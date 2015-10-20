<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@page import="gcom.cobranca.bean.CobrancaDocumentoHelper"%>
<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page import="java.util.Collection"%>
<%@ page import="gcom.util.Util"%>
<%@ page import="gcom.faturamento.conta.ContaEmissao2Via"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>

<script language="JavaScript">
<!-- Begin
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

   	var form = document.forms[0];
   	consultarImovelAjax();
    if (tipoConsulta == 'imovel') {
      form.idImovelDocumentosCobranca.value = codigoRegistro;
      form.matriculaImovelDocumentosCobranca.value = descricaoRegistro;
      form.matriculaImovelDocumentosCobranca.style.color = "#000000";
      
	  form.action = 'consultarImovelWizardAction.do?action=exibirConsultarImovelDocumentosCobrancaAction&indicadorNovo=OK'
	  form.submit();
    }
    
}

function limparForm(){
   	var form = document.forms[0];
    form.idImovelDocumentosCobranca.value = "";
    consultarImovelAjax();
	form.action = 'consultarImovelWizardAction.do?action=exibirConsultarImovelDocumentosCobrancaAction&limparForm=OK'
	form.submit();
}


function habilitaMatricula() {
	var form = document.forms[0];
	
	if (form.idImovelDocumentosCobranca.value != null && form.matriculaImovelDocumentosCobranca.value != null && 
		form.matriculaImovelDocumentosCobranca.value != "" && form.matriculaImovelDocumentosCobranca.value != "IMÓVEL INEXISTENTE"){
	
		form.idImovelDocumentosCobranca.disabled = true;
	} else {
		form.idImovelDocumentosCobranca.disabled = false;
	}
}

function pesquisarImovel() {
	var form = document.forms[0];
 	
 	if (form.idImovelDocumentosCobranca.disabled ) {
 		alert("Para realizar uma pesquisa de imóvel é necessário apagar o imóvel atual.")
	} else {
		abrirPopup('exibirPesquisarImovelAction.do', 400, 800)
	}
}

function consultarImovelAjax(){
	var form = document.forms[0];

	if(form.idImovelDocumentosCobranca != null && form.idImovelDocumentosCobranca.value != ""){
		$.ajax({
				   type: "POST",
				   url: "consultarImovelAjaxAction.do?idImovel="+form.idImovelDocumentosCobranca.value,
				   data: "",
				   success: function(msg){
					   if ( msg != null && msg != "" ) {
						   alert(msg);
						   msg = "";
						}
				   }
	 	});
	} else {
		$.ajax({
			   type: "POST",
			   url: "consultarImovelAjaxAction.do?desfazer=sim",
			   data: "",
			   success: function(msg){
				   
			   }
		});
	}

}


-->

