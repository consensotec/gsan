<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="gcom.atualizacaocadastral.bean.DadosMovimentoAtualizacaoCadastralDMHelper"  isELIgnored="false"%>

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
function facilitador(objeto) {
	if (objeto.value === undefined || objeto.value) {
		objeto.value = false;
		marcarTodos();
	} else {
		objeto.value = true;
		desmarcarTodos();
	}
}

function voltarFiltro(){
	var form = document.forms[0];
	form.action = "exibirFiltrarDadosCadastraisImoveisInconsistentesAction.do";
	form.submit();
}

function gerarRelatorio(form) {
	form.action = "gerarRelatorioImoveisInconsistentesMovimentoAction.do";
	form.submit();
}

-->
</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/filtrarDadosCadastraisImoveisInconsistentesDMAction"
	name="FiltrarDadosCadastraisImoveisInconsistentesDMActionForm"
	type="gcom.gui.atualizacaocadastral.FiltrarDadosCadastraisImoveisInconsistentesDMActionForm" 
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
			<td width="620" valign="top" class="centercoltext">
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
					<td class="parabg">Atualizar Dados Cadastrais para Imoveis Inconsistentes</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4" height="23"><font color="#000000"
						style="font-size:10px"
						face="Verdana, Arial, Helvetica, sans-serif"> <strong>Imoveis
					Cadastrados:</strong></font></td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroImovelManter', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>	
				</tr>
			</table>	
			<table width="100%" cellpadding="0" cellspacing="0">	
				<tr>
					<td colspan="6" bgcolor="#000000" height="2"></td>
				</tr>
				<logic:present name="colecaoDadosMovimentoAtualizacaoCadastralHelper" scope="request">
					<tr bordercolor="#000000">
						<td width="10%" bgcolor="#90c7fc" align="center" bordercolor="#000000">
							<span onclick="facilitador(this);" style="cursor: pointer;">
								<ins><strong>Todos</strong></ins>
							</span>
						</td>
						<td width="15%" bgcolor="#90c7fc" align="center" bordercolor="#000000">
						    <strong>Data de Recebimento</strong>
						</td>
						<td width="15%" bgcolor="#90c7fc" align="center" bordercolor="#000000">
						    <strong>QTDE Total</strong>
						</td>
						<td width="15%" bgcolor="#90c7fc" align="center" bordercolor="#000000">
						    <strong>QTDE Pendente</strong>
						</td>
						<td width="15%" bgcolor="#90c7fc" align="center" bordercolor="#000000">
						    <strong>QTDE Pendente Inscrição</strong>
						</td>
						<td width="15%" bgcolor="#90c7fc" align="center" bordercolor="#000000">
						    <strong>Situação</strong>
						</td>
					</tr>
					<tr>
						<td height="350" colspan="6">
						<div style="width: 100%; height: 100%; overflow: auto;">
							<%	int cont = 0;	%>
							<table width="100%" bgcolor="#99CCFF">
								<logic:iterate name="colecaoDadosMovimentoAtualizacaoCadastralHelper" 
				               			id="helper" 
				               			scope="request" 
				               			type="DadosMovimentoAtualizacaoCadastralDMHelper">
				               			
								         <%	cont = cont + 1;
											if (cont % 2 == 0) {	%>
							                <tr bgcolor="#cbe5fe">
							             <%	} else {	%>
							                <tr bgcolor="#FFFFFF">
							             <%	}	%>
							            	    <td width="10%" align="center">
						                       		<input type="checkbox" name="idRegistro" value="${helper.id}" />
						                      	</td>
					                            
					                            <td width="15%" align="center"> 
					                            	<bean:write name="helper" property="dataRecebimento"/>
					                            </td>
							                            
					                            <td width="15%" align="right" >
					                        	<%	if (new Integer(helper.getQuantidadeTotal()) > 0 ) {	%>
						                            	<a href="javascript:redirecionarSubmit('filtrarDadosCadastraisImoveisInconsistentesDMAction.do?objetoConsultaFiltroAnterior=${telaRetorno}&objetoConsultaFiltro=total&id=${helper.id}');">
															<bean:write name="helper" property="quantidadeTotal" />
														</a>
												<%	} else {	%>
												  	    <bean:write name="helper" property="quantidadeTotal" />
												<%	}	%>
												</td>
														
												<td width="15%" align="right" >
												<%	if (new Integer(helper.getQuantidadePendente()) > 0 ) {	%>
														<a href="javascript:redirecionarSubmit('filtrarDadosCadastraisImoveisInconsistentesDMAction.do?objetoConsultaFiltroAnterior=${telaRetorno}&objetoConsultaFiltro=pendente&id=${helper.id}');">
															<bean:write name="helper" property="quantidadePendente" />
														</a>
												<%	} else {	%>
													  	<bean:write name="helper" property="quantidadePendente" />
												<%	}	%>
												</td>
				
												<td width="15%" align="right" >
												<%	if (new Integer(helper.getQuantidadePendenteInscricao()) > 0 ) {	%>
														<a href="javascript:redirecionarSubmit('filtrarDadosCadastraisImoveisInconsistentesDMAction.do?objetoConsultaFiltroAnterior=${telaRetorno}&objetoConsultaFiltro=pendenteInscricao&id=${helper.id}');">
															<bean:write name="helper" property="quantidadePendenteInscricao" />
														</a>
												<%	} else {	%>
													  	<bean:write name="helper" property="quantidadePendenteInscricao" />
												<%	}	%>
												</td>
												
												<td  align="center" width="15%" >
													<bean:write name="helper" property="situacao" />
												</td>
							            	</tr>
						       	</logic:iterate>
						       	 <%	cont = cont + 1;
									if (cont % 2 == 0) {	%>
					              		<tr bgcolor="#cbe5fe">
					             <%	} else {	%>
					                  	<tr bgcolor="#FFFFFF">
					             <%	}	%>
							       	<td width="10%" align="center"> 
		                            	TOTAL
		                            </td>
	
		                            <td width="15%" align="center"></td>
			                            
		                            <td width="15%" align="right" >
									  	${total}
									</td>
										
									<td width="15%" align="right" >
										 ${totalPendentes}
									</td>
	
									<td width="15%" align="right" >
										 ${totalPendentesInscritos}
									</td>
									
									<td width="15%" align="center"></td>
							   	</tr>
			               	</table>
						</div>
					</td>
					</tr>
					
				</logic:present>
			</table>
			<table>
				<tr>
					<td align="right">
						<input name="Button2" 
							   type="button"
							   class="bottonRightCol" 
							   value="Voltar Filtro" 
							   align="right"
							   onClick="javascript:voltarFiltro();" />
						<input name="Button2" 
							   type="button"
							   class="bottonRightCol" 
							   value="Gerar Relátorio" 
							   align="right"
							   onClick="javascript:gerarRelatorio(document.forms[0]);"/>
					</td>
				</tr>
			</table>			
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	
	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioAcessoLojaVirtualAction.do"/>
	
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>

