<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ include file="/jsp/util/telaespera.jsp"%>
<%@ page import="gcom.relatorio.cobranca.BoletimMedicaoContratoHelper" %>
<%@ page import="gcom.relatorio.cobranca.BoletimMedicaoContratoDadosHelper" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GCOM - Sistema de Gest&atilde;o Comercial</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"  formName="GerarEmitirBoletimMedicaoContratosForm"/>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript">
	
	function validarForm(){
		
		var form = document.forms[0];
		if(validarCampos()){
			if (form.formaGeracao[0].checked == false
				&& form.formaGeracao[1].checked == false) {
  				alert("Selecione a Operação.");
			} else if (form.formaGeracao[1].checked == true) {
				
				if (!selecionouBoletimContrato()) {
					alert("Selecione o Boletim de Medição de Contrato.");
				} else {
					toggleBox('demodiv',1);
				}
					
			} else {
				form.action='solicitarGeracaoEmissaoBoletimMedicaoContratosAction.do';
				form.submit();
			}
		}
	}

	function selecionouBoletimContrato(){

		var form = document.forms[0];
	    var selecionados = form.elements['idBoletimContrato'];
		var retorno = false;
		
		if(selecionados != null){
			if (selecionados.checked) {
				
				retorno = true;
				
			} else {
			
				for (i=0; i< selecionados.length; i++) {
					if(selecionados[i].checked){
						retorno = true;
					}
				}
				
			}
		}
		
		return retorno;
	}
	
  	function limpar(){
  		var form = document.forms[0];

		form.action='exibirSolicitarGeracaoEmissaoBoletimMedicaoContratosAction.do?menu=sim';
		form.submit();
  	}		
	
	function validarCampos(){

		var form = document.forms[0];

		if (form.idEmpresa.value == null 
				|| form.idEmpresa.value == '' 
				|| form.idEmpresa.value == '-1') {
		
	  		alert("Informe a Empresa.");
			return false;
	  		
		} else if (form.idContrato.value == null 
				|| form.idContrato.value == '' 
				|| form.idContrato.value == '-1') {
				
			  		alert("Informe o Contrato.");
					return false;
			  		
		} else if ((form.mesAnoReferencia.value == null 
					|| form.mesAnoReferencia.value == "")
			&& form.formaGeracao[1].checked == true) {
		
	  		alert("Informe o Mês/Ano de Referência.");
			return false;
	  		
		} 
	  		
   		return true;
	}

	function selecionarOperacao(tipo){
		var form = document.forms[0];
		if(tipo==1){
			form.tipoOperacao.value = 1;
			document.getElementById('tipoGerar').style.display = 'block';	
			document.getElementById('tipoEmitir').style.display = 'none';			
		}else if(tipo==2){
			form.tipoOperacao.value = 2;
			document.getElementById('tipoGerar').style.display = 'none';
			document.getElementById('tipoEmitir').style.display = 'block';	
		}
	}
	
	function verificaSelecao() {
		var form = document.forms[0];

		if(form.tipoOperacao.value==1){
			document.getElementById('tipoGerar').style.display = 'block';
			document.getElementById('tipoEmitir').style.display = 'none';
		}else if(form.tipoOperacao.value==2){
			document.getElementById('tipoGerar').style.display = 'none';
			document.getElementById('tipoEmitir').style.display = 'block';
		}
	}
	
	function verificarSelecaoEmpresa() {
		var form = document.forms[0];
		
		if(form.idEmpresa.value != null 
				&& form.idEmpresa.value != ''){
			form.action='exibirSolicitarGeracaoEmissaoBoletimMedicaoContratosAction.do?selecionouEmpresa=sim';
   			form.submit();
		}
	}

	function selecionarBoletim(){
		var form = document.forms[0];

		if (validarCampos()) {
			form.action='exibirSolicitarGeracaoEmissaoBoletimMedicaoContratosAction.do?selecionarBoletim=sim';
   			form.submit();
		}		
	}

	function selecionarBoletimContrato(){
		var form = document.forms[0];

		if (validarCampos()) {
			form.action='exibirSolicitarGeracaoEmissaoBoletimMedicaoContratosAction.do?selecionarBoletimContrato=sim';
   			form.submit();
		}		
	}

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
	
