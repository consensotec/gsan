<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="gsan.util.ConstantesSistema"%>

<html:html>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="FiltrarCobrancaSituacaoActionForm" dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

	function validarForm(formulario){	
			if(validateFiltrarCobrancaSituacaoActionForm(formulario)){    		
	    		if(validateInteger(formulario)){	     
		  			submeterFormPadrao(formulario);
		  		}
		}
	}
	
	function IntegerValidations () {
		this.aa = new Array("id", "O campo C�digo deve conter apenas n�meros.", new Function ("varName", " return this[varName];"));
	}

    function verificarChecado(valor){
		form = document.forms[0];
		if(valor == "1"){
		 	form.indicadorAtualizar.checked = true;
		 }else{
		 	form.indicadorAtualizar.checked = false;
		}
	}
	
</script>

</head>

<body leftmargin="5" topmargin="5">
<html:form action="/filtrarCobrancaSituacaoAction"
	name="FiltrarCobrancaSituacaoActionForm"
	type="gsan.gui.cobranca.FiltrarCobrancaSituacaoActionForm"
	method="post"
	onsubmit="return validateFiltrarCobrancaSituacaoActionForm(this);">


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
						<td class="parabg">Filtrar Situa��o de Cobran�a</td>
						<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
					</tr>
				</table>
				<p>&nbsp;</p>		
				<table width="100%" border="0">
				<tr>

						<td colspan="2">Para filtrar a(s) situa��o(�es) de cobran�a, informe o dado abaixo:</td>
						<td width="100" align="left" colspan="2"><html:checkbox
						property="indicadorAtualizar" value="1" /><strong>Atualizar</strong></td>
				</tr>
					
				<tr>
					<td><strong>C&oacute;digo:</strong></td>
					<td><html:text property="id" size="5" maxlength="5"  onkeypress="return isCampoNumerico(event);" /><font
						size="1">&nbsp;</font> <br>
					<font color="red"><html:errors property="id" /></font></td>
				</tr>
				
				<tr>
					<td width="70%"><strong>Descri&ccedil;&atilde;o:</strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="descricao" size="50" maxlength="50" /> </span></td>
			   		<td width="15%">&nbsp;</td>
			   </tr>
				<tr>
					<td>&nbsp;</td>
					<td><html:radio property="tipoPesquisa" tabindex="5"
						value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
					Iniciando pelo texto <html:radio property="tipoPesquisa"
						tabindex="6"
						value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
					Contendo o texto</td>
					<td>&nbsp;</td>
				</tr>
			
				<tr>
					<td><strong>Motivo de Revis�o da Conta:</strong></td>
					<td><html:select property="contaMotivoRevisao">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoContaMotivoRevisao"
							labelProperty="descricaoMotivoRevisaoConta" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>	   

				<tr>
					<td><strong>Profiss�o:</strong></td>
					<td><html:select property="profissao">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoProfissao"
							labelProperty="descricao" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>
				
				<tr>
					<td><strong>Ramo de Atividade:</strong></td>
					<td><html:select property="ramoAtividade">
						<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						<html:options collection="colecaoRamoAtividade"
							labelProperty="descricao" property="id" />
					</html:select> <font size="1">&nbsp; </font></td>
				</tr>
				
					<tr>
					<td><strong>Indicador de Exig�ncia de Advogado:</strong></td>
					<td><html:radio property="indicadorExigenciaAdvogado" value="1" /><strong>Sim</strong>
					<html:radio property="indicadorExigenciaAdvogado"  value="2" /><strong>N�o</strong>
					</td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Bloqueio de Parcelamento:</strong></td>
					<td><html:radio property="indicadorBloqueioParcelamento" value="1" /><strong>Sim</strong>
					<html:radio property="indicadorBloqueioParcelamento" value="2" /><strong>N�o</strong>
					</td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Bloqueio de Inclus�o de Im�vel na Situa��o:</strong></td>
					<td><html:radio property="indicadorBloqueioRetirada"  value="1" /><strong>Sim</strong>
					<html:radio property="indicadorBloqueioRetirada" value="2" /><strong>N�o</strong>
					</td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Bloqueio de Retirada de Im�vel da Situa��o:</strong></td>
					<td><html:radio property="indicadorBloqueioInclusao"  value="1" /><strong>Sim</strong>
					<html:radio property="indicadorBloqueioInclusao"  value="2" /><strong>N�o</strong>
					</td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Sele��o da Situa��o Apenas pelos Usu�rios com Permiss�o Especial :</strong></td>
					<td><html:radio property="indicadorSelecaoApenasComPermissao" value="1" /><strong>Sim</strong>
					<html:radio property="indicadorSelecaoApenasComPermissao"  value="2" /><strong>N�o</strong>
					</td>
					<td>&nbsp;</td>
				</tr>	
				
				<tr>
					<td><strong>Indicador de Prescri��o para Im�veis Particulares :</strong></td>
					<td><html:radio property="indicadorPrescricaoImoveisParticulares" value="1" /><strong>Sim</strong>
					<html:radio property="indicadorPrescricaoImoveisParticulares"  value="2" /><strong>N�o</strong>
					</td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Bloquear Altera��o de Vencimento da Conta :</strong></td>
					<td><html:radio property="indicadorAlterarVencimentoConta" value="1" /><strong>Sim</strong>
						<html:radio property="indicadorAlterarVencimentoConta" value="2" /><strong>N�o</strong>
					</td>
					<td>&nbsp;</td>				
				</tr>	
				
				
				

			    <tr>
					<td><strong>Indicador de Uso:</strong></td>
					<td><html:radio property="indicadorUso" tabindex="2" value="1"><strong>Ativo</strong></html:radio>
					<html:radio property="indicadorUso" tabindex="3" value="2" ><strong>Inativo</strong></html:radio>
					<html:radio property="indicadorUso" tabindex="8" value="" /><strong>Todos</strong>
					</td>
					<td>&nbsp;</td>
				</tr>
				
							
				<tr>
					<td><input name="Button" type="button" class="bottonRightCol"
						value="Limpar" align="left"
						onclick="window.location.href='/gsan/exibirFiltrarCobrancaSituacaoAction.do?menu=sim'"
						tabindex="8"></td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td width="65" align="right"><input name="Button2" type="button"
						class="bottonRightCol" value="Filtrar" align="right"
						onClick="javascript:validarForm(document.forms[0]);" tabindex="9"/></td>
				</tr>	
	
				</table>
			</td>
		</tr>
</table>
	
			
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
