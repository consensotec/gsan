<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.Util"%>
<%@ page import="gcom.cadastro.tarifasocial.TarifaSocialComandoCarta"%>
<%@ page import="gcom.seguranca.acesso.usuario.Usuario"%>


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
				<td class="parabg">Consultar Comando</td>
				<td width="11"><img border="0"
					src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
			</tr>
		</table>

		<p>&nbsp;</p>

	
		<logic:present name="tarifaSocialComandoCarta" scope="session">
			<table width="100%" border="0">
				<tr>
					<td><strong>N�mero do Comando:</strong></td>
					<td>
						<html:text name="tarifaSocialComandoCarta" property="id" size="16" maxlength="10" 
						readonly="true"	style="background-color:#EFEFEF; border:0" />
					</td>
				</tr>
				<tr>
					<td><strong>C�digo da Ger�ncia:</strong></td>
					<td>
						<logic:empty name="tarifaSocialComandoCarta" property="gerenciaRegional" >
							<input type="text" value="" name="gerenciaRegional" size="16" readonly="true"
								style="background-color:#EFEFEF; border:0" />
						</logic:empty>
						<logic:notEmpty name="tarifaSocialComandoCarta" property="gerenciaRegional" >
							<html:text name="tarifaSocialComandoCarta" property="gerenciaRegional.id" size="16" maxlength="10" 
							readonly="true"	style="background-color:#EFEFEF; border:0" />
						</logic:notEmpty>
					</td>
				</tr>
				<tr>
					<td><strong>C�digo da Unidade:</strong></td>
					<td>
						<logic:empty name="tarifaSocialComandoCarta" property="unidadeNegocio" >
							<input type="text" value="" name="unidadeNegocio" size="16" readonly="true"
								style="background-color:#EFEFEF; border:0" />
						</logic:empty>
						<logic:notEmpty name="tarifaSocialComandoCarta" property="unidadeNegocio" >
							<html:text name="tarifaSocialComandoCarta" property="unidadeNegocio.id" size="16" maxlength="10" 
							readonly="true"	style="background-color:#EFEFEF; border:0" />
						</logic:notEmpty>
					</td>
				</tr>
				<tr>
					<td><strong>C�digo da Localidade:</strong></td>
					<td>
						<logic:empty name="tarifaSocialComandoCarta" property="localidade" >
							<input type="text" value="" name="localidade" size="16" readonly="true"
								style="background-color:#EFEFEF; border:0" />
						</logic:empty>
						<logic:notEmpty name="tarifaSocialComandoCarta" property="localidade" >
							<html:text name="tarifaSocialComandoCarta" property="localidade.id" size="16" maxlength="10" 
							readonly="true"	style="background-color:#EFEFEF; border:0" />
						</logic:notEmpty>
					</td>
				</tr>
				<tr>
					<td><strong>Tipo da Carta:</strong></td>
					<td>
						<logic:equal name="tarifaSocialComandoCarta" property="codigoTipoCarta" value="1">
						<input type="text" 
							value="RECADASTRAMENTO" 
							name="codigoTipoCarta"
							size="20" 
							readonly="true"
							style="background-color:#EFEFEF; border:0" />
						</logic:equal> 

						<logic:equal name="tarifaSocialComandoCarta" property="codigoTipoCarta" value="2">
							<input type="text" 
								value="COBRAN�A" 
								name="codigoTipoCarta"
								size="20" 
								readonly="true"
								style="background-color:#EFEFEF; border:0" />
						</logic:equal>
					</td>
				</tr>	
				<tr>
					<td><strong>Qtde de Dias para Comparecimento:</strong></td>
					<td>
						<html:text name="tarifaSocialComandoCarta" property="quantidadeDiasComparecimento" size="16" maxlength="10" 
						readonly="true"	style="background-color:#EFEFEF; border:0" />
					</td>
				</tr>
				<tr>
					<td><strong>Qtde de Dias para Conta Vencida:</strong></td>
					<td>
						<html:text name="tarifaSocialComandoCarta" property="quantidadeDiasDebitoVencimento" size="16" maxlength="10" 
						readonly="true"	style="background-color:#EFEFEF; border:0" />
					</td>
				</tr>
				<tr>
					<td><strong>M�s/Ano Inicial Informado:</strong></td>
					<td>
						<html:text name="tarifaSocialComandoCarta" property="anoMesInicialImplantacaoFormatado" size="16" 
						readonly="true"	style="background-color:#EFEFEF; border:0" />
					</td>
					<td><strong>M�s/Ano Final Informado:</strong></td>
					<td>
						<html:text name="tarifaSocialComandoCarta" property="anoMesFinalImplantacaoFormatado" size="16" 
						readonly="true"	style="background-color:#EFEFEF; border:0" />
					</td>
				</tr>
				<tr>
					
				</tr>
				<tr>
					<td><strong>Crit�rio de CPF:</strong></td>
					<td>
						<logic:equal name="tarifaSocialComandoCarta" property="indicadorCriterioCpf" value="1">
						<input type="text" value="SIM" name="indicadorCriterioCpf" size="16" readonly="true"
							style="background-color:#EFEFEF; border:0" />
						</logic:equal> 
						<logic:equal name="tarifaSocialComandoCarta" property="indicadorCriterioCpf" value="2">
							<input type="text" value="N�O" name="indicadorCriterioCpf" size="16" readonly="true"
								style="background-color:#EFEFEF; border:0" />
						</logic:equal>
					</td>
					<td><strong>Crit�rio Validade Cart�o Seguro Desemprego:</strong></td>
					<td>
						<logic:equal name="tarifaSocialComandoCarta" property="indicadorCriterioSeguroDesemprego" value="1">
						<input type="text" value="SIM" name="indicadorCriterioSeguroDesemprego"	size="16" readonly="true"
							style="background-color:#EFEFEF; border:0" />
						</logic:equal> 
						<logic:equal name="tarifaSocialComandoCarta" property="indicadorCriterioSeguroDesemprego" value="2">
							<input type="text" value="N�O" name="indicadorCriterioSeguroDesemprego"	size="16" readonly="true"
								style="background-color:#EFEFEF; border:0" />
						</logic:equal>
					</td>
				</tr>	
				<tr>
					<td><strong>Crit�rio de Identidade:</strong></td>
					<td>
						<logic:equal name="tarifaSocialComandoCarta" property="indicadorCriterioIdentidade" value="1">
						<input type="text" 
							value="SIM" 
							name="indicadorCriterioIdentidade"
							size="16" 
							readonly="true"
							style="background-color:#EFEFEF; border:0" />
						</logic:equal> 

						<logic:equal name="tarifaSocialComandoCarta" property="indicadorCriterioIdentidade" value="2">
							<input type="text" 
								value="N�O" 
								name="indicadorCriterioIdentidade"
								size="16" 
								readonly="true"
								style="background-color:#EFEFEF; border:0" />
						</logic:equal>
					</td>
					<td><strong>Crit�rio Renda Comprovada:</strong></td>
					<td>
						<logic:equal name="tarifaSocialComandoCarta" property="indicadorCriterioRendaComprovada" value="1">
						<input type="text" 
							value="SIM" 
							name="indicadorCriterioRendaComprovada"
							size="16" 
							readonly="true"
							style="background-color:#EFEFEF; border:0" />
						</logic:equal> 

						<logic:equal name="tarifaSocialComandoCarta" property="indicadorCriterioRendaComprovada" value="2">
							<input type="text" 
								value="N�O" 
								name="indicadorCriterioRendaComprovada"
								size="16" 
								readonly="true"
								style="background-color:#EFEFEF; border:0" />
						</logic:equal>
					</td>
				</tr>	
				<tr>
					<td><strong>Crit�rio Contrato de Energia:</strong></td>
					<td>
						<logic:equal name="tarifaSocialComandoCarta" property="indicadorCriterioContratoEnergia" value="1">
						<input type="text" 
							value="SIM" 
							name="indicadorCriterioContratoEnergia"
							size="16" 
							readonly="true"
							style="background-color:#EFEFEF; border:0" />
						</logic:equal> 

						<logic:equal name="tarifaSocialComandoCarta" property="indicadorCriterioContratoEnergia" value="2">
							<input type="text" 
								value="N�O" 
								name="indicadorCriterioContratoEnergia"
								size="16" 
								readonly="true"
								style="background-color:#EFEFEF; border:0" />
						</logic:equal>
					</td>
					<td><strong>Crit�rio Renda Declarada:</strong></td>
					<td>
						<logic:equal name="tarifaSocialComandoCarta" property="indicadorCriterioRendaDeclarada" value="1">
						<input type="text" 
							value="SIM" 
							name="indicadorCriterioRendaDeclarada"
							size="16" 
							readonly="true"
							style="background-color:#EFEFEF; border:0" />
						</logic:equal> 

						<logic:equal name="tarifaSocialComandoCarta" property="indicadorCriterioRendaDeclarada" value="2">
							<input type="text" 
								value="N�O" 
								name="indicadorCriterioRendaDeclarada"
								size="16" 
								readonly="true"
								style="background-color:#EFEFEF; border:0" />
						</logic:equal>
					</td>
				</tr>
				<tr>
					<td><strong>Crit�rio de Dados de Energia:</strong></td>
					<td>
						<logic:equal name="tarifaSocialComandoCarta" property="indicadorCriterioDadosEnergia" value="1">
						<input type="text" 
							value="SIM" 
							name="indicadorCriterioDadosEnergia"
							size="16" 
							readonly="true"
							style="background-color:#EFEFEF; border:0" />
						</logic:equal> 

						<logic:equal name="tarifaSocialComandoCarta" property="indicadorCriterioDadosEnergia" value="2">
							<input type="text" 
								value="N�O" 
								name="indicadorCriterioDadosEnergia"
								size="16" 
								readonly="true"
								style="background-color:#EFEFEF; border:0" />
						</logic:equal>
					</td>
					<td><strong>Crit�rio de Qtde de Economia:</strong></td>
					<td>
						<logic:equal name="tarifaSocialComandoCarta" property="indicadorCriterioQtdeEconomia" value="1">
						<input type="text" 
							value="SIM" 
							name="indicadorCriterioQtdeEconomia"
							size="16" 
							readonly="true"
							style="background-color:#EFEFEF; border:0" />
						</logic:equal> 

						<logic:equal name="tarifaSocialComandoCarta" property="indicadorCriterioQtdeEconomia" value="2">
							<input type="text" 
								value="N�O" 
								name="indicadorCriterioQtdeEconomia"
								size="16" 
								readonly="true"
								style="background-color:#EFEFEF; border:0" />
						</logic:equal>
					</td>
				</tr>
				<tr>
					<td><strong>Crit�rio de Programa Social:</strong></td>
					<td>
						<logic:equal name="tarifaSocialComandoCarta" property="indicadorCriterioProgramaSocial" value="1">
						<input type="text" 
							value="SIM" 
							name="indicadorCriterioProgramaSocial"
							size="16" 
							readonly="true"
							style="background-color:#EFEFEF; border:0" />
						</logic:equal> 

						<logic:equal name="tarifaSocialComandoCarta" property="indicadorCriterioProgramaSocial" value="2">
							<input type="text" 
								value="N�O" 
								name="indicadorCriterioProgramaSocial"
								size="16" 
								readonly="true"
								style="background-color:#EFEFEF; border:0" />
						</logic:equal>
					</td>
					<td><strong>Crit�rio de Tempo de Recadastramento:</strong></td>
					<td>
						<logic:equal name="tarifaSocialComandoCarta" property="indicadorCriterioRecadastramento" value="1">
						<input type="text" 
							value="SIM" 
							name="indicadorCriterioRecadastramento"
							size="16" 
							readonly="true"
							style="background-color:#EFEFEF; border:0" />
						</logic:equal> 

						<logic:equal name="tarifaSocialComandoCarta" property="indicadorCriterioRecadastramento" value="2">
							<input type="text" 
								value="N�O" 
								name="indicadorCriterioRecadastramento"
								size="16" 
								readonly="true"
								style="background-color:#EFEFEF; border:0" />
						</logic:equal>
					</td>
				</tr>
				
				<tr>
					<td><strong>Data de Gera��o das Cartas:</strong></td>
					<td>
						<input type="text" value="<bean:write name="tarifaSocialComandoCarta" property="dataGeracao" formatKey="datehour.format" />"
						size="16" readonly="true" style="background-color:#EFEFEF; border:0" />
					</td>
				</tr>
				<tr>
					<td><strong>Data de Execu��o do Comando:</strong></td>
					<td>
						<input type="text" value="<bean:write name="tarifaSocialComandoCarta" property="dataExecucao" formatKey="datehour.format" />"
						size="16" readonly="true" style="background-color:#EFEFEF; border:0" />
					</td>
				</tr>
				<tr>
					<td><strong>Qtde de Cartas Geradas:</strong></td>
					<td>
						<input type="text" value="<bean:write name="tarifaSocialComandoCarta" property="quantidadeCartasGeradas" />"
						size="16" readonly="true" style="background-color:#EFEFEF; border:0" />
					</td>
				</tr>
				<tr>
					<td><strong>Data Limite Comparecimento:</strong></td>
					<td>
						<input type="text" value="<bean:write name="tarifaSocialComandoCarta" property="dataLimiteComparecimentoFormatado" />"
						size="16" readonly="true" style="background-color:#EFEFEF; border:0" />
					</td>
				</tr>
				<tr>
					<td><strong>Usu�rio que Comandou Gera��o:</strong></td>
					<td>
						<logic:empty name="tarifaSocialComandoCarta" property="usuario" >
							<input type="text" value="" name="usuario" size="16" readonly="true"
								style="background-color:#EFEFEF; border:0" />
						</logic:empty>
						<logic:notEmpty name="tarifaSocialComandoCarta" property="usuario" >
							<html:text name="tarifaSocialComandoCarta" property="usuario.id" size="16" maxlength="10" 
							readonly="true"	style="background-color:#EFEFEF; border:0" />
						</logic:notEmpty>
					</td>
				</tr>
			</table>
		</logic:present>

		<table width="100%">
			<tr>
				
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