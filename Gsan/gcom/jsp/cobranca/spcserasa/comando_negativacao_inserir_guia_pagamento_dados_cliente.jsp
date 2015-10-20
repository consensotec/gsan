<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<%@ include file="/jsp/util/telaespera.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script src="<bean:message key="caminho.js"/>util.js" ></script>
<script src="<bean:message key="caminho.js"/>Calendario.js" ></script>
<script src="<bean:message key="caminho.js"/>jquery/jquery.js"></script>
<script src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="InserirComandoNegativacaoActionForm" dynamicJavascript="false" />

<script language="JavaScript">
	function validateInserirComandoNegativacaoActionForm(form) {
		var DATA_ATUAL = document.getElementById("DATA_ATUAL").value;
		var REFERENCIA_DI = form.referenciaInicial.value.substring(3,7) + form.referenciaInicial.value.substring(0,2);
		var REFERENCIA_DF = form.referenciaFinal.value.substring(3,7) + form.referenciaFinal.value.substring(0,2);
		var REFERENCIA_A = DATA_ATUAL.substring(6,10) + DATA_ATUAL.substring(3,5);
		var MES_ANO_C = DATA_ATUAL.substring(3,5) + DATA_ATUAL.substring(5,6) + DATA_ATUAL.substring(6,10);

		if(validaMesAno(form.referenciaInicial) && validaMesAno(form.referenciaFinal)){
			if (comparaData("01/"+form.referenciaInicial.value, ">", "01/" + form.referenciaFinal.value )){
			  	alert('Referência Final do Período é anterior à Referência Inicial do Período.');
			}else if (comparaData(form.dataVencimentoInicial.value, ">", form.dataVencimentoFinal.value)){
			  alert('Data Final do Período é anterior à Data Inicial do Período.');
			}else if (REFERENCIA_A < REFERENCIA_DI){
			    alert("Mês/Ano Inicial do Período de Referência posterior ao Mês e Ano corrente " + MES_ANO_C + ".");
		    } else if (REFERENCIA_A < REFERENCIA_DF){
			    alert("Mês/Ano Final do Período de Referência posterior ao Mês e Ano corrente " + MES_ANO_C + ".");
		    }else if (comparaData(form.dataVencimentoInicial.value, ">", DATA_ATUAL )){
				alert("Data Inicial do Período de Vencimento Débito posterior à Data corrente " + DATA_ATUAL + ".");
			}else if (comparaData(form.dataVencimentoFinal.value, ">", DATA_ATUAL )){
				alert("Data Final do Período de Vencimento Débito posterior à Data corrente " + DATA_ATUAL + ".");
			}else if((obterNumerosSemVirgulaEPonto(form.valorDebitoInicial.value))*1 > (obterNumerosSemVirgulaEPonto(form.valorDebitoFinal.value))*1){
			    alert("Valor do Débito Final é menor que o Valor do Débito Inicial");
			}else{
				return true;
			}

		  	return false;
	    }
	}

	function validaMesAno(mesAno){	
		if(mesAno.value == "") return true;
		return verificaAnoMesMensagemPersonalizada(mesAno,"Referência inválida");
	}

	function validarPeriodoVencimento(data){
		if (data.value.length == 0) return true;
		return verificaDataMensagemPersonalizada(data, "Período de Vencimentodo Débito inválido.");				
	}

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro,	tipoConsulta) {
		var form = document.forms[0];

		if (tipoConsulta == 'cliente') {
			form.idCliente.value = codigoRegistro;
			form.descricaoCliente.value = descricaoRegistro;
			form.descricaoCliente.style.color = "#000000";
		}
	}
</script>

</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">

<div id="formDiv">
<html:form action="/inserirComandoNegativacaoPorGuiaPagamentoWizardAction" method="post">

<html:hidden property="idNegativador"/>

<INPUT TYPE="hidden" ID="DATA_ATUAL" value="${requestScope.dataAtual}" />

<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=2"/>


