<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@ page import="gsan.util.ConstantesSistema"%>
<%@ page import="gsan.seguranca.parametrosistema.ParametroSistema" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<SCRIPT LANGUAGE="JavaScript">

	function gerarRelatorioFiltro(){
		var form = document.forms[0];
		form.action = "/gerarRelatorioFiltroParametroSistemaNovoAction.do";
		form.submit();
	}

</SCRIPT>


</head>

<body leftmargin="5" topmargin="5">
<html:form action="/exibirManterParametroSistemaAction" method="post">

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

					<p>&nbsp;</p>

					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="11">
								<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
							</td>
							<td class="parabg">
								Manter Novos Parâmetros do Sistema.
							</td>
							<td width="11">
								<img border="0"	src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
							</td>
						</tr>
					</table>



					<table width="590" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td colspan="4" height="23"><font color="#000000" style="font-size: 10px" 
								face="Verdana, Arial, Helvetica, sans-serif"> 
								<strong>Parâmetros Cadastrado(s):</strong> </font>
							</td>
							<td align="right">
							</td>
						</tr>
					</table>
					
					<table width="590" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td bgcolor="#000000" height="2"></td>
						</tr>
						<tr>
							<td>
								<table width="590" bgcolor="#99CCFF">
									<tr bgcolor="#99CCFF">
										<td align="center" width="10%">
											<FONT COLOR="#000000"><strong>Código</strong></FONT>
										</td>
										<td align="left" width="40%">
											<FONT COLOR="#000000"><strong>Nome</strong></FONT>
										</td>		
										<td align="center" width="15%">
											<FONT COLOR="#000000"><strong>Tipo</strong></FONT>
										</td>								
									</tr>
								</table>
							</td>
						</tr>
						
						<tr>
							<td>
								<table width="590" bgcolor="#99CCFF">
									<%String cor = "#cbe5fe";%>
									<%--Esquema de paginação--%>
									<pg:pager isOffset="true" index="half-full" maxIndexPages="10" export="currentPageNumber=pageNumber;pageOffset" 
										maxPageItems="10" items="${sessionScope.totalRegistros}">
										
										<pg:param name="pg" />
										<pg:param name="q" />

										<logic:iterate name="colecaoParametroSistema" id="parametroSistema" type="ParametroSistema">
											<pg:item>
												<%if (cor.equalsIgnoreCase("#cbe5fe")) {
													cor = "#FFFFFF";
												%>
												<tr bgcolor="#FFFFFF" height="18">
													<%} else {
														cor = "#cbe5fe";
													%>
												
												<tr bgcolor="#cbe5fe" height="18">
													<%
														}
													%>

												
													<td width="10%"  align="center">
													   
														<bean:write name="parametroSistema" property="id" />
													</td>
													
													<td width="40%"  align="left">
														<html:link href="/gsan/exibirAtualizarParametroSistemaAction.do?primeiraVez=Ok"
															paramId="idRegistroAtualizar" paramProperty="id" paramName="parametroSistema"
															title="<%="" + parametroSistema.getNome() %>">
															<bean:write name="parametroSistema" 
																property="nome" />
														</html:link>
													</td>
														
													<td width="10%"  align="center">
													   <bean:write name="parametroSistema" property="parametroTipo.descricao" />
													</td>
												   
												</tr>
											</pg:item>
										</logic:iterate>									
								</table>									
							</td>
						</tr>
				 </table>


					<table width="100%" border="0">

						<tr>
							<td>
								<div align="center">
									<strong> <%@ include
											file="/jsp/util/indice_pager_novo.jsp"%></strong>
								</div>
							</td>

							</pg:pager>
							<%-- Fim do esquema de paginação --%>
						</tr>

					</table>
					<table>
						<tr>
							<td width="500">
							   <font color="#FF0000">
											
								<input name="button" type="button" class="bottonRightCol"
							tabindex="2" value="Voltar Filtro"
							onclick="javascript:window.location.href='/gsan/exibirFiltrarParametroSistemaAction.do?menu=sim'">
							 </td>
											
						    <td align="right" valign="top">
								<a href="javascript:toggleBox('demodiv',1);">
                        			<img align="right" border="0" src="<bean:message key='caminho.imagens'/>print.gif"  title="Imprimir Parametros"/>
								</a>
							</td>								
					    </tr>
					</table>
				</td>
			</tr>
		
		</table>
		

	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioFiltroParametroSistemaNovoAction.do"/>
	
		<%@ include file="/jsp/util/rodape.jsp"%>

	</html:form>
</body>
</html:html>