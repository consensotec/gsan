<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>

<%@ include file="/jsp/util/telaespera.jsp"%>

<%@page import="gsan.atendimentopublico.bean.OrdemServicoArquivoTextoHelper"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">


	function validaForm(form){
		submeterFormPadrao(form);
	}
	
	function atualizarOrdemServico() {
		form = document.forms[0];
		
	  	if (selecionouOrdemServico()) {
	  		form.action = 'consultarOrdemServicoDoArquivoTextoAction.do';
	    	form.submit();
	  	}
	}
	
	function selecionouOrdemServico(){

		var form = document.forms[0];
	    var selecionados = form.elements['idsRegistros'];
		var retorno = false;
		
		if (selecionados.value != null && selecionados.value != '') {
			
			retorno = true;
			
		} else {
		
			for (i=0; i< selecionados.length; i++) {
				if(selecionados[i].checked){
					retorno = true;
				}
			}
			
		}

		if(retorno == false){
			alert('Selecione pelo menos uma ordem de serviço.');
		}
		
		return retorno;
	}
	
	function ordemServicoSelecionada(){

		var form = document.forms[0];
	    var selecionados = form.elements['idsRegistros'];
		var retorno = false;
		
		if (selecionados.value != null && selecionados.value != '') {
			
			return selecionados.value;
			
		} else {
			for (i=0; i< selecionados.length; i++) {
				if(selecionados[i].checked){
					return selecionados[i].value;
				}
			}
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

<body leftmargin="5" topmargin="5">
<div id="formDiv">  
<html:form action="/consultarOrdemServicoDoArquivoTextoAction"
	name="ConsultarOrdemServicoDoArquivoTextoActionForm"
	type="gsan.gui.atendimentopublico.ordemservico.ConsultarOrdemServicoDoArquivoTextoActionForm"
	method="post"
	onsubmit="return validateConsulta(this);">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
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
			<td width="615" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>


			<!--Início Tabela Reference a Páginação da Tela de Processo-->
			<table>
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Atualizar Ordens de Serviço do Arquivo Texto</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="3">Dados das Ordens de Serviço do arquivo texto selecionado:</td>
				</tr>

				<tr>
	                <td><strong>Comando para Inspeção de Anormalidade:</strong></td>
	                <td colspan="2">
	                	<strong>
			     			<html:text
		     					   maxlength="47"
		     					   size="47"
		     					   property="descricaoComando"
		     					   readonly="true"
   		     					   style="background-color:#EFEFEF; border:0; color: #000000"/>
	     				</strong>
	     			</td>
              	</tr>
            </table>
			<table width="100%" border="0">
				<tr>
					<td colspan="3" height="7"> </td>
				</tr>	
				<tr>
					<td colspan="3" bgcolor="#000000" height="1" valign="baseline"></td>
				</tr>
				<tr>
					<td colspan="3" height="7"> </td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
	                <td><strong>Localidade:</strong></td>
	                <td colspan="2"><strong>
		                <html:text maxlength="3"
				           			size="3"
				           			tabindex="1"
				           			property="idLocalidade"
		     					   readonly="true"
		     					   style="background-color:#EFEFEF; border:0; color: #000000"
				           			/>
		     			<html:text maxlength="40"
		     					   size="40"
		     					   property="descricaoLocalidade"
		     					   readonly="true"
		     					   style="background-color:#EFEFEF; border:0; color: #000000"
		     					   />
	     			</strong>
	     		</td>
              </tr>

				<tr>
	                <td><strong>Setor Comercial Inicial:</strong></td>
	                <td colspan="2"><strong>
		     			<html:text
		     					   maxlength="47"
		     					   size="47"
		     					   property="descricaoSetorComercialInicial"
		     					   readonly="true"
   		     					   style="background-color:#EFEFEF; border:0; color: #000000"/>
	     			</strong>
	     		</td>
              </tr>
              <tr>
	                <td><strong>Setor Comercial Final:</strong></td>
	                <td colspan="2"><strong>
	     			  <html:text
	     					    maxlength="47"
	     					    size="47"
	     					    property="descricaoSetorComercialFinal"
	     					    readonly="true"
  		     					style="background-color:#EFEFEF; border:0; color: #000000"/>
	     			</strong>
	     		</td>
              </tr>
              
               <tr>
	                <td><strong>Quadra Inicial: </strong></td>
	                <td colspan="2"><strong>
	                  <html:text maxlength="47"
	                  			 size="47"
	                  			 tabindex="1"
	                  			 property="numeroQuadraInicial"
	     					     readonly="true"
  		     					 style="background-color:#EFEFEF; border:0; color: #000000"
	                  			 />
	     			</strong> 
	     		</td>
              </tr>
              
               <tr>
	                <td><strong>Quadra Final:</strong></td>
	                <td colspan="2"><strong>
	                  	<html:text maxlength="47"
	                  			 size="47"
	                  			 tabindex="1"
	                  			 property="numeroQuadraFinal"
	     					     readonly="true"
  		     					 style="background-color:#EFEFEF; border:0; color: #000000" />
	     				</strong>
	     			</td>
               </tr>
               <tr>
	                <td><strong>Agente Comercial:</strong></td>
	                <td colspan="2"><strong>
	                  	<html:text maxlength="47"
	                  			 size="47"
	                  			 tabindex="1"
	                  			 property="agenteComercial"
	     					     readonly="true"
  		     					 style="background-color:#EFEFEF; border:0; color: #000000" />
	     				</strong>
	     			</td>
                </tr>	
				<tr>
					<td colspan="3" height="7"> </td>
				</tr>	
				<tr>
					<!--<td colspan="4" bgcolor="#3399FF"> -->
					<td colspan="3" bgcolor="#000000" height="2" valign="baseline"></td>
				</tr>
				<tr>
					<td colspan="3" height="7"> </td>
				</tr>
				<tr>
					<td width="100%" colspan="3">
				
						<table width="100%" align="center" bgcolor="#90c7fc" border="0" cellpadding="0" cellspacing="0">
							<tr bgcolor="#cbe5fe" >
								<td width="100%" align="center">
								 <table width="100%" bgcolor="#99CCFF" border="0">
									<tr bordercolor="#000000" bgcolor="#90c7fc" class="styleFontePeqNegrito">
										<!-- 1 -->
										<td width="5%" bgcolor="#90c7fc">
										<div align="center" style="height:30px;"><strong><a
											href="javascript:facilitador(this);">Todos</a></strong></div>
										</td>
										<!-- 2 -->
										<td width="10%" bgcolor="#90c7fc">
										<div align="center"><strong>Ordem de Serviço</strong></div>
										</td>
										<!-- 3 -->
										<td width="10%" bgcolor="#90c7fc">
										<div align="center"><strong>Matrícula</strong></div>
										</td>
										<!-- 4 -->
										<td width="10%" bgcolor="#90c7fc">
										<div align="center"><strong>Situação</strong></div>
										</td>
										<!-- 5 -->
										<td width="10%" bgcolor="#90c7fc">
										<div align="center"><strong>Data/Hora Recebimento</strong></div>
										</td>
										<!-- 6 -->
										<td width="10%" bgcolor="#90c7fc">
										<div align="center"><strong>Conferida</strong></div>
										</td>
									</tr>
								</table>
		
								<div style="height: 100%; max-height: 300px;  overflow: auto;">
								<table width="100%" bgcolor="#99CCFF">
									<tr bordercolor="#000000" bgcolor="#90c7fc">
										
										<logic:present name="colecaoOrdemServicoArquivoTextoHelper">
											<%int cont = 0;%>
											<logic:iterate name="colecaoOrdemServicoArquivoTextoHelper"
												id="ordemServicoArquivoTextoHelper"
												type="OrdemServicoArquivoTextoHelper"
												scope="session">
										
												<%
													  cont = cont + 1;
													  if (cont % 2 == 0) {
												  %>
												<tr bgcolor="#cbe5fe">
												
													<%} else {%>
													
												<tr bgcolor="#FFFFFF">
												
													<%}%>
													
													
													<!-- 1 -->		
													<td width="5%">
														<div align="center">
															<html:checkbox property="idsRegistros"
															value="${ordemServicoArquivoTextoHelper.idOrdemServico}" />
														</div>
													</td>
														
													<!-- 2 -->	
													<td width="10%" align="center">
														<a href="/gsan/exibirConsultarDadosOrdemServicoVisitaAction.do?ordemServico=${ordemServicoArquivoTextoHelper.idOrdemServico}&matricula=${ordemServicoArquivoTextoHelper.matricula}">
															<bean:write name="ordemServicoArquivoTextoHelper" property="idOrdemServico" />	
														</a>
													</td>
														
													<!-- 3 -->
													<td width="10%" align="center" onmouseover="this.T_BGCOLOR='whitesmoke';this.T_LEFT=true;return escape( '${ordemServicoArquivoTextoHelper.hint}' ); ">
															<bean:write name="ordemServicoArquivoTextoHelper" property="matricula" />	
													</td>
														
													<!-- 4 -->	
													<td width="10%" align="center">
														<bean:write name="ordemServicoArquivoTextoHelper" property="situacao" />	
													</td>
														
													<!-- 5 -->	
													<td width="10%" align="center">
														<bean:write name="ordemServicoArquivoTextoHelper" property="dataHoraRecebimento" />	
													</td>
													
													<!-- 6 -->											
													<td width="10%" align="center">
														<bean:write name="ordemServicoArquivoTextoHelper" property="ordemServicoConferida" />
													</td>
												</tr>
												<!-- </pg:item> -->
											</logic:iterate>
										</logic:present>
								</table>
								</div>
							
							</tr>
						</table>
			  		</td>
			  	</tr>
			  	
				<tr>
					<td colspan="3" height="7"> </td>
				</tr>
				
			  	<tr>
					<td align="right" colspan="3">
						<input name="Button" type="button" class="bottonRightCol"
							value="Voltar" align="left"
							onclick="javascript:window.location.href='/gsan/consultarArquivoTextoOSVisitaAction.do'">
						&nbsp;&nbsp;&nbsp;
						<logic:present name="ordemServicoPendente" scope="session">
							<input type="button" name="confirmar"
								class="bottonRightCol" value="Atualizar OS"
								onClick="javascript:atualizarOrdemServico();" />
						</logic:present>
						<logic:notPresent name="ordemServicoPendente" scope="session">
							<input type="button" name="confirmar" disabled="disabled"
								class="bottonRightCol" value="Atualizar OS"
								onClick="javascript:atualizarOrdemServico();" />
						</logic:notPresent>
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
		</tr>
		
		
	</table>
	<p>&nbsp;</p>

	<tr>

	</tr>
	
	<%@ include file="/jsp/util/rodape.jsp"%>
	<%@ include file="/jsp/util/tooltip.jsp"%>
</html:form>
</div>
</body>
</html:html>