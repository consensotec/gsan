<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.atualizacaocadastral.bean.AtualizacaoCadastralLogradouroHelper"%>
<%@ page import="gcom.atualizacaocadastral.LogradouroAtlzCadDM"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
	<head>
		<%@ include file="/jsp/util/titulo.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
		
		<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
		<html:javascript staticJavascript="false"  formName="InserirNovosLogradourosAtualizacaoCadastralActionForm"/>
		
		<script language="JavaScript">
		
			function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {	
				var form = document.forms[0];
				if (tipoConsulta == 'localidade') {
			      form.idLocalidade.value = codigoRegistro;
			      form.nomeLocalidade.value = descricaoRegistro;
				  form.nomeLocalidade.style.color = "#000000";
				}else{
					form.idLogradouroSubstituto.value = codigoRegistro;
			  		
					form.action = "exibirInserirNovosLogradourosAtualizacaoCadastralAction.do?substituirLogradouro=true"
					form.submit();					
				}	
			}
			
			function limparLocalidade(){
				var form = document.forms[0];				
				form.idLocalidade.value = "";
				form.nomeLocalidade.value = "";
			}
			
			function selecionarLogradouros(){
				var form = document.forms[0];
				if(validateInserirNovosLogradourosAtualizacaoCadastralActionForm(form)){
					form.action = "exibirInserirNovosLogradourosAtualizacaoCadastralAction.do?pesquisarLogradouros=true";
					form.submit();
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
			
			function checkboxNaoVazio(campo){
			  form = document.forms[0];
			  retorno = false;
			  
			  for(indice = 0; indice < form.elements.length; indice++){
				if (form.elements[indice].type == "checkbox" && (form.elements[indice].checked == true || form.elements[indice].disabled == true)) {
					form.elements[indice].checked = true;
					retorno = true;
					break;
				}
			  }
				
			  if (!retorno){
				alert('É obrigatório informar pelo menos um logradouro, para atualizar ou para substituir.');
			  }
				
			  return retorno;
			}
			
			function abrirPopUpGrid(){
				var form = document.forms[0];

				if(checkboxNaoVazio()){
					abrirPopup('exibirPesquisarLogradouroAction.do?indicadorUsoTodos=1', 250, 495);
				}
			}
			
			function atualizarLogradouros(){
				var form = document.forms[0];
				if(checkboxNaoVazio()){
					
					form.action = "inserirNovosLogradourosAtualizacaoCadastralAction.do?atualizarLogradouro=true";
					form.submit();
				}
			}
			
			function gerarRelatorio(){
				var form = document.forms[0];
				if(validateInserirNovosLogradourosAtualizacaoCadastralActionForm(form)){
					toggleBox('demodiv', 1);
				}
			}
						
		</script>
	
	</head>

	<body leftmargin="5" topmargin="5" onload="resizePageSemLink(790, 1020);">
	<div id="formDiv">
		<html:form action="/inserirNovosLogradourosAtualizacaoCadastralAction.do"
					name="InserirNovosLogradourosAtualizacaoCadastralActionForm"
					type="gcom.gui.atualizacaocadastral.InserirNovosLogradourosAtualizacaoCadastralActionForm" 
					method="post">
					
			<%@ include file="/jsp/util/cabecalho.jsp"%>
			<%@ include file="/jsp/util/menu.jsp" %>
			
			<html:hidden property="idLogradouroSubstituto" />
			
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
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
							<tr>
								<td width="11"><img border="0"
									src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
			
								<td class="parabg">Incluir Novo Logradouro - Atualização Cadastral</td>
								
								<td width="11"><img border="0"
									src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
							</tr>
						</table>
						<p>&nbsp;</p>
						<table width="100%" border="0">
							<tr>
								<td>Para adicionar um logradouro, informe os dados abaixo:</td>
							</tr>
						</table>
						&nbsp;
						<table width="100%" border="0">
							<tr>
								<td>
									<strong>Empresa:<font color="#FF0000">*</font></strong>
								</td>
								<td>
									<html:select property="idEmpresa" tabindex="1" >
										<html:option value="">&nbsp;</html:option>
										<html:options collection="colecaoEmpresa" 
										labelProperty="descricao" property="id" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td>
									<strong>Localidade:<font color="#FF0000">*</font></strong>
								</td>
								<td>
									<html:text property="idLocalidade" size="5" maxlength="3" tabindex="2" 
									onkeypress="validaEnterComMensagem(event, 'exibirInserirNovosLogradourosAtualizacaoCadastralAction.do?objetoConsulta=1', 'idLocalidade','Localidade');return isCampoNumerico(event);"/>								
									
									<a href="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do?indicadorUsoTodos=1', 250, 495);">
										<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Localidade" /></a>
									
									<logic:present name="localidadeEncontrada" scope="session">
										<html:text property="nomeLocalidade" 
											size="30"
											maxlength="30" 
											readonly="true"
											style="background-color:#EFEFEF;border:0;"/>
									</logic:present>
									
									<logic:notPresent name="localidadeEncontrada" scope="session">
										<html:text property="nomeLocalidade"  
											readonly="true"
											style="background-color:#EFEFEF; border:0; " 
											size="30" 
											maxlength="30" />
									</logic:notPresent> 

									<a	href="javascript:limparLocalidade();">
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /> 
									</a>
								</td>
							</tr>
							<tr>
								<td />
								<td align="right">
									<input type="button" class="bottonRightCol" value="Selecionar"
										tabindex="3" style="width: 80px; align: right;"
										onclick="selecionarLogradouros();" name="botaoSelecLogradouros">
								</td>
							</tr>
							<tr>
								<td width="100%" colspan="7">
								<table width="100%" cellpadding="0" cellspacing="0">
									<tr bgcolor="#99CCFF">
				                   		<td align="center" colspan="7">
				           					<span class="style2">
				             					<b>Novos Logradouros</b>
				           					</span>
				               			</td>
				              		</tr>
									<tr bordercolor="#000000" bgcolor="#90c7fc" class="styleFontePeqNegrito">
										<td >
											<div align="center" style="height:30px;"><strong><a
											href="javascript:facilitador(this);">Todos</a></strong></div>
										</td>
										<td width="20">
										<div align="center"><strong>Imóveis</strong></div>
										</td>
										<td >
										<div align="center"><strong>Tipo</strong></div>
										</td>
										<td >
										<div align="center"><strong>Título</strong></div>
										</td>
										<td >
										<div align="center"><strong>Nome</strong></div>
										</td>
										<td >
										<div align="center"><strong>Bairro</strong></div>
										</td>
										<td >
										<div align="center"><strong>Município</strong></div>
										</td>
									</tr>		

									<%String cor = "#cbe5fe";%>
									<logic:present name="colecaoLogradouroHelper">
										<logic:iterate name="colecaoLogradouroHelper" id="logradourohelper" 
														type="AtualizacaoCadastralLogradouroHelper" >
											<%if (cor.equalsIgnoreCase("#cbe5fe")) {
													cor = "#FFFFFF";
											%>
											<tr bgcolor="#FFFFFF">
												<%} else {
													cor = "#cbe5fe";%>							
											<tr bgcolor="#cbe5fe">
												<%}%>
												<td align="center" valign="middle" >
													<c:choose>	
														<c:when test="${logradourohelper.idSubstituirLogra == null || logradourohelper.idSubstituirLogra == ''}">
															<html:checkbox property="idsRegistros" value="${logradourohelper.logradouroAtlzCad.id}" />
														</c:when>
													
														<c:otherwise>														
															<html:checkbox property="idsRegistros" value="${logradourohelper.logradouroAtlzCad.id}" disabled="true" />													
														</c:otherwise>												
													</c:choose>			 	
												</td>
												<td>
													<div align="center">
														<logic:notEmpty name="logradourohelper" property="logradouroAtlzCad.logradouro">
															<bean:write name="logradourohelper" property="logradouroAtlzCad.logradouro.id" /> 
														</logic:notEmpty>
														<logic:empty name="logradourohelper" property="logradouroAtlzCad.logradouro">
															<bean:write name="logradourohelper" property="idSubstituirLogra" /> 
															
															<a href="javascript:abrirPopup('exibirImoveisPorLogradouroPopUpAction.do?idLogradouro=${logradourohelper.logradouroAtlzCad.id}', 400, 800);">
																<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>atencao.gif" title="Imoveis Selecionados" /></a>
														</logic:empty>
													</div>
												</td>
												<td>											
													<logic:notEmpty name="logradourohelper" property="logradouroAtlzCad.logradouroTipo">
														<div align="center">
															<bean:define name="logradourohelper" property="logradouroAtlzCad.logradouroTipo" id="logradouroTipo" /> 
															<bean:write name="logradouroTipo" property="descricao" /> 
														</div>
													</logic:notEmpty>
												</td>	
												<td>											
													<logic:notEmpty name="logradourohelper" property="logradouroAtlzCad.logradouroTitulo">
														<div align="center">
															<bean:define name="logradourohelper" property="logradouroAtlzCad.logradouroTitulo" id="logradouroTitulo" /> 
															<bean:write name="logradouroTitulo" property="descricao" /> 
														</div>
													</logic:notEmpty>
												</td>
												<td>											
													<div align="left">
														<c:choose>	
															<c:when test="${logradourohelper.idSubstituirLogra == null || logradourohelper.idSubstituirLogra == ''}">
																<a	href="javascript:abrirPopup('exibirAtualizarLogradouroAtualizacaoCadastralPopUpAction.do?idRegistroAtualizacao=${logradourohelper.logradouroAtlzCad.id}');">
																	<bean:write name="logradourohelper" property="logradouroAtlzCad.nome" />
																</a>
															</c:when>
														
															<c:otherwise>														
																<bean:write name="logradourohelper" property="logradouroAtlzCad.nome"  />													
															</c:otherwise>												
														</c:choose>	
													</div>
												</td>
												<td>
													<div align="left" >
														<bean:write  name="logradourohelper" property="bairros" filter="false"/>
													</div>										
												</td>
												<td>
													<logic:notEmpty name="logradourohelper" property="logradouroAtlzCad.municipio">
														<div align="center">
																<bean:define name="logradourohelper" property="logradouroAtlzCad.municipio" id="municipio" /> 
																<bean:write name="municipio" property="nome" />
														</div>
													</logic:notEmpty>									
												</td>
											</tr>											
										</logic:iterate>
									</logic:present>
								</table>
								</td>
							</tr>
						</table>
						<table width="100%" style="height: 165px;">
							<tr>
								<td align="left" width="40%">
									<input name="Button" type="button" class="bottonRightCol"
											value="Desfazer" align="left"
											onclick="window.location.href='<html:rewrite page="/exibirInserirNovosLogradourosAtualizacaoCadastralAction.do?menu=sim"/>'">
									<input type="button" name="ButtonCancelar" class="bottonRightCol"
											value="Cancelar"
											onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
								</td>
								<td align="right">
									<input type="button" class="bottonRightCol" value="Substituir Logradouros"
										tabindex="3" style="width: 140px; align: right;"
										onclick="abrirPopUpGrid();" name="botaoSubstituir">
									<input type="button" class="bottonRightCol" value="Imprimir"
										tabindex="3" style="width: 80px; align: right;"
										onclick="gerarRelatorio();" name="botaoImprimir">
									<input type="button" class="bottonRightCol" value="Atualizar"
										tabindex="3" style="width: 80px; align: right;"
										onclick="atualizarLogradouros();" name="botaoAtlzLogradouros">
								</td>
							</tr>				
						</table>
					</td>		
				</tr>
			</table>	
			<%@ include file="/jsp/util/rodape.jsp"%>	
			<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio_tela_espera.jsp?relatorio=gerarRelatorioNovosLogradouros.do" />
		</html:form>
		</div>
		<%@ include file="/jsp/util/telaespera.jsp"%>
	</body>

</html:html>