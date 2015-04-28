<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<%@ page import="gsan.util.ConstantesSistema" %>
<%@page import="gsan.util.Util" isELIgnored="false"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false" formName="GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeActionForm" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>

<script language="JavaScript">
	function pesquisarLocalidade() {
		var form = document.forms[0];
		abrirPopup('exibirPesquisarLocalidadeAction.do?limpaForm=S', 495, 300);
	}	
	
	function limparLocalidade() {
		var form = document.forms[0];
		
		form.idLocalidade.value = "";
		form.nomeLocalidade.value = "";	
	}

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    	var form = document.forms[0];
    	form.idLocalidade.value = codigoRegistro;
		form.action = 'exibirGerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeAction2.do?';
		form.submit();
    }

	function verificarOpcaoRelatorio() {
		
	}

	function camposDesabilitados(){
		var form = document.forms[0];

		form.idRegiao.disabled = true;
		form.idMicroregiao.disabled = true;
		form.idMunicipio.disabled = true;
	}

	function validarPeriodoApuracao(){
		var form = document.forms[0];
		var retorno = true;
		
		if(form.periodoApuracao.value == ""){
			alert("Informe período de apuração.");
			retorno = false;
		}else{
			retorno = verificaAnoMes(form.periodoApuracao);
		}
		return retorno;
	}

	function voltar(){
		var form = document.forms[0];
		form.action = 'exibirGerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeAction.do?selecionarComandos=sim';
		form.submit();
	}
	
</script>

<script type="text/javascript">
	$(document).ready(function(){
		$('#botaoDesfazer').click(function(){			
			$('#form').attr("action", "exibirGerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeAction2.do?desfazer=sim").submit();
		});
		
		$('[name=idGerencia]').change(function(){
			$('#form').attr("action", "exibirGerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeAction2.do?mudouGerencia=sim").submit();
		});
		
		$('[name=idRegiao]').change(function(){
			$('#form').attr("action", "exibirGerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeAction2.do?mudouRegiao=sim").submit();
		});

		$('[name=idMicroregiao]').change(function(){
			$('#form').attr("action", "exibirGerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeAction2.do?mudouMicroregiao=sim").submit();
		});
		
		$('#limparLocalidade').click(function(){			
			$('[name=idLocalidade], [name=nomeLocalidade]').val("");
			if($('[name=idGerencia], [name=idUnidadeNegocio]').val() == -1){
				$('[name=idRegiao] option, [name=idMicroregiao] option, [name=idMunicipio] option').removeAttr("selected");
				$('[name=idRegiao], [name=idMicroregiao], [name=idMunicipio]').removeAttr("disabled");
			}
		});

		$('#limparLocalidade').click(function(){			
			$('[name=idLocalidade], [name=nomeLocalidade]').val("");
			if($('[name=idGerencia], [name=idUnidadeNegocio]').val() == -1){
				$('[name=idRegiao] option, [name=idMicroregiao] option, [name=idMunicipio] option').removeAttr("selected");
				$('[name=idRegiao], [name=idMicroregiao], [name=idMunicipio]').removeAttr("disabled");
			}
		});

		$('#botaoGerar').click(function(){
			var form = document.forms[0];
			if(validarPeriodoApuracao()){
				if (form.opcaoRelatorio.checked == true) {
					form.action = 'gerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeAction.do?opcaoRelatorio=1'
				} else {
					form.action = 'gerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeAction.do?opcaoRelatorio=2'
				}
				$('#form').submit();
			}
		});		
	});
</script>

<logic:present name="filtroGerenciaLocalidade" scope="session">
	<script type="text/javascript">
		$(document).ready(function(){
			$('[name=idRegiao], [name=idMicroregiao], [name=idMunicipio]').attr("disabled", true);
			$('[name=idRegiao] option, [name=idMicroregiao] option, [name=idMunicipio] option').removeAttr("selected");
		});
	</script>
</logic:present>

<logic:present name="filtroRegiao" scope="session">
	<script type="text/javascript">
		$(document).ready(function(){
			$('[name=idLocalidade], [name=nomeLocalidade], [name=idGerencia], [name=idUnidadeNegocio]').attr("disabled", true);
			$('[name=idGerencia], [name=idUnidadeNegocio]').val(-1);
			$('[name=idLocalidade], [name=nomeLocalidade]').val("");
		});
	</script>
