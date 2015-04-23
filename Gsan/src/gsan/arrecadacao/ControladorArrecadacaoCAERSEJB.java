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
package gsan.arrecadacao;

import gsan.arrecadacao.banco.Banco;
import gsan.arrecadacao.bean.ArrecadadorMovimentoItemHelper;
import gsan.arrecadacao.bean.DadosConteudoCodigoBarrasHelper;
import gsan.arrecadacao.bean.PagamentoHelperCodigoBarras;
import gsan.arrecadacao.bean.RegistroHelperCodigoA;
import gsan.arrecadacao.bean.RegistroHelperCodigoB;
import gsan.arrecadacao.bean.RegistroHelperCodigoBarras;
import gsan.arrecadacao.bean.RegistroHelperCodigoBarrasTipoPagamento;
import gsan.arrecadacao.bean.RegistroHelperCodigoC;
import gsan.arrecadacao.bean.RegistroHelperCodigoE;
import gsan.arrecadacao.bean.RegistroHelperCodigoF;
import gsan.arrecadacao.bean.RegistroHelperCodigoG;
import gsan.arrecadacao.bean.RegistroHelperCodigoX;
import gsan.arrecadacao.bean.RegistroHelperCodigoZ;
import gsan.arrecadacao.debitoautomatico.DebitoAutomaticoMovimento;
import gsan.arrecadacao.pagamento.GuiaPagamento;
import gsan.arrecadacao.pagamento.Pagamento;
import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.imovel.FiltroImovel;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cobranca.CobrancaDebitoSituacao;
import gsan.cobranca.CobrancaDocumento;
import gsan.cobranca.CobrancaDocumentoItem;
import gsan.cobranca.DocumentoTipo;
import gsan.faturamento.GuiaPagamentoGeral;
import gsan.faturamento.conta.Conta;
import gsan.faturamento.conta.ContaGeral;
import gsan.faturamento.conta.Fatura;
import gsan.faturamento.conta.FiltroFatura;
import gsan.faturamento.credito.CreditoARealizar;
import gsan.faturamento.credito.CreditoARealizarGeral;
import gsan.faturamento.debito.DebitoACobrar;
import gsan.faturamento.debito.DebitoACobrarGeral;
import gsan.faturamento.debito.DebitoTipo;
import gsan.faturamento.debito.FiltroDebitoTipo;
import gsan.relatorio.arrecadacao.pagamento.GuiaPagamentoRelatorioHelper;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesSistema;
import gsan.util.ControladorException;
import gsan.util.ErroRepositorioException;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.SessionBean;

/**
 * Controlador Faturamento CAER
 * 
 * @author Raphael Rossiter
 * @date 30/04/2007
 */
