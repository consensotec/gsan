<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
	<head>
		<title>SAAE</title>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-1.4.2.min.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.blockUI.js"></script>
		<link rel="stylesheet" href="<bean:message key="caminho.portalsaae.css"/>style.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portalsaae.css"/>jquery.theme.css" type="text/css">
		
		<style type="text/css">
			.centraliza{
				margin: 28px 5px 0px 0px !important;
			}
		</style>
		
		<logic:present name="imovelInvalido" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$('#matricula').focus();
				});
			</script>
		</logic:present>
		
		<logic:present name="solicitarCpfCnpj" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#solicitarCpfCnpj'),
						theme : true,
						title : 'Confirmação de Usuário',
						onBlock : function() {
							if ($.browser.msie && parseInt($.browser.version, 10) < 9) {
								$('div.ui-dialog-titlebar').append('<a style="float:right;border:solid 1px #FFF;padding-right:3px;padding-left:3px;z-index:1000px;font-weight:bold;margin-top:-25px;margin-right:5px;" href="javascript:void(0);" id="cancel">X</a>');
							 } else {
								$('div.ui-dialog-titlebar').append('<a style="float:right;border:solid 1px #FFF;padding-right:3px;padding-left:3px;z-index:1000px;font-weight:bold;margin-top:-3px;" href="javascript:void(0);" id="cancel">X</a>');
							 }
							 
							$('#matricula').val($('#matriculaAux').val());							
						}
					});
					
					$('[name=cpfCnpjSolicitante]').focus().select();
					
					$('#cancel').live('click', function(){
						$('[name=cpfCnpjSolicitante]').val('');
						$.unblockUI();	
						$('#matricula').focus().select();					
					});
				});
			</script>
		</logic:present>
		
	</head>
	
	<body>
		<div id="container">
	    	<%@ include file="/jsp/portal/saae/cabecalho.jsp"%>

	        <!-- Content - Start -->
	        <div id="content">
	        	<div id="text-top" style="text-align:justify;">
					A Loja Virtual da Companhia Serviço Autônomo de Água e Esgoto de Juazeiro é o ambiente onde se encontram dispon&iacute;veis os servi&ccedil;os da empresa. Esta &eacute; a forma mais r&aacute;pida de atendimento, tudo para facilitar a sua vida.					
	            </div>
	            <div id="info-index" class="box">	            	
                    <span id="text-box" style="text-align:justify;">
                    </span>
	            </div>
	            <div id="servicos-index" class="box">
                    <span id="text-box" style="text-align:justify;" title="Serviços | Os melhores serviços para sua comodidade">
						Para acessar o menu de op&ccedil;&otilde;es digite a matr&iacute;cula da sua conta de &aacute;gua e clique em OK
					</span>
				    <html:form styleId="form-matricula" action="/exibirServicosPortalSaaeAction.do?method=servicos" method="post" 
						name="ExibirServicosPortalSaaeActionForm" type="gcom.gui.portal.saae.ExibirServicosPortalSaaeActionForm" >
	                    <fieldset>
	                        <label for="matricula">Matr&iacute;cula</label>
	                        <input type="text" name="matricula" id="matricula" class="campo-text" size="11" maxlength="11" tabindex="1" onkeypress="javascript: return isCampoNumerico(event);"/>
	                        <input type="submit" value="" class="btn-ok"  />
	                        <logic:present name="imovelInvalido" scope="request">
								<span style="display:block; margin-right:-15px;">Matr&iacute;cula Inv&aacute;lida</span>
							</logic:present>
	                    </fieldset>
	                </html:form>                
	            </div>
	        </div>
	        <!-- Content - End -->
	        
	       <%@ include file="/jsp/portal/saae/rodape.jsp"%>
	    </div>
	    
	    <logic:present name="solicitarCpfCnpj" scope="request">
		    <div id="solicitarCpfCnpj" style="display:none; cursor: default;">
		    	
		    	<html:form styleId="form-matricula" style="padding: 8px 0 0 0px; width: 100%;" action="/exibirServicosPortalSaaeAction.do?method=servicos&vcpf=true" method="post" 
					name="ExibirServicosPortalSaaeActionForm" type="gcom.gui.portal.saae.ExibirServicosPortalSaaeActionForm" >
                    <fieldset>
                    	<logic:notPresent name="cpfCnpjNaoCadastrado" scope="request">
                    		  <label for="cpfOrCnpj" style="width:auto;" id="cpfOrCnpj">CPF/CNPJ do solicitante: </label>
                        	  <html:hidden property="matricula" styleId="matriculaAux" />
                        	  <html:text property="cpfCnpjSolicitante" style="width: 140px !important; background: url(/gsan/imagens/portal/saae/forms/matricula-text.png) no-repeat !important; background-position: 0px -4px !important;" styleClass="campo-text cpfCnpjSolicitante" size="14" maxlength="14" tabindex="1" onkeypress="javascript: return isCampoNumerico(event);"/>
                              <input type="submit" value="" class="btn-ok"  />
                    	</logic:notPresent>
                      
                        <logic:present name="cpfCnpjInvalido" scope="request">
							<span style="display: block; background: url('/gsan/imagens/portal/saae/forms/cpf_cnpj_invalido.png') no-repeat scroll 0pt 0pt transparent; width: 100px; margin-right: -12px;" id="cpfCnpjError"></span>
					
							<div  style="color: red; clear: both; text-align: justify; font-weight: bold; font-size: 11px; padding-top: 15px;">		
							CPF/CNPJ Inválido, tente novamente ou procure uma de nossas Lojas de Atendimento Presencial com a Certidão de Registro do Imóvel quando proprietário ou com procuração do proprietário ou ainda com Contrato de Locação.
							</div>
						</logic:present>
						
                        <logic:present name="cpfCnpjNaoCadastrado" scope="request">
                       		 <label for="cpfOrCnpj" style="width:auto;" class="centraliza" id="cpfOrCnpj">CPF/CNPJ do solicitante: </label>
                        	 <html:hidden property="matricula" styleId="matriculaAux" />
                        	 <html:text property="cpfCnpjSolicitante" style="width: 140px !important; background: url(/gsan/imagens/portal/saae/forms/matricula-text.png) no-repeat !important; background-position: 0px -4px !important; margin: 22px 5px 0px 0px !important;" styleClass="campo-text cpfCnpjSolicitante "  size="14" maxlength="14" tabindex="1" onkeypress="javascript: return isCampoNumerico(event);"/>
                             <input type="submit" value="" class="btn-ok centraliza" />
                              
							<span style="display: block; background: url('/gsan/imagens/portal/saae/forms/cpf_cnpj_nao_cadastrado.png') no-repeat scroll 0pt 0pt transparent; width: 190px; height:80px;  margin-right: -12px;" id="cpfCnpjError"></span>
							<script>

								$('.blockMsg').css("width","53%");
								$('.blockMsg').css("left","20%");

							</script>
						</logic:present>
                    </fieldset>
                </html:form>
		    </div>
	    </logic:present>
	</body>
</html:html>