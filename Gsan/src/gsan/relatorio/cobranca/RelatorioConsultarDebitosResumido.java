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
package gsan.relatorio.cobranca;

import gsan.arrecadacao.pagamento.FiltroGuiaPagamento;
import gsan.arrecadacao.pagamento.GuiaPagamento;
import gsan.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.cliente.ClienteRelacaoTipo;
import gsan.cadastro.cliente.FiltroCliente;
import gsan.cadastro.imovel.Categoria;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cobranca.bean.ContaValoresHelper;
import gsan.cobranca.bean.GuiaPagamentoValoresHelper;
import gsan.fachada.Fachada;
import gsan.faturamento.credito.CreditoARealizar;
import gsan.faturamento.debito.DebitoACobrar;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.RelatorioDataSource;
import gsan.relatorio.RelatorioVazioException;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaException;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.Util;
import gsan.util.agendadortarefas.AgendadorTarefas;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe respons�vel por criar o Relat�rio de D�bitos
 * 
 * @author Rafael Corr�a
 * @date 14/02/2007
 * 
 */
public class RelatorioConsultarDebitosResumido extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioConsultarDebitosResumido(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_CONSULTAR_DEBITOS_RESUMIDO);
	}

	@Deprecated
	public RelatorioConsultarDebitosResumido() {
		super(null, "");
	}

	private Collection<RelatorioConsultarDebitosResumidoBean> inicializarBeanRelatorio(
			Collection<ContaValoresHelper> colecaoContas,
			Collection<DebitoACobrar> colecaoDebitosACobrar,
			Collection<CreditoARealizar> colecaoCreditosARealizar,
			Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamento) {

		Collection<RelatorioConsultarDebitosResumidoBean> retorno = new ArrayList<RelatorioConsultarDebitosResumidoBean>();

		String valorTotalDebitos = (String) getParametro("valorTotalDebitos");
		String valorTotalDebitosAtualizado = (String) getParametro("valorTotalDebitosAtualizado");

		Fachada fachada = Fachada.getInstancia();

		Integer idImovel = null;
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

		// Ordenar a cole��o pelo im�vel
		Collections.sort((List) colecaoContasTemp, new Comparator() {
			public int compare(Object a, Object b) {
				Integer imovel1 = new Integer(((ContaValoresHelper) a)
						.getConta().getImovel().getId());
				Integer imovel2 = new Integer(((ContaValoresHelper) b)
						.getConta().getImovel().getId());
				

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

			// Conta
			if (colecaoContasTemp != null && !colecaoContasTemp.isEmpty()) {

				Iterator colecaoContasIterator = colecaoContasTemp.iterator();

				while (colecaoContasIterator.hasNext()) {

					ContaValoresHelper contaValoresHelper = (ContaValoresHelper) colecaoContasIterator
							.next();

					if (idImovel == null) {
						idImovel = contaValoresHelper.getConta().getImovel()
								.getId();
					}

					if (!contaValoresHelper.getConta().getImovel().getId()
							.equals(idImovel)) {
						break;
					}

					// Valor da Conta
					if (contaValoresHelper.getConta().getValorTotal() != null) {
						valorContas = valorContas.add(contaValoresHelper
								.getConta().getValorTotal());
						
						qtdeContas = qtdeContas + 1;

						colecaoContasRemover.add(contaValoresHelper);
					}

					// Valor dos Acr�scimos por Impontualidade
					if (contaValoresHelper.getValorTotalContaValores() != null) {
						valorAcrescimos = valorAcrescimos
								.add(contaValoresHelper
										.getValorTotalContaValores());
					}
				}

				colecaoContasTemp.removeAll(colecaoContasRemover);

			}

			// D�bito a Cobrar
			if (colecaoDebitosACobrarTemp != null
					&& !colecaoDebitosACobrarTemp.isEmpty()) {

				Iterator colecaoDebitosACobrarIterator = colecaoDebitosACobrarTemp
						.iterator();

				while (colecaoDebitosACobrarIterator.hasNext()) {

					DebitoACobrar debitoACobrar = (DebitoACobrar) colecaoDebitosACobrarIterator
							.next();

					if (idImovel == null
							|| idImovel.equals(debitoACobrar.getImovel()
									.getId())) {
						valorDebitos = valorDebitos.add(debitoACobrar
								.getValorTotalComBonus());

						colecaoDebitosRemover.add(debitoACobrar);
					}

					if (idImovel == null) {
						idImovel = debitoACobrar.getImovel().getId();
					}

				}

				colecaoDebitosACobrarTemp.removeAll(colecaoDebitosRemover);

			}

			// Cr�dito a Realizar
			if (colecaoCreditosARealizarTemp != null
					&& !colecaoCreditosARealizarTemp.isEmpty()) {

				Iterator colecaoCreditosARealizarIterator = colecaoCreditosARealizarTemp
						.iterator();

				while (colecaoCreditosARealizarIterator.hasNext()) {

					CreditoARealizar creditoARealizar = (CreditoARealizar) colecaoCreditosARealizarIterator
							.next();

					if (idImovel == null
							|| idImovel.equals(creditoARealizar.getImovel()
									.getId())) {
						valorCreditos = valorCreditos.add(creditoARealizar
								.getValorTotalComBonus());

						colecaoCreditoRemover.add(creditoARealizar);
					}

					if (idImovel == null) {
						idImovel = creditoARealizar.getImovel().getId();
					}

				}

				colecaoCreditosARealizarTemp.removeAll(colecaoCreditoRemover);

			}

			// Guia Pagamento
			if (colecaoGuiasPagamentoTemp != null
					&& !colecaoGuiasPagamentoTemp.isEmpty()) {

				Iterator colecaoGuiasPagamentoIterator = colecaoGuiasPagamentoTemp
						.iterator();

				while (colecaoGuiasPagamentoIterator.hasNext()) {

					GuiaPagamentoValoresHelper guiaPagamentoValoresHelper = (GuiaPagamentoValoresHelper) colecaoGuiasPagamentoIterator
							.next();
					
					FiltroGuiaPagamento filtroGuiaPagamento = new FiltroGuiaPagamento();
					filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.ID, guiaPagamentoValoresHelper.getGuiaPagamento().getId()));
					
					Collection colecaoGuiaPagamento = fachada.pesquisar(filtroGuiaPagamento, GuiaPagamento.class.getName());
					
					GuiaPagamento guiaPagamento = (GuiaPagamento) Util.retonarObjetoDeColecao(colecaoGuiaPagamento);

					if (guiaPagamento.getImovel() != null) {
						if (idImovel == null
								|| guiaPagamento.getImovel() == null || idImovel
										.equals(guiaPagamento.getImovel()
												.getId())) {
							valorGuias = valorGuias
									.add(guiaPagamentoValoresHelper
											.getGuiaPagamento()
											.getValorDebito());
							valorAcrescimos = valorAcrescimos
									.add(guiaPagamentoValoresHelper
											.getValorAcrescimosImpontualidade());

							colecaoGuiasRemover.add(guiaPagamentoValoresHelper);
						}

					} else {
						valorGuiasCliente = valorGuiasCliente
									.add(guiaPagamentoValoresHelper
												.getGuiaPagamento()
												.getValorDebito());
						
						colecaoGuiasRemover.add(guiaPagamentoValoresHelper);
						
					}

					if (idImovel == null) {
						if (guiaPagamento.getImovel() != null) {
							idImovel = guiaPagamento.getImovel().getId();
						}
					}

				}

				colecaoGuiasPagamentoTemp.removeAll(colecaoGuiasRemover);

			}

			if ((colecaoContasTemp == null || colecaoContasTemp.isEmpty())
					&& (colecaoDebitosACobrarTemp == null || colecaoDebitosACobrarTemp
							.isEmpty())
					&& (colecaoCreditosARealizarTemp == null || colecaoCreditosARealizarTemp
							.isEmpty())
					&& (colecaoGuiasPagamentoTemp == null || colecaoGuiasPagamentoTemp
							.isEmpty())) {
				iterarColecao = false;
			}

			String inscricaoImovel = fachada.pesquisarInscricaoImovel(idImovel);
			
			String nomeUsuario = "";
			String cpfCnpjUsuario = "";
			String enderecoImovel = "";
			String descricaoLigacaoAguaSituacao = "";
			String descricaoLigacaoEsgotoSituacao = "";
			String descricaoCategoria = "";
			String imovelString = "";
			Object[] dadosCliente = null;
			
			if(idImovel != null){
				dadosCliente = fachada.consultarDadosClienteUsuarioImovel(idImovel+"");	

				if ( dadosCliente[0] != null ) {
					nomeUsuario = (String) dadosCliente[0];
				}
				
				if ( dadosCliente[1] != null ) {
					cpfCnpjUsuario = Util.formatarCpf((String)dadosCliente[1]);
				}else if ( dadosCliente[2] != null ) {
					cpfCnpjUsuario = Util.formatarCnpj((String) dadosCliente[2]);
				}

				enderecoImovel = fachada.pesquisarEndereco(idImovel);
				
				LigacaoAguaSituacao ligacaoAguaSituacao = fachada.pesquisarLigacaoAguaSituacao(idImovel);
				descricaoLigacaoAguaSituacao = ligacaoAguaSituacao.getDescricaoAbreviado();
				
				LigacaoEsgotoSituacao ligacaoEsgotoSituacao = fachada.pesquisarLigacaoEsgotoSituacao(idImovel);
				descricaoLigacaoEsgotoSituacao = ligacaoEsgotoSituacao.getDescricaoAbreviado();

				Imovel imovel = new Imovel();
				imovel.setId(idImovel);
				
				Categoria categoria = fachada.obterDescricoesCategoriaImovel(imovel);
				descricaoCategoria = categoria.getDescricao();
				
				imovelString = imovel.getId().toString();
			}
			
			BigDecimal totalAtualizado = valorContas.add(valorGuias).add(valorAcrescimos);
			BigDecimal totalGeral = totalAtualizado.add(valorDebitos).subtract(valorCreditos);
			

			RelatorioConsultarDebitosResumidoBean bean = new RelatorioConsultarDebitosResumidoBean(
			// Im�vel
					imovelString,

					// Inscri��o
					inscricaoImovel,

					// Nome Usu�rio
					nomeUsuario,
					
					//Cpf/Cnpj do Usuario
					cpfCnpjUsuario,

					// Endere�o
					enderecoImovel,

					// Situa��o da Liga��o de �gua
					descricaoLigacaoAguaSituacao,

					// Situa��o da Liga��o de Esgoto
					descricaoLigacaoEsgotoSituacao,

					// Categoria
					descricaoCategoria,

					// Valor das Contas
					qtdeContas + " - R$" + Util.formatarMoedaReal(valorContas),

					// Valor das Guias de Pagamento
					"R$" + Util.formatarMoedaReal(valorGuias),

					// Valor dos Acr�scimos
					"R$" + Util.formatarMoedaReal(valorAcrescimos),

					// Total Atualizado
					"R$" + Util.formatarMoedaReal(totalAtualizado),

					// Valor dos D�bitos a Cobrar
					"R$" + Util.formatarMoedaReal(valorDebitos),

					// Valor dos Cr�ditos a Realizar
					"R$" + Util.formatarMoedaReal(valorCreditos),

					// Total Geral
					"R$" + Util.formatarMoedaReal(totalGeral),

					// Valor Total dos D�bitos
					null,

					// Valor Total dos D�bitos Atualizado
					null,
					
					//Valor guias de pagamento do cliente sem imovel
					null);

			retorno.add(bean);

			idImovel = null;

			valorContas = new BigDecimal("0");
			valorGuias = new BigDecimal("0");
			valorAcrescimos = new BigDecimal("0");
			valorDebitos = new BigDecimal("0");
			valorCreditos = new BigDecimal("0");
			qtdeContas = 0;

		}

		// Ordenar a cole��o pelo im�vel
		Collections.sort((List) retorno, new Comparator() {
			public int compare(Object a, Object b) {
				Integer imovel1 = new Integer(
						((RelatorioConsultarDebitosResumidoBean) a)
								.getIdImovel());
				Integer imovel2 = new Integer(
						((RelatorioConsultarDebitosResumidoBean) b)
								.getIdImovel());
				

				return imovel1.compareTo(imovel2);

			}
		});

		
		
		if (valorTotalDebitos != null) {

			
			RelatorioConsultarDebitosResumidoBean bean = new RelatorioConsultarDebitosResumidoBean(
			// Im�vel
					null,

					// Inscri��o
					null,

					// Nome Usu�rio
					null,
					
					//CPF Usu�rio
					null,

					// Endere�o
					null,

					// Situa��o da Liga��o de �gua
					null,

					// Situa��o da Liga��o de Esgoto
					null,

					// Categoria
					null,

					// Valor das Contas
					null,

					// Valor das Guias de Pagamento
					null,

					// Valor dos Acr�scimos
					null,

					// Total Atualizado
					null,

					// Valor dos D�bitos a Cobrar
					null,

					// Valor dos Cr�ditos a Realizar
					null,

					// Total Geral
					null,

					// Valor Total dos D�bitos
					"R$" + valorTotalDebitos,

					// Valor Total dos D�bitos Atualizado
					"R$" + valorTotalDebitosAtualizado,
					
					//Valor guias de pagamento do cliente sem imovel
					"R$" + Util.formatarMoedaReal(valorGuiasCliente));

			retorno.add(bean);
		}

		return retorno;
	}

	/**
	 * M�todo que executa a tarefa
	 * 
	 * @return Object
	 */
	public Object executar() throws TarefaException {

		Collection colecaoContaValores = (Collection) getParametro("colecaoContaValores");
		Collection colecaoDebitoACobrar = (Collection) getParametro("colecaoDebitoACobrar");
		Collection colecaoCreditoARealizar = (Collection) getParametro("colecaoCreditoARealizar");
		Collection colecaoGuiaPagamento = (Collection) getParametro("colecaoGuiasPagamento");
		String codigoCliente = (String) getParametro("codigoCliente");
		String nomeCliente = (String) getParametro("nomeCliente");
		ClienteRelacaoTipo tipoRelacao = (ClienteRelacaoTipo) getParametro("tipoRelacao");
		String periodoReferenciaInicial = (String) getParametro("periodoReferenciaInicial");
		String periodoReferenciaFinal = (String) getParametro("periodoReferenciaFinal");
		String dataVencimentoInicial = (String) getParametro("dataVencimentoInicial");
		String dataVencimentoFinal = (String) getParametro("dataVencimentoFinal");
		String cpfCnpj = (String) getParametro("cpfCnpj");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		Fachada fachada = Fachada.getInstancia();

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();
		
		Object[] dados = fachada.pesquisarQtdeImoveisEEconomiasCliente(new Integer(codigoCliente));
		
		int qtdeImoveis = 0;
		int qtdeEconomias = 0;
		if (dados != null){
		   qtdeImoveis = (Integer) dados[0];
			if (dados[1] != null){
				qtdeEconomias = (Integer) dados[1];
			}
		}
		
		String endereco = fachada.pesquisarEnderecoClienteAbreviado(new Integer(codigoCliente));
	
		FiltroCliente filtroCliente = new FiltroCliente();
		filtroCliente.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");
		
		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, new Integer(codigoCliente)));
		
		Collection colecaoClientes = fachada.pesquisar(filtroCliente, Cliente.class.getName());
		
		Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(colecaoClientes);

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("codigoCliente", codigoCliente);
		parametros.put("nomeCliente", nomeCliente);
		
		String cnpjEmpresa = "";
		if (sistemaParametro.getCnpjEmpresa() != null) {
			cnpjEmpresa = Util.formatarCnpj(sistemaParametro.getCnpjEmpresa());
		}
		
		//Usuario que emite o relatorio
		Usuario usuario = this.getUsuario();

		String nomeUsuario = "";
			nomeUsuario = usuario.getNomeUsuario();
	
		parametros.put("nomeUsuario", nomeUsuario);
		parametros.put("nomeEmpresa",sistemaParametro.getNomeAbreviadoEmpresa());
		parametros.put("cnpjEmpresa", cnpjEmpresa);
		
		if ( cliente.getCpf() != null ){
			cpfCnpj = cliente.getCpfFormatado();
		} else if ( cliente.getCnpj() != null ){
			cpfCnpj = cliente.getCnpjFormatado();
		}
		if ( cpfCnpj != null && !cpfCnpj.equals("") ) {
			parametros.put("cpfCnpj", cpfCnpj);
		} else {
			cpfCnpj ="";
		}
		
		if (periodoReferenciaInicial != null
				&& !periodoReferenciaInicial.trim().equals("")) {
			parametros.put("periodoReferenciaDebito", periodoReferenciaInicial
					+ " a " + periodoReferenciaFinal);
		}

		if (dataVencimentoInicial != null
				&& !dataVencimentoInicial.trim().equals("")) {
			parametros.put("periodoVencimentoDebito", dataVencimentoInicial
					+ " a " + dataVencimentoFinal);
		}

		if (tipoRelacao != null) {
			parametros.put("tipoRelacao", tipoRelacao.getDescricao());
		}
		
		parametros.put("qtdeEconomias", "" + qtdeEconomias);
		parametros.put("qtdeMatriculas", "" + qtdeImoveis);
		parametros.put("tipoCliente", cliente.getClienteTipo().getDescricao());
		
		if (endereco != null) {
			parametros.put("endereco", endereco);
		}

		Collection<RelatorioConsultarDebitosResumidoBean> colecaoBean = this
				.inicializarBeanRelatorio(colecaoContaValores,
						colecaoDebitoACobrar, colecaoCreditoARealizar,
						colecaoGuiaPagamento);

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// N�o existem dados para a exibi��o do relat�rio.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_CONSULTAR_DEBITOS_RESUMIDO,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relat�rio no sistema
		// try {
		// persistirRelatorioConcluido(retorno,
		// Relatorio.MEDICAO_CONSUMO_LIGACAO_AGUA,
		// idFuncionalidadeIniciada);
		// } catch (ControladorException e) {
		// e.printStackTrace();
		// throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		// }
		// ------------------------------------

		// retorna o relat�rio gerado
		return retorno;

	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioConsultarDebitosResumido",
				this);
	}
}