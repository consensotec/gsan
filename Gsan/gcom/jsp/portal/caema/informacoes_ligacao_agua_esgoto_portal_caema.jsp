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
						
			.lista-condicoes ul li {
				list-style: url("/gsan/imagens/portal/caema/general/marcador.gif");
				margin: 0 0 0 15px;
				list-style-position: outside;
				padding: 0px;
			}
			
			.lista-condicoes li {
				list-style: decimal;
				list-style-position: inside;
				font-weight: bold;
				color: #008FD6;
				margin: 0 0 0 0px;
				padding: 0px;
							
			}
			
			.lista-condicoes li #lista ul li {
				list-style: upper-alpha inside none;				
				list-style-position: inside;
				font-weight: bold;
				color: #000;
				margin: 0 0 0 30px;
				padding: 0 0px;
			}
			
			em {
    			color: #008FD6;
    			font-style: normal;
   				font-weight: 700;
    			padding-right: 5px;
			}
			font span{
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
    		
    		#lista-criterios ul li
    		{
    			font-weight: bold;
    			list-style-type: upper-alpha;
    			margin: 0 0 0 47px;
				padding:0 0px;				
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
    		span{
    			font-weight: normal;
    			color: #2f2f2f;
    		}
    					
		</style>
	</head>
	
	<body>
		<div id="container">
	    	<%@ include file="/jsp/portal/caema/cabecalho.jsp"%>
	        
	        <!-- Content - Start -->
		        <div id="content">
		        <%@ include file="/jsp/portal/caema/cabecalhoInformacoes.jsp"%>
		        	<div id="ligacaoAguaEsgoto" class="serv-int" style="width:880px;">	
	    	    			
							<p>&nbsp;</p>
							<h3>
								Como pedir sua liga&ccedil;&atilde;o de &aacute;gua e/ou esgoto<span>&nbsp;</span>
							</h3>
							<br />
							<br />
							<p>Para obter os servi&ccedil;os de abastecimento de &aacute;gua tratada e os servi&ccedil;os de coleta de esgoto da CAEMA deve o interessado formalizar o pedido de liga&ccedil;&atilde;o predial de &aacute;gua e/ou liga&ccedil;&atilde;o de esgoto, junto &aacute; Loja de Atendimento da CAEMA do Munic&iacute;pio munido, preferencialmente, com uma fatura de &aacute;gua e esgoto do im&oacute;vel mais pr&oacute;ximo.</p>
							
							<p class="paragrafo">
								<em>Documenta&ccedil;&atilde;o exigida</em>
							</p>
							<ul class="lista">								
								<li>CPF/CNPJ e RG do solicitante.</li>
								<li>Comprovante do v&iacute;nculo de propriedade do solicitante com o im&oacute;vel.</li>
							</ul>
						
							<br />
							<br />
							<br />
								
							<div id="atualizacao" style="background-image: url(/gsan/imagens/portal/caema/general/ultima_atualizacao.png);background-repeat: no-repeat;">
									<span style="position: absolute; padding-top: 7px;"> Última atualiza&ccedil;&atilde;o (sexta, 20 de janeiro de 2012)</span>
							</div>							
							             
		       	</div><!-- Content - End -->
	       </div>
	    	 <%@ include file="/jsp/portal/caema/rodape.jsp"%>
	  	</div><!-- Container - End -->       
	</body>
</html:html>