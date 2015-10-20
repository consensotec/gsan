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
		        	<div id="negociadebitos" class="serv-int" style="width:880px;">	
	    	    			
							<p>&nbsp;</p>
						
	        				<h3>
								Parcelamento de D&eacute;bito<span>&nbsp;</span>
							</h3>
							<br />
							<br />
							
							<p class="paragrafo"><em><b>Conceito:</b></em></p>
							<p>
								Acordo realizado entre a CAEMA e o cliente  para o pagamento de d&eacute;bito em parcelas,
								incorporadas nas Notas Fiscais/ Faturas vencidas, com assinatura de Termo de Confiss&atilde;o de D&iacute;vida
								e Contrato de Parcelamento, conforme condi&ccedil;&otilde;es estabelecidas em Norma pr&oacute;pria da CAEMA.
							</p>
							<p class="paragrafo">
								<em>Requisitos e Condi&ccedil;&otilde;es</em>
							</p>
							<p>
								O parcelamento de d&eacute;bito est&aacute; condicionado à apresenta&ccedil;&atilde;o de documentos que comprovem que o
								solicitante tenha os seguintes v&iacute;nculos com o im&oacute;vel:
							</p>
							<p>&nbsp;</p>
							<p><em><b>Propriet&aacute;rio:</b></em> Escritura ou contrato de compra e venda, Carn&ecirc; de IPTU (&uacute;ltimo 
							exerc&iacute;cio findo) ou documento, com firma reconhecida, que comprove a propriedade do im&oacute;vel.</p>
							<p>&nbsp;</p>
							<p><em><b>Inquilino ou ocupante do im&oacute;vel:</b></em> Contrato de aluguel (observado o prazo de vig&ecirc;ncia)
							ou procuração do propriet&aacute;rio, com firma reconhecida e com finalida de espec&iacute;fica. Neste caso, o n&uacute;mero de parcelas n&atilde;o
							poder&aacute; exceder a vig&ecirc;ncia do prazo do Contrato de aluguel, exceto  quando autorizado por escrito pelo propriet&aacute;rio, 
							devendo este documento estar com firma reconhecida. Toda negocia&ccedil;&atilde;o feita pelo inquilino ou pelo ocupante, a CAEMA dever&aacute; 
							informar expressamente ao propriet&aacute;rio, ap&oacute;s a efetiva&ccedil;&atilde;o do Termo de Confiss&atilde;o de D&iacute;vida e Contrato de Parcelamento
							e Termo de Confiss&atilde;o de D&iacute;vida e Contrato de Reparcelamento.
							</p>
							<p>&nbsp;</p>
							<p>
							<p><em><b>S&iacute;ndico:</b></em>Ata da Elei&ccedil;&atilde;o ou Posse ou autoriza&ccedil;&atilde;o por escrito de 51% 
							(cinquenta e um porcento) dos cond&ocirc;minos;
							</p>
							<p>&nbsp;</p>
							<p><em><b>Outras situa&ccedil;&otilde;es:</b></em> Procura&ccedil;&atilde;o do propriet&aacute;rio ou respons&aacute;vel
							 jur&iacute;dico, com firma reconhecida e finalidade espec&iacute;fica, escritura ou contrato de compra e venda ou documento similar;
							</p>
							<p>&nbsp;</p>
							<p><em><b>Pessoas jur&iacute;dicas:</b></em> Contrato social e documento de propriedade do im&oacute;vel. No caso de im&oacute;veis
							 alugados observar o item Inquilino ou ocupante do im&oacute;vel.
							</p>
							<br />
							<br />
							
							<p align="left">&nbsp;</p>
							<p align="left">&nbsp;</p>
					
							<font><span style="font-size: 11px;">Fonte CAEMA/ NORMA DE PARCELAMENTO DE DÉBITO/ NOR: 03.03/02</span></font>
						
							<p align="left">&nbsp;</p>
							<p align="left">&nbsp;</p>
							
							<br />
							<br />
							<br />
							<br />
							
							<div id="atualizacao" style="background-image: url(/gsan/imagens/portal/caema/general/ultima_atualizacao.png);background-repeat: no-repeat;">
									<span style="position: absolute; padding-top: 7px;"> &Uacute;ltima atualiza&ccedil;&atilde;o ( quinta, 19 de Janeiro de 2012 )</span>
							</div>					
							             
		       	</div><!-- negociadebitos - End -->
	       </div><!-- Content - End -->
	    	 <%@ include file="/jsp/portal/caema/rodape.jsp"%>
	  	</div><!-- Container - End -->       
	</body>
</html:html>