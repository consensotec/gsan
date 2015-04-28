<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gsan.util.ConstantesSistema"%>
<%@ page import="gsan.gui.cobranca.InformarEmpresaAcaoCobrancaGrupoCobrancaHelper"%>

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
	formName="InserirBaciaActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script type="text/javascript">
	var colecaoContratoTodos = "";
	function pesquisarContratoEmpresa(){
			var form = document.forms[0];
			form.action= "exibirInformarEmpresaAcaoCobrancaGrupoCobrancaAction.do?pesquisa=contrato";
			form.submit();
	}

	function selecionar(){
		var form = document.forms[0];

		if(form.idGrupo.options[0].selected==false){
			form.action = "exibirInformarEmpresaAcaoCobrancaGrupoCobrancaAction.do?pesquisa=selecionar";
			form.submit();
		}else{
			alert("Informe o Grupo");
		}
		
	}

	function adicionar(){
		var form = document.forms[0];
		form.action = "exibirInformarEmpresaAcaoCobrancaGrupoCobrancaAction.do?pesquisa=adicionar";
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

	function verficarSelecaoRemover(objeto){

		if (CheckboxNaoVazio(objeto)){
			if (confirm ("Confirma remoção?")) {
				document.forms[0].action = "exibirInformarEmpresaAcaoCobrancaGrupoCobrancaAction.do?pesquisa=remover"
				document.forms[0].submit();
			 }
		}
	 }

	 function adicionar(){
		var form = document.forms[0];

		if(form.idGrupo.options[0].selected==false){
			if(form.idEmpresa.options[0].selected==false){
				if(form.idContrato.options[0].selected==false){
					if(form.idCobrancaAcao.options[0].selected==false){
						form.action = "exibirInformarEmpresaAcaoCobrancaGrupoCobrancaAction.do?pesquisa=adicionar";
						form.submit();
					}else{
						alert("Informe Ação de Cobrança")
					}
				}else{
					alert("Informe a Contrato")
				}			
			}else{
				alert("Informe a Empresa")
			}
		}else{
			alert("Informe o Grupo");
		}
	 }

	 function limpar(){
		var form = document.forms[0];

		form.idGrupo.options[0].selected = true;
		form.idEmpresa.options[0].selected = true;
		form.idContrato.options[0].selected = true;
		form.idCobrancaAcao.options[0].selected = true;

		form.action="exibirInformarEmpresaAcaoCobrancaGrupoCobrancaAction.do?pesquisa=limpar";
		form.submit();
	 }

	 function telaPrincipal(){
		var form = document.forms[0];
		form.action="telaPrincipal.do";
		form.submit();
	 }

	 function desabilitarOnload(colecaoInformarEmpresaAcaoCobranca){
		var form = document.forms[0];
		if(colecaoInformarEmpresaAcaoCobranca==''){
			form.idGrupo.options[0].selected = true;
			form.idEmpresa.disabled = true;
			form.idContrato.disabled = true;
			form.idCobrancaAcao.disabled = true;
			form.botaoAdicionar.disabled = true;
		}else{
			form.idGrupo.disabled = true;
			form.botaoSelecionar.disabled = true
		}
	 }

	 function atualizar(){
		var form = document.forms[0];

		form.action = "informarEmpresaAcaoCobrancaGrupoCobrancaAction.do";
		form.submit();
	 }
</script>
</head>

<body leftmargin="5" topmargin="5" onload="desabilitarOnload('${colecaoInformarEmpresaAcaoCobrancaGrupoCobrancaHelper}');">

<html:form action="/informarEmpresaAcaoCobrancaGrupoCobrancaAction.do"
	name="InformarEmpresaAcaoCobrancaGrupoCobrancaActionForm"
	type="gsan.gui.cobranca.InformarEmpresaAcaoCobrancaGrupoCobrancaActionForm"
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
			<td width="625" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>

			<!--Início Tabela Reference a Páginação da Tela de Processo-->
			<table>
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Informar Empresa por Ação de Cobrança e por Grupo de Cobrança</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->

			<table width="100%">
				<tr>
					<td colspan="2">Para Informar Empresa por Ação de Cobrança e por Grupo de Cobrança, informe o dado abaixo:</td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td><strong> Grupo:</strong> <span class="style2">
					<strong> <font color="#FF0000">*</font> </strong> </span></td>
					<td><strong> <html:select property="idGrupo"
					style="width: 230px;">
					<html:option
						value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
					</html:option>

					<logic:present name="colecaoCobrancaGrupo" scope="session">
					<html:options collection="colecaoCobrancaGrupo"
						labelProperty="descricao" property="id" />

					</logic:present>
					</html:select> </strong></td>
				</tr>
			
				<tr>
					<td><strong> Empresa:</strong> <span class="style2">
					</span></td>
					<td><strong> <html:select property="idEmpresa"
					style="width: 230px;" onchange="pesquisarContratoEmpresa();">
					<html:option
						value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
					</html:option>

					<logic:present name="colecaoEmpresa" scope="session">
					<html:options collection="colecaoEmpresa"
						labelProperty="descricao" property="id"/>

					</logic:present>
					</html:select> </strong></td>
				</tr>
				
				<tr>
					<td><strong> Contrato:</strong> <span class="style2">
					</span></td>
					<td><strong> <html:select property="idContrato"
					style="width: 230px;">
					<html:option
						value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
					</html:option>

					<logic:present name="colecaoContrato" scope="session">
					<html:options collection="colecaoContrato"
						labelProperty="descricaoContrato" property="id" />

					</logic:present>
					</html:select> </strong></td>
				</tr>
				
				<tr>
					<td><strong>Ação de Cobrança:</strong> <span class="style2">
					</span></td>
					<td><strong> <html:select property="idCobrancaAcao"
					style="width: 230px;">
					<html:option
						value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
					</html:option>

					<logic:present name="colecaoCobrancaAcao" scope="session">
					<html:options collection="colecaoCobrancaAcao"
						labelProperty="descricaoCobrancaAcao" property="id" />

					</logic:present>
					</html:select> </strong></td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="right">
						<input type="button" value="Selecionar" class="bottonRightCol" onclick="selecionar();" name="botaoSelecionar">
						&nbsp;&nbsp;
						<input type="button" class="bottonRightCol" value="Adicionar" onclick="adicionar();" name="botaoAdicionar">
					</td>	
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2">
						<table width="100%" bgcolor="#99CCFF">
							<tr bgcolor="#90c7fc">	
								<td align="center"><a href="javascript:facilitador(this);" id="0"><strong>Todos</strong></a></td>
								<td><strong>Ação de Cobrança</strong></td>
								<td><strong>Grupo</strong></td>
								<td><strong>Empresa</strong></td>
								<td><strong>Contrato</strong></td>
							</tr>
							<logic:present name="colecaoInformarEmpresaAcaoCobrancaGrupoCobrancaHelper" scope="session">
								<% String cor = "#cbe5fe";%>
								<logic:iterate name="colecaoInformarEmpresaAcaoCobrancaGrupoCobrancaHelper" id="helper" type="InformarEmpresaAcaoCobrancaGrupoCobrancaHelper">
									<%	if (cor.equalsIgnoreCase("#cbe5fe")){	
										cor = "#FFFFFF";%>
										<tr bgcolor="#FFFFFF">	
									<%} else{	
										cor = "#cbe5fe";%>
										<tr bgcolor="#cbe5fe">		
									<%}%>
									
									<%	if (helper.getIndicadorRemovido().compareTo(ConstantesSistema.NAO)==0){%>
										<td align="center"><input type="checkbox" name="idRegistrosRemocao" value="<bean:write name="helper" property="cobrancaAcaoGrupoContrato.cobrancaAcao.id"/>"/></td>
										<td>${helper.cobrancaAcaoGrupoContrato.cobrancaAcao.descricaoCobrancaAcao}</td>
										<td>${helper.cobrancaAcaoGrupoContrato.cobrancaGrupo.descricaoAbreviada}</td>
										<td>${helper.cobrancaAcaoGrupoContrato.contratoEmpresaServico.empresa.descricao}</td>
										<td>${helper.cobrancaAcaoGrupoContrato.contratoEmpresaServico.descricaoContrato}</td>
									<%}%>
										
									</tr>
								</logic:iterate>
							</logic:present>
						</table>	
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" value="Cancelar" class="bottonRightCol" onclick="telaPrincipal();">
						<input type="button" value="Limpar" class="bottonRightCol" onclick="limpar();">
						<input type="button" value="Remover" class="bottonRightCol" onclick="verficarSelecaoRemover(document.InformarEmpresaAcaoCobrancaGrupoCobrancaActionForm.idRegistrosRemocao);">
					</td>
					<td align="right">
						<input type="button" value="Atualizar" class="bottonRightCol" onclick="atualizar();">
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
		</tr>
	</table>
	<tr>
		<td colspan="3"><%@ include file="/jsp/util/rodape.jsp"%>
	</tr>
</html:form>
</body>
</html:html>
