<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
	<head>
		<title>CAER | Servi&ccedil;os</title>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-1.4.2.min.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.blockUI.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<link rel="stylesheet" href="<bean:message key="caminho.portalcaer.css"/>style.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portalcaer.css"/>internal.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portalcaer.css"/>jquery.theme.css" type="text/css">
		
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
						message : '<h3 style="text-align:left; padding-top:10px; padding-bottom: 10px;">A esfera de poder associado ao cliente responsável não permite certidão negativa para o imóvel.</h3>'
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
		
		<!-- Validações Inicias de efetuar parcelamento de débitos -->
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
		<!-- Fim das validações Inicias de efetuar parcelamento de débitos -->
	</head>
	
	<body>
		<div id="container"> 
		   <html:form action="/exibirServicosPortalCaerAction.do?method=servicos" method="post" 
				name="ExibirServicosPortalCaerActionForm" type="gsan.gui.portal.caer.ExibirServicosPortalCaerActionForm" >
	    	<%@ include file="/jsp/portal/caer/cabecalho.jsp"%>
	        
	        <!-- Content - Start -->
	         <div id="content">
	         <%@ include file="/jsp/portal/caer/cabecalhoImovel.jsp"%>
	            
	            <ul id="lista-informacoes">
	                <li id="serv-1">
	            		<a href="emitirSegundaViaContaPortalCaerAction.do">
	            			<span>2ª Via da conta</span>
            			</a>
	                	<div class="info-serv" style="text-align:justify;">
	                        <p>Este acesso permite solicitar a segunda via da sua conta, que poderá ser paga nos agentes recebedores da CAER. Para acessar este serviço, clique no assunto &quot;2ª Via da conta&quot; e selecione a conta que desejar, ou entre em contato com o call center pelo número 0800 280 9520 ou pelo link "Fale conosco" do nosso site.</p>
	                        <p>A CAER disponibiliza mais este serviço sem custo adicional.</p>
	                        <span id="bottom">&nbsp;</span>
	                        <img src="imagens/portal/caer/general/seta-info-servicos.gif" alt="Seta" />
	                    </div>
	                </li>
	                <li id="serv-2">
	                	<a href="exibirServicosPortalCaerAction.do?method=declaracaoAnual">
	                		<span style="font-size:14px;">Declaração anual de quitação de débito</span>
                		</a>
	                	<div class="info-serv" style="text-align:justify;">
	                        <p>Conforme determina o artigo 3º da lei federal 12.007 de 2009 a CAER disponibiliza para você a declaração de quitação anual de débitos. 
	                        Lembramos que para este acesso o cliente deverá estar em dia com suas contas referentes ao ano da solicitação da Declaração.</p>
	                        <p>Para acessar este serviço, clique no assunto declaração anual de quitação de débito ou dirija-se a uma loja de atendimento.</p>
	                        <span id="bottom">&nbsp;</span>
	                        <img src="imagens/portal/caer/general/seta-info-servicos.gif" alt="Seta" />
	                    </div>
	                </li>
	                <li id="serv-3">
	                	<a href="exibirInserirCadastroEmailClientePortalCaerAction.do?ok=sim">
	                		<span>Recebimento de fatura por e-mail</span>
                		</a>
	                	<div class="info-serv" style="text-align:justify;">
	                        <p>A CAER disponibiliza para você a facilidade de receber suas faturas em seu e-mail, para acessar este serviço, 
	                        clique no assunto recebimento de fatura por e-mail faça seu cadastro e receba suas contas sem sair de casa, ou dirija-se a uma
							loja de atendimento.</p>
	                        <span id="bottom">&nbsp;</span>
	                        <img src="imagens/portal/caer/general/seta-info-servicos.gif" alt="Seta" />
	                    </div>
	                </li>
	                <li id="serv-4">
	                	<a href="exibirInserirSolicitacaoServicosPortalCaerAction.do?init=1">
	                		<span>Outros serviços</span>
                		</a>
	                	<div class="info-serv">
	                        <p></p>
	                        <p>Por este acesso, será possível solicitar alguns serviços. Faça sua opção.</p>
	                        <p></p>
	                        <span id="bottom">&nbsp;</span>
	                        <img src="imagens/portal/caer/general/seta-info-servicos.gif" alt="Seta" />
                    	</div>
	                </li>
	                <li id="serv-5">
	            		<a href="exibirConsultarImovelPagamentosPortalCaerAction.do">
							<span>Consultar pagamento </span>
						</a>
						<div class="info-serv" style="text-align:justify;">
	                        <p></p>
	                        <p>Permite consultar o hist&oacute;rico dos pagamentos efetuados.</p>
	                        <p></p>
	                        <span id="bottom">&nbsp;</span>
	                        <img src="imagens/portal/caer/general/seta-info-servicos.gif" alt="Seta" />
	                    </div>
	                </li>
	                <li id="serv-6">
	                	<a href="exibirAcompanhamentoRAPortalCaerAction.do">
		                	<span >Acompanhar Registro Atendimento</span>
	                	</a>
	                	<div class="info-serv" style="text-align:justify;">
	                    	<p></p>
	                        <p>Por este acesso, é possível visualizar o acompanhamento dos Registros de Atendimento para o seu imóvel.</p>
							<p></p>
	                        <span id="bottom">&nbsp;</span>
	                        <img src="imagens/portal/caer/general/seta-info-servicos.gif" alt="Seta" />
		                </div>
	                </li>
	               	<logic:notPresent name="imovelCadastroVirtual" scope="session">
		                <li id="serv-6">
		                	<a href="exibirEfetuarParcelamentoDebitosPortalCaerAction.do?paginaServicos=SIM">
		                		<span>Negociação de débitos</span>
	                		</a>
		                	<div class="info-serv" style="text-align:justify;">
		                        <p>Este acesso permite simular as condições de regularização de seu débito à vista ou a prazo. Ao final da negociação, será gerado documento pagável nos agentes arrecadadores da Caer.
								</p>
		                        <span id="bottom">&nbsp;</span>
		                        <img src="imagens/portal/general/seta-info-servicos.gif" alt="Seta" />
		                    </div>
		                </li>
	                </logic:notPresent>
 	                <li id="serv-7" >
	            		<a href="exibirConsultarConsumoHistoricoAguaPortalCaerAction.do">
							<span>Consultar hist&oacute;rico de consumo </span>
						</a>
						<div class="info-serv" style="text-align:justify;display:block;">
	                        <p></p>
	                        <p>Consulta do hist&oacute;rico do volume de &aacute;gua fornecido referentes nos &uacute;ltimos meses.</p>
	                        <p></p>
	                        <span id="bottom">&nbsp;</span>
	                        <img src="imagens/portal/caer/general/seta-info-servicos.gif" alt="Seta" />
	                    </div>
	                </li>
 
                	<li id="serv-8">
	                	<a href="exibirInformarVencimentoAlternativoPortalCaerAction.do">
	                		<span>Alterar vencimento de conta</span>
                		</a>
	                	<div class="info-serv">
	                        <p>Conforme determina a lei federal Lei nº 9.791, 24 de Março de 1999 que dispõe sobre a obrigatoriedade de as concessionárias de serviços 
	                        públicos estabelecerem ao consumidor e ao usuário datas opcionais para o vencimento de seus débitos.
							Aqui o cliente pode solicitar alteração da data de pagamento de sua Fatura.</p>
	                        <span id="bottom">&nbsp;</span>
	                        <img src="imagens/portal/caer/general/seta-info-servicos.gif" alt="Seta" />
                    	</div>
	                </li>
	                
	                <logic:notPresent name="gerarCertidaoNegativa" scope="session">
		                <li id="serv-9">
		                	<a href="javascript:abrirPopup('gerarCertidaoNegativaAction.do?lojaVirtual=sim', 400, 800);">
		                		<span>Certidão negativa de débitos</span>
		               		</a>
		                	<div class="info-serv">
		                        <p><br>Permite a emissão de Certidão Negativa de Débito.<br></p>
		                        <span id="bottom">&nbsp;</span>
		                        <img src="imagens/portal/caer/general/seta-info-servicos.gif" alt="Seta" />
		                   	</div>
		                </li>
			        </logic:notPresent>
	                   
