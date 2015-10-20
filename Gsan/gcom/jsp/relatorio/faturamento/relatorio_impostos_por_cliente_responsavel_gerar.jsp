<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ include file="/jsp/util/telaespera.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>



<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="RelatorioImpostosPorClienteResponsavelActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];
    
     if (tipoConsulta == 'cliente') {
      form.clienteID.value = codigoRegistro;
      form.nomeCliente.value = descricaoRegistro;
      form.nomeCliente.style.color = "#000000";     
      
      form.action = "exibirGerarRelatorioImpostosPorClienteResponsavelAction.do?objetoConsulta=1";
	  submeterFormPadrao(form);

    }  
}

function validarTipoImposto(){

    var form = document.forms[0];
    var retorno = true;
    
    var indice;
    var array = new Array(form.indicadorTipoImposto.length);
    var selecionado = "";
    var formulario = document.forms[0]; 
    for(indice = 0; indice < form.elements.length; indice++){
	   if (form.elements[indice].type == 'radio' && form.elements[indice].checked == true && formulario.elements[indice].name == "indicadorTipoImposto") {
	      selecionado = form.elements[indice].value;
	      indice = form.elements.length;
	   }
    }    
	if(selecionado == ''){
		alert('Informe Origem.');
		indicadorTipoImposto.focus();
		retorno = false;
	}	
	
	
	return retorno;
}	
 
function verificaAnoMesInicio(mydata) {
	
	var situacao = true;
	
	if (mydata.value.length == 7) {
	
		dia = 1;
    	mes = mydata.value.substring(0,2); 
    	ano = mydata.value.substring(3,7); 

    	if ((!isNaN(mes) || !isNaN(ano)) && (mes.indexOf('.') == -1 && mes.indexOf(',') == -1 && mes.indexOf('/') == -1) &&
			(ano.indexOf('.') == -1 && ano.indexOf(',') == -1 && ano.indexOf('/') == -1)) {
    	
    		// verifica se o mes e valido 
	    	if ((mes * 1) >= 1 && (mes * 1) <= 12 ) { 
	        
	        	// verifica se o ano e valido
	        	if ((ano * 1) != 0 && (ano * 1) >= 1980) {
	        	
	        		// verifica se e ano bissexto 
	    			if ((mes * 1) == 2 && ((dia * 1) < 1 || (dia * 1) > 29 
	    				|| ((dia * 1) > 28 && (((ano * 1) / 4) != (ano * 1) / 4)))) { 
	       				situacao = false; 
	    			}
	        	}
	        	else{
	        		situacao = false;
	        	}
	    	}
	    	else{
	    		situacao = false;
	    	} 
		}
		else{
			situacao = false;
		}
    }
    else if (mydata.value.length > 0){
    	situacao = false;
    }
    
    if (!situacao) { 
	   alert("M�s e Ano de Refer�ncia da Cobran�a Inicial inv�lida.");
	   mydata.value = "";
	   mydata.focus(); 
	}
	
	return situacao;
}

function verificaAnoMesFim(mydata) {
	
	var situacao = true;
	
	if (mydata.value.length == 7) {
	
		dia = 1;
    	mes = mydata.value.substring(0,2); 
    	ano = mydata.value.substring(3,7); 

    	if ((!isNaN(mes) || !isNaN(ano)) && (mes.indexOf('.') == -1 && mes.indexOf(',') == -1 && mes.indexOf('/') == -1) &&
			(ano.indexOf('.') == -1 && ano.indexOf(',') == -1 && ano.indexOf('/') == -1)) {
    	
    		// verifica se o mes e valido 
	    	if ((mes * 1) >= 1 && (mes * 1) <= 12 ) { 
	        
	        	// verifica se o ano e valido
	        	if ((ano * 1) != 0 && (ano * 1) >= 1980) {
	        	
	        		// verifica se e ano bissexto 
	    			if ((mes * 1) == 2 && ((dia * 1) < 1 || (dia * 1) > 29 
	    				|| ((dia * 1) > 28 && (((ano * 1) / 4) != (ano * 1) / 4)))) { 
	       				situacao = false; 
	    			}
	        	}
	        	else{
	        		situacao = false;
	        	}
	    	}
	    	else{
	    		situacao = false;
	    	} 
		}
		else{
			situacao = false;
		}
    }
    else if (mydata.value.length > 0){
    	situacao = false;
    }
    
    if (!situacao) { 
	   alert("M�s e Ano de Refer�ncia da Cobran�a Final.");
	   mydata.value = "";
	   mydata.focus(); 
	}
	
	return situacao;
}

function validarForm(form){
	validarTipoImposto();
  if(validateRelatorioImpostosPorClienteResponsavelActionForm(form)  && 
		 	verificaAnoMesInicio(form.anoMesReferenciaInicial) && 
		 	verificaAnoMesFim(form.anoMesReferenciaFinal)){	 
	toggleBox('demodiv',1);   
  }
}



function limpar(situacao){

	var form = document.forms[0];

	switch (situacao){
       case 1:
		   form.clienteID.value = "";
		   form.nomeCliente.value = "";		   
		   //Coloca o foco no objeto selecionado
		   form.clienteID.focus();
		   break;   
	   default:
          break;
	}
}

function replicaDados(campoOrigem, campoDestino){
	campoDestino.value = campoOrigem.value;
}
</script>


