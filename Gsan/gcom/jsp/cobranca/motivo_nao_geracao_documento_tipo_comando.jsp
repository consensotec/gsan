<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript">
<!-- Begin
function selecionar(){

		var formulario = document.forms[0]; 
	    var i;
    	for (i=0;i<formulario.tipoComando.length;i++){
       		if (formulario.tipoComando[i].checked)
	          break; 
	    }
    	if(formulario.tipoComando[i].value == 'Cronograma'){
    		formulario.action =  '/gsan/exibirMotivoNaoGeracaoDocumentoTipoComandoCronogramaAction.do?menu=sim'
    		formulario.submit();
		}else if(formulario.tipoComando[i].value == 'Eventual'){
    		formulario.action =  '/gsan/exibirMotivoNaoGeracaoDocumentoTipoComandoEventualAction.do?menu=sim'
    		formulario.submit();
		}
}

/*function cancelar(){
		var formulario = document.forms[0]; 
   		formulario.action =  '/gsan/telaPrincipal.do'
   		formulario.submit();
}*/
-->
</script>
</head>

<body leftmargin="5" topmargin="5">
<form><%@ include file="/jsp/util/cabecalho.jsp"%> <%@ include
	file="/jsp/util/menu.jsp"%>

<table width="770" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="115" valign="top" class="leftcoltext">

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


		<td valign="top" class="centercoltext"><!--In�cio Tabela Reference a P�gina��o da Tela de Processo-->
		<table height="100%">
			<tr>
				<td></td>
			</tr>
		</table>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
				<td class="parabg">Consultar Motivo da n&atilde;o Gera&ccedil;&atilde;o
				 de Documento de Cobran&ccedil;a</td>
				<td width="11" valign="top"><img border="0"
					src="imagens/parahead_right.gif" /></td>
			</tr>
		</table>
		
		<p>&nbsp;</p>
		<table width="100%" border="0" dwcopytype="CopyTableRow">
			<tr>
				<td colspan="3">Informe o tipo do comando:</td>
			</tr>
			<tr>
				<td width="18%" colspan="3"><strong>Tipo do Comando:<font color="#FF0000">*</font></strong>
				<strong> <html:radio property="tipoComando"
					name="MotivoNaoGeracaoDocumentoActionForm" value="Cronograma" /> Cronograma </strong>
				<strong> <html:radio property="tipoComando"
					name="MotivoNaoGeracaoDocumentoActionForm" value="Eventual" /> Eventual</strong></td>
			</tr>
			<tr>
				<td colspan="3">&nbsp;</td>
			</tr>
			<tr>
				<td height="17" colspan="3"><strong><font color="#FF0000"> </font></strong><strong><font
					color="#FF0000"> </font></strong><strong><font color="#FF0000"> </font></strong></td>
			</tr>
			<tr>
				<td height="17" colspan="3"><strong><font color="#FF0000"> </font></strong><strong><font
					color="#FF0000"> </font></strong><strong><font color="#FF0000"> </font></strong></td>
			</tr>
			<tr>
				<td height="17" colspan="3"><strong><font color="#FF0000"> </font></strong><strong><font
					color="#FF0000"> </font></strong><strong><font color="#FF0000"> </font></strong></td>
			</tr>
			<tr>
				<td align="left">&nbsp;</td>
				<td>
				<table border="0" align="right">
					<tr>
						<td align="right"><input name="Button" type="button" class="bottonRightCol"
								value="Cancelar" align="left"
								onClick="window.location.href='/gsan/telaPrincipal.do'">
						</td>
						<td align="right"><input name="Button3222" type="button"
							class="bottonRightCol" value="Avan�ar" onClick="selecionar();" />
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
<%@ include file="/jsp/util/rodape.jsp"%></form>
</body>
</html:html>
