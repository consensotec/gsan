<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>


<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>/validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script>
<!--
function facilitador(objeto){
    if (objeto.id == "0" || objeto.id == undefined){
        objeto.id = "1";
        marcarTodos();
    }
    else{
        objeto.id = "0";
        desmarcarTodos();
    }
}


// Verifica se h� item selecionado
function verificarSelecao(objeto){
	if (CheckboxNaoVazio(objeto)){
		if (confirm ("Confirma remo��o?")) {
			document.forms[0].action = "/gsan/removerComandoNegativacaoPorCriterioAction.do"
			document.forms[0].submit();
		 }
	}
}

function verificaCheckAtualizar(){

    form = document.forms[0];
	retorno = 0;

	for(indice = 0; indice < form.elements.length; indice++){
		if (form.elements[indice].type == "checkbox" && form.elements[indice].checked == true) {
			retorno++;
			idAtualizar = form.elements[indice];
			alert(idAtualizar.value);
		}
	}

	if (retorno > 1){
		alert('Para atualizar, selecione apenas um registro.'); 
	} else if (retorno == 0){
		alert('Para atualizar, selecione um registro.');
	} else {
		document.forms[0].action = "/gsan/exibirAtualizarNegativadorAction.do";
		document.forms[0].submit();
	}
}
-->
</script>


</head>

<body leftmargin="5" topmargin="5">

