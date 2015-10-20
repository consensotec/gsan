<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>

	<head>
		<title>CAEMA | Serviços</title>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-1.4.2.min.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<link rel="stylesheet" href="<bean:message key="caminho.portalcaema.css"/>style.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portalcaema.css"/>internal.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portalcaema.css"/>jquery.theme.css" type="text/css">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.blockUI.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.meio.mask.js"></script>
		
		<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
		
		<!-- [if lt IE 9]>
			<style type="text/css">
				#form-matricula input.campo-text {height:28px!important; padding-top:5px!important}
			</style>
		<![endif]-->

		<logic:present name="clienteCpfCnpjInvalido" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					showMessage('CPF/CNPJ não é válido.');
				});
			</script>
		</logic:present>
		
		<logic:present name="cpfCnpjObrigatorio" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					showMessage('Informe o CPF ou CNPJ.');
				});
			</script>
		</logic:present>
		
		<logic:present name="emailInvalido" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					showMessage('E-mail inválido.');
				});
			</script>
		</logic:present>
		
		<logic:present name="usuarioEmailNulo" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					showMessage('O email é obrigatorio.');
				});
			</script>
		</logic:present>

		<logic:present name="emailCadastradoComSucesso" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#emailCadastradoComSucesso'),
						theme : true,
						title : 'Aviso'
					});
					
					$('#voltar').click(function(){
						window.location.href = '/gsan/exibirServicosPortalCaemaAction.do?method=voltarServico';
					});
				});
			</script>
		</logic:present>
		
		<logic:present name="mensagemSucesso" scope="session">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#mensagemSucesso'),
						theme : true,
						title : 'Aviso',
						onBlock : function() {
							$('.ui-widget-overlay').removeClass('ui-widget-overlay');
						}
					});
				});
			</script>
		</logic:present>
		
		<script type="text/javascript">
			$(document).ready(function(){

				$('[name=telefoneContato]').setMask();
			
				var cpf = $('#cpfFormatado').html();
				if(cpf != null && cpf.length == 11){
				    var cpfFormatado = cpf.substr(0, 3) + "." + cpf.substr(3, 3) + "." + cpf.substr(6, 3) + "-" + cpf.substr(9, 2);
				    $('#cpfFormatado').html(cpfFormatado);
				}
				
				$('.confirm').live('click', function(){
					$.unblockUI();
				});
				
				$('#emailConfirmado').click(function(){
					window.location = '/gsan/exibirServicosPortalCaemaAction.do?menu=sim';
				});
				
				enviarConfirmacao();
				
				$('label').click(function(){
					$('[name=' + $(this).attr('for') + ']').focus();
				});
			});
			
			$.validateEmail = function (email) {
				er = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
				return (er.exec(email))? true : false;
			};
			
			function showMessage(message){
				$('#message h3').text(message);
				$.blockUI({
					message : $('#message'),
					theme : true,
					title : 'Aviso',
					onBlock : function() {
						$('.ui-widget-overlay').removeClass('ui-widget-overlay');
					}
				});
			}
			
			var bCancel = false; 
			
			function validateInserirCadastroEmailClientePortalCaemaActionForm(form) {                                                                   
				if (bCancel) 
			    	return true; 
			   	else 
			    	return validarRequerido(form) && validateCaracterEspecial(form) && validateLong(form) /*&& validateCpf(form) && validateCnpj(form)*/; 
			} 
			
			function caracteresespeciais () { 
				this.aa = new Array("matricula", "Matrícula possui caracteres especiais.", new Function ("varName", " return this[varName];"));
			    this.ac = new Array("cpfSolicitante", "CPF/CNPJ do Solicitante possui caracteres especiais.", new Function ("varName", " return this[varName];"));
			} 
			
			function IntegerValidations () { 
				this.aa = new Array("matricula", "Matrícula deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
			    this.ac = new Array("cpfSolicitante", "CPF/CNPJ do Solicitante deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
			} 
				
			function validarRequerido(form){
					
				var retorno = true;
				var msg = "";
					
				if (form.matricula.value.length < 1){
					msg = "Informe Matrícula <br />";
					form.matricula.focus();
				}
				
				if (form.email.value.length < 1 || !$.validateEmail(form.email.value)){
					
					if (msg.length > 0){
						msg = msg + "Informe um email válido<br />";
					}
					else{
						msg = "Informe um email válido<br />";
						form.email.focus();
					}
				}
				
				if (form.nomeSolicitante.value.length < 1){
					
					if (msg.length > 0){
						msg = msg + "Informe o nome do solicitante <br />";
					}
					else{
						msg = "Informe o nome do solicitante <br />";
						form.nomeSolicitante.focus();
					}
				}
				
				if (form.cpfSolicitante.value.length < 1 ){
					
					if (msg.length > 0){
						msg = msg + "Informe o CPF/CNPJ do solicitante <br />";
					}
					else{
						msg = "Informe o CPF/CNPJ do solicitante <br />";
						form.cpfSolicitante.focus();
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
			
			function validarForm(form){
			
				if(validateInserirCadastroEmailClientePortalCaemaActionForm(form)){
		    		
		    		form.submit();
				}
			}
			
			function enviarConfirmacao(){
				
				var form = document.forms[0];
				var enviarEmailConfirmacao = document.getElementById("enviarConfirmacao").value;
				
				
				if (enviarEmailConfirmacao == "OK"){
					
					if (confirm("Confirma alteração do cadastro de email do cliente? ")) {
				
						alert('Você receberá um email para a confirmação do cadastro.');
						
						form.action = "/gsan/inserirCadastroEmailClientePortalCaemaAction.do?ok=sim&enviarEmail=sim";
						
						form.submit();
					}
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
	            	<h3>Recebimento de fatura por e-mail<span>&nbsp;</span></h3>
	            	
	            	<logic:present name="cpfCnpj" scope="session">
						<p>CPF/CNPJ do Cliente: <em id="cpfFormatado">
							<bean:write name="cpfCnpj" scope="session" /></em>
						</p>
					</logic:present>
	            	
	            	<html:form action="/inserirCadastroEmailClientePortalCaemaAction.do?ok=sim&enviarEmail=sim"
						name="InserirCadastroEmailClientePortalCaemaActionForm"
						type="gcom.gui.portal.caema.InserirCadastroEmailClientePortalCaemaActionForm" 
						method="post"
						onsubmit="return validateInserirCadastroEmailClientePortalCaemaActionForm(this);">

						<html:hidden property="matricula" value="${ExibirServicosPortalCaemaActionForm.matricula}" />
						
						<input type="hidden" id="enviarConfirmacao" name="enviarConfirmacao" value="sim" />
						
						<fieldset>
	                    	<legend>Recebimento de fatura por e-mail</legend>
	                    	
	                    	<table>
	                    		<tr>
	                    			<td style="text-align: left">
										<span class="cmp-text-6">
											<label for="email">E-mail do solicitante<font color="#F00">*</font>:</label>
											<html:text property="email" size="41" maxlength="40" tabindex="1" style="text-transform: none;" />
										</span>
										<span class="cmp-text-2">
											<label for="telefoneContato">Telefone para contato:</label>
											<html:text property="telefoneContato" size="16" maxlength="15" tabindex="2" onkeypress="return isCampoNumerico(event);" alt="phoneCaema" />
										</span>
	                    			</td>
	                    		</tr>
	                    		<tr>
	                    			<td style="text-align: left">
				                    	<!-- campos do outro formulário -->
										<span class="cmp-text-6">
											<label for="nomeSolicitante">Nome do solicitante<font color="#F00">*</font>:</label>
											<html:text  property="nomeSolicitante" 
														size="51" 
														onblur="validaTexto(this)"
														onkeypress="return campoTexto(event, this);"
														maxlength="50" 
														tabindex="3" />
										</span>
										<span class="cmp-text-2">
											<label for="cpfSolicitante">CPF do solicitante<font color="#F00">*</font>:</label>
			                            	<logic:present name="cpfCnpj" scope="session">
												<input type="text"  
													name="cpfSolicitante" 
													size="15" 
													tabindex="4" value='<bean:write name="cpfCnpj" scope="session" />' 
													onkeypress="return isCampoNumerico(event);"/>
											</logic:present>
											<logic:notPresent name="cpfCnpj" scope="session">
												<input type="text" 
													name="cpfSolicitante" 
													size="15" 
													tabindex="4" 
													onkeypress="return isCampoNumerico(event);"/>
											</logic:notPresent>
										</span>
	                    			</td>
	                    		</tr>
	                    	</table>
							<input type="button" name="Button" class="btn-enviar" value=""
								onClick="javascript:validarForm(document.forms[0]);validarRequerido(document.forms[0]);emailValido(email); " />
							<span class="confirm">Você receberá um e-mail para a confirmação do cadastro.</span>
	                    
   	                    	<!-- END campos do outro formulário -->
	                    </fieldset>
						<div style="width: 100%; color: rgb(255, 0, 0); margin-top: 17px;">* campos obrigatórios</div>
					</html:form>
	            </div>
	        </div>
	        
			<%@ include file="/jsp/portal/caema/rodape.jsp"%>
		</div><!-- Content - End -->
		
		<logic:present name="emailCadastradoComSucesso" scope="request">
			<div id="emailCadastradoComSucesso" style="display:none; cursor: default;"> 
		        <h3 style="text-align:justify; padding-top:10px; padding-bottom: 10px;">
	  		    	<bean:write name="respostaCliente" scope="request" />
		        </h3>
		        <a href="javascript:void(0);" class="ui-corner-all button" id="voltar">OK</a>
			</div>
		</logic:present>
		
		<div id="message" style="display:none; cursor: default;"> 
	        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;"></h3> 
			<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
		</div>
				
		<logic:present name="mensagemSucesso" scope="session">
			<div id="mensagemSucesso" style="display:none; cursor: default;"> 
		        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;">
		        	<bean:write name="mensagemSucesso" scope="session" />
		        </h3> 
				<a href="javascript:void(0);" class="ui-corner-all button confirm" id="emailConfirmado">OK</a>
			</div>
		</logic:present>
		
	</body>
</html:html>
