<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="gcom.util.ConstantesSistema"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="gcom.micromedicao.hidrometro.Hidrometro" isELIgnored="false"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="AtualizarHidrometroActionForm" dynamicJavascript="false" />
<script language="JavaScript">
//<!-- Begin
 	var bCancel = false;

	function validateAtualizarHidrometroActionForm(form) {
		if (bCancel)
			return true;
		else {
			var mensagem = validarCamposMacroMicro(form);
	         
			if (mensagem != null && mensagem != '') {
				alert(mensagem);
				return false;
			}
			return validateRequired(form) &&  validateCaracterEspecial(form) &&  validateDate(form) && validateLong(form);
		}
	}

	function required () {
		this.ab = new Array("dataAquisicao", "Informe Data de Aquisição.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
		this.ac = new Array("anoFabricacao", "Informe Ano de Fabricação.", new Function ("varName", " return this[varName];"));
		this.ad = new Array("idHidrometroClasseMetrologica", "Informe Classe Metrológica.", new Function ("varName", " return this[varName];"));
		this.ae = new Array("idHidrometroMarca", "Informe Marca.", new Function ("varName", " return this[varName];"));
		this.af = new Array("idHidrometroDiametro", "Informe Diâmetro.", new Function ("varName", " return this[varName];"));
		this.ag = new Array("idHidrometroCapacidade", "Informe Capacidade.", new Function ("varName", " return this[varName];"));
		this.ah = new Array("idNumeroDigitosLeitura", "Informe Número de Digitos.", new Function ("varName", " return this[varName];"));
	}


	function validarCamposMacroMicro(form){
		var mensagem = "";
		if (form.indicadorMacromedidor[0].checked == true) {
			if (form.tombamento.value == null || form.tombamento.value == '') {
				mensagem = mensagem + "Informe Tombamento.\n";
			}
			
			if (form.idHidrometroFatorCorrecao.value == null || form.idHidrometroFatorCorrecao.value == '' || form.idHidrometroFatorCorrecao.value == '-1') {
				mensagem = mensagem + "Informe Erro do Macromedidor.\n";
			}
			
			if (form.idHidrometroClassePressao.value == null || form.idHidrometroClassePressao.value == '' || form.idHidrometroClassePressao.value == '-1') {
				mensagem = mensagem + "Informe Classe de Pressão dos Hidrômetros.\n";
			}
		} else {
			if (form.numeroHidrometro.value == null || form.numeroHidrometro.value == '') {
				mensagem = mensagem + "Informe Número do Hidrômetro.\n";
			}
			
			if (form.idHidrometroTipo.value == null || form.idHidrometroTipo.value == '' || form.idHidrometroTipo.value == '-1') {
				mensagem = mensagem + "Informe Tipo de Fluxo.\n";
			}
		}
		
		return mensagem;
	}
	
	function DateValidations () {
		this.aa = new Array("dataAquisicao", "Data de Aquisição inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
	}

    function caracteresespeciais () {
    	this.ab = new Array("dataAquisicao", "Data de Aquisição possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    	this.ac = new Array("anoFabricacao", "Ano de Fabricação possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    	this.ad = new Array("vazaoTransicao", "Vazão de Transição possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     	this.ae = new Array("vazaoNominal", "Vazão Nominal possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     	this.af = new Array("vazaoMinima", "Vazão Mínima possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     	this.ag = new Array("tempoGarantiaAnos", "Tempo de Garantia em Anos possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     	this.ah = new Array("notaFiscal", "Nota Fiscal possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    }

	function IntegerValidations () {
		this.aa = new Array("anoFabricacao", "Ano de Fabricação deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ae = new Array("tempoGarantiaAnos", "Tempo de Garantia em Anos deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ah = new Array("notaFiscal", "Nota Fiscal deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	}

	function validarForm(form){
		if (validateAtualizarHidrometroActionForm(form) && testarCampoValorZero(form.numeroHidrometro, 'Número Hidrômetro') && testarCampoValorZero(form.anoFabricacao, 'Data Fabricação')){
			submeterFormPadrao(form);
	}
}
	//Validacao Adicionada por Romulo Aurelio 24/05/2007 
	//[FS0007]-Montar ano de fabricacao
	function validarAnoFabricacao(){
		var form = document.forms[0];
		
		var dataAtual = new Date();
		var anoDataAtual = dataAtual.getFullYear();
		var anoFabricacao = trim(form.anoFabricacao.value);
		var numeroHidrometro = trim(form.numeroHidrometro.value);
		var anoAtualCompleto = ''+ anoDataAtual;
		var anoDataAtualFinal = parseInt(anoAtualCompleto.substring(2,4));
		var ano = parseInt(numeroHidrometro.substring(1,3));
		
		if (numeroHidrometro.substring(1,2) == '0') {
			ano = parseInt(numeroHidrometro.substring(2,3));
		}
		
		if ((form.indicadorMacromedidor[1].checked == true || form.indicadorMacromedidor[2].checked == true) 
			&& !((numeroHidrometro.substring(1,2)== '0' || numeroHidrometro.substring(1,2)== '1' ||
			numeroHidrometro.substring(1,2)== '2' ||numeroHidrometro.substring(1,2)== '3' ||
			numeroHidrometro.substring(1,2)== '4' || numeroHidrometro.substring(1,2)== '5' ||
			numeroHidrometro.substring(1,2)== '6' ||numeroHidrometro.substring(1,2)== '7' ||
			numeroHidrometro.substring(1,2)== '8' ||numeroHidrometro.substring(1,2)== '9') && 
			(numeroHidrometro.substring(2,3)=='0' || numeroHidrometro.substring(2,3)== '1' ||
			numeroHidrometro.substring(2,3)== '2' ||numeroHidrometro.substring(2,3)== '3' ||
			numeroHidrometro.substring(2,3)== '4' || numeroHidrometro.substring(2,3)== '5' ||
			numeroHidrometro.substring(2,3)== '6' ||numeroHidrometro.substring(2,3)== '7' ||
			numeroHidrometro.substring(2,3)== '8' ||numeroHidrometro.substring(2,3)== '9'))) {
			
			alert('Informe ano numérico');
			form.anoFabricacao.value = '';
		} else {
			//form.anoFabricacao.value = 1900 + ano;
			if (ano < 60) {
				form.anoFabricacao.value = 2000 + ano; 
			} else {
				form.anoFabricacao.value = 1900 + ano; 
			}
			
			if (ano >= 85) {
				form.anoFabricacao.value = 1900 + ano; 
			}
			
			if (ano >= 00 &&  ano <= anoDataAtualFinal) {
				form.anoFabricacao.value = 2000 + ano; 
			}
			
			if (anoDataAtualFinal < ano && form.anoFabricacao.value > parseInt(anoAtualCompleto)) {
				form.anoFabricacao.value = '';
				alert('Ano de fabricação inválido');
			} else if(form.anoFabricacao.value  < 1985) {
				form.anoFabricacao.value = '';
				alert('Ano de fabicação de ser igual ou superior a 1985.');
			}
		}
	}

	function limparAnoFabricacao(){
		var form = document.forms[0];
		form.anoFabricacao.value = '';
	} 

	function bloqueiaDados(){
		var form = document.forms[0];
		
		if(form.indicadorMacromedidor[0].checked == true){
			
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
		
		if (form.indicadorMacromedidor[0].checked == false) {
			
			if (form.indicadorMacromedidor[1].checked == true) {
				//form.anoFabricacao.value = "";
				form.anoFabricacao.style.color = "#000000";
				form.anoFabricacao.readOnly = true;
				form.anoFabricacao.style.backgroundColor = '#EFEFEF';
			} else if (form.indicadorMacromedidor[2].checked == true) {
				form.anoFabricacao.style.color = "#000000";
				form.anoFabricacao.readOnly = false;
				form.anoFabricacao.style.backgroundColor = '';
			}
			
			form.idHidrometroTipo.style.color = "#000000";
			form.idHidrometroTipo.disabled = false;
			form.idHidrometroTipo.readOnly = false;
			form.idHidrometroTipo.style.backgroundColor = '';
	
			form.idHidrometroRelojoaria.style.color = "#000000";
			form.idHidrometroRelojoaria.disabled = false;
			form.idHidrometroRelojoaria.readOnly = false;
			form.idHidrometroRelojoaria.style.backgroundColor = '';
			
			if (form.idHidrometroFatorCorrecao) {
				form.idHidrometroFatorCorrecao.value = "-1";
				form.idHidrometroFatorCorrecao.style.color = "#000000";
				form.idHidrometroFatorCorrecao.disabled = true;
				form.idHidrometroFatorCorrecao.readOnly = true;
				form.idHidrometroFatorCorrecao.style.backgroundColor = '#EFEFEF';
			}
			
			if (form.idHidrometroClassePressao) {
				form.idHidrometroClassePressao.value = "-1";
				form.idHidrometroClassePressao.style.color = "#000000";
				form.idHidrometroClassePressao.disabled = true;
				form.idHidrometroClassePressao.readOnly = true;
				form.idHidrometroClassePressao.style.backgroundColor = '#EFEFEF';
			}
		}  
	}
//End -->
</script>

</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}');bloqueiaDados();">
<div id="formDiv">
<html:form action="/atualizarHidrometroAction.do"
	name="AtualizarHidrometroActionForm"
	type="gcom.gui.micromedicao.hidrometro.AtualizarHidrometroActionForm"
	method="post">

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
			<table width="100%" height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img src="<bean:message key="caminho.imagens"/>parahead_left.gif" border="0" /></td>
					<td class="parabg">Atualizar Hidr&ocirc;metro</td>
					<td width="11" valign="top"><img src="<bean:message key="caminho.imagens"/>parahead_right.gif" border="0" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para atualizar o(s) hidr&ocirc;metros(s), informe os dados abaixo:</td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}micromedicaoHidrometroAtualizar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">
						<strong>
							<span class="style1">
								<html:radio property="indicadorMacromedidor" disabled="true" value="1" />&nbsp;Macromedidor&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio property="indicadorMacromedidor" disabled="true" value="2" />&nbsp;Micromedidor&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio property="indicadorMacromedidor" disabled="true" value="3" />&nbsp;Medição de Esgoto
							</span>
					</strong>
					</td>
				</tr>
				<tr>
					<html:hidden property="indicadorFinalidade"/>
					<td width="218"><strong>Matrícula do Imóvel:</strong></td>
					<td width="393"><html:text property="idImovel" size="10" maxlength="10" readonly="true" style="background-color:#EFEFEF; border:0;" /></td>
				</tr>
         		<c:if test="${(indicadorMacromedidor == '2') || (indicadorMacromedidor == '3')}">
					<tr>
						<td width="30%">
							<strong>N&uacute;mero do Hidr&ocirc;metro:<font color="#FF0000">*</font></strong>
						</td>
						<logic:equal name="indicadorMacromedidor" value="2">
							<td width="70%">
								<html:text property="numeroHidrometro" size="13" maxlength="12" tabindex="1" onkeyup= "javascript:limparAnoFabricacao();" onblur="javascript:validarAnoFabricacao();"/>
							</td>
						</logic:equal>
						<logic:equal name="indicadorMacromedidor" value="3">
							<td width="70%">
								<html:text property="numeroHidrometro" size="13" maxlength="12" tabindex="1" />
							</td>
						</logic:equal>
					</tr>
				</c:if>
				<tr>
					<td><strong>Tombamento:</strong></td>
					<td><html:text maxlength="10" property="tombamento" size="10" tabindex="1"/></td>
				</tr>
				<tr>
					<td><strong>Capacidade:<font color="#FF0000">*</font></strong></td>
					<td>
						<html:select property="idHidrometroCapacidade" tabindex="2"
						 onchange="redirecionarSubmit('exibirAtualizarHidrometroAction.do?idRegistroAtualizacao=${idHidrometro}';">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoHidrometroCapacidade" labelProperty="descricao" property="id" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						<strong>Ano de Fabrica&ccedil;&atilde;o:<font color="#FF0000">*</font></strong>
					</td>
					<td>
						<html:text maxlength="4" property="anoFabricacao" size="4" readonly="true"
							style="background-color:#EFEFEF; border:0;" 
							onkeyup="return isCampoNumerico(event);" 
							onkeypress="return isCampoNumerico(event);" 
							onchange="validarCampoNumericoComMensagemLimpandoCampo(document.forms[0].anoFabricacao, 'Ano de Fabricação');" 
							onblur="return isCampoNumerico(event);" />aaaa
					</td>
				</tr>
				<tr>
					<td><strong>Marca:<font color="#FF0000">*</font></strong></td>
					<td>
						<html:select property="idHidrometroMarca" tabindex="3">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoHidrometroMarca" labelProperty="descricao" property="id" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td height="24" colspan="2">
					<hr>
					</td>
				</tr>
				<tr>
					<td>
						<strong>Data de Aquisi&ccedil;&atilde;o:<font color="#FF0000">*</font></strong>
					</td>
					<td>
						<html:text property="dataAquisicao" size="10" maxlength="10"
							onkeyup="mascaraData(this,event)" tabindex="4" 
							onkeypress="mascaraData(this,event);" 
							onchange="validarCampoDataComMensagemLimpandoCampo(document.forms[0].dataAquisicao, 'Data de Aquisição');" 
							onblur="mascaraData(this,event);" />
						<a href="javascript:abrirCalendario('AtualizarHidrometroActionForm', 'dataAquisicao')">
							<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>dd/mm/aaaa
					</td>
				</tr>

				<tr>
					<td><strong>Finalidade:</strong></td>
					<td><html:radio property="indicadorOperacional" tabindex="5"
						value="<%=(Hidrometro.INDICADOR_COMERCIAL).toString()%>" /><strong>Comercial</strong>
					<html:radio property="indicadorOperacional" tabindex="6"
						value="<%=(Hidrometro.INDICADOR_OPERACIONAL).toString()%>" /><strong>Operacional</strong>
					</td>
				</tr>
				<tr>
					<td><strong>Classe Metrológica:<font color="#FF0000">*</font></strong></td>
					<td><html:select property="idHidrometroClasseMetrologica"
						tabindex="7">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoHidrometroClasseMetrologica"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>

				<tr>
					<td><strong>Diâmetro:<font color="#FF0000">*</font></strong></td>
					<td><html:select property="idHidrometroDiametro" tabindex="8">
						<html:option
							value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoHidrometroDiametro"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td><strong>Número de Digitos:<font color="#FF0000">*</font></strong></td>

					<td><logic:present name="colecaoIntervalo">
						<html:select property="idNumeroDigitosLeitura" tabindex="9"
							value="${hidrometro.numeroDigitosLeitura}">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>

							<html:options collection="colecaoIntervalo"
								labelProperty="numeroDigitosLeitura"
								property="numeroDigitosLeitura" />

						</html:select>

						<%--<html:select property="idNumeroDigitosLeitura" tabindex="10">
              			<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option> 
			  			<html:options collection="colecaoIntervalo" labelProperty="idNumeroDigitosLeitura" property="idNumeroDigitosLeitura"/>
            		  </html:select>--%>
					</logic:present> <logic:notPresent name="colecaoIntervalo">
						<html:select property="idNumeroDigitosLeitura" tabindex="9">
							<html:option value="-1">&nbsp;</html:option>
						</html:select>
					</logic:notPresent></td>
				</tr>
         		<c:if test="${(indicadorMacromedidor == '2') || (indicadorMacromedidor == '3')}">
					<tr>
						<td><strong>Tipo de Fluxo:<font color="#FF0000">*</font></strong></td>
						<td>
							<html:select property="idHidrometroTipo" tabindex="10">
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<html:options collection="colecaoHidrometroTipo" labelProperty="descricao" property="id" />
							</html:select>
						</td>
					</tr>
					
					<tr>
						<td><strong>Tipo de Relojoaria:</strong></td>
						<td><html:select property="idHidrometroRelojoaria" tabindex="10">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoHidrometroRelojoaria"
								labelProperty="descricao" property="id" />
						</html:select></td>
					</tr>
				</c:if>
				<tr>
					<td><strong>Vazão Transição:</strong></td>
					<td><html:text maxlength="6" 
								property="vazaoTransicao"
								size="6"
								tabindex="2" 
								onkeypress="formataValorMonetario( this, 6 ); return isCampoNumerico(event);" 
								onkeyup="formataValorMonetario( this, 6 );" 
								style="text-align: right;" /> 
					</td>
				</tr>
				<tr>
					<td><strong>Vazão Nominal:</strong></td>
					<td><html:text maxlength="6" 
								property="vazaoNominal" 
								size="6"
								tabindex="2" 
								onkeypress="formataValorMonetario( this, 6 );  return isCampoNumerico(event);" 
								onkeyup="formataValorMonetario( this, 6 );" 
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
								 style="text-align: right;"/> 
					</td>
				</tr>
				<tr>
					<td><strong>Nota Fiscal:</strong></td>
					<td><html:text maxlength="9" property="notaFiscal" size="9"
						tabindex="2" onkeypress="return isCampoNumerico(event);" /> </td>
				</tr>
				<tr>
					<td><strong>Tempo de Garantia em Anos:</strong></td>
					<td><html:text maxlength="4" property="tempoGarantiaAnos" size="4"
						tabindex="2" onkeypress="return isCampoNumerico(event);" /> </td>
				</tr>
         		<logic:equal name="indicadorMacromedidor" value="1">
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
         		</logic:equal>
				<tr>
					<td>&nbsp;</td>
					<td align="left"><font color="#FF0000">*</font> Campo Obrigatório</td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td><logic:present scope="session" name="manter">
						<input name="Submit222" class="bottonRightCol" value="Voltar"
							type="button"
							onclick="window.location.href='<html:rewrite page="/exibirManterHidrometroAction.do"/>'">
					</logic:present> <logic:notPresent scope="session" name="manter">
						<logic:present scope="session" name="filtrar_manter">
							<input name="Submit222" class="bottonRightCol" value="Voltar"
								type="button" onclick="javascript:history.back();">
						</logic:present>
						<logic:notPresent scope="session" name="filtrar_manter">
							<input name="Submit222" class="bottonRightCol" value="Voltar"
								type="button"
								onclick="window.location.href='/gsan/exibirFiltrarHidrometroAction.do?menu=sim';">
						</logic:notPresent>
					</logic:notPresent> <input name="Submit22" class="bottonRightCol"
						value="Desfazer" type="button"
						onclick="window.location.href='/gsan/exibirAtualizarHidrometroAction.do?idRegistroAtualizacao=<bean:write name="AtualizarHidrometroActionForm" property="idHidrometro" />';">
					<input name="Submit23" class="bottonRightCol" value="Cancelar"
						type="button"
						onclick="window.location.href='/gsan/telaPrincipal.do'"></td>
					<td align="right"><gsan:controleAcessoBotao name="Button"
						value="Atualizar"
						onclick="javascript:validarForm(document.AtualizarHidrometroActionForm);"
						url="atualizarHidrometroAction.do" /> <!--
					<input type="button" class="bottonRightCol" tabindex="12"
						value="Atualizar" tabindex="13"
						onclick="validarForm(document.AtualizarHidrometroActionForm);">--></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</div>
</body>
</html:html>
