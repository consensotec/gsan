<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@page import="gcom.cadastro.empresa.Empresa"%>
<%@page import="gcom.gui.cadastro.atualizacaocadastral.GerarTabelasTemporariasPorLocalidadeActionForm"%>
<%@page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>cadastro/geracao_tabelas_temporarias_localidade.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<html:javascript staticJavascript="false"
	formName="GerarTabelasTemporariasPorLocalidadeActionForm" />


<script>

	function validarForm(){
		
		var form = document.forms[0];
		if(validateGerarTabelasTemporariasPorLocalidadeActionForm(form) && 
			campoObrigatorio() && 
			validarCampos() ){
			if ( form.numeroQuadraInicial.value == "" ) {
				form.action = 'filtrarGerarTabelasTemporariasPorLocalidadeAction.do';
			} else {
				form.action = 'filtrarGerarTabelasTemporariasPorLocalidadeAction.do?quadra=ok' ;
			}
			
			form.submit();
		}
	}

	function campoObrigatorio(){
	
		var form = document.forms[0];
		var msg = "";
		
		if(form.empresa.value == "-1"){
			msg = "Informe a Empresa.";
		}else if(form.matriculaImovel.value == "" && (form.idLocalidadeInicial.value == "" || form.idLocalidadeFinal.value == "") ){
			msg = "Informe o intervalo de Localidade."
		}else if(form.matriculaImovel.value == "" && form.idLocalidadeInicial.value == "" && form.idLocalidadeFinal.value == ""){
			msg = "Informe a matrícula do imóvel ou intervalo de localidade."
		}
		
		if( msg != ""){
			alert(msg);
			return false;
		}
		
		return true;
	}	

	function validarCampos(){
		
		var form = document.forms[0];
		
		msg = verificarAtributosInicialFinal(form.idLocalidadeInicial,form.idLocalidadeFinal,"Localidade");
		if( msg != ""){
			alert(msg);
			return false;
		}else{
			msg = verificarAtributosInicialFinal(form.codigoSetorComercialInicial,form.codigoSetorComercialFinal,"Setor Comercial");
			if( msg != ""){
				alert(msg);
				return false;	
			}else{
				msg = verificarAtributosInicialFinal(form.numeroQuadraInicial,form.numeroQuadraFinal,"Quadra");
				if( msg != ""){
					alert(msg);
					return false;
				} else {
					msg = verificarAtributosInicialFinal(form.rotaInicial,form.rotaFinal,"Rota");
					if( msg != ""){
						alert(msg);
						return false;
					}
				}
			}	
		}
		
		return true;
	}
	
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,campo){
		if(!campo.disabled){
	  		if (objeto == null || codigoObjeto == null){
	     		if(tipo == "" ){
	      			abrirPopup(url,altura, largura);
	     		}else{
		  			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		 		}
	 		}else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				}else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
				}
			}
		}
	}

	function reloadForm(){
		var form = document.forms[0];
	
		form.action='exibirFiltrarGerarTabelasTemporariasPorLocalidadeAction.do';
    	form.submit();
	}

	function limpar(){

		var form = document.forms[0];
	
		form.empresa.value = "-1";
	
		form.idLocalidadeInicial.value = "";
		form.nomeLocalidadeInicial.value = "";
		form.idLocalidadeFinal.value = "";
		form.nomeLocalidadeFinal.value = "";
		form.nomeLocalidadeInicial.style.color = "#000000";
		form.nomeLocalidadeFinal.style.color = "#000000";
	
	
		form.codigoSetorComercialInicial.value = "";
		form.nomeSetorComercialInicial.value = "";
		form.codigoSetorComercialFinal.value = "";
		form.nomeSetorComercialFinal.value = "";
		form.nomeSetorComercialInicial.style.color = "#000000";
	  	form.nomeSetorComercialFinal.style.color = "#000000";
	  	
		form.numeroQuadraInicial.value = "";
		form.numeroQuadraFinal.value = "";

		form.rotaInicial.value = "";
		form.rotaFinal.value = "";

		form.matriculaImovel.value = "";
		form.nomeImovel.value = "";
	  	
		form.action='exibirFiltrarGerarTabelasTemporariasPorLocalidadeAction.do?menu=sim';
	    form.submit();
	}

	function replicarLocalidade(){
		var formulario = document.forms[0]; 
		
		formulario.idLocalidadeFinal.value = formulario.idLocalidadeInicial.value;
		formulario.codigoSetorComercialInicial.focus;
	}

	function replicarSetorComercial(){
		var formulario = document.forms[0]; 
		formulario.codigoSetorComercialFinal.value = formulario.codigoSetorComercialInicial.value;
		formulario.numeroQuadraInicial.focus;
	} 

	function replicarQuadra(){
		var formulario = document.forms[0]; 

			formulario.numeroQuadraFinal.value = formulario.numeroQuadraInicial.value;
	
		formulario.rotaInicial.focus;
	} 

	function replicarRota(){
		var formulario = document.forms[0]; 
		
		formulario.rotaFinal.value = formulario.rotaInicial.value;

		if ( formulario.numeroQuadraInicial.value == "" ) {
			formulario.numeroQuadraFinal.value = "";
		}
	} 
	

	function limparOrigem(tipo){
		var form = document.forms[0];
		
		switch(tipo){
		
		case 1: //De localidade pra baixo

			form.nomeLocalidadeInicial.value = "";
			form.idLocalidadeFinal.value = "";
			form.nomeLocalidadeFinal.value = "";
			form.codigoSetorComercialInicial.value = "";
		    form.codigoSetorComercialFinal.value = "";
		 	form.numeroQuadraInicial.value = "";
		 	form.rotaInicial.value = "";
		  
		case 2: //De setor para baixo

		   form.nomeSetorComercialInicial.value = "";
		   form.codigoSetorComercialFinal.value = "";
		   form.nomeSetorComercialFinal.value = "";
		   form.numeroQuadraFinal.value = "";
		   form.rotaFinal.value = "";		   
			
		}
	}

	function limparDestino(tipo){
		var form = document.forms[0];
		
		switch(tipo){
		
		case 1: //De localidade pra baixo

			form.idLocalidadeFinal.value = "";
			form.nomeLocalidadeFinal.value = "";

			form.codigoSetorComercialFinal.value = "";
		    form.nomeSetorComercialFinal.value = "";

		 	form.numeroQuadraFinal.value = "";
		 	form.rotaFinal.value = "";
		  
		case 2: //De setor para baixo

		    form.codigoSetorComercialFinal.value = "";
		    form.nomeSetorComercialFinal.value = "";
		    
		 	form.numeroQuadraFinal.value = "";
		 	form.rotaFinal.value = "";
			
		}
	}

	
	function limparBorrachaOrigem(tipo){
		var form = document.forms[0];
		
		switch(tipo){
			case 1: //De localidara pra baixo

				form.idLocalidadeInicial.value = "";
				form.nomeLocalidadeInicial.value = "";
		     	form.codigoSetorComercialInicial.value = "";
		     	form.nomeSetorComercialInicial.value = "";
			 	form.numeroQuadraInicial.value = "";
			 	form.rotaInicial.value = "";

				limparBorrachaDestino(1);
								
				break;
			case 2: //De setor para baixo
		     	
		     	form.codigoSetorComercialInicial.value = "";
		     	form.nomeSetorComercialInicial.value = "";
		     	form.numeroQuadraInicial.value = "";
			 	form.rotaInicial.value = "";
		     	limparBorrachaDestino(2);

			case 3: //De setor para baixo

				form.numeroQuadraInicial.value = "";
			 	form.rotaInicial.value = "";
			 	form.numeroQuadraFinal.value = "";
			 	form.rotaFinal.value = "";
		}
		habilitaOuDesabilitaCampos();
	}
	
	
	function limparBorrachaDestino(tipo){
		var form = document.forms[0];

		switch(tipo){
			case 1: //De localidade pra baixo
				form.idLocalidadeFinal.value = "";
				form.nomeLocalidadeFinal.value = "";
				
				form.codigoSetorComercialFinal.value = "";
				form.nomeSetorComercialFinal.value = "";

			 	form.numeroQuadraFinal.value = "";
			 	form.rotaFinal.value = "";
					
				
			case 2: //De setor para baixo		   
		   		form.codigoSetorComercialFinal.value = ""; 
		   		form.nomeSetorComercialFinal.value = "";
			 	form.numeroQuadraFinal.value = "";
			 	form.rotaFinal.value = "";
			   		
		}
		habilitaOuDesabilitaCampos();
	}

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	
		var form = document.forms[0];
	
		if (tipoConsulta == 'localidadeOrigem') {
	  		
	  		form.idLocalidadeInicial.value = codigoRegistro;
	  		form.nomeLocalidadeInicial.value = descricaoRegistro;
	  		
	  		form.idLocalidadeFinal.value = codigoRegistro;
	  		form.nomeLocalidadeFinal.value = descricaoRegistro;
	  		
	  		form.nomeLocalidadeInicial.style.color = "#000000";
	  		form.nomeLocalidadeFinal.style.color = "#000000";
	  		
	  		form.codigoSetorComercialInicial.focus();
		}
	
		if (tipoConsulta == 'localidadeDestino') {
			
	  		form.idLocalidadeFinal.value = codigoRegistro;
	  		form.nomeLocalidadeFinal.value = descricaoRegistro;
	  		form.nomeLocalidadeFinal.style.color = "#000000";
	
	  		form.codigoSetorComercialFinal.focus();
		}

		if (tipoConsulta == 'imovel') {
      		
      		form.matriculaImovel.value = codigoRegistro;
	  		form.nomeImovel.value = descricaoRegistro;
	  		      		
	  		form.nomeImovel.style.color = "#000000";
		}
		habilitaOuDesabilitaCampos();
	}

	function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {

		var form = document.forms[0];

		if (tipoConsulta == 'setorComercialOrigem') {
		  	form.codigoSetorComercialInicial.value = codigoRegistro;
		  	form.nomeSetorComercialInicial.value = descricaoRegistro;
		  	form.nomeSetorComercialInicial.style.color = "#000000"; 
		  	
		  	form.codigoSetorComercialFinal.value = codigoRegistro;
		  	form.nomeSetorComercialFinal.value = descricaoRegistro;
		  	form.nomeSetorComercialFinal.style.color = "#000000";
		  	limparBorrachaOrigem(3);
		  	habilitaOuDesabilitaCampos();
		}

		if (tipoConsulta == 'setorComercialDestino') {
		  	form.codigoSetorComercialFinal.value = codigoRegistro;
		  	form.nomeSetorComercialFinal.value = descricaoRegistro;
		  	form.nomeSetorComercialFinal.style.color = "#000000";
		  	desabilitaIntervaloDiferenteSetor();
		  	habilitaOuDesabilitaCampos();
		}
	}	

	function desabilitaIntervaloDiferente(){
		var form = document.GerarTabelasTemporariasPorLocalidadeActionForm;	

	    if(form.idLocalidadeInicial.value != form.idLocalidadeFinal.value){	        

	        form.codigoSetorComercialInicial.disabled = true;		
		 	form.codigoSetorComercialFinal.disabled = true;
		 	form.numeroQuadraInicial.disabled = true;
		 	form.numeroQuadraFinal.disabled = true;
		 	form.rotaInicial.disabled = true;
		 	form.rotaFinal.disabled = true;

		 	form.codigoSetorComercialInicial.value = "";		
		 	form.codigoSetorComercialFinal.value = "";
 			form.nomeSetorComercialInicial.value = "";		
		 	form.nomeSetorComercialFinal.value = "";
		 	form.numeroQuadraInicial.value = "";
		 	form.numeroQuadraFinal.value = "";
		 	form.rotaInicial.value = "";
		 	form.rotaFinal.value = "";
			
		  }else{
		 	form.codigoSetorComercialInicial.disabled = false;		
		 	form.codigoSetorComercialFinal.disabled = false;
		 	form.numeroQuadraInicial.disabled = false;
		 	form.numeroQuadraFinal.disabled = false
		 	form.rotaInicial.disabled = false;
		 	form.rotaFinal.disabled = false;
		 	
		 }		
	}	

	function desabilitaIntervaloDiferenteSetor(){
		var form = document.GerarTabelasTemporariasPorLocalidadeActionForm;	
		if(form.codigoSetorComercialInicial.value != form.codigoSetorComercialFinal.value || form.codigoSetorComercialInicial.disabled == true){	        	        
		 	form.numeroQuadraInicial.disabled = true;
		 	form.numeroQuadraFinal.disabled = true;
		 	form.rotaInicial.disabled = true;
		 	form.rotaFinal.disabled = true;
		 	
	 		form.numeroQuadraInicial.value = "";
		 	form.numeroQuadraFinal.value = "";
	 		form.rotaInicial.value = "";
		 	form.rotaFinal.value = "";
		 	
		  }else{
		 	form.numeroQuadraInicial.disabled = false;
		 	form.numeroQuadraFinal.disabled = false;
		 	form.rotaInicial.disabled = false;
		 	form.rotaFinal.disabled = false;		 	
		 }		
	}	

	function limparImovel(){
		var form = document.forms[0];
		form.matriculaImovel.value = "";
		form.nomeImovel.value = "";
	}

	function habilitaOuDesabilitaCampos(){
		var form = document.forms[0];

		 if(form.idLocalidadeInicial.value != null && form.idLocalidadeInicial.value != "" &&  
				 form.idLocalidadeFinal.value != null && form.idLocalidadeFinal.value != "" &&
				 form.idLocalidadeInicial.value == form.idLocalidadeFinal.value) {

			 	form.codigoSetorComercialInicial.disabled = false;		
			 	form.codigoSetorComercialFinal.disabled = false;
			 	form.numeroQuadraInicial.disabled = true;
			 	form.numeroQuadraFinal.disabled = true;
			 	form.rotaInicial.disabled = true;
			 	form.rotaFinal.disabled = true;

				if (form.codigoSetorComercialInicial.value != null && form.codigoSetorComercialInicial.value != "" &&  
						 form.codigoSetorComercialFinal.value != null && form.codigoSetorComercialFinal.value != "" &&
						 form.codigoSetorComercialInicial.value == form.codigoSetorComercialFinal.value) {

					form.numeroQuadraInicial.disabled = false;
				 	form.numeroQuadraFinal.disabled = false;
				 	form.rotaInicial.disabled = false;
				 	form.rotaFinal.disabled = false;

				 	if (form.numeroQuadraInicial.value != null && form.numeroQuadraInicial.value != "" &&  
							 form.numeroQuadraFinal.value != null && form.numeroQuadraFinal.value != "" &&
							 form.numeroQuadraInicial.value == form.numeroQuadraFinal.value) {

				 		form.rotaInicial.disabled = true;
					 	form.rotaFinal.disabled = true;
					}	else if ( form.numeroQuadraInicial.value != form.numeroQuadraFinal.value ) {
					 	form.rotaInicial.disabled = true;
					 	form.rotaFinal.disabled = true;
					 	form.rotaInicial.value = "";
					 	form.rotaFinal.value = "";
					} else if (form.rotaInicial.value != null && form.rotaInicial.value != "" &&  
							 form.rotaFinal.value != null && form.rotaFinal.value != "" ) {
					 	form.numeroQuadraInicial.value = "";
					 	form.numeroQuadraFinal.value = "";
						form.numeroQuadraInicial.disabled = true;
					 	form.numeroQuadraFinal.disabled = true;

				 	} else {
					 	form.rotaInicial.disabled = false;
					 	form.rotaFinal.disabled = false;
					}
				} else {
					form.numeroQuadraInicial.disabled = true;
				 	form.numeroQuadraFinal.disabled = true;
				 	form.rotaInicial.disabled = true;
				 	form.rotaFinal.disabled = true;
				}
				 	    
		 } else {
		        form.codigoSetorComercialInicial.disabled = true;		
			 	form.codigoSetorComercialFinal.disabled = true;
			 	form.numeroQuadraInicial.disabled = true;
			 	form.numeroQuadraFinal.disabled = true;
			 	form.rotaInicial.disabled = true;
			 	form.rotaFinal.disabled = true;
		 }
		 
	}


	function limparQuadra() {
		var form = document.forms[0];

		if ( form.numeroQuadraInicial.value == "" ) {
			form.rotaInicial.value = "";
		 	form.rotaFinal.value = "";
		 	form.numeroQuadraFinal.value = "";
		 	form.numeroQuadraInicial.disabled = false;
		 	form.numeroQuadraFinal.disabled = false;
		}
	}

	function click(event) {
		if (event.button==2) {
			alert("Botão direito do mouse desabilitado.");
		}
	}
	
	document.onmousedown=click;
	


