
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/functions' prefix='fn' %>

<%@ include file="/jsp/util/telaespera.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page isELIgnored="false"%>
<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
	
	<%@ page import="gsan.atualizacaocadastral.bean.AtualizacaoCadastralArquivoTextoHelper "%>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="ConsultarRoteiroDispositivoMovelActionForm"
	dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<style>
.styleFontePequena{font-size:9px;
                   color: #000000;
				   font:Verdana, Arial, Helvetica, sans-serif}
.styleFontePeqNegrito{font-size:11px;
                   color: #000000;
				   font-weight: bold}
</style>

<script language="JavaScript">

	function validaForm(form){
		if(validateConsultarRoteiroDispositivoMovelActionForm){
			botaoAvancarTelaEspera('/gsan/consultarRoteiroDispositivoMovelAction.do');
		}
	}
	
	function finalizar(form) {
		if(CheckboxNaoVazio()){
			botaoAvancarTelaEspera('/gsan/validarAtualizacaoCadastralArquivoTextoAction.do?liberar=3');
	 	}
	}
  
  	function leiturista(form) {
		if(CheckboxNaoVazio()){
			abrirPopup('exibirAlterarLeituristaRoteiroDispositivoMovelAction.do', '210', '580');
		}
	}
  
  	function emCampo(form) {
		if(CheckboxNaoVazio()){
			botaoAvancarTelaEspera('/gsan/validarAtualizacaoCadastralArquivoTextoAction.do?liberar=2');
	  	}
  	}
  
  	function liberar(form) {
		if(CheckboxNaoVazio()){
			botaoAvancarTelaEspera('/gsan/validarAtualizacaoCadastralArquivoTextoAction.do?liberar=1');
		}
  	}
  
  	function naoLiberar(form) {
		if(CheckboxNaoVazio()){
			botaoAvancarTelaEspera('/gsan/validarAtualizacaoCadastralArquivoTextoAction.do?liberar=0');
		}
	}
      
  	function facilitador(objeto){
		if (objeto.id == "0" || objeto.id == undefined){
			objeto.id = "1";
			marcarTodos();
		}
		else{
			objeto.id = "0";
			desmarcarTodos();
		}
	}
	
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){

		if (objeto == null || codigoObjeto == null){
			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		}
		else{
			if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
				alert(msg);
			}
			else{
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto , altura, largura);
			}
		}
	}
	
	function chamarPopupQuadra() {
	  	var form = document.ConsultarRoteiroDispositivoMovelActionForm;
	     if(form.idLocalidade.value == ""){
			alert(" Informe Localidade ");      
	     }else if(form.codigoSetorComercial.value == ""){
	     	alert(" Informe SetorComercial "); 
	     }else{
		    abrirPopup('exibirPesquisarQuadraAction.do?consulta=sim&idLocalidade='+form.idLocalidade.value+'&codigoSetorComercial='+form.codigoSetorComercial.value, 275, 480);	
		}
  	}

	function CheckboxNaoVazio(campo){
	  form = document.forms[0];
	  retorno = false;
		
	  for(indice = 0; indice < form.elements.length; indice++){
		if (form.elements[indice].type == "checkbox" && form.elements[indice].checked == true) {
			retorno = true;
			break;
		}
	  }
		
	  if (!retorno){
		alert('Informe o(s) arquivo(s) desejado(s).');
	  }
		
	  return retorno;
	} 

	function extendeTabela(display){
		var form = document.forms[0];

		if(display){
		  	eval('layerHideDadosArquivos').style.display = 'none';
 			eval('layerShowDadosArquivos').style.display = 'block';
		}else{
		  	eval('layerHideDadosArquivos').style.display = 'block';
 			eval('layerShowDadosArquivos').style.display = 'none';
		}
	}
	
	function verificaTabela(achou){
		if (achou == '2'){
		 	eval('layerHideDadosArquivos').style.display = 'block';
			eval('layerShowDadosArquivos').style.display = 'none';
		}else if (achou == '1'){
			eval('layerHideDadosArquivos').style.display = 'none';
			eval('layerShowDadosArquivos').style.display = 'block';
		}
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

		var form = document.forms[0];

		if (tipoConsulta == 'localidade') {
      		
      		form.idLocalidade.value = codigoRegistro;
	  		form.descricaoLocalidade.value = descricaoRegistro;
      		
	  		form.descricaoLocalidade.style.color = "#000000";
	  		
	  		form.codigoSetorComercial.focus();
	  		
		} else if (tipoConsulta == 'setorComercial') {
			
			form.codigoSetorComercial.value = codigoRegistro;
			form.descricaoSetorComercial.value = descricaoRegistro;
			
			form.descricaoSetorComercial.style.color = "#000000";
			
			form.numeroQuadra.focus();
		
		} else if (tipoConsulta == 'quadra') {
			
			form.numeroQuadra.value = codigoRegistro;
			form.descricaoQuadra.value = codigoRegistro;
			
			form.descricaoQuadra.style.color = "#000000";
			
			form.cadastrador.focus();
			
		} else {
			
			botaoAvancarTelaEspera('/gsan/validarAtualizacaoCadastralArquivoTextoAction.do?liberar=4&idNovoLeiturista='+codigoRegistro);
			
		}
	}
	
	function limparPesquisa(tipo){
		var form = document.forms[0];
		
		switch(tipo){
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
	
	function limparPesquisaLocalidade() {
		var form = document.forms[0];
		
		form.idLocalidade.value = "";
		form.descricaoLocalidade.value = "";
		form.codigoSetorComercial.value = "";
		form.descricaoSetorComercial.value = "";
		form.numeroQuadra.value = "";
		form.descricaoQuadra.value = "";
	}
	
	function limparPesquisaSetor() {
		var form = document.forms[0];
		
		form.codigoSetorComercial.value = "";
		form.descricaoSetorComercial.value = "";
		form.numeroQuadra.value = "";
		form.descricaoQuadra.value = "";
	}
	
	function limparPesquisaQuadra(){
		var form = document.forms[0];
		
		form.numeroQuadra.value = "";
		form.descricaoQuadra.value = "";
	}
	
</script>


</head>

<body leftmargin="5" topmargin="5" onload="verificaTabela('<%=session.getAttribute("achou")%>');">
<div id="formDiv">  
<html:form action="/consultarRoteiroDispositivoMovelAction"
	name="ConsultarRoteiroDispositivoMovelActionForm"
	type="gsan.gui.cadastro.ConsultarRoteiroDispositivoMovelActionForm"
	method="post"
	onsubmit="return validateConsultarRoteiroDispositivoMovelActionForm(this);">

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
					<td class="parabg">Consultar Roteiro do Dispositivo Móvel</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="6">Para consultar os arquivos de roteiro para
					dispositivo móvel, informe os dados abaixo:</td>
					<td align="right"></td>
				</tr>

				<tr>
					<td><strong>Localidade:<font color="#ff0000">*</font></strong></td>
		            <td height="24" colspan="2">
                   		<html:text maxlength="3" 
                   				   property="idLocalidade" 
                   				   size="3"  
                   				   tabindex="7"
                   				   onkeyup="javascript:limparPesquisa(1);"
                   				   onkeypress="javascript:validaEnterComMensagem(event, 'exibirConsultarRoteiroDispositivoMovelAction.do', 'idLocalidade', 'Localidade'); return isCampoNumerico(event);"
                   		/>
                      	<a href="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do', 400, 800);">
                        	<img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar Localidade"/></a>
							
	   		      		<logic:present name="localidadeInexistente" scope="request">
							<html:text property="descricaoLocalidade" 
                        			   size="30" 
                        			   readonly="true" 
                        			   style="background-color:#EFEFEF; border:0; color: #ff0000"
                        	/>
	                     </logic:present>
	                     <logic:notPresent name="localidadeInexistente" scope="request">
                        	<html:text property="descricaoLocalidade" 
                        			   size="30" 
                        			   readonly="true" 
                        			   style="background-color:#EFEFEF; border:0; color: #000000"
                        	/>
	                     </logic:notPresent>
	                     
						<a href="javascript:limparPesquisaLocalidade();document.forms[0].idLocalidade.focus();"> <img
						   src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						   border="0" title="Apagar Localidade" /></a>                   
					</td>
				</tr>
				
				<tr>
					<td><strong>Setor Comercial:</strong></td>
		            <td height="24" colspan="2">
                   		<html:text maxlength="3" 
                   				   property="codigoSetorComercial" 
                   				   size="3"  
                   				   tabindex="7"
                   				   onkeyup="javascript:limparPesquisa(2);"
                   				   onkeypress="javascript:validaEnterDependenciaComMensagem(event, 'exibirConsultarRoteiroDispositivoMovelAction.do', this , document.forms[0].idLocalidade.value , 'Localidade', 'Setor Comercial'); return isCampoNumerico(event);"
                   		/>
                      	<a href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'codigoSetorComercial', 'idLocalidade', document.ConsultarRoteiroDispositivoMovelActionForm.idLocalidade.value, 275, 480, 'Informe a Localidade');">
                        	<img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar Setor Comercial"/></a>
							
	   		      		<logic:present name="setorComercialInexistente" scope="request">
							<html:text property="descricaoSetorComercial" 
                        			   size="30" 
                        			   readonly="true" 
                        			   style="background-color:#EFEFEF; border:0; color: #ff0000"
                        	/>
	                     </logic:present>
	                     <logic:notPresent name="setorComercialInexistente" scope="request">
                        	<html:text property="descricaoSetorComercial" 
                        			   size="30" 
                        			   readonly="true" 
                        			   style="background-color:#EFEFEF; border:0; color: #000000"
                        	/>
	                     </logic:notPresent>
	                     
						<a href="javascript:limparPesquisaSetor();document.forms[0].codigoSetorComercial.focus();"> <img
						   src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						   border="0" title="Apagar Setor Comercial" /></a>                   
					</td>
				</tr>
				
				<tr>
					<td><strong>Quadra:</strong></td>
		            <td height="24" colspan="2">
                   		<html:text maxlength="3" 
                   				   property="numeroQuadra" 
                   				   size="3"  
                   				   tabindex="7"
                   				   onkeyup="javascript:limparPesquisa(3);"
                   				   onkeypress="javascript:validaEnterDependenciaComMensagem(event, 'exibirConsultarRoteiroDispositivoMovelAction.do', this , document.forms[0].codigoSetorComercial.value , 'Setor Comercial', 'Quadra'); return isCampoNumerico(event);"
                   		/>
                      	<a href="javascript:chamarPopupQuadra();">
                        	<img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar Quadra"/></a>
							
	   		      		<logic:present name="quadraInexistente" scope="request">
							<html:text property="descricaoQuadra" 
                        			   size="20" 
                        			   readonly="true" 
                        			   style="background-color:#EFEFEF; border:0; color: #ff0000"
                        	/>
	                     </logic:present>
	                     <logic:notPresent name="quadraInexistente" scope="request">
                        	<html:text property="descricaoQuadra" 
                        			   size="20" 
                        			   readonly="true" 
                        			   style="background-color:#EFEFEF; border:0; color: #000000"
                        	/>
	                     </logic:notPresent>
	                     
						<a href="javascript:limparPesquisaQuadra();document.forms[0].numeroQuadra.focus();"> <img
						   src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						   border="0" title="Apagar Quadra" /></a>                   
					</td>
				</tr>

				<tr>

					<td><strong>Cadastrador: </strong></td>
					<td colspan="2" align="left">
					
						<html:select property="cadastrador"	tabindex="4">
						
						<html:option value="-1">&nbsp;</html:option>
						
								<c:if test="${not empty colecaoLeiturista}">
									<c:forEach items="${colecaoLeiturista}" var="leiturista">
										<c:if test="${not empty leiturista.usuario}">
											<option <c:if test="${leiturista.id eq cadastradorSelecionado}">
												selected=true;
											</c:if>
											value="${leiturista.id}">${leiturista.usuario.nomeUsuario}</option>
										</c:if>
									</c:forEach>
								</c:if>
										
						</html:select>	
								
					
					</td>

				</tr>
			
				
				<tr>
					<td><strong>Situação Texto para Leitura:</strong></td>
					<td>
					<html:select property="situacaoArquivo"
						tabindex="4">
						<html:option value="">TODOS</html:option>
						<html:options collection="colecaoSituacaoTransmissao"
							labelProperty="descricaoSituacao" property="id" />
					</html:select></td>
					
				</tr>
				<tr>
					<td></td>
				</tr>
				
				<tr>
					<td colspan="2" >
					<!-- <input name="Button" type="button" class="bottonRightCol"
						value="Desfazer" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirConsultarRoteiroDispositivoMovelAction.do?menu=sim"/>'">
					<input type="button" name="ButtonCancelar" class="bottonRightCol"
						value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"></td> -->
					<td align="right"><input name="Button" type="button" class="bottonRightCol"
						value="Selecionar" align="left" onclick="validaForm(document.forms[0]);" ></td>
				</tr>
				
				
				
				
					<tr>
	             		<td colspan="7"><hr /></td>
	             	</tr>
				
				
				<td colspan="7">
					<font color="#000000" style="font-size:10px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Arquivos Textos para Leitura:</strong></font></td>
				<tr>
					<td colspan="7" height="23"> 
						 <input type="button" name="Botao" value="Liberar" onclick="liberar(document.forms[0]);"
							tabindex="13" class="bottonRightCol" />
					     <input type="button" name="Botao" value="Não Liberar" onclick="naoLiberar(document.forms[0]);"
							tabindex="13" class="bottonRightCol" />
						  
						 <!-- <input type="button" name="Botao" value="Em Campo" onclick="emCampo(document.forms[0]);"
							tabindex="13" class="bottonRightCol" /> -->
								
						 <input type="button" name="Botao" value="Finalizar" 
						  	onclick="finalizar(document.forms[0]);"
							tabindex="13" class="bottonRightCol" />						
						 <input type="button" name="Botao" value="Informar Cadastrador"
						  	onclick="leiturista(document.forms[0]);"
							tabindex="13" class="bottonRightCol" />					
					 </td>
				</tr>

				<tr>
					<td colspan="5" bgcolor="#000000" height="2" valign="baseline"></td>
				</tr>
				<tr>
				 <td colspan="5" width="100%">
				 	<div id="layerHideDadosArquivos" style="display:block">
               				<table width="100%" border="0" bgcolor="#99CCFF">
		    					<tr bgcolor="#99CCFF">
                      				<td align="center">
                     					<span class="style2">
                     					<a href="javascript:extendeTabela(true);"/>
                      						<b>Dados dos Arquivos</b>
                      					</a>
                     					</span>
                      				</td>
                     			</tr>
                     			
                    		</table>
                    		<p>&nbsp;</p>
                     			<p>&nbsp;</p>
                     			<p>&nbsp;</p>
           			</div>
				 </td>
				</tr>
				

				<tr>
					<td width="100%" colspan="5">
				
			   <div id="layerShowDadosArquivos" style="display:none">
				<table width="100%" align="center" bgcolor="#90c7fc" border="0" cellpadding="0" cellspacing="0">
					<tr bgcolor="#99CCFF" >
                   		<td align="center">
           					<span class="style2">
             					<a href="javascript:extendeTabela(false);"/>
             						<b>Dados dos Arquivos</b>
             					</a>
           					</span>
               			</td>
              		</tr>
              		
					<tr bgcolor="#cbe5fe" >
						<td width="100%" align="center">
						 <table width="100%" bgcolor="#99CCFF" border="0">
							<tr bordercolor="#000000" bgcolor="#90c7fc" class="styleFontePeqNegrito">
								<td width="10%" bgcolor="#90c7fc">
								<div align="center" ><strong><a
									href="javascript:facilitador(this);">Todos</a></strong></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
								<div align="center"><strong>Localidade</strong></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
								<div align="center"><strong>Setor Comercial</strong></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
								<div align="center"><strong>Quadra</strong></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
								<div align="center"><strong>Qtde Env</strong></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
								<div align="center"><strong>Qtde Rec</strong></div>
								</td>
								<td width="15%" bgcolor="#90c7fc">
								<div align="center"><strong>Cadastrador</strong></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
								<div align="center"><strong>Situação</strong></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
								<div align="center"><strong>Liberação</strong></div>
								</td>
							</tr>
						</table>

						<div style="height:300px;overflow:auto">
						<table width="100%" bgcolor="#99CCFF">
							<tr bordercolor="#000000" bgcolor="#90c7fc">
								<logic:present name="colecaoArquivoTextoRoteiroDispositivoMovel">
									<%int cont = 0;%>
									<logic:iterate name="colecaoArquivoTextoRoteiroDispositivoMovel"
										id="atualizacaoCadastralArquivoTextoHelper" type="AtualizacaoCadastralArquivoTextoHelper" >
										<!-- <pg:item>  -->
										<%cont = cont + 1;
										if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe" class="styleFontePequena">
											<%} else {%>
										<tr bgcolor="#FFFFFF" class="styleFontePequena">
											<%}%>
											<td width="10%">
											<div align="center">
												<html:checkbox property="idsRegistros" value="${atualizacaoCadastralArquivoTextoHelper.id}" />
											</div>
											</td>
											
											<td width="10%" align="center">
												<c:choose>
													<c:when test='${atualizacaoCadastralArquivoTextoHelper.idSituacaoArquivo != null 
																		&& atualizacaoCadastralArquivoTextoHelper.idSituacaoArquivo == 2 }'>
														<a href="/gsan/transmitirAtualizacaoCadastralArquivoTextoAction.do?idArquivo=${atualizacaoCadastralArquivoTextoHelper.id}&idSituacaoArquivo=${atualizacaoCadastralArquivoTextoHelper.idSituacaoArquivo}" title="Baixar arquivo" > 
															${atualizacaoCadastralArquivoTextoHelper.idLocalidade}
														</a>
													</c:when>
													<c:otherwise>
														${atualizacaoCadastralArquivoTextoHelper.idLocalidade}
													</c:otherwise>
												</c:choose>
											</td>
											
											<td width="10%" align="center">${atualizacaoCadastralArquivoTextoHelper.codigoSetorComercial}</td>
											
											<td width="10%" align="center">
												<c:choose>
													<c:when test='${fn:length(atualizacaoCadastralArquivoTextoHelper.colecaoQuadras) > 1}'>
														<a	href="javascript:abrirPopup('exibirQuadrasSelecionadasPopUpAction.do?idParametro=${atualizacaoCadastralArquivoTextoHelper.idParametro}', 400, 800);">
															Várias
														</a>
													</c:when>
													<c:otherwise>
														<c:forEach  items="${atualizacaoCadastralArquivoTextoHelper.colecaoQuadras}" var="item">
															 <c:out value="${item}"/>
														</c:forEach>
														
													</c:otherwise>
												</c:choose>
											</td>
											
											<td width="10%" align="center">
											    <a href="javascript:abrirPopup('exibirImoveisSelecionadosPopUpAction.do?idParametro=${atualizacaoCadastralArquivoTextoHelper.idParametro}', 400, 800);">
											          ${atualizacaoCadastralArquivoTextoHelper.quantidadeEnviada}
											    </a>
											</td>
													
											<td width="10%" align="center">
												<% if(atualizacaoCadastralArquivoTextoHelper.getQuantidadeRecebida() == null || atualizacaoCadastralArquivoTextoHelper.getQuantidadeRecebida().equals("0")) { %>
													${atualizacaoCadastralArquivoTextoHelper.quantidadeRecebida}
												<% } else { %>
													<a href="javascript:abrirPopup('exibirDadosCadastradorSelecionadosPopUpAction.do?idParametro=${atualizacaoCadastralArquivoTextoHelper.idParametro}&total=${atualizacaoCadastralArquivoTextoHelper.quantidadeRecebida}', 400, 800);">
														${atualizacaoCadastralArquivoTextoHelper.quantidadeRecebida}
													</a>
												<% } %>
											</td>
										
											<td width="15%" align="center">${atualizacaoCadastralArquivoTextoHelper.nomeUsuario}</td>											
											
											<td width="10%" align="center">
												<% if(atualizacaoCadastralArquivoTextoHelper.getDataFinalizacaoArquivo().equals("")) { %>
													${atualizacaoCadastralArquivoTextoHelper.situacaoArquivo}
												<% } else { %>
													<span title="Data de Finalização: ${atualizacaoCadastralArquivoTextoHelper.dataFinalizacaoArquivo}">${atualizacaoCadastralArquivoTextoHelper.situacaoArquivo}</span>
												<% } %>
											</td>
											
											<td width="10%" align="center">${atualizacaoCadastralArquivoTextoHelper.dataLiberacao}</td>

										</tr>
										
									</logic:iterate>
								</logic:present>
							</table>
						</div>
					</tr>
					
					
					
				</table>
			  </div></td></tr>
			  
					<tr>
	             		<td colspan="7"><hr /></td>
	             	</tr>
	             	
	             	<tr>
					<td colspan="2" ><input name="Button" type="button" class="bottonRightCol"
						value="Desfazer" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirConsultarRoteiroDispositivoMovelAction.do?menu=sim"/>'">
					<input type="button" name="ButtonCancelar" class="bottonRightCol"
						value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"></td>
					<!-- <td align="right"><input name="Button" type="button" class="bottonRightCol"
						value="Selecionar" align="left" onclick="validaForm(document.forms[0]);" ></td> -->
				</tr>
			</table>
			<p>&nbsp;</p>
		</tr>
		
		
	</table>
	<p>&nbsp;</p>

	<tr>

	</tr>

<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</div>
</body>
</html:html>

