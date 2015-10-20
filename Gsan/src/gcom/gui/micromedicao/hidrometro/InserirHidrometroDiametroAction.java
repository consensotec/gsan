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
package gcom.gui.micromedicao.hidrometro;

import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroDiametro;
import gcom.micromedicao.hidrometro.HidrometroDiametro;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descri��o da classe
 * 
 * @author Vin�cius Medeiros
 * @date 16/05/2008
 */
public class InserirHidrometroDiametroAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclus�o de uma Diametro do Hidrometro
	 * 
	 * [UC0791] Inserir Diametro do Hidrometro
	 * 
	 * 
	 * @author Vin�cius Medeiros
	 * @date 16/05/2008
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

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirHidrometroDiametroActionForm form = (InserirHidrometroDiametroActionForm) actionForm;

		// Mudar quando houver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		String descricao = form.getDescricao();
		String descricaoAbreviada = form
				.getDescricaoAbreviada();

		HidrometroDiametro hidrometroDiametro = new HidrometroDiametro();
		Collection colecaoPesquisa = null;

		// Descri��o
		if (!"".equals(form.getDescricao())) {
			hidrometroDiametro.setDescricao(form
				.getDescricao());
		} else {
			throw new ActionServletException("atencao.required", null,
					"descri��o");
		}

		// Descri��o Abreviada
		if (!"".equals(form.getDescricaoAbreviada())) {
			hidrometroDiametro.setDescricaoAbreviada(form
				.getDescricaoAbreviada());
		} else {
			throw new ActionServletException("atencao.required", null,
					"descri��o Abreviada");
		}

		// N�mero de Ordem
		if (!"".equals(form.getNumeroOrdem())) {
			hidrometroDiametro.setNumeroOrdem(new Short (form
				.getNumeroOrdem()));
		} else {
			throw new ActionServletException("atencao.required", null,
					"N�mero de ordem");
		}

		//Valor cobrado de deprecia��o
  		if(form.getValorCobradoDepreciacao() != null && Util.verificarNaoVazio(form.getValorCobradoDepreciacao())){  			
  			hidrometroDiametro.setValorCobradoPorDepreciacao(Util.formatarMoedaRealparaBigDecimal(form.getValorCobradoDepreciacao()));
  			if(validarValorCobradoDepreciacao(hidrometroDiametro.getValorCobradoPorDepreciacao()) == false){
  				throw new ActionServletException("atencao.valor_depreciacao_invalido", "");
  			}
  		}else{
  			hidrometroDiametro.setValorCobradoPorDepreciacao(null);
  		}
  		
  		//Debito Tipo
  		if(form.getIdDebito() != null && Util.verificarNaoVazio(form.getIdDebito())){
  			if(validarDebitoTipo(form.getIdDebito())){
				DebitoTipo debitoTipo = new DebitoTipo();
				debitoTipo.setId(Integer.valueOf(form.getIdDebito()));
				hidrometroDiametro.setDebitoTipo(debitoTipo);
			}else{
				throw new ActionServletException("atencao.debito_tipo_invalido", "");
			}
  		}else{
  			hidrometroDiametro.setDebitoTipo(null);
  		}
  		
  		if(hidrometroDiametro.getValorCobradoPorDepreciacao() != null){  			
  			if(hidrometroDiametro.getDebitoTipo() == null){
  				throw new ActionServletException("atencao.debito_tipo_obrigatorio", "");  			
  			}  			
  		}
  		
  		if(hidrometroDiametro.getDebitoTipo() != null){
  			if(hidrometroDiametro.getValorCobradoPorDepreciacao() == null){
  				throw new ActionServletException("atencao.required", null,
  						"Valor cobrado por depreciacao");
  			}
  		}		
  		
		hidrometroDiametro.setUltimaAlteracao(new Date());

		Short iu = 1;
		hidrometroDiametro.setIndicadorUso(iu);

		FiltroHidrometroDiametro filtroHidrometroDiametro = new FiltroHidrometroDiametro();

		filtroHidrometroDiametro.adicionarParametro(new ParametroSimples(
			FiltroHidrometroDiametro.DESCRICAO, hidrometroDiametro.getDescricao()));

		colecaoPesquisa = (Collection) fachada.pesquisar(
			filtroHidrometroDiametro, HidrometroDiametro.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			// Caso j� haja um Diametro do Hidrometro com os dados passados 
			throw new ActionServletException(
				"atencao.diametrohidrometro_ja_cadastrada", null,
				hidrometroDiametro.getDescricao());
		} else {
			hidrometroDiametro.setDescricao(descricao);
			hidrometroDiametro.setDescricaoAbreviada(descricaoAbreviada);

			Integer idHidrometroDiametro = (Integer) fachada
					.inserir(hidrometroDiametro);

			montarPaginaSucesso(httpServletRequest,
				"Di�metro do Hidr�metro " + descricao
				+ " inserido com sucesso.",
				"Inserir outro Di�metro do Hidr�metro",
				"exibirInserirHidrometroDiametroAction.do?menu=sim",
				"exibirAtualizarHidrometroDiametroAction.do?idRegistroAtualizacao="
						+ idHidrometroDiametro,
					"Atualizar Di�metro do Hidr�metro Inserida");

			sessao.removeAttribute("InserirHidrometroDiametroActionForm");

			return retorno;
		}
	}

	public boolean validarValorCobradoDepreciacao(BigDecimal valor){

		boolean retorno = true;

		if(valor.equals(BigDecimal.ZERO)){
			retorno = false;
		}else if (valor.equals(new BigDecimal("0.0"))){
			retorno = false;
		}else if (valor.equals(new BigDecimal("0.00"))){
			retorno = false;
		}else if (valor.equals(new BigDecimal("0.000"))){
			retorno = false;
		}

		return retorno;
	}
	
	public boolean validarDebitoTipo(String idDebitoTipo){
		boolean retorno = true;		
		
		FiltroDebitoTipo filtro = new FiltroDebitoTipo();
		filtro.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.ID, idDebitoTipo));
		DebitoTipo debitoTipo = (DebitoTipo) Util.retonarObjetoDeColecao(Fachada.getInstancia().pesquisar(filtro, DebitoTipo.class.getName()));
		
		if(debitoTipo == null){
			retorno = false;
		}
		
		return retorno;
	}
	
	
}

