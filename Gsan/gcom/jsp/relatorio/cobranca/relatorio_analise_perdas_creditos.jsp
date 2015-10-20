<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@page isELIgnored="false"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/telaespera.jsp"%>
<%@ include file="/jsp/util/titulo.jsp"%>
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="GerarRelatorioAnalisePerdasCreditosActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js">
</script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
	
	
	
	<script language="JavaScript">
	
		function apertouCancelar(){
		    document.forms[0].reset();
			window.location.href = "/gsan/telaPrincipal.do";
		}
		
		function validarForm(){
			var form = document.forms[0];
			
			if(validateGerarRelatorioAnalisePerdasCreditosActionForm(form)){
				toggleBox('demodiv',1);
				form.action = form.action + "&mesAno="+mesAno;
			}
					
		}
		
		function verificaData(event){
			toggleBox('demodiv',0);
			
          	var char = null;
          		if (event.which == null){
				     char= String.fromCharCode(event.keyCode);    // IE
				}else if (event.which != 0 && event.charCode != 0){
				     char= String.fromCharCode(event.which);
				 }   
				 
				if(char != '/'){
					return isCampoNumerico(event);
				}
			
          }
		function limparForm(){
			var form = document.GerarRelatorioAnalisePerdasCreditosActionForm;
			
			form.mesAno.value = "";
		}
          
	</script>
</head>

<body leftmargin="5" topmargin="5">
<div id="formDiv">
<html:form action="/exibirRelatorioAnalisePerdasCreditoAction"
	name="GerarRelatorioAnalisePerdasCreditosActionForm"
	type="gcom.gui.relatorio.cobranca.GerarRelatorioAnalisePerdasCreditosActionForm"
	method="post" onsubmit="return validateGerarRelatorioAnalisePerdasCreditosActionForm(this);">
	
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
			</div>
			</td>


			<td valign="top" class="centercoltext">
					<table>
						<tr>
							<td></td>
						</tr>
					</table>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="11"><img border="0" src="imagens/parahead_left.gif"
								alt="" /></td>
							<td class="parabg">Gerar Relatório de Análise de Perdas com Crédito</td>
							<td width="11" valign="top"><img border="0"
								src="imagens/parahead_right.gif" alt="" /></td>
						</tr>
					</table>
					
					<table width="100%" border="0">
						<tr>
							<td colspan="2">Para gerar o relatório informe os dados abaixo:</td>
						</tr>
						
						<tr>
							<td width="150"><strong>Mês/Ano de Referência:<font color="#FF0000">*</font></strong></td>
							<td colspan="2"><html:text onkeyup="mascaraAnoMes(this, event);"
							onkeypress="return isCampoNumerico(event);" 
							property="mesAno" size="7"  maxlength="7" /> mm/aaaa</td>
						</tr>
						<tr>
							<td><strong> <font color="#FF0000"></font></strong></td>
							<td align="right">&nbsp;
							<div align="left"><strong><font color="#FF0000">*</font></strong>
							Campo obrigat&oacute;rio</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
							<div align="left">
								<input type="button" name="cancelar" onclick="javascritp: apertouCancelar();"
										class="bottonRightCol" value="Cancelar">
								<input type="button" name="ButtonReset" class="bottonRightCol"
										value="Limpar" onclick="javascript:limparForm();">
							</div>
							</td>
							
							<td>
							<div align="right">
								<input type="button" class="bottonRightCol" onclick="javascript: validarForm();" 
										value="Gerar Resumo" width="28" height="26"/>
							</div>
							</td>
						</tr>
					</table>
	
	</table>
	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio_tela_espera.jsp?relatorio=gerarRelatorioAnalisePerdasCreditoAction.do"/> 
	<%@ include file="/jsp/util/rodape.jsp"%>
		</html:form>
		
		</div>
		
	</body>	
	
</html:html>

