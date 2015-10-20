<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
	<head>
		<title>CAEMA</title>
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
		</style>
	</head>
	
	<body>
		<div id="container">
	    	<%@ include file="/jsp/portal/caema/cabecalho.jsp"%>
	        
	        <!-- Content - Start -->
		   	<div id="content">
		        <%@ include file="/jsp/portal/caema/cabecalhoInformacoes.jsp"%>
		        <html:form action="/exibirInformacoesPortalCaemaAction" type="gcom.gui.portal.caema.ExibirInformacoesPortalCaemaActionForm">		        		
		        	<div id="pagina" class="serv-int" style="width:880px;">
		        		<p>&nbsp;</p>						
	        			<h3>
							Normas de Instala��o e Individualiza��o Predial<span>&nbsp;</span>
						</h3>						
						<br />
						<br />
						<div id="medicaoIndividualizada" class="lista pontilhada">
							<p class="paragrafo"><em><b>Medi��o Individualizada</b></em></p>
							<br />
							
							<img src="/gsan/imagens/portal/caema/general/medicao_individializada.gif" class="imagem" style="float: left"/>
							<p style="text-align: justify">
								<span>A Medi��o Individualizada de �gua em Edif�cios / Condom�nios consiste na  instala��o de um hidr�metro em cada apartamento, de maneira que seja poss�vel medir o seu consumo individual.</span>
							</p>
							<br />
							<p style="text-align: justify">
								<span>Implantado pela COMPESA h� 16 anos, em 1994, os resultados s�o satisfat�rios, especialmente no que se refere �:</span>
							</p>
							<br />
							<ul style="margin-left: 210px;">
								<li><span>Redu��o do consumo de �gua nos edif�cios;</span></li>
								<li><span>Diminui��o do desperd�cio e da inadimpl�ncia e</span></li>
								<li><span>Aumento da satisfa��o dos clientes.</span></li>
							</ul>
							
							<p style="text-align: justify">
								<span>Neste modelo de Medi��o de Consumo Individualizado, desenvolvido e implantado pela Ger�ncia de Micromedi��o da COMPESA, o hidr�metro deixa de ser um mero medidor divis�rio para ser um medidor de consumo real.</span>
							</p>
							<br />
							<br />
							<p style="text-align: justify">
								<span>A conta no final do m�s � uma l�gica justa: quem gastar mais vai pagar mais. O medidor que abastece o pr�dio � mantido para apurar o consumo total do edif�cio.</span>
								 <span>A conta de �gua e esgoto passa a ser estabelecida atrav�s do consumo individual de cada apartamento.</span>
							</p>
							<br />
							<br />
							<p style="text-align: justify">
								<span>No decorrer destes 16 anos, mais de 64.880 hidr�metros foram instalados em 3.594 edif�cios / condom�nios.</span>
							</p>
							<br />
							<br />
							<p style="text-align: justify">
								<span>Pernambuco � o Estado brasileiro pioneiro a adotar em larga escala o processo de quantifica��o de conta de �gua atrav�s da medi��o individualizada por apartamento.</span>
							</p>
							<br />
							<br />
							<br />
							<div id="links" style="width: 525px">
								<logic:present name="arquivoLeiNormaMedicao" scope="request">
									<a href="exibirInformacoesPortalCaemaAction.do?modo=verLeiIndividualizacao" target="_blank"><img src="/gsan/imagens/portal/caema/general/botao_pdf.gif" style="float: left"/></a>
									<em><bean:write property="descrissaoLeiIndividualizacao" name="ExibirInformacoesPortalCaemaActionForm" /></em>
									<br />
									<br />
									<br />
								</logic:present>
								
								<logic:present name="arquivoNormaCO" scope="request">
									<a href="exibirInformacoesPortalCaemaAction.do?modo=verNormaCO" target="_blank"><img src="/gsan/imagens/portal/caema/general/botao_pdf.gif" style="float: left"/></a>
									<em><bean:write property="descrissaoNormaCO" name="ExibirInformacoesPortalCaemaActionForm" /></em>
									<br />
									<br />
									<br />
								</logic:present>
								
								<logic:present name="arquivoNormaCM" scope="request">
									<a href="exibirInformacoesPortalCaemaAction.do?modo=verNormaCM" target="_blank"><img src="/gsan/imagens/portal/caema/general/botao_pdf.gif" style="float: left"/></a>
									<em><bean:write property="descrissaoNormaCM" name="ExibirInformacoesPortalCaemaActionForm" /></em>
								</logic:present>
							</div>
							<br />
							<br />
							<br />
							<br />
							<font><span style="font-size: 11px;">Fonte:COMPESA/GMI/JAN/2011</span></font>
							<br />
							<br />
							<div id="atualizacao" style="background-image: url(/gsan/imagens/portal/caema/general/ultima_atualizacao.png);background-repeat: no-repeat;">
									<span style="position: absolute; padding-top: 7px;"> �ltima atualiza��o (segunda, 17 de Janeiro de 2011)</span>
							</div>
							<br />
							<br />
							<br />
						</div><!-- Medicao Individualizada -->
						
						<div id="instalacaoMicromedidor" class="lista pontilhada">
							<br />
							<br />
							<em>Instala��o Micromedidor</em>
							<br />
							<br />
							<br />
							
							<img src="/gsan/imagens/portal/caema/general/instalacao_micromedidor.gif" class="imagem" style="float: left"/>
							<p style="text-align: justify">
								<span>Para efetivar a Medi��o Individualizada em Edif�cios/Condom�nios antigos e novos de acordo com a Norma em vigor na Compesa, � preciso preencher Termo de compromisso (modelo fornecido pela Compesa, na Loja de Atendimento ao Cliente)</span> 
								<span>ao qual dever� ser anexada a Ata da reuni�o de condom�nio, onde deve constar na referida Ata a aceita��o integral das condi��es para implanta��o da medi��o individualizada, aprovada pela maioria dos moradores presentes na reuni�o, ou seja, 50% (cinq�enta por cento) + 1 (um) do total de cond�minos.</span>
							</p>
							<br />
							<ul style="margin-left: 225px;">
								<li><span>O condom�nio deve estar adimplente e o respons�vel (S�ndico ou seu representante legal) dever� apresentar, na Loja de Atendimento ao Cliente, toda documenta��o para an�lise.</span></li>
								<li><span>Edif�cio/Condom�nio Novo ? � a edifica��o cujo respectivo projeto de instala��o hidr�ulica foi protocolado para an�lise no �rg�o competente de cada Munic�pio, ap�s o dia 22/06/2004, data de in�cio da vig�ncia da Lei Estadual 12.609;</span></li>
								<li><span>Edif�cio/Condom�nio Antigo ? � a edifica��o cujo respectivo projeto de instala��o hidr�ulica foi protocolado para an�lise no �rg�o competente de cada Munic�pio, antes do dia 22/06/2004, data de in�cio da vig�ncia da Lei Estadual 12.609;</span> </li>
							</ul>
							<br />
							<p style="text-align: justify">
								<span>Destacamos que os custos das modifica��es a serem realizadas nas instala��es hidr�ulicas dos edif�cios/condom�nios antigos e novos s�o de responsabilidade do condom�nio. 
								Os custos das adapta��es variam em fun��o das caracter�sticas das instala��es hidr�ulicas de cada pr�dio, podendo ser facilitado para os condom�nios que disponham do Projeto hidr�ulico original. 
								Algumas condi��es t�cnicas precisam ser obedecidas para que seja poss�vel fazer a individualiza��o: o apartamento deve ser alimentado por um �nico ponto onde ser� instalado o medidor; 
								� vedada a utiliza��o de v�lvulas de descargas ou similar (por problemas t�cnicos); e n�o pode haver interliga��o das tubula��es de �gua entre os diversos apartamentos do edif�cio.</span>
							</p>
							<br />
							<br />
							<br />
							<br />
							<!-- <font><span style="font-size: 11px;">Fonte:COMPESA/DAC/JUN/2007</span></font> -->
							<br />
							<br />
							<div id="atualizacao" style="background-image: url(/gsan/imagens/portal/caema/general/ultima_atualizacao.png);background-repeat: no-repeat;">
									<span style="position: absolute; padding-top: 7px;"> �ltima atualiza��o (quarta, 24 de Novembro de 2010)</span>
							</div>
							<br />
							<br />
							<br />
						</div><!-- Instalacao Micromedidor -->
						
						<div id="vantagensMicromedidor" class="pontilhada">
							<br />
							<br />
							<em>Vantagens do Micromedidor</em>
							<br />
							<br />
							<br />
							
							<ul id="listapontosvista" class="lista"><!-- listapontosvista - Inicio -->
								<li><b><em>Do ponto de vista do consumidor</em></b>
									<br />
									<br />
									<p><span>As principais vantagens da medi��o individualizada de �gua nos apartamentos de edif�cios multifamiliares, s�o:</span></p>
									<ul id="listavantconsumidor">
										<li><span>Pagamento proporcional ao consumo, ou seja, um apartamento que s� tenha um consumidor n�o pagar� em forma semelhante ao que possua 6, 8 ou 10 pessoas;</span></li>
										<li><span>O cliente n�o pagar� pelo desperd�cio dos outros;</span></li>
										<li><span>Um cliente bom pagador jamais ter� a sua �gua cortada pela irresponsabilidade dos maus pagadores;</span></li>
										<li><span>Redu��o do valor do pagamento da conta de �gua, em alguns casos de at� 25%;</span></li>
										<li><span>Redu��o do consumo do edif�cio em at� 30%;</span></li>
										<li><span>Possibilidade de localizar vazamentos internos nos apartamentos, que, �s vezes, levam meses e at� anos para serem identificados;</span></li>
										<li><span>Maior satisfa��o do cliente, j� que ele passa a controlar diretamente a sua conta de �gua.</span></li>
									</ul>
									<br />
									<br />
								</li>
								<li><b><em>Do ponto de vista da concession�ria</em></b>
									<br />
									<br />
									<span>Os principais benef�cios das empresas concession�rias de �gua, s�o:</span>
									
									<ul>
										<li><span>Redu��o do �ndice de inadimpl�ncia, pois somente � cortada a �gua dos maus pagadores, e na pr�tica esses passam a ser bons pagadores;</span></li>
										<li><span>Redu��o do consumo de �gua, podendo atingir, em m�dia, 25%;</span></li>
										<li><span>Redu��o do n�mero de reclama��es de consumo, refletindo-se numa melhor imagem perante a popula��o;</span></li>
										<li><span>Aumento do faturamento em torno de 20% devido ao efeito da tarifa progressiva.</span></li>
									</ul>
									<br />
									<br />
								</li>
								
								<img src="/gsan/imagens/portal/caema/general/vantagens_micromedidor.gif" class="imagem" style="float: left"/>
								<li><b><em>Do ponto de vista dos construtores</em></b>
									<br />
									<br />
									<span>Para os construtores, o principal benef�cio comentado, �:</span>
									
									<ul style="list-style-position: outside; margin-left: 223px;">
										<li><span>Maior facilidade de venda dos apartamentos com medi��o individualizada de �gua.</span></li>
										<li><span>Menor custo na obra, no que se refere �s instala��es hidr�ulicas.</span></li>
									</ul>
									<br />
									<br />
									<br />
									
								</li>
								<li><b><em>Aspecto Legal</em></b>
									<br />
									<br />
									<span>Lei Estadual 12.609 de 22/06/2004 do Governo do Estado de Pernambuco.</span>
								</li>
							</ul><!-- listapontosvista - Fim -->
							

							<br />
							<br />
							<br />
							<font><span style="font-size: 11px;">Fonte:COMPESA/GMI/JAN/2011</span></font>
							<br />
							<br />
							<div id="atualizacao" style="background-image: url(/gsan/imagens/portal/caema/general/ultima_atualizacao.png);background-repeat: no-repeat;">
									<span style="position: absolute; padding-top: 7px;"> �ltima atualiza��o (segunda, 17 de Janeiro de 2011)</span>
							</div>
							<br />
							<br />
							<br />
						</div><!-- Vantagens Micromedidor -->
							        
		       		</div><!-- Pagina - End -->
		       		</html:form>
	       	</div><!-- content -->
	       	
			<%@ include file="/jsp/portal/caema/rodape.jsp"%>
			
	  	</div><!-- Container - End -->       
	</body>
</html:html>