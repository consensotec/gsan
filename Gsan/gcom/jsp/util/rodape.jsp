<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<logic:notPresent scope="session" name="origemGIS">

<table width="770" cellspacing="4" cellpadding="0">
	<tr>
    	<td>
      	<table width="100%" cellpadding="2" class="footer">
        	<tr>
          		<td  align="left"> 
	          		<logic:present scope="application" name="versaoDataBase"> 
					Banco: ${applicationScope.versaoDataBase}
					</logic:present>  
					<logic:notPresent scope="application" name="versaoDataBase"> 
					PMSS
					</logic:notPresent>
				</td>

          		<td align="right">Vers&atilde;o: 10.2.12.2.5p (${applicationScope.versaoTipo}) 03/02/2016 - 16:50:56 </td>
        	</tr>
      	</table>
		</td>
  	</tr>
</table>

<!-- <script>
	caerninit();
</script> -->

</logic:notPresent>