<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.util.Pagina, gcom.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<html:javascript staticJavascript="false"  formName="GerarArquivoEfdPisConfinsActionForm" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
    
function chamarGerar(){
  var form = document.forms[0];
	  if (validateGerarArquivoEfdPisConfinsActionForm(form)) {
		  botaoAvancarTelaEspera('/gsan/gerarArquivoEfdPisConfinsAction.do');
	  }
}
</script>
</head>

<body leftmargin="5" topmargin="5">
<div id="formDiv">
<html:form
  action="/gerarArquivoEfdPisConfinsAction.do"
  method="post"
  type="gcom.gui.faturamento.GerarArquivoEfdPisConfinsActionForm"
>
<!--  onsubmit="return validateGerarArquivoEfdPisConfinsActionForm(this);" -->
<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="130" valign="top" class="leftcoltext">
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
    
    <td width="625" valign="top" class="centercoltext">
      <table height="100%" border="0">
        <tr>
          <td></td>
        </tr>
      </table>
      
      <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Gerar Arquivo EFD-PIS/Confins</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      
      <p>&nbsp;</p>
	  
	  <table width="100%" border="0">
        <tr>
          <td width="100%" colspan=2>
	       	<table>
		      <tr>
		        <td width="505px">Para gerar o arquivo EFD-PIS/Confins, informe os dados abaixo:</td>
		      </tr>
		    </table>
          </td>
        </tr>
        
        <tr>
          <td><strong>Tipo de Registro:</strong></td>
          <td>
			<html:select property="tipoRegistro" multiple="true" >
 			  <html:option value="<%=""+ ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
		      <html:options collection="colecaoTipoRegistro" labelProperty="descricao" property="id"/>
		    </html:select>          	
          </td>
        </tr>
        
        <tr>
          <td><strong>Referencia:</strong><font color="#FF0000">*</font></td>
          <td>
          	<html:text property="mesAnoReferencia" size="7"  maxlength="7" 
          		onkeyup="mascaraAnoMes(this, event);" onkeypress="return isCampoNumerico(event)"/>&nbsp;mm/aaaa
          </td>
        </tr>
        <tr>
			<td width="40%"><strong>Totalizar por Munic&iacute;pio:</strong>
			</td>
			<td><strong> 
					<html:radio
						property="indicadorTotalizarMunicipio" value="<%= ""+ ConstantesSistema.SIM %>" /> Sim 
					<html:radio
						property="indicadorTotalizarMunicipio" value="<%= ""+ ConstantesSistema.NAO %>" /> N&atilde;o </strong>
			</td>
		</tr>
		<tr>
			<td width="40%"><strong>Tipo da Gera&ccedil;&atilde;o:</strong>
			</td>
			<td><strong> 
					<html:radio
						property="indicadorTipoGeracao" value="1" /> Op&ccedil;&atilde;o 1 
					<html:radio
						property="indicadorTipoGeracao" value="2" /> Op&ccedil;&atilde;o 2 </strong>
			</td>
		</tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        
        <tr>
        	<!--  -->
          <td colspan="2" class="style1">
			<input type="button" name="Button" class="bottonRightCol" value="Desfazer" onclick="window.location.href='<html:rewrite page="/exibirGerarArquivoEfdPisConfinsAction.do"/>'">
			<input name="Submit23" class="bottonRightCol" value="Cancelar" type="button" onclick="window.location.href='/gsan/telaPrincipal.do'">
          </td>
          	
          <td>
            <div align="right">
              <%--<gsan:controleAcessoBotao name="Button" value="Gerar" onclick="chamarGerar();" url="gerarIntegracaoContabilidadeCaernAction.do"/>--%>
               <input type="button" name="Button" class="bottonRightCol" value="Gerar" onClick="javascript:chamarGerar();"/> 
            </div>
          </td>
        </tr>
        <tr>
        	<td>
        		<p>&nbsp;</p>
        		<p>&nbsp;</p>
        		<p>&nbsp;</p>
        		<p>&nbsp;</p>
        		<p>&nbsp;</p>
        		<p>&nbsp;</p>
        		<p>&nbsp;</p>
        		<p>&nbsp;</p>
        		<p>&nbsp;</p>
        		<p>&nbsp;</p>
        		<p>&nbsp;</p>
        		<p>&nbsp;</p>
        		<p>&nbsp;</p>
        		<p>&nbsp;</p>
        		<p>&nbsp;</p>
        	</td>
        </tr>
      </table>      
	</td>
  </tr>
        
</table>

<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</div>
<%@ include file="/jsp/util/telaespera.jsp"%>
</body>
</html:html>