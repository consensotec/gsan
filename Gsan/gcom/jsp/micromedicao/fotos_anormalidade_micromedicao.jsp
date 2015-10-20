<%@page import="gcom.micromedicao.MovimentoRoteiroEmpresaFoto"%>
<%@page import="gcom.micromedicao.bean.ArquivoTextoRoteiroEmpresaFotoHelper"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>


<%@page import="java.util.Collection" %>
<%@page import="java.util.Iterator" %>
<%@ page import="gcom.util.Util"%>
<%@ page import="java.math.BigDecimal"%>

<%
	Collection<MovimentoRoteiroEmpresaFoto> collecionFotos = (Collection<MovimentoRoteiroEmpresaFoto>) request.getAttribute("fotos");
	

%>

	<script type="text/javascript">



</script>

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
						<td class="parabg">Consultar foto de anormalidade</td>
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
		                              		<td height="10" width="33%"><strong>Matricula: </strong></td>		                              		
		                              		<td><input type="text" readonly="readonly" value="<%= request.getAttribute("idImovel") %>" /></td>
	                              		</tr>
	                              		<tr>
	                              			<td height="10" width="33%"><strong>Mês referência:  </strong></td>
	                              			<td><input type="text" readonly="readonly" value="<%= request.getAttribute("anoMes") %>" /></td>
	                              		</tr>
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
                  				
                  				<% 
                  				for (Iterator<MovimentoRoteiroEmpresaFoto> iter = collecionFotos.iterator(); iter.hasNext();) {
                  					MovimentoRoteiroEmpresaFoto foto = iter.next();
                				%>

                				<table border="0" style="float: left;">
                				<tr>
                					<td>
                						<img width="220px" height="170px" 
								 		src="/gsan/jsp/micromedicao/micromedicao_foto.jsp?idFoto=<%=foto.getId() %>"/>
								 	<td>
							 	</tr>
							 	
							 	<tr>
<!-- 								 	<td> -->
<!-- 										<textarea rows="5" cols="25" readonly="readonly" style="min-height: 40px;min-width: 220px; max-width: 220px; max-height: 60px;">"Matricula:"</textarea>							 			 -->
<!-- 								 	</td> -->
								 </tr>
								 
							
								 </table>  
                				<%
                  				}
                  				%>
                  				                				 
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