</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:habilitaMatricula();setarFoco('idImovelDocumentosCobranca');consultarImovelAjax();">
<html:form action="/exibirConsultarImovelAction.do"
	name="ConsultarImovelActionForm"
	type="gcom.gui.cadastro.imovel.ConsultarImovelActionForm" method="post"
	onsubmit="return validateConsultarImovelActionForm(this);">


	<jsp:include
		page="/jsp/util/wizard/navegacao_abas_wizard_consulta.jsp?numeroPagina=8" />


	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<html:hidden property="indicadorValidaCPFCNPJ" />
	<html:hidden property="indicadorClienteCPFCNPJValidado" />

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
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">&nbsp;</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="590" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td colspan="4">
					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr>
						    <td>
							    <table width="100%" align="center" bgcolor="#99CCFF" border="0">
								    <tr>
									    <td align="left" width="4%">
											<logic:equal name="ConsultarImovelActionForm" property="indicadorValidaCPFCNPJ" value="1">
												<logic:equal name="ConsultarImovelActionForm" property="indicadorClienteCPFCNPJValidado" value="1">
													<img border="0" width="25" height="25"
														src="<bean:message key="caminho.imagens"/>informacao.gif"
														onmouseover="this.T_BGCOLOR='whitesmoke';this.T_FONTCOLOR='green';this.T_LEFT=true;return escape( '${ConsultarImovelActionForm.hint2}' ); "/>
												</logic:equal>
												<logic:equal name="ConsultarImovelActionForm" property="indicadorClienteCPFCNPJValidado" value="2">
													<img border="0" width="25" height="25"
														src="<bean:message key="caminho.imagens"/>informacao.gif"
														onmouseover="this.T_BGCOLOR='whitesmoke';this.T_FONTCOLOR='red';this.T_LEFT=true;return escape( '${ConsultarImovelActionForm.hint2}' ); "/>
												</logic:equal>
											</logic:equal>
											<logic:equal name="ConsultarImovelActionForm" property="indicadorValidaCPFCNPJ" value="2">
												<img border="0" width="25" height="25"
													src="<bean:message key="caminho.imagens"/>informacao.gif"
													onmouseover="this.T_BGCOLOR='whitesmoke';this.T_LEFT=true;return escape( '${ConsultarImovelActionForm.hint2}' ); "/>
											</logic:equal>
									    </td>						    						
										<td align="center" width="96%"><strong>Dados do Imóvel<logic:present name="imovelExcluido" scope="request"><font color="#ff0000"> (Excluído)</font></logic:present></strong></td>
									</tr>
								</table>
							</td>
						</tr>

						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">
							<table width="100%" border="0">
								<tr>
									<td bordercolor="#000000" width="25%"><strong>Im&oacute;vel:<font
										color="#FF0000">*</font></strong></td>
									<td width="75%" colspan="3"><html:text
										property="idImovelDocumentosCobranca" maxlength="9" size="9"
										onkeypress="validaEnterComMensagem(event, 'consultarImovelWizardAction.do?action=exibirConsultarImovelDocumentosCobrancaAction&indicadorNovo=OK','idImovelDocumentosCobranca','Im&oacute;vel');return isCampoNumerico(event);" 
										/>
									<a
										href="javascript:pesquisarImovel();">
									<img width="23" height="21"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										border="0" title="Pesquisar Imóvel"/></a> <logic:present
										name="idImovelDocumentosCobrancaNaoEncontrado" scope="request">
										<html:text property="matriculaImovelDocumentosCobranca"
											size="40" readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000" 
											title="Localidade.Setor.Quadra.Lote.Sublote"/>

									</logic:present> <logic:notPresent
										name="idImovelDocumentosCobrancaNaoEncontrado" scope="request">
										<logic:present name="valorMatriculaImovelDocumentosCobranca"
											scope="request">
											<html:text property="matriculaImovelDocumentosCobranca"
												size="40" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" 
												title="Localidade.Setor.Quadra.Lote.Sublote"/>
										</logic:present>
										<logic:notPresent
											name="valorMatriculaImovelDocumentosCobranca" scope="request">
											<html:text property="matriculaImovelDocumentosCobranca"
												size="40" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" 
												title="Localidade.Setor.Quadra.Lote.Sublote"/>
										</logic:notPresent>
									</logic:notPresent> <a href="javascript:limparForm();"> <img
										src="<bean:message key="caminho.imagens"/>limparcampo.gif"
										border="0" title="Apagar" /></a></td>

								</tr>
								<tr>
									<td height="10">
									<div class="style9"><strong>Situação de Água:</strong></div>
									</td>
									<td><html:text property="situacaoAguaDocumentosCobranca"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="15" maxlength="15" /></td>
									<td width="90"><strong>Situação de Esgoto:</strong></td>
									<td width="120"><html:text
										property="situacaoEsgotoDocumentosCobranca" readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="15" maxlength="15" /></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td>
						<br>
					</td>
				</tr>
				
				
					<!--============================== Documento de Cobrança ===================================== -->
				<tr>
					<td>
					<table width="100%" bgcolor="#99CCFF">
						<tr bgcolor="#79bbfd">
							<td align="center" bgcolor="#79bbfd" height="20" colspan="10">
								<strong>Documento de Cobrança</strong>
				   			</td>
						</tr>
					</table>
					
					<div style="width: 100%; height: 150; overflow: auto;">
					<table width="100%" bgcolor="#99CCFF">
						<tr bgcolor="#99CCFF">
							<td align="center" width="50"><FONT style="font-size:9px" COLOR="#000000"><strong>DOC</strong></FONT></td>
							<td align="center" width="70"><FONT style="font-size:9px" COLOR="#000000"><strong>Ação de Cobrança</strong></FONT></td>
							<td align="center" width="50"><FONT style="font-size:9px" COLOR="#000000"><strong>Emissão</strong></FONT></td>
							<td align="center" width="50"><FONT style="font-size:9px" COLOR="#000000"><strong>Forma Emissão</strong></FONT></td>
							<td align="center" width="65"><FONT style="font-size:9px" COLOR="#000000"><strong>Tipo Doc.</strong></FONT></td>
							<td align="center" width="45"><FONT style="font-size:9px" COLOR="#000000"><strong>Imóvel/ Cliente</strong></FONT></td>
							<td align="center" width="40"><FONT style="font-size:9px" COLOR="#000000"><strong>Vl. Doc.</strong></FONT></td>
							<td align="center" width="30"><FONT style="font-size:9px" COLOR="#000000"><strong>Qtd. Itens</strong></FONT></td>
							<td align="center" width="30"><FONT style="font-size:9px" COLOR="#000000"><strong>Sit. OS</strong></FONT></td>
							<td align="center" width="60"><FONT style="font-size:9px" COLOR="#000000"><strong>Responsável</strong></FONT></td>
						</tr>

						<logic:present name="colecaoDocumentoCobranca">
							<%int cont = 1;%>
							<logic:iterate name="colecaoDocumentoCobranca"
								id="cobrancaDocumentoHelper" type="CobrancaDocumentoHelper">
								<%cont = cont + 1;
							if (cont % 2 == 0) {%>
								<tr bgcolor="#FFFFFF">
									<%} else {
							%>
								<tr bgcolor="#cbe5fe">
									<%}%>
									<td width="70" align="center">
										<font style="font-size:9px">
											<logic:empty name="cobrancaDocumentoHelper" property="idOrdemServico">
												<a
												href="javascript:abrirPopup('exibirDocumentoCobrancaItemAction.do?cobrancaDocumentosID=<%="" + cobrancaDocumentoHelper.getCobrancaDocumento().getId()%>', 400, 800);"
												title="<%="" + cobrancaDocumentoHelper.getCobrancaDocumento().getId()%>">${cobrancaDocumentoHelper.cobrancaDocumento.id}</a>
											</logic:empty>
											<logic:notEmpty name="cobrancaDocumentoHelper" property="idOrdemServico">
												<a
												href="javascript:abrirPopup('exibirDocumentoCobrancaItemAction.do?cobrancaDocumentosID=<%="" + cobrancaDocumentoHelper.getCobrancaDocumento().getId()%>&numeroOS=${cobrancaDocumentoHelper.idOrdemServico}', 400, 800);"
												title="<%="" + cobrancaDocumentoHelper.getCobrancaDocumento().getId()%>">${cobrancaDocumentoHelper.cobrancaDocumento.id}</a>
											</logic:notEmpty>
										</font>
									</td>
									<td width="95" align="left">
										<font style="font-size:9px">
											<logic:equal
												name="cobrancaDocumentoHelper"
												property="cobrancaDocumento.documentoEmissaoForma.id"
												value="<%= "" + gcom.cobranca.DocumentoEmissaoForma.CRONOGRAMA %>">
													${cobrancaDocumentoHelper.cobrancaDocumento.cobrancaAcaoAtividadeCronograma.cobrancaAcaoCronograma.cobrancaAcao.descricaoCobrancaAcao}&nbsp;
												</logic:equal> <logic:equal name="cobrancaDocumentoHelper"
												property="cobrancaDocumento.documentoEmissaoForma.id"
												value="<%= "" + gcom.cobranca.DocumentoEmissaoForma.EVENTUAL %>">
													${cobrancaDocumento.cobrancaAcaoAtividadeComando.cobrancaAcao.descricaoCobrancaAcao}
												</logic:equal> <logic:equal name="cobrancaDocumentoHelper"
												property="cobrancaDocumento.documentoEmissaoForma.id"
												value="<%= "" + gcom.cobranca.DocumentoEmissaoForma.INDIVIDUAL %>">
											</logic:equal> &nbsp;
										</font>
									</td>
									<td width="75" align="center">
										<font style="font-size:9px">
											<bean:write
											name="cobrancaDocumentoHelper"
											property="cobrancaDocumento.emissao"
											formatKey="datehour.format" />
										</font>
									</td>
									<td width="95" align="left">${cobrancaDocumentoHelper.cobrancaDocumento.documentoEmissaoForma.descricaoDocumentoEmissaoForma}</td>
									<td width="90" align="center">${cobrancaDocumentoHelper.cobrancaDocumento.documentoTipo.descricaoDocumentoTipo}</td>
									<td align="center" width="45">
										<font style="font-size:9px">
											<logic:present	name="cobrancaDocumentoHelper" property="cobrancaDocumento">
												<logic:equal name="cobrancaDocumentoHelper"  value="1" property="cobrancaDocumento.indicadorClienteImovel" >
					                        		I
					                        	</logic:equal>
					                        	<logic:equal name="cobrancaDocumentoHelper" value="2" property="cobrancaDocumento.indicadorClienteImovel" >
					                        		C
					                        	</logic:equal>
				                        	</logic:present>
										</font>
									</td>
									<td width="70" align="right">
										<font style="font-size:9px">
											<bean:write
												name="cobrancaDocumentoHelper"
												property="cobrancaDocumento.valorDocumento"
												formatKey="money.format" />
										</font>
									</td>
									<td width="60" align="center">${cobrancaDocumentoHelper.quantidadeItensCobrancaDocumento}</td>
									<td width="30" align="center">
										<font style="font-size:9px">
										 	<logic:notEmpty name="cobrancaDocumentoHelper" property="idOrdemServico">
										 	  ${cobrancaDocumentoHelper.situacaoOrdemServico}
											 </logic:notEmpty>
											 <logic:empty name="cobrancaDocumentoHelper" property="idOrdemServico">
											   &nbsp;
											 </logic:empty>
										</font>
									</td>
								<td align="center" width="60">
										<font style="font-size:9px">
											<logic:notEmpty name="cobrancaDocumentoHelper" property="cobrancaDocumento.usuario">
											   		<bean:write name="cobrancaDocumentoHelper"
																		property="cobrancaDocumento.usuario.nomeUsuario" />
											 </logic:notEmpty>
											 <logic:empty name="cobrancaDocumentoHelper" property="cobrancaDocumento.usuario">
											   &nbsp;
											 </logic:empty>
										 </font>
								</td>
							</tr>
							
							</logic:iterate>
						</logic:present>
					</table>
					</div>
					</td>
				</tr>
					<!--============================== FIM Documento de Cobrança ===================================== -->
					
				<tr>
					<td>
						<br>
					</td>
				</tr>
					
					<!--============================== EMISSÃO DE SEGUNDA VIA CONTAS ===================================== -->
				<tr>
					<td>
					<table width="100%" align="center" bgcolor="#90c7fc" border="0">
						<tr bgcolor="#79bbfd">
							<td align="center" bgcolor="#79bbfd" height="20" colspan="9">
								<strong>Emissão de Segunda Via Contas</strong>
							</td>
						</tr>
						<tr bordercolor="#000000">
							<td width="12%" bgcolor="#90c7fc" align="center">
							 <div align="center">Ref.</div>
							</td>
							<td width="20%" bgcolor="#90c7fc" align="center">
							 <div align="center">Cliente</div>
							</td>
							<td width="10%" bgcolor="#90c7fc" align="center">
								<div align="center">Cliente Imóvel ou Conta</div>
							</td>
							<td width="12%" bgcolor="#90c7fc" align="center">
								<div align="center">Valor da Conta</div>
							</td>
							<td width="16%" bgcolor="#90c7fc" align="center">
								<div align="center">Emissão</div>
							</td>
							<td width="12%" bgcolor="#90c7fc" align="center">
								<div align="center">Sit.</div>
							</td>
							<td width="20%" bgcolor="#90c7fc" align="center">
								<div align="center">Responsável</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
					<tr>
						<td width="100%" colspan="7">
							<div style="width: 100%; height: 102; overflow: auto;">
								<table width="100%" align="left" bgcolor="#99CCFF">
									<%int contConta = 0;%>
									<logic:present name="colecaoContaEmissao2Via" >
										<logic:iterate name="colecaoContaEmissao2Via" id="contaEmissao2Via"
														type="ContaEmissao2Via">
											
											<%contConta = contConta + 1;
											if (contConta % 2 == 0) {%>
											<tr bgcolor="#cbe5fe">
												<%} else {%>
											<tr bgcolor="#FFFFFF">
												<%}%>
												<td width="12%">
													<div align="center">
														<logic:present name="contaEmissao2Via" property="contaGeral">
														
															<logic:present name="contaEmissao2Via" property="contaGeral.conta">
																<a href="javascript:abrirPopup('exibirConsultarContaAction.do?tipoConsulta=conta&contaSemCodigoBarras=1&contaID=<%="" + contaEmissao2Via.getContaGeral().getId()%>' , 600, 800);"
																	title="<%="" + Util.formatarMesAnoReferencia(contaEmissao2Via.getContaGeral().getConta().getReferencia())%>">
																	
																	<span style="color: #000000;">
																	<%="" +	Util.formatarMesAnoReferencia(contaEmissao2Via.getContaGeral().getConta().getReferencia())%>
																	</span>
																</a>	
															</logic:present>
														

															<logic:present name="contaEmissao2Via" property="contaGeral.contaHistorico">
																<a href="javascript:abrirPopup('exibirConsultarContaAction.do?contaSemCodigoBarras=1&tipoConsulta=contaHistorico&contaID=<%="" + contaEmissao2Via.getContaGeral().getId()%>' , 600, 800);"
																	title="<%="" + Util.formatarMesAnoReferencia(contaEmissao2Via.getContaGeral().getContaHistorico().getAnoMesReferenciaContabil())%>">
																	<span style="color: #000000;">
																	<%="" +	Util.formatarMesAnoReferencia(contaEmissao2Via.getContaGeral().getContaHistorico().getAnoMesReferenciaConta())%>
																	</span>
																</a>
															</logic:present>
														</logic:present>
														 
														<logic:notPresent name="contaEmissao2Via" property="contaGeral"> 
														&nbsp;
													  	</logic:notPresent>			  	
												  	</div>
												</td>
												<td width="20%" align="left">
													<div align="left">
														<logic:present	name="contaEmissao2Via" property="cliente">
															<bean:write name="contaEmissao2Via"
																	property="cliente.nome" />
														</logic:present> 
													</div>
												</td>
												<td width="10%" align="left">
													<div align="center">
							                        	<logic:equal name="contaEmissao2Via"  value="1" property="indicadorClienteImovel" >
							                        		IMOVEL
							                        	</logic:equal>
							                        	<logic:notEqual name="contaEmissao2Via" value="1" property="indicadorClienteImovel" >
							                        		CONTA
							                        	</logic:notEqual>
													</div>
												</td>
												<td width="12%" align="left">
													<div align="right">
														
														
														
														<logic:present	name="contaEmissao2Via" property="contaGeral">

																<logic:present name="contaEmissao2Via" property="contaGeral.conta">
																	<%="" +contaEmissao2Via.getContaGeral().getConta().getValorTotalConta() %>
																</logic:present>
																
																<logic:present name="contaEmissao2Via" property="contaGeral.contaHistorico">
																	<%="" +contaEmissao2Via.getContaGeral().getContaHistorico().getValorTotalContaBigDecimal() %>
																</logic:present>														
														</logic:present>
														
														<logic:notPresent name="contaEmissao2Via" property="contaGeral"> 
														&nbsp;
													  	</logic:notPresent>
													</div>
												</td>
												<td width="16%" align="left">
													<div align="center">
														<logic:present name="contaEmissao2Via" property="dataEmissao">
															<%="" + Util.formatarDataComHora(contaEmissao2Via.getDataEmissao())%>
														</logic:present> 			
														<logic:notPresent name="contaEmissao2Via" property="dataEmissao"> 
														&nbsp;
													  	</logic:notPresent>
													</div>
												</td>
												<td width="12%" align="left">
													<div align="center">
														<logic:present	name="contaEmissao2Via" property="contaGeral">
														
															<logic:present	name="contaEmissao2Via" property="contaGeral.conta">
																<bean:write name="contaEmissao2Via"
																		property="contaGeral.conta.debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao" />
															</logic:present>
															
															<logic:notPresent	name="contaEmissao2Via" property="contaGeral.conta">
																<bean:write name="contaEmissao2Via"
																		property="contaGeral.contaHistorico.debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao" />
															</logic:notPresent>
															
														</logic:present> 
													</div>
												</td>
												<td width="20%" align="left">
													<div align="left">
														<logic:present	name="contaEmissao2Via" property="usuario">
															<bean:write name="contaEmissao2Via"
																	property="usuario.nomeUsuario" />
														</logic:present> 
													</div>
												</td>
											</tr>
										</logic:iterate>
									</logic:present>
								</table>
							</div>
						</td>
					</tr>
					
					<!--============================== FIM EMISSÃO DE SEGUNDA VIA CONTAS ===================================== -->
					
					</table>
					<table width="590" border="0">
						<tr>
							<td>
							<div align="right"><jsp:include
								page="/jsp/util/wizard/navegacao_botoes_wizard_consulta.jsp?numeroPagina=8" />
							</div>
							</td>
						</tr>
					</table>
		</table>				
		
	
	<%@ include file="/jsp/util/rodape.jsp"%>
    <%@ include file="/jsp/util/tooltip.jsp"%>	
</html:form>
</body>
</html:html>
