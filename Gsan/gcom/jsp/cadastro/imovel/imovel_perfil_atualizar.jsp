<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@ page import="gcom.util.ConstantesSistema"%>


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
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="AtualizarImovelPerfilActionForm" />

<SCRIPT LANGUAGE="JavaScript">
</SCRIPT>

<script type="text/javascript">

	function validarForm(formulario){
		var acao ='/gsan/atualizarImovelPerfilAction.do';
		if(validateAtualizarImovelPerfilActionForm(formulario)){

			//formulario.hidrometroCapacidadesHidden.value = adicionarHidrometroCapacidadesHidden();
			
			formulario.submit();
		}
	 }

	function adicionarHidrometroCapacidadesHidden(){
		var form = document.forms[0];

		var capacidadeSelecionadas = "";
		
		if(form.hidrometroCapacidades.disabled==false)
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

	function limparPermissaoEspecial(){
		var form = document.forms[0];

		form.idPermissaoEspecial.value = "";
		form.descricaoPermissaoEspecial.value = "";
	}

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

	    var form = document.forms[0];

	    if (tipoConsulta == 'permissaoEspecial') {
	      form.idPermissaoEspecial.value = codigoRegistro;
	      form.descricaoPermissaoEspecial.value = descricaoRegistro;
	      form.descricaoPermissaoEspecial.style.color = "#000000";
	      form.idPermissaoEspecial.focus();
	    }
	  }

	function habilitarDesabilitarCapacidade(){
		var form = document.forms[0];

		if(form.indicadorCorporativo.value=='2' && form.indicadorGrandeConsumidor.value=='2')
		{
			form.hidrometroCapacidades.disabled = true;
			form.consumoMinimo.disabled = true;
		}
		else if(form.indicadorCorporativo.value=='1' || form.indicadorGrandeConsumidor.value=='1')
		{
			form.hidrometroCapacidades.disabled = false;
			form.consumoMinimo.disabled = false;
		}
	}

	function desfazer(){
		var form = document.forms[0];

		form.action = "/gsan/exibirAtualizarImovelPerfilAction.do?desfazer=true";

		form.submit();
	}

	function voltar(){
		var form = document.forms[0];

		form.action = '/gsan/filtrarImovelPerfilAction.do?voltar=true';
		form.submit();
	}

	

</script>
</head>

<body leftmargin="5" topmargin="5" onload="habilitarDesabilitarCapacidade();">

