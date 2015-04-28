<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@page import="gsan.atendimentopublico.ordemservico.ConsultarComandosOSSeletivaInspecaoAnormalidadeHelper"%>
<%@page import="gsan.util.Util" isELIgnored="false"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false" formName="GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeActionForm" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>

<script language="JavaScript">

	
	function limparEmpresa() {
		var form = document.forms[0];
		
		form.idEmpresa.value = "";
		form.nomeEmpresa.value = "";	
		form.periodoExecucaoInicial.value = '';
		form.periodoExecucaoFinal.value = '';
	}
	
	function pesquisarEmpresa() {
		var form = document.forms[0];
		abrirPopup('exibirPesquisarEmpresaAction.do?limpaForm=S', 495, 300);
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    	var form = document.forms[0];
   
	    if (tipoConsulta == 'empresa') {
			form.idEmpresa.value = codigoRegistro;
			form.nomeEmpresa.value = descricaoRegistro;
			form.nomeEmpresa.style.color = "#000000";
			form.action = "exibirGerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeAction.do";
			form.submit();
    	}
    
    }
	
	function selecionarComandos(){
		var form =  document.forms[0];
		form.action = 'exibirGerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeAction.do?selecionarComandos=sim';
	    form.submit();
	}
		
	function limparComandos() {
		var form = document.forms[0];
		form.action = 'exibirGerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeAction.do?limpar=sim';
		submeterFormPadrao(form);
	}

</script>

<script type="text/javascript">
	$(document).ready(function(){
		$('#botaoAvancar').click(function(){
			var form = document.forms[0];
			if($('[name=idComando]:checked').size() == 1){
				form.action = 'exibirGerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeAction.do?avancar=sim';
				form.submit();	
			}else{
				alert('Selecione um comando.');
			}
		});
		
		$('#botaoPesquisarComando').click(function(){
			var form = document.forms[0];
			if(validateGerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeActionForm(form)){
				form.action = 'exibirGerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeAction.do?selecionarComandos=sim';
			    form.submit();
			}
			
		});
	});
</script>

</head>

<body leftmargin="5" topmargin="5">

