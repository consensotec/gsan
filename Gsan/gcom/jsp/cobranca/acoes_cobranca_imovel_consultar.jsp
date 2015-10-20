<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="ExibirConsultarAcoesCobrancaImovelActionForm" />	
	
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">	
	
	function limpar(){
		var form = document.forms[0];
		
		form.idImovel.value = "";
		form.inscricaoImovel.value = "";
		
		form.periodoInicialAcao.value = "";
		form.periodoFinalAcao.value = "";
	}
	
	function validaForm(form){
		if(validateExibirConsultarAcoesCobrancaImovelActionForm(form)){
			if(form.periodoInicialAcao.value != '' && form.periodoFinalAcao.value == '' ||
				form.periodoInicialAcao.value == '' && form.periodoFinalAcao.value != ''){
				alert ('Informe Período Ação');
			}else{
				if(form.periodoInicialAcao.value != '' && form.periodoFinalAcao.value != ''){
					if(comparaData(form.periodoInicialAcao.value, '>', form.periodoFinalAcao.value)){
						alert('Período Final deve ser maior ou igual que Período Inicial');
						return;
					}
				}
				
				botaoAvancarTelaEspera('/gsan/consultarAcoesCobrancaImovelAction.do');
				//form.action = "consultarAcoesCobrancaImovelAction.do";
				//form.submit();
			}
		}
	}
	
	function limparImovel(){
		var form = document.forms[0];
		
		form.idImovel.value = "";
		form.inscricaoImovel.value = "";
	}
	
	function limparDescricaoImovel(){
		var form = document.forms[0];
		
		form.inscricaoImovel.value = "";
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];
		
		if (tipoConsulta == 'imovel') {
	    	form.idImovel.value = codigoRegistro;
	      	form.inscricaoImovel.value = descricaoRegistro;
	      	form.inscricaoImovel.style.color = "#000000";
	      	
	      	form.periodoInicialAcao.focus();
	    }    
	}
	
	function duplicarPeriodoAcao(form) {
		form.periodoFinalAcao.value = form.periodoInicialAcao.value;
	}
	
</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">
	
<div id="formDiv">
	<html:form action="/consultarAcoesCobrancaImovelAction"
	    name="ExibirConsultarAcoesCobrancaImovelActionForm"
	    type="gcom.gui.cobranca.ExibirConsultarAcoesCobrancaImovelActionForm"
		method="post" onsubmit="validateExibirConsultarAcoesCobrancaImovelActionForm(this);">
		
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
				
				<td width="625" valign="top" class="centercoltext">
					<table height="100%">
						<tr>
							<td></td>
						</tr>
					</table>
					<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td width="11"><img src="imagens/parahead_left.gif" border="0"></td>
							<td class="parabg">Consultar Ações de Cobrança do Imóvel</td>
							<td valign="top" width="11"><img src="imagens/parahead_right.gif"
								border="0"></td>
						</tr>
					</table>
					<p>&nbsp;</p>
					<table border="0" width="100%">
						<tr>
							<td colspan="3">
								<table bgcolor="#99ccff" border="0" bordercolor="#99ccff" cellpadding="1" cellspacing="0" width="100%">
									<tr bgcolor="#cbe5fe">
										<td>
											<div align="left">Para consultar a(s) ação(ões) de cobrança
												do imóvel, informe o(s) dado(s) abaixo:</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						
						<tr>
							<td colspan="3">
								<hr>
							</td>
						</tr>
						
						<tr>
							<td width="25%" ><strong>Matrícula do Imóvel:<font color="#FF0000">*</font></strong></td>
							<td width="75%" colspan="2"><html:text maxlength="9"
								property="idImovel" size="9"
								onkeyup="javascript:validaEnterComMensagem(event, 'exibirConsultarAcoesCobrancaImovelAction.do', 'idImovel', 'Imovel');limparDescricaoImovel();"
								onkeypress="return isCampoNumerico(event);" />
								
								<a href="javascript:abrirPopup('exibirPesquisarImovelAction.do', 400, 800);">
									<img width="23" height="21" title="Pesquisar Imóvel"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										border="0" />
								</a>
								<logic:notPresent name="imovelNaoEncontrado" scope="request">
									<html:text size="20" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										property="inscricaoImovel" />	
								</logic:notPresent>
								<logic:present name="imovelNaoEncontrado" scope="request">
									<html:text size="20" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000"
										property="inscricaoImovel" />
								</logic:present>
								<a href="javascript:limparImovel();"> <img
									border="0" src="imagens/limparcampo.gif" height="21" width="23" title="Limpar" >
								</a>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<hr>
							</td>
						</tr>
						<tr>
							<td><strong>Período Execução Ação:</strong></td>
							<td colspan="2"><html:text
								name="ExibirConsultarAcoesCobrancaImovelActionForm"
								onkeyup="mascaraData(this, event);duplicarPeriodoAcao(document.forms[0]);" property="periodoInicialAcao"
								onkeypress="return isCampoNumerico(event);"
								size="10" maxlength="10" /> <a
								href="javascript:abrirCalendarioReplicando('ExibirConsultarAcoesCobrancaImovelActionForm', 'periodoInicialAcao', 'periodoFinalAcao')"><img
								border="0"
								src="<bean:message key='caminho.imagens'/>calendario.gif"
								width="20" border="0" align="absmiddle" title="Exibir Calendário" /></a>
							<strong>a</strong> <html:text
								name="ExibirConsultarAcoesCobrancaImovelActionForm"
								onkeyup="mascaraData(this, event);" property="periodoFinalAcao"
								onkeypress="return isCampoNumerico(event);"
								size="10" maxlength="10" /> <a
								href="javascript:abrirCalendario('ExibirConsultarAcoesCobrancaImovelActionForm', 'periodoFinalAcao')"><img
								border="0"
								src="<bean:message key='caminho.imagens'/>calendario.gif"
								width="20" border="0" align="absmiddle" title="Exibir Calendário" /></a> dd/mm/aaaa
							</td>
						</tr>
						
						<tr>
							<td>&nbsp;</td>
						</tr>
						
						<tr>
							<td><strong> <font color="#ff0000"></font></strong></td>
							<td align="right">
							  <div align="left"><strong><font color="#ff0000">*</font></strong>
							  Campos obrigatórios</div>
							</td>
						</tr>
						
						<tr>
							<td>&nbsp;</td>
						</tr>
						
						<tr>
							<td colspan="2">
								<input name="Button" type="button" class="bottonRightCol"
									value="Desfazer" align="left"
									onclick="javascript:limpar();">
								<input type="button" name="ButtonCancelar" class="bottonRightCol"
									value="Cancelar"
									onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
							</td>
							<td align="right">
								<gsan:controleAcessoBotao name="Botao" value="Selecionar"
									onclick="validaForm(document.forms[0]);"
									url="consultarAcoesCobrancaImovelAction.do" tabindex="13" />
							</td>
						</tr>
					</table>
				<p class="style1">&nbsp;</p>
				</td>
			</tr>
		</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
	</html:form>
 </div>
 <%@ include file="/jsp/util/telaespera.jsp"%>
</body>

</html:html>