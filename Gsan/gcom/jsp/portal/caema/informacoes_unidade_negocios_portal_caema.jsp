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
		<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>style.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>internal.css" type="text/css">
	<style type="text/css">
					
			.lista li {
				list-style: url("/gsan/imagens/portal/general/marcador.gif");
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
		        	<div id="unidadeNegocios" class="serv-int" style="width:880px;">	
	    	    			
							<p>&nbsp;</p>
							<h3>
								Unidade Neg�cios<span>&nbsp;</span>
							</h3>
	        				<!-- <h3>
								Negocia��o de D�bitos<span>&nbsp;</span>
							</h3> -->
							<br />
							<br />
							<p>Parcelamento de d�bito</p>
							<p class="paragrafo"><em><b>Condom�nio:</b></em></p>
							<p>
								O solicitante deve ser o s�ndico ou representante legal do condom�nio e ter, no m�nimo, 18 anos.
							</p>
							<p class="paragrafo">
								<em>Quais os documentos necess�rios?</em>
							</p>
							<ul class="lista">
								<li>RG, CPF e telefone de contato do s�ndico;</li>
								<li>Conta de servi�os de �gua e/ou esgotos;</li>
								<li>C�pia do cart�o do CNPJ do condom�nio, se houver;</li>
								<li>C�pia da ata de elei��o do s�ndico com registro em cart�rio ou o Contrato Social da Administradora;</li>
								<li>Quando solicitado por representante legal, apresentar procura��o original (com firma reconhecida ou com a apresenta��o do RG (original) de quem a assinou).</li>
								<li>Para condom�nios sem CNPJ ou sem s�ndicos preencher o termo autoriza��o de parcelamento.</li>
							</ul>
							
							<p class="paragrafo">
								As c�pias devem ser acompanhadas do original e ser�o retidas para arquivo da Compesa.
								No momento da solicita��o ocorrer� o preenchimento e assinatura do Termo de Acordo para Parcelamento de D�bito.								
							</p>
							<br />
							<p><em><b>Residencial</b></em></p>
							<p class="paragrafo">
							<p class="paragrafo"><em><b>Quais os documentos necess�rios?</b></em></p>
							
							<p><em><b>Se propriet�rio:</b></em> RG e CPF, telefone para contato.</p>
							<p>&nbsp;</p>
							<p><em><b>Se inquilino:</b></em> RG e CPF, telefone para contato, c�pia do contrato de loca��o com firma reconhecida*
								Observa��o: a quantidade de parcelas depender� do per�odo do t�rmino do contrato de loca��o.
							</p>
							<p>&nbsp;</p>
							<p>
							<p><em><b>Se representante: </b></em> RG, CPF, e c�pia dos documentos do im�vel, acrescidos de procura��o com firma reconhecida (ou com a apresenta��o do RG original de quem a assinou).
							</p>
							<p>&nbsp;</p>
							<p><em><b>Se emancipado:</b></em> Apresentar um documento que comprove esta condi��o.
									Conta de servi�os de �gua e/ou esgotos. No momento da solicita��o ocorrer� o preenchimento e assinatura do Termo de Acordo para Parcelamento de D�bito.
							</p>
							<br />
							<br />
							<p><em><b>Comercial e industrial</b></em></p>
							<p class="paragrafo">
							C�pia do Contrato Social e �ltima altera��o contratual;
							<ul class="lista">								
								<li>C�pia do Cart�o de CNPJ;</li>
								<li>Representante com procura��o com firma reconhecida*.</li>
							</ul>
								<br />
								
								Para os acordos de parcelamento formalizados por representante, ser�o considerados os dados constantes da procura��o para elabora��o do termo.
								No momento da solicita��o ocorrer� o preenchimento e assinatura do Termo de Acordo para Parcelamento de D�bito.
							</p>
							
							<p align="left">&nbsp;</p>
							<p align="left">&nbsp;</p>
					
							<font><span style="font-size: 11px;">Fonte:COMPESA/DAC/01-10-10 (RD 11/2008)</span></font>
						
							<p align="left">&nbsp;</p>
							<p align="left">&nbsp;</p>
							
							<br />
							<br />
							<br />
							<br />
							
							<div id="atualizacao" style="background-image: url(/gsan/imagens/portal/general/ultima_atualizacao.png);background-repeat: no-repeat;">
									<span style="position: absolute; padding-top: 7px;"> �ltima atualiza��o ( quarta, 15 de junho de 2011 )</span>
							</div>					
							             
		       	</div><!-- negociadebitos - End -->
	       </div><!-- Content - End -->
	    	 <%@ include file="/jsp/portal/rodape.jsp"%>
	  	</div><!-- Container - End -->       
	</body>
</html:html>