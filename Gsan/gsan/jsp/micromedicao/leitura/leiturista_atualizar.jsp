<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="AtualizarLeituristaActionForm" />

<SCRIPT LANGUAGE="JavaScript">

	function chamarPopup(url,altura, largura,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
			abrirPopup(url, altura, largura);
		}
			
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

	function voltar(){
		var form = document.forms[0];
		form.action = "exibirManterLeituristaAction.do";
		submeterFormPadrao(form);
	}

			
    
	function limparCliente(){
		var form = document.forms[0];
		form.idCliente.value = "";
		form.descricaoCliente.value = "";
		bloqueiaDados();
	}
	
	function limparFuncionario(){
		var form = document.forms[0];
		form.idFuncionario.value = "";
		form.descricaoFuncionario.value = "";
		bloqueiaDados();
		form.action = "exibirAtualizarLeituristaManterAction.do?limparFuncionario=sim";
      	form.submit();
	}

	function limparDescricao(){
		var form = document.forms[0];
		form.descricaoFuncionario.value = "";
	}
	
	function limparDescricaoCliente(){
		var form = document.forms[0];
		form.descricaoCliente.value = "";
	}

	function limparNomeUsuario(){
		var form = document.forms[0];
		form.nomeUsuario.value = "";
	}
	
    function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    	var form= document.forms[0];
    	if ( tipoConsulta == 'cliente' ){
	      	form.idCliente.value = codigoRegistro;  
	      	form.descricaoCliente.value = descricaoRegistro;
	      	form.descricaoCliente.style.color = "#000000"; 
	      	 bloqueiaDados(); 
	    } else if ( tipoConsulta == 'usuario' ){
  	      	form.loginUsuario.value = codigoRegistro;
	      	form.nomeUsuario.value = descricaoRegistro;
	      	form.nomeUsuario.style.color = '#000000';
	      	 bloqueiaDados();
		}else if ( tipoConsulta == 'funcionario' ){
  	      	form.idFuncionario.value = codigoRegistro;
	      	form.descricaoFuncionario.value = descricaoRegistro;
	      	form.descricaoFuncionario.style.color = '#000000';
	      	bloqueiaDados();
	      	form.action = "exibirAtualizarLeituristaManterAction.do?pesquisa=funcionario";
	      	form.submit();
		}else if (tipoConsulta = 'unidadeOrganizacional'){
			form.unidadeOrganizacionalId.value = codigoRegistro;
	    	form.unidadeOrganizacionalDescricao.value = descricaoRegistro;
	    	form.unidadeOrganizacionalDescricao.style.color = '#000000';
		}
    }	
    
	/* Limpar Usuário */
	function limparUsuario() {
		var form = document.forms[0];
		
      	form.loginUsuario.value = "";
	    form.nomeUsuario.value = "";
	
		form.loginUsuario.focus();
		bloqueiaDados();
	}

	/* Limpar Unidade Organizacional */
	function limparUnidade(){
		var form = document.forms[0];
 		form.unidadeOrganizacionalId.value = "";
     	form.unidadeOrganizacionalDescricao.value = "";
	}    

	function validarForm(){
		var form = document.forms[0];
		if(validateAtualizarLeituristaActionForm(form)){
			if((form.idFuncionario.value != null && form.idFuncionario.value != "") ||(form.idCliente.value != null && form.idCliente.value != "")){
	       		submeterFormPadrao(form);
	       	}else{
	       		alert("Selecione um Funcionário ou um Cliente");
	       	}
	    }
	}
	
	function validaNumeroDocumento() {
		var form = document.forms[0];		
		if (!form.numeroDocumento1[""].checked
				&& !form.deferimento[1].checked) {
			alert("Informe Numero do Documento.");		
			return false;
		}		
		if (!form.idCodigo1[0].checked
				&& !form.idCodigo1[""].checked) {
			alert("Informe Motivo.");		
			return false;
		}		
		return true;
   	}

	function bloqueiaDados(){
  		var form = document.forms[0];
		if (form.idFuncionario.value != null && form.idFuncionario.value != ''){
			form.idCliente.value = "";
			form.idCliente.style.color = "#000000";
			form.idCliente.readOnly = true;
			form.idCliente.style.backgroundColor = '#EFEFEF';
		}else if(form.idCliente.value != null && form.idCliente.value != ''){
			form.idFuncionario.value = "";
			form.idFuncionario.style.color = "#000000";
			form.idFuncionario.readOnly = true;
			form.idFuncionario.style.backgroundColor = '#EFEFEF';
		}else{
			form.idFuncionario.style.color = "#000000";
			form.idFuncionario.readOnly = false;
			form.idFuncionario.style.backgroundColor = '';
			form.idCliente.style.color = "#000000";
			form.idCliente.readOnly = false;
			form.idCliente.style.backgroundColor = '';
		}
  	}

  	function chamarPopupFuncionario(){
		var form = document.forms[0];
		if (!form.idFuncionario.readOnly)
			abrirPopup('exibirFuncionarioPesquisar.do?limpaForm=S, 495, 300,document.forms[0].idFuncionario');
	}

	function chamarPopupCliente(){
		var form = document.forms[0];
		if (!form.idCliente.readOnly)
			abrirPopup('exibirPesquisarClienteAction.do');
	}	

