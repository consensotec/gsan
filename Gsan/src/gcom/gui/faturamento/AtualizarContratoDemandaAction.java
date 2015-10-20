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
package gcom.gui.faturamento;

import gcom.arrecadacao.ContratoDemanda;
import gcom.arrecadacao.ContratoMotivoCancelamento;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.FiltroContratoDemanda;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarContratoDemandaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarContratoDemandaActionForm atualizarContratoDemandaActionForm = (AtualizarContratoDemandaActionForm) actionForm;
		
		Usuario usuarioLogado = (Usuario) sessao
				.getAttribute(Usuario.USUARIO_LOGADO);

		ContratoDemanda contratoDemanda = (ContratoDemanda) sessao
				.getAttribute("contratoDemandaAtualizar");
		
		String dataInicioContrato = atualizarContratoDemandaActionForm.getDataInicioContrato();
		String dataFimContrato = atualizarContratoDemandaActionForm.getDataFimContrato();
		String dataEncerramento = atualizarContratoDemandaActionForm.getDataEncerramento();
		String idMotivoCancelamento = atualizarContratoDemandaActionForm.getIdMotivoCancelamento();
		
		Imovel imovel = null;

		String idImovel = atualizarContratoDemandaActionForm.getIdImovel();
		
		if (idImovel != null && !idImovel.trim().equals("")) {
			
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel));
			
			Collection colecaoImoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());
			
			if (colecaoImoveis != null && !colecaoImoveis.isEmpty()) {
				imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImoveis);
			} else {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Im�vel");
			}
			
		}
		
		contratoDemanda.setNumeroContrato(atualizarContratoDemandaActionForm.getNumeroContrato());
		contratoDemanda.setImovel(imovel);
		
		if (dataInicioContrato != null && !dataInicioContrato.trim().equals("")) {
			contratoDemanda.setDataContratoInicio(Util.converteStringParaDate(atualizarContratoDemandaActionForm.getDataInicioContrato()));
		} else {
			contratoDemanda.setDataContratoInicio(null);
		}
		
		if (dataFimContrato != null && !dataFimContrato.trim().equals("")) {
			contratoDemanda.setDataContratoFim(Util.converteStringParaDate(atualizarContratoDemandaActionForm.getDataFimContrato()));
		} else {
			contratoDemanda.setDataContratoFim(null);
		}
		
		if (dataEncerramento != null && !dataEncerramento.trim().equals("")) {
			contratoDemanda.setDataContratoEncerrado(Util.converteStringParaDate(atualizarContratoDemandaActionForm.getDataEncerramento()));
		} else {
			contratoDemanda.setDataContratoEncerrado(null);
		}
		
		if (idMotivoCancelamento != null && !idMotivoCancelamento.trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			ContratoMotivoCancelamento contratoMotivoCancelamento = new ContratoMotivoCancelamento();
			contratoMotivoCancelamento.setId(new Integer(idMotivoCancelamento));
			contratoDemanda.setContratoMotivoCancelamento(contratoMotivoCancelamento);
		} else {
			contratoDemanda.setContratoMotivoCancelamento(null);
		}
		
		if (contratoDemanda.getDataContratoEncerrado() == null && contratoDemanda.getContratoMotivoCancelamento() == null) {
			FiltroContratoDemanda filtroContratoDemanda = new FiltroContratoDemanda();
			filtroContratoDemanda.adicionarParametro(
					new ParametroSimples(FiltroContratoDemanda.IMOVEL, idImovel));
			filtroContratoDemanda.adicionarParametro(
					new ParametroNulo(FiltroContratoDemanda.DATACONTRATOENCERRAMENTO));
			filtroContratoDemanda.adicionarParametro(
					new ParametroSimplesDiferenteDe(FiltroContratoDemanda.ID, contratoDemanda.getId()));
			Collection colecaoContratoDemanda = fachada.pesquisar(
					filtroContratoDemanda, ContratoDemanda.class.getName());
			
			if (colecaoContratoDemanda != null & !colecaoContratoDemanda.isEmpty()) {
				throw new ActionServletException("atencao.contrato.demanda.encerrado", null, idImovel);
			}
		}
		
		// Inserir na base de dados ContratoDemanda
		fachada.atualizarContratoDemanda(contratoDemanda,usuarioLogado);
		
		// Montar a p�gina de sucesso
		montarPaginaSucesso(httpServletRequest, "Contrato de Demanda de c�digo "
				+ contratoDemanda.getId().toString()
				+ " atualizado com sucesso.",
				"Realizar outra Manuten��o de Contrato de Demanda",
				"exibirFiltrarContratoDemandaAction.do?menu=sim");

		return retorno;

	}
}
