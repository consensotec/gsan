<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="gcom.cadastro.geografico.Bairro" %>
<%@ page import="gcom.cadastro.endereco.Cep" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>	
	<%@ include file="/jsp/util/titulo.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
	<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
	
	<script language="JavaScript">
	
		function inserirMotivoFinalizacao(){
			var form = document.forms[0];
				  			     
			form.action = "/gsan/exibirConsultarDadosArquivoTextoOSCobrancaSmartphoneAction.do?acao=InformarMotivoFinalizacao";
			form.submit();
		}
		
	</script>
</head>

<html:form action="/exibirInformarMotivoFinalizacaoAction"
	name="ConsultarArquivoTextoOSCobrancaSmartphoneActionForm"
	type="gcom.gui.mobile.ConsultarArquivoTextoOSCobrancaSmartphoneActionForm"
	method="post">
	
	<table width="100%" border="0" cellspacing="5" cellpadding="0">		
		<tr>
			<td width="620" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Informar Motivo Finalização</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
				</tr>
			</table>
			
			<p>&nbsp;</p>
			
			<table width="100%" border="0">
				<tr>
					<td width="30%">
						<strong>Motivo Finalização:<font color="#FF0000">*</font></strong>
					</td>
					<td>
					    <html:textarea property="motivoFinalizacao" cols="50" rows="4"
					    onkeyup="limitTextArea(document.forms[0].motivoFinalizacao, 199, document.getElementById('utilizado'), document.getElementById('limite'));"/><br>
					    <strong><span id="utilizado">0</span>/<span id="limite">199</span></strong>
					</td>
				</tr>
				
				 <tr>
				 	<td width="30%">
					</td>
	      			<td align="right">
	      				<input name="ButtonFechar" type="button" class="bottonRightCol" 
	      						value="Finalizar" onclick="inserirMotivoFinalizacao();window.close();" style="width: 70px;">
	      			</td>			
	      		</tr>
			</table>
	   </tr>
  </table>	
</html:form>
</body>
</html:html>
