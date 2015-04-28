<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="gsan.cadastro.imovel.Imovel"%>
<%@ page import="gsan.cadastro.cliente.ClienteImovel"%>
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>/validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script>
//<!--
	function facilitador(objeto) {
		if (objeto.value == "0") {
			objeto.value = "1";
			marcarTodos();
		} else {
			objeto.value = "0";
			desmarcarTodos();
		}
	}

	function remover() {
		if (CheckboxNaoVazio(document.ManutencaoRegistroActionForm.idRegistrosRemocao)
				&& confirm('Confirma remoção ?')) {
			var form = document.forms[0];
			form.action = "removerImovelAction.do";
			form.submit();
		}
	}

	function enviarFormFiltro(idImovel, mesAno,idTipoLigacao) {
		var form = document.forms[0];

		form.codigoImovel.value = idImovel;
		form.mesAno.value = mesAno;
		form.idTipoLigacao.value = idTipoLigacao;

		form.submit();
	}
	
//-->
</script>
</head>

<body leftmargin="5" topmargin="5">
	<html:form action="/exibirConsultarHistoricoMedicaoIndividualizadaAction"
		name="ConsultarHistoricoMedicaoIndividualizadaActionForm" method="post"
		type="gsan.gui.micromedicao.ConsultarHistoricoMedicaoIndividualizadaActionForm">

		<html:hidden property="codigoImovel"/>
		<html:hidden property="mesAno"/>
		<html:hidden property="idTipoLigacao"/>

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
				<td width="660" valign="top" class="centercoltext">
					<table height="100%">
						<tr>
							<td></td>
						</tr>
					</table>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="11">
								<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
							</td>
							<td class="parabg">Manter Imóvel</td>
							<td width="11">
								<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
							</td>
						</tr>
					</table>
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td colspan="4" height="23">
								<font color="#000000" style="font-size: 10px" face="Verdana, Arial, Helvetica, sans-serif">
									<strong>Imoveis Cadastrados:</strong>
								</font>
							</td>
							<logic:present scope="application" name="urlHelp">
								<td align="right">
									<a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroImovelManter', 500, 700);">
										<span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span>
									</a>
								</td>
							</logic:present>
							<logic:notPresent scope="application" name="urlHelp">
								<td align="right">
									<span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span>
								</td>
							</logic:notPresent>
						</tr>
					</table>
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td colspan="4" bgcolor="#000000" height="2"></td>
						</tr>
						<tr>
							<td>
								<table width="100%" bgcolor="#99CCFF">
									<tr bordercolor="#000000">
										<td width="67" bordercolor="#000000" bgcolor="#90c7fc" align="center">
											<div align="center">
												<strong>Matrícula</strong>
											</div>
										</td>
										<td width="167" bordercolor="#000000" bgcolor="#90c7fc" align="center">
											<div align="center">
												<strong>Cliente</strong>
											</div>
										</td>
										<td width="482" bgcolor="#90c7fc" align="center">
											<strong>Endereço Imóvel</strong>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<%--Esquema de paginação--%>
								<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
									export="currentPageNumber=pageNumber;pageOffset"
									maxPageItems="10" items="${sessionScope.totalRegistros}">

									<table width="100%" bgcolor="#99CCFF">
										<pg:param name="pg" />
										<pg:param name="q" />
										<%
											int cont = 0;
										%>
										<logic:iterate name="imoveisFiltrados" id="imovelFiltrado">
											<pg:item>
												<%
													cont = cont + 1;
													if (cont % 2 == 0) {
												%>
												<tr bgcolor="#cbe5fe">
												<%
													} else {
												%>
												<tr bgcolor="#FFFFFF">
												<%
													}
												%>
													<td width="67">
														<div align="center">
															<bean:write name="imovelFiltrado" property="matricula" />
														</div>
													</td>
													<td width="167">
														<div align="center">
														<a href="javascript:enviarFormFiltro(${imovelFiltrado.matricula},'${mesAnoFaturamento}','${imovelFiltrado.idTipoLigacao}');">
															<bean:write name="imovelFiltrado" property="cliente" />
														</a>
														</div>
													</td>
													<td width="482">
															<bean:write name="imovelFiltrado" property="endereco" />
													</td>
												</tr>
											</pg:item>
										</logic:iterate>
									</table>
									<table width="100%">
										<tr>
											<td>
												<input name="button" type="button" class="bottonRightCol" value="Voltar Pesquisa" onclick="window.location.href='<html:rewrite page="/exibirFiltrarHistoricoMedicaoIndividualizadaAction.do"/>'" align="left" style="width: 110px;">
											</td>
										</tr>
									</table>
									<table width="100%" border="0">
										<tr>
											<td>
												<p align="left">&nbsp;</p>
												<div align="center">
													<strong><%@ include file="/jsp/util/indice_pager_novo.jsp"%></strong>
												</div>
											</td>
										</tr>
									</table>
								</pg:pager>
							</td>
						</tr>
					</table>
					<p>&nbsp;</p>
				</td>
			</tr>
		</table>
		<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>