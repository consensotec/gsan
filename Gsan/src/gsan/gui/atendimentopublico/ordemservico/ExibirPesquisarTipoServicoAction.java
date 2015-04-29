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
package gsan.gui.atendimentopublico.ordemservico;

import gsan.atendimentopublico.ligacaoagua.FiltroDiametroLigacao;
import gsan.atendimentopublico.ordemservico.Atividade;
import gsan.atendimentopublico.ordemservico.FiltroAtividade;
import gsan.atendimentopublico.ordemservico.FiltroMaterialUnidade;
import gsan.atendimentopublico.ordemservico.FiltroPerfilServico;
import gsan.atendimentopublico.ordemservico.FiltroServicoTipoPrioridade;
import gsan.atendimentopublico.ordemservico.FiltroServicoTipoReferencia;
import gsan.atendimentopublico.ordemservico.FiltroServicoTipoSubgrupo;
import gsan.atendimentopublico.ordemservico.MaterialUnidade;
import gsan.atendimentopublico.ordemservico.ServicoPerfilTipo;
import gsan.atendimentopublico.ordemservico.ServicoTipoPrioridade;
import gsan.atendimentopublico.ordemservico.ServicoTipoReferencia;
import gsan.atendimentopublico.ordemservico.ServicoTipoSubgrupo;
import gsan.fachada.Fachada;
import gsan.faturamento.credito.CreditoTipo;
import gsan.faturamento.credito.FiltroCreditoTipo;
import gsan.faturamento.debito.DebitoTipo;
import gsan.faturamento.debito.FiltroDebitoTipo;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;
import gsan.util.tabelaauxiliar.abreviada.FiltroTabelaAuxiliarAbreviada;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Exibe a pesquisa de equipe
 * 
 * @author Leonardo Regis, Pedro Alexandre
 * @date 31/07/2006, 12/12/2007
 */
public class ExibirPesquisarTipoServicoAction extends GcomAction {

