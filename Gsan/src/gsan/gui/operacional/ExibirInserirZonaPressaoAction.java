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
package gsan.gui.operacional;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.operacional.DistritoOperacional;
import gsan.operacional.FiltroDistritoOperacional;
import gsan.operacional.FiltroZonaPressao;
import gsan.operacional.ZonaPressao;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;


/**
 * @author Vin�cius de Melo Medeiros
 * @created 20/05/2008
 */
public class ExibirInserirZonaPressaoAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
    private Collection colecaoPesquisa;
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		HttpSession sessao = httpServletRequest.getSession(false);

		ActionForward retorno = actionMapping.findForward("inserirZonaPressao");

		InserirZonaPressaoActionForm inserirZonaPressaoActionForm = (InserirZonaPressaoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
        String objetoConsulta = (String) httpServletRequest
        .getParameter("objetoConsulta");

        if (objetoConsulta != null
        		&& !objetoConsulta.trim().equalsIgnoreCase("")) {
        	
            
            switch (Integer.parseInt(objetoConsulta)) {
            
            // Distrito Operacional

            case 1:
                
                String distritoOperacionalID = inserirZonaPressaoActionForm
                        .getDistritoOperacionalID();

                FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();

                filtroDistritoOperacional
                        .adicionarParametro(new ParametroSimples(
                                FiltroDistritoOperacional.ID,
                                distritoOperacionalID));

                filtroDistritoOperacional
                        .adicionarParametro(new ParametroSimples(
                                FiltroDistritoOperacional.INDICADORUSO,
                                ConstantesSistema.INDICADOR_USO_ATIVO));

                //Retorna Distrito Operacional
                colecaoPesquisa = fachada.pesquisar(filtroDistritoOperacional,
                        DistritoOperacional.class.getName());

                if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                    //Distrito Operacional nao encontrado
                    //Limpa o campo distritoOperacionalID do formul�rio
                	inserirZonaPressaoActionForm.setDistritoOperacionalID("");
                	inserirZonaPressaoActionForm
                            .setDistritoOperacionalDescricao("Distrito operacional inexistente.");
                    httpServletRequest.setAttribute("corDistritoOperacional",
                            "exception");
                    
                    httpServletRequest.setAttribute("nomeCampo", "distritoOperacionalID");
                } else {
                    DistritoOperacional objetoDistritoOperacional = (DistritoOperacional) Util
                            .retonarObjetoDeColecao(colecaoPesquisa);
                    inserirZonaPressaoActionForm.setDistritoOperacionalID(String
                            .valueOf(objetoDistritoOperacional.getId()));
                    inserirZonaPressaoActionForm
                            .setDistritoOperacionalDescricao(objetoDistritoOperacional
                                    .getDescricao());
                    httpServletRequest.setAttribute("corDistritoOperacional",
                            "valor");
                }
                break;
            }
        }

		if ((httpServletRequest.getParameter("desfazer") != null && httpServletRequest
				.getParameter("desfazer").equalsIgnoreCase("S"))) {

			inserirZonaPressaoActionForm.setDescricao("");
			inserirZonaPressaoActionForm.setDescricaoAbreviada("");
			inserirZonaPressaoActionForm.setDistritoOperacionalID("");
			inserirZonaPressaoActionForm.setDistritoOperacionalDescricao("");

			if (inserirZonaPressaoActionForm.getDescricao() == null
					|| inserirZonaPressaoActionForm.getDescricao().equals("")) {

				Collection colecaoPesquisa = null;
				FiltroZonaPressao filtroZonaPressao = new FiltroZonaPressao();

				filtroZonaPressao.setCampoOrderBy(FiltroZonaPressao.DESCRICAO);
				
				colecaoPesquisa = fachada.pesquisar(filtroZonaPressao,
						ZonaPressao.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa.nenhum_registro_tabela", null,
							"Zona de Press�o");
				} else {
					sessao.setAttribute("colecaoZonaPressao", colecaoPesquisa);
				}

				// Cole��o de Grupo de Faturamento
				FiltroZonaPressao filtroZonaPressao2 = new FiltroZonaPressao();
				filtroZonaPressao2.setCampoOrderBy(FiltroZonaPressao.ID);

				Collection colecaoZonaPressao2 = fachada.pesquisar(filtroZonaPressao2,
						ZonaPressao.class.getName());
				sessao.setAttribute("colecaoZonaPressao2", colecaoZonaPressao2);

			}

		}
		return retorno;
	}
}
