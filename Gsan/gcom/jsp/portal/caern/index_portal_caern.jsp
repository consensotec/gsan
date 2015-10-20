<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
	<head>
		<title>CAERN</title>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-1.4.2.min.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.blockUI.js"></script>
		<link rel="stylesheet" href="<bean:message key="caminho.portalcaern.css"/>style.css" type="text/css">
		
		<link rel="stylesheet" href="<bean:message key="caminho.portalcaern.css"/>jquery.theme.css" type="text/css">
		
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
	    	<%@ include file="/jsp/portal/caern/cabecalho.jsp"%>

	        <!-- Content - Start -->
	        <div id="content">
	        	<div id="text-top" style="text-align:justify;">
					A Loja Virtual da Companhia de &Aacute;guas e Esgotos do Rio Grande do Norte &eacute; o ambiente onde se encontram dispon&iacute;veis os servi&ccedil;os da empresa. Esta &eacute; a forma mais r&aacute;pida de atendimento, tudo para facilitar a sua vida.					
	            </div>
	            <div id="info-index" class="box">
	            	<a href="exibirInformacoesPortalCaernAction.do" title="Informações | Tire suas dúvidas e entenda sua conta">
	                    <span id="text-box" style="text-align:justify;">
	                    	Este acesso permitir&aacute; esclarecimentos sobre diversos servi&ccedil;os que a CAERN oferece aos seus clientes. Para conhecer estes serviços ou tirar suas dúvidas clique no link informa&ccedil;&otilde;es.
	                    </span>
	                </a>
	            </div>
	            <div id="servicos-index" class="box">
                    <span id="text-box" style="text-align:justify;" title="Serviços | Os melhores serviços para sua comodidade">
						Para acessar o menu de op&ccedil;&otilde;es digite a matr&iacute;cula da sua conta de &aacute;gua e clique em OK
					</span>
				    <html:form styleId="form-matricula" action="/exibirServicosPortalCaernAction.do?method=servicos" method="post" 
						name="ExibirServicosPortalCaernActionForm" type="gcom.gui.portal.caern.ExibirServicosPortalCaernActionForm" >
	                    <fieldset>
	                        <label for="matricula">Matr&iacute;cula</label>
	                        <input type="text" name="matricula" id="matricula" class="campo-text" size="11" maxlength="11" tabindex="1" onkeypress="javascript: return isCampoNumerico(event);"/>
	                        <input type="submit" value="" class="btn-ok"  />
	                        <logic:present name="imovelInvalido" scope="request">
								<img src="/gsan/imagens/portal/caern/forms/matricula_invalida.png"/>
							</logic:present>
	                    </fieldset>
	                </html:form>                
	            </div>
	        </div>
	        <!-- Content - End -->
	        
	       <%@ include file="/jsp/portal/caern/rodape.jsp"%>
	    </div>
	    
	    <logic:present name="solicitarCpfCnpj" scope="request">
		    <div id="solicitarCpfCnpj" style="display:none; cursor: default;">
		    	
		    	<html:form styleId="form-matricula" style="padding: 8px 0 0 0px; width: 100%;" action="/exibirServicosPortalCaernAction.do?method=servicos&vcpf=true" method="post" 
					name="ExibirServicosPortalCaernActionForm" type="gcom.gui.portal.caern.ExibirServicosPortalCaernActionForm" >
                    <fieldset>
                        <label for="cpfOrCnpj" style="width:auto;" id="cpfOrCnpj">CPF/CNPJ do solicitante: </label>
                        <html:hidden property="matricula" styleId="matriculaAux" />
                        <html:text property="cpfCnpjSolicitante" style="width: 140px !important; background: url(/gsan/imagens/portal/caern/forms/matricula-text.png) no-repeat !important; background-position: 0px -4px !important;" styleClass="campo-text cpfCnpjSolicitante" size="14" maxlength="14" tabindex="1" onkeypress="javascript: return isCampoNumerico(event);"/>
                        <input type="submit" value="" class="btn-ok"  />
                        <logic:present name="cpfCnpjInvalido" scope="request">
							<span style="display: block; background: url('/gsan/imagens/portal/caern/forms/cpf_cnpj_invalido.png') no-repeat scroll 0pt 0pt transparent; width: 100px; margin-right: -12px;" id="cpfCnpjError"></span>
					
							<div  style="color: red; clear: both; text-align: justify; font-weight: bold; font-size: 11px; padding-top: 15px;">		
							Prezado cliente, favor entrar em contato com a Central de Atendimento, via 115, para se informar sobre como proceder para cadastrar o CPF/CNPJ.
							</div>
						</logic:present>
                        <logic:present name="cpfCnpjNaoCadastrado" scope="request">
							<span style="display: block; background: url('/gsan/imagens/portal/caern/forms/cpf_cnpj_nao_cadastrado.png') no-repeat scroll 0pt 0pt transparent; width: 180px; margin-right: -12px;" id="cpfCnpjError"></span>
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