<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<html:javascript staticJavascript="false"  formName="InformarAssociacaoMotivoEncerramentoActionForm" />

<script>
   
   
	function limparForm() {
		var form = document.InformarAssociacaoMotivoEncerramentoActionForm;
	    form.cobrancaAcaoId.value = "";
		form.indicadorGeraPagamento.value = "";
	    form.indicadorGeraSucessor.value = "";
	    form.indicadorExibeDocumento.value = "";
	}
	
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			} else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				} else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
				}
			}
		}
	}

		function validarForm(){
		var form = document.forms[0];
		if(validateInformarMotivoEncerramentoAtendimentoActionForm(form)){
	       	submeterFormPadrao(form);
	    }
	}
	
	function adicionar(){
		var form = document.forms[0];
		
		form.action = 'informarAssociacaoMotivoEncerramentoAction.do';
		form.submit();
	}
</script>

</head>

<logic:notPresent name="fecharPopup">
	<body leftmargin="5" topmargin="5"
			onload="javascript:resizePageSemLink(570, 370);">
</logic:notPresent>
<logic:present name="fecharPopup">
	<body leftmargin="0" topmargin="0"
		onload="chamarReload('exibirInformarMotivoEncerramentoAtendimentoAction.do?retornarPopup=SIM');window.close()">
</logic:present>

<html:form action="/informarAssociacaoMotivoEncerramentoAction.do"
	name="InformarAssociacaoMotivoEncerramentoActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.InformarAssociacaoMotivoEncerramentoActionForm"
	method="post">

	<table width="550" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="520" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
				
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Informar Ação de Cobrança</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
	
      <p>&nbsp;</p>
      
      <table width="100%" border="0">
        <tr> 
          <td colspan="2">Preencha os campos para informar uma Ação de Cobrança:</td>
        </tr>
       </table>
        
       <table width="100%" border="0">

			<tr>
				<td><strong>Descri&ccedil;&atilde;o:</strong></td>
				<td colspan="2"><span class="style2"> <html:text
					property="descricaoMotivoEncerramento" size="40"  tabindex="2" readonly="true" disabled="true"/> </span></td>
				<td>&nbsp;</td>
			</tr>		
			
			<tr>
				<td width="30%"><strong>A&ccedil;&atilde;o de Cobran&ccedil;a:<font color="#FF0000">*</font></strong></td>
				<td><html:select property="cobrancaAcaoId" tabindex="3">
					<logic:notEmpty name="colecaoCobrancaAcao">
						<option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</option>
						<html:options collection="colecaoCobrancaAcao"
							labelProperty="descricaoCobrancaAcao" property="id" />
					</logic:notEmpty>
				</html:select></td>
			</tr>
				<tr>
					<td><strong>Gera Pagamento?<font color="#FF0000">*</font></strong></td>
					<td><strong> 
						<html:radio property="indicadorGeraPagamento" value="1"/> Sim 
						<html:radio property="indicadorGeraPagamento" value="2"/> N&atilde;o 
						</strong>
					</td>
				<tr>
					<td><strong>Gera Sucessor?<font color="#FF0000">*</font></strong></td>
					<td><strong> 
						<html:radio property="indicadorGeraSucessor" value="1"/> Sim 
						<html:radio property="indicadorGeraSucessor" value="2"/> N&atilde;o 
						</strong>
					</td>
				</tr>
				<tr>
					<td><strong>Exibe no Documento?<font color="#FF0000">*</font></strong></td>
					<td><strong> 
						<html:radio property="indicadorExibeDocumento" value="1"/> Sim 
						<html:radio property="indicadorExibeDocumento" value="2"/> N&atilde;o 
						</strong>
					</td>
				</tr>
				 <td>&nbsp;</td>
	          <td colspan="3">&nbsp;</td>
	        </tr>
	
			<tr>
				<td>

					<input type="button" name="Button1"
						class="bottonRightCol" value="Fechar" 
						onclick="window.close();">
	
						
	            </td>
	            <td  align="right">
	            
					<input type="button" name="Button3"
					class="bottonRightCol" value="Inserir" tabindex="4"
					onclick="javascript:adicionar(); validarForm(document.forms[0]);"/>
					
				</td>
			</tr>

      </table>

			<p>&nbsp;</p>
			<p>&nbsp;</p>
	</table>
</html:form>
</body>
</html:html>
