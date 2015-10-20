<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%@page import="gcom.util.Util" isELIgnored="false"%>
<%@page import="gcom.gui.faturamento.contratodemanda.ContratoDemandaCondominiosResidenciaisHelper"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="FiltrarContratoDemandaCondominiosResidenciaisActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">
	function selecionarContrato(idContrato){
		var form = document.forms[0];
		form.action = 'exibirAtualizarContratoDemandaCondominiosResidenciaisAction.do?idContrato=' + idContrato;
		form.submit();
	}

	function voltarFiltro(){
		var form = document.forms[0];
		form.action = 'exibirFiltrarContratoDemandaCondominiosResidenciaisAction.do?voltar=sim';
		form.submit();
	}
</script>

</head>

<body leftmargin="5" topmargin="5">

<html:form action="/exibirAtualizarContratoDemandaCondominiosResidenciaisAction"
	name="ManterContratoDemandaCondominiosResidenciaisActionForm"
	type="gcom.gui.faturamento.contratodemanda.ManterContratoDemandaCondominiosResidenciaisActionForm"
	method="post" styleId="form">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>


	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="150" valign="top" class="leftcoltext">
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
			<td width="625" valign="top" bgcolor="#003399" class="centercoltext">
			<table height="100%">

				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Manter Contrato de Demanda de condomínio Residencial</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td>
						<table width="100%" bgcolor="#99CCFF" border="0">
						</table>
					</td>
				</tr>
				
				<tr>
					<td>
							<table width="100%" bgcolor="#99CCFF">
							<tr bordercolor="#90c7fc" bgcolor="#90c7fc">
								<td width="10%" align="center"><strong>Número</strong></td>
								<td width="10%" align="center"><strong>Imóvel</strong></td>
								<td width="8%" align="center"><strong>Data Início</strong></td>
								<td width="8%" align="center"><strong>Data Fim</strong></td>
								<td width="40%" align="center"><strong>Cliente</strong></td>
								<td width="4%" align="center"><strong>Desconto</strong></td>
								<td width="20%" align="center"><strong>Situação</strong></td>
							</tr>
							
							
								<%	int cont = 0;	%>
								<logic:iterate name="contratosHelper" 
									id="helper" 
									type="ContratoDemandaCondominiosResidenciaisHelper" 
									scope="session">
									<%	cont = cont + 1;
										if (cont % 2 == 0) {	%>
											<tr bgcolor="#cbe5fe">
									<%	} else {	%>
											<tr bgcolor="#FFFFFF">
									<%	}	%>
										<td width="10%">
											<div align="center">
												<%if(helper.getSituacao().equalsIgnoreCase("ativo") || helper.getSituacao().equalsIgnoreCase("suspenso")){%>
													
												<a href="javascript:selecionarContrato('<%= helper.getNumeroContrato()%>');">
													<%= helper.getNumeroContrato()%>
												</a>
												
												<%}else{%>
													<%= helper.getNumeroContrato()%>
												<%} %>
												
											</div>
										</td>

										<td width="10%">
											<div align="center">
												<%=helper.getIdImovel()%>
											</div>
										</td>
										
										<td width="8%">
											<div align="center">
												<%=Util.formatarData(helper.getDataInicio())%>
											</div>
										</td>
										
										<td width="8%">
											<div align="center">
												<%=Util.formatarData(helper.getDataFim())%>
											</div>
										</td>
										
										<td width="40%">
											<div align="center">
												<%=helper.getCliente()%>
											</div>
										</td>
	
										<td width="4%">
											<div align="center">
												<%=helper.getDesconto()%> %
											</div>
										</td>
										
										<td width="20%">
											<div align="center">
												<%=helper.getSituacao()%>
											</div>
										</td>
									</tr>
								</logic:iterate>
							</table>
					</td>
				</tr>
				
				<tr>
					<td colspan="2">
						<input name="Button" type="button" class="bottonRightCol" value="Voltar Filtro" align="left" tabindex="1"
							onclick="javascript:voltarFiltro();">
					</td>
				</tr>
			</table>
		</table>
		<%@ include file="/jsp/util/rodape.jsp"%> 
	</html:form>
</body>
</html:html>