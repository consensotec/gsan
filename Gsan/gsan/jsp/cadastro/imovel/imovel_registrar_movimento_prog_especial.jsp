<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gsan.util.ConstantesSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
		<script type="text/javascript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>
		
<html:javascript staticJavascript="false"  formName="CategoriaActionForm"/>

<style type="text/css">

.readonly {
  background-color:#EFEFEF; 
  border:0; 
  color: #000000;
}

</style>

<script type="text/javascript">


	$(document).ready(function() {
		
		var limitsize = 200;
		
		if (!$("input[@name='acao']").is(":checked")){
			$("input[@name='acao']").filter('[value=I]').attr('checked', true);
		}
		
		habilitarDesabilitarMesAnoFat();
		
		limitChars('observacao', limitsize, 'limitinfo');
		$('#observacao').keyup(function(){
			limitChars('observacao', limitsize, 'limitinfo');
		});
		
		
		$("input[@name='acao']").click(function(){
			habilitarDesabilitarMesAnoFat();
		});
		
		
		$("input[@name='cancelarItensFatura']").click(function(){
			 if ($("input[@name='cancelarItensFatura']:checked").val() == '1'){
				 $("input[@name='mesAnoFaturamento']").attr('readonly',false);
				 $("input[@name='mesAnoFaturamento']").removeClass("readonly");
			 }
			 else{
				 if($("input[@name='retirarContasProgEspecial']:checked").val() == '2'){
				 	 $("input[@name='mesAnoFaturamento']").attr('readonly',true);
				 	 $("input[@name='mesAnoFaturamento']").addClass("readonly");
				 	 $("input[@name='mesAnoFaturamento']").val("");
				 }
			 }
				 
		});
		
		$("input[@name='retirarContasProgEspecial']").click(function(){
			 if ($("input[@name='retirarContasProgEspecial']:checked").val() == '1'){
				 $("input[@name='mesAnoFaturamento']").attr('readonly',false);
				 $("input[@name='mesAnoFaturamento']").removeClass("readonly");
			 }
			 else{
				 if($("input[@name='cancelarItensFatura']:checked").val() == '2'){
					 $("input[@name='mesAnoFaturamento']").attr('readonly',true);
				 	 $("input[@name='mesAnoFaturamento']").addClass("readonly");
				 	 $("input[@name='mesAnoFaturamento']").val("");
				 }
			 }
				 
		});
		
	});


	function limitChars(textid, limit, infodiv) {
		var text = $('#' + textid).val(),	
			textlength = text.length;
		
		if(textlength > limit) {
			$('#' + textid).val(text.substr(0, limit));
			return false;
		} else {
			$('#' + infodiv).html(textlength + '/' + limit);
			return true;
		}
	}
	
	
	function habilitarDesabilitarMesAnoFat(){
		if ($("input[@name='acao']:checked").val() == 'I'){
			 
			 $("input[@name='mesAnoFaturamento']").val("");
			 $("input[@name='mesAnoFaturamento']").addClass("readonly");
			 $("input[@name='mesAnoFaturamento']").attr('readonly',true);
			 $("input[@name='cancelarItensFatura']").addClass("readonly");
			 $("input[@name='cancelarItensFatura']").filter('[value=2]').attr('checked', true);
			 $("input[@name='cancelarItensFatura']").attr('disabled',true);
			 $("input[@name='retirarContasProgEspecial']").addClass("readonly");
			 $("input[@name='retirarContasProgEspecial']").filter('[value=2]').attr('checked', true);
			 $("input[@name='retirarContasProgEspecial']").attr('disabled',true);
			 $("input[@name='retirarSituacaoEspCobranca']").addClass("readonly");
			 $("input[@name='retirarSituacaoEspCobranca']").filter('[value=2]').attr('checked', true);
			 $("input[@name='retirarSituacaoEspCobranca']").attr('disabled',true);
			 
		 }
		 else{
			 if ($("input[@name='cancelarItensFatura']:checked").val() == '1' || 
				 $("input[@name='retirarContasProgEspecial']:checked").val() == '1' ){
				 
					 $("input[@name='mesAnoFaturamento']").attr('readonly',false);
					 $("input[@name='mesAnoFaturamento']").removeClass("readonly");
			 }
			 else{
				 $("input[@name='mesAnoFaturamento']").val("");
				 $("input[@name='mesAnoFaturamento']").addClass("readonly");
				 $("input[@name='mesAnoFaturamento']").attr('readonly',true);
			 }
			 $("input[@name='cancelarItensFatura']").removeClass("readonly");
			 $("input[@name='cancelarItensFatura']").attr('disabled',false);
			 $("input[@name='retirarContasProgEspecial']").removeClass("readonly");
			 $("input[@name='retirarContasProgEspecial']").attr('disabled',false);
			 $("input[@name='retirarSituacaoEspCobranca']").removeClass("readonly");
			 $("input[@name='retirarSituacaoEspCobranca']").attr('disabled',false);
			 
		 }
	} 
	
	function validarForm(form){
		
		$("input[@name='cancelarItensFaturaHidden']").val($("input[@name='cancelarItensFatura']:checked").val());
		$("input[@name='retirarContasProgEspecialHidden']").val($("input[@name='retirarContasProgEspecial']:checked").val());
		$("input[@name='retirarSituacaoEspCobrancaHidden']").val($("input[@name='retirarSituacaoEspCobranca']:checked").val());
		
		form.submit();
	}


