<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page import="gcom.cadastro.sistemaparametro.SistemaParametro"%>

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="LiberarLocalidadeAtualizacaoCadastralActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>

<script language="JavaScript">
	var bCancel = false;

	function validateLiberarLocalidadeAtualizacaoCadastralActionForm(form) {
		if (bCancel)
			return true;
		return validateCaracterEspecial(form) && validateRequired(form) && validateInteger(form);
	}

	function caracteresespeciais () {
		this.aa = new Array("localidade", "Localidade possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.ab = new Array("setorComercial", "Setor Comercial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	}

	function required () {
		this.aa = new Array("localidade", "Informe Localidade.", new Function ("varName", "return this[varName];"));
		this.ab = new Array("idEmpresa", "Informe Empresa.", new Function ("varName", " return this[varName];"));
	}

	function IntegerValidations () {
		this.aa = new Array("localidade", "Localidade deve ser númerico.", new Function ("varName", " return this[varName];"));
		this.ab = new Array("setorComercial", "Setor Comercial deve ser númerico.", new Function ("varName", " return this[varName];"));
	}

	function validarForm(){
		var form = document.forms[0];

		if(validateLiberarLocalidadeAtualizacaoCadastralActionForm(form)){
			form.submit();
		}
	}

	function limpar(){
		var form = document.forms[0];
		form.action='exibirLiberarLocalidadeAtualizacaoCadastralAction.do?menu=sim';
		form.submit();
	}

	function limparBorrachaOrigem(tipoPesquisa){
		var form = document.forms[0];

		switch (tipoPesquisa) {
			case 1:
				form.localidade.value = "";
				form.nomeLocalidade.value = "";
				form.setorComercial.value = "";
				form.nomeSetorComercial.value = "";
				form.idEmpresa.value = "-1";
				break;

			case 2:
				form.setorComercial.value = "";
				form.nomeSetorComercial.value = "";
				form.idEmpresa.value = "-1";
				break;
		}
	}

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];

		if (tipoConsulta == 'localidade') {
			form.localidade.value = codigoRegistro;
			form.nomeLocalidade.value = descricaoRegistro;
			form.nomeLocalidade.style.color = "#000000";

			form.setorComercial.focus();
		}

		if (tipoConsulta == 'setorComercial') {
			form.setorComercial.value = codigoRegistro;
			form.nomeSetorComercial.value = descricaoRegistro;
			form.nomeSetorComercial.style.color = "#000000";

			form.idEmpresa.focus();
		}
	}
</script>
</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');" >

<div id="formDiv">
	<html:form action="/liberarLocalidadeAtualizacaoCadastralAction"
		name="LiberarLocalidadeAtualizacaoCadastralActionForm"
		type="gcom.gui.atualizacaocadastral.LiberarLocalidadeAtualizacaoCadastralActionForm"
		method="post"
		onsubmit="return validateLiberarLocalidadeAtualizacaoCadastralActionForm(this);">

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
					<td width="11">
						<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
					</td>
					<td class="parabg">Liberar Localidade para Atualização Cadastral</td>
					<td width="11">
						<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table bordercolor="#000000" width="100%" cellspacing="0">
				<tr>
					<td colspan="2">
						<p>Para liberar a localidade para atualização cadastral, informe os dados abaixo:</p>
					</td>
				</tr>
			</table>

			<table width="100%" border="0">
				<tr>
					<td height="10" colspan="3">
						<hr>
					</td>
				</tr>
			</table>

			<table bordercolor="#000000" width="100%" cellspacing="0">
				<tr>
					<td colspan="3">
						<table width="100%" border="0">
							<tr>
								<td style="width: 100px;">
									<strong>Localidade:<font color="#FF0000">*</font></strong>
								</td>
								<td>
									<html:text maxlength="3" tabindex="1" property="localidade" size="3"
										onkeypress="validaEnterComMensagem(event, 'exibirLiberarLocalidadeAtualizacaoCadastralAction.do','localidade','Localidade');return isCampoNumerico(event);"/>

									<a href="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do', 275, 480);">
										<img width="23" height="21" border="0" style="cursor:hand;"src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Localidade" />
									</a>

									<logic:present name="localidadeEncontrada" scope="request">
										<html:text property="nomeLocalidade" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
									</logic:present>

									<logic:notPresent name="localidadeEncontrada" scope="request">
										<html:text property="nomeLocalidade" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: red" />
									</logic:notPresent>

									<a href="javascript:limparBorrachaOrigem(1);">
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" />
									</a>
								</td>
							</tr>

							<tr>
								<td style="width: 100px;">
									<strong>Setor Comercial:</strong>
								</td>
								<td>
									<html:text maxlength="3" tabindex="1" property="setorComercial" size="3"
										onkeypress="validaEnterDependencia(event, 'exibirLiberarLocalidadeAtualizacaoCadastralAction.do', document.forms[0].setorComercial, document.forms[0].localidade.value, 'Localidade');return isCampoNumerico(event);"/>

									<a href="javascript:abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].localidade.value+'&tipo=SetorComercial',document.forms[0].localidade.value,'Localidade', 275, 480);">
										<img width="23" height="21" border="0" style="cursor:hand;" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Setor Comercial" />
									</a>

									<logic:present name="setorComercialEncontrado" scope="request">
										<html:text property="nomeSetorComercial" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
									</logic:present>

									<logic:notPresent name="setorComercialEncontrado" scope="request">
										<html:text property="nomeSetorComercial" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: red" />
									</logic:notPresent>

									<a href="javascript:limparBorrachaOrigem(2);">
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" />
									</a>
								</td>
							</tr>

							<tr>
								<td style="width: 100px;">
									<strong>Empresa:<font color="#FF0000">*</font></strong>
								</td>
								<td align="left">
									<html:select property="idEmpresa" tabindex="1" >
										<html:option value="-1">&nbsp;</html:option>
										<logic:present name="colecaoEmpresa" scope="request">
											<html:options collection="colecaoEmpresa" labelProperty="descricao" property="id" />
										</logic:present>
									</html:select>
								</td>
							</tr>

							<tr>
								<td>&nbsp;</td>
							</tr>

							<tr>
								<td height="19"><strong> <font color="#FF0000"></font></strong></td>
								<td align="right">
									<div align="left">
										<strong><font color="#FF0000">*</font></strong>
										Campos obrigat&oacute;rios
									</div>
								</td>
							</tr>

							<tr>
								<td height="10" colspan="3">
									<hr>
								</td>
							</tr>
						</table>

						<tr>
							<td align="left" colspan="2" >
								<input type="button" class="bottonRightCol" value="Limpar" onclick="javascript:limpar();"/>
								<input type="button" name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
									onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
							</td>
							<td align="right">
								<input type="button" name="Button" class="bottonRightCol" value="Liberar"
									onClick="javascript:validarForm();" />
							</td>
						</tr>

					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
	</html:form>
</div>

</body>
</html:html>
