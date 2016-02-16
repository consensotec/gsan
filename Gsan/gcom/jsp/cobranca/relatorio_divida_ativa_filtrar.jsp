<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script src="<bean:message key="caminho.js"/>jquery/jquery.js"></script>
<html:javascript staticJavascript="false"  formName="FiltrarRelatorioDividaAtivaActionForm" />

<script LANGUAGE="JavaScript">
	
	function validarForm(form){
		if(validateFiltrarRelatorioDividaAtivaActionForm(form)){

			validaMesAno(form.periodoInscricaoInicial, form.periodoInscricaoFinal, "Período de Inscrição");
			validaMesAno(form.periodoAtualizacaoInicial, form.periodoAtualizacaoFinal, "Período de Amortização");

			if(form.indicadorTipoRelatorio[0].checked){
				if (form.indicadorRelatorioSinteticoAnalitico[0].checked || 
					form.indicadorRelatorioSinteticoAnalitico[1].checked){
					botaoAvancarTelaEspera('/gsan/gerarRelatorioDividaAtivaAmortizadaAction.do');
				}else{
					alert("Selecione Relatório Analítico ou Sintético.")
				}
			}else{
				botaoAvancarTelaEspera('/gsan/gerarRelatorioDividaAtivaParceladaAction.do');
			}
		}
	}
	
	function limpaForm(form){
		form.indicadorTipoRelatorio[0].checked = true;
		form.indicadorIntra[0].checked = true;
		form.periodoInscricaoInicial.value = "";
		form.periodoInscricaoFinal.value = "";
		form.periodoAtualizacaoInicial.value = "";
		form.periodoAtualizacaoFinal.value = "";
		form.idImovel.value = "";
		form.inscricaoImovel.value = "";
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];
	    if(tipoConsulta == 'imovel'){
	    	form.idImovel.value = codigoRegistro;
	      	form.inscricaoImovel.value = descricaoRegistro;
	    }
	     
	}

	function limparImovel(form){
		form.idImovel.value = "";
		form.inscricaoImovel.value = "";
	}

	function verificarBloqueioPeriodoAmortizacao() {
		var form = document.forms[0];

		if (form.indicadorTipoRelatorio[1].checked) {
			form.periodoAtualizacaoInicial.value = "";
			form.periodoAtualizacaoFinal.value = "";
			form.periodoAtualizacaoInicial.readOnly = true;
			form.periodoAtualizacaoFinal.readOnly = true; 
		} else {
			form.periodoAtualizacaoInicial.readOnly = false;
			form.periodoAtualizacaoFinal.readOnly = false;
		}
	}
	
	
</script>
</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">

