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
package gsan.gui.faturamento.credito;

import gsan.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gsan.atendimentopublico.ordemservico.OrdemServico;
import gsan.atendimentopublico.registroatendimento.RegistroAtendimento;
import gsan.cadastro.imovel.FiltroImovel;
import gsan.cadastro.imovel.FiltroImovelCobrancaSituacao;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.imovel.ImovelCobrancaSituacao;
import gsan.cobranca.CobrancaSituacao;
import gsan.fachada.Fachada;
import gsan.faturamento.credito.CreditoARealizar;
import gsan.faturamento.credito.CreditoOrigem;
import gsan.faturamento.credito.CreditoTipo;
import gsan.faturamento.credito.FiltroCreditoARealizar;
import gsan.faturamento.debito.DebitoCreditoSituacao;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;
import gsan.util.filtro.ParametroSimplesDiferenteDe;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0194] Cr�dito a Realizar Permite inserir um cr�dito a realizar
 * 
 * @author Roberta Costa
 * @since 11/01/2006
 */
public class InserirCreditoARealizarAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Pega uma inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		// Inst�ncia do formul�rio que est� sendo utilizado
		InserirCreditoARealizarActionForm inserirCreditoARealizarActionForm = (InserirCreditoARealizarActionForm) actionForm;

		// Campos do Formul�rio
		Integer tipoCredito = new Integer(inserirCreditoARealizarActionForm
				.getTipoCredito());
		Integer origemCredito = new Integer(inserirCreditoARealizarActionForm
				.getOrigemCredito());
		Short numeroPrestacoes = new Short(inserirCreditoARealizarActionForm
				.getNumeroPrestacoes());
		String valorCreditoAntes = inserirCreditoARealizarActionForm
				.getValorCredito().toString().replace(".", "");
		String valorCredito = valorCreditoAntes.replace(',', '.');
		String registroAtendimentoForm = inserirCreditoARealizarActionForm
				.getRegistroAtendimento();
		String matriculaImovel = inserirCreditoARealizarActionForm
				.getMatriculaImovel();
		String referencia = inserirCreditoARealizarActionForm.getReferencia();
		String confirmado = httpServletRequest.getParameter("confirmado");

		// Criando o objeto creditoARealizar
		CreditoARealizar creditoARealizar = new CreditoARealizar();

		// Criando o objeto Cr�dito Tipo
		CreditoTipo creditoTipo = new CreditoTipo();
		creditoTipo.setId(tipoCredito);

		creditoARealizar.setCreditoTipo(creditoTipo);

		// Criando o objeto Cr�dito Origem
		CreditoOrigem creditoOrigem = new CreditoOrigem();
		creditoOrigem.setId(origemCredito);

		creditoARealizar.setCreditoOrigem(creditoOrigem);

		// Criando o objeto Registro Atendimento
		RegistroAtendimento registroAtendimento = new RegistroAtendimento();

		if (registroAtendimentoForm != null
				&& !registroAtendimentoForm.equals("")) {
			registroAtendimento.setId(new Integer(registroAtendimentoForm));

			creditoARealizar.setRegistroAtendimento(registroAtendimento);
		} else {
			// inseri o valor 1 temporariamente
			RegistroAtendimento registroAtendimentoConstante = new RegistroAtendimento();
			registroAtendimentoConstante.setId(RegistroAtendimento.CONSTANTE);
			creditoARealizar
					.setRegistroAtendimento(registroAtendimentoConstante);
		}

		if (inserirCreditoARealizarActionForm.getOrdemServico() != null
				&& !inserirCreditoARealizarActionForm.getOrdemServico().equals(
						"")) {
			Integer ordemServicoForm = new Integer(
					inserirCreditoARealizarActionForm.getOrdemServico());

			// Criando o objeto Ordem de Servi�o
			OrdemServico ordemServico = new OrdemServico();
			ordemServico.setId(ordemServicoForm);

			creditoARealizar.setOrdemServico(ordemServico);
		} else {
			creditoARealizar.setOrdemServico(null);
		}
		
		//[FS0006] Verificar exist�ncia de credito a realizar para o registro de atendimento
		if((inserirCreditoARealizarActionForm.getTipoCredito() != null && 
				!inserirCreditoARealizarActionForm.getTipoCredito().equals("")) 
		    &&
		    (inserirCreditoARealizarActionForm.getRegistroAtendimento() != null
	    		&& !inserirCreditoARealizarActionForm.getRegistroAtendimento().equals(""))
		){
			FiltroCreditoARealizar filtroCreditoARelizar = new FiltroCreditoARealizar();
			filtroCreditoARelizar.adicionarParametro(new ParametroSimples(FiltroCreditoARealizar.ID_REGISTRO_ATENDIMENTO,inserirCreditoARealizarActionForm.getRegistroAtendimento()));
			filtroCreditoARelizar.adicionarParametro(new ParametroSimples(FiltroCreditoARealizar.ID_CREDITO_TIPO,inserirCreditoARealizarActionForm.getTipoCredito()));
			filtroCreditoARelizar.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroCreditoARealizar.DEBITO_CREDITO_SITUACAO_ATUAL,DebitoCreditoSituacao.CANCELADA));

			Collection colecaoCreditoARealizar = fachada.pesquisar(
					filtroCreditoARelizar, CreditoARealizar.class
							.getName());
			
			if (colecaoCreditoARealizar != null
					&& !colecaoCreditoARealizar.isEmpty()) {
				throw new ActionServletException(
				"atencao.existe.credito_a_realizar.para.registro_atendimento");
			}
		}
		
		

		// Pega o Im�vel da sess�o
		Imovel imovel = null;
		if (sessao.getAttribute("imovelPesquisado") != null) {
			imovel = (Imovel) sessao.getAttribute("imovelPesquisado");
		}else{
			if (matriculaImovel != null && !matriculaImovel.trim().equals("")) {
				// Pesquisa o imovel na base
				FiltroImovel filtroImovel = new FiltroImovel();

				filtroImovel.adicionarParametro(new ParametroSimples(
						FiltroImovel.ID, matriculaImovel));

				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
				
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("quadra");
				
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
				
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
				
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
                
                filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");
                filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");
        
				/*filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao.id");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao.id");*/
				

				Collection<Imovel> imovelPesquisado = fachada.pesquisar(
						filtroImovel, Imovel.class.getName());

				// [FS0001 - Verificar exist�ncioa da matr�cula do im�vel] Imovel
				// Inxistente
				if (imovelPesquisado != null && imovelPesquisado.isEmpty()) {
					throw new ActionServletException("atencao.imovel.inexistente");
				}

				// [FS0001 - Verificar exist�ncioa da matr�cula do im�vel] Imovel
				// Excluido
				if (imovelPesquisado != null && !imovelPesquisado.isEmpty()) {
					imovel = imovelPesquisado.iterator().next();
					if (imovel.getIndicadorExclusao() == Imovel.IMOVEL_EXCLUIDO) {
						throw new ActionServletException(
								"atencao.pesquisa.imovel.excluido");
					}
				}

				// [FS0002 - Verificar usu�rio com d�bito em cobran�a administrativa
				if (imovelPesquisado != null && !imovelPesquisado.isEmpty()) {
					imovel = imovelPesquisado.iterator().next();
					FiltroImovelCobrancaSituacao filtroImovelCobrancaSituacao = new FiltroImovelCobrancaSituacao();

					filtroImovelCobrancaSituacao
							.adicionarCaminhoParaCarregamentoEntidade("cobrancaSituacao");
					filtroImovelCobrancaSituacao
							.adicionarParametro(new ParametroSimples(
									FiltroImovelCobrancaSituacao.IMOVEL_ID, imovel
											.getId()));

					Collection imovelCobrancaSituacaoEncontrada = fachada
							.pesquisar(filtroImovelCobrancaSituacao,
									ImovelCobrancaSituacao.class.getName());

					// Verifica se o im�vel tem d�bito em cobran�a administrativa
					if (imovelCobrancaSituacaoEncontrada != null
							&& !imovelCobrancaSituacaoEncontrada.isEmpty()) {

						if (((ImovelCobrancaSituacao) ((List) imovelCobrancaSituacaoEncontrada)
								.get(0)).getCobrancaSituacao() != null) {

							if (((ImovelCobrancaSituacao) ((List) imovelCobrancaSituacaoEncontrada)
									.get(0)).getCobrancaSituacao().getId().equals(
									CobrancaSituacao.COBRANCA_ADMINISTRATIVA)
									&& ((ImovelCobrancaSituacao) ((List) imovelCobrancaSituacaoEncontrada)
											.get(0)).getDataRetiradaCobranca() == null) {

								throw new ActionServletException(
										"atencao.pesquisa.imovel.cobranca_administrativa");
							}
						}
					}
				}

				// [FS0003 - Verificar situa��o liga��o de �giua e esgoto]
				if (imovelPesquisado != null && !imovelPesquisado.isEmpty()) {
					imovel = imovelPesquisado.iterator().next();
					if ((imovel.getLigacaoAguaSituacao() != null)
							&& ((imovel.getLigacaoAguaSituacao().getId() == LigacaoAguaSituacao.POTENCIAL) || (imovel
									.getLigacaoEsgotoSituacao().getId() == LigacaoAguaSituacao.FACTIVEL))
							&& (imovel.getLigacaoEsgotoSituacao().getId() != LigacaoEsgotoSituacao.LIGADO)) {
						throw new ActionServletException(
								"atencao.pesquisa.imovel.inativo");
					}
				}

				// Obtem o cliente imovel do imovel pesquisado
				if (imovelPesquisado != null && !imovelPesquisado.isEmpty()) {

					imovel = imovelPesquisado.iterator().next();
					
					sessao.setAttribute("imovelPesquisado", imovel);
				}
			}
		}
		
		if (referencia != null && !referencia.equals("")) {
			Integer referenciaCredito = Util.formatarMesAnoComBarraParaAnoMes(referencia);
			
			Integer qtdContas = fachada.pesquisarQuantidadeContasEContasHistorico(imovel.getId(), referenciaCredito);
			
			// verifica se o valor da devolucao � maior que o valor da guia da devolucao
			if (qtdContas == null || qtdContas == 0) {
			
				if (confirmado == null || !confirmado.trim().equalsIgnoreCase("ok")){
					httpServletRequest.setAttribute("caminhoActionConclusao",
							"/gsan/inserirCreditoARealizarAction.do");
					// Monta a p�gina de confirma��o para perguntar se o usu�rio
					// quer inserir
					// a devolu��o mesmo com o valor da devolu��o sendo superior ao da guia da devolu��o
					return montarPaginaConfirmacao(
							"atencao.referencia.conta.inexistente",
							httpServletRequest, actionMapping);
				}
			}
			
			creditoARealizar.setAnoMesReferenciaCredito(referenciaCredito);
		}

		// Setando as informa��es do Im�vel
		creditoARealizar.setImovel(imovel);
		creditoARealizar.setCodigoSetorComercial(imovel.getSetorComercial()
				.getCodigo());
		creditoARealizar.setNumeroQuadra(new Integer(imovel.getQuadra()
				.getNumeroQuadra()));
		creditoARealizar.setNumeroLote(imovel.getLote());
		creditoARealizar.setNumeroSubLote(imovel.getSubLote());
		creditoARealizar.setQuadra(imovel.getQuadra());
		creditoARealizar.setLocalidade(imovel.getLocalidade());

		creditoARealizar.setNumeroPrestacaoCredito(numeroPrestacoes);
		creditoARealizar.setValorCredito(new BigDecimal(valorCredito));
		creditoARealizar.setAnoMesReferenciaContabil(Util.formatarMesAnoComBarraParaAnoMes(
			inserirCreditoARealizarActionForm.getMesAnoInicialDoCredito()));

		fachada.inserirCreditoARealizar(imovel, creditoARealizar,usuarioLogado);
		
		sessao.removeAttribute("imovelPesquisado");

		montarPaginaSucesso(httpServletRequest, "Cr�dito a Realizar do Im�vel "
				+ imovel.getId() + " inserido com sucesso.",
				"Inserir outro Cr�dito a Realizar",
				"exibirInserirCreditoARealizarAction.do?menu=sim",
				"exibirManterCreditoARealizarAction.do?codigoImovel="
						+ imovel.getId(),
				"Cancelar Cr�dito(s) a Realizar do Im�vel " + imovel.getId());
		
		return retorno;
	}
}