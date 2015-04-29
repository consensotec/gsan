<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gsan.util.Util" %>
<%@ page import="gsan.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<html:javascript staticJavascript="false"  formName="FiltrarComandoNegativacaoActionForm" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
<script>
<!--

function validaForm(){
   	var form = document.FiltrarComandoNegativacaoActionForm;
	form.submit();

}
-->
</script>
</head>

<body leftmargin="5" topmargin="5">
<div id="formDiv">
<html:form
	action="exibirFiltrarComandoNegativacaoAction"    
	name="FiltrarComandoNegativacaoActionForm"
  	type="gsan.gui.cobranca.spcserasa.FiltrarComandoNegativacaoActionForm"
  	method="post">

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="150" valign="top" class="leftcoltext">
			<div align="center">
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>

			<%@ include file="/jsp/util/informacoes_usuario.jsp"%>

			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>

			<%@ include file="/jsp/util/mensagens.jsp"%>

			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			</div>
		</td>
		<td width="613" valign="top" class="centercoltext">
			<!--In�cio Tabela Reference a P�gina��o da Tela de Processo-->

              <table>
                <tr> 
                  <td></td>
                </tr>
              </table>
              <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr> 
                  <td width="11"><img border="0" src="<bean:message key='caminho.imagens'/>parahead_left.gif"/></td>
                  <td class="parabg">Filtrar Comandos de Negativa&ccedil;&atilde;o - Tipo do Comando </td>

                  <td width="11" valign="top"><img border="0" src="<bean:message key='caminho.imagens'/>parahead_right.gif"/></td>
                </tr>
              </table> 
              <!--Fim Tabela Reference a P�gina��o da Tela de Processo-->
              <p>&nbsp;</p>
              <table width="100%" border="0" dwcopytype="CopyTableRow">
                <tr> 
                  <td colspan="3">Para filtrar o(s) comando(s) de negativa&ccedil;&atilde;o, informe o tipo do comando :</td>
                </tr>
                
                <tr><td></td></tr>
                <tr><td></td></tr>
                <tr><td></td></tr>
                                
                <tr>
				    <td width="20%"><strong>Tipo do Comando:<font color="#FF0000">*</font></strong></td>
				    <td>
				        <html:radio property="tipoComando" value="1" tabindex="1" styleId="criterio"/>
				        <label for="criterio"><strong>Por Crit�rio</strong></label>
				    </td>
				
				</tr> 
				
				<tr>
				    <td></td>
				    <td>
				        <html:radio property="tipoComando" value="2" tabindex="2" styleId="matricula"/>
				        <label for="matricula"><strong>Por Matr�cula de Im�veis</strong></label>
				    </td>
				</tr>
				
				 <tr>
				    <td></td>
				    <td>
				        <logic:equal name="ICNEGATIVACAOPORGUIA" value="1" scope="request">
				            <html:radio property="tipoComando" value="3" tabindex="3" styleId="guia"/>
				            <label for="guia"><strong>Por Guia de Pagamento de Cliente</strong></label>
				        </logic:equal>
				    </td>
				</tr>
				<tr>
				    <td> </td>
				    <td style="padding:10px;">
				         <strong><font color="#FF0000">*</font></strong>
				          Campos obrigat&oacute;rios
				    </td>
				</tr>
				
                <tr> 
                  <td height="17" colspan="3"><strong><font color="#FF0000"> </font></strong><strong><font color="#FF0000"> 
                    </font></strong><strong><font color="#FF0000"> </font></strong></td>

                </tr>
                <tr> 
                  <td height="17" colspan="3"><strong><font color="#FF0000"> </font></strong><strong><font color="#FF0000"> 
                    </font></strong><strong><font color="#FF0000"> </font></strong></td>
                </tr>
                <tr> 
                  <td height="17" colspan="3"><strong><font color="#FF0000"> </font></strong><strong><font color="#FF0000"> 
                    </font></strong><strong><font color="#FF0000"> </font></strong></td>
                </tr>

                <tr> 
                  <td height="17" colspan="3"><strong><font color="#FF0000"> </font></strong><strong><font color="#FF0000"> 
                    </font></strong><strong><font color="#FF0000"> </font></strong> 
                    <div align="right"> </div>
                    <div align="right"> 
                      <input name="botaoAvancar" type="button" class="bottonRightCol" value="Avan�ar" onClick="javascript:validaForm();"/>
                      <img src="<bean:message key='caminho.imagens'/>avancar.gif" width="15" height="24"> </div></td>
                </tr>
              </table>
			
            
          <p>&nbsp;</p></tr>
		<tr>
		  <td colspan="3">
		</td>
	</tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>
</div>
</body>
</html:form>

<%@ include file="/jsp/util/telaespera.jsp"%>

<script>

</script>



</html:html>