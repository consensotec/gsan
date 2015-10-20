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
package gcom.gui.cobranca.parcelamento;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.ImovelSituacaoTipo;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cobranca.FiltroResolucaoDiretoria;
import gcom.cobranca.ResolucaoDiretoria;
import gcom.cobranca.parcelamento.FiltroParcelamentoPerfil;
import gcom.cobranca.parcelamento.ParcDesctoInativVista;
import gcom.cobranca.parcelamento.ParcelamentoDescontoAntiguidade;
import gcom.cobranca.parcelamento.ParcelamentoDescontoInatividade;
import gcom.cobranca.parcelamento.ParcelamentoPerfil;
import gcom.cobranca.parcelamento.ParcelamentoQuantidadeReparcelamentoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action form de manter Perfil de Parcelamento
 * 
 * @author Vivianne Sousa
 * @created 12/05/2006
 */
public class AtualizarPerfilParcelamentoAction extends GcomAction {
	/**
	 * @author Vivianne Sousa
	 * @date 12/05/2006
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

		AtualizarParcelamentoPerfilActionForm atualizarParcelamentoPerfilActionForm = (AtualizarParcelamentoPerfilActionForm) actionForm;
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);

		String idPerfilParcelamento = (String) sessao.getAttribute("idRegistroAtualizacao");
		String numeroResolucaoDiretoria = atualizarParcelamentoPerfilActionForm.getNumeroResolucaoDiretoria();

		FiltroParcelamentoPerfil filtroParcelamentoPerfil = new FiltroParcelamentoPerfil();
		// Seta o filtro para buscar o ParcelamentoPerfil na base
		// e recuperar o idImovelSituacaoTipo,idImovelPerfil e o idSubcategoria
		filtroParcelamentoPerfil.adicionarParametro(new ParametroSimples(FiltroParcelamentoPerfil.ID, idPerfilParcelamento));
		filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("imovelSituacaoTipo");
		filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("imovelPerfil");
		filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("subcategoria");
		filtroParcelamentoPerfil.adicionarCaminhoParaCarregamentoEntidade("categoria");
		
		// Procura o ParcelamentoPerfil na base
		ParcelamentoPerfil parcelamentoPerfilNaBase = null;
		parcelamentoPerfilNaBase = (ParcelamentoPerfil) ((List) (fachada.pesquisar(filtroParcelamentoPerfil, ParcelamentoPerfil.class.getName()))).get(0);

		Integer idImovelSituacaoTipo = parcelamentoPerfilNaBase.getImovelSituacaoTipo().getId();

		Integer idImovelPerfil = null;
		if (parcelamentoPerfilNaBase.getImovelPerfil() != null
				&& !parcelamentoPerfilNaBase.getImovelPerfil().equals("")) {
			idImovelPerfil = parcelamentoPerfilNaBase.getImovelPerfil().getId();
		}

		Integer idSubcategoria = null;
		if (parcelamentoPerfilNaBase.getSubcategoria() != null
				&& !parcelamentoPerfilNaBase.getSubcategoria().equals("")) {
			idSubcategoria = parcelamentoPerfilNaBase.getSubcategoria().getId();
		}

		Integer idCategoria = null;
		if (parcelamentoPerfilNaBase.getCategoria() != null
				&& !parcelamentoPerfilNaBase.getCategoria().equals("")) {
			idCategoria = parcelamentoPerfilNaBase.getCategoria().getId();
		}

		String percentualDescontoAcrescimo = null;
		if (atualizarParcelamentoPerfilActionForm.getPercentualDescontoAcrescimo() != null) {
			percentualDescontoAcrescimo = atualizarParcelamentoPerfilActionForm
				.getPercentualDescontoAcrescimo().toString().replace(",",".");
		}
		
		String percentualDescontoMulta = null;
		if (atualizarParcelamentoPerfilActionForm.getPercentualDescontoMulta() != null && !atualizarParcelamentoPerfilActionForm.getPercentualDescontoMulta().equals("") ) {
			percentualDescontoMulta = atualizarParcelamentoPerfilActionForm.getPercentualDescontoMulta().toString().replace(",",".");
			try{
				Util.formatarMoedaRealparaBigDecimal(percentualDescontoMulta);
			}catch(Exception ex){
				throw new ActionServletException("atencao.percentual_invalido", null, "de Desconto sobre os Acr�scimos por Impontualidade Multa");
			}
		}

		String percentualDescontoJuros = null;
		if (atualizarParcelamentoPerfilActionForm.getPercentualDescontoJuros() != null && !atualizarParcelamentoPerfilActionForm.getPercentualDescontoJuros().equals("") ) {
			percentualDescontoJuros = atualizarParcelamentoPerfilActionForm.getPercentualDescontoJuros().toString().replace(",",".");
			try{
				Util.formatarMoedaRealparaBigDecimal(percentualDescontoJuros);
			}catch(Exception ex){
				throw new ActionServletException("atencao.percentual_invalido", null, "de Desconto sobre os Acr�scimos por Impontualidade Juros");
			}
		}
		
		String percentualDescontoAtualizacaoMonetaria = null;
		if (atualizarParcelamentoPerfilActionForm.getPercentualDescontoAtualizacaoMonetaria() != null && 
				!atualizarParcelamentoPerfilActionForm.getPercentualDescontoAtualizacaoMonetaria().equals("") ) {
			percentualDescontoAtualizacaoMonetaria = atualizarParcelamentoPerfilActionForm.getPercentualDescontoAtualizacaoMonetaria().toString().replace(",",".");
			try{
				Util.formatarMoedaRealparaBigDecimal(percentualDescontoAtualizacaoMonetaria);
			}catch(Exception ex){
				throw new ActionServletException("atencao.percentual_invalido", null, "de Desconto sobre os Acr�scimos por Impontualidade Atualiza��o Monet�ria");
			}
		}
		
		//
		String percentualDescontoPagamentoAVistaMulta = null;
		if ( atualizarParcelamentoPerfilActionForm.getPercentualDescontoPagamentoAVistaMulta() != null && 
				!atualizarParcelamentoPerfilActionForm.getPercentualDescontoPagamentoAVistaMulta().equals("") ) {
			
			percentualDescontoPagamentoAVistaMulta = atualizarParcelamentoPerfilActionForm.getPercentualDescontoPagamentoAVistaMulta().toString().replace(",",".");
			try{
				Util.formatarMoedaRealparaBigDecimal(percentualDescontoPagamentoAVistaMulta);
			}catch(Exception ex){
				throw new ActionServletException("atencao.percentual_invalido", null, "de Desconto sobre os Acr�scimos por Impontualidade de Multa para Pagamento a Vista");
			}
		}

		String percentualDescontoPagamentoAVistaJuros = null;
		if ( atualizarParcelamentoPerfilActionForm.getPercentualDescontoPagamentoAVistaJuros() != null && 
				!atualizarParcelamentoPerfilActionForm.getPercentualDescontoPagamentoAVistaJuros().equals("") ) {
			percentualDescontoPagamentoAVistaJuros = atualizarParcelamentoPerfilActionForm.getPercentualDescontoPagamentoAVistaJuros().toString().replace(",",".");
			try{
				Util.formatarMoedaRealparaBigDecimal(percentualDescontoPagamentoAVistaJuros);
			}catch(Exception ex){
				throw new ActionServletException("atencao.percentual_invalido", null, "de Desconto sobre os Acr�scimos por Impontualidade de Juros para Pagamento a Vista");
			}
		}
		
		String percentualDescontoPagamentoAVistaAtuMonetaria = null;
		if (atualizarParcelamentoPerfilActionForm.getPercentualDescontoPagamentoAVistaAtuMonetaria() != null && 
				!atualizarParcelamentoPerfilActionForm.getPercentualDescontoPagamentoAVistaAtuMonetaria().equals("") ) {
			percentualDescontoPagamentoAVistaAtuMonetaria = atualizarParcelamentoPerfilActionForm.getPercentualDescontoPagamentoAVistaAtuMonetaria().toString().replace(",",".");
			try{
				Util.formatarMoedaRealparaBigDecimal(percentualDescontoPagamentoAVistaAtuMonetaria);
			}catch(Exception ex){
				throw new ActionServletException("atencao.percentual_invalido", null, "de Desconto sobre os Acr�scimos por Impontualidade de Atualiza��o Monet�ria para Pagamento a Vista");
			}
		}
		
		String percentualValorContaConsumoMedioPrestacao = null;
		if (atualizarParcelamentoPerfilActionForm.getPercentualValorContaConsumoMedioPrestacao() != null) {
			percentualValorContaConsumoMedioPrestacao = atualizarParcelamentoPerfilActionForm.getPercentualValorContaConsumoMedioPrestacao().toString().replace(",",".");
		}
		
		
		String percentualDescontoAcrescimoPagamentoAVista = null;
		if (atualizarParcelamentoPerfilActionForm.getPercentualDescontoAcrescimoPagamentoAVista() != null
				&& !atualizarParcelamentoPerfilActionForm.getPercentualDescontoAcrescimoPagamentoAVista().equalsIgnoreCase("")) {
			percentualDescontoAcrescimoPagamentoAVista = atualizarParcelamentoPerfilActionForm
				.getPercentualDescontoAcrescimoPagamentoAVista().toString().replace(",", ".");
		}

		String percentualTarifaMinimaPrestacao = null;
		if (atualizarParcelamentoPerfilActionForm.getPercentualTarifaMinimaPrestacao() != null
				&& !atualizarParcelamentoPerfilActionForm.getPercentualTarifaMinimaPrestacao().equalsIgnoreCase("")) {
			percentualTarifaMinimaPrestacao = atualizarParcelamentoPerfilActionForm
				.getPercentualTarifaMinimaPrestacao().toString().replace(",", ".");
		}
		
		String percentualDescontoDebitoPagamentoAVista = null;
		if(atualizarParcelamentoPerfilActionForm.getPercentualDescontoDebitoPagamentoAVista() != null && 
				!atualizarParcelamentoPerfilActionForm.getPercentualDescontoDebitoPagamentoAVista().equals("")){
			percentualDescontoDebitoPagamentoAVista = atualizarParcelamentoPerfilActionForm.
					getPercentualDescontoDebitoPagamentoAVista().toString().replace(",", ".");
			try{
				BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(percentualDescontoDebitoPagamentoAVista);
			}catch(Exception ex){
				throw new ActionServletException("atencao.percentual_invalido",null, "de Desconto sobre valor d�bito para pagamento � vista");
			}
		}
		
		String percentualDescontoDebitoPagamentoParcelado = null;
		if(atualizarParcelamentoPerfilActionForm.getPercentualDescontoDebitoPagamentoParcelado() != null &&
				!atualizarParcelamentoPerfilActionForm.getPercentualDescontoDebitoPagamentoParcelado().equals("")){
			percentualDescontoDebitoPagamentoParcelado = atualizarParcelamentoPerfilActionForm.
					getPercentualDescontoDebitoPagamentoParcelado().toString().replace(",", ".");
			try{
				BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(percentualDescontoDebitoPagamentoParcelado);
			}catch(Exception ex){
				throw new ActionServletException("atencao.percentual_invalido", null, "de Desconto sobre valor d�bito para pagamento � parcelado");
			}
		}
		
		String limiteVencimentoContaPagamentoAVista = null;
		if(atualizarParcelamentoPerfilActionForm.getLimiteVencimentoContaPagamentoAVista() != null &&
				!atualizarParcelamentoPerfilActionForm.getLimiteVencimentoContaPagamentoAVista().equals("")){
			limiteVencimentoContaPagamentoAVista = atualizarParcelamentoPerfilActionForm.getLimiteVencimentoContaPagamentoAVista();
			
			SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");
			
			// joga em dataInicial a parte da data
			String dataInicial = dataFormato.format(new Date());
			
			Integer dataAtual = Util.formatarDiaMesAnoComBarraParaAnoMesDia(dataInicial);
			Integer dataLimiteVencimento = Util.formatarDiaMesAnoComBarraParaAnoMesDia(limiteVencimentoContaPagamentoAVista);
			
			if(dataLimiteVencimento < dataAtual){
				throw new ActionServletException("atencao.data_inferior_que_atual", null, "Limite Vencimento Conta Pagamento A Vista");
			}
		}
		
		String limiteVencimentoContaPagamentoParcelado = null;
		if(atualizarParcelamentoPerfilActionForm.getLimiteVencimentoContaPagamentoParcelado() != null &&
				!atualizarParcelamentoPerfilActionForm.getLimiteVencimentoContaPagamentoParcelado().equals("")){
			limiteVencimentoContaPagamentoParcelado = atualizarParcelamentoPerfilActionForm.getLimiteVencimentoContaPagamentoParcelado();
			
			SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");
			
			// joga em dataInicial a parte da data
			String dataInicial = dataFormato.format(new Date());
			
			Integer dataAtual = Util.formatarDiaMesAnoComBarraParaAnoMesDia(dataInicial);
			Integer dataLimiteVencimento = Util.formatarDiaMesAnoComBarraParaAnoMesDia(limiteVencimentoContaPagamentoParcelado);
			
			if(dataLimiteVencimento < dataAtual){
				throw new ActionServletException("atencao.data_inferior_que_atual", null, "Limite Vencimento Conta Pagamento Parcelado");
			}
		}
		
		if(atualizarParcelamentoPerfilActionForm.getAnoMesReferenciaLimiteInferior() != null 
			&& !atualizarParcelamentoPerfilActionForm.getAnoMesReferenciaLimiteInferior().equals("")){
			if(atualizarParcelamentoPerfilActionForm.getAnoMesReferenciaLimiteSuperior() != null
				&& !atualizarParcelamentoPerfilActionForm.getAnoMesReferenciaLimiteSuperior().equals("")){
				Integer anoMesInferior = Util.formatarMesAnoComBarraParaAnoMes(atualizarParcelamentoPerfilActionForm.getAnoMesReferenciaLimiteInferior());
				Integer anoMesSuperior = Util.formatarMesAnoComBarraParaAnoMes(atualizarParcelamentoPerfilActionForm.getAnoMesReferenciaLimiteSuperior());
				
				if(anoMesInferior > anoMesSuperior){
					throw new ActionServletException("atencao.mes_ano_inicial_maior_mes_ano_final");
				}
			}
		}

		atualizaColecoesNaSessao(sessao, httpServletRequest);

		// collectionParcelamentoQuantidadeReparcelamentoHelper
		Collection collectionParcelamentoQuantidadeReparcelamentoHelper = null;
		if (sessao.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper") != null) {
			collectionParcelamentoQuantidadeReparcelamentoHelper = (Collection) 
					sessao.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper");
		}

		// collectionParcelamentoDescontoInatividade
		Collection collectionParcelamentoDescontoInatividade = null;
		if (sessao.getAttribute("collectionParcelamentoDescontoInatividade") != null) {
			collectionParcelamentoDescontoInatividade = (Collection) 
					sessao.getAttribute("collectionParcelamentoDescontoInatividade");
		}
		
		// collectionParcelamentoDescontoInatividadeAVista
		Collection collectionParcelamentoDescontoInatividadeAVista = null;
		if (sessao.getAttribute("collectionParcelamentoDescontoInatividadeAVista") != null) {
			collectionParcelamentoDescontoInatividadeAVista = (Collection) 
					sessao.getAttribute("collectionParcelamentoDescontoInatividadeAVista");
		}

		// collectionParcelamentoDescontoAntiguidade
		Collection collectionParcelamentoDescontoAntiguidade = null;
		if (sessao.getAttribute("collectionParcelamentoDescontoAntiguidade") != null) {
			collectionParcelamentoDescontoAntiguidade = (Collection) 
					sessao.getAttribute("collectionParcelamentoDescontoAntiguidade");
		}

		//collectionTipoDebitosHelper
		Collection collectionTipoDebitosHelper = null;
		if (sessao.getAttribute("collectionTipoDebitosHelper") != null) {
			collectionTipoDebitosHelper = (Collection) sessao.getAttribute("collectionTipoDebitosHelper");
		}
		
		Collection collectionParcelamentoQuantidadeReparcelamentoHelperLinhaRemovidas = (Collection) 
				sessao.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelperLinhaRemovidas");

		Collection collectionParcelamentoDescontoInatividadeLinhaRemovidas = (Collection) 
				sessao.getAttribute("collectionParcelamentoDescontoInatividadeLinhaRemovidas");
		
		Collection collectionParcelamentoDescontoInatividadeAVistaLinhaRemovidas = (Collection) 
				sessao.getAttribute("collectionParcelamentoDescontoInatividadeAVistaLinhaRemovidas");

		Collection collectionParcelamentoDescontoAntiguidadeLinhaRemovidas = (Collection) 
				sessao.getAttribute("collectionParcelamentoDescontoAntiguidadeLinhaRemovidas");

		Collection collectionParcelamentoQuantidadePrestacaoHelperLinhaRemovidas = (Collection) 
				sessao.getAttribute("collectionParcelamentoQuantidadePrestacaoHelperLinhaRemovidas");

		Collection collectionTipoDebitoReparcelamentoHelperLinhaRemovidas = (Collection) 
				sessao.getAttribute("collectionTipoDebitoReparcelamentoHelperLinhaRemovidas");
		
		validacaoFinal(percentualTarifaMinimaPrestacao, httpServletRequest,
				collectionParcelamentoQuantidadeReparcelamentoHelper,
				collectionParcelamentoDescontoAntiguidade,
				collectionParcelamentoDescontoInatividade,
				idPerfilParcelamento, fachada,
				collectionParcelamentoDescontoInatividadeAVista);

		ParcelamentoPerfil parcelamentoPerfil = new ParcelamentoPerfil();

		parcelamentoPerfil.setId(new Integer(idPerfilParcelamento));

		ResolucaoDiretoria resolucaoDiretoria = null;
		if (numeroResolucaoDiretoria != null
				&& !numeroResolucaoDiretoria.equals("")) {

			FiltroResolucaoDiretoria filtroResolucaoDiretoria = new FiltroResolucaoDiretoria();
			filtroResolucaoDiretoria.adicionarParametro(new ParametroSimples(
					FiltroResolucaoDiretoria.NUMERO, numeroResolucaoDiretoria));
			Collection<ResolucaoDiretoria> collectionResolucaoDiretoria = fachada
					.pesquisar(filtroResolucaoDiretoria,ResolucaoDiretoria.class.getName());

			if (collectionResolucaoDiretoria != null
					&& !collectionResolucaoDiretoria.isEmpty()) {
				resolucaoDiretoria = (ResolucaoDiretoria) Util.retonarObjetoDeColecao(collectionResolucaoDiretoria);
				parcelamentoPerfil.setResolucaoDiretoria(resolucaoDiretoria);
			}
		}

		ImovelSituacaoTipo imovelSituacaoTipo = null;
		if (idImovelSituacaoTipo != null
				&& !idImovelSituacaoTipo.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			imovelSituacaoTipo = new ImovelSituacaoTipo();
			imovelSituacaoTipo.setId(new Integer(idImovelSituacaoTipo));
			parcelamentoPerfil.setImovelSituacaoTipo(imovelSituacaoTipo);
		}

		ImovelPerfil imovelPerfil = null;
		if (idImovelPerfil != null
				&& !idImovelPerfil.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			imovelPerfil = new ImovelPerfil();
			imovelPerfil.setId(new Integer(idImovelPerfil));
			parcelamentoPerfil.setImovelPerfil(imovelPerfil);
		}

		Subcategoria subcategoria = null;
		if (idSubcategoria != null
				&& !idSubcategoria.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			subcategoria = new Subcategoria();
			subcategoria.setId(new Integer(idSubcategoria));
			parcelamentoPerfil.setSubcategoria(subcategoria);
		}

		Categoria categoria = null;
		if (idCategoria != null && !idCategoria.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			categoria = new Categoria();
			categoria.setId(new Integer(idCategoria));
			parcelamentoPerfil.setCategoria(categoria);
		}

		if (percentualDescontoAcrescimo != null && !percentualDescontoAcrescimo.equals("")) {
			
			try{
				BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(percentualDescontoAcrescimo);
				verificarPercentualMaximo(percentual);
				parcelamentoPerfil.setPercentualDescontoAcrescimo(percentual);
			}catch(Exception ex){
				throw new ActionServletException("atencao.percentual_invalido",null, "de Desconto sobre os Acr�scimos por Impontualidade");
			}
		} else {
			parcelamentoPerfil.setPercentualDescontoAcrescimo(new BigDecimal(0));
		}

		if (percentualDescontoMulta != null && !percentualDescontoMulta.equals("")) {
			BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(percentualDescontoMulta);
			verificarPercentualMaximo(percentual);
			parcelamentoPerfil.setPercentualDescontoMulta(percentual);
		} else {
			parcelamentoPerfil.setPercentualDescontoMulta(new BigDecimal(0));
		}
		
		if (percentualDescontoJuros != null && !percentualDescontoJuros.equals("")) {
			BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(percentualDescontoJuros);
			verificarPercentualMaximo(percentual);
			parcelamentoPerfil.setPercentualDescontoJuros(percentual);
		} else {
			parcelamentoPerfil.setPercentualDescontoJuros(new BigDecimal(0));
		}
		
		if (percentualDescontoAtualizacaoMonetaria != null && !percentualDescontoAtualizacaoMonetaria.equals("")) {
			BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(percentualDescontoAtualizacaoMonetaria);
			verificarPercentualMaximo(percentual);
			parcelamentoPerfil.setPercentualDescontoAtualizacaoMonetaria(percentual);
		} else {
			parcelamentoPerfil.setPercentualDescontoAtualizacaoMonetaria(new BigDecimal(0));
		}
		
		if (percentualDescontoPagamentoAVistaMulta != null && !percentualDescontoPagamentoAVistaMulta.equals("")) {
			BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(percentualDescontoPagamentoAVistaMulta);
			verificarPercentualMaximo(percentual);
			parcelamentoPerfil.setPercentualDescontoPagamentoAVistaMulta(percentual);
		} else {
			parcelamentoPerfil.setPercentualDescontoPagamentoAVistaMulta(new BigDecimal(0));
		}
		
		if (percentualDescontoPagamentoAVistaJuros != null && !percentualDescontoPagamentoAVistaJuros.equals("")) {
			BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(percentualDescontoPagamentoAVistaJuros);
			verificarPercentualMaximo(percentual);
			parcelamentoPerfil.setPercentualDescontoPagamentoAVistaJuros(percentual);
		} else {
			parcelamentoPerfil.setPercentualDescontoPagamentoAVistaJuros(new BigDecimal(0));
		}
		
		if (percentualDescontoPagamentoAVistaAtuMonetaria != null && !percentualDescontoPagamentoAVistaAtuMonetaria.equals("")) {
			try{
				BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(percentualDescontoPagamentoAVistaAtuMonetaria);
				verificarPercentualMaximo(percentual);
				parcelamentoPerfil.setPercentualDescontoPagamentoAVistaAtuMonetaria(percentual);
			}catch(Exception ex){
				throw new ActionServletException("atencao.percentual_invalido",null, "Desconto para Pagamento a Vista de Atualiza��o Monet�ria");
			}
		} else {
			parcelamentoPerfil.setPercentualDescontoPagamentoAVistaAtuMonetaria(new BigDecimal(0));
		}
		
		if (percentualValorContaConsumoMedioPrestacao != null && !percentualValorContaConsumoMedioPrestacao.equals("")) {
			try{
				BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(percentualValorContaConsumoMedioPrestacao);
				verificarPercentualMaximo(percentual);
				parcelamentoPerfil.setPercentualValorContaConsumoMedioPrestacao(percentual);
			}catch(Exception ex){
				throw new ActionServletException("atencao.percentual_invalido",null, "do Valor da Conta - Consumo M�dio");
			}
		} else {
			parcelamentoPerfil.setPercentualValorContaConsumoMedioPrestacao(new BigDecimal(0));
		}
		
		if (percentualDescontoAcrescimoPagamentoAVista != null && !percentualDescontoAcrescimoPagamentoAVista.equals("")) {
			try{

				BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(percentualDescontoAcrescimoPagamentoAVista);
				verificarPercentualMaximo(percentual);
				parcelamentoPerfil.setPercentualDescontoAVista(percentual);
			}catch(Exception ex){
				throw new ActionServletException("atencao.percentual_invalido",null, "de Desconto sobre os Acr�scimos por Impontualidade para Pagamento � Vista");
			}
			
		} else {
			parcelamentoPerfil.setPercentualDescontoAVista(new BigDecimal(0));
		}

		if (percentualTarifaMinimaPrestacao != null && !percentualTarifaMinimaPrestacao.equalsIgnoreCase("")) {
			try{
				BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(percentualTarifaMinimaPrestacao);
				verificarPercentualMaximo(percentual);
				parcelamentoPerfil.setPercentualTarifaMinimaPrestacao(percentual);
			}catch(Exception ex){
				throw new ActionServletException("atencao.percentual_invalido",null, "da Tarifa M�nima para C�lculo do Valor M�nimo da Presta��o");
			}
		} else {
			parcelamentoPerfil.setPercentualTarifaMinimaPrestacao(new BigDecimal(0));
		}

		if (atualizarParcelamentoPerfilActionForm.getIndicadorParcelarChequeDevolvido() != null
				&& !atualizarParcelamentoPerfilActionForm.getIndicadorParcelarChequeDevolvido().equals("")) {
			parcelamentoPerfil.setIndicadorChequeDevolvido(
				new Short(atualizarParcelamentoPerfilActionForm.getIndicadorParcelarChequeDevolvido()));
		} else {
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "N�o parcelar com cheque devolvido");
		}

		if (atualizarParcelamentoPerfilActionForm.getConsumoMinimo() != null
				&& !atualizarParcelamentoPerfilActionForm.getConsumoMinimo().equals("")) {
			parcelamentoPerfil.setNumeroConsumoMinimo(new Integer(atualizarParcelamentoPerfilActionForm.getConsumoMinimo()));
		}

		if (atualizarParcelamentoPerfilActionForm.getPercentualVariacaoConsumoMedio() != null
				&& !atualizarParcelamentoPerfilActionForm.getPercentualVariacaoConsumoMedio().equals("")) {
			BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(atualizarParcelamentoPerfilActionForm.getPercentualVariacaoConsumoMedio());
			verificarPercentualMaximo(percentual);
			parcelamentoPerfil.setPercentualVariacaoConsumoMedio(percentual);
		}

		if (atualizarParcelamentoPerfilActionForm.getIndicadorParcelarSancoesMaisDeUmaConta() != null
				&& !atualizarParcelamentoPerfilActionForm.getIndicadorParcelarSancoesMaisDeUmaConta().equals("")) {
			parcelamentoPerfil.setIndicadorSancoesUnicaConta(
					new Short(atualizarParcelamentoPerfilActionForm.getIndicadorParcelarSancoesMaisDeUmaConta()));
		}else {
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null,"N�o parcelar com san��es em mais de uma conta");
		}

		if (atualizarParcelamentoPerfilActionForm.getNumeroConsumoEconomia() != null
				&& !atualizarParcelamentoPerfilActionForm.getNumeroConsumoEconomia().trim().equals("")) {
			parcelamentoPerfil.setNumeroConsumoEconomia(new Integer(atualizarParcelamentoPerfilActionForm.getNumeroConsumoEconomia()));
		}

		if (atualizarParcelamentoPerfilActionForm.getNumeroAreaConstruida() != null
				&& !atualizarParcelamentoPerfilActionForm.getNumeroAreaConstruida().trim().equals("")) {
			parcelamentoPerfil.setNumeroAreaConstruida(
					Util.formatarMoedaRealparaBigDecimal(atualizarParcelamentoPerfilActionForm.getNumeroAreaConstruida()));
		}

		if (atualizarParcelamentoPerfilActionForm.getIndicadorRetroativoTarifaSocial() != null
				&& !atualizarParcelamentoPerfilActionForm.getIndicadorRetroativoTarifaSocial().equals("")) {
			parcelamentoPerfil.setIndicadorRetroativoTarifaSocial(
					new Short(atualizarParcelamentoPerfilActionForm.getIndicadorRetroativoTarifaSocial()));
		} else {
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Indicador de retroativo de tarifa social");
		}

		if (atualizarParcelamentoPerfilActionForm.getAnoMesReferenciaLimiteInferior() != null
				&& !atualizarParcelamentoPerfilActionForm.getAnoMesReferenciaLimiteInferior().trim().equals("")) {
			parcelamentoPerfil.setAnoMesReferenciaLimiteInferior(new Integer(
				Util.formatarMesAnoParaAnoMesSemBarra(atualizarParcelamentoPerfilActionForm.getAnoMesReferenciaLimiteInferior())));
		}

		if (atualizarParcelamentoPerfilActionForm.getAnoMesReferenciaLimiteSuperior() != null
				&& !atualizarParcelamentoPerfilActionForm.getAnoMesReferenciaLimiteSuperior().trim().equals("")) {
			parcelamentoPerfil.setAnoMesReferenciaLimiteSuperior(new Integer(
				Util.formatarMesAnoParaAnoMesSemBarra(atualizarParcelamentoPerfilActionForm.getAnoMesReferenciaLimiteSuperior())));
		}

		if (atualizarParcelamentoPerfilActionForm.getPercentualDescontoAVista() != null
				&& !atualizarParcelamentoPerfilActionForm.getPercentualDescontoAVista().trim().equals("")) {
			
			try{
				BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(atualizarParcelamentoPerfilActionForm.getPercentualDescontoAVista());
				verificarPercentualMaximo(percentual);
				parcelamentoPerfil.setPercentualDescontoAVista(percentual);
			}catch(Exception ex){
				throw new ActionServletException("atencao.percentual_invalido",null, "de desconto tarifa social");
			}
		}

		if (atualizarParcelamentoPerfilActionForm.getParcelaQuantidadeMinimaFatura() != null
				&& !atualizarParcelamentoPerfilActionForm.getParcelaQuantidadeMinimaFatura().trim().equals("")) {
			parcelamentoPerfil.setParcelaQuantidadeMinimaFatura(
					new Integer(atualizarParcelamentoPerfilActionForm.getParcelaQuantidadeMinimaFatura()));
		}

		if (atualizarParcelamentoPerfilActionForm.getIndicadorAlertaParcelaMinima() != null
				&& !atualizarParcelamentoPerfilActionForm.getIndicadorAlertaParcelaMinima().equals("")) {
			parcelamentoPerfil.setIndicadorAlertaParcelaMinima(new Short(
					atualizarParcelamentoPerfilActionForm.getIndicadorAlertaParcelaMinima()));
		} else {
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Indicador de alerta de parcela m�nima");
		}

		if (atualizarParcelamentoPerfilActionForm.getPercentualDescontoSancao() != null
				&& !atualizarParcelamentoPerfilActionForm.getPercentualDescontoSancao().trim().equals("")) {
			BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(atualizarParcelamentoPerfilActionForm.getPercentualDescontoSancao());
			verificarPercentualMaximo(percentual);
			parcelamentoPerfil.setPercentualDescontoSancao(percentual);
		}

		if (atualizarParcelamentoPerfilActionForm.getQuantidadeEconomias() != null
				&& !atualizarParcelamentoPerfilActionForm.getQuantidadeEconomias().trim().equals("")) {
			parcelamentoPerfil.setQuantidadeEconomias(new Integer(atualizarParcelamentoPerfilActionForm.getQuantidadeEconomias()));
		}

		if (atualizarParcelamentoPerfilActionForm.getQuantidadeMaximaReparcelamento() != null
				&& !atualizarParcelamentoPerfilActionForm.getQuantidadeMaximaReparcelamento().trim().equals("")) {
			parcelamentoPerfil.setQuantidadeMaximaReparcelamento(new Integer(
					atualizarParcelamentoPerfilActionForm.getQuantidadeMaximaReparcelamento()));
		}

		if (atualizarParcelamentoPerfilActionForm.getIndicadorEntradaMinima() != null
				&& !atualizarParcelamentoPerfilActionForm.getIndicadorEntradaMinima().equals("")) {
			parcelamentoPerfil.setIndicadorEntradaMinima(new Short(
					atualizarParcelamentoPerfilActionForm.getIndicadorEntradaMinima()));
		} else {
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Indicador de entrada m�nima");
		}

		if (atualizarParcelamentoPerfilActionForm.getCapacidadeHidrometro() != null
				&& !atualizarParcelamentoPerfilActionForm.getCapacidadeHidrometro().trim().equals("")) {
			parcelamentoPerfil.setCapacidadeHidrometro(new Short(atualizarParcelamentoPerfilActionForm.getCapacidadeHidrometro()));
		} else {
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Indicador pesquisa capacidade do hidrometro");
		}
		
		if (atualizarParcelamentoPerfilActionForm.getDataLimiteDescontoPagamentoAVista()  != null && 
        		!atualizarParcelamentoPerfilActionForm.getDataLimiteDescontoPagamentoAVista().trim().equals("")){
			parcelamentoPerfil.setDataLimiteDescontoPagamentoAVista(
        			Util.converteStringParaDate(atualizarParcelamentoPerfilActionForm.getDataLimiteDescontoPagamentoAVista()));
        }
		
		if(percentualDescontoDebitoPagamentoAVista != null){
			BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(percentualDescontoDebitoPagamentoAVista);
			verificarPercentualMaximo(percentual);
			parcelamentoPerfil.setPercentualDescontoDebitoPagamentoAVista(percentual);
		}else{
			parcelamentoPerfil.setPercentualDescontoDebitoPagamentoAVista(new BigDecimal(0));
		}
        
		if(percentualDescontoDebitoPagamentoParcelado != null){
			BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(percentualDescontoDebitoPagamentoParcelado);
			verificarPercentualMaximo(percentual);
			parcelamentoPerfil.setPercentualDescontoDebitoPagamentoParcelado(percentual);
		}else{
			parcelamentoPerfil.setPercentualDescontoDebitoPagamentoParcelado(new BigDecimal(0));
		}
		
		if(limiteVencimentoContaPagamentoAVista != null){
			parcelamentoPerfil.setDataLimiteVencimentoContaAVista(Util.converteStringParaDate(limiteVencimentoContaPagamentoAVista));
		}else{
			parcelamentoPerfil.setDataLimiteVencimentoContaAVista(null);
		}
		
		if(limiteVencimentoContaPagamentoParcelado != null){
			parcelamentoPerfil.setDataLimiteVencimentoContaParcelado(Util.converteStringParaDate(limiteVencimentoContaPagamentoParcelado));
		}else{
			parcelamentoPerfil.setDataLimiteVencimentoContaParcelado(null);
		}

		parcelamentoPerfil.setUltimaAlteracao(Util
				.converteStringParaDateHora(atualizarParcelamentoPerfilActionForm.getUltimaAlteracao()));

		if (atualizarParcelamentoPerfilActionForm.getAnoMesReferenciaLimiteContaPagamentoAVista() != null
				&& !atualizarParcelamentoPerfilActionForm.getAnoMesReferenciaLimiteContaPagamentoAVista().trim().equals("")) {
			parcelamentoPerfil.setAnoMesReferenciaLimiteContaPagamentoAVista(new Integer(
				Util.formatarMesAnoParaAnoMesSemBarra(atualizarParcelamentoPerfilActionForm.getAnoMesReferenciaLimiteContaPagamentoAVista())));
		}
		
		if (atualizarParcelamentoPerfilActionForm.getAnoMesReferenciaLimiteContaParcelada() != null
				&& !atualizarParcelamentoPerfilActionForm.getAnoMesReferenciaLimiteContaParcelada().trim().equals("")) {
			parcelamentoPerfil.setAnoMesReferenciaLimiteContaParcelada(new Integer(
				Util.formatarMesAnoParaAnoMesSemBarra(atualizarParcelamentoPerfilActionForm.getAnoMesReferenciaLimiteContaParcelada())));
		}
		
		
		fachada.atualizarPerfilParcelamento(
						parcelamentoPerfil,
						collectionParcelamentoQuantidadeReparcelamentoHelper,
						collectionParcelamentoDescontoInatividade,
						collectionParcelamentoDescontoAntiguidade,
						collectionParcelamentoQuantidadeReparcelamentoHelperLinhaRemovidas,
						collectionParcelamentoDescontoInatividadeLinhaRemovidas,
						collectionParcelamentoDescontoAntiguidadeLinhaRemovidas,
						collectionParcelamentoQuantidadePrestacaoHelperLinhaRemovidas,
						this.getUsuarioLogado(httpServletRequest),
						collectionParcelamentoDescontoInatividadeAVista,
						collectionParcelamentoDescontoInatividadeAVistaLinhaRemovidas,
						collectionTipoDebitosHelper,
						collectionTipoDebitoReparcelamentoHelperLinhaRemovidas);

		sessao.removeAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper");
		sessao.removeAttribute("collectionParcelamentoDescontoInatividade");
		sessao.removeAttribute("collectionParcelamentoDescontoAntiguidade");
		sessao.removeAttribute("idRegistroAtualizacao");
		sessao.removeAttribute("collectionParcelamentoQuantidadeReparcelamentoHelperLinhaRemovidas");
		sessao.removeAttribute("collectionParcelamentoDescontoInatividadeLinhaRemovidas");
		sessao.removeAttribute("collectionParcelamentoDescontoAntiguidadeLinhaRemovidas");
		sessao.removeAttribute("collectionParcelamentoQuantidadePrestacaoHelperLinhaRemovidas");
		sessao.removeAttribute("collectionParcelamentoDescontoInatividadeAVista");
		sessao.removeAttribute("collectionParcelamentoDescontoInatividadeAVistaLinhaRemovidas");
		sessao.removeAttribute("collectionTipoDebitosHelper");
		sessao.removeAttribute("collectionTipoDebitoReparcelamentoHelperLinhaRemovidas");

		//
		// Monta a p�gina de sucesso
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
			montarPaginaSucesso(httpServletRequest,
					"Perfil de Parcelamento da RD de n�mero "
							+ numeroResolucaoDiretoria
							+ " atualizado com sucesso.",
					"Realizar outra Manuten��o de Perfil de Parcelamento",
					"exibirFiltrarPerfilParcelamentoAction.do?desfazer=S");
		}

		return retorno;
	}

	private void validacaoFinal(String percentualTarifaMinimaPrestacao,
			HttpServletRequest httpServletRequest,
			Collection collectionParcelamentoQuantidadeReparcelamentoHelper,
			Collection collectionParcelamentoDescontoAntiguidade,
			Collection collectionParcelamentoDescontoInatividade,
			String idPerfilParcelamento, Fachada fachada,
			Collection collectionParcelamentoDescontoInatividadeAVista) {

		/*
		 * if (percentualDescontoAcrescimo == null &&
		 * percentualDescontoAcrescimo.equalsIgnoreCase("")){
		 * httpServletRequest.setAttribute("nomeCampo","percentualDescontoAcrescimo");
		 * //Informe Percentual de Desconto sobre os Acr�scimos por
		 * Impontualidade throw new ActionServletException(
		 * "atencao.percentual_desconto_nao_informado"); }
		 */

