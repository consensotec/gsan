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
package gcom.gui.cadastro;

import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.FiltroCep;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0884]Filtrar Cep
 * 
 * @author Vin�cius Medeiros
 * @date 12/02/2009
 */

public class FiltrarCepAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o caminho do retorno
		ActionForward retorno = actionMapping.findForward("exibirAtualizarCep");

		// Mudar isso quando for implementado o esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		FiltrarCepActionForm filtrarCepActionForm = (FiltrarCepActionForm) actionForm;

		FiltroCep filtroCep = new FiltroCep();

		boolean informouCodigo = false;
		
		String codigo = filtrarCepActionForm.getCodigo();
		String codigoCep = null;
		
		if(codigo.length() == 10){
			codigoCep = Util.retirarFormatacaoCEP(filtrarCepActionForm.getCodigo());
		} else if (codigo.length() == 8){
			codigoCep = codigo;
		}
		
		if (codigoCep != null && !codigoCep.trim().equals("")) {
			
			//boolean achou = fachada.verificarExistenciaAgente(new Integer(codigoCep));
			
			//if (achou) {
				informouCodigo = true;
				filtroCep.adicionarParametro(
						new ParametroSimples(FiltroCep.CODIGO, 
								codigoCep));
			
			//}
		}
		
		Collection<Cep> colecaoCep = fachada.pesquisar(
				filtroCep, Cep.class.getName());

		// Verificar a existencia de um CEP
		if (colecaoCep.isEmpty()) {
			
			throw new ActionServletException(
					"atencao.pesquisa_inexistente", null,"CEP");
		}

		// Filtragem sem parametros
		if (!informouCodigo == true) {
		
			throw new ActionServletException(
					"atencao.cep_deve_ser_informado");
		}
		
		// Pesquisa sem registros
		if (colecaoCep == null || colecaoCep.isEmpty()) {
			
			throw new ActionServletException("atencao.cep.inexistente");
		
		} else {
		
			httpServletRequest.setAttribute("colecaoCep",colecaoCep);
			Cep cep = new Cep();
			cep = (Cep) Util.retonarObjetoDeColecao(colecaoCep);
			String idRegistroAtualizacao = cep.getCepId().toString();
			sessao.setAttribute("idRegistroAtualizacao", idRegistroAtualizacao);
			httpServletRequest.setAttribute("idRegistroAtualizacao", idRegistroAtualizacao);
		}

		sessao.setAttribute("filtroCep", filtroCep);

		httpServletRequest.setAttribute("filtroCep",filtroCep);

		return retorno;

	}
}
