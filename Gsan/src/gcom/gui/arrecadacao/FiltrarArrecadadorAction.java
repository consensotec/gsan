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
package gcom.gui.arrecadacao;

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.FiltroArrecadador;
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
 * [UC0536]FILTRAR ARRECADADOR
 * 
 * @author Marcio Roberto
 * @date 01/02/2007
 */

public class FiltrarArrecadadorAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirManterArrecadador");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarArrecadadorActionForm filtrarArrecadadorActionForm = (FiltrarArrecadadorActionForm) actionForm;

		// Recupera todos os campos da p�gina para ser colocada no filtro posteriormente

		String idAgente = filtrarArrecadadorActionForm.getIdAgente();
		String idCliente = filtrarArrecadadorActionForm.getIdCliente();
		String idImovel = filtrarArrecadadorActionForm.getIdImovel();
		String inscricaoEstadual = filtrarArrecadadorActionForm.getInscricaoEstadual();
		String indicadorUso = filtrarArrecadadorActionForm.getIndicadorUso();

		// Indicador Atualizar
		String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");
		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {
			sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
		} else {
			sessao.removeAttribute("indicadorAtualizar");
		}

		boolean peloMenosUmParametroInformado = false;
		FiltroArrecadador filtroArrecadador = new FiltroArrecadador();

		// C�digo do Arrecadador
		if (idAgente != null && !idAgente.trim().equals("")) {
			// [FS0003] - Verificando a exist�ncia do Agente
			boolean achou = fachada.verificarExistenciaAgente(new Integer(idAgente));
			if (achou) {
				peloMenosUmParametroInformado = true;
				filtroArrecadador.adicionarParametro(new ParametroSimples(FiltroArrecadador.CODIGO_AGENTE, idAgente));
			}
		}

		// Cliente
		if (idCliente != null && !idCliente.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroArrecadador.adicionarParametro(new ParametroSimples(FiltroArrecadador.CLIENTE_ID, idCliente));
		}

		// Imovel
		if (idImovel != null && !idImovel.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroArrecadador.adicionarParametro(new ParametroSimples(FiltroArrecadador.IMOVEL_ID, idImovel));
		}

		// Inscricao Estadual
		if (inscricaoEstadual != null && !inscricaoEstadual.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroArrecadador.adicionarParametro(new ParametroSimples(FiltroArrecadador.INSCRICAO_ESTATAL, inscricaoEstadual));
		}
		
		//Indicador de Uso
		if (indicadorUso != null && !indicadorUso.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroArrecadador.adicionarParametro(new ParametroSimples(FiltroArrecadador.INDICADOR_USO, indicadorUso));
		}

		// Erro caso o usu�rio mandou filtrar sem nenhum par�metro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}

		filtroArrecadador.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtroArrecadador.adicionarCaminhoParaCarregamentoEntidade("imovel");

		Collection<Arrecadador> colecaoArrecadador = fachada.pesquisar(
				filtroArrecadador, Arrecadador.class.getName());

		if (colecaoArrecadador == null || colecaoArrecadador.isEmpty()) {
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Arrecadador");
		} else {
			httpServletRequest.setAttribute("colecaoArrecadador",colecaoArrecadador);
			Arrecadador arrecadador = new Arrecadador();
			arrecadador = (Arrecadador) Util.retonarObjetoDeColecao(colecaoArrecadador);
			String idRegistroAtualizacao = arrecadador.getId().toString();
			sessao.setAttribute("idRegistroAtualizacao", idRegistroAtualizacao);
		}

		sessao.setAttribute("filtroArrecadador", filtroArrecadador);

		httpServletRequest.setAttribute("filtroArrecadador", filtroArrecadador);

		return retorno;

	}
}