		if (percentualTarifaMinimaPrestacao == null
				|| percentualTarifaMinimaPrestacao.equalsIgnoreCase("")) {
			httpServletRequest.setAttribute("nomeCampo",
					"percentualTarifaMinimaPrestacao");
			// Informe Percentual da Tarifa M�nima para C�lculo do Valor M�nimo
			// da Presta��o
			throw new ActionServletException("atencao.required", null,
					" Percentual da Tarifa M�nima para C�lculo do Valor M�nimo da Presta��o");
		}

		// [FS0002]Verificar se perfil de parcelamento j� foi utilizado
		// FiltroParcelamento filtroParcelamento = new FiltroParcelamento();
		//
		// filtroParcelamento.adicionarParametro(new ParametroSimples(
		// FiltroParcelamento.PARCELAMENTO_PERFIL_ID, idPerfilParcelamento));
		//
		// Collection colecaoParcelamento =
		// fachada.pesquisar(filtroParcelamento,
		// Parcelamento.class.getName());
		//
		// if (colecaoParcelamento != null && !colecaoParcelamento.isEmpty()) {
		// //Perfil de Parcelamento j� utilizado, n�o pode ser alterado nem
		// exclu�do.
		// throw new ActionServletException(
		// "atencao.perfil_parcelamento_ja_utilizado");
		// }
		//		