</script>

</head>

<body leftmargin="5" topmargin="5" onload="desabilitaIntervaloDiferente();desabilitaIntervaloDiferenteSetor();habilitaOuDesabilitaCampos();">
</body>

<html:form action="/filtrarGerarTabelasTemporariasPorLocalidadeAction.do"
	name="GerarTabelasTemporariasPorLocalidadeActionForm"
	type="gcom.gui.cadastro.atualizacaocadastral.GerarTabelasTemporariasPorLocalidadeActionForm"
	method="post">

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
					<td class="parabg">Gerar Tabelas Temporarias Por Localidade</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				
				<tr>
					<td colspan="2">Para gerar tabelas temporárias por localidade, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td>
						<strong>Empresa:<font color="#FF0000">*</font></strong>
					</td>

					<td>
						<strong> 
						<html:select 
							property="empresa" 
							style="width: 230px;">
							
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>
					
							<logic:present name="colecaoEmpresa" scope="request">
								<html:options collection="colecaoEmpresa"
									labelProperty="descricao" 
									property="id" />
							</logic:present>
						</html:select> 														
						</strong>
					</td>
				</tr>
				
				<tr>
					<td><strong>Imóvel:</strong></td>
					
					<td>						
						<html:text maxlength="9" 
							tabindex="1"
							property="matriculaImovel" 
							size="10"
							onkeypress="validaEnterComMensagem(event, 'exibirFiltrarGerarTabelasTemporariasPorLocalidadeAction.do?objetoConsulta=5','matriculaImovel','Imovel');
							            return isCampoNumerico(event);"/>
							
						<a href="javascript:chamarPopup('exibirPesquisarImovelAction.do', 'imovel', null, null, 275, 480, '',document.forms[0].matriculaImovel);" id="pesIm">
							<img width="23" 
								height="21" 
								border="0" 
								style="cursor:hand;"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Imóvel" /></a>
								

						<logic:present name="imovelEncontrado" scope="request">
							<html:text property="nomeImovel" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="imovelEncontrado" scope="request">
							<html:text property="nomeImovel" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>
		
						<a href="javascript:limparImovel();"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr><!-- Fim campo imóvel -->				
				

				
				<tr>
					<td colspan="2"><hr></td>
				</tr>

				<tr>
					<td><strong>Localidade Inicial:<font color="#FF0000">*</font></strong></td>
					
					<td>
						
						<html:text maxlength="3" 
							tabindex="1"
							property="idLocalidadeInicial" 
							size="3"
							onblur="javascript:replicarLocalidade();desabilitaIntervaloDiferente();habilitaOuDesabilitaCampos();" 
							onkeyup="javascript:limparOrigem(1);desabilitaIntervaloDiferente();"
							onkeypress="javascript:limparOrigem(1);validaEnterComMensagem(event, 'exibirFiltrarGerarTabelasTemporariasPorLocalidadeAction.do?objetoConsulta=1','idLocalidadeInicial','Localidade Inicial');return isCampoNumerico(event);"/>
							
						<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'origem', null, null, 275, 480, '',document.forms[0].idLocalidadeInicial);limparOrigem(1);">
							<img width="23" 
								height="21" 
								border="0" 
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Localidade" /></a>
								

						<logic:present name="localidadeInicialEncontrada" scope="request">
							<html:text property="nomeLocalidadeInicial" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="localidadeInicialEncontrada" scope="request">
							<html:text property="nomeLocalidadeInicial" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>

						
						<a href="javascript:limparBorrachaOrigem(1);"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr>

				<tr>
					<td><strong>Setor Comercial Inicial:</strong></td>
					
					<td>
						
						<html:text maxlength="3" 
							tabindex="1"
							property="codigoSetorComercialInicial" 
							size="3"
							onblur="javascript:replicarSetorComercial();desabilitaIntervaloDiferenteSetor();habilitaOuDesabilitaCampos();"
							onkeyup="limparOrigem(2);desabilitaIntervaloDiferenteSetor();"
							onkeypress="javascript:limparOrigem(2);validaEnterComMensagem(event, 'exibirFiltrarGerarTabelasTemporariasPorLocalidadeAction.do?objetoConsulta=2','codigoSetorComercialInicial','Setor Comercial Inicial');return isCampoNumerico(event);"/>
							
						<a href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialOrigem', 'idLocalidade', document.forms[0].idLocalidadeInicial.value , 275, 480, 'Informe Localidade Inicial',document.forms[0].codigoSetorComercialInicial);">
							<img width="23" 
								height="21" 
								border="0" 
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Setor Comercial" /></a>
								

						<logic:present name="setorComercialInicialEncontrado" scope="request">
							<html:text property="nomeSetorComercialInicial" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="setorComercialInicialEncontrado" scope="request">
							<html:text property="nomeSetorComercialInicial" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>
						
						<a href="javascript:limparBorrachaOrigem(2);"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr>

				<tr>
					<td><strong>Quadra Inicial:</strong></td>
					
					<td>
						
						<html:text maxlength="3" 
							tabindex="1"
							property="numeroQuadraInicial"
							onblur="javascript:replicarQuadra();habilitaOuDesabilitaCampos();limparQuadra();"
							onkeypress="validaEnterDependencia(event, 'exibirFiltrarGerarTabelasTemporariasPorLocalidadeAction.do?objetoConsulta=6',this,document.forms[0].codigoSetorComercialInicial.value,'Setor Comercial');return isCampoNumerico(event);"
							size="3"/>
							
					<logic:present name="idQuadraNaoEncontrada">
						<logic:equal name="idQuadraNaoEncontrada" value="exception">
							<html:text property="descricaoQuadraInicial" size="40" maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" />
						</logic:equal>
					</logic:present> 
					<logic:notPresent name="idQuadraNaoEncontrada">
						<logic:empty name="GerarTabelasTemporariasPorLocalidadeActionForm" property="numeroQuadraInicial">
							<html:text property="descricaoQuadraInicial" value="" size="40"
								maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000"
								style="display: none" />
						</logic:empty>
					</logic:notPresent>
					</td>
				</tr>
				
				<tr>
					<td><strong>Rota Inicial:</strong></td>
					
					<td>
						
						<html:text maxlength="4" 
							tabindex="1"
							property="rotaInicial"
							onblur="javascript:replicarRota();habilitaOuDesabilitaCampos();"
							onkeypress="return isCampoNumerico(event);habilitaOuDesabilitaCampos();" 
							size="5"/>
					</td>
				</tr>
				
				
				<tr>
					<td colspan="2"><hr></td>
				</tr>
								
				<tr>
					<td><strong>Localidade Final:<font color="#FF0000">*</font></strong></td>
					
					<td>
						
						<html:text maxlength="3" 
							tabindex="1"
							property="idLocalidadeFinal" 
							size="3"
							onblur="javascript:desabilitaIntervaloDiferente();habilitaOuDesabilitaCampos();"
							onkeypress="validaEnterComMensagem(event, 'exibirFiltrarGerarTabelasTemporariasPorLocalidadeAction.do?objetoConsulta=3','idLocalidadeFinal','Localidade Final');return isCampoNumerico(event);"
							onkeyup="javascript:desabilitaIntervaloDiferente();"/>
						
						<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'destino', null, null, 275, 480, '',document.forms[0].idLocalidadeFinal);limparDestino(1);">
							<img width="23" 
								height="21" 
								border="0" 
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Localidade" /></a>
								 

						<logic:present name="localidadeFinalEncontrada" scope="request">
							<html:text property="nomeLocalidadeFinal" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="localidadeFinalEncontrada" scope="request">
							<html:text property="nomeLocalidadeFinal" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>

						
						<a href="javascript:limparBorrachaDestino(1);"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr>
				
				
				<tr>
					<td><strong>Setor Comercial Final:</strong></td>
					
					<td>
						
						<html:text maxlength="3" property="codigoSetorComercialFinal"
							size="3"
							tabindex="8"
							onblur="javascript:desabilitaIntervaloDiferenteSetor();habilitaOuDesabilitaCampos();"
							onkeypress="validaEnterComMensagem(event, 'exibirFiltrarGerarTabelasTemporariasPorLocalidadeAction.do?objetoConsulta=4','codigoSetorComercialFinal','Setor Comercial Final');return isCampoNumerico(event);"
							onkeyup="desabilitaIntervaloDiferenteSetor();"/>
								
						<a href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialDestino', 'idLocalidade', document.forms[0].idLocalidadeFinal.value, 275, 480, 'Informe Localidade Final',document.forms[0].codigoSetorComercialFinal);">
							<img width="23" 
								height="21" 
								border="0" 
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Setor Comercial" /></a>
								

						<logic:present name="setorComercialFinalEncontrado" scope="request">
							<html:text property="nomeSetorComercialFinal" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="setorComercialFinalEncontrado" scope="request">
							<html:text property="nomeSetorComercialFinal" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>
						
						<a href="javascript:limparBorrachaDestino(2);"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr>

				<tr>
					<td><strong>Quadra Final:</strong></td>
					
					<td>
						
						<html:text maxlength="3" 
							tabindex="1"
							property="numeroQuadraFinal"
							onkeypress="validaEnterDependencia(event, 'exibirFiltrarGerarTabelasTemporariasPorLocalidadeAction.do?objetoConsulta=7',this,document.forms[0].codigoSetorComercialInicial.value,'Setor Comercial');return isCampoNumerico(event);"
							size="3"/>
							
							<logic:present name="idQuadraFinalNaoEncontrada">
								<logic:equal name="idQuadraFinalNaoEncontrada" value="exception">
									<html:text property="descricaoQuadraFinal" size="40" maxlength="30"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</logic:equal>
							</logic:present> 
							<logic:notPresent name="idQuadraFinalNaoEncontrada">
								<logic:empty name="GerarTabelasTemporariasPorLocalidadeActionForm" property="numeroQuadraFinal">
									<html:text property="descricaoQuadraFinal" value="" size="40"
										maxlength="30" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000"
										style="display: none" />
								</logic:empty>
							</logic:notPresent>
					</td>
				</tr>
				
				<tr>
					<td><strong>Rota Final:</strong></td>
					
					<td>
						
						<html:text maxlength="4" 
							tabindex="1"
							property="rotaFinal"
							onkeypress="return isCampoNumerico(event);habilitaOuDesabilitaCampos();" 
							size="5"/>
					</td>
				</tr>
				
				<tr>
					<td colspan="2"><hr></td>
				</tr>
				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right" colspan="2">
					<div align="left"><strong><font color="#FF0000">*</font></strong>

					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
								
				<tr>
					<td height="24" >
			          	<input type="button" 
			          		class="bottonRightCol" 
			          		value="Limpar" 
			          		onclick="javascript:limpar();"/>
			          	<font color="#FF0000"> <input type="button"
							name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
							onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
						</font>
					</td>
				
					<td align="right">
						<input type="button" 
							name="Button" 
							class="bottonRightCol" 
							value="Gerar" 
							onClick="javascript:validarForm()" />
					</td>
					
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
<%@ include file="/jsp/util/rodape.jsp"%>


		
</html:form>
</html:html>