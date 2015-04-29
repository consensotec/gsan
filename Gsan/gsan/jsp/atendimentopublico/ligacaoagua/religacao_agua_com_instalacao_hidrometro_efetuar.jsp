<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="gsan.util.ConstantesSistema"%>

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
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="EfetuarReligacaoAguaComInstalacaoHidrometroActionForm"
	dynamicJavascript="true" />

<script language="JavaScript">
	
	function validaForm() {
		var form = document.EfetuarReligacaoAguaComInstalacaoHidrometroActionForm;
		if (validateEfetuarReligacaoAguaComInstalacaoHidrometroActionForm(form) && validaDebito()){
			if (form.situacaoCavalete[0].checked == false && form.situacaoCavalete[1].checked == false) {
				alert("Informe Cavalete");
			} else {
				submeterFormPadrao(form);
			}
		}
	}
	
	function limparForm() {
		var form = document.EfetuarReligacaoAguaComInstalacaoHidrometroActionForm;

		form.idOrdemServico.value = "";
		form.nomeOrdemServico.value = "";

		form.matriculaImovel.value = "";
		form.inscricaoImovel.value = "";
		form.clienteUsuario.value = "";
		form.cpfCnpjCliente.value = "";
		form.situacaoLigacaoAgua.value = "";
		form.situacaoLigacaoEsgoto.value = "";
		form.numeroHidrometro.value = "";
		form.localInstalacao.selectedIndex = 0;
		form.protecao.selectedIndex = 0;
		form.leituraInstalacao.value = "";
		form.numeroSelo.value = "";
		form.situacaoCavalete[0].checked = false;
		form.situacaoCavalete[1].checked = false;	

		if(form.idTipoDebito != null){
			form.idTipoDebito.value = "";
			form.descricaoTipoDebito.value = "";
			form.valorDebito.value = "";
			if(form.motivoNaoCobranca != null){
			  form.motivoNaoCobranca.selectedIndex = 0;
			}
			form.percentualCobranca.selectedIndex = 0;
			form.quantidadeParcelas.value = "";
			form.valorParcelas.value = "";
		}

		form.action = 'exibirEfetuarReligacaoAguaComInstalacaoHidrometroAction.do?desfazer=sim';
		form.submit();
	 }

	 function limparDecricaoEfetuar() {
		var form = document.EfetuarReligacaoAguaComInstalacaoHidrometroActionForm;

		form.nomeOrdemServico.value = "";
		form.matriculaImovel.value = "";
		form.inscricaoImovel.value = "";
		form.clienteUsuario.value = "";
		form.cpfCnpjCliente.value = "";
		form.situacaoLigacaoAgua.value = "";
		form.situacaoLigacaoEsgoto.value = "";
		form.dataReligacao.value = "";

		if(form.idTipoDebito != null){
			form.idTipoDebito.value = "";
			form.descricaoTipoDebito.value = "";
			form.valorDebito.value = "";
			if(form.motivoNaoCobranca != null){
			  form.motivoNaoCobranca.selectedIndex = 0;
			}
			form.percentualCobranca.selectedIndex = 0;
			form.quantidadeParcelas.value = "";
			form.valorParcelas.value = "";
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
					abrirPopup(url + "?" + "menu=sim&" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisaOrdemServico=" + tipo, altura, largura);
				}
			}
		}
	}
	 
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];

		if (tipoConsulta == 'hidrometro') {
      		form.numeroHidrometro.value = codigoRegistro;

	      	form.action='exibirEfetuarReligacaoAguaComInstalacaoHidrometroAction.do?objetoConsulta=1';
	      	form.submit();
      		
    	} else if (tipoConsulta == 'ordemServico') {

	    	form.idOrdemServico.value = codigoRegistro;
	      	form.action='exibirEfetuarReligacaoAguaComInstalacaoHidrometroAction.do';

	      	form.submit();
	    }
	}

	function validarCaracteresEspeciais(evento){

		 var tecla = null;
			
		  if(window.event){ // Internet Explorer
		  		tecla = evento.keyCode;
		  }else if(evento.which){ // Nestcape ou Mozilla
		    	tecla = evento.which;
		  }
		  
		  if(tecla>= 33 && tecla <= 47){
			  return false;
		  }
		  if(tecla >=58 && tecla<= 64){
              return false;
	      }

	      if(tecla>=91 && tecla <= 96){
               return false;
		   }

		   if(tecla >= 123 && tecla <= 126){
                return false;
		     }

		   if(tecla == 168){
                return false;
			 }

		  
		
		return true;

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
	
	function limparNumeroHidrometro() {
		var form = document.forms[0];
		form.numeroHidrometro.value = "";
	}
	
	
	function calcularValores(){
	
		var form = document.EfetuarReligacaoAguaComInstalacaoHidrometroActionForm;
	   	if (validateEfetuarReligacaoAguaComInstalacaoHidrometroActionForm(form) && validaDebito()){
	   	
	   		form.action='exibirEfetuarReligacaoAguaComInstalacaoHidrometroAction.do?calculaValores=S';
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
	
	function desabilitaCamposMotivoNaoCobranca(){
	var form = document.forms[0];
	 if(form.motivoNaoCobranca.value != "-1"){
	  form.percentualCobranca.disabled = true;
	  form.quantidadeParcelas.disabled = true;
	  form.buttonCalcular.disabled = true;
	 }
	}		
</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');verificaForm();desabilitaCamposMotivoNaoCobranca();">

<html:form action="/efetuarReligacaoAguaComInstalacaoHidrometroAction.do"
	name="EfetuarReligacaoAguaComInstalacaoHidrometroActionForm"
	type="gsan.gui.atendimentopublico.ligacaoagua.EfetuarReligacaoAguaComInstalacaoHidrometroActionForm"
	method="post">


	<html:hidden property="veioEncerrarOS" />

	<logic:notPresent name="semMenu">

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
				</logic:notPresent> <logic:present name="semMenu">
					<table width="550" border="0" cellspacing="5" cellpadding="0">
						<tr>
							<td width="615" valign="top" class="centercoltext">
							<table height="100%">
								<tr>
									<td></td>
								</tr>
							</table>
							</logic:present>
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="11"><img border="0"
										src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
									<td class="parabg">Efetuar Religa&ccedil;&atilde;o de &Aacute;gua Com Instala&ccedil;&atilde;o de
									Hidr&ocirc;metro</td>
									<td width="11"><img border="0"
										src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
								</tr>
							</table>
							<p>&nbsp;</p>


							<!--Inicio da Tabela Liga��o Agua -->
							<table width="100%" border="0">
								<tr>

									<td height="31">
									<table width="100%" border="0" align="center">
										<tr>
											<td height="10" colspan="2">Para efetuar a
											liga&ccedil;&atilde;o de &aacute;gua com instala&ccedil;&atilde;o de
											hidr&ocirc;metro, informe os dados abaixo:</td>
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

											<td><span class="style2"> <html:text
												property="idOrdemServico" size="8" maxlength="9"
												onkeypress="validaEnterComMensagem(event, 'exibirEfetuarReligacaoAguaComInstalacaoHidrometroAction.do', 'idOrdemServico','Ordem de Servi�o');return isCampoNumerico(event);"
												onkeyup="javascript:limparDecricaoEfetuar()" />
												 <a
												href="javascript:chamarPopup('exibirPesquisarOrdemServicoAction.do', 'ordemServico', null, null, 600, 500, '',document.forms[0].idOrdemServico);">
											<img width="23" height="21" border="0"
												src="<bean:message key="caminho.imagens"/>pesquisa.gif"
												title="Pesquisar OS" /></a> <logic:present
												name="OrdemServioInexistente">
												<html:text property="nomeOrdemServico" readonly="true"
													style="background-color:#EFEFEF; border:0; color: #ff0000"
													size="37" maxlength="40" />
											</logic:present> <logic:notPresent
												name="OrdemServioInexistente">
												<html:text property="nomeOrdemServico" readonly="true"
													style="background-color:#EFEFEF; border:0; color: #000000"
													size="37" maxlength="40" />
											</logic:notPresent> <a href="javascript:limparForm();"> <img
												border="0" title="Apagar"
												src="<bean:message key='caminho.imagens'/>limparcampo.gif" />
											</a> </span></td>
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
															do Im&oacute;vel:</span></strong></td>

															<td width="58%"><html:text property="matriculaImovel"
																readonly="true"
																style="background-color:#EFEFEF; border:0;" size="15"
																maxlength="20" /> <html:text property="inscricaoImovel"
																readonly="true"
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

															<td><strong>Situa&ccedil;&atilde;o da
															Liga&ccedil;&atilde;o de &Aacute;gua:</strong></td>
															<td><html:text property="situacaoLigacaoAgua"
																readonly="true"
																style="background-color:#EFEFEF; border:0;" size="40"
																maxlength="40" /></td>
														</tr>
														<tr>
															<td><strong>Situa&ccedil;&atilde;o da
															Liga&ccedil;&atilde;o de Esgoto:</strong></td>

															<td><html:text property="situacaoLigacaoEsgoto"
																readonly="true"
																style="background-color:#EFEFEF; border:0;" size="40"
																maxlength="40" /></td>
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
													<div align="center"><span class="style2"> Dados da
													Religa&ccedil;&atilde;o </span></div>
													</td>
												</tr>
												<tr bgcolor="#cbe5fe">
													<td>
													<table border="0" width="100%">
														<tr>
															<td width="42%" height="10"><strong>Data da
															Religa&ccedil;&atilde;o:<font color="#FF0000">*</font> </strong>
															</td>

															<td colspan="2"><strong><b> <html:text
																property="dataReligacao" readonly="true"
																style="background-color:#EFEFEF; border:0;" size="20"
																maxlength="20" /> </b></strong></td>
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
													<div align="center"><span class="style2"> Dados da
													Instala��o do Hidr�metro</span></div>
													</td>
												</tr>
												<tr bgcolor="#cbe5fe">
													<td>
													<table border="0" width="100%">
														<tr>
															<td width="37%" class="style3"><strong>N&uacute;mero do
															Hidr&ocirc;metro:<font color="#FF0000">*</font></strong></td>

															<td width="58%"><html:text property="numeroHidrometro"
																size="13" maxlength="12"
																onkeypress="validaEnterString(event, 'exibirEfetuarReligacaoAguaComInstalacaoHidrometroAction.do?objetoConsulta=1', 'numeroHidrometro','N�mero do Hidr�metro'); return validarCaracteresEspeciais(event); "
	                                                            														 
															 />
															<a
																href="javascript:abrirPopup('exibirPesquisarHidrometroAction.do',600,640);"><img
																src="/gsan/imagens/pesquisa.gif" alt="Pesquisar" title="Pesquisar Hidr�metro"
																border="0" height="21" width="23"></a> <a
																href="javascript:limparNumeroHidrometro()"><img
																border="0" title="Apagar"
																src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
															</td>
														</tr>
														<tr>
															<td height="10"><strong>Data da Instala��o:<font
																color="#FF0000">*</font></strong></td>
															<td colspan="2"><strong><b> <!-- Campo Data da Ligacao -->
															<html:text property="dataInstalacao" readonly="true"
																style="background-color:#EFEFEF; border:0;" size="10"
																maxlength="10" /> </b></strong></td>
														</tr>
														<tr>
															<td><strong>Local de Instala��o:<font color="#FF0000">*</font></strong></td>
															<td><html:select property="localInstalacao" tabindex="4">
																<html:option value="-1">&nbsp;</html:option>
																<html:options
																	collection="colecaoHidrometroLocalInstalacao"
																	labelProperty="descricao" property="id" />
															</html:select> <font size="1">&nbsp; </font></td>
														</tr>
														<tr>
															<td><strong>Prote��o:<font color="#FF0000">*</font></strong></td>
															<td><html:select property="protecao" tabindex="4">
																<html:option value="-1">&nbsp;</html:option>
																<html:options collection="colecaoHidrometroProtecao"
																	labelProperty="descricao" property="id" />
															</html:select> <font size="1">&nbsp; </font></td>
														</tr>
														<tr>
															<td class="style3"><strong>Leitura Instala��o:<font color="#FF0000">*</font></strong></td>
															<td colspan="2"><strong><b><span class="style2"> <html:text
																property="leituraInstalacao" size="6" maxlength="6"
																onkeyup="" onkeydown="" onkeypress="return isCampoNumerico(event);" /> </span></b></strong></td>
														</tr>
														<tr>
															<td class="style3"><strong>N�mero do Selo:</strong></td>
															<td colspan="2"><strong><b><span class="style2"> <html:text
																property="numeroSelo" size="12" maxlength="12"
																onkeyup="" onkeydown="" onkeypress="return isCampoNumerico(event);" /> </span></b></strong></td>
														</tr>
														<tr>
															<td><strong>Cavalete:<font color="#FF0000">*</font></strong></td>
															<td><strong> <html:radio property="situacaoCavalete"
																value="1" /> <strong>COM <html:radio
																property="situacaoCavalete" value="2" /> SEM</strong> </strong></td>
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
								<logic:notEmpty
									name="EfetuarReligacaoAguaComInstalacaoHidrometroActionForm"
									property="idTipoDebito">
									<tr>
										<td height="31">
										<table width="100%" border="0" align="center">
											<tr bgcolor="#cbe5fe">
												<td align="center" colspan="2">
												<table width="100%" border="0" bgcolor="#99CCFF">

													<tr bgcolor="#99CCFF">
														<td height="18" colspan="2">
														<div align="center"><span class="style2"> Dados da Gera��o
														do D�bito</span></div>
														</td>
													</tr>
													<tr bgcolor="#cbe5fe">
														<td>
														<table border="0" width="100%">

															<tr>
																<td width="42%" height="10"><strong>Tipo de D�bito:</strong>
																</td>

																<td colspan="2"><strong><b> <html:text
																	property="idTipoDebito" readonly="true"
																	style="background-color:#EFEFEF; border:0;" size="5"
																	maxlength="5" /> <html:text
																	property="descricaoTipoDebito" readonly="true"
																	style="background-color:#EFEFEF; border:0;" size="30"
																	maxlength="30" /> </b></strong></td>
															</tr>
															
															
															<!-- Colocado por Raphael Rossiter em 20/04/2007 -->
															<logic:notEqual name="EfetuarReligacaoAguaComInstalacaoHidrometroActionForm" property="alteracaoValor" value="OK">
															<tr>
																<td><strong>Valor do D�bito:</strong></td>
																<td colspan="2"><strong> <html:text
																	property="valorDebito" readonly="true"
																	style="background-color:#EFEFEF; border:0;text-align: right;"
																	size="15" maxlength="15" /> </strong></td>
															</tr>
															</logic:notEqual>
															
															<logic:equal name="EfetuarReligacaoAguaComInstalacaoHidrometroActionForm" property="alteracaoValor" value="OK">
															<tr>
																<td><strong>Valor do D�bito:</strong></td>
																<td colspan="2"><strong> <html:text
																	property="valorDebito" 
																	style="text-align: right;"
																	size="15" maxlength="15" /> </strong></td>
															</tr>
															</logic:equal>
															
															
															<logic:present name="permissaoMotivoNaoCobranca">
																<tr>
																	<td class="style3"><strong>Motivo da N�o Cobran�a:<font
																		color="#FF0000">*</font></strong></td>

																	<td colspan="2"><strong> <html:select
																		property="motivoNaoCobranca" style="width: 230px;"
																		onchange="habilitarQtdParcelaButton();">
																		<html:option value="-1">&nbsp;</html:option>
																		<logic:present name="colecaoNaoCobranca">
																			<html:options collection="colecaoNaoCobranca"
																				labelProperty="descricao" property="id" />
																		</logic:present>
																	</html:select> </strong></td>
																</tr>
																<tr>
																	<td class="style3"><strong>Percentual de Cobran�a: <font
																		color="#FF0000">*</font></strong></td>
																	<td colspan="2"><strong> <html:select
																		property="percentualCobranca" style="width: 70px;"
																		onchange="limpaValorParcela();calculaValorParcela();">

																		<html:option value="-1">&nbsp;</html:option>
																		<html:option value="100">100%</html:option>
																		<html:option value="70">70%</html:option>
																		<html:option value="50">50%</html:option>
																	</html:select> </strong></td>
																</tr>
																<tr>
																	<td class="style3"><strong>Quantidade de Parcelas:<font
																		color="#FF0000">*</font></strong></td>
																	<td colspan="2"><strong> <html:text
																		property="quantidadeParcelas" size="5" maxlength="5"
																		onkeypress="return isCampoNumerico(event);"
																		 /> </strong></td>
																</tr>
																<tr>
																	<td class="style3"><strong>Valor da Parcela:</strong></td>
																	<td colspan="2"><html:text property="valorParcelas"
																		readonly="true"
																		style="background-color:#EFEFEF; border:0;text-align: right;"
																		size="15" maxlength="15" /></td>
																		
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
																	<td class="style3"><strong>Percentual de Cobran�a: <font
																		color="#FF0000">*</font></strong></td>
																	<td colspan="2"><strong> <html:select
																		property="percentualCobranca" style="width: 70px;">

																		<html:option value="100">100%</html:option>
																	</html:select> </strong></td>
																</tr>
																<tr>
																	<td class="style3"><strong>Quantidade de Parcelas:<font
																		color="#FF0000">*</font></strong></td>
																	<td colspan="2"><strong> <html:text
																		property="quantidadeParcelas" size="5" maxlength="5"
																		readonly="true" /> </strong></td>
																</tr>
																<tr>
																	<td class="style3"><strong>Valor da Parcela:</strong></td>
																	<td colspan="2"><html:text property="valorParcelas"
																		readonly="true"
																		style="background-color:#EFEFEF; border:0;text-align: right;"
																		size="15" maxlength="15" /></td>
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
											</logic:present> <input type="button" name="ButtonReset"
												class="bottonRightCol" value="Desfazer"
												onClick="limparForm();"> <input type="button"
												name="ButtonCancelar" class="bottonRightCol"
												value="Cancelar"
												onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
											</td>

											<td align="right"><input name="Button" type="button"
												class="bottonRightCol" value="Efetuar"
												onclick="validaForm();"></td>
										</tr>
									</table>
									</td>

								</tr>
							</table>
							</td>
						</tr>
					</table>
					<logic:notPresent name="semMenu">
						<%@ include file="/jsp/util/rodape.jsp"%>
					</logic:notPresent></td>
			</tr>
		</table>
</html:form>
</body>
</html:html>
