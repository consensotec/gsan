<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.Util"%>
<%@ page 
	import="gcom.faturamento.debito.DebitoACobrarGeral,gcom.faturamento.debito.DebitoACobrar,
	gcom.atendimentopublico.FiscalizarParametroCalculoDebito,gcom.cobranca.parcelamento.ParcelamentoItem,
	gcom.faturamento.debito.DebitoACobrarHistorico,gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao,
	gcom.faturamento.debito.DemonstrativoFormulaCalculoHelper"
	isELIgnored="false"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<SCRIPT LANGUAGE="JavaScript">
<!--

function fechar(){
	window.close();
}
//-->
</SCRIPT>
</head>

<body leftmargin="5" topmargin="5">

<table width="640" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="640" valign="top" class="centercoltext">
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
				<td class="parabg">Consultar D�bitos A Cobrar</td>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>
		<p>&nbsp;</p>
		<table width="100%" border="0">
			<tr>
				<td width="20%"><strong>C�digo do Im�vel:</strong></td>
				<td width="80%" colspan="2" align="left"><input
					name="imovelConsultar" type="text" size="12" maxlength="10"
					readonly="true" style="background-color:#EFEFEF; border:0"
					value="${requestScope.idImovelConsultado}"></td>
			</tr>
			<tr>
				<td colspan="3">
				<table width="100%" align="center" bgcolor="#99CCFF" border="0">
					<tr>
						<td align="center"><strong>Endere&ccedil;o </strong></td>
					</tr>
					<tr bgcolor="#FFFFFF">
						<td>
						<div align="center">${requestScope.enderecoFormatado} &nbsp;</div>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td colspan="3">
				<hr>
				</td>
			</tr>
			
			<!-- inicio Debito a Cobrar -->
			<logic:present name="colecaoDebitoACobrarConsultar" scope="session">
				<tr>
					<td colspan="3" height="300">
					<div style="width: 100%; height: 330; overflow: auto;">
					<table width="80%" border="0">
						<logic:iterate name="colecaoDebitoACobrarConsultar" id="debitoACobrar"
							type="DebitoACobrar">
							<tr>
								<td><strong>Tipo do D&eacute;bito:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="debitoACobrar"
									property="debitoTipo">
									<html:text name="debitoACobrar" property="debitoTipo.descricao"
										size="40" maxlength="40" readonly="true"
										style="background-color:#EFEFEF; border:0" />
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>Situa&ccedil;&atilde;o do D&eacute;bito a Cobrar:</strong>
								</td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="debitoACobrar"
									property="debitoCreditoSituacaoAtual">
									<html:text name="debitoACobrar"
										property="debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao"
										size="30" maxlength="30" readonly="true"
										style="background-color:#EFEFEF; border:0" />
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td width="183" height="25"><strong>Usu�rio:</strong></td>
								<td colspan="3">
									<logic:present name="debitoACobrar" property="usuario">
										<html:text name="debitoACobrar" property="usuario.nomeUsuario" size="30" readonly="true" style="background-color:#EFEFEF; border:0" />
									</logic:present>		
									<logic:notPresent name="debitoACobrar" property="usuario">
										<input type="text" name="usuario" size="30" readonly="true" style="background-color:#EFEFEF; border:0" />
									</logic:notPresent>
								</td>
							</tr>
							<tr>
								<td><strong>Data e Hora de Gera&ccedil;&atilde;o do
								D&eacute;bito:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="debitoACobrar"
									property="geracaoDebito">
									<input type="text" size="10" maxlength="10" readonly="true"
										style="background-color:#EFEFEF; border:0"
										value="<bean:write name="debitoACobrar"  property="geracaoDebito" formatKey="date.format" />">
											&nbsp; <input type="text" size="8" maxlength="8"
										readonly="true" style="background-color:#EFEFEF; border:0"
										value="<bean:write name="debitoACobrar"  property="geracaoDebito" formatKey="hour.format" />">
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>M&ecirc;s e Ano de Refer&ecirc;ncia do
								D&eacute;bito:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="debitoACobrar"
									property="anoMesReferenciaDebito">
									<input type="text" size="7" maxlength="7" readonly="true"
										style="background-color:#EFEFEF; border:0"
										value="<%="" + Util.formatarMesAnoReferencia(debitoACobrar.getAnoMesReferenciaDebito())%>">
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>M&ecirc;s e Ano da Cobran&ccedil;a do D&eacute;bito:</strong>
								</td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="debitoACobrar"
									property="anoMesCobrancaDebito">
									<input type="text" size="7" maxlength="7" readonly="true"
										style="background-color:#EFEFEF; border:0"
										value="<%="" + Util.formatarMesAnoReferencia(debitoACobrar.getAnoMesCobrancaDebito())%>">
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>N&uacute;mero de Presta&ccedil;&otilde;es Cobradas:</strong>
								</td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="debitoACobrar"
									property="numeroPrestacaoCobradas">
									<html:text name="debitoACobrar"
										property="numeroPrestacaoCobradas" size="7" maxlength="7"
										readonly="true"
										style="background-color:#EFEFEF; border:0; text-align: right;" />
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>N&uacute;mero Total de Presta&ccedil;&otilde;es:</strong>
								</td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="debitoACobrar"
									property="numeroPrestacaoDebitoMenosBonus">
									<html:text name="debitoACobrar"
										property="numeroPrestacaoDebitoMenosBonus" size="7" maxlength="7"
										readonly="true"
										style="background-color:#EFEFEF; border:0; text-align: right;" />
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>Valor Total do D&eacute;bito:</strong></td>
								<td colspan="2" align="left"><logic:notEmpty
									name="debitoACobrar" property="valorDebito">
									<input type="text" size="17" maxlength="17" readonly="true"
										style="background-color:#EFEFEF; border:0; text-align: right;"
										value="<%="" + Util.formatarMoedaReal(debitoACobrar.getValorDebito())%>">
								</logic:notEmpty></td>
							</tr>
							
							<tr>
								<td><strong>Valor da Parcela:</strong></td>
								<td colspan="2" align="left"><logic:notEmpty
									name="debitoACobrar" property="valorParcela">
									<input type="text" size="17" maxlength="17" readonly="true"
										style="background-color:#EFEFEF; border:0; text-align: right;"
										value="<%="" + Util.formatarMoedaReal(debitoACobrar.getValorParcela())%>">
								</logic:notEmpty></td>
							</tr>
							
							
							<tr>
								<td><strong>Valor Restante a Ser Cobrado:</strong></td>
								<td colspan="2" align="left"><logic:notEmpty
									name="debitoACobrar" property="valorTotalComBonus">
									<input type="text" size="17" maxlength="17" readonly="true"
										style="background-color:#EFEFEF; border:0; text-align: right;"
										value="<%="" + Util.formatarMoedaReal(debitoACobrar.getValorTotalComBonus())%>">
								</logic:notEmpty></td>
							</tr>
							<tr>
								<td><strong>Taxa de Juros do Financiamento:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="debitoACobrar"
									property="percentualTaxaJurosFinanciamento">
									<input type="text" size="17" maxlength="17" readonly="true"
										style="background-color:#EFEFEF; border:0; text-align: right;"
										value="<%="" + Util.formatarMoedaReal(debitoACobrar.getPercentualTaxaJurosFinanciamento())%>">
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>Registro de Atendimento:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="debitoACobrar"
									property="registroAtendimento">
									<html:text name="debitoACobrar"
										property="registroAtendimento.id" size="9" maxlength="9"
										readonly="true"
										style="background-color:#EFEFEF; border:0; text-align: right;" />
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>Ordem de Servi&ccedil;o:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="debitoACobrar"
									property="ordemServico">
									<html:text name="debitoACobrar" property="ordemServico.id"
										size="9" maxlength="9" readonly="true"
										style="background-color:#EFEFEF; border:0" />
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>Tipo do Financiamento:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="debitoACobrar"
									property="financiamentoTipo">
									<html:text name="debitoACobrar"
										property="financiamentoTipo.descricao" size="40"
										maxlength="40" readonly="true"
										style="background-color:#EFEFEF; border:0" />
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>Forma de Cobran&ccedil;a:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="debitoACobrar"
									property="cobrancaForma">
									<html:text name="debitoACobrar"
										property="cobrancaForma.descricao" size="40" maxlength="40"
										readonly="true" style="background-color:#EFEFEF; border:0" />
								</logic:notEmpty></div>
								</td>
							</tr>
							
							<tr>
								<td><strong>Matr�cula do Im�vel Origem:</strong></td>
								<td colspan="2">
									
									<logic:present name="debitoACobrar" property="debitoACobrarGeralOrigem">
										<html:text name="debitoACobrar" property="debitoACobrarGeralOrigem.debitoACobrar.imovel.id"
										size="12" maxlength="10" readonly="true"
										style="background-color:#EFEFEF; border:0" />
									</logic:present>
									
									<logic:notPresent name="debitoACobrar" property="debitoACobrarGeralOrigem">
										<input type="text" name="imovelOrigem" size="12" maxlength="10"
										readonly="true" style="background-color:#EFEFEF; border:0" />
									</logic:notPresent>
									
								</td>
							</tr>
					</table>
					<table width="100%" border="0">
						<tr>
							<td colspan="3">
							<hr>
							</td>
						</tr>
						</logic:iterate>
					</table>
					<!-- Par�metros de fiscaliza��o de d�bitos a cobrar -->
					<logic:present name="fiscalizarParametroCalculoDebito" scope="session">
						<table width="100%" border="0">
							<tr align="center">
								<td align="center"><strong>PAR&Acirc;METROS PARA CALCULAR O D&Eacute;BITO</strong></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
						</table>
						<table>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="categoria">
								<tr>
									<td><strong>Categoria Principal:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="categoria.id"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
												&nbsp;&nbsp;
												<html:text name="fiscalizarParametroCalculoDebito"
													property="categoria.descricao"
													size="20" maxlength="20" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="subcategoria">
								<tr>
									<td><strong>Subcategoria Principal:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="subcategoria.codigo"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
												&nbsp;&nbsp;
												<html:text name="fiscalizarParametroCalculoDebito"
													property="subcategoria.descricaoAbreviada"
													size="20" maxlength="20" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>	
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="anoMesApuracaoInicial">
								<tr>
									<td><strong>Ano/M&ecirc;s de apura&ccedil;&atilde;o inicial:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="anoMesApuracaoInicial"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="anoMesApuracaoFinal">
								<tr>
									<td><strong>Ano/M&ecirc;s de apura&ccedil;&atilde;o final:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="anoMesApuracaoFinal"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
								<logic:notEmpty name="fiscalizarParametroCalculoDebito"
									property="numeroMesesApurado">
								<tr>
									<td><strong>N&uacute;mero de meses apurado:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="numeroMesesApurado"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="consumoAgua">
								<tr>
									<td><strong>Consumo de &aacute;gua faturado:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="consumoAgua"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="consumoEsgoto">
								<tr>
									<td><strong>Volume de esgoto faturado:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="consumoEsgoto"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="valorAguar">
								<tr>
									<td><strong>Valor de &aacute;gua calculado:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="valorAguar"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="valorEsgoto">
								<tr>
									<td><strong>Valor de esgoto calculado:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="valorEsgoto"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="valorEsgoto">
								<tr>
									<td><strong>Percentual de esgoto:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="percentualEsgoto"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="consumoMinimo">
								<tr>
									<td><strong>Consumo m&iacute;nimo do im&oacute;vel:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="consumoMinimo"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="qtdEconomiaAutoInfracao">
								<tr>
									<td><strong>Quantidade de economias:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="qtdEconomiaAutoInfracao"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="valorTarifaAutoInfracao">
								<tr>
									<td><strong>Valor da tarifa utilizada <br> para auto de infra&ccedil;&atilde;o:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="valorTarifaAutoInfracao"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="fatorMultiplicador">
								<tr>
									<td><strong>Fator de muliplica&ccedil;&atilde;o:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="fatorMultiplicador"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="fatorReincidencia">
								<tr>
									<td><strong>Fator de reincid&ecirc;ncia:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="fatorReincidencia"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="codCalculoConsumo">
								<tr>
									<td><strong>C&oacute;digo do c&aacute;lculo de d&eacute;bito:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="codCalculoConsumo"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							
							<!-- RM1165 - Exibir demonstrativo da f�rmula de c�lculo -->
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="ligacaoAguaSituacao">
								<tr>
									<td><strong>Situa��o da liga��o de �gua:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="ligacaoAguaSituacao.descricao"
													size="20" maxlength="20" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="numeroMesesFatura">
								<tr>
									<td><strong>N�mero meses fatura:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="numeroMesesFatura"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="numeroMaximoMesesSancoes">
								<tr>
									<td><strong>N�mero m�ximo meses san��es:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="numeroMaximoMesesSancoes"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="dataCorte">
							<tr>
								<td><strong>Data do corte  liga��o de �gua:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="fiscalizarParametroCalculoDebito"
									property="dataCorte">
									<input type="text" size="10" maxlength="10" readonly="true"
										style="background-color:#EFEFEF; border:0"
										value="<bean:write name="fiscalizarParametroCalculoDebito"  property="dataCorte" formatKey="date.format" />">											
								</logic:notEmpty></div>
								</td>
							</tr>
							</logic:notEmpty>
							
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="dataSupressaoAgua">
							<tr>
								<td><strong>Data da supress�o liga��o �gua:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="fiscalizarParametroCalculoDebito"
									property="dataSupressaoAgua">
									<input type="text" size="10" maxlength="10" readonly="true"
										style="background-color:#EFEFEF; border:0"
										value="<bean:write name="fiscalizarParametroCalculoDebito"  property="dataSupressaoAgua" formatKey="date.format" />">
								</logic:notEmpty></div>
								</td>
							</tr>
							</logic:notEmpty>
							
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="dataGeracaoDebito">
							<tr>
								<td><strong>Data da gera��o do d�bito:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="fiscalizarParametroCalculoDebito"
									property="dataGeracaoDebito">
									<input type="text" size="10" maxlength="10" readonly="true"
										style="background-color:#EFEFEF; border:0"
										value="<bean:write name="fiscalizarParametroCalculoDebito"  property="dataGeracaoDebito" formatKey="date.format" />">										
								</logic:notEmpty></div>
								</td>
							</tr>
							</logic:notEmpty>					
							
						</table>
						<table width="100%" border="0">
							<tr>
								<td colspan="3">
								<hr>
								</td>
							</tr>
						</table>
					</logic:present>
						
						</table>
						<table width="100%" border="0">
							<tr>
								<td colspan="3">
								<hr>
								</td>
							</tr>
						</table>
						
					<!-- DEMONSTRATIVO DA FORMULA DE CALCULO -->
					<logic:present name="demonstrativoFormulaCalculoHelper">
						<table width="100%" border="0">
							<tr align="center">
								<td align="center"><strong>DEMONSTRATIVO DA F�RMULA DE C�LCULO</strong></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
						</table>
						
						<table>
							<logic:notEmpty name="demonstrativoFormulaCalculoHelper"
								property="formula" >
								<tr>
									<td colspan="2">
										<div align="left"> F�RMULA: 
										<bean:write name="demonstrativoFormulaCalculoHelper" filter="false" property="formula" />
										</div>										
									</td>
								</tr>
							</logic:notEmpty>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<logic:notEmpty name="demonstrativoFormulaCalculoHelper"
								property="somaAguaEsgoto">
								<tr>
									<td colspan="2" align="right">
										<div align="left">
												SOMA DO VALOR DE �GUA + VALOR DE ESGOTO:  
												<html:text name="fiscalizarParametroCalculoDebito"
													property="valorAguar"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
												&nbsp;+&nbsp;
												<html:text name="fiscalizarParametroCalculoDebito"
													property="valorEsgoto"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
												&nbsp;=&nbsp;
												<html:text name="demonstrativoFormulaCalculoHelper"
													property="somaAguaEsgoto"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="demonstrativoFormulaCalculoHelper"
								property="valorInfracao">
								<tr>
									<td colspan="2">
										<div align="left"> VALOR DA INFRA��O: 
										<bean:write name="demonstrativoFormulaCalculoHelper"  property="valorInfracao" />										
										</div>										
									</td>
								</tr>
							</logic:notEmpty>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<logic:notEmpty name="demonstrativoFormulaCalculoHelper"
								property="numeroMesesFatura">
								<tr>
									<td colspan="2">
										<div align="left"> N�MERO DE MESES FATURA: 
										<html:text name="demonstrativoFormulaCalculoHelper"
													property="numeroMesesFatura"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />									
										</div>										
									</td>
								</tr>
								<tr>
								<td>&nbsp;</td>
								</tr>
							</logic:notEmpty>
							
							<logic:notEmpty name="demonstrativoFormulaCalculoHelper"
								property="fatorReincidencia">
								<tr>
									<td colspan="2">
										<div align="left"> FATOR DE REINCID�NCIA: 
										<bean:write name="demonstrativoFormulaCalculoHelper"  property="fatorReincidencia" />										
										</div>										
									</td>
								</tr>
								
								<tr>
								<td>&nbsp;</td>
								</tr>
							</logic:notEmpty>
							
							<logic:notEmpty name="demonstrativoFormulaCalculoHelper"
								property="valorInfracaoCalculado">
								<tr>
									<td colspan="2" align="right">
										<div align="left">
												VALOR DA INFRA��O CALCULADO: 
												<html:text name="fiscalizarParametroCalculoDebito"
													property="valorTarifaAutoInfracao"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
												&nbsp;X&nbsp;
												<html:text name="fiscalizarParametroCalculoDebito"
													property="qtdEconomiaAutoInfracao"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
												&nbsp;=&nbsp;
												<html:text name="demonstrativoFormulaCalculoHelper"
													property="valorInfracaoCalculado"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
							</logic:notEmpty>
							
							<logic:notEmpty name="demonstrativoFormulaCalculoHelper"
								property="fatorReincidenciaCadastradoInfracao">
								<tr>
									<td colspan="2" align="right">
										<div align="left">
												FATOR DE REINCID�NCIA CADASTRADO PARA INFRA��O:&nbsp;
												<html:text name="demonstrativoFormulaCalculoHelper"
													property="fatorReincidenciaCadastradoInfracao"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
								<tr>
								<td>&nbsp;</td>
								</tr>
							</logic:notEmpty>							
							
							<logic:notEmpty name="demonstrativoFormulaCalculoHelper"
								property="valorDebitoCalculado">
								<tr>
									<td colspan="2" align="right">
										<div align="left">
												VALOR DO D�BITO CALCULADO:
												<logic:notEmpty name="demonstrativoFormulaCalculoHelper"
												property="valorInfracaoCalculado">
												<html:text name="demonstrativoFormulaCalculoHelper"
													property="valorInfracaoCalculado"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
												</logic:notEmpty>
												<logic:notEmpty name="demonstrativoFormulaCalculoHelper"
												property="somaAguaEsgoto">
												<html:text name="demonstrativoFormulaCalculoHelper"
													property="somaAguaEsgoto"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
												</logic:notEmpty>
												&nbsp;X&nbsp;
												<logic:notEmpty name="demonstrativoFormulaCalculoHelper"
												property="fatorReincidenciaCadastradoInfracao">
												<html:text name="demonstrativoFormulaCalculoHelper"
													property="fatorReincidenciaCadastradoInfracao"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
												</logic:notEmpty>
												<logic:notEmpty name="demonstrativoFormulaCalculoHelper"
												property="numeroMesesFatura">
												<html:text name="demonstrativoFormulaCalculoHelper"
													property="numeroMesesFatura"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
												</logic:notEmpty>
												&nbsp;=&nbsp;
												<html:text name="demonstrativoFormulaCalculoHelper"
													property="valorDebitoCalculado"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
						</table>
						<table width="100%" border="0">
							<tr>
								<td colspan="3">
								<hr>
								</td>
							</tr>
						</table>
					</logic:present>
					<!-- Fim RM1165 -->
					
					<!-- Final par�metros de fiscaliza��o de d�bitos a cobrar -->
					</div>
					</td>
				</tr>
			</logic:present>
			<!-- final de Debito a Cobrar -->
			
			<!-- inicio Debito a Cobrar Historico -->
			<logic:present name="colecaoDebitoACobrarHistoricoConsultar" scope="session">
				<tr>
					<td colspan="3" height="300">
					<div style="width: 100%; height: 330; overflow: auto;">
					<table width="80%" border="0">
						<logic:iterate name="colecaoDebitoACobrarHistoricoConsultar" id="debitoACobrarHistorico"	type="DebitoACobrarHistorico">
							<tr>
								<td><strong>Tipo do D&eacute;bito:</strong></td>
								<td colspan="2" align="right">
									<div align="left">
									<logic:notEmpty name="debitoACobrarHistorico" property="debitoTipo">
										<html:text name="debitoACobrarHistorico" property="debitoTipo.descricao" size="40" 
										maxlength="40" readonly="true" style="background-color:#EFEFEF; border:0" />
									</logic:notEmpty>
									</div>
								</td>
							</tr>
							<tr>
								<td><strong>Situa&ccedil;&atilde;o do D&eacute;bito a Cobrar:</strong></td>
								<td colspan="2" align="right">
									<div align="left">
									<logic:notEmpty name="debitoACobrarHistorico" property="debitoCreditoSituacaoAtual">
										<html:text name="debitoACobrarHistorico"
											property="debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao"
											size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0" />
									</logic:notEmpty>
									</div>
								</td>
							</tr>
							<tr>
								<td width="183" height="25"><strong>Usu�rio:</strong></td>
								<td colspan="3">
									<logic:present name="debitoACobrarHistorico" property="usuario">
										<html:text name="debitoACobrarHistorico" property="usuario.nomeUsuario" size="30" readonly="true" style="background-color:#EFEFEF; border:0" />
									</logic:present>		
									<logic:notPresent name="debitoACobrarHistorico" property="usuario">
										<input type="text" name="usuario" size="30" readonly="true" style="background-color:#EFEFEF; border:0" />
									</logic:notPresent>
								</td>
							</tr>
							<tr>
								<td><strong>Data e Hora de Gera&ccedil;&atilde;o do	D&eacute;bito:</strong></td>
								<td colspan="2" align="right">
									<div align="left">
										<logic:notEmpty name="debitoACobrarHistorico" property="debitoGeradoRealizar">
											<input type="text" size="10" maxlength="10" readonly="true" 
											style="background-color:#EFEFEF; border:0"
												value="<bean:write name="debitoACobrarHistorico"  property="debitoGeradoRealizar" formatKey="date.format" />">
													&nbsp; <input type="text" size="8" maxlength="8"
												readonly="true" style="background-color:#EFEFEF; border:0"
												value="<bean:write name="debitoACobrarHistorico"  property="debitoGeradoRealizar" formatKey="hour.format" />">
										</logic:notEmpty>
									</div>
								</td>
							</tr>
							<tr>
								<td><strong>M&ecirc;s e Ano de Refer&ecirc;ncia do
								D&eacute;bito:</strong></td>
								<td colspan="2" align="right">
									<div align="left">
									<logic:notEmpty name="debitoACobrarHistorico" property="anoMesReferenciaDebito">
										<input type="text" size="7" maxlength="7" readonly="true"
											style="background-color:#EFEFEF; border:0"
											value="<%="" + Util.formatarMesAnoReferencia(debitoACobrarHistorico.getAnoMesReferenciaDebito())%>">
									</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>M&ecirc;s e Ano da Cobran&ccedil;a do D&eacute;bito:</strong>
								</td>
								<td colspan="2" align="right">
								<div align="left">
									<logic:notEmpty name="debitoACobrarHistorico" property="anoMesCobrancaDebito">
										<input type="text" size="7" maxlength="7" readonly="true"
											style="background-color:#EFEFEF; border:0"
											value="<%="" + Util.formatarMesAnoReferencia(debitoACobrarHistorico.getAnoMesCobrancaDebito())%>">
									</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>N&uacute;mero de Presta&ccedil;&otilde;es Cobradas:</strong>
								</td>
								<td colspan="2" align="right">
								<div align="left">
									<logic:notEmpty name="debitoACobrarHistorico" property="prestacaoCobradas">
										<html:text name="debitoACobrarHistorico" property="prestacaoCobradas" size="7" maxlength="7"
											readonly="true"
											style="background-color:#EFEFEF; border:0; text-align: right;" />
									</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>N&uacute;mero Total de Presta&ccedil;&otilde;es:</strong>
								</td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="debitoACobrarHistorico"
									property="numeroPrestacaoDebitoMenosBonus">
									<html:text name="debitoACobrarHistorico"
										property="numeroPrestacaoDebitoMenosBonus" size="7" maxlength="7"
										readonly="true"
										style="background-color:#EFEFEF; border:0; text-align: right;" />
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>Valor Total do D&eacute;bito:</strong></td>
								<td colspan="2" align="left"><logic:notEmpty
									name="debitoACobrarHistorico" property="valorDebito">
									<input type="text" size="17" maxlength="17" readonly="true"
										style="background-color:#EFEFEF; border:0; text-align: right;"
										value="<%="" + Util.formatarMoedaReal(debitoACobrarHistorico.getValorDebito())%>">
								</logic:notEmpty></td>
							</tr>
							
							<tr>
								<td><strong>Valor da Parcela:</strong></td>
								<td colspan="2" align="left"><logic:notEmpty
									name="debitoACobrarHistorico" property="valorPrestacao">
									<input type="text" size="17" maxlength="17" readonly="true"
										style="background-color:#EFEFEF; border:0; text-align: right;"
										value="<%="" + Util.formatarMoedaReal(debitoACobrarHistorico.getValorPrestacao())%>">
								</logic:notEmpty></td>
							</tr>
							
							
							<tr>
								<td><strong>Valor Restante a Ser Cobrado:</strong></td>
								<td colspan="2" align="left"><logic:notEmpty
									name="debitoACobrarHistorico" property="valorTotalComBonus">
									<input type="text" size="17" maxlength="17" readonly="true"
										style="background-color:#EFEFEF; border:0; text-align: right;"
										value="<%="" + Util.formatarMoedaReal(debitoACobrarHistorico.getValorTotalComBonus())%>">
								</logic:notEmpty></td>
							</tr>
							<tr>
								<td><strong>Taxa de Juros do Financiamento:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="debitoACobrarHistorico"
									property="percentualTaxaJurosFinanciamento">
									<input type="text" size="17" maxlength="17" readonly="true"
										style="background-color:#EFEFEF; border:0; text-align: right;"
										value="<%="" + Util.formatarMoedaReal(debitoACobrarHistorico.getPercentualTaxaJurosFinanciamento())%>">
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>Registro de Atendimento:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="debitoACobrarHistorico"
									property="registroAtendimento">
									<html:text name="debitoACobrarHistorico"
										property="registroAtendimento.id" size="9" maxlength="9"
										readonly="true"
										style="background-color:#EFEFEF; border:0; text-align: right;" />
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>Ordem de Servi&ccedil;o:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="debitoACobrarHistorico"
									property="ordemServico">
									<html:text name="debitoACobrarHistorico" property="ordemServico.id"
										size="9" maxlength="9" readonly="true"
										style="background-color:#EFEFEF; border:0" />
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>Tipo do Financiamento:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="debitoACobrarHistorico"
									property="financiamentoTipo">
									<html:text name="debitoACobrarHistorico"
										property="financiamentoTipo.descricao" size="40"
										maxlength="40" readonly="true"
										style="background-color:#EFEFEF; border:0" />
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>Forma de Cobran&ccedil;a:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="debitoACobrarHistorico"
									property="cobrancaForma">
									<html:text name="debitoACobrarHistorico"
										property="cobrancaForma.descricao" size="40" maxlength="40"
										readonly="true" style="background-color:#EFEFEF; border:0" />
								</logic:notEmpty></div>
								</td>
							</tr>
							
							<tr>
								<td><strong>Matr�cula do Im�vel Origem:</strong></td>
								<td colspan="2">
									
									<logic:present name="debitoACobrarHistorico" property="debitoACobrarGeralOrigem">
										<html:text name="debitoACobrarHistorico" property="origem.debitoACobrarHistorico.imovel.id"
										size="12" maxlength="10" readonly="true"
										style="background-color:#EFEFEF; border:0" />
									</logic:present>
									
									<logic:notPresent name="debitoACobrarHistorico" property="debitoACobrarGeralOrigem">
										<input type="text" name="imovelOrigem" size="12" maxlength="10"
										readonly="true" style="background-color:#EFEFEF; border:0" />
									</logic:notPresent>
									
								</td>
							</tr>
					</table>
					<table width="100%" border="0">
						<tr>
							<td colspan="3">
							<hr>
							</td>
						</tr>
						</logic:iterate>
					</table>
					<!-- Par�metros de fiscaliza��o de d�bitos a cobrar -->
					<logic:present name="fiscalizarParametroCalculoDebito" scope="session">
						<table width="100%" border="0">
							<tr align="center">
								<td align="center"><strong>PAR&Acirc;METROS PARA CALCULAR O D&Eacute;BITO</strong></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
						</table>
						<table>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="categoria">
								<tr>
									<td><strong>Categoria Principal:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="categoria.id"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
												<html:text name="fiscalizarParametroCalculoDebito"
													property="categoria.descricao"
													size="20" maxlength="20" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="subcategoria">
								<tr>
									<td><strong>Subcategoria Principal:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="subcategoria.codigo"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
												<html:text name="fiscalizarParametroCalculoDebito"
													property="subcategoria.descricaoAbreviada"
													size="20" maxlength="20" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>	
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="anoMesApuracaoInicial">
								<tr>
									<td><strong>Ano/M&ecirc;s de apura&ccedil;&atilde;o inicial:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="anoMesApuracaoInicial"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="anoMesApuracaoFinal">
								<tr>
									<td><strong>Ano/M&ecirc;s de apura&ccedil;&atilde;o final:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="anoMesApuracaoFinal"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
								<logic:notEmpty name="fiscalizarParametroCalculoDebito"
									property="numeroMesesApurado">
								<tr>
									<td><strong>N&uacute;mero de meses apurado:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="numeroMesesApurado"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="consumoAgua">
								<tr>
									<td><strong>Consumo de &aacute;gua faturado:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="consumoAgua"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="consumoEsgoto">
								<tr>
									<td><strong>Volume de esgoto faturado:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="consumoEsgoto"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="valorAguar">
								<tr>
									<td><strong>Valor de &aacute;gua calculado:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="valorAguar"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="valorEsgoto">
								<tr>
									<td><strong>Valor de esgoto calculado:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="valorEsgoto"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="valorEsgoto">
								<tr>
									<td><strong>Percentual de esgoto:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="percentualEsgoto"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="consumoMinimo">
								<tr>
									<td><strong>Consumo m&iacute;nimo do im&oacute;vel:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="consumoMinimo"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="qtdEconomiaAutoInfracao">
								<tr>
									<td><strong>Quantidade de economias:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="qtdEconomiaAutoInfracao"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="valorTarifaAutoInfracao">
								<tr>
									<td><strong>Valor da tarifa utilizada <br> para auto de infra&ccedil;&atilde;o:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="valorTarifaAutoInfracao"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="fatorMultiplicador">
								<tr>
									<td><strong>Fator de muliplica&ccedil;&atilde;o:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="fatorMultiplicador"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="fatorReincidencia">
								<tr>
									<td><strong>Fator de reincid&ecirc;ncia:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="fatorReincidencia"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="codCalculoConsumo">
								<tr>
									<td><strong>C&oacute;digo do c&aacute;lculo de d&eacute;bito:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="codCalculoConsumo"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
						</table>
						<table width="100%" border="0">
							<tr>
								<td colspan="3">
								<hr>
								</td>
							</tr>
						</table>
						
						<!-- DEMONSTRATIVO DA FORMULA DE CALCULO -->
					<logic:present name="demonstrativoFormulaCalculoHelper">
						<table width="100%" border="0">
							<tr align="center">
								<td align="center"><strong>DEMONSTRATIVO DA F�RMULA DE C�LCULO</strong></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
						</table>
						
						<table>
							<logic:notEmpty name="demonstrativoFormulaCalculoHelper"
								property="formula" >
								<tr>
									<td colspan="2">
										<div align="left"> F�RMULA: 
										<bean:write name="demonstrativoFormulaCalculoHelper" filter="false" property="formula" />
										</div>										
									</td>
								</tr>
							</logic:notEmpty>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<logic:notEmpty name="demonstrativoFormulaCalculoHelper"
								property="somaAguaEsgoto">
								<tr>
									<td colspan="2" align="right">
										<div align="left">
												SOMA DO VALOR DE �GUA + VALOR DE ESGOTO:  
												<html:text name="fiscalizarParametroCalculoDebito"
													property="valorAguar"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
												&nbsp;+&nbsp;
												<html:text name="fiscalizarParametroCalculoDebito"
													property="valorEsgoto"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
												&nbsp;=&nbsp;
												<html:text name="demonstrativoFormulaCalculoHelper"
													property="somaAguaEsgoto"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="demonstrativoFormulaCalculoHelper"
								property="valorInfracao">
								<tr>
									<td colspan="2">
										<div align="left"> VALOR DA INFRA��O: 
										<bean:write name="demonstrativoFormulaCalculoHelper"  property="valorInfracao" />										
										</div>										
									</td>
								</tr>
							</logic:notEmpty>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<logic:notEmpty name="demonstrativoFormulaCalculoHelper"
								property="numeroMesesFatura">
								<tr>
									<td colspan="2">
										<div align="left"> N�MERO DE MESES FATURA: 
										<html:text name="demonstrativoFormulaCalculoHelper"
													property="numeroMesesFatura"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />									
										</div>										
									</td>
								</tr>
								<tr>
								<td>&nbsp;</td>
								</tr>
							</logic:notEmpty>
							
							<logic:notEmpty name="demonstrativoFormulaCalculoHelper"
								property="fatorReincidencia">
								<tr>
									<td colspan="2">
										<div align="left"> FATOR DE REINCID�NCIA: 
										<bean:write name="demonstrativoFormulaCalculoHelper"  property="fatorReincidencia" />										
										</div>										
									</td>
								</tr>
								
								<tr>
								<td>&nbsp;</td>
								</tr>
							</logic:notEmpty>
							
							<logic:notEmpty name="demonstrativoFormulaCalculoHelper"
								property="valorInfracaoCalculado">
								<tr>
									<td colspan="2" align="right">
										<div align="left">
												VALOR DA INFRA��O CALCULADO: 
												<html:text name="fiscalizarParametroCalculoDebito"
													property="valorTarifaAutoInfracao"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
												&nbsp;X&nbsp;
												<html:text name="fiscalizarParametroCalculoDebito"
													property="qtdEconomiaAutoInfracao"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
												&nbsp;=&nbsp;
												<html:text name="demonstrativoFormulaCalculoHelper"
													property="valorInfracaoCalculado"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
							</logic:notEmpty>
							
							<logic:notEmpty name="demonstrativoFormulaCalculoHelper"
								property="fatorReincidenciaCadastradoInfracao">
								<tr>
									<td colspan="2" align="right">
										<div align="left">
												FATOR DE REINCID�NCIA CADASTRADO PARA INFRA��O:&nbsp;
												<html:text name="demonstrativoFormulaCalculoHelper"
													property="fatorReincidenciaCadastradoInfracao"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
								<tr>
								<td>&nbsp;</td>
								</tr>
							</logic:notEmpty>							
							
							<logic:notEmpty name="demonstrativoFormulaCalculoHelper"
								property="valorDebitoCalculado">
								<tr>
									<td colspan="2" align="right">
										<div align="left">
												VALOR DO D�BITO CALCULADO:
												<logic:notEmpty name="demonstrativoFormulaCalculoHelper"
												property="valorInfracaoCalculado">
												<html:text name="demonstrativoFormulaCalculoHelper"
													property="valorInfracaoCalculado"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
												</logic:notEmpty>
												<logic:notEmpty name="demonstrativoFormulaCalculoHelper"
												property="somaAguaEsgoto">
												<html:text name="demonstrativoFormulaCalculoHelper"
													property="somaAguaEsgoto"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
												</logic:notEmpty>
												&nbsp;X&nbsp;
												<logic:notEmpty name="demonstrativoFormulaCalculoHelper"
												property="fatorReincidenciaCadastradoInfracao">
												<html:text name="demonstrativoFormulaCalculoHelper"
													property="fatorReincidenciaCadastradoInfracao"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
												</logic:notEmpty>
												<logic:notEmpty name="demonstrativoFormulaCalculoHelper"
												property="numeroMesesFatura">
												<html:text name="demonstrativoFormulaCalculoHelper"
													property="numeroMesesFatura"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
												</logic:notEmpty>
												&nbsp;=&nbsp;
												<html:text name="demonstrativoFormulaCalculoHelper"
													property="valorDebitoCalculado"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
						</table>
						<table width="100%" border="0">
							<tr>
								<td colspan="3">
								<hr>
								</td>
							</tr>
						</table>
					</logic:present>
					<!-- Fim RM1165 -->
					
					</logic:present>
					<!-- Final par�metros de fiscaliza��o de d�bitos a cobrar -->
					</div>
					</td>
				</tr>
			</logic:present>
			<!-- final Debito a Cobrar Historico-->
			
			<logic:present name="colecaoParcelamentoItem">
				<tr>
					<td colspan="3" height="300">
					<div style="width: 100%; height: 330; overflow: auto;">
					<table width="80%" border="0">
						<logic:iterate name="colecaoParcelamentoItem"
							id="parcelamentoItem" type="ParcelamentoItem">
							
							<logic:notEmpty name="parcelamentoItem"
									property="debitoACobrarGeral.debitoACobrar">
							<tr>
								<td><strong>Tipo do D&eacute;bito:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="parcelamentoItem"
									property="debitoACobrarGeral.debitoACobrar.debitoTipo">
									<html:text name="parcelamentoItem"
										property="debitoACobrarGeral.debitoACobrar.debitoTipo.descricao"
										size="40" maxlength="40" readonly="true"
										style="background-color:#EFEFEF; border:0" />
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>Situa&ccedil;&atilde;o do D&eacute;bito a Cobrar:</strong>
								</td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="parcelamentoItem"
									property="debitoACobrarGeral.debitoACobrar.debitoCreditoSituacaoAtual">
									<html:text name="parcelamentoItem"
										property="debitoACobrarGeral.debitoACobrar.debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao"
										size="30" maxlength="30" readonly="true"
										style="background-color:#EFEFEF; border:0" />
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td width="183" height="25"><strong>Usu�rio:</strong></td>
								<td colspan="3">
									<logic:present name="parcelamentoItem" property="debitoACobrarGeral.debitoACobrar.usuario">
										<html:text name="parcelamentoItem" property="debitoACobrarGeral.debitoACobrar.usuario.nomeUsuario" 
												   size="30" readonly="true" style="background-color:#EFEFEF; border:0" />
									</logic:present>		
									<logic:notPresent name="parcelamentoItem" property="debitoACobrarGeral.debitoACobrar.usuario">
										<input type="text" name="usuario" size="30" readonly="true" style="background-color:#EFEFEF; border:0" />
									</logic:notPresent>
								</td>
							</tr>
							<tr>
								<td><strong>Data e Hora de Gera&ccedil;&atilde;o do
								D&eacute;bito:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="parcelamentoItem"
									property="debitoACobrarGeral.debitoACobrar.geracaoDebito">
									<input type="text" size="10" maxlength="10" readonly="true"
										style="background-color:#EFEFEF; border:0"
										value="<bean:write name="parcelamentoItem"  property="debitoACobrarGeral.debitoACobrar.geracaoDebito" formatKey="date.format" />">
											&nbsp; <input type="text" size="8" maxlength="8"
										readonly="true" style="background-color:#EFEFEF; border:0"
										value="<bean:write name="parcelamentoItem"  property="debitoACobrarGeral.debitoACobrar.geracaoDebito" formatKey="hour.format" />">
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>M&ecirc;s e Ano de Refer&ecirc;ncia do
								D&eacute;bito:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="parcelamentoItem"
									property="debitoACobrarGeral.debitoACobrar.anoMesReferenciaDebito">
									<input type="text" size="7" maxlength="7" readonly="true"
										style="background-color:#EFEFEF; border:0"
										value="<%="" + Util.formatarMesAnoReferencia(parcelamentoItem.getDebitoACobrarGeral().getDebitoACobrar().getAnoMesReferenciaDebito())%>">
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>M&ecirc;s e Ano da Cobran&ccedil;a do D&eacute;bito:</strong>
								</td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="parcelamentoItem"
									property="debitoACobrarGeral.debitoACobrar.anoMesCobrancaDebito">
									<input type="text" size="7" maxlength="7" readonly="true"
										style="background-color:#EFEFEF; border:0"
										value="<%="" + Util.formatarMesAnoReferencia(parcelamentoItem.getDebitoACobrarGeral().getDebitoACobrar().getAnoMesCobrancaDebito())%>">
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>N&uacute;mero de Presta&ccedil;&otilde;es Cobradas:</strong>
								</td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="parcelamentoItem"
									property="debitoACobrarGeral.debitoACobrar.numeroPrestacaoCobradas">
									<html:text name="parcelamentoItem"
										property="debitoACobrarGeral.debitoACobrar.numeroPrestacaoCobradas"
										size="7" maxlength="7" readonly="true"
										style="background-color:#EFEFEF; border:0; text-align: right;" />
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>N&uacute;mero Total de Presta&ccedil;&otilde;es:</strong>
								</td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="parcelamentoItem"
									property="debitoACobrarGeral.debitoACobrar.numeroPrestacaoDebitoMenosBonus">
									<html:text name="parcelamentoItem"
										property="debitoACobrarGeral.debitoACobrar.numeroPrestacaoDebitoMenosBonus"
										size="7" maxlength="7" readonly="true"
										style="background-color:#EFEFEF; border:0; text-align: right;" />
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>Valor Total do D&eacute;bito:</strong></td>
								<td colspan="2" align="left"><logic:notEmpty
									name="parcelamentoItem"
									property="debitoACobrarGeral.debitoACobrar.valorDebito">
									<input type="text" size="17" maxlength="17" readonly="true"
										style="background-color:#EFEFEF; border:0; text-align: right;"
										value="<%="" + Util.formatarMoedaReal(parcelamentoItem.getDebitoACobrarGeral().getDebitoACobrar().getValorDebito())%>">
								</logic:notEmpty></td>
							</tr>
							<tr>
								<td><strong>Valor Restante a Ser Cobrado:</strong></td>
								<td colspan="2" align="left"><logic:notEmpty
									name="parcelamentoItem"
									property="debitoACobrarGeral.debitoACobrar.valorTotalComBonus">
									<input type="text" size="17" maxlength="17" readonly="true"
										style="background-color:#EFEFEF; border:0; text-align: right;"
										value="<%="" + Util.formatarMoedaReal(parcelamentoItem.getDebitoACobrarGeral().getDebitoACobrar().getValorTotalComBonus())%>">
								</logic:notEmpty></td>
							</tr>
							<tr>
								<td><strong>Taxa de Juros do Financiamento:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="parcelamentoItem"
									property="debitoACobrarGeral.debitoACobrar.percentualTaxaJurosFinanciamento">
									<input type="text" size="17" maxlength="17" readonly="true"
										style="background-color:#EFEFEF; border:0; text-align: right;"
										value="<%="" + Util.formatarMoedaReal(parcelamentoItem.getDebitoACobrarGeral().getDebitoACobrar().getPercentualTaxaJurosFinanciamento())%>">
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>Registro de Atendimento:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="parcelamentoItem"
									property="debitoACobrarGeral.debitoACobrar.registroAtendimento">
									<html:text name="parcelamentoItem"
										property="debitoACobrarGeral.debitoACobrar.registroAtendimento.id"
										size="9" maxlength="9" readonly="true"
										style="background-color:#EFEFEF; border:0; text-align: right;" />
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>Ordem de Servi&ccedil;o:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="parcelamentoItem"
									property="debitoACobrarGeral.debitoACobrar.ordemServico">
									<html:text name="parcelamentoItem"
										property="debitoACobrarGeral.debitoACobrar.ordemServico.id"
										size="9" maxlength="9" readonly="true"
										style="background-color:#EFEFEF; border:0" />
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>Tipo do Financiamento:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="parcelamentoItem"
									property="debitoACobrarGeral.debitoACobrar.financiamentoTipo">
									<html:text name="parcelamentoItem"
										property="debitoACobrarGeral.debitoACobrar.financiamentoTipo.descricao"
										size="40" maxlength="40" readonly="true"
										style="background-color:#EFEFEF; border:0" />
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>Forma de Cobran&ccedil;a:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="parcelamentoItem"
									property="debitoACobrarGeral.debitoACobrar.cobrancaForma">
									<html:text name="parcelamentoItem"
										property="debitoACobrarGeral.debitoACobrar.cobrancaForma.descricao"
										size="40" maxlength="40" readonly="true"
										style="background-color:#EFEFEF; border:0" />
								</logic:notEmpty></div>
								</td>
							</tr>
							
							<tr>
								<td><strong>Matr�cula do Im�vel Origem:</strong></td>
								<td colspan="2">
									
									<logic:present name="parcelamentoItem" property="debitoACobrarGeral.debitoACobrar.debitoACobrarGeralOrigem">
										<html:text name="parcelamentoItem" property="debitoACobrarGeral.debitoACobrar.debitoACobrarGeralOrigem.imovel.id"
										size="12" maxlength="10" readonly="true"
										style="background-color:#EFEFEF; border:0" />
									</logic:present>
									
									<logic:notPresent name="parcelamentoItem" property="debitoACobrarGeral">
										<input type="text" name="imovelOrigem" size="12" maxlength="10"
										readonly="true" style="background-color:#EFEFEF; border:0" />
									</logic:notPresent>
									
								</td>
							</tr>
					</table>
					<table width="100%" border="0">
						<tr>
							<td colspan="3">
							<hr>
							</td>
						</tr>
						</logic:notEmpty>
						
						
						
						<logic:notEmpty name="parcelamentoItem"
									property="debitoACobrarGeral.debitoACobrarHistorico">
							<tr>
								<td><strong>Tipo do D&eacute;bito:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="parcelamentoItem"
									property="debitoACobrarGeral.debitoACobrarHistorico.debitoTipo">
									<html:text name="parcelamentoItem"
										property="debitoACobrarGeral.debitoACobrarHistorico.debitoTipo.descricao"
										size="40" maxlength="40" readonly="true"
										style="background-color:#EFEFEF; border:0" />
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>Situa&ccedil;&atilde;o do D&eacute;bito a Cobrar:</strong>
								</td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="parcelamentoItem"
									property="debitoACobrarGeral.debitoACobrarHistorico.debitoCreditoSituacaoAtual">
									<html:text name="parcelamentoItem"
										property="debitoACobrarGeral.debitoACobrarHistorico.debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao"
										size="30" maxlength="30" readonly="true"
										style="background-color:#EFEFEF; border:0" />
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td width="183" height="25"><strong>Usu�rio:</strong></td>
								<td colspan="3">
									<logic:present name="parcelamentoItem" property="debitoACobrarGeral.debitoACobrarHistorico.usuario">
										<html:text name="parcelamentoItem" property="debitoACobrarGeral.debitoACobrarHistorico.usuario.nomeUsuario" 
												   size="30" readonly="true" style="background-color:#EFEFEF; border:0" />
									</logic:present>		
									<logic:notPresent name="parcelamentoItem" property="debitoACobrarGeral.debitoACobrarHistorico.usuario">
										<input type="text" name="usuario" size="30" readonly="true" style="background-color:#EFEFEF; border:0" />
									</logic:notPresent>
								</td>
							</tr>
							<tr>
								<td><strong>Data e Hora de Gera&ccedil;&atilde;o do
								D&eacute;bito:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="parcelamentoItem"
									property="debitoACobrarGeral.debitoACobrarHistorico.debitoGeradoRealizar">
									<input type="text" size="10" maxlength="10" readonly="true"
										style="background-color:#EFEFEF; border:0"
										value="<bean:write name="parcelamentoItem"  property="debitoACobrarGeral.debitoACobrarHistorico.debitoGeradoRealizar" formatKey="date.format" />">
											&nbsp; <input type="text" size="8" maxlength="8"
										readonly="true" style="background-color:#EFEFEF; border:0"
										value="<bean:write name="parcelamentoItem"  property="debitoACobrarGeral.debitoACobrarHistorico.debitoGeradoRealizar" formatKey="hour.format" />">
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>M&ecirc;s e Ano de Refer&ecirc;ncia do
								D&eacute;bito:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="parcelamentoItem"
									property="debitoACobrarGeral.debitoACobrarHistorico.anoMesReferenciaDebito">
									<input type="text" size="7" maxlength="7" readonly="true"
										style="background-color:#EFEFEF; border:0"
										value="<%="" + Util.formatarMesAnoReferencia(parcelamentoItem.getDebitoACobrarGeral().getDebitoACobrarHistorico().getAnoMesReferenciaDebito())%>">
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>M&ecirc;s e Ano da Cobran&ccedil;a do D&eacute;bito:</strong>
								</td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="parcelamentoItem"
									property="debitoACobrarGeral.debitoACobrarHistorico.anoMesCobrancaDebito">
									<input type="text" size="7" maxlength="7" readonly="true"
										style="background-color:#EFEFEF; border:0"
										value="<%="" + Util.formatarMesAnoReferencia(parcelamentoItem.getDebitoACobrarGeral().getDebitoACobrarHistorico().getAnoMesCobrancaDebito())%>">
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>N&uacute;mero de Presta&ccedil;&otilde;es Cobradas:</strong>
								</td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="parcelamentoItem"
									property="debitoACobrarGeral.debitoACobrarHistorico.prestacaoCobradas">
									<html:text name="parcelamentoItem"
										property="debitoACobrarGeral.debitoACobrarHistorico.prestacaoCobradas"
										size="7" maxlength="7" readonly="true"
										style="background-color:#EFEFEF; border:0; text-align: right;" />
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>N&uacute;mero Total de Presta&ccedil;&otilde;es:</strong>
								</td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="parcelamentoItem"
									property="debitoACobrarGeral.debitoACobrarHistorico.numeroPrestacaoDebitoMenosBonus">
									<html:text name="parcelamentoItem"
										property="debitoACobrarGeral.debitoACobrarHistorico.numeroPrestacaoDebitoMenosBonus"
										size="7" maxlength="7" readonly="true"
										style="background-color:#EFEFEF; border:0; text-align: right;" />
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>Valor Total do D&eacute;bito:</strong></td>
								<td colspan="2" align="left"><logic:notEmpty
									name="parcelamentoItem"
									property="debitoACobrarGeral.debitoACobrarHistorico.valorDebito">
									<input type="text" size="17" maxlength="17" readonly="true"
										style="background-color:#EFEFEF; border:0; text-align: right;"
										value="<%="" + Util.formatarMoedaReal(parcelamentoItem.getDebitoACobrarGeral().getDebitoACobrarHistorico().getValorDebito())%>">
								</logic:notEmpty></td>
							</tr>
							<tr>
								<td><strong>Valor Restante a Ser Cobrado:</strong></td>
								<td colspan="2" align="left"><logic:notEmpty
									name="parcelamentoItem"
									property="debitoACobrarGeral.debitoACobrarHistorico.valorTotalComBonus">
									<input type="text" size="17" maxlength="17" readonly="true"
										style="background-color:#EFEFEF; border:0; text-align: right;"
										value="<%="" + Util.formatarMoedaReal(parcelamentoItem.getDebitoACobrarGeral().getDebitoACobrarHistorico().getValorTotalComBonus())%>">
								</logic:notEmpty></td>
							</tr>
							<tr>
								<td><strong>Taxa de Juros do Financiamento:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="parcelamentoItem"
									property="debitoACobrarGeral.debitoACobrarHistorico.percentualTaxaJurosFinanciamento">
									<input type="text" size="17" maxlength="17" readonly="true"
										style="background-color:#EFEFEF; border:0; text-align: right;"
										value="<%="" + Util.formatarMoedaReal(parcelamentoItem.getDebitoACobrarGeral().getDebitoACobrarHistorico().getPercentualTaxaJurosFinanciamento())%>">
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>Registro de Atendimento:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="parcelamentoItem"
									property="debitoACobrarGeral.debitoACobrarHistorico.registroAtendimento">
									<html:text name="parcelamentoItem"
										property="debitoACobrarGeral.debitoACobrarHistorico.registroAtendimento.id"
										size="9" maxlength="9" readonly="true"
										style="background-color:#EFEFEF; border:0; text-align: right;" />
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>Ordem de Servi&ccedil;o:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="parcelamentoItem"
									property="debitoACobrarGeral.debitoACobrarHistorico.ordemServico">
									<html:text name="parcelamentoItem"
										property="debitoACobrarGeral.debitoACobrarHistorico.ordemServico.id"
										size="9" maxlength="9" readonly="true"
										style="background-color:#EFEFEF; border:0" />
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>Tipo do Financiamento:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="parcelamentoItem"
									property="debitoACobrarGeral.debitoACobrarHistorico.financiamentoTipo">
									<html:text name="parcelamentoItem"
										property="debitoACobrarGeral.debitoACobrarHistorico.financiamentoTipo.descricao"
										size="40" maxlength="40" readonly="true"
										style="background-color:#EFEFEF; border:0" />
								</logic:notEmpty></div>
								</td>
							</tr>
							<tr>
								<td><strong>Forma de Cobran&ccedil;a:</strong></td>
								<td colspan="2" align="right">
								<div align="left"><logic:notEmpty name="parcelamentoItem"
									property="debitoACobrarGeral.debitoACobrarHistorico.cobrancaForma">
									<html:text name="parcelamentoItem"
										property="debitoACobrarGeral.debitoACobrarHistorico.cobrancaForma.descricao"
										size="40" maxlength="40" readonly="true"
										style="background-color:#EFEFEF; border:0" />
								</logic:notEmpty></div>
								</td>
							</tr>
							
							<tr>
								<td><strong>Matr�cula do Im�vel Origem:</strong></td>
								<td colspan="2">
									
									<logic:present name="parcelamentoItem" property="debitoACobrarGeral.debitoACobrarHistorico.debitoACobrarGeralOrigem">
										<html:text name="parcelamentoItem" property="debitoACobrarGeral.debitoACobrarHistorico.debitoACobrarGeralOrigem.imovel.id"
										size="12" maxlength="10" readonly="true"
										style="background-color:#EFEFEF; border:0" />
									</logic:present>
									
									<logic:notPresent name="parcelamentoItem" property="debitoACobrarGeral">
										<input type="text" name="imovelOrigem" size="12" maxlength="10"
										readonly="true" style="background-color:#EFEFEF; border:0" />
									</logic:notPresent>
									
								</td>
							</tr>
					</table>
					<table width="100%" border="0">
						<tr>
							<td colspan="3">
							<hr>
							</td>
						</tr>
						</logic:notEmpty>
						
						</logic:iterate>
					</table>
					<!-- Par�metros de fiscaliza��o de d�bitos a cobrar -->
					<logic:present name="fiscalizarParametroCalculoDebito" scope="session">
						<table width="100%" border="0">
							<tr align="center">
								<td align="center"><strong>PAR&Acirc;METROS PARA CALCULAR O D&Eacute;BITO</strong></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
						</table>
						<table>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="categoria">
								<tr>
									<td><strong>Categoria Principal:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="categoria.id"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
												<html:text name="fiscalizarParametroCalculoDebito"
													property="categoria.descricao"
													size="20" maxlength="20" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="subcategoria">
								<tr>
									<td><strong>Subcategoria Principal:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="subcategoria.codigo"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
												<html:text name="fiscalizarParametroCalculoDebito"
													property="subcategoria.descricaoAbreviada"
													size="20" maxlength="20" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>	
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="anoMesApuracaoInicial">
								<tr>
									<td><strong>Ano/M&ecirc;s de apura&ccedil;&atilde;o inicial:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="anoMesApuracaoInicial"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="anoMesApuracaoFinal">
								<tr>
									<td><strong>Ano/M&ecirc;s de apura&ccedil;&atilde;o final:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="anoMesApuracaoFinal"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
								<logic:notEmpty name="fiscalizarParametroCalculoDebito"
									property="numeroMesesApurado">
								<tr>
									<td><strong>N&uacute;mero de meses apurado:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="numeroMesesApurado"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="consumoAgua">
								<tr>
									<td><strong>Consumo de &aacute;gua faturado:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="consumoAgua"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="consumoEsgoto">
								<tr>
									<td><strong>Volume de esgoto faturado:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="consumoEsgoto"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="valorAguar">
								<tr>
									<td><strong>Valor de &aacute;gua calculado:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="valorAguar"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="valorEsgoto">
								<tr>
									<td><strong>Valor de esgoto calculado:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="valorEsgoto"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="valorEsgoto">
								<tr>
									<td><strong>Percentual de esgoto:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="percentualEsgoto"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="consumoMinimo">
								<tr>
									<td><strong>Consumo m&iacute;nimo do im&oacute;vel:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="consumoMinimo"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="qtdEconomiaAutoInfracao">
								<tr>
									<td><strong>Quantidade de economias:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="qtdEconomiaAutoInfracao"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="valorTarifaAutoInfracao">
								<tr>
									<td><strong>Valor da tarifa utilizada <br> para auto de infra&ccedil;&atilde;o:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="valorTarifaAutoInfracao"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="fatorMultiplicador">
								<tr>
									<td><strong>Fator de muliplica&ccedil;&atilde;o:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="fatorMultiplicador"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="fatorReincidencia">
								<tr>
									<td><strong>Fator de reincid&ecirc;ncia:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="fatorReincidencia"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="fiscalizarParametroCalculoDebito"
								property="codCalculoConsumo">
								<tr>
									<td><strong>C&oacute;digo do c&aacute;lculo de d&eacute;bito:</strong></td>
									<td colspan="2" align="right">
										<div align="left">
												<html:text name="fiscalizarParametroCalculoDebito"
													property="codCalculoConsumo"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
						</table>
						<table width="100%" border="0">
							<tr>
								<td colspan="3">
								<hr>
								</td>
							</tr>
						</table>
					</logic:present>
					<!-- Final par�metros de fiscaliza��o de d�bitos a cobrar -->
					
					<!-- DEMONSTRATIVO DA FORMULA DE CALCULO -->
					<logic:present name="demonstrativoFormulaCalculoHelper">
						<table width="100%" border="0">
							<tr align="center">
								<td align="center"><strong>DEMONSTRATIVO DA F�RMULA DE C�LCULO</strong></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
						</table>
						
						<table>
							<logic:notEmpty name="demonstrativoFormulaCalculoHelper"
								property="formula" >
								<tr>
									<td colspan="2">
										<div align="left"> F�RMULA: 
										<bean:write name="demonstrativoFormulaCalculoHelper" filter="false" property="formula" />
										</div>										
									</td>
								</tr>
							</logic:notEmpty>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<logic:notEmpty name="demonstrativoFormulaCalculoHelper"
								property="somaAguaEsgoto">
								<tr>
									<td colspan="2" align="right">
										<div align="left">
												SOMA DO VALOR DE �GUA + VALOR DE ESGOTO:  
												<html:text name="fiscalizarParametroCalculoDebito"
													property="valorAguar"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
												&nbsp;+&nbsp;
												<html:text name="fiscalizarParametroCalculoDebito"
													property="valorEsgoto"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
												&nbsp;=&nbsp;
												<html:text name="demonstrativoFormulaCalculoHelper"
													property="somaAguaEsgoto"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
							<logic:notEmpty name="demonstrativoFormulaCalculoHelper"
								property="valorInfracao">
								<tr>
									<td colspan="2">
										<div align="left"> VALOR DA INFRA��O: 
										<bean:write name="demonstrativoFormulaCalculoHelper"  property="valorInfracao" />										
										</div>										
									</td>
								</tr>
							</logic:notEmpty>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<logic:notEmpty name="demonstrativoFormulaCalculoHelper"
								property="numeroMesesFatura">
								<tr>
									<td colspan="2">
										<div align="left"> N�MERO DE MESES FATURA: 
										<html:text name="demonstrativoFormulaCalculoHelper"
													property="numeroMesesFatura"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />									
										</div>										
									</td>
								</tr>
								<tr>
								<td>&nbsp;</td>
								</tr>
							</logic:notEmpty>
							
							<logic:notEmpty name="demonstrativoFormulaCalculoHelper"
								property="fatorReincidencia">
								<tr>
									<td colspan="2">
										<div align="left"> FATOR DE REINCID�NCIA: 
										<bean:write name="demonstrativoFormulaCalculoHelper"  property="fatorReincidencia" />										
										</div>										
									</td>
								</tr>
								
								<tr>
								<td>&nbsp;</td>
								</tr>
							</logic:notEmpty>
							
							<logic:notEmpty name="demonstrativoFormulaCalculoHelper"
								property="valorInfracaoCalculado">
								<tr>
									<td colspan="2" align="right">
										<div align="left">
												VALOR DA INFRA��O CALCULADO: 
												<html:text name="fiscalizarParametroCalculoDebito"
													property="valorTarifaAutoInfracao"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
												&nbsp;X&nbsp;
												<html:text name="fiscalizarParametroCalculoDebito"
													property="qtdEconomiaAutoInfracao"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
												&nbsp;=&nbsp;
												<html:text name="demonstrativoFormulaCalculoHelper"
													property="valorInfracaoCalculado"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
							</logic:notEmpty>
							
							<logic:notEmpty name="demonstrativoFormulaCalculoHelper"
								property="fatorReincidenciaCadastradoInfracao">
								<tr>
									<td colspan="2" align="right">
										<div align="left">
												FATOR DE REINCID�NCIA CADASTRADO PARA INFRA��O:&nbsp;
												<html:text name="demonstrativoFormulaCalculoHelper"
													property="fatorReincidenciaCadastradoInfracao"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
								<tr>
								<td>&nbsp;</td>
								</tr>
							</logic:notEmpty>							
							
							<logic:notEmpty name="demonstrativoFormulaCalculoHelper"
								property="valorDebitoCalculado">
								<tr>
									<td colspan="2" align="right">
										<div align="left">
												VALOR DO D�BITO CALCULADO:
												<logic:notEmpty name="demonstrativoFormulaCalculoHelper"
												property="valorInfracaoCalculado">
												<html:text name="demonstrativoFormulaCalculoHelper"
													property="valorInfracaoCalculado"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
												</logic:notEmpty>
												<logic:notEmpty name="demonstrativoFormulaCalculoHelper"
												property="somaAguaEsgoto">
												<html:text name="demonstrativoFormulaCalculoHelper"
													property="somaAguaEsgoto"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
												</logic:notEmpty>
												&nbsp;X&nbsp;
												<logic:notEmpty name="demonstrativoFormulaCalculoHelper"
												property="fatorReincidenciaCadastradoInfracao">
												<html:text name="demonstrativoFormulaCalculoHelper"
													property="fatorReincidenciaCadastradoInfracao"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
												</logic:notEmpty>
												<logic:notEmpty name="demonstrativoFormulaCalculoHelper"
												property="numeroMesesFatura">
												<html:text name="demonstrativoFormulaCalculoHelper"
													property="numeroMesesFatura"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
												</logic:notEmpty>
												&nbsp;=&nbsp;
												<html:text name="demonstrativoFormulaCalculoHelper"
													property="valorDebitoCalculado"
													size="9" maxlength="9" readonly="true"
													style="background-color:#EFEFEF; border:0; text-align: right;" />
										</div>
									</td>
								</tr>
							</logic:notEmpty>
						</table>
						<table width="100%" border="0">
							<tr>
								<td colspan="3">
								<hr>
								</td>
							</tr>
						</table>
					</logic:present>
					<!-- Fim RM1165 -->
					
					</div>
					</td>
				</tr>
			</logic:present>
			
			<tr>
				<td colspan="3">&nbsp;</td>
			</tr>
			
			<table width="100%" border="0">
				<tr>
					<td height="24"><logic:present
						name="caminhoRetornoTelaConsultaDebitoACobrar">
						<input type="button" class="bottonRightCol" value="Voltar"
							style="width: 70px;" onclick="javascript:history.back();" />
					</logic:present>
					<td align="right"><input name="Button" type="button"
						class="bottonRightCol" value="Fechar"
						onClick="javascript:fechar();"></td>
				</tr>
			</table>
		</table>
		<p>&nbsp;</p>
		</td>
	</tr>
</table>
</body>
</html:html>
