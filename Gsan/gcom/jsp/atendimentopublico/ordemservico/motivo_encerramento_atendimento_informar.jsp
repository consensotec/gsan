<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page import="gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncAcaoCobranca"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>


<script language="JavaScript">

function validaRadioButton(nomeCampo,mensagem){
	var alerta = "";
	if(!nomeCampo[0].checked && !nomeCampo[1].checked){
		alerta = mensagem +" deve ser selecionado.";
	}
	return alerta;
}

function desfazer(){
	 form = document.forms[0];
	 form.action='exibirInformarMotivoEncerramentoAtendimentoAction.do?menu=sim';
	 form.submit();
}
function validarForm(){
	 form = document.forms[0];
	 form.action='informarMotivoEncerramentoAtendimentoAction.do';
	 form.submit();
}

function removerAssociacao(id){
	 form = document.forms[0];
	if (confirm('Confirma Remoção?')) {
		 form.action='exibirInformarMotivoEncerramentoAtendimentoAction.do?removerAssociacao='+id;
		 form.submit();
	 }
}

function recuperarAssociacoes(){
	 form = document.forms[0];
	 document.forms[0].target='';
	 form.action='exibirInformarMotivoEncerramentoAtendimentoAction.do?recuperarAssociacoes=SIM';
	 form.submit();
}

function abrirPopupAssociacao() {
	var form = document.forms[0];
	
	if(form.idMotivoEncerramento.value != null && form.idMotivoEncerramento.value != '' && form.idMotivoEncerramento.value != '-1'){
		abrirPopup('exibirInformarAssociacaoMotivoEncerramentoAction.do?abrirPopup=SIM&idMotivoEncerramento='+form.idMotivoEncerramento.value, 370, 570);
	} else {
		alert('Selecione um Motivo de Encerramento.');
	}
}

</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');">

