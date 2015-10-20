<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page import="gcom.cadastro.sistemaparametro.SistemaParametro"%>
<%@ page import="gcom.atualizacaocadastral.AreaAtualizacaoCadastralDM" isELIgnored="false"%>

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="SuspenderLocalidadeAtualizacaoCadastralActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>

<script language="JavaScript">
	function validateSuspenderLocalidadeAtualizacaoCadastralActionForm(form) {
		return validateRequired(form) && validateCheckRequired(form);
	}

	function required () {
		this.aa = new Array("idEmpresa", "Informe Empresa.", new Function ("varName", "return this[varName];"));
	}

	function CheckRequiredValidations(){
		this.aa = new Array("idsRegistro", "Nenhuma localidade selecionada para suspensão.", new Function ("varName", "return this[varName];"));
	}

	function validarForm(){
		var form = document.forms[0];

		if(validateSuspenderLocalidadeAtualizacaoCadastralActionForm(form)){
			msg = "Tem certeza que deseja suspender a(s) localidade(s)?";
			if(confirm(msg)){
				form.submit();
			}
		}
	}

	function limpar(){
		var form = document.forms[0];
		form.action='exibirSuspenderLocalidadeAtualizacaoCadastralAction.do?menu=sim';
		form.submit();
	}

	function carregarLocalidade(){
		var form = document.forms[0];
		form.action='exibirSuspenderLocalidadeAtualizacaoCadastralAction.do';
		form.submit();
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
</script>
</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');" >

<div id="formDiv">
	<html:form action="/suspenderLocalidadeAtualizacaoCadastralAction"
		name="SuspenderLocalidadeAtualizacaoCadastralActionForm"
		type="gcom.gui.atualizacaocadastral.SuspenderLocalidadeAtualizacaoCadastralActionForm"
		method="post"
		onsubmit="return validateSuspenderLocalidadeAtualizacaoCadastralActionForm(this);">

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
					<td class="parabg">Suspender Localidade para Atualização Cadastral</td>
					<td width="11">
						<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table bordercolor="#000000" width="100%" cellspacing="0">
				<tr>
					<td colspan="2">
						<p>Para suspender a Localidade para atualização cadastral, informe os dados abaixo:</p>
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
									<strong>Empresa:<font color="#FF0000">*</font></strong>
								</td>
								<td align="left">
									<html:select property="idEmpresa" tabindex="1" onchange="carregarLocalidade();" >
										<html:option value="-1">&nbsp;</html:option>
										<logic:present name="colecaoEmpresa" scope="request">
											<html:options collection="colecaoEmpresa" labelProperty="descricao" property="id" />
										</logic:present>
									</html:select>
								</td>
							</tr>

							<tr>
								<td height="10" colspan="3">
									<hr>
								</td>
							</tr>
						</table>

						<logic:present name="colecaoAreaAtualizacaoCadastral" scope="request">
							<table width="100%" cellpadding="0" cellspacing="0">
								<tr>
									<td colspan="2" bgcolor="#000000" height="2"></td>
								</tr>
								<tr>
									<td>
										<table width="100%" bgcolor="#99CCFF" border="0">
											<tr bordercolor="#000000">
												<td width="56" bgcolor="#90c7fc" align="center">
													<strong><a href="javascript:facilitador(this);">Marcar<font color="#FF0000">*</font></a></strong>
												</td>
												<td width="210" bordercolor="#000000" bgcolor="#90c7fc" align="center">
													<div align="center"><strong>Localidade</strong></div>
											   </td>
											   <td width="150" bordercolor="#000000" bgcolor="#90c7fc" align="center">
													<div align="center"><strong>Setor Comercial</strong></div>
											   </td>
											</tr>
										</table>
									</td>
								</tr>

								<tr>
									<td height="120">
										<div style="width: 100%; height: 100%; overflow: auto;">
											<table width="100%" bgcolor="#99CCFF">

												<%int cont = 0;%>
												<logic:iterate name="colecaoAreaAtualizacaoCadastral" id="areaAtualizacao" scope="request" type="AreaAtualizacaoCadastralDM">
													<%cont = cont + 1;
													if (cont % 2 == 0) {%>
														<tr bgcolor="#cbe5fe">
													<%} else {%>
														</tr>
														<tr bgcolor="#FFFFFF">
													<%}%>
															<td width="14%" align="center">
																<input type="checkbox" name="idsRegistro" value="${areaAtualizacao.id}" />
															</td>
															<td width="50%" align="left">
																<bean:write name="areaAtualizacao" property="localidade.descricaoParaRegistroTransacao"/>
															</td>
															<td width="45%" align="left">
																<% if (areaAtualizacao.getSetorComercial() != null) {  %>
																	<bean:write name="areaAtualizacao" property="setorComercial.descricaoParaRegistroTransacao"/>
																<% } %>
															</td>
														</tr>
												</logic:iterate>
											</table>
										</div>
									</td>
								</tr>
							</table>
						</logic:present>

						<logic:present name="semLocalidades" scope="request" >
							<table width="100%" border="0">
								<tr>
									<td align="left">
										<div align="left">
											<strong>NÃO EXISTEM LOCALIDADES PARA SEREM SUSPENSAS PARA A EMPRESA INFORMADA.</strong>
										</div>
									</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
							</table>
						</logic:present>

						<tr>
							<td height="19"><strong> <font color="#FF0000"></font></strong></td>
							<td align="right">
							<div align="left"><strong><font color="#FF0000">*</font></strong>
							Campos obrigat&oacute;rios</div>
							</td>
						</tr>

						<tr>
							<td height="10" colspan="3">
								<hr>
							</td>
						</tr>

						<tr>
							<td align="left" colspan="2" >
								<input type="button" class="bottonRightCol" value="Limpar" onclick="javascript:limpar();"/>
								<input type="button" name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
									onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
							</td>
							<td align="right">
								<input type="button" name="Button" class="bottonRightCol" value="Suspender"
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