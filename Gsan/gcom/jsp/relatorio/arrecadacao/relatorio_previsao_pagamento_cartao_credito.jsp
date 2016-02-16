<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@page import="gcom.util.ConstantesSistema"%>
<%@ include file="/jsp/util/telaespera.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>

<head>
	<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
	<html:javascript staticJavascript="false"  formName="GerarRelatorioPrevisaoPagamentoCartaoCreditoActionForm" />	
	<%@ include file="/jsp/util/titulo.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">	
	<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>	
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>

<script language="JavaScript">

function validarForm(){
	
	var form = document.forms[0];
	if(validateGerarRelatorioPrevisaoPagamentoCartaoCreditoActionForm(form)){
		toggleBox('demodiv',1);
	}
}

function required() {

}
	
	function validaTodosPeriodos() {
		var form = document.forms[0];
		
		if (validaPeriodoEncerramento(form)) {
    		if (comparaData(form.dataVencimentoInicial.value, '>', form.dataVencimentoFinal.value)){
				alert('Data Final do Período de Encerramento é anterior à Data Inicial');
				return false;
			}
		} else {
			return false;
		} 

		return true;
    }	
	
	function validaPeriodoEncerramento(form) {
    	var form = document.forms[0];
    	
    	var periodoEncerramentoInicial = trim(form.dataVencimentoInicial.value);
 	   	var periodoEncerramentoFinal = trim(form.dataVencimentoFinal.value);
    	
    	if ((periodoEncerramentoInicial != null && periodoEncerramentoInicial != '') &&
    	((periodoEncerramentoFinal == null || periodoEncerramentoFinal == ''))) {
    		alert('Informe Data Final Período de Encerramento');
       		return false;
    	} else if ((periodoEncerramentoFinal != null && periodoEncerramentoFinal != '') &&
    	((periodoEncerramentoInicial == null || periodoEncerramentoInicial == ''))) {
    		alert('Informe Data Inicial Período de Encerramento');
    		return false;
    	}
    	
    	return true;
    }	
	
</script>

<body leftmargin="5" topmargin="5">              
    <div id="formDiv">          
	<html:form action="/gerarRelatorioPrevisaoPagamentoCartaoCreditoAction.do"
	           name="GerarRelatorioPrevisaoPagamentoCartaoCreditoActionForm"	                                  
	           type="gcom.gui.relatorio.arrecadacao.GerarRelatorioPrevisaoPagamentoCartaoCreditoActionForm"
	           method="post">
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="115" valign="top" class="leftcoltext">
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
			<td width="600" valign="top" bgcolor="#003399" class="centercoltext">
				<table height="100%">
					<tr>
						<td></td>
					</tr>
				</table>
				
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="11">
							<img border="0" src="imagens/parahead_left.gif" />
						</td>
						<td class="parabg">
							Relat&oacute;rio de Previsão/Pagamento de Cartão de Crédito 
						</td>
						<td width="11" valign="top">
							<img border="0"	src="imagens/parahead_right.gif" />
						</td>
					</tr>
				</table>
				
				<table  width="100%" border="0" align="center" cellpadding="0" cellspacing="3">
					<tr>
					   <td colspan="3">Para gerar o relat&oacute;rio de previs&atilde;o/pagamento, informe os dados abaixo:</td>
					</tr>
					<tr>
						<td width="25%">
							<strong>Data de Vencimento: </strong><font color="#FF0000">*</font>
						</td>
						<td width="75%">
						    <html:text property="dataVencimentoInicial"
										size="8" maxlength="10" onkeypress="return isCampoNumerico(event);"
										onkeyup="mascaraData(this,event);replicarCampo(document.forms[0].dataFinal,this);" />
							<a href="javascript:abrirCalendarioReplicando('GerarRelatorioPrevisaoPagamentoCartaoCreditoActionForm', 'dataVencimentoInicial', 'dataVencimentoFinal')">
							<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif"
								width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
							
							<strong> a </strong> 
							
							<html:text property="dataVencimentoFinal" size="8"
									   maxlength="10" onkeyup="mascaraData(this,event)" onkeypress="return isCampoNumerico(event);" /> 
							<a 	href="javascript:abrirCalendario('GerarRelatorioPrevisaoPagamentoCartaoCreditoActionForm', 'dataVencimentoFinal')">
						    <img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif"
								 width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
						    (dd/mm/aaaa)
						</td>
					</tr>
					<tr> 
						<td><strong>Cliente Arrecadador:</strong><font color="#FF0000">*</font></td>
						<td>
							<strong> 
								 <html:select property="idCliente" style="width: 230px;">
									<logic:present name="arrecadador" scope="session">
										<html:option value="">&nbsp;</html:option>
										<html:options collection="arrecadador" labelProperty="descricao" property="id" />
									</logic:present>
								</html:select> 
							</strong>
						</td>
			        </tr>
			        <tr>
						<td width="25%">
						  	<strong>
						  		Tipo de Relat&oacute;rio:<FONT color=#ff0000>*</FONT>
						  	</strong>
						</td>
						<td width="75%">
						  	<html:radio property="tipoDeRelatorio" value="analitico" />Anal&iacute;tico
						  	<html:radio property="tipoDeRelatorio" value="sintetico" />Sint&eacute;tico
						</td>
				    </tr>
				    <tr>
						<td>&nbsp;</td>
						<td colspan="2" align="left"><font color="#FF0000">*</font> Campo
							Obrigat&oacute;rio</td>
 				    </tr>
					<tr>						
						<td>
						  <font color="#FF0000"> 
						    <input type="button" name="ButtonCancelar" 
						           class="bottonRightCol" value="Cancelar"
						           onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
						  </font>
						  <input name="Button" type="button"
								class="bottonRightCol" value="Limpar" align="left"
								onclick="window.location.href='/gsan/exibirGerarRelatorioPrevisaoPagamentoCartaoCreditoAction.do?menu=sim';">
						</td>	
						
						
						<td align="right">
							<input type="button" 
								   name="Button" 
								   class="bottonRightCol" 
								   value="Gerar" 
								   onClick="javascript:validarForm()" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
		
    <div id="divAux" style="position:absolute; left:11px; top:50px;">
	  <jsp:include page="/jsp/relatorio/escolher_tipo_relatorio_tela_espera.jsp?relatorio=gerarRelatorioPrevisaoPagamentoCartaoCreditoAction.do"/>
	</div>
	
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</div>
</body>
</html:html>