<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ include file="/jsp/util/telaespera.jsp"%>

<%@ page import="gsan.util.Pagina, gsan.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<html:javascript staticJavascript="false"  formName="GerarResumoDevedoresDuvidososActionForm" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
<!-- Begin

    
function gerarResumo(){

	var form = document.forms[0];

	var situacao = true;
  	
	  if( form.idTipoPerda.value == "2" ) {
		 situacao =  validaTipoPerdaSocietaria()	;	  
      } 

	  if( form.idTipoPerda.value == "3" ) {
			 situacao =  validaTipoPerdaOrgaoPublico()	;
	  } 

	  if ( form.idTipoPerda.value != "" ) {
		 
	      if (validateGerarResumoDevedoresDuvidososActionForm(form) && situacao ) {
	    	  botaoAvancarTelaEspera('/gsan/gerarResumoDevedoresDuvidososAction.do');
		  }
	  } else {
		alert("Tipo de Perda é obrigatorio.");
	  }
}

function validaTipoPerdas() {
	  var form = document.forms[0];

	  if ( form.idTipoPerda.value != null && form.idTipoPerda.value != "" && verificaAnoMes(form.anoMesReferenciaContabil) ) {

		  form.action = "exibirGerarResumoDevedoresDuvidososAction.do?idTipoPerda=" + form.idTipoPerda.value;
      	  form.submit();
	  } else if (verificaAnoMes(form.anoMesReferenciaContabil)) { 
		  form.action = "exibirGerarResumoDevedoresDuvidososAction.do?idTipoPerda=desfazer" ;
      	  form.submit();

	  }
		  	
}

function habilitaDesabitaTipoPerda(){
	  var form = document.forms[0];

	  if ( form.anoMesReferenciaContabil.value == null || form.anoMesReferenciaContabil.value == "" ) {
		  form.idTipoPerda.value =  "";
	      form.idTipoPerda.disabled = true;
		  
	  }	else if ( verificaAnoMesSemAlert(form.anoMesReferenciaContabil) ){

		  form.idTipoPerda.disabled = false;
	  }
}


//Valida um campo do tipo Ano mês           
function verificaAnoMesSemAlert(mydata) {

	var form = document.forms[0];
	
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

	   mydata.focus(); 
	}
	
	return situacao;
}

function atualizaValoresDefault() {

	var form = document.forms[0];

	if ( form.idTipoPerda.value == "2" ) { 

		form.indicadorCategoriaResidencial.checked = true;
		form.indicadorCategoriaComercial.checked = true;
		form.indicadorCategoriaIndustrial.checked = true;
		form.indicadorEsferaParticular.checked = true;
		form.indicadorEsferaMunicipal.checked = true;
		form.indicadorEsferaEstadual.checked = true;
		form.indicadorEsferaFederal.checked = true;

	} else if ( form.idTipoPerda.value == "3" ) {

		form.indicadorEsferaPoderMunicipal.checked = true;
		form.indicadorEsferaPoderFederal.checked = true;
	}
}

function validaTipoPerdaSocietaria() {
	var form = document.forms[0];
	var situacao = true;
	
	if ( !validaPeriodoReferenciaParaBaixa() ) {
		situacao = false;

	} else if ( !validarQuantidadeMesesPerdasSocietarias() && situacao ) {
		situacao = false;

	} else if ( !validaEsferaPoderPerdasSocietarias() && situacao ) {
		situacao = false;

	} else if ( !validaCategoriaPerdasSocietarias() && situacao ) {
		situacao = false;
	}
	
	return situacao;
}

function validaTipoPerdaOrgaoPublico() {
	var situacao = true;

	if ( !validaEsferaOrgaoPublico() ) {
		situacao = false;
	}

	if ( !validarQuantidadeMesesOrgaoPublico() && situacao ) {
		situacao = false;
	}

	return situacao	
}

function validaEsferaOrgaoPublico() {
	var form = document.forms[0];
	var situacao = true;
	if ( form.indicadorEsferaPoderMunicipal.checked == false  &&  form.indicadorEsferaPoderFederal.checked == false ) {
		situacao = false;
		alert("Selecione pelo menos uma esfera de poder (MUNICIPAL/FEDERAL).")
	}
	return situacao;
}

