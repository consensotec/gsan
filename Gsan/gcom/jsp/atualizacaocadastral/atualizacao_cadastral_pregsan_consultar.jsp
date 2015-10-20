<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="gcom.util.ConstantesSistema"%>
<%@page import="gcom.atualizacaocadastral.bean.DadosImovelPreGsanHelper"  isELIgnored="false"%>


<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="ConsultarImoveisPreGsanActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>scroll_horizontal.js"></script>

<script language="JavaScript">
<!--
 
var bCancel = false; 

function gerarRelatorioImoveisInconsistentes(){
	var form = document.forms[0];

	if ( form.idEmpresa.value != null && form.idEmpresa.value != '-1' ) {
		enviarSelectMultiplo('ConsultarImoveisPreGsanActionForm','idQuadraSelecionados');
		botaoAvancarTelaEspera('/gsan/gerarRelatorioImoveisInconsistentesAction.do');
	}  else {
		alert("O campo Empresa é obrigatório")
	}
}

function gerarRelatorioImoveisInconsistentesResumo(){
	var form = document.forms[0];
	if(form.indicadorTipoSelecao[0].checked){
		if ( form.idEmpresa.value != null && form.idEmpresa.value != '-1' ) {
			enviarSelectMultiplo('ConsultarImoveisPreGsanActionForm','idQuadraSelecionados');
			botaoAvancarTelaEspera('/gsan/gerarRelatorioImoveisInconsistentesAction.do?resumo=true');
		}  else {
			alert("O campo Empresa é obrigatório")
		}
	}else{
		alert("Relatório válido apenas para	Imóveis com Ocorrência Cadastro");
	}
}

function alterarTodosImoveisPossiveis(selectAtual){
	var id = selectAtual.id;
	var acao = selectAtual.options[selectAtual.selectedIndex].value;
	//alert(acao);	
	var linhasTabela = document.getElementById('tabelaImoveis').getElementsByTagName('tr');
	//alert(linhasTabela.length);
	for(var i=0; i<linhasTabela.length; i++){
		var colunasLinha = linhasTabela[i].getElementsByTagName('td');
		//pegando o id do imovel
		var idImovel = colunasLinha[0].getElementsByTagName('select')[0].id;
		var selectAtual = colunasLinha[0].getElementsByTagName('select')[0];
		//verificando se ele tem alguma situação
		var situacao = colunasLinha[1].innerHTML.trim();
		//if(situacao == '-'){
			//alert("id:"+idImovel+" situacao:"+situacao);
			//adicionarItemAlteradoAcaoId(situacao, idImovel);
			var count = 0;
			for(var j=0; j<selectAtual.options.length; j++){
				//alert(selectAtual.options[j].value);
				if(selectAtual.options[j].value == acao){
					selectAtual.options[j].selected = true;
					adicionarItemAlterado(selectAtual);
					count++;
				} 
			}
			if(count==0){
				selectAtual.options[0].selected = true;
				adicionarItemAlterado(selectAtual);
			}
		//}
	}
}

function adicionarItemAlterado(selectAtual){
	var id = selectAtual.id;
	var acao = selectAtual.options[selectAtual.selectedIndex].value;
	var item = id + "|" + acao + ";"; 
	var form = document.forms[0];
	if(form.imoveisParaAtualizar.value.length > 0){
		if(form.imoveisParaAtualizar.value.indexOf(id)>-1){
			var listaSplit = form.imoveisParaAtualizar.value.split(";");
			var novaLista = "";
			for(var i=0; i<listaSplit.length; i++){
				if(listaSplit[i].length>0){
					if(listaSplit[i].indexOf(id)>-1){
						listaSplit[i] = id + "|" + acao;
						novaLista+=listaSplit[i]+";";
					}else{
						novaLista+=listaSplit[i]+";";
					}
				}
			}
			form.imoveisParaAtualizar.value = novaLista;
		}else{
			form.imoveisParaAtualizar.value += item;
		}
	}else{
		form.imoveisParaAtualizar.value += item;
	}
	//alert(form.imoveisParaAtualizar.value);	
}

