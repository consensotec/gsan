<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="gsan.util.ConstantesSistema"%>

<html:html>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="ParametroSistemaActionForm" dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">
	

		

	
	
	function validarForm(){
		var form = document.forms[0];
		if (validateParametroSistemaActionForm(form)){
				submeterFormPadrao(form);
		}	
	}
	

	function validateParametroSistemaActionForm(form) {
        return true;
	}


	function validarValor(valor){

		var form = document.forms[0];


			var string = form.codigoTipo.value;
			
			 var charCode = (valor.which) ?valor.which : valor.keyCode
			         if (charCode > 31 && (charCode < 48 || charCode > 57))
			            return false;
			 
			         return true;
	

	}
	

	function limparForm() {
		var form = document.FiltrarUsuarioTipoActionForm;
		form.id.value = "";
		form.descricao.value = "";
		form.indicadorUso[2].checked = true;
		form.indicadorFuncionario[2].checked = true;
	}
</script>

</head>

<body leftmargin="5" topmargin="5">
	<html:form action="/filtrarParametroSistemaAction"
		name="ParametroSistemaActionForm"
		type="gsan.gui.seguranca.parametrosistema.ParametroSistemaActionForm"
		method="post"
		onsubmit="return validateParametroSistemaActionForm(this);">


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
				<td width="615" valign="top" class="centercoltext">
					<table height="100%">
						<tr>
							<td></td>
						</tr>
					</table> <!--Início Tabela Reference a Páginação da Tela de Processo-->
					<table>
						<tr>
							<td></td>

						</tr>
					</table>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="11"><img border="0"
								src="imagens/parahead_left.gif" /></td>
							<td class="parabg">Filtrar Parametro do Sistema</td>
							<td width="11" valign="top"><img border="0"
								src="imagens/parahead_right.gif" /></td>
						</tr>
					</table>
					<p>&nbsp;</p>
					<table width="100%" border="0">
						<tr>
							<td colspan="2">Para filtrar um tipo de parametro do
								sistema, informe os dados abaixo:</td>

						</tr>

						<tr>
							<td><strong>C&oacute;digo:</strong></td>
							<td><html:text property="codigoConstanteParametroSistema" size="20"
													maxlength="20" onkeydown="validarValor(this);"/>
								<br> <font color="red"><html:errors
										property="codigoConstanteParametroSistema" /></font></td>
							<td>&nbsp;</td>
						</tr>

						<tr>
							<td><strong>Nome:</strong></td>
							<td colspan="2"><span class="style2"> <html:text
										property="nomeParametroSistema" size="30" maxlength="50" />
							</span></td>
							<td width="15%">&nbsp;</td>
						</tr>

						<tr>
							<td>&nbsp;</td>
							<td><html:radio property="tipoPesquisaNomeParametroSistema"
									value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
								Iniciando pelo texto<html:radio
									property="tipoPesquisaNomeParametroSistema" tabindex="5"
									value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
								Contendo o texto</td>
						</tr>

						<tr>
							<td><strong>Tipo:</strong></td>

							<td><html:select property="parametroTipo"
									tabindex="17">
									<html:option value="-1">&nbsp;</html:option>
									<html:option value="1">1-Número Inteiro</html:option>
									<html:option value="2">2-Indicador (sim não)</html:option>
									<html:option value="3">3-Valor</html:option>
									<html:option value="4">4-URL</html:option>
									<html:option value="5">5-Texto</html:option>
									<html:option value="6">6-Endereço IP</html:option>
									<html:option value="7">7-Método</html:option>
									<html:option value="8">8-Percentual</html:option>
									<html:option value="9">9-Data</html:option>
									<html:option value="10">10-Data/Hora</html:option>
									<html:option value="11">11-Lista de Valores</html:option>
									<html:option value="12">12-Coordenadas(x,y)</html:option>
									<html:option value="13">13-ID(código chave)</html:option>
									<html:option value="14">14-Referência</html:option>
								</html:select></td>
						</tr>

						<tr>
							<td><strong>Par&acirc;metro Restrito:</strong></td>
							<td colspan="2"><span class="style2"><strong>
										<label> <logic:present
												name="parametroRestritoParametroSistema" scope="session">
												<html:radio property="parametroRestritoParametroSistema"
													value="1" disabled="true" />
											</logic:present> <logic:notPresent name="parametroRestritoParametroSistema"
												scope="session">
												<html:radio property="parametroRestritoParametroSistema"
													value="1" />
											</logic:notPresent> Sim
									</label> <label> <logic:present
												name="parametroRestritoParametroSistema" scope="session">
												<html:radio property="parametroRestritoParametroSistema"
													value="2" disabled="true" />
											</logic:present> <logic:notPresent name="parametroRestritoParametroSistema"
												scope="session">
												<html:radio property="parametroRestritoParametroSistema"
													value="2" />
											</logic:notPresent> Não
									</label> <label> <html:radio
												property="parametroRestritoParametroSistema" value="3" />
											Todos
									</label>
								</strong></span></td>
						</tr>

						<tr>
							<td><strong>M&oacute;dulo:</strong></td>

							<td><html:select property="moduloParametroSistema"
									tabindex="17">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="colecaoModulos" property="id"
										labelProperty="descricaoModulo" />
								</html:select></td>

						</tr>

						<tr>
							<td><strong>Indicador de uso:</strong></td>
							<td colspan="2"><span class="style2"><strong>
										<label> <logic:present
												name="indicadorUsoParametroSistema" scope="session">
												<html:radio property="indicadorUsoParametroSistema"
													value="1" disabled="true" />
											</logic:present> <logic:notPresent name="indicadorUsoParametroSistema"
												scope="session">
												<html:radio property="indicadorUsoParametroSistema"
													value="1" />
											</logic:notPresent> Ativo
									</label> <label> <logic:present
												name="indicadorUsoParametroSistema" scope="session">
												<html:radio property="indicadorUsoParametroSistema"
													value="2" disabled="true" />
											</logic:present> <logic:notPresent name="indicadorUsoParametroSistema"
												scope="session">
												<html:radio property="indicadorUsoParametroSistema"
													value="2" />
											</logic:notPresent> Inativo
									</label> <label> <html:radio
												property="indicadorUsoParametroSistema" value="3" /> Todos
									</label>
								</strong></span></td>

						</tr>
						<tr>
							<td><input name="Button" type="button"
								class="bottonRightCol" value="Limpar" align="left"
								onclick="window.location.href='/gsan/exibirFiltrarParametroSistemaAction.do?menu=sim'"
								tabindex="8"> <input name="Submit23"
								class="bottonRightCol" value="Cancelar" type="button"
								tabindex="15"
								onclick="window.location.href='/gsan/telaPrincipal.do'">
							</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td width="65" align="right"><input name="Button2"
								type="button" class="bottonRightCol" value="Filtrar"
								align="right"
								onClick="javascript:validarForm(document.forms[0]);"
								tabindex="9" /></td>
						</tr>
					</table>

				</td>
			</tr>
		</table>
		<%@ include file="/jsp/util/rodape.jsp"%>
	</html:form>
</body>
</html:html>
