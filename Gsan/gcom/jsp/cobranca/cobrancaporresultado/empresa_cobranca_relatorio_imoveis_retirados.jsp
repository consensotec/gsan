<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>

<%@ include file="/jsp/util/telaespera.jsp"%>

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


	function validaForm(){
		form = document.forms[0];
		if(form.idEmpresa.value == '-1'){
			alert('Informe Empresa');
			return false;
		}
		//if(form.dataPeriodoExInicial.value == ''){
		//	alert('Informe Data Inicial do Período de Execução');
		//	return false;
		//}
		//if(form.dataPeriodoExFinal.value == ''){
		//	alert('Informe Data Final do Período de Execução');
		//	return false;
		//}
		
		toggleBox('demodiv', 1);
	}
	 
		 
	 function campoNumerico(campo) {
		var value = campo.value;
	    var bool = isNaN(+value);
	    bool = bool || (value.indexOf('.') != -1);
	    bool = bool || (value.indexOf(",") != -1);
	    if(bool && value.indexOf("/") == -1)
	    	campo.value = '';
	}
	 
	 function verificaData(event){
			
	       	var valor = null;
	     	if (event.which == null){
	        	valor= String.fromCharCode(event.keyCode);   
			}else if (event.which != 0 && event.charCode != 0){
				valor= String.fromCharCode(event.which);
			}   
			 
			if(valor != '/'){
				return isCampoNumerico(event);
			}
		
	    }
	 
	 
	 function replicar(origem, destino){
			 destino.value = origem.value;
	 }
	 
	 function pesquisarEmpresa() {
		var form = document.forms[0];
		abrirPopup('exibirPesquisarEmpresaAction.do?limpaForm=S', 300, 500);
	}	
	 
	 
	function limparEmpresa(){
		document.forms[0].idEmpresa.value = "";
		document.forms[0].nomeEmpresa.value = "";
	} 
	 
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];
		form.idEmpresa.value = codigoRegistro;
		form.nomeEmpresa.value = descricaoRegistro;
		form.nomeEmpresa.style.color = "#000000";
	   
	}
	
</script>


</head>

<body leftmargin="5" topmargin="5" onload="javascript:document.forms[0].idEmpresa.focus();">
<div id="formDiv">  
<html:form action="/gerarRelatorioImoveisContasRetiradosEmpresasCobrancaAction"
	name="GerarRelatorioImoveisContasRetiradosEmpresasCobrancaActionForm"
	type="gcom.gui.cobranca.cobrancaporresultado.GerarRelatorioImoveisContasRetiradosEmpresasCobrancaActionForm"
	method="post">

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

			<table>
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Relatório dos Imóveis e Contas Retirados das Empresas de Cobrança</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="6">Para gerar o relatório dos imóveis e contas retirados das empresas de cobrança
					, informe os dados abaixo:</td>
					<td align="right"></td>
				</tr>
				
				<tr>
					<td colspan="6">&nbsp;</td>
					<td align="right"></td>
				</tr>

				<tr>

					<td width="15%"><strong>Empresa:<font color="#FF0000">*</font></strong></td>
					<td><html:text maxlength="7" property="idEmpresa" size="7"
						tabindex="14" 
						onkeypress="validaEnterComMensagem(event, 'exibirGerarRelatorioImoveisContasRetiradosEmpresasCobrancaAction.do', 'idEmpresa', 'Empresa');
						return isCampoNumerico(event);"
						onchange="validarCampoNumericoComMensagemLimpandoCampo(document.forms[0].idEmpresa, 'Empresa'); " />
					<a href="javascript:pesquisarEmpresa();"><img
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23"
						height="21" alt="Pesquisar" title="Pesquisar Empresa" border="0"></a> <logic:present
						name="empresaInexistente" scope="request">
						<html:text property="nomeEmpresa" size="30" maxlength="30"
							readonly="true"
							style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
					</logic:present> <logic:notPresent name="empresaInexistente"
						scope="request">
						<html:text property="nomeEmpresa" size="30" maxlength="30"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:limparEmpresa();"> <img
						border="0" src="imagens/limparcampo.gif" height="21" width="23" title="Apagar Empresa"> </a>
					</td>

				</tr>

				<tr>
	                <td width="30%"><strong>Refer&ecirc;ncia de Retirada de Cobrança<font color="#FF0000">*</font></strong></td>
	
						<td width="70%"><strong> 
							<html:text  maxlength="7"
										property="amReferenciaInicial" 
										size="7" 
										tabindex="7"
										onkeypress="return isCampoNumerico(event);"
										onkeyup="mascaraAnoMes(this, event);replicarCampo(document.forms[0].amReferenciaFinal,this);" /> 
								<strong> a</strong>
							<html:text  maxlength="7" 
										property="amReferenciaFinal"
										onkeypress="return isCampoNumerico(event);"
										size="7" 
										tabindex="8" 
										onkeyup="mascaraAnoMes(this, event);" /> </strong>
						mm/aaaa</td>
                 
                 	
				<tr>
					<td>&nbsp;</td>
			    </tr>	
					
				<tr>
					<td class="style1">&nbsp;</td>
					<td colspan="3" class="style1"><font color="#ff0000">*</font>
					Campo Obrigatório</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
			    </tr>	
				              			
				<tr>
					<td colspan="2" ><input name="Button" type="button" class="bottonRightCol"
						value="Desfazer" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirGerarRelatorioImoveisContasRetiradosEmpresasCobrancaAction.do?menu=sim"/>'">
					<input type="button" name="ButtonCancelar" class="bottonRightCol"
						value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"></td>
					<td align="right"><%--<INPUT type="button" class="bottonRightCol" value="Inserir" tabindex="13" style="width: 70px;" onclick="validarForm(document.forms[0]);">--%>

		  			<gsan:controleAcessoBotao name="Botao" value="Gerar"
						onclick="validaForm();"
						url="gerarRelatorioImoveisContasRetiradosEmpresasCobrancaAction.do" tabindex="13" /></td>
				</tr>
				
					
				</table>
			</td></tr>
		</table>
	<p>&nbsp;</p>
		
<%@ include file="/jsp/util/rodape.jsp"%>
<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioImoveisContasRetiradosEmpresasCobrancaAction.do" />
</html:form>
</div>
</body>
</html:html>