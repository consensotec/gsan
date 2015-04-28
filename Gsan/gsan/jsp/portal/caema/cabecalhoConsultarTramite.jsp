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
		<script type="text/javascript">
			$(document).ready(function(){
				var matricula = $('#matricula').html();
				var length = matricula.length;
				$('#matricula').html(matricula.substr(0, length - 1) + '.' + matricula.substr(length - 1, 1)); 
			});
		</script>
		
		<style type="text/css">
		
			.btn-voltar {
				left: 38px;
				position: absolute;	
				top: 3%;			
			}	
					
		</style>
	</head>
	<body>
	
		
	
		<div id="barra-servicos">
			<h2>Serviços</h2>
		    <h3>Bem-vindo(a) ${ExibirServicosPortalCaemaActionForm.nomeUsuario}</h3>
		    <h4><label style="font-size: 13px;">Matrícula: </label><span id="matricula">${ExibirServicosPortalCaemaActionForm.matricula}</span></h4>
		    <a href="exibirServicosPortalCaemaAction.do?menu=sim" title="Sair"><img src="/gsan/imagens/portal/caema/general/btn-sair.png" alt="Sair" /></a>
		</div>
		
		<logic:present scope="request" name="voltarServicos">
			<div id="seg-via-conta" class="serv-int" style="margin: 0px;" align="top">
				<a href="exibirAcompanhamentoRAPortalCaemaAction.do" title="Voltar" class="btn-voltar">
			    	<img src="/gsan/imagens/portal/caema/general/btn-voltar.png" alt="Voltar" />
			    </a>
			</div>
		</logic:present>	
		
		<!-- Botão download Adobe Reader - Start -->
		<a href="http://get.adobe.com/br/reader/" title="Faça o download do Adobe Reader" class="adobe-reader" target="_blank"><img src="/gsan/imagens/portal/caema/general/adobe-reader.gif" alt="Download do Adobe Reader" /></a>
		<!-- Botão download Adobe Reader - End -->	
		
	</body>
</html>