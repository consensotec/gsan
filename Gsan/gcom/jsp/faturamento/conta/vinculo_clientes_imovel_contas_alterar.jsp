<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="java.util.Collection,gcom.util.ConstantesSistema"%>
<%@page import="gcom.cadastro.cliente.bean.ClienteImovelHelper" isELIgnored="false"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
	
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
	<html:javascript staticJavascript="false" formName="ExibirAlterarVinculoClientesImovelContasActionForm" />	
	
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>/validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script>

	function limpar(form) {
		 form.action = "exibirAlterarVinculoClientesImovelContasAction.do?menu=sim";
		 form.submit();
	}
	
	function remover(form) {
		msg = "Tem certeza que deseja remover?";  
		if(confirm(msg)){
			form.action = "exibirAlterarVinculoClientesImovelContasAction.do?remover=sim";
			form.submit();
		}
	}
	
	function adicionar(){
		var form = document.forms[0];
		
		if(validateExibirAlterarVinculoClientesImovelContasActionForm(form)){
			validarIndicadorNomeConta();
			
			if(form.dataFinal.value != "" && (form.motivoFim.value == "" || form.motivoFim.value == "-1")){
				alert('Informe o motivo de Fim do Vínculo');
			}else if(form.dataFinal.value == "" && (form.motivoFim.value != "-1")){
				alert('Informe o fim do Vínculo');
			}else{
				form.action = "exibirAlterarVinculoClientesImovelContasAction.do?adicionar=sim";
				form.submit();
			}
		}
	}
	
	function inserirCliente(){
		abrirPopupModalDeNome('exibirInserirClienteAction.do?POPUP=true&tipoBotao=2&PopUpInserirClienteRetorno=exibirAlterarVinculoClientesImovelContasAction', 500, 800, 'POPUP1', 'yes');
	}
	
	function alterarVinculos(){
		var form = document.forms[0];
		
		form.action = "alterarVinculoClientesImovelContaAction.do";
			 submitForm(form);
	}

	function limparCliente() {
	 	document.forms[0].nomeCliente.value = '';
	 	document.forms[0].idCliente.value = '';
	}
 
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    var form = document.forms[0];
	
	    if (tipoConsulta == 'cliente') {
	      form.idCliente.value = codigoRegistro;
	      form.nomeCliente.value = descricaoRegistro;
	      
	      form.action = "exibirAlterarVinculoClientesImovelContasAction.do?pesquisar=2";
	    }
	    
		if (tipoConsulta == 'imovel') {
	      form.idImovel.value = codigoRegistro;
	      form.inscricaoImovel.value = descricaoRegistro;
	   	
	   	  form.action = "exibirAlterarVinculoClientesImovelContasAction.do?pesquisar=1";
	   	}
	   	
	   	form.submit();    
	}

	function duplicaDataInicioRelacao(form) {
		form.dataFinal.value = form.dataInicio.value;
	}
	
	function facilitador(objeto){
	    if (objeto.id == "0" || objeto.id == undefined){ 
	        objeto.id = "1";
	        marcarTodosRegistros();
	    }
	    else{
	        objeto.id = "0";
	        desmarcarTodosRegistros();
	    }
	}


	function marcarTodosRegistros(){

		for (var i=0;i < document.forms[0].elements.length;i++){
			var elemento = document.forms[0].elements[i];
			if (elemento.type == "checkbox" && elemento.disabled == false && elemento.name == "idsRegistro"){
				elemento.checked = true;
			}
		}
	}

	
	function desmarcarTodosRegistros() {

		for (var i=0;i < document.forms[0].elements.length;i++){
			var elemento = document.forms[0].elements[i];
			if (elemento.type == "checkbox" && elemento.name == "idsRegistro"){
				elemento.checked = false;
			}
		}
	}
	
	function validarIndicadorNomeConta(){

	    var form = document.forms[0];
	    
	    var indice;
	    var array = new Array(form.indicadorNomeConta.length);
	    var selecionado = "";
	    var formulario = document.forms[0]; 
	    for(indice = 0; indice < form.elements.length; indice++){
		   if (form.elements[indice].type == 'radio' && form.elements[indice].checked == true && formulario.elements[indice].name == "indicadorNomeConta") {
		      selecionado = form.elements[indice].value;
		      indice = form.elements.length;
		   }
	    }    
		if(selecionado == ''){
			alert('Informe Nome na Conta.');
			indicadorNomeConta.focus();
		}	
	}

	function limpaDadosImovel(){
		var form = document.forms[0];

		form.inscricaoImovel.value = "";
		form.nomeClienteUsuarioAtual.value = "";
		form.situacaoAgua.value = "";
		form.situacaoEsgoto.value = "";
	}

