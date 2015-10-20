<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.util.Util" %>
<%@ page import="gcom.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<html:javascript staticJavascript="false"  formName="FiltrarComandoNegativacaoTipoGuiaPagamentoActionForm" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
<script>
<!--
function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado, campo){
	if(objetoRelacionado.readOnly != true){
		if (objeto == null || codigoObjeto == null){
			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		}
		else{
			if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
				alert(msg);
			}
			else{
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
			}
		}
	}
}

function habilitarPesquisaCliente(form) {
	if (form.codigoCliente.readOnly == false) {
		form.tipoPesquisa.value = 'cliente';
		chamarPopup('exibirPesquisarClienteAction.do', 'cliente', null, null, 275, 480, '',form.codigoCliente.value);
	}	
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	var form = document.forms[0];
	if (tipoConsulta == 'cliente') {
    	form.codigoCliente.value = codigoRegistro;
       	form.action = 'exibirFiltrarComandoNegativacaoTipoGuiaPagamentoAction.do';
        form.submit(); 
    }

    if (tipoConsulta == 'setorComercial') {
    	if(form.tipoPesquisa.value == 'setorComercialInicial'){
	  		form.action = 'exibirFiltrarComandoNegativacaoTipoGuiaPagamentoAction.do';
        	form.submit(); 
	    }else{
	 		form.action = 'exibirFiltrarComandoNegativacaoTipoGuiaPagamentoAction.do';
        	form.submit(); 
	    }
    }
}

function limparForm(tipo){
    var form = document.forms[0];
 	if(tipo == 'cliente')
    {
		var ObjCodigoCliente = returnObject(form,"codigoCliente");
		var ObjNomeCliente = returnObject(form,"nomeCliente");
		ObjCodigoCliente.value = "";
		ObjNomeCliente.value = "";
		form.nomeCliente.value = "";
	}
}

function validaEnterCliente(tecla, caminhoActionReload, nomeCampo) {
	var form = document.forms[0];
	validaEnterComMensagem(tecla, caminhoActionReload, nomeCampo, "Código do Cliente");
}


function retiraEspacos(string) {
    var i = 0;
    var final = '';
    while (i < string.length) {
        if (string.charAt(i) == ' ') {
            final += string.substr(0, i);
            string = string.substr(i+1, string.length - (i+1));
            i = 0;
        }
        else {
            i++;
        }
    }
    return final + string;
}



function validaMesAno(mesAno){	
	if(mesAno.value != ""){
		return verificaAnoMesMensagemPersonalizada(mesAno,"Período de Referência do Débito Inválido");
	}else{
		return true;
	}
}
function validarPeriodoVencimento(data){
	if (data.value.length > 0){
		return verificaDataMensagemPersonalizada(data, "Período de Vencimentodo Débito inválido.");				
	}else{
		return true;		
	}
}

function verificaGeracaoComandoDataInicial(){
alert(form.geracaoComandoDataInicial.value);
	var form = document.forms[0];		
	if (retiraEspacos(form.geracaoComandoDataInicial.value) == "" || retiraEspacos(form.geracaoComandoDataInicial.value) == null){
		form.geracaoComandoDataInicialFinal.value = "";
	}
}

function verificaExecucaoComandoDataInicial(){
	var form = document.forms[0];		
	if (retiraEspacos(form.execucaoComandoDataInicial.value) == "" || retiraEspacos(form.execucaoComandoDataInicial.value) == null){
		form.execucaoComandoDataInicialFinal.value = "";
	}
}

function verificaReferenciaDebitoDataInicial(){
	var form = document.forms[0];		
	if (retiraEspacos(form.referenciaDebitoDataInicial.value) == "" || retiraEspacos(form.referenciaDebitoDataInicial.value) == null){
		form.referenciaDebitoDataFinal.value = "";
	}
}

function verificacVencimentoDebitoDataInicial(){
	var form = document.forms[0];		
	if (retiraEspacos(form.vencimentoDebitoDataInicial.value) == "" || retiraEspacos(form.vencimentoDebitoDataInicial.value) == null){
		form.vencimentoDebitoDataFinal.value = "";
	}
}

