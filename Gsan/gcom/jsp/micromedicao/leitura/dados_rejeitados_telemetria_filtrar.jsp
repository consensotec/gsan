<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<html:javascript staticJavascript="false"  formName="FiltrarDadosRejeitadosTelemetriaActionForm" dynamicJavascript="false" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script>

function replicaData() {
	var form = document.forms[0];
	form.periodoEnvioDadosFinal.value = form.periodoEnvioDadosInicial.value;
}
 
function chamarFiltrar() {
	var form = document.forms[0];
	
	if (document.forms[0].periodoEnvioDadosInicial.value != '' && document.forms[0].periodoEnvioDadosFinal.value == '') {
		 document.forms[0].periodoEnvioDadosFinal.value = document.forms[0].periodoEnvioDadosInicial.value;
	}
	if(validaData(form.periodoEnvioDadosInicial)!=false && validaData(form.periodoEnvioDadosFinal)!=false){
		if(document.forms[0].periodoEnvioDadosInicial.value == '') {
			alert("Campo obrigatório não preenchido")
		}else if(comparaData(document.forms[0].periodoEnvioDadosInicial.value, '>', document.forms[0].periodoEnvioDadosFinal.value) ){
			alert("O Inicio da data do Período de Envio dos Dados é posterior a Data Final.")
		}else{
			document.forms[0].submit();
		}
	}
}



function dataLimpa(){
	var dataInicial = document.forms[0].periodoEnvioDadosInicial.value;
	var dataFinal = document.forms[0].periodoEnvioDadosFinal.value;
	if (dataInicial == '' || dataFinal == ''){
	    document.forms[0].dataLimpaHidden.value = '1';
	} else {
   	document.forms[0].dataLimpaHidden.value = '0';
	}
}


 function limparFiltrar(){
	document.forms[0].periodoEnvioDadosInicial.value = '';
	document.forms[0].periodoEnvioDadosFinal.value = '';
	document.forms[0].motivoRejeicao.value= '-1'; 
	document.forms[0].indicadorEnvioDadosTotalmenteRejeitados.value= '1'; 
		
   limparFuncionalidade();
 }

 function salvarNomeOperacaoUnica(){
   
   var oper = document.forms[0].operacoes;
   if (oper != null && oper.selectedIndex != -1){
     document.forms[0].nomeOperacaoSelecionada.value = oper.options[oper.selectedIndex].text;
   }
 
 }
 
  </script>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/filtrarDadosRejeitadosTelemetriaAction" 
method="post" name="FiltrarDadosRejeitadosTelemetriaActionForm" 
type="gcom.gui.micromedicado.leitura.FiltrarDadosRejeitadosTelemetriaActionForm">

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

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
      </div></td>
    <td width="625" valign="top" class="centercoltext">
      <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Filtrar Dados Rejeitados Telemetria </td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
            <table border="0" width="100%">
                <tbody><tr> 
                  <td colspan="2">Para filtrar dados rejeitados da telemetria, informe os dados abaixo:</td>

                </tr>

                 <tr> 
	                  <td>
	                  	<strong>Período de Envio dos Dados:<font color="#FF0000">*</font></strong>
	                  </td>
                  <td> 
                    <input type="hidden" name="dataLimpaHidden" value="0">
                  	<html:text name="FiltrarDadosRejeitadosTelemetriaActionForm" onkeyup="mascaraData(this, event);replicaData();"
                  	 onblur="javascript:dataLimpa()"  property="periodoEnvioDadosInicial" size="10" maxlength="10" onkeypress="javascript:return isCampoNumerico(event);"/> 
						<a href="javascript:abrirCalendarioReplicando('FiltrarDadosRejeitadosTelemetriaActionForm', 'periodoEnvioDadosInicial', 'periodoEnvioDadosFinal')"><img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif"
							width="20" border="0" align="middle" alt="Exibir Calendário" /></a>
                    <strong>a</strong> 
                    <html:text name="FiltrarDadosRejeitadosTelemetriaActionForm" onkeyup="mascaraData(this, event);" property="periodoEnvioDadosFinal" size="10" maxlength="10" 
                     onkeypress="javascript:return isCampoNumerico(event);"/> 
						<a href="javascript:abrirCalendario('FiltrarDadosRejeitadosTelemetriaActionForm', 'periodoEnvioDadosFinal')"><img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif"
							width="20" border="0" align="middle" alt="Exibir Calendário" /></a> dd/mm/aaaa</td>

                </tr>
                
                <tr>
					<td width="48%"><strong>Apenas Envio de Dados Totalmente Rejeitados:</strong><font color="#FF0000">*</font></td>
					<td width="52%" colspan="2">
						<html:radio property="indicadorEnvioDadosTotalmenteRejeitados" value="2" tabindex="12" /><strong>Sim</strong>
						<html:radio property="indicadorEnvioDadosTotalmenteRejeitados" value="1" tabindex="13" /><strong>N&atilde;o</strong>
					</td>
				</tr>
                
                <tr> 
                  <td width="40%"><strong>Motivo da rejeição:</strong></td>
                  <td width="60%">
					<html:select property="motivoRejeicao" tabindex="4" style="width:267px;" >
 						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoMotivoRejeicao" labelProperty="descricaoRetorno" property="id" />
					</html:select>
                  </td>
                </tr>
                <tr>
					<td>&nbsp;</td>
					<td align="left" colspan="2"><font color="#FF0000">*</font> Campo Obrigatório</td>
				</tr>
				<tr>
				  <td colspan="2">&nbsp;</td>
				</tr>
                <tr> 
                  <td class="style1"><input type="button" class="bottonRightCol" value="Limpar" onclick="javascript:limparFiltrar();"/>
                  <input type="button" name="ButtonCancelar" class="bottonRightCol" value="Cancelar" onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
                  <p>&nbsp;</p>

                  </td>
                  <td class="style1"> <table>
                      <tbody><tr> 
                        <td align="right" width="500"><input type="button" class="bottonRightCol" value="Filtrar" onClick="chamarFiltrar();"/></td>
                        <td>&nbsp;</td>
                      </tr>
                    </tbody></table></td>
                </tr>
            </tbody></table>
      <p>&nbsp;</p>
    </td>
  </tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>
