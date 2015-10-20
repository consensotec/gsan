<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>


<%@ page import="gcom.util.ConstantesSistema" %>
<%@ page import="gcom.cadastro.localidade.SetorComercial" %>
<%@ page import="gcom.cadastro.localidade.Quadra" %>
<%@ page import="gcom.micromedicao.Rota" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="FiltrarContaComunicadoActionForm" />

<script language="JavaScript">

function validarGrupoFaturamento(visulizar){

	var form = document.forms[0];
	
	if(verificarListBoxSelecionado(form.grupoFaturamento)){
		form.gerenciaRegional.value = "";
	  	form.localidade.value = "";
	  	form.localidadeDescricao.value = "";
	  	reiniciarListBox(form.setorComercial);	
	  	reiniciarListBox(form.rota);	
	  	reiniciarListBox(form.quadra);
		
		form.gerenciaRegional.disabled = true;
	  	form.localidade.disabled = true;
	  	form.setorComercial.disabled = true;
	  	form.rota.disabled = true;	
	  	form.quadra.disabled = true;	   	

	}else{
		form.gerenciaRegional.disabled = false;
	  	form.localidade.disabled = false;
	  	form.setorComercial.disabled = false;		    	
	  	form.rota.disabled = false;	
	  	form.quadra.disabled = false;	
	
	}
}

function desabilitarGrupoFaturamento(){
    
    var form = document.forms[0];
    if (form.gerenciaRegional.value != "-1"){
	  form.grupoFaturamento.disabled = true;
      
      form.localidade.value = "";
      form.localidadeDescricao.value = "";
      form.localidade.disabled = true;
      
	  reiniciarListBox(form.setorComercial);
	  form.setorComercial.disabled = true;

	  reiniciarListBox(form.rota);
	  form.rota.disable = true;
	  
	  reiniciarListBox(form.quadra);
	  form.quadra.disable = true;	  
    }
    else{
      form.grupoFaturamento.disabled = false;
      form.localidade.disabled = false;
	  form.setorComercial.disabled = false;
	  form.rota.disabled = false;	 
	  form.quadra.disabled = false;	 
	  form.grupoFaturamento.disabled = false; 
    }
}
  
  function desabilitarGrupoFaturamento2(){
    var form = document.forms[0];
    
    if (form.localidade.value != ""){

        
  	  form.grupoFaturamento.disabled = true;
      
      form.gerenciaRegional.value = "";
      form.gerenciaRegional.disabled = true;
      
    }
    else{
      
      form.grupoFaturamento.disabled = false;
      form.gerenciaRegional.disabled = false;
      
    }
  }
  
  function verificaPreenchimentoLocalidade(){
  
  	var form = document.forms[0];
    
    if (form.localidadeDescricao.value == ""){
      
      if (form.gerenciaRegional.value == "-1" || form.gerenciaRegional.value == ""){
      	form.grupoFaturamento.disabled = false;
      }
      else{
    	  form.grupoFaturamento.disabled = true;
      }
      
      form.gerenciaRegional.disabled = false;

    }
    else{
      
  	  form.grupoFaturamento.disabled = true;
      
      form.gerenciaRegional.value = "";
      form.gerenciaRegional.disabled = true;

    }
  }
  

 function chamaPopupLocalidade(){
 	var form = document.forms[0];
 	if(form.localidade.disabled == false){
	 	abrirPopup('exibirPesquisarLocalidadeAction.do');
	 	limpaLocalidadeSemApagarCodigo();
 	}
 }
 
 
