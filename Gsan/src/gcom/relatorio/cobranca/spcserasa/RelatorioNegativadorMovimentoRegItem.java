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
package gcom.relatorio.cobranca.spcserasa;

import gcom.cobranca.NegativadorMovimentoRegItem;
import gcom.fachada.Fachada;
import gcom.gui.cobranca.spcserasa.ConsultarNegativadorMovimentoRegItemPopupActionForm;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.spcserasa.bean.ConsultarNegativadorMovimentoRegItemContasHelper;
import gcom.spcserasa.bean.ConsultarNegativadorMovimentoRegItemGuiasHelper;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * [UC0684]
 * 
 * @author Rodrigo Cabral
 * @created 18/09/2014
 */
public class RelatorioNegativadorMovimentoRegItem extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioNegativadorMovimentoRegItem(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_NEGATIVADOR_MOVIMENTO_REG_ITEM);
	}

public Object executar() throws TarefaException {
		
		List<RelatorioNegativadorMovimentoRegItemBean> relatorioBeans = new ArrayList<RelatorioNegativadorMovimentoRegItemBean>();
		relatorioBeans.add( criarRelatorioBean() );
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		Map<String, Object> parametros = criarParametrosRelatorio();

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		return this.gerarRelatorio(ConstantesRelatorios.RELATORIO_NEGATIVADOR_MOVIMENTO_REG_ITEM, parametros,ds, tipoFormatoRelatorio);
	}

	/**
	 * Esse m�todo cria o RelatorioBean atrav�s dos parametros
	 * enviado a este objeto.
	 *
	 *@since 22/09/2014
	 *@author Rodrigo Cabral
	 */
	private RelatorioNegativadorMovimentoRegItemBean criarRelatorioBean() {
		
		
		Collection<NegativadorMovimentoRegItem> colecaoContas = 
			(Collection<NegativadorMovimentoRegItem>) getParametro("collNegativadorMovimentoRegItemContas");

		Collection<NegativadorMovimentoRegItem> colecaoGuias = 
			(Collection<NegativadorMovimentoRegItem>) getParametro("collNegativadorMovimentoRegItemGuias");

		RelatorioNegativadorMovimentoRegItemBean relatorioBean = new RelatorioNegativadorMovimentoRegItemBean();
		

		if( !Util.isVazioOrNulo(colecaoContas)){
			
			relatorioBean.setColecaoNegativadorMovimentoRegItemContasHelper(
					new JRBeanCollectionDataSource(criarColecaoNegativadorMovimentoRegItemContasHelper(colecaoContas)));
		}

		if( !Util.isVazioOrNulo(colecaoGuias)){
			
			relatorioBean.setColecaoNegativadorMovimentoRegItemGuiasHelper(
				new JRBeanCollectionDataSource(criarColecaoNegativadorMovimentoRegItemGuiasHelper(colecaoGuias)));			
		}

		return relatorioBean;
	}

	
	/**
	 * Esse m�todo cria os parametros do relatorio com base
	 * nos parametros passados para esse objeto.
	 *
	 *@since 19/09/2014
	 *@author Rodrigo Cabral
	 */
	private Map<String, Object> criarParametrosRelatorio() {

		ConsultarNegativadorMovimentoRegItemPopupActionForm form = (ConsultarNegativadorMovimentoRegItemPopupActionForm) getParametro("consultarNegativadorMovimentoRegItemPopupActionForm");

		Map<String,Object> parametros = new HashMap<String,Object>();

		parametros.put("imagem", Fachada.getInstancia().
				pesquisarParametrosDoSistema().getImagemRelatorio());

		if( Util.verificarNaoVazio(form.getNegativador()) ){
			parametros.put("negativador",form.getNegativador());
		}
		
		String enderecoImovel = "";
		String nomeCliente = "";
		if(Util.verificarNaoVazio(form.getMatriculaImovel())){
			parametros.put("matriculaImovel",
					form.getMatriculaImovel());
			
			enderecoImovel = Fachada.getInstancia().pesquisarEndereco(new Integer(form.getMatriculaImovel()));
			
			parametros.put("enderecoImovel", enderecoImovel);
			
			nomeCliente = Fachada.getInstancia().consultarClienteUsuarioImovel(new Integer(form.getMatriculaImovel()));
			
			parametros.put("nomeCliente", nomeCliente);
		}
		
		if(Util.verificarNaoVazio(form.getInscricao())){
			parametros.put("inscricao",
					form.getInscricao());
			
		}

		if(Util.verificarNaoVazio(form.getSituacaoLigacaoAgua())){
			parametros.put("situacaoLigacaoAgua",form.getSituacaoLigacaoAgua());			
		}
		
		if(Util.verificarNaoVazio(form.getSituacaoLigacaoEsgoto())){
			parametros.put("situacaoLigacaoEsgoto",form.getSituacaoLigacaoEsgoto());			
		}

		parametros.put("dataSituacaoDebito", (String ) getParametro("dataSituacaoDebito"));
		
		Integer totalQtdContas = (Integer ) getParametro("totalQtdContas");
		Integer totalQtdGuias = (Integer ) getParametro("totalQtdGuias");
		BigDecimal totalValorConta = (BigDecimal) getParametro("totalValorConta");
		BigDecimal totalValorGuia = (BigDecimal) getParametro("totalValorGuia");
		BigDecimal totalValorNegativadoConta = (BigDecimal) getParametro("totalValorNegativadoConta");
		BigDecimal totalValorNegativadoGuia = (BigDecimal) getParametro("totalValorNegativadoGuia");
		
		Integer totalDocumentos = totalQtdContas+totalQtdGuias;
		BigDecimal totalValorDocumentos = totalValorConta.add(totalValorGuia);
		
		parametros.put("totalDocumentos", totalDocumentos.toString());
		parametros.put("totalValorDocumentos", Util.formatarMoedaReal(totalValorDocumentos).toString());
		parametros.put("totalValorDocumentosNegativado", Util.formatarMoedaReal(totalValorNegativadoConta.add(totalValorNegativadoGuia)).toString());
		
		
		
		return parametros;
	}

	/**
	 * Cria uma cole��o de ConsultarNegativadorMovimentoRegItemContasHelper a partir da cole��o
	 * de NegativadorMovimentoRegItem passada como parametro.
	 *
	 *@since 22/09/2014
	 *@author Rodrigo Cabral
	 */
	private Collection<ConsultarNegativadorMovimentoRegItemContasHelper> criarColecaoNegativadorMovimentoRegItemContasHelper(
			Collection<NegativadorMovimentoRegItem> colecaoNegativadorMovimentoRegItemContas) {
		
		Integer totalQtdContas = (Integer ) getParametro("totalQtdContas");
		BigDecimal totalValorConta = (BigDecimal) getParametro("totalValorConta");
		BigDecimal totalValorDebitoConta = (BigDecimal) getParametro("totalValorNegativadoConta");
		
		Collection<ConsultarNegativadorMovimentoRegItemContasHelper> colecaoHelpers = new ArrayList<ConsultarNegativadorMovimentoRegItemContasHelper>();
		
		for(NegativadorMovimentoRegItem negatMoviRegItemContas : colecaoNegativadorMovimentoRegItemContas){
			
			ConsultarNegativadorMovimentoRegItemContasHelper helper = new ConsultarNegativadorMovimentoRegItemContasHelper();

			if(negatMoviRegItemContas.getContaGeral() != null && negatMoviRegItemContas.getContaGeral().getConta() != null){
				
					
					helper.setAnoMesConta(negatMoviRegItemContas.getContaGeral().getConta().getReferenciaFormatada());
					
		
					if(negatMoviRegItemContas.getContaGeral().getConta().getDataVencimentoConta() !=null){
						helper.setDataVencimentoConta(Util.formatarData(negatMoviRegItemContas.getContaGeral().getConta().getDataVencimentoConta()));
					}
					
					if(negatMoviRegItemContas.getContaGeral().getConta().getValorTotalContaBigDecimal()!=null){
						helper.setValorConta(Util.formataBigDecimal(negatMoviRegItemContas.getContaGeral().getConta().getValorTotalContaBigDecimal(),2,false));
					}
				
					if(negatMoviRegItemContas.getDebitoACobrarGeral() !=null 
							&& negatMoviRegItemContas.getDebitoACobrarGeral().getDebitoACobrar() != null 
								&& negatMoviRegItemContas.getDebitoACobrarGeral().getDebitoACobrar().getDebitoCreditoSituacaoAtual() != null){
						helper.setSituacaoConta(negatMoviRegItemContas.getDebitoACobrarGeral().getDebitoACobrar().getDebitoCreditoSituacaoAtual().getDescricaoAbreviada());
					}
					
			} else if (negatMoviRegItemContas.getContaGeral() != null && negatMoviRegItemContas.getContaGeral().getContaHistorico() != null){
					
					
					helper.setAnoMesConta(Util.formatarAnoMesParaMesAno(negatMoviRegItemContas.getContaGeral().getContaHistorico().getAnoMesReferenciaConta()));
					
					
					if(negatMoviRegItemContas.getContaGeral().getContaHistorico().getDataVencimentoConta() !=null){
						helper.setDataVencimentoConta(Util.formatarData(negatMoviRegItemContas.getContaGeral().getContaHistorico().getDataVencimentoConta()));
					}
					
					if(negatMoviRegItemContas.getContaGeral().getContaHistorico().getValorTotalContaBigDecimal()!=null){
						helper.setValorConta(Util.formataBigDecimal(negatMoviRegItemContas.getContaGeral().getContaHistorico().getValorTotalContaBigDecimal(),2,false));
					}
				
					if(negatMoviRegItemContas.getDebitoACobrarGeral() !=null 
							&& negatMoviRegItemContas.getDebitoACobrarGeral().getDebitoACobrarHistorico() != null 
								&& negatMoviRegItemContas.getDebitoACobrarGeral().getDebitoACobrar().getDebitoCreditoSituacaoAtual() != null){
						helper.setSituacaoConta(negatMoviRegItemContas.getDebitoACobrarGeral().getDebitoACobrarHistorico().getDebitoCreditoSituacaoAtual().getDescricaoAbreviada());
					}
			}
				
				if(negatMoviRegItemContas.getValorDebito()!=null){
					helper.setValorNegativoConta(Util.formataBigDecimal(negatMoviRegItemContas.getValorDebito(),2,false));
				}
				
				if(negatMoviRegItemContas.getCobrancaDebitoSituacao() != null){
					helper.setSituacaoCobrancaContaAteExclusao(negatMoviRegItemContas.getCobrancaDebitoSituacao().getDescricao());
				}
				
				if(negatMoviRegItemContas.getDataSituacaoDebito() != null){
					helper.setDataSitCobrancaContaAteExclusao(Util.formatarData(negatMoviRegItemContas.getDataSituacaoDebito()));
				}
				
				if(negatMoviRegItemContas.getCobrancaDebitoSituacaoAposExclusao() != null){
					helper.setSituacaoCobrancaContaAposExclusao(negatMoviRegItemContas.getCobrancaDebitoSituacaoAposExclusao().getDescricao());
				}
				
				if(negatMoviRegItemContas.getDataSituacaoDebitoAposExclusao() != null){
					helper.setDataSitCobrancaContaAposExclusao(Util.formatarData(negatMoviRegItemContas.getDataSituacaoDebitoAposExclusao()));
				}
				
				if (negatMoviRegItemContas.getIndicadorSituacaoDefinitiva() == ConstantesSistema.SIM){
					
					helper.setSituacaoDefinitiva("SIM");
				} else if (negatMoviRegItemContas.getIndicadorSituacaoDefinitiva() == ConstantesSistema.NAO) {
					
					helper.setSituacaoDefinitiva("N�O");
				}
				
			helper.setTotalQtdContas(totalQtdContas.toString());
			helper.setTotalValorConta(Util.formataBigDecimal(totalValorConta, 2, false));
			helper.setTotalValorNegativadoConta(Util.formataBigDecimal(totalValorDebitoConta, 2, false));
			
			colecaoHelpers.add(helper);

		}
		return colecaoHelpers;
	}
	
	
	/**
	 * Cria uma cole��o de ConsultarNegativadorMovimentoRegItemContasHelper a partir da cole��o
	 * de NegativadorMovimentoRegItem passada como parametro.
	 *
	 *@since 22/09/2014
	 *@author Rodrigo Cabral
	 */
	private Collection<ConsultarNegativadorMovimentoRegItemGuiasHelper> criarColecaoNegativadorMovimentoRegItemGuiasHelper(
			Collection<NegativadorMovimentoRegItem> colecaoNegativadorMovimentoRegItemGuias) {
		
		Integer totalQtdGuias = (Integer ) getParametro("totalQtdGuias");
		BigDecimal totalValorGuia = (BigDecimal) getParametro("totalValorGuia");
		BigDecimal totalValorNegativadoGuia = (BigDecimal) getParametro("totalValorNegativadoGuia");
		
		Collection<ConsultarNegativadorMovimentoRegItemGuiasHelper> colecaoHelpers = new ArrayList<ConsultarNegativadorMovimentoRegItemGuiasHelper>();
		
		for(NegativadorMovimentoRegItem negatMoviRegItemGuias : colecaoNegativadorMovimentoRegItemGuias){
			
			ConsultarNegativadorMovimentoRegItemGuiasHelper helper = new ConsultarNegativadorMovimentoRegItemGuiasHelper();

			if(negatMoviRegItemGuias.getGuiaPagamentoGeral() != null && negatMoviRegItemGuias.getGuiaPagamentoGeral().getGuiaPagamento() != null){
				
					if (negatMoviRegItemGuias.getDebitoACobrarGeral() != null && negatMoviRegItemGuias.getDebitoACobrarGeral().getDebitoACobrar() != null
							&& negatMoviRegItemGuias.getDebitoACobrarGeral().getDebitoACobrar().getDebitoTipo() != null){
						
						helper.setTipoDebito(negatMoviRegItemGuias.getDebitoACobrarGeral().getDebitoACobrar().getDebitoTipo().getDescricaoAbreviada());
					}
					
					if(negatMoviRegItemGuias.getGuiaPagamentoGeral().getGuiaPagamento().getDataEmissao() !=null){
						helper.setDataEmissaoGuia(Util.formatarData(negatMoviRegItemGuias.getGuiaPagamentoGeral().getGuiaPagamento().getDataEmissao()));
					}
			
					if(negatMoviRegItemGuias.getGuiaPagamentoGeral().getGuiaPagamento().getDataVencimento() !=null){
						helper.setDataVencimentoGuia(Util.formatarData(negatMoviRegItemGuias.getGuiaPagamentoGeral().getGuiaPagamento().getDataVencimento()));
					}
					
					if(negatMoviRegItemGuias.getGuiaPagamentoGeral().getGuiaPagamento().getValorDebito()!=null){
						helper.setValorGuia(Util.formataBigDecimal(negatMoviRegItemGuias.getGuiaPagamentoGeral().getGuiaPagamento().getValorDebito(),2,false));
					}
				
					if(negatMoviRegItemGuias.getDebitoACobrarGeral() !=null 
							&& negatMoviRegItemGuias.getDebitoACobrarGeral().getDebitoACobrar() != null 
								&& negatMoviRegItemGuias.getDebitoACobrarGeral().getDebitoACobrar().getDebitoCreditoSituacaoAtual() != null){
						helper.setSituacaoGuia(negatMoviRegItemGuias.getDebitoACobrarGeral().getDebitoACobrar().getDebitoCreditoSituacaoAtual().getDescricaoAbreviada());
					}
					
			} else if (negatMoviRegItemGuias.getGuiaPagamentoGeral() != null 
							&& negatMoviRegItemGuias.getGuiaPagamentoGeral().getGuiaPagamentoHistorico() != null){
					
					
				if (negatMoviRegItemGuias.getDebitoACobrarGeral() != null && negatMoviRegItemGuias.getDebitoACobrarGeral().getDebitoACobrarHistorico() != null
						&& negatMoviRegItemGuias.getDebitoACobrarGeral().getDebitoACobrarHistorico().getDebitoTipo() != null){
					
					helper.setTipoDebito(negatMoviRegItemGuias.getDebitoACobrarGeral().getDebitoACobrarHistorico().getDebitoTipo().getDescricaoAbreviada());
				}
				
				if(negatMoviRegItemGuias.getGuiaPagamentoGeral().getGuiaPagamentoHistorico().getDataEmissao() !=null){
					helper.setDataEmissaoGuia(Util.formatarData(negatMoviRegItemGuias.getGuiaPagamentoGeral().getGuiaPagamentoHistorico().getDataEmissao()));
				}
		
				if(negatMoviRegItemGuias.getGuiaPagamentoGeral().getGuiaPagamentoHistorico().getDataVencimento() !=null){
					helper.setDataVencimentoGuia(Util.formatarData(negatMoviRegItemGuias.getGuiaPagamentoGeral().getGuiaPagamentoHistorico().getDataVencimento()));
				}
				
				if(negatMoviRegItemGuias.getGuiaPagamentoGeral().getGuiaPagamentoHistorico().getValorDebito()!=null){
					helper.setValorGuia(Util.formataBigDecimal(negatMoviRegItemGuias.getGuiaPagamentoGeral().getGuiaPagamentoHistorico().getValorDebito(),2,false));
				}
				
					if(negatMoviRegItemGuias.getDebitoACobrarGeral() !=null 
							&& negatMoviRegItemGuias.getDebitoACobrarGeral().getDebitoACobrarHistorico() != null 
								&& negatMoviRegItemGuias.getDebitoACobrarGeral().getDebitoACobrar().getDebitoCreditoSituacaoAtual() != null){
						helper.setSituacaoGuia(negatMoviRegItemGuias.getDebitoACobrarGeral().getDebitoACobrarHistorico().getDebitoCreditoSituacaoAtual().getDescricaoAbreviada());
					}
			}
				
				if(negatMoviRegItemGuias.getValorDebito()!=null){
					helper.setValorNegativoGuia(Util.formataBigDecimal(negatMoviRegItemGuias.getValorDebito(), 2, false));
				}
				
				if(negatMoviRegItemGuias.getCobrancaDebitoSituacao() != null){
					helper.setSituacaoCobrancaGuiaAteExclusao(negatMoviRegItemGuias.getCobrancaDebitoSituacao().getDescricao());
				}
				
				if(negatMoviRegItemGuias.getDataSituacaoDebito() != null){
					helper.setDataSitCobrancaGuiaAteExclusao(Util.formatarData(negatMoviRegItemGuias.getDataSituacaoDebito()));
				}
				
				if(negatMoviRegItemGuias.getCobrancaDebitoSituacaoAposExclusao() != null){
					helper.setSituacaoCobrancaGuiaAposExclusao(negatMoviRegItemGuias.getCobrancaDebitoSituacaoAposExclusao().getDescricao());
				}
				
				if(negatMoviRegItemGuias.getDataSituacaoDebitoAposExclusao() != null){
					helper.setDataSitCobrancaGuiaAposExclusao(Util.formatarData(negatMoviRegItemGuias.getDataSituacaoDebitoAposExclusao()));
				}
				
				if (negatMoviRegItemGuias.getIndicadorSituacaoDefinitiva() == ConstantesSistema.SIM){
					
					helper.setSituacaoDefinitiva("SIM");
				} else if (negatMoviRegItemGuias.getIndicadorSituacaoDefinitiva() == ConstantesSistema.NAO) {
					
					helper.setSituacaoDefinitiva("N�O");
				}
				
				helper.setTotalQtdGuias(totalQtdGuias.toString());
				helper.setTotalValorGuia(Util.formataBigDecimal(totalValorGuia, 2, false));
				helper.setTotalValorNegativadoGuia(Util.formataBigDecimal(totalValorNegativadoGuia, 2, false));
			
			colecaoHelpers.add(helper);

		}
		return colecaoHelpers;
	}


	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioNegativadorMovimentoRegItem", this);

	}

}