<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
	<head>
		<title>CAERN | Informa&ccedil;&otilde;es</title>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-1.4.2.min.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<link rel="stylesheet" href="<bean:message key="caminho.portalcaern.css"/>style.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portalcaern.css"/>internal.css" type="text/css">
	<style type="text/css">
						
			.lista-condicoes ul li {
				list-style: url("/gsan/imagens/portal/caern/general/marcador.gif");
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
	    	<%@ include file="/jsp/portal/caern/cabecalho.jsp"%>
	        
	        <!-- Content - Start -->
		        <div id="content">
		        <%@ include file="/jsp/portal/caern/cabecalhoInformacoes.jsp"%>
		        	<div id="ligacaoAguaEsgoto" class="serv-int" style="width:880px;">	
	    	    			
							<p>&nbsp;</p>
							<h3>
								D&eacute;bito autom&aacute;tico em conta<span>&nbsp;</span>
							</h3>
							
							<br />
							<br />
							<br />
							
							<p>Ganhe tempo, segurança e comodidade colocando sua conta de &aacute;gua em d&eacute;bito
								autom&aacute;tico</p>
							
							<br />	
							<p class="paragrafo">
								<em>Você tem as seguintes vantagens</em>
							</p>
							<ul class="lista">								
								<li>&Eacute; totalmente gratuito.</li>
								<li>Evita que você enfrente filas no banco.</li>
								<li>Mant&eacute;m seu pagamento sempre em dia.</li>
								<li>Evita multas por atraso.</li>
								<li>Evita eventuais cortes de &aacute;gua por falta de pagamento.</li>
								<li>Pode ser cancelado sempre que você achar conveniente.</li>
							</ul>
						
						
							<br />
							
							<p>Com o d&eacute;bito autom&aacute;tico, sua conta de &aacute;gua continua sendo enviada
							normalmente para sua casa. Assim, você poder&aacute; conferir seu consumo, o valor
							e a data de vencimento.</p>
							
							<br />
							
							<p>Para cadastrar sua conta em d&eacute;bito autom&aacute;tico, preencha a ficha de
							autoriza&ccedil;&atilde;o e entregue em sua agência banc&aacute;ria.
							Lembre-se de levar a conta da CAERN para realizar o cadastro.
							Alguns bancos disponibilizam o serviço de d&eacute;bito autom&aacute;tico no pr&oacute;prio site.
							Veja se &eacute; o caso de seu banco.</p>
							
							<br />
								
							<p class="paragrafo">
								<em>Bancos Conveniados</em>
							</p>
							<ul class="lista">								
								<li>Banco do Brasil</li>
								<li>Caixa Econômica Federal</li>
								<li>Bradesco</li>
							</ul>
						
						
							<br />
							             
		       	</div><!-- Content - End -->
	       </div>
	    	 <%@ include file="/jsp/portal/caern/rodape.jsp"%>
	  	</div><!-- Container - End -->       
	</body>
</html:html>