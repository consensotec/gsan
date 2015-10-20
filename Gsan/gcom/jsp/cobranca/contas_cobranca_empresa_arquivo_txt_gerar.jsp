<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@page
	import="gcom.cobranca.GerarArquivoTextoContasCobrancaEmpresaHelper"%>
<%@page import="gcom.util.Util" isELIgnored="false"%>

<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="GerarArquivoTextoContasCobrancaEmpresaActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<SCRIPT LANGUAGE="JavaScript"><!--

	
	function limparEmpresa() {
		var form = document.forms[0];
		form.idEmpresa.value = "";
		form.nomeEmpresa.value = "";	
		limparPeriodo();
	}
	
	function limparEmpresaTecla() {
		var form = document.forms[0];
		form.nomeEmpresa.value = "";	
		form.action = 'exibirGerarArquivoTextoContasCobrancaEmpresaAction.do?limpar=sim';
    	submeterFormPadrao(form);
	}
	
	
	
	function pesquisarEmpresa() {
		var form = document.forms[0];

			abrirPopup('exibirPesquisarEmpresaAction.do?limpaForm=S', 495, 300);
	}
	

	function limparPeriodo() {
		var form = document.forms[0];
		
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    	var form = document.forms[0];
   
	    if (tipoConsulta == 'empresa') {
			form.idEmpresa.value = codigoRegistro;
			form.nomeEmpresa.value = descricaoRegistro;
			form.nomeEmpresa.style.color = "#000000";
			form.action = 'exibirGerarArquivoTextoContasCobrancaEmpresaAction.do?limpar=sim';
    		submeterFormPadrao(form);
    	}
    
    }
	
	function validaForm() {
		var form =  document.forms[0];
		if(CheckboxNaoVazio(document.forms[0].idRegistros)){
			if (validateGerarArquivoTextoContasCobrancaEmpresaActionForm(form)){
					submeterFormPadrao(form);
				}
			
		}
	
	}
	
	function selecionarComandos(){
		var form =  document.forms[0];
		
		form.action = 'exibirGerarArquivoTextoContasCobrancaEmpresaAction.do?selecionarComandos=sim';
	    form.submit();
		
	}
	
	function CheckboxNaoVazio(campo){
	  form = document.forms[0];
	  retorno = false;
		
	  for(indice = 0; indice < form.elements.length; indice++){
		if (form.elements[indice].type == "checkbox" && form.elements[indice].checked == true) {
			retorno = true;
			break;
		}
	  }
		
	  if (!retorno){
		alert('Informe o(s) comando(s) desejado(s).');
	  }
		
	  return retorno;
	} 
	
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
	
