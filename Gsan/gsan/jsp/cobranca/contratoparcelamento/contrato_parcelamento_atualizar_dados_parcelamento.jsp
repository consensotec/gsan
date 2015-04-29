<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="gsan.util.ConstantesSistema"%>
<%@ page import="gsan.cobranca.contratoparcelamento.PrestacaoContratoParcelamento" %>
<%@ page import="gsan.cobranca.contratoparcelamento.QuantidadePrestacoesRDHelper" %>


<script type="text/javascript">
	function replaceAll(str, de, para){
    var pos = str.indexOf(de);
    while (pos > -1){
		str = str.replace(de, para);
		pos = str.indexOf(de);
	}
    return (str);
}

	function escolheuRD(dataVigenciaInicio, dataVigenciaFinal, dataContrato, numeroRD){
		
		var form = document.forms[0];
		if(comparaData(dataVigenciaFinal, "<", dataContrato) || comparaData(dataVigenciaInicio, ">", dataContrato)){
			alert("O per�odo de vig�ncia da RD " + numeroRD + " " + dataVigenciaInicio + " - " + dataVigenciaFinal + " n�o abrange a data do contrato " + dataContrato+". Selecione outra Resolu��o de Diretoria." );
			form.resolucaoDiretoria.selectedIndex = 0; 			
		}else{
			form.pacerlaAdd.value = "";
			 form.taxaJurosAdd.value = "";
			 form.action = "exibirAtualizarContratoParcelamentoClienteAction.do?method=mostrarTerceiraEtapa&escolheuRD=true";
     		 form.submit();
		}
		
	}
	
	 function verificaVirgula(event){
       	var char = null;
       		if (event.which == null){
				     char= String.fromCharCode(event.keyCode);    // IE
				}else if (event.which != 0 && event.charCode != 0){
				     char= String.fromCharCode(event.which);
				 }   
				 
       		if(char != ','){
				return isCampoNumerico(event);
			}else{
				var form = document.forms[0];	
				 var taxaJuros = form.taxaJurosAdd.value;
				if(taxaJuros.indexOf(",") != -1){
					return isCampoNumerico(event);
				}
			}
			
       }
	 
	 function naoSelecionouRD(){
		 var form = document.forms[0];

		 form.indicadorDebitoAcresc[0].checked = true;
		 form.indicadorParcelJuros[0].checked = true;
		 form.indicadorInfoVlParcel[1].checked = true;
		 form.valorDaParcela.value = '';
		 
		 form.action = "exibirAtualizarContratoParcelamentoClienteAction.do?method=mostrarTerceiraEtapa&escolheuRD=true";
 		 form.submit();
	 }

	 function verifica_data (data) { 

         dia = (data.value.substring(0,2)); 
         mes = (data.value.substring(3,5)); 
         ano = (data.value.substring(6,10)); 

         // verifica o dia valido para cada mes 
         if ((dia < 01)||(dia < 01 || dia > 30) && (  mes == 04 || mes == 06 || mes == 09 || mes == 11 ) || dia > 31) { 
             return false;
         } 

         // verifica se o mes e valido 
         if (mes < 01 || mes > 12 ) { 
             return false;
         } 

         // verifica se e ano bissexto 
         if (mes == 2 && ( dia < 01 || dia > 29 || ( dia > 28 && (parseInt(ano / 4) != ano / 4)))) { 
             return false;
         } 
 
         if (data.value == "") { 
             return false;
         } 
 
         return true;
       } 
	 
	 function selecionouParcelJurosNao(){
		 var form = document.forms[0];
		 
		 if(form.indicadorParcelJuros.disabled == null || form.indicadorParcelJuros.disabled == false){
			 form.taxaJurosAdd.value = "";
			 form.taxaJurosAdd.style.color = "#000000";
			 form.taxaJurosAdd.readOnly = true;
			 form.taxaJurosAdd.style.backgroundColor = '#EFEFEF';
		 	
		 } 
		 
	 	 form.indicadorInfoVlParcel[0].disabled = false;
	 	 form.indicadorInfoVlParcel[1].disabled = false;
	 	 
	 	 limparTotalizacaoParcelas();
	 }
	 
	 function selecionouParcelJurosSim(){
		 var form = document.forms[0];
		 
		 if(form.indicadorParcelJuros.disabled == null || form.indicadorParcelJuros.disabled == false){
			 if(form.resolucaoDiretoria.selectedIndex == 0 || form.resolucaoDiretoria.selectedIndex == null){
				 form.taxaJurosAdd.value = "";
				 form.taxaJurosAdd.style.color = "#000000";
				 form.taxaJurosAdd.readOnly = false;
			 	 form.taxaJurosAdd.disabled = false;
				 form.taxaJurosAdd.style.backgroundColor = '';
			 }
		 }
		 
	 	 form.indicadorInfoVlParcel.value = "2";
	 	 form.indicadorInfoVlParcel[0].checked = false;
	 	 form.indicadorInfoVlParcel[1].checked = true;
	 	 form.indicadorInfoVlParcel[0].disabled = true;
	 	 form.indicadorInfoVlParcel[1].disabled = true;
	 	 selecionouInformarParcelNao();
	 	 
	 	 limparTotalizacaoParcelas();
	 }
	 
	 function selecionouInformarParcelNao(){
		 var form = document.forms[0];
		 
		 form.btInfoValorParcel.disabled = true;
		 
	 }
	 
	 function selecionouInformarParcelSim(){
		 var form = document.forms[0];
		 
		 form.btInfoValorParcel.disabled = false;
		 
	 }
	 
	 function selecionouParcela(qtdParcelas, taxaJuros, valorParcela, etapa){
		 var form = document.forms[0];

		if(etapa == 'informouParcelas'){
			alert("As parcelas j� foram informadas por meio do bot�o \"Informar Valor Parcelas\". Caso queira informar o n�mero de parcelas por meio da Lista de \"Parcelas x Taxa de Juros (%) x Valor da Parcela da RD\" clique no bot�o \"Informar Valor Parcelas\" e depois clique no bot�o \"Cancelar\".");
			var radioBtns = form.parcelaSelecao;
			 if(radioBtns != null){
		      	for(var i = 0; i < radioBtns.length; i++){
		      			radioBtns[i].checked = false;
		      	}
			 }
		}else{
			 form.pacerlaAdd.value = qtdParcelas;
			 form.pacerlaAdd.style.color = "#000000";
			 form.pacerlaAdd.readOnly = true;
			 form.pacerlaAdd.style.backgroundColor = '#EFEFEF';
			 form.taxaJurosAdd.value = taxaJuros;
			 form.taxaJurosAdd.style.color = "#000000";
			 form.taxaJurosAdd.readOnly = true;
			 form.taxaJurosAdd.style.backgroundColor = '#EFEFEF'
			 form.valorDaParcela.value = valorParcela;
 		 	 
			 valorParcela =  replaceAll(valorParcela,".","");
			 
			 var total = valorParcela.replace(",",".") * qtdParcelas;
			 form.valorParceladoFinal.value = float2moeda(total);
			 
			 montarTabelaParcelaXVlParcelaFinal(qtdParcelas, valorParcela);

		}
		 
	 }
	 
	 function float2moeda (num){
		 x = 0;

		   if(num<0) {
		      num = Math.abs(num);
		      x = 1;
		   }
		   if(isNaN(num)) num = "0";
		      cents = Math.floor((num*100+0.5)%100);

		   num = Math.floor((num*100+0.5)/100).toString();

		   if(cents < 10) cents = "0" + cents;
		      for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
		         num = num.substring(0,num.length-(4*i+3))+'.'
		               +num.substring(num.length-(4*i+3));
		      
	      ret = num + ',' + cents;
	      
	      if (x == 1) ret = ' - ' + ret;return ret;

	 }
	 
	 function montarTabelaParcelaXVlParcelaFinal(qtdParcelas, valorParcela){
		 var numeroParcela = 1;
		 var tabela = document.getElementById("tabelaParcelaXVlParcelaFinal");
		 
		 var tBody = tabela.getElementsByTagName('tbody')[0];
		 tBody.innerHTML = "";
		 
		 valorParcela = replaceAll(valorParcela,".","");
		 valorParcela = valorParcela.replace(",",".");

		var cor = "#FFFFFF";
		 
		 while(numeroParcela <= qtdParcelas){
			 
			 var newTR = document.createElement('tr');
			 newTR.bgColor = cor;
			 var newParcel = document.createElement('td');
			 newParcel.align = "center";
			 var newValor = document.createElement('td');
			 newValor.align = "center";
			 newParcel.innerHTML = numeroParcela;
			 
			 newValor.innerHTML = float2moeda(valorParcela);	 
			 newTR.appendChild (newParcel);
			 newTR.appendChild (newValor);
			 tBody.appendChild(newTR);
			  
			 numeroParcela = numeroParcela + 1;

			 if(cor == "#FFFFFF"){
				 cor = "#cbe5fe";
			}else{
				cor = "#FFFFFF";
			}
		 }
	 }
	 
	 function desmarcarParcela(){
		 var form = document.forms[0];
		 form.pacerlaAdd.value = "";
		 form.taxaJurosAdd.value = "";
		 form.valorDaParcela.value = "";
		 
		 var radioBtns = form.parcelaSelecao;
		 if(radioBtns != null){
	      	for(var i = 0; i < radioBtns.length; i++){
	      			radioBtns[i].checked = false;
	      	}
		 }
		 
		var tabela = document.getElementById("tabelaParcelaXVlParcelaFinal");
		 
		var tBody = tabela.getElementsByTagName('tbody')[0];
		 tBody.innerHTML = "";
		 form.valorParceladoFinal.value = "";
		 
		 form.action = "exibirAtualizarContratoParcelamentoClienteAction.do?method=mostrarTerceiraEtapa";
 		 form.submit();
		
		
	 }
	 </script>
	 
	 <script type="text/javascript"><!--
	 function clicouInformarParcela(){
		 var form = document.forms[0];
		 var existeSelecionado = false;
		 
		 var radioBtns = form.parcelaSelecao;
		 if(radioBtns != null){
		      	for(var i = 0; i < radioBtns.length; i++){
		      		if(radioBtns[i].checked == true){
		      			existeSelecionado = true;
		      		}
		      	}
		 }
      	if(existeSelecionado == true){
      		alert("Os dados das parcelas j� foram informadas por meio da Lista de \"Parcelas x Taxa de Juros (%) x Valor da Parcela da RD\". Caso queira informar os dados das parcelas por meio do bot�o \"Informar Valor Parcelas\" desmarque a combina��o da lista.");
      	}else{
      		
      		if((form.taxaJurosAdd.value != "" && form.taxaJurosAdd.value != "0") || form.pacerlaAdd.value != ""){
      			alert("O \"N�mero de Parcelas\" j� foi informado. Caso queira informar os dados das parcelas por meio do bot�o \"Informar Valor Parcelas\" limpe o campo \"N�mero de Parcelas\" e o campo \"Taxa de Juros\"");
      		}else{
	      		var numeroParcelasAdd = document.getElementById("numeroParcelasPopUp");
	      		var valorParceladoPopUp = document.getElementById("valorParceladoPopUp");
	      		
	      		var valorContaSelecaoTotal =  document.getElementById('valorContaSelecaoTotal').innerHTML;
	      		var valorContaAcrescimoSelecaoTotal =  document.getElementById('valorContaAcrescimoSelecaoTotal').innerHTML;

	      		valorContaSelecaoTotal = replaceAll(valorContaSelecaoTotal,".","");
	    		valorContaSelecaoTotal = valorContaSelecaoTotal.replace(",",".");
	    		valorContaAcrescimoSelecaoTotal = replaceAll(valorContaAcrescimoSelecaoTotal,".","");
	    		valorContaAcrescimoSelecaoTotal = valorContaAcrescimoSelecaoTotal.replace(",",".");

	    		if(form.indicadorDebitoAcresc[0].checked == true){
	    		
   					valorParceladoPopUp.innerHTML = formatarValorMonetario(valorContaAcrescimoSelecaoTotal);

    			}else if(form.indicadorDebitoAcresc[1].checked == true){
    				
   					valorParceladoPopUp.innerHTML = formatarValorMonetario(valorContaSelecaoTotal);

    			}

	      		centerPopup();
	    		loadPopup();
      		}
      	}
		 
	 }
	 
	 function digitouNumeroParcel(){
		 var tabela = document.getElementById("tabelaParcelaXVlParcelaFinal");
		 var tBody = tabela.getElementsByTagName('tbody')[0];
		 var linhas = tBody.rows.length;
			
		if(linhas > 0){
			alert("As parcelas j� foram informadas por meio do bot�o \"Informar Valor Parcelas\". Caso queira informar o n�mero de parcelas para o sistema calcular o valor das parcelas clique no bot�o \"Informar Valor Parcelas\" e depois clique no bot�o \"Cancelar\" ");
		}
	 }
	 
	 function informouTaxaJuros(){
		 
		var form = document.forms[0];
		var tabela = document.getElementById("tabelaParcelaXVlParcelaFinal");
		 
		var tBody = tabela.getElementsByTagName('tbody')[0];
		var linhas = tBody.rows.length;
		
		
	 }
	 
	 function calcularValoresFinal(valorTotalParcelado){
		 var valorContaSelecaoTotal =  document.getElementById('valorContaSelecaoTotal').innerHTML;
   		 var valorContaAcrescimoSelecaoTotal =  document.getElementById('valorContaAcrescimoSelecaoTotal').innerHTML;
		 
		 valorContaSelecaoTotal = replaceAll(valorContaSelecaoTotal,".","");
		 valorContaSelecaoTotal = valorContaSelecaoTotal.replace(",",".");
		 valorContaAcrescimoSelecaoTotal = replaceAll(valorContaAcrescimoSelecaoTotal,".","");
		 valorContaAcrescimoSelecaoTotal = valorContaAcrescimoSelecaoTotal.replace(",",".");
		 
   		 var form = document.forms[0];

		var juros = form.taxaJurosAdd.value;
		
		 	if(form.indicadorDebitoAcresc[0].checked == true){
				var jurosAdd = 0;
				if(juros != ""){
					jurosAdd = juros.replace(",",".") * valorContaAcrescimoSelecaoTotal;
					jurosAdd = jurosAdd / 100;
				}
				var valorFinal = parseFloat(valorContaAcrescimoSelecaoTotal) + parseFloat(jurosAdd);
				var valorFinalAjustado = valorFinal.toFixed(2);
				
		 		form.valorParceladoFinal.value = float2moeda(valorFinalAjustado);
			}else if(form.indicadorDebitoAcresc[1].checked == true){
				var jurosAdd = 0;
				if(juros != ""){
					jurosAdd = juros.replace(",",".") * valorContaSelecaoTotal.replace(",",".");
					jurosAdd = jurosAdd / 100;
				}
				var valorFinal = parseFloat(valorContaSelecaoTotal) + parseFloat(jurosAdd);
				var valorFinalAjustado = valorFinal.toFixed(2);

				form.valorParceladoFinal.value = float2moeda(valorFinalAjustado);
			}

			if(valorTotalParcelado != null && valorTotalParcelado != ''){
				form.valorParceladoFinal.value = valorTotalParcelado;
			}
	 }
	 
	 function roundNumber(num, dec){
		var result = Math.round(num*Math.pow(10,dec))/Math.pow(10,dec);
		return result;
	 }
	 
	 function limparTotalizacaoParcelas() {
	 	var form = document.forms[0];
	 	form.action = "exibirAtualizarContratoParcelamentoClienteAction.do?limparTotalizacaoParcelas=sim";
    	form.submit();
	 }
	 
	 function limparListaParcelas() {
	 	var form = document.forms[0];
	 	form.action = "exibirAtualizarContratoParcelamentoClienteAction.do?limparListaParcelas=sim";
    	form.submit();
	 }

	 function atualizarDataVencimento() {
	 	var form = document.forms[0];
	 	form.action = "exibirAtualizarContratoParcelamentoClienteAction.do?method=atualizarDataVencimento";
    	form.submit(); 
	 }
	 
