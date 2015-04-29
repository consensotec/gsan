<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="GerarRelatorioResumoDevedoresDuvidososActionForm" />
<script>

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	
	    var form = document.GerarRelatorioResumoDevedoresDuvidososActionForm;
	
	    if (tipoConsulta == 'localidade') {
	      form.codigoLocalidade.value = codigoRegistro;
	      form.descricaoLocalidade.value = descricaoRegistro;
	      form.descricaoLocalidade.style.color = "#000000";
	
	    }
	}

	function limparLocalidade() {
		var form = document.GerarRelatorioResumoDevedoresDuvidososActionForm;
	    
	    form.codigoLocalidade.value = '';
	    form.descricaoLocalidade.value = '';
	}

	function validarForm(){
	
		var form = document.GerarRelatorioResumoDevedoresDuvidososActionForm; 
	
		    if (validateGerarRelatorioResumoDevedoresDuvidososActionForm(form) && verificaAnoMes(form.mesAno)){
				 
				 var opcao = '';
				
				 for(i=0; i < form.opcaoTotalizacao.length; i++) {
				 	if (form.opcaoTotalizacao[i].checked) {
				 		opcao = form.opcaoTotalizacao[i].value;
				 	}
				 }
				 
	      		if (opcao == 'localidade') {
	      			if (form.codigoLocalidade.value == '') {
	      				alert('Preencha o c�digo da localidade');
	      				return false;
	      			}
	      		} else if (opcao == '') {
	      			alert('Preencha a op��o de totaliza��o');
	      			return false;
	      		}
				          
				form.submit();			     
	
	  		}
		  
	}

	function limparCampos(opcaoTotalizacao){
		var form = document.forms[0];
		
		if (opcaoTotalizacao.value != 'localidade'){
			limparLocalidade() ;
		}
		
		if (opcaoTotalizacao.value != 'gerenciaRegional'){
			form.gerenciaRegionalId.value = "-1";
		}
		
		if (opcaoTotalizacao.value != 'gerenciaRegionalLocalidade'){
			form.gerenciaRegionalporLocalidadeId.value = "-1" ;
		}
		
		if (opcaoTotalizacao.value != 'unidadeNegocio'){
			form.unidadeNegocioId.value = "-1" ;
		}
	}

</script>


</head>