function validarQuantidadeMesesPerdasSocietarias(){
	var form = document.forms[0];

	var situacao = true;

	if ( form.numeroMesesContasInferiores != null && form.numeroMesesContasInferiores.value != null && form.numeroMesesContasInferiores.value != "" ) {

		var numeroMeses = parseInt(form.numeroMesesContasInferiores.value);
		if ( numeroMeses <= 0 || numeroMeses > 120 ) {
			situacao = false;
			alert ("Numero de Meses deve ser superior a 0 e inferior a 120.")
		}
	} else {
			situacao = false;
			alert("Informe a quantidade de meses que a conta está vencida para efetuar a baixa.")
	}
	return situacao;	
}
function validarQuantidadeMesesOrgaoPublico(){
	var form = document.forms[0];

	var situacao = true;

	if ( form.numeroMesesContasVencidas != null && form.numeroMesesContasVencidas.value != null && form.numeroMesesContasVencidas.value != "" ) {

		var numeroMeses = parseInt(form.numeroMesesContasVencidas.value);
		if ( numeroMeses < 60 ) {
			situacao = false;
			alert ("Numero de Meses deve ser superior a 59.")
		}
	} else {
			situacao = false;
			alert("Informe a quantidade de meses que a conta está vencida para efetuar a baixa.")
	}
	return situacao;	
}

function validaPeriodoReferenciaParaBaixa(){
	var form = document.forms[0];

	var situacao = true;

	if (form.periodoBaixaInicial != null && ( form.periodoBaixaInicial.value == null || form.periodoBaixaInicial.value == "") ) {
		situacao = false;
		alert("Período de Referência para Baixa Inicial Obrigatorio.")
	} else if (form.periodoBaixaFinal != null && ( form.periodoBaixaFinal.value == null || form.periodoBaixaFinal.value == "") ) {
		situacao = false;
		alert("Período de Referência para Baixa Final Obrigatorio.")
	} else if ( !verificaAnoMesSemAlert(form.periodoBaixaInicial) ) {
        situacao = false;
		alert("Período de Referência para Baixa Inicial Invalido.")
	} else if ( !verificaAnoMesSemAlert(form.periodoBaixaFinal)  ) {
		situacao = false;
		alert("Período de Referência para Baixa Final Invalido.")
	} else if ( comparaMesAno(form.periodoBaixaInicial.value , ">" , form.periodoBaixaFinal.value )) {
		situacao = false;
		alert("O ano/mês do período final não pode ser inferior ao ano/mês do período inicial.")
	} else if (comparaMesAno(form.periodoBaixaInicial.value , ">" , form.mesAnoAtual.value )){
		situacao = false;
		alert("O ano/mês do período inicial deverá ser menor ou igual ao ano/mês corrente.")
	} else if (comparaMesAno(form.periodoBaixaFinal.value , ">" , form.mesAnoAtual.value )){
		situacao = false;
		alert("O ano/mês do período final deverá ser menor ou igual ao ano/mês corrente.")
	} else if (comparaMesAno(form.periodoBaixaFinal.value , ">" , form.anoMesReferenciaContabil.value )){
		situacao = false;
		alert("O ano/mês do período final deverá ser menor ou igual ao ano/mês da referência contabil informada.")
	} else if ( verificaDatasPertecemAoMesmoAno(form.periodoBaixaInicial.value, form.periodoBaixaFinal.value) == true ) {
		situacao = false;
		alert("O ano do período final deve ser igual ao ano do período inicial.")
	}
	
	return situacao;	
}

function replicaPeriodoBaixa() {
	var form = document.forms[0];

	form.periodoBaixaFinal.value = form.periodoBaixaInicial.value;
	
}

function validaCategoriaPerdasSocietarias(){
	var form = document.forms[0];

	var situacao = true;

	if ( form.indicadorCategoriaResidencial.checked == false  && form.indicadorCategoriaComercial.checked == false  && 
			form.indicadorCategoriaPublica.checked == false && form.indicadorCategoriaIndustrial.checked == false ) {

		situacao = false;
		alert("Selecione pelo menos uma categoria.")
	}
	return situacao;
}

function validaEsferaPoderPerdasSocietarias() {
	var form = document.forms[0];

	var situacao = true;

	if ( form.indicadorEsferaParticular.checked == false && form.indicadorEsferaMunicipal.checked == false &&
			form.indicadorEsferaEstadual.checked == false && form.indicadorEsferaFederal.checked == false ) {

		situacao = false;
		alert("Selecione pelo menos uma esfera do poder.")
	}
	return situacao;
} 

