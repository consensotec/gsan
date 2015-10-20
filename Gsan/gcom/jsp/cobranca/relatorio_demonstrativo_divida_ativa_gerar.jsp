<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"  formName="GerarRelatorioDemonstrativoDividaAtivaActionForm" />

<script LANGUAGE="JavaScript">
	
	function validarForm(form){
		if(validateGerarRelatorioDemonstrativoDividaAtivaActionForm(form)){
			botaoAvancarTelaEspera('/gsan/gerarRelatorioDemonstrativoDividaAtivaAction.do');
		}
	}
	
	function limpaForm(form){
		form.indicadorIntra[2].checked = true;
		form.anoMesDemonstrativo.value = "";
	}
	
</script>
</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">

<div id="formDiv">
<html:form action="/exibirGerarRelatorioDemonstrativoDividaAtivaAction"
	name="GerarRelatorioDemonstrativoDividaAtivaActionForm"
	type="gcom.gui.cobranca.GerarRelatorioDemonstrativoDividaAtivaActionForm"
	onsubmit="return validateGerarRelatorioDemonstrativoDividaAtivaActionForm(this);">

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="140" valign="top" class="leftcoltext">
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

        <table height="100%">
        <tr>
          <td></td>
        </tr>
      	</table>

      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Gerar Relatório Demonstrativo Dívida Ativa</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>

      <table width="100%" border="0">
	      <tr>
	      	<td colspan="3">Para gerar o relatório, informe os dados abaixo: </td>
	      </tr>	
		
		<tr>
			<td>
				<strong>Mês/Ano do Demonstrativo :<font color="FF0000">*</font></strong>
			</td>
			<td>
				<html:text property="anoMesDemonstrativo" tabindex="3" maxlength="7" 
						  onkeyup="mascaraAnoMes(this,event);" onkeypress="return isCampoNumerico(event);" size="5" />
			</td>
			<td>&nbsp;</td>
		</tr>
		
		<tr>
			<td>
				<strong>Indicador de Intra:<font color="FF0000">*</font></strong>
			</td>
			<td>
				<html:radio property="indicadorIntra" value="1" tabindex="1" />
					<strong>Sim</strong> 
				<html:radio	property="indicadorIntra" value="2" tabindex="2" />
					<strong>Não</strong>
				<html:radio	property="indicadorIntra" value="3" tabindex="3" />
					<strong>Todos</strong>
			</td>
			<td>&nbsp;</td>
		</tr>
		  
	  	 <tr>
			<td colspan="3">&nbsp;</td>
		 </tr>
		 <tr>
		   <td colspan="3" align="right">
		     <table border="0" width="100%">
			   <tr>
			   <td colspan="2">
					<input name="Submit22"
						class="bottonRightCol" 
						value="Limpar" 
						type="button"
						onclick="limpaForm(document.forms[0]);">
					<input type="button"
						onclick="javascript:window.location.href='/gsan/telaPrincipal.do'" 
						value="Cancelar" 
						class="bottonRightCol" 
						name="ButtonCancelar"> 			
				</td>
						
				<td align="right" width="50%">					
				 <input name="Button322222" type="button"
					class="bottonRightCol" value="Gerar"
					onClick="javascript:validarForm(document.forms[0]);" />
				</td>
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
