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
<html:javascript staticJavascript="false"
	formName="AtualizarOcorrenciaOperacionalActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<html:javascript staticJavascript="false" formName="AtualizarOcorrenciaOperacionalActionForm" />
<script language="JavaScript">

var bCancel = false;

function validateAtualizarOcorrenciaOperacionalActionForm(form) {
    if (bCancel)
  return true;
    else

  return testarCampoValorZero(document.AtualizarOcorrenciaOperacionalActionForm.codigoMunicipio, 'Município') &&
	testarCampoValorZero(document.AtualizarOcorrenciaOperacionalActionForm.localidade, 'Localidade') && 
	testarCampoValorZero(document.AtualizarOcorrenciaOperacionalActionForm.bairro, 'Bairro') &&
	testarCampoValorZero(document.AtualizarOcorrenciaOperacionalActionForm.ocorrenciaTipo, 'Tipo de Ocorrência') && 
	testarCampoValorZero(document.AtualizarOcorrenciaOperacionalActionForm.motivoOcorrencia, 'Motivo da Ocorrência') &&
	validaHoraMinutoMensagem(form.horaOcorrencia.value,'Hora da Ocorrência') &&
	validateRequired(form) &&
	validateDate(form) &&
	validateLong(form) &&
	validateNome(form) ;
}


function validarForm(){
	var form = document.forms[0];
		if(validateAtualizarOcorrenciaOperacionalActionForm(form)){
			if(form.dataPrevisao.value==""){
				if(form.dataReprogramacao.value==""){
					if(form.dataConclusao.value==""){
						submeterFormPadrao(form);
					}else{
						if(comparaData(form.dataOcorrencia.value,"<=",form.dataConclusao.value)){
							submeterFormPadrao(form);
						}else{
							alert("Data de Conclusão deve ser superior a Data da Ocorrência");
						}
					}
				}else{
					if(comparaData(form.dataOcorrencia.value,"<=",form.dataReprogramacao.value)){
						if(form.dataConclusao.value!=""){
							if(comparaData(form.dataOcorrencia.value,"<=",form.dataConclusao.value)){
								submeterFormPadrao(form);
							}else{
								alert("Data de Conclusão deve ser superior a Data da Ocorrência");
							}
						}else{
							submeterFormPadrao(form);
						}	
					}else{
						alert("Data de Reprogramação deve ser superior a Data da Ocorrência");
					}
				}
			}else if(form.dataPrevisao.value!=""){
				if(comparaData(form.dataOcorrencia.value,"<=",form.dataPrevisao.value)){
					if(form.dataReprogramacao.value!=""){
						if(comparaData(form.dataOcorrencia.value,"<=",form.dataReprogramacao.value)){
							if(form.dataConclusao.value!=""){
								if(comparaData(form.dataOcorrencia.value,"<=",form.dataConclusao.value)){
									submeterFormPadrao(form);
								}else{
									alert("Data de Conclusão deve ser superior a Data da Ocorrência");
								}
							}else{
								submeterFormPadrao(form);
							}
						}else{
							alert("Data de Reprogramação deve ser superior a Data da Ocorrência");
						}
					}else{
						if(form.dataReprogramacao.value=="" && form.dataConclusao.value==""){
							submeterFormPadrao(form);
						}
						if(form.dataConclusao.value!=""){
							if(comparaData(form.dataOcorrencia.value,"<=",form.dataConclusao.value)){
								submeterFormPadrao(form);
							}else{
								alert("Data de Conclusão deve ser superior a Data da Ocorrência");
							}
						}
					}
				}else{
					alert("Data de Previsão de Conclusão deve ser superior a Data da Ocorrência");
				}
			}
		}
}


	function carregarMotivoOcorrencia(){
		var form = document.forms[0];
		form.action = 'exibirAtualizarOcorrenciaOperacionalAction.do?pesquisar=sim';
		form.submit();
		}

	 	

	function validateNome(form) {
		var bValid = true;
		var focusField = null;
		var i = 0;
		var fields = new Array();
		oNome = new nome();
		for (x in oNome) {
			if ((form[oNome[x][0]].type == 'text'
					|| form[oNome[x][0]].type == 'textarea' || form[oNome[x][0]].type == 'password')
					&& (form[oNome[x][0]].value.length > 0)) {
				if (validacaoNome(form[oNome[x][0]].value)) {
					if (i == 0) {
						focusField = form[oNome[x][0]];
					}
					fields[i++] = oNome[x][1];
					bValid = false;
				}
			}
		}
		if (fields.length > 0) {
			focusField.focus();
			alert(fields.join('\n'));
		}
		return bValid;
	}

	function validacaoNome(c) {
		var achou = false;

		var indesejaveis = "~{}^%$[]@|`\\<¨\#?!;*>\"\'+()&¢¬=¡²³¤€¼½¾‘’¥×áßð/£1234567890§:´";

		for ( var i = 0; i < indesejaveis.length; i++) {
			if ((c.indexOf(indesejaveis.charAt(i))) != -1) {
				achou = true;
			}
		}

		return achou;
	}

	function nome() {
		//this.aa = new Array("observacao","Observação possui caracteres especiais ou números.", new Function("varName", " return this[varName];"));
	}

	function IntegerValidations() {
		this.aa = new Array("codigoMunicipio","Município deve somente conter números positivos.", new Function(	"varName", " return this[varName];"));
	}

	function DateValidations() {
		this.aa = new Array("dataPrevisao","Data de Previsão Inválida.",new Function("varName",	"this.datePattern='dd/MM/yyyy';  return this[varName];"));
		this.ab = new Array("dataReprogramacao","Data de Reprogramação Inválida.",new Function("varName",	"this.datePattern='dd/MM/yyyy';  return this[varName];"));
		this.ac = new Array("dataConclusao","Data de Conclusão Inválida.",new Function("varName",	"this.datePattern='dd/MM/yyyy';  return this[varName];"));
		this.ad = new Array("dataOcorrencia","Data da Ocorrência Inválida.",new Function("varName",	"this.datePattern='dd/MM/yyyy';  return this[varName];"));
	}

	 function required () { 
	     this.aa = new Array("codigoMunicipio", "Informe Município.", new Function ("varName", " return this[varName];"));
	      this.ab = new Array("localidade", "Informe Localidade.", new Function ("varName", " return this[varName];"));
	      this.ac = new Array("bairro", "Informe Bairro.", new Function ("varName", " return this[varName];"));
	      this.ad = new Array("ocorrenciaTipo", "Informe Tipo de Ocorrência.", new Function ("varName", " return this[varName];"));
	      this.ae = new Array("motivoOcorrencia", "Informe Motivo da Ocorrência.", new Function ("varName", " return this[varName];"));
	      this.af = new Array("descricao", "Informe Descrição da Ocorrência.", new Function ("varName", " return this[varName];"));
	      this.ag = new Array("dataOcorrencia", "Informe Data da Ocorrência.", new Function ("varName", " return this[varName];"));
	      this.ah = new Array("horaOcorrencia", "Informe Hora da Ocorrência.", new Function ("varName", " return this[varName];"));
	      this.ai = new Array("areasAfetadas", "Informe Áreas Afetadas.", new Function ("varName", " return this[varName];"));
	      this.aj = new Array("dataPrevisao", "Informe Previsão de Conclusão.", new Function ("varName", " return this[varName];"));
	    } 

	function limparMunicipio() {
		var form = document.AtualizarOcorrenciaOperacionalActionForm;
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

		form.action = 'exibirAtualizarOcorrenciaOperacionalAction.do?pesquisar=sim';
		if (tipoConsulta == 'municipio') {
			form.codigoMunicipio.value = codigoRegistro;
			form.descricaoMunicipio.value = descricaoRegistro;
			form.descricaoMunicipio.style.color = "#000000";
		}
		submeterFormPadrao(form);
	}
