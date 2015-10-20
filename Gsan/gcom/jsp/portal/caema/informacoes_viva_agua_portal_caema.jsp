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
		        	<div id="vivaAgua" class="serv-int" style="width:880px;">	
	    	    			
							<p>&nbsp;</p>
							<h3>
								Viva &Aacute;gua<span>&nbsp;</span>
							</h3>
							<br />
							<br />
						
							<p class="paragrafo"><em><b>DOS CRIT&Eacute;RIOS PARA A ISEN&Ccedil;&Atilde;O</b></em></p>
						
							<p style="text-align:justify;">
								Todo consumidor, propriet&aacute;rio ou n&atilde;o, poder&aacute; participar do VIVA &Aacute;GUA desde que o im&oacute;vel em que reside, esteja ligado aos Sistemas de
								Abastecimento de &aacute;gua operados pela CAEMA, cadastrado na categoria residencial, e que, se enquadre nas seguintes condi&ccedil;&otilde;es:
							</p>
							<p>&nbsp;</p>
							<p style="text-align:justify;">
								I - Propriet&aacute;rio ou inquilino e morador de im&oacute;vel abastecido pela CAEMA, cadastrado na categoria residencial e que apresente para cada economia, o consumo de &aacute;gua de at&eacute; 25m&sup3;/m&ecirc;s (vinte e cinco metros c&uacute;bicos por m&ecirc;s), conforme estabelecido no Artigo 84 do Decreto Estadual n&ordm; 11.060/89.
							</p>
							
							<p>&nbsp;</p>
							<p class="paragrafo"><em><b>PAR&Aacute;GRAFO PRIMEIRO</b></em></p>
														
							<p style="text-align:justify;">
								Em conformidade com o Artigo 3&ordm; do Regulamento de Servi&ccedil;os da Companhia de Saneamento Ambiental do Maranh&atilde;o &minus; CAEMA, &Prime;Define-se como usu&aacute;rio e/ou consumidor toda pessoa f&iacute;sica ou jur&iacute;dica &minus; propriet&aacute;rio ou inquilino &minus; respons&aacute;vel pela ocupa&ccedil;&atilde;o ou utiliza&ccedil;&atilde;o dos pr&eacute;dios servidos pelas redes p&uacute;blicas de &aacute;gua e/ou esgotos sanit&aacute;rios&Prime;.
							</p>
							
							<p>&nbsp;</p>
							<p class="paragrafo"><em><b>PAR&Aacute;GRAFO SEGUNDO</b></em></p>
							
							<p style="text-align:justify;">
								O consumidor postulante do VIVA &Aacute;GUA dever&aacute;, atender, tamb&eacute;m, &agrave;s seguintes condi&ccedil;&otilde;es:
							</p>
							<br />
							<div id="lista-criterios" style="background-color: #e9e9e9;">
								<ul>
									<li>
										<p style="text-align: justify;" ><span>a) O im&oacute;vel para o qual estiver sendo solicitada a inclus&atilde;o dever&aacute; ter a CAEMA como fonte exclusiva de abastecimento de &aacute;gua e na situa&ccedil;&atilde;o de &Prime;LIGA&Ccedil;&Atilde;O DE &Aacute;GUA&Prime;.</span></p>
										<br />
									</li>
									<li>
										<p style="text-align: justify;" class="paragrafo"><span>b) O consumidor dever&aacute; apresentar no ato da formaliza&ccedil;&atilde;o de seu enquadramento no VIVA &Aacute;GUA, junto &agrave; CAEMA, documentos que comprovem seu nome completo, endere&ccedil;o atualizado, RG, CPF e a documenta&ccedil;&atilde;o referente a propriedade, cess&atilde;o de uso ou loca&ccedil;&atilde;o, juntado o respectivo contrato de loca&ccedil;&atilde;o para tanto;</span></p>
										<br />
									</li>
									<li>
										<p style="text-align: justify;"><span>c) Caso o consumidor n&atilde;o disponha da documenta&ccedil;&atilde;o exigida acima, por falta de regulariza&ccedil;&atilde;o do im&oacute;vel, dever&aacute; apresentar qualquer outro documento que comprove sua habilita&ccedil;&atilde;o nestes moldes;</span></p>
										<br />
									</li>
									<li>
										<p style="text-align: justify;"><span>d) Para liga&ccedil;&otilde;es com a situa&ccedil;&atilde;o de &aacute;gua cortada, o consumidor postulante dever&aacute; solicitar, obrigatoriamente, a religa&ccedil;&atilde;o da mesma devendo requerer sua nova inclus&atilde;o no programa.</span></p>
										<br />
									</li>
								</ul>								
							</div>
							
							<p class="paragrafo"><em><b>CL&Aacute;USULA TERCEIRA &minus; DAS OBRIGA&Ccedil;&Otilde;ES DOS PART&Iacute;CIPES</b></em></p>
							
							<br />
							<p style="text-align:justify;">
								II - Compete ao CONVENENTE:
							</p>
							<br />
							<div id="lista-criterios" style="background-color: #e9e9e9;">
								<ul>
									<li>
										<p style="text-align: justify;" ><span>j) O presente Conv&ecirc;nio obrigar&aacute; a Convenente em utilizar o cadastro &Uacute;NICO para programas sociais do Governo Federal, conf. Decreto Lei n&ordm; 3.877/2001,  pena de suspens&atilde;o dos benef&iacute;cios, com arrimo inclusive no Art. 65 e SS c/c Art. 77, da Lei Federal n&ordm; 8.666/93.</span></p>
										<br />
									</li>
								</ul>								
							</div>
							<br />
							
						
							<p align="left">&nbsp;</p>
							<p align="left">&nbsp;</p>
							
							<br />
							<br />
												
							             
		       	</div><!-- negociadebitos - End -->
	       </div><!-- Content - End -->
	    	 <%@ include file="/jsp/portal/caema/rodape.jsp"%>
	  	</div><!-- Container - End -->       
	</body>
</html:html>