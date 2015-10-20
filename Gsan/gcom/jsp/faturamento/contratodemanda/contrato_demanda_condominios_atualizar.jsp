<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.Util" isELIgnored="false"%>
<%@ page import="gcom.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false" formName="AtualizarContratoDemandaCondominiosResidenciaisActionForm" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>

<script language="JavaScript">
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    var form = document.forms[0];
    if (tipoConsulta == 'imovel') {
      	form.idImovel.value = codigoRegistro;
	  	form.action = 'exibirAtualizarContratoDemandaCondominiosResidenciaisAction.do?validarImovel=sim';
		form.submit();
    }else if(tipoConsulta == 'cliente'){
		form.idCliente.value = codigoRegistro;
		form.nomeCliente.value = descricaoRegistro;
    }   
}

function  limparForm(nomeCampo){
	if (nomeCampo == 'imovel') {
      	form.idImovel.value = "";
    	form.inscricaoImovel.value = "";
    }else if(nomeCampo == 'cliente'){
		form.idCliente.value = "";
		form.nomeCliente.value = "";
    }
}

function pesquisarImovel() {
	var form = document.forms[0];
	abrirPopup('exibirPesquisarImovelAction.do', 400, 800);
}

function pesquisarCliente(){
	var form = document.forms[0];
	abrirPopup('exibirPesquisarClienteAction.do', 400, 800);	
}

function validarForm(){
	var form = document.forms[0];
	if(validateAtualizarContratoDemandaCondominiosResidenciaisActionForm(form) && validarEncerramento()){
		form.submit();
	}
}

function validarEncerramento(){
	var retorno = true;

	var form = document.forms[0];
	var situacao = form.situacaoContrato;
	var selecionadoIndex = 0;
	for(var i = 0; i < situacao.length; i++){
	    if(situacao[i].checked == true){
		    selecionadoIndex = i;
		    break;  
	    }
	}

	if(form.dataEncerramento.value != '' && form.idMotivoEncerramento.value == -1){
		retorno = false;
		alert('Selecione o  motivo de encerramento de contrato de demanda');
	}else if(form.situacaoContrato[selecionadoIndex].value == 3){
		if(form.dataEncerramento.value == ''){
			retorno = false;
			alert('A situação do contrato foi selecionada como ENCERRADO, informe a data de encerramento.')
		}
	}else if(form.dataEncerramento.value != '' && form.situacaoContrato[selecionadoIndex].value != 3){
		retorno = false;
		alert('Você informou uma data de encerramento, marque a situação do contrato como ENCERRADO.')
	}else if(form.idMotivoEncerramento.value != -1 && form.situacaoContrato[selecionadoIndex].value != 3){
		retorno = false;
		alert('Você informou um motivo de encerramento, marque a situação do contrato como ENCERRADO.');
	}
	return retorno;
}
</script>

</head>

<body leftmargin="5" topmargin="5">

