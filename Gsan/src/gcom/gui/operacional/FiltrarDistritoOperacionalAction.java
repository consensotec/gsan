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
package gcom.gui.operacional;

import gcom.arrecadacao.FiltroArrecadador;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroDistritoOperacional;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0523]	FILTRAR DISTRITO OPERACIONAL
 * 
 * @author Eduardo Bianchi
 * @date 01/02/2007
 */

public class FiltrarDistritoOperacionalAction extends GcomAction {


	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("exibirManterDistritoOperacional");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarDistritoOperacionalActionForm form = (FiltrarDistritoOperacionalActionForm) actionForm;
		
		//Recupera todos os campos da p�gina para ser colocada no filtro posteriormente
		String descricao = form.getDescricao();
		String setorAbastecimento = form.getSetorAbastecimento();
		String sistemaAbastecimento = form.getSistemaAbastecimento();
		String tipoPesquisa = form.getTipoPesquisa();
		String indicadorUso = form.getIndicadorUso();
		
		//Indicador Atualizar
		String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");
		
		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {			
			sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
		} else {			
			sessao.removeAttribute("indicadorAtualizar");
		}
		
		FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional(FiltroDistritoOperacional.DESCRICAO);
		
		//Objetos secundarios que ser�o carregados
		filtroDistritoOperacional.adicionarCaminhoParaCarregamentoEntidade("zonaAbastecimento");
		filtroDistritoOperacional.adicionarCaminhoParaCarregamentoEntidade("setorAbastecimento");
		
		boolean peloMenosUmParametroInformado = false;
		
		//Adiciona a Descri��o do Distrito Operacional ao filtro
		if (descricao != null && !descricao.equalsIgnoreCase("")) {			
			peloMenosUmParametroInformado = true;			
			if (tipoPesquisa != null && tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {
				filtroDistritoOperacional.adicionarParametro(new ComparacaoTextoCompleto(FiltroDistritoOperacional.DESCRICAO, 
						descricao));
			} else {
				filtroDistritoOperacional.adicionarParametro(new ComparacaoTexto(FiltroDistritoOperacional.DESCRICAO, descricao));
			}
		}		
		
		//Adiciona o Setor Abastecimento
		if (setorAbastecimento != null && !setorAbastecimento.equals("-1")) {
			peloMenosUmParametroInformado = true;			
			filtroDistritoOperacional.adicionarParametro(new ParametroSimples(FiltroDistritoOperacional.SETORABASTECIMENTO,
					setorAbastecimento));
		}
		
		//Adiciona o Sistema Abastecimento
		if (sistemaAbastecimento != null && !sistemaAbastecimento.equals("-1")) {
			peloMenosUmParametroInformado = true;			
			filtroDistritoOperacional.adicionarParametro(new ParametroSimples(FiltroDistritoOperacional.SISTEMAABASTECIMENTO,
					sistemaAbastecimento));
		}		
		
		if ( indicadorUso != null && !indicadorUso.trim().equals("") ){
			peloMenosUmParametroInformado = true;
			filtroDistritoOperacional.adicionarParametro(new ParametroSimples(FiltroArrecadador.INDICADOR_USO, indicadorUso));			
		}		
		
		//Verifica se pelo menos um parametro foi informado
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}
		
		// Manda o filtro pela sessao para o
		// ExibirManterDistritoOperacionalAction
		sessao.setAttribute("filtroDistritoOperacional", filtroDistritoOperacional);
		return retorno;
		}
}

				
				
			
				
				
				
				