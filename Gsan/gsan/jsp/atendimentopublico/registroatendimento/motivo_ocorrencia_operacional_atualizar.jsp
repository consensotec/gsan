<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ page import="gsan.util.ConstantesSistema"%>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<script type="text/javascript">
	function validarForm(){
		var form = document.forms[0];
		if(form.descricao.value==""){
			alert("Informe a Descri��o");		
		}else{
			if(form.tipoOcorrencia.value=="-1"){
				alert("Informe o Tipo de Ocorr�ncia");
			}else{
				form.action = "atualizarMotivoOcorrenciaOperacionalAction.do";
				form.submit();
			}
		}
	}
	function limparCampos(){
		var form = document.forms[0];
		form.descricao.value = "";
		form.descricaoAbreviada.value = "";
		form.tipoOcorrencia.value = "-1";
	}
	function validaCompoNumericoCaracteresEspeciais(evento) {
		
	    var tecla = null;
		
		  if(window.event){ // Internet Explorer
		  		tecla = evento.keyCode;
		  }else if(evento.which){ // Nestcape ou Mozilla
		    	tecla = evento.which;
		  }
		  
		  if(tecla == null){//tab
			  return true;
		  }

		  if (tecla == 8 || tecla == 13 || tecla == 32){ // backspace ou enter
		        return true;
	      }
		  
	      if (tecla>=97 && tecla<=122){ //Letras a-z
	    	  return true;
	      }
	      
	      return false;
	}
	function verificarValorAtualizar() {
		var form = document.forms[0];
		if (form.indicadorAtualizar.checked == true) {
			form.indicadorAtualizar.value = '1';
		} else {
			form.indicadorAtualizar.value = '';
		}
	}
	function desfazer(){
		var form = document.forms[0];
		form.action = 'exibirAtualizarMotivoOcorrenciaOperacionalAction.do?idMotivoOcorrencia='+document.forms[0].idMotivoOcorrenciaOperacional.value;
		form.submit();
	}
	function voltar(){
		window.location.href = 'exibirFiltrarMotivoOcorrenciaOperacionalAction.do?menu=sim';
		
	}
</script>

</head>
<body leftmargin="5" topmargin="5" >
<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
<div id="formDiv">
<html:form action="/atualizarMotivoOcorrenciaOperacionalAction.do"
	name="AtualizarMotivoOcorrenciaOperacionalActionForm"
	type="gsan.gui.atendimentopublico.registroatendimento.AtualizarMotivoOcorrenciaOperacionalActionForm">
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
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
						<td class="parabg">Atualizar Motivo Ocorr�ncia Operacional</td>
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
					</tr>
				</table>
				<p>&nbsp;</p>
				<table width="100%" border="0">
					<tr>
						<td colspan="2">Para atualizar o motivo ocorr�ncia operacional, informe os dados abaixo:
					</td>
					</tr>
					<html:hidden property="idMotivoOcorrenciaOperacional" value="${idMotivoOcorrenciaOperacional}"/>
					<tr>
						<td width="25%"><strong>Descri��o:<font color="red">*</font></strong></td>
						<td>
							<html:text property="descricao" maxlength="50" size="50" onkeydown="mascara(this,mascaraCaracteres)" onblur="mascara(this,mascaraCaracteres)"></html:text>
						</td>
						
					</tr>
					<tr>
						<td width="25%"><strong>Descri��o Abreviada:</strong></td>
						<td>
							<html:text property="descricaoAbreviada" maxlength="10" size="10" onkeydown="mascara(this,mascaraCaracteres)" onblur="mascara(this,mascaraCaracteres)"></html:text>
						</td>
					</tr>
					<tr>
						<td width="25%"><strong>Tipo da Ocorr�ncia:<font color="red">*</font></strong></td>
						<td>
							<html:select property="tipoOcorrencia" style="width: 230px;">
								<html:option
									value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
								</html:option>
								<logic:present name="colecaoOcorrenciaOperacionalTipoGeral" scope="request">
									<html:options collection="colecaoOcorrenciaOperacionalTipoGeral"
										labelProperty="descricao" 
										property="id" />
								</logic:present>
							</html:select>
						</td>
					</tr>
					<tr>
						<td><strong>Indicador de Uso:</strong></td>
						<td>
							<strong><html:radio property="indicadorUso" value="1"></html:radio>Ativo</strong>
							<strong><html:radio property="indicadorUso" value="2"></html:radio>Inativo</strong>
						</td>
					</tr>
					<td colspan="3">&nbsp; </td>
					</tr>
					<tr>
						<td colspan="3" align="center"><strong>Campos Obrigat�rios</strong><font color="red">*</font></td>
					</tr>
					<tr>
						<td colspan="3" height="160px">&nbsp;</td>
					</tr>
				</table>
				<table width="100%" border="0">
					<tr>
						<td align="left">
							<input type="button" value="Voltar" class="bottonRightCol" onclick="voltar();">
							<input type="button" value="Desfazer" class="bottonRightCol" onclick="desfazer();">
							<input type="button" value="Cancelar" class="bottonRightCol" onclick="window.location.href='/gsan/telaPrincipal.do'">
						</td>
						<td align="right">
							<input type="button" value="Atualizar" class="bottonRightCol" onclick="validarForm();">
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	</html:form>
	<%@ include file="/jsp/util/rodape.jsp"%>
	</div>
	</body>
</html:html>