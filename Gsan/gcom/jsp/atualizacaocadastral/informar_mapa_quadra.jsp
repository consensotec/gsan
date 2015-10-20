<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript">

function validarForm() {
	var form = document.forms[0];

	var idLocalidade = form.idLocalidade.value;
	var codSetor = form.codigoSetorComercial.value;
	var numQuadra = form.numeroQuadra.value;
	var filename = form.file.value.replace(/^.*\\/,'');

	if(!idLocalidade || isNaN(idLocalidade)) {
		alert('Informe a Localidade');
		return;
	}

	if(!codSetor || isNaN(codSetor)) {
		alert('Informe o Setor Comercial');
		return;
	}

	if(!numQuadra || isNaN(numQuadra)) {
		alert('Informe a Quadra');
		return;
	}

	if(!form.file.value) {
		alert("Informe o arquivo");
		return;
	}

	if(!(/^L\d{3}-S\d{3}-Q\d{4}\.kmz$/i.test(filename))) {
		alert("O arquivo deve ser do formato KMZ e no padrão L999-S999-Q9999");
		return;
	}

	var localidadeArquivo = filename.match(/L(\d{3})/i)[1];
	if(idLocalidade != parseInt(localidadeArquivo)) {
		alert("A localidade do arquivo difere da localidade informada");
		return;
	}

	var setorArquivo = filename.match(/S(\d{3})/i)[1];
	if(codSetor != parseInt(setorArquivo)) {
		alert("O setor do arquivo difere do setor informado");
		return;
	}

	var quadraArquivo = filename.match(/Q(\d{4})/i)[1];
	if(numQuadra != parseInt(quadraArquivo)) {
		alert("A quadra do arquivo difere da quadra informada");
		return;
	}

	submitForm(form);
}

function chamarPopupSetor(url) {
	var idLocalidade = document.forms[0].idLocalidade.value;

	if(!idLocalidade || isNaN(idLocalidade)) {
		alert('Informe a Localidade');
		return;
	}

	abrirPopup(url + "?tipo=setorComercial&idLocalidade=" + idLocalidade, 275, 480);
}

function chamarPopupQuadra() {
	var idLocalidade = document.forms[0].idLocalidade.value;
	var codSetor = document.forms[0].codigoSetorComercial.value;

     if(!idLocalidade || isNaN(idLocalidade)) {
		alert("Informe Localidade");
		return;     
     }

	if(!codSetor || isNaN(codSetor)) {
		alert("Informe Setor Comercial");
		return;
    }

	 abrirPopup('exibirPesquisarQuadraAction.do?consulta=sim&idLocalidade=' + idLocalidade +
			 '&codigoSetorComercial=' + codSetor, 275, 480);	
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	var form = document.forms[0];

	if (tipoConsulta == 'localidade') {
  		form.idLocalidade.value = codigoRegistro;
  		form.descricaoLocalidade.value = descricaoRegistro;
  		form.descricaoLocalidade.style.color = "#000000";
  		form.codigoSetorComercial.focus();
		return;
	}

	if (tipoConsulta == 'setorComercial') {
		form.codigoSetorComercial.value = codigoRegistro;
		form.descricaoSetorComercial.value = descricaoRegistro;
		form.descricaoSetorComercial.style.color = "#000000";
  		form.numeroQuadra.focus();
		return;
	}

	if (tipoConsulta == 'quadra') {
		form.numeroQuadra.value = codigoRegistro;
		form.descricaoQuadra.value = codigoRegistro;
		form.descricaoQuadra.style.color = "#000000";
	}
}

function limparPesquisa(tipo) {
	var form = document.forms[0];

	switch(tipo) {
		case 1:
			form.descricaoLocalidade.value = "";
			form.codigoSetorComercial.value = "";
			form.descricaoSetorComercial.value = "";
			form.numeroQuadra.value = "";
			form.descricaoQuadra.value = "";
			break;

		case 2:
			form.descricaoSetorComercial.value = "";
			form.numeroQuadra.value = "";
			form.descricaoQuadra.value = "";
			break;

		case 3:
			form.descricaoQuadra.value = "";
			break;
	}
}

function limparCampos(apenasSetor, apenasQuadra) {
	var form = document.forms[0];

	form.numeroQuadra.value = "";
	form.descricaoQuadra.value = "";

	if(apenasQuadra) {
		form.numeroQuadra.focus();
		return;
	}

	form.codigoSetorComercial.value = "";
	form.descricaoSetorComercial.value = "";

	if(apenasSetor) {
		form.codigoSetorComercial.focus();
		return;
	}
	
	form.idLocalidade.value = "";
	form.descricaoLocalidade.value = "";
	form.idLocalidade.focus();
}

