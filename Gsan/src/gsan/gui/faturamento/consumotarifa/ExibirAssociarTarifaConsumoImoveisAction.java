/**
 * 
 */
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
package gsan.gui.faturamento.consumotarifa;

import gsan.cadastro.cliente.ClienteImovel;
import gsan.cadastro.cliente.FiltroClienteImovel;
import gsan.cadastro.imovel.FiltroImovel;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.FiltroQuadra;
import gsan.cadastro.localidade.FiltroSetorComercial;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.Quadra;
import gsan.cadastro.localidade.SetorComercial;
import gsan.fachada.Fachada;
import gsan.faturamento.consumotarifa.ConsumoTarifa;
import gsan.faturamento.consumotarifa.FiltroConsumoTarifa;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.gui.relatorio.cadastro.GerarRelatorioImoveisClientesCorporativoAction;
import gsan.seguranca.acesso.PermissaoEspecial;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.FiltroParametro;
import gsan.util.filtro.ParametroNulo;
import gsan.util.filtro.ParametroSimples;
import gsan.util.filtro.ParametroSimplesDiferenteDe;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descri��o da classe
 * 
 * @author R�mulo Aur�lio
 * @date 07/12/2006
 */
public class ExibirAssociarTarifaConsumoImoveisAction extends GcomAction {

	/**
	 * Este caso de uso permite associar a tarifa de consumo para um ou mais
	 * im�veis.
	 * 
	 * [UC0378] Associar Tarifa de Consumo a Im�veis
	 * 
	 * @author R�mulo Aur�lio
	 * @date 07/12/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("exibirAssociarTarifaConsumoImoveis");

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		// Obt�m a sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

        Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		AssociarTarifaConsumoImoveisActionForm associarTarifaConsumoImoveisActionForm = (AssociarTarifaConsumoImoveisActionForm) actionForm;

		if (httpServletRequest.getParameter("liberarBotaoInserir") != null) {
			httpServletRequest.setAttribute("liberarBotaoInserir", true);
			httpServletRequest.removeAttribute("bloquear");
		}

		if (associarTarifaConsumoImoveisActionForm.getTarifaAnteriorHidden() != null) {
			if (!associarTarifaConsumoImoveisActionForm.getTarifaAnteriorHidden()
					.equals("-1")) {
				associarTarifaConsumoImoveisActionForm
						.setTarifaAnterior(associarTarifaConsumoImoveisActionForm
								.getTarifaAnterior());

			}
		}

		// Carregamento Combos
		FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
		
		filtroConsumoTarifa.adicionarParametro(new ParametroSimples(FiltroConsumoTarifa.INDICADOR_USO, ConstantesSistema.SIM));

		filtroConsumoTarifa.setCampoOrderBy(FiltroConsumoTarifa.DESCRICAO);

		// Verifica se os dados foram informados da tabela existem e joga numa
		// colecao

		Collection<ConsumoTarifa> colecaoConsumoTarifa = fachada.pesquisar(
				filtroConsumoTarifa, ConsumoTarifa.class.getName());

		if (colecaoConsumoTarifa == null || colecaoConsumoTarifa.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Tabela Consumo Tarifa");
		}

		httpServletRequest.setAttribute("colecaoConsumoTarifa",
				colecaoConsumoTarifa);

		// Fim carregamento combobox

		String objetoConsulta = (String) httpServletRequest
				.getParameter("objetoConsulta");

		String inscricaoTipo = (String) httpServletRequest
				.getParameter("inscricaoTipo");

		if (objetoConsulta != null
				&& !objetoConsulta.trim().equalsIgnoreCase("")
				&& inscricaoTipo != null
				&& !inscricaoTipo.trim().equalsIgnoreCase("")) {

			switch (Integer.parseInt(objetoConsulta)) {
			// Localidade
			case 1:

				pesquisarLocalidade(inscricaoTipo,
						associarTarifaConsumoImoveisActionForm, fachada,
						httpServletRequest);

				break;
			// Setor Comercial
			case 2:

				pesquisarLocalidade(inscricaoTipo,
						associarTarifaConsumoImoveisActionForm, fachada,
						httpServletRequest);

				pesquisarSetorComercial(inscricaoTipo,
						associarTarifaConsumoImoveisActionForm, fachada,
						httpServletRequest);

				break;
			// Quadra
			case 3:

				pesquisarLocalidade(inscricaoTipo,
						associarTarifaConsumoImoveisActionForm, fachada,
						httpServletRequest);

				pesquisarSetorComercial(inscricaoTipo,
						associarTarifaConsumoImoveisActionForm, fachada,
						httpServletRequest);

				pesquisarQuadra(inscricaoTipo,
						associarTarifaConsumoImoveisActionForm, fachada,
						httpServletRequest);

				break;
			default:
				break;
			}
		} else {
			sessao.removeAttribute("associarTarifaConsumoImoveisActionForm");
		}
		//

		if (associarTarifaConsumoImoveisActionForm != null) {
			String idImovel = associarTarifaConsumoImoveisActionForm
					.getIdImovel();

			if (idImovel != null && !idImovel.equals("")) {
				
					FiltroImovel filtroImovel = new FiltroImovel();
					filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.IMOVEL_PERFIL_CORPORATIVO,ConstantesSistema.SIM));
					filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,idImovel));
					
					// pesquisa a cole�ao de imovel corporativo
					Collection<?> imoveis = fachada.pesquisar(filtroImovel, Imovel.class
							.getName());
					if(imoveis.size() > 0){
						
						boolean possuiPermissaoManterClienteResponsavelImoveisPublicos = 
				    			fachada.verificarPermissaoEspecialAtiva(
				    					PermissaoEspecial.MANTER_IMOVEL_PERFIL_CORPORATIVO,usuarioLogado);
				    		
				    		if(!possuiPermissaoManterClienteResponsavelImoveisPublicos){
				    			throw new ActionServletException(
				    					"atencao.usuario_permissao_corporativo");
				    		}
				    		
					}
				
				// Pesquisa o imovel para mandar para a pagina informacoes sobre
				// o endereco
				FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

				// Objetos que ser�o retornados pelo Hibernate

				// filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra.bairro.municipio.unidadeFederacao");

				filtroClienteImovel
						.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
				filtroClienteImovel
						.adicionarCaminhoParaCarregamentoEntidade("imovel.consumoTarifa");

				filtroClienteImovel
						.adicionarCaminhoParaCarregamentoEntidade("imovel.localidade");
				filtroClienteImovel
						.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
				filtroClienteImovel
						.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroBairro.bairro.municipio.unidadeFederacao");
				filtroClienteImovel
						.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.cep");
				/*
				 * filtroClienteImovel
				 * .adicionarCaminhoParaCarregamentoEntidade("imovel.areaConstruida");
				 */
				filtroClienteImovel
						.adicionarCaminhoParaCarregamentoEntidade("imovel");
				filtroClienteImovel
						.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTipo");
				filtroClienteImovel
						.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTitulo");
				filtroClienteImovel
						.adicionarCaminhoParaCarregamentoEntidade("imovel.enderecoReferencia");

