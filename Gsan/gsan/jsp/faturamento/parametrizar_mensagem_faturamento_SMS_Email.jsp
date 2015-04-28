<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gsan.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

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
	formName="InserirBaciaActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript">

	function cancelar(){
		var form = document.forms[0];
		form.action = "telaPrincipal.do";
		form.submit();
	}

	function desfazer(){
		var form = document.forms[0];
		form.action = "exibirParametrizarMensagemFaturamentoSMSEmailAction.do?menu=sim";
		form.submit();
	}

	function concluir(){
		var form = document.forms[0];
		var valorMinimoParam = validarValorMinimo();
		if(validarDataReferencia()==true){
			if(valorMinimoParam == ""){
				if(validarSelecaoCategorias()==true){
					if(validarTextoSMSEmail(1)==true){
						if(validarTextoSMSEmail(2)==true){
							if(validarNumeroMaximoTentativas()==true){
								var navegador = "";
								if(/MSIE (\d+\.\d+);/.test(navigator.userAgent)){
									navegador =  "?navegadorIE=true";
								}

								botaoAvancarTelaEspera('/gsan/parametrizarMensagemFaturamentoSMSEmailAction.do'+navegador);
							}else{
								alert("Número máximo de tentativas deve somente conter números positivos.");
							}
						}else{
							alert("Informe Texto E-MAIL.");
						}
					}else{
						alert("Informe Texto SMS.");
					}
				}else{
					alert("Informe Categoria do imóvel.");
				}
			}else{
				alert(valorMinimoParam);
			}
		}else{
			alert("Ano mês de referência inválido.");
		}
	}

	function validarDataReferencia(){
		var form = document.forms[0];

		if(form.anoMesFaturamento.value.length==7){
			var anoMesFaturamento = form.anoMesFaturamento.value.split("/");
			
			var parteMes = anoMesFaturamento[0];
			var parteAno = anoMesFaturamento[1];

			if(parteMes.length==2){
				if(parteAno.length==4){
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	function validarValorMinimo(){
		var form = document.forms[0];
		if (form.valorMinimoConta.value == "" || form.valorMinimoConta.value.length <= 0) {
			return "Informe Valor mínimo da conta.";
		} else if (parseFloat(form.valorMinimoConta.value.replace(",",".")) <= 0) {
			return "Valor mínimo da conta deve somente conter números positivos.";
		} else {
			return "";
		}
	}

	function validarSelecaoCategorias(){
		var form = document.forms[0];
		for(var posicao = 0;posicao<form.categorias.length;posicao++){
			if(form.categorias.options[posicao].selected==true){
				return true;
				break;
			}
		}
		return false;
	}

	function validarTextoSMSEmail(parametro){
		var form = document.forms[0];

		if(parametro==1){
			if(form.descricaoTextoSMS.value!=""){
				return true;
			}

		}else if(parametro==2){
			if(form.descricaoTextoEmail.value!=""){
				return true;
			}
		}
		return false;
	}

	function validarNumeroMaximoTentativas(){
		var form = document.forms[0];

		if(form.quantidadeTentativaEnvio.value!="" &&  form.quantidadeTentativaEnvio.value>0){
			return true;
		}
		return false;
	}
	
	function parametrosSMS(maximoSMSSP){
		var form = document.forms[0];
		var informouParametro = false;
		if(validarSeInformouTag()){
			if(validarQuantidadeCaractereAoInserirTag(recuperarTag(),form.descricaoTextoSMS.value,maximoSMSSP)){
				for(var posicao = 0;posicao<form.selecionarDadosParaTexto.length;posicao++){
					if(form.selecionarDadosParaTexto.options[posicao].selected==true){
						form.descricaoTextoSMS.focus();
						form.descricaoTextoSMS.value =form.descricaoTextoSMS.value+form.selecionarDadosParaTexto.options[posicao].value;
					}
				}
			}else{
				alert("Número de colunas digitadas/selecionadas somada as 57 posições do código de barras é maior que o máximo permitido  ("+maximoSMSSP+") para o SMS!");
			}
		}else{
			alert("Selecione antes o dado que será incluído no texto");
		}
	}

	function validarSeInformouTag(form){
		var form = document.forms[0];
		for(var posicao = 0;posicao<form.selecionarDadosParaTexto.length;posicao++){
			if(form.selecionarDadosParaTexto.options[posicao].selected==true){
				return true;
			}
		}

		return false;
	}
	
	function validarQuantidadeCaractereAoInserirTag(tag,textAreaSMS,maximoSMSSP){
		var form = document.forms[0];
		if(tag.length+textAreaSMS.length<=maximoSMSSP){
			return true;
		}
		return false;
	}

	function recuperarTag(){
		var form = document.forms[0];
		for(var posicao = 0;posicao<form.selecionarDadosParaTexto.length;posicao++){
			if(form.selecionarDadosParaTexto.options[posicao].selected==true){
				return form.selecionarDadosParaTexto.options[posicao].value;
			}
		}

		return false;
	}
	
	function parametrosEmail(){
		var form = document.forms[0];
		var informouParametro = false;
		for(var posicao = 0;posicao<form.selecionarDadosParaTexto.length;posicao++){
			if(form.selecionarDadosParaTexto.options[posicao].selected==true){
				form.descricaoTextoEmail.focus();
				form.descricaoTextoEmail.value =form.descricaoTextoEmail.value+form.selecionarDadosParaTexto.options[posicao].value;
				informouParametro = true;
			}
		}

		if(informouParametro==false){
			alert("Selecione antes o dado que será incluído no texto");
		}
	}

	function carregarParametrosDataRetiradaNull(indicadorPreenchimento,valorMinimoConta,quantidadeDiasAntesVencimento,
			quantidadeTentativaEnvio,descricaoTextoSMS,descricaoTextoEmail,
			indicadorComercial,indicadorIndustrial,indicadorPublico,indicadorResidencial,anoMesFaturamento){
		var form = document.forms[0];

		if(indicadorPreenchimento=="1"){
			form.anoMesFaturamento.value = anoMesFaturamento;
			form.valorMinimoConta.value = valorMinimoConta;
			form.quantidadeDiasAntesVencimento.value = quantidadeDiasAntesVencimento;
			form.quantidadeTentativaEnvio.value = quantidadeTentativaEnvio;
			form.descricaoTextoSMS.value = descricaoTextoSMS;
			form.descricaoTextoEmail.value = descricaoTextoEmail;

			if(indicadorResidencial=="1"){
				form.categorias.options[0].selected = true;
			}

			if(indicadorComercial=="1"){
				form.categorias.options[1].selected = true;
			}

			if(indicadorIndustrial=="1"){
				form.categorias.options[2].selected = true;
			}

			if(indicadorPublico=="1"){
				form.categorias.options[3].selected = true; 
			}
		}
	}
	
	function removerTag(campoTextareaSMSEmail,indicadorTextarea,evento){
		var form  = document.forms[0];
		var campoTextareaSMSEmail_temp = campoTextareaSMSEmail.value.split("");
		var caractereLimpado = null;

		if(campoTextareaSMSEmail.selectionStart){
			caractereLimpado =  campoTextareaSMSEmail.selectionStart - 1;	
		}
		
		var informacaoPosicaoCaractereLimpado = "";
		var textoTextarea = "";
		var tecla = null;
		if(window.event){ 
	  		tecla = evento.keyCode;
		}else if(evento.which){ 
		    	tecla = evento.which;
		}
		if(tecla == 8){
			for(var posicao = 0;posicao<campoTextareaSMSEmail_temp.length;posicao++){
				if(posicao==caractereLimpado){
					informacaoPosicaoCaractereLimpado = campoTextareaSMSEmail_temp[posicao];
					break;
				}
			}

			if(informacaoPosicaoCaractereLimpado=="}"){
				for(var removerParaEsquerda = caractereLimpado;removerParaEsquerda>=0;removerParaEsquerda--){
					if(campoTextareaSMSEmail_temp[removerParaEsquerda]!="{"){
						campoTextareaSMSEmail_temp[removerParaEsquerda] = "";
					}else{
						campoTextareaSMSEmail_temp[removerParaEsquerda] = "";
						break;
					}
				}

				textoTextarea = campoTextareaSMSEmail_temp.toString();
				for(var posicao = 0;posicao<campoTextareaSMSEmail_temp.length;posicao++){
					textoTextarea = textoTextarea.replace(",","");
				}

				if(indicadorTextarea==1){
					form.descricaoTextoSMS.value = textoTextarea+" ";
				}else if(indicadorTextarea==2){
					form.descricaoTextoEmail.value = textoTextarea+" ";
				}
				
			}else if(informacaoPosicaoCaractereLimpado=="{"){
				for(var removerParaDireita = caractereLimpado;removerParaDireita>=0;removerParaDireita++){
					if(campoTextareaSMSEmail_temp[removerParaDireita]!="}"){
						campoTextareaSMSEmail_temp[removerParaDireita] = "";
					}else{
						campoTextareaSMSEmail_temp[removerParaDireita] = "";
						break;
					}
				}

				textoTextarea = campoTextareaSMSEmail_temp.toString();
				for(var posicao = 0;posicao<campoTextareaSMSEmail_temp.length;posicao++){
					textoTextarea = textoTextarea.replace(",","");
				}
				
				if(indicadorTextarea==1){
					form.descricaoTextoSMS.value = textoTextarea+" ";
				}else if(indicadorTextarea==2){
					form.descricaoTextoEmail.value = textoTextarea+" ";
				}
			}else{
				var buscaEsquerdaEncontrou = false;

				for(var buscaEsquerda = caractereLimpado;buscaEsquerda>=0;buscaEsquerda--){
					if(campoTextareaSMSEmail_temp[buscaEsquerda]=="{"){
						buscaEsquerdaEncontrou = true;
					}
				}

				var buscaDireitaEncontrou = false;

				for(var buscaDireita = caractereLimpado;buscaDireita<campoTextareaSMSEmail_temp.length;buscaDireita++){
					if(campoTextareaSMSEmail_temp[buscaDireita]=="}"){
						buscaDireitaEncontrou = true;
					}
				}

				if(buscaEsquerdaEncontrou==true && buscaDireitaEncontrou==true){
					for(var buscaEsquerda = caractereLimpado;buscaEsquerda>=0;buscaEsquerda--){
						if(campoTextareaSMSEmail_temp[buscaEsquerda]!="{"){
							campoTextareaSMSEmail_temp[buscaEsquerda]  = "";
						}else{
							campoTextareaSMSEmail_temp[buscaEsquerda]  = "";
							break;
						}				
					}

					for(var buscaDireita = caractereLimpado;buscaDireita<campoTextareaSMSEmail_temp.length;buscaDireita++){
						if(campoTextareaSMSEmail_temp[buscaDireita]!="}"){
							campoTextareaSMSEmail_temp[buscaDireita]  = "";
						}else{
							campoTextareaSMSEmail_temp[buscaDireita]  = "";
							break;
						}
					}
		
							textoTextarea = campoTextareaSMSEmail_temp.toString();
					for(var posicao = 0;posicao<=campoTextareaSMSEmail_temp.length;posicao++){
						textoTextarea = textoTextarea.replace(",","");
					}

					if(indicadorTextarea==1){
						form.descricaoTextoSMS.value = textoTextarea.toString()+" ";
					}else if(indicadorTextarea==2){
						form.descricaoTextoEmail.value = textoTextarea.toString()+" ";
					}
				}
			}
		}
	}

	function validateParametrizarMensagemFaturamentoSMSEmailActionForm(form) {                                                                   
		return true; 
	}
</script>

</head>

<body leftmargin="5" topmargin="5">
	
<div id="formDiv">

<html:form action="/parametrizarMensagemFaturamentoSMSEmailAction.do"
	name="ParametrizarMensagemFaturamentoSMSEmailActionForm"
	type="gsan.gui.faturamento.ParametrizarMensagemFaturamentoSMSEmailActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>


	<table width="770" border="0" cellspacing="5" cellpadding="0">

		<tr>
			<td width="150" valign="top" class="leftcoltext">
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
					<td class="parabg">Parametrizar Mensagem de Faturamento (SMS/E-MAIL)</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->

			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para Parametrizar Mensagem de Faturamento (SMS/E-MAIL), informe os dados abaixo:</td>
				</tr>
				
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				
				<tr>
					<td><strong>Referência do Faturamento:</strong></td>
					<td><html:text property="anoMesFaturamento" size="7" maxlength="7" readonly="true"/><strong> mm/aaaa</strong></td>
				</tr>
				
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				
				<tr>
					<td><strong>Valor mínimo da conta:<font color="red">*</font></strong></td>
					<td><html:text property="valorMinimoConta"  onkeyup="formataValorMonetario(this, 13);" /></td>
				</tr>
				
				<tr>
					<td colpsan="2">&nbsp;</td>
				</tr>
				
				<tr>
					<td><strong>Categoria do imóvel:<font color="red">*</font></strong></td>
					<td>
						<html:select property="categorias" multiple="true">
							<logic:present name="colecaoCategoria" scope="session">
								<logic:iterate name="colecaoCategoria" id="categoria">
									<html:option value="${categoria.id}">
										${categoria.descricao}
									</html:option>						
								</logic:iterate>
							</logic:present>
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				
				<tr bgcolor="#90c7fc">
					<td colspan="2" align="center"><strong>Regras para envio</strong></td>
				</tr>
				
				<tr>
					<td><strong>Quantidade de dias antes do vencimento:</strong></td>
					<td><html:text property="quantidadeDiasAntesVencimento"  maxlength="2" size="5" onkeyup="somente_numero(this);" /></td>
				</tr>
				
				<tr>
					<td><strong>Número máximo de tentativas para EMAIL:<font color="red">*</font></strong></td>
					<td><html:text property="quantidadeTentativaEnvio"  maxlength="2" size="5" onkeyup="somente_numero(this);" /></td>
				</tr>
				
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				
				<tr bgcolor="#90c7fc">
					<td colspan="2" align="center"><strong>Texto da Mensagem</strong></td>
				</tr>
				
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				
				<tr>
					<td>
						<strong>
							Selecione dados para o texto:
						</strong>
						<br>
						<font size="1">
							Obs.: será acrescentado o código de barras ao final do texto (57 posições).
						</font>
					</td>
					<td>
						
						<html:select property="selecionarDadosParaTexto" size="7">
							<logic:present name="colecaoColunasTextoSMSEmail" scope="session">
								<logic:iterate name="colecaoColunasTextoSMSEmail" id="colunasTextoSMSEmail">
									<html:option value="${colunasTextoSMSEmail.nomeColuna}">
										${colunasTextoSMSEmail.descricaoColuna} - ${colunasTextoSMSEmail.tamanhoColuna} posições
									</html:option>
								</logic:iterate>
							</logic:present>
						</html:select>
						<br>
						<input type="button" value="SMS" class="bottonRightCol" onclick="parametrosSMS(${ParametrizarMensagemFaturamentoSMSEmailActionForm.maximoSMSSP});">
						<input type="button" value="E-MAIL" class="bottonRightCol" onclick="parametrosEmail();">
					</td>
				</tr>
				
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				
				<tr>
					<td><strong>Texto SMS:<font color="red">*</font></strong></td>
					<td><html:textarea property="descricaoTextoSMS" cols="40" onkeydown="removerTag(this,1,event)" /></td>
				</tr>
				
				<tr>
					<td><strong>Texto E-MAIL:<font color="red">*</font></strong></td>
					<td><html:textarea property="descricaoTextoEmail" cols="40" onkeydown="removerTag(this,2,event)" /> </td>
				</tr>
				
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				
				<tr>
					<td align="left">
						<input type="button" value="Desfazer" class="bottonRightCol" onclick="desfazer();">
						<input type="button" value="Cancelar" class="bottonRightCol" onclick="cancelar();">
					</td>
					<td align="right">
						<input type="button" value="Atualizar" class="bottonRightCol" onclick="concluir();">
					</td>
				</tr>
				
			</table>
			<p>&nbsp;</p>
		</tr>
	</table>
	<tr>
		<td colspan="3"><%@ include file="/jsp/util/rodape.jsp"%>
	</tr>
</html:form>

</div>

<%@ include file="/jsp/util/telaespera.jsp"%>

</body>
</html:html>
