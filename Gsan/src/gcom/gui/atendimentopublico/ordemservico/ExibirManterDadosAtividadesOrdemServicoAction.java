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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.Atividade;
import gcom.atendimentopublico.ordemservico.FiltroAtividade;
import gcom.atendimentopublico.ordemservico.bean.ManterDadosAtividadesOrdemServicoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usu�rio a tela que receber� os
 * par�metros para realiza��o da atualiza��o do dados das atividades de uma OS
 * 
 * @author Raphael Rossiter
 * @date 15/09/2006
 */
public class ExibirManterDadosAtividadesOrdemServicoAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("manterDadosAtividadesOrdemServico");

		ManterDadosAtividadesOrdemServicoActionForm form = (ManterDadosAtividadesOrdemServicoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();
		
		/*
		 * O caso de uso recebe o n�mero sa OS, data de roteiro e a data de encerramento (dever� vir informado
		 * um ou outro e ambos)
		 */
		String numeroOSParametro = httpServletRequest.getParameter("numeroOS");
		
		if (numeroOSParametro != null && !numeroOSParametro.equals("")){
			
			String dataRoteiroParametro = httpServletRequest.getParameter("dataRoteiro");
			String dataEncerramentoParametro = httpServletRequest.getParameter("dataEncerramento");
			
			String caminhoRetorno = httpServletRequest.getParameter("caminhoRetorno");
			
			if (caminhoRetorno != null && !caminhoRetorno.equalsIgnoreCase("")){
				form.setCaminhoRetorno(caminhoRetorno);
			}
			
			//Conversa��o dos objetos
			Integer numeroOS = Util.converterStringParaInteger(numeroOSParametro);
			form.setNumeroOSForm(numeroOSParametro);
			
			//Date dataRoteiro = null;
			if (dataRoteiroParametro != null && !dataRoteiroParametro.equals("")){
				//dataRoteiro = Util.converteStringParaDate(dataRoteiroParametro);
				form.setDataRoteiroForm(dataRoteiroParametro);
			}
			
			//Date dataEncerramento = null;
			if (dataEncerramentoParametro != null && !dataEncerramentoParametro.equals("")){
				//dataEncerramento = Util.converteStringParaDate(dataEncerramentoParametro);
				form.setDataEncerramentoForm(dataEncerramentoParametro);
			}
			

			/*
			 * O sistema apresenta uma lista de atividades associadas ao servi�o tipo que pertencem � ordem de servi�o
			 */
			Collection<Atividade> colecaoAtividadeOS = fachada.obterAtividadesOrdemServico(numeroOS);
			sessao.setAttribute("colecaoAtividade", colecaoAtividadeOS);
			
		}
		
		
		//Pesquisar Atividade
		if ((form.getIdAtividade() != null && !form.getIdAtividade().equals("")) &&
			(form.getDescricaoAtividade() == null || form.getDescricaoAtividade().equals(""))){
			
			this.pesquisarAtividade(form, fachada, httpServletRequest);
		}
		
		
		//Remover Atividade
		String removerAtividade = httpServletRequest.getParameter("removerAtividade");
		
		if (removerAtividade != null && !removerAtividade.equals("")){
			
			Atividade remover = new Atividade();
			remover.setId(new Integer(removerAtividade));
			
			Collection<Atividade> colecaoAtividade = (Collection) sessao.getAttribute("colecaoAtividade");
			colecaoAtividade.remove(remover);
			
			if (sessao.getAttribute("colecaoManterDadosAtividadesOrdemServicoHelper") != null){
				
				Collection colecaoManterDadosAtividadesOrdemServicoHelper = (Collection)
				sessao.getAttribute("colecaoManterDadosAtividadesOrdemServicoHelper");
				
				Iterator iteratorColecaoManterDadosAtividadesOrdemServicoHelper = 
				colecaoManterDadosAtividadesOrdemServicoHelper.iterator();
				
				ManterDadosAtividadesOrdemServicoHelper helper = null;
				
				while (iteratorColecaoManterDadosAtividadesOrdemServicoHelper.hasNext()){
					
					helper = (ManterDadosAtividadesOrdemServicoHelper)
					iteratorColecaoManterDadosAtividadesOrdemServicoHelper.next();
					
					if (helper.getOrdemServicoAtividade().getAtividade().getId()
						.equals(remover.getId())){
						
						colecaoManterDadosAtividadesOrdemServicoHelper.remove(helper);
						break;
					}
				}
			}
		}
		
		
		//Adicionar Atividade
		String adicionarAtividade = httpServletRequest.getParameter("adicionarAtividade");
		
		if (adicionarAtividade != null && !adicionarAtividade.equals("")){
			
			Atividade adicionar = this.pesquisarAtividade(form, fachada, httpServletRequest);
			
			Collection<Atividade> colecaoAtividade = (Collection) sessao.getAttribute("colecaoAtividade");
		
			//[FS0003 - Verificar exist�ncia da atividade]
			if (colecaoAtividade.contains(adicionar)){
				throw new ActionServletException("atencao.atividade_ja_existente");
			}
			
			colecaoAtividade.add(adicionar);
			
			form.setIdAtividade("");
			form.setDescricaoAtividade("");
			
			httpServletRequest.setAttribute("nomeCampo", "idAtividade");
		}
		
		
		//Pesquisar Atividade POPUP
		String pesquisarAtividade = httpServletRequest.getParameter("pesquisarAtividade");
		
		if (pesquisarAtividade != null && !pesquisarAtividade.equalsIgnoreCase("")){
			retorno = actionMapping.findForward("pesquisarAtividade");
		}
		
		
		//Recebendo dados Atividade POPUP
		if (httpServletRequest.getParameter("idCampoEnviarDados") != null){
			form.setIdAtividade(httpServletRequest.getParameter("idCampoEnviarDados"));
			form.setDescricaoAtividade(httpServletRequest.getParameter("descricaoCampoEnviarDados"));
		
			httpServletRequest.setAttribute("nomeCampo", "botaoAdicionar");
		}
		
		
		//Concluir Manuten��o
		String concluirManutencao = httpServletRequest.getParameter("inserir");
		
		if (concluirManutencao != null && !concluirManutencao.equalsIgnoreCase("")){
			
			if (sessao.getAttribute("colecaoManterDadosAtividadesOrdemServicoHelper") != null){
				
				Collection colecaoManterDadosAtividadesOrdemServicoHelper = (Collection)
				sessao.getAttribute("colecaoManterDadosAtividadesOrdemServicoHelper");
				
				if (colecaoManterDadosAtividadesOrdemServicoHelper != null &&
					!colecaoManterDadosAtividadesOrdemServicoHelper.isEmpty()){
					
					if (form.getCaminhoRetorno() != null && !form.getCaminhoRetorno().equalsIgnoreCase("")){
						
						fachada.manterDadosAtividadesOrdemServico(colecaoManterDadosAtividadesOrdemServicoHelper);
						
						sessao.removeAttribute("colecaoManterDadosAtividadesOrdemServicoHelper");
					
						httpServletRequest.setAttribute("caminhoRetorno", form.getCaminhoRetorno());
					}
					else{
						sessao.setAttribute("colecaoManutencao", colecaoManterDadosAtividadesOrdemServicoHelper);
					}
				}
				
				httpServletRequest.setAttribute("fecharPopup", "OK");
			}
		}
		
		if(httpServletRequest.getParameter("caminhoRetornoDadosAtividadesOS")!= null && 
				!httpServletRequest.getParameter("caminhoRetornoDadosAtividadesOS").equals("")){
			sessao.setAttribute("caminhoRetornoDadosAtividadesOS", httpServletRequest.getParameter("caminhoRetornoDadosAtividadesOS"));
		}

		return retorno;
	}
	
	
	
	private Atividade pesquisarAtividade(ManterDadosAtividadesOrdemServicoActionForm form, 
			Fachada fachada, HttpServletRequest httpServletRequest){
		
		Atividade retorno = null;
		
		FiltroAtividade filtroAtividade = new FiltroAtividade();
		
		filtroAtividade.adicionarParametro(new ParametroSimples(FiltroAtividade.ID,
		form.getIdAtividade()));
		
		filtroAtividade.adicionarParametro(new ParametroSimples(FiltroAtividade.INDICADORUSO,
		ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection colecaoAtividade = fachada.pesquisar(filtroAtividade,
		Atividade.class.getName());

		if (colecaoAtividade == null || colecaoAtividade.isEmpty()) {

			form.setIdAtividade("");
			form.setDescricaoAtividade("Atividade inexistente");

			httpServletRequest.setAttribute("corAtividade", "exception");
			httpServletRequest.setAttribute("nomeCampo", "idAtividade");

		} else {
			
			 Atividade atividade = (Atividade) Util
			.retonarObjetoDeColecao(colecaoAtividade);

			form.setIdAtividade(atividade.getId().toString());
			form.setDescricaoAtividade(atividade.getDescricao());

			httpServletRequest.setAttribute("nomeCampo", "idAtividade");
		
			retorno = atividade;
		}
		
		
		return retorno;
	}

}
