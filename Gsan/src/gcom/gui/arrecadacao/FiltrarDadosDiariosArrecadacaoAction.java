
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

import gcom.arrecadacao.FiltroConsultarDadosDiariosArrecadacao;
import gcom.arrecadacao.FiltroConsultarDadosDiariosArrecadacao.GROUP_BY;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarDadosDiariosArrecadacaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = null;

		Fachada fachada = Fachada.getInstancia();
		
        /** filtro para verificar se a funcionalidade de gerar dados di�rios de arrecada��o esta executando */
		
        fachada.verificarExecucaoFuncionalidade(Funcionalidade.GERAR_DADOS_DIARIOS_ARRECADACAO);

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		// Pega o formul�rio
		FiltrarDadosDiariosArrecadacaoActionForm filtrarDadosDiariosArrecadacaoActionForm = (FiltrarDadosDiariosArrecadacaoActionForm) actionForm;

		// Recupera os par�metros do form
		String periodoArrecadacaoInicial = filtrarDadosDiariosArrecadacaoActionForm.getPeriodoArrecadacaoInicio();
		String periodoArrecadacaoFinal = filtrarDadosDiariosArrecadacaoActionForm.getPeriodoArrecadacaoFim();
		String localidade = filtrarDadosDiariosArrecadacaoActionForm.getLocalidade();
		String idArrecadador = filtrarDadosDiariosArrecadacaoActionForm.getIdArrecadador();
		String idGerenciaRegional = filtrarDadosDiariosArrecadacaoActionForm.getIdGerenciaRegional();
		String idElo = filtrarDadosDiariosArrecadacaoActionForm.getIdElo();
		String[] idsImovelPerfil = filtrarDadosDiariosArrecadacaoActionForm.getImovelPerfil();
		String[] idsLigacaoAgua = filtrarDadosDiariosArrecadacaoActionForm.getLigacaoAgua();
		String[] idsLigacaoEsgoto = filtrarDadosDiariosArrecadacaoActionForm.getLigacaoEsgoto();
		String[] idsCategoria = filtrarDadosDiariosArrecadacaoActionForm.getCategoria();
		String[] idsEsferaPoder = filtrarDadosDiariosArrecadacaoActionForm.getEsferaPoder();
		String[] idsDocumentosTipos = filtrarDadosDiariosArrecadacaoActionForm.getDocumentoTipo();

		retorno = actionMapping.findForward("consultarDadosDiariosParametros");
		
        // Monta o Status do Wizard
		StatusWizard statusWizard = new StatusWizard(
				"consultarDadosDiariosArrecadacaoWizardAction", "exibirFiltrarDadosDiariosArrecadacaoAction",
				"cancelarConsultarDadosDiariosArrecadacaoAction", 
				"exibirFiltrarDadosDiariosArrecadacaoAction",	
				"filtrarDadosDiariosArrecadacaoAction.do");

		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						1, "ParametrosPrimeiraAbaA.gif", "ParametrosPrimeiraAbaD.gif",
						"exibirConsultarDadosDiariosParametrosAction",
						""));
		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						2, "GerenciaIntervaloAbaA.gif", "GerenciaIntervaloAbaD.gif",
						"exibirConsultarDadosDiariosGerenciaAction",
						""));
		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						3, "ArrecadadorIntervaloAbaA.gif", "ArrecadadorIntervaloAbaD.gif",
						"exibirConsultarDadosDiariosArrecadadorAction",
						""));
		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						4, "CategoriaIntervaloAbaA.gif", "CategoriaIntervaloAbaD.gif",
						"exibirConsultarDadosDiariosCategoriaAction",
						""));
		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						5, "PerfilIntervaloAbaA.gif", "PerfilIntervaloAbaD.gif",
						"exibirConsultarDadosDiariosPerfilAction",
						""));

		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						6, "DocumentoUltimaAbaA.gif", "DocumentoUltimaAbaD.gif",
						"exibirConsultarDadosDiariosDocumentoAction",
						""));
        