--></script>

<table>
	<tr>
		<td colspan="4">
		<div align="left">
		<div align="left">
		<table width="100%" border="0" bgcolor="#90c7fc">
			<tr>
				<td width="50"  bgcolor="#90c7fc">
				<div align="center"></div>
				</td>
				<td width="50"  bgcolor="#90c7fc">
				<div align="center"><strong>Valor</strong></div>
				<div align="center"></div>
				</td>
				<td width="100"  bgcolor="#90c7fc">
				<div align="center"><strong>Acr&eacute;scimos por Impontualidade</strong></div>
				</td>
				<td width="100"  bgcolor="#90c7fc">
				<div align="center"><strong>D&eacute;bito Com Acr&eacute;scimo</strong></div>
				</td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF">
				<div align="left"><strong>Contas</strong></div>
				</td>
				<td bgcolor="#FFFFFF">
				<div align="right"><fmt:formatNumber value="${valorContasSelecao}" minFractionDigits="2" maxFractionDigits="2" type="number" /> </div>
				</td>
				<td bgcolor="#FFFFFF">
				<div align="right"><fmt:formatNumber value="${valorAcrescSelecao}" minFractionDigits="2" maxFractionDigits="2" type="number" /> 
				</div>
				</td>
				<td bgcolor="#FFFFFF">
				<div align="right"><fmt:formatNumber value="${valorContaComAcrescimoSelecao}" minFractionDigits="2" maxFractionDigits="2" type="number" /> 
				</div>
				</td>
			</tr>
			<tr>
				<td bgcolor="#cbe5fe">
				<div align="left"><strong>D�bitos a Cobrar</strong></div>
				</td>
				<td bgcolor="#cbe5fe">
				<div align="right"><fmt:formatNumber value="${valorDebitosACobrarSelecao}" minFractionDigits="2" maxFractionDigits="2" type="number" /> </div>
				</td>
				<td bgcolor="#cbe5fe">
				<div align="right"><fmt:formatNumber value="0.0" minFractionDigits="2" maxFractionDigits="2" type="number" /> 
				</div>
				</td>
				<td bgcolor="#cbe5fe">
				<div align="right"><fmt:formatNumber value="${valorDebitosACobrarSelecao}" minFractionDigits="2" maxFractionDigits="2" type="number" /> 
				</div>
				</td>
			</tr>
			<tr>
				<td bgcolor="#FFFFFF">
				<div align="left"><strong>Total Geral</strong></div>
				</td>
				<td  bgcolor="#FFFFFF">
				<div align="right" id="valorContaSelecaoTotal"><fmt:formatNumber value="${valorContaSelecao}" minFractionDigits="2" maxFractionDigits="2" type="number" /></div>
				<div align="center"></div>
				</td>
				<td  bgcolor="#FFFFFF">
				<div align="right" id="valorAcrescimoSelecaoTotal"><fmt:formatNumber value="${valorAcrescimoSelecao}" minFractionDigits="2" maxFractionDigits="2" type="number" /></div>
				</td>
				<td  bgcolor="#FFFFFF">
				<div align="right" id="valorContaAcrescimoSelecaoTotal"><fmt:formatNumber value="${valorContaAcrescimoSelecao}" minFractionDigits="2" maxFractionDigits="2" type="number"/></div>
				</td>
			</tr>
		</table>
		</div>
		</div>
		</td>
	</tr>
	<tr>
		<td colspan="4">
		<div align="left">
		<div align="left">
		<hr>
		</div>
		</div>
		</td>
	</tr>
	<tr>
		<td colspan="2"><strong>Resolu&ccedil;&atilde;o de Diretoria:</strong><strong>
		<font color="#FF0000"></font></strong></td>
		<td colspan="2">
			<html:select property="resolucaoDiretoria" >
				<option value="" onclick="naoSelecionouRD();"></option>
				<logic:notEmpty name="collResolucoesDiretoria">
					<logic:iterate name="collResolucoesDiretoria" id="resolucaoDiretoria">
						<option value="${resolucaoDiretoria.numero}"  <c:if test="${contratoAtualizar.resolucaoDiretoria.id == resolucaoDiretoria.id}">selected="selected"</c:if>  onclick="escolheuRD('<c:out value="${resolucaoDiretoria.dataVigenciaInicioFormatada}"></c:out>','<c:out value="${resolucaoDiretoria.dataVigenciaFinalFormatada}"></c:out>','<c:out value="${contratoAtualizar.dataContratoFormatada}"></c:out>','<c:out value="${resolucaoDiretoria.numero}"></c:out>')">
							<c:out value="${resolucaoDiretoria.numero}"></c:out>
						</option>
					</logic:iterate>
				</logic:notEmpty>
			</html:select>	
		</td>
	</tr>
	<tr>
		<td colspan="4">
		<div align="left">
		<div align="left">
		<hr>
		</div>
		</div>
		</td>
	</tr>
	<tr>
		<td colspan="2"><strong>D&eacute;bito com Acr&eacute;scimo:</strong><strong><span
			class="style2"><strong><font color="#FF0000">*</font></strong></span></strong></td>
		<td colspan="2">
		
		<input name="indicadorDebitoAcresc"
			type="radio" value="1" onclick="calcularValoresFinal('');limparTotalizacaoParcelas();"
					<c:if test="${contratoAtualizar.resolucaoDiretoria == null}">checked="checked"</c:if> 
					<c:if test="${contratoAtualizar.resolucaoDiretoria != null && contratoAtualizar.indicadorDebitosAcrescimos == 2}">disabled="disabled"</c:if>
					<c:if test="${contratoAtualizar.indicadorDebitosAcrescimos == 1}">checked="checked"</c:if> > <strong>Sim</strong> 
			
			<input name="indicadorDebitoAcresc" type="radio" 	value="2"  onclick="calcularValoresFinal('');limparTotalizacaoParcelas();"
					<c:if test="${contratoAtualizar.indicadorDebitosAcrescimos == 2}">checked="checked"</c:if> 
					<c:if test="${(permissaoEspecialDebitoAcrescimos != null && permissaoEspecialDebitoAcrescimos == false) 
						|| (contratoAtualizar.resolucaoDiretoria != null && contratoAtualizar.indicadorDebitosAcrescimos == 1)}">disabled="disabled"</c:if>> 
			<strong>N&atilde;o</strong>
			</td>
	</tr>
	<tr>
		<td colspan="2"><strong>Parcelamento com Juros:</strong><strong><span
			class="style2"><strong><font color="#FF0000">*</font></strong></span></strong></td>
		<td colspan="2"><input name="indicadorParcelJuros" onclick="selecionouParcelJurosSim();" <c:if test="${contratoAtualizar.indicadorParcelamentoJuros == 1}">checked="checked"</c:if>
			<c:if test="${contratoAtualizar.resolucaoDiretoria != null && contratoAtualizar.indicadorParcelamentoJuros == 2}">disabled="disabled"</c:if>
			type="radio" value="1" <c:if test="${contratoAtualizar.resolucaoDiretoria == null}">checked="checked"</c:if> > <strong>Sim</strong> 
			
			<input onclick="selecionouParcelJurosNao();" <c:if test="${contratoAtualizar.resolucaoDiretoria != null && contratoAtualizar.indicadorParcelamentoJuros == 1}">disabled="disabled"</c:if>
			type="radio" name="indicadorParcelJuros" <c:if test="${contratoAtualizar.indicadorParcelamentoJuros == 2}">checked="checked"</c:if>
			value="2"> <strong>N&atilde;o</strong></td>
	</tr>
	<tr>
		<td colspan="2"><strong>Permite Informar o Valor da Parcela:</strong><strong><span
			class="style2"><strong><font color="#FF0000">*</font></strong></span></strong></td>
		<td colspan="2">
			<input name="indicadorInfoVlParcel"  onclick="selecionouInformarParcelSim();limparTotalizacaoParcelas();"
				<c:if test="${contratoAtualizar.indicadorParcelamentoJuros == 2 && contratoAtualizar.indicadorPermiteInformarValorParcel == 1}">checked="checked"</c:if>
					<c:if test="${contratoAtualizar.indicadorParcelamentoJuros == 1 || (contratoAtualizar.resolucaoDiretoria != null && contratoAtualizar.indicadorPermiteInformarValorParcel == 2)}">disabled="disabled"</c:if>  
				type="radio" value="1"> <strong>Sim</strong> 
			<input onclick="selecionouInformarParcelNao();limparTotalizacaoParcelas();"
				<c:if test="${contratoAtualizar.indicadorParcelamentoJuros == 1 || (contratoAtualizar.resolucaoDiretoria != null && contratoAtualizar.indicadorPermiteInformarValorParcel == 1)}">disabled="disabled"</c:if>
				name="indicadorInfoVlParcel" type="radio" <c:if test="${contratoAtualizar.indicadorParcelamentoJuros == 1 || contratoAtualizar.indicadorPermiteInformarValorParcel == 2}" >checked="checked"</c:if>
				value="2" > <strong>N&atilde;o</strong>
		</td>
	</tr>
	<tr>
		<td colspan="2"><strong>Forma de Pagamento:</strong><strong><span
			class="style2"><strong><font color="#FF0000">*</font></strong></span></strong></td>
		<td colspan="2"><strong> 
			<logic:present name="formaPagamentoDesabilitada" >
				<html:text property="formaPagamentoRD"
					readonly="true" 
					style="background-color:#EFEFEF; border:0; color: #000000" 
					size="20" maxlength="20" />
			</logic:present>
        	<logic:notPresent name="formaPagamentoDesabilitada" >
        		<html:select property="formaPgto" > 
					<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
					<html:options collection="collFormaPgto" 
						labelProperty="descricao" 
						property="id" />
				</html:select>
        	</logic:notPresent>
		 </strong></td>
	</tr>
	<tr>
		<td colspan="4">
		<table width="100%" border="0" bgcolor="#90c7fc">
			<tr>
				<td width="50" bgcolor="#90c7fc">&nbsp;</td>
				<td width="50" bgcolor="#90c7fc">
				<div align="center"><strong> Parcelas</strong></div>
				</td>
				<td width="100" bgcolor="#90c7fc">
				<div align="center"><strong>Taxa de Juros (%)</strong></div>
				</td>
				<td width="100" bgcolor="#90c7fc">
				<div align="center"><strong>Valor da Parcela</strong></div>
				</td>
			</tr>
			
			<% String corTabelaParcelas = "#FFFFFF"; %>
			<logic:notEmpty name="colecaoQuantidadePrestacoesRDHelper">
				<logic:iterate 
					name="colecaoQuantidadePrestacoesRDHelper" 
					id="quantidadePrestacoesRDHelper" 
					type="QuantidadePrestacoesRDHelper">
					<tr bgcolor="<%=corTabelaParcelas %>">
						
						<td bordercolor="#90c7fc">
						<div align="center"><strong> 
							<input type="radio" <c:if test="${contratoAtualizar.qtdPrestacoesDaRDEscolhida != null && contratoAtualizar.qtdPrestacoesDaRDEscolhida.id == quantidadePrestacoesRDHelper.idQuantidadePrestacoes}">checked="checked"</c:if>
								name="parcelaSelecao" value="${quantidadePrestacoesRDHelper.idQuantidadePrestacoes }" 
								onClick="selecionouParcela('<c:out value="${quantidadePrestacoesRDHelper.quantidadeParcelas}"></c:out>','<c:out value="${quantidadePrestacoesRDHelper.taxaJuros}" ></c:out>','<fmt:formatNumber value="${quantidadePrestacoesRDHelper.valorDaParcela}"  minFractionDigits="2" maxFractionDigits="2" type="number"/>', '<c:out value="${etapa}"></c:out>')" 
								/> 
						</strong></div>
						</td>
						<td>
						<div align="center"><c:out value="${quantidadePrestacoesRDHelper.quantidadeParcelas}"></c:out></div>
						</td>
						<td>
						<div align="center">
							<fmt:formatNumber value="${quantidadePrestacoesRDHelper.taxaJuros}"  minFractionDigits="2" maxFractionDigits="2" type="number"/>
						</div>
						</td>
						<td>
							<div align="center">
								<fmt:formatNumber value="${quantidadePrestacoesRDHelper.valorDaParcela}"  minFractionDigits="2" maxFractionDigits="2" type="number"/>
							</div>
						</td>
					</tr>
					<%
						if(corTabelaParcelas.equals("#FFFFFF")){
							corTabelaParcelas = "#cbe5fe";
						}else{
							corTabelaParcelas = "#FFFFFF";
						}
					%>
				</logic:iterate>
			</logic:notEmpty>
		</table>
				<tr>
					<td colspan="5" align="right">
					 	<input name="desmarcar" type="button" onclick="javascript: desmarcarParcela();" class="bottonRightCol"  value="Desmarcar" /> 
					</td>
				</tr>
	<tr>
		<td colspan="4">
		<div align="left">
		<div align="left">
		<hr>
		</div>
		</div>
		</td>
	</tr>
	<tr>
		<td colspan="2"><strong>N�mero de Dias entre os Vencimentos das
		Parcelas:</strong></td>
		<td colspan="2">
			<html:select property="numeroEntreVencParcelas" onchange="atualizarDataVencimento();">
					<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
					<html:option value="5">5</html:option>
					<html:option value="10">10</html:option>
					<html:option value="15">15</html:option>
					<html:option value="20">20</html:option>
					<html:option value="25">25</html:option>
					<html:option value="30">30</html:option>							
			</html:select> 
		</td>
	</tr>
	<tr>
		<td colspan="2"><strong>Data de Vencimento da 1a. Parcela:<font color="#FF0000">*</font></strong></td>
		<td colspan="2">
		<div align="left"><span class="style2"><strong> 
			<input name="dataVencimentoPrimParcela" type="text" 
			   onkeyup="mascaraData(this, event);" onkeypress="return isCampoNumerico(event);"
			 size="10" maxlength="10"	value="${contratoAtualizar.dataVencimentoPrimParcelaFormatada}"				
			<c:if test="${(temPermissaoAlterarVencimentoContratoParcelamentoCliente != null && temPermissaoAlterarVencimentoContratoParcelamentoCliente == false)}">readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"</c:if>
			> 
		 </strong>(dd/mm/aaaa)<strong> </strong></span>
		</div>
		</td>
	</tr>
	<tr>
		<td colspan="2"><strong>N&uacute;mero de Parcelas:</strong></td>
		<td colspan="2"><strong> 
			<logic:present name="numeroParcelasDesabilitada" >
				<html:text property="pacerlaAdd" 
						onkeypress="return isCampoNumerico(event);" 
						onchange="limparListaParcelas();"
						readonly="true" 
						style="text-align:right; background-color:#EFEFEF; color: #000000"
						size="3" maxlength="3"
						/> 
			</logic:present>
			<logic:notPresent name="numeroParcelasDesabilitada" >
				<html:text property="pacerlaAdd" 
						onkeypress="return isCampoNumerico(event);" 
						onchange="limparListaParcelas();"
						size="3" maxlength="3"
						style="text-align:right;"
						/> 
			</logic:notPresent>
		</strong></td>
	</tr>
	<tr>
		<td colspan="2"><strong>Taxa de Juros:</strong></td>
		
		<logic:present name="taxaJurosDesabilitada" >
			<td colspan="2"><strong> 
				<html:text property="taxaJurosAdd" 
					onkeypress="return verificaVirgula(event);" 
					onblur="informouTaxaJuros();" 
					onkeyup="formataValorMonetario(this, 6);"
					onchange="limparListaParcelas();"
					size="3" maxlength="6" 
					readonly="true" 
					style="text-align:right; background-color:#EFEFEF; color: #000000"  /> 
			 %</strong></td>
		</logic:present>
		<logic:notPresent name="taxaJurosDesabilitada" >
			<td colspan="2"><strong> 
				<html:text property="taxaJurosAdd" 
					onkeypress="return verificaVirgula(event);" 
					onblur="informouTaxaJuros();" 
					onkeyup="formataValorMonetario(this, 6);"
					onchange="limparListaParcelas();"
					style="text-align:right;"
					size="3" maxlength="6"
					 /> 
			%</strong></td>	
		</logic:notPresent>
			
	</tr>
	<tr>
		<td colspan="2"><strong>Valor da Parcela:</strong></td>
		<td width="142"><strong> <html:text property="valorDaParcela"
			readonly="true" 
			style="background-color:#EFEFEF; border:0; color: #000000" 
			size="20" maxlength="20" /> </strong></td>
		<td width="236">
		<div align="right"><strong> 
			<input name="btInfoValorParcel" type="button" onclick="javascript: clicouInformarParcela();"
			class="bottonRightCol" <c:if test="${contratoAtualizar.indicadorParcelamentoJuros == 1 || contratoAtualizar.indicadorPermiteInformarValorParcel == 2}">disabled="disabled"</c:if> value="Informar Valor Parcelas"> </strong></div>
		</td>
	</tr>

	<tr>
		<td colspan="4">
		<div align="left">
		<div align="left">
		<hr>
		</div>
		</div>
		</td>
	</tr>
	<tr>
		<td colspan="2"><b class="style1">Condi&ccedil;&otilde;es da
		Negocia&ccedil;&atilde;o</b><strong>: <font color="#FF0000"></font></strong></td>
		<td colspan="2">&nbsp;</td>
	</tr>
	<tr>
		<td colspan="2"><strong>Valor Parcelado</strong><strong>: <font
			color="#FF0000"></font></strong></td>
		<td colspan="2"><strong> 
		
		<input 
			name="valorParceladoFinal" 
			type="text" 
			readonly="true" 
			style="background-color:#EFEFEF; border:0; color: #000000" 
			size="20" 
			maxlength="20"
			value="<fmt:formatNumber value="${contratoAtualizar.valorTotalParcelado}"  minFractionDigits="2" maxFractionDigits="2" type="number"/>"
		> 
		</strong></td>
	</tr>
	<tr>
		<td colspan="4">
		<table width="100%" border="0" id="tabelaParcelaXVlParcelaFinal" bgcolor="#90c7fc">
			<thead>
				<tr>
					<td width="100"  bgcolor="#90c7fc">
					<div align="center"><strong> Parcelas</strong></div>
					</td>
					<td width="200"  bgcolor="#90c7fc">
					<div align="center"><strong>Valor da Parcela</strong></div>
					</td>
				</tr>
			</thead>
			<tbody>
					<%
							String corTabelaParcelFinal = "#FFFFFF";
										List<PrestacaoContratoParcelamento> listaDeParcelas = (List<PrestacaoContratoParcelamento>) session.getAttribute("listaDeParcelas");
										if(listaDeParcelas != null){ %>
											
											<%
											if(listaDeParcelas.size() == 1){
												PrestacaoContratoParcelamento parcelaIni = listaDeParcelas.get(0); 
											%>
												<tr bgcolor="<%=corTabelaParcelFinal %>"> 
													<td>
														<div align="center"><%=parcelaIni.getNumero()%></div>
													</td>
													<td>
														<div align="center">
															<fmt:formatNumber value="<%=parcelaIni.getValor()%>"  minFractionDigits="2"/>
														</div>
													</td>
												</tr>
												
											<%}else{
												
											List<Float> listaValoresDistintos = (List<Float>) session.getAttribute("listaValoresDistintos");
											ArrayList<Integer> idsParcelas = new ArrayList<Integer>();
											
											for(int i = 0; i < listaValoresDistintos.size(); i++)
											{ 
												
												List<PrestacaoContratoParcelamento> parcelasGrupo = new ArrayList<PrestacaoContratoParcelamento>();
												
												for(int j = 0; j < listaDeParcelas.size(); j++){
													
													
													if(listaValoresDistintos.get(i).floatValue() == listaDeParcelas.get(j).getValor().floatValue()){
														
															if (j+1 == listaDeParcelas.size() || !idsParcelas.contains(listaDeParcelas.get(j).getNumero()) ||
																	(j != 0 && listaDeParcelas.get(j-1).getNumero() + 1 ==  listaDeParcelas.get(j).getNumero() )){
																
																parcelasGrupo.add(listaDeParcelas.get(j));
																idsParcelas.add(listaDeParcelas.get(j).getNumero());
																if(j+1 < listaDeParcelas.size() &&
																		listaValoresDistintos.get(i) != listaDeParcelas.get(j+1).getValor().floatValue()){
																	j = listaDeParcelas.size();
																}
															}else{
																j = listaDeParcelas.size();
															}
													}
													
												}
												
												
											if(parcelasGrupo.size() > 1){
												%>			<tr bgcolor="<%=corTabelaParcelFinal %>">
															
																<td>
																	<div align="center">
																			<%=parcelasGrupo.get(0).getNumero()%>-<%=parcelasGrupo.get(parcelasGrupo.size()-1).getNumero()%>
																	</div>
																</td>
																<td>
																	<div align="center">
																		<fmt:formatNumber value="<%=parcelasGrupo.get(0).getValor()%>"  minFractionDigits="2"/>
																	</div>
																</td>
															</tr>
													
												<% }else if(parcelasGrupo.size() == 1){%>
														<tr bgcolor="<%=corTabelaParcelFinal %>">
															<td>
																<div align="center"><%=parcelasGrupo.get(0).getNumero()%></div>
															</td>
															<td>
																<div align="center">
																	<fmt:formatNumber value="<%=parcelasGrupo.get(0).getValor()%>"  minFractionDigits="2"/>
																</div>
															</td>
														</tr>
													
													<%}
													
													if(corTabelaParcelFinal.equals("#FFFFFF")){
														corTabelaParcelFinal = "#cbe5fe";
													}else{
														corTabelaParcelFinal = "#FFFFFF";
													}
													
													%>
											<% }%> 	
											<% }%> 	
										<% }%> 	
					
			
			</tbody>
		</table>
		</td>
	</tr>
</table>
