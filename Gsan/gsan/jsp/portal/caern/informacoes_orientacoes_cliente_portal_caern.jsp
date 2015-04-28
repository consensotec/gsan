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
								Principais d&uacute;vidas dos consumidores<span>&nbsp;</span>
							</h3>
							
							<br />
							<br />
								
							<p class="paragrafo">
								<em>Como solicito uma liga&ccedil;&atilde;o nova de &aacute;gua?</em>
							</p>
							
							<ul class="lista">								
								<li>A solicita&ccedil;&atilde;o de liga&ccedil;&atilde;o nova de &aacute;gua poder&aacute; ser feita atrav&eacute;s do escrit&oacute;rio distrital que atende o seu bairro ou no balc&atilde;o da Caern das Centrais do Cidad&atilde;o.</li>
								<li>O pedido dever&aacute; ser feito pelo propriet&aacute;rio do im&oacute;vel, portando carteira de identidade e CPF, escritura p&uacute;blica ou particular, reconhecida em cart&oacute;rio e o mesmo n&atilde;o pode ter pend&ecirc;ncia de d&eacute;bito vencido em outros im&oacute;veis j&aacute; atendidos pela Caern.</li>
							</ul>
						
						
							<br />
							
							<p class="paragrafo">
								<em>Como solicito mudan&ccedil;a de titularidade da conta de &aacute;gua?</em>
							</p>
							
							<ul class="lista">								
								<li>Esse atendimento poder&aacute; ser feito em qualquer balc&atilde;o da Caern das Centrais do Cidad&atilde;o ou escrit&oacute;rio distrital que atende o seu bairro.</li>
								<li>O propriet&aacute;rio dever&aacute; portar documentos pessoais como carteira de identidade e CPF, recibo de compra e venda do im&oacute;vel e escritura p&uacute;blica ou particular reconhecida em cart&oacute;rio e dever&aacute; ser paga uma taxa de servi&ccedil;os.</li>
							</ul>
							
							<br />
							
							<p class="paragrafo">
								<em>Como posso solicitar o corte de &aacute;gua?</em>
							</p>
							
							<ul class="lista">								
								<li>Compare&ccedil;a ao escrit&oacute;rio da Caern ou balc&atilde;o das Centrais do Cidad&atilde;o, portando a leitura do hidrômetro (apenas a sequ&ecirc;ncia num&eacute;rica preta), carteira de identidade e CPF.</li>
								<li>O im&oacute;vel n&atilde;o poder&aacute; ter d&eacute;bitos vencidos, nem a vencer. Al&eacute;m disso dever&aacute; ser paga uma taxa pelo corte. O solicitante deve ser o titular, propriet&aacute;rio ou respons&aacute;vel pelo im&oacute;vel.</li>
							</ul>
							
							<br />
							
							<p class="paragrafo">
								<em>Se estou em d&eacute;bito com a Caern, como devo proceder?</em>
							</p>
							
							<ul class="lista">								
								<li>Compare&ccedil;a ao balc&atilde;o da Caern nas Centrais do Cidad&atilde;o ou a um dos nossos escrit&oacute;rios com a fatura, carteira de identidade e CPF, para verificar a situa&ccedil;&atilde;o atual do im&oacute;vel.</li>
							</ul>							

							<br />
							
							<p class="paragrafo">
								<em>Moro em condom&iacute;nio e gostaria de saber o que &eacute; medi&ccedil;&atilde;o individualizada?</em>
							</p>
							
							<ul class="lista">								
								<li>A Caern disponibiliza medi&ccedil;&atilde;o individual do consumo em edif&iacute;cios e condom&iacute;nios horizontais.</li>
								<li>Para adequar seu condom&iacute;nio a este processo, no qual cada unidade residencial paga a sua pr&oacute;pria conta de &aacute;gua/esgoto &eacute; necess&aacute;rio que o s&iacute;ndico convoque uma assembl&eacute;ia geral extraordin&aacute;ria. O pedido da individualiza&ccedil;&atilde;o dever&aacute; ser aprovado em ata do condom&iacute;nio.</li>
								<li>Ap&oacute;s a elabora&ccedil;&atilde;o da ata, o s&iacute;ndico deve procurar a Caern para demais orienta&ccedil;&oacute;es.</li>
								<li>A medi&ccedil;&atilde;o individualizada torna mais justa a cobran&ccedil;a dos servi&ccedil;os prestados. A conta ser&aacute; emitida tomando por base o consumo registrado no medidor individual, somando o rateio do consumo das &aacute;reas comuns. Tamb&eacute;m sobre o consumo da &aacute;gua &eacute; cobrado o percentual relativo a coleta de esgoto (quando existente). Para medi&ccedil;&atilde;o individualizada, o condom&iacute;nio n&atilde;o poder&aacute; ter d&eacute;bitos com a Caern.</li>
							</ul>							
														

							<br />
							
							<p class="paragrafo">
								<em>Como posso informar sobre um vazamento na rua?</em>
							</p>
							
							<ul class="lista">								
								<li>A informa&ccedil;&atilde;o poder&aacute; ser repassada ao 0800-840195, nos balc&otilde;es da Caern das Centrais do Cidad&atilde;o ou em nossos escrit&oacute;rios distritais. O cliente ao entrar em contato deve informar o endere&ccedil;o e ponto de refer&ecirc;ncia mais pr&oacute;ximo ao local do vazamento.</li>
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