function filtrar(){
	var form = document.forms[0];
	form.submit();		
}

function verificaCompatibilidadeLocalidadeInicial(){
	var form = document.forms[0];
	if (form.localidadeInicialIncompativel.value == "true"){
		alert('A Localidade do Setor Comercial selecionado não compatível com a Localidade já escolhida');
	}
}

-->
</script>
</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">
<div id="formDiv">
<html:form
	action="filtrarComandoNegativacaoTipoGuiaPagamentoAction"    
	name="FiltrarComandoNegativacaoTipoGuiaPagamentoActionForm"
  	type="gcom.gui.cobranca.spcserasa.FiltrarComandoNegativacaoTipoGuiaPagamentoActionForm"
  	method="post">

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<input type="hidden" name="tipoPesquisa"/>
<input type="hidden" name="tipoLocalidade" />
<input type="hidden" name="tipoSetorComercial" />
<input type="hidden" name="tipoInicialFinal" />
<html:hidden property="localidadeInicialIncompativel"/>

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
		<td width="613" valign="top" class="centercoltext">
			<!--Início Tabela Reference a Páginação da Tela de Processo-->

              <table>
                <tr> 
                  <td></td>
                </tr>
              </table>
              <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr> 
                  <td width="11"><img border="0" src="<bean:message key='caminho.imagens'/>parahead_left.gif"/></td>
                  <td class="parabg">Filtrar Comandos de Negativa&ccedil;&atilde;o - Por Guia de Pagamento de Cliente</td>

                  <td width="11" valign="top"><img border="0" src="<bean:message key='caminho.imagens'/>parahead_right.gif"/></td>
                </tr>
              </table> 
              <!--Fim Tabela Reference a Páginação da Tela de Processo-->
              <p>&nbsp;</p>
              <table width="100%" border="0" dwcopytype="CopyTableRow">
                <tr>
                  <td colspan="3">Para filtrar o(s) comando(s) de negativa&ccedil;&atilde;o,  informe os dados abaixo:</td>

                </tr>
                <tr>
                  <td><strong>Negativador<span class="style5">:</span></strong></td>
                  <td colspan="2"><strong>
                    <logic:present name="colecaoNegativador">  
                   	   <html:select property="idNegativador" tabindex="7">
						   <selected><html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option> </selected>
							<logic:present name="colecaoNegativador">
								<html:options collection="colecaoNegativador" labelProperty="cliente.nome" property="id"/>
							</logic:present>
						</html:select>
                	</logic:present>  
                  <b> </b> <b> </b> </strong></td>
                </tr>
                <tr>
                  <td colspan="3"><hr>                  </td>

                </tr>
                <tr>
                  <td><strong>T&iacute;tulo:</strong></td>
                  <td colspan="2"><p>
                    <html:textarea property="titulo" cols="50" rows="2"></html:textarea>
                  </p>
                  <p>
                  	<html:radio property="tipoPesquisaTitulo" value="1" disabled="false" />
					Iniciando pelo texto
                  	<html:radio property="tipoPesquisaTitulo" value="2" disabled="false" />
					Contendo o texto </p></td>
                </tr>
                <tr> 
                  <td width="242"><strong>Comando Simulado:</strong></td>
                  <td colspan="2"><strong>					
					<html:radio property="comandoSimulado" value="1" disabled="false" />
                    <strong> Sim
					<html:radio property="comandoSimulado" value="2" disabled="false" />
                    <strong>N&atilde;o
                    <html:radio property="comandoSimulado" value="3" disabled="false" />
                    <strong> Todos </strong>
                    </strong></strong></strong></td>
                </tr>
                <tr>
                  <td colspan="3"><hr>                  </td>

                </tr>
                <tr>
                  <td><strong>Cliente:</strong></td>
                  <td colspan="2"><strong><b><span class="style4">
                    <html:text property="codigoCliente" maxlength="9" size="9" onkeyup="return validaEnterCliente(event, 'exibirFiltrarComandoNegativacaoTipoGuiaPagamentoAction.do', 'codigoCliente'); " onkeypress="return isCampoNumerico(event);" />
					<a href="javascript:habilitarPesquisaCliente(document.forms[0]);" alt="Pesquisar Cliente">
					<img width="23" height="21" src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" /></a>
					<logic:present name="corCliente">
						<logic:equal name="corCliente" value="exception">
							<html:text property="nomeCliente" size="38"	readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="corCliente" value="exception">
							<html:text property="nomeCliente" size="38"	readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> 
					<logic:notPresent name="corCliente">
						<logic:empty name="FiltrarComandoNegativacaoTipoGuiaPagamentoActionForm"	property="codigoCliente">
							<html:text property="nomeCliente" size="38" value="" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty name="FiltrarComandoNegativacaoTipoGuiaPagamentoActionForm" property="codigoCliente">
							<html:text property="nomeCliente" size="38"	readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent>
					<a href="javascript:limparForm('cliente');"> 
						<img border="0" src="<bean:message key='caminho.imagens'/>limparcampo.gif" style="cursor: hand;" /> 
					</a>
                </tr>
                <tr>
                  <td colspan="3"><hr>                  </td>
                </tr>
                <tr>
                  <td><strong>Per&iacute;odo de Gera&ccedil;&atilde;o do Comando<span class="style5">:</span></strong></td>

                  <td colspan="2">
                  	<html:text property="geracaoComandoDataInicial" size="10"
						maxlength="10" tabindex="2"
						onkeyup="mascaraData(this, event);"
						onkeypress="return isCampoNumerico(event);" 
						onblur="validarPeriodoVencimento(document.forms[0].geracaoComandoDataInicial);"/>
					<a	href="javascript:abrirCalendarioReplicando('FiltrarComandoNegativacaoTipoGuiaPagamentoActionForm', 'geracaoComandoDataInicial', 'geracaoComandoDataFinal');" >
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário"/></a>
					a <html:text property="geracaoComandoDataFinal" size="10"
						maxlength="10" tabindex="3" 
						onkeyup="mascaraData(this, event);" 
						onblur="validarPeriodoVencimento(document.forms[0].geracaoComandoDataFinal);"
						onkeypress="return isCampoNumerico(event);"/>
						 
					<a	href="javascript:abrirCalendario('FiltrarComandoNegativacaoTipoGuiaPagamentoActionForm', 'geracaoComandoDataFinal')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					dd/mm/aaaa
                  </td>

                </tr>
                <tr>
                  <td><strong>Per&iacute;odo de Execu&ccedil;&atilde;o do Comando:</strong></td>
                  <td colspan="2">
                  	<html:text property="execucaoComandoDataInicial" size="10"
						maxlength="10" tabindex="2"
						onkeyup="mascaraData(this, event);"
						onkeypress="return isCampoNumerico(event);" 
						onblur="validarPeriodoVencimento(document.forms[0].execucaoComandoDataInicial);"/>
					<a	href="javascript:abrirCalendarioReplicando('FiltrarComandoNegativacaoTipoGuiaPagamentoActionForm', 'execucaoComandoDataInicial', 'execucaoComandoDataFinal');" >
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário"/></a>
					a <html:text property="execucaoComandoDataFinal" size="10"
						maxlength="10" tabindex="3" 
						onkeyup="mascaraData(this, event); "
						onkeypress="return isCampoNumerico(event);" 
						onblur="validarPeriodoVencimento(document.forms[0].execucaoComandoDataFinal);"/> 
					<a	href="javascript:abrirCalendario('FiltrarComandoNegativacaoTipoGuiaPagamentoActionForm', 'execucaoComandoDataFinal')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					dd/mm/aaaa
                  </td>
                </tr>
                <tr>
                  <td><strong>Per&iacute;odo de Refer&ecirc;ncia do D&eacute;bito:</strong></td>

                  <td colspan="2">
                  	<html:text property="referenciaDebitoDataInicial" 
					size="8" maxlength="7" 
					onkeyup="mascaraAnoMes(this, event);" 
					onblur="validaMesAno(document.forms[0].referenciaDebitoDataInicial);"
					onkeypress="return isCampoNumerico(event);"/>a 
					<html:text property="referenciaDebitoDataFinal" 
					size="8" maxlength="7" onkeyup="mascaraAnoMes(this, event);" 
					onblur="validaMesAno(document.forms[0].referenciaDebitoDataFinal);"
					onkeypress="return isCampoNumerico(event);"/> mm/aaaa
				  </td>
				</td>
                </tr>

                <tr>
                  <td><strong>Per&iacute;odo de Vencimento do D&eacute;bito:</strong></td>
                  <td colspan="2">
                  	<html:text property="vencimentoDebitoDataInicial" size="10"
						maxlength="10" tabindex="2"
						onkeyup="mascaraData(this, event); "
						onkeypress="return isCampoNumerico(event);" 
						onblur="validarPeriodoVencimento(document.forms[0].vencimentoDebitoDataInicial);"/>
					<a	href="javascript:abrirCalendarioReplicando('FiltrarComandoNegativacaoTipoGuiaPagamentoActionForm', 'vencimentoDebitoDataInicial', 'vencimentoDebitoDataFinal');" >
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário"/></a>
					a <html:text property="vencimentoDebitoDataFinal" size="10"
						maxlength="10" tabindex="3" onkeyup="mascaraData(this, event); "
						onkeypress="return isCampoNumerico(event);" 
						onblur="validarPeriodoVencimento(document.forms[0].vencimentoDebitoDataFinal);"/> 
					<a	href="javascript:abrirCalendario('FiltrarComandoNegativacaoTipoGuiaPagamentoActionForm', 'vencimentoDebitoDataFinal')">
					<img border="0"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					dd/mm/aaaa
                  </td>
                </tr>
                <tr>
                  <td colspan="3"><hr>                  </td>
                </tr>

                <tr>
                  <td><strong>Intervalo de Valor do D&eacute;bito:</strong></td>
                  <td colspan="2">
                  	<html:text property="valorDebitoInicial" 
						size="13" maxlength="13" onkeyup="formataValorMonetario(this, 13); "
						onkeypress="return isCampoNumerico(event);"/>a 
					<html:text property="valorDebitoFinal" 
						size="13" maxlength="13" onkeyup="formataValorMonetario(this, 13); "
						onkeypress="return isCampoNumerico(event);"/>
				  </td>

                </tr>

                <tr>
                  <td colspan="3"><hr>                  </td>
                </tr>

                <tr>
                  <td><strong>Situa&ccedil;&atilde;o do Comando:</strong></td>
                  <td colspan="2"><strong>
					<html:radio property="situacaoComando" value="3" disabled="false" />
                    <strong><strong>Todos</strong>
					<html:radio property="situacaoComando" value="1" disabled="false" />
					<strong><strong>Executados</strong></strong>
					<html:radio property="situacaoComando" value="2" disabled="false" />
                    <strong>N&atilde;o <strong><strong><strong>Executados</strong></strong></strong></strong></strong></strong></td>
                </tr>

                <tr> 
                  <td colspan="3"><hr> </td>
                </tr>
                <tr> 
                  <td height="17" colspan="3"><strong><font color="#FF0000"> </font></strong></td>
                </tr>
                <tr> 
                  <td height="17" colspan="2"><strong><font color="#FF0000"> </font></strong><strong><font color="#FF0000"> 
                    </font></strong><strong><font color="#FF0000"> 
                  	<input name="Submit22"
						class="bottonRightCol" value="Limpar" type="button"
						onclick="window.location.href='/gsan/exibirFiltrarComandoNegativacaoTipoGuiaPagamentoAction.do?menu=sim';">
                    </font></strong></td>
                  <td align="right"><div align="right"><img src="<bean:message key='caminho.imagens'/>voltar.gif" width="15" height="24">
                    <input name="Button32222" type="button" class="bottonRightCol" value="Voltar" onClick="javascript:window.location.href='/gsan/exibirFiltrarComandoNegativacaoAction.do'"/>
                    <input name="Button322" type="button" class="bottonRightCol" value="Filtrar" onClick="return filtrar();" />
                  </div></td>
                  </tr>
              </table>
			
            
          <p>&nbsp;</p></tr>
		<tr>
		  <td colspan="3">
		</td>
	</tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>
</div>
</body>
</html:form>

<%@ include file="/jsp/util/telaespera.jsp"%>

<script>

</script>



</html:html>