</script>

</head>

<body leftmargin="5" topmargin="5" onload="verificaSelecao();">

<div id="formDiv"><html:form action="/exibirSolicitarGeracaoEmissaoBoletimMedicaoContratosAction.do"
	name="GerarEmitirBoletimMedicaoContratosForm"
	type="gcom.gui.relatorio.cobranca.GerarEmitirBoletimMedicaoContratosForm"
	method="post"> 

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
	  <html:hidden property="tipoOperacao"/>
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
			<td width="600" valign="top" class="centercoltext">
		        <table height="100%">
			        <tr>
			          <td></td>
			        </tr>
		      	</table>

			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Solicitar Geração/Emissão Boletim de Medição de Contrato</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				
				<tr>
					<td colspan="3">Para selecionar o(s) Boletim(ns) de Medição de Cobrança, informar os dados abaixo:</td>
				</tr>
				
				<tr>
					<td colspan="1"><strong>Operação:<font color="#FF0000">*</font></strong></td>
					<td colspan="2">
						<html:radio property="formaGeracao"
						    value="1" 
							onclick="selecionarOperacao(this.value);"/>
							Gerar Boletim
						<html:radio property="formaGeracao"
						    value="2" 
							onclick="selecionarOperacao(this.value);"/>
							Emitir Relatório
					</td>
				</tr>
				
				<tr>
					<td colspan="2"></td>
				</tr>
				
				<tr>
					<td colspan="1"><strong>Empresa:<font color="#FF0000">*</font></strong></td>
					<td colspan="2" align="left">
						<html:select property="idEmpresa"
							tabindex="4" onchange="verificarSelecaoEmpresa();">
							<html:option value="-1">&nbsp;</html:option>
							<logic:present name="colecaoEmpresas">					
								<html:options collection="colecaoEmpresas"
									labelProperty="descricao" property="id" />
							</logic:present>
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td colspan="1"><strong>Contrato:<font color="#FF0000">*</font></strong></td>
					<td colspan="2" align="left">
						<logic:present name="informouEmpresa">
							<html:select property="idContrato"
								tabindex="4" disabled="false">
								<html:option value="-1">&nbsp;</html:option>
								<logic:present name="colecaoContrato">					
									<html:options collection="colecaoContrato"
										labelProperty="descricaoContrato" property="id" />
								</logic:present>
							</html:select>
						</logic:present>
						<logic:notPresent name="informouEmpresa">
							<html:select property="idContrato"
								tabindex="4" disabled="true">
								<html:option value="-1">&nbsp;</html:option>
							</html:select>
						</logic:notPresent>
					</td>
				</tr>
				
				<tr>
					<td colspan="1">
						<strong>Mês/Ano de Referência:</strong>
					</td>

					<td colspan="2">
						<html:text  property="mesAnoReferencia" 
									size="7" 
									maxlength="7" 
									tabindex="1"
									onkeyup="mascaraAnoMes(this, event);"
									onkeypress="return isCampoNumerico(event);" /> (mm/aaaa)
					</td>
				</tr>
				
				<tr><td>&nbsp;</td></tr>
				
				<tr>
					<td colspan="3">
						<div id="tipoGerar" style="display:none;">
							<table border="0" width="100%">
								<tr>
									<td align="right" colspan="3">
										<input type="button" 
											   name="selecionar" 
											   class="bottonRightCol" 
											   value="Selecionar" 
											   onClick="javascript:selecionarBoletim()" />
									</td>
									
								</tr>
								<tr>
									<td colspan="3">
										<table width="100%"  border="0">
											<tr bordercolor="#000000">
												<td width="15%" bgcolor="#90c7fc" align="center" rowspan="2"><strong><a href="javascript:facilitador(this);" id="0">Todos</a></strong></td>
												<td width="30%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Mês/Ano Referência</strong></td>
												<td width="55%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Grupo de Cobrança</strong></td>
												
								
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="3">
										<div style="height: 100%; max-height: 300px; overflow: auto;">
										<table width="100%" bgcolor="#99CCFF">
											<logic:present
												name="colecaoBoletimMedicaoContratoHelper">
												<%int cont = 0;%>
												<logic:iterate
													name="colecaoBoletimMedicaoContratoHelper"
													id="boletimMedicaoContratoHelper"
													type="BoletimMedicaoContratoHelper"
													scope="session">
														<%cont = cont + 1;
												if (cont % 2 == 0) {%>
														<tr bgcolor="#cbe5fe">
															<%} else {%>
														</tr>
														<tr bgcolor="#FFFFFF">
															<%}%>
															<td width="15%">
																<div align="center">
																	<input name="idsBoletim" 
															 			value="<c:out value="${boletimMedicaoContratoHelper.idBoletim}" ></c:out>" 
															 			type="checkbox" >
																</div>
															</td>
			
															<td align="center" width="30%">
																<bean:write name="boletimMedicaoContratoHelper" property="anoMesReferencia" />
															</td>
					
															<td align="center" width="55%">
																<bean:write name="boletimMedicaoContratoHelper" property="descricaoGrupoCobranca"  /> 
															</td>
			
														</tr>
												</logic:iterate>
											</logic:present>
										</table>
										</div>
									</td>
								</tr>
							</table>
						</div>
						
						<div id="tipoEmitir" style="display:none;">
							<table border="0" width="100%">
								<tr>
									<td align="right" colspan="3">
										<input type="button" 
											   name="selecionar" 
											   class="bottonRightCol" 
											   value="Selecionar" 
											   onClick="javascript:selecionarBoletimContrato()" />
									</td>
									
								</tr>
								<tr>
									<td colspan="3">
										<table width="100%"  border="0">
											<tr bordercolor="#000000">
												<td width="15%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Selecionar</strong></td>
												<td width="30%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Mês/Ano Referência</strong></td>
												<td width="55%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Descrição do Contrato</strong></td>
												
								
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="3">
										<div style="height: 100%; max-height: 300px; overflow: auto;">
										<table width="100%" bgcolor="#99CCFF">
											<logic:present
												name="colecaoBoletimMedicaoContratoDadosHelper">
												<%int cont = 0;%>
												<logic:iterate
													name="colecaoBoletimMedicaoContratoDadosHelper"
													id="boletimMedicaoContratoDadosHelper"
													type="BoletimMedicaoContratoDadosHelper"
													scope="session">
														<%cont = cont + 1;
												if (cont % 2 == 0) {%>
														<tr bgcolor="#cbe5fe">
															<%} else {%>
														</tr>
														<tr bgcolor="#FFFFFF">
															<%}%>
															<td width="15%">
																<div align="center">
																	<input name="idBoletimContrato" 
															 			value="<c:out value="${boletimMedicaoContratoDadosHelper.idBoletim}" ></c:out>" 
															 			type="radio" >
																</div>
															</td>
			
															<td align="center" width="30%">
																<bean:write name="boletimMedicaoContratoDadosHelper" property="anoMesReferencia" />
															</td>
					
															<td align="center" width="55%">
																<bean:write name="boletimMedicaoContratoDadosHelper" property="descricaoContrato"  /> 
															</td>
			
														</tr>
												</logic:iterate>
											</logic:present>
										</table>
										</div>
									</td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="3"></td>
				</tr>
				
				<tr>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td colspan="3"></td>
				</tr>
	
				<tr>
					<td colspan="3" align="center"><font color="#FF0000">*</font> Campo Obrigatório</td>
				</tr>
								          	
				<tr>
					<td height="24" colspan="1">
			          	<input type="button" 
			          		   class="bottonRightCol" 
			          		   value="Limpar" 
			          		   onclick="javascript:limpar();"/>
			          		   &nbsp;&nbsp;
			          	<input type="button" 
				          		name="ButtonCancelar" 
				          		class="bottonRightCol"
								value="Cancelar"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>
				
					<td align="right" colspan="2">
						<input type="button" 
							   name="Button" 
							   class="bottonRightCol" 
							   value="Enviar" 
							   onClick="javascript:validarForm()" />
					</td>
					
				</tr>							
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	
	<jsp:include
			page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=/gsan/gerarRelatorioAcompanhamentoBoletimContratoAction.do" 
	/>	
<%@ include file="/jsp/util/rodape.jsp"%>	
</html:form></div>
</body>
</html:html>
