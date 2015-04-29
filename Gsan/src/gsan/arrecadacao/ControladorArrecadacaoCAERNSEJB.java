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
package gsan.arrecadacao;

import gsan.arrecadacao.bean.ArrecadadorMovimentoItemHelper;
import gsan.arrecadacao.bean.DadosConteudoCodigoBarrasHelper;
import gsan.arrecadacao.bean.RegistroHelperCodigoBarrasTipoPagamento;
import gsan.arrecadacao.bean.RegistroHelperCodigoG;
import gsan.cadastro.cliente.ClienteImovel;
import gsan.util.ConstantesSistema;
import gsan.util.ControladorException;
import gsan.util.Util;

import java.util.Collection;

import javax.ejb.SessionBean;


/**
 * Controlador Faturamento CAERN
 *
 * @author Raphael Rossiter
 * @date 30/04/2007
 */
public class ControladorArrecadacaoCAERNSEJB extends ControladorArrecadacao implements SessionBean {

	private static final long serialVersionUID = 1L;

	//==============================================================================================================
	// M�TODOS EXCLUSIVOS DA CAERN
	// ==============================================================================================================
	
	/**
	 * retorna o objeto distribuido de acordo comj o tipo de pagamento [UC0264] -
	 * Distribuir Dados do C�digo de Barras [SF0001] - Distribuir Pagamento de
	 * Conta [SF0002] - Distribuir Pagamento de Guia de Pagamento [SF0003] -
	 * Distribuir Pagamento de Documento de Cobram�a [SF0004] - Distribuir
	 * Pagamento de Fatura do Cliente Respons�vel Autor: S�vio Luiz Data:
	 * 15/02/2006
	 * @throws ControladorException 
	 */