--></script>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/gerarArquivoTextoContasCobrancaEmpresaAction"
	name="GerarArquivoTextoContasCobrancaEmpresaActionForm"
	type="gcom.gui.cobranca.GerarArquivoTextoContasCobrancaEmpresaActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>


	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="150" valign="top" class="leftcoltext">
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
			<td width="625" valign="top" bgcolor="#003399" class="centercoltext">
			<table height="100%">

				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Gerar Arquivo Texto de Contas em Cobran�a por Empresa</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a P�gina��o da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para gerar o arquivo texto das contas em cobran�a por
					empresa, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td width="30%"><strong>Empresa:<font color="#FF0000">*</font></strong></td>
					<td><html:text maxlength="9" property="idEmpresa" size="9"
						tabindex="14" 
						onkeypress="validaEnterComMensagem(event, 'exibirGerarArquivoTextoContasCobrancaEmpresaAction.do', 'idEmpresa', 'Empresa');" />
					<a href="javascript:pesquisarEmpresa();"><img
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"
						height="21" alt="Pesquisar" border="0"></a> <logic:present
						name="empresaInexistente" scope="request">
						<html:text property="nomeEmpresa" size="40" maxlength="40"
							readonly="true"
							style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
					</logic:present> <logic:notPresent name="empresaInexistente"
						scope="request">
						<html:text property="nomeEmpresa" size="40" maxlength="40"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:limparEmpresa();"> <img
						border="0" src="imagens/limparcampo.gif" height="21" width="23"> </a>
					</td>
				</tr>
				<tr>
					<td><strong>Per�odo de Execu��o do Comando:</strong></td>
					<td><strong> <html:text maxlength="10"
						property="periodoComandoInicial" size="10" tabindex="10"
						onkeyup="mascaraData(this, event);  replicarCampo(document.forms[0].periodoComandoFinal, document.forms[0].periodoComandoInicial);limparPeriodo();" />
					<a
						href="javascript:abrirCalendarioReplicando('GerarArquivoTextoContasCobrancaEmpresaActionForm', 'periodoComandoInicial', 'periodoComandoFinal');">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calend�rio" /></a>
					</strong> <html:text maxlength="10" property="periodoComandoFinal"
						tabindex="11" size="10" onkeyup="mascaraData(this, event);limparPeriodo();" /> <a
						href="javascript:abrirCalendario('GerarArquivoTextoContasCobrancaEmpresaActionForm', 'periodoComandoFinal')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calend�rio" /></a>
					(dd/mm/aaaa)</td>
				</tr>


				<tr>
					<td colspan="2" align="right"><input type="button"
						name="selecionar" class="bottonRightCol"
						value="Selecionar Comandos"
						onClick="javascript:selecionarComandos();" /></td>
				</tr>

				<tr>
					<td colspan="2" align="left"><strong>Comandos de Contas em
					Cobran�a:</strong></td>
				</tr>


				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td colspan="4" bgcolor="#000000" height="2"></td>
					</tr>
					<tr>
						<td>
						<table width="100%" bgcolor="#99CCFF" border="0">
							<tr bordercolor="#000000">
								<td width="8%" bgcolor="#90c7fc" align="center" rowspan="2"><strong><a
									href="javascript:facilitador(this);">Todos</a></strong></td>
								<td width="10%" bordercolor="#000000" bgcolor="#90c7fc"
									align="center" rowspan="2">
								<div align="center"><strong>Comando</strong></div>
								</td>
								<td width="12%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Data
								de Execu��o</strong></td>
								
				
						</table>
						</td>
					</tr>
					<tr>
						<td>
						<table width="100%" bgcolor="#99CCFF">
							<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
								export="currentPageNumber=pageNumber;pageOffset"
								maxPageItems="10" items="${sessionScope.totalRegistros}">
								<pg:param name="pg" />
								<pg:param name="q" />
								<logic:present
									name="colecaoGerarArquivoTextoContasCobrancaEmpresaHelper">
									<%int cont = 0;%>
									<logic:iterate
										name="colecaoGerarArquivoTextoContasCobrancaEmpresaHelper"
										id="gerarArquivoTextoContasCobrancaEmpresaHelper"
										type="GerarArquivoTextoContasCobrancaEmpresaHelper"
										scope="session">
										<pg:item>
											<%cont = cont + 1;
									if (cont % 2 == 0) {%>
											<tr bgcolor="#cbe5fe">
												<%} else {%>
											</tr>
											<tr bgcolor="#FFFFFF">
												<%}%>
												<td width="8%">
												<div align="center"><input type="checkbox"
													name="idRegistros"
													value="${gerarArquivoTextoContasCobrancaEmpresaHelper.idComandoEmpresaCobrancaConta}" />
												</div>
												</td>

												<td align="center" width="10%"><a
													href="javascript:abrirPopup('exibirConsultarContasComandoCobrancaPopupAction.do?pesquisa=nao&idComandoEmpresaCobrancaConta=<%= gerarArquivoTextoContasCobrancaEmpresaHelper
													.getIdComandoEmpresaCobrancaConta()%>', 475, 600);">
												<%=gerarArquivoTextoContasCobrancaEmpresaHelper
															.getIdComandoEmpresaCobrancaConta()%> </a></td>

												<td align="center" width="12%"><logic:present
													name="gerarArquivoTextoContasCobrancaEmpresaHelper"
													property="dataExecucao">
													<%=Util
																.formatarData(gerarArquivoTextoContasCobrancaEmpresaHelper
																		.getDataExecucao())%>
												</logic:present> <logic:notPresent
													name="<%=Util.formatarData(gerarArquivoTextoContasCobrancaEmpresaHelper.getDataExecucao())%>">&nbsp;</logic:notPresent>
												</td>
												

											</tr>
										</pg:item>
									</logic:iterate>
								</logic:present>
						</table>
						</td>
					</tr>
				</table>
				<table width="100%" border="0">
					<tr>
						<td colspan="2">
						<div align="center"><strong><%@ include
							file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
						</td>
					</tr>
					</pg:pager>

					<tr>
						<td colspan="2">&nbsp;</td>
					</tr>

					<tr>
						<td><strong> <font color="#FF0000"></font></strong></td>
						<td align="right">
						<div align="left"><strong><font color="#FF0000">*</font></strong>
						Campos obrigat&oacute;rios</div>
						</td>
					</tr>

					<tr>
						<td><input name="Button" type="button" class="bottonRightCol"
							value="Desfazer" align="left"
							onclick="window.location.href='<html:rewrite page="/exibirGerarArquivoTextoContasCobrancaEmpresaAction.do?menu=sim"/>'">
						<input name="Button" type="button" class="bottonRightCol"
							value="Cancelar" align="left"
							onclick="javascript:window.location.href='/gsan/telaPrincipal.do'"></td>
						<td colspan="2" align="right"><input type="button" name="gerar"
							class="bottonRightCol" value="Gerar"
							onClick="javascript:validaForm();" /></td>
					</tr>

				</table>

			</table>

			<%@ include file="/jsp/util/rodape.jsp"%> </html:form>
</body>
</html:html>
