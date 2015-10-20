<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.gui.GcomAction"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script src="<bean:message key="caminho.js"/>util.js"></script>
<script src="<bean:message key="caminho.js"/>jquery/jquery.js"></script>

<script>
	function alternarVisibilidade(classDetalheFoto) {
		$(classDetalheFoto).toggle();
	}
</script>
</head>

<body leftmargin="0" topmargin="0"
	onload="window.focus();resizePageSemLink(730, 550);">
	
<html:form action="/exibirConsultarFotosAtualizacaoCadastralPopupAction" 
	method="post" name="ConsultarImovelActionForm"
	type="gcom.gui.cadastro.imovel.ConsultarImovelActionForm">
	
	<table width="100%" border="0" cellspacing="5" cellpadding="0">     
		<tr>
			<td width="615" valign="top" class="centercoltext">
				<table height="100%">
					<tr>
						<td></td>
					</tr>
				</table>
				
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
						</td>

						<td class="parabg">Consultar Fotos da Atualização Cadastral do Imóvel</td>
						
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
						</td>
					</tr>
				</table>
			
				<p>&nbsp;</p>
			
				<table width="100%" border="0">

					<% int contadorFoto = 0; %>

					<logic:iterate name="helpers" id="helper" type="gcom.cadastro.imovel.bean.ConsultarFotosAtualizacaoCadastralHelper">
					<tr>
						<td align="center">
								<table width="100%" border="0" bgcolor="#99CCFF">
									<tr bgcolor="#99CCFF">
										<td colspan="2" align="center">
											<span class="style2">
											<a href="javascript:alternarVisibilidade('.detalheFoto<%= ++contadorFoto %>');">
												<b>${helper.dataVisita}</b>
											</a>
											</span>
										</td>
									</tr>

									<tr class="detalheFoto<%= contadorFoto %>" style="display:none">
										<td><strong>Cadastrador:</strong></td>
										<td> 
											<html:text name="helper" property="cadastrador" readonly="true"
												style="background-color:#EFEFEF; border:0; width: 100%" size="65"
												maxlength="65"/>
										</td>
									</tr>
										
									<tr class="detalheFoto<%= contadorFoto %>" style="display:none">
										<td colspan="2">
											<table width="100%">

											<% int cont = 1; %>
											<logic:iterate name="helper" property="fotos" id="foto" type="gcom.atualizacaocadastral.ImovelFotoAtualizacaoCadastralDM">
											<% if (++cont % 2 == 0) { %> <tr bgcolor="#cbe5fe"> <% } %>
												<td>
													<table width="100%" border="0">
													<tr>
														<td align="center">
															<strong>${foto.fotoSituacao.descricao}</strong>
														</td>
													</tr>
													<tr>
														<td align="center">
															<img width="220px" height="170px" src="${foto.fotoImovelBase64}" /> 
														</td>
													</tr>
													</table>
												</td>
											<% if (cont % 2 != 0) { %> </tr> <% } %>
											</logic:iterate>

											</table>
										</td>
									</tr>
								</table>
						</td>
					</tr>
					</logic:iterate>
					
					<tr>
						<td>
							<table width="100%" border="0">
								<tr>
									<td align="left">
										<input name="ButtonFechar" type="button" class="bottonRightCol" 
											value="Fechar" onclick="window.close();" style="width: 70px;">
									</td>
								</tr>
							</table>
						</td>
					</tr>
					
				</table>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html>
