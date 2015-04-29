<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gsan.cadastro.empresa.Empresa"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

	<%@ include file="/jsp/util/titulo.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link rel="stylesheet"
		href="<bean:message key="caminho.css"/>EstilosCompesa.css"
		type="text/css">
	<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
	
	<html:javascript staticJavascript="false"  formName="InformarDadosGeracaoBoletimCustoAtualizacaoCadastralActionForm" />
	
	<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
	
<script language="JavaScript">
	
    function validarForm() {
    	var form = document.forms[0];
    	if ( validateRequired(form) && validateDate(form)){
			if (comparaData(form.dataFinal.value, '<' ,form.dataInicial.value)){
				alert("Data Final do Per�odo de Atualiza��o Cadastral anterior � Data Inicial");
    		}else{
    			form.submit();
    		}
    	}

   	}

	function required() { 
     	this.ab = new Array("empresa", "Informe Empresa.", new Function ("varName", " return this[varName];"));
     	this.ac = new Array("dataInicial", "Informe Data Inicial do Per�odo de Atualiza��o Cadastral.", new Function ("varName", " return this[varName];"));
     	this.ad = new Array("dataFinal", "Informe Data Final do Per�odo de Atualiza��o Cadastral.", new Function ("varName", " return this[varName];"));
    }

    function DateValidations() {
     	this.aa = new Array("dataInicial", "Data Inicial do Per�odo de Atualiza��o Cadastral Inv�lida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
     	this.ab = new Array("dataFinal", "Data Final do Per�odo de Atualiza��o Cadastral Inv�lida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
    } 
    
    

	function limpaDataFinal(){
		var form = document.forms[0];
		  if(form.dataInicial.value == ''){
		   form.dataFinal.value = '';
	}

}

</script>

</head>
<body leftmargin="5" topmargin="5">
	<html:form action="/gerarBoletimCustoAtualizacaoCadastralAction" 
	name="InformarDadosGeracaoBoletimCustoAtualizacaoCadastralActionForm"
	type="gsan.gui.cadastro.InformarDadosGeracaoBoletimCustoAtualizacaoCadastralActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
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
					<td class="parabg">Gerar Boletim de Custo da Atualiza&ccedil;&atilde;o Cadastral</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table border="0" width="100%">
				<tr>
					<td colspan="2">Para gerar o boletim de custo da atualiza&ccedil;&atilde;o cadastral, informe os dados abaixo:
						<table border="0" width="100%">
							<tr>
								<td width="26%"><strong>Empresa:<font color="#ff0000">*</font></strong></td>
								<td width="74%">
									<html:select property="empresa">
										<option value=""></option>
										<html:options name="request" collection="colecaoEmpresa" 
										labelProperty="descricao" property="id" />
									</html:select>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td width="26%"><strong>Per&iacute;odo da Atualiza&ccedil;&atilde;o:<font color="#ff0000">*</font></strong></td>
					<td width="74%"><html:text property="dataInicial" size="10"
						maxlength="10" tabindex="7"
						onkeyup="mascaraData(this, event);replicarCampo(document.forms[0].dataFinal,this);"
						onkeypress="return isCampoNumerico(event);"
						onfocus="replicarCampo(document.forms[0].dataFinal,this);" />
					<a
						href="javascript:abrirCalendarioReplicando('InformarDadosGeracaoBoletimCustoAtualizacaoCadastralActionForm', 'dataInicial', 'dataFinal');">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calend�rio" /></a>
			
					<html:text property="dataFinal" size="10" maxlength="10"
						tabindex="7" onkeyup="mascaraData(this, event);"
						onkeypress="return isCampoNumerico(event);" /> <a
						href="javascript:abrirCalendario('InformarDadosGeracaoBoletimCustoAtualizacaoCadastralActionForm', 'dataFinal')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calend�rio" /></a>
					dd/mm/aaaa</td>
				</tr>
				
				<tr>
					<td width="26%"></td>
					<td width="26%">Campo Obrigat&oacute;rio:<font color="#ff0000">*</font></td>
				</tr>
				<tr>
					<td>
						<input name="Submit22" class="bottonRightCol" value="Gerar Boletim" type="button"
						onclick="javascript:validarForm();">
					</td>
				</tr>
			</table>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
	</html:form>
</body>
</html:html>