<html:form action="/removerPerfilParcelamentoAction" method="post"
	name="ManutencaoRegistroActionForm"
	type="gcom.gui.ManutencaoRegistroActionForm"
	onsubmit="return CheckboxNaoVazio(document.ManutencaoRegistroActionForm.idRegistrosRemocao) && confirm('Confirma remo��o?')">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<input type="hidden" name="idAtualizar">

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
			<td width="602" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			
			
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img src="<bean:message key="caminho.imagens"/>parahead_left.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_left.xws" border="0" /></td>
          <td class="parabg">Manter Comando de Negativa��o</td>

          <td width="11"><img src="<bean:message key="caminho.imagens"/>parahead_right.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws" border="0" /></td>
        </tr>
      </table>
	  <table width="100%" border="0" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF">
	  <tr bordercolor="#cbe5fe"> 
          <td colspan="6">&nbsp;</td>
        </tr>
        <tr bordercolor="#cbe5fe"> 
          <td colspan="6" bgcolor="#cbe5fe"><strong>Comando(s) de Negativa��o Selecionado(s):</strong></td>
        </tr>
        
        <tr bordercolor="#cbe5fe"> 
			<td colspan="6">
        
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<!--<td colspan="4" bgcolor="#3399FF"> -->
					<td colspan="6" bgcolor="#000000" height="2"></td>
				</tr>

				<tr bordercolor="#000000">

		          <td width="7%" bgcolor="#90c7fc"><div align="center"><strong><a	href="javascript:facilitador(this);" id="0">Todos</a></strong></div></td>
		          
		          <td width="40%" bgcolor="#90c7fc"><div align="center"><strong>T�tulo</strong></div></td>
		          <td width="13%" bgcolor="#90c7fc"><div align="center"><strong>Negativador</strong></div>          </td>
		          <td width="10%" bgcolor="#90c7fc"><div align="center"><strong>Simula��o</strong></div>          </td>
		          <td width="17%" bgcolor="#90c7fc"><div align="center"><strong>Data e Hora de Gera��o do Comando</strong></div>          </td>
				  <td width="20%" bgcolor="#90c7fc"><div align="center"><strong>Usu�rio Respons�vel</strong></div>          </td>
		        </tr>
			<tr>
				<td colspan="6">
					<table width="100%" bgcolor="#99CCFF">
		
		<%--Esquema de pagina��o--%>
							<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"
							maxPageItems="10" items="${sessionScope.totalRegistros}">
								<pg:param name="pg" />
								<pg:param name="q" />

							<logic:present name="collectionComandoNegativacao">
								<%int cont = 1;%>
								<logic:iterate name="collectionComandoNegativacao" id="comandoNegativacaoHelper">
									<pg:item>
										<%cont = cont + 1;
											if (cont % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {

											%>
										<tr bgcolor="#cbe5fe">
											<%}%>

											<td width="7%" align="center">
												<input type="checkbox"	name="idRegistrosRemocao"	value="<bean:write name="comandoNegativacaoHelper" property="idComandoNegativacao"/>" >
											</td>
											
											<td width="40%">
												<div align="center">
													<logic:notPresent name="acao" scope="session">
														<c:choose>
															<c:when test="${comandoNegativacaoHelper.tipoComandoCriterio == 3}">
																<a href="exibirAtualizarComandoNegativacaoPorGuiaPagamentoAction.do?manter=sim&idComandoNegativacao=<bean:write
																	name="comandoNegativacaoHelper" property="idComandoNegativacao" />">			
																	<bean:write name="comandoNegativacaoHelper" property="tituloComando"/>
																</a>
															</c:when>
															<c:otherwise>
																<a href="exibirAtualizarComandoNegativacaoPorCriterioAction.do?manter=sim&idComandoNegativacao=<bean:write
																	name="comandoNegativacaoHelper" property="idComandoNegativacao" />">			
																	<bean:write name="comandoNegativacaoHelper" property="tituloComando"/>
																</a>
															</c:otherwise>
														</c:choose>
													</logic:notPresent>
												</div>
											</td>
											
											<td width="13%" align="center">
												<bean:write name="comandoNegativacaoHelper" property="nomeClienteNegativador"/>
											</td>
											
											
											<td width="10%" align="center">
											<logic:equal name="comandoNegativacaoHelper" property="indicadorComandoSimulado" value="<%=gcom.util.ConstantesSistema.SIM.toString() %>">
											SIM
											</logic:equal>
											
											
											<logic:equal name="comandoNegativacaoHelper" property="indicadorComandoSimulado"  value="<%=gcom.util.ConstantesSistema.NAO.toString() %>">
											N�O
											</logic:equal>
											
											
											
																						
											</td>
											<td width="17%" align="center">
												<bean:write name="comandoNegativacaoHelper" property="geracaoComandoInicio"/>
											</td>
											<td width="20%" align="center">
												<bean:write name="comandoNegativacaoHelper" property="nomeUsuarioResponsavel"/>
											</td>
										</tr>
									</pg:item>
								</logic:iterate>
							</logic:present>
						</table>
					</td>
					</tr>       

	    	    	<tr bordercolor="#cbe5fe">
	        			<td colspan="6">&nbsp;</td>
	        		</tr>
		        </table>
	        </td>
        </tr>
        
        </table>
        
        
       	<table width="100%">
				<tr>

					<td>
						<input name="ButtonRemover" type="button" class="bottonRightCol" value="Remover" align="left" style="width: 70px;" onclick="verificarSelecao(idRegistrosRemocao);"> 

						<input name="button" type="button"
							class="bottonRightCol" value="Novo Filtro"
							onclick="window.location.href='<html:rewrite page="/exibirFiltrarComandoNegativacaoPorCriterioAction.do?desfazer=S"/>'"
							align="left" style="width: 80px;">
						
					</td>
					<!-- td align="right">      
						<div align="right"><a href="javascript:toggleBox('demodiv',1);">
							<img border="0"
								src="<bean:message key="caminho.imagens"/>print.gif"
								title="Imprimir Contrato Negativador" /> </a></div>
					</td-->
				</tr>
			</table>



			<table width="100%" border="0">

				<tr>
					<td>
					<div align="center"><strong><%@ include
						file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
					</td>
					</pg:pager>
				</tr>
<%-- Fim do esquema de pagina��o --%>
	
	
	
	
	
	
			</table>
      
      
			</td>
		</tr>
	</table>
	
	<!--
	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioPerfilParcelamentoManterAction.do" />
	-->
	<%@ include file="/jsp/util/rodape.jsp"%>

</body>
</html:form>
</html:html>
