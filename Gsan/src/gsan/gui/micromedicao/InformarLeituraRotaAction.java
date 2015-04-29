/*
 * Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
 *
 * This file is part of GSAN, an integrated service management system for Sanitation
 *
 * GSAN is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License.
 *
 * GSAN is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
 */

/*
 * GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
 * Copyright (C) <2007> 
 * Adriano Britto Siqueira
 * Alexandre Santos Cabral
 * Ana Carolina Alves Breda
 * Ana Maria Andrade Cavalcante
 * Aryed Lins de Ara�jo
 * Bruno Leonardo Rodrigues Barros
 * Carlos Elmano Rodrigues Ferreira
 * Cl�udio de Andrade Lira
 * Denys Guimar�es Guenes Tavares
 * Eduardo Breckenfeld da Rosa Borges
 * Fab�ola Gomes de Ara�jo
 * Fl�vio Leonardo Cavalcanti Cordeiro
 * Francisco do Nascimento J�nior
 * Homero Sampaio Cavalcanti
 * Ivan S�rgio da Silva J�nior
 * Jos� Edmar de Siqueira
 * Jos� Thiago Ten�rio Lopes
 * K�ssia Regina Silvestre de Albuquerque
 * Leonardo Luiz Vieira da Silva
 * M�rcio Roberto Batista da Silva
 * Maria de F�tima Sampaio Leite
 * Micaela Maria Coelho de Ara�jo
 * Nelson Mendon�a de Carvalho
 * Newton Morais e Silva
 * Pedro Alexandre Santos da Silva Filho
 * Rafael Corr�a Lima e Silva
 * Rafael Francisco Pinto
 * Rafael Koury Monteiro
 * Rafael Palermo de Ara�jo
 * Raphael Veras Rossiter
 * Roberto Sobreira Barbalho
 * Rodrigo Avellar Silveira
 * Rosana Carvalho Barbosa
 * S�vio Luiz de Andrade Cavalcante
 * Tai Mu Shih
 * Thiago Augusto Souza do Nascimento
 * Tiago Moreno Rodrigues
 * Vivianne Barbosa Sousa
 *
 * Este programa � software livre; voc� pode redistribu�-lo e/ou
 * modific�-lo sob os termos de Licen�a P�blica Geral GNU, conforme
 * publicada pela Free Software Foundation; vers�o 2 da
 * Licen�a.
 * Este programa � distribu�do na expectativa de ser �til, mas SEM
 * QUALQUER GARANTIA; sem mesmo a garantia impl�cita de
 * COMERCIALIZA��O ou de ADEQUA��O A QUALQUER PROP�SITO EM
 * PARTICULAR. Consulte a Licen�a P�blica Geral GNU para obter mais
 * detalhes.
 * Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU
 * junto com este programa; se n�o, escreva para Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307, USA.
 */
package gsan.gui.micromedicao;

import gsan.batch.FiltroProcessoIniciado;
import gsan.batch.Processo;
import gsan.batch.ProcessoIniciado;
import gsan.batch.ProcessoSituacao;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.micromedicao.ArquivoTextoRoteiroEmpresa;
import gsan.micromedicao.FiltroRota;
import gsan.micromedicao.Rota;
import gsan.micromedicao.SituacaoTransmissaoLeitura;
import gsan.micromedicao.leitura.FiltroLeituraAnormalidade;
import gsan.micromedicao.leitura.LeituraAnormalidade;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesSistema;
import gsan.util.ControladorException;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;
import gsan.util.filtro.ParametroSimplesIn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>
 * [UC0936] Informar Leitura por Rota
 * </p>
 * 
 * @author Thiago Nascimento
 */
public class InformarLeituraRotaAction extends GcomAction {

	Collection colecaoProcessos = null;
	boolean concluir = false;

	@Override
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		HttpSession sessao = httpServletRequest.getSession(false);

		boolean concluir = false;
		
		

		ActionForward retorno = actionMapping
				.findForward("InformarLeituraRotaAction");

