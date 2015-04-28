<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@page import="gsan.atendimentopublico.registroatendimento.SolicitacaoTipo"%>
<%@page import="gsan.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>/validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script>
function facilitador(objeto){
	if (objeto.id == "0" || objeto.id == undefined){
		objeto.id = "1";
		marcarTodos();
	} else{
		objeto.id = "0";
		desmarcarTodos();
	}
}

</script>
</head>
<body leftmargin="5" topmargin="5">
<html:form action="/exibirFiltrarProcedimentoOperacionalPadraoAction"
	name="FiltrarProcedimentoOperacionalPadraoActionForm"
	type="gsan.gui.atendimentopublico.registroatendimento.popprocedimentooperacionalpadrao.FiltrarProcedimentoOperacionalPadraoActionForm"
	method="post">

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
			<table height="100%"><tr><td></td></tr></table>
			<table><tr><td></td></tr></table>
			
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Manter Tipo de Solicitação com Especificações</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>

				</tr>
			</table>

			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="7" height="23"><font style="font-size: 10px;"
						color="#000000" face="Verdana, Arial, Helvetica, sans-serif"> <strong>Tipos
					de Solicitação com Especificações Cadastradas:</strong> </font></td>
				</tr>
				<tr>
					<!--<td colspan="4" bgcolor="#3399FF"> -->
					<td colspan="7" bgcolor="#000000" height="2" valign="baseline"></td>
				</tr>
				<tr>
					<td>
					<table width="100%" align="center" bgcolor="#90c7fc" border="0"
						cellpadding="0" cellspacing="0">
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">
							<table width="100%" bgcolor="#90c7fc">
								<tr bordercolor="#FFFFFF" bgcolor="#79BBFD">
									<td width="25%">
									<div align="center"><strong>Tipo da Solicitação</strong></div>
									</td>
									<td width="25%">
									<div align="center"><strong>Especificação</strong></div>
									</td>
									<td width="50%">
									<div align="center"><strong>Arquivo</strong></div>
									</td>
								</tr>
														
								<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
									export="currentPageNumber=pageNumber;pageOffset"
									maxPageItems="10" items="${sessionScope.totalRegistros}">
									<pg:param name="pg" />
									<pg:param name="q" />
									<%int cont = 0;%>
									<logic:iterate name="colecaoArquivos" id="arquivos">
									<% cont = cont + 1;
									if (cont % 2 == 0) {%>
									<tr bgcolor="#cbe5fe">
									<%} else { %>
									<tr bgcolor="#FFFFFF">
									<%}%>
									<td align="center" width="150px" valign="middle">
									<bean:write name="arquivos" property="solicitacaoTipoEspecificacao.solicitacaoTipo.descricao" /> 	
									</td>
									
									<td align="center" width="150px" valign="middle">
									<bean:write name="arquivos" property="solicitacaoTipoEspecificacao.descricao" /> 	
									</td>
									
									<td align="center" width="250px" valign="middle" >
									<a href="/gsan/filtrarProcedimentoOperacionalPadraoAction.do?visualizar=<bean:write name="arquivos" property="id"/>"><bean:write name="arquivos" property="nomeTruncado" /></a>
									</td>
			
								</logic:iterate>
							</table>
							<table width="100%" border="0">
								<tr>
									<td align="center"><strong><%@ include
										file="/jsp/util/indice_pager_novo.jsp"%></strong></td>
								</tr>
								</pg:pager>
								<tr>
									<td><font color="#FF0000"> 
										</font> <input name="button" type="button"
										class="bottonRightCol" value="Voltar Filtro"
										onclick="window.location.href='<html:rewrite page="/exibirFiltrarProcedimentoOperacionalPadraoAction.do?limpar=S"/>'"
										align="left" style="width: 80px;"></td>
								</tr>
							</table>
							</td>
						</tr>

					</table>
					<p>&nbsp;</p>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
