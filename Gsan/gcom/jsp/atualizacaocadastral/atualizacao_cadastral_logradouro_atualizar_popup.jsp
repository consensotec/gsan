<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="gcom.cadastro.geografico.Bairro" %>
<%@ page import="gcom.cadastro.endereco.Cep" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
	<head>	
		<%@ include file="/jsp/util/titulo.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		
		<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
		<html:javascript staticJavascript="false"  formName="AtualizarLogradouroAtualizacaoCadastralPopUpActionForm"/>
		
		<script LANGUAGE="JavaScript">
			  
			  function limparPesquisaBairro() {
			    var form = document.AtualizarLogradouroAtualizacaoCadastralPopUpActionForm;
			
			    form.codigoBairro.value = "";
			    form.nomeBairro.value = "";			
			  }
			  
			  function limparPesquisaCep(){
			  
			  	var form = document.AtualizarLogradouroAtualizacaoCadastralPopUpActionForm;
			  	
			  	form.codigoCEP.value = "";
			    form.descricaoCEP.value = "";
			  }
			  
			  function limparPesquisaMunicipio() {
			    
			    var form = document.AtualizarLogradouroAtualizacaoCadastralPopUpActionForm;
			
			    form.idMunicipio.value = "";
			    form.nomeMunicipio.value = "";
			    form.codigoBairro.value = "";
			    form.nomeBairro.value = "";
			    form.codigoCEP.value = "";
			    form.descricaoCEP.value = "";
			    
			    form.action = "exibirAtualizarLogradouroAtualizacaoCadastralPopUpAction.do?removerColecaoBairro=OK&removerColecaoCep=OK";
				submeterFormPadrao(form);
				
			  }
			  
			  function validarForm(form){
				if(validateAtualizarLogradouroAtualizacaoCadastralPopUpActionForm(form)){
			    	
			    	if (!testarCampoValorZero(document.forms[0].idMunicipio, "Munic�pio")){
			    		document.forms[0].idMunicipio.focus();
			    	}
			    	/*else if (document.getElementById("bairroCarregado") == null){
			    		alert("Informe Bairro(s)");
			    		document.forms[0].codigoBairro.focus();
			    	}
			    	else if (document.getElementById("cepCarregado") == null){
			    		alert("Informe CEP(s)");
			    		document.forms[0].codigoCEP.focus();
			    	}*/
			    	else{
			    		toUpperCase(form);
			    		document.forms[0].target='';
			    		form.action = 'atualizarLogradouroAtualizacaoCadastralPopUpAction.do?confirmarLogradouroMesmoNome=NAO';
			    		form.submit();
			    	}
			    }
			}
			
			function adicionarObjeto(tipoSelecao){
				var form = document.forms[0];
				var objMunicipio = form.idMunicipio;
				var objBairro = form.codigoBairro; 
				var objCEP = form.codigoCEP;
				
				if (objMunicipio.value.length > 0){
					
					//Bairro
					if (tipoSelecao == "1"){
						redirecionarSubmit('exibirAtualizarLogradouroAtualizacaoCadastralPopUpAction.do?chamarPesquisa=exibirPesquisarBairro');
					}
					//CEP
					else{	
						redirecionarSubmit('exibirAtualizarLogradouroAtualizacaoCadastralPopUpAction.do?chamarPesquisa=exibirPesquisarCep');			
					}
				}
				else {
					alert("Informe Munic�pio");
					objMunicipio.focus();
				}
			}
			
			function adicionarObjetoEnter(tipoSelecao){
				var form = document.forms[0];
				var objMunicipio = form.idMunicipio;
				var objBairro = form.codigoBairro; 
				var objCEP = form.codigoCEP;
			
			
				if (objMunicipio.value.length < 1){
					alert("Informe Munic�pio");
					objMunicipio.focus();
				}
				else if (objMunicipio.value.length > 0 && (isNaN(objMunicipio.value) || objMunicipio.value.indexOf(',') != -1 ||
						 objMunicipio.value.indexOf('.') != -1)){
			
					alert("Munic�pio deve somente conter n�meros positivos.");
					objMunicipio.focus();
				}
				else if (!testarCampoValorZero(document.forms[0].idMunicipio, "Munic�pio")){
			    	document.forms[0].idMunicipio.focus();
			    }
			    else {
			    
			    	//Bairro
					if (tipoSelecao == "1" && objBairro.value.length > 0){
					
						if (!testarCampoValorZero(objBairro, "Bairro(s)")){
							objBairro.focus();
						}
						else if (objBairro.value.length > 0 && (isNaN(objBairro.value) || objBairro.value.indexOf(',') != -1 ||
								objBairro.value.indexOf('.') != -1)){
			
							alert("Bairro(s) deve somente conter n�meros positivos.");
							objBairro.focus();
						}
						else{
							form.action = "exibirAtualizarLogradouroAtualizacaoCadastralPopUpAction.do?adicionarBairroColecao=OK";
						submeterFormPadrao(form);
						}
					}
					
					//CEP
					else if (tipoSelecao == "2" && objCEP.value.length > 0){
						
						if (!testarCampoValorZero(objCEP, "CEP(s)")){
							objCEP.focus();
						}
						else if (objCEP.value.length > 0 && (isNaN(objCEP.value) || objCEP.value.indexOf(',') != -1 ||
								objCEP.value.indexOf('.') != -1)){
			
							alert("CEP(s) deve somente conter n�meros positivos.");
							objCEP.focus();
						}
						else{
							form.action = "exibirAtualizarLogradouroAtualizacaoCadastralPopUpAction.do?adicionarCepColecao=OK";
							submeterFormPadrao(form);
						}
						
					}
			    }
			}
			
			function remover(idObj, idLog,tipoRemocao){
				var form = document.forms[0];
				
				if (tipoRemocao == "1"){
					if (confirm('Confirma Remo��o?')){
						form.action = "exibirAtualizarLogradouroAtualizacaoCadastralPopUpAction.do?idBairro=" + idObj +"&idLogradouro="+ idLog ;
						submeterFormPadrao(form);
					}
				}else{
					if(document.getElementById("cepUnico").value != "OK"){
						if (confirm('Confirma Remo��o?')){
							form.action = "exibirAtualizarLogradouroAtualizacaoCadastralPopUpAction.do?idCepAtualizacaoCadastral=" + idObj +"&idLogradouro="+ idLog ;
							submeterFormPadrao(form);
						}
					}
				}
			}
			
			function fechar(){
				window.opener.location.href='/gsan/exibirInserirNovosLogradourosAtualizacaoCadastralAction.do';
				window.close();
			}
			
			function desabilitarBotao(){
				var form = document.forms[0];
				if ( form.indicadorDesabilitarBotao.value == '1' ) {
					form.BotaoAtualizar.disabled = true;
				} else {
					form.BotaoAtualizar.disabled = false;
				}
			}

			
			function selecionarNovoObjetoBairro(idBairroAtual){
				document.getElementById("idObjetoAlteracao").value = idBairroAtual;
				abrirPopup('exibirPesquisarBairroAction.do?tipo=bairro', 410, 790);
			}
			
			function selecionarNovoObjetoCep(cdCepAtual){			
				document.getElementById("idObjetoAlteracao").value = cdCepAtual;
				abrirPopup('exibirPesquisarCepAction.do', 250, 600);
			}
			
			function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {
				var form = document.forms[0];
			
				if (tipoConsulta == 'bairro') {			      
			      var idBairroAtual = document.getElementById("idObjetoAlteracao").value;			      
			      form.action = 'exibirAtualizarLogradouroAtualizacaoCadastralPopUpAction.do?idBairroAtual=' + idBairroAtual + '&idBairroNovo=' + idRegistro;
				  submeterFormPadrao(form);
				}		
			}
			
					
			function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	
			    var form = document.forms[0];
				
			    if (tipoConsulta == 'municipio') {
			      form.idMunicipio.value = codigoRegistro;
			      form.nomeMunicipio.value = descricaoRegistro;
			      form.nomeMunicipio.style.color = "#000000";
			      form.codigoBairro.value = "";
			      form.nomeBairro.value = "";
			      
			      //Codigo responsavel pela atualizacao automatica da colecao
			      form.action = "exibirAtualizarLogradouroAtualizacaoCadastralPopUpAction.do?removerColecaoBairro=OK&removerColecaoCep=OK";
				  submeterFormPadrao(form);
			    }
			
			    if (tipoConsulta == 'bairro') {
			      form.codigoBairro.value = codigoRegistro;
			      form.nomeBairro.value = descricaoRegistro;
			      form.nomeBairro.style.color = "#000000";
			      
			      //Codigo responsavel pela atualizacao automatica da colecao
			      form.action = "exibirAtualizarLogradouroAtualizacaoCadastralPopUpAction.do";
				  submeterFormPadrao(form);
			
			    }
			    
			    if (tipoConsulta == 'cep') {
			      /*form.codigoCEP.value = codigoRegistro;
			      form.descricaoCEP.value = descricaoRegistro;
			      form.descricaoCEP.style.color = "#000000";*/
			      
			      //C�digo respons�vel pela atualizacao autom�tica da colecao
			      /*form.action = "exibirAtualizarLogradouroAtualizacaoCadastralPopUpAction.do";
				  submeterFormPadrao(form);*/
				  
				  var cdCepAtual = document.getElementById("idObjetoAlteracao").value;
			      
			      form.action = 'exibirAtualizarLogradouroAtualizacaoCadastralPopUpAction.do?cdCepAtual=' + cdCepAtual + '&cdCepNovo=' + codigoRegistro;
				  submeterFormPadrao(form);
			
			    }
			  }
			  
		</SCRIPT>		
	</head>
	<logic:present name="fechar">
		<body leftmargin="5" topmargin="5" onload="desabilitarBotao();fechar();">
	</logic:present>	
	<logic:notPresent name="fechar">
		<body leftmargin="0" topmargin="0" onload="resizePageSemLink(800, 570);desabilitarBotao();">
	</logic:notPresent>
		<input type="hidden" id="idObjetoAlteracao" name="idObjetoAlteracao">
		
		
		<html:form action="/exibirAtualizarLogradouroAtualizacaoCadastralPopUpAction" 
					name="AtualizarLogradouroAtualizacaoCadastralPopUpActionForm"
					type="gcom.gui.atualizacaocadastral.AtualizarLogradouroAtualizacaoCadastralPopUpActionForm"
					method="post">
				
			<html:hidden property="indicadorDesabilitarBotao"/>
			<input type="hidden" id="cepUnico" value="${requestScope.cepUnico}"/>
				
			<table width="100%" border="0" cellspacing="5" cellpadding="0">		
				<tr>
					<td width="615" valign="top" class="centercoltext">
						<table height="100%">
							<tr>
								<td></td>
							</tr>
						</table>					
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
							<tr>
								<td width="11">
									<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
								</td>
								<td class="parabg">Atualizar Novo Logradouro - Atualiza��o Cadastral</td>
								<td width="11">
									<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
								</td>
							</tr>
						</table>
						<p>&nbsp;</p>
						<table width="100%" border="0">
							<tr>
								<td>Para atualizar um novo logradouro, informe os dados abaixo:</td>
								<logic:present scope="application" name="urlHelp">
									<td align="right">
										<a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroEnderecoLogradouroAtualizar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a>
									</td>									
								</logic:present>
								<logic:notPresent scope="application" name="urlHelp">
									<td align="right">
										<span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span>
									</td>									
								</logic:notPresent>
							</tr>
						</table>
						<table width="100%" border="0">
							<tr>
								<td height="0"><strong>Tipo:<font color="#FF0000">*</font></strong></td>
								<td colspan="2">
									<html:select property="idTipo" tabindex="1" style="width: 150px;">
										<html:option value="">&nbsp;</html:option>
										<html:options collection="logradouroTipos" labelProperty="descricao" property="id" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td><strong>T�tulo:</strong></td>
								<td colspan="2">
									<html:select property="idTitulo" tabindex="2" style="width: 150px;">
										<html:option value="">&nbsp;</html:option>
										<html:options collection="logradouroTitulos" labelProperty="descricao" property="id" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td width="15%"><strong>Nome:<font color="#FF0000">*</font></strong></td>
								<td width="85%" colspan="2">
									<html:text maxlength="40" property="nome" size="50" tabindex="3" />
								</td>
							</tr>
							<tr>
								<td width="15%"><strong>Nome Popular:</strong></td>
								<td width="85%" colspan="2">
									<html:text maxlength="30" property="nomePopular" size="40" tabindex="4" />
								</td>
							</tr>							
							<tr>
								<td width="15%"><strong>Loteamento:</strong></td>
								<td width="85%" colspan="2">
									<html:text maxlength="30" property="nomeLoteamento" size="40" tabindex="5" />
								</td>
							</tr>
							<tr>
								<td colspan="3" height="10"></td>
							</tr>
							<tr>
								<td><strong>Munic&iacute;pio:<font color="#FF0000">*</font></strong></td>
								<td colspan="2">
									<html:text maxlength="4" property="idMunicipio" size="4" tabindex="5"
									onkeypress="return validaEnter(event, 'exibirAtualizarLogradouroAtualizacaoCadastralPopUpAction.do?removerColecaoBairro=OK&removerColecaoCep=OK', 'idMunicipio');return isCampoNumerico(event);"/>
									<a href="javascript:redirecionarSubmit('exibirAtualizarLogradouroAtualizacaoCadastralPopUpAction.do?chamarPesquisa=exibirPesquisarMunicipio');"><img width="23" height="21" border="0"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Municipio" /></a> 
									<logic:present	name="idMunicipioNaoEncontrado">
										<logic:equal name="idMunicipioNaoEncontrado" value="exception">
											<html:text property="nomeMunicipio" size="40" maxlength="30" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:equal>
										<logic:notEqual name="idMunicipioNaoEncontrado" value="exception">
											<html:text property="nomeMunicipio" size="40" maxlength="30" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" />
										</logic:notEqual>
									</logic:present> 
									<logic:notPresent name="idMunicipioNaoEncontrado">
										<logic:empty name="AtualizarLogradouroAtualizacaoCadastralPopUpActionForm" property="idMunicipio">
										<html:text property="nomeMunicipio" value="" size="40" maxlength="30" readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:empty>
										<logic:notEmpty name="AtualizarLogradouroAtualizacaoCadastralPopUpActionForm" property="idMunicipio">
											<html:text property="nomeMunicipio" size="40" maxlength="30" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" />
										</logic:notEmpty>			
									</logic:notPresent>
									<a href="javascript:limparPesquisaMunicipio();"><img
										src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar" />
									</a>
								</td>
							</tr>
							<tr>
								<td><strong>Bairro(s):<font color="#FF0000">*</font></strong></td>
								<td>
									<html:text maxlength="4" property="codigoBairro" size="4" tabindex="6"
									onkeypress="javascript:return validaEnterDependencia(event, 'exibirAtualizarLogradouroAtualizacaoCadastralPopUpAction.do', this,document.forms[0].idMunicipio.value,'Munic�pio');return isCampoNumerico(event);" />
									<a href="javascript:adicionarObjeto('1');"><img width="23" height="21" border="0"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Selecionar Bairro" /></a> 										
									<logic:present name="idBairroNaoEncontrado">
										<logic:equal name="idBairroNaoEncontrado" value="exception">
											<html:text property="nomeBairro" size="40" maxlength="30" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:equal>
										<logic:notEqual name="idBairroNaoEncontrado" value="exception">
											<html:text property="nomeBairro" size="40" maxlength="30" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" />
										</logic:notEqual>
									</logic:present> 
									<logic:notPresent name="idBairroNaoEncontrado">
										<logic:empty name="AtualizarLogradouroAtualizacaoCadastralPopUpActionForm" property="idMunicipio">
											<html:text property="nomeBairro" value="" size="40" maxlength="30" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:empty>
										<logic:notEmpty name="AtualizarLogradouroAtualizacaoCadastralPopUpActionForm" property="idMunicipio">
											<html:text property="nomeBairro" size="40" maxlength="30" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" />
										</logic:notEmpty>
									</logic:notPresent> 								
									<a href="javascript:limparPesquisaBairro();"><img
										src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar" />
									</a>																
								</td>									
								<td align="right">
									<input type="button" class="bottonRightCol" value="Adicionar" tabindex="7" style="width: 80px; align: right;" onclick="adicionarObjetoEnter('1');" name="botaoAdicionarBairro">
								</td>
							</tr>
							<tr>
								<td colspan="3">								
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr> 
							                <td> 											
												<table width="100%" bgcolor="#99CCFF">
								                    <tr bgcolor="#99CCFF"> 
														<td align="center" width="10%">&nbsp;</td>
														<td width="90%">
															<div align="center">
																<strong>Bairro</strong>
															</div>
														</td>
								                    </tr>
							                    </table>
											</td>
							            </tr>
						            </table>
						            
						            <% String cor = "#cbe5fe";%>						            
						            <logic:present name="colecaoBairrosSelecionadosUsuario" scope="session">			
									<div style="width: 100%; height: 100; overflow: auto;">								
										<table width="100%" cellpadding="0" cellspacing="0" id="bairroCarregado">
								            <tr> 
												<td> 											
													<% cor = "#cbe5fe";%>											
													<table width="100%" align="center" bgcolor="#99CCFF">
														<logic:iterate name="colecaoBairrosSelecionadosUsuario" id="bairro" type="Bairro">					                            
															
															<%	if (cor.equalsIgnoreCase("#FFFFFF")){
																	cor = "#cbe5fe";%>
																	<tr bgcolor="#cbe5fe">
															<%} else{
																	cor = "#FFFFFF";%>
																	<tr bgcolor="#FFFFFF">
															<%}%> 
															
															<td align="center" width="10%">														
																<img src="<bean:message key='caminho.imagens'/>Error.gif" 
																onclick="remover(<%="" + bairro.getId().intValue()%>, <bean:write name="logradouro" property="id"/>, '1')" 
																title="Remover" style="cursor: hand;">													
															</td>
																<td width="90%">
																	<div align="left">										
																		<logic:present name="bairro" property="nome">																		
																			<bean:write name="bairro" property="nome"/>																
																		</logic:present>
																		<logic:notPresent name="bairro" property="nome">
																			&nbsp;
																		</logic:notPresent>															
																	</div>
																</td>
															</tr>					
														</logic:iterate>						                          
													</table>
												</td>
								            </tr>							
										</table>											
									</div>
									</logic:present>	
								</td>
							</tr>
							<tr>
								<td colspan="3" height="20"></td>
							</tr>
							<tr>
								<td><strong>CEP(s):<font color="#FF0000">*</font></strong></td>
								<td>
									<logic:present name="cepUnico" scope="request">
										<html:text maxlength="8" property="codigoCEP" size="10" tabindex="8" disabled="true" 
										onkeypress="return isCampoNumerico(event);"/>
									</logic:present>									
									<logic:notPresent name="cepUnico" scope="request">
										<html:text maxlength="8" property="codigoCEP" size="10" tabindex="8"
											onkeypress="javascript:return validaEnterDependencia(event, 'exibirAtualizarLogradouroAtualizacaoCadastralPopUpAction.do', this,document.forms[0].idMunicipio.value,'Munic�pio');return isCampoNumerico(event);" />
									</logic:notPresent>
									<logic:present name="cepUnico" scope="request">
										<img width="23" height="21" border="0"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										title="Selecionar CEP"/>
									</logic:present>
									<logic:notPresent name="cepUnico" scope="request">
										<a href="javascript:adicionarObjeto('2');" >
										<img width="23" height="21" border="0" alt="Selecionar CEP"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										title="Selecionar CEP"/></a>
									</logic:notPresent>								
									<logic:present name="idCEPNaoEncontrado">
										<logic:equal name="idCEPNaoEncontrado" value="exception">
											<html:text property="descricaoCEP" size="40" maxlength="30" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:equal>
										<logic:notEqual name="idCEPNaoEncontrado" value="exception">
											<html:text property="descricaoCEP" size="40" maxlength="30" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" />
										</logic:notEqual>
									</logic:present> 
									<logic:notPresent name="idCEPNaoEncontrado">
										<logic:empty name="AtualizarLogradouroAtualizacaoCadastralPopUpActionForm" property="idMunicipio">
											<html:text property="descricaoCEP" value="" size="40" maxlength="30" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:empty>
										<logic:notEmpty name="AtualizarLogradouroAtualizacaoCadastralPopUpActionForm" property="idMunicipio">
											<html:text property="descricaoCEP" size="40" maxlength="30" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" />
										</logic:notEmpty>
									</logic:notPresent> 
									<logic:present name="cepUnico" scope="request">
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar" />
									</logic:present>										
									<logic:notPresent name="cepUnico" scope="request">
										<a href="javascript:limparPesquisaCep();"> <img
										src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar" /></a>
									</logic:notPresent>								
								</td>								
								<td align="right">									
									<logic:present name="cepUnico" scope="request">
										<input type="button" class="bottonRightCol" value="Adicionar" tabindex="9" style="width: 80px; align: right;" disabled="disabled">
									</logic:present>										
									<logic:notPresent name="cepUnico" scope="request">
										<input type="button" class="bottonRightCol" value="Adicionar" tabindex="9" style="width: 80px; align: right;" onclick="adicionarObjetoEnter('2');" name="botaoAdicionarCep">
									</logic:notPresent>									
								</td>
							</tr>
							<tr>
								<td colspan="3">							
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr> 
						               		<td> 									
												<table width="100%" bgcolor="#99CCFF">
								                    <tr bgcolor="#99CCFF"> 
														<td align="center" width="10%"></td>
														<td width="25%"><div align="center"><strong>Logradouro</strong></div></td>
					                        			<td width="20%"><div align="center"><strong>Bairro</strong></div></td>
					                        			<td width="20%"><div align="center"><strong>Munic�pio</strong></div></td>
														<td width="10%"><div align="center"><strong>UF</strong></div></td>
														<td width="15%"><div align="center"><strong>CEP</strong></div></td>
								                    </tr>
							                    </table>											
											</td>
							            </tr>
						            </table>   
						            <logic:present name="colecaoCepSelecionadosUsuario" scope="session">		
									<div style="width: 100%; height: 100; overflow: auto;">			
									<table width="100%" cellpadding="0" cellspacing="0" id="cepCarregado">
						            <tr> 
										<td> 										
											<% cor = "#cbe5fe";%>									
											<table width="100%" align="center" bgcolor="#99CCFF">				
											<logic:iterate name="colecaoCepSelecionadosUsuario" id="cep" type="Cep">
					                            												
												<%	if (cor.equalsIgnoreCase("#FFFFFF")){
														cor = "#cbe5fe";%>
														<tr bgcolor="#cbe5fe">
												<%} else{
														cor = "#FFFFFF";%>
														<tr bgcolor="#FFFFFF">
												<%}%> 
												
													<td align="center" width="10%"> 
														<img src="<bean:message key='caminho.imagens'/>Error.gif" onclick="remover(<%="" + cep.getIdCepAtualizacaoCadastral()%>, <bean:write name="logradouro" property="id"/>, '2')" title="Remover" style="cursor: hand;">
													</td>
													<td width="25%">
														<div align="left">					
															<logic:present name="cep" property="descricaoLogradouroFormatada">
																<bean:write name="cep" property="descricaoLogradouroFormatada"/>
															</logic:present>
															<logic:notPresent name="cep" property="descricaoLogradouroFormatada">&nbsp;</logic:notPresent>
														</div>
													</td>
													<td width="20%">
														<div align="left">													
															<logic:present name="cep" property="bairro">
																	<bean:write name="cep" property="bairro"/>
															</logic:present>
															<logic:notPresent name="cep" property="bairro">&nbsp;</logic:notPresent>												
														</div>
													</td>													
													<td width="20%">
														<div align="left">														
															<logic:present name="cep" property="municipio">
																	<bean:write name="cep" property="municipio"/>
															</logic:present>
															<logic:notPresent name="cep" property="municipio">&nbsp;</logic:notPresent>														
														</div>
													</td>					
													<td width="10%">
														<div align="left">														
															<logic:present name="cep" property="sigla">
																	<bean:write name="cep" property="sigla"/>
															</logic:present>
															<logic:notPresent name="cep" property="sigla">&nbsp;</logic:notPresent>													
														</div>
													</td>					
													<td width="15%">
														<div align="center">													
															<logic:present name="cep" property="codigo">
																	<bean:write name="cep" property="cepFormatado"/>
															</logic:present>
															<logic:notPresent name="cep" property="codigo">&nbsp;</logic:notPresent>										
														</div>
													</td>
												</tr>				
											</logic:iterate>				                          
											</table>											
										</td>
						            </tr>				
									</table>									
									</div>
									</logic:present>								
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td colspan="2">
									<strong><font color="#FF0000">*</font></strong> 
									Campos obrigat&oacute;rios
								</td>
							</tr>
							</table>
							<table width="100%">
								<tr>
									<td />
									<td width="100%" align="right">
										<input id="BotaoAtualizar" name="BotaoAtualizar" 
										type="button" onclick="validarForm(document.forms[0]);" class="bottonRightCol" value="Atualizar">
									</td>
								</tr>
							</table>
					</td>
				</tr>
			</table>	
		</html:form>
	</body>
</html:html>
