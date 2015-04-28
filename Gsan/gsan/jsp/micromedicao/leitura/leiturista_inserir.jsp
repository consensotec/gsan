<%@page import="org.hibernate.Session"%>
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
	formName="InserirLeituristaActionForm" />

<SCRIPT LANGUAGE="JavaScript">

	function limparFuncionario(){
		var form = document.forms[0];
	
		form.idFuncionario.value = "";
		form.descricaoFuncionario.value = "";
		form.idFuncionario.focus();
		form.empresaID.value = "";
		bloqueiaDados();
		form.action = "exibirInserirLeituristaAction.do?limparFuncionario=sim";
      	form.submit();
	}
	
	function limparCliente(){
		var form = document.forms[0];
	
		form.idCliente.value = "";
		form.descricaoCliente.value = "";  
		form.idCliente.focus();
		bloqueiaDados();
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
    	//var form= document.InserirHistoricoAlteracaoSistemaActionForm;
    	
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
		} else if ( tipoConsulta == 'funcionario' ){
  	      	form.idFuncionario.value = codigoRegistro;
	      	form.descricaoFuncionario.value = descricaoRegistro;
	      	form.descricaoFuncionario.style.color = '#000000';
	      	bloqueiaDados();
	      	form.action = "exibirInserirLeituristaAction.do";
	      	form.submit();
		} else if (tipoConsulta = 'unidadeOrganizacional'){
			form.unidadeOrganizacionalId.value = codigoRegistro;
	    	form.unidadeOrganizacionalDescricao.value = descricaoRegistro;
	    	form.unidadeOrganizacionalDescricao.style.color = '#000000';
		}
    }	
    
	function limparUsuario() {
		var form = document.forms[0];
		
      	form.loginUsuario.value = "";
	    form.nomeUsuario.value = "";
		form.loginUsuario.focus();
	}    

	function validarForm(){
		var form = document.InserirLeituristaActionForm;
		if(validateInserirLeituristaActionForm(form)){
	       	submeterFormPadrao(form);
	    }
	}
		
	function limparForm() {
		var form = document.InserirLeituristaActionForm;
		form.idFuncionario.value = "";
		form.descricaoFuncionario.value = "";
	    form.empresaID.value = "";
	    form.idCliente.value = "";
		form.descricaoCliente.value = "";
	    form.ddd.value = "";
	    form.telefone.value = "";
	    form.indicadorAgenteComercial.value = "";
	    form.loginUsuario = "";
	    form.nomeUsuario = "";
	    bloqueiaDados();
	}
	
	
	function isIMEI (s) {
		var etal = /^[0-9]{15}$/;
	 	if (!etal.test(s))
	    	return false;
		sum = 0; mul = 2; l = 14;
		for (i = 0; i < l; i++) {
			digit = s.substring(l-i-1,l-i);
		    tp = parseInt(digit,10)*mul;
		    if (tp >= 10)
		    	sum += (tp % 10) +1;
		    else
		        sum += tp;
		    if (mul == 1)
		        mul++;
		    else
		        mul--;
		}
	  	chk = ((10 - (sum % 10)) % 10);
	  	if (chk != parseInt(s.substring(14,15),10))
	    	return false;
	  	return true;
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
			form.idCliente.disabled = true;
		}else if(form.idCliente.value != null && form.idCliente.value != ''){
			form.idFuncionario.value = "";
			form.idFuncionario.disabled = true;
		}else{
			form.idFuncionario.disabled = false;
			form.idCliente.disabled = false;
		}
	}
			
   	 
  	function remover(objetoRemocao){
  		redirecionarSubmit("/gsan/exibirInserirLeituristaAction.do?removerComponente=" + objetoRemocao);
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

	function chamarPopupFuncionario(){
		var form = document.forms[0];
		if (!form.idFuncionario.disabled)
			abrirPopup('exibirFuncionarioPesquisar.do?limpaForm=S');
	}

	function chamarPopupCliente(){
		var form = document.forms[0];
		if (!form.idCliente.disabled)
			abrirPopup('exibirPesquisarClienteAction.do?menu=sim');
	}

	function limparUnidade(){
		var form = document.forms[0];
 		form.unidadeOrganizacionalId.value = "";
     	form.unidadeOrganizacionalDescricao.value = "";
     	form.unidadeOrganizacionalId.focus();
	}
	
</SCRIPT>


</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}'); bloqueiaDados();">


