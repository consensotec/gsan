<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>

<%@ include file="/jsp/util/telaespera.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page isELIgnored="false"%>
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
	formName="ConsultarArquivoTextoLeituraActionForm"
	dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<style>
.styleFontePequena{font-size:9px;
                   color: #000000;
				   font:Verdana, Arial, Helvetica, sans-serif}
.styleFontePeqNegrito{font-size:11px;
                   color: #000000;
				   font-weight: bold}
</style>

<script language="JavaScript">

	 
	 function validaForm(){
		 form = document.forms[0];
		 
		 if(form.idEmpresaContratada.value == -1){
			 alert('Informar Empresa');
			 return false;
		 }
		 if(form.periodoApuracao.value == ''){
			 alert('Informar Período Apuração');
			 return false;
		 }
		 if(qtdSelecionadosRadio(form.opcaoBoletim) == 0){
			 alert('Informar Opção do Boletim');
			 return false;
		 }
		 if(qtdSelecionadosRadio(form.opcaoRelatorio) == 0){
			 alert('Informar Opção de Relatório');
			 return false;
		 }
		 
		 form.action = "gerarRelatorioBoletimMedicaoAcompanhamentoAction.do";
		 form.submit();
	 }
	 
	 function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, campo){
			if(!campo.disabled){
				if (objeto == null || codigoObjeto == null){
			    	if(tipo == "" ){
			      		abrirPopup(url,altura, largura);
			    	}else{
			  			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
					}
				}else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
				}
			}
		}
	 
	 function campoNumerico(campo) {
		var value = campo.value;
	    var bool = isNaN(+value);
	    bool = bool || (value.indexOf('.') != -1);
	    bool = bool || (value.indexOf(",") != -1);
	    if(bool && value.indexOf("/") == -1)
	    	campo.value = '';
	}
	 
	 function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
			var form = document.forms[0];
			
		 	if(tipoConsulta == "localidade"){
		 		form.idLocalidade.value = codigoRegistro;
		 		form.descricaoLocalidade.value = descricaoRegistro;
		 		form.descricaoLocalidade.style.color = "#000000";
		 		habilitarDesabilitar(1);
		 	}
	 }
	 
	 function limparConjunto(id){
		    var form = document.forms[0];
		 	if(id == 1){
		 		form.idLocalidade.value = '';
		 		form.descricaoLocalidade.value = '';
		 		form.descricaoLocalidade.style.color = "#000000";
		 	}
	 }
	 
	 function reload(indicador,campo){
		 document.forms[0].action = 'exibirGerarRelatorioBoletimMedicaoAcompanhamentoAction.do?indicador='+indicador+"&campo="+campo;
		 document.forms[0].submit();
	 }
	 
	 
	 function verificaData(event){
			
	       	var valor = null;
	     	if (event.which == null){
	        	valor= String.fromCharCode(event.keyCode);   
			}else if (event.which != 0 && event.charCode != 0){
				valor= String.fromCharCode(event.which);
			}   
			 
			if(valor != '/'){
				return isCampoNumerico(event);
			}
		
	    }
	 
	 function habilitarDesabilitar(indicador){
		 form = document.forms[0];
		 if(indicador == 2){
			 if(qtdSelecionados(form.idsRegiao) != 0 ||
				qtdSelecionados(form.idsMicroregiao) != 0 || 
				qtdSelecionados(form.idsMunicipio) != 0){
					 desselecionar(form.idsGerenciaRegional);
					 desselecionar(form.idsUnidadeNegocio);
					 form.idLocalidade.value = '';
					 form.idsGerenciaRegional.disabled = true;
					 form.idsUnidadeNegocio.disabled = true;
					 form.idLocalidade.disabled = true;
					 document.getElementById("idLocalidadeLink").href = "javascript:void(0)";
				 
			 }
			 else{
				 form.idsGerenciaRegional.disabled = false;
				 form.idsUnidadeNegocio.disabled = false;
				 form.idLocalidade.disabled = false;
				 document.getElementById("idLocalidadeLink").href = "javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', null, null, null, 400, 800, '',document.forms[0].idLocalidade);";
			 }	 
		 }
		 else if(indicador == 1){
			 if(qtdSelecionados(form.idsGerenciaRegional) != 0 ||
				qtdSelecionados(form.idsUnidadeNegocio) != 0 || 
				form.idLocalidade.value != ''){
					 desselecionar(form.idsRegiao);
					 desselecionar(form.idsMicroregiao);
					 desselecionar(form.idsMunicipio);
					 form.idsRegiao.disabled = true;
					 form.idsMicroregiao.disabled = true;
					 form.idsMunicipio.disabled = true;
				 
			 }
			 else{
				 form.idsRegiao.disabled = false;
				 form.idsMicroregiao.disabled = false;
				 form.idsMunicipio.disabled = false;
			 }
		 }
	 }
	 
	 
	 function qtdSelecionados(selObj) {
	   var totalChecked = 0;
	   for (i = 0; i < selObj.options.length; i++) {
	      if (selObj.options[i].selected && selObj.options[i].value != -1) {
	         totalChecked++;
	      }
	   }
	  return totalChecked;	
	}
	
	 
	 function qtdSelecionadosRadio(selObj) {
		   var totalChecked = 0;
		   for (i = 0; i < selObj.length; i++) {
		      if (selObj[i].checked) {
		         totalChecked++;
		      }
		   }
		  return totalChecked;	
		}
	 
	function desselecionar(selObj){
		for (i = 0; i < selObj.options.length; i++) {
	      if (selObj.options[i].selected) {
	    	  selObj.options[i].selected = false;
	      }
	   }
	} 
	
	function limparArrecadacao(){
		document.forms[0].encerramentoArrecadacao.value = "";
	}
	 
	