<html:form action="/informarMotivoEncerramentoAtendimentoAction"
	name="InformarMotivoEncerramentoAtendimentoActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.InformarMotivoEncerramentoAtendimentoActionForm"
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
					<td class="parabg">Informar Motivo de Encerramento dos Atendimentos</td>

					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				<tr>
					<td height="5" colspan="3"></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">
					<p>Para Informar Motivo de Encerramento dos Atendimentos, informe os dados abaixo:</p>
					</td>
				</tr>
				<tr>
					<td width="30%"><strong>Descrição:<font color="#FF0000">*</font></strong></td>
					<td><html:select property="idMotivoEncerramento" tabindex="3" onchange="recuperarAssociacoes();" >
						<logic:notEmpty name="colecaoAtendimentoMotivoEncerramento">
							<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
							<html:options collection="colecaoAtendimentoMotivoEncerramento"
								labelProperty="descricao" property="id" />
						</logic:notEmpty>
					</html:select></td>
				</tr>
			</table>
			<p>&nbsp;</p> 
				<logic:present name="exibirInformacoesAtendimentoMotivoEnc">
			<table width="100%" border="0">
				<tr>
					<td width="30%" align="left"><strong>Descri&ccedil;&atilde;o Abreviada:</strong></td>
					<td><html:text maxlength="8" property="descricaoAbreviada" size="8"/></td>
				</tr>
				<tr>
					<td><strong>Indicador de Execu&ccedil;&atilde;o:</strong></td>
					<td><strong> 
						<html:radio property="indicadorExecucao" value="1"/> Sim 
						<html:radio property="indicadorExecucao" value="2"/> N&atilde;o 
						</strong>
					</td>
				<tr>
					<td><strong>Indicador de Duplicidade:</strong></td>
					<td><strong> 
						<html:radio property="indicadorDuplicidade" value="1"/> Sim 
						<html:radio property="indicadorDuplicidade" value="2"/> N&atilde;o 
						</strong>
					</td>
				</tr>
				<tr>
					<td><strong>Indicador de Fiscaliza&ccedil;&atilde;o:</strong></td>
					<td><strong> 
						<html:radio property="indicadorFiscalizacao" value="1"/> Sim 
						<html:radio property="indicadorFiscalizacao" value="2"/> N&atilde;o 
						</strong>
					</td>
				</tr>
				<td><strong>Indicador de Visita Realizada:</strong></td>
					<td><strong> 
						<html:radio property="indicadorVisitaRealizada" value="1"/> Sim 
						<html:radio property="indicadorVisitaRealizada" value="2"/> N&atilde;o 
						</strong>
					</td>
				</tr>
				<tr>
					<td width="30%" align="left"><strong>Quantidade Encerramento de OS:</strong></td>
					<td><html:text maxlength="2" property="qtdeDiasAditivoPrazo" size="3" 
						onkeyup="verificaNumeroInteiro(this);"
						onchange="javascript:verificaNumeroInteiroComAlerta(this,'Quantidade Encerramento de OS');"
					/></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
			</logic:present>
			<table width="100%" border="0">
				<tr>
					<td valign="top">
						<div align="right"><input name="botaoAssociar" type="button"
							class="bottonRightCol" value="Associar"
							onclick="abrirPopupAssociacao();" tabindex="8"></div>
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" cellpadding="0" cellspacing="0">
			
					<tr bordercolor="#000000">
						<td bgcolor="#90c7fc" align="center" width="11%">
						<div align="center"><strong>Remover</strong></div>
						</td>
						<td bgcolor="#90c7fc" width="22%"><strong>Ação de cobrança</strong></td>
						<td bgcolor="#90c7fc" width="22%"><strong>Gera Pagamento?</strong></td>
						<td bgcolor="#90c7fc" width="22%"><strong>Gera Sucessor?</strong></td>
						<td bgcolor="#90c7fc" width="22%"><strong>Exibe no Documento?</strong></td>
					</tr>
					<logic:present name="colecaoAtendimentoMotivoEncAcaoCobranca" scope="session">
						<tr>
							<td colspan="5">
	
							<div style="width: 100%; height: 100%; overflow: auto;">
							<table width="100%" bgcolor="#99CCFF">
								<tr>
								<logic:iterate name="colecaoAtendimentoMotivoEncAcaoCobranca"
									id="atendimentoMotivoEncAcaoCobranca" type="AtendimentoMotivoEncAcaoCobranca">
									<c:set var="count2" value="${count2+1}" />
									<c:choose>
										<c:when test="${count2%2 == '1'}">
											<tr bgcolor="#FFFFFF">
										</c:when>
										<c:otherwise>
											<tr bgcolor="#cbe5fe">
										</c:otherwise>
									</c:choose>
				
									<td width="11%">
										<div align="center"><font color="#333333"> <img width="14"
											height="14" border="0"
											src="<bean:message key="caminho.imagens"/>Error.gif"
										 	onclick="removerAssociacao('${count2}')" />
										</font></div>
									</td>
									<td width="22%">
			      				 		<bean:define name="atendimentoMotivoEncAcaoCobranca" property="cobrancaAcao" id="cobrancaAcao" /> 
										<bean:write name="cobrancaAcao"
											property="descricaoCobrancaAcao" />
									</td>
									<td width="22%">
										<logic:equal name="atendimentoMotivoEncAcaoCobranca" property="indicadorGeraPagamento" value="1">
										SIM
										</logic:equal>
										<logic:notEqual name="atendimentoMotivoEncAcaoCobranca" property="indicadorGeraPagamento" value="1">
										NAO
										</logic:notEqual>
									</td>
									<td width="22%">
									
										<logic:equal name="atendimentoMotivoEncAcaoCobranca" property="indicadorGeraSucessor" value="1">
										SIM
										</logic:equal>
										<logic:notEqual name="atendimentoMotivoEncAcaoCobranca" property="indicadorGeraSucessor" value="1">
										NAO
										</logic:notEqual>
									</td>
									<td width="22%"> 
									
										<logic:equal name="atendimentoMotivoEncAcaoCobranca" property="indicadorExibeDocumento" value="1">
										SIM
										</logic:equal>
										<logic:notEqual name="atendimentoMotivoEncAcaoCobranca" property="indicadorExibeDocumento" value="1">
										NAO
										</logic:notEqual>
									</td>
								</logic:iterate>
								</tr>
								
								
	
							</table>
							</div>
							</td>
						</tr>
					</logic:present>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td valign="top"><input name="button" type="button"
						class="bottonRightCol" value="Voltar" onclick="desfazer();">&nbsp;<input
						type="button" name="ButtonCancelar" class="bottonRightCol"
						value="Cancelar"
						onClick="javascript:document.forms[0].target='';window.location.href='/gsan/telaPrincipal.do'"></td>
					<td valign="top">
					<logic:present name="exibirInformacoesAtendimentoMotivoEnc">
						<div align="right"><input name="botaoInserir" type="button"
							class="bottonRightCol" value="Informar"
							onclick="validarForm(document.forms[0]);" tabindex="8"></div>
					</logic:present>
					
					<logic:notPresent name="exibirInformacoesAtendimentoMotivoEnc">
						<div align="right"><input name="botaoInserir" type="button"
							class="bottonRightCol" value="Informar"
							tabindex="8"></div>
					</logic:notPresent>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td height="62" colspan="3"/>					
				</tr>
			</table>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
