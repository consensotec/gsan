<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

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
<script type="text/javascript">
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
function atualizarMotivoOcorrenciaOperacional(id){
	var form = document.forms[0];
	form.action = 'exibirAtualizarMotivoOcorrenciaOperacionalAction.do?idMotivoOcorrencia='+id;
	form.submit();
}
function removerOcorrenciaMotivoOperacional(){
	var form = document.forms[0];
		if(confirm('Confirma a Remoção?')){
			form.action = "removerMotivoOcorrenciaOperacionalAction.do";
			form.submit();
	}
}
function voltarFiltro(opcaoAtualizar){
	var form = document.forms[0];
	form.action = 'exibirFiltrarMotivoOcorrenciaOperacionalAction.do?opcaoAtualizar='+opcaoAtualizar;
	form.submit();
}
function verifcarCheckBoxSelecionadoRemover(){
	var form = document.forms[0];
	var condicaoRemover = false;
	for(var a = 0;a<form.idMotivoOcorrenciaOperacional.length;a++){
		if(form.idMotivoOcorrenciaOperacional[a].checked==true){
			condicaoRemover = true;
			removerOcorrenciaMotivoOperacional();
			break;			
		}
	}
	if(!condicaoRemover){
		alert("Nenhum registro selecionado para remoção.");
	}
	return condicaoRemover;
}
</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/exibirManterMotivoOcorrenciaOperacionalAction"
	name="AtualizarMotivoOcorrenciaOperacionalActionForm"
	type="gsan.gui.atendimentopublico.registroatendimento.AtualizarMotivoOcorrenciaOperacionalActionForm" method="post">
	
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

			<table width="100%">
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Manter Motivo de Ocorrência Operacional</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>

				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<td colspan="20"><font color="#000000" style="font-size:10px"
						face="Verdana, Arial, Helvetica, sans-serif"> <strong>Ocorrências Motivos Operacionais encontradas: </strong> </font></td>
				</tr>
			</table>
			<table width="100%" cellpadding="0" cellspacing="0">
				<table  width="100%" bgcolor="#99CCFF" border="0">
				<tr>
					<td colspan="6" bgcolor="#000000" height="2"></td>
				</tr>
				<tr bordercolor="#000000">
					<td align="center" bgcolor="#90c7fc"><strong><a href="javascript:facilitador(this);" id="0">Todos</a></strong></td>
					<td align="center" bgcolor="#90c7fc"><strong>Código</strong></td>
					<td align="center" bgcolor="#90c7fc"><strong>Descrição<strong></td>
					<td align="center" bgcolor="#90c7fc"><strong>Descrição Abreviada</strong></td>
					<td align="center" bgcolor="#90c7fc"><strong>Tipo da Ocorrência</strong></td>
					<td align="center" bgcolor="#90c7fc"><strong>Indicador de Uso</strong></td>
				</tr>
				</table>
				<table width="100%" bgcolor="#99CCFF" border="0">
				<%int cont = 0;%>
				<logic:iterate id="ocorrenciaOperacionalMotivo" name="colecaoOcorrenciaOperacionalMotivo">
						<%cont = cont + 1;
						if (cont % 2 == 0) {%>
						<tr bgcolor="#FFFFFF">
						<%} else {%>
						<tr bgcolor="#cbe5fe">
						<%}%>
						<td align="center"><input type="checkbox" name="idMotivoOcorrenciaOperacional" value="${ocorrenciaOperacionalMotivo.id}"></td>
						<td align="center"><a href="javascript:atualizarMotivoOcorrenciaOperacional(${ocorrenciaOperacionalMotivo.id});"><bean:write name="ocorrenciaOperacionalMotivo" property="id"/></a></td>
						<td align="center"><bean:write name="ocorrenciaOperacionalMotivo" property="descricao"/></td>
						<td align="center"><bean:write name="ocorrenciaOperacionalMotivo" property="descricaoAbreviada"/></td>
						<td align="center"><bean:write name="ocorrenciaOperacionalMotivo" property="ocorrenciaOperacionalTipo.descricao"/></td>
						<td align="center">
						<logic:equal
								name="ocorrenciaOperacionalMotivo"
								property="indicadorUso" value="1">
										Ativo
							</logic:equal> 
							<logic:equal 
								name="ocorrenciaOperacionalMotivo"
								property="indicadorUso" value="2">
										Inativo
							</logic:equal>
						</td>
					<tr>
				</logic:iterate>
				</table>
				<input type="button" value="Remover" class="bottonRightCol" onclick="verifcarCheckBoxSelecionadoRemover();">
				<input type="button" value="Voltar Filtro" class="bottonRightCol" onclick="voltarFiltro(${opcaoAtualizar});">
			</table>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
