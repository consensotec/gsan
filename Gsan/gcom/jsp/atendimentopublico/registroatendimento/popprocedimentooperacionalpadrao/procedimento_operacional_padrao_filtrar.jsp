<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="FiltrarTipoSolicitacaoEspecificacaoActionForm" />
<script language="JavaScript">

function statusEspecificacao(){
	var form = document.forms[0];	
	form.action="exibirFiltrarProcedimentoOperacionalPadraoAction.do?objetoConsulta=tipoSolicitacao";	
	submeterFormPadrao(form);
}
	
function validarForm(form){
	form.action="filtrarProcedimentoOperacionalPadraoAction.do";
	submeterFormPadrao(form);
}

</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');">

<html:form action="/exibirFiltrarProcedimentoOperacionalPadraoAction"
	name="FiltrarProcedimentoOperacionalPadraoActionForm"
	type="gcom.gui.atendimentopublico.registroatendimento.popprocedimentooperacionalpadrao.FiltrarProcedimentoOperacionalPadraoActionForm"
	method="post">

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
			<td width="600" valign="top" bgcolor="#003399" class="centercoltext">
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
					<td class="parabg">Consultar Procedimento Operacional Padrão (POP)</td>

					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				<tr>
					<td height="5" colspan="3"></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td width="80%" colspan="2">Para filtrar o PoP Comercial, 
					informe os dados abaixo:</td>
					
				</tr>
				<tr>
					<td></td>
				</tr>
				
				<tr>
					<td><strong>Tipo de solicitação:</strong></td>

					<td><html:select property="idTipoSolicitacao" onchange="javascript:statusEspecificacao();">
							<html:option value="">&nbsp;</html:option>
							<html:options collection="colecaoTipoSolicitacao"
								labelProperty="descricao" property="id" />
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td><strong>Especificação:</strong></td>

					<td><html:select property="idEspecificacao">
							<html:option value="">&nbsp;</html:option>
							<html:options collection="colecaoEspecificacao"
								labelProperty="descricao" property="id" />
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td width="40%"><strong>Procedimento:</strong></td>
					<td colspan="2"><html:radio property="indicadorProcedimento" value="O"
						tabindex="3" /> <strong>Operacional</strong>&nbsp; <html:radio
						property="indicadorProcedimento" value="C" tabindex="4" /> <strong>Comercial</strong>&nbsp;
					<html:radio property="indicadorProcedimento" value="3" tabindex="4" />
					<strong>Todos</strong>
				</tr>
								
				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td valign="top"><input name="button" type="button"
						class="bottonRightCol" value="Limpar"
						onclick="window.location.href='/gsan/exibirFiltrarProcedimentoOperacionalPadraoAction.do?menu=sim'">&nbsp;</td>
					<td valign="top">
					<div align="right"><input name="button" type="button"
						class="bottonRightCol" value="Filtrar"
						onclick="validarForm(document.forms[0]);" tabindex="8"></div>
					</td>
				</tr>
				
				<tr> <td>&nbsp;</td> </tr>
				<tr> <td>&nbsp;</td> </tr>
				<tr> <td>&nbsp;</td> </tr>
				<tr> <td>&nbsp;</td> </tr>
				<tr> <td height="130" colspan="3"/> </tr>
			</table>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>