</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<div id="formDiv"><html:form  action="gerarRelatorioImpostosPorClienteResponsavelAction.do" method="post" 
			name="RelatorioImpostosPorClienteResponsavelActionForm"
			type="gcom.gui.faturamento.RelatorioImpostosPorClienteResponsavelActionForm"
			onsubmit="return validarForm(this);">
	
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<input type="hidden" name="tipoPesquisa" />

	<table width="770" border="0" cellspacing="5" cellpadding="0">

		<tr>
			<td width="150" valign="top" class="leftcoltext">
			<div align="center">
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>

			<%@ include file="/jsp/util/informacoes_usuario.jsp"%>

			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>

			<%@ include file="/jsp/util/mensagens.jsp"%>

			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			</div>
			</td>
			<td width="625" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>

			<!--In�cio Tabela Reference a P�gina��o da Tela de Processo-->
			<table>
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Gerar Relat�rio Impostos Por Cliente Respons�vel</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a P�gina��o da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>

					<td colspan="2">Para gerar o Relat�rio, informe os dados gerais
					abaixo: </td>
				</tr>
				<tr>
					<td>
						<strong>Origem:<font color="#FF0000">*</font></strong>
					</td>
					<td colspan="3"><strong> <span class="style1"><strong> 
					<html:radio	property="indicadorTipoImposto" value="1" /> <strong>Faturamento
					<html:radio property="indicadorTipoImposto" value="2" /> Arrecada��o</strong></strong></span></strong></td>
				</tr>
				<tr>
					<td><strong>Per�odo de Refer�ncia:</strong></td>
					<td>
						<html:text  property="anoMesReferenciaInicial" size="7" maxlength="7" tabindex="8"
									onkeyup="replicaDados(document.forms[0].anoMesReferenciaInicial, document.forms[0].anoMesReferenciaFinal);mascaraAnoMes(this, event);"/> 
									&nbsp;&nbsp;A&nbsp;&nbsp;
						<html:text  property="anoMesReferenciaFinal" size="7" maxlength="7" tabindex="8"
									onkeyup="mascaraAnoMes(this, event);" /> &nbsp;&nbsp;(MM/AAAA)</td>
					<td>
				</tr>
				<tr>
					        <td>
					        	<strong>Cliente:</strong>
					        </td>
					        <td>
					        
					        	<logic:present name="desabilitarDadosSolicitanteCliente">
					        		<html:text property="clienteID" size="10" maxlength="9" tabindex="1" readonly="true"/>
									
									<img width="23" height="21" border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Cliente" />
								</logic:present>
								
								<logic:notPresent name="desabilitarDadosSolicitanteCliente">
					        		<html:text property="clienteID" size="10" maxlength="9" tabindex="1" onkeypress="validaEnterComMensagem(event, 'exibirGerarRelatorioImpostosPorClienteResponsavelAction.do?objetoConsulta=1', 'clienteID', 'Cliente');"/>
									
									<a href="javascript:abrirPopup('exibirPesquisarClienteAction.do', 475, 800);">
									<img width="23" height="21" border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Cliente" /></a>
								</logic:notPresent>
					        	
					        	
								<logic:present name="corCliente">
						
									<logic:equal name="corCliente" value="exception">
										<html:text property="nomeCliente" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
									</logic:equal>
						
									<logic:notEqual name="corCliente" value="exception">
										<html:text property="nomeCliente" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
									</logic:notEqual>
						
								</logic:present>
						
								<logic:notPresent name="corCliente">
						
									<logic:empty name="RelatorioImpostosPorClienteResponsavelActionForm" property="clienteID">
										<html:text property="nomeCliente" size="45" value="" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
									</logic:empty>
									<logic:notEmpty name="RelatorioImpostosPorClienteResponsavelActionForm" property="clienteID">
										<html:text property="nomeCliente" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
									</logic:notEmpty>
										
								</logic:notPresent>
								
								<logic:present name="desabilitarDadosSolicitanteCliente">
					        			<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar" />
								</logic:present>
								
								<logic:notPresent name="desabilitarDadosSolicitanteCliente">
					        		<a	href="javascript:limpar(1);">
									<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" title="Apagar" /> </a>
								</logic:notPresent>
								
						 </td>
			    </tr>					
				<tr>
					<td>
						<strong>Tipo de Relat�rio:<font color="#FF0000">*</font></strong>
					</td>
					<td colspan="3">
						<html:select property="relatorioTipo" tabindex="1" style="width:200px;">
							<html:option value="2"> SINTETICO </html:option>
 					        <html:option value="1"> ANALITICO </html:option>
						</html:select>
					</td>
				</tr>	
				<tr>
					<td height="19">
						<strong> <font color="#FF0000"></font></strong>
					</td>
					<td align="right">
						<div align="left">
							<strong><font color="#FF0000">*</font></strong> Campos obrigat&oacute;rios
						</div>
					</td>
				</tr>
				
				<tr>
					<td colspan="2"><input name="Button" type="button" class="bottonRightCol"
						value="Limpar" align="left"
						onclick="window.location.href='/gsan/exibirGerarRelatorioImpostosPorClienteResponsavelAction.do?menu=sim'" tabindex="17">
						<input name="Button" class="bottonRightCol" value="Cancelar"
						type="button" align="left" onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">	
					</td>
					<td align="right" colspan="2">
						<input name="Button" type="button" class="bottonRightCol" value="Gerar" align="left" tabindex="16"
						onclick="validarForm(document.forms[0]);"></td>
					<td align="right"></td>
				</tr>
			</table>
			<p>&nbsp;</p>
		</tr>
	</table>
	<tr>
		<td colspan="3"><%@ include file="/jsp/util/rodape.jsp"%>
	</tr>
<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio_tela_espera.jsp?relatorio=gerarRelatorioImpostosPorClienteResponsavelAction.do" />	
</html:form></div>
</body>

</html:html>

