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
package gcom.gui.relatorio.faturamento.conta;

import gcom.batch.Processo;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesExecucaoRelatorios;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.faturamento.conta.RelatorioConta;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action respons�vel pela exibi��o do relat�rio de contas
 * 
 * @author Rafael Corr�a
 * @created 27/07/2009
 */
public class GerarRelatorioContaAction extends
		ExibidorProcessamentoTarefaRelatorio {

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param actionMapping
	 *            Descri��o do par�metro
	 * @param actionForm
	 *            Descri��o do par�metro
	 * @param httpServletRequest
	 *            Descri��o do par�metro
	 * @param httpServletResponse
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a vari�vel de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		GerarRelatorioContaActionForm form = (GerarRelatorioContaActionForm) actionForm;	
		
		Fachada fachada = Fachada.getInstancia();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		// Valida os par�metro passados como consulta
		boolean peloMenosUmParametroInformado = false;

		// Grupo
		Integer idGrupo = null;

		if (form.getIdGrupoFaturamento() != null
				&& !form.getIdGrupoFaturamento()
						.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;
			idGrupo = new Integer(form
					.getIdGrupoFaturamento());
			
			
		}
		
		// Rota
		Short codigoRotaInicial = null;
		Short codigoRotaFinal = null;
		Short sequencialRotaInicial = null;
		Short sequencialRotaFinal = null;

		if (form.getCodigoRotaInicial() != null
				&& !form.getCodigoRotaInicial().trim()
						.equals("")) {
			peloMenosUmParametroInformado = true;
			codigoRotaInicial = new Short(form
					.getCodigoRotaInicial());
			codigoRotaFinal = new Short(form
					.getCodigoRotaFinal());
			if (form.getSequencialRotaInicial() != null
					&& !form.getSequencialRotaInicial().trim()
							.equals("")) {
				peloMenosUmParametroInformado = true;
				sequencialRotaInicial = new Short(form
						.getSequencialRotaInicial());
				sequencialRotaFinal = new Short(form
						.getSequencialRotaFinal());
			}
		}

		// Localidade Inicial
		Localidade localidadeInicial = null;
		SetorComercial setorComercialInicial = null;

		String idLocalidadeInicial = form
				.getIdLocalidadeInicial();
		String codigoSetorComercialInicial = form
				.getCodigoSetorComercialInicial();

		if (idLocalidadeInicial != null && !idLocalidadeInicial.equals("")) {
			peloMenosUmParametroInformado = true;

			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidadeInicial));

			Collection colecaoLocalidades = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (colecaoLocalidades != null && !colecaoLocalidades.isEmpty()) {
				localidadeInicial = (Localidade) Util
						.retonarObjetoDeColecao(colecaoLocalidades);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Localidade Inicial");
			}
			
			// Setor Comercial Inicial
			if (codigoSetorComercialInicial != null && !codigoSetorComercialInicial.trim().equals("")) {
				
				FiltroSetorComercial filtro = new FiltroSetorComercial();
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercialInicial));
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, localidadeInicial.getId()));
				Collection pesquisa = (Collection) fachada.pesquisar(filtro, SetorComercial.class.getName());
				
				if (pesquisa != null && !pesquisa.isEmpty()) {
					setorComercialInicial = (SetorComercial) Util.retonarObjetoDeColecao(pesquisa);
				} else {
					throw new ActionServletException("atencao.pesquisa_inexistente", null, "Setor Comercial");
				}
			}
		}

		// Localidade Final
		Localidade localidadeFinal = null;
		SetorComercial setorComercialFinal = null;

		String idLocalidadeFinal = form
				.getIdLocalidadeFinal();
		String codigoSetorComercialFinal = form
				.getCodigoSetorComercialFinal();

		if (idLocalidadeFinal != null && !idLocalidadeFinal.equals("")) {
			peloMenosUmParametroInformado = true;

			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidadeFinal));

			Collection colecaoLocalidades = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (colecaoLocalidades != null && !colecaoLocalidades.isEmpty()) {
				localidadeFinal = (Localidade) Util
						.retonarObjetoDeColecao(colecaoLocalidades);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Localidade Final");
			}
			
			// Setor Comercial Final
			if (codigoSetorComercialFinal != null && !codigoSetorComercialFinal.trim().equals("")) {
				
				FiltroSetorComercial filtro = new FiltroSetorComercial();
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercialFinal));
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, localidadeFinal.getId()));
				Collection pesquisa = (Collection) fachada.pesquisar(filtro, SetorComercial.class.getName());
				
				if (pesquisa != null && !pesquisa.isEmpty()) {
					setorComercialFinal = (SetorComercial) Util.retonarObjetoDeColecao(pesquisa);
				} else {
					throw new ActionServletException("atencao.pesquisa_inexistente", null, "Setor Comercial");
				}
			}
		}
		

		// Refer�ncia
		Integer mesAno = null;

		if (form.getMesAno() != null && !form.getMesAno().equals("")) {
			mesAno = Util.formatarMesAnoComBarraParaAnoMes(form.getMesAno());
		}

		// Erro caso o usu�rio mandou filtrar sem nenhum par�metro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		// seta os parametros que ser�o mostrados no relat�rio

		// Fim da parte que vai mandar os parametros para o relat�rio
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		RelatorioConta relatorio = new RelatorioConta(
				(Usuario) (httpServletRequest.getSession(false))
						.getAttribute("usuarioLogado"));

		relatorio.addParametro("mesAno", mesAno);
		relatorio.addParametro("idGrupo", idGrupo);

		if (localidadeInicial != null) {
			relatorio.addParametro("idLocalidadeInicial",
					localidadeInicial.getId());
		}

		if (localidadeFinal != null) {
			relatorio.addParametro("idLocalidadeFinal",
					localidadeFinal.getId());
		}
		
		if (setorComercialInicial != null) {
			relatorio.addParametro("codigoSetorComercialInicial", setorComercialInicial.getCodigo());
		}
		
		if (setorComercialFinal != null) {
			relatorio.addParametro("codigoSetorComercialFinal", setorComercialFinal.getCodigo());
		}

		
		FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
		filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.ID, idGrupo));
		
		Collection colecaoFaturamentoGrupo = fachada.pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
		
		FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) colecaoFaturamentoGrupo.iterator().next();
		//Solicitado por Eduardo Borges
		//Caso o ano mes do grupo - 1 seja menor ou igual ao informado, n�o existem contas.
		if(Util.subtraiAteSeisMesesAnoMesReferencia(faturamentoGrupo.getAnoMesReferencia(), 1) < mesAno){
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
			
			
		relatorio.addParametro("codigoRotaInicial", codigoRotaInicial);
		relatorio.addParametro("codigoRotaFinal", codigoRotaFinal);
		
		relatorio.addParametro("sequencialRotaInicial", sequencialRotaInicial);
		relatorio.addParametro("sequencialRotaFinal", sequencialRotaFinal);
		
		relatorio.addParametro("tipoFormatoRelatorio",
				Integer.parseInt(tipoRelatorio));
		
		String indicadorEmissao = form.getIndicadorEmissao();
		String indicadorOrdenacao = form.getIndicadorOrdenacao();
		
		relatorio.addParametro("indicadorEmissao",indicadorEmissao);
		relatorio.addParametro("indicadorOrdenacao",indicadorOrdenacao);		
		
		boolean verficarRelatorioBatch = verificarRelatorioBatch(relatorio, fachada);
		
		if(verficarRelatorioBatch){
			
			Map parametros =  new HashMap();
			parametros.put("relatorio", relatorio);
			
			Fachada.getInstancia().inserirProcessoIniciadoParametrosLivres(parametros, 
         		Processo.GERAR_RELATORIO_CONTAS,
         		this.getUsuarioLogado(httpServletRequest));		
			
			 montarPaginaSucesso(httpServletRequest,
				"Gerar Contas Enviado para Processamento Batch", "Voltar",
				"/exibirGerarRelatorioContaAction.do");			
			
		}else{

			httpServletRequest.setAttribute("telaSucessoRelatorio",true);
			
			retorno = processarExibicaoRelatorio(relatorio,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);
			
			// devolve o mapeamento contido na vari�vel retorno
			
		}		
		
		return retorno;
	}
	
	private boolean verificarRelatorioBatch(RelatorioConta relatorio, Fachada fachada){
		
		boolean retorno = false;
		int quantidadeRegistroGerado = 0;
		
		quantidadeRegistroGerado = relatorio.calcularTotalRegistrosRelatorio();
		
		String nomeClasseRelatorio = relatorio.getClass().getSimpleName();
		
		int quantidadeMaximaOnLineRelatorio = ConstantesExecucaoRelatorios.get(nomeClasseRelatorio);
		
		if (quantidadeMaximaOnLineRelatorio == ConstantesExecucaoRelatorios.QUANTIDADE_NAO_INFORMADA
				|| quantidadeRegistroGerado > quantidadeMaximaOnLineRelatorio) {
		
			retorno = true;
		}			
		
		return retorno;
	}

}