function associarMatriculaGsanImovelAtlzCadastral(idImovelAtlzCadastral, matriculaGsan){
	var id = idImovelAtlzCadastral;
	var acao = matriculaGsan;
	var item = id + "|" + acao + ";"; 
	var form = document.forms[0];
	if(form.matriculasGsanAssociadas.value.length > 0){
		if(form.matriculasGsanAssociadas.value.indexOf(id)>-1){
			var listaSplit = form.matriculasGsanAssociadas.value.split(";");
			var novaLista = "";
			for(var i=0; i<listaSplit.length; i++){
				if(listaSplit[i].length>0){
					if(listaSplit[i].indexOf(id)>-1){
						listaSplit[i] = id + "|" + acao;
						novaLista+=listaSplit[i]+";";
					}else{
						novaLista+=listaSplit[i]+";";
					}
				}
			}
			form.matriculasGsanAssociadas.value = novaLista;
		}else{
			form.matriculasGsanAssociadas.value += item;
		}
	}else{
		form.matriculasGsanAssociadas.value += item;
	}
	//alert(form.matriculasGsanAssociadas.value);	
}

function validateConsultarImoveisPreGsanActionForm(form) {                                                                   
	if (bCancel) 
    	return true; 
   	else 
    	return true; 
} 

function limparFormulario(form) {
	form.action = "exibirConsultarImoveisPreGsanAction.do?objetoConsulta=limpar";
	form.submit();
}

function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,campo){
	if(!campo.disabled){
  		if (objeto == null || codigoObjeto == null){
     		if(tipo == "" ){
      			abrirPopup(url,altura, largura);
     		}else{
	  			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
	 		}
 		}else{
			if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
				alert(msg);
			}else{
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
			}
		}
	}
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

	var form = document.forms[0];

	if (tipoConsulta == 'localidadeOrigem') {

  		form.idLocalidade.value = codigoRegistro;
  		form.descricaoLocalidade.value = descricaoRegistro;
  		
	}
}


function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro, codigoRegistro, tipoConsulta) {

	var form = document.forms[0];

	if (tipoConsulta == 'setorComercialOrigem') {
	  	form.idSetorComercial.value = codigoRegistro;
	  	form.descricaoSetorComercial.value = descricaoRegistro;
	  	form.descricaoSetorComercial.style.color = "#000000"; 

	  	botaoAvancarTelaEspera('/gsan/exibirConsultarImoveisPreGsanAction.do?objetoConsulta=4');
	}
}	


function pesquisar(){
	var form = document.forms[0];

	if ( form.idEmpresa.value != null && form.idEmpresa.value != '-1' ) {
		enviarSelectMultiplo('ConsultarImoveisPreGsanActionForm','idQuadraSelecionados');
		botaoAvancarTelaEspera('/gsan/exibirConsultarImoveisPreGsanAction.do?objetoConsulta=pesquisar');
	}  else {
		alert("O campo Empresa é obrigatório")
	}
}

function validarCpfCnpjRF() {
	var form = document.forms[0];

	form.indicadorValidarCpfCnpjRF.value = "1";
	botaoAvancarTelaEspera('/gsan/exibirConsultarImoveisPreGsanAction.do');
	
}

function validarForm(){
	botaoAvancarTelaEspera('/gsan/atualizarImovelPreGsanAction.do');
}

function limpar(tipo){

	var form = document.forms[0];

	if ( tipo == '1') {

		form.action = "exibirConsultarImoveisPreGsanAction.do?limpar=localidade";
		form.submit();
		
	} else if ( tipo == '2' ) {
		form.action = "exibirConsultarImoveisPreGsanAction.do?limpar=setor";
		form.submit();
	}
	
}

function carregarSituacao(){
	var form = document.forms[0];
	form.action = "exibirConsultarImoveisPreGsanAction.do?carregar=situacao";
	form.submit();
}

function carregarCadastrador(){
	var form = document.forms[0];

	if ( form.idEmpresa.value != null && form.idEmpresa.value != '-1' ) {
		form.action = "/gsan/exibirConsultarImoveisPreGsanAction.do?pesquisarCadastradores=sim";
		form.submit();
	}
}

function tipoSelecaoChange(){
	var form = document.forms[0];
	
	if(form.indicadorTipoSelecao[0].checked){
		document.getElementById("botaoResumo").disabled= false;			
	}else{
		document.getElementById("botaoResumo").disabled= true;
	}

}

