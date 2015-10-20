package gcom.gui.relatorio.cadastro;

import gcom.atualizacaocadastral.FiltroMensagemAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.MensagemAtualizacaoCadastralDM;
import gcom.cadastro.atualizacaocadastral.bean.DadosResumoMovimentoAtualizacaoCadastralHelper;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cadastro.RelatorioMensagensPendentesCadastrador;
import gcom.relatorio.cadastro.RelatorioQuantitativoMensagensPendentes;
import gcom.relatorio.cadastro.RelatorioResumoPosicaoAtualizacaoCadastral;
import gcom.relatorio.cadastro.RelatorioResumoSituacaoImoveisAnalistaCadastrador;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarResumoMovimentacaoAtualizacaoCadastralAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, 
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
		
		// Seta o mapeamento de retorno
				ActionForward retorno = actionMapping.findForward(null);

				httpServletRequest.setAttribute("telaSucessoRelatorio", true);

				GerarResumoMovimentacaoAtualizacaoCadastralActionForm form = (GerarResumoMovimentacaoAtualizacaoCadastralActionForm) actionForm;

				DadosResumoMovimentoAtualizacaoCadastralHelper helper = new DadosResumoMovimentoAtualizacaoCadastralHelper();

				if (form.getIdsRegistro() == null) {
					throw new ActionServletException("atencao.selecione_opcao_para_gerar_relatorio");
				} else {
					if (form.getIdsRegistro().length > 1) {
						throw new ActionServletException("atencao.selecione_apenas_uma_opcao_para_relatorio");
					}
				}

				String idRelatorio = "";
				for (int i = 0; i < form.getIdsRegistro().length; i++) {
					idRelatorio = form.getIdsRegistro()[i];
				}

				if (form.getIdEmpresa() != null && !form.getIdEmpresa().equals("-1")) {
					helper.setIdEmpresa(form.getIdEmpresa());
				}

				if (form.getPeriodoAtualizacaoInicial() != null && !form.getPeriodoAtualizacaoInicial().equals("")) {
					helper.setPeriodoInicial(form.getPeriodoAtualizacaoInicial());

					Integer dataInicial = Util.formatarDiaMesAnoComBarraParaAnoMesDia(form.getPeriodoAtualizacaoInicial());
					Integer dataAtual = Util.formatarDiaMesAnoComBarraParaAnoMesDia(Util.formatarData(new Date()));

					if (dataInicial > dataAtual) {
						throw new ActionServletException("atencao.periodo_atualizacao_maior_data_atual");
					}
				}

				if (form.getPeriodoAtualizacaoFinal() != null && !form.getPeriodoAtualizacaoFinal().equals("")) {
					helper.setPeriodoFinal(form.getPeriodoAtualizacaoFinal());

					Integer dataFinal = Util.formatarDiaMesAnoComBarraParaAnoMesDia(form.getPeriodoAtualizacaoFinal());
					Integer dataAtual = Util.formatarDiaMesAnoComBarraParaAnoMesDia(Util.formatarData(new Date()));

					if (dataFinal > dataAtual) {
						throw new ActionServletException("atencao.periodo_atualizacao_maior_data_atual");
					}
				}

				if (form.getGerenciaRegional() != null && !form.getGerenciaRegional().equals("-1")) {
					helper.setIdGerenciaRegional(form.getGerenciaRegional());
				}

				if (form.getUnidadeNegocio() != null && !form.getUnidadeNegocio().equals("-1")) {
					helper.setIdUnidade(form.getUnidadeNegocio());
				}
				
				
				if (form.getLocalidadeInicial() != null && !form.getLocalidadeInicial().equals("")) {
					FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
					filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getLocalidadeInicial()));

					if (form.getGerenciaRegional() != null && !form.getGerenciaRegional().equals("-1")) {
						filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID_GERENCIA, form.getGerenciaRegional()));
					}

					Collection<?> colLocalidade = getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());

					if (Util.isVazioOrNulo(colLocalidade)) {
						throw new ActionServletException("atencao.pesquisa.localidade_inexistente");
					} else {
						helper.setIdLocalidadeInicial(form.getLocalidadeInicial());
					}
				}	
				
				if (form.getSetorComercialInicial() != null && !form.getSetorComercialInicial().equals("")) {
					FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
					filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getSetorComercialInicial()));

					if (form.getLocalidadeInicial() != null && !form.getLocalidadeInicial().equals("")) {
						filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, form.getLocalidadeInicial()));
					} else {
						throw new ActionServletException("atencao.informe_localidade_para_informar_setor");
					}

					Collection<?> colSetorComercial = getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());

					if (Util.isVazioOrNulo(colSetorComercial)) {
						throw new ActionServletException("atencao.setor_comercial.inexistente");
					} else {
						helper.setIdSetorComercialInicial(form.getSetorComercialInicial());
					}
				}
				
				String numerosQuadras = "";
				
				String quadrasFiltro = "";	
				
				if (form.getQuadraSelecionados() != null && form.getQuadraSelecionados().length > 0) {
					
					Integer[] quadrasSelecionadas = form.getQuadraSelecionados();
					
					for (int i = 0; i < quadrasSelecionadas.length; i++) {
						Integer idQuadra = quadrasSelecionadas[i];
						
						FiltroQuadra filtroQuadra = new FiltroQuadra();
						filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID, idQuadra));
						
						Collection<Quadra> colecaoQuadras = getFachada().pesquisar(filtroQuadra, Quadra.class.getName());
						
						if (colecaoQuadras != null && !colecaoQuadras.isEmpty()) {
							Quadra quadra = colecaoQuadras.iterator().next();
							
							numerosQuadras = numerosQuadras + quadra.getNumeroQuadra() + ","; 
							
							if (i < 7) {
								quadrasFiltro = quadrasFiltro + quadra.getNumeroQuadra() + ",";
							} else if (i == 7) {
								quadrasFiltro = quadrasFiltro.substring(0, quadrasFiltro.length() - 1) + " e mais outros. ";
							}
						}
						
					}
					
					if (numerosQuadras != null && !numerosQuadras.equals("")) {
						numerosQuadras = numerosQuadras.substring(0, numerosQuadras.length() - 1);
						quadrasFiltro = quadrasFiltro.substring(0, quadrasFiltro.length() - 1);
						helper.setNumerosQuadras(numerosQuadras);
					}
					
					
					
				}

