
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<fmt:bundle basename="gcom.properties.application" />
<%@page isELIgnored="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InformarVencimentoAlternativoActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<script language="JavaScript">
</script>
</head>

<body leftmargin="5" topmargin="5">
<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>

    <td width="770" height="52"  valign="top" class="topstrip"> 
      <table width="100%" height="52" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="52" colspan="2" valign="top" class="topstrip"><img src="imagens/logocompesa.gif"></td>
			
          <td valign="top" align="right"><img src="imagens/logocht.gif" width="192" height="51"></td> 
        </tr>
      </table>
   </td>
  </tr>
  <tr>

  <td border=0>
  <table border="0" class="layerMenu">
  <tr>
    <td>
	<link rel="StyleSheet" href="dtree.css" type="text/css" /><script type="text/javascript" src="dtree.js"></script>

<div class="dtree">
<script><!--
/* d = new dTree('d');
d.add(0,-1,'Menu GSAN');
d.add(1,0,'GSAN','#');
d.add(2,1,'Acesso','#');
d.add(3,2,'Controlar Funcionalidade Opera��o','exibirControlarGrupoUsuarioAction.do');
d.add(4,2,'Inserir Grupo','exibirInserirGrupoAction.do');
d.add(5,2,'Manter Grupo ','exibirManterGrupoAction.do');
d.add(6,1,'Amostra','#');
d.add(7,6,'Inserir Caracter�stica da Amostra','exibirInserirCaracteristicaAction.do');
d.add(8,6,'Manter Caracteristica','exibirManterCaracteristicaAction.do');
d.add(9,1,'Analise','#');
d.add(10,9,'Imprimir Relat�rios de Analise','exibirFiltroRelatorioAnaliseHidrobiologicaAguaLaboratorioAction.do');
d.add(11,1,'An�lise','#');
d.add(12,11,'Definir An�lise','exibirDefinirAnaliseAction.do');
d.add(13,11,'Inserir �tem F�sico Qu�mico','exibirInserirItemFisicoQuimicoAction.do');
d.add(14,11,'Manter Item F�sico Qu�mico','exibirManterItemFisicoQuimicoAction.do');
d.add(15,1,'Empresa','#');
d.add(16,15,'Inserir Distrito','exibirInserirDistritoAction.do');
d.add(17,15,'Inserir Unidade Operacional','exibirInserirUnidadeOperacionalAction.do');
d.add(18,15,'Manter Empresa','exibirManterEmpresaAction.do');
d.add(19,15,'Manter Unidade Operacional','exibirManterUnidadeOperacionalAction.do');
d.add(20,15,'Remover Unidade Operacional Origem','exibirEscolherUnidadeOperacionalOrigemRemocaoAction.do');
d.add(21,1,'Funcion�rio','#');
d.add(22,21,'Inserir Equipe','exibirInserirEquipeAction.do');
d.add(23,21,'Inserir Funcion�rio','exibirInserirFuncionarioAction.do');
d.add(24,21,'Manter Equipe','exibirManterEquipeAction.do');
d.add(25,21,'Manter Funcion�rio','exibirManterFuncionarioAction.do');
d.add(26,1,'Laborat�rio','#');
d.add(27,26,'Inserir Aumento para o Microsc�pio','exibirInserirMicroscopioAumentoAction.do');
d.add(28,26,'Inserir Fator de Corre��o','exibirInserirFatorCorrecaoAction.do');
d.add(29,26,'Inserir Microsc�pio','exibirInserirMicroscopioAction.do');
d.add(30,26,'Manter Aumento para o Microsc�pio','exibirManterMicroscopioAumentoAction.do');
d.add(31,26,'Manter Fator Corre��o','exibirManterFatorCorrecaoAction.do');
d.add(32,26,'Manter Microsc�pio','exibirManterMicroscopioAction.do');
d.add(33,1,'Localidade','#');
d.add(34,33,'Inserir Endere�o Desvinculado','exibirInserirEnderecoDesvinculadoAction.do');
d.add(35,33,'Manter Endere�o Desvinculado ','exibirManterEnderecoDesvinculadoAction.do');
d.add(36,33,'Manter V�nculo Cliente Im�vel ','exibirManterVinculoClienteImovelAction.do');
d.add(37,33,'Vincular Cliente Im�vel','exibirInserirVincularClienteImovelAction.do');
d.add(38,1,'Servi�o','#');
d.add(39,38,'Abrir Servi�o','exibirAbrirServicoAction.do');
d.add(40,38,'Inserir Atividade por Servi�o','exibirInserirServicoAtividadeAction.do');
d.add(41,38,'Inserir Especifica��o de Tipo de Solicita��o','exibirInserirEspecificacaoTipoSolicitacaoAction.do');
d.add(42,38,'Inserir Situa��o Registro Atendimento','exibirInserirSituacaoRegistroAtendimentoAction.do');
d.add(43,38,'Inserir Unidade Executora','exibirInserirUnidadeExecutoraAction.do');
d.add(44,38,'Manter Atividades por Servi�o','exibirManterServicoAtividadeAction.do');
d.add(45,38,'Manter Especifica��o de Tipo de Solicita��o','exibirManterEspecificacaoTipoSolicitacaoAction.do');
d.add(46,38,'Manter Servi�o','exibirManterServicoAction.do');
d.add(47,38,'Manter Situa��o Registro Atendimento','exibirManterSituacaoRegistroAtendimentoAction.do');
d.add(48,38,'Manter Unidade Executora','exibirManterUnidadeExecutoraAction.do');
d.add(49,38,'Registrar Funcion�rio em Atividades por Servi�o','exibirRegistrarServicoAtividadeFuncionarioAction.do');
d.draw(); */

d = new dTree('d');
d.add(0,-1,'Menu GSAN');
d.add(1,0,'GSAN','#');
d.add(2,1,'Cadastro','#');
d.add(3,2,'Inserir Cliente','processoinserircliente1.htm');
d.add(4,2,'Inserir Im�vel','processoinseririmovel1.htm');
d.add(5,1,'Micromedi��o','#');
d.add(6,5,'Em Desenvolvimento','#');
d.add(7,1,'Arrecada��o','#');
d.add(8,7,'Em Desenvolvimento','#');
d.add(9,1,'Cobran�a','#');
d.add(10,9,'Em Desenvolvimento','#');
d.add(11,1,'Seguran�a','#');
d.add(12,11,'Em Desenvolvimento','#');
d.add(13,1,'Gerencial','#');
d.add(14,13,'Em Desenvolvimento','#');
d.add(14,1,'Atendimento P�blico','#');
d.add(15,14,'Em Desenvolvimento','#');
d.add(16,1,'Operacional','#');
d.add(17,16,'Em Desenvolvimento','#');
d.draw();
//--></script>
</div>
    </td>

  </tr>
</table>
 
 
 
<table width="770" cellspacing="0" cellpadding="0">
  <tr  >
     <td  height="4" colspan="3"></td>
  </tr>
  
  <tr> 
    <td> <table width="100%" cellpadding="0"  cellspacing="0" class="footer">

                    <tr> 
                      <td height="20"  align="left">&nbsp;Compesa</td>
                      <td align="right">Powered by GPD/DDM&nbsp; </td>
        </tr>
      </table></td>
  </tr>
</table>


		</td>
		</tr>
	  </table>
 
   
  
  </td>
  </tr>
  
</table>


</body>
</html>
 