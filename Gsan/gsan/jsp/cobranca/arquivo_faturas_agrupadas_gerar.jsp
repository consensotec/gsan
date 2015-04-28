<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="gsan.util.ConstantesSistema"%>
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<%@ include file="/jsp/util/telaespera.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<% Boolean semPermissao = (Boolean) session.getAttribute("semPermissao"); %>

<script src="<bean:message key="caminho.js"/>jquery/jquery.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="ExibirGerarArquivoFaturasAgrupadasActionForm" dynamicJavascript="false" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
<script language="JavaScript">
$(document).ready(function(){
	verificarClientes();
});

function verificarClientes(){
	//Se cliente superior esta preenchido desabilita inicial e final e vice-versa
	var form = document.forms[0];
	if (form.clienteResponsavelId.value != ''){
		form.clienteResponsavelId.disabled = false;	
		form.clienteResponsavelInicialId.disabled = true;	
 		form.clienteResponsavelFinalId.disabled = true;	
	} else if (form.clienteResponsavelInicialId.value != '' || form.clienteResponsavelFinalId.value != ''){
		form.clienteResponsavelId.disabled = true;	
		form.clienteResponsavelInicialId.disabled = false;	
 		form.clienteResponsavelFinalId.disabled = false;	
	} else {
		form.clienteResponsavelId.disabled = false;	
		form.clienteResponsavelInicialId.disabled = false;	
 		form.clienteResponsavelFinalId.disabled = false;	
	}
}

function pesquisarClienteSuperior(form, tipo){
	form.tipoPesquisa.value = tipo;
	chamarPopup('exibirPesquisarClienteAction.do', 'cliente', null, null, 275, 480, '',form.clienteResponsavelId.value);
}

function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
	if(objetoRelacionado.readOnly != true){
		if (objeto == null || codigoObjeto == null){
			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		}
		else{
			if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
				alert(msg);
			}
			else{
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
			}
		}
	}
}

function limparDestino(tipo){
	var form = document.forms[0];

	switch(tipo){
		case 1: //Cliente Responsavel			
			form.clienteResponsavelNome.value = "";
			break;
		case 2: //Cliente Responsavel Inicial		
			form.clienteResponsavelInicialNome.value = "";
			break;
		case 3: //Cliente Responsavel Final		
			form.clienteResponsavelFinalNome.value = "";
			break;			   
	}
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    var form = document.forms[0];
	 if (tipoConsulta == 'cliente') {
		 if (form.tipoPesquisa.value == 1) {
	 		form.clienteResponsavelId.value = codigoRegistro;
    	    form.clienteResponsavelNome.value = descricaoRegistro;        	
		 } else if (form.tipoPesquisa.value == 2) {
	 		form.clienteResponsavelInicialId.value = codigoRegistro;
    	    form.clienteResponsavelInicialNome.value = descricaoRegistro;        	
		 } else {
	 		form.clienteResponsavelFinalId.value = codigoRegistro;
    	    form.clienteResponsavelFinalNome.value = descricaoRegistro;        	
		 }
	 }     
	form.action = 'exibirGerarArquivoFaturasAgrupadasAction.do';
    form.submit();
}

function limparCliente(tipo){
	 var form = document.forms[0];
	if (tipo == 1) {
 		form.clienteResponsavelId.value = '';
	    form.clienteResponsavelNome.value = '';   
	    //Habilita tudo
	    form.clienteResponsavelId.disabled = false;	
		form.clienteResponsavelInicialId.disabled = false;	
 		form.clienteResponsavelFinalId.disabled = false;	
	 } else if (tipo == 2) {
 		form.clienteResponsavelInicialId.value = '';
	    form.clienteResponsavelInicialNome.value = '';  
	    
	    if (form.clienteResponsavelFinalId.value == ''){
	    	//Habilita tudo
		    form.clienteResponsavelId.disabled = false;	
			form.clienteResponsavelInicialId.disabled = false;	
	 		form.clienteResponsavelFinalId.disabled = false;
	    }
	 } else {
 		form.clienteResponsavelFinalId.value = '';
	    form.clienteResponsavelFinalNome.value = '';    
	    
	    if (form.clienteResponsavelInicialId.value == ''){
	    	//Habilita tudo
		    form.clienteResponsavelId.disabled = false;	
			form.clienteResponsavelInicialId.disabled = false;	
	 		form.clienteResponsavelFinalId.disabled = false;
	    }
	 }
}

