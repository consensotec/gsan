<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>

<%@ page import="gsan.cobranca.NegativacaoCriterioCpfTipo"%>
<%@ page import="gsan.util.ConstantesSistema,java.util.Collection"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="AtualizarComandoNegativacaoPorCriterioActionForm" dynamicJavascript="false" />

<script language="JavaScript">
	
	function validateAtualizarComandoNegativacaoPorCriterioActionForm(form) {
	  if(form.simular[1].checked){
	    if(form.executarSimulacao[0].checked && (form.descricaoComandoSimulado.value == null || form.descricaoComandoSimulado.value == "")){
	      alert("Pesquisar Comando de Simula��o");
	      return false;
	    }
	  }
	  
	  if(form.indicadorCpfCnpjValido.value  == '')
		  form.indicadorCpfCnpjValido.value = '1';
	  
        return validateRequired(form)
   } 
   
    function required () { 
       this.aa = new Array("titulo", "Informe T�tulo.", new Function ("varName", " return this[varName];"));
       this.ab = new Array("solicitacao", "Descric�o da Solicita��o.", new Function ("varName", " return this[varName];"));
       this.ac = new Array("usuario", "Informe Usu�rio Respons�vel.", new Function ("varName", " return this[varName];"));
    } 
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    	var form = document.forms[0];
    	
	    if (tipoConsulta == 'usuario') {
	      form.usuario.value = codigoRegistro;
	      form.nomeUsuario.value = descricaoRegistro;
	      form.nomeUsuario.style.color = "#000000";
	    }
	    
    	if (tipoConsulta == 'comandoNegativacao') {
      		form.action = 'atualizarComandoNegativacaPorCriterioWizardAction.do?action=exibirAtualizarComandoNegativacaoDadosGeraisAction&idComandoNegativacao='+codigoRegistro
      		form.submit();
    	}  	    
  	}
    var comando = 0;
	
	function setComandoNegativacao(tipo) {
		comando = tipo;
	}
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    	var form = document.forms[0];
    	
	    if (tipoConsulta == 'usuario') {
	      form.usuario.value = codigoRegistro;
	      form.nomeUsuario.value = descricaoRegistro;
	      form.nomeUsuario.style.color = "#000000";
	    } else if (tipoConsulta == 'comandoNegativacao') {
	      if (comando == 1) {      		
      		form.submit();
	      } else if (comando == 2) {
			form.action = 'atualizarComandoNegativacaPorCriterioWizardAction.do?action=exibirAtualizarComandoNegativacaoDadosGeraisAction&idComandoNegativacaoSimulado='+codigoRegistro
      		form.submit();		      
	      }
	      comando = 0;
	    }    
  	}
	  function limparUsuario() {
			var form = document.forms[0];
			
	      	form.usuario.value = '';
		    form.nomeUsuario.value = '';
		
			form.usuario.focus();
	  }
	  
	function removerTitularidade(idTitularidade){
	  var formulario = document.forms[0]; 
	    formulario.action =  'atualizarComandoNegativacaPorCriterioWizardAction.do?action=exibirAtualizarComandoNegativacaoDadosGeraisAction&idTitularidadeRemover='+ idTitularidade;
	    formulario.submit();
    }   
    
   function desabilitarBotaoPesquisar(){
	  var form = document.forms[0]; 
	  if(form.executarSimulacao[0].checked){
	  	 form.ButaoPesquisar.disabled=false;     	     	     
	  }else{
	  	 form.idComandoSimulado.value = "";
	     form.descricaoComandoSimulado.value = "";	  
		 form.ButaoPesquisar.disabled=true;
	  }
	}
</script>

</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');desabilitarBotaoPesquisar(); limitTextArea(document.forms[0].titulo, 100, document.getElementById('utilizado'), document.getElementById('limite'));">

<div id="formDiv">
<html:form action="/atualizarComandoNegativacaPorCriterioWizardAction" method="post">

<html:hidden property="idComandoSimulado"/>

<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=1"/>


