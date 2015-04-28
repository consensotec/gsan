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
		if(form.indicadorUso[2].checked==true
				&& form.descricao.value=="" 
					&& form.descricaoAbreviada.value==""
						&& form.tipoOcorrencia.value=="-1"){
			alert("Informe pelo menos uma opção de seleção");
		}else{
			if(form.indicadorAtualizar.checked==true){
				form.action = "filtrarMotivoOcorrenciaOperacionalAction.do?opcaoAtualizar=1";
				form.submit();
			}else{
				form.action = "filtrarMotivoOcorrenciaOperacionalAction.do?opcaoAtualizar=2";
				form.submit();
			}
		}
	}
	function limparCampos(){
		var form = document.forms[0];
		form.descricao.value = "";
		form.descricaoAbreviada.value = "";
		form.tipoOcorrencia.value = "-1";
		form.indicadorUso[2].checked = true;
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
	function onload(atualizar,uso){
		var form = document.forms[0];
        var atualizar2 = atualizar;
		var uso2 = uso;
		if(atualizar2==1){
			form.indicadorAtualizar.checked = true;
		}else if(atualizar2==2){
			form.indicadorAtualizar.checked = false;
		}

		if(uso2==1){
			form.indicadorUso[2].checked = true;
		}
	}
</script>

</head>
<body leftmargin="5" topmargin="5" onload="onload(${atualizar},${uso});">
<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
<div id="formDiv">
<html:form action="/filtrarMotivoOcorrenciaOperacionalAction"
	name="FiltrarMotivoOcorrenciaOperacionalActionForm"
	type="gsan.gui.atendimentopublico.registroatendimento.FiltrarMotivoOcorrenciaOperacionalActionForm">
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
						<td class="parabg">Filtrar Motivo Ocorrência Operacional</td>
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
					</tr>
				</table>
				<p>&nbsp;</p>
				<table width="100%" border="0">
					<tr>
						<td colspan="2">Para filtrar o motivo ocorrência operacional, informe os dados abaixo:
					</td>
					<td width="100" align="left" colspan="2"><html:checkbox
						property="indicadorAtualizar" value="1"/><strong>Atualizar</strong></td>
					</tr>
					<tr>
						<td width="25%"><strong>Descrição:</strong></td>
						<td>
							<html:text property="descricao" maxlength="50" size="50" onkeypress="return validaCompoNumericoCaracteresEspeciais(event);"></html:text>
						</td>
						
					</tr>
					<tr>
						<td width="25%"><strong>Descrição Abreviada:</strong></td>
						<td>
							<html:text property="descricaoAbreviada" maxlength="10" size="10" onkeypress="return validaCompoNumericoCaracteresEspeciais(event);"></html:text>
						</td>
					</tr>
					<tr>
						<td width="25%"><strong>Tipo da Ocorrência:</strong></td>
						<td>
							<html:select property="tipoOcorrencia" style="width: 230px;">
								<html:option
									value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
								</html:option>
								<logic:present name="colecaoOcorrenciaOperacionalTipo" scope="request">
									<html:options collection="colecaoOcorrenciaOperacionalTipo"
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
							<strong><html:radio property="indicadorUso" value="3"> </html:radio>Todos</strong>
						</td>
					</tr>
					<tr>
						<td colspan="3" height="180px">&nbsp;</td>
					</tr>
				</table>
				<table width="100%" border="0">
					<tr>
						<td align="left">
							<input type="button" value="Limpar" class="bottonRightCol" onclick="limparCampos();">
						</td>
						<td align="right">
							<input type="button" value="Filtrar" class="bottonRightCol" onclick="validarForm();">
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