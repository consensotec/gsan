<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<html:javascript formName="InformarSistemaParametrosActionForm" dynamicJavascript="false" staticJavascript="true" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script>

	var bCancel = false;

	function validateInformarSistemaParametrosActionForm(form) {
		if (bCancel){
			return true;
		}else{
			return  validateRequired(form) && 
			validateInteger(form) && validateCaracterEspecial(form);
		}
	}

	function caracteresespeciais () { 
		this.aa = new Array("codigoEmpresaFebraban", "C�digo da Empresa para FEBRABAN deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.ab = new Array("numeroLayOut", "N�mero do Layout da FEBRABAN deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.ac = new Array("maximoParcelas", "M�ximo de Parcelas para um Financiamento deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.ad = new Array("numeroMaximoParcelaCredito", "N�mero M�ximo para Parcela de Cr�dito deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.ae = new Array("numeroModuloDigitoVerificador", "N�mero do m�dulo verificador deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.af = new Array("numeroMesesPesquisaImoveisRamaisSuprimidos", "N�mero meses para pesquisa de im�veis com ramais suprimidos deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.ag = new Array("numeroAnoQuitacao", "N�mero de anos para gera��o da declara��o de quita��o de d�bitos deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.ah = new Array("numeroMesesAnterioresParaDeclaracaoQuitacao", "Quantidade de meses anteriores gera��o declara��o de quita��o de d�bitos deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.ai = new Array("valorMaximoBaixado", "Valor M�ximo a ser Baixado deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.aj = new Array("diferencaMaximaBaixado", "Diferen�a M�xima para Baixa deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.al = new Array("percentualEntradaMinima", "Percentual de Entrada M�nima para Financiamento deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.am = new Array("percentualMaximoAbatimento", "Percentual M�ximo para Abatimento de um Servi�o deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.an = new Array("percentualTaxaFinanciamento", "Percentual de Taxa de Juros para Financiamento deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.ap = new Array("percentualCalculoIndice", "Percentual da M�dia do �ndice para C�lculo do Parcelamento deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
	  }
	
	function IntegerValidations () {
		this.aa = new Array("codigoEmpresaFebraban", "C�digo da Empresa para FEBRABAN deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.ab = new Array("numeroLayOut", "N�mero do Layout da FEBRABAN deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.ac = new Array("maximoParcelas", "M�ximo de Parcelas para um Financiamento deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.ad = new Array("numeroMaximoParcelaCredito", "N�mero M�ximo para Parcela de Cr�dito deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.ae = new Array("numeroModuloDigitoVerificador", "N�mero do m�dulo verificador deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.af = new Array("numeroMesesPesquisaImoveisRamaisSuprimidos", "N�mero meses para pesquisa de im�veis com ramais suprimidos deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.ag = new Array("numeroAnoQuitacao", "N�mero de anos para gera��o da declara��o de quita��o de d�bitos deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.ah = new Array("numeroMesesAnterioresParaDeclaracaoQuitacao", "Quantidade de meses anteriores gera��o declara��o de quita��o de d�bitos deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.ai = new Array("valorMaximoBaixado", "Valor M�ximo a ser Baixado deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.aj = new Array("diferencaMaximaBaixado", "Diferen�a M�xima para Baixa deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.al = new Array("percentualEntradaMinima", "Percentual de Entrada M�nima para Financiamento deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
		this.am = new Array("percentualMaximoAbatimento", "Percentual M�ximo para Abatimento de um Servi�o deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
	}

	function required () {
		this.aa = new Array("mesAnoReferenciaArrecadacao", "Informe M�s e Ano de Refer�ncia.", new Function ("varName", " return this[varName];"));
	}

</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/informarParametrosSistemaWizardAction" method="post" 
	onsubmit="return validateInformarSistemaParametrosActionForm(this);">

	<jsp:include 
	page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=3" />

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<input type="hidden" name="numeroPagina" value="3" />
	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="150" valign="top" class="leftcoltext">
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
			<td width="655" valign="top" class="centercoltext">
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
					<td class="parabg">Informar Par�metros do Sistema</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0" dwcopytype="CopyTableRow">
				<tr>
					<td>Para informar par�metros do sistema, informe os dados abaixo:
					<td align="right"><a
						href="javascript: abrirPopup('/gsan/help/help.jsp?mapIDHelpSet=clienteInserirAbaNomeTipo', 500, 700);"><span
						style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>
				</tr>
			</table>

			<table width="100%" border="0">


				<tr>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center"><strong>Par�metros para Arrecada��o:</strong></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td width="40%" align="left">
						<strong>M�s e Ano de Refer�ncia:</strong><font color="#FF0000">*</font>
					</td>
					<td width="82%">
						<html:text property="mesAnoReferenciaArrecadacao"
							size="7" 
							maxlength="7"
							onkeyup="javascript:mascaraAnoMes(this,event);" 
							onkeypress="return isCampoNumerico(event);" disabled="true"/><strong>mm/aaaa</strong>
					</td>
				</tr>
				
				<tr>
					<td width="40%" align="left">
						<strong>C�digo da Empresa para FEBRABAN:</strong>
					</td>
					<td>
						<html:text maxlength="4" 
							property="codigoEmpresaFebraban"
							size="4" 
							onkeyup="javascript:verificaNumeroInteiro(this);" />
					</td>
				</tr>
				
				<tr>
					<td width="40%" align="left">
						<strong>N�mero do Layout da FEBRABAN:</strong>
					</td>
					<td>
						<html:text maxlength="4" 
							property="numeroLayOut" 
							size="4"
							onkeyup="javascript:verificaNumeroInteiro(this);" />
					</td>
				</tr>
				
				<tr>
					<td width="40%" align="left">
						<strong>Identificador da Conta Banc�ria para Devolu��o:</strong>
					</td>
					<td>
						<html:select property="indentificadorContaDevolucao">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoContaBancaria" 
							labelProperty="id"
							property="id" />
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td width="40%" align="left">
						<strong>N�mero do m�dulo verificador:</strong>
					</td>
					<td>
						<html:text maxlength="2" 
							property="numeroModuloDigitoVerificador" 
							size="3"
							onkeypress="return isCampoNumerico(event);" />
					</td>
				</tr>
				
				<tr>
					<td width="40%" align="left">
						<strong>N�mero meses para pesquisa de im�veis com ramais suprimidos:</strong>
					</td>
					<td>
						<html:text maxlength="3" 
							property="numeroMesesPesquisaImoveisRamaisSuprimidos" 
							size="4"
							onkeypress="return isCampoNumerico(event);" />
					</td>
				</tr>

				<tr>
					<td width="40%" align="left">
						<strong>N�mero de anos para gera��o da declara��o de quita��o de d�bitos:</strong>
					</td>
					<td>
						<html:text maxlength="2" 
							property="numeroAnoQuitacao" 
							size="4"
							onkeypress="return isCampoNumerico(event);" />
					</td>
				</tr>
				
				<tr>
					<td width="40%" align="left">
						<strong>Quantidade de meses anteriores gera��o declara��o de quita��o de d�bitos:</strong>
					</td>
					<td>
						<html:text maxlength="2" 
							property="numeroMesesAnterioresParaDeclaracaoQuitacao" 
							size="4"
							onkeypress="return isCampoNumerico(event);" />
					</td>
				</tr>
				
				
				<tr>
						<td width="40%">
							<strong>Contas parceladas para declara��o de quita��o de d�bitos: </strong>
						</td>
						<td>
							<strong> 
							<html:radio property="indicadorContaParcelada" value="1" /> Sim 
							<html:radio property="indicadorContaParcelada" value="2" /> N&atilde;o
							</strong>
						</td>
				</tr>
				
				<tr>
						<td width="40%">
							<strong>Contas em cobran�a judicial para declara��o de quita��o de d�bitos: </strong>
						</td>
						<td>
							<strong> 
							<html:radio property="indicadorCobrancaJudical" value="1" /> Sim 
							<html:radio property="indicadorCobrancaJudical" value="2" /> N&atilde;o
							</strong>
						</td>
				</tr>
				
				<tr>
						<td width="40%">
							<strong>Indicador do valor do movimento arrecadador: </strong><font color="#FF0000">*</font>
						</td>
						<td>
							<strong> 
							<html:radio property="indicadorValorMovimentoArrecadador" value="1" /> Sim 
							<html:radio property="indicadorValorMovimentoArrecadador" value="2" /> N&atilde;o
							</strong>
						</td>
				</tr>
				
				<tr>
					<td width="40%" align="left">
						<strong>N&uacute;mero Conv&ecirc;nio Ficha Compensa&ccedil;&atilde;o:</strong>
					</td>
					<td>
						<html:text maxlength="7" 
							property="numeroConvenioFichaCompensacao" 
							size="7"
							onkeypress="return isCampoNumerico(event);" />
					</td>
				</tr>
				<tr>
					<td width="40%" align="left">
						<strong>Baixa Autom�tica de Pagamentos:</strong>
					</td>
				</tr>
				<tr>
					<td width="40%" align="left">
						<strong>Valor M�ximo a ser Baixado:</strong>
					</td>					
					<td width="87%">
						<html:text property="valorMaximoBaixado"
							size="13" maxlength="13"
							onkeyup="javascript:formataValorMonetario(this, 13);" /> 
					</td>
				</tr>				
				<tr>
					<td width="40%" align="left">
						<strong>Diferen�a M�xima para Baixa:</strong>
					</td>					
					<td width="87%">
						<html:text property="diferencaMaximaBaixado"
							size="13" maxlength="13"
							onkeyup="javascript:formataValorMonetario(this, 13);" /> 
					</td>
				</tr>
				
				
				<tr>
						<td width="40%">
							<strong>Indicador Contrato Banc�rio da<br />Carteira 17:</strong>
						</td>
						<td>
							<strong> 
							<html:radio property="indicadorCarteira17" value="1" /> Sim 
							<html:radio property="indicadorCarteira17" value="2" /> N&atilde;o
							</strong>
						</td>
				</tr>
				
								
				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td colspan="2" align="center">
						<strong>Par�metros para o Financeiro:</strong>
					</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td width="40%" align="left">
						<strong> Percentual de Entrada M�nima para Financiamento:</strong>
					</td>					
					<td width="87%">
						<html:text property="percentualEntradaMinima"
							size="5" 
							maxlength="5"
							onkeyup="javascript:formataValorMonetario(this, 5);" /> 
					</td>
				</tr>				
				<tr>
					<td width="40%" align="left">
						<strong>M�ximo de Parcelas para um Financiamento:</strong>
					</td>
					<td>
						<html:text maxlength="5" 
							property="maximoParcelas" 
							size="5"
							onkeyup="javascript:verificaNumeroInteiro(this);" />
					</td>
				</tr>				
				<tr>
					<td width="40%" align="left">
						<strong>Percentual M�ximo para Abatimento de um Servi�o:</strong>
					</td>
					<td>
						<html:text maxlength="5" 
							property="percentualMaximoAbatimento"
							size="5" 
							onkeyup="javascript:formataValorMonetario(this, 5);" />
					</td>
				</tr>				
				<tr>
					<td width="40%" align="left">
						<strong>Percentual de Taxa de Juros para Financiamento:</strong>
					</td>
					<td>
						<html:text maxlength="5" 
							property="percentualTaxaFinanciamento"
							size="5" 
							onkeyup="javascript:formataValorMonetario(this, 5);" />
					</td>
				</tr>				
				<tr>
					<td width="40%" align="left">
						<strong>N�mero M�ximo para Parcela de Cr�dito:</strong>
					</td>					
					<td>
						<html:text maxlength="3" 
							property="numeroMaximoParcelaCredito"
							size="3" 
							onkeyup="javascript:verificaNumeroInteiro(this);" />
					</td>
				</tr>				
				<tr>
					<td width="40%" align="left">
						<strong>Percentual da M�dia do �ndice para C�lculo do Parcelamento:</strong>
					</td>
					<td>
						<html:text maxlength="5" 
							property="percentualCalculoIndice"
							size="5" 
							onkeyup="javascript:formataValorMonetario(this, 5);" /></td>
				</tr>				
				<tr>
					<td width="40%" align="left">
						<strong>C�digo Relat�rio Dados Di�rios:</strong><font color="#FF0000">*</font>
					</td>
					<td align="right" colspan="2">
						<div align="left"><span class="style2">
				  				<html:select property="cdDadosDiarios" tabindex="1">
								<html:option value="<%= ""+ConstantesSistema.NUMERO_NAO_INFORMADO %>">&nbsp;</html:option>				  
								<html:option value="0">Nao Exibir Faturamento Cobrado em Conta</html:option>
								<html:option value="1">Exibir Faturamento Cobrado em Conta</html:option>
								</html:select>
				  			</span>
				  		</div>
				  	</td>
				</tr>				
				<tr>
					<td></td>
					<td><strong><font color="#FF0000">*</font></strong>Campo obrigat&oacute;rio</td>
				</tr>				
				<tr>
					<td colspan="2">
					<div align="right"><jsp:include
						page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar_valida_voltar.jsp?numeroPagina=3" /></div>
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
