<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>

<%@ page import="gsan.atendimentopublico.bean.AcoesParaCorrecaoAnormalidadesEncontradasHelper" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>
<script>
    function validateConsultarDadosOrdemServicoVisitaActionForm(form) {
        return true;
    }

	
	function selecionouObj(nome){

		var form = document.forms[0];
	    var selecionados = form.elements[nome];
		var retorno = false;
		
		if (selecionados.value != null && selecionados.value != '') {
			
			retorno = true;
			
		} else {
		
			for (i=0; i< selecionados.length; i++) {
				if(selecionados[i].checked){
					retorno = true;
				}
			}
			
		}
		
		return retorno;
	}
	
	function objetoSelecionado(nome){

		var form = document.forms[0];
	    var selecionados = form.elements[nome];
		var retorno = false;
		
		if (selecionados.value != null && selecionados.value != '') {
			
			return selecionados.value;
			
		} else {
			for (i=0; i< selecionados.length; i++) {
				if(selecionados[i].checked){
					return selecionados[i].value;
				}
			}
		}
	}
	
    function facilitador(objeto){
        if (objeto.id == "0" || objeto.id == undefined){ 
            objeto.id = "1";
            
    		for (var i=0;i < document.forms[0].elements.length;i++){
    			var elemento = document.forms[0].elements[i];
    			if (elemento.type == "checkbox" && elemento.name == 'idsAcoes'){
    				elemento.checked = true;
    			}
    		}
        }
        else{
            objeto.id = "0";
            
            for (var i=0;i < document.forms[0].elements.length;i++){
    			var elemento = document.forms[0].elements[i];
    			if (elemento.type == "checkbox" && elemento.name == 'idsAcoes'){
    				
    				if (elemento.checked == true){
    					elemento.checked = false;
    				}
    			}
    		}
        }
    }
</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form
    action="/consultarDadosOrdemServicoVisitaWizardAction"
    method="post"
>

