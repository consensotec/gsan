<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ page import="gsan.cobranca.CobrancaAtividade" %>
<%@ include file="/jsp/util/telaespera.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="gsan.util.ConstantesSistema"%>
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<%@ include file="/jsp/util/telaespera.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<html:javascript staticJavascript="false"
	formName="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm" />
	
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script type="text/javascript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>		
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm" 
	dynamicJavascript="false" 
	/>
	
<script language="JavaScript">
<!-- Begin

$(document).ready(function() {
	
	validarRelacaoImoveis();
	
	$("input[@name='relacaoImoveis']").change(validarRelacaoImoveis);
	
});

function validarRelacaoImoveis(){
	var form = document.forms[0];
	if($("input[@name='relacaoImoveis']").val() == ''){
		
		$("input[@name='prazoExecucao']").attr('disabled', false);
		$("input[@name='valorLimiteObrigatoria']").attr('disabled', false);
		$("input[@name='indicadorImoveisDebito']").attr('disabled', false);
		$("input[@name='indicadorImoveisDebito']").filter('[value=1]').attr('checked', true);
		$("input[@name='indicadorGerarBoletimCadastro']").attr('disabled', false);
		$("input[@name='numeroQuadraInicial']").attr('disabled', false);
		$("input[@name='numeroQuadraFinal']").attr('disabled', false);
		$("select[@name='clienteRelacaoTipo']").attr('disabled', false);
	    $("select[@name='cobrancaGrupo']").attr('disabled', false);
	    $("input[@name='periodoVencimentoContaInicial']").attr('disabled', false);		        
        $("input[@name='periodoVencimentoContaFinal']").attr('disabled', false);		        
  		$("input[@name='quantidadeDiasVencimento']").attr('disabled', false);
        $("input[@name='periodoInicialConta']").attr('disabled', false);
        $("input[@name='periodoFinalConta']").attr('disabled', false);
        $("input[@name='consumoMedioInicial']").attr('disabled', false);		        
        $("input[@name='consumoMedioFinal']").attr('disabled', false);		 
        $("input[@name='periodoInicialFiscalizacao']").attr('disabled', false);		        
        $("input[@name='periodoFinalFiscalizacao']").attr('disabled', false);		        
        $("select[@name='situacaoFiscalizacao']").attr('disabled', false);

		$("input[@name='periodoVencimentoContaFinal']").val($("input[@name='periodoVencimentoContaFinalHidden']").val());
		$("input[@name='periodoFinalFiscalizacao']").val($("input[@name='periodoFinalFiscalizacaoHidden']").val());
		$("input[@name='periodoFinalConta']").val($("input[@name='periodoFinalContaHidden']").val());
		
		
		habilitar(document.forms[0].cobrancaAtividadeIndicadorExecucao.value);
		validarCliente(0);
		validarClienteSuperior(1);
		desabilitarLocalidadeCobrancaAcao();
		validarGrupoCobrancaParaBloquear();
		validarGerenciaRegionalParaBloquear();
		validarUnidadeNegocioParaBloquear();
		verificarCampoPrazoExecucao();
		validarClienteParaBloquear();
		validarHabilitarCampo();
		validarLogradouroParaBloquear();
	}
	else{

		if($("input[@name='relacaoImoveis']").val() != ''){
			form.idImovel.value ='';
			form.descricaoImovel.value='';
			form.idImovel.disabled = true;
			form.descricaoImovel.disabled=true;

		}
		
		form.Button32232.disabled = true;
		$("select[@name='gerenciaRegional']").attr('disabled', true);	
		$("select[@name='unidadeNegocio']").attr('disabled', true);
    	$("input[@name='localidadeOrigemID']").attr('disabled', true);
		$("input[@name='localidadeDestinoID']").attr('disabled', true);
        $("input[@name='setorComercialOrigemCD']").attr('disabled', true);
        $("input[@name='setorComercialDestinoCD']").attr('disabled', true);
        $("input[@name='imagem']").attr('disabled', true);
	    if(form.rotaInicial != undefined){
    	   		$("input[@name='rotaInicial']").attr('disabled', true);
    	   		$("input[@name='rotaFinal']").attr('disabled', true);
	    }
        
        $("input[@name='idCliente']").attr('disabled', true);
        $("select[@name='clienteRelacaoTipo']").attr('disabled', true);
        $("select[@name='cobrancaGrupo']").attr('disabled', true);
        
        
        $("input[@name='quantidadeMaximaDocumentos']").attr('disabled', true);
        $("input[@name='valorLimiteObrigatoria']").attr('disabled', true);		         
        $("input[@name='indicadorImoveisDebito']").attr('disabled', true);		        
        $("input[@name='indicadorGerarBoletimCadastro']").attr('disabled', true);		        
        $("input[@name='numeroQuadraInicial']").attr('disabled', true);		        
        $("input[@name='numeroQuadraFinal']").attr('disabled', true);		        
        $("input[@name='codigoClienteSuperior']").attr('disabled', true);		        
        $("input[@name='periodoVencimentoContaInicial']").attr('disabled', true);		        
        $("input[@name='periodoVencimentoContaFinal']").attr('disabled', true);		        
  		$("input[@name='quantidadeDiasVencimento']").attr('disabled', true);
        $("input[@name='consumoMedioInicial']").attr('disabled', true);		        
        $("input[@name='consumoMedioFinal']").attr('disabled', true);		        
        $("input[@name='periodoInicialFiscalizacao']").attr('disabled', true);		        
        $("input[@name='periodoFinalFiscalizacao']").attr('disabled', true);		        
        $("select[@name='situacaoFiscalizacao']").attr('disabled', true);		            
        $("input[@name='logradouroId']").attr('disabled', true);
        $("input[@name='periodoInicialConta']").attr('disabled', true);
        $("input[@name='periodoFinalConta']").attr('disabled', true);
        $("select[@name='tipoConsumo']").attr('disabled', true);
		$("select[@name='gerenciaRegional']").val('-1');	
		$("select[@name='unidadeNegocio']").val('-1');
    	$("input[@name='localidadeOrigemID']").val('');
		$("input[@name='localidadeDestinoID']").val('');
        $("input[@name='setorComercialOrigemCD']").val('');
        $("input[@name='setorComercialDestinoCD']").val('');
	    if(form.rotaInicial != undefined){
    	   		$("input[@name='rotaInicial']").val('');
    	   		$("input[@name='rotaFinal']").val('');
	    }
        $("input[@name='idCliente']").val('');
        $("select[@name='clienteRelacaoTipo']").val('-1');
        $("select[@name='cobrancaGrupo']").val('-1');
        
        
        $("input[@name='quantidadeMaximaDocumentos']").val('');
        $("input[@name='valorLimiteObrigatoria']").val('');
        $("input[@name='indicadorImoveisDebito']").filter('[value=2]').attr('checked', true);      
        $("input[@name='indicadorGerarBoletimCadastro']").filter('[value=2]').attr('checked', true);	        
        $("input[@name='numeroQuadraInicial']").val('');	        
        $("input[@name='numeroQuadraFinal']").val('');   
        $("input[@name='codigoClienteSuperior']").val('');    
        $("input[@name='periodoVencimentoContaInicial']").val(''); 
        $("input[@name='periodoVencimentoContaFinal']").val('');              
  		$("input[@name='quantidadeDiasVencimento']").val('');
        $("input[@name='consumoMedioInicial']").val(''); 
        $("input[@name='consumoMedioFinal']").val('');      
        $("input[@name='periodoInicialFiscalizacao']").val('');       
        $("input[@name='periodoFinalFiscalizacao']").val('');            
        $("select[@name='situacaoFiscalizacao']").val('-1');           
        $("input[@name='logradouroId']").val('');
        $("input[@name='periodoInicialConta']").val('');
        $("input[@name='periodoFinalConta']").val('');
        $("select[@name='tipoConsumo']").val('-1');
        $("input[@name='nomeLocalidadeOrigem']").val('');
        $("input[@name='nomeLocalidadeDestino']").val('');
        $("input[@name='nomeSetorComercialOrigem']").val('');
        $("input[@name='nomeSetorComercialDestino']").val('');
        $("input[@name='logradouroDescricao']").val('');
        $("input[@name='nomeCliente']").val('');
        $("input[@name='nomeClienteSuperior']").val(''); 
           
        
	}
}



	function caracteresespeciais () {
    	this.aa = new Array("titulo", "T�tulo possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    	this.ab = new Array("localidadeOrigemID", "Localidade Inicial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     	this.ac = new Array("localidadeDestinoID", "Localidade Final possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     	this.ad = new Array("setorComercialOrigemCD", "Setor Comercial Inicial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     	this.ae = new Array("setorComercialDestinoCD", "Setor Comercial Final possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     	this.af = new Array("idCliente", "Cliente possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     	
     	this.aw = new Array("descricaoSolicitacao", "Descri��o Solicitacao n�o permite caracteres especiais.", new Function ("varName", " return this[varName];"));
     	this.ax = new Array("idImovel", "Matr�cula do Im�vel n�o permite caracteres especiais.", new Function ("varname", "return this[varName];"));
     	this.ay = new Array("quantidadeMaximaDocumentos", "Quantidade M�xima de Documentos n�o permite caracteres especiais.", new Function ("varname", "return this[varName];"));
     	this.ak = new Array("valorLimiteObrigatoria", "Valor Limite para Emiss�o Obrigat�ria n�o permite caracteres especiais.", new Function ("varname", "return this[varName];"));
     	this.az = new Array("codigoClienteSuperior", "C�digo do Cliente Superior n�o permite caracteres especiais.", new Function ("varname", "return this[varName];"));
     	this.ba = new Array("consumoMedioInicial", "Consumo M�dio do Im�vel inicial n�o permite caracteres especiais.", new Function ("varname", "return this[varName];"));
     	this.bb = new Array("consumoMedioFinal", "Consumo M�dio do Im�vel final n�o permite caracteres especiais.", new Function ("varname", "return this[varName];"));
     	this.bc = new Array("logradouroId", "Logradouro n�o permite caracteres especiais.", new Function ("varname", "return this[varName];"));
     	
     	
     }

    function IntegerValidations () {
    	this.ag = new Array("prazoExecucao", "Prazo de Execu��o deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
    	this.ah = new Array("quantidadeMaximaDocumentos", "Quantidade m�xima de documentos deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
    	this.ai = new Array("localidadeOrigemID", "Localidade Inicial deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
     	this.aj = new Array("localidadeDestinoID", "Localidade Final deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
     	this.al = new Array("setorComercialOrigemCD", "Setor Comercial Inicial deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
     	this.am = new Array("setorComercialDestinoCD", "Setor Comercial Final deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
     	this.an = new Array("idCliente", "Cliente deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
     	this.ao = new Array("idImovel", "Matr�cula do Im�vel deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
     	this.ap = new Array("rotaFinal", "Rota Final deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
     	this.aq = new Array("rotaInicial", "Rota Inicial deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
     	/*this.af = new Array("valorLimiteObrigatoria", "Valor Limite para Emiss�o Obrigat�ria deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));*/
     	
    }
    
    function FloatValidations () {
	 	this.ar = new Array("valorLimiteObrigatoria", "Valor Limite para Emiss�o Obrigat�ria deve somente conter n�meros decimais positivos.",new Function ("varName", " return this[varName];"));
	}

    function required () {
    	this.as = new Array("titulo", "Informe T�tulo.", new Function ("varName", " return this[varName];"));
     	this.at = new Array("descricaoSolicitacao", "Informe Descri��o Solicita��o.", new Function ("varName", " return this[varName];"));     	
     	this.au = new Array("cobrancaAcao", "Informe A��o de Cobran�a.", new Function ("varName", " return this[varName];"));
     	this.av = new Array("cobrancaAtividade", "Informe Atividade de Cobran�a.", new Function ("varName", " return this[varName];"));    	
    }    

	function validar(){

   		var form = document.forms[0];
	   	if(form.cobrancaAcao.value == "" || form.cobrancaAcao.value == "-1"){
	   		alert("Informe A��o de Cobran�a.");
	   		return false;
	   	}
   		
		return true;
	}

  	function validarCliente(apagar){
    	var form = document.forms[0];
    	if (form.idCliente.value != ''){
      		if(apagar == 1) form.clienteRelacaoTipo.value = "";	
      		form.clienteRelacaoTipo.disabled = false;
      		form.codigoClienteSuperior.value = "";
      		form.codigoClienteSuperior.disabled = true;
    	}else{
      		form.clienteRelacaoTipo.disabled = true;
      		form.codigoClienteSuperior.disabled = false;
   	 	}
  	}
  	
  	 

	function desabilitarCobrancaAcao(){
  		var form = document.forms[0];
  		
  		if (form.localidadeOrigemID.value != ''  ){
			form.nomeLocalidadeOrigem.value = "";	
	      	form.cobrancaGrupo.value = "";	
	      	form.cobrancaGrupo.disabled = true;
	      	form.gerenciaRegional.disabled = true;
	      	form.unidadeNegocio.disabled = true;
	      	//form.nomeCliente.value = ""
        	form.idCliente.value = ""
	        form.idCliente.disabled = true;
	        form.clienteRelacaoTipo.value = "";
	        form.clienteRelacaoTipo.disabled = true;
	        //form.logradouroId.value = "";
        	form.logradouroId.disabled = true;
        	//form.logradouroDescricao.value = "";
        	form.logradouroDescricao.disabled = true;      	
  		}else{
	     	form.cobrancaGrupo.disabled = false;
		    form.gerenciaRegional.disabled = false;
		    form.unidadeNegocio.disabled = false;      
		    form.idCliente.disabled = false;
		    form.clienteRelacaoTipo.disabled = false;  
		    //form.logradouroId.value = "";
        	form.logradouroId.disabled = false;
        	//form.logradouroDescricao.value = "";
        	form.logradouroDescricao.disabled = false;    
  		}
	}

	function desabilitarLocalidadeCobrancaAcao(){
	  	var form = document.forms[0];
	  	if (form.idCliente.value != '' || form.codigoClienteSuperior.value != '' ){
	
	      	form.cobrancaGrupo.value = "";	
	      	form.cobrancaGrupo.disabled = true;
	      	form.localidadeOrigemID.disabled = true;
	      	form.localidadeDestinoID.disabled = true;
		  	form.setorComercialOrigemCD.disabled = true;
	      	form.setorComercialDestinoCD.disabled = true;
	      	form.gerenciaRegional.disabled = true;
	      	form.unidadeNegocio.disabled = true;
	      	form.rotaInicial.disabled = true;
	      	form.rotaFinal.disabled = true;    
	      	//form.logradouroId.value = "";  
	      	form.logradouroId.disabled = true;
	      	//form.logradouroDescricao.value = "";
	      	form.logradouroDescricao.disabled = true;
	  	}else{
	      	form.cobrancaGrupo.disabled = false;
	      	form.localidadeOrigemID.disabled = false;
	      	form.localidadeDestinoID.disabled = false;
		  	form.setorComercialOrigemCD.disabled = false;
	      	form.setorComercialDestinoCD.disabled = false;
	      	form.gerenciaRegional.disabled = false;
	      	form.unidadeNegocio.disabled = false;  
	      	form.rotaInicial.disabled = false;
	      	form.rotaFinal.disabled = false;    
	      	//form.logradouroId.value = "";  
	      	form.logradouroId.disabled = false;
	      	//form.logradouroDescricao.value = "";
	      	form.logradouroDescricao.disabled = false;
	  	}
	  	verificarPreenchimentoImovel();
	}

	function verificarCobrancaAtividade(){
		///document.forms[0].cobrancaAtividade.options[document.forms[0].cobrancaAtividade.selectedIndex].id
    	var form = document.forms[0];
		if(form.cobrancaAtividade.value == 1){
			///		form.executarComando.disabled = true;	
		}else{
		}
	}

	function executarcomando(){
	
		var form = document.forms[0];
	
		if(validateRequired(form) && testarCampoValorZero(document.InserirComandoAcaoCobrancaEventualCriterioRotaActionForm.localidadeOrigemID, 'Localidade Inicial.') 
	    && testarCampoValorZero(document.InserirComandoAcaoCobrancaEventualCriterioRotaActionForm.localidadeDestinoID, 'Localidade Final.') 
		&& testarCampoValorZero(document.InserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setorComercialOrigemCD, 'Setor Comercial Inicial.') 
		&& testarCampoValorZero(document.InserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setorComercialDestinoCD, 'Setor Comercial Final.') 
	 	&& testarCampoValorZero(document.InserirComandoAcaoCobrancaEventualCriterioRotaActionForm.idCliente, 'Cliente.') 
	    && validateCaracterEspecial(form) 
		&& validateLong(form) 
		&& verificaAnoMesMensagemPersonalizada(form.periodoInicialConta,"M�s/Ano Inicial do Per�odo de Refer�ncia das Contas inv�lido.")
		&& verificaAnoMesMensagemPersonalizada(form.periodoFinalConta,"M�s/Ano Final do Per�odo de Refer�ncia das Contas inv�lido.")
		&& verificaDataMensagemPersonalizada(form.periodoVencimentoContaInicial,"Data Inicial do Per�odo de Vencimento das Contas inv�lida.")
		&& verificaDataMensagemPersonalizada(form.periodoVencimentoContaFinal,"Data Final do Per�odo de Vencimento das Contas inv�lida.")
		&& validarLocalidadeDiferentes()
		&& validarSetoresComerciaisDiferentes()
		&& validarQuadraDiferentes()
		&& validarRotasDiferentes()
		&& verificarSetoresComerciaisMenores()
		&& verificarLocalidadeMenores()
		&& tratarExecutarComando()){	

		    //form.action =  '/gsan/inserirComandoAcaoCobrancaEventualExecutarComandoAction.do'
			//form.submit();
		    if (validarLimite()){
		    	botaoAvancarTelaEspera('/gsan/inserirComandoAcaoCobrancaEventualExecutarComandoAction.do');
		    }
		}
	}

	function concluircomando(){
		var form = document.forms[0];
	
		if(validateRequired(form) && testarCampoValorZero(document.InserirComandoAcaoCobrancaEventualCriterioRotaActionForm.localidadeOrigemID, 'Localidade Inicial.') 
	    && testarCampoValorZero(document.InserirComandoAcaoCobrancaEventualCriterioRotaActionForm.localidadeDestinoID, 'Localidade Final.') 
		&& testarCampoValorZero(document.InserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setorComercialOrigemCD, 'Setor Comercial Inicial.') 
		&& testarCampoValorZero(document.InserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setorComercialDestinoCD, 'Setor Comercial Final.') 
	 	&& testarCampoValorZero(document.InserirComandoAcaoCobrancaEventualCriterioRotaActionForm.idCliente, 'Cliente.') 
	    && validateCaracterEspecial(form)
		&& validateLong(form) 
		&& verificaAnoMesMensagemPersonalizada(form.periodoInicialConta,"M�s/Ano Inicial do Per�odo de Refer�ncia das Contas inv�lido.")
		&& verificaAnoMesMensagemPersonalizada(form.periodoFinalConta,"M�s/Ano Final do Per�odo de Refer�ncia das Contas inv�lido.")
		&& verificaDataMensagemPersonalizada(form.periodoVencimentoContaInicial,"Data Inicial do Per�odo de Vencimento das Contas inv�lida.")
		&& verificaDataMensagemPersonalizada(form.periodoVencimentoContaFinal,"Data Final do Per�odo de Vencimento das Contas inv�lida.")
		&& validarLocalidadeDiferentes()
		&& validarSetoresComerciaisDiferentes()
		&& validarQuadraDiferentes()
		&& validarRotasDiferentes()
		&& verificarSetoresComerciaisMenores()
		&& verificarLocalidadeMenores()
		&& validarInscricoes() && verificarAtividadeEmitir() && validar()){
		
			//form.action =  '/gsan/inserirComandoAcaoCobrancaEventualConcluirAction.do'
	    	//form.submit();
			if (validarLimite()){
				botaoAvancarTelaEspera('/gsan/inserirComandoAcaoCobrancaEventualConcluirAction.do');
			}
			
		}
	}

function validarLimite(){
	var form = document.forms[0];
	var valor = "string";
	valor = form.valorLimiteObrigatoria.value;
	var tamanho = valor.length;
	
	if (tamanho > 9){
		alert('Valor Limite para Emiss�o Obrigat�ria deve possuir at� 5 casas decimais.');
		form.valorLimiteObrigatoria.focus();
		return false;
	}
	return true;
}
	
	function tratarExecutarComando(){
		var form = document.forms[0];
		if(form.cobrancaAtividade.value == 3){
			alert("O Caso de Uso - Encerrar Atividade de A��o de Cobran�a n�o foi implementado para esta itera��o.")
			form.cobrancaAtividade.focus();
			return false;
		}
		return true;
	}

	function validarLocalidadeDiferentes(){
		var form = document.forms[0];
	
		if(form.localidadeOrigemID.value != '' && form.localidadeDestinoID.value == ''){
			alert("Informe Localidade Final.");
			form.localidadeDestinoID.focus();
			return false;
		}
		if(form.localidadeOrigemID.value == '' && form.localidadeDestinoID.value != ''){
			alert("Informe Localidade Inicial.");
			form.localidadeOrigemID.focus();
			return false;
		}
		return true;
	}

function validarSetoresComerciaisDiferentes(){
	var form = document.forms[0];
	
	if(form.setorComercialOrigemCD.value!= '' && form.setorComercialDestinoCD.value == ''){
		alert("Informe Setor Comercial Final.");
		form.setorComercialDestinoCD.focus();
		return false;
	}
	if(form.setorComercialOrigemCD.value == '' && form.setorComercialDestinoCD.value != ''){
		alert("Informe Setor Comercial Inicial.");
		form.setorComercialOrigemCD.focus();
		return false;
	}
	return true;
}

function validarQuadraDiferentes(){
	var form = document.forms[0];
	
	if(form.numeroQuadraInicial.value!= '' && form.numeroQuadraFinal.value == ''){
		alert("Informe Quadra Final.");
		form.numeroQuadraFinal.focus();
		return false;
	}
	if(form.numeroQuadraInicial.value == '' && form.numeroQuadraFinal.value != ''){
		alert("Informe Quadra Inicial.");
		form.numeroQuadraInicial.focus();
		return false;
	}
	
	if(form.numeroQuadraInicial.value != '' && form.setorComercialOrigemCD.value == ''){
		alert("Informe Setor Comercial Inicial.");
		form.setorComercialOrigemCD.focus();
		return false;
	}

	if(form.numeroQuadraFinal.value != '' && form.setorComercialDestinoCD.value == ''){
		alert("Informe Setor Comercial Final.");
		form.setorComercialDestinoCD.focus();
		return false;
	}
	
	return true;
}

function validarRotasDiferentes(){
	var form = document.forms[0];
	
    if(form.rotaInicial != undefined){
		if(form.rotaInicial.value!= '' && form.rotaFinal.value == ''){
			alert("Informe Rota Final.");
			form.rotaFinal.focus();
			return false;
		}
		if(form.rotaInicial.value == '' && form.rotaFinal.value != ''){
			alert("Informe Rota Inicial.");
			form.rotaInicial.focus();
			return false;
		}
	}
	return true;
}

function verificarSetoresComerciaisMenores(){
	
	var form = document.forms[0];
	if(form.setorComercialOrigemCD.value != '' && form.setorComercialDestinoCD.value != ''){
		if(form.setorComercialDestinoCD.value < form.setorComercialOrigemCD.value){
			alert("Setor Comercial Final menor que Setor Comercial Inicial.");	
			form.setorComercialDestinoCD.focus();
			return false;
		}else{
			return true;
		}
	
	}else{
		return true;
	}
}

function verificarLocalidadeMenores(){
	var form = document.forms[0];
	
	if(form.localidadeOrigemID.value != '' && form.localidadeDestinoID.value != ''){
		if(form.localidadeDestinoID.value < form.localidadeOrigemID.value){
			alert("Localidade Final menor que Localidade Inicial.");	
			form.localidadeDestinoID.focus();
			return false;
		}else{
			return true;
		}
	
	}else{
		return true;
	}
}



function validarCriterios(valor){

	var form = document.forms[0];

	if(valor == 1){ //validar a��o cobran�a
		form.action =  '/gsan/exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do?validarCriterio=SIM&validar=Acao'
    	form.submit();
    }else if(valor == 2){//validar cobran�a atividade
		form.action =  '/gsan/exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do?validarCriterio=SIM&validar=Atividade'
    	form.submit();
    	
    }
}

function verificar(){

	var form = document.forms[0];
	
	if(form.cobrancaAtividadeIndicadorExecucao.value != 1){
///		form.executarComando.disabled = true;	
	}else if(form.cobrancaAtividadeIndicadorExecucao.value != 1){
	///	form.executarComando.disabled = false;	
	}
}

function habilitar(valor){

	var form = document.forms[0];
	form.cobrancaAtividadeIndicadorExecucao.value = valor;
	if(valor == 1){//indicador de rota
		form.concluir.disabled = false;
		form.Avancar.disabled = true;
		form.indicador[0].checked = true;
	}else if(valor == 2){//indicador de comando
		form.indicador[1].checked = true;
		form.concluir.disabled  = true;
		form.Avancar.disabled = false;		
	}else if(valor == ""){
		form.concluir.disabled = false;
		form.Avancar.disabled = true;
		form.indicador[0].checked = true;
	}
}

function desabilitarCobrancaGrupo(){
	
	var form = document.forms[0];
	form.cobrancaGrupo.disabled = true;
	form.cobrancaGrupo.value = "";
}

function validarGrupoCobranca(visulizar){

	var form = document.forms[0];
	
///	if(form.cobrancaGrupo.value != ""){
		var ok = true;
		if(form.gerenciaRegional.value != "-1"){
			ok = false;
		}

		if(form.unidadeNegocio.value != "-1"){
			ok = false;
		}

		if(form.localidadeOrigemID.value != ""){
			ok = false;
		}
		if(form.localidadeDestinoID.value != ""){
			ok = false;
		}
		if(ok == true){
			if(form.cobrancaGrupo.value != ""){
				form.gerenciaRegional.disabled = true;
				form.unidadeNegocio.disabled = true;				
		    	form.localidadeOrigemID.disabled = true;
				form.localidadeDestinoID.disabled = true;
		        form.setorComercialOrigemCD.disabled = true;
		        form.setorComercialDestinoCD.disabled = true;
		        form.imagem.enabled = true;
			    if(form.rotaInicial != undefined){
		    	   		form.rotaInicial.disabled = true;
		    	   		form.rotaFinal.disabled = true;
			    }
			}else{
				form.gerenciaRegional.disabled = false;
				form.unidadeNegocio.disabled = false;				
		    	form.localidadeOrigemID.disabled = false;
				form.localidadeDestinoID.disabled = false;
		        form.setorComercialOrigemCD.disabled = false;
		        form.setorComercialDestinoCD.disabled = false;
		        form.imagem.enabled = false;
			    if(form.rotaInicial != undefined){
		    	   		form.rotaInicial.disabled = false;
		    	   		form.rotaFinal.disabled = false;
			    }	
			}
		}else{
			if(visulizar == 1){
				alert("Ger�ncia Regional/Localidade/Setor Comercial/Rota informados");
				form.cobrancaGrupo.value = "";
			}
		}
	///}	
}


function validarGrupoCobrancaSelecionado(){

	var form = document.forms[0];
	
	if(form.cobrancaGrupo.value != "-1"){
		form.gerenciaRegional.disabled = true;
		form.unidadeNegocio.disabled = true;		
    	form.localidadeOrigemID.disabled = true;
		form.localidadeDestinoID.disabled = true;
        form.setorComercialOrigemCD.disabled = true;
        form.setorComercialDestinoCD.disabled = true;
        form.imagem.enabled = true;
	    if(form.rotaInicial != undefined){
    	   		form.rotaInicial.disabled = true;
    	   		form.rotaFinal.disabled = true;
	    }

        form.nomeCliente.value = ""
        form.idCliente.value = ""
        form.idCliente.disabled = true;
        form.clienteRelacaoTipo.value = "";
        form.clienteRelacaoTipo.disabled = true;
        form.logradouroId.value = "";
        form.logradouroId.disabled = true;
        form.logradouroDescricao.value = "";
        form.logradouroDescricao.disabled = true;
        form.numeroQuadraInicial.readOnly = true;
        form.numeroQuadraFinal.readOnly = true;
        form.numeroQuadraInicial.value = "";
        form.numeroQuadraFinal.value = "";
        
	}else{
		form.gerenciaRegional.disabled = false;
		form.unidadeNegocio.disabled = false;		
    	form.localidadeOrigemID.disabled = false;
		form.localidadeDestinoID.disabled = false;
        form.setorComercialOrigemCD.disabled = false;
        form.setorComercialDestinoCD.disabled = false;
        form.imagem.enabled = false;
	    if(form.rotaInicial != undefined){
    	   		form.rotaInicial.disabled = false;
    	   		form.rotaFinal.disabled = false;
	    }	
        form.idCliente.value = ""
        form.idCliente.disabled = false;
        form.clienteRelacaoTipo.value = "";
        form.clienteRelacaoTipo.disabled = false;
        //form.logradouroId.value = "";
        form.logradouroId.disabled = false;
        //form.logradouroDescricao.value = "";
        form.logradouroDescricao.disabled = false;
        form.numeroQuadraInicial.readOnly = false;
        form.numeroQuadraFinal.readOnly = false;
	}
}



function validarLogradouroSelecionado(){

	var form = document.forms[0];
	form.logradouroDescricao.value = '';
	
	if(form.logradouroId.value != ''){
		form.gerenciaRegional.disabled = true;
		form.unidadeNegocio.disabled = true;		
    	form.localidadeOrigemID.disabled = true;
		form.localidadeDestinoID.disabled = true;
        form.setorComercialOrigemCD.disabled = true;
        form.setorComercialDestinoCD.disabled = true;
        form.imagem.enabled = true;
	    if(form.rotaInicial != undefined){
    	   		form.rotaInicial.disabled = true;
    	   		form.rotaFinal.disabled = true;
	    }
        form.nomeCliente.value = ""
        form.idCliente.value = ""
        form.idCliente.disabled = true;
        form.clienteRelacaoTipo.value = "";
        form.clienteRelacaoTipo.disabled = true;
        form.cobrancaGrupo.value != "-1";
        form.cobrancaGrupo.disabled = true;
        
	}else{
		form.gerenciaRegional.disabled = false;
		form.unidadeNegocio.disabled = false;		
    	form.localidadeOrigemID.disabled = false;
		form.localidadeDestinoID.disabled = false;
        form.setorComercialOrigemCD.disabled = false;
        form.setorComercialDestinoCD.disabled = false;
        form.imagem.enabled = false;
	    if(form.rotaInicial != undefined){
    	   		form.rotaInicial.disabled = false;
    	   		form.rotaFinal.disabled = false;
	    }	
        form.nomeCliente.value = ""
        form.idCliente.value = ""
        form.idCliente.disabled = false;
        form.clienteRelacaoTipo.value = "";
        form.clienteRelacaoTipo.disabled = false;
        form.cobrancaGrupo.value != "-1";
        form.cobrancaGrupo.disabled = false;
	}
}


function validarGerenciaRegionalSelecionado(){

	var form = document.forms[0];
	
	if(form.gerenciaRegional.value != "-1"){
		form.cobrancaGrupo.disabled = true;
    	form.localidadeOrigemID.disabled = true;
		form.localidadeDestinoID.disabled = true;
        form.setorComercialOrigemCD.disabled = true;
        form.setorComercialDestinoCD.disabled = true;
        form.unidadeNegocio.disabled = true;        
        form.imagem.enabled = true;
	    if(form.rotaInicial != undefined){
    	   		form.rotaInicial.disabled = true;
    	   		form.rotaFinal.disabled = true;
	    }
	    form.nomeCliente.value = ""
	    form.idCliente.value = ""
	    form.idCliente.disabled = true;
	    form.clienteRelacaoTipo.value = "";
	    form.clienteRelacaoTipo.disabled = true;
        form.logradouroId.value = "";
        form.logradouroId.disabled = true;
        form.logradouroDescricao.value = "";
        form.logradouroDescricao.disabled = true;
        form.numeroQuadraInicial.readOnly = true;
        form.numeroQuadraFinal.readOnly = true;
        form.numeroQuadraInicial.value = "";
        form.numeroQuadraFinal.value = "";
        
	}else{
		form.cobrancaGrupo.disabled = false;
    	form.localidadeOrigemID.disabled = false;
		form.localidadeDestinoID.disabled = false;
        form.setorComercialOrigemCD.disabled = false;
        form.setorComercialDestinoCD.disabled = false;
        form.unidadeNegocio.disabled = false;        
        form.imagem.enabled = false;
	    if(form.rotaInicial != undefined){
    	   		form.rotaInicial.disabled = false;
    	   		form.rotaFinal.disabled = false;
	    }	
	    form.idCliente.value = ""
        form.idCliente.disabled = false;
        form.clienteRelacaoTipo.value = "";
        form.clienteRelacaoTipo.disabled = false;
        form.logradouroId.disabled = false;  
        form.logradouroDescricao.disabled = false;
   		form.numeroQuadraInicial.readOnly = false;
        form.numeroQuadraFinal.readOnly = false;
	}
}

function validarUnidadeNegocioSelecionado(){

	var form = document.forms[0];
	
	if(form.unidadeNegocio.value != "-1"){
		form.cobrancaGrupo.disabled = true;
    	form.localidadeOrigemID.disabled = true;
		form.localidadeDestinoID.disabled = true;
        form.setorComercialOrigemCD.disabled = true;
        form.setorComercialDestinoCD.disabled = true;
		form.gerenciaRegional.disabled = true;
        form.imagem.enabled = true;
	    if(form.rotaInicial != undefined){
    	   		form.rotaInicial.disabled = true;
    	   		form.rotaFinal.disabled = true;
	    }
	    form.nomeCliente.value = ""
        form.idCliente.value = ""
        form.idCliente.disabled = true;
        form.clienteRelacaoTipo.value = "";
        form.clienteRelacaoTipo.disabled = true;
        form.logradouroId.value = "";
        form.logradouroId.disabled = true;
        form.logradouroDescricao.value = "";
        form.logradouroDescricao.disabled = true;
        form.numeroQuadraInicial.readOnly = true;
        form.numeroQuadraFinal.readOnly = true;
        form.numeroQuadraInicial.value = "";
        form.numeroQuadraFinal.value = "";
	}else{
		form.cobrancaGrupo.disabled = false;
    	form.localidadeOrigemID.disabled = false;
		form.localidadeDestinoID.disabled = false;
        form.setorComercialOrigemCD.disabled = false;
        form.setorComercialDestinoCD.disabled = false;
   		form.gerenciaRegional.disabled = false;
        form.imagem.enabled = false;
	    if(form.rotaInicial != undefined){
    	   		form.rotaInicial.disabled = false;
    	   		form.rotaFinal.disabled = false;
	    }	
	    form.idCliente.value = ""
        form.idCliente.disabled = false;
        form.clienteRelacaoTipo.value = "";
        form.clienteRelacaoTipo.disabled = false;
        form.logradouroId.value = "";
        form.logradouroId.disabled = false;
        form.logradouroDescricao.disabled = false;
   		form.numeroQuadraInicial.readOnly = false;
        form.numeroQuadraFinal.readOnly = false;
	}
}


function validarGrupoCobrancaParaBloquear(){

	var form = document.forms[0];
	
		var ok = 1;
		if(form.localidadeOrigemID.value != ""){
			ok = 2;
		}
		if(form.localidadeDestinoID.value != ""){
			ok = 2;
		}
		if(ok == 2){
			form.cobrancaGrupo.disabled = true;
		    form.gerenciaRegional.disabled = true;
		    form.unidadeNegocio.disabled = true;		    
        	form.nomeCliente.value = ""
        	form.idCliente.value = ""
        	form.idCliente.disabled = true;
        	form.clienteRelacaoTipo.value = "";
        	form.clienteRelacaoTipo.disabled = true;
        	form.logradouroId.value = "";
       		form.logradouroId.disabled = true;
        	form.logradouroDescricao.value = "";
       		form.logradouroDescricao.disabled = true;
		}
}

function validarClienteParaBloquear(){

	var form = document.forms[0];
	
		if(form.idCliente.value != ""){
			form.cobrancaGrupo.disabled = true;
		    form.gerenciaRegional.disabled = true;
		    form.unidadeNegocio.disabled = true;		    
	    	form.localidadeOrigemID.disabled = true;
			form.localidadeDestinoID.disabled = true;
        	form.setorComercialOrigemCD.disabled = true;
	        form.setorComercialDestinoCD.disabled = true;
    	    form.imagem.enabled = true;
	    	if(form.rotaInicial != undefined){
    	   		form.rotaInicial.disabled = true;
    	   		form.rotaFinal.disabled = true;
		    }
		}
}

function validarGerenciaRegionalParaBloquear(){

	var form = document.forms[0];
	
		var ok = 1;
		if(form.localidadeOrigemID.value != ""){
			ok = 2;
		}
		if(form.localidadeDestinoID.value != ""){
			ok = 2;
		}
		if(form.idCliente.value != ""){
			ok = 2;
		}
		if(form.codigoClienteSuperior.value != ""){
		  ok = 2;
		}
		if(form.unidadeNegocio.value != "-1"){
			ok = 2;
		}
		if(form.cobrancaGrupo.value != "-1"){
			ok = 2;
		}
		if(ok == 1){
			form.cobrancaGrupo.disabled = true;
		    form.unidadeNegocio.disabled = true;		    
			form.localidadeOrigemID.disabled = true;
			form.localidadeDestinoID.disabled = true;
			form.setorComercialOrigemCD.disabled = true;
		    form.setorComercialDestinoCD.disabled = true;
		    form.rotaInicial.disabled = true;
    	    form.rotaFinal.disabled = true;
        	form.nomeCliente.value = ""
        	form.idCliente.value = ""
        	form.idCliente.disabled = true;
        	form.clienteRelacaoTipo.value = "";
        	form.clienteRelacaoTipo.disabled = true;
		}
}

function validarLogradouroParaBloquear(){

	var form = document.forms[0];
		if(form.logradouroId.value != ""){
		var ok = 1;
		if(form.localidadeOrigemID.value != ""){
			ok = 2;
		}
		if(form.localidadeDestinoID.value != ""){
			ok = 2;
		}
		if(form.idCliente.value != ""){
			ok = 2;
		}
		if(form.codigoClienteSuperior.value != ""){
		  ok = 2;
		}
		if(form.unidadeNegocio.value != "-1"){
			ok = 2;
		}
		if(form.gerenciaRegional.value != "-1"){
			ok = 2;
		}
		if(form.cobrancaGrupo.value != "-1"){
			ok = 2;
		}
		if(ok == 1){
			form.cobrancaGrupo.disabled = true;
			form.gerenciaRegional.disabled = true;
		    form.unidadeNegocio.disabled = true;		    
			form.localidadeOrigemID.disabled = true;
			form.localidadeDestinoID.disabled = true;
			form.setorComercialOrigemCD.disabled = true;
		    form.setorComercialDestinoCD.disabled = true;
		    form.rotaInicial.disabled = true;
    	    form.rotaFinal.disabled = true;
        	form.nomeCliente.value = ""
        	form.idCliente.value = ""
        	form.idCliente.disabled = true;
        	form.clienteRelacaoTipo.value = "";
        	form.clienteRelacaoTipo.disabled = true;
		}
		}
}


function validarUnidadeNegocioParaBloquear(){

	var form = document.forms[0];
	
		var ok = 1;
		if(form.localidadeOrigemID.value != ""){
			ok = 2;
		}
		if(form.localidadeDestinoID.value != ""){
			ok = 2;
		}
		if(form.idCliente.value != ""){
			ok = 2;
		}
		if(form.codigoClienteSuperior.value != ""){
		  ok = 2;
		}
		if(form.gerenciaRegional.value != "-1"){
			ok = 2;
		}
		if(form.cobrancaGrupo.value != "-1"){
			ok = 2;
		}
		if(ok == 1){
			form.cobrancaGrupo.disabled = true;
		    form.gerenciaRegional.disabled = true;		    
			form.localidadeOrigemID.disabled = true;
			form.localidadeDestinoID.disabled = true;
			form.setorComercialOrigemCD.disabled = true;
		    form.setorComercialDestinoCD.disabled = true;
		    form.rotaInicial.disabled = true;
    	    form.rotaFinal.disabled = true;
        	form.nomeCliente.value = ""
        	form.idCliente.value = ""
        	form.idCliente.disabled = true;
        	form.clienteRelacaoTipo.value = "";
        	form.clienteRelacaoTipo.disabled = true;
		}
}

function validarHabilitarCampo(){

	var form = document.forms[0];
	
		var ok = 1;
		if(form.localidadeOrigemID.value != ""){
			ok = 2;
		}
		if(form.localidadeDestinoID.value != ""){
			ok = 2;
		}
		if(form.idCliente.value != ""){
			ok = 2;
		}
		if(form.codigoClienteSuperior.value != ""){
		  ok = 2;
		}
		if(form.gerenciaRegional.value != "-1"){
			ok = 2;
		}
		if(form.cobrancaGrupo.value != "-1"){
			ok = 2;
		}
		if(form.unidadeNegocio.value != "-1"){
			ok = 2;
		}
		if(form.logradouroId.value != ""){
			ok = 2;
		}
		
		if(ok == 1){
			form.cobrancaGrupo.disabled = false;
		    form.gerenciaRegional.disabled = false;		    
		    form.unidadeNegocio.disabled = false;		    
			form.localidadeOrigemID.disabled = false;
			form.localidadeDestinoID.disabled = false;
			form.setorComercialOrigemCD.disabled = false;
		    form.setorComercialDestinoCD.disabled = false;
		    form.rotaInicial.disabled = false;
    	    form.rotaFinal.disabled = false;
        	form.nomeCliente.value = ""
        	form.idCliente.value = ""
        	form.idCliente.disabled = false;
        	form.clienteRelacaoTipo.value = "";
        	form.clienteRelacaoTipo.disabled = false;
            form.logradouroId.disabled = false;
            form.logradouroDescricao.disabled = false;
		}
}


function validarLocalidade(){

	var form = document.forms[0];

	if(form.localidadeOrigemID.value != form.localidadeDestinoID.value){
		
	    form.setorComercialDestinoCD.value = "";
	    form.setorComercialDestinoID.value = "";		   
 	    form.nomeSetorComercialDestino.value = "";
		form.setorComercialOrigemCD.value = "";
		form.setorComercialOrigemID.value = "";
		form.nomeSetorComercialOrigem.value = "";
		alert("Para informar o Setor Comercial, a Localidade Inicial e Final precisam ser iguais.");
	}

}




function limparDestino(tipo){

	var form = document.forms[0];

	switch(tipo){
		case 1: //De localidade pra baixo
			form.localidadeDestinoID.value = "";
			form.nomeLocalidadeDestino.value = "";		
			form.setorComercialOrigemCD.value = "";
			form.setorComercialOrigemID.value = "";
			form.nomeSetorComercialOrigem.value = "";
		    form.setorComercialDestinoCD.value = "";
		    desabilitarCobrancaAcao();
		    break;
		case 2: //De setor para baixo
   	       form.setorComercialDestinoCD.value = "";
		   form.setorComercialDestinoID.value = "";
		   form.nomeSetorComercialOrigem.value = "";
		   form.nomeSetorComercialDestino.value = "";		   
	   	   form.rotaInicial.value = "";
   	   	   form.rotaFinal.value = "";
   	   	   break;
		case 3: //Imovel			
			form.descricaoImovel.value = "";
			verificarPreenchimentoImovel();
			break;
		case 4: //C�digo do Cliente Superior			
			form.nomeClienteSuperior.value = "";
			break;
		case 5: //Cliente		
			form.nomeCliente.value = "";
			form.nomeClienteSuperior.value = "";
			break;
		case 6: //Logradouro			
			//form.logradouroDescricao.value = "";
			break;
	}
}

function limparOrigem(tipo){
	var form = document.forms[0];

	switch(tipo){
		case 1: //De localidara pra baixo
			
			form.cobrancaGrupo.disabled = false;
			form.gerenciaRegional.disabled = false;
			form.unidadeNegocio.disabled = false;			
	        form.idCliente.disabled = false;
    	    form.clienteRelacaoTipo.disabled = true;			
			
			form.localidadeOrigemID.value = "";
			form.nomeLocalidadeOrigem.value = "";
			
			form.localidadeDestinoID.value = "";
			form.nomeLocalidadeDestino.value = "";
		case 2: //De setor para baixo
		   form.setorComercialOrigemCD.value = "";
		   form.setorComercialOrigemID.value = "";
		   form.nomeSetorComercialOrigem.value = "";
	   
		   form.setorComercialDestinoCD.value = "";
		   form.setorComercialDestinoID.value = "";		   
		   form.nomeSetorComercialDestino.value = "";
		   form.rotaInicial.value = "";
   		   form.rotaFinal.value = "";		   
	}
}

function limparBorrachaDestino(tipo){
	var form = document.forms[0];

	switch(tipo){
		case 1: //De localidade pra baixo
			form.localidadeDestinoID.value = "";
			form.nomeLocalidadeDestino.value = "";					
			form.setorComercialOrigemCD.value = "";
			form.setorComercialOrigemID.value = "";
			form.nomeSetorComercialOrigem.value = "";
    		form.setorComercialDestinoCD.value = "";
    		desabilitaIntervaloDiferente(1);
		case 2: //De setor para baixo		   
		   form.setorComercialDestinoID.value = ""; 
		   form.nomeSetorComercialDestino.value = "";		   
   		   form.setorComercialDestinoCD.value = "";
		   form.rotaInicial.value = "";
   		   form.rotaFinal.value = "";
	}
}

function habilitarRota(){
	var form = document.forms[0];

	if(form.setorComercialDestinoCD.value == form.setorComercialOrigemCD.value){
	   form.rotaInicial.value = "";
 	   form.rotaFinal.value = "";
	   form.rotaInicial.disabled = false;
 	   form.rotaFinal.disabled = false;	
	}else{	
	   form.rotaInicial.value = "";
 	   form.rotaFinal.value = "";
	   form.rotaInicial.disabled = true;
   	   form.rotaFinal.disabled = true;	

	}
}


function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {

	var form = document.forms[0];

	if (tipoConsulta == 'setorComercialOrigem') {
      form.setorComercialOrigemCD.value = codigoRegistro;
      form.setorComercialOrigemID.value = idRegistro;
      form.action = 'exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do?objetoConsulta=2&inscricaoTipo=origem'
      form.submit();
      
	  form.nomeSetorComercialOrigem.value = descricaoRegistro;
	  form.nomeSetorComercialOrigem.style.color = "#000000";
	  if(form.setorComercialDestinoCD.value == ""){
	      form.setorComercialDestinoCD.value = codigoRegistro;
    	  form.setorComercialDestinoID.value = idRegistro;
		  form.nomeSetorComercialDestino.value = descricaoRegistro;
		  form.nomeSetorComercialDestino.style.color = "#000000"; 
	  }
	  
	}

	if (tipoConsulta == 'setorComercialDestino') {
	
//		if(form.setorComercialOrigemID.value != ""){
///			if(form.setorComercialOrigemCD.value < codigoRegistro){
	///			alert("Setor Comercial Final Maior que a Inicial");
		///	}else{
	///	}else{
	      form.setorComercialDestinoCD.value = codigoRegistro;
    	  form.setorComercialDestinoID.value = idRegistro;
      form.action = 'exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do?objetoConsulta=2&inscricaoTipo=destino'
      form.submit();

		  form.nomeSetorComercialDestino.value = descricaoRegistro;
		  form.nomeSetorComercialDestino.style.color = "#000000"; 
///	  }
	}

}


function validarPeriodoVencimentoConta(){
	
	if($("input[@name='periodoVencimentoContaFinal']").val() == '' && 
			$("input[@name='periodoVencimentoContaInicial']").val() != ''){
		
		alert('Informe Per�odo de Refer�ncia das Contas Final');
		return false;
	}
	
	if($("input[@name='periodoVencimentoContaFinal']").val() != '' && 
			$("input[@name='periodoVencimentoContaInicial']").val() == ''){
		
		alert('Informe Per�odo de Refer�ncia das Contas Inicial');
		return false;
	}
	
	else{
		return true;
	}
	
}

function validarPeriodoFiscalizacaoImovel(){
	if($("input[@name='periodoFinalFiscalizacao']").val() == '' && 
			$("input[@name='periodoInicialFiscalizacao']").val() != ''){
		
		alert('Informe Per�odo de Fiscaliza��o do Im�vel Final');
		return false;
	}
	
	if($("input[@name='periodoFinalFiscalizacao']").val() != '' && 
			$("input[@name='periodoInicialFiscalizacao']").val() == ''){
		
		alert('Informe Per�odo de Fiscaliza��o do Im�vel Inicial');
		return false;
	}
	
	else{
		return true;
	}
}

function validarPeriodoConta(){
	
	if($("input[@name='periodoFinalConta']").val() == '' && 
			$("input[@name='periodoInicialConta']").val() != ''){
		
		alert('Informe Per�odo de Vencimento das Contas Final');
		return false;
	}
	
	if($("input[@name='periodoFinalConta']").val() != '' && 
			$("input[@name='periodoInicialConta']").val() == ''){
		
		alert('Informe Per�odo de Vencimento das Contas Inicial');
		return false;
	}
	
	else{
		return true;
	}
	
}


	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    	var form = document.forms[0];
    	if (tipoConsulta == 'cobrancaAcaoAtividadeComando') {
      		form.action = 'exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do?idCobrancaAcaoAtividadeComando='+codigoRegistro
      		form.submit();
    	}

		if (tipoConsulta == 'localidadeOrigem') {
      		form.localidadeOrigemID.value = codigoRegistro;
	  		form.nomeLocalidadeOrigem.value = descricaoRegistro;
      		form.action = 'exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do?objetoConsulta=1&inscricaoTipo=origem'
      		form.submit();
	  		form.nomeLocalidadeOrigem.style.color = "#000000";
	  		if(form.localidadeDestinoID.value == ""){
	      		form.localidadeDestinoID.value = codigoRegistro;
		  		form.nomeLocalidadeDestino.value = descricaoRegistro;
		  		form.nomeLocalidadeDestino.style.color = "#000000";
	  		}
		}

		if (tipoConsulta == 'localidade') {
			
			if(form.tipoLocalidade.value == 'I'){
	      		
	      		form.localidadeOrigemID.value = codigoRegistro;
		  		form.nomeLocalidadeOrigem.value = descricaoRegistro;
		  		form.nomeLocalidadeOrigem.style.color = "#000000";
		  		
	      		form.action = 'exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do?objetoConsulta=1&inscricaoTipo=origem'
	      		form.submit();

		  		if(form.localidadeDestinoID.value == ""){
		      		form.localidadeDestinoID.value = codigoRegistro;
			  		form.nomeLocalidadeDestino.value = descricaoRegistro;
			  		form.nomeLocalidadeDestino.style.color = "#000000";
		  		}
		  		
			}else{

    			form.localidadeDestinoID.value = codigoRegistro;
	    		form.nomeLocalidadeDestino.value = descricaoRegistro;
	 			form.nomeLocalidadeDestino.style.color = "#000000";    		
        	
	        	form.action = 'exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do?objetoConsulta=1&inscricaoTipo=destino'
	        	form.submit();
	    		
 			}
		}
	
     	if (tipoConsulta == 'cliente') {
	 	if (form.tipoPesquisa.value != null && form.tipoPesquisa.value == 'clienteSuperior') {
	 		form.codigoClienteSuperior.value = codigoRegistro;
      		form.nomeClienteSuperior.value = descricaoRegistro;
      		form.nomeClienteSuperior.style.color = "#000000";
      		validarClienteSuperior(1);	
      		desabilitarLocalidadeCobrancaAcao();
	 	} else {
	        form.idCliente.value = codigoRegistro;
      		form.nomeCliente.value = descricaoRegistro;
      		form.nomeCliente.style.color = "#000000";
     	 	form.clienteRelacaoTipo.disabled = false;
     	 	validarCliente(1);	
     	 	desabilitarLocalidadeCobrancaAcao()
        }
        
     }
     
      if (tipoConsulta == 'logradouro') {
  	      	form.logradouroId.value = codigoRegistro;
	      	form.logradouroDescricao.value = descricaoRegistro;
	      	form.logradouroDescricao.style.color = '#000000';
	      	
	      	validarLogradouroSelecionado();
	  } else if (tipoConsulta == 'imovel') {
	      	form.idImovel.value = codigoRegistro;
	      	form.descricaoImovel.value = descricaoRegistro;
	      	
	      	form.action = 'exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do?objetoConsulta=5';
	      	form.submit();
	  }	

}


function receberRota(codigoRota,destino) {
 	  var form = document.forms[0];
	if(destino == "inicial"){
		form.rotaInicial.value = codigoRota;		
		form.rotaFinal.value = codigoRota;				
	}else if(destino == "final"){
		form.rotaFinal.value = codigoRota;		
	}

}

function avancar(){
	var form = document.forms[0]; 
		
	if(validateRequired(form) && testarCampoValorZero(document.InserirComandoAcaoCobrancaEventualCriterioRotaActionForm.localidadeOrigemID, 'Localidade Inicial.') 
	    && testarCampoValorZero(document.InserirComandoAcaoCobrancaEventualCriterioRotaActionForm.localidadeDestinoID, 'Localidade Final.') 
		&& testarCampoValorZero(document.InserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setorComercialOrigemCD, 'Setor Comercial Inicial.') 
		&& testarCampoValorZero(document.InserirComandoAcaoCobrancaEventualCriterioRotaActionForm.setorComercialDestinoCD, 'Setor Comercial Final.') 
	 	&& testarCampoValorZero(document.InserirComandoAcaoCobrancaEventualCriterioRotaActionForm.idCliente, 'Cliente.') 
	    && validateCaracterEspecial(form) 
		&& validateLong(form) 
		&& verificaAnoMesMensagemPersonalizada(form.periodoInicialConta,"M�s/Ano Inicial do Per�odo de Refer�ncia das Contas inv�lido.")
		&& verificaAnoMesMensagemPersonalizada(form.periodoFinalConta,"M�s/Ano Final do Per�odo de Refer�ncia das Contas inv�lido.")
		&& verificaDataMensagemPersonalizada(form.periodoVencimentoContaInicial,"Data Inicial do Per�odo de Vencimento das Contas inv�lida.")
		&& verificaDataMensagemPersonalizada(form.periodoVencimentoContaFinal,"Data Final do Per�odo de Vencimento das Contas inv�lida.")
		&& validarLocalidadeDiferentes()
		&& validarSetoresComerciaisDiferentes()
		&& validarRotasDiferentes()
		&& verificarSetoresComerciaisMenores()
		&& verificarLocalidadeMenores()
		&& validarInscricoes() && validar() && verificarAtividadeEmitir()){	
			form.action =  '/gsan/exibirInserirComandoAcaoCobrancaEventualCriterioComandoAction.do?idCobrancaAtividade='+form.cobrancaAtividade.value+'&idCobrancaAcao='+form.cobrancaAcao.value
   			form.submit();
   	}
}

function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){


	if(objetoRelacionado.disabled != true){
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

function voltar(){
	var formulario = document.forms[0]; 
	formulario.action =  'exibirInserirComandoAcaoCobrancaAction.do?menu=sim'
	formulario.submit();
}

function mensagem(mensagem){
	if(mensagem.length > 0){
		alert(mensagem);
	}
}

function validarExistenciaLocalidadeInicial(){
	var form = document.forms[0]; 
	if(form.localidadeOrigemID.value == ""){
		form.localidadeDestinoID.value = "";
///		alert("Informe a Localidade Inicial.");
		form.localidadeOrigemID.focus();
		
		
		
	}
}

function validarExistenciaSetorComercialInicial(){
	var form = document.forms[0]; 
	if(form.setorComercialOrigemCD.value == ""){
		form.setorComercialDestinoCD.value = "";
		alert("Informe Setor Comercial Inicial.");
		form.setorComercialOrigemCD.focus();
	}
}


function habilitacaoCampoRota(){

	var form = document.forms[0];
	
	var setorComercialOrigem = trim(form.setorComercialOrigemCD.value);
	var setorComercialDestino = trim(form.setorComercialDestinoCD.value);
	
	if (setorComercialOrigem.length > 0 && setorComercialDestino.length > 0){
		
		if (setorComercialOrigem != setorComercialDestino){
			form.rotaInicial.disabled = true;
			form.rotaFinal.disabled = true;
		}
		else{
			form.rotaInicial.disabled = false;
			form.rotaFinal.disabled = false;
		}
		
	}
}

function replicarSetorComercial(){
	var form = document.forms[0];
	if(form.setorComercialDestinoCD.value == ""){
		form.setorComercialDestinoCD.value = form.setorComercialOrigemCD.value;
	}	
}

function replicarLocalidade(){
	var form = document.forms[0];
	if(form.localidadeDestinoID.value == ""){
		form.localidadeDestinoID.value = form.localidadeOrigemID.value;	
	}
	
}


function desabilitaIntervaloDiferente(tipo){
	var form = document.forms[0];
	
	switch(tipo){
		case 1: //De localidade 
		    if(form.localidadeOrigemID.value != form.localidadeDestinoID.value){
		        form.setorComercialOrigemCD.value = "";
			 	form.setorComercialDestinoCD.value = "";
		        form.setorComercialOrigemCD.disabled = true;
			 	form.setorComercialDestinoCD.disabled = true;
			 	form.nomeSetorComercialOrigem.value = "";
			 	form.nomeSetorComercialDestino.value = "";
			 	form.nomeLocalidadeDestino.value = "";
			    if(form.rotaInicial != undefined){
				  	form.rotaInicial.disabled = true;
				 	form.rotaFinal.disabled = true;
             	}
			 
			  }else{
		        form.setorComercialOrigemCD.disabled = false;
			 	form.setorComercialDestinoCD.disabled = false;
			    if(form.rotaInicial != undefined){
				  	form.rotaInicial.disabled = false;
				 	form.rotaFinal.disabled = false;
             	}
			  }
			break;					
		case 2: //De setor Comercial		   
		    if(form.setorComercialOrigemCD.value != form.setorComercialDestinoCD.value){
			    if(form.rotaInicial != undefined){
				  	form.rotaInicial.disabled = true;
				 	form.rotaFinal.disabled = true;
             	}
			    form.nomeSetorComercialDestino.value = "";
  			  }else{
				   if(form.rotaInicial != undefined){
					  	form.rotaInicial.disabled = false;
			 			form.rotaFinal.disabled = false;
			 		}
			  }
			break;
		case 3://De Rota 

		   if(form.rotaInicial != undefined){
		   
		     if(form.rotaInicial.value != form.rotaFinal.value){
			  	form.rotaInicial.disabled = true;
			 	form.rotaFinal.disabled = true;
             	form.rotaInicial.value = "";
             	form.rotaFinal.value = "";
			  }else{
			  	form.rotaInicial.disabled = false;
			 	form.rotaFinal.disabled = false;
			  }

			}
			break;
		}
}

function validarAbrirPoupPesquisarCliente(){
	var form = document.forms[0];	
							
	if(form.idCliente.disabled == false){
	    form.tipoPesquisa.value = 'cliente';
	    chamarPopup('exibirPesquisarClienteAction.do?indicadorUsoTodos=1&limparForm=OK', 'cliente', null, null, 275, 480, '',form.idCliente.value);
	}
	
}



function validarLimparCliente(){
	var form = document.forms[0];						
	if(form.idCliente.disabled == false){
		limparCliente();
	}
}






function duplicarRota(){
	var formulario = document.forms[0]; 
	formulario.rotaFinal.value = formulario.rotaInicial.value;
}  
function duplicarQuadra(){
	var form = document.forms[0]; 
	form.numeroQuadraFinal.value = form.numeroQuadraInicial.value;	
}
function carregarRotaInicial(){

	var formulario = document.forms[0]; 
	
	if(formulario.setorComercialOrigemCD.value!= '' && formulario.setorComercialDestinoCD.value != ''){
		if(formulario.rotaInicial.options.length == 1){
	   		formulario.action =  '/gsan/exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do?rota=CARREGAR'
   			formulario.submit();				
		}
	}
	
}

function limparRota(){
	var formulario = document.forms[0]; 
	formulario.action =  '/gsan/exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do?limparRota=CARREGAR'
	formulario.submit();	
}

function carregarRotaFinal(){
	var formulario = document.forms[0]; 
	if(formulario.setorComercialOrigemCD.value!= '' && formulario.setorComercialDestinoCD.value != ''){
		if(formulario.rotaFinal.options.length == 1){
	   		formulario.action =  '/gsan/exibirFiltrarComandosAcaoCobrancaEventualAction.do?rota=CARREGAR'
   			formulario.submit();				
		}
	}
	
}

function validarPreenchimento(){

	var formulario = document.forms[0]; 
    if(formulario.localidadeOrigemID.value == ''){

    } 
    if(formulario.localidadeDestinoID.value == ''){

    }
	if(formulario.setorComercialOrigemCD.value == ''){

	}
	if(formulario.setorComercialDestinoCD.value == ''){

	}

}

function validarSetorFinal(){
	var formulario = document.forms[0]; 
	if(formulario.setorComercialDestinoCD.value == ''){
///		limparRota();
	}
}

function validarInscricoes(){
   var form = document.forms[0];
   var msg = '';
   if(form.setorComercialOrigemCD.value != "" && form.localidadeOrigemID.value == ""){
	   msg = 'Informe Localidade Inicial.\n';
   }
   if(form.setorComercialDestinoCD.value != "" && form.localidadeDestinoID.value == ""){
	   msg = msg+ 'Informe Localidade Final.\n';
   }
   if(form.rotaInicial.value != "" && form.setorComercialOrigemCD.value == ""){
	   msg = msg +'Informe Setor Comercial Inicial.\n';
   }
   if(form.rotaFinal.value != "" &&form.setorComercialDestinoCD.value == ""){
	   msg = msg+ 'Informe Setor Comercial Final.\n';
   }

   if( msg != '' ){
   		alert(msg);
		return false;
   }else{
		return true;
   }
}


function pesquisarRota(destino){
   var form = document.forms[0];
   var msg = '';
   if(form.localidadeOrigemID.value == ""){
	   msg = 'Informe Localidade Inicial.\n';
   }
   if(form.localidadeDestinoID.value == ""){
	   msg = msg+ 'Informe Localidade Final.\n';
   }
   if(form.setorComercialOrigemCD.value == ""){
	   msg = msg +'Informe Setor Comercial Inicial.\n';
   }
   if(form.setorComercialDestinoCD.value == ""){
	   msg = msg+ 'Informe Setor Comercial Final.\n';
   }

   if( msg != '' ){

   }else{
		var msgDois = '';
		
	   if(form.localidadeOrigemID.value != form.localidadeDestinoID.value){
		   msgDois = 'Localidades diferentes.\n';
	   }
	   if(form.setorComercialOrigemCD.value != form.setorComercialDestinoCD.value){
		   msgDois = msgDois + 'Setores Comeriais diferentes.\n';
	   }
	   if( msgDois != '' ){
		}else{
			abrirPopup('exibirPesquisarRotaAction.do?idLocalidade='+form.localidadeOrigemID.value+'&codigoSetorComercial='+form.setorComercialOrigemCD.value+'&destino='+destino, 250, 495);
		}
	}
}

function verificarAtividadeEmitir(){
var form = document.forms[0];
 if(form.cobrancaAtividade.value == form.idAtividadeCobrancaEmitir.value){
  if(form.prazoExecucao.value == ''){
   alert("Informe Prazo de Execu��o.");
   return false;
  }else{
   return true;
  }
 }else{
  return true;
 }
}

function verificarCampoPrazoExecucao(){
var form = document.forms[0];
 if(form.cobrancaAtividade != null && form.cobrancaAtividade.value != form.idAtividadeCobrancaEmitir.value){
  form.prazoExecucao.value = '';
  form.prazoExecucao.disabled = true; 
 }else{
  form.prazoExecucao.disabled = false; 
 }
}

function  validarAbrirPoupPesquisarClienteSuperior() {
	var form = document.forms[0];

	if(form.codigoClienteSuperior.disabled == false){

		if (form.codigoClienteSuperior.readOnly == false) {
			form.tipoPesquisa.value = 'clienteSuperior';
			chamarPopup('exibirPesquisarClienteAction.do?indicadorUsoTodos=1&limparForm=OK', 'clienteSuperior', null, null, 275, 480, '',form.codigoClienteSuperior.value);
		}	

	}

	
}

function validarLimparClienteSuperior(){
	var form = document.forms[0];						
	if(form.codigoClienteSuperior.disabled == false){
		limparClienteSuperior();
	}
}

function limparClienteSuperior(){
  	  var form = document.forms[0];
      form.nomeClienteSuperior.value = ""
      form.codigoClienteSuperior.value = ""
      form.nomeCliente.value = "";
      form.idCliente.disabled = false;
}


function limparCliente(){
  	  var form = document.forms[0];
       form.nomeCliente.value = ""
       form.idCliente.value = ""
       form.clienteRelacaoTipo.value = "";
       form.clienteRelacaoTipo.disabled = true;
       form.nomeClienteSuperior.value = "";
       form.codigoClienteSuperior.disabled = false;
}

function validarClienteSuperior(apagar){
    	var form = document.forms[0];
    	if (form.codigoClienteSuperior.value != ''){
      		if(apagar == 1) {
      			form.clienteRelacaoTipo.value = "";      			
      		}
      		form.clienteRelacaoTipo.disabled = true;
      		form.idCliente.disabled = true;
      		form.idCliente.value = "";
      		form.nomeCliente.value = "";      		
    	}else{
      		form.clienteRelacaoTipo.disabled = false;
      		form.idCliente.disabled = false;
   	 	}
  	}

/* Limpar Logradouro */
function limparLogradouro() {
		var form = document.forms[0];
		
      	form.logradouroId.value = '';
	    form.logradouroDescricao.value = '';
	
		form.logradouroId.focus();
}

function validarExibicaoTipoCosumo(){

	var form = document.forms[0];

	if(form.consumoMedioInicial.value!="" && form.consumoMedioFinal.value!=""){
		form.tipoConsumo.disabled = false;
	}else{
		form.tipoConsumo.value = '1';
		form.tipoConsumo.disabled = true;;
	}
	
}

	
	function validarQuadra(nomeCampo, idDependencia, nomeDependencia){
      
		var form = document.forms[0];
		
		var objetoCampo = nomeCampo;
		var valorCampo = nomeCampo.value;
	
		if(idDependencia.length < 1 || isNaN(idDependencia) || ((idDependencia * 1) == 0) ||
		   idDependencia.indexOf(',') != -1 || idDependencia.indexOf('.') != -1 || ((idDependencia * 1) == 0)){
			
			alert('Informe ' + nomeDependencia);
			form.setorComercialOrigemCD.focus();
			
		}
	}


	function limparDescricao(campo){
		campo.value = "";
	}
	
function verificarPreenchimentoImovel(){
	//Se imovel esta preenchido desabilita campos
	var form = document.forms[0];
	if (form.idImovel.value != ''){
		desabilitarCampos(true);
		limparCamposImovel();
	} else {
		desabilitarCampos(false);	
	}
}

function desabilitarCampos(booleano){
	var form = document.forms[0];	

	form.cobrancaGrupo.disabled = booleano;
	form.gerenciaRegional.disabled = booleano;
	form.unidadeNegocio.disabled = booleano;
	form.localidadeOrigemID.disabled = booleano;
	form.setorComercialOrigemCD.disabled = booleano;
	form.numeroQuadraInicial.disabled = booleano;
	form.rotaInicial.disabled = booleano;
	form.localidadeDestinoID.disabled = booleano;
	form.setorComercialDestinoCD.disabled = booleano;
	form.numeroQuadraFinal.disabled = booleano;
	form.rotaFinal.disabled = booleano;
	form.codigoClienteSuperior.disabled = booleano;
	form.idCliente.disabled = booleano;
	form.clienteRelacaoTipo.disabled = booleano;
	form.logradouroId.disabled = booleano;	
}
function limparCamposImovel(){
	var form = document.forms[0];

	form.cobrancaGrupo.value='';
	form.gerenciaRegional.value='';
	form.unidadeNegocio.value='';
	form.localidadeOrigemID.value='';
	form.nomeLocalidadeOrigem.value='';
	form.setorComercialOrigemCD.value='';
	form.nomeSetorComercialOrigem.value='';
	form.numeroQuadraInicial.value='';
	form.rotaInicial.value='';
	form.localidadeDestinoID.value='';
	form.nomeLocalidadeDestino.value='';
	form.setorComercialDestinoCD.value='';
	form.nomeSetorComercialDestino.value='';
	form.numeroQuadraFinal.value='';
	form.rotaFinal.value='';
	form.codigoClienteSuperior.value='';
	form.nomeClienteSuperior.value='';
	form.idCliente.value='';
	form.nomeCliente.value='';
	form.clienteRelacaoTipo.value='';
	form.logradouroId.value='';
	form.logradouroDescricao.value='';
}
function limparImovel(){
	var form = document.forms[0];
	form.idImovel.value = '';
	form.descricaoImovel.value = '';
	verificarPreenchimentoImovel();
}
function validateInserirComandoAcaoCobrancaEventualCriterioRotaActionForm(){return true;}
function habilitarPesquisaImovel(form) {
	if (form.idImovel.disabled == false) {
		chamarPopup('exibirPesquisarImovelAction.do', 'imovel', null, null, 275, 480, '',form.idImovel.value);
	}	
}

function habilitarPesquisarSetorComercialInicial(){

	var form = document.forms[0];

		if (form.setorComercialOrigemCD.disabled == false) {
			abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+form.localidadeOrigemID.value+'&tipo=setorComercialOrigem&indicadorUsoTodos=1',form.localidadeOrigemID.value,'Localidade da inscri��o de origem', 400, 800);
		}
		
}

function habilitarPesquisaCalendarioPeriodoVencimentoInicial(){

	var form = document.forms[0];

	if(form.periodoVencimentoContaInicial.disabled == false){

		abrirCalendario('InserirComandoAcaoCobrancaEventualCriterioRotaActionForm', 'periodoVencimentoContaInicial');

		}

	}

function habilitarPesquisaCalendarioPeriodoVencimentoFinal(){

	var form = document.forms[0];
	
	if(form.periodoVencimentoContaFinal.disabled == false){

		abrirCalendario('InserirComandoAcaoCobrancaEventualCriterioRotaActionForm', 'periodoVencimentoContaFinal');


		}
	}

function habilitarPesquisarCalendarioPeriodoFiscalizacaoInicial(){

	var form = document.forms[0];

	if(form.periodoInicialFiscalizacao.disabled == false){
		abrirCalendario('InserirComandoAcaoCobrancaEventualCriterioRotaActionForm', 'periodoInicialFiscalizacao');
	}
	
}

function habilitarPesquisarCalendarioPeriodoFiscalizacaoFinal(){

	var form = document.forms[0];

	if(form.periodoFinalFiscalizacao.disabled == false){
		abrirCalendario('InserirComandoAcaoCobrancaEventualCriterioRotaActionForm', 'periodoFinalFiscalizacao');
	}
	
}


-->
</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:mensagem('${requestScope.mensagem}');setarFoco('${requestScope.nomeCampo}');iniciar();">
<div id="formDiv">
<html:form action="/exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do"
   name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm"
   type="gsan.gui.cobranca.InserirComandoAcaoCobrancaEventualCriterioRotaActionForm"
   method="post"
   onsubmit="return validateInserirComandoAcaoCobrancaEventualCriterioRotaActionForm(this);"
   enctype="multipart/form-data">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<input type="hidden" name="tipoLocalidade" />
	
	<input type="hidden" name="idAtividadeCobrancaEmitir" value= "<%=CobrancaAtividade.EMITIR %>"/>
	<input type="hidden" name="tipoPesquisa" />
	<input type="hidden" name="cancelarValidacao" value="true" />
	
	<input type="hidden" name="periodoFinalContaHidden" value='<bean:write name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm" property="periodoFinalConta"/>' />
	<input type="hidden" name="periodoVencimentoContaFinalHidden" value='<bean:write name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm" property="periodoVencimentoContaFinal"/>' />
	<input type="hidden" name="periodoFinalFiscalizacaoHidden" value='<bean:write name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm" property="periodoFinalFiscalizacao"/>' />
	

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


			<td valign="top" class="centercoltext"><!--In�cio Tabela Reference a P�gina��o da Tela de Processo-->
			<!--In�cio Tabela Reference a P�gina��o da Tela de Processo-->
			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Inserir Comando de A&ccedil;&atilde;o de
					Cobran&ccedil;a - Grupo de Cobran�a</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a P�gina��o da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0"  cellpadding="0" cellspacing="3">
				<tr>
					<td colspan="3">Para determinar a a&ccedil;&atilde;o de
					cobran&ccedil;a a ser comandada, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td width="16%"><strong>T�tulo:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="titulo" size= "60" maxlength="100" 
					tabindex="1"/></td>
				</tr>
				<tr>
					<td width="16%"><strong>Descri��o da Solicita��o:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:textarea property="descricaoSolicitacao" 
					rows="10" cols="50" tabindex="2"/></td>
				</tr>
				<tr>
					<td width="16%"><strong>A&ccedil;&atilde;o de Cobran&ccedil;a:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:select property="cobrancaAcao" style="height: 100px" multiple="true" tabindex="3">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:present scope="session" name="colecaoCobrancaAcao">
							<html:options name="session" collection="colecaoCobrancaAcao"
								labelProperty="descricaoCobrancaAcao" property="id" />
						</logic:present>
					</html:select></td>
				</tr>
				<tr>
					<td width="16%"><strong>Atividade de Cobran&ccedil;a:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:select property="cobrancaAtividade" onchange="validarCriterios(2);" tabindex="4">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:present scope="session" name="colecaoCobrancaAtividade">

							<html:options name="session"
								collection="colecaoCobrancaAtividade"
								labelProperty="descricaoCobrancaAtividade" property="id" />
						</logic:present>

					</html:select> </td>
				</tr>
				<tr>
					<logic:equal value="2" property="cobrancaAtividade" name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm">
						<td width="16%"><strong>Prazo de Execu��o:<font color="#FF0000">*</font></strong></td>
					</logic:equal>
					<logic:notEqual value="2" property="cobrancaAtividade" name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm">
						<td width="16%"><strong>Prazo de Execu��o:</strong></td>
					</logic:notEqual>
					<td colspan="2"><html:text property="prazoExecucao" tabindex="3" size="3" maxlength="4" onkeypress="return isCampoNumerico(event);" onkeyup="somente_numero_zero_a_nove(this);" onblur="somente_numero_zero_a_nove(this);"/></td>
				</tr>
				<tr>
					<td width="16%"><strong>Quantidade M�xima de Documentos:</strong></td>
					<td colspan="2"><html:text property="quantidadeMaximaDocumentos" tabindex="4" size="5" maxlength="5" onkeypress="return isCampoNumerico(event);"
					onkeyup="somente_numero_zero_a_nove(this);"
					onblur="somente_numero_zero_a_nove(this);"/></td>
				</tr>
				<tr>
					<td width="16%"><strong>Valor Limite para Emiss�o Obrigat�ria:</strong></td>
					<td colspan="2"><html:text property="valorLimiteObrigatoria" size="9"
							maxlength="9" tabindex="5" 
							onkeyup="formataValorMonetario(this, 9)" 
							style="text-align:right;" onkeypress="return isCampoNumerico(event);"
							onblur="somente_numero_zero_a_nove(this);"/>
				</tr>
				<tr>
					<td width="16%"><strong>Apenas para Im�veis com D�bito:</strong></td>
					<td colspan="2"><html:radio property="indicadorImoveisDebito" value="1" /> 
					                  <strong>Sim 
					                <html:radio	property="indicadorImoveisDebito" value="2" /> 
					                N&atilde;o</strong>
					</td>
				</tr>
				<tr>
					<td width="16%"><strong>Gerar Boletins de Cadastro:</strong></td>
					<td><html:radio property="indicadorGerarBoletimCadastro" value="1" /> 
					                  <strong>Sim 
					                <html:radio	property="indicadorGerarBoletimCadastro" value="2" /> 
					                N&atilde;o</strong>
					</td>
					<html:hidden
						property="cobrancaAtividadeIndicadorExecucao" />
					<td >
					<input name="Button32232" type="button"
						class="bottonRightCol" value="Pesquisar Comandos"
						onClick="javascript:abrirPopup('exibirPesquisarComandoAcaoCobrancaAction.do', 400, 750);" />
				</tr>
				
				
				<tr>
					<td colspan="3">
					<hr>
					</td>
				</tr>
				<tr>
					<td width="16%"><strong>Grupo de Cobran&ccedil;a:</strong></td>
					<td colspan="2"><html:select property="cobrancaGrupo" onchange="validarGrupoCobrancaSelecionado();" tabindex="3">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:present scope="session" name="colecaoCobrancaGrupo">
							<html:options name="session" collection="colecaoCobrancaGrupo"
								labelProperty="descricao" property="id" />
						</logic:present>
					</html:select></td>
				</tr>
				<tr>
					<td colspan="3">
					<hr>
					</td>
				</tr>
				<tr>
					<td width="16%"><strong>Matr�cula do Im�vel:</strong></td>
					<td colspan="2"><html:text property="idImovel" size="9" maxlength="9"
						onkeypress="validaEnter(event, 'exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do?objetoConsulta=5', 'idImovel');return isCampoNumerico(event);"
						onkeyup="verificarPreenchimentoImovel(); limparDestino(3);" />
					<a href="javascript:habilitarPesquisaImovel(document.forms[0]);" >
							<img width="23" height="21"	src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" title="Pesquisar Im�vel"/></a>	
					 <logic:present name="matriculaInexistente"
						scope="request">
						<html:text property="descricaoImovel" size="35" maxlength="35"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000" />
					</logic:present> <logic:notPresent name="matriculaInexistente"
						scope="request">
						<html:text property="descricaoImovel" size="35" maxlength="35"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:limparImovel();"><img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>
				<tr>
					<td colspan="3">
					<hr>
					</td>
				</tr>
				<tr>
					<td width="16%"><strong>Ger&ecirc;ncia Regional:<strong></strong></strong></td>
					<td colspan="2">
					<div align="left"><strong> <html:select tabindex="4"
						name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm"
						onchange="javaScript:validarGerenciaRegionalSelecionado();"
						property="gerenciaRegional">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:present scope="session" name="colecaoGerenciaRegional">
				         <logic:iterate name="colecaoGerenciaRegional" id="colecaoGerenciasRegionais">
  								<html:option value="${colecaoGerenciasRegionais.id}">
					           <bean:write name="colecaoGerenciasRegionais" property="nomeAbreviado"/> 
					           - <bean:write name="colecaoGerenciasRegionais" property="nome"/>
					          </html:option>
				         </logic:iterate>
						</logic:present>
					</html:select></strong></div>
					</td>

				</tr>

				<tr>
					<td width="16%"><strong>Unidade Neg�cio:<strong></strong></strong></td>
					<td colspan="2">
					<div align="left"><strong> <html:select tabindex="4"
						name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm"
						onchange="javaScript:validarUnidadeNegocioSelecionado();"
						property="unidadeNegocio">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<logic:present scope="session" name="colecaoUnidadeNegocio">
				         <logic:iterate name="colecaoUnidadeNegocio" id="colecaoUnidadeNegocios">
								<html:option value="${colecaoUnidadeNegocios.id}">
					           <bean:write name="colecaoUnidadeNegocios" property="nomeAbreviado"/> 
					           - <bean:write name="colecaoUnidadeNegocios" property="nome"/>
					          </html:option>
				         </logic:iterate>
						</logic:present>
					</html:select></strong></div>
					</td>

				</tr>

				<tr>
					<td colspan="3">
					<hr>
					</td>
				</tr>
				
				<html:hidden property="inscricaoTipo" />
				<tr>

					<td width="16%"><strong>Localidade Inicial:</strong></td>
					<td align="right" colspan="2">
					<div align="left">
						<html:text maxlength="3"
							property="localidadeOrigemID" 
							size="3" 
							tabindex="5"
							onchange="limparDescricao(document.forms[0].nomeLocalidadeOrigem);"
							onkeypress="limparDescricao(document.forms[0].nomeLocalidadeOrigem);limparDestino(1);validaEnterComMensagem(event, 'exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do?objetoConsulta=1&inscricaoTipo=origem&validarCriterio=naoAcao', 'localidadeOrigemID', 'Localidade Inicial');return isCampoNumerico(event);"
							onkeyup="javaScript:desabilitarCobrancaAcao();"
							onblur="javascript:somente_numero_zero_a_nove(this);replicarLocalidade();"/> 
							
						<a href="javascript:document.forms[0].tipoLocalidade.value='I';chamarPopup('exibirPesquisarLocalidadeAction.do?indicadorUsoTodos=1', 'origem', null, null, 275, 480, '',document.forms[0].localidadeOrigemID);">
                       		<img border="0" title="Pesquisar Localidade"
                       			src="<bean:message key="caminho.imagens"/>pesquisa.gif" 
                       			border="0"/></a>

						<strong> 
						<logic:present name="corLocalidadeOrigem">
							<logic:equal name="corLocalidadeOrigem" value="exception">
								<html:text property="nomeLocalidadeOrigem" 
									size="40"
									maxlength="40" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:equal>

							<logic:notEqual name="corLocalidadeOrigem" value="exception">
								<html:text property="nomeLocalidadeOrigem" 
									size="40"
									maxlength="40" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notEqual>

						</logic:present> 
						
						<logic:notPresent name="corLocalidadeOrigem">
							<logic:empty name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm"
								property="localidadeOrigemID">
							
							<html:text property="nomeLocalidadeOrigem" 
								value="" 
								size="40"
								maxlength="40" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:empty>
							
							<logic:notEmpty name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm"
								property="localidadeOrigemID">
								
								<html:text property="nomeLocalidadeOrigem" 
									size="40"
									maxlength="40" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: 	#000000" />
							</logic:notEmpty>
						</logic:notPresent> 
						
						<a href="javascript:limparOrigem(1);"> <img name="imagem"
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>  					
						</strong></div>
					</td>

				</tr>

				<tr>
					<td><strong>Setor Comercial Inicial: </strong></td>
					<td align="right" colspan="2">
					<div align="left"><html:text maxlength="3" tabindex="6"
						property="setorComercialOrigemCD" size="3"
						onblur="javascript:somente_numero_zero_a_nove(this);replicarSetorComercial();"
						onkeyup="javascript:validarPreenchimento();limparDestino(2);"
						onchange="limparDescricao(document.forms[0].nomeSetorComercialOrigem);"
						onkeypress="limparDescricao(document.forms[0].nomeSetorComercialOrigem);
									limparDestino(2);
						            validaEnterDependenciaComMensagem(event, 'exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do?objetoConsulta=2&inscricaoTipo=origem&validarCriterio=naoAcao', document.forms[0].setorComercialOrigemCD, document.forms[0].localidadeOrigemID.value, 'Localidade Inicial', 'Setor Comercial Inicial');
						            return isCampoNumerico(event);"
						 /> 
						<a href="javascript:habilitarPesquisarSetorComercialInicial();">
                         <img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0"
                         title="Pesquisar Setor Comercial"/></a>
					<logic:present name="corSetorComercialOrigem">
						<logic:equal name="corSetorComercialOrigem" value="exception">
							<html:text property="nomeSetorComercialOrigem" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>

						<logic:notEqual name="corSetorComercialOrigem" value="exception">
							<html:text property="nomeSetorComercialOrigem" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>
					</logic:present> <logic:notPresent name="corSetorComercialOrigem">
						<logic:empty
							name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm"
							property="setorComercialOrigemCD">
							<html:text property="nomeSetorComercialOrigem" value="" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty
							name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm"
							property="setorComercialOrigemCD">
							<html:text property="nomeSetorComercialOrigem" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>
						
					</logic:notPresent> 
						<a href="javascript:limparOrigem(2);"> <img name="imagem"
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>  										
					<html:hidden property="setorComercialOrigemID" /></div>
					</td>
				</tr>
				
				<tr>
					<td width="16%"><strong>Quadra Inicial:</strong></td>
					<td align="left" colspan="2">
						<html:text maxlength="5" 
							property="numeroQuadraInicial" 
							size="5"  
							tabindex="3"
							onchange="duplicarQuadra()" 
                   			onblur="validarQuadra(this, document.forms[0].setorComercialOrigemCD.value, 'Setor Comercial Inicial');"
                   			onkeypress="return isCampoNumerico(event);" 
                   			onblur="somente_numero_zero_a_nove(this);" />
                   </td>					
				</tr>


				<tr>
					<td width="16%"><strong>Rota Inicial:<font color="#FF0000"> </font></strong></td>
					<td align="left" colspan="2">
						<html:text maxlength="4" tabindex="7"
						property="rotaInicial" size="4"
						onchange="duplicarRota()" 
						onkeypress="return isCampoNumerico(event);"
						name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm"
						onblur="somente_numero_zero_a_nove(this);"/> 
					</td>
				</tr>

				<tr>
					<td colspan="3">
					<hr>
					</td>
				</tr>


				<tr>
					<td width="16%"><strong>Localidade Final:<font color="#FF0000"> </font></strong></td>
					<td align="left" colspan="2"><html:text maxlength="3"
						property="localidadeDestinoID" size="3" tabindex="8"
						onchange="limparDescricao(document.forms[0].nomeLocalidadeDestino);"
						onkeyup="desabilitaIntervaloDiferente(1);validarPreenchimento();"
						onkeypress="limparDescricao(document.forms[0].nomeLocalidadeDestino);validaEnterComMensagem(event, 'exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do?objetoConsulta=1&inscricaoTipo=destino&validarCriterio=naoAcao', 'localidadeDestinoID', 'Localidade Final');return isCampoNumerico(event);"
						onblur="somente_numero_zero_a_nove(this);"/>
						<a href="javascript:document.forms[0].tipoLocalidade.value='F';chamarPopup('exibirPesquisarLocalidadeAction.do?indicadorUsoTodos=1', 'destino', null, null, 275, 480, '',document.forms[0].localidadeDestinoID);">
                         <img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0"
                         title="Pesquisar Localidade"/></a>
						<logic:present name="corLocalidadeDestino">

						<logic:equal name="corLocalidadeDestino" value="exception">
							<html:text property="nomeLocalidadeDestino" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>

						<logic:notEqual name="corLocalidadeDestino" value="exception">
							<html:text property="nomeLocalidadeDestino" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>

					</logic:present> <logic:notPresent name="corLocalidadeDestino">

						<logic:empty
							name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm"
							property="localidadeDestinoID">
							<html:text property="nomeLocalidadeDestino" value="" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty
							name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm"
							property="localidadeDestinoID">
							<html:text property="nomeLocalidadeDestino" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: 	#000000" />
						</logic:notEmpty>
					</logic:notPresent>
						<a href="javascript:limparBorrachaDestino(1);"> <img name="imagem"
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>  					
					</td>
				</tr>

				<tr>
					<td width="16%"><strong>Setor Comercial Final: </strong></td>
					<td align="left" colspan="2"><html:text maxlength="3"
						property="setorComercialDestinoCD" size="3" tabindex="9"
						onchange="limparDescricao(document.forms[0].nomeSetorComercialDestino);"
						onkeyup="desabilitaIntervaloDiferente(2);habilitarRota();validarPreenchimento();validarSetorFinal();"
						onkeypress="limparDescricao(document.forms[0].nomeSetorComercialDestino);validaEnterDependenciaComMensagem(event, 'exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do?objetoConsulta=2&inscricaoTipo=destino&validarCriterio=naoAcao', document.forms[0].setorComercialDestinoCD, document.forms[0].localidadeDestinoID.value, 'Localidade Final', 'Setor Comercial Final');
						return isCampoNumerico(event);"
						 onblur="somente_numero_zero_a_nove(this);" /> 
						<a href="javascript:abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].localidadeDestinoID.value+'&tipo=setorComercialDestino&indicadorUsoTodos=1',document.forms[0].localidadeDestinoID.value,'Localidade da inscri��o de destino', 400, 800);">
                         <img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0"
                         title="Pesquisar Setor Comercial"/></a>
						 <logic:present name="corSetorComercialDestino">

						<logic:equal name="corSetorComercialDestino" value="exception">
							<html:text property="nomeSetorComercialDestino" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>

						<logic:notEqual name="corSetorComercialDestino" value="exception">
							<html:text property="nomeSetorComercialDestino" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEqual>

					</logic:present> <logic:notPresent name="corSetorComercialDestino">

						<logic:empty
							name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm"
							property="setorComercialDestinoCD">
							<html:text property="nomeSetorComercialDestino" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:empty>
						<logic:notEmpty
							name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm"
							property="setorComercialDestinoCD">
							<html:text property="nomeSetorComercialDestino" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notEmpty>

					</logic:notPresent> <html:hidden property="setorComercialDestinoID" />
						<a href="javascript:limparBorrachaDestino(2);"> <img name="imagem"
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>  
					</td>
				</tr>
				
				<tr>
					<td width="16%"><strong>Quadra Final:</strong></td>
					<td align="left" colspan="2">
						<html:text maxlength="5" 
							property="numeroQuadraFinal" 
							size="5"  
							tabindex="3"
                   			onblur="validarQuadra(this, document.forms[0].setorComercialDestinoCD.value, 'Setor Comercial Final');"
                   			onkeypress="return isCampoNumerico(event);"/>
                   </td>					
				</tr>
				
				<tr>
					<td width="16%"><strong>Rota Final:<font color="#FF0000"> </font></strong></td>
					<td align="right" colspan="2">
						<div align="left">
							<html:text maxlength="4" tabindex="10"
								property="rotaFinal" size="4"
								name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm"
								onkeypress="return isCampoNumerico(event);"
								onblur="somente_numero_zero_a_nove(this);"/> 
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="3">
					<hr>
					</td>
				</tr>
				<tr>
					<td width="16%"><strong>C�digo do Cliente Superior:</strong></td>
					<td colspan="2">
						<html:text property="codigoClienteSuperior" maxlength="9" size="9" 
						onchange="limparDescricao(document.forms[0].nomeClienteSuperior);"
						onkeyup="javaScript:validarClienteSuperior(1);desabilitarLocalidadeCobrancaAcao();limparDestino(4);"
						onkeypress="javascript:limparDescricao(document.forms[0].nomeClienteSuperior);validaEnterComMensagem(event, 'exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do?objetoConsulta=3&inscricaoTipo=superior&validarCriterio=naoAcao', 'codigoClienteSuperior');return isCampoNumerico(event);" 
						onblur="somente_numero_zero_a_nove(this);" />
						<a
						href="javascript:validarAbrirPoupPesquisarClienteSuperior();">
					<img border="0"  title="Pesquisar Cliente"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						alt="Pesquisar Cliente" /></a> 
						<logic:present
						  name="codigoClienteSuperiorNaoEncontrado" scope="request">
						<input type="text" name="nomeClienteSuperior" size="40" readonly="readonly"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							value="CLIENTE SUPERIOR INEXISTENTE" />
					     </logic:present> 
					   <logic:notPresent
						name="codigoClienteSuperiorNaoEncontrado" scope="request">
						<html:text property="nomeClienteSuperior" size="40" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> 
					<a href="javascript:validarLimparClienteSuperior();desabilitarLocalidadeCobrancaAcao();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a>
					</td>
				</tr>
				<tr>
					<td width="16%"><strong>Cliente:</strong></td>
					<td colspan="2"><strong> <html:text maxlength="9"
						property="idCliente" size="9" tabindex="11"
						onchange="limparDescricao(document.forms[0].nomeCliente);"
						onkeyup="javaScript:validarCliente(1);desabilitarLocalidadeCobrancaAcao();limparDestino(5);"
						onkeypress="javascript:limparDescricao(document.forms[0].nomeCliente);validaEnterComMensagem(event, 'exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do?objetoConsulta=3&validarCriterio=naoAcao', 'idCliente', 'Cliente');return isCampoNumerico(event);" 
						onblur="somente_numero_zero_a_nove(this);" />

					<a
						href="javascript:validarAbrirPoupPesquisarCliente();">
					<img border="0"  title="Pesquisar Cliente"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Cliente" /></a> 
						<logic:present
						  name="codigoClienteNaoEncontrado" scope="request">
						<input type="text" name="nomeCliente" size="40" readonly="readonly"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							value="CLIENTE INEXISTENTE" />
					     </logic:present> 
					   <logic:notPresent
						name="codigoClienteNaoEncontrado" scope="request">
						<html:text property="nomeCliente" size="40" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> 
					<a href="javascript:validarLimparCliente();desabilitarLocalidadeCobrancaAcao();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a> </strong></td>
				</tr>
				<tr>
					<td width="16%"><strong>Tipo de Rela&ccedil;&atilde;o:</strong></td>
					<td colspan="2"><strong> <html:select tabindex="12"
						name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm"
						property="clienteRelacaoTipo" disabled="true">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options name="request"
							collection="colecaoClienteRelacaoTipo" labelProperty="descricao"
							property="id" />
					</html:select> </strong></td>
				</tr>
				<tr>
					<td colspan="3">
					<hr>
					</td>
				</tr>
				<tr>
					<td width="16%"><strong>Per&iacute;odo de Refer&ecirc;ncia das Contas</strong><strong>:</strong></td>
					<td colspan="2"><strong> <html:text maxlength="7"
						property="periodoInicialConta" size="7" tabindex="13"
						onkeyup="somente_numero(this);mascaraAnoMes(this, event);replicarCampo(document.forms[0].periodoFinalConta,this);" 
						onkeypress="return isCampoNumerico(event);"
						onblur="somente_numero(this);replicarCampo(document.forms[0].periodoFinalConta,this);"/><strong> a</strong> 
						<html:text
						name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm" tabindex="14"
						maxlength="7" property="periodoFinalConta" size="7"
						onkeyup="mascaraAnoMes(this, event);"
						onkeypress="return isCampoNumerico(event);" 
						onblur="somente_numero(this);"/> </strong>mm/aaaa</td>
				</tr>
				<tr>
					<td width="16%"><strong>Per&iacute;odo de Vencimento das Contas:</strong></td>
					<td colspan="2"><strong> <html:text maxlength="10" tabindex="15"
						property="periodoVencimentoContaInicial" size="10"
						onkeyup="somente_numero(this);mascaraData(this, event);replicarCampo(document.forms[0].periodoVencimentoContaFinal,this);"
						onkeypress="return isCampoNumerico(event);" 
						onblur="somente_numero(this);replicarCampo(document.forms[0].periodoVencimentoContaFinal,this);"/> <a
						href="javascript:habilitarPesquisaCalendarioPeriodoVencimentoInicial();"
						>
					<img border="0" title="Exibir Calend�rio"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calend�rio" /></a><strong>
					a</strong> <html:text maxlength="10" tabindex="16"
						property="periodoVencimentoContaFinal" size="10"
						onkeyup="mascaraData(this, event);"
						onkeypress="return isCampoNumerico(event);" onblur="somente_numero(this);"/> <a
						href="javascript:habilitarPesquisaCalendarioPeriodoVencimentoFinal();">
					<img border="0" title="Exibir Calend�rio"
						src="<bean:message key='caminho.imagens'/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calend�rio" /></a>
					</strong>dd/mm/aaaa</td>
				</tr>
				
				<tr>
					<td width="16%"><strong>Quantidade de Dias de Vencimento:</strong></td>
					<td colspan="2">
						<html:text property="quantidadeDiasVencimento" 
											   tabindex="4" 
											   size="3" 
											   maxlength="3" 
											   onkeypress="return isCampoNumerico(event);"
											   onblur="somente_numero_zero_a_nove(this);"/>
						</td>
				</tr>	
				
				
				<tr>
					<td colspan="3">
					<hr>
					</td>
				</tr>
					<tr>
						<td width="16%"><strong>Consumo M�dio do Im�vel:</strong></td>
						<td colspan="2"><strong> <html:text maxlength="7"
							property="consumoMedioInicial" size="7" tabindex="13"
							onkeyup="replicarCampo(document.forms[0].consumoMedioFinal,this);validarExibicaoTipoCosumo();"
							onkeypress="return isCampoNumerico(event);"
							onblur="somente_numero_zero_a_nove(this);validaNumero(this);replicarCampo(document.forms[0].consumoMedioFinal,this);"/><strong> a</strong> <html:text
							name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm" tabindex="14"
							maxlength="7" property="consumoMedioFinal" size="7"					
							onkeypress="validarExibicaoTipoCosumo();return isCampoNumerico(event);" 
							onkeyup="somente_numero_zero_a_nove(this);"
							onblur="somente_numero_zero_a_nove(this);"/> </strong>
						</td>
					</tr>
					<tr>
						<td width="16%"><strong>Tipo de Consumo:</strong></td>
						<td colspan="2"><strong> 
							<html:select tabindex="12"
								name="InserirComandoAcaoCobrancaEventualCriterioRotaActionForm"
								property="tipoConsumo" disabled="true">
								<html:option value="1">MEDIDO</html:option>
								<html:option value="2">N�O MEDIDO</html:option>
								<html:option value="3">AMBOS</html:option>
							</html:select> </strong>
						</td>
					</tr>
				<logic:present scope="session" name="colecaoFiscalizacaoSituacao">					
					<tr>
						<td colspan="3">
						<hr>
						</td>
					</tr>
					<tr>
						<td width="16%"><strong>Per�odo de Fiscaliza��o do Im�vel:</strong></td>
						<td colspan="2"><strong> <html:text maxlength="10" tabindex="15"
							property="periodoInicialFiscalizacao" size="10"
							onkeyup="replicarCampo(document.forms[0].periodoFinalFiscalizacao,this);mascaraData(this, event);"
							onkeypress="return isCampoNumerico(event);"
							onblur="somente_numero(this);replicarCampo(document.forms[0].periodoFinalFiscalizacao,this);" /> <a
							href="javascript:habilitarPesquisarCalendarioPeriodoFiscalizacaoInicial();">
						<img border="0" title="Exibir Calend�rio"
							src="<bean:message key='caminho.imagens'/>calendario.gif"
							width="20" border="0" align="absmiddle" alt="Exibir Calend�rio" /></a><strong>
						a</strong> <html:text maxlength="10" tabindex="16"
							property="periodoFinalFiscalizacao" size="10"
							onkeyup="mascaraData(this, event);"
							onkeypress="return isCampoNumerico(event);" 
							onblur="somente_numero(this);" /> <a
							href="javascript:habilitarPesquisarCalendarioPeriodoFiscalizacaoFinal();">
						<img border="0" title="Exibir Calend�rio"
							src="<bean:message key='caminho.imagens'/>calendario.gif"
							width="20" border="0" align="absmiddle" alt="Exibir Calend�rio" /></a>
						</strong>dd/mm/aaaa</td>
					</tr>		
					<tr>
						<td width="16%"><strong>Situa��o de Fiscaliza��o:</strong></td>
						<td colspan="2"><html:select property="situacaoFiscalizacao" style="height: 100px" multiple="true" tabindex="3">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<logic:present scope="session" name="colecaoFiscalizacaoSituacao">
								<html:options name="session" collection="colecaoFiscalizacaoSituacao"
									labelProperty="descricaoFiscalizacaoSituacao" property="id" />
							</logic:present>
						</html:select></td>
					</tr>
				</logic:present>
				
				<tr>
					<td width="16%"><strong>Rela��o de Im�veis:</strong></td>
					<td colspan="2">
						<html:file property="relacaoImoveis" size="9"/>
					</td>
				</tr>
				
				
				<tr>
					<td colspan="3">
					<hr>
					</td>
				</tr>
			    <tr> 
                	<td width="16%"><strong>Logradouro:</strong></td>
                		<td colspan="2"><span class="style2"><strong> 
							<html:text property="logradouroId" size="9" maxlength="9"							
							   onchange="limparDescricao(document.forms[0].logradouroDescricao);"
							   onkeypress="javascript:limparDescricao(document.forms[0].logradouroDescricao);validaEnterComMensagem(event, 'exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do?objetoConsulta=4&validarCriterio=naoAcao&inscricaoTipo=origem', 'logradouroId','Logradouro');return isCampoNumerico(event);"
							   onkeyup="javascript:validarLogradouroSelecionado();limparDestino(6);"	
							   onblur="somente_numero_zero_a_nove(this);"/>
						
						<a href="javascript:chamarPopup('exibirPesquisarLogradouroAction.do?indicadorUsoTodos=1&primeriaVez=1', 'origem', null, null, 275, 480,'',document.forms[0].logradouroId);">
						<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"	height="21" name="imagem" alt="Pesquisar" 
						 title="Pesquisar Logradouro" border="0"></a>

						<logic:present name="logradouroEncontrada" scope="session">
							<html:text property="logradouroDescricao" readonly="true"
								   style="background-color:#EFEFEF; border:0; color: #000000" size="40" maxlength="40" />
						</logic:present> 
	
						<logic:notPresent name="logradouroEncontrada" scope="session">
							<html:text property="logradouroDescricao" readonly="true"
								   style="background-color:#EFEFEF; border:0; color: #ff0000" size="40" maxlength="40" />
						</logic:notPresent>

						<a href="javascript:limparLogradouro();">
							<img border="0" title="Apagar" src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
                  	</strong></span></td>
                </tr>
                <tr>
				 	<td colspan="3">
				 	<hr>
				 	</td>
			    </tr>
			    <tr>
					<td width="16%"><strong>Indicador do Crit&eacute;rio:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><strong> <html:radio property="indicador" tabindex="17"
						value="Rota" onclick="habilitar('1');" /> Usa Crit&eacute;rio da
					Rota </strong> 
						<html:radio property="indicador" value="Comando" tabindex="18"
							onclick="habilitar('2');" />
						<strong>Usa <strong><strong>Crit&eacute;rio</strong> do Comando</strong>
						</strong>
						</td>
				 </tr>
				<tr>
					<td height="17"><strong></strong></td>
					<td colspan="2"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</td>
				</tr>
				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td height="17" colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3" align="right">
					<table border="0"  width="100%">
						<tr>
							 <td align="left"><!--<input name="executarComando"
								type="button" class="bottonRightCol" onClick="executarcomando();"
								value="Executar Comando" />--></td>
							<td align="right" width="60%"><img src="imagens/voltar.gif" width="15"
								height="24" border="0" /></td>
							<td align="right" width="5%"><input name="Button32222" type="button"
								class="bottonRightCol" value="Voltar" tabindex="19"
								onClick="javascript:voltar()" /></td>
							<td align="right" width="5%">
							  <input name="Avancar" type="button" class="bottonRightCol" disabled value="Avan�ar" tabindex="20"
								onClick="avancar();" /></td>
							<td align="left" width="30%"><img src="imagens/avancar.gif" width="15"
								height="24" border="0" /></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="3" height="1px" bgcolor="#000000"></td>
				</tr>				
				<tr>
					<td colspan="2"><strong> <font color="#ff0000"> <input name="Submit22"
						class="bottonRightCol" value="Limpar" type="button"
						onClick="window.location.href='exibirInserirComandoAcaoCobrancaEventualCriterioRotaAction.do?limparForm=OK&menu=sim'" />

					<input name="Submit23" class="bottonRightCol" value="Cancelar"
						type="button"
						onclick="window.location.href='/gsan/telaPrincipal.do'"> </font></strong></td>
					<td align="right">
					  <gsan:controleAcessoBotao name="concluir" 
					  	value="Concluir" 
					  	onclick="javascript:concluircomando();" 
					  	url="inserirComandoAcaoCobrancaEventualConcluirAction.do"/>				
					  
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
</body>
<script language="JavaScript">
<!-- Begin
function iniciar(){
	habilitar(document.forms[0].cobrancaAtividadeIndicadorExecucao.value);
///	validarGrupoCobranca(0);
	validarCliente(0);
	validarClienteSuperior(1);
	desabilitarLocalidadeCobrancaAcao();
	validarGrupoCobrancaParaBloquear();
	validarGerenciaRegionalParaBloquear();
	validarUnidadeNegocioParaBloquear();
	verificarCampoPrazoExecucao();
	
//// habilitacaoCampoRota();
	validarClienteParaBloquear();
	validarHabilitarCampo();
	validarLogradouroParaBloquear();
	verificarPreenchimentoImovel();
}
-->
</script>
</html:html>
