<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="gcom.util.ConstantesSistema"%>

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
<html:javascript staticJavascript="false" formName="CancelarFaturamentoDeUmGrupoActionForm" dynamicJavascript="true" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

	function validarForm(formulario){	
		if(validateCancelarFaturamentoDeUmGrupoActionForm(formulario)){    	

			 if (confirm("Confirma o cancelamento do faturamento?")) {	
				submeterFormPadrao(formulario);

			 }else {
				 return null;
			 }
		}
	}


	
	function limparForm() {
		var form = document.CancelarFaturamentoDeUmGrupoActionForm;
			form.referenciaFaturada.value = "";
			form.valorFaturadoAgua.value = "";
			form.valorFaturadoEsgoto.value = "";
			form.valorFaturadoDebito.value = "";
			form.valorFaturadoCredito.value = "";
			form.valorFaturadoImpostos.value = "";
			form.valorFatudadoTotalCobrado.value = "";
			form.contasFaturadasNormal.value = "";
			form.contasFaturadasIncluida.value = "";
			form.contasFaturadasCancelada.value = "";
			form.contasFaturadasRetificada.value = "";
			form.contasFaturadasParcelada.value = "";
			form.contasFaturadaPaga.value = "";
			form.contasFaturadasVinculadasADcoumentoCobranca.value = "";
			form.contasFaturadasVinculadasAFaturamentoAgrupada.value = "";
			form.totalDeContasParaExclusao.value = "";
			form.grupoFaturamento.value = "-1";
		
	}

	function limparFormGrupoVazio() {
		var form = document.CancelarFaturamentoDeUmGrupoActionForm;
		if (form.grupoFaturamento.value == "-1" ) {
			form.referenciaFaturada.value = "";
			form.valorFaturadoAgua.value = "";
			form.valorFaturadoEsgoto.value = "";
			form.valorFaturadoDebito.value = "";
			form.valorFaturadoCredito.value = "";
			form.valorFaturadoImpostos.value = "";
			form.valorFatudadoTotalCobrado.value = "";
			form.contasFaturadasNormal.value = "";
			form.contasFaturadasIncluida.value = "";
			form.contasFaturadasCancelada.value = "";
			form.contasFaturadasRetificada.value = "";
			form.contasFaturadasParcelada.value = "";
			form.contasFaturadaPaga.value = "";
			form.contasFaturadasVinculadasADcoumentoCobranca.value = "";
			form.contasFaturadasVinculadasAFaturamentoAgrupada.value = "";
			form.totalDeContasParaExclusao.value = "";
		}
		
	}
	
</script>

