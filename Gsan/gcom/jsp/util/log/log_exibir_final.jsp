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

</head>

<script language="JavaScript">
				
	function pesquisar() {
	    var form = document.forms[0];
	    form.verTudo.value = '';
	    form.submit();
	}

	function anterior(campo) {
	    var form = document.forms[0];
	    form.verTudo.value = '';
	    form.inicio.value = campo;
	    form.action='exibirLogTelaFinalAction.do?acao=anterior';
		form.submit();
				
	}

	function primeiro(campo) {
	    var form = document.forms[0];
	    form.verTudo.value = '';
	    form.inicio.value = campo;
	    form.action='exibirLogTelaFinalAction.do?acao=primeiro';
		form.submit();
				
	}

	function proximo(campo) {
	    var form = document.forms[0];
	    form.verTudo.value = '';
	    form.inicio.value = campo;

	    form.action='exibirLogTelaFinalAction.do?acao=proximo';
		form.submit();

	}

	function ultimo(campo) {
	    var form = document.forms[0];
	    form.verTudo.value = '';
	    form.inicio.value = campo;

	    form.action='exibirLogTelaFinalAction.do?acao=ultimo';
		form.submit();

	}
	
	function exibirTudo() {
	    var form = document.forms[0];
	    form.verTudo.value = 'true';
	    form.submit();				
	}
	
	function voltar(){
		
		var form = document.forms[0];
    	
    	form.action='exibirLogTelaInicialAction.do';
		form.submit();
	
	}

	function habilitaEdesabilitaBotaoPagina(){
		var pagina = document.getElementById('numeroPagina');
		var palavra = document.getElementById('palavra');
		var texto = document.getElementById('texto');

		var botaoPagina = document.getElementById('botaoPagina');
		var botaoPalavra = document.getElementById('botaoPalavra');
		var botaoTexto = document.getElementById('botaoTexto');

		if(pagina.value == "" && palavra.value == "" && texto.value == ""){
			botaoPagina.disabled = false;
			botaoPalavra.disabled = false;
			botaoTexto.disabled = false;
		}

		if(pagina.value != ""){
			botaoPagina.disabled = false;
			botaoPalavra.disabled = true;
			botaoTexto.disabled = true;
			palavra.value = "";
			texto.value = "";
		}
	}

	function habilitaEdesabilitaBotaoPalavra(){
		var pagina = document.getElementById('numeroPagina');
		var palavra = document.getElementById('palavra');
		var texto = document.getElementById('texto');

		var botaoPagina = document.getElementById('botaoPagina');
		var botaoPalavra = document.getElementById('botaoPalavra');
		var botaoTexto = document.getElementById('botaoTexto');

		if(pagina.value == "" && palavra.value == "" && texto.value == ""){
			botaoPagina.disabled = false;
			botaoPalavra.disabled = false;
			botaoTexto.disabled = false;
		}
		if(palavra.value != ""){
			botaoPagina.disabled = true;
			botaoPalavra.disabled = false;
			botaoTexto.disabled = true;
			pagina.value = "";
			texto.value = "";
		}
	}

	function habilitaEdesabilitaBotaoTexto(){
		var pagina = document.getElementById('numeroPagina');
		var palavra = document.getElementById('palavra');
		var texto = document.getElementById('texto');

		var botaoPagina = document.getElementById('botaoPagina');
		var botaoPalavra = document.getElementById('botaoPalavra');
		var botaoTexto = document.getElementById('botaoTexto');

		if(pagina.value == "" && palavra.value == "" && texto.value == ""){
			botaoPagina.disabled = false;
			botaoPalavra.disabled = false;
			botaoTexto.disabled = false;
		}
	
		if(texto.value != ""){
			botaoPagina.disabled = true;
			botaoPalavra.disabled = true;
			botaoTexto.disabled = false;
			palavra.value = "";
			pagina.value = "";
		}
	}


</script>

<body leftmargin="5" topmargin="5">