		if (collectionParcelamentoQuantidadeReparcelamentoHelper == null
				|| collectionParcelamentoQuantidadeReparcelamentoHelper
						.isEmpty()) {
			throw new ActionServletException(
			// Informe os dados de Reparcelamento Consecutivo
					"atencao.required", null, " Reparcelamento Consecutivo");
		}
		Iterator iterator = collectionParcelamentoQuantidadeReparcelamentoHelper
				.iterator();

		while (iterator.hasNext()) {

			ParcelamentoQuantidadeReparcelamentoHelper parcelamentoQuantidadeReparcelamentoHelper = (ParcelamentoQuantidadeReparcelamentoHelper) iterator
					.next();
			/*
			 * if
			 * (parcelamentoQuantidadeReparcelamentoHelper.getValorMinimoPrestacao() ==
			 * null){ throw new ActionServletException( // Informe Valor
			 * M�nimo da Presta��o "atencao.required", null, " Valor M�nimo
			 * da Presta��o"); }
			 */
			Collection collectionParcelamentoQuantidadePrestacaoHelper = parcelamentoQuantidadeReparcelamentoHelper
					.getCollectionParcelamentoQuantidadePrestacaoHelper();

			if (collectionParcelamentoQuantidadePrestacaoHelper == null
					|| collectionParcelamentoQuantidadePrestacaoHelper
							.isEmpty()) {
				throw new ActionServletException(
				// Informa��es do Parcelamento por Quantidade de
				// Reparcelamentos deve ser informado
						"atencao.campo.informado", null,
						"Informa��es do Parcelamento por Quantidade de Reparcelamentos");
			}
		}