function validarGerarArquivo(form){
	var msg = '';
	
	//Valida datas
	if (form.mesAnoInicial.value ==''){
		msg = msg + 'Informe Referência Inicial do Período. \n';
	}
	if (form.mesAnoFinal.value ==''){
		msg = msg + 'Informe Referência Final do Período. \n';
	}
	if(!validaAnoMesSemAlert(form.mesAnoInicial)){
		form.mesAnoFinal.value = '';
		msg = msg + 'Referência inválida. \n';		
	} else if(!validaAnoMesSemAlert(form.mesAnoFinal)){
		msg = msg + 'Referência inválida. \n';		
	} else if (comparaMesAno(form.mesAnoInicial.value, ">", form.mesAnoFinal.value)){
		msg = msg + "Referência Final do Período é anterior à Referência Inicial do Período. \n";		
	}
	
	//Valida preenchimento de clientes
	if (form.clienteResponsavelId.value ==''){
		if (form.clienteResponsavelInicialId.value ==''){
			if (form.clienteResponsavelFinalId.value ==''){		
				msg = msg + 'Informe Código do Cliente Superior ou Código do Cliente Inicial e Final. \n';
			} else {
				msg = msg + 'Informe Código do Cliente Inicial. \n';
			}
		} else {
			if (form.clienteResponsavelFinalId.value ==''){
				msg = msg + 'Informe Código do Cliente Final. \n';
			} else {
				//Valida se o inicial é menor que o final
				if (parseInt(form.clienteResponsavelInicialId.value) > parseInt(form.clienteResponsavelFinalId.value)){
					msg = msg + 'Código do Cliente Final é anterior ao Código do Cliente Inicial. \n';
				} 
				/* else {
					var diferenca = parseInt(form.clienteResponsavelFinalId.value) - parseInt(form.clienteResponsavelInicialId.value);
					if (diferenca > 1000){
						msg = msg + 'Informe um intervalo de clientes menor que 1000. \n';
					}
				} */
			}
		}
	} else {
		if (form.clienteResponsavelInicialId.value !='' || form.clienteResponsavelFinalId.value !=''){
			msg = msg + 'Informe apenas um critério de geração: Código do Cliente Superior ou Código do Cliente Inicial e Final. \n';
		}
	}
	
	if (msg !=''){
		alert(msg);
		return false;
	} else {
		//form.action = 'gerarArquivoFaturasAgrupadasAction.do';
		//form.submit();
		botaoAvancarTelaEspera('/gsan/gerarArquivoFaturasAgrupadasAction.do');
	}
}
function validateExibirGerarArquivoFaturasAgrupadasActionForm(form){ return true;}
</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="setarFoco('${requestScope.nomeCampo}');">
	
