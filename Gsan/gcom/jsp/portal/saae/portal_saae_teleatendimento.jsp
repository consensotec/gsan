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
		
		<style>
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
		        	<div id="teleatend" class="serv-int" style="width:890px;">	
	    	    			<a class="adobe-reader" target="_blank" title="Faça o download do Adobe Reader" href="http://get.adobe.com/br/reader/">
								<img alt="Download do Adobe Reader" src="/gsan/imagens/portal/saae/general/adobe-reader.gif">
							</a>
							<a class="btn-voltar" title="Voltar" href="exibirServicosPortalsaaeAction.do">
								<img alt="Voltar" src="/gsan/imagens/portal/saae/general/btn-voltar.gif">
							</a>
							<p>&nbsp;</p>
						
	        				<h3>
								Teleatendimento<span>&nbsp;</span>
							</h3>
						
							<br>&nbsp;</br>
							 <p>&nbsp;</p>
							
							<div>                  
				               <em>Teleatendimento (Call Center) 0800-280 95 20</em>             
		                
				        		<p>&nbsp;</p>
				        		
						           <p style="text-align: justify;">
								<img style="float: left; margin-right: 25px;" src="/gsan/imagens/portal/saae/general/teleatendimento.bmp">
									Reclamações, sugestões e consultas em geral podem ser realizadas por telefone. Este atendimento é gratuito.
				                	<em>0800-280 95 20</em>
								<br />
								<br />
								</p>
								<p align="left">&nbsp;</p>
								<p align="left">&nbsp;</p>
						
						
								<p align="left">&nbsp;</p>
								<p align="left">&nbsp;</p>
								<p align="left">&nbsp;</p>
								
								<div id="atualizacao" style="background-image: url(/gsan/imagens/portal/saae/general/ultima_atualizacao.png);background-repeat: no-repeat; ">
									<span style="position: absolute; padding-top: 7px; ">Última atualização (segunda, 10 de Setembro de 2012)</span>
								</div>
								
								<p align="left">&nbsp;</p>
								<p align="left">&nbsp;</p>
								<p align="left">&nbsp;</p>
	                		</div>			                   
		       		</div><!-- Content - End -->
	   	    	</div>
	    		 <%@ include file="/jsp/portal/saae/rodape.jsp"%>
	  	</div><!-- Container - End -->       
	</body>
</html:html>