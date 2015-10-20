<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.util.ConstantesSistema" isELIgnored="false"%>
<%@ page import="java.util.Collection" isELIgnored="false"%>
<%@page import="gcom.cadastro.imovel.bean.ImovelRaPopupHelper"  isELIgnored="false"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<html:javascript staticJavascript="false" formName="ConsultarImovelActionForm" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript"><!--


	function comandoFecharPopup(){
		var form = document.forms[0];

		if(form.fecharPopup != null && form.fecharPopup.value != '' && form.fecharPopup.value == 'true'){
			form.fecharPopup.value = null;
			window.close();
		}
	}

	
	
--></script>
</head>


<body leftmargin="0" topmargin="0" onunload="self.focus()" onload="comandoFecharPopup(); resizePageSemLink(700,650);">


<html:form action="/exibirRAContaRevisaoPopupAction.do" 
	name="ConsultarImovelActionForm"
	type="gcom.gui.cadastro.imovel.ConsultarImovelActionForm"
	method="post">
	

	<table width="680" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			
			&nbsp;
			&nbsp;
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg" align="center">RA´s do tipo REGISTRO DE MOVIMENTAÇÃO JUDICIAL</td>
					<td width="11">
						<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
					
				</tr>
			</table>
			
			<table width="100%">
			
				<logic:present name="colecaoImovelRAHelper" scope="request" >
				<%if (((Collection) request.getAttribute("colecaoImovelRAHelper")).size() 
											> 4) {%>
				<td height="150" colspan="10">
					<%} else {%>
					<td  colspan="10">
					<%} %>
							<div style="width: 100%; height: 100%; overflow: auto;">
								<table width="100%">
					<logic:iterate name="colecaoImovelRAHelper" id="helperRA" >
					
						<tr>
							<td bordercolor="#000000" width="100%" colspan="3">
								<strong>Tipo de Especificação: </strong><bean:write name="helperRA" property="nomeEspecificacao"/>
							</td>
							
						</tr>
									
						<tr bordercolor="#000000">
							<td width="15%" bgcolor="#90c7fc" align="center">
								<div class="style9"><font color="#000000" style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Número do RA</strong>
								</font></div>
							</td>
							<td width="15%" bgcolor="#90c7fc" align="center">
								<div class="style9"><font color="#000000" style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Data de Atendimento</strong>
								</font></div>
							</td>
							<td width="70%" bgcolor="#90c7fc" align="center">
								<div class="style9"><font color="#000000" style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Observação do RA</strong> </font></div>
							</td>
						</tr>
						
						<bean:define name="helperRA" 
									 property="imovelRaPopupHelper" 
									 id="helperDadosRA" >
						</bean:define>
						
						<%int count = 0 ;%>			
						<%String cor = "#cbe5fe";%>
						<%cor = "#cbe5fe";%>
									<logic:iterate name="helperDadosRA" 
											  	   id="dados" 
											       type="ImovelRaPopupHelper">
						
										<%if (cor.equalsIgnoreCase("#cbe5fe")) {
											cor = "#FFFFFF";%>
										<tr bgcolor="#FFFFFF">
											<%} else {
											cor = "#cbe5fe";%>
										<tr bgcolor="#cbe5fe">
											<%}%>
												<% count ++ ;%>					
											<td align="left" width="15%">
												<div align="center">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<bean:write name="dados" property="idRegistroAtendimento"/>
													</font>
												</div>
											</td>
											<td align="left" width="15%">
												<div align="center">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<bean:write name="dados" property="dataAtendimento"/> 
													</font>
												</div>
											</td>
											<td align="left" width="70%">
												<div align="center">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<bean:write name="dados" property="observacao"/> 
													</font>
												</div>
											</td>
											
										</tr>
									</logic:iterate>
									<tr bordercolor="#000000">
										<td width="100%" bgcolor="#90c7fc" align="left" colspan="3">
											<div class="style9"><font color="#000000" style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <strong>Total de RA: <bean:write name="helperRA" property="qtdTotalEspecificacao"/></strong>
											</font></div>
										</td>
									</tr>
					&nbsp;
						&nbsp;
					</logic:iterate>
								</table>
							</div>
						</td>
					<logic:iterate name="colecaoTotalRA" id="helperTotalRA" >
						<tr bordercolor="#000000">
							<td width="100%" bgcolor="#90c7fc" align="left" colspan="3">
								<div class="style9"><font color="#000000" style="font-size:9px"
									face="Verdana, Arial, Helvetica, sans-serif"> <strong>Total Geral: <bean:write name="helperTotalRA" property="countRATotal"/></strong>
								</font></div>
							</td>
						</tr>
					</logic:iterate>
					
				</logic:present>
				<logic:notPresent name="colecaoImovelRAHelper" scope="request">
				<div align="center">
					<strong>Não existe nenhum RA do tipo Registro de Movimentação Judicial.</strong>
					</div>
				</logic:notPresent>
			</table>
			
			
			
			&nbsp;
			&nbsp;
			&nbsp;
			&nbsp;
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg" align="center">Contas em Revisão por Ação Judicial</td>
					<td width="11">
						<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
					
				</tr>
			</table>
			
			<table width="100%">
				<logic:present name="colecaoImovelContaHelper" scope="request" >
					<tr bordercolor="#000000">
						<td width="25%" bgcolor="#90c7fc" align="center">
							<div class="style9"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Referência</strong>
							</font></div>
						</td>
						<td width="25%" bgcolor="#90c7fc" align="center">
							<div class="style9"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Vencimento</strong>
							</font></div>
						</td>
						<td width="25%" bgcolor="#90c7fc" align="center">
							<div class="style9"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Valor da Conta</strong> </font></div>
						</td>
						<td width="25%" bgcolor="#90c7fc" align="center">
							<div class="style9"><font color="#000000" style="font-size:9px"
								face="Verdana, Arial, Helvetica, sans-serif"> <strong>Motivo Revisão</strong> </font></div>
						</td>
						
					</tr>
						<%String cor = "#cbe5fe";%>
						<%cor = "#cbe5fe";%>
						<%if (((Collection) request.getAttribute("colecaoImovelContaHelper")).size() 
											> 10) {%>
				<td height="150" colspan="10">
					<%} else {%>
					<td  colspan="10">
					<%} %>
								<div style="width: 100%;  height: 100%; overflow: auto;">
								<table width="100%">
					<logic:iterate name="colecaoImovelContaHelper" id="helper" >
						
						<%if (cor.equalsIgnoreCase("#cbe5fe")) {
							cor = "#FFFFFF";%>
						<tr bgcolor="#FFFFFF">
							<%} else {
							cor = "#cbe5fe";%>
						<tr bgcolor="#cbe5fe">
							<%}%>
							
							<td align="left" width="25%">
								<div align="center">
									<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
										<bean:write name="helper" property="amReferenciaConta"/>
									</font>
								</div>
							</td>
							<td align="left" width="25%">
								<div align="center">
									<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
										<bean:write name="helper" property="dataVencimento"/> 
									</font>
								</div>
							</td>
							<td align="left" width="25%">
								<div align="center">
									<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
										<bean:write name="helper" property="valorConta"/> 
									</font>
								</div>
							</td>
							<td align="left" width="25%">
								<div align="center">
									<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
										<bean:write name="helper" property="descricaoMotivo"/> 
									</font>
								</div>
							</td>
						</tr>
						</logic:iterate>
						</table>
						</div>
						</td>
					
						<logic:iterate name="colecaoTotalConta" id="helperTotalConta" >
							<tr bordercolor="#000000">
								<td width="25%" bgcolor="#90c7fc" align="center">
									<div class="style9"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <strong>Total de Contas:</strong>
									</font></div>
								</td>
								<td width="25%" bgcolor="#90c7fc" align="center">
									<div class="style9"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"> <strong><bean:write name="helperTotalConta" property="countContaTotal"/></strong>
									</font></div>
								</td>
								<td width="25%" bgcolor="#90c7fc" align="center">
									<div class="style9">
										<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> <strong><bean:write name="helperTotalConta" property="valorContaTotal"/></strong> </font>
									</div>
								</td>
								<td width="25%"  align="center">
									<div class="style9">
										<font  style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif">  </font>
									</div>
								</td>
							</tr>
						</logic:iterate>
				</logic:present>
				<logic:notPresent name="colecaoImovelContaHelper" scope="request">
					<div align="center">
					<strong >Não existe nenhuma conta em revisão por motivo de ação Judicial</strong>
					</div>
				</logic:notPresent>
			
			</table>
		</tr>
			<tr>
							<td>
							&nbsp;
			&nbsp;
			&nbsp;
							<table width="100%" cellpadding="0" cellspacing="1">
								<tr>
									<td><input type="button" name="cancelar"
										class="bottonRightCol" value="Fechar"
										onclick="javascript:window.close()">
									</td>
									</tr>
							</table>
							</td>
						</tr>
	</table>
</html:form>
</body>
</html:html>

