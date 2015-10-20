<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="InserirComandoNegativacaoActionForm" dynamicJavascript="false" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript">
	function selecionar() {
		var formulario = document.forms[0]; 

		if(formulario.idNegativador.value == -1){
		  alert("Informe Negativador");
		  return;
		}

    	var tipo = formulario.tipo.value;
    	
 		if(tipo === '1') {
    		formulario.action = 'exibirInserirComandoNegativacaoPorCriterioAction.do'
    		formulario.submit();
    		return;
		}

		if(tipo === '2') {
    		formulario.action = 'exibirInserirComandoNegativacaoMatriculaImovelAction.do?limparForm=OK&menu=sim'
    		formulario.submit();
    		return;
		}

		if(tipo === '3') {
    		formulario.action = 'exibirInserirComandoNegativacaoPorGuiaPagamentoAction.do'
    		formulario.submit();
    		return;
		}
    }
    
</script>

</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">

<div id="formDiv">

<html:form action="/exibirInserirComandoNegativacaoTipoComandoAction"
	name="InserirComandoNegativacaoActionForm"
	type="gcom.gui.spcserasa.InserirComandoNegativacaoActionForm"
	method="post"
	onsubmit="return validateInserirComandoNegativacaoActionForm(this);">


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
	  <table height="100%"><tr><td></td></tr></table>

      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Inserir Comando de Negativação</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>

      <table width="100%" border="0">
      <tr>
      	<td colspan="2">Para inserir o comando de negativação, informe o tipo do comando:</td>
      </tr>
      <tr>
        <td><strong>Negativador:<font color="#FF0000">*</font></strong></td>
        <td colspan="2">
			<html:select property="idNegativador" tabindex="7">
				<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
				<logic:present name="colecaoNegativador">
					<html:options collection="colecaoNegativador" labelProperty="cliente.nome" property="id"/>
				</logic:present>
			</html:select>
		</td>
      </tr>

      <tr>
      	<td width="20%"><strong>Tipo do Comando:<font color="#FF0000">*</font></strong></td>
      	<td>
      		<html:radio property="tipo" value="1" tabindex="1" styleId="criterio"/>
			<label for="criterio"><strong>Por Critério</strong></label>
		</td>
      </tr>

      <tr>
      	<td></td>
		<td>
			<html:radio property="tipo" value="2" tabindex="2" styleId="matricula"/>
			<label for="matricula"><strong>Por Matrícula de Imóveis</strong></label>
		</td>
      </tr>

      <tr>
      	<td></td>
		<td>
			<logic:equal name="ICNEGATIVACAOPORGUIA" value="1" scope="request">
				<html:radio property="tipo" value="3" tabindex="3" styleId="guia"/>
				<label for="guia"><strong>Por Guia de Pagamento de Cliente</strong></label>
			</logic:equal>
		</td>
      </tr>

	  <tr>
	  	<td>&nbsp;</td>
	    <td style="padding:10px;">
	    	<strong><font color="#FF0000">*</font></strong>
	    	Campos obrigat&oacute;rios
	    </td>
	  </tr>

	  <tr>
	    <td colspan="2" align="right">
		  <table border="0" width="100%">
			<tr>
				<td align="right"  width="60%">&nbsp;</td>
				<td align="right"  width="5%">	
				  <gsan:controleAcessoBotao name="Button3222" value="Avançar" onclick="selecionar();" url="inserirComandoNegativacaoTipoComandoAction.do"/>			
				</td>
				<td align="right" width="2%">
					<img src="imagens/avancar.gif" width="15"  border="0"/>
				</td>
				<td align="right"  width="33%">&nbsp;</td>
			</tr>
		  </table>
		</td>
	  </tr>
      </table>
      <p>&nbsp;</p>
	</td>
  </tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</div>

<%@ include file="/jsp/util/telaespera.jsp"%>

</body>
</html:html>