</script>


</head>

<body leftmargin="5" topmargin="5">
<div id="formDiv">  
<html:form action="/gerarRelatorioBoletimMedicaoAcompanhamentoAction"
	name="GerarRelatorioBoletimMedicaoAcompanhamentoActionForm"
	type="gcom.gui.cobranca.cobrancaporresultado.GerarRelatorioBoletimMedicaoAcompanhamentoActionForm"
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
			<td width="615" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>

			<table>
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Relatório de Boletim de Medição e de Acompanhamento</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="6">Para gerar o relatório de boletim de medição e de acompanhamento
					, informe os dados abaixo:</td>
					<td align="right"></td>
				</tr>

				<tr>

					<td><strong>Empresa:<font color="#FF0000">*</font></strong></td>
					<td colspan="2" align="left"><html:select property="idEmpresaContratada"
						tabindex="4">
						<html:option value="-1">&nbsp;</html:option>
							<logic:present name="colecaoEmpresasContratadas">					
								<html:options collection="colecaoEmpresasContratadas"
									labelProperty="descricao" property="id" />
							</logic:present>
						</html:select>
					</td>

				</tr>

				<tr>
	                <td><strong>Período Apuração:<font color="#FF0000">*</font></strong></td>
		                <td colspan="2"><strong>
		                  <html:text maxlength="7"
		                  			 size="7"
		                  			 tabindex="1"
		                  			 property="periodoApuracao"
			                  			 onkeypress="javascript:validaEnterString(event, 'exibirGerarRelatorioBoletimMedicaoAcompanhamentoAction.do?pesquisarDataEncerramento=OK', 'periodoApuracao');return verificaData(event);"
			                  			 onchange="campoNumerico(this);limparArrecadacao();"
			                  			 onkeyup="mascaraAnoMes(this, event);"/>
		                    	   
		     			</strong>(MM/AAAA)
		     			 &nbsp;&nbsp;&nbsp;
			     		<strong>Encerramento da Arrecadação:
		             	 <html:text
	     					   maxlength="10"
	     					   size="10"
	     					   readonly="true"
	     					   property="encerramentoArrecadacao"
	 		     					   style="background-color:#EFEFEF; border:0; color: #000000"/>
		   		     					   
						</strong>

                 </tr>
                 
                 
                 <tr>
					<!--<td colspan="4" bgcolor="#3399FF"> -->
					<td colspan="5" bgcolor="#000000" height="1" valign="baseline"></td>
			     </tr>
              
              		<tr>
						<td width="30%"><strong>Gerência Regional:</strong></td>
						<td colspan="2"><html:select property="idsGerenciaRegional" tabindex="3" multiple="mutiple" size="4" onchange="javascript:habilitarDesabilitar(1)" onblur="javascript:reload(1,1)" >
							<logic:notEmpty name="colecaoGerenciaRegional">
								<html:option value="-1">&nbsp;</html:option>
								<html:options collection="colecaoGerenciaRegional"
									labelProperty="nome" property="id" />
							</logic:notEmpty>
						</html:select></td>
					</tr>
					<tr>
						<td width="30%"><strong>Unidade Negócio:</strong></td>
						<td colspan="2"><html:select property="idsUnidadeNegocio" tabindex="3" multiple="mutiple" size="4" onchange="javascript:habilitarDesabilitar(1)" >
							<logic:notEmpty name="colecaoUnidadeNegocio">
							<html:option value="-1">&nbsp;</html:option>
								<html:options collection="colecaoUnidadeNegocio"
									labelProperty="nome" property="id" />
							</logic:notEmpty>
						</html:select></td>
					</tr>	
              
	              <tr>
		                <td><strong>Localidade:</strong></td>
		                <td colspan="2"><strong>
		                  <html:text maxlength="3"
		                  			 size="3"
		                  			 tabindex="1"
		                  			 property="idLocalidade"
		                  			 onkeypress="javascript:validaEnterComMensagem(event, 'exibirGerarRelatorioBoletimMedicaoAcompanhamentoAction.do?pesquisarLocalidade=OK&indicador=1', 'idLocalidade','Localidade');return isCampoNumerico(event);" 
		                  			 onchange="campoNumerico(this);habilitarDesabilitar(1);"/>
		                <a id="idLocalidadeLink" href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', null, null, null, 400, 800, '',document.forms[0].idLocalidade);">
							<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							     title="Pesquisar Localidade" /></a>
						<logic:notPresent name="localidadeException" scope="request">	     
			     			<html:text
			     					   maxlength="40"
			     					   size="40"
			     					   readonly="true"
			     					   property="descricaoLocalidade"
	   		     					   style="background-color:#EFEFEF; border:0; color: #000000"/>
		     			</logic:notPresent>
		     			<logic:present name="localidadeException" scope="request">
			     			<html:text
			     					   maxlength="40"
			     					   size="40"
			     					   readonly="true"
			     					   property="descricaoLocalidade"
			     					   style="background-color:#EFEFEF; border:0; color: #ff0000"/>	
		     			</logic:present>	     
		     			</strong>
		     			<a href="javascript:limparConjunto(1);habilitarDesabilitar(1);">
							<img border="0" title="Apagar" src="/gsan/imagens/limparcampo.gif">
						</a>
		     		</td>
	             </tr>
              
              
                 <tr>
					<!--<td colspan="4" bgcolor="#3399FF"> -->
					<td colspan="5" bgcolor="#000000" height="1" valign="baseline"></td>
			     </tr>
              
         
				<tr>
					<td width="30%"><strong>Região:</strong></td>
					<td colspan="2"><html:select property="idsRegiao" tabindex="3" multiple="mutiple" size="4" onblur="javascript:reload(2,2)" onchange="javascript:habilitarDesabilitar(2)">
						<logic:notEmpty name="colecaoRegiao">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoRegiao"
								labelProperty="nome" property="id" />
						</logic:notEmpty>
					</html:select></td>
				</tr>
				<tr>
					<td width="30%"><strong>Microrregião:</strong></td>
					<td colspan="2"><html:select property="idsMicroregiao" tabindex="3" multiple="mutiple" size="4" onblur="javascript:reload(2,3)" onchange="javascript:habilitarDesabilitar(2)">
						<logic:notEmpty name="colecaoMicroregiao">
						<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoMicroregiao"
								labelProperty="nome" property="id" />
						</logic:notEmpty>
					</html:select></td>
				</tr> 
				
				<tr>
					<td width="30%"><strong>Município:</strong></td>
					<td colspan="2"><html:select property="idsMunicipio" tabindex="3" multiple="mutiple" size="4" onchange="javascript:habilitarDesabilitar(2)">
						<logic:notEmpty name="colecaoMunicipio">
						<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoMunicipio"
								labelProperty="nome" property="id" />
						</logic:notEmpty>
					</html:select></td>
				</tr>                   
				
				<tr>
					<!--<td colspan="4" bgcolor="#3399FF"> -->
					<td colspan="5" bgcolor="#000000" height="1" valign="baseline"></td>
			    </tr>
					
				<tr>
					<td width="50%"><strong>Opção do Boletim:<font color="#FF0000">*</font></strong></td>
					<td width="50%"><strong> <span class="style1"><strong> 
					<html:radio property="opcaoBoletim" value="1" tabindex="4" /> <strong>Geral 
					<html:radio property="opcaoBoletim" value="2" tabindex="5" /> Parcelamento
					</strong></strong></span></strong></td>
				</tr>
				
				<tr>
					<td width="50%"><strong>Opção de Relatório:<font color="#FF0000">*</font></strong></td>
					<td width="50%"><strong> <span class="style1"><strong> 
					<html:radio property="opcaoRelatorio" value="1" tabindex="4" /> <strong>Analítico 
					<html:radio property="opcaoRelatorio" value="2" tabindex="5" /> Resumido
					</strong></strong></span></strong></td>
				</tr>	
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td align="left" colspan="3"><font color="#FF0000">*</font> Campo Obrigatório</td>
				</tr>
