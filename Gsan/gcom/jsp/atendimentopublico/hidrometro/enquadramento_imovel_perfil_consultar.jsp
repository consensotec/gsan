<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page import="gcom.cadastro.imovel.ImovelHistoricoPerfil"%>
<%@ page import="gcom.util.Util"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
</head>
	<body leftmargin="0" topmargin="0" onload="window.focus();resizePageSemLink(750,255);">	
		<html:form action="/exibirConsultarEnquadramentoImovelPerfilAction" 
			name="ConsultarEnquadramentoImovelPerfilActionForm"
			type="gcom.gui.atendimentopublico.hidrometro.ConsultarEnquadramentoImovelPerfilActionForm"
			method="post">			
			<table width="702" border="0" cellspacing="5" cellpadding="0">
				<tr>
					<td width="100%" valign="top" class="centercoltext">
						<table height="100%"><tr><td></td></tr></table>
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">	
							<tr>
								<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
								<td class="parabg">Consultar Enquadramento do Imóvel</td>
								<td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
							</tr>
						</table>
						<p>&nbsp;</p>
						<table width="100%" border="0">
							<tr bordercolor="#000000" bgcolor="#90c7fc" class="styleFontePeqNegrito">
								<td width="20%" bgcolor="#90c7fc"><strong>Tipo</strong></td>
								<td width="20%" bgcolor="#90c7fc"><strong>Data</strong></td>	
								<td width="10%" bgcolor="#90c7fc"><strong>Perfil Anterior</strong></td>	
								<td width="10%" bgcolor="#90c7fc"><strong>Perfil Atual</strong></td>
								<td width="20%" bgcolor="#90c7fc"><strong>Usuario</strong></td>
								<td width="20%" bgcolor="#90c7fc"><strong>Motivo</strong></td>												
							</tr>							
							<%int cont = 0;%>
							<logic:notEmpty name="imovelHistPerfils">
								<logic:iterate id="imovelHistoricoPerfil" name="imovelHistPerfils" type="ImovelHistoricoPerfil">
									<%cont = cont + 1;
									  if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe" class="styleFontePequena">
									<%} else {%>
									  	<tr bgcolor="#FFFFFF" class="styleFontePequena">
									<%}%>											
											<td colspan="1"><%=imovelHistoricoPerfil.getPerfilAlteracaoTipo() != null ? imovelHistoricoPerfil.getPerfilAlteracaoTipo().getDescricao() : ""%></td>
											<td colspan="1"><%=imovelHistoricoPerfil.getUltimaAlteracao() != null ? Util.formatarData(imovelHistoricoPerfil.getUltimaAlteracao()) : ""%></td>					
											<td colspan="1"><%=imovelHistoricoPerfil.getImovelPerfilAnterior() != null ? imovelHistoricoPerfil.getImovelPerfilAnterior().getDescricao() : ""%></td>
											<td colspan="1"><%=imovelHistoricoPerfil.getImovelPerfilPosterior() != null ? imovelHistoricoPerfil.getImovelPerfilPosterior().getDescricao() : ""%></td>
											<td colspan="1"><%=imovelHistoricoPerfil.getUsuario() != null ? imovelHistoricoPerfil.getUsuario().getNomeUsuario() : ""%></td>
											<td colspan="1"><%=imovelHistoricoPerfil.getPerfilAlteracaoMotivo() != null ? imovelHistoricoPerfil.getPerfilAlteracaoMotivo().getDescricao() : ""%></td>
										</tr>	
								</logic:iterate>							
							</logic:notEmpty>																		
						</table>
						<p>&nbsp;</p>
					</td>
				</tr>
			</table>	
		</html:form>
	</body>
</html:html>