	/**
	 * Efetua pesquisa de Tipo de Servi�o
	 * 
	 * [UC0413] Pesquisar Tipo de Servi�o
	 * 
	 * 
	 * @author Leandro Cavalcanti
	 * @date 04/08/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		PesquisarTipoServicoActionForm pesquisarTipoServicoActionForm = (PesquisarTipoServicoActionForm) actionForm;
		ActionForward retorno = actionMapping.findForward("tipoServicoPesquisar");
		Fachada fachada = Fachada.getInstancia();
//		 Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);
		
		if(httpServletRequest.getParameter("limpar") != null){
			pesquisarTipoServicoActionForm.setDsTipoServico("");
			pesquisarTipoServicoActionForm.setTipoPesquisa(""+ConstantesSistema.TIPO_PESQUISA_INICIAL);
			pesquisarTipoServicoActionForm.setDsAbreviadaTipoServico("");
			pesquisarTipoServicoActionForm.setTipoPesquisaAbreviada(""+ConstantesSistema.TIPO_PESQUISA_INICIAL);
			pesquisarTipoServicoActionForm.setSubgrupoTipoServico("");
			//pesquisarTipoServicoActionForm.setIndicadorPavimento("3");
			
			pesquisarTipoServicoActionForm.setIndicadorPavimentoRua("3");
			pesquisarTipoServicoActionForm.setIndicadorPavimentoCalcada("3");
			
			pesquisarTipoServicoActionForm.setValorServicoInicial("");
			pesquisarTipoServicoActionForm.setValorServicoFinal("");
//			pesquisarTipoServicoActionForm.setIndicadorAtualizacaoComercio("3");
			pesquisarTipoServicoActionForm.setIndicadorAtualizacaoComercio("0");
			pesquisarTipoServicoActionForm.setIndicadorServicoTerceirizado("3");
			pesquisarTipoServicoActionForm.setCodigoServico("3");
			pesquisarTipoServicoActionForm.setTempoMedioExecucaoInicial("");
			pesquisarTipoServicoActionForm.setTempoMedioExecucaoFinal("");
			pesquisarTipoServicoActionForm.setTipoDebito("");
			pesquisarTipoServicoActionForm.setDsTipoDebito("");
			pesquisarTipoServicoActionForm.setTipoCredito("");
			pesquisarTipoServicoActionForm.setPrioridadeServico("");
			pesquisarTipoServicoActionForm.setPerfilServco("");
			pesquisarTipoServicoActionForm.setDsPerfilServco("");
			pesquisarTipoServicoActionForm.setTipoServicoReferencia("");
			pesquisarTipoServicoActionForm.setDsTipoServicoReferencia("");
			pesquisarTipoServicoActionForm.setAtividadesTipoServico("");
			pesquisarTipoServicoActionForm.setDsAtividadesTipoServico("");
			pesquisarTipoServicoActionForm.setArrayAtividade(new ArrayList());
			pesquisarTipoServicoActionForm.setArrayMateriais(new ArrayList());
			
			
		}
		
		// Resultado da Pesquisa pelo popup
		if (httpServletRequest.getParameter("tipoConsulta") != null
				&& !httpServletRequest.getParameter("tipoConsulta").equals("")) {
			
			// Perfil do Servico
			if (httpServletRequest.getParameter("tipoConsulta").equals("servicoPerfilTipo")) {
				pesquisarTipoServicoActionForm.setPerfilServco(httpServletRequest.getParameter("idCampoEnviarDados"));
				pesquisarTipoServicoActionForm.setDsPerfilServco(httpServletRequest.getParameter("descricaoCampoEnviarDados"));
			}
			// Tipo de Debito
			else if (httpServletRequest.getParameter("tipoConsulta").equals(
					"tipoDebito")) {
				pesquisarTipoServicoActionForm.setTipoDebito(httpServletRequest.getParameter("idCampoEnviarDados"));
				pesquisarTipoServicoActionForm.setDsTipoDebito(httpServletRequest.getParameter("descricaoCampoEnviarDados"));
			}
			// Tipo de Servi�o Refer�ncia
			else if (httpServletRequest.getParameter("tipoConsulta").equals("servicoTipoReferencia")) {
				pesquisarTipoServicoActionForm.setTipoServicoReferencia(httpServletRequest.getParameter("idCampoEnviarDados"));
				pesquisarTipoServicoActionForm.setDsTipoServicoReferencia(httpServletRequest.getParameter("descricaoCampoEnviarDados"));
			}
			// Atividade do tipo Servico
			else if (httpServletRequest.getParameter("tipoConsulta").equals("atividade")) {
				pesquisarTipoServicoActionForm.setAtividadesTipoServico(httpServletRequest.getParameter("idCampoEnviarDados"));
				pesquisarTipoServicoActionForm.setDsAtividadesTipoServico(httpServletRequest.getParameter("descricaoCampoEnviarDados"));
			}
			// Material do Tipo de Servico
			else if (httpServletRequest.getParameter("tipoConsulta").equals(
					"material")) {
				pesquisarTipoServicoActionForm.setTipoServicoMaterial(httpServletRequest.getParameter("idCampoEnviarDados"));
				pesquisarTipoServicoActionForm.setDsTipoServicoMaterial(httpServletRequest.getParameter("descricaoCampoEnviarDados"));
			}
		}
		
		// Inicio do c�digo para carregamento das campos 
		//Subgrupo do Tipo de Servico
		getSubgrupoTipoServico(httpServletRequest,pesquisarTipoServicoActionForm, fachada);
		//Tipo de Cr�dito
		getTipoCredito(httpServletRequest, pesquisarTipoServicoActionForm,fachada);
		//Prioridade Servi�o
		getPrioridadeServico(httpServletRequest,pesquisarTipoServicoActionForm, fachada);
		// Tipo de Tipo de D�bito
		getTipoDebito(pesquisarTipoServicoActionForm, fachada);
		// Tipo de Tipo de D�bito
		getTipoDebito(pesquisarTipoServicoActionForm, fachada);
		// Perfil do Servi�o
		getPerfilServico(pesquisarTipoServicoActionForm, fachada);
		// Tipo de Servi�o de Refer�ncia
		getTipoServicoReferencia(pesquisarTipoServicoActionForm, fachada);
		// Atividades do tipo de Servi�o
		getAtividadesTipoServico(pesquisarTipoServicoActionForm, fachada);
		// Tipo de Material
		getTipoServicoMaterial(pesquisarTipoServicoActionForm, fachada);
		
		// Remove componete na cole��o Atividade
		if ("removeServicoTipoAtividade".equals(pesquisarTipoServicoActionForm
				.getMethod())) {
				Integer id = new Integer(pesquisarTipoServicoActionForm.getTipoServicoAtividadeId());
			if (id!= null){
				pesquisarTipoServicoActionForm.removeServicoTipoAtividade(id,pesquisarTipoServicoActionForm.getAtividades(),pesquisarTipoServicoActionForm.getArrayAtividade());
				pesquisarTipoServicoActionForm.setMethod("");
			}
		}
		// Remove componete na cole��o Material
		if ("removeServicoTipoMaterial".equals(pesquisarTipoServicoActionForm
				.getMethod())) {
			Integer id = new Integer(pesquisarTipoServicoActionForm.getTipoServicoMaterialId());
			if (id!= null){
				pesquisarTipoServicoActionForm.removeServicoTipoMateriais(id,pesquisarTipoServicoActionForm.getMateriais(),pesquisarTipoServicoActionForm.getArrayMateriais());
				
				pesquisarTipoServicoActionForm.setMethod("");
			}
		}
		
		if(httpServletRequest.getParameter("caminhoRetornoTelaPesquisaTipoServico") != null){
			sessao.setAttribute("caminhoRetornoTelaPesquisaTipoServico", httpServletRequest.getParameter("caminhoRetornoTelaPesquisaTipoServico"));
		}
		if(httpServletRequest.getParameter("tipo") != null){
			sessao.setAttribute("tipo", httpServletRequest.getParameter("tipo"));
		}
		// Inicio da Valida��o dos Campos 
		// Tempo Medio
		//this.isValidarTempoMedio(pesquisarTipoServicoActionForm);
		// Valor do Tipo de Servi�o
		//this.isValidarValorTipoServico(pesquisarTipoServicoActionForm);

		return retorno;
	}

	/**
	 * Seta novo Componente na Cole��o
	 * 
	 * @author Leandro Cavlcanti
	 * @date 10/08/2006
	 * 
	 * @param inserirAtividadeActionForm
	 * @param atividades
	 */
	private void setColecaoAtividades(
			PesquisarTipoServicoActionForm pesquisarTipoServicoActionForm,
			Atividade atividade) {
		if(!pesquisarTipoServicoActionForm.getAtividades().contains(atividade)){
			pesquisarTipoServicoActionForm.getArrayAtividade().add(atividade);
			pesquisarTipoServicoActionForm.getAtividades().add(atividade.getId());
		}
	}

