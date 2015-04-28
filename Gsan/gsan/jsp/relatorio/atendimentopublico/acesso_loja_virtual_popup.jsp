<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ page import="java.util.Collection,gsan.util.ConstantesSistema"%>
<%@page import="gsan.relatorio.atendimentopublico.bean.AcessoLojaVirtualHelper"  isELIgnored="false"%>

<html:html>
<head>
<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:resizePageSemLink(806, 500)">
<table width="770" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="770" valign="top" class="centercoltext">
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
				<td class="parabg">Acesso Detalhado - ${requestScope.tipoAtendimento}</td>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>
		<p>&nbsp;</p>
		<table width="100%" border="0" bgcolor="#90c7fc">
			<tr align="left" bgcolor="#90c7fc">
				<td width="40%" align="center"><strong>Data Atendimento</strong></td>
				<td width="40%" align="center"><strong>IP Acesso</strong></td>
				<td width="40%" align="center"><strong>Serviços Executados</strong></td>
			</tr>
			<%--Esquema de paginação--%>
			<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
				export="currentPageNumber=pageNumber;pageOffset"
				maxPageItems="10" items="${sessionScope.totalRegistros}">
				
				
				<pg:param name="q" />

					<%int cont = 0;%>
			        <logic:iterate name="colecaoHelperPopup" id="helper" scope="request" type="AcessoLojaVirtualHelper">
					<pg:item>
							<%cont = cont + 1;
							if (cont % 2 == 0) {%>
								<tr bgcolor="#cbe5fe">								
							<%} else {	%>
								<tr bgcolor="#FFFFFF">
							<%}%>
				
							<td width="40%" align="center">
								<bean:write name="helper" property="dataAtendimento" />
							</td>

							<td width="40%" align="center">
								<bean:write name="helper" property="ipAcesso" />
							</td>
							
							<logic:equal name="helper" property="indicadorServicoExecutado" value="1">
								<td width="20%" align="center">SIM</td>
							</logic:equal>
							<logic:notEqual name="helper" property="indicadorServicoExecutado" value="1">
								<td width="20%" align="center">NÃO</td>	
							</logic:notEqual>							
						</tr>
					</pg:item>
				</logic:iterate>
		</table>
		<table width="100%" border="0">
			<tr>
				<td>
				<div align="center"><strong><%@ include
					file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
				</td>
				</pg:pager>
				<%-- Fim do esquema de paginação --%>
			</tr>
			<tr>
				<td height="24"><input type="button" class="bottonRightCol"
					value="Fechar" onClick="javascript:window.close();" />
				</td>
			</tr>
		</table>
</table>
</body>
</html:html>