		InformarLeituraRotaActionForm form = (InformarLeituraRotaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		

		
		
		
		
		
		
		
		
		
		FiltroLeituraAnormalidade filtro = new FiltroLeituraAnormalidade(
				FiltroLeituraAnormalidade.ID);
		filtro.adicionarParametro(new ParametroSimples(
				FiltroLeituraAnormalidade.INDICADOR_USO, ConstantesSistema.SIM));
		filtro.adicionarParametro(new ParametroSimples(
				FiltroLeituraAnormalidade.INDICADOR_USO_SISTEMA,
				ConstantesSistema.NAO));

		StringBuffer faixas = new StringBuffer();

		int indice = form.getIndice().intValue();

		char delimitador = '/';
		char delimitador2 = ';';
		Map<Integer, String[]> imoveisAnormalidades = new LinkedHashMap<Integer, String[]>();
		boolean naoAcabou = false;

		try {

			String temPreenchido = httpServletRequest
					.getParameter("temPreenchido");
			String confirmado = httpServletRequest.getParameter("confirmado");

			String action = httpServletRequest.getParameter("action")
					.toString();
			
			
				
			if (!temPreenchido.trim().equals("0")) {

				Vector<DadosMovimentacao> dados = new Vector<DadosMovimentacao>();
				int lengthLeituras = form.getLeituras().length;

				String[] leituras = form.getLeituras();
				String[] anormalidades = form.getAnormalidades();
				String[] datas = form.getDatas();

				/*
				 * Auxiliar criada para remover 1 dos arrays de leituras,
				 * anormalidades e datas quando existir um dado com msg de
				 * supressao ou hidrometro retirado.
				 */
				int auxiliar = 0;

				for (int i = (indice - 1) * 12; i < (indice - 1) * 12
						+ lengthLeituras; i++) {
					DadosMovimentacao dado = form.getDados().get(i);
					
					int aux = i % 12;

					
					if (auxiliar > 0) {
						aux = aux - auxiliar;
					}
					
					if((datas[aux] !=null && !datas[aux].equals("")) && (leituras[aux] !=null && !leituras[aux].equals("") || anormalidades[aux] != null && !anormalidades[aux].equals(""))){
					
					if (dado.getMsgImovelSuprimidoOuHidrometroRetirado() == null
							|| dado.getMsgImovelSuprimidoOuHidrometroRetirado()
									.equals("")) {


						if (leituras[aux] != null && !leituras[aux].equals("")) {
							dado.setLeituraHidrometro(new Integer(leituras[aux]));
							if (dado.getLeituraHidrometro().intValue() >= dado
									.getFaixaLeituraEsperadaInferior()
									.intValue()
									&& dado.getLeituraHidrometro().intValue() <= dado
											.getFaixaLeituraEsperadaSuperior()
											.intValue()) {
								dado.setIndicadorConfirmacaoLeitura(new Byte(
										(byte) 1));
							} else {
								dado.setIndicadorConfirmacaoLeitura(new Byte(
										(byte) 0));
							}

						} else {
							dado.setLeituraHidrometro(null);
							dado.setIndicadorConfirmacaoLeitura(new Byte(
									(byte) 0));
						}

						if (anormalidades[aux] != null
								&& !anormalidades[aux].equals("")) {
							dado.setCodigoAnormalidade(new Integer(
									anormalidades[aux]));
						} else {
							dado.setCodigoAnormalidade(new Integer(0));
						}

						if (datas[aux] != null && !datas[aux].equals("")) {
							dado.setDataLeituraCampo(Util
									.converteStringParaDate(datas[aux]));
							if (dado.getDataLeituraCampo() == null) {
								throw new ActionServletException(
										"atencao.date", null, "Data");
							} else if (dado.getDataLeituraCampo().after(
									new Date())) {
								throw new ActionServletException(
										"atencao.data_menor_que_atual", null,
										"Leitura");
							}
						}
					} else {
						/*
						 * aumenta um indice no tamanho de dados da tela, pois
						 * nao conta com os dados que ligacao de agua suprimida
						 * ou hidrometro retirado.
						 */
						lengthLeituras = lengthLeituras + 1;
						auxiliar = auxiliar + 1;
						dado.setLeituraHidrometro(null);
						dado.setIndicadorConfirmacaoLeitura(new Byte((byte) 0));
						dado.setCodigoAnormalidade(new Integer(0));
						dado.setDataLeituraCampo(new Date());
					}

					dados.add(dado);
					}
				}
				
              if(!dados.isEmpty()){            	  
            	Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");            	  
            	  
				fachada.atualizarLeituraAnormalidadeSemCelular(dados, usuarioLogado);
              }
              
            }

			

			
			if (action.equals("voltar")) {

				// Voltar
				indice--;
				// naoAcabou = true;
				naoAcabou(actionMapping, httpServletRequest, retorno, form,
						fachada, false, false);

			} else if (action.equals("avancar")) {
				if ( confirmado == null ){
					// Avan�ar
					indice++;
					// naoAcabou = true;
					naoAcabou(actionMapping, httpServletRequest, retorno, form,
							fachada, false, false);					
				} else {
					for (int i = (indice - 1) * 12; i < (indice - 1) * 12 + 12
							&& i < form.getDados().size(); i++) {
						DadosMovimentacao dado = form.getDados().get(i);
	
						// RM 5768 - Inclus�o de alertas para n�o deixar que o
						// leiturista informe poss�veis erros
						if (dado.getCodigoAnormalidade() != null
								&& dado.getCodigoAnormalidade().intValue() != 0) {
							try {
								Map<Integer, String[]> imoveis = fachada
										.validarAnormalidades(dado);
								if (imoveis != null) {
									imoveisAnormalidades.putAll(imoveis);
								}
							} catch (ControladorException e) {
								e.printStackTrace();
							}
						}
					}
	
					if (imoveisAnormalidades.size() > 0) {

						httpServletRequest
								.setAttribute("caminhoActionConclusao",
										"/gsan/informarLeituraRotaAction.do?confirmado=ok&action=avancar&temPreenchido=1");

						// Montar mensagem de confirma��o com os dados
						// (matr�cula e anormalidade) dos im�veis
						Set<Integer> chaves = imoveisAnormalidades.keySet();
						String mensagem = "</br></br>";
						for (Integer in : chaves) {
							if (in != null) {
								String[] msg = imoveisAnormalidades.get(in);
								for (String m : msg) {
									if (m != null) {
										mensagem += "Matr�cula " + in + " : "
												+ m + "</br>";
									}
								}
							}
						}

						// Tela de confirma��o
						return montarPaginaConfirmacao("atencao.anormalidades",
								httpServletRequest, actionMapping, mensagem);
					}
				}

			} else if (action.equals("concluir")) {

/*				retorno = naoAcabou(actionMapping, httpServletRequest, retorno,
						form, fachada, naoAcabou, true);

				Iterator<DadosMovimentacao> i = form.getDados().iterator();

				concluir = true;
				naoAcabou = false;
				while (i.hasNext()) {
					DadosMovimentacao dado = i.next();

					// RM 5768 - Inclus�o de alertas para n�o deixar que o
					// leiturista informe poss�veis erros
					if (dado.getCodigoAnormalidade() != null
							&& dado.getCodigoAnormalidade().intValue() != 0) {
						try {
							Map<Integer, String[]> imoveis = fachada
									.validarAnormalidades(dado);
							if (imoveis != null) {
								imoveisAnormalidades.putAll(imoveis);
							}
						} catch (ControladorException e) {
							e.printStackTrace();
						}
					}
				}
				// Se houverem anormalidades lan�ar p�gina de confirma��o
				if (imoveisAnormalidades.size() > 0) {

					httpServletRequest
							.setAttribute("caminhoActionConclusao",
									"/gsan/informarLeituraRotaAction.do?confirmado=ok&temPreenchido=1&action=0");

					// Montar mensagem de confirma��o com os dados (matr�cula e
					// anormalidade) dos im�veis
					Set<Integer> chaves = imoveisAnormalidades.keySet();
					String mensagem = "</br></br>";
					for (Integer in : chaves) {
						if (in != null) {
							String[] msg = imoveisAnormalidades.get(in);
							for (String m : msg) {
								if (m != null) {
									mensagem += "Matr�cula " + in + " : " + m
											+ "</br>";
								}
							}
						}
					}

					// Tela de confirma��o
					return montarPaginaConfirmacao("atencao.anormalidades",
							httpServletRequest, actionMapping, mensagem);

				} else {
					// Nenhuma anormalidade
					retorno = naoAcabou(actionMapping, httpServletRequest,
							retorno, form, fachada, naoAcabou, true);

					return retorno;
				}
*/
				if ( confirmado != null ){
					// naoAcabou = true;
					return naoAcabou(actionMapping, httpServletRequest, retorno, form,
							fachada, false, false);					
				} else {
					for (int i = (indice - 1) * 12; i < (indice - 1) * 12 + 12
							&& i < form.getDados().size(); i++) {
						DadosMovimentacao dado = form.getDados().get(i);
	
						// RM 5768 - Inclus�o de alertas para n�o deixar que o
						// leiturista informe poss�veis erros
						if (dado.getCodigoAnormalidade() != null
								&& dado.getCodigoAnormalidade().intValue() != 0) {
							try {
								Map<Integer, String[]> imoveis = fachada
										.validarAnormalidades(dado);
								if (imoveis != null) {
									imoveisAnormalidades.putAll(imoveis);
								}
							} catch (ControladorException e) {
								e.printStackTrace();
							}
						}
					}
	
					if (imoveisAnormalidades.size() > 0) {

						httpServletRequest
								.setAttribute("caminhoActionConclusao",
										"/gsan/informarLeituraRotaAction.do?confirmado=ok&temPreenchido=1&action=0");
						
						// Montar mensagem de confirma��o com os dados
						// (matr�cula e anormalidade) dos im�veis
						Set<Integer> chaves = imoveisAnormalidades.keySet();
						String mensagem = "</br></br>";
						for (Integer in : chaves) {
							if (in != null) {
								String[] msg = imoveisAnormalidades.get(in);
								for (String m : msg) {
									if (m != null) {
										mensagem += "Matr�cula " + in + " : "
												+ m + "</br>";
									}
								}
							}
						}

						// Tela de confirma��o
						return montarPaginaConfirmacao("atencao.anormalidades",
								httpServletRequest, actionMapping, mensagem);
					}
				}
			} if ((confirmado != null && confirmado.equals("ok")) || action.equals("concluir")) {
				// Concluir

				retorno = naoAcabou(actionMapping, httpServletRequest, retorno,
						form, fachada, naoAcabou, true);

			}
			if (!concluir) {
				form.setIndice(new Integer(indice));
				Collection<DadosMovimentacao> dados12 = new ArrayList<DadosMovimentacao>();

				for (int i = (indice - 1) * 12; i < (indice - 1) * 12 + 12
						&& i < form.getDados().size(); i++) {
					DadosMovimentacao dados = form.getDados().get(i);
					dados12.add(dados);
					faixas.append(dados.getFaixaLeituraEsperadaInferior());
					faixas.append(delimitador2);
					faixas.append(dados.getFaixaLeituraEsperadaSuperior());
					if (i + 1 < (indice - 1) * 12 + 12
							&& i + 1 < form.getDados().size()) {
						faixas.append(delimitador);
					}
				}

				// Novos 12 imoveis
				sessao.setAttribute("colecaoLeituras", dados12);
				

				httpServletRequest.setAttribute("qnt", "" + dados12.size());

				// Anormalidades do banco
				Collection colecaoLeituraAnormalidade = Fachada.getInstancia()
						.pesquisar(filtro, LeituraAnormalidade.class.getName());

				Iterator iterator = colecaoLeituraAnormalidade.iterator();
				StringBuffer anor = new StringBuffer();

				while (iterator.hasNext()) {
					LeituraAnormalidade l = (LeituraAnormalidade) iterator
							.next();
					anor.append(l.getId().toString());
					anor.append(delimitador2);
					anor.append(l.getIndicadorLeitura().toString());

					if (iterator.hasNext()) {
						anor.append(delimitador);
					}

				}
				httpServletRequest.setAttribute("anormalidadesBanco",
						anor.toString());
				httpServletRequest.setAttribute("faixa", faixas.toString());

			}

		} catch (NumberFormatException n) {
			throw new ActionServletException("atencao.integer", n,
					"Leitura ou Anormalidade");

		} catch (IllegalArgumentException i) {
			throw new ActionServletException("atencao.date", i, "Data");
		}

		return retorno;
	}

