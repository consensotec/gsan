<link REL="SHORTCUT ICON" HREF="<bean:message key="caminho.imagens"/>icoGSAN.ico"> 

    <script language="JavaScript">
    	(function() {
    	  var chaveAnalytics;

    	  <%if("CAER".equals(getServletContext().getAttribute("nomeEmpresa"))){ %>
    	  chaveAnalytics = 'UA-60594060-8';
    	  <% } else if ("SAAE".equals(getServletContext().getAttribute("nomeEmpresa"))){ %>
    	  chaveAnalytics = 'UA-60594060-10';
    	  <% } else if ("CAEMA".equals(getServletContext().getAttribute("nomeEmpresa"))){ %>
    	  chaveAnalytics = 'UA-60594060-7';
    	  <% } else if ("COSAMA".equals(getServletContext().getAttribute("nomeEmpresa"))){ %>
    	  chaveAnalytics = 'UA-60594060-9';
    	  <% } %>

    	  if (chaveAnalytics) {
    	    (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
    	    (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
    	    m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
    	    })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

    	    ga('create', chaveAnalytics, 'auto');
    	    ga('send', 'pageview');
    	  }
    	}());
    </script>  

	<!-- CAERN 
    <script language="JavaScript" src="javascript/functions.js"></script>
    <link rel="stylesheet" href="css/style.css" type="text/css">
    CAERN -->    

<logic:notPresent scope="session" name="origemGIS">
	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td height="0" valign="top" class="topstrip">
			<table width="100%" height="0" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td height="0" valign="bottom">
						<img src="${applicationScope.logoMarca}">
					</td>
 

					<td width="35%" align="center">
						<br>

						<marquee bgcolor="#CBE5FE" title="titulo" valign="top" loop="true"
							scrollamount="4" behavior="scroll" direction="left">
							<font color="black">
								<strong>${requestScope.mensagemAviso} </strong>
							</font>
						</marquee>

				<%	if (!"IPAD".equals(getServletContext().getAttribute("nomeEmpresa"))){ %>
						<a href="http://conhecimento.consensotec.com.br/" style="text-decoration: none;" target="_blank">
							<img src="<bean:message key="caminho.imagens"/>ajuda2.gif" border="0">
						</a>
				<%	} else {	%>
						<img src="<bean:message key="caminho.imagens"/>ajuda2.gif" border="0">
				<%	}	%>
					</td>

					<td align="right" valign="bottom">
						<img src="<bean:message key="caminho.imagens"/>logo_menu_superior.gif" border="0">
					</td>
				</tr>
			</table>

			<table cellpadding="0" cellspacing="0" border="0" class="layerCaminhoMenu">
				<tr>
					<logic:notEmpty name="caminhoMenuFuncionalidade" scope="session">
						<td>
					  		<a href="${sessionScope.caminhoHelpFuncionalidade}" target="_blank"
					  			alt="${sessionScope.caminhoMenuFuncionalidade}" title="Clique para acessar a ajuda da funcionalidade">
					  			<span style="position: relative; top: 2px;">
									<img src="<bean:message key="caminho.imagens"/>help.png" border="0">
								</span>${sessionScope.caminhoMenuFuncionalidade}
 							</a>
					</logic:notEmpty>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</logic:notPresent>