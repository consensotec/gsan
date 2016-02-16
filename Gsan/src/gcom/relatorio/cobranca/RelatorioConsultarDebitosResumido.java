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
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
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
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.CompareToBuilder;

/**
 * classe responsável por criar o Relatório de Débitos
 * 
 * @author Rafael Corrêa
 * @date 14/02/2007
 * 
 */
public class RelatorioConsultarDebitosResumido extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	private BigDecimal valorGuiasCliente = BigDecimal.ZERO;

	public RelatorioConsultarDebitosResumido(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_CONSULTAR_DEBITOS_RESUMIDO);
	}

	@Deprecated
	public RelatorioConsultarDebitosResumido() {
		super(null, "");
	}

	private Collection<RelatorioConsultarDebitosResumidoBean> inicializarBeanRelatorio(
			Collection<ContaValoresHelper> colecaoContas,
			Collection<DebitoACobrar> colecaoDebitosACobrar,
			Collection<CreditoARealizar> colecaoCreditosARealizar,
			Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamento,
			final Boolean clienteSuperior, Integer codigoClienteInformado, String nomeClienteInformado) {
		Fachada fachada = Fachada.getInstancia();
		Collection<RelatorioConsultarDebitosResumidoBean> retorno = new ArrayList<RelatorioConsultarDebitosResumidoBean>();

		int qtdContas;
		boolean iterarColecao = true;
		Integer idImovel;
		BigDecimal valorContas;
		BigDecimal valorGuias;
		BigDecimal valorAcrescimos;
		BigDecimal valorDebitos;
		BigDecimal valorCreditos;

		Collection<ContaValoresHelper> colecaoContasRemover = new ArrayList<ContaValoresHelper>();
		Collection<GuiaPagamentoValoresHelper> colecaoGuiasRemover = new ArrayList<GuiaPagamentoValoresHelper>();
		Collection<DebitoACobrar> colecaoDebitosRemover = new ArrayList<DebitoACobrar>();
		Collection<CreditoARealizar> colecaoCreditoRemover = new ArrayList<CreditoARealizar>();

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
			idImovel = null;

			valorContas = BigDecimal.ZERO;
			valorGuias = BigDecimal.ZERO;
			valorAcrescimos = BigDecimal.ZERO;
			valorDebitos = BigDecimal.ZERO;
			valorCreditos = BigDecimal.ZERO;
			qtdContas = 0;

			// Conta
			if (!Util.isVazioOrNulo(colecaoContasTemp)) {
				for (ContaValoresHelper helper : colecaoContasTemp) {
					if (idImovel == null) {
						idImovel = helper.getConta().getImovel().getId();
					}

					if (!helper.getConta().getImovel().getId().equals(idImovel)) {
						break;
					}

					// Valor da Conta
					if (helper.getConta().getValorTotal() != null) {
						valorContas = valorContas.add(helper.getConta().getValorTotal());
						qtdContas++;
						colecaoContasRemover.add(helper);
					}

					// Valor dos Acréscimos por Impontualidade
					if (helper.getValorTotalContaValores() != null) {
						valorAcrescimos = valorAcrescimos.add(helper.getValorTotalContaValores());
					}
				}

				colecaoContasTemp.removeAll(colecaoContasRemover);
			}

			// Débito a Cobrar
			if (!Util.isVazioOrNulo(colecaoDebitosACobrarTemp)) {
				for (DebitoACobrar debitoACobrar : colecaoDebitosACobrarTemp) {
					if (idImovel == null || idImovel.equals(debitoACobrar.getImovel().getId())) {
						valorDebitos = valorDebitos.add(debitoACobrar.getValorTotalComBonus());

						colecaoDebitosRemover.add(debitoACobrar);
					}

					if (idImovel == null) {
						idImovel = debitoACobrar.getImovel().getId();
					}
				}

				colecaoDebitosACobrarTemp.removeAll(colecaoDebitosRemover);
			}

			// Crédito a Realizar
			if (!Util.isVazioOrNulo(colecaoCreditosARealizarTemp)) {
				for (CreditoARealizar creditoARealizar : colecaoCreditosARealizarTemp) {
					if (idImovel == null || idImovel.equals(creditoARealizar.getImovel().getId())) {
						valorCreditos = valorCreditos.add(creditoARealizar.getValorTotalComBonus());

						colecaoCreditoRemover.add(creditoARealizar);
					}

					if (idImovel == null) {
						idImovel = creditoARealizar.getImovel().getId();
					}
				}

				colecaoCreditosARealizarTemp.removeAll(colecaoCreditoRemover);
			}

			// Guia Pagamento
			if (!Util.isVazioOrNulo(colecaoGuiasPagamentoTemp)) {
				for (GuiaPagamentoValoresHelper helper : colecaoGuiasPagamentoTemp) {
					FiltroGuiaPagamento filtro = new FiltroGuiaPagamento();
					filtro.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.ID, helper.getGuiaPagamento()
							.getId()));

					Collection<?> colecaoGuiaPagamento = fachada.pesquisar(filtro, GuiaPagamento.class.getName());
					GuiaPagamento guiaPagamento = (GuiaPagamento) Util.retonarObjetoDeColecao(colecaoGuiaPagamento);

					if (guiaPagamento.getImovel() != null) {
						if (idImovel == null || guiaPagamento.getImovel() == null
								|| idImovel.equals(guiaPagamento.getImovel().getId())) {
							valorGuias = valorGuias.add(helper.getGuiaPagamento().getValorDebito());
							valorAcrescimos = valorAcrescimos.add(helper.getValorAcrescimosImpontualidade());

							colecaoGuiasRemover.add(helper);
						}
					} else {
						valorGuiasCliente = valorGuiasCliente.add(helper.getGuiaPagamento().getValorDebito());
						colecaoGuiasRemover.add(helper);
					}

					if (idImovel == null && guiaPagamento.getImovel() != null) {
						idImovel = guiaPagamento.getImovel().getId();
					}
				}

				colecaoGuiasPagamentoTemp.removeAll(colecaoGuiasRemover);
			}

			if (Util.isVazioOrNulo(colecaoContasTemp)
					&& Util.isVazioOrNulo(colecaoDebitosACobrarTemp)
					&& Util.isVazioOrNulo(colecaoCreditosARealizarTemp)
					&& Util.isVazioOrNulo(colecaoGuiasPagamentoTemp)) {
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
			Integer idResponsavel = new Integer(99999999);
			String nomeResponsavel = "SEM RESPONSÁVEL";
			String cpfCnpjResponsavel = "";
			String enderecoResponsavel = "";
			Integer qtdeEconomias = 0;
			Integer codigoClienteUsuario = null;
			
			if (idImovel != null) {
				Object[] cliente = fachada.consultarDadosClienteUsuarioImovel(idImovel + "");

				if (cliente[0] != null) {
					nomeUsuario = (String) cliente[0];
				}

				if (cliente[1] != null) {
					cpfCnpjUsuario = Util.formatarCpf((String) cliente[1]);
				} else if (cliente[2] != null) {
					cpfCnpjUsuario = Util.formatarCnpj((String) cliente[2]);
				}
				
			if(cliente[3] != null){
				codigoClienteUsuario = (Integer) cliente[3];
			}
				
				enderecoImovel = fachada.pesquisarEndereco(idImovel);

				LigacaoAguaSituacao ligacaoAguaSituacao = fachada.pesquisarLigacaoAguaSituacao(idImovel);
				descricaoLigacaoAguaSituacao = ligacaoAguaSituacao.getDescricaoAbreviado();

				LigacaoEsgotoSituacao ligacaoEsgotoSituacao = fachada.pesquisarLigacaoEsgotoSituacao(idImovel);
				descricaoLigacaoEsgotoSituacao = ligacaoEsgotoSituacao.getDescricaoAbreviado();

				Imovel imovel = new Imovel();
				imovel.setId(idImovel);
				
				qtdeEconomias = fachada.obterQuantidadeEconomias(imovel);

				Categoria categoria = fachada.obterDescricoesCategoriaImovel(imovel);
				descricaoCategoria = categoria.getDescricao();

				imovelString = imovel.getId().toString();

				Object[] responsavel = null;
				if(clienteSuperior) {
					responsavel = fachada.pesquisarClienteResponsavel(idImovel);
				}

				if(responsavel != null) {
					idResponsavel = (Integer) responsavel[0];
					nomeResponsavel = (String) responsavel[2];
					if(responsavel[1] != null) {
						cpfCnpjResponsavel = (String) responsavel[1];
						cpfCnpjResponsavel = cpfCnpjResponsavel.length() <= 11 ?
								Util.formatarCpf(cpfCnpjResponsavel) :
								Util.formatarCnpj(cpfCnpjResponsavel);
					}

					Collection<?> colecao = fachada.pesquisarClientesEnderecosAbreviado(idResponsavel);
					Object endereco = Util.retonarObjetoDeColecao(colecao);
					if(endereco != null) enderecoResponsavel = endereco.toString();
				}
			}

			BigDecimal totalAtualizado = valorContas.add(valorGuias).add(valorAcrescimos);
			BigDecimal totalGeral = totalAtualizado.add(valorDebitos).subtract(valorCreditos);
			
//			Integer codigoClienteSuperiorInt = null;
//			if(codigoClienteSuperior =! null){
//				codigoClienteSuperiorInt = Integer.valueOf(codigoClienteSuperior);
//			}
			
			

			RelatorioConsultarDebitosResumidoBean bean = new RelatorioConsultarDebitosResumidoBean(imovelString,
					inscricaoImovel, nomeUsuario, cpfCnpjUsuario, enderecoImovel, descricaoLigacaoAguaSituacao,
					descricaoLigacaoEsgotoSituacao, descricaoCategoria, qtdContas, valorContas, valorGuias,
					valorAcrescimos, totalAtualizado, valorDebitos, valorCreditos, totalGeral, idResponsavel,
					nomeResponsavel, cpfCnpjResponsavel, enderecoResponsavel, qtdeEconomias, 
					codigoClienteUsuario, codigoClienteInformado, nomeClienteInformado);

			retorno.add(bean);
		}

		// Ordenar a coleção pelo responsável e imóvel
		Collections.sort((List<RelatorioConsultarDebitosResumidoBean>) retorno,
				new Comparator<RelatorioConsultarDebitosResumidoBean>() {
		    public int compare(RelatorioConsultarDebitosResumidoBean a, RelatorioConsultarDebitosResumidoBean b) {
		        return new CompareToBuilder()
		        .append(a.getIdResponsavel(), b.getIdResponsavel())
        		.append(a.getIdImovel(), b.getIdImovel())
		        .toComparison();
		    }
		});

		return retorno;
	}

	/**
	 * Método que executa a tarefa
	 * 
	 * @return Object
	 */
	public Object executar() throws TarefaException {
		byte[] retorno = null;

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
		Boolean clienteSuperior = (Boolean)getParametro("clienteSuperior");

		Map<String, Object> parametros = new HashMap<String, Object>();
		Fachada fachada = Fachada.getInstancia();
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		Object[] dados = fachada.pesquisarQtdeImoveisEEconomiasCliente(new Integer(codigoCliente));
		
		int qtdeImoveis = 0;
		int qtdeEconomias = 0;
		if (dados != null) {
			qtdeImoveis = (Integer) dados[0];
			if (dados[1] != null) {
				qtdeEconomias = (Integer) dados[1];
			}
		}

		FiltroCliente filtroCliente = new FiltroCliente();
		filtroCliente.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");
		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, new Integer(codigoCliente)));
		
		Collection<?> colecaoClientes = fachada.pesquisar(filtroCliente, Cliente.class.getName());
		Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(colecaoClientes);

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("codigoCliente", codigoCliente);
		parametros.put("nomeCliente", nomeCliente);

		String cnpjEmpresa = "";
		if (sistemaParametro.getCnpjEmpresa() != null) {
			cnpjEmpresa = Util.formatarCnpj(sistemaParametro.getCnpjEmpresa());
		}

		parametros.put("nomeUsuario", getUsuario().getNomeUsuario());
		parametros.put("nomeEmpresa", sistemaParametro.getNomeAbreviadoEmpresa());
		parametros.put("cnpjEmpresa", cnpjEmpresa);
		parametros.put("clienteSuperior", clienteSuperior);

		if (cliente.getCpf() != null) {
			cpfCnpj = cliente.getCpfFormatado();
		} else if (cliente.getCnpj() != null) {
			cpfCnpj = cliente.getCnpjFormatado();
		}
		if (Util.verificarNaoVazio(cpfCnpj)) {
			parametros.put("cpfCnpj", cpfCnpj);
		}

		if (Util.verificarNaoVazio(periodoReferenciaInicial)) {
			parametros.put("periodoReferenciaDebito", periodoReferenciaInicial + " a " + periodoReferenciaFinal);
		}

		if (Util.verificarNaoVazio(dataVencimentoInicial)) {
			parametros.put("periodoVencimentoDebito", dataVencimentoInicial + " a " + dataVencimentoFinal);
		}

		if (tipoRelacao != null) {
			parametros.put("tipoRelacao", tipoRelacao.getDescricao());
		}

		parametros.put("qtdeEconomias", "" + qtdeEconomias);
		parametros.put("qtdeMatriculas", "" + qtdeImoveis);
		parametros.put("tipoCliente", cliente.getClienteTipo().getDescricao());

		String endereco = fachada.pesquisarEnderecoClienteAbreviado(new Integer(codigoCliente));
		if (endereco != null) {
			parametros.put("endereco", endereco);
		}
		
        String codClieInf= codigoCliente;
        Integer codigoClienteInformado = null;

        if(!codClieInf.equals(null)){
        	codigoClienteInformado = Integer.valueOf(codClieInf);
        }		

        String nomeClienteInformado = nomeCliente;
        
        
        Collection<RelatorioConsultarDebitosResumidoBean> colecaoBean = inicializarBeanRelatorio(
				colecaoContaValores, colecaoDebitoACobrar, colecaoCreditoARealizar, colecaoGuiaPagamento, clienteSuperior, 
				codigoClienteInformado, nomeClienteInformado);
		
		if (Util.isVazioOrNulo(colecaoBean)) {
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		BigDecimal valorTotalDebitos =
				Util.formatarMoedaRealparaBigDecimal((String) getParametro("valorTotalDebitos"));
		BigDecimal valorTotalDebitosAtualizado =
				Util.formatarMoedaRealparaBigDecimal((String) getParametro("valorTotalDebitosAtualizado"));

		parametros.put("valorTotalDebitos", valorTotalDebitos);
		parametros.put("valorTotalDebitosAtualizado", valorTotalDebitosAtualizado);
		parametros.put("valorGuiasCliente", valorGuiasCliente);
		
		
		if(tipoFormatoRelatorio == TIPO_CSV && valorGuiasCliente!=BigDecimal.ZERO){
			RelatorioConsultarDebitosResumidoBean relatorioConsultarDebitosResumidoBean = new RelatorioConsultarDebitosResumidoBean(null, null, 
					null, null, null, null, null, null, 0, BigDecimal.ZERO, valorGuiasCliente, BigDecimal.ZERO, valorGuiasCliente, 
					BigDecimal.ZERO, BigDecimal.ZERO, valorGuiasCliente, 99999999, "SEM RESPONSÁVEL", null, null, 0, null, null, null);
			colecaoBean.add(relatorioConsultarDebitosResumidoBean);
		}
		
		RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);
		return gerarRelatorio(ConstantesRelatorios.RELATORIO_CONSULTAR_DEBITOS_RESUMIDO,
				parametros, ds,	tipoFormatoRelatorio);
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioConsultarDebitosResumido", this);
	}
}
