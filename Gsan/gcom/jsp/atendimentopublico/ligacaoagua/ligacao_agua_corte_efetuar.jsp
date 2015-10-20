<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="EfetuarCorteLigacaoAguaActionForm" />

<script language="JavaScript">
	
	function validaForm() {
		var form = document.EfetuarCorteLigacaoAguaActionForm;
	   	 if (validateEfetuarCorteLigacaoAguaActionForm(form) && validaDebito()){
	  		//submeterFormPadrao(form);
	  		submitForm(form);
		 }
	}
	
	function limparForm() {
		var form = document.forms[0];
	
		form.idOrdemServico.value = "";
		limparOS();
		if(form.idTipoDebito != null){
		  form.idTipoDebito.value = "";
		  form.descricaoTipoDebito.value = "";
		  form.valorDebito.value = "";
		  if(form.motivoNaoCobranca != null){	
		    form.motivoNaoCobranca.selectedIndex = 0;
		  }
		  limpaDadosCobranca();
		}
	 }
	
	function limparOS() {
		var form = document.EfetuarCorteLigacaoAguaActionForm;
		form.nomeOrdemServico.value = "";
		form.matriculaImovel.value = "";
		form.inscricaoImovel.value = "";
		form.clienteUsuario.value = "";
		form.cpfCnpjCliente.value = "";
		form.situacaoLigacaoAgua.value = "";
		form.situacaoLigacaoEsgoto.value = "";
		form.dataCorte.value = "";
		form.motivoCorte.selectedIndex = 0;
		form.numLeituraCorte.value = "";
		form.numSeloCorte.value = "";	
		
	}

	//Recupera Dados Popup
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];
	    if (tipoConsulta == 'ordemServico') {
	    	form.idOrdemServico.value = codigoRegistro;
	      	form.action='exibirEfetuarCorteLigacaoAguaAction.do';
	      	form.submit();
	    }
	}
	
	//Chama Popup
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			}
			else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				}
				else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisaOrdemServico=" + tipo, altura, largura);
				}
			}
		}
	}	 
 	//verifica se existe alguma restri��o 
	//para exibi��o alguma campo no formulario
    function verificaForm() {

       	var form = document.forms[0];

		if(form.veioEncerrarOS.value == 'true'){
			form.idOrdemServico.disabled=true;
		}else{
			form.idOrdemServico.disabled=false;
		}
	 }
	
	function limpaDadosCobranca() {
		var form = document.forms[0];
		
		form.percentualCobranca.selectedIndex = 0;
		form.quantidadeParcelas.value = "";
		limpaValorParcela();
	}
	
	function limpaValorParcela() {
		var form = document.forms[0];
		form.valorParcelas.value = '';
	}
	
	function calcularValores(){
	
		var form = document.EfetuarCorteLigacaoAguaActionForm;
	   	if (validateEfetuarCorteLigacaoAguaActionForm(form) && validaDebito()){
	   	
	   		form.action='exibirEfetuarCorteLigacaoAguaAction.do?calculaValores=S';
	      	form.submit();
		}
	}
	
	//verifica se o motivo da n�o cobra�a naum foi informado
	//libera o campo quantidade de parcelas 
	function habilitarQtdParcelaButton(){
		var form = document.forms[0];
		if(form.motivoNaoCobranca.value == "-1"){	
			form.percentualCobranca.value = "-1"
			form.valorParcelas.value = "";
			form.percentualCobranca.disabled = false;
			form.quantidadeParcelas.disabled = false;
			form.valorParcelas.value = "";
			form.buttonCalcular.disabled = false;
		}else{
			form.percentualCobranca.value = "-1"
			form.percentualCobranca.disabled = true;
			form.quantidadeParcelas.disabled = true;
			form.quantidadeParcelas.value = "";
			form.valorParcelas.value = "";
			form.buttonCalcular.disabled = true;
		}
	}		

</script>
</head>



<body leftmargin="5" topmargin="5"
	onload="javascript:verificaForm();setarFoco('${requestScope.nomeCampo}');">

<div id="formDiv">

