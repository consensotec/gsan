<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
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
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
	<html:javascript staticJavascript="false"  formName="EfetuarInstalacaoSubstituicaoRetiradaLoteActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">
<!-- Begin
	
	
	//Chama Popup
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			}
			else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				}
				else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
				}
			}
		}
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
   		var form = document.forms[0];

    	if (tipoConsulta == 'ordemServico') {

	    	form.idOrdemServico.value = codigoRegistro;
	    	form.descricaoOrdemServico.value = descricaoRegistro;
	      	form.action='exibirEfetuarInstalacaoSubstituicaoRetiradaLoteAction.do';
	      	form.submit();

	    }

    	if (tipoConsulta == 'hidrometro') {
          	form.numeroHidrometroInstalado.value = codigoRegistro;
          	form.action='exibirEfetuarInstalacaoSubstituicaoRetiradaLoteAction.do';
	      	form.submit();
        }
	    
  	}
  	
	function limparDecricaoEfetuar() {
		var form = document.forms[0];
		form.descricaoOrdemServico.value = "";
	}

	function limparOS(){
		var form = document.forms[0];
		form.descricaoOrdemServico.value = "";
		form.idOrdemServico.value = "";
		form.matriculaImovel.value = '';
		form.numeroHidrometroAtual.value = '';
		form.numeroHidrometroInstalado.value = '';
		 form.leituraRetirada.value = '';
		 form.leituraInstalacao.value = '';
		 form.action='exibirEfetuarInstalacaoSubstituicaoRetiradaLoteAction.do';
	      	form.submit();
	}

	function limparHidrometro() {
	    var form = document.forms[0];
	        form.numeroHidrometroInstalado.value = "";
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

	function adicionar(){

		var form = document.EfetuarInstalacaoSubstituicaoRetiradaLoteActionForm;

		if ((validaData()) && (validaOS()) && (validaHidrometro()) && (validaHidrometroSubstituido()) && (validateEfetuarInstalacaoSubstituicaoRetiradaLoteActionForm(form))){
			form.action='exibirEfetuarInstalacaoSubstituicaoRetiradaLoteAction.do?adicionar=sim';
	      	form.submit();
		}

	}

	function desfazer(){
		var form = document.forms[0];
		
		form.action='exibirEfetuarInstalacaoSubstituicaoRetiradaLoteAction.do?desfazer=sim';
      	form.submit();
	}
	

	function limpar(){
		 var form = document.forms[0];

		 form.dataInstalSubstRet.value = '';
		 form.idOrdemServico.value = '';
		 form.descricaoOrdemServico.value = '';
		 form.matriculaImovel.value = '';
		 form.numeroHidrometroInstalado.value = '';
		 form.numeroHidrometroAtual.value = '';
		 form.leituraInstalacao.value = '';
		 form.leituraRetirada.value = '';
		 form.operacao.value = '';
		 
		}

	function validaData(){
		var form = document.EfetuarInstalacaoSubstituicaoRetiradaLoteActionForm;

		if (form.dataInstalSubstRet.value != null && form.dataInstalSubstRet.value != ""){
			return true;
		} else {
			alert('Informe a Data Instal./Ret./Subst.');
			return false;
			}

	}

	function validaOS(){
		var form = document.EfetuarInstalacaoSubstituicaoRetiradaLoteActionForm;

		if (form.idOrdemServico.value != null && form.idOrdemServico.value != ""
				&& form.descricaoOrdemServico.value != null && form.descricaoOrdemServico.value != ""){
			return true;
		} else {
			alert('Informe uma Ordem de Serviço válida');
			return false;
			}
		
	}

	function validaHidrometroSubstituido(){
		var form = document.EfetuarInstalacaoSubstituicaoRetiradaLoteActionForm;

		if (form.numeroHidrometroAtual.value != null && form.numeroHidrometroAtual.value != ""
				&& (form.leituraRetirada.value == null || form.leituraRetirada.value == "")){
			alert('Informe a leitura de retirada');
			return false;
		} else {
			return true;
			}
		
	}

	function validaHidrometro(){
		var form = document.EfetuarInstalacaoSubstituicaoRetiradaLoteActionForm;

		if (form.numeroHidrometroInstalado.value != null && form.numeroHidrometroInstalado.value != ""
				&& form.leituraInstalacao.value != null && form.leituraInstalacao.value != ""){
			return true;
		} else {
			alert('Informe o Hidrômetro e sua leitura de instalação');
			return false;
			}
		
	}
	
	function concluir() {
		var form = document.forms[0];

		form.action='efetuarInstalacaoSubstituicaoRetiradaLoteAction.do';
      	form.submit();
	  }

	function remover(){
	    var form = document.forms[0];
	  	form.action = 'exibirEfetuarInstalacaoSubstituicaoRetiradaLoteAction.do?remover=sim';
	    form.submit();		
	}
	
	 function IntegerValidations  () {
		    
	    	this.ab = new Array("leituraInstalacao", "Leitura de Instalação deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	    	this.ac = new Array("leituraRetirada", "Leitura de Retirada deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
	    	
	    }
	
-->
</script>
<head>

<body leftmargin="5" topmargin="5"
	onload="">
	
<div id="formDiv">
	
<html:form action="/efetuarInstalacaoSubstituicaoRetiradaLoteAction"
	name="EfetuarInstalacaoSubstituicaoRetiradaLoteActionForm"
	type="gcom.gui.atendimentopublico.EfetuarInstalacaoSubstituicaoRetiradaLoteActionForm"
	method="post">
	
	<%@ include file="/jsp/util/cabecalho.jsp"%> 
	<%@ include	file="/jsp/util/menu.jsp"%>

<table width="775" border="0" cellspacing="5" cellpadding="0">
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


		<td valign="top" class="centercoltext"><!--Início Tabela Reference a Páginação da Tela de Processo-->
		<table height="100%">
			<tr>
				<td></td>
			</tr>
		</table>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
				<td class="parabg">Efetuar Instalacao/Substituicao/Retirada Hidrômetro em Lote</td>
				<td width="11" valign="top"><img border="0"
					src="imagens/parahead_right.gif" /></td>
			</tr>
		</table>
		
		<p>&nbsp;</p>
		<table width="100%" border="0" dwcopytype="CopyTableRow">
			<tr>
				<td width="29%"><strong>Data Instal./Ret./Subst.: <font color="#FF0000">*</font></strong></td>
				<td colspan="3"><strong>
					<html:text  maxlength="10" tabindex="1"
								name="EfetuarInstalacaoSubstituicaoRetiradaLoteActionForm"
								property="dataInstalSubstRet" size="11"
								onkeyup="javascript:mascaraData(this, event);" />
								<a href="javascript:abrirCalendario('EfetuarInstalacaoSubstituicaoRetiradaLoteActionForm', 'dataInstalSubstRet');">
										<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif"
											width="20" border="0" align="absmiddle" alt="Exibir Calendário" />
								</a>(dd/mm/aaaa)</strong>
				</td>
			</tr>
			
			<tr>
				<td width="28%"><strong>Ordem de Serviço: </strong></td>
				<td colspan="3">
				<html:text
					property="idOrdemServico" 
					size="10" 
					maxlength="9"
					onkeypress="validaEnterComMensagem(event, 'exibirEfetuarInstalacaoSubstituicaoRetiradaLoteAction.do', 'idOrdemServico','Ordem de Serviço');return isCampoNumerico(event);"
					onkeyup="javascript:limparDecricaoEfetuar()" /> 
					
					<a href="javascript:chamarPopup('exibirPesquisarOrdemServicoAction.do', 'ordemServico', null, null, 600, 500, '',document.forms[0].idOrdemServico);">
						
						<img src="/gsan/imagens/pesquisa.gif" 
							alt="Pesquisar" 
							border="0"
							height="21" 
							width="23"
							title="Pesquisar Ordem de Serviço"></a> 
					
				
					<logic:present name="ordemServicoInexistente" scope="session">
						<logic:equal name="ordemServicoInexistente" scope="session" value="true">
							<html:text name="EfetuarInstalacaoSubstituicaoRetiradaLoteActionForm"
								property="descricaoOrdemServico" size="35"
								readonly="true"
								style="background-color: rgb(239, 239, 239); border:0; color: #ff0000;" />
						</logic:equal>
						<logic:equal name="ordemServicoInexistente" scope="session" value="false">
							<html:text name="EfetuarInstalacaoSubstituicaoRetiradaLoteActionForm"
								property="descricaoOrdemServico" size="35" maxlength="35"
								readonly="true"
								style="border: 0pt none ; background-color: rgb(239, 239, 239);" />
						</logic:equal>
					</logic:present> 
					
					<logic:notPresent name="ordemServicoInexistente" scope="session">
						<logic:empty name="EfetuarInstalacaoSubstituicaoRetiradaLoteActionForm"
							property="idOrdemServico">
							<input type="text" name="descricaoOrdemServico" size="35"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000;" />
						</logic:empty>
						<logic:notEmpty name="EfetuarInstalacaoSubstituicaoRetiradaLoteActionForm"
							property="idOrdemServico">
							<html:text name="EfetuarInstalacaoSubstituicaoRetiradaLoteActionForm"
									property="descricaoOrdemServico" size="35" maxlength="35"
									readonly="true"
									style="border: 0pt none ; background-color: rgb(239, 239, 239);" />
						</logic:notEmpty>
					</logic:notPresent> 
					
					<a href="javascript:limparOS()"> 
						<img border="0" 
							title="Apagar"
							src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
				</td>
			</tr>
			
			<tr>
			<td width="30%"><strong>Matrícula do Imóvel:</strong></td>
			
			<td><html:text property="matriculaImovel" size="35" maxlength="35"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000" />
											
			</td>
			</tr>
			
			<tr>
						<td width="20%"><strong>Número do Hidrômetro:  Instalado</strong></td>
						
						<logic:equal name="servicoTipo" scope="session" value="true">
						<td>
						<html:text property="numeroHidrometroInstalado" size="11" maxlength="10" style="text-transform: none;"			
						onkeypress="return pesquisaEnter(event, 'exibirEfetuarInstalacaoSubstituicaoRetiradaLoteAction.do','numeroHidrometroInstalado');" /> 
						
						<a
							href="javascript:abrirPopup('exibirPesquisarHidrometroAction.do',600,640);">
						<img width="23" height="21" border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Número do Hidrometro" /></a> 
							
						 <a href="javascript:limparHidrometro();"> <img
							src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar" /> </a></td>
						</logic:equal>
						
						<logic:equal name="servicoTipo" scope="session" value="false">
						<td>
						<html:text property="numeroHidrometroInstalado" size="11" maxlength="10" readonly="true"	 style="text-transform: none;"	
						onkeypress="return pesquisaEnter(event, 'exibirEfetuarInstalacaoSubstituicaoRetiradaLoteAction.do','numeroHidrometroInstalado');" /> 
						</td>
						</logic:equal>
							
						<td width="20%"><strong> Atual:</strong></td>
						<td><html:text property="numeroHidrometroAtual" size="11" maxlength="10"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000" />
											
						</td>
			</tr>
			
			
			<tr>
			
				
					<td width="30%"><strong>Leitura Instalação:</strong></td>
					<logic:equal name="servicoTipo" scope="session" value="true">
					<td align="left"><html:text property="leituraInstalacao" 
						size="12" maxlength="12" onkeypress="return isCampoNumerico(event);"/></td>
					</logic:equal>
					<logic:equal name="servicoTipo" scope="session" value="false">
					<td align="left"><html:text property="leituraInstalacao" 
						size="12" maxlength="12" readonly="true" /></td>
					</logic:equal>
				
					
					
					<td width="30%"><strong>Leitura Retirada:</strong></td>
					<logic:equal name="servicoTipo" scope="session" value="false">	
					<td align="left"><html:text property="leituraRetirada"
						size="12" maxlength="12" readonly="true" /></td>
					</logic:equal>
					<logic:equal name="servicoTipo" scope="session" value="true">	
					<td align="left"><html:text property="leituraRetirada"
						size="12" maxlength="12"  onkeypress="return isCampoNumerico(event);" /></td>
					</logic:equal>
					
			
			</tr>
			
			<tr>
			<td></td><td></td><td></td>
					<td align="center"><input name="Button32222" type="button"
							class="bottonRightCol" value="Adicionar" 
							onClick="javascript:adicionar()"  />
					</tr>
			
			<tr>
			<td width="50%"><strong>Local de Instalação:</strong></td>
			
			<td>
			<input type="text" required name="localInstalacao"  size="12" maxlength="12"
			readonly="true"
			style="background-color:#EFEFEF; border:0; color: #000000" value="CALCADA">
											
			</td>
			
			<td width="50%"><strong>Situação do Hidrômetro Retirado:</strong></td>
			<td>
			<input type="text" required name="situacaoHidrometroRetirado"  size="15" maxlength="12"
			readonly="true"
			style="background-color:#EFEFEF; border:0; color: #000000" value="EM MANUTENCAO">
											
			</td>
			</tr>
			
			<tr>
			<td width="50%"><strong>Proteção:</strong></td>
			
			<td>
			<input type="text" required name="protecao"  size="15" maxlength="12"
			readonly="true"
			style="background-color:#EFEFEF; border:0; color: #000000" value="CAIXA METALICA">
			</td>
			
			<td width="50%"><strong>Local de Armazenagem:</strong></td>
			<td>
			<input type="text" required name="localArmazenagem"  size="15" maxlength="12"
			readonly="true"
			style="background-color:#EFEFEF; border:0; color: #000000" value="ALMOXAR. CENTRAL">
											
			</td>
						
			</tr>
			
			<tr>
			<td width="50%"><strong>Cavalete:</strong></td>
			
			<td>
			<input type="text" required name="cavalete"  size="12" maxlength="12"
			readonly="true"
			style="background-color:#EFEFEF; border:0; color: #000000" value="SEM CAVALETE">
											
			</td>
			
			</tr>
			
			
			<tr>
				<td colspan="4" bgcolor="#000000" height="2" valign="baseline"></td>
			</tr>
			
			<tr bgcolor="#cbe5fe" >
						<td width="100%" colspan="4" align="center">
						 <table width="100%" bgcolor="#99CCFF" border="0">
							<tr bordercolor="#000000" bgcolor="#90c7fc" class="styleFontePeqNegrito">
								<td width="10%" bgcolor="#90c7fc">
								<div align="center" style="height:30px;"><strong><a
									href="javascript:facilitador(this);">Todos</a></strong></div>
								</td>
								<td width="15%" bgcolor="#90c7fc">
								<div align="center"><strong>O.S.</strong></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
								<div align="center"><strong>Operação</strong></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
								<div align="center"><strong>Matrícula</strong></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
								<div align="center"><strong>Hidrômetro Instalado</strong></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
								<div align="center"><strong>Hidrômetro Retirado</strong></div>
								</td>
								<td width="15%" bgcolor="#90c7fc">
								<div align="center"><strong>Data Inst./Ret.</strong></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
								<div align="center"><strong>Leitura Instalação</strong></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
								<div align="center"><strong>Leitura Retirada</strong></div>
								</td>
							</tr>
							
							<logic:present name="colecaoOsHidrometro">
							<tr>
								<td colspan="4" >

									<div style="width: 100%; height: 100%; overflow: auto;">
		
											<%int cont = 0;%>
											<logic:iterate name="colecaoOsHidrometro" id="helper">
												
												<%cont = cont + 1;
												if (cont % 2 == 0) {%>
												<tr bgcolor="#cbe5fe">
													<%} else {%>
												<tr bgcolor="#FFFFFF">
													<%}%>
													
													<td width="7%">
														<div align="center"><input type="checkbox" checked
															name="idRegistrosRemocao" onclick="habilitaDesabilitaBotao();"
															value="<bean:write name="helper" property="idOS" />" />
														</div>
													</td>
													<td width="12%" align="center"><bean:write name="helper"
														property="idOS" />
													</td>
													<td width="12%" align="center"><bean:write name="helper"
														property="operacao" />
													</td>
													<td width="40%" align="center"><bean:write name="helper"
														property="matricula" />
													</td>
													<td align="center" width="20%"><bean:write name="helper"
													    property="hidrometroInstalado" />
												    </td>													    												
													<td align="center" width="20%"><bean:write name="helper"
													    property="hidrometroRetirado" />
												    </td>
												    <td align="center" width="20%"><bean:write name="helper"
													    property="dataInstalacaoRetirada" />
												    </td>
												    <td align="center" width="20%"><bean:write name="helper"
													    property="leituraInstalacao" />
												    </td>
												    <td align="center" width="20%"><bean:write name="helper"
													    property="leituraRetirada" />
												    </td>
												</tr>										
											</logic:iterate>
									</div>
								</td>
							</tr>
						</logic:present>
						</table>
						
			
			<tr>
				<td colspan="3" align="left">
				<table border="0" align="left">
					<tr>
						<td align="left"><input name="Button32222" type="button"
							class="bottonRightCol" value="Remover" tabindex="5"
							onClick="javascript:remover()"  /></td>
					</tr>
				</table>
				</td>
			</tr>
			
			<tr>
			<p>&nbsp;</p>
					<td colspan="4">
					<hr>
					</td>
			</tr>
			
			<tr>
			<td colspan="3" align="left">
				<table border="0" align="left">
					<tr>
						<td align="left"><input name="Button32222" type="button"
							class="bottonRightCol" value="Voltar" 
							onClick="window.history.back();"  />
							
						<td align="center"><input name="Button32222" type="button"
							class="bottonRightCol" value="Desfazer"
							onClick="javascript:desfazer()"  />
							
						<td align="right"><input name="Button32222" type="button"
							class="bottonRightCol" value="Cancelar" 
							onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"  />
							
						
					</tr>
				</table>
				<td align="right"><input name="Button32222" type="button"
							class="bottonRightCol" value="Concluir" 
							onClick="javascript:concluir()"  />
				</td>
				</tr>
		</table>
		<p>&nbsp;</p>
		</td>
	</tr>
</table>
<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</div>
<body>
</html:html>
