<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="gcom.atualizacaocadastral.bean.DadosImovelDuplicadoPreGsanHelper"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>

<script type="text/javascript">

	$(function() {
		$("#btnatualizar").click(function(event) {
	
			var $idImovelPreGsan = document.forms[0].matricula.value;
			var $idImovelGsan = document.forms[0].valorSelecionado.value;

			//enviando os dados para realização da associação
			$.post( "/gsan/exibirConsultarImoveisPreGsanAction.do", { idImovelAtlzCadastral: $idImovelPreGsan, idImovel: $idImovelGsan } );

			//atualizando o conteudo do item na tabela
			window.opener.document.getElementById("matAssociada"+$idImovelPreGsan).innerHTML = $idImovelGsan;
			
			//fechando o popup
			self.close();
			
		});
	});

	function enviarImovelSelecionado() {
		var form = document.forms[0];

		opener.window.location.href = '/gsan/exibirConsultarImoveisPreGsanAction.do?idAtualizacaoCadastralPopup='
				+ form.matricula.value
				+ '&idImovelSelecionado='
				+ form.valorSelecionado.value;
		self.close();

	}

	function armazenarValorSelecionado(valor) {
		var form = document.forms[0];

		form.valorSelecionado.value = valor.value;

	}
</script>

</head>
<body onload="resizePageSemLink(430, 400);">
<form >

<input type="hidden" name="matricula" value="${requestScope.matricula}" id="matricula"> 
<input type="hidden" name="valorSelecionado"  id="valorSelecionado"> 
	<table width="392" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="330" valign="top" class="centercoltext">
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
					<td class="parabg">
					
						Imóveis Selecionados
					
					</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0" bordercolor="#79bbfd">
				<tr>
					<td>
						<div style="width: 100%; height: 200; overflow: auto;">
							<table width="100%" bgcolor="#99CCFF" border="0">

								
								<logic:present name="colecaoImovelDuplicado" scope="request">
					  				<%int cont = 0;%>
                  						<logic:iterate name="colecaoImovelDuplicado" id="idImovel" scope="request" type="DadosImovelDuplicadoPreGsanHelper">								
						  					<%cont = cont + 1;
						  					if (cont % 2 == 0) {%>
					                       	<tr bgcolor="#cbe5fe">
					                        <%} else {%>
					                        <tr bgcolor="#FFFFFF">
					                        <%}%>
					                        
					                        	<td width="50%" align="center"> 
					                            	<input type="radio" id="imovelSelecionado" name="imovelSelecionado"  onchange="armazenarValorSelecionado(this)" value="<bean:write name="idImovel" property="matricula"/>"
					                            		
					                            	/>
					                            </td>
					                            <td width="50%" align="center"  onmouseover="this.T_BGCOLOR='whitesmoke';this.T_LEFT=true;return escape( '${idImovel.dadosMatriculaAssociadaHint}' );"> 
					                            	<bean:write name="idImovel" property="matricula"/>
					                            </td>	
											</tr>
									</logic:iterate>	
								</logic:present>								
							</table>
						</div>
					</td>
				</tr>
				
				<tr>
					
						<td  align="left">
							<input type="button" name="Button3"
							class="bottonRightCol" value="Fechar" tabindex="1"
							onClick="javascript:window.close()"/>
							
									<input id="btnatualizar" type="button" name="Button4"
							class="bottonRightCol" value="Atualizar" tabindex="1" />
						</td>
						
					
				</tr>
			</table>
		<p>&nbsp;</p>
	</table>
	<%@ include file="/jsp/util/tooltip.jsp"%>	
	</form>
</body>

</html:html>