<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="FiltrarLeituristaActionForm" dynamicJavascript="true" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<SCRIPT LANGUAGE="JavaScript">


	function limpar(){

		var form = document.forms[0];
	
		form.idFuncionario.value = "";
		form.descricaoFuncionario.value = "";
			   
	//Coloca o foco no objeto selecionado
		form.idFuncionario.focus();
	}

	function limparDescricao(){
		
		var form = document.forms[0];
		form.descricaoFuncionario.value = "";
	}

	function limparCliente(){
		var form = document.forms[0];

		form.idCliente.value = "";
		form.descricaoCliente.value = "";  
		desbloqueiaFuncionario();
	} 

	function limparDescricaoCliente(){
		var form = document.forms[0];
		form.descricaoCliente.value = "";
		bloqueiaFuncionario();
	}
	
	function limparNomeUsuario(){
		
		var form = document.forms[0];
		form.nomeUsuario.value = "";
	}

    function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    	
    	 var form = document.forms[0];
	  	 
	  	 if ( tipoConsulta == 'cliente' ) {
	      	form.idCliente.value = codigoRegistro;  
	      	form.descricaoCliente.value = descricaoRegistro;
	      	form.descricaoCliente.style.color = "#000000"; 
	      	bloqueiaFuncionario(); 
	     }
	     
	     if ( tipoConsulta == 'funcionario' ) {
	      	form.idFuncionario.value = codigoRegistro;  
	      	form.descricaoFuncionario.value = descricaoRegistro;
	      	form.descricaoFuncionario.style.color = "#000000";
	      	bloqueiaCliente();  
	     }
	     
	     if (tipoConsulta == 'usuario') {
  	      	form.loginUsuario.value = codigoRegistro;
	      	form.nomeUsuario.value = descricaoRegistro;
	      	form.nomeUsuario.style.color = '#000000';
	    }
	     
    }	

	function validaForm(){
		var form = document.FiltrarLeituristaActionForm;
		
		if ( form.empresaID.value != '-1' &&
				form.empresaID.value != '' ) {
			if(validateFiltrarLeituristaActionForm(form)){
		       	submeterFormPadrao(form);
		    }
		} else {
			alert("Informe a Empresa");
		}    
	}
	
	function validaNumeroDocumento() {
		
		var form = document.forms[0];		
		if (!form.numeroDocumento1[""].checked && 
			!form.deferimento[1].checked) {
			
			alert("Informe Numero do Documento.");		
			return false;
		}		
		if (!form.idCodigo1[0].checked && 
			!form.idCodigo1[""].checked) {
			
			alert("Informe Motivo.");		
			return false;
		}		
		return true;
   	}
   	
   	function verificarChecado(valor){
		form = document.forms[0];
		if(valor == "1"){
	 		form.indicadorAtualizar.checked = true;
	 	}else{
	 		form.indicadorAtualizar.checked = false;
		}
	}	
	
	function limparDescricaoFuncionario(){
		var form = document.forms[0];	
		
		form.idFuncionario.value = '';
		form.descricaoFuncionario.value = '';	
		desbloqueiaCliente();
	}
	
	
	
	/* Limpar Usuário */
	function limparUsuario() {
		var form = document.forms[0];
		
      	form.loginUsuario.value = "";
	    form.nomeUsuario.value = "";
	
		form.loginUsuario.focus();
	}
	
	/* Chama Popup */ 
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
	function pesquisarFuncionario(){
		var form = document.forms[0];
		if(form.idFuncionario.readOnly == false){
			abrirPopup('exibirFuncionarioPesquisar.do?menu=sim');
		}
		else{
			alert('Pesquisa Bloqueada')
		}
	}
	function pesquisarCliente(){
		var form = document.forms[0];
		if(form.idCliente.readOnly == false){
			abrirPopup('exibirPesquisarClienteAction.do?menu=sim');
		}
		else{
			alert('Pesquisa Bloqueada')
		}
	}

	function bloqueiaCliente(){
		var form = document.forms[0];
		if(form.idFuncionario.value !=''){
			form.idCliente.readOnly = true;
		}
		else{
			desbloqueiaCliente();
		}
	}
	function desbloqueiaCliente(){
		var form = document.forms[0];
		form.idCliente.readOnly = false;
	}
	
	function bloqueiaFuncionario(){
		var form = document.forms[0];
		if(form.idCliente.value !=''){
			form.idFuncionario.readOnly = true;
		}
		else{
			desbloqueiaFuncionario();
		}
	}
	function desbloqueiaFuncionario(){
		var form = document.forms[0];
		form.idFuncionario.readOnly = false;
	}
	
		
	
