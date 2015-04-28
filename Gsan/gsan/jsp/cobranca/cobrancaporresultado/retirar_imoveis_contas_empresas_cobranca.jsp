<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="gsan.gui.cobranca.cobrancaporresultado.RetirarImoveisContasEmpresaCobrancaHelper" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"	href="<bean:message key="caminho.css"/>EstilosCompesa.css"	type="text/css">
<script language="JavaScript"	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"	formName="ConsultarComandosContasCobrancaEmpresaActionForm" />
<script language="JavaScript"	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script type="text/javascript">

	function pesquisarEmpresa() {
		var form = document.forms[0];
		form.idEmpresa.value = "";
		form.nomeEmpresa.value = "";
		abrirPopup('exibirPesquisarEmpresaAction.do?limpaForm=S', 300, 480);
	}

	function limparEmpresa() {
		var form = document.forms[0];
		
		form.idEmpresa.value = "";
		form.nomeEmpresa.value = "";	
		form.periodoComandoInicial.value = '';
		form.periodoComandoFinal.value = '';
				
		form.action = 'exibirRetirarImoveisContasEmpresasCobrancaAction.do?limpar=sim';
		
		submeterFormPadrao(form);
	}
	
	function limparEmpresaTecla() {
		var form = document.forms[0];
		form.nomeEmpresa.value = "";			
	}

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    	var form = document.forms[0];
   
	    if (tipoConsulta == 'empresa') {
			form.idEmpresa.value = codigoRegistro;
			form.nomeEmpresa.value = descricaoRegistro;
			form.nomeEmpresa.style.color = "#000000";
			form.action = 'exibirRetirarImoveisContasEmpresasCobrancaAction.do?limpar=sim';
    		submeterFormPadrao(form);
    	}
	}
	function limparComandos() {
		var form = document.forms[0];								
		form.action = 'exibirRetirarImoveisContasEmpresasCobrancaAction.do?limpar=sim';		
		submeterFormPadrao(form);		
	}
	function selecionarComandos(){
		var form =  document.forms[0];
		var perIni = true;
		var perFin = true;
		if(form.idEmpresa.value !=null && form.idEmpresa.value != ""){
			if(form.periodoComandoInicial.value !='' && form.periodoComandoInicial.value.length < 10){
				perIni = false;
				alert('Informe um período inicial válido');				
			}
			if(form.periodoComandoFinal.value !='' && form.periodoComandoFinal.value.length < 10){
				perFin = false;
				alert('Informe um período final válido');				
			}
			if(perIni && perFin){			
				form.action = 'exibirRetirarImoveisContasEmpresasCobrancaAction.do?selecionarComandos=sim';
	    		form.submit();
			}	
		}
		else{
			alert('Informe a Empresa');	
		}	
	}
	function CheckboxNaoVazio(){
		  form = document.forms[0];
		  retorno = false;
			
		  for(indice = 0; indice < form.elements.length; indice++){
			if (form.elements[indice].type == "checkbox" && form.elements[indice].checked == true) {
				retorno = true;
				break;
			}
		  }
			
		  if (!retorno){
			alert('Informe o(s) comando(s) desejado(s).');
		  }
			
		  return retorno;
	}

	function validaForm() {
		var form =  document.forms[0];
		if(CheckboxNaoVazio()){
			form.action = 'retirarImoveisContasEmpresasCobrancaAction.do';
	    	form.submit();				
		}
	
	}
	 
