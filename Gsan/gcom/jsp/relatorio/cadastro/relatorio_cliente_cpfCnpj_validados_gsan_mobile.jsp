<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="GerarRelatorioClienteCpfCnpjValidadosActionForm" />

<script LANGUAGE="JavaScript">
 	
	//So chama a função abrirCalendario caso o campo esteja habilitado
	function chamarCalendario(fieldNameOrigem,objetoRelacionado,fieldNameDestino){
		//abrirCalendarioReplicando('GerarRelatorioClienteCpfCnpjValidadosActionForm', fieldNameOrigem,fieldNameDestino);		
		if(objetoRelacionado.disabled != true){
			if(fieldNameDestino != ''){
				abrirCalendarioReplicando('GerarRelatorioClienteCpfCnpjValidadosActionForm', fieldNameOrigem,fieldNameDestino);
			}else{
				abrirCalendario('GerarRelatorioClienteCpfCnpjValidadosActionForm', fieldNameOrigem);
			}
		}
	}
	
	function validarForm(form){
		if(validateGerarRelatorioClienteCpfCnpjValidadosActionForm(form)){
			//form.action = "/gsan/filtrarAlteracaoAtualizacaoCadastralAction.do";
			//submeterFormPadrao(form);
			botaoAvancarTelaEspera('/gsan/gerarRelatorioClienteCpfCnpjValidadosAction.do');
		}
	}
	
	function listarLeiturista(){
		 var form = document.forms[0];
		 form.action = 'exibirGerarRelatorioClienteCpfCnpjValidadosAction.do';
	  	 form.submit();
	
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	   var form = document.forms[0];
	   if (tipoConsulta == 'arquivoTextoAtualizacaoCadastral') {
	      form.idArquivo.value = codigoRegistro;
	      form.descricaoArquivo.value = descricaoRegistro;
	      form.descricaoArquivo.style.color = "#000000";      
			
			 if((form.descricaoArquivo.value != null && form.descricaoArquivo.value != '')
					   || (form.idArquivo.value != null && form.idArquivo.value != '')){
				   
				   form.periodoInicial.disabled = true;
				   form.periodoInicial.value = '';
				   form.periodoFinal.disabled = true;
				   form.periodoFinal.value = '';
				   
			   }else{
				   form.periodoInicial.disabled = false;
				   form.periodoFinal.disabled = false;
			   }			 
	    }
	   
	   form.action = 'exibirGerarRelatorioClienteCpfCnpjValidadosAction.do';
	   form.submit();
   }
   
	
	function replicaDataEncerramento() {
		var form = document.forms[0];
		form.periodoFinal.value = form.periodoInicial.value;
	}
	
	
	
   	function chamarPopup(url,altura, largura,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
			abrirPopup(url, altura, largura);
		}
   	}
   	
   function limparArquivo(){
		var form = document.forms[0];
	    form.idArquivo.value = '';
	    form.descricaoArquivo.value = '';	    
	}
   
   
   function desabilitarPeriodo(){
	   var form = document.forms[0];	 
	   if((form.descricaoArquivo.value != null && form.descricaoArquivo.value != '')
			   || (form.idArquivo.value != null && form.idArquivo.value != '')){
		   
		   form.periodoInicial.disabled = true;
		   form.periodoInicial.value = '';
		   form.periodoFinal.disabled = true;
		   form.periodoFinal.value = '';
		   
	   }else{
		   form.periodoInicial.disabled = false;
		   form.periodoFinal.disabled = false;
	   }
   }
   
   
</script>
</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}'); desabilitarPeriodo();">

