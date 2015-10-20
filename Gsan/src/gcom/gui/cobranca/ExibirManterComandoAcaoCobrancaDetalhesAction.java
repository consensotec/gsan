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
 * Anderson Italo Felinto de Lima
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
package gcom.gui.cobranca;

import gcom.atendimentopublico.ordemservico.FiltroFiscalizacaoSituacao;
import gcom.atendimentopublico.ordemservico.FiscalizacaoSituacao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.CobrancaAcaoAtividadeComandoFiscalizacaoSituacao;
import gcom.cobranca.CobrancaAtividade;
import gcom.cobranca.FiltroCobrancaAcaoAtividadeComandoFiscalizacaoSituacao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0244] Manter Comando de A��o de Conbran�a Visualiza os Dados do Comando
 * A��o Cobran�an para atualizar
 * 
 * @author Rafael Santos
 * @since 24/03/2006
 */
public class ExibirManterComandoAcaoCobrancaDetalhesAction extends GcomAction {

	private Collection colecaoPesquisa = null;

	private String localidadeID = null;

	// private String gerenciaRegionalID = null;

	private String setorComercialCD = null;

	private HttpSession sessao;

	/**
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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirManterComandoAcaoCobrancaDetalhes");

		// Mudar isso quando implementar a parte de seguran�a
		sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		String validarCriterio = (String) httpServletRequest
				.getParameter("validarCriterio");

		String validar = (String) httpServletRequest.getParameter("validar");

		String objetoConsulta = (String) httpServletRequest
				.getParameter("objetoConsulta");

		String inscricaoTipo = (String) httpServletRequest
				.getParameter("inscricaoTipo");

		String idCobrancaAcaoAtividadeComando = (String) httpServletRequest
				.getParameter("idCobrancaAcaoAtividadeComando");
		String cobrancaAcaoAtividadeComandoPesquisado = (String) httpServletRequest
				.getParameter("cobrancaAcaoAtividadeComandoPesquisado");

		ManterComandoAcaoCobrancaDetalhesActionForm manterComandoAcaoCobrancaDetalhesActionForm = (ManterComandoAcaoCobrancaDetalhesActionForm) actionForm;

		if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("")) {
			Integer consulta = Integer.parseInt(objetoConsulta);
			if (inscricaoTipo != null && !inscricaoTipo.trim().equalsIgnoreCase("")) {
				
				switch (consulta) {
				// Localidade
				case 1:
					pesquisarLocalidade(inscricaoTipo,
							manterComandoAcaoCobrancaDetalhesActionForm, fachada,
							httpServletRequest);
					break;
				// Setor Comercial
				case 2:
					pesquisarLocalidade(inscricaoTipo,
							manterComandoAcaoCobrancaDetalhesActionForm, fachada,
							httpServletRequest);
					pesquisarSetorComercial(inscricaoTipo,
							manterComandoAcaoCobrancaDetalhesActionForm, fachada,
							httpServletRequest);
					break;
				case 3: //Cliente
					pesquisarCliente(inscricaoTipo,
							manterComandoAcaoCobrancaDetalhesActionForm, fachada,
							httpServletRequest);
					break;			
					
				default:
					break;
				}
			}
			
			if (consulta.intValue()==5){ //Imovel
				pesquisarImovel(
						manterComandoAcaoCobrancaDetalhesActionForm,
						httpServletRequest);
			}
			
			return retorno;		
		}
		
				//Valores padr�es do sistema
				//=================================================================
				int periodoFinalConta = fachada.pesquisarParametrosDoSistema()
						.getAnoMesArrecadacao();
				
				
				Calendar calendario = Calendar.getInstance();

				calendario.add(Calendar.MONTH, -1);

				String ultimoDiaMesAnterior = "";
				ultimoDiaMesAnterior = calendario.getActualMaximum(Calendar.DAY_OF_MONTH)
						+ "";

				if (calendario.get(Calendar.MONTH) < 9) {
					ultimoDiaMesAnterior = ultimoDiaMesAnterior + "/0"
							+ (calendario.get(Calendar.MONTH) + 1);
				} else {
					ultimoDiaMesAnterior = ultimoDiaMesAnterior + "/"
							+ (calendario.get(Calendar.MONTH) + 1);
				}
				ultimoDiaMesAnterior = ultimoDiaMesAnterior + "/" + calendario.get(Calendar.YEAR);
				
				
//				manterComandoAcaoCobrancaDetalhesActionForm.setPeriodoFinalContaHidden(Util.formatarAnoMesParaMesAno(periodoFinalConta));
//				manterComandoAcaoCobrancaDetalhesActionForm.setPeriodoVencimentoContaFinalHidden(ultimoDiaMesAnterior);
//				manterComandoAcaoCobrancaDetalhesActionForm.setPeriodoFinalFiscalizacaoHidden(ultimoDiaMesAnterior);
				
				//=================================================================
				/*//Per�odo de Refer�ncia
				if(manterComandoAcaoCobrancaDetalhesActionForm.getPeriodoFinalConta() == null ||
						manterComandoAcaoCobrancaDetalhesActionForm.getPeriodoFinalConta().equals("")){
					manterComandoAcaoCobrancaDetalhesActionForm.setPeriodoFinalConta(
							Util.formatarAnoMesParaMesAno(periodoFinalConta));
				}
				
				//Per�odo de Vencimento das Contas
				if(manterComandoAcaoCobrancaDetalhesActionForm.getPeriodoVencimentoContaFinal() == null || 
					manterComandoAcaoCobrancaDetalhesActionForm.getPeriodoVencimentoContaFinal().equals("")){
					manterComandoAcaoCobrancaDetalhesActionForm.setPeriodoVencimentoContaFinal(ultimoDiaMesAnterior);
				}
				
				if(manterComandoAcaoCobrancaDetalhesActionForm.getPeriodoFinalFiscalizacao() == null || 
						manterComandoAcaoCobrancaDetalhesActionForm.getPeriodoFinalFiscalizacao().equals("")){
					manterComandoAcaoCobrancaDetalhesActionForm.setPeriodoFinalFiscalizacao(ultimoDiaMesAnterior);
				}*/
				
		
		// String idCobrancaAcao = manterComandoAcaoCobrancaDetalhesActionForm
		// .getCobrancaAcao();

		String idCobrancaAtividae = manterComandoAcaoCobrancaDetalhesActionForm
				.getCobrancaAtividade();
		
		
		CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando = null;
		