</script>


</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">

	<html:form action="/atualizarOcorrenciaOperacionalAction.do"
		method="post" name="AtualizarOcorrenciaOperacionalActionForm"
		type="gcom.gui.atendimentopublico.registroatendimento.AtualizarOcorrenciaOperacionalActionForm"
		onsubmit="return validateAtualizarOcorrenciaOperacionalActionForm(this);">

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
							<td class="parabg">Atualizar Ocorrência Operacional</td>
							<td width="11" valign="top"><img border="0"
								src="imagens/parahead_right.gif" /></td>
						</tr>

					</table>

					<p>&nbsp;</p>

					<table width="100%" border="0">

						<tr>
							<td colspan="3">Para atualizar uma ocorrência, informe os
								dados abaixo:</td>
						</tr>

						<tr>
							<td><strong>Código:</strong>
							</td>
							<td colspan="3"> <html:hidden property="id" /> 
							<bean:write
									name="AtualizarOcorrenciaOperacionalActionForm"
									property="id" />
							</td>
						</tr>


						<tr>
							<td><strong>Município:<font color="#FF0000">*</font>
							</strong></td>
							<td colspan="2"><html:text maxlength="5"
									property="codigoMunicipio" size="5" tabindex="1"
									onkeyup="javascript:limparMunicipioTecla();"
									onkeyup="javascript:verificaNumeroInteiro(this);"
									onblur="javascript:somente_numero(this);mascara(this, mascaraInteiro);"
									onkeypress="javascript:validaEnterComMensagem(event, 'exibirAtualizarOcorrenciaOperacionalAction.do?pesquisar=sim', 'codigoMunicipio', 'Município');" />

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
							<td><strong>Localidade:<font color="#ff0000">*</font>
							</strong></td>
							<td colspan="2"><html:select property="localidade" tabindex="2">
									<option value="-1"></option>
									<html:options name="request" collection="colecaoLocalidade"
										labelProperty="descricao" property="id" />
								</html:select>
							</td>
						</tr>


						<tr>
							<td><strong>Bairro:<font color="#ff0000">*</font> </strong>
							</td>
							<td colspan="2"><html:select property="bairro" tabindex="3">
									<option value="-1"></option>
									<html:options name="request" collection="colecaoBairro"
										labelProperty="nome" property="id" />
								</html:select>
							</td>
						</tr>

						<tr>
							<td><strong>Tipo de Ocorrência:<font
									color="#ff0000">*</font> </strong></td>
							<td colspan="2"><html:select property="ocorrenciaTipo"
									onchange="javascript:carregarMotivoOcorrencia();" tabindex="3">
									<option value="-1"></option>
									<html:options name="request"
										collection="colecaoOcorrenciaOperacionalTipo"
										labelProperty="descricao" property="id" />
								</html:select>
							</td>
						</tr>

						<tr>
							<td><strong>Motivo da Ocorrência:<font
									color="#ff0000">*</font> </strong></td>
							<td colspan="2"><html:select property="motivoOcorrencia" tabindex="4">
									<option value="-1"></option>
									<html:options name="request"
										collection="colecaoOcorrenciaOperacionalMotivo"
										labelProperty="descricao" property="id" />
								</html:select>
							</td>
						</tr>


						<tr>
							<td height="10" width="30"><strong>Descrição da
									Ocorrência:<font color="#ff0000">*</font> </strong>
							</td>
							<td colspan="2"><strong> <html:textarea rows="5" tabindex="5"
										cols="40" property="descricao"
										onkeypress="return limitarTextArea(document.forms[0].descricao, 200, document.getElementById('utilizadoDescricao'), document.getElementById('limiteDescricao'),event);" />

									<span id="utilizadoDescricao">0</span>/<span
									id="limiteDescricao">200</span> </strong>
							</td>
						</tr>



						<tr>
							<td><strong>Data da Ocorrência:<font
									color="#ff0000">*</font> </strong></td>
							<td><html:text property="dataOcorrencia" size="10"
									maxlength="10" tabindex="6"
									onkeyup="javascript:somente_numero(this);mascaraDataValidaDiaMesAno(this, event);" 
									onblur="somente_numero(this);mascaraDataValidaDiaMesAno(this, event)"/> <a
								href="javascript:abrirCalendario('AtualizarOcorrenciaOperacionalActionForm', 'dataOcorrencia')">
									<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a><font size="2">dd/mm/aaaa</font>
							</td>
						</tr>

						<tr>
							<td><strong>Horário da Ocorrência:<font
									color="#ff0000">*</font> </strong></td>
							<td colspan="2"><html:text property="horaOcorrencia"
									size="5" maxlength="5" tabindex="7"
									onkeyup="mascaraHora(this, event);"
									/> <font size="2">hh:mm</font>
							</td>
						</tr>


						<tr>
							<td height="10" width="30"><strong>Áreas Afetadas:<font
									color="#ff0000">*</font> </strong>
							</td>
							<td colspan="2"><strong> <html:textarea rows="5" tabindex="8"
										cols="40" property="areasAfetadas"
										onkeypress="return limitarTextArea(document.forms[0].areasAfetadas, 200, document.getElementById('utilizadoArea'), document.getElementById('limiteArea'),event);" />

									<span id="utilizadoArea">0</span>/<span id="limiteArea">200</span>
							</strong>
							</td>
						</tr>


						<tr>
							<td><strong>Previsão da Conclusão:<font
									color="#ff0000">*</font></strong></td>
							<td><html:text property="dataPrevisao" size="10"
									maxlength="10" tabindex="9"
									onkeyup="javascript:somente_numero(this);mascaraDataValidaDiaMesAno(this, event);" 
									onblur="somente_numero(this);mascaraDataValidaDiaMesAno(this, event)"/> <a
								href="javascript:abrirCalendario('AtualizarOcorrenciaOperacionalActionForm', 'dataPrevisao')">
									<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a><font size="2">dd/mm/aaaa</font>
							</td>

							<td><strong> <html:radio
										property="codigoPeriodoPrevisao" value="1" onmousedown="selecionarRadio(this, event);" onclick="evitarEvento(event);"/> <strong>Manhã
										<html:radio property="codigoPeriodoPrevisao" value="2" onmousedown="selecionarRadio(this, event);" onclick="evitarEvento(event);"/>
										Tarde <html:radio property="codigoPeriodoPrevisao" value="3" onmousedown="selecionarRadio(this, event);" onclick="evitarEvento(event);"/>
										Noite </strong>
							</td>
						</tr>

						<tr>
							<td><strong>Reprogramação:</strong></td>
							<td><html:text property="dataReprogramacao" size="10"
									maxlength="10" tabindex="10"
									onkeyup="javascript:somente_numero(this);mascaraDataValidaDiaMesAno(this, event);" 
									onblur="somente_numero(this);mascaraDataValidaDiaMesAno(this, event)"/> <a
								href="javascript:abrirCalendario('AtualizarOcorrenciaOperacionalActionForm', 'dataReprogramacao')">
									<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a><font size="2">dd/mm/aaaa</font>
							</td>
							<td><strong> <html:radio
										property="codigoPeriodoReprogramacao" value="1" onmousedown="selecionarRadio(this, event);" onclick="evitarEvento(event);"/> <strong>Manhã
										<html:radio property="codigoPeriodoReprogramacao" value="2" onmousedown="selecionarRadio(this, event);" onclick="evitarEvento(event);"/>
										Tarde <html:radio property="codigoPeriodoReprogramacao"
											value="3" onmousedown="selecionarRadio(this, event);" onclick="evitarEvento(event);"/> Noite </strong>
							</td>
						</tr>

						<tr>
							<td><strong>Conclusão:</strong></td>
							<td><html:text property="dataConclusao" size="10"
									maxlength="10" tabindex="11"
									onkeyup="javascript:somente_numero(this);mascaraDataValidaDiaMesAno(this, event);" 
									onblur="somente_numero(this);mascaraDataValidaDiaMesAno(this, event)"/> <a
								href="javascript:abrirCalendario('AtualizarOcorrenciaOperacionalActionForm', 'dataConclusao')">
									<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a><font size="2">dd/mm/aaaa</font>
							</td>
							<td><strong> <html:radio
										property="codigoPeriodoConclusao" value="1" onmousedown="selecionarRadio(this, event);" onclick="evitarEvento(event);"/> <strong>Manhã
										<html:radio property="codigoPeriodoConclusao" value="2" onmousedown="selecionarRadio(this, event);" onclick="evitarEvento(event);"/>
										Tarde <html:radio property="codigoPeriodoConclusao" value="3" onmousedown="selecionarRadio(this, event);" onclick="evitarEvento(event);"/>
										Noite </strong>
							</td>
						</tr>

						<tr>
							<td height="10" width="30"><strong>Observa&ccedil;&atilde;o:</strong>
							</td>
							<td colspan="2"><strong> <html:textarea rows="5" tabindex="11"
										cols="40" property="observacao"
										onkeypress="return limitarTextArea(document.forms[0].observacao, 200, document.getElementById('utilizadoObservacao'), document.getElementById('limiteObservacao'),event);" />

									<span id="utilizadoObservacao">0</span>/<span
									id="limiteObservacao">200</span> </strong></td>
						</tr>


						<tr>
							<td></td>
							<td align="right" colspan="2">
								<div align="left">
									<strong><font color="#FF0000">*</font> </strong> Campos
									obrigat&oacute;rios
								</div>
							</td>
						</tr>

						<tr>



							<td colspan="2"><logic:present name="manter" scope="session">
									<input type="button" name="ButtonReset" class="bottonRightCol"
										value="Voltar"
										onClick="javascript:window.location.href='/gsan/exibirManterOcorrenciaOperacionalAction.do'">
								</logic:present> <logic:notPresent name="manter" scope="session">
									<input type="button" name="ButtonReset" class="bottonRightCol"
										value="Voltar"
										onClick="javascript:window.location.href='/gsan/exibirFiltrarOcorrenciaOperacionalAction.do'">
								</logic:notPresent> <input name="Button" type="button" class="bottonRightCol"
								value="Desfazer" align="left"
								onclick="window.location.href='<html:rewrite page="/exibirAtualizarOcorrenciaOperacionalAction.do?desfazer=s"/>'">

								<input name="Button" type="button" class="bottonRightCol"
								value="Cancelar" align="left"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
							</td>

							<td align="right"><input name="Button" type="button"
								class="bottonRightCol" value="Atualizar" align="right"
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