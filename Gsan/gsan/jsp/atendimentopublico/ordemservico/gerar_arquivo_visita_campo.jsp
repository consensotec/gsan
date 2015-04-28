<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>





<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

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

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="GerarArquivoVisitaCampoActionForm" />

<script type="text/javascript">

	function pesquisarLocalidade(){
		var form = document.forms[0];

		if (form.localidade.disabled == false)  {			
			abrirPopup('exibirPesquisarLocalidadeAction.do', 275, 480);
		}
	}
	
	function limparLocalidade(){
		var form = document.forms[0];
		form.localidade.value = "";
		form.nomeLocalidade.value = "";
		limparSetorComercialOrigem();
		
	}
	
	function limparSetorComercialOrigem(){
		var form = document.forms[0];
		form.codigoSetorComercialOrigem.value = "";
		form.descricaoSetorComercialOrigem.value = "";
		form.codigoSetorComercialDestino.value = "";
		form.descricaoSetorComercialDestino.value = "";
		limparQuadraInicial();
	}
	
	function limparSetorComercialOrigemTecla(){
		var form = document.forms[0];
		form.descricaoSetorComercialOrigem.value = "";
		form.descricaoSetorComercialDestino.value = "";
		limparQtdOsSeletiva();
	}
	
	function limparSetorComercialDestino(){
		var form = document.forms[0];
		form.codigoSetorComercialDestino.value = "";
		form.descricaoSetorComercialDestino.value = "";		
		form.codigoQuadraFinal.value = "";
		limparQtdOsSeletiva();
	}
	
	function limparSetorComercialDestinoTecla(){
		var form = document.forms[0];
		form.descricaoSetorComercialDestino.value = "";
		limparQtdOsSeletiva();
	}
	
	function limparQuadraInicial(){
		var form = document.forms[0];
		form.codigoQuadraInicial.value = "";	
		form.codigoQuadraFinal.value = "";
		document.getElementById('qdrIniIne').style.visibility = 'hidden';
		document.getElementById('qdrFinIne').style.visibility = 'hidden';
		limparQtdOsSeletiva();

	}
	
	function limparQuadraInicialTecla(){
		var form = document.forms[0];
		document.getElementById('qdrIniIne').style.visibility = 'hidden';
		document.getElementById('qdrFinIne').style.visibility = 'hidden';
	
	}
	
	function limparQuadraFinal(){
		var form = document.forms[0];		
		document.getElementById('qdrFinIne').style.visibility = 'hidden';
		form.codigoQuadraFinal.value = "";
	
	}
	
	function limparQuadraFinalTecla(){
		var form = document.forms[0];
		document.getElementById('qdrFinIne').style.visibility = 'hidden';
		
	}

	function pesquisarSetorComercialOrigem(){
		var form = document.forms[0];

		if (form.codigoSetorComercialOrigem.disabled == false) {
			form.tipoPesquisa.value = 'origem';			
			abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+form.localidade.value, form.localidade.value, 'Localidade', 275, 480);
		}
	}
	function pesquisarSetorComercialDestino(){
		var form = document.forms[0];
		form.tipoPesquisa.value = 'destino';
		abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+form.localidade.value, form.localidade.value, 'Localidade', 275, 480);
		
	}
	
	function pesquisarQuadraInicial(){
		var form = document.forms[0];

		if (form.codigoQuadraInicial.disabled == false) {
			form.tipoPesquisa.value = 'origem';
			abrirPopupDependencia('exibirPesquisarQuadraAction.do?consulta=sim&codigoSetorComercial='+form.codigoSetorComercialOrigem.value+'&idLocalidade='+form.localidade.value, 
			form.codigoSetorComercialOrigem.value, 'Setor Comercial Inicial', 275, 480);
		}
	}
	
	function pesquisarQuadraFinal(){
		var form = document.forms[0];

		if (form.codigoQuadraFinal.disabled == false){
			form.tipoPesquisa.value = 'destino';			
			abrirPopupDependencia('exibirPesquisarQuadraAction.do?consulta=sim&codigoSetorComercial='+form.codigoSetorComercialOrigem.value+'&idLocalidade='+form.localidade.value, 
			form.codigoSetorComercialDestino.value, 'Setor Comercial Final', 275, 480);
		}
	}
	
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta){
		var form = document.forms[0];		
		if (tipoConsulta == 'localidade'){
	      form.localidade.value = codigoRegistro;
		  form.nomeLocalidade.value = descricaoRegistro;	     
		  form.nomeLocalidade.style.color = "#000000";
		}
		
		else if (tipoConsulta == 'setorComercial'){
			if (form.tipoPesquisa.value == 'origem'){
	    			form.codigoSetorComercialOrigem.value = codigoRegistro;
					form.descricaoSetorComercialOrigem.value = descricaoRegistro;
					form.descricaoSetorComercialOrigem.style.color = "#000000";
					form.codigoSetorComercialDestino.value = codigoRegistro;
					form.descricaoSetorComercialDestino.value = descricaoRegistro;
					form.descricaoSetorComercialDestino.style.color = "#000000";
					verificaIgualdadeSetor();					
				} else if(form.tipoPesquisa.value == 'destino'){
					form.codigoSetorComercialDestino.value = codigoRegistro;
					form.descricaoSetorComercialDestino.value = descricaoRegistro;
					form.descricaoSetorComercialDestino.style.color = "#000000";
					verificaIgualdadeSetor();
				}
		}
		else if (tipoConsulta == 'quadra'){
    		if (form.tipoPesquisa.value == 'origem') {
    			form.codigoQuadraInicial.value = codigoRegistro;
				form.codigoQuadraFinal.value = codigoRegistro;							
			} else if(form.tipoPesquisa.value == 'destino'){
				form.codigoQuadraFinal.value = codigoRegistro;				
			}
		}
	}
	function verificaIgualdadeSetor(){
		var form = document.forms[0];
		var codSetOri = form.codigoSetorComercialOrigem;
		var codSetDest = form.codigoSetorComercialDestino;
		if(codSetOri.value != codSetDest.value){
			form.codigoQuadraInicial.value='';
			form.codigoQuadraFinal.value='';
			form.codigoQuadraInicial.readOnly = true;
			form.codigoQuadraFinal.readOnly = true;
		}
		else{
			form.codigoQuadraInicial.readOnly = false;
			form.codigoQuadraFinal.readOnly = false;
		}
	}
	function limparTudo(){
		var form = document.forms[0];
		form.nomeLocalidade.value = "";
		form.codigoSetorComercialOrigem.value = "";
		form.descricaoSetorComercialOrigem.value = "";
		form.codigoSetorComercialDestino.value = "";
		form.descricaoSetorComercialDestino.value = "";
		form.codigoQuadraInicial.value='';
		form.codigoQuadraFinal.value='';
		form.qtdOsSeletiva.value='';
	}
	
	function limparQtdOsSeletiva(){
		var form = document.forms[0];
		form.qtdOsSeletiva.value='';
	}
	
	function validarForm(id){		
		var form = document.forms[0];
		var codSetOri = form.codigoSetorComercialOrigem;
		var codSetDest = form.codigoSetorComercialDestino;
		var qdrIni = form.codigoQuadraInicial;
		var qdrFin = form.codigoQuadraFinal;
		var loc = form.localidade;
		
		if(loc.value == "" || codSetOri.value == "" || codSetDest.value== ""){
			alert('Campos obrigatórios não preenchidos');
		}		
		else if(parseInt(codSetOri.value) > parseInt(codSetDest.value)){
			alert('Setor Comercial Final deve ser maior ou igual ao Setor Comercial Inicial');
		}
		else if((codSetOri.value == codSetDest.value) && (qdrIni.value == '' || qdrFin.value == '')){
				alert('Informar intervalo das quadras');			
		}		
		else if(parseInt(qdrIni.value) > parseInt(qdrFin.value)){
			alert('Quadra Inicial maior que a Quadra Final');	
		}				
		else{
			if(id == 1){				
				form.action='gerarArquivoTextoVisitaCampo.do';
				submeterFormPadrao(form);
			}
			else if(id == 2){
				form.action='exibirGerarArquivosVisitaCampoAction.do?tipoPesquisa=consultarQdt';
				submeterFormPadrao(form);
			}
		}
	}

	function gerarRelatorio(){
		var form = document.forms[0];
		var codSetOri = form.codigoSetorComercialOrigem;
		var codSetDest = form.codigoSetorComercialDestino;
		var qdrIni = form.codigoQuadraInicial;
		var qdrFin = form.codigoQuadraFinal;

		if(codSetOri.value !='' && codSetDest.value == ''){
			alert('Preencha o Setor Comercial Final');	
		}
		else if(codSetOri.value =='' && codSetDest.value != ''){
			alert('Preencha o Setor Comercial Inicial');
		}
		else if(parseInt(codSetOri.value) > parseInt(codSetDest.value)){
			alert('Setor Comercial Final deve ser maior ou igual ao Setor Comercial Inicial');
		}
		else if(qdrIni.value !='' && qdrFin.value == ''){
			alert('Preencha a Quadra Final');
		}
		else if(qdrIni.value =='' && qdrFin.value != ''){
			alert('Preencha a Quadra Inicial');
		}
		else if(parseInt(qdrIni.value) > parseInt(qdrFin.value)){
			alert('Quadra Inicial maior que a Quadra Final');	
		}				
		else{
			toggleBox('demodiv',1);
		}
	}
	
	
