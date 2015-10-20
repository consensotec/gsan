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
package gcom.gui.cadastro.endereco;

import gcom.atendimentopublico.ordemservico.OSProgramacaoCalibragem;
import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.FiltroLogradouroBairro;
import gcom.cadastro.endereco.FiltroOSProgramaCalibragem;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action respons�vel pela atualiza��o de um logradouro na base
 * 
 * @author S�vio Luiz
 */
public class AtualizarLogradouroGrauImportanciaAction extends GcomAction {
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

		ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;
		
		//		 Obt�m os ids de remo��o
		String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();

		Integer qtIds = ids.length;
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		//DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);
        Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
//      mensagem de erro quando o usu�rio tenta excluir sem ter selecionado
		// nenhum
		// registro
		if (ids == null || ids.length == 0) {
			throw new ActionServletException(
					"atencao.registros.nao_selecionados_atualizacao");
		}
		

		// ===========================================================================
		
		String logBairro = httpServletRequest.getParameter("logbairro");

		Logradouro logradouro = null;
		LogradouroBairro logradouroBairro = null;
		Map<String, String[]> requestMap = httpServletRequest.getParameterMap();
		int count = 0;
		String idsAtualizados = "";
		int[] arr ;
		arr = new int[qtIds];
		for(int i =0; i < qtIds; i++){
			
			String idLogradouro = ids[i];
			
			if (idLogradouro != null && logBairro != null && logBairro.equals("sim")) {
				
				//LogradouroBairro
				FiltroLogradouroBairro filtroLogradouroBairro = new FiltroLogradouroBairro();

				filtroLogradouroBairro.adicionarParametro(new ParametroSimples(
						FiltroLogradouroBairro.ID, new Integer(idLogradouro)));

				filtroLogradouroBairro.adicionarCaminhoParaCarregamentoEntidade("programaCalibragem");
				filtroLogradouroBairro.adicionarCaminhoParaCarregamentoEntidade("bairro");
				filtroLogradouroBairro.adicionarCaminhoParaCarregamentoEntidade("logradouro");
				filtroLogradouroBairro.adicionarParametro(new ParametroSimples(
						FiltroLogradouroBairro.INDICADORUSO_BAIRRO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection logradouroEncontrado = fachada.pesquisar(filtroLogradouroBairro,
						LogradouroBairro.class.getName());
				
				if (logradouroEncontrado != null && !logradouroEncontrado.isEmpty()) {
					logradouroBairro = (LogradouroBairro) ((List) logradouroEncontrado).get(0);

				} else {
					throw new ActionServletException(
							"atencao.pesquisa.nenhumresultado", null, "logradouro");
				}
				
				if (requestMap.get("grauImportancia_" + idLogradouro) != null && !((requestMap.get("grauImportancia_" + idLogradouro))[0]).equals("")){

					FiltroOSProgramaCalibragem filtroOSProgramaCalibragem = new FiltroOSProgramaCalibragem();
					filtroOSProgramaCalibragem.adicionarParametro(new ParametroSimples(FiltroOSProgramaCalibragem.ID,new Integer((requestMap.get("grauImportancia_" + idLogradouro))[0])));
					Collection collOSProgramaCalibragem = fachada.pesquisar(filtroOSProgramaCalibragem, OSProgramacaoCalibragem.class.getName());
					OSProgramacaoCalibragem programaCalibragem = (OSProgramacaoCalibragem)Util.retonarObjetoDeColecao(collOSProgramaCalibragem);
					
					logradouroBairro.setProgramaCalibragem(programaCalibragem);
					
				}else{
					throw new ActionServletException(
							"atencao.campo_selecionado.obrigatorio", null, "Grau de Import�ncia");
				}
				
				arr[count]  = logradouroBairro.getId();			
				count++;
				
				
				fachada.atualizarGrauImportancia(logradouroBairro, logradouroBairro.getProgramaCalibragem().getId(),usuarioLogado);
				
			} else if (idLogradouro != null) {

				FiltroLogradouro filtroLogradouro = new FiltroLogradouro();

				filtroLogradouro.adicionarParametro(new ParametroSimples(
						FiltroLogradouro.ID, new Integer(idLogradouro)));

				filtroLogradouro.adicionarCaminhoParaCarregamentoEntidade("programaCalibragem");
				filtroLogradouro.adicionarParametro(new ParametroSimples(
						FiltroLogradouro.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection logradouroEncontrado = fachada.pesquisar(filtroLogradouro,
						Logradouro.class.getName());

				if (logradouroEncontrado != null && !logradouroEncontrado.isEmpty()) {
					logradouro = (Logradouro) ((List) logradouroEncontrado).get(0);

				} else {
					throw new ActionServletException(
							"atencao.pesquisa.nenhumresultado", null, "logradouro");
				}
				
				if (requestMap.get("grauImportancia_" + idLogradouro) != null && !((requestMap.get("grauImportancia_" + idLogradouro))[0]).equals("")){

					FiltroOSProgramaCalibragem filtroOSProgramaCalibragem = new FiltroOSProgramaCalibragem();
					filtroOSProgramaCalibragem.adicionarParametro(new ParametroSimples(FiltroOSProgramaCalibragem.ID,new Integer((requestMap.get("grauImportancia_" + idLogradouro))[0])));
					Collection collOSProgramaCalibragem = fachada.pesquisar(filtroOSProgramaCalibragem, OSProgramacaoCalibragem.class.getName());
					OSProgramacaoCalibragem programaCalibragem = (OSProgramacaoCalibragem)Util.retonarObjetoDeColecao(collOSProgramaCalibragem);
					
					logradouro.setProgramaCalibragem(programaCalibragem);
					
				}else{
					throw new ActionServletException(
							"atencao.campo_selecionado.obrigatorio", null, "Grau de Import�ncia");
				}
				
				arr[count]  = logradouro.getId();			
				count++;
				
				
				//fachada.atualizarGrauImportancia(logradouro, logradouro.getProgramaCalibragem().getId(),usuarioLogado);
			}
		
			
			
			
				
		}
		for(int j = 0; j < arr.length; j++){
			if(j==arr.length-1){
				idsAtualizados += arr[j];
			}else{
				idsAtualizados += arr[j]+",";
			}
		}
		
		//sessao.removeAttribute("indicadorImportanciaLogradouro");
		
		
		
		
		if (logBairro != null && logBairro.equals("sim")){
			montarPaginaSucesso(httpServletRequest, "Logradouro de c�digo "
					+ idsAtualizados + " atualizado com sucesso.",
					"Realizar outra Manuten��o de Logradouro",
					"exibirManterLogradouroBairroAction.do?menu=sim&implog=sim");
		} else {
			montarPaginaSucesso(httpServletRequest, "Logradouro de c�digo "
				+ idsAtualizados + " atualizado com sucesso.",
				"Realizar outra Manuten��o de Logradouro",
				"exibirManterLogradouroAction.do?menu=sim&implog=sim");
		}
		return retorno;
	}
}
