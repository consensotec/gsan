<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
	<head>

		<title>Caema | Informa&ccedil;&otilde;es</title>

		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-1.4.2.min.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.blockUI.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<link rel="stylesheet" href="<bean:message key="caminho.portalcaema.css"/>style.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portalcaema.css"/>internal.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portalcaema.css"/>jquery.theme.css" type="text/css">
		
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
		   <html:form action="/exibirInformacoesPortalCaemaAction.do" method="post" 
				name="ExibirInformacoesPortalCaemaActionForm" type="gcom.gui.portal.caema.ExibirInformacoesPortalCaemaActionForm" >
		    	<%@ include file="/jsp/portal/caema/cabecalho.jsp"%>
		        
		        <!-- Content - Start -->
		         <div id="content">
		            <%@ include file="/jsp/portal/caema/cabecalhoInformacoes.jsp"%>`
		            <ul id="lista-informacoes">
		          	  <li>
		            		<a href="exibirInformacoesPortalCaemaAction.do?method=parcelamentoDebito">
								<span>Parcelamento de d&eacute;bito</span>
							</a>
							<div class="info-serv" style="text-align:justify;display:block;">
							<!-- Alterar acentuação -->
		                        <p>Aqui voc&ecirc; encontrar&aacute; informa&ccedil;&otilde;es de toda documenta&ccedil;&atilde;o necess&aacute;ria para parcelamento de d&eacute;bito.
		                        </p>
		                        <span id="bottom">&nbsp;</span>
		                        <img src="imagens/portal/caema/general/seta-info-servicos.gif" alt="Seta" />
		                    </div>
		                </li>
		            	
		            	<li>
		            		<a href="exibirConsultarEstruturaTarifariaPortalCaemaAction.do">
								<span>Estrutura tarif&aacute;ria</span>
							</a>
							<div class="info-serv" style="text-align:justify;display:block;">

		                        <p>Objetivando reduzir suas d&uacute;vidas quanto aos valores cobrados nas suas contas, este acesso
									permite detalhar o valor cobrado para cada categoria de consumidor medido e n&atilde;o medido.

								</p>
								
		                        <span id="bottom">&nbsp;</span>
		                        <img src="imagens/portal/caema/general/seta-info-servicos.gif" alt="Seta" />
		                    </div>
		                </li>
		               <li>
		            		<a href="exibirConsultarRegulamentoServicosPortalCaemaAction.do">
								<span>Regulamento de servi&ccedil;os</span>
							</a>
							<div class="info-serv" style="text-align:justify;display:block;">
		                        <p> Este Regulamento (DECRETO N&#176; 11.060/89, alterado pelo Decreto Estadual
								11.898/91) define e disciplina os crit&eacute;rios a serem aplicados aos servi&ccedil;os
								de abastecimento de &aacute;gua e coleta de esgotos sanit&aacute;rios, administrados pela
								Companhia de Saneamento Ambiental do Maranh&atilde;o, CAEMA.
		                		</p>
		                        <span id="bottom">&nbsp;</span>
		                        <img src="imagens/portal/caema/general/seta-info-servicos.gif" alt="Seta" />
		                    </div>
		                </li>
		            	<li>
		            		<a href="exibirInformacoesPortalCaemaAction.do?method=vivaAgua">
								<span>Viva &aacute;gua</span>
							</a>
							<div class="info-serv" style="text-align:justify;display:block;">
							
							<!-- Alterar centuação -->
		                        <p>Um dos programas do atual governo do Estado do Maranh&atilde;o tem como objeto a mobiliza&ccedil;&atilde;o
		                         e participa&ccedil;&atilde;o social nos servi&ccedil;os de saneamento, sendo a principal meta deste programa o
		                         atendimento a pessoas consideradas de baixa renda na pir&acirc;mide social.
		                        </p>
		                        <span id="bottom">&nbsp;</span>
		                        <img src="imagens/portal/caema/general/seta-info-servicos.gif" alt="Seta" />
		                    </div>
		                </li>
		            	<li>
		            		<a href="exibirConsultarTabelaServicosPortalCaemaAction.do">
								<span>Tabela servi&ccedil;os</span>
							</a>
							<div class="info-serv" style="text-align:justify;display:block;">
		                        <p> &nbsp;
								</p>
		                        <p> &nbsp;                                                    Consulte o valor dos servi&ccedil;os prestados pela CAEMA.
								</p>
								<p> &nbsp;
								</p>
		                        <span id="bottom">&nbsp;</span>
		                        <img src="imagens/portal/caema/general/seta-info-servicos.gif" alt="Seta" />
		                    </div>
		                </li>

	                  	<li>
		            		<a href="exibirInformacoesPortalCaemaAction.do?method=ligacaoAguaEsgoto">
								<span>Como pedir liga&ccedil;&atilde;o de &aacute;gua ou esgoto</span>
							</a>
							
							<div class="info-serv" 
								style="text-align:justify;display:block;">
								
		                        <p>Para obter os servi&ccedil;os de abastecimento de &aacute;gua tratada e os servi&ccedil;os de coleta 
		                        de esgoto da CAEMA deve o interessado formalizar o pedido de liga&ccedil;&atilde;o predial de &aacute;gua
		                         e/ou liga&ccedil;&atilde;o de esgoto, junto &aacute; Loja de Atendimento da CAEMA do Munic&iacute;pio munido, 
		                         preferencialmente, com uma fatura de &aacute;gua e esgoto do im&oacute;vel mais pr&oacute;ximo.
								</p>
		                        
		                        <span id="bottom">&nbsp;</span>
		                        
		                        <img src="imagens/portal/caema/general/seta-info-servicos.gif" alt="Seta" />
		                    </div>
		              	</li>
		                
            			<li>
		            		<a href="exibirInformacoesPortalCaemaAction.do?method=debitoAutomatico">
								<span>D&eacute;bito autom&aacute;tico em conta</span>
							</a>
							
							<div class="info-serv" 
								style="text-align:justify;display:block;">
								
		                        <p>O servi�o de d&eacute;bito autom&aacute;tico em conta corrente � mais uma facilidade de pagamento que a 
		                        CAEMA oferece aos seus Clientes.
								</p>
		                        
		                        <span id="bottom">&nbsp;</span>
		                        
		                        <img src="imagens/portal/caema/general/seta-info-servicos.gif" alt="Seta" />
		                    </div>
		              	</li>		                
		                
		                
		                <li>
		            		<a href="exibirConsultarPagamentoFaturaPortalCaemaAction.do">
								<span>Onde pagar sua fatura</span>
							</a>
							
		                </li>
		                <li>
		            		<a href="exibirInformacoesPortalCaemaAction.do?method=orientacoesCliente">
								<span>Orienta&ccedil;&otilde;es ao cliente</span>
							</a>
	                	</li>
            			<li>
		            		<a href="exibirInformacoesPortalCaemaAction.do?method=validarCertidaoNegativaDebito">
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
		                        
		                        <img src="imagens/portal/caema/general/seta-info-servicos.gif" alt="Seta" />
		                    </div>
		              	</li>		                
		                
		            </ul>
		        </div>
	        <!-- Content - End -->
	        
	       <%@ include file="/jsp/portal/caema/rodape.jsp"%>
	       </html:form>
	    </div>
	</body>
</html:html>