<%@ page import = "java.io.*" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ page import="gcom.util.Util"%>
<%@ page import="java.math.BigDecimal"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
</head>

<body leftmargin="0" topmargin="0" >
	<table width="100%" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="100%" valign="top" class="centercoltext">

				<table height="100%">
					<tr>
						<td></td>
					</tr>
				</table>
	
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
						</td>
						<td class="parabg">Consultar foto ordem de serviço</td>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
						</td>
					</tr>
				</table>
			
				<p>&nbsp;</p>
				<table width="100%" border="0">

            	<tr>
                	<td height="31"  colspan="2">
                    <table width="100%" border="0" align="center">

                        <tr bgcolor="#cbe5fe">

                      		<td align="center">
                      		<table width="100%" border="0">
								<tr bgcolor="#cbe5fe">
									<td>
									<table border="0" width="100%">
	                              		<tr>
               								<td>&nbsp;</td>
               							</tr>
	                              		<tr>
	                              			<td colspan="2" width="100%" >
	                              				<hr />
	                              			</td>
	                              		</tr>
                              		</table>
                             		</td>
                           		</tr>
                       		</table>
                       		</td>
                  		</tr>
                  		<tr>
                  			<td>
                  				
                 				<table border="0" style="float: left;">
                 				<tr> 
                 					<td> 
                 						<img width="220px" height="170px" 
  								 			src="/gsan/jsp/atendimentopublico/ordemservico/fiscalizacao_foto.jsp?idArquivo=<%= request.getAttribute("idArquivo")%>"/>
													
 								 	<td>
 							 	</tr>
							 		 
 								 </table> 
                   				 
                  			</td>
                  		</tr>
                    	</table>
                   	</td>
               	</tr>
             	<tr>
               		<td>&nbsp;</td>
               	</tr>
               	<tr>
               		<td colspan="2" width="100%" >
               			<hr />
               		</td>
               	</tr>
               	<tr>
					<td width="100%">
						<strong> <font color="#FF0000"> <input
								type="button" name="fechar" class="bottonRightCol"  id="fechar"
								value="Fechar"
								style="float: right;"
								onclick="window.close();"> </font> </strong>
					</td>
				</tr>
           	</table>
			</td>
		</tr>		
	</table>
	
</body>
</html:html>