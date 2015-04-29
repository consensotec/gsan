<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gsan.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<html:javascript formName="InformarSistemaParametrosActionForm"
	dynamicJavascript="false" staticJavascript="true" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script>

	var bCancel = false;

	function validateInformarSistemaParametrosActionForm(form) {
		if (bCancel) {
			return true;
		} else {
			return  validateRequired(form) && 
				validateEmail(form) && 
				validateInteger(form)&&
				validateCaracterEspecial(form);
		}
	}
	
	function IntegerValidations () {
		this.aa = new Array("diasMaximoReativarRA", "Dias M�ximo para Reativar RA deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.ab = new Array("diasMaximoAlterarOS", "Dias M�ximo para Alterar Dados da OS deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.ac = new Array("ultimoIDGeracaoRA", "�ltimo ID Utilizado para Gera��o do RA Manual deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.ad = new Array("diasMaximoExpirarAcesso", "Dias M�ximo para Expirar o Acesso deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.ae = new Array("diasMensagemExpiracaoSenha", "Dias para Come�ar Aparecer a	Mensagem de Expira��o de Senha deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.af = new Array("numeroMaximoTentativasAcesso", "N�mero M�ximo de Tentativas de	Acesso deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.ag = new Array("numeroMaximoFavoritosMenu", "N�mero M�ximo de Favoritos no	Menu do Sistema deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.ah = new Array("numeroDiasEncerramentoOrdemServico", "N�mero de Dias para Encerramento da OS deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.ai = new Array("numeroDiasEncerramentoOSSeletiva", "N�mero de Dias para Encerramento da OS Seletiva deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.aj = new Array("numeroDiasAlteracaoVencimentoPosterior", "N�mero de Dias para Altera��o do Vencimento Posterior deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.aj = new Array("diasVencimentoCertidaoNegativa", "N�mero dias de Vencimento para gerar Certid�o Negativa deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.al = new Array("ultimoDiaVencimentoAlternativo", "Ultimo Dia do Vencimento Alternativo deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.al = new Array("qtdDiasGuardarOcorrenciasParalisacao", "Quantidade de dias para guardar ocorr�ncias de paralisa��o deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		
		
	}

	function caracteresespeciais () {
	this.aa = new Array("descricaoRegulamento", "Descri��o Regulamento possui caracteres especiais.", new Function ("varName", " return this[varName];"));

	}
	
	function email () {
		this.aa = new Array("emailResponsavel", "E-Mail do Respons�vel inv�lido.", new Function ("varName", " return this[varName];"));
	}

	function required () {
		this.aa = new Array("diasMaximoAlterarOS", "Informe Dias M�ximo para Alterar Dados da OS.", new Function ("varName", " return this[varName];"));
		this.ab = new Array("ultimoIDGeracaoRA", "Informe �ltimo ID Utilizado para Gera��o do RA Manual.", new Function ("varName", " return this[varName];"));
		this.ac = new Array("ipServidorSmtp", "Informe IP do Servidor SMTP.", new Function ("varName", " return this[varName];"));
		this.ad = new Array("emailResponsavel", "Informe E-mail do Respons�vel.", new Function ("varName", " return this[varName];"));
		this.ae = new Array("ipServidorGerencial", "Informe IP do Servidor Gerencial.", new Function ("varName", " return this[varName];"));
		this.af = new Array("numeroDiasEncerramentoOrdemServico", "Informe N�mero de Dias para Encerramento da OS.", new Function ("varName", " return this[varName];"));
		this.ag = new Array("numeroDiasEncerramentoOSSeletiva", "Informe N�mero de Dias para Encerramento da OS Seletiva.", new Function ("varName", " return this[varName];"));
		this.ah = new Array("numeroDiasAlteracaoVencimentoPosterior", "Informe N�mero de Dias para Altera��o do Vencimento Posterior.", new Function ("varName", " return this[varName];"));
		this.ai = new Array("diasVencimentoCertidaoNegativa", "Informe N�mero dias de Vencimento para gerar Certid�o Negativa.", new Function ("varName", " return this[varName];"));
		this.aj = new Array("qtdDiasGuardarOcorrenciasParalisacao", "Informe Quantidade de dias para guardar ocorr�ncias de paralisa��o.", new Function ("varName", " return this[varName];"));
		
	}

	function limparUnidade() {
		var form = document.InformarSistemaParametrosActionForm;
		form.idUnidadeDestinoGrandeConsumidor.value = "";
		form.nomeUnidadeDestinoGrandeConsumidor.value = "";
	}

	/* Recupera Dados Popup */	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    var form = document.forms[0];
	    

	    if (tipoConsulta == 'unidadeOrganizacional') {
			form.idUnidadeDestinoGrandeConsumidor.value = codigoRegistro;
			form.nomeUnidadeDestinoGrandeConsumidor.value = descricaoRegistro;
			form.nomeUnidadeDestinoGrandeConsumidor.style.color = "#000000";
	   }

	}
	
	function adicionarArquivo(tipoArquivo){
	
		var form = document.forms[0];
		
		form.target = "";
		form.action = "informarParametrosSistemaWizardAction.do?destino=5&action=informarParametrosSistemaDadosGeraisEmpresaAction&adicionar=OK&tipoArquivo="+tipoArquivo;
	
		retorno = true;
	
		var campoArquivo;
		var descricaoArquivo;
		var msgCampo;
		var msgDescricao;
		
		switch (tipoArquivo){
			case 1:
				msgCampo = "Arquivo do Decreto"
				campo = form.arquivoDecreto;
				descricaoArquivo = form.descricaoDecreto;
		    	break;
		   	case 2:
		   		msgCampo = "Arquivo da Lei"
		   		campo = form.arquivoLeiTarifaria;
				descricaoArquivo = form.descricaoLeiTarifaria;
		    	break;
		}	
		
		if(campo.value.length == 0){
			alert("Informe "+msgCampo);
			campo.focus();
			retorno = false;
//		} else if (!validateCaracterEspecial(form)){
//			retorno = false;
		}
	
		if (retorno){
			form.submit();
		}	
	}	
	
</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');">

	<html:form action="/informarParametrosSistemaWizardAction"
		method="post"
		onsubmit="return validateInformarSistemaParametrosActionForm(this);"
		enctype="multipart/form-data">

		<jsp:include
			page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=5" />

		<%@ include file="/jsp/util/cabecalho.jsp"%>
		<%@ include file="/jsp/util/menu.jsp"%>

		<input type="hidden" name="numeroPagina" value="5" />
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
					</div></td>
				<td width="655" valign="top" class="centercoltext">
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
							<td class="parabg">Informar Par�metros do Sistema</td>
							<td width="11"><img border="0"
								src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
							</td>
						</tr>
					</table>
					<p>&nbsp;</p>
					<table width="100%" border="0" dwcopytype="CopyTableRow">
						<tr>
							<td>Para informar par�metros do sistema, informe os dados
								abaixo:
							<td align="right"><a
								href="javascript: abrirPopup('/gsan/help/help.jsp?mapIDHelpSet=clienteInserirAbaNomeTipo', 500, 700);"><span
									style="font-weight: bold"><font color="#3165CE">Ajuda</font>
								</span>
							</a>
							</td>
						</tr>
					</table>

					<table width="100%" border="0">


						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td colspan="2" align="center"><strong>Par�metros
									para Atendimento ao P�blico:</strong>
							</td>
						</tr>

						<tr>
							<td>&nbsp;</td>
						</tr>

						<tr>
							<td width="40%"><strong>Indicador de Sugest�o de
									Tr�mite:</strong>
							</td>
							<td><strong> <html:radio
										property="indicadorSugestaoTramite" value="1" /> Sim <html:radio
										property="indicadorSugestaoTramite" value="2" /> N&atilde;o </strong>
							</td>
						</tr>

						<tr>
							<td width="40%"><strong>Indicador de Controle de
									Autoriza��o para a Tramita��o do RA:</strong>
							</td>
							<td><strong> <html:radio
										property="indicadorControleTramitacaoRA" value="1" /> Sim <html:radio
										property="indicadorControleTramitacaoRA" value="2" />
									N&atilde;o </strong></td>
						</tr>

						<tr>
							<td width="40%"><strong>Indicador de Calculo da
									Data Prevista do RA em Dias �teis:</strong>
							</td>
							<td><strong> <html:radio
										property="indicadorCalculoPrevisaoRADiasUteis" value="1" />
									Sim <html:radio property="indicadorCalculoPrevisaoRADiasUteis"
										value="2" /> N&atilde;o </strong></td>
						</tr>

						<tr>
							<td width="40%" align="left"><strong>Dias M�ximo
									para Reativar RA:</strong>
							</td>
							<td width="75%"><html:text property="diasMaximoReativarRA"
									size="2" maxlength="2" /></td>
						</tr>

						<tr>
							<td width="40%" align="left"><strong>Dias M�ximo
									para Alterar Dados da OS:<font color="#FF0000">*</font>
							</strong></td>

							<td width="75%"><html:text property="diasMaximoAlterarOS"
									size="2" maxlength="2" /></td>
						</tr>

						<tr>
							<td width="40%" align="left"><strong>N�mero de Dias
									para Encerramento da OS:<font color="#FF0000">*</font>
							</strong></td>

							<td width="75%"><html:text
									property="numeroDiasEncerramentoOrdemServico" size="2"
									maxlength="2" /></td>
						</tr>

						<tr>
							<td width="40%" align="left"><strong>N�mero de Dias
									para Encerramento da OS Seletiva:<font color="#FF0000">*</font>
							</strong></td>

							<td width="75%"><html:text
									property="numeroDiasEncerramentoOSSeletiva" size="2"
									maxlength="2" /></td>
						</tr>

						<tr>
							<td width="40%"><strong>Indicador de Valida��o da
									Localidade no Encerramento da OS Seletiva:</strong>
							</td>
							<td><strong> <html:radio
										property="indicadorValidacaoLocalidadeEncerramentoOS"
										value="1" /> Sim <html:radio
										property="indicadorValidacaoLocalidadeEncerramentoOS"
										value="2" /> N&atilde;o </strong></td>
						</tr>

						<tr>
							<td width="40%" align="left"><strong>Quantidade de
									dias de prorroga��o do vencimento na retifica��o:<font
									color="#FF0000">*</font>
							</strong></td>

							<td width="75%"><html:text
									property="numeroDiasAlteracaoVencimentoPosterior" size="2"
									maxlength="2" /></td>
						</tr>					
						
						
						<tr>
							<td><strong> Situa��o de �gua na Exclus�o de Im�vel: </strong></td>

							<td colspan="2"><strong> 
							<html:select property="situacaoAguaExclusaoImovel" style="width: 230px;">
								<html:option 
									value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
								</html:option>

								<logic:present name="colecaoSituacaoAguaLigacao"
									scope="session">
									<html:options collection="colecaoSituacaoAguaLigacao"
										labelProperty="descricao" property="id" />
								</logic:present>
							</html:select> </strong></td>
						</tr>

						<tr>
							<td><strong> Situa��o de Esgoto na Exclus�o de Im�vel: </strong></td>

							<td colspan="2"><strong> 
							<html:select property="situacaoEsgotoExclusaoImovel" style="width: 230px;">
								<html:option 
									value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
								</html:option>

								<logic:present name="colecaoSituacaoEsgotoLigacao"
									scope="session">
									<html:options collection="colecaoSituacaoEsgotoLigacao"
										labelProperty="descricao" property="id" />
								</logic:present>
							</html:select> </strong></td>
						</tr>							

						<tr>
							<td width="40%" align="left"><strong>Prazo para
									Revis�o de Conta(n� dias ap�s vencimento):<font color="#FF0000"></font>
							</strong></td>

							<td width="75%"><html:text property="numeroDiasRevisaoConta"
									size="2" maxlength="2" /></td>
						</tr>

						<tr>
							<td width="40%" align="left"><strong>�ltimo ID
									Utilizado para Gera��o do RA Manual:<font color="#FF0000">*</font>
							</strong></td>
							<td width="75%"><html:text property="ultimoIDGeracaoRA"
									size="5" maxlength="5"
									onkeyup="javascript:verificaNumeroInteiro(this);" /></td>
						</tr>

						<tr>
							<td width="40%"><strong>Indicador Certid�o Negativa
									com Efeito Positivo:</strong></td>
							<td><strong> <html:radio
										property="indicadorCertidaoNegativaEfeitoPositivo" value="1" />
									Sim <html:radio
										property="indicadorCertidaoNegativaEfeitoPositivo" value="2" />
									N&atilde;o </strong></td>
						</tr>
						<tr>
							<td width="40%"><strong>Indicador de D&eacute;bito
									a Cobrar v&aacute;lido Certid�o Negativa:</strong></td>
							<td><strong> <html:radio
										property="indicadorDebitoACobrarValidoCertidaoNegativa"
										value="1" /> Sim <html:radio
										property="indicadorDebitoACobrarValidoCertidaoNegativa"
										value="2" /> N&atilde;o </strong></td>
						</tr>

						<tr>
							<td width="25%" align="left"><strong>N�mero dias de
									Vencimento para gerar Certid�o Negativa:</strong> <font color="#FF0000">*</font>
							</td>
							<td><html:text maxlength="3"
									property="diasVencimentoCertidaoNegativa" size="3" /></td>
                                             						
						</tr>
						<tr>
							<td width="40%"><strong>Indicador de Documento
									obrigat�rio para 2� via da Conta:</strong>
							</td>
							<td><strong> <html:radio
										property="indicadorDocumentoValido" value="1" /> Sim <html:radio
										property="indicadorDocumentoValido" value="2" /> N&atilde;o </strong>
							</td>
						</tr>

						<tr>
							<td><strong>Unidade de destino para im�veis com
									perfil de grande consumidor:</strong>
							</td>
							<td><html:text maxlength="4"
									property="idUnidadeDestinoGrandeConsumidor" size="4"
									onkeyup="javascript:limparUnidadeTecla();"
									onkeypress="javascript:validaEnterComMensagem(event, 'informarParametrosSistemaWizardAction.do?destino=5&action=informarParametrosSistemaDadosGeraisEmpresaAction&pesquisaUnidade=sim&id='+this.value, 'idUnidadeDestinoGrandeConsumidor', 'Unidade Organizacional');" />

								<a
								href="javascript:abrirPopup('exibirPesquisarUnidadeOrganizacionalAction.do');">
									<img width="23" height="21" border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Unidade Organizacional" />
							</a> <logic:present name="idUnidadeDestinoGrandeConsumidor">
									<logic:present name="idUnidadeEncontrada" scope="request">
										<html:text maxlength="30"
											property="nomeUnidadeDestinoGrandeConsumidor" readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000"
											size="40" />
									</logic:present>
									<logic:notPresent name="idUnidadeEncontrada" scope="request">
										<html:text maxlength="30"
											property="nomeUnidadeDestinoGrandeConsumidor" readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000"
											size="40" />
									</logic:notPresent>
								</logic:present> <logic:notPresent name="idUnidadeDestinoGrandeConsumidor"
									scope="request">
									<html:text maxlength="30"
										property="nomeUnidadeDestinoGrandeConsumidor" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000"
										size="40" />
								</logic:notPresent> <a href="javascript:limparUnidade();"> <img
									src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" title="Apagar" />
							</a></td>
						</tr>

						<tr>
							<td width="40%" align="left"><strong>�ltimo dia do
									Vencimento Alternativo:</strong></td>

							<td width="75%"><html:text
									property="ultimoDiaVencimentoAlternativo" size="2"
									maxlength="2" /></td>
						</tr>

						<tr>
							<td width="40%" align="left"><strong>N�mero de dias
									para validade ordem de fiscaliza��o:</strong></td>

							<td width="75%"><html:text
									property="qtdeDiasValidadeOSFiscalizacao" size="2"
									maxlength="2" /></td>
						</tr>

						<tr>
							<td width="40%" align="left"><strong>N�mero m�ximo
									de dias para uma ordem de servi�o ser fiscalizada:</strong></td>

							<td width="75%"><html:text
									property="qtdeDiasEncerraOSFiscalizacao" size="2" maxlength="2" />
							</td>
						</tr>

						<tr>
							<td width="40%" align="left"><strong>N�mero de dias
									para envio de conta por email:</strong></td>

							<td width="75%"><html:text
									property="qtdeDiasEnvioEmailConta" size="2" maxlength="2" /></td>
						</tr>

						<!-- RM 5759 -->
						<tr>
							<td><strong>Exigir RA no cancelamento do d�bito:<font
									color="#FF0000">*</font>
							</strong>
							</td>

							<td><html:radio property="indicadorPermiteCancelarDebito"
									value="1" tabindex="32" /><strong>Sim</strong>&nbsp; <html:radio
									property="indicadorPermiteCancelarDebito" value="2"
									tabindex="33" /><strong>N&atilde;o</strong></td>
						</tr>

						<tr>
							<td>&nbsp;</td>
						</tr>

						<tr>
							<td colspan="2" align="center"><strong>Par�metros
									para Seguran�a:</strong>
							</td>
						</tr>

						<tr>
							<td>&nbsp;</td>
						</tr>

						<tr>
							<td width="40%"><strong>Indicador de Acesso por
									Usu�rio:</strong></td>
							<td><strong> <html:radio
										property="indicadorLoginUnico" value="0" /> V&aacute;rios
									Acessos <html:radio property="indicadorLoginUnico" value="1" />
									&Uacute;nico Acesso </strong></td>
						</tr>

						<tr>
							<td width="40%" align="left"><strong>Dias M�ximo
									para Expirar o Acesso:</strong></td>
							<td><html:text maxlength="2"
									property="diasMaximoExpirarAcesso" size="2"
									onkeyup="javascript:verificaNumeroInteiro(this);" /></td>
						</tr>

						<tr>
							<td width="40%" align="left"><strong>Dias para
									Come�ar Aparecer a Mensagem de Expira��o de Senha:</strong></td>
							<td><html:text maxlength="2"
									property="diasMensagemExpiracaoSenha" size="2"
									onkeyup="javascript:verificaNumeroInteiro(this);" /></td>
						</tr>

						<tr>
							<td width="40%" align="left"><strong>N�mero M�ximo
									de Tentativas de Acesso:</strong></td>
							<td><html:text maxlength="2"
									property="numeroMaximoTentativasAcesso" size="2"
									onkeyup="javascript:verificaNumeroInteiro(this);" /></td>
						</tr>

						<tr>
							<td width="40%" align="left"><strong>N�mero M�ximo
									de Favoritos no Menu do Sistema:</strong></td>
							<td><html:text maxlength="2"
									property="numeroMaximoFavoritosMenu" size="2"
									onkeyup="javascript:verificaNumeroInteiro(this);" /></td>
						</tr>

						<tr>
							<td width="40%" align="left"><strong>IP do Servidor
									SMTP:<font color="#FF0000">*</font>
							</strong></td>
							<td><html:text maxlength="15" property="ipServidorSmtp"
									size="15" /></td>
						</tr>

						<tr>
							<td width="40%" align="left"><strong>IP do Servidor
									Gerencial:<font color="#FF0000">*</font>
							</strong></td>
							<td><html:text maxlength="30" property="ipServidorGerencial"
									size="31" /></td>
						</tr>

						<tr>
							<td width="40%" align="left"><strong>URL do
									servidor HELP:</strong></td>
							<td><html:text maxlength="60" property="urlHelp" size="55"
									style="text-transform: none;" /></td>
						</tr>

						<tr>
							<td width="40%" align="left"><strong>E-mail do
									Respons�vel:<font color="#FF0000">*</font>
							</strong></td>
							<td><html:text maxlength="80" property="emailResponsavel"
									size="35" style="text-transform: none;" /></td>
						</tr>

						<tr>
							<td width="40%" align="left"><strong>Mensagem do Sistema:
							</strong></td>
							<td><html:textarea property="mensagemSistema" cols="40"
									rows="4"
									onkeyup="limitTextArea(document.forms[0].mensagemSistema, 200, document.getElementById('utilizado'), document.getElementById('limite'));" />
							</td>
						</tr>

						<tr>
							<td width="40%"><strong>Indicador de controle de
									dias de expira��o de senha por Grupo:</strong>
							</td>
							<td><strong> <html:radio
										property="indicarControleExpiracaoSenhaPorGrupo" value="1" />
									Sim <html:radio
										property="indicarControleExpiracaoSenhaPorGrupo" value="2" />
									N&atilde;o </strong></td>
						</tr>

						<tr>
							<td width="40%"><strong>Indicador de controle de
									bloqueio de senhas usadas anteriormente:</strong>
							</td>
							<td><strong> <html:radio
										property="indicarControleBloqueioSenha" value="1" /> Sim <html:radio
										property="indicarControleBloqueioSenha" value="2" />
									N&atilde;o </strong></td>
						</tr>

						<tr>
							<td width="40%"><strong>Indicador de controle de
									senha forte:</strong>
							</td>
							<td><strong> <html:radio
										property="indicadorSenhaForte" value="1" /> Sim <html:radio
										property="indicadorSenhaForte" value="2" /> N&atilde;o </strong></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>

						<tr>
							<td colspan="2" align="center"><strong>Par�metros
									para Loja Virtual:</strong>
							</td>
						</tr>

						<tr>
							<td><strong>Regulamento de Servi�os:</strong>
							</td>
						</tr>
						
						<tr>
							<td><strong>Descri��o regulamento:</strong>
							</td>
							<td align="left"><html:text property="descricaoRegulamento"
									size="30" maxlength="30" 
									onkeypress="return campoTexto(event, this);"
									style="text-transform: none;" /></td>
						</tr>
						<tr>
							<td><strong>Arquivo regulamento:</strong>
							</td>
							<td><html:file property="arquivoRegulamento" size="40" /></td>
						</tr>						

						<tr>
							<td><strong>Estrutura Tarif�ria:</strong>
							</td>
						</tr>


						<tr>
							<td><strong>Descri��o decreto:</strong>
							</td>
							<td align="left"><html:text property="descricaoDecreto"
									size="30" maxlength="30" style="text-transform: none;" /></td>
						</tr>
						<tr>
							<td><strong>Arquivo decreto:</strong>
							</td>
							<td><html:file property="arquivoDecreto" size="40" /></td>
						</tr>

						<tr>
							<td><strong>Descri��o lei:</strong>
							</td>
							<td align="left"><html:text property="descricaoLeiEstTarif"
									size="30" maxlength="30" style="text-transform: none;" /></td>
						</tr>
						<tr>
							<td><strong>Arquivo lei:</strong>
							</td>
							<td><html:file property="arquivoLeiEstTarif" size="40" /></td>
						</tr>

						<!-- Normas de Instala��o e Individualiza��o Predial: - Inicio -->
						<tr>
							<td><strong>Normas de Instala��o e Individualiza��o
									Predial:</strong>
							</td>
						</tr>

						<tr>
							<td><strong>Descri��o lei:</strong>
							</td>
							<td align="left"><html:text
									property="descricaoLeiIndividualizacao" size="30"
									maxlength="30" style="text-transform: none;" /></td>
						</tr>
						<tr>
							<td><strong>Arquivo lei:</strong>
							</td>
							<td><html:file property="arquivoLeiIndividualizacao"
									size="40" /></td>
						</tr>

						<tr>
							<td><strong>Descri��o Norma CO:</strong>
							</td>
							<td align="left"><html:text property="descricaoNormaCO"
									size="30" maxlength="30" style="text-transform: none;" /></td>
						</tr>
						<tr>
							<td><strong>Arquivo Norma CO:</strong>
							</td>
							<td><html:file property="arquivoNormaCO" size="40"
									accept="application/pdf" /></td>
						</tr>

						<tr>
							<td><strong>Descri��o Norma CM:</strong>
							</td>
							<td align="left"><html:text property="descricaoNormaCM"
									size="30" maxlength="30" style="text-transform: none;" /></td>
						</tr>
						<tr>
							<td><strong>Arquivo Norma CM:</strong>
							</td>
							<td><html:file property="arquivoNormaCM" size="40" /></td>
						</tr>

						<!-- Normas de Instala��o e Individualiza��o Predial: - Fim -->
						<tr>
							<td width="40%" align="left"><strong>Quantidade de dias para guardar ocorr�ncias de paralisa��o:<font color="#FF0000">*</font></strong></td>
							<td><html:text maxlength="2"
									property="qtdDiasGuardarOcorrenciasParalisacao" size="2"
									onkeyup="javascript:verificaNumeroInteiro(this);" /></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						
						<tr>
							<td colspan="2" align="center"><strong>Documento Obrigat�rio:</strong></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>	
						<tr>
							<td width="40%"><strong>Atendimento Operacional:</strong>
							</td>
							<td><strong> <html:radio
										property="indicadorDocObrAtendimentoOperacional" value="1" /> Sim <html:radio
										property="indicadorDocObrAtendimentoOperacional" value="2" /> N&atilde;o </strong></td>
						</tr>
						<tr>
							<td width="40%"><strong>Atendimento Comercial:</strong>
							</td>
							<td><strong> <html:radio
										property="indicadorDocObrAtendimentoComercial" value="1" /> Sim <html:radio
										property="indicadorDocObrAtendimentoComercial" value="2" /> N&atilde;o </strong></td>
						</tr>
						<tr>
							<td width="40%"><strong>Informa��o:</strong>
							</td>
							<td><strong> <html:radio
										property="indicadorDocObrInformacao" value="1" /> Sim <html:radio
										property="indicadorDocObrInformacao" value="2" /> N&atilde;o </strong></td>
						</tr>
						<tr>
							<td width="40%"><strong>Reitera��o:</strong>
							</td>
							<td><strong> <html:radio
										property="indicadorDocObrReiteracao" value="1" /> Sim <html:radio
										property="indicadorDocObrReiteracao" value="2" /> N&atilde;o </strong></td>
						</tr>
						
						

						<tr>
							<td></td>
							<td><strong><font color="#FF0000">*</font>
							</strong>Campo obrigat&oacute;rio</td>
						</tr>

						<tr>
							<td colspan="2">
								<div align="right"><jsp:include
										page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=5" /></div>
							</td>
						</tr>
					</table>
					<p>&nbsp;</p></td>
			</tr>
		</table>

		<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
