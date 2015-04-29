<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gsan.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
	<head>
		<title>Compesa | Servi�os</title>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-1.4.2.min.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>style.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>internal.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>jquery.theme.css" type="text/css">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.blockUI.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.ui.core.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.ui.widget.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.ui.datepicker.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.ui.datepicker-pt-BR.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.meio.mask.js"></script>
		
		<!-- JS's da p�gina antiga -->
		<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
		
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
				});
			</script>
		</logic:present>

		<logic:present name="contaBraileJaSolicitada" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#contaBraileJaSolicitada'),
						theme : true,
						title : 'Aviso'
					});
				
					$('#voltarServicos').live('click', function(){
						window.location.href = '/gsan/exibirServicosPortalCaemaAction.do?method=voltarServico';
					});
				});
			</script>
		</logic:present>
		
		<logic:present name="contaBraileSolicitadaComSucesso" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#contaBraileSolicitadaComSucesso'),
						theme : true,
						title : 'Aviso'
					});
				
					$('#voltar').live('click', function(){
						window.location.href = '/gsan/exibirServicosPortalCaemaAction.do?method=voltarServico';
					});
				});
			</script>
		</logic:present>
			
		<script type="text/javascript">
			$(document).ready(function(){
			
				$('[name=telefoneContato]').setMask();
							
				$.datepicker.setDefaults($.datepicker.regional['pt-BR']);
				$('[name=dataExpedicao]').datepicker({
					showOn : 'both',
					buttonImage : '/gsan/imagens/portal/icons/data.gif',
					maxDate : '+0d',
					buttonImageOnly : 'true',
					buttonText : 'Clique no calend�rio para selecionar a Data de Expedi��o'
				});
				
				if ($.browser.msie) {
					$('[name=dataExpedicao]').parent().append('<input type="button" style="background: no-repeat url(/gsan/imagens/portal/icons/data.gif); border:none;position:absolute;right:-80px;top:26px;" id="imageDatepicker" onclick="javascript:setFocusData();" />');
					$('[name=dataExpedicao]').parent().children('img:first').remove();
				}
				
				var cpf = $('#cpfFormatado').html();
				if(cpf.length == 11){
				    var cpfFormatado = cpf.substr(0, 3) + "." + cpf.substr(3, 3) + "." + cpf.substr(6, 3) + "-" + cpf.substr(9, 2);
				    $('#cpfFormatado').html(cpfFormatado);
				}
				
				$('.confirm').live('click', function(){
					$.unblockUI();
				});
				
				$('label').click(function(){
					$('[name=' + $(this).attr('for') + ']').focus();
				});
			});
			
			function setFocusData(){
				$('[name=dataExpedicao]').focus();
			}		
		
			var bCancel = false; 
			
			function validateInserirCadastroContaBrailePortalCaemaActionForm(form) {                                                                   
				if (bCancel) 
			    	return true; 
			   	else 
			    	return validarRequerido(form) && validateCaracterEspecial(form) && validateLong(form) /*&& validateCpf(form) && validateCnpj(form)*/; 
			} 
			
			function caracteresespeciais () { 
				this.aa = new Array("matricula", "Matr�cula possui caracteres especiais.", new Function ("varName", " return this[varName];"));
			    this.ab = new Array("cpfCnpjCliente", "CPF/CNPJ do Cliente possui caracteres especiais.", new Function ("varName", " return this[varName];"));
			    this.ac = new Array("cpfSolicitante", "CPF do Solicitante possui caracteres especiais.", new Function ("varName", " return this[varName];"));
			    this.ad = new Array("rg", "RG possui caracteres especiais.", new Function ("varName", " return this[varName];"));
			} 
			
			function IntegerValidations () { 
				this.aa = new Array("matricula", "Matr�cula deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
			    this.ab = new Array("cpfCnpjCliente", "CPF/CNPJ do Cliente deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
			    this.ac = new Array("cpfSolicitante", "CPF do Solicitante deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
			} 
			
			function DateValidations () {
				this.aa = new Array("dataExpedicao", "Data de Expedi��o inv�lida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
			}
		
			function validarRequerido(form){
					
				var retorno = true;
				var msg = "";
					
				if (form.matricula.value.length < 1){
					msg = "Informe Matr�cula <br />";
					form.matricula.focus();
				}
				
				if (form.nomeCliente.value.length < 1){
					
					if (msg.length > 0){
						msg = msg + "Informe o Nome do Cliente <br />";
					}
					else{
						msg = "Informe o Nome do Cliente <br />";
						form.nomeCliente.focus();
					}
				}
					
				if (form.email.value.length < 1 ){
					
					if (msg.length > 0){
						msg = msg + "Informe o E-mail <br />";
					}
					else{
						msg = "Informe o E-mail <br />";
						form.email.focus();
					}
				}
				
				if (form.nomeSolicitante.value.length < 1){
					
					if (msg.length > 0){
						msg = msg + "Informe o Nome do Solicitante <br />";
					}
					else{
						msg = "Informe o Nome do Solicitante <br />";
						form.nomeSolicitante.focus();
					}
				}
				
				if (form.cpfSolicitante.value.length < 1 ){
					
					if (msg.length > 0){
						msg = msg + "Informe o CPF do Solicitante <br />";
					}
					else{
						msg = "Informe o CPF do Solicitante <br />";
						form.cpfSolicitante.focus();
					}
				}
				
				if (form.rg.value.length < 1 ){
					
					if (msg.length > 0){
						msg = msg + "Informe o Registro Geral <br />";
					}
					else{
						msg = "Informe o Registro Geral <br />";
						form.rg.focus();
					}
				}
				
				if(form.orgaoExpeditor.value == '-1'){
					
					if (msg.length > 0){
						msg = msg + "Informe o �rg�o Expeditor <br />";
					}
					else{
						msg = "Informe o �rg�o Expeditor <br />";
						form.orgaoExpeditor.focus();
					}
				}
				
				if(form.unidadeFederacao.value == '-1'){
					
					if (msg.length > 0){
						msg = msg + "Informe a Unidade Federa��o <br />";
					}
					else{
						msg = "Informe a Unidade Federa��o <br />";
						form.unidadeFederacao.focus();
					}
				}
				
				if (form.dataExpedicao.value.length < 10 ){
					
					if (msg.length > 0){
						msg = msg + "Informe a Data de Expedi��o <br />";
					}
					else{
						msg = "Informe a Data de Expedi��o <br />";
						form.dataExpedicao.focus();
					}
				}
				
				if (form.telefoneContato.value.length < 1){
					
					if (msg.length > 0){
						msg = msg + "Informe o Telefone de Contato <br />";
					}
					else{
						msg = "Informe o Telefone de Contato <br />";
						form.telefoneContato.focus();
					}
				}
				
				if (msg.length > 0){
					$.blockUI({
						message : '<h3 style="text-align:justify; padding-top:10px; padding-bottom: 10px;">' + msg + '</h3>'
								 +'<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>',
						theme : true,
						title : 'Aviso'
					});
					retorno = false;
				}
				
				return retorno;
			}
			
			function limparMatricula(form){
			
				form.matricula.value = '';
				form.matricula.focus();
				
			}
		
			function limparForm(form){
			
				form.nomeCliente.value = "";
				form.nomeCliente.disabled = true;
				
				form.cpfCnpjCliente.value = "";
				form.cpfCnpjCliente.disabled = true;
				
				form.email.value = "";
				form.email.disabled = true;
				
				form.nomeSolicitante.value = "";
				form.nomeSolicitante.disabled = true;
				
				form.cpfSolicitante.value = "";
				form.cpfSolicitante.disabled = true;
				
				form.telefoneContato.value = "";
				form.telefoneContato.disabled = true;
				
				form.rg.value = "";
				form.rg.disabled = true;
				
				form.orgaoExpeditor.value = "-1";
				form.orgaoExpeditor.disabled = true;
				
				form.unidadeFederacao.value = "-1";
				form.unidadeFederacao.disabled = true;
				
				form.dataExpedicao.value = "";
				form.dataExpedicao.disabled = true;
				
			}
		
			function validarForm(form){
			
				if(validateInserirCadastroContaBrailePortalActionForm(form)){
		    		
		    		form.submit();
				}
			}
			
			function verificarDadosCliente(){
					
				var form =  document.forms[0];
				
				form.action = "/gsan/exibirInserirCadastroContaBrailePortalCaemaAction.do?ok=sim";
				form.submit();
			
			}
			
			function validarNomeCliente(){
			
				var form =  document.forms[0];
				
				form.confirmarNomeCliente.value = "confirmado";
				
				document.getElementById('confirmarNomeClienteBotao').disabled = true;
			
			}
			
			function validarCpfCnpjCliente(){
			
				var form =  document.forms[0];
				
				form.confirmarCpfCnpjCliente.value = "confirmado";
				
				document.getElementById('confirmarCpfCnpjClienteBotao').disabled = true;
			
			}
			
			function limparMatricula(){
			
				var form =  document.forms[0];
				
				form.matricula.value = "";
			
			}
			
			function limparNomeCliente(){
			
				var form =  document.forms[0];
				
				form.nomeCliente.value = "";
			
			}
			
			function limparCpfCnpjCliente(){
			
				var form =  document.forms[0];
				
				form.cpfCnpjCliente.value = "";
			
			}
			
			function limparEmail(){
			
				var form =  document.forms[0];
				
				form.email.value = "";
			
			}
			
			function limparNomeSolicitante(){
			
				var form =  document.forms[0];
				
				form.nomeSolicitante.value = "";
			
			}
			
			function limparCpfSolicitante(){
			
				var form =  document.forms[0];
				
				form.cpfSolicitante.value = "";
			
			}
			
			function limparRg(){
			
				var form = document.forms[0];
				
				form.rg.value = "";
			}
			
			function limparOrgaoExpeditor(){
			
				var form = document.forms[0];
				
				form.orgaoExpeditor.value = "";
			}
			
			function limparTelefoneContato(){
			
				var form =  document.forms[0];
				
				form.telefoneContato.value = "";
			
			}
			
			function desabilitarCampos(){
				//Desabilita os campos se a matricula do imovel nao for informada
				var form = document.forms[0];
		
				if ( form.desabilitaCampos.value != null
						&& form.desabilitaCampos.value == "1" ){
				
					form.nomeCliente.value = "";
					form.nomeCliente.disabled = true;
					
					form.cpfCnpjCliente.value = "";
					form.cpfCnpjCliente.disabled = true;
					
					form.email.value = "";
					form.email.disabled = true;
					
					form.nomeSolicitante.value = "";
					form.nomeSolicitante.disabled = true;
					
					form.cpfSolicitante.value = "";
					form.cpfSolicitante.disabled = true;
					
					form.telefoneContato.value = "";
					form.telefoneContato.disabled = true;
					
					form.rg.value = "";
					form.rg.disabled = true;
					
					form.orgaoExpeditor.value = "-1";
					form.orgaoExpeditor.disabled = true;
					
					form.unidadeFederacao.value = "-1";
					form.unidadeFederacao.disabled = true;
					
					form.dataExpedicao.value = "";
					form.dataExpedicao.disabled = true;
				}
			}
		
		</script>
	</head>
	
	<body>
		<div id="container">
	    	<%@ include file="/jsp/portal/caema/cabecalho.jsp"%>
	        
	        <!-- Content - Start -->
	        <div id="content">
	       	 	<%@ include file="/jsp/portal/caema/cabecalhoImovel.jsp"%>

				<div id="fatura-email" class="serv-int">
	            	<h3>Solicitar conta em braile<span>&nbsp;</span></h3>
                	<p>CPF/CNPJ do Cliente: <em id="cpfFormatado"><bean:write name="cpfCnpj" scope="session" /></em></p>
	            	<html:form action="/inserirCadastroContaBrailePortalCaemaAction.do"
						name="InserirCadastroContaBrailePortalCaemaActionForm"
						type="gsan.gui.portal.caema.InserirCadastroContaBrailePortalCaemaActionForm"
						method="post"
						onsubmit="return validateInserirCadastroContaBrailePortalCaemaActionForm(this);">
						<html:hidden property="matricula" value="${ExibirServicosPortalCaemaActionForm.matricula}" />
						<html:hidden property="nomeCliente" value="${ExibirServicosPortalCaemaActionForm.nomeUsuario}" />
						<html:hidden property="cpfCnpjCliente" value='<bean:write name="cpfCnpj" scope="session" />' />
						
						<html:hidden property="confirmarNomeCliente" value="${ExibirServicosPortalCaemaActionForm.nomeUsuario}" />
						<html:hidden property="confirmarCpfCnpjCliente" value='<bean:write name="cpfCnpj" scope="session" />' />
						<html:hidden property="desabilitaCampos" />
						
						<fieldset>
							<legend>Solicitar conta em braile</legend>
							
							<table>
								<tr>
									<td style="text-align: left">
			                        	<span class="cmp-text-6">
			                            	<label for="email">E-mail do Solicitante<font color="#F00">*</font>:</label>
											<html:text property="email" size="41" maxlength="40" tabindex="1" style="text-transform: none;" />
										</span>
										<span class="cmp-text-2">
			                            	<label for="telefoneContato" style="width: 170px;">Telefone para Contato<font color="#F00">*</font>:</label>
											<html:text property="telefoneContato" size="14" maxlength="13" tabindex="2" onkeypress="return isCampoNumerico(event);" alt="phone" /> 
										</span>
									</td>
								</tr>
								<tr>
									<td style="text-align: left">
										<span class="cmp-text-6">
				                            <label for="nomeSolicitante">Nome do Solicitante<font color="#F00">*</font>:</label>							
											<html:text property="nomeSolicitante" size="51" maxlength="50" tabindex="3" />
										</span>
										<span class="cmp-text-2">
			                            	<label for="cpf">CPF do Solicitante<font color="#F00">*</font>:</label>
			                            	<logic:present name="cpfCnpj" scope="session">
												<input type="text" name="cpfSolicitante" size="15" tabindex="4" value='<bean:write name="cpfCnpj" scope="session" />' onkeypress="return isCampoNumerico(event);"/>
											</logic:present>
											<logic:notPresent name="cpfCnpj" scope="session">
												<input type="text" name="cpfSolicitante" size="15" tabindex="4" value="" onkeypress="return isCampoNumerico(event);"/>
											</logic:notPresent>
										</span>
									</td>
								</tr>
								<tr>
									<td style="text-align: left">
										<span class="cmp-text-3">
				                            <label for="rg">Registro Geral<font color="#F00">*</font>:</label>
											<html:text property="rg" size="13" maxlength="13" tabindex="5" onkeypress="return isCampoNumerico(event);" />
										</span>
										<span class="select-2" style="width:auto;">
			                            	<label for="orgaoExpeditor">�rg�o Expedidor<font color="#F00">*</font>:</label>
			                            	<div>
												<html:select property="orgaoExpeditor" tabindex="6">
													<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
													<html:options collection="orgaosExpedidores" labelProperty="descricaoAbreviada" property="id" />
												</html:select>
											</div>
										</span>
										<span class="select-2">
			                            	<label for="unidadeFederacao">UF<font color="#F00">*</font>:</label>
			                            	<div>
												<html:select property="unidadeFederacao" tabindex="7">
													<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
													<html:options collection="unidadesFederacao" labelProperty="sigla" property="id" />
												</html:select>
											</div>
										</span>
										<span class="cmp-text-5" style="width: 130px">
			                            	<label for="dataExpedicao" style="width:170px;">Data de Expedi��o<font color="#F00">*</font>:</label>
											<html:text property="dataExpedicao" size="10" maxlength="10" style="margin-top: 5px;" tabindex="8" onkeyup="javascript:mascaraData(this, event);" /> 
										</span>
									</td>
								</tr>
								
								
							</table>
							<input type="button" name="Button" tabindex="9" class="btn-enviar" value="" onClick="javascript:validarForm(document.forms[0]);emailValido(email); " />
							<span class="confirm">Voc� receber� um email para a confirma��o do cadastro.</span>
						</fieldset>
						<div style="width: 100%; color: rgb(255, 0, 0); margin-top: 17px;">* campos obrigat�rios</div>
					</html:form>
				</div>
			</div>
			<%@ include file="/jsp/portal/caema/rodape.jsp"%>
		</div><!-- Content - End -->
		
		<logic:present name="contaBraileSolicitadaComSucesso" scope="request">
			<div id="contaBraileSolicitadaComSucesso" style="display:none; cursor: default;"> 
		        <h3 style="text-align:justify; padding-top:10px; padding-bottom: 10px;">
	  		    	<bean:write name="mensagemBraileSolicitadaComSucesso" scope="request" />
		        </h3>
		        <a href="javascript:void(0);" class="ui-corner-all button" id="voltar">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="exception" scope="request">
			<div id="exception" style="display:none; cursor: default;"> 
		        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;">
		        	<bean:write name="exception" scope="request" />
		        </h3> 
				<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
	</body>
	
	<logic:present name="contaBraileJaSolicitada" scope="request">
		<div id="contaBraileJaSolicitada" style="display:none;cursor:default;">
			<img alt="Aviso" src="imagens/portal/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
	        <h3 style="text-align:justify; padding-top:10px; padding-bottom: 10px;">J� existe uma solicita��o de conta em braile para este im�vel</h3>
	        <p>
	        	Em caso de d�vidas, procure uma loja de atendimento mais pr�xima, ou entre em contato com o call center pelo 0800 081 0195
	        </p>
	        <a href="javascript:void(0);" class="ui-corner-all button confirm" id="voltarServicos">OK</a>
		</div>
	</logic:present>
</html:html>
