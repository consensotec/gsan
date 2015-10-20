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
package gcom.gui.cadastro.geografico;

import gcom.cadastro.geografico.BairroArea;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.DistritoOperacional;
import gcom.operacional.FiltroDistritoOperacional;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Vivianne Sousa
 * @date 20/12/2006
 */
public class InserirAreaBairroPopupAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("inserirAreaBairroPopupAction");

		Fachada fachada = Fachada.getInstancia();

		AdicionarAreaBairroActionForm adicionarAreaBairroActionForm = (AdicionarAreaBairroActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		BairroArea bairroAreaInserir = new BairroArea();

		String areaBairroNome = adicionarAreaBairroActionForm
				.getAreaBairroNome();

		String distritoOperacionalID = adicionarAreaBairroActionForm
				.getDistritoOperacionalID();

		Collection<BairroArea> colecaoBairroArea = null;
		
		DistritoOperacional distritoOperacional = null;

		if (sessao.getAttribute("colecaoBairroArea") != null
				&& !sessao.getAttribute("colecaoBairroArea").equals("")) {

			colecaoBairroArea = (Collection) sessao
					.getAttribute("colecaoBairroArea");
		} else {
			colecaoBairroArea = new ArrayList<BairroArea>();
		}

		if (areaBairroNome == null || areaBairroNome.equals("")) {
			throw new ActionServletException(
					"atencao.campo_selecionado.obrigatorio", null,
					"Nome da �rea de Bairro");
		}

		// Alteracao Solicitada por Ana Breda Data:22/02/2007
		// Distrito Operacional n�o � mais obrigat�rio

		/*
		 * if(distritoOperacionalID == null ||
		 * distritoOperacionalID.equals("")){ throw new
		 * ActionServletException("atencao.campo_selecionado.obrigatorio",null,"Distrito
		 * Operacional"); }
		 */

		BairroArea bairroAreaAtualizar = null;
		if (sessao.getAttribute("atualizar") != null) {
			// remove area bairro e depois insere a area bairro alterada
			String ultimaAlteracao = (String) sessao.getAttribute("atualizar");

			Iterator iter = colecaoBairroArea.iterator();

			while (iter.hasNext()) {
				BairroArea bairroArea = (BairroArea) iter.next();

				if (bairroArea.getUltimaAlteracao().getTime() == Long
						.parseLong(ultimaAlteracao)) {
					bairroAreaAtualizar = bairroArea;
				}

			}
		}

		// Alteracao Solicitada por Ana Breda Data:22/02/2007
		// Distrito Operacional n�o � mais obrigat�rio

		if (distritoOperacionalID != null
				&& !distritoOperacionalID.equalsIgnoreCase("-1")) {
			// [FS0006] Verificar exist�ncia do distrito operacional
			FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();

			filtroDistritoOperacional.adicionarParametro(new ParametroSimples(
					FiltroDistritoOperacional.ID, distritoOperacionalID));

			filtroDistritoOperacional.adicionarParametro(new ParametroSimples(
					FiltroDistritoOperacional.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna Distrito Operacional
			Collection colecaoPesquisa = fachada.pesquisar(
					filtroDistritoOperacional, DistritoOperacional.class
							.getName());

			
			if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
				distritoOperacional = (DistritoOperacional) Util
						.retonarObjetoDeColecao(colecaoPesquisa);

			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Distrito Operacional");
			}
		}

		if (bairroAreaAtualizar != null) {

			// [FS0007] Verificar exist�ncia da �rea de bairro
			if (colecaoBairroArea != null && !colecaoBairroArea.isEmpty()) {

				Iterator iteratorBairroArea = colecaoBairroArea.iterator();

				while (iteratorBairroArea.hasNext()) {
					BairroArea bairroArea = (BairroArea) iteratorBairroArea
							.next();
					if (bairroArea.getNome().trim().equals(
							areaBairroNome.trim())
							&& !bairroArea.getNome().trim().equals(
									bairroAreaAtualizar.getNome().trim())) {
						throw new ActionServletException(
								"atencao.area_bairro_ja_informada", null,
								bairroArea.getNome().trim());
					}
				}
			}

			bairroAreaAtualizar.setNome(areaBairroNome.toUpperCase());

			bairroAreaAtualizar.setDistritoOperacional(distritoOperacional);

		} else {

			// [FS0007] Verificar exist�ncia da �rea de bairro
			if (colecaoBairroArea != null && !colecaoBairroArea.isEmpty()) {

				Iterator iteratorBairroArea = colecaoBairroArea.iterator();

				while (iteratorBairroArea.hasNext()) {
					BairroArea bairroArea = (BairroArea) iteratorBairroArea
							.next();
					if (bairroArea.getNome().trim().equals(
							areaBairroNome.trim())) {
						throw new ActionServletException(
								"atencao.area_bairro_ja_informada", null,
								bairroArea.getNome().trim());
					}
				}
			}

			bairroAreaInserir.setNome(areaBairroNome.toUpperCase());
			
			bairroAreaInserir.setDistritoOperacional(distritoOperacional);
			
			bairroAreaInserir.setUltimaAlteracao(new Date());

			colecaoBairroArea.add(bairroAreaInserir);
		}

		sessao.setAttribute("colecaoBairroArea", colecaoBairroArea);
		httpServletRequest.setAttribute("fechaPopup", "true");

		return retorno;
	}
}
