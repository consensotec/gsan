<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ include file="/jsp/util/telaespera.jsp"%>
<%@ page import="gcom.relatorio.cobranca.AcoesPenalidadeGrupoHelper" %>
<%@ page import="gcom.micromedicao.InformarItensContratoServicoHelper"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GCOM - Sistema de Gest&atilde;o Comercial</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"  formName="GerarRelatorioBoletimMedicaoCobrancaForm"/>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript">
	
	function validarForm(){
		
		var form = document.forms[0];
		if(validateGerarRelatorioBoletimMedicaoCobrancaForm(form)){
			if(form.grupoCobranca.value == "-1" &&
					form.empresa.value == "-1"){
  				alert("Informe Grupo de Cobrança ou Empresa");
  				return false;
			}
			if(form.empresa.value != "-1"){
			 	if (form.idNumeroContrato == null || !campoSelecionado(form)) {
					alert("Selecione o contrato da empresa.");
					return false;
				}
			}
			if (form.formaGeracao[0].checked == false
				&& form.formaGeracao[1].checked == false) {
  				alert("Selecione a Operação.");
  				return false;
			} else if (form.formaGeracao[1].checked == true) {
				if (form.idLocalidadeFinal.value != ""
					&& form.idLocalidadeInicial.value == "") {
  					alert("Informe a Localidade Inicial.");
  					return false;
				} else if (form.idLocalidadeFinal.value != ""
					&& form.idLocalidadeInicial.value > form.idLocalidadeFinal.value ) {
  					alert("Localidade Final é menor que Localidade Inicial.");
  					return false;
				} else {
					toggleBox('demodiv',1);
				}
			} else {
				if(form.formaGeracao[0].checked){
					var checks = document.getElementsByName("idAcao");
					var selecionou = false;
					for(i=0; i<checks.length; i++){
						if(checks[i].checked){
							var str = checks[i].getAttribute('id').split("idAcao");
							var jus = document.getElementById('jus'+str[1]);
							if(jus.value == null || jus.value ==''){
								alert('preencha a justificativa');
								return false;
							}
						}
					}
					
					botaoAvancarTelaEspera('/gsan/exibirSolicitarGeracaoBoletimMedicaoCobrancaAction.do');
				}
				else{
					botaoAvancarTelaEspera('/gsan/exibirSolicitarGeracaoBoletimMedicaoCobrancaAction.do');
				}
			}
		}
	}
	
  	function limpar(){	
		window.location.href = "exibirSolicitarGeracaoBoletimMedicaoCobrancaAction.do?menu=sim";
  	}		
	
	function selecionarOperacao(tipo){
		var form = document.forms[0];
		if(tipo==1){
			form.tipoOperacao.value = 1;
			document.getElementById('tipoEmitir').style.display = 'none';
			document.getElementById('tipoGerar').style.display = 'block';			
		}else if(tipo==2){
			form.tipoOperacao.value = 2;
			document.getElementById('tipoEmitir').style.display = 'block';
			document.getElementById('tipoGerar').style.display = 'none';
			form.action='exibirSolicitarGeracaoBoletimMedicaoCobrancaAction.do?pesquisarPenalidades=false';
   			form.submit();			
		}
	}
	
	function limparDescLocalidadeInicial(form){
	
		form.idLocalidadeFinal.value = "";
    	form.descricaoLocalidadeInicial.value = "";
    	form.descricaoLocalidadeFinal.value = "";
		
	}
   
   function habilitarPesquisaLocalidade(form, tipo) {
		if (form.idLocalidadeInicial.disabled == false) 
		{
		    if(tipo == 'origem')
		    {
				chamarPopup('exibirPesquisarLocalidadeAction.do', 'origem', 'indicadorUsoTodos', '1', 275, 480, '',form.idLocalidadeInicial.value);
			}
			else if(tipo == 'destino')
		    {
				chamarPopup('exibirPesquisarLocalidadeAction.do', 'destino', 'indicadorUsoTodos', '1', 275, 480, '',form.idLocalidadeFinal.value);
			}
		}	
	}
	
	function limparLocalidadeInicial(form) {
	
		form.idLocalidadeInicial.value = "";
		form.idLocalidadeFinal.value = "";
    	form.descricaoLocalidadeInicial.value = "";
    	form.descricaoLocalidadeFinal.value = "";
		
	}

	function limparDescLocalidadeFinal(form) {
	
    	form.descricaoLocalidadeFinal.value = "";
    	
	}
	
	function limparLocalidadeFinal(form){
	
		form.idLocalidadeFinal.value = "";
		form.descricaoLocalidadeFinal.value = "";
		
	}
	
	function verificaSelecao() {
		
		var form = document.forms[0];
		if(form.tipoOperacao.value==1){
			document.getElementById('tipoEmitir').style.display = 'none';
			document.getElementById('tipoGerar').style.display = 'block';
		}else if(form.tipoOperacao.value==2){
			document.getElementById('tipoEmitir').style.display = 'block';
			document.getElementById('tipoGerar').style.display = 'none';
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
	
 	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

	    var form = document.forms[0];
	
	    if (tipoConsulta == 'localidadeOrigem') {
	      	limparLocalidadeInicial(form);
	      	limparLocalidadeFinal(form);
	      	
	      	form.idLocalidadeInicial.value = codigoRegistro;
	    	form.descricaoLocalidadeInicial.value = descricaoRegistro;
	    	form.descricaoLocalidadeInicial.style.color = "#000000";
	    	
	      	form.idLocalidadeFinal.value = codigoRegistro;
	    	form.descricaoLocalidadeFinal.value = descricaoRegistro;
	    	form.descricaoLocalidadeFinal.style.color = "#000000";
	    	
	    }
	    
	     if (tipoConsulta == 'localidadeDestino') {
	      	limparLocalidadeFinal(form);
	      	form.idLocalidadeFinal.value = codigoRegistro;
	    	form.descricaoLocalidadeFinal.value = descricaoRegistro;
	    	form.descricaoLocalidadeFinal.style.color = "#000000";
	    }
	
	    
   }

   	function exibirPenalidades(){
   		var form = document.forms[0];  			
 		form.action='exibirSolicitarGeracaoBoletimMedicaoCobrancaAction.do?pesquisarPenalidades=true';
 		form.submit();
	}

	function qtdCaracter(id){
		var form = document.forms[0];
		var maxCharacter = 150
		var tamanho = document.getElementById(id).value.length;
		var filtroNumero = /^[a-zA-Z0-9\u00C0-\u00ff]*$/;
		
		if (tamanho >= maxCharacter){
			document.getElementById(id).value = document.getElementById(id).value.substring(0, maxCharacter).toUpperCase();
			alert('Quantidade de caracteres digitados ultrapassou o limite de 150, por favor, verifique o texto');			
		}
		else{			
			if(!filtroNumero.test(document.getElementById(id).value)){
				document.getElementById(id).value = document.getElementById(id).value.substring(0, (tamanho - 1)).toUpperCase();				
			}
			else{
				document.getElementById(id).value = document.getElementById(id).value.toUpperCase();
			}
		}
	}
	function mostraJustificativa(id, idMostar,idContador){		
		if (document.getElementById(id).checked){				
			var style = 'max-height: 50px; min-height: 50px; 	max-width: 200px; min-width: 200px; display: block;';			
			document.getElementById(idMostar).setAttribute('style', style);
			document.getElementById('divContador'+idContador).style.display = 'block';
		}  else {				
			var style = 'max-height: 50px; min-height: 50px; 	max-width: 200px; min-width: 200px; display: none;';
			document.getElementById(idMostar).value = "";
			document.getElementById('limite'+idContador).innerHTML = "150";
			document.getElementById('utilizado'+idContador).innerHTML = "0";
			document.getElementById(idMostar).setAttribute('style', style);
			document.getElementById('divContador'+idContador).style.display = 'none';
		}		
	}
	function retiraCheck(){
		var checks = document.getElementsByName("idAcao");
		for(var i=0; i<checks.length; i++) {
             checks[i].checked = false;
             
        }
        var juss = document.getElementsByName("justificativa"); 
        for(var i=0; i<checks.length; i++) {
            juss[i].value = "";            
       }
	}

	function habilitarDesabilitar(){
		var form = document.forms[0];
	    form.empresa.disabled = false;
	    form.grupoCobranca.disabled = false;
		if(form.grupoCobranca.value != "-1"){
		    form.empresa.disabled = true;
		}else if(form.empresa.value != "-1"){
		    form.grupoCobranca.disabled = true;
		}
	}  	

	function selecionarContratos(){
	      var form = document.forms[0];
	      form.action = 'exibirSolicitarGeracaoBoletimMedicaoCobrancaAction.do?pesquisarContrato=true';
	      form.submit();
	}

	function campoSelecionado(form) {
		if(form.idNumeroContrato.value != ""){
			if(form.idNumeroContrato.checked==true){
				return true;
			}else{
				for (var i = 0; i < form.idNumeroContrato.length; i++){
					if (form.idNumeroContrato[i].checked == true) {
						return true;
					}
				}
			}	
		}			
		
		return false;
	}
	
</script>

</head>

<body leftmargin="5" topmargin="5" onload="verificaSelecao(); retiraCheck();habilitarDesabilitar();">

<div id="formDiv"><html:form action="/exibirSolicitarGeracaoBoletimMedicaoCobrancaAction.do"
	name="GerarRelatorioBoletimMedicaoCobrancaForm"
	type="gcom.gui.relatorio.cobranca.GerarRelatorioBoletimMedicaoCobrancaForm"
	method="post"> 

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
	  <html:hidden property="tipoOperacao"/>
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
					<td class="parabg">Solicitar Geração/Emissão Boletim de Medição de Cobrança</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				
				<tr>
					<td colspan="2">Para gerar/emitir o Boletim de Medição de Cobrança, informe os dados abaixo:</td>
				</tr>
				
				<tr>
					<td width="18%"><strong>Operação:<font color="#FF0000">*</font></strong></td>
					<td colspan="3">
						<html:radio property="formaGeracao"
						    value="1" 
							onclick="selecionarOperacao(this.value);"/>
							Gerar Boletim de Medição
						<html:radio property="formaGeracao"
						    value="2" 
							onclick="selecionarOperacao(this.value);"/>
							Emitir Relatório
					</td>
				</tr>
				
				<tr>
					<td colspan="2"></td>
				</tr>
				<tr>
					<td width="200">
						<strong>Mês/Ano do Grupo de Cobrança:<font color="#FF0000">*</font></strong>
					</td>

					<td colspan="3">
						<html:text  property="mesAnoReferencia" 
									size="7" 
									maxlength="7" 
									tabindex="1"
									onkeyup="mascaraAnoMes(this, event);"
									onkeypress="return isCampoNumerico(event);" /> (mm/aaaa)
					</td>
				</tr>
				<tr>
					<td>
						<strong>Grupo de Cobrança:</strong>
					</td>

					<td colspan="3">
						<strong> 
						<html:select property="grupoCobranca" 
									 style="width: 230px;" 
									 tabindex="4" onchange="habilitarDesabilitar();">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>
					
							<logic:present name="colecaoCobrancaGrupo" 
										   scope="request">
							   <html:options collection="colecaoCobrancaGrupo"
											 labelProperty="descricao" 
											 property="id"/>
							</logic:present>
						</html:select> 														
						</strong>
					</td>
				</tr>
				<tr>
					<td>
						<strong>Empresa:</strong>
					</td>

					<td colspan="3">
						<strong> 
							<html:select property="empresa"  style="width: 230px;" 
									 tabindex="4"  onchange="habilitarDesabilitar();selecionarContratos();">
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<html:options collection="collectionEmpresa" labelProperty="descricaoAbreviada" property="id" />
							</html:select>													
						</strong>
					</td>
				</tr>
				<tr>
					<td width="30%"><strong>Contrato de Cobran&ccedil;a: </strong></td>
					<td colspan="3" width="70%">
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td>
							<table width="60%" bgcolor="#99CCFF">
								<tr bgcolor="#99CCFF">
									<td width="10%">
										<strong>Selecionar</strong>
									</td>
									<td width="50%">
										<strong>Número do Contrato</strong>
									</td>
								</tr>
							</table>
							</td>
						</tr>
						
						<tr>
							<td>
								<%String cor = "#FFFFFF";%> 
								<logic:present name="collectionContrato" scope="session">
			
									<div style="width: 60%; height: 80; overflow: auto;">
			
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td><%cor = "#cbe5fe";%>
			
											<table width="100%" align="center" bgcolor="#99CCFF">
												<logic:iterate name="collectionContrato" 
													id="collectionContrato"
													type="InformarItensContratoServicoHelper">
													<c:set var="count" value="${count+1}" />
													<c:choose>
														<c:when test="${count%2 == '1'}">
															<tr bgcolor="#FFFFFF">
														</c:when>
														<c:otherwise>
															<tr bgcolor="#cbe5fe">
														</c:otherwise>
													</c:choose>
														
													<td width="10%">
														<div align="center">
																<input type="radio"
																name="idNumeroContrato" value="${count}"/>
														</div>
													</td>
													<td width="50%">
														<div align="left">
															<div align="left">
																<bean:write name="collectionContrato" property="contratoEmpresaServico.descricaoContrato" />
															</div>
														</div>
													</td>
												</logic:iterate>
											</table>
											</td>
										</tr>
									</table>
									</div>
								</logic:present>
							</td>
						</tr>
					</table>
					
					</td>
				</tr>
				<tr><td>&nbsp;</td></tr>				
				<tr>
					<td colspan="2">
						<div id="tipoGerar" style="display:none;">
						<logic:present name="temPermissao" scope="request">
						<div align="right">
						
						
						<input type="button" 
							   name="Button"
							   id="solicitarPenalidade" 
							   class="bottonRightCol" 
							   value="Solicitar não geração de penalidades " 
							   onClick="javascript:exibirPenalidades();" />
						<br />
						<br />
						<br />	   
					   </div>
						<logic:present name="exibeTabelaPenalidade" 
										   scope="request">
											<table width="100%" bgcolor="#99CCFF" border="0">
												<tr>
													<td bgcolor="#79bbfd" colspan="3" height="20" align="center">
														<strong>Cronograma de Ações<font color="#FF0000">*</font></strong>
													</td>
												</tr>
												<tr>
													<td bgcolor="#90c7fc" align="center" rowspan="2" width="120px">
														<strong>Não Gera Penalidade de execução</strong>
													</td>
													<td bgcolor="#90c7fc" align="center" rowspan="2">
														<strong>Ação</strong>
													</td>
													<td bgcolor="#90c7fc" align="center" rowspan="2">
														<strong>Justificativa</strong>
													</td>
												</tr>
											</table>
											<table width="100%" bgcolor="#99CCFF">
												<logic:present
													name="acoesPenalidades">
													<%int cont = 0;%>
													<logic:iterate
														name="acoesPenalidades"
														id="helper"
														type="AcoesPenalidadeGrupoHelper"
														scope="request">												
											
													<%cont = cont + 1;
													if (cont % 2 == 0) {%>
														<tr bgcolor="#cbe5fe">
													<%} else {%>
														</tr>
														<tr bgcolor="#FFFFFF">
													<%}%>
														<td align="center" width="120px">
														<input type="checkbox" 
														onclick="javascript: mostraJustificativa('idAcao<%=helper.getIdAcao() %>','jus<%=helper.getIdAcao() %>',<%=helper.getIdAcao() %>)" 
															name="idAcao" id="idAcao<%=helper.getIdAcao() %>" 
															value="<%=helper.getIdAcao() %>" />
														</td>
														<td align="center" width="115px"><%=helper.getAcao() %></td>
														<td align="center">														
															<textarea name="justificativa" id="jus<%=helper.getIdAcao() %>" 
																rows="3" cols="15"
																onchange="qtdCaracter('jus<%=helper.getIdAcao() %>')" 
																onkeyup="limitTextArea(document.getElementById('jus<%=helper.getIdAcao() %>'), 150, document.getElementById('utilizado<%=helper.getIdAcao() %>'), document.getElementById('limite<%=helper.getIdAcao() %>'));"
																style="max-height: 50px; min-height: 50px; 
																	max-width: 200px; min-width: 200px;
																	display: none;"></textarea>
														<div id="divContador<%=helper.getIdAcao() %>" style="display: none;">			
															<strong><span id="utilizado<%=helper.getIdAcao() %>">0</span>/<span id="limite<%=helper.getIdAcao() %>">150</span></strong>
														</div>
														</td>
													</tr>
												</logic:iterate>
											</logic:present>
										</table>								
							</logic:present>
							
							<!--<logic:present name="naoExistePenalidade" scope="request">
								<strong>Não Existem Penalidades para esse Grupo de Cobrança</strong>
							</logic:present>--> 
						 </logic:present>
						</div>						
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div id="tipoEmitir" style="display:none;">
							<table border="0">
							<tr>
							
								<td height="24">
									<strong>Gerência Regional:<font color="#FF0000"></font></strong>
								</td>
			
								<td colspan="3">
									<strong> 
									<html:select property="gerenciaRegional" 
												 style="width: 230px;" 
												 tabindex="4"
												 onchange="javascript:reloadForm();">
										<html:option
											value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
										</html:option>
								
										<logic:present name="colecaoGerenciaRegional" 
													   scope="request">
										   <html:options collection="colecaoGerenciaRegional"
														 labelProperty="nome" 
														 property="id"/>
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
								<td><strong>Localidade Inicial:</strong></td>
								<td height="24" colspan="3"><html:text maxlength="3" tabindex="6"
									property="idLocalidadeInicial" size="3"
									onkeypress="javascript:limparDescLocalidadeInicial(document.forms[0]);
									validaEnterComMensagem(event, 'exibirSolicitarGeracaoBoletimMedicaoCobrancaAction.do?objetoConsulta=1', 'idLocalidadeInicial','Localidade');"
									/>
									<a href="javascript:habilitarPesquisaLocalidade(document.forms[0],'origem');" >
									<img width="23" height="21" border="0"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										title="Pesquisar Localidade" /></a> 
										
										<logic:present
											name="idLocalidadeInicialNaoEncontrado">
										<logic:equal name="idLocalidadeInicialNaoEncontrado" value="exception">
											<html:text property="descricaoLocalidadeInicial" size="40"
												maxlength="30" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:equal>
										
										<logic:notEqual name="idLocalidadeInicialNaoEncontrado" value="exception">
											<html:text property="descricaoLocalidadeInicial" size="40"
												maxlength="30" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" />
										</logic:notEqual>
										</logic:present> 
										
										<logic:notPresent name="idLocalidadeInicialNaoEncontrado">
											<logic:empty name="GerarRelatorioBoletimMedicaoCobrancaForm" property="idLocalidadeInicial">
												<html:text property="descricaoLocalidadeInicial" value="" size="40"
													maxlength="30" readonly="true"
													style="background-color:#EFEFEF; border:0; color: #ff0000" />
											</logic:empty>
											
											<logic:notEmpty name="GerarRelatorioBoletimMedicaoCobrancaForm" property="idLocalidadeInicial">
												<html:text property="descricaoLocalidadeInicial" size="40"
													maxlength="30" readonly="true"
													style="background-color:#EFEFEF; border:0; color: #000000" />
											</logic:notEmpty>
				
									</logic:notPresent> 					<a
										href="javascript:limparLocalidadeInicial(document.forms[0]);">
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar" />
									</a>
								</td>
							</tr>
							
							<tr>
								<td><strong>Localidade Final:</strong></td>
								<td height="24" colspan="3"><html:text maxlength="3" tabindex="7"
									property="idLocalidadeFinal" size="3"
									onkeypress="javascript:limparDescLocalidadeFinal(document.forms[0]);
									validaEnterComMensagem(event, 'exibirSolicitarGeracaoBoletimMedicaoCobrancaAction.do?objetoConsulta=1', 'idLocalidadeFinal','Localidade Final');"
									 />
									<a href="javascript:habilitarPesquisaLocalidade(document.forms[0],'destino');" >
									<img width="23" height="21" border="0"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										title="Pesquisar Localidade" /></a> 
										
										<logic:present
											name="idLocalidadeFinalNaoEncontrado">
										<logic:equal name="idLocalidadeFinalNaoEncontrado" value="exception">
											<html:text property="descricaoLocalidadeFinal" size="40"
												maxlength="30" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #ff0000" />
										</logic:equal>
										
										<logic:notEqual name="idLocalidadeFinalNaoEncontrado" value="exception">
											<html:text property="descricaoLocalidadeFinal" size="40"
												maxlength="30" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" />
										</logic:notEqual>
										</logic:present> 
										
										<logic:notPresent name="idLocalidadeFinalNaoEncontrado">
											<logic:empty name="GerarRelatorioBoletimMedicaoCobrancaForm" property="idLocalidadeFinal">
												<html:text property="descricaoLocalidadeFinal" value="" size="40"
													maxlength="30" readonly="true"
													style="background-color:#EFEFEF; border:0; color: #ff0000" />
											</logic:empty>
											
											<logic:notEmpty name="GerarRelatorioBoletimMedicaoCobrancaForm" property="idLocalidadeFinal">
												<html:text property="descricaoLocalidadeFinal" size="40"
													maxlength="30" readonly="true"
													style="background-color:#EFEFEF; border:0; color: #000000" />
											</logic:notEmpty>
				
									</logic:notPresent>
									<a
										href="javascript:limparLocalidadeFinal(document.forms[0]);">
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar" />
									</a>
								</td>
							</tr>
						</table>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2"></td>
				</tr>
				
				<tr>
					<td colspan="2"></td>
				</tr>
				<tr>
					<td colspan="2"></td>
				</tr>
	
				<tr>
					<td>&nbsp;</td>
					<td align="left"><font color="#FF0000">*</font> Campo Obrigatório</td>
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
							   value="Enviar" 
							   onClick="javascript:validarForm();" />
					</td>
					
				</tr>							
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	
	
	<jsp:include
			page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=/gsan/gerarRelatorioBoletimMedicaoCobrancaAction.do" 
	/>	
<%@ include file="/jsp/util/rodape.jsp"%>	
</html:form></div>
<script>javascript: <bean:write name="GerarRelatorioBoletimMedicaoCobrancaForm" property="formaGeracao" /> == '1'? selecionarOperacao(1): null;</script>
</body>
</html:html>
