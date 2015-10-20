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
	    	<%@ include file="/jsp/portal/caern/cabecalho.jsp"%>
	        
	        <!-- Content - Start -->
		        <div id="content">
		        	<div id="teleatend" class="serv-int" style="width:890px;">	
	    	    			<a class="adobe-reader" target="_blank" title="Fa&ccedil;a o download do Adobe Reader" href="http://get.adobe.com/br/reader/">
								<img alt="Download do Adobe Reader" src="/gsan/imagens/portal/caern/general/adobe-reader.gif">
							</a>
							<a class="btn-voltar" title="Voltar" href="exibirServicosPortalCaernAction.do">
								<img alt="Voltar" src="/gsan/imagens/portal/caern/general/btn-voltar.gif">
							</a>
							<p>&nbsp;</p>
						
	        				<h3>
								Teleatendimento<span>&nbsp;</span>
							</h3>
						
							<br>&nbsp;</br>
							<p>
							
							O teleatendimento da CAERN funciona 24 horas por dia, todos os dias da semana, inclusive s&aacute;bados, domingos e feriados.
							</p>
							<br />
							<p> 
							Atrav&eacute;s do n&uacute;mero 115 ou 0800-084-0195, os clientes da empresa podem fazer solicita&ccedil;&otilde;es referentes a reparos de vazamentos e falta de &aacute;gua, 
							obstru&ccedil;&otilde;es e transbordamentos de esgotos e informa&ccedil;&otilde;es sobre outros servi&ccedil;os.
							</p>
							<br />
							<p> 
							Para agilizar o atendimento , o cliente deve estar com o CPF e o n&uacute;mero da matr&iacute;cula do im&oacute;vel (que consta na conta)
							</p>
			                   
		       		</div><!-- Content - End -->
	   	    	</div>
	    		 <%@ include file="/jsp/portal/caern/rodape.jsp"%>
	  	</div><!-- Container - End -->       
	</body>
</html:html>