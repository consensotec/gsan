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

          		<td align="right">Vers&atilde;o: 10.1.04.2p (${applicationScope.versaoTipo}) 09/04/2015 - 15:41:17 </td>
        	</tr>
      	</table>
		</td>
  	</tr>
</table>
</logic:notPresent>