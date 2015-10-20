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
import gcom.atendimentopublico.ordemservico.Equipe;
import gcom.atendimentopublico.ordemservico.FiltroEquipe;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoAtividade;
import gcom.atendimentopublico.ordemservico.OsAtividadePeriodoExecucao;
import gcom.atendimentopublico.ordemservico.OsExecucaoEquipe;
import gcom.atendimentopublico.ordemservico.bean.ManterDadosAtividadesOrdemServicoHelper;
import gcom.atendimentopublico.ordemservico.bean.OSAtividadePeriodoExecucaoHelper;
import gcom.atendimentopublico.ordemservico.bean.OSExecucaoEquipeHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
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
 * Esta classe tem por finalidade exibir para o usu�rio a tela que receber� os
 * par�metros para realiza��o da atualiza��o dos dados das atividades de uma OS
 * (Horas)
 * 
 * @author Raphael Rossiter
 * @date 18/09/2006
 */
public class ExibirManterHorasExecucaoEquipeOSAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("manterHorasExecucaoEquipeOS");

		ManterHorasExecucaoEquipeOSActionForm form = (ManterHorasExecucaoEquipeOSActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();
		
		//Carregamento inicial
		String numeroOS = httpServletRequest.getParameter("ordemServico");
		
		if (numeroOS != null && !numeroOS.equalsIgnoreCase("")){
			
			//Identifica se a chamada foi feita por uma tela principal ou por um popup
			String caminhoRetorno = httpServletRequest.getParameter("caminhoRetorno");
			
			if (caminhoRetorno != null && !caminhoRetorno.equalsIgnoreCase("")){
				sessao.setAttribute("caminhoRetornoManterHoras", caminhoRetorno);
			}
			
			String idAtividade = httpServletRequest.getParameter("idAtividade");
			String descricaoAtividade = httpServletRequest.getParameter("descricaoAtividade");
			
			form.setNumeroOS(numeroOS);
			form.setIdAtividade(idAtividade);
			form.setDescricaoAtividade(descricaoAtividade);
			
			/*
			 * Apresentar na data de execu��o a data do roteiro caso tenha sido informada, caso contr�rio informar 
			 * a data do encerramento
			 */
			String dataRoteiro = httpServletRequest.getParameter("dataRoteiro");
			String dataEncerramento = httpServletRequest.getParameter("dataEncerramento");
			
			if (dataRoteiro != null && !dataRoteiro.equalsIgnoreCase("")){
				form.setDataExecucao(dataRoteiro);
				form.setDataEncerramentoOS("");
				httpServletRequest.setAttribute("nomeCampo", "horaInicioExecucao");
				sessao.setAttribute("desabilitarDataExecucao", "OK");
			}
			else{
				form.setDataExecucao(dataEncerramento);
				form.setDataEncerramentoOS(dataEncerramento);
				httpServletRequest.setAttribute("nomeCampo", "dataExecucao");
				sessao.removeAttribute("desabilitarDataExecucao");
			}
			
			//Equipes Programadas
			Collection colecaoEquipes = fachada.obterEquipesProgramadas(Util
					.converterStringParaInteger(numeroOS));
			if (colecaoEquipes != null && !colecaoEquipes.isEmpty()) {
				sessao.setAttribute("colecaoEquipe", colecaoEquipes);
			} 
			
			
			//Caso a OS esteja associada a um documento de cobran�a
			sessao.removeAttribute("documentoCobranca");
			sessao.removeAttribute("registroAtendimento");
			sessao.removeAttribute("colecaoEquipesPorUnidade");
			
			if (fachada.verificarOSAssociadaDocumentoCobranca(Util.converterStringParaInteger(numeroOS))){
				sessao.setAttribute("documentoCobranca", "OK");
			}
			//Caso a OS esteja associada a um RA
			else if (fachada.verificarOSAssociadaRA(Util.converterStringParaInteger(numeroOS))){
				
				//Pesquisa o �ltimo tramite do RA para obter a unidade de destino
				Integer idUnidadeDestino = fachada.obterUnidadeDestinoUltimoTramite(Util.converterStringParaInteger(numeroOS));
				if (idUnidadeDestino != null){
					
					//Obt�m todas as equipes pertencentens a unidade de destino
					Collection colecaoEquipesPorUnidade = fachada.obterEquipesPorUnidade(idUnidadeDestino);
					
					if (colecaoEquipesPorUnidade != null && !colecaoEquipesPorUnidade.isEmpty()){
						sessao.setAttribute("registroAtendimento", "OK");
						sessao.setAttribute("colecaoEquipesPorUnidade", colecaoEquipesPorUnidade);
					}else{
						throw new ActionServletException("atencao.unidade_sem_equipe");
					}
				}
			}
			
			
			
			
			//Inicializando o formul�rio
			form.setHoraInicioExecucao("");
			form.setHoraFimExecucao("");
			form.setIdEquipeProgramada("");
			form.setIdEquipeNaoProgramada("");
			form.setDescricaoEquipeNaoProgramada("");
			
		}
		
		
		//Pesquisar Equipe ENTER
		if ((form.getIdEquipeNaoProgramada() != null && !form.getIdEquipeNaoProgramada().equals("")) &&
			(form.getDescricaoEquipeNaoProgramada() == null || form.getDescricaoEquipeNaoProgramada().equals(""))){

			FiltroEquipe filtroEquipe = new FiltroEquipe();
		
			filtroEquipe.adicionarParametro(new ParametroSimples(FiltroEquipe.ID,
			form.getIdEquipeNaoProgramada()));
			
			filtroEquipe.adicionarParametro(new ParametroSimples(FiltroEquipe.INDICADOR_USO,
			ConstantesSistema.INDICADOR_USO_ATIVO));
			
			Collection colecaoEquipe = fachada.pesquisar(filtroEquipe,
			Equipe.class.getName());
	
			if (colecaoEquipe == null || colecaoEquipe.isEmpty()) {
	
				form.setIdEquipeNaoProgramada("");
				form.setDescricaoEquipeNaoProgramada("EQUIPE INEXISTENTE");
	
				httpServletRequest.setAttribute("corEquipe", "exception");
				httpServletRequest.setAttribute("nomeCampo", "idEquipeNaoProgramada");
	
			} else {
				
				 Equipe equipe = (Equipe) Util
				.retonarObjetoDeColecao(colecaoEquipe);
	
				form.setIdEquipeNaoProgramada(equipe.getId().toString());
				form.setDescricaoEquipeNaoProgramada(equipe.getNome());
				
				httpServletRequest.setAttribute("nomeCampo", "botaoAdicionar");
			
			}
		}
		
		
		//Pesquisar Equipe POPUP
		String pesquisarEquipe = httpServletRequest.getParameter("pesquisarEquipe");
		
		if (pesquisarEquipe != null && !pesquisarEquipe.equalsIgnoreCase("")){
			retorno = actionMapping.findForward("pesquisarEquipe");
		}
		
		//Recebendo dados Equipe POPUP
		if (httpServletRequest.getParameter("idCampoEnviarDados") != null){
			form.setIdEquipeNaoProgramada(httpServletRequest.getParameter("idCampoEnviarDados"));
			form.setDescricaoEquipeNaoProgramada(httpServletRequest.getParameter("descricaoCampoEnviarDados"));
		
			httpServletRequest.setAttribute("nomeCampo", "horaInicioExecucao");
		}
		
		
		//Adicionar
		String adicionarPeriodoEquipe = httpServletRequest.getParameter("adicionarPeriodoEquipe");
		
		if (adicionarPeriodoEquipe != null && !adicionarPeriodoEquipe.equalsIgnoreCase("")){
			
			//Verificando as informa��es colhidas
			//===================================================================================
			Integer idEquipe = null;
			if (form.getIdEquipeProgramada() != null && !form.getIdEquipeProgramada().equalsIgnoreCase("")){
				idEquipe = Util.converterStringParaInteger(form.getIdEquipeProgramada());
			}
			else{
				idEquipe = Util.converterStringParaInteger(form.getIdEquipeNaoProgramada());
			}
			
			fachada.verificaDadosAdicionarPeriodoEquipe(form.getDataExecucao(), form.getHoraInicioExecucao(),
			form.getHoraFimExecucao(), idEquipe, form.getDataEncerramentoOS(), Util.converterStringParaInteger(form.getNumeroOS()));
			//===================================================================================
			
			
			//Informando OrdemServicoAtividade
			if (sessao.getAttribute("colecaoManterDadosAtividadesOrdemServicoHelper") != null){
				
				Collection colecaoManterDadosAtividadesOrdemServicoHelper = (Collection)
				sessao.getAttribute("colecaoManterDadosAtividadesOrdemServicoHelper");
				
				this.informarOrdemServicoAtividade(colecaoManterDadosAtividadesOrdemServicoHelper, 
				Util.converterStringParaInteger(form.getNumeroOS()), 
			    Util.converterStringParaInteger(form.getIdAtividade()), form.getDataExecucao(), form.getHoraInicioExecucao(), form.getHoraFimExecucao(), 
			    idEquipe, sessao, fachada);
			}
			else{
				
				this.informarOrdemServicoAtividade(null, 
				Util.converterStringParaInteger(form.getNumeroOS()), 
				Util.converterStringParaInteger(form.getIdAtividade()), form.getDataExecucao(), form.getHoraInicioExecucao(), form.getHoraFimExecucao(), 
				idEquipe, sessao, fachada);
				
			}
			
			//Inicializando o formul�rio
			form.setHoraInicioExecucao("");
			form.setHoraFimExecucao("");
			form.setIdEquipeProgramada("");
			form.setIdEquipeNaoProgramada("");
			form.setDescricaoEquipeNaoProgramada("");
			
			httpServletRequest.setAttribute("nomeCampo", "horaInicioExecucao");
		}
		
		
		//Remover
		String removerPeriodoEquipe = httpServletRequest.getParameter("removerPeriodoEquipe");
		
		if (removerPeriodoEquipe != null && !removerPeriodoEquipe.equalsIgnoreCase("")){
			
			long identificadorEquipe = Long.valueOf(removerPeriodoEquipe);
			Collection colecaoSessao = (Collection) sessao.getAttribute("colecaoManterDadosAtividadesOrdemServicoHelper");
			
			this.removerPeriodoExecucaoEquipe(identificadorEquipe, 
			Util.converterStringParaInteger(form.getIdAtividade()), colecaoSessao);
			
			//Inicializando o formul�rio
			form.setHoraInicioExecucao("");
			form.setHoraFimExecucao("");
			form.setIdEquipeProgramada("");
			form.setIdEquipeNaoProgramada("");
			form.setDescricaoEquipeNaoProgramada("");
			
			httpServletRequest.setAttribute("nomeCampo", "horaInicioExecucao");
		}
		
		
		//Objetos utilizados apenas para facilitar a quebra na exibi��o
		httpServletRequest.setAttribute("numeroOS", form.getNumeroOS());
		httpServletRequest.setAttribute("idAtividade", form.getIdAtividade());
		
		return retorno;
	}
	
	
	
	private void informarOrdemServicoAtividade(Collection colecaoSessao, Integer numeroOS, 
			Integer idAtividade, String dataExecucao, String horaInicio, String horaFim, 
			Integer idEquipe ,HttpSession sessao, Fachada fachada){
		
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
					Collection colecaoOsAtividadePeriodoExecucaoHelper = this.informarOsAtividadePeriodoExecucao(manterDadosAtividadesOrdemServicoHelper
					.getColecaoOSAtividadePeriodoExecucaoHelper(), dataExecucao,horaInicio, horaFim, idEquipe, fachada);
					
					manterDadosAtividadesOrdemServicoHelper.setColecaoOSAtividadePeriodoExecucaoHelper(
					colecaoOsAtividadePeriodoExecucaoHelper);
					
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
				idAtividade, dataExecucao, horaInicio, horaFim, idEquipe, fachada);
				
				//2� Passo - Adicionando o objeto na cole��o que foi recebida (J� foi criada e colocada na sess�o)
				colecaoSessao.add(manterDadosAtividadesOrdemServicoHelper);
			}
		}
		else{
			
			//Primeira OrdemServicoAtividade informada
			
			//1� Passo - Gerando o objeto
			manterDadosAtividadesOrdemServicoHelper = this.gerarOrdemServicoAtividade(numeroOS, 
			idAtividade, dataExecucao, horaInicio, horaFim, idEquipe, fachada);
			
			//2� Passo - Adicionando o objeto em uma cole��o vazia
			Collection colecaoManterDadosAtividadesOrdemServicoHelper = new ArrayList();
			colecaoManterDadosAtividadesOrdemServicoHelper.add(manterDadosAtividadesOrdemServicoHelper);
			
			//3� Passo - Colocando a cole��o gerada na sess�o
			sessao.setAttribute("colecaoManterDadosAtividadesOrdemServicoHelper", 
			colecaoManterDadosAtividadesOrdemServicoHelper);
			
		}
	}
	
	private ManterDadosAtividadesOrdemServicoHelper gerarOrdemServicoAtividade(Integer numeroOS, Integer idAtividade, String 
		dataExecucao, String horaInicio, String horaFim, Integer idEquipe, Fachada fachada){
		
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
		
		//Informando OsAtividadePeriodoExecucao
		Collection colecaoOsAtividadePeriodoExecucaoHelper = this.informarOsAtividadePeriodoExecucao(null, 
		dataExecucao,horaInicio, horaFim, idEquipe, fachada);
		
		manterDadosAtividadesOrdemServicoHelper.setColecaoOSAtividadePeriodoExecucaoHelper(
		colecaoOsAtividadePeriodoExecucaoHelper);
		
		return manterDadosAtividadesOrdemServicoHelper;
		
	}
	
	
	
	private Collection informarOsAtividadePeriodoExecucao(Collection colecaoOsAtividadePeriodoExecucaoHelper, 
			String dataExecucao, String horaInicio, String horaFim, Integer idEquipe, Fachada fachada){
		
		Collection colecaoRetorno = null;
		OSAtividadePeriodoExecucaoHelper osAtividadePeriodoExecucaoHelper = null;
		
		if (colecaoOsAtividadePeriodoExecucaoHelper != null &&
			!colecaoOsAtividadePeriodoExecucaoHelper.isEmpty()){
			
			boolean osAtividadePeriodoExecucaoInformada = false;
			Iterator iteratorcolecaoOsAtividadePeriodoExecucaoHelper = colecaoOsAtividadePeriodoExecucaoHelper.iterator();
			
			while(iteratorcolecaoOsAtividadePeriodoExecucaoHelper.hasNext()){
				
				osAtividadePeriodoExecucaoHelper = (OSAtividadePeriodoExecucaoHelper)
				iteratorcolecaoOsAtividadePeriodoExecucaoHelper.next();
				
				/*
				 * Verifica se j� existe na cole��o uma OSAtividadePeriodoExecucaoHelper com a mesma data de execu��o 
				 * e mesmo per�odo de execu��o
				 */
				if (osAtividadePeriodoExecucaoHelper.getDataExecucaoParaQuebra()
					.equals(Util.converteStringParaDate(dataExecucao)) &&
					osAtividadePeriodoExecucaoHelper.getHoraMinutoInicioParaQuebra().equals(horaInicio) &&
					osAtividadePeriodoExecucaoHelper.getHoraMinutoFimParaQuebra().equals(horaFim)){
					
					
					//Informando OsAtividadePeriodoExecucao
					Collection colecaoOsExecucaoEquipeHelper = this.informarOsExecucaoEquipe(
					osAtividadePeriodoExecucaoHelper.getColecaoOSExecucaoEquipeHelper(), idEquipe, fachada);
					
					osAtividadePeriodoExecucaoHelper.setColecaoOSExecucaoEquipeHelper(colecaoOsExecucaoEquipeHelper);
					
					osAtividadePeriodoExecucaoInformada = true;
					break;
				}
			}
			
			/*
			 * Caso j� exista OSAtividadePeriodoExecucaoHelper informada, por�m ainda n�o foi cadastrada nenhuma 
			 * com a mesma data de execu��o e mesmo per�odo de execu��o
			 */
			if (!osAtividadePeriodoExecucaoInformada){
				
				//1� Passo - Gerando o objeto
				osAtividadePeriodoExecucaoHelper = this.gerarOsAtividadePeriodoExecucao(dataExecucao, 
				horaInicio, horaFim, idEquipe, fachada);
				
				//2� Passo - Adicionando o objeto na cole��o que foi recebida (J� foi criada e colocada na sess�o)
				colecaoOsAtividadePeriodoExecucaoHelper.add(osAtividadePeriodoExecucaoHelper);
			}
			
			return colecaoOsAtividadePeriodoExecucaoHelper;
		}
		else{
			
			//Primeira OsAtividadePeriodoExecucao informada
			
			//1� Passo - Gerando o objeto
			osAtividadePeriodoExecucaoHelper = this.gerarOsAtividadePeriodoExecucao(dataExecucao, 
			horaInicio, horaFim, idEquipe, fachada);
			
			//2� Passo - Adicionando o objeto em uma cole��o vazia
			colecaoRetorno = new ArrayList();
			colecaoRetorno.add(osAtividadePeriodoExecucaoHelper);
			
			return colecaoRetorno;
		}
	} 
	
	
	private OSAtividadePeriodoExecucaoHelper gerarOsAtividadePeriodoExecucao(String
			dataExecucao, String horaInicio, String horaFim, Integer idEquipe, Fachada fachada){
			
		OSAtividadePeriodoExecucaoHelper osAtividadePeriodoExecucaoHelper = 
		new OSAtividadePeriodoExecucaoHelper();
		
		OsAtividadePeriodoExecucao osAtividadePeriodoExecucao = new OsAtividadePeriodoExecucao();
		
		//Gerando uma String no formato dd/MM/yyyy HH:mm:ss
		String dataHoraExecucaoInicio = dataExecucao + " " + horaInicio + ":00";
		String dataHoraExecucaoFim = dataExecucao + " " + horaFim + ":00";
		
		osAtividadePeriodoExecucao.setDataInicio(Util.converteStringParaDateHora(dataHoraExecucaoInicio));
		osAtividadePeriodoExecucao.setDataFim(Util.converteStringParaDateHora(dataHoraExecucaoFim));
		
		//Objetos utilizados apenas para facilitar a quebra na exibi��o
		osAtividadePeriodoExecucaoHelper.setDataExecucaoParaQuebra(Util.converteStringParaDate(dataExecucao));
		osAtividadePeriodoExecucaoHelper.setHoraMinutoInicioParaQuebra(horaInicio);
		osAtividadePeriodoExecucaoHelper.setHoraMinutoFimParaQuebra(horaFim);
		
		osAtividadePeriodoExecucaoHelper.setOsAtividadePeriodoExecucao(osAtividadePeriodoExecucao);
		
		//Informando OsExecucaoEquipe
		Collection colecaoOSExecucaoEquipeHelper = this.informarOsExecucaoEquipe(null, idEquipe, fachada);
			
		osAtividadePeriodoExecucaoHelper.setColecaoOSExecucaoEquipeHelper(colecaoOSExecucaoEquipeHelper);
		
		return osAtividadePeriodoExecucaoHelper;
			
	}
	
	
	
	private Collection informarOsExecucaoEquipe(Collection colecaoOSExecucaoEquipeHelper, 
			Integer idEquipe, Fachada fachada){
		
		Collection colecaoRetorno = null;
		OSExecucaoEquipeHelper osExecucaoEquipeHelper = null;
		
		if (colecaoOSExecucaoEquipeHelper != null &&
			!colecaoOSExecucaoEquipeHelper.isEmpty()){
			
			Iterator iteratorColecaoOSExecucaoEquipeHelper = colecaoOSExecucaoEquipeHelper.iterator();
			
			while(iteratorColecaoOSExecucaoEquipeHelper.hasNext()){
				
				osExecucaoEquipeHelper = (OSExecucaoEquipeHelper)
				iteratorColecaoOSExecucaoEquipeHelper.next();
			
				/*
				 * Verifica se j� existe na cole��o uma OSExecucaoEquipeHelper com a mesma equipe
				 * 
				 *[FS002] - Verificar Exist�ncia de Dados
				 */
				if (osExecucaoEquipeHelper.getOsExecucaoEquipe().getEquipe().getId().equals(idEquipe)){
					
					throw new ActionServletException(
					"atencao.data_periodo_equipe_ja_informado");
				}
			}
			
			/*
			 * Caso j� exista OSExecucaoEquipeHelper informada, por�m ainda n�o foi cadastrada nenhuma 
			 * com a mesma equipe
			 */
			
			//1� Passo - Gerando o objeto
			osExecucaoEquipeHelper = this.gerarOsExecucaoEquipe(idEquipe, fachada);
				
			//2� Passo - Adicionando o objeto na cole��o que foi recebida (J� foi criada e colocada na sess�o)
			colecaoOSExecucaoEquipeHelper.add(osExecucaoEquipeHelper);
			
			return colecaoOSExecucaoEquipeHelper;
		}
		else{
			
			//Primeira OsExecucaoEquipe informada
			
			//1� Passo - Gerando o objeto
			osExecucaoEquipeHelper = this.gerarOsExecucaoEquipe(idEquipe, fachada);
			
			//2� Passo - Adicionando o objeto em uma cole��o vazia
			colecaoRetorno = new ArrayList();
			colecaoRetorno.add(osExecucaoEquipeHelper);
			
			return colecaoRetorno;
		}
	}
	
	
	private OSExecucaoEquipeHelper gerarOsExecucaoEquipe(Integer idEquipe, Fachada fachada){
			
		OSExecucaoEquipeHelper osExecucaoEquipeHelper = 
		new OSExecucaoEquipeHelper();
		
		FiltroEquipe filtroEquipe = new FiltroEquipe();
		
		filtroEquipe.adicionarParametro(new ParametroSimples(FiltroEquipe.ID, 
		idEquipe));
		
		Collection colecaoEquipe = fachada.pesquisar(filtroEquipe, Equipe.class.getName());
		
		Equipe equipe = (Equipe)Util.retonarObjetoDeColecao(colecaoEquipe);
		
		//Alterado para facilitar a identifica��o de uma equipe na cole��o
		equipe.setUltimaAlteracao(new Date());
		
		OsExecucaoEquipe osExecucaoEquipe = new OsExecucaoEquipe();
		osExecucaoEquipe.setEquipe(equipe);
		
		osExecucaoEquipeHelper.setOsExecucaoEquipe(osExecucaoEquipe);
		
		return osExecucaoEquipeHelper;
			
	}
	
	
	
	private void removerPeriodoExecucaoEquipe(long identificacaoEquipe, Integer idAtividade,Collection colecaoSessao){
		
		Iterator iteratorColecaoSessao = colecaoSessao.iterator();
		ManterDadosAtividadesOrdemServicoHelper manterDadosAtividadesOrdemServicoHelper = null;
		OSAtividadePeriodoExecucaoHelper osAtividadePeriodoExecucaoHelper = null;
		OSExecucaoEquipeHelper osExecucaoEquipeHelper = null;
		
		boolean removerSuperior = false;
		
		//Atividade
		while (iteratorColecaoSessao.hasNext()){
			
			manterDadosAtividadesOrdemServicoHelper = (ManterDadosAtividadesOrdemServicoHelper)
			iteratorColecaoSessao.next();
			
			if (manterDadosAtividadesOrdemServicoHelper.getOrdemServicoAtividade().getAtividade().getId()
				.equals(idAtividade)){
				
				//Per�odo
				Collection colecaoOSAtividadePeriodoExecucaoHelper = 
				manterDadosAtividadesOrdemServicoHelper.getColecaoOSAtividadePeriodoExecucaoHelper();
				
				Iterator iteratorColecaoOSAtividadePeriodoExecucaoHelper = 
				colecaoOSAtividadePeriodoExecucaoHelper.iterator();
				
				while (iteratorColecaoOSAtividadePeriodoExecucaoHelper.hasNext()){
					
					osAtividadePeriodoExecucaoHelper = (OSAtividadePeriodoExecucaoHelper)
					iteratorColecaoOSAtividadePeriodoExecucaoHelper.next();
					
					//Equipe
					Collection colecaoOSExecucaoEquipeHelper = osAtividadePeriodoExecucaoHelper
					.getColecaoOSExecucaoEquipeHelper();
					
					Iterator iteratorColecaoOSExecucaoEquipeHelper = colecaoOSExecucaoEquipeHelper.iterator();
					
					while (iteratorColecaoOSExecucaoEquipeHelper.hasNext()){
						
						osExecucaoEquipeHelper = (OSExecucaoEquipeHelper) iteratorColecaoOSExecucaoEquipeHelper.next();
					
						if ((GcomAction.obterTimestampIdObjeto(osExecucaoEquipeHelper.getOsExecucaoEquipe().getEquipe()))
							== identificacaoEquipe){
							
							colecaoOSExecucaoEquipeHelper.remove(osExecucaoEquipeHelper);
							
							if (colecaoOSExecucaoEquipeHelper.size() < 1){
								removerSuperior = true;
							}
							
							break;
						}
					}
					
					if (removerSuperior){
						colecaoOSAtividadePeriodoExecucaoHelper.remove(osAtividadePeriodoExecucaoHelper);
						
						if (colecaoOSAtividadePeriodoExecucaoHelper.size() < 1){
							removerSuperior = true;
						}
						else{
							removerSuperior = false;
						}
						
						break;
					}
				}
				
				if (removerSuperior){
					break;
				}
			}
		}
		
		if (removerSuperior){
			colecaoSessao.remove(manterDadosAtividadesOrdemServicoHelper);
		}
	}
}
