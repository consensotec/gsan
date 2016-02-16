<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>

<%@ include file="/jsp/util/telaespera.jsp"%>

<%@page import="gcom.mobile.execucaoordemservico.bean.OrdemServicoCobrancaSmartphoneHelper"%>

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
	
	function encerrarOrdemServico() {
		form = document.forms[0];
		 
		if (selecionouOrdemServico()) {
	  		form.action = 'encerrarOrdemServicoCobrancaSmartphoneAction.do';
	    	form.submit();
	  	}
	}
	
	function selecionouOrdemServico(){

		var form = document.forms[0];
	    var selecionados = form.elements['idsRegistros'];
		var retorno = false;

		if(!selecionados){
			alert('Selecione pelo menos uma ordem de serviço.');
			return false;
		}	
		
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

	function verificarTelaSucessoRelatorio(telaSucesso){
		var form =  document.forms[0];
		if(telaSucesso == true){
			form.action = "/gsan/telaSucessoRelatorioAction.do";
			form.submit();
		}

	}
	
</script>

</head>

<body leftmargin="5" topmargin="5" onload="javascript:verificarTelaSucessoRelatorio(${requestScope.telaSucessoRelatorio});">
<div id="formDiv">  
<html:form action="/consultarOrdemServicoDoArquivoTextoAction"
	name="ConsultarOrdemServicoCobrancaSmartphoneActionForm"
	type="gcom.gui.mobile.execucaoordemservico.ConsultarOrdemServicoCobrancaSmartphoneActionForm"
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
					<td width="30%"><strong>Empresa:</strong></td>
					<td>
						<html:hidden property="empresa"/>
						<html:text property="descricaoEmpresa" size="60" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
					</td>
				</tr>
				<tr>
					<td width="30%"><strong>Tipo da Ordem de Serviço:</strong></td>
					<td>
						<html:hidden property="idTipoOS"/>
						<html:text property="descricaoTipoOS" size="60" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
					</td>
				</tr>
				<tr>
					<td width="30%"><strong>Tipo de Serviço:</strong></td>
					<td>
						<html:text property="descricaoServicoTipo" size="60" maxlength="40" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
					</td>
				</tr>
				<tr>
	                <td><strong>Agente Comercial:</strong></td>
	                <td colspan="2"><strong>
	                  	<html:text maxlength="60" size="60" tabindex="1" property="agenteComercial" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
	                </strong></td>
                </tr>	
            </table>
			
			
		
			
			<table width="100%" border="0">
			
				<logic:notEmpty name="ConsultarOrdemServicoCobrancaSmartphoneActionForm" property="anoMesReferenciaCronograma">
					<tr><td>&nbsp;</td></tr>
					<tr bgcolor="#99CCFF" >
						<td height="18" colspan="3">
							<div align="left">
								<strong>
									<span class="style2"> Filtro por Grupo de Cobrança</span>
								</strong>
							</div>
						</td>
					</tr>
				    <tr>
		                <td width="30%"><strong>Mês/Ano do Cronograma:</strong></td>
		                <td colspan="2"><strong>
		                  	<html:text maxlength="8" size="8" tabindex="1" property="anoMesReferenciaCronograma" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
		                </strong></td>
	                </tr>	
					<tr>
		                <td width="30%"><strong>Grupo de Cobrança:</strong></td>
		                <td colspan="2"><strong>
		                  	<html:text maxlength="30" size="30" tabindex="1" property="cobrancaGrupo" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
		                </strong></td>
	                </tr>	
	                
	                <logic:present name="colecaoLocalidades">
	            	    <tr bgcolor="#cbe5fe" >
			    			<td colspan="1" width="30%"><strong>Localidade(s):</strong></td>    
			    			<td colspan="2">
								<div style="height:50px;overflow:auto">
									<table width="95%" >
										<tr>								
											<%int cont = 0;%>
											<logic:iterate name="colecaoLocalidades"
												id="localidade"
												type="gcom.cadastro.localidade.Localidade">
												<tr>
													<td style="background-color:#EFEFEF; border:0; color: #000000">
														${localidade.descricao}
													</td>
												</tr>
											</logic:iterate>
										</tr>
									</table>
								</div>
							</td>
						</tr>
	                </logic:present>

				</logic:notEmpty>
			
			 	<logic:notEmpty name="ConsultarOrdemServicoCobrancaSmartphoneActionForm" property="tituloCobrancaAcaoAtividadeComando">
		            <tr><td>&nbsp;</td></tr>
					<tr bgcolor="#99CCFF" >
						<td height="18" colspan="3">
							<div align="left">
								<strong>
									<span class="style2"> Filtro por Cobrança Eventual</span>
								</strong>
							</div>
						</td>
					</tr>
		            <tr>
		                <td width="30%" ><strong>Data de Realização:</strong></td>
		                <td colspan="2"><strong>
			               <html:text maxlength="20" size="20" tabindex="1" property="dataRealizacaoCobrancaAcaoAtividadeComando" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
		     			</strong></td>
		            </tr>
					<tr>
		                <td width="30%" ><strong>Cobrança Eventual:</strong></td>
		                <td colspan="2"><strong>
			               <html:text maxlength="60" size="60" tabindex="1" property="tituloCobrancaAcaoAtividadeComando" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
		     			</strong></td>
		            </tr>
				</logic:notEmpty>
				
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
										<td width="10%" bgcolor="#90c7fc">
										<div align="center" style="center:30px;"><strong><a
											href="javascript:facilitador(this);">Todos</a></strong></div>
										</td>
										<!-- 2 -->
										<td width="20%" bgcolor="#90c7fc">
										<div align="center"><strong>Ordem de Serviço</strong></div>
										</td>
										<!-- 3 -->
										<td width="20%" bgcolor="#90c7fc">
										<div align="center"><strong>Matrícula</strong></div>
										</td>
										<!-- 4 -->
										<td width="20%" bgcolor="#90c7fc">
										<div align="center"><strong>Situação</strong></div>
										</td>
										<!-- 5 -->
										<td width="30%" bgcolor="#90c7fc">
										<div align="center"><strong>Data/Hora Recebimento</strong></div>
										</td>
									</tr>
								</table>
		
								<div style="height: 100%; max-height: 300px;  overflow: auto;">
								<table width="100%" bgcolor="#99CCFF">
									<tr bordercolor="#000000" bgcolor="#90c7fc">
										
										<logic:present name="colecaoOrdemServicoCobrancaSmartphoneHelper">
											<%int cont = 0;%>
											<logic:iterate name="colecaoOrdemServicoCobrancaSmartphoneHelper"
												id="ordemServicoCobrancaSmartphoneHelper"
												type="OrdemServicoCobrancaSmartphoneHelper"
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
													<td width="10%">
						                        		<c:choose>
						                        			<c:when test="${ordemServicoCobrancaSmartphoneHelper.situacao != 'Encerrada'}">
																<div align="center">
																	<html:checkbox property="idsRegistros"
																	value="${ordemServicoCobrancaSmartphoneHelper.idOrdemServico}" />
																</div>
						                        			</c:when>
						                        			<c:otherwise>
																<div align="center">
																</div>
						                        			</c:otherwise>
						                        		</c:choose>
													</td>
														
													<!-- 2 -->	
													<td width="20%" align="center">
						                        		<c:choose>
						                        			<c:when test="${ordemServicoCobrancaSmartphoneHelper.situacao != 'Encerrada'}">													
																<a href="/gsan/exibirConsultarDadosOrdemServicoCobrancaSmartphoneAction.do?ordemServico=${ordemServicoCobrancaSmartphoneHelper.idOrdemServico}&matricula=${ordemServicoCobrancaSmartphoneHelper.matricula}&idArquivo=${ordemServicoCobrancaSmartphoneHelper.idArquivo}">
																	<bean:write name="ordemServicoCobrancaSmartphoneHelper" property="idOrdemServico" />	
																</a>
															</c:when>																
						                        			<c:otherwise>
																<div align="center">
																	${ordemServicoCobrancaSmartphoneHelper.idOrdemServico}
																</div>
						                        			</c:otherwise>
						                        		</c:choose>																
													</td>
														
													<!-- 3 -->
													<td width="20%" align="center" onmouseover="this.T_BGCOLOR='whitesmoke';this.T_LEFT=true;return escape( '${ordemServicoCobrancaSmartphoneHelper.hint}' ); ">
															<bean:write name="ordemServicoCobrancaSmartphoneHelper" property="matricula" />	
													</td>

													<!-- 4 -->	
													<td width="20%" align="center">
														<bean:write name="ordemServicoCobrancaSmartphoneHelper" property="situacao" />	
													</td>
														
													<!-- 5 -->	
													<td width="30%" align="center">
														<bean:write name="ordemServicoCobrancaSmartphoneHelper" property="dataHoraRecebimento" />	
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
							onclick="javascript:window.location.href='/gsan/consultarArquivoTextoOSCobrancaSmartphoneAction.do'">
						&nbsp;&nbsp;&nbsp;
						
						<input type="button" name="confirmar" class="bottonRightCol" value="Encerrar OS" onClick="javascript:encerrarOrdemServico();" />
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
		</tr>
		
		
	</table>


	<tr>

	</tr>
	
	<%@ include file="/jsp/util/rodape.jsp"%>
	<%@ include file="/jsp/util/tooltip.jsp"%>
</html:form>
</div>
</body>
</html:html>