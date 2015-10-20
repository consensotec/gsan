<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.seguranca.acesso.PermissaoEspecial"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<html:javascript staticJavascript="false"
	formName="AtualizarGrupoActionForm" dynamicJavascript="false" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script>

	var bCancel = false;

    function validateAtualizarGrupoActionForm(form) {
    	if (bCancel)
      		return true;
        else
       		return  validateRequired(form) && validateCaracterEspecial(form) && validateInteger(form) ;
   	}

    function IntegerValidations () {
		this.aa = new Array("diasExpiracaoSenha", "N�mero de dias informado inv�lido.", new Function ("varName", " return this[varName];"));
	}

    function caracteresespeciais () {
    	this.aa = new Array("descricao", "Descri��o possui caracteres especiais.", new Function ("varName", " return this[varName];"));
      	this.ab = new Array("descricaoAbreviada", "Descri��o Abreviada possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    }
    
    function required () {
    	this.aa = new Array("descricao", "Informe Descri��o.", new Function ("varName", " return this[varName];"));
      	this.ab = new Array("descricaoAbreviada", "Informe a Descri��o Abreviada.", new Function ("varName", " return this[varName];"));
    }
    
function selecionarPermissao(){
	var form = this;
	var objeto = form.idPermissao;
	
	if (form.idPerm == "0" || form.idPerm == undefined) {
		form.idPerm = "1";
		marca(objeto);
	} else {
		form.idPerm = "0";
		desmarca(objeto);
	}
}
function marca(objeto) {
	for (var i=0;i < objeto.length;i++){
		var elemento = objeto[i];
		if (elemento.type == "checkbox" && elemento.disabled == false){
			elemento.checked = true;
		}
	}
}
function desmarca(objeto) {
	for (var i=0;i < objeto.length;i++){
		var elemento = objeto[i];
		if (elemento.type == "checkbox"){
			elemento.checked = false;
		}
	}
}
</script>
</head>

<body leftmargin="5" topmargin="5">
	<html:form action="/atualizarGrupoWizardAction" method="post"
		onsubmit="return validateAtualizarGrupoActionForm(this);">

		<jsp:include
			page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=1" />

		<%@ include file="/jsp/util/cabecalho.jsp"%>
		<%@ include file="/jsp/util/menu.jsp"%>

		<input type="hidden" name="numeroPagina" value="1" />

		<table width="770" border="0" cellspacing="5" cellpadding="0">

			<tr>
				<td width="123" valign="top" class="leftcoltext">

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
								src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
							</td>

							<td class="parabg">Atualizar Grupo - Dados Gerais</td>
							<td width="11"><img border="0"
								src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
							</td>
						</tr>
					</table>

					<p>&nbsp;</p>

					<table border="0" width="100%">
						<tr>
							<td colspan="2">Para adicionar o grupo, informe os dados
								abaixo:</td>
						</tr>

						<tr>
							<td><strong>Descri��o:<font color="#ff0000">*</font>
							</strong>
							</td>
							<td><html:text maxlength="40"
									name="AtualizarGrupoActionForm" property="descricao" size="40" />
							</td>
						</tr>

						<tr>
							<td><strong>Descri��o Abreviada:<font
									color="#ff0000">*</font> </strong>
							</td>
							<td><html:text maxlength="10"
									name="AtualizarGrupoActionForm" property="descricaoAbreviada"
									size="10" />
							</td>
						</tr>
						<tr>
							<td width="26%"><strong>N�mero de dias para
									expira��o de senha:</strong>
							</td>
							<td width="74%"><html:text property="diasExpiracaoSenha"
									size="10" maxlength="10"
									onkeypress="return isCampoNumerico(event);" />
							</td>
						</tr>
						<tr>
							<td width="26%"><strong>Mensagem para usu�rios:</strong></td>
							<td width="74%"><html:text property="mensagem"
									maxlength="100" size="50" />
							</td>
						</tr>
						<tr>
							<td><strong>Indicador de Uso:<font color="#ff0000">*</font>
							</strong>
							</td>

							<td><html:radio name="AtualizarGrupoActionForm"
									property="indicadorUso" value="1" />Ativo <html:radio
									name="AtualizarGrupoActionForm" property="indicadorUso"
									value="2" />Inativo</td>
						</tr>

						<tr>
							<td><strong>Indicador de Superintend�ncia:<font
									color="#FF0000">*</font> </strong></td>
							<td><html:radio property="indicadorSuperintendencia"
									value="1" /> Sim <html:radio
									property="indicadorSuperintendencia" value="2" /> N�o</td>
						</tr>
						<tr>
							<td width="26%"><strong>Compet�ncia para
									Retifica��o:</strong>
							</td>
							<td width="74%"><html:text property="competenciaRetificacao"
									size="10" maxlength="6"
									onkeyup="formataValorMonetario(this, 5);"
									style="text-align: right;"
									onkeypress="javascript:return isCampoNumerico(event);" />&nbsp;(N�mero
								de vezes a m�dia de consumo)</td>
						</tr>

						<!-- RM 3892.1 Implementar Normas de Senhas no GSAN -->
						<logic:present name="colecaoPermissaoEspecial">
						<tr>
							<td height="5" colspan="2"></td>
						</tr>
						<tr>
							<td height="15" colspan="2"><strong>Permiss�o
									Especial:</strong></td>
						</tr>
						<tr>
						<td colspan="2">
							<table border="0" bgcolor="#99CCFF" width="100%" cellpadding="0"
								cellspacing="0">
								<tr bordercolor="#000000">
									<td width="18%" bgcolor="#90c7fc">
										<div align="center">
											<strong>Marca/Desmarca</strong>
										</div>
									</td>
									<td width="80%" bgcolor="#90c7fc"><strong>Descri��o</strong>
									</td>
								</tr>
								<tr>
									<td colspan="2" height="250">
										<div style="width: 100%; height: 100%; overflow: auto;">
											<table border="0" bgcolor="#99CCFF" width="100%">
												<%
													int cont = 0;
												%>
												
													<logic:iterate name="colecaoPermissaoEspecial"
														id="permissaoEspecial">
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
															<td width="18%">
																<div align="center">
																	<strong> <html:multibox
																			property="permissoesEspeciais"
																			value="${permissaoEspecial.id}" /> </strong>
																</div>
															</td>
															<td width="80%"><bean:write name="permissaoEspecial"
																	property="descricao" /></td>
														</tr>



													</logic:iterate>
												
												<logic:present name="colecaoPermissaoEspecialDesalibitado">
													<logic:iterate name="colecaoPermissaoEspecialDesalibitado"
														id="permissaoEspecial">
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
															<td width="18%">
																<div align="center">
																	<strong> <html:multibox
																			property="permissoesEspeciais"
																			value="${permissaoEspecial.id}" disabled="true" /> </strong>
																</div></td>
															<td width="80%"><bean:write name="permissaoEspecial"
																	property="descricao" /></td>
														</tr>
													</logic:iterate>
												</logic:present>
											</table>
										</div></td>
								</tr>
							</table>
							</td>
							</tr>
						</logic:present>
						<!-- Fim RM 3892.1 -->


						<tr>
							<td>&nbsp;</td>
							<td><strong><font color="#ff0000">*</font> </strong>Campos
								obrigat�rios</td>
						</tr>

						<tr>
							<td colspan="2">
								<table border="0" width="100%">
									<tr>
										<td>
											<div align="right"><jsp:include
													page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=1" /></div>
										</td>
									</tr>
								</table></td>
						</tr>

					</table></td>
			</tr>
		</table>

		<%@ include file="/jsp/util/rodape.jsp"%>

	</html:form>
</body>
</html:html>