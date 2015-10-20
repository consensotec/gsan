<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>

<%@ include file="/jsp/util/telaespera.jsp"%>

<%@ page import="gcom.micromedicao.SituacaoTransmissaoLeitura"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page isELIgnored="false"%>
<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="ConsultarArquivoTextoLeituraActionForm"
	dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<style>
.styleFontePequena{font-size:9px;
                   color: #000000;
				   font:Verdana, Arial, Helvetica, sans-serif}
.styleFontePeqNegrito{font-size:11px;
                   color: #000000;
				   font-weight: bold}
</style>

<script language="JavaScript">

	var semAgente;
	

	function validaForm(){
		form = document.forms[0];
		if(verificarAtributosInicialFinal(form) == false)
			return false;
		else if(form.idLocalidade.value == ''){
			alert("Informe Localidade");
			return false;
		}
		
		submeterFormPadrao(form);
	}
	
	
	 
	 
	function naoLiberar(form) {
		if(CheckboxNaoVazio()){
			form.action = 'validarArquivoTextoOSVisitaAction.do?liberar=0';
			form.submit();
		}
	 
	}
	
	 
	function liberar(form) {
		if(CheckboxNaoVazio()){
			form.action = 'validarArquivoTextoOSVisitaAction.do?liberar=1';
			form.submit();
		}
	 
	}

	function emCampo(form) {
		if(CheckboxNaoVazio()){
			form.action = 'validarArquivoTextoOSVisitaAction.do?liberar=2';
			form.submit();
		}
	 
	}
	
	function finalizar(form) {
		if(CheckboxNaoVazio()){
			form.action = 'validarArquivoTextoOSVisitaAction.do?liberar=3';
			form.submit();
		}
	 
	}
	
	function leiturista(form) {
		if(CheckboxNaoVazio()){
			abrirPopup('exibirAtualizarLeituristaOSVisitaAction.do', '210', '580');
		}
	}
	
	
	
	
	function verificarAtributosInicialFinal(form){
		
		if(form.idSetorComercialInicial.value == "" && form.idSetorComercialFinal.value != ""){
			alert("Informe Setor Comercial Inicial");
			return false;
		}
		else if(form.idSetorComercialInicial.value != "" && form.idSetorComercialFinal.value == ""){
			alert("Informe Setor Comercial Final");
			return false;
		}
		else if(form.idQuadraInicial.value == "" && form.idQuadraFinal.value != ""){
			alert("Informe Quadra Inicial");
			return false;
		}
		else if(form.idQuadraInicial.value != "" && form.idQuadraFinal.value == ""){
			alert("Informe Quadra Final");
			return false;
		}
		
		else if(form.idSetorComercialInicial.value > form.idSetorComercialFinal.value){
			alert("Setor Comercial Final é menor que o Setor Comercial Inicial");
			return false;
		}
		
		else if(form.idQuadraInicial.value > form.idQuadraFinal.value){
			alert("Quadra Final é menor que a Quadra Inicial");
			return false;
		}
		
		return true;
		
	}
	
 
	function CheckboxNaoVazio(){
	  form = document.forms[0];
	  retorno = false;
		
	  for(indice = 0; indice < form.elements.length; indice++){
		if (form.elements[indice].type == "checkbox" && form.elements[indice].checked == true) {
			retorno = true;
			break;
		}
	  }
		
	  if (!retorno){
		alert('Informe o(s) arquivo(s) desejado(s).');
	  }
		
	  return retorno;
	} 

		
	function extendeTabela(display){
		var form = document.forms[0];

		if(display){
		  	eval('layerHideDadosArquivos').style.display = 'none';
 			eval('layerShowDadosArquivos').style.display = 'block';
		}else{
		  	eval('layerHideDadosArquivos').style.display = 'block';
 			eval('layerShowDadosArquivos').style.display = 'none';
		}
	}
	
	function verificaTabela(achou){
	 if (achou == '2'){
	  	eval('layerHideDadosArquivos').style.display = 'block';
 		eval('layerShowDadosArquivos').style.display = 'none';
	 }else if (achou == '1'){
		eval('layerHideDadosArquivos').style.display = 'none';
 		eval('layerShowDadosArquivos').style.display = 'block';
	 }
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
	
	
	function pesquisarSetorComercial(arg1){
	    origemDestino = arg1;
	    abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].idLocalidade.value,document.forms[0].idLocalidade.value,'Localidade', 400, 800);
	 }

	 function pesquisarQuadra(arg1, arg2){
	 	  origemDestino = arg1;
          if(arg1 == 'origem')
	 		  abrirPopupDependencia('exibirPesquisarQuadraAction.do', document.forms[0].idSetorComercialInicial.value, 'Setor Comercial Inicial', 275, 480);
	 	  else
	 	  	  abrirPopupDependencia('exibirPesquisarQuadraAction.do', document.forms[0].idSetorComercialFinal.value, 'Setor Comercial Final', 275, 480);
	 }		 
	 
	 
	 function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];
		
	 	if(tipoConsulta == "localidade"){
	 		form.idLocalidade.value = codigoRegistro;
	 		form.descricaoLocalidade.value = descricaoRegistro;
	 		form.descricaoLocalidade.style.color = "#000000";
	 	}
	 	else if(tipoConsulta == "setorComercial"){
		 	if(origemDestino == "origem"){
		 		form.idSetorComercialInicial.value = codigoRegistro;
		 		form.descricaoSetorComercialInicial.value = descricaoRegistro;
		 		form.descricaoSetorComercialInicial.style.color = "#000000";
		 		form.idSetorComercialFinal.value = codigoRegistro;
		 		form.descricaoSetorComercialFinal.value = descricaoRegistro;
     	 		form.descricaoSetorComercialFinal.style.color = "#000000";
     	 		validarPreencherQuadra();
		 	}
		 	else{
		 		form.idSetorComercialFinal.value = codigoRegistro;
		 		form.descricaoSetorComercialFinal.value = descricaoRegistro;
     	 		form.descricaoSetorComercialFinal.style.color = "#000000";
     	 		validarPreencherQuadra();
		 	}
	 		
	 	}
	 	else if(tipoConsulta == "Quadra"){
		 	if(origemDestino == "origem"){
		 		form.idQuadraInicial.value = codigoRegistro;
		 		form.descricaoQuadraInicial.value = descricaoRegistro;
		 		form.descricaoQuadraInicial.style.color = "#000000";
		 		form.idQuadraFinal.value = codigoRegistro;
		 		form.descricaoQuadraFinal.value = descricaoRegistro;
		 		form.descricaoQuadraFinal.style.color = "#000000";
	 		}
	 		else{
		 		form.idQuadraFinal.value = codigoRegistro;
		 		form.descricaoQuadraFinal.value = descricaoRegistro;
		 		form.descricaoQuadraFinal.style.color = "#000000";
	 		}
	 	}
	 	else{
			form = document.forms[0];
			form.action = 'validarArquivoTextoOSVisitaAction.do?liberar=4&idNovoLeiturista='+codigoRegistro;
			form.submit();
		}
	 }
	 
	 
	 function limparConjunto(id){
		    var form = document.forms[0];
		 	if(id == 1){
		 		form.action = 'exibirConsultarArquivoTextoOSVisitaAction.do?limpar=ok&indice=1';
				form.submit();
		 	}
		 	else if(id == 2){
		 		form.action = 'exibirConsultarArquivoTextoOSVisitaAction.do?limpar=ok&indice=2';
				form.submit();
		 	}
		 	else if(id == 3){
		 		form.action = 'exibirConsultarArquivoTextoOSVisitaAction.do?limpar=ok&indice=3';
				form.submit();
		 	}
		 	
		 }
	 
	 function facilitador(objeto){
			if (objeto.id == "0" || objeto.id == undefined){
				objeto.id = "1";
				marcarTodos();
			}
			else{
				objeto.id = "0";
				desmarcarTodos();
			}
	 }
	 
	 function limparCampoDeleteBackspace(origem,destino,event){
		 if(origem.value == '' && (event.keyCode == 8 || event.keyCode == 46) )
			 destino.value = '';
		 
	 }
	 
	 function replicar(origem, destino){
		 if(origem.value == destino.value.substring(0,destino.value.length - 1)
				 || origem.value.substring(0,origem.value.length - 1) == destino.value
				 || destino.value == '')
			 destino.value = origem.value;
	 }
	 
	 function validarPreencherQuadra(){
		 form = document.forms[0];
		 if(form.idSetorComercialInicial.value != form.idSetorComercialFinal.value){
			 form.idQuadraInicial.value = '';
			 form.idQuadraFinal.value = '';
			 form.idQuadraInicial.disabled = true;
			 form.idQuadraFinal.disabled = true;
		 }
		 else{
			 form.idQuadraInicial.disabled = false;
			 form.idQuadraFinal.disabled = false;
		 }
	 }
	 

	 
	 function campoNumerico(campo) {
		var value = campo.value;
	    var bool = isNaN(+value);
	    bool = bool || (value.indexOf('.') != -1);
	    bool = bool || (value.indexOf(",") != -1);
	    if(bool)
	    	campo.value = '';
	}
	 
	
