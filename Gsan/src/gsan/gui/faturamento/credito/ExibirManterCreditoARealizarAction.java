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
import gsan.cadastro.cliente.ClienteImovel;
import gsan.cadastro.cliente.ClienteRelacaoTipo;
import gsan.cadastro.cliente.FiltroClienteImovel;
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
import gsan.faturamento.credito.FiltroCreditoOrigem;
import gsan.faturamento.credito.FiltroCreditoTipo;
import gsan.faturamento.debito.DebitoCreditoSituacao;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
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
 * [UC0195] Manter Cr�dito a Realizar Permite cancelar um ou mais cr�ditos a
 * realizar de um determinado im�vel
 * 
 * @author Roberta Costa
 * @since 12/01/2006
 */
public class ExibirManterCreditoARealizarAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirManterCreditoARealizar");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		// Cole��o de Tipo de Cr�dito
		FiltroCreditoTipo filtroCreditoTipo = new FiltroCreditoTipo();
		Collection<CreditoTipo> collectionCreditoTipo = fachada.pesquisar(
				filtroCreditoTipo, CreditoTipo.class.getName());

		httpServletRequest.setAttribute("collectionCreditoTipo",
				collectionCreditoTipo);

		// Cole��o de Origem do Cr�dito
		FiltroCreditoOrigem filtroCreditoOrigem = new FiltroCreditoOrigem();
		Collection<CreditoOrigem> collectionCreditoOrigem = fachada.pesquisar(
				filtroCreditoOrigem, CreditoOrigem.class.getName());

		httpServletRequest.setAttribute("collectionCreditoOrigem",
				collectionCreditoOrigem);

		// Valida��es do Formul�rio
		ManterCreditoARealizarActionForm manterCreditoARealizarActionForm = (ManterCreditoARealizarActionForm) actionForm;

		String limparForm = (String) httpServletRequest
				.getParameter("limparForm");
		String codigoImovel = manterCreditoARealizarActionForm
				.getMatriculaImovel();
		
		if(httpServletRequest.getParameter("menu") != null){
			codigoImovel = null;
			manterCreditoARealizarActionForm.setMatriculaImovel("");
			manterCreditoARealizarActionForm.setInscricaoImovel("");
			manterCreditoARealizarActionForm.setNomeCliente("");
			manterCreditoARealizarActionForm.setSituacaoAgua("");
			manterCreditoARealizarActionForm.setSituacaoEsgoto("");
		}
		
		if (codigoImovel == null || codigoImovel.trim().equals("")){
			if (httpServletRequest.getParameter("codigoImovel") != null){
				codigoImovel = httpServletRequest.getParameter("codigoImovel").toString();
			}
		}
		
		if (codigoImovel != null && !codigoImovel.trim().equals("")) {
			// Pesquisa o imovel na base
			FiltroImovel filtroImovel = new FiltroImovel();

			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.ID, codigoImovel));
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
            
            /*filtroImovel
            .adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao.id");
            filtroImovel
            .adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao.id");
    */
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");

			Collection<Imovel> imovelPesquisado = fachada.pesquisar(
					filtroImovel, Imovel.class.getName());

			// [FS0001 - Verificar exist�ncioa da matr�cula do im�vel] Imovel
			// Inxistente
			if (imovelPesquisado != null && imovelPesquisado.isEmpty()) {
				httpServletRequest.setAttribute("corImovel","exception");
           		manterCreditoARealizarActionForm
           			.setInscricaoImovel(ConstantesSistema.CODIGO_IMOVEL_INEXISTENTE);
           		manterCreditoARealizarActionForm
       				.setMatriculaImovel("");
           		manterCreditoARealizarActionForm
   					.setNomeCliente("");
           		manterCreditoARealizarActionForm
   					.setSituacaoAgua("");
           		manterCreditoARealizarActionForm
   					.setSituacaoEsgoto("");
			}

			// [FS0001 - Verificar exist�ncioa da matr�cula do im�vel] Imovel
			// Excluido
			if (imovelPesquisado != null && !imovelPesquisado.isEmpty()) {
				Imovel imovel = imovelPesquisado.iterator().next();
				if (imovel.getIndicadorExclusao() == Imovel.IMOVEL_EXCLUIDO) {
					throw new ActionServletException(
							"atencao.pesquisa.imovel.excluido");
				}
			}

			// [FS0002 - Verificar usu�rio com d�bito em cobran�a administrativa
			if (imovelPesquisado != null && !imovelPesquisado.isEmpty()) {
				Imovel imovel = imovelPesquisado.iterator().next();
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
				Imovel imovel = imovelPesquisado.iterator().next();
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

				Imovel imovel = imovelPesquisado.iterator().next();

				sessao.setAttribute("imovelPesquisado", imovel);

				FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

				filtroClienteImovel
						.adicionarCaminhoParaCarregamentoEntidade("cliente");
				filtroClienteImovel.adicionarParametro(new ParametroSimples(
						FiltroClienteImovel.IMOVEL_ID, codigoImovel));
				filtroClienteImovel.adicionarParametro(new ParametroSimples(
						FiltroClienteImovel.CLIENTE_RELACAO_TIPO,
						ClienteRelacaoTipo.USUARIO));

				Collection<ClienteImovel> clienteImovelPesquisado = fachada
						.pesquisar(filtroClienteImovel, ClienteImovel.class
								.getName());

				if (clienteImovelPesquisado != null
						&& !clienteImovelPesquisado.isEmpty()) {
					ClienteImovel clienteImovel = clienteImovelPesquisado
							.iterator().next();
					if (clienteImovel.getCliente() != null) {
						manterCreditoARealizarActionForm
								.setNomeCliente(clienteImovel.getCliente()
										.getNome());
					}
				}
				if (imovel.getLigacaoAguaSituacao() != null) {
					manterCreditoARealizarActionForm.setSituacaoAgua(imovel
							.getLigacaoAguaSituacao().getDescricao());
				}

				if (imovel.getLigacaoEsgotoSituacao() != null) {
					manterCreditoARealizarActionForm.setSituacaoEsgoto(imovel
							.getLigacaoEsgotoSituacao().getDescricao());
				}
				manterCreditoARealizarActionForm
						.setMatriculaImovel(codigoImovel);

				manterCreditoARealizarActionForm.setInscricaoImovel(imovel
						.getInscricaoFormatada());
			}
			if(imovelPesquisado != null && !imovelPesquisado.isEmpty())
			{
				// Pesquisando os Cr�dito a Realizar do Im�vel
				// CreditoARealizar creditoARealizar = new CreditoARealizar();
	
				FiltroCreditoARealizar filtroCreditoARealizar = new FiltroCreditoARealizar();
	
				filtroCreditoARealizar.adicionarParametro(new ParametroSimples(
						FiltroCreditoARealizar.IMOVEL_ID, codigoImovel));
				filtroCreditoARealizar.adicionarParametro(new ParametroSimples(
						FiltroCreditoARealizar.DEBITO_CREDITO_SITUACAO_ATUAL,
						DebitoCreditoSituacao.NORMAL));
				/*filtroCreditoARealizar
						.adicionarCaminhoParaCarregamentoEntidade("creditoTipo.descricaoAbreviada");
				filtroCreditoARealizar
						.adicionarCaminhoParaCarregamentoEntidade("creditoOrigem.descricaoAbreviada");
*/                
                filtroCreditoARealizar.adicionarCaminhoParaCarregamentoEntidade("creditoTipo");
                filtroCreditoARealizar.adicionarCaminhoParaCarregamentoEntidade("creditoOrigem");
	
				Collection<CreditoARealizar> collectionCreditoARealizar = fachada
						.pesquisar(filtroCreditoARealizar, CreditoARealizar.class
								.getName());
				
				if (collectionCreditoARealizar == null
						|| collectionCreditoARealizar.isEmpty()) {
					// Nenhum Credito a Realizar cadastrado
					throw new ActionServletException(
							"atencao.credito_a_realizar_inexistente",null,manterCreditoARealizarActionForm.getMatriculaImovel());
				}	
				
				Iterator it = collectionCreditoARealizar.iterator();
				Collection<CreditoARealizar> colecaoCreditoARealizar = new ArrayList<CreditoARealizar>();
				
				while(it.hasNext()){
					
					CreditoARealizar creditoARealizar = (CreditoARealizar) it.next();
					
					if(creditoARealizar.getAnoMesReferenciaCredito() == null || 
							creditoARealizar.getAnoMesReferenciaCredito().equals("")){
						creditoARealizar.setAnoMesReferenciaCredito(creditoARealizar.getAnoMesCobrancaCredito());
					}
					
					colecaoCreditoARealizar.add(creditoARealizar);
					
				}			

				httpServletRequest.setAttribute("collectionCreditoARealizar",
					colecaoCreditoARealizar);				
			}
		}

		if (limparForm != null && !limparForm.trim().equalsIgnoreCase("")) {

			manterCreditoARealizarActionForm.reset(actionMapping,
					httpServletRequest);

			if (sessao.getAttribute("imovelPesquisado") != null) {
				sessao.removeAttribute("imovelPesquisado");
			}
		}

		return retorno;
	}
}