</logic:present>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/gerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeAction"
	name="GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeActionForm2"
	type="gsan.gui.relatorio.atendimentopublico.GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeActionForm2"
	method="post"
	styleId="form">
	
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="150" valign="top" class="leftcoltext">
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
			<td width="625" valign="top" bgcolor="#003399" class="centercoltext">
			<table height="100%">

				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Relatório de Boletim de Medição - Inspeção de Anormalidade</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td width="31%">
						<strong>Descrição do Comando:</strong></td>
					<td width="69%">
						<html:text property="descricaoComando" size="50" disabled="true"/>
					</td>	
				</tr>
			
				<tr>
					<td>
						<strong>Período Apuração:</strong></td>
					<td>
						<html:text property="periodoApuracao" size="7" maxlength="7" onkeyup="mascaraAnoMes(this, event);" /> (MM/AAAA)
					</td>
				</tr>
				
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				
				<tr>
					<td colspan="3">Para gerar o relatório de boletim de medição das OS de Inspeção de Anormalidade, informe os dados abaixo:</td>
				</tr>

			 	<tr> 
	              	<td height="24" colspan="4" bordercolor="#000000" class="style1"> 
	                  	<hr>
	                </td>
			  	</tr>

				
				<tr>
				   	<td>
				   		<strong>Gerência:</strong>
				   	</td>
				   	<td>
						<logic:notPresent name="possuiGerencia" scope="session">
							<html:select property="idGerencia" style="width: 200px;" tabindex="9">
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<html:options collection="colecaoGerencia" labelProperty="nome" property="id"/>
							</html:select>
						</logic:notPresent>
						<logic:present name="possuiGerencia" scope="session">
							<html:select property="idGerencia" style="width: 200px;" tabindex="9" disabled="disabled">
								<html:options collection="colecaoGerencia" labelProperty="nome" property="id"/>
							</html:select>
						</logic:present>
				   </td>
   				</tr>

				<tr>
				   	<td>
				   		<strong>Unidade Negócio:</strong>
				   	</td>
				   	<td>
						<logic:notPresent name="possuiUnidade" scope="session">
							<html:select property="idUnidadeNegocio" style="width: 200px;" tabindex="9">
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<html:options collection="colecaoUnidade" labelProperty="nome" property="id"/>
							</html:select>
						</logic:notPresent>
						<logic:present name="possuiUnidade" scope="session">
							<html:select property="idUnidadeNegocio" style="width: 200px;" tabindex="9" disabled="disabled">
								<html:options collection="colecaoUnidade" labelProperty="nome" property="id"/>
	   						</html:select>
	   					</logic:present>
				   </td>
   				</tr>

				<tr>
					<td width="25%"><strong>Localidade:</strong></td>
					<td width="75%">
						<html:text maxlength="3" property="idLocalidade" size="3" tabindex="14"   
							 onkeypress="validaEnterComMensagem(event, 'exibirGerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeAction2.do', 'idLocalidade', 'Localidade');camposDesabilitados();"/>
						<a href="javascript:pesquisarLocalidade();">
							<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" height="21" alt="Pesquisar" border="0"></a> 
						
						<logic:present name="localidadeInexistente" scope="request">
							<html:text property="nomeLocalidade" size="40" maxlength="40"
								readonly="true"
								style="border: 0pt none; background-color:#EFEFEF; color: #ff0000" />
						</logic:present> 
						
						<logic:notPresent name="localidadeInexistente" scope="request">
							<html:text property="nomeLocalidade" size="40" maxlength="40"
								readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notPresent> 
						<a href="javascript:limparLocalidade();" id="limparLocalidade"> 
							<img border="0" src="imagens/limparcampo.gif" height="21" width="23"> 
						</a>
					</td>
				</tr>

				<tr> 
	              	<td height="24" colspan="4" bordercolor="#000000" class="style1"> 
	                  	<hr>
	                </td>
			  	</tr>

				<logic:notEmpty name="colecaoRegiao">
					<tr>
						<td width="180"><strong>Região:</strong></td>
						<td align="right">
							<div align="left">
								<strong>
									<html:select property="idRegiao" style="width: 160px;"  multiple="false" size="4">
										<html:option value="-1">&nbsp;</html:option>
										<html:options collection="colecaoRegiao" labelProperty="nome" property="id" />
									</html:select>
								</strong>
							</div>
						</td>
					</tr>
				</logic:notEmpty>

				<tr>
					<td width="180"><strong>Microregião:</strong></td>
					<td align="right">
						<div align="left">
							<strong>
								<logic:notEmpty name="colecaoMicroregiao">
									<html:select property="idMicroregiao" style="width: 160px;" multiple="false" size="4">
										<html:option value="-1">&nbsp;</html:option>
										<html:options collection="colecaoMicroregiao" labelProperty="nome" property="id" />
									</html:select>
								</logic:notEmpty>
								
								<logic:empty name="colecaoMicroregiao">
									<html:select property="idMicroregiao" style="width: 160px;" multiple="false" size="4" disabled="disabled">
										
									</html:select>
								</logic:empty>
							</strong>
						</div>
					</td>
				</tr>
			
				<tr>
					<td width="180"><strong>Município:</strong></td>
					<td align="right">
						<div align="left">
							<strong>
								<logic:notEmpty name="colecaoMunicipio">
									<html:select property="idMunicipio" style="width: 160px;" multiple="false" size="4">
										<html:option value="-1">&nbsp;</html:option>
										<html:options collection="colecaoMunicipio" labelProperty="nome" property="id" />
									</html:select>
								</logic:notEmpty>
								
								<logic:empty name="colecaoMunicipio">
									<html:select property="idMunicipio" style="width: 160px;" multiple="false" size="4" disabled="disabled">
										
									</html:select>
								</logic:empty>
							</strong>
						</div>
					</td>
				</tr>

				<tr> 
	              	<td height="24" colspan="4" bordercolor="#000000" class="style1"> 
	                  	<hr>
	                </td>
			  	</tr>

				<tr>
					<td>
						<strong>Opção Relatório:</strong>
					</td>
					<td>
						<input type="checkbox" name="opcaoRelatorio"/>Arquivo Texto
					</td>
				</tr>
				
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				
				<tr>
					<td colspan="3">
						<input name="Button" type="button" class="bottonRightCol" value="Voltar" onclick="voltar();" >
						<input name="Button" type="button" class="bottonRightCol" value="Desfazer" align="left" id="botaoDesfazer">
						<input name="Button" type="button" class="bottonRightCol" value="Cancelar" align="left"
							onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>
					<td align="right">
						<input type="button" id="botaoGerar" name="movimentar" class="bottonRightCol" value="Gerar"/>
					</td>
				</tr>
			</table>
		</table>
		<%@ include file="/jsp/util/rodape.jsp"%> 
	</html:form>
</body>
</html:html>