public class ControladorArrecadacaoCAERSEJB extends ControladorArrecadacao
		implements SessionBean {

	private static final long serialVersionUID = 1L;

	// ==============================================================================================================
	// MÉTODOS EXCLUSIVOS DA CAER
	// ==============================================================================================================

	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 * 
	 * Autor: Sávio Luiz, Rafael Pinto, Raphael Rossiter Data: 01/02/2006, ,
	 * 30/04/2007
	 */
	public PagamentoHelperCodigoBarras processarPagamentosCodigoBarrasPorTipoPagamento(
			RegistroHelperCodigoBarras registroHelperCodigoBarras, Date dataPagamento, Integer anoMesPagamento,
			BigDecimal valorPagamento, Integer idFormaArrecadacao, SistemaParametro sistemaParametro, Usuario usuarioLogado) 
			throws ControladorException {

		PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = new PagamentoHelperCodigoBarras();
		
		RegistroHelperCodigoBarrasTipoPagamento codigoBarrasTipoPagamento = registroHelperCodigoBarras
		.getRegistroHelperCodigoBarrasTipoPagamento();
		
		//TIPO PAGAMENTO ATUAL
		String tipoPagamento = registroHelperCodigoBarras.getTipoPagamento();
		
		//TIPO PAGAMENTO LEGADO
		String tipoPagamentoLEGADO = codigoBarrasTipoPagamento.getIdPagamento1();
		
		//PARA IDENTIFICAR LEGADO
		boolean eHLegado = false;
		
		
		// ** Caso o tipo do documento (igual 01) - Conta e Segunda Via
		if ((tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA.toString()) &&
			codigoBarrasTipoPagamento.getIdPagamento4().equals("00") &&
			codigoBarrasTipoPagamento.getIdPagamento5().equals("00000000") &&
			tipoPagamentoLEGADO != null && tipoPagamentoLEGADO.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA_CAER))
			||
			(tipoPagamentoLEGADO != null && tipoPagamentoLEGADO.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA_CAER) &&
			!Util.validarAnoMesSemBarra(codigoBarrasTipoPagamento.getIdPagamento3()) &&
			(Integer.valueOf(codigoBarrasTipoPagamento.getIdPagamento3().substring(0, 4).trim())).intValue() == 2007)){

			String descricaoOcorrencia = "OK";
			String indicadorAceitacaoRegistro = "1";
			Collection colecaoPagamentos = new ArrayList();
			
			boolean matriculaImovelInvalida = false;
			Integer idImovelNaBase = null;
			Integer matriculaImovel = null;
			boolean anoMesReferencia = false;
			int anoMes = 0;
			
			matriculaImovelInvalida = Util.validarValorNaoNumerico(codigoBarrasTipoPagamento.getIdPagamento2());

			if (matriculaImovelInvalida) {
				descricaoOcorrencia = "MÁTRICULA DO IMÓVEL INVÁLIDA";
			} 
			else {

				// Verifica se existe a matricula do imóvel na base
				matriculaImovel = new Integer(codigoBarrasTipoPagamento.getIdPagamento2());

				// [FS0008] - Calcular Digito Verificador da Matricula
				if (matriculaImovel != null) {
					
					int digitoModulo11 = Util.obterDigitoVerificadorModulo11("" + matriculaImovel);

					matriculaImovel = new Integer(matriculaImovel.toString() + digitoModulo11);
				}

				idImovelNaBase = null;

				try {

					idImovelNaBase = repositorioImovel.recuperarMatriculaImovel(matriculaImovel);

				} catch (ErroRepositorioException e) {
					e.printStackTrace();
					throw new ControladorException("erro.sistema", e);
				}

				// Se o id do imovel pesquisado na base for diferente de nulo
				if (idImovelNaBase == null) {
					descricaoOcorrencia = "MATRÍCULA DO IMÓVEL NÃO CADASTRADA";
				}
			}

			// Valida mes/ano referencia
			anoMesReferencia = Util.validarValorNaoNumerico(codigoBarrasTipoPagamento.getIdPagamento3());

			if (!anoMesReferencia) {
				anoMes = Integer.parseInt(codigoBarrasTipoPagamento.getIdPagamento3());
			} else {
				descricaoOcorrencia = "ANO/MÊS DE REFERÊNCIA DA CONTA INVÁLIDA";
			}

			if (descricaoOcorrencia.equals("OK")) {

				Integer idLocalidade = null;

				Integer idConta = null;

				Imovel imovel = new Imovel();
				imovel.setId(idImovelNaBase);

				try {

					idConta = repositorioFaturamento.pesquisarExistenciaContaComSituacaoAtual(imovel, anoMes);

				} catch (ErroRepositorioException e) {
					e.printStackTrace();
					throw new ControladorException("erro.sistema", e);
				}
				
				/*
                 * Alterado por Raphael Rossiter em 10/01/2008 - Analistas: Eduardo e Aryed
                 * OBJ: Gerar os pagamentos associados com a localidade da conta e NÃO com
                 * a localidade do imóvel.
                 */
                if (idConta != null) {
                	
                	try {
                        idLocalidade = repositorioLocalidade
                        .pesquisarIdLocalidadePorConta(idConta);

                    } catch (ErroRepositorioException e) {
                        throw new ControladorException("erro.sistema", e);
                    }
                }
                else{
                	
                	try {
                        idLocalidade = repositorioLocalidade
                        .pesquisarIdLocalidade(idImovelNaBase);

                    } catch (ErroRepositorioException e) {
                        throw new ControladorException("erro.sistema", e);
                    }
                }

				/*
				 * if (idConta == null || idConta.equals("")) {
				 * descricaoOcorrencia = "CONTA INEXISTENTE"; }
				 */

				Pagamento pagamento = this
						.processarPagamentosCodigoBarrasTipoPagamentoContaLEGADO(
								anoMes, anoMesPagamento, sistemaParametro,
								valorPagamento, dataPagamento, idConta,
								idLocalidade, idFormaArrecadacao,
								idImovelNaBase, imovel);

				colecaoPagamentos.add(pagamento);
				
			} 
			else {

				// Atribui o valor 2(NÃO) ao indicador aceitacao registro
				indicadorAceitacaoRegistro = "2";
			}
			
			//Seta os parametros que serão retornados
			pagamentoHelperCodigoBarras.setColecaoPagamentos(colecaoPagamentos);
			pagamentoHelperCodigoBarras.setDescricaoOcorrencia(descricaoOcorrencia);
			pagamentoHelperCodigoBarras.setIndicadorAceitacaoRegistro(indicadorAceitacaoRegistro);

			eHLegado = true;
		}
		// ** Caso o tipo do documento - Documento de Cobranca
		else if (tipoPagamentoLEGADO != null &&
				tipoPagamentoLEGADO.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_DOC_COBRANCA_IMOVEL_CAER) &&
				tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_DOCUMENTO_COBRANCA.toString())){
			/*********************************************************
			* CRC5063
			* autor: Ivan Sergio
			* analista: Rosana
			* data: 01/10/2010
			*********************************************************/
			pagamentoHelperCodigoBarras = this.processarPagamentosCodigoBarrasDocumentoCobrancaImovelLEGADO(
					registroHelperCodigoBarras, sistemaParametro,
					dataPagamento, anoMesPagamento,
					valorPagamento, idFormaArrecadacao,
					usuarioLogado);
			/********************************************************/
			
			eHLegado = true;
		}
		/*
		 * GUIA PAGAMENTO - CLIENTE RESPONSÁVEL
		 */
		else if (tipoPagamentoLEGADO != null &&
				tipoPagamentoLEGADO.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_CLIENTE_CAER) &&
				tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_CLIENTE.toString())){

			pagamentoHelperCodigoBarras = this
					.processarPagamentosCodigoBarrasGuiaPagamentoClienteLEGADO(
							registroHelperCodigoBarras, sistemaParametro,
							dataPagamento, anoMesPagamento, valorPagamento,
							idFormaArrecadacao);
			
			eHLegado = true;
		}

		/*
		 * FATURA DE CLIENTE RESPONSÁVEL
		 */
		else if (tipoPagamentoLEGADO != null &&
				tipoPagamentoLEGADO.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_FATURA_CLIENTE_RESPONSAVEL_CAER) &&
				tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_FATURA_CLIENTE_RESPONSAVEL.toString())){

			pagamentoHelperCodigoBarras = this
					.processarPagamentosCodigoBarrasClienteResponsavel(
							registroHelperCodigoBarras, sistemaParametro,
							dataPagamento, anoMesPagamento, valorPagamento,
							idFormaArrecadacao);
			
			eHLegado = true;
		}

		/*
		 * DOCUMENTO DE COBRANÇA (EXTRATO DE CLIENTE RESPONSÁVEL)
		 */
		else if (tipoPagamentoLEGADO != null &&
				tipoPagamentoLEGADO.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_DOC_COBRANCA_CLIENTE_RESPONSAVEL_CAER) &&
				tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_DOCUMENTO_COBRANCA_NOVO.toString())){

			pagamentoHelperCodigoBarras = this
					.processarPagamentosCodigoBarrasDocumentoCobrancaClienteLEGADO(
							registroHelperCodigoBarras, sistemaParametro,
							dataPagamento, anoMesPagamento, valorPagamento,
							idFormaArrecadacao, usuarioLogado);
			
			eHLegado = true;
		}

		/*
		 * GUIA PAGAMENTO - IMÓVEL
		 */
		else if (tipoPagamentoLEGADO != null &&
				tipoPagamentoLEGADO.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_IMOVEL_CAER) &&
				tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO.toString())){

			pagamentoHelperCodigoBarras = this
					.processarPagamentosCodigoBarrasGuiaPagamentoLEGADO(
							registroHelperCodigoBarras, sistemaParametro,
							dataPagamento, anoMesPagamento, valorPagamento,
							idFormaArrecadacao);
			
			eHLegado = true;
		}

		
		if (!eHLegado){
			
			//[UC0259] - Processar Pagamento com Código de Barras
			pagamentoHelperCodigoBarras = super.processarPagamentosCodigoBarrasPorTipoPagamento(
				registroHelperCodigoBarras, dataPagamento, anoMesPagamento, valorPagamento, idFormaArrecadacao, 
				sistemaParametro, usuarioLogado);
			
		}
		
		
		return pagamentoHelperCodigoBarras;
	}

	/**
	 * retorna o objeto distribuido de acordo comj o tipo de pagamento
	 * 
	 * [UC0264] - Distribuir Dados do Código de Barras
	 * 
	 * [SF0001] - Distribuir Pagamento de Conta [SF0002] - Distribuir Pagamento
	 * de Guia de Pagamento [SF0003] - Distribuir Pagamento de Documento de
	 * Cobramça [SF0004] - Distribuir Pagamento de Fatura do Cliente Responsável
	 * 
	 * Autor: Sávio Luiz, Raphael Rossiter Data: 15/02/2006, 03/05/2007
	 */

	public RegistroHelperCodigoBarrasTipoPagamento distribuirDadosCodigoBarrasPorTipoPagamento(
			String idPagamento, String tipoPagamento, String idEmpresa) throws ControladorException {

		RegistroHelperCodigoBarrasTipoPagamento registroHelperCodigoBarrasTipoPagamento = new RegistroHelperCodigoBarrasTipoPagamento();

		//PARA IDENTIFICAR LEGADO
		boolean eHLegado = false;
		
		/*
		 * Se o tipo do pagamento for igual a 3 (Conta) e os 10 últimos dígitos forem iguais a zero; tratar como legado, 
		 * caso contrário; tratar como documento do gsan.
		 */
		if ((tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA.toString()) &&
			idPagamento.substring(14, 24).trim().equals("0000000000"))
			||
			(idPagamento.substring(0, 2).trim().equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA_CAER) &&
			 !Util.validarAnoMesSemBarra(idPagamento.substring(8, 14).trim()) && 
			 (Integer.valueOf(idPagamento.substring(8, 12).trim())).intValue() == 2007)){
			
			//TIPO DO PAGAMENTO
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento1(idPagamento.substring(0, 2).trim());
			
			//MATRÍCULA DO IMÓVEL
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento2(idPagamento.substring(2, 8).trim());
			
			// ANO E MÊS DA CONTA
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento3(idPagamento.substring(8, 14).trim());
			
			// CODIGO ORIGEM DA CONTA
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento4(idPagamento.substring(14, 16).trim());
			
			// NÚMERO DO DOCUMENTO
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento5(idPagamento.substring(16, 24).trim());
			
			eHLegado = true;
		}
		/*
		 * Se o tipo do pagamento for igual a 5 (documento de cobrança), o primeiro dígito for igual a 1, o segundo dígito for igual a 0, 
		 * tratar como legado, caso contrário, tratar como documento do gsan.
		 */
		else if (tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_DOCUMENTO_COBRANCA.toString()) && 
				idPagamento.substring(0, 2).trim().equals("10")){
			
			//TIPO DO PAGAMENTO
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento1(idPagamento.substring(0, 2).trim());
			
			//MATRÍCULA DO IMÓVEL
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento2(idPagamento.substring(2, 8).trim());
			
			//NÚMERO DO DOCUMENTO
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento5(idPagamento.substring(8, 24).trim());
			
			eHLegado = true;
		}
		/*
		 * Se o tipo do pagamento for igual a 4 (guia de pagamento) e o dígito da posição 14 for igual a zero, tratar como legado,
		 * caso contrário, tratar como documento do gsan.
		 */
		else if (tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO.toString()) && 
				idPagamento.substring(14, 15).trim().equals("0")){
			
			//TIPO DO PAGAMENTO
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento1(idPagamento.substring(0, 2).trim());
			
			//CÓDIGO DA LOCALIDADE
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento2(idPagamento.substring(2, 5).trim());

			//MATRÍCULA DO IMÓVEL
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento3(idPagamento.substring(5, 13).trim());

			//NÃO UTILIZADO (2)
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento4(idPagamento.substring(13, 15).trim());

			//CÓDIGO DO TIPO DO DÉBITO
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento5(idPagamento.substring(15, 19).trim());

			//ANO DA EMISSÃO DA GUIA DE PAGAMENTO
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento6(idPagamento.substring(19, 23).trim());
			
			eHLegado = true;
		}
		/*
		 * Se o tipo do pagamento for igual a 6 (GUIA DE PAGAMENTO PARA CLIENTE), o primeiro dígito for igual a 0, o segundo
		 * dígito for igual a 6, tratar como legado, caso contrário, tratar como documento do gsan.
		 */
		else if (tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_CLIENTE.toString()) && 
				idPagamento.substring(0, 2).trim().equals("06")){

			//TIPO DO PAGAMENTO
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento1(idPagamento.substring(0, 2).trim());
			
			//CÓDIGO DA LOCALIDADE
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento2(idPagamento.substring(2, 5).trim());

			//MATRÍCULA DO IMÓVEL
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento3(idPagamento.substring(5, 13).trim());

			//NÃO UTILIZADO (2)
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento4(idPagamento.substring(13, 15).trim());

			//CÓDIGO DO TIPO DO DÉBITO
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento5(idPagamento.substring(15, 19).trim());

			//ANO DA EMISSÃO DA GUIA DE PAGAMENTO
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento6(idPagamento.substring(19, 23).trim());
			
			eHLegado = true;
		}
		/*
		 * Se o tipo do pagamento for igual a 7 (FATURA CLIENTE RESPONSÁVEL), os dois primeiros dígitos forem iguais a "07" 
		 * e os dígitos das posições 10 e 11 forem iguais a zero, tratar como legado, caso contrário, tratar como documento do gsan.
		 */
		else if (tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_FATURA_CLIENTE_RESPONSAVEL.toString()) && 
				idPagamento.substring(0, 2).trim().equals("07")){

			//TIPO DO PAGAMENTO
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento1(idPagamento.substring(0, 2).trim());
			
			//CÓDIGO DO CLIENTE
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento2(idPagamento.substring(2, 11).trim());
			
			//MÊS E ANO DE REFERÊNCIA DA CONTA
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento4(idPagamento.substring(11, 17).trim());
			
			//DÍGITO VERIFICADOR DA CONTA NO MÓDULO 10
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento5(idPagamento.substring(17, 18).trim());
			
			//SEQUENCIAL DA FATURA DO CLIENTE RESPONSAVEL
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento6(idPagamento.substring(18, 24).trim());
			
			eHLegado = true;
		}
		/*
		 * Se o tipo do pagamento for igual a 8 (DOCUMENTO DE COBRANÇA PARA CLIENTE) e o dígito da posição 2 for igual a oito, 
		 * tratar como legado, caso contrário, tratar como documento do gsan.
		 */
		else if (tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_DOCUMENTO_COBRANCA_NOVO.toString()) && 
				idPagamento.substring(0, 2).trim().equals("08")){

			//TIPO DO PAGAMENTO
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento1(idPagamento.substring(0, 2).trim());
			
			//NÃO UTILIZADO (3)
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento2(idPagamento.substring(2, 5).trim());

			//CÓDIGO DO CLIENTE
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento3(idPagamento.substring(5, 13).trim());

			//SEQUENCIAL DO DOCUMENTO DE COBRANÇA
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento4(idPagamento.substring(13, 22).trim());

			//CÓDIGO DO TIPO DO DOCUMENTO
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento5(idPagamento.substring(22, 24).trim());

			eHLegado = true;
		}
		
		if (!eHLegado){
			
			registroHelperCodigoBarrasTipoPagamento = super.distribuirDadosCodigoBarrasPorTipoPagamento(
			idPagamento, tipoPagamento.trim(), idEmpresa);
		}

		return registroHelperCodigoBarrasTipoPagamento;
	}

	
	/**
	 * Obtém a representação númerica do código de barras de um pagamento de
	 * acordo com os parâmetros informados
	 * 
	 * [UC0229] Obter Representação Numérica do Código de Barras
	 * 
	 * Formata a identificação do pagamento de acordo com o tipo de pagamento
	 * informado
	 * 
	 * [SB0001] Obter Identificação do Pagamento
	 * 
	 * @author Pedro Alexandre
	 * @date 20/04/2006
	 * 
	 * @param tipoPagamento
	 * @param idLocalidade
	 * @param matriculaImovel
	 * @param anoMesReferenciaConta
	 * @param digitoVerificadorRefContaModulo10
	 * @param idTipoDebito
	 * @param anoEmissaoGuiaPagamento
	 * @param sequencialDocumentoCobranca
	 * @param idTipoDocumento
	 * @param idCliente
	 * @param seqFaturaClienteResponsavel
	 * @param idDebitoCreditoSituacaoAtual
	 * @return
	 */
	public String obterIdentificacaoPagamento(Integer tipoPagamento,
			Integer idLocalidade, Integer matriculaImovel,
			String mesAnoReferenciaConta,
			Integer digitoVerificadorRefContaModulo10, Integer idTipoDebito,
			String anoEmissaoGuiaPagamento, String sequencialDocumentoCobranca,
			Integer idTipoDocumento, Integer idCliente,
			Integer seqFaturaClienteResponsavel,String idGuiaPagamento) throws ControladorException {

		// Cria a variável que vai armazenar o identificador do pagamento formatado
		String identificacaoPagamento = "";

		//Caso o tipo de pagamento seja referente a guia de pagamento (Imóvel)
		if (tipoPagamento.intValue() == 4) {
			
			identificacaoPagamento = identificacaoPagamento + Util.adicionarZerosEsquedaNumero(3, "" + idLocalidade);
			identificacaoPagamento = identificacaoPagamento + Util.adicionarZerosEsquedaNumero(9, "" + matriculaImovel);
			
			//Identifica o tamanho da matrícula do imóvel
			identificacaoPagamento = identificacaoPagamento + "1";
			
			identificacaoPagamento = identificacaoPagamento + (Util.adicionarZerosEsquedaNumero(4, idTipoDebito.toString()));
			
			identificacaoPagamento = identificacaoPagamento + anoEmissaoGuiaPagamento;
			
			identificacaoPagamento = identificacaoPagamento + "000";
			
		} 
		//Caso o tipo de pagamento seja referente a guia de pagamento (Cliente)
		else if (tipoPagamento.intValue() == 6) {
			
			identificacaoPagamento = identificacaoPagamento + Util.adicionarZerosEsquedaNumero(3, "" + idLocalidade);
			identificacaoPagamento = identificacaoPagamento + Util.adicionarZerosEsquedaNumero(8, "" + idCliente);
			
			identificacaoPagamento = identificacaoPagamento + "00";
			
			identificacaoPagamento = identificacaoPagamento + (Util.adicionarZerosEsquedaNumero(4, idTipoDebito.toString()));
			identificacaoPagamento = identificacaoPagamento + anoEmissaoGuiaPagamento;
			
			identificacaoPagamento = identificacaoPagamento + "000";

		}
		
		/*
		 * Caso o documento não seja do tipo de guia de pagamento ou guia de pagamento para cliente; a identificação do pagamento
		 * será feita no padrão do GSAN.
		 */ 
		if (identificacaoPagamento.equals("")){
			
			identificacaoPagamento = super.obterIdentificacaoPagamento(tipoPagamento, idLocalidade, matriculaImovel,
			mesAnoReferenciaConta, digitoVerificadorRefContaModulo10, idTipoDebito,
			anoEmissaoGuiaPagamento, sequencialDocumentoCobranca, idTipoDocumento, 
			idCliente, seqFaturaClienteResponsavel, idGuiaPagamento);
		}

		// Retorna o identificador do pagamento formatado
		return identificacaoPagamento;
	}

	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 * 
	 * GUIA DE PAGAMENTO
	 * 
	 * Autor: Raphael Rossiter Data: 31/08/2007
	 */
	protected PagamentoHelperCodigoBarras processarPagamentosCodigoBarrasGuiaPagamentoLEGADO(
			RegistroHelperCodigoBarras registroHelperCodigoBarras,
			SistemaParametro sistemaParametro, Date dataPagamento,
			Integer anoMesPagamento, BigDecimal valorPagamento,
			Integer idFormaPagamento) throws ControladorException {

		PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = new PagamentoHelperCodigoBarras();

		String descricaoOcorrencia = "OK";

		String indicadorAceitacaoRegistro = "1";

		Collection colecaoPagamnetos = new ArrayList();

		boolean idLocalidadeInvalida = false;
		boolean matriculaImovelInvalida = false;

		Integer idImovelNaBase = null;
		String matriculaImovel = null;
		Integer matriculaImovelValidada = null;

		idLocalidadeInvalida = Util
				.validarValorNaoNumerico(registroHelperCodigoBarras
						.getRegistroHelperCodigoBarrasTipoPagamento()
						.getIdPagamento2());

		if (idLocalidadeInvalida) {
			descricaoOcorrencia = "CÓDIGO DA LOCALIDADE NÃO NUMÉRICA";
		}

		matriculaImovelInvalida = Util
				.validarValorNaoNumerico(registroHelperCodigoBarras
						.getRegistroHelperCodigoBarrasTipoPagamento()
						.getIdPagamento3());

		if (matriculaImovelInvalida) {
			descricaoOcorrencia = "MÁTRICULA DO IMÓVEL INVÁLIDA";
		} else {

			// [FS0008] - Calcular Digito Verificador da Matricula CAER
			matriculaImovel = registroHelperCodigoBarras
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento3();

			if (matriculaImovel != null) {
				int digitoModulo11 = Util
						.obterDigitoVerificadorModulo11(matriculaImovel);

				matriculaImovelValidada = new Integer(matriculaImovel
						+ digitoModulo11);
			}

			// Verifica se existe a matricula do imóvel na base
			try {
				idImovelNaBase = repositorioImovel
						.recuperarMatriculaImovel(matriculaImovelValidada);
			} catch (ErroRepositorioException e) {
				e.printStackTrace();
				throw new ControladorException("erro.sistema", e);
			}

			if (idImovelNaBase == null) {
				descricaoOcorrencia = "MATRÍCULA DO IMÓVEL NÃO CADASTRADA";
			}
		}

		// Valida o tipo do débito
		boolean codigoTipoDebito = Util
				.validarValorNaoNumerico(registroHelperCodigoBarras
						.getRegistroHelperCodigoBarrasTipoPagamento()
						.getIdPagamento5());

		if (codigoTipoDebito) {
			descricaoOcorrencia = "TIPO DO DÉBITO NÃO NUMÉRICO";
		} else {

			Integer idDebitoTipoNaBase = getControladorFaturamento()
					.verificarExistenciaDebitoTipo(
							Util
									.converterStringParaInteger(registroHelperCodigoBarras
											.getRegistroHelperCodigoBarrasTipoPagamento()
											.getIdPagamento5()));

			if (idDebitoTipoNaBase == null) {
				descricaoOcorrencia = "TIPO DO DÉBITO INEXISTENTE";
			}
		}

		if (descricaoOcorrencia.equals("OK")) {

			Integer idLocalidade = null;

			Integer idGuiaPagamento = null;

			Integer idDebitoTipo = new Integer(registroHelperCodigoBarras
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento5());

			Imovel imovel = new Imovel();
			imovel.setId(idImovelNaBase);

			try {
				/*
	             * Alterado por Ana Maria em 11/08/2008 - Analistas: Denys e Aryed
	             * Colocar o valor do pagamento na pesquisa de guia. Caso retorne mais
	             * de uma guia de pagamento, selecionar a que tiver o menor vencimento.
	             */
				
				idGuiaPagamento = repositorioArrecadacao
						.pesquisarExistenciaGuiaPagamento(imovel, idDebitoTipo, valorPagamento);

			} catch (ErroRepositorioException e) {
				e.printStackTrace();
				throw new ControladorException("erro.sistema", e);
			}

			if (idGuiaPagamento == null || idGuiaPagamento.equals("")) {
				descricaoOcorrencia = "GUIA PAGAMENTO INEXISTENTE";
			}
			
			/**
			 * Alterado por Arthur Carvalho em 29/12/2009 - Analista Rafael Pinto
			 * Gera Guia de Pagamento. Caso a Guia de Pagamento seja NUlA e o Debito tipo = DOACAO.
			 */
			if ( idGuiaPagamento == null ) {
				
				FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
				filtroDebitoTipo.adicionarParametro( 
					new ParametroSimples( FiltroDebitoTipo.ID, idDebitoTipo) );

				Collection colecaoDebitoTipo = 
					getControladorUtil().pesquisar(filtroDebitoTipo, 
						DebitoTipo.class.getName());
				
				if ( colecaoDebitoTipo != null && !colecaoDebitoTipo.equals("")) {
					
					DebitoTipo debitoTipo = 
						(DebitoTipo) Util.retonarObjetoDeColecao(colecaoDebitoTipo);
					
					if (debitoTipo.getCodigoConstante() != null && DebitoTipo.DOACAO == debitoTipo.getCodigoConstante().intValue() ) {
						
						//Gera Guia de Pagamento
						idGuiaPagamento = this.inserirGuiaPagamentoReferenteDebitoTipoDoacao(
								idImovelNaBase, 
								debitoTipo, 
								dataPagamento, 
								valorPagamento);
						
					}
				}
			}
			
			/*
             * Alterado por Raphael Rossiter em 11/01/2008 - Analistas: Eduardo e Aryed
             * OBJ: Gerar os pagamentos associados com a localidade da guia de pagamento e NÃO com
             * a localidade do imóvel.
             */
            if (idGuiaPagamento != null) {
            	
            	try {
                    idLocalidade = repositorioLocalidade
                    .pesquisarIdLocalidadePorGuiaPagamento(idGuiaPagamento);

                } catch (ErroRepositorioException e) {
                    throw new ControladorException("erro.sistema", e);
                }
                
            }
            else{
            	
            	try {
                    idLocalidade = repositorioLocalidade
                    .pesquisarIdLocalidade(idImovelNaBase);

                } catch (ErroRepositorioException e) {
                    throw new ControladorException("erro.sistema", e);
                }
            }
            

			// Cria o objeto pagamento para setar os dados
			Pagamento pagamento = new Pagamento();
			pagamento.setAnoMesReferenciaPagamento(null);

			/*
			 * Caso o ano mes da data de dedito seja maior que o ano mes de
			 * arrecadação da tabela sistema parametro então seta o ano mes da
			 * data de debito
			 */
			if (anoMesPagamento > sistemaParametro.getAnoMesArrecadacao()) {

				pagamento.setAnoMesReferenciaArrecadacao(anoMesPagamento);

			} else {

				/*
				 * caso contrario seta o o ano mes arrecadação da tabela sistema
				 * parametro
				 */
				pagamento.setAnoMesReferenciaArrecadacao(sistemaParametro
						.getAnoMesArrecadacao());
			}

			pagamento.setValorPagamento(valorPagamento);
			pagamento.setDataPagamento(dataPagamento);
			pagamento.setPagamentoSituacaoAtual(null);
			pagamento.setPagamentoSituacaoAnterior(null);
			DebitoTipo debitoTipo = new DebitoTipo();
			debitoTipo.setId(idDebitoTipo);
			pagamento.setDebitoTipo(debitoTipo);

			pagamento.setContaGeral(null);

			// Verifica se o id da conta é diferente de nulo
			if (idGuiaPagamento != null) {

				GuiaPagamento guiaPagamento = new GuiaPagamento();
				guiaPagamento.setId(idGuiaPagamento);
				pagamento.setGuiaPagamento(guiaPagamento);

			} else {
				pagamento.setGuiaPagamento(null);
			}

			// verifica se o id da conta é diferente de nulo
			if (idLocalidade != null) {

				Localidade localidade = new Localidade();
				localidade.setId(idLocalidade);
				pagamento.setLocalidade(localidade);

			} else {
				pagamento.setLocalidade(null);
			}

			DocumentoTipo documentoTipo = new DocumentoTipo();
			documentoTipo.setId(DocumentoTipo.GUIA_PAGAMENTO);
			pagamento.setDocumentoTipo(documentoTipo);

			pagamento.setAvisoBancario(null);

			if (idImovelNaBase != null) {
				pagamento.setImovel(imovel);
			} else {
				pagamento.setImovel(null);
			}

			pagamento.setArrecadadorMovimentoItem(null);

			ArrecadacaoForma arrecadacaoForma = new ArrecadacaoForma();
			arrecadacaoForma.setId(idFormaPagamento);
			pagamento.setArrecadacaoForma(arrecadacaoForma);
			pagamento.setCliente(null);
			pagamento.setUltimaAlteracao(new Date());
			
			pagamento.setFatura(null);
			pagamento.setCobrancaDocumento(null);
			
			DocumentoTipo documentoAgregador = new DocumentoTipo();
			documentoAgregador.setId(DocumentoTipo.GUIA_PAGAMENTO);
			pagamento.setDocumentoTipoAgregador(documentoAgregador);
			
			colecaoPagamnetos.add(pagamento);

		} else {

			// Atribui o valor 2(NÃO) ao indicador aceitacao registro
			indicadorAceitacaoRegistro = "2";
		}

		// Seta os parametros que serão retornados
		pagamentoHelperCodigoBarras.setColecaoPagamentos(colecaoPagamnetos);
		pagamentoHelperCodigoBarras.setDescricaoOcorrencia(descricaoOcorrencia);
		pagamentoHelperCodigoBarras
				.setIndicadorAceitacaoRegistro(indicadorAceitacaoRegistro);

		return pagamentoHelperCodigoBarras;
	}

	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 * 
	 * GUIA DE PAGAMENTO CLIENTE(Tipo 6)
	 * 
	 * Autor: Ana Maria Data: 06/08/2007
	 */
	protected PagamentoHelperCodigoBarras processarPagamentosCodigoBarrasGuiaPagamentoClienteLEGADO(
			RegistroHelperCodigoBarras registroHelperCodigoBarras,
			SistemaParametro sistemaParametro, Date dataPagamento,
			Integer anoMesPagamento, BigDecimal valorPagamento,
			Integer idFormaPagamento) throws ControladorException {

		PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = new PagamentoHelperCodigoBarras();

		String descricaoOcorrencia = "OK";

		String indicadorAceitacaoRegistro = "1";

		Collection colecaoPagamnetos = new ArrayList();

		boolean idLocalidadeInvalida = false;
		boolean idClienteInvalido = false;

		Integer idClienteNaBase = null;

		idLocalidadeInvalida = Util
				.validarValorNaoNumerico(registroHelperCodigoBarras
						.getRegistroHelperCodigoBarrasTipoPagamento()
						.getIdPagamento2());

		if (idLocalidadeInvalida) {
			descricaoOcorrencia = "CÓDIGO DA LOCALIDADE NÃO NUMÉRICA";
		}

		idClienteInvalido = Util
				.validarValorNaoNumerico(registroHelperCodigoBarras
						.getRegistroHelperCodigoBarrasTipoPagamento()
						.getIdPagamento3());

		Integer idCliente = null;
		if (idClienteInvalido) {
			descricaoOcorrencia = "CÓDIGO DO CLIENTE NÃO NUMÉRICO";
		} else {
			// verifica se existe o id do cliente na
			// base
			idCliente = new Integer(registroHelperCodigoBarras
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento3());

			try {
				idClienteNaBase = repositorioCliente
						.verificarExistenciaCliente(new Integer(idCliente));
			} catch (ErroRepositorioException e) {
				throw new ControladorException("erro.sistema", e);
			}

			if (idClienteNaBase == null) {
				descricaoOcorrencia = "CLIENTE RESPONSÁVEL NÂO CADASTRADO";
			}
		}

		// Valida o namo mes de referencia da conta
		boolean codigoTipoDebito = Util
				.validarValorNaoNumerico(registroHelperCodigoBarras
						.getRegistroHelperCodigoBarrasTipoPagamento()
						.getIdPagamento5());

		if (codigoTipoDebito) {
			descricaoOcorrencia = "TIPO DO DÉBITO NÃO NUMÉRICO";
		} else {

			Integer idDebitoTipoNaBase = getControladorFaturamento()
					.verificarExistenciaDebitoTipo(
							Util
									.converterStringParaInteger(registroHelperCodigoBarras
											.getRegistroHelperCodigoBarrasTipoPagamento()
											.getIdPagamento5()));

			if (idDebitoTipoNaBase == null) {
				descricaoOcorrencia = "TIPO DO DÉBITO INEXISTENTE";
			}
		}

		if (descricaoOcorrencia.equals("OK")) {

			Integer idLocalidade = new Integer(registroHelperCodigoBarras
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento2());

			Integer idGuiaPagamento = null;

			Integer idDebitoTipo = new Integer(registroHelperCodigoBarras
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento5());

			// Imovel imovel = new Imovel();
			// imovel.setId(idImovelNaBase);

			try {
				// idLocalidade =
				// repositorioLocalidade.pesquisarIdLocalidade(idImovelNaBase);

				idGuiaPagamento = repositorioArrecadacao
						.pesquisarExistenciaGuiaPagamentoCliente(idCliente,
								idDebitoTipo);

			} catch (ErroRepositorioException e) {
				e.printStackTrace();
				throw new ControladorException("erro.sistema", e);
			}

			if (idGuiaPagamento == null || idGuiaPagamento.equals("")) {
				descricaoOcorrencia = "GUIA PAGAMENTO INEXISTENTE";
			}

			// Cria o objeto pagamento para setar os dados
			Pagamento pagamento = new Pagamento();
			pagamento.setAnoMesReferenciaPagamento(null);

			/*
			 * Caso o ano mes da data de dedito seja maior que o ano mes de
			 * arrecadação da tabela sistema parametro então seta o ano mes da
			 * data de debito
			 */
			if (anoMesPagamento > sistemaParametro.getAnoMesArrecadacao()) {

				pagamento.setAnoMesReferenciaArrecadacao(anoMesPagamento);

			} else {

				/*
				 * caso contrario seta o o ano mes arrecadação da tabela sistema
				 * parametro
				 */
				pagamento.setAnoMesReferenciaArrecadacao(sistemaParametro
						.getAnoMesArrecadacao());
			}

			pagamento.setValorPagamento(valorPagamento);
			pagamento.setDataPagamento(dataPagamento);
			pagamento.setPagamentoSituacaoAtual(null);
			pagamento.setPagamentoSituacaoAnterior(null);
			DebitoTipo debitoTipo = new DebitoTipo();
			debitoTipo.setId(idDebitoTipo);
			pagamento.setDebitoTipo(debitoTipo);

			pagamento.setContaGeral(null);

			// Verifica se o id da conta é diferente de nulo
			if (idGuiaPagamento != null) {

				GuiaPagamento guiaPagamento = new GuiaPagamento();
				guiaPagamento.setId(idGuiaPagamento);
				pagamento.setGuiaPagamento(guiaPagamento);

			} else {
				pagamento.setGuiaPagamento(null);
			}

			// verifica se o id da conta é diferente de nulo
			if (idLocalidade != null) {

				Localidade localidade = new Localidade();
				localidade.setId(idLocalidade);
				pagamento.setLocalidade(localidade);

			} else {
				pagamento.setLocalidade(null);
			}

			DocumentoTipo documentoTipo = new DocumentoTipo();
			documentoTipo.setId(DocumentoTipo.GUIA_PAGAMENTO);
			pagamento.setDocumentoTipo(documentoTipo);

			pagamento.setAvisoBancario(null);

			if (idCliente != null) {
				Cliente cliente = new Cliente();
				cliente.setId(idCliente);
				pagamento.setCliente(cliente);
			} else {
				pagamento.setCliente(null);
			}

			pagamento.setArrecadadorMovimentoItem(null);

			ArrecadacaoForma arrecadacaoForma = new ArrecadacaoForma();
			arrecadacaoForma.setId(idFormaPagamento);
			pagamento.setArrecadacaoForma(arrecadacaoForma);
			pagamento.setImovel(null);
			pagamento.setUltimaAlteracao(new Date());

			pagamento.setFatura(null);
			pagamento.setCobrancaDocumento(null);
			
			DocumentoTipo documentoAgregador = new DocumentoTipo();
			documentoAgregador.setId(DocumentoTipo.GUIA_PAGAMENTO);
			pagamento.setDocumentoTipoAgregador(documentoAgregador);			

			colecaoPagamnetos.add(pagamento);

		} else {

			// Atribui o valor 2(NÃO) ao indicador aceitacao registro
			indicadorAceitacaoRegistro = "2";
		}

		// Seta os parametros que serão retornados
		pagamentoHelperCodigoBarras.setColecaoPagamentos(colecaoPagamnetos);
		pagamentoHelperCodigoBarras.setDescricaoOcorrencia(descricaoOcorrencia);
		pagamentoHelperCodigoBarras
				.setIndicadorAceitacaoRegistro(indicadorAceitacaoRegistro);

		return pagamentoHelperCodigoBarras;
	}

	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 * 
	 * DOCUMENTO COBRANÇA TIPO 08
	 * 
	 * Autor: Raphael Rossiter Data: 02/05/2007
	 */
	protected PagamentoHelperCodigoBarras processarPagamentosCodigoBarrasDocumentoCobrancaClienteLEGADO(
			RegistroHelperCodigoBarras registroHelperCodigoBarras,
			SistemaParametro sistemaParametro, Date dataPagamento,
			Integer anoMesPagamento, BigDecimal valorPagamento,
			Integer idFormaPagamento, Usuario usuarioLogado) throws ControladorException {

		PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = new PagamentoHelperCodigoBarras();

		String descricaoOcorrencia = "OK";

		String indicadorAceitacaoRegistro = "1";

		Collection colecaoPagamnetos = new ArrayList();

		// valida o cliente
		boolean idClienteInvalido = Util
				.validarValorNaoNumerico(registroHelperCodigoBarras
						.getRegistroHelperCodigoBarrasTipoPagamento()
						.getIdPagamento3());

		Integer idClienteNaBase = null;

		if (idClienteInvalido) {
			descricaoOcorrencia = "CÓDIGO DO CLIENTE NÃO NUMÉRICO";
		} else {
			// verifica se existe o id do cliente na
			// base
			Integer idCliente = new Integer(registroHelperCodigoBarras
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento3());

			try {
				idClienteNaBase = repositorioCliente
						.verificarExistenciaCliente(new Integer(idCliente));
			} catch (ErroRepositorioException e) {
				throw new ControladorException("erro.sistema", e);
			}

			if (idClienteNaBase == null) {
				descricaoOcorrencia = "CLIENTE RESPONSÁVEL NÂO CADASTRADO";
			}
		}

		// valida o namo mes de referencia da conta
		boolean tipoDocumentoInvalido = Util
				.validarValorNaoNumerico(registroHelperCodigoBarras
						.getRegistroHelperCodigoBarrasTipoPagamento()
						.getIdPagamento5());

		if (tipoDocumentoInvalido) {
			descricaoOcorrencia = "TIPO DO DOCUMENTO NÃO NUMÉRICO";
		}

		if (descricaoOcorrencia.equals("OK")) {
			// inicializa o id da localidade
			// Integer idLocalidade = null;

			// inicializa a coleção de cobranca documento item
			Collection cobrancaDocumentoItens = null;
			// inicializa a coleção de cobranca documento item
			Object[] parmsDocumentoCobranca = null;
			int numeroSequencialDocumento = Integer
					.parseInt(registroHelperCodigoBarras
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento4());

			try {

				cobrancaDocumentoItens = repositorioCobranca
						.pesquisarCobrancaDocumentoItemCliente(idClienteNaBase,
								numeroSequencialDocumento);
				
				 parmsDocumentoCobranca = repositorioCobranca.pesquisarParmsCobrancaDocumentoCliente( idClienteNaBase,numeroSequencialDocumento);
				 
			} catch (ErroRepositorioException e) {
				throw new ControladorException("erro.sistema", e);
			}
			
			/**
			 * Alteração para inserir pagamentos para os acrescimos das guias de pagamentos por cliente.
			 * Na funcionalidade extrato de débitos, é possivel gerar acrescimos para as guias de pagamentos de cliente. 
			 * @author Arthur Carvalho
			 * @date 12/12/2012
			 */
			
			// caso exista documento de cobrança
			if (parmsDocumentoCobranca != null) {
				
				Integer idCobrancaDocumentoCliente = null;
				BigDecimal valorAcrescimo = new BigDecimal("0.00");
				Date dataEmissao = null;
				Integer idDocumentoTipoCliente = null;
				Integer idCobrancaDebitoSituacao = null;
				
				if (parmsDocumentoCobranca[0] != null) {
					valorAcrescimo = ((BigDecimal) parmsDocumentoCobranca[0]);
				}
				
				if (parmsDocumentoCobranca[1] != null) {
					dataEmissao = ((Date) parmsDocumentoCobranca[1]);
				}
				if (parmsDocumentoCobranca[2] != null) {
					idCobrancaDocumentoCliente = ((Integer) parmsDocumentoCobranca[2]);
				}
				
				/*
                 * Alterado por Raphael Rossiter em 10/01/2008 - Analistas: Eduardo e Aryed
                 * OBJ: Gerar os pagamentos associados com a localidade do document de cobrança e NÃO com
                 * a localidade do imóvel.
                 */
				Integer idLocalidade = null;
				if (parmsDocumentoCobranca[3] != null) {

					idLocalidade = ((Integer) parmsDocumentoCobranca[3]);
				}
				else{
					
					try {
						// inicializa a coleção de cobranca documento item
						Object[] parmsLocalidade = null;
						parmsLocalidade = repositorioCobranca.pesquisarLocalidadeDaGuiaPagamentoCliente(idClienteNaBase, numeroSequencialDocumento);
						
						if ( parmsLocalidade != null ) {
							
							if ( parmsLocalidade[0] != null ) {
								idLocalidade = (Integer) parmsLocalidade[0]; 
							} 
						}
						

                    } catch (ErroRepositorioException e) {
                        throw new ControladorException("erro.sistema", e);
                    }
				}
				
				if (parmsDocumentoCobranca[4] != null) {
					idDocumentoTipoCliente = ((Integer) parmsDocumentoCobranca[4]);
				}
				//RM3453 - adicionado por Vivianne Sousa - 14/07/2012
				if(parmsDocumentoCobranca[5] != null){
					idCobrancaDebitoSituacao = (Integer)parmsDocumentoCobranca[5];
				}

				// caso o valor de acrescimo for maior que zero
				if (valorAcrescimo.compareTo(new BigDecimal("0.00")) == 1) {
					
					//RM3453 - adicionado por Vivianne Sousa - 14/07/2012
					if((idDocumentoTipoCliente == null || idCobrancaDebitoSituacao == null)
						||	!(idDocumentoTipoCliente.equals(DocumentoTipo.EXTRATO_ENTRADA_PARCELAMENTO) 
							&& idCobrancaDebitoSituacao.equals(CobrancaDebitoSituacao.CANCELADO))){
						
						// [SB0008 - Alterar vencimento dos itens do documento de cobrança]
						alterarVencimentoItensDocumentoCobranca(idCobrancaDocumentoCliente, dataEmissao);
					}
					
				}
			

				// caso o valor de acrescimo for maior que zero
				if (valorAcrescimo.compareTo(new BigDecimal("0.00")) == 1) {
					// [SB0005 - Processar Recebimento de Acrescimos
					// por
					// Impontualidade]

					Pagamento pagamento = processarRecebimentoAcrescimosImpontualidade(
							idCobrancaDocumentoCliente, dataPagamento, valorAcrescimo,
							null, idLocalidade, sistemaParametro,
							idFormaPagamento, idDocumentoTipoCliente, idClienteNaBase);

					colecaoPagamnetos.add(pagamento);

				}
			}
			/**
			 * Fim da alteração para gerar pagamento dos acrescimos cobrados para as guias de pagamento por cliente
			 */
		

			// caso exista documento de cobrança
			// verifica se a coleção é diferente de nula
			if (cobrancaDocumentoItens != null
					&& !cobrancaDocumentoItens.isEmpty()) {
				Iterator cobrancaDocumentoItensIterator = cobrancaDocumentoItens
						.iterator();
				while (cobrancaDocumentoItensIterator.hasNext()) {
					Object[] cobrancaDocumentoItem = (Object[]) cobrancaDocumentoItensIterator
							.next();
					// inicializa as variaveis que veio da
					// pesquisa
					Integer idContaPesquisa = null;
					Integer idContaGeralPesquisa = null;
					Integer idGuiaPagamento = null;
					Integer idDebitoACobrar = null;
					BigDecimal valorItemCobrado = null;
					int contaReferencia = 0;
					Integer idDebitoTipo = null;
					Integer idGuiaPagamentoGeralPesquisa = null;
					Integer idDebitoACobrarGeralPesquisa = null;
					Integer idCobrancaDocumento = null;
					Integer idDocumentoTipo = null;
					// verifica o id da conta
					if (cobrancaDocumentoItem[0] != null) {
						idContaPesquisa = (Integer) cobrancaDocumentoItem[0];
						idContaGeralPesquisa = (Integer) cobrancaDocumentoItem[0];
						// referencia conta
						if (cobrancaDocumentoItem[4] != null) {
							contaReferencia = (Integer) cobrancaDocumentoItem[4];
						}
					} else {
						// caso não exista na conta então pesquisa
						// na conta histórico
						if (cobrancaDocumentoItem[10] != null) {
							idContaGeralPesquisa = (Integer) cobrancaDocumentoItem[10];
						}

						// referencia conta histórico
						if (cobrancaDocumentoItem[5] != null) {
							contaReferencia = (Integer) cobrancaDocumentoItem[5];
						}
					}

					// verifica o id da guia pagamento
					if (cobrancaDocumentoItem[1] != null) {
						idGuiaPagamento = (Integer) cobrancaDocumentoItem[1];
						idGuiaPagamentoGeralPesquisa = (Integer) cobrancaDocumentoItem[1];
					} else {
						// caso não exista no guia pagamento então
						// pesquisa no guia pagamento histórico
						if (cobrancaDocumentoItem[11] != null) {
							idGuiaPagamentoGeralPesquisa = (Integer) cobrancaDocumentoItem[11];
						}
					}
					// verifica o id do debito a cobrar
					if (cobrancaDocumentoItem[2] != null) {
						idDebitoACobrar = (Integer) cobrancaDocumentoItem[2];
						idDebitoACobrarGeralPesquisa = (Integer) cobrancaDocumentoItem[2];

						// [SB0012]- Verifica Pagamento de Débito a Cobrar de
						// Parcelamento
						verificaPagamentoDebitoACobrarParcelamento(idDebitoACobrar, null, usuarioLogado);

					} else {
						// caso não exista no debito a cobrar então
						// pesquisa no guia pagamento histórico
						if (cobrancaDocumentoItem[12] != null) {
							idDebitoACobrarGeralPesquisa = (Integer) cobrancaDocumentoItem[12];
						}
					}
					// verifica o valor do item cobrado da
					// cobranca
					// documento item
					if (cobrancaDocumentoItem[3] != null) {
						valorItemCobrado = (BigDecimal) cobrancaDocumentoItem[3];
					}

					// se o id da conta for igual a null
					if (idContaGeralPesquisa == null) {
						// caso exista guia de pagamento
						if (idGuiaPagamentoGeralPesquisa != null) {
							// verifica o id do debito tipo se é da
							// guia
							if (cobrancaDocumentoItem[6] != null) {
								idDebitoTipo = (Integer) cobrancaDocumentoItem[6];
							} else {
								// caso não exista no guia pagamento
								// então
								// pesquisa no guia pagamento
								// histórico
								if (cobrancaDocumentoItem[7] != null) {
									idDebitoTipo = (Integer) cobrancaDocumentoItem[7];
								}
							}
						}
						// caso exista debito a cobrar
						if (idDebitoACobrarGeralPesquisa != null) {
							// verifica o id do debito tipo no
							// debito a cobrar
							if (cobrancaDocumentoItem[8] != null) {
								idDebitoTipo = (Integer) cobrancaDocumentoItem[8];

							} else {
								// caso não exista no debito a
								// cobrar
								// então
								// pesquisa no debito a cobrar
								// histórico
								if (cobrancaDocumentoItem[9] != null) {
									idDebitoTipo = (Integer) cobrancaDocumentoItem[9];
								}
							}
						}
					}

					// cria o objeto pagamento para setar os
					// dados
					Pagamento pagamento = new Pagamento();
					if (contaReferencia != 0) {
						pagamento.setAnoMesReferenciaPagamento(contaReferencia);
					} else {
						pagamento.setAnoMesReferenciaPagamento(null);
					}

					// caso o ano mes da data de dedito seja
					// maior que o ano mes de arrecadação da
					// tabela sistema parametro então seta o ano
					// mes da data de debito
					if (anoMesPagamento > sistemaParametro
							.getAnoMesArrecadacao()) {
						pagamento
								.setAnoMesReferenciaArrecadacao(anoMesPagamento);
					} else {
						// caso contrario seta o o ano mes
						// arrecadação da tabela sistema
						// parametro
						pagamento
								.setAnoMesReferenciaArrecadacao(sistemaParametro
										.getAnoMesArrecadacao());
					}
					pagamento.setValorPagamento(valorItemCobrado);
					pagamento.setDataPagamento(dataPagamento);
					pagamento.setPagamentoSituacaoAtual(null);
					pagamento.setPagamentoSituacaoAnterior(null);
					if (idDebitoTipo != null) {
						DebitoTipo debitoTipo = new DebitoTipo();
						debitoTipo.setId(idDebitoTipo);
						pagamento.setDebitoTipo(debitoTipo);
					} else {
						pagamento.setDebitoTipo(null);
					}

					// verifica se o id da conta é diferente de
					// nulo
					if (idContaGeralPesquisa != null) {
						if (idContaPesquisa != null) {
							ContaGeral conta = new ContaGeral();
							conta.setId(idContaPesquisa);
							pagamento.setContaGeral(conta);
						} else {
							pagamento.setContaGeral(null);
						}

						DocumentoTipo documentoTipo = new DocumentoTipo();
						documentoTipo.setId(DocumentoTipo.CONTA);
						pagamento.setDocumentoTipo(documentoTipo);
					} else {
						pagamento.setContaGeral(null);
					}
					// verifica se o id da guia de pagamento é
					// diferente de nulo
					if (idGuiaPagamentoGeralPesquisa != null) {
						if (idGuiaPagamento != null) {
							GuiaPagamento guiaPagamento = new GuiaPagamento();
							guiaPagamento.setId(idGuiaPagamento);
							pagamento.setGuiaPagamento(guiaPagamento);

						} else {
							pagamento.setGuiaPagamento(null);
						}
						DocumentoTipo documentoTipo = new DocumentoTipo();
						documentoTipo.setId(DocumentoTipo.GUIA_PAGAMENTO);
						pagamento.setDocumentoTipo(documentoTipo);

					} else {
						pagamento.setGuiaPagamento(null);
					}

					// verifica se o id do debito a cobrar é
					// diferente de nulo
					if (idDebitoACobrarGeralPesquisa != null) {
						if (idDebitoACobrar != null) {

							DebitoACobrarGeral debitoACobrarGeral = new DebitoACobrarGeral();
							debitoACobrarGeral.setId(idDebitoACobrar);
							
							pagamento.setDebitoACobrarGeral(debitoACobrarGeral);

							try {
								// atualiza a situação atual para
								// cancelada
								repositorioFaturamento
										.atualizarSituacaoAtualDebitoACobrar(idDebitoACobrar);
							} catch (ErroRepositorioException e) {
								throw new ControladorException("erro.sistema",
										e);
							}

						} else {
							pagamento.setDebitoACobrarGeral(null);
						}
						DocumentoTipo documentoTipo = new DocumentoTipo();
						documentoTipo.setId(DocumentoTipo.DEBITO_A_COBRAR);
						pagamento.setDocumentoTipo(documentoTipo);

					} else {
						pagamento.setGuiaPagamento(null);
					}

					// seta o id do aviso bancario
					pagamento.setAvisoBancario(null);

					//SETANDO A LOCALIDADE E O IMÓVEL DO PAGAMENTO 
					Imovel imovel = new Imovel();
					Localidade localidade = new Localidade();
					
					//CONTA
					if (idContaGeralPesquisa != null) {
						
						if (cobrancaDocumentoItem[16] != null){
							
							//Imovel na tabela CONTA
							imovel.setId((Integer) cobrancaDocumentoItem[13]);
							
							//Localidade na tabela CONTA
							localidade.setId((Integer) cobrancaDocumentoItem[16]);
						}
						else{
							
							//Imovel na tabela CONTA_HISTORICO
							imovel.setId((Integer) cobrancaDocumentoItem[19]);
							
							//Localidade na tabela CONTA_HISTORICO
							localidade.setId((Integer) cobrancaDocumentoItem[20]);
						}
						
						pagamento.setImovel(imovel);
						pagamento.setLocalidade(localidade);
						
					} 
					
					//GUIA_PAGAMENTO
					else if (idGuiaPagamentoGeralPesquisa != null) {
						
						if (cobrancaDocumentoItem[17] != null){
							
							//Imovel na tabela GUIA_PAGAMENTO
							imovel.setId((Integer) cobrancaDocumentoItem[14]);
							
							//Localidade na tabela GUIA_PAGAMENTO
							localidade.setId((Integer) cobrancaDocumentoItem[17]);
						}
						else{
							
							//Imovel na tabela GUIA_PAGAMENTO_HISTORICO
							imovel.setId((Integer) cobrancaDocumentoItem[21]);
							
							//Localidade na tabela GUIA_PAGAMENTO_HISTORICO
							localidade.setId((Integer) cobrancaDocumentoItem[22]);
						}
						
						pagamento.setImovel(imovel);
						pagamento.setLocalidade(localidade);
						
					} 
					
					//DEBITO_A_COBRAR
					else if (idDebitoACobrarGeralPesquisa != null) {
						
						if (cobrancaDocumentoItem[18] != null){
							
							//Imovel na tabela DEBITO_A_COBRAR
							imovel.setId((Integer) cobrancaDocumentoItem[15]);
							
							//Localidade na tabela DEBITO_A_COBRAR
							localidade.setId((Integer) cobrancaDocumentoItem[18]);
						}
						else{
							
							//Imovel na tabela DEBITO_A_COBRAR_HISTORICO
							imovel.setId((Integer) cobrancaDocumentoItem[23]);
							
							//Localidade na tabela DEBITO_A_COBRAR_HISTORICO
							localidade.setId((Integer) cobrancaDocumentoItem[24]);
						}
						
						pagamento.setImovel(imovel);
						pagamento.setLocalidade(localidade);
					}

					/*
					 * Adicao dos campos 'id do Documento de cobranca' e 'id do tipo de documento' usados no
					 * relatório do Float
					 * Francisco 18/07/08
					 */
					if (cobrancaDocumentoItem[25] != null){
						idCobrancaDocumento = (Integer) cobrancaDocumentoItem[25]; 
					}
					if (cobrancaDocumentoItem[26] != null){
						idDocumentoTipo = (Integer) cobrancaDocumentoItem[26]; 
					}
					
					/*
					 * if (idImovelNaBase != null) { Imovel imovel = new
					 * Imovel(); imovel.setId(idImovelNaBase);
					 * pagamento.setImovel(imovel); } else {
					 * pagamento.setImovel(null); }
					 */

					// ArrecadadorMovimentoItem
					// arrecadadorMovimentoItem
					// = new ArrecadadorMovimentoItem();
					pagamento.setArrecadadorMovimentoItem(null);

					ArrecadacaoForma arrecadacaoForma = new ArrecadacaoForma();
					arrecadacaoForma.setId(idFormaPagamento);
					pagamento.setArrecadacaoForma(arrecadacaoForma);
					pagamento.setCliente(null);
					pagamento.setUltimaAlteracao(new Date());

					pagamento.setFatura(null);
					
					if (cobrancaDocumentoItem[25] != null){
						idCobrancaDocumento = (Integer) cobrancaDocumentoItem[25];	
					}
					if (cobrancaDocumentoItem[26] != null){
						idDocumentoTipo  = (Integer) cobrancaDocumentoItem[26];	
					}
					
					CobrancaDocumento cobrancaDocumento = new CobrancaDocumento();
					cobrancaDocumento.setId(idCobrancaDocumento);
					pagamento.setCobrancaDocumento(cobrancaDocumento);
					
					DocumentoTipo documentoAgregador = new DocumentoTipo();
					documentoAgregador.setId(idDocumentoTipo);
					pagamento.setDocumentoTipoAgregador(documentoAgregador);
					
					colecaoPagamnetos.add(pagamento);

				}
			} else {
				descricaoOcorrencia = "DOCUMENTO DE COBRANÇA INEXISTENTE";
				indicadorAceitacaoRegistro = "2";
			}

		} else {
			// atribui o valor 2(NÃO) ao indicador aceitacao
			// registro
			indicadorAceitacaoRegistro = "2";
		}

		// Seta os parametros que serão retornados
		pagamentoHelperCodigoBarras.setColecaoPagamentos(colecaoPagamnetos);
		pagamentoHelperCodigoBarras.setDescricaoOcorrencia(descricaoOcorrencia);
		pagamentoHelperCodigoBarras
				.setIndicadorAceitacaoRegistro(indicadorAceitacaoRegistro);

		return pagamentoHelperCodigoBarras;
	}

	protected void mandaArquivoLeituraEmail(StringBuilder arquivo,
			String emailReceptor, String emailRemetente, String tituloMensagem,
			String corpoMensagem) throws ControladorException {
		/*
		 * try { File leitura = new File("arquivo_leitura", ".txt");
		 * BufferedWriter out = new BufferedWriter(new OutputStreamWriter( new
		 * FileOutputStream(leitura.getAbsolutePath())));
		 * out.write(arquivo.toString()); out.close();
		 * 
		 * ServicosEmail.enviarMensagemArquivoAnexado(emailReceptor,
		 * emailRemetente, tituloMensagem, corpoMensagem, leitura);
		 * 
		 * leitura.delete(); } catch (IOException e) { throw new
		 * ControladorException("erro.sistema", e); } catch (Exception e) {
		 * throw new ControladorException("erro.sistema", e); }
		 */
	}

	/**
	 * [UC0270] Apresentar Análise do Movimento dos Arrecadadores
	 * 
	 * O sistema captura os dados referentes ao conteúdo do do código de barras
	 * 
	 * [SF0003] Apresentar Dados do Conteúdo do Código de Barras
	 * 
	 * @author Raphael Rossiter
	 * @data 22/03/2006
	 * 
	 * @param registroHelperCodigoG
	 * @return DadosConteudoCodigoBarrasHelper
	 */
	public DadosConteudoCodigoBarrasHelper apresentarDadosConteudoCodigoBarras(
			RegistroHelperCodigoG registroHelperCodigoG)
			throws ControladorException {

		DadosConteudoCodigoBarrasHelper retorno = new DadosConteudoCodigoBarrasHelper();

		retorno.setIdentificacaoProduto(registroHelperCodigoG.getRegistroHelperCodigoBarras().getIdProduto());
		retorno.setIdentificacaoSegmento(registroHelperCodigoG.getRegistroHelperCodigoBarras().getIdSegmento());
		retorno.setIdentificacaoValorRealOUReferencia(registroHelperCodigoG.getRegistroHelperCodigoBarras().getIdValorReal());
		retorno.setDigitoVerificadorGeral(registroHelperCodigoG.getRegistroHelperCodigoBarras().getDigitoVerificadorGeral());
		
		retorno.setValorPagamento(Util.formatarMoedaReal(Util
				.formatarMoedaRealparaBigDecimalComUltimos2CamposDecimais(registroHelperCodigoG
				.getRegistroHelperCodigoBarras().getValorPagamento())));

		//TIPO PAGAMENTO
		String tipoPagamento = registroHelperCodigoG.getRegistroHelperCodigoBarras().getTipoPagamento();
		
		//CÓDIGO DE BARRAS DISTRIBUIDO
		RegistroHelperCodigoBarrasTipoPagamento codigoBarrasTipoPagamento = registroHelperCodigoG.getRegistroHelperCodigoBarras()
		.getRegistroHelperCodigoBarrasTipoPagamento();
		
		//TIPO PAGAMENTO LEGADO
		String tipoPagamentoLEGADO = codigoBarrasTipoPagamento.getIdPagamento1();
		
		//PARA IDENTIFICAR LEGADO
		boolean eHLegado = false;
		
		String matriculaImovel = null;

		if (tipoPagamentoLEGADO != null &&
			tipoPagamentoLEGADO.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA_CAER) &&
			tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA.toString()) &&
			codigoBarrasTipoPagamento.getIdPagamento4().equals("00") &&
			codigoBarrasTipoPagamento.getIdPagamento5().equals("00000000")){

			retorno.setTipoPagamento(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA_CAER);

			matriculaImovel = new Integer(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento2()).toString();

			// Calcular Digito Verificador da Matricula
			if (matriculaImovel != null) {
				int digitoModulo11 = Util.obterDigitoVerificadorModulo11(""
						+ matriculaImovel);

				matriculaImovel = new Integer(matriculaImovel.toString()
						+ digitoModulo11).toString();
			}

			retorno.setMatriculaImovel(matriculaImovel);

			retorno.setMesAnoReferenciaConta(Util
					.formatarAnoMesParaMesAno(registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento3()));

			retorno.setCodigoOrigemConta(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento4().toString());

			retorno.setNumeroDocumento(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento5().toString());
			
			eHLegado = true;

		}  
		else if (tipoPagamentoLEGADO != null &&
				tipoPagamentoLEGADO.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_CLIENTE_CAER) &&
				tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_CLIENTE.toString())){
		

			retorno.setTipoPagamento(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_CLIENTE_CAER);

			retorno.setCodigoLocalidade(new Integer(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento2()).toString());

			retorno.setCodigoCliente(new Integer(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento3()).toString());

			retorno.setCodigoTipoDebito(new Integer(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento5()).toString());

			retorno.setAnoEmissaoGuiaPagamento(new Integer(
					registroHelperCodigoG.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento6()).toString());
			
			eHLegado = true;

		} 
		else if (tipoPagamentoLEGADO != null &&
				tipoPagamentoLEGADO.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_IMOVEL_CAER) &&
				tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO.toString())){

			retorno.setTipoPagamento(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_IMOVEL_CAER);

			retorno.setCodigoLocalidade(new Integer(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento2()).toString());

			matriculaImovel = new Integer(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento3()).toString();

			// Calcular Digito Verificador da Matricula
			if (matriculaImovel != null) {
				int digitoModulo11 = Util.obterDigitoVerificadorModulo11(""
						+ matriculaImovel);

				matriculaImovel = new Integer(matriculaImovel.toString()
						+ digitoModulo11).toString();
			}

			retorno.setMatriculaImovel(matriculaImovel);

			retorno.setCodigoTipoDebito(new Integer(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento5()).toString());

			retorno.setAnoEmissaoGuiaPagamento(new Integer(
					registroHelperCodigoG.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento6()).toString());
			
			eHLegado = true;

		} 
		else if (tipoPagamentoLEGADO != null &&
				tipoPagamentoLEGADO.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_DOC_COBRANCA_CLIENTE_RESPONSAVEL_CAER) &&
				tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_DOCUMENTO_COBRANCA_NOVO.toString())){

			retorno.setTipoPagamento(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_DOC_COBRANCA_CLIENTE_RESPONSAVEL_CAER);

			retorno.setCodigoCliente(new Integer(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento3()).toString());

			retorno.setSequencialDocumentoCobranca(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento4());

			retorno.setCodigoTipoDocumento(new Integer(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento5()).toString());
			
			eHLegado = true;

		} 
		else if (tipoPagamentoLEGADO != null &&
				tipoPagamentoLEGADO.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_FATURA_CLIENTE_RESPONSAVEL_CAER) &&
				tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_FATURA_CLIENTE_RESPONSAVEL.toString())){

			retorno.setTipoPagamento(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_FATURA_CLIENTE_RESPONSAVEL_CAER);

			retorno.setCodigoCliente(new Integer(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento2()).toString());

			retorno.setMesAno(Util
					.formatarMesAnoSemBarraParaMesAnoComBarra(registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento4()));

			retorno.setDigitoVerificadorContaModulo10(new Integer(
					registroHelperCodigoG.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento5()).toString());

			retorno.setSequencialFaturaClienteResponsavel(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento6());
			
			eHLegado = true;

		} 
		else if (tipoPagamentoLEGADO != null &&
				tipoPagamentoLEGADO.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_DOC_COBRANCA_IMOVEL_CAER) &&
				tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_DOCUMENTO_COBRANCA.toString())){

			retorno.setTipoPagamento(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_DOC_COBRANCA_IMOVEL_CAER);

			matriculaImovel = new Integer(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento2()).toString();

			// Calcular Digito Verificador da Matricula
			if (matriculaImovel != null) {
				int digitoModulo11 = Util.obterDigitoVerificadorModulo11(""
						+ matriculaImovel);

				matriculaImovel = new Integer(matriculaImovel.toString()
						+ digitoModulo11).toString();
			}

			retorno.setMatriculaImovel(matriculaImovel);

			retorno.setNumeroDocumento(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento5());
			
			eHLegado = true;

		}
		
		if (!eHLegado){
			
			//[UC0270] Apresentar Análise do Movimento dos Arrecadadores
			retorno = super.apresentarDadosConteudoCodigoBarras(registroHelperCodigoG);
		}

		return retorno;

	}



	
	/**
	 * [UC0259] - Processar Pagamneto com Código de Barras
	 * 
	 * Autor: Rafael Pinto
	 * 
	 * Data: 19/04/2007
	 */
	protected Pagamento processarPagamentosCodigoBarrasTipoPagamentoContaLEGADO(
			int anoMes, Integer anoMesPagamento,
			SistemaParametro sistemaParametro, BigDecimal valorPagamento,
			Date dataPagamento, Integer idConta, Integer idLocalidade,
			Integer idFormaPagamento, Integer idImovelNaBase, Imovel imovel)
			throws ControladorException {

		Pagamento pagamento = new Pagamento();
		pagamento.setAnoMesReferenciaPagamento(anoMes);

		// caso o ano mes da data de dedito seja
		// maior que o ano mes de arrecadação da
		// tabela sistema parametro então seta o ano
		// mes da data de debito
		if (anoMesPagamento > sistemaParametro.getAnoMesArrecadacao()) {
			pagamento.setAnoMesReferenciaArrecadacao(anoMesPagamento);
		} else {
			// caso contrario seta o o ano mes
			// arrecadação da tabela sistema
			// parametro
			pagamento.setAnoMesReferenciaArrecadacao(sistemaParametro
					.getAnoMesArrecadacao());
		}

		pagamento.setValorPagamento(valorPagamento);
		pagamento.setDataPagamento(dataPagamento);
		pagamento.setPagamentoSituacaoAtual(null);
		pagamento.setPagamentoSituacaoAnterior(null);
		pagamento.setDebitoTipo(null);
		
		// Verifica se o id da conta é diferente de nulo
		if (idConta != null) {
			
			ContaGeral conta = new ContaGeral();
			conta.setId(idConta);
			pagamento.setContaGeral(conta);
			
			/*
			 * Colocado por Raphael Rossiter em 26/11/2008 - CRC264
			 * OBJ: Inserir o pagamento com a localidade da própria conta e não
			 * com a localidade do documento de cobrança
			 */
			try {
				idLocalidade = repositorioLocalidade
				.pesquisarIdLocalidadePorConta(idConta);

                } catch (ErroRepositorioException e) {
                    throw new ControladorException("erro.sistema", e);
                }
		} 
		else {
				
			pagamento.setContaGeral(null);
				
		}
		
		//Seta a guia de pagamento pra NULL
		pagamento.setGuiaPagamento(null);

		// Verifica se o id da conta é diferente de nulo
		if (idLocalidade != null) {
			Localidade localidade = new Localidade();
			localidade.setId(idLocalidade);
			pagamento.setLocalidade(localidade);
		} 
		else {
			pagamento.setLocalidade(null);
		}
		
		DocumentoTipo documentoTipo = new DocumentoTipo();
		documentoTipo.setId(DocumentoTipo.CONTA);
		pagamento.setDocumentoTipo(documentoTipo);

		// seta o id do aviso bancario
		pagamento.setAvisoBancario(null);

		// seta o imovel
		if (idImovelNaBase != null) {
			pagamento.setImovel(imovel);
		} else {
			pagamento.setImovel(null);
		}

		pagamento.setArrecadadorMovimentoItem(null);

		ArrecadacaoForma arrecadacaoForma = new ArrecadacaoForma();
		arrecadacaoForma.setId(idFormaPagamento);
		pagamento.setArrecadacaoForma(arrecadacaoForma);
		pagamento.setCliente(null);
		pagamento.setUltimaAlteracao(new Date());

		pagamento.setFatura(null);
		pagamento.setCobrancaDocumento(null);
		
		DocumentoTipo documentoAgregador = new DocumentoTipo();
		documentoAgregador.setId(DocumentoTipo.CONTA);
		pagamento.setDocumentoTipoAgregador(documentoAgregador);
		
		pagamento.setDataProcessamento(new Date());

		return pagamento;
	}
	
	/**
	 * [UC0270] Apresentar Análise do Movimento dos Arrecadadores
	 *
	 * @author Rômulo Aurélio
	 * @date 05/03/2009
	 *
	 * @param registroHelperCodigoG
	 * @param arrecadadorMovimentoItemHelper
	 * @throws ControladorException
	 */
	public void distribuirDadosRegistroMovimentoArrecadadorPorTipoPagamento(RegistroHelperCodigoG registroHelperCodigoG,
			ArrecadadorMovimentoItemHelper arrecadadorMovimentoItemHelper) throws ControladorException {
		
		//TIPO PAGAMENTO
		String tipoPagamento = registroHelperCodigoG.getRegistroHelperCodigoBarras().getTipoPagamento();
		
		//CÓDIGO DE BARRAS DISTRIBUIDO
		RegistroHelperCodigoBarrasTipoPagamento codigoBarrasTipoPagamento = registroHelperCodigoG.getRegistroHelperCodigoBarras()
		.getRegistroHelperCodigoBarrasTipoPagamento();
		
		//TIPO PAGAMENTO LEGADO
		String tipoPagamentoLEGADO = codigoBarrasTipoPagamento.getIdPagamento1();
		
		//PARA IDENTIFICAR LEGADO
		boolean eHLegado = false;
		
		if (tipoPagamentoLEGADO != null &&
			tipoPagamentoLEGADO.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA_CAER) &&
			tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA.toString()) &&
			codigoBarrasTipoPagamento.getIdPagamento4().equals("00") &&
			codigoBarrasTipoPagamento.getIdPagamento5().equals("00000000")){

			arrecadadorMovimentoItemHelper
					.setIdentificacao(registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento2());

			arrecadadorMovimentoItemHelper
					.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_CONTA);
			
			eHLegado = true;
		} 
		else if (tipoPagamentoLEGADO != null &&
				tipoPagamentoLEGADO.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_CLIENTE_CAER) &&
				tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_CLIENTE.toString())){

			arrecadadorMovimentoItemHelper
					.setIdentificacao(registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento2());
			arrecadadorMovimentoItemHelper
					.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_GUIA_PAGAMENTO_CLIENTE_CAER);
			
			eHLegado = true;
		}
		else if (tipoPagamentoLEGADO != null &&
				tipoPagamentoLEGADO.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_IMOVEL_CAER) &&
				tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO.toString())){

			arrecadadorMovimentoItemHelper
					.setIdentificacao(registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento2());
			arrecadadorMovimentoItemHelper
					.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_GUIA_PAGAMENTO_IMOVEL_CAER);
			
			eHLegado = true;
		}
		else if (tipoPagamentoLEGADO != null &&
				tipoPagamentoLEGADO.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_DOC_COBRANCA_CLIENTE_RESPONSAVEL_CAER) &&
				tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_DOCUMENTO_COBRANCA_NOVO.toString())){

			arrecadadorMovimentoItemHelper
					.setIdentificacao(registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento2());
			arrecadadorMovimentoItemHelper
					.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_DOC_COBRANCA_CLIENTE_RESPONSAVEL_CAER);
			
			eHLegado = true;
		}
		else if (tipoPagamentoLEGADO != null &&
				tipoPagamentoLEGADO.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_FATURA_CLIENTE_RESPONSAVEL_CAER) &&
				tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_FATURA_CLIENTE_RESPONSAVEL.toString())){

			arrecadadorMovimentoItemHelper
					.setIdentificacao(registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento2());
			arrecadadorMovimentoItemHelper
					.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_FATURA_CLIENTE_RESPONSAVEL_CAER);
			
			eHLegado = true;
		}
		else if (tipoPagamentoLEGADO != null &&
				tipoPagamentoLEGADO.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_DOC_COBRANCA_IMOVEL_CAER) &&
				tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_DOCUMENTO_COBRANCA.toString())){

			arrecadadorMovimentoItemHelper
					.setIdentificacao(registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento2());
			arrecadadorMovimentoItemHelper
					.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_DOC_COBRANCA_IMOVEL_CAER);
			
			eHLegado = true;
		}
		
		if (!eHLegado){
			
			//[UC0270] Apresentar Análise do Movimento dos Arrecadadores
			super.distribuirDadosRegistroMovimentoArrecadadorPorTipoPagamento(registroHelperCodigoG, arrecadadorMovimentoItemHelper);
		}
		
	}
	
	
	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 * 
	 * DOCUMENTO COBRANÇA TIPO 10
	 * 
	 * @author Ivan Sergio
	 * @data 18/10/2010
	 */
	protected PagamentoHelperCodigoBarras processarPagamentosCodigoBarrasDocumentoCobrancaImovelLEGADO(
			RegistroHelperCodigoBarras registroHelperCodigoBarras,
			SistemaParametro sistemaParametro, Date dataPagamento,
			Integer anoMesPagamento, BigDecimal valorPagamento,
			Integer idFormaArrecadacao, Usuario usuarioLogado) throws ControladorException {

		PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = new PagamentoHelperCodigoBarras();
		String descricaoOcorrencia = "OK";
		String indicadorAceitacaoRegistro = "1";
		Collection colecaoPagamentos = new ArrayList();
		Collection colecaoDevolucoes = new ArrayList();
		Collection colecaoDebitosACobrarJurosParcelamento = new ArrayList();

		boolean matriculaImovelInvalida = false;

		Integer idImovelNaBase = null;
		Integer matriculaImovel = null;

		// valida a matricula do imóvel
		matriculaImovelInvalida = Util.validarValorNaoNumerico(registroHelperCodigoBarras
				.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento2());
		
		if (matriculaImovelInvalida) {
			descricaoOcorrencia = "MÁTRICULA DO IMÓVEL INVÁLIDA";
		} else {
			// verifica se existe a matricula do imóvel na base
			matriculaImovel = new Integer(registroHelperCodigoBarras
					.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento2());
			
			// [FS0008] - Calcular Digito Verificador da Matricula
			if (matriculaImovel != null) {
				int digitoModulo11 = Util.obterDigitoVerificadorModulo11("" + matriculaImovel);
				matriculaImovel = new Integer(matriculaImovel.toString() + digitoModulo11);
			}
			
			idImovelNaBase = null;
			
			try {
				idImovelNaBase = repositorioImovel.recuperarMatriculaImovel(new Integer(matriculaImovel));
			} catch (ErroRepositorioException e) {
				throw new ControladorException("erro.sistema", e);
			}

			if (idImovelNaBase == null) {
				descricaoOcorrencia = "MATRÍCULA DO IMÓVEL NÃO CADASTRADA";
			}
		}

		if (descricaoOcorrencia.equals("OK")) {
			// inicializa o id da localidade
			Integer idLocalidade = null;

			// inicializa a coleção de cobranca documento item
			Collection cobrancaDocumentoItens = null;
			// inicializa a coleção de cobranca documento item
			Object[] parmsDocumentoCobranca = null;

			int numeroSequencialDocumento = Integer.parseInt(registroHelperCodigoBarras
					.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento5());

			try {
				cobrancaDocumentoItens = repositorioCobranca.pesquisarCobrancaDocumentoItem(
						idImovelNaBase, numeroSequencialDocumento);
				parmsDocumentoCobranca = repositorioCobranca.pesquisarParmsCobrancaDocumento(
						idImovelNaBase, numeroSequencialDocumento);
			} catch (ErroRepositorioException e) {
				throw new ControladorException("erro.sistema", e);
			}

			// caso exista documento de cobrança
			if (parmsDocumentoCobranca != null) {
				Integer idCobrancaDocumento = null;
				BigDecimal valorAcrescimo = new BigDecimal("0.00");
				BigDecimal valorDesconto = new BigDecimal("0.00");
				Date dataEmissao = null;
				BigDecimal valorTaxa = new BigDecimal("0.00");
				Integer idDocumentoTipo = null;
				
				if (parmsDocumentoCobranca[0] != null) {
					valorAcrescimo = ((BigDecimal) parmsDocumentoCobranca[0]);
				}
				if (parmsDocumentoCobranca[1] != null) {
					valorDesconto = ((BigDecimal) parmsDocumentoCobranca[1]);
				}
				if (parmsDocumentoCobranca[2] != null) {
					dataEmissao = ((Date) parmsDocumentoCobranca[2]);
				}
				if (parmsDocumentoCobranca[3] != null) {
					idCobrancaDocumento = ((Integer) parmsDocumentoCobranca[3]);
				}
				if (parmsDocumentoCobranca[4] != null) {
					valorTaxa = ((BigDecimal) parmsDocumentoCobranca[4]);
				}
				
				/*
                 * Alterado por Raphael Rossiter em 10/01/2008 - Analistas: Eduardo e Aryed
                 * OBJ: Gerar os pagamentos associados com a localidade do document de cobrança e NÃO com
                 * a localidade do imóvel.
                 */
				if (parmsDocumentoCobranca[5] != null) {
					idLocalidade = ((Integer) parmsDocumentoCobranca[5]);
				}
				else{
					try {
                        idLocalidade = repositorioLocalidade.pesquisarIdLocalidade(idImovelNaBase);
                    } catch (ErroRepositorioException e) {
                        throw new ControladorException("erro.sistema", e);
                    }
				}

				if (parmsDocumentoCobranca[6] != null) {
					idDocumentoTipo = ((Integer) parmsDocumentoCobranca[6]);
				}
				
				// caso o valor de acrescimo for maior que zero
				if (valorAcrescimo.compareTo(new BigDecimal("0.00")) == 1) {
					// [SB0008 - Alterar vencimento dos itens do
					// documento de cobrança]
					alterarVencimentoItensDocumentoCobranca(idCobrancaDocumento, dataEmissao);
				}

				// caso o valor de acrescimos seja maior que o valor
				// de
				// descontos
				if (valorAcrescimo.compareTo(valorDesconto) == 1) {
					valorAcrescimo = valorAcrescimo.subtract(valorDesconto);
					valorDesconto = new BigDecimal("0.00");
				} else {
					valorDesconto = valorDesconto.subtract(valorAcrescimo);
					valorAcrescimo = new BigDecimal("0.00");
				}

				// caso o valor de acrescimo for maior que zero
				if (valorAcrescimo.compareTo(new BigDecimal("0.00")) == 1) {
					// [SB0005 - Processar Recebimento de Acrescimos
					// por
					// Impontualidade]

					Pagamento pagamento = processarRecebimentoAcrescimosImpontualidade(
							idCobrancaDocumento, dataPagamento, valorAcrescimo,
							idImovelNaBase, idLocalidade, sistemaParametro,
							idFormaArrecadacao, idDocumentoTipo, null);

					colecaoPagamentos.add(pagamento);

				}

				// caso o valor de desconto for maior que zero
				if (valorDesconto.compareTo(new BigDecimal("0.00")) == 1) {
					// [SB0006 - Processar Desconto concedido no
					// documento de cobrança]
					Devolucao devolucao = processarDescontoConcedidoDocumentoCobranca(
							idCobrancaDocumento, dataPagamento, valorDesconto,
							idImovelNaBase, idLocalidade, sistemaParametro,
							idFormaArrecadacao, idDocumentoTipo);

					colecaoDevolucoes.add(devolucao);

				}

				// caso o valor da taxa referente ao documento de cobrança for
				// maior que zero
				if (valorTaxa.compareTo(new BigDecimal("0.00")) == 1) {
					// [SB0006 - Processar Desconto concedido no
					// documento de cobrança]
					Pagamento pagamento = processarTaxaDocumentoCobranca(
							idCobrancaDocumento, dataPagamento, valorTaxa,
							idImovelNaBase, idLocalidade, sistemaParametro,
							idFormaArrecadacao, idDocumentoTipo);

					colecaoPagamentos.add(pagamento);

				}

				// verifica se a coleção é diferente de nula
				if (cobrancaDocumentoItens != null && !cobrancaDocumentoItens.isEmpty()) {
					Iterator cobrancaDocumentoItensIterator = cobrancaDocumentoItens.iterator();

					while (cobrancaDocumentoItensIterator.hasNext()) {
						Object[] arrayCobrancaDocumentoItem = (Object[]) cobrancaDocumentoItensIterator.next();
						CobrancaDocumentoItem cobrancaDocumentoItem = new CobrancaDocumentoItem();
						
						//VALOR DO ITEM COBRADO
						cobrancaDocumentoItem.setValorItemCobrado((BigDecimal) arrayCobrancaDocumentoItem[3]);
						
						//NUMERO DE PARCELAS ANTECIPADAS
						cobrancaDocumentoItem.setNumeroParcelasAntecipadas((Integer) arrayCobrancaDocumentoItem[18]);
						
						/*
						 * Colocado por Raphael Rossiter em 31/10/2007 OBJ:
						 * Apenas gerar os pagamentos referentes aos itens que
						 * NAO tenham CreditoARealizar
						 */
						if (arrayCobrancaDocumentoItem[13] == null) {
							ContaGeral contaGeral = null;
							Conta conta = null;
							
							//CONTA
							if (arrayCobrancaDocumentoItem[0] != null) {
								conta = new Conta();
								conta.setId((Integer) arrayCobrancaDocumentoItem[0]);
								
								//REFERENCIA DA CONTA
								if (arrayCobrancaDocumentoItem[4] != null) {
									conta.setReferencia((Integer) arrayCobrancaDocumentoItem[4]);
								}
								else{
									conta.setReferencia(0);
								}
								
								contaGeral = new ContaGeral();
								contaGeral.setConta(conta);
								contaGeral.setId(conta.getId());
								
								cobrancaDocumentoItem.setContaGeral(contaGeral);
							} 
							
							//CONTA HISTORICO
							else if (arrayCobrancaDocumentoItem[10] != null){
								conta = new Conta();
								conta.setId((Integer) arrayCobrancaDocumentoItem[10]);
								
								//REFERENCIA DA CONTA
								if (arrayCobrancaDocumentoItem[5] != null) {
									conta.setReferencia((Integer) arrayCobrancaDocumentoItem[5]);
								}
								else{
									conta.setReferencia(0);
								}
								
								contaGeral = new ContaGeral();
								contaGeral.setConta(conta);
								contaGeral.setId(conta.getId());
								
								cobrancaDocumentoItem.setContaGeral(contaGeral);
							}
							
							GuiaPagamentoGeral guiaPagamentoGeral = null;
							GuiaPagamento guiaPagamento = null;
							
							//GUIA DE PAGAMENTO
							if (arrayCobrancaDocumentoItem[1] != null) {
								guiaPagamentoGeral = new GuiaPagamentoGeral();
								guiaPagamento = new GuiaPagamento();
								
								guiaPagamento.setId((Integer) arrayCobrancaDocumentoItem[1]);
								guiaPagamentoGeral.setGuiaPagamento(guiaPagamento);
								guiaPagamentoGeral.setId(guiaPagamento.getId());
								
								cobrancaDocumentoItem.setGuiaPagamentoGeral(guiaPagamentoGeral);
							}
							
							//GUIA DE PAGAMENTO HISTORICO
							else if (arrayCobrancaDocumentoItem[11] != null){
								guiaPagamentoGeral = new GuiaPagamentoGeral();
								guiaPagamento = new GuiaPagamento();
								
								guiaPagamento.setId((Integer) arrayCobrancaDocumentoItem[11]);
								guiaPagamentoGeral.setGuiaPagamento(guiaPagamento);
								guiaPagamentoGeral.setId(guiaPagamento.getId());
								
								cobrancaDocumentoItem.setGuiaPagamentoGeral(guiaPagamentoGeral);
							}
							
							DebitoACobrarGeral debitoACobrarGeral = null;
							DebitoACobrar debitoACobrar = null;
							
							//DEBITO A COBRAR
							if (arrayCobrancaDocumentoItem[2] != null) {
								debitoACobrarGeral = new DebitoACobrarGeral();
								debitoACobrar = new DebitoACobrar();
								
								debitoACobrar.setId((Integer) arrayCobrancaDocumentoItem[2]);
								debitoACobrar.setNumeroPrestacaoDebito((Short) arrayCobrancaDocumentoItem[16]);
								debitoACobrar.setNumeroPrestacaoCobradas((Short) arrayCobrancaDocumentoItem[17]);
								
								debitoACobrarGeral.setDebitoACobrar(debitoACobrar);
								debitoACobrarGeral.setId(debitoACobrar.getId());
								
								cobrancaDocumentoItem.setDebitoACobrarGeral(debitoACobrarGeral);
								
								// [SB0012]- Verifica Pagamento de Débito a Cobrar de Parcelamento
								this.verificaPagamentoDebitoACobrarParcelamento(cobrancaDocumentoItem
										.getDebitoACobrarGeral().getDebitoACobrar().getId(),
										cobrancaDocumentoItem.getNumeroParcelasAntecipadas(), usuarioLogado);
							}
							
							//DEBITO A COBRAR HISTORICO
							else if (arrayCobrancaDocumentoItem[12] != null){
								debitoACobrarGeral = new DebitoACobrarGeral();
								debitoACobrar = new DebitoACobrar();
								
								debitoACobrar.setId((Integer) arrayCobrancaDocumentoItem[12]);
								
								debitoACobrarGeral.setDebitoACobrar(debitoACobrar);
								debitoACobrarGeral.setId(debitoACobrar.getId());
								
								cobrancaDocumentoItem.setDebitoACobrarGeral(debitoACobrarGeral);
							}
							
							//CRÉDITO A REALIZAR (UTILIZADO PARA PAGAMENTO ANTECIPADO)
							
							//IDENTIFICANDO O TIPO DE DEBITO QUE SERA ASSOCIADO AO PAGAMENTO
							Integer idDebitoTipo = null;
							
							if (cobrancaDocumentoItem.getContaGeral() == null) {
								//CASO SEJA PARA GUIA DE PAGAMENTO
								if (cobrancaDocumentoItem.getGuiaPagamentoGeral() != null) {
									//GUIA DE PAGAMENTO
									if (arrayCobrancaDocumentoItem[6] != null) {
										idDebitoTipo = (Integer) arrayCobrancaDocumentoItem[6];
									} 
									//GUIA DE PAGAMENTO HISTORICO
									else if (arrayCobrancaDocumentoItem[7] != null){
										 idDebitoTipo = (Integer) arrayCobrancaDocumentoItem[7];
									}
								}
								
								//CASO SEJA PARA DEBITO A COBRAR
								if (cobrancaDocumentoItem.getDebitoACobrarGeral() != null) {
									//DEBITO A COBRAR
									if (arrayCobrancaDocumentoItem[8] != null) {
										idDebitoTipo = (Integer) arrayCobrancaDocumentoItem[8];
									} 
									//DEBITO A COBRAR HISTORICO
									else if (arrayCobrancaDocumentoItem[9] != null) {
										idDebitoTipo = (Integer) arrayCobrancaDocumentoItem[9];
									}
								}
							}
							
							//[SB0019] – Gerar Débitos/Créditos Parcelas Antecipadas
							DebitoACobrar debitoACobrarAntecipacao = null;
							if (cobrancaDocumentoItem.getNumeroParcelasAntecipadas() != null){
								debitoACobrarAntecipacao = (DebitoACobrar) 
								this.gerarDebitoCreditoParcelasAntecipadas(
										idImovelNaBase, cobrancaDocumentoItem, usuarioLogado);
								
								/*
								* Caso o débito a cobrar com parcelas antecipadas tenha juros de parcelamento (FNTP_ID = “Juros de Parcelamento” da
								* tabela DEBITO_A_COBRAR com PARC_ID = PARC_ID do débito com parcelas antecipadas). O sistema deverá
								* atualizar a quantidade de parcela bônus do débito a cobrar de juros (DBAC_NNPARCELABONUS =
								* DBAC_NNPARCELABONUS + quantidade de parcelas antecipadas e DBAC_TMULTIMAALTERACAO = Data e Hora Correntes)
								*/
								DebitoACobrar debitoACobrarJurosParcelamento = this.pesquisarDebitoACobrarJurosParcelamento(
								debitoACobrarAntecipacao.getParcelamento().getId());

								if (debitoACobrarJurosParcelamento != null){
									if (!colecaoDebitosACobrarJurosParcelamento.contains(debitoACobrarJurosParcelamento)){
										Short numeroParcelaBonus = debitoACobrarAntecipacao.getNumeroPrestacaoDebito();
										if (debitoACobrarJurosParcelamento.getNumeroParcelaBonus() != null){
											numeroParcelaBonus = Short.valueOf(String.valueOf(
													debitoACobrarJurosParcelamento.getNumeroParcelaBonus()
													.shortValue() + debitoACobrarAntecipacao
													.getNumeroPrestacaoDebito()));
										}

										debitoACobrarJurosParcelamento.setNumeroParcelaBonus(numeroParcelaBonus);
										colecaoDebitosACobrarJurosParcelamento.add(debitoACobrarJurosParcelamento);
									}
								}
							}
							
							//GERANDO O PAGAMENTO
							Pagamento pagamento = new Pagamento();
							
							//REFERENCIA DO PAGAMENTO
							if (cobrancaDocumentoItem.getContaGeral() != null &&
								cobrancaDocumentoItem.getContaGeral().getConta().getReferencia() != 0) {
								
								pagamento.setAnoMesReferenciaPagamento(cobrancaDocumentoItem.getContaGeral().getConta().getReferencia());
							} 
							else {
								pagamento.setAnoMesReferenciaPagamento(null);
							}

							/*
							 * Caso o ano mes da data de debito seja maior que o ano mes de arrecadação da
							 * tabela sistema parametro então seta o ano mes da data de debito
							 */
							if (anoMesPagamento > sistemaParametro.getAnoMesArrecadacao()) {
								pagamento.setAnoMesReferenciaArrecadacao(anoMesPagamento);
							} 
							//Caso contrario seta o o ano mes arrecadação da tabela sistema parametro
							else {
								pagamento.setAnoMesReferenciaArrecadacao(sistemaParametro.getAnoMesArrecadacao());
							}
							
							//VALOR DO PAGAMENTO
							pagamento.setValorPagamento(cobrancaDocumentoItem.getValorItemCobrado());
							
							//DATA DO PAGAMENTO
							pagamento.setDataPagamento(dataPagamento);
							
							//SITUAÇÃO ATUAL
							pagamento.setPagamentoSituacaoAtual(null);
							
							//SITUAÇÃO ANTERIOR
							pagamento.setPagamentoSituacaoAnterior(null);
							
							if (idDebitoTipo != null) {
								DebitoTipo debitoTipo = new DebitoTipo();
								debitoTipo.setId(idDebitoTipo);
								pagamento.setDebitoTipo(debitoTipo);
							} else {
								pagamento.setDebitoTipo(null);
							}

							//VERIFICA SE O PAGAMENTO SERÁ RELACIONADO COM UMA CONTA
							if (cobrancaDocumentoItem.getContaGeral() != null) {
								/*
								 * Colocado por Raphael Rossiter em 26/11/2008 - CRC264
								 * OBJ: Inserir o pagamento com a localidade da própria conta e não
								 * com a localidade do documento de cobrança
								 */
								Integer idLocalidadeConta = null;
								
								try {
									idLocalidadeConta = repositorioLocalidade.pesquisarIdLocalidadePorConta(
											cobrancaDocumentoItem.getContaGeral().getConta().getId());
					            } catch (ErroRepositorioException e) {
					            	throw new ControladorException("erro.sistema", e);
					            }
					            
					            if (idLocalidadeConta != null){
					            	pagamento.setContaGeral(cobrancaDocumentoItem.getContaGeral());
					            }
					            else{
					            	try {
										idLocalidadeConta = repositorioLocalidade
											.pesquisarIdLocalidadePorContaHistorico(
													cobrancaDocumentoItem.getContaGeral().getConta().getId());
						            } catch (ErroRepositorioException e) {
						            	throw new ControladorException("erro.sistema", e);
						            }
					            }
					            
					            idLocalidade = idLocalidadeConta;
								 
								DocumentoTipo documentoTipo = new DocumentoTipo();
								documentoTipo.setId(DocumentoTipo.CONTA);
								pagamento.setDocumentoTipo(documentoTipo);
							} 
							else {
								
								pagamento.setContaGeral(null);
							}
							
							//VERIFICA SE O PAGAMENTO SERÁ RELACIONADO COM UMA GUIA DE PAGAMENTO
							if (cobrancaDocumentoItem.getGuiaPagamentoGeral() != null) {
								/*
								 * Colocado por Raphael Rossiter em 26/11/2008 - CRC264
								 * OBJ: Inserir o pagamento com a localidade da própria guia e não
								 * com a localidade do documento de cobrança
								 */
								Integer idLocalidadeGuiaPagamento = null;
								
								try {
									idLocalidadeGuiaPagamento = repositorioLocalidade
										.pesquisarIdLocalidadePorGuiaPagamento(
													cobrancaDocumentoItem.getGuiaPagamentoGeral()
													.getGuiaPagamento().getId());

					            } catch (ErroRepositorioException e) {
					            	throw new ControladorException("erro.sistema", e);
					            }
					            
					            if (idLocalidadeGuiaPagamento != null){
					            	pagamento.setGuiaPagamento(cobrancaDocumentoItem.getGuiaPagamentoGeral().getGuiaPagamento());
					            }
					            else{
					            	try {
					            		idLocalidadeGuiaPagamento = repositorioLocalidade
					            			.pesquisarIdLocalidadePorGuiaPagamentoHistorico(
					            					cobrancaDocumentoItem.getGuiaPagamentoGeral()
					            					.getGuiaPagamento().getId());

						            } catch (ErroRepositorioException e) {
						            	throw new ControladorException("erro.sistema", e);
						            }
					            }
					            
					            idLocalidade = idLocalidadeGuiaPagamento;
								
								DocumentoTipo documentoTipo = new DocumentoTipo();
								
								/*
								 * verificar se o tipo de debito eh 'entrada de parcelamento', e preencher o documentotipo
								 * com o 'entrada de parcelamento'
								 */
								
								// Alterado por Rômulo Aurélio, Analista Rosana/Aryed
								// quando o tipo de debito for Entrada de Guia é pra inserir 
								// o tipo de documento como guia de Parcelamento
								documentoTipo.setId(DocumentoTipo.GUIA_PAGAMENTO);	
								documentoTipo.setDescricaoDocumentoTipo(ConstantesSistema.TIPO_PAGAMENTO_DOCUMENTO_COBRANCA);
								pagamento.setDocumentoTipo(documentoTipo);
							} 
							else {
								pagamento.setGuiaPagamento(null);
							}

							//VERIFICA SE O PAGAMENTO SERÁ RELACIONADO COM UM DEBITO A COBRAR
							if (cobrancaDocumentoItem.getDebitoACobrarGeral() != null) {
								try {
									if (debitoACobrarAntecipacao != null){
										debitoACobrarGeral.setDebitoACobrar(debitoACobrarAntecipacao);
										debitoACobrarGeral.setId(debitoACobrarAntecipacao.getId());
										
										pagamento.setDebitoACobrarGeral(debitoACobrarGeral);
										
										/*
										 * Colocado por Raphael Rossiter em 26/11/2008 - CRC264
										 * OBJ: Inserir o pagamento com a localidade do próprio debito a cobrar e não
										 * com a localidade do documento de cobrança
										 */
										idLocalidade = repositorioLocalidade
											.pesquisarIdLocalidadePorDebitoACobrar(
													debitoACobrarGeral.getDebitoACobrar().getId());
									}
									else if (cobrancaDocumentoItem.getDebitoACobrarGeral().getDebitoACobrar().getNumeroPrestacaoCobradas() !=
										cobrancaDocumentoItem.getDebitoACobrarGeral().getDebitoACobrar().getNumeroPrestacaoDebito()) {
											
										/*
										 * Colocado por Raphael Rossiter em 26/11/2008 - CRC264
										 * OBJ: Inserir o pagamento com a localidade do próprio debito a cobrar e não
										 * com a localidade do documento de cobrança
										 */
										Integer idLocalidadeDebitoACobrar = null;
										
										try {
											idLocalidadeDebitoACobrar = repositorioLocalidade
												.pesquisarIdLocalidadePorDebitoACobrar(
														cobrancaDocumentoItem.getDebitoACobrarGeral()
														.getDebitoACobrar().getId());

							            } catch (ErroRepositorioException e) {
							            	throw new ControladorException("erro.sistema", e);
							            }
							            
							            if (idLocalidadeDebitoACobrar != null){
							            	pagamento.setDebitoACobrarGeral(cobrancaDocumentoItem.getDebitoACobrarGeral());
							            }
							            else{
							            	try {
							            		idLocalidadeDebitoACobrar = repositorioLocalidade
							            			.pesquisarIdLocalidadePorDebitoACobrarHistorico(
							            					cobrancaDocumentoItem.getDebitoACobrarGeral()
							            					.getDebitoACobrar().getId());

								            } catch (ErroRepositorioException e) {
								            	throw new ControladorException("erro.sistema", e);
								            }
							            }
							            
							            idLocalidade = idLocalidadeDebitoACobrar;
									}
										
								} catch (ErroRepositorioException e) {
									throw new ControladorException("erro.sistema", e);
								}

								DocumentoTipo documentoTipo = new DocumentoTipo();
								documentoTipo.setId(DocumentoTipo.DEBITO_A_COBRAR);
								pagamento.setDocumentoTipo(documentoTipo);
							} 
							else {
								pagamento.setDebitoACobrarGeral(null);
							}

							//LOCALIDADE
							if (idLocalidade != null) {
								Localidade localidade = new Localidade();
								localidade.setId(idLocalidade);
								pagamento.setLocalidade(localidade);
							} else {
								pagamento.setLocalidade(null);
							}

							//AVISO BANCARIO
							pagamento.setAvisoBancario(null);

							//IMOVEL
							if (idImovelNaBase != null) {
								Imovel imovel = new Imovel();
								imovel.setId(idImovelNaBase);
								pagamento.setImovel(imovel);
							} else {
								pagamento.setImovel(null);
							}

							pagamento.setArrecadadorMovimentoItem(null);

							ArrecadacaoForma arrecadacaoForma = new ArrecadacaoForma();
							arrecadacaoForma.setId(idFormaArrecadacao);
							pagamento.setArrecadacaoForma(arrecadacaoForma);
							pagamento.setCliente(null);
							pagamento.setUltimaAlteracao(new Date());
							
							pagamento.setFatura(null);
							
							CobrancaDocumento cobrancaDocumento = new CobrancaDocumento();
							cobrancaDocumento.setId(idCobrancaDocumento);
							pagamento.setCobrancaDocumento(cobrancaDocumento);							
							
							// documento tipo do documento de cobranca
							if(idDocumentoTipo != null){
								DocumentoTipo documentoAgregador = new DocumentoTipo();
								documentoAgregador.setId(idDocumentoTipo);
								pagamento.setDocumentoTipoAgregador(documentoAgregador);
							}
							
							pagamento.setDataProcessamento(new Date());
							
							if (pagamento.getDocumentoTipo() != null) {
								colecaoPagamentos.add(pagamento);
							}
						} 
						else {

							CreditoARealizarGeral creditoARealizarGeral = new CreditoARealizarGeral();
							creditoARealizarGeral.setId((Integer) arrayCobrancaDocumentoItem[13]);
							
							cobrancaDocumentoItem.setCreditoARealizarGeral(creditoARealizarGeral);
							
							//[SB0019] – Gerar Débitos/Créditos Parcelas Antecipadas
							CreditoARealizar creditoARealizarAntecipacao = null;
							if (cobrancaDocumentoItem.getNumeroParcelasAntecipadas() != null){
								
								creditoARealizarAntecipacao = (CreditoARealizar) this.gerarDebitoCreditoParcelasAntecipadas(idImovelNaBase, 
								cobrancaDocumentoItem, usuarioLogado);
								
								creditoARealizarGeral.setId(creditoARealizarAntecipacao.getId());
								creditoARealizarGeral.setCreditoARealizar(creditoARealizarAntecipacao);
							}
							
							// Para os itens que tenham CreditoARealizar gerar
							// suas respectivas devoluções

							Devolucao devolucao = new Devolucao();

							// DataDevolucao = DataPagamento
							devolucao.setDataDevolucao(dataPagamento);

							/*
							 * AnoMesReferenciaDevolucao Caso o anoMes da data
							 * de devolução seja MAIOR que a
							 * PARM_AMREFERENCIAARRECADACAO da tabela
							 * SISTEMA_PARAMETROS atribuir o anoMes da data da
							 * devolução, caso contrário atribuir o
							 * PARM_AMREFERENCIAARRECADACAO.
							 */
							Integer anoMesDataDevolucao = Util.getAnoMesComoInteger(devolucao.getDataDevolucao());

							if (anoMesDataDevolucao > sistemaParametro.getAnoMesArrecadacao()) {
								
								devolucao.setAnoMesReferenciaArrecadacao(anoMesDataDevolucao);
							} 
							else {
								
								devolucao.setAnoMesReferenciaArrecadacao(sistemaParametro.getAnoMesArrecadacao());
							}

							// ValorDevolucao = ValorItemCobrado
							devolucao.setValorDevolucao(cobrancaDocumentoItem.getValorItemCobrado());

							// Localidade = Localidade da tabela
							// COBRANCA_DOCUMENTO
							if (arrayCobrancaDocumentoItem[14] != null) {
								Localidade localidade = new Localidade();
								localidade
										.setId((Integer) arrayCobrancaDocumentoItem[14]);
								devolucao.setLocalidade(localidade);
							}

							// Imovel = Imovel da tabela COBRANCA_DOCUMENTO
							if (arrayCobrancaDocumentoItem[15] != null) {
								Imovel imovel = new Imovel();
								imovel
										.setId((Integer) arrayCobrancaDocumentoItem[15]);
								devolucao.setImovel(imovel);
							}

							// DebitoTipo = DebitoTipo com o valor
							// correspondente a outros
							DebitoTipo debitoTipo = new DebitoTipo();
							debitoTipo.setId(DebitoTipo.OUTROS);
							devolucao.setDebitoTipo(debitoTipo);

							// CreditoARealizarGeral = CreditoARealizarGeral da
							// tabela COBRANCA_DOCUMENTO_ITEM
							devolucao.setCreditoARealizarGeral(creditoARealizarGeral);

							// Ultima Alteração
							devolucao.setUltimaAlteracao(new Date());

							CobrancaDocumento cobrancaDocumento = new CobrancaDocumento();
							cobrancaDocumento.setId(idCobrancaDocumento);
							devolucao.setCobrancaDocumento(cobrancaDocumento);							
							
							// documento tipo do documento de cobranca
							if(idDocumentoTipo != null){
								DocumentoTipo documentoAgregador = new DocumentoTipo();
								documentoAgregador.setId(idDocumentoTipo);
								devolucao.setDocumentoTipoAgregador(documentoAgregador);
							}
							
							// ADICIONANDO A DEVOLUCAO GERADA NA COLECAO DE
							// RETORNO
							colecaoDevolucoes.add(devolucao);
						}
					}
					
					/*
					 * Caso o débito a cobrar com parcelas antecipadas tenha juros de parcelamento (FNTP_ID = “Juros de Parcelamento” da 
					 * tabela DEBITO_A_COBRAR com PARC_ID = PARC_ID do débito com parcelas antecipadas). O sistema deverá 
					 * atualizar a quantidade de parcela bônus do débito a cobrar de juros (DBAC_NNPARCELABONUS = 
					 * DBAC_NNPARCELABONUS + quantidade de parcelas antecipadas e DBAC_TMULTIMAALTERACAO = Data e Hora Correntes)
					 */
					if (colecaoDebitosACobrarJurosParcelamento != null &&
						!colecaoDebitosACobrarJurosParcelamento.isEmpty()){
						
						Iterator itDebitosACobrarJurosParcelamento = colecaoDebitosACobrarJurosParcelamento.iterator();
						
						while(itDebitosACobrarJurosParcelamento.hasNext()){
							
							this.atualizarNumeroParcelasBonus((DebitoACobrar) itDebitosACobrarJurosParcelamento.next());
						}
					}
				}

			} else {
				descricaoOcorrencia = "DOCUMENTO DE COBRANÇA INEXISTENTE";
				indicadorAceitacaoRegistro = "2";
			}
		} else {
			// atribui o valor 2(NÃO) ao indicador aceitacao
			// registro
			indicadorAceitacaoRegistro = "2";
		}

		// Seta os parametros que serão retornados
		pagamentoHelperCodigoBarras.setColecaoPagamentos(colecaoPagamentos);
		pagamentoHelperCodigoBarras.setColecaoDevolucao(colecaoDevolucoes);
		pagamentoHelperCodigoBarras.setDescricaoOcorrencia(descricaoOcorrencia);
		pagamentoHelperCodigoBarras
				.setIndicadorAceitacaoRegistro(indicadorAceitacaoRegistro);

		return pagamentoHelperCodigoBarras;
	}
}
