<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
	<head>
		<title>CAEMA</title>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-1.4.2.min.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<link rel="stylesheet" href="<bean:message key="caminho.portalcaema.css"/>style.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portalcaema.css"/>internal.css" type="text/css">
	<style type="text/css">
					
			.lista li {
				list-style: url("/gsan/imagens/portal/caema/general/marcador.gif");
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
	    	<%@ include file="/jsp/portal/caema/cabecalho.jsp"%>
	        
	        <!-- Content - Start -->
		        <div id="content">
		        	<div id="negociadebitos" class="serv-int" style="width:880px;">	
	    	    			
	    	    			<a class="adobe-reader" target="_blank" title="Fa�a o download do Adobe Reader" href="http://get.adobe.com/br/reader/">
								<img alt="Download do Adobe Reader" src="/gsan/imagens/portal/caema/general/adobe-reader.gif">
							</a>
							<a class="btn-voltar" title="Voltar" href="exibirServicosPortalCaemaAction.do">
								<img alt="Voltar" src="/gsan/imagens/portal/caema/general/btn-voltar.gif">
							</a>
	    	    			
							<p>&nbsp;</p>
						
	        				<h3>
								Ouvidoria<span>&nbsp;</span>
							</h3>
							<br />
							<br />
							<p>Agradecemos seu contato com a Ouvidoria. Esse � mais um canal de relacionamento entre a CAEMA e o p�blico interno e externo. 
							� um setor ligado � Diretoria de Opera��o e Manuten��o e Atendimento ao Cliente - DO para recebimento de reclama��es, informa��es, 
							den�ncias, elogios e sugest�es.</p>
							<p class="paragrafo">
								<em>Finalidade</em>
							</p>
							<ul class="lista">
								<li>Melhorar a qualidade dos servi�os prestados;</li>
								<li>Assegurar ao cliente, o exame de sua reivindica��o;</li>
								<li>Fortalecer a cidadania ao permitir a participa��o do cliente interno e externo;</li>
								<li>Esclarecer ao cliente sobre sua reclama��o/informa��o;</li>
							</ul>
							
							<p class="paragrafo">
								<em>Como funciona</em>
							</p>
							<ul class="lista">
								<li>Recebe, avalia e encaminha as manifesta��es do cliente interno e externo, para a �rea competente sempre em busca de solu��es;</li>
								<li>Comunica a exist�ncia do problema e requisita o devido esclarecimento;</li>
								<li>Procura responder, no menor prazo poss�vel, de maneira honesta, objetiva, com transpar�ncia, integridade e respeito;</li>
								<li>Procura desburocratizar rotinas.</li>
							</ul>
							
							<p class="paragrafo">
								<em>Diferen�a entre o atendimento ao cliente e a ouvidoria</em>
							</p>
							<ul class="lista">
								<li>Os setores normais de atendimento (Call-Center, SAC, atendimento direto nas Unidades de Neg�cios) tratam das quest�es habituais, rotineiras;</li>
								<li>A Ouvidoria trata dos casos excepcionais, que n�o foram bem resolvidos pelos setores normais, por alguma raz�o operacional ou gerencial ou pela demora da solu��o do problema;</li>
							</ul>
							<br />
							
							<p class="paragrafo">
								<em>Forma de contato</em>
							</p>
							<ul class="lista">
								<li><em>Endere�o:</em> Rua Silva Jardim, 307 (atendimento personalizado ou correspond�ncia), Sede da CAEMA.</li>
								<li><em>Telefone:</em> (98) 3219-5170 ou (98) 3219-5062.</li>
								<li><em>Site:</em> www.caema.ma.gov.br</li>
								<li><em>E-mail:</em> ouvidoria@caema.ma.gov.br</li>
							</ul>
							
							<br />
							<br />
							
							
							<p align="left">&nbsp;</p>
							<p align="left">&nbsp;</p>
					
							<p align="left">&nbsp;</p>
							<p align="left">&nbsp;</p>
							
							<br />
							<br />
							<br />
							<br />
							
							<div id="atualizacao" style="background-image: url(/gsan/imagens/portal/caema/general/ultima_atualizacao.png);background-repeat: no-repeat;">
									<span style="position: absolute; padding-top: 7px;"> �ltima atualiza��o ( quarta, 15 de junho de 2011 )</span>
							</div>					
							             
		       	</div><!-- negociadebitos - End -->
	       </div><!-- Content - End -->
	    	 <%@ include file="/jsp/portal/caema/rodape.jsp"%>
	  	</div><!-- Container - End -->       
	</body>
</html:html>