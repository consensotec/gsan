<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gsan.util.Util"%>
<%@ page import="gsan.faturamento.conta.Conta" isELIgnored="false"%>
<%@ page import="gsan.faturamento.conta.ContaHistorico"
	isELIgnored="false"%>
<%@ page import="gsan.util.ConstantesSistema" isELIgnored="false"%>
<%@ page import="java.util.Collection" isELIgnored="false"%>
<%@ page import="gsan.faturamento.debito.DebitoACobrar" isELIgnored="false"%>
<%@ page import="gsan.faturamento.debito.DebitoACobrarHistorico" isELIgnored="false"%>
<%@ page import="gsan.faturamento.credito.CreditoARealizar" isELIgnored="false"%>
<%@ page import="gsan.faturamento.credito.CreditoARealizarHistorico" isELIgnored="false"%>
<%@ page import="gsan.arrecadacao.pagamento.GuiaPagamentoHistorico" isELIgnored="false"%>
<%@ page import="gsan.arrecadacao.pagamento.GuiaPagamento" isELIgnored="false"%>	

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>

<html:javascript staticJavascript="false"
	formName="ConsultarHistoricoFaturamentoActionForm" />

<script language="JavaScript">

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    	var form = document.forms[0];
      	consultarImovelAjax();
   		if (tipoConsulta == 'imovel') {
      		form.idImovelHistoricoFaturamento.value = codigoRegistro;
	      	form.matriculaImovelHistoricoFaturamento.value = descricaoRegistro;
	      	form.matriculaImovelHistoricoFaturamento.style.color = "#000000";
		  	form.action = 'consultarImovelWizardAction.do?action=exibirConsultarImovelHistoricoFaturamentoAction&indicadorNovo=OK'
		  	form.submit();
   		}
	}

	function limparForm(){
   		var form = document.forms[0];
   		form.idImovelHistoricoFaturamento.value = "";
   		consultarImovelAjax();   		
		
		form.action = 'consultarImovelWizardAction.do?action=exibirConsultarImovelHistoricoFaturamentoAction&limparForm=OK'
		form.submit();
	}
	

	function verificaExibicaoRelatorioHistorico(){
		var form = document.forms[0];
		
		if (form.idImovelHistoricoFaturamento.value.length > 0 && form.matriculaImovelHistoricoFaturamento.value.length > 0) {
			toggleBoxCaminho('demodiv',1,'gerarRelatorioHistoricoFaturamentoImovelAction.do');
		} else {
			alert('Informe Im�vel');
		}
	
	}
	
	function habilitaMatricula() {
		var form = document.forms[0];
		
		if (form.idImovelHistoricoFaturamento.value != null && form.matriculaImovelHistoricoFaturamento.value != null && 
			form.matriculaImovelHistoricoFaturamento.value != "" && form.matriculaImovelHistoricoFaturamento.value != "IM�VEL INEXISTENTE"){
		
			form.idImovelHistoricoFaturamento.disabled = true;
		} else {
			form.idImovelHistoricoFaturamento.disabled = false;
		}
	}
	
	function pesquisarImovel() {
		var form = document.forms[0];
	 	
	 	if (form.idImovelHistoricoFaturamento.disabled ) {
	 		alert("Para realizar uma pesquisa de im�vel � necess�rio apagar o im�vel atual.")
		} else {
			abrirPopup('exibirPesquisarImovelAction.do', 400, 800)
		}
	}

	function consultarImovelAjax(){
		var form = document.forms[0];

		if(form.idImovelHistoricoFaturamento != null && form.idImovelHistoricoFaturamento.value != ""){
			$.ajax({
					   type: "POST",
					   url: "consultarImovelAjaxAction.do?idImovel="+form.idImovelHistoricoFaturamento.value,
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
 
</SCRIPT>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:habilitaMatricula();setarFoco('idImovelHistoricoFaturamento');consultarImovelAjax();">

<html:form action="/exibirConsultarImovelAction.do"
	name="ConsultarImovelActionForm"
	type="gsan.gui.cadastro.imovel.ConsultarImovelActionForm" 
	method="post"
	onsubmit="return validateConsultarImovelActionForm(this);">

	<jsp:include
		page="/jsp/util/wizard/navegacao_abas_wizard_consulta.jsp?numeroPagina=4" />

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<html:hidden property="indicadorValidaCPFCNPJ" />
	<html:hidden property="indicadorClienteCPFCNPJValidado" />
	
	<table width="768" border="0" cellspacing="5" cellpadding="0">
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

			<td width="625" valign="top" class="centercoltext">
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
			
			<table width="100%" border="0">
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
										<td align="center" width="96%"><strong>Dados do Im�vel<logic:present name="imovelExcluido" scope="request"><font color="#ff0000"> (Exclu�do)</font></logic:present></strong></td>
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
										property="idImovelHistoricoFaturamento" maxlength="9" size="9"
										onkeypress="validaEnterComMensagem(event, 'consultarImovelWizardAction.do?action=exibirConsultarImovelHistoricoFaturamentoAction&indicadorNovo=OK','idImovelHistoricoFaturamento','Im&oacute;vel');return isCampoNumerico(event);" 
										/>
									<a
										href="javascript:pesquisarImovel();" title="Pesquisar Im�vel">
									<img width="23" height="21"
										src="<bean:message key="caminho.imagens"/>pesquisa.gif"
										border="0" title="Pesquisar Im�vel"/></a> <logic:present
										name="idImovelHistoricoFaturamentoNaoEncontrado"
										scope="request">
										<html:text property="matriculaImovelHistoricoFaturamento"
											size="40" readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000" 
											title="Localidade.Setor.Quadra.Lote.Sublote"/>

									</logic:present> <logic:notPresent
										name="idImovelHistoricoFaturamentoNaoEncontrado"
										scope="request">
										<logic:present name="valorMatriculaImovelHistoricoFaturamento"
											scope="request">
											<html:text property="matriculaImovelHistoricoFaturamento"
												size="40" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000" 
												title="Localidade.Setor.Quadra.Lote.Sublote"/>
										</logic:present>
										<logic:notPresent
											name="valorMatriculaImovelHistoricoFaturamento"
											scope="request">
											<html:text property="matriculaImovelHistoricoFaturamento"
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
									<div class="style9"><strong>Situa��o de �gua:</strong></div>
									</td>
									<td><html:text property="situacaoAguaHistoricoFaturamento"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="15" maxlength="15" /></td>
									<td width="100"><strong>Situa��o de Esgoto:</strong></td>
									<td width="120"><html:text
										property="situacaoEsgotoHistoricoFaturamento" readonly="true"
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
					<td colspan="3" height="10"></td>
				</tr>
				<tr>
					<td height="10">&nbsp;</td>
					<td></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3" height="5"></td>
				</tr>

				<tr>
					<td colspan="3">
					<table width="100%" border="0">
						<tr>
							<td colspan="5">
							<table width="100%" border="0" bgcolor="#99C7FC">
								<tr bgcolor="#79bbfd">
									<td bgcolor="#79bbfd" height="20" colspan="9"><strong>Hist�rico
									das Contas do Im�vel</strong></td>
								</tr>
								<%if ((request.getAttribute("colecaoContaImovel") != null)
					              && (((Collection) request.getAttribute("colecaoContaImovel")).size() <= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONTAS_DEBITO)) {%>
								
								<tr bordercolor="#FFFFFF" bgcolor="#99C7FC">
									
									<td width="5%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>M&ecirc;s/Ano</strong></font></div>
									</td>

									<td width="15%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Vencimento</strong></font></div>
									</td>

									<td width="60%" colspan="6" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>VALORES
									DA CONTA</strong></font></div>
									</td>

									<td width="10%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Situa&ccedil;&atilde;o</strong></font></div>
									</td>


								</tr>
								<tr bordercolor="#FFFFFF" bgcolor="#cbe5fe">

									<td width="10%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>&Aacute;gua</strong></font></div>
									</td>

									<td width="9%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"></div>
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Esgoto</strong></font></div>
									</td>
									<td width="10%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>D&eacute;bitos</strong></font></div>
									</td>
									<td width="9%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Cr&eacute;ditos</strong></font></div>
									</td>
									
									<td width="10%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Impostos</strong></font></div>
									</td>
									
									
									<td width="15%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>TOTAL</strong></font></div>
									</td>
								</tr>
								<%} else {

			%>
								<tr bordercolor="#FFFFFF" bgcolor="#99C7FC">


									<td width="5%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>M&ecirc;s/Ano</strong></font></div>
									</td>

									<td width="15%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Vencimento</strong></font></div>
									</td>

									<td width="60%" colspan="6" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>VALORES
									DA CONTA</strong></font></div>
									</td>

									<td width="10%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Situa&ccedil;&atilde;o</strong></font></div>
									</td>


								</tr>
								<tr bordercolor="#FFFFFF" bgcolor="#cbe5fe">

									<td width="10%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>&Aacute;gua</strong></font></div>
									</td>

									<td width="10%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"></div>
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Esgoto</strong></font></div>
									</td>
									<td width="10%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>D&eacute;bitos</strong></font></div>
									</td>
									<td width="10%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Cr&eacute;ditos</strong></font></div>
									</td>									
									
								    <td width="10%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Impostos</strong></font></div>
									</td>
									
									
									<td width="15%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>TOTAL</strong></font></div>
									</td>
								</tr>
								<%	}	%>
							</table>

							<div style="width: 100%; height: 100; overflow: auto;">
							<table width="100%" bgcolor="#99C7FC">

								<!-- Esquema pra exibir as contas =========================== -->
								<logic:present name="colecaoContaImovel">

									<%int cont = 0;%>
									<logic:iterate name="colecaoContaImovel" id="conta"
										type="Conta">

										<%cont = cont + 1;
										if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
											<%	} else {	%>
										<tr bgcolor="#FFFFFF">
											<%	}	%>
											<logic:present name="conta" property="contaMotivoRevisao">
												
												<td width="5%">
													<font color="#FF0000" 
														style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> 
														<a href="javascript:abrirPopup('exibirConsultarContaAction.do?tipoConsulta=conta&contaSemCodigoBarras=1&contaID=<%="" + conta.getId()%>' , 600, 800);"
															title="<%="" + Util.formatarMesAnoReferencia(conta.getReferencia())%>"><span
															style="color: #FF0000;">
															<%=""+ Util.formatarMesAnoReferencia(conta.getReferencia())%></span>
														</a> 
													</font>
												</td>

												<td width="15%">
												
												<div align="center"><logic:present name="conta"
													property="dataVencimentoConta">
													<font color="#FF0000" style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <span
														style="color: #FF0000;"><%=""
					+ Util.formatarData(conta.getDataVencimentoConta())%></span> </font>
												</logic:present> <logic:notPresent name="conta"
													property="dataVencimentoConta">
													<font color="#FF0000" style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent></div>
												</td>

												<td width="10%">
												<div align="right"><font color="#FF0000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
													name="conta" property="valorAgua" formatKey="money.format" />
												</font></div>
												</td>

												<td width="10%">
												<div align="right"><font color="#FF0000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
													name="conta" property="valorEsgoto"
													formatKey="money.format" /> </font></div>
												</td>

												<td width="10%">
												<div align="right"><logic:equal name="conta"
													property="debitos" value="0">
													<font color="#FF0000" style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
														name="conta" property="debitos" formatKey="money.format" />
													</font>
												</logic:equal> <logic:notEqual name="conta"
													property="debitos" value="0">
													<a
														href="javascript:abrirPopup('exibirConsultarDebitoCobradoAction.do?tipoConsulta=conta&contaID=<%="" + conta.getId()%>' , 500, 800);"
														title="<%="" + Util.formatarMesAnoReferencia(conta.getReferencia())%>">
													<font color="#FF0000" style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
														name="conta" property="debitos" formatKey="money.format" />
													</font> </a>
												</logic:notEqual></div>
												</td>

												<td width="10%">
												<div align="right"><logic:present name="conta"
													property="valorCreditos">
													<logic:equal name="conta" property="valorCreditos"
														value="0">
														<font color="#FF0000" style="font-size:9px"
															face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
															name="conta" property="valorCreditos"
															formatKey="money.format" /> </font>
													</logic:equal>
													<logic:notEqual name="conta" property="valorCreditos"
														value="0">
														<a
															href="javascript:abrirPopup('exibirConsultarCreditoRealizadoAction.do?tipoConsulta=conta&contaID=<%="" + conta.getId()%>' , 500, 800);"
															title="<%="" + Util.formatarMesAnoReferencia(conta.getReferencia())%>">
														<font color="#FF0000" style="font-size:9px"
															face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
															name="conta" property="valorCreditos"
															formatKey="money.format" /> </font> </a>
													</logic:notEqual>
												</logic:present> <logic:notPresent name="conta"
													property="valorCreditos">
													<font color="#FF0000" style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent></div>
												</td>



	                                          <td width="10%">
												<div align="right"><logic:equal name="conta"
													property="valorImposto" value="0">
													<font color="#FF0000" style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
														name="conta" property="valorImposto" formatKey="money.format" />
													</font>
												</logic:equal> <logic:notEqual name="conta"
													property="valorImposto" value="0">													
													<font color="#FF0000" style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
														name="conta" property="valorImposto" formatKey="money.format" />
													</font> 
												</logic:notEqual></div>
												</td>




												<td width="15%">
												<div align="right"><font color="#000000"
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <span
													style="color: #FF0000;"><%="" + Util.formatarMoedaReal(conta.getValorTotal())%></span> </font></div>
												</td>

												<td width="7%">
												<div align="left" title="${conta.debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao}"><logic:present name="conta"
													property="debitoCreditoSituacaoAtual">
													<font color="#FF0000" style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
														name="conta"
														property="debitoCreditoSituacaoAtual.descricaoAbreviada" />
													</font>
												</logic:present> <logic:notPresent name="conta"
													property="debitoCreditoSituacaoAtual">
													<font color="#FF0000" style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent></div>
												</td>
											</logic:present>

											<logic:notPresent name="conta" property="contaMotivoRevisao">
											<td width="5%"><font color="#000000" style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <a
												href="javascript:abrirPopup('exibirConsultarContaAction.do?tipoConsulta=conta&contaSemCodigoBarras=1&contaID=<%="" + conta.getId()%>' , 600, 800);"
												title="<%="" + Util.formatarMesAnoReferencia(conta.getReferencia())%>"><span
												style="color: #000000;"><%=""
					+ Util.formatarMesAnoReferencia(conta.getReferencia())%></span></a> </font></td>

											<td width="15%">
											<div align="center"><logic:present name="conta"
												property="dataVencimentoConta">
												<font color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <span
													style="color: #000000;"><%=""
					+ Util.formatarData(conta.getDataVencimentoConta())%></span> </font>
											</logic:present> <logic:notPresent name="conta"
												property="dataVencimentoConta">
												<font color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
											</logic:notPresent></div>
											</td>

											<td width="10%">
											<div align="right"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
												name="conta" property="valorAgua" formatKey="money.format" />
											</font></div>
											</td>

											<td width="9%">
											<div align="right"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
												name="conta" property="valorEsgoto" formatKey="money.format" />
											</font></div>
											</td>

											<td width="9%">
											<div align="right"><logic:equal name="conta"
												property="debitos" value="0">
												<font color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
													name="conta" property="debitos" formatKey="money.format" />
												</font>
											</logic:equal> <logic:notEqual name="conta"
												property="debitos" value="0">
												<a
													href="javascript:abrirPopup('exibirConsultarDebitoCobradoAction.do?tipoConsulta=conta&contaID=<%="" + conta.getId()%>' , 500, 800);"
													title="<%="" + Util.formatarMesAnoReferencia(conta.getReferencia())%>">
												<font color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
													name="conta" property="debitos" formatKey="money.format" />
												</font> </a>
											</logic:notEqual></div>
											</td>

											<td width="9%">
											<div align="right"><logic:present name="conta"
												property="valorCreditos">
												<logic:equal name="conta" property="valorCreditos" value="0">
													<font color="#000000" style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
														name="conta" property="valorCreditos"
														formatKey="money.format" /> </font>
												</logic:equal>
												<logic:notEqual name="conta" property="valorCreditos"
													value="0">
													<a
														href="javascript:abrirPopup('exibirConsultarCreditoRealizadoAction.do?tipoConsulta=conta&contaID=<%="" + conta.getId()%>' , 500, 800);"
														title="<%="" + Util.formatarMesAnoReferencia(conta.getReferencia())%>">
													<font color="#000000" style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
														name="conta" property="valorCreditos"
														formatKey="money.format" /> </font> </a>
												</logic:notEqual>
											</logic:present> <logic:notPresent name="conta"
												property="valorCreditos">
												<font color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
											</logic:notPresent></div>
											</td>
											
											
											<td width="9%">
											<div align="right"><logic:equal name="conta"
												property="valorImposto" value="0">
												<font color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
													name="conta" property="valorImposto" formatKey="money.format" />
												</font>
											</logic:equal> <logic:notEqual name="conta"
												property="valorImposto" value="0">											
												<font color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
													name="conta" property="valorImposto" formatKey="money.format" />
												</font> 
											</logic:notEqual></div>
											</td>
											
											
											
											
											
											

											<td width="15%">
											<div align="right"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <span
												style="color: #000000;"><%="" + Util.formatarMoedaReal(conta.getValorTotal())%></span> </font></div>
											</td>

											<td width="7%">
											<div align="left" title="${conta.debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao}"><logic:present name="conta"
												property="debitoCreditoSituacaoAtual">
												<font color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
													name="conta"
													property="debitoCreditoSituacaoAtual.descricaoAbreviada" /> 
												</font>
											</logic:present> <logic:notPresent name="conta"
												property="debitoCreditoSituacaoAtual">
												<font color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
											</logic:notPresent></div>
											</td>
											</logic:notPresent>

										</tr>
									</logic:iterate>
								</logic:present>
								<!-- Fim do Esquema pra exibir as contas =========================== -->

								<!-- Esquema pra exibir as contas do hist�rico =========================== -->
								<logic:present name="colecaoContaHistoricoImovel">

									<tr>
										<td colspan="8">
											<div align="center">
												<font color="#000000" 
													style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif">
												<strong>-----------------Hist�rico-----------------</strong>
												</font>
											</div>
										</td>
									</tr>

									<%int cont1 = 0;%>
									<logic:iterate name="colecaoContaHistoricoImovel"
										id="contaHistorico" type="ContaHistorico">

										<%cont1 = cont1 + 1;
			if (cont1 % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
											<%} else {

			%>
										<tr bgcolor="#FFFFFF">
											<%}%>
											
											<logic:present name="contaHistorico" property="contaMotivoRevisao">
											<td width="5%"><font color="#FF0000" style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <a
												href="javascript:abrirPopup('exibirConsultarContaAction.do?contaSemCodigoBarras=1&tipoConsulta=contaHistorico&contaID=<%="" + contaHistorico.getId()%>' , 600, 800);"
												title="<%="" + Util.formatarMesAnoReferencia(contaHistorico.getAnoMesReferenciaContabil())%>"><span
												style="color: #FF0000;"><%=""
					+ Util.formatarMesAnoReferencia(contaHistorico
							.getAnoMesReferenciaContabil())%></span></a> </font></td>

											<td width="15%">
											<div align="center"><logic:present name="contaHistorico"
												property="dataVencimentoConta">
												<font color="#FF0000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <span
													style="color: #FF0000;"><%=""
					+ Util
							.formatarData(contaHistorico
									.getDataVencimentoConta())%></span> </font>
											</logic:present> <logic:notPresent name="contaHistorico"
												property="dataVencimentoConta">
												<font color="#FF0000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
											</logic:notPresent></div>
											</td>

											<td width="10%">
											<div align="right"><font color="#FF0000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
												name="contaHistorico" property="valorAgua"
												formatKey="money.format" /> </font></div>
											</td>

											<td width="9%">
											<div align="right"><font color="#FF0000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
												name="contaHistorico" property="valorEsgoto"
												formatKey="money.format" /> </font></div>
											</td>

											<td width="10%">
											<div align="right"><logic:equal name="contaHistorico"
												property="valorDebitos" value="0">
												<font color="#FF0000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
													name="contaHistorico" property="valorDebitos"
													formatKey="money.format" /> </font>
											</logic:equal> <logic:notEqual name="contaHistorico"
												property="valorDebitos" value="0">
												<a
													href="javascript:abrirPopup('exibirConsultarDebitoCobradoAction.do?tipoConsulta=contaHistorico&contaID=<%="" + contaHistorico.getId()%>' , 500, 800);"
													title="<%="" + Util.formatarMesAnoReferencia(contaHistorico.getAnoMesReferenciaConta())%>">
												<font color="#FF0000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
													name="contaHistorico" property="valorDebitos"
													formatKey="money.format" /> </font> </a>
											</logic:notEqual></div>
											</td>

											<td width="10%">
											<div align="right"><logic:present name="contaHistorico"
												property="valorCreditos">
												<logic:equal name="contaHistorico" property="valorCreditos"
													value="0">
													<font color="#FF0000" style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
														name="contaHistorico" property="valorCreditos"
														formatKey="money.format" /> </font>
												</logic:equal>
												<logic:notEqual name="contaHistorico"
													property="valorCreditos" value="0">
													<a
														href="javascript:abrirPopup('exibirConsultarCreditoRealizadoAction.do?tipoConsulta=contaHistorico&contaID=<%="" + contaHistorico.getId()%>' , 500, 800);"
														title="<%="" + Util.formatarMesAnoReferencia(contaHistorico.getAnoMesReferenciaConta())%>">
													<font color="#FF0000" style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
														name="contaHistorico" property="valorCreditos"
														formatKey="money.format" /> </font> </a>
												</logic:notEqual>
											</logic:present> <logic:notPresent name="contaHistorico"
												property="valorCreditos">
												<font color="#FF0000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
											</logic:notPresent></div>
											</td>
											
											
											
											<td width="10%">
											<div align="right"><logic:equal name="contaHistorico"
												property="valorImposto" value="0">
												<font color="#FF0000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
													name="contaHistorico" property="valorImposto"
													formatKey="money.format" /> </font>
											</logic:equal> <logic:notEqual name="contaHistorico"
												property="valorImposto" value="0">											
												<font color="#FF0000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
													name="contaHistorico" property="valorImposto"
													formatKey="money.format" /> </font> 
											</logic:notEqual></div>
											</td>
											
											
											
											
											
											
											

											<td width="15%">
											<div align="right"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <span
												style="color: #FF0000;"><%=""
					+ Util.formatarMoedaReal(contaHistorico.getValorTotal())%></span> </font></div>
											</td>

											<td width="7%">
											<div align="left" title="${contaHistorico.debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao}"><logic:present name="contaHistorico"
												property="debitoCreditoSituacaoAtual">
												<font color="#FF0000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
													name="contaHistorico"
													property="debitoCreditoSituacaoAtual.descricaoAbreviada" />
												</font>
											</logic:present> <logic:notPresent name="contaHistorico"
												property="debitoCreditoSituacaoAtual">
												<font color="#FF0000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
											</logic:notPresent></div>
											</td>
											</logic:present>
											
											<logic:notPresent name="contaHistorico" property="contaMotivoRevisao">
												<td width="5%"><font color="#000000" style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <a
												href="javascript:abrirPopup('exibirConsultarContaAction.do?contaSemCodigoBarras=1&tipoConsulta=contaHistorico&contaID=<%="" + contaHistorico.getId()%>' , 600, 800);"
												title="<%="" + Util.formatarMesAnoReferencia(contaHistorico.getAnoMesReferenciaContabil())%>"><span
												style="color: #000000;"><%=""
					+ Util.formatarMesAnoReferencia(contaHistorico
							.getAnoMesReferenciaContabil())%></span></a> </font></td>

											<td width="15%">
											<div align="center"><logic:present name="contaHistorico"
												property="dataVencimentoConta">
												<font color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <span
													style="color: #000000;"><%=""
					+ Util
							.formatarData(contaHistorico
									.getDataVencimentoConta())%></span> </font>
											</logic:present> <logic:notPresent name="contaHistorico"
												property="dataVencimentoConta">
												<font color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
											</logic:notPresent></div>
											</td>

											<td width="10%">
											<div align="right"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
												name="contaHistorico" property="valorAgua"
												formatKey="money.format" /> </font></div>
											</td>

											<td width="10%">
											<div align="right"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
												name="contaHistorico" property="valorEsgoto"
												formatKey="money.format" /> </font></div>
											</td>

											<td width="10%">
											<div align="right"><logic:equal name="contaHistorico"
												property="valorDebitos" value="0">
												<font color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
													name="contaHistorico" property="valorDebitos"
													formatKey="money.format" /> </font>
											</logic:equal> <logic:notEqual name="contaHistorico"
												property="valorDebitos" value="0">
												<a
													href="javascript:abrirPopup('exibirConsultarDebitoCobradoAction.do?tipoConsulta=contaHistorico&contaID=<%="" + contaHistorico.getId()%>' , 500, 800);"
													title="<%="" + Util.formatarMesAnoReferencia(contaHistorico.getAnoMesReferenciaConta())%>">
												<font color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
													name="contaHistorico" property="valorDebitos"
													formatKey="money.format" /> </font> </a>
											</logic:notEqual></div>
											</td>

											<td width="10%">
											<div align="right"><logic:present name="contaHistorico"
												property="valorCreditos">
												<logic:equal name="contaHistorico" property="valorCreditos"
													value="0">
													<font color="#000000" style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
														name="contaHistorico" property="valorCreditos"
														formatKey="money.format" /> </font>
												</logic:equal>
												<logic:notEqual name="contaHistorico"
													property="valorCreditos" value="0">
													<a
														href="javascript:abrirPopup('exibirConsultarCreditoRealizadoAction.do?tipoConsulta=contaHistorico&contaID=<%="" + contaHistorico.getId()%>' , 500, 800);"
														title="<%="" + Util.formatarMesAnoReferencia(contaHistorico.getAnoMesReferenciaConta())%>">
													<font color="#000000" style="font-size:9px"
														face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
														name="contaHistorico" property="valorCreditos"
														formatKey="money.format" /> </font> </a>
												</logic:notEqual>
											</logic:present> <logic:notPresent name="contaHistorico"
												property="valorCreditos">
												<font color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
											</logic:notPresent></div>
											</td>
											
											
												<td width="10%">
											<div align="right"><logic:equal name="contaHistorico"
												property="valorImposto" value="0">
												<font color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
													name="contaHistorico" property="valorImposto"
													formatKey="money.format" /> </font>
											</logic:equal> <logic:notEqual name="contaHistorico"
												property="valorImposto" value="0">												
												<font color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
													name="contaHistorico" property="valorImposto"
													formatKey="money.format" /> </font> 
											</logic:notEqual></div>
											</td>
											
											
											

											<td width="15%">
											<div align="right"><font color="#000000"
												style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <span
												style="color: #000000;"><%=""
					+ Util.formatarMoedaReal(contaHistorico.getValorTotal())%></span> </font></div>
											</td>

											<td width="7%">
											<div align="left" title="${contaHistorico.debitoCreditoSituacaoAtual.descricaoDebitoCreditoSituacao}"><logic:present name="contaHistorico"
												property="debitoCreditoSituacaoAtual">
												<font color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
													name="contaHistorico"
													property="debitoCreditoSituacaoAtual.descricaoAbreviada" />
												</font>
											</logic:present> <logic:notPresent name="contaHistorico"
												property="debitoCreditoSituacaoAtual">
												<font color="#000000" style="font-size:9px"
													face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
											</logic:notPresent></div>
											</td>
											</logic:notPresent>

										</tr>
									</logic:iterate>
								</logic:present>
								<!-- Fim do Esquema pra exibir as contas do hist�rico =========================== -->

							</table>
							</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				
				
				
				
				<tr>
				<td colspan="3">&nbsp;</td>
				</tr>
				
				
				<!-- inicio da tabela Debito A Cobrar -->
						
						<tr>
							<td colspan="4">
							<table width="100%" border="0" bgcolor="#99C7FC">
								<tr bgcolor="#79bbfd">
									<td bgcolor="#79bbfd" height="20" colspan="8">
									<strong>Hist�rico dos D�bitos A Cobrar do Im�vel</strong></td>
								</tr>
								
								<%if (session.getAttribute("tamanhoColecaoDebitos") != null && (Integer)session.getAttribute("tamanhoColecaoDebitos") <= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONTAS_DEBITO) {%>
								
								<tr bordercolor="#FFFFFF" bgcolor="#99C7FC">
									<td width="30%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Tipo do D�bito</strong></font></div>
									</td>

									<td width="10%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>M�s/Ano Refer�ncia</strong></font></div>
									</td>

									<td width="10%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>M�s/Ano Cobran�a</strong></font></div>
									</td>
									
									<td width="27%" colspan="3" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Parcelas </strong></font></div>
									</td>
									
									
									<td width="15%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Valor D�bito</strong></font></div>
									</td>

									<td width="8%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Situa&ccedil;&atilde;o</strong></font></div>
									</td>



								</tr>
								<tr bordercolor="#FFFFFF" bgcolor="#cbe5fe">

									<td width="9%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Cobradas</strong></font></div>
									</td>

									<td width="9%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"></div>
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Total</strong></font></div>
									</td>
									<td width="9%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>B�nus</strong></font></div>
									</td>
								</tr>
								<%}else{ %>
								<tr bordercolor="#FFFFFF" bgcolor="#99C7FC">


									<td width="29%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Tipo do D�bito</strong></font></div>
									</td>

									<td width="10%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>M�s/Ano Refer�ncia</strong></font></div>
									</td>

									<td width="10%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>M�s/Ano Cobran�a</strong></font></div>
									</td>
									
									<td width="26%" colspan="3" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Parcelas </strong></font></div>
									</td>
									
									
									<td width="15%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Valor D�bito</strong></font></div>
									</td>

									<td width="10%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Situa&ccedil;&atilde;o</strong></font></div>
									</td>

								</tr>
								<tr bordercolor="#FFFFFF" bgcolor="#cbe5fe">

									<td width="9%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Cobradas</strong></font></div>
									</td>

									<td width="8%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"></div>
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Total</strong></font></div>
									</td>
									<td width="9%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>B�nus</strong></font></div>
									</td>
								</tr>
								<%} %>
							</table>

							<%if (session.getAttribute("tamanhoColecaoDebitos") != null && (Integer)session.getAttribute("tamanhoColecaoDebitos") > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONTAS_DEBITO) {%>
							<div style="width: 100%; height: 65; overflow: auto;">
							<%} %>
							<table width="100%" bgcolor="#99C7FC">

								<!-- Esquema pra exibir os debitos =========================== -->
								<logic:present name="colecaoDebitoACobrarImovel">

									<%int cont2 = 0;%>
									<logic:iterate name="colecaoDebitoACobrarImovel" id="debitoacobrar"	type="DebitoACobrar">

										<%cont2 = cont2 + 1;
									if (cont2 % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
											<%} else {

									%>
										<tr bgcolor="#FFFFFF">
											<%}%>
											<td width="30%"><font color="#000000" style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <a
											href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=<bean:define name="debitoacobrar" property="imovel" id="imovel" /><bean:write name="imovel" property="id" />&debitoID=<bean:write name="debitoacobrar" property="id" />', 570, 720);">
											<bean:define name="debitoacobrar" property="debitoTipo"	id="debitoTipo" /> 
											<logic:notEmpty name="debitoTipo" property="descricao">
												<bean:write name="debitoTipo" property="descricao" />
											</logic:notEmpty> </a>
											</font></td>

											<td width="10%">
											<div align="center">
												<logic:present name="debitoacobrar" property="anoMesReferenciaDebito">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<span style="color: #000000;"><%=""	+ Util.formatarAnoMesParaMesAno(debitoacobrar.getAnoMesReferenciaDebito())%></span>
													</font>
												</logic:present> 
												<logic:notPresent name="debitoacobrar" property="anoMesReferenciaDebito">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent>
											</div>
											</td>
											
											<td width="10%">
											<div align="center">
												<logic:present name="debitoacobrar" property="anoMesCobrancaDebito">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<span style="color: #000000;"><%=""	+ Util.formatarAnoMesParaMesAno(debitoacobrar.getAnoMesCobrancaDebito())%></span>
													</font>
												</logic:present> 
												<logic:notPresent name="debitoacobrar" property="anoMesCobrancaDebito">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent>
											</div>
											</td>

											<td width="9%">
											<div align="center">
												<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
													<bean:write	name="debitoacobrar" property="numeroPrestacaoCobradas" />
												</font>
											</div>
											</td>

											<td width="9%">
											<div align="center">
												<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
													<bean:write	name="debitoacobrar" property="numeroPrestacaoDebito" />
												</font>
											</div>
											</td>
											
											<td width="9%">
											<div align="center">
												<logic:present name="debitoacobrar" property="numeroParcelaBonus">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<bean:write	name="debitoacobrar" property="numeroParcelaBonus" />
													</font>
												</logic:present> 
												<logic:notPresent name="debitoacobrar" property="numeroParcelaBonus">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent>
											</div>
											</td>

											<td width="15%">
											<div align="right">
												<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
													<bean:write	name="debitoacobrar" property="valorDebito" formatKey="money.format" />
												</font>
											</div>
											</td>

											<td width="8%">
											<div align="left">
												<logic:present name="debitoacobrar"	property="debitoCreditoSituacaoAtual">
													<font color="#000000" style="font-size:9px"	face="Verdana, Arial, Helvetica, sans-serif"> 
														<bean:write name="debitoacobrar" property="debitoCreditoSituacaoAtual.descricaoAbreviada" />
													</font>
												</logic:present> 
												<logic:notPresent name="debitoacobrar" property="debitoCreditoSituacaoAtual">
													<font color="#000000" style="font-size:9px"	face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent>
											</div>
											</td>

										</tr>
									</logic:iterate>
								</logic:present>
								<!-- Fim do Esquema pra exibir os debitos =========================== -->



								<!-- Esquema pra exibir os debitos do hist�rico =========================== -->
								<logic:present name="colecaoDebitoACobrarHistoricoImovel">

									<%int cont3 = 0;%>
									<logic:iterate name="colecaoDebitoACobrarHistoricoImovel" id="debitoacobrarhistorico" type="DebitoACobrarHistorico">

										<%cont3 = cont3 + 1;
							if (cont3 % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
											<%} else {

							%>
										<tr bgcolor="#FFFFFF">
											<%}%>
											<td width="30%"><font color="#000000" style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <a
											href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=<bean:define name="debitoacobrarhistorico" property="imovel" id="imovel" /><bean:write name="imovel" property="id" />&debitoHistoricoID=<bean:write name="debitoacobrarhistorico" property="id" />', 570, 720);">
											<bean:define name="debitoacobrarhistorico" property="debitoTipo" id="debitoTipo" /> 
											<logic:notEmpty name="debitoTipo" property="descricao">
												<bean:write name="debitoTipo" property="descricao" />
											</logic:notEmpty> </a>
											</font></td>

											<td width="10%">
											<div align="center">
												<logic:present name="debitoacobrarhistorico" property="anoMesReferenciaDebito">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<span style="color: #000000;"><%=""	+ Util.formatarAnoMesParaMesAno(debitoacobrarhistorico.getAnoMesReferenciaDebito())%></span>
													</font>
												</logic:present> 
												<logic:notPresent name="debitoacobrarhistorico" property="anoMesReferenciaDebito">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent>
											</div>
											</td>
											
											<td width="10%">
											<div align="center">
												<logic:present name="debitoacobrarhistorico" property="anoMesCobrancaDebito">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<span style="color: #000000;"><%=""	+ Util.formatarAnoMesParaMesAno(debitoacobrarhistorico.getAnoMesCobrancaDebito())%></span>
													</font>
												</logic:present> 
												<logic:notPresent name="debitoacobrarhistorico" property="anoMesCobrancaDebito">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent>
											</div>
											</td>

											<td width="9%">
											<div align="center">
												<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
													<bean:write	name="debitoacobrarhistorico" property="prestacaoCobradas" />
												</font>
											</div>
											</td>

											<td width="9%">
											<div align="center">
												<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
													<bean:write	name="debitoacobrarhistorico" property="prestacaoDebito" />
												</font>
											</div>
											</td>
											
											<td width="9%">
											<div align="center">
												<logic:present name="debitoacobrarhistorico" property="numeroParcelaBonus">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<bean:write	name="debitoacobrarhistorico" property="numeroParcelaBonus" />
													</font>
												</logic:present> 
												<logic:notPresent name="debitoacobrarhistorico" property="numeroParcelaBonus">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent>
											</div>
											</td>

											<td width="15%">
											<div align="right">
												<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
													<bean:write	name="debitoacobrarhistorico" property="valorDebito" formatKey="money.format" />
												</font>
											</div>
											</td>

											<td width="8%">
											<div align="left">
											<bean:define name="debitoacobrarhistorico" property="debitoCreditoSituacaoAtual" id="debitoCreditoSituacaoAtual"/>
												<logic:present name="debitoCreditoSituacaoAtual" property="descricaoAbreviada">
											
													<font color="#000000" style="font-size:9px"	face="Verdana, Arial, Helvetica, sans-serif"> 
														<bean:write name="debitoacobrarhistorico" property="debitoCreditoSituacaoAtual.descricaoAbreviada" />
													</font>
												</logic:present> 
												<logic:notPresent name="debitoCreditoSituacaoAtual" property="descricaoAbreviada">
													<font color="#000000" style="font-size:9px"	face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent>
											</div>
											</td>

										</tr>
									</logic:iterate>
								</logic:present>
								<!-- Fim do Esquema pra exibir os debitos do hist�rico =========================== -->

							</table>
							<%if (session.getAttribute("tamanhoColecaoDebitos") != null && (Integer)session.getAttribute("tamanhoColecaoDebitos") > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONTAS_DEBITO) {%>
							</div>
							<%} %>
							</td>
						</tr>
						
						
						<!-- Fim da tabela Debito A Cobrar -->
						
						
						
						<tr>
						<td colspan="3">&nbsp;</td>
						</tr>
					
					
					
						<!-- inicio da tabela Credito A Realizar -->
						
						<tr>
							<td colspan="4">
							<table width="100%" border="0" bgcolor="#99C7FC">
								<tr bgcolor="#79bbfd">
									<td bgcolor="#79bbfd" height="20" colspan="8">
									<strong>Hist�rico dos Cr�ditos A Realizar do Im�vel</strong></td>
								</tr>
								
								<%if (session.getAttribute("tamanhoColecaoCreditos") != null && (Integer)session.getAttribute("tamanhoColecaoCreditos") <= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONTAS_DEBITO) {%>
									 
								<tr bordercolor="#FFFFFF" bgcolor="#99C7FC">
									<td width="30%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Tipo do Cr�dito</strong></font></div>
									</td>

									<td width="10%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>M�s/Ano Refer�ncia</strong></font></div>
									</td>

									<td width="10%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>M�s/Ano Cobran�a</strong></font></div>
									</td>
									
									<td width="27%" colspan="3" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Parcelas </strong></font></div>
									</td>
									
									
									<td width="15%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Valor Cr�dito</strong></font></div>
									</td>

									<td width="8%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Situa&ccedil;&atilde;o</strong></font></div>
									</td>



								</tr>
								<tr bordercolor="#FFFFFF" bgcolor="#cbe5fe">

									<td width="9%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Cobradas</strong></font></div>
									</td>

									<td width="9%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"></div>
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Total</strong></font></div>
									</td>
									<td width="9%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>B�nus</strong></font></div>
									</td>
								</tr>
								<%}else{ %>
								<tr bordercolor="#FFFFFF" bgcolor="#99C7FC">


									<td width="29%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Tipo do Cr�dito</strong></font></div>
									</td>

									<td width="10%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>M�s/Ano Refer�ncia</strong></font></div>
									</td>

									<td width="10%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>M�s/Ano Cobran�a</strong></font></div>
									</td>
									
									<td width="26%" colspan="3" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Parcelas </strong></font></div>
									</td>
									
									
									<td width="15%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Valor Cr�dito</strong></font></div>
									</td>

									<td width="10%" rowspan="2" bordercolor="#000000"
										bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Situa&ccedil;&atilde;o</strong></font></div>
									</td>

								</tr>
								<tr bordercolor="#FFFFFF" bgcolor="#cbe5fe">

									<td width="9%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Cobradas</strong></font></div>
									</td>

									<td width="8%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"></div>
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Total</strong></font></div>
									</td>
									<td width="9%" bordercolor="#cbe5fe" bgcolor="#cbe5fe">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>B�nus</strong></font></div>
									</td>
								</tr>
								<%} %>
							</table>

							<%if (session.getAttribute("tamanhoColecaoCreditos") != null && (Integer)session.getAttribute("tamanhoColecaoCreditos") > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONTAS_DEBITO) {%>
							<div style="width: 100%; height: 65; overflow: auto;">
							<%}%>
							<table width="100%" bgcolor="#99C7FC">

								<!-- Esquema pra exibir os creditos =========================== -->
								<logic:present name="colecaoCreditoARealizarImovel">

									<%int cont5 = 0;%>
									<logic:iterate name="colecaoCreditoARealizarImovel" id="creditoarealizar" type="CreditoARealizar">

										<%cont5 = cont5 + 1;
									if (cont5 % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
											<%} else {

									%>
										<tr bgcolor="#FFFFFF">
											<%}%>
											<td width="30%"><font color="#000000" style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <a
											href="javascript:abrirPopup('exibirConsultarCreditoARealizarAction.do?imovelID=<bean:define name="creditoarealizar" property="imovel" id="imovel" /><bean:write name="imovel" property="id" />&creditoID=<bean:write name="creditoarealizar" property="id" />', 570, 720);">
										<bean:define name="creditoarealizar" property="creditoTipo"
											id="creditoTipo" /> <logic:notEmpty name="creditoTipo"
											property="descricao">
											<bean:write name="creditoTipo" property="descricao" />
										</logic:notEmpty> </a>
											</font></td>

											<td width="10%">
											<div align="center">
												<logic:present name="creditoarealizar" property="anoMesReferenciaCredito">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<span style="color: #000000;"><%=""	+ Util.formatarAnoMesParaMesAno(creditoarealizar.getAnoMesReferenciaCredito())%></span>
													</font>
												</logic:present> 
												<logic:notPresent name="creditoarealizar" property="anoMesReferenciaCredito">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent>
											</div>
											</td>
											
											<td width="10%">
											<div align="center">
												<logic:present name="creditoarealizar" property="anoMesCobrancaCredito">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<span style="color: #000000;"><%=""	+ Util.formatarAnoMesParaMesAno(creditoarealizar.getAnoMesCobrancaCredito())%></span>
													</font>
												</logic:present> 
												<logic:notPresent name="creditoarealizar" property="anoMesCobrancaCredito">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent>
											</div>
											</td>

											<td width="9%">
											<div align="center">
												<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
													<bean:write	name="creditoarealizar" property="numeroPrestacaoRealizada" />
												</font>
											</div>
											</td>

											<td width="9%">
											<div align="center">
												<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
													<bean:write	name="creditoarealizar" property="numeroPrestacaoCredito" />
												</font>
											</div>
											</td>
											
											<td width="9%">
											<div align="center">
												<logic:present name="creditoarealizar" property="numeroParcelaBonus">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<bean:write	name="creditoarealizar" property="numeroParcelaBonus" />
													</font>
												</logic:present> 
												<logic:notPresent name="creditoarealizar" property="numeroParcelaBonus">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent>
											</div>
											</td>

											<td width="15%">
											<div align="right">
												<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
													<bean:write	name="creditoarealizar" property="valorCredito" formatKey="money.format" />
												</font>
											</div>
											</td>

											<td width="8%">
											<div align="left">
												<logic:present name="creditoarealizar"	property="debitoCreditoSituacaoAtual">
													<font color="#000000" style="font-size:9px"	face="Verdana, Arial, Helvetica, sans-serif"> 
														<bean:write name="creditoarealizar" property="debitoCreditoSituacaoAtual.descricaoAbreviada" />
													</font>
												</logic:present> 
												<logic:notPresent name="creditoarealizar" property="debitoCreditoSituacaoAtual">
													<font color="#000000" style="font-size:9px"	face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent>
											</div>
											</td>

										</tr>
									</logic:iterate>
								</logic:present>
								<!-- Fim do Esquema pra exibir os creditos =========================== -->



								<!-- Esquema pra exibir os creditos do hist�rico =========================== -->
								<logic:present name="colecaoCreditoARealizarHistoricoImovel">

									<%int cont6 = 0;%>
									<logic:iterate name="colecaoCreditoARealizarHistoricoImovel" id="creditoarealizarhistorico" type="CreditoARealizarHistorico">

										<%cont6 = cont6 + 1;
							if (cont6 % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
											<%} else {

							%>
										<tr bgcolor="#FFFFFF">
											<%}%>
											<td width="30%"><font color="#000000" style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <a
											href="javascript:abrirPopup('exibirConsultarCreditoARealizarAction.do?imovelID=<bean:define name="creditoarealizarhistorico" property="imovel" id="imovel" /><bean:write name="imovel" property="id" />&creditoHistoricoID=<bean:write name="creditoarealizarhistorico" property="id" />', 570, 720);">
										<bean:define name="creditoarealizarhistorico" property="creditoTipo"
											id="creditoTipo" /> <logic:notEmpty name="creditoTipo"
											property="descricao">
											<bean:write name="creditoTipo" property="descricao" />
										</logic:notEmpty> </a>
											</font></td>

											<td width="10%">
											<div align="center">
												<logic:present name="creditoarealizarhistorico" property="anoMesReferenciaCredito">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<span style="color: #000000;"><%=""	+ Util.formatarAnoMesParaMesAno(creditoarealizarhistorico.getAnoMesReferenciaCredito())%></span>
													</font>
												</logic:present> 
												<logic:notPresent name="creditoarealizarhistorico" property="anoMesReferenciaCredito">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent>
											</div>
											</td>
											
											<td width="10%">
											<div align="center">
												<logic:present name="creditoarealizarhistorico" property="anoMesCobrancaCredito">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<span style="color: #000000;"><%=""	+ Util.formatarAnoMesParaMesAno(creditoarealizarhistorico.getAnoMesCobrancaCredito())%></span>
													</font>
												</logic:present> 
												<logic:notPresent name="creditoarealizarhistorico" property="anoMesCobrancaCredito">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent>
											</div>
											</td>

											<td width="9%">
											<div align="center">
												<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
													<bean:write	name="creditoarealizarhistorico" property="prestacaoRealizadas" />
												</font>
											</div>
											</td>

											<td width="9%">
											<div align="center">
												<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
													<bean:write	name="creditoarealizarhistorico" property="prestacaoCredito" />
												</font>
											</div>
											</td>
											
											<td width="9%">
											<div align="center">
												<logic:present name="creditoarealizarhistorico" property="numeroParcelaBonus">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<bean:write	name="creditoarealizarhistorico" property="numeroParcelaBonus" />
													</font>
												</logic:present> 
												<logic:notPresent name="creditoarealizarhistorico" property="numeroParcelaBonus">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent>
											</div>
											</td>

											<td width="15%">
											<div align="right">
												<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
													<bean:write	name="creditoarealizarhistorico" property="valorCredito" formatKey="money.format" />
												</font>
											</div>
											</td>

											<td width="8%">
											<div align="left">
												<logic:present name="creditoarealizarhistorico"	property="debitoCreditoSituacaoAtual">
													<font color="#000000" style="font-size:9px"	face="Verdana, Arial, Helvetica, sans-serif"> 
														<bean:write name="creditoarealizarhistorico" property="debitoCreditoSituacaoAtual.descricaoAbreviada" />
													</font>
												</logic:present> 
												<logic:notPresent name="creditoarealizarhistorico" property="debitoCreditoSituacaoAtual">
													<font color="#000000" style="font-size:9px"	face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent>
											</div>
											</td>

										</tr>
									</logic:iterate>
								</logic:present>
								<!-- Fim do Esquema pra exibir os creditos do hist�rico =========================== -->

							</table>
							<%if (session.getAttribute("tamanhoColecaoCreditos") != null && (Integer)session.getAttribute("tamanhoColecaoCreditos") > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONTAS_DEBITO) {%>
							</div>
							<%}%>
							</td>
						</tr>
						
						
						<!-- Fim da tabela Credito A Realizar -->					
					
					
					
					
						<tr>
							<td colspan="3">&nbsp;</td>
						</tr>
						
					
					
					
						<!-- inicio da tabela Guia de Pagamento -->
						
						<tr>
							<td colspan="4">
							<table width="100%" border="0" bgcolor="#99C7FC">
								<tr bgcolor="#79bbfd">
									<td bgcolor="#79bbfd" height="20" colspan="8">
									<strong>Hist�rico das Guias de Pagamento do Im�vel</strong></td>
								</tr>
								
								<%if (session.getAttribute("tamanhoColecaoGuias") != null && (Integer)session.getAttribute("tamanhoColecaoGuias") <= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONTAS_DEBITO) {%>
								
								<tr bordercolor="#FFFFFF" bgcolor="#99C7FC">
									<td width="32%" bordercolor="#000000" bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Tipo do D�bito</strong></font></div>
									</td>

									<td width="10%" bordercolor="#000000" bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>N�mero da Presta��o</strong></font></div>
									</td>

									<td width="15%" bordercolor="#000000" bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>N�mero da Presta��o Total</strong></font></div>
									</td>
									
									<td width="14%" bordercolor="#000000" bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Emiss�o </strong></font></div>
									</td>
									
									<td width="14%" bordercolor="#000000" bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Vencimento </strong></font></div>
									</td>
									
									<td width="15%" bordercolor="#000000" bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Valor da Guia</strong></font></div>
									</td>

								<%}else{ %>
								<tr bordercolor="#FFFFFF" bgcolor="#99C7FC">

									<td width="31%" bordercolor="#000000" bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Tipo do D�bito</strong></font></div>
									</td>

									<td width="11%" bordercolor="#000000" bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>N�mero da Presta��o</strong></font></div>
									</td>

									<td width="14%" bordercolor="#000000" bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>N�mero da Presta��o Total</strong></font></div>
									</td>
									
									<td width="14%" bordercolor="#000000" bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Emiss�o </strong></font></div>
									</td>
									
									<td width="13%" bordercolor="#000000" bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Vencimento </strong></font></div>
									</td>
									
									<td width="19%" bordercolor="#000000" bgcolor="#90c7fc">
									<div align="center"><font color="#000000" style="font-size:9px"
										face="Verdana, Arial, Helvetica, sans-serif"><strong>Valor da Guia</strong></font></div>
									</td>
									
								</tr>
								
								<%} %>
							</table>

							<%if (session.getAttribute("tamanhoColecaoGuias") != null && (Integer)session.getAttribute("tamanhoColecaoGuias") > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONTAS_DEBITO) {%>
							<div style="width: 100%; height: 65; overflow: auto;">
							<%} %>
							<table width="100%" bgcolor="#99C7FC">

								<!-- Esquema pra exibir as guias =========================== -->
								<logic:present name="colecaoGuiaPagamentoImovel">

									<%int cont7 = 0;%>
									<logic:iterate name="colecaoGuiaPagamentoImovel" id="guiapagamento" type="GuiaPagamento">

										<%cont7 = cont7 + 1;
									if (cont7 % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
											<%} else {

									%>
										<tr bgcolor="#FFFFFF">
											<%}%>
											<td width="32%"><font color="#000000" style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <a
												href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoId=<bean:write name="guiapagamento" property="id" />', 600, 800);"><bean:define name="guiapagamento" property="debitoTipo"	id="debitoTipo" /> 
												<logic:notEmpty name="debitoTipo" property="descricao"><bean:write name="debitoTipo" property="descricao" /></logic:notEmpty> </a> 
											</font></td>
											

											<td width="10%">
											<div align="center">
												<logic:present name="guiapagamento" property="numeroPrestacaoDebito">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<bean:write	name="guiapagamento" property="numeroPrestacaoDebito" />
													</font>
												</logic:present> 
												<logic:notPresent name="guiapagamento" property="numeroPrestacaoDebito">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent>
											</div>
											</td>
											
											<td width="15%">
											<div align="center">
												<logic:present name="guiapagamento" property="numeroPrestacaoTotal">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<bean:write	name="guiapagamento" property="numeroPrestacaoTotal" />
													</font>
												</logic:present> 
												<logic:notPresent name="guiapagamento" property="numeroPrestacaoTotal">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent>
											</div>
											</td>

											<td width="14%">
											<div align="center">
												<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
													<bean:write name="guiapagamento" property="dataEmissao" formatKey="date.format" />
												</font>
											</div>
											</td>

											<td width="14%">
											<div align="center">
												<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
													<bean:write name="guiapagamento" property="dataVencimento" formatKey="date.format" />
												</font>
											</div>
											</td>

											<td width="15%">
											<div align="right">
												<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
													<bean:write name="guiapagamento" property="valorDebito"	formatKey="money.format" />
												</font>
											</div>
											</td>


										</tr>
									</logic:iterate>
								</logic:present>
								<!-- Fim do Esquema pra exibir as guias =========================== -->



								<!-- Esquema pra exibir as guias do hist�rico =========================== -->
								<logic:present name="colecaoGuiaPagamentoHistoricoImovel">

									<%int cont8 = 0;%>
									<logic:iterate name="colecaoGuiaPagamentoHistoricoImovel" id="guiaPagamentohistorico" type="GuiaPagamentoHistorico">

										<%cont8 = cont8 + 1;
							if (cont8 % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
											<%} else {

							%>
										<tr bgcolor="#FFFFFF">
											<%}%>
										<td width="32%"><font color="#000000" style="font-size:9px"
												face="Verdana, Arial, Helvetica, sans-serif"> <a
												href="javascript:abrirPopup('exibirConsultarGuiaPagamentoAction.do?guiaPagamentoHistoricoId=<bean:write name="guiaPagamentohistorico" property="id" />', 600, 800);"><bean:define name="guiaPagamentohistorico" property="debitoTipo"	id="debitoTipo" /> 
												<logic:notEmpty name="debitoTipo" property="descricao"><bean:write name="debitoTipo" property="descricao" /></logic:notEmpty> </a> 
											</font></td>
											

											<td width="10%">
											<div align="center">
												<logic:present name="guiaPagamentohistorico" property="numeroPrestacaoDebito">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<bean:write	name="guiaPagamentohistorico" property="numeroPrestacaoDebito" />
													</font>
												</logic:present> 
												<logic:notPresent name="guiaPagamentohistorico" property="numeroPrestacaoDebito">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent>
											</div>
											</td>
											
											<td width="15%">
											<div align="center">
												<logic:present name="guiaPagamentohistorico" property="numeroPrestacaoTotal">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
														<bean:write	name="guiaPagamentohistorico" property="numeroPrestacaoTotal" />
													</font>
												</logic:present> 
												<logic:notPresent name="guiaPagamentohistorico" property="numeroPrestacaoTotal">
													<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp; </font>
												</logic:notPresent>
											</div>
											</td>

											<td width="14%">
											<div align="center">
												<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
													<bean:write name="guiaPagamentohistorico" property="dataEmissao" formatKey="date.format" />
												</font>
											</div>
											</td>

											<td width="14%">
											<div align="center">
												<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
													<bean:write name="guiaPagamentohistorico" property="dataVencimento" formatKey="date.format" />
												</font>
											</div>
											</td>

											<td width="15%">
											<div align="right">
												<font color="#000000" style="font-size:9px" face="Verdana, Arial, Helvetica, sans-serif"> 
													<bean:write name="guiaPagamentohistorico" property="valorDebito"	formatKey="money.format" />
												</font>
											</div>
											</td>

										</tr>
									</logic:iterate>
								</logic:present>
								<!-- Fim do Esquema pra exibir as guias do hist�rico =========================== -->

							</table>
							<%if (session.getAttribute("tamanhoColecaoGuias") != null && (Integer)session.getAttribute("tamanhoColecaoGuias") > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONTAS_DEBITO) {%>
							</div>
							<%} %>
							</td>
						</tr>
						<!-- Fim da tabela Guia de Pagamento -->		
						<tr>
							<td align="left" width="100%">
								  <div align="right">
									   <a href="javascript:verificaExibicaoRelatorioHistorico();">
											<img border="0" src="<bean:message key="caminho.imagens"/>print.gif"
												title="Imprimir Hist�rico Faturamento" /> 
										</a>
								  </div>
							</td>
						</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">
					<div align="right"><jsp:include
						page="/jsp/util/wizard/navegacao_botoes_wizard_consulta.jsp?numeroPagina=4" /></div>
					</td>
				</tr>
			</table>

			</td>
		</tr>
	</table>

	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioHistoricoFaturamentoImovelAction.do"/>
	<%@ include file="/jsp/util/rodape.jsp"%>
    <%@ include file="/jsp/util/tooltip.jsp" %>		

</html:form>
</body>
</html:html>