</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/exibirRetirarImoveisContasEmpresasCobrancaAction"
	name="RetirarImoveisContasEmpresaCobrancaActionForm"
	type="gsan.gui.cobranca.cobrancaporresultado.RetirarImoveisContasEmpresaCobrancaActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>


	<table width="770" border="0" cellspacing="5" cellpadding="0">
	<%
		SimpleDateFormat formata = new SimpleDateFormat("dd/MM/yyyy");
	%>
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
						<td class="parabg">Retirar Imóveis e Contas das Empresas de Cobrança</td>
						<td width="11" valign="top"><img border="0"
							src="imagens/parahead_right.gif" /></td>
					</tr>
				</table>			
				<p>&nbsp;</p>
				<table width="100%" border="0">
					<tr>
						<td colspan="2">Para selecionar os comandos, informar os dados abaixo:</td>
					</tr>
					<tr>
						<td width="30%"><strong>Empresa:<font color="#FF0000">*</font></strong></td>
						<td><html:text maxlength="9" property="idEmpresa" size="9"
						tabindex="14" 
						onkeypress="validaEnterComMensagem(event, 'exibirRetirarImoveisContasEmpresasCobrancaAction.do', 'idEmpresa', 'Empresa');
						limparEmpresaTecla();
						return isCampoNumerico(event);"
						onchange="validarCampoNumericoComMensagemLimpandoCampo(document.forms[0].idEmpresa, 'Empresa');
						limparComandos();" />
					<a href="javascript:pesquisarEmpresa();"><img
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"
						height="21" alt="Pesquisar" title="Pesquisar Empresa" border="0"></a> <logic:present
						name="empresaInexistente" scope="request">
						<html:text property="nomeEmpresa" size="40" maxlength="40"
							readonly="true"
							style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
					</logic:present> <logic:notPresent name="empresaInexistente"
						scope="request">
						<html:text property="nomeEmpresa" size="40" maxlength="40"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:limparEmpresa();"> <img
						border="0" src="imagens/limparcampo.gif" height="21" width="23" title="Apagar Empresa"> </a>
					</td>
					</tr>
					<tr>				
						<td><strong>Período de Execução do Comando:</strong></td>
						<td><strong> 
							<html:text maxlength="10"
								property="periodoComandoInicial" size="10" tabindex="10"
								onkeyup="replicarCampo(document.forms[0].periodoComandoFinal, this);return mascaraDataNova(this, event);"						
								onchange="validarCampoDataComMensagemLimpandoCampo(document.forms[0].periodoComandoInicial, 'Período Execução Inicial');"
							 /> <a
							 href="javascript:abrirCalendarioReplicando('RetirarImoveisContasEmpresaCobrancaActionForm', 'periodoComandoInicial', 'periodoComandoFinal');"><img 
							 border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" align="absmiddle" 
							alt="Exibir Calendário" /></a>
						</strong> 
							<html:text maxlength="10" property="periodoComandoFinal"
								tabindex="11" size="10" onkeyup="return mascaraDataNova(this, event);"
								onchange="validarCampoDataComMensagemLimpandoCampo(document.forms[0].periodoComandoFinal, 'Período Execução Final');
								limparComandos();" />
								<a href="javascript:abrirCalendario('RetirarImoveisContasEmpresaCobrancaActionForm', 'periodoComandoFinal')"><img 
								border="0" src="<bean:message key="caminho.imagens"/>calendario.gif" width="20" border="0" 
							align="absmiddle" alt="Exibir Calendário" /></a>
						(dd/mm/aaaa)</td>
					</tr>
					<tr>
						<td colspan="2" align="right"><input type="button"
							name="selecionar" class="bottonRightCol"
							value="Selecionar Comandos"
							onClick="javascript:selecionarComandos();" />
						</td>
					</tr>
					<tr><td>&nbsp;</td></tr>					
				</table>
				<table width="100%" bgcolor="#99CCFF" border="0">
					<tr bordercolor="#000000">
						<td bgcolor="#90c7fc" align="center" rowspan="2"><strong>Seleciona</strong></td>
						<td bordercolor="#000000" bgcolor="#90c7fc" align="center" rowspan="2">
						<strong>Comando</strong></td>
						<td  bgcolor="#90c7fc" align="center" rowspan="2"><strong>Data Execução</strong></td>
						<td  bgcolor="#90c7fc" align="center" rowspan="2"><strong>Imóveis em Cobrança</strong></td>
						<td  bgcolor="#90c7fc" align="center" rowspan="2"><strong>Contas em Cobrança</strong></td>
					</tr>
				</table>
				<div style="height: 100%; max-height: 300px; overflow: auto;">
				<table width="100%" bgcolor="#99CCFF" border="0">
					<logic:present name="colecaoRetirarImoveisContasEmpresaCobrancaHelper">
						<%int cont = 0;%>
						<logic:iterate
							name="colecaoRetirarImoveisContasEmpresaCobrancaHelper"
							id="helper"
							type="RetirarImoveisContasEmpresaCobrancaHelper"
							scope="session">							
								<%cont = cont + 1;
						if (cont % 2 == 0) {%>
								<tr bgcolor="#cbe5fe">
									<%} else {%>
								</tr>
								<tr bgcolor="#FFFFFF">
									<%}%>
									<td width="9%" >
									<div align="center">
									<html:checkbox property="idRegistros" value="${helper.idComando}"/>																									
									</div>
									</td>

									<td align="center" width="13%"><a
										href="javascript:abrirPopup('exibirConsultarContasComandoCobrancaPopupAction.do?pesquisa=nao&idComandoEmpresaCobrancaConta=<%=helper.getIdComando() %>', 600, 700);">
										<%=helper.getDescricaoComando()%> </a>
									</td>												

									<td align="center" width="21%">
										<%= formata.format(helper.getDataExecucao())%>													
									</td>
									<td align="center" width="29%"><%=helper.getImoveisCobranca() %></td>
									<td align="center"><%=helper.getContasCobranca() %></td>
								</tr>							
						</logic:iterate>
					</logic:present>					
				</table>
				</div>
				<table width="100%" border="0">
			
					<tr>
						<td colspan="2">&nbsp;</td>
					</tr>

					<tr>
						<td><strong> <font color="#FF0000"></font></strong></td>
						<td align="right">
						<div align="left"><strong><font color="#FF0000">*</font></strong>
						Campos obrigat&oacute;rios</div>
						</td>
					</tr>

					<tr>
						<td><input name="Button" type="button" class="bottonRightCol"
							value="Desfazer" align="left"
							onclick="window.location.href='<html:rewrite page="/exibirRetirarImoveisContasEmpresasCobrancaAction.do?menu=sim"/>'">
						<input name="Button" type="button" class="bottonRightCol"
							value="Cancelar" align="left"
							onclick="javascript:window.location.href='/gsan/telaPrincipal.do'"></td>
						<logic:present name="colecaoRetirarImoveisContasEmpresaCobrancaHelper">
							<td colspan="2" align="right"><input type="button" name="gerar"
								class="bottonRightCol" value="Retirar Imóveis e Contas"
								onClick="javascript:validaForm();" /></td>
						</logic:present>
					</tr>
					</table>													
				
				
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form> 
		
</body>
</html:html>