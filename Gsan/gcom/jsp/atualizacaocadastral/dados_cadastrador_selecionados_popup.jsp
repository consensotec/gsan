<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="gcom.atualizacaocadastral.bean.DadosCadastradorHelper" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
</head>
<body onload="resizePageSemLink(490, 473);">
	<table width="392" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="330" valign="top" class="centercoltext">
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
					<td class="parabg">
					
						Relação de Cadastradores e Quantidade de Imóveis
					
					</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0" bordercolor="#79bbfd">
				<tr>
					<td>
						<div style="width: 100%; height: 250; overflow: auto;">
							<table width="100%" bgcolor="#99CCFF" border="0">

								
								<logic:present name="colecaoDadosCadastrador" scope="request">
					  				<%int cont = 0;%>
                  						<logic:iterate name="colecaoDadosCadastrador" id="dados" scope="request" type="DadosCadastradorHelper">								
						  					<%cont = cont + 1;
						  					if (cont % 2 == 0) {%>
					                       	<tr bgcolor="#cbe5fe">
					                        <%} else {%>
					                        <tr bgcolor="#FFFFFF">
					                        <%}%>
				                        	    <td width="90%" align="left"> 
					                            	<bean:write name="dados" property="cpf" />&nbsp;&nbsp;<bean:write name="dados" property="nome" />
					                            </td>
						                        
						                        <td width="20%" align="center"> 
					                            	<bean:write name="dados" property="quantidadeImoveis" />
					                            </td>					                            	
											</tr>									
									</logic:iterate>	
								</logic:present>
								<tr align="right">
								<td><strong>Total: </strong></td>
								<td align="center"><%= session.getAttribute("total")%></td>
							</tr>									
							</table>
						</div>
					</td>
				</tr>				
							
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td  align="right">
						<input type="button" name="Button3"
						class="bottonRightCol" value="Fechar" tabindex="1"
						onClick="javascript:window.close()"/>
					</td>
				</tr>
			</table>
		<p>&nbsp;</p>
	</table>
</body>

</html:html>