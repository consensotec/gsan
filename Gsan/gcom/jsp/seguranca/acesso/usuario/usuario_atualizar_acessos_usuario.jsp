<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ page import="gcom.seguranca.acesso.usuario.UsuarioAbrangencia"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="AtualizarUsuarioDadosGeraisActionForm"
	dynamicJavascript="false" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
//window.onmousemove = verificarAcesso;
	function limparElo () {
		 	document.forms[0].idElo.value = '';
		 	document.forms[0].nomeElo.value = '';
	}

	function limparLocalidade() {
		 	document.forms[0].idLocalidade.value = '';
		 	document.forms[0].nomeLocalidade.value = '';
	}

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		if ('elo' == tipoConsulta) {
		 	document.forms[0].idElo.value = codigoRegistro;
		 	document.forms[0].nomeElo.value = descricaoRegistro;
	 	} else if ('localidadeElo' == tipoConsulta) { 
		 	document.forms[0].idElo.value = codigoRegistro;
		 	document.forms[0].nomeElo.value = descricaoRegistro;
	 	} else if ('localidade' == tipoConsulta) { 
		 	document.forms[0].idLocalidade.value = codigoRegistro;
		 	document.forms[0].nomeLocalidade.value = descricaoRegistro;
	 	}
	}
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado,nomeDependencia,valorDependencia){
		if(objetoRelacionado.disabled != true){
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "tipo=" + tipo + "&"+ nomeDependencia + "=" + valorDependencia, altura, largura);
			}
			else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				}
				else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&" + nomeDependencia + "=" + valorDependencia, altura, largura);
				}
			}
		}
   } 
    var bCancel = false; 
   function validateAtualizarUsuarioDadosGeraisActionForm(form) {                                                                   
        if (bCancel) {
      	return true; 
      }
        else {
       //Retirar valida��o do Grupo no caso de sele��o da op��o internet e/ou batch
      		<c:choose>
				<c:when test='${usuarioParaAtualizar.indicadorUsuarioBatch == 1 || usuarioParaAtualizar.indicadorUsuarioInternet == 1}'>
					return validateRequired(form)&& validateLong(form) && validateCaracterEspecial(form) && enviar() && testarCampoValorZero(form.idElo, 'Elo P�lo') && testarCampoValorZero(form.idLocalidade, 'Localidade'); 
				</c:when>
				<c:otherwise>
		       		return validateRequired(form)&& validarGrupo() && validateLong(form) && validateCaracterEspecial(form) && enviar() && testarCampoValorZero(form.idElo, 'Elo P�lo') && testarCampoValorZero(form.idLocalidade, 'Localidade'); 
				</c:otherwise>
			</c:choose>
	}
   } 

    function required () { 
     this.aa = new Array("abrangencia", "Informe Abrang�ncia do Acesso.", new Function ("varName", " return this[varName];"));
      this.ab = new Array("grupo", "Informe Grupo.", new Function ("varName", " return this[varName];"));
    } 

    function IntegerValidations () { 
     this.aa = new Array("idElo", "Elo P�lo deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("idLocalidade", "Localidade deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
    } 

    function caracteresespeciais () { 
     this.aa = new Array("idElo", "Elo P�lo possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     this.ab = new Array("idLocalidade", "Localidade possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    } 
	
	function validarGrupo(){
       var form = document.forms[0];
	   if (form.grupo.value == ''){
	   	  alert('Informe Grupo.');
	   }else {
	      return true;
	   }
   }
	
function desfazer(){
    document.forms[0].reset();
    verificarAcesso()
}
function enviar() {
    var form = document.forms[0];
    var achou = true;
	if (form.abrangencia.value == form.gerenciaRegionalConstante.value) {
	  if(form.gerenciaRegional.value == ''){
	   alert('Informe Ger�ncia Regional.');
	   achou = false;
	  }
	}else{
	   if(form.abrangencia.value == form.unidadeNegocioConstante.value){
	     if(form.unidadeNegocio.value == ''){
	       alert('Informe Unidade de Neg�cio.');
	       achou = false;
	     }
	  }else{
	   if (form.abrangencia.value == form.eloPolo.value){
	     if(form.idElo.value == ''){
	      alert('Informe Elo P�lo.');
	      achou = false;
	     }
	   }else{
	     if(form.abrangencia.value == form.localidade.value){
	       if(form.idLocalidade.value == ''){
	        alert('Informe Localidade.');
	        achou = false;
	       }
	     }
	  }
	 } 
   }
 return achou;
}

function verificarAcesso(){
var form = document.forms[0];
//caso a abrang�ncia n�o tenha valor
if(form.abrangencia.value == '' || form.abrangencia.value == form.estado.value){
 form.gerenciaRegional.disabled = true;
 form.unidadeNegocio.disabled = true;
 form.idElo.disabled = true;
 form.idLocalidade.disabled = true;
 form.gerenciaRegional.value = '';
 form.idElo.value = '';
 form.nomeElo.value = '';
 form.idLocalidade.value = '';
 form.nomeLocalidade.value = '';
 form.unidadeNegocio.value = '';
}else{
 if(form.abrangencia.value == form.gerenciaRegionalConstante.value){
 form.gerenciaRegional.disabled = false;
 form.unidadeNegocio.disabled = true;
 form.idElo.disabled = true;
 form.idLocalidade.disabled = true;
 form.idElo.value = '';
 form.nomeElo.value = '';
 form.idLocalidade.value = '';
 form.nomeLocalidade.value = '';
 form.unidadeNegocio.value = '';
 
 }
 
 if(form.abrangencia.value == form.unidadeNegocioConstante.value){
 form.unidadeNegocio.disabled = false;
 form.gerenciaRegional.disabled = true;
 form.idElo.disabled = true;
 form.idLocalidade.disabled = true;
 form.gerenciaRegional.value = '';
 form.idElo.value = '';
 form.nomeElo.value = '';
 form.idLocalidade.value = '';
 form.nomeLocalidade.value = '';
 
 }
 if(form.abrangencia.value == form.eloPolo.value){
 form.gerenciaRegional.disabled = true;
 form.unidadeNegocio.disabled = true;
 form.idElo.disabled = false;
 form.idLocalidade.disabled = true;
 form.idLocalidade.value = '';
 form.nomeLocalidade.value = '';
 form.gerenciaRegional.value = '';
 form.unidadeNegocio.value = '';
 }
 if(form.abrangencia.value == form.localidade.value){
 form.gerenciaRegional.disabled = true;
 form.unidadeNegocio.disabled = true;
 form.idElo.disabled = true;
 form.idLocalidade.disabled = false;
 form.idElo.value = '';
 form.nomeElo.value = '';
 form.gerenciaRegional.value = '';
 form.unidadeNegocio.value = '';
 }
}
}

function selecionarCampos(){
var form = document.forms[0];
if(form.grupoNaoDesabilitados.value != ''){
var idsGrupos = form.grupoNaoDesabilitados.value.split(',');
for (i = 0;i< idsGrupos.length;i++){
 for (j=0;j< form.grupo.length;j++){
   if(idsGrupos[i] == form.grupo[j].value){
    form.grupo[j].selected = true;
    break;
   }
 }
}
}
}

</script>
</head>

<body leftmargin="5" topmargin="5" onload="verificarAcesso()">

<html:form action="/atualizarUsuarioWizardAction" method="post"
	onsubmit="return validateAtualizarUsuarioDadosGeraisActionForm(this);">

	<jsp:include
		page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=2" />

	<input type="hidden" name="numeroPagina" value="2" />
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
			<td width="600" valign="top" class="centercoltext">
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
					<td class="parabg">Atualizar Usu�rio - Acesso Usu�rio</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table border="0" width="100%">
				<tr>
					<td colspan="2">Para adicionar o usu�rio, informe os dados abaixo:
					<input type="hidden" name="estado"
						value="<%=UsuarioAbrangencia.ESTADO%>" /> <input type="hidden"
						name="gerenciaRegionalConstante"
						value="<%=UsuarioAbrangencia.GERENCIA_REGIONAL%>" /> <input
						type="hidden" name="eloPolo"
						value="<%=UsuarioAbrangencia.ELO_POLO%>" /> <input type="hidden"
						name="localidade" value="<%=UsuarioAbrangencia.LOCALIDADE%>" />
						<input type="hidden"
						name="unidadeNegocioConstante" value="<%=UsuarioAbrangencia.UNIDADE_NEGOCIO_INT%>" /></td>
				</tr>
				<tr>
					<td width="26%"><strong>Abrang�ncia do Acesso:<font color="#ff0000">*</font></strong></td>
					<td width="74%"><logic:present name="desabilitaUsuarioAbrangencia">
						<html:select property="abrangencia"
							disabled="true">
							<option value="">&nbsp;</option>
							<html:options name="request" collection="collUsuarioAbrangencia"
								labelProperty="descricao" property="id" />
						</html:select>
					</logic:present> <logic:notPresent
						name="desabilitaUsuarioAbrangencia">
						<html:select property="abrangencia"
							onchange="javascript:verificarAcesso();">
							<option value="">&nbsp;</option>
							<html:options name="request" collection="collUsuarioAbrangencia"
								labelProperty="descricao" property="id" />
						</html:select>
					</logic:notPresent></td>
				</tr>
				<tr>
					<td width="26%"><strong>Ger�ncia Regional:</strong></td>
					<td width="74%"><html:select property="gerenciaRegional">
						<option value="">&nbsp;</option>
						<html:options name="request" collection="collGerenciaRegional"
							labelProperty="nome" property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td width="26%"><strong>Unidade Neg�cio:</strong></td>
					<td width="74%"><html:select property="unidadeNegocio">
						<option value="">&nbsp;</option>
						<html:options name="request" collection="collUnidadeNegocio"
							labelProperty="nome" property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td width="26%"><strong>Localidade P�lo:</strong></td>
					<td width="74%"><html:text maxlength="9"
						name="AtualizarUsuarioDadosGeraisActionForm" property="idElo"
						size="9"
						onkeypress="javascript:limparLocalidade();validaEnterComMensagem(event, 'exibirAtualizarUsuarioAcessosUsuarioAction.do?onLoad=1', 'idElo','Elo P�lo');" />

					<a
						href="javascript:chamarPopup('exibirPesquisarEloAction.do', 'elo', null, null, 400, 800, document.forms[0].idElo,document.forms[0].idElo,'','');">
					<img width="23" border="0" height="21"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						style="cursor:hand;" alt="Pesquisar" /></a> <logic:equal
						name="AtualizarUsuarioDadosGeraisActionForm"
						property="eloNaoEncontrada" value="false">
						<html:text property="nomeElo" size="40" readonly="true"
							style="background-color:#EFEFEF; border:0" />
					</logic:equal> <logic:equal
						name="AtualizarUsuarioDadosGeraisActionForm"
						property="eloNaoEncontrada" value="true">
						<html:text property="nomeElo" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							size="40" maxlength="40" />
					</logic:equal> <a href="javascript:limparElo();"> <img border="0"
						src="imagens/limparcampo.gif" height="21" width="23"> </a></td>
				</tr>
				<tr>
					<td width="26%"><strong>Localidade:</strong></td>
					<td width="74%"><html:text property="idLocalidade" size="5"
						maxlength="3"
						onkeyup="return validaEnterComMensagem(event, 'exibirAtualizarUsuarioAcessosUsuarioAction.do?onLoad=1', 'idLocalidade', 'Localidade');" />
					<a
						href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'localidade', null, null, 400, 800,document.forms[0].idLocalidade,document.forms[0].idLocalidade,'idElo',document.forms[0].idElo.value);">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						style="cursor:hand;" alt="Pesquisar" /></a> <logic:equal
						name="AtualizarUsuarioDadosGeraisActionForm"
						property="localidadeNaoEncontrada" value="false">
						<html:text property="nomeLocalidade" size="40" readonly="true"
							style="background-color:#EFEFEF; border:0" />
					</logic:equal> <logic:equal
						name="AtualizarUsuarioDadosGeraisActionForm"
						property="localidadeNaoEncontrada" value="true">
						<html:text property="nomeLocalidade" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							size="40" maxlength="40" />
					</logic:equal> <a href="javascript:limparLocalidade();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>

				<tr>
				
					<td width="26%"><strong>Grupo:
						<c:if test='${usuarioParaAtualizar.indicadorUsuarioBatch != 1 && usuarioParaAtualizar.indicadorUsuarioInternet != 1}'>
							<font color="#ff0000">*</font></strong>
						</c:if>
					</td>
					<td width="74%">
					
					<c:choose>
						<c:when test='${usuarioParaAtualizar.indicadorUsuarioBatch == 1 || usuarioParaAtualizar.indicadorUsuarioInternet == 1}'>
							<html:select multiple="true" size="6"  style="background-color:#EFEFEF; border:0; color: #000000"
								name="AtualizarUsuarioDadosGeraisActionForm" property="grupo" disabled="true">
								<option value="">&nbsp;</option>
								<logic:notEmpty name="collGrupo">
								<html:options name="request"  collection="collGrupo"
									labelProperty="descricao" property="id" />
								</logic:notEmpty>
							</html:select>
						</c:when>
						<c:otherwise>
							<html:select multiple="true" size="6"
								name="AtualizarUsuarioDadosGeraisActionForm" property="grupo">
								<option value="">&nbsp;</option>
								<logic:notEmpty name="collGrupo">
								<html:options name="request" collection="collGrupo"
									labelProperty="descricao" property="id" />
								</logic:notEmpty>
							</html:select><html:hidden property="grupoNaoDesabilitados" />
						</c:otherwise>
					</c:choose>
						
					</td>
					
				</tr>
				<tr>
					<td width="26%"><strong>Situa��o:<font color="#ff0000">*</font></strong></td>
					<td width="74%"><html:select property="situacao">
						<option value="-1">&nbsp;</option>
						<html:options name="request" collection="collUsuarioSituacao"
							labelProperty="descricaoUsuarioSituacao" property="id" />
					</html:select></td>
				</tr>

				<tr>
					<td colspan="2">
					<table border="0" width="100%">
						<tr>
							<td>
							<div align="right"><jsp:include
								page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=2" /></div>
							</td>
						</tr>
					</table>
					</td>
				</tr>

			</table>
			</td>
		</tr>
	</table>
    <%@ include file="/jsp/util/tooltip.jsp"%>
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
