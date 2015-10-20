<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.gui.*,java.util.*" isELIgnored="false"%>

<%String numeroPagina = request.getParameter("numeroPagina");
			StatusWizard statusWizard = (StatusWizard) session
					.getAttribute("statusWizard");
			StatusWizard.StatusWizardItem itemWizardAba = statusWizard
					.retornarItemWizard(Integer.parseInt(numeroPagina));
			pageContext.setAttribute("numeroPagina", numeroPagina);
			pageContext.setAttribute("caminhoActionFinalPagina", itemWizardAba
					.getCaminhoActionFinal());
			pageContext.setAttribute("caminhoActionInicialPagina",
					itemWizardAba.getCaminhoActionInicial());

			String hint = statusWizard.gerarHint();
			pageContext.setAttribute("hint", hint);
			
			int qtdAbas = statusWizard.getRelacaoNumeroPaginaCaminho().size();
			
			// Vari�vel gerada para realiza��o da condicional que verifica a exist�ncia de hint para funcionalidade
			// Ficou constatado que apenas os tipos primitivos do Java podem ser manipulados via Javascript
			
			int condicaoHint = 0;
			
			if (hint.trim().length() > 0){
				condicaoHint = 1;
			}

			%>


<div id='Layer1'
	style='position:absolute; left:; top:77px; width:300px; height:12px; z-index:1'>
<table align="right" cellpadding="0" cellspacing="0">
	<tr>
		<%int contador = 1;%>
		<logic:notEmpty name="hint">
			<td valign="center"><img border="0" width="25" height="25"
				src="<bean:message key="caminho.imagens"/>informacao.gif"
				onmouseover="this.T_BGCOLOR='whitesmoke';this.T_LEFT=true;return escape('<%=hint%>')" />&nbsp;&nbsp;&nbsp;
			</td>
		</logic:notEmpty>

		<logic:iterate name="statusWizard"
			property="relacaoNumeroPaginaCaminho" id="itemWizard" scope="session">
			<td valign="top"><logic:lessThan name="itemWizard"
				property="value.numeroPagina" value="${pageScope.numeroPagina}">
				<%StatusWizard.StatusWizardItem item = statusWizard
							.retornarItemWizard(contador);

					%>
				<a
					href="javascript:botaoAvancarTelaEspera('/gsan/<bean:write name="statusWizard" property="caminhoActionPrincipalWizard"/>.do?action=<%=item.getCaminhoActionInicial()%>');document.forms[0].submit();">
				<img id="<%=contador%>" border="0"
					src="<bean:message key="caminho.imagens"/><bean:write name="itemWizard" property="value.imagemNaoSelecionada"/>" />
				</a>
			</logic:lessThan> <logic:greaterThan name="itemWizard"
				property="value.numeroPagina" value="${pageScope.numeroPagina}">
				<a
					href="javascript:botaoAvancarTelaEspera('/gsan/<bean:write name="statusWizard" property="caminhoActionPrincipalWizard"/>.do?destino=<%=contador%>&action=<bean:write name="caminhoActionFinalPagina"/>');">
				<img id="<%=contador%>" border="0"
					src="<bean:message key="caminho.imagens"/><bean:write name="itemWizard" property="value.imagemNaoSelecionada"/>" />
				</a>
			</logic:greaterThan> <logic:equal name="itemWizard"
				property="value.numeroPagina" value="${pageScope.numeroPagina}">
				<img id="<%=contador%>" border="0"
					src="<bean:message key="caminho.imagens"/><bean:write name="itemWizard" property="value.imagemSelecionada"/>" />
			</logic:equal></td>
			<%contador++;%>
		</logic:iterate>
	</tr>
</table>
</div>

<SCRIPT LANGUAGE="JavaScript">
<!--

function browser(){

	var padrao = 460;

	if (document.all) {
		
		var qtdAbas = <%=""+qtdAbas%>;
		var hint = <%=""+condicaoHint%>;
		
		 switch (qtdAbas){
			case 2: 
				document.getElementById("Layer1").style.left = 460;
				break;
			case 3: 
				document.getElementById("Layer1").style.left = padrao;
				break;
			case 4: 
				document.getElementById("Layer1").style.left = 460;
				break;
			case 5: 
				
				if (hint == "0"){
					document.getElementById("Layer1").style.left = 448;
				}
				else{
					document.getElementById("Layer1").style.left = 413;
				}
				
				break;
			case 6: 
				
				if (hint == "0"){
					document.getElementById("Layer1").style.left = 386;
				}
				else{
					document.getElementById("Layer1").style.left = 353;
				}
				
				break;
			case 7: 
				
				if (hint == "0"){
					document.getElementById("Layer1").style.left = 325;
				}
				else{
					document.getElementById("Layer1").style.left = 292;
				}
				
				break;
			default : 
		}
	}
	else{
		document.getElementById("Layer1").style.left = padrao;
	}
	
}


browser();

//-->
</SCRIPT>