<html:form action="/exibirLogTelaFinalAction.do"
	name="ExibirLogActionForm"
	type="gcom.gui.util.log.ExibirLogActionForm"
	method="post">

	<input type="hidden" name="inicio" value="">
	<input type="hidden" name="verTudo" value="">
	
	
<%@ include file="/jsp/util/cabecalho.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
  		<tr>
    		<td width="770" valign="top" class="centercoltext">
      			
      			<table height="100%">
			        <tr>
			          <td></td>
			        </tr>
      			</table>
      			
		      	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		        	<tr>
		          		<td width="11">
		          			<img border="0" 
		          				src="<bean:message key="caminho.imagens"/>parahead_left.gif"/>
		          		</td>
		          		<td class="parabg">Exibição do LOG - Pagina Atual <b><bean:write name="ExibirLogActionForm" property="paginaAtual"/> </b></td>
		          		<td width="11">
		          			<img border="0" 
		          				src="<bean:message key="caminho.imagens"/>parahead_right.gif"/>
		          		</td>
		        	</tr>
		      	</table>
			   	
			   	<table width="100%" border="0">
			        
			        <tr>
						<bean:write name="ExibirLogActionForm" 
							property="textoErro"
							filter="false"/>
			        </tr>
			        <tr>
			        	<td width="100" align="left">
				        	<logic:notEmpty name="ExibirLogActionForm" property="inicio">
					        	
								<input name="Button" 
									type="button"
									class="bottonRightCol" 
									value="Primeira" 
									onclick="primeiro(0);">
									
								<input name="Button" 
									type="button"
									class="bottonRightCol" 
									value="Anterior" 
									onclick="anterior(<bean:write name="ExibirLogActionForm" property="anterior"/>);">			        
									
				        	</logic:notEmpty>

							<input name="Button" 
								type="button"
								class="bottonRightCol" 
								value="Proxima" 
								onclick="proximo(<bean:write name="ExibirLogActionForm" property="proximo"/>);">
							
							<input name="Button" 
								type="button"
								class="bottonRightCol" 
								value="Ultima" 
								onclick="ultimo(<bean:write name="ExibirLogActionForm" property="ultima"/>);">												        
						</td>
			        </tr>

			        <tr>
			        	<td width="100" align="left">
							Pagina: <input type="text" 
								name="numeroPagina" id="numeroPagina" onchange="habilitaEdesabilitaBotaoPagina();"
								size="5" onkeypress="return isCampoNumerico(event);"
								maxlength="4">
							
							<input name="Button" 
								type="button"
								class="bottonRightCol" 
								value="Pesquisar" id="botaoPagina" 
								onClick="javascript:pesquisar();">
			        	</td>
			        </tr>
			        
			        <tr>
			        	<td width="100" align="left">
							Pesquisar Por Palavra: <input type="text" 
								name="palavra" id="palavra" 
								size="35" onchange="habilitaEdesabilitaBotaoPalavra();"
								maxlength="30">
							
							<input name="Button" 
								type="button" id="botaoPalavra"
								class="bottonRightCol" 
								value="Pesquisar Por Palavra" 
								onClick="javascript:pesquisar();">
			        	</td>
			        </tr>
			        
			        <tr>
			        	<td width="100" align="left">
			        		Exibir apenas as linhas que contém o texto pesquisado:
			        		<input type="text" name="texto" id="texto" size="35" maxlength="30" onchange="habilitaEdesabilitaBotaoTexto();">
			        		<input type="button" name="Button" class="bottonRightCol"  id="botaoTexto"
			        			value="Pesquisar por Texto" onclick="javascript:pesquisar();">
			        	</td>
			        </tr>
			        
			        <tr>
			          	<td width="60" align="left">
							<input name="Button" 
								type="button"
								class="bottonRightCol" 
								value="Voltar" 
								onclick="voltar();">			          	
			          	</td>
			          
			          	<td align="right">
	  		      			<input name="Button" 
								type="button"
								class="bottonRightCol" 
								value="Exibir"
								onclick="exibirTudo();">
			  	      	</td>
			        </tr>
				</table>
			   	
			   	<p>&nbsp;</p>
			    <p>&nbsp;</p>
			    
    		</td>
  		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>