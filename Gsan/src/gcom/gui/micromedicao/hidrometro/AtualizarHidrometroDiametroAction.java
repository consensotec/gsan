/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.gui.micromedicao.hidrometro;

import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.HidrometroDiametro;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarHidrometroDiametroAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarHidrometroDiametroActionForm form = (AtualizarHidrometroDiametroActionForm) actionForm;

		HidrometroDiametro hidrometroDiametro = (HidrometroDiametro) sessao.getAttribute("atualizarHidrometroDiametro");
		
		hidrometroDiametro.setDescricao(form.getDescricao());
		hidrometroDiametro.setDescricaoAbreviada(form.getDescricaoAbreviada());
		
		if(!form.getNumeroOrdem().equals("")){
			hidrometroDiametro.setNumeroOrdem(new Short (form.getNumeroOrdem()));
		}else{
			hidrometroDiametro.setNumeroOrdem(null);
		}
		
		if(form.getIndicadorUso() != null){
			hidrometroDiametro.setIndicadorUso(new Short (form.getIndicadorUso()));
		}else{
			throw new ActionServletException("atencao.required", null,
						"Indicador de Uso");
		}
		
        String descricaoHidrometroDiametro = form
        .getDescricao();
        String descricaoAbreviadaHidrometroDiametro = form
        .getDescricaoAbreviada();    
        String numeroOrdem = form.getNumeroOrdem();
        String indicadordeUso = form
        .getIndicadorUso();
		
        hidrometroDiametro.setDescricao(descricaoHidrometroDiametro);
        hidrometroDiametro.setDescricaoAbreviada(descricaoAbreviadaHidrometroDiametro);
      	hidrometroDiametro.setNumeroOrdem( new Short(numeroOrdem));
        hidrometroDiametro.setUltimaAlteracao( new Date() );	
        hidrometroDiametro.setIndicadorUso( new Short(indicadordeUso));
  		
  		//Valor cobrado de depreciação
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
  		
  		
		fachada.atualizar(hidrometroDiametro);

		montarPaginaSucesso(httpServletRequest, "Diâmetro do Hidrômetro "
				+ form.getId().toString() + " atualizado com sucesso.",
				"Realizar outra Manutenção de Diâmetro do Hidrômetro ",
				"exibirFiltrarHidrometroDiametroAction.do?menu=sim");        
        
		return retorno;
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
