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
package gcom.gui.cobranca.contratoparcelamento;

import gcom.cobranca.CobrancaForma;
import gcom.cobranca.FiltroCobrancaForma;
import gcom.cobranca.contratoparcelamento.ComparatorParcela;
import gcom.cobranca.contratoparcelamento.ContratoParcelamentoRD;
import gcom.cobranca.contratoparcelamento.QuantidadePrestacoes;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pr�-processamento da p�gina de Filtrar Resolucao de Diretoria para Contrato pro Cliente
 * 
 * @author Paulo Diniz
 * @created 16/03/2011
 */
public class AtualizarResolucaoDiretoriaContratoParcelamentoAction extends
		GcomAction {

	/**
	 * Este caso de uso permite a inclus�o de uma resolu��o de diretoria
	 * 
	 * [UC1134] Manter Resolu��o de Diretoria (RD) para Contratos de Parcelamento por cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 16/03/2011
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	//Obt�m a inst�ncia da fachada
	Fachada fachada = Fachada.getInstancia();

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		String method = httpServletRequest.getParameter("method");
		ActionForward retorno = actionMapping.findForward("exibirAtualizar");
		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		
		AtualizarResolucaoDiretoriaContratoParcelamentoActionForm form = (AtualizarResolucaoDiretoriaContratoParcelamentoActionForm) actionForm;
			
        //ContratoParcelamentoRD que vai ser cadastrado no sistema
		ContratoParcelamentoRD contratoAtualizar = (ContratoParcelamentoRD) sessao
				.getAttribute("contratoAtualizar");
		if (contratoAtualizar == null) {
			contratoAtualizar = new ContratoParcelamentoRD();
		}
		
		ContratoParcelamentoRD contratoAntes = (ContratoParcelamentoRD) sessao.getAttribute("contratoAtualizar");
		sessao.setAttribute("contratoAntes",contratoAntes);

		if(method != null && method.equals("concluirAtualizarRD")){
			
			List<QuantidadePrestacoes> parcelas = (List<QuantidadePrestacoes>) sessao.getAttribute("parcelas");
			
			String validacao = validarFormulario(form);

			if(!validacao.equals("")){
				carregarFormContratoAtualizar(contratoAtualizar, form);
				Collections.sort(parcelas, new ComparatorParcela());
				sessao.setAttribute("parcelas", parcelas);
				sessao.setAttribute("contratoAtualizar", contratoAtualizar);
				sessao.setAttribute("atencao", validacao);
				sessao.setAttribute("voltar","document.forms[0].action = '/gsan/atualizarResolucaoDiretoriaContratoParcelamentoAction.do'; document.forms[0].submit();");
				retorno = actionMapping.findForward("exibirError");
			}else{
				
				carregarFormContratoAtualizar(contratoAtualizar, form);
				Collections.sort(parcelas, new ComparatorParcela());
				
				this.getFachada().atualizarContratoParcelamentoResolucaoDiretoria(contratoAtualizar,parcelas,usuarioLogado);
				
				retorno = actionMapping.findForward("telaSucesso");
				
				montarPaginaSucesso(
						httpServletRequest,
						"Resolu��o de diretoria para contratos de parcelamento por cliente - "
						+ contratoAtualizar.getNumero() +" - atualizada com sucesso",
						"Realizar outra Manuten��o de Resolu��o de diretoria para contratos de parcelamento por cliente",
						"exibirFiltrarResolucaoDiretoriaContratoParcelamentoAction.do?menu=sim");
			}
			
			
		}else if(method != null && method.equals("inserirParcela")){
			
			retorno = actionMapping.findForward("exibirPaginaAtualizar");

			List<QuantidadePrestacoes> parcelas = (List<QuantidadePrestacoes>) sessao.getAttribute("parcelas");
			
			if(parcelas == null){
				parcelas = new ArrayList<QuantidadePrestacoes>();
			}
			
			String parcela = httpServletRequest.getParameter("parcela");
			String taxaJuros = httpServletRequest.getParameter("taxaJuros");
			
			boolean parcelaExistente = false;
			boolean campoInvalido = false;
			if(parcela != null && !parcela.equals("") 
					&& taxaJuros != null && !taxaJuros.equals("")
					&& Util.formatarMoedaRealparaBigDecimal(taxaJuros).compareTo(BigDecimal.ZERO) != 0){
				for (QuantidadePrestacoes prestacoes : parcelas) {
					if(prestacoes.getQtdFaturasParceladas() == Integer.parseInt(parcela)){
						parcelaExistente = true;
					}
				}
			}else{
				campoInvalido = true;
			}
			
			if(parcelaExistente == false && campoInvalido == false){
				QuantidadePrestacoes prestacao = new QuantidadePrestacoes();
				prestacao.setQtdFaturasParceladas(Integer.parseInt(parcela));
				prestacao.setUltimaAlteracao(new Date());
				taxaJuros = taxaJuros.replace(',','.');
				Double taxa = Double.parseDouble(taxaJuros);
				prestacao.setTaxaJuros(BigDecimal.valueOf(taxa));
				parcelas.add(prestacao);
				
				carregarFormContratoAtualizar(contratoAtualizar, form);
				Collections.sort(parcelas, new ComparatorParcela());
				sessao.setAttribute("parcelas", parcelas);
				sessao.setAttribute("contratoAtualizar", contratoAtualizar);
			}else{
				carregarFormContratoAtualizar(contratoAtualizar, form);
				
				sessao.setAttribute("parcelas", parcelas);
				sessao.setAttribute("contratoAtualizar", contratoAtualizar);
				if(parcelaExistente){
					//sessao.setAttribute("atencao", "Parcela informada j� consta na lista");
					throw new ActionServletException(
						"atencao.parcela_informada_ja_existente");
				} else {
					//sessao.setAttribute("atencao", "Campo Parcelas ou Taxa de Juros inv�lido");
					throw new ActionServletException(
						"atencao.erro_invalido", "Campo Parcelas ou Taxa de Juros");
				}
				//sessao.setAttribute("voltar","document.forms[0].action = '/gsan/atualizarResolucaoDiretoriaContratoParcelamentoAction.do'; document.forms[0].submit();");
				//retorno = actionMapping.findForward("exibirError");
			}
			
		}else if(method != null && method.equals("removerParcela")){
			
			retorno = actionMapping.findForward("exibirPaginaAtualizar");

			List<QuantidadePrestacoes> parcelas = (List<QuantidadePrestacoes>) sessao.getAttribute("parcelas");
			
			String parcela = httpServletRequest.getParameter("parcela");
			
			if(parcela != null && !parcela.equals("")){
				for (int i = 0; i < parcelas.size(); i++) {
					if(parcelas.get(i).getQtdFaturasParceladas() == Integer.parseInt(parcela)){
						parcelas.remove(i);
						i = parcelas.size();
					}
				}
			}else{
				throw new ActionServletException("erro.sistema");
			}
			
			carregarFormContratoAtualizar(contratoAtualizar, form);
			Collections.sort(parcelas, new ComparatorParcela());
			sessao.setAttribute("parcelas", parcelas);
			sessao.setAttribute("contratoAtualizar", contratoAtualizar);
		} else if (method != null && method.equals("limparColecaoParcelas")) {
			contratoAtualizar.setIndicadorParcelamentoJuros(ConstantesSistema.NAO);
			form.setIndicadorParcelamentoJuros(ConstantesSistema.NAO);
			sessao.removeAttribute("parcelas");
			sessao.setAttribute("contratoAtualizar", contratoAtualizar);
		}

		return retorno;
	}
	
	
	/**Inicio dos metodos Privados**/
	private void carregarFormContratoAtualizar(ContratoParcelamentoRD contratoAtualizar, AtualizarResolucaoDiretoriaContratoParcelamentoActionForm form){
		contratoAtualizar.setDataVigenciaInicio(null);
		contratoAtualizar.setDataVigenciaFinal(null);
		contratoAtualizar.setQtdFaturasParceladas(null);
		contratoAtualizar.setCobrancaForma(null);

		contratoAtualizar.setAssunto(form.getAssunto());
		if (form != null && !"".equals(form.getDataVigenciaInicial())){
			contratoAtualizar.setDataVigenciaInicio(Util.converteStringParaDate(form.getDataVigenciaInicial()));
		}
		if (form != null && !"".equals(form.getDataVigenciaFinal())){
			contratoAtualizar.setDataVigenciaFinal(Util.converteStringParaDate(form.getDataVigenciaFinal()));
		}
		
		contratoAtualizar.setIndicadorDebitoAcrescimo(form.getIndicadorDebitoAcrescimo());
		contratoAtualizar.setIndicadorInformarParcela(form.getIndicadorInformarParcela());
		contratoAtualizar.setIndicadorParcelamentoJuros(form.getIndicadorParcelamentoJuros());
		
		if (form != null && !"".equals(form.getQtdFaturasParceladas())){
			contratoAtualizar.setQtdFaturasParceladas(Integer.parseInt(form.getQtdFaturasParceladas()));
		}
			
		if (form != null && !"".equals(form.getFormaPgto())){
			Collection<CobrancaForma> formasPagto = this.getFachada().pesquisar(new FiltroCobrancaForma(), CobrancaForma.class.getName());
			for (CobrancaForma formaPgto : formasPagto) {
				if(formaPgto.getId() == Integer.parseInt(form.getFormaPgto())){
					contratoAtualizar.setCobrancaForma(formaPgto);
				}
			}
		}
	}
	
	private String validarFormulario(AtualizarResolucaoDiretoriaContratoParcelamentoActionForm form){
		String retorno = "";
		
		if (form.getAssunto() == null || "".equals(form.getAssunto())){
			retorno = "Assunto ";	
		}
		if (form.getDataVigenciaInicial() == null || "".equals(form.getDataVigenciaInicial())){
			retorno = "Data Vig�ncia Inicial ";	
		}
		if (form.getDataVigenciaFinal() == null || "".equals(form.getDataVigenciaFinal())){
			retorno = "Data Vig�ncia Final ";	
		}
		if (form.getIndicadorDebitoAcrescimo() == null || "".equals(form.getIndicadorDebitoAcrescimo())){
			retorno = "D�bito com Acr�scimo ";	
		}
		if (form.getIndicadorInformarParcela() == null || "".equals(form.getIndicadorInformarParcela())){
			retorno = "Parcelamento com Juros ";	
		}
		if (form.getQtdFaturasParceladas() == null || "".equals(form.getIndicadorParcelamentoJuros())){
			retorno = "Indicador Informar o Valor da Parcela ";	
		}
		if (form.getQtdFaturasParceladas() == null || "".equals(form.getQtdFaturasParceladas())){
			retorno = "Quantidade de Faturas Parceladas ";	
		}
		if (form.getFormaPgto() == null ||  "".equals(form.getFormaPgto())){
			retorno = "Forma de Pagamento ";	
		}
		
		if(!retorno.equals("")){
			retorno = "Informe: " + retorno;
		}
		
		Date dataInicial = Util.converteStringParaDate(form.getDataVigenciaInicial());
		Date dataFinal = Util.converteStringParaDate(form.getDataVigenciaFinal());
		if(dataInicial == null || dataFinal == null){
			retorno = "Data inv�lida";
		}else{
			if(dataFinal.before(dataInicial)){
				retorno = "Data In�cio da Vig�ncia deve ser anterior ou igual � Data Fim da Vig�ncia";
			}
		}
		
		
		return retorno;
	}
	
}
