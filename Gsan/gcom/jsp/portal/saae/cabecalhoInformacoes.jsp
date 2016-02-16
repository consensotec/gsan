<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<style>
			.btn-voltar-informacoes {
    			left: 0px;
    			position: absolute;
    			top: -10px;
			}
			#barra-informacoes{
				 background: url("/gsan/imagens/portal/saae/general/barra-informacoes.gif") no-repeat scroll 0 0 transparent;
				 margin: 50px 0 0 38px;
				 position: relative;
				 width: 865px;
				 float: left;
				 height: 40px
			}
		</style>
	</head>
	<body>
		<logic:present scope="request" name="voltarInformacoes">
			<div id="voltar-informacoes" class="serv-int btn-voltar-informacoes" >
				<a href="exibirInformacoesPortalSaaeAction.do" title="Voltar Para o Menu de Informa��es">
		   			<img src="/gsan/imagens/portal/saae/general/btn-voltar-informacoes.gif" alt="Voltar Para o Menu de Informa��es" />
				</a>
			</div>
		</logic:present>
		<div id="barra-informacoes">
			<a href="exibirServicosPortalSaaeAction.do?menu=sim" title="Sair"><img src="/gsan/imagens/portal/saae/general/btn-sair.png" alt="Sair" style="float: right;" /></a>
		</div>
		
		<!-- Bot�o download Adobe Reader - Start -->
		<a href="http://get.adobe.com/br/reader/" title="Fa�a o download do Adobe Reader" class="adobe-reader" target="_blank"><img src="/gsan/imagens/portal/saae/general/adobe-reader.gif" alt="Download do Adobe Reader" /></a>
		<!-- Bot�o download Adobe Reader - End -->
		
		
	</body>
</html>