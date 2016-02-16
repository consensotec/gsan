<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.atendimentopublico.bean.AcoesParaCorrecaoAnormalidadesEncontradasHelper" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>
<script>
    function validateConsultarDadosOrdemServicoCobrancaSmartphoneActionForm(form) {
        return true;
    }

	
	function selecionouObj(nome){

		var form = document.forms[0];
	    var selecionados = form.elements[nome];
		var retorno = false;
		
		if (selecionados.value != null && selecionados.value != '') {
			
			retorno = true;
			
		} else {
		
			for (i=0; i< selecionados.length; i++) {
				if(selecionados[i].checked){
					retorno = true;
				}
			}
			
		}
		
		return retorno;
	}
	
	function objetoSelecionado(nome){

		var form = document.forms[0];
	    var selecionados = form.elements[nome];
		var retorno = false;
		
		if (selecionados.value != null && selecionados.value != '') {
			
			return selecionados.value;
			
		} else {
			for (i=0; i< selecionados.length; i++) {
				if(selecionados[i].checked){
					return selecionados[i].value;
				}
			}
		}
	}
	
    function facilitador(objeto){
        if (objeto.id == "0" || objeto.id == undefined){ 
            objeto.id = "1";
            
    		for (var i=0;i < document.forms[0].elements.length;i++){
    			var elemento = document.forms[0].elements[i];
    			if (elemento.type == "checkbox" && elemento.name == 'idsAcoes'){
    				elemento.checked = true;
    			}
    		}
        }
        else{
            objeto.id = "0";
            
            for (var i=0;i < document.forms[0].elements.length;i++){
    			var elemento = document.forms[0].elements[i];
    			if (elemento.type == "checkbox" && elemento.name == 'idsAcoes'){
    				
    				if (elemento.checked == true){
    					elemento.checked = false;
    				}
    			}
    		}
        }
    }

    function habilitarQtdParcelaButton(){
		var form = document.forms[0];
		if(form.motivoNaoCobranca.value == "-1"){	
			form.percentualCobranca.value = "-1"
			form.valorParcelas.value = "";
			form.percentualCobranca.disabled = false;
			form.quantidadeParcelas.disabled = false;
			form.valorParcelas.value = "";
			form.buttonCalcular.disabled = false;
		}else{
			form.percentualCobranca.value = "-1"
			form.percentualCobranca.disabled = true;
			form.quantidadeParcelas.disabled = true;
			form.quantidadeParcelas.value = "";
			form.valorParcelas.value = "";
			form.buttonCalcular.disabled = true;
		}
	}

    function calcularValores(){
    	
    	var form = document.forms[0];
		
		if (form.quantidadeParcelas.value == ''){
		     alert("É necessário informar a quatidade de parcelas.");
		} else
		if (form.quantidadeParcelas.value == 0){
		     alert("Quantidade de Parcelas informada não pode ser igual a 0(zero).");
		} else {
		   	if (validaDebito()){
		   		form.action='exibirConsultarDadosOrdemServicoCobrancaSmartphoneAction.do?ordemServico='+ 
		   		form.ordemServico.value + '&matricula=' + form.matricula.value + '&idArquivo=' + form.idArquivo.value + '&calculaValores=S';
			   	form.submit();
			}
		}
	}

    function validateConsultarDadosOrdemServicoCobrancaSmartphoneActionForm(form) {                                                                   
         
		var form = document.forms[0];

		if (form.idTipoDebito.value == '' || form.icExibirDebitos.value == '' ){
			retorno = true;	
		}
		else{

			if (form.quantidadeParcelas.value == ''){
			     alert("É necessário informar a quatidade de parcelas.");
			} 
			else if (form.quantidadeParcelas.value == 0){
			     alert("Quantidade de Parcelas informada não pode ser igual a 0(zero).");
			} else {
			   	if (validaDebito()){
			   		retorno = true;
				}
			}
			returno = true;
		}

		return retorno;		

   }
	
