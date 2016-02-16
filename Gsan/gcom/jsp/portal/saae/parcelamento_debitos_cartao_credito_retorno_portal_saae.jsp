<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.cobranca.bean.ContaValoresHelper" isELIgnored="false"%>
<%@ page import="gcom.cobranca.bean.GuiaPagamentoValoresHelper" isELIgnored="false"%>
<%@ page import="gcom.util.ConstantesSistema" isELIgnored="false"%>
<%@ page import="java.util.Collection" isELIgnored="false"%>
<%@ page import="gcom.cobranca.bean.OpcoesParcelamentoHelper" isELIgnored="false"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
	<head>
		<title>Saae | Serviços</title>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-1.4.2.min.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.blockUI.js"></script>
		<link rel="stylesheet" href="<bean:message key="caminho.portalsaae.css"/>style.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portalsaae.css"/>internal2.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portalsaae.css"/>jquery.theme.css" type="text/css">

		<style type="text/css">
			.titulo {
				margin: 25px 25px 25px 0px;
				padding: 5px !important;
				background-color: #F5F6F6;
				border-radius: 10px;
				text-align: center;
				font-size: 14px;
			}

			.link {
				display: block;
				margin: 0px auto;
				width: 200px;
				color: #008FD6;
				font-weight: bold;
				font-size: 16px;
				text-align: center;
				text-decoration: underline;
				margin-bottom: 20px;
			}
		</style>
	</head>
	<body>
		<div id="container">
	    	<%@ include file="/jsp/portal/saae/cabecalho.jsp"%>

			<div id="content">
				<%@ include file="/jsp/portal/saae/cabecalhoImovel.jsp"%>

				<div id="parc-debito" class="serv-int">
	            	<h3>Retorno parcelamento de débitos por cartão de crédito<span>&nbsp;</span></h3>

	                <p class="titulo">
						<c:choose>
							<c:when test="${empty messagemFalha}">
								<strong>Pagamento do parcelamento realizado com sucesso.</strong>
							</c:when>
							<c:otherwise>
								<span style="color: red;">
				                	<strong>Falha no processamento do pagamento do parcelamento.</strong>
				                	<br>
									${messagemFalha}
								</span>
							</c:otherwise>
						</c:choose>
	                </p>

					<div style="width: 100%; float:left">
						<c:if test="${empty messagemFalha}">
		            		<a href="/gsan/gerarRelatorioDocumentosParcelamentoPortalSaaeAction.do" class="link">Emitir Termo</a>
		            	</c:if>
		            	<a href="/gsan/" class="link">Retornar ao Gsan</a>
	            	</div>
	            </div>
	        </div>

			<%@ include file="/jsp/portal/saae/rodape.jsp"%>
		</div>
	</body>
</html:html>
