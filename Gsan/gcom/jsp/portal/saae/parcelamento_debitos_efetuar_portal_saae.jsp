

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.cobranca.CobrancaForma" isELIgnored="false"%>
<%@ page import="gcom.cobranca.bean.ContaValoresHelper" isELIgnored="false"%>
<%@ page import="gcom.cobranca.bean.GuiaPagamentoValoresHelper" isELIgnored="false"%>
<%@ page import="gcom.util.ConstantesSistema" isELIgnored="false"%>
<%@ page import="java.util.Collection" isELIgnored="false"%>
<%@ page import="gcom.cobranca.bean.OpcoesParcelamentoHelper" isELIgnored="false"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
        <head>
                <title>Saae | Serviços</title>
                <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
                <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
                <script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-1.4.2.min.js"></script>
                <script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
                <link rel="stylesheet" href="<bean:message key="caminho.portalsaae.css"/>style.css" type="text/css">
                <link rel="stylesheet" href="<bean:message key="caminho.portalsaae.css"/>internal2.css" type="text/css">
                <link rel="stylesheet" href="<bean:message key="caminho.portalsaae.css"/>jquery.theme.css" type="text/css">
                <script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.blockUI.js"></script>
                <script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

                <style type="text/css">
                        #email {
                                text-transform: lowercase;
                        }
                </style>

                <!-- [if lt IE 9]>
                        <style type="text/css">
                                #form-matricula input.campo-text {height:28px!important; padding-top:5px!important}
                        </style>
                <![endif]-->

                <logic:present name="exibirDetalhesDebito" scope="request">
                        <script type="text/javascript">
                                $(document).ready(function(){
                                        $('table').each(function(){
                                                $(this).children('tbody').children('tr:last').addClass('last-tr');
                                        });
                                });
                        </script>
                </logic:present>

                <logic:present name="clienteInvalido" scope="request">
                        <script type="text/javascript">
                                $(document).ready(function(){
                                        showMessage('O seu CPF não esta vinculado a matrícula do imóvel, procure uma loja de atendimento mais próxima, ou entre em contato com o call center pelo 0800 280 9520.');
                                });
                        </script>
                </logic:present>

                <logic:present name="entradaInformadaMenorMinima" scope="request">
                        <script type="text/javascript">
                                $(document).ready(function(){
                                        showMessage('Valor da entrada deve ser maior ou igual a entrada miníma do parcelamento R$<%= request.getAttribute("entradaMinima") %>.');
                                });
                        </script>
                </logic:present>

                <logic:present name="recalcularOpcaoParcelamento" scope="request">
                        <script type="text/javascript">
                                $(document).ready(function(){
                                        showMessage('É necessário calcular quando o valor da entrada for alterado.');
                                });
                        </script>
                </logic:present>

                <logic:present name="numeroReparcelamentos" scope="request">
                        <script type="text/javascript">
                                $(document).ready(function(){
                                        showMessage('Não existe a condição por quantidade de reparcelamentos para o perfil do parcelamento.');
                                });
                        </script>
                </logic:present>

                <logic:present name="opcaoParcelamentoNaoInformada" scope="request">
                        <script type="text/javascript">
                                $(document).ready(function(){
                                        showMessage('Informe opção de parcelamento.');
                                });
                        </script>
                </logic:present>

                <logic:present name="parcelamentoJaEfetuado" scope="request">
                        <script type="text/javascript">
                                $(document).ready(function(){
                                        showMessage('Parcelamento já efetuado.');
                                });
                        </script>
                </logic:present>

                <logic:present name="mensagemReativacao" scope="request">
                        <script type="text/javascript">
                                $(document).ready(function(){
                                        showMessageReativacao('Para reativação do ramal de água, procure uma loja de atendimento mais próxima, ou entre em contato com o call center pelo 0800 280 9520.');
                                });
                        </script>
                </logic:present>

                <logic:present name="valorMinimoInvalido" scope="request">
                        <script type="text/javascript">
                                $(document).ready(function(){
                                        showMessage('Valor da entrada informado menor que o valor possível para quantidade de parcelas selecionadas.');
                                });
                        </script>
                </logic:present>

                <logic:present name="valorEntradaInvalido" scope="request">
                        <script type="text/javascript">
                                $(document).ready(function(){
                                        showMessage('Não é permitido efetuar o parcelamento com o valor da entrada, tente pagar à vista ou vá a um dos postos de atendimento da Saae.');
                                });
                        </script>
                </logic:present>

                <logic:present name="valorEntradaNaoDigitado" scope="request">
                        <script type="text/javascript">
                                $(document).ready(function(){
                                        showMessage('Digite o valor da entrada.');
                                });
                        </script>
                </logic:present>

                <logic:present name="cpfInvalido" scope="request">
                        <script type="text/javascript">
                                $(document).ready(function(){
                                        showMessage('O seu CPF não esta vinculado a matrícula do imóvel, procure uma loja de atendimento mais próxima, ou entre em contato com o call center pelo 0800 280 9520.');
                                });
                        </script>
                </logic:present>

                <logic:present name="indicadorFormaNaoSelecionado" scope="request">
                        <script type="text/javascript">
                                $(document).ready(function(){
                                        showMessage('Selecione a forma de cobrança');
                                });
                        </script>
                </logic:present>

                <script type="text/javascript">
                        function handleEnter (field, event) {
                            var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
                                return (keyCode != 13);
                                }

                        function showMessage(message){
                                $('#message h3').text(message);
                                $.blockUI({
                                        message : $('#message'),
                                        theme : true,
                                        title : 'Aviso'
                                });

                                $('.confirm').live('click', function(){
                                        $.unblockUI();
                                });
                        }

                        function showMessageReativacao(message){
                                $('#alertReativacaoRamal h3').text(message);
                                $.blockUI({
                                        message : $('#alertReativacaoRamal'),
                                        theme : true,
                                        title : 'Aviso'
                                });
                                
                                $('#botaoOkReativacao').live('click', function(){
                                        gerarDocumentosParcelamento();
                                        $.unblockUI();
                                });
                        }

                        function showMessageReativacaoAVista(message){
                                $('#alertReativacaoRamal h3').text(message);
                                $.blockUI({
                                        message : $('#alertReativacaoRamal'),
                                        theme : true,
                                        title : 'Aviso'
                                });
                                
                                $('#botaoOkReativacao').live('click', function(){
                                        gerarDocumentoExtrato();
                                        $.unblockUI();
                                });
                        }

                        function caracteresespeciais () { 
                                this.aa = new Array("matricula", "Matrícula possui caracteres especiais.", new Function ("varName", " return this[varName];"));
                            this.ac = new Array("cpfCliente", "CPF do Cliente possui caracteres especiais.", new Function ("varName", " return this[varName];"));
                        }

                        function IntegerValidations () { 
                                this.aa = new Array("matricula", "Matrícula deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
                            this.ac = new Array("cpfCliente", "CPF do Cliente deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
                        }

                        function limparForm(form) {
                                limparConfirmarCpfCliente();
                        }

                        function recalcularParcelmamento() {
                                var form = document.forms[0];
                                if(form.cpfCliente.value.length != 0) {
                                        form.action = "exibirEfetuarParcelamentoDebitosPortalSaaeAction.do?method=pesquisarCliente&mudancaIndicadorRestabelecimento=SIM";
                                        form.submit();
                                }
                        }

                        function calcularOpcaoParcelamento() {
                                var form = document.forms[0];
                                form.action = "exibirEfetuarParcelamentoDebitosPortalSaaeAction.do?calcularParcelas=SIM";
                                form.submit();
                        }

                        function efetuarParcelamento() {
                                var form = document.forms[0];
                                form.submit();
                        }

                        function gerarDocumentoExtrato() {
                                window.location.href='<html:rewrite page="/gerarRelatorioExtratoDebitoAction.do?parcelamentoPortal=SAAE&parcelamento=1&indicadorContasRevisao=1&RD="/><bean:write name="EfetuarParcelamentoDebitosPortalSaaeActionForm" property="resolucaoDiretoria"/>'
                        }

                        function gerarDocumentosParcelamento() {
                                window.location.href='gerarRelatorioDocumentosParcelamentoPortalSaaeAction.do'
                        }

                        function exibirEfetuarParcelamento() {
                                var form = document.forms[0];
                                form.action = "exibirEfetuarParcelamentoDebitosPortalSaaeAction.do?method=pesquisarCliente";
                                form.submit();
                        }

                        function bloquearCobrancaForma() {
                                var form = document.forms[0];
                                if(form.indicadorFormaCobranca.value == "3"){                           
                                   form.indicadorFormaCobranca[0].checked = true;
                                }
                        }

                        function validarEmail() {
                                var email = $('#email').val();
                                if (!email) return true;

                                var er = /^[a-zA-Z0-9][a-zA-Z0-9\._-]+@([a-zA-Z0-9\._-]+\.)[a-zA-Z-0-9]{2}/;
                                if (er.exec(email)) return true;

                                showMessage('E-mail inválido');
                                return false;
                        }

                        $(function() {
                                if('<%= request.getParameter("calcularParcelas") %>' == 'SIM') {
                                        $('[name=valorEntradaInformado]').focus();                                      
                                }

                                $('#email').blur(function() {
                                        validarEmail();
                                });

                                $('#btn-confirmar').click(function() {
                                        if (!validarEmail()) return;

                                        $.blockUI({
                                                message : $('#alertConfirmacao'),
                                                theme : true,
                                                title : 'Confirmar'
                                        });

                                        $('#botaoSim').click(function() {
                                                efetuarParcelamento();
                                                $.unblockUI();
                                        });

                                        $('#botaoNao').click(function() {
                                                $.unblockUI();
                                        });
                                        
                                });

                                $('#btn-a-vista').click(function() {
                                        $.blockUI({
                                                message : $('#alertConfirmacaoAVista'),
                                                theme : true,
                                                title : 'Confirmar'
                                        });
                                        
                                        $('#botaoSimAVista').click(function() {
                                                if(parseInt($('[name=indicadorRestabelecimento]:checked').val()) == 1) {
                                                        showMessageReativacaoAVista('Para reativação do ramal de água, procure uma loja de atendimento mais próxima, ou entre em contato com o call center pelo 0800 280 9520.');
                                                } else {
                                                        gerarDocumentoExtrato();
                                                        $.unblockUI();
                                                }
                                        });
                                        $('#botaoNaoAVista').click(function() {
                                                $.unblockUI();
                                        });
                                });
                        });
                </script>
        </head>
        <body onload="bloquearCobrancaForma();">
                <div id="container">
                <%@ include file="/jsp/portal/saae/cabecalho.jsp"%>

                        <!-- Content - Start -->
                        <div id="content">
                                <%@ include file="/jsp/portal/saae/cabecalhoImovel.jsp"%>
                                
                                <div id="parc-debito" class="serv-int">
                        <h3>Parcelamento de débitos<span>&nbsp;</span></h3>
                        <p>Endereço do imóvel: <em id="enderecoImovel">${EfetuarParcelamentoDebitosPortalSaaeActionForm.enderecoImovel}</em></p>
                                        <div style="background-color: #F5F6F6; float: left; padding: 8px; border-radius: 10px;">Para efetuar o parcelamento informe a forma de cobrança e o CPF do cliente responsável pelo débito.</div>
                        <html:form action="/efetuarParcelamentoDebitosPortalSaaeAction.do"
                                                name="EfetuarParcelamentoDebitosPortalSaaeActionForm"
                                                type="gcom.gui.portal.saae.EfetuarParcelamentoDebitosPortalSaaeAction" method="post">

                                                <fieldset>
                                                        <logic:present name="gsanPortal">
                                                                <div style="padding-bottom: 10px; vertical-align: middle;">
                                                                        <h5 style="color: #008FD6; float: none;">Forma de cobrança:</h5>
                                                                        <html:radio style="float: none" property="indicadorFormaCobranca" value="<%=CobrancaForma.COBRANCA_EM_CARTAO_CREDITO.toString()%>" styleId="cobrancaCartaoCredito" disabled="true"/>
                                                                        <label for="cobrancaCartaoCredito" style="vertical-align: top;">Cartão de Crédito</label>
                                                                </div>
                                                        </logic:present>

                                                        <logic:notPresent name="gsanPortal">
                                                                <div style="padding-bottom: 10px; vertical-align: middle;">
                                                                        <h5 style="color: #008FD6; float: none;">Forma de cobrança:</h5>
                                                                        <html:radio style="float: none" property="indicadorFormaCobranca" value="<%=CobrancaForma.COBRANCA_EM_CARTAO_CREDITO.toString()%>" styleId="cobrancaCartaoCredito"/>
                                                                        <label for="cobrancaCartaoCredito" style="vertical-align: top;">Cartão de Crédito</label>
                                                                        &nbsp;
                                                                        <html:radio style="float: none" property="indicadorFormaCobranca" value="<%=CobrancaForma.COBRANCA_EM_CONTA.toString()%>" styleId="cobrancaEmConta"/>
                                                                        <label for="cobrancaEmConta" style="vertical-align: top;">Em Conta</label>
                                                                </div>
                                                        </logic:notPresent>
                                                
                                                        <span class="cmp-text-2">
                                                                <label for="cpfCliente" style="font-weight: bold">CPF do solicitante:</label>
                                                                <html:text property="cpfCliente" size="15" maxlength="14" tabindex="1" onkeypress="return isCampoNumerico(event);"  onkeydown="return handleEnter(this, event)"/>
                                                        </span>
                                                        
                                                        <input type="button" value="" class="btn-pesquisar" onclick="javascript:exibirEfetuarParcelamento();"/>
                                                        
                                                        <logic:present name="exibirDetalhesDebito" scope="request">
                                                                <p class="nome-cliente">${EfetuarParcelamentoDebitosPortalSaaeActionForm.nomeCliente}</p>
                                                        </logic:present>
                                                        <logic:present name="reativacaoLigacaoAgua" scope="request">
                                                                <span class="cmp-text-2" style="width: 90%;">
                                                                        <label for="cpfCliente">Com reativação de ligação de água?</label>
                                                                        <div style="display: inline-block; width: 80px;">
                                                                                <html:radio property="indicadorRestabelecimento" value="1" onchange="javascript:recalcularParcelmamento()" style="width:auto;" styleId="sim"/>
                                                                                <label for="sim" style="float: left; padding-top: 6px; padding-left: 8px;">Sim</label>
                                                                        </div>
                                                                        <div style="display: inline-block; width: 80px;">
                                                                                <html:radio property="indicadorRestabelecimento" value="2" onchange="javascript:recalcularParcelmamento()" style="width:auto;" styleId="nao"/>
                                                                                <label style="float: left; padding-top: 6px; padding-left: 8px;" for="nao">Não</label>
                                                                        </div>
                                                                </span>
                                                        </logic:present>

                                                        <logic:present name="exibirDetalhesDebito" scope="request">
                                                                <c:set var="forma" value="<%= CobrancaForma.COBRANCA_EM_CARTAO_CREDITO.toString() %>" scope="page" />
                                                                <c:if test="${EfetuarParcelamentoDebitosPortalSaaeActionForm.indicadorFormaCobranca eq forma}">
                                                                        <span style="padding: 0px; clear: both;" class="cmp-text-1">
                                                                                <label for="email" style="font-weight: bold">E-mail:</label>
                                                                                <html:text property="email" maxlength="40" tabindex="2" styleId="email"/>
                                                                        </span>
                                                                </c:if>
                                                                <table summary="Tabela de contas">
                                                                        <%int cont = 0;%>
                                                                        <tr>
                                                                                <td colspan="4">
                                                                                <table summary="Tabela de contas">
                                                                                        <logic:empty name="colecaoContaValores" scope="session">
                                                                                                <thead>
                                                                                <tr>
                                                                                        <th width="205">Mês / Ano</th>
                                                                                    <th width="125">Vencimento</th>
                                                                                    <th width="117">Valor (R$)</th>
                                                                                    <th width="170">Acréscimo por impontualidade (R$)</th>
                                                                                </tr>
                                                                            </thead>
                                                                                        </logic:empty>
                                                                                        <logic:notEmpty name="colecaoContaValores" scope="session">
                                                                                                <%if (((Collection<gcom.cobranca.bean.ContaValoresHelper>) session.getAttribute("colecaoContaValores")).size() 
                                                                                                                <= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_CONTAS_DEBITO) {%>
                                                                                                <thead>
                                                                                <tr>
                                                                                        <th width="205">Mês / Ano</th>
                                                                                    <th width="125">Vencimento</th>
                                                                                    <th width="117">Valor (R$)</th>
                                                                                    <th width="170">Acréscimo por impontualidade (R$)</th>
                                                                                </tr>
                                                                            </thead>
                                                                                                
                                                                                                <tbody>
                                                                                                        <logic:iterate name="colecaoContaValores"
                                                                                                        type="ContaValoresHelper" id="contaValores">
                                                                                                        
                                                                                                                <c:if test="${contaValores.revisao eq 2}">
                                                                                                                
                                                                                                                <%cont = cont + 1;
                                                                                                                if (cont % 2 == 0) {%>
                                                                                                                <tr class="tr-2">
                                                                                                                <%} else {%>
                                                                                                                <tr class="tr-1">
                                                                                                                <%}%>
                                                                                                                        <td width="205">
                                                                                                                                <logic:notEmpty name="contaValores" property="conta">
                                                                                                                                        <logic:notEmpty name="contaValores" property="conta.dataRevisao">
                                                                                                                                   <bean:write name="contaValores" property="conta.formatarAnoMesParaMesAno"/>
                                                                                                                                </logic:notEmpty>  
                                                                                                                                        <logic:empty name="contaValores" property="conta.dataRevisao">
                                                                                                                                   <bean:write name="contaValores" property="conta.formatarAnoMesParaMesAno" />
                                                                                                                                        </logic:empty>
                                                                                                                         </logic:notEmpty>
                                                                                                                         <logic:empty name="contaValores" property="conta">
                                                                                                                              <bean:write name="contaValores" property="conta.formatarAnoMesParaMesAno" />
                                                                                                                     </logic:empty> 
                                                                                                                        </td>
                                                                                                                        <td width="125">
                                                                                                                                <bean:write name="contaValores"
                                                                                                                                        property="conta.dataVencimentoConta" formatKey="date.format" />
                                                                                                                        </td>
                                                                                                                        <td width="117"><bean:write name="contaValores" property="conta.valorTotal"
                                                                                                                                formatKey="money.format" />
                                                                                                                        </td>
                                                                                                                        <td width="170">
                                                                                                                                <bean:write name="contaValores"
                                                                                                                                        property="valorTotalContaValoresParcelamento" formatKey="money.format" />
                                                                                                                        </td>
                                                                                                                </tr>
                                                                                                                </c:if>
                                                                                                        </logic:iterate>
                                                                                                </tbody>
                                                                                                
                                                                                                <%} else {%>
                                                                                                
                                                                                                <thead>
                                                                                <tr>
                                                                                        <th width="205">Mês / Ano</th>
                                                                                    <th width="125">Vencimento</th>
                                                                                    <th width="117">Valor (R$)</th>
                                                                                    <th width="170">Acréscimo por impontualidade (R$)</th>
                                                                                </tr>
                                                                            </thead>
                                                                            
                                                                            <tbody>
                                                                                                        <tr>
                                                                                                                <td height="100" colspan="6">
                                                                                                                <div style="width: 100%; height: 100%; overflow-x: hidden;">
                                                                                                                <table width="100%" style="margin-top: 0">
                                                                                                                        
                                                                                                                        <logic:iterate name="colecaoContaValores" type="ContaValoresHelper" id="contaValores">
                                                                                                                                
                                                                                                                                <c:if test="${contaValores.revisao eq 2}">
                                                                                                                                <%cont = cont + 1;
                                                                                                                                if (cont % 2 == 0) {%>
                                                                                                                                <tr class="tr-2">
                                                                                                                                <%} else {%>
                                                                                                                                <tr class="tr-1">
                                                                                                                                <%}%>
                                                                                                                                        <td width="205">
                                                                                                                                                <logic:notEmpty name="contaValores" property="conta">
                                                                                                                                           <bean:write name="contaValores" property="conta.formatarAnoMesParaMesAno" />
                                                                                                                                         </logic:notEmpty>
                                                                                                                                         <logic:empty name="contaValores" property="conta">
                                                                                                                                      <bean:write name="contaValores"
                                                                                                                                                property="conta.formatarAnoMesParaMesAno" />
                                                                                                                                     </logic:empty> 
                                                                                                                                        </td>
                                                                                                                                        <td width="125">
                                                                                                                                                <bean:write name="contaValores" property="conta.dataVencimentoConta"
                                                                                                                                                        formatKey="date.format" />
                                                                                                                                        </td>
                                                                                                                                        <td width="117">
                                                                                                                                                <bean:write name="contaValores" property="conta.valorTotal"
                                                                                                                                                        formatKey="money.format" />
                                                                                                                                        </td>
                                                                                                                                        <td width="170">
                                                                                                                                                <bean:write name="contaValores" property="valorTotalContaValores" 
                                                                                                                                                        formatKey="money.format" />
                                                                                                                                        </td>
                                                                                                                                </tr>
                                                                                                                                </c:if>
                                                                                                                        </logic:iterate>
                                                                                                                </table>
                                                                                                                </div>
                                                                                                                </td>
                                                                                                        </tr>
                                                                                                </tbody>
                                                                                                <%}%>
                                                                                        </logic:notEmpty>
                                                                                </table>
                                                                                </td>
                                                                        </tr>
                                                                </table>
                                                                <!-- Fim do detalhamento das contas -->

                                                                <!-- Início Resumo dos débitos -->                                                            
                                                                <h4 class="resumo">Resumo do débito<span>&nbsp;</span></h4>
                                                                <ul class="lista-resumo">
                                                                        <li>
                                                                <h6>Contas:</h6>
                                                                R$<bean:write name="EfetuarParcelamentoDebitosPortalSaaeActionForm" property="valorTotalContaValores" />
                                                        </li>
                                                        <li>
                                                                <h6>Guias de pagamento:</h6>
                                                                R$<bean:write name="EfetuarParcelamentoDebitosPortalSaaeActionForm" property="valorGuiasPagamento" />
                                                        </li>
                                                        <li>
                                                                <h6>Acréscimos por impontualidade:</h6>
                                                                <logic:notEqual name="EfetuarParcelamentoDebitosPortalSaaeActionForm" 
                                                                                        property="valorAcrescimosImpontualidade" value="0,00">
                                                                                        R$<bean:write name="EfetuarParcelamentoDebitosPortalSaaeActionForm"
                                                                                                property="valorAcrescimosImpontualidade" formatKey="money.format" />
                                                                                </logic:notEqual>
                                                                                <logic:equal name="EfetuarParcelamentoDebitosPortalSaaeActionForm"
                                                                                        property="valorAcrescimosImpontualidade" value="0,00">
                                                                                        R$<bean:write name="EfetuarParcelamentoDebitosPortalSaaeActionForm"
                                                                                                property="valorAcrescimosImpontualidade" formatKey="money.format" />
                                                                                </logic:equal>
                                                        </li>
                                                        <li>
                                                                <h6>Débitos a cobrar:</h6>
                                                                <span>Serviços:</span>
                                                                <logic:notEqual name="EfetuarParcelamentoDebitosPortalSaaeActionForm" 
                                                                                        property="valorDebitoACobrarServico" value="0,00">
                                                                                        R$<bean:write name="EfetuarParcelamentoDebitosPortalSaaeActionForm" property="valorDebitoACobrarServico"  formatKey="money.format"/>
                                                                                </logic:notEqual>
                                                                                <logic:equal name="EfetuarParcelamentoDebitosPortalSaaeActionForm" 
                                                                                        property="valorDebitoACobrarServico" value="0,00">
                                                                                        R$<bean:write name="EfetuarParcelamentoDebitosPortalSaaeActionForm" property="valorDebitoACobrarServico" formatKey="money.format" />
                                                                                </logic:equal>
                                                                <br />
                                                                <span>Parcelamento:</span>
                                                                <% /*<logic:notEqual name="EfetuarParcelamentoDebitosPortalSaaeActionForm" 
                                                                                        property="valorDebitoACobrarParcelamento" value="0,00">
                                                                                        <a href="javascript:abrirPopup('exibirConsultarDebitoACobrarAction.do?imovelID=
                                                                                                <bean:define name="EfetuarParcelamentoDebitosPortalSaaeActionForm" property="matriculaImovel" id="imovel" />
                                                                                                <bean:write name="EfetuarParcelamentoDebitosPortalSaaeActionForm" property="matriculaImovel" />&parcelamentoID=', 600, 800);">
                                                                                                R$<bean:write name="EfetuarParcelamentoDebitosPortalSaaeActionForm" property="valorDebitoACobrarParcelamento"  formatKey="money.format"/>
                                                                                        </a>
                                                                                </logic:notEqual>
                                                                                <logic:equal name="EfetuarParcelamentoDebitosPortalSaaeActionForm"  
                                                                                        property="valorDebitoACobrarParcelamento" value="0,00">*/%>
                                                                                        R$<bean:write name="EfetuarParcelamentoDebitosPortalSaaeActionForm" property="valorDebitoACobrarParcelamento" formatKey="money.format" />
                                                                                <%/* </logic:equal> */%>
                                                        </li>
                                                        <li>
                                                                <h6>Créditos a realizar:</h6>
                                                                <%/*<logic:notEqual name="EfetuarParcelamentoDebitosPortalSaaeActionForm" 
                                                                                        property="valorCreditoARealizar" value="0,00">
                                                                                        <a href="javascript:abrirPopup('exibirConsultarCreditoARealizarAction.do?imovelID=<bean:define name="EfetuarParcelamentoDebitosPortalSaaeActionForm" property="matriculaImovel" id="imovel" />
                                                                                                <bean:write name="EfetuarParcelamentoDebitosPortalSaaeActionForm" property="matriculaImovel" />&parcelamentoID=', 600, 800);">
                                                                                                R$<bean:write name="EfetuarParcelamentoDebitosPortalSaaeActionForm" property="valorCreditoARealizar" formatKey="money.format"/>
                                                                                        </a>
                                                                                </logic:notEqual>
                                                                                <logic:equal name="EfetuarParcelamentoDebitosPortalSaaeActionForm" 
                                                                                        property="valorCreditoARealizar" value="0,00">*/%>
                                                                                        R$<bean:write   name="EfetuarParcelamentoDebitosPortalSaaeActionForm" property="valorCreditoARealizar" formatKey="money.format" />
                                                                                        <%/*</logic:equal>*/%>
                                                                                <br />&nbsp;
                                                        </li>
                                                        <li>
                                                                <h6>Débito total atualizado:</h6>
                                                                R$<bean:write name="EfetuarParcelamentoDebitosPortalSaaeActionForm" property="valorDebitoTotalAtualizado" />
                                                                <br />&nbsp;
                                                        </li>
                                                        <li>
                                                                
                                                        </li>
                                                                </ul>   
                                                                <!-- Fim Resumo dos débitos -->        
                                                                
                                                                <!-- Início Forma de pagamento a vista -->
                                                                
                                                                <logic:notPresent name="gsanPortal">
                                                                        <div id="pagto">
                                                                                <h5>Pagamento à vista:</h5>
                                                                                <ul class="lista-resumo">
                                                                                        <li>
                                                                                <h6>Valor atualizado:</h6>
                                                                                                R$<bean:write name="EfetuarParcelamentoDebitosPortalSaaeActionForm" property="valorDebitoTotalAtualizado"  
                                                                                                        formatKey="money.format"/>
                                                                                <br />
                                                                                        </li>
                                                                                        <li>
                                                                                <h6>Valor dos impostos:</h6>
                                                                                                R$<bean:write name="EfetuarParcelamentoDebitosPortalSaaeActionForm" property="valorTotalImpostos" 
                                                                                                        formatKey="money.format"/>
                                                                                                <br />
                                                                                        </li>
                                                                                        <li>
                                                                                <h6>Valor do desconto:</h6>
                                                                                                R$<bean:write name="EfetuarParcelamentoDebitosPortalSaaeActionForm" property="valorDescontoPagamentoAVista" 
                                                                                                        formatKey="money.format"/>
                                                                                                <br />
                                                                                        </li>
                                                                                        <li>
                                                                                <h6>Valor pagamento à vista:</h6>
                                                                                                R$<bean:write name="EfetuarParcelamentoDebitosPortalSaaeActionForm" property="valorPagamentoAVista" 
                                                                                                        formatKey="money.format"/>
                                                                                        </li>
                                                                                </ul>
                                                                <table>
                                                                        <tr>
                                                                                <td width="20"><input type="button" value="" class="btn-confirmar" id="btn-a-vista"
                                                                                        style="margin: 2px 10px 0 0px;"/></td>
                                                                                <td style="text-align: left;"><em style="padding:28px 0px 0px 0px; clear: none; text-align: left;">Gerar Extrato Pagamento à Vista</em></td>
                                                                        </tr>
                                                                </table>
                                                                        </div>
                                                                </logic:notPresent>
                                                                <!-- Fim Forma de pagamento a vista -->
                                                                
                                                                
                                                                
                                                                <!-- Início Forma de pagamento parcelado -->
                                                                <div id="pagto">
                                                                        <h5>Pagamento parcelado</h5>
                                                        <span class="cmp-text-2" style="width: 190px; font-weight: 700">
                                                    <label for="vlr-entrada" style="color: #008FD6; font-size: 12px;">Valor total a ser parcelado:</label> 
                                                    R$<bean:write name="EfetuarParcelamentoDebitosPortalSaaeActionForm" property="valorDesconto"/>
                                                    <label for="vlr-entrada" style="color: #008FD6; font-size: 12px;">Valor de entrada (R$):</label>
                                                    <html:text property="valorEntradaInformado" size="15" maxlength="14" tabindex="3"
                                                        onkeyup="formataValorMonetario(this,20)" 
                                                        onkeypress="return isCampoNumerico(event);" />
                                                </span>
                                            <input type="button" value="" class="btn-calcular" tabindex="4" style="margin-top: 63px" 
                                                                                onclick="javascript:calcularOpcaoParcelamento()"/>
                                                                                
                                                                        <%int count = 0;%>
                                                                        <%int cor = 0;%>        
                                                                        <table summary="Tabela de contas">
                                                            <logic:notEmpty name="colecaoOpcoesParcelamento" scope="session">
                                                                <%if (((Collection<OpcoesParcelamentoHelper>) session.getAttribute("colecaoOpcoesParcelamento")).size() <= ConstantesSistema.NUMERO_MAXIMO_REGISTROS_OPCAO_PARCELAMENTO) {%>
                                                                <thead>
                                                                        <tr>
                                                                                <th width="86">Parcelas</th>
                                                                            <th width="115">Valor da entrada(R$)</th>
                                                                            <th width="110">Valor da parcela(R$)</th>
                                                                            <th width="">Taxa de juros(%)</th>
                                                                        </tr>
                                                                    </thead>
                                                                    <logic:iterate name="colecaoOpcoesParcelamento" type="OpcoesParcelamentoHelper" id="opcoesParcelamento">
                                                                    <%count = count + 1;
                                                                                        if (count % 2 == 0) {%>
                                                                                        <tr class="tr-3">
                                                                                        <%} else {%>
                                                                                        <tr class="tr-4">
                                                                                        <%}%>
                                                                                                <td class="first-td">
                                                                                                        <% if ((((OpcoesParcelamentoHelper)opcoesParcelamento).getIndicadorQuantidadeParcelas()) != null 
                                                                                                                        && ((OpcoesParcelamentoHelper)opcoesParcelamento).getIndicadorQuantidadeParcelas()
                                                                                                                                .equals(((OpcoesParcelamentoHelper)opcoesParcelamento).getQuantidadePrestacao().toString())){ %>
                                                                                                                <input type="radio" name="indicadorQuantidadeParcelas" value="${opcoesParcelamento.quantidadePrestacao}" checked="true"/>
                                                                                                        <% } else { %>
                                                                                                                <input type="radio" name="indicadorQuantidadeParcelas" value="${opcoesParcelamento.quantidadePrestacao}"/>
                                                                                                        <% } %>
                                                                                                        <bean:write name="opcoesParcelamento" property="quantidadePrestacao"/>
                                                                                                </td>
                                                                                                <td>
                                                                                                        <bean:write name="opcoesParcelamento" property="valorEntradaMinima" formatKey="money.format"/>
                                                                                                </td>
                                                                                                <td>
                                                                                                        <bean:write name="opcoesParcelamento" property="valorPrestacao" formatKey="money.format"/>      
                                                                                                </td>
                                                                                                <td>
                                                                                                        <bean:write name="opcoesParcelamento" property="taxaJuros" formatKey="money.format"/>   
                                                                                                </td>
                                                                    </logic:iterate>
                                                                    <% }else{ %>
                                                                    <thead>
                                                                        <tr>
                                                                                <th width="86">Parcelas</th>
                                                                            <th width="115">Valor da entrada(R$)</th>
                                                                            <th width="110">Valor da parcela(R$)</th>
                                                                            <th width="">Taxa de juros(%)</th>
                                                                        </tr>
                                                                    </thead>
                                                                                        
                                                                                        <tbody>
                                                                                                <tr>
                                                                                                        <td height="190" colspan="6">
                                                                                                                <div style="width: 100%; height: 100%; overflow-x: hidden;">
                                                                                                                <table width="100%" style="margin-top: 0">
                                                                                                                                
                                                                                                                <logic:iterate name="colecaoOpcoesParcelamento" type="OpcoesParcelamentoHelper" id="opcoesParcelamento">
                                                                                                                <%count = count + 1;
                                                                                                                if (count % 2 == 0) {%>
                                                                                                                        <tr class="tr-3">
                                                                                                                <%} else {%>
                                                                                                                        <tr class="tr-4">
                                                                                                                <%}%>
                                                                                                                        
                                                                                                                                <logic:equal name="opcoesParcelamento" property="indicadorValorPrestacaoMaiorValorLimite" value="2">
                                                                                                                                        <td width="86" class="first-td">
                                                                                                                                                <% if ((((OpcoesParcelamentoHelper)opcoesParcelamento).getIndicadorQuantidadeParcelas()) != null 
                                                                                                                                                        && ((OpcoesParcelamentoHelper)opcoesParcelamento).getIndicadorQuantidadeParcelas()
                                                                                                                                                                .equals(((OpcoesParcelamentoHelper)opcoesParcelamento).getQuantidadePrestacao())){ %>
                                                                                                                                                        <input type="radio" name="indicadorQuantidadeParcelas" 
                                                                                                                                                                value="${opcoesParcelamento.quantidadePrestacao}" checked="true"/>
                                                                                                                                                <% } else { %>
                                                                                                                                                        <input type="radio" name="indicadorQuantidadeParcelas" 
                                                                                                                                                                value="${opcoesParcelamento.quantidadePrestacao}"/>
                                                                                                                                                <% } %>
                                                                                                                                                <bean:write name="opcoesParcelamento" property="quantidadePrestacao"/>
                                                                                                                                        </td>
                                                                                                                                        <td width="115">
                                                                                                                                                <bean:write name="opcoesParcelamento" property="valorEntradaMinima" formatKey="money.format"/>
                                                                                                                                        </td>
                                                                                                                                        <td width="110">
                                                                                                                                                <bean:write name="opcoesParcelamento" property="valorPrestacao" formatKey="money.format"/>      
                                                                                                                                        </td>
                                                                                                                                        <td width="">
                                                                                                                                                <bean:write name="opcoesParcelamento" property="taxaJuros" formatKey="money.format"/>   
                                                                                                                                        </td>
                                                                                                                                </logic:equal>
                                                                                                                                
                                                                                                                                
                                                                                                                                <logic:equal name="opcoesParcelamento" property="indicadorValorPrestacaoMaiorValorLimite" value="1">
                                                                                                                                        <%cor = 1;%>            
                                                                                                                                        <td width="86" class="first-td">
                                                                                                                                                <% if ((((OpcoesParcelamentoHelper)opcoesParcelamento).getIndicadorQuantidadeParcelas()) != null 
                                                                                                                                                        && ((OpcoesParcelamentoHelper)opcoesParcelamento).getIndicadorQuantidadeParcelas()
                                                                                                                                                                .equals(((OpcoesParcelamentoHelper)opcoesParcelamento).getQuantidadePrestacao())){ %>
                                                                                                                                                        <input type="radio" name="indicadorQuantidadeParcelas" 
                                                                                                                                                                value="${opcoesParcelamento.quantidadePrestacao}" checked="true"/>
                                                                                                                                                <% } else { %>
                                                                                                                                                        <input type="radio" name="indicadorQuantidadeParcelas" 
                                                                                                                                                                value="${opcoesParcelamento.quantidadePrestacao}"/>
                                                                                                                                                <% } %>
                                                                                                                                                        <bean:write name="opcoesParcelamento" property="quantidadePrestacao"/>
                                                                                                                                        </td>
                                                                                                                                        <td width="115">
                                                                                                                                                <bean:write name="opcoesParcelamento" property="valorEntradaMinima" formatKey="money.format"/>
                                                                                                                                        </td>
                                                                                                                                        <td width="110">
                                                                                                                                                <bean:write name="opcoesParcelamento" property="valorPrestacao" formatKey="money.format"/>      
                                                                                                                                        </td>
                                                                                                                                        <td width="">
                                                                                                                                                <bean:write name="opcoesParcelamento" property="taxaJuros" formatKey="money.format"/>   
                                                                                                                                        </td>
                                                                                                                                </logic:equal>
                                                                                                                        </tr>
                                                                                                                </logic:iterate>
                                                                                                        </table>
                                                                                                        </div>
                                                                                                </td>
                                                                                        </tr>
                                                                                </tbody>
                                                                                        <% } %>
                                                            </logic:notEmpty>
                                                        </table>
                                                        <input type="button" value="" class="btn-confirmar" id="btn-confirmar"/>
                                                                        <!-- Fim da forma de pagamento parcelado -->    
                                                                </div>  
                                                        </logic:present>
                            </fieldset>
                                        </html:form>
                    </div>
                </div>
                
                        <%@ include file="/jsp/portal/saae/rodape.jsp"%>
                </div><!-- Content - End -->
                
                <div id="message" style="display:none; cursor: default;"> 
                <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;"></h3> 
                        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
                </div>
                
                <div id="alertConfirmacao" style="display:none; cursor: default;"> 
                <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;">Deseja confirmar parcelamento dos débitos?</h3> 
                        <a href="javascript:void(0);" id="botaoSim" class="ui-corner-all button">Sim</a>&nbsp;
                        <a href="javascript:void(0);" id="botaoNao" class="ui-corner-all button">Não</a>
                </div>
                
                <div id="alertConfirmacaoAVista" style="display:none; cursor: default;"> 
                <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;">Deseja gerar extrato para pagamento à vista?</h3> 
                        <a href="javascript:void(0);" id="botaoSimAVista" class="ui-corner-all button">Sim</a>&nbsp;
                        <a href="javascript:void(0);" id="botaoNaoAVista" class="ui-corner-all button">Não</a>
                </div>
                
                <div id="alertReativacaoRamal" style="display:none; cursor: default;"> 
                <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;"></h3> 
                        <a href="javascript:void(0);" id="botaoOkReativacao" class="ui-corner-all button">OK</a>
                </div>
        </body>
</html:html>
