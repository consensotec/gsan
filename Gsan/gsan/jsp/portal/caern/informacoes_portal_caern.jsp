<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
	<head>

		<title>CAERN | Informa&ccedil;&otilde;es</title>

		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-1.4.2.min.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.blockUI.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<link rel="stylesheet" href="<bean:message key="caminho.portalcaern.css"/>style.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portalcaern.css"/>internal.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portalcaern.css"/>jquery.theme.css" type="text/css">
		
		<script type="text/javascript">
			$(document).ready(function(){
				$('.info-serv').hide();
				$('#lista-servicos li, #lista-informacoes li').hover(function(){
					$('.ativo').removeClass('ativo');
					$(this).find('.info-serv').fadeIn(50);
					$(this).find('a').addClass('ativo').css('color', '#FFF');
				}, function(){
					$('.ativo').removeClass('ativo').css('color', '#008FD6');;
					$(this).find('.info-serv').fadeOut(50);
				});
			});
		</script>
	</head>
	
	<body>
		<div id="container"> 
		   <html:form action="/exibirInformacoesPortalCaernAction.do" method="post" 
				name="ExibirInformacoesPortalCaernActionForm" type="gsan.gui.portal.caer.ExibirInformacoesPortalCaernActionForm" >
		    	<%@ include file="/jsp/portal/caern/cabecalho.jsp"%>
		        
		        <!-- Content - Start -->
		         <div id="content">
		            <%@ include file="/jsp/portal/caern/cabecalhoInformacoes.jsp"%>
		            <ul id="lista-informacoes">
		          		
		          		<li>
		            		<a href="exibirInformacoesPortalCaernAction.do?method=parcelamentoDebito">
								<span>Negocia&ccedil;&atilde;o de d&eacute;bito</span>
							</a>
							<div class="info-serv" style="text-align:justify;display:block;">

		                        <p>
		                        Aqui voc&ecirc; encontra as informa&ccedil;&otilde;es e documenta&ccedil;&atilde;o necess&aacute;ria para negociar seus d&eacute;bitos.
		                        </p>
		                        <span id="bottom">&nbsp;</span>
		                        <img src="imagens/portal/caern/general/seta-info-servicos.gif" alt="Seta" />
		                    </div>
		                </li>
		                
		          		<li>
		            		<a href="exibirConsultarEstruturaTarifariaPortalCaernAction.do">
								<span>Estrutura tarif&aacute;ria</span>
							</a>
							<div class="info-serv" style="text-align:justify;display:block;">
		                        <p>Tire suas dúvidas quanto aos valores cobrados nas suas contas, 
		                        este acesso permite detalhar o valor cobrado para cada categoria de consumidor.
								</p>
		                        <span id="bottom">&nbsp;</span>
		                        <img src="imagens/portal/caern/general/seta-info-servicos.gif" alt="Seta" />
		                    </div>
		                </li>
		                
		                <li>
		            		<a href="exibirInformacoesPortalCaernAction.do?method=validarCertidaoNegativaDebito">
								<span>Validar Certid&atilde;o Negativa de D&eacute;bito</span>
							</a>
							
							<div class="info-serv" 
								style="text-align:justify;display:block;">
								 <p> &nbsp;
								</p>
		                        <p>Permite verificar a autenticidade da Certid&atilde;o Negativa de D&eacute;bitos.
								</p>
		                         <p> &nbsp;
								</p>
		                        <span id="bottom">&nbsp;</span>
		                        
		                        <img src="imagens/portal/caern/general/seta-info-servicos.gif" alt="Seta" />
		                    </div>
		              	</li>		                
		          		
						<li>
		            		<a href="exibirInformacoesPortalCaernAction.do?method=usoRacionalAgua">
								<span>Volume Consumido</span>
							</a>
						</li>

						<li>
		            		<a href="exibirConsultarTabelaServicosPortalCaernAction.do">
								<span>Tabela servi&ccedil;os</span>
							</a>
							<div class="info-serv" style="text-align:justify;display:block;">
		                        <p> &nbsp;
								</p>
		                        <p> &nbsp; Consulte o valor dos servi&ccedil;os prestados pela CAERN.
								</p>
								<p> &nbsp;
								</p>
		                        <span id="bottom">&nbsp;</span>
		                        <img src="imagens/portal/caern/general/seta-info-servicos.gif" alt="Seta" />
		                    </div>
		                </li>
		                
		                <li>
		            		<a href="exibirConsultarPagamentoFaturaPortalCaernAction.do">
								<span>Onde pagar sua fatura</span>
							</a>
						</li>
						
						<li>
		            		<a href="exibirInformacoesPortalCaernAction.do?method=orientacoesCliente">
								<span>Orienta&ccedil;&otilde;es ao cliente</span>
							</a>
						</li>
						
            			<li>
		            		<a href="exibirInformacoesPortalCaernAction.do?method=debitoAutomatico">
								<span>D&eacute;bito autom&aacute;tico em conta</span>
							</a>
							
							<div class="info-serv" 
								style="text-align:justify;display:block;">
								
		                        <p>O serviço de d&eacute;bito autom&aacute;tico em conta corrente é mais uma facilidade de pagamento que a 
		                        CAERN oferece aos seus Clientes.
								</p>
		                        
		                        <span id="bottom">&nbsp;</span>
		                        
		                        <img src="imagens/portal/caema/general/seta-info-servicos.gif" alt="Seta" />
		                    </div>
		              	</li>		                
						
		              		                
		            </ul>
		        </div>
	        <!-- Content - End -->
	        
	       <%@ include file="/jsp/portal/caern/rodape.jsp"%>
	       </html:form>
	    </div>
	</body>
</html:html>