<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

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

      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Atualizar Comando de Negativa��o Por Crit�rio</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>

      <table width="100%" border="0">
      <tr>
      	<td colspan="3">Para determinar a negativa��o a ser comandada, informe os dados gerais abaixo:</td>
      </tr>
      <tr>
      	<td width="35%"><strong>Negativador:</strong></td>
       	<td colspan="2">
			<html:text property="nomeNegativador" size="50" maxlength="60" tabindex="4" readonly="true"
			style="background-color:#EFEFEF; border:0; color: #000000"/>
		</td>
	  </tr>
	  <tr>
         <td colspan="3"><hr></td>
      </tr>
      <tr>
      	<td HEIGHT="30" width="35%"><strong>T�tulo:<font color="#FF0000">*</font></strong></td>
        <td colspan="2">
			<html:textarea property="titulo" cols="40" rows="4" onkeyup="limitTextArea(document.forms[0].titulo, 100, document.getElementById('utilizado'), document.getElementById('limite'));"/><br>
			<strong><span id="utilizado">0</span>/<span id="limite">100</span></strong>
		</td>
      </tr>
      <tr>
      	<td HEIGHT="30" width="35%"><strong>Descri��o da Solicita��o:<font color="#FF0000">*</font></strong></td>
        <td colspan="2">
			<html:textarea property="solicitacao" cols="40" rows="4" /><br>
		</td>
      </tr>      
      
      <tr>
      	<td HEIGHT="30" width="35%"><strong>Simular a Negativa��o:<font color="#FF0000">*</font></strong></td>
        <td colspan="2">
			<html:radio property="simular" value="1" tabindex="1" onclick="redirecionarSubmit('atualizarComandoNegativacaPorCriterioWizardAction.do?action=exibirAtualizarComandoNegativacaoDadosGeraisAction&determinarData=ok');"/><strong>Sim
			<html:radio property="simular" value="2" tabindex="2" onclick="redirecionarSubmit('atualizarComandoNegativacaPorCriterioWizardAction.do?action=exibirAtualizarComandoNegativacaoDadosGeraisAction&determinarData=ok');"/>N�o</strong>
		</td>
      </tr>
      <logic:notPresent name="habilitarExecutarSimulacao">
      	<html:hidden property="executarSimulacao"/>
		<html:hidden property="descricaoComandoSimulado"/>
      </logic:notPresent>
      <logic:present name="habilitarExecutarSimulacao">
            <tr>
      	<td HEIGHT="30" width="35%"><strong>Executar o comando a partir de uma simula��o:<font color="#FF0000">*</font></strong></td>
        <td colspan="2">
			<html:radio property="executarSimulacao" value="1" tabindex="1" onclick="javascript:desabilitarBotaoPesquisar();"/><strong>Sim
			<html:radio property="executarSimulacao" value="2" tabindex="2" onclick="javascript:desabilitarBotaoPesquisar();"/>N�o</strong>
		</td>
      </tr>  
      <tr>
      	<td HEIGHT="30" width="35%"><strong>Indicador de Im�vel Categoria P�blico:</strong></td>
        <td colspan="2">
			<html:radio property="indicadorOrgaoPublico" value="1" disabled="true" /><strong>Sim
			<html:radio property="indicadorOrgaoPublico" value="2" disabled="true" />N�o</strong>
		</td>
      </tr>
      <tr>
      	<td HEIGHT="30" width="35%"><strong>Comando de Simula��o:</strong></td>
        <td>
			<html:text property="descricaoComandoSimulado" size="46" maxlength="100" tabindex="4" readonly="true"
			    style="background-color:#EFEFEF; border:0; color: #000000"/>
		</td>
		<td>
			<div align="right"><input name="ButaoPesquisar" type="button"
						class="bottonRightCol" value="Pesquisar" onclick="setComandoNegativacao(2); abrirPopup('exibirPesquisarComandoNegativacaoAction.do?menu=ok', 400, 750);" /></div>
		</td>
      </tr> 
      </logic:present>       
      <tr>
      	<td HEIGHT="30" width="35%"><strong>Data Prevista para execu��o:</strong></td>
        <td colspan="2">
			<html:text property="dataPrevista" size="11" maxlength="10" tabindex="12" onkeyup="mascaraData(this, event)" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
		</td>
      </tr>
      <tr>
		<td width="35%"><strong>Usu�rio Respons�vel:<font color="#FF0000">*</font></strong></td>
		<td colspan="2">
			<html:text property="usuario" size="9" maxlength="9"
				onkeypress="validaEnter(event, 'atualizarComandoNegativacaPorCriterioWizardAction.do?action=exibirAtualizarComandoNegativacaoDadosGeraisAction&pesquisarUsuario=ok', 'usuario'); return isCampoNumerico(event);" />
			<a href="javascript:abrirPopup('exibirUsuarioPesquisar.do?limpaForm=OK', 300, 350);">
			   <img src="<bean:message key='caminho.imagens'/>pesquisa.gif" border="0"
			width="23" height="21" style="cursor:hand;"	alt="Pesquisar"></a> 
			<logic:present name="corUsuario">
				<logic:equal name="corUsuario" value="exception">
					<html:text property="nomeUsuario" size="35"
						readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
				</logic:equal>
				<logic:notEqual name="corUsuario" value="exception">
					<html:text property="nomeUsuario" size="35"
						readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
				</logic:notEqual>
			</logic:present> 
			<logic:notPresent name="corUsuario">
				<logic:empty name="AtualizarComandoNegativacaoPorCriterioActionForm" property="nomeUsuario">
					<html:text property="nomeUsuario" size="35"
						value="" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000" />
				</logic:empty>
				<logic:notEmpty name="AtualizarComandoNegativacaoPorCriterioActionForm" property="nomeUsuario">
					<html:text property="nomeUsuario" size="35"
						readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
				</logic:notEmpty>
			</logic:notPresent> 

			<a href="javascript:limparUsuario();"> 
			<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" /></a>
		</td>
	  </tr>
      <tr>
      	<td HEIGHT="30" width="35%"><strong>Quantidade M�xima de inclus�es:</strong></td>
        <td colspan="2">
		  <html:text property="qtdMaximaInclusao" size="8" maxlength="5" tabindex="3"/>
		</td>
      </tr>	  	
      
      <tr>
      	<td><strong>S� Considerar CPF/CNPJ Validado:<font color="#FF0000">*</font></strong></td>
        <td colspan="2">
	        <c:choose>
	        	<c:when test="${sessionScope.alterarSoCPFCNPJValidos}">
					<html:radio property="indicadorCpfCnpjValido" value="1" tabindex="1" /><strong>Sim
					<html:radio property="indicadorCpfCnpjValido" value="2" tabindex="2" />N�o</strong>
	        	</c:when>
	        	<c:otherwise>
	        		<html:radio property="indicadorCpfCnpjValido" value="1" tabindex="1" disabled="true"/><strong>Sim
					<html:radio property="indicadorCpfCnpjValido" value="2" tabindex="2" disabled="true"/>N�o</strong>
	        	</c:otherwise>
	        </c:choose>
		</td>
      </tr>
        
	  <tr>
         <td colspan="3"><hr></td>
      </tr>
      <tr>
        <td HEIGHT="30" width="35%"><strong>Titularidade do CPF/CNPJ da Negativa��o:<font color="#FF0000">*</font></strong></td>
        <td>
          <logic:present name="colecaoCPFTipo">
		  <html:select property="titularidadeNegativacao" tabindex="10">
			<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
			<html:options collection="colecaoCPFTipo" labelProperty="descricaoTipoCpf" property="id"/>
		  </html:select>
		  </logic:present>
		</td>
		<td align="right">
	      <input type="button" class="bottonRightCol" value="Adicionar" tabindex="3" id="botaoEndereco" onclick="redirecionarSubmit('atualizarComandoNegativacaPorCriterioWizardAction.do?action=exibirAtualizarComandoNegativacaoDadosGeraisAction&adicionarTitularidade=ok');">	      							      		
	    </td>
      </tr>	  
	  <tr>
		<td colspan="3">

		<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td>
				<table width="100%" bgcolor="#99CCFF">
					<tr bgcolor="#99CCFF">
						<td align="center" width="15%"><strong>Remover</strong></td>
						<td>
						  <div align="left" width="55%"><strong>Titularidade do CPF/CNPJ da Negativa��o</strong></div>
						</td>
						<td>
						  <div align="center" width="15%"><strong>Ordem</strong></div>
						</td>
						<td>
						  <div align="center" width="15%"><strong>Coincidente</strong></div>
						</td>
					</tr>
				</table>
				</td>
			</tr>

			<%int cont = 0;%>

			<logic:present name="colecaoNegativacaoCriterioCpfTipo">
			<tr>
				<td>

				<div style="width: 100%; height: 100; overflow: auto;">

				<table width="100%" align="center" bgcolor="#99CCFF">

					<logic:iterate name="colecaoNegativacaoCriterioCpfTipo" id="negativacaoCriterioCpfTipo"
						type="NegativacaoCriterioCpfTipo">

						<%cont = cont + 1;
						if (cont % 2 == 0) {%>
						<tr bgcolor="#cbe5fe">
						<%} else {%>
						<tr bgcolor="#FFFFFF">
						<%}%>
							<td align="center" width="15%" valign="middle">
								<a href="javascript:removerTitularidade(<%="" + negativacaoCriterioCpfTipo.getCpfTipo().getId()%>)">
									<img src="<bean:message key='caminho.imagens'/>Error.gif" border="0" >
								</a>
							</td>
							<td width="55%"><bean:write name="negativacaoCriterioCpfTipo"
								property="cpfTipo.descricaoTipoCpf" />
							</td>	
							<%if(((Collection) session.getAttribute("colecaoNegativacaoCriterioCpfTipo")).size() == 1){ %>
								<td width="15%" align="center">
									<input type="text" style="text-align: center;font-size: xx-small;" 
									size="4" maxlength="2" align="center" disabled="true"/>
								</td>	
								<td width="15%" align="center">
									<div align="center"><input type="checkbox" 
									     disabled="true"/>
									</div>
								</td>							  						
							<%}else{ %>						
								<%if (negativacaoCriterioCpfTipo.getNumeroOrdemSelecao() == null) {%>
								<td width="15%" align="center">
									<input type="text" style="text-align: center;font-size: xx-small;" 
									size="4" maxlength="2" align="center"
									name="ordem<bean:write name="negativacaoCriterioCpfTipo" property="cpfTipo.id"/>" 
									value="" 
									/>
								</td>
								<%} else {%>
								<td width="15%" align="center">
									<input type="text" style="text-align: center;font-size: xx-small;" 
									size="4" maxlength="2" align="center" onkeypress="return isCampoNumerico(event);"
									name="ordem<bean:write name="negativacaoCriterioCpfTipo" property="cpfTipo.id"/>" 
									value="<%="" + negativacaoCriterioCpfTipo.getNumeroOrdemSelecao()%>" 
									/>
								</td>							
								<%}%>
								<%if (negativacaoCriterioCpfTipo.getIndicadorCoincidente() == 2) {%>
								<td width="15%" align="center">
									<div align="center"><input type="checkbox" 
									name="coincidente<bean:write name="negativacaoCriterioCpfTipo" property="cpfTipo.id"/>" 
									value="<%="" + negativacaoCriterioCpfTipo.getCpfTipo().getId()%>" />
									</div>
								</td>
								<%} else {%>
								<td width="15%" align="center">
									<div align="center"><input type="checkbox" checked
									name="coincidente<bean:write name="negativacaoCriterioCpfTipo" property="cpfTipo.id"/>" 
									value="<%="" + negativacaoCriterioCpfTipo.getCpfTipo().getId()%>" />
									</div>
								</td>	
								<%}%>
							<%} %>																						
						</tr>
					</logic:iterate>
					</table>
					</div>
					</td>
					</tr>
				   </logic:present>
				</table>
			</td>
	  </tr>
      <tr>
      	<td colspan="3" height="10"></td>
      </tr>
      <tr>
        <td colspan="3">
			<div align="right">
				<jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=1" />
			</div>
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

<%@ include file="/jsp/util/telaespera.jsp"%>

<script>
document.getElementById('botaoConcluir').onclick = function() { botaoAvancarTelaEspera('/gsan/atualizarComandoNegativacaPorCriterioWizardAction.do?concluir=true&action=atualizarComandoNegativacaoDadosGeraisAction'); }
</script>

</body>
</html:html>

