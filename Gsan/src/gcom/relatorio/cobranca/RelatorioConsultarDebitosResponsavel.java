/*
 * Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
 * GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
 * Copyright (C) <2007> 
 * Adriano Britto Siqueira
 * Alexandre Santos Cabral
 * Ana Carolina Alves Breda
 * Ana Maria Andrade Cavalcante
 * Aryed Lins de Araújo
 * Bruno Leonardo Rodrigues Barros
 * Carlos Elmano Rodrigues Ferreira
 * Cláudio de Andrade Lira
 * Denys Guimarães Guenes Tavares
 * Eduardo Breckenfeld da Rosa Borges
 * Fabíola Gomes de Araújo
 * Flávio Leonardo Cavalcanti Cordeiro
 * Francisco do Nascimento Júnior
 * Homero Sampaio Cavalcanti
 * Ivan Sérgio da Silva Júnior
 * José Edmar de Siqueira
 * José Thiago Tenório Lopes
 * Kássia Regina Silvestre de Albuquerque
 * Leonardo Luiz Vieira da Silva
 * Márcio Roberto Batista da Silva
 * Maria de Fátima Sampaio Leite
 * Micaela Maria Coelho de Araújo
 * Nelson Mendonça de Carvalho
 * Newton Morais e Silva
 * Pedro Alexandre Santos da Silva Filho
 * Rafael Corrêa Lima e Silva
 * Rafael Francisco Pinto
 * Rafael Koury Monteiro
 * Rafael Palermo de Araújo
 * Raphael Veras Rossiter
 * Roberto Sobreira Barbalho
 * Rodrigo Avellar Silveira
 * Rosana Carvalho Barbosa
 * Sávio Luiz de Andrade Cavalcante
 * Tai Mu Shih
 * Thiago Augusto Souza do Nascimento
 * Tiago Moreno Rodrigues
 * Vivianne Barbosa Sousa
 *
 * Este programa é software livre; você pode redistribuí-lo e/ou
 * modificá-lo sob os termos de Licença Pública Geral GNU, conforme
 * publicada pela Free Software Foundation; versão 2 da
 * Licença.
 * Este programa é distribuído na expectativa de ser útil, mas SEM
 * QUALQUER GARANTIA; sem mesmo a garantia implícita de
 * COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
 * PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
 * detalhes.
 * Você deve ter recebido uma cópia da Licença Pública Geral GNU
 * junto com este programa; se não, escreva para Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307, USA.
 */  
package gcom.relatorio.cobranca;

import gcom.arrecadacao.pagamento.FiltroGuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


/**
 * classe responsável por criar o Relatório de Débitos
 * 
 * @author Cesar Medeiros
 * @date 15/04/2015
 * 
 */
public class RelatorioConsultarDebitosResponsavel extends TarefaRelatorio{


	private static final long serialVersionUID = 1L;

