<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="AtualizarAdicionarSolicitacaoEspecificacaoActionForm" />

<script language="JavaScript">
    
    window.onmousemove = iniciarJsp;
	function validaRadioButton(nomeCampo,mensagem){
		var alerta = "";
		if(!nomeCampo[0].checked && !nomeCampo[1].checked){
			alerta = mensagem +" deve ser selecionado.";
		}
		return alerta;
	}
	
function adicionarArquivo(){
	var form = document.forms[0];
	form.target = "";
	form.action = 'exibirAtualizarAdicionarSolicitacaoEspecificacaoAction.do?adicionar=OK&retornarTela=exibirInserirTipoSolicitacaoEspecificacaoAction.do';
	retorno = true;
	if(form.arquivo.value.length == 0){
		alert("Informe o Arquivo");
		form.arquivo.focus();
		retorno = false;
	}
	else if (!validateCaracterEspecial(form)){
		retorno = false;
	}
	if (retorno){
		form.submit();
	}	
}
function removerArquivo(identificacao){	
	var form = document.forms[0];
	form.action = 'exibirAtualizarAdicionarSolicitacaoEspecificacaoAction.do?retornarTela=exibirInserirTipoSolicitacaoEspecificacaoAction.do&remover=' + identificacao;
	form.submit();	
}

	function iniciarJsp(){
		var form = document.forms[0];
	
	}

	function validarRadioButtonLigacoes(){

		var form = document.forms[0];
		
		if(form.indicadorLigacaoAgua[0].checked){			

			form.indicadorLigacaoEsgoto[0].disabled = true;
			form.indicadorLigacaoEsgoto[1].checked = true;
			
			form.indicadorPoco[0].disabled = true;
			form.indicadorPoco[1].checked = true;
			
		}else if(form.indicadorPoco[0].checked){		
			
			form.indicadorLigacaoEsgoto[0].disabled = true;
			form.indicadorLigacaoEsgoto[1].checked = true;
			
			form.indicadorLigacaoAgua[0].disabled = true;
			form.indicadorLigacaoAgua[1].checked = true;
			
		}else if(form.indicadorLigacaoEsgoto[0].checked){

			form.indicadorLigacaoAgua[0].disabled = true;
			form.indicadorLigacaoAgua[1].checked = true;
			
			form.indicadorPoco[0].disabled = true;
			form.indicadorPoco[1].checked = true;
		}else {
			form.indicadorLigacaoAgua[0].disabled = false;
			form.indicadorPoco[0].disabled = false;
			form.indicadorLigacaoEsgoto[0].disabled = false;
		}
		
	}

	function validaTodosRadioButton(){
		var form = document.forms[0];
		var mensagem = "";
		if(validaRadioButton(form.indicadorPavimentoCalcada," Pavimento Cal�ada Obrigat�ria?") != ""){
				mensagem = mensagem + validaRadioButton(form.indicadorPavimentoCalcada," Pavimento Cal�ada Obrigat�ria?")+"\n";
		}
		if(validaRadioButton(form.indicadorPavimentoRua,"Pavimento Rua Obrigat�ria?") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorPavimentoRua," Pavimento Rua Obrigat�ria?")+"\n";
		}
		if(validaRadioButton(form.indicadorLigacaoAgua,"Refere-se a Liga��o de �gua?") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorLigacaoAgua,"Refere-se a Liga��o de �gua?")+"\n";
		}

		if(validaRadioButton(form.indicadorPoco,"Refere-se a Po�o?") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorPoco,"Refere-se a Liga��o de Po�o?")+"\n";
		}

		if(validaRadioButton(form.indicadorLigacaoEsgoto,"Refere-se a Liga��o de Esgoto?") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorLigacaoEsgoto,"Refere-se a Liga��o de Esgoto?")+"\n";
		}
		
		if(validaRadioButton(form.indicadorCobrancaMaterial," Cobran�a de Material?") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorCobrancaMaterial," Cobran�a de Material?")+"\n";
		}
		if(validaRadioButton(form.indicadorParecerEncerramento," Parecer de Encerramento Obrigat�rio?") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorParecerEncerramento," Parecer de Encerramento Obrigat�rio?")+"\n";
		}
		if(validaRadioButton(form.indicadorGerarDebito,"Gera D�bito?") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorGerarDebito,"Gera D�bito?")+"\n";
		}
		if(validaRadioButton(form.indicadorGerarCredito,"Gera Cr�dito?") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorGerarCredito,"Gera Cr�dito?")+"\n";
		}
		if(validaRadioButton(form.indicadorCliente,"Cliente Obrigat�rio?") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorCliente,"Cliente Obrigat�rio?")+"\n";
		}
		if(validaRadioButton(form.indicadorVerificarDebito,"Existe Verifica��o de D�bito?") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorVerificarDebito,"Existe Verifica��o de D�bito?")+"\n";
		}
		if(validaRadioButton(form.indicadorMatriculaImovel,"Matr�cula do Im�vel Obrigat�ria?") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorMatriculaImovel,"Matr�cula do Im�vel Obrigat�ria?")+"\n";
		}
		if(validaRadioButton(form.indicadorUrgencia,"Indicador de Urg�ncia Obrigat�rio?") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorMatriculaImovel,"Indicador de Urg�ncia Obrigat�rio?")+"\n";
		}
		if(validaRadioButton(form.indicadorPermiteAlterarValor,"Alterar o valor do d�bito?") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorPermiteAlterarValor,"Alterar o valor do d�bito?")+"\n";
		}
		
		if(validaRadioButton(form.indicadorCobrarJuros,"Cobrar juros?") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorCobrarJuros,"Cobrar juros?")+"\n";
		}
		
		if(validaRadioButton(form.indicadorGeraOrdemServico,"Gera Ordem de Servi�o?") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorGeraOrdemServico,"Gera Ordem de Servi�o?")+"\n";
		}
		
		if(validaRadioButton(form.indicadorEncerramentoAutomatico,"Encerramento Autom�tico?") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorEncerramentoAutomatico,"Encerramento Autom�tico?")+"\n";
		}
		
		if(validaRadioButton(form.indicadorLojaVirtual, "Indicador Loja Virtual") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorLojaVirtual, "Indicador Loja Virtual") + "\n";
		}
		
		if(validaRadioButton(form.indicadorInformarContaRA,"Informar conta no Registro de Atendimento") != ""){
			mensagem = mensagem + validaRadioButton(form.indicadorInformarContaRA,"Informar conta no Registro de Atendimento")+"\n";
		}
	 
		if(mensagem != ""){
			alert(mensagem);
			return false;
		}else{
			return true;
		}
	}

    

 
	function validarForm(form){
		if(validateAtualizarAdicionarSolicitacaoEspecificacaoActionForm(form) 
				&& validaTodosRadioButton() && validaIndicadorGerarOrdemServico()){
		    form.action="atualizarAdicionarSolicitacaoEspecificacaoAction.do?btatualizar=sim";
		    botaoDesabilita(form)
		    submeterFormPadrao(form);
		
		}
	}

	function desfazer(){
		form = document.forms[0];
	 	form.action='exibirAtualizarAdicionarSolicitacaoEspecificacaoAction.do?limpaSessao=1';
	 	form.submit();
	}

	function desfazerAtualizar(){
		form = document.forms[0];
		form.action='exibirAtualizarAdicionarSolicitacaoEspecificacaoAction.do?desfazer=sim';
		form.submit();
	}

	function desabilitaCampos(){
		var form = document.forms[0];
		if(form.indicadorGeraOrdemServico[0].checked){
			form.idServicoOS.disabled = false;
			form.adicionarTipoServico.disabled = false;
			form.valorDebito.disabled = true;
			form.idDebitoTipo.disabled = true;
			form.descricaoDebitoTipo.disabled = true;
			form.valorDebito.value = "";
			form.idDebitoTipo.value = "" ;
			form.descricaoDebitoTipo.value = "";
		}else{
			form.idServicoOS.value = "";
			form.idServicoOS.disabled = true;
			form.descricaoServicoOS.value="";
			form.adicionarTipoServico.disabled = true;
			form.valorDebito.disabled = false;
			form.idDebitoTipo.disabled = false;
			form.descricaoDebitoTipo.disabled = false;
		}
	}

	

	
	function desabilitaCamposDebitoCredito(){
		var form = document.forms[0];
		//indicador igual a sim
		if(form.indicadorGerarDebito[0].checked){
			form.indicadorGerarCredito[0].disabled = true;
			form.indicadorGerarCredito[1].disabled = true;
			form.indicadorGerarCredito[1].checked = true;
		}
	}

	function desabilitaCamposDebitoCreditoOnClick(){
		var form = document.forms[0];
		//indicador igual a sim
		if(form.indicadorGerarDebito[0].checked){
			form.indicadorGerarCredito[0].disabled = true;
			form.indicadorGerarCredito[1].disabled = true;
			form.indicadorGerarCredito[1].checked = true;
		}else{
			form.indicadorGerarCredito[0].disabled = false;
			form.indicadorGerarCredito[1].disabled = false;
			form.indicadorGerarCredito[1].checked = false;
		
		}
	}

	function verificarCampoDesabilitado(campo,redirect,limparCampo){
	 	if(!campo.disabled){
	  		redirecionarSubmit(redirect);
	  		limparCampo = ''; 
	 	}
	}

	function limparPesquisaUnidadeTramitacao(){
		var form = document.forms[0];
		form.idUnidadeTramitacao.value= '';
		form.descricaoUnidadeTramitacao.value= '';
	}

	function limparPesquisaTipoServicoOS(){
		var form = document.forms[0];
		form.idServicoOS.value= '';
		form.descricaoServicoOS.value= '';
	}
	
	function limparPesquisaDebitoTipo(){

		var form = document.forms[0];
		form.idDebitoTipo.value= '';
		form.descricaoDebitoTipo.value= '';
	}

	function desabilitaCamposMatriculaImovelOnClick(){
		var form = document.forms[0];
		//indicador igual a sim
		if(form.indicadorMatriculaImovel[0].checked){
			form.idSituacaoImovel.disabled = false;
		}else{
			form.idSituacaoImovel.disabled = true;
			form.idSituacaoImovel.value = '';
		}
	}
	
	function statusEspecificacao(){
		var form = document.forms[0];
		form.action="exibirAtualizarAdicionarSolicitacaoEspecificacaoAction.do?objetoConsulta=tipoSolicitacao";
   		submeterFormPadrao(form);
   	}
	
	function validaIndicadorGerarOrdemServico(){

		var form = document.forms[0];
		
		if (form.indicadorGeraOrdemServico[0].checked && 
				(form.idServicoOS.value == null || form.idServicoOS.value == "")) {
			alert("O Tipo de Servi�o deve ser informado!");
			return false;
			}

		return true;
		}
	
