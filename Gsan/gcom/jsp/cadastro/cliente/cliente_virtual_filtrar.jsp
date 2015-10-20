<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="gcom.util.ConstantesSistema"%>
<%@page import="gcom.cadastro.cliente.ClienteVirtual"  isELIgnored="false"%>

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="FiltrarClienteVirtualActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript">
<!--


function validarForm(form) {

	if( validateFiltrarClienteVirtualActionForm(form) ){
		if ( comparaData(form.periodoAtendimentoInicial.value,"<=",form.periodoAtendimentoFinal.value) ) {
			form.submit();
		} else {
			alert("Período final deve ser superior ao período inicial");
		}
	}
}

function replicarData() {
	var form =  document.forms[0];

	form.periodoAtendimentoFinal.value = form.periodoAtendimentoInicial.value; 
}


-->
</script>
</head>
<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');">

<html:form action="/exibirFiltrarClienteVirtualAction" 
 		   name="FiltrarClienteVirtualActionForm" 
		   type="gcom.gui.cadastro.cliente.FiltrarClienteVirtualActionForm"
		   method="post"
		   onsubmit="return validateFiltrarClienteVirtualActionForm(this);">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

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
			<td width="620" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11">
						<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
					</td>
					<td class="parabg">Filtrar Cliente Cadastrado no Ambiente Virtual</td>
					<td width="11">
						<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table bordercolor="#000000" width="100%" cellspacing="0">
				<tr>
					<td colspan="2">
						<p>Para filtrar, informe os dados abaixo:</p>
					</td>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>
					</tr>
	        </table>
	        
	        <table bordercolor="#000000" width="100%" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3">
						<table width="100%" border="0">
							<tr>
								<td>
									<strong>Per&iacute;odo de Atendimento:</strong>
								</td>
             				   <td colspan="6">
									<html:text property="periodoAtendimentoInicial" 
											size="11" 
											maxlength="10" 
											tabindex="3" 
											onkeyup="mascaraData(this, event);replicarData();" 
											onkeypress="return isCampoNumerico(event);"/>
									<a href="javascript:abrirCalendario('FiltrarClienteVirtualActionForm', 'periodoAtendimentoInicial');">
										<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" />
									</a>
									a <html:text property="periodoAtendimentoFinal" 
												size="11" 
												maxlength="10" 
												tabindex="3" 
												onkeyup="mascaraData(this, event)"  
												onkeypress="return isCampoNumerico(event);"/>
									<a href="javascript:abrirCalendario('FiltrarClienteVirtualActionForm', 'periodoAtendimentoFinal');">
										<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" alt="Exibir Calendário" />
									</a>
							</tr>
							<tr>
								<td>
									<input name="Button" 
										   type="button" 
										   class="bottonRightCol"
										   value="Limpar" 
										   align="left"
										   onclick="window.location.href='/gsan/exibirFiltrarClienteVirtualAction.do?limparFormulario=sim'"
									       tabindex="8">
							    </td>
								<td>&nbsp;</td>
								<td width="65" align="right">
									<input name="Button2" 
										   type="button"
										   class="bottonRightCol" 
										   value="Filtrar" 
										   align="right"
										   onClick="javascript:validarForm(document.forms[0]);" tabindex="9" /></td>
							</tr>
						</table>
						
					</td>
				</tr>
			</table>
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4" bgcolor="#000000" height="2"></td>
				</tr>
				<tr>
					<td>
					<table width="100%" bgcolor="#99CCFF" border="0">
						<tr bordercolor="#000000">
							<td width="100" bordercolor="#000000" bgcolor="#90c7fc" align="center">
								<div align="center"><strong>Data</strong></div>
						   </td>
						   <td width="380" bgcolor="#90c7fc" align="center">
						  	 <strong>Nome</strong>
						   </td>
						    <td width="120" bgcolor="#90c7fc" align="center"><strong>CPF/CNPJ</strong>
						   </td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
				  <td>
				  <table width="100%" bgcolor="#99CCFF">
                    <pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"
							maxPageItems="10" items="${sessionScope.totalRegistros}">
                    <pg:param name="pg" />
                    <pg:param name="q" />
                    <logic:present name="clienteVirtuais" scope="request">
                      <%int cont = 0;%>
                      <logic:iterate name="clienteVirtuais" id="clienteVirtual" scope="request" type="ClienteVirtual">
                        <pg:item>
                          <%cont = cont + 1;
							if (cont % 2 == 0) {%>
	                          <tr bgcolor="#cbe5fe">
	                            <%} else {%>
	                          </tr>
	                          <tr bgcolor="#FFFFFF">
	                            <%}%>
	                            <td width="15%" align="center"> 
	                            	<bean:write name="clienteVirtual" property="dataFormatada"/>
	                            </td>
	                            
	                            <td width="65%" align="left"  onmouseover="this.T_BGCOLOR='whitesmoke';this.T_LEFT=true;return escape( '${clienteVirtual.hint}' ); ">
	                            	  <% if ( !clienteVirtual.getVerificaCpfCnpjCadastrado() ) {%>
	                            		<a href="javascript:abrirPopupModalDeNome('exibirInserirClienteAction.do?POPUP=true&PopUpInserirClienteRetorno=exibirFiltrarClienteVirtualAction&desfazer=true&idClienteVirtual=' + ${clienteVirtual.id}, 500, 800, 'POPUP1', 'yes');">
											   <bean:write name="clienteVirtual" property="nome" />
										</a>
									 <%} else {%>
										<a href="javascript:abrirPopupModalDeNome('exibirAtualizarClienteAction.do?POPUP=true&&PopUpAtualizarClienteRetorno=exibirFiltrarClienteVirtualAction&atualizarClienteVirtual='+ ${clienteVirtual.id}, 500, 800, 'POPUP2', 'yes');">
												   <bean:write name="clienteVirtual" property="nome"/>
										</a>
									 <%}%>
								</td>
								 
								<td width="20%" align="center"> 
								  <% if ( clienteVirtual.getCpf() != null ) {%>
									 <bean:write name="clienteVirtual" property="cpfFormatado"/>
								  <%} else {%>
								 	 <bean:write name="clienteVirtual" property="cnpjFormatado"/>
								  <%}%>
	                            </td>
	                          </tr>
                        </pg:item>
                      </logic:iterate>
                    </logic:present>
                  </table>
                  </td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">
						<div align="center">
							<strong>
								<%@ include file="/jsp/util/indice_pager_novo.jsp"%>
							</strong>
						</div>
					</td>
				</tr>
				</pg:pager> 
			</table>
	</tr>
</table>

<%@ include file="/jsp/util/rodape.jsp"%>
<%@ include file="/jsp/util/tooltip.jsp"%>
</html:form>
</body>
</html:html>