//		colecaoArrecadacaoDadosDiarios = fachada.filtrarDadosDiariosArrecadacao(Util
//				.formatarMesAnoParaAnoMesSemBarra(periodoArrecadacaoInicial),
//				Util
//				.formatarMesAnoParaAnoMesSemBarra(periodoArrecadacaoFinal),
//				localidade,
//				idGerenciaRegional,
//				idArrecadador,
//				idElo,
//				idsImovelPerfil,
//				idsLigacaoAgua,
//				idsLigacaoEsgoto,
//				idsDocumentosTipos,
//				idsCategoria,
//				idsEsferaPoder);
		
		boolean peloMenosUmParametroInformado = false;

		// Per�odo Arrecada��o
		if (periodoArrecadacaoInicial != null
				&& !periodoArrecadacaoInicial.equals("")) {
			peloMenosUmParametroInformado = true;

		}

		// Localidade
		if (localidade != null
				&& !localidade.equals("") && !localidade
				.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;

		}
		else{
			filtrarDadosDiariosArrecadacaoActionForm.setLocalidade("");
			filtrarDadosDiariosArrecadacaoActionForm.setDescricaoLocalidade("");
		}
		
		// Gerencia Regional
		if (idGerenciaRegional != null
				&& !idGerenciaRegional.equals("") && (!(idGerenciaRegional
				.equals(new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())))
				
				) {
			peloMenosUmParametroInformado = true;

		}
		else{
			filtrarDadosDiariosArrecadacaoActionForm.setIdGerenciaRegional("");
			filtrarDadosDiariosArrecadacaoActionForm.setNomeGerenciaRegional("");
		}

		// Arrecadador
		if (idArrecadador != null
				&& !idArrecadador.equals("") && !idArrecadador
				.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;

		}
		else{
			filtrarDadosDiariosArrecadacaoActionForm.setIdArrecadador("");
			filtrarDadosDiariosArrecadacaoActionForm.setNomeArrecadador("");
		}

		
		// Elo
		if (idElo != null
				&& !idElo.equals("") && !idElo
				.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;

		}
		else{
			filtrarDadosDiariosArrecadacaoActionForm.setIdElo("");
			filtrarDadosDiariosArrecadacaoActionForm.setNomeElo("");
		}

		
		// Imovel Perfil
		int i = 0;
		if (idsImovelPerfil != null) {
			while (i < idsImovelPerfil.length) {
				if (!idsImovelPerfil[i].equals("")) {
					peloMenosUmParametroInformado = true;
				}
				i++;
			}
		}
		
		// Situa��o Liga��o �gua 
		i = 0;
		if (idsLigacaoAgua != null) {
			while (i < idsLigacaoAgua.length) {
				if (!idsLigacaoAgua[i].equals("")) {
					peloMenosUmParametroInformado = true;
				}
				i++;
			}
		}

		// Situa��o Liga��o Esgoto 
		i = 0;
		if (idsLigacaoEsgoto != null) {
			while (i < idsLigacaoEsgoto.length) {
				if (!idsLigacaoEsgoto[i].equals("")) {
					peloMenosUmParametroInformado = true;
				}
				i++;
			}
		}
		
		// Tipo do Documento
		i = 0;
		if (idsDocumentosTipos != null) {
			while (i < idsDocumentosTipos.length) {
				if (!idsDocumentosTipos[i].equals("")) {
//					peloMenosUmParametroInformado = true;
				}
				i++;
			}
		}

		// Categoria
		i = 0;
		if (idsCategoria != null) {
			while (i < idsCategoria.length) {
				if (!idsCategoria[i].equals("")) {
//					peloMenosUmParametroInformado = true;					
				}
				i++;
			}
		}

		// Esfera Poder
		i = 0;
		if (idsEsferaPoder != null) {
			while (i < idsEsferaPoder.length) {
				if (!idsEsferaPoder[i].equals("")) {
//					peloMenosUmParametroInformado = true;
				}
				i++;
			}
		}
		
		// Erro caso o usu�rio mandou filtrar sem nenhum par�metro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		FiltroConsultarDadosDiariosArrecadacao filtro = new FiltroConsultarDadosDiariosArrecadacao();
		filtro.setAgrupamento(GROUP_BY.ANO_MES);
		filtro.setIdArrecadador(idArrecadador);
		filtro.setIdElo(idElo);
		filtro.setIdGerenciaRegional(idGerenciaRegional);
		filtro.setIdLocalidade(localidade);
		filtro.setIdsCategoria(idsCategoria);
		filtro.setIdsDocumentoTipoAgregador(idsDocumentosTipos);
		filtro.setIdsEsferaPoder(idsEsferaPoder);
		filtro.setIdsImovelPerfil(idsImovelPerfil);
		filtro.setIdsSituacaoLigacaoAgua(idsLigacaoAgua);
		filtro.setIdsSituacaoLigacaoEsgoto(idsLigacaoEsgoto);
				
		boolean existeDados =
			fachada.verificarExistenciaDadosDiariosArrecadacao(
					Util.formatarMesAnoComBarraParaAnoMes(periodoArrecadacaoInicial),
					Util.formatarMesAnoComBarraParaAnoMes(periodoArrecadacaoFinal), 
					filtro);

		
		// [FS0009] Verifica a exist�ncia de Dados diarios de arrecadacao
		if (!existeDados) {
			// Nenhum dados diarios de arrecadacao cadastrado
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		sessao.setAttribute("filtroConsultarDadosDiariosArrecadacao", filtro);
		sessao.setAttribute("periodoArrecadacaoInicial", 
			Util.formatarMesAnoComBarraParaAnoMes(periodoArrecadacaoInicial));
		sessao.setAttribute("periodoArrecadacaoFinal", 
				Util.formatarMesAnoComBarraParaAnoMes(periodoArrecadacaoFinal));
//			sessao.setAttribute("colecaoArrecadacaoDadosDiarios",
//					colecaoArrecadacaoDadosDiarios);
		
        //manda o statusWizard para a sess�o
        sessao.setAttribute("statusWizard", statusWizard);
        
		// Devolve o mapeamento de retorno
		return retorno;
	}
}