	public RelatorioConsultarDebitosResponsavel(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_CONSULTAR_DEBITOS_RESPONSAVEL);
	}

	
	public RelatorioConsultarDebitosResponsavel() {
		super(null, "");
	}

	private Collection<RelatorioConsultarDebitosResponsavelBean> inicializarBeanRelatorio(
		Collection<ContaValoresHelper> colecaoContas,
		Collection<DebitoACobrar> colecaoDebitosACobrar,
		Collection<CreditoARealizar> colecaoCreditosARealizar,
		Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamento) {


		Collection<RelatorioConsultarDebitosResponsavelBean> colecaoCliente = new ArrayList<RelatorioConsultarDebitosResponsavelBean>();

		Collection<RelatorioConsultarDebitosResponsavelBean> retorno = new ArrayList<RelatorioConsultarDebitosResponsavelBean>();

		Fachada fachada = Fachada.getInstancia();

		Integer idImovel = null;
		Integer qtdeEconomias = 0;
		BigDecimal valorContas = new BigDecimal("0");
		BigDecimal valorGuias = new BigDecimal("0");
		BigDecimal valorAcrescimos = new BigDecimal("0");
		BigDecimal valorDebitos = new BigDecimal("0");
		BigDecimal valorCreditos = new BigDecimal("0");
		BigDecimal valorGuiasCliente = new BigDecimal("0");
		Collection<ContaValoresHelper> colecaoContasRemover = new ArrayList<ContaValoresHelper>();
		Collection<GuiaPagamentoValoresHelper> colecaoGuiasRemover = new ArrayList<GuiaPagamentoValoresHelper>();
		Collection<DebitoACobrar> colecaoDebitosRemover = new ArrayList<DebitoACobrar>();
		Collection<CreditoARealizar> colecaoCreditoRemover = new ArrayList<CreditoARealizar>();
		int qtdeContas = 0;
		boolean iterarColecao = true;

		Collection<ContaValoresHelper> colecaoContasTemp = new ArrayList<ContaValoresHelper>();
		colecaoContasTemp.addAll(colecaoContas);

		// Ordenar a coleção pelo imóvel
		Collections.sort((List<ContaValoresHelper>) colecaoContasTemp, new Comparator<ContaValoresHelper>() {
			public int compare(ContaValoresHelper a, ContaValoresHelper b) {
				Integer imovel1 = a.getConta().getImovel().getId();
				Integer imovel2 = b.getConta().getImovel().getId();

				return imovel1.compareTo(imovel2);
			}
		});

		Collection<DebitoACobrar> colecaoDebitosACobrarTemp = new ArrayList<DebitoACobrar>();
		colecaoDebitosACobrarTemp.addAll(colecaoDebitosACobrar);

		Collection<CreditoARealizar> colecaoCreditosARealizarTemp = new ArrayList<CreditoARealizar>();
		colecaoCreditosARealizarTemp.addAll(colecaoCreditosARealizar);

		Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamentoTemp = new ArrayList<GuiaPagamentoValoresHelper>();
		colecaoGuiasPagamentoTemp.addAll(colecaoGuiasPagamento);

		while (iterarColecao) {

			RelatorioConsultarDebitosResponsavelBean bean = new RelatorioConsultarDebitosResponsavelBean();

			idImovel = null;
			BigDecimal valorTotalContas = new BigDecimal("0");
			BigDecimal valorTotalAcrescimos = new BigDecimal("0");
			BigDecimal valorTotalDebitosACobrar = new BigDecimal("0");
			BigDecimal valorTotalCreditosARealizar = new BigDecimal("0");
			BigDecimal valorTotalGuiasPagamento = new BigDecimal("0");

			// Conta
			if (!Util.isVazioOrNulo(colecaoContasTemp)) {
				for (ContaValoresHelper helper : colecaoContasTemp) {
					if (idImovel == null) {
						idImovel = helper.getConta().getImovel().getId();
						bean.setIdImovel(idImovel);
					}

					if (!helper.getConta().getImovel().getId().equals(idImovel)) {
						break;
					}

					// Valor da Conta
					if (helper.getConta().getValorTotal() != null) {
						valorContas = helper.getConta().getValorTotal();
						qtdeContas = qtdeContas + 1;						
						valorTotalContas = valorTotalContas.add(valorContas);	
						bean.setValorConta(valorTotalContas);
						colecaoContasRemover.add(helper);
					}

					// Valor dos Acréscimos por Impontualidade
					if (helper.getValorTotalContaValores() != null) {
						valorAcrescimos = helper.getValorTotalContaValores();
						valorTotalAcrescimos = valorTotalAcrescimos.add(valorAcrescimos);
						bean.setValorAcrescimos(valorTotalAcrescimos);
					}				

				}

				bean.setQtdContas(qtdeContas);
				qtdeContas = 0;
				colecaoContasTemp.removeAll(colecaoContasRemover);				
			}

			// Débito a Cobrar
			if (!Util.isVazioOrNulo(colecaoDebitosACobrarTemp)) {
				for (DebitoACobrar debitoACobrar : colecaoDebitosACobrarTemp) {
					if (idImovel == null || idImovel.equals(debitoACobrar.getImovel().getId())) {
						valorDebitos = debitoACobrar.getValorTotalComBonus();						
						valorTotalDebitosACobrar  = valorTotalDebitosACobrar.add(valorDebitos);
						colecaoDebitosRemover.add(debitoACobrar);
					}

					if (idImovel == null) {
						idImovel = debitoACobrar.getImovel().getId();
						bean.setIdImovel(idImovel);
					}
				}

				bean.setValorDebitoCobrar(valorTotalDebitosACobrar);
				colecaoDebitosACobrarTemp.removeAll(colecaoDebitosRemover);
			}

			// Crédito a Realizar
			if (!Util.isVazioOrNulo(colecaoCreditosARealizarTemp)) {
				for (CreditoARealizar creditoARealizar : colecaoCreditosARealizarTemp) {
					if (idImovel == null || idImovel.equals(creditoARealizar.getImovel().getId())) {
						valorCreditos = creditoARealizar.getValorTotalComBonus();
						valorTotalCreditosARealizar = valorTotalCreditosARealizar.add(valorCreditos);		
						colecaoCreditoRemover.add(creditoARealizar);

					}

					if (idImovel == null) {
						idImovel = creditoARealizar.getImovel().getId();
						bean.setIdImovel(idImovel);
					}
				}

				bean.setValorCreditoRealizar(valorTotalCreditosARealizar);
				colecaoCreditosARealizarTemp.removeAll(colecaoCreditoRemover);
			}

			// Guia Pagamento
			if (!Util.isVazioOrNulo(colecaoGuiasPagamentoTemp)) {
				for (GuiaPagamentoValoresHelper helper : colecaoGuiasPagamentoTemp) {
					FiltroGuiaPagamento filtro = new FiltroGuiaPagamento();
					filtro.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.ID, helper.getGuiaPagamento().getId()));

					Collection<?> colecaoGuiaPagamento = fachada.pesquisar(filtro, GuiaPagamento.class.getName());
					GuiaPagamento guiaPagamento = (GuiaPagamento) Util.retonarObjetoDeColecao(colecaoGuiaPagamento);

					if (guiaPagamento.getImovel() != null) {
						if (idImovel == null || guiaPagamento.getImovel() == null || idImovel.equals(guiaPagamento.getImovel().getId())) {
							valorGuias = helper.getGuiaPagamento().getValorDebito();
							valorAcrescimos = valorAcrescimos.add(helper.getValorAcrescimosImpontualidade());
							valorTotalGuiasPagamento = valorTotalGuiasPagamento.add(valorGuias);
							colecaoGuiasRemover.add(helper);

						}
					} else {
						valorGuiasCliente = valorGuiasCliente.add(helper.getGuiaPagamento().getValorDebito());
						colecaoGuiasRemover.add(helper);
					}

					if (idImovel == null && guiaPagamento.getImovel() != null) {
						idImovel = guiaPagamento.getImovel().getId();
						bean.setIdImovel(idImovel);
					}
				}

				bean.setValorGuiaPagamento(valorTotalGuiasPagamento);				
				colecaoGuiasPagamentoTemp.removeAll(colecaoGuiasRemover);
			}

			if (Util.isVazioOrNulo(colecaoContasTemp)
					&& Util.isVazioOrNulo(colecaoDebitosACobrarTemp)
					&& Util.isVazioOrNulo(colecaoCreditosARealizarTemp)
					&& Util.isVazioOrNulo(colecaoGuiasPagamentoTemp)) {
				iterarColecao = false;

			}

			BigDecimal totalAtualizado = valorTotalContas.add(valorTotalGuiasPagamento).add(valorTotalAcrescimos);
			BigDecimal totalGeral = valorTotalContas.add(valorTotalGuiasPagamento).add(valorTotalDebitosACobrar).add(valorTotalAcrescimos).subtract(valorTotalCreditosARealizar);

			bean.setValorAtualizado(totalAtualizado);
			bean.setValorGeral(totalGeral);					
			
			Imovel imovel = new Imovel();
			imovel.setId(idImovel);
			qtdeEconomias = fachada.obterQuantidadeEconomias(imovel);
			bean.setQtdEconomias(qtdeEconomias);
			
			colecaoCliente.add(bean);

		}		

		if(!valorGuiasCliente.equals(new BigDecimal("0"))){
		
			RelatorioConsultarDebitosResponsavelBean bean = new RelatorioConsultarDebitosResponsavelBean();
			bean.setIdImovel(0);
			bean.setQtdContas(0);
			bean.setValorConta(BigDecimal.ZERO);
			bean.setValorGuiaPagamento(valorGuiasCliente);
			bean.setValorAcrescimos(BigDecimal.ZERO);
			bean.setValorAtualizado(valorGuiasCliente);
			bean.setValorDebitoCobrar(BigDecimal.ZERO);
			bean.setValorCreditoRealizar(BigDecimal.ZERO);
			bean.setValorGeral(valorGuiasCliente);
			bean.setQtdEconomias(0);
			colecaoCliente.add(bean);
		}
		
		
		for (RelatorioConsultarDebitosResponsavelBean bean : colecaoCliente) {

			Integer imovelId = bean.getIdImovel();
			Integer idResponsavel = 99999999;
			String  nomeResponsavel = "SEM RESPONSÁVEL";

			if( imovelId != null && !imovelId.equals(0)){

				Object[] responsavel = fachada.pesquisarClienteResponsavel(imovelId);
				if(responsavel != null) {

					if(responsavel[0] !=null){
						idResponsavel = (Integer) responsavel[0];
					}
					if(responsavel[2] != null){ 
						nomeResponsavel = (String) responsavel[2];
					}
				}				
			}else{
				System.out.println("erro");
			}

			bean.setIdResponsavel(idResponsavel);
			bean.setNomeResponsavel(nomeResponsavel);

			retorno.add(bean);
		}

		// Ordenar a coleção pelo responsavel
		Collections.sort((List<RelatorioConsultarDebitosResponsavelBean>) retorno, new Comparator<RelatorioConsultarDebitosResponsavelBean>() {
			public int compare(RelatorioConsultarDebitosResponsavelBean a, RelatorioConsultarDebitosResponsavelBean b) {
				Integer responsavel1 = new Integer(a.getIdResponsavel());
				Integer responsavel2 = new Integer(b.getIdResponsavel());

				return responsavel1.compareTo(responsavel2);
			}
		});

		return retorno;
	}


	public Object executar() throws TarefaException {

		byte[] retorno = null;

		Fachada fachada = Fachada.getInstancia();

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		Collection colecaoContaValores = (Collection) getParametro("colecaoContaValores");
		Collection colecaoDebitoACobrar = (Collection) getParametro("colecaoDebitoACobrar");
		Collection colecaoCreditoARealizar = (Collection) getParametro("colecaoCreditoARealizar");
		Collection colecaoGuiaPagamento = (Collection) getParametro("colecaoGuiaPagamentoValores");

		String periodoReferenciaFinalDebito = (String)getParametro("periodoReferenciaFinalDebito") ;
		String periodoReferenciaInicialDebito = (String)getParametro("periodoReferenciaInicialDebito"); 
		String periodoVencimentoInicialDebito = (String)getParametro("dataVencimentoInicial");
		String periodoVencimentoFinalDebito = (String)getParametro("dataVencimentoFinal"); 
		String nomeCliente = (String)getParametro("nomeCliente");
		String tipoRelacao = (String)getParametro("tipoRelacao");
		String cpfCnpj = (String)getParametro("cpfCnpj");
		String codigoCliente = (String)getParametro("codigoCliente");	
		String tipo = (String)getParametro("clienteTipo");		


		Collection<RelatorioConsultarDebitosResponsavelBean> colecaoRelatorioBean = inicializarBeanRelatorio(
			colecaoContaValores, colecaoDebitoACobrar,colecaoCreditoARealizar, colecaoGuiaPagamento);				

		Collection<RelatorioConsultarDebitosResponsavelBean> colecaoRelatorioBeanCSV = new ArrayList<RelatorioConsultarDebitosResponsavelBean>();

		
		Object[] dados = fachada.pesquisarQtdeImoveisEEconomiasCliente(new Integer(codigoCliente));

		int qtdeImoveis = 0;
		int qtdeEconomias = 0;
		if (dados != null) {
			qtdeImoveis = (Integer) dados[0];
			if (dados[1] != null) {
				qtdeEconomias = (Integer) dados[1];
			}
		}

		
		if(tipoFormatoRelatorio == TarefaRelatorio.TIPO_CSV){
			
			colecaoRelatorioBeanCSV = this.montarBeanCSV(colecaoRelatorioBean,codigoCliente,nomeCliente);

		}		
		
		List<Object> relatorioBeans = new ArrayList<Object>();

		if(tipoFormatoRelatorio == TarefaRelatorio.TIPO_PDF){
			Iterator i = colecaoRelatorioBean.iterator(); 

			while(i.hasNext()){
				RelatorioConsultarDebitosResponsavelBean relatorioConsultarDebitosResponsavelBean = (RelatorioConsultarDebitosResponsavelBean) i.next();			
				relatorioBeans.add(relatorioConsultarDebitosResponsavelBean);
			}
		}else if(tipoFormatoRelatorio == TarefaRelatorio.TIPO_CSV){
		
			Iterator i = colecaoRelatorioBeanCSV.iterator(); 

			while(i.hasNext()){
				RelatorioConsultarDebitosResponsavelBean relatorioConsultarDebitosResponsavelBean = (RelatorioConsultarDebitosResponsavelBean) i.next();			
				relatorioBeans.add(relatorioConsultarDebitosResponsavelBean);
			}
			
		}
		
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		Map parametros = new HashMap();



		SistemaParametro sistemaParametro = fachada.
				pesquisarParametrosDoSistema();

		String periodoReferenciaDebito = "";
		String periodoVencimentoDebito = "";

		if(periodoReferenciaInicialDebito != null && !periodoReferenciaInicialDebito.equals("") 
				&& periodoReferenciaFinalDebito != null && !periodoReferenciaFinalDebito.equals("")){

			periodoReferenciaDebito = periodoReferenciaInicialDebito + " - " + periodoReferenciaFinalDebito;

		}

		if(periodoVencimentoInicialDebito != null && !periodoVencimentoInicialDebito.equals("")
				&& periodoVencimentoFinalDebito != null && !periodoVencimentoFinalDebito.equals("")){

			periodoVencimentoDebito = periodoVencimentoInicialDebito + " - " + periodoVencimentoFinalDebito;

		}

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("nomeEmpresa", sistemaParametro.getNomeAbreviadoEmpresa());

		String cnpjEmpresa = "";
		if (sistemaParametro.getCnpjEmpresa() != null) {
			cnpjEmpresa = Util.formatarCnpj(sistemaParametro.getCnpjEmpresa());
		}	
		parametros.put("cnpjEmpresa", cnpjEmpresa);
		parametros.put("periodoReferenciaDebito", periodoReferenciaDebito);
		parametros.put("periodoVencimentoDebito", periodoVencimentoDebito);
		parametros.put("nomeCliente", nomeCliente);
		parametros.put("tipoRelacao", tipoRelacao); 
		parametros.put("cpfCnpj", cpfCnpj);
		parametros.put("codigoCliente", codigoCliente);	

		Usuario usuario = this.getUsuario();
		String nomeUsuario = usuario.getNomeUsuario();
		parametros.put("nomeUsuario", nomeUsuario);

		if(codigoCliente != null){
			String endereco = Fachada.getInstancia().pesquisarEnderecoClienteAbreviado(new Integer(codigoCliente));
			parametros.put("endereco", endereco);
		}	
		parametros.put("tipo", tipo);
		
		parametros.put("quantidadeEconomias", qtdeEconomias);
		parametros.put("quantidadeMatricula", qtdeImoveis);	

		retorno = gerarRelatorio(
			ConstantesRelatorios.RELATORIO_CONSULTAR_DEBITOS_RESPONSAVEL,
			parametros, ds, tipoFormatoRelatorio);

		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioConsultarDebitosResponsavel", this);

	}

	//Método responsavel por totalizar os debitos por cliente responsavel
	public Collection<RelatorioConsultarDebitosResponsavelBean> montarBeanCSV (Collection<RelatorioConsultarDebitosResponsavelBean> colecaoRelatorioBean,
		String codigoClienteSuperior, String nomeClienteSuperior){

		Collection<RelatorioConsultarDebitosResponsavelBean> retorno = new ArrayList<RelatorioConsultarDebitosResponsavelBean>();
		
		Map<Integer, RelatorioConsultarDebitosResponsavelBean> totais = new TreeMap<Integer, RelatorioConsultarDebitosResponsavelBean>();
		
		for (RelatorioConsultarDebitosResponsavelBean bean : colecaoRelatorioBean) {
			RelatorioConsultarDebitosResponsavelBean beanAux = totais.get(bean.getIdResponsavel());
			
			if(beanAux == null) {
				beanAux = new RelatorioConsultarDebitosResponsavelBean();
				totais.put(bean.getIdResponsavel(), beanAux);
			}
			
			beanAux.setValorConta(beanAux.getValorConta().add(bean.getValorConta()));
			beanAux.setValorAcrescimos(beanAux.getValorAcrescimos().add(bean.getValorAcrescimos()));
			beanAux.setValorCreditoRealizar(beanAux.getValorCreditoRealizar().add(bean.getValorCreditoRealizar()));
			beanAux.setValorDebitoCobrar(beanAux.getValorDebitoCobrar().add(bean.getValorDebitoCobrar()));
			beanAux.setValorGuiaPagamento(beanAux.getValorGuiaPagamento().add(bean.getValorGuiaPagamento()));
			beanAux.setNomeResponsavel(bean.getNomeResponsavel());
			beanAux.setIdResponsavel(bean.getIdResponsavel());
			beanAux.setQtdContas(beanAux.getQtdContas() + bean.getQtdContas());
			beanAux.setQtdEconomias(beanAux.getQtdEconomias() + bean.getQtdEconomias());
			if(!bean.getIdImovel().equals(0)){
				beanAux.setQtdMatriculas(beanAux.getQtdMatriculas() + 1);
			}
			beanAux.setNomeClienteSuperior(nomeClienteSuperior);
			beanAux.setCodigoClienteSuperior(codigoClienteSuperior); 
			beanAux.setValorAtualizado(beanAux.getValorAtualizado().add(bean.getValorAtualizado()));
			beanAux.setValorGeral(beanAux.getValorGeral().add(bean.getValorGeral()));
		}

		retorno.addAll(totais.values());

		return retorno;
	}	
}