</script>


</head>

<body leftmargin="5" topmargin="5" onload="toggleBox('demodiv',0);verificaTabela('<%=session.getAttribute("achou")%>');">
<div id="formDiv">  
<html:form action="/consultarArquivoTextoOSVisitaAction"
	name="ConsultarArquivoTextoOSVisitaActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.ConsultarArquivoTextoOSVisitaActionForm"
	method="post"
	onsubmit="return validateConsulta(this);">

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
					<td></td>
				</tr>
			</table>


			<!--Início Tabela Reference a Páginação da Tela de Processo-->
			<table>
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Consultar Arquivo Texto das Ordens de Serviço de Visita</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="6">Para consultar os arquivos textos 
					das ordens de serviço de visita, informe os dados abaixo:</td>
					<td align="right"></td>
				</tr>

				<tr>
	                <td><strong>Localidade<font color="#FF0000">*</font>:</strong></td>
	                <td colspan="2"><strong>
	                  <html:text maxlength="3"
	                  			 size="3"
	                  			 tabindex="1"
	                  			 property="idLocalidade"
	                  			 onkeypress="javascript:validaEnterComMensagem(event, 'exibirConsultarArquivoTextoOSVisitaAction.do?pesquisarLocalidade=OK', 'idLocalidade','Localidade');return isCampoNumerico(event);" 
	                  			 onchange="campoNumerico(this)"/>
	                <a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', null, null, null, 400, 800, '',document.forms[0].idLocalidade);">
						<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						     title="Pesquisar Localidade" /></a>
					<logic:notPresent name="localidadeException" scope="request">	     
		     			<html:text
		     					   maxlength="40"
		     					   size="40"
		     					   readonly="true"
		     					   property="descricaoLocalidade"
   		     					   style="background-color:#EFEFEF; border:0; color: #000000"/>
	     			</logic:notPresent>
	     			<logic:present name="localidadeException" scope="request">
		     			<html:text
		     					   maxlength="40"
		     					   size="40"
		     					   readonly="true"
		     					   property="descricaoLocalidade"
		     					   style="background-color:#EFEFEF; border:0; color: #ff0000"/>	
	     			</logic:present>	     
	     			</strong>
	     			<a href="javascript:limparConjunto(1);">
						<img border="0" title="Apagar" src="/gsan/imagens/limparcampo.gif">
					</a>
	     		</td>
              </tr>

			<tr>
	                <td><strong>Setor Comercial Inicial:</strong></td>
	                <td colspan="2"><strong>
	                  <html:text maxlength="3"
	                  			 size="3"
	                  			 tabindex="1"
	                  			 property="idSetorComercialInicial"
 	                  			 onkeypress="javascript:validaEnterDependencia(event, 'exibirConsultarArquivoTextoOSVisitaAction.do?pesquisarSetorComercialInicial=OK', this, document.forms[0].idLocalidade.value, 'Localidade');return isCampoNumerico(event);"
 	                  			 onkeyup="javascript:limparCampoDeleteBackspace(this,document.forms[0].idSetorComercialFinal,event);replicar(this,document.forms[0].idSetorComercialFinal);"
 	                  			 onblur="javascript:validarPreencherQuadra();"
 	                  			 onchange="campoNumerico(this)"/>
	                    <a href="javascript:pesquisarSetorComercial('origem')">
						<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						     title="Pesquisar Setor Comercial" /></a>
					<logic:notPresent name="setorComercialInicialException" scope="session">	     
		     			<html:text
		     					   maxlength="40"
		     					   size="40"
		     					   readonly="true"
		     					   property="descricaoSetorComercialInicial"
   		     					   style="background-color:#EFEFEF; border:0; color: #000000"/>
	     			</logic:notPresent>
	     			<logic:present name="setorComercialInicialException" scope="session">
		     			<html:text
		     					   maxlength="40"
		     					   size="40"
		     					   readonly="true"
		     					   property="descricaoSetorComercialInicial"
		     					   style="background-color:#EFEFEF; border:0; color: #ff0000"/>	
	     			</logic:present>	   
	     			</strong>
	     			<a href="javascript:limparConjunto(2);">
						<img border="0" title="Apagar" src="/gsan/imagens/limparcampo.gif">
					</a>  	     
	     		</td>
              </tr>
				
				              <tr>
	                <td><strong>Setor Comercial Final:</strong></td>
	                <td colspan="2"><strong>
	                  <html:text maxlength="3"
	                  			 size="3"
	                  			 tabindex="1"
	                  			 property="idSetorComercialFinal"
	                  			 onkeypress="javascript:validaEnterDependencia(event, 'exibirConsultarArquivoTextoOSVisitaAction.do?pesquisarSetorComercialFinal=OK', this, document.forms[0].idLocalidade.value, 'Localidade');return isCampoNumerico(event);"
	                  			 onblur="javascript:validarPreencherQuadra();"
	                  			 onchange="campoNumerico(this)"/>
   	                    <a href="javascript:pesquisarSetorComercial('destino')">
						<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						     title="Pesquisar Setor Comercial" /></a>
					<logic:notPresent name="setorComercialFinalException" scope="session">	     
		     			<html:text
		     					   maxlength="40"
		     					   size="40"
		     					   readonly="true"
		     					   property="descricaoSetorComercialFinal"
   		     					   style="background-color:#EFEFEF; border:0; color: #000000"/>
	     			</logic:notPresent>
	     			<logic:present name="setorComercialFinalException" scope="session">
		     			<html:text
		     					   maxlength="40"
		     					   size="40"
		     					   readonly="true"
		     					   property="descricaoSetorComercialFinal"
		     					   style="background-color:#EFEFEF; border:0; color: #ff0000"/>	
	     			</logic:present>	     	     	    
	     			</strong>
	     			<a href="javascript:limparConjunto(3);">
						<img border="0" title="Apagar" src="/gsan/imagens/limparcampo.gif">
					</a>  
	     		</td>
              </tr>
              
               <tr>
	                <td><strong>Quadra Inicial: </strong></td>
	                <td colspan="2">
	                  <html:text maxlength="3"
	                  			 size="3"
	                  			 tabindex="1"
	                  			 property="idQuadraInicial"
	                  			 onkeypress="javascript:validaEnterDependencia(event, 'exibirConsultarArquivoTextoOSVisitaAction.do?pesquisarQuadraInicial=OK',this,document.forms[0].idSetorComercialInicial.value, 'Setor Comercial Inicial');return isCampoNumerico(event);"
								 onkeyup="javascript:limparCampoDeleteBackspace(this,document.forms[0].idQuadraFinal,event);replicar(this,document.forms[0].idQuadraFinal)" 
								 onchange="campoNumerico(this)"/>
					<logic:notPresent name="quadraInicial" scope="request">
						<span style="color:#ff0000" id="msgQuadraInicial">
							<bean:write name="ConsultarArquivoTextoOSVisitaActionForm" property="descricaoQuadraInicial" />
						</span>
					</logic:notPresent> 
					<logic:present name="quadraInicial" scope="request" />
	     		
	     		</td>
              </tr>
              
               <tr>
	                <td><strong>Quadra Final:</strong></td>
	                <td colspan="2">
	                  <html:text maxlength="3"
	                  			 size="3"
	                  			 tabindex="1"
	                  			 property="idQuadraFinal"
	                  			 onkeypress="javascript:validaEnterDependenciaComMensagem(event, 'exibirConsultarArquivoTextoOSVisitaAction.do?pesquisarQuadraFinal=OK',this,document.forms[0].idSetorComercialFinal.value, 'Setor Comercial Final');return isCampoNumerico(event);"
	                  			 onchange="campoNumerico(this)"/>
	               <logic:notPresent name="quadraFinal" scope="request">
						<span style="color:#ff0000" id="msgQuadraFinal">
							<bean:write name="ConsultarArquivoTextoOSVisitaActionForm" property="descricaoQuadraFinal" />
						</span>
					</logic:notPresent> 
					<logic:present name="quadraFinal" scope="request" ></logic:present>       					   
	     			
	     		</td>
              </tr>			



				<tr>

					<td><strong>Agente Comercial:</strong></td>
					<td colspan="2" align="left"><html:select property="leituristaID"
						tabindex="4">
						<html:option value="-1">&nbsp;</html:option>
							<logic:present name="colecaoLeiturista">					
								<html:options collection="colecaoLeiturista"
									labelProperty="nome" property="id" />
							</logic:present>
						</html:select>
					</td>

				</tr>
				
				<tr>

					<td><strong>Situação Arquivo Texto:</strong></td>
					<td colspan="2" align="left"><html:select property="situacaoArquivoTexto"
						tabindex="4">
						<html:option value="-1">&nbsp;</html:option>
							<logic:present name="colecaoSituacaoArquivoTexto">
								<html:options collection="colecaoSituacaoArquivoTexto"
									labelProperty="descricaoSituacao" property="id" />
							</logic:present>	
						</html:select>
					</td>
				</tr>
			
				<tr>
					<td colspan="2" ><input name="Button" type="button" class="bottonRightCol"
						value="Desfazer" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirConsultarArquivoTextoOSVisitaAction.do?menu=sim"/>'">
					<input type="button" name="ButtonCancelar" class="bottonRightCol"
						value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"></td>
					<td align="right"><%--<INPUT type="button" class="bottonRightCol" value="Inserir" tabindex="13" style="width: 70px;" onclick="validarForm(document.forms[0]);">--%>
					<%-- 
		  	A taglib vai substituir o botão, as propriedades requeridas da tag são :
		  	1-name -> O nome do botão.
		  	2-value -> A descrição do botão. 
		  	3-onclick -> a função javascript que vai ser chamada no click do botão.
		  	4-url -> a url doaction da operação para verificar se o usário logado tem acesso a operação.
		  	
		  	As propriedades NÃO requeridas da tag são:
		  	1-tabindex -> mesma função do input
		  	2-align -> mesma função do input
		  --%> <gsan:controleAcessoBotao name="Botao" value="Selecionar"
						onclick="validaForm();"
						url="consultarArquivoTextoOSVisitaAction.do" tabindex="13" /></td>
				</tr>
				<td colspan="6">
					<font color="#000000" style="font-size:10px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Arquivos Textos para Leitura:</strong></font></td>
				<tr>
					<td colspan="6" height="23"> 
					 <gsan:controleAcessoBotao name="Botao1" value="Liberar" onclick="liberar(document.forms[0]);"
						url="validarArquivoTextoOSVisitaAction.do" tabindex="13" />
				     <gsan:controleAcessoBotao name="Botao2" value="Não Liberar" onclick="naoLiberar(document.forms[0]);"
						url="validarArquivoTextoOSVisitaAction.do" tabindex="13" />
					 <gsan:controleAcessoBotao name="Botao3" value="Em Campo" onclick="emCampo(document.forms[0]);"
						url="validarArquivoTextoOSVisitaAction.do" tabindex="13" />	
					 <gsan:controleAcessoBotao name="Botao4" value="Finalizar" 
					  	onclick="finalizar(document.forms[0]);"
						url="validarArquivoTextoOSVisitaAction.do" tabindex="13" />						
					 <gsan:controleAcessoBotao name="Botao5" value="Informar Agente Comercial" 
					  	onclick="leiturista(document.forms[0]);"
						url="validarArquivoTextoOSVisitaAction.do" tabindex="13" />												 
					</td>
				</tr>

				<tr>
					<!--<td colspan="4" bgcolor="#3399FF"> -->
					<td colspan="5" bgcolor="#000000" height="2" valign="baseline"></td>
				</tr>
				<tr>
				 <td colspan="5" width="100%">
				 	<div id="layerHideDadosArquivos" style="display:block">
               				<table width="100%" border="0" bgcolor="#99CCFF">
		    					<tr bgcolor="#99CCFF">
                      				<td align="center">
                     					<span class="style2">
                     					<a href="javascript:extendeTabela(true);"/>
                      						<b>Dados dos Arquivos</b>
                      					</a>
                     					</span>
                      				</td>
                     			</tr>
                    		</table>
           			</div>
				 </td>
				</tr>

				<tr>
					<td width="100%" colspan="5">
				
			   <div id="layerShowDadosArquivos" style="display:none">
				<table width="100%" align="center" bgcolor="#90c7fc" border="0" cellpadding="0" cellspacing="0">
					<tr bgcolor="#99CCFF">
                   		<td align="center">
           					<span class="style2">
             					<a href="javascript:extendeTabela(false);"/>
             						<b>Dados dos Arquivos</b>
             					</a>
           					</span>
               			</td>
              		</tr>
					<tr bgcolor="#cbe5fe" >
						<td width="100%" align="center">
						 <table width="100%" bgcolor="#99CCFF" border="0">
							<tr bordercolor="#000000" bgcolor="#90c7fc" class="styleFontePeqNegrito">
								<!-- 1 -->
								<td width="5%" bgcolor="#90c7fc">
								<div align="center" style="height:30px;"><strong><a
									href="javascript:facilitador(this);">Todos</a></strong></div>
								</td>
								<!-- 2 -->
								<td width="10%" bgcolor="#90c7fc">
								<div align="center"><strong>Localidade</strong></div>
								</td>
								<!-- 3 -->
								<td width="10%" bgcolor="#90c7fc">
								<div align="center"><strong>Setor Comercial Inicial</strong></div>
								</td>
								<!-- 4 -->
								<td width="10%" bgcolor="#90c7fc">
								<div align="center"><strong>Setor Comercial Final</strong></div>
								</td>
								<!-- 5 -->
								<td width="10%" bgcolor="#90c7fc">
								<div align="center"><strong>Quadra Inicial</strong></div>
								</td>
								<!-- 6 -->
								<td width="10%" bgcolor="#90c7fc">
								<div align="center"><strong>Quadra Final</strong></div>
								</td>
								<!-- 7 -->
								<td width="5%" bgcolor="#90c7fc">
								<div align="center"><strong>Qtd</strong></div>
								</td>
								<!-- 8 -->
								<td width="20%" bgcolor="#90c7fc">
								<div align="center"><strong>Agente Comercial</strong></div>
								</td>
								<!-- 9 -->
								<td width="10%" bgcolor="#90c7fc">
								<div align="center"><strong>Situação</strong></div>
								</td>
								<!-- 10 -->
								<td width="10%" bgcolor="#90c7fc">
								<div align="center"><strong>Liberação</strong></div>
								</td>
								<!-- 11 -->
								<!-- <td width="5%" bgcolor="#90c7fc">
								<div align="center"><strong>Id</strong></div>
								</td> -->
							</tr>
						</table>

						<div style="height:500px;overflow:auto">
						<table width="100%" bgcolor="#99CCFF">
							<tr bordercolor="#000000" bgcolor="#90c7fc">
								
								<logic:present name="colecaoArquivoTextoOSVisita">
									<%int cont = 0;%>
									<logic:iterate name="colecaoArquivoTextoOSVisita"
										id="arquivoTextoOSVisita">
								
										<%
											  cont = cont + 1;
											  if (cont % 2 == 0) {
										  %>
										<tr bgcolor="#cbe5fe" class="styleFontePequena">
										
											<%} else {%>
											
										<tr bgcolor="#FFFFFF" class="styleFontePequena">
										
											<%}%>
											
											
											<!-- 1 -->		
											<td width="5%">
												<div align="center">
													<html:checkbox property="idsRegistros"
													value="${arquivoTextoOSVisita.idArquivo}" disabled="false" />
												</div>
											</td>
												
											<!-- 2 -->	
											<td width="10%" align="center">
												<!-- VERIFICAR SITUAÇÃO LIBERADA -->
											    <c:choose>
											       
													<c:when test='${arquivoTextoOSVisita.idSituacao == 2}'>
															<a href="/gsan/transmitirAquivoTxtOSVisitaAction.do?imei=${arquivoTextoOSVisita.imei}" >${arquivoTextoOSVisita.idLocalidade}</a> 
													</c:when>
													
													<c:otherwise>
															${arquivoTextoOSVisita.idLocalidade}
													</c:otherwise>
												</c:choose>
												
											</td>
												
											<!-- 3 -->
											<td width="10%" align="center">
												${arquivoTextoOSVisita.codSetorComercialInicial}
											</td>
												
											<!-- 4 -->	
											<td width="10%">
												<div align="center">${arquivoTextoOSVisita.codSetorComercialFinal}</div>
											</td>
												
											<!-- 5 -->	
											<td width="10%">
												<div align="center">${arquivoTextoOSVisita.numeroQuadraInicial}</div>
											</td>
											
											<!-- 6 -->											
											<td width="10%" align="center">
												<div align="center">${arquivoTextoOSVisita.numeroQuadraFinal}</div>
											
											
												<%-- <c:choose>
													<c:when test='${arquivoTextoRoteiroEmpresa.numeroImei == null 
																		&& arquivoTextoRoteiroEmpresa.rota != null 
																		&& arquivoTextoRoteiroEmpresa.rota.numeroLimiteImoveis != null }'>
														
														<a href="javascript:abrirPopup('exibirConsultarArquivoTextoLeituraDivisaoPopupAction.do?idArquivoTextoRoteiroEmpresa=${arquivoTextoRoteiroEmpresa.id}', 480, 800);">
															${arquivoTextoRoteiroEmpresa.rota.codigo} 
														</a>
													</c:when>
													
													<c:otherwise>
														<html:link page="/retornarArquivoTxtLeituraAction.do"
																   title="${arquivoTextoRoteiroEmpresa.nomeArquivo}"
																   paramName="arquivoTextoRoteiroEmpresa" paramProperty="id"
																   paramId="idRegistroAtualizacao">
														
															<logic:present name="arquivoTextoRoteiroEmpresa" property="rota">
															
																${arquivoTextoRoteiroEmpresa.rota.codigo}
																
															</logic:present>
															
															<logic:notPresent name="arquivoTextoRoteiroEmpresa" property="rota">
															
																GRUPO: ${arquivoTextoRoteiroEmpresa.faturamentoGrupo.id}
																
															</logic:notPresent>
														
														</html:link>
													</c:otherwise>
												</c:choose> --%>
											
											</td>
											
											<!-- 7 -->			
											<td width="5%" align="center" title="Finalizados: ${arquivoTextoOSVisita.qtdOSEncerradas}">
													${arquivoTextoOSVisita.qtdOrdemServico}
											</td>
	
											<!-- 8 -->
											<td width="20%">
											    <div align="center">
											    
											    <c:choose>
													<c:when test='${arquivoTextoOSVisita.nomeFuncionario != null }'>
															<a href="/gsan/exibirConsultarOrdemServicoDoArquivoTextoAction.do?arquivoTexto=${arquivoTextoOSVisita.idArquivo}">${arquivoTextoOSVisita.nomeFuncionario}</a> 
													</c:when>
													
													<c:otherwise>
															<a href="/gsan/exibirConsultarOrdemServicoDoArquivoTextoAction.do?arquivoTexto=${arquivoTextoOSVisita.idArquivo}">${arquivoTextoOSVisita.nomeCliente}</a>
													</c:otherwise>
												</c:choose>
											     </div>
											</td>
											
											<!-- 9 -->											
											<td width="10%">
												<div align="center">
													${arquivoTextoOSVisita.descricaoSituacao}
												</div>
											</td>
											
											<!-- 10 -->
											<td width="10%">
											<div align="center"><fmt:formatDate pattern="dd/MM/yyyy"
												value="${arquivoTextoOSVisita.dataUltimaAlteracao}" /></div>
											</td>
											
											<!-- <td width="5%">
												<div align="center">
													${arquivoTextoOSVisita.idArquivo}
												</div>
											</td> -->
										</tr>
										<!-- </pg:item> -->
									</logic:iterate>
								</logic:present>
						</table>
						</div>
					
					</tr>
					<%-- <tr>
						<td align="right" valign="top">
                                	<a href="javascript:toggleBox('demodiv',1);">
                                    	<img align="right" border="0" src="<bean:message key='caminho.imagens'/>print.gif"  title="Imprimir Arquivos Textos"/></a>
	                     </td>
					</tr> --%>
					
				</table>
			  </div></td></tr>
			</table>
			<p>&nbsp;</p>
		</tr>
		
		
	</table>
	<p>&nbsp;</p>

	<tr>

	</tr>
<logic:present name="colecaoArquivoTextoOSVisita">
	<script>javascript:extendeTabela(true);</script>
</logic:present>

<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio_tela_espera.jsp?relatorio=gerarRelatorioLeituraConsultarArquivosTextoAction.do"/>
<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</div>
</body>
</html:html>