</SCRIPT>

</head>

<body leftmargin="5" topmargin="5"
	onload="verificarChecado('${sessionScope.indicadorAtualizar}');">

<html:form action="/filtrarLeituristaAction"
	name="FiltrarLeituristaActionForm"
	type="gsan.gui.micromedicao.leitura.FiltrarLeituristaActionForm"
	method="post"
	onsubmit="return validateFiltrarLeituristaActionForm(this);">

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
			
			<td width="610" valign="top" class="centercoltext">

				<table height="100%">
					<tr>
						<td></td>
					</tr>
				</table>

				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
						<td class="parabg">Filtrar Leiturista</td>
						<td width="11" valign="top">
							<img border="0" src="imagens/parahead_right.gif" />
						</td>
					</tr>
				</table>
				
				<p>&nbsp;</p>
				
				<table width="100%" border="0">
					<tr>
						<td width="100%" colspan=2>
						<table width="100%" border="0">
							<tr>
								<td>Para filtrar um Leiturista, informe os dados abaixo:</td>
								
								<td align="right">
									<input type="checkbox"
										name="indicadorAtualizar" 
										value="1" /><strong>Atualizar</strong>
								</td>
							</tr>
						</table>
						</td>
					</tr>

					<tr>
						<td><strong>Funcionário:</strong></td>
						<td colspan="6">
							<html:text property="idFuncionario" 
								size="9"
								maxlength="9" 
								tabindex="2"
								onkeypress="bloqueiaCliente();limparDescricao(); validaEnterComMensagem(event, 'exibirFiltrarLeituristaAction.do', 'idFuncionario','Funcionario');return isCampoNumerico(event);" 
								onkeyup="javascript:bloqueiaCliente();verificaNumeroInteiro(this);"
								onchange="javascript:bloqueiaCliente();verificaNumeroInteiroComAlerta(this,'Funcionario');"/>

							<a href="javascript:pesquisarFuncionario();">
								<img src="/gsan/imagens/pesquisa.gif" 
									alt="Pesquisar Funcionario"
									border="0" 
									height="21" 
									width="23"
									title="Pesquisar Funcionário">
							</a> 

							<logic:present name="funcionalidadeEncontrada">
								<logic:equal name="funcionalidadeEncontrada" value="exception">
									<html:text property="descricaoFuncionario" 
										size="40"
										maxlength="50" 
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:equal>

								<logic:notEqual name="funcionalidadeEncontrada" value="exception">
									<html:text property="descricaoFuncionario" 
										size="40"
										maxlength="40" 
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEqual>
							</logic:present> 
							
							<logic:notPresent name="funcionalidadeEncontrada">
								<logic:empty name="FiltrarLeituristaActionForm" property="idFuncionario">
									<html:text property="descricaoFuncionario" 
										size="40"
										maxlength="40" 
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:empty>

								<logic:notEmpty name="FiltrarLeituristaActionForm" property="idFuncionario">
									<html:text property="descricaoFuncionario" 
										size="40"
										maxlength="40" 
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEmpty>
							</logic:notPresent> 
							
							<a href="javascript:limparDescricaoFuncionario()"> 
								<img border="0" 
									title="Apagar"
									src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
						</td>
					</tr>

					<tr>
						<td><strong>Empresa:<font color="#FF0000">*</font></strong></td>
						
						<td colspan="1" align="left">
							<c:if test="${bloquearEmpresa == null}">
								<html:select property="empresaID" tabindex="3">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="colecaoEmpresa" 
										labelProperty="descricao" 
										property="id" />
								</html:select>
							</c:if>
						
							<c:if test="${bloquearEmpresa != null}">
								<html:select property="empresaID" tabindex="3" disabled="true">
									<html:option value="-1">&nbsp;</html:option>
									<html:options collection="colecaoEmpresa" 
										labelProperty="descricao" 
										property="id" />
								</html:select>
							</c:if>
						</td>
					</tr>

					<tr>
						<td><strong>Cliente:</strong></td>
						<td colspan="6">
							<html:text property="idCliente" 
								size="9"
								maxlength="9" 
								tabindex="4"
								onkeypress="bloqueiaFuncionario();limparDescricaoCliente(); validaEnterComMensagem(event, 'exibirFiltrarLeituristaAction.do', 'idCliente','Cliente');return isCampoNumerico(event);" 
								onkeyup="javascript:bloqueiaFuncionario();verificaNumeroInteiro(this);"
								onchange="javascript:bloqueiaFuncionario();verificaNumeroInteiroComAlerta(this,'Cliente');"/>
								 
							<a href="javascript:pesquisarCliente();">
								<img src="/gsan/imagens/pesquisa.gif" 
									alt="Pesquisar Cliente"
									border="0" 
									height="21" 
									width="23"
									title="Pesquisar Cliente">
							</a> 

							<logic:present name="funcionalidadeEncontrada">
								<logic:equal name="funcionalidadeEncontrada" value="exception">
									<html:text property="descricaoCliente" 
										size="40" 
										maxlength="50"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:equal>

								<logic:notEqual name="funcionalidadeEncontrada" value="exception">
									<html:text property="descricaoCliente" 
										size="40" 
										maxlength="40"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEqual>
							</logic:present> 
							
							<logic:notPresent name="funcionalidadeEncontrada">

								<logic:empty name="FiltrarLeituristaActionForm" property="idCliente">
									<html:text property="descricaoCliente" 
										size="40" 
										maxlength="40"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:empty>

								<logic:notEmpty name="FiltrarLeituristaActionForm" property="idCliente">
									<html:text property="descricaoCliente" 
										size="40" 
										maxlength="40"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:notEmpty>
							</logic:notPresent> 
							
							<a href="javascript:limparCliente()">
								<img border="0" 
									title="Apagar"
									src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
						</td>
					</tr>

					<tr>
						<td><strong>Código do DDD do Município:</strong></td>
						<td>
							<html:text property="ddd" 
								size="2" 
								maxlength="2" 
								tabindex="5" 
								onkeypress="return isCampoNumerico(event);"
								onkeyup="javascript:verificaNumeroInteiro(this);"
								onchange="javascript:verificaNumeroInteiroComAlerta(this,'Código DDD');"/></td>
					</tr>

					<tr>
						<td><strong>Número Telefone:</strong></td>
						<td>
							<html:text property="telefone" 
								size="9" 
								maxlength="9"
								tabindex="6" 
								onkeypress="return isCampoNumerico(event);"
								onkeyup="javascript:verificaNumeroInteiro(this);"
								onchange="javascript:verificaNumeroInteiroComAlerta(this,'Número de Telefone');"/>
						</td>
					</tr>
					
					<tr>
						<td><strong>Número do IMEI:</strong></td>
						<td>
							<html:text property="imei" 
								size="20" 
								maxlength="20"
								tabindex="7" 
								onkeypress="return isCampoNumerico(event);"
								onkeyup="isIMEI();verificaNumeroInteiro(this);"
								onchange="javascript:verificaNumeroInteiroComAlerta(this,'Número do IMEI');"/>
						</td>
					</tr>
				
					<tr>
						<td><strong>Indicador de Uso:</strong></td>
						<td><strong> 
							<html:radio property="indicadorUso" 
								value="1" 
								tabindex="8"/>Ativos&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							
							<html:radio property="indicadorUso" 
								value="2" 
								tabindex="9"/>Inativos&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							
							<html:radio property="indicadorUso" 
								value="3" 
								tabindex="10"/> Todos</strong> 
						</td>
					</tr>
					
					<tr>
						<td>
							<strong>Indicador Atualização Cadastral:</strong>
						</td>
						<td><strong> 
							<html:radio property="indicadorAtualizacaoCadastral" 
								value="1" 
								tabindex="8"
								styleId="radioSimICATLZCadastral"/>
								<label for="radioSimICATLZCadastral">Sim&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
							
							<html:radio property="indicadorAtualizacaoCadastral" 
								value="2" 
								tabindex="9"
								styleId="radioNaoICATLZCadastral"/>
								<label for="radioNaoICATLZCadastral">Não&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
							
							<html:radio property="indicadorAtualizacaoCadastral" 
								value="3" 
								tabindex="10"
								styleId="radioTodosICATLZCadastral"/>
								<label for="radioTodosICATLZCadastral">Todos</label></strong> 
						</td>
					</tr>
					
		            <tr> 
		              <td><strong>Login do usuário:</strong></td>
		              <td colspan="6"><span class="style2"><strong>
					<html:text property="loginUsuario" 
						size="10" 
						maxlength="11"
						style="text-transform: none;"
						onkeypress="javascript:pesquisaEnterSemUpperCase(event, 'exibirFiltrarLeituristaAction.do', 'loginUsuario'); limparNomeUsuario();"/>
					
					<a href="javascript:chamarPopup('exibirUsuarioPesquisar.do?limpar=S', 'usuario', null, null, 370, 600, '',document.forms[0].loginUsuario);">
						<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"	height="21" name="imagem" alt="Pesquisar Usuário" border="0" title="Pesquisar Usuário"></a>
						 
					<logic:present name="funcionalidadeEncontrada">
						<logic:equal name="funcionalidadeEncontrada" value="exception">
						<html:text property="nomeUsuario" 
							readonly="true"
							size="40" 
							maxlength="40" 
							style="background-color:#EFEFEF; border:0; color: #000000"/>
						</logic:equal>
		
						<logic:notEqual name="funcionalidadeEncontrada" value="exception">
							<html:text property="nomeUsuario" 
								readonly="true"
							    size="40" 
						        maxlength="40" 
						        style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:notEqual>
					</logic:present> 
					
					<logic:notPresent name="funcionalidadeEncontrada">

					<logic:empty name="FiltrarLeituristaActionForm" property="loginUsuario">
						<html:text property="nomeUsuario" 
						     		size="40" 
									maxlength="40"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:empty>
	
					<logic:notEmpty name="FiltrarLeituristaActionForm" property="loginUsuario">
						<html:text property="nomeUsuario" 
									size="40" 
									maxlength="40"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notEmpty>
				</logic:notPresent> 
					
					<a href="javascript:limparUsuario();">
						<img border="0" title="Apagar" src="<bean:message key='caminho.imagens'/>limparcampo.gif" />
					</a>
		                </strong></span></td>
		            </tr>
					
					<tr>
						<td>&nbsp;</td>
					</tr>
					
					<tr>
						<td>
							<input type="button" 
								name="Submit22" 
								class="bottonRightCol"
								value="Cancelar"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
						</td>
						
						<td>
							<input type="button" 
								name="Submit22" 
								class="bottonRightCol"
								value="Limpar"
								onClick="javascript:window.location.href='/gsan/exibirFiltrarLeituristaAction.do?menu=sim'">
						</td>
						<td align="right">
							<input type="button" 
								name="Submit2"
								class="bottonRightCol" 
								value="Filtrar"
								onclick="validaForm(document.forms[0]);"
								url="filtrarLeituristaAction.do" tabindex="13"/>
						</td>
					</tr>
				</table>
				<p>&nbsp;</p>
			</tr>
		<%@ include file="/jsp/util/rodape.jsp"%>
	</table>
	
	<p>&nbsp;</p>

</html:form>
</body>
</html:html>