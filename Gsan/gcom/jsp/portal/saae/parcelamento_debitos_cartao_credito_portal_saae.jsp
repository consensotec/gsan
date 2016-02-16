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
	</head>
	<body>
		<div id="container">
	    	<%@ include file="/jsp/portal/saae/cabecalho.jsp"%>

			<div id="content">
				<%@ include file="/jsp/portal/saae/cabecalhoImovel.jsp"%>

				<div id="parc-debito" class="serv-int">
	            	<h3>Parcelamento de débitos por cartão de crédito<span>&nbsp;</span></h3>

	            	<form method="post" action="https://homologacao.pagador.com.br/pagador/Passthru.asp">
						<h5 style="color: #008FD6; float: none;">Bandeira do Cartão de Crédito</h5>

						<c:forEach items="${bandeiras}" var="bandeira">
							<input style="height: 48px; margin-right: 5px; float: none;" type="radio" id="pgto_${bandeira.id}" name="CodPagamento" value="${bandeira.codigoBandeira}"  tabindex="2" />
							<label style="cursor: pointer;" for="pgto_${bandeira.id}">
								<img src="${bandeira.imagemBase64}" alt="${bandeira.nome}" height="48">
							</label>
							<span style="float: none;">&nbsp;</span>
						</c:forEach>

		                <input type="hidden" name="Id_Loja" value="${idLoja}"/>
		                <input type="hidden" name="VendaId" value="${idPagamento}"/>
		                <input type="hidden" name="Nome" value="${nomeCliente}"/>
		                <input type="hidden" name="Cpf" value="${cpfCliente}"/>
		                <input type="hidden" name="Valor" value="${valor}"/>
		                <input type="hidden" name="PARCELAS" value="${numeroPrestacoes}"/>
		                <input type="hidden" name="TRANSACTIONTYPE" value="2"/><%/* 2 = Captura Automática */%>
		                <input type="hidden" name="TRANSACTIONCURRENCY" value="BRL"/>
		                <input type="hidden" name="TRANSACTIONCOUNTRY" value="BRA"/>

		                <p style="margin: 25px 25px 25px 0px; padding: 5px; background-color: #F5F6F6; border-radius: 10px;">
		                	<strong>Caro cliente, ao pressionar o botão confirmar, você será redirecionado ao ambiente seguro da Braspag para que sejam informados os dados do cartão de crédito. Após a confirmação do pagamento, você retornará a loja virtual para emissão do termo de parcelamento.</strong>
		                </p>

		                <input type="button" id="btn-confirmar" class="btn-confirmar" style="margin-left: 380px;" value=""  tabindex="4"/>
					</form>
	            </div>
	        </div>

			<%@ include file="/jsp/portal/saae/rodape.jsp"%>
		</div>

		<div id="message" style="display:none; cursor: default;"> 
	        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;"></h3> 
			<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
		</div>

		<script>
			function showMessage(message) {
				$('#message h3').text(message);
				$.blockUI({
					message : $('#message'),
					theme : true,
					title : 'Aviso'
				});

				$('.confirm').click(function() {
					$.unblockUI();
				});
			}

			$('#btn-confirmar').click(function () {
				if (!$("input[name='CodPagamento']:checked").val()) {
					showMessage('Selecione a Bandeira do Cartão de Crédito');
					return;
				}

				$('form').submit();
			});
		</script>
	</body>
</html:html>
