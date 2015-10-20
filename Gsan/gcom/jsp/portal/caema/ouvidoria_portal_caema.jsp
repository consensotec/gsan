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
	    	    			
	    	    			<a class="adobe-reader" target="_blank" title="Faça o download do Adobe Reader" href="http://get.adobe.com/br/reader/">
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
							<p>Agradecemos seu contato com a Ouvidoria. Esse é mais um canal de relacionamento entre a CAEMA e o público interno e externo. 
							É um setor ligado à Diretoria de Operação e Manutenção e Atendimento ao Cliente - DO para recebimento de reclamações, informações, 
							denúncias, elogios e sugestões.</p>
							<p class="paragrafo">
								<em>Finalidade</em>
							</p>
							<ul class="lista">
								<li>Melhorar a qualidade dos serviços prestados;</li>
								<li>Assegurar ao cliente, o exame de sua reivindicação;</li>
								<li>Fortalecer a cidadania ao permitir a participação do cliente interno e externo;</li>
								<li>Esclarecer ao cliente sobre sua reclamação/informação;</li>
							</ul>
							
							<p class="paragrafo">
								<em>Como funciona</em>
							</p>
							<ul class="lista">
								<li>Recebe, avalia e encaminha as manifestações do cliente interno e externo, para a área competente sempre em busca de soluções;</li>
								<li>Comunica a existência do problema e requisita o devido esclarecimento;</li>
								<li>Procura responder, no menor prazo possível, de maneira honesta, objetiva, com transparência, integridade e respeito;</li>
								<li>Procura desburocratizar rotinas.</li>
							</ul>
							
							<p class="paragrafo">
								<em>Diferença entre o atendimento ao cliente e a ouvidoria</em>
							</p>
							<ul class="lista">
								<li>Os setores normais de atendimento (Call-Center, SAC, atendimento direto nas Unidades de Negócios) tratam das questões habituais, rotineiras;</li>
								<li>A Ouvidoria trata dos casos excepcionais, que não foram bem resolvidos pelos setores normais, por alguma razão operacional ou gerencial ou pela demora da solução do problema;</li>
							</ul>
							<br />
							
							<p class="paragrafo">
								<em>Forma de contato</em>
							</p>
							<ul class="lista">
								<li><em>Endereço:</em> Rua Silva Jardim, 307 (atendimento personalizado ou correspondência), Sede da CAEMA.</li>
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
									<span style="position: absolute; padding-top: 7px;"> Última atualização ( quarta, 15 de junho de 2011 )</span>
							</div>					
							             
		       	</div><!-- negociadebitos - End -->
	       </div><!-- Content - End -->
	    	 <%@ include file="/jsp/portal/caema/rodape.jsp"%>
	  	</div><!-- Container - End -->       
	</body>
</html:html>