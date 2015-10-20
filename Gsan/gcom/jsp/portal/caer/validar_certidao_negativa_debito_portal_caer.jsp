<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
	<head>
		<title>CAER</title>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-1.4.2.min.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<link rel="stylesheet" href="<bean:message key="caminho.portalcaer.css"/>style.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portalcaer.css"/>internal.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portalcaer.css"/>internal2.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portalcaer.css"/>jquery.theme.css" type="text/css">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.blockUI.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.meio.mask.js"></script>
		
		<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
		
		<logic:present name="matriculaImovelObrigatoria" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					showMessage('Informe a Matr�cula do Im�vel.');
				});
			</script>
		</logic:present>
		
		<logic:present name="numeroAutenticacaoObrigatorio" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					showMessage('Informe o N�mero de Autentica��o Eletr�nica.');
				});
			</script>
		</logic:present>
		
		<logic:present name="certidaoNegativaInvalida" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					showMessage('Certid�o negativa de d�bito inv�lida.');
				});
			</script>
		</logic:present>
		
		<logic:present name="certidaoNegativaForaPeriodoValidade" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					showMessage('Certid�o negativa fora do per�odo de validade.');
				});
			</script>
		</logic:present>
		
		<logic:present name="certidaoNegativaValida" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					showMessage('Certid�o negativa de d�bito v�lida.');
				});
			</script>
		</logic:present>
		
		<script type="text/javascript">

			$(document).ready(function(){
	
				$('.confirm').live('click', function(){
					$.unblockUI();
				});

				$('label').click(function(){
					$('[name=' + $(this).attr('for') + ']').focus();
				});
				
			});
			
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
			
			function validateValidarCertidaoNegativaDebitoPortalCaerActionForm(form) {                                                                   
				if (bCancel) 
			    	return true; 
			   	else 
			    	return validarRequerido(form) && validateCaracterEspecial(form) && validateLong(form); 
			} 
			
			function caracteresespeciais () { 
				this.aa = new Array("numeroAutenticacao", "N�mero de Autentica��o possui caracteres especiais.", new Function ("varName", " return this[varName];"));
			    this.ac = new Array("matriculaImovel", "Matr�cula possui caracteres especiais.", new Function ("varName", " return this[varName];"));
			} 
			
			function IntegerValidations () { 
				this.aa = new Array("matriculaImovel", "Matr�cula deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
			} 
				
			function validarRequerido(form){
					
				var retorno = true;
				var msg = "";
					
				if (form.matriculaImovel.value.length < 1){
					msg = "Informe Matr�cula <br />";
					form.matriculaImovel.focus();
				}
				
				if (form.numeroAutenticacao.value.length < 1){
					
					if (msg.length > 0){
						msg = msg + "Informe o N�mero de Autentica��o <br />";
					}
					else{
						msg = "Informe o N�mero de Autentica��o <br />";
						form.numeroAutenticacao.focus();
					}
				}
				
				if (msg.length > 0){
					$.blockUI({
						message : '<img alt="Aviso" src="imagens/portal/caer/icons/warning.png" alt="Aviso" style="float: left; padding-right:15px; margin-top: 10px;">'
								 +'<h3 style="text-align:justify; padding-top:5px; padding-bottom: 10px;">' + msg + '</h3>'
								 +'<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>',
						theme : true,
						title : 'Aviso'
					});
					retorno = false;
				}
				
				return retorno;
			}
			
			function validarForm(form){
			
				if(validateValidarCertidaoNegativaDebitoPortalCaerActionForm(form)){
		    		
		    		form.submit();
				}
			}
			
		</script>
	</head>
	<body>
		<div id="container">
	    	<%@ include file="/jsp/portal/caer/cabecalho.jsp"%>

			<!-- Content - Start -->
			<div id="content">
		
				<%@ include file="/jsp/portal/caer/cabecalhoInformacoes.jsp"%>
				
				<div id="validar-certidao" class="serv-int">
	            	<h3>Validar Certid�o Negativa de D�bito<span>&nbsp;</span></h3>
					
	            	<html:form action="/validarCertidaoNegativaDebitoPortalCaerAction.do"
						name="ValidarCertidaoNegativaDebitoPortalCaerActionForm"
						type="gcom.gui.portal.caer.ValidarCertidaoNegativaDebitoPortalCaerActionForm" 
						method="post"
						onsubmit="return validateValidarCertidaoNegativaDebitoPortalCaerActionForm(this);">

						<fieldset>
	                    	<legend>Validar Certid�o Negativa de D�bito</legend>
	                    	
	                    	<table>
	                    		<tr>
	                    			<td colspan="3">
	                    			 &nbsp;
	                    			</td>
	                    		</tr>
								<tr>
									<td style="text-align: left" colspan="3">
										<p class="info-2">Informe o n�mero da autentica��o eletr�nica e a matr�cula do im�vel.</p>
									</td>
								</tr>
	                    		<tr>
	                    			<td style="text-align: left">
				                    	<!-- campos do outro formul�rio -->
										<span class="cmp-text-6">
											<label for="numeroAutenticacao">N�mero da Autentica��o Eletr�nica<font color="#F00">*</font>:</label>
										</span>
									</td>
	                    			<td style="text-align: left">
										<span class="cmp-text-2">
											<label for="matriculaImovel">Matr�cula do Im�vel<font color="#F00">*</font>:</label>
										</span>
	                    			</td>
	                    			<td align="right">
	                    			</td>
	                    		</tr>
	                    		<tr>
	                    			<td style="text-align: left">
				                    	<!-- campos do outro formul�rio -->
										<span class="cmp-text-6">
											<html:text  property="numeroAutenticacao" 
														size="51" 
														onblur="validaCampoSemCaractereEspecialSemMsg(this)"
														maxlength="12" 
														tabindex="1" />
										</span>
									</td>
	                    			<td style="text-align: left">
										<span class="cmp-text-2">
											<html:text property="matriculaImovel" 
												size="11" 
												onkeypress="return isCampoNumerico(event);"
												maxlength="11" 
												tabindex="2" />
										</span>
	                    			</td>
	                    			<td align="right">
										<input type="button" name="Button" class="btn-enviar" value=""
											onClick="javascript:validarForm(document.forms[0]);validarRequerido(document.forms[0]); " />
	                    			</td>
	                    		</tr>
	                    	</table>
	                    
   	                    	<!-- END campos do outro formul�rio -->
	                    </fieldset>
						<div style="width: 100%; color: rgb(255, 0, 0); margin-top: 17px;">* campos obrigat�rios</div>
					</html:form>
	            </div>
	        </div>
	        
			<%@ include file="/jsp/portal/caer/rodape.jsp"%>
		</div><!-- Content - End -->
		
		<div id="message" style="display:none; cursor: default;"> 
	        <img alt="Aviso" src="imagens/portal/caer/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 5px;">
	        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;"></h3> 
			<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
		</div>
				
	</body>
</html:html>
