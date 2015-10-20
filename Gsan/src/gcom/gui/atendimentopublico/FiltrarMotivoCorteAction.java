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
package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.ligacaoagua.FiltroMotivoCorte;
import gcom.atendimentopublico.ligacaoagua.MotivoCorte;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0755]FILTRAR MOTIVO DE CORTE
 * 
 * @author Vin�cius Medeiros
 * @date 02/04/2008
 */

public class FiltrarMotivoCorteAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirManterMotivoCorte");

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();
		
		FiltrarMotivoCorteActionForm filtrarMotivoCorteActionForm = (FiltrarMotivoCorteActionForm) actionForm;

		FiltroMotivoCorte filtroMotivoCorte = new FiltroMotivoCorte();

		boolean peloMenosUmParametroInformado = false;

		String idMotivoCorte = filtrarMotivoCorteActionForm.getIdMotivoCorte();
		String descricao = filtrarMotivoCorteActionForm.getDescricao();
		String indicadorUso = filtrarMotivoCorteActionForm.getIndicadorUso();
		String tipoPesquisa = filtrarMotivoCorteActionForm.getTipoPesquisa();

		// Filtro pelo ID
		if (idMotivoCorte != null && !idMotivoCorte.trim().equals("")) {
			boolean achou = fachada.verificarExistenciaAgente(new Integer(idMotivoCorte));
			
			if (achou) {
				peloMenosUmParametroInformado = true;
				filtroMotivoCorte.adicionarParametro(new ParametroSimples(
						FiltroMotivoCorte.ID, idMotivoCorte));
			}
			
		}
		
		// Filtro pela Descri��o
		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			if (tipoPesquisa != null&& tipoPesquisa.equals(
					ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {

				filtroMotivoCorte
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroMotivoCorte.DESCRICAO, descricao));
			} else {

				filtroMotivoCorte.adicionarParametro(new ComparacaoTexto(
						FiltroMotivoCorte.DESCRICAO, descricao));
			}
		}
			
		// Filtro pelo Indicador de Uso
		if (indicadorUso != null && !indicadorUso.trim().equals("")) {
			
			peloMenosUmParametroInformado = true;
			
			filtroMotivoCorte.adicionarParametro(new ParametroSimples(
					FiltroMotivoCorte.INDICADOR_USO, indicadorUso));
		}
		
		Collection<MotivoCorte> colecaoMotivoCorte = fachada.pesquisar(
				filtroMotivoCorte, MotivoCorte.class.getName());
		
		//Verificar a existencia de um Motivo de Corte
		if (colecaoMotivoCorte.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.motivoCorte_inexistente");
		}

		// Filtragem sem parametros
		if (!peloMenosUmParametroInformado) {
	            throw new ActionServletException(
	                    "atencao.filtro.nenhum_parametro_informado");

	    } else {
	            httpServletRequest.setAttribute("filtroMotivoCorte",
	                    filtroMotivoCorte);
	            sessao.setAttribute("filtroMotivoCorte",
	                    filtroMotivoCorte);	            
	            sessao.setAttribute("idMotivoCorte",
	            		idMotivoCorte);
	            
	    }
		
		// Pesquisa sem registros 
		if (colecaoMotivoCorte == null || colecaoMotivoCorte.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", null,"Motivo Corte");
		} else {
			httpServletRequest.setAttribute("colecaoMotivoCorte",colecaoMotivoCorte);
			MotivoCorte motivoCorte = new MotivoCorte();
			motivoCorte = (MotivoCorte) Util.retonarObjetoDeColecao(colecaoMotivoCorte);
			String idRegistroAtualizacao = motivoCorte.getId().toString();
			sessao.setAttribute("idRegistroAtualizacao", idRegistroAtualizacao);
		}

		sessao.setAttribute("filtroMotivoCorte", filtroMotivoCorte);
		
		httpServletRequest.setAttribute("filtroMotivoCorte", filtroMotivoCorte);

		return retorno;

	}
}
