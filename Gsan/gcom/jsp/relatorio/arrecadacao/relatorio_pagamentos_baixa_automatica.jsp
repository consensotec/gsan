<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@page import="gcom.util.ConstantesSistema"%>
<%@ include file="/jsp/util/telaespera.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<script>
	function validarFormSubmit(){		
    	var form = document.GerarRelatorioPagamentoBaixaAutomaticaActionForm;
    	if(validateGerarRelatorioPagamentoBaixaAutomaticaActionForm(form) && validaTodosPeriodos()){
	    	form.action = 'gerarRelatorioPagamentoBaixaAutomaticaAction.do';
    		form.submit();				
    	}
	}	
	
	function validaTodosPeriodos() {
		var form = document.forms[0];
		
		if (validaPeriodoEncerramento(form)) {
    		if (comparaData(form.dataInicial.value, '>', form.dataFinal.value)){
				alert('Data Final do Período de Encerramento é anterior à Data Inicial');
				return false;
			}
		} else {
			return false;
		} 

		return true;
    }	
	
	function validaPeriodoEncerramento(form) {
    	var form = document.forms[0];
    	
    	var periodoEncerramentoInicial = trim(form.dataInicial.value);
 	   	var periodoEncerramentoFinal = trim(form.dataFinal.value);
    	
    	if ((periodoEncerramentoInicial != null && periodoEncerramentoInicial != '') &&
    	((periodoEncerramentoFinal == null || periodoEncerramentoFinal == ''))) {
    		alert('Informe Data Final Período de Encerramento');
       		return false;
    	} else if ((periodoEncerramentoFinal != null && periodoEncerramentoFinal != '') &&
    	((periodoEncerramentoInicial == null || periodoEncerramentoInicial == ''))) {
    		alert('Informe Data Inicial Período de Encerramento');
    		return false;
    	}
    	
    	return true;
    }
	
	function pesquisarImovel() {
		var form = document.forms[0];
	 	
		if (form.idImovelDebitos.disabled ) {
	 		alert("Para realizar uma pesquisa de imóvel é necessário apagar o imóvel atual.")
		} else {
			abrirPopup('exibirPesquisarImovelAction.do', 400, 800);				
		}		
	}

	function replicaDados(campoOrigem, campoDestino){
		campoDestino.value = campoOrigem.value;
	}	
	
	function limparMatricula() {
		var form = document.forms[0];				
		form.idMatricula.value = '';
	    form.nomeMatricula.value = '';			
	}	
	
	function desabilitarTotalizacao(){
		var form = document.forms[0];				
		if(form.idMatricula.value == null || form.idMatricula.value == ''){			
			form.indicadorEstado.disabled = false;
			form.indicadorGerenciaRegional.disabled = false;
			form.indicadorUnidadeNegocio.disabled = false;
			form.indicadorLocalidade.disabled = false;
			form.indicadorSetorComercial.disabled = false;
			form.idGerenciaRegional.disabled = true;
			form.idUnidadeNegocio.disabled = true;
			form.idLocalidade.disabled = true;
			form.idSetorComercial.disabled = true;
			form.tipo[0].disabled = false;
			form.tipo[1].disabled = false;	
		}else{			
			form.indicadorEstado.disabled = true;
			form.indicadorGerenciaRegional.disabled = true;
			form.indicadorUnidadeNegocio.disabled = true;
			form.indicadorLocalidade.disabled = true;
			form.indicadorSetorComercial.disabled = true;
			form.idGerenciaRegional.disabled = true;
			form.idUnidadeNegocio.disabled = true;
			form.idLocalidade.disabled = true;
			form.idSetorComercial.disabled = true;
			form.tipo.value = 'analitico';
			form.tipo[0].checked = true;
			form.tipo[1].checked = false;
			form.tipo[0].disabled = true;
			form.tipo[1].disabled = true;
		}
	}
	
	function desabilitarCheckBox(){		
		var form = document.forms[0];		
			form.indicadorEstado.disabled = true;
			form.indicadorGerenciaRegional.disabled = true;
			form.indicadorUnidadeNegocio.disabled = true;
			form.indicadorLocalidade.disabled = true;
			form.indicadorSetorComercial.disabled = true;
			form.idGerenciaRegional.disabled = true;
			form.idUnidadeNegocio.disabled = true;
			form.idLocalidade.disabled = true;
			form.idSetorComercial.disabled = true;
			form.tipo.value = 'analitico';	
			form.tipo[0].checked = true;
			form.tipo[1].checked = false;
			form.tipo[0].disabled = true;
			form.tipo[1].disabled = true;		
	}
	
	function habilitarCheckBox(){
		var form = document.forms[0];
		form.indicadorEstado.disabled = false;
		form.indicadorGerenciaRegional.disabled = false;
		form.indicadorUnidadeNegocio.disabled = false;
		form.indicadorLocalidade.disabled = false;
		form.indicadorSetorComercial.disabled = false;
		form.idGerenciaRegional.disabled = false;
		form.idUnidadeNegocio.disabled = false;
		form.idLocalidade.disabled = false;
		form.idSetorComercial.disabled = false;
		form.tipo[0].disabled = false;
		form.tipo[1].disabled = false;		
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

	    var form = document.forms[0];
	    
	    if (tipoConsulta == 'imovel') {	    	    	
	      form.idMatricula.value = codigoRegistro;
	      form.nomeMatricula.value = descricaoRegistro;
	      desabilitarCheckBox();
	    }	    
	}
	
	function mostrarCombox(checkBox, comboBox){
		
		if(checkBox.checked == true){
			comboBox.disabled = false;			
		}else{
			comboBox.disabled = true;
			comboBox.value = '-1';
		}		
	}
