<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@page import="gsan.micromedicao.hidrometro.Hidrometro"
	isELIgnored="false"%>
<%@page import="gsan.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="PesquisarHidrometroActionForm" />

<script language="Javascript">
	function validarForm(form){
		if (validatePesquisarHidrometroActionForm(form) && 
		testarCampoValorZero(form.anoFabricacao, 'Data Fabrica��o') && 
		testarCampoValorZero(form.idLocalArmazenamento, 'Local de Armazenamento')) {
			if(form.numeroHidrometro.value =="" && 
			form.dataAquisicao.value =="" && 
			form.anoFabricacao.value =="" && 
			document.getElementById("indicadorOperacionalAUX").value == "" &&
			form.idHidrometroClasseMetrologica.value =="-1" &&
			form.idHidrometroMarca.value =="-1" && 
			form.idHidrometroDiametro.value =="-1" && 
			form.idHidrometroCapacidade.value =="-1" && 
			form.idHidrometroTipo.value =="-1" && 
			form.idLocalArmazenamento.value =="" &&
			form.idHidrometroSituacao.value == "-1" &&
			form.fixo.value == "" && 
			form.faixaInicial.value == "" && 
			form.faixaFinal.value == ""
			){
				alert('Informe pelo menos uma op��o de sele��o.');
			}else{	
				if(form.numeroHidrometro.value =="" && 
				form.dataAquisicao.value =="" && 
				form.anoFabricacao.value =="" && 
				document.getElementById("indicadorOperacionalAUX").value == "" &&
				form.idHidrometroClasseMetrologica.value =="-1" &&
				form.idHidrometroMarca.value =="-1" && 
				form.idHidrometroDiametro.value =="-1" && 
				form.idHidrometroCapacidade.value =="-1" && 
				form.idHidrometroTipo.value =="-1" && 
				form.idLocalArmazenamento.value =="" &&
				form.idHidrometroSituacao.value == "-1"
				){
					if(form.fixo.value != "" && form.fixo.value != "0" &&
					form.faixaInicial.value != "" && form.faixaInicial.value != "0" &&
					form.faixaFinal.value != "" && form.faixaFinal.value != "0"
					){
						var objFaixaInicial = returnObject(form, "faixaInicial");
						var objFaixaFinal = returnObject(form, "faixaFinal");
						if( parseInt(objFaixaInicial.value * 1) > parseInt(objFaixaFinal.value * 1) ){
							alert('Faixa Final da numera��o do hidr�metro deve ser menor ou igual � Faixa Inicial da numera��o do hidr�metro.');
						}else{
							submeterFormPadrao(form);
						}	
					}else{
						if( form.fixo.value == "0" ){
							alert('Numera��o Fixa do Hidr�metro deve ser maior que zero.');
						}else if( form.faixaInicial.value == "0" ){
							alert('Faixa Inicial da Numera��o do Hidr�metro deve ser maior que zero.');
						}else if( form.faixaFinal.value == "0" ){
							alert('Faixa Final da Numera��o do Hidr�metro deve ser maior que zero.');
						}else if( form.faixaInicial.value == "" ){
							alert('Faixa Inicial da Numera��o do Hidr�metro deve ser informado.');
						}else if( form.faixaFinal.value == "" ){
							alert('Faixa Final da Numera��o do Hidr�metro deve ser informado.');
						}else{
							alert('O preenchimento dos campos fixo e faixa s�o obrigat�rios.');
						}
						return false;
					}
				}else{
					if(form.fixo.value != "" || 
					form.faixaInicial.value != "" || 
					form.faixaFinal.value != ""
					){
						alert('Preencher somente caracter�sticas ou numera��o');
						return false;
					}else{
						
						var msgDataAquisicao = "Data de Aquisi��o n�o pode ser superior � data corrente.";
   						var msgAnoFabricacao = "Ano de Fabrica��o deve ser menor ou igual ao ano da Data de Aquisi��o.";
						var DATA_ATUAL = document.getElementById("DATA_ATUAL").value;
		    		
						if(comparaData(form.dataAquisicao.value, ">", DATA_ATUAL)){
							alert(msgDataAquisicao);
						}else{
							submeterFormPadrao(form);
						}	
					}
				}
			}
		}
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    	var form = document.PesquisarHidrometroActionForm;

	    if (tipoConsulta == 'hidrometroLocalArmazenagem') {
    	  	form.idLocalArmazenamento.value = codigoRegistro;
      		form.descricaoLocalArmazenamento.value = descricaoRegistro;
      		form.descricaoLocalArmazenamento.style.color = "#000000";
    	}
  	}

	function limparPesquisaLocalArmazenamento() {
    	var form = document.PesquisarHidrometroActionForm;

		if (form.idLocalArmazenamento.readOnly == false) {
			form.idLocalArmazenamento.value = "";
		    form.descricaoLocalArmazenamento.value = "";
		}
	    

	}
	//Verifica o valor do objeto radio em tempo de execu��o
	function verificarMarcacao(valor){
		document.getElementById("indicadorOperacionalAUX").value = valor;
	}
	
	function limparForm(){
		var form = document.PesquisarHidrometroActionForm;
			
		form.numeroHidrometro.value = "";
		form.dataAquisicao.value = "";
		form.anoFabricacao.value = "";
		form.finalidade[0].checked = false;
		form.finalidade[1].checked = false;	
		form.idHidrometroClasseMetrologica.value =  "-1";
		form.idHidrometroMarca.value = "-1";
		form.idHidrometroDiametro.value =  "-1";
		form.idHidrometroCapacidade.value =  "-1";
		form.idHidrometroTipo.value =  "-1";
		form.idLocalArmazenamento.value = "";
		form.descricaoLocalArmazenamento.value = "";
		form.idHidrometroSituacao.value =  "-1";
		form.fixo.value = "";
		form.faixaInicial.value = "";
		form.faixaFinal.value = "";
	}
	
