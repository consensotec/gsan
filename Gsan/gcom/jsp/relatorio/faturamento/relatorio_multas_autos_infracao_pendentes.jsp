<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="gcom.util.ConstantesSistema"%>
<html:html>
<head>
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

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="GerarRelatorioMultasAutosInfracaoPendentesActionForm" />

<script language="JavaScript">

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    var form = document.forms[0];  
    if (tipoConsulta == 'funcionario') {
		form.idFuncionario.value = codigoRegistro;
		form.descricaoFuncionario.value = descricaoRegistro;
		form.descricaoFuncionario.style.color = "#000000";
   }
}

function limparForm(){
	var form = document.forms[0];
	limparFuncionario();
	limparRota();
	form.periodoAtuacaoInicial.value = "";
	form.periodoAtuacaoFinal.value = "";
	document.forms[0].grupo[0].selected = "1";
}

function limparFuncionario(){
	var form = document.forms[0]; 
	form.idFuncionario.value = "";
	form.descricaoFuncionario.value = "";
	form.descricaoFuncionario.style.color = "#000000";
}

function limparRota(){
	var form = document.forms[0]; 
	form.idRota.value = "";
	form.descricaoRota.value = "";
	form.descricaoRota.style.color = "#000000";
}

function validarForm(){
 	var form = document.forms[0];
 	if(validateGerarRelatorioMultasAutosInfracaoPendentesActionForm(form)){

 		if(form.periodoAtuacaoInicial.value == "" && form.periodoAtuacaoFinal.value != ""){
 	 		alert('Informe Período de Atuação Inicial');	
 	 	}else if(form.periodoAtuacaoInicial.value != "" && form.periodoAtuacaoFinal.value == ""){
 	 		alert('Informe Período de Atuação Final');	
 	 	}else if(comparaData(form.periodoAtuacaoInicial.value,'<=',form.periodoAtuacaoFinal.value)){
 			botaoAvancarTelaEspera('/gsan/gerarRelatorioMultasAutosInfracaoPendentesAction.do');
 		}else{
 			alert('Período de Atuação Inicial deve ser anterior ou igual ao Período de Atuação Final');
 		}
 	}
}

function receberRota(codigoRota,descricao,codigo,destino) {
	var form = document.forms[0];
	
	if(destino == 'Inicial'){
 		form.idRota.value = codigoRota;
  	}
  	
	form.action = 'exibirGerarRelatorioMultasAutosInfracaoPendentesAction.do';
	form.submit();
}

function replicarData() {
	var form =  document.forms[0];

	form.periodoAtuacaoFinal.value = form.periodoAtuacaoInicial.value; 
}


</script>

</head>

