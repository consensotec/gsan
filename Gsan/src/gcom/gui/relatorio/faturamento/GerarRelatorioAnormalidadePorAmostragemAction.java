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
package gcom.gui.relatorio.faturamento;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.faturamento.GerarRelatorioAnormalidadePorAmostragemActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.faturamento.RelatorioAnormalidadePorAmostragem;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * Este caso de uso gera relatorio de anormalidade Informadas - Amostragem
 * 
 * [UC1051] ? Gerar Relat�rio de Amostragem das Anormalidades Informadas
 * 
 * @author Hugo Leonardo
 * @date 06/08/2010
 * 
 */
public class GerarRelatorioAnormalidadePorAmostragemAction extends
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
		ActionForward retorno = null;

		GerarRelatorioAnormalidadePorAmostragemActionForm form = (GerarRelatorioAnormalidadePorAmostragemActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Valida os par�metro passados como consulta
		boolean peloMenosUmParametroInformado = false;

		// Grupo
		Integer idGrupo = null;
		if (form.getGrupo() != null
				&& !form.getGrupo().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			peloMenosUmParametroInformado = true;
			idGrupo = new Integer(form.getGrupo());
		}
		
		// Rota
		Short cdRota = null;
		if (form.getRota() != null && !form.getRota().trim().equals("")) {
			
			peloMenosUmParametroInformado = true;
			cdRota = new Short(form.getRota());
		}

		// Ger�ncia Regional
		Integer idGerenciaRegional = null;
		if (form.getRegional() != null
				&& !form.getRegional()
						.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;
			idGerenciaRegional = new Integer(
					form.getRegional());
		}

		// Unidade de Neg�cio
		Integer idUnidadeNegocio = null;
		if (form.getUnidadeNegocio() != null
				&& !form.getUnidadeNegocio().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			peloMenosUmParametroInformado = true;
			idUnidadeNegocio = new Integer(form.getUnidadeNegocio());
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
			
			pesquisarQuadra(form,fachada,httpServletRequest);
			validacaoFinal(form);	
		}
		
		Collection<Integer> colecaoIdsEmpresa = null;
		if(form.getColecaoIdsEmpresa() != null){
			colecaoIdsEmpresa = new ArrayList<Integer>();
			
			for (String id : form.getColecaoIdsEmpresa()) {
				if (!id.equals("-1")) {
					colecaoIdsEmpresa.add(new Integer(id));
				}
			}
			
			if (colecaoIdsEmpresa.size() > 0) {
				peloMenosUmParametroInformado = true;
			}	
		}

		// Anormalidade de Leitura Informada
		Collection<Integer> colecaoIdsAnormalidadeLeituraInformada = null;

		if (form.getColecaoIdsLeituraAnormalidadeInformada() != null) {
			colecaoIdsAnormalidadeLeituraInformada = new ArrayList<Integer>();
			
			for (String id : form.getColecaoIdsLeituraAnormalidadeInformada()) {
				if (!id.equals("-1")) {
					colecaoIdsAnormalidadeLeituraInformada.add(new Integer(id));
				}
			}
			
			if (colecaoIdsAnormalidadeLeituraInformada.size() > 0) {
				peloMenosUmParametroInformado = true;
			}
		}

		// Perfil do Im�vel
		Integer idImovelPerfil = null;

		if (form.getIdImovelPerfil() != null
				&& !form.getIdImovelPerfil().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			peloMenosUmParametroInformado = true;

			idImovelPerfil = new Integer( form.getIdImovelPerfil());
		}
		
		//Categoria
		Integer idCategoria = null;
		if (form.getIdCategoria() != null
				&& !form.getIdCategoria().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			peloMenosUmParametroInformado = true;

			idCategoria = new Integer( form.getIdCategoria());
		}

		// Refer�ncia
		Integer referencia = null;

		if (form.getReferencia() != null
				&& !form.getReferencia().equals("")) {
			
			peloMenosUmParametroInformado = true;

			referencia = Util.formatarMesAnoComBarraParaAnoMes(form.getReferencia());

			SistemaParametro sistemaParametro = fachada
					.pesquisarParametrosDoSistema();

			if (referencia > sistemaParametro.getAnoMesFaturamento()) {
				throw new ActionServletException(
						"atencao.ano_mes_referencia_anterior_que_ano_mes_faturamento_corrente",
						null, Util.somaMesMesAnoComBarra(Util.formatarAnoMesParaMesAno(sistemaParametro
								.getAnoMesFaturamento()),1));
			}

		}

		Integer mediaConsumoInicial = null;
		Integer mediaConsumoFinal = null;

		if (form.getIntervaloMediaConsumoInicial() != null
				&& !form.getIntervaloMediaConsumoInicial().trim().equals("")) {
			peloMenosUmParametroInformado = true;

			mediaConsumoInicial = new Integer(
					form.getIntervaloMediaConsumoInicial());

			mediaConsumoFinal = new Integer(
					form.getIntervaloMediaConsumoFinal());

			if (mediaConsumoInicial > mediaConsumoFinal) {
				throw new ActionServletException(
						"atencao.media.consumo.final.maior.media.consumo.inical");
			}

		}

		Integer numeroOcorrenciasConsecutivas = null;

		if (form.getNumOcorrenciasConsecutivas() != null
				&& !form.getNumOcorrenciasConsecutivas().trim().equals("")) {
			numeroOcorrenciasConsecutivas = new Integer(
					form.getNumOcorrenciasConsecutivas());

			if (numeroOcorrenciasConsecutivas > 12) {
				throw new ActionServletException(
						"atencao.quantidade_ocorrencia_maior");
			}
		}
		
		Integer amostragem = null;

		if (form.getAmostragem() != null && !form.getAmostragem().trim().equals("")) {
			
			amostragem = new Integer( form.getAmostragem());

			if (amostragem <= 0 || amostragem > 100 ) {
				throw new ActionServletException(
						"atencao.amostragem_intevalo_incorreto");
			}
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

		RelatorioAnormalidadePorAmostragem relatorioAnormalidadePorAmostragem = new RelatorioAnormalidadePorAmostragem(
				(Usuario) (httpServletRequest.getSession(false))
						.getAttribute("usuarioLogado"));

		relatorioAnormalidadePorAmostragem.addParametro("idGrupo", idGrupo);
		
		relatorioAnormalidadePorAmostragem.addParametro("cdRota", cdRota);

		relatorioAnormalidadePorAmostragem.addParametro("idGerenciaRegional",
				idGerenciaRegional);

		relatorioAnormalidadePorAmostragem.addParametro("idUnidadeNegocio",
				idUnidadeNegocio);

		if (localidadeInicial != null) {
			relatorioAnormalidadePorAmostragem.addParametro("idLocalidadeInicial",
					localidadeInicial.getId());
		}

		if (localidadeFinal != null) {
			relatorioAnormalidadePorAmostragem.addParametro("idLocalidadeFinal",
					localidadeFinal.getId());
		}
		
		if (setorComercialInicial != null) {
			relatorioAnormalidadePorAmostragem.addParametro("idSetorComercialInicial", setorComercialInicial.getCodigo());
		}
		
		if (setorComercialFinal != null) {
			relatorioAnormalidadePorAmostragem.addParametro("idSetorComercialFinal", setorComercialFinal.getCodigo());
		}
		
		if (form.getQuadraInicialNM() != null &&
				!form.getQuadraInicialNM().equals("")){
			relatorioAnormalidadePorAmostragem.addParametro("numeroQuadraInicial",
					new Integer(form.getQuadraInicialNM()));
		}
		
		if (form.getQuadraFinalID() != null &&
				!form.getQuadraFinalNM().equals("")){
			relatorioAnormalidadePorAmostragem.addParametro("numeroQuadraFinal", 
					new Integer(form.getQuadraFinalNM()));
		}

		if (colecaoIdsEmpresa != null) {
			relatorioAnormalidadePorAmostragem.addParametro("colecaoIdsEmpresa", colecaoIdsEmpresa);
		}

		if (colecaoIdsAnormalidadeLeituraInformada != null) {
			relatorioAnormalidadePorAmostragem.addParametro("colecaoIdsAnormalidadeLeituraInformada", colecaoIdsAnormalidadeLeituraInformada);
		}

		relatorioAnormalidadePorAmostragem.addParametro("numeroOcorrencias",
				numeroOcorrenciasConsecutivas);

		relatorioAnormalidadePorAmostragem.addParametro("ocorrenciasIguais",
				form.getIndicadorOcorrenciasIguais());

		relatorioAnormalidadePorAmostragem.addParametro("idImovelPerfil",
				idImovelPerfil);
		relatorioAnormalidadePorAmostragem.addParametro("referencia", referencia);

		relatorioAnormalidadePorAmostragem.addParametro("mediaConsumoInicial",
				mediaConsumoInicial);
		relatorioAnormalidadePorAmostragem.addParametro("mediaConsumoFinal",
				mediaConsumoFinal);
		
		relatorioAnormalidadePorAmostragem.addParametro("tipoMedicao",
				new Integer(form.getTipoMedicao()));

		relatorioAnormalidadePorAmostragem.addParametro("idCategoria",idCategoria);
		
		relatorioAnormalidadePorAmostragem.addParametro("tipoFormatoRelatorio",
				Integer.parseInt(tipoRelatorio));
		
		relatorioAnormalidadePorAmostragem.addParametro("amostragem", amostragem);

		
		retorno = processarExibicaoRelatorio(relatorioAnormalidadePorAmostragem,
				tipoRelatorio, httpServletRequest, httpServletResponse,
				actionMapping);

		return retorno;
	}
	
	private void pesquisarQuadra( GerarRelatorioAnormalidadePorAmostragemActionForm form,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		Collection colecaoPesquisa = null;
		String setorComercialCD = null;
		String setorComercialID = null;
		String quadraNM = null;
		FiltroQuadra filtroQuadra = new FiltroQuadra();

		if(form.getQuadraInicialNM() != null &&
				!form.getQuadraInicialNM().equals("")){
			
			setorComercialCD = (String) form.getCodigoSetorComercialInicial();
			setorComercialID = (String) form.getIdSetorComercialInicial();

			if (setorComercialCD != null
					&& !setorComercialCD.trim().equalsIgnoreCase("")
					&& setorComercialID != null
					&& !setorComercialID.trim().equalsIgnoreCase("")) {

				quadraNM = (String) form.getQuadraInicialNM();

				// Adiciona o id do setor comercial que est� no formul�rio para compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_SETORCOMERCIAL, setorComercialID));

				// Adiciona o n�mero da quadra que esta no formul�rio para compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.NUMERO_QUADRA, quadraNM));

				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna quadra
				colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Quadra nao encontrada
					// Limpa os campos quadraOrigemNM e quadraOrigemID do formul�rio
					form.setQuadraInicialNM("");
					form.setQuadraInicialID("");
					// Mensagem de tela
					form.setQuadraMensagemInicial("QUADRA INEXISTENTE.");
					httpServletRequest.setAttribute("corQuadraOrigem",	"exception");
					httpServletRequest.setAttribute("nomeCampo", "quadraInicialNM");
					
					throw new ActionServletException("atencao.pesquisa_inexistente", null, "Quadra Inicial");
					
				} else {
					Quadra objetoQuadra = (Quadra) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					form.setQuadraInicialNM(String.valueOf(objetoQuadra.getNumeroQuadra()));
					form.setQuadraInicialID(String.valueOf(objetoQuadra.getId()));
					httpServletRequest.setAttribute("corQuadraOrigem", "valor");
					
					if(form.getQuadraFinalNM() == null || form.getQuadraFinalNM().equals("")){
						form.setQuadraFinalNM(String.valueOf(objetoQuadra.getNumeroQuadra()));
						form.setQuadraFinalID(String.valueOf(objetoQuadra.getId()));
						httpServletRequest.setAttribute("corQuadraDestino", "valor");
					}
					
				}
			} else {
				// Limpa o campo quadraOrigemNM do formul�rio
				form.setQuadraInicialNM("");
				form.setQuadraMensagemInicial("Informe o setor comercial inicial.");
				httpServletRequest.setAttribute("corQuadraOrigem", "exception");
				httpServletRequest.setAttribute("nomeCampo", "codigoSetorComercialInicial");
			}
		}
		
		if(form.getQuadraFinalNM() != null &&
				!form.getQuadraFinalNM().equals("")){
			//Recebe os valores dos campos setorComercialOrigemCD e
			// setorComercialOrigemID do formul�rio.
			setorComercialCD = (String) form.getCodigoSetorComercialFinal();
			setorComercialID = (String) form.getIdSetorComercialFinal();

			// Os campos setorComercialOrigemCD e setorComercialID ser�o
			// obrigat�rios
			if (setorComercialCD != null
					&& !setorComercialCD.trim().equalsIgnoreCase("")
					&& setorComercialID != null
					&& !setorComercialID.trim().equalsIgnoreCase("")) {

				quadraNM = (String) form.getQuadraFinalNM();

				// Adiciona o id do setor comercial que est� no formul�rio para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_SETORCOMERCIAL, setorComercialID));

				// Adiciona o n�mero da quadra que esta no formul�rio para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.NUMERO_QUADRA, quadraNM));

				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna quadra
				colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class
						.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Quadra nao encontrada
					// Limpa os campos quadraOrigemNM e quadraOrigemID do
					// formul�rio
					form.setQuadraFinalNM("");
					form.setQuadraFinalID("");
					// Mensagem de tela
					form.setQuadraMensagemFinal("QUADRA INEXISTENTE.");
					httpServletRequest.setAttribute("corQuadraDestino",	"exception");
					httpServletRequest.setAttribute("nomeCampo", "quadraFinalNM");
					
					throw new ActionServletException("atencao.pesquisa_inexistente", null, "Quadra Final");
					
				} else {
					Quadra objetoQuadra = (Quadra) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					form.setQuadraFinalNM(String.valueOf(objetoQuadra.getNumeroQuadra()));
					form.setQuadraFinalID(String.valueOf(objetoQuadra.getId()));
					httpServletRequest.setAttribute("corQuadraDestino", "valor");
				}
			} else {
				// Limpa o campo setorComercialOrigemCD do formul�rio
				form.setQuadraFinalNM("");
				// Mensagem de tela
				form.setQuadraMensagemFinal("Informe o setor comercial final.");
				httpServletRequest.setAttribute("corQuadraDestino", "exception");
				httpServletRequest.setAttribute("nomeCampo","codigoSetorComercialFinal");
			}
		}
	}
	
	private void validacaoFinal(GerarRelatorioAnormalidadePorAmostragemActionForm form) {

		// validar localidade inicial sendo maior que localidade final
		if (form.getIdLocalidadeInicial() != null
				&& form.getIdLocalidadeFinal() != null) {
			
			if (!form.getIdLocalidadeInicial().equals("") && !form.getIdLocalidadeFinal().equals("")) {
				
				int origem = Integer.parseInt(form.getIdLocalidadeInicial());
				int destino = Integer.parseInt(form.getIdLocalidadeFinal());
				
				if (origem > destino) {
					throw new ActionServletException(
					"atencao.localidade.final.maior.localidade.inicial",null, "");
				}
			}
		}

		// validar setor comercial sendo maior que localidade final
		if (form.getCodigoSetorComercialInicial() != null
				&& form.getCodigoSetorComercialFinal() != null) {
			if (!form.getCodigoSetorComercialInicial().equals("")
					&& !form.getCodigoSetorComercialFinal().equals("")) {
				
				int origem = Integer.parseInt(form.getCodigoSetorComercialInicial());
				int destino = Integer.parseInt(form.getCodigoSetorComercialFinal());
				
				if (origem > destino) {
					throw new ActionServletException(
							"atencao.setor.comercial.final.maior.setor.comercial.inicial", null, "");
				}
			}
		}

		// validar quadra sendo maior que localidade final
		if (form.getQuadraInicialNM() != null && form.getQuadraFinalNM() != null) {
			
			if (!form.getQuadraInicialNM().equals("")
					&& !form.getQuadraFinalNM().equals("")) {
				
				int origem = Integer.parseInt(form.getQuadraInicialNM());
				int destino = Integer.parseInt(form.getQuadraFinalNM());
				
				if (origem > destino)
					throw new ActionServletException(
							"atencao.quadra.final.maior.quadra.inical", null,"");
			}
		}
	}
	
}
