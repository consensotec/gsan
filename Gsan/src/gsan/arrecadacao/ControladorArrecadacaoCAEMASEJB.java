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
import gsan.arrecadacao.bean.PagamentoHelperCodigoBarras;
import gsan.arrecadacao.bean.RegistroHelperCodigoBarras;
import gsan.arrecadacao.bean.RegistroHelperCodigoBarrasTipoPagamento;
import gsan.arrecadacao.bean.RegistroHelperCodigoG;
import gsan.arrecadacao.pagamento.GuiaPagamento;
import gsan.arrecadacao.pagamento.Pagamento;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cobranca.CobrancaDocumento;
import gsan.cobranca.DocumentoTipo;
import gsan.cobranca.parcelamento.FiltroParcelamentoItem;
import gsan.cobranca.parcelamento.Parcelamento;
import gsan.cobranca.parcelamento.ParcelamentoItem;
import gsan.faturamento.conta.Conta;
import gsan.faturamento.conta.ContaGeral;
import gsan.faturamento.conta.ContaMotivoRevisao;
import gsan.faturamento.conta.FiltroConta;
import gsan.faturamento.credito.CreditoARealizarGeral;
import gsan.faturamento.debito.DebitoACobrarGeral;
import gsan.faturamento.debito.DebitoTipo;
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
 * Controlador Faturamento CAEMA
 *
 * @author S�vio Luiz
 * @date 28/04/2008
 */
public class ControladorArrecadacaoCAEMASEJB extends ControladorArrecadacao implements SessionBean {
	
	private static final long serialVersionUID = 1L;
	
