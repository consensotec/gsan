<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>

<%@ page import="gsan.cobranca.NegativacaoCriterioCpfTipo"%>
<%@ page import="gsan.util.ConstantesSistema,java.util.Collection"%>

<!doctype HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script src="<bean:message key="caminho.js"/>util.js" ></script>
<script src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script src="<bean:message key="caminho.js"/>Calendario.js" ></script>
<script src="<bean:message key="caminho.js"/>jquery/jquery.js"></script>
<html:javascript staticJavascript="false" formName="AtualizarComandoNegativacaoPorCriterioActionForm" dynamicJavascript="false" />

<script language="JavaScript">
	function validateAtualizarComandoNegativacaoPorCriterioActionForm(form) {
		if(form.simular[1].checked){
			if(form.executarSimulacao[0].checked && (!form.descricaoComandoSimulado.value)){
				alert("Pesquisar Comando de Simulação");
				return false;
			}
		}

		if(!form.indicadorCpfCnpjValido)
			form.indicadorCpfCnpjValido.value = '1';

		return validateRequired(form)
	} 

	function required () { 
	   this.aa = new Array("titulo", "Informe Título.", new Function ("varName", " return this[varName];"));
	   this.ab = new Array("solicitacao", "Descricão da Solicitação.", new Function ("varName", " return this[varName];"));
	   this.ac = new Array("usuario", "Informe Usuário Responsável.", new Function ("varName", " return this[varName];"));
	} 

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    	var form = document.forms[0];
    	
	    if (tipoConsulta == 'usuario') {
	      form.usuario.value = codigoRegistro;
	      form.nomeUsuario.value = descricaoRegistro;
	      form.nomeUsuario.style.color = "#000000";
	    }
	    
    	if (tipoConsulta == 'comandoNegativacao') {
      		form.action = 'atualizarComandoNegativacaoPorGuiaPagamentoWizardAction.do?action=exibirAtualizarComandoNegativacaoPorGuiaPagamentoDadosGeraisAction&idComandoNegativacao=' + codigoRegistro;
      		form.submit();
    	}  	    
  	}
    var comando = 0;

	function setComandoNegativacao(tipo) {
		comando = tipo;
	}

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    	var form = document.forms[0];
    	
	    if (tipoConsulta == 'usuario') {
	      form.usuario.value = codigoRegistro;
	      form.nomeUsuario.value = descricaoRegistro;
	      form.nomeUsuario.style.color = "#000000";
	    } else if (tipoConsulta == 'comandoNegativacao') {
	      if (comando == 1) {      		
      		form.submit();
	      } else if (comando == 2) {
			form.action = 'atualizarComandoNegativacaoPorGuiaPagamentoWizardAction.do?action=exibirAtualizarComandoNegativacaoPorGuiaPagamentoDadosGeraisAction&idComandoNegativacaoSimulado=' + codigoRegistro;
      		form.submit();		      
	      }
	      comando = 0;
	    }    
  	}

	function limparUsuario() {
		var form = document.forms[0];

		form.usuario.value = '';
		form.nomeUsuario.value = '';

		form.usuario.focus();
	}

	function desabilitarBotaoPesquisar() {
		var form = document.forms[0];

		if(!form.executarSimulacao[0]) return;

		if (form.executarSimulacao[0].checked) {
			form.ButaoPesquisar.disabled = false;
		} else {
			form.idComandoSimulado.value = "";
			form.descricaoComandoSimulado.value = "";
			form.ButaoPesquisar.disabled = true;
		}
	}
</script>

</head>

<body leftmargin="5" topmargin="5">

<div id="formDiv">
<html:form action="/atualizarComandoNegativacaPorCriterioWizardAction" method="post">

<html:hidden property="idComandoSimulado"/>

