<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ page import="gcom.atendimentopublico.ordemservico.FiscalizacaoSituacao"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript">
<!-- Begin -->
	
</script>

</head>

<body leftmargin="0" topmargin="0">

<form method="post">
<table width="700" border="0" cellpadding="0" cellspacing="5">
	<tr>
		<td width="690" valign="top" class="centercoltext">
		<table height="100%">
			<tr>
				<td></td>
			</tr>
		</table>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="11"><img src="imagens/parahead_left.gif"
					editor="Webstyle4"
					moduleid="Album Photos (Project)\toptab_page2_parahead_left.xws"
					border="0" /></td>
				<td class="parabg">Consultar<strong> </strong>Dados do Comando de
				A&ccedil;&atilde;o de Cobran&ccedil;a Eventual<font size="-1">&nbsp;</font></td>
				<td width="11"><img src="imagens/parahead_right.gif"
					editor="Webstyle4"
					moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws"
					border="0" /></td>
			</tr>
		</table>
		<table width="88%" border="0" >
			<tr>
				<td height="200" width="100%" colspan="4">
				<table width="100%" border="0">
					<tr>
						<td colspan="4">&nbsp;</td>
					</tr>
					<tr>
						<td width="48%" height="24"><strong>A&ccedil;&atilde;o de
						Cobran&ccedil;a:</strong></td>
						<td width="69%" colspan="3"><strong><strong> <html:text maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm"
					property="acaoCobranca" size="30"/>  </strong></strong></td>
					</tr>
					<tr>
						<td height="24"><strong>Atividade de Cobran&ccedil;a:</strong></td>
						<td colspan="3"><strong><strong> <html:text maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm"
					property="atividadeCobranca" size="30"/> </strong></strong></td>
					</tr>
					<tr>
						<td height="24" colspan="4">
						<hr>
						</td>
					</tr>
					<tr>
						<td height="24"><strong> Crit&eacute;rio Utilizado:</strong></td>
						<td colspan="3"><strong><strong> <html:text maxlength="15" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm"
					property="criterioUtilizado" size="15"/> </strong></strong></td>
					</tr>
					<tr>
						<td height="24"><strong>Crit&eacute;rio de Cobran&ccedil;a:</strong></td>
						<td colspan="3"><strong><strong> <html:text maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm"
					property="criterio" size="30"/> </strong></strong></td>
					</tr>
					<tr>
						<td height="24" colspan="4">
						<hr>
						</td>
					</tr>
					<tr>
						<td height="24"><strong>Grupo de Cobran&ccedil;a:</strong></td>
						<td colspan="3"><strong><strong> <html:text maxlength="25" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm"
					property="grupoCobranca" size="25"/> </strong></strong></td>
					</tr>
					<tr>
						<td height="24" colspan="4">
						<hr>
						</td>
					</tr>
					<tr>
						<td height="24"><strong>Matr�cula do Im�vel:</strong></td>
						<td colspan="3"><strong><strong> <html:text maxlength="25" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm"
					property="idImovel" size="25"/> </strong></strong></td>
					</tr>
					<tr>
						<td height="24" colspan="4">
						<hr>
						</td>
					</tr>
					<tr>
						<td height="24"><strong>Dados de Localiza&ccedil;&atilde;o
						Geogr&aacute;fica</strong></td>
						<td colspan="3">&nbsp;</td>
					</tr>
					<tr>
						<td height="24"><strong>Ger&ecirc;ncia Regional:</strong></td>
						<td colspan="3"><strong><strong> <html:text maxlength="25" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm"
					property="gerenciaRegional" size="25"/> </strong></strong></td>
					</tr>
					<tr>
						<td height="24"><strong>Unidade Neg�cio:</strong></td>
						<td colspan="3"><strong><strong> <html:text maxlength="25" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm"
					property="unidadeNegocio" size="25"/> </strong></strong></td>
					</tr>

					<tr>
						<td height="24"><strong>Localidade Inicial:<font color="#FF0000"></font></strong></td>
						<td colspan="3"><strong><strong> <strong> <html:text maxlength="4" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm"
					property="localidadeInicial" size="4"/>
						</strong> <html:text maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm"
					property="descricaoLocalidadeInicial" size="30"/> </strong></strong></td>
					</tr>
					<tr>
						<td height="24"><strong>Localidade Final:<font color="#FF0000"></font></strong></td>
						<td colspan="3"><strong><strong> <strong>  <html:text maxlength="4" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm"
					property="localidadeFinal" size="4"/>
						</strong>  <html:text maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm"
					property="descricaoLocalidadeFinal" size="30"/> </strong></strong></td>
					</tr>
					<tr>
						<td height="24"><strong>Setor Comercial Inicial:</strong></td>
						<td colspan="3"><strong><strong> <strong> <html:text maxlength="4" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm"
					property="setorComercialInicial" size="4"/> 
						</strong> <html:text maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm"
					property="descricaoSetorComercialInicial" size="30"/>  </strong></strong></td>
					</tr>
					<tr>
						<td height="24"><strong>Setor Comercial Final:</strong></td>
						<td colspan="3"><strong><strong> <strong> <html:text maxlength="4" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm"
					property="setorComercialFinal" size="4"/> 
						</strong> <html:text maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm"
					property="descricaoSetorComercialFinal" size="30"/>  </strong></strong></td>
					</tr>
					<tr>
						<td height="24"><strong>Rota Inicial:</strong></td>
						<td colspan="3"><strong><strong> <html:text maxlength="4" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm"
					property="rotaInicial" size="4"/>  </strong></strong></td>
					</tr>
					<tr>
						<td height="24"><strong>Rota Final:</strong></td>
						<td colspan="3"><strong><strong> <html:text maxlength="4" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm"
					property="rotaFinal" size="4"/>  </strong></strong></td>
					</tr>
					<tr>
						<td height="24" colspan="4">
						<hr>
						</td>
					</tr>
					<tr>
						<td height="24"><strong>Cliente:</strong></td>
						<td colspan="3"><strong><strong> <html:text maxlength="50" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm"
					property="cliente" size="50"/>  </strong></strong></td>
					</tr>
					<tr>
						<td height="24"><strong>Tipo de Rela&ccedil;&atilde;o: </strong></td>
						<td colspan="3"><strong><strong> <html:text maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm"
					property="tipoRelacaoCliente" size="30"/>  </strong></strong></td>
					</tr>
					<tr>
						<td height="24" colspan="4">
						<hr>
						</td>
					</tr>
					<tr>
						<td height="24"><strong>Data e Hora do Comando:</strong></td>
						<td colspan="3"><strong><strong> <html:text maxlength="10" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm"
					property="dataComando" size="10"/>  <strong> <html:text maxlength="8" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm"
					property="horaComando" size="8"/>  </strong> </strong></strong></td>
					</tr>
					<tr>
						<td height="24"><strong>Data e Hora de Realiza&ccedil;&atilde;o:</strong></td>
						<td colspan="3"><strong><strong> <html:text maxlength="10" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm"
					property="dataRealizacao" size="10"/>  <strong> <html:text maxlength="8" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm"
					property="horaRealizacao" size="8"/>  </strong> </strong></strong></td>
					</tr>
					<tr>
						<td height="24" colspan="4">
						<hr>
						</td>
					</tr>
					<tr>
						<td height="24"><strong>Per&iacute;odo de Refer&ecirc;ncia das
						Contas: </strong></td>
						<td colspan="3"><strong><strong> <html:text maxlength="7" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm"
					property="periodoReferenciaContasInicial" size="7"/>  a <strong> <html:text maxlength="7" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm"
					property="periodoReferenciaContasFinal" size="7"/> 
						</strong></strong></strong></td>
					</tr>
					<tr>
						<td height="24"><strong>Per&iacute;odo de Vencimento das Contas: </strong></td>
						<td colspan="3"><strong><strong> <strong> <html:text maxlength="10" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm"
					property="periodoVencimentoContasInicial" size="10"/>  </strong> a <strong> <strong> <html:text maxlength="10" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm"
					property="periodoVencimentoContasFinal" size="10"/>  </strong> </strong></strong></strong></td>
					</tr>
					<tr>
						<td height="24" colspan="4">
						<hr>
						</td>
					</tr>
					<tr>
						<td height="24"><strong>Quantidade de Dias de Vencimento:</strong></td>
						<td colspan="3"><strong><strong> 
						<html:text maxlength="3" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm"
					property="quantidadeDiasVencimento" size="3"/> </strong></strong></td>
					</tr>
					 
					
					<tr>
						<td height="24" colspan="4">
						<hr>
						</td>
					</tr>
					<tr>
						<td height="24"><strong>Intervalo Consumo M�dio:</strong></td>
						<td colspan="3"><strong><strong> <strong> <html:text maxlength="10" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm"
					property="consumoMedioInicial" size="10"/>  </strong> a <strong> <strong> <html:text maxlength="10" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm"
					property="consumoMedioFinal" size="10"/>  </strong> </strong></strong></strong></td>
					</tr>
					<tr>
						<td height="24"><strong>Tipo Consumo:</strong></td>
						<td colspan="3"><strong><strong> <html:text maxlength="15" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm"
					property="tipoConsumo" size="15"/> </strong></strong></td>
					</tr>
					<tr>
						<td height="24" colspan="4">
						<hr>
						</td>
					</tr>
					<tr>
						<td height="24"><strong>Per&iacute;odo de Fiscaliza��o: </strong></td>
						<td colspan="3"><strong><strong> <strong> <html:text maxlength="10" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
						name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm"
						property="periodoInicialFiscalizacao" size="10"/>  </strong> a <strong> <strong> <html:text maxlength="10" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
						name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm"
						property="periodoFinalFiscalizacao" size="10"/>  </strong> </strong></strong></strong>
						</td>
					</tr>
					<logic:present name="colecaoFiscalizacao"scope="session">
					<tr>
						<td height="24" valign="top"><strong>Situa��o Fiscaliza��o:</strong></td>
						<td colspan="3">
						<table width="100%" align="left" bgcolor="#99CCFF">
						<tr bordercolor="#FFFFFF" bgcolor="#79BBFD">
							<td width="100%">
							<div align="left"><strong>Descri��o</strong></div>
							</td>
						</tr>
						<%String cor = "#FFFFFF";%>
					
								<logic:iterate name="colecaoFiscalizacao" id="fiscalizacao">
									
										<%if (cor.equalsIgnoreCase("#cbe5fe")) {
											cor = "##FFFFFF";%>
										<tr bgcolor="#cbe5fe">
											<%} else {
											cor = "#cbe5fe";%>
										<tr bgcolor="#FFFFFF">
											<%}%>
											<td colspan="2" bordercolor="#90c7fc">											
												<div align="left">
													<bean:write name="fiscalizacao"
														property="descricaoFiscalizacaoSituacao" />
												</div>
											</td>
										</tr>
							
								</logic:iterate>					
						</table>
						</td>
					</tr>
					</logic:present>	
					<tr>
						<td height="24" valign="top"><strong>Rela��o de Im�veis:</strong></td>
						<td colspan="3"/>
					<tr>
						<td height="24" valign="top"><strong>Nome do arquivo:</strong></td>
						<td colspan="3">
						<html:text maxlength="96" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm"
					property="nomeArquivoRelacaoImoveis" size="96"/> </strong></strong></td>
					</tr>
					
					
					<logic:present name="colecaoComandoAtividadeImoveis"scope="session">
						<table width="100%" align="left" bgcolor="#99CCFF">
						<tr bordercolor="#FFFFFF" bgcolor="#79BBFD">
							<td width="100%" colspan="4">
							<div align="left"><strong>Im�veis:</strong></div>
							</td>
						</tr>
						<%String cor = "#FFFFFF";%>
					
								<logic:iterate name="colecaoComandoAtividadeImoveis" id="comandoAtividadeImoveis">
									
										<%if (cor.equalsIgnoreCase("#cbe5fe")) {
											cor = "##FFFFFF";%>
										<tr bgcolor="#cbe5fe">
											<%} else {
											cor = "#cbe5fe";%>
										<tr bgcolor="#FFFFFF">
											<%}%>
											<td colspan="4" bordercolor="#90c7fc">											
												<div align="left">
													<bean:write name="comandoAtividadeImoveis"
														property="imovel.id" />
												</div>
											</td>
										</tr>
							
								</logic:iterate>					
						</table>
					
					</logic:present>
					<tr>
						<td height="24" colspan="4">
						<hr>
						</td>
					</tr>
					<tr>
						<td height="24"><strong> Valor dos Documentos:</strong></td>
						<td colspan="3"><strong><strong> <html:text maxlength="14" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm"
					property="valorDocumentos" size="14"/>  </strong></strong></td>
					</tr>
					<tr>
						<td height="24"><strong> Quantidade de Documentos:</strong></td>
						<td colspan="3"><strong><strong> <html:text maxlength="11" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm"
					property="quantidadeDocumentos" size="11"/>  </strong></strong></td>
					</tr>
					<tr>
						<td height="24"><strong> Quantidade de Itens dos Documentos:</strong></td>
						<td colspan="3"><strong><strong> <html:text maxlength="11" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm"
					property="quantidadeItensDocumentos" size="11"/>  </strong></strong></td>
					</tr>
					<logic:notEmpty name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm" property="quantidadeSmsGerados">
						<logic:greaterThan name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm" property="quantidadeSmsGerados" value="0">
							<tr>
								<td height="24"><strong>Quantidade de SMS gerados / enviados:</strong></td>
								<td colspan="3">
									<strong>
										<html:text maxlength="5" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm" property="quantidadeSmsGerados" size="5"/>
										<span>&nbsp;/&nbsp;</span>
										<html:text maxlength="5" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm" property="quantidadeSmsEnviados" size="5"/>
									</strong>
								</td>
							</tr>
						</logic:greaterThan>
					</logic:notEmpty>
					<logic:notEmpty name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm" property="quantidadeEmailGerados">
						<logic:greaterThan name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm" property="quantidadeEmailGerados" value="0">
							<tr>
								<td height="24"><strong>Quantidade de E-mail gerados / enviados:</strong></td>
								<td colspan="3">
									<strong>
										<html:text maxlength="5" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm" property="quantidadeEmailGerados" size="5"/>
										<span>&nbsp;/&nbsp;</span>
										<html:text maxlength="5" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm" property="quantidadeEmailEnviados" size="5"/>
									</strong>
								</td>
							</tr>
						</logic:greaterThan>
					</logic:notEmpty>
					<tr>
						<td height="24" colspan="4">
						<hr>
						</td>
					</tr>
					<tr>
						<td height="24"><strong>Situa&ccedil;&atilde;o do Comando: </strong></td>
						<td colspan="3"><strong><strong> <html:text maxlength="16" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"
					name="ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm"
					property="situacaoComando" size="16"/>  </strong></strong></td>
					</tr>
					<tr>
						<td height="24"><strong> </strong></td>
						<td colspan="3">&nbsp;</td>
					</tr>
					<tr>
					    <logic:present name="emitir">
						<td height="27">
						<div align="left"><input name="Button2" type="button"
							class="bottonRightCol" value="Emitir Protocolo"
							onclick="window.location.href='<html:rewrite page="/gerarRelatorioEmitirProtocoloDocumentoCobranca.do"/>'"/>
						</div>
						 
							<input name="Button2" type="button" class="bottonRightCol" value="Cancelar Documentos"
							onclick="if (confirm('Confirma remo��o?')) window.location.href='<html:rewrite page="/cancelarDocumentosCobrancaAction.do?idCobrancaAcaoAtividadeComando=${param.idCobrancaAcaoAtividadeEventual}"/>'"/>
						</td> 
						</logic:present>
						<logic:notPresent name="emitir">
						<td height="27">
						<div align="left"><input name="Button2" type="button"
							class="bottonRightCol" value="Emitir Protocolo" disabled="true"
							onclick="window.location.href='<html:rewrite page="/gerarRelatorioEmitirProtocoloDocumentoCobrancaCronograma.do"/><%= "?idCobrancaAcaoAtividadeCronograma=" + request.getAttribute("idCobrancaAcaoAtividadeCronograma") %>'"/>
						</div>
						</td>
						<td height="27"> 
							<input name="Button2" type="button" class="bottonRightCol" value="Cancelar Documentos" 
							onclick="if (confirm('Confirma remo��o?')) window.location.href='<html:rewrite page="/cancelarDocumentosCobrancaAction.do?idCobrancaAcaoAtividadeComando=${param.idCobrancaAcaoAtividadeEventual}"/>'"/>
						</td>				
						</logic:notPresent>
							<td height="27" colspan="2" align ="right">
						<logic:present name="idCobrancaAcaoAtividadeCronograma" scope="request">
							<input name="Button2" type="button"
								class="bottonRightCol" value="Emitir Documento de Cobran�a"
								onclick="window.location.href='<html:rewrite page="/gerarRelatorioComandoDocumentoCobrancaAction.do"/><%= "?idCobrancaAcaoAtividadeCronograma=" + request.getAttribute("idCobrancaAcaoAtividadeCronograma") %>'"/>
						</logic:present>
						<logic:notPresent name="idCobrancaAcaoAtividadeCronograma" scope="request">
							<input name="Button2" type="button"
								class="bottonRightCol" value="Emitir Documento de Cobran�a"
								onclick="window.location.href='<html:rewrite page="/gerarRelatorioComandoDocumentoCobrancaAction.do?idCobrancaAcaoAtividadeComando=${param.idCobrancaAcaoAtividadeEventual}"/>'"/>
						</logic:notPresent>
						<input name="Button2" type="button"
							class="bottonRightCol" value="Fechar"
							onClick="javascript:window.close();">
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		<p>&nbsp;</p>
		</td>
	</tr>
</table>
</form>
</body>
</html:html>
