
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>


<html:javascript staticJavascript="false" formName="FiltrarContratoDemandaCondominiosResidenciaisActionForm" />

<script language="JavaScript">
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    var form = document.forms[0];
    if (tipoConsulta == 'imovel') {
      	form.matriculaImovel.value = codigoRegistro;
	  	form.action = 'exibirFiltrarContratoDemandaCondominiosResidenciaisAction.do?validarImovel=sim';
		form.submit();
    }   
}

function  limparForm(){
	var form = document.forms[0];
	form.numeroContrato.value = "";
	form.matriculaImovel.value = "";
	form.dataInicioContrato.value = "";
	form.dataInicioContrato2.value = "";
	form.dataFimContrato.value = "";
	form.dataFimContrato2.value = "";
	form.inscricaoImovel.value = "";
}

function pesquisarImovel() {
	var form = document.forms[0];
	abrirPopup('exibirPesquisarImovelAction.do', 400, 800);
}

function validarPeriodo(){
	var form = document.forms[0];
	var valido = true;
	if(form.dataInicioContrato.value != ''){
		if(form.dataInicioContrato2.value == ''){
			valido = false;
			alert('Informe a data final do período do início do contrato.');
		}
	}

	if(form.dataInicioContrato2.value != ''){
		if(form.dataInicioContrato.value == ''){
			valido = false;
			alert('Informe a data inicial do período do início do contrato.');
		}
	}

	if(form.dataFimContrato.value != ''){
		if(form.dataFimContrato2.value == ''){
			valido = false;
			alert('Informe a data final do período de término do contrato.');
		}
	}

	if(form.dataFimContrato2.value != ''){
		if(form.dataFimContrato.value == ''){
			valido = false;
			alert('Informe a data inicial do período de término do contrato.');
		}
	}
	return valido;
}

function verificarChecado(valor){
	form = document.forms[0];
	if(valor.value == 1){
		valor.checked = true;
	 }else{
		 valor.checked = false;
	}
}

function limparImovel(){
	var form = document.forms[0];
	form.inscricaoImovel.value = "";
	form.matriculaImovel.value = "";
	
}
</script>

<script type="text/javascript">
	$(document).ready(function(){
		$('#botaoFiltrar').click(function(){
			var form = document.forms[0];
			if ($('[name=indicadorAtualizar]:checked').val() !== undefined) {
				form.indicadorAtualizar.value = 1;
			}else{
				form.indicadorAtualizar.value = 2;
			}
			if(form.numeroContrato.value == '' && form.matriculaImovel.value == ''){
				if(form.dataInicioContrato.value == '' && form.dataInicioContrato2.value == '' && form.dataFimContrato.value == '' && form.dataFimContrato2.value == ''){
					alert('Informe pelo menos uma opção de seleção.');
				}else{
					if(validateFiltrarContratoDemandaCondominiosResidenciaisActionForm(form) && validarPeriodo()){
						form.action = 'filtrarContratoDemandaCondominiosResidenciaisAction.do?atualizar=' + form.indicadorAtualizar.value; 
						form.submit();
					}
				}
			}else{
				if(validateFiltrarContratoDemandaCondominiosResidenciaisActionForm(form) && validarPeriodo()){
					form.action = 'filtrarContratoDemandaCondominiosResidenciaisAction.do?atualizar=' + form.indicadorAtualizar.value;
					form.submit();
				}
			}
		});
	});
</script>

</head>

<body leftmargin="5" topmargin="5" onload="verificarChecado(document.forms[0].indicadorAtualizar);">

