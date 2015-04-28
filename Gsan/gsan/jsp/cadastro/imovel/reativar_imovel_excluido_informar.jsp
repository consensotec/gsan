<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="ReativarImovelExcluidoActionForm" />

<script language="JavaScript">

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

    if (tipoConsulta == 'imovel') {
      form.matricula.value = codigoRegistro;
	  form.action = 'exibirReativarImovelExcluido.do?objetoConsulta=1';	  
	  form.submit();
    }
 }
 
 function validaForm(nomeCliente) {
	 var form = document.forms[0]; 	
 	
 	if(confirm('Deseja confirmar a reativação do Imóvel?')){
	 	if (validateReativarImovelExcluidoActionForm(form)){
	 		form.action = 'reativarImovelExcluidoAction.do?matricula='+form.matricula.value;
	 		form.submit();
	 	}
	 }
 }
 
 
</script>
</head>
<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('matricula');">

<html:form action="/exibirReativarImovelExcluido.do"
	name="ReativarImovelExcluidoActionForm"
	type="gsan.gui.cadastro.imovel.ReativarImovelExcluidoActionForm"
	method="post"
	onsubmit="return validateReativarImovelExcluidoActionForm(this);">

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
					<td><input name="nomeCliente" type="hidden"></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Reativar Imóvel Excluído</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>			
			<p>&nbsp;</p>
			<table border="0" width="100%">
				<tr>
					<td colspan="4">
					<table align="center" bgcolor="#99ccff" border="0" width="100%">
						<tr>
							<td align="center"><strong>Dados do Imóvel</strong></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td align="center" width="100%">
								<table border="0" width="100%">
									<tr>
										<td bordercolor="#000000" width="160">
											<strong>Matrícula do Imóvel:<font color="#ff0000">*</font></strong>
										</td>
										<td colspan="3" bordercolor="#000000" width="414">
											<html:text  property="matricula" 
														maxlength="9" 
														size="10" 
														tabindex="1"
														onkeypress="javascript:validaEnterComMensagem(event, 'exibirReativarImovelExcluido.do?objetoConsulta=1', 'matricula','Imóvel');return isCampoNumerico(event);" />
												<a href="javascript:abrirPopup('exibirPesquisarImovelAction.do');">
													<img src="imagens/pesquisa.gif" 
														 title="Pesquisar Imovél"
														 border="0" 
														 height="21" 
														 width="23"></a> 
											<logic:present name="inexistente" scope="request">
												<html:text  property="descricaoImovel" 
															maxlength="25" 
															size="43"
															value="IMÓVEL INEXISTENTE" 
															readonly="readonly"
															style="background-color:#EFEFEF; border:0; color: #ff0000;" />
											</logic:present> 											
											
											<logic:notPresent name="inexistente" scope="request">
												<logic:present name="imovelAtivo" scope="request">
													<html:text  property="descricaoImovel" 
															maxlength="25" 
															size="43"
															value="IMÓVEL JÁ ESTÁ ATIVO" 
															readonly="readonly"
															style="background-color:#EFEFEF; border:0; color: #ff0000;" />															
												</logic:present>
												<logic:notPresent name="imovelAtivo" scope="request">
													<html:text  property="descricaoImovel" 
																	maxlength="25" 
																	size="43"
																	value="${requestScope.matriculaImovel}" 
																	readonly="readonly"
																	style="border: 0pt none ; background-color: rgb(239, 239, 239); color: rgb(0, 0, 0);" />
												
												</logic:notPresent>	
											</logic:notPresent> 
											
												<a href="exibirReativarImovelExcluido.do?menu=sim">
													<img src="imagens/limparcampo.gif" 
														 border="0" 
														 height="21" 
														 width="23"
														 title="Apagar">
												 </a>
										</td>
									</tr>									
									<tr bgcolor="#cbe5fe">										
										<td colspan="2" align="center"><strong>Situação Atual</strong></td>
										<td colspan="2" align="center"><strong>Situação Anterior</strong></td>										
									</tr>
									<tr>
										<td height="10">
											<div class="style9"><strong>Água:</strong></div>
										</td>
										<td><input name="situacaoAgua" type="text" 
											value="${requestScope.imovel.ligacaoAguaSituacao.descricao}"
											style="background-color:#EFEFEF; border:0; font-size:9px"
											value="" size="22" maxlength="15" readonly="readonly">
										</td>
										
										<td height="10">
											<div class="style9"><strong>Água:</strong></div>
										</td>
										<td><input name="situacaoAguaAnterior" type="text" 
											value="${requestScope.imovel.ligacaoAguaSituacaoAnterior.descricao}"
											style="background-color:#EFEFEF; border:0; font-size:9px"
											value="" size="22" maxlength="15" readonly="readonly">
										</td>										
									</tr>
									<tr>
										<td width="146"><strong>Esgoto:</strong></td>
										<td width="123"><input name="situacaoEsgoto" type="text" 
											value="${requestScope.imovel.ligacaoEsgotoSituacao.descricao}"
											style="background-color:#EFEFEF; border:0; font-size:9px"
											value="" size="22" maxlength="15" readonly="readonly">
										</td>
										<td width="146"><strong>Esgoto:</strong></td>
										<td width="123"><input name="situacaoEsgotoAnterior" type="text" 
											value="${requestScope.imovel.ligacaoEsgotoSituacaoAnterior.descricao}"
											style="background-color:#EFEFEF; border:0; font-size:9px"
											value="" size="22" maxlength="15" readonly="readonly">
										</td>
									</tr>
										<tr>
										<td height="10">
											<div class="style9"><strong>Perfil do Imóvel:</strong></div>
										</td>
										<td><input name="perfilImovel" type="text" 
											value="${requestScope.imovel.imovelPerfil.descricao}"
											style="background-color:#EFEFEF; border:0; font-size:9px"
											value="" size="22" maxlength="15" readonly="readonly">
										</td>
	
										<td width="146"><strong>Perfil do Imóvel:</strong></td>
										<td width="123"><input name="perfilImovelAnterior" type="text" 
											value="${requestScope.imovel.imovelPerfilAnterior.descricao}"
											style="background-color:#EFEFEF; border:0; font-size:9px"
											value="" size="22" maxlength="15" readonly="readonly">
										</td>
									</tr>										
									<tr>
										<td colspan="4">
											<table align="center" bgcolor="#99ccff" border="0" width="100%">
												<tr>
													<td align="center">
													<div class="style9"><strong>Endereço </strong></div>				
													</td>
												</tr>
												<tr bgcolor="#cbe5fe">
													<logic:present name="endereco" scope="request">
														<td align="center" bgcolor="#ffffff">${requestScope.endereco}</td>
													</logic:present>
												</tr>
											</table>
										</td>
									</tr>									
									<tr>
										<td colspan="4">
											<table align="center" bgcolor="#99ccff" border="0" width="100%">
												<tr>
													<td align="center">
													<div class="style9"><strong>Cliente Usuário</strong></div>				
													</td>
												</tr>
												<tr bgcolor="#cbe5fe">
													<logic:present name="cliente" scope="request">
														<td align="center" bgcolor="#ffffff">${requestScope.cliente}</td>
													</logic:present>
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
					<td colspan="5">
					<table border="0" width="100%">					
						<tr>
							<td colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td align="left" width="51%">&nbsp; 
								<input  name="Submit22"
										class="bottonRightCol" 
										value="Desfazer" 
										type="button"
										onclick="window.location.href='/gsan/exibirReativarImovelExcluido.do?menu=sim';">
							
								<input  name="Submit23" 
										class="bottonRightCol" 
										value="Cancelar"
										type="button"
										onclick="window.location.href='/gsan/telaPrincipal.do'">
							</td>
							<td align="left" width="49%">
								<div align="right">								
									<logic:present name="botaoReativar" scope="request">
									<input  name="reativar" 
											class="bottonRightCol" 
											value="Reativar"																					
											onclick="javascript:validaForm();" 
											type="button">
									</logic:present>
									<logic:notPresent name="botaoReativar" scope="request">
										<input  name="reativar" 
												class="bottonRightCol" 
												value="Reativar"
												disabled="disabled"																						
												onclick="javascript:validaForm();" 
												type="button">
									</logic:notPresent>																														
								</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
