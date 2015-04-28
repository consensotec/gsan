<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@page import="gsan.util.ConstantesSistema"%>
<%@ page import="java.util.Collection" isELIgnored="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>

<head>
<%@ include file="/jsp/util/telaespera.jsp"%>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="AtualizarContaNovoClienteActionForm" dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
	<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript">

	function validarForm(formulario){	
		if(validateAtualizarContaNovoClienteActionForm(formulario)){    		
    		if(validateInteger(formulario)){	     
	  			submeterFormPadrao(formulario);
	  		}
		}
	}

	function limparForm() {
		var form = document.AtualizarContaNovoClienteActionForm;
		form.matricula.value = "";
		form.inscricao.value = "";
		form.idCliente.value = "";
		form.nomeCliente.value = "";
		habilitarDesabilitarCampos();
	}

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];

		if (tipoConsulta == 'cliente') {
			form.nomeCliente.style.color = "#000000";
			form.idCliente.value = codigoRegistro;
			form.nomeCliente.value = descricaoRegistro;
		} else  if (tipoConsulta == 'imovel') {
	      	form.matricula.value = codigoRegistro;
	      	form.inscricao.value = descricaoRegistro;
		  	form.inscricao.style.color = '#000000';	 
		} else  if (tipoConsulta == 'clienteAssociado') {
	      	form.idClienteAssociado.value = codigoRegistro;
	      	form.nomeClienteassociado.value = descricaoRegistro;
		  	form.nomeClienteassociado.style.color = '#000000';	 
		}

		if (form.idClienteAssociado != undefined ) {
			 habilitarDesabilitarBotao();
		}

		habilitarDesabilitarCampos();
	}

	function limparClienteResposavelForm(){
		var form = document.forms[0];
		form.idCliente.value = '';
		form.nomeCliente.value = '';
		form.action = 'exibirAtualizarContaNovoClienteAction.do?menu=sim';
		form.submit();
		
	}
	
	function limparNovoClienteResposavelForm(){
		var form = document.forms[0];
		form.nomeClienteassociado.value = "";
		form.idClienteAssociado.value = "";
		habilitarDesabilitarBotao();
	}

	
	function limparFormImovel(){
		var form = document.forms[0];
		form.matricula.value = '';
		form.inscricao.value = '';
		form.action = 'exibirAtualizarContaNovoClienteAction.do?menu=sim';
		form.submit();
	}

	function habilitarDesabilitarCampos(){
		var form = document.forms[0];

		if ( form.matricula.value != null && form.matricula.value != '' ) {
			form.idCliente.disabled = true;
			form.nomeCliente.disabled = true;
			form.nomeCliente.value = '';
		} else {
			form.idCliente.disabled = false;
			form.nomeCliente.disabled = false;
		}

		if ( form.idCliente.value != null && form.idCliente.value != '' ) {
			form.matricula.disabled = true;
			form.inscricao.disabled = true;
			form.inscricao.value = '';
		} else {
			form.matricula.disabled = false;
			form.inscricao.disabled = false;
		}
	}

	function habilitarDesabilitarBotao(){
		var form = document.forms[0];

		if ( form.idClienteAssociado.value != null && form.idClienteAssociado.value != '' ) {
			form.ButtonAssociar.disabled = false;
			form.ButtonAssociar.disabled = false;
		} else {
			form.ButtonAssociar.disabled = true;
			form.ButtonAssociar.disabled = true;
		}
	}

	function consultarDebitos(){
		var form = document.forms[0];
		
		if ( (form.idCliente.value != null && form.idCliente.value != '') || 
			 (form.matricula.value != null && form.matricula.value != '') ) {			

			form.action = 'exibirAtualizarContaNovoClienteAction.do?pesquisar=ok';
			form.submit();
		}
	}

	function facilitador(objeto){
		if (objeto.value == "0"){
			objeto.value = "1";
			marcarTodos();	
		}
		else{
			objeto.value = "0";
			desmarcarTodos();	
			
		}
	}

	function removerClienteResponsavel(objeto){
		if (CheckboxNaoVazioMensagemGenerico('Nenhum registro selecionado',objeto)){
			if (confirm ("Confirma a operação?")) {
				document.forms[0].action = "atualizarContaNovoClienteAction.do?operacao=removerClienteConta"
					eval('submitForm(document.forms[0]);')
			 }
		} else {
			alert("É necessário selecionar pelo menos uma conta.")
		}
	}

	function associarClienteResponsavelImovel(objeto){
		
		if (CheckboxNaoVazioMensagemGenerico('Nenhum registro selecionado',objeto)){
			if (confirm ("Confirma a operação?")) {
				document.forms[0].action = "atualizarContaNovoClienteAction.do?operacao=associarClienteResponsavelImovel"
					eval('submitForm(document.forms[0]);')
			}
		} 
	}

	function associarClienteResponsavelInformado(objeto){
		
		if (CheckboxNaoVazioMensagemGenerico('Nenhum registro selecionado',objeto)){
			if (confirm ("Confirma a operação?")) {
				document.forms[0].action = "atualizarContaNovoClienteAction.do?operacao=associarClienteResponsavelInformado"
					eval('submitForm(document.forms[0]);')
			}
		} 
	}

	function pesquisarImovel(){
		var form = document.forms[0];
		if (form.idCliente.value == null || form.idCliente.value == '') {
			abrirPopup('exibirPesquisarImovelAction.do', 'imovel', null, null, 275, 480, '',document.forms[0].matricula);
		}
	}

	function pesquisarCliente(){
		var form = document.forms[0];
		if (form.matricula.value == null || form.matricula.value == '') {
			abrirPopup('exibirPesquisarClienteAction.do', 'cliente', null, null, 275, 480, '',document.forms[0].idCliente);
		}
	}
	
