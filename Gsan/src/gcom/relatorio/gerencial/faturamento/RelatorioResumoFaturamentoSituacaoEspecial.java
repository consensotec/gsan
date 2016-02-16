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
package gcom.relatorio.gerencial.faturamento;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gerencial.cobranca.ResumoCobrancaSituacaoEspecialConsultaFinalHelper;
import gcom.gerencial.cobranca.ResumoCobrancaSituacaoEspecialConsultaGerenciaRegHelper;
import gcom.gerencial.cobranca.ResumoCobrancaSituacaoEspecialConsultaLocalidadeHelper;
import gcom.gerencial.cobranca.ResumoCobrancaSituacaoEspecialConsultaMotivoHelper;
import gcom.gerencial.cobranca.ResumoCobrancaSituacaoEspecialConsultaSetorComercialHelper;
import gcom.gerencial.cobranca.ResumoCobrancaSituacaoEspecialConsultaSitTipoHelper;
import gcom.gerencial.cobranca.ResumoCobrancaSituacaoEspecialConsultaUnidadeNegHelper;
import gcom.gerencial.faturamento.ResumoFaturamentoSituacaoEspecialConsultaFinalHelper;
import gcom.gerencial.faturamento.ResumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper;
import gcom.gerencial.faturamento.ResumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper;
import gcom.gerencial.faturamento.ResumoFaturamentoSituacaoEspecialConsultaMotivoHelper;
import gcom.gerencial.faturamento.ResumoFaturamentoSituacaoEspecialConsultaSetorComercialHelper;
import gcom.gerencial.faturamento.ResumoFaturamentoSituacaoEspecialConsultaSitTipoHelper;
import gcom.gerencial.faturamento.ResumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper;
import gcom.gerencial.faturamento.bean.ResumoFaturamentoSituacaoEspecialConsultaMotivoAnaliticoHelper;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioResumoFaturamentoSituacaoEspecial extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor da classe RelatorioDadosEconomiaImovel
	 */
	public RelatorioResumoFaturamentoSituacaoEspecial(Usuario usuario) {
		super(
				usuario,
				ConstantesRelatorios.RELATORIO_RESUMO_FATURAMENTO_SITUACAO_ESPECIAL);
	}

	@Deprecated
	public RelatorioResumoFaturamentoSituacaoEspecial() {
		super(null, "");
	}

	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		String mesAnoReferencia = (String)getParametro("mesAnoReferencia");
		StringBuilder dadosEscrita = new StringBuilder("");

		// valor de retorno
		byte[] retorno = null;

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioResumoFaturamentoSituacaoEspecialBean relatorioBean = null;

		String nomeRelatorio = (String) getParametro("nomeRelatorio");
		
		
		String numeroRelatorio = (String) getParametro("numeroRelatorio");
		
		if (nomeRelatorio.equalsIgnoreCase("faturamento")) {
			
			ResumoFaturamentoSituacaoEspecialConsultaFinalHelper resumoFaturamentoSituacaoEspecialConsultaFinalHelper = (ResumoFaturamentoSituacaoEspecialConsultaFinalHelper) getParametro("resumoFaturamentoSituacaoEspecialConsultaFinalHelper");

			for (ResumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper helperGerencia : resumoFaturamentoSituacaoEspecialConsultaFinalHelper.getResumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper()) {
				
				for (ResumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper helperUnidadeNegocio :  helperGerencia.getResumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper()) {

					for (ResumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper helperLocalidade :  helperUnidadeNegocio.getResumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper()) {

						for (ResumoFaturamentoSituacaoEspecialConsultaSetorComercialHelper helperSetorComercial :  helperLocalidade.getResumoFaturamentoSituacaoEspecialConsultaSetorComercialHelper()) {
						
							for (ResumoFaturamentoSituacaoEspecialConsultaSitTipoHelper helperSitTipo :  helperSetorComercial.getResumoFaturamentoSituacaoEspecialConsultaSitTipoHelper()) {

								for (ResumoFaturamentoSituacaoEspecialConsultaMotivoHelper helperMotivo :  helperSitTipo.getResumoFaturamentoSituacaoEspecialConsultaMotivoHelper()) {

									if(tipoFormatoRelatorio == TarefaRelatorio.TIPO_TXT || tipoFormatoRelatorio == TarefaRelatorio.TIPO_CSV ){
										if (helperMotivo.getResumoFaturamentoSituacaoEspecialConsultaMotivoAnaliticoHelper() != null) {
											for (ResumoFaturamentoSituacaoEspecialConsultaMotivoAnaliticoHelper dadosAnaliticos : helperMotivo.getResumoFaturamentoSituacaoEspecialConsultaMotivoAnaliticoHelper()) {

												// 7.1.1.1
												dadosEscrita.append(helperGerencia.getIdGerenciaRegional());
												dadosEscrita.append(";");

												// 7.1.1.2
												dadosEscrita.append(helperGerencia.getGerenciaRegionalDescricao());
												dadosEscrita.append(";");

												// 7.1.1.3. Id da Unidade
												// Negocio
												dadosEscrita.append(helperUnidadeNegocio.getIdUnidadeNegocio().toString());
												dadosEscrita.append(";");

												// 7.1.1.4. C�digo da Localidade
												dadosEscrita.append(helperLocalidade.getIdLocalidade());
												dadosEscrita.append(";");

												// 7.1.1.5. Descri��o da Unidade
												// Negocio
												dadosEscrita.append(helperUnidadeNegocio.getUnidadeNegocioDescricao());
												dadosEscrita.append(";");

												// 7.1.1.6. Descri��o da
												// Localidade
												dadosEscrita.append(helperLocalidade.getLocalidadeDescricao());
												dadosEscrita.append(";");

												// 7.1.1.7. C�digo do Setor
												// Comercial
												dadosEscrita.append(helperSetorComercial.getCodigoSetorComercial());
												dadosEscrita.append(";");

												// 7.1.1.8 Id da Situa��o
												dadosEscrita.append(helperSitTipo.getIdSituacaoTipo());
												dadosEscrita.append(";");

												// 7.1.1.9 Descri��o da Situa��o
												dadosEscrita.append(helperSitTipo.getSituacaoTipoDescricao());
												dadosEscrita.append(";");

												// 7.1.1.10 Id do Motivo
												dadosEscrita.append(helperMotivo.getIdMotivo());
												dadosEscrita.append(";");

												// 7.1.1.11 Descri��o do Motivo
												dadosEscrita.append(helperMotivo.getMotivoDescricao());
												dadosEscrita.append(";");

												// 7.1.1.11.1
												dadosEscrita.append(dadosAnaliticos.getImovelId().toString());
												dadosEscrita.append(";");
												dadosEscrita.append("\n");
											}
										}
									
										
									}else{
										// In�cio da Constru��o do objeto
										// RelatorioDadosEconomiaImovelBean
										// para ser colocado no relat�rio
										relatorioBean = new RelatorioResumoFaturamentoSituacaoEspecialBean(

												// Faturamento Estimado Geral
												resumoFaturamentoSituacaoEspecialConsultaFinalHelper
														.getTotalFatEstimadoGeral() == null ? ""
														: Util
																.formatarMoedaReal(resumoFaturamentoSituacaoEspecialConsultaFinalHelper
																		.getTotalFatEstimadoGeral()),

												//Faturamento Estimado Ger�ncia
												helperGerencia
														.getTotalFatEstimadoGerencia() == null ? ""
														: Util
																.formatarMoedaReal(helperGerencia
																		.getTotalFatEstimadoGerencia()),
																		
												// Faturamento Estimado Unidade Neg�cio
												helperUnidadeNegocio
														.getTotalFatEstimadoUnidadeNegocio() == null ? ""
														: Util
																.formatarMoedaReal(helperUnidadeNegocio
																		.getTotalFatEstimadoUnidadeNegocio()),
																		
												// Faturamento Estimado Localidade
												helperLocalidade
														.getTotalFatEstimadoLocalidade() == null ? ""
														: Util
																.formatarMoedaReal(helperLocalidade
																		.getTotalFatEstimadoLocalidade()),
																		
												// Faturamento Estimado Setor Comercial
												helperSetorComercial
														.getTotalFatEstimadoSetorComercial() == null ? ""
														: Util
																.formatarMoedaReal(helperSetorComercial
																		.getTotalFatEstimadoSetorComercial()),
																								
												// Faturamento Estimado Motivo
												helperMotivo
														.getFaturamentoEstimado() == null ? ""
														: Util
																.formatarMoedaReal(helperMotivo
																		.getFaturamentoEstimado()),

												// Faturamento Estimado Situacao
												helperSitTipo
														.getTotalFatEstimadoSitTipo() == null ? ""
														: Util
																.formatarMoedaReal(helperSitTipo
																		.getTotalFatEstimadoSitTipo()),

												// Ger�ncia Regional
												helperGerencia
														.getIdGerenciaRegional()
														.toString()
														+ " - "
														+ helperGerencia
																.getGerenciaRegionalDescricao(),
																
												// Unidade Neg�cio
												helperUnidadeNegocio
														.getIdUnidadeNegocio()
														.toString()
														+ " - "
														+ helperUnidadeNegocio
																.getUnidadeNegocioDescricao(),

												// Localidade
												helperLocalidade
														.getIdLocalidade()
														.toString()
														+ " - "
														+ helperLocalidade
																.getLocalidadeDescricao(),
																
												// Setor Comercial
												helperSetorComercial
														.getCodigoSetorComercial()
														.toString()
														+ " - "
														+ helperSetorComercial
																.getSetorComercialDescricao(),

												// Ano M�s Fim
												helperMotivo
														.getFormatarAnoMesParaMesAnoFim(),

												// Ano M�s In�cio
												helperMotivo
														.getFormatarAnoMesParaMesAnoInicio(),

												// Motivo
												helperMotivo
														.getMotivoDescricao(),

												// Percentual Geral
												resumoFaturamentoSituacaoEspecialConsultaFinalHelper
														.getTotalPercentualGeral() == null ? ""
														: Util
																.formatarMoedaReal(resumoFaturamentoSituacaoEspecialConsultaFinalHelper
																		.getTotalPercentualGeral()),

												// Percentual Ger�ncia
												helperGerencia
														.getTotalPercentualGerencia() == null ? ""
														: Util
																.formatarMoedaReal(helperGerencia
																		.getTotalPercentualGerencia()),
																		

												// Percentual Unidade Neg�cio
												helperUnidadeNegocio
														.getTotalPercentualUnidadeNegocio() == null ? ""
														: Util
																.formatarMoedaReal(helperUnidadeNegocio
																		.getTotalPercentualUnidadeNegocio()),

												// Percentual Localidade
												helperLocalidade
														.getTotalPercentualLocalidade() == null ? ""
														: Util
																.formatarMoedaReal(helperLocalidade
																		.getTotalPercentualLocalidade()),
																		
												// Percentual Setor Comercial
												helperSetorComercial
														.getTotalPercentualSetorComercial() == null ? ""
														: Util
																.formatarMoedaReal(helperSetorComercial
																		.getTotalPercentualSetorComercial()),

												// Percentual Motivo
												helperMotivo
														.getPercentual() == null ? ""
														: Util
																.formatarMoedaReal(helperMotivo
																		.getPercentual()),

												// Percentual Situa��o
												helperSitTipo
														.getTotalPercentualSitTipo() == null ? ""
														: Util
																.formatarMoedaReal(helperSitTipo
																		.getTotalPercentualSitTipo()),

												// Quantidade Im�vel Geral
												resumoFaturamentoSituacaoEspecialConsultaFinalHelper
														.getTotalQtLigacoesGeral()
														.toString(),

												// Quantidade Im�vel Ger�ncia
												helperGerencia
														.getTotalQtLigacoesGerencia()
														.toString(),
														
												// Quantidade Im�vel Unidade Neg�cio
												helperUnidadeNegocio
														.getTotalQtLigacoesUnidadeNegocio()
														.toString(),

												// Quantidade Im�vel Localidade
												helperLocalidade
														.getTotalQtLigacoesLocalidade()
														.toString(),
														
												// Quantidade Im�vel Setor Comercial
												helperSetorComercial
														.getTotalQtLigacoesSetorComercial()
														.toString(),
														
												// Quantidade Im�vel Motivo
												helperMotivo
														.getQtLigacoes() == null ? "0"
														: helperMotivo
																.getQtLigacoes()
																.toString(),

												// Quantidade Im�vel Situa��o
												helperSitTipo
														.getTotalQtLigacoesSitTipo()
														.toString(),

												// Quantidade Paralisada Geral
												resumoFaturamentoSituacaoEspecialConsultaFinalHelper
														.getTotalGeral().toString(),

												// Quantidade Paralisada Ger�ncia
												helperGerencia
														.getTotalGerenciaRegional()
														.toString(),
														
												// Quantidade Paralisada Unidade Neg�cio
												helperUnidadeNegocio
														.getTotalUnidadeNegocio()
														.toString(),

												// Quantidade Paralisada Motivo
												helperMotivo
														.getQtParalisada()
														.toString(),

												// Quantidade Paralisada Situa��o
												helperSitTipo
														.getTotalSituacaoTipo()
														.toString(),

												// Quantidade Paralisada Localidade
												helperLocalidade
														.getTotalLocalidade()
														.toString(),
														
												// Quantidade Paralisada Setor Comercial
												helperSetorComercial
														.getTotalSetorComercial()
														.toString(),

												// Situa��o
												helperSitTipo
														.getSituacaoTipoDescricao());
										
										ArrayList colecaoDadosAnalitico = new ArrayList();
										RelatorioResumoFaturamentoSituacaoEspecialAnaliticoBean rrfseab = null;
									    
										if(helperMotivo.getResumoFaturamentoSituacaoEspecialConsultaMotivoAnaliticoHelper()!=null){
									    	 for(ResumoFaturamentoSituacaoEspecialConsultaMotivoAnaliticoHelper dadosAnaliticos : helperMotivo.getResumoFaturamentoSituacaoEspecialConsultaMotivoAnaliticoHelper()){
									    		 	rrfseab = new RelatorioResumoFaturamentoSituacaoEspecialAnaliticoBean();
									    		 	rrfseab.setImovelId(dadosAnaliticos.getImovelId().toString());
									    		 	rrfseab.setEndereco(dadosAnaliticos.getEndereco());
									    		 	colecaoDadosAnalitico.add(rrfseab);
									    		 	
									    		 	
									    	 }
									     }
										relatorioBean.setArrayRelatorioImoveisBean(colecaoDadosAnalitico);
										relatorioBean.setArrayJRImoveis(new JRBeanCollectionDataSource(colecaoDadosAnalitico));
										
										// Fim da Constru��o do objeto
										// RelatorioDadosEconomiaImovelBean
										// para ser colocado no relat�rio

										// adiciona o bean a cole��o
										relatorioBeans.add(relatorioBean);
									}
								}
							}
						}
					}
				}
			}

		} else {
			
			ResumoCobrancaSituacaoEspecialConsultaFinalHelper resumoCobrancaSituacaoEspecialConsultaFinalHelper = (ResumoCobrancaSituacaoEspecialConsultaFinalHelper) getParametro("resumoCobrancaSituacaoEspecialConsultaFinalHelper");

//			Iterator resumoCobrancaSituacaoEspecialConsultaGerenciaHelperIterator = resumoCobrancaSituacaoEspecialConsultaFinalHelper
//					.getResumoCobrancaSituacaoEspecialConsultaGerenciaRegHelper()
//					.iterator();
//
//			while (resumoCobrancaSituacaoEspecialConsultaGerenciaHelperIterator
//					.hasNext()) {
//
//				ResumoCobrancaSituacaoEspecialConsultaGerenciaRegHelper resumoCobrancaSituacaoEspecialConsultaGerenciaRegHelper = (ResumoCobrancaSituacaoEspecialConsultaGerenciaRegHelper) resumoCobrancaSituacaoEspecialConsultaGerenciaHelperIterator
//						.next();
//
//				Iterator resumoCobrancaSituacaoEspecialConsultaLocalidadeHelperIterator = resumoCobrancaSituacaoEspecialConsultaGerenciaRegHelper
//						.getResumoCobrancaSituacaoEspecialConsultaLocalidadeHelper()
//						.iterator();
//
//				while (resumoCobrancaSituacaoEspecialConsultaLocalidadeHelperIterator
//						.hasNext()) {
//
//					ResumoCobrancaSituacaoEspecialConsultaLocalidadeHelper resumoCobrancaSituacaoEspecialConsultaLocalidadeHelper = (ResumoCobrancaSituacaoEspecialConsultaLocalidadeHelper) resumoCobrancaSituacaoEspecialConsultaLocalidadeHelperIterator
//							.next();
//
//					Iterator resumoCobrancaSituacaoEspecialConsultaSitTipoHelperIterator = resumoCobrancaSituacaoEspecialConsultaLocalidadeHelper
//							.getResumoCobrancaSituacaoEspecialConsultaSitTipoHelper()
//							.iterator();
//
//					while (resumoCobrancaSituacaoEspecialConsultaSitTipoHelperIterator
//							.hasNext()) {
//
//						ResumoCobrancaSituacaoEspecialConsultaSitTipoHelper resumoCobrancaSituacaoEspecialConsultaSitTipoHelper = (ResumoCobrancaSituacaoEspecialConsultaSitTipoHelper) resumoCobrancaSituacaoEspecialConsultaSitTipoHelperIterator
//								.next();
//
//						Iterator resumoCobrancaSituacaoEspecialConsultaMotivoHelperIterator = resumoCobrancaSituacaoEspecialConsultaSitTipoHelper
//								.getResumoCobrancaSituacaoEspecialConsultaMotivoHelper()
//								.iterator();
//
//						while (resumoCobrancaSituacaoEspecialConsultaMotivoHelperIterator
//								.hasNext()) {
//
//							ResumoCobrancaSituacaoEspecialConsultaMotivoHelper resumoCobrancaSituacaoEspecialConsultaMotivoHelper = (ResumoCobrancaSituacaoEspecialConsultaMotivoHelper) resumoCobrancaSituacaoEspecialConsultaMotivoHelperIterator
//									.next();
			
			for (ResumoCobrancaSituacaoEspecialConsultaGerenciaRegHelper helperGerencia : resumoCobrancaSituacaoEspecialConsultaFinalHelper.getResumoCobrancaSituacaoEspecialConsultaGerenciaRegHelper()) {
				
				for (ResumoCobrancaSituacaoEspecialConsultaUnidadeNegHelper helperUnidadeNegocio :  helperGerencia.getResumoCobrancaSituacaoEspecialConsultaUnidadeNegHelper()) {

					for (ResumoCobrancaSituacaoEspecialConsultaLocalidadeHelper helperLocalidade :  helperUnidadeNegocio.getResumoCobrancaSituacaoEspecialConsultaLocalidadeHelper()) {

						for (ResumoCobrancaSituacaoEspecialConsultaSetorComercialHelper helperSetorComercial :  helperLocalidade.getResumoCobrancaSituacaoEspecialConsultaSetorComercialHelper()) {
						
							for (ResumoCobrancaSituacaoEspecialConsultaSitTipoHelper helperSitTipo :  helperSetorComercial.getResumoCobrancaSituacaoEspecialConsultaSitTipoHelper()) {

								for (ResumoCobrancaSituacaoEspecialConsultaMotivoHelper helperMotivo :  helperSitTipo.getResumoCobrancaSituacaoEspecialConsultaMotivoHelper()) {

							// In�cio da Constru��o do objeto
							// RelatorioDadosEconomiaImovelBean
							// para ser colocado no relat�rio
							relatorioBean = new RelatorioResumoFaturamentoSituacaoEspecialBean(
									// Faturamento Estimado Geral
									resumoCobrancaSituacaoEspecialConsultaFinalHelper
											.getTotalFatEstimadoGeral() == null ? ""
											: Util
													.formatarMoedaReal(resumoCobrancaSituacaoEspecialConsultaFinalHelper
															.getTotalFatEstimadoGeral()),

									//Faturamento Estimado Ger�ncia
									helperGerencia
											.getTotalFatEstimadoGerencia() == null ? ""
											: Util
													.formatarMoedaReal(helperGerencia
															.getTotalFatEstimadoGerencia()),
															
									// Faturamento Estimado Unidade Neg�cio
									helperUnidadeNegocio
											.getTotalFatEstimadoUnidadeNegocio() == null ? ""
											: Util
													.formatarMoedaReal(helperUnidadeNegocio
															.getTotalFatEstimadoUnidadeNegocio()),
															
									// Faturamento Estimado Localidade
									helperLocalidade
											.getTotalFatEstimadoLocalidade() == null ? ""
											: Util
													.formatarMoedaReal(helperLocalidade
															.getTotalFatEstimadoLocalidade()),
															
									// Faturamento Estimado Setor Comercial
									helperSetorComercial
											.getTotalFatEstimadoSetorComercial() == null ? ""
											: Util
													.formatarMoedaReal(helperSetorComercial
															.getTotalFatEstimadoSetorComercial()),
																					
									// Faturamento Estimado Motivo
									helperMotivo
											.getFaturamentoEstimado() == null ? ""
											: Util
													.formatarMoedaReal(helperMotivo
															.getFaturamentoEstimado()),

									// Faturamento Estimado Situacao
									helperSitTipo
											.getTotalFatEstimadoSitTipo() == null ? ""
											: Util
													.formatarMoedaReal(helperSitTipo
															.getTotalFatEstimadoSitTipo()),

									// Ger�ncia Regional
									helperGerencia
											.getIdGerenciaRegional()
											.toString()
											+ " - "
											+ helperGerencia
													.getGerenciaRegionalDescricao(),
													
									// Unidade Neg�cio
									helperUnidadeNegocio
											.getIdUnidadeNegocio()
											.toString()
											+ " - "
											+ helperUnidadeNegocio
													.getUnidadeNegocioDescricao(),

									// Localidade
									helperLocalidade
											.getIdLocalidade()
											.toString()
											+ " - "
											+ helperLocalidade
													.getLocalidadeDescricao(),
													
									// Setor Comercial
									helperSetorComercial
											.getCodigoSetorComercial()
											.toString()
											+ " - "
											+ helperSetorComercial
													.getSetorComercialDescricao(),

									// Ano M�s Fim
									helperMotivo
											.getFormatarAnoMesParaMesAnoFim(),

									// Ano M�s In�cio
									helperMotivo
											.getFormatarAnoMesParaMesAnoInicio(),

									// Motivo
									helperMotivo
											.getMotivoDescricao(),

									// Percentual Geral
											resumoCobrancaSituacaoEspecialConsultaFinalHelper
											.getTotalPercentualGeral() == null ? ""
											: Util
													.formatarMoedaReal(resumoCobrancaSituacaoEspecialConsultaFinalHelper
															.getTotalPercentualGeral()),

									// Percentual Ger�ncia
									helperGerencia
											.getTotalPercentualGerencia() == null ? ""
											: Util
													.formatarMoedaReal(helperGerencia
															.getTotalPercentualGerencia()),
															

									// Percentual Unidade Neg�cio
									helperUnidadeNegocio
											.getTotalPercentualUnidadeNegocio() == null ? ""
											: Util
													.formatarMoedaReal(helperUnidadeNegocio
															.getTotalPercentualUnidadeNegocio()),

									// Percentual Localidade
									helperLocalidade
											.getTotalPercentualLocalidade() == null ? ""
											: Util
													.formatarMoedaReal(helperLocalidade
															.getTotalPercentualLocalidade()),
															
									// Percentual Setor Comercial
									helperSetorComercial
											.getTotalPercentualSetorComercial() == null ? ""
											: Util
													.formatarMoedaReal(helperSetorComercial
															.getTotalPercentualSetorComercial()),

									// Percentual Motivo
									helperMotivo
											.getPercentual() == null ? ""
											: Util
													.formatarMoedaReal(helperMotivo
															.getPercentual()),

									// Percentual Situa��o
									helperSitTipo
											.getTotalPercentualSitTipo() == null ? ""
											: Util
													.formatarMoedaReal(helperSitTipo
															.getTotalPercentualSitTipo()),

									// Quantidade Im�vel Geral
									resumoCobrancaSituacaoEspecialConsultaFinalHelper
											.getTotalQtLigacoesGeral()
											.toString(),

									// Quantidade Im�vel Ger�ncia
									helperGerencia
											.getTotalQtLigacoesGerencia()
											.toString(),
											
									// Quantidade Im�vel Unidade Neg�cio
									helperUnidadeNegocio
											.getTotalQtLigacoesUnidadeNegocio()
											.toString(),

									// Quantidade Im�vel Localidade
									helperLocalidade
											.getTotalQtLigacoesLocalidade()
											.toString(),
											
									// Quantidade Im�vel Setor Comercial
									helperSetorComercial
											.getTotalQtLigacoesSetorComercial()
											.toString(),
											
									// Quantidade Im�vel Motivo
									helperMotivo
											.getQtLigacoes() == null ? "0"
											: helperMotivo
													.getQtLigacoes()
													.toString(),

									// Quantidade Im�vel Situa��o
									helperSitTipo
											.getTotalQtLigacoesSitTipo()
											.toString(),

									// Quantidade Paralisada Geral
									resumoCobrancaSituacaoEspecialConsultaFinalHelper
											.getTotalGeral().toString(),

									// Quantidade Paralisada Ger�ncia
									helperGerencia
											.getTotalGerenciaRegional()
											.toString(),
											
									// Quantidade Paralisada Unidade Neg�cio
									helperUnidadeNegocio
											.getTotalUnidadeNegocio()
											.toString(),

									// Quantidade Paralisada Motivo
									helperMotivo
											.getQtParalisada()
											.toString(),

									// Quantidade Paralisada Situa��o
									helperSitTipo
											.getTotalSituacaoTipo()
											.toString(),

									// Quantidade Paralisada Localidade
									helperLocalidade
											.getTotalLocalidade()
											.toString(),
											
									// Quantidade Paralisada Setor Comercial
									helperSetorComercial
											.getTotalSetorComercial()
											.toString(),

									// Situa��o
									helperSitTipo
											.getSituacaoTipoDescricao());
//
//									// Faturamento Estimado Geral
//									resumoCobrancaSituacaoEspecialConsultaFinalHelper
//											.getTotalFatEstimadoGeral() == null ? ""
//											: Util
//													.formatarMoedaReal(resumoCobrancaSituacaoEspecialConsultaFinalHelper
//															.getTotalFatEstimadoGeral()),
//
//									// //Faturamento Estimado Gerencia
//									helperGerencia
//											.getTotalFatEstimadoGerencia() == null ? ""
//											: Util
//													.formatarMoedaReal(helperGerencia
//															.getTotalFatEstimadoGerencia()),
//
//									// Faturamento Estimado Motivo
//									helperMotivo
//											.getFaturamentoEstimado() == null ? ""
//											: Util
//													.formatarMoedaReal(helperMotivo
//															.getFaturamentoEstimado()),
//
//									// Faturamento Estimado Situacao
//									helperSitTipo
//											.getTotalFatEstimadoSitTipo() == null ? ""
//											: Util
//													.formatarMoedaReal(helperSitTipo
//															.getTotalFatEstimadoSitTipo()),
//
//									// Faturamento Estimado Situacao
//									helperSitTipo
//											.getTotalFatEstimadoSitTipo() == null ? ""
//											: Util
//													.formatarMoedaReal(helperSitTipo
//															.getTotalFatEstimadoSitTipo()),
//
//									// GerenciaRegional
//									helperGerencia
//											.getIdGerenciaRegional().toString()
//											+ " - "
//											+ helperGerencia
//													.getGerenciaRegionalDescricao(),
//
//									// Localidade
//									helperLocalidade
//											.getIdLocalidade().toString()
//											+ " - "
//											+ helperLocalidade
//													.getLocalidadeDescricao(),
//
//									// Ano M�s Fim
//									helperMotivo
//											.getFormatarAnoMesParaMesAnoFim(),
//
//									// Ano M�s In�cio
//									helperMotivo
//											.getFormatarAnoMesParaMesAnoInicio(),
//
//									// Motivo
//									helperMotivo
//											.getMotivoDescricao(),
//
//									// Percentual Geral
//									resumoCobrancaSituacaoEspecialConsultaFinalHelper
//											.getTotalPercentualGeral() == null ? ""
//											: Util
//													.formatarMoedaReal(resumoCobrancaSituacaoEspecialConsultaFinalHelper
//															.getTotalPercentualGeral()),
//
//									// Percentual Gerencial
//									helperGerencia
//											.getTotalPercentualGerencia() == null ? ""
//											: Util
//													.formatarMoedaReal(helperGerencia
//															.getTotalPercentualGerencia()),
//
//									// Percentual Localidade
//									helperLocalidade
//											.getTotalPercentualLocalidade() == null ? ""
//											: Util
//													.formatarMoedaReal(helperLocalidade
//															.getTotalPercentualLocalidade()),
//
//									// Percentual Motivo
//									helperMotivo
//											.getPercentual() == null ? ""
//											: Util
//													.formatarMoedaReal(helperMotivo
//															.getPercentual()),
//
//									// Percentual Situa��o
//									helperSitTipo
//											.getTotalPercentualSitTipo() == null ? ""
//											: Util
//													.formatarMoedaReal(helperSitTipo
//															.getTotalPercentualSitTipo()),
//
//									// Quantidade Im�vel Geral
//									resumoCobrancaSituacaoEspecialConsultaFinalHelper
//											.getTotalQtLigacoesGeral()
//											.toString(),
//
//									// Quantidade Im�vel Ger�ncia
//									helperGerencia
//											.getTotalQtLigacoesGerencia()
//											.toString(),
//
//									// Quantidade Im�vel Localidade
//									helperLocalidade
//											.getTotalQtLigacoesLocalidade()
//											.toString(),
//
//									// Quantidade Im�vel Motivo
//									helperMotivo
//											.getQtLigacoes() == null ? "0"
//											: helperMotivo
//													.getQtLigacoes().toString(),
//
//									// Quantidade Im�vel Situa��o
//									helperSitTipo
//											.getTotalQtLigacoesSitTipo()
//											.toString(),
//
//									// Quantidade Paralisada Geral
//									resumoCobrancaSituacaoEspecialConsultaFinalHelper
//											.getTotalGeral().toString(),
//
//									// Quantidade Paralisada Ger�ncia
//									helperGerencia
//											.getTotalGerenciaRegional()
//											.toString(),
//
//									// Quantidade Paralisada Motivo
//									helperMotivo
//											.getQtParalisada().toString(),
//
//									// Quantidade Paralisada Situa��o
//									helperSitTipo
//											.getTotalSituacaoTipo().toString(),
//
//									// Quantidade Paralisada Localidade
//									helperLocalidade
//											.getTotalLocalidade().toString(),
//
//									// Situa��o
//									helperSitTipo
//											.getSituacaoTipoDescricao());

							// Fim da Constru��o do objeto
							// RelatorioDadosEconomiaImovelBean
							// para ser colocado no relat�rio

							// adiciona o bean a cole��o
							relatorioBeans.add(relatorioBean);

						}
					}
				}
			}
		}}}

		// Organizar a cole��o

		// Collections.sort((List) relatorioBeans, new Comparator() {
		// public int compare(Object a, Object b) {
		// String chaveRegistro1 = ((RelatorioDadosEconomiaImovelBean) a)
		// .getNomeClienteUsuario() == null ?
		// ((RelatorioDadosEconomiaImovelBean) a)
		// .getMatricula()
		// + ((RelatorioDadosEconomiaImovelBean) a)
		// .getSubcategoria() + " "
		// : ((RelatorioDadosEconomiaImovelBean) a)
		// .getIdSetorComercial()
		// + ((RelatorioDadosEconomiaImovelBean) a)
		// .getIdLocalidade()
		// + ((RelatorioDadosEconomiaImovelBean) a)
		// .getIdGerenciaRegional()
		// + ((RelatorioDadosEconomiaImovelBean) a)
		// .getMatricula()
		// + ((RelatorioDadosEconomiaImovelBean) a)
		// .getSubcategoria()
		// + ((RelatorioDadosEconomiaImovelBean) a)
		// .getNomeClienteUsuario()
		// + ((RelatorioDadosEconomiaImovelBean) a)
		// .getNomeCliente()
		// + ((RelatorioDadosEconomiaImovelBean) a)
		// .getTipoRelacao();
		// String chaveRegistro2 = ((RelatorioDadosEconomiaImovelBean) b)
		// .getNomeClienteUsuario() == null ?
		// ((RelatorioDadosEconomiaImovelBean) b)
		// .getMatricula()
		// + ((RelatorioDadosEconomiaImovelBean) b)
		// .getSubcategoria() + " "
		// : ((RelatorioDadosEconomiaImovelBean) b)
		// .getIdSetorComercial()
		// + ((RelatorioDadosEconomiaImovelBean) b)
		// .getIdLocalidade()
		// + ((RelatorioDadosEconomiaImovelBean) b)
		// .getIdGerenciaRegional()
		// + ((RelatorioDadosEconomiaImovelBean) b)
		// .getMatricula()
		// + ((RelatorioDadosEconomiaImovelBean) b)
		// .getSubcategoria()
		// + ((RelatorioDadosEconomiaImovelBean) b)
		// .getNomeClienteUsuario()
		// + ((RelatorioDadosEconomiaImovelBean) b)
		// .getNomeCliente()
		// + ((RelatorioDadosEconomiaImovelBean) b)
		// .getTipoRelacao();
		//
		// return chaveRegistro1.compareTo(chaveRegistro2);
		//
		// }
		// });

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("nomeRelatorio", nomeRelatorio);
		
		parametros.put("numeroRelatorio", numeroRelatorio);
		
		parametros.put("mesAnoReferenciaFaturamento", mesAnoReferencia);

		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise

		RelatorioDataSource ds = new RelatorioDataSource((List) relatorioBeans);

		// exporta o relat�rio em pdf e retorna o array de bytes

		if(tipoFormatoRelatorio == TarefaRelatorio.TIPO_TXT || tipoFormatoRelatorio == TarefaRelatorio.TIPO_CSV ){
			retorno = this.gerarRelatorio(dadosEscrita, tipoFormatoRelatorio);
		}else{
			retorno = this
					.gerarRelatorio(ConstantesRelatorios.RELATORIO_RESUMO_FATURAMENTO_SITUACAO_ESPECIAL,
							parametros, ds, tipoFormatoRelatorio);
		}

		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.RESUMO_FATURAMENTO_SITUACAO_ESPECIAL,
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

		// if
		// (getParametro("resumoFaturamentoSituacaoEspecialConsultaFinalHelper")
		// != null
		// &&
		// getParametro("resumoFaturamentoSituacaoEspecialConsultaFinalHelper")
		// instanceof Collection) {
		// retorno = ((Collection)
		// getParametro("resumoFaturamentoSituacaoEspecialConsultaFinalHelper")).size();
		// } else {
		// retorno = ((Collection)
		// getParametro("resumoCobrancaSituacaoEspecialConsultaFinalHelper")).size();
		// }
		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa(
				"RelatorioResumoFaturamentoSituacaoEspecial", this);
	}
}
