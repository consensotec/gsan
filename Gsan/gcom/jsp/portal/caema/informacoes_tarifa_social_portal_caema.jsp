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
		        	<div id="tarifasocial" class="serv-int" style="width:880px;">	
	    	    			
							<p>&nbsp;</p>
						
	        				<h3>
								Tarifa social<span>&nbsp;</span>
							</h3>
						
							<br />
							<br />						
							<em>Cadastramento na tarifa social</em>
							<br />
							<br />	
							
							<p style="text-align:justify;">
								O governo do estado, conjuntamente com a Compesa, instituiu em novembro de 2003 a TARIFA SOCIAL com o objetivo de asistir as fam�lias de baixa renda.
								O cliente que se enquadrar e se cadastrar nos crit�rios e condi��es da Tarifa Social ser� beneficiado com um subs�dio de mai de 78% sobre o valor da Tarifa M�nima de �gua que � de R$ 24,52(vinte e quatro reais e cinquenta e dois centavos), passando a pagar R$ 5,25(cinco reais e vinte e cinco centavos) a partir do Extrato de Decis�o - ARPE - DOE - 19/06/2010.
							</p>
							
							<p>&nbsp;</p>						
							<em>Crit�rios:</em>
							<br />
							<br />	
							
							<p style="text-align:justify;">
								Ter� direito ao benef�cio da Tarifa Social o cliente que seja morador de im�vel abastecido pela Compesa, cadastrado na categoria Residencial n�o medido ou medido que apresente nos �ltimos 6(seis) meses, para cada economia, consumo m�dio de �gua -  de at� 10m�/m�s(dez metros c�bicos m�s) e consumo m�dio de energia el�trica - na categoria residencial monof�sico - de at� 80 kwh/m�s(oitenta quilowatts hora m�s) e que tamb�m se enquadre em um dos crit�rio abaixo estabelecidos:
							</p>
							<br />
							<div id="lista-criterios" style="background-color: #e9e9e9;">
								<ul>
									<li>
										<p style="text-align: justify;" ><span>Seja Benefici�rio de Programa de Prote��o Social do Governo Federal, descritos a seguir: Bolsa Familia, Programa de Erradica��o do Trabalho Infantil-PETI, Beneficio de Presta��o Continuada(Amparo Assistencial ao idoso e ao Deficiente) e Seguro Desemprego.</span></p>
										<p style="text-align: justify;" class="paragrafo"><span>Cliente beneficiado com  Seguro Desemprego dever� estar recebendo o valor de 1(um) salario m�nimo vigente, sendo o beneficio da Tarifa Social concedido pelo per�odo m�ximo de 5 (cinco) meses.</span></p>
										<br />
									</li>
									<li>
										<p style="text-align: justify;" class="paragrafo"><span>Tenha Renda Familiar Mensal Comprovada de at� 1 (um) sal�rio m�nimo vigente.</span></p>
										<p style="text-align: justify;" class="paragrafo"><span>Entende-se por Renda Familiar Mensal Comprovada o somat�rio dos rendimentos de todos os moradores do im�vel advindos de sal�rios e vantagens (exeto Sal�rio-Fam�lia), pens�es, aposentadorias, beneficios e outros.</span></p>
										<br />
									</li>
									<li>
										<p style="text-align: justify;"><span>Tenha Renda Familiar Mensal Declarada de at� 1 (um) sal�rio m�nimo vigente e seja morador de im�vel com �rea construida de at� 60m� (sessenta metros quadrados).</span></p>
									</li>
								</ul>								
							</div>
							<br />
							<p style="text-align:justify;">
								Entende-se por Renda Familiar Mensal Declarada o somat�rio dos recebimentos de todos os moradores do im�vel advindos de rendimentos de aut�nomos, presta��o ou vendas de bens e servi�os, alugueis e outros.
							</p>
							
							<p>&nbsp;</p>						
							<em>Condi��es</em>
							<br>&nbsp;</br>
							
							<ul class="lista-condicoes">
								<li><b>Para Cadastramento</b>
									<br />
									<br />
									<div id="lista" style="background-color: #e9e9e9;">
										<!-- Substituido por lista de crit�rios -->
										<ul style="" >
											<li>
												<span>O im�vel dever� estar na situa��o "Ligado", "Cortado" ou "Suprimido" de �gua;</span>
												<br />
												<br />
											</li>
											<li>
												<span>O cliente inadimplente que se enquadrar no crit�rios da Tarifa Social, ter� direito ao beneficio desde que se comprometa a liquidar ou negociar o d�bito, mediante Carta Cobran�a, que a Compesa enviar� ao seu im�vel;</span>
												<br />
												<br />
											</li>
											<li>
												<span>O d�bito de fatura do per�odo n�o prescricional ser� convertido retroativamente para o valor da Tarifa Social da �poca.</span>
												<br />
												<br />
											</li>
											<li>
												<span>As multas, juros e corre��es do d�bito convertido seram cancelados.</span>
												<br />
												<br />
											</li>
											<li>
												<span>O cliente dever� apresentar original e c�pia do Cadastro de Pessoa Fisica - CF, Carteira de Identidade, conta da Compesa, conta de Energia El�trica e demais documentos atualizados, conforme se enquadre.</span>
												<br />
											</li>
										</ul>
										
									</div>
									<br />
									<br />
									<p style="text-align: justify;" >
										<span>Caso o solicitante do benef�cio n�o seja proprietario do im�vel, ser� obrigat�rio anexar ao formul�rio de cadastramento c�pia do CPF e Carteira de Identidade do propriet�rio;</span>
									</p>
									<br />
									<br />
									<p style="color: #808080;"><b>Sendo Benefici�rio de Programa de Prote��o Social do Governo Federal:</b></p>
									<br />
									<ul>
										<li><span>Cart�o de Programa Social do Governo Federal.</span></li>
										<li><span>Comprovante de Pagamento do Benef�cio Social.</span></li>
									</ul>
									<br />
									<br />
									<p style="color: #808080;"><b>Tendo Renda Familiar Mensal Comprovada:</b></p>
									<br />
									<ul>
										<li><span>Recibo de Pagamento e Carteira profissional ou</span></li>
										<li><span>Contra-Cheque ou</span></li>
										<li><span>Demonstrativo de Pagamento.</span></li>
									</ul>
									<br />
									<br />
									<p style="color: #808080;"><b>Tendo Renda Familiar Mensal Declarada:</b></p>
									<br />
									<ul>
										<li><span>Imposto Predial e Territorial Urbano - IPTU ou</span></li>
										<li><span>Escritura com �rea construida do im�vel e</span></li>
										<li><span>Declarar Renda Familiar no Formul�rio "Tarifa Social-Cadastramento".</span></li>
									</ul>
									<br />
									<br />
								</li>
								
								<li><b>Para Implanta��o</b>
								<br />
								<br />
								
								<p style="text-align:justify;">
									<span>
										A implanta��o do cliente na Tarifa Social estar� condicionada a an�lise e aprova��o do cadastro pela Compesa.
										<br />
										<br />
										Os Clientes residentes em im�vel com mais de uma economia estar�o condicionados � aprova��o de todos os cadastros do referido im�vel.
									</span>
								</p>
								
								</li>
							</ul>
							
							
							
							<p align="left">&nbsp;</p>
							<p align="left">&nbsp;</p>
					
							<font><span style="font-size: 11px;">Fonte:COMPESA/DAC/01-10-10 (RD 11/2008)</span></font>
						
							<p align="left">&nbsp;</p>
							<p align="left">&nbsp;</p>
								
							<div id="atualizacao" style="background-image: url(/gsan/imagens/portal/caema/general/ultima_atualizacao.png);background-repeat: no-repeat;">
									<span style="position: absolute; padding-top: 7px;"> �ltima atualiza��o (segunda, 4 de Outubro de 2010)</span>
							</div>							
							             
		       	</div><!-- Content - End -->
	       </div>
	    	 <%@ include file="/jsp/portal/caema/rodape.jsp"%>
	  	</div><!-- Container - End -->       
	</body>
</html:html>