		//Faz o primeiro carregamento do ID
		if(idCobrancaAcaoAtividadeComando != null){
			
				manterComandoAcaoCobrancaDetalhesActionForm.setIdCobrancaAcaoAtividadeComandoHidden(idCobrancaAcaoAtividadeComando);
			
			// consultar o cobranca a��o atividade comando selecionada
			cobrancaAcaoAtividadeComando = fachada
					.consultarCobrancaAcaoAtividadeComando(manterComandoAcaoCobrancaDetalhesActionForm.getIdCobrancaAcaoAtividadeComandoHidden());
	
			// carregar os dados na tela da cobran�a a��o atividade comando
			if (cobrancaAcaoAtividadeComando != null) {
	
				manterComandoAcaoCobrancaDetalhesActionForm
						.setCobrancaAcao(cobrancaAcaoAtividadeComando
								.getCobrancaAcao().getId().toString());
				manterComandoAcaoCobrancaDetalhesActionForm
						.setCobrancaAtividade(cobrancaAcaoAtividadeComando
								.getCobrancaAtividade().getId().toString());
	
				// cobranca grupo
				if (cobrancaAcaoAtividadeComando.getCobrancaGrupo() != null) {
					manterComandoAcaoCobrancaDetalhesActionForm
							.setCobrancaGrupo(cobrancaAcaoAtividadeComando
									.getCobrancaGrupo().getId().toString());
				} else {
					manterComandoAcaoCobrancaDetalhesActionForm
							.setCobrancaGrupo("");
				}
				// gerencia regional
				if (cobrancaAcaoAtividadeComando.getGerenciaRegional() != null) {
					manterComandoAcaoCobrancaDetalhesActionForm
							.setGerenciaRegional(cobrancaAcaoAtividadeComando
									.getGerenciaRegional().getId().toString());
				} else {
					manterComandoAcaoCobrancaDetalhesActionForm
							.setGerenciaRegional("");
				}
				// unidade Negocio
				if (cobrancaAcaoAtividadeComando.getUnidadeNegocio() != null) {
					manterComandoAcaoCobrancaDetalhesActionForm
							.setUnidadeNegocio(cobrancaAcaoAtividadeComando
									.getUnidadeNegocio().getId().toString());
				} else {
					manterComandoAcaoCobrancaDetalhesActionForm
							.setUnidadeNegocio("");
				}
	
				// localidade inicial
				if (cobrancaAcaoAtividadeComando.getLocalidadeInicial() != null) {
					manterComandoAcaoCobrancaDetalhesActionForm
							.setLocalidadeOrigemID(cobrancaAcaoAtividadeComando
									.getLocalidadeInicial().getId().toString());
					FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
					filtroLocalidade.adicionarParametro(new ParametroSimples(
							FiltroLocalidade.ID, cobrancaAcaoAtividadeComando
									.getLocalidadeInicial().getId()));
					Collection colecaoLocalidadesIniciais = fachada.pesquisar(
							filtroLocalidade, Localidade.class.getName());
	
					Localidade localidadeInicial = (Localidade) colecaoLocalidadesIniciais
							.iterator().next();
					manterComandoAcaoCobrancaDetalhesActionForm
							.setNomeLocalidadeOrigem(localidadeInicial
									.getDescricao());
				} else {
					manterComandoAcaoCobrancaDetalhesActionForm
							.setLocalidadeOrigemID("");
					manterComandoAcaoCobrancaDetalhesActionForm
							.setNomeLocalidadeOrigem("");
				}
				// localidade final
				if (cobrancaAcaoAtividadeComando.getLocalidadeFinal() != null) {
					manterComandoAcaoCobrancaDetalhesActionForm
							.setLocalidadeDestinoID(cobrancaAcaoAtividadeComando
									.getLocalidadeFinal().getId().toString());
					FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
					filtroLocalidade.adicionarParametro(new ParametroSimples(
							FiltroLocalidade.ID, cobrancaAcaoAtividadeComando
									.getLocalidadeFinal().getId()));
					Collection colecaoLocalidadesFinais = fachada.pesquisar(
							filtroLocalidade, Localidade.class.getName());
	
					Localidade localidadeFinal = (Localidade) colecaoLocalidadesFinais
							.iterator().next();
					manterComandoAcaoCobrancaDetalhesActionForm
							.setNomeLocalidadeDestino(localidadeFinal
									.getDescricao());
				} else {
					manterComandoAcaoCobrancaDetalhesActionForm
							.setLocalidadeDestinoID("");
					manterComandoAcaoCobrancaDetalhesActionForm
							.setNomeLocalidadeDestino("");
				}
				// setor comercial inicial
				if (cobrancaAcaoAtividadeComando.getCodigoSetorComercialInicial() != null) {
					manterComandoAcaoCobrancaDetalhesActionForm
							.setSetorComercialOrigemCD(cobrancaAcaoAtividadeComando
									.getCodigoSetorComercialInicial().toString());
					FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
					filtroSetorComercial.adicionarParametro(new ParametroSimples(
							FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
							cobrancaAcaoAtividadeComando
									.getCodigoSetorComercialInicial().toString()));
					Collection colecaoSetorComercialIniciais = fachada.pesquisar(
							filtroSetorComercial, SetorComercial.class.getName());
	
					SetorComercial setorComercialInicial = (SetorComercial) colecaoSetorComercialIniciais
							.iterator().next();
					manterComandoAcaoCobrancaDetalhesActionForm
							.setNomeSetorComercialOrigem(setorComercialInicial
									.getDescricao());
				} else {
					manterComandoAcaoCobrancaDetalhesActionForm
							.setSetorComercialOrigemCD("");
					manterComandoAcaoCobrancaDetalhesActionForm
							.setNomeSetorComercialOrigem("");
				}
				// setor comercial final
				if (cobrancaAcaoAtividadeComando.getCodigoSetorComercialFinal() != null) {
					manterComandoAcaoCobrancaDetalhesActionForm
							.setSetorComercialDestinoCD(cobrancaAcaoAtividadeComando
									.getCodigoSetorComercialFinal().toString());
					FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
					filtroSetorComercial.adicionarParametro(new ParametroSimples(
							FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
							cobrancaAcaoAtividadeComando
									.getCodigoSetorComercialFinal().toString()));
					Collection colecaoSetorComercialFinais = fachada.pesquisar(
							filtroSetorComercial, SetorComercial.class.getName());
	
					SetorComercial setorComercialFinal = (SetorComercial) colecaoSetorComercialFinais
							.iterator().next();
					manterComandoAcaoCobrancaDetalhesActionForm
					.setNomeSetorComercialDestino(setorComercialFinal
									.getDescricao());
				} else {
					manterComandoAcaoCobrancaDetalhesActionForm
							.setSetorComercialDestinoCD("");
					manterComandoAcaoCobrancaDetalhesActionForm
							.setNomeSetorComercialOrigem("");
				}
				boolean carregou = false;
				// rota inicial
				if (cobrancaAcaoAtividadeComando.getRotaInicial() != null) {
					// carregarRota(manterComandoAcaoCobrancaDetalhesActionForm,fachada,cobrancaAcaoAtividadeComando.getCodigoSetorComercialInicial().toString());
					manterComandoAcaoCobrancaDetalhesActionForm
							.setRotaInicial(cobrancaAcaoAtividadeComando
									.getRotaInicial().getCodigo().toString());
					carregou = true;
				} else {
					manterComandoAcaoCobrancaDetalhesActionForm.setRotaInicial("");
					// sessao.setAttribute("colecaoRota", null);
					manterComandoAcaoCobrancaDetalhesActionForm
							.setRotaInicial(null);
				}
				
				// quadra inicial
				if (cobrancaAcaoAtividadeComando.getNumeroQuadraInicial() != null) {
					
					manterComandoAcaoCobrancaDetalhesActionForm.setNumeroQuadraInicial(cobrancaAcaoAtividadeComando.getNumeroQuadraInicial().toString());
					carregou = true;
				} else {
					manterComandoAcaoCobrancaDetalhesActionForm.setNumeroQuadraInicial(null);
				}
				
				// quadra final
				if (cobrancaAcaoAtividadeComando.getNumeroQuadraFinal() != null) {
					
					manterComandoAcaoCobrancaDetalhesActionForm.setNumeroQuadraFinal(cobrancaAcaoAtividadeComando.getNumeroQuadraFinal().toString());
					carregou = true;
				} else {
					manterComandoAcaoCobrancaDetalhesActionForm.setNumeroQuadraFinal(null);
				}
				
				// rota final
				if (cobrancaAcaoAtividadeComando.getRotaFinal() != null) {
					if (!carregou) {
						// carregarRota(manterComandoAcaoCobrancaDetalhesActionForm,fachada,cobrancaAcaoAtividadeComando.getCodigoSetorComercialFinal().toString());
					}
	
					manterComandoAcaoCobrancaDetalhesActionForm
							.setRotaFinal(cobrancaAcaoAtividadeComando
									.getRotaFinal().getCodigo().toString());
				} else {
					manterComandoAcaoCobrancaDetalhesActionForm.setRotaFinal("");
					// sessao.setAttribute("colecaoRota", null);
					manterComandoAcaoCobrancaDetalhesActionForm.setRotaFinal(null);
				}
				// cliente
				if (cobrancaAcaoAtividadeComando.getCliente() != null) {
					manterComandoAcaoCobrancaDetalhesActionForm
							.setIdCliente(cobrancaAcaoAtividadeComando.getCliente()
									.getId().toString());
					FiltroCliente filtroCliente = new FiltroCliente();
					filtroCliente.adicionarParametro(new ParametroSimples(
							FiltroCliente.ID, cobrancaAcaoAtividadeComando
									.getCliente().getId().toString()));
					Collection colecaoCliente = fachada.pesquisar(filtroCliente,
							Cliente.class.getName());
					Cliente cliente = (Cliente) colecaoCliente.iterator().next();
					manterComandoAcaoCobrancaDetalhesActionForm
							.setNomeCliente(cliente.getNome());
	
				} else {
					manterComandoAcaoCobrancaDetalhesActionForm.setNomeCliente("");
					manterComandoAcaoCobrancaDetalhesActionForm.setIdCliente("");
				}
				
				// cliente superior
				if (cobrancaAcaoAtividadeComando.getSuperior() != null) {
					manterComandoAcaoCobrancaDetalhesActionForm
							.setCodigoClienteSuperior(cobrancaAcaoAtividadeComando.getSuperior()
									.getId().toString());
					FiltroCliente filtroCliente = new FiltroCliente();
					filtroCliente.adicionarParametro(new ParametroSimples(
							FiltroCliente.ID, cobrancaAcaoAtividadeComando
									.getSuperior().getId().toString()));
					Collection colecaoCliente = fachada.pesquisar(filtroCliente,
							Cliente.class.getName());
					Cliente cliente = (Cliente) colecaoCliente.iterator().next();
					manterComandoAcaoCobrancaDetalhesActionForm
							.setNomeClienteSuperior(cliente.getNome());
	
				} else {
					manterComandoAcaoCobrancaDetalhesActionForm.setNomeClienteSuperior("");
					manterComandoAcaoCobrancaDetalhesActionForm.setCodigoClienteSuperior("");
				}
				
				// cliente relacao tipo
				if (cobrancaAcaoAtividadeComando.getClienteRelacaoTipo() != null) {
					manterComandoAcaoCobrancaDetalhesActionForm
							.setClienteRelacaoTipo(cobrancaAcaoAtividadeComando
									.getClienteRelacaoTipo().getId().toString());
				} else {
					manterComandoAcaoCobrancaDetalhesActionForm
							.setClienteRelacaoTipo("");
				}
				// ano mes conta inicial
				if (cobrancaAcaoAtividadeComando.getAnoMesReferenciaContaInicial() != null) {
					manterComandoAcaoCobrancaDetalhesActionForm
							.setPeriodoInicialConta(Util
									.formatarAnoMesParaMesAno(Util
											.adicionarZerosEsquedaNumero(
													6,
													cobrancaAcaoAtividadeComando
															.getAnoMesReferenciaContaInicial()
															.toString())
											+ ""));
				} else {
					manterComandoAcaoCobrancaDetalhesActionForm
							.setPeriodoInicialConta("");
				}
	
				// ano mes conta final
				if (cobrancaAcaoAtividadeComando.getAnoMesReferenciaContaFinal() != null) {
					manterComandoAcaoCobrancaDetalhesActionForm
							.setPeriodoFinalConta(Util
									.formatarAnoMesParaMesAno(Util
											.adicionarZerosEsquedaNumero(
													6,
													cobrancaAcaoAtividadeComando
															.getAnoMesReferenciaContaFinal()
															.toString())
											+ ""));
					manterComandoAcaoCobrancaDetalhesActionForm
					.setPeriodoFinalContaHidden(Util
							.formatarAnoMesParaMesAno(Util
									.adicionarZerosEsquedaNumero(
											6,
											cobrancaAcaoAtividadeComando
													.getAnoMesReferenciaContaFinal()
													.toString())
									+ ""));
				} else {
					if (cobrancaAcaoAtividadeComandoPesquisado != null
							&& cobrancaAcaoAtividadeComandoPesquisado
									.equals("true")) {
						manterComandoAcaoCobrancaDetalhesActionForm
								.setPeriodoFinalConta("");
					} else {
						// caso n�o esteja preenchido pelo registro, � preenchido
						// com o
						// dado do sistema
						manterComandoAcaoCobrancaDetalhesActionForm
								.setPeriodoFinalConta(fachada
										.consultarPeriodoFinalContaCobrancaAcaoAtividadeComando());
					}
				}
				// data vencimento conta inicial
				if (cobrancaAcaoAtividadeComando.getDataVencimentoContaInicial() != null) {
					manterComandoAcaoCobrancaDetalhesActionForm
							.setPeriodoVencimentoContaInicial(Util
									.formatarData(cobrancaAcaoAtividadeComando
											.getDataVencimentoContaInicial()));
				} else {
					if (cobrancaAcaoAtividadeComandoPesquisado != null
							&& cobrancaAcaoAtividadeComandoPesquisado
									.equals("true")) {
						manterComandoAcaoCobrancaDetalhesActionForm
								.setPeriodoVencimentoContaFinal("");
					} else {
						// caso n�o esteja preenchido pelo registro, � preenchido
						// com o
						// dado do sistema
						manterComandoAcaoCobrancaDetalhesActionForm
								.setPeriodoVencimentoContaFinal(fachada
										.consultarPeriodoVencimentoContaFinalCobrancaAcaoAtividadeComando());
					}
				}
				// data vencimento conta final
				if (cobrancaAcaoAtividadeComando.getDataVencimentoContaFinal() != null) {
					manterComandoAcaoCobrancaDetalhesActionForm
							.setPeriodoVencimentoContaFinal(Util
									.formatarData(cobrancaAcaoAtividadeComando
											.getDataVencimentoContaFinal()));
					manterComandoAcaoCobrancaDetalhesActionForm
					.setPeriodoVencimentoContaFinalHidden(Util
							.formatarData(cobrancaAcaoAtividadeComando
									.getDataVencimentoContaFinal()));
				} else {
					manterComandoAcaoCobrancaDetalhesActionForm
							.setPeriodoVencimentoContaFinal("");
				}
	
				if (cobrancaAcaoAtividadeComando.getCobrancaCriterio() != null) {
					cobrancaAcaoAtividadeComando
							.setCobrancaCriterio(cobrancaAcaoAtividadeComando
									.getCobrancaCriterio());
					sessao.setAttribute("cobrancaAcaoAtividadeComando",
							cobrancaAcaoAtividadeComando);
				}
	
				if (cobrancaAcaoAtividadeComando.getDescricaoTitulo() != null) {
					manterComandoAcaoCobrancaDetalhesActionForm
							.setTitulo(cobrancaAcaoAtividadeComando
									.getDescricaoTitulo());
				}
	
				if (cobrancaAcaoAtividadeComando.getDescricaoSolicitacao() != null) {
					manterComandoAcaoCobrancaDetalhesActionForm
							.setDescricaoSolicitacao(cobrancaAcaoAtividadeComando
									.getDescricaoSolicitacao());
				}
	
				if (cobrancaAcaoAtividadeComando.getQuantidadeDiasRealizacao() != null) {
					manterComandoAcaoCobrancaDetalhesActionForm.setPrazoExecucao(""
							+ cobrancaAcaoAtividadeComando
									.getQuantidadeDiasRealizacao());
	
				}
	
				if (cobrancaAcaoAtividadeComando.getQuantidadeMaximaDocumentos() != null) {
					manterComandoAcaoCobrancaDetalhesActionForm
							.setQuantidadeMaximaDocumentos(""
									+ cobrancaAcaoAtividadeComando
											.getQuantidadeMaximaDocumentos());
				}
				if (cobrancaAcaoAtividadeComando.getValorLimiteObrigatoria() != null) {
					manterComandoAcaoCobrancaDetalhesActionForm
							.setValorLimiteObrigatoria(""
									+ cobrancaAcaoAtividadeComando
											.getValorLimiteObrigatoria().toString().replace(".", ","));
				}
				if (cobrancaAcaoAtividadeComando.getIndicadorBoletim() != null) {
					manterComandoAcaoCobrancaDetalhesActionForm
							.setIndicadorGerarBoletimCadastro(""
									+ cobrancaAcaoAtividadeComando
											.getIndicadorBoletim());
				}
				if (cobrancaAcaoAtividadeComando.getIndicadorDebito() != null) {
					manterComandoAcaoCobrancaDetalhesActionForm
							.setIndicadorImoveisDebito(""
									+ cobrancaAcaoAtividadeComando
											.getIndicadorDebito());
				}
	
				if (cobrancaAcaoAtividadeComando.getRealizacao() != null) {
					manterComandoAcaoCobrancaDetalhesActionForm
							.setDataRealizacao(""
									+ cobrancaAcaoAtividadeComando.getRealizacao());
				}
				
				if(cobrancaAcaoAtividadeComando.getImovel() != null 
						&& cobrancaAcaoAtividadeComando.getImovel().getId()!=null ){
					// Validamos o imovel
					FiltroImovel filtroImovel = new FiltroImovel();
					filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, cobrancaAcaoAtividadeComando.getImovel().getId()));
					Collection<Imovel> colImovel = Fachada.getInstancia().pesquisar(filtroImovel,Imovel.class.getName());

					if (colImovel == null || colImovel.isEmpty()) {
						// O Imovel n�o existe
						manterComandoAcaoCobrancaDetalhesActionForm.setIdImovel("");
						manterComandoAcaoCobrancaDetalhesActionForm.setDescricaoImovel("Im�vel inexistente");
						httpServletRequest.setAttribute("matriculaInexistente","exception");
						httpServletRequest.setAttribute("nomeCampo","idImovel");
					} else {
						Imovel imovel = (Imovel) Util
								.retonarObjetoDeColecao(colImovel);
						String inscricaoImovel = Fachada.getInstancia().pesquisarInscricaoImovel(imovel.getId());
						manterComandoAcaoCobrancaDetalhesActionForm.setIdImovel(imovel.getId().toString());
						manterComandoAcaoCobrancaDetalhesActionForm.setDescricaoImovel(inscricaoImovel);
			            httpServletRequest.setAttribute("nomeCampo","periodoInicialConta");
					}
					
				}
				
				
	
			}
	
			// indicador de criterio
			if (cobrancaAcaoAtividadeComando != null
					&& cobrancaAcaoAtividadeComando.getIndicadorCriterio() != null) {
				if (cobrancaAcaoAtividadeComando.getIndicadorCriterio()
						.shortValue() == 1) {
					manterComandoAcaoCobrancaDetalhesActionForm
							.setCobrancaAtividadeIndicadorExecucao("1");
					// manterComandoAcaoCobrancaDetalhesActionForm.setIndicador("Rota");
				} else if (cobrancaAcaoAtividadeComando.getIndicadorCriterio()
						.shortValue() == 2) {
					manterComandoAcaoCobrancaDetalhesActionForm
							.setCobrancaAtividadeIndicadorExecucao("2");
					// manterComandoAcaoCobrancaDetalhesActionForm.setIndicador("Comando");
				}
			}
	
			String idComandoSelecionado = httpServletRequest
					.getParameter("idComandoSelecionado");
			if (idComandoSelecionado != null) {
				if (cobrancaAcaoAtividadeComando.getCobrancaCriterio() != null) {
					cobrancaAcaoAtividadeComando.getCobrancaCriterio().setId(
							new Integer(idComandoSelecionado));
					sessao.setAttribute("cobrancaAcaoAtividadeComando",
							cobrancaAcaoAtividadeComando);
				}
			}
			if(cobrancaAcaoAtividadeComando.getConsumoMedioInicial()!=null){
				manterComandoAcaoCobrancaDetalhesActionForm
					.setConsumoMedioInicial(cobrancaAcaoAtividadeComando.getConsumoMedioInicial().toString());
			}else{
				manterComandoAcaoCobrancaDetalhesActionForm
					.setConsumoMedioInicial("");
			}
			if(cobrancaAcaoAtividadeComando.getConsumoMedioFinal()!=null){
				manterComandoAcaoCobrancaDetalhesActionForm
					.setConsumoMedioFinal(cobrancaAcaoAtividadeComando.getConsumoMedioFinal().toString());
			}else{
				manterComandoAcaoCobrancaDetalhesActionForm
				.setConsumoMedioFinal("");
			}
			if(cobrancaAcaoAtividadeComando.getTipoConsumo()!=null){
				manterComandoAcaoCobrancaDetalhesActionForm
					.setTipoConsumo(cobrancaAcaoAtividadeComando.getTipoConsumo().toString());
			}else{
				manterComandoAcaoCobrancaDetalhesActionForm
					.setTipoConsumo("1");
			}
			
			if(cobrancaAcaoAtividadeComando.getPeriodoInicialFiscalizacao()!=null){
				manterComandoAcaoCobrancaDetalhesActionForm
					.setPeriodoInicialFiscalizacao(Util.formatarData(cobrancaAcaoAtividadeComando.getPeriodoInicialFiscalizacao()));
			}else{
				manterComandoAcaoCobrancaDetalhesActionForm
					.setPeriodoInicialFiscalizacao("");
			}
			
			if(cobrancaAcaoAtividadeComando.getPeriodoFinalFiscalizacao()!=null){
				manterComandoAcaoCobrancaDetalhesActionForm
					.setPeriodoFinalFiscalizacao(Util.formatarData(cobrancaAcaoAtividadeComando.getPeriodoFinalFiscalizacao()));
				manterComandoAcaoCobrancaDetalhesActionForm
				.setPeriodoFinalFiscalizacaoHidden(Util.formatarData(cobrancaAcaoAtividadeComando.getPeriodoFinalFiscalizacao()));
			}else{
				
				Calendar calendarNova = Calendar.getInstance();
	
				calendarNova.add(Calendar.MONTH, -1);
	
				String dataNova = "";
				dataNova = calendarNova.getActualMaximum(Calendar.DAY_OF_MONTH)
						+ "";
	
				if (calendarNova.get(Calendar.MONTH) < 9) {
					dataNova = dataNova + "/0"
							+ (calendarNova.get(Calendar.MONTH) + 1);
				} else {
					dataNova = dataNova + "/"
							+ (calendarNova.get(Calendar.MONTH) + 1);
				}
				dataNova = dataNova + "/" + calendarNova.get(Calendar.YEAR);
				
				
				manterComandoAcaoCobrancaDetalhesActionForm
				.setPeriodoFinalFiscalizacao(dataNova);
			}
			
			if (sessao.getAttribute("colecaoFiscalizacaoSituacao") == null) {
				
				FiltroFiscalizacaoSituacao filtroFiscalizacaoSituacao = new FiltroFiscalizacaoSituacao();
				
				filtroFiscalizacaoSituacao.setCampoOrderBy(FiltroFiscalizacaoSituacao.DESCRICAO);
				
				Collection colecaoFiscalizacaoSituacao = (Collection) fachada.pesquisar(
						filtroFiscalizacaoSituacao, FiscalizacaoSituacao.class.getName());
				if (colecaoFiscalizacaoSituacao != null && !colecaoFiscalizacaoSituacao.isEmpty()) {
					// carregar a��o de cobran�a
					sessao.setAttribute("colecaoFiscalizacaoSituacao", colecaoFiscalizacaoSituacao);
				}
			}
			
			FiltroCobrancaAcaoAtividadeComandoFiscalizacaoSituacao filtroCobrancaAcaoFisc
				= new FiltroCobrancaAcaoAtividadeComandoFiscalizacaoSituacao();
			
			filtroCobrancaAcaoFisc.adicionarParametro(
					new ParametroSimples(
						FiltroCobrancaAcaoAtividadeComandoFiscalizacaoSituacao.COBRANCA_ACAO_ATIVIDADE_COMANDO_ID,
						cobrancaAcaoAtividadeComando.getId()));
			
			Collection<CobrancaAcaoAtividadeComandoFiscalizacaoSituacao> colecaoCobrancaAcaoFisc =
				fachada.pesquisar(filtroCobrancaAcaoFisc, 
						CobrancaAcaoAtividadeComandoFiscalizacaoSituacao.class.getName());
			
			
			String[] fiscalizacaoSituacoes = null;
			if(!Util.isVazioOrNulo(colecaoCobrancaAcaoFisc)){
			
				fiscalizacaoSituacoes = new String[colecaoCobrancaAcaoFisc.size()];
				int cont = 0;
				
				for (CobrancaAcaoAtividadeComandoFiscalizacaoSituacao helper : colecaoCobrancaAcaoFisc) {
					
					fiscalizacaoSituacoes[cont] = helper.getFiscalizacaoSituacao().getId().toString();
					cont++;
					
				}
				
			}
			manterComandoAcaoCobrancaDetalhesActionForm.setSituacaoFiscalizacao(fiscalizacaoSituacoes);
			
			
			//Quantidade de Dias de Vencimento
			if(cobrancaAcaoAtividadeComando.getQuantidadeDiasVencimento() != null)
				manterComandoAcaoCobrancaDetalhesActionForm
							.setQuantidadeDiasVencimento(cobrancaAcaoAtividadeComando.getQuantidadeDiasVencimento().toString());
			
			//Rela��o de Im�veis
			//1.1.1.23.1. Caso exista um arquivo para o comando
			if(cobrancaAcaoAtividadeComando.getNomeArquivoRelacaoImoveis() != null 
					&& !cobrancaAcaoAtividadeComando.getNomeArquivoRelacaoImoveis().equals("")){
				
				//1.1.1.23.1.1. Nome do arquivo
				manterComandoAcaoCobrancaDetalhesActionForm
					.setNomeArquivoRelacaoImoveis(cobrancaAcaoAtividadeComando.getNomeArquivoRelacaoImoveis());
				
				//1.1.1.23.1.2. Lista com os im�veis informados
				Collection idsImoveisColecaoComandoAtivImoveis = fachada.obterImoveisComandoAtividadeImovel(null,cobrancaAcaoAtividadeComando.getId());
				
				if(idsImoveisColecaoComandoAtivImoveis != null && idsImoveisColecaoComandoAtivImoveis.size() > 0){
						sessao.setAttribute("idsImoveisColecaoComandoAtivImoveis", idsImoveisColecaoComandoAtivImoveis);
				}
			}		
		}
		
		// valdiar os criteorios de rota e comando para o usu�rio selecionar
		if (validarCriterio != null && !validarCriterio.equals("")) {
			
			//Limpa campo Prazo de execu��o
			manterComandoAcaoCobrancaDetalhesActionForm.setPrazoExecucao("");
			
			// validar a atividade selecionada
			if (validar != null && validar.equals("Atividade")) {
				if (idCobrancaAtividae != null
						&& !idCobrancaAtividae.equals("")) {

					CobrancaAtividade cobrancaAtividade = fachada
							.obterCobrancaAtividade(idCobrancaAtividae);

					if (cobrancaAtividade != null) {
						if (cobrancaAtividade.getIndicadorExecucao() != null) {
							manterComandoAcaoCobrancaDetalhesActionForm
									.setCobrancaAtividadeIndicadorExecucao(cobrancaAtividade
											.getIndicadorExecucao().toString());
						} else {
							manterComandoAcaoCobrancaDetalhesActionForm
									.setCobrancaAtividadeIndicadorExecucao("");
						}
					} else {
						manterComandoAcaoCobrancaDetalhesActionForm
								.setCobrancaAtividadeIndicadorExecucao("");
					}
				}
			}
		}

		// CARREGAR AS COBRAN�AS GRUPO
		if (sessao.getAttribute("colecaoCobrancaGrupo") == null) {
			sessao.setAttribute("colecaoCobrancaGrupo", fachada
					.obterColecaoCobrancaGrupo());
		}

		// CARREGAR AS COBRAN�AS ATIVIDADE
		if (sessao.getAttribute("colecaoCobrancaAtividade") == null) {
			sessao.setAttribute("colecaoCobrancaAtividade", fachada
					.obterColecaoCobrancaAtividade());
		}

		// CARREGAR AS COBRAN�AS ACAO
		if (sessao.getAttribute("colecaoCobrancaAcao") == null) {
			sessao.setAttribute("colecaoCobrancaAcao", fachada
					.obterColecaoCobrancaAcaoEventual());
		}

		// CARREGAR AS GERENCIAIS REGIONAIS
		if (sessao.getAttribute("colecaoGerenciaRegional") == null) {
			sessao.setAttribute("colecaoGerenciaRegional", fachada
					.obterColecaoGerenciaRegional());
		}

		// CARREGAR AS UNIDADES NEGOCIOS
		if (sessao.getAttribute("colecaoUnidadeNegocio") == null) {
			sessao.setAttribute("colecaoUnidadeNegocio", fachada
					.obterColecaoUnidadeNegocio());
		}

		// CARREGAR OS CLIENTE RELACAO TIPO
		if (sessao.getAttribute("colecaoClienteRelacaoTipo") == null) {
			sessao.setAttribute("colecaoClienteRelacaoTipo", fachada
					.obterColecaoClienteRelacaoTipo());
		}

		if (cobrancaAcaoAtividadeComando != null) {
			sessao.setAttribute("cobrancaAcaoAtividadeComando",
					cobrancaAcaoAtividadeComando);
		}

		return retorno;
	}

	/**
	 * Pesquisa o Imovel 
	 */
	private void pesquisarImovel(ManterComandoAcaoCobrancaDetalhesActionForm form,
			HttpServletRequest httpServletRequest) {
		
		// Validamos o imovel
		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, Integer.parseInt(form.getIdImovel())));
		Collection<Imovel> colImovel = Fachada.getInstancia().pesquisar(filtroImovel,Imovel.class.getName());

		if (colImovel == null || colImovel.isEmpty()) {
			// O Imovel n�o existe
			form.setIdImovel("");
			form.setDescricaoImovel("Im�vel inexistente");
			httpServletRequest.setAttribute("matriculaInexistente","exception");
			httpServletRequest.setAttribute("nomeCampo","idImovel");
		} else {
			Imovel imovel = (Imovel) Util
					.retonarObjetoDeColecao(colImovel);
			String inscricaoImovel = Fachada.getInstancia().pesquisarInscricaoImovel(imovel.getId());
            form.setIdImovel(imovel.getId().toString());
            form.setDescricaoImovel(inscricaoImovel);
            httpServletRequest.setAttribute("nomeCampo","periodoInicialConta");
		}
		
	}
	
	/**
	 * Pesquisa a Localidade
	 * 
	 * @param inscricaoTipo
	 * @param imovelOutrosCriteriosActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	private void pesquisarLocalidade(
			String inscricaoTipo,
			ManterComandoAcaoCobrancaDetalhesActionForm manterComandoAcaoCobrancaDetalhesActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			manterComandoAcaoCobrancaDetalhesActionForm
					.setInscricaoTipo("origem");
			// Recebe o valor do campo localidadeOrigemID do formul�rio.
			localidadeID = (String) manterComandoAcaoCobrancaDetalhesActionForm
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
				manterComandoAcaoCobrancaDetalhesActionForm
						.setLocalidadeOrigemID("");
				manterComandoAcaoCobrancaDetalhesActionForm
						.setNomeLocalidadeOrigem("Localidade Inexistente");
				httpServletRequest.setAttribute("corLocalidadeOrigem",
						"exception");
			} else {
				Localidade objetoLocalidade = (Localidade) Util
						.retonarObjetoDeColecao(colecaoPesquisa);
				manterComandoAcaoCobrancaDetalhesActionForm
						.setLocalidadeOrigemID(String.valueOf(objetoLocalidade
								.getId()));
				manterComandoAcaoCobrancaDetalhesActionForm
						.setNomeLocalidadeOrigem(objetoLocalidade
								.getDescricao());
				httpServletRequest.setAttribute("corLocalidadeOrigem", "valor");

				String localidadeDestinoID = (String) manterComandoAcaoCobrancaDetalhesActionForm
						.getLocalidadeDestinoID();
				// verifica o valor das localidades, origem e final
				if (localidadeDestinoID != null) {

					if (localidadeDestinoID.equals("")) {
						manterComandoAcaoCobrancaDetalhesActionForm
								.setLocalidadeDestinoID(String
										.valueOf(objetoLocalidade.getId()));
						manterComandoAcaoCobrancaDetalhesActionForm
								.setNomeLocalidadeDestino(objetoLocalidade
										.getDescricao());
					} else {
						int localidadeDestino = new Integer(localidadeDestinoID)
								.intValue();
						int localidadeOrigem = objetoLocalidade.getId()
								.intValue();
						if (localidadeOrigem > localidadeDestino) {
							manterComandoAcaoCobrancaDetalhesActionForm
									.setLocalidadeDestinoID(String
											.valueOf(objetoLocalidade.getId()));
							manterComandoAcaoCobrancaDetalhesActionForm
									.setNomeLocalidadeDestino(objetoLocalidade
											.getDescricao());
						}
					}
				}
			}
		} else {
			// Recebe o valor do campo localidadeDestinoID do formul�rio.
			localidadeID = (String) manterComandoAcaoCobrancaDetalhesActionForm
					.getLocalidadeDestinoID();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, localidadeID));

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna localidade
			colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			manterComandoAcaoCobrancaDetalhesActionForm
					.setInscricaoTipo("destino");

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Localidade nao encontrada
				// Limpa os campos localidadeDestinoID e nomeLocalidadeDestino
				// do formul�rio
				manterComandoAcaoCobrancaDetalhesActionForm
						.setLocalidadeDestinoID("");
				manterComandoAcaoCobrancaDetalhesActionForm
						.setNomeLocalidadeDestino("Localidade inexistente");
				httpServletRequest.setAttribute("corLocalidadeDestino",
						"exception");
			} else {
				Localidade objetoLocalidade = (Localidade) Util
						.retonarObjetoDeColecao(colecaoPesquisa);

				int localidadeDestino = objetoLocalidade.getId().intValue();

				String localidade = (String) manterComandoAcaoCobrancaDetalhesActionForm
						.getLocalidadeOrigemID();

				if (localidade != null && !localidade.equals("")) {

					int localidadeOrigem = new Integer(localidade).intValue();
					if (localidadeDestino < localidadeOrigem) {
						manterComandoAcaoCobrancaDetalhesActionForm
								.setLocalidadeDestinoID("");
						manterComandoAcaoCobrancaDetalhesActionForm
								.setNomeLocalidadeDestino("Loc. Final maior que a Inicial");
						httpServletRequest.setAttribute("corLocalidadeDestino",
								"exception");

					} else {
						manterComandoAcaoCobrancaDetalhesActionForm
								.setLocalidadeDestinoID(String
										.valueOf(objetoLocalidade.getId()));
						manterComandoAcaoCobrancaDetalhesActionForm
								.setNomeLocalidadeDestino(objetoLocalidade
										.getDescricao());
						httpServletRequest.setAttribute("corLocalidadeDestino",
								"valor");
					}
				} else {
					manterComandoAcaoCobrancaDetalhesActionForm
							.setLocalidadeDestinoID(String
									.valueOf(objetoLocalidade.getId()));
					manterComandoAcaoCobrancaDetalhesActionForm
							.setNomeLocalidadeDestino(objetoLocalidade
									.getDescricao());
					httpServletRequest.setAttribute("corLocalidadeDestino",
							"valor");
				}
			}
		}

	}

	/**
	 * Pesquisa o Setor Comercial
	 * 
	 * @param inscricaoTipo
	 * @param imovelOutrosCriteriosActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	private void pesquisarSetorComercial(
			String inscricaoTipo,
			ManterComandoAcaoCobrancaDetalhesActionForm manterComandoAcaoCobrancaDetalhesActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			manterComandoAcaoCobrancaDetalhesActionForm
					.setInscricaoTipo("origem");
			// Recebe o valor do campo localidadeOrigemID do formul�rio.
			localidadeID = (String) manterComandoAcaoCobrancaDetalhesActionForm
					.getLocalidadeOrigemID();

			// O campo localidadeOrigemID ser� obrigat�rio
			if (localidadeID != null
					&& !localidadeID.trim().equalsIgnoreCase("")) {

				setorComercialCD = (String) manterComandoAcaoCobrancaDetalhesActionForm
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
					manterComandoAcaoCobrancaDetalhesActionForm
							.setSetorComercialOrigemCD("");
					manterComandoAcaoCobrancaDetalhesActionForm
							.setSetorComercialOrigemID("");
					manterComandoAcaoCobrancaDetalhesActionForm
							.setNomeSetorComercialOrigem("Setor inexistente");
					httpServletRequest.setAttribute("corSetorComercialOrigem",
							"exception");
					manterComandoAcaoCobrancaDetalhesActionForm
							.setRotaInicial(null);
					manterComandoAcaoCobrancaDetalhesActionForm
							.setRotaFinal(null);

				} else {
					SetorComercial objetoSetorComercial = (SetorComercial) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					// setorComercialID =
					// objetoSetorComercial.getId().toString();
					// setorComercialOrigem
					manterComandoAcaoCobrancaDetalhesActionForm
							.setSetorComercialOrigemCD(String
									.valueOf(objetoSetorComercial.getCodigo()));
					manterComandoAcaoCobrancaDetalhesActionForm
							.setSetorComercialOrigemID(String
									.valueOf(objetoSetorComercial.getId()));
					manterComandoAcaoCobrancaDetalhesActionForm
							.setNomeSetorComercialOrigem(objetoSetorComercial
									.getDescricao());
					httpServletRequest.setAttribute("corSetorComercialOrigem",
							"valor");

					String setorComercialDestinoCD = (String) manterComandoAcaoCobrancaDetalhesActionForm
							.getSetorComercialDestinoCD();

					// verifica o valor dos setores comerciais, origem e final
					if (setorComercialDestinoCD != null) {

						if (setorComercialDestinoCD.equals("")) {

							// setorComercialDestino
							manterComandoAcaoCobrancaDetalhesActionForm
									.setSetorComercialDestinoCD(String
											.valueOf(objetoSetorComercial
													.getCodigo()));
							manterComandoAcaoCobrancaDetalhesActionForm
									.setSetorComercialDestinoID(String
											.valueOf(objetoSetorComercial
													.getId()));
							manterComandoAcaoCobrancaDetalhesActionForm
									.setNomeSetorComercialDestino(objetoSetorComercial
											.getDescricao());

							Collection colecaRotas = fachada
									.obterColecaoRota(objetoSetorComercial
											.getId().toString());

							sessao.setAttribute("colecaoRota", colecaRotas);
							/*manterComandoAcaoCobrancaDetalhesActionForm
									.setRotaInicial("rota");
							manterComandoAcaoCobrancaDetalhesActionForm
									.setRotaFinal("rota");*/
						} else {

							int setorDestino = new Integer(
									setorComercialDestinoCD).intValue();
							int setorOrigem = objetoSetorComercial.getCodigo();
							if (setorOrigem > setorDestino) {

								// setorComercialDestino
								manterComandoAcaoCobrancaDetalhesActionForm
										.setSetorComercialDestinoCD(String
												.valueOf(objetoSetorComercial
														.getCodigo()));
								manterComandoAcaoCobrancaDetalhesActionForm
										.setSetorComercialDestinoID(String
												.valueOf(objetoSetorComercial
														.getId()));
								manterComandoAcaoCobrancaDetalhesActionForm
										.setNomeSetorComercialDestino(objetoSetorComercial
												.getDescricao());

								Collection colecaRotas = fachada
										.obterColecaoRota(objetoSetorComercial
												.getId().toString());

								sessao.setAttribute("colecaoRota", colecaRotas);
								/*manterComandoAcaoCobrancaDetalhesActionForm
										.setRotaInicial("rota");
								manterComandoAcaoCobrancaDetalhesActionForm
										.setRotaFinal("rota");*/
							}
						}
					}
				}
			} else {
				// Limpa o campo setorComercialOrigemCD do formul�rio
				manterComandoAcaoCobrancaDetalhesActionForm
						.setSetorComercialOrigemCD("");
				manterComandoAcaoCobrancaDetalhesActionForm
						.setNomeSetorComercialOrigem("Informe a localidade da inscri��o de origem.");
				httpServletRequest.setAttribute("corSetorComercialOrigem",
						"exception");
			}
		} else {

			manterComandoAcaoCobrancaDetalhesActionForm
					.setInscricaoTipo("destino");

			// Recebe o valor do campo localidadeDestinoID do formul�rio.
			localidadeID = (String) manterComandoAcaoCobrancaDetalhesActionForm
					.getLocalidadeDestinoID();

			// O campo localidadeOrigem ser� obrigat�rio
			if (localidadeID != null
					&& !localidadeID.trim().equalsIgnoreCase("")) {

				setorComercialCD = (String) manterComandoAcaoCobrancaDetalhesActionForm
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
					manterComandoAcaoCobrancaDetalhesActionForm
							.setSetorComercialDestinoCD("");
					manterComandoAcaoCobrancaDetalhesActionForm
							.setSetorComercialDestinoID("");
					manterComandoAcaoCobrancaDetalhesActionForm
							.setNomeSetorComercialDestino("Setor comercial inexistente.");
					httpServletRequest.setAttribute("corSetorComercialDestino",
							"exception");
					manterComandoAcaoCobrancaDetalhesActionForm
							.setRotaInicial(null);
					manterComandoAcaoCobrancaDetalhesActionForm
							.setRotaFinal(null);

				} else {
					SetorComercial objetoSetorComercial = (SetorComercial) Util
							.retonarObjetoDeColecao(colecaoPesquisa);

					int setorDestino = objetoSetorComercial.getCodigo();

					String setor = (String) manterComandoAcaoCobrancaDetalhesActionForm
							.getSetorComercialOrigemCD();

					if (setor != null && !setor.equals("")) {

						int setorOrigem = new Integer(setor).intValue();
						if (setorDestino < setorOrigem) {

							manterComandoAcaoCobrancaDetalhesActionForm
									.setSetorComercialDestinoCD("");
							manterComandoAcaoCobrancaDetalhesActionForm
									.setSetorComercialDestinoID("");
							manterComandoAcaoCobrancaDetalhesActionForm
									.setNomeSetorComercialDestino("Setor Final maior que Inicial");
							httpServletRequest.setAttribute(
									"corSetorComercialDestino", "exception");
							manterComandoAcaoCobrancaDetalhesActionForm
									.setRotaInicial(null);
							manterComandoAcaoCobrancaDetalhesActionForm
									.setRotaFinal(null);

						} else {
							// rota
							Collection colecaRotas = fachada
									.obterColecaoRota(objetoSetorComercial
											.getId().toString());

							sessao.setAttribute("colecaoRota", colecaRotas);
							/*manterComandoAcaoCobrancaDetalhesActionForm
									.setRotaInicial("rota");
							manterComandoAcaoCobrancaDetalhesActionForm
									.setRotaFinal("rota");*/

							// setor comercial destino
							manterComandoAcaoCobrancaDetalhesActionForm
									.setSetorComercialDestinoCD(String
											.valueOf(objetoSetorComercial
													.getCodigo()));
							manterComandoAcaoCobrancaDetalhesActionForm
									.setSetorComercialDestinoID(String
											.valueOf(objetoSetorComercial
													.getId()));
							manterComandoAcaoCobrancaDetalhesActionForm
									.setNomeSetorComercialDestino(objetoSetorComercial
											.getDescricao());
							httpServletRequest.setAttribute(
									"corSetorComercialDestino", "valor");
						}
					} else {

						Collection colecaRotas = fachada
								.obterColecaoRota(objetoSetorComercial.getId()
										.toString());

						sessao.setAttribute("colecaoRota", colecaRotas);
						/*manterComandoAcaoCobrancaDetalhesActionForm
								.setRotaInicial("rota");
						manterComandoAcaoCobrancaDetalhesActionForm
								.setRotaFinal("rota");*/
						
						// setor comercial destino
						manterComandoAcaoCobrancaDetalhesActionForm
								.setSetorComercialDestinoCD(String
										.valueOf(objetoSetorComercial
												.getCodigo()));
						manterComandoAcaoCobrancaDetalhesActionForm
								.setSetorComercialDestinoID(String
										.valueOf(objetoSetorComercial.getId()));
						manterComandoAcaoCobrancaDetalhesActionForm
								.setNomeSetorComercialDestino(objetoSetorComercial
										.getDescricao());
						httpServletRequest.setAttribute(
								"corSetorComercialDestino", "valor");
					}
				}
			} else {
				// Limpa o campo setorComercialDestinoCD do formul�rio
				manterComandoAcaoCobrancaDetalhesActionForm
						.setSetorComercialDestinoCD("");
				manterComandoAcaoCobrancaDetalhesActionForm
						.setNomeSetorComercialDestino("Informe a localidade da inscri��o de destino.");
				httpServletRequest.setAttribute("corSetorComercialDestino",
						"exception");
			}
		}

	}

	/**
	 * Pesquisa o Setor Comercial
	 * 
	 * @param inscricaoTipo
	 * @param imovelOutrosCriteriosActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	private void pesquisarCliente(
			String inscricaoTipo,
			ManterComandoAcaoCobrancaDetalhesActionForm manterComandoAcaoCobrancaDetalhesActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		String idCliente = null;
		if (inscricaoTipo != null && inscricaoTipo.equals("superior")) {
			idCliente = manterComandoAcaoCobrancaDetalhesActionForm
					.getCodigoClienteSuperior();
		} else {
			idCliente = manterComandoAcaoCobrancaDetalhesActionForm
					.getIdCliente();
		}

		// -------Parte que trata do c�digo quando o usu�rio tecla enter
		// se o id do cliente for diferente de nulo
		if (idCliente != null
				&& !idCliente.toString().trim().equalsIgnoreCase("")) {

			FiltroCliente filtroCliente = new FiltroCliente();
			Collection clientes = null;
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, new Integer(idCliente)));
			clientes = fachada
					.pesquisar(filtroCliente, Cliente.class.getName());
			if (clientes != null && !clientes.isEmpty()) {
				// O cliente foi encontrado
				if (inscricaoTipo != null && inscricaoTipo.equals("superior")) {
					manterComandoAcaoCobrancaDetalhesActionForm
							.setCodigoClienteSuperior(((Cliente) ((List) clientes)
									.get(0)).getId().toString());
					manterComandoAcaoCobrancaDetalhesActionForm
							.setNomeClienteSuperior(((Cliente) ((List) clientes)
									.get(0)).getNome());
				} else {
					manterComandoAcaoCobrancaDetalhesActionForm
							.setIdCliente(((Cliente) ((List) clientes).get(0))
									.getId().toString());
					manterComandoAcaoCobrancaDetalhesActionForm
							.setNomeCliente(((Cliente) ((List) clientes).get(0))
									.getNome());
				}

				Cliente cliente = new Cliente();

				cliente = (Cliente) clientes.iterator().next();
				sessao.setAttribute("clienteObj", cliente);
			} else {
				if (inscricaoTipo != null && inscricaoTipo.equals("superior")) {
					httpServletRequest.setAttribute(
							"codigoClienteSuperiorNaoEncontrado", "true");
					manterComandoAcaoCobrancaDetalhesActionForm
							.setNomeClienteSuperior("");
					manterComandoAcaoCobrancaDetalhesActionForm
					.setCodigoClienteSuperior("");
			
					httpServletRequest.setAttribute("nomeCampo",
							"codigoClienteSuperior");
				} else {
					httpServletRequest.setAttribute(
							"codigoClienteNaoEncontrado", "true");
					manterComandoAcaoCobrancaDetalhesActionForm
							.setNomeCliente("");
					manterComandoAcaoCobrancaDetalhesActionForm
					.setIdCliente("");
					httpServletRequest.setAttribute("nomeCampo", "idCliente");
				}
			}

		}

	}

	/**
	 * Inicializa a Rota
	 * 
	 * @param inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
	 * @param fachada
	 * @param objetoSetorComercial
	 */
	public void carregarRota(
			ManterComandoAcaoCobrancaDetalhesActionForm manterComandoAcaoCobrancaDetalhesActionForm,
			Fachada fachada, String codigoSetorComercial) {

		FiltroRota filtroRota = new FiltroRota();
		filtroRota.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
		filtroRota.adicionarParametro(new ParametroSimples(
				FiltroRota.SETOR_COMERCIAL_CODIGO, codigoSetorComercial));
		Collection colecaoRota = (Collection) fachada.pesquisar(filtroRota,
				Rota.class.getName());
		sessao.setAttribute("colecaoRota", colecaoRota);
		manterComandoAcaoCobrancaDetalhesActionForm.setRotaInicial("rota");
		manterComandoAcaoCobrancaDetalhesActionForm.setRotaFinal("rota");

	}

}
