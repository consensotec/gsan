<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
	<head>
		<title>SAAE | Servi&ccedil;os</title>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-1.4.2.min.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.blockUI.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<link rel="stylesheet" href="<bean:message key="caminho.portalsaae.css"/>style.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portalsaae.css"/>internal.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portalsaae.css"/>jquery.theme.css" type="text/css">
		
		<script type="text/javascript">
			$(document).ready(function(){
				$('.info-serv').hide();
				$('#lista-servicos li, #lista-informacoes li').hover(function(){
					$('.ativo').removeClass('ativo');
					$(this).find('.info-serv').fadeIn(50);
					$(this).find('a').addClass('ativo').css('color', '#FFF');
				}, function(){
					$('.ativo').removeClass('ativo').css('color', '#008FD6');;
					$(this).find('.info-serv').fadeOut(50);
				});
			
				$('.confirm').click(function(){
					$.unblockUI();
				});
			});

			function esferaPoderNaoPermiteGerarCertidaoNegativa() {

					$.blockUI({
						message : '<h3 style="text-align:left; padding-top:10px; padding-bottom: 10px;">A esfera de poder associado ao cliente respons�vel n�o permite certid�o negativa para o im�vel.</h3>'
								 +'<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>',
						theme : true,
						title : 'Aviso'
					});

					$('.confirm').live('click', function(){
						$.unblockUI();
						inputComFocus.focus()
					});

			}
		</script>
		<logic:present name="imovelSemDebito" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#imovelSemDebitos'),
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		
		<logic:present name="debitoParceladoMesCorrente" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#debitoParceladoMesCorrente'),
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		
		<logic:present name="imovelSemQuitacaoAnual" scope="request">
				<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#imovelSemQuitacaoAnual'),
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		
		<!-- Valida��es Inicias de efetuar parcelamento de d�bitos -->
		<logic:present name="imovelParcelamentoAtivo" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#imovelParcelamentoAtivo'),
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		
		<logic:present name="imovelSemDebitos" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#imovelSemDebitos'),
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		
		<logic:present name="imovelNaoPossuiPerfilParcelamento" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#imovelNaoPossuiPerfilParcelamento'),
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		
		<logic:present name="quantidadeReparcelNaoPermiteParcel" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#quantidadeReparcelNaoPermiteParcel'),
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		
		<logic:present name="naoExistePerfilSituacaoImovel" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#naoExistePerfilSituacaoImovel'),
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		
		<logic:present name="imovelSituacaoCobranca" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#imovelSituacaoCobranca'),
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		<logic:present name="numeroMesesMinimoVencimentoAlternativoSuperiorPermitido" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#numeroMesesMinimoVencimentoAlternativoSuperiorPermitido'),
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		<logic:present name="imovelSemRA" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#imovelSemRA'),
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		<logic:present name="imovelSemPagamento" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#imovelSemPagamento'),
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		<logic:present name="imovelSemConsumos" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#imovelSemConsumos'),
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		<!-- Fim das valida��es Inicias de efetuar parcelamento de d�bitos -->
	</head>
	
	<body>
		<div id="container"> 
		   <html:form action="/exibirServicosPortalSaaeAction.do?method=servicos" method="post" 
				name="ExibirServicosPortalSaaeActionForm" type="gcom.gui.portal.saae.ExibirServicosPortalSaaeActionForm" >
	    	<%@ include file="/jsp/portal/saae/cabecalho.jsp"%>
	        
	        <!-- Content - Start -->
	         <div id="content">
	         <%@ include file="/jsp/portal/saae/cabecalhoImovel.jsp"%>	            
	            <ul id="lista-informacoes">	  
	            
	            	<li id="serv-1">
	            		<a href="emitirSegundaViaContaPortalSaaeAction.do">
	            			<span>2� Via da conta</span>
            			</a>
	                	<div class="info-serv" style="text-align:justify;">
	                        <p>Este acesso permite solicitar a segunda via da sua conta, que poder� ser paga nos agentes recebedores do SAAE. Para acessar este servi�o, clique no assunto &quot;2� Via da conta&quot; e selecione a conta que desejar, ou entre em contato com o call center pelo n�mero ***0800 280 9520*** ou pelo link "Fale conosco" do nosso site.</p>
	                        <p>O SAAE disponibiliza mais este servi�o sem custo adicional.</p>
	                        <span id="bottom">&nbsp;</span>
	                        <img src="imagens/portal/saae/general/seta-info-servicos.gif" alt="Seta" />
	                    </div>
	                </li>

	               	<logic:notPresent name="imovelCadastroVirtual" scope="session">
		                <li id="serv-4">
		                	<a href="exibirInserirSolicitacaoServicosPortalSaaeAction.do?init=1">
		                		<span>Outros servi�os</span>
	                		</a>
		                	<div class="info-serv">
		                        <p></p>
		                        <p>Por este acesso, ser� poss�vel solicitar alguns servi�os. Fa�a sua op��o.</p>
		                        <p></p>
		                        <span id="bottom">&nbsp;</span>
		                        <img src="imagens/portal/saae/general/seta-info-servicos.gif" alt="Seta" />
	                    	</div>
		                </li>

		                <%/*
		                <li id="serv-6">
		                	<a href="exibirEfetuarParcelamentoDebitosPortalSaaeAction.do?paginaServicos=SIM">
		                		<span>Negocia��o de d�bitos</span>
	                		</a>
		                	<div class="info-serv" style="text-align:justify;">
		                        <p>Este acesso permite simular as condi��es de regulariza��o de seu d�bito � vista ou a prazo. Ao final da negocia��o, ser� gerado documento pag�vel nos agentes arrecadadores da Saae.
								</p>
		                        <span id="bottom">&nbsp;</span>
		                        <img src="imagens/portal/general/seta-info-servicos.gif" alt="Seta" />
		                    </div>
		                </li>
		                */%>
	                </logic:notPresent> 
	                
	            </ul>
	        </div>
	        <!-- Content - End -->
	         <span>&nbsp;</span>
	       <%@ include file="/jsp/portal/saae/rodape.jsp"%>
	       </html:form>
	    </div>
	    
		<!-- Avisos -->
		<logic:present name="numeroMesesMinimoVencimentoAlternativoSuperiorPermitido" scope="request">
			<div id="numeroMesesMinimoVencimentoAlternativoSuperiorPermitido" style="display:none;cursor:default;">
				<img alt="Aviso" src="imagens/portal/saae/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;"> Im�vel com vencimento alterado h� menos de <bean:write name="numeroMesesMinimo" scope="request" /> meses.</h3> 
				<p>
		        	Em caso de d�vidas, procure uma loja de atendimento mais pr�xima, ou entre em contato com o call center pelo 0800 280 9520.
		        </p>
				<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		<logic:present name="imovelSemDebito" scope="request">
			<div id="imovelSemDebitos" style="display:none;cursor:default;">
				<img alt="Aviso" src="imagens/portal/saae/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;">Im�vel sem d�bitos.</h3> 
				<p>
		        	Em caso de d�vidas, procure uma loja de atendimento mais pr�xima, ou entre em contato com o call center pelo 0800 280 9520.
		        </p>
				<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="imovelSemQuitacaoAnual" scope="request">
			<div id="imovelSemQuitacaoAnual" style="display:none;cursor:default;">
				<img alt="Aviso" src="imagens/portal/saae/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="text-align:justify; padding-top:10px; padding-bottom: 10px;">Im�vel sem declara��o anual de quita��o de d�bitos</h3>
		        <p>
		        	Em caso de d�vidas, procure uma loja de atendimento mais pr�xima, ou entre em contato com o call center pelo 0800 280 9520
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<!-- Valida��es Inicias de efetuar parcelamento de d�bitos -->
		<logic:present name="imovelParcelamentoAtivo" scope="request">
			<div id="imovelParcelamentoAtivo" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/saae/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="padding-top:10px; padding-bottom: 10px;">Im�vel j� possui um parcelamento n�o quitado/cobrado. </h3>
		        <p>
		        	Em caso de d�vidas, procure uma loja de atendimento mais pr�xima, ou entre em contato com o call center pelo 0800 280 9520.
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="imovelSemDebitos" scope="request">
			<div id="imovelSemDebitos" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/saae/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="padding-top:10px; padding-bottom: 10px;">O Im�vel informado n�o possui d�bitos. </h3>
		        <p>
		        	Em caso de d�vidas, procure uma loja de atendimento mais pr�xima, ou entre em contato com o call center pelo 0800 280 9520.
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="debitoParceladoMesCorrente" scope="request">
			<div id="debitoParceladoMesCorrente" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/saae/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="padding-top:10px; padding-bottom: 10px;">O d�bito deste im�vel j� foi parcelado no m�s de faturamento corrente. </h3>
		        <p>
		        	Em caso de d�vidas, procure uma loja de atendimento mais pr�xima, ou entre em contato com o call center pelo 0800 280 9520.
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="imovelNaoPossuiPerfilParcelamento" scope="request">
			<div id="imovelNaoPossuiPerfilParcelamento" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/saae/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="padding-top:10px; padding-bottom: 10px;">N�o existe perfil de parcelamento correspondente � situa��o do im�vel. </h3>
		        <p>
		        	Em caso de d�vidas, procure uma loja de atendimento mais pr�xima, ou entre em contato com o call center pelo 0800 280 9520.
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="quantidadeReparcelNaoPermiteParcel" scope="request">
			<div id="quantidadeReparcelNaoPermiteParcel" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/saae/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="padding-top:10px; padding-bottom: 10px;">Quantidade de reparcelamento do im�vel n�o permite um novo parcelamento. </h3>
		        <p>
		        	Em caso de d�vidas, procure uma loja de atendimento mais pr�xima, ou entre em contato com o call center pelo 0800 280 9520.
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="naoExistePerfilSituacaoImovel" scope="request">
			<div id="naoExistePerfilSituacaoImovel" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/saae/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="padding-top:10px; padding-bottom: 10px;">N�o existe perfil de parcelamento correspondente � situa��o do im�vel.</h3>
		        <p>
		        	Em caso de d�vidas, procure uma loja de atendimento mais pr�xima, ou entre em contato com o call center pelo 0800 280 9520.
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="imovelSituacaoCobranca" scope="request">
			<div id="imovelSituacaoCobranca" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/saae/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="padding-top:10px; padding-bottom: 10px;">Im�vel com situa��o de cobran�a, n�o � possivel fazer o parcelamento de d�bitos.</h3>
		        <p>
		        	Em caso de d�vidas, procure uma loja de atendimento mais pr�xima, ou entre em contato com o call center pelo 0800 280 9520.
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="imovelSemRA" scope="request">
			<div id="imovelSemRA" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/saae/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="padding-top:10px; padding-bottom: 10px;">O Im�vel informado n�o possui Registros de Atendimento para serem acompanhados. </h3>
		        <p>
		        	Em caso de d�vidas, procure uma loja de atendimento mais pr�xima, ou entre em contato com o call center pelo 0800 280 9520.
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="imovelSemPagamento" scope="request">
			<div id="imovelSemPagamento" style="display:none;cursor:default;">
				<img alt="Aviso" src="imagens/portal/saae/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;">Im�vel n�o possui pagamentos efetuados.</h3> 
				<p>
		        	Em caso de d�vidas, procure uma loja de atendimento mais pr�xima, ou entre em contato com o call center pelo 0800 280 9520.
		        </p>
				<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="imovelSemConsumos" scope="request">
			<div id="imovelSemConsumos" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/saae/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="padding-top:10px; padding-bottom: 10px;">O Im�vel informado n�o possui Hist�rico de Consumo. </h3>
		        <p>
		        	Em caso de d�vidas, procure uma loja de atendimento mais pr�xima, ou entre em contato com o call center pelo 0800 280 9520.
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<!-- Fim das valida��es Inicias de efetuar parcelamento de d�bitos -->
	</body>
</html:html>