<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

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

      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg"><font style="font-size: 9px;">ICN - Por Guia de Pagamento de Cliente - Dados do Cliente</font></td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>

      <table width="100%" border="0">
      <tr>
      	<td colspan="3">Para determinar a negativação a ser comandada, informe os dados abaixo:</td>
      </tr>
      <tr>
      	<td width="35%"><strong>Negativador:</strong></td>
       	<td colspan="2">
			<html:text property="nomeNegativador" size="50" maxlength="60" tabindex="4" readonly="true"
				style="background-color:#EFEFEF; border:0; color: #000000"/>
		</td>
	  </tr>
	  <tr>
         <td colspan="3"><hr></td>
      </tr>
	  <tr>
		<td height="10" width="35%">
			<div class="style9"><strong>Período de Referência do Débito:</strong></div>
		</td>
		<td>
			<html:text property="referenciaInicial" size="8" maxlength="7" onkeyup="mascaraAnoMes(this, event);"
				onkeypress="return isCampoNumerico(event);" onblur="validaMesAno(document.forms[0].referenciaInicial);"/>
			a
			<html:text property="referenciaFinal" size="8" maxlength="7" onkeyup="mascaraAnoMes(this, event);"
				onkeypress="return isCampoNumerico(event);" onblur="validaMesAno(document.forms[0].referenciaFinal);"/>
			mm/aaaa
		</td>
	  </tr>
	  <tr>
		<td width="35%"><strong>Período de Vencimento do Débito:</strong></td>
		<td>
			<html:text property="dataVencimentoInicial" size="10" maxlength="10" tabindex="2" onkeyup="mascaraData(this, event);"
				onblur="validarPeriodoVencimento(document.forms[0].dataVencimentoInicial);"/>
			<a href="javascript:abrirCalendarioReplicando('InserirComandoNegativacaoActionForm', 'dataVencimentoInicial', 'dataVencimentoFinal');">
				<img border="0"	src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário"/></a>
			a
			<html:text property="dataVencimentoFinal" size="10" maxlength="10" tabindex="3" onkeyup="mascaraData(this, event);"
				onkeypress="return isCampoNumerico(event);" onblur="validarPeriodoVencimento(document.forms[0].dataVencimentoFinal);"/>
			<a href="javascript:abrirCalendario('InserirComandoNegativacaoActionForm', 'dataVencimentoFinal')">
				<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário"/></a>
			dd/mm/aaaa
		</td>
	  </tr>	  
	  <tr>
		<td height="10" width="35%">
			<div class="style9"><strong>Valor do Débito:</strong></div>
		</td>
		<td>
			<html:text property="valorDebitoInicial" size="17" maxlength="17" onkeyup="formataValorMonetario(this, 13)"/>
			a
			<html:text property="valorDebitoFinal" size="17" maxlength="17" onkeyup="formataValorMonetario(this, 13)"/>
		</td>
	  </tr>
      <tr>
         <td colspan="3"><hr></td>
      </tr>
      <tr>
		<td width="30%"><strong>Cliente:</strong></td>
		<td>
			<html:text property="idCliente" styleId="idCliente" size="8" maxlength="8" tabindex="2"
				onkeypress="validaEnterComMensagem(event,
					'inserirComandoNegativacaoPorGuiaPagamentoWizardAction.do?action=exibirInserirComandoNegativacaoPorGuiaPagamentoDadosClienteAction',
					'idCliente','Cliente');return isCampoNumerico(event);" />

			<a href="javascript:abrirPopup('exibirPesquisarClienteAction.do');">
				<img src="/gsan/imagens/pesquisa.gif" alt="Pesquisar Cliente" border="0" height="21" width="23"></a> 

			<logic:empty name="InserirComandoNegativacaoActionForm"	property="idCliente">
				<html:text property="descricaoCliente" styleId="descricaoCliente" size="38" readonly="true"
					style="background-color:#EFEFEF; border:0; color: #ff0000" />
			</logic:empty>

			<logic:notEmpty name="InserirComandoNegativacaoActionForm" property="idCliente">
				<html:text property="descricaoCliente" styleId="descricaoCliente" size="38" readonly="true"
					style="background-color:#EFEFEF; border:0; color: #000000" />
			</logic:notEmpty>

			<a id="limparCliente" href="#">
				<img border="0" title="Apagar" src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
		</td>
	  </tr>
	  <tr> 
        <td width="30%"><strong>Tipo de Cliente:</strong></td>
        <td colspan="6">
        	<span class="style2">
	        	<strong>   
					<html:select property="tipoCliente" styleId="tipoCliente" style="width: 350px; height:100px;" multiple="true"> 
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:notPresent name="desabilitar">
							<logic:present name="colecaoTipoCliente" scope="session">
								<html:options collection="colecaoTipoCliente" labelProperty="descricao" property="id" />
							</logic:present>
						</logic:notPresent>
					</html:select>  
				</strong>
			</span>
		</td>
      </tr>  
      <tr>
         <td colspan="3"><hr></td>
      </tr>
      <tr>
      	<td colspan="3" height="10"></td>
      </tr>
      <tr>
        <td colspan="3">
			<div align="right">
				<jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_tela_espera.jsp?numeroPagina=2" />
			</div>
		</td>
      </tr>
      </table>
      <p>&nbsp;</p>
	</td>
  </tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</div>

<script>
$(function() {
	$idCliente = $('#idCliente');
	$tipoCliente = $('#tipoCliente');
	$descricaoCliente = $('#descricaoCliente');

	$('input[name=desfazer]').click(function() {
		document.forms[0].action = 'exibirInserirComandoNegativacaoPorGuiaPagamentoAction.do?desfazer=true';
		document.forms[0].submit();
	});

	// Ao carregar a tela, se cliente preenchido, limpar e bloquear tipo cliente 
	if($idCliente.val()) {
		$tipoCliente.val(-1).attr('disabled', true);
	}

	// Ao carregar a tela, se tipo cliente preenchido, limpar e bloquear cliente
	if($tipoCliente.val() && $tipoCliente.val() != -1) {
		$idCliente.attr('readonly', true);
		$idCliente.css('background-color', '#EFEFEF');
		$idCliente.css('border', '0');
	}

	// Ao prencher o cliente, limpar e bloquear o tipo cliente
	$idCliente.bind('input propertychange paste', function() {
		$tipoCliente.val(-1).attr('disabled', $idCliente.val());
		$descricaoCliente.val('');
	});

	// Ao prencher o tipo cliente, limpar e bloquear o cliente
	$tipoCliente.change(function() {
		$idCliente.val('');
		if($tipoCliente.val() && $tipoCliente.val() != -1) {
			$idCliente.attr('readonly', true);
			$idCliente.css('background-color', '#EFEFEF');
			$idCliente.css('border', '0');
		} else {
			$idCliente.attr('readonly', false);
			$idCliente.css('background-color', '');
			$idCliente.css('border', '');
		}
	});

	// Borracha cliente
	$('#limparCliente').click(function() {
		$idCliente.val('').focus();
		$descricaoCliente.val('');
		$tipoCliente.attr('disabled', false);
	});
});
</script>

</body>
</html:html>
