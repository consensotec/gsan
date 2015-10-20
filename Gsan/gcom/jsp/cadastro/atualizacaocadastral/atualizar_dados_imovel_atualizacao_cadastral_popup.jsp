<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@page isELIgnored="false"%>
<%@ page import="gcom.cadastro.atualizacaocadastral.bean.DadosTabelaAtualizacaoCadastralHelper"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="ExibirAtualizarDadosImovelAtualizacaoCadastralPopupActionForm" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
<!-- Begin
	function confirma() {
		var form = document.forms[0];
		var valoresNao = "";
		var valoresSim = "";
		// Varre os checkbox que estao desmarcados.
		for (var i = 0;i < document.forms[0].elements.length; i++){
			var elemento = document.forms[0].elements[i];
			if (elemento.type == "checkbox" && elemento.name == "chkRegistrosAlteracao"){
				if (elemento.checked == false) {
					valoresNao += (valoresNao != "" ? "," : "") + elemento.value;
				} else {
					valoresSim += (valoresSim != "" ? "," : "") + elemento.value;
				}
			}
		}
		
		if(valoresNao == "" && valoresSim == ""){
			alert('Informe um registro para atualiza��o.');
		}else{
		
			form.idRegistrosAutorizados.value = valoresSim;
			form.idRegistrosNaoAutorizados.value = valoresNao;
	
			form.submit();
		}
	}
	
	function verificarTipoAlteracao(idTipoAlteracao,objeto){
	  var form = document.forms[0];
	  if(idTipoAlteracao == '' || idTipoAlteracao == '2'){
	    facilitador(objeto);
	  }
	
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
	
-->
</script>
</head>

<logic:present name="reload">
	<body leftmargin="5" topmargin="5"
		onload="window.close();">
</logic:present>

<logic:notPresent name="reload">
	<body leftmargin="5" topmargin="5" onload="resizePageSemLink(1060, 480);verificarTipoAlteracao('',0)">
</logic:notPresent>

<html:form action="/atualizarDadosImovelAtualizacaoCadastralAction"
	name="ExibirAtualizarDadosImovelAtualizacaoCadastralPopupActionForm"
	type="gcom.gui.cadastro.ExibirAtualizarDadosImovelAtualizacaoCadastralPopupActionForm" method="post"
	onsubmit="">
	
	<html:hidden property="idRegistrosNaoAutorizados" />
	<html:hidden property="idRegistrosAutorizados" />
	<html:hidden property="idImovelAtualizacaoCadastral" />

	<table width="1000" border="0" cellpadding="0" cellspacing="5">
		<tr>
			<td width="1000" valign="top" class="centercoltext">
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
					<td class="parabg">Consultar dados do Im&oacute;vel para Atualiza&ccedil;&atilde;o Cadastral</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>Dados do Im&oacute;vel:</td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td width="100"><strong>Im&oacute;vel:</strong></td>
					<td align="left"><html:text property="idImovel" readonly="true" size="30"
						style="background-color:#EFEFEF; border:0; color: #000000" /></td>
				</tr>
				<tr>
					<td><strong>Localidade:</strong></td>
					<td align="left"><html:text property="descricaoLocalidade" readonly="true" size="60"
						style="background-color:#EFEFEF; border:0; color: #000000" /></td>
				</tr>
				<tr>
					<td><strong>Setor Comercial:</strong></td>
					<td align="left"><html:text property="descricaoSetorComercial" readonly="true" size="60"
						style="background-color:#EFEFEF; border:0; color: #000000" /></td>
				</tr>
				<tr>
					<td><strong>Quadra:</strong></td>
					<td align="left"><html:text property="numeroQuadra" readonly="true" size="8"
						style="background-color:#EFEFEF; border:0; color: #000000" /></td>
				</tr>
			</table>
			
			<hr>
			
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Tabelas Atualizadas:</td>
				</tr>
				<tr>
					<td colspan="2">
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td height="0">

									<table width="1000" bgcolor="#90c7fc" border=0>
										<tr bordercolor="#90c7fc" bgcolor="#90c7fc">
											<td width="150"><strong>Tabela</strong></td>
											<td width="150"><strong>Campo</strong></td>
											<td width="400" colspan="2">
												<table border=0 cellpadding="0" cellspacing="0" width="100%">
													<tr><td colspan="2" align="center"><strong>Conte&uacute;do</strong></td></tr>
													<tr>
														<td align="center"><strong>Anterior</strong></td>
														<td align="center"><strong>Atual</strong></td>
													</tr>
												</table>
											</td>
											<td width="150"><strong>Data/Hora Atualiza&ccedil;&atilde;o</strong></td>
											<td width="100"><strong>Tipo Opera&ccedil;&atilde;o</strong></td>
											<td width="50"><strong>Alterar</strong></td>
										</tr>
									</table>

								</td>
							</tr>
							<tr>
								<td>

									<table width="1000" align="center" bgcolor="#99CCFF" border=0>
									<%int cont = 1;%>
									<logic:iterate name="colecaoDadosTabelaAtualizacaoCadastral" id="dadosTabelaAtualizacaoCadastralHelper">
										<%cont = cont + 1;
										if (cont % 2 == 0) {%>
											<tr bgcolor="#FFFFFF">
										<%} else {%>
											<tr bgcolor="#cbe5fe">
										<%}%>
												<td width="150">
													<bean:write name="dadosTabelaAtualizacaoCadastralHelper" property="descricaoTabela" />
												</td>
												<td width="150">
													<bean:write name="dadosTabelaAtualizacaoCadastralHelper" property="descricaoColuna" />
												</td>
												<td width="200">
													<bean:write name="dadosTabelaAtualizacaoCadastralHelper" property="colunaValorAnterior" />
												</td>
												<td width="200">
													<bean:write name="dadosTabelaAtualizacaoCadastralHelper" property="colunaValorAtual" />
												</td>
												<td width="150">
													<bean:write name="dadosTabelaAtualizacaoCadastralHelper" property="ultimaAtualizacao" />
												</td>
												<td width="100">
													<bean:write name="dadosTabelaAtualizacaoCadastralHelper" property="descricaoAlteracaoTipo" />
												</td>
												<td width="50">
												<div align="center">
												  <logic:empty name="dadosTabelaAtualizacaoCadastralHelper" property="dataProcessamento">
													<%
													String checado = "checked";
													%>
													<logic:notPresent name="desabilita" scope="request" >
														<logic:notPresent name="marcados" scope="request">
															<input type="checkbox" name="chkRegistrosAlteracao" id="chkRegistrosAlteracao" <%=checado%>
																value="<%=""+((DadosTabelaAtualizacaoCadastralHelper) dadosTabelaAtualizacaoCadastralHelper).getIdTabelaColunaAtualizacaoCadastral()%>" onclick="javascript:verificarTipoAlteracao('<%=((DadosTabelaAtualizacaoCadastralHelper) dadosTabelaAtualizacaoCadastralHelper).getIdAlteracaoTipo()%>',this);" />
														</logic:notPresent>
														<logic:present name="marcados" scope="request">
															<input type="checkbox" name="chkRegistrosAlteracao" id="chkRegistrosAlteracao" <%=checado%> disabled="disabled"
																value="<%=""+((DadosTabelaAtualizacaoCadastralHelper) dadosTabelaAtualizacaoCadastralHelper).getIdTabelaColunaAtualizacaoCadastral()%>" onclick="javascript:verificarTipoAlteracao('<%=((DadosTabelaAtualizacaoCadastralHelper) dadosTabelaAtualizacaoCadastralHelper).getIdAlteracaoTipo()%>',this);" />
														</logic:present>
													</logic:notPresent>
												  </logic:empty>
												</div>
												</td>
											</tr>
									</logic:iterate>
									</table>

								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td height="24">
					<div align="right"><input type="hidden" name="testeInserir"> 
					
					<logic:notPresent name="desabilita" scope="request">
						<input name="Button" type="button" class="bottonRightCol" value="Confirmar" onClick="confirma();" >
					</logic:notPresent>
					
					<input name="Button2" type="button" class="bottonRightCol"
						value="Fechar" onClick="window.close();"></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html:html>
