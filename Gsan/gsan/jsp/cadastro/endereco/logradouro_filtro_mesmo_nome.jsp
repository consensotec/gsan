<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ page import="gsan.util.Pagina,gsan.util.ConstantesSistema,java.util.Collection"%>
<%@ page import="gsan.atendimentopublico.ordemservico.OSProgramacaoCalibragem"%>
<%@ page import="gsan.cadastro.endereco.bean.ManterLogradouroHelper"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script>


function confirmarInserir(){
	document.forms[0].action = "inserirLogradouroAction.do?confirmarLogradouroMesmoNome=ok"
	document.forms[0].submit();
}

function confirmarAtualizar(){
	document.forms[0].action = "atualizarLogradouroAction.do?confirmarLogradouroMesmoNome=ok"
	document.forms[0].submit();
}

</script>

</head>

<body leftmargin="5" topmargin="5">


<html:form action="/exibirFiltrarLogradouroMesmoNomeAction"
	name="ExibirFiltrarLogradouroMesmoNomeAction"
	type="gsan.gui.cadastro.endereco.LogradouroActionForm" method="post"
	onsubmit="return confirm('Confirma inclusão?')">
	
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
			<td valign="top" bgcolor="#003399" class="centercoltext">
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
						Lista de Logradouros
					</td>

					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				<tr>
					<td height="5" colspan="3"></td>
				</tr>
			</table>

			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="5" height="23"><font color="#000000"
						style="font-size:10px"
						face="Verdana, Arial, Helvetica, sans-serif"><strong>Logradouros
					Encontrados:</strong></font></td>
				</tr>
			</table>
			
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<!--<td colspan="4" bgcolor="#3399FF"> -->
					<td colspan="5" bgcolor="#000000" height="2"></td>
				</tr>
				<tr bordercolor="#000000">
					<td width="6%" align="center" bgcolor="#90c7fc"><strong>Cod.</strong></td>
					<td width="25%" align="center" bgcolor="#90c7fc"><strong>Nome Logradouro</strong></td>
					<td width="33%" align="center" bgcolor="#90c7fc"><strong>Bairro(s)</strong></td>
					<td width="19%" align="center" bgcolor="#90c7fc"><strong>Munic&iacute;pio</strong></td>
					<td width="19%" align="center" bgcolor="#90c7fc"><strong>CEP</strong></td>
				</tr>

				<tr>
					<td colspan="5">
					<table width="100%" bgcolor="#99CCFF">
						<%--Esquema de paginação--%>
						<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"
							maxPageItems="10" items="${sessionScope.totalRegistros}">
							<pg:param name="pg" />
							<pg:param name="q" />
							<logic:present name="colecaoLogradouro">
								<%int cont = 1;%>
								<logic:iterate name="colecaoLogradouro" id="logradouro">
									<pg:item>
										<%cont = cont + 1;
							if (cont % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											<td width="6%" align="center">${logradouro.id}</td>
											<td width="25%" align="center">${logradouro.nome}</td>
											<td width="33%" align="center">${logradouro.bairro}</td>
											<td width="19%" align="center">${logradouro.municipio}</td>
											<td width="19%" align="center">${logradouro.cep}</td>
										</tr>
									</pg:item>
								</logic:iterate>
							</logic:present>
					</table>
					<table width="100%" border="0">
						<tr>
							<td valign="top">
								<logic:present name="telaAtualizar">
									<input type="button" class="bottonRightCol"
										value="Confirmar" name="confirmarInclusao" onclick="confirmarAtualizar();"/>
								</logic:present>	
								<logic:present name="telaInserir">
									<input type="button" class="bottonRightCol"
										value="Confirmar" name="confirmarInclusao" onclick="confirmarInserir();"/>
								</logic:present>
								<input name="button"
									type="button" class="bottonRightCol" value="Voltar"
									onclick="window.location.href='<html:rewrite page="/exibirAtualizarLogradouroAction.do"/>'"
									align="left" style="width: 80px;">
							</td>
						</tr>
					</table>
					</td>
				</tr>
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
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioLogradouroManterAction.do" />
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
