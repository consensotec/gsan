<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="gsan.util.Util" isELIgnored="false"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="InserirContratoDemandaCondominiosResidenciaisActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>
<script language="JavaScript">
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    var form = document.forms[0];
    if (tipoConsulta == 'imovel') {
      	form.matriculaImovel.value = codigoRegistro;
	  	form.action = 'exibirInserirContratoDemandaCondominiosResidenciaisAction.do?validarImovel=sim';
		form.submit();
    }else if(tipoConsulta == 'cliente'){
		form.idCliente.value = codigoRegistro;
		form.nomeCliente.value = descricaoRegistro;
    }   
}

function  limparForm(nomeCampo){
	if (nomeCampo == 'imovel') {
      	form.matriculaImovel.value = "";
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

function validarRadioButton(){
	var retorno = true;
	var form = document.forms[0];
	var tipoRelacao = form.tipoRelacaoCliente;
	if(tipoRelacao[0].checked == false && tipoRelacao[1].checked == false){
		alert('Selecione o tipo de relação do cliente SINDICO/RESPONSAVEL LEGAL');
		retorno = false;
	}
	return retorno;
}

function limparDesconto(){
	
	var form = document.forms[0];
	var desconto = document.getElementById("desconto");
	var demanda = form.demandaMinima.value;

	if(demanda != undefined && demanda != ''){
		desconto.innerHTML = '';
	}
		
}

function validarDesconto(){
	var form = document.forms[0];
	var objetoCampo = form.demandaMinima;
	var valorCampo = objetoCampo.value;
	if (!isNaN(valorCampo) && valorCampo.length > 0 && ((valorCampo * 1) > 0)){
		objetoCampo.value = trim(valorCampo);
		
		if (objetoCampo.value.length > 0){
			$.ajax({
			   type: "POST",
			   url: "exibirInserirContratoDemandaCondominiosResidenciaisAction.do?calcularDesconto=sim",
			   data: "demandaMinima="+objetoCampo.value,
			   success: function(msg){
				   var desconto = document.getElementById("desconto");
				   desconto.innerHTML = msg;
			   }
			 });
		}
	}	
}
</script>

<script type="text/javascript">
	$(document).ready(function(){
		$('#botaoInserir').click(function(){
			var form = document.forms[0];
			if(validateInserirContratoDemandaCondominiosResidenciaisActionForm(form) && validarRadioButton()){
				$('#form').submit();
			}
		});

		
	});
</script>

</head>

<body leftmargin="5" topmargin="5">

<html:form action="/inserirContratoDemandaCondominiosResidenciaisAction"
	name="InserirContratoDemandaCondominiosResidenciaisActionForm"
	type="gsan.gui.faturamento.contratodemanda.InserirContratoDemandaCondominiosResidenciaisActionForm"
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
					<td class="parabg">Inserir Contrato de Demanda de Condomínio Residencial</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para inserir o contrato de demanda, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td width="25%"><strong>Número do contrato:<font color="#FF0000">*</font></strong></td>
					<td width="75%"><html:text maxlength="10" property="numeroContrato" size="11" tabindex="1"/></td>
				</tr>
			
				<tr>
					<td width="31%" height="10"><strong>Imóvel:<font color="#FF0000">*</font></strong></td>
					<td colspan="3">
						<html:text property="matriculaImovel" maxlength="9" size="11" onkeypress="return isCampoNumerico(event);" tabindex="2"
							onkeyup="validaEnterComMensagem(event, 'exibirInserirContratoDemandaCondominiosResidenciaisAction.do?validarImovel=sim', 'matriculaImovel', 'Matrícula do Imóvel');"/>
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
					<td>
						<strong> 
							<html:text maxlength="10" property="dataInicioContrato" size="11" tabindex="3" 
								onkeypress="return isCampoNumerico(event);" onkeyup="mascaraData(this, event);"/>
							<a href="javascript:abrirCalendario('InserirContratoDemandaCondominiosResidenciaisActionForm', 'dataInicioContrato');">
								<img border="0" width="20" border="0" align="absmiddle" alt="Exibir Calendário" 
									src="<bean:message key="caminho.imagens"/>calendario.gif"/>
							</a>
						</strong> 
						(dd/mm/aaaa)
					</td>
				</tr>
				
				<tr>
					<td><strong>Data de Fim do contrato:<font color="#FF0000">*</font></strong></td>
					<td>
						<strong> 
							<html:text maxlength="10" property="dataFimContrato" size="11" tabindex="4" 
								onkeypress="return isCampoNumerico(event);" onkeyup="mascaraData(this, event);"/>
							<a href="javascript:abrirCalendario('InserirContratoDemandaCondominiosResidenciaisActionForm', 'dataFimContrato');">
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
							onkeyup="javascript:validaEnterComMensagem(event, 'exibirInserirContratoDemandaCondominiosResidenciaisAction.do?pesquisarCliente=sim', 'idCliente', 'Código do Cliente');"
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
					<td width="25%">
						<strong>Demanda Mínima Contratada por Economia:<font color="#FF0000">*</font></strong>
					</td>
					<td width="75%">
						<html:text maxlength="4" property="demandaMinima" size="4" tabindex="6" onblur="javascript:validarDesconto();"
							onkeypress="limparDesconto(); return isCampoNumerico(event); "/>
						<span style="color:#ff0000" id="desconto"><bean:write property="desconto" name="InserirContratoDemandaCondominiosResidenciaisActionForm"/></span>
					</td>
				</tr>

				<tr>
					<td><html:radio property="tipoRelacaoCliente" value="1" tabindex="7"></html:radio><strong>Síndico</strong></td>
					<td><html:radio property="tipoRelacaoCliente" value="2" tabindex="8"></html:radio><strong>Representante Legal</strong></td>
				</tr>
				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigatórios</div>
					</td>
				</tr>

				<tr>
					<td colspan="2">
						<input name="Button" type="button" class="bottonRightCol" value="Desfazer" align="left" tabindex="10"
							onclick="window.location.href='<html:rewrite page="/exibirInserirContratoDemandaCondominiosResidenciaisAction.do?menu=sim"/>'">
						<input name="Button" type="button" class="bottonRightCol" value="Cancelar" align="left" tabindex="11"
							onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>
					<td align="right">
						<input type="button" name="inserir" class="bottonRightCol" value="Inserir" id="botaoInserir" tabindex="12"/>
					</td>
				</tr>
			</table>
		</table>
		<%@ include file="/jsp/util/rodape.jsp"%> 
	</html:form>
</body>
</html:html>
