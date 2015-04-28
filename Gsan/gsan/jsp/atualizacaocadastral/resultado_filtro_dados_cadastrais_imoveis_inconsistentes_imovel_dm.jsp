<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="gsan.atualizacaocadastral.bean.DadosMovimentoAtualizacaoCadastralDMHelper"  isELIgnored="false"%>

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>/validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script>
function facilitador(objeto) {
	if (objeto.value === undefined || objeto.value) {
		objeto.value = false;
		marcarTodos();
	} else {
		objeto.value = true;
		desmarcarTodos();
	}
}

function voltarFiltro(telaRetorno){
	var form = document.forms[0];
	if(telaRetorno=='filtro'){
		form.action = "exibirFiltrarDadosCadastraisImoveisInconsistentesAction.do";
	}else{
		form.action = "filtrarDadosCadastraisImoveisInconsistentesDMAction.do?objetoConsultaFiltro="+telaRetorno;
	}
	form.submit();
	
}

 function pesquisar(objetoConsultaFiltro,id,telaRetorno){
	var form = document.forms[0];
	if(validarPreenchimentoCheckBox(form)){
		form.action = "exibirAtualizarDadosImoveisInconsistentesAction.do?objetoConsultaFiltro="
			+objetoConsultaFiltro+"&id="+id+"&objetoConsultaFiltroAnterior="+telaRetorno;
		form.submit();
	}else{
		alert("Selecione um ou mais de um imóvel para fazer a pesquisa dos dados de atualização cadastral");
	}
 }

 function validarPreenchimentoCheckBox(form){
 	retorno = false;
	for(indice = 0; indice < form.elements.length; indice++){
		if (form.elements[indice].type == "checkbox" && form.elements[indice].checked == true) {
			return retorno = true;
			break;
		}
	}
	return retorno;
 }
</script>
</head>

<body leftmargin="5" topmargin="5">
<html:form action="/filtrarDadosCadastraisImoveisInconsistentesDMAction"
	name="FiltrarDadosCadastraisImoveisInconsistentesDMActionForm"
	type="gsan.gui.atualizacaocadastral.FiltrarDadosCadastraisImoveisInconsistentesDMActionForm" 
	method="post">

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
			<td width="620" valign="top" class="centercoltext">
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
					<td class="parabg">Atualizar Dados Cadastrais para Imoveis Inconsistentes</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4" height="23"><font color="#000000"
						style="font-size:10px"
						face="Verdana, Arial, Helvetica, sans-serif"> <strong>Imoveis
					Cadastrados:</strong></font></td>
					<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroImovelManter', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>	
				</tr>
			</table>	
			<table width="100%" cellpadding="0" cellspacing="0">	
				<tr>
					<td colspan="7" bgcolor="#000000" height="2"></td>
				</tr>
				<logic:present name="colecaoDadosImoveisHelper" scope="request">
					<tr bordercolor="#000000">
						<td width="10%" bgcolor="#90c7fc" align="center">
							<span onclick="facilitador(this);" style="cursor: pointer;">
								<ins><strong>Todos</strong></ins>
							</span>
						</td>
						<td width="10%" bgcolor="#90c7fc" align="center">
						    <strong>Setor</strong>
						</td>
						<td width="10%" bgcolor="#90c7fc" align="center">
						    <strong>Quadra</strong>
						</td>
						<td width="10%" bgcolor="#90c7fc" align="center">
						    <strong>Lote</strong>
						</td>
						<td width="15%" bgcolor="#90c7fc" align="center">
						    <strong>Matricula</strong>
						</td>
						<td width="30%" bgcolor="#90c7fc" align="center">
						    <strong>Cadastrador</strong>
						</td>
						<td width="15%" bgcolor="#90c7fc" align="center">
						    <strong>Situação</strong>
						</td>
					</tr>
					
					<tr>
						<td height="200" colspan="7">
							<div style="width: 100%; height: 100%; overflow: auto;">
								<table width="100%" bgcolor="#99CCFF">
									<%	int contt = 0;	%>
		                   			<logic:iterate 
		                   				name="colecaoDadosImoveisHelper" 
		                   				id="helper" 
		                   				scope="request" 
		                   				type="DadosMovimentoAtualizacaoCadastralDMHelper">
		                   				
						               	<%	contt = contt + 1;
											if (contt % 2 == 0) {	%>
						                  		<tr bgcolor="#cbe5fe">
						             	<%	} else {	%>
						                 		<tr bgcolor="#FFFFFF">
						             	<%	}	%>
		                   				
		                   				<td width="10%" align="center">
		                            	<input type="checkbox" 
		                            		name="idRegistroImovel" 
		                            		value="${helper.idImovel}" />
		                           		</td>
			                            <td width="10%" align="center"> 
			                            	<bean:write name="helper" property="codigoSetor"/>
			                            </td>
			                            <td width="10%" align="center"> 
			                            	<bean:write name="helper" property="numeroQuadra"/>
			                            </td>
			                            <td width="10%" align="center"> 
			                            	<bean:write name="helper" property="numeroLote"/>
			                            </td>
			                            <td width="15%" align="center"> 
			                            	<bean:write name="helper" property="idImovel"/>
			                            </td>
			                            <td width="30%" align="center"> 
			                            	<bean:write name="helper" property="nomeCadastrador"/>
			                            </td>
			                             <td width="15%" align="center"> 
			                            	<bean:write name="helper" property="imovelSituacao"/>
			                            </td>
		                   			</logic:iterate>
								</table>
							</div>
						</td>
					</tr>
				</logic:present>
			</table>
			<table width="100%" bgcolor="#99CCFF" border="0">
				<tr bordercolor="#000000">
					<td bgcolor="#90c7fc" align="left">
						<strong>1-Atualizado &nbsp;  2-Pendente por Inconsistência &nbsp;  3-Pendente Alteração Inscrição &nbsp; </strong>
				   	</td>
				</tr>
					
				<tr bordercolor="#000000">
					<td bgcolor="#90c7fc" align="left">
						<strong>4-Pendente por Logradouro</strong>
					</td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td align="right" colspan="2">
						<input name="Button2" 
							   type="button"
							   class="bottonRightCol" 
							   value="Voltar" 
							   align="right"
							   onClick="javascript:voltarFiltro('${telaRetorno}');" />

						<input name="Button2" 
							   type="button"
							   class="bottonRightCol" 
							   value="Pesquisar" 
							   align="right"
							   onClick="javascript:pesquisar('${objetoConsultaFiltro}','${id}','${telaRetorno}');"/>
					</td>
				</tr>
			</table>			
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>

