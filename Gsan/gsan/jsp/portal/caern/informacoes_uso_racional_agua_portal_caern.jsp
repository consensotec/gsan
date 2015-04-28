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
						
			.lista li {
				list-style: url("/gsan/imagens/portal/caern/general/marcador.gif");
				margin: 0 0 0 15px;
				list-style-position: outside;
				padding: 0px;
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
								Uso Racional da &Aacute;gua<span>&nbsp;</span>
							</h3>
							
							<br />
							<br />
								
							<p class="paragrafo">
								<em>Como verificar se a leitura e o consumo da conta est&atilde;o corretos</em>
							</p>
							
							<ul class="lista">								
								<li>Localize em sua conta o campo 'Leitura Atual'.</li>
								<li>Verifique a leitura hoje em seu hidr&ocirc;metro (caso tenha dificuldade na visualiza&ccedil;&atilde;o dos números, 
								molhe o visor para desagregar poeira externa ou got&iacute;culas de vapor d´&aacute;gua condensadas na parte interna do instrumento de medi&ccedil;&atilde;o).</li>
								<li>Compare as leituras. Se a leitura do hidr&ocirc;metro estiver igual ou maior que a informada na conta, a informa&ccedil;&atilde;o est&aacute; correta, 
								ou seja, sua conta est&aacute; certa. Caso contr&aacute;rio, procure o escrit&oacute;rio do seu bairro levando estas informa&ccedil;&otilde;es e 
								os seus documentos para poss&iacute;vel atualiza&ccedil;&atilde;o de dados cadastrais e an&aacute;lise da fatura.</li>
							</ul>
						
						
							<br />
							
							<p class="paragrafo">
								<em>Se a leitura estiver correta e o consumo da minha conta estiver al&eacute;m do habitual, como devo proceder?</em>
							</p>
							
							<ul class="lista">								
								<li>Verificar a exist&ecirc;ncia de vazamentos.</li>
								<li>É poss&iacute;vel detectar poss&iacute;veis vazamentos embutidos</li>
							</ul>
							
							<br />
							
							<p class="paragrafo">
								<em>Quais os testes que podem auxiliar na identifica&ccedil;&atilde;o dos vazamentos?</em>
							</p>
							
							<ul class="lista">								
								<li>Mantenha aberto o registro do cavalete.</li>
								<li>Feche bem as torneiras da casa e n&atilde;o utilize a descarga do vaso sanit&aacute;rio.</li>
								<li>Feche completamente as torneiras de b&oacute;ia das caixas, n&atilde;o permitindo a entrada de &aacute;gua.</li>
								<li>Observe a leitura do hidr&ocirc;metro e, ap&oacute;s meia hora, verifique se ela sofreu alguma altera&ccedil;&atilde;o.</li>
								<li>Em caso afirmativo, h&aacute; vazamento no ramal alimentado, diretamente pela rede.</li>
								<li>Compare&ccedil;a ao escrit&oacute;rio distrital do seu bairro com os seus documentos pessoais e a conta de &aacute;gua para receber informa&ccedil;&otilde;es de t&eacute;cnicos da Caern sobre como retirar o vazamento.</li>
							</ul>
							
							<br />
							
							<p class="paragrafo">
								<em>Como posso diminuir o meu consumo de &aacute;gua?</em>
							</p>
							
							<ul class="lista">								
								<li>A consci&ecirc;ncia no uso racional da &aacute;gua diminui o desperd&iacute;cio e consequentemente a redu&ccedil;&atilde;o do consumo.</li>
								<li>Oriente sua fam&iacute;lia a executar atividades como escovar os dentes, lavar os pratos ou fazer a barba, fechando a torneira sempre que n&atilde;o for utilizar a &aacute;gua.</li>
								<li>Ao lavar roupa na m&aacute;quina, utilize a carga m&aacute;xima permitida, evitando assim o desperd&iacute;cio de &aacute;gua, sab&atilde;o e energia el&eacute;trica.</li>
								<li>Troque as lavagens de cal&ccedil;ada e carro com mangueira pelo uso do balde.</li>
							</ul>							
														
							<br />
							<br />
							<br />
								
							<div id="atualizacao" style="background-image: url(/gsan/imagens/portal/caern/general/ultima_atualizacao.png);background-repeat: no-repeat;">
									<span style="position: absolute; padding-top: 7px;"> &Uacute;ltima atualiza&ccedil;&atilde;o (sexta, 17 de julho de 2013)</span>
							</div>							
							             
		       	</div><!-- Content - End -->
	       </div>
	    	 <%@ include file="/jsp/portal/caern/rodape.jsp"%>
	  	</div><!-- Container - End -->       
	</body>
</html:html>