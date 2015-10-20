<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">
<%@page import="gcom.arrecadacao.pagamento.Pagamento"%>
<%@page import="gcom.arrecadacao.pagamento.PagamentoHistorico"%>
<%@page import="gcom.cadastro.cliente.ClienteImovel"%>
<%@page import="gcom.cadastro.imovel.bean.ImovelMicromedicao"%>
<%@page import="gcom.util.Util" isELIgnored="false"%>
<%@ page import="gcom.util.ConstantesSistema" isELIgnored="false"%>

<html:html>
	<head>
		<title>CAERN | Servi&ccedil;os</title>
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
    		
    		<logic:present name="arquivoNaoEncontrado" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					showMessage('O arquivo não foi encontrado.');
				});
			</script>
		</logic:present>
    					
		</style>
	</head>
<body onload="setarFoco('${requestScope.nomeCampo}');">
		<div id="container">
	    	<%@ include file="/jsp/portal/caern/cabecalho.jsp"%>
	        
	        <!-- Content - Start -->
		        <div id="content">
				<%@ include file="/jsp/portal/caern/cabecalhoImovel.jsp"%>
		        	<div id="consultarConsumoHistoricoAgua" class="serv-int" style="width:880px;">	
	    	    			
							<p>&nbsp;</p>
							<h3>
								Consultar Consumo<span>&nbsp;</span>
							</h3>
							<br />
							<br />
							<p>&nbsp;</p>
							
							<div id="consumos" class="subTopicos" style="height: 300px; width: 660px;; overflow: auto;">
							
								<table summary="Consultar Consumo" style="margin-top: 0px">
									<%int cont = 0;%>
									<tr>
										<td colspan="4">
										<table summary="Consultar Consumo">
											<thead>
						                    	<tr>
						                        	<th width="15%">C&oacute;digo M&ecirc;s&#47;Ano</th>
													<th width="15%">Data da leitura</th>
						                            <th width="15%">Leitura</th>
						                            <th width="15%">Cons. Medido</th>
						                            <th width="15%">Cons. Faturado</th>
						                            <th width="14%">Cons. Médio</th>
						                            <th width="14%">Dias de Consumo</th>
						                        </tr>
						                    </thead>
	
											<logic:notEmpty name="imoveisMicromedicao" scope="request">	
												<tbody>
													<logic:iterate name="imoveisMicromedicao" type="ImovelMicromedicao" 
														id="imovelMicromedicao">
														 <%cont = cont + 1;
														if (cont % 2 == 0) {%>
														<tr class="tr-2" id="tr-2-2">
														<%} else {%>
														<tr class="tr-1" id="tr-1-2">
														<%}%>
															<bean:define name="imovelMicromedicao" property="consumoHistorico" id="consumoHistorico" ></bean:define>
															<bean:define name="imovelMicromedicao" property="medicaoHistorico" id="medicaoHistorico" ></bean:define>
															
															<td width="15%" style="border-right: 1px solid #C8C8C8;">
													          <bean:write name="consumoHistorico" property="mesAno" />	
															</td>
															<td width="15%">
																<logic:present name="imovelMicromedicao" property="medicaoHistorico">
															  		<bean:write name="medicaoHistorico" property="dataLeituraAtualFaturamento"  formatKey="date.format"/>
															  	</logic:present>
															</td>
															<td width="15%">
															<logic:present name="imovelMicromedicao" property="medicaoHistorico" >
															  <bean:write name="medicaoHistorico" property="leituraAtualFaturamento" />
															 </logic:present>
															</td>
															<td width="15%">
													          <bean:write name="medicaoHistorico" property="numeroConsumoMes" />	
															</td>
															<td width="15%">
													          <bean:write name="consumoHistorico" property="numeroConsumoFaturadoMes" />
															</td>
															<td width="14%">
													          <bean:write name="consumoHistorico" property="consumoMedio" />
															</td>
															<td width="14%">
													          <bean:write name="imovelMicromedicao" property="qtdDias" />
															</td>
														</tr>
													</logic:iterate>
												</tbody>										
											</logic:notEmpty>
										</table>
										</td>
									</tr>
								</table>
							</div>
							
				</div><!-- negociadebitos - End -->
	       	</div><!-- Content - End -->
	    	<%@ include file="/jsp/portal/caern/rodape.jsp"%>
	  	</div><!-- Container - End -->       
	</body>
</html:html>