</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form
    action="/consultarDadosOrdemServicoCobrancaSmartphoneWizardAction"
    method="post"
>

<html:hidden property="idArquivo"/>
<html:hidden property="idTipoDebito"/>
<html:hidden property="icExibirDebitos"/>

<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=1"/>

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="165" valign="top" class="leftcoltext">
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
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg" colspan="2">Consultar Dados da OS de Servico Smartphone</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
      <table width="100%" border="0" dwcopytype="CopyTableRow">
        <tr>
          <td colspan="4">
            Dados das Ordens de Serviço para Smartphone do Arquivo Texto:
          </td>
        </tr>
        
		<tr>
			<td width="25%">
				<strong>Empresa:</strong>
			</td>
			<td>
				<html:hidden property="empresa"/>
				<html:text property="descricaoEmpresa" size="28" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			</td>
		</tr>
		<tr>
			<td width="25%">
				<strong>Tipo da Ordem de Serviço:</strong>
			</td>
			<td>
				<html:hidden property="idTipoOrdemServico"/>
				<html:text property="descricaoTipoOrdemServico" size="28" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
			</td>
		</tr>       
		<tr>
            <td><strong>Número da OS:</strong></td>
            <td>
               	<strong>
	     			<html:text maxlength="20" size="20" property="ordemServico" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
    			</strong>
    		</td>
    	</tr>
    	<tr>
    		<td><strong>Matrícula do Imóvel:</strong></td>        
            <td>
               	<strong>
	     			<html:text maxlength="20" size="20" property="matricula" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
    			</strong>
    		</td>    		
    	</tr>
    	<tr>
    		<td><strong>Tipo do Serviço:</strong></td>        
            <td>
               	<strong>
               		<html:hidden property="idServicoTipo"/>
	     			<html:text maxlength="66" size="66" property="descricaoServicoTipo" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
    			</strong>
    		</td>    		
    	</tr>
    	
    	<!-- CORTE -->
    	<logic:present name="execucaoOSCorte" >
			<tr>
				<td colspan="2">
					<table width="100%" border="0" align="center" cellpadding="0"cellspacing="3">
						<tr>
							<td colspan="9" width="100%" height="1px" bgcolor="#000000"></td>
						</tr>
					</table>
				</td>
			</tr>
    	   	<tr>
	    		<td><strong>Motivo do Corte:</strong></td>        
	            <td>
	               	<strong>
		     			<html:text maxlength="66" size="66" property="descricaoMotivoCorte" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
	    			</strong>
	    		</td>    		
    		</tr>
    	   	<tr>
	    		<td><strong>Tipo do Corte:</strong></td>        
	            <td>
	               	<strong> 
		     			<html:text maxlength="66" size="66" property="descricaoTipoCorte" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
	    			</strong>
	    		</td>    		
    		</tr>
    		
    		<logic:notEmpty name="ConsultarDadosOrdemServicoCobrancaSmartphoneActionForm" property="tipoPavimento" >
	    		<tr>
		    		<td><strong>Tipo de Pavimento:</strong></td>        
		            <td>
		               	<strong>
			     			<html:text maxlength="66" size="66" property="tipoPavimento" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
		    			</strong>
		    		</td>    		
	    		</tr>
    		</logic:notEmpty>
    		<logic:notEmpty name="ConsultarDadosOrdemServicoCobrancaSmartphoneActionForm" property="comCalcada" >
	    		<tr>
		    		<td><strong>Com Calçada? </strong></td>        
		            <td>
		               	<strong>
			     			<html:text maxlength="10" size="10" property="comCalcada" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
		    			</strong>
		    		</td>    		
	    		</tr>
    		</logic:notEmpty>
    	   	<tr>
	    		<td><strong>Leitura do Corte:</strong></td>        
	            <td>
	               	<strong>
		     			<html:text maxlength="10" size="10" property="leituraCorte" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
	    			</strong>
	    		</td>    		
    		</tr>
    		<tr>
	    		<td><strong>Número do Selo:</strong></td>        
	            <td>
	               	<strong>
		     			<html:text maxlength="10" size="10" property="numeroSelo" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
	    			</strong>
	    		</td>    		
    		</tr>
    	</logic:present>

		<!-- FISCALIZAÇÃO -->		
    	<logic:present name="execucaoOSFiscalizacao" >
			<tr>
				<td colspan="2">
					<table width="100%" border="0" align="center" cellpadding="0"cellspacing="3">
						<tr>
							<td colspan="9" width="100%" height="1px" bgcolor="#000000"></td>
						</tr>
					</table>
				</td>
			</tr>
    	   	<tr>
	    		<td><strong>Documento Entregue:</strong></td>        
	            <td>
	               	<strong>
		     			<html:text maxlength="66" size="66" property="descricaoDocumentoEntregue" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
	    			</strong>
	    		</td>    		
    		</tr>
    		<tr bgcolor="#cbe5fe" >
    			<td colspan="1"><strong>Situações Encontradas:</strong></td>    
    			<td colspan="2">
					<div style="height:60px;overflow:auto">
						<table width="95%" >
							<tr>								
								<logic:present name="colecaoSituacoesEncontradas">
									<%int cont = 0;%>
									<logic:iterate name="colecaoSituacoesEncontradas"
										id="situacaoEncontrada"
										type="gcom.mobile.execucaoordemservico.ExecucaoOSSituacoesEncontradas">
										<tr>
											<td style="background-color:#EFEFEF; border:0; color: #000000">
												${situacaoEncontrada.fiscalizacaoSituacao.descricaoFiscalizacaoSituacao}
											</td>
										</tr>
									</logic:iterate>
								</logic:present>
							</tr>
						</table>
					</div>
				</td>
			</tr>
    	</logic:present>
    	
    	<!-- SUPRESSÃO -->
    	<logic:present name="execucaoOSSupressao" >
			<tr>
				<td colspan="2">
					<table width="100%" border="0" align="center" cellpadding="0"cellspacing="3">
						<tr>
							<td colspan="9" width="100%" height="1px" bgcolor="#000000"></td>
						</tr>
					</table>
				</td>
			</tr>
    	   	<tr>
	    		<td><strong>Motivo do Supressão:</strong></td>        
	            <td>
	               	<strong>
		     			<html:text maxlength="60" size="60" property="descricaoMotivoSupressao" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
	    			</strong>
	    		</td>    		
    		</tr>
    	   	<tr>
	    		<td><strong>Tipo do Supressão:</strong></td>        
	            <td>
	               	<strong>
		     			<html:text maxlength="60" size="60" property="descricaoTipoSupressao" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
	    			</strong>
	    		</td>    		
    		</tr>
    	   	<tr>
	    		<td><strong>Leitura da Supressão:</strong></td>        
	            <td>
	               	<strong>
		     			<html:text maxlength="40" size="40" property="leituraSupressao" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
	    			</strong>
	    		</td>    		
    		</tr>
		</logic:present>
		
    	<!-- SUBSTITUIÇÃO -->
    	<logic:present name="execucaoOSSubstituicao" >
			<tr>
				<td colspan="2">
					<table width="100%" border="0" align="center" cellpadding="0"cellspacing="3">
						<tr>
							<td colspan="9" width="100%" height="1px" bgcolor="#000000"></td>
						</tr>
					</table>
				</td>
			</tr>
    		<tr>
	    		<td colspan="2" align="center" width="100%" bgcolor="#90c7fc"><strong>Dados do Hidrômetro Atual:</strong></td>
            </tr>
    	   	<tr>
	    		<td><strong>Número da Leitura:</strong></td>        
	            <td>
	               	<strong>
		     			<html:text maxlength="40" size="40" property="numeroLeitura" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
	    			</strong>
	    		</td>    		
    		</tr>
    	   	<tr>
	    		<td><strong>Situação do Hidrômetro:</strong></td>        
	            <td>
	               	<strong>
		     			<html:text maxlength="40" size="40" property="situacaoHidrometro" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
	    			</strong>
	    		</td>    		
    		</tr>
    	   	<tr>
	    		<td><strong>Local de Armazenagem:</strong></td>        
	            <td>
	               	<strong>
		     			<html:text maxlength="40" size="40" property="localArmazenagem" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
	    			</strong>
	    		</td>    		
    		</tr>
    		<tr>
	    		<td colspan="2" align="center" width="100%" bgcolor="#90c7fc"><strong>Dados do Hidrômetro Novo:</strong></td>
            </tr>
    	   	<tr>
	    		<td><strong>Tipo de Hidrômetro:</strong></td>        
	            <td>
	               	<strong>
		     			<html:text maxlength="40" size="40" property="tipoHidrometro" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
	    			</strong>
	    		</td>    		
    		</tr>
    		 <logic:notEmpty name="ConsultarDadosOrdemServicoCobrancaSmartphoneActionForm" property="numeroHidrometro">
	    	   	<tr>
		    		<td><strong>Número do Hidrômetro:</strong></td>        
		            <td>
		               	<strong>
			     			<html:text maxlength="40" size="40" property="numeroHidrometro" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
		    			</strong>
		    		</td>    		
	    		</tr>
    		</logic:notEmpty>
    		 <logic:notEmpty name="ConsultarDadosOrdemServicoCobrancaSmartphoneActionForm" property="numeroTombamento">
	    	   	<tr>
		    		<td><strong>Número do Tombamento:</strong></td>        
		            <td>
		               	<strong>
			     			<html:text maxlength="40" size="40" property="numeroTombamento" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
		    			</strong>
		    		</td>    		
	    		</tr>
    		</logic:notEmpty>
   			<tr>
	    		<td><strong>Leitura Instalação:</strong></td>        
	            <td>
	               	<strong>
		     			<html:text maxlength="40" size="40" property="numeroLeituraInstalacao" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
	    			</strong>
	    		</td>    		
    		</tr>
   			<tr>
	    		<td><strong>Número do Selo:</strong></td>        
	            <td>
	               	<strong>
		     			<html:text maxlength="40" size="40" property="numeroSelo" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
	    			</strong>
	    		</td>    		
    		</tr>
    	   	<tr>
	    		<td><strong>Cavalete:</strong></td>        
	            <td>
	               	<strong>
	     			    <html:radio property="cavalete" value="1" disabled="true" /> COM 
						<html:radio property="cavalete" value="2" disabled="true" /> SEM
	    			</strong>
	    		</td>    		
    		</tr>
		</logic:present>
		
		<!-- REMOÇÃO -->
		<logic:present name="execucaoOSRemocao" >
			<tr>
				<td colspan="2">
					<table width="100%" border="0" align="center" cellpadding="0"cellspacing="3">
						<tr>
							<td colspan="9" width="100%" height="1px" bgcolor="#000000"></td>
						</tr>
					</table>
				</td>
			</tr>
    	   	<tr>
	    		<td><strong>Local de Instalação do Hidrômetro:</strong></td>        
	            <td>
	               	<strong>
		     			<html:text maxlength="40" size="40" property="localInstalacaoHidrometro" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
	    			</strong>
	    		</td>    		
    		</tr>
    	   	<tr>
	    		<td><strong>Proteção do Hidrômetro:</strong></td>        
	            <td>
	               	<strong>
		     			<html:text maxlength="40" size="40" property="protecaoHidrometro" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
	    			</strong>
	    		</td>    		
    		</tr>
    	   	<tr>
	    		<td><strong>Cavalete:</strong></td>        
	            <td>
	               	<strong>
	     			    <html:radio property="cavalete" value="1" disabled="true" /> COM 
						<html:radio property="cavalete" value="2" disabled="true" /> SEM
	    			</strong>
	    		</td>    		
    		</tr>
		</logic:present>
		
		<!-- CAIXA PROTEÇÃO -->
		<logic:present name="execucaoOSCaixaProtecao" >
			<tr>
				<td colspan="2">
					<table width="100%" border="0" align="center" cellpadding="0"cellspacing="3">
						<tr>
							<td colspan="9" width="100%" height="1px" bgcolor="#000000"></td>
						</tr>
					</table>
				</td>
			</tr>
    	   	<tr>
	    		<td><strong>Local de Instalação do Hidrômetro:</strong></td>        
	            <td>
	               	<strong>
		     			<html:text maxlength="40" size="40" property="localInstalacaoHidrometro" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
	    			</strong>
	    		</td>    		
    		</tr>
    	   	<tr>
	    		<td><strong>Proteção do Hidrômetro:</strong></td>        
	            <td>
	               	<strong>
		     			<html:text maxlength="40" size="40" property="protecaoHidrometro" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
	    			</strong>
	    		</td>    		
    		</tr>
    	   	<tr>
	    		<td><strong>Troca de Proteção:</strong></td>        
	            <td>
	               	<strong>
	     			    <html:radio property="trocaProtecao" value="1" disabled="true" /> SIM 
						<html:radio property="trocaProtecao" value="2" disabled="true" /> NÃO
	    			</strong>
	    		</td>    		
    		</tr>
    	   	<tr>
	    		<td><strong>Troca de Registro:</strong></td>        
	            <td>
	               	<strong>
	     			    <html:radio property="trocaRegistro" value="1" disabled="true" /> SIM 
						<html:radio property="trocaRegistro" value="2" disabled="true" /> NÃO
	    			</strong>
	    		</td>    		
    		</tr>
		</logic:present>
    	
    	
	    <logic:notEmpty name="ConsultarDadosOrdemServicoCobrancaSmartphoneActionForm" property="idTipoDebito">
	    	<logic:notEmpty name="ConsultarDadosOrdemServicoCobrancaSmartphoneActionForm" property="icExibirDebitos">
	   			<tr>
					<td width="42%" height="10"><strong>Tipo de Débito:</strong></td>
					<td>
						<html:text property="idTipoDebito" readonly="true" style="background-color:#EFEFEF; border:0;" size="5" maxlength="5" />
						<html:text property="descricaoTipoDebito" readonly="true" style="background-color:#EFEFEF; border:0;" size="51" maxlength="51" />
					</td>
				</tr>
				<!-- Colocado por Raphael Rossiter em 19/04/2007 -->
				<logic:notEqual name="ConsultarDadosOrdemServicoCobrancaSmartphoneActionForm" property="alteracaoValor" value="OK">
					<tr>
						<td><strong>Valor do Débito:</strong></td>
						<td>
							<html:text property="valorDebito" readonly="true" style="background-color:#EFEFEF; border:0; text-align:right;" size="10" />
						</td>
					</tr>
				</logic:notEqual>
				
				<logic:equal name="ConsultarDadosOrdemServicoCobrancaSmartphoneActionForm" property="alteracaoValor" value="OK">
					<tr>
						<td><strong>Valor do Débito:</strong></td>
						<td>
							<html:text property="valorDebito" style="text-align:right;" size="10" />
						</td>
					</tr>
				</logic:equal>
											
				<logic:present name="permissaoMotivoNaoCobranca">
					<tr>
						<td><strong>Motivo da Não Cobrança:</strong></td>
						<td>
							<html:select property="motivoNaoCobranca" style="width: 230px;" onchange="habilitarQtdParcelaButton();">
								<html:option value="-1">&nbsp;</html:option>
								<logic:present name="colecaoMotivoNaoCobranca">
									<html:options collection="colecaoMotivoNaoCobranca" labelProperty="descricao" property="id" />
								</logic:present>
							</html:select> 
						</td>
					</tr>
					<tr>
						<td><strong>Percentual de Cobrança:</strong></td>
						<td>
							<html:select property="percentualCobranca" 
								style="width: 80px;">
								<html:option value="-1">&nbsp;</html:option>
								<html:option value="100">100%</html:option>
								<html:option value="70">70%</html:option>
								<html:option value="50">50%</html:option>
							</html:select>
						</td>
					</tr>
					<tr>
						<td>
							<strong>Quantidade de Parcelas:</strong>
						</td>
						<td>
							<html:text property="quantidadeParcelas" size="5" maxlength="5" onkeypress="return isCampoNumerico(event);"/>
						</td>
					</tr>
					
					<tr>
						<td><strong>Valor da Parcela:</strong></td>
						<td colspan="2">
							<html:text property="valorParcelas" readonly="true" style="background-color:#EFEFEF; border:0; text-align:right;" size="10" />
							<input type="button" class="bottonRightCol" value="Calcular" name="buttonCalcular" tabindex="10" onclick="calcularValores();" style="width: 80px">
						</td>
					</tr>
				</logic:present>
											
				<logic:notPresent name="permissaoMotivoNaoCobranca">
					<tr>
						<td>
							<strong>Percentual de Cobrança: <font color="#FF0000">*</font></strong>
						</td>
						<td>
							<html:select property="percentualCobranca" style="width: 80px;" >
								<html:option value="100">100%</html:option>
							</html:select> 
						</td>
					</tr>
					<tr>
						<td>
							<strong>Quantidade de Parcelas:<font color="#FF0000">*</font></strong>
						</td>
						<td> 
							<html:text property="quantidadeParcelas" size="5" maxlength="5" onkeypress="return isCampoNumerico(event);"/>
						</td>
					</tr>
					<tr>
						<td>
							<strong>Valor da Parcela:</strong>
						</td>
						<td>
							<html:text property="valorParcelas" readonly="true" style="background-color:#EFEFEF; border:0;text-align: right;" size="10" maxlength="10" />
								<input type="button" class="bottonRightCol" value="Calcular" name="buttonCalcular" tabindex="10" onclick="calcularValores();" style="width: 80px">
						</td>
					</tr>
				</logic:notPresent>
			</logic:notEmpty>
   		</logic:notEmpty>
    	
		<tr>
			<td colspan="2">
				<table width="100%" border="0" align="center" cellpadding="0"cellspacing="3">
					<tr>
						<td colspan="9" width="100%" height="1px" bgcolor="#000000"></td>
					</tr>
				</table>
			</td>
		</tr>
    	<tr>
    		<td><strong>Motivo do Encerramento:</strong></td>        
            <td>
               	<strong>
               		<html:hidden property="idMotivoEncerramento"/>
	     			<html:text maxlength="40" size="40" property="descricaoMotivoEncerramento" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
    			</strong>
    		</td>    		
    	</tr>
    	<tr>
    		<td><strong>Data da Execução:</strong></td>        
            <td>
               	<strong>
	     			<html:text maxlength="40" size="40" property="dataExecucao" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
    			</strong>
    		</td>    		
    	</tr>
    	<tr>
    		<td><strong>Hora da Execução:</strong></td>        
            <td>
               	<strong>
	     			<html:text maxlength="40" size="40" property="horaExecucao" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
    			</strong>
    		</td>    		
    	</tr>
    	<tr>
    		<td><strong>Parecer:</strong></td>        
            <td>
               	<strong>
	     			<html:textarea property="parecer" cols="50" rows="4" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
    			</strong>
    		</td>    		
    	</tr>
        <tr>
          <td colspan="2"><div align="right"><jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=1"/></div></td>
        </tr>
      </table>
		<table width="100%" border="0">
			<tr>
				<td colspan="3" height="7"> </td>
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