	/**
	 * Seta novo Componente na Cole��o
	 * 
	 * @author Leandro Cavlcanti
	 * @date 10/08/2006
	 * 
	 * @param inserirMateriaisActionForm
	 * @param materiais
	 */
	private void setColecaoMateriais(
			PesquisarTipoServicoActionForm pesquisarTipoServicoActionForm,
			MaterialUnidade servicoTipoMaterial) {
		if(!pesquisarTipoServicoActionForm.getMateriais().contains(servicoTipoMaterial)){
			pesquisarTipoServicoActionForm.getArrayMateriais().add(servicoTipoMaterial);
			pesquisarTipoServicoActionForm.getMateriais().add(servicoTipoMaterial.getId());
		}
	}

	/**
	 * Recupera Subgrupo do Tipo de Servi�o
	 * 
	 * @author Leondro Cavalcanti
	 * @date 03/08/2006
	 * 
	 * @param pesquisarEquipeActionForm
	 * @param equipeComponentes
	 * 
	 */

	private void getSubgrupoTipoServico(HttpServletRequest httpServletRequest,
			PesquisarTipoServicoActionForm pesquisarTipoServicoActionForm,
			Fachada fachada) {
		HttpSession sessao = httpServletRequest.getSession(false);
		FiltroServicoTipoSubgrupo filtroServicoTipoSubgrupo = new FiltroServicoTipoSubgrupo();
		filtroServicoTipoSubgrupo.adicionarParametro(new ParametroSimples(FiltroServicoTipoSubgrupo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		// Recupera
		Collection colecaoServicoTipoSubgrupo = fachada.pesquisar(filtroServicoTipoSubgrupo, ServicoTipoSubgrupo.class.getName());
		
		if (colecaoServicoTipoSubgrupo != null
				&& !colecaoServicoTipoSubgrupo.isEmpty()) {
			sessao.setAttribute("colecaoServicoTipoSubgrupo",colecaoServicoTipoSubgrupo);
		}else{
			//pesquisarTipoServicoActionForm.setSubgrupoTipoServico("Subgrupo do Tipo Servi�o Inexistente!");
			pesquisarTipoServicoActionForm.setSubgrupoTipoServico("");
		}

	}


	/**
	 * Recupera Tipo de D�bito [FS0003] Validar tipo de D�bito
	 * 
	 * @author Leondro Cavalcanti
	 * @date 03/08/2006
	 * 
	 * @param pesquisarEquipeActionForm
	 * @param equipeComponentes
	 * 
	 */

	private void getTipoDebito(PesquisarTipoServicoActionForm pesquisarTipoServicoActionForm,
			Fachada fachada) {
		String idTipoDebito = pesquisarTipoServicoActionForm.getTipoDebito();

		if (idTipoDebito != null && !idTipoDebito.trim().equals("")) {

			// Filtro para obter o Equipamento Especial do id informado
			FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();

			filtroDebitoTipo.adicionarParametro(new ParametroSimples(
					FiltroTabelaAuxiliarAbreviada.ID, idTipoDebito));
			filtroDebitoTipo.adicionarParametro(new ParametroSimples(
					FiltroTabelaAuxiliarAbreviada.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Pesquisa de acordo com os par�metros informados no filtro
			Collection colecaoDebitoTipo = Fachada.getInstancia().pesquisar(
					filtroDebitoTipo, DebitoTipo.class.getName());

			// Verifica se a pesquisa retornou algum objeto para a cole��o
			if (colecaoDebitoTipo != null && !colecaoDebitoTipo.isEmpty()) {

				DebitoTipo debitoTipo = (DebitoTipo) Util
						.retonarObjetoDeColecao(colecaoDebitoTipo);

				pesquisarTipoServicoActionForm.setTipoDebito(debitoTipo.getId()
						.toString());
				pesquisarTipoServicoActionForm.setDsTipoDebito(debitoTipo
						.getDescricao());

			} else {
				// [FS0003] Validar tipo de D�bito
				pesquisarTipoServicoActionForm.setTipoDebito("");
				pesquisarTipoServicoActionForm
						.setDsTipoDebito("Tipo de D�bito Inexistente!");

			}
		}
	}

	/**
	 * Recupera Tipo de Credito
	 * 
	 * @author Leondro Cavalcanti
	 * @date 03/08/2006
	 * 
	 * @param pesquisarEquipeActionForm
	 * @param equipeComponentes
	 * 
	 */

	private void getTipoCredito(HttpServletRequest httpServletRequest,
			PesquisarTipoServicoActionForm pesquisarTipoServicoActionForm,
			Fachada fachada) {
		HttpSession sessao = httpServletRequest.getSession(false);
		// Filtra
		FiltroCreditoTipo filtroCreditoTipo = new FiltroCreditoTipo();
		filtroCreditoTipo.adicionarParametro(new ParametroSimples(
				FiltroDiametroLigacao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO.toString()));

		// Recupera
		Collection colecaoTipoCredito = fachada.pesquisar(filtroCreditoTipo,
				CreditoTipo.class.getName());
		if (colecaoTipoCredito != null && !colecaoTipoCredito.isEmpty()) {
			sessao.setAttribute("colecaoTipoCredito", colecaoTipoCredito);
		}

	}

	/**
	 * Recupera Prioridade Do Servi�o
	 * 
	 * @author Leondro Cavalcanti
	 * @date 03/08/2006
	 * 
	 * @param PesquisarTipoServicoActionForm
	 * @param fachada
	 * 
	 */

	private void getPrioridadeServico(HttpServletRequest httpServletRequest,
			PesquisarTipoServicoActionForm pesquisarTipoServicoActionForm,
			Fachada fachada) {
		HttpSession sessao = httpServletRequest.getSession(false);
		// Filtra
		FiltroServicoTipoPrioridade filtroServicoTipoPrioridade = new FiltroServicoTipoPrioridade();

		filtroServicoTipoPrioridade.adicionarParametro(new ParametroSimples(
				FiltroDiametroLigacao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		// Recupera
		Collection colecaoPrioridadeServico = fachada.pesquisar(
				filtroServicoTipoPrioridade, ServicoTipoPrioridade.class
						.getName());
		if (colecaoPrioridadeServico != null
				&& !colecaoPrioridadeServico.isEmpty()) {
			sessao.setAttribute("colecaoPrioridadeServico",
					colecaoPrioridadeServico);
		}

	}

	/**
	 * Recupera Perfil do Servi�o [FS0005] Validar Perfil do Servico
	 * 
	 * @author Leondro Cavalcanti
	 * @date 03/08/2006
	 * 
	 * @param pesquisarTipoServicoActionForm
	 * @param fachada
	 * 
	 */

	private void getPerfilServico(
			PesquisarTipoServicoActionForm pesquisarTipoServicoActionForm,
			Fachada fachada) {

		String idPerfilServico = pesquisarTipoServicoActionForm
				.getPerfilServco();

		if (idPerfilServico != null && !idPerfilServico.trim().equals("")) {

			// Filtro para obter o Equipamento Especial do id informado
			FiltroPerfilServico filtroPerfilServico = new FiltroPerfilServico();

			filtroPerfilServico.adicionarParametro(new ParametroSimples(
					FiltroTabelaAuxiliarAbreviada.ID, idPerfilServico));
			filtroPerfilServico.adicionarParametro(new ParametroSimples(
					FiltroTabelaAuxiliarAbreviada.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Pesquisa de acordo com os par�metros informados no filtro
			Collection colecaoServicoPerfilTipo = Fachada.getInstancia()
					.pesquisar(filtroPerfilServico,
							ServicoPerfilTipo.class.getName());

			// Verifica se a pesquisa retornou algum objeto para a cole��o
			if (colecaoServicoPerfilTipo != null
					&& !colecaoServicoPerfilTipo.isEmpty()) {

				ServicoPerfilTipo servicoPerfilTipo = (ServicoPerfilTipo) Util
						.retonarObjetoDeColecao(colecaoServicoPerfilTipo);
				pesquisarTipoServicoActionForm
						.setPerfilServco(servicoPerfilTipo.getId().toString());
				pesquisarTipoServicoActionForm
						.setDsPerfilServco(servicoPerfilTipo.getDescricao());
				pesquisarTipoServicoActionForm.setDsPerfilServicoCor("#000000");
			} else {
				// [FS0005] Validar Perfil do Servico
				pesquisarTipoServicoActionForm.setPerfilServco("");
				pesquisarTipoServicoActionForm
						.setDsPerfilServco("Perfil do Servi�o Inexistente");
				pesquisarTipoServicoActionForm.setDsPerfilServicoCor("#FF0000");

			}

		}

	}

	/**
	 * Validar atividade [FS0006] Validar atividade
	 * 
	 * @author Leondro Cavalcanti
	 * @date 03/08/2006
	 * 
	 * @param pesquisarTipoServicoActionForm
	 * 
	 * 
	 */

	private void getAtividadesTipoServico(
			PesquisarTipoServicoActionForm pesquisarTipoServicoActionForm,
			Fachada fachada) {

		String idAtividadesTipoServico = pesquisarTipoServicoActionForm
				.getAtividadesTipoServico();

		if (idAtividadesTipoServico != null
				&& !idAtividadesTipoServico.trim().equals("")) {

			// Filtro para obter o Equipamento Especial do id informado
			FiltroAtividade filtroAtividade = new FiltroAtividade();

			filtroAtividade.adicionarParametro(new ParametroSimples(
					FiltroAtividade.ID, idAtividadesTipoServico));
			
			// Pesquisa de acordo com os par�metros informados no filtro

			Collection colecaoServicoTipoAtividade = Fachada.getInstancia()
					.pesquisar(filtroAtividade, Atividade.class.getName());

			// Verifica se a pesquisa retornou algum objeto para a cole��o
			if (colecaoServicoTipoAtividade != null
					&& !colecaoServicoTipoAtividade.isEmpty()) {

				Atividade servicoTipoAtividade = (Atividade) Util
						.retonarObjetoDeColecao(colecaoServicoTipoAtividade);

				if ((servicoTipoAtividade != null && !servicoTipoAtividade
						.equals(""))) {
					pesquisarTipoServicoActionForm
							.setAtividadesTipoServico(servicoTipoAtividade
									.getId().toString());
					pesquisarTipoServicoActionForm
							.setDsAtividadesTipoServico(servicoTipoAtividade
									.getDescricao());

					// add aividades na cole��o
					setColecaoAtividades(pesquisarTipoServicoActionForm,
							servicoTipoAtividade);
					
					pesquisarTipoServicoActionForm.setAtividadesTipoServico("");
					pesquisarTipoServicoActionForm
							.setDsAtividadesTipoServico("");
					pesquisarTipoServicoActionForm.setDsAtividadeCor("#000000");
				}
				
			} else {
				// [FS0006] Validar atividade
				pesquisarTipoServicoActionForm.setAtividadesTipoServico("");
				pesquisarTipoServicoActionForm
						.setDsAtividadesTipoServico("Atividada Inexistente!");
				pesquisarTipoServicoActionForm.setDsAtividadeCor("#FF0000");

			}
		}
	}

	/**
	 * Recupera Tipo de Servico Refer�ncia [FS0004] Validar Tipo de Servico
	 * Refer�ncia
	 * 
	 * @author Leondro Cavalcanti
	 * @date 03/08/2006
	 * 
	 * @param pesquisarTipoServicoActionForm
	 * 
	 * 
	 */

	private void getTipoServicoReferencia(
			PesquisarTipoServicoActionForm pesquisarTipoServicoActionForm,
			Fachada fachada) {

		String idTipoServicoReferencia = pesquisarTipoServicoActionForm
				.getTipoServicoReferencia();

		if (idTipoServicoReferencia != null
				&& !idTipoServicoReferencia.trim().equals("")) {

			// Filtro para obter o Equipamento Especial do id informado
			FiltroServicoTipoReferencia filtroServicoTipoReferencia = new FiltroServicoTipoReferencia();

			filtroServicoTipoReferencia
					.adicionarParametro(new ParametroSimples(
							FiltroTabelaAuxiliarAbreviada.ID,
							idTipoServicoReferencia));

			// Pesquisa de acordo com os par�metros informados no filtro
			Collection colecaoServicoTipoReferencia = Fachada.getInstancia()
					.pesquisar(filtroServicoTipoReferencia,
							ServicoTipoReferencia.class.getName());

			// Verifica se a pesquisa retornou algum objeto para a cole��o
			if (colecaoServicoTipoReferencia != null
					&& !colecaoServicoTipoReferencia.isEmpty()) {

				ServicoTipoReferencia servicoTipoReferencia = (ServicoTipoReferencia) Util
						.retonarObjetoDeColecao(colecaoServicoTipoReferencia);
				
				pesquisarTipoServicoActionForm
						.setTipoServicoReferencia(servicoTipoReferencia.getId()
								.toString());
				pesquisarTipoServicoActionForm
						.setDsTipoServicoReferencia(servicoTipoReferencia
								.getDescricao());
				pesquisarTipoServicoActionForm.setDsTipoServicoReferenciaCor("#000000");
			} else {
				// [FS0004] Validar tipo de Servico Refer�ncia
				pesquisarTipoServicoActionForm.setTipoServicoReferencia("");
				pesquisarTipoServicoActionForm
						.setDsTipoServicoReferencia("Tipo de Servi�o Refer�ncia inexistente");
				pesquisarTipoServicoActionForm.setDsTipoServicoReferenciaCor("#FF0000");

			}
		}
	}

	/**
	 * Validar Valor Tipo de Servico [FS0001] - Validar Valor Tipo de Servico
	 * 
	 * @author Leondro Cavalcanti
	 * @date 03/08/2006
	 * 
	 * @param pesquisarEquipeActionForm
	 * @param equipeComponentes
	 * 
	 */


	/*private void isValidarValorTipoServico(
			PesquisarTipoServicoActionForm pesquisarTipoServicoActionForm) {

		Integer valorTipoServicoInicial = new Integer(
				pesquisarTipoServicoActionForm.getValorServicoInicial());
		Integer valorTipoServicoFinal = new Integer(
				pesquisarTipoServicoActionForm.getValorServicoFinal());

		if (valorTipoServicoInicial != null
				&& !valorTipoServicoInicial.equals("")
				&& valorTipoServicoFinal != null
				&& !valorTipoServicoFinal.equals("")) {
			if (valorTipoServicoFinal.intValue() < valorTipoServicoInicial
					.intValue()) {
				throw new ActionServletException(
						"atencao.situacao.valor.servico.invalida", null, "");
			}
		}
	}*/

	/**
	 * Validar Tempo M�dio informado [FS0001] - Validar Tempo M�dio
	 * 
	 * @author Leondro Cavalcanti
	 * @date 04/08/2006
	 * 
	 * @param pesquisarTipoServicoActionForm
	 * 
	 * 
	 */


	/*private void isValidarTempoMedio(
			PesquisarTipoServicoActionForm pesquisarTipoServicoActionForm) {

		Integer valorTempoMedioInicial = new Integer(
				pesquisarTipoServicoActionForm.getTempoMedioExecucaoInicial());
		Integer valorTempoMedioFinal = new Integer(
				pesquisarTipoServicoActionForm.getTempoMedioExecucaoFinal());

		if (valorTempoMedioInicial != null
				&& !valorTempoMedioInicial.equals("")
				&& valorTempoMedioFinal != null
				&& !valorTempoMedioFinal.equals("")) {
			if (valorTempoMedioFinal.intValue() < valorTempoMedioInicial
					.intValue()) {
				throw new ActionServletException(
						"atencao.situacao.tempo.execucao.invalida", null, "");
			}
		}
	}*/

	/**
	 * Recupera Materiais do Tipo de Servico [FS0007] Validar Material
	 * 
	 * @author Leondro Cavalcanti
	 * @date 03/08/2006
	 * 
	 * @param pesquisarTipoServicoActionForm
	 * 
	 * 
	 */

	private void getTipoServicoMaterial(
			PesquisarTipoServicoActionForm pesquisarTipoServicoActionForm,
			Fachada fachada) {
		
		String idTipoServicoMaterial = pesquisarTipoServicoActionForm
				.getTipoServicoMaterial();

		if (idTipoServicoMaterial != null
				&& !idTipoServicoMaterial.trim().equals("")) {

			// Filtro para obter o Equipamento Especial do id informado
			FiltroMaterialUnidade filtroMaterialUnidade = new FiltroMaterialUnidade();

			filtroMaterialUnidade.adicionarParametro(new ParametroSimples(
					FiltroMaterialUnidade.ID, idTipoServicoMaterial));
			
			// Pesquisa de acordo com os par�metros informados no filtro

			Collection colecaoServicoTipoMaterial = Fachada.getInstancia()
					.pesquisar(filtroMaterialUnidade,
							MaterialUnidade.class.getName());

			// Verifica se a pesquisa retornou algum objeto para a cole��o
			if (colecaoServicoTipoMaterial != null
					&& !colecaoServicoTipoMaterial.isEmpty()) {

				MaterialUnidade servicoTipoMaterial = (MaterialUnidade) Util
						.retonarObjetoDeColecao(colecaoServicoTipoMaterial);
				if ((servicoTipoMaterial != null && !servicoTipoMaterial
						.equals(""))) {
					pesquisarTipoServicoActionForm.setTipoServicoMaterial(servicoTipoMaterial.getId().toString());
					pesquisarTipoServicoActionForm.setDsTipoServicoMaterial(servicoTipoMaterial.getDescricao());
					
					// add componente material da cole��o
					setColecaoMateriais(pesquisarTipoServicoActionForm,servicoTipoMaterial);
					//	sessao.setAttribute("materiais",servicoTipoMaterial );
					}
					pesquisarTipoServicoActionForm.setTipoServicoMaterial("");
					pesquisarTipoServicoActionForm.setDsTipoServicoMaterial("");
					pesquisarTipoServicoActionForm.setDsMaterialCor("#000000");
				}

			 else {
				//[FS0004] Validar tipo de Servico Material
				pesquisarTipoServicoActionForm.setTipoServicoMaterial("");
				pesquisarTipoServicoActionForm.setDsTipoServicoMaterial("Material do Tipo de Servi�o Inexistente");
				pesquisarTipoServicoActionForm.setDsMaterialCor("#FF0000");
			}
		}
	}
}