function validaForm(){
	var form = document.forms[0];
	if (validateFiltrarContaComunicadoActionForm(form)){
		if (verificaAnoMesReferencia(form.referenciaInicial) && verificaAnoMesReferencia(form.referenciaFinal) && verificarPreenchimentoReferencia()){
			
			submeterFormPadrao(form);
		} 
	}
}
 
 function verificaAnoMesReferencia(mydata) {
	
	var situacao = true;
	
	if (mydata.value.length == 7) {
	
		dia = 1;
    	mes = mydata.value.substring(0,2); 
    	ano = mydata.value.substring(3,7); 

    	if ((!isNaN(mes) || !isNaN(ano)) && (mes.indexOf('.') == -1 && mes.indexOf(',') == -1 && mes.indexOf('/') == -1) &&
			(ano.indexOf('.') == -1 && ano.indexOf(',') == -1 && ano.indexOf('/') == -1)) {
    	
    		// verifica se o mes e valido 
	    	if ((mes * 1) >= 1 && (mes * 1) <= 12 ) { 
	        
	        	// verifica se o ano e valido
	        	if ((ano * 1) != 0 && (ano * 1) >= 1980) {
	        	
	        		// verifica se e ano bissexto 
	    			if ((mes * 1) == 2 && ((dia * 1) < 1 || (dia * 1) > 29 
	    				|| ((dia * 1) > 28 && (((ano * 1) / 4) != (ano * 1) / 4)))) { 
	       				situacao = false; 
	    			}
	        	}
	        	else{
	        		situacao = false;
	        	}
	    	}
	    	else{
	    		situacao = false;
	    	} 
		}
		else{
			situacao = false;
		}
    }
    else if (mydata.value.length > 0){
    	situacao = false;
    }
    
    if (!situacao) { 
	   alert("Referência do Faturamento inválida.");
	   mydata.value = "";
	   mydata.focus(); 
	}
	
	return situacao;
}
 
 
 function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	
	var form = document.forms[0];
	
	if (tipoConsulta == "localidade") {
      
      limpaLocalidade();
      
      form.localidade.value = codigoRegistro;
      form.localidadeDescricao.value = descricaoRegistro;
      form.localidadeDescricao.style.color = "#000000";
      form.setorComercial.focus();
      form.grupoFaturamento.value = "";
      form.grupoFaturamento.disabled = true;
      form.gerenciaRegional.value = "";
      form.gerenciaRegional.disabled = true;
      
      reiniciarListBox(form.setorComercial);
      reiniciarListBox(form.rota);
 	  reiniciarListBox(form.quadra);
      
      form.action = "exibirFiltrarContaComunicadoAction.do"
 	  submeterFormPadrao(form);

    }
}
 
 function limpaLocalidade(){
 	var form = document.forms[0];
 	if(form.localidade.disabled == false){
	 	form.localidade.value = "";
	 	form.localidadeDescricao.value = "";
	 	reiniciarListBox(form.rota);
	 	form.rota.disabled = true;
	 	reiniciarListBox(form.quadra); 	
		form.quadra.disabled = true;
		
		form.setorComercial.value = "";	
		reiniciarListBox(form.setorComercial);
	    form.setorComercial.disabled = true;
			
		if (form.gerenciaRegional.value == "-1"){
		  form.grupoFaturamento.disabled = false;
	      form.gerenciaRegional.disabled = false;
	      form.localidade.disabled = false;
		  form.setorComercial.disabled = false;
		  form.rota.disabled = false;
		  form.quadra.disabled = false;
		  
		} else {
			form.setorComercial.disabled = true;
			form.rota.disabled = true;
			form.quadra.disabled = true;
			form.grupoFaturamento.disabled = true;
		}
	
	 	if ( form.grupoFaturamento.value != "" ) {
	 		form.setorComercial.disabled = true;
	 		form.rota.disabled = true;
	 		form.quadra.disabled = true;
	 		form.localidade.disabled = true;
	 		form.gerenciaRegional.disabled = true;
	 	}
		
		if (!form.localidade.disabled){
	 		form.localidade.focus();
	 	}
 	}
 
 }

 function borrachaLocalidade(){

	 var form = document.forms[0];

	 if(form.localidade.disabled == false){
		 form.grupoFaturamento.disabled = false;
	     form.gerenciaRegional.disabled = false;
	     form.localidade.disabled = false;
		  form.setorComercial.disabled = false;
		  form.rota.disabled = false;
		  form.quadra.disabled = false;
		  form.localidade.value = "";
	 	form.localidadeDescricao.value = "";
	 	reiniciarListBox(form.rota);
	 	form.rota.disabled = true;
	 	reiniciarListBox(form.quadra); 	
		form.quadra.disabled = true;
		
		form.setorComercial.value = "";	
		reiniciarListBox(form.setorComercial);
	    form.setorComercial.disabled = true;
	 }
 }

 function limpaLocalidadeSemApagarCodigo(){
 	var form = document.forms[0];
 	if(form.localidade.disabled == false){
	 	form.localidadeDescricao.value = "";
	 	reiniciarListBox(form.setorComercial);
	 	reiniciarListBox(form.rota); 	
	 	reiniciarListBox(form.quadra); 	
	 	
	 	form.setorComercial.value = "";	
		reiniciarListBox(form.setorComercial);
	    form.setorComercial.disabled = true;
 	}
 
 }

