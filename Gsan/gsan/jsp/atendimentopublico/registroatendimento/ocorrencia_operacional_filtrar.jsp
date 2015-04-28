<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="FiltrarOcorrenciaOperacionalActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

var bCancel = false;

function validateFiltrarOcorrenciaOperacionalActionForm(form) {
    if (bCancel)
  return true;
    else

  return testarCampoValorZero(document.FiltrarOcorrenciaOperacionalActionForm.codigoMunicipio, 'Município') &&
	testarCampoValorZero(document.FiltrarOcorrenciaOperacionalActionForm.localidade, 'Localidade') && 
	testarCampoValorZero(document.FiltrarOcorrenciaOperacionalActionForm.bairro, 'Bairro') &&
	testarCampoValorZero(document.FiltrarOcorrenciaOperacionalActionForm.ocorrenciaTipo, 'Tipo de Ocorrência') && 
	testarCampoValorZero(document.FiltrarOcorrenciaOperacionalActionForm.motivoOcorrencia, 'Motivo da Ocorrência') &&
	validateDate(form) &&
	validateLong(form) ;
}


	function validarForm(){
		var form = document.forms[0];
		
			if(validateFiltrarOcorrenciaOperacionalActionForm(form)){
					submeterFormPadrao(form);
			}
	}


	function carregarMotivoOcorrencia(){
		var form = document.forms[0];
		form.action = 'exibirFiltrarOcorrenciaOperacionalAction.do';
		form.submit();
		}

	 	


	function IntegerValidations() {
		this.aa = new Array("codigoMunicipio","Município deve somente conter números positivos.", new Function(	"varName", " return this[varName];"));
	}

	function DateValidations() {
		this.aa = new Array("dataPrevisao","Data de Previsão Inválida.",new Function("varName",	"this.datePattern='dd/MM/yyyy';  return this[varName];"));
		this.ab = new Array("dataReprogramacao","Data de Reprogramação Inválida.",new Function("varName",	"this.datePattern='dd/MM/yyyy';  return this[varName];"));
		this.ac = new Array("dataConclusao","Data de Conclusão Inválida.",new Function("varName",	"this.datePattern='dd/MM/yyyy';  return this[varName];"));
	}

	function limparMunicipio() {
		var form = document.FiltrarOcorrenciaOperacionalActionForm;
		form.codigoMunicipio.value = "";
		form.descricaoMunicipio.value = "";
		form.localidade.value = "";
		form.bairro.value = "";
	}

	/* Chama Popup */
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,	objetoRelacionado) {
		if (objetoRelacionado.disabled != true) {
			if (objeto == null || codigoObjeto == null) {
				abrirPopup(url + "&" + "tipo=" + tipo, altura, largura);
			} else {
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)) {
					alert(msg);
				} else {
					abrirPopup(url + "&" + "tipo=" + tipo + "&" + objeto + "="+ codigoObjeto + "&caminhoRetornoTelaPesquisa="+ tipo, altura, largura);
				}
			}
		}
	}

	/* Recupera Dados Popup */
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro,
			tipoConsulta) {
		var form = document.forms[0];

		form.action = 'exibirFiltrarOcorrenciaOperacionalAction.do';
		if (tipoConsulta == 'municipio') {
			form.codigoMunicipio.value = codigoRegistro;
			form.descricaoMunicipio.value = descricaoRegistro;
			form.descricaoMunicipio.style.color = "#000000";
		}
		submeterFormPadrao(form);
	}

	function somenteNumeros(evento) {
		
	    var tecla = null;
		
		  if(window.event){
		  		tecla = evento.keyCode;
		  }else if(evento.which){
		    	tecla = evento.which;
		  }
		  
		  if(tecla == null){//tab
			  return true;
		  }

		  if((tecla > 47 && tecla < 58) || (tecla.value == 'undefined')){
		    return true;
		  }
		  
	      if (tecla == 8 || tecla == 13){
		        return true;
	      }
		  
	      return false;
	}