				// adiciona o indicador de exclus�o do imovel
				filtroClienteImovel
						.adicionarParametro(new ParametroSimplesDiferenteDe(
								FiltroClienteImovel.INDICADOR_IMOVEL_EXCLUIDO,
								Imovel.IMOVEL_EXCLUIDO,
								FiltroParametro.CONECTOR_OR, 2));

				filtroClienteImovel.adicionarParametro(new ParametroNulo(
						FiltroClienteImovel.INDICADOR_IMOVEL_EXCLUIDO));

				filtroClienteImovel.adicionarParametro(new ParametroSimples(
						FiltroClienteImovel.IMOVEL_ID, idImovel));

				Collection clientesImoveis = Fachada.getInstancia().pesquisar(
						filtroClienteImovel, ClienteImovel.class.getName());

				if (!clientesImoveis.isEmpty()) {
					ClienteImovel clienteImovel = (ClienteImovel) ((List) clientesImoveis)
							.get(0);

					sessao.setAttribute("clienteImovel", clienteImovel);

					// Obter a quantidade de economias do im�vel escolhido
					int quantEconomias = Fachada
							.getInstancia()
							.obterQuantidadeEconomias(clienteImovel.getImovel());

					associarTarifaConsumoImoveisActionForm
							.setTarifaAnterior(clienteImovel.getImovel()
									.getConsumoTarifa().getId().toString());

					Integer qtdImoveisAtualizados = 1;

					httpServletRequest
							.setAttribute("liberarBotaoInserir", true);

					associarTarifaConsumoImoveisActionForm
							.setQuantidadeImoveisAtualizados(qtdImoveisAtualizados
									.toString());

					// Seta no request
					sessao.setAttribute("quantEconomias", String
							.valueOf(quantEconomias));
					associarTarifaConsumoImoveisActionForm
							.setEndereco(clienteImovel.getImovel()
									.getEnderecoFormatado());

					associarTarifaConsumoImoveisActionForm
							.setInscricaoImovel(clienteImovel.getImovel()
									.getInscricaoFormatada());
					httpServletRequest.setAttribute("nomeCampo", "selecionar");
					httpServletRequest
							.setAttribute("enderecoFormatado", "true");

				} else {
					associarTarifaConsumoImoveisActionForm
							.setInscricaoImovel("MATR�CULA INEXISTENTE");
					associarTarifaConsumoImoveisActionForm.setEndereco("");

					httpServletRequest.setAttribute("corImovel", "exception");

					httpServletRequest.setAttribute("nomeCampo", "idImovel");
				}
			}
		}

		validacaoFinal(associarTarifaConsumoImoveisActionForm, fachada);

		// Exibir quantidade de Imoveis com situacao especial de faturamento
		if (httpServletRequest.getParameter("selecionar") != null) {

			httpServletRequest.removeAttribute("bloquear");

			httpServletRequest.setAttribute("liberarBotaoInserir", true);

			// O usuario clicou no botao selecionar, ser� feita uma pesquisa na
			// tabela imovel para depois atualizar a columa cstf_id =
			// consumoTarifa

			if (associarTarifaConsumoImoveisActionForm.getIdImovel() == null
					|| associarTarifaConsumoImoveisActionForm.getIdImovel()
							.equalsIgnoreCase("")) {

				Collection colecaoImoveis = fachada
						.pesquisarImoveisTarifaConsumo(
								associarTarifaConsumoImoveisActionForm
										.getLocalidadeOrigemID(),
								associarTarifaConsumoImoveisActionForm
										.getLocalidadeDestinoID(),
								associarTarifaConsumoImoveisActionForm
										.getSetorComercialOrigemCD(),
								associarTarifaConsumoImoveisActionForm
										.getSetorComercialDestinoCD(),
								associarTarifaConsumoImoveisActionForm
										.getQuadraOrigemID(),
								associarTarifaConsumoImoveisActionForm
										.getQuadraDestinoID(),
								associarTarifaConsumoImoveisActionForm
										.getLoteOrigem(),
								associarTarifaConsumoImoveisActionForm
										.getLoteDestino(),
								associarTarifaConsumoImoveisActionForm
										.getSubloteOrigem(),
								associarTarifaConsumoImoveisActionForm
										.getSubloteDestino(),
								associarTarifaConsumoImoveisActionForm
										.getTarifaAnteriorHidden());

				sessao.setAttribute("colecaoImoveis", colecaoImoveis);
				
				
				Integer qtd = colecaoImoveis.size();

				associarTarifaConsumoImoveisActionForm
						.setQuantidadeImoveisAtualizados(qtd.toString());

				// Iterator iteratorImoveis = colecaoImoveis.iterator();
				
				

			}

		} else {

			if (httpServletRequest.getParameter("bloquear") != null) {
				if (httpServletRequest.getParameter("bloquear").equals(
						"matricula")) {
					httpServletRequest.setAttribute("bloquear", "matricula");
				} else {
					httpServletRequest.setAttribute("bloquear", "todos");
				}
			} else {
				httpServletRequest.setAttribute("bloquear", "");
			}
		}
		// manda o parametro que veio do validar enter
		// para ,se preciso, desabilitar os campos posterior ao intervalo, que
		// n�o
		// s�o iguais.
		if (httpServletRequest.getParameter("campoDesabilita") != null
				&& !httpServletRequest.getParameter("campoDesabilita").equals(
						"")) {
			httpServletRequest.setAttribute("campoDesabilita",
					httpServletRequest.getParameter("campoDesabilita"));
		}

		return retorno;
	}

	private void validacaoFinal(AssociarTarifaConsumoImoveisActionForm form,
			Fachada fachada) {

		// validar localidade inicial sendo maior que localidade final
		if (form.getLocalidadeOrigemID() != null
				&& form.getLocalidadeDestinoID() != null) {
			if (!form.getLocalidadeOrigemID().equals("")
					&& !form.getLocalidadeDestinoID().equals("")) {
				int origem = Integer.parseInt(form.getLocalidadeOrigemID());
				int destino = Integer.parseInt(form.getLocalidadeDestinoID());
				if (origem > destino) {
					throw new ActionServletException(
							"atencao.localidade.final.maior.localidade.inicial",
							null, "");
				}
				String localidadeID = (String) form.getLocalidadeOrigemID();

				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, localidadeID));

				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna localidade
				Collection colecaoPesquisa = fachada.pesquisar(
						filtroLocalidade, Localidade.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Localidade nao encontrada
					throw new ActionServletException(
							"atencao.pesquisa.localidade_inexistente");
				} else {
					Localidade objetoLocalidadeOrigem = (Localidade) Util
							.retonarObjetoDeColecao(colecaoPesquisa);

					form.setLocalidadeOrigemID(String
							.valueOf(objetoLocalidadeOrigem.getId()));

					if (origem < destino) {
						// se localidade inicial < localidade final
						// pesquisa p descobrir localidade final existe
						// se existir seta o id dela no actionForm

						filtroLocalidade.limparListaParametros();
						filtroLocalidade
								.adicionarParametro(new ParametroSimples(
										FiltroLocalidade.ID, destino));

						filtroLocalidade
								.adicionarParametro(new ParametroSimples(
										FiltroLocalidade.INDICADORUSO,
										ConstantesSistema.INDICADOR_USO_ATIVO));

						// Retorna localidade
						Collection colecaoPesquisaDestino = fachada.pesquisar(
								filtroLocalidade, Localidade.class.getName());

						if (colecaoPesquisaDestino == null
								|| colecaoPesquisaDestino.isEmpty()) {
							// Localidade nao encontrada
							throw new ActionServletException(
									"atencao.pesquisa.localidade_inexistente");
						}
						Localidade objetoLocalidadeDestino = (Localidade) Util
								.retonarObjetoDeColecao(colecaoPesquisaDestino);

						form.setLocalidadeDestinoID(String
								.valueOf(objetoLocalidadeDestino.getId()));

					} else {
						form.setLocalidadeDestinoID(String
								.valueOf(objetoLocalidadeOrigem.getId()));
					}

				}
			}
		}

		// validar setor comercial sendo maior que setor comercial final
		if (form.getSetorComercialOrigemCD() != null
				&& form.getSetorComercialDestinoCD() != null) {
			if (!form.getSetorComercialOrigemCD().equals("")
					&& !form.getSetorComercialDestinoCD().equals("")) {
				int origem = Integer.parseInt(form.getSetorComercialOrigemCD());
				int destino = Integer.parseInt(form
						.getSetorComercialDestinoCD());
				if (origem > destino) {
					throw new ActionServletException(
							"atencao.setor.comercial.final.maior.setor.comercial.inicial",
							null, "");
				}
				String setorComercialCD = (String) form
						.getSetorComercialOrigemCD();

				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
				// Adiciona o id da localidade que est� no formul�rio para
				// compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, form
								.getLocalidadeOrigemID()));

				// Adiciona o c�digo do setor comercial que esta no formul�rio
				// para compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						setorComercialCD));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna setorComercial
				Collection colecaoPesquisa = fachada.pesquisar(
						filtroSetorComercial, SetorComercial.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Setor Comercial inexistente.
					throw new ActionServletException(
							"atencao.setor_comercial.inexistente");
				} else {

					SetorComercial objetoSetorComercial = (SetorComercial) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					form.setSetorComercialOrigemID(String
							.valueOf(objetoSetorComercial.getId()));

					if (origem < destino) {
						// se setor comercial inicial < setor comercial final
						// pesquisa p descobrir setor comercial final existe
						// se existir seta o id dele no actionForm
						filtroSetorComercial.limparListaParametros();
						filtroSetorComercial
								.adicionarParametro(new ParametroSimples(
										FiltroSetorComercial.ID_LOCALIDADE,
										form.getLocalidadeOrigemID()));

						// Adiciona o c�digo do setor comercial que esta no
						// formul�rio
						// para compor a pesquisa.
						filtroSetorComercial
								.adicionarParametro(new ParametroSimples(
										FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
										destino));

						filtroSetorComercial
								.adicionarParametro(new ParametroSimples(
										FiltroSetorComercial.INDICADORUSO,
										ConstantesSistema.INDICADOR_USO_ATIVO));

						// Retorna setorComercial
						Collection colecaoPesquisaDestino = fachada.pesquisar(
								filtroSetorComercial, SetorComercial.class
										.getName());

						if (colecaoPesquisaDestino == null
								|| colecaoPesquisaDestino.isEmpty()) {
							// Setor Comercial inexistente.
							throw new ActionServletException(
									"atencao.setor_comercial.inexistente");
						}
						SetorComercial objetoSetorComercialDestino = (SetorComercial) Util
								.retonarObjetoDeColecao(colecaoPesquisaDestino);

						form.setSetorComercialDestinoCD(String
								.valueOf(objetoSetorComercialDestino.getCodigo()));

					} else {
						form.setSetorComercialDestinoID(String
								.valueOf(objetoSetorComercial.getId()));
					}

				}
			}
		}

		// validar quadra sendo maior que localidade final

		if (form.getQuadraOrigemNM() != null
				&& form.getQuadraDestinoNM() != null) {
			if (!form.getQuadraOrigemNM().equals("")
					&& !form.getQuadraDestinoNM().equals("")) {
				int origem = Integer.parseInt(form.getQuadraOrigemNM());
				int destino = Integer.parseInt(form.getQuadraDestinoNM());
				if (origem > destino) {
					throw new ActionServletException(
							"atencao.quadra.final.maior.quadra.inical", null,
							"");
				}
				String quadraNM = (String) form.getQuadraOrigemNM();

				// Adiciona o id do setor comercial que est� no formul�rio para
				// compor a pesquisa.
				FiltroQuadra filtroQuadra = new FiltroQuadra();
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_SETORCOMERCIAL, form
								.getSetorComercialOrigemID()));

				// Adiciona o n�mero da quadra que esta no formul�rio para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.NUMERO_QUADRA, quadraNM));

				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna quadra
				Collection colecaoPesquisa = fachada.pesquisar(filtroQuadra,
						Quadra.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					throw new ActionServletException(
							"atencao.quadra.inexistente");
				} else {
					Quadra objetoQuadra = (Quadra) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					form
							.setQuadraOrigemID(String.valueOf(objetoQuadra
									.getId()));

					if (origem < destino) {
						// se setor comercial inicial < setor comercial final
						// pesquisa p descobrir setor comercial final existe
						// se existir seta o id dele no actionForm
						filtroQuadra.limparListaParametros();
						filtroQuadra.adicionarParametro(new ParametroSimples(
								FiltroQuadra.ID_SETORCOMERCIAL, form
										.getSetorComercialOrigemID()));

						// Adiciona o n�mero da quadra que esta no formul�rio
						// para
						// compor a pesquisa.
						filtroQuadra.adicionarParametro(new ParametroSimples(
								FiltroQuadra.NUMERO_QUADRA, quadraNM));

						filtroQuadra.adicionarParametro(new ParametroSimples(
								FiltroQuadra.INDICADORUSO,
								ConstantesSistema.INDICADOR_USO_ATIVO));

						// Retorna quadra
						Collection colecaoPesquisaDestino = fachada.pesquisar(
								filtroQuadra, Quadra.class.getName());

						if (colecaoPesquisaDestino == null
								|| colecaoPesquisaDestino.isEmpty()) {
							throw new ActionServletException(
									"atencao.quadra.inexistente");
						}
						Quadra objetoQuadraDestino = (Quadra) Util
								.retonarObjetoDeColecao(colecaoPesquisaDestino);
						form.setQuadraDestinoID(String
								.valueOf(objetoQuadraDestino.getId()));
					} else {

						form.setQuadraDestinoID(String.valueOf(objetoQuadra
								.getId()));
					}
				}
			}
		}

		// validar lote sendo maior que localidade final

		if (form.getLoteOrigem() != null && form.getLoteDestino() != null) {
			if (!form.getLoteOrigem().equals("")
					&& !form.getLoteDestino().equals("")) {
				try {
					int origem = Integer.parseInt(form.getLoteOrigem());
					int destino = Integer.parseInt(form.getLoteDestino());
					if (origem > destino) {
						throw new ActionServletException(
								"atencao.lote.final.maior.lote.inical", null,
								"");
					}
				} catch (NumberFormatException e) {
					throw new ActionServletException("atencao.nao.numerico",
							null, "Lote(s)");
				}
			}
		}

		// validar sublote sendo maior que localidade final
		if (form.getSubloteOrigem() != null && form.getSubloteDestino() != null) {
			if (!form.getSubloteOrigem().equals("")
					&& !form.getSubloteDestino().equals("")) {
				try {
					int origem = Integer.parseInt(form.getSubloteOrigem());
					int destino = Integer.parseInt(form.getSubloteDestino());
					if (origem > destino) {
						throw new ActionServletException(
								"atencao.sublote.final.maior.sublote.inicial",
								null, "");
					}
				} catch (NumberFormatException e) {
					throw new ActionServletException("atencao.nao.numerico",
							null, "SubLote(s)");
				}
			}
		}
		// validar Sublote sendo maior que localidade final

	}

	private void pesquisarLocalidade(
			String inscricaoTipo,
			AssociarTarifaConsumoImoveisActionForm associarTarifaConsumoImoveisActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		Collection colecaoPesquisa = null;

		String localidadeID = null;

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			associarTarifaConsumoImoveisActionForm.setInscricaoTipo("origem");
			// Recebe o valor do campo localidadeOrigemID do formul�rio.
			localidadeID = (String) associarTarifaConsumoImoveisActionForm
					.getLocalidadeOrigemID();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, localidadeID));

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna localidade
			colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Localidade nao encontrada
				// Limpa os campos localidadeOrigemID e nomeLocalidadeOrigem do
				// formul�rio
				associarTarifaConsumoImoveisActionForm
						.setLocalidadeOrigemID("");
				associarTarifaConsumoImoveisActionForm
						.setNomeLocalidadeOrigem("LOCALIDADE INEXISTENTE");
				httpServletRequest.setAttribute("corLocalidadeOrigem",
						"exception");
				httpServletRequest.setAttribute("nomeCampo",
						"localidadeOrigemID");
			} else {
				Localidade objetoLocalidade = (Localidade) Util
						.retonarObjetoDeColecao(colecaoPesquisa);
				associarTarifaConsumoImoveisActionForm
						.setLocalidadeOrigemID(String.valueOf(objetoLocalidade
								.getId()));
				associarTarifaConsumoImoveisActionForm
						.setNomeLocalidadeOrigem(objetoLocalidade
								.getDescricao());
				associarTarifaConsumoImoveisActionForm
						.setLocalidadeDestinoID(String.valueOf(objetoLocalidade
								.getId()));
				associarTarifaConsumoImoveisActionForm
						.setNomeLocalidadeDestino(objetoLocalidade
								.getDescricao());
				httpServletRequest.setAttribute("corLocalidadeOrigem", "valor");
				httpServletRequest.setAttribute("nomeCampo",
						"setorComercialOrigemCD");
			}
		} else {
			// Recebe o valor do campo localidadeDestinoID do formul�rio.
			localidadeID = (String) associarTarifaConsumoImoveisActionForm
					.getLocalidadeDestinoID();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, localidadeID));

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna localidade
			colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			associarTarifaConsumoImoveisActionForm.setInscricaoTipo("destino");

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Localidade nao encontrada
				// Limpa os campos localidadeDestinoID e nomeLocalidadeDestino
				// do formul�rio
				associarTarifaConsumoImoveisActionForm
						.setLocalidadeDestinoID("");
				associarTarifaConsumoImoveisActionForm
						.setNomeLocalidadeDestino("Localidade inexistente.");
				httpServletRequest.setAttribute("corLocalidadeDestino",
						"exception");
				httpServletRequest.setAttribute("nomeCampo",
						"localidadeDestinoID");
			} else {
				Localidade objetoLocalidade = (Localidade) Util
						.retonarObjetoDeColecao(colecaoPesquisa);
				associarTarifaConsumoImoveisActionForm
						.setLocalidadeDestinoID(String.valueOf(objetoLocalidade
								.getId()));
				associarTarifaConsumoImoveisActionForm
						.setNomeLocalidadeDestino(objetoLocalidade
								.getDescricao());
				httpServletRequest
						.setAttribute("corLocalidadeDestino", "valor");
				httpServletRequest.setAttribute("nomeCampo",
						"setorComercialDestinoCD");
			}
		}

	}

	private void pesquisarSetorComercial(
			String inscricaoTipo,
			AssociarTarifaConsumoImoveisActionForm associarTarifaConsumoImoveisActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		Collection colecaoPesquisa = null;

		String localidadeID = null;

		String setorComercialCD = null;

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			associarTarifaConsumoImoveisActionForm.setInscricaoTipo("origem");
			// Recebe o valor do campo localidadeOrigemID do formul�rio.
			localidadeID = (String) associarTarifaConsumoImoveisActionForm
					.getLocalidadeOrigemID();

			// O campo localidadeOrigemID ser� obrigat�rio
			if (localidadeID != null
					&& !localidadeID.trim().equalsIgnoreCase("")) {

				setorComercialCD = (String) associarTarifaConsumoImoveisActionForm
						.getSetorComercialOrigemCD();

				// Adiciona o id da localidade que est� no formul�rio para
				// compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, localidadeID));

				// Adiciona o c�digo do setor comercial que esta no formul�rio
				// para compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						setorComercialCD));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna setorComercial
				colecaoPesquisa = fachada.pesquisar(filtroSetorComercial,
						SetorComercial.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Setor Comercial nao encontrado
					// Limpa os campos setorComercialOrigemCD,
					// nomeSetorComercialOrigem e setorComercialOrigemID do
					// formul�rio
					associarTarifaConsumoImoveisActionForm
							.setSetorComercialOrigemCD("");
					associarTarifaConsumoImoveisActionForm
							.setSetorComercialOrigemID("");
					associarTarifaConsumoImoveisActionForm
							.setNomeSetorComercialOrigem("Setor comercial inexistente.");
					httpServletRequest.setAttribute("corSetorComercialOrigem",
							"exception");
					httpServletRequest.setAttribute("nomeCampo",
							"setorComercialOrigemCD");

				} else {
					SetorComercial objetoSetorComercial = (SetorComercial) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					// setorComercialOrigem
					associarTarifaConsumoImoveisActionForm
							.setSetorComercialOrigemCD(String
									.valueOf(objetoSetorComercial.getCodigo()));
					associarTarifaConsumoImoveisActionForm
							.setSetorComercialOrigemID(String
									.valueOf(objetoSetorComercial.getId()));
					associarTarifaConsumoImoveisActionForm
							.setNomeSetorComercialOrigem(objetoSetorComercial
									.getDescricao());
					// setorComercialOrigem

					// setorComercialDestino
					associarTarifaConsumoImoveisActionForm
							.setSetorComercialDestinoCD(String
									.valueOf(objetoSetorComercial.getCodigo()));
					associarTarifaConsumoImoveisActionForm
							.setSetorComercialDestinoID(String
									.valueOf(objetoSetorComercial.getId()));
					associarTarifaConsumoImoveisActionForm
							.setNomeSetorComercialDestino(objetoSetorComercial
									.getDescricao());
					// setorComercialDestino
					httpServletRequest.setAttribute("corSetorComercialOrigem",
							"valor");
					httpServletRequest.setAttribute("nomeCampo",
							"quadraOrigemNM");
				}
			} else {
				// Limpa o campo setorComercialOrigemCD do formul�rio
				associarTarifaConsumoImoveisActionForm
						.setSetorComercialOrigemCD("");
				associarTarifaConsumoImoveisActionForm
						.setNomeSetorComercialOrigem("Informe Localidade Inicial.");
				httpServletRequest.setAttribute("corSetorComercialOrigem",
						"exception");
				httpServletRequest.setAttribute("nomeCampo",
						"localidadeOrigemID");
			}
		} else {

			associarTarifaConsumoImoveisActionForm.setInscricaoTipo("destino");

			// Recebe o valor do campo localidadeDestinoID do formul�rio.
			localidadeID = (String) associarTarifaConsumoImoveisActionForm
					.getLocalidadeDestinoID();

			// O campo localidadeOrigem ser� obrigat�rio
			if (localidadeID != null
					&& !localidadeID.trim().equalsIgnoreCase("")) {

				setorComercialCD = (String) associarTarifaConsumoImoveisActionForm
						.getSetorComercialDestinoCD();

				// Adiciona o id da localidade que est� no formul�rio para
				// compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, localidadeID));

				// Adiciona o c�digo do setor comercial que esta no formul�rio
				// para compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						setorComercialCD));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna setorComercial
				colecaoPesquisa = fachada.pesquisar(filtroSetorComercial,
						SetorComercial.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Setor Comercial nao encontrado
					// Limpa os campos setorComercialDestinoCD,
					// nomeSetorComercialDestino e setorComercialDestinoID do
					// formul�rio
					associarTarifaConsumoImoveisActionForm
							.setSetorComercialDestinoCD("");
					associarTarifaConsumoImoveisActionForm
							.setSetorComercialDestinoID("");
					associarTarifaConsumoImoveisActionForm
							.setNomeSetorComercialDestino("Setor Comercial inexistente.");
					httpServletRequest.setAttribute("corSetorComercialDestino",
							"exception");
					httpServletRequest.setAttribute("nomeCampo",
							"setorComercialDestinoCD");
				} else {
					SetorComercial objetoSetorComercial = (SetorComercial) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					associarTarifaConsumoImoveisActionForm
							.setSetorComercialDestinoCD(String
									.valueOf(objetoSetorComercial.getCodigo()));
					associarTarifaConsumoImoveisActionForm
							.setSetorComercialDestinoID(String
									.valueOf(objetoSetorComercial.getId()));
					associarTarifaConsumoImoveisActionForm
							.setNomeSetorComercialDestino(objetoSetorComercial
									.getDescricao());
					httpServletRequest.setAttribute("corSetorComercialDestino",
							"valor");
					httpServletRequest.setAttribute("nomeCampo",
							"quadraDestinoNM");
				}
			} else {
				// Limpa o campo setorComercialDestinoCD do formul�rio
				associarTarifaConsumoImoveisActionForm
						.setSetorComercialDestinoCD("");
				associarTarifaConsumoImoveisActionForm
						.setNomeSetorComercialDestino("Informe Localidade Final.");
				httpServletRequest.setAttribute("corSetorComercialDestino",
						"exception");
				httpServletRequest.setAttribute("nomeCampo",
						"localidadeDestinoID");
			}
		}

	}

	public void pesquisarQuadra(
			String inscricaoTipo,
			AssociarTarifaConsumoImoveisActionForm associarTarifaConsumoImoveisActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		Collection colecaoPesquisa = null;

		String setorComercialCD = null;

		String setorComercialID = null;

		String quadraNM = null;

		FiltroQuadra filtroQuadra = new FiltroQuadra();

		// Objetos que ser�o retornados pelo hibernate.
		// filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("bairro");

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			associarTarifaConsumoImoveisActionForm.setInscricaoTipo("origem");
			// Recebe os valores dos campos setorComercialOrigemCD e
			// setorComercialOrigemID do formul�rio.
			setorComercialCD = (String) associarTarifaConsumoImoveisActionForm
					.getSetorComercialOrigemCD();

			setorComercialID = (String) associarTarifaConsumoImoveisActionForm
					.getSetorComercialOrigemID();

			// Os campos setorComercialOrigemCD e setorComercialID ser�o
			// obrigat�rios
			if (setorComercialCD != null
					&& !setorComercialCD.trim().equalsIgnoreCase("")
					&& setorComercialID != null
					&& !setorComercialID.trim().equalsIgnoreCase("")) {

				quadraNM = (String) associarTarifaConsumoImoveisActionForm
						.getQuadraOrigemNM();

				// Adiciona o id do setor comercial que est� no formul�rio para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_SETORCOMERCIAL, setorComercialID));

				// Adiciona o n�mero da quadra que esta no formul�rio para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.NUMERO_QUADRA, quadraNM));

				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna quadra
				colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class
						.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Quadra nao encontrada
					// Limpa os campos quadraOrigemNM e quadraOrigemID do
					// formul�rio

					// !!!!!!!!!!!!!!!!!!!!!!!!!!!!! N�O ENCONTRADA
					// !!!!!!!!!!!!!!!!!!!!!!!!
					// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

					associarTarifaConsumoImoveisActionForm
							.setQuadraOrigemNM("");
					associarTarifaConsumoImoveisActionForm
							.setQuadraOrigemID("");
					// Mensagem de tela
					associarTarifaConsumoImoveisActionForm
							.setQuadraMensagemOrigem("QUADRA INEXISTENTE.");
					httpServletRequest.setAttribute("corQuadraOrigem",
							"exception");
					httpServletRequest.setAttribute("nomeCampo",
							"quadraOrigemNM");
				} else {

					// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ENCONTRADA
					// !!!!!!!!!!!!!!!!!!!!!!!!!
					// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

					Quadra objetoQuadra = (Quadra) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					associarTarifaConsumoImoveisActionForm
							.setQuadraOrigemNM(String.valueOf(objetoQuadra
									.getNumeroQuadra()));
					associarTarifaConsumoImoveisActionForm
							.setQuadraOrigemID(String.valueOf(objetoQuadra
									.getId()));
					associarTarifaConsumoImoveisActionForm
							.setQuadraDestinoNM(String.valueOf(objetoQuadra
									.getNumeroQuadra()));
					associarTarifaConsumoImoveisActionForm
							.setQuadraDestinoID(String.valueOf(objetoQuadra
									.getId()));

					// httpServletRequest.setAttribute("corQuadraOrigem",
					// "valor");
					httpServletRequest.setAttribute("nomeCampo", "loteOrigem");
				}
			} else {
				// Limpa o campo quadraOrigemNM do formul�rio
				associarTarifaConsumoImoveisActionForm.setQuadraOrigemNM("");
				associarTarifaConsumoImoveisActionForm
						.setQuadraMensagemOrigem("Informe Setor Comercial Inicial.");
				httpServletRequest.setAttribute("corQuadraOrigem", "exception");
				httpServletRequest.setAttribute("nomeCampo",
						"setorComercialOrigemCD");
			}
		} else {

			associarTarifaConsumoImoveisActionForm.setInscricaoTipo("destino");

			// Recebe os valores dos campos setorComercialOrigemCD e
			// setorComercialOrigemID do formul�rio.
			setorComercialCD = (String) associarTarifaConsumoImoveisActionForm
					.getSetorComercialDestinoCD();
			setorComercialID = (String) associarTarifaConsumoImoveisActionForm
					.getSetorComercialDestinoID();

			// Os campos setorComercialOrigemCD e setorComercialID ser�o
			// obrigat�rios
			if (setorComercialCD != null
					&& !setorComercialCD.trim().equalsIgnoreCase("")
					&& setorComercialID != null
					&& !setorComercialID.trim().equalsIgnoreCase("")) {

				quadraNM = (String) associarTarifaConsumoImoveisActionForm
						.getQuadraDestinoNM();

				// Adiciona o id do setor comercial que est� no formul�rio para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_SETORCOMERCIAL, setorComercialID));

				// Adiciona o n�mero da quadra que esta no formul�rio para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.NUMERO_QUADRA, quadraNM));

				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna quadra
				colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class
						.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Quadra nao encontrada
					// Limpa os campos quadraOrigemNM e quadraOrigemID do
					// formul�rio

					// !!!!!!!!!!!!!!!!!!!!!!!!!!!!! N�O ENCONTRADA
					// !!!!!!!!!!!!!!!!!!!!!!!!
					// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					associarTarifaConsumoImoveisActionForm
							.setQuadraDestinoNM("");
					associarTarifaConsumoImoveisActionForm
							.setQuadraDestinoID("");
					// Mensagem de tela
					associarTarifaConsumoImoveisActionForm
							.setQuadraMensagemDestino("QUADRA INEXISTENTE.");
					httpServletRequest.setAttribute("corQuadraDestino",
							"exception");
					httpServletRequest.setAttribute("nomeCampo",
							"quadraDestinoNM");
				} else {
					// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ENCONTRADA
					// !!!!!!!!!!!!!!!!!!!!!!!!!
					// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

					Quadra objetoQuadra = (Quadra) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					associarTarifaConsumoImoveisActionForm
							.setQuadraDestinoNM(String.valueOf(objetoQuadra
									.getNumeroQuadra()));
					associarTarifaConsumoImoveisActionForm
							.setQuadraDestinoID(String.valueOf(objetoQuadra
									.getId()));
					// httpServletRequest
					// .setAttribute("corQuadraDestino", "valor");
					httpServletRequest.setAttribute("nomeCampo", "loteDestino");
				}
			} else {
				// Limpa o campo setorComercialOrigemCD do formul�rio
				associarTarifaConsumoImoveisActionForm.setQuadraDestinoNM("");
				// Mensagem de tela
				associarTarifaConsumoImoveisActionForm
						.setQuadraMensagemDestino("Informe Setor Comercial.");
				// httpServletRequest
				// .setAttribute("corQuadraDestino", "exception");
				httpServletRequest.setAttribute("nomeCampo",
						"setorComercialDestinoCD");
			}
		}

	}

}
