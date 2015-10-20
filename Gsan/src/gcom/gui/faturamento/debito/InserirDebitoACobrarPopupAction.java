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
package gcom.gui.faturamento.debito;

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.CobrancaForma;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.financeiro.FiltroFinanciamentoTipo;
import gcom.financeiro.FinanciamentoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.FiltroParametro;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Inserir D�bito A Cobrar popup
 * 
 * @author Vivianne Sousa
 * @created 25/04/2006
 */
public class InserirDebitoACobrarPopupAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("inserirDebitoACobrar");

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		// Inst�ncia do formul�rio que est� sendo utilizado
		InserirDebitoACobrarPopupActionForm inserirDebitoACobrarPopupActionForm = (InserirDebitoACobrarPopupActionForm) actionForm;

		Imovel imovel = null;
		if (sessao.getAttribute("idImovel") != null) {
			String idImovel = (String)sessao.getAttribute("idImovel");
			
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, new Integer(idImovel)));
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");

			filtroImovel.adicionarParametro(new ParametroSimplesDiferenteDe(
					FiltroImovel.INDICADOR_IMOVEL_EXCLUIDO,
					Imovel.IMOVEL_EXCLUIDO, FiltroParametro.CONECTOR_OR,2));

			filtroImovel.adicionarParametro(new ParametroNulo(FiltroImovel.INDICADOR_IMOVEL_EXCLUIDO));
			
			Collection<Imovel> imovelPesquisado = fachada.pesquisar(filtroImovel, Imovel.class.getName());
		    imovel = imovelPesquisado.iterator().next();

		}

		Integer numeroPrestacoes = new Integer(1);
		BigDecimal valorTotalServico = Util.formatarMoedaRealparaBigDecimal(inserirDebitoACobrarPopupActionForm.getValorTotalServico());
		BigDecimal percentualAbatimento = null;
	
		percentualAbatimento = new BigDecimal("0.00");
		BigDecimal valorEntrada = null;
		
		String idDebitoTipo = inserirDebitoACobrarPopupActionForm.getIdTipoDebito();

		DebitoACobrar debitoACobrar = new DebitoACobrar();
		debitoACobrar.setGeracaoDebito(new Date());

		debitoACobrar.setValorDebito(Util.formatarMoedaRealparaBigDecimal(inserirDebitoACobrarPopupActionForm.getValorTotalServico()));
		
		debitoACobrar.setNumeroPrestacaoDebito(new Short("1"));
		debitoACobrar.setNumeroPrestacaoCobradas(new Short("0"));
		
		debitoACobrar.setCodigoSetorComercial(imovel.getSetorComercial().getCodigo());
		debitoACobrar.setNumeroQuadra(new Integer(imovel.getQuadra().getNumeroQuadra()));
		debitoACobrar.setNumeroLote(imovel.getLote());
		debitoACobrar.setNumeroSubLote(imovel.getSubLote());

		debitoACobrar.setPercentualTaxaJurosFinanciamento(new BigDecimal("0.00"));

		debitoACobrar.setRegistroAtendimento(null);
		debitoACobrar.setOrdemServico(null);

		debitoACobrar.setImovel(imovel);
		debitoACobrar.setQuadra(imovel.getQuadra());
		debitoACobrar.setLocalidade(imovel.getLocalidade());

		FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
        filtroDebitoTipo.adicionarCaminhoParaCarregamentoEntidade("financiamentoTipo");
        filtroDebitoTipo.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.ID, idDebitoTipo));
        filtroDebitoTipo.adicionarCaminhoParaCarregamentoEntidade("lancamentoItemContabil");
        
		Collection colecaoDebitoTipo = fachada.pesquisar(filtroDebitoTipo, DebitoTipo.class.getName());
		DebitoTipo debitoTipo = (DebitoTipo) colecaoDebitoTipo.iterator().next();
		debitoTipo.setId(new Integer(idDebitoTipo));

		// [FS0006] - Verificar tipo de financaimento do tipo de d�bito
		if (debitoTipo.getFinanciamentoTipo().getId().intValue() != FinanciamentoTipo.SERVICO_NORMAL.intValue()
				|| debitoTipo.getIndicadorGeracaoAutomatica().shortValue() == 1) {

			throw new ActionServletException(
					"atencao.tipo_financiamento.tipo_debito.nao.permite.debito_a_cobrar",
					debitoTipo.getFinanciamentoTipo().getDescricao(),
					debitoTipo.getDescricao());
		}

		// obter o financiamento tipo do tipo de debito
		FiltroFinanciamentoTipo filtroFinanciamentoTipo = new FiltroFinanciamentoTipo();
		filtroFinanciamentoTipo.adicionarParametro(new ParametroSimples(
				FiltroFinanciamentoTipo.ID, debitoTipo.getFinanciamentoTipo().getId()));

		Collection colecaoFinaciamentoTipo = fachada.pesquisar(
				filtroFinanciamentoTipo, FinanciamentoTipo.class.getName());

		FinanciamentoTipo financiamentoTipo = null;
		if (colecaoFinaciamentoTipo != null && !colecaoFinaciamentoTipo.isEmpty()) {
			financiamentoTipo = (FinanciamentoTipo) colecaoFinaciamentoTipo.iterator().next();
		}

		debitoACobrar.setFinanciamentoTipo(financiamentoTipo);
		debitoACobrar.setDebitoTipo(debitoTipo);
		debitoACobrar.setLancamentoItemContabil(debitoTipo.getLancamentoItemContabil());

		CobrancaForma cobrancaForma = new CobrancaForma();
		cobrancaForma.setId(CobrancaForma.COBRANCA_EM_CONTA);
		debitoACobrar.setCobrancaForma(cobrancaForma);
		debitoACobrar.setUltimaAlteracao(new Date());

		/**
		 * Recupera o usu�rio logado
		 * para passar no met�do de inserir d�bito a cobrar para verificar se o
		 * usu�rio tem abrang�ncia para inserir um d�bito a cobrar para o im�vel
		 * informado.
		 */
    	Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
    	
		Integer idDebitoACobrar = fachada.inserirDebitoACobrar(numeroPrestacoes, debitoACobrar,
				valorTotalServico, imovel, percentualAbatimento, valorEntrada, usuarioLogado);
		
		DebitoCreditoSituacao debitoCreditoSituacao = new DebitoCreditoSituacao();
		debitoCreditoSituacao.setId(DebitoCreditoSituacao.NORMAL);
		debitoACobrar.setDebitoCreditoSituacaoAtual(debitoCreditoSituacao);
		debitoACobrar.setId(idDebitoACobrar);
		sessao.setAttribute("idDebitoACobrarInserido",idDebitoACobrar.toString());
		
		
		Collection<DebitoACobrar> colecaoDebitosACobrar = null;
		
		if(sessao.getAttribute("colecaoDebitoACobrar") != null){
			colecaoDebitosACobrar = (Collection<DebitoACobrar>)sessao.getAttribute("colecaoDebitoACobrar");
		}else{
			colecaoDebitosACobrar = new ArrayList();
		}
		colecaoDebitosACobrar.add(debitoACobrar);
		sessao.removeAttribute("colecaoDebitoACobrar");
		sessao.setAttribute("colecaoDebitoACobrar",colecaoDebitosACobrar);
		
		httpServletRequest.setAttribute("reloadPage",1);
		
		return retorno;
	}

}