</SCRIPT>


</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}'); bloqueiaDados();">


<html:form action="/atualizarLeituristaAction"
	name="AtualizarLeituristaActionForm"
	type="gsan.gui.cadastro.AtualizarLeituristaActionForm" method="post">

	<INPUT TYPE="hidden" name="removerEndereco">
	<INPUT TYPE="hidden" name="limparCampos" id="limparCampos">


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

			<td width="600" valign="top" class="centercoltext">

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
					<td class="parabg">Atualizar Leiturista</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para Atualizar um Leiturista, informe os dados
					abaixo:</td>
				</tr>	
 					
				<tr>
					<td><strong>Código do Leiturista:<font color="#FF0000">*</font></strong></td>
					<td><html:text property="id" size="6" maxlength="6" tabindex="4" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/></td>
				</tr>

				<tr>
					<td><strong>Funcionário :</strong></td>
					<td colspan="3">
						<html:text property="idFuncionario"  size="9" maxlength="9" tabindex="2"
							onkeypress="limparDescricao(); validaEnterComMensagem(event, 'exibirAtualizarLeituristaManterAction.do?pesquisa=funcionario', 'idFuncionario','Funcionario');return isCampoNumerico(event);" 
							onkeyup="javascript:verificaNumeroInteiro(this); javascript:bloqueiaDados();" onchange="javascript:verificaNumeroInteiroComAlerta(this,'Funcionario');" >
						</html:text>

						<a href="javascript:chamarPopupFuncionario();">
							<img src="/gsan/imagens/pesquisa.gif" alt="Pesquisar Funcionario" border="0" height="21" width="23" title="Pesquisar Funcionário">
						</a> 
						
						<logic:present name="funcionalidadeEncontrada">
							<logic:equal name="funcionalidadeEncontrada" value="exception">
								<html:text property="descricaoFuncionario" size="45" maxlength="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
							<logic:notEqual name="funcionalidadeEncontrada" value="exception">
								<html:text property="descricaoFuncionario" size="45" maxlength="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present> 
						
						<logic:notPresent name="funcionalidadeEncontrada">
							<logic:empty name="AtualizarLeituristaActionForm" property="idFuncionario">
								<html:text property="descricaoFuncionario" size="45" maxlength="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty name="AtualizarLeituristaActionForm" property="idFuncionario">
								<html:text property="descricaoFuncionario" size="45" maxlength="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent> 
						
						<a href="javascript:limparFuncionario()"> 
							<img border="0" title="Apagar" src="<bean:message key='caminho.imagens'/>limparcampo.gif" /> 
						</a>
					</td>
				</tr>

				<tr>
					<td><strong>Cliente:</strong></td>
					<td colspan="3">
						<html:text property="idCliente" size="9" maxlength="9" tabindex="2"
							onkeypress="validaEnterComMensagem(event, 'exibirAtualizarLeituristaManterAction.do?pesquisa=cliente', 'idCliente','Cliente');return isCampoNumerico(event); limparDescricaoCliente();"
							onkeyup="javascript:verificaNumeroInteiro(this); javascript:bloqueiaDados();" onchange="javascript:verificaNumeroInteiroComAlerta(this,'Cliente');" >
						</html:text>
									
						<a href="javascript:chamarPopupCliente();">
							<img src="/gsan/imagens/pesquisa.gif" alt="Pesquisar Cliente" border="0" height="21" width="23" title="Pesquisar Cliente">
						</a> 
						
						<logic:present name="funcionalidadeEncontrada">
							<logic:equal name="funcionalidadeEncontrada" value="exception">
								<html:text property="descricaoCliente" size="45" maxlength="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>
							<logic:notEqual name="funcionalidadeEncontrada" value="exception">
								<html:text property="descricaoCliente" size="45" maxlength="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present>
						
						<logic:notPresent name="funcionalidadeEncontrada">
							<logic:empty name="AtualizarLeituristaActionForm" property="idCliente">
								<html:text property="descricaoCliente" size="45" maxlength="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty name="AtualizarLeituristaActionForm" property="idCliente">
								<html:text property="descricaoCliente" size="45" maxlength="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent>
						
						<a href="javascript:limparCliente()">
							<img border="0" title="Apagar" src="<bean:message key='caminho.imagens'/>limparcampo.gif" /> 
						</a>
					</td>
				</tr>

				<tr>
					<td><strong>Código do DDD do Município:<font color="#FF0000">*</font></strong></td>
					<td><html:text property="ddd" size="2" maxlength="2" tabindex="4" onkeypress="return isCampoNumerico(event);"/>
					</td>
				</tr>

				<tr>
					<td><strong>Número Telefone:<font color="#FF0000">*</font></strong></td>
					<td>
						<html:text property="telefone" size="8" maxlength="8" tabindex="4" onkeypress="return isCampoNumerico(event);"/>
					</td>
				</tr>
				
				<tr>
					<td><strong>Empresa:<font color="#FF0000">*</font></strong></td>
					<td colspan="2" align="left">
					
					<c:if test="${bloquearEmpresa == null}">
						<html:select property="empresaID">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoEmpresa" labelProperty="descricao" property="id" />
						</html:select>
					</c:if>
					
					<c:if test="${bloquearEmpresa != null}">
						
						<html:select property="empresaID" disabled="true">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoEmpresa" labelProperty="descricao" property="id" />
						</html:select>
					</c:if>
					
					</td>
				</tr>
				
				<tr>
					<td><strong>Número do IMEI:<font color="#FF0000">*</font></strong></td>
					<td>
						<html:text property="numeroImei" size="15" maxlength="15" tabindex="4" onkeypress="return isCampoNumerico(event);"/>
		   			</td>
				</tr>
				
				<tr>
        			<td><strong>Indicador Agente Comercial:<font color="#FF0000">*</font></strong></td>
        			<td>
						<html:radio property="indicadorAgenteComercial" value="1"/><strong> Sim
						<html:radio property="indicadorAgenteComercial" value="2"/> N&atilde;o</strong>
					</td>
      			</tr>
	            
	            <tr>
					<td>
						<strong>Indicador Atualização Cadastral:<font color="#FF0000">*</font></strong>
					</td>
					<td>
						<strong> 
							<html:radio property="indicadorAtualizacaoCadastral" value="1" /> Sim 
							<html:radio property="indicadorAtualizacaoCadastral" value="2" /> N&atilde;o 
						</strong>
					</td>
				</tr>
	            
	            <tr> 
	            	<td><strong>Login do usuário:</strong></td>
	              	<td colspan="6">
	              		<span class="style2"><strong>
							<html:text property="loginUsuario" size="11" maxlength="11" style="text-transform: none;"
								onkeypress="limparNomeUsuario(); javascript:pesquisaEnterSemUpperCase(event, 'exibirAtualizarLeituristaManterAction.do?pesquisa=login', 'loginUsuario');">
							</html:text>
							
							<a href="javascript:chamarPopup('exibirUsuarioPesquisar.do?limpaForm=S', 'usuario', null, null, 370, 600, '',document.forms[0].loginUsuario);">
								<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"	height="21" name="imagem" alt="Pesquisar" border="0" title="Pesquisar Usuário">
							</a>
								 
							<logic:present name="usuarioEncontrado" scope="session">
								<html:text property="nomeUsuario" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" size="45" maxlength="45" />
							</logic:present> 
				
							<logic:notPresent name="usuarioEncontrado" scope="session">
								<html:text property="nomeUsuario" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"  size="45" maxlength="45" />
							</logic:notPresent>
							
							<a href="javascript:limparUsuario();">
								<img border="0" title="Apagar" src="<bean:message key='caminho.imagens'/>limparcampo.gif" />
							</a>
				        </strong></span>
		            </td>
	            </tr>
	            
	            <tr>
					<td width="140"><strong>Unidade Organizacional:</strong> </td>
					<td colspan="3">
						<html:text property="unidadeOrganizacionalId" size="11" maxlength="9" tabindex="15"
							onkeypress="validaEnterComMensagem(event, 'exibirAtualizarLeituristaManterAction.do?pesquisa=unidade', 'unidadeOrganizacionalId','Unidade Organizacional');return isCampoNumerico(event);"/> 
						<a href="javascript:abrirPopup('exibirPesquisarUnidadeOrganizacionalAction.do',275,480);">
							<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Unidade Organizacional" />
						</a> 
						
						<logic:present name="unidadeOrganizacionalIdEncontrada" scope="session">
	                    	<html:text property="unidadeOrganizacionalDescricao" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" size="45" maxlength="45" />
	                    </logic:present>
	                    <logic:notPresent name="unidadeOrganizacionalIdEncontrada" scope="session">
	                    	<html:text property="unidadeOrganizacionalDescricao" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" size="45" maxlength="45" />
						</logic:notPresent> 
						
						<a href="javascript:limparUnidade();"> <img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /></a>
					</td>
				</tr>			

				<tr>
        		<td><strong>Indicador de uso:</strong></td>
        		<td>
					<html:radio property="indicadorUso" value="1"/><strong>Ativo
					<html:radio property="indicadorUso" value="2"/>Inativo</strong>
				</td>
      			</tr>
				
				<tr>
					<td></td>
					<td><font color="#FF0000">*</font>&nbsp;Campos obrigat&oacute;rios</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td colspan="3">
						<input type="button" name="ButtonVoltar" class="bottonRightCol" value="Voltar" onClick="voltar();">
						<input type="button" name="ButtonDesfazer"  class="bottonRightCol" value="Desfazer" align="left" onclick="window.location.href='<html:rewrite page="/exibirAtualizarLeituristaManterAction.do?desfazer=S"/>'">
						<input type="button" name="ButtonCancelar" class="bottonRightCol" value="Cancelar" onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>
					<td align="right">
						<gsan:controleAcessoBotao name="Botao" value="Atualizar" onclick="validarForm(document.forms[0]);" url="atualizarLeituristaAction.do" tabindex="13" />
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>

</body>
</html:html>