<html:form action="/filtrarContratoDemandaCondominiosResidenciaisAction"
	name="FiltrarContratoDemandaCondominiosResidenciaisActionForm"
	type="gsan.gui.faturamento.contratodemanda.FiltrarContratoDemandaCondominiosResidenciaisActionForm"
	method="post" styleId="form">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>


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
			<td width="625" valign="top" bgcolor="#003399" class="centercoltext">
			<table height="100%">

				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Filtrar Contratos de Demanda de Condomínio Residencial</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para filtrar contrato(s) de demanda, informe os dados abaixo:</td>
					<td align="right"><input type="checkbox" name="indicadorAtualizar" value="1" /><strong>Atualizar</strong>
				</tr>
				<tr>
					<td width="25%"><strong>Número do contrato:</strong></td>
					<td width="75%"><html:text maxlength="10" property="numeroContrato" size="11" tabindex="1"/></td>
				</tr>
			
				<tr>
					<td width="31%" height="10"><strong>Imóvel:</strong></td>
					<td colspan="3">
						<html:text property="matriculaImovel" maxlength="9" size="11" onkeypress="return isCampoNumerico(event);" tabindex="2"
							onkeyup="validaEnterComMensagem(event, 'exibirFiltrarContratoDemandaCondominiosResidenciaisAction.do', 'matriculaImovel', 'Matrícula do Imóvel');"/>
						<a href="javascript:pesquisarImovel();">
							<img width="23" height="21" src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" title="Pesquisar imóvel"/>
						</a>
						
						<logic:present name="codigoImovelNaoEncontrado" scope="request">
							<html:text property="inscricaoImovel" size="35" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:present> 
						<logic:notPresent name="codigoImovelNaoEncontrado" scope="request">
							<html:text property="inscricaoImovel" size="35"	readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notPresent>
						
						<a id="limparImovel" href="javascript:limparImovel();" > 
							<img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif" style="cursor: hand;" title="Apagar"/> 
						</a>	
					</td>
				</tr>
			
				<tr>
					<td><strong>Período Início de Contrato:</strong></td>
					<td>
						<strong> 
							<html:text maxlength="10" property="dataInicioContrato" size="11" tabindex="3" 
								onkeypress="return isCampoNumerico(event);" 
								onkeyup="mascaraData(this, event); replicarCampo(document.forms[0].dataInicioContrato2, this);"/>
							<a href="javascript:abrirCalendario('FiltrarContratoDemandaCondominiosResidenciaisActionForm', 'dataInicioContrato');">
								<img border="0" width="20" border="0" align="middle" alt="Exibir Calendário" 
									src="<bean:message key="caminho.imagens"/>calendario.gif"/>
							</a>
						</strong> 

						<strong> 
							<html:text maxlength="10" property="dataInicioContrato2" size="11" tabindex="3" 
								onkeypress="return isCampoNumerico(event);" onkeyup="mascaraData(this, event);"/>
							<a href="javascript:abrirCalendario('FiltrarContratoDemandaCondominiosResidenciaisActionForm', 'dataInicioContrato2');">
								<img border="0" width="20" border="0" align="middle" alt="Exibir Calendário" 
									src="<bean:message key="caminho.imagens"/>calendario.gif"/>
							</a>
						</strong> 
						(dd/mm/aaaa)
					</td>
				</tr>
				
				<tr>
					<td><strong>Período Fim de Contrato:</strong></td>
					<td>
						<strong> 
							<html:text maxlength="10" property="dataFimContrato" size="11" tabindex="4" 
								onkeypress="return isCampoNumerico(event);" 
								onkeyup="mascaraData(this, event); replicarCampo(document.forms[0].dataFimContrato2, this);"/>
							<a href="javascript:abrirCalendario('FiltrarContratoDemandaCondominiosResidenciaisActionForm', 'dataFimContrato');">
								<img border="0" width="20" border="0" align="middle" alt="Exibir Calendário" 
									src="<bean:message key="caminho.imagens"/>calendario.gif"/>
							</a>
						</strong> 

						<strong> 
							<html:text maxlength="10" property="dataFimContrato2" size="11" tabindex="4" 
								onkeypress="return isCampoNumerico(event);" onkeyup="mascaraData(this, event);"/>
							<a href="javascript:abrirCalendario('FiltrarContratoDemandaCondominiosResidenciaisActionForm', 'dataFimContrato2');">
								<img border="0" width="20" border="0" align="middle" alt="Exibir Calendário" 
									src="<bean:message key="caminho.imagens"/>calendario.gif"/>
							</a>
						</strong> 
						(dd/mm/aaaa)
					</td>
				</tr>

				<tr>
					<td colspan="2">
						<input name="Button" type="button" class="bottonRightCol" value="Limpar" align="left" tabindex="5"
							onclick="javascript:limparForm();">
						<input name="Button" type="button" class="bottonRightCol" value="Cancelar" align="left" tabindex="6"
							onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>
					<td align="right">
						<input type="button" name="filtrar" class="bottonRightCol" value="Filtrar" id="botaoFiltrar" tabindex="7"/>
					</td>
				</tr>
			</table>
		</table>
		<%@ include file="/jsp/util/rodape.jsp"%> 
	</html:form>
</body>
</html:html>