</script>

</head>
<body leftmargin="5" topmargin="5" onload="javascript:verificaIgualdadeSetor();">

<html:form action="/exibirGerarArquivosVisitaCampoAction.do"
	name="GerarArquivoVisitaCampoActionForm"
	type="gsan.gui.atendimentopublico.ordemservico.GerarArquivoVisitaCampoActionForm"
	method="post">

	<input type="hidden" name="tipoPesquisa" />
	<html:hidden property="comando"/>

	<%@ include file="/jsp/util/cabecalho.jsp"%>

	<%@ include file="/jsp/util/menu.jsp"%>

	<input type="hidden" name="tipoPesquisa" />

	<table width="770" border="0" cellpadding="0" cellspacing="5">

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

			<table height="100%">

				<tr>

					<td></td>

				</tr>

			</table>

			<table width="100%" border="0" align="center" cellpadding="0"

				cellspacing="0">

				<tr>

					<td width="11"><img

						src="<bean:message key="caminho.imagens"/>parahead_left.gif"

						border="0" /></td>

					<td class="parabg">Gerar Arquivo Texto para as Ordens de Serviço da Visita</td>

					<td width="11" valign="top"><img

						src="<bean:message key="caminho.imagens"/>parahead_right.gif"

						border="0" /></td>

				</tr>

			</table>

			<p>&nbsp;</p>

			<table width="100%" border="0">

				<tr>

					<td colspan="3">Para gerar o arquivo texto para as ordens de serviço de visita, informe os dados abaixo:</td>

				</tr>
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td colspan="3">
						<strong>
							Comando para Inspeção de Anormalidade: 
							<bean:write name="GerarArquivoVisitaCampoActionForm" property="comandoDesc"/>
						</strong>
					</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				<tr bgcolor="#99CCFF" >					
					<td height="18" colspan="2">
						<div align="left">
							<strong>
								<span class="style2"> Filtro pra Geração do Arquivo TXT </span>
							</strong>
						</div>
					</td>
				</tr>
				

				<tr>

					<td width="26%">
						<strong>Localidade:<font color="#FF0000">*</font></strong>
					</td>
					<td>
						<html:text maxlength="3" property="localidade"
						size="3"
						onkeypress="validaEnter(event, 'exibirGerarArquivosVisitaCampoAction.do?tipoPesquisa=localidade', 'localidade');
						return isCampoNumerico(event);"
						onkeyup="limparTudo(); return isCampoNumerico(event);"
						onchange="validarCampoNumericoComMensagemLimpandoCampo(document.forms[0].localidade, 'Localidade');"						
						tabindex="6" /> 
						<a href="javascript:pesquisarLocalidade();"> 
							<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Localidade" /></a>
						<logic:present name="localidadeInexistente" scope="request">
							<html:text property="nomeLocalidade" size="40" maxlength="40" readonly="true" 
								style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
						</logic:present> 
						<logic:notPresent name="localidadeInexistente" scope="request">
							<html:text property="nomeLocalidade" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notPresent> 
						<a href="javascript:limparLocalidade();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar Localidade" />
						</a>
					
					</td>

				</tr>

			<tr>
					<td><strong>Setor Comercial Inicial:<font color="#FF0000">*</font></strong></td>
					<td>					
					<html:text maxlength="3" property="codigoSetorComercialOrigem" size="3"
						onkeypress="
						validaEnterDependenciaVerificaCampoNumerico(event, 'exibirGerarArquivosVisitaCampoAction.do?tipoPesquisa=setorComercial&tipoSetor=1', document.forms[0].codigoSetorComercialOrigem, 'Setor Comercial Inicial', document.forms[0].localidade.value, 'Localidade');
						replicarCampo(form.codigoSetorComercialDestino, form.codigoSetorComercialOrigem);
						verificaIgualdadeSetor();
						return isCampoNumerico(event);"
						onchange="
						validaEnterDependenciaVerificaCampoNumerico(event, 'exibirGerarArquivosVisitaCampoAction.do?tipoPesquisa=setorComercial&tipoSetor=1', document.forms[0].codigoSetorComercialOrigem, 'Setor Comercial Inicial', document.forms[0].localidade.value, 'Localidade');
						replicarCampo(form.codigoSetorComercialDestino, form.codigoSetorComercialOrigem);
						verificaIgualdadeSetor();"
						tabindex="5" onkeyup="javascript:replicarCampo(form.codigoSetorComercialDestino, form.codigoSetorComercialOrigem);limparSetorComercialOrigemTecla();"
						/>
					<a href="javascript:pesquisarSetorComercialOrigem();"> 
						<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Setor Comercial Inicial" /></a>
					<logic:present
						name="setorComercialOrigemInexistente" scope="request">
						<html:text property="descricaoSetorComercialOrigem" size="40"
							maxlength="40" readonly="true"
							style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
					</logic:present> 
					<logic:notPresent
						name="setorComercialOrigemInexistente" scope="request">
						<html:text property="descricaoSetorComercialOrigem" size="40"
							maxlength="40" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a
						href="javascript:limparSetorComercialOrigem();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar Setor Comercial Inicial" /></a></td>
				</tr>
				<tr>
					<td><strong>Quadra Inicial:</strong></td>
					<td><html:text maxlength="3" property="codigoQuadraInicial" size="3"
						onkeypress="
						validaEnterDependenciaVerificaCampoNumerico(event, 'exibirGerarArquivosVisitaCampoAction.do?tipoPesquisa=quadra&tipoQuadra=1', document.forms[0].codigoQuadraInicial, 'Quadra Inicial', document.forms[0].codigoSetorComercialOrigem.value, 'Setor Comercial Inicial');
						replicarCampo(form.codigoQuadraFinal, form.codigoQuadraInicial); 
						return isCampoNumerico(event);"
						onchange="validaEnterDependenciaVerificaCampoNumerico(event, 'exibirGerarArquivosVisitaCampoAction.do?tipoPesquisa=quadra&tipoQuadra=1', document.forms[0].codigoQuadraInicial, 'Quadra Inicial', document.forms[0].codigoSetorComercialOrigem.value, 'Setor Comercial Inicial');
						replicarCampo(form.codigoQuadraFinal, form.codigoQuadraInicial);"
						tabindex="8"
						onkeyup="javascript:replicarCampo(form.codigoQuadraFinal, form.codigoQuadraInicial);
						limparQuadraInicialTecla();
						limparQtdOsSeletiva();"						
						 />
						 <%--
						<a href="javascript:pesquisarQuadraInicial();"> <img
							width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Quadra" /></a>
						--%> 
						<logic:present name="quadraInicialInexistente" scope="request">
							<font color="#ff0000" id="qdrIniIne">QUADRA INEXISTENTE</font>
						</logic:present>
						<%--
						<logic:notPresent name="quadraInicialInexistente" scope="request">
							<html:text property="descricaoQuadraInicial" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notPresent>
						 <a
							href="javascript:limparQuadraInicial();limparTotalizacao();"> <img
							src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar Quadra Inicial" /></a>
							--%>
					</td>
				</tr>
				<tr>
					<td><strong>Setor Comercial Final:<font color="#FF0000">*</font></strong></td>
					<td><html:text maxlength="3" property="codigoSetorComercialDestino"
						size="3"
						onkeypress="
						validaEnterDependenciaVerificaCampoNumerico(event, 'exibirGerarArquivosVisitaCampoAction.do?tipoPesquisa=setorComercial&tipoSetor=2', document.forms[0].codigoSetorComercialDestino, 'Setor Comercial Final', document.forms[0].localidade.value, 'Localidade '); 
						verificaIgualdadeSetor();
						return isCampoNumerico(event);"
						onchange="
						validaEnterDependenciaVerificaCampoNumerico(event, 'exibirGerarArquivosVisitaCampoAction.do?tipoPesquisa=setorComercial&tipoSetor=2', document.forms[0].codigoSetorComercialDestino, 'Setor Comercial Final', document.forms[0].localidade.value, 'Localidade '); 
						verificaIgualdadeSetor();"						
						tabindex="7" onkeyup="limparSetorComercialDestinoTecla();" /> <a
						href="javascript:pesquisarSetorComercialDestino();"> <img
						width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Setor Comercial Final" /></a> <logic:present
						name="setorComercialDestinoInexistente" scope="request">
						<html:text property="descricaoSetorComercialDestino" size="40"
							maxlength="40" readonly="true"
							style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
					</logic:present> <logic:notPresent
						name="setorComercialDestinoInexistente" scope="request">
						<html:text property="descricaoSetorComercialDestino" size="40"
							maxlength="40" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a
						href="javascript:limparSetorComercialDestino();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar Setor Comercial Final" /></a></td>
				</tr>
				
				<tr>
					<td><strong>Quadra Final:</strong></td>
					<td><html:text maxlength="3" property="codigoQuadraFinal" size="3"
						onkeypress="
						validaEnterDependenciaVerificaCampoNumerico(event, 'exibirGerarArquivosVisitaCampoAction.do?tipoPesquisa=quadra&tipoQuadra=2', document.forms[0].codigoQuadraFinal, 'Quadra Final', document.forms[0].codigoSetorComercialDestino.value, 'Setor Comercial Final');
						return isCampoNumerico(event);"
						onchange="
						validaEnterDependenciaVerificaCampoNumerico(event, 'exibirGerarArquivosVisitaCampoAction.do?tipoPesquisa=quadra&tipoQuadra=2', document.forms[0].codigoQuadraFinal, 'Quadra Final', document.forms[0].codigoSetorComercialDestino.value, 'Setor Comercial Final');"
						tabindex="8" onkeyup="javascript:limparQuadraFinalTecla();limparQtdOsSeletiva();" 
						 />
						 <logic:present name="quadraFinalInexistente" scope="request">
							<font color="#ff0000" id="qdrFinIne">QUADRA INEXISTENTE</font>
						</logic:present>
						<%--
						<a href="javascript:pesquisarQuadraFinal();"> <img
							width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Quadra" /></a>
							
						<logic:present name="quadraFinalInexistente" scope="request">
						<html:text property="descricaoQuadraFinal" size="40"
							maxlength="40" readonly="true"
							style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
						</logic:present>
						<logic:notPresent name="quadraFinalInexistente" scope="request">
							<html:text property="descricaoQuadraFinal" size="40"
								maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notPresent>
						 <a
							href="javascript:limparQuadraFinal();limparTotalizacao();"> <img
							src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar Quadra Final" /></a>
							--%>
					</td>
				</tr>	
				
				<tr>

					<td>

					<p>&nbsp;</p>

					</td>

					<td colspan="2"></td>

				</tr>

			</table>				
			<table>				
				<tr>
					<td>
					<input type="button" class="bottonRightCol" value="Consultar Quantidade"
							onclick="javascript:validarForm(2)" style="margin-left: 17px;">
					</td>
					<td>
						<html:text property="qtdOsSeletiva"  readonly="true" size="3"/>
					</td>
					
				</tr>
				<tr>

					<td>

					<p>&nbsp;</p>

					</td>

					<td colspan="2"></td>

				</tr>				
			</table>
				<table align="center">
					<tr>
						<td colspan="2" align="center">
							<br />
							<br />
							<font color="#FF0000" style="margin-left: 17px;">*</font> 						
							Campos Obrigat&oacute;rios
						</td>
	
					</tr>
				</table>
				<hr />
			<table>

				<tr>

					<td width="100%">

					<table width="30%" border="0" align="right" cellpadding="0"

						cellspacing="2">

						<tr>

							<td width="61">&nbsp;</td>

							<td width="416">
								<input type="button" class="bottonRightCol" value="Voltar"
								onclick="window.location.href='/gsan/exibirConsultarComandosOSSeletivaInspecaoAnormalidadeAction.do?menu=sim'" />
							</td>

							<td width="12">
								<input type="button" class="bottonRightCol" value="Cancelar" style="margin-right: 220px;"
								onclick="window.location.href='/gsan/telaPrincipal.do'" />
								
							</td>
							<td width="61">							
							<input type="button" class="bottonRightCol" onclick="javascript:gerarRelatorio();" value="Gerar Relat&oacute;rio" />							
							</td>
							<td width="12">								
								<input type="button" class="bottonRightCol" onclick="javascript:validarForm(1)" value="Gerar Arquivo Txt" />
								
							</td>
						</tr>
					</table>

					</td>

				</tr>

			</table>

			<p>&nbsp;</p>

			</td>

		</tr>

	</table>
	
<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioQuantImovOSSeletivaAction.do"/>
	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>

</body>

</html:html>