<div id="formDiv">
<html:form action="/gerarArquivoFaturasAgrupadasAction" name="ExibirGerarArquivoFaturasAgrupadasActionForm"
  	type="gsan.gui.cobranca.ExibirGerarArquivoFaturasAgrupadasActionForm" method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
		
	<input type="hidden" name="tipoPesquisa" />
	
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
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Gerar Arquivo Texto Faturas Agrupadas</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td width="30%"><strong>Refer&ecirc;ncia das Faturas:<font color="#FF0000">*</font></strong></td>
					<td colspan="3" align="right">
					<div align="left">
						<html:text  property="mesAnoInicial" 
									maxlength="7" 
									size="7" 
									onkeyup="mascaraAnoMes(this, event);replicarCampo(document.forms[0].mesAnoFinal,this);" 
									onkeypress="return isCampoNumerico(event);"/>
					<strong> a</strong> 
						<html:text  property="mesAnoFinal" 
									maxlength="7" 
									size="7" 
									onkeyup="mascaraAnoMes(this, event);" 
									onkeypress="return isCampoNumerico(event);"/>
					mm/aaaa</div>
					<div align="left"><strong></strong></div>
					<div align="left"></div>
					</td>
				</tr>
				
				<tr>
					<td colspan="5">
					<hr>
					</td>
				</tr>
				<tr>
					<td width="30%"><strong>Código do Cliente Responsável Superior:</strong></td>
					<td colspan="3">
						<html:text  property="clienteResponsavelId" 
									maxlength="9" 
									size="9" 
									onkeyup="verificarClientes();limparDestino(1);"
									onkeypress="javascript:validaEnterComMensagem(event, 'exibirGerarArquivoFaturasAgrupadasAction.do?objetoConsulta=1','clienteResponsavelId','Cliente responsavel');return isCampoNumerico(event);"
									/>
							<a href="javascript:pesquisarClienteSuperior(document.forms[0], 1);" >
								<img width="23" height="21" 
									 src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" 
									 title="Pesquisar Cliente Superior"/></a>
									 
						<logic:notPresent name="clienteInexistente" scope="request">
							<html:text property="clienteResponsavelNome" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notPresent> 

						<logic:present name="clienteInexistente" scope="request">
							<html:text property="clienteResponsavelNome" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:present>
						
						<a href="javascript:limparCliente(1);"> 
							<img border="0" 
								 src="<bean:message key='caminho.imagens'/>limparcampo.gif" 
								 title="Apagar"
								 style="cursor: hand;" /> 
						</a>
					</td>
				</tr>
				
				<tr>
					<td colspan="5">
					<hr>
					</td>
				</tr>
				<tr>
					<td colspan="3">Faixa por Cliente Responsável:</td>
				</tr>
				
				<tr>
					<td width="30%"><strong>Código do Cliente Inicial:</strong></td>
					<td colspan="3">
						<html:text  property="clienteResponsavelInicialId" 
									maxlength="9" 
									size="9" 
									onkeyup="verificarClientes();limparDestino(2);"
									onkeypress="javascript:validaEnterComMensagem(event, 'exibirGerarArquivoFaturasAgrupadasAction.do?objetoConsulta=2','clienteResponsavelInicialId','Cliente responsavel');return isCampoNumerico(event);"
									/>
							<a href="javascript:pesquisarClienteSuperior(document.forms[0], 2);" >
								<img width="23" height="21" 
									 src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" 
									 title="Pesquisar Cliente Inicial"/></a>
									 
						<logic:notPresent name="clienteInicialInexistente" scope="request">
							<html:text property="clienteResponsavelInicialNome" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notPresent> 

						<logic:present name="clienteInicialInexistente" scope="request">
							<html:text property="clienteResponsavelInicialNome" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:present>
						
						<a href="javascript:limparCliente(2);"> 
							<img border="0" 
								 src="<bean:message key='caminho.imagens'/>limparcampo.gif" 
								 title="Apagar"
								 style="cursor: hand;" /> 
						</a>
					</td>
				</tr>
				
				<tr>
					<td width="30%"><strong>Código do Cliente Final:</strong></td>
					<td colspan="3">
						<html:text  property="clienteResponsavelFinalId" 
									maxlength="9" 
									size="9" 
									onkeyup="verificarClientes();limparDestino(3);"
									onkeypress="javascript:validaEnterComMensagem(event, 'exibirGerarArquivoFaturasAgrupadasAction.do?objetoConsulta=3','clienteResponsavelFinalId','Cliente responsavel');return isCampoNumerico(event);"
									/>
							<a href="javascript:pesquisarClienteSuperior(document.forms[0], 3);" >
								<img width="23" height="21" 
									 src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0" 
									 title="Pesquisar Cliente Final"/></a>
									 
						<logic:notPresent name="clienteFinalInexistente" scope="request">
							<html:text property="clienteResponsavelFinalNome" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notPresent> 

						<logic:present name="clienteFinalInexistente" scope="request">
							<html:text property="clienteResponsavelFinalNome" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:present>
						
						<a href="javascript:limparCliente(3);"> 
							<img border="0" 
								 src="<bean:message key='caminho.imagens'/>limparcampo.gif" 
								 title="Apagar"
								 style="cursor: hand;" /> 
						</a>
					</td>
				</tr>				
				
				<tr>
					<td colspan="5">&nbsp;</td>
				</tr>
				<tr>
					
				</tr>
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left" colspan="2"><strong><font color="#FF0000">*</font></strong>

					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
				
				<tr>
				<td colspan="5">
				<table width="100%" border="0">
				<tr>
					<td  class="style1">
						<input name="Submit22"
						class="bottonRightCol" value="Limpar" type="button"
						onclick="window.location.href='/gsan/exibirGerarArquivoFaturasAgrupadasAction.do?menu=sim';">
						&nbsp;
						<input type="button" name="Button" class="bottonRightCol" value="Cancelar" tabindex="6" onClick="javascript:window.location.href='/gsan/telaPrincipal.do'" style="width: 80px"/>
					</td>
					<td align="right">
						<input name="Button" type="button"
						class="bottonRightCol" value="Gerar" onclick="validarGerarArquivo(document.forms[0]);">
					</td>
				</tr>
				</table>
				</td>
				</tr>
				
				<tr>
					<td colspan="5">&nbsp;</td>
				</tr>
			</table>
			<p>&nbsp;</p>

			</td>
		</tr>
	</table>
	
	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</div>
</body>
</html:html>