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

package gsan.gui.seguranca.parametrosistema;

import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.seguranca.parametrosistema.FiltroParametroSistema;
import gsan.seguranca.parametrosistema.ParametroSistema;
import gsan.util.ConstantesSistema;
import gsan.util.filtro.ComparacaoTexto;
import gsan.util.filtro.ComparacaoTextoCompleto;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *[uc1667] [uc1666] Filtrar novos parametros do sistema
 * 
 * @author F�bio Aguiar
 * 
 * @date 26/01/2015
 */

public class FiltrarParametroSistemaAction extends GcomAction {
	
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
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno =  actionMapping.findForward("exibirManterParametroSistema");;

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		ParametroSistemaActionForm parametroSistemaActionForm = (ParametroSistemaActionForm) actionForm;
		
		Collection<ParametroSistema> colecaoParametroSistema; 
		
		FiltroParametroSistema filtroParametroSistema = new FiltroParametroSistema();
		
		
		String codigoConstante = parametroSistemaActionForm.getCodigoConstanteParametroSistema();
		String nome = parametroSistemaActionForm.getNomeParametroSistema();
		String tipoPesquisaNome = parametroSistemaActionForm.getTipoPesquisaNomeParametroSistema();
		String tipoParametro = parametroSistemaActionForm.getParametroTipo();
		String parametroRestrito = parametroSistemaActionForm.getParametroRestritoParametroSistema();
		String moduloId = parametroSistemaActionForm.getModuloParametroSistema();
		String indicadorUso = parametroSistemaActionForm.getIndicadorUsoParametroSistema();
				
			// Filtro pelo ID do codigo constante
			if (codigoConstante != null && !codigoConstante.trim().equals("")) {
			
				filtroParametroSistema.adicionarParametro(new ParametroSimples(FiltroParametroSistema.CODIGO_CONSTANTE, codigoConstante));
			
			}

			if(nome != null && !nome.trim().equals("")){
				if (tipoPesquisaNome != null && tipoPesquisaNome.equals(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString())) {
					filtroParametroSistema.adicionarParametro(new ComparacaoTexto(FiltroParametroSistema.NOME, nome));
				} else if (tipoPesquisaNome != null && tipoPesquisaNome.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())){	
					filtroParametroSistema.adicionarParametro(new ComparacaoTextoCompleto(FiltroParametroSistema.NOME, nome));
				}
			}
			
			filtroParametroSistema.adicionarCaminhoParaCarregamentoEntidade("parametroTipo");
			if(tipoParametro != null && !tipoParametro.trim().equals("") && Integer.parseInt(tipoParametro) > 0){
				filtroParametroSistema.adicionarParametro(new ParametroSimples(FiltroParametroSistema.TIPO_PARAMETRO, Integer.parseInt(tipoParametro)));
				
			}
			
			
			if(parametroRestrito != null && !parametroRestrito.trim().equals("")){
				if(parametroRestrito.equals(String.valueOf(ConstantesSistema.SIM)) || parametroRestrito.equals(String.valueOf(ConstantesSistema.NAO))){
					filtroParametroSistema.adicionarParametro(new ParametroSimples(FiltroParametroSistema.INDICADOR_PARAMETRO_RESTRITO, parametroRestrito));
				}
			}

			
			if(moduloId != null && !moduloId.trim().equals("") && Integer.parseInt(moduloId) > 0){
				filtroParametroSistema.adicionarCaminhoParaCarregamentoEntidade("modulo");
				filtroParametroSistema.adicionarParametro(new ParametroSimples(FiltroParametroSistema.MODULO_ID, moduloId));
			}
						
			
			if(indicadorUso != null && !indicadorUso.trim().equals("")){
				if(indicadorUso.equals(String.valueOf(ConstantesSistema.SIM)) || indicadorUso.equals(String.valueOf(ConstantesSistema.NAO))){
					filtroParametroSistema.adicionarParametro(new ParametroSimples(FiltroParametroSistema.INDICADOR_USO, indicadorUso));
				}
			}
		
		colecaoParametroSistema = fachada.pesquisar(filtroParametroSistema, ParametroSistema.class.getName());
		
		sessao.setAttribute("filtroParametroSistema", filtroParametroSistema);

		httpServletRequest.setAttribute("filtroParametroSistema",filtroParametroSistema);
		
		return retorno;
	}
	
}
