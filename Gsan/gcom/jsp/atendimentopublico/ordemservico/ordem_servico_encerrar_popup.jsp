<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.gui.GcomAction"%>
<%@ page import="gcom.atendimentopublico.ordemservico.OrdemServicoFoto"%>
<%@ page import="gcom.atendimentopublico.ordemservico.ServicoTipo" %>
<%@ page import="gcom.atendimentopublico.ordemservico.ServicoTipoReferencia" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	  href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	  type="text/css">


<%@ page import="gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento"%>
<%@ page import="gcom.atendimentopublico.ordemservico.OsReferidaRetornoTipo"%>	

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script src="<bean:message key="caminho.js"/>jquery/jquery.js"></script>

<html:javascript staticJavascript="false"  formName="EncerrarOrdemServicoActionForm" dynamicJavascript="false" />

<script type="text/javascript">
jQuery(document).ready(function() {
	
	var indExPav = "input[@name='indicadorExistePavimento']";
	cleanAndHideInd();
	
	if($(indExPav).is(':checked')){
		$(indExPav).removeAttr("checked");
	}	
	
	$(indExPav).click(function(){
		if($(this).val() == 1){
			cleanAndHideInd()		
			showInd()
		}else if ($(this).val() == 2){
			cleanAndHideIndCalc()
			 showIndCalc()
		}
		else{
			cleanAndHideInd();			
		}
		
	});	
	
});

function showInd(){
	$("#trQtdRepM2Asf").show();
	$("#trQtdRepM2Par").show();
	$("#trQtdRepM2Cal").show();

	$("#trTipoPavim").show();
	$("#trCalcada").show();
}

function showIndCalc(){
	$("#trCalcada").show();
}

function cleanAndHideInd(){
	$("#trQtdRepM2Asf").hide();
	$("#trQtdRepM2Par").hide();
	$("#trQtdRepM2Cal").hide();	
	$("#trTipoPavim").hide();
	$("#trCalcada").hide();
	$("input[name='qtdeReposicaoAsfalto']").attr('value', '0,00');
	$("input[name='qtdeReposicaoParalelo']").attr('value', '0,00');
	$("input[name='qtdeReposicaoCalcada']").attr('value', '0,00');

	var indExTipoPavimento = "input[@name='tipoPavimento']";
	if($(indExTipoPavimento).is(':checked')){
		$(indExTipoPavimento).removeAttr("checked");
	}	

	var indExCalcada = "input[@name='indicadorExisteCalcada']";
	if($(indExCalcada).is(':checked')){
		$(indExCalcada).removeAttr("checked");
	}	
	
	
}

function cleanAndHideIndCalc(){
	$("#trQtdRepM2Asf").hide();
	$("#trQtdRepM2Par").hide();
	$("#trQtdRepM2Cal").hide();	
	$("#trTipoPavim").hide();
	$("input[name='qtdeReposicaoAsfalto']").attr('value', '0,00');
	$("input[name='qtdeReposicaoParalelo']").attr('value', '0,00');
	$("input[name='qtdeReposicaoCalcada']").attr('value', '0,00');

	var indExTipoPavimento = "input[@name='tipoPavimento']";
	if($(indExTipoPavimento).is(':checked')){
		$(indExTipoPavimento).removeAttr("checked");
	}	

	var indExCalcada = "input[@name='indicadorExisteCalcada']";
	if($(indExCalcada).is(':checked')){
		$(indExCalcada).removeAttr("checked");
	}	
	
}
</script>

<script language="JavaScript">