<!--	                  
	                  <logic:present name="esferaPoderNAOPermiteGerarCertidaoNegativa" scope="session">
	               			 <li id="serv-5">
			                	<a href="javascript:esferaPoderNaoPermiteGerarCertidaoNegativa();">
			                		<span>Certidão Negativa de Débitos</span>
		                		</a>
			                	<div class="info-serv">
			                        <p><br>Permite a emissão de Certidão Negativa de Débito.<br></p>
			                        <span id="bottom">&nbsp;</span>
			                        <img src="imagens/portal/caer/general/seta-info-servicos.gif" alt="Seta" />
		                    	</div>
			                 </li>
		              </logic:present>
	                  
	                 <logic:notPresent name="esferaPoderNAOPermiteGerarCertidaoNegativa" scope="session">
	                 		
	                 		<logic:present name="gerarCertidaoNegativa" scope="session">
				                 <li id="serv-5">
				                	<a href="emitirSegundaViaContaPortalCaerAction.do">
				                		<span>Certidão negativa de débitos</span>
			                		</a>
				                	<div class="info-serv">
				                		<p></p>
				                        <p><br>Permite a emissão de certidão negativa de débito.<br></p>
				                        <p></p>
				                        <span id="bottom">&nbsp;</span>
				                        <img src="imagens/portal/caer/general/seta-info-servicos.gif" alt="Seta" />
			                    	</div>
				                </li>
			                </logic:present>
			         </logic:notPresent>
	                 -->
	            </ul>
	        </div>
	        <!-- Content - End -->
	        
	       <%@ include file="/jsp/portal/caer/rodape.jsp"%>
	       </html:form>
	    </div>
	    
		<!-- Avisos -->
		<logic:present name="numeroMesesMinimoVencimentoAlternativoSuperiorPermitido" scope="request">
			<div id="numeroMesesMinimoVencimentoAlternativoSuperiorPermitido" style="display:none;cursor:default;">
				<img alt="Aviso" src="imagens/portal/caer/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;"> Imóvel com vencimento alterado há menos de <bean:write name="numeroMesesMinimo" scope="request" /> meses.</h3> 
				<p>
		        	Em caso de dúvidas, procure uma loja de atendimento mais próxima, ou entre em contato com o call center pelo 0800 280 9520.
		        </p>
				<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		<logic:present name="imovelSemDebito" scope="request">
			<div id="imovelSemDebitos" style="display:none;cursor:default;">
				<img alt="Aviso" src="imagens/portal/caer/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;">Imóvel sem débitos.</h3> 
				<p>
		        	Em caso de dúvidas, procure uma loja de atendimento mais próxima, ou entre em contato com o call center pelo 0800 280 9520.
		        </p>
				<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="imovelSemQuitacaoAnual" scope="request">
			<div id="imovelSemQuitacaoAnual" style="display:none;cursor:default;">
				<img alt="Aviso" src="imagens/portal/caer/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="text-align:justify; padding-top:10px; padding-bottom: 10px;">Imóvel sem declaração anual de quitação de débitos</h3>
		        <p>
		        	Em caso de dúvidas, procure uma loja de atendimento mais próxima, ou entre em contato com o call center pelo 0800 280 9520
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<!-- Validações Inicias de efetuar parcelamento de débitos -->
		<logic:present name="imovelParcelamentoAtivo" scope="request">
			<div id="imovelParcelamentoAtivo" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/caer/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="padding-top:10px; padding-bottom: 10px;">Imóvel já possui um parcelamento não quitado/cobrado. </h3>
		        <p>
		        	Em caso de dúvidas, procure uma loja de atendimento mais próxima, ou entre em contato com o call center pelo 0800 280 9520.
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="imovelSemDebitos" scope="request">
			<div id="imovelSemDebitos" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/caer/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="padding-top:10px; padding-bottom: 10px;">O Imóvel informado não possui débitos. </h3>
		        <p>
		        	Em caso de dúvidas, procure uma loja de atendimento mais próxima, ou entre em contato com o call center pelo 0800 280 9520.
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="debitoParceladoMesCorrente" scope="request">
			<div id="debitoParceladoMesCorrente" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/caer/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="padding-top:10px; padding-bottom: 10px;">O débito deste imóvel já foi parcelado no mês de faturamento corrente. </h3>
		        <p>
		        	Em caso de dúvidas, procure uma loja de atendimento mais próxima, ou entre em contato com o call center pelo 0800 280 9520.
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="imovelNaoPossuiPerfilParcelamento" scope="request">
			<div id="imovelNaoPossuiPerfilParcelamento" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/caer/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="padding-top:10px; padding-bottom: 10px;">Não existe perfil de parcelamento correspondente à situação do imóvel. </h3>
		        <p>
		        	Em caso de dúvidas, procure uma loja de atendimento mais próxima, ou entre em contato com o call center pelo 0800 280 9520.
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="quantidadeReparcelNaoPermiteParcel" scope="request">
			<div id="quantidadeReparcelNaoPermiteParcel" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/caer/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="padding-top:10px; padding-bottom: 10px;">Quantidade de reparcelamento do imóvel não permite um novo parcelamento. </h3>
		        <p>
		        	Em caso de dúvidas, procure uma loja de atendimento mais próxima, ou entre em contato com o call center pelo 0800 280 9520.
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="naoExistePerfilSituacaoImovel" scope="request">
			<div id="naoExistePerfilSituacaoImovel" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/caer/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="padding-top:10px; padding-bottom: 10px;">Não existe perfil de parcelamento correspondente à situação do imóvel.</h3>
		        <p>
		        	Em caso de dúvidas, procure uma loja de atendimento mais próxima, ou entre em contato com o call center pelo 0800 280 9520.
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="imovelSituacaoCobranca" scope="request">
			<div id="imovelSituacaoCobranca" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/caer/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="padding-top:10px; padding-bottom: 10px;">Imóvel com situação de cobrança, não é possivel fazer o parcelamento de débitos.</h3>
		        <p>
		        	Em caso de dúvidas, procure uma loja de atendimento mais próxima, ou entre em contato com o call center pelo 0800 280 9520.
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="imovelSemRA" scope="request">
			<div id="imovelSemRA" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/caer/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="padding-top:10px; padding-bottom: 10px;">O Imóvel informado não possui Registros de Atendimento para serem acompanhados. </h3>
		        <p>
		        	Em caso de dúvidas, procure uma loja de atendimento mais próxima, ou entre em contato com o call center pelo 0800 280 9520.
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="imovelSemPagamento" scope="request">
			<div id="imovelSemPagamento" style="display:none;cursor:default;">
				<img alt="Aviso" src="imagens/portal/caer/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;">Imóvel não possui pagamentos efetuados.</h3> 
				<p>
		        	Em caso de dúvidas, procure uma loja de atendimento mais próxima, ou entre em contato com o call center pelo 0800 280 9520.
		        </p>
				<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="imovelSemConsumos" scope="request">
			<div id="imovelSemConsumos" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/caer/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="padding-top:10px; padding-bottom: 10px;">O Imóvel informado não possui Histórico de Consumo. </h3>
		        <p>
		        	Em caso de dúvidas, procure uma loja de atendimento mais próxima, ou entre em contato com o call center pelo 0800 280 9520.
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<!-- Fim das validações Inicias de efetuar parcelamento de débitos -->
	</body>
</html:html>