<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false" 
	formName="GerarRelatorioAcompanhamentoExecucaoOSActionForm" staticJavascript="false" dynamicJavascript="true"/>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">

	function validarForm(){
   		var form = document.forms[0];

    	if(validateGerarRelatorioAcompanhamentoExecucaoOSActionForm(form)){	
    		if(validaTodosPeriodos()){
				enviarSelectMultiplo('GerarRelatorioAcompanhamentoExecucaoOSActionForm','tipoServicoSelecionados');
				toggleBox('demodiv', 1);

			}	
		}
    }
    
    function validaTodosPeriodos() {

		var form = document.forms[0];
		
		if (validaPeriodoAtendimento(form) && validaPeriodoEncerramento(form)) {

    		if (comparaData(form.periodoAtendimentoInicial.value, '>', form.periodoAtendimentoFinal.value)){

				alert('Data Final do Per�odo de Atendimento � anterior � Data Inicial do Per�odo de Atendimento');
				return false;

			} else if (comparaData(form.periodoEncerramentoInicial.value, '>', form.periodoEncerramentoFinal.value)){

				alert('Data Final do Per�odo de Encerramento � anterior � Data Inicial do Per�odo de Encerramento');
				return false;

			}
			
		} else {
			return false;
		} 

		return true;
    }
    
    function validaPeriodoAtendimento(form) {
    	
    	var periodoAtendimentoInicial = trim(form.periodoAtendimentoInicial.value);
 	   	var periodoAtendimentoFinal = trim(form.periodoAtendimentoFinal.value);
    	
    	if ((periodoAtendimentoInicial != null && periodoAtendimentoInicial != '') &&
    	((periodoAtendimentoFinal == null || periodoAtendimentoFinal == ''))) {
    		alert('Informe Data Final Per�odo de Atendimento');
       		return false;
    	} else if ((periodoAtendimentoFinal != null && periodoAtendimentoFinal != '') &&
    	((periodoAtendimentoInicial == null || periodoAtendimentoInicial == ''))) {
    		alert('Informe Data Inicial Per�odo de Atendimento');
    		return false;
    	}
    	
    	if (form.periodoAtendimentoInicial.readOnly == false && 
    	(periodoAtendimentoInicial == null || periodoAtendimentoInicial == '') && 
    	(periodoAtendimentoFinal == null || periodoAtendimentoFinal == '')) {
    		alert('Informe Per�odo de Atendimento');
    		return false;
    	}
    	
    	return true;
    }
    
    function validaPeriodoEncerramento(form) {
    	var form = document.forms[0];
    	
    	var periodoEncerramentoInicial = trim(form.periodoEncerramentoInicial.value);
 	   	var periodoEncerramentoFinal = trim(form.periodoEncerramentoFinal.value);
    	
    	if ((periodoEncerramentoInicial != null && periodoEncerramentoInicial != '') &&
    	((periodoEncerramentoFinal == null || periodoEncerramentoFinal == ''))) {
    		alert('Informe Data Final Per�odo de Encerramento');
       		return false;
    	} else if ((periodoEncerramentoFinal != null && periodoEncerramentoFinal != '') &&
    	((periodoEncerramentoInicial == null || periodoEncerramentoInicial == ''))) {
    		alert('Informe Data Inicial Per�odo de Encerramento');
    		return false;
    	}
    	
    	return true;
    }

    function limparUnidadeAtendimento() {
        var form = document.forms[0];

        form.idUnidadeAtendimento.value = "";
    	form.descricaoUnidadeAtendimento.value = "";
    }

    function limparUnidadeAtual() {
        var form = document.forms[0];

        form.idUnidadeAtual.value = "";
    	form.descricaoUnidadeAtual.value = "";
    }

    function limparUnidadeEncerramento() {
        var form = document.forms[0];

        form.idUnidadeEncerramento.value = "";
    	form.descricaoUnidadeEncerramento.value = "";
    }
    
    function limparUnidadeAtendimentoTecla() {
        var form = document.forms[0];

    	form.descricaoUnidadeAtendimento.value = "";
    }

    function limparUnidadeAtualTecla() {
        var form = document.forms[0];

    	form.descricaoUnidadeAtual.value = "";
    }

    function limparUnidadeEncerramentoTecla() {
        var form = document.forms[0];

    	form.descricaoUnidadeEncerramento.value = "";
    }

    function limparEquipeProgramacao() {
        var form = document.forms[0];

        form.idEquipeProgramacao.value = "";
    	form.descricaoEquipeProgramacao.value = "";
    }
    
    function limparEquipeExecucao() {
        var form = document.forms[0];

        form.idEquipeExecucao.value = "";
    	form.descricaoEquipeExecucao.value = "";
    }
    
    function limparEquipeProgramacaoTecla() {
        var form = document.forms[0];

    	form.descricaoEquipeProgramacao.value = "";
    }
    
    function limparEquipeExecucaoTecla() {
        var form = document.forms[0];

    	form.descricaoEquipeExecucao.value = "";
    }

    function limparPeriodoEncerramento() {

        var form = document.forms[0];
        
		form.periodoEncerramentoInicial.readOnly = false;
		form.periodoEncerramentoInicial.style.backgroundColor = '';
		
		form.periodoEncerramentoFinal.readOnly = false;
		form.periodoEncerramentoFinal.style.backgroundColor = '';
		
		form.periodoEncerramentoInicial.value="";
		form.periodoEncerramentoFinal.value="";
  	}
    
	//Replica Data de Atendimento
	function replicaDataAtendimento() {
		var form = document.forms[0];
		form.periodoAtendimentoFinal.value = form.periodoAtendimentoInicial.value;
	}

	//Replica Data de Encerramento
	function replicaDataEncerramento() {
		var form = document.forms[0];
		form.periodoEncerramentoFinal.value = form.periodoEncerramentoInicial.value;
	}

	function validaForm(){
		var form = document.forms[0];
		
		if(	form.situacaoOrdemServico.value == '1' || 
			form.situacaoOrdemServico.value == '3' ||
			form.situacaoOrdemServico.value == '4' ){

			limparPeriodoEncerramento();
			
			form.periodoEncerramentoInicial.readOnly = true;
			form.periodoEncerramentoInicial.style.backgroundColor = '#EFEFEF';
			
			form.periodoEncerramentoFinal.readOnly = true;
			form.periodoEncerramentoFinal.style.backgroundColor = '#EFEFEF';
		
		}else{

			form.periodoEncerramentoInicial.readOnly = false;
			form.periodoEncerramentoInicial.style.backgroundColor = '';
			
			form.periodoEncerramentoFinal.readOnly = false;
			form.periodoEncerramentoFinal.style.backgroundColor = '';
		
		}

		if(form.situacaoProgramacao[2].checked){
			
			limparPeriodoProgramacao();
			
			form.periodoProgramacaoInicial.readOnly = true;
			form.periodoProgramacaoInicial.style.backgroundColor = '#EFEFEF';
			
			form.periodoProgramacaoFinal.readOnly = true;
			form.periodoProgramacaoFinal.style.backgroundColor = '#EFEFEF';
		}else{

			form.periodoProgramacaoInicial.readOnly = false;
			form.periodoProgramacaoInicial.style.backgroundColor = '';
			
			form.periodoProgramacaoFinal.readOnly = false;
			form.periodoProgramacaoFinal.style.backgroundColor = '';
		}
		
	}
	
	function validaOrigemServico(origem) {
		var form = document.forms[0];
		
		if (origem == '2') {
			form.idUnidadeAtendimento.value = "";
			form.descricaoUnidadeAtendimento.value = "";
			form.idUnidadeAtendimento.readOnly = true;
			form.idUnidadeAtendimento.style.backgroundColor = '#EFEFEF';
			/*form.periodoAtendimentoInicial.value = "";
			form.periodoAtendimentoFinal.value = "";
			form.periodoAtendimentoInicial.disabled = true;
			form.periodoAtendimentoFinal.disabled = true;*/
		} else {
			form.idUnidadeAtendimento.readOnly = false;
			form.idUnidadeAtendimento.style.backgroundColor = '';
			form.periodoAtendimentoInicial.readOnly = false;
			form.periodoAtendimentoInicial.style.backgroundColor = '';
			form.periodoAtendimentoFinal.readOnly = false;
			form.periodoAtendimentoFinal.style.backgroundColor = '';
		}
	}
	
	function validaSituacaoOS(situacao) {
		var form = document.forms[0];
		
		if (situacao == '1') {
			form.idUnidadeEncerramento.value = "";
			form.descricaoUnidadeEncerramento.value = "";
			form.idUnidadeEncerramento.readOnly = true;
			form.idUnidadeEncerramento.style.backgroundColor = '#EFEFEF';
			form.periodoEncerramentoInicial.value = "";
			form.periodoEncerramentoFinal.value = "";
			form.periodoEncerramentoInicial.readOnly = true;
			form.periodoEncerramentoInicial.style.backgroundColor = '#EFEFEF';
			form.periodoEncerramentoFinal.readOnly = true;
			form.periodoEncerramentoFinal.style.backgroundColor = '#EFEFEF';
		} else {
			form.idUnidadeEncerramento.readOnly = false;
			form.idUnidadeEncerramento.style.backgroundColor = '';
			form.periodoEncerramentoInicial.readOnly = false;
			form.periodoEncerramentoInicial.style.backgroundColor = '';
			form.periodoEncerramentoFinal.readOnly = false;
			form.periodoEncerramentoFinal.style.backgroundColor = '';
		}
	}
	
	/* Chama Popup */ 
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		if(objetoRelacionado.readOnly != true){
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			} else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				} else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
				}
			}
		}
	}
	var unidade = 0;
	
	function setUnidade(tipo) {
		unidade = tipo;
	}
	
	var equipe = 0;
	
	function setEquipe(tipo) {
		equipe = tipo;
	}
	/* Recuperar Popup */
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    var form = document.forms[0];
	    
	    if (tipoConsulta == 'unidadeOrganizacional') {

	   		if (unidade == 1) {

		    	form.idUnidadeAtendimento.value = codigoRegistro;
		      	form.descricaoUnidadeAtendimento.value = descricaoRegistro;
	      		form.descricaoUnidadeAtendimento.style.color = "#000000";

	      	} else if (unidade == 2) {

		      	form.idUnidadeAtual.value = codigoRegistro;
		      	form.descricaoUnidadeAtual.value = descricaoRegistro;
      			form.descricaoUnidadeAtual.style.color = "#000000";

	      	} else if (unidade == 3) {
	      	
		      	form.idUnidadeEncerramento.value = codigoRegistro;
		      	form.descricaoUnidadeEncerramento.value = descricaoRegistro;
      			form.descricaoUnidadeEncerramento.style.color = "#000000";

	      	}
	      	
	      	unidade = 0;
	    
	    } else if (tipoConsulta == 'equipe') {
		    if (equipe == 1) {

		    	form.idEquipeProgramacao.value = codigoRegistro;
		      	form.descricaoEquipeProgramacao.value = descricaoRegistro;
	      		form.descricaoEquipeProgramacao.style.color = "#000000";

	      	} else if (equipe == 2) {

		      	form.idEquipeExecucao.value = codigoRegistro;
		      	form.descricaoEquipeExecucao.value = descricaoRegistro;
      			form.descricaoEquipeExecucao.style.color = "#000000";

	      	}
	      	
	      	equipe = 0;
	    
	    } 
	}
	
	//So chama a fun��o abrirCalendario caso o campo esteja habilitado
	function chamarCalendario(fieldNameOrigem,objetoRelacionado,fieldNameDestino){
		
		if(objetoRelacionado.readOnly != true){
			if(fieldNameDestino != ''){
				abrirCalendarioReplicando('GerarRelatorioAcompanhamentoExecucaoOSActionForm', fieldNameOrigem,fieldNameDestino);
			}else{
				abrirCalendario('GerarRelatorioAcompanhamentoExecucaoOSActionForm', fieldNameOrigem);
			}
		}
	}
	
	
	function MoveSelectedDataFromMenu1TO2(form, object, selectedObject){
		var form = document.forms[0];
		
		if (form.tipoServico.value != '-1') {
			MoverDadosSelectMenu1PARAMenu2(form, object, selectedObject);
		}
	}
	
	function MoveSelectedDataFromMenu2TO1(form, object, selectedObject){
		var form = document.forms[0];
		
		if (form.tipoServico.value != '-1') {
			MoverDadosSelectMenu2PARAMenu1(form, object, selectedObject);
		}
	}

