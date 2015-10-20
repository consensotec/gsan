<%@page import="gcom.util.ConstantesSistema"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script src="<bean:message key="caminho.js"/>jquery/jquery.js"></script>
<script src="<bean:message key="caminho.js"/>util.js"></script>

<script>
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg, objetoRelacionado) {
		if(objetoRelacionado.disabled) return;

		if (objeto == null || codigoObjeto == null) {
			abrirPopup(url + "?tipo=" + tipo, altura, largura);
			return;
		}

		if (codigoObjeto.length < 1 || isNaN(codigoObjeto)) {
			alert(msg);
		} else {
			abrirPopup(url + "?menu=sim&tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
		}
	}

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    var form = document.forms[0];

	    if (tipoConsulta == 'funcionalidade') {
	      form.idFuncionalidade.value = codigoRegistro;
	      form.submit();
	    }
	}
	
	function validarForm() {
		var form = document.forms[0];

		var idFuncionalidade = form.idFuncionalidade.value.trim();
		var caminhoUrlHelp = form.caminhoUrlHelp.value.trim();

		if(!idFuncionalidade) {
			alert('Informe Código da Funcionalidade');
			return;
		}

		if(!/^\d+$/.test(idFuncionalidade)){
			alert('Código da Funcionalidade Inválido');
			return;
		}

		if(!caminhoUrlHelp) {
			alert('Informe o Caminho');
			return;
		}

	    form.action='informarPaginaAjudaFuncionalidadeAction.do';
		form.submit();
	}

	function desfazer() {
		var form = document.forms[0];

	    form.action='exibirInformarPaginaAjudaFuncionalidadeAction.do?menu=sim';
	    form.submit();
	}

	function limparFuncionalidade() {
		var form = document.forms[0];

		form.idFuncionalidade.value = "";
		form.descricaoFuncionalidade.value = "";
		form.caminhoUrlHelp.value = "";
		setarFoco('idFuncionalidade');
	}

	function limparDescricao() {
		var form = document.forms[0];

		form.descricaoFuncionalidade.value = "";
		form.caminhoUrlHelp.value = "";
	}

	$(function() {
		var $testeUrl = $('#testeUrl');

		$("input[name='caminhoUrlHelp']").keyup(function(a) {
			var sufixo = a.target.value;
			if(!sufixo || !sufixo.trim()) {
				$testeUrl.css("visibility", "hidden")
				return;
			}

			$testeUrl.css("visibility", "visible")
			$testeUrl.attr('href', '<%= ConstantesSistema.URL_HELP %>?' + sufixo.trim().toLowerCase());
		});

		$("input[name='caminhoUrlHelp']").keyup();
	});
</script>

</head>

<body leftmargin="5" topmargin="5">

<html:form action="/exibirInformarPaginaAjudaFuncionalidadeAction"
	name="InformarPaginaAjudaFuncionalidadeActionForm"
	type="gcom.gui.seguranca.InformarPaginaAjudaFuncionalidadeActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
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

			<%@ include file="/jsp/util/mensagens.jsp"%>

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
			</div>
			</td>
			<td width="600" valign="top" bgcolor="#003399" class="centercoltext">
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
					<td class="parabg">Informar Página de Ajuda de Funcionalidade</td>

					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				<tr>
					<td height="5" colspan="3"></td>
				</tr>
			</table>

			<table width="100%" border="0">
				<tr>
					<td colspan="3">
					<p>&nbsp;</p>
					<p>Para informar a página de ajuda de funcionalidade, preecha os campos abaixo:</p>
					</td>
				</tr>

				<tr>
					<td><strong>Funcionalidade:</strong></td>
					<td colspan="2">
						<html:text property="idFuncionalidade" size="9"
							maxlength="9" tabindex="1"
							onkeyup="javascript:limparDescricao();"
							onkeypress="return validaEnter(event, 'exibirInformarPaginaAjudaFuncionalidadeAction.do', 'idFuncionalidade');" />
	
						<a href="javascript:chamarPopup('exibirPesquisarFuncionalidadeAction.do', '', null, null, 420, 800, '','');">
							<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Funcionalidade"/></a>

						<html:text property="descricaoFuncionalidade"
							size="45" maxlength="45" readonly="true"
							style="background-color:#EFEFEF; border:0;
								color: ${funcionalidadeNaoEncontrada == 'exception' ? '#ff0000' : '#000000' }" />
	
						<a href="javascript:limparFuncionalidade();">
							<img border="0" title="Apagar" src="<bean:message key='caminho.imagens'/>limparcampo.gif"/></a>
					</td>
				</tr>
				
				<tr>
					<td><strong>Caminho:</strong></td>
					<td colspan="2">
						<html:text property="caminhoUrlHelp" size="60" maxlength="200" tabindex="2" style="text-transform: lowercase;"/>
					</td>
				</tr>

				<tr>
					<td></td>
					<td colspan="2">
						<p><strong><small>Prefixo: <%= ConstantesSistema.URL_HELP %></small></strong></p>
						<p><strong><small>Exemplo: id=ajuda:manter_imovel</small></strong></p>
					</td>
				</tr>

				<tr>
					<td></td>
					<td colspan="2">
						<p><strong><small><a target="_blank" href="http://www.google.com" id="testeUrl">Testar URL</a></small></strong></p>
					</td>
				</tr>
						
			</table>

			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td>
					  <input name="button" type="button" class="bottonRightCol" value="Desfazer" onclick="desfazer();">
					  &nbsp;
					  <input name="button" type="button" class="bottonRightCol" value="Cancelar" onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>
					<td align="right">
						<input type="button" name="Button" class="bottonRightCol" value="Concluir" onclick="validarForm();" tabindex="13" />
					</td>
				</tr>
			</table>
			
		   </td>
	  </tr>
 </table>

<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