		// filtro para descobrir o percentual m�ximo de desconto permitido para
		// financiamento
		/*
		 * FiltroSistemaParametro filtroSistemaParametro = new
		 * FiltroSistemaParametro(); Collection colecaoSistemaParametros;
		 * 
		 * colecaoSistemaParametros = fachada.pesquisar( filtroSistemaParametro,
		 * SistemaParametro.class.getName()); SistemaParametro sistemaParametro =
		 * (SistemaParametro)colecaoSistemaParametros .iterator().next();
		 * BigDecimal percentualMaximoAbatimentoPermitido =
		 * sistemaParametro.getPercentualMaximoAbatimento();
		 */

		if (collectionParcelamentoDescontoAntiguidade != null && !collectionParcelamentoDescontoAntiguidade.isEmpty()) {

			Iterator iteratorParcelamentoDescontoAntiguidade = collectionParcelamentoDescontoAntiguidade
					.iterator();

			while (iteratorParcelamentoDescontoAntiguidade.hasNext()) {

				ParcelamentoDescontoAntiguidade parcelamentoDescontoAntiguidade = (ParcelamentoDescontoAntiguidade) 
						iteratorParcelamentoDescontoAntiguidade.next();

				if (parcelamentoDescontoAntiguidade
						.getPercentualDescontoSemRestabelecimento() == null) {
					throw new ActionServletException(
					// Percentual de Desconto Sem Restabelecimento
							"atencao.required", null,
							"  Percentual de Desconto Sem Restabelecimento");
				}

				if (parcelamentoDescontoAntiguidade
						.getPercentualDescontoComRestabelecimento() == null) {
					throw new ActionServletException(
					// Informe Percentual de Desconto Com Restabelecimento
							"atencao.required", null,
							"  Percentual de Desconto Com Restabelecimento");
				}

				if (parcelamentoDescontoAntiguidade
						.getPercentualDescontoAtivo() == null) {
					throw new ActionServletException(
					// Informe Percentual de Desconto Ativo
							"atencao.required", null,
							"  Percentual de Desconto Ativo");
				}
			}

		}

