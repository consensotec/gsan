<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.cobranca.DocumentoTipo" %>
<%@ page import="java.util.Collection" isELIgnored="false"%>
<%@ page import="gcom.faturamento.debito.DebitoACobrar"%>
<%@ page import="gcom.cobranca.bean.ContaValoresHelper"%>
<%@ page import="gcom.cobranca.bean.GuiaPagamentoValoresHelper"%>
<%@ page import="gcom.util.Util" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>
<script type="text/javascript" language="Javascript1.1">

	function validateConsultarDadosOrdemServicoVisitaActionForm(form) {
	    return true;
	}
</script>

<style type="text/css">
	/* jQuery lightBox plugin - Gallery style */
	#gallery ul { list-style: none; }
	#gallery ul li { display: inline; }
	#gallery ul img {
		border: 5px solid #CBE5FE;
		border-width: 5px 5px 20px;
	}
	#gallery ul a:hover img {
		border: 5px solid #fff;
		border-width: 5px 5px 20px;
		color: #fff;
	}
	#gallery ul a:hover { color: #fff; }
</style>
</head>

<body leftmargin="5" topmargin="5" >

<html:form
    action="/consultarDadosOrdemServicoVisitaWizardAction"
    method="post"
>

<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=2"/>
<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>
<input type="hidden" name="numeroPagina" value="2"/>

<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="145" valign="top" class="leftcoltext">
    
		<input type="hidden" name="checkConta" value="0">
		<input type="hidden" name="checkDebito" value="0">
		<input type="hidden" name="checkGuia" value="0">
		
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
      <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Consultar Dados da OS de Visita</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
      <table width="100%" border="0" dwcopytype="CopyTableRow">
        <tr>
          <td colspan="2">
            Dados da ordem de servi�o de visita selecionada:
          </td>
        </tr>
		<tr>
			<td colspan="3" height="7"> </td>
		</tr>	
		<tr>
            <td><strong>Ordem de Servi�o:</strong></td>
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
            <td><strong>Matr�cula:</strong></td>
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
		
		<logic:present name="colecaoFotoOS">
		<%int cont = 1;%>
			<tr>
				<td colspan="2" height="7">
					<div id="gallery">
					    <ul>
					    	<table width="100%" border="0" >
						    	<logic:iterate name="colecaoFotoOS" id="osFoto" type="gcom.atendimentopublico.ordemservico.OrdemServicoFoto">
									<li>
								    		<%
												  cont = cont + 1;
												  if (cont % 2 == 0) {
											  %>
											<tr bgcolor="#cbe5fe">
												<%}%>
												<td >
												<div id="gallery">
													<table width="100%" border="0">
														<tr>
															<td align="center">
																<strong><bean:write name="osFoto" property="descricaoFoto"/></strong>
															</td>
														</tr>
														<tr>
															<td align="center">
																<a href='/gsan/jsp/atendimentopublico/ordemservico/ordem_servico_foto.jsp?idFoto=<bean:write name="osFoto" property="id"/>'
																	title='<bean:write name="osFoto" property="descricaoFoto"/>'>
																	<img width="220px" height="170px" 
																		src="/gsan/jsp/atendimentopublico/ordemservico/ordem_servico_foto.jsp?idFoto=<bean:write name="osFoto" property="id"/>"/>
																</a>
															</td>
														</tr>
													</table>
												</div>
												</td>
												<%
												  if (cont % 2 != 0) {
											 	 %>
												</tr>
												<%}%>
										</li>
									</logic:iterate>
							</table>
					    </ul>
					</div>
				</td>
			</tr>
		</logic:present>
        <tr>
          <td colspan="2"><div align="right"><jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=2"/></div></td>
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