</script>


</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">

	<html:form action="/filtrarOcorrenciaOperacionalAction.do" method="post"
		name="FiltrarOcorrenciaOperacionalActionForm"
		type="gsan.gui.atendimentopublico.registroatendimento.FiltrarOcorrenciaOperacionalActionForm"
		onsubmit="return validateFiltrarOcorrenciaOperacionalActionForm(this);">

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

					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="11"><img border="0"
								src="imagens/parahead_left.gif" /></td>
							<td class="parabg">Filtrar Ocorrência Operacional</td>
							<td width="11" valign="top"><img border="0"
								src="imagens/parahead_right.gif" /></td>
						</tr>

					</table>

					<p>&nbsp;</p>

					<table width="100%" border="0">

						<tr>
							<td colspan="3">Para filtrar uma ocorrência, informe os
								dados abaixo:</td>
								<td width="100" align="right"><html:checkbox property="atualizar" value="1" /><strong>Atualizar</strong></td>
						</tr>


						<tr>
							<td><strong>Município: 
							</strong></td>
							<td colspan="3"><html:text maxlength="5" property="codigoMunicipio"
									size="5" onkeyup="javascript:limparMunicipioTecla();"
									onkeyup="javascript:verificaNumeroInteiro(this);"
									onblur="javascript:somente_numero(this);mascara(this, mascaraInteiro);"
									onkeypress="javascript:validaEnterComMensagem(event, 'exibirFiltrarOcorrenciaOperacionalAction.do', 'codigoMunicipio', 'Município');" />

								<a
								href="javascript:abrirPopup('exibirPesquisarMunicipioAction.do');">
									<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Município" /></a>
									<logic:present
									name="idMunicipioNaoEncontrado" scope="request">
									<html:text maxlength="30" property="descricaoMunicipio"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000"
										size="40" />
								</logic:present> <logic:notPresent name="idMunicipioNaoEncontrado"
									scope="request">
									<html:text maxlength="30" property="descricaoMunicipio"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="40" />
								</logic:notPresent> <a href="javascript:limparMunicipio();"> <img
									src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" title="Apagar" /> </a>
							</td>
						</tr>


						<tr>
							<td><strong>Localidade:
							</strong></td>
							<td colspan="3"><html:select property="localidade">
									<option value="-1"></option>
									<html:options name="request" collection="colecaoLocalidade"
										labelProperty="descricao" property="id" />
								</html:select>
							</td>
						</tr>


						<tr>
							<td><strong>Bairro:</strong>
							</td>
							<td colspan="3"><html:select property="bairro">
									<option value="-1"></option>
									<html:options name="request" collection="colecaoBairro"
										labelProperty="nome" property="id" />
								</html:select>
							</td>
						</tr>

						<tr>
							<td><strong>Tipo de Ocorrência: </strong></td>
							<td colspan="3"><html:select property="ocorrenciaTipo" >
									<option value="-1"></option>
									<html:options name="request"
										collection="colecaoOcorrenciaOperacionalTipo"
										labelProperty="descricao" property="id" />
								</html:select>
							</td>
						</tr>

						<tr>
							<td><strong>Motivo da Ocorrência:</strong></td>
							<td colspan="3"><html:select property="motivoOcorrencia">
									<option value="-1"></option>
									<html:options name="request"
										collection="colecaoOcorrenciaOperacionalMotivo"
										labelProperty="descricao" property="id" />
								</html:select>
							</td>
						</tr>

						<tr>
							<td><strong>Previsão da Conclusão:</strong></td>
							<td><html:text property="dataPrevisao" size="10"
									maxlength="10" tabindex="6"
									onkeyup="javascript:somente_numero(this);mascaraDataValidaDiaMesAno(this, event);" 
									onblur="somente_numero(this);mascaraDataValidaDiaMesAno(this, event)"/> <a
								href="javascript:abrirCalendario('FiltrarOcorrenciaOperacionalActionForm', 'dataPrevisao')">
									<img border="0"	src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a><font size="2">dd/mm/aaaa</font>
							</td>
							
							<td colspan="2"><strong> 
							<html:radio property="codigoPeriodoPrevisao" value="1" onmousedown="selecionarRadio(this, event);" onclick="evitarEvento(event);"/> <strong>Manhã
							<html:radio property="codigoPeriodoPrevisao" value="2" onmousedown="selecionarRadio(this, event);" onclick="evitarEvento(event);"/> Tarde
							<html:radio property="codigoPeriodoPrevisao" value="3" onmousedown="selecionarRadio(this, event);" onclick="evitarEvento(event);"/> Noite
							</strong>
							</td>
						</tr>

						<tr>
							<td><strong>Reprogramação:</strong></td>
							<td><html:text property="dataReprogramacao" size="10"
									maxlength="10" tabindex="6"
									onkeyup="javascript:somente_numero(this);mascaraDataValidaDiaMesAno(this, event);" 
									onblur="somente_numero(this);mascaraDataValidaDiaMesAno(this, event)"/> <a
								href="javascript:abrirCalendario('FiltrarOcorrenciaOperacionalActionForm', 'dataReprogramacao')">
									<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a><font size="2">dd/mm/aaaa</font>
							</td>
							<td colspan="2"><strong> 
							<html:radio property="codigoPeriodoReprogramacao" value="1" onmousedown="selecionarRadio(this, event);" onclick="evitarEvento(event);"/> <strong>Manhã
							<html:radio property="codigoPeriodoReprogramacao" value="2" onmousedown="selecionarRadio(this, event);" onclick="evitarEvento(event);"/> Tarde
							<html:radio property="codigoPeriodoReprogramacao" value="3" onmousedown="selecionarRadio(this, event);" onclick="evitarEvento(event);"/> Noite
							</strong>
							</td>
						</tr>

						<tr>
							<td><strong>Conclusão:</strong></td>
							<td><html:text property="dataConclusao" size="10"
									maxlength="10" tabindex="6"
									onkeyup="javascript:somente_numero(this);mascaraDataValidaDiaMesAno(this, event);" 
									onblur="somente_numero(this);mascaraDataValidaDiaMesAno(this, event)"/> <a
								href="javascript:abrirCalendario('FiltrarOcorrenciaOperacionalActionForm', 'dataConclusao')">
									<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a><font size="2">dd/mm/aaaa</font>
							</td>
							<td colspan="2"><strong> 
							<html:radio property="codigoPeriodoConclusao" value="1" onmousedown="selecionarRadio(this, event);" onclick="evitarEvento(event);"/> <strong>Manhã
							<html:radio property="codigoPeriodoConclusao" value="2" onmousedown="selecionarRadio(this, event);" onclick="evitarEvento(event);"/> Tarde
							<html:radio property="codigoPeriodoConclusao" value="3" onmousedown="selecionarRadio(this, event);" onclick="evitarEvento(event);"/> Noite
							</strong>
							</td>
						</tr>

						<tr>
							<td colspan="3"><input name="Button" type="button"
								class="bottonRightCol" value="Limpar" align="left"
								onclick="window.location.href='<html:rewrite page="/exibirFiltrarOcorrenciaOperacionalAction.do?menu=sim"/>'">

								<input name="Button" type="button" class="bottonRightCol"
								value="Cancelar" align="left"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
							</td>

							<td align="right"><input name="Button" type="button"
								class="bottonRightCol" value="Filtrar" align="right"
								onClick="javascript:validarForm();">
							</td>
						</tr>
					</table>
					<p>&nbsp;</p>
			</tr>
		</table>

		<tr>
			<td colspan="3"><%@ include file="/jsp/util/rodape.jsp"%>
		</tr>
	</html:form>
</body>
</html:html>