<html:form action="/efetuarCorteLigacaoAguaAction.do"
	name="EfetuarCorteLigacaoAguaActionForm"
	type="gcom.gui.atendimentopublico.EfetuarCorteLigacaoAguaActionForm"
	method="post">

	<html:hidden property="veioEncerrarOS" />
	<html:hidden property="qtdeMaxParcelas" />

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
					<td class="parabg">Efetuar Corte de Liga��o de �gua</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>


			<!--Inicio da Tabela Corte Liga��o �gua -->
			<table width="100%" border="0">
				<tr>

					<td height="31">
					<table width="100%" border="0" align="center">
						<tr>
							<td height="10" colspan="2">Para efetuar o corte da liga��o de
							�gua, informe os dados abaixo:.</td>
						</tr>
						<tr>
							<td height="10" colspan="2">
							<hr>
							</td>
						</tr>

						<tr>
							<td height="10"><strong>Ordem de Servi&ccedil;o:<span
								class="style2"> <strong><font color="#FF0000">*</font></strong></span>
							</strong></td>
							<td><span class="style2"> <!-- Campo Ordem de Servi�o --> <html:text
								property="idOrdemServico" size="10" maxlength="9"
								onkeypress="javascript:validaEnterComMensagem(event, 'exibirEfetuarCorteLigacaoAguaAction.do', 'idOrdemServico','Ordem de Servi�o');"
								onkeyup="limparOS();" /> <img
								src="<bean:message key='caminho.imagens'/>pesquisa.gif"
								width="23" height="21" name="imagem"
								onclick="chamarPopup('exibirPesquisarOrdemServicoAction.do', 'ordemServico', null, null, 275, 480, '',document.forms[0].idOrdemServico);"
								alt="Pesquisar"> <logic:present name="osEncontrada"
								scope="session">
								<html:text property="nomeOrdemServico" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000"
									size="50" />
							</logic:present> <logic:notPresent name="osEncontrada"
								scope="session">
								<html:text property="nomeOrdemServico" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000"
									size="50" />
							</logic:notPresent> <a href="javascript:limparForm();"  > <img
								border="0" 
								src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
							</span></td>
						</tr>

						<tr bgcolor="#cbe5fe">
							<td align="center" colspan="2">
							<table width="100%" border="0" bgcolor="#99CCFF">
								<tr bgcolor="#99CCFF">
									<td height="18" colspan="2">
									<div align="center"><span class="style2"> Dados do Im�vel </span></div>
									</td>
								</tr>
								<tr bgcolor="#cbe5fe">

									<td>
									<table border="0" width="100%">
										<tr>
											<td width="37%" height="10"><strong><span class="style2">Matr&iacute;cula
											do Im&oacute;vel:<strong></strong></span></strong></td>
											<td width="58%"><html:text property="matriculaImovel"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												size="15" maxlength="20" /> <html:text
												property="inscricaoImovel" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="21"
												maxlength="20" /></td>
										</tr>
										<tr>
											<td><strong> Cliente Usu&aacute;rio:</strong></td>
											<td><html:text property="clienteUsuario" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="40"
												maxlength="40" /></td>

										</tr>
										<tr>
											<td><strong>CPF ou CNPJ:</strong></td>
											<td><html:text property="cpfCnpjCliente" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="40"
												maxlength="40" /></td>
										</tr>
										<tr>

											<td><strong>Situa&ccedil;&atilde;o da Liga&ccedil;&atilde;o
											de &Aacute;gua:</strong></td>
											<td><html:text property="situacaoLigacaoAgua" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="40"
												maxlength="40" /></td>
										</tr>
										<tr>
											<td><strong>Situa&ccedil;&atilde;o da Liga&ccedil;&atilde;o
											de Esgoto:</strong></td>

											<td><html:text property="situacaoLigacaoEsgoto"
												readonly="true" style="background-color:#EFEFEF; border:0;"
												size="40" maxlength="40" /></td>
										</tr>
									</table>
									</td>
								</tr>
							</table>
							</td>

						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td height="31">
					<table width="100%" border="0" align="center">
						<tr bgcolor="#cbe5fe">
							<td align="center" colspan="2">
							<table width="100%" border="0" bgcolor="#99CCFF">

								<tr bgcolor="#99CCFF">
									<td height="18" colspan="2">
									<div align="center"><span class="style2"> Dados do Corte de
									Liga��o de �gua</span></div>
									</td>
								</tr>
								<tr bgcolor="#cbe5fe">
									<td>
									<table border="0" width="100%">
										<tr>

											<td width="42%" height="10"><strong>Data do Corte:<span
												class="style2"><strong><font color="#FF0000"></font></strong></span><span
												class="style2"></span></strong></td>
											<td colspan="2"><strong><b> <!-- Campo Data do Corte  --> <html:text
												property="dataCorte" readonly="true"
												style="background-color:#EFEFEF; border:0;" size="12"
												maxlength="20" /></b></strong></td>
										</tr>
										<tr>
											<td><strong> Motivo do Corte:<span class="style2"><strong><font
												color="#FF0000">*</font></strong></span></strong></td>
											<td colspan="2"><strong> <!--Campo Sele��o Motivo do Corte -->
											<html:select property="motivoCorte" style="width: 230px;">
												<logic:present name="colecaoMotivoCorteLigacaoAgua"
													scope="session">
													<html:option
														value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
													<html:options collection="colecaoMotivoCorteLigacaoAgua"
														labelProperty="descricao" property="id" />
												</logic:present>
											</html:select> </strong></td>
										</tr>
										<tr>
											<td><strong> Tipo do Corte:<span class="style2"><strong><font
												color="#FF0000">*</font></strong></span></strong></td>
											<td colspan="2"><strong> <!--Campo Sele��o Tipo do Corte -->
											<html:select property="tipoCorte" style="width: 230px;">
												<logic:present name="colecaoTipoCorteLigacaoAgua"
													scope="session">
													<html:option
														value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
													<html:options collection="colecaoTipoCorteLigacaoAgua"
														labelProperty="descricao" property="id" />
												</logic:present>
											</html:select> </strong></td>
										</tr>
										<tr>
											<td class="style3">
												<strong>Leitura do Corte:<span class="style2"><strong>
												<font color="#FF0000"></font></strong></span></strong>
											</td>
											<td colspan="2"><strong> <!-- Campo Material Liga��o --> 
												<html:text  property="numLeituraCorte" 
															size="12" 
															maxlength="6"
															onkeypress="javascript: return isCampoNumerico(event);" /> </strong>
											</td>
										</tr>
										<tr>
											<td class="style3">
												<strong>N�mero do Selo do Corte:<span class="style2"></span></strong>
											</td>
											<td colspan="2">
											<strong> 
												<html:text  property="numSeloCorte"
															size="12" 
															maxlength="8" 
															onkeypress="javascript:return isCampoNumerico(event);" /> 
											</strong>
											</td>
										</tr>
									</table>
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<logic:notEmpty name="EfetuarCorteLigacaoAguaActionForm"
					property="idTipoDebito">
					<tr>
						<td height="31">
						<table width="100%" border="0" align="center">
							<tr bgcolor="#cbe5fe">
								<td align="center" colspan="2">
								<table width="100%" border="0" bgcolor="#99CCFF">

									<tr bgcolor="#99CCFF">
										<td height="18" colspan="2">
										<div align="center"><span class="style2"> Dados da Gera��o do
										D�bito</span></div>
										</td>
									</tr>
									<tr bgcolor="#cbe5fe">
										<td>
										<table border="0" width="100%">
											<tr>
												<td width="42%" height="10"><strong>Tipo de D�bito:</strong></td>
												<td colspan="2">
													<html:text property="idTipoDebito" 
														readonly="true"
														style="background-color:#EFEFEF; border:0;" 
														size="5"
														maxlength="5" /> 
													
													<html:text property="descricaoTipoDebito"
														readonly="true" 
														style="background-color:#EFEFEF; border:0;"
														size="30" 
														maxlength="30" /> 
												</td>
											</tr>
											
											<!-- Colocado por Raphael Rossiter em 19/04/2007 -->
											<logic:notEqual name="EfetuarCorteLigacaoAguaActionForm" property="alteracaoValor" value="OK">
												<tr>
													<td><strong>Valor do D�bito:</strong></td>
													
													<td colspan="2">
														<html:text property="valorDebito" 
															readonly="true"
															style="background-color:#EFEFEF; border:0; text-align:right;"
															size="10" />
													</td>
												</tr>
											</logic:notEqual>
											
											<logic:equal name="EfetuarCorteLigacaoAguaActionForm" property="alteracaoValor" value="OK">
												<tr>
													<td><strong>Valor do D�bito:</strong></td>
													<td colspan="2">
														<html:text property="valorDebito" 
															style="text-align:right;"
															size="10" />
													</td>
												</tr>
											</logic:equal>
											
											
											<logic:present name="permissaoMotivoNaoCobranca">
												<tr>
													<td class="style3"><strong>Motivo da N�o Cobran�a:</strong></td>
													
													<td colspan="2">
														<html:select property="motivoNaoCobranca" 
															style="width: 230px;"
															onchange="habilitarQtdParcelaButton();">
															<html:option value="-1">&nbsp;</html:option>
															
															<logic:present name="colecaoMotivoNaoCobranca">
																<html:options collection="colecaoMotivoNaoCobranca"
																	labelProperty="descricao" 
																	property="id" />
															</logic:present>
														</html:select> 
													</td>
												</tr>
												<tr>
													<td class="style3"><strong>Percentual de Cobran�a:</strong></td>
													
													<td colspan="2">
														<html:select property="percentualCobranca" 
															style="width: 70px;">
															<html:option value="-1">&nbsp;</html:option>
															<html:option value="100">100%</html:option>
															<html:option value="70">70%</html:option>
															<html:option value="50">50%</html:option>
														</html:select>
													</td>
												</tr>
												<tr>
													<td class="style3">
														<strong>Quantidade de Parcelas:</strong>
													</td>
													<td colspan="2">
														<html:text property="quantidadeParcelas" 
															size="5" 
															maxlength="5"
															onkeypress="return isCampoNumerico(event);"/> 
													</td>
												</tr>
												
												<tr>
													<td class="style3"><strong>Valor da Parcela:</strong></td>
													<td colspan="2">
														<html:text property="valorParcelas" 
															readonly="true"
															style="background-color:#EFEFEF; border:0; text-align:right;"
															size="10" /> 
													</td>
													
													<td colspan="4" align="right">
														<input type="button" 
															class="bottonRightCol" 
															value="Calcular"
															name="buttonCalcular" 
															tabindex="10"
															onclick="calcularValores();" 
															style="width: 80px">
													</td>													
												</tr>
											</logic:present>
											
											<logic:notPresent name="permissaoMotivoNaoCobranca">
												
												<tr>
													<td class="style3">
														<strong>Percentual de Cobran�a: <font olor="#FF0000">*</font></strong>
													</td>
													<td colspan="2">
														<html:select property="percentualCobranca" style="width: 70px;" >
															<html:option value="100" disabled="true" >100%</html:option>
														</html:select> 
													</td>
												</tr>
												<tr>
													<td class="style3">
														<strong>Quantidade de Parcelas:<font color="#FF0000">*</font></strong>
													</td>
													<td colspan="2"> 
														<html:text  property="quantidadeParcelas" 
																	size="5" 
																	maxlength="5"
																	readonly="true" 
																	style="background-color:#EFEFEF; border:0;text-align: right;"/> 
													</td>
												</tr>
												<tr>
													<td class="style3">
														<strong>Valor da Parcela:</strong>
													</td>
													<td colspan="2">
														<html:text property="valorParcelas"
															readonly="true"
															style="background-color:#EFEFEF; border:0;text-align: right;"
															size="15" 
															maxlength="15" />
													</td>
												</tr>
											</logic:notPresent>
											
										</table>
										</td>
									</tr>
								</table>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</logic:notEmpty>
				<tr>
					<td colspan="2">
					<table width="100%">

						<tr>
							<td width="40%" align="left"><logic:present
								name="caminhoRetornoIntegracaoComercial">
								<INPUT TYPE="button" class="bottonRightCol" value="Voltar"
									onclick="redirecionarSubmit('${caminhoRetornoIntegracaoComercial}')" />
							</logic:present> 

							<input type="button" name="ButtonCancelar" class="bottonRightCol"
								value="Cancelar"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
							</td>

							<td align="right"><input name="Button" type="button"
								class="bottonRightCol" value="Efetuar" onclick="validaForm();">
							</td>
						</tr>
					</table>
					</td>

				</tr>
				<!--</tr></table></td>-->
			</table>
	</table>
	<!-- Fim do Corpo - Leandro Cavalcanti -->


	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>

</div>
<%@ include file="/jsp/util/telaespera.jsp"%>
</body>
</html:html>
