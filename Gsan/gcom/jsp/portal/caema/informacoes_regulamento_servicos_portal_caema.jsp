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
						
			.lista ul li {
				list-style: url("/gsan/imagens/portal/caema/general/marcador.gif");
				margin: 0 0 0 15px;
				list-style-position: outside;
				padding: 5px;
			}
			
			.lista li {
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
    		.paragrafo {line-height: 30px;}
    		
    		#lista-criterios ul li
    		{
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
    		.imagem{border: 5px solid #c9c9c9; margin-right: 15px;}
    		.pontilhada{border-bottom: 1px dashed #c9c9c9;}  
    		span{
    			font-weight: normal;
    			color: #2f2f2f;
    		} 
    		<logic:present name="arquivoNaoEncontrado" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					showMessage('O arquivo não foi encontrado.');
				});
			</script>
			</logic:present>					
		</style>
	</head>
	
	<body>
		<div id="container">
	    	<%@ include file="/jsp/portal/caema/cabecalho.jsp"%>
	        
	        <!-- Content - Start -->
		     <div id="content">
		        <%@ include file="/jsp/portal/caema/cabecalhoInformacoes.jsp"%>
		        	<div id="regulamentoServicos" class="serv-int" style="width:880px;">	
	    	    			
							<p>&nbsp;</p>
						
	        				<h3>
								Regulamento de servi&ccedil;os<span>&nbsp;</span>
							</h3>	
							
							<br />
							<br />
							
							<p><em>
								<a href="http://www.caema.ma.gov.br/portalcaema/index.php?option=com_docman&task=cat_view&gid=57&Itemid=83" target="_blank" title="Regulamento de Serviço">
								<b>1. Regulamento de Serviço - ARSEP</b>
								</a>
							</em></p>
							
							<br />
														
							<!-- Início dos links para download -->
							<div id="links" class="subTopicos">
								<logic:present name="regulamento" scope="request">
										<div>
											<a href="exibirConsultarRegulamentoServicosPortalCaemaAction.do?download=regulamento" target="_blank">
												<img src="/gsan/imagens/portal/caema/general/botao_pdf.gif" style="float: left"/>
											</a>
											<em style="height: 34px; width: 300px;"><%=request.getAttribute("labelRegulamento")%></em>
										</div>
								</logic:present>
							</div>
							
							<br />
							<br />
							<br />
							<br />
							
							<div id="atualizacao" style="background-image: url(/gsan/imagens/portal/caema/general/ultima_atualizacao.png);background-repeat: no-repeat;">
									<span style="position: absolute; padding-top: 7px;"> Última atualização ( sexta, 20 de Janeiro de 2012 )</span>
							</div>	
						
							        
		       		</div><!-- Pagina - End -->
		    
	       	</div><!-- content -->
	       	
			<%@ include file="/jsp/portal/caema/rodape.jsp"%>
			
	  	</div><!-- Container - End -->       
	</body>
</html:html>