<html:form action="/atualizarImovelPerfilAction.do" method="post">

	<INPUT TYPE="hidden" name="removerImovelPerfil">
	<INPUT TYPE="hidden" name="limparCampos" id="limparCampos">
	
	<html:hidden property="hidrometroCapacidadesHidden"/>


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

			<td width="625" valign="top" class="centercoltext">

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
					<td class="parabg">Atualizar Imóvel Perfil</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>

			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para Atualizar um imóvel perfil, informe os
					dados abaixo:</td>
				</tr>			
				
				<tr>
					<td><strong>C&oacute;digo:</strong></td>
					<td><html:hidden property="id" /> <bean:write
						name="AtualizarImovelPerfilActionForm" property="id" /></td>
				</tr>				
			

				<tr>
					<td height="30"><strong>Descrição:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="2"><html:text property="descricao" maxlength="20"
						size="30"/><br>
					</td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Uso:<font color="#FF0000">*</font></strong></td>
					<td><strong> <span class="style1"> <html:radio
						property="indicadorUso" value="<%=ConstantesSistema.SIM.toString()%>"
						tabindex="13" /> Sim <html:radio
						property="indicadorUso" value="<%=ConstantesSistema.NAO.toString()%>"/>
					Não </span></strong></td>
				</tr>	
				
				<tr>
					<td><strong>Indicador Geração Automática:<font color="#FF0000">*</font></strong></td>
					<td><strong> <span class="style1"> <html:radio
						property="indicadorGeracaoAutomatica" value="<%=ConstantesSistema.SIM.toString()%>"
						tabindex="13" /> Sim <html:radio
						property="indicadorGeracaoAutomatica" value="<%=ConstantesSistema.NAO.toString()%>"/>
					Não </span></strong></td>
				</tr>	
				
				<tr>
					<td><strong>Indicador Inserir Manter Perfil:<font color="#FF0000">*</font></strong></td>
					<td><strong> <span class="style1"> <html:radio
						property="indicadorInserirManterPerfil" value="<%=ConstantesSistema.SIM.toString()%>"
						tabindex="13" /> Sim <html:radio
						property="indicadorInserirManterPerfil" value="<%=ConstantesSistema.NAO.toString()%>"/>
					Não </span></strong></td>
				</tr>
				
				<tr>
					<td><strong>Indicador Gerar Dados Leitura:<font color="#FF0000">*</font></strong></td>
					<td><strong> <span class="style1"> <html:radio
						property="indicadorGerarDadosLeitura" value="<%=ConstantesSistema.SIM.toString()%>"
						tabindex="13" /> Sim <html:radio
						property="indicadorGerarDadosLeitura" value="<%=ConstantesSistema.NAO.toString()%>"/>
					Não </span></strong></td>
				</tr>
				
				<tr>
					<td><strong>Indicador Bloquear Retificação:<font color="#FF0000">*</font></strong></td>
					<td><strong> <span class="style1"> <html:radio
						property="indicadorBloquearRetificacao" value="<%=ConstantesSistema.SIM.toString()%>"
						tabindex="13" /> Sim <html:radio
						property="indicadorBloquearRetificacao" value="<%=ConstantesSistema.NAO.toString()%>"/>
					Não </span></strong></td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Grande Cliente:<font color="#FF0000">*</font></strong></td>
					<td><strong> <span class="style1"> <html:radio
						property="indicadorGrandeConsumidor" value="<%=ConstantesSistema.SIM.toString()%>"
						tabindex="13" onclick="habilitarDesabilitarCapacidade();"/> Sim <html:radio
						property="indicadorGrandeConsumidor" value="<%=ConstantesSistema.NAO.toString()%>" onclick="habilitarDesabilitarCapacidade();"/>
					Não </span></strong></td>
				</tr>
				
				<tr>
					<td><strong>Indicador Bloquear Dados Social:<font color="#FF0000"></font></strong></td>
					<td><strong> <span class="style1"> <html:radio
						property="indicadorBloquearDadosSocial" value="<%=ConstantesSistema.SIM.toString()%>"
						tabindex="13" /> Sim <html:radio
						property="indicadorBloquearDadosSocial" value="<%=ConstantesSistema.NAO.toString()%>"/>
					Não </span></strong></td>
				</tr>
				
				<tr>
					<td><strong>Indicador Gerar Débitos Segunda Via Conta:<font color="#FF0000">*</font></strong></td>
					<td><strong> <span class="style1"> <html:radio
						property="indicadorGeraDebitoSegundaViaConta" value="<%=ConstantesSistema.SIM.toString()%>"
						tabindex="13" /> Sim <html:radio
						property="indicadorGeraDebitoSegundaViaConta" value="<%=ConstantesSistema.NAO.toString()%>"/>
					Não </span></strong></td>
				</tr>	

				<tr>
					<td><strong>Indicador de Gerar Multa/Juros:<font color="#FF0000">*</font></strong></td>
					<td><strong> <span class="style1"> <html:radio
						property="indicadorAcrescimosImpontualidade" value="<%=ConstantesSistema.SIM.toString()%>"
						tabindex="13" /> Sim <html:radio
						property="indicadorAcrescimosImpontualidade" value="<%=ConstantesSistema.NAO.toString()%>"/>
					Não </span></strong></td>
				</tr>	
				
				<tr>
					<td><strong>Indicador Negativação do Cliente:<font color="#FF0000">*</font></strong></td>
					<td><strong> <span class="style1"> <html:radio
						property="indicadorNegativacaoDoCliente" value="<%=ConstantesSistema.SIM.toString()%>"
						tabindex="13" /> Sim <html:radio
						property="indicadorNegativacaoDoCliente" value="<%=ConstantesSistema.NAO.toString()%>"/>
					Não </span></strong></td>
				</tr>	
				
				<tr>
					<td><strong>Indicador Cliente Corporativo:<font color="#FF0000">*</font></strong></td>
					<td><strong> <span class="style1"> <html:radio
						property="indicadorCorporativo" value="<%=ConstantesSistema.SIM.toString()%>"
						tabindex="13" onclick="habilitarDesabilitarCapacidade();"/> Sim <html:radio
						property="indicadorCorporativo" value="<%=ConstantesSistema.NAO.toString()%>"  onclick="habilitarDesabilitarCapacidade();"/>
					Não </span></strong></td>
				</tr>	
				
				<tr>
					<td><strong>Indicador Perfil Telemedido:<font color="#FF0000">*</font></strong></td>
					<td><strong> <span class="style1"> <html:radio
						property="indicadorPerfilTelemedido" value="<%=ConstantesSistema.SIM.toString()%>"
						tabindex="13" /> Sim <html:radio
						property="indicadorPerfilTelemedido" value="<%=ConstantesSistema.NAO.toString()%>"/>
					Não </span></strong></td>
				</tr>
				
				<logic:present name="controleClienteCorporativo" scope="request">
					<tr>
						<td><strong>Consumo Mínimo (m3):</strong></td>
						<td><html:text property="consumoMinimo" size="4" maxlength="6" onkeyup="somente_numero(this);"/></td>
					</tr>
					
					<tr>
						<td><strong>Capacidade de Hidrometro:</strong></td>
						<td>
							<html:select property="hidrometroCapacidades" multiple="true" style="width:100%;">
								<html:optionsCollection property="colecaoHidrometroCapacidade" value="id" label="descricao"/>
							</html:select>
						</td>
					</tr>
					
					<tr>
						<td>
							<strong>Permissão Especial:</strong>
						</td>
						<td>
							<html:text property="idPermissaoEspecial" size="4" maxlength="4"
								onkeypress="javascript:validaEnterComMensagem(event, 'exibirAtualizarImovelPerfilAction.do?pesquisarPermissaoEspecial=true', 'idPermissaoEspecial', 'Permissão Especial');" />
							<a href="javascript:abrirPopup('exibirPesquisarPermissaoEspecialAction.do?menu=true', 400, 800);">
								<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" border="0"
									height="21" style="cursor:hand;" title="Pesquisar Permissão Especial" alt="Pesquisar Permissão Especial"></a> 
							<logic:present name="idPermissaoEspecialNaoEncontrado" scope="request">
								<html:text property="descricaoPermissaoEspecial" size="40" maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000" />
							</logic:present> 
							<logic:notPresent name="idPermissaoEspecialNaoEncontrado" scope="request">
								<html:text property="descricaoPermissaoEspecial" size="40" maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notPresent> 
							<a href="javascript:limparPermissaoEspecial();">
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /></a>
						</td>
					</tr>
				</logic:present>
				
				<logic:notPresent name="controleClienteCorporativo" scope="request">
					<html:hidden property="consumoMinimo"/>
					<html:hidden property="hidrometroCapacidades"/>
					<html:hidden property="idPermissaoEspecial"/>
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
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
					
				</tr>

				<tr>
					<td width="40%" align="left"><input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Voltar"
						onClick="window.location.href='/gsan/filtrarImovelPerfilAction.do'"> <input type="button"
						name="ButtonReset" class="bottonRightCol" value="Desfazer"
						onClick="desfazer();"> <input type="button"
						name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>
					<td align="right"><input type="button"
						onClick="javascript:validarForm(document.forms[0]);"
						name="botaoAtualizar" class="bottonRightCol" value="Atualizar"></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>