<body leftmargin="5" topmargin="5">
<div id="formDiv">
<html:form action="/gerarRelatorioMultasAutosInfracaoPendentesAction"
	name="GerarRelatorioMultasAutosInfracaoPendentesActionForm"
	type="gcom.gui.relatorio.faturamento.GerarRelatorioMultasAutosInfracaoPendentesActionForm"
	method="post" >

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
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
			<td width="600" valign="top" bgcolor="#003399" class="centercoltext">
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
					<td class="parabg">Gerar Relatórios das Multas de Auto de Infração Pendentes</td>

					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				<tr>
					<td height="5" colspan="3"></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td colspan="3">Para gerar o relatório de multas de auto de infração, informe
					os dados abaixo: </td>
				</tr>
				<tr>
					<td width="100"><strong>Grupo:</strong></td>
	            	<td colspan="2">
			            <div align="left">
			                <strong>
				                <html:select property="grupo" tabindex="1">
					                <html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
   					                <logic:present name="colecaoGrupos" scope="session">
						            	<html:options collection="colecaoGrupos" labelProperty="descricao" property="id" />
					            	</logic:present>
								</html:select>
			                </strong>
			            </div>
	           		 </td>
				</tr>
				
				 <tr>
	                <td><strong>Funcionário:</strong></td>
	                <td colspan="2"><strong>
	                  <html:text maxlength="9"
	                  			 size="9"
	                  			 tabindex="2"
	                  			 property="idFuncionario"
	                  			 onkeypress="javascript:validaEnterComMensagem(event, 'exibirGerarRelatorioMultasAutosInfracaoPendentesAction.do?pesquisarFuncionario=OK', 'idFuncionario','Funcionário');return isCampoNumerico(event);" />
	                <a href="javascript:abrirPopup('exibirFuncionarioPesquisar.do', 330, 600);" tabindex="3">
						<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						     title="Pesquisar Funcionário" /></a>
					<logic:notPresent name="funcionarioException" scope="request">	     
		     			<html:text tabindex="3"
		     					   maxlength="30"
		     					   size="30"
		     					   readonly="true"
		     					   property="descricaoFuncionario"
   		     					   style="background-color:#EFEFEF; border:0; color: #000000"/>
	     			</logic:notPresent>
	     			<logic:present name="funcionarioException" scope="request">
		     			<html:text tabindex="4"
		     					   maxlength="30"
		     					   size="30"
		     					   readonly="true"
		     					   property="descricaoFuncionario"
		     					   style="background-color:#EFEFEF; border:0; color: #ff0000"/>	
	     			</logic:present>	     
	     			</strong>
	     			<a href="javascript:limparFuncionario();" tabindex="5">
						<img border="0" title="Apagar" src="/gsan/imagens/limparcampo.gif">
					</a>
	     		</td>
              </tr>
              <tr>
				<td><strong>Período de Autuação:</strong></td>				
				<td colspan="3">
					<html:text property="periodoAtuacaoInicial" 
							size="11" 
							maxlength="10" 
							tabindex="3" 
							onkeyup="mascaraData(this, event);replicarData();" 
							onkeypress="return isCampoNumerico(event);"/>
					<a href="javascript:abrirCalendarioReplicando('GerarRelatorioMultasAutosInfracaoPendentesActionForm', 'periodoAtuacaoInicial', 'periodoAtuacaoFinal');">
						<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" title="Exibir Calendário" /></a>
					a <html:text property="periodoAtuacaoFinal" 
								size="11" 
								maxlength="10" 
								tabindex="3" 
								onkeyup="mascaraData(this, event)"  
								onkeypress="return isCampoNumerico(event);"/>
					<a href="javascript:abrirCalendario('GerarRelatorioMultasAutosInfracaoPendentesActionForm', 'periodoAtuacaoFinal');">
						<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="16" height="15" border="0" title="Exibir Calendário" /></a> dd/mm/aaaa				

				</td>
			</tr>
			<tr>
				<td><strong>Rota:</strong></td>
				<td>
					<html:hidden property="idRota"/>
					
					<a tabindex="11"	href="javascript:abrirPopup('exibirPesquisarInformarRotaLeituraAction.do?caminhoRetornoTelaPesquisaRota=exibirPesquisarInformarRotaLeituraAction&destinoRota=Inicial&limparForm=sim');">
						<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Rota" /></a>
					
					<logic:notPresent name="rotaException" scope="request"> 			
						<html:text property="descricaoRota" size="30" tabindex="12"
									maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />						
					</logic:notPresent>
					<logic:present name="rotaException" scope="request"> 			
						<html:text property="descricaoRota" size="30" tabindex="13"
									maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />						
					</logic:present>
						
					<a href="javascript:limparRota();" tabindex="14" >
						<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" />
					</a>
											
				</td>					
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="3">
	                	<input type="button" name="adicionar2" tabindex="16" 
	                			class="bottonRightCol" 
	                			value="Limpar" 
	                			onclick="javascript:limparForm();">
	                	<input type="button" name="adicionar" tabindex="17"
				                class="bottonRightCol" 
				                value="Cancelar" 
				                onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
				               
                </td>
				<td align="right">
					<input type="button" 
						   name="Button" 
						   tabindex="15"
						   class="bottonRightCol" 
						   value="Gerar" id="botaoGerar" 
						   onclick="javascript:validarForm();" />
				</td>
			 </tr>
			
				
			</table>	
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</div>

	<%@ include file="/jsp/util/telaespera.jsp"%>

</body>

</html:html>