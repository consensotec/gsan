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
* Ivan S�rgio Virginio da Silva J�nior
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
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.EmitirContaHelper;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * [UC0482] Emitir 2� Via de Conta
 * 
 * @author Vivianne Sousa
 * @date 15/09/2006
 */

public class Relatorio2ViaConta extends TarefaRelatorio {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7034984685957706140L;

	/**
	 * Quantidade m�xima de linhas do detail da primeira p�gina da conta
	 */
	public final static int NUMERO_MAX_lINHAS_DETAIL_PRIMEIRA_PAGINA = 11; 
	
	/**
	 * Quantidade m�xima de linhas do detail a partir da segunda p�gina do rel�torio
	 */
	public final static int NUMERO_MAX_lINHAS_DETAIL_OUTRAS_PAGINAS = 43;
	
	/**
	 * Quantidade m�xima de linhas do detail da primeira p�gina do boleto bancario
	 */
	public final static int NUMERO_MAX_lINHAS_DETAIL_PRIMEIRA_PAGINA_BOLETO = 4;
		
	public Relatorio2ViaConta(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_2_VIA_CONTA);
	}
	
	public Relatorio2ViaConta(Usuario usuario, String nomeRelatorio) {
		super(usuario, nomeRelatorio);
	}
	
	protected Collection<Relatorio2ViaContaBean> inicializarBeanRelatorio(
			Collection colecaoEmitirContaHelper, SistemaParametro sistemaParametro, String empresa, Fachada fachada,
			Integer idCliente) {
		
		BigDecimal valorPis = new BigDecimal("0.0");
		BigDecimal valorCofins = new BigDecimal("0.0");
		BigDecimal baseCalculo = new BigDecimal("0.0");
		BigDecimal percentualPis = ConstantesSistema.PERCENTUAL_PIS;
		BigDecimal percentualCofins = ConstantesSistema.PERCENTUAL_CONFINS;			
		
		
		String enderecoEmpresa = null;
		
		Collection<Relatorio2ViaContaBean> retorno = new ArrayList<Relatorio2ViaContaBean>();
		String cedente = sistemaParametro.getNomeAbreviadoEmpresa() + "-" + sistemaParametro.getNomeEmpresa();
		
		String nomeCliente = null;
		String documentoCliente = null;
		if(idCliente != null){
			FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
			filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_ID, idCliente));
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.CLIENTE);
			
			Collection<?> colClienteImovel = fachada.pesquisar(filtroClienteImovel, ClienteImovel.class.getName());
			if(!Util.isVazioOrNulo(colClienteImovel)){
				ClienteImovel clienteImovel = (ClienteImovel) Util.retonarObjetoDeColecao(colClienteImovel);
				if(clienteImovel != null && clienteImovel.getCliente() != null){
					nomeCliente = clienteImovel.getCliente().getNome();
					
					if(clienteImovel.getCliente().getCpf() != null){
						documentoCliente = clienteImovel.getCliente().getCpfFormatado();
					}else if(clienteImovel.getCliente().getCnpj() != null){
						documentoCliente = clienteImovel.getCliente().getCnpjFormatado();
					}
				}
			}
		}
		
		Iterator iter = colecaoEmitirContaHelper.iterator();
		while (iter.hasNext()) {
			
			EmitirContaHelper emitirContaHelper = (EmitirContaHelper) iter.next();
			
			enderecoEmpresa = null;
			
			if(sistemaParametro.getNomeAbreviadoEmpresa().toString().equals("CAEMA")){
				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

				filtroLocalidade.adicionarParametro(
					new ParametroSimples(
						FiltroLocalidade.ID,
						emitirContaHelper.getIdLocalidade()));

				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroCep");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
				filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("unidadeNegocio");

				Collection cLocalidade = 
					(Collection) fachada.pesquisar(
						filtroLocalidade,Localidade.class.getName());
				
				Localidade localidade = (Localidade) cLocalidade.iterator().next();
				
				String numeroFatura = emitirContaHelper.getIdConta()+ "/" + 
					Util.formatarAnoMesParaMesAnoSemBarra(emitirContaHelper.getAmReferencia());
				String endereco = localidade.getEnderecoFormatadoTituloAbreviado();
				String telefone = Util.completaString(localidade.getFone(), 9);
				String cnpj = Util.formatarCnpj(sistemaParametro.getCnpjEmpresa());
				String inscricaoEstadual = Util.formatarInscricaoEstadualCaema(sistemaParametro.getInscricaoEstadual());
				String dataEmissao = Util.formatarData(new Date());
				/*
				empresa = "N� Fatura:"+numeroFatura+"    "+endereco+"\n"+
					"Emitida em:"+dataEmissao+"   Cnpj:"+cnpj+"    Fone:"+telefone+"\n" +
					" Insc. Estadual:"+inscricaoEstadual;
				*/
				enderecoEmpresa = "N�Fatura:"+numeroFatura+"\n"+ 
				"Emitida em:"+dataEmissao+"\n";
				
				empresa = endereco+"\n"+
					"Cnpj:"+cnpj+"  Fone:"+telefone+"\n" +
					"Insc. Estadual:"+inscricaoEstadual;	
			}else{
				valorPis = new BigDecimal("0.0");
				valorCofins = new BigDecimal("0.0");
				baseCalculo = new BigDecimal("0.0");				

				baseCalculo = baseCalculo.add(emitirContaHelper.getValorBaseCalculo());				

				valorPis = percentualPis.multiply(baseCalculo);
				valorCofins = percentualCofins.multiply(baseCalculo);
			}
			
			String baseCalculosFormatada = Util.formatarMoedaReal(baseCalculo);		
			String percentualPisFormatado = "1,65";		
			String percentualCofinsFormatado = "7,6";		
			String valorPisFormatado = Util.formatarMoedaReal(valorPis);		
			String valorCofinsFormatado = Util.formatarMoedaReal(valorCofins);			
			
			String codigoRota = null;
			
			if(emitirContaHelper.getRotaEntrega()!= null &&	!emitirContaHelper.getRotaEntrega().equals("")){
				codigoRota = emitirContaHelper.getRotaEntrega();
			}
			
			String debitoCreditoSituacaoAtualConta = "";
			if (emitirContaHelper.getDebitoCreditoSituacaoAtualConta()!= null){
				debitoCreditoSituacaoAtualConta = emitirContaHelper.getDebitoCreditoSituacaoAtualConta().toString();
			}
			
			String contaPaga = null;
			if(emitirContaHelper.getContaPaga() != null && !emitirContaHelper.getContaPaga().equals("")){
				contaPaga = emitirContaHelper.getContaPaga();
			}
			
			String enderecoClienteResponsavel = "";
			String enderecoClienteResponsavelLinha2 = "";
			if(emitirContaHelper.getEnderecoClienteResponsavel() != null && !emitirContaHelper.getEnderecoClienteResponsavel().equals("")){
				
				if(emitirContaHelper.getEnderecoClienteResponsavel().length() >= 64){
					enderecoClienteResponsavel = emitirContaHelper.getEnderecoClienteResponsavel().substring(0,64);
					enderecoClienteResponsavelLinha2 = emitirContaHelper.getEnderecoClienteResponsavel().substring(64);
				}else{
					enderecoClienteResponsavel = emitirContaHelper.getEnderecoClienteResponsavel();
				}
			}
		
			Collection colecaolinhasDescricaoServicosTarifasTotal = emitirContaHelper.getColecaoContaLinhasDescricaoServicosTarifasTotalHelper();

			Collection<Relatorio2ViaContaDetailBean> colecaoDetail = new ArrayList<Relatorio2ViaContaDetailBean>();
			Collection<Relatorio2ViaContaBoletoBancarioBean> colecaoBoleto = new ArrayList<Relatorio2ViaContaBoletoBancarioBean>();

			int totalLinhasRelatorio = 0;
			int totalPaginasRelatorio = 1;
			int indicadorPrimeiraPagina = 1;

			String dataVencimentoFormatada = Util.formatarData(emitirContaHelper.getDataVencimentoConta());
			
			Boolean ehBoleto = false;
			int boleto = 2;
			int numeroMaxLinhasDetailPrimeiraPagina = NUMERO_MAX_lINHAS_DETAIL_PRIMEIRA_PAGINA;
			
			if((sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_COMPESA)
				&& emitirContaHelper.getValorConta()!= null && emitirContaHelper.getValorConta().
				compareTo(EmitirContaHelper.VALOR_LIMITE_FICHA_COMPENSACAO) == 1) 
				|| (sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAERN)
					&& sistemaParametro.getValorContaFichaComp() != null 
					&& !sistemaParametro.getValorContaFichaComp().equals(new BigDecimal("0.00"))
					&& emitirContaHelper.getValorConta()!= null 
					&& emitirContaHelper.getValorConta().compareTo(sistemaParametro.getValorContaFichaComp()) == 1) ){
				
				ehBoleto = true;
				numeroMaxLinhasDetailPrimeiraPagina = NUMERO_MAX_lINHAS_DETAIL_PRIMEIRA_PAGINA_BOLETO;
				
			}
			
			Iterator iterator = colecaolinhasDescricaoServicosTarifasTotal.iterator();		
			while (iterator.hasNext()) {
			
				ContaLinhasDescricaoServicosTarifasTotalHelper linhasDescricaoServicosTarifasTotalHelper 
					= (ContaLinhasDescricaoServicosTarifasTotalHelper) iterator.next();
				
				Relatorio2ViaContaDetailBean relatorio2ViaContaDetailBean = 
					new Relatorio2ViaContaDetailBean(linhasDescricaoServicosTarifasTotalHelper);
				
				colecaoDetail.add(relatorio2ViaContaDetailBean);
				totalLinhasRelatorio = totalLinhasRelatorio + 1;
				
				if ((totalLinhasRelatorio== numeroMaxLinhasDetailPrimeiraPagina) || 
						(totalLinhasRelatorio- numeroMaxLinhasDetailPrimeiraPagina) % NUMERO_MAX_lINHAS_DETAIL_OUTRAS_PAGINAS == 0) {
					
						if (ehBoleto){
							
							if (indicadorPrimeiraPagina == 1){
								
								boleto = 1;
								
								Relatorio2ViaContaBoletoBancarioBean boletoBean = 
									new Relatorio2ViaContaBoletoBancarioBean(
										emitirContaHelper, indicadorPrimeiraPagina ,
										totalPaginasRelatorio, contaPaga, 
										colecaoDetail, debitoCreditoSituacaoAtualConta,cedente);	
								
								boletoBean.setPisBaseCalculo(baseCalculosFormatada);
								boletoBean.setPisPercentual(percentualPisFormatado);
								boletoBean.setPisValorImposto(valorPisFormatado);
								
								boletoBean.setCofinsBaseCalculo(baseCalculosFormatada);
								boletoBean.setCofinsPercentual(percentualCofinsFormatado);
								boletoBean.setCofinsValorImposto(valorCofinsFormatado);
								
								colecaoBoleto.add(boletoBean);
							}
							
						}
						
						Relatorio2ViaContaBean relatorio2ViaContaBean = 
									new Relatorio2ViaContaBean(
										emitirContaHelper, indicadorPrimeiraPagina,
										colecaoDetail, dataVencimentoFormatada,
										enderecoClienteResponsavel,	totalPaginasRelatorio,
										codigoRota,	debitoCreditoSituacaoAtualConta,
										contaPaga, enderecoClienteResponsavelLinha2,
										colecaoBoleto,boleto);
						
						relatorio2ViaContaBean.setEmpresa(empresa);
						relatorio2ViaContaBean.setEnderecoEmpresa(enderecoEmpresa);
						
						relatorio2ViaContaBean.setPisBaseCalculo(baseCalculosFormatada);
						relatorio2ViaContaBean.setPisPercentual(percentualPisFormatado);
						relatorio2ViaContaBean.setPisValorImposto(valorPisFormatado);
						
						relatorio2ViaContaBean.setCofinsBaseCalculo(baseCalculosFormatada);
						relatorio2ViaContaBean.setCofinsPercentual(percentualCofinsFormatado);
						relatorio2ViaContaBean.setCofinsValorImposto(valorCofinsFormatado);
						
						retorno.add(relatorio2ViaContaBean);
						colecaoDetail.clear();
						colecaoBoleto.clear();
				}
				
				if ((totalLinhasRelatorio- numeroMaxLinhasDetailPrimeiraPagina) % NUMERO_MAX_lINHAS_DETAIL_OUTRAS_PAGINAS == 0) {
					totalPaginasRelatorio = totalPaginasRelatorio + 1;
					indicadorPrimeiraPagina = totalPaginasRelatorio;
				}
				
			}	
			
			if  (totalLinhasRelatorio!= numeroMaxLinhasDetailPrimeiraPagina && 
					((totalLinhasRelatorio- numeroMaxLinhasDetailPrimeiraPagina) %
							NUMERO_MAX_lINHAS_DETAIL_OUTRAS_PAGINAS != 0)) {
	
				if (ehBoleto){
					
					if (indicadorPrimeiraPagina == 1){

						boleto = 1;
						
						Relatorio2ViaContaBoletoBancarioBean boletoBean = 
							new Relatorio2ViaContaBoletoBancarioBean(
								emitirContaHelper, indicadorPrimeiraPagina ,
								totalPaginasRelatorio, contaPaga, 
								colecaoDetail, debitoCreditoSituacaoAtualConta,cedente);
						
						
						boletoBean.setPisBaseCalculo(baseCalculosFormatada);
						boletoBean.setPisPercentual(percentualPisFormatado);
						boletoBean.setPisValorImposto(valorPisFormatado);
						
						boletoBean.setCofinsBaseCalculo(baseCalculosFormatada);
						boletoBean.setCofinsPercentual(percentualCofinsFormatado);
						boletoBean.setCofinsValorImposto(valorCofinsFormatado);
						
						colecaoBoleto.add(boletoBean);
						
					}
					
				}
				
				Relatorio2ViaContaBean relatorio2ViaContaBean = 
					new Relatorio2ViaContaBean(
						emitirContaHelper, indicadorPrimeiraPagina,
						colecaoDetail, dataVencimentoFormatada,
						enderecoClienteResponsavel,	totalPaginasRelatorio,
						codigoRota,	debitoCreditoSituacaoAtualConta,
						contaPaga, enderecoClienteResponsavelLinha2,
						colecaoBoleto,boleto);
				
				if(emitirContaHelper.getCodigoDebitoAutomatico()!=null &&
						!emitirContaHelper.getCodigoDebitoAutomatico().equals("")){
					
					relatorio2ViaContaBean.setCodigoDebitoAutomatico(emitirContaHelper.getCodigoDebitoAutomaticoFormatado());
						
				}
				relatorio2ViaContaBean.setEnderecoEmpresa(enderecoEmpresa);
				relatorio2ViaContaBean.setEmpresa(empresa);
				
				
				//Retirado pois estava deixando todas as segundas vias do 
				//conjunto de contas de imoveis com o mesmo nome e documento
//				if(nomeCliente != null){
//					relatorio2ViaContaBean.setNomeCliente(nomeCliente);
//					
//					if (documentoCliente != null) {
//						relatorio2ViaContaBean.setNumeroCpfCnpj(documentoCliente);
//					} else {
//						relatorio2ViaContaBean.setNumeroCpfCnpj("");
//					}
//				}
				
				relatorio2ViaContaBean.setPisBaseCalculo(baseCalculosFormatada);
				relatorio2ViaContaBean.setPisPercentual(percentualPisFormatado);
				relatorio2ViaContaBean.setPisValorImposto(valorPisFormatado);				
				relatorio2ViaContaBean.setCofinsBaseCalculo(baseCalculosFormatada);
				relatorio2ViaContaBean.setCofinsPercentual(percentualCofinsFormatado);
				relatorio2ViaContaBean.setCofinsValorImposto(valorCofinsFormatado);
				
				retorno.add(relatorio2ViaContaBean);
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

		Collection idsConta = (Collection) getParametro("idsConta");
		boolean cobrarTaxaEmissaoConta = (Boolean) getParametro("cobrarTaxaEmissaoConta");
		Short contaSemCodigoBarras = (Short) getParametro("contaSemCodigoBarras");
		
		Integer idCliente = (Integer) getParametro("idCliente");
		
		Integer idContaHistorico = (Integer)getParametro("idContaHistorico");
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		Collection colecaoEmitirContaHelper = new ArrayList();
		if (idContaHistorico == null) {
			colecaoEmitirContaHelper = fachada.emitir2ViaContas(idsConta, cobrarTaxaEmissaoConta, contaSemCodigoBarras);
		} else {
			colecaoEmitirContaHelper = fachada.emitir2ViaContasHistorico(idsConta, cobrarTaxaEmissaoConta, contaSemCodigoBarras);
		}		

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		String nomeEmpresa = (String) getParametro("nomeEmpresa");
		
		String cnpjEmpresa = "";
		if (sistemaParametro.getCnpjEmpresa() != null) {
			cnpjEmpresa = Util.formatarCnpj(sistemaParametro.getCnpjEmpresa());
		}
		
		String idUsuario = "";
		
		Usuario usuario = this.getUsuario();
		
		String nomeUsuario = "";
		
		//**********************************************************************
		// Alterado por: Ivan Sergio
		// Data: 30/04/2009
		// CRC1656
		//**********************************************************************
		idUsuario = "INTERNET";
		nomeUsuario = "INTERNET";
		if (usuario != null) {
			if (sistemaParametro.getIndicadorImprimeUsuarioSegundaVia().equals(ConstantesSistema.SIM)) {
				idUsuario = usuario.getId().toString();
				nomeUsuario = usuario.getNomeUsuario();
			}
		} 			
		
		// valor de retorno
		byte[] retorno = null;

		// Par�metros do relat�rio
		Map<String, String> parametros = new HashMap();		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("imagemConta", sistemaParametro.getImagemConta());
		parametros.put("nomeEmpresa",nomeEmpresa);
		parametros.put("cnpjEmpresa", cnpjEmpresa);
		parametros.put("idUsuario", idUsuario);
		parametros.put("indicadorExibirMsgNaoReceberPagamento", sistemaParametro.getIndicadorExibeMensagemNaoReceberPagamento().toString());
		parametros.put("nomeUsuario", nomeUsuario);
		
		String empresa = "\n	  	"+nomeEmpresa +" - "+cnpjEmpresa;
		
		Collection dadosRelatorio = colecaoEmitirContaHelper;

		Collection<Relatorio2ViaContaBean> colecaoBean = 
			this.inicializarBeanRelatorio(dadosRelatorio, sistemaParametro, empresa, fachada, idCliente);

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// N�o existem dados para a exibi��o do relat�rio.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		RelatorioDataSource ds = new RelatorioDataSource((java.util.List) colecaoBean);
		
		if(sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_JUAZEIRO)){
			retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_2_VIA_CONTA_SAAE, parametros, ds,
				tipoFormatoRelatorio);
		}else{

			retorno = this.gerarRelatorio(
					ConstantesRelatorios.RELATORIO_2_VIA_CONTA, parametros, ds,
					tipoFormatoRelatorio);
		}
		
		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.SEGUNDA_VIA_CONTA,
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
		Integer qtdeContas = (Integer) getParametro("qtdeContas");
		
		return qtdeContas;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("Relatorio2ViaConta", this);
	}
	
}