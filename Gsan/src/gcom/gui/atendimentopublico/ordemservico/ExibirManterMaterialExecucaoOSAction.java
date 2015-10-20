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
import gcom.atendimentopublico.ordemservico.FiltroMaterial;
import gcom.atendimentopublico.ordemservico.Material;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoAtividade;
import gcom.atendimentopublico.ordemservico.OsAtividadeMaterialExecucao;
import gcom.atendimentopublico.ordemservico.bean.ManterDadosAtividadesOrdemServicoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Esta classe tem por finalidade exibir para o usu�rio a tela que receber� os
 * par�metros para realiza��o da atualiza��o dos dados das atividades de uma OS
 * (Material)
 * 
 * @author Raphael Rossiter
 * @date 18/10/2006
 */
public class ExibirManterMaterialExecucaoOSAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("manterMaterialExecucaoOS");

		ManterMaterialExecucaoOSActionForm form = (ManterMaterialExecucaoOSActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();
		
		//Carregamento inicial
		String numeroOS = httpServletRequest.getParameter("ordemServico");
		
		if (numeroOS != null && !numeroOS.equalsIgnoreCase("")){
			
			//Identifica se a chamada foi feita por uma tela principal ou por um popup
			String caminhoRetorno = httpServletRequest.getParameter("caminhoRetorno");
			
			if (caminhoRetorno != null && !caminhoRetorno.equalsIgnoreCase("")){
				sessao.setAttribute("caminhoRetornoManterMaterial", caminhoRetorno);
			}
			
			String idAtividade = httpServletRequest.getParameter("idAtividade");
			String descricaoAtividade = httpServletRequest.getParameter("descricaoAtividade");
			
			form.setNumeroOS(numeroOS);
			form.setIdAtividade(idAtividade);
			form.setDescricaoAtividade(descricaoAtividade);
			
			//Obtendo material j� cadastrado - PARTE NOVA
			Collection colecaoSessao = (Collection) 
			sessao.getAttribute("colecaoManterDadosAtividadesOrdemServicoHelper");
			
			this.informarOrdemServicoAtividadeJaCadastrada(colecaoSessao, Util.converterStringParaInteger(numeroOS), 
			Util.converterStringParaInteger(idAtividade), sessao, fachada);
			
			
			//Material Programado
			Collection colecaoMaterial = fachada.obterMateriaisProgramados(Util.converterStringParaInteger(numeroOS));
			if (colecaoMaterial != null && !colecaoMaterial.isEmpty()){
				sessao.setAttribute("colecaoMaterialProgramado", colecaoMaterial);
			}
			
			
			//Inicializando o formul�rio
			form.setIdMaterialProgramado("");
			form.setIdMaterialNaoProgramado("");
			form.setDescricaoMaterialNaoProgramado("");
			
		}
		
		
		//Pesquisar Material ENTER
		if ((form.getIdMaterialNaoProgramado() != null && !form.getIdMaterialNaoProgramado().equals("")) &&
			(form.getDescricaoMaterialNaoProgramado() == null || form.getDescricaoMaterialNaoProgramado().equals(""))){

			FiltroMaterial filtroMaterial = new FiltroMaterial();
		
			filtroMaterial.adicionarParametro(new ParametroSimples(FiltroMaterial.ID,
			form.getIdMaterialNaoProgramado()));
			
			filtroMaterial.adicionarParametro(new ParametroSimples(FiltroMaterial.INDICADOR_USO,
			ConstantesSistema.INDICADOR_USO_ATIVO));
			
			Collection colecaoMaterial = fachada.pesquisar(filtroMaterial,
			Material.class.getName());
	
			if (colecaoMaterial == null || colecaoMaterial.isEmpty()) {
	
				form.setIdMaterialNaoProgramado("");
				form.setDescricaoMaterialNaoProgramado("MATERIAL INEXISTENTE");
	
				httpServletRequest.setAttribute("corMaterial", "exception");
				httpServletRequest.setAttribute("nomeCampo", "idMaterialNaoProgramado");
	
			} else {
				
				 Material material = (Material) Util
				.retonarObjetoDeColecao(colecaoMaterial);
	
				 form.setIdMaterialNaoProgramado(material.getId().toString());
				 form.setDescricaoMaterialNaoProgramado(material.getDescricao());
				
				httpServletRequest.setAttribute("nomeCampo", "botaoAdicionar");
			
			}
		}
		
		
		//Pesquisar Material POPUP
		String pesquisarMaterial = httpServletRequest.getParameter("pesquisarMaterial");
		
		if (pesquisarMaterial != null && !pesquisarMaterial.equalsIgnoreCase("")){
			retorno = actionMapping.findForward("pesquisarMaterial");
		}
		
		
		//Recebendo dados Material POPUP
		if (httpServletRequest.getParameter("idCampoEnviarDados") != null){
			form.setIdMaterialNaoProgramado(httpServletRequest.getParameter("idCampoEnviarDados"));
			form.setDescricaoMaterialNaoProgramado(httpServletRequest.getParameter("descricaoCampoEnviarDados"));
		
			httpServletRequest.setAttribute("nomeCampo", "botaoAdicionar");
		}
		
		
		//Adicionar
		String adicionarMaterial = httpServletRequest.getParameter("adicionarMaterial");
		
		if (adicionarMaterial != null && !adicionarMaterial.equalsIgnoreCase("")){
			
			//Verificando as informa��es colhidas
			//===================================================================================
			Integer idMaterial = null;
			if (form.getIdMaterialProgramado() != null && !form.getIdMaterialProgramado().equalsIgnoreCase("")){
				idMaterial = Util.converterStringParaInteger(form.getIdMaterialProgramado());
			}
			else{
				idMaterial = Util.converterStringParaInteger(form.getIdMaterialNaoProgramado());
			}
			
			//===================================================================================
			
			
			//Informando OS_ATIVIDADE_MATERIAL_EXECUCAO
			if (sessao.getAttribute("colecaoManterDadosAtividadesOrdemServicoHelper") != null){
				
				Collection colecaoManterDadosAtividadesOrdemServicoHelper = (Collection)
				sessao.getAttribute("colecaoManterDadosAtividadesOrdemServicoHelper");
				
				this.informarOrdemServicoAtividade(colecaoManterDadosAtividadesOrdemServicoHelper, 
				Util.converterStringParaInteger(form.getNumeroOS()), 
				Util.converterStringParaInteger(form.getIdAtividade()), idMaterial, sessao, fachada);
			}
			else{
				
				this.informarOrdemServicoAtividade(null, 
				Util.converterStringParaInteger(form.getNumeroOS()), 
				Util.converterStringParaInteger(form.getIdAtividade()), idMaterial, sessao, fachada);
				
			}
			
			//Inicializando o formul�rio
			form.setIdMaterialProgramado("");
			form.setIdMaterialNaoProgramado("");
			form.setDescricaoMaterialNaoProgramado("");
			
			httpServletRequest.setAttribute("nomeCampo", "idMaterialProgramado");
		}
		
		
		//Remover
		String removerMaterial = httpServletRequest.getParameter("removerMaterial");
		if (removerMaterial != null && !removerMaterial.equalsIgnoreCase("")){
			
			Integer identificadorMaterial = Util.converterStringParaInteger(removerMaterial);
			Collection colecaoSessao = (Collection) sessao.getAttribute("colecaoManterDadosAtividadesOrdemServicoHelper");
			
			this.removerMaterial(identificadorMaterial, 
			Util.converterStringParaInteger(form.getIdAtividade()), colecaoSessao);
			
			//Inicializando o formul�rio
			form.setIdMaterialProgramado("");
			form.setIdMaterialNaoProgramado("");
			form.setDescricaoMaterialNaoProgramado("");
			
			httpServletRequest.setAttribute("nomeCampo", "idMaterialProgramado");
		}
		
		
		//Atualizar Quantidade
		String atualizarQuantidade = httpServletRequest.getParameter("atualizarQuantidade");
		if (atualizarQuantidade != null && !atualizarQuantidade.equalsIgnoreCase("")){
			
			if (sessao.getAttribute("caminhoRetornoManterMaterial") != null){
				httpServletRequest.setAttribute("voltar", "OK");
			}
			else{
				httpServletRequest.setAttribute("inserir", "OK");
			}
		}
		
		
		//Atualizar Quantidade
		if (sessao.getAttribute("colecaoManterDadosAtividadesOrdemServicoHelper") != null){
			
			Collection colecaoSessao = (Collection) 
			sessao.getAttribute("colecaoManterDadosAtividadesOrdemServicoHelper");
			
			Map<String, String[]> requestMap = httpServletRequest.getParameterMap();
			
			this.atualizarQuantidadeMaterial(colecaoSessao, 
			Util.converterStringParaInteger(form.getIdAtividade()), requestMap);
		}
		
		
		//Objetos utilizados apenas para facilitar a quebra na exibi��o
		httpServletRequest.setAttribute("numeroOS", form.getNumeroOS());
		httpServletRequest.setAttribute("idAtividade", form.getIdAtividade());
		
		return retorno;
	}
	
	
	
	private void informarOrdemServicoAtividadeJaCadastrada(Collection colecaoSessao, Integer numeroOS, 
			Integer idAtividade, HttpSession sessao, Fachada fachada){
		
		ManterDadosAtividadesOrdemServicoHelper manterDadosAtividadesOrdemServicoHelper = null;
		
		if (colecaoSessao != null && !colecaoSessao.isEmpty()){
			
			boolean ordemServicoAtividadeInformada = false;
			Iterator iteratorColecaoSessao = colecaoSessao.iterator();
			
			while(iteratorColecaoSessao.hasNext()){
			
				manterDadosAtividadesOrdemServicoHelper = (ManterDadosAtividadesOrdemServicoHelper)
				iteratorColecaoSessao.next();
			
				/*
				 * Verifica se j� existe na cole��o uma OrdemServicoAtividade com o mesmo n�mero de OS e 
				 * mesma atividade informada
				 */
				if (manterDadosAtividadesOrdemServicoHelper.getOrdemServicoAtividade().getOrdemServico().getId()
					.equals(numeroOS) && manterDadosAtividadesOrdemServicoHelper.getOrdemServicoAtividade()
					.getAtividade().getId().equals(idAtividade)){
					
					//Informando OsAtividadeMaterialExecucao
					Collection colecaoOsAtividadeMaterialExecucao = null;
					
					if (manterDadosAtividadesOrdemServicoHelper
						.getOrdemServicoAtividade().getId() != null){
						
						colecaoOsAtividadeMaterialExecucao = fachada.pesquisarOsAtividadeMaterialExecucao(
						manterDadosAtividadesOrdemServicoHelper.getOrdemServicoAtividade().getId());
					} 
					
					if (colecaoOsAtividadeMaterialExecucao != null &&
						!colecaoOsAtividadeMaterialExecucao.isEmpty()){
					
						manterDadosAtividadesOrdemServicoHelper.setColecaoOsAtividadeMaterialExecucao(
						colecaoOsAtividadeMaterialExecucao);
					}
					
					ordemServicoAtividadeInformada = true;
					break;
				}
			}
		
			/*
			 * Caso j� exista OrdemServicoAtividade informada, por�m ainda n�o foi cadastrada nenhuma com o mesmo
			 * n�mero de OS e atividade informados
			 */
			if (!ordemServicoAtividadeInformada){
				
				//1� Passo - Gerando o objeto
				manterDadosAtividadesOrdemServicoHelper = this.gerarOrdemServicoAtividadeJaCadastrada(numeroOS, 
				idAtividade, fachada);
				
				//2� Passo - Adicionando o objeto na cole��o que foi recebida (J� foi criada e colocada na sess�o)
				colecaoSessao.add(manterDadosAtividadesOrdemServicoHelper);
			}
		}
		else{
			
			//Primeira OrdemServicoAtividade informada
			
			//1� Passo - Gerando o objeto
			manterDadosAtividadesOrdemServicoHelper = this.gerarOrdemServicoAtividadeJaCadastrada(numeroOS, 
			idAtividade, fachada);
			
			if (manterDadosAtividadesOrdemServicoHelper != null){
			
				//2� Passo - Adicionando o objeto em uma cole��o vazia
				Collection colecaoManterDadosAtividadesOrdemServicoHelper = new ArrayList();
				colecaoManterDadosAtividadesOrdemServicoHelper.add(manterDadosAtividadesOrdemServicoHelper);
				
				//3� Passo - Colocando a cole��o gerada na sess�o
				sessao.setAttribute("colecaoManterDadosAtividadesOrdemServicoHelper", 
				colecaoManterDadosAtividadesOrdemServicoHelper);
			}
		}
	}
	
	
	private ManterDadosAtividadesOrdemServicoHelper gerarOrdemServicoAtividadeJaCadastrada(Integer numeroOS, 
			Integer idAtividade, Fachada fachada){
		
		ManterDadosAtividadesOrdemServicoHelper manterDadosAtividadesOrdemServicoHelper = null;
		
		OrdemServicoAtividade ordemServicoAtividade = fachada.pesquisarOrdemServicoAtividade(numeroOS,
		idAtividade);
			
		if (ordemServicoAtividade != null){
			
			manterDadosAtividadesOrdemServicoHelper = new ManterDadosAtividadesOrdemServicoHelper();
		
			manterDadosAtividadesOrdemServicoHelper.setOrdemServicoAtividade(ordemServicoAtividade);
			
			//Informando OsAtividadeMaterialExecucao
			Collection colecaoOsAtividadeMaterialExecucao = fachada.pesquisarOsAtividadeMaterialExecucao(
			ordemServicoAtividade.getId());
			
			if (colecaoOsAtividadeMaterialExecucao != null && !colecaoOsAtividadeMaterialExecucao.isEmpty()){
				
				manterDadosAtividadesOrdemServicoHelper.setColecaoOsAtividadeMaterialExecucao(
				colecaoOsAtividadeMaterialExecucao);
			}
		}
		
		
		return manterDadosAtividadesOrdemServicoHelper;
	}
	
	
	//===============================================================================================================
	
	private void informarOrdemServicoAtividade(Collection colecaoSessao, Integer numeroOS, 
			Integer idAtividade, Integer idMaterial ,HttpSession sessao, Fachada fachada){
		
		ManterDadosAtividadesOrdemServicoHelper manterDadosAtividadesOrdemServicoHelper = null;
		
		if (colecaoSessao != null && !colecaoSessao.isEmpty()){
			
			boolean ordemServicoAtividadeInformada = false;
			Iterator iteratorColecaoSessao = colecaoSessao.iterator();
			
			while(iteratorColecaoSessao.hasNext()){
			
				manterDadosAtividadesOrdemServicoHelper = (ManterDadosAtividadesOrdemServicoHelper)
				iteratorColecaoSessao.next();
			
				/*
				 * Verifica se j� existe na cole��o uma OrdemServicoAtividade com o mesmo n�mero de OS e 
				 * mesma atividade informada
				 */
				if (manterDadosAtividadesOrdemServicoHelper.getOrdemServicoAtividade().getOrdemServico().getId()
					.equals(numeroOS) && manterDadosAtividadesOrdemServicoHelper.getOrdemServicoAtividade()
					.getAtividade().getId().equals(idAtividade)){
					
					//Informando OsAtividadePeriodoExecucao
					Collection colecaoOsAtividadeMaterialExecucao = this.informarOsAtividadeMaterialExecucao(
					manterDadosAtividadesOrdemServicoHelper.getColecaoOsAtividadeMaterialExecucao(),
					numeroOS, idMaterial, fachada);
					
					manterDadosAtividadesOrdemServicoHelper.setColecaoOsAtividadeMaterialExecucao(
					colecaoOsAtividadeMaterialExecucao);
					
					ordemServicoAtividadeInformada = true;
					break;
				}
			}
		
			/*
			 * Caso j� exista OrdemServicoAtividade informada, por�m ainda n�o foi cadastrada nenhuma com o mesmo
			 * n�mero de OS e atividade informados
			 */
			if (!ordemServicoAtividadeInformada){
				
				//1� Passo - Gerando o objeto
				manterDadosAtividadesOrdemServicoHelper = this.gerarOrdemServicoAtividade(numeroOS, 
				idAtividade, idMaterial, fachada);
				
				//2� Passo - Adicionando o objeto na cole��o que foi recebida (J� foi criada e colocada na sess�o)
				colecaoSessao.add(manterDadosAtividadesOrdemServicoHelper);
			}
		}
		else{
			
			//Primeira OrdemServicoAtividade informada
			
			//1� Passo - Gerando o objeto
			manterDadosAtividadesOrdemServicoHelper = this.gerarOrdemServicoAtividade(numeroOS, 
			idAtividade, idMaterial, fachada);
			
			//2� Passo - Adicionando o objeto em uma cole��o vazia
			Collection colecaoManterDadosAtividadesOrdemServicoHelper = new ArrayList();
			colecaoManterDadosAtividadesOrdemServicoHelper.add(manterDadosAtividadesOrdemServicoHelper);
			
			//3� Passo - Colocando a cole��o gerada na sess�o
			sessao.setAttribute("colecaoManterDadosAtividadesOrdemServicoHelper", 
			colecaoManterDadosAtividadesOrdemServicoHelper);
			
		}
	}
	
	private ManterDadosAtividadesOrdemServicoHelper gerarOrdemServicoAtividade(Integer numeroOS, Integer idAtividade, 
			Integer idMaterial, Fachada fachada){
		
		ManterDadosAtividadesOrdemServicoHelper manterDadosAtividadesOrdemServicoHelper = 
		new ManterDadosAtividadesOrdemServicoHelper();
		
		OrdemServico ordemServico = new OrdemServico();
		ordemServico.setId(numeroOS);
		
		Atividade atividade = new Atividade();
		atividade.setId(idAtividade);
		
		OrdemServicoAtividade ordemServicoAtividade = new OrdemServicoAtividade();
		ordemServicoAtividade.setAtividade(atividade);
		ordemServicoAtividade.setOrdemServico(ordemServico);
		
		manterDadosAtividadesOrdemServicoHelper.setOrdemServicoAtividade(ordemServicoAtividade);
		
		//Informando OsAtividadeMaterialExecucao
		Collection colecaoOsAtividadeMaterialExecucaoHelper = this.informarOsAtividadeMaterialExecucao(
		null, numeroOS, idMaterial, fachada);
		
		manterDadosAtividadesOrdemServicoHelper.setColecaoOsAtividadeMaterialExecucao(
		colecaoOsAtividadeMaterialExecucaoHelper);
		
		return manterDadosAtividadesOrdemServicoHelper;
		
	}
	
	
	//================================================================================================================
	
	private Collection informarOsAtividadeMaterialExecucao(Collection colecaoOsAtividadeMaterialExecucao, 
			Integer numeroOS, Integer idMaterial, Fachada fachada){
		
		Collection colecaoRetorno = null;
		OsAtividadeMaterialExecucao osAtividadeMaterialExecucao = null;
		
		if (colecaoOsAtividadeMaterialExecucao != null &&
			!colecaoOsAtividadeMaterialExecucao.isEmpty()){
			
			Iterator iteratorColecaoOsAtividadeMaterialExecucao = colecaoOsAtividadeMaterialExecucao.iterator();
			
			while(iteratorColecaoOsAtividadeMaterialExecucao.hasNext()){
				
				osAtividadeMaterialExecucao = (OsAtividadeMaterialExecucao)
				iteratorColecaoOsAtividadeMaterialExecucao.next();
			
				/*
				 * Verifica se j� existe na cole��o uma OsAtividadeMaterialExecucao com o mesmo material
				 * 
				 *[FS0012] - Verificar Material j� existente
				 */
				if (osAtividadeMaterialExecucao.getMaterial().getId().equals(idMaterial)){
					
					throw new ActionServletException(
					"atencao.material_ja_informado");
				}
			}
			
			/*
			 * Caso j� exista OsAtividadeMaterialExecucao informada, por�m ainda n�o foi cadastrada nenhuma 
			 * com o mesmo material
			 */
			
			//1� Passo - Gerando o objeto
			osAtividadeMaterialExecucao = this.gerarOsAtividadeMaterialExecucao(numeroOS, idMaterial, fachada);
				
			//2� Passo - Adicionando o objeto na cole��o que foi recebida (J� foi criada e colocada na sess�o)
			colecaoOsAtividadeMaterialExecucao.add(osAtividadeMaterialExecucao);
			
			return colecaoOsAtividadeMaterialExecucao;
		}
		else{
			
			//Primeira OsAtividadeMaterialExecucao informada
			
			//1� Passo - Gerando o objeto
			osAtividadeMaterialExecucao = this.gerarOsAtividadeMaterialExecucao(numeroOS, idMaterial, fachada);
			
			//2� Passo - Adicionando o objeto em uma cole��o vazia
			colecaoRetorno = new ArrayList();
			colecaoRetorno.add(osAtividadeMaterialExecucao);
			
			return colecaoRetorno;
		}
	}
	
	
	private OsAtividadeMaterialExecucao gerarOsAtividadeMaterialExecucao(Integer numeroOS, 
			Integer idMaterial, Fachada fachada){
			
		OsAtividadeMaterialExecucao osAtividadeMaterialExecucao = 
		new OsAtividadeMaterialExecucao();
		
		FiltroMaterial filtroMaterial = new FiltroMaterial();
		
		filtroMaterial.adicionarParametro(new ParametroSimples(FiltroMaterial.ID, 
		idMaterial));
		
		Collection colecaoMaterial = fachada.pesquisar(filtroMaterial, Material.class.getName());
		
		//[FS0011] - Verificar Exist�ncia do Material
		if (colecaoMaterial == null || colecaoMaterial.isEmpty()){
			
			throw new ActionServletException(
			"atencao.pesquisa_inexistente", null, "Material");
		}
		
		Material material = (Material) Util.retonarObjetoDeColecao(colecaoMaterial);
		
		osAtividadeMaterialExecucao.setMaterial(material);
		
		//Obter quantidade padr�o de acordo com o material e a OS
		BigDecimal qtdMaterial = fachada.obterQuantidadePadraoMaterial(numeroOS, idMaterial);
		
		if (qtdMaterial != null){
			osAtividadeMaterialExecucao.setQuantidadeMaterial(qtdMaterial);
		}
		
		return osAtividadeMaterialExecucao;
			
	}
	
	
	//=================================================================================================================
	
	private void removerMaterial(Integer identificacaoMaterial, Integer idAtividade,Collection colecaoSessao){
		
		Iterator iteratorColecaoSessao = colecaoSessao.iterator();
		ManterDadosAtividadesOrdemServicoHelper manterDadosAtividadesOrdemServicoHelper = null;
		OsAtividadeMaterialExecucao osAtividadeMaterialExecucao = null;
		
		//Atividade
		while (iteratorColecaoSessao.hasNext()){
			
			manterDadosAtividadesOrdemServicoHelper = (ManterDadosAtividadesOrdemServicoHelper)
			iteratorColecaoSessao.next();
			
			if (manterDadosAtividadesOrdemServicoHelper.getOrdemServicoAtividade().getAtividade().getId()
				.equals(idAtividade)){
				
				//Material
				Collection colecaoOSAtividadeMaterialExecucao = 
				manterDadosAtividadesOrdemServicoHelper.getColecaoOsAtividadeMaterialExecucao();
				
				Iterator iteratorColecaoOSAtividadeMaterialExecucao = 
				colecaoOSAtividadeMaterialExecucao.iterator();
				
				while (iteratorColecaoOSAtividadeMaterialExecucao.hasNext()){
					
					osAtividadeMaterialExecucao = (OsAtividadeMaterialExecucao)
					iteratorColecaoOSAtividadeMaterialExecucao.next();
					
					if (osAtividadeMaterialExecucao.getMaterial().getId().equals(identificacaoMaterial)){
							
						colecaoOSAtividadeMaterialExecucao.remove(osAtividadeMaterialExecucao);
							
						break;
					}
				}
			}
		}
	}
	
	
	//=================================================================================================================
	
	private void atualizarQuantidadeMaterial(Collection colecaoSessao, Integer idAtividade,
			Map<String, String[]> requestMap){
		
		Iterator iteratorColecaoSessao = colecaoSessao.iterator();
    	
    	ManterDadosAtividadesOrdemServicoHelper helper = null;
    	String quantidade = null;
    	BigDecimal quantidadeAtualizada = new BigDecimal ("0.00"); 
    	
    	while (iteratorColecaoSessao.hasNext()) {
			
    		helper = (ManterDadosAtividadesOrdemServicoHelper) iteratorColecaoSessao.next();
    		
			if (helper.getOrdemServicoAtividade().getAtividade().getId().equals(idAtividade)){
			
				Collection colecaoOsAtividadeMaterialExecucao = helper.getColecaoOsAtividadeMaterialExecucao();
				
				if (colecaoOsAtividadeMaterialExecucao != null &&
					!colecaoOsAtividadeMaterialExecucao.isEmpty()){
					
					Iterator iteratorColecaoOsAtividadeMaterialExecucao = 
					colecaoOsAtividadeMaterialExecucao.iterator();
					
					OsAtividadeMaterialExecucao osAtividadeMaterialExecucao = null;
				
					while (iteratorColecaoOsAtividadeMaterialExecucao.hasNext()){
						
						osAtividadeMaterialExecucao = (OsAtividadeMaterialExecucao) 
						iteratorColecaoOsAtividadeMaterialExecucao.next();
						
						if (requestMap.get("material"
							+ osAtividadeMaterialExecucao.getMaterial().getId()) != null) {
							
							quantidade = (requestMap.get("material" 
							+ + osAtividadeMaterialExecucao.getMaterial().getId()))[0];
							
							quantidadeAtualizada = Util.formatarMoedaRealparaBigDecimal(quantidade);
							
							osAtividadeMaterialExecucao.setQuantidadeMaterial(quantidadeAtualizada);
						}
					}
				}
			}
    	}
	}
}