</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="window.focus();javascript:setarFoco('${requestScope.nomeCampo}');validaOrigemServico('${requestScope.origemServico}');validaSituacaoOS('${requestScope.situacaoOS}');" >

<div id="formDiv">

<html:form action="/filtrarOrdemServicoAction" method="post"
	name="GerarRelatorioAcompanhamentoExecucaoOSActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.GerarRelatorioAcompanhamentoExecucaoOSActionForm" >


	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

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
			<td width="600" valign="top" class="centercoltext">
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
					<td class="parabg">Filtrar Relat�rio de Acompanhamento de Execu��o
					de Ordem de Servi�o</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">

				<tr>
					<td colspan="2">Para selecionar ordens de servi�o para gera��o do
					relat�rio de acompanhamento, informe os dados abaixo:</td>
				</tr>
				<tr>

					<td><strong>Origem dos Servi�os:</strong></td>
					<td><html:radio property="origemServico" tabindex="0"
						value="<%=""+ConstantesSistema.ORIGEM_SERVICO_SOLICITADO%>"
						onclick="javascript:validaOrigemServico('1');" /> <strong>Solicitados</strong>
					<html:radio property="origemServico" tabindex="0"
						value="<%=""+ConstantesSistema.ORIGEM_SERVICO_SELETIVO%>"
						onclick="javascript:validaOrigemServico('2');"/> <strong>Seletivos</strong></td>
				</tr>
				<tr>
					<td height="10" colspan="3">
					<div align="right">
					<hr>
					</div>
					<div align="right"></div>
					</td>
				</tr>
				<tr>
					<td><strong>Situa&ccedil;&atilde;o da Ordem de Servi&ccedil;o:</strong>
					</td>
					<td><html:radio property="situacaoOrdemServico" tabindex="1"
						value="<%=""+ConstantesSistema.SITUACAO_REFERENCIA_ENCERRADA%>"
						onclick="javascript:validaSituacaoOS('2');" /> <strong>Encerrados</strong>
					<html:radio property="situacaoOrdemServico" tabindex="1"
						value="<%=""+ConstantesSistema.SITUACAO_REFERENCIA_PENDENTE%>"
						onclick="javascript:validaSituacaoOS('1');" /> <strong>Pendentes</strong>
					<html:radio property="situacaoOrdemServico" value="" tabindex="1"
						onclick="javascript:validaSituacaoOS('');" /> <strong>Ambos</strong>
					</td>
				</tr>
				<tr>
					<td height="10" colspan="3">
					<div align="right">
					<hr>
					</div>
					<div align="right"></div>
					</td>
				</tr>
				<tr>
					<td width="120"><strong>Tipo de Servi&ccedil;o:</strong></td>
					<td colspan="2">
					<table width="100%" border=0 cellpadding=0 cellspacing=0
						align="left">
						<tr>
							<td width="175">

							<div align="left"><strong>Dispon�veis</strong></div>

							<html:select property="tipoServico" size="6" multiple="true" tabindex="2"
								style="width:190px" >

								<html:options collection="colecaoTipoServico" 
									labelProperty="descricao" property="id" />
							</html:select></td>

							<td width="5" valign="center"><br>
							<table width="50" align="center">
								<tr>
									<td align="center"><input type="button" class="bottonRightCol" tabindex="3"
										onclick="javascript:MoverTodosDadosSelectMenu1PARAMenu2('GerarRelatorioAcompanhamentoExecucaoOSActionForm', 'tipoServico', 'tipoServicoSelecionados'); enviarSelectMultiplo('GerarRelatorioAcompanhamentoExecucaoOSActionForm','tipoServicoSelecionados');"
										value=" &gt;&gt; "></td>
								</tr>

								<tr>
									<td align="center"><input type="button" class="bottonRightCol" tabindex="4"
										onclick="javascript:MoverDadosSelectMenu1PARAMenu2('GerarRelatorioAcompanhamentoExecucaoOSActionForm', 'tipoServico', 'tipoServicoSelecionados'); enviarSelectMultiplo('GerarRelatorioAcompanhamentoExecucaoOSActionForm','tipoServicoSelecionados');"
										value=" &nbsp;&gt;  "></td>
								</tr>

								<tr>
									<td align="center"><input type="button" class="bottonRightCol" tabindex="5"
										onclick="javascript:MoverDadosSelectMenu2PARAMenu1('GerarRelatorioAcompanhamentoExecucaoOSActionForm', 'tipoServico', 'tipoServicoSelecionados'); enviarSelectMultiplo('GerarRelatorioAcompanhamentoExecucaoOSActionForm','tipoServicoSelecionados');"
										value=" &nbsp;&lt;  "></td>
								</tr>

								<tr>
									<td align="center"><input type="button" class="bottonRightCol" tabindex="6"
										onclick="javascript:MoverTodosDadosSelectMenu2PARAMenu1('GerarRelatorioAcompanhamentoExecucaoOSActionForm', 'tipoServico', 'tipoServicoSelecionados'); enviarSelectMultiplo('GerarRelatorioAcompanhamentoExecucaoOSActionForm','tipoServicoSelecionados');"
										value=" &lt;&lt; "></td>
								</tr>
							</table>
							</td>

							<td>
							<div align="left"><strong>Selecionados</strong></div>

							<html:select property="tipoServicoSelecionados" size="6" tabindex="7"
								multiple="true" style="width:190px">
								
								<logic:present name="existeColecaoTipoServicoSelecionados">
									<html:options collection="colecaoTipoServicoSelecionados"
									labelProperty="descricao" property="id" />
								</logic:present>
								

							</html:select></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td height="10" colspan="3">
					<div align="right">
					<hr>
					</div>
					<div align="right"></div>
					</td>
				</tr>
				<tr>
					<td><strong>Unidade de Atendimento:</strong></td>
					<td><html:text maxlength="4" tabindex="8"
						property="idUnidadeAtendimento" size="4"
						onkeyup="javascript:limparUnidadeAtendimentoTecla();"
						onkeypress="validaEnterComMensagem(event, 'exibirGerarRelatorioAcompanhamentoExecucaoOSAction.do?objetoConsulta=1','idUnidadeAtendimento','Unidade Atendimento');" />
					<a tabindex="9"
						href="javascript:setUnidade(1); chamarPopup('exibirPesquisarUnidadeOrganizacionalAction.do?tipoUnidade=unidadeAtendimento', 'idUnidadeAtendimento', null, null, 275, 480, '', document.forms[0].idUnidadeAtendimento);">
					<img width="23" height="21" border="0" 
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Unidade Atendimento" /></a> <logic:present
						name="unidadeAtendimentoEncontrada" scope="request">

						<html:text property="descricaoUnidadeAtendimento" size="40"
							maxlength="40" readonly="true" tabindex="10"
							style="background-color:#EFEFEF; border:0; color: #000000" />

					</logic:present> <logic:notPresent
						name="unidadeAtendimentoEncontrada" scope="request">

						<html:text property="descricaoUnidadeAtendimento" size="40"
							maxlength="40" readonly="true" tabindex="10"
							style="background-color:#EFEFEF; border:0; color: red" />

					</logic:notPresent> <a tabindex="11"
						href="javascript:limparUnidadeAtendimento();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /> </a></td>
				</tr>

				<tr>
					<td><strong>Unidade Atual:</strong></td>
					<td><html:text maxlength="4" tabindex="12" property="idUnidadeAtual" 
						size="4" onkeyup="javascript:limparUnidadeAtualTecla();"
						onkeypress="validaEnterComMensagem(event, 'exibirGerarRelatorioAcompanhamentoExecucaoOSAction.do?objetoConsulta=2','idUnidadeAtual','Unidade Atual');" />

					<a tabindex="13"
						href="javascript:setUnidade(2); chamarPopup('exibirPesquisarUnidadeOrganizacionalAction.do?tipoUnidade=unidadeAtual', 'idUnidadeAtual', null, null, 275, 480, '', document.forms[0].idUnidadeAtual);">

					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Unidade Atual" /></a> <logic:present
						name="unidadeAtualEncontrada" scope="request">

						<html:text property="descricaoUnidadeAtual" size="40"
							maxlength="40" readonly="true" tabindex="14"
							style="background-color:#EFEFEF; border:0; color: #000000" />

					</logic:present> <logic:notPresent name="unidadeAtualEncontrada"
						scope="request">

						<html:text property="descricaoUnidadeAtual" size="40"
							maxlength="40" readonly="true" tabindex="14"
							style="background-color:#EFEFEF; border:0; color: red" />

					</logic:notPresent> <a tabindex="15" href="javascript:limparUnidadeAtual();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				
				
				
				<tr>
					<td><strong>Unidade Encerramento:</strong></td>
					<td><html:text maxlength="4" tabindex="16"
						property="idUnidadeEncerramento" size="4"
						onkeyup="javascript:limparUnidadeEncerramentoTecla();"
						onkeypress="validaEnterComMensagem(event, 'exibirGerarRelatorioAcompanhamentoExecucaoOSAction.do?objetoConsulta=3','idUnidadeEncerramento','Unidade Encerramento');" />

					<a tabindex="17"
						href="javascript:setUnidade(3); chamarPopup('exibirPesquisarUnidadeOrganizacionalAction.do?tipoUnidade=unidadeEncerramento', 'idUnidadeEnceramento', null, null, 275, 480, '', document.forms[0].idUnidadeEncerramento);">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Unidade Encerramento" /></a> <logic:present
						name="unidadeEncerramentoEncontrada" scope="request">
						<html:text property="descricaoUnidadeEncerramento" size="40"
							maxlength="40" readonly="true" tabindex="18"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:present> <logic:notPresent
						name="unidadeEncerramentoEncontrada" scope="request">

						<html:text property="descricaoUnidadeEncerramento" size="40"
							maxlength="40" readonly="true" tabindex="18"
							style="background-color:#EFEFEF; border:0; color: red" />

					</logic:notPresent> <a tabindex="19"
						href="javascript:limparUnidadeEncerramento();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /> </a></td>
				</tr>

				<tr>
					<td height="10" colspan="3">
					<div align="right">
					<hr>
					</div>
					<div align="right"></div>
					</td>
				</tr>

				<tr>
					<td><strong>Per&iacute;odo de Atendimento:</strong><font color="#FF0000">*</font></td>

					<td colspan="6"><span class="style2"> <strong> <html:text
						property="periodoAtendimentoInicial" size="11" maxlength="10"
						tabindex="20"
						onkeyup="mascaraData(this, event);replicaDataAtendimento();" /> <a tabindex="21"
						href="javascript:abrirCalendarioReplicando('GerarRelatorioAcompanhamentoExecucaoOSActionForm', 'periodoAtendimentoInicial','periodoAtendimentoFinal');">
					<img border="0"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						width="16" height="15" border="0" alt="Exibir Calend�rio"
						/></a> a <html:text
						property="periodoAtendimentoFinal" size="11" maxlength="10"
						tabindex="22" onkeyup="mascaraData(this, event)" /> <a tabindex="23"
						href="javascript:abrirCalendario('GerarRelatorioAcompanhamentoExecucaoOSActionForm', 'periodoAtendimentoFinal');">
					<img border="0"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						width="16" height="15" border="0" alt="Exibir Calend�rio"
						 /></a> </strong>(dd/mm/aaaa)<strong> </strong> </span>
					</td>
				</tr>
				<tr>
					<td><strong>Per&iacute;odo de Encerramento:</strong></td>

					<td colspan="6"><span class="style2"> <strong> <html:text
						property="periodoEncerramentoInicial" size="11" maxlength="10"
						tabindex="24"
						onkeyup="mascaraData(this, event);replicaDataEncerramento();" />
					<a
						href="javascript:chamarCalendario('periodoEncerramentoInicial',document.forms[0].periodoEncerramentoInicial,'periodoEncerramentoFinal');">
					<img border="0"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						width="16" height="15" border="0" alt="Exibir Calend�rio"
						tabindex="25" /></a> a <html:text
						property="periodoEncerramentoFinal" size="11" maxlength="10"
						tabindex="26" onkeyup="mascaraData(this, event)" /> <a
						href="javascript:chamarCalendario('periodoEncerramentoFinal',document.forms[0].periodoEncerramentoFinal,'');">
					<img border="0"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						width="16" height="15" border="0" alt="Exibir Calend�rio"
						tabindex="27" /></a> </strong>(dd/mm/aaaa)<strong> </strong> </span>
					</td>
				</tr>

				<tr>
					<td height="10" colspan="3">
					<div align="right">
					<hr>
					</div>
					<div align="right"></div>
					</td>
				</tr>
				<tr>
					<td><strong>Equipe de Programa��o:</strong></td>
					<td><html:text maxlength="4" tabindex="28"
						property="idEquipeProgramacao" size="4"
						onkeyup="javascript:limparEquipeProgramacaoTecla();"
						onkeypress="validaEnterComMensagem(event, 'exibirGerarRelatorioAcompanhamentoExecucaoOSAction.do?objetoConsulta=4','idEquipeProgramacao','Equipe de Programa��o');" />
					<a tabindex="29"
						href="javascript:setEquipe(1); chamarPopup('exibirPesquisarEquipeAction.do?tipoEquipe=equipeProgramacao', 'idEquipeProgramacao', null, null, 275, 480, '', document.forms[0].idUnidadeAtendimento);">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Equipe de Progama��o" /></a> <logic:present
						name="equipeProgramacaoEncontrada" scope="request">

						<html:text property="descricaoEquipeProgramacao" size="40"
							maxlength="40" readonly="true" tabindex="30"
							style="background-color:#EFEFEF; border:0; color: #000000" />

					</logic:present> <logic:notPresent
						name="equipeProgramacaoEncontrada" scope="request">

						<html:text property="descricaoEquipeProgramacao" size="40"
							maxlength="40" readonly="true" tabindex="30"
							style="background-color:#EFEFEF; border:0; color: red" />

					</logic:notPresent> <a tabindex="31" href="javascript:limparEquipeProgramacao();">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /> </a></td>
				</tr>

				<tr>
					<td><strong>Equipe de Execu��o:</strong></td>
					<td><html:text maxlength="4" tabindex="32"
						property="idEquipeExecucao" size="4"
						onkeyup="javascript:limparEquipeExecucaoTecla();"
						onkeypress="validaEnterComMensagem(event, 'exibirGerarRelatorioAcompanhamentoExecucaoOSAction.do?objetoConsulta=5','idEquipeExecucao','Equipe de Execu��o');" />

					<a tabindex="33"
						href="javascript:setEquipe(2); chamarPopup('exibirPesquisarEquipeAction.do?tipoEquipe=equipeExecucao', 'idEquipeExecucao', null, null, 275, 480, '', document.forms[0].idEquipeExecucao);">

					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Equipe de Execu��o" /></a> <logic:present
						name="equipeExecucaoEncontrada" scope="request">

						<html:text property="descricaoEquipeExecucao" size="40"
							maxlength="40" readonly="true" tabindex="34"
							style="background-color:#EFEFEF; border:0; color: #000000" />

					</logic:present> <logic:notPresent name="equipeExecucaoEncontrada"
						scope="request">

						<html:text property="descricaoEquipeExecucao" size="40" tabindex="34"
							maxlength="40" readonly="true"
							style="background-color:#EFEFEF; border:0; color: red" />

					</logic:notPresent> <a tabindex="35" href="javascript:limparEquipeExecucao();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				<tr>
					<td><strong>Tipo de Ordena��o</strong></td>
					<td><html:radio tabindex="36" property="tipoOrdenacao" value="1"> 
						<strong> N�mero RA</strong>
					</html:radio> <html:radio tabindex="36" property="tipoOrdenacao" value="2">
						<strong> Endere�o</strong>
					</html:radio></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td align="left">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>				
				
				<tr>
				<td colspan="2">
				<input type="button" name="adicionar2" 
                			class="bottonRightCol" 
                			value="Limpar" tabindex="39"
                			onclick="window.location.href='/gsan/exibirGerarRelatorioAcompanhamentoExecucaoOSAction.do?menu=sim';">
				&nbsp;
				<input type="button" name="adicionar" 
			                class="bottonRightCol" 
			                value="Cancelar" tabindex="38"
			                onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
				</td>
                <td>
                                <input type="button" name="Button" 
	                    		class="bottonRightCol" tabindex="37" 
	                    		value="Filtrar" 
	                    		onclick="javascript:validarForm();">
                </td>	 
              </tr>      
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio_tela_espera.jsp?relatorio=gerarRelatorioAcompanhamentoExecucaoOSAction.do" />
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</div>
<%@ include file="/jsp/util/telaespera.jsp"%>
</body>
</html:html>
