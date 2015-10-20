<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
	<head>
		<title>Caema</title>
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
		        <%@ include file="/jsp/portal/caema/cabecalhoInformacoes.jsp"%>
		        	<div id="orientacoesCliente" class="serv-int" style="width:880px;">	
	    	    			
							<p>&nbsp;</p>
							<h3>
								Orienta&ccedil;&atilde;es ao cliente:<span>&nbsp;</span>
							</h3>
							
							<br />
							<br />
							
							<p><em>
								<a href="http://www.caema.ma.gov.br/portalcaema/index.php?option=com_content&view=article&id=99&Itemid=55" target="_blank" title="Dicas Gerais">
								<b>1. Dicas Gerais</b>
								</a>
							</em></p>
										
							<br />
							
							<p><em>
								<a href="http://www.caema.ma.gov.br/portalcaema/index.php?option=com_content&view=article&id=847&Itemid=125" target="_blank" title="Evite desperdício">
								<b>2. Evite desperdício</b>
								</a>
							</em></p>

							<br />	
							<p><em>
								<a href="http://www.caema.ma.gov.br/portalcaema/index.php?option=com_content&view=article&id=100&Itemid=120" target="_blank" title="Evite obstru&ccedil;&atilde;o de esgoto">
								<b>3. Evite obstru&ccedil;&atilde;o de esgoto</b>
								</a>
							</em></p>

							<br />	
							<p><em>
								<a href="http://www.caema.ma.gov.br/portalcaema/index.php?option=com_content&view=article&id=201&Itemid=125" target="_blank" title="Dicas de Limpeza">
								<b>4. Limpeza caixa d´agua</b>
								</a>
							</em></p>
							
						     
		       	</div><!-- negociadebitos - End -->
	       </div><!-- Content - End -->
	    	 <%@ include file="/jsp/portal/caema/rodape.jsp"%>
	  	</div><!-- Container - End -->       
	</body>
</html:html>