<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=1"/>

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="165" valign="top" class="leftcoltext">
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
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg" colspan="2">Consultar Dados da OS de Visita</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
      <table width="100%" border="0" dwcopytype="CopyTableRow">
        <tr>
          <td colspan="4">
            Dados da ordem de serviço de visita selecionada:
          </td>
        </tr>
		<tr>
			<td colspan="3" height="7"> </td>
		</tr>	
		<tr>
            <td><strong>Ordem de Serviço:</strong></td>
            <td>
               	<strong>
	     			<html:text
     					   maxlength="20"
     					   size="20"
     					   property="ordemServico"
     					   readonly="true"
 		     			   style="background-color:#EFEFEF; border:0; color: #000000"/>
    			</strong>
    		</td>
            <td><strong>Matrícula:</strong></td>
            <td>
               	<strong>
	     			<html:text
     					   maxlength="20"
     					   size="20"
     					   property="matricula"
     					   readonly="true"
 		     			   style="background-color:#EFEFEF; border:0; color: #000000"/>
    			</strong>
    		</td>
        </tr>
        </table>

     	<table width="100%" border="0" dwcopytype="CopyTableRow">
		<tr>
			<td colspan="2" height="7"> </td>
		</tr>	
		<tr>
			<td colspan="2" bgcolor="#000000" height="2" valign="baseline"></td>
		</tr>
		<tr>
			<td colspan="2" height="7"> </td>
		</tr>
		<tr>
            <td><strong>Anormalidade Registrada:</strong></td>
            <td align="left">
               	<strong>
	     			<html:text
     					   maxlength="30"
     					   size="30"
     					   property="anormalidadeRegistrada"
     					   readonly="true"
 		     			   style="background-color:#EFEFEF; border:0; color: #000000"/>
    			</strong>
    		</td>
        </tr>
		<tr>
            <td><strong>Anormalidade Encontrada:</strong></td>
            <td align="left">
               	<strong>
	     			<html:text
     					   maxlength="30"
     					   size="30"
     					   property="anormalidadeEncontrada"
     					   readonly="true"
 		     			   style="background-color:#EFEFEF; border:0; color: #000000"/>
    			</strong>
    		</td>
        </tr>
		<tr>
            <td><strong>Tipo de Pavimento de Calçada:</strong></td>
            <td align="left">
               	<strong>
	     			<html:text
     					   maxlength="30"
     					   size="30"
     					   property="tipoPavimentoCalcada"
     					   readonly="true"
 		     			   style="background-color:#EFEFEF; border:0; color: #000000"/>
    			</strong>
    		</td>
        </tr>
		<tr>
            <td><strong>Tipo de Pavimento de Rua:</strong></td>
            <td align="left">
               	<strong>
	     			<html:text
     					   maxlength="30"
     					   size="30"
     					   property="tipoPavimentoRua"
     					   readonly="true"
 		     			   style="background-color:#EFEFEF; border:0; color: #000000"/>
    			</strong>
    		</td>
        </tr>
		<tr>
			<td colspan="2" height="7"> </td>
		</tr>	
        <tr>
        	<td width="100%" colspan="2">
				<table width="80%" align="center" border="0" cellpadding="0" cellspacing="0">
					<logic:notPresent name="ordemServicoEncerrada" scope="session" >
						<tr>
				            <td align="left"><strong>Marque abaixo as ações para geração das Ordens de Serviço:</strong></td>
				        </tr>
			        </logic:notPresent>
					<tr>
						<td colspan="2" height="4"> </td>
					</tr>	
				</table>
				<table width="80%" align="center" bgcolor="#90c7fc" border="0" cellpadding="0" cellspacing="0">
					<tr bgcolor="#cbe5fe" >
						<td width="100%" align="center">
							 <table width="100%" bgcolor="#99CCFF" border="0">
								<tr bordercolor="#000000" bgcolor="#90c7fc" class="styleFontePeqNegrito">
									<!-- 1 -->
									<logic:notPresent name="ordemServicoEncerrada" scope="session" >
										<td width="10%" bgcolor="#90c7fc" align="center">
											<div align="center" ><strong><a
												href="javascript:facilitador(this);">Todas</a></strong></div>
										</td>
									</logic:notPresent>
									<!-- 2 -->
									<td width="90%" bgcolor="#90c7fc" align="left">
										<div align="left"><strong>Ação para Correção da Anormalidade</strong></div>
									</td>
								</tr>
							</table>
	
							<div style="height: 100%; max-height: 300px;  overflow: auto;">
								<table width="100%" bgcolor="#99CCFF">
									<tr bordercolor="#000000" bgcolor="#90c7fc">
										
										<logic:present name="colecaoAcoesCorrecaoAnormalidadesEncontradasHelper">
											<%int cont = 0;%>
											<logic:iterate name="colecaoAcoesCorrecaoAnormalidadesEncontradasHelper"
												id="acoesParaCorrecaoAnormalidadesEncontradasHelper"
												type="AcoesParaCorrecaoAnormalidadesEncontradasHelper"
												scope="session">
												  <%
													  cont = cont + 1;
													  if (cont % 2 == 0) {
												  %>
													<tr bgcolor="#cbe5fe">
													<%} else {%>
													<tr bgcolor="#FFFFFF">
													<%}%>
													
													<!-- 1 -->		
												<logic:notPresent name="ordemServicoEncerrada" scope="session" >
													<td width="10%">
														<div align="center">
																<html:checkbox property="idsAcoes"
																	value="${acoesParaCorrecaoAnormalidadesEncontradasHelper.idServicoTipo}" />
														</div>
													</td>
												</logic:notPresent>
												<!-- 2 -->	
												<td width="90%" align="left">
													<bean:write name="acoesParaCorrecaoAnormalidadesEncontradasHelper" property="servicoTipo" />
												</td>
											</tr>
										</logic:iterate>
									</logic:present>
							</table>
						</div>
						</td>
					</tr>
				</table>
        	</td>
        </tr>
        <logic:notPresent name="ordemServicoEncerrada" scope="session" >
			<tr>
				<td>
					<div align="center">
						<html:checkbox 
							property="ordemServicoConferida" 
							value="sim" />
							&nbsp;<strong>OS de Visita Conferida</strong>
					</div>
				</td>
			</tr>
		</logic:notPresent>
        <tr>
          <td colspan="2"><div align="right"><jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=1"/></div></td>
        </tr>
      </table>
		<table width="100%" border="0">
			<tr>
				<td colspan="3" height="7"> </td>
			</tr>	
		</table>
      <p>&nbsp;</p>
    </td>
  </tr>

</table>

<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>