<div id="formDiv">
<html:form action="/exibirFiltrarRelatorioDividaAtivaAction"
	name="FiltrarRelatorioDividaAtivaActionForm"
	type="gcom.gui.cobranca.FiltrarRelatorioDividaAtivaActionForm"
	onsubmit="return validateExibirFiltrarRelatorioDividaAtivaActionForm(this);">

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="140" valign="top" class="leftcoltext">
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
          <td class="parabg">Filtrar Relatório Dívida Ativa</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>

      <table width="100%" border="0">
      <tr>
      	<td colspan="3">Para gerar o relatório, informe os dados abaixo: </td>
      </tr>	
     
	<tr>
		<td><strong>Tipo do Relatório:<font color="FF0000">*</font></strong></td>
		<td>
			<html:radio property="indicadorTipoRelatorio" value="1" tabindex="1" onchange="verificarBloqueioPeriodoAmortizacao()" />
				<strong>Amortização</strong> 
			<html:radio	property="indicadorTipoRelatorio" value="2" tabindex="2" onchange="verificarBloqueioPeriodoAmortizacao()" />
				<strong>Parcelamento</strong>
		</td>
		<td>&nbsp;</td>
	</tr>
     
     <tr>
		<td ><strong>Indicador de Intra:<font color="FF0000">*</font></strong></td>
		<td>
			<html:radio property="indicadorIntra" value="1" tabindex="1" /><strong>Sim</strong> 
			<html:radio	property="indicadorIntra" value="2" tabindex="2" /><strong>Não</strong>
			<html:radio	property="indicadorIntra" value="3" tabindex="3" /><strong>Ambos</strong>
		</td>
		<td>&nbsp;</td>
	</tr>
	
	<tr id="divRelatorioAnaliticoSintetico">
		<td>
			<strong>Tipo de Relatório :<font color="FF0000">*</font></strong>
		</td>
		<td>
			<html:radio property="indicadorRelatorioSinteticoAnalitico" value="1" tabindex="3" /><strong>Analítico</strong> 
			<html:radio	property="indicadorRelatorioSinteticoAnalitico" value="2" tabindex="4" /><strong>Sintético</strong>
		</td>
		<td>&nbsp;</td>
	</tr>
	
   	 <tr>
		<td height="0"><strong>Per&iacute;odo de Inscrição em Dívida Ativa:</strong></td>
		<td colspan="3">
			<strong> 
				<html:text maxlength="7"
				property="periodoInscricaoInicial" size="7"
				tabindex="5"
				onkeypress="return isCampoNumerico(event);"
				onkeyup="mascaraAnoMes(this, event); replicaDados(document.forms[0].periodoInscricaoInicial, document.forms[0].periodoInscricaoFinal);" onfocus="replicaDados(document.forms[0].periodoInscricaoInicial, document.forms[0].periodoInscricaoFinal);" /> 
			 <strong> a</strong> 
				 <html:text
					maxlength="7" property="periodoInscricaoFinal" size="7"
					tabindex="6"
					onkeypress="return isCampoNumerico(event);"
					onkeyup="mascaraAnoMes(this, event);" /> 
			</strong> mm/aaaa
		</td>
	 </tr>
	 
	 <tr>
		<td height="0"><strong>Per&iacute;odo de Amortização:</strong></td>
		<td colspan="3">
			<strong> 
				<html:text maxlength="7"
				property="periodoAtualizacaoInicial" size="7"
				tabindex="7"
				onkeypress="return isCampoNumerico(event);"
				onkeyup="mascaraAnoMes(this, event); replicaDados(document.forms[0].periodoAtualizacaoInicial, document.forms[0].periodoAtualizacaoFinal);" onfocus="replicaDados(document.forms[0].periodoAtualizacaoInicial, document.forms[0].periodoAtualizacaoFinal);" /> 
			 <strong> a</strong> 
				 <html:text
					maxlength="7" property="periodoAtualizacaoFinal" size="7"
					tabindex="8"
					onkeypress="return isCampoNumerico(event);"
					onkeyup="mascaraAnoMes(this, event);" /> 
			</strong> mm/aaaa
		</td>
	 </tr>
	 
	<tr>
		<td width="12%"><strong>Imóvel:</strong></td>
		<td><html:text maxlength="9"
			property="idImovel" size="10"
			onkeypress="validaEnter(event, 'exibirFiltrarRelatorioDividaAtivaAction.do?tipoConsulta=1', 'idImovel');" />

		<a
			href="javascript:abrirPopup('exibirPesquisarImovelAction.do', 400, 800);">
		<img width="23" height="21" border="0"
			src="<bean:message key="caminho.imagens"/>pesquisa.gif"
			title="Pesquisar Imóvel" /></a><html:text
			property="inscricaoImovel" readonly="true"
			style="background-color:#EFEFEF; border:0; color: ${corTexto}"
			size="30" /><a href="javascript:limparImovel(document.forms[0]);"><img
			src="<bean:message key="caminho.imagens"/>limparcampo.gif"
			border="0" title="Apagar Imóvel" /> </a></td>
	</tr>      
          
  	  <tr>
		<td colspan="3">&nbsp;</td>
	 </tr>
	 <tr>
	   <td colspan="3" align="right">
	     <table border="0" width="100%">
		   <tr>
		   <td colspan="2">
				<input name="Submit22"
					class="bottonRightCol" 
					value="Limpar" 
					type="button"
					onclick="limpaForm(document.forms[0]);">
				<input type="button"
					onclick="javascript:window.location.href='/gsan/telaPrincipal.do'" 
					value="Cancelar" 
					class="bottonRightCol" 
					name="ButtonCancelar"> 			
			</td>
					
			<td align="right" width="50%">					
			 <input name="Button322222" type="button"
				class="bottonRightCol" value="Gerar"
				onClick="javascript:validarForm(document.forms[0]);" />
			</td>
		  </tr>
		</table>
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
<%@ include file="/jsp/util/telaespera.jsp"%>

<script>
	$(function() {
		verificaRelatorioAnaliticoSintetico();
	});

	$('input[type=radio][name=indicadorTipoRelatorio]').change(function() {
		verificaRelatorioAnaliticoSintetico();
	});
	
	function verificaRelatorioAnaliticoSintetico(){
		if ($('input:radio[name=indicadorTipoRelatorio]:checked').val() == "1"){
			$("#divRelatorioAnaliticoSintetico").show();
		}else{			
			$('input[name=indicadorRelatorioSinteticoAnalitico]').attr('checked', '');
			$('#divRelatorioAnaliticoSintetico').hide();
		}
	}
</script>
</body>
</html:html>