<div id="formDiv">
<html:form action="/gerarRelatorioClienteCpfCnpjValidadosAction"
	name="GerarRelatorioClienteCpfCnpjValidadosActionForm"
	type="gcom.gui.relatorio.cadastro.GerarRelatorioClienteCpfCnpjValidadosActionForm"
	onsubmit="return validateGerarRelatorioClienteCpfCnpjValidadosActionForm(this);">

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="140" valign="top" class="leftcoltext">
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

	<td width="625" valign="top" class="centercoltext">

        <table height="100%">
        <tr>
          <td></td>
        </tr>
      	</table>

      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Clientes com CPF/CNPJ validados no Gsan e informado no Mobile</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>

      <table width="100%" border="0">
      <tr>
      	<td colspan="3">Para gerar o relatório, informe os dados abaixo: </td>
      </tr>	  
	  <tr>
		<td width="150"><strong>Empresa:</strong></td>
		<td colspan="2" align="left">
			<html:select property="idEmpresa" tabindex="3" onchange="javascript:listarLeiturista()">
				<html:option value="-1">&nbsp;</html:option>
				<html:options collection="colecaoEmpresa"
					labelProperty="descricao" property="id" />
			</html:select>
		</td>
	  </tr>
	  <tr>
		<td height="10" width="145"><strong>Agente Comercial:</strong></td>
		<td colspan="2" align="left"><html:select property="idLeiturista"
			tabindex="4">
			<html:option value="-1">&nbsp;</html:option>
			<html:options collection="colecaoLeiturista" labelProperty="descricao" property="id" />
		</html:select></td>
	 </tr>
     <tr>
		<td width="125"><strong>Identificador do Arquivo:</strong></td>
		<td colspan="2"><html:text maxlength="9" property="idArquivo"
			tabindex="1" size="7"	
			onkeyup="javascript:desabilitarPeriodo();"
			onblur="javascript:desabilitarPeriodo();"			
			onkeypress="validaEnterComMensagem(event, 'exibirGerarRelatorioClienteCpfCnpjValidadosAction.do', 'idArquivo', 'Arquivo');  return isCampoNumerico(event);" />			
			<a href="javascript:chamarPopup('exibirPesquisarArquivoTextoAtualizacaoCadastralAction.do?limpaForm=S', 500, 300,document.forms[0].idArquivo); desabilitarPeriodo(); ">		
		<img width="23" height="21" border="0"
			src="<bean:message key="caminho.imagens"/>pesquisa.gif"
			title="Pesquisar Arquivo" /></a> 
		<logic:present
			name="idArquivoEncontrado" scope="session">
			<html:text maxlength="50" property="descricaoArquivo"
				readonly="true"
				style="background-color:#EFEFEF; border:0; color: #000000"				
				size="32" />
		</logic:present> 
		<logic:notPresent
			name="idArquivoEncontrado" scope="session">
			<html:text maxlength="50" property="descricaoArquivo"
				readonly="true"
				style="background-color:#EFEFEF; border:0; color: #ff0000"
				size="32" />
		</logic:notPresent>
		<a href="javascript:limparArquivo(); desabilitarPeriodo();"> 
		<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
			border="0" title="Apagar" /></a></td>
	</tr>  		
		
     <tr>
         <td>
         	<strong>Período:</strong>
         </td>        
        <td colspan="2">
         	<span class="style2">
         	<strong> 
				<html:text property="periodoInicial" 
					size="11" 
					maxlength="10" 
					tabindex="3"
					onclick="replicaDataEncerramento();"
					onblur="replicaDataEncerramento();"
					onfocus="replicaDataEncerramento();"
					onkeypress="replicaDataEncerramento();" 
					onkeyup="mascaraData(this, event);replicaDataEncerramento();"/>
					
		
				<a href="javascript:chamarCalendario('periodoInicial',document.forms[0].periodoInicial,'periodoFinal');">
				<img border="0" 
					src="<bean:message key='caminho.imagens'/>calendario.gif" 
					width="16" 
					height="15" 
					border="0" 
					alt="Exibir Calendário" 
					tabindex="4"/></a>
					a 
		
				<html:text property="periodoFinal" 
					size="11" 
					maxlength="10" 
					tabindex="3" 
					onkeyup="mascaraData(this, event)"/>		
				<a href="javascript:chamarCalendario('periodoFinal',document.forms[0].periodoFinal,'');">
					<img border="0" 
						src="<bean:message key='caminho.imagens'/>calendario.gif" 
						width="16" 
						height="15" 
						border="0" 
						alt="Exibir Calendário" 
						tabindex="4"/></a>
		
		        </strong>(dd/mm/aaaa)<strong> 
		    </strong>
           	</span>
       	</td>
      </tr>           
          
  	  <tr>
		<td colspan="3">&nbsp;</td>
	 </tr>
	 <tr>
	   <td colspan="3" align="right">
	     <table border="0" width="100%">
		   <tr>
		   <td colspan="2">
				<input name="Submit22"
					class="bottonRightCol" 
					value="Limpar" 
					type="button"
					onclick="window.location.href='/gsan/exibirGerarRelatorioClienteCpfCnpjValidadosAction.do?menu=sim';">
				<input type="button" 
					onclick="javascript:window.location.href='/gsan/telaPrincipal.do'" 
					value="Cancelar" 
					class="bottonRightCol" 
					name="ButtonCancelar"> 			
			</td>
					
			<td align="right" width="50%">					
			 <input name="Button322222" type="button"
				class="bottonRightCol" value="Gerar"
				onClick="javascript:validarForm(document.forms[0]);" />
			</td>
		  </tr>
		</table>
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
<%@ include file="/jsp/util/telaespera.jsp"%>
</body>
</html:html>
