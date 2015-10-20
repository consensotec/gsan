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

<%@ page import="gcom.util.ConstantesSistema"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"  formName="EmissaoRelatorioQuadroMetasExercicioActionForm"/>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript">
	
	function validarForm(){
		
		var form = document.forms[0];
		
		if(validateEmissaoRelatorioQuadroMetasExercicioActionForm(form) && 
			validaCamposObrigatorios()){

			toggleBox('demodiv',1);
			//submeterFormPadrao(form);
		}
	}
	
	
	function validaCamposObrigatorios(){
	
		var form = document.forms[0];
		
		var opcaoTotalizacao = form.opcaoTotalizacao.value;
		var msg = "";
		
 		if(opcaoTotalizacao >= 6 && opcaoTotalizacao <= 9){
		
			if(form.gerenciaRegional.value == "-1"){
				msg = "Informe Ger�ncia Regional";
				form.gerenciaRegional.focus();
			}
		
		}else if(opcaoTotalizacao >= 10 && opcaoTotalizacao <= 12){

			if(form.gerenciaRegional.value == "-1"){
				msg = "Informe Ger�ncia Regional";
				form.gerenciaRegional.focus();
			}
			
			if(form.unidadeNegocio.value == "-1"){

				if(msg == ""){
					msg = "Informe Unidade de Neg�cio";
					form.unidadeNegocio.focus();
				}else{
					msg = msg+"\n"+"Informe Unidade de Neg�cio";
				}				
			}
			
		}else if(opcaoTotalizacao >= 16 && opcaoTotalizacao <= 18){
			
			if(form.localidade.value == ""){
				msg = "Informe Localidade";
				form.localidade.focus();
			}
		
		}	
		
		if(msg != ""){
			alert(msg);
			return false;
		}else{
			return true;
		}
	
	}
	
	function habilitaDesabilitaFormulario(limpaSessao){
		var form = document.forms[0];
		
		
		var opcaoTotalizacao = form.opcaoTotalizacao.value;
		
		if(opcaoTotalizacao >= 1 && opcaoTotalizacao <= 5){
		
			habilitaDesabilitaGerenciaRegional(true);
			habilitaDesabilitaUnidadeNegocio(true);
			habilitaDesabilitaLocalidade(true);
		
		}else if(opcaoTotalizacao >= 6 && opcaoTotalizacao <= 9){
		
			habilitaDesabilitaGerenciaRegional(false);
			
			habilitaDesabilitaUnidadeNegocio(true);
			habilitaDesabilitaLocalidade(true);
		
		}else if(opcaoTotalizacao >= 10 && opcaoTotalizacao <= 12){

			habilitaDesabilitaGerenciaRegional(false);
			habilitaDesabilitaUnidadeNegocio(false);
			
			habilitaDesabilitaLocalidade(true);
			
		}else if(opcaoTotalizacao >= 16 && opcaoTotalizacao <= 18){
			
			habilitaDesabilitaLocalidade(false);
			
			habilitaDesabilitaGerenciaRegional(true);
			habilitaDesabilitaUnidadeNegocio(true);
			
		}else{
			habilitaDesabilitaLocalidade(false);
			habilitaDesabilitaGerenciaRegional(false);
			habilitaDesabilitaUnidadeNegocio(false);
		}
		
		if(limpaSessao){
			limparSemOpcaoTotalizacao();
		}
	}
	
	function limparSemOpcaoTotalizacao(){
  		var form = document.forms[0];
  		
  		limparLocalidade();

  		form.gerenciaRegional.value = "-1";
  		form.unidadeNegocio.value = "-1";

  		form.action='exibirGerarQuadroMetasExercicioAction.do?menu=sim';
	    form.submit();
  		
	}  
	
	/*
	*	valor = true - desabilita
	*	valor = false - habilita
	*/
	function habilitaDesabilitaGerenciaRegional(valor){
		var form = document.forms[0];
		
		if(valor){
			form.gerenciaRegional.value = "-1";
		}
		
		form.gerenciaRegional.disabled = valor;
	}
	
	function habilitaDesabilitaUnidadeNegocio(valor){
		var form = document.forms[0];
		
		if(valor){			
			form.unidadeNegocio.value = "-1";
		}
		form.unidadeNegocio.disabled = valor;
	}
	
	function habilitaDesabilitaLocalidade(valor){
		var form = document.forms[0];
		if(valor){			
			form.localidade.value = "";
			form.nomeLocalidade.value = "";
		}
		form.localidade.disabled = valor;
	
	}
		
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    
	    var form = document.forms[0];
	    
		if (tipoConsulta == 'localidade') {

			form.localidade.value = codigoRegistro;
		    form.nomeLocalidade.value = descricaoRegistro;
		    form.nomeLocalidade.style.color = "#000000";
	    
	    }	
	}
	
	/* Chama Popup */ 
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		
		if(objetoRelacionado.disabled != true){
			
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			} else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				} else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
				}
			}
		}
	}	
	
	function limparLocalidade() {
    	var form = document.forms[0];

      	form.localidade.value = "";
      	form.nomeLocalidade.value = "";
  	}
  	
  	function reloadForm(){
  		var form = document.forms[0];
  	
  		form.action='exibirGerarQuadroMetasExercicioAction.do';
	    form.submit();
  	}
  	
  	function limpar(){
  		var form = document.forms[0];
  		
  		limparLocalidade();

  		form.gerenciaRegional.value = "-1";
  		form.unidadeNegocio.value = "-1";
  		form.opcaoTotalizacao.value = "1";

  		form.action='exibirGerarQuadroMetasExercicioAction.do?menu=sim';
	    form.submit();  	
  	}  		
  	
