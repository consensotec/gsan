<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="CategoriaActionForm"/>

<script language="JavaScript">
<!--
// Verifica se o n�mero do campo do formul�rio � negativo
function numeroNegativo(valor,campo,descricao){
	if(valor < 0){ 
		alert(descricao+' deve somente conter n�meros positivos.');
		campo.focus();
		return true;
	}
}

// Faz as valida��es do formul�rio
function validarForm(form){
	// Verifica se os campos s�o vazios 
	if(validateCategoriaActionForm(form)){
		var retorno = true;
		var objConsumoMinimo = returnObject(form, "consumoMinimo");
		var objConsumoEstouro = returnObject(form, "consumoEstouro");
		var objVezesMediaEstouro = returnObject(form, "vezesMediaEstouro");
		var objMediaBaixoConsumo = returnObject(form, "mediaBaixoConsumo");		
		var objPorcentagemMediaBaixoConsumo = returnObject(form, "porcentagemMediaBaixoConsumo");		
		var objConsumoAlto = returnObject(form, "consumoAlto");		
		var objVezesMediaAltoConsumo = returnObject(form, "vezesMediaAltoConsumo");		
		
		// Verifica se os campos s�o negativos
		if(numeroNegativo(objConsumoMinimo.value,objConsumoMinimo,'Consumo M�nimo da Categoria')){
			retorno = false;
		}else if(numeroNegativo(objConsumoEstouro.value,objConsumoEstouro,'Consumo de Refer�ncia do Estouro de Consumo')){
			retorno = false;
		}else if(numeroNegativo(objVezesMediaEstouro.value,objVezesMediaEstouro,'Fator de Multiplica��o do Consumo M�dio do Estouro do Consumo')){
			retorno = false;
		}else if(numeroNegativo(objMediaBaixoConsumo.value,objMediaBaixoConsumo,'Consumo M�dio de Refer�ncia do Baixo Consumo')){
			retorno = false;
		}else if(numeroNegativo(objPorcentagemMediaBaixoConsumo.value,objPorcentagemMediaBaixoConsumo,'Percentual do Consumo M�dio do Baixo Consumo')){
			retorno = false;
		}else if(numeroNegativo(objConsumoAlto.value,objConsumoAlto,'Consumo de Refer�ncia do Alto Consumo')){
			retorno = false;
		}else if(numeroNegativo(objVezesMediaAltoConsumo.value,objVezesMediaAltoConsumo,'Fator de Multiplica��o do Consumo M�dio do Alto Consumo')){
			retorno = false;
		}
		// Valida��es do Caso de Uso
		if(parseInt(objConsumoEstouro.value) < parseInt(objConsumoMinimo.value)){
		   	retorno = false;
		   	alert('Consumo de Refer�ncia do Estouro do Consumo deve ser superior ao Consumo M�nimo da Categoria.');
		   	objConsumoEstouro.focus();
		}else if(parseInt(objVezesMediaEstouro.value) < 1){
		   	retorno = false;
		   	alert('Fator de Multiplica��o da M�dia do Estouro do Consumo deve ser maior ou igual a um.');
		   	objVezesMediaEstouro.focus();
		}else if(parseInt(objMediaBaixoConsumo.value) < parseInt(objConsumoMinimo.value)){
		   	retorno = false;
		   	alert('Consumo M�dio de Refer�ncia do Baixo Consumo deve ser superior ao Consumo M�nimo da Categoria.');
		   	objMediaBaixoConsumo.focus();
		}else if(parseInt(objPorcentagemMediaBaixoConsumo.value) < 1){
		   	retorno = false;
		   	alert('Percentual do Consumo M�dio do Baixo Consumo deve ser maior ou igual a um.');
		   	objPorcentagemMediaBaixoConsumo.focus();
		}else if(parseInt(objConsumoAlto.value) < parseInt(objConsumoMinimo.value)){
		   	retorno = false;
		   	alert('Consumo de Refer�ncia do Alto Consumo deve ser superior ao Consumo M�nimo da Categoria.');
		   	objConsumoAlto.focus();
		}else if(parseInt(objConsumoAlto.value) > parseInt(objConsumoEstouro.value)){
		   	retorno = false;
		   	alert('Consumo de Refer�ncia do Alto Consumo deve ser inferior ao Consumo de Refer�ncia do Estouro do Consumo.');
		   	objConsumoAlto.focus();
		}else if(parseInt(objVezesMediaAltoConsumo.value) < 1){
		   	retorno = false;
		   	alert('Fator de Multiplica��o do Consumo M�dio do Alto Consumo deve ser maior ou igual a um.');
		   	objVezesMediaAltoConsumo.focus();
		}else if(parseFloat(objVezesMediaAltoConsumo.value.replace(",", ".")) > parseFloat(objVezesMediaEstouro.value.replace(",", "."))){
		   	retorno = false;
		   	alert('Fator de Multiplica��o do Consumo M�dio do Alto Consumo deve ser inferior ao Fator de Multiplica��o do Consumo M�dio do Estouro do Consumo.');
		   	objVezesMediaAltoConsumo.focus();
		}
		if( retorno == true ){
			submeterFormPadrao(form);
		}	
	}	
}
-->
</script>
</head>