function verificaDatasPertecemAoMesmoAno(dataInicial, dataFinal){
		
	var situacao = false;
	
	while (comparaData(dataInicial, "<", dataFinal)){
			
		var diaInicial = dataInicial.substr(0,2);
        var mesInicial = dataInicial.substr(3,2);
		var anoInicial = dataInicial.substr(6,4);
	
		var diaFinal = dataFinal.substr(0,2);
        var mesFinal = dataFinal.substr(3,2);
		var anoFinal = dataFinal.substr(6,4);
	
		anoInicial++;
		dataInicial = diaInicial + "/" + mesInicial + "/" + anoInicial;
	
		if (parseInt(anoInicial) == parseInt(anoFinal)){
				
			if (parseInt(mesInicial) < parseInt(mesFinal) ||
				( parseInt(mesInicial) == parseInt(mesFinal) && parseInt(diaInicial) <= parseInt(diaFinal) ) ){
					
				situacao = true;
			}
	
			break;
		}
	}
		
	return situacao;
}



-->
</script>
</head>

<body leftmargin="5" topmargin="5" onload="habilitaDesabitaTipoPerda();atualizaValoresDefault();">
<div id="formDiv"><html:form
  action="/gerarResumoDevedoresDuvidososAction.do"
  method="post"
  onsubmit="return validateGerarResumoDevedoresDuvidososActionForm(this);"
  type="gsan.gui.financeiro.GerarResumoDevedoresDuvidososActionForm"
>

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<html:hidden property="mesAnoAtual" />


