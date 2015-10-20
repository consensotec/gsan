<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.gui.portal.caern.ConsultarPagamentoFaturaPortalCaernHelper" isELIgnored="false"%>
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
	
	<body>
		<div id="container">
	    	<%@ include file="/jsp/portal/caern/cabecalho.jsp"%>
	        
	        <!-- Content - Start -->
		        <div id="content">
		        <%@ include file="/jsp/portal/caern/cabecalhoInformacoes.jsp"%>
		        	<div id="pagamentoFatura" class="serv-int" style="width:880px;">	
	    	    			
							<p>&nbsp;</p>
							<h3>
								Onde pagar sua fatura<span>&nbsp;</span>
							</h3>
							<br />
							<br />
							
							<p><em>
								<a href="http://www.caern.rn.gov.br/contentproducao/aplicacao/caern/servicos/gerados/locais_credenciados.asp" target="_blank" title="Locais de recebimento">
								<b>Locais para o recebimento de contas de &aacute;gua</b>
								</a>
							</em></p>
							
							<br />					
							
							<p>
							A Caern possui 1.700 pontos de recebimento de conta de &aacute;gua no Estado.
							Os clientes da Companhia podem pagar suas contas no Banco do Brasil, rede Pague F&aacute;cil, Bradesco, Crediserid&oacute; e estabelecimentos da Neoenergia
							</p>
							<p>&nbsp;</p>
							<table summary="Bancos credenciados" style="margin-top: 0px">
								<%int cont = 0;%>
								<tr>
									<td colspan="4">
									<table summary="Bancos credenciados">
										<logic:notEmpty name="helperPagamentoFatura" scope="request">
											<thead>
						                    	<tr>
						                        	<th width="30%">C&oacute;digo</th>
						                            <th width="50%">Nome</th>
						                        </tr>
						                    </thead>
												
											<tbody>
												<logic:iterate name="helperPagamentoFatura" type="ConsultarPagamentoFaturaPortalCaernHelper" 
													id="helper">
													 <%cont = cont + 1;
													if (cont % 2 == 0) {%>
													<tr class="tr-2" id="tr-2-2">
													<%} else {%>
													<tr class="tr-1" id="tr-1-2">
													<%}%>
														<td width="30%" style="border-right: 1px solid #C8C8C8;">
												           <bean:write name="helper" property="codigo" />
														</td>
														<td width="50%">
															<bean:write name="helper" property="nome" />
														</td>
													</tr>
												</logic:iterate>
											</tbody>										
										</logic:notEmpty>
									</table>
									</td>
								</tr>
							</table>
							
							
							             
		       	</div><!-- negociadebitos - End -->
	       </div><!-- Content - End -->
	    	 <%@ include file="/jsp/portal/caern/rodape.jsp"%>
	  	</div><!-- Container - End -->       
	</body>
</html:html>