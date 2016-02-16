<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
	<head>
		<title>Saae</title>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-1.4.2.min.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<link rel="stylesheet" href="<bean:message key="caminho.portalsaae.css"/>style.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portalsaae.css"/>internal.css" type="text/css">
	<style type="text/css">
					
			.lista li {
				list-style: url("/gsan/imagens/portal/saae/general/marcador.gif");
				margin: 0 0 0 15px;
				padding: 0px;
			}
			
			em {
    			color: #008FD6;
    			font-style: normal;
   				font-weight: 700;
    			padding-right: 5px;
			}
			font {
   				color: #008FD6;
    			float: none;
   				margin: 0;
    			padding-bottom: 10px;
    			text-indent: 0;
				font-style:italic;
    			float: right;
    		}
    		.paragrafo {
    			line-height: 30px;
    		}    		
    		p{
    		text-align: justify;
    		}
			#atualizacao{
    			line-height:2.3em;
    			padding:0 15px; 
    			position:relative;    			
    			float:left; 
    			font-size:11px;
    			height:33px;
    			width: 315px;  	
    		}
    					
		</style>
	</head>
	
	<body>
		<div id="container">
	    	<%@ include file="/jsp/portal/saae/cabecalho.jsp"%>
	        
	        <!-- Content - Start -->
		        <div id="content">
		        <%@ include file="/jsp/portal/saae/cabecalhoInformacoes.jsp"%>
		        	<div id="negociadebitos" class="serv-int" style="width:880px;">	
	    	    			
							<p>&nbsp;</p>
							
							<h3>
								Negocia&ccedil;&atilde;o de D&eacute;bitos<span>&nbsp;</span>
							</h3>
							<br />
							<br />
							
							<p>O SAAE adotou algumas medidas para que o usu&aacute;rio tenha conhecimento e condi&ccedil;&otilde;es de negociar 
							e pagar seus d&eacute;bitos.</p>
							
							<p class="paragrafo"><em><b>Aviso de D&eacute;bito:</b></em></p>
							<p>
								O usu&aacute;rio que deve mais de 3 meses recebe na sua resid&ecirc;ncia um Aviso de D&eacute;bito, onde consta todo seu d&eacute;bito, 
								podendo ser quitado de imediato nas agencias banc&aacute;rias, loterias e postos de arrecada&ccedil;&atilde;o credenciadas pela empresa 
								ou o usu&aacute;rio comparece no escrit&oacute;rio central da SAAE na Rua Barão de Cotegipe, Centro da cidade para negociar sua d&iacute;vida.
							</p>
							<p>&nbsp;</p>
							
							<p>O n&atilde;o comparecimento o SAAE no prazo de 15 dias para negocia&ccedil;&atilde;o, implicar&aacute; na suspens&atilde;o do fornecimento de &aacute;gua.
							</p>
							<p>&nbsp;</p>
							
							<p>Se mesmo ap&oacute;s a suspens&atilde;o do fornecimento de &aacute;gua o usu&aacute;rio n&atilde;o comparecer a SAAE, 
							uma equipe de revis&atilde;o de corte ir&aacute; ate o local. 
							Se detectado religa&ccedil;&atilde;o indevida, o usu&aacute;rio será notificado e multado  e novamente ser&aacute; realizado o corte.
							</p>
							<p>&nbsp;</p>
							
							<p>Caso o usu&aacute;rio insista na liga&ccedil;&atilde;o indevida, o 3º corte ser&aacute; 
							na rede geral e o usu&aacute;rio ser&aacute; encaminhado a Assessoria Jur&iacute;dica para provid&ecirc;ncias cab&iacute;veis.
							</p>
							<p>&nbsp;</p>
							
	        				<h3>
								Parcelamento de D&eacute;bito<span>&nbsp;</span>
							</h3>
							<br />
							<br />
							
							<p class="paragrafo"><em><b>Contrato de Transa&ccedil;&atilde;o de Parcelamento:</b></em></p>
							<p>
								Somente o titular da conta de &aacute;gua ou o procurador poder&aacute; assinar o Contrato. 
								Neste contrato o usu&aacute;rio ter&aacute; que dar uma entrada de 30% do valor de todo d&eacute;bito e divide o restante em at&eacute; 12 vezes, 
								n&atilde;o podendo o valor da parcela ser inferior a R$ 40,00. 
								As parcelas ser&atilde;o inclusas nas pr&oacute;ximas faturas, juntamente com o consumo mensal.
							</p>
							<p>&nbsp;</p>
							<p>
								Existem situa&ccedil;&otilde;es especiais, analisadas por uma equipe da Assessoria Jur&iacute;dica, onde &eacute; levado em considera&ccedil;&atilde;o 
								o valor do d&eacute;bito, situa&ccedil;&atilde;o do usu&aacute;rio, podendo o d&eacute;bito ser dividido em at&eacute; 36 vezes.
							</p>
							<p>&nbsp;</p>
							
							<br />
							<br />
							
							<p align="left">&nbsp;</p>
							<p align="left">&nbsp;</p>
					
							<font><span style="font-size: 11px;">Fonte SAAE/ NOR: 24.07/2008</span></font>
						
							<p align="left">&nbsp;</p>
							<p align="left">&nbsp;</p>
							
							<br />
							<br />
							<br />
							<br />
							
							<!--  <div id="atualizacao" style="background-image: url(/gsan/imagens/portal/saae/general/ultima_atualizacao.png);background-repeat: no-repeat;">
									<span style="position: absolute; padding-top: 7px;"> &Uacute;ltima atualiza&ccedil;&atilde;o ( quinta, 13 de Setembro de 2012 )</span>
							</div>	-->				
							             
		       	</div><!-- negociadebitos - End -->
	       </div><!-- Content - End -->
	    	<%@ include file="/jsp/portal/saae/rodape.jsp"%>
	  	</div><!-- Container - End -->       
	</body>
</html:html>