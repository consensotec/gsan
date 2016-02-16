<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
	<head>
		<title>SAAE | Serviços</title>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-1.4.2.min.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<link rel="stylesheet" href="<bean:message key="caminho.portalsaae.css"/>style.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portalsaae.css"/>internal.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portalsaae.css"/>jquery.theme.css" type="text/css">
		
		<!-- [if lt IE 9]>
			<style type="text/css">
				#form-matricula input.campo-text {height:28px!important; padding-top:5px!important}
			</style>
		<![endif]-->
			
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.blockUI.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.meio.mask.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
		
		<script type="text/javascript">
			$(document).ready(function(){
				
				$('[name=telefoneContato]').setMask();
				
				$('[name=nomeSolicitante]').focus();
				
				var limitNum1 = 60;
				limitTextArea(document.forms[0].pontoReferencia, limitNum1, document.getElementById('utilizado1'), document.getElementById('limite1'));
				$('[name=pontoReferencia]').keyup(function(){

					limitTextArea(document.forms[0].pontoReferencia, limitNum1, document.getElementById('utilizado1'), document.getElementById('limite1'));
				});
				
				var limitNum = 400;
				limitTextArea(document.forms[0].observacoes, limitNum, document.getElementById('utilizado'), document.getElementById('limite'));
				$('[name=observacoes]').keyup(function(){

					limitTextArea(document.forms[0].observacoes, limitNum, document.getElementById('utilizado'), document.getElementById('limite'));
				});
				
				$('label').click(function(){
					$('[name=' + $(this).attr('for') + ']').focus();
				});
			});
		
			var inputComFocus = null;
			
			$.validateEmail = function (email) {
				er = /^[a-zA-Z0-9][a-zA-Z0-9\._-]+@([a-zA-Z0-9\._-]+\.)[a-zA-Z-0-9]{2}/;
				return (er.exec(email))? true : false;
			};			

			var bCancel = false; 
			
			function validateInserirSolicitacaoServicosPortalActionForm() {   
				var form = document.InserirSolicitacaoServicosPortalActionForm;                                                                
				if (bCancel) 
			    	return true; 
			   	else 
			    	return validateCaracterEspecial(form) ; 
			} 
			
			function caracteresespeciais () { 
				
				this.aa = new Array("nomeSolicitante", "Nome Solicitante possui caracteres especiais.", new Function ("varName", " return this[varName];"));
			} 
			
			function validarForm(){

				var form = document.InserirSolicitacaoServicosPortalActionForm;
				var retorno = true;
				var msg = '';
				
				if($('[name=nomeSolicitante]').val().length < 1){
					msg += ' - Nome do solicitante';
					$('[name=nomeSolicitante]').focus(function(){
						inputComFocus = $(this);
					}).focus();
				}
				
				if ($('[name=solicitacaoTipo] option:selected').val() <= 0){
					
					if (msg.length > 0) {
						msg += '<br />';
					} else {
						$('[name=solicitacaoTipo]').focus(function(){
							inputComFocus = $(this);
						}).focus();
					}
					msg += ' - Tipo de solicitação';
				}
					
				if ($('[name=especificacao] option:selected').val() <= 0){
					if (msg.length > 0) {
						msg += '<br />';
					} else {
						$('[name=solicitacaoTipo]').focus(function(){
							inputComFocus = $(this);
						}).focus();
					}
					msg += ' - Especificação';
				}
				
				if (!($('[name=telefoneContato]').val().length >= 15  && $('[name=telefoneContato]').val().length <= 16) ){
					if (msg.length > 0) {
						msg += '<br />';
					} else {
						$('[name=telefoneContato]').focus(function(){
							inputComFocus = $(this);
						}).focus();
					}
					msg += ' - Telefone para contato deve conter entre 10 e 11 dígitos. EX:(XX) XXXXX-XXXX.';
				}
				
				if ($('[name=pontoReferencia]').val().length < 1 ){
					if (msg.length > 0) {
						msg += '<br />';
					} else {
						$('[name=pontoReferencia]').focus(function(){
							inputComFocus = $(this);
						}).focus();
					}
					msg += ' - Ponto de Referência';
				}
				
				if($('[name=email]').val().length < 1){
					msg += '<br /> - E-mail do solicitante';
					$('[name=email]').focus(function(){
						inputComFocus = $(this);
					}).focus();
				}
				
				if (msg.length > 0){
					msg = "Os campos abaixo são obrigatórios:<br /><span style='font-weight:normal;'>" + msg + "</span>"; 
					$.blockUI({
						message : '<h3 style="text-align:left; padding-top:10px; padding-bottom: 10px;">' + msg + '</h3>'
								 +'<br /><a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>',
						theme : true,
						title : 'Aviso'
					});
					
					retorno = false;
				}

				

				if(retorno && $.trim($('[name=email]').val()).length > 0 && !$.validateEmail($('[name=email]').val())) {
					$.blockUI({
						message : '<h3 style="text-align:left; padding-top:10px; padding-bottom: 10px;">O e-mail informado é inválido</h3>'
								 +'<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>',
						theme : true,
						title : 'Aviso'
					});
					
					inputComFocus = $('[name=email]');

					retorno = false;
				}
				
				$('.confirm').live('click', function(){
					$.unblockUI();
					inputComFocus.focus();
				});

				if (retorno) {
					retorno =	validateInserirSolicitacaoServicosPortalActionForm();
				}
				
				return retorno;
			}

			
		
			function carregarEspecificacao(){
				redirecionarSubmit('exibirInserirSolicitacaoServicosPortalSaaeAction.do');
			}
			
			function calcularDataPrevista(){
				redirecionarSubmit('exibirInserirSolicitacaoServicosPortalSaaeAction.do');
			}

		</script>
		<logic:present name="RASolicitadaComSucesso" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#RASolicitadaComSucesso'),
						theme : true,
						title : 'Aviso'
					});
					
					$('#voltar').click(function(){
						window.location.href = '/gsan/exibirServicosPortalSaaeAction.do?method=voltarServico';
					});
				});
			</script>
		</logic:present>
		<logic:present name="RAJaSolicitada" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#RAJaSolicitada'),
						theme : true,
						title : 'Aviso'
					});
					
					$('#voltar').click(function(){
						$.unblockUI();
						$('[name=especificacao]').focus();
					});
				});
			</script>
		</logic:present>
		
		<logic:present name="exception" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#exception'),
						theme : true,
						title : 'Aviso',
						onBlock : function() {
							$('.ui-widget-overlay').removeClass('ui-widget-overlay');
						}
					});
					
					$('#voltar').click(function(){
						$.unblockUI();
					});
				});
			</script>
		</logic:present>
		
		<logic:present name="colecaoEspecificacao">
			<script type="text/javascript">
				$(document).ready(function(){
					$('.btn-enviar').focus();
					if(parseInt($('[name=especificacao] option:selected').val()) < 1){
						$('[name=especificacao]').focus();
					} else {
						$('[name=telefoneContato]').focus();
					}
				});
			</script>
		</logic:present>
	</head>
	<body>
		<div id="container">
	    	<%@ include file="/jsp/portal/saae/cabecalho.jsp"%>

			<!-- Content - Start -->
			<div id="content">
		
				<%@ include file="/jsp/portal/saae/cabecalhoImovel.jsp"%>
				
				<div id="solicitacao-serv" class="serv-int">
	            	<h3>Solicita&ccedil;&atilde;o de servi&ccedil;os<span>&nbsp;</span></h3>
					<p>Endere&ccedil;o do im&oacute;vel: <em><%= session.getAttribute("enderecoImovel") %></em></p>
					<p class="info">Fa&ccedil;a sua solicita&ccedil;&atilde;o ou reclama&ccedil;&atilde;o utilizando o formul&aacute;rio abaixo.</p>
	            	<html:form action="/inserirSolicitacaoServicosPortalSaaeAction.do"
						name="InserirSolicitacaoServicosPortalSaaeActionForm"
						type="gcom.gui.portal.saae.InserirSolicitacaoServicosPortalSaaeActionForm" method="post"
						onsubmit="return validarForm();validateInserirSolicitacaoServicosPortalActionForm();">
						
						<html:hidden property="matricula" value="${ExibirServicosPortalSaaeActionForm.matricula}" />
						
						<fieldset>
	                    	<legend>Solicita&ccedil;&atilde;o de servi&ccedil;os</legend>
	                    	
	                    	<!-- campos do outro formulário -->
							<span class="cmp-text-6">
								<label for="nomeSolicitante">Nome do solicitante<font color="#F00">*</font>:</label>
								<html:text  property="nomeSolicitante" 
											maxlength="60"
											onblur="validaTexto(this)"
											onkeypress="return campoTexto(event, this);"
											style="font-size: 10px;"
											tabindex="1" 
											/>
							</span>
							<span class="cmp-text-2">
								<label for="telefoneContato">Telefone para contato<font color="#F00">*</font>:</label>
								<html:text property="telefoneContato" size="16" maxlength="15" tabindex="2" onkeypress="return isCampoNumerico(event);" alt="phoneSaae" />
							</span>
							<span class="cmp-text-7">
								<label for="email">E-mail do Solicitante<font color="#F00">*</font>:</label>
								<html:text property="email" maxlength="40" tabindex="5" style="text-transform:none; font-size: 10px;" />
							</span>
							<span class="select-3">
								<label for="solicitacaoTipo">Tipo de solicita&ccedil;&atilde;o<font color="#F00">*</font>:</label>
                            	<div class="select-um" style="padding-bottom:3px;">
									<html:select property="solicitacaoTipo" onchange="carregarEspecificacao()" tabindex="2">
										<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
										<logic:present name="colecaoSolicitacaoTipo">
											<html:options collection="colecaoSolicitacaoTipo" labelProperty="descricao" property="id" />
										</logic:present>
									</html:select>
								</div>
								<label for="especificacao">Especifica&ccedil;&atilde;o<font color="#F00">*</font>:</label>
								<div>
									<html:select property="especificacao" tabindex="3" onchange="calcularDataPrevista()">
										<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
										<logic:present name="colecaoEspecificacao">
											<html:options collection="colecaoEspecificacao" labelProperty="descricao" property="id"/>
										</logic:present>
									</html:select>
								</div>
							</span>
							<span class="cmp-textarea">
								<label for="pontoReferencia">Ponto de refer&ecirc;ncia<font color="#F00">*</font>:</label>
								<html:textarea property="pontoReferencia" cols="35" rows="5" tabindex="6" style="min-height:78px;max-height:78px;min-width:425px;max-width:425px; font-size: 12px;" />
								<font><span id="utilizado1">0</span>/<span id="limite1">60</span></font>
							</span>
							<span class="cmp-text-none">
								<label for="dataSolicitacao">Data da solicita&ccedil;&atilde;o:</label>
								<input type="text" id="dataSolicitacao" size="8" name="dataSolicitacao" value="<bean:write name='dataSolicitacao' scope='session' />"/>
								<br></br>
								<label for="dataPrevista">Data prevista:</label>									
								<logic:present scope="session" name="dataPrevista">
									<input type="text" id="dataPrevista"  size="8" name="dataPrevista" value="<bean:write name='dataPrevista' scope='session' />" />
								</logic:present>								
							</span>
							
							<span class="cmp-textarea">
								<label for="observacoes">Observa&ccedil;&otilde;es:</label>
								<html:textarea property="observacoes" cols="35" rows="5" tabindex="7" style="min-height:78px;max-height:78px;min-width:425px;max-width:425px; font-size:12px;" />
								<font><span id="utilizado">0</span>/<span id="limite">400</span></font>
							</span>
							
							<input type="submit" name="Button" class="btn-enviar" value="" tabindex="8" style="margin-top:-29px;" />
	                    </fieldset>
	                    <div style="width: 100%; color: rgb(255, 0, 0); margin-top: 17px;">* campos obrigatórios</div>
					</html:form>
	            </div>
	        </div>
	        
			<%@ include file="/jsp/portal/saae/rodape.jsp"%>
		</div><!-- Content - End -->
	</body>
	
	<logic:present name="RASolicitadaComSucesso" scope="request">
		<div id="RASolicitadaComSucesso" style="display:none; cursor: default;"> 
	        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;">
	        	Registro de Atendimento Cadastrado<br />
	        </h3>
	    	<font style="font-weight:normal; text-align:left;">
	    		Protocolo: <bean:write name="mensagemRA" scope="request" /><br />
	    		Previsão de Atendimento: <bean:write name="dataPrevistaAtendimentoRA" scope="request" />
	    	</font>
	        <a href="javascript:void(0);" class="ui-corner-all button" id="voltar">OK</a>
		</div>
	</logic:present>
	
	<logic:present name="RAJaSolicitada" scope="request">
		<div id="RAJaSolicitada" style="display:none; cursor: default;"> 
	        <img alt="Aviso" src="imagens/portal/saae/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 5px;">
	        <h3 style="text-align:left; padding-top:10px; padding-bottom: 10px;">
	        	Existe uma solicitação para esta especificação:
	        </h3>
	    	<font style="font-weight:normal; text-align:left;">
	    		Protocolo:<b>&nbsp;<bean:write name="mensagemRA" scope="request" /></b><br />
	    		Status:<b>&nbsp; Pendente</b>
	    	</font>
	    	<br />
	        <a href="javascript:void(0);" class="ui-corner-all button" id="voltar">OK</a>
		</div>
	</logic:present>
	
	<logic:present name="exception" scope="request">
		<div id="exception" style="display:none; cursor: default;"> 
	        <img alt="Aviso" src="imagens/portal/saae/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 5px;">
	        <p style="text-align:justify; padding-top:10px; padding-bottom: 10px;">
	        	<bean:write name="exception" scope="request" />
	        </p> 
			<a href="javascript:void(0);" class="ui-corner-all button confirm" id="voltar">OK</a>
		</div>
	</logic:present>
</html:html>
