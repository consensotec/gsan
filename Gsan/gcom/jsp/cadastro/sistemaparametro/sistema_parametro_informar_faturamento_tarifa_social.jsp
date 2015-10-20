<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.ConstantesSistema"%>
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
			return  validateRequired(form) && validateInteger(form);
		}
	}

	function IntegerValidations () {
		this.aa = new Array("menorConsumo", "Menor Consumo para ser Grande Usu�rio deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.ab = new Array("menorValor", "Menor Valor para Emiss�o de Contas deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.ac = new Array("qtdeEconomias", "Qtde de Economias para ser Grande Usu�rio deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.ad = new Array("mesesCalculoMedio", "Meses para C�lculo de M�dia de Consumo deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.ae = new Array("diasMinimoVencimento", "Dias M�nimo para Calcular Vencimento deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		
		this.ag = new Array("numeroMesesValidadeConta", "N�mero de Meses para validade da Conta deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.ah = new Array("numeroMesesAlteracaoVencimento", "N�mero de Meses para altera��o de um vencimento para outro deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.ai = new Array("consumoMaximo", "Consumo de Energia M�xima para a Tarifa deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.aj = new Array("areaMaxima", "�rea M�xima do Im�vel para a Tarifa deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.al = new Array("numeroDiasVariacaoConsumo", "Qtde de Dias de Varia��o de Consumo deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.al = new Array("qtdeContasRetificadas", "Qtde de Contas Retificadas deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		
		
		this.an = new Array("numeroVezesSuspendeLeitura", "Numero de vezes suspende leitura deve somente conter n�meros positivos.", new Function ("varName", "return this[varName];"));
		this.ao = new Array("numeroMesesLeituraSuspensa", "Numero de meses de leitura suspensa deve somente conter n�meros positivos.", new Function ("varName", "return this[varName];"));
		this.ap = new Array("numeroMesesReinicioSitEspFatu", "Numero de meses reinicio de situacao especial de faturamento deve somente conter n�meros positivos.", new Function ("varName", "return this[varName];"));
	}

	function required () {
		this.aa = new Array("mesAnoReferencia", "Informe M�s e Ano de Refer�ncia.", new Function ("varName", " return this[varName];"));
		this.ab = new Array("menorConsumo", "Informe Menor Consumo para ser Grande Usu�rio.", new Function ("varName", " return this[varName];"));
		this.ac = new Array("menorValor", "Informe Menor Valor para Emiss�o de Contas.", new Function ("varName", " return this[varName];"));
		this.ad = new Array("qtdeEconomias", "Informe Qtde de Economias para ser Grande Usu�rio.", new Function ("varName", " return this[varName];"));
		this.ae = new Array("mesesCalculoMedio", "Informe Meses para C�lculo de M�dia de Consumo.", new Function ("varName", " return this[varName];"));
		this.af = new Array("mesAnoAtualizacaoTarifaria", "Informe M�s e Ano de Atualiza��o Tarif�ria.", new Function ("varName", " return this[varName];"));
		this.ag = new Array("numeroMesesMaximoCalculoMedia", "Informe N�mero m�ximo de meses para calculo da media.", new Function ("varName", " return this[varName];"));
		this.ah = new Array("numeroMesesCalculoCorrecao", "Informe N�mero de meses para corre��o monet�ria.", new Function ("varName", " return this[varName];"));
		this.ai = new Array("numeroDiasVariacaoConsumo", "Informe Qtde de Dias de Varia��o de Consumo.", new Function ("varName", " return this[varName];"));
		this.ak = new Array("nnDiasPrazoRecursoAutoInfracao", "Informe N�mero de dias de prazo para entrada de Recurso do Auto de Infra��o.", new Function ("varName", " return this[varName];"));
    	this.al = new Array("qtdeContasRetificadas", "Informe Qtde de Contas Retificadas.", new Function ("varName", " return this[varName];"));
    	this.al = new Array("codigoTipoCalculoNaoMedido", "Informe o C�digo de Tipo de C�lculo de N�o Medido.", new Function ("varName", " return this[varName];"));
    	this.am = new Array("percentualBonusSocial", "Informe o percentual de B�nus Social.", new Function ("varName", " return this[varName];"));
    	this.an = new Array("qtdeContasRetificadas", "Informe Qtde de Contas Retificadas.", new Function ("varName", " return this[varName];"));
		
	}

	function validarDias(valor){
		var form = document.forms[0];
		alert('2');
		if(valor > 15){
			alert('O n�mero m�nimo de dias n�o pode ser superior a 15');
			limparDiasMinimoVencimento();
			limparDiasMinimoVencimentoCorreio();

		}
	}

	

	

	function limparDiasMinimoVencimento(){
		var form = document.forms[0];
		if(form.diasMinimoVencimento.value > 15){
			form.diasMinimoVencimento.value = '';
			form.diasMinimoVencimento.focus();
		}
	}

	function limparDiasMinimoVencimentoCorreio(){
		var form = document.forms[0];
		if(form.diasMinimoVencimentoCorreio.value > 15){
			form.diasMinimoVencimentoCorreio.value = '';
			form.diasMinimoVencimentoCorreio.focus();
		}
	}

	function limparNumeroMesesValidadeConta()	{
		var form = document.forms[0];
		if(form.numeroMesesValidadeConta.value > 12){
			form.numeroMesesValidadeConta.value = '';
			form.numeroMesesValidadeConta.focus();
		}
	}
	
	function limparNumeroMesesAlteracaoVencimento(){
		var form = document.forms[0];	
		if(form.numeroMesesAlteracaoVencimento.value > 12){
			form.numeroMesesAlteracaoVencimento.value = '';
			form.numeroMesesAlteracaoVencimento.focus();
		}
	}

</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}'); limitTextArea(document.forms[0].mensagemContaBraile, 500, document.getElementById('utilizado'), document.getElementById('limite'));">
<html:form action="/informarParametrosSistemaWizardAction" method="post"
	onsubmit="return validateInformarSistemaParametrosActionForm(this);">

	<jsp:include
		page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=2" />

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<input type="hidden" name="numeroPagina" value="2" />

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
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Informar Par�metros do Sistema</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>

			<p>&nbsp;</p>

			<table width="100%" border="0" dwcopytype="CopyTableRow">
				<tr>
					<td>Para informar par�metros do sistema, informe os dados abaixo:
					<td align="right"><a
						href="javascript: abrirPopup('/gsan/help/help.jsp?mapIDHelpSet=clienteInserirAbaNomeTipo', 500, 700);"><span
						style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>
				</tr>
			</table>

			<table width="100%" border="0">

				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td colspan="2" align="center"><strong>Par�metros para Faturamento:</strong></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>M�s e Ano de Refer�ncia:</strong>
					<font color="#FF0000">*</font></td>
					<td width="82%"><html:text property="mesAnoReferencia" size="7"
						maxlength="7" onkeyup="javascript:mascaraAnoMes(this,event);"
						disabled="true" /> <strong>mm/aaaa</strong></td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>Menor Consumo para ser Grande
					Usu�rio:</strong> <font color="#FF0000">*</font></td>
					<td><html:text maxlength="9" property="menorConsumo" size="9"
						onkeyup="javascript:verificaNumeroInteiro(this);" /></td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>Menor Valor para Emiss�o de
					Contas:</strong> <font color="#FF0000">*</font></td>
					<td><html:text maxlength="13" property="menorValor" size="13"
						onkeyup="javascript:formataValorMonetario(this,13);" /></td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>Valor para Emiss�o de Conta no
					Formato Ficha de Compensa��o:</strong></td>
					<td><html:text maxlength="13" property="valorContaFichaComp"
						size="13" onkeyup="javascript:formataValorMonetario(this,13);" />
					</td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong> Qtde de Economias para ser
					Grande Usu�rio:</strong> <font color="#FF0000">*</font></td>
					<td width="87%"><html:text property="qtdeEconomias" size="9"
						maxlength="9" onkeyup="javascript:verificaNumeroInteiro(this);" />
					</td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong> Meses para C�lculo de M�dia
					de Consumo:<font color="#FF0000">*</font></strong></td>
					<td width="87%"><html:text property="mesesCalculoMedio" size="2"
						maxlength="2" onkeyup="javascript:verificaNumeroInteiro(this);" />
					</td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>Dias M�nimo para Calcular
					Vencimento:</strong></td>
					<td><html:text maxlength="5" property="diasMinimoVencimento"
						size="2" onkeyup="javascript:verificaNumeroInteiro(this);"
						onblur="javascript:validarDias(document.forms[0].diasMinimoVencimento.value);" />
					</td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>Dias M�nimo para Calcular
					Vencimento se entrega pelos Correios:</strong></td>
					<td><html:text maxlength="2" property="diasMinimoVencimentoCorreio"
						size="2" onkeyup="javascript:verificaNumeroInteiro(this);"
						onblur="javascript:validarDias(document.forms[0].diasMinimoVencimentoCorreio.value);" />
					</td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>Dias para vencimento
					alternativo:</strong></td>
					<td><html:text property="diasVencimentoAlternativo" size="50"
						maxlength="83"
						onkeypress="return (isCampoNumerico(event) || event.keyCode == 59 || event.which == 59)" />
					</td>
				</tr>
				<tr>
					<td width="40%" align="left"><strong>N�mero de meses para validade
					da Conta:</strong></td>
					<td><html:text maxlength="2" property="numeroMesesValidadeConta"
						size="2" onkeyup="javascript:verificaNumeroInteiro(this);"
						onblur="validarMes(document.forms[0].numeroMesesValidadeConta.value);" />
					</td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>N�mero de meses para altera��o
					de um vencimento para outro:</strong></td>
					<td><html:text maxlength="2"
						property="numeroMesesAlteracaoVencimento" size="2"
						onkeyup="javascript:verificaNumeroInteiro(this);"
						onblur="validarMes(document.forms[0].numeroMesesAlteracaoVencimento.value);" />
					</td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>N�mero m�ximo de meses para
					retroagir o calculo da media:</strong> <font color="#FF0000">*</font>
					</td>
					<td><html:text maxlength="2"
						property="numeroMesesMaximoCalculoMedia" size="2"
						onkeyup="javascript:verificaNumeroInteiro(this);"
						 />
					</td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>N�mero de meses para calcular
					corre��o monet�ria:</strong> <font color="#FF0000">*</font></td>
					<td><html:text maxlength="2" property="numeroMesesCalculoCorrecao"
						size="2" onkeyup="javascript:verificaNumeroInteiro(this);"
						 />
					</td>
				</tr>
				
			   <tr>
					<td width="40%" align="left"><strong>N�mero m�ximo de meses para inserir conta antecipada:</strong></td>
					<td><html:text maxlength="2" property="numeroMaximoMesesInserirContaAntecipada"
						size="2" onkeyup="javascript:verificaNumeroInteiro(this);"
						 />
					</td>
				</tr>

				<tr>
					<td width="40%"><strong>Indicador Seguir Roteiro Empresa:</strong>
					</td>
					<td><strong> <html:radio property="indicadorRoteiroEmpresa"
						value="1" /> Sim <html:radio property="indicadorRoteiroEmpresa"
						value="2" /> N&atilde;o </strong></td>
				</tr>

				<tr>
					<td width="40%"><strong>Indicador Altera��o do Vencimento Mais de
					uma Vez:</strong></td>
					<td><strong> <html:radio
						property="indicadorLimiteAlteracaoVencimento" value="1" /> Sim <html:radio
						property="indicadorLimiteAlteracaoVencimento" value="2" />
					N&atilde;o </strong></td>
				</tr>

				<tr>
					<td width="40%"><strong>Indicador Tipo de Tarifa de Consumo: </strong>
					</td>
					<td><strong> <html:radio property="indicadorTarifaCategoria"
						value="1" /> Por Categoria <html:radio
						property="indicadorTarifaCategoria" value="2" /> Por SubCategoria
					</strong></td>
				</tr>

				<tr>
					<td width="40%"><strong>Indicador Atualiza�&atilde;o Tarif�ria: </strong>
					</td>
					<td><strong> <html:radio property="indicadorAtualizacaoTarifaria"
						value="1" /> Sim <html:radio
						property="indicadorAtualizacaoTarifaria" value="2" /> N&atilde;o
					</strong></td>
				</tr>

				<tr>
					<td width="40%"><strong>M�s e Ano de Atualiza��o Tarif�ria:</strong>
					<font color="#FF0000">*</font></td>
					<td><html:text property="mesAnoAtualizacaoTarifaria" size="7"
						maxlength="7" onkeyup="javascript:mascaraAnoMes(this,event);" 
						onkeypress="return isCampoNumerico(event);mascaraAnoMes(this, event);"/>
					<strong>mm/aaaa</strong></td>
				</tr>

				<tr>
					<td width="40%"><strong>Indicador de Faturamento Antecipado: </strong>
					</td>
					<td><strong> <html:radio property="indicadorFaturamentoAntecipado"
						value="1" /> Sim <html:radio
						property="indicadorFaturamentoAntecipado" value="2" /> N&atilde;o
					</strong></td>
				</tr>

				<tr>
					<td>&nbsp;</td>

				</tr>
				<tr>
					<td width="40%"><strong>Retificar com um valor Menor: </strong></td>
					<td><strong> <html:radio property="indicadorRetificacaoValorMenor"
						value="1" /> Sim <html:radio
						property="indicadorRetificacaoValorMenor" value="2" /> N&atilde;o
					</strong></td>
				</tr>


				<tr>
					<td width="40%"><strong>Transfer�ncia com d�bito: </strong></td>
					<td><strong> <html:radio property="indicadorTransferenciaComDebito"
						value="1" /> Sim <html:radio
						property="indicadorTransferenciaComDebito" value="2" />
					N&atilde;o </strong></td>
				</tr>

				<tr>
					<td width="40%"><strong>N�o Medido por Tarifa de Consumo: </strong>
					</td>
					<td><strong> <html:radio property="indicadorNaoMedidoTarifa"
						value="1" /> Sim <html:radio property="indicadorNaoMedidoTarifa"
						value="2" /> N&atilde;o </strong></td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong> Qtde de contas retificadas</strong>
					<font color="#FF0000">*</font></td>
					<td width="87%"><html:text property="qtdeContasRetificadas"
						size="9" maxlength="9"
						onkeyup="javascript:verificaNumeroInteiro(this);" /></td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>Quantidade de dias de varia��o
					de consumo:</strong> <font color="#FF0000">*</font></td>
					<td><html:text maxlength="3" property="numeroDiasVariacaoConsumo"
						size="3" onkeyup="javascript:verificaNumeroInteiro(this);" /></td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>Percentual do B�nus Social:</strong>
					<font color="#FF0000">*</font></td>
					<td><html:text maxlength="6" property="percentualBonusSocial"
						size="6" onkeyup="javascript:formataValorMonetario(this, 5);" />
					</td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>Indicador de bloqueio de
					recalculo e reemissao de conta na impressao simultanea:</strong> <font
						color="#FF0000">*</font></td>
					<td><strong> <html:radio property="indicadorBloqueioContaMobile"
						value="1" /> Sim <html:radio
						property="indicadorBloqueioContaMobile" value="2" /> N&atilde;o </strong>
					</td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>Mensagem Pedido Conta BRAILE:<font
						color="#FF0000"></font></strong></td>

					<td colspan="3"><span class="style2"> <strong> <html:textarea
						property="mensagemContaBraile" cols="40" rows="4"
						onkeyup="limitTextArea(document.forms[0].mensagemContaBraile, 500, document.getElementById('utilizado'), document.getElementById('limite'));" /><br>

					<strong> <span id="utilizado">0</span>/<span id="limite">500</span>
					</strong> </strong> </span></td>
				</tr>

				<tr>
					<td><strong>C�digo de Tipo de C�lculo de N�o Medido:<font
						color="#FF0000">*</font></strong></td>
					<td align="right" colspan="2">
					<div align="left"><span class="style2"> <html:select
						property="codigoTipoCalculoNaoMedido" tabindex="1">
						<html:option
							value="<%= ""+ConstantesSistema.NUMERO_NAO_INFORMADO %>">&nbsp;</html:option>
						<html:option value="1">AREA CONSTRUIDA</html:option>
						<html:option value="2">PONTOS DE UTILIZA��O</html:option>
						<html:option value="3">NUMERO DE MORADORES</html:option>
					</html:select> </span></div>
					</td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>N�mero de meses para retificar
					uma conta:</strong></td>
					<td><html:text maxlength="4" property="numeroMesesRetificarConta"
						size="4" onkeyup="javascript:verificaNumeroInteiro(this);" /></td>
				</tr>

				<tr>
					<td width="40%"><strong>Est� na Norma de Retifica��o da Conta: </strong>
					</td>
					<td><strong> <html:radio property="indicadorNormaRetificacao"
						value="1" /> Sim <html:radio property="indicadorNormaRetificacao"
						value="2" /> N&atilde;o </strong></td>
				</tr>

				<tr>
					<td colspan="2" align="center"><strong>Par�metros para Tarifa
					Social:</strong></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>Sal�rio M�nimo:</strong></td>
					<td><html:text maxlength="9" property="salarioMinimo" size="9"
						onkeyup="javascript:formataValorMonetario(this, 9);" /></td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>�rea M�xima do Im�vel para a
					Tarifa:</strong></td>
					<td><html:text maxlength="7" property="areaMaxima" size="7"
						onkeyup="javascript:verificaNumeroInteiro(this);" /></td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>Consumo de Energia M�xima para
					a Tarifa:</strong></td>
					<td><html:text maxlength="4" property="consumoMaximo" size="4"
						onkeyup="javascript:verificaNumeroInteiro(this);" /></td>
				</tr>

				<tr>
					<td width="40%" align="left"><strong>N�mero de dias de prazo para
					entrada de recurso do auto de infra��o:</strong> <font
						color="#FF0000">*</font></td>
					<td><html:text maxlength="4"
						property="nnDiasPrazoRecursoAutoInfracao" size="4"
						onkeyup="javascript:verificaNumeroInteiro(this);" /></td>
				</tr>
				
				<tr>
					<td width="40%" align="left">
						<strong>N�mero de vezes com consumo at� 10m� para suspender leitura:</strong>
					</td>
					
					<td><html:text property="numeroVezesSuspendeLeitura" maxlength="2"
						size="2" onkeyup="javascript:verificaNumeroInteiro(this);" />
					</td>
				</tr>

				<tr>
					<td width="40%" align="left">
						<strong>N�mero de meses para manter leitura suspensa:</strong>
					</td>
					
					<td><html:text property="numeroMesesLeituraSuspensa" maxlength="2"
						size="2" onkeyup="javascript:verificaNumeroInteiro(this);" />
					</td>
				</tr>

				<tr>
					<td width="40%" align="left">
						<strong>Intervalo de meses para considerar reincid�ncia de consumo at� 10m�:</strong>
					</td>
					
					<td><html:text property="numeroMesesReinicioSitEspFatu"
						maxlength="2" size="2"
						onkeyup="javascript:verificaNumeroInteiro(this);" /></td>
				</tr>
				
				<tr>
					<td width="40%" align="left">
						<strong>N�mero m�ximo de dias entre data liga��o e leitura para tratar como nova liga��o:</strong>
					</td>
					
					<td><html:text property="numeroMaximoDiasNovaLigacao"
						maxlength="3" size="3"
						onkeyup="javascript:verificaNumeroInteiro(this);" /></td>
				</tr>
				
				<tr>
					<td width="40%"><strong>Utiliza Tarifa de Simula��o para Histograma: </strong>
					</td>
					<td><strong> <html:radio property="indicadorUtilizaTarifaSimulacao"
						value="1" /> Sim <html:radio property="indicadorUtilizaTarifaSimulacao"
						value="2" /> N&atilde;o </strong></td>
				</tr>

				<tr>
					<td></td>
					<td><strong><font color="#FF0000">*</font></strong>Campos
					obrigat&oacute;rio</td>
				</tr>

				<tr>
					<td colspan="2">
					<div align="right"><jsp:include
						page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=2" /></div>
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