	//==============================================================================================================
	// M�TODOS EXCLUSIVOS DA CAEMA
	//==============================================================================================================
	/**
	 * [UC0264] - Distribuir Dados do C�digo de Barras 
	 *
	 * @author S�vio Luiz, Rafael Corr�a, Raphael Rossiter
	 * @date 15/02/2006, 12/05/2008, 18/11/2008
	 *
	 * @param codigoBarras
	 * @return RegistroHelperCodigoBarras
	 * @throws ControladorException 
	 */
	public RegistroHelperCodigoBarras distribuirDadosCodigoBarras(
			String codigoBarras) throws ControladorException {

		// instancia o objeto de c�digo de barras, setando os valores que s�o iguais para todas as empresas
		RegistroHelperCodigoBarras registroHelperCodigoBarras = distribuirDadosCodigoBarrasGeral(codigoBarras);
		
		// recupera o id pagamento da string
		String idPagamento = codigoBarras.substring(19, 44);
		
		//TIPO PAGAMENTO GSAN
		String tipoPagamento = idPagamento.substring(24, 25).trim();
		
		//TIPO PAGAMENTO LEGADO
		String tipoPagamentoLegado = idPagamento.substring(21, 23).trim();
		
		String tipoPagamentoLegadoConta = idPagamento.substring(22, 23).trim();
		
		//PARA IDENTIFICAR LEGADO
		boolean eHLegado = false;
		
		String dataVencimentoParaVerificar = idPagamento.substring(0, 8);
		Integer anoVencimentoParaVerificar = Integer.parseInt(dataVencimentoParaVerificar.substring(4, 8));
		
		Integer anoReferenciaParaVerificar = Integer.parseInt(idPagamento.substring(8, 12));
		
		if (tipoPagamento.equals("0")){
			eHLegado = true;
		}
		else if (!tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA.toString()) &&
				!tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO.toString()) &&
				!tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_DOCUMENTO_COBRANCA.toString()) &&
				!tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_CLIENTE.toString()) &&
				!tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_FATURA_CLIENTE_RESPONSAVEL.toString()) &&
				!tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_DOCUMENTO_COBRANCA_NOVO.toString()) &&
				!tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_COM_IDENTIFICACAO_MATRICULA.toString()) &&
				!tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_COM_IDENTIFICACAO_CLIENTE.toString())){
				
			eHLegado = true;
		}
			
		else if (!Util.validarDiaMesAnoSemBarra(dataVencimentoParaVerificar) && 
				anoVencimentoParaVerificar > 2000 && anoReferenciaParaVerificar < 2009 &&
				anoReferenciaParaVerificar <= anoVencimentoParaVerificar &&
				(tipoPagamentoLegado.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_FATURA_LEGADO_CAEMA) ||
						tipoPagamentoLegadoConta.equals(""+(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA_LEGADO_CAEMA_INTEGER)))){
			
			eHLegado = true;
		}
		
		//IDENTIFICAR TIPO DE PAGAMENTO CAMPANHA - CONTINUA��O LEGADO
		if (!eHLegado){
			
			String mesAnoFaturaParaVerificar = idPagamento.substring(11, 17);
			mesAnoFaturaParaVerificar = Util.formatarMesAnoSemBarraParaMesAnoComBarra(mesAnoFaturaParaVerificar);
			
			if (tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_FATURA_CLIENTE_RESPONSAVEL.toString()) &&
				(tipoPagamentoLegado.substring(1, 2)).equals(
				ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CAMPANHA_AVISO_DEBITO_LEGADO_CAEMA.toString()) &&
				!Util.validarMesAno(mesAnoFaturaParaVerificar)){
				
				eHLegado = true;
			}
			
		}
		
		if (eHLegado){
		
			
			
			if ((tipoPagamentoLegado.substring(1, 2)).equals(
				ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA_LEGADO_CAEMA_INTEGER.toString())){
				 
				tipoPagamento = ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA_LEGADO_CAEMA;
			}
			
			else if ((tipoPagamentoLegado.substring(1, 2)).equals(
				ConstantesSistema.CODIGO_TIPO_PAGAMENTO_FATURA_LEGADO_CAEMA_INTEGER.toString())){
					 
				tipoPagamento = ConstantesSistema.CODIGO_TIPO_PAGAMENTO_FATURA_LEGADO_CAEMA;
			}
			
			else if ((tipoPagamentoLegado.substring(1, 2)).equals(
				ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_LEGADO_CAEMA_INTEGER.toString())){
						 
				tipoPagamento = ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_LEGADO_CAEMA;
			}
			
			else{
				
				tipoPagamento = ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CAMPANHA_AVISO_DEBITO_LEGADO_CAEMA;
			}
		}
		
		
		registroHelperCodigoBarras.setTipoPagamento(tipoPagamento);
		
		RegistroHelperCodigoBarrasTipoPagamento registroHelperCodigoBarrasTipoPagamento = distribuirDadosCodigoBarrasPorTipoPagamento(
		idPagamento, tipoPagamento, registroHelperCodigoBarras.getIdEmpresa());

		registroHelperCodigoBarras
		.setRegistroHelperCodigoBarrasTipoPagamento(registroHelperCodigoBarrasTipoPagamento);

		return registroHelperCodigoBarras;
	}

	
	/**
	 * [UC0264] - Distribuir Dados do C�digo de Barras - LEGADO
	 *
	 * @author Raphael Rossiter
	 * @date 19/11/2008
	 *
	 * @param idPagamento
	 * @param tipoPagamento
	 * @param idEmpresa
	 * @return RegistroHelperCodigoBarrasTipoPagamento
	 * @throws ControladorException 
	 */
	public RegistroHelperCodigoBarrasTipoPagamento distribuirDadosCodigoBarrasPorTipoPagamento(
			String idPagamento, String tipoPagamento, String idEmpresa) throws ControladorException {

		RegistroHelperCodigoBarrasTipoPagamento registroHelperCodigoBarrasTipoPagamento = new RegistroHelperCodigoBarrasTipoPagamento();

		registroHelperCodigoBarrasTipoPagamento = 
			super.distribuirDadosCodigoBarrasPorTipoPagamento(idPagamento, tipoPagamento, idEmpresa);
		
		//LEGADO - CAEMA
		//===============================================================================================================================
		if (tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_NEGOCIACAO_A_VISTA_LEGADO_CAEMA.toString()) ||
			tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_LEGADO_CAEMA.toString())){
			
			//Ano de Emiss�o da Guia
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento1(idPagamento
					.substring(0, 4).trim());
			
			// N�mero da Guia
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento2(idPagamento
					.substring(4, 12).trim());
			
			// Matr�cula do Im�vel sem o d�gito verificador
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento3(idPagamento
					.substring(12, 21).trim());
			
			// Tipo de Documento
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento4(idPagamento
					.substring(21, 23).trim());
			
			// N�o Utilizado
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento5(idPagamento
					.substring(23, 25).trim());
		}
		
		else if (tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_FATURA_LEGADO_CAEMA.toString())){
			
			//Vencimento da Fatura
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento1(idPagamento
					.substring(0, 8).trim());
			
			// Quantidade de D�bitos
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento2(idPagamento
					.substring(8, 12).trim());
			
			// C�digo Or�ament�rio passou a ser o id do cliente - Raphael Rossiter e S�vio em 11/09/2008
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento3(idPagamento
					.substring(12, 21).trim());
			
			// Tipo de Documento
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento4(idPagamento
					.substring(21, 23).trim());
			
			// M�s da Fatura
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento5(idPagamento
					.substring(23, 25).trim());
		}
		
		else if (tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA_LEGADO_CAEMA.toString())){
			
			//Vencimento da Fatura
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento1(idPagamento
					.substring(0, 8).trim());
			
			// Ano da Fatura
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento2(idPagamento
					.substring(8, 12).trim());
			
			// Matr�cula do Im�vel sem o d�gito verificador
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento3(idPagamento
					.substring(12, 21).trim());
			
			// Tipo da Fatura
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento4(idPagamento
					.substring(21, 22).trim());
			
			// Tipo de Documento
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento5(idPagamento
					.substring(22, 23).trim());
			
			// M�s da Fatura
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento6(idPagamento
					.substring(23, 25).trim());
		}
		
		else if (tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CAMPANHA_AVISO_DEBITO_LEGADO_CAEMA.toString())){
			
			//Aviso de D�bitos
			if (idPagamento.substring(23, 25).trim().equals("00")) {
				
				// Vencimento da Fatura
				registroHelperCodigoBarrasTipoPagamento.setIdPagamento1(idPagamento
						.substring(0, 8).trim());
				
				// Ano da Fatura
				registroHelperCodigoBarrasTipoPagamento.setIdPagamento2(idPagamento
						.substring(8, 12).trim());
				
				// Matr�cula do Im�vel sem o d�gito verificador
				registroHelperCodigoBarrasTipoPagamento.setIdPagamento3(idPagamento
						.substring(12, 21).trim());
				
				// Tipo de Documento
				registroHelperCodigoBarrasTipoPagamento.setIdPagamento4(idPagamento
						.substring(21, 23).trim());
				
				// N�o Utilizado (Zeros)
				registroHelperCodigoBarrasTipoPagamento.setIdPagamento5(idPagamento
						.substring(23, 25).trim());
				
			} 
			
			// Campanha
			else {
				
				// N�mero do Lote
				registroHelperCodigoBarrasTipoPagamento.setIdPagamento1(idPagamento
						.substring(0, 8).trim());
				
				// Ano do Lote
				registroHelperCodigoBarrasTipoPagamento.setIdPagamento2(idPagamento
						.substring(8, 12).trim());
				
				// Matr�cula do Im�vel sem o d�gito verificador
				registroHelperCodigoBarrasTipoPagamento.setIdPagamento3(idPagamento
						.substring(12, 21).trim());
				
				// Tipo de Documento 
				registroHelperCodigoBarrasTipoPagamento.setIdPagamento4(idPagamento
						.substring(21, 22).trim());
				
				// Tipo de Documento 
				registroHelperCodigoBarrasTipoPagamento.setIdPagamento5(idPagamento
						.substring(22, 23).trim());
				
				// Plano do Financiamento
				registroHelperCodigoBarrasTipoPagamento.setIdPagamento6(idPagamento
						.substring(23, 25).trim());
				
			}
		}	
		//===============================================================================================================================
		
		
		return registroHelperCodigoBarrasTipoPagamento;
	}
	
	
	/**
	 * [UC0259] - Processar Pagamento com C�digo de Barras - LEGADO
	 *
	 * @author Raphael Rossiter
	 * @date 26/05/2008
	 *
	 * @param registroHelperCodigoBarras
	 * @param dataPagamento
	 * @param anoMesPagamento
	 * @param valorPagamento
	 * @param idFormaPagamento
	 * @param sistemaParametro
	 * @return PagamentoHelperCodigoBarras
	 * @throws ControladorException
	 */
	protected PagamentoHelperCodigoBarras processarPagamentosCodigoBarrasPorTipoPagamento(
			RegistroHelperCodigoBarras registroHelperCodigoBarras, Date dataPagamento, Integer anoMesPagamento,
			BigDecimal valorPagamento, Integer idFormaArrecadacao, SistemaParametro sistemaParametro, Usuario usuarioLogado) 
			throws ControladorException {
		
		PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = null;
		
		String tipoPagamento = registroHelperCodigoBarras.getTipoPagamento();		
		
		//LEGADO - CAEMA
		//===============================================================================================================================
		if (tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_NEGOCIACAO_A_VISTA_LEGADO_CAEMA.toString()) ||
			tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_LEGADO_CAEMA.toString())){
			
			pagamentoHelperCodigoBarras = this.processarPagamentosCodigoBarrasGuiaPagamento_CAEMA_LEGADO(
			registroHelperCodigoBarras, sistemaParametro,dataPagamento, anoMesPagamento, valorPagamento,
			idFormaArrecadacao);
					
			
			

			//===============================================================================================================================
		}
		
		else if (tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_FATURA_LEGADO_CAEMA.toString())){
			
			pagamentoHelperCodigoBarras = this.processarPagamentosCodigoBarrasClienteResponsavel_CAEMA_LEGADO(
			registroHelperCodigoBarras, sistemaParametro,dataPagamento, anoMesPagamento, valorPagamento,
			idFormaArrecadacao);
					

			//===============================================================================================================================
		}
		
		else if (tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA_LEGADO_CAEMA.toString())){
			
			pagamentoHelperCodigoBarras = this.processarPagamentosCodigoBarrasConta_CAEMA_LEGADO(
			registroHelperCodigoBarras, sistemaParametro,dataPagamento, anoMesPagamento, valorPagamento,
			idFormaArrecadacao);
			
			
			//===============================================================================================================================
		}		
	
		else if (tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CAMPANHA_AVISO_DEBITO_LEGADO_CAEMA.toString())){
			
			/*
			 * Caso o G05.7.2.6 - PLANO DE FINANCIAMENTO tenha sido informado � CAMPANHA, caso
			 * contr�rio ser� AVISO DE D�BITO.
			 */
			if (registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento6() != null){
				
				//CAMPANHA
				pagamentoHelperCodigoBarras = this.processarPagamentosCodigoBarrasCampanha_CAEMA_LEGADO(
				registroHelperCodigoBarras, sistemaParametro,dataPagamento, anoMesPagamento, valorPagamento,
				idFormaArrecadacao);
				
				//===============================================================================================================================
			}
			else{
				
				//AVISO DE D�BITO
				pagamentoHelperCodigoBarras = processarPagamentosCodigoBarrasDocumentoCobrancaTipo5_CAEMA_LEGADO(
				registroHelperCodigoBarras, sistemaParametro, dataPagamento, anoMesPagamento, valorPagamento,
				idFormaArrecadacao, usuarioLogado);
				
				//===============================================================================================================================
			}
			
		}
		else{
			
			pagamentoHelperCodigoBarras = super.processarPagamentosCodigoBarrasPorTipoPagamento(
					registroHelperCodigoBarras, dataPagamento,
					anoMesPagamento, valorPagamento,
					idFormaArrecadacao, sistemaParametro, usuarioLogado);
			
		}
		
		
		
		

		return pagamentoHelperCodigoBarras;
	}
	
	
	/**
	 * [UC0259] - Processar Pagamento com C�digo de Barras - LEGADO
	 *
	 * [SB0014] - Processar Pagamento Legado CAEMA - FATURA 
	 *
	 * @author Raphael Rossiter
	 * @date 27/05/2008
	 *
	 * @param registroHelperCodigoBarras
	 * @param sistemaParametro
	 * @param dataPagamento
	 * @param anoMesPagamento
	 * @param valorPagamento
	 * @param idFormaPagamento
	 * @return PagamentoHelperCodigoBarras
	 * @throws ControladorException
	 */
	protected PagamentoHelperCodigoBarras processarPagamentosCodigoBarrasClienteResponsavel_CAEMA_LEGADO(
			RegistroHelperCodigoBarras registroHelperCodigoBarras,
			SistemaParametro sistemaParametro, Date dataPagamento,
			Integer anoMesPagamento, BigDecimal valorPagamento,
			Integer idFormaPagamento) throws ControladorException {

		PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = new PagamentoHelperCodigoBarras();

		String descricaoOcorrencia = "OK";

		String indicadorAceitacaoRegistro = "1";

		Collection colecaoPagamentos = new ArrayList();
		
		/*
		 * Alterado por Raphael Rossiter e S�vio em 11/09/2008
		 * O c�digo or�ament�rio na realizade � o id do cliente respons�vel 
		 */
		//[FS0011] - Validar C�digo Or�ament�rio
		/*Short codigoOrcamentario = null;
		Integer codigoOrcamentarioInteger = null;
		
		boolean codigoOrcamentarioInvalido = Util.validarValorNaoNumerico(registroHelperCodigoBarras
		.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento3());
		
		if (codigoOrcamentarioInvalido) {
			descricaoOcorrencia = "C�DIGO OR�AMENT�RIO N�O NUM�RICO";
		}
		else{
			
			codigoOrcamentarioInteger = new Integer(registroHelperCodigoBarras
			.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento3());
			
			codigoOrcamentario = new Short(codigoOrcamentarioInteger.toString());
		}*/
		
		//[FS0000] - Validar Cliente
		boolean idClienteInvalido = Util.validarValorNaoNumerico(registroHelperCodigoBarras
		.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento3());
		
		Integer idClienteNaBase = null;

		if (idClienteInvalido) {
			descricaoOcorrencia = "C�DIGO DO CLIENTE N�O NUM�RICO";
		} 
		else {
			
			// Verifica se existe o id do cliente na base
			Integer idCliente = new Integer(registroHelperCodigoBarras
			.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento3());

			try {
				idClienteNaBase = repositorioCliente.verificarExistenciaCliente(idCliente);
			} 
			catch (ErroRepositorioException e) {
				throw new ControladorException("erro.sistema", e);
			}

			if (idClienteNaBase == null) {
				descricaoOcorrencia = "CLIENTE RESPONS�VEL N�O CADASTRADO";
			}
		}
		
		//[FS0012] - Validar Data de Vencimento
		Date dataVencimento = null;
		
		boolean dataVencimentoInvalido = Util.validarDiaMesAnoSemBarra(registroHelperCodigoBarras
				.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento1());
		
		if (dataVencimentoInvalido) {
			descricaoOcorrencia = "DATA DE VENCIMENTO INV�LIDO";
		}
		else{
			dataVencimento = Util.converteStringSemBarraParaDate(registroHelperCodigoBarras
			.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento1());
		}
		

		if (descricaoOcorrencia.equals("OK")) {

			/*
			 * O anoMesReferencia ser� formado pelo ano da data de vencimento da fatura com o m�s
			 * que vem no c�digo de barras - Raphael Rossiter e Rosana Carvalho
			 */
			String mesFatura = registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento()
			.getIdPagamento5();
			
			String anoMesReferencia = String.valueOf(Util.getAno(dataVencimento)) + mesFatura; 
			
			// inicializa a cole��o de fatura item
			Collection faturaItens = null;

			try {
				
				faturaItens = repositorioFaturamento.pesquisarFaturaItem(idClienteNaBase, 
				new Integer(anoMesReferencia), valorPagamento);
			} 
			catch (ErroRepositorioException e) {
				throw new ControladorException("erro.sistema", e);
			}

			// verifica se a cole��o � diferente de nula
			if (faturaItens != null && !faturaItens.isEmpty()) {
				
				Iterator faturaItensIterator = faturaItens.iterator();
				
				while (faturaItensIterator.hasNext()) {
					
					Object[] faturaItem = (Object[]) faturaItensIterator.next();
					
					// inicializa as variaveis que veio da pesquisa
					Integer idContaPesquisa = null;
					Integer idImovelPesquisa = null;
					Integer idLocalidadePesquisa = null;
					BigDecimal valorConta = null;
					Integer anoMesReferenciaFatura = null;
					// verifica o valor da conta
					if (faturaItem[0] != null) {
						valorConta = (BigDecimal) faturaItem[0];
					}
					// verifica o id da conta
					if (faturaItem[1] != null) {
						idContaPesquisa = (Integer) faturaItem[1];
					}
					// verifica o id da localidade
					if (faturaItem[2] != null) {
						idLocalidadePesquisa = (Integer) faturaItem[2];
					}
					// verifica o id do imovel
					if (faturaItem[3] != null) {
						idImovelPesquisa = (Integer) faturaItem[3];
					}
					// verifica o id da localidade de Conta
					// Hist�rico
					if (faturaItem[4] != null) {
						idLocalidadePesquisa = (Integer) faturaItem[4];
					}
					// verifica o id do imovel de Conta Hist�rico
					if (faturaItem[5] != null) {
						idImovelPesquisa = (Integer) faturaItem[5];
					}
					//verifica o id da conta Historico
					if (faturaItem[6] != null) {
						idContaPesquisa = (Integer) faturaItem[6];
					}
					//verifica o id da conta Historico
					if (faturaItem[7] != null) {
						anoMesReferenciaFatura = (Integer) faturaItem[7];
					}

					// cria o objeto pagamento para setar os dados
					Pagamento pagamento = new Pagamento();
					pagamento.setAnoMesReferenciaPagamento(anoMesReferenciaFatura);
					// caso o ano mes da data de dedito seja
					// maior que o ano mes de arrecada��o da
					// tabela sistema parametro ent�o seta o ano
					// mes da data de debito
					if (anoMesPagamento > sistemaParametro
							.getAnoMesArrecadacao()) {
						pagamento
								.setAnoMesReferenciaArrecadacao(anoMesPagamento);
					} else {
						// caso contrario seta o o ano mes
						// arrecada��o da tabela sistema
						// parametro
						pagamento
								.setAnoMesReferenciaArrecadacao(sistemaParametro
										.getAnoMesArrecadacao());
					}
					pagamento.setValorPagamento(valorConta);
					pagamento.setDataPagamento(dataPagamento);
					pagamento.setPagamentoSituacaoAtual(null);
					pagamento.setPagamentoSituacaoAnterior(null);
					pagamento.setDebitoTipo(null);
					// verifica se o id da conta � diferente de
					// nulo
					if (idContaPesquisa != null) {
						ContaGeral conta = new ContaGeral();
						conta.setId(idContaPesquisa);
						pagamento.setContaGeral(conta);
					} else {
						pagamento.setContaGeral(null);
					}
					pagamento.setGuiaPagamento(null);

					pagamento.setDebitoACobrarGeral(null);

					// verifica se o id da conta � diferente de
					// nulo
					if (idLocalidadePesquisa != null) {
						Localidade localidade = new Localidade();
						localidade.setId(idLocalidadePesquisa);
						pagamento.setLocalidade(localidade);
					} else {
						pagamento.setLocalidade(null);
					}
					DocumentoTipo documentoTipo = new DocumentoTipo();
					documentoTipo.setId(DocumentoTipo.FATURA_CLIENTE);
					pagamento.setDocumentoTipo(documentoTipo);

					// seta o id do aviso bancario
					pagamento.setAvisoBancario(null);

					// seta o imovel
					if (idImovelPesquisa != null) {
						Imovel imovel = new Imovel();
						imovel.setId(idImovelPesquisa);
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

					colecaoPagamentos.add(pagamento);
				}
			}
			else{
				
				//atribui o valor 2(N�O) ao indicador aceitacao registro
				indicadorAceitacaoRegistro = "2";
				
				descricaoOcorrencia = "FATURA DO C�DIGO OR�AMENT�RIO INEXISTENTE";
			}
		} 
		else {
			
			// atribui o valor 2(N�O) ao indicador aceitacao registro
			indicadorAceitacaoRegistro = "2";
		}

		// Seta os parametros que ser�o retornados
		pagamentoHelperCodigoBarras.setColecaoPagamentos(colecaoPagamentos);
		pagamentoHelperCodigoBarras.setDescricaoOcorrencia(descricaoOcorrencia);
		pagamentoHelperCodigoBarras.setIndicadorAceitacaoRegistro(indicadorAceitacaoRegistro);

		return pagamentoHelperCodigoBarras;
	}
	
	
	/**
	 * [UC0259] - Processar Pagamento com C�digo de Barras - LEGADO
	 *
	 * [SB0014] - Processar Pagamento Legado CAEMA - CONTA ou 2 VIA
	 *
	 * @author Raphael Rossiter
	 * @date 27/05/2008
	 *
	 * @param registroHelperCodigoBarras
	 * @param sistemaParametro
	 * @param dataPagamento
	 * @param anoMesPagamento
	 * @param valorPagamento
	 * @param idFormaPagamento
	 * @return PagamentoHelperCodigoBarras
	 * @throws ControladorException
	 */
	protected PagamentoHelperCodigoBarras processarPagamentosCodigoBarrasConta_CAEMA_LEGADO(
			RegistroHelperCodigoBarras registroHelperCodigoBarras,
			SistemaParametro sistemaParametro, Date dataPagamento,
			Integer anoMesPagamento, BigDecimal valorPagamento,
			Integer idFormaPagamento) throws ControladorException {

		PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = new PagamentoHelperCodigoBarras();

		String descricaoOcorrencia = "OK";

		String indicadorAceitacaoRegistro = "1";

		Collection colecaoPagamentos = new ArrayList();

		boolean matriculaImovelInvalida = false;

		Integer idImovelNaBase = null;
		String matriculaImovel = null;
		Integer matriculaImovelValidada = null;

		//[FS0002] - Validar matr�cula do im�vel
		matriculaImovelInvalida = Util.validarValorNaoNumerico(registroHelperCodigoBarras
		.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento3());

		if (matriculaImovelInvalida) {
			descricaoOcorrencia = "M�TRICULA DO IM�VEL INV�LIDA";
		} 
		else {

			//Calcular Digito Verificador da Matricula (M�DULO 11)
			matriculaImovel = registroHelperCodigoBarras
			.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento3();

			if (matriculaImovel != null) {
				
				int digitoModulo11 = Util.obterDigitoVerificadorModulo11(matriculaImovel);
				
				try {
					
					matriculaImovelValidada = new Integer(matriculaImovel + digitoModulo11);
					
				} catch (Exception e) {
					matriculaImovelValidada = 0;
				}
			}

			// Verifica se existe a matricula do im�vel na base
			try {
				idImovelNaBase = repositorioImovel
				.recuperarMatriculaImovel(matriculaImovelValidada);
			} 
			catch (ErroRepositorioException e) {
				e.printStackTrace();
				throw new ControladorException("erro.sistema", e);
			}

			if (idImovelNaBase == null) {
				descricaoOcorrencia = "MATR�CULA DO IM�VEL N�O CADASTRADA";
			}
		}

		
		
		//M�s/Ano de refer�ncia da conta = (campo G05.7.2.3 + G05.7.2.6)
		Integer anoMesReferencia = new Integer(registroHelperCodigoBarras
		.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento2() + 
		registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento6());

		if (descricaoOcorrencia.equals("OK")) {

			Integer idLocalidade = null;

			Integer idConta = null;

			Imovel imovel = new Imovel();
			imovel.setId(idImovelNaBase);

			try {
				idConta = repositorioFaturamento
				.pesquisarExistenciaContaComSituacaoAtual(imovel, anoMesReferencia);

			} catch (ErroRepositorioException e) {
				e.printStackTrace();
				throw new ControladorException("erro.sistema", e);
			}
			
			/*
             * Alterado por Raphael Rossiter em 09/01/2008 - Analistas: Eduardo e Aryed
             * OBJ: Gerar os pagamentos associados com a localidade da conta e N�O com
             * a localidade do im�vel.
             */
			if (idConta == null) {
				descricaoOcorrencia = "CONTA INEXISTENTE";
			
				try {
                    idLocalidade = repositorioLocalidade
                    .pesquisarIdLocalidade(idImovelNaBase);

                } catch (ErroRepositorioException e) {
                    throw new ControladorException("erro.sistema", e);
                }
			}
			else {
				
				try {
                    idLocalidade = repositorioLocalidade
                    .pesquisarIdLocalidadePorConta(idConta);

                } catch (ErroRepositorioException e) {
                    throw new ControladorException("erro.sistema", e);
                }
			}

			// Cria o objeto pagamento para setar os dados
			Pagamento pagamento = new Pagamento();
			pagamento.setAnoMesReferenciaPagamento(anoMesReferencia);

			/*
			 * Caso o ano mes da data de dedito seja maior que o ano mes de
			 * arrecada��o da tabela sistema parametro ent�o seta o ano mes da
			 * data de debito.
			 */
			if (anoMesPagamento > sistemaParametro.getAnoMesArrecadacao()) {

				pagamento.setAnoMesReferenciaArrecadacao(anoMesPagamento);

			} else {

				/*
				 * caso contrario seta o o ano mes arrecada��o da tabela sistema
				 * parametro
				 */
				pagamento.setAnoMesReferenciaArrecadacao(sistemaParametro
						.getAnoMesArrecadacao());
			}

			pagamento.setValorPagamento(valorPagamento);
			pagamento.setDataPagamento(dataPagamento);
			pagamento.setPagamentoSituacaoAtual(null);
			pagamento.setPagamentoSituacaoAnterior(null);
			pagamento.setDebitoTipo(null);

			// Verifica se o id da conta � diferente de nulo
			if (idConta != null) {

				ContaGeral conta = new ContaGeral();
				conta.setId(idConta);
				pagamento.setContaGeral(conta);

			} else {

				pagamento.setContaGeral(null);
			}
			pagamento.setGuiaPagamento(null);

			// verifica se o id da conta � diferente de nulo
			if (idLocalidade != null) {

				Localidade localidade = new Localidade();
				localidade.setId(idLocalidade);
				pagamento.setLocalidade(localidade);
			} else {

				pagamento.setLocalidade(null);
			}

			DocumentoTipo documentoTipo = new DocumentoTipo();
			documentoTipo.setId(DocumentoTipo.CONTA);
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

			colecaoPagamentos.add(pagamento);
			
			// Alterado por Davi Menezes Data: 14/03/2013, Analista Claudio Lira
			// quando o tipo de debito for Conta � pra analisar 
			// a entrada de parcelamento
			if(idConta != null){
				if(sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAEMA)){
					if(sistemaParametro.getIndicadorExcluirNegativacaoAposPgmto().equals(ConstantesSistema.SIM)){
						FiltroConta filtroConta = new FiltroConta();
						
						filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.ID, idConta));
						filtroConta.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.IMOVEL);
						filtroConta.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.PARCELAMENTO);
						filtroConta.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.CONTA_MOTIVO_REVISAO);
						
						Collection colecaoConta = this.getControladorUtil().pesquisar(filtroConta, Conta.class.getName());
						if(!Util.isVazioOrNulo(colecaoConta)){
							Conta conta = (Conta) Util.retonarObjetoDeColecao(colecaoConta);
							if(conta != null){
								if(conta.getParcelamento() != null && conta.getContaMotivoRevisao() != null 
										&& conta.getContaMotivoRevisao().getId().equals(ContaMotivoRevisao.REVISAO_ENTRADA_DE_PARCELAMENTO)){
									
									Parcelamento parcelamento = conta.getParcelamento();
									if(parcelamento != null){
										Integer qtdContas = getControladorFaturamento().verificarExistenciaContaAssociadaEntradaParcelamentoNaoPaga(
												parcelamento.getId(), idConta, conta.getImovel().getId());
										
										if(qtdContas == 0){
											this.getControladorSpcSerasa().verificarRelacaoDoParcelamentoComItensNegativacao(parcelamento, conta, null);
											
											FiltroParcelamentoItem filtroParcelamentoItem = new FiltroParcelamentoItem();
											filtroParcelamentoItem.adicionarParametro(new ParametroSimples(FiltroParcelamentoItem.PARCELAMENTO, parcelamento.getId()));
											filtroParcelamentoItem.adicionarParametro(new ParametroSimples(FiltroParcelamentoItem.ID_DOCUMENTO_TIPO, DocumentoTipo.CONTA));
											filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("contaGeral.conta");
											
											Collection<ParcelamentoItem> colParcelamentoItem = getControladorUtil().pesquisar(filtroParcelamentoItem, ParcelamentoItem.class.getName());
											if(!Util.isVazioOrNulo(colParcelamentoItem)){
												for(ParcelamentoItem parcelamentoItem : colParcelamentoItem){
													this.getControladorSpcSerasa().verificarRelacaoDoParcelamentoComItensNegativacao(
															parcelamento, parcelamentoItem.getContaGeral().getConta(), null);
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}

		} else {
			// atribui o valor 2(N�O) ao indicador aceitacao registro
			indicadorAceitacaoRegistro = "2";
		}

		// Seta os parametros que ser�o retornados
		pagamentoHelperCodigoBarras.setColecaoPagamentos(colecaoPagamentos);
		pagamentoHelperCodigoBarras.setDescricaoOcorrencia(descricaoOcorrencia);
		pagamentoHelperCodigoBarras
				.setIndicadorAceitacaoRegistro(indicadorAceitacaoRegistro);

		return pagamentoHelperCodigoBarras;
	}
	
	
	/**
	 * [UC0259] - Processar Pagamento com C�digo de Barras
	 *
	 * [SB0014] - Processar Pagamento Legado CAEMA - GUIA DE PAGAMENTO
	 *
	 * @author Raphael Rossiter
	 * @date 16/06/2008
	 *
	 * @param registroHelperCodigoBarras
	 * @param sistemaParametro
	 * @param dataPagamento
	 * @param anoMesPagamento
	 * @param valorPagamento
	 * @param idFormaPagamento
	 * @return PagamentoHelperCodigoBarras
	 * @throws ControladorException
	 */
	protected PagamentoHelperCodigoBarras processarPagamentosCodigoBarrasGuiaPagamento_CAEMA_LEGADO(
			RegistroHelperCodigoBarras registroHelperCodigoBarras,
			SistemaParametro sistemaParametro, Date dataPagamento,
			Integer anoMesPagamento, BigDecimal valorPagamento,
			Integer idFormaPagamento) throws ControladorException {

		PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = new PagamentoHelperCodigoBarras();

		String descricaoOcorrencia = "OK";

		String indicadorAceitacaoRegistro = "1";

		Collection colecaoPagamnetos = new ArrayList();

		boolean matriculaImovelInvalida = false;

		Integer idImovelNaBase = null;
		String matriculaImovel = null;
		Integer matriculaImovelValidada = null;

		matriculaImovelInvalida = Util
				.validarValorNaoNumerico(registroHelperCodigoBarras
						.getRegistroHelperCodigoBarrasTipoPagamento()
						.getIdPagamento3());

		if (matriculaImovelInvalida) {
			descricaoOcorrencia = "M�TRICULA DO IM�VEL INV�LIDA";
		} else {

			//Calcular Digito Verificador da Matricula (M�DULO 10)
			matriculaImovel = registroHelperCodigoBarras
			.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento3();

			if (matriculaImovel != null) {
				
				int digitoModulo11 = Util.obterDigitoVerificadorModulo11(matriculaImovel);
				
				try {
					
					matriculaImovelValidada = new Integer(matriculaImovel + digitoModulo11);
					
				} catch (Exception e) {
					matriculaImovelValidada = 0;
				}
			}

			// Verifica se existe a matricula do im�vel na base
			try {
				idImovelNaBase = repositorioImovel
				.recuperarMatriculaImovel(matriculaImovelValidada);
			} 
			catch (ErroRepositorioException e) {
				e.printStackTrace();
				throw new ControladorException("erro.sistema", e);
			}

			if (idImovelNaBase == null) {
				descricaoOcorrencia = "MATR�CULA DO IM�VEL N�O CADASTRADA";
			}
		}


		if (descricaoOcorrencia.equals("OK")) {

			Integer idLocalidade = null;

			Integer dadosGuiaPagamento[] = null;

			Imovel imovel = new Imovel();
			imovel.setId(idImovelNaBase);
			
			//ANO DA GUIA
			Integer anoGuia = new Integer(registroHelperCodigoBarras
			.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento1());
			
			//N�MERO GUIA
			Integer numeroGuia = new Integer(registroHelperCodigoBarras
			.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento2());

			
			try {
				
				dadosGuiaPagamento = repositorioArrecadacao
				.pesquisarExistenciaGuiaPagamento(imovel, numeroGuia, anoGuia);

			} catch (ErroRepositorioException e) {
				e.printStackTrace();
				throw new ControladorException("erro.sistema", e);
			}

			if (dadosGuiaPagamento == null) {
				descricaoOcorrencia = "GUIA PAGAMENTO INEXISTENTE";
			}
			
			
			/*
             * Alterado por Raphael Rossiter em 11/01/2008 - Analistas: Eduardo e Aryed
             * OBJ: Gerar os pagamentos associados com a localidade da guia de pagamento e N�O com
             * a localidade do im�vel.
             */
            if (dadosGuiaPagamento != null) {
            	
            	try {
                    idLocalidade = repositorioLocalidade
                    .pesquisarIdLocalidadePorGuiaPagamento(dadosGuiaPagamento[0]);

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
			 * arrecada��o da tabela sistema parametro ent�o seta o ano mes da
			 * data de debito
			 */
			if (anoMesPagamento > sistemaParametro.getAnoMesArrecadacao()) {

				pagamento.setAnoMesReferenciaArrecadacao(anoMesPagamento);

			} else {

				/*
				 * caso contrario seta o o ano mes arrecada��o da tabela sistema
				 * parametro
				 */
				pagamento.setAnoMesReferenciaArrecadacao(sistemaParametro
						.getAnoMesArrecadacao());
			}

			pagamento.setValorPagamento(valorPagamento);
			pagamento.setDataPagamento(dataPagamento);
			pagamento.setPagamentoSituacaoAtual(null);
			pagamento.setPagamentoSituacaoAnterior(null);
			pagamento.setContaGeral(null);

			// Verifica se o id da guia � diferente de nulo
			if (dadosGuiaPagamento != null) {

				GuiaPagamento guiaPagamento = new GuiaPagamento();
				guiaPagamento.setId(dadosGuiaPagamento[0]);
				pagamento.setGuiaPagamento(guiaPagamento);
				
				DebitoTipo debitoTipo = new DebitoTipo();
				debitoTipo.setId(dadosGuiaPagamento[1]);
				pagamento.setDebitoTipo(debitoTipo);
				
				// Alterado por Davi Menezes Data: 14/03/2013, Analista Claudio Lira
    			// quando o tipo de debito for Guia de Pagamento � pra analisar 
    			// a entrada de parcelamento
				if(sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAEMA)){
					if(sistemaParametro.getIndicadorExcluirNegativacaoAposPgmto().equals(ConstantesSistema.SIM)){
						String idImovel = String.valueOf(idImovelNaBase);
						
						GuiaPagamento novaGuiaPagamento = this.pesquisarGuiaPagamentoDigitadaRegistrarMovimento(idImovel, String.valueOf(guiaPagamento.getId()));
						if(novaGuiaPagamento != null){
							if(novaGuiaPagamento.getParcelamento() != null && novaGuiaPagamento.getParcelamento().getId() != null){
								if(novaGuiaPagamento.getDebitoTipo() != null 
										&& novaGuiaPagamento.getDebitoTipo().getId().equals(DebitoTipo.ENTRADA_PARCELAMENTO)){
									
									Parcelamento parcelamento = novaGuiaPagamento.getParcelamento();
									
									this.getControladorSpcSerasa().verificarRelacaoDoParcelamentoComItensNegativacao(parcelamento, null, novaGuiaPagamento);
									
									FiltroParcelamentoItem filtroParcelamentoItem = new FiltroParcelamentoItem();
									filtroParcelamentoItem.adicionarParametro(new ParametroSimples(FiltroParcelamentoItem.PARCELAMENTO, parcelamento.getId()));
									filtroParcelamentoItem.adicionarParametro(new ParametroSimples(FiltroParcelamentoItem.ID_DOCUMENTO_TIPO, DocumentoTipo.CONTA));
									filtroParcelamentoItem.adicionarCaminhoParaCarregamentoEntidade("contaGeral.conta");
									
									Collection<ParcelamentoItem> colParcelamentoItem = getControladorUtil().pesquisar(filtroParcelamentoItem, ParcelamentoItem.class.getName());
									if(!Util.isVazioOrNulo(colParcelamentoItem)){
										for(ParcelamentoItem parcelamentoItem : colParcelamentoItem){
											this.getControladorSpcSerasa().verificarRelacaoDoParcelamentoComItensNegativacao(
													parcelamento, parcelamentoItem.getContaGeral().getConta(), null);
										}
									}
								}
							}
						}
					}
				}
				
			} else {
				pagamento.setGuiaPagamento(null);
				
				/*
				 * Colocado por Raphael Rossiter em 13/01/2009
				 * Analista: Eduardo Borges
				 */
				DebitoTipo debitoTipo = new DebitoTipo();
				debitoTipo.setId(DebitoTipo.SERVICOS_ESPECIAIS);
				
				pagamento.setDebitoTipo(debitoTipo);
			}

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

			colecaoPagamnetos.add(pagamento);

		} else {

			// Atribui o valor 2(N�O) ao indicador aceitacao registro
			indicadorAceitacaoRegistro = "2";
		}

		// Seta os parametros que ser�o retornados
		pagamentoHelperCodigoBarras.setColecaoPagamentos(colecaoPagamnetos);
		pagamentoHelperCodigoBarras.setDescricaoOcorrencia(descricaoOcorrencia);
		pagamentoHelperCodigoBarras.setIndicadorAceitacaoRegistro(indicadorAceitacaoRegistro);

		return pagamentoHelperCodigoBarras;
	}
	
	
	/**
	 * [UC0259] - Processar Pagamento com C�digo de Barras
	 *
	 * [SB0014] - Processar Pagamento Legado CAEMA - CAMPANHA
	 *
	 * @author Raphael Rossiter
	 * @date 16/06/2008
	 *
	 * @param registroHelperCodigoBarras
	 * @param sistemaParametro
	 * @param dataPagamento
	 * @param anoMesPagamento
	 * @param valorPagamento
	 * @param idFormaPagamento
	 * @return PagamentoHelperCodigoBarras
	 * @throws ControladorException
	 */
	protected PagamentoHelperCodigoBarras processarPagamentosCodigoBarrasCampanha_CAEMA_LEGADO(
			RegistroHelperCodigoBarras registroHelperCodigoBarras,
			SistemaParametro sistemaParametro, Date dataPagamento,
			Integer anoMesPagamento, BigDecimal valorPagamento,
			Integer idFormaPagamento) throws ControladorException {

		PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = new PagamentoHelperCodigoBarras();

		String descricaoOcorrencia = "OK";

		String indicadorAceitacaoRegistro = "1";

		Collection colecaoPagamnetos = new ArrayList();

		boolean matriculaImovelInvalida = false;

		Integer idImovelNaBase = null;
		String matriculaImovel = null;
		Integer matriculaImovelValidada = null;

		matriculaImovelInvalida = Util
				.validarValorNaoNumerico(registroHelperCodigoBarras
						.getRegistroHelperCodigoBarrasTipoPagamento()
						.getIdPagamento3());

		if (matriculaImovelInvalida) {
			descricaoOcorrencia = "M�TRICULA DO IM�VEL INV�LIDA";
		} else {

			//Calcular Digito Verificador da Matricula (M�DULO 10)
			matriculaImovel = registroHelperCodigoBarras
			.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento3();

			if (matriculaImovel != null) {
				
				int digitoModulo11 = Util.obterDigitoVerificadorModulo11(matriculaImovel);
				
				try {
					
					matriculaImovelValidada = new Integer(matriculaImovel + digitoModulo11);
					
				} catch (Exception e) {
					matriculaImovelValidada = 0;
				}
			}

			// Verifica se existe a matricula do im�vel na base
			try {
				idImovelNaBase = repositorioImovel
				.recuperarMatriculaImovel(matriculaImovelValidada);
			} 
			catch (ErroRepositorioException e) {
				e.printStackTrace();
				throw new ControladorException("erro.sistema", e);
			}

			if (idImovelNaBase == null) {
				descricaoOcorrencia = "MATR�CULA DO IM�VEL N�O CADASTRADA";
			}
		}


		if (descricaoOcorrencia.equals("OK")) {

			Integer idLocalidade = null;

			Integer dadosGuiaPagamento[] = null;

			Imovel imovel = new Imovel();
			imovel.setId(idImovelNaBase);
			
			//N�MERO LOTE
			Integer lotePagamento = new Integer(registroHelperCodigoBarras
			.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento1());
			
			//ANO DO LOTE
			Integer anoGuia = new Integer(registroHelperCodigoBarras
			.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento2());
			
			try {
				
				dadosGuiaPagamento = repositorioArrecadacao
				.pesquisarExistenciaGuiaPagamentoPorLotePagamento(imovel, lotePagamento, anoGuia);

			} catch (ErroRepositorioException e) {
				e.printStackTrace();
				throw new ControladorException("erro.sistema", e);
			}

			if (dadosGuiaPagamento == null) {
				descricaoOcorrencia = "GUIA PAGAMENTO INEXISTENTE";
			}
			
			
			/*
             * Alterado por Raphael Rossiter em 11/01/2008 - Analistas: Eduardo e Aryed
             * OBJ: Gerar os pagamentos associados com a localidade da guia de pagamento e N�O com
             * a localidade do im�vel.
             */
            if (dadosGuiaPagamento != null) {
            	
            	try {
                    idLocalidade = repositorioLocalidade
                    .pesquisarIdLocalidadePorGuiaPagamento(dadosGuiaPagamento[0]);

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
			 * arrecada��o da tabela sistema parametro ent�o seta o ano mes da
			 * data de debito
			 */
			if (anoMesPagamento > sistemaParametro.getAnoMesArrecadacao()) {

				pagamento.setAnoMesReferenciaArrecadacao(anoMesPagamento);

			} else {

				/*
				 * caso contrario seta o o ano mes arrecada��o da tabela sistema
				 * parametro
				 */
				pagamento.setAnoMesReferenciaArrecadacao(sistemaParametro
						.getAnoMesArrecadacao());
			}

			pagamento.setValorPagamento(valorPagamento);
			pagamento.setDataPagamento(dataPagamento);
			pagamento.setPagamentoSituacaoAtual(null);
			pagamento.setPagamentoSituacaoAnterior(null);
			pagamento.setContaGeral(null);

			// Verifica se o id da guia � diferente de nulo
			if (dadosGuiaPagamento != null) {

				GuiaPagamento guiaPagamento = new GuiaPagamento();
				guiaPagamento.setId(dadosGuiaPagamento[0]);
				pagamento.setGuiaPagamento(guiaPagamento);
				
				DebitoTipo debitoTipo = new DebitoTipo();
				debitoTipo.setId(dadosGuiaPagamento[1]);
				pagamento.setDebitoTipo(debitoTipo);

			} else {
				pagamento.setGuiaPagamento(null);
				
				/*
				 * Colocado por Raphael Rossiter em 23/01/2009
				 * Analista: Eduardo Borges
				 */
				DebitoTipo debitoTipo = new DebitoTipo();
				debitoTipo.setId(DebitoTipo.SERVICOS_ESPECIAIS);
				
				pagamento.setDebitoTipo(debitoTipo);
			}

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

			colecaoPagamnetos.add(pagamento);

		} else {

			// Atribui o valor 2(N�O) ao indicador aceitacao registro
			indicadorAceitacaoRegistro = "2";
		}

		// Seta os parametros que ser�o retornados
		pagamentoHelperCodigoBarras.setColecaoPagamentos(colecaoPagamnetos);
		pagamentoHelperCodigoBarras.setDescricaoOcorrencia(descricaoOcorrencia);
		pagamentoHelperCodigoBarras.setIndicadorAceitacaoRegistro(indicadorAceitacaoRegistro);

		return pagamentoHelperCodigoBarras;
	}
	
	
	/**
	 * [UC0259] - Processar Pagamento com C�digo de Barras
	 *
	 * [SB0014] - Processar Pagamento Legado CAEMA - AVISO DE D�BITO
	 *
	 * @author Raphael Rossiter
	 * @date 16/06/2008
	 *
	 * @param registroHelperCodigoBarras
	 * @param sistemaParametro
	 * @param dataPagamento
	 * @param anoMesPagamento
	 * @param valorPagamento
	 * @param idFormaPagamento
	 * @return PagamentoHelperCodigoBarras
	 * @throws ControladorException
	 */
	protected PagamentoHelperCodigoBarras processarPagamentosCodigoBarrasDocumentoCobrancaTipo5_CAEMA_LEGADO(
			RegistroHelperCodigoBarras registroHelperCodigoBarras,
			SistemaParametro sistemaParametro, Date dataPagamento,
			Integer anoMesPagamento, BigDecimal valorPagamento,
			Integer idFormaPagamento, Usuario usuarioLogado) throws ControladorException {

		PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = new PagamentoHelperCodigoBarras();

		String descricaoOcorrencia = "OK";

		String indicadorAceitacaoRegistro = "1";

		Collection colecaoPagamentos = new ArrayList();

		Collection colecaoDevolucoes = new ArrayList();

		boolean matriculaImovelInvalida = false;

		Integer idImovelNaBase = null;
		String matriculaImovel = null;
		Integer matriculaImovelValidada = null;

		// valida a matricula do im�vel
		matriculaImovelInvalida = Util
				.validarValorNaoNumerico(registroHelperCodigoBarras
						.getRegistroHelperCodigoBarrasTipoPagamento()
						.getIdPagamento3());
		
		if (matriculaImovelInvalida) {
			descricaoOcorrencia = "M�TRICULA DO IM�VEL INV�LIDA";
		} else {
			
			//Calcular Digito Verificador da Matricula (M�DULO 10)
			matriculaImovel = registroHelperCodigoBarras
			.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento3();

			if (matriculaImovel != null) {
				
				int digitoModulo11 = Util.obterDigitoVerificadorModulo11(matriculaImovel);
				
				try {
					
					matriculaImovelValidada = new Integer(matriculaImovel + digitoModulo11);
					
				} catch (Exception e) {
					matriculaImovelValidada = 0;
				}
			}

			// Verifica se existe a matricula do im�vel na base
			try {
				idImovelNaBase = repositorioImovel
				.recuperarMatriculaImovel(matriculaImovelValidada);
			} 
			catch (ErroRepositorioException e) {
				e.printStackTrace();
				throw new ControladorException("erro.sistema", e);
			}

			if (idImovelNaBase == null) {
				descricaoOcorrencia = "MATR�CULA DO IM�VEL N�O CADASTRADA";
			}
		}

		//Campo G05.7.2.3 - Data de Emiss�o
		Date dataEmissao = Util.converteStringSemBarraParaDate(registroHelperCodigoBarras
		.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento1());
		
		if (descricaoOcorrencia.equals("OK")) {
			
			// inicializa o id da localidade
			Integer idLocalidade = null;

			// inicializa a cole��o de cobranca documento item
			Collection cobrancaDocumentoItens = null;
			// inicializa a cole��o de cobranca documento item
			Object[] parmsDocumentoCobranca = null;

			

			try {

				cobrancaDocumentoItens = repositorioCobranca
				.pesquisarCobrancaDocumentoItem(idImovelNaBase, dataEmissao);
				
				if (cobrancaDocumentoItens == null || cobrancaDocumentoItens.isEmpty()){
					
					cobrancaDocumentoItens = repositorioCobranca
					.pesquisarCobrancaDocumentoItem(idImovelNaBase, valorPagamento);
				}
				
				parmsDocumentoCobranca = repositorioCobranca
				.pesquisarParmsCobrancaDocumento(idImovelNaBase, dataEmissao);
				
				if (parmsDocumentoCobranca == null){
					
					parmsDocumentoCobranca = repositorioCobranca
					.pesquisarParmsCobrancaDocumento(idImovelNaBase, valorPagamento);
				}
				
			} catch (ErroRepositorioException e) {
				throw new ControladorException("erro.sistema", e);
			}

			// caso exista documento de cobran�a
			if (parmsDocumentoCobranca != null) {
				
				Integer idCobrancaDocumento = null;
				BigDecimal valorAcrescimo = new BigDecimal("0.00");
				BigDecimal valorDesconto = new BigDecimal("0.00");
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
                 * OBJ: Gerar os pagamentos associados com a localidade do documento de cobran�a e N�O com
                 * a localidade do im�vel.
                 */
				if (parmsDocumentoCobranca[5] != null) {

					idLocalidade = ((Integer) parmsDocumentoCobranca[5]);
				}
				else{
					
					try {
                        idLocalidade = repositorioLocalidade
                        .pesquisarIdLocalidade(idImovelNaBase);

                    } catch (ErroRepositorioException e) {
                        throw new ControladorException("erro.sistema", e);
                    }
                    
				}
				
				if (parmsDocumentoCobranca[6] != null) {
					idDocumentoTipo = ((Integer) parmsDocumentoCobranca[6]);
				}
				
				// Caso o valor de acrescimo for maior que zero
				if (valorAcrescimo.compareTo(new BigDecimal("0.00")) == 1) {
					// [SB0008 - Alterar vencimento dos itens do
					// documento de cobran�a]

					alterarVencimentoItensDocumentoCobranca(
							idCobrancaDocumento, dataEmissao);

				}
				
				// Caso o valor de acrescimos seja maior que o valor de descontos
				if (valorAcrescimo.compareTo(valorDesconto) == 1) {
					
					valorAcrescimo = valorAcrescimo.subtract(valorDesconto);
					valorDesconto = new BigDecimal("0.00");
					
				} 
				else {
					
					valorDesconto = valorDesconto.subtract(valorAcrescimo);
					valorAcrescimo = new BigDecimal("0.00");
				}

				// Caso o valor de acrescimo for maior que zero
				if (valorAcrescimo.compareTo(new BigDecimal("0.00")) == 1) {
					
					// [SB0005 - Processar Recebimento de Acrescimos por Impontualidade]
					Pagamento pagamento = processarRecebimentoAcrescimosImpontualidade(
							idCobrancaDocumento, dataEmissao, valorAcrescimo,
							idImovelNaBase, idLocalidade, sistemaParametro,
							idFormaPagamento, idDocumentoTipo, null);

					colecaoPagamentos.add(pagamento);

				}

				// Caso o valor de desconto for maior que zero
				if (valorDesconto.compareTo(new BigDecimal("0.00")) == 1) {
					
					// [SB0006 - Processar Desconto concedido no documento de cobran�a]
					Devolucao devolucao = processarDescontoConcedidoDocumentoCobranca(
							idCobrancaDocumento, dataEmissao, valorDesconto,
							idImovelNaBase, idLocalidade, sistemaParametro,
							idFormaPagamento, idDocumentoTipo);

					colecaoDevolucoes.add(devolucao);

				}

				// Caso o valor da taxa referente ao documento de cobran�a for maior que zero
				if (valorTaxa.compareTo(new BigDecimal("0.00")) == 1) {
					
					// [SB0006 - Processar Desconto concedido no documento de cobran�a]
					Pagamento pagamento = processarTaxaDocumentoCobranca(
							idCobrancaDocumento, dataEmissao, valorTaxa,
							idImovelNaBase, idLocalidade, sistemaParametro,
							idFormaPagamento, idDocumentoTipo);

					colecaoPagamentos.add(pagamento);

				}

				// verifica se a cole��o � diferente de nula
				if (cobrancaDocumentoItens != null
						&& !cobrancaDocumentoItens.isEmpty()) {

					Iterator cobrancaDocumentoItensIterator = cobrancaDocumentoItens
							.iterator();

					while (cobrancaDocumentoItensIterator.hasNext()) {

						Object[] cobrancaDocumentoItem = (Object[]) cobrancaDocumentoItensIterator
								.next();

						/*
						 * Colocado por Raphael Rossiter em 31/10/2007 OBJ:
						 * Apenas gerar os pagamentos referentes aos itens que
						 * NAO tenham CreditoARealizar
						 */
						BigDecimal valorItemCobrado = null;

						if (cobrancaDocumentoItem[13] == null) {

							// inicializa as variaveis que veio da
							// pesquisa
							Integer idContaPesquisa = null;
							Integer idContaGeralPesquisa = null;
							Integer idGuiaPagamento = null;
							Integer idDebitoACobrar = null;
							int contaReferencia = 0;
							Integer idDebitoTipo = null;
							Integer idGuiaPagamentoGeralPesquisa = null;
							Integer idDebitoACobrarGeralPesquisa = null;
							// verifica o id da conta
							if (cobrancaDocumentoItem[0] != null) {
								idContaPesquisa = (Integer) cobrancaDocumentoItem[0];
								idContaGeralPesquisa = (Integer) cobrancaDocumentoItem[0];
								// referencia conta
								if (cobrancaDocumentoItem[4] != null) {
									contaReferencia = (Integer) cobrancaDocumentoItem[4];
								}
							} else {
								// caso n�o exista na conta ent�o
								// pesquisa
								// na conta hist�rico
								if (cobrancaDocumentoItem[10] != null) {
									idContaGeralPesquisa = (Integer) cobrancaDocumentoItem[10];
								}

								// referencia conta hist�rico
								if (cobrancaDocumentoItem[5] != null) {
									contaReferencia = (Integer) cobrancaDocumentoItem[5];
								}
							}

							// verifica o id da guia pagamento
							if (cobrancaDocumentoItem[1] != null) {
								idGuiaPagamento = (Integer) cobrancaDocumentoItem[1];
								idGuiaPagamentoGeralPesquisa = (Integer) cobrancaDocumentoItem[1];
							} else {
								// caso n�o exista no guia pagamento
								// ent�o
								// pesquisa no guia pagamento hist�rico
								if (cobrancaDocumentoItem[11] != null) {
									idGuiaPagamentoGeralPesquisa = (Integer) cobrancaDocumentoItem[11];
								}
							}
							// verifica o id do debito a cobrar
							if (cobrancaDocumentoItem[2] != null) {
								idDebitoACobrar = (Integer) cobrancaDocumentoItem[2];
								idDebitoACobrarGeralPesquisa = (Integer) cobrancaDocumentoItem[2];

								// [SB0012]- Verifica Pagamento de D�bito a
								// Cobrar
								// de Parcelamento
								verificaPagamentoDebitoACobrarParcelamento(idDebitoACobrar, null, usuarioLogado);

							} else {
								// caso n�o exista no debito a cobrar
								// ent�o
								// pesquisa no debito a cobrar hist�rico
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

							if (idContaGeralPesquisa == null) {
								// caso exista guia de pagamento
								if (idGuiaPagamentoGeralPesquisa != null) {
									// verifica o id do debito tipo se �
									// da
									// guia
									if (cobrancaDocumentoItem[6] != null) {
										idDebitoTipo = (Integer) cobrancaDocumentoItem[6];
									} else {
										// caso n�o exista no guia
										// pagamento
										// ent�o
										// pesquisa no guia pagamento
										// hist�rico
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
										// caso n�o exista no debito a
										// cobrar
										// ent�o
										// pesquisa no debito a cobrar
										// hist�rico
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
								pagamento
										.setAnoMesReferenciaPagamento(contaReferencia);
							} else {
								pagamento.setAnoMesReferenciaPagamento(null);
							}

							// caso o ano mes da data de dedito seja
							// maior que o ano mes de arrecada��o da
							// tabela sistema parametro ent�o seta o ano
							// mes da data de debito
							if (anoMesPagamento > sistemaParametro
									.getAnoMesArrecadacao()) {
								pagamento
										.setAnoMesReferenciaArrecadacao(anoMesPagamento);
							} else {
								// caso contrario seta o o ano mes
								// arrecada��o da tabela sistema
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

							// Verifica se o id da conta � diferente de nulo
							if (idContaGeralPesquisa != null) {
								
								if (idContaPesquisa != null) {
									
									ContaGeral conta = new ContaGeral();
									conta.setId(idContaPesquisa);
									
									pagamento.setContaGeral(conta);
									
									/*
									 * Colocado por Raphael Rossiter em 26/11/2008 - CRC264
									 * OBJ: Inserir o pagamento com a localidade da pr�pria conta e n�o
									 * com a localidade do documento de cobran�a
									 */
									try {
					                    idLocalidade = repositorioLocalidade
					                    .pesquisarIdLocalidadePorConta(idContaPesquisa);

					                } catch (ErroRepositorioException e) {
					                    throw new ControladorException("erro.sistema", e);
					                }
								} 
								else {
									
									pagamento.setContaGeral(null);
									
								}

								DocumentoTipo documentoTipo = new DocumentoTipo();
								documentoTipo.setId(DocumentoTipo.CONTA);
								pagamento.setDocumentoTipo(documentoTipo);
							} 
							else {
								
								pagamento.setContaGeral(null);
								
							}
							
							// Verifica se o id da guia de pagamento � diferente de nulo
							if (idGuiaPagamentoGeralPesquisa != null) {
								
								if (idGuiaPagamento != null) {
									
									GuiaPagamento guiaPagamento = new GuiaPagamento();
									guiaPagamento.setId(idGuiaPagamento);
									pagamento.setGuiaPagamento(guiaPagamento);
									
									/*
									 * Colocado por Raphael Rossiter em 26/11/2008 - CRC264
									 * OBJ: Inserir o pagamento com a localidade da pr�pria guia e n�o
									 * com a localidade do documento de cobran�a
									 */
									try {
					                    idLocalidade = repositorioLocalidade
					                    .pesquisarIdLocalidadePorGuiaPagamento(idGuiaPagamento);

					                } catch (ErroRepositorioException e) {
					                    throw new ControladorException("erro.sistema", e);
					                }
								} 
								else {
									
									pagamento.setGuiaPagamento(null);
									
								}
								
								DocumentoTipo documentoTipo = new DocumentoTipo();
								documentoTipo.setId(DocumentoTipo.GUIA_PAGAMENTO);
								pagamento.setDocumentoTipo(documentoTipo);

							} 
							else {
								
								pagamento.setGuiaPagamento(null);
								
							}
							
							// Verifica se o id do debito a cobrar � diferente de nulo
							if (idDebitoACobrarGeralPesquisa != null) {
								
								if (idDebitoACobrar != null) {
									
									DebitoACobrarGeral debitoACobrarGeral = new DebitoACobrarGeral();
									debitoACobrarGeral.setId(idDebitoACobrar);
									
									pagamento.setDebitoACobrarGeral(debitoACobrarGeral);
									
									/*
									 * Colocado por Raphael Rossiter em 26/11/2008 - CRC264
									 * OBJ: Inserir o pagamento com a localidade do pr�prio debito a cobrar e n�o
									 * com a localidade do documento de cobran�a
									 */
									try {
										
										idLocalidade = repositorioLocalidade
					                    .pesquisarIdLocalidadePorDebitoACobrar(idDebitoACobrar);
										
										// atualiza a situa��o atual para cancelada
										repositorioFaturamento
										.atualizarSituacaoAtualDebitoACobrar(idDebitoACobrar);
										
									} catch (ErroRepositorioException e) {
										throw new ControladorException("erro.sistema", e);
									}

								} 
								else {
									
									pagamento.setDebitoACobrarGeral(null);
									
								}
								
								DocumentoTipo documentoTipo = new DocumentoTipo();
								documentoTipo.setId(DocumentoTipo.DEBITO_A_COBRAR);
								pagamento.setDocumentoTipo(documentoTipo);

							} 
							else {
								
								pagamento.setDebitoACobrarGeral(null);
								
							}

							// verifica se o id da conta � diferente de
							// nulo
							if (idLocalidade != null) {
								Localidade localidade = new Localidade();
								localidade.setId(idLocalidade);
								pagamento.setLocalidade(localidade);
							} else {
								pagamento.setLocalidade(null);

							}

							// seta o id do aviso bancario
							pagamento.setAvisoBancario(null);

							// seta o imovel
							if (idImovelNaBase != null) {
								Imovel imovel = new Imovel();
								imovel.setId(idImovelNaBase);
								pagamento.setImovel(imovel);
							} else {
								pagamento.setImovel(null);
							}

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
							CobrancaDocumento cobrancaDocumento = new CobrancaDocumento();
							cobrancaDocumento.setId(idCobrancaDocumento);
							pagamento.setCobrancaDocumento(cobrancaDocumento);
							
							DocumentoTipo documentoTipoAgregador = new DocumentoTipo();
							documentoTipoAgregador.setId(idDocumentoTipo);
							pagamento.setDocumentoTipoAgregador(documentoTipoAgregador);
							
							pagamento.setDataProcessamento(new Date());
							
							if (pagamento.getDocumentoTipo() != null) {
								colecaoPagamentos.add(pagamento);
							}
						} else {

							// Para os itens que tenham CreditoARealizar gerar
							// suas respectivas devolu��es

							Devolucao devolucao = new Devolucao();

							// DataDevolucao = DataPagamento
							devolucao.setDataDevolucao(dataPagamento);

							/*
							 * AnoMesReferenciaDevolucao Caso o anoMes da data
							 * de devolu��o seja MAIOR que a
							 * PARM_AMREFERENCIAARRECADACAO da tabela
							 * SISTEMA_PARAMETROS atribuir o anoMes da data da
							 * devolu��o, caso contr�rio atribuir o
							 * PARM_AMREFERENCIAARRECADACAO.
							 */
							Integer anoMesDataDevolucao = Util
									.getAnoMesComoInteger(devolucao
											.getDataDevolucao());

							if (anoMesDataDevolucao > sistemaParametro
									.getAnoMesArrecadacao()) {
								devolucao
										.setAnoMesReferenciaArrecadacao(anoMesDataDevolucao);
							} else {
								devolucao
										.setAnoMesReferenciaArrecadacao(sistemaParametro
												.getAnoMesArrecadacao());
							}

							// ValorDevolucao = ValorItemCobrado
							if (cobrancaDocumentoItem[3] != null) {
								valorItemCobrado = (BigDecimal) cobrancaDocumentoItem[3];
								devolucao.setValorDevolucao(valorItemCobrado);
							}

							// Localidade = Localidade da tabela
							// COBRANCA_DOCUMENTO
							if (cobrancaDocumentoItem[14] != null) {
								Localidade localidade = new Localidade();
								localidade
										.setId((Integer) cobrancaDocumentoItem[14]);
								devolucao.setLocalidade(localidade);
							}

							// Imovel = Imovel da tabela COBRANCA_DOCUMENTO
							if (cobrancaDocumentoItem[15] != null) {
								Imovel imovel = new Imovel();
								imovel
										.setId((Integer) cobrancaDocumentoItem[15]);
								devolucao.setImovel(imovel);
							}

							// DebitoTipo = DebitoTipo com o valor
							// correspondente a outros
							DebitoTipo debitoTipo = new DebitoTipo();
							debitoTipo.setId(DebitoTipo.OUTROS);
							devolucao.setDebitoTipo(debitoTipo);

							// CreditoARealizarGeral = CreditoARealizarGeral da
							// tabela COBRANCA_DOCUMENTO_ITEM
							CreditoARealizarGeral creditoARealizarGeral = new CreditoARealizarGeral();
							creditoARealizarGeral
									.setId((Integer) cobrancaDocumentoItem[13]);
							devolucao
									.setCreditoARealizarGeral(creditoARealizarGeral);

							// Ultima Altera��o
							devolucao.setUltimaAlteracao(new Date());

							// ADICIONANDO A DEVOLUCAO GERADA NA COLECAO DE
							// RETORNO
							colecaoDevolucoes.add(devolucao);
						}
					}
				}

			} else {
				descricaoOcorrencia = "DOCUMENTO DE COBRAN�A INEXISTENTE";
				indicadorAceitacaoRegistro = "2";
			}

		} else {
			// atribui o valor 2(N�O) ao indicador aceitacao
			// registro
			indicadorAceitacaoRegistro = "2";
		}

		// Seta os parametros que ser�o retornados
		pagamentoHelperCodigoBarras.setColecaoPagamentos(colecaoPagamentos);
		pagamentoHelperCodigoBarras.setColecaoDevolucao(colecaoDevolucoes);
		pagamentoHelperCodigoBarras.setDescricaoOcorrencia(descricaoOcorrencia);
		pagamentoHelperCodigoBarras.setIndicadorAceitacaoRegistro(indicadorAceitacaoRegistro);

		return pagamentoHelperCodigoBarras;

	}
	
	
	/**
	 * Obt�m a representa��o n�merica do c�digo de barras de um pagamento de
	 * acordo com os par�metros informados
	 * 
	 * [UC0229] Obter Representa��o Num�rica do C�digo de Barras
	 * 
	 * Formata a identifica��o do pagamento de acordo com o tipo de pagamento
	 * informado
	 * 
	 * [SB0001] Obter Identifica��o do Pagamento
	 * 
	 * @author Pedro Alexandre, Raphael Rossiter
	 * @date 20/04/2006, 04/11/2008
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
	 * @return
	 */
	public String obterIdentificacaoPagamento(Integer tipoPagamento,
			Integer idLocalidade, Integer matriculaImovel,
			String mesAnoReferenciaConta,
			Integer digitoVerificadorRefContaModulo10, Integer idTipoDebito,
			String anoEmissaoGuiaPagamento, String sequencialDocumentoCobranca,
			Integer idTipoDocumento, Integer idCliente,
			Integer seqFaturaClienteResponsavel,String idGuiaPagamento) throws ControladorException {

		// Cria a vari�vel que vai armazenar o identificador do pagamento formatado
		String identificacaoPagamento = "";

		// Caso o tipo de pagamento seja referente a conta
		if (tipoPagamento.intValue() == 3) {
			
			identificacaoPagamento = identificacaoPagamento + Util.adicionarZerosEsquedaNumero(3, "" + idLocalidade);
			identificacaoPagamento = identificacaoPagamento + Util.adicionarZerosEsquedaNumero(9, "" + matriculaImovel);
			
			//FIXO
			identificacaoPagamento = identificacaoPagamento + "0";
			
			//Identifica o tamanho da matr�cula do im�vel
			identificacaoPagamento = identificacaoPagamento + "1";
			
			identificacaoPagamento = identificacaoPagamento + mesAnoReferenciaConta;
			identificacaoPagamento = identificacaoPagamento + digitoVerificadorRefContaModulo10;
			
			identificacaoPagamento = identificacaoPagamento + "000";

		} 
		//Caso o tipo de pagamento seja referente a guia de pagamento (Im�vel)
		else if (tipoPagamento.intValue() == 4) {
			
			identificacaoPagamento = identificacaoPagamento + Util.adicionarZerosEsquedaNumero(3, "" + idLocalidade);
			identificacaoPagamento = identificacaoPagamento + Util.adicionarZerosEsquedaNumero(9, "" + matriculaImovel);
			
			//Identifica o tamanho da matr�cula do im�vel
			identificacaoPagamento = identificacaoPagamento + "1";
			
			identificacaoPagamento = identificacaoPagamento + (Util.adicionarZerosEsquedaNumero(4, idTipoDebito.toString()));
			
			identificacaoPagamento = identificacaoPagamento + anoEmissaoGuiaPagamento;
			
			identificacaoPagamento = identificacaoPagamento + "000";
			
		} 
		//Caso a tipo de pagamento seja referente a documento de cobran�a
		else if (tipoPagamento.intValue() == 5) {
			
			identificacaoPagamento = identificacaoPagamento + Util.adicionarZerosEsquedaNumero(3, "" + idLocalidade);
			identificacaoPagamento = identificacaoPagamento + Util.adicionarZerosEsquedaNumero(9, "" + matriculaImovel);
			identificacaoPagamento = identificacaoPagamento + (Util.adicionarZerosEsquedaNumero(9, sequencialDocumentoCobranca));
			identificacaoPagamento = identificacaoPagamento + (Util.adicionarZerosEsquedaNumero(2, idTipoDocumento.toString()));
			
			//Identifica o tamanho da matr�cula do im�vel
			identificacaoPagamento = identificacaoPagamento + "1";

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
		//Caso o tipo de pagamento seja referente a fatura do cliente respons�vel
		else if (tipoPagamento.intValue() == 7) {
			
			identificacaoPagamento = identificacaoPagamento + (Util.adicionarZerosEsquedaNumero(9, idCliente.toString()));
			
			identificacaoPagamento = identificacaoPagamento + "00";
			
			identificacaoPagamento = identificacaoPagamento + mesAnoReferenciaConta;
			identificacaoPagamento = identificacaoPagamento + digitoVerificadorRefContaModulo10;
			identificacaoPagamento = identificacaoPagamento + (Util.adicionarZerosEsquedaNumero(6, seqFaturaClienteResponsavel.toString()));
			
		} 
		//Caso a tipo de pagamento seja referente a documento de cobran�a cliente
		else if (tipoPagamento.intValue() == 8) {
			
			identificacaoPagamento = identificacaoPagamento + "000";
			
			identificacaoPagamento = identificacaoPagamento + (Util.adicionarZerosEsquedaNumero(8, idCliente.toString()));
			identificacaoPagamento = identificacaoPagamento + (Util.adicionarZerosEsquedaNumero(9, sequencialDocumentoCobranca));
			identificacaoPagamento = identificacaoPagamento + (Util.adicionarZerosEsquedaNumero(2, idTipoDocumento.toString()));
			
			identificacaoPagamento = identificacaoPagamento + "00";
		} 
		else if(tipoPagamento.intValue() == 1 || tipoPagamento.intValue() == 9){
			
			identificacaoPagamento = Util.adicionarZerosEsquedaNumeroTruncando(3,idLocalidade.toString());
			
			if(tipoPagamento.intValue() == 1){
				
				identificacaoPagamento = identificacaoPagamento + Util.adicionarZerosEsquedaNumeroTruncando(9,matriculaImovel.toString());
				
				identificacaoPagamento = identificacaoPagamento + Util.adicionarZerosEsquedaNumeroTruncando(9,idGuiaPagamento);
				
				//FIXO
				identificacaoPagamento = identificacaoPagamento + "00";
				
				//Identifica o tamanho da matr�cula do im�vel
				identificacaoPagamento = identificacaoPagamento + "1";
			}
			else if(tipoPagamento.intValue() == 9){
				
				identificacaoPagamento = identificacaoPagamento + Util.adicionarZerosEsquedaNumeroTruncando(9,idCliente.toString());
				
				identificacaoPagamento = identificacaoPagamento + Util.adicionarZerosEsquedaNumeroTruncando(9,idGuiaPagamento);
				
				//FIXO
				identificacaoPagamento = identificacaoPagamento + "00";
				
				//Identifica o tamanho da matr�cula do im�vel
				identificacaoPagamento = identificacaoPagamento + "1";
			}
				
		}

		// Retorna o identificador do pagamento formatado
		return identificacaoPagamento;
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
		
		
		super.distribuirDadosRegistroMovimentoArrecadadorPorTipoPagamento(registroHelperCodigoG,
		arrecadadorMovimentoItemHelper);
		
		//LEGADO - CAEMA
		//===============================================================================================================================
		if (registroHelperCodigoG.getRegistroHelperCodigoBarras()
			.getTipoPagamento().equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_NEGOCIACAO_A_VISTA_LEGADO_CAEMA.toString())){
			
			//Calcular Digito Verificador da Matricula (M�DULO 11)
			Integer matriculaImovelComDigito = null;
			
			String matriculaImovel = registroHelperCodigoG.getRegistroHelperCodigoBarras()
			.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento3();

			if (matriculaImovel != null) {
				
				int digitoModulo11 = Util.obterDigitoVerificadorModulo11(matriculaImovel);
				
				try {
					
					matriculaImovelComDigito = new Integer(matriculaImovel + digitoModulo11);
					
				} catch (Exception e) {
					matriculaImovelComDigito = 0;
				}
			}

			if (matriculaImovelComDigito == 0){
				arrecadadorMovimentoItemHelper
				.setIdentificacao(matriculaImovel);
			}
			else{
				arrecadadorMovimentoItemHelper
				.setIdentificacao(matriculaImovelComDigito.toString());
			}

			arrecadadorMovimentoItemHelper
			.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_NEGOCIACAO_A_VISTA_LEGADO_CAEMA);
		}
		
		else if (registroHelperCodigoG.getRegistroHelperCodigoBarras()
				.getTipoPagamento().equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_LEGADO_CAEMA.toString())){
			
			//Calcular Digito Verificador da Matricula (M�DULO 11)
			Integer matriculaImovelComDigito = null;
			
			String matriculaImovel = registroHelperCodigoG.getRegistroHelperCodigoBarras()
			.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento3();

			if (matriculaImovel != null) {
				
				int digitoModulo11 = Util.obterDigitoVerificadorModulo11(matriculaImovel);
				
				try {
					
					matriculaImovelComDigito = new Integer(matriculaImovel + digitoModulo11);
					
				} catch (Exception e) {
					matriculaImovelComDigito = 0;
				}
			}

			if (matriculaImovelComDigito == 0){
				arrecadadorMovimentoItemHelper
				.setIdentificacao(matriculaImovel);
			}
			else{
				arrecadadorMovimentoItemHelper
				.setIdentificacao(matriculaImovelComDigito.toString());
			}

			arrecadadorMovimentoItemHelper
			.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_GUIA_PAGAMENTO);
		}
		
		else if (registroHelperCodigoG.getRegistroHelperCodigoBarras()
				.getTipoPagamento().equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_FATURA_LEGADO_CAEMA.toString())){
			
			arrecadadorMovimentoItemHelper
			.setIdentificacao(registroHelperCodigoG.getRegistroHelperCodigoBarras()
			.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento3());

			arrecadadorMovimentoItemHelper
			.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_FATURA_CLIENTE_RESPONSAVEL);
		}
		
		else if (registroHelperCodigoG.getRegistroHelperCodigoBarras()
				.getTipoPagamento().equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA_LEGADO_CAEMA.toString())){
			
			//Calcular Digito Verificador da Matricula (M�DULO 11)
			Integer matriculaImovelComDigito = null;
			
			String matriculaImovel = registroHelperCodigoG.getRegistroHelperCodigoBarras()
			.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento3();

			if (matriculaImovel != null) {
				
				int digitoModulo11 = Util.obterDigitoVerificadorModulo11(matriculaImovel);
				
				try {
					
					matriculaImovelComDigito = new Integer(matriculaImovel + digitoModulo11);
					
				} catch (Exception e) {
					matriculaImovelComDigito = 0;
				}
			}

			if (matriculaImovelComDigito == 0){
				arrecadadorMovimentoItemHelper
				.setIdentificacao(matriculaImovel);
			}
			else{
				arrecadadorMovimentoItemHelper
				.setIdentificacao(matriculaImovelComDigito.toString());
			}

			arrecadadorMovimentoItemHelper
			.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_CONTA);
		}
		
		else if (registroHelperCodigoG.getRegistroHelperCodigoBarras()
				.getTipoPagamento().equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CAMPANHA_AVISO_DEBITO_LEGADO_CAEMA.toString())){
			
			//Calcular Digito Verificador da Matricula (M�DULO 11)
			Integer matriculaImovelComDigito = null;
			
			String matriculaImovel = registroHelperCodigoG.getRegistroHelperCodigoBarras()
			.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento3();

			if (matriculaImovel != null) {
				
				int digitoModulo11 = Util.obterDigitoVerificadorModulo11(matriculaImovel);
				
				try {
					
					matriculaImovelComDigito = new Integer(matriculaImovel + digitoModulo11);
					
				} catch (Exception e) {
					matriculaImovelComDigito = 0;
				}
			}

			if (matriculaImovelComDigito == 0){
				arrecadadorMovimentoItemHelper
				.setIdentificacao(matriculaImovel);
			}
			else{
				arrecadadorMovimentoItemHelper
				.setIdentificacao(matriculaImovelComDigito.toString());
			}
			
			if (registroHelperCodigoG.getRegistroHelperCodigoBarras()
				.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento5().equals("00")){
				
				arrecadadorMovimentoItemHelper
				.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_AVISO_DEBITO_LEGADO_CAEMA);
			}
			else{
				
				arrecadadorMovimentoItemHelper
				.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_CAMPANHA_LEGADO_CAEMA);
			}
			
		}

		
		//===============================================================================================================================
	}
	
	
	/**
	 * [UC0264] - Distribuir Dados do C�digo de Barras
	 *
	 * @author Raphael Rossiter
	 * @date 06/11/2008
	 *
	 * @param registroHelperCodigoBarrasTipoPagamento
	 * @param idPagamento
	 * @return RegistroHelperCodigoBarrasTipoPagamento
	 */
	public RegistroHelperCodigoBarrasTipoPagamento distribuirDadosCodigoBarrasPorTipoPagamento_GUIA_PAGAMENTO_IMOVEL(
			RegistroHelperCodigoBarrasTipoPagamento registroHelperCodigoBarrasTipoPagamento, String idPagamento) {
		
		//seta o c�digo da localidade
		registroHelperCodigoBarrasTipoPagamento.setIdPagamento1(idPagamento
				.substring(0, 3).trim());
		// seta a matr�cula do im�vel
		registroHelperCodigoBarrasTipoPagamento.setIdPagamento2(idPagamento
				.substring(3, 11).trim());
		// n�o est� sendo utilizado
		registroHelperCodigoBarrasTipoPagamento.setIdPagamento3(idPagamento
				.substring(11, 13).trim());
		// seta o c�digo do tipo do debito
		registroHelperCodigoBarrasTipoPagamento.setIdPagamento4(idPagamento
				.substring(13, 17).trim());
		// seta o ano da emiss�o da guia de pagamento(AAAA)
		registroHelperCodigoBarrasTipoPagamento.setIdPagamento5(idPagamento
				.substring(17, 21).trim());
		// n�o est� sendo utilizado
		registroHelperCodigoBarrasTipoPagamento.setIdPagamento6(idPagamento
				.substring(21, 24).trim());
		
		return registroHelperCodigoBarrasTipoPagamento;
	}
	
	/**
	 * [UC0264] - Distribuir Dados do C�digo de Barras
	 *
	 * @author Raphael Rossiter
	 * @date 06/11/2008
	 *
	 * @param registroHelperCodigoBarrasTipoPagamento
	 * @param idPagamento
	 * @return RegistroHelperCodigoBarrasTipoPagamento
	 */
	public RegistroHelperCodigoBarrasTipoPagamento distribuirDadosCodigoBarrasPorTipoPagamento_GUIA_PAGAMENTO_CLIENTE(
			RegistroHelperCodigoBarrasTipoPagamento registroHelperCodigoBarrasTipoPagamento, String idPagamento) {
		
		//seta o c�digo da localidade
		registroHelperCodigoBarrasTipoPagamento.setIdPagamento1(idPagamento
				.substring(0, 3).trim());
		// seta o id do cliente
		registroHelperCodigoBarrasTipoPagamento.setIdPagamento2(idPagamento
				.substring(3, 11).trim());
		// n�o est� sendo utilizado
		registroHelperCodigoBarrasTipoPagamento.setIdPagamento3(idPagamento
				.substring(11, 13).trim());
		// seta o c�digo do tipo do debito
		registroHelperCodigoBarrasTipoPagamento.setIdPagamento4(idPagamento
				.substring(13, 17).trim());
		// seta o ano da emiss�o da guia de pagamento(AAAA)
		registroHelperCodigoBarrasTipoPagamento.setIdPagamento5(idPagamento
				.substring(17, 21).trim());
		// n�o est� sendo utilizado
		registroHelperCodigoBarrasTipoPagamento.setIdPagamento6(idPagamento
				.substring(21, 24).trim());
		
		return registroHelperCodigoBarrasTipoPagamento;
	}
}