<body leftmargin="5" topmargin="5">
<html:form action="/inserirCategoriaAction.do"
	name="CategoriaActionForm"
	type="gcom.gui.cadastro.imovel.CategoriaActionForm" method="post"
	onsubmit="return validateCategoriaActionForm(this);">

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
		<td width="625" valign="top" class="centercoltext">
	        <table height="100%">
		        <tr><td></td></tr>
	      	</table>
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	        	<tr>
		          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
		          <td class="parabg">Inserir Categoria</td>
		          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
	    	    </tr>
		    </table>
			<!-- In�cio do Corpo - Roberta Costa -->
			<table align="right">
			<tr>
					<logic:present scope="application" name="urlHelp">
								<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroCategoriaInserir', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
								<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
			</tr>		
			</table>		
			<table width="100%" border="0">
				
				<tr>
					<td colspan="2">Para adicionar a categoria, informe os dados abaixo:</td>
				
				</tr>
			<tr>
				<td>
					&nbsp;
				</td>
			</tr>
				<tr>
					<td width="50%">
						<strong>Descri&ccedil;&atilde;o da Categoria:<font color="#FF0000">*</font></strong>
					</td>
					<td align="right">
						<div align="left"><input name="descricao" type="text" size="15" maxlength="15"></div>
					</td>
				</tr>
				<tr>
					<td>
						<strong>Descri&ccedil;&atilde;o Abreviada da Categoria:<font color="#FF0000">*</font></strong>
					</td>
					<td align="right">
						<div align="left"><input name="descricaoAbreviada" type="text" size="3" maxlength="3"></div>
					</td>
				</tr>
				<tr>
					<td>
						<strong>Tipo da Categoria:<font color="#FF0000">*</font></strong>
					</td>
					<td>
						<html:select property="tipoCategoria" tabindex="3">
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoTipoCategoria" labelProperty="descricao" property="id" />
	                   	</html:select>
	                </td>
				</tr>
				<tr>
					<td>
						<strong>Consumo M&iacute;nimo da Categoria:<font color="#FF0000">*</font></strong>
					</td>
					<td align="right">
						<div align="left"><input name="consumoMinimo" type="text" size="3" maxlength="3"></div>
					</td>
				</tr>
				<tr>
					<td colspan="2"><br>
						<table width="100%" align="center" bgcolor="#99CCFF" border="0">
							<tr>
								<td align="center"><strong>Dados para Determina��o do Estouro do Consumo</strong></td>
							</tr>
							<tr bgcolor="#cbe5fe">
								<td width="100%" align="center">
									<table width="100%" border="0">
										<tr> 
											<td width="50%">
												<strong>Consumo de Refer&ecirc;ncia:<font color="#FF0000">*</font></strong>
											</td>
											<td>
												<input name="consumoEstouro" type="text" size="4" maxlength="4">&nbsp;m<sup>3</sup>
											</td>
										</tr>
										<tr> 
											<td><strong>Fator de Multiplica&ccedil;&atilde;o do Consumo M&eacute;dio:<font color="#FF0000">*</font></strong></td>
											<td>
												<input name="vezesMediaEstouro" type="text" size="4" maxlength="4" 
													onKeyup="formataValorMonetario(this, 3);" style="text-align: right;">
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2"><br>
						<table width="100%" align="center" bgcolor="#99CCFF" border="0">
							<tr>
								<td align="center"><strong>Dados para Determina&ccedil;&atilde;o do Baixo Consumo</strong></td>
							</tr>
							<tr bgcolor="#cbe5fe">
								<td width="100%" align="center">
									<table width="100%" border="0">
										<tr> 
											<td width="50%">
												<strong>Consumo M&eacute;dio de Refer&ecirc;ncia:<font color="#FF0000">*</font></strong>
											</td>
											<td>
												<input name="mediaBaixoConsumo" type="text" size="4" maxlength="4">&nbsp;m<sup>3</sup>
											</td>
										</tr>
										<tr> 
											<td><strong>Percentual do Consumo M&eacute;dio:<font color="#FF0000">*</font></strong></td>
											<td>
												<input name="porcentagemMediaBaixoConsumo" type="text" size="6" maxlength="6" 
													onKeyup="formataValorMonetario(this, 5);" style="text-align: right;">&nbsp;%
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2"><br>
						<table width="100%" align="center" bgcolor="#99CCFF" border="0">
							<tr>
								<td align="center"><strong>Dados para Determina&ccedil;&atilde;o do Alto Consumo</strong></td>
							</tr>
							<tr bgcolor="#cbe5fe">
								<td width="100%" align="center">
									<table width="100%" border="0">
										<tr> 
											<td width="50%">
												<strong>Consumo de Refer&ecirc;ncia:<font color="#FF0000">*</font></strong>
											</td>
											<td>
												<input name="consumoAlto" type="text" size="4" maxlength="4">&nbsp;m<sup>3</sup>
											</td>
										</tr>
										<tr> 
											<td><strong>Fator de Multiplica&ccedil;&atilde;o do Consumo M&eacute;dio:<font color="#FF0000">*</font></strong></td>
											<td>
												<input name="vezesMediaAltoConsumo" type="text" size="4" maxlength="4" onKeyup="formataValorMonetario(this, 3);" style="text-align: right;">
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td><strong><font color="#FF0000"></font></strong></td>
					<td align="right">
						<div align="left"><strong> <font color="#FF0000">*</font></strong>Campos obrigat&oacute;rios</div>
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" name="Button" class="bottonRightCol" value="Desfazer"
							onClick="javascript:window.location.href='/gsan/exibirInserirCategoriaAction.do?menu=sim'">
						<input type="button" name="Button" class="bottonRightCol" value="Cancelar"
							onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"/>
					</td>
					<td align="right">
						<gsan:controleAcessoBotao name="Button" value="Inserir"
							  onclick="javascript:validarForm(document.forms[0]);" url="inserirCategoriaAction.do"/>
						<!-- <input type="button" name="botaoInserir" class="bottonRightCol" value="Inserir" 
							onClick="javascript:validarForm(document.forms[0])"/> --> 
					</td>
				</tr>
			</table>

			<p>&nbsp;</p>
			<!-- Fim do Corpo - Roberta Costa -->
		</td>
	</tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>