<html:form action="/exibirGerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeAction"
	name="GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeActionForm"
	type="gsan.gui.relatorio.atendimentopublico.GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeActionForm"
	method="post" onsubmit="return validateConsultarDebitoActionForm(this);">

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
					<td class="parabg">Relatório de Boletim de Medição - Inspeção de Anormalidade</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para gerar o relatório de boletim de medição das OS de Inspeção de Anormalidade, informe os dados abaixo:</td>
				</tr>
				<tr>
					<td width="25%"><strong>Empresa:<font color="#FF0000">*</font></strong></td>
					<td width="75%">
						<html:text maxlength="3" property="idEmpresa" size="3" tabindex="1"    
							onkeyup="validaEnterComMensagem(event, 'exibirGerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeAction.do', 'idEmpresa', 'Empresa');"
							onkeypress="return isCampoNumerico(event);"/>
						<a href="javascript:pesquisarEmpresa();">
							<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" height="21" alt="Pesquisar" border="0">
						</a> 
						
						<logic:present name="empresaInexistente" scope="request">
							<html:text property="nomeEmpresa" size="40" maxlength="40"
								readonly="true"
								style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
						</logic:present> 
						
						<logic:notPresent name="empresaInexistente" scope="request">
							<html:text property="nomeEmpresa" size="40" maxlength="40"
								readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notPresent> 
						<a href="javascript:limparEmpresa();"> 
							<img border="0" src="imagens/limparcampo.gif" height="21" width="23"> 
						</a>
					</td>
				</tr>
			
				<tr>
					<td><strong>Período de Execução do Comando:<font color="#FF0000">*</font></strong></td>
					<td>
						<strong> 
							<html:text maxlength="10"
								property="periodoExecucaoInicial" 
								size="10" 
								tabindex="2"
								onkeypress="return isCampoNumerico(event);"
								onkeyup="mascaraData(this, event);replicarCampo(document.forms[0].periodoExecucaoFinal, this);"/>
							
							<a href="javascript:abrirCalendarioReplicando('GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeActionForm', 'periodoExecucaoInicial', 'periodoExecucaoFinal');">
								<img border="0" 
									width="20" 
									border="0" 
									align="middle" 
									alt="Exibir Calendário" 
									src="<bean:message key="caminho.imagens"/>calendario.gif"/>
							</a>
						</strong> 
						
						<html:text maxlength="10" 
							property="periodoExecucaoFinal"
							tabindex="3" 
							size="10" 
							onkeyup="mascaraData(this, event);"
							onkeypress="return isCampoNumerico(event);"/> 
						<a href="javascript:abrirCalendario('GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeActionForm', 'periodoExecucaoFinal')">
							<img border="0" 
								src="<bean:message key="caminho.imagens"/>calendario.gif"
								width="20" 
								border="0" 
								align="middle" 
								alt="Exibir Calendário" />
						</a>
						(dd/mm/aaaa)
					</td>
				</tr>

				<tr>
					<td colspan="2" align="right">
						<input type="button" class="bottonRightCol" value="Pesquisar Comandos"  tabindex="4" id="botaoPesquisarComando"/>
					</td>
				</tr>

				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>

				<logic:present name="colecaoConsultarComandosOSHelper" scope="session">
					<tr>
						<td colspan="2" align="left">
							<strong>Comandos de OS Seletiva:</strong>
						</td>
					</tr>
						
					<tr>
						<td colspan="2">
							<table width="100%" cellpadding="0" cellspacing="0">
								<tr>
									<td colspan="4" bgcolor="#000000" height="2"></td>
								</tr>
								<tr>
									<td>
									
										<table width="100%" bgcolor="#99CCFF" border="0">
										</table>
									</td>
								</tr>
						
								<tr>
									<td>
										<div align="left" style="max-height: 200px; overflow-y: scroll; ">
											<table width="100%" bgcolor="#99CCFF">
												
												<tr bordercolor="#000000">
													<td width="10%" bgcolor="#90c7fc" align="center"><strong>Marca</strong></td>
													<td width="50%" bgcolor="#90c7fc" align="center"><strong>Comando</strong></td>
													<td width="10%" bgcolor="#90c7fc" align="center"><strong>Data de Execução</strong></td>
												</tr>
												
												<%	int cont = 0;	%>
												<logic:iterate name="colecaoConsultarComandosOSHelper" id="helper" 
													type="ConsultarComandosOSSeletivaInspecaoAnormalidadeHelper" scope="session">
													<%	cont = cont + 1;
													if (cont % 2 == 0) {	%>
														<tr bgcolor="#cbe5fe">
												<%	} else {	%>
														<tr bgcolor="#FFFFFF">
												<%	}	%>
														<td width="10%">
														<div align="center"><html:radio property="idComando"
															value="${helper.idComando}" />
														</div>
														</td>
		
														<td align="left" width="50%"><a
															href="javascript:abrirPopup('exibirConsultarComandosOSSeletivaInspecaoAnormalidadePopupAction.do?pesquisa=nao&idComando=<%= helper.getIdComando()%>', 600, 700);">
														<%=helper.getDescComando()%> </a></td>
		
														<td align="center" width="10%">
															<%=Util.formatarData(helper.getDataExecucao())%>
														</td>
													</tr>
												</logic:iterate>
											</table>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</logic:present>
				 
				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigatórios</div>
					</td>
				</tr>

				<tr>
					<td colspan="2">
						<input name="Button" type="button" class="bottonRightCol" value="Desfazer" align="left"
							onclick="window.location.href='<html:rewrite page="/exibirGerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeAction.do?menu=sim"/>'">
						<input name="Button" type="button" class="bottonRightCol" value="Cancelar" align="left"
							onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>
					<td align="right">
						<input type="button" name="movimentar" class="bottonRightCol" value="Avançar" id="botaoAvancar"/>
					</td>
				</tr>
			</table>
		</table>
		<%@ include file="/jsp/util/rodape.jsp"%> 
	</html:form>
</body>
</html:html>
