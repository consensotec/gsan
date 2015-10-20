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
package gcom.relatorio.cadastro.imovel;

import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamentoHistorico;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.ContaHistoricoRelatoriosHelper;
import gcom.faturamento.bean.ContaRelatoriosHelper;
import gcom.faturamento.bean.CreditoARealizarHistoricoRelatoriosHelper;
import gcom.faturamento.bean.CreditoARealizarRelatoriosHelper;
import gcom.faturamento.bean.DebitoACobrarHistoricoRelatoriosHelper;
import gcom.faturamento.bean.DebitoACobrarRelatoriosHelper;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaHistorico;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.credito.CreditoARealizarHistorico;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarHistorico;
import gcom.gui.cadastro.imovel.ConsultarImovelActionForm;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.arrecadacao.pagamento.GuiaPagamentoRelatorioHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioHistoricoFaturamentoImovel extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioHistoricoFaturamentoImovel(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_HISTORICO_FATURAMENTO_IMOVEL);
	}
	
	public Object executar() throws TarefaException {
		
		List<RelatorioHistoricoFaturamentoImovelBean> relatorioBeans = new ArrayList<RelatorioHistoricoFaturamentoImovelBean>();
		relatorioBeans.add( criarRelatorioBean() );
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		Map<String, Object> parametros = criarParametrosRelatorio();

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		return this.gerarRelatorio(ConstantesRelatorios.RELATORIO_HISTORICO_FATURAMENTO_IMOVEL, parametros,ds, tipoFormatoRelatorio);
	}

	/**
	 * Esse m�todo cria o RelatorioBean atrav�s dos parametros
	 * enviado a este objeto.
	 *
	 *@since 28/09/2009
	 *@author Marlon Patrick
	 */
	private RelatorioHistoricoFaturamentoImovelBean criarRelatorioBean() {
		
		ConsultarImovelActionForm consultarImovelForm =
			(ConsultarImovelActionForm) getParametro("consultarImovelForm");

		RelatorioHistoricoFaturamentoImovelBean relatorioBean = new RelatorioHistoricoFaturamentoImovelBean();

		relatorioBean.setInscricaoImovel(consultarImovelForm.getMatriculaImovelHistoricoFaturamento());
		relatorioBean.setMatriculaImovel(consultarImovelForm.getIdImovelHistoricoFaturamento());
		relatorioBean.setSituacaoAguaImovel(consultarImovelForm.getSituacaoAguaHistoricoFaturamento());
		relatorioBean.setSituacaoEsgotoImovel(consultarImovelForm.getSituacaoEsgotoHistoricoFaturamento());

		relatorioBean.setColecaoContas(new JRBeanCollectionDataSource( criarColecaoContaHelper() ));

		relatorioBean.setColecaoContaHistorico(new JRBeanCollectionDataSource( criarColecaoContaHistoricoHelper() ));

		relatorioBean.setColecaoDebitoCobrar(new JRBeanCollectionDataSource( criarColecaoDebitoCobrarHelper() ));

		relatorioBean.setColecaoDebitoCobrarHistorico(new JRBeanCollectionDataSource(criarColecaoDebitoCobrarHistoricoHelper()));

		relatorioBean.setColecaoCreditoRealizar(new JRBeanCollectionDataSource(criarColecaoCreditoRealizarHelper()));

		relatorioBean.setColecaoCreditoRealizarHistorico(new JRBeanCollectionDataSource(criarColecaoCreditoReazlizarHistoricoHelper()));

		relatorioBean.setColecaoGuiaPagamento(new JRBeanCollectionDataSource(criarColecaoGuiaPagamentoHelper()));

		relatorioBean.setColecaoGuiaPagamentoHistorico(new JRBeanCollectionDataSource(criarColecaoGuiaPagamentoHistoricoHelper()));


		return relatorioBean;
	}

	/**
	 * Cria uma cole��o de GuiaPagamentoRelatorioHelper
	 * a partir da cole��o de Guia de Pagamentos de Historicos.
	 *
	 *@since 29/09/2009
	 *@author Marlon Patrick
	 */
	private ArrayList<GuiaPagamentoRelatorioHelper> criarColecaoGuiaPagamentoHistoricoHelper() {

		Collection<GuiaPagamentoHistorico> colecaoGuiaPagamentoHistoricoImovel = 
			(Collection<GuiaPagamentoHistorico>) getParametro("colecaoGuiaPagamentoHistoricoImovel");

		ArrayList<GuiaPagamentoRelatorioHelper> colecaoHelpers = null;

		if( !Util.isVazioOrNulo(colecaoGuiaPagamentoHistoricoImovel) ){			

			colecaoHelpers = new ArrayList<GuiaPagamentoRelatorioHelper>();
			
			for(GuiaPagamentoHistorico guia : colecaoGuiaPagamentoHistoricoImovel){
				
				GuiaPagamentoRelatorioHelper helper = new GuiaPagamentoRelatorioHelper();
				
				helper.setNumeroPrestacaoDebito(guia.getNumeroPrestacaoDebito());
				helper.setNumeroPrestacaoTotal(guia.getNumeroPrestacaoTotal());
				helper.setDataEmissao(guia.getDataEmissao());
				helper.setDataVencimento(guia.getDataVencimento());
				helper.setValorDebito(guia.getValorDebito());
				
				if(guia.getDebitoTipo()!= null){
					helper.setDescTipoDebito(guia.getDebitoTipo().getDescricao());
				}
				
				colecaoHelpers.add(helper);
			}

		}
		return colecaoHelpers;
	}

	/**
	 * Cria uma cole��o de GuiaPagamentoRelatorioHelper
	 * a partir da cole��o de Guia de Pagamentos.
	 *
	 *@since 29/09/2009
	 *@author Marlon Patrick
	 */
	private ArrayList<GuiaPagamentoRelatorioHelper> criarColecaoGuiaPagamentoHelper() {

		Collection<GuiaPagamento> colecaoGuiaPagamentoImovel = 
			(Collection<GuiaPagamento>) getParametro("colecaoGuiaPagamentoImovel");

		ArrayList<GuiaPagamentoRelatorioHelper> colecaoHelpers = null;

		if( !Util.isVazioOrNulo(colecaoGuiaPagamentoImovel) ){
			
			colecaoHelpers = new ArrayList<GuiaPagamentoRelatorioHelper>();
			
			for(GuiaPagamento guia : colecaoGuiaPagamentoImovel){
				
				GuiaPagamentoRelatorioHelper helper = new GuiaPagamentoRelatorioHelper();
				
				helper.setNumeroPrestacaoDebito(guia.getNumeroPrestacaoDebito());
				helper.setNumeroPrestacaoTotal(guia.getNumeroPrestacaoTotal());
				helper.setDataEmissao(guia.getDataEmissao());
				helper.setDataVencimento(guia.getDataVencimento());
				helper.setValorDebito(guia.getValorDebito());
				
				if(guia.getDebitoTipo()!= null){
					helper.setDescTipoDebito(guia.getDebitoTipo().getDescricao());
				}
				
				colecaoHelpers.add(helper);
			}

		}
		return colecaoHelpers;
	}

	/**
	 * Cria uma cole��o de CreditoARealizarHistoricoRelatoriosHelper
	 * a partir da cole��o de Creditos a Realizar Historico.
	 *
	 *@since 29/09/2009
	 *@author Marlon Patrick
	 */
	private ArrayList<CreditoARealizarHistoricoRelatoriosHelper> criarColecaoCreditoReazlizarHistoricoHelper(){

		Collection<CreditoARealizarHistorico> colecaoCreditoARealizarHistoricoImovel = 
			(Collection<CreditoARealizarHistorico>) getParametro("colecaoCreditoARealizarHistoricoImovel");

		ArrayList<CreditoARealizarHistoricoRelatoriosHelper> colecaoHelpers = null;
		
		if( !Util.isVazioOrNulo(colecaoCreditoARealizarHistoricoImovel) ){
			
			colecaoHelpers = new ArrayList<CreditoARealizarHistoricoRelatoriosHelper>();
			
			for(CreditoARealizarHistorico credito : colecaoCreditoARealizarHistoricoImovel){
				colecaoHelpers.add(new CreditoARealizarHistoricoRelatoriosHelper(credito));
			}

		}
		return colecaoHelpers;
	}

	/**
	 * Cria uma cole��o de CreditoARealizarRelatoriosHelper
	 * a partir da cole��o de Creditos a Realizar.
	 *
	 *@since 29/09/2009
	 *@author Marlon Patrick
	 */
	private ArrayList<CreditoARealizarRelatoriosHelper> criarColecaoCreditoRealizarHelper() {

		Collection<CreditoARealizar> colecaoCreditoARealizarImovel = 
			(Collection<CreditoARealizar>) getParametro("colecaoCreditoARealizarImovel");

		ArrayList<CreditoARealizarRelatoriosHelper> colecaoHelpers = null;

		if( !Util.isVazioOrNulo(colecaoCreditoARealizarImovel) ){

			colecaoHelpers = new ArrayList<CreditoARealizarRelatoriosHelper>();
			
			for(CreditoARealizar credito : colecaoCreditoARealizarImovel){
				colecaoHelpers.add(new CreditoARealizarRelatoriosHelper(credito));
			}

		}
		return colecaoHelpers;
	}

	/**
	 * Cria uma cole��o de DebitoACobrarHistoricoRelatoriosHelper
	 * a partir da cole��o de Debitos a Cobrar Historico.
	 *
	 *@since 29/09/2009
	 *@author Marlon Patrick
	 */
	private ArrayList<DebitoACobrarHistoricoRelatoriosHelper> criarColecaoDebitoCobrarHistoricoHelper() {
		Collection<DebitoACobrarHistorico> colecaoDebitoACobrarHistoricoImovel = 
			(Collection<DebitoACobrarHistorico>) getParametro("colecaoDebitoACobrarHistoricoImovel");

		ArrayList<DebitoACobrarHistoricoRelatoriosHelper> colecaoHelpers = null;
		
		if( !Util.isVazioOrNulo(colecaoDebitoACobrarHistoricoImovel) ){
			
			colecaoHelpers = new ArrayList<DebitoACobrarHistoricoRelatoriosHelper>();
			
			for(DebitoACobrarHistorico debitoHistorico : colecaoDebitoACobrarHistoricoImovel){
				colecaoHelpers.add(new DebitoACobrarHistoricoRelatoriosHelper(debitoHistorico));
			}
		}
		
		return colecaoHelpers;
	}

	/**
	 * Cria uma cole��o de DebitoACobrarRelatoriosHelper
	 * a partir da cole��o de Debitos a Cobrar.
	 *
	 *@since 29/09/2009
	 *@author Marlon Patrick
	 */
	private ArrayList<DebitoACobrarRelatoriosHelper> criarColecaoDebitoCobrarHelper() {
		
		Collection<DebitoACobrar> colecaoDebitoACobrarImovel = 
			(Collection<DebitoACobrar>) getParametro("colecaoDebitoACobrarImovel");

		ArrayList<DebitoACobrarRelatoriosHelper> colecaoHelpers = null;
		
		if( !Util.isVazioOrNulo(colecaoDebitoACobrarImovel) ){			
			colecaoHelpers = new ArrayList<DebitoACobrarRelatoriosHelper>();
			
			for(DebitoACobrar debitoCobrar : colecaoDebitoACobrarImovel){
				colecaoHelpers.add(new DebitoACobrarRelatoriosHelper(debitoCobrar));
			}
		}
		
		return colecaoHelpers;
	}

	/**
	 * Cria uma cole��o de ContaHistoricoRelatoriosHelper
	 * a partir da cole��o de Contas Historico.
	 *
	 *@since 29/09/2009
	 *@author Marlon Patrick
	 */
	private ArrayList<ContaHistoricoRelatoriosHelper> criarColecaoContaHistoricoHelper() {

		Collection<ContaHistorico> colecaoContaHistoricoImovel = 
			(Collection<ContaHistorico>) getParametro("colecaoContaHistoricoImovel");

		ArrayList<ContaHistoricoRelatoriosHelper> colecaoHelpers = null;
		
		if( !Util.isVazioOrNulo(colecaoContaHistoricoImovel) ){
			
			colecaoHelpers = new ArrayList<ContaHistoricoRelatoriosHelper>();
			
			for(ContaHistorico contaHistorico : colecaoContaHistoricoImovel){
				colecaoHelpers.add(new ContaHistoricoRelatoriosHelper(contaHistorico));
			}

		}
		return colecaoHelpers;
	}

	/**
	 * Cria uma cole��o de ContaRelatoriosHelper
	 * a partir da cole��o de Contas.
	 *
	 *@since 29/09/2009
	 *@author Marlon Patrick
	 */
	private ArrayList<ContaRelatoriosHelper> criarColecaoContaHelper() {

		Collection<Conta> colecaoContasImovel = 
			(Collection<Conta>) getParametro("colecaoContaImovel");

		ArrayList<ContaRelatoriosHelper> colecaoHelpers = null;

		if( !Util.isVazioOrNulo(colecaoContasImovel) ){
			
			colecaoHelpers = new ArrayList<ContaRelatoriosHelper>();
			
			for(Conta conta : colecaoContasImovel){
				colecaoHelpers.add(new ContaRelatoriosHelper(conta));
			}

		}
		
		return colecaoHelpers;
	}
	
	/**
	 * Esse m�todo cria os parametros do relatorio com base
	 * nos parametros passados para esse objeto.
	 *
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private Map<String, Object> criarParametrosRelatorio() {

		ConsultarImovelActionForm consultarImovelForm =
			(ConsultarImovelActionForm) getParametro("consultarImovelForm");

		Map<String,Object> parametros = new HashMap<String,Object>();

		parametros.put("imagem", Fachada.getInstancia().
				pesquisarParametrosDoSistema().getImagemRelatorio());

		if( Util.verificarNaoVazio(consultarImovelForm.getIdImovelHistoricoFaturamento()) ){
			parametros.put("idImovelFiltro",consultarImovelForm.getIdImovelHistoricoFaturamento());
		}
		
		if( Util.isVazioOrNulo((Collection<ContaHistorico>) getParametro("colecaoContaHistoricoImovel"))){
			parametros.put("isColecaoContaHistoricoVazia",new Boolean(true));			
		}else{
			parametros.put("isColecaoContaHistoricoVazia",new Boolean(false));			
		}

		if( Util.isVazioOrNulo((Collection<DebitoACobrar>) getParametro("colecaoDebitoACobrarHistoricoImovel"))){
			parametros.put("isColecaoDebitoHistoricoVazia",new Boolean(true));			
		}else{
			parametros.put("isColecaoDebitoHistoricoVazia",new Boolean(false));			
		}

		if( Util.isVazioOrNulo((Collection<Conta>) getParametro("colecaoCreditoARealizarHistoricoImovel"))){
			parametros.put("isColecaoCreditoHistoricoVazia",new Boolean(true));			
		}else{
			parametros.put("isColecaoCreditoHistoricoVazia",new Boolean(false));			
		}

		if( Util.isVazioOrNulo((Collection<Conta>) getParametro("colecaoGuiaPagamentoHistoricoImovel"))){
			parametros.put("isColecaoGuiaHistoricoVazia",new Boolean(true));			
		}else{
			parametros.put("isColecaoGuiaHistoricoVazia",new Boolean(false));			
		}

		return parametros;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioHistoricoFaturamentoImovel", this);
	}

}