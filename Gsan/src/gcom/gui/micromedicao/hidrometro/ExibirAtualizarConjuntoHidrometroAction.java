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
package gcom.gui.micromedicao.hidrometro;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroCapacidade;
import gcom.micromedicao.hidrometro.FiltroHidrometroClasseMetrologica;
import gcom.micromedicao.hidrometro.FiltroHidrometroDiametro;
import gcom.micromedicao.hidrometro.FiltroHidrometroMarca;
import gcom.micromedicao.hidrometro.FiltroHidrometroTipo;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroClasseMetrologica;
import gcom.micromedicao.hidrometro.HidrometroDiametro;
import gcom.micromedicao.hidrometro.HidrometroMarca;
import gcom.micromedicao.hidrometro.HidrometroTipo;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action respons�vel pela pre-exibi��o da atualiza��o do conjunto de hidrometro
 * 
 * @author S�vio Luiz
 * @created 14 de Setembro de 2005
 */
public class ExibirAtualizarConjuntoHidrometroAction extends GcomAction {
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
		ActionForward retorno = actionMapping
				.findForward("atualizarConjuntoHidrometro");

		// Obt�m o action form
		HidrometroActionForm hidrometroActionForm = (HidrometroActionForm) actionForm;
		// Obt�m a sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

		Collection colecaoHidrometroCapacidade = null;
		/*
		 * if(sessao.getAttribute("colecaoHidrometroCapacidade")!= null &&
		 * !sessao.getAttribute("colecaoHidrometroCapacidade").equals("")){
		 * colecaoHidrometroCapacidade =
		 * (Collection)sessao.getAttribute("colecaoHidrometroCapacidade"); }
		 */

		// Filtro de hidr�metro capacidade para obter capacidade de hidr�metro
		// de acordo com o id
		FiltroHidrometroCapacidade filtroHidrometroCapacidadeNumeroDigitos = new FiltroHidrometroCapacidade();

		String fixo = (String) sessao.getAttribute("fixo");
		String faixaInicial = (String) sessao.getAttribute("faixaInicial");
		String faixaFinal = (String) sessao.getAttribute("faixaFinal");

		// Obt�m a facahda
		Fachada fachada = Fachada.getInstancia();
		
		Collection hidrometros = fachada.pesquisarNumeroHidrometroFaixa(fixo,faixaInicial,faixaFinal);
		
		// caso o pesquisa intervalo seja diferente de nulo ent�o veio do
		// hidrometro_atualizar_conjunto
		// e n�o ser� necessario verificar novamente se o conjunto de
		// hidrometros
		// est�o com o mesmo valor
		String pesquisaIntervaloPorCapacidade = httpServletRequest
				.getParameter("pesquisaIntervalo");

