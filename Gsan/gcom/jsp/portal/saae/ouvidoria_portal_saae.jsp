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
		        	<div id="negociadebitos" class="serv-int" style="width:880px;">	
	    	    			
	    	    			<a class="adobe-reader" target="_blank" title="Faça o download do Adobe Reader" href="http://get.adobe.com/br/reader/">
								<img alt="Download do Adobe Reader" src="/gsan/imagens/portal/saae/general/adobe-reader.gif">
							</a>
							<a class="btn-voltar" title="Voltar" href="exibirServicosPortalSaaeAction.do">
								<img alt="Voltar" src="/gsan/imagens/portal/saae/general/btn-voltar.gif">
							</a>
	    	    			
							<p>&nbsp;</p>
						
	        				<h3>
								Ouvidoria<span>&nbsp;</span>
							</h3>
							<br />
							<br />
							<p>O SAAE disponibiliza mais um canal de comunicação para clientes e funcionários. A ouvidoria tem por objetivo estreitar a relação da diretoria com o público interno e externo.</p>
							
							<p class="paragrafo">
								<em>Forma de contato</em>
							</p>
							<ul class="lista">
								<li><em>Endereço:</em> RUA DO PARAISO, Nº 02 - SANTO ANTÔNIO - JUAZEIRO-BA.</li>
								<li><em>Telefone:</em>  (74) 3612-5838/3613-0659.</li>
								<li><em>Site:</em><a href="http://saaejuazeiro.ba.ipmbrasil.org.br/" target="_blank">http://saaejuazeiro.ba.ipmbrasil.org.br/</a></li>
								<li><em>E-mail:</em></li>
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
							
							<div id="atualizacao" style="background-image: url(/gsan/imagens/portal/saae/general/ultima_atualizacao.png);background-repeat: no-repeat; ">
									<span style="position: absolute; padding-top: 7px;"> Última atualização (segunda, 03 de setembro de 2012)</span>
							</div>					
							             
		       	</div><!-- negociadebitos - End -->
	       </div><!-- Content - End -->
	    	 <%@ include file="/jsp/portal/saae/rodape.jsp"%>
	  	</div><!-- Container - End -->       
	</body>
</html:html>