</script>

</head>

<body leftmargin="5" topmargin="5" onload="habilitarDesabilitarCampos();habilitarDesabilitarBotao();">
<div id="formDiv">
<html:form action="/atualizarContaNovoClienteAction"
	name="AtualizarContaNovoClienteActionForm"
	type="gsan.gui.faturamento.conta.AtualizarContaNovoClienteActionForm" method="post">

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
			<td width="615" valign="top" class="centercoltext">
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
					<td class="parabg">Associar Contas a Novo Cliente ou Remover o Cliente da Conta</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				

				<tr>
					<td width="30%"><strong>Cliente Responsável:</strong></td>
					<td><html:text property="idCliente" size="9" maxlength="9"
						onkeyup="habilitarDesabilitarCampos();validaEnter(event, 'exibirAtualizarContaNovoClienteAction.do?consulta=cliente', 'idCliente');"
						onkeypress="habilitarDesabilitarCampos();" /> <a
						href="javascript:pesquisarCliente();">
					<img width="23" height="21"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						style="cursor:hand;" alt="Pesquisar" title="Pesquisar Cliente Responsável" border="0" /></a> <logic:present
						name="clienteInexistente" scope="request">
						<html:text property="nomeCliente" size="30" maxlength="30"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present> <logic:notPresent name="clienteInexistente"
						scope="request">
						<html:text property="nomeCliente" size="30" maxlength="30"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:limparClienteResposavelForm();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /> </a></td>
				</tr>
				
				<tr> 
	                <td><strong><span class="style2">Matr&iacute;cula do Im&oacute;vel</span>:</strong></td>
	                <td colspan="6"><span class="style2"><strong>
						<html:text property="matricula" size="9" maxlength="9" 
								   onkeypress="javascript:validaEnterComMensagem(event, 'exibirAtualizarContaNovoClienteAction.do?consulta=imovel', 'matricula','Matrícula do Imovel');habilitarDesabilitarCampos();return isCampoNumerico(event);"
								   onkeyup="habilitarDesabilitarCampos();"/> <a 
								   href="javascript:pesquisarImovel();">
							<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"	height="21" name="imagem" alt="Pesquisar" border="0" title="Pesquisar Imóvel"></a>
							 
						<logic:present name="inscricaoImovelEncontrada" scope="session">
							<html:text property="inscricao" readonly="true" 
									   style="background-color:#EFEFEF; border:0; color: #000000" size="30" maxlength="30" />
						</logic:present> 
	
						<logic:notPresent name="inscricaoImovelEncontrada" scope="session">
							<html:text property="inscricao" readonly="true" 
									   style="background-color:#EFEFEF; border:0; color: #ff0000" size="30" maxlength="30" />
						</logic:notPresent>  						 
							 
						<a href="javascript:limparFormImovel();">
							<img border="0" title="Apagar" src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
	                  </strong></span></td>
	              </tr>
	              <tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				  </tr>	
				 
				  <tr>
					<td width="65" align="right" colspan="4">
						<input  name="Button2" type="button"
								class="bottonRightCol" value="Selecionar" align="right"
								onClick="javascript:consultarDebitos();" tabindex="9" /></td>
				  </tr>		
				 <tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				  </tr>	
				  <logic:present name="colecaoAssociarContaClienteNovo" scope="session" >
					  <table width="100%" bgcolor="#90c7fc">
						<tr bordercolor="#000000">
							<td width="10%" bgcolor="#90c7fc" align="center" >
								<div class="style9"><font color="#000000" style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"><a
								href="javascript:facilitador(this);"> <strong>Todos</strong></a>
								</font></div>
							</td>
							<td width="10%" bgcolor="#90c7fc" align="center">
								<div class="style9"><font color="#000000" style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Imóvel</strong>
								</font></div>
							</td>
							<td width="34%" bgcolor="#90c7fc" align="center">
								<div class="style9"><font color="#000000" style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Nome do Cliente Usuário</strong>
								</font></div>
							</td>
							<td width="12%" bgcolor="#90c7fc" align="left">
								<div class="style9"><font color="#000000" style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Referência</strong> </font></div>
							</td>
							<td width="12%" bgcolor="#90c7fc" align="left">
								<div class="style9"><font color="#000000" style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Vencimento</strong> </font></div>
							</td>
							<td width="12%" bgcolor="#90c7fc" align="left">
								<div class="style9"><font color="#000000" style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor</strong> </font></div>
							</td>
							<td width="10%" bgcolor="#90c7fc" align="left">
								<div class="style9"><font color="#000000" style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Situação</strong> </font></div>
							</td>
						</tr>
						<%String cor = "#cbe5fe";%>
						<%cor = "#cbe5fe";%>
						<%if (((Collection) session.getAttribute("colecaoAssociarContaClienteNovo")).size() > 10) {%>
							<td height="150" colspan="10">
						<%} else {%>
							<td  colspan="10">
						<%} %>
							<div style="width: 100%;  height: 100%; overflow: auto;">
								<table width="100%">
									<logic:iterate name="colecaoAssociarContaClienteNovo" id="helper" >
						
										<%if (cor.equalsIgnoreCase("#cbe5fe")) {
											cor = "#FFFFFF";%>
										<tr bgcolor="#FFFFFF">
											<%} else {
											cor = "#cbe5fe";%>
										<tr bgcolor="#cbe5fe">
											<%}%>
							
											<td align="left" width="10%">
												<div align="center">
													<input type="checkbox" name="idRegistros" value="${helper.idContaSelecionada}" />
												</div>
											</td>
											<td align="left" width="10%">
												<div align="center">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<bean:write name="helper" property="idImovel"/> 
													</font>
												</div>
											</td>
											<td align="left" width="34%">
												<div align="center">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<bean:write name="helper" property="nomeCliente"/> 
													</font>
												</div>
											</td>
											<td align="left" width="12%">
												<div align="center">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
													<a href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=<bean:write name="helper" property="idContaSelecionada" />&tipoConsulta=conta', 600, 800);">
														<bean:write name="helper" property="anoMesReferencia"/> 
														</a>
													</font>
												</div>
											</td>
											<td align="left" width="12%">
												<div align="center">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<bean:write name="helper" property="dataVencimento"/> 
													</font>
												</div>
											</td>
											<td align="left" width="12%">
												<div align="center">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<bean:write name="helper" property="valorConta"/> 
													</font>
												</div>
											</td>
											<td align="left" width="10%">
												<div align="center">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<bean:write name="helper" property="situacaoConta"/> 
													</font>
												</div>
											</td>
										</tr>
									</logic:iterate>
								</table>
							</div>
						</td>
				  </table>
				  <table width="100%" border="0">
				 	 <tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
				  	</tr>
				 	<tr>
						<td width="30%"><strong>Novo Cliente Responsável Associado às Contas:</strong></td>
						<td><html:text property="idClienteAssociado" size="9" maxlength="9"
							onkeyup="validaEnter(event, 'exibirAtualizarContaNovoClienteAction.do?consulta=clienteAssociado', 'idClienteAssociado');habilitarDesabilitarBotao();"
							onkeypress="habilitarDesabilitarBotao();return isCampoNumerico(event);"
							onblur="habilitarDesabilitarBotao();"
							/> <a
							href="javascript:abrirPopup('exibirPesquisarClienteAction.do?consultaCliente=sim&pesquisarClienteAssociado=sim', 'clienteAssociado', null, null, 275, 480, '',document.forms[0].idClienteAssociado);">
						<img width="23" height="21"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							style="cursor:hand;" alt="Pesquisar" title="Pesquisar Novo Cliente Responsável" border="0" /></a> <logic:present
							name="clienteAssociadoInexistente" scope="request">
							<html:text property="nomeClienteassociado" size="30" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:present> <logic:notPresent name="clienteAssociadoInexistente"
							scope="request">
							<html:text property="nomeClienteassociado" size="30" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notPresent> <a href="javascript:limparNovoClienteResposavelForm();"> <img
							src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar" /> </a></td>
					</tr>
					<tr>
						<td colspan="4"  align="right">
						<input name="ButtonAssociar" type="button" class="bottonRightCol" id="ButtonAssociar"
						 	value="Associar Conta ao Cliente Responsável Informado" align="right"
						 	width="100px"
							onclick="associarClienteResponsavelInformado(document.forms[0].idRegistros);"
							tabindex="8">
						</td>
				  	</tr>	
				  </table>
				   <table width="100%" border="0">
					  	<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>	
				  		<tr >
							<td colspan="2">
								<input  name="Button" 
									type="button" 
									class="bottonRightCol"
								 	value="Remover Cliente Conta" 
								 	align="left"
									onclick="removerClienteResponsavel(document.forms[0].idRegistros);"
									tabindex="8">
							</td>
							<td  colspan="2" align="right">
								<input name="Button" type="button" class="bottonRightCol"  align="right"
							 	value="Associar Conta ao Cliente Responsável do Imóvel" 
								onclick="associarClienteResponsavelImovel(document.forms[0].idRegistros);"
								>
								
							</td>
					  	</tr>
				  	<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>	
			  </table>
			  </logic:present>	  
			  
			 
			  
			  <table>
			  
			  	<tr>
			  		<td colspan="1" >
			  			<input type="button" align="left"
						name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"> 
					</td>
					<td colspan="3"><input name="Button" type="button" class="bottonRightCol"
					 	value="Limpar" align="left"
						onclick="window.location.href='/gsan/exibirAtualizarContaNovoClienteAction.do?menu=sim'"
						tabindex="8">
					</td>
			  	</tr>
			  	<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>	
			  </table>

			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</div>
</body>
</html:html>
