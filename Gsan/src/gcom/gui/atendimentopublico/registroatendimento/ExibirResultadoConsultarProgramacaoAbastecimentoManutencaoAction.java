/**
 * 
 */
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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.BairroArea;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroBairroArea;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroSistemaAbastecimento;
import gcom.operacional.FiltroZonaAbastecimento;
import gcom.operacional.SistemaAbastecimento;
import gcom.operacional.ZonaAbastecimento;
import gcom.operacional.abastecimento.AbastecimentoProgramacao;
import gcom.operacional.abastecimento.ManutencaoProgramacao;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Calendar;
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
 * Descri��o da classe
 * 
 * @author R�mulo Aur�lio
 * @date 29/08/2006
 */
public class ExibirResultadoConsultarProgramacaoAbastecimentoManutencaoAction
		extends GcomAction {
	/**
	 * Este caso de uso permite a programa�ao de abastecimento e manutencao de
	 * uma determinada �rea de bairro
	 * 
	 * [UC0440] Consultar Programa��o de Abastecimento e Manuten��o
	 * 
	 * 
	 * @author R�mulo Aur�lio
	 * @date 29/08/2006
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

		ActionForward retorno = actionMapping
				.findForward("programacaoAbastecimentoManutencaoResultadoConsultar");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		ResultadoConsultarProgramacaoAbastecimentoManutencaoActionForm resultadoConsultarProgramacaoAbastecimentoManutencaoActionForm = (ResultadoConsultarProgramacaoAbastecimentoManutencaoActionForm) actionForm;

		// Recebe o ano e o mes pela sessao

		String ano = (String) sessao.getAttribute("ano");
		String anoCalendario = (String) httpServletRequest.getParameter("ano");

		String mes = (String) sessao.getAttribute("mes");
		String mesCalendario = (String) httpServletRequest.getParameter("mes");

		Calendar calendario = Calendar.getInstance();
		int ultimoDia = 0;

		if (anoCalendario != null && !anoCalendario.equalsIgnoreCase("")
				&& mesCalendario != null && !mesCalendario.equalsIgnoreCase("")) {

			httpServletRequest.setAttribute("ano", anoCalendario);
			httpServletRequest.setAttribute("mes", mesCalendario);

			// new Integer(mesCalendario) - 1

			Integer aux = new Integer(mesCalendario);

			aux = aux + 1;

			if (aux < 10) {

				mesCalendario = "0" + aux.toString();
			} else {
				mesCalendario = aux.toString();
			}

			ano = anoCalendario;
			mes = mesCalendario;

			calendario.set(new Integer(anoCalendario), new Integer(
					mesCalendario), 1);
			ultimoDia = calendario.getActualMaximum(Calendar.DAY_OF_MONTH);

		} else {
			httpServletRequest.setAttribute("ano", ano);
			httpServletRequest.setAttribute("mes", new Integer(mes) - 1);

			calendario.set(new Integer(ano), new Integer(mes), 1);
			ultimoDia = calendario.getMaximum(Calendar.DAY_OF_MONTH);

		}

		Integer[] vetorEstilos = new Integer[ultimoDia];

		String idMunicipio = (String) sessao.getAttribute("idMunicipio");

		String idBairro = (String) sessao.getAttribute("idBairro");
		String areaBairro = (String) sessao.getAttribute("areaBairro");
		String mesAnoReferencia = null;

		resultadoConsultarProgramacaoAbastecimentoManutencaoActionForm
				.setCodigoMunicipio(idMunicipio);

		resultadoConsultarProgramacaoAbastecimentoManutencaoActionForm
				.setCodigoBairro(idBairro);

		resultadoConsultarProgramacaoAbastecimentoManutencaoActionForm
				.setCodigoAreaBairro(areaBairro);

		mesAnoReferencia = mes + "/" + ano;
		
		sessao.setAttribute("mesAnoReferencia", mesAnoReferencia);

		// Pesquisa o a Programacao de Abastecimento
		Collection colecaoProgramacaoAbastecimento = fachada
				.consultarProgramacaoAbastecimento(idMunicipio, idBairro,
						areaBairro, mesAnoReferencia);

		// Pesquisa o a Programacao de Manutencao
		Collection colecaoProgramacaoManutencao = fachada
				.consultarProgramacaoManutencao(idMunicipio, idBairro,
						areaBairro, mesAnoReferencia);

		// // Caso nao possua programacao de abastecimento e manutencao
		// if ((colecaoProgramacaoAbastecimento == null ||
		// colecaoProgramacaoAbastecimento
		// .isEmpty())
		// && ((colecaoProgramacaoManutencao == null ||
		// colecaoProgramacaoManutencao
		// .isEmpty()))) {
		// throw new ActionServletException(
		// "atencao.abastecimento_manutencao_sem_registro", null);
		//
		// }

		sessao.setAttribute("colecaoProgramacaoAbastecimento",
				colecaoProgramacaoAbastecimento);

		sessao.setAttribute("colecaoProgramacaoManutencao",
				colecaoProgramacaoManutencao);

		Date dataInicio = null;
		Date dataFim = null;

		// Caso possua abastecimento no ano mes de referencia

		if (colecaoProgramacaoAbastecimento != null
				&& !colecaoProgramacaoAbastecimento.isEmpty()) {

			Iterator iteratorAbastecimento = colecaoProgramacaoAbastecimento
					.iterator();

			AbastecimentoProgramacao abastecimentoProgramacao = null;

			while (iteratorAbastecimento.hasNext()) {

				abastecimentoProgramacao = (AbastecimentoProgramacao) iteratorAbastecimento
						.next();

				dataInicio = abastecimentoProgramacao.getDataInicio();

				dataFim = abastecimentoProgramacao.getDataFim();

				/*
				 * Caso exista um intervalo maior do que um dia no
				 * abastecimento, verifica a qtde de dias entre o inicio e o fim
				 * as e jogo num vetor informando o estilo deste dia para o
				 * calend�rio
				 * 
				 */

				Calendar obterDia = Calendar.getInstance();

				if (dataInicio != dataFim) {

					int qtdDias = gcom.util.Util
							.obterQuantidadeDiasEntreDuasDatas(dataInicio,
									dataFim);

					int i = 0;
					Date dataIntervalo = null;

					while (i < qtdDias + 1) {

						dataIntervalo = gcom.util.Util
								.adicionarNumeroDiasDeUmaData(dataInicio, i);

						obterDia.setTime(dataIntervalo);
						vetorEstilos[obterDia.get(Calendar.DAY_OF_MONTH) - 1] = ConstantesSistema.SITUACAO_ABASTECIMENTO;

						i++;
					}
				} else {

					obterDia.setTime(dataInicio);
					vetorEstilos[obterDia.get(Calendar.DAY_OF_MONTH) - 1] = ConstantesSistema.SITUACAO_ABASTECIMENTO;
				}
			}

			if (abastecimentoProgramacao.getSistemaAbastecimento() != null &&
				abastecimentoProgramacao.getSistemaAbastecimento().getId() != null) {
				
				FiltroSistemaAbastecimento filtroSistemaAbastecimento = new FiltroSistemaAbastecimento();

				filtroSistemaAbastecimento
						.adicionarParametro(new ParametroSimples(
								FiltroSistemaAbastecimento.ID,
								abastecimentoProgramacao
										.getSistemaAbastecimento().getId()
										.toString()));

				Collection colecaoSistemaAbastecimento = fachada.pesquisar(
						filtroSistemaAbastecimento, SistemaAbastecimento.class
								.getName());

				SistemaAbastecimento sistemaAbastecimento = (SistemaAbastecimento) colecaoSistemaAbastecimento
						.iterator().next();

				// Envia dados para recuperar em
				// ExibirResultadoDetalhadoProgramacaoAbastecimentoManutencaoAction

				sessao.setAttribute("sistemaAbastecimento",
						sistemaAbastecimento.getDescricao());

				resultadoConsultarProgramacaoAbastecimentoManutencaoActionForm
						.setSistemaAbastecimento(sistemaAbastecimento
								.getDescricao());

				resultadoConsultarProgramacaoAbastecimentoManutencaoActionForm
						.setCodigoSistemaAbastecimento(sistemaAbastecimento
								.getId().toString());

				sessao.setAttribute("codigoSistemaAbastecimento",
						sistemaAbastecimento.getId().toString());

			}

			/*
			 * Zona Abastecimento, caso nao encontre abastecimento, pesquisa em
			 * manutencao
			 */

			if (abastecimentoProgramacao.getZonaAbastecimento() != null && 
				abastecimentoProgramacao.getZonaAbastecimento().getId() != null) {

				FiltroZonaAbastecimento filtroZonaAbastecimento = new FiltroZonaAbastecimento();

				filtroZonaAbastecimento
						.adicionarParametro(new ParametroSimples(
								FiltroZonaAbastecimento.ID,
								abastecimentoProgramacao.getZonaAbastecimento()
										.getId().toString()));

				Collection colecaoZonaAbastecimento = fachada.pesquisar(
						filtroZonaAbastecimento, ZonaAbastecimento.class
								.getName());

				ZonaAbastecimento zonaAbastecimento = (ZonaAbastecimento) colecaoZonaAbastecimento
						.iterator().next();

				resultadoConsultarProgramacaoAbastecimentoManutencaoActionForm
						.setZonaAbastecimento(zonaAbastecimento.getDescricao());

				resultadoConsultarProgramacaoAbastecimentoManutencaoActionForm
						.setCodigoZonaAbastecimento(zonaAbastecimento.getId()
								.toString());

				sessao.setAttribute("codigoZonaAbastecimento",
						zonaAbastecimento.getId().toString());

				// Envia dados para recuperar em
				// ExibirResultadoDetalhadoProgramacaoAbastecimentoManutencaoAction

				sessao.setAttribute("zonaAbastecimento",
						abastecimentoProgramacao.getZonaAbastecimento()
								.getDescricao().toString());

			}

			/*
			 * Municipio, caso nao encontre abastecimento, pesquisa em
			 * manutencao
			 */

			if (abastecimentoProgramacao.getMunicipio() != null && 
				abastecimentoProgramacao.getMunicipio().getId() != null) {

				FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

				filtroMunicipio.adicionarParametro(new ParametroSimples(
						FiltroMunicipio.ID, abastecimentoProgramacao
								.getMunicipio().getId().toString()));

				Collection colecaoMunicipio = fachada.pesquisar(
						filtroMunicipio, Municipio.class.getName());

				Municipio municipio = (Municipio) colecaoMunicipio.iterator()
						.next();

				resultadoConsultarProgramacaoAbastecimentoManutencaoActionForm
						.setMunicipio(municipio.getNome());

				// Envia dados para recuperar em
				// ExibirResultadoDetalhadoProgramacaoAbastecimentoManutencaoAction

				sessao
						.setAttribute("municipio", municipio.getNome()
								.toString());

			}
			/*
			 * Bairro, caso nao encontre abastecimento, pesquisa em manutencao
			 */

			if (abastecimentoProgramacao.getBairro() != null && 
				abastecimentoProgramacao.getBairro().getId() != null) {

				FiltroBairro filtroBairro = new FiltroBairro();

				filtroBairro.adicionarParametro(new ParametroSimples(
						FiltroBairro.ID, abastecimentoProgramacao.getBairro()
								.getId().toString()));

				Collection colecaoBairro = fachada.pesquisar(filtroBairro,
						Bairro.class.getName());

				Bairro bairro = (Bairro) colecaoBairro.iterator().next();

				resultadoConsultarProgramacaoAbastecimentoManutencaoActionForm
						.setBairro(bairro.getNome());

				// Envia dados para recuperar em
				// ExibirResultadoDetalhadoProgramacaoAbastecimentoManutencaoAction

				sessao.setAttribute("bairro", bairro.getNome().toString());
			}

			/*
			 * �rea Bairro, caso nao encontre abastecimento, pesquisa em
			 * manutencao
			 */

			if (abastecimentoProgramacao.getBairroArea() != null && 
				abastecimentoProgramacao.getBairroArea().getId() != null) {

				FiltroBairroArea filtroBairroArea = new FiltroBairroArea();

				filtroBairroArea.adicionarParametro(new ParametroSimples(
						FiltroBairro.ID, abastecimentoProgramacao
								.getBairroArea().getId().toString()));

				Collection colecaoBairroArea = fachada.pesquisar(
						filtroBairroArea, BairroArea.class.getName());

				BairroArea bairroArea = (BairroArea) colecaoBairroArea
						.iterator().next();

				resultadoConsultarProgramacaoAbastecimentoManutencaoActionForm
						.setAreaBairro(bairroArea.getNome());

				// Envia dados para recuperar em
				// ExibirResultadoDetalhadoProgramacaoAbastecimentoManutencaoAction

				sessao.setAttribute("bairroArea", bairroArea.getNome()
						.toString());

			}

		}

		// Caso possua manutencao no ano mes de referencia

		if (colecaoProgramacaoManutencao != null
				&& !colecaoProgramacaoManutencao.isEmpty()) {

			Iterator iteratorManutencao = colecaoProgramacaoManutencao
					.iterator();

			ManutencaoProgramacao manutencaoProgramacao = null;

			dataInicio = null;
			dataFim = null;

			while (iteratorManutencao.hasNext()) {

				manutencaoProgramacao = (ManutencaoProgramacao) iteratorManutencao
						.next();

				dataInicio = manutencaoProgramacao.getDataInicio();

				dataFim = manutencaoProgramacao.getDataFim();

				/*
				 * Caso exista um intervalo maior do que um dia no
				 * abastecimento, verifica a qtde de dias entre o inicio e o fim
				 * as e jogo num vetor informando o estilo deste dia para o
				 * calend�rio
				 * 
				 */

				Calendar obterDia = Calendar.getInstance();

				if (dataInicio != dataFim) {

					int qtdDias = gcom.util.Util
							.obterQuantidadeDiasEntreDuasDatas(dataInicio,
									dataFim);

					int i = 0;
					Date dataIntervalo = null;

					while (i < qtdDias + 1) {

						dataIntervalo = gcom.util.Util
								.adicionarNumeroDiasDeUmaData(dataInicio, i);

						obterDia.setTime(dataIntervalo);

						// Verifica se o dia possui abastecimento e
						// manutencao

						if (vetorEstilos[obterDia.get(Calendar.DAY_OF_MONTH) - 1] != null) {

							vetorEstilos[obterDia.get(Calendar.DAY_OF_MONTH) - 1] = ConstantesSistema.SITUACAO_ABASTECIMENTO_MANUTENCAO;

						} else {

							vetorEstilos[obterDia.get(Calendar.DAY_OF_MONTH) - 1] = ConstantesSistema.SITUACAO_MANUTENCAO;
						}
						i++;
					}
				} else {

					obterDia.setTime(dataInicio);

					// Verifica se o dia possui abastecimento e manutencao

					if (vetorEstilos[obterDia.get(Calendar.DAY_OF_MONTH) - 1] != null) {

						vetorEstilos[obterDia.get(Calendar.DAY_OF_MONTH) - 1] = ConstantesSistema.SITUACAO_ABASTECIMENTO_MANUTENCAO;

					} else {

						vetorEstilos[obterDia.get(Calendar.DAY_OF_MONTH) - 1] = ConstantesSistema.SITUACAO_MANUTENCAO;
					}
				}
			}

			if (manutencaoProgramacao.getSistemaAbastecimento() != null && 
				manutencaoProgramacao.getSistemaAbastecimento().getId() != null) {
				
				FiltroSistemaAbastecimento filtroSistemaAbastecimento = new FiltroSistemaAbastecimento();

				filtroSistemaAbastecimento
						.adicionarParametro(new ParametroSimples(
								FiltroSistemaAbastecimento.ID,
								manutencaoProgramacao.getSistemaAbastecimento()
										.getId().toString()));

				Collection colecaoSistemaAbastecimento = fachada.pesquisar(
						filtroSistemaAbastecimento, SistemaAbastecimento.class
								.getName());

				SistemaAbastecimento sistemaAbastecimento = (SistemaAbastecimento) colecaoSistemaAbastecimento
						.iterator().next();

				resultadoConsultarProgramacaoAbastecimentoManutencaoActionForm
						.setSistemaAbastecimento(sistemaAbastecimento
								.getDescricao());

				// Envia dados para recuperar em
				// ExibirResultadoDetalhadoProgramacaoAbastecimentoManutencaoAction

				sessao.setAttribute("sistemaAbastecimento",
						sistemaAbastecimento.getDescricao().toString());

			}

			/*
			 * Zona Abastecimento, caso nao encontre abastecimento, pesquisa em
			 * manutencao
			 */

			if (manutencaoProgramacao.getZonaAbastecimento() != null &&
				manutencaoProgramacao.getZonaAbastecimento().getId() != null) {

				FiltroZonaAbastecimento filtroZonaAbastecimento = new FiltroZonaAbastecimento();

				filtroZonaAbastecimento
						.adicionarParametro(new ParametroSimples(
								FiltroZonaAbastecimento.ID,
								manutencaoProgramacao.getZonaAbastecimento()
										.getId().toString()));

				Collection colecaoZonaAbastecimento = fachada.pesquisar(
						filtroZonaAbastecimento, ZonaAbastecimento.class
								.getName());

				ZonaAbastecimento zonaAbastecimento = (ZonaAbastecimento) colecaoZonaAbastecimento
						.iterator().next();

				resultadoConsultarProgramacaoAbastecimentoManutencaoActionForm
						.setZonaAbastecimento(zonaAbastecimento.getDescricao());

				// Envia dados para recuperar em
				// ExibirResultadoDetalhadoProgramacaoAbastecimentoManutencaoAction

				sessao.setAttribute("zonaAbastecimento", zonaAbastecimento
						.getDescricao().toString());

			}

			/*
			 * Municipio, caso nao encontre abastecimento, pesquisa em
			 * manutencao
			 */

			if (manutencaoProgramacao.getMunicipio() != null &&
				manutencaoProgramacao.getMunicipio().getId() != null) {

				FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

				filtroMunicipio.adicionarParametro(new ParametroSimples(
						FiltroMunicipio.ID, manutencaoProgramacao
								.getMunicipio().getId().toString()));

				Collection colecaoMunicipio = fachada.pesquisar(
						filtroMunicipio, Municipio.class.getName());

				Municipio municipio = (Municipio) colecaoMunicipio.iterator()
						.next();

				resultadoConsultarProgramacaoAbastecimentoManutencaoActionForm
						.setMunicipio(municipio.getNome());

				// Envia dados para recuperar em
				// ExibirResultadoDetalhadoProgramacaoAbastecimentoManutencaoAction

				sessao
						.setAttribute("municipio", municipio.getNome()
								.toString());
			}
			/*
			 * Bairro, caso nao encontre abastecimento, pesquisa em manutencao
			 */

			if (manutencaoProgramacao.getBairro() != null &&
				manutencaoProgramacao.getBairro().getId() != null) {

				FiltroBairro filtroBairro = new FiltroBairro();

				filtroBairro.adicionarParametro(new ParametroSimples(
						FiltroBairro.ID, manutencaoProgramacao.getBairro()
								.getId().toString()));

				Collection colecaoBairro = fachada.pesquisar(filtroBairro,
						Bairro.class.getName());

				Bairro bairro = (Bairro) colecaoBairro.iterator().next();

				resultadoConsultarProgramacaoAbastecimentoManutencaoActionForm
						.setBairro(bairro.getNome());
				// Envia dados para recuperar em
				// ExibirResultadoDetalhadoProgramacaoAbastecimentoManutencaoAction

				sessao.setAttribute("bairro", bairro.getNome().toString());
			}

			/*
			 * �rea Bairro, caso nao encontre abastecimento, pesquisa em
			 * manutencao
			 */

			if (manutencaoProgramacao.getBairroArea() != null &&
				manutencaoProgramacao.getBairroArea().getId() != null) {

				FiltroBairroArea filtroBairroArea = new FiltroBairroArea();

				filtroBairroArea.adicionarParametro(new ParametroSimples(
						FiltroBairro.ID, manutencaoProgramacao.getBairroArea()
								.getId().toString()));

				Collection colecaoBairroArea = fachada.pesquisar(
						filtroBairroArea, BairroArea.class.getName());

				BairroArea bairroArea = (BairroArea) colecaoBairroArea
						.iterator().next();

				resultadoConsultarProgramacaoAbastecimentoManutencaoActionForm
						.setAreaBairro(bairroArea.getNome());

				// Envia dados para recuperar em
				// ExibirResultadoDetalhadoProgramacaoAbastecimentoManutencaoAction

				sessao.setAttribute("bairroArea", bairroArea.getNome()
						.toString());

			}
		}

		// Dias sem abatecimento e sem manutencao
		int cont = 0;
		String situacoes = "";
		while (cont < vetorEstilos.length) {

			if (vetorEstilos[cont] == null) {

				vetorEstilos[cont] = ConstantesSistema.SITUACAO_ABERTO;

			}

			if (situacoes.length() < 1) {
				situacoes = vetorEstilos[cont].toString();
			} else {
				situacoes = situacoes + "," + vetorEstilos[cont].toString();
			}

			cont++;
		}

		httpServletRequest.setAttribute("situacoes", situacoes);
		
		sessao.setAttribute("pesquisa", "sim");

		return retorno;

	}
}
