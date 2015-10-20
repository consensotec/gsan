<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html:html>

<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript">

	function limparForm(){
		var form = document.forms[0];
		form.action = "consultarNossoNumeroAction.do?desfazer=sim";
		form.submit();
	}

	function validarNossoNumero(){
		var retorno = true;
		var form = document.forms[0];

		var codigoConvenio = form.numero.value.substring(0,7);
		var tipoDocumento = form.numero.value.substring(7,9);
		var idDocumento = form.numero.value.substring(9, 17);
		
		if((codigoConvenio == "0000000") || (tipoDocumento == "00") || idDocumento == "00000000"){
			alert("Nosso número é inválido.");
			retorno = false;
		}
		
		return retorno;
	}

	function validaEnterNossoNumeroComMensagemAceitaZERO(tecla, caminhoActionReload, nomeCampoForm, nomeCampoMSG) {

		var form = document.forms[0];

		var objetoCampo = eval("form." + nomeCampoForm);
		var valorCampo = trim(eval("form." + nomeCampoForm + ".value"));
		var indesejaveis = "~{}^%$[]@|`\\<?\#?!;*>\"\'";
		var teste = true;
		
		var codigoConvenio = valorCampo.substring(0,7);
		var tipoDocumento = valorCampo.substring(7,9);
		var idDocumento = valorCampo.substring(9, 17);
		
		if (document.all) {
			var codigo = event.keyCode;
	    }
		else {
	       var codigo = tecla.which;
	    }

	    if (codigo == 13) {
			validarForm();    
		}else{
			return false;
		}
	}

	function validarForm(){
		var form = document.forms[0];
		var valorCampo = form.numero.value;
		var indesejaveis = "~{}^%$[]@|`\\<?\#?!;*>\"\'-+_&()/";
		var teste = true;

		if(form.numero.value == ""){
			alert("Nosso número é obrigatório.");
		}else{
			for (var i=0; i<indesejaveis.length; i++) {
				if ((valorCampo.indexOf(indesejaveis.charAt(i))) != -1 ){
					teste = false;
				}
	      	}
	      	if(teste == false)
	      	{
		      	alert("Nosso numero possui caracteres especiais.");
			}
	      	else if (valorCampo.length > 0 && (isNaN(valorCampo) || valorCampo.indexOf(',') != -1 ||
				valorCampo.indexOf('.') != -1)){
				alert("Nosso número deve somente conter números positivos.");
			}
			else if(validarNossoNumero()){
				form.action = "consultarNossoNumeroAction.do";
				form.submit();
			}
		}
	}

