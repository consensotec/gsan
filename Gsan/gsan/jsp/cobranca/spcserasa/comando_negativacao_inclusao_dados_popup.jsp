<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@page import="gsan.cobranca.bean.DadosInclusoesComandoNegativacaoHelper"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>



<script language="JavaScript">


</script>

</head>

<body leftmargin="0" topmargin="0" onload="window.focus();">

<html:form action="/exibirInclusaoDadosComandoNegativacaoPopupAction" 
	name="InclusaoDadosComandoNegativacaoPopupActionForm" method="post"
	type="gsan.gui.cobranca.spcserasa.InclusaoDadosComandoNegativacaoPopupActionForm">


<table width="700" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="690" valign="top" class="centercoltext">
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
					<td class="parabg">Consultar Inclus�es da Negativa��o </td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				<tr>
					<td height="3" colspan="3"></td>
				</tr>
			</table>
			
			<table width="100%" cellpadding="0" cellspacing="0">
					
				<tr>
					<td>
						<strong>Negativador:</strong>
					</td>
					<td>
						<html:text property="negativador" size="50" maxlength="50" readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000;" />
						
					</td>
				</tr>
				<tr>
					<td height="10" colspan="4">
					<div align="right">
					<hr>
					</div>
					<div align="right"></div>
					</td>
				</tr>
				
				
				<tr>
					<td>
						<strong>Quantidade de Inclus�es:</strong>
					</td>
					<td>
						<html:text property="qtdInclusoes"  size="5" maxlength="5" readonly="true" 
						style="background-color:#EFEFEF; border:0; color: #000000;" />
					</td>
				</tr>
				
				<tr>
					<td>
						<strong>Valor Total do D�bito:</strong>
					</td>
					<td>
						<html:text property="valorTotalDebito"  size="16" maxlength="16" readonly="true" 
						style="background-color:#EFEFEF; border:0; color: #000000;" />
					</td>
				</tr>
				
				<tr>
					<td>
						<strong>Quantidade de Itens Inclu�dos:</strong>
					</td>
					<td>
						<html:text property="qtdItensIncluidos"  size="5" maxlength="5" readonly="true" 
						style="background-color:#EFEFEF; border:0; color: #000000;" />
					</td>
				</tr>
				
				<tr>
					<td height="10" colspan="4">
					<div align="right">
					<hr>
					</div>
					<div align="right"></div>
					</td>
				</tr>
				
	  		</table>
			
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4" height="23">
						<font color="#000000" style="font-size:10px" face="Verdana, Arial, Helvetica, sans-serif">
						<strong>DADOS DAS INCLUS�ES</strong>
						</font>
					</td>
				</tr>
	  		</table>
	  		<table width="100%" cellpadding="0" cellspacing="0">
	  			
				<tr>
					<td colspan="4" bgcolor="#000000" height="1"></td>
				</tr>
				<tr>
					<td>
						<table width="100%" bgcolor="#99CCFF">
							<!--header da tabela interna -->
							<tr bgcolor="#99CCFF">
									<td width="13%" align="center">
										<strong>Im�vel</strong>
									</td>
									<td width="13%" align="center">
										<strong>CPF/CNPJ</strong>
									</td>
									<td width="11%" align="center">
										<strong>Valor do D�bito</strong>
									</td>
									<td width="11%" align="center">
										<strong>Situa��o do D�bito</strong>
									</td>
									<td width="11%" align="center">
										<strong>Data da Situa��o do D�bito</strong>
									</td>
									<td width="9%" align="center">
										<strong>Inclus�o Aceita</strong>
									</td>
									<td width="9%" align="center">
										<strong>Inclus�o Corrigida</strong>
									</td>
									<td width="11%" align="center">
										<strong>Situa��o da Inclusao</strong>
									</td>
									<td width="10%" align="center">
										<strong>Usu�rio da Exclus�o</strong>
									</td>
									<td width="10%" align="center">
										<strong>Usu�rio de Inclus�o</strong>
									</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table width="100%" bgcolor="#99CCFF">
						<%--Esquema de pagina��o--%>
						<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"				
							maxPageItems="10" items="${sessionScope.totalRegistrosSegundaPaginacao}">
							<pg:param name="pg"/>
							<pg:param name="q"/>
							<logic:present name="collectionDadosInclusoesComandoNegativacao">
								<%int cont = 0;%>
								<logic:iterate name="collectionDadosInclusoesComandoNegativacao" id="dadosInclusoesComandoNegativacao" type="DadosInclusoesComandoNegativacaoHelper">
									<pg:item>
										<%cont = cont + 1;
										if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
										<%} else {%>
										<tr bgcolor="#FFFFFF">
										<%}%>
											<td width="13%" align="center">
												<bean:write name="dadosInclusoesComandoNegativacao" property="idImovel"/>
											</td>
											<td width="11%" align="center">
												<bean:write name="dadosInclusoesComandoNegativacao" property="cpfCnpj"/>
											</td>
											<td width="11%" align="center">
												<bean:write name="dadosInclusoesComandoNegativacao" property="valorDebito"/>
											</td>
											<td width="11%" align="center">
												<bean:write name="dadosInclusoesComandoNegativacao" property="descricaoCobrancaSituacao"/>
											</td>
											<td width="11%" align="center">
												<bean:write name="dadosInclusoesComandoNegativacao" property="dataSituacaoDebito" formatKey="date.format"/>
											</td>
											<td width="7%" align="center">
												<bean:write name="dadosInclusoesComandoNegativacao" property="inclusaoAceita"/>
											</td>
											<td width="7%" align="center">
												<bean:write name="dadosInclusoesComandoNegativacao" property="inclusaoCorrigida"/>
											</td>
											<td width="9%" align="center">
												<bean:write name="dadosInclusoesComandoNegativacao" property="situacaoInclusao"/>
											</td>
											<td width="10%" align="center">
												<bean:write name="dadosInclusoesComandoNegativacao" property="nomeUsuario"/>
											</td>
											<td width="20%" align="center">
												<bean:write name="dadosInclusoesComandoNegativacao" property="nomeUsuarioInclusao"/>
											</td>
										</tr>
									</pg:item>
								</logic:iterate>
							</logic:present>
						<%-- Fim do esquema de pagina��o --%>
							<table width="100%" border="0">
								<tr>
									<td align="center">
										<strong><%@ include file="/jsp/util/indice_pager_novo.jsp"%></strong>
									</td>
								</tr>
							</table>						
						
						
							</pg:pager>
						</table>
												

						<table width="100%">
							<tr>
			            		<td >
		              				<div align="right">
	                  					<input name="ButtonFechar" type="button" class="bottonRightCol" value="Fechar" onclick="window.close();">
		              				</div>
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
</html:form>
</body>
</html:html>