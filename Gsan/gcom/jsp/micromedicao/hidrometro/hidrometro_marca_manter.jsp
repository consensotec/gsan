<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@page import="gcom.micromedicao.hidrometro.HidrometroMarca"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>/validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script>
<!--
function facilitador(objeto){

	if (objeto.id == "0" || objeto.id == undefined){
		objeto.id = "1";
		marcarTodos();
	}
	else{
		objeto.id = "0";
		desmarcarTodos();
	}
}

function remover(objeto){

	if (CheckboxNaoVazio(objeto)){
		if (confirm ("Confirma remo��o?")) {
			document.forms[0].action = "/gsan/removerHidrometroMarcaAction.do"
			document.forms[0].submit();
		 }
	}
}
-->
</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/removerHidrometroMarcaAction" 
	name="ManutencaoHidrometroMarcaActionForm"
	type="gcom.gui.ManutencaoRegistroActionForm" method="post"
	onsubmit="return CheckboxNaoVazio(document.ManutencaoHidrometroMarcaActionForm.idRegistrosRemocao) && confirm('Confirma remo��o?')">

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp"%>

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
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Manter Marca do Hidr�metro</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				<tr>
					<td height="5" colspan="3"></td>
				</tr>
			</table>
	
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4" height="23">
						<font color="#000000" style="font-size:10px" face="Verdana, Arial, Helvetica, sans-serif">
						<strong>Marca(s) do Hidr�metro(s) cadastrado(s):</strong>
						</font>
					</td>
				</tr>
	  		</table>
			
	  		<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4" bgcolor="#000000" height="2"></td>
				</tr>
				<tr>
					<td>
						<table width="100%" bgcolor="#99CCFF">
							<!--header da tabela interna -->
							<tr bgcolor="#99CCFF">
								<td width="10%" align="center">
									<strong>
									<a onclick="this.focus();" id="0" href="javascript:facilitador(this);">Todos</a>
									</strong>
								</td>
								<td width="10%" align="center">
									<strong>Identificador</strong>
								</td>
								<td width="25%" align="center">
									<strong>Descri��o</strong>
								</td>
								<td width="15%" align="center">
									<strong>Abreviatura</strong>
								</td>
								<td width="15%" align="center">
									<strong>Validade da revis�o</strong>
								</td>
								<td width="15%" align="center">
									<strong>C�digo </strong>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table width="100%" bgcolor="#99CCFF">
						<%--Esquema de pagina��o--%>
						<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"				
							maxPageItems="10" items="${sessionScope.totalRegistros}">
							<pg:param name="pg"/>
							<pg:param name="q"/>
							<logic:present name="colecaoHidrometroMarca">
								<%int cont = 0;%>
								<logic:iterate name="colecaoHidrometroMarca" id="hidrometroMarca" type="HidrometroMarca">
									<pg:item>
										<%cont = cont + 1;
										if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">	
										<%} else {%>
										<tr bgcolor="#FFFFFF">
										<%}%>
											<td width="10%" align="center">
													<input type="checkbox" name="idRegistrosRemocao" 
													value="<bean:write name="hidrometroMarca" property="id"/>"/>
											</td>
											<td width="10%" align="center">
												<a href="/gsan/exibirAtualizarHidrometroMarcaAction.do?idHidrometroMarca=<%=""+ hidrometroMarca.getId()%>">
												<bean:write name="hidrometroMarca" property="id"/></a>											
											</td>											
											<td width="25%" align="center">
													<a href="/gsan/exibirAtualizarHidrometroMarcaAction.do?idHidrometroMarca=<%=""+ hidrometroMarca.getId()%>">
													<bean:write name="hidrometroMarca" property="descricao"/></a>
											</td>
											<td width="15%" align="center">
													<bean:write name="hidrometroMarca" property="descricaoAbreviada"/>
											</td>
											<td width="15%" align="center">
													<bean:write name="hidrometroMarca" property="intervaloDiasRevisao"/>
											</td>
											<td width="15%" align="center">
													<bean:write name="hidrometroMarca" property="codigoHidrometroMarca"/>
											</td>											
										</tr>
									</pg:item>
								</logic:iterate>
							</logic:present>
						<%-- Fim do esquema de pagina��o --%>
						</table>
						<table width="100%">
							<tr>
								<td>
									<input type="button" class="bottonRightCol" tabindex="1" value="Remover" name="removerHidrometroMarca" 
											onclick="remover(document.ManutencaoHidrometroMarcaActionForm.idRegistrosRemocao);"/>
									<input name="button" type="button" class="bottonRightCol" tabindex="2" value="Voltar Filtro"
										onclick="window.location.href='<html:rewrite page="/exibirFiltrarHidrometroMarcaAction.do"/>'">
								</td>
							</tr>
						</table>
						<table width="100%" border="0">
							<tr>
								<td align="center">
									<strong><%@ include file="/jsp/util/indice_pager_novo.jsp"%></strong>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>		
		</pg:pager>
		<p>&nbsp;</p>
		</td>
	</tr>
</table>
 <%@ include file="/jsp/util/rodape.jsp"%> 
</body>
</html:form>
</html:html>