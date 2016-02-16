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
package gcom.relatorio.cadastro.localidade;

import gcom.batch.Relatorio;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 
 * Title: GCOM
 * </p>
 * <p>
 * 
 * Description: Sistema de Gest�o Comercial
 * </p>
 * <p>
 * 
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Rafael Corr�a
 * @created 9 de Setembro de 2005
 * @version 1.0
 */

public class RelatorioManterQuadra extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the RelatorioManterQuadra object
	 */
	public RelatorioManterQuadra(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_QUADRA_MANTER);
	}

	@Deprecated
	public RelatorioManterQuadra() {
		super(null, "");
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param quadraParametros
	 *            Description of the Parameter
	 * @return Descri��o do retorno
	 * @exception RelatorioVazioException
	 *                Descri��o da exce��o
	 */

	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		// Recebe os par�metros que ser�o utilizados no relat�rio
		FiltroQuadra filtroQuadra = (FiltroQuadra) getParametro("filtroQuadra");
		Quadra quadraParametros = (Quadra) getParametro("quadraParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// cole��o de beans do relat�rio

		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioManterQuadraBean relatorioBean = null;

		// Cria adiciona os carregamentos dos objetos necess�rios para
		// a impress�o do relat�rio
		filtroQuadra
				.adicionarCaminhoParaCarregamentoEntidade("setorComercial.localidade");
		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("quadraPerfil");
		filtroQuadra
				.adicionarCaminhoParaCarregamentoEntidade("bacia.sistemaEsgoto");
		filtroQuadra
				.adicionarCaminhoParaCarregamentoEntidade("distritoOperacional");
		filtroQuadra
				.adicionarCaminhoParaCarregamentoEntidade("ibgeSetorCensitario");
		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("zeis");
		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("areaTipo");
		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("rota");

		filtroQuadra.setConsultaSemLimites(true);

		// Nova consulta para trazer objeto completo
		Collection quadrasNovas = fachada.pesquisar(filtroQuadra, Quadra.class
				.getName());

		Collections.sort((List) quadrasNovas, new Comparator() {
			public int compare(Object a, Object b) {
				String idLocal1 = ""
						+ ((Quadra) a).getSetorComercial().getLocalidade()
								.getId();
				String idLocal2 = ""
						+ ((Quadra) b).getSetorComercial().getLocalidade()
								.getId();
				if (idLocal1.compareTo(idLocal2) == 0) {

					String idSetor1 = ""
							+ ((Quadra) a).getSetorComercial().getId();
					String idSetor2 = ""
							+ ((Quadra) b).getSetorComercial().getId();

					if (idSetor1.compareTo(idSetor2) == 0) {
						Integer numQuadra1 = ((Quadra) a).getNumeroQuadra();
						Integer numQuadra2 = ((Quadra) b).getNumeroQuadra();

						return numQuadra1.compareTo(numQuadra2);

					} else {

						return idSetor1.compareTo(idSetor2);
					}

				} else {

					return idLocal1.compareTo(idLocal2);
				}
			}
		});
		
			
			
			if (quadrasNovas != null && !quadrasNovas.isEmpty()) {
				// coloca a cole��o de par�metros da analise no iterator
				Iterator quadraNovaIterator = quadrasNovas.iterator();

				while (quadraNovaIterator.hasNext()) {

					Quadra quadraNova = (Quadra) quadraNovaIterator.next();
					
					//Consulta do indicador da quadra face do Sistema Parametro e verifica se contem Quadra Face
					SistemaParametro sistParametro = fachada.pesquisarParametrosDoSistema();
					String indicadorQuadraFace = "" + sistParametro.getIndicadorQuadraFace();
					
					Integer idQuadra = quadraNova.getId();
					
					// Faz as valida��es dos campos necess�riose e formata a String
					// para a forma como ir� aparecer no relat�rio
					String tipoArea = "";

					if (quadraNova.getAreaTipo() != null) {
						tipoArea = quadraNova.getAreaTipo().getDescricaoAbreviada();
					}

					String indicadorRedeAgua = "";

					if (quadraNova.getIndicadorRedeAgua() != null
							&& quadraNova.getIndicadorRedeAgua().equals(
									new Short("2"))) {
						
						indicadorRedeAgua = "SIM";
						
					} else if (quadraNova.getIndicadorRedeAgua() != null
							&& quadraNova.getIndicadorRedeAgua().equals(
									new Short("1"))) {
						
						indicadorRedeAgua = "N�O";
						
					} else {
						indicadorRedeAgua = "PARCIAL";
					}
					
					String indicadorRedeEsgoto = "";

					if (quadraNova.getIndicadorRedeEsgoto() != null
							&& quadraNova.getIndicadorRedeEsgoto().equals(
									new Short("2"))) {
						
						indicadorRedeEsgoto = "SIM";
						
					} else if (quadraNova.getIndicadorRedeEsgoto() != null
							&& quadraNova.getIndicadorRedeEsgoto().equals(
									new Short("1"))) {
						
						indicadorRedeEsgoto = "N�O";
						
					} else {
						indicadorRedeEsgoto = "PARCIAL";
					}

					// Inicializa o construtor constitu�do dos campos
					// necess�rios para a impress�o do relatorio
					relatorioBean = new RelatorioManterQuadraBean(
					// N�mero da Quadra
							"" + quadraNova.getNumeroQuadra(),

							// Descri��o Localidade
							quadraNova.getSetorComercial().getLocalidade()
									.getDescricao(),

							// C�digo Setor Comercial
							"" + quadraNova.getSetorComercial().getCodigo(),

							// Descri��o Setor Comercial
							quadraNova.getSetorComercial().getDescricao(),

							// Quadra Perfil
							quadraNova.getQuadraPerfil().getDescricao(),

							// Tipo de �rea
							tipoArea,

							// Indicador Rede �gua
							indicadorRedeAgua,

							// Indicador Rede Esgoto
							indicadorRedeEsgoto,

							// Campos que ficam em branco para serem setados
							// ap�s o construtor, evitando, assim, que ocorra
							// erros
							// devido ao fato de alguns objetos estarem nulos.
							// Esses campos s�o: bacia, distrito operacional,
							// setor censit�rio e zeis
							"", "", "", "", "",

							// Rota
							quadraNova.getRota().getCodigo().toString(),

							// Indicador Uso
							quadraNova.getIndicadorUso().toString(),
							
							//Indicador de Face da Quadra
							indicadorQuadraFace,
							
							//Id da Face da Quadra
							null,
							
							//Bacia da Quadra Face
							null,
							
							//Sistems Esgoto da Quadra Face
							null,
							
							//Id Quadra
							""+ idQuadra,
							
							//Rede de Agua da Quadra Face
							null,
							
							//Distrito Operacional da Quadra Face
							null,
							
							//Descricao Sistema Esgoto Quadra Face
							null
							);

					// Bacia
					if (quadraNova.getBacia() != null) {
						relatorioBean.setSistemaEsgoto(quadraNova.getBacia()
								.getSistemaEsgoto().getDescricao());
						relatorioBean
								.setBacia(quadraNova.getBacia().getDescricao());
					}

					// Distrito Operacional
					if (quadraNova.getDistritoOperacional() != null) {
						relatorioBean.setDistritoOperacional(quadraNova
								.getDistritoOperacional().getDescricaoAbreviada());
					}

					// Setor Censit�rio
					if (quadraNova.getIbgeSetorCensitario() != null) {
						relatorioBean.setSetorCensitario(quadraNova
								.getIbgeSetorCensitario().getDescricao());
					}

					// Zeis
					if (quadraNova.getZeis() != null) {
						relatorioBean.setZeis(quadraNova.getZeis()
								.getDescricaoAbreviada());
					}
						
					//Verifica o parametro da empresa se trabalha com Quadra Face.
					if ( indicadorQuadraFace.equals("1") ) {
						
						//Pesquisa que retorna a Quadra face associada a Quadra
						Collection<Object[]> quadraFace = null;
						quadraFace = fachada.pesquisarQuadraFaceAssociadaQuadra(idQuadra);
						
						if ( quadraFace != null && !quadraFace.isEmpty() ) {
							
							
							Iterator quadraFaceNovaIterator = quadraFace.iterator();
							
							while ( quadraFaceNovaIterator.hasNext() ) { 
								
								Object[] objeto = (Object[]) quadraFaceNovaIterator.next();
								
								Integer numeroQuadraFace = null;
								String baciaQuadraFace = "";
								String distritoOperacionalQuadraFace = "";
								Short indicadorAgua = null;
								Short indicadorEsgoto = null;
								String descricaoSistemaEsgotoQuadraFace = "";
								String redeEsgoto = "";
								String redeAgua = "";
								
								//ID da Quadra Face
								if ( objeto[0] != null ) {
									numeroQuadraFace = (Integer) objeto[0];
								}
								
								//Bacia da Quadra Face
								if ( objeto[1] != null ) {
									baciaQuadraFace = (String) objeto[1];
								}

								//Indicador Rede De Esgoto
								if ( objeto[2] != null ) {
									indicadorEsgoto = (Short) objeto[2];
								}
								
								if (indicadorEsgoto.equals(new Short("2")) ){
									redeEsgoto = "SIM";
								} else if (indicadorEsgoto.equals(new Short("1")) ){
									redeEsgoto = "N�O";
								} else {
									redeEsgoto = "PARCIAL";
								}
								
								//Indicador de Agua
								if ( objeto[3] != null ) {
									indicadorAgua = (Short) objeto[3];
								}
								
								if (indicadorAgua.equals(new Short("2")) ){
									redeAgua = "SIM";
								} else if (indicadorAgua.equals(new Short("1")) ){
									redeAgua = "N�O";
								} else {
									redeAgua = "PARCIAL";
								}
								
								//Distrito Operacional da Quadra Face
								if ( objeto[4] != null ) {
									distritoOperacionalQuadraFace = ( String ) objeto[4];
								}
								
								//Sistema Esgoto Quadra Face
								if ( objeto[5] != null ) {
									descricaoSistemaEsgotoQuadraFace = (String) objeto[5];
								}
								//Inicializa o construtor constitu�do dos campos
								// necess�rios para a impress�o do relatorio
								relatorioBean = new RelatorioManterQuadraBean(
								// N�mero da Quadra
										"" + quadraNova.getNumeroQuadra(),

										// Descri��o Localidade
										quadraNova.getSetorComercial().getLocalidade()
												.getDescricao(),

										// C�digo Setor Comercial
										"" + quadraNova.getSetorComercial().getCodigo(),

										// Descri��o Setor Comercial
										quadraNova.getSetorComercial().getDescricao(),

										// Quadra Perfil
										quadraNova.getQuadraPerfil().getDescricao(),

										// Tipo de �rea
										tipoArea,

										// Indicador Rede �gua
										indicadorRedeAgua,

										// Indicador Rede Esgoto
										indicadorRedeEsgoto,

										// Campos que ficam em branco para serem setados
										// ap�s o construtor, evitando, assim, que ocorra
										// erros
										// devido ao fato de alguns objetos estarem nulos.
										// Esses campos s�o: bacia, distrito operacional,
										// setor censit�rio e zeis
										"", "", "", "", "",

										// Rota
										quadraNova.getRota().getCodigo().toString(),

										// Indicador Uso
										quadraNova.getIndicadorUso().toString(),
										
										//Indicador de Face da Quadra
										indicadorQuadraFace,
										
										//Id da Face da Quadra
										"" + numeroQuadraFace,
										
										//Bacia da Quadra Face
										baciaQuadraFace,
										
										//Sistems Esgoto da Quadra Face
										redeEsgoto,
										
										//Id Quadra
										""+ idQuadra,
										
										//Rede de Agua da Quadra Face
										redeAgua,
										
										//Distrito Operacional da Quadra Face
										distritoOperacionalQuadraFace,
										
										//Descricao Sistema Esgoto Quadra Face
										descricaoSistemaEsgotoQuadraFace
										);

								// Bacia
								if (quadraNova.getBacia() != null) {
									relatorioBean.setSistemaEsgoto(quadraNova.getBacia()
											.getSistemaEsgoto().getDescricao());
									relatorioBean
											.setBacia(quadraNova.getBacia().getDescricao());
								}

								// Distrito Operacional
								if (quadraNova.getDistritoOperacional() != null) {
									relatorioBean.setDistritoOperacional(quadraNova
											.getDistritoOperacional().getDescricaoAbreviada());
								}

								// Setor Censit�rio
								if (quadraNova.getIbgeSetorCensitario() != null) {
									relatorioBean.setSetorCensitario(quadraNova
											.getIbgeSetorCensitario().getDescricao());
								}

								// Zeis
								if (quadraNova.getZeis() != null) {
									relatorioBean.setZeis(quadraNova.getZeis()
											.getDescricaoAbreviada());
								}
								// adiciona o bean a cole��o
								relatorioBeans.add(relatorioBean);
							}
						} else {
							relatorioBeans.add(relatorioBean);
						}
					} else {
						relatorioBeans.add(relatorioBean);
					}
			}
		}
		// Par�metros do relat�rio
		Map parametros = new HashMap();

		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("quadra", quadraParametros.getNumeroQuadra() == -1 ? ""
				: "" + quadraParametros.getNumeroQuadra());

		parametros.put("idLocalidade", quadraParametros.getSetorComercial()
				.getLocalidade().getId() == null ? "" : ""
				+ quadraParametros.getSetorComercial().getLocalidade().getId());

		parametros.put("nomeLocalidade", quadraParametros.getSetorComercial()
				.getLocalidade().getDescricao());

		parametros.put("idSetorComercial", quadraParametros.getSetorComercial()
				.getCodigo() == 0 ? "" : ""
				+ quadraParametros.getSetorComercial().getCodigo());

		parametros.put("nomeSetorComercial", quadraParametros
				.getSetorComercial().getDescricao());
		
		if (quadraParametros.getRota() != null) {
			parametros.put("codigoRota", quadraParametros.getRota().getCodigo().toString());
		} else {
			parametros.put("codigoRota", "");
		}

		String indicadorUso = "";

		if (quadraParametros.getIndicadorUso() != null
				&& !quadraParametros.getIndicadorUso().equals("")) {
			if (quadraParametros.getIndicadorUso().equals(new Short("1"))) {
				indicadorUso = "Ativo";
			} else if (quadraParametros.getIndicadorUso()
					.equals(new Short("2"))) {
				indicadorUso = "Inativo";
			}
		}

		parametros.put("indicadorUso", indicadorUso);

		// cria uma inst�ncia do dataSource do relat�rio

		RelatorioDataSource ds = new RelatorioDataSource((List) relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_QUADRA_MANTER, parametros, ds,
				tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.MANTER_QUADRA,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		}
		// ------------------------------------

		// retorna o relat�rio gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		retorno = Fachada.getInstancia().totalRegistrosPesquisa(
				(FiltroQuadra) getParametro("filtroQuadra"),
				Quadra.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterQuadra", this);
	}

}