		if (pesquisaIntervaloPorCapacidade == null
				|| pesquisaIntervaloPorCapacidade.equals("")) {

			// Verifica se os objetos est�o na sess�o
			if (hidrometros != null && !hidrometros.isEmpty()) {

				Iterator hidrometroIterator = hidrometros.iterator();

				boolean primeiraVez = true;
				Hidrometro primeiroHidrometro = null;

				while (hidrometroIterator.hasNext()) {
					if (primeiraVez) {

						primeiraVez = false;

						primeiroHidrometro = (Hidrometro) hidrometroIterator
								.next();
						// seta os valores no form
						sessao.setAttribute("hidrometro", primeiroHidrometro);
						hidrometroActionForm.setFixo(fixo);
						hidrometroActionForm.setFaixaFinal(faixaFinal);
						hidrometroActionForm.setFaixaInicial(faixaInicial);
						hidrometroActionForm
								.setAnoFabricacao(formatarResultado(""
										+ primeiroHidrometro.getAnoFabricacao()));
						SimpleDateFormat dataFormatoAtual = new SimpleDateFormat(
								"dd/MM/yyyy");
						// joga em dataInicial a parte da data
						String dataAquisicao = dataFormatoAtual
								.format(primeiroHidrometro.getDataAquisicao());

						hidrometroActionForm
								.setDataAquisicao(formatarResultado(dataAquisicao));
						hidrometroActionForm
								.setIdHidrometroCapacidade(formatarResultado(""
										+ primeiroHidrometro
												.getHidrometroCapacidade()
												.getId()));
						hidrometroActionForm
								.setIdHidrometroClasseMetrologica(formatarResultado(""
										+ primeiroHidrometro
												.getHidrometroClasseMetrologica()
												.getId()));
						hidrometroActionForm
								.setIdHidrometroDiametro(formatarResultado(""
										+ primeiroHidrometro
												.getHidrometroDiametro()
												.getId()));
						hidrometroActionForm
								.setIdHidrometroMarca(formatarResultado(""
										+ primeiroHidrometro
												.getHidrometroMarca().getId()));
						hidrometroActionForm
								.setIdHidrometroTipo(formatarResultado(""
										+ primeiroHidrometro
												.getHidrometroTipo().getId()));
						hidrometroActionForm
								.setIndicadorOperacional(formatarResultado(""
										+ primeiroHidrometro
												.getIndicadorOperacional()));
						hidrometroActionForm
								.setIdNumeroDigitosLeitura(formatarResultado(""
										+ primeiroHidrometro
												.getNumeroDigitosLeitura()));
						if(primeiroHidrometro.getHidrometroRelojoaria() != null && 
								!primeiroHidrometro.getHidrometroRelojoaria().equals("")){
						hidrometroActionForm
							    .setIdHidrometroRelojoaria(formatarResultado(""
									+ primeiroHidrometro
											.getHidrometroRelojoaria().getId()));
						}else{
							hidrometroActionForm
						    .setIdHidrometroRelojoaria("");
						}
						//Vazao Transicao
						if (primeiroHidrometro.getVazaoTransicao() != null ) {
							hidrometroActionForm
		                		.setVazaoTransicao( "" + Util.formatarMoedaReal(primeiroHidrometro.getVazaoTransicao()))  ;
						} else {
							hidrometroActionForm.setVazaoTransicao( "" );
						}
						//Vazao Nominal
						if (primeiroHidrometro.getVazaoNominal() != null ) {
		                hidrometroActionForm
		            		.setVazaoNominal( "" + Util.formatarMoedaReal(primeiroHidrometro.getVazaoNominal()));
						} else {
							hidrometroActionForm.setVazaoNominal( "" );
						}
						//Vazao Minima
						if (primeiroHidrometro.getVazaoMinima() != null ) {
		                hidrometroActionForm
		            		.setVazaoMinima( "" +  Util.formatarMoedaReal(primeiroHidrometro.getVazaoMinima())) ;
						} else {
							hidrometroActionForm.setVazaoMinima( "" );
						}
						//Nota Fiscal
		                if(primeiroHidrometro.getNotaFiscal() != null ) {
		                	hidrometroActionForm
		                		.setNotaFiscal( "" + primeiroHidrometro.getNotaFiscal() ) ;
		                } else {
		                	hidrometroActionForm.setNotaFiscal( "" );
		                }
		                //Tempo Garantia Anos
		                if(primeiroHidrometro.getTempoGarantiaAnos() != null ) {
		                	hidrometroActionForm
		                		.setTempoGarantiaAnos( "" +  primeiroHidrometro.getTempoGarantiaAnos() ) ;
		                } else {
		                	hidrometroActionForm.setTempoGarantiaAnos( "" ) ;
		                }

					} else {

						Hidrometro hidrometro = (Hidrometro) hidrometroIterator
								.next();
						// Caso as informa��es de data de aquisi��o, ano de
						// fabrica��o, finalidade da medi��o do hidr�metro,
						// classe metrol�gica, marca, di�metro, capacidade e
						// tipo
						// n�o sejam todas iguais
						if (!primeiroHidrometro.equalsHidrometro(hidrometro)) {
							throw new ActionServletException(
									"atencao.faixa.nao.uniformidade");
						}
					}

				}

				// Filtro de hidr�metro classe metrol�gica para obter todas as
				// classes metrol�gicas ativas
				FiltroHidrometroClasseMetrologica filtroHidrometroClasseMetrologica = new FiltroHidrometroClasseMetrologica();

				filtroHidrometroClasseMetrologica
						.adicionarParametro(new ParametroSimples(
								FiltroHidrometroClasseMetrologica.INDICADOR_USO,
								ConstantesSistema.INDICADOR_USO_ATIVO));
				filtroHidrometroClasseMetrologica
						.setCampoOrderBy(FiltroHidrometroClasseMetrologica.DESCRICAO);

				// Pesquisa a cole��o de classe metrol�gica
				Collection colecaoHidrometroClasseMetrologica = fachada
						.pesquisar(filtroHidrometroClasseMetrologica,
								HidrometroClasseMetrologica.class.getName());

				// Filtro de hidr�metro marca para obter todas as marcas de
				// hidr�metros ativas
				FiltroHidrometroMarca filtroHidrometroMarca = new FiltroHidrometroMarca();

				filtroHidrometroMarca.adicionarParametro(new ParametroSimples(
						FiltroHidrometroMarca.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
				filtroHidrometroMarca
						.setCampoOrderBy(FiltroHidrometroMarca.DESCRICAO);

				// Pesquisa a cole��o de hidr�metro marca
				Collection colecaoHidrometroMarca = fachada.pesquisar(
						filtroHidrometroMarca, HidrometroMarca.class.getName());

				// Filtro de hidr�metro di�metro para obter todos os di�metros
				// de
				// hidr�metros ativos
				FiltroHidrometroDiametro filtroHidrometroDiametro = new FiltroHidrometroDiametro();

				filtroHidrometroDiametro
						.adicionarParametro(new ParametroSimples(
								FiltroHidrometroDiametro.INDICADOR_USO,
								ConstantesSistema.INDICADOR_USO_ATIVO));
				filtroHidrometroDiametro
						.setCampoOrderBy(FiltroHidrometroDiametro.DESCRICAO);

				// Pesquisa a cole��o de hidr�metro capacidade
				Collection colecaoHidrometroDiametro = fachada.pesquisar(
						filtroHidrometroDiametro, HidrometroDiametro.class
								.getName());

				// Filtro de hidr�metro capacidade para obter todos as
				// capacidade de
				// hidr�metros ativas
				FiltroHidrometroCapacidade filtroHidrometroCapacidade = new FiltroHidrometroCapacidade();

				filtroHidrometroCapacidade
						.adicionarParametro(new ParametroSimples(
								FiltroHidrometroCapacidade.INDICADOR_USO,
								ConstantesSistema.INDICADOR_USO_ATIVO));
				filtroHidrometroCapacidade
						.setCampoOrderBy(FiltroHidrometroCapacidade.DESCRICAO);

				// Pesquisa a cole��o de hidr�metro capacidade
				colecaoHidrometroCapacidade = fachada.pesquisar(
						filtroHidrometroCapacidade, HidrometroCapacidade.class
								.getName());

				// Filtro de hidr�metro tipo para obter todos os tipos de
				// hidr�metros ativos
				FiltroHidrometroTipo filtroHidrometroTipo = new FiltroHidrometroTipo();

				filtroHidrometroTipo.adicionarParametro(new ParametroSimples(
						FiltroHidrometroTipo.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
				filtroHidrometroTipo
						.setCampoOrderBy(FiltroHidrometroTipo.DESCRICAO);

				// Pesquisa a cole��o de hidr�metro tipo
				Collection colecaoHidrometroTipo = fachada.pesquisar(
						filtroHidrometroTipo, HidrometroTipo.class.getName());

				// Envia as cole��es na sess�o
				sessao.setAttribute("colecaoHidrometroClasseMetrologica",
						colecaoHidrometroClasseMetrologica);
				sessao.setAttribute("colecaoHidrometroMarca",
						colecaoHidrometroMarca);
				sessao.setAttribute("colecaoHidrometroDiametro",
						colecaoHidrometroDiametro);
				sessao.setAttribute("colecaoHidrometroCapacidade",
						colecaoHidrometroCapacidade);
				sessao.setAttribute("colecaoHidrometroTipo",
						colecaoHidrometroTipo);

				// Verifica se a cole��o de hidrometro capacidade � diferente de
				// null
//				if (colecaoHidrometroCapacidade != null
//						&& !colecaoHidrometroCapacidade.isEmpty()) {
//
//					// Obt�m o primeiro objeto da collection
//					Iterator colecaoHidrometroCapacidadeIterator = colecaoHidrometroCapacidade
//							.iterator();
//					HidrometroCapacidade hidrometroCapacidade = (HidrometroCapacidade) colecaoHidrometroCapacidadeIterator
//							.next();

					// Filtra pelo primeiro objeto da collection
					filtroHidrometroCapacidadeNumeroDigitos
							.adicionarParametro(new ParametroSimples(
									FiltroHidrometroCapacidade.ID,
									primeiroHidrometro.getHidrometroCapacidade().getId()));
//				}
			}
		} else {

			// Filtra pelo id selecionado no combobox
			filtroHidrometroCapacidadeNumeroDigitos
					.adicionarParametro(new ParametroSimples(
							FiltroHidrometroCapacidade.ID, new Integer(
									hidrometroActionForm
											.getIdHidrometroCapacidade())));

		}

		// Pesquisa o n�mero de d�gitos de acordo com o filtro
		Collection colecaoHidrometroCapacidadeNumeroDigitos = fachada
				.pesquisar(filtroHidrometroCapacidadeNumeroDigitos,
						HidrometroCapacidade.class.getName());

		// Retorna o objeto pesquisado
		HidrometroCapacidade hidrometroCapacidadeNumeroDigitos = (HidrometroCapacidade) Util
				.retonarObjetoDeColecao(colecaoHidrometroCapacidadeNumeroDigitos);

		// Obt�m as leituras
		Integer leituraMinimo = new Integer(hidrometroCapacidadeNumeroDigitos
				.getLeituraMinimo().toString());
		Integer leituraMaximo = new Integer(hidrometroCapacidadeNumeroDigitos
				.getLeituraMaximo().toString());

		// Obt�m a quantidade da diferen�a
		int quantidade = (leituraMaximo.intValue() - leituraMinimo.intValue()) + 1;

		Collection colecaoIntervalo = new ArrayList();

		// Adiciona a quantidade da diferen�a na cole��o
		for (int i = 0; i < quantidade; i++) {

			colecaoIntervalo.add(new Integer(leituraMinimo.intValue() + i));
		}

		// Envia pela sess�o
		sessao.setAttribute("colecaoIntervalo", colecaoIntervalo);

		return retorno;
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param parametro
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	private String formatarResultado(String parametro) {
		if (parametro != null && !parametro.trim().equals("")) {
			if (parametro.equals("null")) {
				return "";
			} else {
				return parametro.trim();
			}
		} else {
			return "";
		}
	}

}
