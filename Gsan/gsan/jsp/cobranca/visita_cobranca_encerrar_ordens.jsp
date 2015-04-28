<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="gsan.util.ConstantesSistema"%>
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="EncerrarOrdensVisitaCobrancaActionForm" />

<script language="JavaScript">



function validarForm(){
	form = document.forms[0];
	if(form.idGrupo.value == -1){
		alert("Informe Grupo");
		return false;
	}
	else if(form.mesano.value == ""){
		alert("Informe Mês/Ano do Ciclo de Cobrança");
		return false;
	}
	 var form = document.forms[0]; 
	 form.submit();
}


</script>

</head>

<body leftmargin="5" topmargin="5">
<div id="formDiv">
<html:form action="/encerrarOrdensVisitaCobrancaAction"
	name="EncerrarOrdensVisitaCobrancaActionForm"
	type="gsan.gui.cobranca.EncerrarOrdensVisitaCobrancaActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>

			<td width="115" valign="top" class="leftcoltext">
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
			<td width="600" valign="top" bgcolor="#003399" class="centercoltext">
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
					<td class="parabg">Encerrar Ordens de Visita de Cobrança</td>

					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				<tr>
					<td height="5" colspan="3"></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td colspan="3">Para encerrar as ordens de visita de cobrança, preencher os dados abaixo: </td>
				</tr>
				<tr>
					<td width="130"><strong>Grupo:<font color="#FF0000">*</font></strong></td>
	            	<td>
			            <div align="left">
			                <strong>
				                <html:select property="idGrupo">
					                <html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
   					                <logic:present name="colecaoGrupos" scope="session">
						            	<html:options collection="colecaoGrupos" labelProperty="descricao" property="id" />
					            	</logic:present>
								</html:select>
			                </strong>
			            </div>
	           		 </td>
				</tr>
	     		
	     		<tr>
					<td><strong>Ciclo de Cobrança:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="mesano" size="7"
						maxlength="7" onkeyup="javascript:mascaraAnoMes(this,event);" 
						onkeypress="return isCampoNumerico(event);"/>
					mm/aaaa</td>

				</tr>
				
              </tr>
			<tr>
				<td>&nbsp;</td>
				<td align="left"><font color="#FF0000">*</font> Campos Obrigatórios</td>	
			</tr>
			<tr>
				<td>&nbsp;</td>	
			</tr>
			<tr>
							
				<td colspan="2" >
					<input name="Button" type="button" class="bottonRightCol"
						value="Desfazer" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirEncerrarOrdensVisitaCobrancaAction.do?menu=sim"/>'">
				<input type="button" name="ButtonCancelar" class="bottonRightCol"
					value="Cancelar"
					onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"></td>
				<td>&nbsp;</td>
				
				<td align="right"><input type="button" name="Submit"
				class="bottonRightCol" value="Encerrar" id="botaoGerar" onclick="javascript:validarForm();"></td>
			</tr>	
		</table>	
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</div>
</body>

</html:html>
