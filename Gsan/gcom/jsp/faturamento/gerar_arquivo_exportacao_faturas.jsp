<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="gcom.util.ConstantesSistema"%>

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<html:javascript staticJavascript="false" formName="GerarArquivoExportacaoFaturasActionForm" dynamicJavascript="true" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];

		if (tipoConsulta == 'cliente') {
	      form.idCliente.value = codigoRegistro;
	      form.nomeCliente.value = descricaoRegistro;
	      form.nomeCliente.style.color = "#000000";
		} else if (tipoConsulta == 'responsavelSuperior') {
	      form.idClienteSuperior.value = codigoRegistro;
	      form.nomeClienteSuperior.value = descricaoRegistro;
	      form.nomeClienteSuperior.style.color = "#000000";
		}
	}

	function limparClienteTecla(){
		var form = document.forms[0];

		if (form.idCliente.value != "") {
			bloquearClienteSuperior(form);
		} else if (form.idClienteSuperior.value == "") {
			desbloquearClienteSuperior(form);
		}
		
		form.nomeCliente.value = "";
	}
	
	function limparCliente(){
		var form = document.forms[0];
		
		if (form.idClienteSuperior.value == "") {
			desbloquearClienteSuperior(form);
		}
		
		form.idCliente.value = "";
		form.nomeCliente.value = "";
	}
	
	function limparClienteSuperiorTecla(){
		var form = document.forms[0];

		if (form.idClienteSuperior.value != "") {
			bloquearCliente(form);
		} else if (form.idCliente.value == "") {
			desbloquearCliente(form);
		}
		
		form.nomeClienteSuperior.value = "";
	}
	
	function limparClienteSuperior(){
		var form = document.forms[0];
		
		form.idClienteSuperior.value = "";
		form.nomeClienteSuperior.value = "";
		
		if (form.idCliente.value == "") {
			desbloquearCliente(form);
		}
	}

	function gerarArquivoExportacao(form){
		if(validateGerarArquivoExportacaoFaturasActionForm(form)) {
			if (form.idCliente.value == "" && form.idClienteSuperior.value == "") {
				alert("Obrigatório informar Cliente Superior ou Cliente");
				return;
			}
			botaoAvancarTelaEspera('/gsan/gerarArquivoExportacaoFaturasAction.do');
		}
	}

	function bloquearCliente(form) {
		form.idCliente.value = "";
		form.idCliente.readOnly = true;
	}
	
	function bloquearClienteSuperior(form) {
		form.idClienteSuperior.value = "";
		form.idClienteSuperior.readOnly = true;
	}
	
	function desbloquearCliente(form) {
		form.idCliente.value = "";
		form.idCliente.readOnly = false;
	}
	
	function desbloquearClienteSuperior(form) {
		form.idClienteSuperior.value = "";
		form.idClienteSuperior.readOnly = false;
	}

	function bloquearCampos() {
		var form = document.forms[0];
		
		if (form.idCliente.value != "") {
			bloquearClienteSuperior(form);
		} else if (form.idClienteSuperior.value != "") {
			bloquearCliente(form);
		}
	}

	function limparFormulario(form) {
		form.mesAno.value = "";

		form.idClienteSuperior.value = "";
		form.idClienteSuperior.readOnly = false;
		form.nomeClienteSuperior.value = "";

		form.idCliente.value = "";
		form.idCliente.readOnly = false;
		form.nomeCliente.value = "";
	}
</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');bloquearCampos();">

<div id="formDiv">

<html:form action="/gerarArquivoExportacaoFaturasAction"
	name="GerarArquivoExportacaoFaturasActionForm"
	type="gcom.gui.faturamento.GerarArquivoExportacaoFaturasActionForm" method="post"
	onsubmit="return validateGerarArquivoExportacaoFaturasActionForm(this);" >

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
			<p align="left">&nbsp;</p>
			</div>
			</td>
			<td width="625" valign="top" bgcolor="#003399" class="centercoltext">
			<table height="100%">

				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Gerar Arquivo de Exportação de Faturas</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>

			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para gerar o arquivo, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td width="150"><strong>Mês/Ano de Faturamento:<font color="#FF0000">*</font></strong></td>
					<td>
					<html:text property="mesAno" tabindex="1" onkeypress="javascript:mascaraAnoMes(this,event);return isCampoNumerico(event);" size="10" maxlength="7" />
					mm/aaaa
					</td>
				</tr>
				<tr>
					<td><strong>Cliente Superior:</strong></td>
					<td>
						<html:text tabindex="2" property="idClienteSuperior" maxlength="9" size="9" onkeyup="validaEnterComMensagem(event, 'exibirGerarArquivoExportacaoFaturasAction.do?objetoConsulta=1', 'idClienteSuperior');limparClienteSuperiorTecla();"
						onkeypress="return isCampoNumerico(event);" />
						<a href="javascript:abrirPopup('exibirPesquisarResponsavelSuperiorAction.do?pesquisaSuperior=sim', 400, 800);">
							<img width="23" height="21" title="Pesquisar Cliente Superior" src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a>
						<logic:present name="clienteSuperiorInexistente" scope="request">
							<html:text property="nomeClienteSuperior"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000"
								size="40" maxlength="40" />
						</logic:present>
						<logic:notPresent name="clienteSuperiorInexistente" scope="request">
							<html:text property="nomeClienteSuperior"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="40" maxlength="40" />
						</logic:notPresent>	
						<a href="javascript:limparClienteSuperior();"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar Cliente Superior" />
						</a>
					</td>
				</tr>
				<tr>
					<td bordercolor="#000000"><strong>Cliente:</strong>
					</td>
					<td>
						<html:text
							property="idCliente" maxlength="9" size="9" tabindex="3"
							onkeypress="validaEnterComMensagem(event, 'exibirGerarArquivoExportacaoFaturasAction.do?objetoConsulta=2','idCliente', 'Cliente');return isCampoNumerico(event);"
							onkeyup="limparClienteTecla();"/>
						<a href="javascript:abrirPopup('exibirPesquisarClienteAction.do?limparForm=sim', 400, 800);">
							<img width="23" height="21" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar Cliente" /></a>
						<logic:present name="clienteInexistente" scope="request">
							<html:text property="nomeCliente"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000"
								size="40" maxlength="40" />
						</logic:present>
						<logic:notPresent name="clienteInexistente" scope="request">
							<html:text property="nomeCliente"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="40" maxlength="40" />
						</logic:notPresent>	
						<a href="javascript:limparCliente();"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar Cliente" />
						</a>
					</td>	
				</tr>
			</table>

			<br>

			<table width="100%" border="0">
				<tr>
					<td>
						<input type="button" name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
							onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
						<input type="button"
							name="ButtonLimpar" 
							class="bottonRightCol" 
							value="Limpar"
							onClick="javascript:limparFormulario(document.forms[0]);">
					</td>
					<td align="right">
						<input type="button" name="ButtonGerarArquivo" class="bottonRightCol" value="Gerar Arquivo Exportação"
							onclick="javascript:gerarArquivoExportacao(document.forms[0]);" />
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
