package gcom.gui.portal.saae;

import gcom.atendimentopublico.portal.AcessoLojaVirtual;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Classe Responsável por exibir os Serviços do Loja Virtual da SAAE
 * 
 * @author Cesar Medeiros
 * @date 1/07/2013
 */
public class ExibirServicosPortalSaaeAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		String retorno = "exibirServicosPortalSaaeAction";

		ExibirServicosPortalSaaeActionForm form = (ExibirServicosPortalSaaeActionForm) actionForm;

		String method = httpServletRequest.getParameter("method");

		HttpSession sessao = httpServletRequest.getSession(false);
		String ip = httpServletRequest.getRemoteAddr();
		
		if (method != null) {
			if (method.equalsIgnoreCase("servicos")) {

				String cpfDigitado = httpServletRequest.getParameter("vcpf");

				try {
					Integer matricula = Integer.valueOf(form.getMatricula());

					Integer matriculaExistente = this.getFachada().verificarExistenciaImovel(matricula);

					if (matriculaExistente == 1) {

						sessao.setAttribute("matricula", matricula);
						sessao.removeAttribute("cpfCnpj");

						// Caso o usuário já tenha informado o CPF / CNPJ
						if (Util.verificarNaoVazio(form.getCpfCnpjSolicitante()) && Util.verificarNaoVazio(cpfDigitado)) {
							boolean isValidCpfOrCnpj = false;
							if (form.getCpfCnpjSolicitante().length() == 11) {
								isValidCpfOrCnpj = Util.validacaoCPF(form.getCpfCnpjSolicitante());
							} else if (form.getCpfCnpjSolicitante().length() > 11) {
								isValidCpfOrCnpj = Util.validacaoCNPJ(form.getCpfCnpjSolicitante());
							}

							if (isValidCpfOrCnpj) {

								FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
								filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID,
																							matricula));
								filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
								filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.CLIENTE);
								Collection<ClienteImovel> colecaoClienteImovel = this.getFachada().pesquisar(filtroClienteImovel, ClienteImovel.class.getName());

								for (ClienteImovel clienteImovel : colecaoClienteImovel) {

									if (form.getCpfCnpjSolicitante().equals(clienteImovel.getCliente().getCpf())
											|| form.getCpfCnpjSolicitante().equals(clienteImovel.getCliente().getCnpj())) {
										// Pesquisa o nome do usuário e coloca
										// na sessão
										String nomeUsuario = this.getFachada().consultarClienteUsuarioImovel(matricula);
										
										form.setNomeUsuario(nomeUsuario);
										sessao.setAttribute("nomeUsuario", nomeUsuario);
										sessao.setAttribute("cpfCnpj", form.getCpfCnpjSolicitante());
										
										sessao.setAttribute("idCliente", clienteImovel.getCliente().getId());
										sessao.setAttribute("idRelacaoTipo", ClienteRelacaoTipo.USUARIO);
										
										SistemaParametro sistemaParametro = this.getFachada()
												.pesquisarParametrosDoSistema();

										Calendar dataFimVencimentoDebito = new GregorianCalendar();
										dataFimVencimentoDebito.add(Calendar.DATE, -sistemaParametro
												.getNumeroDiasVencimentoDebitoGeracaoCertidaoNegativaDebitos());
										// Pesquisamos se o imovel possue debitos ou não
										ObterDebitoImovelOuClienteHelper debitoGeral = Fachada.getInstancia()
												.obterDebitoImovelOuCliente(1, matricula + "", null, null,
														"190001", "299901", Util.criarData(1, 1, 1900),
														dataFimVencimentoDebito.getTime(), 1, 1, 1, 1, 1, 1, 1,
														true,2);

										if (sistemaParametro.getIndicadorCertidaoNegativaEfeitoPositivo() == ConstantesSistema.NAO
												.shortValue()) {

											boolean esferaPoderPermiteGerarCertidaoNegativa = Fachada.getInstancia().
													esferaPoderPermiteGerarCertidaoNegativa(matricula);
											
											if ( !esferaPoderPermiteGerarCertidaoNegativa ){
												sessao.setAttribute("esferaPoderNAOPermiteGerarCertidaoNegativa", true);
											}
											
											if (debitoGeral != null) {
												if ((debitoGeral.getColecaoContasValores() != null && !debitoGeral
														.getColecaoContasValores().isEmpty())
														|| (debitoGeral.getColecaoGuiasPagamentoValores() != null && !debitoGeral
																.getColecaoGuiasPagamentoValores().isEmpty())) {
													
														sessao.setAttribute("gerarCertidaoNegativa", true);
													
												} else {
													if (sistemaParametro
															.getIndicadorDebitoACobrarValidoCertidaoNegativa()
															.equals(ConstantesSistema.SIM)
															&& (debitoGeral.getColecaoDebitoACobrar() != null && !debitoGeral
																	.getColecaoDebitoACobrar().isEmpty())) {
														
														sessao.setAttribute("gerarCertidaoNegativa", true);
													}
												}
											}

										}

										httpServletRequest.removeAttribute("solicitarCpfCnpj");
										httpServletRequest.removeAttribute("cpfCnpjNaoCadastrado");
										httpServletRequest.removeAttribute("cpfCnpjInvalido");

										retorno = "servicosPortalSaaeAction";

										break;
									} else {
										httpServletRequest.setAttribute("solicitarCpfCnpj", true);
										httpServletRequest.setAttribute("cpfCnpjNaoCadastrado", true);
										httpServletRequest.removeAttribute("cpfCnpjInvalido");
									}
								}
							} else {
								httpServletRequest.setAttribute("solicitarCpfCnpj", true);
								httpServletRequest.setAttribute("cpfCnpjInvalido", true);
								httpServletRequest.removeAttribute("cpfCnpjNaoCadastrado");
							}
						} else {
							// Caso o usuário ainda não tenha informado o CPF /
							// CNPJ
							httpServletRequest.setAttribute("solicitarCpfCnpj", true);
							if(cpfDigitado != null){
								httpServletRequest.setAttribute("cpfCnpjInvalido", true);	
							}
						}
					} else {

						httpServletRequest.setAttribute("nomeCampo", "matricula");
						httpServletRequest.setAttribute("imovelInvalido", true);
					}
				} catch (NumberFormatException e) {
					httpServletRequest.setAttribute("nomeCampo", "matricula");
					httpServletRequest.setAttribute("imovelInvalido", true);
				}
			} else if (method.equalsIgnoreCase("declaracaoAnual")) {
				this.pesquisarAnosImovel((Integer) sessao.getAttribute("matricula"), httpServletRequest, form);
				httpServletRequest.setAttribute("voltarServicos", true);
				retorno = "servicoDeclaracaoAnualSaae";
				
				Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.DECLARACAO_ANUAL_QUITACAO_DEBITO, AcessoLojaVirtual.INDICADOR_SERVICO_NAO_EXECUTADO); 

			} else if (method.equalsIgnoreCase("voltarServico")) {
				retorno = "servicosPortalSaaeAction";
			} else if ( method.equalsIgnoreCase("certidao") ) {
				retorno = "";
			}
		} else {
			form.setCpfCnpjSolicitante(null);
			sessao.removeAttribute("cpfCnpj");
		}

		if (httpServletRequest.getAttribute("imovelSemQuitacaoAnual") != null) {
			retorno = "servicosPortalSaaeAction";
			httpServletRequest.removeAttribute("voltarServicos");
			httpServletRequest.removeAttribute("exibirDeclaracaoAnual");
		}

		return actionMapping.findForward(retorno);
	}

	/**
	 * @author Magno Gouveia
	 * @date 17/05/2011
	 * @param idImovel,
	 *            httpServletRequest
	 */
	private void pesquisarAnosImovel(Integer idImovel, HttpServletRequest httpServletRequest,
			ExibirServicosPortalSaaeActionForm form) {

		Collection<Object> colecaoAnosImovel = this.getFachada().pesquisarAnoImovelEmissao2ViaDeclaracaoAnualQuitacaoDebitos(String.valueOf(idImovel));
		if (colecaoAnosImovel == null || colecaoAnosImovel.isEmpty()) {
			httpServletRequest.setAttribute("imovelSemQuitacaoAnual", true);

		} else {
			httpServletRequest.setAttribute("colecaoAnosImovel", colecaoAnosImovel);

			// Verifica se o usuário digitou o CPF / CNPJ
			httpServletRequest.removeAttribute("exibirDeclaracaoAnual");
			if (form.getCpfCnpjSolicitante() != null) {
				httpServletRequest.setAttribute("exibirDeclaracaoAnual", true);
			} else {
				httpServletRequest.setAttribute("exception", "CPF/CNPJ informado não corresponde com o do cliente");
			}
		}
	}
	
}