<html:form action="/inserirLeituristaAction" name="InserirLeituristaActionForm" type="gsan.gui.cadastro.InserirLeituristaActionForm" method="post">

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

			<td width="615" valign="top" class="centercoltext">

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
					<td class="parabg">Inserir Leiturista</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para adicionar um Leiturista, informe os dados abaixo:
				</td>

				<%-- 					
				<tr>
					<td><strong>Código do Leiturista Responsável:<font color="#FF0000">*</font></strong></td>
					<td><html:text property="codigo" size="6" maxlength="6" tabindex="4" /></td>
				</tr>
				--%>
				
				<tr>
					<td WIDTH="25%"><strong>Funcionário:</strong></td>
					<td width="75%" colspan="3">
						<html:text property="idFuncionario" size="9" maxlength="9" tabindex="4"
							onkeypress="validaEnterComMensagem(event, 'exibirInserirLeituristaAction.do', 'idFuncionario', 'Funcionário');" 
							onkeyup="javascript:verificaNumeroInteiro(this); bloqueiaDados();" onchange="javascript:verificaNumeroInteiro(this);" >
						</html:text> 
						<a href="javascript:chamarPopupFuncionario();">
							<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" height="21" alt="Pesquisar" border="0">
						</a> 
						
						<logic:present name="funcionarioEncontrado">
							<logic:equal name="funcionarioEncontrado" value="exception">
								<html:text property="descricaoFuncionario" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>

							<logic:notEqual name="funcionarioEncontrado" value="exception">
								<html:text property="descricaoFuncionario" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present> 
						
						<logic:notPresent name="funcionarioEncontrado">
							<logic:empty name="InserirLeituristaActionForm" property="idFuncionario">
								<html:text property="descricaoFuncionario" value="" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty name="InserirLeituristaActionForm" property="idFuncionario">
								<html:text property="descricaoFuncionario" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent> 
						
						<a href="javascript:limparFuncionario();"> 
							<img src="<bean:message key='caminho.imagens'/>limparcampo.gif" title="Apagar" border="0">
						</a>
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
					<td>
						<strong>Indicador Agente Comercial:<font color="#FF0000">*</font></strong>
					</td>
					<td>
						<strong> 
							<html:radio property="indicadorAgenteComercial" value="1" /> Sim 
							<html:radio property="indicadorAgenteComercial" value="2" /> N&atilde;o 
						</strong>
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
					<td><strong>Cliente:</strong></td>
					<td colspan="3">
						<html:text property="idCliente" size="9" maxlength="9" tabindex="4"
							onkeypress="validaEnterComMensagem(event, 'exibirInserirLeituristaAction.do', 'idCliente','Cliente'); "
							onkeyup="javascript:verificaNumeroInteiro(this); bloqueiaDados();" onchange="javascript:verificaNumeroInteiro(this);">
						</html:text>

						<a href="javascript:chamarPopupCliente();">
							<img src="/gsan/imagens/pesquisa.gif" alt="Pesquisar Cliente" border="0" height="21" width="23">
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
							<logic:empty name="InserirLeituristaActionForm" property="idCliente">
								<html:text property="descricaoCliente" size="45" maxlength="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>

							<logic:notEmpty name="InserirLeituristaActionForm" property="idCliente">
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
					<td>
						<html:text property="ddd" size="2" maxlength="2" tabindex="4"  onkeyup="javascript:verificaNumeroInteiro(this);" onchange="javascript:verificaNumeroInteiroComAlerta(this,'Código DDD');"/>
					</td>
				</tr>

				<tr>
					<td><strong>Número Telefone:<font color="#FF0000">*</font></strong></td>
					<td>
						<html:text property="telefone" size="8" maxlength="8" tabindex="4"  onkeyup="javascript:verificaNumeroInteiro(this);" onchange="javascript:verificaNumeroInteiroComAlerta(this,'Número de Telefone');"/>
					</td>
				</tr>

				<tr>
					<td><strong>Número do IMEI:<font color="#FF0000">*</font></strong></td>
					<td>
						<html:text property="numeroImei" size="15" maxlength="15" tabindex="4" onkeyup="isIMEI();verificaNumeroInteiro(this);" onchange="javascript:verificaNumeroInteiroComAlerta(this,'Número do IMEI');"/>
					</td>
				</tr>
				
	            <tr> 
	              <td><strong>Login do usuário:</strong></td>
	              <td colspan="3"><span class="style2"><strong>
						<html:text property="loginUsuario"  size="11" maxlength="11" tabindex="4" style="text-transform: none;" 
							onkeypress="javascript:pesquisaEnterSemUpperCase(event, 'exibirInserirLeituristaAction.do', 'loginUsuario'); ">
						</html:text>
						
						<a href="javascript:chamarPopup('exibirUsuarioPesquisar.do?limpaForm=S', 'usuario', null, null, 370, 600, '',document.forms[0].loginUsuario);">
							<img src="/gsan/imagens/pesquisa.gif" alt="Pesquisar Usuário" border="0" height="21" width="23">
						</a> 
						
						<logic:present name="funcionalidadeEncontrada">
							<logic:equal name="funcionalidadeEncontrada" value="exception">
								<html:text property="nomeUsuario" size="45" maxlength="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>

							<logic:notEqual name="funcionalidadeEncontrada" value="exception">
									<html:text property="nomeUsuario" size="45" maxlength="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>
						</logic:present> 
						
						<logic:notPresent name="funcionalidadeEncontrada">
							<logic:empty name="InserirLeituristaActionForm" property="loginUsuario">
								<html:text property="nomeUsuario" size="45" maxlength="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							<logic:notEmpty name="InserirLeituristaActionForm" property="loginUsuario">
								<html:text property="nomeUsuario" size="45" maxlength="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEmpty>
						</logic:notPresent> 
						
						<a href="javascript:limparUsuario()"> 
							<img border="0" title="Apagar" src="<bean:message key='caminho.imagens'/>limparcampo.gif" />
						</a>
					</td>
	           	</tr>
	           	
	           	<tr>
					<td width="270"><strong>Unidade Organizacional:</strong> </td>
					<td colspan="3">
						<html:text property="unidadeOrganizacionalId" size="11" maxlength="9" tabindex="15"
							onkeypress="validaEnterComMensagem(event, 'exibirInserirLeituristaAction.do', 'unidadeOrganizacionalId','Unidade Organizacional');return isCampoNumerico(event);"/> 
							<a href="javascript:abrirPopup('exibirPesquisarUnidadeOrganizacionalAction.do?menu=sim',275,480);">
								<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Distrito Operacional" />
							</a> 
						<logic:present name="unidadeOrganizacionalIdEncontrada" scope="session">
	                    	<html:text property="unidadeOrganizacionalDescricao" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" size="45" maxlength="45" />
	                    </logic:present>
	                    <logic:notPresent name="unidadeOrganizacionalIdEncontrada" scope="session">
	                    	<html:text property="unidadeOrganizacionalDescricao" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" size="45" maxlength="45" />
						</logic:notPresent> 
						
						<a href="javascript:limparUnidade();"> <img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /> </a>
					</td>
				</tr>				

				<tr>
					<td></td>
					<td><font color="#FF0000">*</font>&nbsp;Campos obrigat&oacute;rios</td>
				</tr>

				<tr>
					<td>
						<input type="button" name="ButtonLimpar"  class="bottonRightCol" value="Limpar" align="left" onclick="window.location.href='<html:rewrite page="/exibirInserirLeituristaAction.do?limparForm=S"/>'">
						<input type="button" name="ButtonCancelar" class="bottonRightCol" value="Cancelar" onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>
					<td align="right"><%--<INPUT type="button" class="bottonRightCol" value="Inserir" tabindex="13" style="width: 70px;" onclick="validarForm(document.forms[0]);">--%>
			<%-- 
		  	A taglib vai substituir o botão, as propriedades requeridas da tag são :
		  	1-name -> O nome do botão.
		  	2-value -> A descrição do botão. 
		  	3-onclick -> a função javascript que vai ser chamada no click do botão.
		  	4-url -> a url doaction da operação para verificar se o usário logado tem acesso a operação.
		  	
		  	As propriedades NÃO requeridas da tag são:
		  	1-tabindex -> mesma função do input
		  	2-align -> mesma função do input
		  	--%> 		
		  				<gsan:controleAcessoBotao name="Botao" value="Inserir" onclick="validarForm(document.forms[0]);" url="inserirLeituristaAction.do" tabindex="13" />
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