		if (collectionParcelamentoDescontoInatividade != null
				&& !collectionParcelamentoDescontoInatividade.isEmpty()) {

			Iterator iteratorParcelamentoDescontoInatividade = collectionParcelamentoDescontoInatividade
					.iterator();

			while (iteratorParcelamentoDescontoInatividade.hasNext()) {

				ParcelamentoDescontoInatividade parcelamentoDescontoInatividade = (ParcelamentoDescontoInatividade) 
						iteratorParcelamentoDescontoInatividade.next();

				if (parcelamentoDescontoInatividade
						.getPercentualDescontoSemRestabelecimento() == null) {
					throw new ActionServletException(
					// Percentual de Desconto Sem Restabelecimento
							"atencao.required", null,
							"  Percentual de Desconto Sem Restabelecimento");
				}

				if (parcelamentoDescontoInatividade
						.getPercentualDescontoComRestabelecimento() == null) {
					throw new ActionServletException(
					// Informe Percentual de Desconto Com Restabelecimento
							"atencao.required", null,
							"  Percentual de Desconto Com Restabelecimento");
				}
			}
		}
		
		if (collectionParcelamentoDescontoInatividadeAVista != null
				&& !collectionParcelamentoDescontoInatividadeAVista.isEmpty()) {

			Iterator iteratorParcelamentoDescontoInatividade = collectionParcelamentoDescontoInatividadeAVista
					.iterator();

			while (iteratorParcelamentoDescontoInatividade.hasNext()) {

				ParcDesctoInativVista parcelamentoDescontoInatividade = 
					(ParcDesctoInativVista) iteratorParcelamentoDescontoInatividade.next();

				if (parcelamentoDescontoInatividade.getPercentualDescontoSemRestabelecimento() == null) {
					throw new ActionServletException(
					// Percentual de Desconto Sem Restabelecimento
							"atencao.required", null,
							"  Percentual de Desconto Sem Restabelecimento");
				}

				if (parcelamentoDescontoInatividade
						.getPercentualDescontoComRestabelecimento() == null) {
					throw new ActionServletException(
					// Informe Percentual de Desconto Com Restabelecimento
							"atencao.required", null,
							"  Percentual de Desconto Com Restabelecimento");
				}
			}
		}

	}

	private void atualizaColecoesNaSessao(HttpSession sessao,
			HttpServletRequest httpServletRequest) {

		// collectionParcelamentoDescontoAntiguidade
		if (sessao.getAttribute("collectionParcelamentoDescontoAntiguidade") != null
				&& !sessao.getAttribute(
						"collectionParcelamentoDescontoAntiguidade").equals("")) {

			Collection collectionParcelamentoDescontoAntiguidade = (Collection) sessao
					.getAttribute("collectionParcelamentoDescontoAntiguidade");
			// cria as vari�veis para recuperar os par�metros do request e jogar
			// no objeto ParcelamentoDescontoAntiguidade
			String vlSemRestAntiguidade = null;
			String vlComRestAntiguidade = null;
			String vlDescontoAtivo = null;

			Iterator iteratorParcelamentoDescontoAntiguidade = collectionParcelamentoDescontoAntiguidade
					.iterator();

			while (iteratorParcelamentoDescontoAntiguidade.hasNext()) {

				ParcelamentoDescontoAntiguidade parcelamentoDescontoAntiguidade = (ParcelamentoDescontoAntiguidade) 
						iteratorParcelamentoDescontoAntiguidade.next();
				long valorTempo = parcelamentoDescontoAntiguidade
						.getUltimaAlteracao().getTime();
				vlSemRestAntiguidade = httpServletRequest
						.getParameter("vlSemRestAntiguidade" + valorTempo);
				vlComRestAntiguidade = httpServletRequest
						.getParameter("vlComRestAntiguidade" + valorTempo);
				vlDescontoAtivo = httpServletRequest
						.getParameter("vlDescontoAtivo" + valorTempo);

				// inseri essas vari�veis no objeto
				// ParcelamentoDescontoAntiguidade
				BigDecimal percentualDescontoSemRestabelecimentoAntiguidade = null;
				if (vlSemRestAntiguidade != null && !vlSemRestAntiguidade.equals("")) {
					percentualDescontoSemRestabelecimentoAntiguidade = Util.formatarMoedaRealparaBigDecimal(vlSemRestAntiguidade);
					verificarPercentualMaximo(percentualDescontoSemRestabelecimentoAntiguidade);
				}

				BigDecimal percentualDescontoComRestabelecimentoAntiguidade = null;
				if (vlComRestAntiguidade != null && !vlComRestAntiguidade.equals("")) {
					percentualDescontoComRestabelecimentoAntiguidade = Util.formatarMoedaRealparaBigDecimal(vlComRestAntiguidade);
					verificarPercentualMaximo(percentualDescontoComRestabelecimentoAntiguidade);
				}

				BigDecimal percentualDescontoAtivoAntiguidade = null;
				if (vlDescontoAtivo != null && !vlDescontoAtivo.equals("")) {
					percentualDescontoAtivoAntiguidade = Util.formatarMoedaRealparaBigDecimal(vlDescontoAtivo);
					verificarPercentualMaximo(percentualDescontoAtivoAntiguidade);
				}

				parcelamentoDescontoAntiguidade.setPercentualDescontoSemRestabelecimento(percentualDescontoSemRestabelecimentoAntiguidade);
				parcelamentoDescontoAntiguidade.setPercentualDescontoComRestabelecimento(percentualDescontoComRestabelecimentoAntiguidade);
				parcelamentoDescontoAntiguidade.setPercentualDescontoAtivo(percentualDescontoAtivoAntiguidade);
			}
		}

		// collectionParcelamentoDescontoInatividade
		if (sessao.getAttribute("collectionParcelamentoDescontoInatividade") != null
				&& !sessao.getAttribute("collectionParcelamentoDescontoInatividade").equals("")) {

			Collection collectionParcelamentoDescontoInatividade = (Collection) sessao
					.getAttribute("collectionParcelamentoDescontoInatividade");
			// cria as vari�veis para recuperar os par�metros do request e jogar
			// no objeto ParcelamentoDescontoInatividade
			String vlSemRestInatividade = null;
			String vlComRestInatividade = null;

			Iterator iteratorParcelamentoDescontoInatividade = collectionParcelamentoDescontoInatividade
					.iterator();

			while (iteratorParcelamentoDescontoInatividade.hasNext()) {

				ParcelamentoDescontoInatividade parcelamentoDescontoInatividade = (ParcelamentoDescontoInatividade) 
						iteratorParcelamentoDescontoInatividade.next();
				
				long valorTempo = parcelamentoDescontoInatividade.getUltimaAlteracao().getTime();
				vlSemRestInatividade = httpServletRequest.getParameter("vlSemRestInatividade" + valorTempo);
				vlComRestInatividade = httpServletRequest.getParameter("vlComRestInatividade" + valorTempo);

				// insere essas vari�veis no objeto
				// ParcelamentoDescontoInatividade
				BigDecimal percentualDescontoSemRestabelecimentoInatividade = null;
				if (vlSemRestInatividade != null && !vlSemRestInatividade.equals("")) {
					percentualDescontoSemRestabelecimentoInatividade = Util.formatarMoedaRealparaBigDecimal(vlSemRestInatividade);
					verificarPercentualMaximo(percentualDescontoSemRestabelecimentoInatividade);
				}

				BigDecimal percentualDescontoComRestabelecimentoInatividade = null;
				if (vlComRestInatividade != null && !vlComRestInatividade.equals("")) {
					percentualDescontoComRestabelecimentoInatividade = Util.formatarMoedaRealparaBigDecimal(vlComRestInatividade);
					verificarPercentualMaximo(percentualDescontoComRestabelecimentoInatividade);
				}

				parcelamentoDescontoInatividade.setPercentualDescontoSemRestabelecimento(percentualDescontoSemRestabelecimentoInatividade);
				parcelamentoDescontoInatividade.setPercentualDescontoComRestabelecimento(percentualDescontoComRestabelecimentoInatividade);
			}
		}
		
    	//collectionParcelamentoDescontoInatividadeAVista
    	if (sessao.getAttribute("collectionParcelamentoDescontoInatividadeAVista") != null 
    			&& !sessao.getAttribute("collectionParcelamentoDescontoInatividadeAVista").equals("")) {
	
			Collection collectionParcelamentoDescontoInatividadeAVista = (Collection) 
					sessao.getAttribute("collectionParcelamentoDescontoInatividadeAVista");
			// cria as vari�veis para recuperar os par�metros do request e jogar
			// no objeto  ParcelamentoDescontoInatividade
			String vlSemRestInatividade = null;
			String vlComRestInatividade = null;

			Iterator iteratorParcelamentoDescontoInatividade = collectionParcelamentoDescontoInatividadeAVista.iterator();
			
			while (iteratorParcelamentoDescontoInatividade.hasNext()) {
				ParcDesctoInativVista parcelamentoDescontoInatividade = 
						(ParcDesctoInativVista) iteratorParcelamentoDescontoInatividade.next();
				
				long valorTempo = parcelamentoDescontoInatividade.getUltimaAlteracao().getTime();
				vlSemRestInatividade = (String) httpServletRequest.getParameter("vlSemRestInatividadeAVista"+ valorTempo);
				vlComRestInatividade = httpServletRequest.getParameter("vlComRestInatividadeAVista"+ valorTempo);
				
				// insere essas vari�veis no objeto ParcelamentoDescontoInatividade
				BigDecimal percentualDescontoSemRestabelecimentoInatividade  = null;
				if (vlSemRestInatividade != null && !vlSemRestInatividade.equals("")) {
					percentualDescontoSemRestabelecimentoInatividade = Util.formatarMoedaRealparaBigDecimal(vlSemRestInatividade);
					verificarPercentualMaximo(percentualDescontoSemRestabelecimentoInatividade);
				}
				
				BigDecimal percentualDescontoComRestabelecimentoInatividade = null;
				if (vlComRestInatividade != null && !vlComRestInatividade.equals("")) {
					percentualDescontoComRestabelecimentoInatividade = Util.formatarMoedaRealparaBigDecimal(vlComRestInatividade);
					verificarPercentualMaximo(percentualDescontoComRestabelecimentoInatividade);
				}

				parcelamentoDescontoInatividade.setPercentualDescontoSemRestabelecimento(percentualDescontoSemRestabelecimentoInatividade);
				parcelamentoDescontoInatividade.setPercentualDescontoComRestabelecimento(percentualDescontoComRestabelecimentoInatividade);

			}

        }	        	

		// collectionParcelamentoQuantidadeReparcelamentoHelper
		/*
		 * if
		 * (sessao.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper") !=
		 * null &&
		 * !sessao.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper").equals(
		 * "")) {
		 * 
		 * Collection collectionParcelamentoQuantidadeReparcelamentoHelper =
		 * (Collection) sessao
		 * .getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper"); //
		 * cria as vari�veis para recuperar os par�metros do request e jogar //
		 * no objeto ParcelamentoQuantidadeReparcelamentoHelper String
		 * vlMinPrest = null;
		 * 
		 * Iterator iteratorParcelamentoQuantidadeReparcelamentoHelper =
		 * collectionParcelamentoQuantidadeReparcelamentoHelper.iterator();
		 * 
		 * while (iteratorParcelamentoQuantidadeReparcelamentoHelper.hasNext()) {
		 * ParcelamentoQuantidadeReparcelamentoHelper
		 * parcelamentoQuantidadeReparcelamentoHelper =
		 * (ParcelamentoQuantidadeReparcelamentoHelper)
		 * iteratorParcelamentoQuantidadeReparcelamentoHelper .next(); long
		 * valorTempo =
		 * parcelamentoQuantidadeReparcelamentoHelper.getUltimaAlteracao()
		 * .getTime(); vlMinPrest = (String)
		 * httpServletRequest.getParameter("vlMinPrest" + valorTempo);
		 *  // insere essas vari�veis no objeto
		 * ParcelamentoQuantidadeReparcelamentoHelper BigDecimal
		 * valorMinimoPrestacao = null; if (vlMinPrest != null &&
		 * !vlMinPrest.equals("")) { valorMinimoPrestacao =
		 * Util.formatarMoedaRealparaBigDecimal(vlMinPrest); }
		 * 
		 * parcelamentoQuantidadeReparcelamentoHelper.setValorMinimoPrestacao(valorMinimoPrestacao);
		 *  } }
		 */
	}

	// [FS0010]-Verificar Percentual M�ximo
	private void verificarPercentualMaximo(BigDecimal percentual) {

		BigDecimal percentualMaximo = new BigDecimal("100");

		if (percentual.compareTo(percentualMaximo) == 1) {
			throw new ActionServletException(
					"atencao.percentual_maior_percentual_maximo");
		}

	}
}