<!-- 				<tr>
					<!--<td colspan="4" bgcolor="#3399FF">
					<td colspan="5" bgcolor="#000000" height="2" valign="baseline"></td>
			    </tr> -->	
				<tr>
					<td>&nbsp;</td>
				</tr>
					
				              			
				<tr>
					<td colspan="2" ><input name="Button" type="button" class="bottonRightCol"
						value="Desfazer" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirGerarRelatorioBoletimMedicaoAcompanhamentoAction.do?menu=sim"/>'">
					<input type="button" name="ButtonCancelar" class="bottonRightCol"
						value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"></td>
					<td align="right"><%--<INPUT type="button" class="bottonRightCol" value="Inserir" tabindex="13" style="width: 70px;" onclick="validarForm(document.forms[0]);">--%>

		  			<gsan:controleAcessoBotao name="Botao" value="Selecionar"
						onclick="validaForm();"
						url="gerarRelatorioBoletimMedicaoAcompanhamentoAction.do" tabindex="13" /></td>
				</tr>
				
					
				</table>
			</td></tr>
		</table>
	<p>&nbsp;</p>
<logic:present name="indicadorBloqueio" scope="request">
	<script>javascript:habilitarDesabilitar(${requestScope.indicadorBloqueio});</script>
</logic:present>
		
<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</div>
</body>
</html:html>