	private ActionForward naoAcabou(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest, ActionForward retorno,
			InformarLeituraRotaActionForm form, Fachada fachada,
			boolean naoAcabou, boolean validar) {

		if (validar) {
			Iterator<DadosMovimentacao> it = form.getDados().iterator();
			concluir = true;
			naoAcabou = false;
			while (it.hasNext()) {
				DadosMovimentacao dado = it.next();

				if (dado.getDataLeituraCampo() == null
						|| (dado.getLeituraHidrometro() == null && dado
								.getCodigoAnormalidade() == null)) {

					if (dado.getMsgImovelSuprimidoOuHidrometroRetirado() == null
							|| dado.getMsgImovelSuprimidoOuHidrometroRetirado()
									.equals("")) {

						naoAcabou = true;
						
						SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
						
						if(sistemaParametro.getIndicadorObrigatorioPreecherLeituraRota() == 2){
							naoAcabou = false;
						}
						break;
					}

				}
			}
		}
		if (!naoAcabou) {

			FiltroRota filtroRota = new FiltroRota();
			filtroRota.adicionarParametro(new ParametroSimples(
					FiltroRota.LOCALIDADE_ID, form.getIdLocalidade()));
			filtroRota.adicionarParametro(new ParametroSimples(
					FiltroRota.SETOR_COMERCIAL_CODIGO, form
							.getCodigoSetorComercial()));
			filtroRota.adicionarParametro(new ParametroSimples(
					FiltroRota.CODIGO_ROTA, form.getRota()));

			Collection colecao = fachada.pesquisar(filtroRota,
					Rota.class.getName());

			Rota rota = null;

			if (colecao != null && !colecao.isEmpty()) {
				rota = (Rota) colecao.iterator().next();
			}

			Collection<Integer> processoSituacao = new ArrayList<Integer>();
			processoSituacao.add(ProcessoSituacao.EM_ESPERA);
			processoSituacao.add(ProcessoSituacao.EM_PROCESSAMENTO);

			FiltroProcessoIniciado filtroProcessoIniciado = new FiltroProcessoIniciado();
			filtroProcessoIniciado.adicionarParametro(new ParametroSimples(
					FiltroProcessoIniciado.ID_PROCESSO,
					Processo.FATURAR_GRUPO_FATURAMENTO));
			filtroProcessoIniciado.adicionarParametro(new ParametroSimples(
					FiltroProcessoIniciado.CODIGO_GRUPO, rota
							.getFaturamentoGrupo().getId()));
			filtroProcessoIniciado.adicionarParametro(new ParametroSimplesIn(
					FiltroProcessoIniciado.PROCESSO_SITUACAO_ID,
					processoSituacao));

			colecaoProcessos = fachada.pesquisar(filtroProcessoIniciado,
					ProcessoIniciado.class.getName());

			if (!Util.isVazioOrNulo(colecaoProcessos)) {
				throw new ActionServletException(
						"atencao.processo_faturamento_em_execucao");
			}

			Date dataRealizacao = null;
			if (form.getDados().get(0) != null
					&& form.getDados().get(0).getDataLeituraCampo() != null
					&& !form.getDados().get(0).getDataLeituraCampo().equals("")) {
				dataRealizacao = form.getDados().get(0).getDataLeituraCampo();
			} else {
				dataRealizacao = new Date();
			}

			fachada.atualizarFaturamentoAtividadeCronogramaRegistrarConsistirEfetuarLeitura(
					form.getDados().get(0).getGrupoFaturamento(),
					dataRealizacao);

			Iterator<DadosMovimentacao> itera = form.getDados().iterator();
			ArquivoTextoRoteiroEmpresa arquivoTextoRoteiroEmpresa = null;
			while (itera.hasNext()) {
				DadosMovimentacao dadosM = itera.next();

				if (dadosM.getArquivoTextoRoteiroEmpresa() != null) {
					arquivoTextoRoteiroEmpresa = dadosM
							.getArquivoTextoRoteiroEmpresa();
					break;
				}
			}

			if (arquivoTextoRoteiroEmpresa != null
					&& arquivoTextoRoteiroEmpresa
							.getSituacaoTransmissaoLeitura() != null
					&& arquivoTextoRoteiroEmpresa
							.getSituacaoTransmissaoLeitura()
							.getId()
							.compareTo(
									SituacaoTransmissaoLeitura.FINALIZADO_INCOMPLETO) != 0) {

				arquivoTextoRoteiroEmpresa
						.setSituacaoTransmissaoLeitura(new SituacaoTransmissaoLeitura(
								SituacaoTransmissaoLeitura.FINALIZADO_POR_DIGITACAO));
				arquivoTextoRoteiroEmpresa.setUltimaAlteracao(new Date());

				fachada.atualizar(arquivoTextoRoteiroEmpresa);
			}

			if (validar) {
				retorno = actionMapping.findForward("telaSucesso");
				montarPaginaSucesso(
						httpServletRequest,
						"Leituras e Anormalidades da Rota inseridas com sucesso",
						"Leituras e Anormalidades por Rota",
						"exibirInformarLeituraRotaAction.do?menu=sim");
			}

		} else {

			// colecaoProcessos = null;
			concluir = false;

			// Mensagem de erro
			throw new ActionServletException(
					"atencao.leitura_rota_nao_concluida", null, form.getRota());

		} // fim (!nao acabou)
		return retorno;
	}