</script>

</head>

<body leftmargin="5" topmargin="5" onload="javascript:habilitaDesabilitaFormulario(false);setarFoco('${requestScope.mesAnoFaturamento}');">

<html:form action="/gerarRelatorioQuadroMetasExercicioAction.do"
	name="EmissaoRelatorioQuadroMetasExercicioActionForm"
	type="gcom.gui.relatorio.gerencial.faturamento.EmissaoRelatorioQuadroMetasExercicioActionForm"
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
					<td class="parabg">Gerar Quadro de Metas por Exerc&iacute;cio </td>
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
                	<td><strong>Exerc&iacute;cio :</strong><font color="#FF0000">*</font></td>
                	<td colspan="6">
                		<strong>
                  			<html:text property="anoExercicio" 
                  				size="4" 
                  				maxlength="4"/>
                  				&nbsp;(aaaa)
                  		</strong>
                  	</td>
              	</tr>
              	
				<tr>

					<td>
						<strong>Op&ccedil;&atilde;o de Totaliza&ccedil;&atilde;o:</strong>
						<font color="#FF0000">*</font>
					</td>

					<td>
						<strong> 
						<html:select property="opcaoTotalizacao" 
							style="width: 300px;"
							onchange="javascript:habilitaDesabilitaFormulario(true);" >

		                    <html:option value="1">ESTADO</html:option>
		                    <html:option value="2">ESTADO POR GERENCIAL REGIONAL</html:option>
		                    <html:option value="3">ESTADO POR UNIDADE DE NEG&Oacute;CIO</html:option>
		                    <html:option value="5">ESTADO POR LOCALIDADE</html:option>
		                    <html:option value="6">GER&Ecirc;NCIA REGIONAL</html:option>
		                    <html:option value="10">UNIDADE DE NEG&Oacute;CIO</html:option>
		                    <html:option value="16">LOCALIDADE</html:option>
					
						</html:select> 														
						</strong>
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
					<td><strong>Localidade:</strong></td>
					
					<td>
						
						<html:text maxlength="3" 
							tabindex="1"
							property="localidade" 
							size="3"
							onkeypress="validaEnterComMensagem(event, 'exibirGerarQuadroMetasExercicioAction.do?objetoConsulta=2','localidade','Localidade');"/>
							
						<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do?tipo=imovelLocalidade&indicadorUsoTodos=1', 'localidade', null, null, 275, 480, '', document.forms[0].localidade);">
						
							<img width="23" 
								height="21" 
								border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Localidade" /></a> 

						<logic:present name="localidadeEncontrada" scope="request">
							<html:text property="nomeLocalidade" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="localidadeEncontrada" scope="request">
							<html:text property="nomeLocalidade" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>

						
						<a href="javascript:limparLocalidade();"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
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
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioQuadroMetasExercicioAction.do" />

</html:form>
</html:html>
