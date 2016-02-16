<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
	<head>

		<title>Saae | Informa&ccedil;&otilde;es</title>

		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-1.4.2.min.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.blockUI.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<link rel="stylesheet" href="<bean:message key="caminho.portalsaae.css"/>style.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portalsaae.css"/>internal.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portalsaae.css"/>jquery.theme.css" type="text/css">
		
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
		   <html:form action="/exibirInformacoesPortalSaaeAction.do" method="post" 
				name="ExibirInformacoesPortalSaaeActionForm" type="gcom.gui.portal.saae.ExibirInformacoesPortalSaaeActionForm" >
		    	<%@ include file="/jsp/portal/saae/cabecalho.jsp"%>
		        
		        <!-- Content - Start -->
		         <div id="content">
		            <%@ include file="/jsp/portal/saae/cabecalhoInformacoes.jsp"%>
		            <ul id="lista-informacoes">
		          		<!-- 
		          		<li>
		            		<a href="exibirConsultarEstruturaTarifariaPortalSaaeAction.do">
								<span>Estrutura tarif&aacute;ria</span>
							</a>
							<div class="info-serv" style="text-align:justify;display:block;">
		                        <p>Tire suas dúvidas quanto aos valores cobrados nas suas contas, 
		                        este acesso permite detalhar o valor cobrado para cada categoria de consumidor.
								</p>
		                        <span id="bottom">&nbsp;</span>
		                        <img src="imagens/portal/saae/general/seta-info-servicos.gif" alt="Seta" />
		                    </div>
		                </li>
		          		 -->
		          		<li>
		            		<a href="exibirInformacoesPortalSaaeAction.do?method=parcelamentoDebito">
								<span>Parcelamento de d&eacute;bito</span>
							</a>
							<div class="info-serv" style="text-align:justify;display:block;">
							<!-- Alterar acentuaÃ§Ã£o -->
		                        <p>
		                        Aqui voc&ecirc; encontra as informa&ccedil;&otilde;es e documenta&ccedil;&atilde;o necess&aacute;ria para negociar seus d&eacute;bitos.
		                        </p>
		                        <span id="bottom">&nbsp;</span>
		                        <img src="imagens/portal/saae/general/seta-info-servicos.gif" alt="Seta" />
		                    </div>
		                </li>
		                <!--
		                <li>
		            		<a href="exibirConsultarPagamentoFaturaPortalSaaeAction.do">
								<span>Onde pagar sua fatura</span>
							</a>
						</li>
						
						<li>
		            		<a href="exibirConsultarTabelaServicosPortalSaaeAction.do">
								<span>Tabela servi&ccedil;os</span>
							</a>
							<div class="info-serv" style="text-align:justify;display:block;">
		                        <p> &nbsp;
								</p>
		                        <p> &nbsp; Consulte o valor dos servi&ccedil;os prestados pela SAAE.
								</p>
								<p> &nbsp;
								</p>
		                        <span id="bottom">&nbsp;</span>
		                        <img src="imagens/portal/saae/general/seta-info-servicos.gif" alt="Seta" />
		                    </div>
		                </li>
		                
		                <li>
		            		<a href="exibirInformacoesPortalSaaeAction.do?method=validarCertidaoNegativaDebito">
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
		                        
		                        <img src="imagens/portal/saae/general/seta-info-servicos.gif" alt="Seta" />
		                    </div>
		              	</li>
		            	
		            	<!-- 
		            	<li>
		            		<a href="exibirConsultarRegulamentoServicosPortalSaaeAction.do">
								<span>Regulamento de servi&ccedil;os</span>
							</a>
							<div class="info-serv" style="text-align:justify;display:block;">
		                        <p> Este Regulamento (DECRETO N&#176; 11.060/89, alterado pelo Decreto Estadual
								11.898/91) define e disciplina os crit&eacute;rios a serem aplicados aos servi&ccedil;os
								de abastecimento de &aacute;gua e coleta de esgotos sanit&aacute;rios, administrados pela
								Companhia de Saneamento Ambiental do Maranh&atilde;o, saae.
		                		</p>
		                        <span id="bottom">&nbsp;</span>
		                        <img src="imagens/portal/saae/general/seta-info-servicos.gif" alt="Seta" />
		                    </div>
		                </li>
		                -->	
		            	
		            	<!--
		            	<li>
		            		<a href="exibirInformacoesPortalSaaeAction.do?method=vivaAgua">
								<span>Viva &aacute;gua</span>
							</a>
							<div class="info-serv" style="text-align:justify;display:block;">
							
							    <p>Um dos programas do atual governo do Estado do Maranh&atilde;o tem como objeto a mobiliza&ccedil;&atilde;o
		                         e participa&ccedil;&atilde;o social nos servi&ccedil;os de saneamento, sendo a principal meta deste programa o
		                         atendimento a pessoas consideradas de baixa renda na pir&acirc;mide social.
		                        </p>
		                        <span id="bottom">&nbsp;</span>
		                        <img src="imagens/portal/saae/general/seta-info-servicos.gif" alt="Seta" />
		                    </div>
		                </li>
		                -->
		            	
		            	<!--
	                  	<li>
		            		<a href="exibirInformacoesPortalSaaeAction.do?method=ligacaoAguaEsgoto">
								<span>Como pedir liga&ccedil;&atilde;o de &aacute;gua ou esgoto</span>
							</a>
							
							<div class="info-serv" 
								style="text-align:justify;display:block;">
								
		                        <p>Para obter os servi&ccedil;os de abastecimento de &aacute;gua tratada e os servi&ccedil;os de coleta 
		                        de esgoto da SAAE deve o interessado formalizar o pedido de liga&ccedil;&atilde;o predial de &aacute;gua
		                         e/ou liga&ccedil;&atilde;o de esgoto, junto &aacute; Loja de Atendimento da SAAE do Munic&iacute;pio munido, 
		                         preferencialmente, com uma fatura de &aacute;gua e esgoto do im&oacute;vel mais pr&oacute;ximo.
								</p>
		                        
		                        <span id="bottom">&nbsp;</span>
		                        
		                        <img src="imagens/portal/saae/general/seta-info-servicos.gif" alt="Seta" />
		                    </div>
		              	</li>
		              	-->
		                
		                <!--
            			<li>
		            		<a href="exibirInformacoesPortalSaaeAction.do?method=debitoAutomatico">
								<span>D&eacute;bito autom&aacute;tico em conta</span>
							</a>
							
							<div class="info-serv" 
								style="text-align:justify;display:block;">
								
		                        <p>O serviço de d&eacute;bito autom&aacute;tico em conta corrente é mais uma facilidade de pagamento que a 
		                        SAAE oferece aos seus Clientes.
								</p>
		                        
		                        <span id="bottom">&nbsp;</span>
		                        
		                        <img src="imagens/portal/saae/general/seta-info-servicos.gif" alt="Seta" />
		                    </div>
		              	</li>
		              	-->		                
		                
		                <!-- 
		                <li>
		            		<a href="exibirInformacoesPortalSaaeAction.do?method=orientacoesCliente">
								<span>Orienta&ccedil;&otilde;es ao cliente</span>
							</a>
	                	</li>
            			-->
		              		               
		            </ul>
		        </div>
	        <!-- Content - End -->
	        
	       <%@ include file="/jsp/portal/saae/rodape.jsp"%>
	       </html:form>
	    </div>
	</body>
</html:html>