</script>
</head>
<body leftmargin="5" topmargin="5">
<div id="formDiv">
<html:form action="/uploadMapaQuadraAction.do" name="ExibirInformarMapaQuadraActionForm"
	type="gcom.gui.atualizacaocadastral.ExibirInformarMapaQuadraActionForm" method="post" enctype="multipart/form-data">

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

			<td valign="top" class="centercoltext">

				<p>&nbsp;</p>

				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/>
						</td>
						<td class="parabg">Informar Mapa da Quadra</td>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/>
						</td>
					</tr>
				</table>

				<p>&nbsp;</p>
				<table width="100%" border="0">
				<tr>
				<td colspan="2">
					<table width="100%" border="0">
						<tr>
							<td colspan="2">Para informar o mapa da quadra, informe os dados abaixo:</td>
						</tr>

						<tr>
							<td width="10%"><strong>Localidade:<font color="#FF0000">*</font></strong></td>
				            <td height="24" colspan="2">
		                   		<html:text property="idLocalidade"
		                   			maxlength="3" size="3" tabindex="7"
									onkeyup="javascript:limparPesquisa(1);" 
									onkeypress="javascript:validaEnterComMensagem(event, 'exibirInformarMapaQuadraAction.do', 'idLocalidade', 'Localidade'); return isCampoNumerico(event);"/>

		                      	<a href="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do', 400, 800);">
		                      		<img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar Localidade"/>
		                      	</a>
								
								<c:choose>
									<c:when test="${ExibirInformarMapaQuadraActionForm.descricaoLocalidade == 'LOCALIDADE INEXISTENTE' }">
										<html:text property="descricaoLocalidade" 
										size="35" readonly="true"
										style="background-color:#EFEFEF; border:0; color: red;"/>
									</c:when>
									<c:otherwise>
										<html:text property="descricaoLocalidade" 
										size="35" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"/>
									</c:otherwise>
								</c:choose>

								<a href="javascript:limparCampos();">
									<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar Localidade"/>
								</a>                   
							</td>
						</tr>

						<tr>
							<td><strong>Setor&nbsp;Comercial:&nbsp;<font color="#FF0000">*</font></strong></td>
				            <td height="24" colspan="2">
		                   		<html:text property="codigoSetorComercial"
		                   			maxlength="3" size="3" tabindex="7"
									onkeyup="javascript:limparPesquisa(2);"
									onkeypress="javascript:validaEnterDependenciaComMensagem(event, 'exibirInformarMapaQuadraAction.do', this , document.forms[0].idLocalidade.value , 'Localidade', 'Setor Comercial'); return isCampoNumerico(event);"/>

		                      	<a href="javascript:chamarPopupSetor('exibirPesquisarSetorComercialAction.do');">
		                      		<img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar Setor Comercial"/>
		                      	</a>

								<c:choose>
									<c:when test="${ExibirInformarMapaQuadraActionForm.descricaoSetorComercial == 'SETOR COMERCIAL INEXISTENTE' }">
										<html:text property="descricaoSetorComercial" 
										size="35" readonly="true"
										style="background-color:#EFEFEF; border:0; color: red"/>
									</c:when>
									<c:otherwise>
		                        		<html:text property="descricaoSetorComercial" 
										size="35" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"/>
									</c:otherwise>
								</c:choose>

								<a href="javascript:limparCampos(true);">
									<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar Setor Comercial"/>
								</a>                   
							</td>
						</tr>

						<tr>
							<td><strong>Quadra:&nbsp;<font color="#FF0000">*</font></strong></td>
				            <td height="24" colspan="2">
		                   		<html:text property="numeroQuadra"
									maxlength="3" size="3" tabindex="7"
									onkeyup="javascript:limparPesquisa(3);"
									onkeypress="javascript:validaEnterDependenciaComMensagem(event, 'exibirInformarMapaQuadraAction.do', this , document.forms[0].codigoSetorComercial.value , 'Setor Comercial', 'Quadra'); return isCampoNumerico(event);"/>

		                      	<a href="javascript:chamarPopupQuadra();">
		                        	<img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar Quadra"/>
		                        </a>

								<c:choose>
									<c:when test="${ExibirInformarMapaQuadraActionForm.descricaoQuadra == 'QUADRA INEXISTENTE' }">
										<html:text property="descricaoQuadra" 
										size="35" readonly="true" 
										style="background-color:#EFEFEF; border:0; color: red"/>
									</c:when>
									<c:otherwise>
		                        		<html:text property="descricaoQuadra" 
										size="35" readonly="true" 
										style="background-color:#EFEFEF; border:0; color: #000000"/>
									</c:otherwise>
								</c:choose>

								<a href="javascript:limparCampos(false, true);">
									<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar Quadra"/>
								</a>                   
							</td>
						</tr>

						<tr>
							<td><strong>Arquivo:<font color="#FF0000">*</font></strong></td>
							<td width="90%"><html:file style="width: 100%" styleId="file" property="formFile" size="35"/></td>
						</tr>

						<tr>
							<td>&nbsp;</td>
							<td align="left"><font color="#FF0000">*</font> Campo Obrigat&oacute;rio</td>
						</tr>
					</table>
				</td></tr>

				<tr><td>&nbsp;</td></tr>
				<tr><td>&nbsp;</td></tr>

				<tr>
					<td align="left">
						<input type="button"name="ButtonDesfazer" class="bottonRightCol" value="Desfazer" align="left"
							onclick="window.location.href='<html:rewrite page="/exibirInformarMapaQuadraAction.do?menu=sim"/>'">
	
						&nbsp;
	
						<input type="button" name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
							onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>
					
					<td align="right">
						<input  type="button" name="ButtonConfirmar" class="bottonRightCol" value="Confirmar" onclick="validarForm();" />
					</td>
				</tr>
				
				</table>

			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</div>
</body>
<%@ include file="/jsp/util/telaespera.jsp"%>
</html:html>