<html:form action="/atualizarContratoDemandaCondominiosResidenciaisAction"
	name="AtualizarContratoDemandaCondominiosResidenciaisActionForm"
	type="gcom.gui.faturamento.contratodemanda.AtualizarContratoDemandaCondominiosResidenciaisActionForm"
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
					<td class="parabg">Atualizar Contrato de Demanda de Condomínio Residencial</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="4">Para atualizar o contrato de demanda, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td width="25%"><strong>Número do contrato:<font color="#FF0000">*</font></strong></td>
					<td width="75%" colspan="2"><html:text maxlength="10" property="numeroContrato" size="11" tabindex="1"/></td>
				</tr>
			
				<tr>
					<td width="31%" height="10"><strong>Imóvel:<font color="#FF0000">*</font></strong></td>
					<td colspan="3">
						<html:text property="idImovel" maxlength="9" size="11" onkeypress="return isCampoNumerico(event);"
							onkeyup="validaEnterComMensagem(event, 'exibirAtualizarContratoDemandaCondominiosResidenciaisAction.do?validarImovel=sim', 'idImovel', 'Matrícula do Imóvel');"/>
						<a href="javascript:pesquisarImovel();">
							<img width="23" height="21" src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" title="Pesquisar imóvel"/>
						</a>
						
						<logic:present name="codigoImovelNaoEncontrado" scope="request">
							<html:text property="inscricaoImovel" size="35" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:present> 
						<logic:notPresent name="codigoImovelNaoEncontrado" scope="request">
							<html:text property="inscricaoImovel" size="35"	readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notPresent>
						
						<a href="javascript:limparForm('imovel');"> 
							<img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif" style="cursor: hand;" title="Apagar"/> 
						</a>	
					</td>
				</tr>
			
				<tr>
					<td><strong>Data de Início do contrato:<font color="#FF0000">*</font></strong></td>
					<td colspan="3">
						<strong> 
							<html:text maxlength="10" property="dataInicio" size="11" tabindex="3" 
								onkeypress="return isCampoNumerico(event);" onkeyup="mascaraData(this, event);"/>
							<a href="javascript:abrirCalendario('AtualizarContratoDemandaCondominiosResidenciaisActionForm', 'dataInicio');">
								<img border="0" width="20" border="0" align="absmiddle" alt="Exibir Calendário" 
									src="<bean:message key="caminho.imagens"/>calendario.gif"/>
							</a>
						</strong> 
						(dd/mm/aaaa)
					</td>
				</tr>
				
				<tr>
					<td><strong>Data de Fim do contrato:<font color="#FF0000">*</font></strong></td>
					<td colspan="3">
						<strong> 
							<html:text maxlength="10" property="dataFim" size="11" tabindex="4" 
								onkeypress="return isCampoNumerico(event);" onkeyup="mascaraData(this, event);"/>
							<a href="javascript:abrirCalendario('AtualizarContratoDemandaCondominiosResidenciaisActionForm', 'dataFim');">
								<img border="0" width="20" border="0" align="absmiddle" alt="Exibir Calendário" 
									src="<bean:message key="caminho.imagens"/>calendario.gif"/>
							</a>
						</strong> 
						(dd/mm/aaaa)
					</td>
				</tr>				 
				
				<tr>
					<td width="30%"><strong>Cliente Solicitante:<font color="#FF0000">*</font></strong></td>
					<td colspan="3">
						<html:text property="idCliente" maxlength="9" size="11" 
							onkeyup="javascript:validaEnterComMensagem(event, 'exibirAtualizarContratoDemandaCondominiosResidenciaisAction.do?pesquisarCliente=sim', 'idCliente', 'Código do Cliente');"
							onkeypress="return isCampoNumerico(event);" />
						<a  href="javascript:pesquisarCliente();" alt="Pesquisar Cliente">
							<img width="23" height="21" src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" title="Pesquisar Cliente"/>
						</a>
					
						<logic:present name="codigoClienteNaoEncontrado" scope="request">
							<html:text property="nomeCliente" size="35" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:present> 
						<logic:notPresent name="codigoClienteNaoEncontrado" scope="request">
							<html:text property="nomeCliente" size="35"	readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notPresent>
					
						<a href="javascript:limparForm('cliente');"> 
							<img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif" style="cursor: hand;" title="Apagar"/> 
						</a>
					</td>
				</tr>
				
				<tr>
					<td><html:radio property="tipoRelacaoCliente" value="1" tabindex="6"></html:radio><strong>Síndico</strong></td>
					<td colspan="3"><html:radio property="tipoRelacaoCliente" value="2" tabindex="7"></html:radio><strong>Representante Legal</strong></td>
				</tr>
				
				<tr>
					<td width="55%">
						<strong>Demanda Mínima Contratada por Economia:<font color="#FF0000">*</font></strong>
					</td>
					<td width="25%">
						<html:text maxlength="4" property="demandaMinima" size="4" tabindex="8" onkeypress="return isCampoNumerico(event);"/>
					</td>
					
					<td width="50%">
						<strong>Percentual de Desconto:</strong>
					</td>
					<td>
						<html:text maxlength="4" property="percentualDesconto" size="4" tabindex="9" readonly="true" 
							style="background-color:#EFEFEF; border:0; color: #000000"/>
					</td>
				</tr>
				
				<tr>
					<td>
						<strong>&nbsp;</strong>
					</td>
				</tr>
				
				<tr>
					<td colspan="4">
						<strong>Dados do Encerramento</strong>
					</td>
				</tr>
				
				<tr>
					<td><strong>Data:<font color="#FF0000"></font></strong></td>
					<td colspan="3">
						<strong> 
							<html:text maxlength="10" property="dataEncerramento" size="11" tabindex="4" 
								onkeypress="return isCampoNumerico(event);" onkeyup="mascaraData(this, event);"/>
							<a href="javascript:abrirCalendario('AtualizarContratoDemandaCondominiosResidenciaisActionForm', 'dataEncerramento');">
								<img border="0" width="20" border="0" align="absmiddle" alt="Exibir Calendário" 
									src="<bean:message key="caminho.imagens"/>calendario.gif"/>
							</a>
						</strong> 
						(dd/mm/aaaa)
					</td>
				</tr>
				
				<tr>
					<td><strong>Motivo:</strong></td>
					<td colspan="3">
						<html:select property="idMotivoEncerramento" style="width: 200px;" tabindex="9">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoMotivo" labelProperty="descricao" property="id"  />
						</html:select>
				   </td>
				</tr>
				
				<tr>
					<td><strong>Observação de Encerramento do Contrato:</strong></td>
					<td colspan="3">
						<html:textarea property="observacaoEncerramento" cols="40" rows="4" tabindex="10" 
							onkeyup="limitTextArea(document.forms[0].observacaoEncerramento, 100, document.getElementById('utilizado'), document.getElementById('limite'));"/>
						<br>
						<strong><span id="utilizado">0</span>/<span id="limite">100</span></strong>
				   </td>
				</tr>
				
				<tr>
					<td><strong>Observação de Suspensão do Contrato:</strong></td>
					<td colspan="3">
						<html:textarea property="observacaoSuspensao" cols="40" rows="4" tabindex="11" 
							onkeyup="limitTextArea(document.forms[0].observacaoSuspensao, 100, document.getElementById('utilizado2'), document.getElementById('limite2'));"/>
						<br>
						<strong><span id="utilizado2">0</span>/<span id="limite2">100</span></strong>
				   </td>
				</tr>
				
				<tr>
					<td><strong>Situação do Contrato:</strong></td>
					<td colspan="3">
						<html:radio property="situacaoContrato" value="1" tabindex="11"></html:radio><strong>Ativo</strong>
						<html:radio property="situacaoContrato" value="2" tabindex="12"></html:radio><strong>Suspenso</strong>
						<html:radio property="situacaoContrato" value="3" tabindex="13"></html:radio><strong>Encerrado<font color="#FF0000">*</font></strong>
					</td>
				</tr>
				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right" colspan="3">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigatórios</div>
					</td>
				</tr>

				<tr>
					<td colspan="2">
						<input name="Button" type="button" class="bottonRightCol" value="Voltar" align="left" tabindex="11"
							onclick="javascript:window.location.href='<html:rewrite page="/exibirManterContratoDemandaCondominiosResidenciaisAction.do?desfazerManter=sim"/>'">
						<input name="Button" type="button" class="bottonRightCol" value="Desfazer" align="left" tabindex="10"
							onclick="window.location.href='<html:rewrite page="/exibirAtualizarContratoDemandaCondominiosResidenciaisAction.do?desfazer=sim"/>'">
						<input name="Button" type="button" class="bottonRightCol" value="Cancelar" align="left" tabindex="11"
							onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>
					<td align="right" colspan="2">
						<input type="button" name="atualizar" class="bottonRightCol" value="Atualizar" id="botaoAtualizar" tabindex="12"
							onclick="javascript:validarForm();"/>
					</td>
				</tr>
			</table>
		</table>
		<%@ include file="/jsp/util/rodape.jsp"%> 
	</html:form>
</body>
</html:html>
