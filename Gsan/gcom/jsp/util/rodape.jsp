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

          		<td align="right">Vers&atilde;o: 10.2.10.2.5p - Versão Obter Débito (${applicationScope.versaoTipo}) 19/10/2015 - 8:55:43 </td>
        	</tr>
      	</table>
		</td>
  	</tr>
</table>
</logic:notPresent>