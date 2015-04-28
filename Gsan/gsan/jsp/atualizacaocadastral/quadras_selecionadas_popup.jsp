<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
</head>
<body onload="resizePageSemLink(425, 480);">
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
					
						Quadras Selecionadas
					
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

								
								<logic:present name="colecaoQuadras" scope="request">
					  				<%int cont = 0;%>
                  						<logic:iterate name="colecaoQuadras" id="idQuadra" scope="request" type="Integer">								
						  					<%cont = cont + 1;
						  					if (cont % 2 == 0) {%>
					                       	<tr bgcolor="#cbe5fe">
					                        <%} else {%>
					                        <tr bgcolor="#FFFFFF">
					                        <%}%>
					                            <td width="100%" align="center"> 
					                            	<bean:write name="idQuadra" />
					                            </td>	
											</tr>
									</logic:iterate>	
								</logic:present>								
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