//						
//						Integer[] quadrasSelecionadas = form.getQuadraSelecionados();
//						String[] quadraString = new String[quadrasSelecionadas.length]; 			
//						
//						for(int i = 0; i < quadrasSelecionadas.length; i++)
//						{
//							quadraString[i] = quadrasSelecionadas[i].toString();
//						}
						
						
						
					
				

				if (form.getCadastrador() != null && !form.getCadastrador().equals("-1")) {
					helper.setIdCadastrador(form.getCadastrador());
				}

				if (form.getAnalista() != null && !form.getAnalista().equals("-1")) {
					helper.setIdAnalista(form.getAnalista());
				}

				if (form.getTipoInconsistencia() != null && !form.getTipoInconsistencia().equals("-1")) {
					helper.setMensagem(form.getTipoInconsistencia());
				}

				String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
				if (tipoRelatorio == null) {
					tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
				}

				String nomeEmpresa = "";
				if (form.getIdEmpresa() != null && !form.getIdEmpresa().equals("-1")) {
					FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
					filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, form.getIdEmpresa()));

					Collection<?> colecaoEmpresa = getFachada().pesquisar(filtroEmpresa, Empresa.class.getName());

					if (!Util.isVazioOrNulo(colecaoEmpresa)) {
						Empresa empresa = (Empresa) Util.retonarObjetoDeColecao(colecaoEmpresa);
						nomeEmpresa = empresa.getDescricao();
					}
				}

				String nomeGerencia = "";
				if (form.getGerenciaRegional() != null && !form.getGerenciaRegional().equals("-1")) {
					FiltroGerenciaRegional filtroGerencia = new FiltroGerenciaRegional();
					filtroGerencia.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.ID, form.getGerenciaRegional()));

					Collection<?> colecaoGerencia = getFachada().pesquisar(filtroGerencia, GerenciaRegional.class.getName());

					if (!Util.isVazioOrNulo(colecaoGerencia)) {
						GerenciaRegional gerencia = (GerenciaRegional) Util.retonarObjetoDeColecao(colecaoGerencia);
						nomeGerencia = gerencia.getNome();
					}
				}
				
				String nomeUnidadeNegocio = "";
				if (form.getUnidadeNegocio() != null && !form.getUnidadeNegocio().equals("")) {
					FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
					filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID, form.getUnidadeNegocio()));

					Collection<?> colecaoUnidadeNegocio = getFachada().pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());

					if (!Util.isVazioOrNulo(colecaoUnidadeNegocio)) {
						UnidadeNegocio unidadeNegocio = (UnidadeNegocio) Util.retonarObjetoDeColecao(colecaoUnidadeNegocio);
					
						nomeUnidadeNegocio = unidadeNegocio.getNome();
					}
				}

				String codSetorInicial = "";
				if (form.getSetorComercialInicial() != null && !form.getSetorComercialInicial().equals("")) {
					codSetorInicial = form.getSetorComercialInicial();
				}

				String cadastrador = "Todos";
				if (form.getCadastrador() != null && !form.getCadastrador().equals("-1")) {
					FiltroUsuario filtroUsuario = new FiltroUsuario();
					filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, form.getCadastrador()));

					Collection<?> colUsuario = getFachada().pesquisar(filtroUsuario, Usuario.class.getName());

					if (!Util.isVazioOrNulo(colUsuario)) {
						Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colUsuario);
						cadastrador = usuario.getNomeUsuario();
					}
				}

				String analista = "Todos";
				if (form.getAnalista() != null && !form.getAnalista().equals("-1")) {
					FiltroUsuario filtroUsuario = new FiltroUsuario();
					filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, form.getAnalista()));

					Collection<?> colUsuario = getFachada().pesquisar(filtroUsuario, Usuario.class.getName());

					if (!Util.isVazioOrNulo(colUsuario)) {
						Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colUsuario);
						analista = usuario.getNomeUsuario();
					}
				}

				String mensagemInconsistencia = "Todos";
				if (form.getTipoInconsistencia() != null && !form.getTipoInconsistencia().equals("-1")) {
					FiltroMensagemAtualizacaoCadastralDM filtroMensagem = new FiltroMensagemAtualizacaoCadastralDM();
					filtroMensagem.adicionarParametro(new ParametroSimples(FiltroMensagemAtualizacaoCadastralDM.ID, form.getTipoInconsistencia()));

					Collection<?> colMensagens = getFachada().pesquisar(filtroMensagem, MensagemAtualizacaoCadastralDM.class.getName());

					if (!Util.isVazioOrNulo(colMensagens)) {
						MensagemAtualizacaoCadastralDM mensagem = (MensagemAtualizacaoCadastralDM) Util.retonarObjetoDeColecao(colMensagens);
						mensagemInconsistencia = mensagem.getMensagem();
					}
				}

				Usuario usuarioLogado = (Usuario) httpServletRequest.getSession(false).getAttribute("usuarioLogado");

				if (idRelatorio.equals("1")) {
				RelatorioResumoPosicaoAtualizacaoCadastral relatorio = new RelatorioResumoPosicaoAtualizacaoCadastral(usuarioLogado);
	
					relatorio.addParametro("nomeEmpresa", nomeEmpresa);
					relatorio.addParametro("nomeGerencia", nomeGerencia);
				    relatorio.addParametro("nomeUnidadeNegocio", nomeUnidadeNegocio);
					relatorio.addParametro("periodoInicial", helper.getPeriodoInicial());
					relatorio.addParametro("periodoFinal", helper.getPeriodoFinal());
					relatorio.addParametro("localidadeInicial", helper.getIdLocalidadeInicial());		
					relatorio.addParametro("codigoSetorInicial", codSetorInicial);					
					relatorio.addParametro("quadraInicial", quadrasFiltro);					
					relatorio.addParametro("cadastrador", cadastrador);
					relatorio.addParametro("analista", analista);
					relatorio.addParametro("tipoInconsistencia", mensagemInconsistencia);
					relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
					relatorio.addParametro("dadosRelatorio", helper);

					retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping);

				} else if (idRelatorio.equals("2")) {
				RelatorioResumoSituacaoImoveisAnalistaCadastrador relatorio = new RelatorioResumoSituacaoImoveisAnalistaCadastrador(usuarioLogado);
					relatorio.addParametro("nomeEmpresa", nomeEmpresa);
				    relatorio.addParametro("nomeGerencia", nomeGerencia);
				    relatorio.addParametro("nomeUnidadeNegocio", nomeUnidadeNegocio);
				    relatorio.addParametro("localidadeInicial", helper.getIdLocalidadeInicial());
					relatorio.addParametro("codigoSetorInicial", codSetorInicial);	
					relatorio.addParametro("quadraInicial", quadrasFiltro);				
					relatorio.addParametro("cadastrador", cadastrador);
					relatorio.addParametro("analista", analista);
					relatorio.addParametro("tipoInconsistencia", mensagemInconsistencia);
					relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
					relatorio.addParametro("dadosRelatorio", helper);
		
				retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping);

				} else if (idRelatorio.equals("3")) {
					RelatorioMensagensPendentesCadastrador relatorio = new RelatorioMensagensPendentesCadastrador(usuarioLogado);
		
				relatorio.addParametro("nomeEmpresa", nomeEmpresa);
					relatorio.addParametro("nomeGerencia", nomeGerencia);
				    relatorio.addParametro("nomeUnidadeNegocio", nomeUnidadeNegocio);
				    relatorio.addParametro("localidadeInicial", helper.getIdLocalidadeInicial());
					relatorio.addParametro("codigoSetorInicial", codSetorInicial);						
					relatorio.addParametro("quadraInicial", quadrasFiltro);			
					relatorio.addParametro("cadastrador", cadastrador);
					relatorio.addParametro("analista", analista);
					relatorio.addParametro("tipoInconsistencia", mensagemInconsistencia);
					relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
					relatorio.addParametro("dadosRelatorio", helper);
		
				retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping);

				} else if (idRelatorio.equals("4")) {
					RelatorioQuantitativoMensagensPendentes relatorio = new RelatorioQuantitativoMensagensPendentes(usuarioLogado);
		
					relatorio.addParametro("nomeEmpresa", nomeEmpresa);
					relatorio.addParametro("nomeGerencia", nomeGerencia);
				    relatorio.addParametro("nomeUnidadeNegocio", nomeUnidadeNegocio);
				    relatorio.addParametro("localidadeInicial", helper.getIdLocalidadeInicial());
					relatorio.addParametro("codigoSetorInicial", codSetorInicial);					
					relatorio.addParametro("quadraInicial", quadrasFiltro);					
					relatorio.addParametro("cadastrador", cadastrador);
					relatorio.addParametro("analista", analista);
					relatorio.addParametro("tipoInconsistencia", mensagemInconsistencia);
					relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
					relatorio.addParametro("dadosRelatorio", helper);
		
					retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping);

				}/*else {

//					RelatorioResumoPosicaoAtualizacaoCadastralPacote relatorio = new RelatorioResumoPosicaoAtualizacaoCadastralPacote(usuarioLogado);
		//
//					relatorio.addParametro("nomeEmpresa", nomeEmpresa);
//					relatorio.addParametro("nomeGerencia", nomeGerencia);
//					relatorio.addParametro("codigoSetorInicial", codSetorInicial);
//					relatorio.addParametro("codigoSetorFinal", codSetorFinal);
//					relatorio.addParametro("quadraInicial", quadraInicial);
//					relatorio.addParametro("quadraFinal", quadraFinal);
//					relatorio.addParametro("cadastrador", cadastrador);
//					relatorio.addParametro("analista", analista);
//					relatorio.addParametro("tipoInconsistencia", mensagemInconsistencia);
//					relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
//					relatorio.addParametro("dadosRelatorio", helper);
		//
//					retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping);
				}*/

				return retorno;
			}
}