</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('id');">
<html:form action="/cancelarFaturamentoDeUmGrupoAction"
	name="CancelarFaturamentoDeUmGrupoActionForm"
	type="gcom.gui.faturamento.CancelarFaturamentoDeUmGrupoActionForm" method="post"
	onsubmit="return validateCancelarFaturamentoDeUmGrupoActionForm(this);">


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
			</table>

			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Cancelar Faturamento do Grupo</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td width="40%" class="style3"><strong>Selecione o grupo de faturamento:</strong></td>
					<td width="60%" colspan="3">
						<html:select property="grupoFaturamento" tabindex="4" style="width:200px;"
									 onchange="redirecionarSubmit('exibirCancelarGrupoFaturamentoAction.do');limparFormGrupoVazio();">
							<html:option value="-1"> &nbsp; </html:option>
							<html:options collection="colecaoGrupoFaturamento"
										  property="id" 
										  labelProperty="descricao" />
						</html:select>
					</td>
				</tr>
				
				<tr> 
                <td height="24" colspan="7" bordercolor="#000000" class="style1"> 
                  <hr></td>
              </tr>
				
				<td width="50%" class="style3">
					<table width="100%" border="0">
						<tr>
						<td><strong>Referência Faturada: </strong></td>
						<td colspan="3">
							<span class="style2"> 
								<html:text property="referenciaFaturada" size="7" readonly="true" maxlength="7" tabindex="1"/>mm/aaaa 
							</span>
						</td>
						</tr>
						
						<tr>
							<td colspan="3"><strong>Valores Faturados: </strong></td>
						</tr>
						
						<tr>
							<td><strong>Água: </strong></td>
							<td colspan="2">
								<span class="style2"> 
									<html:text property="valorFaturadoAgua" size="16" maxlength="16" tabindex="1" readonly="true"/> 
								</span>
							</td>
						</tr>
		
						<tr>
							<td><strong>Esgoto: </strong></td>
							<td colspan="2">
								<span class="style2"> 
									<html:text property="valorFaturadoEsgoto" size="16" maxlength="16" tabindex="1" readonly="true"/> 
								</span>
							</td>
						</tr>
						<tr>
							<td><strong>Débitos: </strong></td>
							<td colspan="2">
								<span class="style2"> 
									<html:text property="valorFaturadoDebito" size="16" maxlength="16" readonly="true" tabindex="1"/> 
								</span>
							</td>
						</tr>
						<tr>
							<td><strong>Créditos: </strong></td>
							<td colspan="2">
								<span class="style2"> 
									<html:text property="valorFaturadoCredito" size="16" maxlength="16" tabindex="1"readonly="true" /> 
								</span>
							</td>
						</tr>
						<tr>
							<td><strong>Impostos: </strong></td>
							<td colspan="2">
								<span class="style2"> 
									<html:text property="valorFaturadoImpostos" size="16" maxlength="16" tabindex="1"readonly="true" /> 
								</span>
							</td>
						</tr>
						
						<tr>
							<td><strong>Total Cobrado: </strong></td>
							<td colspan="2">
								<span class="style2"> 
									<html:text property="valorFatudadoTotalCobrado" size="16" maxlength="16" tabindex="1" readonly="true"/> 
								</span>
							</td>
						</tr>
						<tr>
							<td width="40%" class="style3"><strong>Motivo de Cancelamento:</strong></td>
							<td width="60%" colspan="2">
								<html:select property="motivoCancelamento" tabindex="4" style="width:200px;">
									<html:option value="-1"> &nbsp; </html:option>
									<html:options collection="colecaoMotivoCancelamento"
												  property="id" 
												  labelProperty="descricaoMotivoCancelamentoConta" />
								</html:select>
							</td>
						</tr>
					</table>
				</td>
				
				<td>
					<table>
						<tr>
							<td><strong>Quantidade de contas faturadas por situação:</strong></td>
						</tr>
						<tr>
							<td><strong>Normal: </strong></td>
							<td colspan="2">
								<span class="style2"> 
									<html:text property="contasFaturadasNormal" size="10" maxlength="10" tabindex="1"readonly="true" /> 
								</span>
							</td>
						</tr>
						
						<tr>
							<td><strong>Incluida: </strong></td>
							<td colspan="2">
								<span class="style2"> 
									<html:text property="contasFaturadasIncluida" size="10" maxlength="10" tabindex="1"readonly="true"/> 
								</span>
							</td>
						</tr>
						
						<tr>
							<td><strong>Cancelada: </strong></td>
							<td colspan="2">
								<span class="style2"> 
									<html:text property="contasFaturadasCancelada" size="10" maxlength="10" tabindex="1"readonly="true"/> 
								</span>
							</td>
						</tr>
						
						<tr>
							<td><strong>Retificada: </strong></td>
							<td colspan="2">
								<span class="style2"> 
									<html:text property="contasFaturadasRetificada" size="10" maxlength="10" tabindex="1" readonly="true"/> 
								</span>
							</td>
						</tr>
						
						<tr>
							<td><strong>Parcelada: </strong></td>
							<td colspan="2">
								<span class="style2"> 
									<html:text property="contasFaturadasParcelada" size="10" maxlength="10" tabindex="1"readonly="true"/> 
								</span>
							</td>
						</tr>
						
						<tr>
							<td><strong>Paga: </strong></td>
							<td colspan="2">
								<span class="style2"> 
									<html:text property="contasFaturadaPaga" size="10" maxlength="10" tabindex="1"readonly="true"/> 
								</span>
							</td>
						</tr>
						
						<tr>
							<td><strong>Vinculada a doc. de cobrança: </strong></td>
							<td colspan="2">
								<span class="style2"> 
									<html:text property="contasFaturadasVinculadasADcoumentoCobranca" size="10" maxlength="10" tabindex="1"readonly="true"/> 
								</span>
							</td>
						</tr>
						
						<tr>
							<td><strong>Vinculada a fat. agrupada: </strong></td>
							<td colspan="2">
								<span class="style2"> 
									<html:text property="contasFaturadasVinculadasAFaturamentoAgrupada" size="10" maxlength="10" tabindex="1"readonly="true"/> 
								</span>
							</td>
						</tr>
						
						<tr>
							<td><strong>Total de contas para exclusão: </strong></td>
							<td colspan="2">
								<span class="style2"> 
									<html:text property="totalDeContasParaExclusao" size="10" maxlength="10" tabindex="1" readonly="true"/> 
								</span>
							</td>
						</tr>
						
					</table>
				</td>
				
			</table>
			<table  width="100%" border="0" >
			
				<tr>
					<td>
						<div align="left">
							<input type="button" name="adicionar" class="bottonRightCol" value="Cancelar" 
				                onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
							<input type="button" name="Desfazer" class="bottonRightCol" value="Limpar" onclick="javascript:limparForm();"
								tabindex="5">
						</div>
					</td>
					
					<td>
						<div align="right">
							<input type="button" name="botaoConcluir" class="bottonRightCol" 
								value="CANCELAR FATURAMENTO" onclick="javascript:validarForm(document.CancelarFaturamentoDeUmGrupoActionForm);">
						</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