</script>

</head>
<logic:notPresent name="fecharPopup" scope="request">
	<body leftmargin="5" topmargin="5"
		onload="javascript:resizePageSemLink(642, 700);setarFoco('${requestScope.nomeCampo}');desabilitaCampos();desabilitaCamposDebitoCredito();desabilitaCamposMatriculaImovelOnClick();validarRadioButtonLigacoes();">
</logic:notPresent>
<logic:present name="fecharPopup" scope="request">
	<body leftmargin="0" topmargin="0"
		onload="chamarReload('exibirAtualizarTipoSolicitacaoEspecificacaoAction.do');window.close();">
</logic:present>
<body leftmargin="5" topmargin="5"
	onload="javascript:resizePageSemLink(642, 700);setarFoco('${requestScope.nomeCampo}');desabilitaCampos();desabilitaCamposDebitoCredito();desabilitaCamposMatriculaImovelOnClick();validarRadioButtonLigacoes();">





<html:form action="/atualizarAdicionarSolicitacaoEspecificacaoAction"
	name="AtualizarAdicionarSolicitacaoEspecificacaoActionForm"
	type="gcom.gui.atendimentopublico.registroatendimento.AtualizarAdicionarSolicitacaoEspecificacaoActionForm"
	enctype="multipart/form-data"
	method="post">


	<table width="615" border="0" cellspacing="5" cellpadding="0">
		<tr>

			<td width="100%" valign="top" class="centercoltext">
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
					<td class="parabg"><html:hidden property="cabecalho" /> <bean:write
						name="AtualizarAdicionarSolicitacaoEspecificacaoActionForm"
						property="cabecalho" /> Especifica��o do Tipo da Solicita��o</td>

					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				<tr>
					<td height="5" colspan="3"></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">
					<p>Preencha os campos para atualizar uma especifica��o para o tipo
					de solicita��o:</p>
					<p>&nbsp;</p>
					</td>
				</tr>
				<tr>
					<td width="33%"><strong>Descri��o:<font color="#FF0000">*</font></strong></td>
					<td><html:text property="descricaoSolicitacao" size="40"
						maxlength="50" tabindex="1" /></td>
				</tr>
				<tr>
					<td><strong> Prazo Previs�o de Atendimento (em dias):</strong><strong><font
						color="#FF0000">*</font></strong></td>
					<td><html:text property="prazoPrevisaoAtendimento" size="4"
						maxlength="4" tabindex="2" onkeyup="somente_numero(this);"/></td>

				</tr>
				<tr>
					<td><strong> Pavimento Cal�ada Obrigat�ria?:<font color="#FF0000">*</font></strong></td>
					<td><html:radio property="indicadorPavimentoCalcada" value="1"
						tabindex="3" /> <strong>Sim</strong>&nbsp; <html:radio
						property="indicadorPavimentoCalcada" value="2" tabindex="4" /> <strong>N&atilde;o</strong></td>
				</tr>
				<tr>
					<td><strong> Pavimento Rua Obrigat�ria?:<font color="#FF0000"></font><font
						color="#FF0000">*</font></strong></td>
					<td><html:radio property="indicadorPavimentoRua" value="1"
						tabindex="5" /> <strong>Sim</strong>&nbsp; <html:radio
						property="indicadorPavimentoRua" value="2" tabindex="6" /> <strong>N&atilde;o</strong>
					</td>
				</tr>
				<tr>
					<td><strong>Refere-se a Liga��o de �gua?:<font color="#FF0000"></font><font
						color="#FF0000">*</font></strong></td>
					<td><html:radio property="indicadorLigacaoAgua" value="1"
						tabindex="5" onchange="javascript:validarRadioButtonLigacoes();" /> <strong>Sim</strong>&nbsp; <html:radio
						property="indicadorLigacaoAgua" value="2" tabindex="6" onchange="javascript:validarRadioButtonLigacoes();" /> <strong>N&atilde;o</strong>
					</td>
				</tr>
				<tr>
					<td><strong>Refere-se a Po�o?:<font color="#FF0000"></font><font
						color="#FF0000">*</font></strong></td>
					<td><html:radio property="indicadorPoco" value="1"
						tabindex="5" onchange="javascript:validarRadioButtonLigacoes();" /> <strong>Sim</strong>&nbsp; <html:radio
						property="indicadorPoco" value="2" tabindex="6" onchange="javascript:validarRadioButtonLigacoes();"/> <strong>N&atilde;o</strong>
					</td>
				</tr>
				<tr>
					<td><strong>Refere-se a Liga��o de Esgoto?:<font color="#FF0000"></font><font
						color="#FF0000">*</font></strong></td>
					<td><html:radio property="indicadorLigacaoEsgoto" value="1"
						tabindex="5" onchange="javascript:validarRadioButtonLigacoes();" /> <strong>Sim</strong>&nbsp; <html:radio
						property="indicadorLigacaoEsgoto" value="2" tabindex="6" onchange="javascript:validarRadioButtonLigacoes();"/> <strong>N&atilde;o</strong>
					</td>
				</tr>
				<tr>
					<td><strong> Cobran�a de Material?:<font color="#FF0000"></font><font
						color="#FF0000">*</font></strong></td>
					<td><html:radio property="indicadorCobrancaMaterial" value="1"
						tabindex="7" /> <strong>Sim</strong>&nbsp; <html:radio
						property="indicadorCobrancaMaterial" value="2" tabindex="8" /> <strong>N&atilde;o</strong>
					</td>
				</tr>
				<tr>
					<td><strong>Parecer de Encerramento Obrigat�rio?:<font
						color="#FF0000"></font><font color="#FF0000">*</font></strong></td>
					<td><html:radio property="indicadorParecerEncerramento" value="1"
						tabindex="9" /> <strong>Sim</strong>&nbsp; <html:radio
						property="indicadorParecerEncerramento" value="2" tabindex="10" />
					<strong>N&atilde;o</strong></td>
				</tr>
				<tr>
					<td><strong>Gera D�bito?:<font color="#FF0000">*</font></strong></td>
					<td><html:radio property="indicadorGerarDebito" value="1"
						tabindex="11" onclick="desabilitaCamposDebitoCreditoOnClick();" />
					<strong>Sim</strong>&nbsp; <html:radio
						property="indicadorGerarDebito" value="2" tabindex="12"
						onclick="desabilitaCamposDebitoCreditoOnClick();" /> <strong>N&atilde;o</strong>
					</td>
				</tr>
				<tr>
					<td><strong>Gera Cr�dito?:<font color="#FF0000">*</font></strong></td>

					<td><html:radio property="indicadorGerarCredito" value="1"
						tabindex="13" /> <strong>Sim</strong>&nbsp; <html:radio
						property="indicadorGerarCredito" value="2" tabindex="14" /> <strong>N&atilde;o</strong>
					</td>
				</tr>
				<tr>
					<td><strong>Cliente Obrigat�rio?:<font color="#FF0000">*</font></strong></td>

					<td><html:radio property="indicadorCliente" value="1" tabindex="15" />
					<strong>Sim</strong>&nbsp; <html:radio property="indicadorCliente"
						value="2" tabindex="16" /> <strong>N&atilde;o</strong></td>
				</tr>
				
				<tr>
					<td><strong>Existe Verifica��o de D�bito?:<font color="#FF0000">*</font></strong></td>

					<td>
					    <html:radio property="indicadorVerificarDebito" value="1" tabindex="19" />
					    <strong>Sim</strong>&nbsp; <html:radio property="indicadorVerificarDebito"
						 value="2" tabindex="20" /> <strong>N&atilde;o</strong>
					</td>
				</tr>		
				
				<tr>
					<td><strong>Matr�cula do Im�vel Obrigat�ria?:<font color="#FF0000">*</font></strong></td>

					<td><html:radio property="indicadorMatriculaImovel" value="1"
						tabindex="21" onclick="desabilitaCamposMatriculaImovelOnClick();" />
					<strong>Sim</strong>&nbsp; <html:radio
						property="indicadorMatriculaImovel" value="2" tabindex="22"
						onclick="desabilitaCamposMatriculaImovelOnClick();" /> <strong>N&atilde;o</strong>
					</td>
				</tr>
				
				<tr>
					<td><strong>Tipo com Urg�ncia?:<font color="#FF0000">*</font></strong></td>

					<td><html:radio property="indicadorUrgencia" value="1" tabindex="22"/>
					<strong>Sim</strong>&nbsp; <html:radio
						property="indicadorUrgencia" value="2"  /> <strong>N&atilde;o</strong>
					</td>
				</tr>				
				
				<tr>
					<td><strong>Situa��o do Im�vel?:</strong></td>
					<td><html:select property="idSituacaoImovel" tabindex="23">
						<html:option value="">&nbsp;</html:option>
						<html:options collection="colecaoImovelSituacao"
							labelProperty="descricao" property="id" />
					</html:select></td>
				<tr>
				
				
				<tr>
		      	<td><strong>Tipo de D�bito:</strong></td>
		        <td>
					<html:text property="idDebitoTipo" size="4" maxlength="4" tabindex="24" onkeypress="validaEnterComMensagem(event, 'exibirAtualizarAdicionarSolicitacaoEspecificacaoAction.do?objetoConsulta=1', 'idDebitoTipo', 'Tipo de D�bito');"
					onkeyup="document.forms[0].descricaoDebitoTipo.value = '';somente_numero(this);" />
					
					<a href="javascript:javascript:verificarCampoDesabilitado(document.forms[0].idDebitoTipo,'recuperarDadosPesquisarAtualizarAdicionarSolicitacaoEspecificacaoAction.do?caminhoRetornoTelaPesquisaTipoDebito=exibirAtualizarAdicionarSolicitacaoEspecificacaoAction',document.forms[0].descricaoDebitoTipo);">
					<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" height="21" title="Pesquisar" border="0"></a>
		
					<logic:present name="corDebitoTipo">
		
						<logic:equal name="corDebitoTipo" value="exception">
							<html:text property="descricaoDebitoTipo" size="40" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
						</logic:equal>
		
						<logic:notEqual name="corDebitoTipo" value="exception">
							<html:text property="descricaoDebitoTipo" size="40" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
						</logic:notEqual>
		
					</logic:present>
		
					<logic:notPresent name="corDebitoTipo">
		
						<logic:empty name="AtualizarAdicionarSolicitacaoEspecificacaoActionForm" property="idDebitoTipo">
							<html:text property="descricaoDebitoTipo" value="" size="40" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>
						</logic:empty>
						<logic:notEmpty name="AtualizarAdicionarSolicitacaoEspecificacaoActionForm" property="idDebitoTipo">
							<html:text property="descricaoDebitoTipo" size="40" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
						</logic:notEmpty>
						
					</logic:notPresent>
		        	
		        	<a href="javascript:limparPesquisaDebitoTipo();">
		        	<img src="<bean:message key='caminho.imagens'/>limparcampo.gif" title="Apagar" border="0"></a>
		        
		       	</td>
			  	
			  	</tr>
	  
				<tr> 
		          <td><strong>Valor do D�bito:</strong></td>
		          <td> 
		            <strong> 
		              <html:text property="valorDebito" size="14" maxlength="14" tabindex="25"
		              onkeyup="formataValorMonetario(this, 14)"style="text-align:right;"/>
		            </strong> 
		          </td>
		        </tr>
		        
		        <tr>
					<td><strong>Encerramento Autom�tico?:<font color="#FF0000">*</font></strong></td>

					<td>
					    <html:radio property="indicadorEncerramentoAutomatico" value="1"
					    	tabindex="26" /> <strong>Sim</strong>&nbsp;
					    <html:radio property="indicadorEncerramentoAutomatico" value="2"
						  tabindex="27" /> <strong>N&atilde;o</strong>
				   </td>
				</tr>
				
				<tr>
					<td><strong>Indicador Loja Virtual:<font color="#FF0000">*</font></strong></td>
					
					<td>
						<html:radio property="indicadorLojaVirtual" value="1" tabindex="38" /><strong>Sim</strong>&nbsp;
						<html:radio property="indicadorLojaVirtual" value="2" tabindex="39" /><strong>N&atilde;o</strong>
					</td>
				</tr>
		        
		       <!--  RM 5759 -->
		       <tr>
				<td><strong>Permite Cancelar D�bito a Cobrar?:<font color="#FF0000">*</font></strong></td>
				
				<td>
					<html:radio property="indicadorPermiteCancelarDebito" value="1" tabindex="32" /><strong>Sim</strong>&nbsp;
					<html:radio property="indicadorPermiteCancelarDebito" value="2" tabindex="33" /><strong>N&atilde;o</strong>
				</td>
				</tr>  
				
		        <tr>
					<td><strong>Alterar o valor do d�bito?:<font color="#FF0000">*</font></strong></td>

					<td>
					    <html:radio property="indicadorPermiteAlterarValor" value="1"
					    	tabindex="28" /> <strong>Sim</strong>&nbsp;
					    <html:radio property="indicadorPermiteAlterarValor" value="2"
						  tabindex="29" /> <strong>N&atilde;o</strong>
				   </td>
				</tr>
				
				<tr>
					<td><strong>Cobrar juros?:<font color="#FF0000">*</font></strong></td>

					<td>
					    <html:radio property="indicadorCobrarJuros" value="1"
					    	tabindex="30" /> <strong>Sim</strong>&nbsp;
					    <html:radio property="indicadorCobrarJuros" value="2"
						  tabindex="31" /> <strong>N&atilde;o</strong>
				   </td>
				</tr>
				
				<tr>				
					<td><strong>Documento Obrigat�rio:</strong></td>
	
						<td><html:radio property="indicadorDocumentoObrigatorio" value="1"
							tabindex="26" /> <strong>Sim</strong>&nbsp; <html:radio
							property="indicadorDocumentoObrigatorio" value="2" tabindex="27" /> <strong>N&atilde;o</strong>
					</td>				
				</tr>				
				
				 <tr>
				<td><strong>Indica se Tipo Especifica��o Ser� Listada no Popup Consultar Im�vel :<font color="#FF0000">*</font></strong></td>
				
				<td>
					<html:radio property="indicadorTipoEspecificacaoListadoPopupConsultarImovel" value="1" tabindex="32" /><strong>Sim</strong>&nbsp;
					<html:radio property="indicadorTipoEspecificacaoListadoPopupConsultarImovel" value="2" tabindex="33" /><strong>N&atilde;o</strong>
				</td>
				</tr> 
				
				<tr>
					<td width="40%"><strong>Observa��es:</strong></td>
					<td><html:textarea 
						property="observacao" 
						cols="40" 
						rows="7"
						onkeyup="limitTextArea(document.forms[0].observacao, 400, document.getElementById('utilizado'), document.getElementById('limite'));"
						style="resize:none" />
														
						<span id="utilizado">0</span>/<span id="limite">400</span>
					</td>
				</tr>
				
				<tr>
					<td><strong>Unidade Inicial de Tramita��o:</strong></td>
					<td><html:text maxlength="4" property="idUnidadeTramitacao"
						size="4" tabindex="32"
						onkeypress="javascript:validaEnterComMensagem(event, 'exibirAtualizarAdicionarSolicitacaoEspecificacaoAction.do?objetoConsulta=1', 'idUnidadeTramitacao', 'Unidade Tramita��o');"
						onkeyup="document.forms[0].descricaoUnidadeTramitacao.value = '';somente_numero(this);" />
					<a
						href="javascript:verificarCampoDesabilitado(document.forms[0].idUnidadeTramitacao,'recuperarDadosPesquisarAtualizarAdicionarSolicitacaoEspecificacaoAction.do?caminhoRetornoTelaPesquisaUnidadeOrganizacional=exibirAtualizarAdicionarSolicitacaoEspecificacaoAction',document.forms[0].descricaoUnidadeTramitacao);">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Unidade Inicial de Tramita��o" /></a> <logic:present
						name="idUnidadeTramitacaoNaoEncontrado">
						<logic:equal name="idUnidadeTramitacaoNaoEncontrado"
							value="exception">
							<html:text property="descricaoUnidadeTramitacao" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="idUnidadeTramitacaoNaoEncontrado"
							value="exception">
							<html:text property="descricaoUnidadeTramitacao" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent
						name="idUnidadeTramitacaoNaoEncontrado">
						<logic:empty
							name="AtualizarAdicionarSolicitacaoEspecificacaoActionForm"
							property="idUnidadeTramitacao">
							<html:text property="descricaoUnidadeTramitacao" value=""
								size="40" maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty
							name="AtualizarAdicionarSolicitacaoEspecificacaoActionForm"
							property="idUnidadeTramitacao">
							<html:text property="descricaoUnidadeTramitacao" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a
						href="javascript:limparPesquisaUnidadeTramitacao();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				
				<tr>
					<td colspan="2"><hr></td>
				</tr>				
				
				<tr>
					<td colspan="2"><strong>Incluir RA no Encerramento com Especifica��o abaixo:</strong></td>
				</tr>
				
				<tr>
					<td><strong>Tipo de solicita��o:</strong></td>

					<td><html:select property="idTipoSolicitacao" onchange="javascript:statusEspecificacao();">
							<html:option value="">&nbsp;</html:option>
							<html:options collection="colecaoTipoSolicitacao"
								labelProperty="descricao" property="id" />
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td><strong>Especifica��o:</strong></td>

					<td><html:select property="idEspecificacao">
							<html:option value="">&nbsp;</html:option>
							<html:options collection="colecaoEspecificacao"
								labelProperty="descricao" property="id" />
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td colspan="2"><hr></td>
				</tr>			
				
				<tr>
					<td><strong>Gera Ordem de Servi�o?:<font color="#FF0000">*</font></strong></td>

					<td><html:radio property="indicadorGeraOrdemServico" value="1"
						tabindex="33" onclick="desabilitaCampos();" /> <strong>Sim</strong>&nbsp;
					<html:radio property="indicadorGeraOrdemServico" value="2"
						tabindex="34" onclick="desabilitaCampos();" /> <strong>N&atilde;o</strong>
					</td>
				</tr>

				<tr>
					<td><strong>Tipo do Servi�o OS Obrigat�ria:</strong></td>
					<td><html:text maxlength="4" property="idServicoOS" size="4"
						tabindex="35"
						onkeypress="javascript:validaEnterComMensagem(event, 'exibirAtualizarAdicionarSolicitacaoEspecificacaoAction.do?objetoConsulta=1', 'idServicoOS', 'Tipo do Servi�o OS Obrigat�ria');"
						onkeyup="document.forms[0].descricaoServicoOS.value = ''; somente_numero(this);" /> <a
						href="javascript:verificarCampoDesabilitado(document.forms[0].idServicoOS,'recuperarDadosPesquisarAtualizarAdicionarSolicitacaoEspecificacaoAction.do?caminhoRetornoTelaPesquisaTipoServico=exibirAtualizarAdicionarSolicitacaoEspecificacaoAction&limpar=1',document.forms[0].descricaoServicoOS);">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Tipo do Servi�o OS Obrigat�ria" /></a> <logic:present
						name="idServicoOSNaoEncontrado">
						<logic:equal name="idServicoOSNaoEncontrado" value="exception">
							<html:text property="descricaoServicoOS" size="40" maxlength="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
						<logic:notEqual name="idServicoOSNaoEncontrado" value="exception">
							<html:text property="descricaoServicoOS" size="40" maxlength="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="idServicoOSNaoEncontrado">
						<logic:empty
							name="AtualizarAdicionarSolicitacaoEspecificacaoActionForm"
							property="idServicoOS">
							<html:text property="descricaoServicoOS" value="" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty
							name="AtualizarAdicionarSolicitacaoEspecificacaoActionForm"
							property="idServicoOS">
							<html:text property="descricaoServicoOS" size="40" maxlength="40"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
					</logic:notPresent> <a
						href="javascript:limparPesquisaTipoServicoOS();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>

				<tr>
					<td><strong>Tipo do Servi�o OS Poss�veis</strong></td>
					<td align="right"><input type="button" name="adicionarTipoServico"
						class="bottonRightCol" value="Adicionar"
						onClick="javascript:redirecionarSubmit('exibirAtualizarAdicionarSolicitacaoEspecificacaoTipoServicoAction.do?retornarTelaPopup=atualizar&limpaSessao=1');"
						tabindex="36"></td>
				</tr>

				<tr>
					<td colspan="2">
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr bordercolor="#000000">
							<td bgcolor="#90c7fc" align="center" width="15%" height="20">
							<div align="center"><strong>Remover</strong></div>
							</td>
							<td bgcolor="#90c7fc" width="40%"><strong>Tipo de Servi�o</strong></td>
							<td bgcolor="#90c7fc" width="40%"><strong>Ordem de Execu��o</strong></td>
						</tr>
						<logic:present name="colecaoEspecificacaoServicoTipo">
							<tr>
								<td colspan="3">

								<div style="width: 100%; height: 100%; overflow: auto;">
								<table width="100%" bgcolor="#99CCFF">

									<%int cont = 0;%>
									<logic:iterate name="colecaoEspecificacaoServicoTipo"
										id="especificacaoServicoTipo">
										<%cont = cont + 1;
			if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
											<%} else {

			%>
										<tr bgcolor="#FFFFFF">
											<%}%>
											<td width="15%">
											<div align="center"><font color="#333333"> <img width="14"
												height="14" border="0"
												src="<bean:message key="caminho.imagens"/>Error.gif"
												onclick="javascript:if(confirm('Confirma remo��o?')){redirecionarSubmit('removerSolicitacaoEspecificacaoAction.do?codigoSolicitacaoEspecificacao=<bean:write name="especificacaoServicoTipo" property="ultimaAlteracao.time"/>&removerSolicitacaoEspecificacaoAction.do?codigoSolicitacaoEspecificacao=<bean:write name="especificacaoServicoTipo" property="ultimaAlteracao.time"/>&atualizar=sim');}" />
											</font></div>
											</td>
											<td width="40%"><bean:write name="especificacaoServicoTipo"
												property="servicoTipo.descricao" /></td>
											<td width="40%"><bean:write name="especificacaoServicoTipo"
												property="ordemExecucao" /></td>
										</tr>

									</logic:iterate>

								</table>
								</div>
								</td>
							</tr>
						</logic:present>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>		
				
				<tr>
					<td><strong>Informar conta no Registro de Atendimento:<font color="#FF0000">*</font></strong></td>

					<td><html:radio property="indicadorInformarContaRA" value="1"
						tabindex="33" onclick="" /> <strong>Sim</strong>&nbsp;
					<html:radio property="indicadorInformarContaRA" value="2"
						tabindex="34" onclick="" /> <strong>N&atilde;o</strong>
					</td>
				</tr>
				

				<tr>
					<td><strong>Informar Pagamento em Duplicidade no Registro de Atendimento:<font color="#FF0000">*</font></strong></td>

					<td><html:radio property="indicadorInformarPagamentoDP" value="1"
						tabindex="33" onclick="" /> <strong>Sim</strong>&nbsp;
					<html:radio property="indicadorInformarPagamentoDP" value="2"
						tabindex="34" onclick="" /> <strong>N&atilde;o</strong>
					</td>
				</tr>
				
				<tr>
					<td><strong>Alterar Vencimento Alternativo:<font color="#FF0000">*</font></strong></td>

					<td><html:radio property="indicadorAlterarVencimentoAlternativo" value="1"
						tabindex="33" /> <strong>Sim</strong>&nbsp; <html:radio
						property="indicadorAlterarVencimentoAlternativo" value="2" tabindex="34" /> <strong>N&atilde;o</strong>
					</td>
				</tr>
				
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>	
				
				<!-- RM 5924 -->
				<tr>
				<td><strong>Adicionar Procedimento Operacional Padr�o (POP):</strong></td>
				</tr>
				<tr>
		      	<td><input TYPE="file" NAME="arquivo" size="54" />	</td>
		        
					<td align="right"><input type="button" class="bottonRightCol"
					value="Adicionar" tabindex="11" style="width: 80px"
					onclick="javascript:adicionarArquivo();"></td>
				</tr>
				
				
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
				<td colspan="2">
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr bordercolor="#000000">
						<td bgcolor="#90c7fc" align="center" width="20%" height="20">
						<div align="center"><strong>Remover</strong></div>
						</td>
						<td bgcolor="#90c7fc" width="70%"><strong>Descri��o</strong></td>
					</tr>
					
					<logic:present name="colecaoArquivos"> 
						<tr>
							<td colspan="3">
			
							<div style="width: 100%; height: 100%; overflow: auto;">
							<table width="100%" bgcolor="#99CCFF">
			
								<%int cont = 0;%>
								<logic:iterate name="colecaoArquivos" id="arquivos" scope="session">
									<% cont = cont + 1;
									if (cont % 2 == 0) {%>
									<tr bgcolor="#cbe5fe">
									<%} else { %>
									<tr bgcolor="#FFFFFF">
									<%}%>
									<td align="center" width="20%" >
									<a href="javascript:removerArquivo(<bean:write name="arquivos" property="posicao" />)" title="Remover">
										<img src="<bean:message key='caminho.imagens'/>Error.gif" border="0" >
									</a>
									</td>
									
									<td align="center" valign="middle">
									<bean:write name="arquivos" property="nomeArquivo" />	
									</td>
			
								</logic:iterate>
								
							<tr>
							<td colspan="2">&nbsp;</td>
							</tr>
							</table>
							
							</div>
							</td>
						</tr>
					</logic:present>
					<tr>
					<td colspan="2">&nbsp;</td>
					</tr>
				<tr>
				<td colspan="2">
				<hr>
				</td>
				</tr>
					<tr>
					<td colspan="2">&nbsp;</td>
					</tr>
				</table>
				</td>
			</tr>
			<!-- FIM RM 5924 -->
			
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
			</table>
			<table width="100%" border="0">

				<tr>
					<logic:present name="atualizar" scope="request">

						<td valign="top">
							<input name="button" type="button"
								class="bottonRightCol" value="Desfazer"
								onclick="desfazerAtualizar();">&nbsp;
							<input type="button"
								name="ButtonCancelar" class="bottonRightCol" value="Fechar"
								onClick="javascript:window.close();"></td>

						<td valign="top">
						<div align="right">
							<input name="Button" type="button"
								class="bottonRightCol" value="Atualizar"
								onclick="validarForm(document.forms[0]);" tabindex="24"></div>
						</td>
					</logic:present>
					
					<logic:notPresent name="atualizar" scope="request">
						<td valign="top">
							<input name="button" type="button"
								class="bottonRightCol" value="Desfazer" onclick="desfazer();">&nbsp;
							<input
								type="button" name="ButtonCancelar" class="bottonRightCol"
								value="Fechar" onClick="javascript:window.close();"></td>

						<td valign="top">
						<div align="right">
							<input name="Button" type="button"
								class="bottonRightCol" value="Atualizar"
								onclick="validarForm(document.forms[0]);" tabindex="24"></div>
						</td>
					</logic:notPresent>
				</tr>



				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</body>
</html:form>
</html:html>