</script>
<head>
	<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
	<html:javascript staticJavascript="false"  formName="GerarRelatorioPagamentoBaixaAutomaticaActionForm" />	
	<%@ include file="/jsp/util/titulo.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">	
	<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>	
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body leftmargin="5" topmargin="5" onload="desabilitarTotalizacao();">              
    <div id="formDiv">          
	<html:form action="/gerarRelatorioPagamentoBaixaAutomaticaAction.do"
	           name="GerarRelatorioPagamentoBaixaAutomaticaActionForm"	                                  
	           type="gcom.gui.relatorio.arrecadacao.GerarRelatorioPagamentoBaixaAutomaticaActionForm"
	           method="post">
	<html:hidden property="opcaoTotalizacao" />
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="115" valign="top" class="leftcoltext">
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
			<td width="600" valign="top" bgcolor="#003399" class="centercoltext">
				<table height="100%">
					<tr>
						<td></td>
					</tr>
				</table>
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
						<td class="parabg">Gerar Relatório de Pagamento com Baixa Automática</td>
	
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
					</tr>
					<tr>
						<td height="5" colspan="3"></td>
					</tr>
				</table>
				<table width="100%" border="0">				    			    
				    <tr>
						<td colspan="2" width="574px">
							<p>Para gerar o Relat&oacute;rio, informe os dados abaixo:</p>							
						</td>
					</tr>					
					<tr>					    
						<td width="26%">
						  <strong>Tipo de Relat&oacute;rio:<FONT color=#ff0000>*</FONT></strong>
						</td>						
						<td width="74%">
						  <html:radio property="tipo" value="analitico" />Anal&iacute;tico						  
						  <html:radio property="tipo" value="sintetico" />Sint&eacute;tico						 
						</td>
					</tr>			
					<tr>
						<td width="25%"><strong>Período da Retificação de Contas:</strong>
							<strong><font color="#FF0000">*</font></strong>
						</td>
						<td><strong> <html:text property="dataInicial"
							size="8" maxlength="10" onkeypress="return isCampoNumerico(event);"
							onkeyup="mascaraData(this,event);replicarCampo(document.forms[0].dataFinal,this);" />
						<a
							href="javascript:abrirCalendarioReplicando('GerarRelatorioPagamentoBaixaAutomaticaActionForm', 'dataInicial', 'dataFinal')">
						<img border="0"
							src="<bean:message key="caminho.imagens"/>calendario.gif"
							width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
						a </strong> <html:text property="dataFinal" size="8"
							maxlength="10" onkeyup="mascaraData(this,event)" onkeypress="return isCampoNumerico(event);" /> <a
							href="javascript:abrirCalendario('GerarRelatorioPagamentoBaixaAutomaticaActionForm', 'dataFinal')">
						<img border="0"
							src="<bean:message key="caminho.imagens"/>calendario.gif"
							width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
						(dd/mm/aaaa)</td>
					</tr>				
					<tr>
						<td width="130"><strong>Matrícula:</strong></td>
						<td colspan="2"><html:text maxlength="9" property="idMatricula"
								tabindex="1" size="7"
								onkeypress="javascript: validaEnterComMensagem(event, 'exibirGerarRelatorioPagamentoBaixaAutomaticaAction.do', 'idMatricula', 'Matricula'); desabilitarCheckBox(); return isCampoNumerico(event);"
								onkeydown="javascript:desabilitarCheckBox();" 
								onblur="javascript:desabilitarTotalizacao();"
								onkeyup="javascript:desabilitarTotalizacao();"/>								
							<a href="javascript:abrirPopup('exibirPesquisarImovelAction.do');">
							<img width="23" height="21" border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Matricula" /></a> 
							<logic:present name="idMatriculaNaoEncontrado" scope="request">
								<html:text maxlength="40" property="nomeMatricula" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" size="25"
									onblur="javascript:desmarcarTodos();" />
							</logic:present> 
							<logic:notPresent name="idMatriculaNaoEncontrado" scope="request">
								<html:text maxlength="30" property="nomeMatricula" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000"
									size="25" />
							</logic:notPresent> 
							<a href="javascript:limparMatricula(); habilitarCheckBox();"> 
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" title="Apagar" />
							</a>
						</td>
					</tr>																	
					<tr>
						<td width="70">
							<strong>Op&ccedil;&atilde;o de Totaliza&ccedil;&atilde;o:</strong>
						</td>
						<td>
							<html:checkbox property="indicadorEstado" value="true"/>
							<strong> Por Estado</strong>
						</td>							
					</tr>
					<tr>
						<td width="70"/>
						<td width="450">
							<html:checkbox property="indicadorGerenciaRegional" value="true"
							  		onclick="javascript:mostrarCombox(this,idGerenciaRegional);" />
							  	<strong> Por Gerência Regional</strong>
								&nbsp;&nbsp;
							<html:select property="idGerenciaRegional" style="width: 250px;" disabled="true">
								<html:option
									value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<html:options collection="colecaoGerenciaRegional" labelProperty="nome"
									property="id" />
							</html:select>								
						</td>
						<td>
					</tr>
					<tr>
						<td width="70"/>
						<td width="450">
							<html:checkbox property="indicadorUnidadeNegocio" value="true" 
									onclick="javascript:mostrarCombox(this,idUnidadeNegocio);"/>
								<strong> Por Unidade de Negócio</strong>
							<html:select property="idUnidadeNegocio" style="width: 250px;" disabled="true">
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<html:options collection="colecaoUnidadeNegocio" 
								  		labelProperty="nome"								  
										property="id" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td width="70"/>
						<td width="450">
							<html:checkbox property="indicadorLocalidade" value="true" 
							  		onclick="javascript:mostrarCombox(this,idLocalidade);"/>
							  	<strong> Por Localidade</strong>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<html:select property="idLocalidade" style="width: 250px;" disabled="true">
								<html:option
									value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<html:options collection="colecaoLocalidade" labelProperty="descricao"
									property="id" />
							</html:select>
						</td>
					</tr>					
					<tr>
						<td width="70"></td>
						<td width="450">
							<html:checkbox property="indicadorSetorComercial" value="true" 
							  			onclick="javascript:mostrarCombox(this,idSetorComercial);"/>
								<strong> Por Setor Comercial</strong>	
							  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;						
							<html:select property="idSetorComercial" style="width: 250px;" disabled="true">
								<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<html:options collection="colecaoSetorComercial" labelProperty="descricao"
										property="id" />
							 </html:select>
						</td>
					</tr>					
					<tr>					    
						<td width="26%">
						  <STRONG>Baixa Automática de Pagamentos por:<FONT color=#ff0000>*</FONT></STRONG>
						</td>						
						<td width="74%">
						  <html:radio property="baixaAutomaticaPagamento" value="1"/>Crédito						  
						  <html:radio property="baixaAutomaticaPagamento" value="2"/>Débito
						  <html:radio property="baixaAutomaticaPagamento" value="3"/>Todos						 
						</td>
					</tr>					
					<tr>
						<td width="50"><strong>Faixa de Diferença dos Valores</strong></td>
						<td><strong> 
							<html:text property="faixaDiferencaValoresInicial" size="10"
								  onkeyup="javascript:formataValorMonetario(this, 8);"
								  onkeypress="return isCampoNumerico(event);" maxlength="10" />
								a</strong> 
							<html:text property="faixaDiferencaValoresFinal" size="10"
								  onkeyup="javascript:formataValorMonetario(this, 8);"
								  onkeypress="return isCampoNumerico(event);" maxlength="10"/>							
						</td>
					</tr>										             		
					<tr>						
						<td colspan="2" align="center">
							<strong><font color="#FF0000">*</font>Campos Obrigat&oacute;rios</strong>
						</td>
					</tr>
					<tr>
						<td>
						</td>
					</tr>
					<tr>
						<td>
						</td>
					</tr>
					<tr>
						<td>
						</td>
					</tr>
					<tr>						
						<td>
						  <font color="#FF0000"> 
						    <input type="button" name="ButtonCancelar" 
						      class="bottonRightCol" value="Cancelar"
						      onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
						  </font>
						  <input name="Button" type="button"
								class="bottonRightCol" value="Limpar" align="left"
								onclick="window.location.href='/gsan/exibirGerarRelatorioPagamentoBaixaAutomaticaAction.do?menu=sim';">
						</td>						
						 <td>
							<div align="right">
							  <gsan:controleAcessoBotao name="Submit23" value="Gerar" onclick="validarFormSubmit();" 
							  url="gerarRelatorioPagamentoBaixaAutomaticaAction.do"/>							  
							</div>							
						</td>						
							<td width="60">
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>					
					</tr>
				</table>
			</td>
		</tr>
	</table>	
    <div id="divAux" style="position:absolute; left:11px; top:50px;">
	  <jsp:include page="/jsp/relatorio/escolher_tipo_relatorio_tela_espera.jsp?relatorio=gerarRelatorioPagamentoBAixaAutomaticaAction.do"/>
	</div>
<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</div>
</body>
</html:html>