<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="gcom.util.ConstantesSistema"%>

<html:html>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="FiltrarImovelPerfilActionForm" dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript">


	function validarForm(formulario){
		var form = document.forms[0];
		if(validateFiltrarImovelPerfilActionForm(form))
		{
			form.hidrometroCapacidadesHidden.value = adicionarHidrometroCapacidadesHidden();
			
			submeterFormPadrao(form);
		}
	}

	function adicionarHidrometroCapacidadesHidden(){
		var form = document.forms[0];

		var capacidadeSelecionadas = "";
		if(form.hidrometroCapacidades.disabled==false && form.hidrometroCapacidades.value!="")
		{
			for(var posicao = 0;posicao<form.hidrometroCapacidades.options.length;posicao++)
			{
				if(form.hidrometroCapacidades.options[posicao].selected)
				{
					if(capacidadeSelecionadas!="")
					{
						capacidadeSelecionadas += ",";
					}

					capacidadeSelecionadas+=form.hidrometroCapacidades.options[posicao].value;
				}
			}
		}
		
		return capacidadeSelecionadas;
		
	}

    function verificarChecado(valor){
		form = document.forms[0];
		if(valor == "1"){
		 	form.indicadorAtualizar.checked = true;
		 }else{
		 	form.indicadorAtualizar.checked = false;
		}
	}
</script>

</head>