-->
</script>
</head>
<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');tipoSelecaoChange();">
<div id="formDiv">
<html:form action="/atualizarImovelPreGsanAction" 
	name="ConsultarImoveisPreGsanActionForm" 
	type="gcom.gui.atualizacaocadastral.ConsultarImoveisPreGsanActionForm"
	method="post"
	onsubmit="return validateConsultarImoveisPreGsanActionForm(this);">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<html:hidden property="indicadorValidarCpfCnpjRF"/>
	<html:hidden property="indicadorSituacaoTodosHabilitado"/>
	
	<html:hidden property="imoveisParaAtualizar"/>
	<html:hidden property="matriculasGsanAssociadas"/>
	

	<table width="770" border="0" cellspacing="5" cellpadding="0">
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
			
			
				<table width="100%" 
					border="0" 
					align="center" 
					cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
						</td>
						<td class="parabg">Consultar Imóveis no Ambiente Pré GSAN</td>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
						</td>
					</tr>
				</table>
				
				<p>&nbsp;</p>
			
				<table bordercolor="#000000" width="100%" cellspacing="0">
					<tr>
						<td>
							<p>Para consultar os imóveis no ambiente Pré-GSAN, informe os dados abaixo:</p>
						</td>
					</tr>
	        	</table>
	        	<p>&nbsp;</p>
	        	
	        	<table bordercolor="#000000" width="100%" cellspacing="0">
					<tr>
						<td>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<strong>Empresa:<font color="#FF0000">*</font></strong>
								</td>
								
								<td align="left">
									<html:select property="idEmpresa" onchange="carregarCadastrador();" style="width: 200px;">
										
										<html:option value="-1">&nbsp;</html:option>
										<logic:present name="colecaoEmpresa" scope="request">					
											<html:options collection="colecaoEmpresa" 
														  labelProperty="descricao" 
														  property="id"/>
										</logic:present>
									</html:select>
								</td>
							</tr>

							<tr>
								<td><strong>Localidade:</strong></td>
								
								<td>
									<html:text  maxlength="3" 
												tabindex="4"
												property="idLocalidade" 
												size="3"
												onkeypress="javascript:validaEnterComMensagem(event, 'exibirConsultarImoveisPreGsanAction.do?objetoConsulta=2', 'idLocalidade','Localidade');return isCampoNumerico(event);"/>
										
									<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'origem', null, null, 275, 480, '',document.forms[0].idLocalidade);">
										<img width="23" 
											height="21" 
											border="0" 
											style="cursor:hand;"
											src="<bean:message key="caminho.imagens"/>pesquisa.gif"
											title="Pesquisar Localidade" /></a>
			
									<logic:present name="localidadeEncontrada" scope="request">
										<html:text property="descricaoLocalidade"  
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000" 
											size="50" 
											maxlength="50" />
									</logic:present> 
				
									<logic:notPresent name="localidadeEncontrada" scope="request">
										<html:text property="descricaoLocalidade" 
											size="50"
											maxlength="50" 
											readonly="true"
											style="background-color:#EFEFEF;border:0;"/>
									</logic:notPresent>
									
									<a href="javascript:limpar(1);"> 
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
											border="0" 
											title="Apagar" /></a>
								</td>
							</tr>
							
							<tr>
								<td><strong>Setor Comercial:</strong></td>
								
								<td>
									<html:text maxlength="3" 
										tabindex="5"
										property="idSetorComercial" 
										size="3"
										onkeypress="javascript:validaEnterComMensagem(event, 'exibirConsultarImoveisPreGsanAction.do?limparQuadras=ok&objetoConsulta=3', 'idSetorComercial','Setor Comercial');return isCampoNumerico(event);"/>
										
									<a href="javascript:chamarPopup('exibirPesquisarSetorComercialAction.do', 'setorComercialOrigem', 'idLocalidade', document.forms[0].idLocalidade.value , 275, 480, 'Informe Localidade Inicial',document.forms[0].idSetorComercial);">
										<img width="23" 
											height="21" 
											border="0" 
											style="cursor:hand;"
											src="<bean:message key="caminho.imagens"/>pesquisa.gif"
											title="Pesquisar Setor Comercial" /></a>
											
									<logic:present name="setorEncontrado" scope="request">
										<html:text property="descricaoSetorComercial"  
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000" 
											size="50" 
											maxlength="50" />
									</logic:present> 
				
									<logic:notPresent name="setorEncontrado" scope="request">
										<html:text property="descricaoSetorComercial" 
											size="50"
											maxlength="50" 
											readonly="true"
											style="background-color:#EFEFEF; border:0;" />
									</logic:notPresent>
									
									<a href="javascript:limpar(2);"> 
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
											border="0" 
											title="Apagar" /></a>
								</td>
							</tr>
							
							<tr>
								<td width="120">
									<strong>Quadras:</strong>
								</td>
								<td colspan="2">
								<table width="100%" border=0 cellpadding=0 cellspacing=0 align="left">
									<tr>
										<td width="110">
										
											<div align="left"><strong>Disponíveis</strong></div>
			
											<div id='disponiveis' style="OVERFLOW: auto;WIDTH: 70px;HEIGHT: 120px" onscroll="OnDivScroll(document.forms[0].idQuadra, 6);" >
											
												<html:select property="idQuadra" size="6" multiple="true" onfocus="OnSelectFocus(this, document.getElementById('disponiveis'), 6);">
													<logic:present name="colecaoQuadras" scope="request">	
														<html:options collection="colecaoQuadras" 
															labelProperty="numeroQuadra" 
															property="numeroQuadra"/>
													</logic:present>
												</html:select>
												
												
											</div>
										</td>
			
										<td width="5" valign="center"><br>
											<table width="50" align="center">
												<tr>
													<td align="center">
														<input type="button" 
															class="bottonRightCol"
															onclick="javascript:MoverTodosDadosSelectMenu1PARAMenu2('ConsultarImoveisPreGsanActionForm', 'idQuadra', 'idQuadraSelecionados');
															OnDivScroll(document.forms[0].idQuadra, 6); OnDivScroll(document.forms[0].idQuadraSelecionados, 6);"
															value=" &gt;&gt; ">
													</td>
												</tr>
				
												<tr>
													<td align="center">
														<input type="button" 
															class="bottonRightCol"
															onclick="javascript:MoverDadosSelectMenu1PARAMenu2('ConsultarImoveisPreGsanActionForm', 'idQuadra', 'idQuadraSelecionados');
															OnDivScroll(document.forms[0].idQuadra, 6); OnDivScroll(document.forms[0].idQuadraSelecionados, 6);"
															value=" &nbsp;&gt;  ">
													</td>
												</tr>
				
												<tr>
													<td align="center">
														<input type="button" 
															class="bottonRightCol"
															onclick="javascript:MoverDadosSelectMenu2PARAMenu1('ConsultarImoveisPreGsanActionForm', 'idQuadra', 'idQuadraSelecionados');
															OnDivScroll(document.forms[0].idQuadra, 6); OnDivScroll(document.forms[0].idQuadraSelecionados, 6);"
															value=" &nbsp;&lt;  ">
													</td>
												</tr>
				
												<tr>
													<td align="center">
														<input type="button" 
															class="bottonRightCol"
															onclick="javascript:MoverTodosDadosSelectMenu2PARAMenu1('ConsultarImoveisPreGsanActionForm', 'idQuadra', 'idQuadraSelecionados');
															OnDivScroll(document.forms[0].idQuadra, 6); OnDivScroll(document.forms[0].idQuadraSelecionados, 6);"
															value=" &lt;&lt; ">
													</td>
												</tr>
											</table>
										</td>
			
										<td>
											<div align="left">
												<strong>Selecionados</strong>
											</div>
											
											<div id='selecionados' style="OVERFLOW: auto;WIDTH: 70px;HEIGHT: 120px" onscroll="OnDivScroll(document.forms[0].idQuadraSelecionados, 6);" >
											
												<html:select property="idQuadraSelecionados" size="6" multiple="true" onfocus="OnSelectFocus(this, document.getElementById('selecionados'), 6);">
													<logic:present name="colecaoQuadrasSelecionadas" scope="request">	
														<html:options collection="colecaoQuadrasSelecionadas" 
															labelProperty="numeroQuadra" 
															property="numeroQuadra"/>
													</logic:present>
												</html:select>
											
											</div>
											
										</td>
									</tr>
								</table>
								</td>
							</tr>
								
							
							<tr>
								<td>
									<strong>Ocorrência Cadastro:</strong>
								</td>
								<td align="left">
									<html:select property="idCadastroOcorrencia" tabindex="8" style="width: 200px;">
										<html:option value="-1">&nbsp;</html:option>
											<logic:present name="colecaoCadastroOcorrencia" scope="request">					
												<html:options collection="colecaoCadastroOcorrencia" 
															  labelProperty="descricao" 
															  property="id" />
											</logic:present>
									</html:select>
								</td>
							</tr>
						
							<tr>
								<td><strong>Tipo de Seleção:<font color="#FF0000">*</font></strong></td>
								<td align="left">
									<html:radio styleId="radioOcorrenciaCadastro"
												property="indicadorTipoSelecao" 
												tabindex="9"
												value="2" onchange="tipoSelecaoChange();" />
										<label for="radioOcorrenciaCadastro"><strong>Imóveis com Ocorrência Cadastro</strong></label> 
									<html:radio styleId="radioImoveisNovos"
												property="indicadorTipoSelecao"
												tabindex="10" 
												value="1" onchange="tipoSelecaoChange()" />
										<label for="radioImoveisNovos"><strong>Imóveis Novos</strong></label>
										 
								</td>
							</tr>
							
							<tr>
			             		<td><strong>Cadastrador:</strong></td>
			             		<td>
			             			<html:select property="cadastrador" style="width: 200px;">
										<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
										<logic:present name="colecaoLeiturista" scope="session">
											<html:options collection="colecaoLeiturista"
													  labelProperty="descricao" 
													  property="id" />
										</logic:present>
									</html:select> 	
			             		</td>
			             	</tr>
							
							<tr>
								<td valign="top" colspan="2">
									<div align="right">
										<input type="button"
											name="ButtonSelecionar" 
											class="bottonRightCol" 
											value="Selecionar"
											onclick="javascript:pesquisar();">
									</div>
								</td>
							</tr>	
							
							
							<tr>
			             		<td height="10" colspan="2"> 
				             		<div align="right"> 
				                 		<hr>
				               		</div>
				               		<div align="right"> </div>
			               		</td>
			           		</tr>
							
							<!-- -------------------------------- Imóveis novos --------------------------------------------- -->
							<logic:present name="imoveisNovosPreGsan" scope="session">
								
								<tr>
									<td align="left" colspan="2">
										<strong>Imóveis	Novos:</strong>
									</td>
								</tr>
								
								<tr bgcolor="#90c7fc" bordercolor="#000000">
									<td colspan="2">
										<table width="100%" cellpadding="0" cellspacing="0">
											<thead>
												<tr bgcolor="#90c7fc" height="35px">
												
													<th width="95px">
														<span>Ação</span>
														<select style="width: 96%;" onchange="alterarTodosImoveisPossiveis(this);">
															<option value="-1"/>
															<option value="Liberado para Atualização GSAN">Liberado para Atualização GSAN</option>
															<option value="Remover Registro Atualização Cadastral">Remover Registro Atualização Cadastral</option>
														</select>
													</th>
													
													<th width="60px">Situação</th>
													<th width="40px">Setor</th>
													<th width="50px">Quadra</th>
													<th width="45px" >Lote</th>
													<th width="185px">Endereço</th>
													<th width="80px">Matrícula</th>
												</tr>
											</thead>
										</table>
									</td>
								</tr>
								
								<tr>
									<td colspan="2" height="250">
										<div style="width: 100%; height: 100%; overflow: auto;">
											<table width="100%" bgcolor="#90c7fc" id="tabelaImoveis" >
											<%int cont=0;%> 
												<logic:iterate name="imoveisNovosPreGsan" id="dados" scope="session" type="DadosImovelPreGsanHelper">
														
														<%	cont = cont + 1;
														if(cont % 2 == 0) {%>
															<tr style="text-align: center;" bgcolor="#cbe5fe">
														<%}else{%>
						               						<tr style="text-align: center;" bgcolor="#FFFFFF" >
						               					<%}%>
						               					
														<td width="95px">
															<select style="width: 100%;" id="<bean:write name="dados" property="idImovelAtualizacaoCadastral"/>" onchange="adicionarItemAlterado(this);" >
																<option value="-1"/>
																<c:forEach items="${dados.acoesDisponiveis}" var="acao">
																	<option value="${acao}"> 
																		<c:out value="${acao}"></c:out>
																	</option>
																</c:forEach>
															</select>
														</td>										
														<td width="60px">
															<bean:write name="dados" property="indicadoresSituacao"/>
															<logic:empty name="dados" property="indicadoresSituacao">
															-
															</logic:empty>
														</td>										
														<td width="40px">
															<a href="javascript:abrirPopup('exibirConsultarDadosImovelAmbienteVirtualPopupAction.do?idImovelAtualizacaoCadastral=${dados.idImovelAtualizacaoCadastral}&acao=pesquisarImovel&icPregsan=true',800, 600);" >
							                            		<bean:write name="dados" property="codigoSetorComercial"/>
							                            	</a>
														</td>										
														<td width="50px"><bean:write name="dados" property="numeroQuadra"/></td>										
														<td width="45px"><bean:write name="dados" property="numeroLote"/></td>
														<td width="185px"><bean:write name="dados" property="enderecoFormatado"/>
																										
														<!-- <td width="80px"><bean:write name="dados" property="matriculaGsan"/></td> -->
														
														<%	if (  dados.getColecaoMatriculaGsan() != null && dados.getColecaoMatriculaGsan().size() > Integer.valueOf(1)) { %>
								               				<td  width="80px" >
																<a href="javascript:abrirPopup('exibirInformarMatriculaDuplicadaPopupAction.do?idAtualizacaoCadastralDuplicado=${dados.idImovelAtualizacaoCadastral}',430, 400);" >
																	<span id="matAssociada<bean:write name="dados" property="idImovelAtualizacaoCadastral"/>"> 
																		<bean:write name="dados" property="matriculaGsan" />
																	</span>
																</a>
																<img src="<bean:message key="caminho.imagens"/>alerta.jpg" />
															</td>
								               			<%	} else {	%> 
					               							<td  width="80px" >
					               								<a  href="javascript:abrirPopup('exibirInformarMatriculaDuplicadaPopupAction.do?idAtualizacaoCadastralDuplicado=${dados.idImovelAtualizacaoCadastral}',430, 400);" >
																	<span id="matAssociada<bean:write name="dados" property="idImovelAtualizacaoCadastral"/>"> 
																		<bean:write name="dados" property="matriculaGsan" />
																	</span>
																</a>
															</td>
								               			<%	}	%>
																								
													</tr>
												</logic:iterate>
											</table>
										</div>
									</td>
								</tr>
								
								<tr style="height: 10px;"></tr>
								
								<tr bordercolor="#000000">
		           					<td bgcolor="#90c7fc" align="left" colspan="2">
		           						<strong>
		           							&nbsp; 1 - Logradouro inexistente no GSAN;
		           							&nbsp; 2 - Setor Comercial inexistente;
		           							&nbsp; 3 - Quadra inexistente;
		           							<br />
		           							&nbsp; 4 - Inscrição duplicada no ambiente virtual 2;
		           							&nbsp; 5 - Inscrição duplicada no GSAN;
		           						</strong>
		           					</td>
		           				</tr>
		           				
		           				<tr style="height: 10px;"></tr>
								
							</logic:present>
							
							
							<!-- ------------------------ Imóveis com ocorrência de cadastro -------------------------------- -->
							<logic:present name="imoveisCadastradosPreGsan" scope="session">
								
								<tr>
									<td align="left" colspan="2">
										<strong>Imóveis	com Ocorrência de Cadastro:</strong>
									</td>
								</tr>
								
								<tr bgcolor="#90c7fc" bordercolor="#000000">
									<td colspan="2">
										<table width="100%" cellpadding="0" cellspacing="0">
											<thead>
												<tr bgcolor="#90c7fc" height="35px">
												
													<th width="95px">
														<span>Ação</span>
														<select style="width: 93px; margin-left:-4;" onchange="alterarTodosImoveisPossiveis(this);">
															<option value="-1"/>
															<option value="Liberado para Atualização GSAN">Liberado para Atualização GSAN</option>
															<option value="Retornar para Campo">Retornar para Campo</option>
															<option value="Remover Registro Atualização Cadastral">Remover Registro Atualização Cadastral</option>
														</select>
													</th>
													
													<th width="60px">Situação</th>
													<th width="40px">Setor</th>
													<th width="50px">Quadra</th>
													<th width="80px">Matrícula</th>
													<th width="185px">Ocorrência Cadastro</th>
													<th width="45px" >Nº Visitas</th>
												</tr>
											</thead>
										</table>
									</td>
								</tr>
								
								<tr>
									<td colspan="2" height="250">
										<div style="width: 100%; height: 100%; overflow: auto;">
											<table width="100%" bgcolor="#90c7fc" id="tabelaImoveis">
											<%int cont=0;%> 
												<logic:iterate name="imoveisCadastradosPreGsan" id="dados" scope="session" type="DadosImovelPreGsanHelper">
														
														<%	cont = cont + 1;
														if(cont % 2 == 0) {%>
															<tr style="text-align: center;" bgcolor="#cbe5fe">
														<%}else{%>
						               						<tr style="text-align: center;" bgcolor="#FFFFFF" >
						               					<%}%>
						               					
														<td width="90px">
															<select style="width: 100%;" id="<bean:write name="dados" property="idImovelAtualizacaoCadastral"/>" onchange="adicionarItemAlterado(this);" >
																<option value="-1"/>
																<c:forEach items="${dados.acoesDisponiveis}" var="acao">
																	<option value="${acao}"> 
																		<c:out value="${acao}"></c:out>
																	</option>
																</c:forEach>
															</select>
														</td>										
														<td width="60px">
															<bean:write name="dados" property="indicadoresSituacao"/>
															<logic:empty name="dados" property="indicadoresSituacao">
															-
															</logic:empty>
														</td>										
														<td width="40px">
															<a href="javascript:abrirPopup('exibirConsultarDadosImovelAmbienteVirtualPopupAction.do?idImovelAtualizacaoCadastral=${dados.idImovelAtualizacaoCadastral}&acao=pesquisarImovel&icPregsan=true',800, 600);" >
							                            		<bean:write name="dados" property="codigoSetorComercial"/>
							                            	</a>
														</td>										
														<td width="50px"><bean:write name="dados" property="numeroQuadra"/></td>										
														<td width="80px"><bean:write name="dados" property="matricula"/></td>										
														<td width="185px"><bean:write name="dados" property="descricaoCadastroOcorrencia"/></td>						 				
														<td width="45px"><bean:write name="dados" property="numeroVisitas"/></td>										
													</tr>
												</logic:iterate>
											</table>
										</div>
									</td>
								</tr>
								
								<tr style="height: 10px;"></tr>
								
								<tr bordercolor="#000000">
		           					<td bgcolor="#90c7fc" align="left" colspan="2">
		           						<strong>
		           							&nbsp; 1 - Logradouro inexistente no GSAN;
		           							&nbsp; 2 - Setor Comercial inexistente;
		           							&nbsp; 3 - Quadra inexistente;
		           							<br />
		           							&nbsp; 4 - Inscrição duplicada no ambiente virtual 2;
		           							&nbsp; 5 - Inscrição duplicada no GSAN;
		           						</strong>
		           					</td>
		           				</tr>
		           				
		           				<tr style="height: 10px;"></tr>
		           				
							</logic:present>
							
							<table width="100%" border="0">
								<tr>
									<td valign="top" align="left">
										<input type="button"
											name="ButtonCancelar" 
											class="bottonRightCol" 
											value="Cancelar"
											onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
									
										<input type="button"
											name="ButtonLimpar" 
											class="bottonRightCol" 
											value="Limpar"
											onClick="javascript:limparFormulario(document.forms[0]);">
											
									</td>	
									<td valign="top" align="right">
										<input type="button"
											align="right"
											name="ButtonLimpar" 
											class="bottonRightCol" 
											value="Imprimir"
											onClick="javascript:gerarRelatorioImoveisInconsistentes();">
										<input
											id="botaoResumo"
											type="button"
											align="right"
											name="ButtonLimpar" 
											class="bottonRightCol" 
											value="Imprimir Resumo"
											onClick="javascript:gerarRelatorioImoveisInconsistentesResumo();">
										<input type="button"
											align="right"
											name="ButtonAtualizar" 
											class="bottonRightCol" 
											value="Atualizar"
											onClick="javascript:validarForm();">
									</td>
								</tr>	
							</table>
						</table>
						</td>
					</tr>
					
				</table>
				
				
			</td>
		</tr>
	</table>


<%@ include file="/jsp/util/rodape.jsp"%>
<%@ include file="/jsp/util/tooltip.jsp"%>
<%--<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioCpfCnpjInconsistentesImoveisNovosAction.do" /> --%>

</html:form>
</div>
<%@ include file="/jsp/util/telaespera.jsp"%>
</body>
</html:html>