</script>

</head>

<body leftmargin="5" topmargin="5" onload="javascript:resizePageSemLink(660,660);javascript:setarFoco('${requestScope.nomeCampo}');">
<html:form action="/pesquisarHidrometroAction.do"
	name="PesquisarHidrometroActionForm"
	type="gsan.gui.micromedicao.hidrometro.PesquisarHidrometroActionForm"
	method="post">

	<input type="hidden" id="indicadorOperacionalAUX" value="" />
	<INPUT TYPE="hidden" ID="DATA_ATUAL" value="${requestScope.dataAtual}" />

	<table width="630" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="625" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img
						src="<bean:message key="caminho.imagens"/>parahead_left.gif"
						border="0" /></td>
					<td class="parabg">Pesquisar Hidr&ocirc;metro</td>
					<td width="11"><img
						src="<bean:message key="caminho.imagens"/>parahead_right.gif"
						border="0" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para pesquisar o hidr&ocirc;metro, informe as
					caracter&iacute;sticas ou a numera&ccedil;&atilde;o dos
					hidr&ocirc;metros:</td>
				</tr>
				<tr>
					<td height="23" colspan="2">
					<hr>
					</td>
				</tr>
				
				
				<tr>
					<td colspan="2">
						<table width="100%">
				
							<tr>
								<td height="18" colspan="2" class="style1"><strong>Caracter&iacute;sticas
								dos Hidr�metros:</strong></td>
							</tr>
							<tr>
								<td width="30%"><strong>N&uacute;mero do Hidr&ocirc;metro:</strong></td>
								<td><input name="numeroHidrometro" type="text" size="13"
									maxlength="12"></td>
							</tr>
							<tr>
								<td><strong>Data de Aquisi&ccedil;&atilde;o:</strong></td>
								<td>
									<input name="dataAquisicao" type="text" size="10"
										maxlength="10" onkeyup="mascaraData(this, event);"><img
									src="<bean:message key="caminho.imagens"/>calendario.gif"
									onclick="javascript:abrirCalendario('PesquisarHidrometroActionForm', 'dataAquisicao')"
									width="16" height="15"> (dd/mm/aaaa)</td>
							</tr>
							<tr>
								<td><strong>Ano de Fabrica&ccedil;&atilde;o:</strong></td>
								<td><input name="anoFabricacao" type="text" size="4" maxlength="4">
								(aaaa)</td>
							</tr>
							<tr>
								<td><strong>Finalidade:</strong></td>
								<td>
								  <html:radio property="finalidade" value="<%=(Hidrometro.INDICADOR_COMERCIAL).toString()%>" onclick="verificarMarcacao(this.value)"/><strong>Comercial</strong>
								  <html:radio property="finalidade" value="<%=(Hidrometro.INDICADOR_OPERACIONAL).toString()%>" onclick="verificarMarcacao(this.value)"/><strong>Operacional</strong>
								</td>
							</tr>
							<tr>
								<td><strong>Classe Metr&oacute;logica:</strong></td>
			
								<td><html:select property="idHidrometroClasseMetrologica">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
									<html:options collection="hidrometrosClasseMetrologica"
										labelProperty="descricao" property="id" />
								</html:select></td>
							</tr>
							<tr>
								<td><strong>Marca:</strong></td>
								<td><html:select property="idHidrometroMarca">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
									<html:options collection="hidrometrosMarca"
										labelProperty="descricao" property="id" />
								</html:select></td>
							</tr>
							<tr>
								<td height="27"><strong>Di�metro:</strong></td>
								<td><html:select property="idHidrometroDiametro">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
									<html:options collection="hidrometrosDiametro"
										labelProperty="descricao" property="id" />
								</html:select></td>
							</tr>
							<tr>
								<td><strong>Capacidade</strong></td>
								<td><html:select property="idHidrometroCapacidade">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
									<html:options collection="hidrometrosCapacidade"
										labelProperty="descricao" property="id" />
								</html:select></td>
							</tr>
							<tr>
								<td><strong>Tipo de Fluxo:</strong></td>
								<td><html:select property="idHidrometroTipo">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
									<html:options collection="hidrometrosTipo"
										labelProperty="descricao" property="id" />
								</html:select></td>
							</tr>
			
			
							<tr>
								<td><strong>Local de Armazenamento:</strong></td>
								<td>
								<logic:present name="bloquearLocalArmazenagem" scope="session">
									<html:text property="idLocalArmazenamento" size="3"
									maxlength="3" readonly="true"
									onkeypress="validaEnter(event, 'exibirPesquisarHidrometroAction.do?consultarLocalArmazenamento=1&objetoConsulta=1', 'idLocalArmazenamento');" />
									<img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
									width="23" height="21" style="cursor:hand;"
									alt="Pesquisar"> 
								</logic:present>
								<logic:notPresent name="bloquearLocalArmazenagem" scope="session">
								<html:text property="idLocalArmazenamento" size="3"
									maxlength="3"
									onkeypress="validaEnter(event, 'exibirPesquisarHidrometroAction.do?consultarLocalArmazenamento=1&objetoConsulta=1', 'idLocalArmazenamento');" />
									<img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
									width="23" height="21" style="cursor:hand;"
									onclick="resizePageComLink('exibirPesquisarTabelaAuxiliarAbreviadaAction.do?tela=hidrometroLocalArmazenagem&caminhoRetorno=exibirPesquisarHidrometroAction&tipoPesquisa=popup', 495, 300);"
									alt="Pesquisar"> 
								</logic:notPresent>
								<c:if
									test="${empty requestScope.localArmazenagemEncontrado}"
									var="localNaoEncontrado">
									<html:text property="descricaoLocalArmazenamento" size="45"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #ff0000" />
								</c:if> <c:if test="${!localNaoEncontrado}">
									<html:text property="descricaoLocalArmazenamento" size="45"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</c:if> <a href="javascript:limparPesquisaLocalArmazenamento();"> <img
									src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" title="Apagar" /></a></td>
							</tr>
							<tr>
								<td><strong>Situa&ccedil;&atilde;o:</strong></td>
								<td><html:select property="idHidrometroSituacao">
									<html:option
										value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
									<html:options collection="hidrometrosSituacao"
										labelProperty="descricao" property="id" />
								</html:select></td>
							</tr>
							<tr>
								<td height="23" colspan="2">
								<hr>
								</td>
							</tr>
							<tr>
								<td height="18" colspan="2" class="style1"><strong>Numera��o dos
								Hidr�metros:</strong></td>
							</tr>
							<tr>
								<td class="style1"><strong>Fixo:</strong></td>
								<td class="style1"><input name="fixo" type="text" size="4"
									maxlength="4"></td>
							</tr>
							<tr>
								<td class="style1"><strong>Faixa:</strong></td>
								<td class="style1"><input name="faixaInicial" type="text" size="6"
									maxlength="6"> <input name="faixaFinal" type="text" size="6"
									maxlength="6"></td>
							</tr>
						</table>
					</td>
				</tr>
		
				<tr>
			        <td height="24" width="80%">
				       	<INPUT TYPE="button" class="bottonRightCol" value="Limpar" 
				       	onclick="javascript:limparForm();window.location.href='<html:rewrite page="/exibirPesquisarHidrometroAction.do"/>'"
				       	/>
				       	&nbsp;&nbsp;
				       	<logic:present name="caminhoRetornoTelaPesquisaHidrometro"> 
				       		<INPUT TYPE="button" class="bottonRightCol" value="Voltar" onclick="redirecionarSubmit('${caminhoRetornoTelaPesquisaHidrometro}.do')"/>
				       	 </logic:present> 
			        </td>
			        <td align="right">
			        	<input name="Button" type="button" class="bottonRightCol" value="Pesquisar"
						onclick="javascript:validarForm(document.PesquisarHidrometroActionForm)">
					</td>
		        </tr>
				
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html>