<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=1"/>

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

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

	<td width="625" valign="top" class="centercoltext">

        <table height="100%">
        <tr>
          <td></td>
        </tr>
      	</table>

      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Atualizar Comando de Negativação Por Guia de Pagamento</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>

      <table width="100%" border="0">
      <tr>
      	<td colspan="3">Para determinar a negativação a ser comandada, informe os dados gerais abaixo:</td>
      </tr>
      <tr>
      	<td width="35%"><strong>Negativador:</strong></td>
       	<td colspan="2">
			<html:text property="nomeNegativador" size="50" maxlength="60" tabindex="4" readonly="true"
			style="background-color:#EFEFEF; border:0; color: #000000"/>
		</td>
	  </tr>
	  <tr>
         <td colspan="3"><hr></td>
      </tr>
      <tr>
      	<td height="30" width="35%"><strong>Título:<font color="#FF0000">*</font></strong></td>
        <td colspan="2">
			<html:textarea property="titulo" cols="40" rows="4" onkeyup="limitTextArea(document.forms[0].titulo, 100, document.getElementById('utilizado'), document.getElementById('limite'));"/><br>
			<strong><span id="utilizado">0</span>/<span id="limite">100</span></strong>
		</td>
      </tr>
      <tr>
      	<td height="30" width="35%"><strong>Descrição da Solicitação:<font color="#FF0000">*</font></strong></td>
        <td colspan="2">
			<html:textarea property="solicitacao" cols="40" rows="4"
				onkeyup="limitTextArea(document.forms[0].solicitacao, 500, document.getElementById('utilizado-descricao'), document.getElementById('limite-descricao'));"/>
			<br>
			<strong><span id="utilizado-descricao">0</span>/<span id="limite-descricao">500</span></strong>
		</td>
      </tr>      
      
      <tr>
      	<td height="30" width="35%"><strong>Simular a Negativação:<font color="#FF0000">*</font></strong></td>
        <td colspan="2">
			<html:radio property="simular" value="1" tabindex="1" styleId="simularSim"
				onclick="redirecionarSubmit('atualizarComandoNegativacaoPorGuiaPagamentoWizardAction.do?action=exibirAtualizarComandoNegativacaoPorGuiaPagamentoDadosGeraisAction&determinarData=ok');"
			/><label for="simularSim"><strong>Sim</strong></label>
			<html:radio property="simular" value="2" tabindex="2" styleId="simularNao"
				onclick="redirecionarSubmit('atualizarComandoNegativacaoPorGuiaPagamentoWizardAction.do?action=exibirAtualizarComandoNegativacaoPorGuiaPagamentoDadosGeraisAction&determinarData=ok');"
			/><label for="simularNao"><strong>Não</strong></label>
		</td>
      </tr>
      <logic:notPresent name="habilitarExecutarSimulacao">
      	<html:hidden property="executarSimulacao"/>
		<html:hidden property="descricaoComandoSimulado"/>
      </logic:notPresent>
      <logic:present name="habilitarExecutarSimulacao">
            <tr>
      	<td height="30" width="35%"><strong>Executar o comando a partir de uma simulação:<font color="#FF0000">*</font></strong></td>
        <td colspan="2">
			<html:radio property="executarSimulacao" value="1" tabindex="1" styleId="executarSimulacaoSim"
				onclick="javascript:desabilitarBotaoPesquisar();"
			/><label for="executarSimulacaoSim"><strong>Sim</strong></label>
			<html:radio property="executarSimulacao" value="2" tabindex="2" styleId="executarSimulacaoNao"
				 onclick="javascript:desabilitarBotaoPesquisar();"
			/><label for="executarSimulacaoNao"><strong>Não</strong></label>
		</td>
      </tr>  
      <tr>
      	<td height="30" width="35%"><strong>Comando de Simulação:</strong></td>
        <td>
			<html:text property="descricaoComandoSimulado" size="46" maxlength="100" tabindex="4" readonly="true"
			    style="background-color:#EFEFEF; border:0; color: #000000"/>
		</td>
		<td>
			<div align="right"><input name="ButaoPesquisar" type="button"
						class="bottonRightCol" value="Pesquisar" onclick="setComandoNegativacao(2); abrirPopup('exibirPesquisarComandoNegativacaoAction.do?menu=ok', 400, 750);" /></div>
		</td>
      </tr> 
      </logic:present>       
      <tr>
      	<td height="30" width="35%"><strong>Data Prevista para execução:</strong></td>
        <td colspan="2">
			<html:text property="dataPrevista" size="11" maxlength="10" tabindex="12" onkeyup="mascaraData(this, event)" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
		</td>
      </tr>
      <tr>
		<td width="35%"><strong>Usuário Responsável:<font color="#FF0000">*</font></strong></td>
		<td colspan="2">
			<html:text property="usuario" size="9" maxlength="9"
				onkeypress="validaEnter(event, 'atualizarComandoNegativacaoPorGuiaPagamentoWizardAction.do?action=exibirAtualizarComandoNegativacaoPorGuiaPagamentoDadosGeraisAction&pesquisarUsuario=ok', 'usuario'); return isCampoNumerico(event);" />
			<a href="javascript:abrirPopup('exibirUsuarioPesquisar.do?limpaForm=OK', 300, 350);">
			   <img src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0"
			width="23" height="21" style="cursor:hand;"	alt="Pesquisar"></a> 
			<logic:present name="corUsuario">
				<logic:equal name="corUsuario" value="exception">
					<html:text property="nomeUsuario" size="35"
						readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
				</logic:equal>
				<logic:notEqual name="corUsuario" value="exception">
					<html:text property="nomeUsuario" size="35"
						readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
				</logic:notEqual>
			</logic:present> 
			<logic:notPresent name="corUsuario">
				<logic:empty name="AtualizarComandoNegativacaoPorCriterioActionForm" property="nomeUsuario">
					<html:text property="nomeUsuario" size="35"
						value="" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
				</logic:empty>
				<logic:notEmpty name="AtualizarComandoNegativacaoPorCriterioActionForm" property="nomeUsuario">
					<html:text property="nomeUsuario" size="35"
						readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
				</logic:notEmpty>
			</logic:notPresent> 

			<a href="javascript:limparUsuario();"> 
			<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /></a>
		</td>
	  </tr>
      <tr>
      	<td height="30" width="35%"><strong>Quantidade Máxima de inclusões:</strong></td>
        <td colspan="2">
		  <html:text property="qtdMaximaInclusao" size="8" maxlength="5" tabindex="3"/>
		</td>
      </tr>	  	

      <tr>
      	<td><strong>Só Considerar CPF/CNPJ Validado:<font color="#FF0000">*</font></strong></td>
        <td colspan="2">
			<html:radio property="indicadorCpfCnpjValido" value="1" tabindex="1" styleId="indicadorCpfCnpjValidoSim"
			    disabled="${!sessionScope.alterarSoCPFCNPJValidos}" /><label for="indicadorCpfCnpjValidoSim"><strong>Sim</strong></label>
			<html:radio property="indicadorCpfCnpjValido" value="2" tabindex="2" styleId="indicadorCpfCnpjValidoNao"
			    disabled="${!sessionScope.alterarSoCPFCNPJValidos}" /><label for="indicadorCpfCnpjValidoNao"><strong>Não</strong></label>
		</td>
      </tr>

      <tr>
        <td colspan="3">
			<div align="right">
				<jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=1" />
			</div>
		</td>
      </tr>
      </table>
      <p>&nbsp;</p>
	</td>
  </tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</div>

<%@ include file="/jsp/util/telaespera.jsp"%>

<script>
$(function() {
	setarFoco('${requestScope.nomeCampo}');
	desabilitarBotaoPesquisar();
	limitTextArea(document.forms[0].titulo, 100, document.getElementById('utilizado'), document.getElementById('limite'));
	limitTextArea(document.forms[0].solicitacao, 500, document.getElementById('utilizado-descricao'), document.getElementById('limite-descricao'));

	document.getElementById('botaoConcluir').onclick = function() {
		botaoAvancarTelaEspera('/gsan/atualizarComandoNegativacaoPorGuiaPagamentoWizardAction.do?concluir=true&action=atualizarComandoNegativacaoPorGuiaPagamentoDadosGeraisAction');
	}
});
</script>

</body>
</html:html>