</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">
<div id="formDiv">
<html:form action="/alterarVinculoClientesImovelContaAction"
    name="ExibirAlterarVinculoClientesImovelContasActionForm"
    type="gcom.gui.faturamento.conta.ExibirAlterarVinculoClientesImovelContasActionForm"
	method="post" onsubmit="return validateExibirAlterarVinculoClientesImovelContasActionForm(this);">
	
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
			<table align="center" border="0" cellpadding="0" cellspacing="0"
				width="100%">
				<tbody>
					<tr>
						<td width="11"><img src="imagens/parahead_left.gif" border="0"></td>
						<td class="parabg">Alterar Vínculo de Clientes com Imóvel e Contas</td>
						<td valign="top" width="11"><img src="imagens/parahead_right.gif"
							border="0"></td>
					</tr>
				</tbody>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<%-- Início --%>
			<table border="0" width="100%">
				<tr>
					<td colspan="2">
						<table bgcolor="#99ccff" border="0" bordercolor="#99ccff"
							cellpadding="1" cellspacing="0" width="100%">
							<!--header da tabela interna -->
							<tbody>
								<tr bgcolor="#cbe5fe">
									<td>
									<div align="left">Para alterar o vínculo de clientes com o imóvel e contas,
										informe os dados abaixo:</div>
									</td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
			</table>
			
			<table border="0" width="100%">
				<tr>
					<td width="25%"><strong>Matrícula do Imóvel:<font color="#FF0000">*</font></strong></td>
					<td width="75%"><html:text maxlength="9"
						name="ExibirAlterarVinculoClientesImovelContasActionForm"
						property="idImovel" size="9"
						onkeypress="return isCampoNumerico(event);"
						onkeyup="javascript:limpaDadosImovel();return validaEnterComMensagem(event, 'exibirAlterarVinculoClientesImovelContasAction.do?pesquisar=1', 'idImovel', 'Imóvel');" />
					<a
						href="javascript:abrirPopup('exibirPesquisarImovelAction.do', 400, 800);">
					<img width="23" height="21"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						border="0" title="Pesquisar Imóvel" /></a> 
					<logic:present name="imovelNaoEncontrado" scope="request" >
						<html:text size="30" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							name="ExibirAlterarVinculoClientesImovelContasActionForm"
							property="inscricaoImovel" />
					</logic:present>
					<logic:notPresent name="imovelNaoEncontrado" scope="request" >
						<html:text size="30" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"
							name="ExibirAlterarVinculoClientesImovelContasActionForm"
							property="inscricaoImovel" />
					</logic:notPresent>
					<a href="javascript:limpar(document.forms[0]);"> <img
						border="0" src="imagens/limparcampo.gif" height="21" width="23" title="Apagar Imóvel" >
					</a></td>
				</tr>
				<tr>
					<td width="25%"><strong>Cliente Usuário Atual:</strong></td>
					<td width="75%"><html:text 
						name="ExibirAlterarVinculoClientesImovelContasActionForm"
						property="nomeClienteUsuarioAtual"
						style="background-color:#EFEFEF; border:0;" 
						readonly="true" size="40" />
				</tr>
				<tr>
					<td width="25%"><strong>Situação de Água:</strong></td>
					<td width="75%"><html:text 
						name="ExibirAlterarVinculoClientesImovelContasActionForm"
						style="background-color:#EFEFEF; border:0;"
						property="situacaoAgua"
						readonly="true" size="20" />
				</tr>
				<tr>
					<td width="25%"><strong>Situação de Esgoto:</strong></td>
					<td width="75%"><html:text
						name="ExibirAlterarVinculoClientesImovelContasActionForm"
						style="background-color:#EFEFEF; border:0;"
						property="situacaoEsgoto"
						readonly="true" size="20" />
				</tr>
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>
			</table>
			
			<table border="0" width="100%">
				<tr>
					<td width="25%"><strong>Código do Cliente:<font color="#FF0000">*</font></strong></td>
					<td width="75%"><html:text maxlength="9"
						name="ExibirAlterarVinculoClientesImovelContasActionForm"
						property="idCliente" size="9"
						onkeypress="return isCampoNumerico(event);"
						onkeyup="return validaEnterComMensagem(event, 'exibirAlterarVinculoClientesImovelContasAction.do?pesquisar=2', 'idCliente', 'Cliente');" />
					<a href="javascript:abrirPopupDeNome('exibirPesquisarClienteAction.do?indicadorUsoTodos=1', 400, 800, 'Cliente','yes');">
						<img width="23" height="21"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							border="0" title="Pesquisar Cliente" /></a> 
					<logic:present name="clienteNaoEncontrado" scope="request" >
						<html:text name="ExibirAlterarVinculoClientesImovelContasActionForm"
							property="nomeCliente" size="40" maxlength="40" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present>
					<logic:notPresent name="clienteNaoEncontrado" scope="request" >
						<html:text name="ExibirAlterarVinculoClientesImovelContasActionForm"
							property="nomeCliente" size="40" maxlength="40" readonly="true"
							style="border: 0pt none ; background-color: rgb(239, 239, 239);" />
					</logic:notPresent>
					<a href="javascript:limparCliente();"> <img
						border="0" src="imagens/limparcampo.gif" height="21" width="23" title="Limpar Cliente" >
					</a> 
					</td>
				</tr>
				<tr>
					<td><strong>Tipo da Relação:<font color="#FF0000">*</font></strong></td>
					<td>
						<html:select property="tipoRelacao" >
				        	<logic:notEmpty name="colecaoClienteRelacaoTipo" scope="request">
					          	<html:option value="<%=ConstantesSistema.NUMERO_NAO_INFORMADO+""%>">&nbsp;</html:option>
					            <html:options collection="colecaoClienteRelacaoTipo" labelProperty="descricao" property="id" />
				            </logic:notEmpty>
				       </html:select>
				       <input name="inserir" class="bottonRightCol"
							value="Inserir Cliente" type="button" onclick="javascript:inserirCliente();">
					</td>
				</tr>
				<tr>
					<td><strong>Data Início do Vínculo:<font color="#FF0000">*</font></strong></td>
					<td><html:text
						name="ExibirAlterarVinculoClientesImovelContasActionForm"
							property="dataInicio" size="10" maxlength="10" 
							onkeypress="return isCampoNumerico(event);"
							onkeyup="mascaraData(this, event);" />&nbsp;dd/mm/aaaa
					</td>
				</tr>
				<tr>
					<td><strong>Data Fim do Vínculo:</strong></td>
					<td><html:text
						name="ExibirAlterarVinculoClientesImovelContasActionForm"
							property="dataFinal" size="10" maxlength="10" 
							onkeypress="return isCampoNumerico(event);"
							onkeyup="mascaraData(this, event);" />&nbsp;dd/mm/aaaa
					</td>
				</tr>
				<tr>
					<td><strong>Motivo Fim do Vínculo:</strong></td>
					<td>
						<html:select property="motivoFim" >
				        	<logic:notEmpty name="colecaoMotivoFimRelacao" scope="request">
					          	<html:option value="<%=ConstantesSistema.NUMERO_NAO_INFORMADO+""%>">&nbsp;</html:option>
					            <html:options collection="colecaoMotivoFimRelacao" labelProperty="descricao" property="id" />
				            </logic:notEmpty>
				       </html:select>
					</td>
				</tr>
				<tr>
					<td><strong>Nome na Conta:<font color="#FF0000">*</font></strong></td>
					<td>
						<html:radio property="indicadorNomeConta" value="1" ><strong>Sim</strong></html:radio>
						<html:radio property="indicadorNomeConta" value="2" ><strong>Não</strong></html:radio>
					</td>
				</tr>
				<tr>
	                <td>&nbsp;</td>
	                <td><strong><font color="#FF0000">*</font></strong> Campos
	                obrigat&oacute;rios</td>
                </tr>
                <tr>
                	<td>&nbsp;</td>
                </tr>
			</table>               
            
             <table width="100%">
             	<tr>
					<td align="left"><strong>Cliente(s) Vinculado(s)</strong></td>
					<td align="right">
						<input name="Button3" 
							   type="button"
							   class="bottonRightCol" 
							   value="Adicionar" 
							   onClick="javascript:adicionar();" tabindex="9" />
					</td>
				</tr> 
             </table>
            
             <!-- Tabela -->
             <logic:present name="colecaoClienteImovelHelper" scope="session">
		     	<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td colspan="2">
							<div style="width: 100%; height: 170px; overflow: auto;">
								<table width="100%" bgcolor="#99CCFF" border="0">
									<tr bordercolor="#000000">
										<td width="7%" bordercolor="#000000" bgcolor="#90c7fc" align="center">
											<div align="center"><strong><a href="javascript:facilitador(this);" id="0">Todos</a></strong></div>
										</td>
										<td width="10%" bordercolor="#000000" bgcolor="#90c7fc" align="center">
											<div align="center"><strong>Data Inicio</strong></div>
									   	</td>
									   	<td width="10%" bordercolor="#000000" bgcolor="#90c7fc" align="center">
											<div align="center"><strong>Data Fim</strong></div>
									   	</td>
									   	<td width="11%" bgcolor="#90c7fc" align="center">
									   		<strong>Código</strong>
									   	</td>
									   	<td width="28%" bgcolor="#90c7fc" align="center">
									  		<strong>Nome</strong>
									   	</td>
									    <td width="16%" bgcolor="#90c7fc" align="center">
									    	<strong>Tipo</strong>
									   	</td>
									   	<td width="8%" bgcolor="#90c7fc" align="center">
									    	<strong>Nome Conta</strong>
									   	</td>
									   	<td width="10%" bgcolor="#90c7fc" align="center">
									    	<strong>Colocar Revisão</strong>
									   	</td>
									</tr>
		                   			<%int cont = 0;%>
			                      	<logic:iterate name="colecaoClienteImovelHelper" id="clienteImovelHelper" scope="session" type="ClienteImovelHelper">
			                        	<%cont = cont + 1;
										if (cont % 2 == 0) {%>
				                        <tr bgcolor="#cbe5fe">
				                       	<%} else {%>
				                        <tr bgcolor="#FFFFFF">
				                        <%}%>
				                            <td width="7%" align="center">
				                            	<div align="center"><input type="checkbox"
													name="idsRegistro"
													value="<%= cont %>">
												</div>	
				                            </td>
				                            <td width="10%" align="center"> 
				                            	<input name="dataInicioRelacao_<%= cont %>" onkeyup="mascaraData(this, event);" style="text-align:right;"
													onkeypress="javascript:return isCampoNumerico(event);" size="9" maxlength="10" type="text"
													value="<bean:write name="clienteImovelHelper" property="dataInicioRelacao"/>" >
				                            </td>
				                            <td width="10%" align="center"> 
				                            	<input name="dataFimRelacao_<%= cont %>" onkeyup="mascaraData(this, event);" style="text-align:right;"
													onkeypress="javascript:return isCampoNumerico(event);" size="9" maxlength="10" type="text"
													value="<bean:write name="clienteImovelHelper" property="dataFimRelacao"/>" >
				                            </td>
			                                <td width="11%" align="center"> 
				                            	<bean:write name="clienteImovelHelper" property="idCliente"/>
				                            </td>
											<td width="28%" align="left" >
				                           		<bean:write name="clienteImovelHelper" property="nomeCliente"/> 
											</td>
											<td width="16%" align="center"> 
				                            	<bean:write name="clienteImovelHelper" property="descricaoRelacaoTipo"/>
				                            </td>
											<td width="8%" align="center"> 
												<% if ( clienteImovelHelper.getIndicadorNomeConta().equals("1")) {%>
													<input name="indicadorNomeConta_<%= cont %>" type="checkbox" checked="checked" value="${clienteImovelHelper.indicadorNomeConta}" >
												<%} else {%>
													<input name="indicadorNomeConta_<%= cont %>" type="checkbox" value="${clienteImovelHelper.indicadorNomeConta}" >
												<%} %>
											</td>
											<td width="10%" align="center"> 
												<% if ( clienteImovelHelper.getIndicadorRevisao().equals("1")) {%>
													<input name="indicadorRevisao_<%= cont %>" type="checkbox" checked="checked" value="${clienteImovelHelper.indicadorRevisao}" >
												<%} else {%>
													<input name="indicadorRevisao_<%= cont %>" type="checkbox" value="${clienteImovelHelper.indicadorRevisao}" >
												<%} %>
											</td>
			                  		  	</tr>
			                    	</logic:iterate>
			                	</table>
		          			</div>
			          	</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>
							<table>
								<tr>
									<td width="65" align="left">
										<input name="Button3" 
											   type="button"
											   class="bottonRightCol" 
											   value="Remover" 
											   align="left"
											   onClick="javascript:remover(document.forms[0]);" tabindex="9" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</logic:present>	
				
			<table width="100%" cellpadding="0" cellspacing="0">
                <tr>
					<td colspan="3">
					<hr>
					</td>
				</tr>
				
				<tr>
					<td class="style1" colspan="2">
						<input name="cancelar" class="bottonRightCol"
							value="Cancelar" type="button" onClick="javascript:document.forms[0].target='';window.location.href='/gsan/telaPrincipal.do'" >
						<input name="limpar" class="bottonRightCol"
							value="Limpar" type="button" onClick="javascript:document.forms[0].target='';window.location.href='/gsan/exibirAlterarVinculoClientesImovelContasAction.do?menu=sim'" >
					</td>
					<td align="right" class="style1">
						<gsan:controleAcessoBotao name="Button" value="Alterar Vínculos"
						  onclick="javascript:alterarVinculos();" url="alterarVinculoClientesImovelContaAction.do"/>
					</td>
				</tr>
			</table>
			
			<p class="style1">&nbsp;</p>
			<%-- Fim --%></td>
		
		</tr>
		<%@ include file="/jsp/util/rodape.jsp"%>
	</table>
</html:form>
</div>
<%@ include file="/jsp/util/telaespera.jsp"%>
</body>
</html:html>
