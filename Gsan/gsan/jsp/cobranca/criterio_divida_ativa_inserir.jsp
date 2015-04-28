<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gsan.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="InserirCriterioDividaAtivaActionForm" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

	function validarForm(form){
		if(validateInserirCriterioDividaAtivaActionForm(form)){
	    	if(validarIntervalo()){
	    		javascript:botaoAvancarTelaEspera('/gsan/inserirCriterioDividaAtivaAction.do');
	    	}
		}
	}

	function limparForm(form) {
		form.vencimentoConta.value = "";
		form.esferaPoder.value = "-1";
		form.tipoCliente.value = "-1"; 
		form.valorInicial.value = "";
		form.valorFinal.value = "";
	}
	
	function replicarCampo(fim,inicio) {
    	fim.value = inicio.value;
	}
	
	function validarIntervalo(){
		var form = document.forms[0];
		
		if(form.valorInicial.value == '' && form.valorFinal.value != ''){
			alert('Informe Valor Inicial');
			return false;
		}else if(form.valorInicial.value != '' && form.valorFinal.value == ''){
			alert('Informe Valor Final');
			return false;
		}
	
		return true;
	}

</script>


</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<div id="formDiv">
<html:form action="/inserirCriterioDividaAtivaAction" method="post"
	name="InserirCriterioDividaAtivaActionForm"
	type="gsan.gui.cobranca.InserirCriterioDividaAtivaActionForm"
	onsubmit="return validateInserirCriterioDividaAtivaActionForm(this);">
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

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
			<td width="625" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>

			<!--Início Tabela Reference a Páginação da Tela de Processo-->
			<table>
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Inscrição em Dívida Ativa</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>

					<td colspan="2">Para seleção dos débitos para inscrição em dívida ativa,
					informe os dados abaixo:</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td width="162"><strong>Data Máxima Vencimento Conta:<font color="#FF0000">*</font></strong></td>
					<td><strong> <html:text property="vencimentoConta"
						size="10" maxlength="10" onkeypress="return isCampoNumerico(event);"
						onkeyup="mascaraData(this,event);" />
					<a
						href="javascript:abrirCalendario('InserirCriterioDividaAtivaActionForm', 'vencimentoConta')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="middle" title="Exibir Calendário" /></a> dd/mm/aaaa</strong></td>
				</tr>
				
				<tr>
					<td colspan="3"><hr /></td>
				</tr>
				
				<tr> 
                	<td><strong>Esfera de Poder:</strong></td>
                	<td colspan="6"><span class="style2"><strong>
						<html:select property="esferaPoder" style="width: 350px; height:100px;" multiple="true" >
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<logic:present name="colecaoEsferaPoder" scope="request">
								<html:options collection="colecaoEsferaPoder" labelProperty="descricao" property="id" />
							</logic:present>
						</html:select>                
                  	</strong></span></td>
              	</tr>
				
				<tr> 
                	<td><strong>Tipo de Cliente:</strong></td>
                	<td colspan="6"><span class="style2"><strong>
						<html:select property="tipoCliente" style="width: 350px; height:100px;" multiple="true" >
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<logic:present name="colecaoClienteTipo" scope="request">
								<html:options collection="colecaoClienteTipo" labelProperty="descricao" property="id" />
							</logic:present>
						</html:select>                
                  	</strong></span></td>
              	</tr>
				
				<tr>
					<td width="30%"><strong>Intervalo de Valor:</strong></td>
					<td width="70%">
						<html:text  property="valorInicial" 
									size="14" 
									maxlength="14" 
									tabindex="15" 
									onkeyup="formataValorMonetario(this, 14);replicarCampo(document.forms[0].valorFinal,this);"
									onkeypress="return isCampoNumerico(event);" 
									style="text-align: right;"/> 
						<strong> a</strong> 
						<html:text 	property="valorFinal" 
									size="14" 
									maxlength="14" 
									tabindex="16" 
									onkeyup="formataValorMonetario(this, 14)" 
									onkeypress="return isCampoNumerico(event);"
									style="text-align: right;" />
					</td>
				</tr>
				
				<tr>
					<td height="19"><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
				<tr>
					<td colspan="2"><input name="Button" type="button"
						class="bottonRightCol" value="Limpar" align="left"
						onclick="javascript:limparForm(document.forms[0]);"> <input
						name="Button" type="button" class="bottonRightCol"
						value="Cancelar" align="left"
						onclick="window.location.href='/gsan/telaPrincipal.do'"></td>
					<td align="right" height="24"><input type="button" name="Button"
						class="bottonRightCol" value="Inserir"
						onClick="javascript:validarForm(document.forms[0]);" /></td>
					<td>&nbsp;</td>
				</tr>
			</table>
			<p>&nbsp;</p>
		</tr>
	</table>
	<tr>
		<td colspan="3"><%@ include file="/jsp/util/rodape.jsp"%>
	</tr>
</html:form>
</div>
<%@ include file="/jsp/util/telaespera.jsp"%>
</body>
</html:html>

