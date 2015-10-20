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


import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.DivisaoEsgoto;
import gcom.operacional.FiltroDivisaoEsgoto;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Este caso de uso permite a inclus�o de uma Divis�o de Esgoto
 * 
 * [UC0815] Inserir Divisao de Esgoto
 * 
 * 
 * @author Arthur Carvalho
 * @date 09/06/2008
 */
public class InserirDivisaoEsgotoAction extends GcomAction {

		public ActionForward execute(ActionMapping actionMapping,
				ActionForm actionForm, HttpServletRequest httpServletRequest,
				HttpServletResponse httpServletResponse) {

			ActionForward retorno = actionMapping.findForward("telaSucesso");

			InserirDivisaoEsgotoActionForm inserirDivisaoEsgotoActionForm = (InserirDivisaoEsgotoActionForm) actionForm;

			HttpSession sessao = httpServletRequest.getSession(false);

			Fachada fachada = Fachada.getInstancia();

			String descricao = inserirDivisaoEsgotoActionForm.getDescricao();
			String idUnidadeOrganizacional= inserirDivisaoEsgotoActionForm.getUnidadeOrganizacionalId();
			
			
			
			DivisaoEsgoto divisaoEsgoto= new DivisaoEsgoto();
			Collection colecaoPesquisa = null;
			

			// Descricao
			if (!"".equals(inserirDivisaoEsgotoActionForm.getDescricao())) {
				divisaoEsgoto.setDescricao(inserirDivisaoEsgotoActionForm
						.getDescricao());
			} else {
				throw new ActionServletException("atencao.required", null,
						"descri��o");
			}
			
			/*
			 * Unidade Organizacional � obrigat�rio 
			 */
			if (idUnidadeOrganizacional == null || idUnidadeOrganizacional.equalsIgnoreCase("")) {
				throw new ActionServletException("atencao.required", null,  "Unidade Organizacional");
			} else {
				FiltroUnidadeOrganizacional filtroUnidadeOrganizacional= new FiltroUnidadeOrganizacional();
	            
	            filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
	                    FiltroUnidadeOrganizacional.ID, idUnidadeOrganizacional));

				filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
						FiltroUnidadeOrganizacional.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna Unidade Organizacional
				colecaoPesquisa = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class
						.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa.distrito_operacional_inexistente");
				} else {
					UnidadeOrganizacional objetoUnidadeOrganizacional = (UnidadeOrganizacional) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					divisaoEsgoto.setUnidadeOrganizacional(objetoUnidadeOrganizacional);
				}
			}
			
			Short iu = 1;
			divisaoEsgoto.setIndicadorUso(iu);
			
			// Ultima altera��o
			divisaoEsgoto.setUltimaAlteracao(new Date());

			
			FiltroDivisaoEsgoto filtroDivisaoEsgoto = new FiltroDivisaoEsgoto();

			filtroDivisaoEsgoto.adicionarParametro(new ParametroSimples(
					FiltroDivisaoEsgoto.DESCRICAO, divisaoEsgoto.getDescricao()));

			
			colecaoPesquisa = (Collection) fachada.pesquisar(
					filtroDivisaoEsgoto, DivisaoEsgoto.class.getName());

			if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			
				throw new ActionServletException(
						"atencao.divisao_esgoto_ja_cadastrada", null,
						descricao);
				
			
			} else {
				divisaoEsgoto.setDescricao(descricao);
				

				Integer idDivisaoEsgoto = (Integer) fachada
						.inserir(divisaoEsgoto);

				montarPaginaSucesso(httpServletRequest,
						"Divis�o de Esgoto " + descricao
								+ " inserido com sucesso.",
						"Inserir outra Divis�o de Esgoto",
						"exibirInserirDivisaoEsgotoAction.do?menu=sim",
						"exibirAtualizarDivisaoEsgotoAction.do?idRegistroAtualizacao="
								+ idDivisaoEsgoto,
						"Atualizar Divis�o de Esgoto Inserida");

				sessao.removeAttribute("InserirDivisaoEsgotoActionForm");

				return retorno;
			}

			}
		}

