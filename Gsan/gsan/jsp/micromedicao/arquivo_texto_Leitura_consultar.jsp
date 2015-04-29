<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page isELIgnored="false"%>
<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<%@ include file="/jsp/util/telaespera.jsp"%>

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

function validaForm(form){

	if(testarCampoValorZero(document.ConsultarArquivoTextoLeituraActionForm.empresaID, 'Empresa')  && 
	testarCampoValorZero(document.ConsultarArquivoTextoLeituraActionForm.mesAno, 'M�s de Refer�ncia')) {


		if(validateConsultarArquivoTextoLeituraActionForm(form)){
				form.action = 'consultarArquivoTextoLeituraAction.do' ;
    			//submeterFormPadrao(form);
		botaoAvancarTelaEspera('/gsan/consultarArquivoTextoLeituraAction.do');
     }
  }
}


function gerarRelatorio(form){

	if(testarCampoValorZero(document.ConsultarArquivoTextoLeituraActionForm.empresaID, 'Empresa')  && 
	testarCampoValorZero(document.ConsultarArquivoTextoLeituraActionForm.mesAno, 'M�s de Refer�ncia')) {
	
		if(validateConsultarArquivoTextoLeituraActionForm(form)){
			toggleBox('demodiv',1);
	}
  }
}

  function finalizarComMotivo(motivoFinalizacao, deveFinalizar ){
  
	  var form = document.forms[0];
	  
	  form.motivoFinalizacao.value = motivoFinalizacao.value;
	  
	  if ( deveFinalizar.value == '1' ){
	  	finalizar(form);
	  } else {
	    informarMotivo( form );
	  }
  }
  

  function finalizar(form) {
	form.action = 'liberarArquivoLeituraAction.do?liberar=7';
  	form.submit();
  
  }
  
  function informarMotivo(form) {
	form.action = 'liberarArquivoLeituraAction.do?liberar=9';
  	form.submit();
  
  }
  
  
  function emCampo(form) {
	form.action = 'liberarArquivoLeituraAction.do?liberar=2';
  	form.submit();
  
  }
  
  function liberar(form) {
	form.action = 'liberarArquivoLeituraAction.do?liberar=1';
  	form.submit();
  
  }
  function naoLiberar(form) {
	form.action = 'liberarArquivoLeituraAction.do?liberar=0';
  	form.submit();
  
  }
      function limparForm(form) {
		form.empresaID.value = "";
		form.grupoFaturamentoID.value = "";
		form.mesAno.value = "";
		
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

	function listarLeiturista(){
		 var form = document.forms[0];
		 form.action = 'exibirConsultarArquivoTextoLeituraAction.do';
	  	 form.submit();
	
	}
	function CheckboxNaoVazio(campo){
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

	function gerarZip(){
		var form = document.forms[0];
		
		if(CheckboxNaoVazio(document.forms[0].idsRegistros)){
	
			form.action = 'retornarArquivoZipLeituraAction.do';
	
			form.submit();
		}
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
	
	function extendeTabelaFotos(idRota){
		var form = document.forms[0];
		var display = eval('layer'+idRota).style.display;
		if(display == "none"){
	  	    eval('layer'+idRota).style.display = 'block';
		}else{
		    eval('layer'+idRota).style.display = 'none';
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
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

		var form = document.forms[0];

		if (tipoConsulta == 'localidade') {
      		
      		form.idLocalidade.value = codigoRegistro;
	  		form.nomeLocalidade.value = descricaoRegistro;
      		
	  		form.nomeLocalidade.style.color = "#000000";
	  		
		}
	}
	
	function limparPesquisaLocalidade() {
		
		var form = document.ConsultarArquivoTextoLeituraActionForm;
		form.idLocalidade.value = "";
		form.nomeLocalidade.value = "";	
	}
	
	function gerarImoveisNaoEnviados(){	
		var form = document.forms[0];
		
		form.action = 'gerarArquivosImoveisNaoEnviadosAction.do';
  		form.submit();	
	}

	function verificaMotivoFinalizacao(){

		if(CheckboxNaoVazio(document.forms[0].idsRegistros)){
			abrirPopup('exibirInformarMotivoFinalizacaoPopupAction.do?finalizar=1', 300, 600)
		}
	}

	function desabilitaCompactar(){
		var form = document.forms[0];
	
			form.action = 'exibirConsultarArquivoTextoLeituraAction.do';
	
			form.submit();
	}
	
	
</script>


</head>

<body leftmargin="5" topmargin="5" onload="toggleBox('demodiv',0);verificaTabela('<%=session.getAttribute("achou")%>');">
<div id="formDiv">  
<html:form action="/consultarArquivoTextoLeituraAction"
	name="ConsultarArquivoTextoLeituraActionForm"
	type="gsan.gui.micromedicao.ConsultarArquivoTextoLeituraActionForm"
	method="post"
	onsubmit="return validateConsultarArquivoTextoLeituraActionForm(this);">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<html:hidden property="motivoFinalizacao" />
	
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
					<td class="parabg">Consultar Arquivos Texto para Leitura</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a P�gina��o da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="6">Para consultar os arquivos textos para
					leitura, informe os dados abaixo:</td>
					<td align="right"></td>
				</tr>

				<tr>

					<td><strong>M�s/Ano de Refer�ncia:<font
						color="#FF0000">*</font></strong></td>
					<td><html:text property="mesAno" 
								size="8" 
								maxlength="7"
								tabindex="1" 
								onkeyup="mascaraAnoMes(this, event);" 
								onkeypress="return isCampoNumerico(event);mascaraAnoMes(this, event);"
								/> mm/aaaa
					</td>

				</tr>

				<tr>
					<td><strong>Grupo de Faturamento:</strong></td>
					<td colspan="2" align="left"><html:select
						property="grupoFaturamentoID" tabindex="2">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoFaturamentoGrupo"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				
				<tr>
					<td><strong>Localidade:<font color="#FF0000"></font></strong></td>
		            <td height="24" colspan="2">
                   		<html:text maxlength="3" 
                   				   property="idLocalidade" 
                   				   size="3"  
                   				   tabindex="7"
                   				   onkeypress="javascript:validaEnter(event, 'exibirConsultarArquivoTextoLeituraAction.do', 'idLocalidade'); return isCampoNumerico(event);"
                   		/>
                      	<a href="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do?tipo=imovelLocalidade', 400, 800);">
                        	<img border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" title="Pesquisar"/></a>
							
	   		      		<logic:present name="localidadeInexistente" 
	   		      					   scope="request">
							<input type="text" 
								   name="nomeLocalidade" 
								   size="30" 
								   readonly="true" 
								   style="background-color:#EFEFEF; border:0; color: #ff0000" 
								   value="<bean:message key="atencao.localidade.inexistente"/>"
							/>
	                     </logic:present>
	                     <logic:notPresent name="localidadeInexistente" 
	                      				   scope="request">
                        	<html:text property="nomeLocalidade" 
                        			   size="30" 
                        			   readonly="true" 
                        			   style="background-color:#EFEFEF; border:0; color: #000000"
                        	/>
	                     </logic:notPresent>
	                     
						<a href="javascript:limparPesquisaLocalidade();document.forms[0].idLocalidade.focus();"> <img
						   src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						   border="0" title="Apagar" /></a>                   
					</td>
				</tr>

				<tr>
					<td><strong>Empresa:<font color="#FF0000">*</font></strong></td>
					<td colspan="2" align="left"><logic:equal name="permissao"
						value="1">
						<html:select property="empresaID" tabindex="3"
							onchange="javascript:listarLeiturista()">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoEmpresa"
								labelProperty="descricao" property="id" />
						</html:select>
					</logic:equal> <logic:equal name="permissao" value="2">
						<html:select property="empresaID" tabindex="3" disabled="true">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoEmpresa"
								labelProperty="descricao" property="id" />
						</html:select>
					</logic:equal></td>
				</tr>


				<tr>

					<td><strong>Leiturista:</strong></td>
					<td colspan="2" align="left"><html:select property="leituristaID"
						tabindex="4">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoLeiturista"
							labelProperty="descricao" property="id" />
					</html:select></td>

				</tr>
			
				
				<tr>

					<td><strong>Tipo de Servi�o:<font color="#FF0000">*</font></strong></td>
					<td colspan="2" align="left"><html:select property="servicoTipoCelular"
						tabindex="4" onchange="desabilitaCompactar()">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoServicoTipoCelular"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				


				<tr>
					<td><strong>Situa��o Texto para Leitura:</strong></td>
					<td>
					<html:select property="situaTransmLeitura"
						tabindex="4">
						<html:option value="">TODOS</html:option>
						<html:options collection="colecaoSituacaoTransmissaoLeitura"
							labelProperty="descricaoSituacao" property="id" />
					</html:select></td>
					
					<!--<strong> <html:radio property="situaTransmLeitura" value="1" /></strong>
					<strong>Dispon�vel<html:radio property="situaTransmLeitura"
						value="2" />Liberado</strong> <strong><html:radio
						property="situaTransmLeitura" value="3" />Em Campo</strong> <strong><html:radio
						property="situaTransmLeitura" value="5" />N�o Transmitido</strong>	
					-->
					

				</tr>
				<!--<tr>
					<td></td>
					<td><strong><html:radio property="situaTransmLeitura" value="4" />Finalizado</strong>
					<strong><html:radio property="situaTransmLeitura" value="7" />Finalizado Pelo Usu�rio</strong>
					<strong><html:radio property="situaTransmLeitura" value="" />Todos</strong>
					</td>

				</tr>

				--><tr>
					<td></td>
				</tr>
				<tr>
					<td colspan="2" ><input name="Button" type="button" class="bottonRightCol"
						value="Desfazer" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirConsultarArquivoTextoLeituraAction.do?menu=sim"/>'">
					<input type="button" name="ButtonCancelar" class="bottonRightCol"
						value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"></td>
					<td align="right"><%--<INPUT type="button" class="bottonRightCol" value="Inserir" tabindex="13" style="width: 70px;" onclick="validarForm(document.forms[0]);">--%>
					<%-- 
		  	A taglib vai substituir o bot�o, as propriedades requeridas da tag s�o :
		  	1-name -> O nome do bot�o.
		  	2-value -> A descri��o do bot�o. 
		  	3-onclick -> a fun��o javascript que vai ser chamada no click do bot�o.
		  	4-url -> a url doaction da opera��o para verificar se o us�rio logado tem acesso a opera��o.
		  	
		  	As propriedades N�O requeridas da tag s�o:
		  	1-tabindex -> mesma fun��o do input
		  	2-align -> mesma fun��o do input
		  --%> <gsan:controleAcessoBotao name="Botao" value="Selecionar"
						onclick="validaForm(document.forms[0]);"
						url="consultarArquivoTextoLeituraAction.do" tabindex="13" /></td>
				</tr>
				<td colspan="6">
					<font color="#000000" style="font-size:10px" face="Verdana, Arial, Helvetica, sans-serif"><strong>Arquivos Textos para Leitura:</strong></font></td>
				<tr>
					<td colspan="6" height="23"> 
					 <gsan:controleAcessoBotao name="Botao" value="Liberar" onclick="liberar(document.forms[0]);"
						url="liberarArquivoLeituraAction.do" tabindex="13" />
		    		<!-- <gsan:controleAcessoBotao name="Botao" value="N�o Liberar" onclick="naoLiberar(document.forms[0]);"
						url="liberarArquivoLeituraAction.do" tabindex="13" /> -->
					<input type="button" name="ButtonCancelar" class="bottonRightCol"
						value="N�o Liberar"
						onClick="naoLiberar(document.forms[0]);">	
					 <gsan:controleAcessoBotao name="Botao" value="Em Campo" onclick="emCampo(document.forms[0]);"
						url="liberarArquivoLeituraAction.do" tabindex="13" />	
					 <gsan:controleAcessoBotao name="Botao" value="Finalizar" 
					  	onclick="javascript:verificaMotivoFinalizacao();"
						url="liberarArquivoLeituraAction.do" tabindex="13" />						
					 <gsan:controleAcessoBotao name="Botao" value="Informar Motivo Finaliza��o" 
					  	onclick="javascript:abrirPopup('exibirInformarMotivoFinalizacaoPopupAction.do?finalizar=2', 300, 600);"
						url="liberarArquivoLeituraAction.do" tabindex="13" />												
					 
					
					
					
					<logic:present name="desabilitaBotao" scope="request">
						<input name="btnCompactar" type="button" class="bottonRightCol" value="Compactar" disabled="true" onclick="javascript:gerarZip();" url="retornarArquivoZipLeituraAction.do" tabindex="13" />
					</logic:present>
					
					<logic:notPresent name="desabilitaBotao" scope="request">
						<input name="btnCompactar" type="button" class="bottonRightCol" value="Compactar" onclick="javascript:gerarZip();" url="retornarArquivoZipLeituraAction.do" tabindex="13" />
					</logic:notPresent>		
						
					 
					 <logic:present name="permissaoRegerarArquivoImoveisNaoEnviados">					 
					 	<logic:equal name="permissaoRegerarArquivoImoveisNaoEnviados" value="SIM">
						 	<input name="btnGerarImoveisNaoEnviados" type="button" class="bottonRightCol" value="Gerar Im�ves N�o Enviados" onclick="javascript:gerarImoveisNaoEnviados();" url="gerarArquivosImoveisNaoEnviadosAction.do" tabindex="13" />					 					 					 	
					 	</logic:equal>
					 	<logic:equal name="permissaoRegerarArquivoImoveisNaoEnviados" value="NAO">
						 	<input name="btnGerarImoveisNaoEnviados" type="button" class="bottonRightCol" value="Gerar Im�ves N�o Enviados" disabled="true" onclick="javascript:gerarImoveisNaoEnviados();" url="gerarArquivosImoveisNaoEnviadosAction.do" tabindex="13" />					 					 					 	
					 	</logic:equal>				     	
					 </logic:present>

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
								<td width="10%" bgcolor="#90c7fc">
								<div align="center" style="height:30px;"><strong><a
									href="javascript:facilitador(this);">Todos</a></strong></div>
								</td>
								<td width="15%" bgcolor="#90c7fc">
								<div align="center"><strong>Sequ�ncia de Libera��o</strong></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
								<div align="center"><strong>Localidade</strong></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
								<div align="center"><strong>Setor Comercial</strong></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
								<div align="center"><strong>Rota</strong></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
								<div align="center"><strong>Quantidade</strong></div>
								</td>
								<td width="15%" bgcolor="#90c7fc">
								<div align="center"><strong>Leiturista</strong></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
								<div align="center"><strong>Situa��o</strong></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
								<div align="center"><strong>Libera��o</strong></div>
								</td>
							</tr>
						</table>

						<div style="height:500px;overflow:auto">
						<table width="100%" bgcolor="#99CCFF">
							<tr bordercolor="#000000" bgcolor="#90c7fc">
								<logic:present name="colecaoArquivoTextoRoteiroEmpresa">
									<%int cont = 0;%>
									<logic:iterate name="colecaoArquivoTextoRoteiroEmpresa"
										id="arquivoTextoRoteiroEmpresa">
										<!-- <pg:item>  -->
										<%cont = cont + 1;
			if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe" class="styleFontePequena">
											<%} else {%>
										<tr bgcolor="#FFFFFF" class="styleFontePequena">
											<%}%>
											<td width="10%">
											<div align="center">
											
												<logic:present name="arquivoTextoRoteiroEmpresa" property="rota">
												
													<c:choose>
												
														<c:when test='${arquivoTextoRoteiroEmpresa.leiturasRealizas == arquivoTextoRoteiroEmpresa.quantidadeImovel && arquivoTextoRoteiroEmpresa.situacaoTransmissaoLeitura.id == 4}'>
														
															<html:checkbox property="idsRegistros"
															value="${arquivoTextoRoteiroEmpresa.id}" disabled="true" />
													
														</c:when>
													
														<c:otherwise>
															
															<html:checkbox property="idsRegistros"
															value="${arquivoTextoRoteiroEmpresa.id}" />
													
														</c:otherwise>
													
													</c:choose>		
												
												</logic:present>
												
												<logic:notPresent name="arquivoTextoRoteiroEmpresa" property="rota">
													
													<html:checkbox property="idsRegistros"
													value="${arquivoTextoRoteiroEmpresa.id}" />
															
												</logic:notPresent>
												
											</div>
											</td>
											
											<td width="15%" align="center">${arquivoTextoRoteiroEmpresa.numeroSequenciaLeitura}</td>
											
											<td width="10%">
											
											<logic:present name="arquivoTextoRoteiroEmpresa" property="rota">
												<c:choose>
													<c:when test='${arquivoTextoRoteiroEmpresa.colecaoHelper != null }'>
<%-- 														<a href="javascript:extendeTabelaFotos(${arquivoTextoRoteiroEmpresa.rota.id});"> --%>
 															${arquivoTextoRoteiroEmpresa.localidade.id} 
<!-- 														</a> -->
													</c:when>
													<c:otherwise>
														${arquivoTextoRoteiroEmpresa.localidade.id}
													</c:otherwise>
												</c:choose>
											</logic:present>
											
											</td>
											
											<td width="10%">
											
											<logic:present name="arquivoTextoRoteiroEmpresa" property="rota">
												<div align="center">${arquivoTextoRoteiroEmpresa.codigoSetorComercial1}</div>
											</logic:present>
											
											</td>
											
											<td width="10%" align="center">
											<!-- Valida��o aqui -->
											
												<c:choose>
													<c:when test='${arquivoTextoRoteiroEmpresa.numeroImei == null 
																		&& arquivoTextoRoteiroEmpresa.rota != null 
																		&& arquivoTextoRoteiroEmpresa.rota.numeroLimiteImoveis != null }'>
													
														<c:choose>
													
															<c:when test='${arquivoTextoRoteiroEmpresa.qtdArquivosSubdivididos != null }'>																
																	<a href="javascript:abrirPopup('exibirConsultarArquivoTextoLeituraDivisaoPopupAction.do?idArquivoTextoRoteiroEmpresa=${arquivoTextoRoteiroEmpresa.id}', 480, 800);">
																		<font color="#FF0000" title="Rota Subdividida">
																			${arquivoTextoRoteiroEmpresa.rota.codigo}
																		</font>																		
																	</a>
															</c:when>
															<c:otherwise>
															<a href="javascript:abrirPopup('exibirConsultarArquivoTextoLeituraDivisaoPopupAction.do?idArquivoTextoRoteiroEmpresa=${arquivoTextoRoteiroEmpresa.id}', 480, 800);">
																${arquivoTextoRoteiroEmpresa.rota.codigo} 
															</a>
															</c:otherwise>
														</c:choose>
													</c:when>
													
													<c:otherwise>
														<html:link page="/retornarArquivoTxtLeituraAction.do"
																   title="${arquivoTextoRoteiroEmpresa.nomeArquivo}"
																   paramName="arquivoTextoRoteiroEmpresa" paramProperty="id"
																   paramId="idRegistroAtualizacao">
																   
														<c:choose>
															<c:when test='${arquivoTextoRoteiroEmpresa.qtdArquivosSubdivididos != null && arquivoTextoRoteiroEmpresa.qtdArquivosSubdivididos > 0  }'>
																<logic:present name="arquivoTextoRoteiroEmpresa" property="rota" >
																	<font color="#FF0000" >
																		${arquivoTextoRoteiroEmpresa.rota.codigo}
																	</font>
																</logic:present>
														
																<logic:notPresent name="arquivoTextoRoteiroEmpresa" property="rota">
																	GRUPO: ${arquivoTextoRoteiroEmpresa.faturamentoGrupo.id}																	
																</logic:notPresent>
															</c:when>
															
															<c:otherwise>
															
																<logic:present name="arquivoTextoRoteiroEmpresa" property="rota">
																	${arquivoTextoRoteiroEmpresa.rota.codigo}
																</logic:present>
																
																<logic:notPresent name="arquivoTextoRoteiroEmpresa" property="rota">
																	GRUPO: ${arquivoTextoRoteiroEmpresa.faturamentoGrupo.id}	
																</logic:notPresent>		
																				
															</c:otherwise>
														</c:choose>
														
														
														
														</html:link>
													</c:otherwise>
												
												</c:choose>

											</td>
													
											<td width="10%" align="center">
											
											<c:choose>
												
												<c:when test='${arquivoTextoRoteiroEmpresa.situacaoTransmissaoLeitura.id != 1 && arquivoTextoRoteiroEmpresa.situacaoTransmissaoLeitura.id != 2}'>
											
													<logic:present name="arquivoTextoRoteiroEmpresa" property="rota">
														
														${arquivoTextoRoteiroEmpresa.leiturasRealizas}/ ${arquivoTextoRoteiroEmpresa.quantidadeImovel}
														
													</logic:present>
													
													<logic:notPresent name="arquivoTextoRoteiroEmpresa" property="rota">
														
														${arquivoTextoRoteiroEmpresa.quantidadeImovel}
														
													</logic:notPresent>
													
										 		
										 		</c:when>
										
												<c:otherwise>
										 	
										 			${arquivoTextoRoteiroEmpresa.quantidadeImovel}									 
										 	
										 		</c:otherwise>
											
											</c:choose>
											
											</td>

											<td width="15%">
											    <div align="center" onmouseover="this.T_BGCOLOR='whitesmoke';this.T_FONTCOLOR='green';this.T_LEFT=true;return escape( 'TELEFONE: ${arquivoTextoRoteiroEmpresa.numeroFoneLeiturista} <br> IMEI: ${arquivoTextoRoteiroEmpresa.numeroImei}' ); "><c:choose>
												<c:when
													test='${arquivoTextoRoteiroEmpresa.leiturista.cliente!=null}'>												
														<a href="javascript:abrirPopup('exibirMonitorarLeituraMobilePopupAction.do?anoMes=<bean:write name="ConsultarArquivoTextoLeituraActionForm" property="mesAno"/>&grupoFaturamento=${arquivoTextoRoteiroEmpresa.faturamentoGrupo.id}&nomeLocalidade=${arquivoTextoRoteiroEmpresa.localidade.descricao}&nomeEmpresa=${arquivoTextoRoteiroEmpresa.empresa.descricao}&nomeLeiturista=${arquivoTextoRoteiroEmpresa.leiturista.cliente.nome}&tipoServico=${arquivoTextoRoteiroEmpresa.servicoTipoCelular.descricao}&situacaoTextoLeitura=${arquivoTextoRoteiroEmpresa.situacaoTransmissaoLeitura.descricaoSituacao}&idRota=${arquivoTextoRoteiroEmpresa.rota.id}&cdRota=${arquivoTextoRoteiroEmpresa.rota.codigo}&tipo=todos',720, 800)">${arquivoTextoRoteiroEmpresa.leiturista.cliente.nome}
														</a>
													</c:when>
												<c:otherwise>
														<a href="javascript:abrirPopup('exibirMonitorarLeituraMobilePopupAction.do?anoMes=<bean:write name="ConsultarArquivoTextoLeituraActionForm" property="mesAno"/>&grupoFaturamento=${arquivoTextoRoteiroEmpresa.faturamentoGrupo.id}&nomeLocalidade=${arquivoTextoRoteiroEmpresa.localidade.descricao}&nomeEmpresa=${arquivoTextoRoteiroEmpresa.empresa.descricao}&nomeLeiturista=${arquivoTextoRoteiroEmpresa.leiturista.funcionario.nome}&tipoServico=${arquivoTextoRoteiroEmpresa.servicoTipoCelular.descricao}&situacaoTextoLeitura=${arquivoTextoRoteiroEmpresa.situacaoTransmissaoLeitura.descricaoSituacao}&idRota=${arquivoTextoRoteiroEmpresa.rota.id}&cdRota=${arquivoTextoRoteiroEmpresa.rota.codigo}&tipo=todos',720, 800)">${arquivoTextoRoteiroEmpresa.leiturista.funcionario.nome}</a>
												</c:otherwise>
											</c:choose></div>
											</td>											<td width="10%">
											<div align="center">
												<a href="javascript:abrirPopup('exibirConsultarSituacaoLeituraPopupAction.do?idRota=${arquivoTextoRoteiroEmpresa.rota.id}', 480, 800);">
												${arquivoTextoRoteiroEmpresa.situacaoTransmissaoLeitura.descricaoSituacao}
												</a>
											</div>
											</td>
											<td width="10%">
											<div align="center"><fmt:formatDate pattern="dd/MM/yyyy"
												value="${arquivoTextoRoteiroEmpresa.ultimaAlteracao}" /></div>
											</td>

											<html:link href="/gsan/liberarArquivoLeituraAction.do"
												paramId="codigo" paramProperty="id"
												paramName="arquivoTextoRoteiroEmpresa">
											</html:link>

										</tr>
										
										<!-- In�cio Altera��o -->
<!-- 										<tr > -->
<!-- 											<td align="center" colspan="9">	 -->
<%-- 											 	<div id="layer${arquivoTextoRoteiroEmpresa.rota.id}"  style="display:none"> --%>
<!-- 													<table width="100%" align="center" bgcolor="#90c7fc" border="0" cellpadding="0" cellspacing="0"> -->
<!-- 														<tr bgcolor="#90c7fc" > -->
<!-- 															<td width="100%"> -->
<!-- 																<table width="100%" bgcolor="#99CCFF"> -->
<!-- 																	<tr bgcolor="#90c7fc" class="styleFontePequena" > -->
<!-- 																		<td width="10%" bgcolor="#90c7fc" colspan="1" > -->
<!-- 																			<div align="center"><strong>Foto</strong></div> -->
<!-- 																		</td> -->
<!-- 																		<td width="20%" bgcolor="#90c7fc"> -->
<!-- 																			<div align="center"><strong>Matr�cula</strong></div> -->
<!-- 																		</td> -->
<!-- 																		<td width="10%" bgcolor="#90c7fc"> -->
<!-- 																			<div align="center"><strong>Sequ�ncial</strong></div> -->
<!-- 																		</td> -->
<!-- 																		<td width="60%" bgcolor="#90c7fc"> -->
<!-- 																			<div align="center"><strong>Endere�o</strong></div> -->
<!-- 																		</td> -->
<!-- 																	</tr> -->
<!-- 																</table> -->
<!-- 															</td> -->
<!-- 														</tr> -->
														
<%-- 														<%int count = 0;%> --%>
<!-- 														<tr> -->
<!-- 															<td width="100%" > -->
<!-- 																<table width="100%" bgcolor="#99CCFF"> -->
<%-- 																	<logic:present name="arquivoTextoRoteiroEmpresa" property="colecaoHelper"> --%>
<%-- 																		<logic:iterate name="arquivoTextoRoteiroEmpresa" property="colecaoHelper" id="arquivoTextoRoteiroEmpresaFotoHelper"> --%>
<%-- 																			<%count = count + 1; --%>
<%-- 																			if (count % 2 == 0) {%> --%>
<!-- 																				<tr bgcolor="#cbe5fe" class="styleFontePequena"> -->
<%-- 																			<%} else {%> --%>
<!-- 																				<tr bgcolor="#FFFFFF" class="styleFontePequena"> -->
<%-- 																			<%}%> --%>
																			
<!-- 																				<td width="10%" align="center" > -->
<%-- 																					<logic:notEmpty name="arquivoTextoRoteiroEmpresaFotoHelper" property="idFoto"> --%>
<%-- 																						<a href="javascript:abrirPopup('exibirFotoMovimentoRoteiroEmpresa.do?idFoto=<bean:write name="arquivoTextoRoteiroEmpresaFotoHelper" property="idFoto"/>', 600, 800);"> --%>
<!-- 																							<img width="18" height="18" -->
<%-- 																								src="<bean:message key="caminho.imagens"/>imgfolder.gif" border="0" /> --%>
<!-- 																						</a> -->
<%-- 																					</logic:notEmpty> --%>
<!-- 																				</td> -->
																				
<!-- 																				<td width="20%"> -->
<%-- 																					<logic:present name="arquivoTextoRoteiroEmpresaFotoHelper" property="matricula"> --%>
<%-- 																						<div align="center">${arquivoTextoRoteiroEmpresaFotoHelper.matricula}</div>		 --%>
<%-- 																					</logic:present> --%>
<!-- 																				</td> -->
																				
<!-- 																				<td width="11%"> -->
<%-- 																					<logic:present name="arquivoTextoRoteiroEmpresaFotoHelper" property="sequencial"> --%>
<%-- 																						<div align="center">${arquivoTextoRoteiroEmpresaFotoHelper.sequencial}</div> --%>
<%-- 																					</logic:present> --%>
<!-- 																				</td> -->
																				
<!-- 																				<td width="59%"> -->
<%-- 																					<logic:present name="arquivoTextoRoteiroEmpresaFotoHelper" property="endereco"> --%>
<%-- 																						<div align="center">${arquivoTextoRoteiroEmpresaFotoHelper.endereco}</div> --%>
<%-- 																					</logic:present> --%>
<!-- 																				</td> -->
																				
<!-- 																			</tr> -->
<%-- 																		</logic:iterate> --%>
<%-- 																	</logic:present> --%>
<!-- 																</table> -->
<!-- 															</td> -->
<!-- 														</tr> -->
<!-- 													</table> -->
<!-- 												</div>	 -->
<!-- 											</td> -->
<!-- 										</tr> -->
										<!-- Fim Altera��o -->
										
										<!-- </pg:item> -->
									</logic:iterate>
								</logic:present>
							</table>
						</div>
					
					</tr>
					<tr>
						<td align="right" valign="top">
                                	<a href="javascript:gerarRelatorio(document.forms[0]);">
                                    	<img align="right" border="0" src="<bean:message key='caminho.imagens'/>print.gif"  title="Imprimir Arquivos Textos"/></a>
	                     </td>
					</tr>
					
				</table>
			  </div></td></tr>
			</table>
			<p>&nbsp;</p>
		</tr>
		
		
	</table>
	<p>&nbsp;</p>

	<tr>

	</tr>

<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio_tela_espera.jsp?relatorio=gerarRelatorioLeituraConsultarArquivosTextoAction.do"/>
<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
<%@ include file="/jsp/util/tooltip.jsp" %>
</div>
</body>
</html:html>

