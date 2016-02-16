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
package gcom.relatorio.faturamento.conta;
import gcom.batch.Relatorio;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.ComparatorChain;



/**
 * [UC] 
 * @author Flavio Cordeiro
 * @date 14/02/2007
 */

public class RelatorioContasCanceladas extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	
	private Map mapTotalAnos = null;
	
	public RelatorioContasCanceladas(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_CONTAS_CANCELADAS);
	}
	
	@Deprecated
	public RelatorioContasCanceladas() {
		super(null, "");
	}
	
	//classe tempor�ria criada para o totalizador geral dos anos. 
	private class TotaisAno{
		private String ano; 
		private int contadorContas = 0;		
		private BigDecimal sumValorCanceladas = new BigDecimal("0");		
		
		public TotaisAno(){}
		
		public String getAno() {
			return ano;
		}
		public void setAno(String ano) {
			this.ano = ano;
		}
		public int getContadorContas() {
			return contadorContas;
		}
		public void setContadorContas(int contadorContas) {
			this.contadorContas = contadorContas;
		}
		public BigDecimal getSumValorCanceladas() {
			return sumValorCanceladas;
		}
		public void setSumValorCanceladas(BigDecimal sumValorCanceladas) {
			this.sumValorCanceladas = sumValorCanceladas;
		}						
	}
	
	
	private Collection inicializarBeanRelatorio(
			Collection colecaoDados) {
		
		Fachada fachada = Fachada.getInstancia();
		
		mapTotalAnos = new HashMap();
		
		Collection retorno = new ArrayList();

		Iterator iter = colecaoDados.iterator();
		
		RelatorioContasCanceladasBean relatorioContasCanceladasBean = null;
		
		while (iter.hasNext()) {
			
			RelatorioContasCanceladasRetificadasHelper rel = (RelatorioContasCanceladasRetificadasHelper) iter.next();

			Localidade localidade = null;
			
			if(rel.getIdLocalidade() != null){
				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, rel.getIdLocalidade()));
				Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
				localidade = (Localidade)colecaoLocalidade.iterator().next();
			}
			
			relatorioContasCanceladasBean = 
					new RelatorioContasCanceladasBean(
							rel.getCancelamento(),
							rel.getIdResponsavel(),
							rel.getEndereco(),
							rel.getReferencia(),
							rel.getIdMotivo(),
							rel.getValorCancelado(),
							rel.getIdRA(),
							rel.getInscricaoImovel(),
							rel.getIdLocalidade(),
							localidade != null ? localidade.getDescricao() : null,
						    rel.getMatricula() );
			
			relatorioContasCanceladasBean.setCodigoSetorComercial(rel.getCodigoSetorComercial());
			relatorioContasCanceladasBean.setUnidadeNegocio(rel.getUnidadeNegocio());
			relatorioContasCanceladasBean.setIdUnidadeNegocio(rel.getIdUnidadeNegocio());
			relatorioContasCanceladasBean.setGerenciaRegional(rel.getGerenciaRegional());
			relatorioContasCanceladasBean.setIdGerenciaRegional(rel.getIdGerenciaRegional());
			relatorioContasCanceladasBean.setIdGrupo(rel.getGrupo());			
			
			retorno.add(relatorioContasCanceladasBean);
			
			//Preenche os valores dos totais do anos
			String ano = rel.getReferencia().substring(3);
					
			if(mapTotalAnos.containsKey(ano)){
				TotaisAno totaisAno = (TotaisAno) mapTotalAnos.get(ano);
				totaisAno.setContadorContas(totaisAno.getContadorContas() + 1);
				totaisAno.setSumValorCanceladas(totaisAno.getSumValorCanceladas().add(new BigDecimal(rel.getValorCancelado())));				
				
				mapTotalAnos.put(ano, totaisAno);				
			}else{
				TotaisAno totaisAno = new TotaisAno();
				
				totaisAno.setAno(ano);
				totaisAno.setContadorContas(1);
				totaisAno.setSumValorCanceladas( new BigDecimal(rel.getValorCancelado()) );				
								
				mapTotalAnos.put(ano, totaisAno);
			}
		}
		
		return retorno;
	}

	/**
	 * M�todo que executa a tarefa
	 * 
	 * @return Object
	 * 
	 */
	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		Fachada fachada = Fachada.getInstancia();

		String mesAno = (String) getParametro("mesAno");
		String relatorioTipo = (String) getParametro("relatorioTipo");
		String ordenacaoTipo = (String) getParametro("ordenacaoTipo");

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("imagemConta", sistemaParametro.getImagemConta());
		parametros.put("mesAno", mesAno);

		RelatorioContasCanceladasRetificadasHelper helper = (RelatorioContasCanceladasRetificadasHelper) getParametro("relatorioContasCanceladasRetificadasHelper");

		parametros.put("grupo", helper.getGrupo());

		Collection colecaoContasCanceladas = fachada
				.gerarRelatorioContasCanceladas(helper);
		
		Collection colecaoBean = new ArrayList();
		//************************************************
		// CRC5059
		// Por: Ivan Sergio
		// 08/09/2010
		// Verifica se a consulta retornou algum dado.
		//************************************************
		if (colecaoContasCanceladas != null && !colecaoContasCanceladas.isEmpty()) {

			colecaoBean = this
					.inicializarBeanRelatorio(colecaoContasCanceladas);
	
			ArrayList sortFields = new ArrayList();
	
			BeanComparator ordenaGerenciaRegional = new BeanComparator(
					"idGerenciaRegional", new Comparator() {
						public int compare(Object o1, Object o2) {
							Integer i = new Integer((String) o1);
							Integer i2 = new Integer((String) o2);
							return i.compareTo(i2);
						}
					});
	
			sortFields.add(ordenaGerenciaRegional);
	
			BeanComparator ordenaUnidadeNegocio = new BeanComparator(
					"idUnidadeNegocio", new Comparator() {
						public int compare(Object o1, Object o2) {
							Integer i = new Integer((String) o1);
							Integer i2 = new Integer((String) o2);
							return i.compareTo(i2);
						}
					});
	
			sortFields.add(ordenaUnidadeNegocio);
	
			BeanComparator ordenaLocalidade = new BeanComparator("idLocalidade",
					new Comparator() {
						public int compare(Object o1, Object o2) {
							Integer i = Integer.parseInt((String) o1);
							Integer i2 = Integer.parseInt((String) o2);
							return i.compareTo(i2);
						}
					});
	
			sortFields.add(ordenaLocalidade);
	
			//trecho que implementa os Totalizadores dos anos
			List colecaoTotais = null;
	
			if (mapTotalAnos != null && mapTotalAnos.size() != 0) {
	
				RelatorioContasCanceladasBean relatorioContasCanceladasBean = null;
				colecaoTotais = new ArrayList();
	
				Iterator iterator = mapTotalAnos.keySet().iterator();
	
				while (iterator.hasNext()) {
					relatorioContasCanceladasBean = new RelatorioContasCanceladasBean();
					TotaisAno totaisAno = (TotaisAno) mapTotalAnos
							.get((String) iterator.next());
	
					relatorioContasCanceladasBean.setAno(totaisAno.getAno());
					relatorioContasCanceladasBean.setQuantidadeContasAno(totaisAno
							.getContadorContas());
					relatorioContasCanceladasBean.setValorCanceladasAno(totaisAno
							.getSumValorCanceladas());
	
					colecaoTotais.add(relatorioContasCanceladasBean);
				}
			}
	
			// ORDENA COLE��O POR ANO MES DE REFERENCIA DA CONTA
			Collections.sort(colecaoTotais, new Comparator() {
				public int compare(Object left, Object right) {
					RelatorioContasCanceladasBean leftKey = (RelatorioContasCanceladasBean) left;
					RelatorioContasCanceladasBean rightKey = (RelatorioContasCanceladasBean) right;
					return leftKey.getAno().compareTo(rightKey.getAno());
				}
			});
	
			RelatorioDataSource dataSourceAno = new RelatorioDataSource(
					colecaoTotais);
	
			parametros.put("DataSourceAno", dataSourceAno);
	
			if (relatorioTipo.equalsIgnoreCase("sintetico")) {
	
				BeanComparator ordenaReferencia = new BeanComparator("referencia",
						new Comparator() {
							public int compare(Object o1, Object o2) {
								Integer i = Integer
										.parseInt(Util
												.formatarMesAnoParaAnoMesSemBarra((String) o1));
								Integer i2 = Integer
										.parseInt(Util
												.formatarMesAnoParaAnoMesSemBarra((String) o2));
								return i.compareTo(i2);
							}
						});
	
				sortFields.add(ordenaReferencia);
	
				ComparatorChain multiSort = new ComparatorChain(sortFields);
				Collections.sort((ArrayList) colecaoBean, multiSort);
	
				RelatorioDataSource ds = new RelatorioDataSource(
						(java.util.List) colecaoBean);
				retorno = this.gerarRelatorio(
						ConstantesRelatorios.RELATORIO_CONTAS_CANCELADAS_SINTETICO,
						parametros, ds, tipoFormatoRelatorio);
			} else if (relatorioTipo.equalsIgnoreCase("analitico")) {
	
				if (ordenacaoTipo != null) {
	
					if (ordenacaoTipo.equals("2")) {
	
						BeanComparator ordenaData = new BeanComparator(
								"cancelamento", new Comparator() {
									public int compare(Object o1, Object o2) {
										String i = (String) o1;
										String i2 = (String) o2;
										Date i3 = Util.converteStringParaDate(i);
										Date i4 = Util.converteStringParaDate(i2);
										i = Util.formatarDataSemBarra(i3);
										i2 = Util.formatarDataSemBarra(i4);
	
										return i.compareTo(i2);
									}
								});
	
						sortFields.add(ordenaData);
					} else if (ordenacaoTipo.equals("1")) {
						BeanComparator ordenaInscricao = new BeanComparator(
								"inscricao", new Comparator() {
									public int compare(Object o1, Object o2) {
										String i = (String) o1;
	
										if (i == null) {
											i = "";
										}
										String i2 = (String) o2;
										if (i2 == null) {
											i2 = "";
										}
										return i.compareTo(i2);
									}
								});
						sortFields.add(ordenaInscricao);
					}
	
				}
	
				BeanComparator ordenaReferencia = new BeanComparator("referencia",
						new Comparator() {
							public int compare(Object o1, Object o2) {
								Integer i = Integer
										.parseInt(Util
												.formatarMesAnoParaAnoMesSemBarra((String) o1));
								Integer i2 = Integer
										.parseInt(Util
												.formatarMesAnoParaAnoMesSemBarra((String) o2));
								return i.compareTo(i2);
							}
						});
	
				sortFields.add(ordenaReferencia);
	
				ComparatorChain multiSort = new ComparatorChain(sortFields);
				Collections.sort((ArrayList) colecaoBean, multiSort);
				RelatorioDataSource ds = new RelatorioDataSource(
						(java.util.List) colecaoBean);
	
				retorno = this.gerarRelatorio(
						ConstantesRelatorios.RELATORIO_CONTAS_CANCELADAS,
						parametros, ds, tipoFormatoRelatorio);
			}
		}else {
			//************************************************
			// Verifica se o relatorio est� sendo executado
			// como batch ou online;
			//************************************************
			if (idFuncionalidadeIniciada.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				throw new RelatorioVazioException("atencao.relatorio.vazio");
			}else {
				//************************************************
				// Gera o Relat�rio em Branco
				//************************************************
				RelatorioDataSource ds = new RelatorioDataSource((java.util.List) colecaoBean);
				retorno = this.gerarRelatorio(
						ConstantesRelatorios.RELATORIO_CONTAS_RETIFICADAS,
						parametros, ds, tipoFormatoRelatorio);
			}
		}

		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			if (relatorioTipo.equalsIgnoreCase("sintetico")) {
				persistirRelatorioConcluido(retorno,
						Relatorio.RELATORIO_CONTAS_CANCELADAS_SINTETICO,
						idFuncionalidadeIniciada);
			} else if (relatorioTipo.equalsIgnoreCase("analitico")) {
				persistirRelatorioConcluido(retorno,
						Relatorio.RELATORIO_CONTAS_CANCELADAS,
						idFuncionalidadeIniciada);
			}
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
		
		RelatorioContasCanceladasRetificadasHelper helper = 
			(RelatorioContasCanceladasRetificadasHelper) 
				getParametro("relatorioContasCanceladasRetificadasHelper");
		
		Fachada fachada = Fachada.getInstancia();
		
		int retorno = 
			fachada.pesquisarQuantidadeContasCanceladasOuRetificadas(helper,1);
		
		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioContasCanceladas", this);
	}
	
}
