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
package gsan.gui.atendimentopublico.registroatendimento;

import gsan.atendimentopublico.ordemservico.FiltroServicoTipo;
import gsan.atendimentopublico.ordemservico.ServicoTipo;
import gsan.atendimentopublico.registroatendimento.EspecificacaoImovelSituacao;
import gsan.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gsan.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gsan.atendimentopublico.registroatendimento.bean.SolicitacaoEspecificacaoHelper;
import gsan.cadastro.unidade.FiltroUnidadeOrganizacional;
import gsan.cadastro.unidade.UnidadeOrganizacional;
import gsan.fachada.Fachada;
import gsan.faturamento.debito.DebitoTipo;
import gsan.faturamento.debito.FiltroDebitoTipo;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action respons�vel pela pre-exibi��o da pagina de inserir bairro
 * 
 * @author S�vio Luiz
 * @created 28 de Julho de 2006
 */
public class AdicionarSolicitacaoEspecificacaoAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("adicionarSolicitacaoEspecificacao");

		Collection colecaoSolicitacaoTipoEspecificacao = null;
		if (sessao.getAttribute("colecaoSolicitacaoTipoEspecificacao") == null) {
			colecaoSolicitacaoTipoEspecificacao = new ArrayList();
		} else {
			colecaoSolicitacaoTipoEspecificacao = (Collection) sessao
					.getAttribute("colecaoSolicitacaoTipoEspecificacao");
		}

		AdicionarSolicitacaoEspecificacaoActionForm adicionarSolicitacaoEspecificacaoActionForm = (AdicionarSolicitacaoEspecificacaoActionForm) actionForm;

		// Obt�m a inst�ncia da Fachada
		Fachada fachada = Fachada.getInstancia();

		// seta os campos do form no objeto SolicitacaoTipoEspecificacao
		SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = new SolicitacaoTipoEspecificacao();

		solicitacaoTipoEspecificacao.setIndicadorSolicitante(new Short("1"));

		if (adicionarSolicitacaoEspecificacaoActionForm
				.getPrazoPrevisaoAtendimento() != null
				&& !adicionarSolicitacaoEspecificacaoActionForm
						.getPrazoPrevisaoAtendimento().equals("")) {
			// Prazo de previs�o de atendimento
			solicitacaoTipoEspecificacao.setDiasPrazo(new Integer(
					adicionarSolicitacaoEspecificacaoActionForm
							.getPrazoPrevisaoAtendimento()));
		}
		// Descri��o da especifica��o
		solicitacaoTipoEspecificacao
				.setDescricao(adicionarSolicitacaoEspecificacaoActionForm
						.getDescricaoSolicitacao());

		// Pavimento de cal�ada obrigat�rio
		if (adicionarSolicitacaoEspecificacaoActionForm
				.getIndicadorPavimentoCalcada() != null
				&& !adicionarSolicitacaoEspecificacaoActionForm
						.getIndicadorPavimentoCalcada().equals("")) {
			solicitacaoTipoEspecificacao
					.setIndicadorPavimentoCalcada(new Short(
							adicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorPavimentoCalcada()));
		}
		// Pavimento de rua obrigat�rio
		if (adicionarSolicitacaoEspecificacaoActionForm
				.getIndicadorPavimentoRua() != null
				&& !adicionarSolicitacaoEspecificacaoActionForm
						.getIndicadorPavimentoRua().equals("")) {
			solicitacaoTipoEspecificacao.setIndicadorPavimentoRua(new Short(
					adicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorPavimentoRua()));
		}

		// refere-se a liga��o de agua
		if (adicionarSolicitacaoEspecificacaoActionForm
				.getIndicadorLigacaoAgua() != null
				&& !adicionarSolicitacaoEspecificacaoActionForm
						.getIndicadorLigacaoAgua().equals("")) {
			solicitacaoTipoEspecificacao.setIndicadorLigacaoAgua(new Short(
					adicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorLigacaoAgua()));
		}
		
		// refere-se a Po�o
		if (adicionarSolicitacaoEspecificacaoActionForm
				.getIndicadorPoco() != null
				&& !adicionarSolicitacaoEspecificacaoActionForm
						.getIndicadorPoco().equals("")) {
			solicitacaoTipoEspecificacao.setIndicadorPoco(new Short(
					adicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorPoco()));
		}
		
		// refere-se a liga��o de esgoto
				if (adicionarSolicitacaoEspecificacaoActionForm
						.getIndicadorLigacaoEsgoto() != null
						&& !adicionarSolicitacaoEspecificacaoActionForm
								.getIndicadorLigacaoEsgoto().equals("")) {
					solicitacaoTipoEspecificacao.setIndicadorLigacaoEsgoto(new Short(
							adicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorLigacaoEsgoto()));
				}
		
		// Cobran�a de material
		if (adicionarSolicitacaoEspecificacaoActionForm
				.getIndicadorCobrancaMaterial() != null
				&& !adicionarSolicitacaoEspecificacaoActionForm
						.getIndicadorCobrancaMaterial().equals("")) {
			solicitacaoTipoEspecificacao
					.setIndicadorCobrancaMaterial(new Integer(
							adicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorCobrancaMaterial()));
		}
		// Matricula do im�vel obrigat�rio
		if (adicionarSolicitacaoEspecificacaoActionForm
				.getIndicadorMatriculaImovel() != null
				&& !adicionarSolicitacaoEspecificacaoActionForm
						.getIndicadorMatriculaImovel().equals("")) {
			solicitacaoTipoEspecificacao.setIndicadorMatricula(new Integer(
					adicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorMatriculaImovel()));
		}

		// Indicador urgencia
		if (adicionarSolicitacaoEspecificacaoActionForm.getIndicadorUrgencia() != null
				&& !adicionarSolicitacaoEspecificacaoActionForm
						.getIndicadorUrgencia().equals("")) {
			solicitacaoTipoEspecificacao.setIndicadorUrgencia(new Short(
					adicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorUrgencia()));
		}

		// Parecer de encerramento obrigat�rio
		if (adicionarSolicitacaoEspecificacaoActionForm
				.getIndicadorParecerEncerramento() != null
				&& !adicionarSolicitacaoEspecificacaoActionForm
						.getIndicadorParecerEncerramento().equals("")) {
			solicitacaoTipoEspecificacao
					.setIndicadorParecerEncerramento(new Integer(
							adicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorParecerEncerramento()));
		}
		// Gera d�bito
		if (adicionarSolicitacaoEspecificacaoActionForm
				.getIndicadorGerarDebito() != null
				&& !adicionarSolicitacaoEspecificacaoActionForm
						.getIndicadorGerarDebito().equals("")) {
			solicitacaoTipoEspecificacao.setIndicadorGeracaoDebito(new Integer(
					adicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorGerarDebito()));
		}
		// Gera Cr�dito
		if (adicionarSolicitacaoEspecificacaoActionForm
				.getIndicadorGerarCredito() != null
				&& !adicionarSolicitacaoEspecificacaoActionForm
						.getIndicadorGerarCredito().equals("")) {
			solicitacaoTipoEspecificacao
					.setIndicadorGeracaoCredito(new Integer(
							adicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorGerarCredito()));
		}

		// seta indicador de validar Documento Respons�vel para 2
		solicitacaoTipoEspecificacao
				.setIndicadorValidarDocResponsavel(ConstantesSistema.NAO);

		// hora e data correntes
		solicitacaoTipoEspecificacao.setUltimaAlteracao(new Date());

		// Unidade inicial tramita��o
		if (adicionarSolicitacaoEspecificacaoActionForm
				.getIdUnidadeTramitacao() != null
				&& !adicionarSolicitacaoEspecificacaoActionForm
						.getIdUnidadeTramitacao().equals("")) {
			// Verifica se o c�digo foi modificado
			if (adicionarSolicitacaoEspecificacaoActionForm
					.getDescricaoUnidadeTramitacao() == null
					|| adicionarSolicitacaoEspecificacaoActionForm
							.getDescricaoUnidadeTramitacao().trim().equals("")) {

				FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

				filtroUnidadeOrganizacional
						.adicionarParametro(new ParametroSimples(
								FiltroUnidadeOrganizacional.ID,
								adicionarSolicitacaoEspecificacaoActionForm
										.getIdUnidadeTramitacao()));

				filtroUnidadeOrganizacional
						.adicionarParametro(new ParametroSimples(
								FiltroUnidadeOrganizacional.INDICADOR_USO,
								ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection unidadeOrganizacionalEncontrado = fachada.pesquisar(
						filtroUnidadeOrganizacional,
						UnidadeOrganizacional.class.getName());

				if (unidadeOrganizacionalEncontrado != null
						&& !unidadeOrganizacionalEncontrado.isEmpty()) {
					UnidadeOrganizacional uinidadeOrganizacional = (UnidadeOrganizacional) ((List) unidadeOrganizacionalEncontrado)
							.get(0);
					solicitacaoTipoEspecificacao
							.setUnidadeOrganizacional(uinidadeOrganizacional);

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Unidade Organizacional");
				}

			} else {
				UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
				unidadeOrganizacional.setId(new Integer(
						adicionarSolicitacaoEspecificacaoActionForm
								.getIdUnidadeTramitacao()));
				solicitacaoTipoEspecificacao
						.setUnidadeOrganizacional(unidadeOrganizacional);
			}
		}

		// id do tipo da solicita��o gerada
		if (adicionarSolicitacaoEspecificacaoActionForm.getIdServicoOS() != null
				&& !adicionarSolicitacaoEspecificacaoActionForm
						.getIdServicoOS().equals("")) {

			// Verifica se o c�digo foi modificado
			if (adicionarSolicitacaoEspecificacaoActionForm
					.getDescricaoServicoOS() == null
					|| adicionarSolicitacaoEspecificacaoActionForm
							.getDescricaoServicoOS().trim().equals("")) {

				FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();

				filtroServicoTipo.adicionarParametro(new ParametroSimples(
						FiltroServicoTipo.ID,
						adicionarSolicitacaoEspecificacaoActionForm
								.getIdServicoOS()));

				Collection servicoTipoEncontrado = fachada.pesquisar(
						filtroServicoTipo, ServicoTipo.class.getName());

				if (servicoTipoEncontrado != null
						&& !servicoTipoEncontrado.isEmpty()) {
					// [SF0003] - Validar Tipo Servi�o
					fachada.verificarServicoTipoReferencia(new Integer(
							adicionarSolicitacaoEspecificacaoActionForm
									.getIdServicoOS()));

					ServicoTipo servicoTipo = (ServicoTipo) ((List) servicoTipoEncontrado)
							.get(0);
					solicitacaoTipoEspecificacao.setServicoTipo(servicoTipo);
				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Servi�o Tipo");
				}
			} else {
				ServicoTipo servicoTipo = new ServicoTipo();
				servicoTipo.setId(new Integer(
						adicionarSolicitacaoEspecificacaoActionForm
								.getIdServicoOS()));
				solicitacaoTipoEspecificacao.setServicoTipo(servicoTipo);
			}

			}else{
				ServicoTipo servicoTipo = new ServicoTipo();
	
				solicitacaoTipoEspecificacao
						.setServicoTipo(servicoTipo);
		}

		// Gera ordem de servi�o
		if (adicionarSolicitacaoEspecificacaoActionForm
				.getIndicadorGeraOrdemServico() != null
				&& !adicionarSolicitacaoEspecificacaoActionForm
						.getIndicadorGeraOrdemServico().equals("")) {

			solicitacaoTipoEspecificacao
					.setIndicadorGeracaoOrdemServico(new Short(
							adicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorGeraOrdemServico()));
		}
		// Cliente Obrigat�rio
		if (adicionarSolicitacaoEspecificacaoActionForm.getIndicadorCliente() != null
				&& !adicionarSolicitacaoEspecificacaoActionForm
						.getIndicadorCliente().equals("")) {

			solicitacaoTipoEspecificacao.setIndicadorCliente(new Short(
					adicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorCliente()));
		}

		// Indicador verfificca��o de d�bito
		if (adicionarSolicitacaoEspecificacaoActionForm
				.getIndicadorVerificarDebito() != null
				&& !adicionarSolicitacaoEspecificacaoActionForm
						.getIndicadorVerificarDebito().equals("")) {

			solicitacaoTipoEspecificacao.setIndicadorVerificarDebito(new Short(
					adicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorVerificarDebito()));
		}

		// Situa��o imovel
		if (adicionarSolicitacaoEspecificacaoActionForm.getIdSituacaoImovel() != null
				&& !adicionarSolicitacaoEspecificacaoActionForm
						.getIdSituacaoImovel().equals("")) {
			EspecificacaoImovelSituacao especificacaoImovelSituacao = new EspecificacaoImovelSituacao();
			especificacaoImovelSituacao.setId(new Integer(
					adicionarSolicitacaoEspecificacaoActionForm
							.getIdSituacaoImovel()));
			solicitacaoTipoEspecificacao
					.setEspecificacaoImovelSituacao(especificacaoImovelSituacao);
		}
		Collection colecaoEspecificacaoServicoTipo = (Collection) sessao
				.getAttribute("colecaoEspecificacaoServicoTipo");
		
		// recupera a cole��o de especificacao servico tipo
		if (colecaoEspecificacaoServicoTipo != null
				&& !colecaoEspecificacaoServicoTipo.isEmpty()) {
			// [SF0004] - Validar Valor Ordem de Servi�o 2� parte
			fachada
					.verificarOrdemExecucaoForaOrdem(colecaoEspecificacaoServicoTipo);
			solicitacaoTipoEspecificacao
					.setEspecificacaoServicoTipos(new HashSet(
							colecaoEspecificacaoServicoTipo));
			sessao.removeAttribute("colecaoEspecificacaoServicoTipo");
		}

		// Colocado por Raphael Rossiter em 25/02/2008
		// Tipo de D�bito
		if (adicionarSolicitacaoEspecificacaoActionForm.getIdDebitoTipo() != null
				&& !adicionarSolicitacaoEspecificacaoActionForm
						.getIdDebitoTipo().equals("")) {

			FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();

			filtroDebitoTipo.adicionarParametro(new ParametroSimples(
					FiltroDebitoTipo.ID,
					adicionarSolicitacaoEspecificacaoActionForm
							.getIdDebitoTipo()));

			Collection debitoTipoEncontrado = fachada.pesquisar(
					filtroDebitoTipo, DebitoTipo.class.getName());

			if (debitoTipoEncontrado != null && !debitoTipoEncontrado.isEmpty()) {

				DebitoTipo debitoTipo = (DebitoTipo) Util
						.retonarObjetoDeColecao(debitoTipoEncontrado);

				solicitacaoTipoEspecificacao.setDebitoTipo(debitoTipo);
			} else {

				// [FS0007] - Validar Tipo de d�bito
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Tipo de D�bito");
			}

		}

		// Valor do D�bito
		if (adicionarSolicitacaoEspecificacaoActionForm.getValorDebito() != null
				&& !adicionarSolicitacaoEspecificacaoActionForm
						.getValorDebito().equals("")) {

			solicitacaoTipoEspecificacao
					.setValorDebito(Util
							.formatarMoedaRealparaBigDecimal(adicionarSolicitacaoEspecificacaoActionForm
									.getValorDebito()));
		}

		// Alterar Valor do d�bito
		if (adicionarSolicitacaoEspecificacaoActionForm
				.getIndicadorPermiteAlterarValor() != null
				&& !adicionarSolicitacaoEspecificacaoActionForm
						.getIndicadorPermiteAlterarValor().equals("")) {
			solicitacaoTipoEspecificacao
					.setIndicadorPermiteAlterarValor(new Short(
							adicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorPermiteAlterarValor()));
		}

		// Cobrar Juros
		if (adicionarSolicitacaoEspecificacaoActionForm
				.getIndicadorCobrarJuros() != null
				&& !adicionarSolicitacaoEspecificacaoActionForm
						.getIndicadorCobrarJuros().equals("")) {
			solicitacaoTipoEspecificacao.setIndicadorCobrarJuros(new Short(
					adicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorCobrarJuros()));
		}
		
		//Indicador Documento Cobran�a
		if(adicionarSolicitacaoEspecificacaoActionForm.getIndicadorDocumentoObrigatorio() != null &&
				!adicionarSolicitacaoEspecificacaoActionForm.getIndicadorDocumentoObrigatorio().equals("")){

				if(adicionarSolicitacaoEspecificacaoActionForm
						.getIndicadorDocumentoObrigatorio().equals(ConstantesSistema.SIM.toString())){
					
					solicitacaoTipoEspecificacao.setIndicadorDocumentoObrigatorio(ConstantesSistema.SIM);
					
				}else{
					
					solicitacaoTipoEspecificacao.setIndicadorDocumentoObrigatorio(ConstantesSistema.NAO);
					
				}
			
		}

		// Indicador encerramento autom�tico
		if (adicionarSolicitacaoEspecificacaoActionForm
				.getIndicadorEncerramentoAutomatico() != null
				&& !adicionarSolicitacaoEspecificacaoActionForm
						.getIndicadorEncerramentoAutomatico().equals("")) {

			solicitacaoTipoEspecificacao
					.setIndicadorEncerramentoAutomatico(new Short(
							adicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorEncerramentoAutomatico()));
		}
		
		//indicador de loja virtual
		if(Util.verificarNaoVazio(adicionarSolicitacaoEspecificacaoActionForm.getIndicadorLojaVirtual())){
			solicitacaoTipoEspecificacao.setIndicadorLojaVirtual(new Short(adicionarSolicitacaoEspecificacaoActionForm.getIndicadorLojaVirtual()));
		}

		/*RM 5759 - Am�lia Pessoa - 05/12/2011
		indicador Permite Cancelar Debito */
		if(Util.verificarNaoVazio(adicionarSolicitacaoEspecificacaoActionForm.getIndicadorPermiteCancelarDebito())){
			solicitacaoTipoEspecificacao.setIndicadorPermiteCancelarDebito(new Short(adicionarSolicitacaoEspecificacaoActionForm.getIndicadorPermiteCancelarDebito()));
		}
		
		/*RM 7642 - Flavio Ferreira - 20/11/203
		indicador Tipo de Especifica��o Listado Consultar Imovel */
		if(Util.verificarNaoVazio(adicionarSolicitacaoEspecificacaoActionForm.getIndicadorTipoEspecificacaoListadoPopupConsultarImovel())){
			solicitacaoTipoEspecificacao.setIndicadorTipoEspecificacaoListadoPopupConsultarImovel(new Short(
				adicionarSolicitacaoEspecificacaoActionForm.getIndicadorTipoEspecificacaoListadoPopupConsultarImovel()));
		}
		
		/*RM 6692 - Filipe Selva - 09/07/2013
		Observa��es */
		if(adicionarSolicitacaoEspecificacaoActionForm.getObservacao() != null && !(adicionarSolicitacaoEspecificacaoActionForm.getObservacao() == "")){
			solicitacaoTipoEspecificacao.setObservacao(adicionarSolicitacaoEspecificacaoActionForm.getObservacao());
		}
		
		/*RM 5924 - Am�lia Pessoa - 13/12/2011
		Adicionar arquivo de procedimentos operacionais padr�es POPs */ 
		if(sessao.getAttribute("colecaoHelpers") != null){
			Collection<SolicitacaoEspecificacaoHelper> colecaoHelpers = (Collection<SolicitacaoEspecificacaoHelper>) sessao.getAttribute("colecaoHelpers");
			if(colecaoHelpers.size() != 0){		
				
				Integer ultimo = (Integer) sessao.getAttribute("proxId");
				ultimo = ultimo-1;
				for (SolicitacaoEspecificacaoHelper helper: colecaoHelpers){
					if (helper.getIdHelper()==ultimo.intValue()){
						solicitacaoTipoEspecificacao.setColecaoArquivosPOP(helper.getColecaoArquivos());
						solicitacaoTipoEspecificacao.setIdHelper(ultimo);
					}
				}
			}
			
		}
		solicitacaoTipoEspecificacao.setIdHelper(adicionarSolicitacaoEspecificacaoActionForm.getIdHelper());
		sessao.removeAttribute("colecaoArquivos");
		
		
		// indicador de uso ativo
		solicitacaoTipoEspecificacao.setIndicadorUso(new Short(
				ConstantesSistema.INDICADOR_USO_ATIVO));

		// Especifica��o do novo RA

		if (adicionarSolicitacaoEspecificacaoActionForm.getIdEspecificacao() != null
				&& !adicionarSolicitacaoEspecificacaoActionForm
						.getIdEspecificacao().equals("")) {

			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacaoNovoRA = new SolicitacaoTipoEspecificacao();
			solicitacaoTipoEspecificacaoNovoRA.setId(Integer
					.parseInt(adicionarSolicitacaoEspecificacaoActionForm
							.getIdEspecificacao()));

			SolicitacaoTipo solicitacaoTipo = new SolicitacaoTipo();
			solicitacaoTipo.setId(Integer
					.parseInt(adicionarSolicitacaoEspecificacaoActionForm
							.getIdTipoSolicitacao()));
			solicitacaoTipoEspecificacaoNovoRA
					.setSolicitacaoTipo(solicitacaoTipo);

			solicitacaoTipoEspecificacao
					.setSolicitacaoTipoEspecificacaoNovoRA(solicitacaoTipoEspecificacaoNovoRA);
		}

		// Informar conta no Registro de Atendimento
		if (adicionarSolicitacaoEspecificacaoActionForm
				.getIndicadorInformarContaRA() != null
				&& !adicionarSolicitacaoEspecificacaoActionForm
						.getIndicadorInformarContaRA().equals("")) {

			solicitacaoTipoEspecificacao.setIndicadorInformarContaRA(new Short(
					adicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorInformarContaRA()));
		}

		// Informar Pagamento em Duplicidade no Registro de Atendimento
		if (adicionarSolicitacaoEspecificacaoActionForm
				.getIndicadorInformarPagamentoDP() != null
				&& !adicionarSolicitacaoEspecificacaoActionForm
						.getIndicadorInformarPagamentoDP().equals("")) {

			solicitacaoTipoEspecificacao
					.setIndicadorInformarPagamentoDuplicidade(new Short(
							adicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorInformarPagamentoDP()));
		}

		// Informar vencimento alternativo
		if (adicionarSolicitacaoEspecificacaoActionForm
				.getIndicadorAlterarVencimentoAlternativo() != null
				&& !adicionarSolicitacaoEspecificacaoActionForm
						.getIndicadorAlterarVencimentoAlternativo().equals("")) {

			solicitacaoTipoEspecificacao
					.setIndicadorAlterarVencimentoAlternativo(new Short(
							adicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorAlterarVencimentoAlternativo()));
		}

		// adiciona na cole��o o tipo de solicita��o especificado
		if (!colecaoSolicitacaoTipoEspecificacao
				.contains(solicitacaoTipoEspecificacao)) {

			colecaoSolicitacaoTipoEspecificacao
					.add(solicitacaoTipoEspecificacao);

		} else {
			throw new ActionServletException(
					"atencao.tipo.especificacao.ja.existe");
		}

		sessao.setAttribute("colecaoSolicitacaoTipoEspecificacao",
				colecaoSolicitacaoTipoEspecificacao);

		// manda um parametro para fechar o popup
		httpServletRequest.setAttribute("fecharPopup", 1);

		return retorno;
	}
}