<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="130" valign="top" class="leftcoltext">
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
      
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Gerar Resumo dos Devedores Duvidosos</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      
      <p>&nbsp;</p>
	  
	  <table width="100%" >
        <tr>
          <td width="100%" colspan=2>
	       	<table>
		      <tr>
		        <td width="505px">Para gerar o resumo dos devedores duvidosos, informe os dados abaixo:</td>
		      </tr>
		    </table>
          </td>
        </tr>
        
        <tr>
          <td align="left" colspan="1"><strong>Referência Contábil:</strong><font color="#FF0000">*</font></td>
          <td align="left" colspan="1">
            <div align="left">
          	  <html:text property="anoMesReferenciaContabil" size="7"  
          	  			 maxlength="7" 
          	  			 tabindex="1" 
          	  			 onkeypress="javascript:mascaraAnoMes(this, event);" 
          	  			 onkeyup="habilitaDesabitaTipoPerda();"
          	  			 onblur="habilitaDesabitaTipoPerda();"/>&nbsp;mm/aaaa
          	</div>
          </td>
        </tr>
        <tr> 
           <td align="left" colspan="1">	
           		<strong>Tipo de Perda:<font color="#FF0000">*</font></strong>
           </td>
           <td colspan="1">
	           <div align="left" >
	               <html:select property="idTipoPerda" tabindex="5" style="width: 300px;"  onchange="validaTipoPerdas();">
						<html:option value="" >&nbsp;</html:option>
						<html:options collection="colecaoPerdasTipo" labelProperty="descricao" property="id" />
				   </html:select>
			   </div>
           </td>
         </tr>
         
         <logic:present name="baixaPerdaSocietaria">
         	
         	<tr> 
                <td height="24" colspan="2" bordercolor="#000000" class="style1"><hr></td>
            </tr>
           
            <tr>
	         	<td colspan="7">	
	           		<strong>Critérios para Baixa por Perda Societária:</strong>
	           </td>
           </tr>
             <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
            <tr> 
                <td colspan="1" align="left"><strong>Per&iacute;odo de Referência para Baixa:</strong><font color="#FF0000">*</font></td>
                <td colspan="1" align="left"><span class="style2"><strong> 
						<html:text property="periodoBaixaInicial" 
									size="8" 
									maxlength="7" 
									tabindex="2" 
									onkeypress="return isCampoNumerico(event);"
									onkeyup="mascaraAnoMes(this, event);replicaPeriodoBaixa();"
									/>
						a <html:text property="periodoBaixaFinal" 
									size="8" 
									maxlength="7" 
									tabindex="3" 
									onkeypress="return isCampoNumerico(event);"
									onkeyup="mascaraAnoMes(this, event)"/>
						</strong>(mm/aaaa)<strong> 
                  </strong></span></td>
              </tr>
              
              <tr>
		          <td align="left" ><strong>Imóveis que Tenham Contas com Baixa Contábil com Referências Inferiores a:</strong><font color="#FF0000">*</font></td>
		          <td align="left">
		          	  <html:text property="numeroMesesContasInferiores" size="4"  maxlength="3" tabindex="4"/>
		          	&nbsp; 
		          	<strong>Meses</strong>
		          </td>
		      </tr>
		      
		      <tr>
		          <td align="left" ><strong>Categoria:</strong><font color="#FF0000">*</font></td>
			      <td width="100" align="left" colspan="2">
			      	<html:checkbox property="indicadorCategoriaResidencial" value="1" tabindex="5"/><strong>Residencial</strong>
			      &nbsp;
				  	<html:checkbox property="indicadorCategoriaComercial" value="1" tabindex="6"/><strong>Comercial</strong>
				  &nbsp;
				  	<html:checkbox property="indicadorCategoriaIndustrial" value="1" tabindex="7"/><strong>Industrial</strong>
				  &nbsp;
				  	<html:checkbox property="indicadorCategoriaPublica" value="1" tabindex="8"/><strong>Pública</strong>
				  </td>    
		      </tr>
		      
		      <tr>
		          <td align="left" ><strong>Esfera de Poder:</strong><font color="#FF0000">*</font></td>
			      <td width="100" align="left" colspan="2">
			      	<html:checkbox property="indicadorEsferaParticular" value="1" tabindex="9"/><strong>Particular</strong>
			      &nbsp;
				  	<html:checkbox property="indicadorEsferaMunicipal" value="1" tabindex="10"/><strong>Municipal</strong>
				  &nbsp;
				  	<html:checkbox property="indicadorEsferaEstadual" value="1" tabindex="11"/><strong>Estadual</strong>
				  &nbsp;
				  	<html:checkbox property="indicadorEsferaFederal" value="1" tabindex="12"/><strong>Federal</strong>
				  </td>    
		      </tr>
         
         	  <tr>
					<td  ><strong>Tipo de Geração:</strong><font color="#FF0000">*</font></td>
					<td>
						<html:radio property="indicadorTipoGeracao" tabindex="13" value="1" /><strong>Real</strong>
						<html:radio property="indicadorTipoGeracao" tabindex="14" value="2" /><strong>Simulação</strong>
					</td>
			  </tr>
			  
         </logic:present>
         
         <logic:present name="baixaPerdaOrgaoPublico">
         
         	<tr> 
                <td height="24" colspan="2" bordercolor="#000000" class="style1"><hr></td>
            </tr>
            
         	<tr>
	         	<td colspan="7">	
	           		<strong>Critérios para Baixa por Perda de Orgão Público:</strong>
	           </td>
           </tr>
              
           <tr>
          	<td>&nbsp;</td>
          	<td>&nbsp;</td>
           </tr>
           
           <tr>
		       <td align="left" ><strong>Quantidade Minima de Meses Vencidos para as Contas:</strong><font color="#FF0000">*</font></td>
		       <td align="left">
		          	  <html:text property="numeroMesesContasVencidas" size="6"  maxlength="6" />
		           &nbsp; 
		       	<strong>Meses</strong>
		       </td>
		   </tr>           
           
           <tr>
		        <td align="left" colspan="1"><strong>Esfera de Poder:</strong></td>
				<td align="left">
				  	<html:checkbox property="indicadorEsferaPoderMunicipal" value="1" tabindex="1"/><strong>Municipal</strong>
			    &nbsp; 
				  	<html:checkbox property="indicadorEsferaPoderFederal" value="1" tabindex="1"/><strong>Federal</strong>
				</td>
		   </tr>
         
         </logic:present>
      	<tr>
			<td>&nbsp;</td>
			<td colspan="2" align="left"><font color="#FF0000">*</font> Campo
			Obrigat&oacute;rio</td>
		</tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        
        <tr>
          <td colspan="2" class="style1">
			<input type="button" name="Button" class="bottonRightCol" value="Desfazer" onclick="window.location.href='<html:rewrite page="/exibirGerarResumoDevedoresDuvidososAction.do?menu=sim"/>'">
			<input name="Submit23" class="bottonRightCol" value="Cancelar" type="button" onclick="window.location.href='/gsan/telaPrincipal.do'">
          </td>
          
          <td>
            <div align="right">
              <gsan:controleAcessoBotao name="Button" value="Gerar" onclick="gerarResumo();" url="gerarResumoDevedoresDuvidososAction.do"/>
            </div>
          </td>
        </tr>
        
      </table>
      <p>&nbsp;</p>
	</td>
  </tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</div>
</body>
</html:html>