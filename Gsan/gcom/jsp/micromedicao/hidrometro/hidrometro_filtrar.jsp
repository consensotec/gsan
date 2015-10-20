<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ page import="gcom.micromedicao.hidrometro.Hidrometro"%>
<%@ page import="gcom.util.ConstantesSistema"%>

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
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="HidrometroActionForm" dynamicJavascript="false" />
<script type="text/javascript" language="Javascript">
//<!-- Begin
     var bCancel = false;

    function validateHidrometroActionForm(form) {
        if (bCancel)
      return true;
        else
       return validateCaracterEspecial(form) && validateDate(form) && validateLong(form);
   }

    function caracteresespeciais () {
     this.aa = new Array("numeroHidrometro", "Número do Hidrômetro possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("dataAquisicao", "Data de Aquisição possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("anoFabricacao", "Ano de Fabricação possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("idLocalArmazenagem", "Local de Armazenagem possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ae = new Array("fixo", "Fixo da Numeração dos Hidrômetros possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.af = new Array("faixaInicial", "Faixa Inicial da Numeração dos Hidrômetros possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ag = new Array("faixaFinal", "Faixa Final da Numeração dos Hidrômetros possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	 this.ai = new Array("tempoGarantiaAnos", "Tempo de Garantia em Anos deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }

	function DateValidations () {
    	this.aa = new Array("dataAquisicao", "Data de Aquisição inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
    }

    function IntegerValidations () {
     this.aa = new Array("anoFabricacao", "Ano de Fabricação deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("idLocalArmazenagem", "Local de Armazenagem deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ac = new Array("faixaInicial", "Faixa Inicial da Numeração dos Hidrômetros deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("faixaFinal", "Faixa Final da Numeração dos Hidrômetros deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
     this.ah = new Array("notaFiscal", "Nota Fiscal deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
   	 this.ai = new Array("tempoGarantiaAnos", "Tempo de Garantia em Anos deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
    }


function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.HidrometroActionForm;

    if (tipoConsulta == 'hidrometroLocalArmazenagem') {
      form.idLocalArmazenagem.value = codigoRegistro;
      form.localArmazenagemDescricao.value = descricaoRegistro;
      form.localArmazenagemDescricao.style.color = "#000000";

    }
    
    if (tipoConsulta == 'localidade') {
      form.idLocalidade.value = codigoRegistro;
      form.nomeLocalidade.value = descricaoRegistro;
	  form.nomeLocalidade.style.color = "#000000";
	}
	  
	if (tipoConsulta == 'setorComercial') {
      form.codigoSetorComercial.value = codigoRegistro;
      form.nomeSetorComercial.value = descricaoRegistro;
	  form.nomeSetorComercial.style.color = "#000000";
	}
	  
	  
  }

 function limparPesquisaLocalArmazenagem() {
    var form = document.HidrometroActionForm;

    form.idLocalArmazenagem.value = "";
    form.localArmazenagemDescricao.value = "";

  }



function validarForm(form){
    
	if (validateHidrometroActionForm(form) && testarCampoValorZero(form.anoFabricacao, 'Data Fabricação') && testarCampoValorZero(form.idLocalArmazenagem, 'Local de Armazenamento') )
	{
    	if(form.numeroHidrometro.value == "" && form.dataAquisicao.value =="" && form.anoFabricacao.value =="" && document.getElementById("indicadorOperacionalAUX").value == "" && form.idHidrometroClasseMetrologica.value =="-1" &&
             form.idHidrometroMarca.value =="-1" && form.idHidrometroDiametro.value =="-1" && form.idHidrometroCapacidade.value =="-1" && form.idHidrometroTipo.value =="-1" && form.idLocalArmazenagem.value =="")
        {
        	if(form.fixo.value != "" && form.faixaInicial.value != "" && form.faixaFinal.value != "" )
        	{  
            	if(form.faixaInicial.value > 0 && form.faixaFinal.value > 0)
            	{
            	    var intervalo = (form.faixaFinal.value - form.faixaInicial.value) + 1;
                	if(intervalo > 0)
                	{
                  		submeterFormPadrao(form);
                 	}else{
                 		alert("Faixa Final da Numeração dos Hidrômetros deve ser maior ou igual a Faixa Inicial.");
                 		form.faixaFinal.focus();
                 	}
               	}else{
                	alert("Faixa Inicial e Faixa Final da Numeração dos Hidrômetros devem somente conter números positivos.");
                	form.faixaInicial.focus();
               	}
            }else{
            	if(form.faixaInicial.value != "" && form.faixaFinal.value != "" && form.fixo.value == "")
            	{
               		alert("O preenchimento do campo Fixo da Numeração dos Hidrômetros é obrigatório, caso Faixa Inicial e Faixa Final sejam informadas.");
               		form.fixo.focus();
               		return false;
               	}
               	if(form.faixaInicial.value != "" && form.faixaFinal.value == "" )
               	{
                	alert("O preenchimento do campo Faixa Final da Numeração dos Hidrômetros é obrigatório, caso Faixa Inicial seja informada.");
                	form.faixaFinal.focus();
                	return false;
               	}
               	if(form.faixaInicial.value == "" && form.faixaFinal.value != "" ){
                	alert("O preenchimento do campo Faixa Inicial da Numeração dos Hidrômetros é obrigatório, caso Faixa Final seja informada.");
                	form.faixaInicial.focus();
                	return false;
               	}
               	if(form.faixaInicial.value == "" && form.faixaFinal.value == "" && form.fixo.value != ""){
                	alert("O preenchimento dos campos Faixa Inicial e Final da Numeração dos Hidrômetros é obrigatório, caso Fixo seja informado.");
                	form.faixaInicial.focus();
                	return false;
               	}
               	submeterFormPadrao(form);
            }
         }else{
         	if(form.fixo.value != "" || form.faixaInicial.value != "" || form.faixaFinal.value != "" || form.tombamento.value != "")
         	{
            	alert('Preencher somente características ou numeração');
                return false;
            }else{
                submeterFormPadrao(form);
            }
        }
	}
}

//Verifica o valor do objeto radio em tempo de execução
function verificarMarcacao(valor){
  document.getElementById("indicadorOperacionalAUX").value = valor;
}

function limparForm(){
	var form = document.HidrometroActionForm;

    form.tombamento.value = "";
	form.numeroHidrometro.value = "";
	form.dataAquisicao.value = "";
	form.anoFabricacao.value = "";
	form.indicadorOperacional[2].checked = true;
	form.idHidrometroClasseMetrologica.value = -1;
	form.idHidrometroMarca.value = -1;
	form.idHidrometroDiametro.value = -1;
	form.idHidrometroCapacidade.value = -1;
	form.idHidrometroTipo.value = -1;
	form.idLocalArmazenagem.value = "";
	form.localArmazenagemDescricao.value = "";
    form.idHidrometroSituacao.value = -1;
	form.fixo.value = "";
	form.faixaInicial.value = "";
	form.faixaFinal.value = "";
	form.numeroHidrometro.focus();
	form.vazaoTransicao.value = "";
	form.vazaoNominal.value = "";
	form.vazaoMinima.value = "";
	form.notaFiscal.value = "";	
	form.tempoGarantiaAnos.value = "";	
	form.idHidrometroRelojoaria.value = "-1";
	form.idHidrometroFatorCorrecao.value = "-1";
	form.idHidrometroClassePressao.value = "-1";
	limparLocalidade();
	limparSetorComercial();
	limparQuadra();
	form.indicadorMacromedidor[0].checked = false;
	form.indicadorMacromedidor[1].checked = true;
	bloqueiaDados();
}

	function chamarReload(){
	var form = document.HidrometroActionForm;
	form.action = 'exibirFiltrarHidrometroAction.do?situacao=sim';
    form.submit();
	
	}
	
	function recarregar(){
		var form = document.HidrometroActionForm;
		form.action = 'exibirFiltrarHidrometroActionMovimentarHidrometro.do?tela=movimentarHidrometro&situacao=sim';
	    form.submit();
	}


	function limparLocalidade(){
	var form = document.HidrometroActionForm;
	form.idLocalidade.value = '';
	form.nomeLocalidade.value = '';
	limparSetorComercial();
	limparQuadra();
	
	}
	
	function limparSetorComercial(){
	var form = document.HidrometroActionForm;
	form.idSetorComercial.value = '';
	form.codigoSetorComercial.value = '';
	form.nomeSetorComercial.value = '';
	
	
	}
	
	function limparQuadra(){
	var form = document.HidrometroActionForm;
	form.numeroQuadra.value = '';
	form.idQuadra.value = '';
	
	}
	
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,campo){
	if(!campo.disabled){
	  if (objeto == null || codigoObjeto == null){
	     if(tipo == "" ){
	      abrirPopup(url,altura, largura);
	     }else{
		  abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		 }
	 }else{
		if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
			alert(msg);
		}
		else{
			abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
		}
	}
  }
}

	function bloqueiaDados(){
	
		var form = document.forms[0];
		
		if (form.indicadorMacromedidor[0].checked == false
				&& form.indicadorMacromedidor[1].checked == false
				&& form.indicadorMacromedidor[2].checked == false) {
			
			form.indicadorMacromedidor[1].checked = true;
		}
		
		if(form.indicadorMacromedidor[0].checked == true){
			
			form.fixo.value = "";
			form.fixo.style.color = "#000000";
			form.fixo.readOnly = true;
			form.fixo.style.backgroundColor = '#EFEFEF';
			
			form.faixaInicial.value = "";
			form.faixaInicial.style.color = "#000000";
			form.faixaInicial.readOnly = true;
			form.faixaInicial.style.backgroundColor = '#EFEFEF';
			
			form.faixaFinal.value = "";
			form.faixaFinal.style.color = "#000000";
			form.faixaFinal.readOnly = true;
			form.faixaFinal.style.backgroundColor = '#EFEFEF';
			
			form.anoFabricacao.style.color = "#000000";
			form.anoFabricacao.readOnly = false;
			form.anoFabricacao.style.backgroundColor = '';
			
			form.idHidrometroTipo.value = "-1";
			form.idHidrometroTipo.style.color = "#000000";
			form.idHidrometroTipo.disabled = true;
			form.idHidrometroTipo.readOnly = true;
			form.idHidrometroTipo.style.backgroundColor = '#EFEFEF';
			
			form.idHidrometroRelojoaria.value = "-1";
			form.idHidrometroRelojoaria.style.color = "#000000";
			form.idHidrometroRelojoaria.disabled = true;
			form.idHidrometroRelojoaria.readOnly = true;
			form.idHidrometroRelojoaria.style.backgroundColor = '#EFEFEF';

			form.idHidrometroFatorCorrecao.style.color = "#000000";
			form.idHidrometroFatorCorrecao.disabled = false;
			form.idHidrometroFatorCorrecao.readOnly = false;
			form.idHidrometroFatorCorrecao.style.backgroundColor = '';

			form.idHidrometroClassePressao.style.color = "#000000";
			form.idHidrometroClassePressao.disabled = false;
			form.idHidrometroClassePressao.readOnly = false;
			form.idHidrometroClassePressao.style.backgroundColor = '';
		}
		
		if(form.indicadorMacromedidor[0].checked == false){

			form.fixo.style.color = "#000000";
			form.fixo.readOnly = false;
			form.fixo.style.backgroundColor = '';

			form.faixaInicial.style.color = "#000000";
			form.faixaInicial.readOnly = false;
			form.faixaInicial.style.backgroundColor = '';

			form.faixaFinal.style.color = "#000000";
			form.faixaFinal.readOnly = false;
			form.faixaFinal.style.backgroundColor = '';

			form.anoFabricacao.value = "";
			form.anoFabricacao.style.color = "#000000";
			form.anoFabricacao.readOnly = true;
			form.anoFabricacao.style.backgroundColor = '#EFEFEF';

			form.idHidrometroTipo.style.color = "#000000";
			form.idHidrometroTipo.disabled = false;
			form.idHidrometroTipo.readOnly = false;
			form.idHidrometroTipo.style.backgroundColor = '';

			form.idHidrometroRelojoaria.style.color = "#000000";
			form.idHidrometroRelojoaria.disabled = false;
			form.idHidrometroRelojoaria.readOnly = false;
			form.idHidrometroRelojoaria.style.backgroundColor = '';
			
			form.idHidrometroFatorCorrecao.value = "-1";
			form.idHidrometroFatorCorrecao.style.color = "#000000";
			form.idHidrometroFatorCorrecao.disabled = true;
			form.idHidrometroFatorCorrecao.readOnly = true;
			form.idHidrometroFatorCorrecao.style.backgroundColor = '#EFEFEF';
			
			form.idHidrometroClassePressao.value = "-1";
			form.idHidrometroClassePressao.style.color = "#000000";
			form.idHidrometroClassePressao.disabled = true;
			form.idHidrometroClassePressao.readOnly = true;
			form.idHidrometroClassePressao.style.backgroundColor = '#EFEFEF';
		}

		if(form.indicadorMacromedidor[2].checked == true){
			
			form.fixo.value = "";
			form.fixo.style.color = "#000000";
			form.fixo.readOnly = true;
			form.fixo.style.backgroundColor = '#EFEFEF';
			
			form.faixaInicial.value = "";
			form.faixaInicial.style.color = "#000000";
			form.faixaInicial.readOnly = true;
			form.faixaInicial.style.backgroundColor = '#EFEFEF';
			
			form.faixaFinal.value = "";
			form.faixaFinal.style.color = "#000000";
			form.faixaFinal.readOnly = true;
			form.faixaFinal.style.backgroundColor = '#EFEFEF';
			
			form.anoFabricacao.style.color = "#000000";
			form.anoFabricacao.readOnly = false;
			form.anoFabricacao.style.backgroundColor = '';
			
			form.idHidrometroTipo.value = "-1";
			form.idHidrometroTipo.style.color = "#000000";
			form.idHidrometroTipo.disabled = true;
			form.idHidrometroTipo.readOnly = true;
			form.idHidrometroTipo.style.backgroundColor = '#EFEFEF';
			
			form.idHidrometroRelojoaria.value = "-1";
			form.idHidrometroRelojoaria.style.color = "#000000";
			form.idHidrometroRelojoaria.disabled = true;
			form.idHidrometroRelojoaria.readOnly = true;
			form.idHidrometroRelojoaria.style.backgroundColor = '#EFEFEF';

			form.idHidrometroFatorCorrecao.style.color = "#000000";
			form.idHidrometroFatorCorrecao.disabled = false;
			form.idHidrometroFatorCorrecao.readOnly = false;
			form.idHidrometroFatorCorrecao.style.backgroundColor = '';

			form.idHidrometroClassePressao.style.color = "#000000";
			form.idHidrometroClassePressao.disabled = false;
			form.idHidrometroClassePressao.readOnly = false;
			form.idHidrometroClassePressao.style.backgroundColor = '';
		}
	}
//-->
</script>


</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}');bloqueiaDados();">
<html:form action="/filtrarHidrometroAction.do"
	name="HidrometroActionForm"
	type="gcom.gui.micromedicao.hidrometro.HidrometroActionForm"
	method="post" onsubmit="return validateHidrometroActionForm(this);">

	<input type="hidden" id="indicadorOperacionalAUX" value="" />

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellpadding="0" cellspacing="5">
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
			<td valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img
						src="<bean:message key="caminho.imagens"/>parahead_left.gif"
						border="0" /></td>
					<td class="parabg">Filtrar Hidr&ocirc;metro</td>
					<td width="11" valign="top"><img
						src="<bean:message key="caminho.imagens"/>parahead_right.gif"
						border="0" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td>Para filtrar o(s) hidr&ocirc;metro(s), informe as
					caracter&iacute;stica(s) ou a numera&ccedil;&atilde;o do(s)
					hidr&ocirc;metro(s):</td>
					<td width="84"><input name="atualizar" type="checkbox"
						checked="checked" value="1"> <strong>Atualizar</strong></td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}micromedicaoHidrometroFiltrar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">
						<strong> <span class="style1">
							<logic:present name="tela" >
								<html:radio property="indicadorMacromedidor"
									value="1" onchange="bloqueiaDados();redirecionarSubmit('exibirFiltrarHidrometroActionMovimentarHidrometro.do?tela=movimentarHidrometro');"/>&nbsp;Macromedidor&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio property="indicadorMacromedidor"
									value="2" onchange="bloqueiaDados();redirecionarSubmit('exibirFiltrarHidrometroActionMovimentarHidrometro.do?tela=movimentarHidrometro');"/>&nbsp;Micromedidor&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio property="indicadorMacromedidor"
									value="3" onchange="bloqueiaDados();redirecionarSubmit('exibirFiltrarHidrometroActionMovimentarHidrometro.do?tela=movimentarHidrometro');"/>&nbsp;Medição de Esgoto
							</logic:present>
							
							<logic:notPresent name="tela" >
								<html:radio property="indicadorMacromedidor"
									value="1" onchange="bloqueiaDados();redirecionarSubmit('exibirFiltrarHidrometroAction.do');"/>&nbsp;Macromedidor&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio property="indicadorMacromedidor"
									value="2" onchange="bloqueiaDados();redirecionarSubmit('exibirFiltrarHidrometroAction.do');"/>&nbsp;Micromedidor&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio property="indicadorMacromedidor"
									value="3" onchange="bloqueiaDados();redirecionarSubmit('exibirFiltrarHidrometroAction.do');"/>&nbsp;Medição de Esgoto
							</logic:notPresent>
						</span></strong>
					</td>
				</tr>

				<tr>
					<td colspan="2"><strong>Numeração dos Hidrômetros</strong></td>
				</tr>
				<tr>
					<td><strong>Fixo:</strong></td>
					<td><html:text maxlength="4" property="fixo" size="4" tabindex="12"  /></td>
				</tr>
				<tr>
					<td><strong>Faixa:</strong></td>
					<td>
						<html:text maxlength="6" property="faixaInicial" size="6"
							tabindex="13"  onkeyup="return isCampoNumerico(event);" 
							onkeypress="return isCampoNumerico(event);" 
							onchange="validarCampoNumericoComMensagemLimpandoCampo(document.forms[0].faixaInicial, 'Faixa Inicial');" 
							onblur="return isCampoNumerico(event);"/> 
						<html:text maxlength="6" property="faixaFinal"
							size="6" tabindex="14"  onkeyup="return isCampoNumerico(event);" 
							onkeypress="return isCampoNumerico(event);" 
							onchange="validarCampoNumericoComMensagemLimpandoCampo(document.forms[0].faixaFinal, 'Faixa Final');" 
							onblur="return isCampoNumerico(event);" /></td>
				</tr>
				
				<tr>
					<td><strong>Tombamento:</strong></td>
					<td><html:text maxlength="10" property="tombamento" size="10" tabindex="1"/></td>
				</tr>
				
				<tr>
					<td><strong>Capacidade:</strong></td>
					<td><html:select property="idHidrometroCapacidade" tabindex="9">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoHidrometroCapacidade"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td><strong>Ano de Fabrica&ccedil;&atilde;o:</strong></td>
					<td><html:text maxlength="4" property="anoFabricacao" size="4"
						tabindex="3" onkeypress="return isCampoNumerico(event);"  />aaaa</td>
				</tr>
				<tr>
					<td><strong>Marca:</strong></td>
					<td><html:select property="idHidrometroMarca" tabindex="7">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoHidrometroMarca"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>

				<tr>
					<td height="24" colspan="2" class="style1">
					<hr>
					</td>
				</tr>



				<tr>
					<td><strong>N&uacute;mero do Hidr&ocirc;metro:</strong></td>
					<td><html:text property="numeroHidrometro" size="13" maxlength="12" /></td>
				</tr>

				<tr>
					<td><strong>Data de Aquisi&ccedil;&atilde;o:</strong></td>
					<td><html:text property="dataAquisicao" size="10" maxlength="10"
						onkeypress="mascaraData(this,event); return isCampoNumerico(event);" 
						onkeyup="mascaraData(this,event)" 
						onchange="validarCampoDataComMensagemLimpandoCampo(document.forms[0].dataAquisicao, 'Data de Aquisição');" 
						onblur="mascaraData(this,event);" 
						tabindex="2" /> <a
						href="javascript:abrirCalendario('HidrometroActionForm', 'dataAquisicao')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					dd/mm/aaaa</td>
				</tr>
				
				<tr>
					<td><strong>Finalidade:</strong></td>
					<td><html:radio tabindex="4" property="indicadorOperacional"
						value="<%=(Hidrometro.INDICADOR_COMERCIAL).toString()%>"
						onclick="verificarMarcacao(this.value)" /><strong>Comercial</strong>
					<html:radio tabindex="5" property="indicadorOperacional"
						value="<%=(Hidrometro.INDICADOR_OPERACIONAL).toString()%>"
						onclick="verificarMarcacao(this.value)" /><strong>Operacional</strong>

					<html:radio tabindex="6" property="indicadorOperacional"
						value="-1" /><strong>Todos</strong></td>
				</tr>
				<tr>
					<td><strong>Classe Metrológica:</strong></td>
					<td><html:select property="idHidrometroClasseMetrologica"
						tabindex="6">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoHidrometroClasseMetrologica"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				
				<tr>
					<td><strong>Diâmetro:</strong></td>
					<td><html:select property="idHidrometroDiametro" tabindex="8">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoHidrometroDiametro"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				
				<tr>
					<td><strong>Tipo de Fluxo:</strong></td>
					<td><html:select property="idHidrometroTipo" tabindex="10">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoHidrometroTipo"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				
				<tr>
					<td><strong>Tipo de Relojoaria:</strong></td>
					<td><html:select property="idHidrometroRelojoaria" tabindex="10">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoHidrometroRelojoaria"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				
				<tr>
					<td><strong>Local de Armazenagem:</strong></td>
					<logic:notPresent name="tela" >
						<td><html:text property="idLocalArmazenagem" tabindex="11" size="4"
							maxlength="3"
							onkeypress="validaEnterComMensagem(event, 'exibirFiltrarHidrometroAction.do?objetoConsulta=1', 'idLocalArmazenagem', 'Local de Armazenagem');
							return isCampoNumerico(event);" 
							onkeyup="return isCampoNumerico(event);" 
							onchange="validarCampoNumericoComMensagemLimpandoCampo(document.forms[0].idLocalArmazenagem, 'Local de Armazenagem');" 
							onblur="return isCampoNumerico(event);" />
					</logic:notPresent>
					<logic:present name="tela">
						<td><html:text property="idLocalArmazenagem" tabindex="11" size="4"
							maxlength="3"
							onkeypress="validaEnterComMensagem(event, 'exibirFiltrarHidrometroActionMovimentarHidrometro.do?tela=movimentarHidrometro&objetoConsulta=1', 'idLocalArmazenagem', 'Local de Armazenagem');
							return isCampoNumerico(event);" 
							onkeyup="return isCampoNumerico(event);" 
							onchange="validarCampoNumericoComMensagemLimpandoCampo(document.forms[0].idLocalArmazenagem, 'Local de Armazenagem');" 
							onblur="return isCampoNumerico(event);" />
					</logic:present>
					
					<a
						href="javascript:abrirPopup('exibirPesquisarLocalArmazenagemHidrometroAction.do?', 250, 495);">
					<img src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						border="0" width="23" height="21" title="Pesquisar Local de Armazenagem"></a> <logic:present
						name="corLocalArmazenagem">
						<logic:equal name="corLocalArmazenagem" value="exception">
							<html:text property="localArmazenagemDescricao" size="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="corLocalArmazenagem" value="exception">
							<html:text property="localArmazenagemDescricao" size="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="corLocalArmazenagem">
						<logic:empty name="HidrometroActionForm"
							property="idLocalArmazenagem">
							<html:text property="localArmazenagemDescricao" size="40"
								value="" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="HidrometroActionForm"
							property="idLocalArmazenagem">
							<html:text property="localArmazenagemDescricao" size="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a
						href="javascript:limparPesquisaLocalArmazenagem();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar Local de Armazenagem" /></a></td>
				</tr>
				<tr>
					<td><strong>Situação:</strong></td>
					<logic:notPresent name="tela" >
						<td><html:select property="idHidrometroSituacao" tabindex="10" onchange="javascript:chamarReload();">
							<html:option value="-1">&nbsp;</html:option>
							<logic:present name="colecaoHidrometroSituacao">
								<html:options collection="colecaoHidrometroSituacao"
									labelProperty="descricao" property="id" />
							</logic:present>
						</html:select></td>
					</logic:notPresent>
					<logic:present name="tela">
						<td><html:select property="idHidrometroSituacao" tabindex="10" onchange="javascript:recarregar();">
							<html:option value="-1">&nbsp;</html:option>
							<logic:present name="colecaoHidrometroSituacao">
								<html:options collection="colecaoHidrometroSituacao"
									labelProperty="descricao" property="id" />
							</logic:present>
						</html:select></td>
					</logic:present>
				</tr>
				
				
				<logic:present name="instalado" scope="session">
				<tr>
				<td><strong>Localidade:</strong><font color="#FF0000">*</font></td>
						<td>
							<logic:notPresent name="tela">
								<html:text maxlength="3" property="idLocalidade"
									size="3"
									onkeypress="validaEnterComMensagem(event, 'exibirFiltrarHidrometroAction.do?situacao=sim', 'idLocalidade','Localidade');
									return isCampoNumerico(event);"
									tabindex="11" 
								/> 
							</logic:notPresent>
							<logic:present name="tela" >
								<html:text maxlength="3" property="idLocalidade"
									size="3"
									onkeypress="validaEnterComMensagem(event, 'exibirFiltrarHidrometroActionMovimentarHidrometro.do?tela=movimentarHidrometro&situacao=sim', 'idLocalidade','Localidade');
									return isCampoNumerico(event);"
									tabindex="11" 
								/> 
							</logic:present>
							
							<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'localidade', null, null, 275, 480, '',document.forms[0].idLocalidade);">
							<img width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Localidade" /></a>
							
							 <logic:present
							name="corLocalidade">

							<logic:equal name="corLocalidade" value="exception">
								<html:text property="nomeLocalidade" size="40"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000"
									disabled="true" />
							</logic:equal>

							<logic:notEqual name="corLocalidade" value="exception">
								<html:text property="nomeLocalidade" size="40"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000"
									disabled="true" />
							</logic:notEqual>

						</logic:present> <logic:notPresent name="corLocalidade">

							<logic:empty name="HidrometroActionForm"
								property="idLocalidade">
								<html:text property="nomeLocalidade" value="" size="40"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000"
									disabled="true" />
							</logic:empty>
							<logic:notEmpty name="HidrometroActionForm"
								property="idLocalidade">
								<html:text property="nomeLocalidade" size="40"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: 	#000000"
									disabled="true" />
							</logic:notEmpty>
						</logic:notPresent> <a
							href="javascript:limparLocalidade();;"> <img
							src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar Localidade" /></a></td>
				</tr>
				<tr>
					<td><strong>Setor Comercial :</strong></td>
					<td>
						<logic:notPresent name="tela">
							<html:text maxlength="3" property="codigoSetorComercial"
								size="3" 
								onkeypress="validaEnterDependenciaComMensagem(event, 'exibirFiltrarHidrometroAction.do?situacao=sim', document.forms[0].codigoSetorComercial, document.forms[0].idLocalidade.value, 'Localidade','Setor Comercial'); 
								return isCampoNumerico(event);"
								tabindex="3" />
						</logic:notPresent>
						<logic:present name="tela">
							<html:text maxlength="3" property="codigoSetorComercial"
								size="3" 
								onkeypress="validaEnterDependenciaComMensagem(event, 'exibirFiltrarHidrometroActionMovimentarHidrometro.do?tela=movimentarHidrometro&situacao=sim', document.forms[0].codigoSetorComercial, document.forms[0].idLocalidade.value, 'Localidade','Setor Comercial'); 
								return isCampoNumerico(event);"
								tabindex="3" />
						</logic:present>
						
						<a href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercial', 'idLocalidade', document.forms[0].idLocalidade.value, 275, 480, 'Informe Localidade',document.forms[0].codigoSetorComercial);
						         ">
						<img width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Setor Comercial" /></a>

					<logic:present name="corSetorComercial">

						<logic:equal name="corSetorComercial" value="exception">
							<html:text property="nomeSetorComercial" size="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000"
								disabled="true" />
						</logic:equal>

						<logic:notEqual name="corSetorComercial" value="exception">
							<html:text property="nomeSetorComercial" size="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								disabled="true" />
						</logic:notEqual>
 					<html:hidden property="idSetorComercial" />	
					</logic:present> 
					
					<logic:notPresent name="corSetorComercial">

						<logic:empty name="HidrometroActionForm"
							property="codigoSetorComercial">
							<html:text property="nomeSetorComercial" value="" size="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000"
								disabled="true" />
						</logic:empty>
						<logic:notEmpty name="HidrometroActionForm"
							property="codigoSetorComercial">
							<html:text property="nomeSetorComercial" size="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								disabled="true" />
						</logic:notEmpty>

					</logic:notPresent> <a href="javascript:limparSetorComercial();">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar Setor Comercial" /></a> 
						<html:hidden
						property="idSetorComercial" /></td>
				</tr>
				
				
				<tr>
					<td width="183"><strong>Quadra:</strong></td>
					<td width="432">
						<logic:notPresent name="tela">
							<html:text maxlength="4" property="numeroQuadra"
								size="4"
								onkeypress="validaEnterDependenciaComMensagem(event, 'exibirFiltrarHidrometroAction.do?situacao=sim', document.forms[0].numeroQuadra, document.forms[0].codigoSetorComercial.value, 'Setor Comercial','Quadra'); return isCampoNumerico(event);"
								tabindex="4" />
						</logic:notPresent>
						<logic:present name="tela" >
							<html:text maxlength="4" property="numeroQuadra"
								size="4"
								onkeypress="validaEnterDependenciaComMensagem(event, 'exibirFiltrarHidrometroActionMovimentarHidrometro.do?tela=movimentarHidrometro&situacao=sim', document.forms[0].numeroQuadra, document.forms[0].codigoSetorComercial.value, 'Setor Comercial','Quadra'); return isCampoNumerico(event);"
								tabindex="4" />
						</logic:present>
						 
						<logic:present name="corQuadra">
						 	<logic:equal name="corQuadra" value="exception">
									<span style="color:#ff0000" id="msgOrigem"><bean:write name="HidrometroActionForm" property="quadraMensagem"/></span>
							</logic:equal>	
			            </logic:present>	
						
					</td>
				</tr>
				 <html:hidden property="idQuadra" />	
				</logic:present>
				
				<tr>
					<td><strong>Vazão Transição:</strong></td>
					<td><html:text maxlength="6" 
								property="vazaoTransicao"
								size="6"
								tabindex="2" 
								onkeypress="formataValorMonetario( this, 6 ); return isCampoNumerico(event);" 
								onkeyup="formataValorMonetario( this, 6 );" 
								onchange="formataValorMonetario( this, 6 );" 
								onblur="formataValorMonetario( this, 6 );" 
								style="text-align: right;" /> 
					</td>
				</tr>
				
				<tr>
					<td><strong>Vazão Nominal:</strong></td>
					<td><html:text maxlength="6" 
								property="vazaoNominal" 
								size="6"
								tabindex="2" 
								onkeypress="formataValorMonetario( this, 6 ); return isCampoNumerico(event);"
								onkeyup="formataValorMonetario( this, 6 );" 
								onchange="formataValorMonetario( this, 6 );" 
								onblur="formataValorMonetario( this, 6 );" 
								style="text-align: right;" /> 
				    </td>
				</tr>
				
				<tr>
					<td><strong>Vazão Mínima:</strong></td>
					<td><html:text maxlength="6"
								 property="vazaoMinima" 
								 size="6"
								 tabindex="2" 
								 onkeypress="formataValorMonetario( this, 6 ); return isCampoNumerico(event);" 
								 onkeyup="formataValorMonetario( this, 6 );" 
								 onchange="formataValorMonetario( this, 6 );" 
								 onblur="formataValorMonetario( this, 6 );" 
								 style="text-align: right;"/> 
					</td>
				</tr>
				
				<tr>
					<td><strong>Nota Fiscal:</strong></td>
					<td><html:text maxlength="9" property="notaFiscal" size="9"
						tabindex="2" onkeypress="return isCampoNumerico(event);"
						onkeyup="return isCampoNumerico(event);" 
						onchange="validarCampoNumericoComMensagemLimpandoCampo(document.forms[0].notaFiscal, 'Nota Fiscal');" 
						onblur="return isCampoNumerico(event);"  /> </td>
				</tr>
				
				<tr>
					<td><strong>Tempo de Garantia em Anos:</strong></td>
					<td><html:text maxlength="4" property="tempoGarantiaAnos" size="4"
						tabindex="2" onkeypress="return isCampoNumerico(event);"
						onkeyup="return isCampoNumerico(event);" 
						onchange="validarCampoNumericoComMensagemLimpandoCampo(document.forms[0].tempoGarantiaAnos, 'Tempo de Garantia em Anos');" 
						onblur="return isCampoNumerico(event);"  /> </td>
				</tr>

				<tr>
					<td><strong>Erro do Macromedidor:<font color="#FF0000"></font></strong></td>
					<td><html:select property="idHidrometroFatorCorrecao" tabindex="5">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoHidrometroFatorCorrecao"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>

				<tr>
					<td><strong>Classe de Pressão:<font color="#FF0000"></font></strong></td>
					<td><html:select property="idHidrometroClassePressao" tabindex="5">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoHidrometroClassePressao"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				
				<tr>
					<td colspan="2"><strong><font color="#ff0000"> 
						<logic:notPresent name="tela">
							<input name="Submit22"
								class="bottonRightCol" value="Limpar" type="button"
								onclick="window.location.href='/gsan/exibirFiltrarHidrometroAction.do?menu=sim'">&nbsp; 
						</logic:notPresent>
						<logic:present name="tela">
							<input name="Submit22"
								class="bottonRightCol" value="Limpar" type="button"
								onclick="window.location.href='/gsan/exibirFiltrarHidrometroActionMovimentarHidrometro.do?tela=movimentarHidrometro&menu=sim'">&nbsp;
						</logic:present>
						
						<input type="button" name="Button"
							class="bottonRightCol" value="Cancelar" tabindex="6"
							onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"
							style="width: 80px" /> </font></strong></td>
					<td align="right"><gsan:controleAcessoBotao name="Button"
						value="Filtrar"
						onclick="javascript:validarForm(document.forms[0]);"
						url="filtrarHidrometroAction.do" /> <!-- <input type="button" class="bottonRightCol" tabindex="15" value="Filtrar" tabindex="13"
							onclick="validarForm(document.forms[0]);" /> --></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