function desabilitaOuHabilitaRota(){
 	var form = document.forms[0];
		cont = 0;
	 	for ( i = 0; i < form.setorComercial.length; i++) {
			if ( form.setorComercial[i].selected ) {
				cont += 1;
			}
		}
	 	form.rota.value="";
	 	reiniciarListBox(form.rota);	
	 	form.quadra.value="";
	  	reiniciarListBox(form.quadra);				
		if ( cont == 1 ){
			for ( i = 0; i < form.setorComercial.length; i++) {
				if ( form.setorComercial[i].selected ) {
					if ( form.setorComercial[i].value != "" ) {
						obj = form.setorComercial[i].value;
						form.action = 'exibirFiltrarContaComunicadoAction.do?acao=rota&id='+obj;
						form.submit();
					} else {
						form.action = 'exibirFiltrarContaComunicadoAction.do';
						form.submit();
					}
				}
			}
		}
}

function desabilitaOuHabilitaQuadra(){
 	var form = document.forms[0];
		cont = 0;
	 	for ( i = 0; i < form.rota.length; i++) {
			if ( form.rota[i].selected ) {
				cont += 1;
			}
		}
	 	form.quadra.value="";
	  	reiniciarListBox(form.quadra); 				
		if ( cont == 1 ){
			for ( i = 0; i < form.rota.length; i++) {
				if ( form.rota[i].selected ) {
					if ( form.rota[i].value != "" ) {
						obj = form.rota[i].value;
						form.action = 'exibirFiltrarContaComunicadoAction.do?acao=quadra&id='+obj;
						form.submit();
					} else {
						form.action = 'exibirFiltrarContaComunicadoAction.do';
						form.submit();
					}
				}
			}
		}
}

function verificarPreenchimentoReferencia(){
	var form = document.forms[0];
	var situacao = true;

	if(form.referenciaInicial.value != "" && form.referenciaFinal.value == ""){
		alert("Digite a Referência Final.");
		situacao = false;
	}else if(form.referenciaInicial.value == "" && form.referenciaFinal.value != ""){
		alert("Digite a Referência Inicial.");
		situacao = false;
	}
	return situacao;
}

function desabilitaCamposVoltarManter(){
var form = document.forms[0];
	if(verificarListBoxSelecionado(form.grupoFaturamento)){

		form.gerenciaRegional.value = "";
	  	form.localidade.value = "";
	  	form.localidadeDescricao.value = "";
	  	reiniciarListBox(form.setorComercial);	
	  	reiniciarListBox(form.rota);	
	  	reiniciarListBox(form.quadra);
		
		form.gerenciaRegional.disabled = true;
	  	form.localidade.disabled = true;
	  	form.setorComercial.disabled = true;
	  	form.rota.disabled = true;	
	  	form.quadra.disabled = true;	   	

	}else if (form.gerenciaRegional.value != "-1"){

		form.gerenciaRegional.value = "";
	  	form.localidade.value = "";
	  	form.localidadeDescricao.value = "";
	  	reiniciarListBox(form.setorComercial);	
	  	reiniciarListBox(form.rota);	
	  	reiniciarListBox(form.quadra);
	  	reiniciarListBox(form.grupoFaturamento);

		form.localidade.disabled = true;
	  	form.setorComercial.disabled = true;
	  	form.rota.disabled = true;	
	  	form.quadra.disabled = true;
	  	form.grupoFaturamento.disabled = true;
	  	
	}else if(form.localidade.value != ""){

		form.gerenciaRegional.disabled = true;
		form.grupoFaturamento.disabled = true;
	}
}

</script>
</head>

<body leftmargin="5" topmargin="5" onload="javascript:desabilitaCamposVoltarManter();">