</script>
</head>

<body leftmargin="5" topmargin="5">
<html:form action="/registrarMovimentoProgramaEspecialAction.do"
	name="RegistrarMovimentoProgramaEspecialActionForm"
	type="gsan.gui.cadastro.imovel.RegistrarMovimentoProgramaEspecialActionForm"
	method="post"
	enctype="multipart/form-data">

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
		          <td class="parabg">Registrar Movimento do Programa Especial</td>
		          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
	    	    </tr>
		    </table>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para registrar o movimento do programa especial, informe os dados abaixo:</td>	
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
				
				<tr>
					<td width="30%"><strong>Ação:<font color="#FF0000">*</font></strong></td>
					<td width="70%"><strong> <span class="style1"><strong> 
					<html:radio property="acao" value="I" tabindex="4" /> <strong>Inserir 
					<html:radio property="acao" value="S" tabindex="5" /> Suspender
					</strong></strong></span></strong></td>
				</tr>
				
				<tr>
					<td width="30%"><strong>Mês/Ano de Faturamento:<font color="#FF0000" id="divAnoMes" >*</font></strong></td>
					<td width="70%">
						<html:text  maxlength="7"
									property="mesAnoFaturamento" 
									size="7" 
									tabindex="7"
									onkeypress="return isCampoNumerico(event);"
									onkeyup="mascaraAnoMes(this, event);" /> 
									mm/aaaa
					</td>
				</tr>
				
				<tr>
					<td width="30%"><strong>Arquivo de Movimento:<font color="#FF0000">*</font></strong></td>
					<td width="70%">
						<html:file property="arquivoMovimento" size="9"/>
					</td>
				</tr>
				
				
				<tr>
					<td height="30%"><strong>Observação:<font color="#FF0000">*</font></strong></td>
					<td width="70%">
						<html:textarea property="observacao" rows="5" cols="40" styleId="observacao"/>
						<strong style="padding-left: 300px;"><span id="limitinfo"></span></strong>
					</td>
				</tr>
				
				<tr>
					<td width="30%"><strong>Cancelar Itens da Fatura:</strong></td>
					<td width="70%"><strong> <span class="style1"><strong> 
					<html:radio property="cancelarItensFatura" value="1" tabindex="4" /> <strong>Sim 
					<html:radio property="cancelarItensFatura" value="2" tabindex="5" /> Não
					<html:hidden property="cancelarItensFaturaHidden"/>
					</strong></strong></span></strong></td>
				</tr>
				
				<tr>
					<td width="30%"><strong>Retirar Contas do Programa Especial:</strong></td>
					<td width="70%"><strong> <span class="style1"><strong> 
					<html:radio property="retirarContasProgEspecial" value="1" tabindex="4" /> <strong>Sim 
					<html:radio property="retirarContasProgEspecial" value="2" tabindex="5" /> Não
					<html:hidden property="retirarContasProgEspecialHidden"/>
					</strong></strong></span></strong></td>
				</tr>
				
				<tr>
					<td width="30%"><strong>Retirar Situação Especial de Cobrança:</strong></td>
					<td width="70%"><strong> <span class="style1"><strong> 
					<html:radio property="retirarSituacaoEspCobranca" value="1" tabindex="4" /> <strong>Sim 
					<html:radio property="retirarSituacaoEspCobranca" value="2" tabindex="5" /> Não
					<html:hidden property="retirarSituacaoEspCobrancaHidden"/>
					</strong></strong></span></strong></td>
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
							onClick="javascript:window.location.href='/gsan/exibirRegistrarMovimentoProgramaEspecialAction.do?menu=sim'">
						<input type="button" name="Button" class="bottonRightCol" value="Cancelar"
							onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"/>
					</td>
					<td align="right">
						<gsan:controleAcessoBotao name="Button" value="Registrar"
							  onclick="javascript:validarForm(document.forms[0]);" url="registrarMovimentoProgramaEspecialAction.do"/>
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
