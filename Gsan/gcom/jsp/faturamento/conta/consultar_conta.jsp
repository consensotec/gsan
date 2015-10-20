<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.Util"%>
<%@ page import="gcom.faturamento.conta.Conta"%>
<%@ page import="gcom.faturamento.conta.ContaHistorico"%>
<%@ page import="gcom.cadastro.cliente.ClienteConta"%>
<%@ page import="gcom.cadastro.cliente.ClienteContaAnterior"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript">
	function fechar(){
  		window.close();
	}
</script>
</head>

<body leftmargin="5" topmargin="5">
<table width="770" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="750" valign="top" class="centercoltext">

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
				<td class="parabg">Consultar Conta</td>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>

		<p>&nbsp;</p>

		<table width="100%" border="0">
			<tr>
				<td align="right">
					<a href="javascript: abrirPopupHelp('/gsan/help/help.jsp?mapIDHelpSet=faturamentoContaConsultar', 500, 700);">
						<span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span>
					</a>
				</td>
			</tr>
		</table>

		<logic:present name="conta" scope="session">
			<table width="100%" border="0">
				<tr>
					<td height="25"><strong>Matr&iacute;cula do Im&oacute;vel:</strong></td>
					
					<td width="120">
						<html:text name="conta" 
							property="imovel.id"
							size="12" 
							maxlength="10" 
							readonly="true"
							style="background-color:#EFEFEF; border:0" />
					</td>
					
					<td><strong>M&ecirc;s e Ano da Conta:</strong></td>
					
					<td>
						<input type="text" 
							name="referencia" 
							size="10"
							value=<%="" +Util.formatarMesAnoReferencia(((Conta)session.getAttribute("conta")).getReferencia())%>
							readonly="true" 
							style="background-color:#EFEFEF; border:0" />
					</td>
				</tr>

				<tr>
					<td height="25">
						<strong>M�s e Ano Cont�bil:</strong>
					</td>
						<td width="120">
							<input type="text" 
								name="mesAnoContabil" 
								size="10"
								value=<%="" +Util.formatarMesAnoReferencia(((Conta)session.getAttribute("conta")).getReferenciaContabil())%>
								readonly="true" 
								style="background-color:#EFEFEF; border:0" />
						</td>
					<td>
						<strong>M�s e Ano de baixa cont�bil:</strong>
					</td>

					<logic:present name="conta" property="referenciaBaixaContabil" scope="session">
						<td>
							<input type="text" 
								name="mesAnoBaixaContabil" 
								size="10"
								value=<%="" +Util.formatarMesAnoReferencia(((Conta)session.getAttribute("conta")).getReferenciaBaixaContabil())%>
								readonly="true" 
								style="background-color:#EFEFEF; border:0" />
						</td>
					</logic:present>
					
					<logic:notPresent name="conta" property="referenciaBaixaContabil" scope="session">
						<td>
							<input type="text" 
								name="mesAnoBaixaContabil" 
								size="10" 
								value=""
								readonly="true" 
								style="background-color:#EFEFEF; border:0" />
						</td>
					</logic:notPresent>
				</tr>
				
				<tr>
					<td width="183" height="25"><strong>Situa&ccedil;&atilde;o da Conta:</strong></td>
					<td colspan="1">
						<html:text name="conta"
							property="debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao"
							size="30" 
							readonly="true"
							style="background-color:#EFEFEF; border:0" /></td>
				    <td><strong>Perfil do Im�vel</strong></td>
				    <td colspan="1">
						<html:text name="imovelPerfil"
							property="descricao"
							size="30" 
							readonly="true"
							style="background-color:#EFEFEF; border:0" /></td>
				</tr>
				
				
				
				<tr>
					<td width="183" height="25"><strong>Usu�rio(s) Retifica��o:</strong></td>
				</tr>
				
				<logic:present name="colecaoUsuariosContaRetificada">
					<logic:iterate name="colecaoUsuariosContaRetificada" id="usuario">
						<tr>
							<td colspan="4">
								<html:text name="usuario" property="id"
								size="10" readonly="true"
								style="background-color:#EFEFEF; border:0" />
								<html:text name="usuario" property="nomeUsuario"
								size="40" readonly="true"
								style="background-color:#EFEFEF; border:0" />
							</td>
						</tr>	
					</logic:iterate>
				</logic:present>
				<logic:notPresent name="colecaoUsuariosContaRetificada">
					<tr>
						<td colspan="4">
							<input type="text"  name="usuario" 
							size="10" readonly="true" 
							style="background-color:#EFEFEF; border:0" />
							<input type="text"  name="usuario" 
							size="40"readonly="true" 
							style="background-color:#EFEFEF; border:0" />
						</td>
					</tr>	
				</logic:notPresent>
				
				<tr>
					<td width="183" height="25"><strong>Usu�rio(s) Revis�o:</strong></td>
				</tr>
				<logic:present name="colecaoUsuariosContaEmRevisao">
					<logic:iterate name="colecaoUsuariosContaEmRevisao" id="usuario">
						<tr>
							<td colspan="4">
								<html:text name="usuario" property="id"
								size="10" readonly="true"
								style="background-color:#EFEFEF; border:0" />
								<html:text name="usuario" property="nomeUsuario"
								size="40" readonly="true"
								style="background-color:#EFEFEF; border:0" />
							</td>
						</tr>
					</logic:iterate>
				</logic:present>
				
				<logic:notPresent name="colecaoUsuariosContaEmRevisao">
					<tr>
						<td colspan="4">
							<input type="text"  name="usuario" 
							size="10" readonly="true" 
							style="background-color:#EFEFEF; border:0" />
							<input type="text"  name="usuario" 
							size="40"readonly="true" 
							style="background-color:#EFEFEF; border:0" />
						</td>
					</tr>	
				</logic:notPresent>
				
				
				<tr>
					<td colspan="2"><strong>Usu�rio(s) Cancelamento:</strong></td>
				</tr>
				<logic:present name="colecaoUsuariosContaCancelada">
						<logic:iterate name="colecaoUsuariosContaCancelada" id="usuario">
							<tr>
								<td colspan="4">
									<html:text name="usuario" property="id"
									size="10" readonly="true"
									style="background-color:#EFEFEF; border:0" />
									<html:text name="usuario" property="nomeUsuario"
									size="40" readonly="true"
									style="background-color:#EFEFEF; border:0" />
								</td>
							</tr>	
						</logic:iterate>
					</logic:present>
					
					<logic:notPresent name="colecaoUsuariosContaCancelada">
						<tr>
							<td colspan="4">
								<input type="text"  name="usuario" 
								size="10" readonly="true" 
								style="background-color:#EFEFEF; border:0" />
								<input type="text"  name="usuario" 
								size="40"readonly="true" 
								style="background-color:#EFEFEF; border:0" />
							</td>
						</tr>	
					</logic:notPresent>
				
				

				<tr>
					<td><strong>Situa&ccedil;&atilde;o da Lig. de &Aacute;gua:</strong></td>
					<td>
						<logic:present name="conta" property="ligacaoAguaSituacao">
							<html:text name="conta" 
								property="ligacaoAguaSituacao.descricao"
								size="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0" />
						</logic:present> 
						
						<logic:notPresent name="conta" property="ligacaoAguaSituacao">
							<input type="text" 
								name="ligacaoAguaSituacao" 
								size="30"
								readonly="true" 
								style="background-color:#EFEFEF; border:0" />
						</logic:notPresent>
					</td>

					<td width="175"><strong>Situa&ccedil;&atilde;o da Lig. de Esgoto:</strong></td>

					<td width="122">
						<logic:present name="conta" property="ligacaoEsgotoSituacao">
							<html:text name="conta" 
								property="ligacaoEsgotoSituacao.descricao"
								size="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0" />
						</logic:present> 

						<logic:notPresent name="conta" property="ligacaoEsgotoSituacao">
							<input type="text" 
								name="ligacaoEsgotoSituacao" 
								size="30"
								readonly="true" 
								style="background-color:#EFEFEF; border:0" />
						</logic:notPresent>
					</td>
				</tr>

				<tr>
					<td><strong>Motivo N&atilde;o Entrega:</strong></td>
					<td colspan="2">
						<logic:present name="conta" property="motivoNaoEntregaDocumento">
							<html:text name="conta"
								property="motivoNaoEntregaDocumento.motivoNaoeEntregaDocumento"
								size="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0" />
						</logic:present> 
						
						<logic:notPresent name="conta" property="motivoNaoEntregaDocumento">
							<input type="text" 
								name="motivoNaoEntregaDocumento" 
								size="30"
								readonly="true" 
								style="background-color:#EFEFEF; border:0" />
						</logic:notPresent>
					</td>
				</tr>

				<tr>
					<td width="50%"><strong>Cobran&ccedil;a de Multa:</strong></td>
					<td>
						<logic:equal name="conta" property="indicadorCobrancaMulta" value="1">
						<input type="text" 
							value="SIM" 
							name="indicadorCobrancaMulta"
							size="4" 
							readonly="true"
							style="background-color:#EFEFEF; border:0" />
						</logic:equal> 

						<logic:equal name="conta" property="indicadorCobrancaMulta" value="2">
							<input type="text" 
								value="N�O" 
								name="indicadorCobrancaMulta"
								size="4" 
								readonly="true"
								style="background-color:#EFEFEF; border:0" />
						</logic:equal>
					</td>

					<td width="50%"><strong>Altera&ccedil;&atilde;o de Vencimento:</strong></td>
					<td>
						<logic:present name="conta" property="indicadorAlteracaoVencimento">
							<logic:equal name="conta" property="indicadorAlteracaoVencimento" value="1">
								<input type="text" 
									value="SIM"
									name="indicadorAlteracaoVencimento" 
									size="4" 
									readonly="true"
									style="background-color:#EFEFEF; border:0" />
							</logic:equal>
							
							<logic:equal name="conta" property="indicadorAlteracaoVencimento" value="2">
								<input type="text" 
									value="N�O"
									name="indicadorAlteracaoVencimento" 
									size="4" 
									readonly="true"
									style="background-color:#EFEFEF; border:0" />
							</logic:equal>
						</logic:present> 
						
						<logic:notPresent name="conta" property="indicadorAlteracaoVencimento">
							<input type="text" 
								name="indicadorAlteracaoVencimento" 
								size="4"
								readonly="true" 
								style="background-color:#EFEFEF; border:0" />
						</logic:notPresent>
					</td>
				</tr>

				<tr>
					<td><strong>Consumo de &Aacute;gua:</strong></td>
					<td colspan="2">
						<logic:present name="conta" property="consumoAgua">
							<html:text name="conta" 
								property="consumoAgua" 
								size="7"
								readonly="true" 
								style="background-color:#EFEFEF; border:0" /> m&#179;
						</logic:present> 
						
						<logic:notPresent name="conta" property="consumoAgua">
							<input type="text" 
								name="consumoAgua" 
								size="7" 
								readonly="true"
								style="background-color:#EFEFEF; border:0" />
						</logic:notPresent>
					</td>
				</tr>

				<tr>
					<td><strong>Consumo de Esgoto:</strong></td>
					<td>
						<logic:present name="conta" property="consumoEsgoto">
							<html:text name="conta" 
								property="consumoEsgoto" 
								size="7"
								readonly="true" 
								style="background-color:#EFEFEF; border:0" /> m&#179;
						</logic:present> 

						<logic:notPresent name="conta" property="consumoEsgoto">
							<input type="text" 
								name="consumoEsgoto" 
								size="7" 
								readonly="true"
								style="background-color:#EFEFEF; border:0" />
						</logic:notPresent>
					</td>
					
					<td><strong>Percentual de Esgoto:</strong></td>
						<td>
							<input type="text" 
								name="percentualEsgoto"
								value=<%="" + Util.formatarMoedaReal(((Conta)session.getAttribute("conta")).getPercentualEsgoto())%>
								size="5" 
								readonly="true"
								style="background-color:#EFEFEF; border:0" />
						</td>
				</tr>

				<tr>
					<td><strong>Valor de &Aacute;gua:</strong></td>
					<td>
						<input type="text" 
							name="valorAgua"
							value=<%="" + Util.formatarMoedaReal(((Conta)session.getAttribute("conta")).getValorAgua())%>
							size="20" 
							readonly="true"
							style="background-color:#EFEFEF; border:0; text-align: right;" />
					</td>
					
					<td><strong>Valor de Esgoto:</strong></td>
					<td>
						<input type="text" 
							name="valorEsgoto"
							value=<%="" + Util.formatarMoedaReal(((Conta)session.getAttribute("conta")).getValorEsgoto())%>
							size="20" 
							readonly="true"
							style="background-color:#EFEFEF; border:0; text-align: right;" /></td>
				</tr>

				<tr>
					<td><strong>Valor dos D&eacute;bitos:</strong></td>
					<td>
						<input type="text" 
							name="debitos"
							value=<%="" + Util.formatarMoedaReal(((Conta)session.getAttribute("conta")).getDebitos())%>
							size="20" 
							readonly="true"
							style="background-color:#EFEFEF; border:0; text-align: right;" /></td>

					<td><strong>Valor dos Cr&eacute;ditos:</strong></td>
					<td>
						<logic:present name="conta" property="valorCreditos">
							<input type="text" 
								name="valorCreditos"
								value=<%="" + Util.formatarMoedaReal(((Conta)session.getAttribute("conta")).getValorCreditos())%>
								size="20" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; text-align: right;" />
						</logic:present> 

						<logic:notPresent name="conta" property="valorCreditos">
							<input type="text" 
								name="valorCreditos" 
								size="20" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; text-align: right;" />
						</logic:notPresent></td>
				</tr>

				<tr>
					<td><strong>Valor Total da Conta:</strong></td>
					<td>
						<input type="text" 
							name="valorTotalConta"
							value=<%="" + Util.formatarMoedaReal(((Conta)session.getAttribute("conta")).getValorTotal())%>
							readonly="true"
							style="background-color:#EFEFEF; border:0; text-align: right;" /></td>

					<td><strong>D&eacute;bito Autom&aacute;tico:</strong></td>
					<td>
						<logic:present name="conta" property="indicadorDebitoConta">
							<logic:equal name="conta" property="indicadorDebitoConta" value="1">
								<input type="text" 
									value="SIM" 
									name="indicadorDebitoConta"
									size="4" 
									readonly="true"
									style="background-color:#EFEFEF; border:0" />
							</logic:equal>
							
							<logic:equal name="conta" property="indicadorDebitoConta" value="2">
								<input type="text" 
									value="N�O" 
									name="indicadorDebitoConta"
									size="4" 
									readonly="true"
									style="background-color:#EFEFEF; border:0" />
							</logic:equal>
						</logic:present> 
						
						<logic:notPresent name="conta" property="indicadorDebitoConta">
							<input type="text" 
								name="indicadorDebitoConta" 
								size="4"
								readonly="true" 
								style="background-color:#EFEFEF; border:0" />
						</logic:notPresent>
					</td>
				</tr>

				<tr>
					<td><strong>Motivo da Inclus&atilde;o:</strong></td>
					<td colspan="1">
						<logic:present name="conta" property="contaMotivoInclusao">
							<html:text name="conta"
								property="contaMotivoInclusao.descricaoMotivoInclusaoConta"
								size="35" 
								readonly="true"
								style="background-color:#EFEFEF; border:0" />
						</logic:present> 
						<logic:notPresent name="conta" property="contaMotivoInclusao">
							<input type="text" 
								name="contaMotivoInclusao" 
								size="35"
								readonly="true" 
								style="background-color:#EFEFEF; border:0" />
						</logic:notPresent>
					</td>
					
					<logic:present name="debitoAutomaticoMovimento" scope="request">				
						<td><strong>Situa&ccedil;&atilde;o do D&eacute;bito:</strong></td>
						<td>
							<logic:present name="debitoAutomaticoMovimento" property="debitoAutomaticoRetornoCodigo" scope="request">
								<input type="text" 
									value="<bean:write name="debitoAutomaticoMovimento" property="debitoAutomaticoRetornoCodigo.descricaoDebitoAutomaticoRetornoCodigo" />"
									size="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0" />
							</logic:present>
							<logic:notPresent name="debitoAutomaticoMovimento" property="debitoAutomaticoRetornoCodigo" scope="request">
								<input type="text" 
									size="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0" />
							</logic:notPresent>							
						</td>
					</logic:present>
				</tr>

				<tr>
					<td><strong>Motivo da Retifica&ccedil;&atilde;o:</strong></td>
					<td colspan="1">
						<logic:present name="conta" property="contaMotivoRetificacao">
							<html:text name="conta"
								property="contaMotivoRetificacao.descricao"
								size="35" 
								readonly="true"
								style="background-color:#EFEFEF; border:0" />
						</logic:present> 

						<logic:notPresent name="conta" property="contaMotivoRetificacao">
							<input type="text" 
								name="contaMotivoRetificacao" 
								size="35"
								readonly="true" 
								style="background-color:#EFEFEF; border:0" />
						</logic:notPresent>
					</td>
					
					<logic:present name="debitoAutomaticoMovimento" scope="request">
						<td><strong>Data de Envio:</strong></td>
						<td>
							<logic:present name="debitoAutomaticoMovimento" property="envioBanco">
								<input type="text" 
									value="<bean:write name="debitoAutomaticoMovimento" property="envioBanco" formatKey="datehour.format" />"
									size="16" 
									readonly="true"
									style="background-color:#EFEFEF; border:0" />
							</logic:present>
							<logic:notPresent name="debitoAutomaticoMovimento" property="envioBanco">
								<input type="text" 
									size="16" 
									readonly="true"
									style="background-color:#EFEFEF; border:0" />
							</logic:notPresent>							
						</td>
					</logic:present>
				</tr>

				<tr>
					<td><strong>Motivo do Cancelamento:</strong></td>
					<td colspan="1">
						<logic:present name="conta" property="contaMotivoCancelamento">
							<html:text name="conta"
								property="contaMotivoCancelamento.descricaoMotivoCancelamentoConta"
								size="35" 
								readonly="true"
								style="background-color:#EFEFEF; border:0" />
						</logic:present> 
						<logic:notPresent name="conta" property="contaMotivoCancelamento">
							<input type="text" 
								name="contaMotivoCancelamento" 
								size="35"
								readonly="true" 
								style="background-color:#EFEFEF; border:0" />
						</logic:notPresent>
					</td>
					
					<logic:present name="debitoAutomaticoMovimento" scope="request">
						<td><strong>Data de Retorno:</strong></td>
						<td>
							<logic:present name="debitoAutomaticoMovimento" property="retornoBanco">
								<input type="text" 
									value="<bean:write name="debitoAutomaticoMovimento" property="retornoBanco" formatKey="datehour.format" />"
									size="16" 
									readonly="true"
									style="background-color:#EFEFEF; border:0" />
							</logic:present>
							<logic:notPresent name="debitoAutomaticoMovimento" property="retornoBanco">
								<input type="text" 
									size="16" 
									readonly="true"
									style="background-color:#EFEFEF; border:0" />
							</logic:notPresent>							
						</td>
					</logic:present>
					
				</tr>

				<tr>
					<td><strong>Motivo da Revis&atilde;o:</strong></td>
					<td colspan="1">
						<logic:present name="conta" property="contaMotivoRevisao">
							<html:text name="conta"
								property="contaMotivoRevisao.descricaoMotivoRevisaoConta"
								size="35" 
								readonly="true"
								style="background-color:#EFEFEF; border:0" />
						</logic:present> 

						<logic:notPresent name="conta" property="contaMotivoRevisao">
							<input type="text" 
								name="contaMotivoRevisao" 
								size="35"
								readonly="true" 
								style="background-color:#EFEFEF; border:0" />
						</logic:notPresent></td>
						
					<logic:present name="debitoAutomaticoMovimento" scope="request">
						<td><strong>NSA Envio:</strong></td>
						<td>
							<logic:present name="debitoAutomaticoMovimento" property="numeroSequenciaArquivoEnviado">
								<input type="text" 
									value="<bean:write name="debitoAutomaticoMovimento" property="numeroSequenciaArquivoEnviado" />"
									size="8" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; text-align: right;" />
							</logic:present>
							<logic:notPresent name="debitoAutomaticoMovimento" property="numeroSequenciaArquivoEnviado">
								<input type="text" 
									size="8" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; text-align: right;" />
							</logic:notPresent>							
						</td>
					</logic:present>
						
				</tr>
				
				<tr>
					<td><strong>Matr�cula do Im�vel Origem:</strong></td>
					<td colspan="1">
						
						<logic:present name="conta" property="origem">
							<html:text name="conta" 
								property="origem.conta.imovel.id"
								size="12" 
								maxlength="10" 
								readonly="true"
								style="background-color:#EFEFEF; border:0" />
						</logic:present>
						
						<logic:notPresent name="conta" property="origem">
							<input type="text" 
								name="imovelOrigem" 
								size="12" 
								maxlength="10"
								readonly="true" 
								style="background-color:#EFEFEF; border:0" />
						</logic:notPresent>
					</td>
					
					<logic:present name="debitoAutomaticoMovimento" scope="request">
						<td><strong>NSA Retorno:</strong></td>
						<td>
							<logic:present name="debitoAutomaticoMovimento" property="numeroSequenciaArquivoRecebido">
								<input type="text" 
									value="<bean:write name="debitoAutomaticoMovimento" property="numeroSequenciaArquivoRecebido" />"
									size="8" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; text-align: right;" />									
							</logic:present>
							<logic:notPresent name="debitoAutomaticoMovimento" property="numeroSequenciaArquivoRecebido">
								<input type="text" 
									size="8" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; text-align: right;" />
							</logic:notPresent>							
						</td>
					</logic:present>
					
				</tr>
				
				<logic:present name="consumoContratado" scope="session">
					<tr>
						<td><strong>Consumo Contratado:</strong></td>
						<td colspan="1">
								<input type="text" 
									value="<bean:write name="consumoContratado" />"
									size="12" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; " />
						</td>
					</tr>
				</logic:present>
				
				<logic:present name="contaPaga" scope="session">
					<tr>
						<td colspan="2">
						<hr>
						</td>
					</tr>
					
					<tr>
						<td colspan="4">Dados do Pagamento:</td>
					</tr>
					
					<tr>
						<td><strong>Nosso N�mero:</strong></td>
						<td align="right">
						<div align="left"><input name="nossoNumero" type="text" readonly
							style="background-color:#EFEFEF; border:0" size="18" maxlength="18"
							value="${requestScope.nossoNumero}"></div>
						</td>
					</tr>
					
					<logic:notEmpty name="arrecadador" scope="request">
						<tr>
							<td><strong>NSA do Movimento:</strong></td>
							<td align="right">
							<div align="left"><input name="nsaMovimento" type="text" readonly
								style="background-color:#EFEFEF; border:0" size="10" maxlength="10"
								value="${requestScope.arrecadador.numeroSequencialArquivo}"></div>
							</td>
						</tr>
						
						<tr>
							<td><strong>Valor do Movimento:</strong></td>
							<td align="right">
							<div align="left"><input name="valorMovimento" type="text" readonly
								style="background-color:#EFEFEF; border:0" size="12" maxlength="12"
								value="${requestScope.arrecadador.valorTotalMovimento}"></div>
							</td>
						</tr>
					</logic:notEmpty>
					
					<tr>
						<td><strong>Data Pagamento:</strong></td>
						<td align="right">
						<div align="left"><input name="dataPagamento" type="text" readonly
							style="background-color:#EFEFEF; border:0" size="10" maxlength="10"
							value="<bean:write name="pagamento" property="dataPagamento" formatKey="date.format" scope="request"/>"></div>
						</td>
					</tr>
					
					<logic:present name="indicadorAceitacao" scope="request">
						<tr>
							<td><strong>Pagamento Aceito:</strong></td>
							<td align="right">
								<logic:equal name="indicadorAceitacao" value="1" scope="request">
									<div align="left"><input name="pagamentoAceito" type="text" readonly
										style="background-color:#EFEFEF; border:0" size="3" maxlength="3"
										value="SIM"></div>
								</logic:equal>
								<logic:notEqual name="indicadorAceitacao" value="1">
									<div align="left"><input name="pagamentoAceito" type="text" readonly
										style="background-color:#EFEFEF; border:0" size="3" maxlength="3"
										value="N�O"></div>
								</logic:notEqual>
							</td>
						</tr>
					</logic:present>
				</logic:present>
			</table>
		</logic:present> 
		
		<%--FIM CONTA --%> 
		<%-- INICIO CONTA HISTORICO --%> 
		
		<logic:present name="contaHistorico" scope="session">
			<table width="100%" border="0">
				<tr>
					<td height="25"><strong>Matr&iacute;cula do Im&oacute;vel:</strong></td>
					<td width="120">
						<html:text name="contaHistorico"
							property="imovel.id" 
							size="12" 
							maxlength="10" 
							readonly="true"
							style="background-color:#EFEFEF; border:0" /></td>
					<td><strong>M&ecirc;s e Ano da Conta:</strong></td>
					<td>
						<input type="text" 
							name="referencia" 
							size="10"
							value=<%=""+Util.formatarMesAnoReferencia(((ContaHistorico)session.getAttribute("contaHistorico")).getAnoMesReferenciaConta())%>
							readonly="true" 
							style="background-color:#EFEFEF; border:0" /></td>
				</tr>

				<tr>
					<td height="25">
						<strong>M�s e Ano cont�bil:</strong>
					</td>
					<logic:present name="contaHistorico" property="anoMesReferenciaContabil" scope="session">
						<td width="120">
							<input type="text" 
							name="mesAnoContabil" 
							size="10"
							value=<%=""+Util.formatarMesAnoReferencia(((ContaHistorico)session.getAttribute("contaHistorico")).getAnoMesReferenciaContabil())%>
							readonly="true" 
							style="background-color:#EFEFEF; border:0" />
						</td>
					</logic:present>
					
					<logic:notPresent name="contaHistorico" property="anoMesReferenciaContabil" scope="session">
						<td width="120">
							<input type="text" 
								name="mesAnoContabil" 
								size="10" 
								value=""
								readonly="true" 
								style="background-color:#EFEFEF; border:0" />
						</td>
					</logic:notPresent>
					
					<td>
						<strong>M�s e Ano de baixa cont�bil:</strong>
					</td>
					
					<logic:present name="contaHistorico" property="anoMesReferenciaBaixaContabil" scope="session">
						<td>
							<input type="text" 
								name="mesAnoBaixaContabil" 
								size="10"
								value=<%=""+Util.formatarMesAnoReferencia(((ContaHistorico)session.getAttribute("contaHistorico")).getAnoMesReferenciaBaixaContabil())%>
								readonly="true" 
								style="background-color:#EFEFEF; border:0" />
						</td>
					</logic:present>
					
					<logic:notPresent name="contaHistorico" property="anoMesReferenciaBaixaContabil" scope="session">
						<td>
							<input type="text" 
								name="mesAnoBaixaContabil" 
								size="10" 
								value=""
								readonly="true" 
								style="background-color:#EFEFEF; border:0" />
						</td>
					</logic:notPresent>
				</tr>

				<tr>
					<td width="183" height="25"><strong>Situa&ccedil;&atilde;o da Conta:</strong></td>

					<td colspan="1">
						<html:text name="contaHistorico"
							property="debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao"
							size="30" 
							readonly="true"
							style="background-color:#EFEFEF; border:0" />
					</td>
					
					<td><strong>Perfil do Im�vel</strong></td>
				    <td colspan="1">
						<html:text name="imovelPerfilHistorico"
							property="descricao"
							size="30" 
							readonly="true"
							style="background-color:#EFEFEF; border:0" /></td>
				</tr>
				
				
				<tr>
					<td width="183" height="25"><strong>Usu�rio(s) Retifica��o:</strong></td>
				</tr>
				
				<logic:present name="colecaoUsuariosContaRetificada">
					<logic:iterate name="colecaoUsuariosContaRetificada" id="usuario">
						<tr>
							<td colspan="4">
								<html:text name="usuario" property="id"
								size="10" readonly="true"
								style="background-color:#EFEFEF; border:0" />
								<html:text name="usuario" property="nomeUsuario"
								size="40" readonly="true"
								style="background-color:#EFEFEF; border:0" />
							</td>
						</tr>	
					</logic:iterate>
				</logic:present>
				<logic:notPresent name="colecaoUsuariosContaRetificada">
					<tr>
						<td colspan="4">
							<input type="text"  name="usuario" 
							size="10" readonly="true" 
							style="background-color:#EFEFEF; border:0" />
							<input type="text"  name="usuario" 
							size="40"readonly="true" 
							style="background-color:#EFEFEF; border:0" />
						</td>
					</tr>	
				</logic:notPresent>
				
				<tr>
					<td width="183" height="25"><strong>Usu�rio(s) Revis�o:</strong></td>
				</tr>
				<logic:present name="colecaoUsuariosContaEmRevisao">
					<logic:iterate name="colecaoUsuariosContaEmRevisao" id="usuario">
						<tr>
							<td colspan="4">
								<html:text name="usuario" property="id"
								size="10" readonly="true"
								style="background-color:#EFEFEF; border:0" />
								<html:text name="usuario" property="nomeUsuario"
								size="40" readonly="true"
								style="background-color:#EFEFEF; border:0" />
							</td>
						</tr>
					</logic:iterate>
				</logic:present>
				
				<logic:notPresent name="colecaoUsuariosContaEmRevisao">
					<tr>
						<td colspan="4">
							<input type="text"  name="usuario" 
							size="10" readonly="true" 
							style="background-color:#EFEFEF; border:0" />
							<input type="text"  name="usuario" 
							size="40"readonly="true" 
							style="background-color:#EFEFEF; border:0" />
						</td>
					</tr>	
				</logic:notPresent>
				
				
				<tr>
					<td colspan="2"><strong>Usu�rio(s) Cancelamento:</strong></td>
				</tr>
				<logic:present name="colecaoUsuariosContaCancelada">
						<logic:iterate name="colecaoUsuariosContaCancelada" id="usuario">
							<tr>
								<td colspan="4">
									<html:text name="usuario" property="id"
									size="10" readonly="true"
									style="background-color:#EFEFEF; border:0" />
									<html:text name="usuario" property="nomeUsuario"
									size="40" readonly="true"
									style="background-color:#EFEFEF; border:0" />
								</td>
							</tr>	
						</logic:iterate>
					</logic:present>
					
					<logic:notPresent name="colecaoUsuariosContaCancelada">
						<tr>
							<td colspan="4">
								<input type="text"  name="usuario" 
								size="10" readonly="true" 
								style="background-color:#EFEFEF; border:0" />
								<input type="text"  name="usuario" 
								size="40"readonly="true" 
								style="background-color:#EFEFEF; border:0" />
							</td>
						</tr>	
					</logic:notPresent>

				<tr>
					<td><strong>Situa&ccedil;&atilde;o da Lig. de &Aacute;gua:</strong></td>
					<td>
						<logic:present name="contaHistorico" property="ligacaoAguaSituacao">
							<html:text name="contaHistorico"
								property="ligacaoAguaSituacao.descricao" 
								size="20"
								readonly="true" style="background-color:#EFEFEF; border:0" />
						</logic:present> 

						<logic:notPresent name="contaHistorico" property="ligacaoAguaSituacao">
							<input type="text" 
								name="ligacaoAguaSituacao" 
								size="20"
								readonly="true" 
								style="background-color:#EFEFEF; border:0" />
						</logic:notPresent>
					</td>

					<td width="175"><strong>Situa&ccedil;&atilde;o da Lig. de Esgoto:</strong></td>
					<td width="122">
						<logic:present name="contaHistorico" property="ligacaoEsgotoSituacao">
							<html:text name="contaHistorico"
								property="ligacaoEsgotoSituacao.descricao" 
								size="20"
								readonly="true" 
								style="background-color:#EFEFEF; border:0" />
						</logic:present> 
						<logic:notPresent name="contaHistorico" property="ligacaoEsgotoSituacao">
							<input type="text" 
								name="ligacaoEsgotoSituacao" 
								size="20"
								readonly="true" 
								style="background-color:#EFEFEF; border:0" />
						</logic:notPresent>
					</td>
				</tr>

				<tr>
					<td><strong>Motivo N&atilde;o Entrega:</strong></td>
					<td colspan="2">
						<logic:present name="contaHistorico" property="motivoNaoEntregaDocumento">
							<html:text name="contaHistorico"
								property="motivoNaoEntregaDocumento.motivoNaoeEntregaDocumento"
								size="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0" />
						</logic:present> 
						<logic:notPresent name="contaHistorico" property="motivoNaoEntregaDocumento">
							<input type="text" 
								name="motivoNaoEntregaDocumento" 
								size="30"
								readonly="true" 
								style="background-color:#EFEFEF; border:0" />
						</logic:notPresent>
					</td>
				</tr>

				<tr>
					<td width="50%"><strong>Cobran&ccedil;a de Multa:</strong></td>
					<td>
						<logic:equal name="contaHistorico" property="indicadorCobrancaMulta" value="1">
							<input type="text" 
								value="SIM" 
								name="indicadorCobrancaMulta"
								size="4" 
								readonly="true"
								style="background-color:#EFEFEF; border:0" />
						</logic:equal> 

						<logic:equal name="contaHistorico" property="indicadorCobrancaMulta" value="2">
							<input type="text" 
								value="N�O" 
								name="indicadorCobrancaMulta"
								size="4" 
								readonly="true"
								style="background-color:#EFEFEF; border:0" />
						</logic:equal>
					</td>

					<td width="50%"><strong>Altera&ccedil;&atilde;o de Vencimento:</strong></td>
					<td>
						<logic:present name="contaHistorico" property="indicadorAlteracaoVencimento">
							<logic:equal name="contaHistorico" property="indicadorAlteracaoVencimento" value="1">
								<input type="text" 
									value="SIM"
									name="indicadorAlteracaoVencimento" 
									size="4" 
									readonly="true"
									style="background-color:#EFEFEF; border:0" />
							</logic:equal>
							
							<logic:equal name="contaHistorico" property="indicadorAlteracaoVencimento" value="2">
								<input type="text" 
									value="N�O"
									name="indicadorAlteracaoVencimento" 
									size="4" 
									readonly="true"
									style="background-color:#EFEFEF; border:0" />
							</logic:equal>
						</logic:present> 
						
						<logic:notPresent name="contaHistorico" property="indicadorAlteracaoVencimento">
							<input type="text" 
								name="indicadorAlteracaoVencimento" 
								size="4"
								readonly="true" 
								style="background-color:#EFEFEF; border:0" />
						</logic:notPresent>
					</td>
				</tr>

				<tr>
					<td><strong>Consumo de &Aacute;gua:</strong></td>
					<td colspan="2">
						<logic:present name="contaHistorico" property="consumoAgua">
							<html:text name="contaHistorico" 
								property="consumoAgua" 
								size="7"
								readonly="true" 
								style="background-color:#EFEFEF; border:0" /> m&#179;
						</logic:present> 
						<logic:notPresent name="contaHistorico" property="consumoAgua">
							<input type="text" 
								name="consumoAgua" 
								size="7" 
								readonly="true"
								style="background-color:#EFEFEF; border:0" />
						</logic:notPresent>
					</td>
				</tr>

				<tr>
					<td><strong>Consumo de Esgoto:</strong></td>
					<td>
						<logic:present name="contaHistorico" property="consumoEsgoto">
							<html:text name="contaHistorico" 
								property="consumoEsgoto" 
								size="7"
								readonly="true" 
								style="background-color:#EFEFEF; border:0" /> m&#179;
						</logic:present> 

						<logic:notPresent name="contaHistorico" property="consumoEsgoto">
							<input type="text" 
								name="consumoEsgoto" 
								size="7" 
								readonly="true"
								style="background-color:#EFEFEF; border:0" />
						</logic:notPresent>
					</td>
					
					<td><strong>Percentual de Esgoto:</strong></td>
					
					<td>
						<input type="text" 
							name="percentualEsgoto"
							value=<%="" + Util.formatarMoedaReal(((ContaHistorico)session.getAttribute("contaHistorico")).getPercentualEsgoto())%>
							size="5" 
							readonly="true"
							style="background-color:#EFEFEF; border:0" /></td>
				</tr>

				<tr>
					<td><strong>Valor de &Aacute;gua:</strong></td>
					<td>
						<input type="text" 
							name="valorAgua"
							value=<%="" + Util.formatarMoedaReal(((ContaHistorico)session.getAttribute("contaHistorico")).getValorAgua())%>
							size="20" 
							readonly="true"
							style="background-color:#EFEFEF; border:0" /></td>

					<td><strong>Valor de Esgoto:</strong></td>
					<td>
						<input type="text" 
							name="valorEsgoto"
							value=<%="" + Util.formatarMoedaReal(((ContaHistorico)session.getAttribute("contaHistorico")).getValorEsgoto())%>
							size="20" 
							readonly="true"
							style="background-color:#EFEFEF; border:0" /></td>
				</tr>

				<tr>
					<td><strong>Valor dos D&eacute;bitos:</strong></td>
					<td>
						<input type="text" 
							name="debitos"
							value=<%="" + Util.formatarMoedaReal(((ContaHistorico)session.getAttribute("contaHistorico")).getValorDebitos())%>
							size="20" 
							readonly="true"
							style="background-color:#EFEFEF; border:0" /></td>

					<td><strong>Valor dos Cr&eacute;ditos:</strong></td>
					<td>
						<logic:present name="contaHistorico" property="valorCreditos">
							<input type="text" 
								name="valorCreditos"
								value=<%="" + Util.formatarMoedaReal(((ContaHistorico)session.getAttribute("contaHistorico")).getValorCreditos())%>
								size="20" 
								readonly="true"
								style="background-color:#EFEFEF; border:0" />
						</logic:present> 
						<logic:notPresent name="contaHistorico" property="valorCreditos">
							<input type="text" 
								name="valorCreditos" 
								size="20" 
								readonly="true"
								style="background-color:#EFEFEF; border:0" />
						</logic:notPresent>
					</td>
				</tr>

				<tr>
					<td><strong>Valor Total da Conta:</strong></td>
					<td>
						<input type="text" 
							name="valorTotalConta"
							value=<%="" + Util.formatarMoedaReal(((ContaHistorico)session.getAttribute("contaHistorico")).getValorTotal())%>
							readonly="true" 
							style="background-color:#EFEFEF; border:0" /></td>

					<td><strong>D&eacute;bito Autom&aacute;tico:</strong></td>
					<td>
						<logic:present name="contaHistorico" property="indicadorDebitoConta">
							<logic:equal name="contaHistorico" property="indicadorDebitoConta" value="1">
								<input type="text" value="SIM" 
									name="indicadorDebitoConta"
									size="4" 
									readonly="true"
									style="background-color:#EFEFEF; border:0" />
							</logic:equal>
							<logic:equal name="contaHistorico" property="indicadorDebitoConta" value="2">
								<input type="text" 
									value="N�O" 
									name="indicadorDebitoConta"
									size="4" 
									readonly="true"
									style="background-color:#EFEFEF; border:0" />
							</logic:equal>
						</logic:present> 
						
						<logic:notPresent name="contaHistorico" property="indicadorDebitoConta">
							<input type="text" 
								name="indicadorDebitoConta" 
								size="4"
								readonly="true" 
								style="background-color:#EFEFEF; border:0" />
					</logic:notPresent></td>
				</tr>

				<tr>
					<td><strong>Motivo da Inclus&atilde;o:</strong></td>
					<td colspan="1">
						<logic:present name="contaHistorico" property="contaMotivoInclusao">
							<html:text name="contaHistorico"
								property="contaMotivoInclusao.descricaoMotivoInclusaoConta"
								size="35" 
								readonly="true"
								style="background-color:#EFEFEF; border:0" />
						</logic:present> 
						<logic:notPresent name="contaHistorico" property="contaMotivoInclusao">
							<input type="text" 
								name="contaMotivoInclusao" 
								size="35"
								readonly="true" 
								style="background-color:#EFEFEF; border:0" />
						</logic:notPresent>
					</td>
					
					<logic:present name="debitoAutomaticoMovimento" scope="request">
						<td><strong>Situa&ccedil;&atilde;o do D&eacute;bito:</strong></td>
						<td>
							<logic:present name="debitoAutomaticoMovimento" property="debitoAutomaticoRetornoCodigo">
								<input type="text" 
									value="<bean:write name="debitoAutomaticoMovimento" property="debitoAutomaticoRetornoCodigo.descricaoDebitoAutomaticoRetornoCodigo" />"
									size="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0" />
							</logic:present>
							<logic:notPresent name="debitoAutomaticoMovimento" property="debitoAutomaticoRetornoCodigo">
								<input type="text" 
									size="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0" />
							</logic:notPresent>							
						</td>
					</logic:present>
					
				</tr>

				<tr>
					<td><strong>Motivo da Retifica&ccedil;&atilde;o:</strong></td>
					<td colspan="1">
						<logic:present name="contaHistorico" property="contaMotivoRetificacao">
							<html:text name="contaHistorico"
								property="contaMotivoRetificacao.descricao"
								size="35" 
								readonly="true"
								style="background-color:#EFEFEF; border:0" />
						</logic:present> 
						<logic:notPresent name="contaHistorico" property="contaMotivoRetificacao">
							<input type="text" 
								name="contaMotivoRetificacao" 
								size="35"
								readonly="true" 
								style="background-color:#EFEFEF; border:0" />
						</logic:notPresent>
					</td>
					
					<logic:present name="debitoAutomaticoMovimento" scope="request">
						<td><strong>Data de Envio:</strong></td>
						<td>
							<logic:present name="debitoAutomaticoMovimento" property="envioBanco">
								<input type="text" 
									value="<bean:write name="debitoAutomaticoMovimento" property="envioBanco" formatKey="datehour.format" />"
									size="16" 
									readonly="true"
									style="background-color:#EFEFEF; border:0" />
							</logic:present>
							<logic:notPresent name="debitoAutomaticoMovimento" property="envioBanco">
								<input type="text" 
									size="16" 
									readonly="true"
									style="background-color:#EFEFEF; border:0" />
							</logic:notPresent>							
						</td>
					</logic:present>
					
				</tr>

				<tr>
					<td><strong>Motivo do Cancelamento:</strong></td>
					<td colspan="1">
						<logic:present name="contaHistorico" property="contaMotivoCancelamento">
							<html:text name="contaHistorico"
								property="contaMotivoCancelamento.descricaoMotivoCancelamentoConta"
								size="35" 
								readonly="true"
								style="background-color:#EFEFEF; border:0" />
						</logic:present> 
						<logic:notPresent name="contaHistorico" property="contaMotivoCancelamento">
							<input type="text" 
								name="contaMotivoCancelamento" 
								size="35"
								readonly="true" 
								style="background-color:#EFEFEF; border:0" />
						</logic:notPresent>
					</td>
					
					<logic:present name="debitoAutomaticoMovimento" scope="request">
						<td><strong>Data de Retorno:</strong></td>
						<td>
							<logic:present name="debitoAutomaticoMovimento" property="retornoBanco">
								<input type="text" 
									value="<bean:write name="debitoAutomaticoMovimento" property="retornoBanco" formatKey="datehour.format" />"
									size="16" 
									readonly="true"
									style="background-color:#EFEFEF; border:0" />
							</logic:present>
							<logic:notPresent name="debitoAutomaticoMovimento" property="retornoBanco">
								<input type="text" 
									size="16" 
									readonly="true"
									style="background-color:#EFEFEF; border:0" />
							</logic:notPresent>							
						</td>
					</logic:present>
					
				</tr>

				<tr>
					<td><strong>Motivo da Revis&atilde;o: dsd</strong></td>
					<td colspan="1">
						<logic:present name="contaHistorico" property="contaMotivoRevisao">
							<html:text name="contaHistorico"
								property="contaMotivoRevisao.descricaoMotivoRevisaoConta"
								size="35" 
								readonly="true"
								style="background-color:#EFEFEF; border:0" />
						</logic:present> 
						<logic:notPresent name="contaHistorico" property="contaMotivoRevisao">
							<input type="text" 
								name="contaMotivoRevisao" 
								size="35"
								readonly="true" 
								style="background-color:#EFEFEF; border:0" />
						</logic:notPresent>
					</td>
					
					<logic:present name="debitoAutomaticoMovimento" scope="request">
						<td><strong>NSA Envio:</strong></td>
						<td>
							<logic:present name="debitoAutomaticoMovimento" property="numeroSequenciaArquivoEnviado">
								<input type="text" 
									value="<bean:write name="debitoAutomaticoMovimento" property="numeroSequenciaArquivoEnviado" />"
									size="8" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; text-align: right;" />
							</logic:present>
							<logic:notPresent name="debitoAutomaticoMovimento" property="numeroSequenciaArquivoEnviado">
								<input type="text" 
									size="8" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; text-align: right;" />
							</logic:notPresent>							
						</td>
					</logic:present>
					
				</tr>
				
				<tr>
					<td><strong>Matr�cula do Im�vel Origem:</strong></td>
					<td colspan="1">
						<logic:present name="conta" property="origem">
							<html:text name="conta" 
								property="origem.conta.imovel.id"
								size="12" 
								maxlength="10" 
								readonly="true"
								style="background-color:#EFEFEF; border:0" />
						</logic:present>
						
						<logic:notPresent name="conta" property="origem">
							<input type="text" 
								name="imovelOrigem" 
								size="12" 
								maxlength="10"
								readonly="true" 
								style="background-color:#EFEFEF; border:0" />
						</logic:notPresent>
						
					</td>
					
					<logic:present name="debitoAutomaticoMovimento" scope="request">
						<td><strong>NSA Retorno:</strong></td>
						<td>
							<logic:present name="debitoAutomaticoMovimento" property="numeroSequenciaArquivoRecebido">
								<input type="text" 
									value="<bean:write name="debitoAutomaticoMovimento" property="numeroSequenciaArquivoRecebido" />"
									size="8" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; text-align: right;" />
							</logic:present>
							<logic:notPresent name="debitoAutomaticoMovimento" property="numeroSequenciaArquivoRecebido">
								<input type="text" 
									size="8" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; text-align: right;" />
							</logic:notPresent>							
						</td>
					</logic:present>
					
				</tr>
				
				<logic:present name="consumoContratado" scope="session">
					<tr>
						<td><strong>Consumo Contratado:</strong></td>
						<td colspan="1">
								<input type="text" 
									value="<bean:write name="consumoContratado" />"
									size="12" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; " />
						</td>
					</tr>
				</logic:present>
				
				<logic:present name="contaPaga" scope="session">
					<tr>
						<td colspan="4">
						<hr>
						</td>
					</tr>
					
					<tr>
						<td colspan="2">Dados do Pagamento:</td>
					</tr>
					
					<tr>
						<td><strong>Nosso N�mero:</strong></td>
						<td align="right">
						<div align="left"><input name="nossoNumero" type="text" readonly
							style="background-color:#EFEFEF; border:0" size="18" maxlength="18"
							value="${requestScope.nossoNumero}"></div>
						</td>
					</tr>
					
					<logic:notEmpty name="arrecadador" scope="request">
						<tr>
							<td><strong>NSA do Movimento:</strong></td>
							<td align="right">
							<div align="left"><input name="nsaMovimento" type="text" readonly
								style="background-color:#EFEFEF; border:0" size="10" maxlength="10"
								value="${requestScope.arrecadador.numeroSequencialArquivo}"></div>
							</td>
						</tr>
					</logic:notEmpty>
					
					<logic:notEmpty name="pagamento" scope="request">	
						<tr>
							<td><strong>Valor do Movimento:</strong></td>
							<td align="right">
							<div align="left"><input name="valorMovimento" type="text" readonly
								style="background-color:#EFEFEF; border:0" size="12" maxlength="12"
								value="${requestScope.pagamento.arrecadadorMovimentoItem.arrecadadorMovimento.valorTotalMovimento}"></div>
							</td>
						</tr>
					</logic:notEmpty>
					
					<tr>
						<td><strong>Data Pagamento:</strong></td>
						<td align="right">
						<div align="left"><input name="dataPagamento" type="text" readonly
							style="background-color:#EFEFEF; border:0" size="10" maxlength="10"
							value="<bean:write name="pagamento" property="dataPagamento" formatKey="date.format" scope="request"/>"></div>
						</td>
					</tr>
					
					<logic:present name="indicadorAceitacao" scope="request">
						<tr>
							<td><strong>Pagamento Aceito:</strong></td>
							<td align="right">
								<logic:equal name="indicadorAceitacao" value="1" scope="request">
									<div align="left"><input name="pagamentoAceito" type="text" readonly
										style="background-color:#EFEFEF; border:0" size="3" maxlength="3"
										value="SIM"></div>
								</logic:equal>
								<logic:notEqual name="indicadorAceitacao" value="1">
									<div align="left"><input name="pagamentoAceito" type="text" readonly
										style="background-color:#EFEFEF; border:0" size="3" maxlength="3"
										value="N�O"></div>
								</logic:notEqual>
							</td>
						</tr>
					</logic:present>
				</logic:present> 
			</table>
		</logic:present>
		
		
		<!--============================== CLIENTES DA CONTA ===================================== -->
		<hr>
		
		<Strong>Clientes da Conta:</Strong> 
		<table width="100%" align="center" bgcolor="#90c7fc" border="0">
			<tr bordercolor="#000000">
				<td width="17%" bgcolor="#90c7fc" align="center">
				 <div align="center"><strong>Tipo da Rela&ccedil;&atilde;o</strong></div>
				</td>
				<td width="6%" bgcolor="#90c7fc" align="center">
					<div align="center"><strong>Nome Conta</strong></div>
				</td>
				<td width="14%" bgcolor="#90c7fc" align="center">
					<div align="center"><strong>C�digo</strong></div>
				</td>
				<td width="43%" bgcolor="#90c7fc" align="center">
					<div align="center"><strong>Nome do	Cliente</strong></div>
				</td>
				<td width="20%" bgcolor="#90c7fc" align="center">
					<div align="center"><strong>CPF/CNPJ</strong></div>
				</td>
			</tr>
			<tr>
				<td width="100%" colspan="5">
					<div style="width: 100%; height: 100%; overflow: auto;">
						<table width="100%" align="left" bgcolor="#99CCFF">
							<%int contCliente = 0;%>
							<logic:notEmpty name="contaClientes" scope="session">
								<logic:iterate name="contaClientes" id="contaCliente"
												type="ClienteConta">
									<%contCliente = contCliente + 1;
									if (contCliente % 2 == 0) {%>
									<tr bgcolor="#cbe5fe">
										<%} else {%>
									<tr bgcolor="#FFFFFF">
										<%}%>
										
										<td width="17%" align="left">
											<div align="left">
												<logic:present	name="contaCliente" property="clienteRelacaoTipo">
													<bean:write name="contaCliente"
															property="clienteRelacaoTipo.descricao" />
												</logic:present> 
											</div>
										</td>
										<td width="6%" align="center">
											<div align="center">
				                        	<logic:equal name="contaCliente"  value="1" property="indicadorNomeConta" >
				                        		<input type="radio" name="contaCliente" checked="checked" disabled="disabled" />
				                        	</logic:equal>
				                        	<logic:notEqual name="contaCliente" value="1" property="indicadorNomeConta" >
				                        		<input type="radio" name="contaCliente" disabled="disabled"  />
				                        	</logic:notEqual>
				                        	</div>
										</td>
										<td width="14%">
											<div align="center">
												<logic:present	name="contaCliente" property="cliente">
													<bean:write name="contaCliente" property="cliente.id" />
												</logic:present> 
											</div>
										</td>
										<td width="43%" align="left" >
											<div align="left">
												<logic:present	name="contaCliente" property="cliente">
													<bean:write name="contaCliente" property="cliente.nome" />
												</logic:present> 
											</div>
										</td>
										<td width="20%" align="right">
											<div align="right">
											<logic:notEmpty
												name="contaCliente" property="cliente.cpf">
												<bean:write name="contaCliente"
													property="cliente.cpfFormatado" />
											</logic:notEmpty> 
											<logic:notEmpty name="contaCliente"
												property="cliente.cnpj">
												<bean:write name="contaCliente"
													property="cliente.cnpjFormatado" />
											</logic:notEmpty>
											</div>									
										</td>								
									</tr>
								</logic:iterate>
							</logic:notEmpty>
						</table>
					</div>
				</td>
			</tr>
		</table>
		
		<!--============================== CLIENTES DA CONTA ANTERIOR ===================================== -->
		
		<Strong>Clientes Anteriores:</Strong> 
		<table width="100%" align="center" bgcolor="#90c7fc" border="0">
			<tr bordercolor="#000000">
				<td width="17%" bgcolor="#90c7fc" align="center">
				 <div align="center"><strong>Data Altera��o</strong></div>
				</td>
				<td width="17%" bgcolor="#90c7fc" align="center">
				 <div align="center"><strong>Tipo da Rela&ccedil;&atilde;o</strong></div>
				</td>
				<td width="6%" bgcolor="#90c7fc" align="center">
					<div align="center"><strong>Nome Conta</strong></div>
				</td>
				<td width="10%" bgcolor="#90c7fc" align="center">
					<div align="center"><strong>C�digo</strong></div>
				</td>
				<td width="30%" bgcolor="#90c7fc" align="center">
					<div align="center"><strong>Nome do	Cliente</strong></div>
				</td>
				<td width="20%" bgcolor="#90c7fc" align="center">
					<div align="center"><strong>CPF/CNPJ</strong></div>
				</td>
			</tr>
			<tr>
				<td width="100%" colspan="6">
					<div style="width: 100%; height: 100%; overflow: auto;">
						<table width="100%" align="left" bgcolor="#99CCFF">
							<%int contClienteAnterior = 0;%>
							<logic:notEmpty name="contaClientesAnterior" scope="session">
								<logic:iterate name="contaClientesAnterior" id="contaClienteAnterior"
												type="ClienteContaAnterior">
									<%contClienteAnterior = contClienteAnterior + 1;
									if (contClienteAnterior % 2 == 0) {%>
									<tr bgcolor="#cbe5fe">
										<%} else {%>
									<tr bgcolor="#FFFFFF">
										<%}%>
										<td width="17%">
											<div align="center">
												<logic:present name="contaClienteAnterior" property="dataVinculo">
														<%=""
															+ Util.formatarData(contaClienteAnterior.getDataVinculo())%>
												</logic:present> 
						
												<logic:notPresent name="contaClienteAnterior" property="dataVinculo"> 
												&nbsp;
											  	</logic:notPresent>
											  	
										  	</div>
										</td>
										<td width="17%" align="left">
											<div align="left">
												<logic:present	name="contaClienteAnterior" property="clienteRelacaoTipo">
													<bean:write name="contaClienteAnterior"
															property="clienteRelacaoTipo.descricao" />
												</logic:present> 
											</div>
										</td>
										<td width="6%" align="center">
											<div align="center">
				                        	<logic:equal name="contaClienteAnterior"  value="1" property="indicadorNomeConta" >
				                        		<input type="checkbox" name="contaClienteAnterior" checked="checked" disabled="disabled" />
				                        	</logic:equal>
				                        	<logic:notEqual name="contaClienteAnterior" value="1" property="indicadorNomeConta" >
				                        		<input type="checkbox" name="contaClienteAnterior" disabled="disabled"  />
				                        	</logic:notEqual>
				                        	</div>
										</td>
										<td width="10%">
											<div align="center">
												<logic:present	name="contaClienteAnterior" property="cliente">
													<bean:write name="contaClienteAnterior" property="cliente.id" />
												</logic:present> 
											</div>
										</td>
										<td width="30%" align="left" >
											<div align="left">
												<logic:present	name="contaClienteAnterior" property="cliente">
													<bean:write name="contaClienteAnterior" property="cliente.nome" />
												</logic:present> 
											</div>
										</td>
										<td width="20%" align="right">
											<div align="right">
											<logic:notEmpty
												name="contaClienteAnterior" property="cliente.cpf">
												<bean:write name="contaClienteAnterior"
													property="cliente.cpfFormatado" />
											</logic:notEmpty> 
											<logic:notEmpty name="contaClienteAnterior"
												property="cliente.cnpj">
												<bean:write name="contaClienteAnterior"
													property="cliente.cnpjFormatado" />
											</logic:notEmpty>
											</div>									
										</td>								
									</tr>
								</logic:iterate>
							</logic:notEmpty>
						</table>
					</div>
				</td>
			</tr>
		</table>
		
		
		<!--============================== DATAS ===================================== -->
		
		<hr>
		
		<strong> DATAS: </strong>
		
		<table width="100%" border="0" bgcolor="#90c7fc">
			<tr bordercolor="#FFFFFF" bgcolor="#90c7fc">
				<td width="15%">
					<div align="center"><strong>Vencimento</strong></div>
				</td>
				<td width="14%">
					<div align="center"><strong>Validade</strong></div>
				</td>
				<td width="18%">
					<div align="center"><strong>Inclus&atilde;o</strong></div>
				</td>
				<td width="16%">
					<div align="center"><strong>Retifica&ccedil;&atilde;o</strong></div>
				</td>
				<td width="19%">
					<div align="center"><strong>Cancelamento</strong></div>
				</td>
				<td height="20">
					<div align="center"><strong>Revis&atilde;o</strong></div>
				</td>
			</tr>

			<%--INICIO CONTA --%>
			<logic:present name="conta" scope="session">

				<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF">
					<td width="12%">
					<div align="center">
						<logic:present name="conta" property="dataVencimentoConta">
							<span style="color: #000000;"><%=""
									+ Util.formatarData(((Conta) session
											.getAttribute("conta"))
											.getDataVencimentoConta())%></span>
						</logic:present> 

						<logic:notPresent name="conta" property="dataVencimentoConta"> &nbsp;
					  	</logic:notPresent>
					  	
				  	</div>
					</td>

					<td width="12%">
					<div align="center">
						<logic:present name="conta" property="dataValidadeConta">
							<span style="color: #000000;"><%=""
									+ Util.formatarData(((Conta) session
											.getAttribute("conta"))
											.getDataValidadeConta())%></span>
						</logic:present> <logic:notPresent name="conta" property="dataValidadeConta">
						&nbsp;
				  		</logic:notPresent>
				  	</div>
					</td>

					<td width="12%">
					<div align="center">
						<logic:present name="conta" property="dataInclusao">
							<span style="color: #000000;"><%=""
									+ Util.formatarData(((Conta) session
											.getAttribute("conta"))
											.getDataInclusao())%></span>
						</logic:present> 
						<logic:notPresent name="conta" property="dataInclusao">
						&nbsp;
				  		</logic:notPresent>
				  	</div>
					</td>

					<td width="12%">
					<div align="center">
						<logic:present name="conta" property="dataRetificacao">
							<span style="color: #000000;"><%=""
									+ Util.formatarData(((Conta) session
											.getAttribute("conta"))
											.getDataRetificacao())%></span>
						</logic:present> 
						<logic:notPresent name="conta" property="dataRetificacao">
						&nbsp;
				  		</logic:notPresent>
				  	</div>
					</td>

					<td width="12%">
					<div align="center">
						<logic:present name="conta" property="dataCancelamento">
							<span style="color: #000000;"><%=""
									+ Util.formatarData(((Conta) session
											.getAttribute("conta"))
											.getDataCancelamento())%></span>
						</logic:present> 
						<logic:notPresent name="conta" property="dataCancelamento">
						&nbsp;
				  		</logic:notPresent>
				  	</div>
					</td>

					<td width="12%">
					<div align="center">
						<logic:present name="conta" property="dataRevisao">
						<span style="color: #000000;"><%=""
								+ Util.formatarData(((Conta) session
										.getAttribute("conta"))
										.getDataRevisao())%></span>
						</logic:present> 
						<logic:notPresent name="conta" property="dataRevisao">
						&nbsp;
				  		</logic:notPresent>
				  	</div>
					</td>
				</tr>
			</logic:present>
			<%--FIM CONTA --%>

			<%--INICIO CONTA HISTORICO--%>
			<logic:present name="contaHistorico" scope="session">

				<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF">
					<td width="12%">
					<div align="center">
						<logic:present name="contaHistorico" property="dataVencimentoConta">
						<span style="color: #000000;"><%=""
								+ Util.formatarData(((ContaHistorico) session
										.getAttribute("contaHistorico"))
										.getDataVencimentoConta())%></span>
						</logic:present> 
						<logic:notPresent name="contaHistorico" property="dataVencimentoConta">
						&nbsp;
				  		</logic:notPresent>
				  	</div>
					</td>

					<td width="12%">
					<div align="center">
						<logic:present name="contaHistorico" property="dataValidadeConta">
							<span style="color: #000000;"><%=""
									+ Util.formatarData(((ContaHistorico) session
											.getAttribute("contaHistorico"))
											.getDataValidadeConta())%></span>
						</logic:present> 
						<logic:notPresent name="contaHistorico" property="dataValidadeConta">
						&nbsp;
				  		</logic:notPresent>
				  	</div>
					</td>

					<td width="12%">
					<div align="center">
						<logic:present name="contaHistorico" property="dataInclusao">
						<span style="color: #000000;"><%=""
								+ Util.formatarData(((ContaHistorico) session
										.getAttribute("contaHistorico"))
										.getDataInclusao())%></span>
						</logic:present> 
						<logic:notPresent name="contaHistorico" property="dataInclusao">
						&nbsp;
				  		</logic:notPresent>
				  	</div>
					</td>

					<td width="12%">
					<div align="center">
						<logic:present name="contaHistorico" property="dataRetificacao">
							<span style="color: #000000;"><%=""
									+ Util.formatarData(((ContaHistorico) session
											.getAttribute("contaHistorico"))
											.getDataRetificacao())%></span>
						</logic:present> 
						<logic:notPresent name="contaHistorico" property="dataRetificacao">
						&nbsp;
					  	</logic:notPresent>
					</div>
					</td>

					<td width="12%">
					<div align="center">
						<logic:present name="contaHistorico" property="dataCancelamento">
						<span style="color: #000000;"><%=""
								+ Util.formatarData(((ContaHistorico) session
										.getAttribute("contaHistorico"))
										.getDataCancelamento())%></span>
						</logic:present> 
						<logic:notPresent name="contaHistorico" property="dataCancelamento">
						&nbsp;
				  		</logic:notPresent>
				  	</div>
					</td>

					<td width="12%">
					<div align="center">
						<logic:present name="contaHistorico" property="dataRevisao">
						<span style="color: #000000;"><%=""
								+ Util.formatarData(((ContaHistorico) session
										.getAttribute("contaHistorico"))
										.getDataRevisao())%></span>
						</logic:present> 
						<logic:notPresent name="contaHistorico" property="dataRevisao">
						&nbsp;
				  		</logic:notPresent>
				  	</div>
					</td>
				</tr>
			</logic:present>
			<%--FIM CONTA HISTORICO--%>

		</table>
		<!--============================ CATEGORIAS ================================== -->
		<hr>
		<p></p>
		<table width="100%" border="0" bgcolor="#90c7fc">
			<tr bordercolor="#FFFFFF" bgcolor="#90c7fc">
				<td width="30%">
				<div align="left"><strong>Categoria</strong></div>
				</td>
				<td width="70%">
				<div align="center"><strong>Quantidade de Economias </strong></div>
				</td>
			</tr>

			<logic:present name="colecaoContaCategoria">
				<%	int cont = 0;	%>
				<logic:iterate name="colecaoContaCategoria" id="contaCategoria">
				<%	cont = cont + 1;
					if (cont % 2 == 0) {	%>
					<tr bgcolor="#cbe5fe">
				<%	} else {	%>
					<tr bgcolor="#FFFFFF">
				<%	}	%>

						<td>
						<div align="left">
							<bean:write name="contaCategoria"
								property="comp_id.categoria.descricao" />
						</div>
						</td>

						<td>
						<div align="center">
							<bean:write name="contaCategoria"
								property="quantidadeEconomia" />
						</div>
						</td>
					</tr>
				</logic:iterate>
			</logic:present>

			<logic:present name="colecaoContaCategoriaHistorico">
				<%	int cont1 = 0;	%>
				<logic:iterate name="colecaoContaCategoriaHistorico" id="contaCategoriaHistorico">
				<%	cont1 = cont1 + 1;
					if (cont1 % 2 == 0) {	%>
						<tr bgcolor="#cbe5fe">
				<%	} else {	%>
						<tr bgcolor="#FFFFFF">
				<%	}	%>
						<td>
						<div align="left">
							<bean:write name="contaCategoriaHistorico"
								property="comp_id.categoria.descricao" />
						</div>
						</td>

						<td>
						<div align="center">
							<bean:write name="contaCategoriaHistorico"
								property="quantidadeEconomia" />
						</div>
						</td>

					</tr>
				</logic:iterate>
			</logic:present>
		</table>
		
		<!--============================ DIVIDA ATIVA ================================== -->
		<hr>
		<p></p>
		<logic:present name="dataInscricao">
			<table width="100%" border="0" bgcolor="#90c7fc">
				<tr bordercolor="#FFFFFF" bgcolor="#90c7fc">
					<td width="30%">
						<div align="left"><strong>Divida Ativa</strong></div>
					</td>
				</tr>
	
				<tr bgcolor="#FFFFFF">	
					<td>
						<div align="left">
							<bean:write name="dataInscricao" />
						</div>
					</td>
				</tr>
			</table>
		</logic:present>
		
		<!--============================ IMPOSTOS RETIDOS ============================ -->
		<hr>
		<p></p>
		<table width="100%" border="0" bgcolor="#90c7fc">
			<tr bordercolor="#FFFFFF" bgcolor="#90c7fc">
				<td>
					<div align="left" class="style9"><strong>Imposto</strong></div>
				</td>
				<td>
					<div align="center" class="style9"><strong>Valor do Imposto</strong></div>
				</td>
			</tr>
			
			<logic:present name="colecaoContaImpostosDeduzidos">
				<%	int cont2 = 0;	%>
				<logic:iterate name="colecaoContaImpostosDeduzidos" id="contaImpostosDeduzidos">

				<%	cont2 = cont2 + 1;
					if (cont2 % 2 == 0) {	%>
						<tr bgcolor="#cbe5fe">
				<%	} else {	%>
						<tr bgcolor="#FFFFFF">
				<%	}	%>
						<td>
							<div align="left">
								<bean:write name="contaImpostosDeduzidos"
									property="impostoTipo.descricaoImposto" />
							</div>
						</td>

						<td>
							<div align="right">
								<bean:write name="contaImpostosDeduzidos"
									property="valorImposto" 
									formatKey="money.format" />
							</div>
						</td>
					</tr>
				</logic:iterate>
			</logic:present>
		</table>

		<table width="100%">
			<tr>
				<td height="24">
					<logic:present name="caminhoRetornoTelaConsultaConta">
						<input type="button" 
							class="bottonRightCol" 
							value="Voltar"
							style="width: 70px;"
							onclick="javascript:history.back();" />
					</logic:present>
				</td>
				<td align="right" width="90%">
					<logic:present name="emitirSegundaVia" scope="request">
						<logic:present name="contaSemCodigoBarras" scope="request">
							<logic:present name="naoCobrarTaxa">
							<input type="button" 
								name="" 
								value="Emitir 2� Via de Conta"
								class="bottonRightCol"
								onclick="javascript:window.location.href='<html:rewrite page="/gerarRelatorio2ViaContaAction.do?contaSemCodigoBarras=1"/>'" />
							</logic:present>
							<logic:notPresent name="naoCobrarTaxa">
							<input type="button" 
								name="" 
								value="Emitir 2� Via de Conta"
								class="bottonRightCol"
								onclick="javascript:if (confirm('A impress�o da 2� Via de Conta ir� gerar taxa de cobranca. Confirma?')){window.location.href='<html:rewrite page="/gerarRelatorio2ViaContaAction.do?contaSemCodigoBarras=1"/>'}" />
							</logic:notPresent>
						</logic:present> 
						
						<logic:notPresent name="contaSemCodigoBarras" scope="request">
							<logic:present name="naoCobrarTaxa">
							<input type="button" 
								name="" 
								value="Emitir 2� Via de Conta"
								class="bottonRightCol"
								onclick="javascript:window.location.href='<html:rewrite page="/gerarRelatorio2ViaContaAction.do"/>'" />
							</logic:present>
							<logic:notPresent name="naoCobrarTaxa">
							<input type="button" 
								name="" 
								value="Emitir 2� Via de Conta"
								class="bottonRightCol"
								onclick="javascript:if (confirm('A impress�o da 2� Via de Conta ir� gerar taxa de cobranca. Confirma?')){window.location.href='<html:rewrite page="/gerarRelatorio2ViaContaAction.do"/>'}" />
							</logic:notPresent>
						</logic:notPresent>
					</logic:present> 
					
					<logic:notPresent name="emitirSegundaVia" scope="request">
						<input type="button" 
							name="" 
							value="Emitir 2� Via de Conta"
							class="bottonRightCol" 
							disabled="true"/>
					</logic:notPresent>
				</td>

				<td colspan="1" align="right">
					<input name="Button" 
						type="button" 
						class="bottonRightCol" 
						value="Fechar"
						onClick="javascript:fechar();">
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</body>
</html:html>