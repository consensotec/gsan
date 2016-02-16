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
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.EmitirContaHelper;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * [UC0482] Emitir 2� Via de Conta
 * 
 * @author Vivianne Sousa
 * @date 03/03/2007
 */

public class Relatorio2ViaContaTipo2 extends TarefaRelatorio {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7034984685957706140L;

	/**
	 * Quantidade m�xima de linhas do detail da primeira p�gina do rel�torio
	 */
	public final static int NUMERO_MAX_lINHAS_DETAIL_PRIMEIRA_PAGINA = 15;
	
	/**
	 * Quantidade m�xima de linhas do detail a partir da segunda p�gina do rel�torio
	 */
	public final static int NUMERO_MAX_lINHAS_DETAIL_OUTRAS_PAGINAS = 43;
		
	public Relatorio2ViaContaTipo2(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_2_VIA_CONTA_TIPO_2);
	}
	
	@Deprecated
	public Relatorio2ViaContaTipo2() {
		super(null, "");
	}

	
	private Collection<Relatorio2ViaContaTipo2Bean> inicializarBeanRelatorio(
			Collection colecaoEmitirContaHelper) {
		
		Collection<Relatorio2ViaContaTipo2Bean> retorno = new ArrayList<Relatorio2ViaContaTipo2Bean>();
		
		Iterator iter = colecaoEmitirContaHelper.iterator();
		while (iter.hasNext()) {
			
			EmitirContaHelper emitirContaHelper = (EmitirContaHelper) iter.next();
			
			String codigoRota = null;
			
			if(emitirContaHelper.getRotaEntrega()!= null &&
					!emitirContaHelper.getRotaEntrega().equals("")){
				codigoRota = emitirContaHelper.getRotaEntrega();
			}
						
			Collection colecaolinhasDescricaoServicosTarifasTotal = emitirContaHelper.getColecaoContaLinhasDescricaoServicosTarifasTotalHelper();

			Collection<Relatorio2ViaContaDetailBean> colecaoDetail = new ArrayList<Relatorio2ViaContaDetailBean>();

			int totalLinhasRelatorio = 0;
			int totalPaginasRelatorio = 1;
			String indicadorPrimeiraPagina = "1";

			String dataVencimentoFormatada = Util.formatarData(emitirContaHelper.getDataVencimentoConta());
			
			String[] arrayDadosConsumoAnterior1 = emitirContaHelper.getDadosConsumoMes1().toString().split("-");
			String[] arrayDadosConsumoAnterior2 = emitirContaHelper.getDadosConsumoMes2().toString().split("-");
			String[] arrayDadosConsumoAnterior3 = emitirContaHelper.getDadosConsumoMes3().toString().split("-");
			String[] arrayDadosConsumoAnterior4 = emitirContaHelper.getDadosConsumoMes4().toString().split("-");
			String[] arrayDadosConsumoAnterior5 = emitirContaHelper.getDadosConsumoMes5().toString().split("-");
			String[] arrayDadosConsumoAnterior6 = emitirContaHelper.getDadosConsumoMes6().toString().split("-");
			
			
			String mesAno1 = arrayDadosConsumoAnterior1[0];
			String consumo1 = arrayDadosConsumoAnterior1[1];

			String mesAno2 = arrayDadosConsumoAnterior2[0];
			String consumo2 = arrayDadosConsumoAnterior2[1];
			
			String mesAno3 = arrayDadosConsumoAnterior3[0];
			String consumo3 = arrayDadosConsumoAnterior3[1];
			
			String mesAno4 = arrayDadosConsumoAnterior4[0];
			String consumo4 = arrayDadosConsumoAnterior4[1];
			
			String mesAno5 = arrayDadosConsumoAnterior5[0];
			String consumo5 = arrayDadosConsumoAnterior5[1];
			
			String mesAno6 = arrayDadosConsumoAnterior6[0];
			String consumo6 = arrayDadosConsumoAnterior6[1];
			
			Iterator iterator = colecaolinhasDescricaoServicosTarifasTotal.iterator();		
			while (iterator.hasNext()) {
				
				ContaLinhasDescricaoServicosTarifasTotalHelper linhasDescricaoServicosTarifasTotalHelper 
					= (ContaLinhasDescricaoServicosTarifasTotalHelper) iterator.next();
				
				Relatorio2ViaContaDetailBean relatorio2ViaContaDetailBean = 
					new Relatorio2ViaContaDetailBean(
						linhasDescricaoServicosTarifasTotalHelper.getDescricaoServicosTarifas(), 
						linhasDescricaoServicosTarifasTotalHelper.getConsumoFaixa(),
						linhasDescricaoServicosTarifasTotalHelper.getValor());
			
				colecaoDetail.add(relatorio2ViaContaDetailBean);
				
				
				if ((totalLinhasRelatorio== NUMERO_MAX_lINHAS_DETAIL_PRIMEIRA_PAGINA) || 
					(totalLinhasRelatorio- NUMERO_MAX_lINHAS_DETAIL_PRIMEIRA_PAGINA) % NUMERO_MAX_lINHAS_DETAIL_OUTRAS_PAGINAS == 0) {
					Relatorio2ViaContaTipo2Bean relatorio2ViaContaTipo2Bean = new Relatorio2ViaContaTipo2Bean(
							indicadorPrimeiraPagina, colecaoDetail,
							emitirContaHelper.getDescricaoLocalidade(),
							emitirContaHelper.getMatriculaImovelFormatada(),
							dataVencimentoFormatada,
							emitirContaHelper.getNomeCliente(),
							emitirContaHelper.getEnderecoImovel(),
							Util.formatarAnoMesParaMesAno(emitirContaHelper.getAmReferencia()),
							emitirContaHelper.getInscricaoImovel(),
							emitirContaHelper.getIdClienteResponsavel(),
							emitirContaHelper.getEnderecoClienteResponsavel(),
							emitirContaHelper.getDescricaoLigacaoAguaSituacao(),
							emitirContaHelper.getDescricaoLigacaoEsgotoSituacao(),
							emitirContaHelper.getLeituraAnterior(),
							emitirContaHelper.getLeituraAtual(),
							emitirContaHelper.getConsumoFaturamento(),
							emitirContaHelper.getDiasConsumo(),
							emitirContaHelper.getConsumoMedioDiario(),
							emitirContaHelper.getDataLeituraAnterior(),
							emitirContaHelper.getDataLeituraAtual(),
							emitirContaHelper.getDescricaoTipoConsumo(),
							emitirContaHelper.getDescricaoAnormalidadeConsumo(),
							emitirContaHelper.getQuantidadeEconomiaConta() != null? emitirContaHelper.getQuantidadeEconomiaConta(): "",
							emitirContaHelper.getConsumoEconomia() != null ? emitirContaHelper.getConsumoEconomia(): "",
							emitirContaHelper.getCodigoAuxiliarString(),
							emitirContaHelper.getMensagemConsumoString(),
							emitirContaHelper.getValorContaString(),
//							emitirContaHelper.getPrimeiraParte(),
//							emitirContaHelper.getSegundaParte(),
//							emitirContaHelper.getTerceiraParte(),
							emitirContaHelper.getNomeGerenciaRegional(),
							emitirContaHelper.getMesAnoFormatado(),
							emitirContaHelper.getNumeroIndiceTurbidez(),
							emitirContaHelper.getNumeroCloroResidual(),
							emitirContaHelper.getRepresentacaoNumericaCodBarraFormatada(),
							emitirContaHelper.getRepresentacaoNumericaCodBarraSemDigito(),
							emitirContaHelper.getDataValidade(),
							emitirContaHelper.getIdFaturamentoGrupo().toString(),
							emitirContaHelper.getIdEmpresa().toString(),
							"" + totalPaginasRelatorio,
							emitirContaHelper.getIdConta().toString(),codigoRota,
							emitirContaHelper.getDatasVencimento(),
							emitirContaHelper.getConsumoMedio(),
							mesAno1, mesAno2, mesAno3, mesAno4, mesAno5, mesAno6,
							consumo1, consumo2, consumo3, consumo4,consumo5,consumo6,
							emitirContaHelper.getCategoriaImovel(),emitirContaHelper.getNumeroHidrometro(),
							emitirContaHelper.getContaSemCodigoBarras(),
							emitirContaHelper.getMsgConta(),  
							emitirContaHelper.getMsgLinha1Conta(),  
							emitirContaHelper.getMsgLinha2Conta(),  
							emitirContaHelper.getMsgLinha3Conta(),  
							emitirContaHelper.getMsgLinha4Conta(),  
							emitirContaHelper.getMsgLinha5Conta(),  
							emitirContaHelper.getValorMedioTurbidez(),  
							emitirContaHelper.getPadraoTurbidez(),  
							emitirContaHelper.getValorMedioPh(),  
							emitirContaHelper.getValorMedioCor(),  
							emitirContaHelper.getValorMedioCloro(),  
							emitirContaHelper.getValorMedioFluor(),  
							emitirContaHelper.getValorMedioFerro(),  
							emitirContaHelper.getValorMedioColiformesTotais(),  
							emitirContaHelper.getValorMedioColiformesfecais(),  
							emitirContaHelper.getPadraoPh(),  
							emitirContaHelper.getPadraoCor(),  
							emitirContaHelper.getPadraoCloro(),  
							emitirContaHelper.getPadraoFluor(),  
							emitirContaHelper.getPadraoFerro(),  
							emitirContaHelper.getPadraoColiformesTotais(),  
							emitirContaHelper.getPadraoColiformesfecais(),  
							emitirContaHelper.getEnderecoLinha2(),  
							emitirContaHelper.getEnderecoLinha3(),
							emitirContaHelper.getIdFaturamentoGrupo().toString(),
							emitirContaHelper.getIdImovel().toString(),
							""+emitirContaHelper.getAmReferencia(),
							emitirContaHelper.getQuantidadeCloroExigidas(),
							emitirContaHelper.getQuantidadeCloroAnalisadas(),
							emitirContaHelper.getQuantidadeCloroConforme(),
							emitirContaHelper.getQuantidadeTurbidezExigidas(),
							emitirContaHelper.getQuantidadeTurbidezAnalisadas(),
							emitirContaHelper.getQuantidadeTurbidezConforme(),
							emitirContaHelper.getQuantidadeCorExigidas(),
							emitirContaHelper.getQuantidadeCorAnalisadas(),
							emitirContaHelper.getQuantidadeCorConforme(),
							emitirContaHelper.getQuantidadeColiformesTotaisExigidas(),
							emitirContaHelper.getQuantidadeColiformesTotaisAnalisadas(),
							emitirContaHelper.getQuantidadeColiformesTotaisConforme(),
							emitirContaHelper.getQuantidadeColiformesTermotolerantesExigidas(),
							emitirContaHelper.getQuantidadeColiformesTermotolerantesAnalisadas(),
							emitirContaHelper.getQuantidadeColiformesTermotolerantesConforme()
							);
					retorno.add(relatorio2ViaContaTipo2Bean);
					colecaoDetail.clear();
				}
				
				if ((totalLinhasRelatorio- NUMERO_MAX_lINHAS_DETAIL_PRIMEIRA_PAGINA) % NUMERO_MAX_lINHAS_DETAIL_OUTRAS_PAGINAS == 0) {
					totalPaginasRelatorio = totalPaginasRelatorio + 1;
					indicadorPrimeiraPagina = "" + (totalPaginasRelatorio);
				}
				
			}	
			
			if  (totalLinhasRelatorio!= NUMERO_MAX_lINHAS_DETAIL_PRIMEIRA_PAGINA && 
					((totalLinhasRelatorio- NUMERO_MAX_lINHAS_DETAIL_PRIMEIRA_PAGINA) %
							NUMERO_MAX_lINHAS_DETAIL_OUTRAS_PAGINAS != 0)) {
	
				Relatorio2ViaContaTipo2Bean relatorio2ViaContaTipo2Bean = new Relatorio2ViaContaTipo2Bean(
						indicadorPrimeiraPagina, 
						colecaoDetail,
						emitirContaHelper.getDescricaoLocalidade(),
						emitirContaHelper.getMatriculaImovelFormatada(),
						dataVencimentoFormatada,
						emitirContaHelper.getNomeCliente(),
						emitirContaHelper.getEnderecoImovel(),
						Util.formatarAnoMesParaMesAno(emitirContaHelper.getAmReferencia()),
						emitirContaHelper.getInscricaoImovel(),
						emitirContaHelper.getIdClienteResponsavel(),
						emitirContaHelper.getEnderecoClienteResponsavel(),
						emitirContaHelper.getDescricaoLigacaoAguaSituacao(),
						emitirContaHelper.getDescricaoLigacaoEsgotoSituacao(),
						emitirContaHelper.getLeituraAnterior(),
						emitirContaHelper.getLeituraAtual(),
						emitirContaHelper.getConsumoFaturamento(),
						emitirContaHelper.getDiasConsumo(),
						emitirContaHelper.getConsumoMedioDiario(),
						emitirContaHelper.getDataLeituraAnterior(),
						emitirContaHelper.getDataLeituraAtual(),
						emitirContaHelper.getDescricaoTipoConsumo(),
						emitirContaHelper.getDescricaoAnormalidadeConsumo(),
						emitirContaHelper.getQuantidadeEconomiaConta() != null ? emitirContaHelper.getQuantidadeEconomiaConta(): "",
						emitirContaHelper.getConsumoEconomia() != null ? emitirContaHelper.getConsumoEconomia(): "",
						emitirContaHelper.getCodigoAuxiliarString(),
						emitirContaHelper.getMensagemConsumoString(),
						emitirContaHelper.getValorContaString(),
//						emitirContaHelper.getPrimeiraParte(),emitirContaHelper.getSegundaParte(),emitirContaHelper.getTerceiraParte(),
						emitirContaHelper.getNomeGerenciaRegional(),
						emitirContaHelper.getMesAnoFormatado(),
						emitirContaHelper.getNumeroIndiceTurbidez(),
						emitirContaHelper.getNumeroCloroResidual(),
						emitirContaHelper.getRepresentacaoNumericaCodBarraFormatada(),
						emitirContaHelper.getRepresentacaoNumericaCodBarraSemDigito(),
						emitirContaHelper.getDataValidade(),
						emitirContaHelper.getIdFaturamentoGrupo().toString(),
						emitirContaHelper.getIdEmpresa().toString(),
						"" + totalPaginasRelatorio,
						emitirContaHelper.getIdConta().toString(),
						codigoRota,
						emitirContaHelper.getDatasVencimento(),
						emitirContaHelper.getConsumoMedio(),
						mesAno1, 
						mesAno2, 
						mesAno3, 
						mesAno4, 
						mesAno5, 
						mesAno6,
						consumo1, 
						consumo2, 
						consumo3, 
						consumo4,
						consumo5,
						consumo6,
						emitirContaHelper.getCategoriaImovel(),
						emitirContaHelper.getNumeroHidrometro(),
						emitirContaHelper.getContaSemCodigoBarras(),
						emitirContaHelper.getMsgConta(),  
						emitirContaHelper.getMsgLinha1Conta(),  
						emitirContaHelper.getMsgLinha2Conta(),  
						emitirContaHelper.getMsgLinha3Conta(),  
						emitirContaHelper.getMsgLinha4Conta(),  
						emitirContaHelper.getMsgLinha5Conta(),  
						emitirContaHelper.getValorMedioTurbidez(),  
						emitirContaHelper.getPadraoTurbidez(),  
						emitirContaHelper.getValorMedioPh(),  
						emitirContaHelper.getValorMedioCor(),  
						emitirContaHelper.getValorMedioCloro(),  
						emitirContaHelper.getValorMedioFluor(),  
						emitirContaHelper.getValorMedioFerro(),  
						emitirContaHelper.getValorMedioColiformesTotais(),  
						emitirContaHelper.getValorMedioColiformesfecais(),  
						emitirContaHelper.getPadraoPh(),  
						emitirContaHelper.getPadraoCor(),  
						emitirContaHelper.getPadraoCloro(),  
						emitirContaHelper.getPadraoFluor(),  
						emitirContaHelper.getPadraoFerro(),  
						emitirContaHelper.getPadraoColiformesTotais(),  
						emitirContaHelper.getPadraoColiformesfecais(),  
						emitirContaHelper.getEnderecoLinha2(),  
						emitirContaHelper.getEnderecoLinha3(),
						emitirContaHelper.getIdFaturamentoGrupo().toString(),
						emitirContaHelper.getIdImovel().toString(),
						""+emitirContaHelper.getAmReferencia(),
						emitirContaHelper.getQuantidadeCloroExigidas(),
						emitirContaHelper.getQuantidadeCloroAnalisadas(),
						emitirContaHelper.getQuantidadeCloroConforme(),
						emitirContaHelper.getQuantidadeTurbidezExigidas(),
						emitirContaHelper.getQuantidadeTurbidezAnalisadas(),
						emitirContaHelper.getQuantidadeTurbidezConforme(),
						emitirContaHelper.getQuantidadeCorExigidas(),
						emitirContaHelper.getQuantidadeCorAnalisadas(),
						emitirContaHelper.getQuantidadeCorConforme(),
						emitirContaHelper.getQuantidadeColiformesTotaisExigidas(),
						emitirContaHelper.getQuantidadeColiformesTotaisAnalisadas(),
						emitirContaHelper.getQuantidadeColiformesTotaisConforme(),
						emitirContaHelper.getQuantidadeColiformesTermotolerantesExigidas(),
						emitirContaHelper.getQuantidadeColiformesTermotolerantesAnalisadas(),
						emitirContaHelper.getQuantidadeColiformesTermotolerantesConforme()
						);
				
				retorno.add(relatorio2ViaContaTipo2Bean);

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

		//Collection colecaoEmitirContaHelper = (Collection) getParametro("colecaoEmitirContaHelper");

		Collection idsConta = (Collection) getParametro("idsConta");
		boolean cobrarTaxaEmissaoConta = (Boolean) getParametro("cobrarTaxaEmissaoConta");
		Short contaSemCodigoBarrasPesquisa = (Short) getParametro("contaSemCodigoBarrasPesquisa");
		Integer idContaHistorico = (Integer)getParametro("idContaHistorico");
		
//		Collection colecaoEmitirContaHelper = fachada.emitir2ViaContas(idsConta, cobrarTaxaEmissaoConta, contaSemCodigoBarrasPesquisa);
		
		Collection colecaoEmitirContaHelper = new ArrayList();
		if (idContaHistorico == null){
			colecaoEmitirContaHelper = fachada.emitir2ViaContas(idsConta, cobrarTaxaEmissaoConta, contaSemCodigoBarrasPesquisa);
		}else{
			colecaoEmitirContaHelper = fachada.emitir2ViaContasHistorico(idsConta, cobrarTaxaEmissaoConta, contaSemCodigoBarrasPesquisa);
		}
		
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		String nomeEmpresa = (String) getParametro("nomeEmpresa");
		
		//String contaSemCodigoBarras = (String)getParametro("contaSemCodigoBarras");

		// valor de retorno
		byte[] retorno = null;

		// Par�metros do relat�rio
		Map<String, String> parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("imagemConta", sistemaParametro.getImagemConta());
		parametros.put("nomeEmpresa",nomeEmpresa);
		
		parametros.put("cnpjEmpresa",sistemaParametro.getCnpjEmpresa().toString());

		Collection dadosRelatorio = colecaoEmitirContaHelper;

		Collection<Relatorio2ViaContaTipo2Bean> colecaoBean = this
				.inicializarBeanRelatorio(dadosRelatorio);

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// N�o existem dados para a exibi��o do relat�rio.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		RelatorioDataSource ds = new RelatorioDataSource((java.util.List) colecaoBean);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_2_VIA_CONTA_TIPO_2, parametros, ds,
				tipoFormatoRelatorio);
		
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