<body leftmargin="5" topmargin="5">
<div id="formDiv">
<html:form action="/gerarRelatorioResumoDevedoresDuvidososAction.do"
	name="GerarRelatorioResumoDevedoresDuvidososActionForm"
	type="gsan.gui.relatorio.financeiro.GerarRelatorioResumoDevedoresDuvidososActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellpadding="0" cellspacing="5">
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
			<td valign="top" class="centercoltext">
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
					<td class="parabg">Rel�torio Resumo de Devedores Duvidosos</td>
					<td width="11" valign="top"><img
						src="<bean:message key="caminho.imagens"/>parahead_right.gif"
						border="0" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="3">Para gerar o relat&oacute;rio resumo de
					devedores duvidosos, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td width="26%"><strong>M�s/Ano de Refer�ncia Contabil:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="mesAno" size="7" maxlength="7" onkeyup="mascaraAnoMes(this, event);somente_numero(this);"/>
					<strong>&nbsp; </strong>mm/aaaa</td>
				</tr>
				
				<tr>
					<td><strong>Tipo de Perda:<font color="#FF0000">*</font></strong></td>
					<td colspan="2">
						<html:select property="tipoPerda">
							<html:option value="">&nbsp;</html:option>
							<html:options collection="colecaoTipoPerda" labelProperty="descricao" property="id" />
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td><strong>Op&ccedil;&atilde;o de Totaliza&ccedil;&atilde;o:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="2" align="left"><html:radio
						property="opcaoTotalizacao" value="estado" onclick = "limparCampos(this);"/> <strong>Estado </strong></td>
				</tr>
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td colspan="2" align="left"><strong> <html:radio
						property="opcaoTotalizacao" value="estadoGerencia" onclick = "limparCampos(this);"/> Estado por
					Ger&ecirc;ncia Regional </strong></td>
				</tr>
				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td colspan="2" align="left"><strong> 
						<html:radio property="opcaoTotalizacao" value="estadoUnidadeNegocio" 
						onclick = "limparCampos(this);"/>
					<strong>Estado por Unidade de Neg�cio</strong></strong></td>
				</tr>
				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td colspan="2" align="left"><strong> <html:radio
						property="opcaoTotalizacao" value="estadoLocalidade" onclick = "limparCampos(this);"/> <strong>Estado</strong>
					por Localidade</strong></td>
				</tr>
				
				
				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td width="36%" align="left"><strong> <html:radio
						property="opcaoTotalizacao" value="gerenciaRegional" onclick = "limparCampos(this);"/> <strong>Ger&ecirc;ncia
					Regional </strong></strong></td>
					<td width="38%" align="left"><strong><strong> <html:select
						property="gerenciaRegionalId">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoGerenciaRegional"
							labelProperty="nome" property="id" />
					</html:select> </strong></strong></td>
				</tr>
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="left"><strong> <html:radio property="opcaoTotalizacao"
						value="gerenciaRegionalLocalidade" onclick = "limparCampos(this);"/> <strong>Ger&ecirc;ncia
					Regional</strong> por Localidade</strong></td>
					<td align="left"><strong><strong> <strong> <html:select
						property="gerenciaRegionalporLocalidadeId">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoGerenciaRegional"
							labelProperty="nome" property="id" />
					</html:select> </strong> </strong></strong></td>
				</tr>
				
						<tr>
						<td><strong> <font color="#FF0000"></font></strong></td>
						<td width="36%" align="left"><strong> 
							<html:radio property="opcaoTotalizacao" value="unidadeNegocio" 
							onclick = "limparCampos(this);"/>
						<strong>Unidade de Neg�cio </strong></strong></td>
						<td width="38%" align="left"><strong><strong> 
							<html:select property="unidadeNegocioId">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoUnidadeNegocio" labelProperty="nome" property="id" />
						</html:select> </strong></strong></td>
					</tr>
					
				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="left"><strong> <html:radio property="opcaoTotalizacao"
						value="localidade" onclick = "limparCampos(this);" /> Localidade</strong></td>
					<td align="left"><html:text
						value="${requestScope.codigoLocalidade}"
						property="codigoLocalidade" size="3" maxlength="3"
						onkeypress="validaEnter(event, 'exibirGerarRelatorioResumoDevedoresDuvidososAction.do', 'codigoLocalidade');" />
					<img src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						width="23" height="21" border="0"
						onclick="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do', 400, 800);">
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						width="23" height="21" onclick="javascript:limparLocalidade();"></td>
				</tr>
				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="left"><strong> </strong></td>
					<td align="left"><strong> 
					
						<input type="text" name="descricaoLocalidade" readonly
							style="background-color:#EFEFEF; border:0" size="30"
							maxlength="30" value="${requestScope.descricaoLocalidade}" />
							
					<c:if test="${empty requestScope.codigoLocalidade}" var="testeCor">
						<script>
							document.GerarRelatorioResumoDevedoresDuvidososActionForm.descricaoLocalidade.style.color = '#FF0000';
						</script>
					</c:if>
					
					<c:if test="${!testeCor}">
						<script>
							document.GerarRelatorioResumoDevedoresDuvidososActionForm.descricaoLocalidade.style.color = '#000000';
						</script>
					</c:if>
							

					 </strong></td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td colspan="2" align="left"><font color="#FF0000">*</font> Campo
					Obrigat&oacute;rio</td>
				</tr>
				<tr>
					<td>
					<p>&nbsp;</p>
					</td>
					<td colspan="2"></td>
				</tr>
			</table>
			<table width="100%">
				<tr>
					<td width="100%">
					<table width="100%" border="0" align="right" cellpadding="0"
						cellspacing="2">
						<tr>
							<td width="40%" align="left">
								<input type="button"name="ButtonCancelar" class="bottonRightCol" value="Cancelar" 
									onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
								<input type="button" name="ButtonReset" class="bottonRightCol" value="Limpar" 
									onclick="window.location.href='/gsan/exibirGerarRelatorioResumoDevedoresDuvidososAction.do';">
							</td>
							
							<td align="right">							
								<input type="button" class="bottonRightCol"
									value="Gerar Relat&oacute;rio"
									onclick="toggleBox('demodiv',1);">
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
	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio_tela_espera.jsp?relatorio=gerarRelatorioResumoDevedoresDuvidososAction.do"/> 
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</div>
<%@ include file="/jsp/util/telaespera.jsp"%>
</body>
</html:html>