<html:form action="/filtrarContaComunicadoAction.do"
	name="FiltrarContaComunicadoActionForm"
	type="gcom.gui.faturamento.conta.FiltrarContaComunicadoActionForm"
	method="post" onsubmit="return validateFiltrarContaComunicadoActionForm(this);" >

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
			<td width="615" valign="top" class="centercoltext">
				<table height="100%">
					<tr>
						<td colspan = "3"></td>
					</tr>
				</table>
				
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
						<td class="parabg">Filtrar Comunicado na Conta</td>
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
					</tr>
				</table>
				
				<p>&nbsp;</p>
				<table border="0" width="100%">
					<tr>
						<td colspan="3">Para Filtrar o comunicado na conta, informe os
						dados abaixo:</td>
						<logic:present scope="application" name="urlHelp">
							<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}faturamentoContaComunicadoFiltrar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
						</logic:present>
						<logic:notPresent scope="application" name="urlHelp">
							<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
						</logic:notPresent>				
					</tr>
				</table>
				<table border="0" width="100%">	
					<tr>
						<td colspan = "2">
							<table width="100%" align="center" bgcolor="#99CCFF" border="0">
								<tr>
									<td align="center"><strong>Abrangência</strong></td>
								</tr>
								<tr bgcolor="#cbe5fe">
									<td width="100%" align="center">
										<html:radio property="abrangencia" value="todos" >Todos</html:radio>
										<html:radio property="abrangencia" value="estado">Estado</html:radio>
										<html:radio property="abrangencia" value="faturamentoGrupo">Grupo de Faturamento</html:radio>
										<html:radio property="abrangencia" value="gerencia">Gerência Regional</html:radio>
										<html:radio property="abrangencia" value="localidade">Localidade</html:radio>
										<html:radio property="abrangencia" value="setor">Setor Comercial</html:radio>
										<html:radio property="abrangencia" value="rota">Rota</html:radio>
										<html:radio property="abrangencia" value="quadra">Quadra</html:radio>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td width="30%">
							<strong>Referência do Faturamento :</strong>
						</td>
						<td align="right" colspan="2">
							<div align="left">
								<html:text property="referenciaInicial"
											size="7" 
											maxlength="7" 
											onkeyup="mascaraAnoMes(this, event); "
											onkeypress="return isCampoNumerico(event);"/> à 
								<html:text property="referenciaFinal"
											size="7" 
											maxlength="7" 
											onkeyup="mascaraAnoMes(this, event); "
											onkeypress="return isCampoNumerico(event);"/>
								mm/aaaa
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<strong>Título:</strong>
						</td>
	
						<td align="right" colspan="2">
							<div align="left">
								<html:text property="titulo"
											maxlength="100" 
											size="60"/>
							</div>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><html:radio property="tipoPesquisaTitulo"
							value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
						Iniciando pelo texto<html:radio property="tipoPesquisaTitulo"
							tabindex="5"
							value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
						Contendo o texto</td>
					</tr>
					<tr>
						<td><strong>Grupo de Faturamento:</strong></td>
						<td align="right" colspan="2">
						<div align="left"><strong> <html:select property="grupoFaturamento"
							style="width: 400px;" multiple="mutiple" size="4" onchange="validarGrupoFaturamento(1);">
							
							<logic:present name="colecaoFaturamentoGrupo">
								<html:option value="">&nbsp;</html:option>
								<html:options collection="colecaoFaturamentoGrupo"
									labelProperty="descricao" property="id" />
							</logic:present>
							
						</html:select> </strong></div>
						</td>
					</tr>
					
					<tr>
						<td colspan="3">
						
							<table width="100%" align="center" bgcolor="#99CCFF" border="0">
							<tr>
								<td align="center"><strong>Dados de Localização Geográfica</strong></td>
							</tr>
							<tr bgcolor="#cbe5fe">
								<td width="100%" align="center">
	
								<table width="100%" border="0">
									
									<tr>
										<td width="23%"><strong>Gerência Regional:</strong></td>
										<td><html:select property="gerenciaRegional"
											style="width: 230px;"
											onchange="limpaLocalidade(); desabilitarGrupoFaturamento();">
											<logic:present name="colecaoGerenciaRegional">
												<html:option value="-1">&nbsp;</html:option>
												<html:options collection="colecaoGerenciaRegional" labelProperty="nome" property="id" />
											</logic:present>
										</html:select></td>
									</tr>
									<tr>
										<td><strong>Localidade:</strong></td>
										<td>
											<html:text property="localidade" size="3"
														maxlength="3" 
														onkeyup="desabilitarGrupoFaturamento2();"
														onkeydown="limpaLocalidadeSemApagarCodigo();"
														onkeypress="validaEnter(event, 'exibirFiltrarContaComunicadoAction.do?acao=limparColecoes', 'localidade');return isCampoNumerico(event);desabilitarGrupoFaturamento2();" />
										<a href="javascript:chamaPopupLocalidade();"> 
											<img src="imagens/pesquisa.gif" style="" height="21" width="23"
												 border="0" title="Pesquisar Localidade">
										</a> 
										<logic:present name="localidadeInexistente" scope="request">
											<html:text property="localidadeDescricao" 
														size="40" 
														maxlength="40"
														readonly="true"
														style="background-color:#EFEFEF; border:0; color: #ff0000;" />
										</logic:present> 
										<logic:notPresent name="localidadeInexistente" scope="request">
											<html:text property="localidadeDescricao" 
														size="40" 
														maxlength="40"
														readonly="true"
														style="background-color:#EFEFEF; border:0; color: #000000;" />
										</logic:notPresent> 
											<a href="javascript:borrachaLocalidade();">
												<img src="imagens/limparcampo.gif" 
													 border="0" title="Apagar">
											</a>
										</td>
									</tr>
									
									<tr>
										<td><strong>Setor Comercial:</strong></td>
										<td><html:select styleId="listSetor" property="setorComercial" style="width: 400px;" multiple="mutiple" size="4" 
														 onchange="javascript:desabilitaOuHabilitaRota();" >
	
												<logic:present name="colecaoSetorComercialPorLocalidade">
													<html:option value=""/>
													
													<logic:iterate name="colecaoSetorComercialPorLocalidade" id="setorComercial" type="SetorComercial">
														
														<html:option value="<%="" + setorComercial.getId()%>">
															<bean:write name="setorComercial" property="descricao"/>
														</html:option>
														
													</logic:iterate>
													
												</logic:present>
												
											</html:select>
										</td>
									</tr>
									
									<tr>
										<td><strong>Rota:</strong></td>
										<td><html:select styleId="listRota" property="rota" style="width: 400px;" multiple="mutiple" size="4" 
														onchange="javascript:desabilitaOuHabilitaQuadra();" >
												
												<logic:present name="colecaoRotaPorSetor">
													<html:option value=""/>
													
													<logic:iterate name="colecaoRotaPorSetor" id="rota" type="Rota">
														
														<html:option value="<%="" + rota.getId()%>">
															<bean:write name="rota" property="codigo"/>
														</html:option>
														
													</logic:iterate>
													
												</logic:present>
												
											</html:select>
										</td>
									</tr>
									
									<tr>
										<td><strong>Quadra:</strong></td>
										<td><html:select property="quadra" style="width: 400px;" multiple="mutiple" size="4">
												
												<logic:present name="colecaoQuadraPorRota">
													<html:option value=""/>
													
													<logic:iterate name="colecaoQuadraPorRota" id="quadra" type="Quadra">
														
														<html:option value="<%="" + quadra.getId()%>">
															<bean:write name="quadra" property="numeroQuadra"/>
														</html:option>
														
													</logic:iterate>
													
												</logic:present>
												
											</html:select>
										</td>
									</tr>
									
									
								</table>
	
								</td>
							</tr>
						</table>
							
						</td>
					</tr>
					<tr>
										<td><strong>Indicador de Uso:</strong></td>
										<td align="right">
										<div align="left"><html:radio property="icUso" tabindex="4"
											value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>" />
										<strong>Ativo</strong> <html:radio property="icUso"
											tabindex="5"
											value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>" />
										<strong>Inativo</strong> <html:radio property="icUso"
											tabindex="5" value="" /> <strong>Todos</strong></div>
										</td>
									</tr>
					
					<tr>
						<td>&nbsp;</td>
						<td align="right">&nbsp;</td>
					</tr>
					<tr>
						<td>
							<strong> 
								<font color="#FF0000"> 
									<input type="button" name="Submit22" class="bottonRightCol" value="Limpar"
										onClick="javascript:window.location.href='/gsan/exibirFiltrarContaComunicadoAction.do?menu=sim'">
								</font> 
							</strong>
						</td>
						<td align="right">
							<input type="button" name="Submit2"
								class="bottonRightCol" value="Filtrar"
								onclick="validaForm(document.forms[0]);">
						</td>
					</tr>
				</table>
				<p>&nbsp;</p>
				<!-- Fim do Corpo - Roberta Costa --></td>
			</tr>
		</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
<script language="JavaScript">
</script>


</html:html>