var bCancel = false;

    function validateEncerrarOrdemServicoActionForm(form) {
        if (bCancel)
      return true;
        else
      return validateCaracterEspecial(form) && validateRequired(form) && validateDate(form) && encerrar();
   }

    function caracteresespeciais () { 
        this.aa = new Array("dataEncerramento", "Data de Encerramento possui caracteres especiais.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
        this.ab = new Array("horaEncerramento", "Hora do Encerramento possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    } 

    function required () { 
     this.aa = new Array("dataEncerramento", "Informe Data de Encerramento.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
     this.ab = new Array("idMotivoEncerramento", "Informe Motivo do Encerramento.", new Function ("varName", " return this[varName];"));
    } 

    function DateValidations () { 
     this.aa = new Array("dataEncerramento", "Data de Encerramento inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
    } 
	
	function extendeTabela(tabela,display){
		var form = document.forms[0];

		if(display){
 			eval('layerHide'+tabela).style.display = 'none';
 			eval('layerShow'+tabela).style.display = 'block';
		}else{
			eval('layerHide'+tabela).style.display = 'block';
 			eval('layerShow'+tabela).style.display = 'none';
		}
	}
	
	function carregarCampos(){
		
		var form = document.forms[0];
		
		redirecionarSubmit('exibirEncerrarOrdemServicoPopupAction.do?carregarCampos=OK');
		
	}
	function informarAtividade(){
	var form = document.forms[0];
		redirecionarSubmit('exibirManterDadosAtividadesOrdemServicoAction.do?numeroOS=' + form.numeroOS.value);
	}
	
	function informarOSFiscalizacao(){
		
		var form = document.forms[0];
		 redirecionarSubmit('exibirEncerrarOrdemServicoPopupAction.do?redirecionarPagina=exibirGerarOSInserirRA');
	}
	
	function encerrar(){
	 var form = document.forms[0];
	 var mensagem = '';
	 if(form.indicadorExecucao.value != ''){
	   if(form.indicadorExecucao.value == form.indicadorExecucaoSim.value){
		 if(form.tipoServicoReferenciaId.value != ''){
		  if(form.idTipoRetornoReferida.value == ''){
		   if(mensagem == ''){
		     mensagem = 'Informe Tipo Retorno Referida.';
		   }else{
		    mensagem = mensagem + '\nInforme Tipo Retorno Referida.';
		   }
		  }
		  /**  if(form.indicadorDeferimento.value != '' 
		    && form.indicadorDeferimento.value == form.indicadorDeferimentoSim.value){
			  if(form.pavimento != null && form.indicadorPavimento.value != ''){
			    if(form.indicadorPavimento.value == form.indicadorPavimentoSim.value){
				  if(form.pavimento.value == ''){
				    if(mensagem == ''){
		             mensagem = 'Informe Pavimento.';
		            }else{
		             mensagem = mensagem + '\nInforme Pavimento.';
		            }
				  }else{
				   if(!testarCampoValorZero(document.EncerrarOrdemServicoActionForm.pavimento, 'Pavimento')){
				    return false;
				   }
				  }
				}
			 }
		 }
		 */
		 if(form.servicoTipoObrigatorio.value != ''){
			if(form.servicoTipoObrigatorio.value == "SIM"){ 
			 if(form.idServicoTipo.value == ''){
			   if(mensagem == ''){
			     mensagem = 'Informe Tipo de Serviço.';
			   }else{
			    mensagem = mensagem +  '\nInforme Tipo de Serviço.';
			   }
			 }}else{
				   if(!testarCampoValorZero(document.EncerrarOrdemServicoActionForm.idServicoTipo, 'Tipo de Serviço')){
				    return false;
				   }
			 }
			} 
		  }
		 }else{
			 if(form.pavimento != null && form.indicadorPavimento.value != ''){
				if(form.indicadorPavimento.value == form.indicadorPavimentoSim.value){
				  if(form.pavimento.value == ''){
				    if(mensagem == ''){
		     		  mensagem = 'Informe Pavimento.';
		   			}else{
		    		  mensagem = mensagem +  '\nInforme Pavimento.';
		   			}
				  }else{
				   if(!testarCampoValorZero(document.EncerrarOrdemServicoActionForm.pavimento, 'Pavimento')){
				    return false;
				   }
				  }
				}
			 }
		 }
	   }
	 if(form.indicadorVistoriaServicoTipo.value == '1'){	 
	  if((!form.codigoRetornoVistoriaOs[0].checked) && (!form.codigoRetornoVistoriaOs[1].checked)){
	     if(mensagem == ''){
		   mensagem = 'Informe Retorno Vistoria.';
		 }else{
		   mensagem = mensagem +  '\nInforme Retorno Vistoria.';
		 }
	  }
	 } 
	 
	  var INDICADOR_DIAGNOSTICO_ATIVO = document.getElementById("INDICADOR_DIAGNOSTICO_ATIVO").value;
	 if(form.indicadorDiagnostico != null && form.indicadorDiagnostico.value == INDICADOR_DIAGNOSTICO_ATIVO){	 
	  if(form.observacaoEncerramento == null || form.observacaoEncerramento.value == ''){
	     if(mensagem == ''){
		   mensagem = 'Para serviço de diagnóstico, Parecer do Encerramento é obrigatório.';
		 }else{
		   mensagem = mensagem +  '\nPara serviço de diagnóstico, Parecer do Encerramento é obrigatório.';
		 }
	  }
	 } 

	  if(form.indicadorExistePavimento != null && form.indicadorExistePavimento[0].checked) {

		  form.tipoPavimento[0].checked
		  
		  if(!form.tipoPavimento[0].checked && !form.tipoPavimento[1].checked) {
			  if(mensagem == ''){
				  mensagem = 'Informe Tipo de Pavimento.';		  		 
			  }else{
				  mensagem = mensagem +  '\nInforme Tipo de Pavimento.';		 		  
			  }
		  }
		  if(!form.indicadorExisteCalcada[0].checked && !form.indicadorExisteCalcada[1].checked) {
			  if(mensagem == ''){
				  mensagem = 'Informe Com Calçada?.';		  		 
			  }else{
				  mensagem = mensagem +  '\nInforme Com Calçada?.';		 		  
			  }
		  }
 	 }
	 
	 if(mensagem == ''){
	   return true;
	 }else{
	  alert(mensagem);
	 return false;
	 }
	 
	}
	
	function validarForm(form){
     if(validateEncerrarOrdemServicoActionForm(form)){
	
	//alert(form.idPavimentoRua.value);

     if ( form.idPavimentoRua != undefined && form.idPavimentoRua != 'undefined'  
     		&& form.idPavimentoRua.value != null && form.idPavimentoRua.value != '' ) {
    
	     	if (form.metragemPavimentoRua.value != null && form.metragemPavimentoRua.value != '' &&
	     		testarCampoValorZeroDecimal(form.metragemPavimentoRua, 'Metragem') ) {
	     	  
		     	if (form.indicadorExecucao.value == form.indicadorExecucaoSim.value && form.mostrarAlert.value != null && form.mostrarAlert.value != '') {
			  		alert('Este encerramento não gerará débito, pois já existe uma guia de pagamento para este tipo de serviço.');
			  	}
		
		     	submeterFormPadrao(form);
			} else if (form.metragemPavimentoRua.value == null || form.metragemPavimentoRua.value == ''){
				alert("Informe o campo metragem.");
			}
		 
     } else {
     
     	if (form.indicadorExecucao.value == form.indicadorExecucaoSim.value && form.mostrarAlert.value != null && form.mostrarAlert.value != '') {
	  		alert('Este encerramento não gerará débito, pois já existe uma guia de pagamento para este tipo de serviço.');
	  	}
     	submeterFormPadrao(form);
      }
    }
   }
	
	function limparPesquisaServicoTipo(){
	 var form = document.forms[0];
	 form.idServicoTipo.value = '';
	 form.descricaoServicoTipo.value = '';
	}
	
	function carregarCamposEncerrarComExecucaoComReferencia(){
		
	 var form = document.forms[0];
	 redirecionarSubmit('exibirEncerrarOrdemServicoPopupAction.do?carregarCamposComReferencia=OK');
		
	}
	
	function telaAtencao(mensagem){
	 if(mensagem != ''){
	  alert(mensagem);
	 }
	}
	
	function adicionarArquivo(){
		
		var form = document.forms[0];
		form.target = "";
		form.action = "exibirEncerrarOrdemServicoPopupAction.do?addFoto=OK";
		
		retorno = true;
		
		if(form.arquivoFoto.value.length == 0){
			alert("Informe o Arquivo");
			form.arquivoAnexo.focus();
			retorno = false;
		}
		if(form.observacaoFoto.value.length > 200){
			alert("Campo Observação excedeu limite de 200 caracteres");
			retorno = false;
		}
		/*else if (!validateCaracterEspecial(form)){
			retorno = false;
		}*/
		
		if (retorno){
			form.submit();
		}	
	}
	function removerArquivo(identificacao){
		
		var form = document.forms[0];
		form.target = "";
		form.action = "exibirEncerrarOrdemServicoPopupAction.do?removerFoto=" + identificacao;
		
		form.submit();	
	}

	function visualizarArquivo(identificacao){
		
		var form = document.forms[0];
		form.target = "_new";
		form.action = "exibirEncerrarOrdemServicoPopupAction.do?visualizarFoto=" + identificacao;
		
	}	
		
	
</script>
</head>
<logic:present name="fecharPopup">
	<body leftmargin="0" 
		  topmargin="0"
		  onload="javascript:chamarReload('${sessionScope.retornoTela}');window.close();">
</logic:present>

<logic:notPresent name="fecharPopup">
	<body leftmargin="0" 
	      topmargin="0"
		  onload="window.focus();resizePageSemLink(700, 600);javascript:setarFoco('${requestScope.nomeCampo}');javascript:telaAtencao('${requestScope.atencaoIndeferimento}');">

</logic:notPresent>
<html:form  action="/encerrarOrdemServicoPopupAction"
			name="EncerrarOrdemServicoActionForm"
			type="gcom.gui.atendimentopublico.ordemservico.EncerrarOrdemServicoActionForm"
			enctype="multipart/form-data"
			method="post">
			
	<html:hidden property="tipoServicoReferenciaIndicadorFiscalizacao" />
	<html:hidden property="indicadorDiagnostico" />
	<html:hidden property="mostrarAlert" />
	<html:hidden property="exibeIndicadorExistePavimento" />
	<html:hidden property="exibeQtdeReposicaoAsfalto" />
	<html:hidden property="exibeQtdeReposicaoCalcada" />
	<html:hidden property="exibeQtdeReposicaoParalelo" />
	<input  type="hidden" 
			id="INDICADOR_DIAGNOSTICO_ATIVO" 
			value="<%=ServicoTipoReferencia.INDICADOR_DIAGNOSTICO_ATIVO%>"/>

	<table width="100%" border="0" cellspacing="5" cellpadding="0">

		<tr>
			<td width="100%" valign="top" class="centercoltext">

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
						<td class="parabg">Encerrar Ordem de Serviço</td>
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
						</td>
					</tr>
				</table>

			<p>&nbsp;</p>

			<!--Inicio da Tabela Dados Gerais da Ordem de Serviço -->
			<table width="100%" border="0">

				<tr>
					<td height="31" colspan="2">
					<input  type="hidden" 
							name="indicadorExecucaoSim"
							value="<%=AtendimentoMotivoEncerramento.INDICADOR_EXECUCAO_SIM%>" /> 
					<input  type="hidden" name="indicadorExecucaoNao"
							value="<%=AtendimentoMotivoEncerramento.INDICADOR_EXECUCAO_NAO%>" />
					<input  type="hidden" 
						    name="indicadorPavimentoSim"
						    value="<%=""+ServicoTipo.INDICADOR_PAVIMENTO_SIM %>"/> 
					<input  type="hidden" 
							name="indicadorDeferimentoSim"
							value="<%=""+OsReferidaRetornoTipo.INDICADOR_DEFERIMENTO_SIM %>"/> 
							
					<html:hidden property="indicadorExecucao"/> 
					<html:hidden property="indicadorPavimento"/> 
					<html:hidden property="tipoServicoReferenciaId"/> 
					<html:hidden property="indicadorDeferimento"/>
					<html:hidden property="servicoTipoObrigatorio"/>
					<html:hidden property="indicadorVistoriaServicoTipo"/> 
							
					<table width="100%" border="0" align="center">

						<tr bgcolor="#cbe5fe">
							<td align="center">
							<table width="100%" border="0" bgcolor="#99CCFF">
								<tr bgcolor="#99CCFF">
									<td height="18" colspan="2">
									<div id="layerHideDadosGerais" style="display:block">
										<table width="100%" border="0" bgcolor="#99CCFF">
											<tr bgcolor="#99CCFF">
												<td align="center"><span class="style2"> <a
													href="javascript:extendeTabela('DadosGerais',true);" /> <b>Dados
												Gerais da Ordem de Serviço</b> </a> </span></td>
											</tr>
										</table>
									</div>

									<div id="layerShowDadosGerais" style="display:none">

									<table width="100%" border="0" bgcolor="#99CCFF">

										<tr bgcolor="#99CCFF">
											<td align="center"><span class="style2"> 
												<a href="javascript:extendeTabela('DadosGerais',false);" /> 
													<b>Dados Gerais da Ordem de Serviço</b> 
												</a> </span>
											</td>
										</tr>
										<tr bgcolor="#cbe5fe">
											<td>
											<table border="0" width="100%">

												<tr>
													<td height="10" width="30%"><strong>N&uacute;mero do OS:</strong></td>

													<td width="69%">
														<html:text  property="numeroOS"
																	readonly="true"
																	style="background-color:#EFEFEF; border:0;" 
																	size="9"
																	maxlength="9" /> &nbsp;&nbsp;&nbsp;&nbsp; 
														<strong>Situa&ccedil;&atilde;o do OS:</strong> &nbsp;&nbsp; 
														<html:text property="situacaoOS" 
																   readonly="true"
																   style="background-color:#EFEFEF; border:0;" 
																   size="25"
																   maxlength="25" />
													</td>
												</tr>
												<c:if test="${EncerrarOrdemServicoActionForm.numeroRA != null}">
													<tr>
														<td height="10" width="30%"><strong>N&uacute;mero do RA:</strong></td>

														<td width="69%">
															<html:text  property="numeroRA"
																		readonly="true"
																		style="background-color:#EFEFEF; border:0;" 
																		size="9"
																		maxlength="9" /> &nbsp;&nbsp;&nbsp;&nbsp; 
																<strong>Situa&ccedil;&atilde;o do RA:</strong> &nbsp;&nbsp; 
															<html:text  property="situacaoRA" 
																		readonly="true"
																		style="background-color:#EFEFEF; border:0;" 
																		size="25"
																		maxlength="25" />
														</td>
													</tr>
												</c:if>

												<c:if test="${EncerrarOrdemServicoActionForm.numeroDocumentoCobranca != null}">
													<tr>
														<td width="30%">
															<strong>N&uacute;mero do Documento de Cobran&ccedil;a:</strong>
														</td>
														<td width="69%">
															<html:text  property="numeroDocumentoCobranca" 
																		readonly="true"
																		style="background-color:#EFEFEF; border:0;" 
																		size="9"
																		maxlength="9" />
														</td>
													</tr>
												</c:if>

												<tr>
													<td width="30%">
														<strong>Data da Gera&ccedil;&atilde;o:</strong>
													</td>
													<td width="69%">
														<html:text  property="dataGeracao"
																	readonly="true"
																	style="background-color:#EFEFEF; border:0;" 
																	size="10"
																	maxlength="10" />
													</td>
												</tr>

												<tr>
													<td height="10" width="30%">
														<strong>Tipo do Servi&ccedil;o:</strong>
													</td>
													<td width="69%">
														<html:text  property="tipoServicoOSId"
																	readonly="true"
																	style="background-color:#EFEFEF; border:0;" 
																	size="4"
																	maxlength="4" /> 
														<html:text  property="tipoServicoOSDescricao" 
																	readonly="true"
																	style="background-color:#EFEFEF; border:0;" 
																	size="40"
																	maxlength="40" />
													</td>
												</tr>
												<tr>
													<td height="10" width="30">
														<strong>Observa&ccedil;&atilde;o:</strong>
													</td>
													<td width="69%">
														<strong> 
														<html:textarea  property="observacao" 
																		readonly="true"
																		style="background-color:#EFEFEF; border:0;" 
																		cols="40" 
				    													rows="5"
				    													onkeyup="limitTextArea(document.forms[0].observacao, 200, document.getElementById('utilizado'), document.getElementById('limite'));"/>
															<span id="utilizado">0</span>/<span id="limite">200</span>
														</strong>
													</td>
												</tr>

												<tr>
													<td height="10" width="30%">
														<strong>Valor do Servi&ccedil;o Original:</strong>
													</td>

													<td width="69%">
														<html:text  property="valorServicoOriginal"
																	readonly="true"
																	style="background-color:#EFEFEF; border:0; text-align:right;"
																	size="9" /> &nbsp;&nbsp;&nbsp;&nbsp; 
															<strong>Valor do Servi&ccedil;o Atual:</strong> &nbsp;&nbsp; 
														<html:text  property="valorServicoAtual" 
																	readonly="true"
																	style="background-color:#EFEFEF; border:0; text-align:right;"
																	size="9" />
													</td>
												</tr>

												<tr>
													<td width="30%">
														<strong>Prioridade Original:</strong>
													</td>
													<td width="69%">
														<html:text  property="prioridadeOriginal"
																	readonly="true"
																	style="background-color:#EFEFEF; border:0;" 
																	size="48"
																	maxlength="48" />
													</td>
												</tr>

												<tr>
													<td width="30%">
														<strong>Prioridade Atual:</strong>
													</td>
													<td width="69%">
														<html:text  property="prioridadeAtual"
																	readonly="true"
																	style="background-color:#EFEFEF; border:0;" 
																	size="48"
																	maxlength="48" />
													</td>
												</tr>


												<tr>
													<td width="30%">
														<strong>Unidade da Gera&ccedil;&atilde;o da OS:</strong>
													</td>
													<td width="69%">
														<html:text  property="unidadeGeracaoId"
																	readonly="true"
																	style="background-color:#EFEFEF; border:0;" 
																	size="4"
																	maxlength="4" /> 
														<html:text  property="unidadeGeracaoDescricao" 
																	readonly="true"
																	style="background-color:#EFEFEF; border:0;" 
																	size="40"
																	maxlength="40" />
													</td>
												</tr>

												<tr>
													<td width="30%">
														<strong>Usu&aacute;rio da Gera&ccedil;&atilde;o da OS:</strong>
													</td>
													<td width="69%">
														<html:text  property="usuarioGeracaoId"
																	readonly="true"
																	style="background-color:#EFEFEF; border:0;" size="4"
																	maxlength="4" /> 
														<html:text  property="usuarioGeracaoNome"
																	readonly="true"
																	style="background-color:#EFEFEF; border:0;" size="40"
																	maxlength="40" />
													</td>
												</tr>

												<tr>
													<td width="30%">
														<strong>Data da &Uacute;ltima Emiss&atilde;o:</strong>
													</td>
													<td width="69%">
														<html:text  property="dataUltimaEmissao"
																	readonly="true"
																	style="background-color:#EFEFEF; border:0;" 
																	size="9" />
													</td>
												</tr>
											</table>
											</td>
										</tr>
									</table>
									</div>
									</td>
								</tr>
							</table>
							</td>
						</tr>

						<!-- Dados do OS referencia -->
						<logic:notEmpty name="osReferencia">
							<tr>
								<td>
								<div id="layerHideOSReferencia" style="display:block">
								<table width="100%" border="0" bgcolor="#99CCFF">
									<tr bgcolor="#99CCFF">
										<td align="center"><span class="style2"> <a
											href="javascript:extendeTabela('OSReferencia',true);" /> <b>Ordem
										de Serviço de Referência</b> </a> </span></td>
									</tr>
								</table>
								</div>

								<div id="layerShowOSReferencia" style="display:none">

								<table width="100%" border="0" bgcolor="#99CCFF">

									<tr bgcolor="#99CCFF">
										<td align="center"><span class="style2"> <a
											href="javascript:extendeTabela('OSReferencia',false);" /> <b>Ordem
										de Serviço de Referência</b> </a> </span></td>
									</tr>

									<tr bgcolor="#cbe5fe">

										<td>
										<table border="0" width="100%">

											<tr>
												<td width="32%"><strong>Número da OS Referência:</strong></td>

												<td width="67%"><input type="text" name="numeroOSReferencia"
													readonly="true" style="background-color:#EFEFEF; border:0;"
													size="10" maxlength="10" value="${osReferencia.id}" /></td>
											</tr>
											<tr>
												<td width="32%"><strong>Tipo de Serviço da OS Referência:</strong></td>

												<td width="67%"><html:text
													property="tipoServicoReferenciaDescricao" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="40"
													maxlength="40" /></td>
											</tr>
											<tr>
												<td width="32%"><strong>Situação da OS Referência:</strong></td>

												<td width="67%"><input type="text"
													name="situacaoOSReferencia" readonly="true"
													style="background-color:#EFEFEF; border:0;" size="25"
													maxlength="25" value="${osReferencia.descricaoSituacao}" /></td>
											</tr>


										</table>
										</td>
									</tr>

								</table>
								</div>

								</td>
							</tr>
						</logic:notEmpty>
						
						<!-- Dados do Serviço tipo referencia -->
						<logic:notEmpty name="EncerrarOrdemServicoActionForm"
								property="servicoTipoReferenciaOS">
							<tr>
								<td>
								<div id="layerHideServicoTipoReferencia" style="display:block">
								<table width="100%" border="0" bgcolor="#99CCFF">
									<tr bgcolor="#99CCFF">
										<td align="center"><span class="style2"> <a
											href="javascript:extendeTabela('ServicoTipoReferencia',true);" /> <b>Tipo de Serviço de Referência
											</b> </a> </span></td>
									</tr>
								</table>
								</div>

								<div id="layerShowServicoTipoReferencia" style="display:none">

								<table width="100%" border="0" bgcolor="#99CCFF">

									<tr bgcolor="#99CCFF">
										<td align="center"><span class="style2"> <a
											href="javascript:extendeTabela('ServicoTipoReferencia',false);" /> <b>Tipo de Serviço de Referência
											</b> </a> </span></td>
									</tr>

									<tr bgcolor="#cbe5fe">

										<td>
										<table border="0" width="100%">

											<tr>
												<td width="32%"><strong>Tipo de Serviço de Referência:</strong></td>

												<td width="67%"><html:text property="servicoTipoReferenciaOSDescricao"
													readonly="true" style="background-color:#EFEFEF; border:0;"
													size="30" maxlength="30"/></td>
											</tr>

										</table>
										</td>
									</tr>

								</table>
								</div>

								</td>
							</tr>
						</logic:notEmpty>


						<!-- Dados do encerramento da OS -->

						<tr>
							<td>
							<table width="100%" border="0" bgcolor="#99CCFF">
								<tr bgcolor="#99CCFF">
									<td align="center"><b>Dados do Encerramento da Ordem de Serviço</b></td>
								</tr>
								<tr bgcolor="#cbe5fe">

									<td>
									<table border="0" width="100%">
										<tr>
											<td><strong>Data do Encerramento:<font color="#ff0000">*</font></strong></td>

											<td><html:text property="dataEncerramento" 
														   size="10"
												           maxlength="10" 
												           tabindex="1"
												           onkeypress="return isCampoNumerico(event);"
												           onkeyup="mascaraData(this, event);"/>
												<a href="javascript:abrirCalendario('EncerrarOrdemServicoActionForm', 'dataEncerramento');" >
														  <img border="0" 
														  	   src="<bean:message key="caminho.imagens"/>calendario.gif"
															   width="20" 
															   border="0" 
															   align="absmiddle" 
															   alt="Exibir Calendário"/>
												</a>
											</td>
										</tr>
										<tr>
											<td><strong>Hora do Encerramento:</strong></td>

											<td>
												<html:text property="horaEncerramento" 
														   size="5"
														   maxlength="5" 
														   tabindex="1" 
														   onkeyup="mascaraHora(this, event);"
														   onkeypress="return isCampoNumerico(event);"/>
											</td>
										</tr>											
										<tr>
											<td width="30%"><strong>Motivo do Encerramento:<font
												color="#ff0000">*</font></strong></td>

											<td width="69%"><html:select property="idMotivoEncerramento"
												tabindex="2" onchange="carregarCampos();">
												<html:option value="">&nbsp;</html:option>
												<html:options collection="colecaoAtendimentoMotivoEncerrado"
													labelProperty="descricao" property="id" />
											</html:select></td>
										</tr>
									
										<!-- 4.5  indicador execução igual a não -->
										<logic:notEmpty name="EncerrarOrdemServicoActionForm" property="indicadorExecucao">
											<logic:equal name="EncerrarOrdemServicoActionForm"
												property="indicadorExecucao"
												value="<%=""+AtendimentoMotivoEncerramento.INDICADOR_EXECUCAO_NAO%>">
												<tr>
													<td width="30%"><strong>Parecer do Encerramento:</strong></td>
													<td width="69%">
													
													<html:textarea
														property="observacaoEncerramento" 
														cols="50" 
														rows="5"
    													onkeyup="limitTextArea(document.forms[0].observacaoEncerramento, 400, document.getElementById('utilizado'), document.getElementById('limite'));"/>
													
													<span id="utilizado">0</span>/<span id="limite">400</span>    													
														
														
													</td>
												</tr>

											</logic:equal>
									
											<!-- 4.6  indicador execução igual a sim -->
											<logic:equal name="EncerrarOrdemServicoActionForm"
												property="indicadorExecucao"
												value="<%=""+AtendimentoMotivoEncerramento.INDICADOR_EXECUCAO_SIM%>">
												<!-- 4.6.1  id do tipo de serviço referência igual a nulo -->
												<tr>
													<td width="30%"><strong>Parecer do Encerramento:</strong></td>
													<td width="69%">
														<html:textarea
															property="observacaoEncerramento" 
															cols="50"
															rows="5"
   															onkeyup="limitTextArea(document.forms[0].observacaoEncerramento, 400, document.getElementById('utilizado'), document.getElementById('limite'));"/>
														
														<span id="utilizado">0</span>/<span id="limite">400</span>
													
													</td>
												</tr>
												<!--  RUA OBRIGATORIO -->
												<logic:equal name="PavimentoCalcadaObrigatorio" scope="session" value="false">
													<logic:equal name="PavimentoRuaObrigatorio" scope="session" value="SIM">
														<logic:empty name="EncerrarOrdemServicoActionForm"
															property="tipoServicoReferenciaId">
															
															<logic:notEmpty name="EncerrarOrdemServicoActionForm"
																property="indicadorPavimento">
																<logic:equal name="EncerrarOrdemServicoActionForm"
																 property="indicadorPavimento" value="<%=""+ServicoTipo.INDICADOR_PAVIMENTO_RUA_SIM %>">
															     <tr>
																  <td width="30%"><strong>Pavimento Rua:<font color="#ff0000">*</font></strong></td>
		                           									 <td width="69%">
		                           									 	<html:select property="idPavimentoRua" tabindex="2">
																			 <html:option value="">&nbsp;</html:option>
																		 	 <html:options collection="colecaoPavimentoRua"
												                         				   labelProperty="descricao" 
												                         				   property="id" />
										                                 </html:select> &nbsp;&nbsp;&nbsp;&nbsp;
			                                                             <STRONG>Metragem:<font color="#ff0000">*</font></STRONG> &nbsp;&nbsp; 
			                                                             	<html:text property="metragemPavimentoRua" 
			                                                             	  		   size="6" 
			                                                             	  		   maxlength="5" 
			                                                             	  		   onkeypress="return isCampoNumerico(event);"		                                                             	  		   
			                                                             	  		   onkeyup="javascript:formataValorMonetario(this, 6)" />&nbsp;<STRONG>m²</STRONG>
																  	</td>
															     </tr>
															      <tr>
																      <td HEIGHT="30"><strong>Unidade Repavimentadora:</strong></td>
																       <td colspan="2">
																			<html:select property="idUnidadeRepavimentadora" tabindex="2" >
																				 <html:option value="-1">&nbsp;</html:option>
																			 	 <html:options collection="colecaoUnidadeOrgazanicional"
													                         				   labelProperty="descricao" 
													                         				   property="id" />
													                        </html:select>				   
																		</td>
															      </tr>		
															    </logic:equal>
															</logic:notEmpty>
												
														</logic:empty>
													</logic:equal>
												</logic:equal>
												
												
												<!-- 4.6.2  id do tipo de serviço referência diferente de nulo -->
												<logic:notEmpty name="EncerrarOrdemServicoActionForm"
													property="tipoServicoReferenciaId">
										<!-- 			<tr>
														<td width="30%"><strong>Parecer do Encerramento:</strong></td>
														<td width="69%">
															<html:textarea
																property="observacaoEncerramento" 
																cols="50"
																rows="5"
   																onkeyup="limitTextArea(document.forms[0].observacaoEncerramento, 400, document.getElementById('utilizado'), document.getElementById('limite'));"/>
															
																<span id="utilizado">0</span>/<span id="limite">400</span>
														
														</td>
													</tr>  -->
													<tr>
															<td width="30%"><strong>Tipo de Retorno Referida:<font
												                 color="#ff0000">*</font></strong></td>

															<td width="69%"><html:select
																property="idTipoRetornoReferida" tabindex="2" onchange="carregarCamposEncerrarComExecucaoComReferencia();">
																<html:option value="">&nbsp;</html:option>
																<html:options
																	collection="colecaoOSReferidaRetornoTipo"
																	labelProperty="descricao" property="id" />
															</html:select></td>
													</tr>
													
													<logic:notEmpty name="EncerrarOrdemServicoActionForm" property="tipoServicoReferenciaIndicadorFiscalizacao">
														<logic:equal name="EncerrarOrdemServicoActionForm" property="tipoServicoReferenciaIndicadorFiscalizacao" value="1">
														<tr> 	
				                                    		<td>
				                                    			<strong>Serviço:</strong>
				                                    		</td>
				                                    		<td>
				   												<html:radio property="indicadorServicoAceito" value="1"/>ACEITO
																<html:radio property="indicadorServicoAceito" value="2"/>REJEITADO
															</td>
				                                  		</tr>
														</logic:equal>
													</logic:notEmpty>
													
													<logic:notEmpty name="EncerrarOrdemServicoActionForm" property="servicoTipoObrigatorio">
													      <logic:equal name="EncerrarOrdemServicoActionForm" property="servicoTipoObrigatorio" value="SIM">
													         <tr>
											                   <td width="30%"><strong>Tipo de Serviço:<font
												                 color="#ff0000">*</font></strong></td>
												               <td width="69%">
												                  <logic:present name="colecaoServicoTipo">  
												                    <html:select property="idServicoTipo"
													                 tabindex="2" >
													                  <html:option value="">&nbsp;</html:option>
													                  <html:options collection="colecaoServicoTipo"
														               labelProperty="descricao" property="id" />
												                      </html:select>
												                   </logic:present>
												                   <logic:notPresent name="colecaoServicoTipo">
												                   <html:text maxlength="4" property="idServicoTipo" size="4" tabindex="7"
																		onkeypress="javascript:validaEnterComMensagem(event, 'exibirEncerrarOrdemServicoPopupAction.do?pesquisaServicoTipo=1', 'idServicoTipo', 'Serviço Tipo');" onkeyup="document.forms[0].descricaoServicoTipo.value='';"/>
																	<a href="javascript:redirecionarSubmit('recuperarPesquisarDadosEncerrarPopupOSAction.do?caminhoRetornoTelaPesquisaServicoTipo=exibirEncerrarOrdemServicoPopupAction.do?pesquisaServicoTipo=1');">
																		<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
																			title="Pesquisar Serviço Tipo" /></a>
																	<logic:present name="idServicoTipoNaoEncontrado">
																		<logic:equal name="idServicoTipoNaoEncontrado" value="exception">
																			<html:text property="descricaoServicoTipo" size="40" maxlength="30" readonly="true"
																				style="background-color:#EFEFEF; border:0; color: #ff0000" />
																		</logic:equal>
																		<logic:notEqual name="idServicoTipoNaoEncontrado" value="exception">
																			<html:text property="descricaoServicoTipo" size="40" maxlength="30" readonly="true"
																				style="background-color:#EFEFEF; border:0; color: #000000" />
																		</logic:notEqual>
																	</logic:present> 
																	<logic:notPresent name="idServicoTipoNaoEncontrado">
																		<logic:empty name="EncerrarOrdemServicoActionForm" property="idServicoTipo">
																			<html:text property="descricaoServicoTipo" value="" size="40" maxlength="30" readonly="true"
																				style="background-color:#EFEFEF; border:0; color: #ff0000" />
																		</logic:empty>
																		<logic:notEmpty name="EncerrarOrdemServicoActionForm" property="idServicoTipo">
																			<html:text property="descricaoServicoTipo" size="40" maxlength="30" readonly="true"
																				style="background-color:#EFEFEF; border:0; color: #000000" />
																		</logic:notEmpty>
																	</logic:notPresent>
																	<a href="javascript:limparPesquisaServicoTipo();">
																		<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
																		border="0" title="Apagar" /></a>
												                   </logic:notPresent>
												               </td>
												                  
												                    
										                       </tr>
													   
													      </logic:equal>
													</logic:notEmpty>
												</logic:notEmpty>

                                            </logic:equal>
													
										</logic:notEmpty>
										
										
										<tr>
														<td width="100%" height="1px" bgcolor="#99CCFF" colspan="9"></td>
													</tr>
												
													<tr>
														<td><b>Anexar Foto: </b></td>
														<td>
															<html:file property="arquivoFoto" />														
														</td>
													</tr>													
													<tr>
														<td><b>Observação para a Foto: </b></td>
														<td><html:textarea
															property="observacaoFoto" cols="50" rows="5"
														    style="max-height:98px; min-height:98px; max-width:421px; min-width:421px;"
		    												onkeyup="limitTextArea(document.forms[0].observacaoFoto, 200, document.getElementById('utilizadoFoto'), document.getElementById('limiteFoto'));"/>
																	
															<span id="utilizadoFoto">0</span>/<span id="limiteFoto">200</span>
														</td>
													</tr>
													<tr>
														<td><b>Arquivos Informados: </b></td>
														<td>
															<input class="bottonRightCol" style="float: right;" type="button" onclick="javascript:adicionarArquivo();" value="Adicionar" name="AddFoto">
														</td>
													</tr>													
													
													<tr>
														<td colspan="4">
														<div style="width: 100%; height: 100; overflow: auto;">
														<table width="100%" bgcolor="#99CCFF">														
															<tr bgcolor="#99CCFF">
							
																<td align="center" width="10%"><strong>Remover</strong></td>
																<td width="10%">
																	<div align="center"><strong>Arquivo</strong></div>
																</td>
																<td style="width:80%">
																	<div align="center"><strong>Observação</strong></div>
																</td>
															</tr>
														</table>
														<table width="100%" bgcolor="#99CCFF">
														<logic:notEmpty name="colecaoOrdemServicoFoto">						
														<% int cont = 0;%>
														<logic:iterate name="colecaoOrdemServicoFoto" id="foto" type="OrdemServicoFoto">
															<%cont = cont + 1;
															
															if (cont % 2 == 0) {%>
																<tr bgcolor="#cbe5fe">
															<%} else {%>
																<tr bgcolor="#FFFFFF">
															<%}%>
					
																	<td align="center" width="10%" valign="middle">
																		<a href="javascript:removerArquivo('<%="" + GcomAction.obterTimestampIdObjeto(foto) %>')" title="Remover">
																			<img src="<bean:message key='caminho.imagens'/>Error.gif" border="0" >
																		</a>
																	</td>
																	<td align="center" width="10%" valign="middle">
																	<%-- <a href="javascript:visualizarArquivo('<%="" + GcomAction.obterTimestampIdObjeto(foto) %>')" title="Visualizar Arquivo">--%>
																		<a href="exibirEncerrarOrdemServicoPopupAction.do?visualizarFoto=<%="" + GcomAction.obterTimestampIdObjeto(foto) %>" title="Visualizar Arquivo" target="_blank">																			
																			<IMG SRC="<bean:message key="caminho.imagens"/>JPG.gif" width="35" height="35" BORDER="0" ALT=""></a>	
																	</td>
																	<td width="80%">
																		<% if (cont % 2 == 0) {%>
																			<TEXTAREA ROWS="2" COLS="55" style="max-height:54px; min-height:54px; max-width:421px; min-width:421px;border:0px solid; background-color: #cbe5fe;" readonly><bean:write name="foto" property="observacaoFoto" /></TEXTAREA>
																		<%} else {%>
																			<TEXTAREA ROWS="2" COLS="55" style="max-height:54px; min-height:54px; max-width:421px; min-width:421px;border:0px solid" readonly><bean:write name="foto" property="observacaoFoto" /></TEXTAREA>
																		<%}%>																																						
																	</td>																		
																</tr>
														</logic:iterate>
														</logic:notEmpty>
														</table>
														</div>
														</td>
													</tr>
										
										<logic:notEmpty name="EncerrarOrdemServicoActionForm" property="indicadorVistoriaServicoTipo">
										 <logic:equal name="EncerrarOrdemServicoActionForm" property="indicadorVistoriaServicoTipo" value="<%=""+ServicoTipo.INDICADOR_VISTORIA_SIM%>">
										  <tr>
											<td width="30%"><strong>Retorno Vistoria:<font
												color="#ff0000">*</font></strong></td>

											<td width="69%"><html:radio property="codigoRetornoVistoriaOs" value="1"/><strong>Deferido </strong>&nbsp;&nbsp;
											                <html:radio property="codigoRetornoVistoriaOs" value="2"/><strong>Indeferido </strong>
											</td>
										  </tr>
										 </logic:equal> 
										</logic:notEmpty>
										
										
										
										<!-- Boletim de Medição - Vivianne Sousa 27/01/2011 -->
								
								<logic:notEmpty name="EncerrarOrdemServicoActionForm" property="exibeIndicadorExistePavimento">
									<logic:equal name="EncerrarOrdemServicoActionForm" 
										property="exibeIndicadorExistePavimento" value="1">
										<tr id="trTipoExecucao">
											<td width="30%">
												<strong>Tipo de Execução:<font color="#ff0000">*</font></strong>
											</td>
											<td width="69%">
												<html:radio property="indicadorExistePavimento" value="1"/><strong>Ramal com Pavimento</strong>&nbsp;&nbsp;
											    <html:radio property="indicadorExistePavimento" value="2"/><strong>Ramal sem Pavimento</strong>
											    <html:radio property="indicadorExistePavimento" value="3"/><strong>Nicho</strong>
											</td>
										</tr>	 
										<tr id="trTipoPavim">
											<td width="30%">
												<strong>Tipo de Pavimento:<font color="#ff0000">*</font></strong>
											</td>
											<td width="69%">
												<html:radio property="tipoPavimento" value="1"/><strong>Asfáltico</strong>&nbsp;&nbsp;
											    <html:radio property="tipoPavimento" value="2"/><strong>Paralelo/Pedra Tosca</strong>
											</td>
										</tr>
										<tr id="trCalcada">
											<td width="30%">
												<strong>Com Calçada?<font color="#ff0000">*</font></strong>
											</td>
											<td width="69%">
												<html:radio property="indicadorExisteCalcada" value="1"/><strong>Sim</strong>&nbsp;&nbsp;
											    <html:radio property="indicadorExisteCalcada" value="2"/><strong>Não</strong>
											</td>
										</tr>
								 	</logic:equal>
								</logic:notEmpty>																
								<logic:notEmpty name="EncerrarOrdemServicoActionForm" property="exibeQtdeReposicaoAsfalto">
									<logic:equal name="EncerrarOrdemServicoActionForm" 
										property="exibeQtdeReposicaoAsfalto" value="1">
										<tr id="trQtdRepM2Asf">
											<td width="30%">
												<strong>Quantidade de reposição em m² de asfalto:</strong>
											</td>
											<td width="69%">
												<html:text property="qtdeReposicaoAsfalto" size="6" maxlength="6" onkeyup="formataValorMonetario(this, 6)"/>
											</td>
										</tr>				 
								 	</logic:equal>
								</logic:notEmpty>
								
								<logic:notEmpty name="EncerrarOrdemServicoActionForm" property="exibeQtdeReposicaoParalelo">
									<logic:equal name="EncerrarOrdemServicoActionForm" 
										property="exibeQtdeReposicaoParalelo" value="1">
										<tr id="trQtdRepM2Par">
											<td width="30%">
												<strong>Quantidade de reposição em m² de paralelo:</strong>
											</td>

											<td width="69%">
												<html:text property="qtdeReposicaoParalelo" size="6" maxlength="6" onkeyup="formataValorMonetario(this, 6)"/>
											</td>
										</tr>															 
								 	</logic:equal>
								</logic:notEmpty>	
								 
								<logic:notEmpty name="EncerrarOrdemServicoActionForm" property="exibeQtdeReposicaoCalcada">
									<logic:equal name="EncerrarOrdemServicoActionForm" 
										property="exibeQtdeReposicaoCalcada" value="1">
										<tr id="trQtdRepM2Cal">
											<td width="30%">
												<strong>Quantidade de reposição em m² de calçada:</strong>
											</td>

											<td width="69%">
												<html:text property="qtdeReposicaoCalcada" size="6" maxlength="6" onkeyup="formataValorMonetario(this, 6)"/>
											</td>
										</tr>					 
								 	</logic:equal>
								</logic:notEmpty>									 								 
								
								<!-- Boletim de Medição - Vivianne Sousa 27/01/2011 -->
										
										
									</table>
									</td>
								</tr>


								


							</table>


							</td>

						</tr>



					</table>

					</td>
				</tr>

				<tr>
					<td>
					<table width="100%">
						<tr>
							<td>
							<div><input name="ButtonVoltar" type="button"
								class="bottonRightCol" value="Voltar"
								onclick="javascript:history.back();"></div>
							</td>
							
							<td>
							<div align="right">
							<table>
							<tr>
							 <td>
							   <input name="ButtonOSFiscalizacao" type="button"
										class="bottonRightCol" value="Gerar OS Fiscalização"
										onclick="javascript:informarOSFiscalizacao();">
							</td>
							<logic:notEmpty name="EncerrarOrdemServicoActionForm"
									property="indicadorExecucao">
								<logic:equal name="EncerrarOrdemServicoActionForm"
									property="indicadorExecucao"
									value="<%=""+AtendimentoMotivoEncerramento.INDICADOR_EXECUCAO_SIM%>">
									<td><input name="ButtonAtividade" type="button"
										class="bottonRightCol" value="Informar Atividades"
										onclick="javascript:informarAtividade();">
									<td>	
										<input name="ButtonEncerrar" type="button"
								         class="bottonRightCol" value="Encerrar"
								         onclick="javascript:validarForm(document.forms[0]);">
									</td>
								</logic:equal>	
								<logic:notEqual name="EncerrarOrdemServicoActionForm"
									property="indicadorExecucao"
									value="<%=""+AtendimentoMotivoEncerramento.INDICADOR_EXECUCAO_SIM%>">
									<td><input name="ButtonEncerrar" type="button"
								class="bottonRightCol" value="Encerrar"
								onclick="javascript:validarForm(document.forms[0]);"></td>
								</logic:notEqual>
							</logic:notEmpty>
							<logic:empty name="EncerrarOrdemServicoActionForm"
									property="indicadorExecucao">
							<td><input name="ButtonEncerrar" type="button"
								class="bottonRightCol" value="Encerrar"
								onclick="javascript:validarForm(document.forms[0]);"></td>
							</logic:empty>
						  </tr>	
						</table>	
						</div>
					   </td>
					  </tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<!-- Fim do Corpo - Sávio Luiz -->
</html:form>
</body>
</html:html>