	//
	// private void verificaGrupoFaturado(
	// String idLocalidade,
	// String cdSetorComercial,
	// String cdRota,
	// InformarLeituraRotaActionForm form,
	// HttpServletRequest httpServletRequest, Fachada fachada) {
	//
	// SistemaParametro sistemaParametro =
	// fachada.pesquisarParametrosDoSistema();
	//
	// FiltroRota filtroRota = new FiltroRota();
	// filtroRota.adicionarParametro(new
	// ParametroSimples(FiltroRota.CODIGO_ROTA, cdRota));
	// filtroRota.adicionarParametro(new
	// ParametroSimples(FiltroRota.LOCALIDADE_ID, idLocalidade));
	// filtroRota.adicionarParametro(new
	// ParametroSimples(FiltroRota.SETOR_COMERCIAL_CODIGO,cdSetorComercial));
	//
	// filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.SETOR_COMERCIAL);
	// filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.LOCALIDADE);
	// filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.FATURAMENTO_GRUPO);
	// filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.EMPRESA);
	//
	// Collection pesquisa = fachada.pesquisar(filtroRota,
	// Rota.class.getName());
	//
	// if (pesquisa != null && !pesquisa.isEmpty()) {
	// Rota rota = (Rota) Util.retonarObjetoDeColecao(pesquisa);
	//
	// FaturamentoGrupo grupoFaturamento = rota.getFaturamentoGrupo();
	// Integer anoMesFaturamento = sistemaParametro.getAnoMesFaturamento();
	//
	// if(Util.compararAnoMesReferencia(grupoFaturamento.getAnoMesReferencia(),
	// anoMesFaturamento, ">") ){
	// throw new ActionServletException("atencao.grupo.ja.faturado");
	// }
	// }
	// }
	//

}