	public RegistroHelperCodigoBarrasTipoPagamento distribuirDadosCodigoBarrasPorTipoPagamento(
			String idPagamento, String tipoPagamento, String idEmpresa) throws ControladorException {

		RegistroHelperCodigoBarrasTipoPagamento registroHelperCodigoBarrasTipoPagamento = new RegistroHelperCodigoBarrasTipoPagamento();
		
		registroHelperCodigoBarrasTipoPagamento = 
			super.distribuirDadosCodigoBarrasPorTipoPagamento(idPagamento, tipoPagamento, idEmpresa);
		
		
		int tipoPagamentoParaComparacao = Integer.parseInt(tipoPagamento.trim());

		switch (tipoPagamentoParaComparacao) {

		// LEGADO - CAERN
		// ===============================================================================================================================
		case 0:

			// seta a matr�cula do im�vel
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento1(idPagamento
					.substring(0, 6).trim());
			// seta a referencia da data
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento2(idPagamento
					.substring(6, 9).trim());
			// seta o digito identificador da fatura
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento3(idPagamento
					.substring(9, 10).trim());
			// seta o digito identificador da fatura
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento4(idPagamento
					.substring(10, 11).trim());
			// nao utilizado
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento5(idPagamento
					.substring(11, 24).trim());

			break;
			
		}	
		// ===============================================================================================================================
	
		return registroHelperCodigoBarrasTipoPagamento;
	}
		
	
	/**
	 * [UC0270] Apresentar An�lise do Movimento dos Arrecadadores
	 *
	 * @author Raphael Rossiter
	 * @date 08/11/2008
	 *
	 * @param registroHelperCodigoG
	 * @param arrecadadorMovimentoItemHelper
	 * @throws ControladorException
	 */
	
	
	public void distribuirDadosRegistroMovimentoArrecadadorPorTipoPagamento(RegistroHelperCodigoG registroHelperCodigoG,
			ArrecadadorMovimentoItemHelper arrecadadorMovimentoItemHelper) throws ControladorException {
		
		/*
		 * Caso o tipo de pagamento corresponda a: Conta (valor =
		 * 3), Guia de Pagamento (valor = 4) ou Documento de
		 * Cobran�a (valor = 5), exibir a matr�cula do im�vel
		 * retornada pelo [UC0264]
		 */
		
		// Alterado por: Yara T. Souza
		// Solicitado por : Adriano Brito
		// Data : 13/11/2008
		// Coloquei esse m�todo com essa verifica��o apenas no ControladorArrecadacaoCAERN ,
        // no ControladorArrecadacao
		// Essa condi��o est� comentada.
		
		if (registroHelperCodigoG
				.getRegistroHelperCodigoBarras()
				.getTipoPagamento()
				.equals(
						String
								.valueOf(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA_LEGADO_CAERN))) {

			Integer matriculaImovel = new Integer(
					registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento1());

			// Calcular Digito Verificador da Matricula
			if (matriculaImovel != null) {

				int digitoModulo11 = Util
						.obterDigitoVerificadorModuloCAERN(""
								+ matriculaImovel);

				matriculaImovel = new Integer(matriculaImovel
						.toString()
						+ digitoModulo11);

			}

			arrecadadorMovimentoItemHelper
					.setIdentificacao(matriculaImovel.toString());

			arrecadadorMovimentoItemHelper
					.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_CONTA);

		} else if (registroHelperCodigoG
				.getRegistroHelperCodigoBarras()
				.getTipoPagamento()
				.equals(
						String
								.valueOf(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA))) {

			arrecadadorMovimentoItemHelper
					.setIdentificacao(registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento2());

			arrecadadorMovimentoItemHelper
					.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_CONTA);

		} else if (registroHelperCodigoG
				.getRegistroHelperCodigoBarras()
				.getTipoPagamento()
				.equals(
						String
								.valueOf(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO))) {

			arrecadadorMovimentoItemHelper
					.setIdentificacao(registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento2());
			arrecadadorMovimentoItemHelper
					.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_GUIA_PAGAMENTO);
		} else if (registroHelperCodigoG
				.getRegistroHelperCodigoBarras()
				.getTipoPagamento()
				.equals(
						String
								.valueOf(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_DOCUMENTO_COBRANCA))) {

			arrecadadorMovimentoItemHelper
					.setIdentificacao(registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento2());
			arrecadadorMovimentoItemHelper
					.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_DOCUMENTO_COBRANCA);
		} else if (registroHelperCodigoG
				.getRegistroHelperCodigoBarras()
				.getTipoPagamento()
				.equals(
						String
								.valueOf(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_FATURA_CLIENTE_RESPONSAVEL))) {

			arrecadadorMovimentoItemHelper
					.setIdentificacao(registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento2());
			arrecadadorMovimentoItemHelper
					.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_FATURA_CLIENTE_RESPONSAVEL);
		}
	}

	/**
	 * [UC0270] Apresentar An�lise do Movimento dos Arrecadadores
	 * 
	 * O sistema captura os dados referentes ao conte�do do do c�digo de barras
	 * 
	 * [SF0003] Apresentar Dados do Conte�do do C�digo de Barras
	 * 
	 * @author Raphael Rossiter,Vivianne Sousa
	 * @data 22/03/2006,27/11/2008
	 * 
	 * @param registroHelperCodigoG
	 * @return DadosConteudoCodigoBarrasHelper
	 */
	public DadosConteudoCodigoBarrasHelper apresentarDadosConteudoCodigoBarras(
			RegistroHelperCodigoG registroHelperCodigoG)
			throws ControladorException {

		DadosConteudoCodigoBarrasHelper retorno = new DadosConteudoCodigoBarrasHelper();

		retorno.setIdentificacaoProduto(registroHelperCodigoG
				.getRegistroHelperCodigoBarras().getIdProduto());
		retorno.setIdentificacaoSegmento(registroHelperCodigoG
				.getRegistroHelperCodigoBarras().getIdSegmento());
		retorno.setIdentificacaoValorRealOUReferencia(registroHelperCodigoG
				.getRegistroHelperCodigoBarras().getIdValorReal());
		retorno.setDigitoVerificadorGeral(registroHelperCodigoG
				.getRegistroHelperCodigoBarras().getDigitoVerificadorGeral());
		retorno
				.setValorPagamento(Util
						.formatarMoedaReal(Util
								.formatarMoedaRealparaBigDecimalComUltimos2CamposDecimais(registroHelperCodigoG
										.getRegistroHelperCodigoBarras()
										.getValorPagamento())));

		String tipoPagamento = registroHelperCodigoG
				.getRegistroHelperCodigoBarras().getTipoPagamento();

		// Alterado por: Vivianne Sousa
		// Solicitado por : Adriano Brito
		// Data : 27/11/2008
		// Coloquei esse m�todo com essa verifica��o apenas no ControladorArrecadacaoCAERN ,
        // no ControladorArrecadacao
		// Essa condi��o est� comentada.
		
		if (tipoPagamento != null
				&& tipoPagamento
						.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA_LEGADO_CAERN
								.toString())) {

			retorno.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_CONTA);

			// Verifica se existe a matricula do im�vel na base
			Integer matriculaImovel = new Integer(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento1());

			// Calcular Digito Verificador da Matricula
			if (matriculaImovel != null) {

				int digitoModulo11 = Util.obterDigitoVerificadorModuloCAERN(""
						+ matriculaImovel);

				matriculaImovel = new Integer(matriculaImovel.toString()
						+ digitoModulo11);

				retorno.setMatriculaImovel(matriculaImovel.toString());

				Collection colecaoClienteImovel = this.getControladorImovel()
						.pesquisarImovel(matriculaImovel.toString(), null,
								null, null, null, null, null, null, null, null,
								null, null, null, false, false, 0);

				ClienteImovel clienteImovel = (ClienteImovel) Util
						.retonarObjetoDeColecao(colecaoClienteImovel);

				if (clienteImovel != null && clienteImovel.getImovel() != null
						&& !clienteImovel.getImovel().equals("")) {

					retorno.setCodigoLocalidade(clienteImovel.getImovel()
							.getLocalidade().getId().toString());
				}

			}

			int anoMesReferenciaConta = this.obterMesAnoReferencia(Integer
					.parseInt(registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento2()));

			retorno.setMesAnoReferenciaConta(Util
					.formatarAnoMesParaMesAno(anoMesReferenciaConta));

			retorno.setDigitoVerificadorContaModulo10(new Long(
					registroHelperCodigoG.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento5()).toString());

		} else if (tipoPagamento != null
				&& tipoPagamento
						.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA
								.toString())) {

			retorno.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_CONTA);

			retorno.setCodigoLocalidade(new Integer(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento1()).toString());

			retorno.setMatriculaImovel(new Integer(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento2()).toString());

			retorno
					.setMesAnoReferenciaConta(Util
							.formatarMesAnoSemBarraParaMesAnoComBarra(registroHelperCodigoG
									.getRegistroHelperCodigoBarras()
									.getRegistroHelperCodigoBarrasTipoPagamento()
									.getIdPagamento4()));

			retorno.setDigitoVerificadorContaModulo10(new Integer(
					registroHelperCodigoG.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento5()).toString());

		} else if (tipoPagamento != null
				&& tipoPagamento
						.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO
								.toString())) {

			retorno
					.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_GUIA_PAGAMENTO);

			retorno.setCodigoLocalidade(new Integer(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento1()).toString());
			retorno.setMatriculaImovel(new Integer(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento2()).toString());
			retorno.setCodigoTipoDebito(new Integer(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento4()).toString());
			retorno.setAnoEmissaoGuiaPagamento(new Integer(
					registroHelperCodigoG.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento5()).toString());

		} else if (tipoPagamento != null
				&& tipoPagamento
						.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_DOCUMENTO_COBRANCA
								.toString())) {

			retorno
					.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_DOCUMENTO_COBRANCA);

			retorno.setMatriculaImovel(new Integer(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento2()).toString());
			retorno.setSequencialDocumentoCobranca(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento3());
			retorno.setCodigoTipoDocumento(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento4());

		} else if (tipoPagamento != null
				&& tipoPagamento
						.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_FATURA_CLIENTE_RESPONSAVEL
								.toString())) {

			retorno
					.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_FATURA_CLIENTE_RESPONSAVEL);

			retorno.setCodigoCliente(new Integer(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento2()).toString());
			retorno.setMesAnoReferenciaConta(Util
					.formatarAnoMesParaMesAno(new Integer(registroHelperCodigoG
							.getRegistroHelperCodigoBarras()
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento2()).intValue()));
			retorno.setDigitoVerificadorContaModulo10(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento5());
			retorno.setSequencialFaturaClienteResponsavel(registroHelperCodigoG
					.getRegistroHelperCodigoBarras()
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento6());

		}

		return retorno;

	}

	
}
