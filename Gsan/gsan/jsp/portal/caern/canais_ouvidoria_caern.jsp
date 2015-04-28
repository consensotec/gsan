<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
	<head>
		<title>CAERN | Canais de atendimento</title>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-1.4.2.min.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<link rel="stylesheet" href="<bean:message key="caminho.portalcaern.css"/>style.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portalcaern.css"/>internal.css" type="text/css">
	<style type="text/css">
					
			.lista li {
				list-style: url("/gsan/imagens/portal/caern/general/marcador.gif");
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
	    	<%@ include file="/jsp/portal/caern/cabecalho.jsp"%>
	        
	        <!-- Content - Start -->
		        <div id="content">
		        	<div id="negociadebitos" class="serv-int" style="width:880px;">	
	    	    			
	    	    			<a class="adobe-reader" target="_blank" title="Fa&ccedil;a o download do Adobe Reader" href="http://get.adobe.com/br/reader/">
								<img alt="Download do Adobe Reader" src="/gsan/imagens/portal/caern/general/adobe-reader.gif">
							</a>
							<a class="btn-voltar" title="Voltar" href="exibirServicosPortalCaernAction.do">
								<img alt="Voltar" src="/gsan/imagens/portal/caern/general/btn-voltar.gif">
							</a>
	    	    			
							<p>&nbsp;</p>
						
	        				<h3>
								Ouvidoria<span>&nbsp;</span>
							</h3>
							<br />
							<br />
							<p>
							A ouvidoria &eacute; um espa&ccedil;o permanente de di&aacute;logo entre a empresa e seus clientes, atrav&eacute;s do qual podem fazer elogios, 
							dar sugest&otilde;es ou registrar reclama&ccedil;&otilde;es sobre servi&ccedil;os mal executados ou que estejam fora do prazo estipulado pela CAERN. 
							Todas as reclama&ccedil;&otilde;es e elogios registrados s&atilde;o encaminhados às esferas superiores, 
							com o objetivo de obter uma solu&ccedil;&atilde;o r&aacute;pida dos problemas apresentados e a satisfa&ccedil;&atilde;o do usu&aacute;rio.</p>							
							<br />
							
							<p>Para garantir a realiza&ccedil;&atilde;o de um servi&ccedil;o idôneo, o setor pode manter sigilo sobre a identidade do cliente, caso ele prefira</p>
							
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
							
							             
		       	</div><!-- negociadebitos - End -->
	       </div><!-- Content - End -->
	    	 <%@ include file="/jsp/portal/caern/rodape.jsp"%>
	  	</div><!-- Container - End -->       
	</body>
</html:html>