<body leftmargin="5" topmargin="5">
<html:form action="/filtrarImovelPerfilAction"
	name="FiltrarImovelPerfilActionForm"
	type="gcom.gui.cadastro.imovel.FiltrarImovelPerfilActionForm"
	method="post"
	onsubmit="return validateFiltrarImovelPerfilActionForm(this);">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<html:hidden property="hidrometroCapacidadesHidden"/>

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


			<!--In�cio Tabela Reference a P�gina��o da Tela de Processo-->
			<table>
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Filtrar Im�vel Perfil</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para filtrar o im�vel perfil, informe os dados
					abaixo:</td>					
			
				
				<td width="100" align="left" colspan="2"><html:checkbox
						property="indicadorAtualizar" value="1" /><strong>Atualizar</strong></td>
				</tr>
				
			   <tr>
					<td ><strong>C�digo:</strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="id" size="5" maxlength="5"
						 onkeypress="return isCampoNumerico(event);"/> </span></td>
			   		
			   </tr>
				
								
		       <tr>
					<td><strong>Descri��o:</strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="descricao" size="30" maxlength="20" /> </span></td>
			   		
			   </tr>
			   
			   <tr>
					<td>&nbsp;</td>
					<td colspan = "2"><html:radio property="tipoPesquisa" tabindex="5"
						value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
					Iniciando pelo texto <html:radio property="tipoPesquisa"
						tabindex="6"
						value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
					Contendo o texto</td>
					
				</tr>						

				<tr>
					<td ><strong>Indicador de uso:</strong></td>
					<td><html:radio property="indicadorUso" tabindex="2" value="1" /><strong>Sim</strong>
					<html:radio property="indicadorUso" tabindex="3" value="2" /><strong>N�o</strong>
					<html:radio property="indicadorUso" tabindex="4" value="" /><strong>Todos</strong>
					</td>
					
				</tr>
				
				<tr>
					<td><strong>Indicador Gera��o Autom�tica:<font color="#FF0000"></font></strong></td>
					<td><html:radio property="indicadorGeracaoAutomatica" tabindex="2" value="1" /><strong>Sim</strong>
					<html:radio property="indicadorGeracaoAutomatica" tabindex="3" value="2" /><strong>N�o</strong>
					<html:radio property="indicadorGeracaoAutomatica" tabindex="4" value="" /><strong>Todos</strong>
					</td>
					
				</tr>
				
				<tr>
					<td><strong>Indicador Inserir Manter Perfil:<font color="#FF0000"></font></strong></td>
					<td><html:radio property="indicadorInserirManterPerfil" tabindex="2" value="1" /><strong>Sim</strong>
					<html:radio property="indicadorInserirManterPerfil" tabindex="3" value="2" /><strong>N�o</strong>
					<html:radio property="indicadorInserirManterPerfil" tabindex="4" value="" /><strong>Todos</strong>
					</td>
					
				</tr>	
				
				<tr>
					<td><strong>Indicador Gerar Dados Leitura:<font color="#FF0000"></font></strong></td>
					<td><html:radio property="indicadorGerarDadosLeitura" tabindex="2" value="1" /><strong>Sim</strong>
					<html:radio property="indicadorGerarDadosLeitura" tabindex="3" value="2" /><strong>N�o</strong>
					<html:radio property="indicadorGerarDadosLeitura" tabindex="4" value="" /><strong>Todos</strong>
					</td>
					
				</tr>	
				
				<tr>
					<td><strong>Indicador Bloquear Retifica��o:<font color="#FF0000"></font></strong></td>
					<td><html:radio property="indicadorBloquearRetificacao" tabindex="2" value="1" /><strong>Sim</strong>
					<html:radio property="indicadorBloquearRetificacao" tabindex="3" value="2" /><strong>N�o</strong>
					<html:radio property="indicadorBloquearRetificacao" tabindex="4" value="" /><strong>Todos</strong>
					</td>
					
				</tr>	
				
				<tr>
					<td><strong>Indicador de Grande Cliente:<font color="#FF0000"></font></strong></td>
					<td><html:radio property="indicadorGrandeConsumidor" tabindex="2" value="1" /><strong>Sim</strong>
					<html:radio property="indicadorGrandeConsumidor" tabindex="3" value="2" /><strong>N�o</strong>
					<html:radio property="indicadorGrandeConsumidor" tabindex="4" value="" /><strong>Todos</strong>
					</td>
					
				</tr>	
				
				<tr>
					<td><strong>Indicador Bloquear Dados Social:<font color="#FF0000"></font></strong></td>
					<td><html:radio property="indicadorBloquearDadosSocial" tabindex="2" value="1" /><strong>Sim</strong>
					<html:radio property="indicadorBloquearDadosSocial" tabindex="3" value="2" /><strong>N�o</strong>
					<html:radio property="indicadorBloquearDadosSocial" tabindex="4" value="" /><strong>Todos</strong>
					</td>
					
				</tr>			
				
				
				<tr>
					<td><strong>Indicador Gerar D�bitos Segunda Via Conta:<font color="#FF0000"></font></strong></td>
					<td><html:radio property="indicadorGeraDebitoSegundaViaConta" tabindex="2" value="1" /><strong>Sim</strong>
					<html:radio property="indicadorGeraDebitoSegundaViaConta" tabindex="3" value="2" /><strong>N�o</strong>
					<html:radio property="indicadorGeraDebitoSegundaViaConta" tabindex="4" value="" /><strong>Todos</strong>
					</td>
					
				</tr>	
				
				<tr>
					<td><strong>Indicador Negativa��o do Cliente:<font color="#FF0000"></font></strong></td>
					<td><html:radio property="indicadorNegativacaoDoCliente" tabindex="2" value="1" /><strong>Sim</strong>
					<html:radio property="indicadorNegativacaoDoCliente" tabindex="3" value="2" /><strong>N�o</strong>
					<html:radio property="indicadorNegativacaoDoCliente" tabindex="4" value="" /><strong>Todos</strong>
					</td>
					
				</tr>	
				
				<tr>
					<td><strong>Indicador Cliente Corporativo:<font color="#FF0000"></font></strong></td>
					<td><html:radio property="indicadorCorporativo" tabindex="2" value="1" /><strong>Sim</strong>
					<html:radio property="indicadorCorporativo" tabindex="3" value="2" /><strong>N�o</strong>
					<html:radio property="indicadorCorporativo" tabindex="4" value="" /><strong>Todos</strong>
					</td>
					
				</tr>					
				
				<tr>
					<td><strong>Indicador Perfil Telemedido:<font color="#FF0000"></font></strong></td>
					<td><html:radio property="indicadorPerfilTelemedido" tabindex="2" value="1" /><strong>Sim</strong>
					<html:radio property="indicadorPerfilTelemedido" tabindex="3" value="2" /><strong>N�o</strong>
					<html:radio property="indicadorPerfilTelemedido" tabindex="4" value="" /><strong>Todos</strong>
					</td>
					
				</tr>
				
				<logic:present name="controleClienteCorporativo" scope="request">
					<tr>
						<td><strong>Consumo M�nimo (m3):</strong></td>
						<td><html:text property="consumoMinimo" maxlength="6" size="4" onkeyup="somente_numero(this);"/></td>
					</tr>
					
					<tr>
						<td><strong>Capacidade de Hidrometro:</strong></td>
						<td>
							<html:select property="hidrometroCapacidades" multiple="true" style="width:100%;">
								<html:optionsCollection property="colecaoHidrometroCapacidade" value="id" label="descricao"/>
							</html:select>
						</td>
					</tr>
				</logic:present>
				
				<logic:notPresent name="controleClienteCorporativo" scope="request">
					<html:hidden property="consumoMinimo"/>
					<html:hidden property="hidrometroCapacidades" value=""/>
				</logic:notPresent>
				
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
				
				<tr>
					<td> 
						&nbsp;	
					</td>	
				</tr>
				
				
				<tr>
					<td> 
						&nbsp;	
					</td>	
				</tr>		
				
			
				
				<tr>
					<td colspan="2"><input name="Button" type="button" class="bottonRightCol"
						value="Limpar" align="left"
						onclick="window.location.href='/gsan/exibirFiltrarImovelPerfilAction.do?menu=sim'"
						tabindex="8">
						
						<input type="button" 
								name="Button"
								class="bottonRightCol" 
								value="Cancelar" 
								tabindex="6"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"
								style="width: 80px" />
						
						</td>	
																							
					<td width="65" align="right"><input name="Button2" type="button"
						class="bottonRightCol" value="Filtrar" align="right"
						onClick="javascript:validarForm(document.forms[0]);" tabindex="9"></td>
				</tr>

			</table>

			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>

