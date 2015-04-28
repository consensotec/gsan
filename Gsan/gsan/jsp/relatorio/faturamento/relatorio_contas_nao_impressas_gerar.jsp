<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<%@ page import="gsan.util.ConstantesSistema"%>
<%@ page import="gsan.util.Util"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"  formName="GerarRelatorioContasNaoImpressasActionForm"/>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript">
	
	function validarForm(){
		
		var form = document.forms[0];
		if(validateGerarRelatorioContasNaoImpressasActionForm(form) && 
			validarCampos() && validaMesAno(form)){
			
			toggleBox('demodiv',1);
		}
	}
	
	function validaMesAno(form){
		var form = document.GerarRelatorioContasNaoImpressasActionForm;
		
		if (!validaAnoMesNumerico(form.anoMesReferencia)){
			alert("Mês/Ano de faturamento inválido.");
			return false;
		}

		data = new Date();
		ano = data.getFullYear();

		if (!verificaAnoMesDataAtualMensagemPersonalizada(form.anoMesReferencia,"Mês/Ano de faturamento inválido.", ano)){
			return false;
		}
		
		return true;
	}

	//Author:Thúlio Araújo Data:02/01/2012 - Verifica o Ano Mes e Ano Atual com a mensagem do alert personalida ao campo 
	function verificaAnoMesDataAtualMensagemPersonalizada(mydata,mensagem,anoAtual) {
		
		var situacao = true;
		if (mydata.value.length > 0) {
		
			if (mydata.value.length == 7) {
		
				dia = 1;
	    		mes = mydata.value.substring(0,2); 
		    	ano = mydata.value.substring(3,7); 

	    		if ((!isNaN(mes) || !isNaN(ano)) && (mes.indexOf('.') == -1 && mes.indexOf(',') == -1 && mes.indexOf('/') == -1) &&
					(ano.indexOf('.') == -1 && ano.indexOf(',') == -1 && ano.indexOf('/') == -1)) {
	    	
	    			// verifica se o mes e valido 
		    		if ((mes * 1) >= 1 && (mes * 1) <= 12 ) { 
		        
			        	// verifica se o ano e valido
		        		if ((ano * 1) != 0 && (ano * 1) >= 1980 && (ano * 1) <= anoAtual) {
		        	
				        	// verifica se e ano bissexto 
				    		if ((mes * 1) == 2 && ((dia * 1) < 1 || (dia * 1) > 29 
				    			|| ((dia * 1) > 28 && (((ano * 1) / 4) != (ano * 1) / 4)))) { 
				       			situacao = false;
				       			alert(mensagem);
				       			mydata.value = "";
				   				mydata.focus(); 
				    		}
				        }
				        else{
				        	situacao = false;
				        	alert(mensagem);
				        	mydata.value = "";
				   			mydata.focus();
				        }
				    }
			    	else{
		    			situacao = false;
		    			alert(mensagem);
		   				mydata.value = "";
			   			mydata.focus();
			    	} 
				}
				else{
					situacao = false;
					alert(mensagem);
			   		mydata.value = "";
			   		mydata.focus();
				}
		    }else{
				situacao = false;
				alert(mensagem);
		   		mydata.value = "";
		   		mydata.focus();
			}
		}
	    
	    return situacao;
	}
	
	function validarCampos(){
		
		var form = document.forms[0];
		
		msg = verificarAtributosInicialFinal(form.localidadeInicial,form.localidadeFinal,"Localidade");
		if( msg != ""){
			alert(msg);
			return false;
		}else{
			msg = verificarAtributosInicialFinal(form.setorComercialInicial,form.setorComercialFinal,"Setor Comercial");
			if( msg != ""){
				alert(msg);
				return false;
			}else{
				msg = verificarAtributosInicialFinal(form.rotaInicial,form.rotaFinal,"Rota");
				if( msg != ""){
					alert(msg);
					return false;
				}else{
					msg = verificarAtributosInicialFinal(form.sequencialRotaInicial,form.sequencialRotaFinal,"Sequencial da Rota");
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
  	
  		form.action='exibirGerarRelatorioContasNaoImpressasAction.do';
	    form.submit();
  	}
	
  	function limpar(){

  		var form = document.forms[0];

  		form.anoMesReferencia.value = "";
		form.localidadeInicial.value = "";
		form.localidadeFinal.value = "";

		form.setorComercialInicial.value = "";
		form.setorComercialFinal.value = "";

		form.rotaInicial.value = "";
		form.rotaFinal.value = "";

		form.sequencialRotaInicial.value = "";
		form.sequencialRotaFinal.value = "";
		
  		form.gerenciaRegional.value = "-1";
  		form.unidadeNegocio.value = "-1";
  		form.grupoFaturamento.value = "-1";

  		limparOrigem(1);
  		limparOrigem(2);
  		
  		form.action='exibirGerarRelatorioContasNaoImpressasAction.do?menu=sim';
	    form.submit();
  	}
  	
  	function replicarLocalidade(){
		var formulario = document.forms[0]; 
		
		formulario.localidadeFinal.value = formulario.localidadeInicial.value;
		formulario.setorComercialInicial.focus;
	}
	
	function replicarSetorComercial(){
		var formulario = document.forms[0]; 
		formulario.setorComercialFinal.value = formulario.setorComercialInicial.value;
		formulario.rotaInicial.focus;
	} 
	
	function replicarRota(){
		var formulario = document.forms[0]; 
		formulario.rotaFinal.value = formulario.rotaInicial.value;
		formulario.sequencialRotaInicial.focus;
	} 
  	
	function replicarSequencialRota(){
		var formulario = document.forms[0];
		formulario.sequencialRotaFinal.value = formulario.sequencialRotaInicial.value;
	} 
	
	function limparOrigem(tipo){
		var form = document.forms[0];
		
		switch(tipo){
		
		case 1: //De localidade pra baixo

			form.nomeLocalidadeInicial.value = "";
			form.localidadeFinal.value = "";
			form.nomeLocalidadeFinal.value = "";
			form.setorComercialInicial.value = "";
		    form.setorComercialFinal.value = "";
		  	form.rotaInicial.value = "";
		   	form.sequencialRotaInicial.value = "";
		    
		case 2: //De setor para baixo

		   form.nomeSetorComercialInicial.value = "";
		   form.setorComercialFinal.value = "";
		   form.nomeSetorComercialFinal.value = "";
		   form.rotaFinal.value = "";
		   form.sequencialRotaFinal.value = "";
		   desabilitaIntervaloDiferente(2); 
			
		}
	}
	
	function limparBorrachaOrigem(tipo){
		var form = document.forms[0];
		
		switch(tipo){
			case 1: //De localidara pra baixo

				form.localidadeInicial.value = "";
				form.nomeLocalidadeInicial.value = "";
				form.localidadeFinal.value = "";
				form.nomeLocalidadeFinal.value = "";
				desabilitaIntervaloDiferente(1);
				break;
			case 2: //De setor para baixo
		     	
		     	form.setorComercialInicial.value = "";
		     	form.nomeSetorComercialInicial.value = "";
		     	form.setorComercialFinal.value = "";
		     	form.nomeSetorComercialFinal.value = "";
		     	desabilitaIntervaloDiferente(2);
		}
	}
	
	function limparBorrachaDestino(tipo){
		var form = document.forms[0];

		switch(tipo){
			case 1: //De localidade pra baixo
				form.localidadeFinal.value = "";
				form.nomeLocalidadeFinal.value = "";
				form.setorComercialFinal.value = "";
				
			case 2: //De setor para baixo		   
		   		form.setorComercialFinal.value = ""; 
		   		form.nomeSetorComercialFinal.value = "";		   
		}
	}
	
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

		var form = document.forms[0];

		if (tipoConsulta == 'localidadeOrigem') {
      		
      		form.localidadeInicial.value = codigoRegistro;
	  		form.nomeLocalidadeInicial.value = descricaoRegistro;
	  		
	  		form.localidadeFinal.value = codigoRegistro;
      		form.nomeLocalidadeFinal.value = descricaoRegistro;
      		
	  		form.nomeLocalidadeInicial.style.color = "#000000";
	  		form.nomeLocalidadeFinal.style.color = "#000000";
	  		
	  		form.action='exibirGerarRelatorioContasNaoImpressasAction.do';
	   		form.submit();
	  		
	  		form.setorComercialInicial.focus();
	  		desabilitaIntervaloDiferente(1);
		}

		if (tipoConsulta == 'localidadeDestino') {
		
      		form.localidadeFinal.value = codigoRegistro;
      		form.nomeLocalidadeFinal.value = descricaoRegistro;
	  		form.nomeLocalidadeFinal.style.color = "#000000";
			
			form.action='exibirGerarRelatorioContasNaoImpressasAction.do';
	   		form.submit();
			
	  		form.setorComercialFinal.focus();
	  		desabilitaIntervaloDiferente(1);
		}
	}
	
	
	function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {

		var form = document.forms[0];

		if (tipoConsulta == 'setorComercialOrigem') {
		  	form.setorComercialInicial.value = codigoRegistro;
		  	form.nomeSetorComercialInicial.value = descricaoRegistro;
		  	form.nomeSetorComercialInicial.style.color = "#000000"; 
		  	
		  	form.setorComercialFinal.value = codigoRegistro;
		  	form.nomeSetorComercialFinal.value = descricaoRegistro;
		  	form.nomeSetorComercialFinal.style.color = "#000000";
		  	desabilitaIntervaloDiferente(2);
		  	
		  	form.action='exibirGerarRelatorioContasNaoImpressasAction.do';
	   		form.submit();
		}

		if (tipoConsulta == 'setorComercialDestino') {
		  	form.setorComercialFinal.value = codigoRegistro;
		  	form.nomeSetorComercialFinal.value = descricaoRegistro;
		  	form.nomeSetorComercialFinal.style.color = "#000000";
		  	desabilitaIntervaloDiferente(2);
		  	
		  	form.action='exibirGerarRelatorioContasNaoImpressasAction.do';
	   		form.submit();

		}
	}
	function desabilitaIntervaloDiferente(){
	var form = document.GerarRelatorioContasNaoImpressasActionForm;	
		    if(form.localidadeInicial.value != form.localidadeFinal.value){	        
		        form.setorComercialInicial.disabled = true;		
			 	form.setorComercialFinal.disabled = true;
			 	form.rotaInicial.disabled = true;
			 	form.rotaFinal.disabled = true;
			 	form.sequencialRotaInicial.disabled = true;
			 	form.sequencialRotaFinal.disabled = true;
			 	form.setorComercialInicial.value = "";		
			 	form.setorComercialFinal.value = "";
	 			form.nomeSetorComercialInicial.value = "";		
			 	form.nomeSetorComercialFinal.value = "";
			 	form.rotaInicial.value = "";
			 	form.rotaFinal.value = "";
			 	form.sequencialRotaInicial.value = "";
			 	form.sequencialRotaFinal.value= "";
			 	
			 	
			  }else{
			 	form.setorComercialInicial.disabled = false;		
			 	form.setorComercialFinal.disabled = false;
			 	form.rotaInicial.disabled = false;
			 	form.rotaFinal.disabled = false;
			 	form.sequencialRotaInicial.disabled = false;
			 	form.sequencialRotaFinal.disabled = false;
			 }		
	}		
	function desabilitaIntervaloDiferenteSetor(){
	var form = document.GerarRelatorioContasNaoImpressasActionForm;	
		    if(form.setorComercialInicial.value != form.setorComercialFinal.value || form.setorComercialInicial.disabled == true){	        	        
			 	form.rotaInicial.disabled = true;
			 	form.rotaFinal.disabled = true;
			 	form.sequencialRotaInicial.disabled = true;
			 	form.sequencialRotaFinal.disabled = true;
		 		form.rotaInicial.value = "";
			 	form.rotaFinal.value = "";
			 	form.sequencialRotaInicial.value = "";
			 	form.sequencialRotaFinal.value= "";	
			  }else{
			 	form.rotaInicial.disabled = false;
			 	form.rotaFinal.disabled = false;
			 	form.sequencialRotaInicial.disabled = false;
			 	form.sequencialRotaFinal.disabled = false;
			 }		
	}	

	
	function limparDestino(tipo){
		var form = document.GerarRelatorioContasNaoImpressasActionForm;
	
		switch(tipo){
			case 1: //De localidade pra baixo
				 if(form.localidadeInicial.value != form.localidadeFinal.value){
	
	             	form.setorComercialFinal.value = "";
				 	form.rotaFinal.value = "";
				 	form.sequencialRotaFinal.value = "";
				 	form.nomeSetorComercialFinal.value = "";
			    }
			case 2: //De setor para baixo		 
				if(form.setorComercialInicial.value != form.setorComercialFinal.value) {
				  	form.rotaFinal.value = "";
				 	form.sequencialRotaFinal.value = "";	   
				}   		   		   
			case 3://De quadra pra baixo
			   if(form.rotaInicial.value != form.rotaFinal.value) {
				 	form.sequencialRotaFinal.value = "";
		           
		       }    
	           
	           
		}
	}

  	//Arthur
	function desabilitaIntervaloDiferente(tipo){

		var form = document.GerarRelatorioContasNaoImpressasActionForm;
		
		switch(tipo){
			case 1: //De localidade 
	
			    if(form.localidadeInicial.value != form.localidadeFinal.value){
	
			        form.setorComercialInicial.disabled = true;
				 	form.rotaInicial.disabled = true;
			     	form.sequencialRotaInicial.disabled = true;
	             	form.setorComercialFinal.disabled = true;
				 	form.rotaFinal.disabled = true;
				 	form.sequencialRotaFinal.disabled = true;
				 	
				 	form.setorComercialInicial.value = "";
				 	form.rotaInicial.value = "";
			     	form.sequencialRotaInicial.value = "";
	             	form.setorComercialFinal.value = "";
				 	form.rotaFinal.value = "";
				 	form.sequencialRotaFinal.value = "";
				 	form.nomeSetorComercialInicial.value = "";
				 	form.nomeSetorComercialFinal.value = "";
				 	
				 
				  }else{
				 	form.setorComercialInicial.disabled = false;
				 	form.rotaInicial.disabled = false;
			     	form.sequencialRotaInicial.disabled = false;
	             	form.setorComercialFinal.disabled = false;
				 	form.rotaFinal.disabled = false;
				 	form.sequencialRotaFinal.disabled = false;
				
				  }
		  	break;
		  	
		  	case 2: //Setor Comercial
		  		if(form.setorComercialInicial.value != form.setorComercialFinal.value) {
		
			  		form.rotaInicial.disabled = true;
			     	form.sequencialRotaInicial.disabled = true;
			     	form.rotaFinal.disabled = true;
				 	form.sequencialRotaFinal.disabled = true;
				 	
				 	form.rotaInicial.value = "";
			     	form.sequencialRotaInicial.value = "";
			     	form.rotaFinal.value = "";
				 	form.sequencialRotaFinal.value = "";
		  		
		  		} else {
				 	form.rotaInicial.disabled = false;
			     	form.sequencialRotaInicial.disabled = false;
				 	form.rotaFinal.disabled = false;
				 	form.sequencialRotaFinal.disabled = false;
		  		}
		  	break; 	
		  	
		  	case 3: //Rota
		  		if(form.rotaInicial.value != form.rotaFinal.value) {
		
			     	form.sequencialRotaInicial.disabled = true;
				 	form.sequencialRotaFinal.disabled = true;
				 	
			     	form.sequencialRotaInicial.value = "";
				 	form.sequencialRotaFinal.value = "";
		  		
		  		} else {
			     	form.sequencialRotaInicial.disabled = false;
				 	form.sequencialRotaFinal.disabled = false;
		  		}
		  	break; 	
		  }
	}
	
</script>

</head>

<body leftmargin="5" topmargin="5" onload = "desabilitaIntervaloDiferente(${requestScope.campoDesabilita})">

<html:form action="/gerarRelatorioContasNaoImpressasAction"
	name="GerarRelatorioContasNaoImpressasActionForm"
	type="gsan.gui.relatorio.faturamento.GerarRelatorioContasNaoImpressasActionForm"
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
					<td class="parabg">Contas n&atilde;o Impressas pela Anormalidade</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				
				<tr>
					<td colspan="2">Para gerar o relat&oacute;rio, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td><strong>M&ecirc;s/Ano Refer&ecirc;ncia:<font color="#FF0000">*</font></strong></td>
					<td align="left"><html:text property="anoMesReferencia" size="10"
						tabindex="2" maxlength="7"
						onkeyup="mascaraAnoMes(this, event);somente_numero(this);"
						onkeypress="return isCampoNumerico(event);"
						/>&nbsp;mm/aaaa
					</td>
				</tr>
				<tr>
			        <td width="10%"><strong>Grupo de Faturamento:</strong></td>
			        <td width="90%"><html:select property="grupoFaturamento">
			         		<html:option value="-1">&nbsp;</html:option>
			             <html:options collection="faturamentoGrupos" labelProperty="descricao" property="id"/>
			            </html:select>
			        </td>
		        </tr>
				<tr>
					<td>
						<strong>Ger&ecirc;ncia Regional:</strong>
					</td>

					<td>
						<strong> 
						<html:select property="gerenciaRegional" 
							style="width: 230px;" 
							onchange="javascript:reloadForm();">
							
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>
					
							<logic:present name="colecaoGerenciaRegional" scope="request">
								<html:options collection="colecaoGerenciaRegional"
									labelProperty="nome" 
									property="id" />
							</logic:present>
						</html:select> 														
						</strong>
					</td>
				</tr>

				<tr>
					<td>
						<strong>Unidade de Neg&oacute;cio:</strong>
					</td>

					<td>
						<strong> 
						<html:select property="unidadeNegocio" style="width: 230px;">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>
					
							<logic:present name="colecaoUnidadeNegocio" scope="request">
								<html:options collection="colecaoUnidadeNegocio"
									labelProperty="nome" 
									property="id" />
							</logic:present>
						</html:select> 														
						</strong>
					</td>
				</tr>				
              	
              	<tr>
					<td>
						<strong>Empresa:</strong>
					</td>
					<td>
						<strong>
							<html:select property="empresaID" style="width: 230px;">
								<html:option
									value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
								</html:option>
								<logic:present name="colecaoEmpresa" scope="request">
									<html:options collection="colecaoEmpresa"
										labelProperty="descricao" property="id" />
								</logic:present>
							</html:select>
						</strong>
					</td>
				</tr>
              	
				<tr>
					<td><strong>Localidade Inicial:</strong></td>
					
					<td>
						
						<html:text maxlength="3" 
							tabindex="1"
							property="localidadeInicial" 
							size="3"
							onblur="javascript:replicarLocalidade();" 
							onkeyup="javascript:limparOrigem(1);desabilitaIntervaloDiferente(1);"
							onkeypress="javascript:limparOrigem(1);validaEnterComMensagem(event, 'exibirGerarRelatorioContasNaoImpressasAction.do?objetoConsulta=1&campoDesabilita=1','localidadeInicial','Localidade Inicial');"/>
							
						<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'origem', null, null, 275, 480, '',document.forms[0].localidadeInicial);limparOrigem(1);">
							<img width="23" 
								height="21" 
								border="0" 
								style="cursor:hand;"
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
							property="setorComercialInicial" 
							size="3"
							onblur="javascript:replicarSetorComercial();"
							onkeyup="javascript:limparOrigem(2);desabilitaIntervaloDiferente(2);"
							onkeypress="limparOrigem(2);validaEnterComMensagem(event, 'exibirGerarRelatorioContasNaoImpressasAction.do?objetoConsulta=2&campoDesabilita=2','setorComercialInicial','Setor Comercial Inicial');"/>
							
						<a href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialOrigem', 'idLocalidade', document.forms[0].localidadeInicial.value , 275, 480, 'Informe Localidade Inicial',document.forms[0].setorComercialInicial);
						         limparOrigem(2);">
							<img width="23" 
								height="21" 
								border="0" 
								style="cursor:hand;"
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
					<td><strong>Rota Inicial:</strong></td>
					
					<td>
						
						<html:text maxlength="5" 
							tabindex="1"
							property="rotaInicial"
							onblur="javascript:replicarRota();"
							size="5"/>
					</td>
				</tr>

				<tr>
					<td><strong>Sequencial Inicial da Rota:</strong></td>
					
					<td>
						
						<html:text maxlength="5" 
							tabindex="1"
							property="sequencialRotaInicial"
							onblur="javascript:replicarSequencialRota();"
							size="5"/>
					</td>
				</tr>
				
				
				<tr>
					<td><strong>Localidade Final:</strong></td>
					
					<td>
						
						<html:text maxlength="3" 
							tabindex="1"
							property="localidadeFinal" 
							size="3"
							onkeypress="validaEnterComMensagem(event, 'exibirGerarRelatorioContasNaoImpressasAction.do?objetoConsulta=3&campoDesabilita=1','localidadeFinal','Localidade Final');"
							onkeyup="javascript:limparDestino(1);desabilitaIntervaloDiferente(1);"
							/>
						
						<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'destino', null, null, 275, 480, '',document.forms[0].localidadeFinal);limparDestino(1);">
							<img width="23" 
								height="21" 
								border="0" 
								style="cursor:hand;"
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
						
						<html:text maxlength="3" property="setorComercialFinal"
							size="3"
							onkeypress="validaEnterComMensagem(event, 'exibirGerarRelatorioContasNaoImpressasAction.do?objetoConsulta=4&campoDesabilita=2', 'setorComercialFinal', 'Localidade Final');"
							onkeyup = "javascript:limparDestino(2);desabilitaIntervaloDiferente(2);"
							tabindex="8"/>
								
						<a href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialDestino', 'idLocalidade', document.forms[0].localidadeFinal.value, 275, 480, 'Informe Localidade Final',document.forms[0].setorComercialFinal);
							        limparDestino(2);">
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
					<td><strong>Rota Final:</strong></td>
					
					<td>
						
						<html:text maxlength="5" 
							tabindex="1"
							property="rotaFinal" 
							size="5"
							onkeyup="limparDestino(3);desabilitaIntervaloDiferente(3);"/>
					</td>
				</tr>

				<tr>
					<td><strong>Sequencial Final da Rota:</strong></td>
					
					<td>
						
						<html:text maxlength="5" 
							tabindex="1"
							property="sequencialRotaFinal" 
							size="5"/>
					</td>
				</tr>		
				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
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
</body>
	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioContasNaoImpressasAction.do" />

</html:form>
</html:html>