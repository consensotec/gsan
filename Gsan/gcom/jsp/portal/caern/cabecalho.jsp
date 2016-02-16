<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html>
<head>
	<script language="JavaScript">
		(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
		(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
		m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
		})(window,document,'script','//www.google-analytics.com/analytics.js','ga');

		ga('create', 'UA-60594060-13', 'auto');
		ga('send', 'pageview');
	</script>	
</head>

<body>
   	<!-- Início Cabeçalho -->
     <div id="top">
     	<a href="exibirServicosPortalCaernAction.do?menu=sim" title="Caern">
        	<img src="<bean:message key="caminho.portalcaern.imagens"/>general/loja-virtual.png" alt="Mosaico" style="width:auto;float:none;"/>
     	</a>
     </div>
     <!-- Fim Cabeçalho -->
</body>
</html>