</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/consultarNossoNumeroAction" name="ConsultaNossoNumeroActionForm" onsubmit="return false;"
	type="gcom.gui.arrecadacao.ConsultarDocumentoArrecadacaoNossoNumeroActionForm" method="post" >
	
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
					<p align="left">&nbsp;</p>
				</div>
			</td>
			<td width="625" valign="top" bgcolor="#003399" class="centercoltext">
				<table height="100%">
					<tr>
						<td></td>
					</tr>
				</table>
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="11"><img src="imagens/parahead_left.gif"
							editor="Webstyle4"
							moduleid="Album Photos (Project)\toptab_page2_parahead_left.xws"
							border="0" /></td>
						<td class="parabg">Consultar Documentos de Arrecadação Através do Nosso Número</td>
	
						<td width="11"><img src="imagens/parahead_right.gif"
							editor="Webstyle4"
							moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws"
							border="0" /></td>
					</tr>
				</table>
				<p>&nbsp;</p>
				<table width="100%" align="center" cellpadding="0" cellspacing="0">
					<!-- Corpo da segunda tabela -->
					<tr>
						<td colspan="5" height="23"><font color="#000000" style="font-size:11px"
							face="Verdana, Arial, Helvetica, sans-serif"><strong>Informe o dado abaixo para realizar a consulta:</strong></font>
						</td>
					</tr>
					<tr>
						<td>
							<table width="100%" border="0" >
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td style="width: 100px;">
										<strong>Nosso N&uacute;mero:<font color="#FF0000">*</font></strong>
									</td>
									<td>
										<span class="style2">
											<html:text property="numero" size="18" maxlength="18" onkeypress="validaEnterNossoNumeroComMensagemAceitaZERO(event, 'consultarNossoNumeroAction.do', 'numero', 'Nosso numero');return isCampoNumerico(event);" />
										</span>
									</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
							</table>
							<table width="100%" border="0">
								<tr>
									<td style="width: 110px;">
										<div><strong><font color="#FF0000">*</font></strong>Campo obrigat&oacute;rio</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					
					<logic:notEmpty name="conta" scope="session">
						<tr>
							<td width="100%" colspan="2">	
								<table width="100%" align="center" bgcolor="#90c7fc" border="0">
									<tr bordercolor="#79bbfd">
										<td align="center" bgcolor="#79bbfd">
											<logic:equal name="tipoConsultaConta" value="conta" scope="session">
												<strong>Conta</strong>
											</logic:equal>
											<logic:notEqual name="tipoConsultaConta" value="conta" scope="session">
												<strong>Conta Histórico</strong>
											</logic:notEqual>
										</td>
									</tr>
									<tr>
										<td align="left">
											<logic:notEmpty name="conta" property="id">
												<div align="center">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<logic:equal name="tipoConsultaConta" value="conta" scope="session">
															<a href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=<bean:define name="conta" property="id" id="id" /><bean:write name="conta" property="id"/>&tipoConsulta=conta', 600, 800);">
																<bean:define name="conta" property="id" id="id" />
																<bean:write name="conta" property="id" />
															</a>
														</logic:equal>
														<logic:notEqual name="tipoConsultaConta" value="conta" scope="session">
															<a href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=<bean:define name="conta" property="id" id="id" /><bean:write name="conta" property="id"/>&tipoConsulta=contaHistorico', 600, 800);">
																<bean:define name="conta" property="id" id="id" />
																<bean:write name="conta" property="id" />
															</a>
														</logic:notEqual>
													</font>
												</div>
											</logic:notEmpty>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</logic:notEmpty>
						
					<logic:notEmpty name="cobrancaDocumento" scope="session">
						<tr>
							<td width="100%" colspan="2">	
								<table width="100%" align="center" bgcolor="#90c7fc" border="0">
									<tr bordercolor="#79bbfd">
										<td align="center" bgcolor="#79bbfd">
											<strong>Cobrança Documento</strong>
										</td>
									</tr>
									<tr>
										<td align="left">
											<logic:notEmpty name="cobrancaDocumento" property="id">
												<div align="center">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<a href="javascript:abrirPopup('exibirDocumentoCobrancaItemAction.do?cobrancaDocumentosID=<bean:define name="cobrancaDocumento" property="id" id="id" /><bean:write name="cobrancaDocumento" property="id"/>', 600, 800);">
															<bean:define name="cobrancaDocumento" property="id" id="id" />
															<bean:write name="cobrancaDocumento" property="id" />
														</a>
													</font>
												</div>
											</logic:notEmpty>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</logic:notEmpty>
					
					<logic:notEmpty name="guiaPagamento" scope="session">
						<tr>
							<td width="100%" colspan="2">
								<table width="100%" align="center" bgcolor="#90c7fc" border="0">
									<tr bordercolor="#79bbfd">
										<td align="center" bgcolor="#79bbfd">
											<strong>Guia de Pagamento</strong>
										</td>
									</tr>
									<tr>
										<td align="left">
											<logic:notEmpty name="guiaPagamento" property="id">
												<div align="center">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<a href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoId=<bean:define name="guiaPagamento" property="id" id="id" /><bean:write name="guiaPagamento" property="id"/>', 600, 800);">
															<bean:define name="guiaPagamento" property="id" id="id" />
															<bean:write name="guiaPagamento" property="id" />
														</a>
													</font>
												</div>
											</logic:notEmpty>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</logic:notEmpty>
					
					<logic:notEmpty name="guiaPagamentoHistorico" scope="session">
						<tr>
							<td width="100%" colspan="2">
								<table width="100%" align="center" bgcolor="#90c7fc" border="0">
									<tr bordercolor="#79bbfd">
										<td align="center" bgcolor="#79bbfd">
											<strong>Guia de Histórico de Pagamento</strong>
										</td>
									</tr>
									<tr>
										<td align="left">
											<logic:notEmpty name="guiaPagamentoHistorico" property="id">
												<div align="center">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<a href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoHistoricoId=<bean:define name="guiaPagamentoHistorico" property="id" id="id" /><bean:write name="guiaPagamentoHistorico" property="id"/>', 600, 800);">
															<bean:define name="guiaPagamentoHistorico" property="id" id="id" />
															<bean:write name="guiaPagamentoHistorico" property="id" />
														</a>
													</font>
												</div>
											</logic:notEmpty>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</logic:notEmpty>
					
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<!-- Botões -->

					<tr>
						<td align="left"><input name="Button" type="button"
							class="bottonRightCol" value="Limpar" align="left"
							onclick="limparForm();">
						<input type="button" name="ButtonCancelar" class="bottonRightCol"
							value="Cancelar"
							onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
						</td>
						<td colspan="2" align="right"><input name="Button" type="button"
							class="bottonRightCol" value=Pesquisar onclick="validarForm();"></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>

</body>
</html:html>