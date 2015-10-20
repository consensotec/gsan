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
package gcom.gerencial.arrecadacao;

import gcom.batch.ControladorBatchLocal;
import gcom.batch.ControladorBatchLocalHome;
import gcom.batch.UnidadeProcessamento;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.ControladorImovelLocalHome;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.gerencial.arrecadacao.bean.ResumoArrecadacaoAguaEsgotoHelper;
import gcom.gerencial.arrecadacao.bean.ResumoArrecadacaoAguaEsgotoPorAnoHelper;
import gcom.gerencial.arrecadacao.pagamento.GEpocaPagamento;
import gcom.gerencial.arrecadacao.pagamento.GPagamentoSituacao;
import gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaPerfil;
import gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaSituacao;
import gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoPerfil;
import gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoSituacao;
import gcom.gerencial.cadastro.ControladorGerencialCadastroLocal;
import gcom.gerencial.cadastro.ControladorGerencialCadastroLocalHome;
import gcom.gerencial.cadastro.IRepositorioGerencialCadastro;
import gcom.gerencial.cadastro.RepositorioGerencialCadastroHBM;
import gcom.gerencial.cadastro.cliente.GClienteTipo;
import gcom.gerencial.cadastro.cliente.GEsferaPoder;
import gcom.gerencial.cadastro.imovel.GCategoria;
import gcom.gerencial.cadastro.imovel.GImovelPerfil;
import gcom.gerencial.cadastro.imovel.GSubcategoria;
import gcom.gerencial.cadastro.localidade.GGerenciaRegional;
import gcom.gerencial.cadastro.localidade.GLocalidade;
import gcom.gerencial.cadastro.localidade.GQuadra;
import gcom.gerencial.cadastro.localidade.GSetorComercial;
import gcom.gerencial.cadastro.localidade.GUnidadeNegocio;
import gcom.gerencial.cobranca.GDocumentoTipo;
import gcom.gerencial.micromedicao.GRota;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.util.ConstantesJNDI;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ControladorUtilLocalHome;
import gcom.util.ErroRepositorioException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * 
 * 
 * @author Ivan S�rgio
 * @created 11/05/2007
 */
public class ControladorGerencialArrecadacaoSEJB implements SessionBean {
	private static final long serialVersionUID = 1L;
	
	private IRepositorioGerencialArrecadacao repositorioGerencialArrecadacao = null;
	private IRepositorioGerencialCadastro repositorioGerencialCadastro = null;
	
	SessionContext sessionContext;

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @exception CreateException
	 *                Descri��o da exce��o
	 */
	public void ejbCreate() throws CreateException {
		repositorioGerencialArrecadacao = RepositorioGerencialArrecadacaoHBM.getInstancia();
		repositorioGerencialCadastro = RepositorioGerencialCadastroHBM.getInstancia();
	}

	/**
	 * Retorna a interface remota de ControladorImovel
	 * 
	 * @return A interface remota do controlador de par�metro
	 */
	private ControladorImovelLocal getControladorImovel() {
		ControladorImovelLocalHome localHome = null;
		ControladorImovelLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorImovelLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_IMOVEL_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas �
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	
	/**
	 * Retorna o valor de controladorUtil
	 * 
	 * @return O valor de controladorUtil
	 */
	private ControladorUtilLocal getControladorUtil() {

		ControladorUtilLocalHome localHome = null;
		ControladorUtilLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorUtilLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_UTIL_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas �
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}

	/**
	 * < <Descri��o do m�todo>>
	 */
	public void ejbRemove() {
	}

	/**
	 * < <Descri��o do m�todo>>
	 */
	public void ejbActivate() {
	}

	/**
	 * < <Descri��o do m�todo>>
	 */
	public void ejbPassivate() {
	}

	/**
	 * Seta o valor de sessionContext
	 * 
	 * @param sessionContext
	 *            O novo valor de sessionContext
	 */
	public void setSessionContext(SessionContext sessionContext) {
		this.sessionContext = sessionContext;
	}

	private ControladorBatchLocal getControladorBatch() {
		ControladorBatchLocalHome localHome = null;
		ControladorBatchLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorBatchLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_BATCH_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}
	
	
	/**
	 * M�todo que gera o resumo da Arrecadacao
	 *
	 * [UC0551] - Gerar Resumo da Arrecadacao Agua/Esgoto
	 * 			- Gerar Resumo da Arrecadacao Outros
	 * 			- Gerar Resumo da Arrecadacao Credito
	 *
	 * @author Ivan S�rgio
	 * @date 17/05/2007, 29/06/2007, 17/10/2007, 12/11/2007, 10/06/2008,
	 * 		 07/10/2008 - Foi detectado uma falha no modo de se obter o indicador de Contas no mes.
	 */
	public void gerarResumoArrecadacao(
			int idLocalidade, 
			int anoMesReferenciaArrecadacao,
			int idFuncionalidadeIniciada) throws ControladorException {
		
		/**********************************************************
		 * Inicio do Resumo Arrecadacao Agua Esgoto
		 *********************************************************/
		int idUnidadeIniciada = 0;

		// -------------------------
		// Registrar o in�cio do processamento da Unidade de
		// Processamento do Batch
		// -------------------------
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.LOCALIDADE, idLocalidade);
		
		try {
			//System.out.print("=====> LOCALIDADE : " + idLocalidade);
			
			List imoveisResumoArrecadacaoAguaEsgoto = this.repositorioGerencialArrecadacao
					.getImoveisResumoArrecadacaoAguaEsgoto(idLocalidade, anoMesReferenciaArrecadacao);
			
			/**********************************************************************************
			 * Recupera os Valores Nao Identificados para os casos de Conta, Guia de Pagamento
			 * e Debito a Cobrar iguais a Null
			 **********************************************************************************/
			List imoveisResumoArrecadacaoAguaEsgotoValorNaoIdentificado = this.repositorioGerencialArrecadacao
				.getImoveisResumoArrecadacaoAguaEsgotoValorNaoIdentificado(
						idLocalidade, anoMesReferenciaArrecadacao);
			/**********************************************************************************/
			
			/**********************************************************************************
			* Junta as Partes
			***********************************************************************************/
			if (imoveisResumoArrecadacaoAguaEsgotoValorNaoIdentificado != null &&
					!imoveisResumoArrecadacaoAguaEsgotoValorNaoIdentificado.isEmpty()) {
				
				imoveisResumoArrecadacaoAguaEsgoto.addAll(imoveisResumoArrecadacaoAguaEsgotoValorNaoIdentificado);
			}
			/**********************************************************************************/

			List<ResumoArrecadacaoAguaEsgotoHelper> listaSimplificada = new ArrayList();
			
			// Declaracao dos Objetos Usados
			Object obj = null;
			Object[] retorno = null;
			ResumoArrecadacaoAguaEsgotoHelper helper = null;
			BigDecimal valorAgua = null;
			BigDecimal valorEsgoto = null;
			BigDecimal valorNaoIdentificado = null;
			BigDecimal valorImpostos = null;
			short indicadorHidrometro;
			Integer anoMesReferenciaDocumento = null;
			int posicao = 0;
			ResumoArrecadacaoAguaEsgotoHelper jaCadastrado = null;
			
			//System.out.print("=====> TOTAL : " + imoveisResumoArrecadacaoAguaEsgoto.size());
			
			//FS0001 - Verificar existencia de dados para o ano/mes referencia informado
	   		getControladorGerencialCadastro().excluirResumoGerencialC(
	   				getControladorUtil().pesquisarParametrosDoSistema().getAnoMesArrecadacao(),
	   				"arrecadacao.un_resumo_arrecadacao",
	   				"rear_amreferencia",
	   				"loca_id",
	   				idLocalidade);
			
			// pra cada objeto obter a categoria
			for (int i = 0; i < imoveisResumoArrecadacaoAguaEsgoto.size(); i++) {
				obj = imoveisResumoArrecadacaoAguaEsgoto.get(i);

				if (obj instanceof Object[]) {
					retorno = (Object[]) obj;
					
					// Montamos um objeto de resumo, com as informacoes do retorno
					helper = new ResumoArrecadacaoAguaEsgotoHelper(
							(Integer) retorno[1],  // Gerencia Regional
							(Integer) retorno[2],  // Unidade de Negocio
							(Integer) retorno[3],  // Codigo do Elo
							(Integer) retorno[4],  // Localidade
							(Integer) retorno[5],  // Setor Comercial
							(Integer) retorno[6],  // Rota
							(Integer) retorno[7],  // Quadra
							(Integer) retorno[8],  // Perfil Imovel
							(Integer) retorno[9],  // Situa��o Liga��o �gua
							(Integer) retorno[10], // Situa��o Liga��o Esgoto
							(Integer) retorno[11], // Perfil da Liga��o da �gua
							(Integer) retorno[12], // Perfil da Liga��o do Esgoto
							(Integer) retorno[13], // Tipo Documento
							(Integer) retorno[14], // Situa��o do Pagamento
							(Integer) retorno[15], // Indicador de Contas Recebidas (SER� VERIFICADO EM verificarIndicadorContasRecebidasNoMes)
							(Integer) retorno[16], // �poca do Pagamento
							(Integer) retorno[17], // Codigo do Setor Comercial
							(Integer) retorno[18], // N�mero da Quadra
							(Integer) retorno[22], // Forma de Arrecadacao
							(Integer) retorno[23]);// Agenete Arrecadador
					
					Integer idImovel = (Integer) retorno[0]; // Codigo do imovel que esta sendo processado
					
					if (idImovel != null) {
						// Pesquisamos a esfera de poder do cliente responsavel
						helper.setIdEsferaPoder(repositorioGerencialCadastro.pesquisarEsferaPoderClienteResponsavelImovel(idImovel));
						// Pesquisamos o tipo de cliente responsavel do imovel
						helper.setIdTipoCliente(repositorioGerencialCadastro.pesquisarTipoClienteClienteResponsavelImovel(idImovel));

						// pesquisando a categoria
						// [UC0306] - Obtter principal categoria do im�vel
						Categoria categoria = null;
						categoria = this.getControladorImovel().obterPrincipalCategoriaImovel(idImovel);
						
						if (categoria != null) {
							helper.setIdCategoria(categoria.getId());

							// Pesquisando a principal subcategoria
							ImovelSubcategoria subcategoria =
								this.getControladorImovel().obterPrincipalSubcategoria(categoria.getId(), idImovel);

							if (subcategoria != null){
								helper.setIdSubCategoria(subcategoria.getComp_id().getSubcategoria().getId());
							}
						}
						
						// Verifica Indicador de Hidrometro para o imovel
						// [UC0307] - Obter Indicador de Exist�ncia de Hidr�metro
						String indicadorHidrometroString = new Integer(this.getControladorImovel().
								obterIndicadorExistenciaHidrometroImovel(idImovel)).toString();
						indicadorHidrometro = new Short(indicadorHidrometroString);
						helper.setIndicadorHidrometro(indicadorHidrometro);
					}else {
						// Esfera de Poder
						helper.setIdEsferaPoder(null);
						// Tipo de Cliente
						helper.setIdTipoCliente(25);
						// Categoria
						helper.setIdCategoria(1);
						// Sub-Categoria
						helper.setIdSubCategoria(10);
						// Indicador de Hidrometro
						helper.setIndicadorHidrometro(new Short("2"));
					}
					
					valorAgua = new BigDecimal(0);
					if (retorno[19] != null) {
						if (!retorno[19].equals(0)) {
							valorAgua = (BigDecimal) retorno[19];
						}
					}
					
					valorEsgoto = new BigDecimal(0);
					if (retorno[20] != null) {
						if (!retorno[20].equals(0)) {
							valorEsgoto = (BigDecimal) retorno[20];	
						}
					}
					
					valorNaoIdentificado = new BigDecimal(0);
					if (retorno[21] != null) {
						valorNaoIdentificado = (BigDecimal) retorno[21];
					}
					
					valorImpostos = new BigDecimal(0);
					if (retorno[24] != null) {
						if (!retorno[24].equals(0)) {
							valorImpostos = (BigDecimal) retorno[24];
						}
					}
					
					anoMesReferenciaDocumento = null;
					if (retorno[25] != null) {
						anoMesReferenciaDocumento = (Integer) retorno[25];
						helper.setAnoMesReferenciaDocumento(anoMesReferenciaDocumento);
					}
					
					//***********************************************************************
					// Verifica Epoca de Pagamento
					//***********************************************************************
					Integer epocaPagamento = null;
					String dataPagamento = retorno[26].toString();
					String dataVencimentoConta = "";
					if (retorno[27] != null) {
						if (!retorno[27].equals("")) {
							dataVencimentoConta = retorno[27].toString();
						}
					}
					
					if (helper.getIdEpocaPagamento() == 99) {
						// Verifica Epoca de Pagamento para CONTA
						epocaPagamento = retornaValorEpocaPagamento(dataPagamento, dataVencimentoConta);
						helper.setIdEpocaPagamento(epocaPagamento);
					}
					//***********************************************************************
					
					//***********************************************************************
					// Verifica o Indicador de Contas Recebidas no Mes
					//***********************************************************************
					helper.setIdIndicadorContasRecebidas(this.verificarIndicadorContasRecebidasNoMes(
							anoMesReferenciaDocumento, dataPagamento));
					//***********************************************************************
					
					//***********************************************************************
					// Verifica os Casos em que recupera os Primeiros Registros
					//***********************************************************************
					Object[] dadosPagamentoSemContaGuiaDebito = null; 
					
					if (helper.getIdSetorComercial().equals(0)) {
						dadosPagamentoSemContaGuiaDebito = (Object[]) repositorioGerencialArrecadacao
							.pesquisarDadosPagamentoSemContaGuiaDebito(helper.getIdLocalidade());
						
						// Setor Comercial
						helper.setIdSetorComercial((Integer) dadosPagamentoSemContaGuiaDebito[0]);
						// Codigo Setor Comercial
						helper.setIdCodigoSetorComercial((Integer) dadosPagamentoSemContaGuiaDebito[1]);
						// Rota
						helper.setIdRota((Integer) dadosPagamentoSemContaGuiaDebito[2]);
						// Quadra
						helper.setIdQuadra((Integer) dadosPagamentoSemContaGuiaDebito[3]);
						// Numero Quadra
						helper.setIdNumeroQuadra((Integer) dadosPagamentoSemContaGuiaDebito[4]);
					}
					
					// Codigo da Rota
					helper.setCodigoRota(this.pesquisaCodigoRota(helper.getIdRota()).intValue());
					//***********************************************************************
					
					
					// Se ja existe um objeto igual a ele entao soma os
					// valores Agua, Esgoto e N�o Identificado.
					// Um objeto eh igual ao outro se ele tem todos as
					// informacoes de quebra iguais.
					if (listaSimplificada.contains(helper)) {
						posicao = listaSimplificada.indexOf(helper);
						jaCadastrado = (ResumoArrecadacaoAguaEsgotoHelper) listaSimplificada.get(posicao);

						// Somatorio de Valor Agua
						jaCadastrado.setValorAgua(jaCadastrado.getValorAgua().add(valorAgua));
						
						// Somatorio de Valor Esgoto
						jaCadastrado.setValorEsgoto(jaCadastrado.getValorEsgoto().add(valorEsgoto));
						
						// Somatorio de Valor Nao Identificado
						jaCadastrado.setValorNaoIdentificado(jaCadastrado.getValorNaoIdentificado()
								.add(valorNaoIdentificado));
						
						// Somatorio de Valor Impostos
						jaCadastrado.setValorImpostos(jaCadastrado.getValorImpostos()
								.add(valorImpostos));
						
						// Incrementamos a Quantidade de Contas
						jaCadastrado.setQuantidadeContas(jaCadastrado.getQuantidadeContas().intValue() + 1);
						
						// Incrementamos a Quantidade de Pagamentos
						jaCadastrado.setQuantidadePagamentos(jaCadastrado.getQuantidadePagamentos().intValue() + 1);
						
					} else {
						// Incluimos o Valor Agua 
						helper.setValorAgua(helper.getValorAgua().add(valorAgua));
						
						// Incluimos o Valor Esgoto 
						helper.setValorEsgoto(helper.getValorEsgoto().add(valorEsgoto));
						
						// Incluimos o Valor Nao Identificado 
						helper.setValorNaoIdentificado(helper.getValorNaoIdentificado()
								.add(valorNaoIdentificado));
						
						// Incluimos o Valor Impostos 
						helper.setValorImpostos(helper.getValorImpostos()
								.add(valorImpostos));
						
						// Incrementamos a Quantidade de Contas
						helper.setQuantidadeContas(helper.getQuantidadeContas().intValue() + 1);
						
						// Incrementamos a Quantidade de Pagamentos
						helper.setQuantidadePagamentos(helper.getQuantidadePagamentos().intValue() + 1);
						
						listaSimplificada.add(helper);
					}
				}
			}
			
			imoveisResumoArrecadacaoAguaEsgoto.clear();
			imoveisResumoArrecadacaoAguaEsgoto = null;
			/**
			 * para todas as ResumoArrecadacaoAguaEsgotoHelper cria
			 * UnResumoArrecadacao
			 */
			// Declaracao dos Objetos Usados
			int anoMesReferencia = 0;
			int codigoSetorComercial = 0;
			int numeroQuadra = 0;
			int quantidadeContas = 0;
			int quantidadePagamentos = 0;
			short indicadorRecebidasNomes = 0;
			BigDecimal volumeAgua = null;
			BigDecimal volumeEsgoto = null;
			Date ultimaAlteracao = null;
			BigDecimal volumeNaoIdentificado = null;
			
			GSubcategoria subCategoria = null;
			GClienteTipo clienteTipo = null;
			GLigacaoAguaSituacao ligacaoAguaSituacao = null;
			GUnidadeNegocio unidadeNegocio = null;
			GLocalidade localidade = null;
			GLocalidade elo = null;
			GQuadra quadra = null;
			GLigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;
			GLigacaoEsgotoPerfil ligacaoEsgotoPerfil = null;
			GGerenciaRegional gerenciaRegional = null;
			GSetorComercial setorComercial = null;
			GDocumentoTipo documentoTipo = null;
			GPagamentoSituacao pagamentoSituacao = null;
			GLigacaoAguaPerfil ligacaoAguaPerfil = null;
			GEpocaPagamento epocaPagamento = null;
			GEsferaPoder esferaPoder = null;
			GCategoria Gcategoria = null;
			GImovelPerfil imovelPerfil = null;
			GRota rota = null;
			GArrecadacaoForma formaArrecadacao = null;
			GArrecadador agenteArrecadador = null;
			UnResumoArrecadacao resumoArrecadacaoAguaEsgoto = null;
			
			// Preenche os Objetos com valor 0 que ser�o usados em Credito e Outros
			Integer creditoOrigem = null;
			Integer itemContabelOutros = null;
			Integer itemContabelCredito = null;
			Integer financiamentoTipo = null;
			
			for (int i = 0; i < listaSimplificada.size(); i++) {
				helper = (ResumoArrecadacaoAguaEsgotoHelper) listaSimplificada.get(i);

				// Montamos todo o agrupamento necessario
				
				// Mes ano de referencia
				anoMesReferencia = anoMesReferenciaArrecadacao;
				
				// Codigo Setor Comercial
				if (helper.getIdSetorComercial() != null) {
					codigoSetorComercial = helper.getIdCodigoSetorComercial();
				}
				
				// Numero da quadra
				if (helper.getIdNumeroQuadra() != null) {
					numeroQuadra = helper.getIdNumeroQuadra();
				}
				
				// Quantidades de Conta
				if (helper.getQuantidadeContas() != null) {
					quantidadeContas = helper.getQuantidadeContas();
				}
				
				// Quantidades de Pagamentos
				if (helper.getQuantidadePagamentos() != null) {
					quantidadePagamentos = helper.getQuantidadePagamentos();
				}
				
				// Indicador de Recebidas no Mes
				if (helper.getIdIndicadorContasRecebidas() != null) {
					indicadorRecebidasNomes = helper.getIdIndicadorContasRecebidas().shortValue();
				}
				
				// Volume Agua
				volumeAgua = new BigDecimal(0);
				if (helper.getValorAgua() != null) {
					volumeAgua = helper.getValorAgua();
				}
				
				// Volume Esgoto
				volumeEsgoto = new BigDecimal(0);
				if (helper.getValorAgua() != null) {
					volumeEsgoto = helper.getValorEsgoto();
				}
				
				// Ultima Alteracao
				ultimaAlteracao = new Date();
				
				// Volume Nao Identificado
				volumeNaoIdentificado = new BigDecimal(0);
				if (helper.getValorNaoIdentificado() != null) {
					volumeNaoIdentificado = helper.getValorNaoIdentificado();
				}
				
				// Principal SubCategoria da Principal Categoria do Imovel
				if (helper.getIdSubCategoria() != null){
					subCategoria = new GSubcategoria();
					subCategoria.setId(helper.getIdSubCategoria());
				}
				
				// Tipo do Cliente Respons�vel
				if (helper.getIdTipoCliente() != null) {
					clienteTipo = new GClienteTipo();
					clienteTipo.setId(helper.getIdTipoCliente());
				}
				
				// Situacao da Liga��o da �gua
				if (helper.getIdSituacaoLigacaoAgua() != null) {
					ligacaoAguaSituacao = new GLigacaoAguaSituacao();
					ligacaoAguaSituacao.setId(helper.getIdSituacaoLigacaoAgua());
				}
				
				// Unidade de Negocio
				if (helper.getIdUnidadeNegocio() != null) {
					unidadeNegocio = new GUnidadeNegocio();
					unidadeNegocio.setId(helper.getIdUnidadeNegocio());
				}
				
				// Localidade
				if (helper.getIdLocalidade() != null){
					localidade = new GLocalidade();
					localidade.setId(helper.getIdLocalidade());
				}
				
				// Elo
				if (helper.getIdCodigoElo() != null) {
					elo = new GLocalidade();
					elo.setId(helper.getIdCodigoElo());
				}
				
				// Quadra
				if (helper.getIdQuadra() != null) {
					quadra = new GQuadra();
					quadra.setId(helper.getIdQuadra());
				}
				
				// Situa��o da Liga��o do Esgoto
				if (helper.getIdSituacaoLigacaoEsgoto() != null){
					ligacaoEsgotoSituacao = new GLigacaoEsgotoSituacao();
					ligacaoEsgotoSituacao.setId(helper.getIdSituacaoLigacaoEsgoto());
				}
				
				// Perfil da Liga��o do Esgoto
				if (helper.getIdPerfilLigacaoEsgoto() != null){
					ligacaoEsgotoPerfil = new GLigacaoEsgotoPerfil();
					ligacaoEsgotoPerfil.setId(helper.getIdPerfilLigacaoEsgoto());
				}
				
				// Gerencia Regional
				if (helper.getIdGerenciaRegional() != null) {
					gerenciaRegional = new GGerenciaRegional();
					gerenciaRegional.setId(helper.getIdGerenciaRegional());
				}
				
				// Setor comercial
				if (helper.getIdSetorComercial() != null) {
					setorComercial = new GSetorComercial();
					setorComercial.setId(helper.getIdSetorComercial());
				}
				
				// Tipo do Documento
				if (helper.getIdTipoDocumento() != null) {
					documentoTipo = new GDocumentoTipo();
					documentoTipo.setId(helper.getIdTipoDocumento());
				}
				
				// Situacao do Pagamento
				if (helper.getIdSituacaoPagamento() != null) {
					pagamentoSituacao = new GPagamentoSituacao();
					pagamentoSituacao.setId(helper.getIdSituacaoPagamento());
				}
				
				// Perfil da Liga��o da �gua
				if (helper.getIdPerfilLigacaoAgua() != null){
					ligacaoAguaPerfil = new GLigacaoAguaPerfil();
					ligacaoAguaPerfil.setId(helper.getIdPerfilLigacaoAgua());
				}
				
				// Epoca do Pagamento
				if (helper.getIdEpocaPagamento() != null) {
					epocaPagamento = new GEpocaPagamento();
					epocaPagamento.setId(helper.getIdEpocaPagamento());
				}
				
				// Esfera de Poder
				if (helper.getIdEsferaPoder() != null){
					esferaPoder = new GEsferaPoder();
					esferaPoder.setId(helper.getIdEsferaPoder());
				}
				
				// Principal Categoria do Imovel
				if (helper.getIdCategoria() != null){
					Gcategoria = new GCategoria();
					Gcategoria.setId(helper.getIdCategoria());
				}
				
				// Perfil do Imovel
				if (helper.getIdPerfilImovel() != null) {
					imovelPerfil = new GImovelPerfil();
					imovelPerfil.setId(helper.getIdPerfilImovel());
				}
				
				// Rota
				if (helper.getIdRota() != null) {
					rota = new GRota();
					rota.setId(helper.getIdRota());
				}
				
				// Forma Arrecadacao
				if (helper.getIdFormaArrecadacao() != null) {
					formaArrecadacao = new GArrecadacaoForma();
					formaArrecadacao.setId(helper.getIdFormaArrecadacao());
				}
				
				// Agente Arrecadador
				if (helper.getIdAgenteArrecadador() != null) {
					agenteArrecadador = new GArrecadador();
					agenteArrecadador.setId(helper.getIdAgenteArrecadador());
				}
				
				// Valor Impostos
				valorImpostos = new BigDecimal(0);
				if (helper.getValorImpostos() != null) {
					valorImpostos = helper.getValorImpostos();
				}
				
				// Indicador Hidrometro
				indicadorHidrometro = helper.getIndicadorHidrometro();
				
				// Ano/Mes Referencia Documento
				anoMesReferenciaDocumento = null;
				if (helper.getAnoMesReferenciaDocumento() != null) {
					anoMesReferenciaDocumento = helper.getAnoMesReferenciaDocumento();
				}
				
				// Criamos um resumo de Arrecadacao Agua/Esgoto
				resumoArrecadacaoAguaEsgoto = new UnResumoArrecadacao(
						anoMesReferencia , codigoSetorComercial    , numeroQuadra,
						quantidadeContas , indicadorRecebidasNomes , volumeAgua,
						volumeEsgoto     , ultimaAlteracao         , volumeNaoIdentificado,
						subCategoria     , clienteTipo             , ligacaoAguaSituacao,
						unidadeNegocio   , localidade              , elo,
						quadra           , ligacaoEsgotoSituacao   , ligacaoEsgotoPerfil,
						gerenciaRegional , setorComercial          , documentoTipo,
						pagamentoSituacao, ligacaoAguaPerfil       , epocaPagamento,
						esferaPoder      , Gcategoria              , imovelPerfil,
						rota             , valorImpostos           , indicadorHidrometro,
						anoMesReferenciaDocumento);
				
				resumoArrecadacaoAguaEsgoto.setGerArrecadacaoForma(formaArrecadacao);
				resumoArrecadacaoAguaEsgoto.setGerArrecadador(agenteArrecadador);
				
				// Preenche os Objetos com valor 0 que ser�o usados em Credito e Outros
				creditoOrigem = new Integer(0);
				itemContabelOutros = new Integer(0);
				itemContabelCredito = new Integer(0);
				financiamentoTipo = new Integer(0);
				
				resumoArrecadacaoAguaEsgoto.setCreditoOrigemIdCredito(creditoOrigem);
				resumoArrecadacaoAguaEsgoto.setLancamentoItemIdCredito(itemContabelCredito);
				resumoArrecadacaoAguaEsgoto.setValorDocumentosRecebidosCredito(new BigDecimal(0));
				
				resumoArrecadacaoAguaEsgoto.setLancamentoItemIdOutros(itemContabelOutros);
				resumoArrecadacaoAguaEsgoto.setFinanciamentoTipoIdOutros(financiamentoTipo);
				resumoArrecadacaoAguaEsgoto.setValorDocumentosRecebidosOutros (new BigDecimal(0));
				
				resumoArrecadacaoAguaEsgoto.setQuantidadePagamentos(quantidadePagamentos);
				
				// Insere em UN_RESUMO_ARRECADACAO_AGUA_ESGOTO
				//System.out.print("=====> Inserindo Resumo Arrecadacao <=====");
				this.getControladorBatch().inserirObjetoParaBatchGerencial(resumoArrecadacaoAguaEsgoto);
				
				helper = null;
				resumoArrecadacaoAguaEsgoto = null;
			}
			//System.out.print("=================> FIM do Resumo Arrecadacao LOCALIDADE: " + idLocalidade + " <=======================================");
			
			/**********************************************************************************
			 * INICIO DO RESUMO ARRECADACAO OUTROS
			 **********************************************************************************/
			this.gerarResumoArrecadacaoOutros(idLocalidade, anoMesReferenciaArrecadacao);
			/**********************************************************************************/
			
			/**********************************************************************************
			 * INICIO DO RESUMO ARRECADACAO CREDITOS
			 **********************************************************************************/
			this.gerarResumoArrecadacaoCreditos(idLocalidade, anoMesReferenciaArrecadacao);
			/**********************************************************************************/
			
			/**********************************************************************************
			 * INICIO DO RESUMO ARRECADACAO DEVOLUCAO
			 **********************************************************************************/
			this.gerarResumoArrecadacaoDevolucao(idLocalidade, anoMesReferenciaArrecadacao);
			/**********************************************************************************/
			
			// --------------------------------------------------------
			//
			// Registrar o fim da execu��o da Unidade de Processamento
			//
			// --------------------------------------------------------

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);
		} catch (Exception ex) {
			// Este catch serve para interceptar qualquer exce��o que o processo
			// batch venha a lan�ar e garantir que a unidade de processamento do
			// batch ser� atualizada com o erro ocorrido
			System.out.println("*********** Resumo da Arrecadacao ****************************************");
			System.out.println("ERROR NA LOCALIDADE -> " + idLocalidade);
			System.out.println("**************************************************************************");
			ex.printStackTrace();
			// sessionContext.setRollbackOnly();
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex, idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}//gerarResumoArrecadacao
	
	
	/**
	 * @author Ivan S�rgio
	 * @date 09/01/2008
	 * 		 07/10/2008 - Foi detectado uma falha no modo de se obter o indicador de Contas no mes;
	 * 					  Retirada a condicao de caso o fntp_id = null desconsiderar o Pagamento;
	 *
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @throws ControladorException
	 */
	public void gerarResumoArrecadacaoOutros(
			int idLocalidade, 
			int anoMesReferenciaArrecadacao) throws ControladorException {
		
		try {
			List imoveisResumoArrecadacaoOutros = new ArrayList();
			List imoveisResumoArrecadacaoOutrosConta = new ArrayList();
			List imoveisResumoArrecadacaoOutrosGuiaPagamento = new ArrayList();
			List imoveisResumoArrecadacaoOutrosDebitoACobrar = new ArrayList();
			
			// Usado para calcular os Valor N�o Identificado
			Integer idPagamento = 0;
			Integer idPagamentoAnterior = 0;
			BigDecimal valorNaoIdentificado = null;
			
			//**************************************************************************************
			// RESUMO ARRECADACAO OUTROS - CONTA
			//**************************************************************************************
			imoveisResumoArrecadacaoOutrosConta = this.repositorioGerencialArrecadacao
					.getImoveisResumoArrecadacaoOutrosConta(
							idLocalidade, anoMesReferenciaArrecadacao);
			//**************************************************************************************
			// RESUMO ARRECADACAO OUTROS - GUIA PAGAMENTO
			//**************************************************************************************
			imoveisResumoArrecadacaoOutrosGuiaPagamento = this.repositorioGerencialArrecadacao
					.getImoveisResumoArrecadacaoOutrosGuiaPagamento(
							idLocalidade, anoMesReferenciaArrecadacao);
			//**************************************************************************************
			// RESUMO ARRECADACAO OUTROS - DEBITO A COBRAR
			//**************************************************************************************
			imoveisResumoArrecadacaoOutrosDebitoACobrar = this.repositorioGerencialArrecadacao
					.getImoveisResumoArrecadacaoOutrosDebitoACobrar(
							idLocalidade, anoMesReferenciaArrecadacao);
			//**************************************************************************************
			
			//**************************************************************************************
			//** Junta os 3 resultados *************************************************************
			if (imoveisResumoArrecadacaoOutrosConta != null &&
					!imoveisResumoArrecadacaoOutrosConta.isEmpty()) {
				imoveisResumoArrecadacaoOutros.addAll(imoveisResumoArrecadacaoOutrosConta);
			}
			
			if (imoveisResumoArrecadacaoOutrosGuiaPagamento != null &&
					!imoveisResumoArrecadacaoOutrosGuiaPagamento.isEmpty()) {
				imoveisResumoArrecadacaoOutros.addAll(imoveisResumoArrecadacaoOutrosGuiaPagamento);
			}
			
			if (imoveisResumoArrecadacaoOutrosDebitoACobrar != null &&
					!imoveisResumoArrecadacaoOutrosDebitoACobrar.isEmpty()) {
				imoveisResumoArrecadacaoOutros.addAll(imoveisResumoArrecadacaoOutrosDebitoACobrar);
			}
			//**************************************************************************************

			List<ResumoArrecadacaoAguaEsgotoHelper> listaSimplificada = new ArrayList();
			
			// Declaracao dos Objetos Usados
			Object obj = null;
			Object[] retorno = null;
			ResumoArrecadacaoAguaEsgotoHelper helper = null;
			BigDecimal valorImpostos = null;
			short indicadorHidrometro;
			Integer anoMesReferenciaDocumento = null;
			int posicao = 0;
			ResumoArrecadacaoAguaEsgotoHelper jaCadastrado = null;
			
			for (int i = 0; i < imoveisResumoArrecadacaoOutros.size(); i++) {
				obj = imoveisResumoArrecadacaoOutros.get(i);
				
				if (obj instanceof Object[]) {
					retorno = (Object[]) obj;
					
					// Se o Tipo de Financiamento for igual a NULL, despresar o Pagamento
					//if (retorno[25] != null) {
						
						// Montamos um objeto de resumo, com as informacoes do retorno
						helper = new ResumoArrecadacaoAguaEsgotoHelper(
								(Integer) retorno[1],  // Gerencia Regional
								(Integer) retorno[2],  // Unidade de Negocio
								(Integer) retorno[3],  // Codigo do Elo
								(Integer) retorno[4],  // Localidade
								(Integer) retorno[5],  // Setor Comercial
								(Integer) retorno[6],  // Rota
								(Integer) retorno[7],  // Quadra
								(Integer) retorno[8],  // Perfil Imovel
								(Integer) retorno[9],  // Situa��o Liga��o �gua
								(Integer) retorno[10], // Situa��o Liga��o Esgoto
								(Integer) retorno[11], // Perfil da Liga��o da �gua
								(Integer) retorno[12], // Perfil da Liga��o do Esgoto
								(Integer) retorno[13], // Tipo Documento
								(Integer) retorno[14], // Situa��o do Pagamento
								(Integer) retorno[15], // Indicador de Contas Recebidas
								(Integer) retorno[16], // �poca do Pagamento
								(Integer) retorno[17], // Codigo do Setor Comercial
								(Integer) retorno[18], // N�mero da Quadra
								(Integer) retorno[19], // Forma de Arrecadacao
								(Integer) retorno[20]);// Agente Arrecadador

						Integer idImovel = (Integer) retorno[0]; // Codigo do imovel que esta sendo processado
						
						if (idImovel != null) {
							// Pesquisamos a esfera de poder do cliente responsavel
							helper.setIdEsferaPoder(repositorioGerencialCadastro.pesquisarEsferaPoderClienteResponsavelImovel(idImovel));
							// Pesquisamos o tipo de cliente responsavel do imovel
							helper.setIdTipoCliente(repositorioGerencialCadastro.pesquisarTipoClienteClienteResponsavelImovel(idImovel));

							// pesquisando a categoria
							// [UC0306] - Obtter principal categoria do im�vel
							Categoria categoria = null;
							categoria = this.getControladorImovel().obterPrincipalCategoriaImovel(idImovel);

							if (categoria != null) {
								helper.setIdCategoria(categoria.getId());

								// Pesquisando a principal subcategoria
								ImovelSubcategoria subcategoria =
									this.getControladorImovel().obterPrincipalSubcategoria(categoria.getId(), idImovel);

								if (subcategoria != null){
									helper.setIdSubCategoria(subcategoria.getComp_id().getSubcategoria().getId());
								}
							}
							
							// Verifica Indicador de Hidrometro para o imovel
							// [UC0307] - Obter Indicador de Exist�ncia de Hidr�metro
							String indicadorHidrometroString = new Integer(this.getControladorImovel().
									obterIndicadorExistenciaHidrometroImovel(idImovel)).toString();
							indicadorHidrometro = new Short(indicadorHidrometroString);
							helper.setIndicadorHidrometro(indicadorHidrometro);
						}else {
							// Esfera de Poder
							helper.setIdEsferaPoder(null);
							// Tipo de Cliente
							helper.setIdTipoCliente(25);
							// Categoria
							helper.setIdCategoria(1);
							// Sub-Categoria
							helper.setIdSubCategoria(10);
							// Indicador de Hidrometro
							helper.setIndicadorHidrometro(new Short("2"));
						}
						
						anoMesReferenciaDocumento = null;
						if (retorno[21] != null) {
							anoMesReferenciaDocumento = (Integer) retorno[21];
							helper.setAnoMesReferenciaDocumento(anoMesReferenciaDocumento);
						}
						
						// Verifica Epoca de Pagamento
						Integer epocaPagamento = null;
						String dataPagamento = retorno[22].toString();
						String dataVencimentoConta = "";
						if (retorno[23] != null && !retorno[23].equals("")){

							dataVencimentoConta = retorno[23].toString();
						}
						
						//***********************************************************************
						// Verifica o Indicador de Contas Recebidas no Mes
						//***********************************************************************
						helper.setIdIndicadorContasRecebidas(this.verificarIndicadorContasRecebidasNoMes(
								anoMesReferenciaDocumento, dataPagamento));
						//***********************************************************************
						
						String dataVencimentoGuia = "";
						if (retorno[24] != null && !retorno[24].equals("")) {
							dataVencimentoGuia = retorno[24].toString();
						}
						
						/*********************************************************************
						 * Dados da Arrecadacao OUTROS
						 *********************************************************************/
						Integer tipoFinanciamento = null;
						if (retorno[25] != null) {
							tipoFinanciamento = (Integer) retorno[25];
							helper.setIdTipoFinanciamento(tipoFinanciamento);
						}
						
						Integer lancamentoItemContabilOutros = null;
						if (retorno[26] != null) {
							lancamentoItemContabilOutros = (Integer) retorno[26];
							helper.setIdLancamentoItemContabilOutros(lancamentoItemContabilOutros);
						}
						
						BigDecimal valorDebitos = new BigDecimal(0);
						if (retorno[27] != null) {
							valorDebitos = (BigDecimal) retorno[27];
						}
						
						/*********************************************************************/
						
						if (helper.getIdEpocaPagamento() == 99) {
							// Verifica Epoca de Pagamento para CONTA
							epocaPagamento = retornaValorEpocaPagamento(dataPagamento, dataVencimentoConta);
							helper.setIdEpocaPagamento(epocaPagamento);
						}else if (helper.getIdEpocaPagamento() == 98) {
							// Verifica Epoca de Pagamento para GUIA
							epocaPagamento = retornaValorEpocaPagamento(dataPagamento, dataVencimentoGuia);
							helper.setIdEpocaPagamento(epocaPagamento);						
						}
						
						//***********************************************************************
						// Verifica os Casos em que recupera os Primeiros Registros
						//***********************************************************************
						Object[] dadosPagamentoSemContaGuiaDebito = null; 
						
						if (helper.getIdSetorComercial().equals(0)) {
							dadosPagamentoSemContaGuiaDebito = (Object[]) repositorioGerencialArrecadacao
								.pesquisarDadosPagamentoSemContaGuiaDebito(helper.getIdLocalidade());
							
							// Setor Comercial
							helper.setIdSetorComercial((Integer) dadosPagamentoSemContaGuiaDebito[0]);
							// Codigo Setor Comercial
							helper.setIdCodigoSetorComercial((Integer) dadosPagamentoSemContaGuiaDebito[1]);
							// Rota
							helper.setIdRota((Integer) dadosPagamentoSemContaGuiaDebito[2]);
							// Quadra
							helper.setIdQuadra((Integer) dadosPagamentoSemContaGuiaDebito[3]);
							// Numero Quadra
							helper.setIdNumeroQuadra((Integer) dadosPagamentoSemContaGuiaDebito[4]);
						}
						
						// Codigo da Rota
						helper.setCodigoRota(this.pesquisaCodigoRota(helper.getIdRota()).intValue());
						//***********************************************************************
						
						//***********************************************************************
						// Calcula o Valor N�o Identificado
						//***********************************************************************
						idPagamento = (Integer) retorno[29];
						
						valorNaoIdentificado = new BigDecimal(0);
						if (!idPagamento.equals(idPagamentoAnterior)) {
							if (!retorno[28].equals(0)) {
								valorNaoIdentificado = (BigDecimal) retorno[28];
							}
						}
						idPagamentoAnterior = idPagamento;
						//***********************************************************************
						
						// Se ja existe um objeto igual a ele entao soma os
						// valores Agua, Esgoto e N�o Identificado.
						// Um objeto eh igual ao outro se ele tem todos as
						// informacoes de quebra iguais.
						if (listaSimplificada.contains(helper)) {
							posicao = listaSimplificada.indexOf(helper);
							jaCadastrado = (ResumoArrecadacaoAguaEsgotoHelper) listaSimplificada.get(posicao);

							// Somatorio de Valor Debitos
							jaCadastrado.setValorDebitos(jaCadastrado.getValorDebitos().add(valorDebitos));
							
							// Somatorio de Valor Nao Identificado
							jaCadastrado.setValorNaoIdentificado(jaCadastrado.getValorNaoIdentificado()
									.add(valorNaoIdentificado));
						} else {
							// Incluimos o Valor Debitos
							helper.setValorDebitos(valorDebitos);
							
							// Incluimos o Valor Nao Identificado 
							helper.setValorNaoIdentificado(helper.getValorNaoIdentificado()
									.add(valorNaoIdentificado));
							
							listaSimplificada.add(helper);
						}
					//}
				}
			}
			
			imoveisResumoArrecadacaoOutros.clear();
			imoveisResumoArrecadacaoOutros = null;
			
			imoveisResumoArrecadacaoOutrosConta.clear();
			imoveisResumoArrecadacaoOutrosConta = null;
			
			imoveisResumoArrecadacaoOutrosDebitoACobrar.clear();
			imoveisResumoArrecadacaoOutrosDebitoACobrar = null;
			
			imoveisResumoArrecadacaoOutrosGuiaPagamento.clear();
			imoveisResumoArrecadacaoOutrosGuiaPagamento = null;
			
			/**
			 * para todas as ResumoArrecadacaoAguaEsgotoHelper cria
			 * UnResumoArrecadacao
			 */
			// Declaracao dos Objetos Usados
			int anoMesReferencia = 0;
			int codigoSetorComercial = 0;
			int numeroQuadra = 0;
			int quantidadeContas = 0;
			int quantidadePagamentos = 0;
			short indicadorRecebidasNomes = 0;
			BigDecimal volumeAgua = new BigDecimal(0);
			BigDecimal volumeEsgoto = new BigDecimal(0);
			BigDecimal volumeNaoIdentificado = new BigDecimal(0);
			valorImpostos = new BigDecimal(0);
			Date ultimaAlteracao = null;
			
			//GSubcategoria subCategoria = null;
			GClienteTipo clienteTipo = null;
			GLigacaoAguaSituacao ligacaoAguaSituacao = null;
			GUnidadeNegocio unidadeNegocio = null;
			GLocalidade localidade = null;
			GLocalidade elo = null;
			GQuadra quadra = null;
			GLigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;
			GLigacaoEsgotoPerfil ligacaoEsgotoPerfil = null;
			GGerenciaRegional gerenciaRegional = null;
			GSetorComercial setorComercial = null;
			GDocumentoTipo documentoTipo = null;
			GPagamentoSituacao pagamentoSituacao = null;
			GLigacaoAguaPerfil ligacaoAguaPerfil = null;
			GEpocaPagamento epocaPagamento = null;
			GEsferaPoder esferaPoder = null;
			//GCategoria Gcategoria = null;
			GImovelPerfil imovelPerfil = null;
			GRota rota = null;
			GArrecadacaoForma formaArrecadacao = null;
			GArrecadador agenteArrecadador = null;
			UnResumoArrecadacao resumoArrecadacaoAguaEsgoto = null;
			
			BigDecimal valorDebito = new BigDecimal(0);
			
			// Preenche os Objetos com valor 0 que ser�o usados em Credito e Outros
			Integer creditoOrigem = null;
			Integer itemContabelOutros = null;
			Integer itemContabelCredito = null;
			Integer financiamentoTipoOutros = null;
			
			for (int i = 0; i < listaSimplificada.size(); i++) {
				helper = (ResumoArrecadacaoAguaEsgotoHelper) listaSimplificada.get(i);

				// Mes ano de referencia
				anoMesReferencia = anoMesReferenciaArrecadacao;
				
				// Codigo Setor Comercial
				if (helper.getIdSetorComercial() != null) {
					codigoSetorComercial = helper.getIdCodigoSetorComercial();
				}
				
				// Numero da quadra
				if (helper.getIdNumeroQuadra() != null) {
					numeroQuadra = helper.getIdNumeroQuadra();
				}
				
				// Indicador de Recebidas no Mes
				if (helper.getIdIndicadorContasRecebidas() != null) {
					indicadorRecebidasNomes = helper.getIdIndicadorContasRecebidas().shortValue();
				}
				
				// Ultima Alteracao
				ultimaAlteracao = new Date();
				
				// Principal SubCategoria da Principal Categoria do Imovel
				GSubcategoria subCategoria = null;
				if (helper.getIdSubCategoria() != null){
					subCategoria = new GSubcategoria();
					subCategoria.setId(helper.getIdSubCategoria());
				}
				
				// Tipo do Cliente Respons�vel
				if (helper.getIdTipoCliente() != null) {
					clienteTipo = new GClienteTipo();
					clienteTipo.setId(helper.getIdTipoCliente());
				}
				
				// Situacao da Liga��o da �gua
				if (helper.getIdSituacaoLigacaoAgua() != null) {
					ligacaoAguaSituacao = new GLigacaoAguaSituacao();
					ligacaoAguaSituacao.setId(helper.getIdSituacaoLigacaoAgua());
				}
				
				// Unidade de Negocio
				if (helper.getIdUnidadeNegocio() != null) {
					unidadeNegocio = new GUnidadeNegocio();
					unidadeNegocio.setId(helper.getIdUnidadeNegocio());
				}
				
				// Localidade
				if (helper.getIdLocalidade() != null){
					localidade = new GLocalidade();
					localidade.setId(helper.getIdLocalidade());
				}
				
				// Elo
				if (helper.getIdCodigoElo() != null) {
					elo = new GLocalidade();
					elo.setId(helper.getIdCodigoElo());
				}
				
				// Quadra
				if (helper.getIdQuadra() != null) {
					quadra = new GQuadra();
					quadra.setId(helper.getIdQuadra());
				}
				
				// Situa��o da Liga��o do Esgoto
				if (helper.getIdSituacaoLigacaoEsgoto() != null){
					ligacaoEsgotoSituacao = new GLigacaoEsgotoSituacao();
					ligacaoEsgotoSituacao.setId(helper.getIdSituacaoLigacaoEsgoto());
				}
				
				// Perfil da Liga��o do Esgoto
				if (helper.getIdPerfilLigacaoEsgoto() != null){
					ligacaoEsgotoPerfil = new GLigacaoEsgotoPerfil();
					ligacaoEsgotoPerfil.setId(helper.getIdPerfilLigacaoEsgoto());
				}
				
				// Gerencia Regional
				if (helper.getIdGerenciaRegional() != null) {
					gerenciaRegional = new GGerenciaRegional();
					gerenciaRegional.setId(helper.getIdGerenciaRegional());
				}
				
				// Setor comercial
				if (helper.getIdSetorComercial() != null) {
					setorComercial = new GSetorComercial();
					setorComercial.setId(helper.getIdSetorComercial());
				}
				
				// Tipo do Documento
				if (helper.getIdTipoDocumento() != null) {
					documentoTipo = new GDocumentoTipo();
					documentoTipo.setId(helper.getIdTipoDocumento());
				}
				
				// Situacao do Pagamento
				if (helper.getIdSituacaoPagamento() != null) {
					pagamentoSituacao = new GPagamentoSituacao();
					pagamentoSituacao.setId(helper.getIdSituacaoPagamento());
				}
				
				// Perfil da Liga��o da �gua
				if (helper.getIdPerfilLigacaoAgua() != null){
					ligacaoAguaPerfil = new GLigacaoAguaPerfil();
					ligacaoAguaPerfil.setId(helper.getIdPerfilLigacaoAgua());
				}
				
				// Epoca do Pagamento
				if (helper.getIdEpocaPagamento() != null) {
					epocaPagamento = new GEpocaPagamento();
					epocaPagamento.setId(helper.getIdEpocaPagamento());
				}
				
				// Esfera de Poder
				if (helper.getIdEsferaPoder() != null){
					esferaPoder = new GEsferaPoder();
					esferaPoder.setId(helper.getIdEsferaPoder());
				}
				
				// Principal Categoria do Imovel
				GCategoria categoria = null;
				if (helper.getIdCategoria() != null){
					categoria = new GCategoria();
					categoria.setId(helper.getIdCategoria());
				}
				
				// Perfil do Imovel
				if (helper.getIdPerfilImovel() != null) {
					imovelPerfil = new GImovelPerfil();
					imovelPerfil.setId(helper.getIdPerfilImovel());
				}
				
				// Rota
				if (helper.getIdRota() != null) {
					rota = new GRota();
					rota.setId(helper.getIdRota());
				}
				
				// Forma Arrecadacao
				if (helper.getIdFormaArrecadacao() != null) {
					formaArrecadacao = new GArrecadacaoForma();
					formaArrecadacao.setId(helper.getIdFormaArrecadacao());
				}
				
				// Agente Arrecadador
				if (helper.getIdAgenteArrecadador() != null) {
					agenteArrecadador = new GArrecadador();
					agenteArrecadador.setId(helper.getIdAgenteArrecadador());
				}
				
				// Indicador Hidrometro
				indicadorHidrometro = helper.getIndicadorHidrometro();
				
				// Ano/Mes Referencia Documento
				anoMesReferenciaDocumento = null;
				if (helper.getAnoMesReferenciaDocumento() != null) {
					anoMesReferenciaDocumento = helper.getAnoMesReferenciaDocumento();
				}
				
				// Valor Nao Identificado
				volumeNaoIdentificado = new BigDecimal(0);
				if (helper.getValorNaoIdentificado() != null) {
					volumeNaoIdentificado = helper.getValorNaoIdentificado();
				}
				
				// Criamos um resumo de Arrecadacao Agua/Esgoto
				resumoArrecadacaoAguaEsgoto = new UnResumoArrecadacao(
						anoMesReferencia , codigoSetorComercial    , numeroQuadra,
						quantidadeContas , indicadorRecebidasNomes , volumeAgua,
						volumeEsgoto     , ultimaAlteracao         , volumeNaoIdentificado,
						subCategoria     , clienteTipo             , ligacaoAguaSituacao,
						unidadeNegocio   , localidade              , elo,
						quadra           , ligacaoEsgotoSituacao   , ligacaoEsgotoPerfil,
						gerenciaRegional , setorComercial          , documentoTipo,
						pagamentoSituacao, ligacaoAguaPerfil       , epocaPagamento,
						esferaPoder      , categoria               , imovelPerfil,
						rota             , valorImpostos           , indicadorHidrometro,
						anoMesReferenciaDocumento);
				
				resumoArrecadacaoAguaEsgoto.setGerArrecadacaoForma(formaArrecadacao);
				resumoArrecadacaoAguaEsgoto.setGerArrecadador(agenteArrecadador);
				
				resumoArrecadacaoAguaEsgoto.setQuantidadePagamentos(quantidadePagamentos);
				
				// Preenche os Objetos com valor 0 que ser�o usados em CREDITO
				creditoOrigem = new Integer(0);
				itemContabelCredito = new Integer(0);

				resumoArrecadacaoAguaEsgoto.setCreditoOrigemIdCredito(creditoOrigem);
				resumoArrecadacaoAguaEsgoto.setLancamentoItemIdCredito(itemContabelCredito);
				resumoArrecadacaoAguaEsgoto.setValorDocumentosRecebidosCredito(new BigDecimal(0));
				
				/*********************************************************************************
				 * Arrecadacao OUTROS
				 *********************************************************************************/
				// Valor Debitos
				if (helper.getValorDebitos() != null) {
					valorDebito = helper.getValorDebitos();
				}
				
				// Lancamento Item Contabel
				if (helper.getIdLancamentoItemContabilOutros() != null) {
					itemContabelOutros = helper.getIdLancamentoItemContabilOutros();
				}
				
				// Financiamento Tipo
				financiamentoTipoOutros = null;
				if (helper.getIdTipoFinanciamento() != null) {
					financiamentoTipoOutros = helper.getIdTipoFinanciamento();
				}
				
				// Se o Tipo de Financiamento for igual a NULL, despresar o Pagamento
				//if (financiamentoTipoOutros != null) {
					resumoArrecadacaoAguaEsgoto.setLancamentoItemIdOutros(itemContabelOutros);
					resumoArrecadacaoAguaEsgoto.setFinanciamentoTipoIdOutros(financiamentoTipoOutros);
					resumoArrecadacaoAguaEsgoto.setValorDocumentosRecebidosOutros(valorDebito);
					
					// Inicio do Resumo Arrecadacao Outros
					//System.out.print("=====> Inserindo Resumo Arrecadacao OUTROS <=====");
					this.getControladorBatch().inserirObjetoParaBatchGerencial(resumoArrecadacaoAguaEsgoto);
				//}
				
				helper = null;
				resumoArrecadacaoAguaEsgoto = null;
			}
			//System.out.print("=================> FIM do Resumo Arrecadacao OUTROS LOCALIDADE: " + idLocalidade + " <=================");

		} catch (Exception ex) {
			System.out.println("RESUMO ARRECADACAO OUTROS ERRO NA LOCALIDADE -> " + idLocalidade);
			ex.printStackTrace();
			throw new EJBException(ex);
		}
	}//gerarResumoArrecadacaoOUTROS
	
	
	/**
	 * 
	 * @author Ivan S�rgio
	 * @date 10/01/2008
	 * 		 07/10/2008 - Foi detectado uma falha no modo de se obter o indicador de Contas no mes.
	 *
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @throws ControladorException
	 */
	public void gerarResumoArrecadacaoCreditos(
			int idLocalidade, 
			int anoMesReferenciaArrecadacao) throws ControladorException {
		
		try {
			List imoveisResumoArrecadacaoCreditos = this.repositorioGerencialArrecadacao
					.getImoveisResumoArrecadacaoCreditos(idLocalidade, anoMesReferenciaArrecadacao);
			
			List<ResumoArrecadacaoAguaEsgotoHelper> listaSimplificada = new ArrayList();
			
			// Declaracao dos Objetos Usados
			Object obj = null;
			Object[] retorno = null;
			ResumoArrecadacaoAguaEsgotoHelper helper = null;
			BigDecimal valorImpostos = null;
			short indicadorHidrometro;
			Integer anoMesReferenciaDocumento = null;
			int posicao = 0;
			ResumoArrecadacaoAguaEsgotoHelper jaCadastrado = null;
			
			for (int i = 0; i < imoveisResumoArrecadacaoCreditos.size(); i++) {
				obj = imoveisResumoArrecadacaoCreditos.get(i);
				
				if (obj instanceof Object[]) {
					retorno = (Object[]) obj;
					
					// Montamos um objeto de resumo, com as informacoes do retorno
					helper = new ResumoArrecadacaoAguaEsgotoHelper(
							(Integer) retorno[1],  // Gerencia Regional
							(Integer) retorno[2],  // Unidade de Negocio
							(Integer) retorno[3],  // Codigo do Elo
							(Integer) retorno[4],  // Localidade
							(Integer) retorno[5],  // Setor Comercial
							(Integer) retorno[6],  // Rota
							(Integer) retorno[7],  // Quadra
							(Integer) retorno[8],  // Perfil Imovel
							(Integer) retorno[9],  // Situa��o Liga��o �gua
							(Integer) retorno[10], // Situa��o Liga��o Esgoto
							(Integer) retorno[11], // Perfil da Liga��o da �gua
							(Integer) retorno[12], // Perfil da Liga��o do Esgoto
							(Integer) retorno[13], // Tipo Documento
							(Integer) retorno[14], // Situa��o do Pagamento
							(Integer) retorno[15], // Indicador de Contas Recebidas
							(Integer) retorno[16], // �poca do Pagamento
							(Integer) retorno[17], // Codigo do Setor Comercial
							(Integer) retorno[18], // N�mero da Quadra
							(Integer) retorno[19], // Forma de Arrecadacao
							(Integer) retorno[20]);// Agente Arrecadador

					Integer idImovel = (Integer) retorno[0]; // Codigo do imovel que esta sendo processado
					
					if (idImovel != null) {
						// Pesquisamos a esfera de poder do cliente responsavel
						helper.setIdEsferaPoder(repositorioGerencialCadastro.pesquisarEsferaPoderClienteResponsavelImovel(idImovel));
						// Pesquisamos o tipo de cliente responsavel do imovel
						helper.setIdTipoCliente(repositorioGerencialCadastro.pesquisarTipoClienteClienteResponsavelImovel(idImovel));

						// pesquisando a categoria
						// [UC0306] - Obtter principal categoria do im�vel
						Categoria categoria = null;
						categoria = this.getControladorImovel().obterPrincipalCategoriaImovel(idImovel);

						if (categoria != null) {
							helper.setIdCategoria(categoria.getId());

							// Pesquisando a principal subcategoria
							ImovelSubcategoria subcategoria =
								this.getControladorImovel().obterPrincipalSubcategoria(categoria.getId(), idImovel);

							if (subcategoria != null){
								helper.setIdSubCategoria(subcategoria.getComp_id().getSubcategoria().getId());
							}
						}
						
						// Verifica Indicador de Hidrometro para o imovel
						// [UC0307] - Obter Indicador de Exist�ncia de Hidr�metro
						String indicadorHidrometroString = new Integer(this.getControladorImovel().
								obterIndicadorExistenciaHidrometroImovel(idImovel)).toString();
						indicadorHidrometro = new Short(indicadorHidrometroString);
						helper.setIndicadorHidrometro(indicadorHidrometro);
					}else {
						// Esfera de Poder
						helper.setIdEsferaPoder(null);
						// Tipo de Cliente
						helper.setIdTipoCliente(25);
						// Categoria
						helper.setIdCategoria(1);
						// Sub-Categoria
						helper.setIdSubCategoria(10);
						// Indicador de Hidrometro
						helper.setIndicadorHidrometro(new Short("2"));
					}
					
					anoMesReferenciaDocumento = null;
					if (retorno[21] != null) {
						anoMesReferenciaDocumento = (Integer) retorno[21];
						helper.setAnoMesReferenciaDocumento(anoMesReferenciaDocumento);
					}
					
					// Verifica Epoca de Pagamento
					Integer epocaPagamento = null;
					String dataPagamento = retorno[22].toString();
					String dataVencimentoConta = "";
					if (retorno[23] != null) {
						dataVencimentoConta = retorno[23].toString();
					}
					
					//***********************************************************************
					// Verifica o Indicador de Contas Recebidas no Mes
					//***********************************************************************
					helper.setIdIndicadorContasRecebidas(this.verificarIndicadorContasRecebidasNoMes(
							anoMesReferenciaDocumento, dataPagamento));
					//***********************************************************************
					
					//String dataVencimentoGuia = "";
					//if (retorno[24] != null) {
					//	dataVencimentoGuia = retorno[24].toString();
					//}
					
					/*********************************************************************
					 * Dados da Arrecadacao CREDITOS
					 *********************************************************************/
					// Credito Origem
					Integer creditoOrigem = null;
					if (retorno[25] != null) {
						creditoOrigem = (Integer) retorno[25];
						helper.setIdCreditoOrigem(creditoOrigem);
					}
					
					// Lancamento Item Contabel
					Integer itemContabelCredito = null;
					if (retorno[26] != null) {
						itemContabelCredito = (Integer) retorno[26]; 
						helper.setIdLancamentoItemContabilCredito(itemContabelCredito);
					}
					
					// Valor Credito
					BigDecimal valorCredito = new BigDecimal(0);
					if (retorno[27] != null) {
						valorCredito = (BigDecimal) retorno[27];
					}
					
					/*********************************************************************/
					
					if (helper.getIdEpocaPagamento() == 99) {
						// Verifica Epoca de Pagamento para CONTA
						epocaPagamento = retornaValorEpocaPagamento(dataPagamento, dataVencimentoConta);
						helper.setIdEpocaPagamento(epocaPagamento);
					}
					
					//***********************************************************************
					// Verifica os Casos em que recupera os Primeiros Registros
					//***********************************************************************
					Object[] dadosPagamentoSemContaGuiaDebito = null;
					
					if (helper.getIdSetorComercial().equals(0)) {
						dadosPagamentoSemContaGuiaDebito = (Object[]) repositorioGerencialArrecadacao
							.pesquisarDadosPagamentoSemContaGuiaDebito(helper.getIdLocalidade());
						
						// Setor Comercial
						helper.setIdSetorComercial((Integer) dadosPagamentoSemContaGuiaDebito[0]);
						// Codigo Setor Comercial
						helper.setIdCodigoSetorComercial((Integer) dadosPagamentoSemContaGuiaDebito[1]);
						// Rota
						helper.setIdRota((Integer) dadosPagamentoSemContaGuiaDebito[2]);
						// Quadra
						helper.setIdQuadra((Integer) dadosPagamentoSemContaGuiaDebito[3]);
						// Numero Quadra
						helper.setIdNumeroQuadra((Integer) dadosPagamentoSemContaGuiaDebito[4]);
					}
					
					// Codigo da Rota
					helper.setCodigoRota(this.pesquisaCodigoRota(helper.getIdRota()).intValue());
					//***********************************************************************
					
					// Se ja existe um objeto igual a ele entao soma os
					// valores Agua, Esgoto e N�o Identificado.
					// Um objeto eh igual ao outro se ele tem todos as
					// informacoes de quebra iguais.
					if (listaSimplificada.contains(helper)) {
						posicao = listaSimplificada.indexOf(helper);
						jaCadastrado = (ResumoArrecadacaoAguaEsgotoHelper) listaSimplificada.get(posicao);

						// Somatorio de Valor Debitos
						jaCadastrado.setValorCredito(jaCadastrado.getValorCredito().add(valorCredito));
					} else {
						// Incluimos o Valor Debitos
						helper.setValorCredito(valorCredito);
						
						listaSimplificada.add(helper);
					}
				}
			}
			
			imoveisResumoArrecadacaoCreditos.clear();
			imoveisResumoArrecadacaoCreditos = null;
			
			/**
			 * para todas as ResumoArrecadacaoAguaEsgotoHelper cria
			 * UnResumoArrecadacao
			 */
			// Declaracao dos Objetos Usados
			int anoMesReferencia = 0;
			int codigoSetorComercial = 0;
			int numeroQuadra = 0;
			int quantidadeContas = 0;
			int quantidadePagamentos = 0;
			short indicadorRecebidasNomes = 0;
			BigDecimal volumeAgua = new BigDecimal(0);
			BigDecimal volumeEsgoto = new BigDecimal(0);
			BigDecimal volumeNaoIdentificado = new BigDecimal(0);
			valorImpostos = new BigDecimal(0);
			Date ultimaAlteracao = null;
			
			//GSubcategoria subCategoria = null;
			GClienteTipo clienteTipo = null;
			GLigacaoAguaSituacao ligacaoAguaSituacao = null;
			GUnidadeNegocio unidadeNegocio = null;
			GLocalidade localidade = null;
			GLocalidade elo = null;
			GQuadra quadra = null;
			GLigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;
			GLigacaoEsgotoPerfil ligacaoEsgotoPerfil = null;
			GGerenciaRegional gerenciaRegional = null;
			GSetorComercial setorComercial = null;
			GDocumentoTipo documentoTipo = null;
			GPagamentoSituacao pagamentoSituacao = null;
			GLigacaoAguaPerfil ligacaoAguaPerfil = null;
			GEpocaPagamento epocaPagamento = null;
			GEsferaPoder esferaPoder = null;
			//GCategoria Gcategoria = null;
			GImovelPerfil imovelPerfil = null;
			GRota rota = null;
			GArrecadacaoForma formaArrecadacao = null;
			GArrecadador agenteArrecadador = null;
			UnResumoArrecadacao resumoArrecadacaoAguaEsgoto = null;
			BigDecimal valorCredito = new BigDecimal(0);
			
			// Preenche os Objetos com valor 0 que ser�o usados em Credito e Outros
			Integer creditoOrigem = null;
			Integer itemContabelCredito = null;
			
			for (int i = 0; i < listaSimplificada.size(); i++) {
				helper = (ResumoArrecadacaoAguaEsgotoHelper) listaSimplificada.get(i);

				// Mes ano de referencia
				anoMesReferencia = anoMesReferenciaArrecadacao;
				
				// Codigo Setor Comercial
				if (helper.getIdSetorComercial() != null) {
					codigoSetorComercial = helper.getIdCodigoSetorComercial();
				}
				
				// Numero da quadra
				if (helper.getIdNumeroQuadra() != null) {
					numeroQuadra = helper.getIdNumeroQuadra();
				}
				
				// Indicador de Recebidas no Mes
				if (helper.getIdIndicadorContasRecebidas() != null) {
					indicadorRecebidasNomes = helper.getIdIndicadorContasRecebidas().shortValue();
				}
				
				// Ultima Alteracao
				ultimaAlteracao = new Date();
				
				// Principal SubCategoria da Principal Categoria do Imovel
				GSubcategoria subCategoria = null;
				if (helper.getIdSubCategoria() != null){
					subCategoria = new GSubcategoria();
					subCategoria.setId(helper.getIdSubCategoria());
				}
				
				// Tipo do Cliente Respons�vel
				if (helper.getIdTipoCliente() != null) {
					clienteTipo = new GClienteTipo();
					clienteTipo.setId(helper.getIdTipoCliente());
				}
				
				// Situacao da Liga��o da �gua
				if (helper.getIdSituacaoLigacaoAgua() != null) {
					ligacaoAguaSituacao = new GLigacaoAguaSituacao();
					ligacaoAguaSituacao.setId(helper.getIdSituacaoLigacaoAgua());
				}
				
				// Unidade de Negocio
				if (helper.getIdUnidadeNegocio() != null) {
					unidadeNegocio = new GUnidadeNegocio();
					unidadeNegocio.setId(helper.getIdUnidadeNegocio());
				}
				
				// Localidade
				if (helper.getIdLocalidade() != null){
					localidade = new GLocalidade();
					localidade.setId(helper.getIdLocalidade());
				}
				
				// Elo
				if (helper.getIdCodigoElo() != null) {
					elo = new GLocalidade();
					elo.setId(helper.getIdCodigoElo());
				}
				
				// Quadra
				if (helper.getIdQuadra() != null) {
					quadra = new GQuadra();
					quadra.setId(helper.getIdQuadra());
				}
				
				// Situa��o da Liga��o do Esgoto
				if (helper.getIdSituacaoLigacaoEsgoto() != null){
					ligacaoEsgotoSituacao = new GLigacaoEsgotoSituacao();
					ligacaoEsgotoSituacao.setId(helper.getIdSituacaoLigacaoEsgoto());
				}
				
				// Perfil da Liga��o do Esgoto
				if (helper.getIdPerfilLigacaoEsgoto() != null){
					ligacaoEsgotoPerfil = new GLigacaoEsgotoPerfil();
					ligacaoEsgotoPerfil.setId(helper.getIdPerfilLigacaoEsgoto());
				}
				
				// Gerencia Regional
				if (helper.getIdGerenciaRegional() != null) {
					gerenciaRegional = new GGerenciaRegional();
					gerenciaRegional.setId(helper.getIdGerenciaRegional());
				}
				
				// Setor comercial
				if (helper.getIdSetorComercial() != null) {
					setorComercial = new GSetorComercial();
					setorComercial.setId(helper.getIdSetorComercial());
				}
				
				// Tipo do Documento
				if (helper.getIdTipoDocumento() != null) {
					documentoTipo = new GDocumentoTipo();
					documentoTipo.setId(helper.getIdTipoDocumento());
				}
				
				// Situacao do Pagamento
				if (helper.getIdSituacaoPagamento() != null) {
					pagamentoSituacao = new GPagamentoSituacao();
					pagamentoSituacao.setId(helper.getIdSituacaoPagamento());
				}
				
				// Perfil da Liga��o da �gua
				if (helper.getIdPerfilLigacaoAgua() != null){
					ligacaoAguaPerfil = new GLigacaoAguaPerfil();
					ligacaoAguaPerfil.setId(helper.getIdPerfilLigacaoAgua());
				}
				
				// Epoca do Pagamento
				if (helper.getIdEpocaPagamento() != null) {
					epocaPagamento = new GEpocaPagamento();
					epocaPagamento.setId(helper.getIdEpocaPagamento());
				}
				
				// Esfera de Poder
				if (helper.getIdEsferaPoder() != null){
					esferaPoder = new GEsferaPoder();
					esferaPoder.setId(helper.getIdEsferaPoder());
				}
				
				// Principal Categoria do Imovel
				GCategoria categoria = null;
				if (helper.getIdCategoria() != null){
					categoria = new GCategoria();
					categoria.setId(helper.getIdCategoria());
				}
				
				// Perfil do Imovel
				if (helper.getIdPerfilImovel() != null) {
					imovelPerfil = new GImovelPerfil();
					imovelPerfil.setId(helper.getIdPerfilImovel());
				}
				
				// Rota
				if (helper.getIdRota() != null) {
					rota = new GRota();
					rota.setId(helper.getIdRota());
				}
				
				// Forma Arrecadacao
				if (helper.getIdFormaArrecadacao() != null) {
					formaArrecadacao = new GArrecadacaoForma();
					formaArrecadacao.setId(helper.getIdFormaArrecadacao());
				}
				
				// Agente Arrecadador
				if (helper.getIdAgenteArrecadador() != null) {
					agenteArrecadador = new GArrecadador();
					agenteArrecadador.setId(helper.getIdAgenteArrecadador());
				}
				
				// Indicador Hidrometro
				indicadorHidrometro = helper.getIndicadorHidrometro();
				
				// Ano/Mes Referencia Documento
				anoMesReferenciaDocumento = null;
				if (helper.getAnoMesReferenciaDocumento() != null) {
					anoMesReferenciaDocumento = helper.getAnoMesReferenciaDocumento();
				}
				
				// Criamos um resumo de Arrecadacao Agua/Esgoto
				resumoArrecadacaoAguaEsgoto = new UnResumoArrecadacao(
						anoMesReferencia , codigoSetorComercial    , numeroQuadra,
						quantidadeContas , indicadorRecebidasNomes , volumeAgua,
						volumeEsgoto     , ultimaAlteracao         , volumeNaoIdentificado,
						subCategoria     , clienteTipo             , ligacaoAguaSituacao,
						unidadeNegocio   , localidade              , elo,
						quadra           , ligacaoEsgotoSituacao   , ligacaoEsgotoPerfil,
						gerenciaRegional , setorComercial          , documentoTipo,
						pagamentoSituacao, ligacaoAguaPerfil       , epocaPagamento,
						esferaPoder      , categoria               , imovelPerfil,
						rota             , valorImpostos           , indicadorHidrometro,
						anoMesReferenciaDocumento);
				
				resumoArrecadacaoAguaEsgoto.setGerArrecadacaoForma(formaArrecadacao);
				resumoArrecadacaoAguaEsgoto.setGerArrecadador(agenteArrecadador);
				
				resumoArrecadacaoAguaEsgoto.setQuantidadePagamentos(quantidadePagamentos);
				
				creditoOrigem = new Integer(0);
				itemContabelCredito = new Integer(0);

				// Limpa os valores da Arrecadacao OUTROS
				resumoArrecadacaoAguaEsgoto.setLancamentoItemIdOutros(new Integer(0));
				resumoArrecadacaoAguaEsgoto.setFinanciamentoTipoIdOutros(new Integer(0));
				resumoArrecadacaoAguaEsgoto.setValorDocumentosRecebidosOutros(new BigDecimal(0));
				
				/*********************************************************************************
				 * Arrecadacao CREDITOS
				 *********************************************************************************/
				// Valor Creditos
				if (helper.getValorCredito() != null) {
					valorCredito = helper.getValorCredito();
				}
				
				// Lancamento Item Contabel
				if (helper.getIdLancamentoItemContabilCredito() != null) {
					itemContabelCredito = helper.getIdLancamentoItemContabilCredito();
				}
				
				// Credito Origem
				if (helper.getIdCreditoOrigem() != null) {
					creditoOrigem = helper.getIdCreditoOrigem();
				}
				
				resumoArrecadacaoAguaEsgoto.setValorDocumentosRecebidosCredito(valorCredito);
				resumoArrecadacaoAguaEsgoto.setLancamentoItemIdCredito(itemContabelCredito);
				resumoArrecadacaoAguaEsgoto.setCreditoOrigemIdCredito(creditoOrigem);
				
				// Inicio do Resumo Arrecadacao Creditos
				//System.out.print("=====> Inserindo Resumo Arrecadacao CREDITOS <=====");
				this.getControladorBatch().inserirObjetoParaBatchGerencial(resumoArrecadacaoAguaEsgoto);
				
				helper = null;
				resumoArrecadacaoAguaEsgoto = null;
			}
			//System.out.print("=================> FIM do Resumo Arrecadacao CREDITOS LOCALIDADE: " + idLocalidade + " <=================");

		} catch (Exception ex) {
			System.out.println("RESUMO ARRECADACAO CREDITOS ERRO NA LOCALIDADE -> " + idLocalidade);
			ex.printStackTrace();
			throw new EJBException(ex);
		}
	}//gerarResumoArrecadacaoCREDITOS
	
	
	public void gerarResumoArrecadacaoDevolucao(
			int idLocalidade, 
			int anoMesReferenciaArrecadacao) throws ControladorException {
		
		try {
			//System.out.print("=====> LOCALIDADE : " + idLocalidade);
			
			List imoveisResumoArrecadacaoDevolucao = this.repositorioGerencialArrecadacao
					.getImoveisResumoArrecadacaoDevolucao(idLocalidade, anoMesReferenciaArrecadacao);
			
			if (imoveisResumoArrecadacaoDevolucao.size() > 0) {
				List<ResumoArrecadacaoAguaEsgotoHelper> listaSimplificada = new ArrayList();
				
				// Declaracao dos Objetos Usados
				Object obj = null;
				Object[] retorno = null;
				ResumoArrecadacaoAguaEsgotoHelper helper = null;
				BigDecimal valorAgua = new BigDecimal(0);
				BigDecimal valorEsgoto = new BigDecimal(0);
				BigDecimal valorNaoIdentificado = new BigDecimal(0);
				BigDecimal valorImpostos = new BigDecimal(0);
				short indicadorHidrometro;
				Integer anoMesReferenciaDevolucao = null;
				int posicao = 0;
				ResumoArrecadacaoAguaEsgotoHelper jaCadastrado = null;
				
				BigDecimal valorDevolucaoClassificadas = null;
				BigDecimal valorDevolucaoNaoClassificadas = null;
				
				// pra cada objeto obter a categoria
				for (int i = 0; i < imoveisResumoArrecadacaoDevolucao.size(); i++) {
					obj = imoveisResumoArrecadacaoDevolucao.get(i);

					if (obj instanceof Object[]) {
						retorno = (Object[]) obj;
						
						// Montamos um objeto de resumo, com as informacoes do retorno
						/*
						helper = new ResumoArrecadacaoAguaEsgotoHelper(
								(Integer) retorno[1],  // Gerencia Regional
								(Integer) retorno[2],  // Unidade de Negocio
								(Integer) retorno[3],  // Codigo do Elo
								(Integer) retorno[4],  // Localidade
								(Integer) retorno[5],  // Setor Comercial
								(Integer) retorno[6],  // Rota
								(Integer) retorno[7],  // Quadra
								(Integer) retorno[8],  // Perfil Imovel
								(Integer) retorno[9],  // Situa��o Liga��o �gua
								(Integer) retorno[10], // Situa��o Liga��o Esgoto
								(Integer) retorno[11], // Perfil da Liga��o da �gua
								(Integer) retorno[12], // Perfil da Liga��o do Esgoto
								(Integer) retorno[13], // Tipo Documento
								(Integer) retorno[14], // Situa��o do Pagamento
								(Integer) retorno[15], // Indicador de Contas Recebidas (SER� VERIFICADO EM verificarIndicadorContasRecebidasNoMes)
								(Integer) retorno[16], // �poca do Pagamento
								(Integer) retorno[17], // Codigo do Setor Comercial
								(Integer) retorno[18], // N�mero da Quadra
								(Integer) retorno[22], // Forma de Arrecadacao
								(Integer) retorno[23]);// Agenete Arrecadador
						*/
						helper = new ResumoArrecadacaoAguaEsgotoHelper();
						
						helper.setIdGerenciaRegional((Integer) retorno[1]);
						helper.setIdUnidadeNegocio((Integer) retorno[2]);
						helper.setIdCodigoElo((Integer) retorno[3]);
						helper.setIdLocalidade((Integer) retorno[4]);
						helper.setIdSetorComercial((Integer) retorno[5]);
						helper.setIdRota((Integer) retorno[6]);
						helper.setIdQuadra((Integer) retorno[7]);
						helper.setIdPerfilImovel((Integer) retorno[8]);
						helper.setIdSituacaoLigacaoAgua((Integer) retorno[9]);
						helper.setIdSituacaoLigacaoEsgoto((Integer) retorno[10]);
						helper.setIdPerfilLigacaoAgua((Integer) retorno[11]);
						helper.setIdPerfilLigacaoEsgoto((Integer) retorno[12]);
						helper.setIdTipoDocumento((Integer) retorno[13]);
						helper.setIdSituacaoPagamento(null);
						helper.setIdEpocaPagamento(0);
						helper.setIdCodigoSetorComercial((Integer) retorno[17]);
						helper.setIdNumeroQuadra((Integer) retorno[18]);
						// Dados de Devolucao
						valorDevolucaoClassificadas = (BigDecimal) retorno[19];
						valorDevolucaoNaoClassificadas = (BigDecimal) retorno[20];
						helper.setIdDevolucaoSituacaoAtual((Integer) retorno[21]);
						// Deixa os valores igual a zero ou Null
						helper.setValorAgua(valorAgua);
						helper.setValorEsgoto(valorEsgoto);
						helper.setValorNaoIdentificado(valorNaoIdentificado);
						helper.setValorImpostos(valorImpostos);
						helper.setQuantidadeContas(0);
						helper.setQuantidadePagamentos(0);
						helper.setIdFormaArrecadacao(null);
						helper.setIdAgenteArrecadador(null);
						helper.setAnoMesReferenciaDocumento(null);
						
						Integer idImovel = (Integer) retorno[0]; // Codigo do imovel que esta sendo processado
						
						// Pesquisamos a esfera de poder do cliente responsavel
						helper.setIdEsferaPoder(repositorioGerencialCadastro.pesquisarEsferaPoderClienteResponsavelImovel(idImovel));
						// Pesquisamos o tipo de cliente responsavel do imovel
						helper.setIdTipoCliente(repositorioGerencialCadastro.pesquisarTipoClienteClienteResponsavelImovel(idImovel));

						// pesquisando a categoria
						// [UC0306] - Obtter principal categoria do im�vel
						Categoria categoria = null;
						categoria = this.getControladorImovel().obterPrincipalCategoriaImovel(idImovel);
						
						if (categoria != null) {
							helper.setIdCategoria(categoria.getId());

							// Pesquisando a principal subcategoria
							ImovelSubcategoria subcategoria =
								this.getControladorImovel().obterPrincipalSubcategoria(categoria.getId(), idImovel);

							if (subcategoria != null){
								helper.setIdSubCategoria(subcategoria.getComp_id().getSubcategoria().getId());
							}
						}
						
						// Verifica Indicador de Hidrometro para o imovel
						// [UC0307] - Obter Indicador de Exist�ncia de Hidr�metro
						String indicadorHidrometroString = new Integer(this.getControladorImovel().
								obterIndicadorExistenciaHidrometroImovel(idImovel)).toString();
						indicadorHidrometro = new Short(indicadorHidrometroString);
						helper.setIndicadorHidrometro(indicadorHidrometro);
						
						//***********************************************************************
						// Verifica o Indicador de Contas Recebidas no Mes
						//***********************************************************************
						String dataDevolucao = retorno[14].toString();
						anoMesReferenciaDevolucao = null;
						if (retorno[15] != null) {
							anoMesReferenciaDevolucao = (Integer) retorno[15];
						}
						helper.setIdIndicadorContasRecebidas(this.verificarIndicadorContasRecebidasNoMes(
								anoMesReferenciaDevolucao, dataDevolucao));
						//***********************************************************************
						
						// Faz o Agrupamento
						if (listaSimplificada.contains(helper)) {
							posicao = listaSimplificada.indexOf(helper);
							jaCadastrado = (ResumoArrecadacaoAguaEsgotoHelper) listaSimplificada.get(posicao);
							
							// Somatorio de Valor Devolucoes Classificadas
							jaCadastrado.setValorDevolucoesClassificadas(
									jaCadastrado.getValorDevolucoesClassificadas()
									.add(valorDevolucaoClassificadas));
							
							// Somatorio de Valor Devolucoes Nao Classificadas
							jaCadastrado.setValorDevolucoesNaoClassificadas(
									jaCadastrado.getValorDevolucoesNaoClassificadas()
									.add(valorDevolucaoNaoClassificadas));
						} else {
							// Incluimos o Valor Devolucoes Classificadas
							helper.setValorDevolucoesClassificadas(valorDevolucaoClassificadas);
							
							// Incluimos o Valor Devolucoes Nao Classificadas
							helper.setValorDevolucoesNaoClassificadas(valorDevolucaoNaoClassificadas);
							
							listaSimplificada.add(helper);
						}
					}
				}
				
				imoveisResumoArrecadacaoDevolucao.clear();
				imoveisResumoArrecadacaoDevolucao = null;
				/**
				 * para todas as ResumoArrecadacaoAguaEsgotoHelper cria
				 * UnResumoArrecadacao
				 */
				// Declaracao dos Objetos Usados
				int anoMesReferencia = 0;
				int codigoSetorComercial = 0;
				int numeroQuadra = 0;
				int quantidadeContas = 0;
				int quantidadePagamentos = 0;
				short indicadorRecebidasNomes = 0;
				BigDecimal volumeAgua = null;
				BigDecimal volumeEsgoto = null;
				Date ultimaAlteracao = null;
				BigDecimal volumeNaoIdentificado = null;
				Integer anoMesReferenciaDocumento = null;
				
				GSubcategoria subCategoria = null;
				GClienteTipo clienteTipo = null;
				GLigacaoAguaSituacao ligacaoAguaSituacao = null;
				GUnidadeNegocio unidadeNegocio = null;
				GLocalidade localidade = null;
				GLocalidade elo = null;
				GQuadra quadra = null;
				GLigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;
				GLigacaoEsgotoPerfil ligacaoEsgotoPerfil = null;
				GGerenciaRegional gerenciaRegional = null;
				GSetorComercial setorComercial = null;
				GDocumentoTipo documentoTipo = null;
				GPagamentoSituacao pagamentoSituacao = null;
				GLigacaoAguaPerfil ligacaoAguaPerfil = null;
				GEpocaPagamento epocaPagamento = null;
				GEsferaPoder esferaPoder = null;
				GCategoria Gcategoria = null;
				GImovelPerfil imovelPerfil = null;
				GRota rota = null;
				GArrecadacaoForma formaArrecadacao = null;
				GArrecadador agenteArrecadador = null;
				UnResumoArrecadacao resumoArrecadacaoDevolucao = null;
				GDevolucaoSituacao devolucaoSituacao = null;
				
				// Preenche os Objetos com valor 0 que ser�o usados em Credito e Outros
				Integer creditoOrigem = null;
				Integer itemContabelOutros = null;
				Integer itemContabelCredito = null;
				Integer financiamentoTipo = null;
				
				for (int i = 0; i < listaSimplificada.size(); i++) {
					helper = (ResumoArrecadacaoAguaEsgotoHelper) listaSimplificada.get(i);

					// Montamos todo o agrupamento necessario
					
					// Mes ano de referencia
					anoMesReferencia = anoMesReferenciaArrecadacao;
					
					// Codigo Setor Comercial
					if (helper.getIdSetorComercial() != null) {
						codigoSetorComercial = helper.getIdCodigoSetorComercial();
					}
					
					// Numero da quadra
					if (helper.getIdNumeroQuadra() != null) {
						numeroQuadra = helper.getIdNumeroQuadra();
					}
					
					// Quantidades de Conta
					if (helper.getQuantidadeContas() != null) {
						quantidadeContas = helper.getQuantidadeContas();
					}
					
					// Quantidades de Pagamentos
					if (helper.getQuantidadePagamentos() != null) {
						quantidadePagamentos = helper.getQuantidadePagamentos();
					}
					
					// Indicador de Recebidas no Mes
					if (helper.getIdIndicadorContasRecebidas() != null) {
						indicadorRecebidasNomes = helper.getIdIndicadorContasRecebidas().shortValue();
					}
					
					// Volume Agua
					volumeAgua = new BigDecimal(0);
					if (helper.getValorAgua() != null) {
						volumeAgua = helper.getValorAgua();
					}
					
					// Volume Esgoto
					volumeEsgoto = new BigDecimal(0);
					if (helper.getValorAgua() != null) {
						volumeEsgoto = helper.getValorEsgoto();
					}
					
					// Ultima Alteracao
					ultimaAlteracao = new Date();
					
					// Volume Nao Identificado
					volumeNaoIdentificado = new BigDecimal(0);
					if (helper.getValorNaoIdentificado() != null) {
						volumeNaoIdentificado = helper.getValorNaoIdentificado();
					}
					
					// Principal SubCategoria da Principal Categoria do Imovel
					if (helper.getIdSubCategoria() != null){
						subCategoria = new GSubcategoria();
						subCategoria.setId(helper.getIdSubCategoria());
					}
					
					// Tipo do Cliente Respons�vel
					if (helper.getIdTipoCliente() != null) {
						clienteTipo = new GClienteTipo();
						clienteTipo.setId(helper.getIdTipoCliente());
					}
					
					// Situacao da Liga��o da �gua
					if (helper.getIdSituacaoLigacaoAgua() != null) {
						ligacaoAguaSituacao = new GLigacaoAguaSituacao();
						ligacaoAguaSituacao.setId(helper.getIdSituacaoLigacaoAgua());
					}
					
					// Unidade de Negocio
					if (helper.getIdUnidadeNegocio() != null) {
						unidadeNegocio = new GUnidadeNegocio();
						unidadeNegocio.setId(helper.getIdUnidadeNegocio());
					}
					
					// Localidade
					if (helper.getIdLocalidade() != null){
						localidade = new GLocalidade();
						localidade.setId(helper.getIdLocalidade());
					}
					
					// Elo
					if (helper.getIdCodigoElo() != null) {
						elo = new GLocalidade();
						elo.setId(helper.getIdCodigoElo());
					}
					
					// Quadra
					if (helper.getIdQuadra() != null) {
						quadra = new GQuadra();
						quadra.setId(helper.getIdQuadra());
					}
					
					// Situa��o da Liga��o do Esgoto
					if (helper.getIdSituacaoLigacaoEsgoto() != null){
						ligacaoEsgotoSituacao = new GLigacaoEsgotoSituacao();
						ligacaoEsgotoSituacao.setId(helper.getIdSituacaoLigacaoEsgoto());
					}
					
					// Perfil da Liga��o do Esgoto
					if (helper.getIdPerfilLigacaoEsgoto() != null){
						ligacaoEsgotoPerfil = new GLigacaoEsgotoPerfil();
						ligacaoEsgotoPerfil.setId(helper.getIdPerfilLigacaoEsgoto());
					}
					
					// Gerencia Regional
					if (helper.getIdGerenciaRegional() != null) {
						gerenciaRegional = new GGerenciaRegional();
						gerenciaRegional.setId(helper.getIdGerenciaRegional());
					}
					
					// Setor comercial
					if (helper.getIdSetorComercial() != null) {
						setorComercial = new GSetorComercial();
						setorComercial.setId(helper.getIdSetorComercial());
					}
					
					// Tipo do Documento
					if (helper.getIdTipoDocumento() != null) {
						documentoTipo = new GDocumentoTipo();
						documentoTipo.setId(helper.getIdTipoDocumento());
					}
					
					// Situacao do Pagamento
					if (helper.getIdSituacaoPagamento() != null) {
						pagamentoSituacao = new GPagamentoSituacao();
						pagamentoSituacao.setId(helper.getIdSituacaoPagamento());
					}
					
					// Perfil da Liga��o da �gua
					if (helper.getIdPerfilLigacaoAgua() != null){
						ligacaoAguaPerfil = new GLigacaoAguaPerfil();
						ligacaoAguaPerfil.setId(helper.getIdPerfilLigacaoAgua());
					}
					
					// Epoca do Pagamento
					if (helper.getIdEpocaPagamento() != null) {
						epocaPagamento = new GEpocaPagamento();
						epocaPagamento.setId(helper.getIdEpocaPagamento());
					}
					
					// Esfera de Poder
					if (helper.getIdEsferaPoder() != null){
						esferaPoder = new GEsferaPoder();
						esferaPoder.setId(helper.getIdEsferaPoder());
					}
					
					// Principal Categoria do Imovel
					if (helper.getIdCategoria() != null){
						Gcategoria = new GCategoria();
						Gcategoria.setId(helper.getIdCategoria());
					}
					
					// Perfil do Imovel
					if (helper.getIdPerfilImovel() != null) {
						imovelPerfil = new GImovelPerfil();
						imovelPerfil.setId(helper.getIdPerfilImovel());
					}
					
					// Rota
					if (helper.getIdRota() != null) {
						rota = new GRota();
						rota.setId(helper.getIdRota());
					}
					
					// Forma Arrecadacao
					if (helper.getIdFormaArrecadacao() != null) {
						formaArrecadacao = new GArrecadacaoForma();
						formaArrecadacao.setId(helper.getIdFormaArrecadacao());
					}
					
					// Agente Arrecadador
					if (helper.getIdAgenteArrecadador() != null) {
						agenteArrecadador = new GArrecadador();
						agenteArrecadador.setId(helper.getIdAgenteArrecadador());
					}
					
					// Valor Impostos
					valorImpostos = new BigDecimal(0);
					if (helper.getValorImpostos() != null) {
						valorImpostos = helper.getValorImpostos();
					}
					
					// Indicador Hidrometro
					indicadorHidrometro = helper.getIndicadorHidrometro();
					
					// Ano/Mes Referencia Documento
					anoMesReferenciaDocumento = null;
					if (helper.getAnoMesReferenciaDocumento() != null) {
						anoMesReferenciaDocumento = helper.getAnoMesReferenciaDocumento();
					}
					
					//******************************************
					// Dados de Devolucao
					//******************************************
					valorDevolucaoClassificadas = null;
					if (helper.getValorDevolucoesClassificadas() != null) {
						valorDevolucaoClassificadas = helper.getValorDevolucoesClassificadas();
					}
					
					valorDevolucaoNaoClassificadas = null;
					if (helper.getValorDevolucoesNaoClassificadas() != null) {
						valorDevolucaoNaoClassificadas = helper.getValorDevolucoesNaoClassificadas();
					}
					
					if (helper.getIdDevolucaoSituacaoAtual() != null) {
						devolucaoSituacao = new GDevolucaoSituacao();
						devolucaoSituacao.setId(helper.getIdDevolucaoSituacaoAtual());
					}
					
					
					// Criamos um resumo de Arrecadacao Agua/Esgoto
					resumoArrecadacaoDevolucao = new UnResumoArrecadacao(
							anoMesReferencia , codigoSetorComercial    , numeroQuadra,
							quantidadeContas , indicadorRecebidasNomes , volumeAgua,
							volumeEsgoto     , ultimaAlteracao         , volumeNaoIdentificado,
							subCategoria     , clienteTipo             , ligacaoAguaSituacao,
							unidadeNegocio   , localidade              , elo,
							quadra           , ligacaoEsgotoSituacao   , ligacaoEsgotoPerfil,
							gerenciaRegional , setorComercial          , documentoTipo,
							pagamentoSituacao, ligacaoAguaPerfil       , epocaPagamento,
							esferaPoder      , Gcategoria              , imovelPerfil,
							rota             , valorImpostos           , indicadorHidrometro,
							anoMesReferenciaDocumento);
					
					resumoArrecadacaoDevolucao.setGerArrecadacaoForma(formaArrecadacao);
					resumoArrecadacaoDevolucao.setGerArrecadador(agenteArrecadador);
					
					resumoArrecadacaoDevolucao.setQuantidadePagamentos(quantidadePagamentos);
					
					// Preenche os Objetos com valor 0 que ser�o usados em Credito e Outros
					creditoOrigem = new Integer(0);
					itemContabelOutros = new Integer(0);
					itemContabelCredito = new Integer(0);
					financiamentoTipo = new Integer(0);
					
					resumoArrecadacaoDevolucao.setCreditoOrigemIdCredito(creditoOrigem);
					resumoArrecadacaoDevolucao.setLancamentoItemIdCredito(itemContabelCredito);
					resumoArrecadacaoDevolucao.setValorDocumentosRecebidosCredito(new BigDecimal(0));
					
					resumoArrecadacaoDevolucao.setLancamentoItemIdOutros(itemContabelOutros);
					resumoArrecadacaoDevolucao.setFinanciamentoTipoIdOutros(financiamentoTipo);
					resumoArrecadacaoDevolucao.setValorDocumentosRecebidosOutros (new BigDecimal(0));
					
					// Valores de Devolucao
					resumoArrecadacaoDevolucao.setValorDevolucoesClassificadas(valorDevolucaoClassificadas);
					resumoArrecadacaoDevolucao.setValorDevolucoesNaoClassificadas(valorDevolucaoNaoClassificadas);
					resumoArrecadacaoDevolucao.setGerDevolucaoSituacao(devolucaoSituacao);					
					
					// Insere em UN_RESUMO_ARRECADACAO
					//System.out.print("=====> Inserindo Resumo Arrecadacao DEVOLUCAO <=====");
					this.getControladorBatch().inserirObjetoParaBatchGerencial(resumoArrecadacaoDevolucao);
					
					helper = null;
					resumoArrecadacaoDevolucao = null;
				}
			}
			//System.out.print("=================> FIM do Resumo Arrecadacao DEVOLUCAO LOCALIDADE: " + idLocalidade + " <=======================================");
			
		} catch (Exception ex) {
			System.out.println("RESUMO ARRECADACAO DEVOLUCAO ERRO NA LOCALIDADE -> " + idLocalidade);
			ex.printStackTrace();
			throw new EJBException(ex);
		}
	}//gerarResumoArrecadacaoDevolucao
	
	
	/**
	 * Retorna o Valor para Epoca de Pagamento
	 * 
	 * @param dataPagamento
	 * @param dataVencimento
	 * @return
	 */
	private Integer retornaValorEpocaPagamento(String dataPagamento, String dataVencimento) {
		Integer retorno = null;
		
		Date data1 = Util.converteStringParaDate(formataData(dataPagamento));
		dataPagamento = "" + Util.getAno(data1);
		
		// Adiciona 0 ao mes
		if (Util.getMes(data1) < 10) {
			dataPagamento += "0" + Util.getMes(data1); 
		}else {
			dataPagamento += "" + Util.getMes(data1);
		}
		
		Integer iDataPagamento = Util.converterStringParaInteger(dataPagamento);
		
		if (iDataPagamento.equals(incrementaData(dataVencimento, 1))) {
			retorno = 2;
		}
		
		if (iDataPagamento.equals(incrementaData(dataVencimento, 2))) {
			retorno = 3;
		}
		
		if (iDataPagamento.equals(incrementaData(dataVencimento, 3))) {
			retorno = 4;
		}
		
		if (iDataPagamento.compareTo(incrementaData(dataVencimento, 3)) > 0) {
			retorno = 5;
		}
		
		return retorno;
	}
	
	/**
	 * Formata uma data no formato yyy-mm-dd para dd/mm/aaaa
	 * 
	 * @param data
	 * @return
	 */
	private String formataData(String data) {
		String retorno = "";
		String dia = "";
		String mes = "";
		String ano = "";
		
		dia = data.substring(8, 10);
		mes = data.substring(5, 7);
		ano = data.substring(0, 4);
		
		retorno = dia + "/" + mes + "/" + ano;
		
		return retorno;
	}
	
	/**
	 * 
	 * @param data
	 * @param qtdMes
	 * @return
	 */
	private Integer incrementaData(String data, int qtdMes) {
		Date data1 = Util.converteStringParaDate(formataData(data));
		data1 = Util.adcionarOuSubtrairMesesAData(data1, qtdMes, 0);
		String sData = "" + Util.getAno(data1);
		
		// Adiciona 0 ao mes
		if (Util.getMes(data1) < 10) {
			sData += "0" + Util.getMes(data1);
		}else {
			sData += "" + Util.getMes(data1);
		}
		
		Integer iData = Util.converterStringParaInteger(sData);
		
		return iData;
	}
	
	/***
	 * Retorna o primeiro Id do Setor Comercial da Localidade passada
	 * 
	 * @param idLocalidade
	 * @return
	 * @throws ControladorException
	 */
	/*
	private Integer pesquisaPrimeiroSetorComercial(Integer idLocalidade) throws ControladorException {
		Integer idSetorComercial = null;
		
		try {
			FiltroSetorComercial filtro = new FiltroSetorComercial();
			filtro.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.ID_LOCALIDADE, idLocalidade));
			filtro.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtro.setCampoOrderBy(FiltroSetorComercial.ID);
			
			SetorComercial setor = (SetorComercial) getControladorUtil().limiteMaximoFiltroPesquisa(
					filtro, SetorComercial.class.getName(), 1).iterator().next();
			idSetorComercial = setor.getId();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new EJBException(ex);
		}
		
		return idSetorComercial;
	}
	*/
	
	/**
	 * Retorna o Codigo da Rota da tabela Rota com sua Rota
	 * igua a Rota informada.
	 * 
	 * @param idRota
	 * @return
	 * @throws ControladorException
	 */
	private Short pesquisaCodigoRota(Integer idRota) throws ControladorException {
		Short codigoRota = null;
		
		try {
			FiltroRota filtro = new FiltroRota();
			filtro.adicionarParametro(new ParametroSimples(
					FiltroRota.ID_ROTA, idRota));
			//filtro.adicionarParametro(new ParametroSimples(
			//		FiltroRota.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			Rota rota = (Rota) getControladorUtil().pesquisar(filtro, Rota.class.getName()).iterator().next();
			codigoRota = rota.getCodigo();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new EJBException(ex);
		}
		
		return codigoRota;
	}
	
	/***
	 * Metodo utilizado para verificar se o Pagamento foi feito na referencia corrente
	 * 
	 * @param anoMesReferencia
	 * @param dataPagamento
	 * @return
	 */
	private Integer verificarIndicadorContasRecebidasNoMes(Integer anoMesReferencia, String dataPagamento) {
		Integer retorno = 0;
		
		try {
			if (anoMesReferencia != null) {
				Date data1 = Util.converteStringParaDate(formataData(dataPagamento));
				dataPagamento = "" + Util.getAno(data1);
				
				// Adiciona 0 ao mes
				if (Util.getMes(data1) < 10) {
					dataPagamento += "0" + Util.getMes(data1); 
				}else {
					dataPagamento += "" + Util.getMes(data1);
				}
				
				Integer iDataPagamento = Util.converterStringParaInteger(dataPagamento);
				
				if (iDataPagamento < anoMesReferencia) {
					retorno = 2;
				}else {
					retorno = 1;
				}
			} else {
				retorno = 2;
			}
		}catch (Exception ex) {
			ex.printStackTrace();
			throw new EJBException(ex);
		}
		
		return retorno;
	}
	

	/**
	 * Retorna o valor de controladorCadastro
	 * 
	 * @return O valor de controladorCadastro
	 */
	private ControladorGerencialCadastroLocal getControladorGerencialCadastro() {

		ControladorGerencialCadastroLocalHome localHome = null;
		ControladorGerencialCadastroLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorGerencialCadastroLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_GERENCIAL_CADASTRO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}

	/**
	 * M�todo que gera o resumo da Arrecadacao Por Ano
	 *
	 * @author Fernando Fontelles Filho
	 * @date 02/06/2010 
	 *
	 */
	public void gerarResumoArrecadacaoPorAno(
			int idLocalidade, 
			int anoMesReferenciaArrecadacao,
			int idFuncionalidadeIniciada) throws ControladorException {
		
		/**********************************************************
		 * Inicio do Resumo Arrecadacao Agua Esgoto
		 *********************************************************/
		int idUnidadeIniciada = 0;

		// -------------------------
		// Registrar o in�cio do processamento da Unidade de
		// Processamento do Batch
		// -------------------------
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.LOCALIDADE, idLocalidade);
		
		try {
			
			//FS0001 - Verificar existencia de dados para o ano/mes referencia informado
	   		getControladorGerencialCadastro().excluirResumoGerencialC(
	   				getControladorUtil().pesquisarParametrosDoSistema().getAnoMesArrecadacao(),
	   				"arrecadacao.un_resumo_arrecadacao_ref_2010",
	   				"rear_amreferencia",
	   				"loca_id",
	   				idLocalidade);
			
			List imoveisResumoArrecadacaoAguaEsgoto = this.repositorioGerencialArrecadacao
					.getImoveisResumoArrecadacaoAguaEsgotoPorAno(idLocalidade, anoMesReferenciaArrecadacao);
			
			/**********************************************************************************
			 * Recupera os Valores Nao Identificados para os casos de Conta, Guia de Pagamento
			 * e Debito a Cobrar iguais a Null
			 **********************************************************************************/
			List imoveisResumoArrecadacaoAguaEsgotoValorNaoIdentificado = this.repositorioGerencialArrecadacao
				.getImoveisResumoArrecadacaoAguaEsgotoValorNaoIdentificadoPorAno(
						idLocalidade, anoMesReferenciaArrecadacao);
			/**********************************************************************************/
			
			/**********************************************************************************
			* Junta as Partes
			***********************************************************************************/
			if (imoveisResumoArrecadacaoAguaEsgotoValorNaoIdentificado != null &&
					!imoveisResumoArrecadacaoAguaEsgotoValorNaoIdentificado.isEmpty()) {
				
				imoveisResumoArrecadacaoAguaEsgoto.addAll(imoveisResumoArrecadacaoAguaEsgotoValorNaoIdentificado);
			}
			/**********************************************************************************/

			List<ResumoArrecadacaoAguaEsgotoPorAnoHelper> listaSimplificada = new ArrayList();
			
			// Declaracao dos Objetos Usados
			Object obj = null;
			Object[] retorno = null;
			ResumoArrecadacaoAguaEsgotoPorAnoHelper helper = null;
			BigDecimal valorAgua = null;
			BigDecimal valorEsgoto = null;
			BigDecimal valorNaoIdentificado = null;
			BigDecimal valorImpostos = null;
			short indicadorHidrometro;
			Integer anoMesReferenciaDocumento = null;
			int posicao = 0;
			ResumoArrecadacaoAguaEsgotoPorAnoHelper jaCadastrado = null;
			
			// pra cada objeto obter a categoria
			for (int i = 0; i < imoveisResumoArrecadacaoAguaEsgoto.size(); i++) {
				obj = imoveisResumoArrecadacaoAguaEsgoto.get(i);

				if (obj instanceof Object[]) {
					retorno = (Object[]) obj;
					
					// Montamos um objeto de resumo, com as informacoes do retorno
					helper = new ResumoArrecadacaoAguaEsgotoPorAnoHelper(
							(Integer) retorno[1],  // Gerencia Regional
							(Integer) retorno[2],  // Unidade de Negocio
							(Integer) retorno[3],  // Codigo do Elo
							(Integer) retorno[4],  // Localidade
							(Integer) retorno[5],  // Setor Comercial
//							(Integer) retorno[6],  // Rota
//							(Integer) retorno[7],  // Quadra
							(Integer) retorno[6],  // Perfil Imovel
							(Integer) retorno[7],  // Situa��o Liga��o �gua
							(Integer) retorno[8], // Situa��o Liga��o Esgoto
							(Integer) retorno[9], // Perfil da Liga��o da �gua
							(Integer) retorno[10], // Perfil da Liga��o do Esgoto
							(Integer) retorno[11], // Tipo Documento
							(Integer) retorno[12], // Situa��o do Pagamento
							(Integer) retorno[13], // Indicador de Contas Recebidas (SER� VERIFICADO EM verificarIndicadorContasRecebidasNoMes)
							(Integer) retorno[14], // �poca do Pagamento
							(Integer) retorno[15], // Codigo do Setor Comercial
//							(Integer) retorno[18], // N�mero da Quadra
							(Integer) retorno[19], // Forma de Arrecadacao
							(Integer) retorno[20]);// Agenete Arrecadador
					
					Integer idImovel = (Integer) retorno[0]; // Codigo do imovel que esta sendo processado
					
					if (idImovel != null) {
						// Pesquisamos a esfera de poder do cliente responsavel
						helper.setIdEsferaPoder(repositorioGerencialCadastro.
								pesquisarEsferaPoderClienteResponsavelImovel(idImovel));
						// Pesquisamos o tipo de cliente responsavel do imovel
						helper.setIdTipoCliente(repositorioGerencialCadastro.
								pesquisarTipoClienteClienteResponsavelImovel(idImovel));

						// pesquisando a categoria
						// [UC0306] - Obtter principal categoria do im�vel
						Categoria categoria = null;
						categoria = this.getControladorImovel().obterPrincipalCategoriaImovel(idImovel);
						
						if (categoria != null) {
							helper.setIdCategoria(categoria.getId());

							// Pesquisando a principal subcategoria
							ImovelSubcategoria subcategoria =
								this.getControladorImovel().obterPrincipalSubcategoria(categoria.getId(), idImovel);

							if (subcategoria != null){
								helper.setIdSubCategoria(subcategoria.getComp_id().getSubcategoria().getId());
							}
						}
						
						// Verifica Indicador de Hidrometro para o imovel
						// [UC0307] - Obter Indicador de Exist�ncia de Hidr�metro
						String indicadorHidrometroString = new Integer(this.getControladorImovel().
								obterIndicadorExistenciaHidrometroImovel(idImovel)).toString();
						indicadorHidrometro = new Short(indicadorHidrometroString);
						helper.setIndicadorHidrometro(indicadorHidrometro);
					}else {
						// Esfera de Poder
						helper.setIdEsferaPoder(null);
						// Tipo de Cliente
						helper.setIdTipoCliente(25);
						// Categoria
						helper.setIdCategoria(1);
						// Sub-Categoria
						helper.setIdSubCategoria(10);
						// Indicador de Hidrometro
						helper.setIndicadorHidrometro(new Short("2"));
					}
					
					valorAgua = new BigDecimal(0);
					if (retorno[16] != null) {
						if (!retorno[16].equals(0)) {
							valorAgua = (BigDecimal) retorno[16];
						}
					}
					
					valorEsgoto = new BigDecimal(0);
					if (retorno[17] != null) {
						if (!retorno[17].equals(0)) {
							valorEsgoto = (BigDecimal) retorno[17];	
						}
					}
					
					valorNaoIdentificado = new BigDecimal(0);
					if (retorno[18] != null) {
						valorNaoIdentificado = (BigDecimal) retorno[18];
					}
					
					valorImpostos = new BigDecimal(0);
					if (retorno[21] != null) {
						if (!retorno[21].equals(0)) {
							valorImpostos = (BigDecimal) retorno[21];
						}
					}
					
					anoMesReferenciaDocumento = null;
					if (retorno[22] != null) {
						anoMesReferenciaDocumento = (Integer) retorno[22];
						helper.setAnoMesReferenciaDocumento(anoMesReferenciaDocumento);
					}
					
					//***********************************************************************
					// Verifica Epoca de Pagamento
					//***********************************************************************
					Integer epocaPagamento = null;
					String dataPagamento = retorno[23].toString();
					String dataVencimentoConta = "";
					if (retorno[24] != null) {
						if (!retorno[24].equals("")) {
							dataVencimentoConta = retorno[24].toString();
						}
					}
					
					if (helper.getIdEpocaPagamento() == 99) {
						// Verifica Epoca de Pagamento para CONTA
						epocaPagamento = retornaValorEpocaPagamento(dataPagamento, dataVencimentoConta);
						helper.setIdEpocaPagamento(epocaPagamento);
					}
					//***********************************************************************
					
					//***********************************************************************
					// Verifica o Indicador de Contas Recebidas no Mes
					//***********************************************************************
					helper.setIdIndicadorContasRecebidas(this.verificarIndicadorContasRecebidasNoMes(
							anoMesReferenciaDocumento, dataPagamento));
					//***********************************************************************
					
					//***********************************************************************
					// Verifica os Casos em que recupera os Primeiros Registros
					//***********************************************************************
					Object[] dadosPagamentoSemContaGuiaDebito = null; 
					
					if (helper.getIdSetorComercial().equals(0)) {
						dadosPagamentoSemContaGuiaDebito = (Object[]) repositorioGerencialArrecadacao
							.pesquisarDadosPagamentoSemContaGuiaDebitoPorAno(helper.getIdLocalidade());
						
						// Setor Comercial
						helper.setIdSetorComercial((Integer) dadosPagamentoSemContaGuiaDebito[0]);
						// Codigo Setor Comercial
						helper.setIdCodigoSetorComercial((Integer) dadosPagamentoSemContaGuiaDebito[1]);
						// Rota
//						helper.setIdRota((Integer) dadosPagamentoSemContaGuiaDebito[2]);
//						// Quadra
//						helper.setIdQuadra((Integer) dadosPagamentoSemContaGuiaDebito[3]);
//						// Numero Quadra
//						helper.setIdNumeroQuadra((Integer) dadosPagamentoSemContaGuiaDebito[4]);
					}
					
					// Codigo da Rota
//					helper.setCodigoRota(this.pesquisaCodigoRota(helper.getIdRota()).intValue());
					//***********************************************************************
					
					
					// Se ja existe um objeto igual a ele entao soma os
					// valores Agua, Esgoto e N�o Identificado.
					// Um objeto eh igual ao outro se ele tem todos as
					// informacoes de quebra iguais.
					if (listaSimplificada.contains(helper)) {
						posicao = listaSimplificada.indexOf(helper);
						jaCadastrado = (ResumoArrecadacaoAguaEsgotoPorAnoHelper) listaSimplificada.get(posicao);

						// Somatorio de Valor Agua
						jaCadastrado.setValorAgua(jaCadastrado.getValorAgua().add(valorAgua));
						
						// Somatorio de Valor Esgoto
						jaCadastrado.setValorEsgoto(jaCadastrado.getValorEsgoto().add(valorEsgoto));
						
						// Somatorio de Valor Nao Identificado
						jaCadastrado.setValorNaoIdentificado(jaCadastrado.getValorNaoIdentificado()
								.add(valorNaoIdentificado));
						
						// Somatorio de Valor Impostos
						jaCadastrado.setValorImpostos(jaCadastrado.getValorImpostos()
								.add(valorImpostos));
						
						// Incrementamos a Quantidade de Contas
						jaCadastrado.setQuantidadeContas(jaCadastrado.getQuantidadeContas().intValue() + 1);
						
						// Incrementamos a Quantidade de Pagamentos
						jaCadastrado.setQuantidadePagamentos(jaCadastrado.getQuantidadePagamentos().intValue() + 1);
						
					} else {
						// Incluimos o Valor Agua 
						helper.setValorAgua(helper.getValorAgua().add(valorAgua));
						
						// Incluimos o Valor Esgoto 
						helper.setValorEsgoto(helper.getValorEsgoto().add(valorEsgoto));
						
						// Incluimos o Valor Nao Identificado 
						helper.setValorNaoIdentificado(helper.getValorNaoIdentificado()
								.add(valorNaoIdentificado));
						
						// Incluimos o Valor Impostos 
						helper.setValorImpostos(helper.getValorImpostos()
								.add(valorImpostos));
						
						// Incrementamos a Quantidade de Contas
						helper.setQuantidadeContas(helper.getQuantidadeContas().intValue() + 1);
						
						// Incrementamos a Quantidade de Pagamentos
						helper.setQuantidadePagamentos(helper.getQuantidadePagamentos().intValue() + 1);
						
						listaSimplificada.add(helper);
					}
				}
			}
			
			imoveisResumoArrecadacaoAguaEsgoto.clear();
			imoveisResumoArrecadacaoAguaEsgoto = null;
			/**
			 * para todas as ResumoArrecadacaoAguaEsgotoPorAnoHelper cria
			 * UnResumoArrecadacaoPorAno
			 */
			// Declaracao dos Objetos Usados
			int anoMesReferencia = 0;
			int codigoSetorComercial = 0;
			//int numeroQuadra = 0;
			int quantidadeContas = 0;
			int quantidadePagamentos = 0;
			short indicadorRecebidasNomes = 0;
			BigDecimal volumeAgua = null;
			BigDecimal volumeEsgoto = null;
			Date ultimaAlteracao = null;
			BigDecimal volumeNaoIdentificado = null;
			
			GSubcategoria subCategoria = null;
			GClienteTipo clienteTipo = null;
			GLigacaoAguaSituacao ligacaoAguaSituacao = null;
			GUnidadeNegocio unidadeNegocio = null;
			GLocalidade localidade = null;
			GLocalidade elo = null;
			//GQuadra quadra = null;
			GLigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;
			GLigacaoEsgotoPerfil ligacaoEsgotoPerfil = null;
			GGerenciaRegional gerenciaRegional = null;
			GSetorComercial setorComercial = null;
			GDocumentoTipo documentoTipo = null;
			GPagamentoSituacao pagamentoSituacao = null;
			GLigacaoAguaPerfil ligacaoAguaPerfil = null;
			GEpocaPagamento epocaPagamento = null;
			GEsferaPoder esferaPoder = null;
			GCategoria Gcategoria = null;
			GImovelPerfil imovelPerfil = null;
			//GRota rota = null;
			GArrecadacaoForma formaArrecadacao = null;
			GArrecadador agenteArrecadador = null;
			UnResumoArrecadacaoPorAno resumoArrecadacaoAguaEsgoto = null;
			
			// Preenche os Objetos com valor 0 que ser�o usados em Credito e Outros
			Integer creditoOrigem = null;
			Integer itemContabelOutros = null;
			Integer itemContabelCredito = null;
			Integer financiamentoTipo = null;
			
			for (int i = 0; i < listaSimplificada.size(); i++) {
				helper = (ResumoArrecadacaoAguaEsgotoPorAnoHelper) listaSimplificada.get(i);

				// Montamos todo o agrupamento necessario
				
				// Mes ano de referencia
				anoMesReferencia = anoMesReferenciaArrecadacao;
				
				// Codigo Setor Comercial
				if (helper.getIdSetorComercial() != null) {
					codigoSetorComercial = helper.getIdCodigoSetorComercial();
				}
				
				// Numero da quadra
//				if (helper.getIdNumeroQuadra() != null) {
//					numeroQuadra = (helper.getIdNumeroQuadra());
//				}
				
				// Quantidades de Conta
				if (helper.getQuantidadeContas() != null) {
					quantidadeContas = helper.getQuantidadeContas();
				}
				
				// Quantidades de Pagamentos
				if (helper.getQuantidadePagamentos() != null) {
					quantidadePagamentos = helper.getQuantidadePagamentos();
				}
				
				// Indicador de Recebidas no Mes
				if (helper.getIdIndicadorContasRecebidas() != null) {
					indicadorRecebidasNomes = helper.getIdIndicadorContasRecebidas().shortValue();
				}
				
				// Volume Agua
				volumeAgua = new BigDecimal(0);
				if (helper.getValorAgua() != null) {
					volumeAgua = helper.getValorAgua();
				}
				
				// Volume Esgoto
				volumeEsgoto = new BigDecimal(0);
				if (helper.getValorAgua() != null) {
					volumeEsgoto = helper.getValorEsgoto();
				}
				
				// Ultima Alteracao
				ultimaAlteracao = new Date();
				
				// Volume Nao Identificado
				volumeNaoIdentificado = new BigDecimal(0);
				if (helper.getValorNaoIdentificado() != null) {
					volumeNaoIdentificado = helper.getValorNaoIdentificado();
				}
				
				// Principal SubCategoria da Principal Categoria do Imovel
				if (helper.getIdSubCategoria() != null){
					subCategoria = new GSubcategoria();
					subCategoria.setId(helper.getIdSubCategoria());
				}
				
				// Tipo do Cliente Respons�vel
				if (helper.getIdTipoCliente() != null) {
					clienteTipo = new GClienteTipo();
					clienteTipo.setId(helper.getIdTipoCliente());
				}
				
				// Situacao da Liga��o da �gua
				if (helper.getIdSituacaoLigacaoAgua() != null) {
					ligacaoAguaSituacao = new GLigacaoAguaSituacao();
					ligacaoAguaSituacao.setId(helper.getIdSituacaoLigacaoAgua());
				}
				
				// Unidade de Negocio
				if (helper.getIdUnidadeNegocio() != null) {
					unidadeNegocio = new GUnidadeNegocio();
					unidadeNegocio.setId(helper.getIdUnidadeNegocio());
				}
				
				// Localidade
				if (helper.getIdLocalidade() != null){
					localidade = new GLocalidade();
					localidade.setId(helper.getIdLocalidade());
				}
				
				// Elo
				if (helper.getIdCodigoElo() != null) {
					elo = new GLocalidade();
					elo.setId(helper.getIdCodigoElo());
				}
				
				// Quadra
//				if (helper.getIdQuadra() != null) {
//					quadra = new GQuadra();
//					quadra.setId(helper.getIdQuadra());
//				}
				
				// Situa��o da Liga��o do Esgoto
				if (helper.getIdSituacaoLigacaoEsgoto() != null){
					ligacaoEsgotoSituacao = new GLigacaoEsgotoSituacao();
					ligacaoEsgotoSituacao.setId(helper.getIdSituacaoLigacaoEsgoto());
				}
				
				// Perfil da Liga��o do Esgoto
				if (helper.getIdPerfilLigacaoEsgoto() != null){
					ligacaoEsgotoPerfil = new GLigacaoEsgotoPerfil();
					ligacaoEsgotoPerfil.setId(helper.getIdPerfilLigacaoEsgoto());
				}
				
				// Gerencia Regional
				if (helper.getIdGerenciaRegional() != null) {
					gerenciaRegional = new GGerenciaRegional();
					gerenciaRegional.setId(helper.getIdGerenciaRegional());
				}
				
				// Setor comercial
				if (helper.getIdSetorComercial() != null) {
					setorComercial = new GSetorComercial();
					setorComercial.setId(helper.getIdSetorComercial());
				}
				
				// Tipo do Documento
				if (helper.getIdTipoDocumento() != null) {
					documentoTipo = new GDocumentoTipo();
					documentoTipo.setId(helper.getIdTipoDocumento());
				}
				
				// Situacao do Pagamento
				if (helper.getIdSituacaoPagamento() != null) {
					pagamentoSituacao = new GPagamentoSituacao();
					pagamentoSituacao.setId(helper.getIdSituacaoPagamento());
				}
				
				// Perfil da Liga��o da �gua
				if (helper.getIdPerfilLigacaoAgua() != null){
					ligacaoAguaPerfil = new GLigacaoAguaPerfil();
					ligacaoAguaPerfil.setId(helper.getIdPerfilLigacaoAgua());
				}
				
				// Epoca do Pagamento
				if (helper.getIdEpocaPagamento() != null) {
					epocaPagamento = new GEpocaPagamento();
					epocaPagamento.setId(helper.getIdEpocaPagamento());
				}
				
				// Esfera de Poder
				if (helper.getIdEsferaPoder() != null){
					esferaPoder = new GEsferaPoder();
					esferaPoder.setId(helper.getIdEsferaPoder());
				}
				
				// Principal Categoria do Imovel
				if (helper.getIdCategoria() != null){
					Gcategoria = new GCategoria();
					Gcategoria.setId(helper.getIdCategoria());
				}
				
				// Perfil do Imovel
				if (helper.getIdPerfilImovel() != null) {
					imovelPerfil = new GImovelPerfil();
					imovelPerfil.setId(helper.getIdPerfilImovel());
				}
				
				// Rota
//				if (helper.getIdRota() != null) {
//					rota = new GRota();
//					rota.setId(helper.getIdRota());
//				}
				
				// Forma Arrecadacao
				if (helper.getIdFormaArrecadacao() != null) {
					formaArrecadacao = new GArrecadacaoForma();
					formaArrecadacao.setId(helper.getIdFormaArrecadacao());
				}
				
				// Agente Arrecadador
				if (helper.getIdAgenteArrecadador() != null) {
					agenteArrecadador = new GArrecadador();
					agenteArrecadador.setId(helper.getIdAgenteArrecadador());
				}
				
				// Valor Impostos
				valorImpostos = new BigDecimal(0);
				if (helper.getValorImpostos() != null) {
					valorImpostos = helper.getValorImpostos();
				}
				
				// Indicador Hidrometro
				indicadorHidrometro = helper.getIndicadorHidrometro();
				
				// Ano/Mes Referencia Documento
				anoMesReferenciaDocumento = null;
				if (helper.getAnoMesReferenciaDocumento() != null) {
					anoMesReferenciaDocumento = helper.getAnoMesReferenciaDocumento();
				}
				
				// Criamos um resumo de Arrecadacao Agua/Esgoto
				resumoArrecadacaoAguaEsgoto = new UnResumoArrecadacaoPorAno(
						anoMesReferencia , 
						codigoSetorComercial , 
//						numeroQuadra,
						quantidadeContas , 
						indicadorRecebidasNomes , 
						volumeAgua,
						volumeEsgoto , 
						ultimaAlteracao , 
						volumeNaoIdentificado,
						subCategoria , 
						clienteTipo , 
						ligacaoAguaSituacao ,
						unidadeNegocio , 
						localidade , 
						elo ,
//						quadra , 
						ligacaoEsgotoSituacao , 
						ligacaoEsgotoPerfil ,
						gerenciaRegional , 
						setorComercial , 
						documentoTipo ,
						pagamentoSituacao , 
						ligacaoAguaPerfil , 
						epocaPagamento,
						esferaPoder , 
						Gcategoria , 
						imovelPerfil ,
//						rota , 
						valorImpostos , 
						indicadorHidrometro ,
						anoMesReferenciaDocumento);
				
				resumoArrecadacaoAguaEsgoto.setGerArrecadacaoForma(formaArrecadacao);
				resumoArrecadacaoAguaEsgoto.setGerArrecadador(agenteArrecadador);
				
				// Preenche os Objetos com valor 0 que ser�o usados em Credito e Outros
				creditoOrigem = new Integer(0);
				itemContabelOutros = new Integer(0);
				itemContabelCredito = new Integer(0);
				financiamentoTipo = new Integer(0);
				
				resumoArrecadacaoAguaEsgoto.setCreditoOrigemIdCredito(creditoOrigem);
				resumoArrecadacaoAguaEsgoto.setLancamentoItemIdCredito(itemContabelCredito);
				resumoArrecadacaoAguaEsgoto.setValorDocumentosRecebidosCredito(new BigDecimal(0));
				
				resumoArrecadacaoAguaEsgoto.setLancamentoItemIdOutros(itemContabelOutros);
				resumoArrecadacaoAguaEsgoto.setFinanciamentoTipoIdOutros(financiamentoTipo);
				resumoArrecadacaoAguaEsgoto.setValorDocumentosRecebidosOutros (new BigDecimal(0));
				
				resumoArrecadacaoAguaEsgoto.setQuantidadePagamentos(quantidadePagamentos);
				
				// Insere em UN_RESUMO_ARRECADACAO_AGUA_ESGOTO
				//System.out.print("=====> Inserindo Resumo Arrecadacao <=====");
				this.getControladorBatch().inserirObjetoParaBatchGerencial(resumoArrecadacaoAguaEsgoto);
				
				helper = null;
				resumoArrecadacaoAguaEsgoto = null;
			}
			System.out.print("=================> FIM do Resumo Arrecadacao Por Ano LOCALIDADE: " 
					+ idLocalidade + " <=======================================");
			
			/**********************************************************************************
			 * INICIO DO RESUMO ARRECADACAO OUTROS
			 **********************************************************************************/
			this.gerarResumoArrecadacaoOutrosPorAno(idLocalidade, anoMesReferenciaArrecadacao);
			/**********************************************************************************/
			
			/**********************************************************************************
			 * INICIO DO RESUMO ARRECADACAO CREDITOS
			 **********************************************************************************/
			this.gerarResumoArrecadacaoCreditosPorAno(idLocalidade, anoMesReferenciaArrecadacao);
			/**********************************************************************************/
			
			/**********************************************************************************
			 * INICIO DO RESUMO ARRECADACAO DEVOLUCAO
			 **********************************************************************************/
			this.gerarResumoArrecadacaoDevolucaoPorAno(idLocalidade, anoMesReferenciaArrecadacao);
			/**********************************************************************************/
			
			// --------------------------------------------------------
			//
			// Registrar o fim da execu��o da Unidade de Processamento
			//
			// --------------------------------------------------------

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);
		} catch (Exception ex) {
			// Este catch serve para interceptar qualquer exce��o que o processo
			// batch venha a lan�ar e garantir que a unidade de processamento do
			// batch ser� atualizada com o erro ocorrido
			System.out.println("*********** Resumo da Arrecadacao ****************************************");
			System.out.println("ERROR NA LOCALIDADE -> " + idLocalidade);
			System.out.println("**************************************************************************");
			ex.printStackTrace();
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex, idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}//gerarResumoArrecadacaoPorAno
	
	/**
	 * @author Fernando Fontelles
	 * @date 09/06/2010
	 *
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @throws ControladorException
	 */
	public void gerarResumoArrecadacaoOutrosPorAno(
			int idLocalidade, 
			int anoMesReferenciaArrecadacao) throws ControladorException {
		
		try {
			List imoveisResumoArrecadacaoOutros = new ArrayList();
			List imoveisResumoArrecadacaoOutrosConta = new ArrayList();
			List imoveisResumoArrecadacaoOutrosGuiaPagamento = new ArrayList();
			List imoveisResumoArrecadacaoOutrosDebitoACobrar = new ArrayList();
			
			// Usado para calcular os Valor N�o Identificado
			Integer idPagamento = 0;
			Integer idPagamentoAnterior = 0;
			BigDecimal valorNaoIdentificado = null;
			
			//**************************************************************************************
			// RESUMO ARRECADACAO OUTROS - CONTA
			//**************************************************************************************
			imoveisResumoArrecadacaoOutrosConta = this.repositorioGerencialArrecadacao
					.getImoveisResumoArrecadacaoOutrosContaPorAno(
							idLocalidade, anoMesReferenciaArrecadacao);
			//**************************************************************************************
			// RESUMO ARRECADACAO OUTROS - GUIA PAGAMENTO
			//**************************************************************************************
			imoveisResumoArrecadacaoOutrosGuiaPagamento = this.repositorioGerencialArrecadacao
					.getImoveisResumoArrecadacaoOutrosGuiaPagamentoPorAno(
							idLocalidade, anoMesReferenciaArrecadacao);
			//**************************************************************************************
			// RESUMO ARRECADACAO OUTROS - DEBITO A COBRAR
			//**************************************************************************************
			imoveisResumoArrecadacaoOutrosDebitoACobrar = this.repositorioGerencialArrecadacao
					.getImoveisResumoArrecadacaoOutrosDebitoACobrarPorAno(
							idLocalidade, anoMesReferenciaArrecadacao);
			//**************************************************************************************
			
			//**************************************************************************************
			//** Junta os 3 resultados *************************************************************
			if (imoveisResumoArrecadacaoOutrosConta != null &&
					!imoveisResumoArrecadacaoOutrosConta.isEmpty()) {
				imoveisResumoArrecadacaoOutros.addAll(imoveisResumoArrecadacaoOutrosConta);
			}
			
			if (imoveisResumoArrecadacaoOutrosGuiaPagamento != null &&
					!imoveisResumoArrecadacaoOutrosGuiaPagamento.isEmpty()) {
				imoveisResumoArrecadacaoOutros.addAll(imoveisResumoArrecadacaoOutrosGuiaPagamento);
			}
			
			if (imoveisResumoArrecadacaoOutrosDebitoACobrar != null &&
					!imoveisResumoArrecadacaoOutrosDebitoACobrar.isEmpty()) {
				imoveisResumoArrecadacaoOutros.addAll(imoveisResumoArrecadacaoOutrosDebitoACobrar);
			}
			//**************************************************************************************

			List<ResumoArrecadacaoAguaEsgotoPorAnoHelper> listaSimplificada = new ArrayList();
			
			// Declaracao dos Objetos Usados
			Object obj = null;
			Object[] retorno = null;
			ResumoArrecadacaoAguaEsgotoPorAnoHelper helper = null;
			BigDecimal valorImpostos = null;
			short indicadorHidrometro;
			Integer anoMesReferenciaDocumento = null;
			int posicao = 0;
			ResumoArrecadacaoAguaEsgotoPorAnoHelper jaCadastrado = null;
			
			for (int i = 0; i < imoveisResumoArrecadacaoOutros.size(); i++) {
				obj = imoveisResumoArrecadacaoOutros.get(i);
				
				if (obj instanceof Object[]) {
					retorno = (Object[]) obj;
					
					// Se o Tipo de Financiamento for igual a NULL, despresar o Pagamento
					//if (retorno[25] != null) {
						
						// Montamos um objeto de resumo, com as informacoes do retorno
						helper = new ResumoArrecadacaoAguaEsgotoPorAnoHelper(
								(Integer) retorno[1],  // Gerencia Regional
								(Integer) retorno[2],  // Unidade de Negocio
								(Integer) retorno[3],  // Codigo do Elo
								(Integer) retorno[4],  // Localidade
								(Integer) retorno[5],  // Setor Comercial
//								(Integer) retorno[6],  // Rota
//								(Integer) retorno[7],  // Quadra
								(Integer) retorno[6],  // Perfil Imovel
								(Integer) retorno[7],  // Situa��o Liga��o �gua
								(Integer) retorno[8], // Situa��o Liga��o Esgoto
								(Integer) retorno[9], // Perfil da Liga��o da �gua
								(Integer) retorno[10], // Perfil da Liga��o do Esgoto
								(Integer) retorno[11], // Tipo Documento
								(Integer) retorno[12], // Situa��o do Pagamento
								(Integer) retorno[13], // Indicador de Contas Recebidas
								(Integer) retorno[14], // �poca do Pagamento
								(Integer) retorno[15], // Codigo do Setor Comercial
//								(Integer) retorno[18], // N�mero da Quadra
								(Integer) retorno[16], // Forma de Arrecadacao
								(Integer) retorno[17]);// Agente Arrecadador

						Integer idImovel = (Integer) retorno[0]; // Codigo do imovel que esta sendo processado
						
						if (idImovel != null) {
							// Pesquisamos a esfera de poder do cliente responsavel
							helper.setIdEsferaPoder(repositorioGerencialCadastro.pesquisarEsferaPoderClienteResponsavelImovel(idImovel));
							// Pesquisamos o tipo de cliente responsavel do imovel
							helper.setIdTipoCliente(repositorioGerencialCadastro.pesquisarTipoClienteClienteResponsavelImovel(idImovel));

							// pesquisando a categoria
							// [UC0306] - Obtter principal categoria do im�vel
							Categoria categoria = null;
							categoria = this.getControladorImovel().obterPrincipalCategoriaImovel(idImovel);

							if (categoria != null) {
								helper.setIdCategoria(categoria.getId());

								// Pesquisando a principal subcategoria
								ImovelSubcategoria subcategoria =
									this.getControladorImovel().obterPrincipalSubcategoria(categoria.getId(), idImovel);

								if (subcategoria != null){
									helper.setIdSubCategoria(subcategoria.getComp_id().getSubcategoria().getId());
								}
							}
							
							// Verifica Indicador de Hidrometro para o imovel
							// [UC0307] - Obter Indicador de Exist�ncia de Hidr�metro
							String indicadorHidrometroString = new Integer(this.getControladorImovel().
									obterIndicadorExistenciaHidrometroImovel(idImovel)).toString();
							
							indicadorHidrometro = new Short(indicadorHidrometroString);
							
							helper.setIndicadorHidrometro(indicadorHidrometro);
							
						}else {
							// Esfera de Poder
							helper.setIdEsferaPoder(null);
							// Tipo de Cliente
							helper.setIdTipoCliente(25);
							// Categoria
							helper.setIdCategoria(1);
							// Sub-Categoria
							helper.setIdSubCategoria(10);
							// Indicador de Hidrometro
							helper.setIndicadorHidrometro(new Short("2"));
						}
						
						anoMesReferenciaDocumento = null;
						if (retorno[18] != null) {
							
							anoMesReferenciaDocumento = (Integer) retorno[18];
							helper.setAnoMesReferenciaDocumento(anoMesReferenciaDocumento);
							
						}
						
						// Verifica Epoca de Pagamento
						Integer epocaPagamento = null;
						String dataPagamento = retorno[19].toString();
						String dataVencimentoConta = "";
						
						 if (retorno[20] != null && !retorno[20].equals("")){
							
							dataVencimentoConta = retorno[20].toString();
							
						}
						
						//***********************************************************************
						// Verifica o Indicador de Contas Recebidas no Mes
						//***********************************************************************
						helper.setIdIndicadorContasRecebidas(this.verificarIndicadorContasRecebidasNoMes(
								anoMesReferenciaDocumento, dataPagamento));
						//***********************************************************************
						
						String dataVencimentoGuia = "";
						if (!retorno[21].equals("")) {
							dataVencimentoGuia = retorno[21].toString();
						}
						
						/*********************************************************************
						 * Dados da Arrecadacao OUTROS
						 *********************************************************************/
						Integer tipoFinanciamento = null;
						if (retorno[22] != null) {
							tipoFinanciamento = (Integer) retorno[22];
							helper.setIdTipoFinanciamento(tipoFinanciamento);
						}
						
						Integer lancamentoItemContabilOutros = null;
						if (retorno[23] != null) {
							lancamentoItemContabilOutros = (Integer) retorno[23];
							helper.setIdLancamentoItemContabilOutros(lancamentoItemContabilOutros);
						}
						
						BigDecimal valorDebitos = new BigDecimal(0);
						if (retorno[24] != null) {
							valorDebitos = (BigDecimal) retorno[24];
						}
						
						/*********************************************************************/
						
						if (helper.getIdEpocaPagamento() == 99) {
							// Verifica Epoca de Pagamento para CONTA
							epocaPagamento = retornaValorEpocaPagamento(dataPagamento, dataVencimentoConta);
							helper.setIdEpocaPagamento(epocaPagamento);
							
						}else if (helper.getIdEpocaPagamento() == 98) {
							// Verifica Epoca de Pagamento para GUIA
							epocaPagamento = retornaValorEpocaPagamento(dataPagamento, dataVencimentoGuia);
							helper.setIdEpocaPagamento(epocaPagamento);						
						}
						
						//***********************************************************************
						// Verifica os Casos em que recupera os Primeiros Registros
						//***********************************************************************
						Object[] dadosPagamentoSemContaGuiaDebito = null; 
						
						if (helper.getIdSetorComercial().equals(0)) {
							dadosPagamentoSemContaGuiaDebito = (Object[]) repositorioGerencialArrecadacao
								.pesquisarDadosPagamentoSemContaGuiaDebito(helper.getIdLocalidade());
							
							// Setor Comercial
							helper.setIdSetorComercial((Integer) dadosPagamentoSemContaGuiaDebito[0]);
							// Codigo Setor Comercial
							helper.setIdCodigoSetorComercial((Integer) dadosPagamentoSemContaGuiaDebito[1]);
							// Rota
//							helper.setIdRota((Integer) dadosPagamentoSemContaGuiaDebito[2]);
//							// Quadra
//							helper.setIdQuadra((Integer) dadosPagamentoSemContaGuiaDebito[3]);
//							// Numero Quadra
//							helper.setIdNumeroQuadra((Integer) dadosPagamentoSemContaGuiaDebito[4]);
						}
						
						// Codigo da Rota
//						helper.setCodigoRota(this.pesquisaCodigoRota(helper.getIdRota()).intValue());
						//***********************************************************************
						
						//***********************************************************************
						// Calcula o Valor N�o Identificado
						//***********************************************************************
						idPagamento = (Integer) retorno[26];
						
						valorNaoIdentificado = new BigDecimal(0);
						if (!idPagamento.equals(idPagamentoAnterior)) {
							if (!retorno[25].equals(0)) {
								valorNaoIdentificado = (BigDecimal) retorno[25];
							}
						}
						idPagamentoAnterior = idPagamento;
						//***********************************************************************
						
						// Se ja existe um objeto igual a ele entao soma os
						// valores Agua, Esgoto e N�o Identificado.
						// Um objeto eh igual ao outro se ele tem todos as
						// informacoes de quebra iguais.
						if (listaSimplificada.contains(helper)) {
							posicao = listaSimplificada.indexOf(helper);
							jaCadastrado = (ResumoArrecadacaoAguaEsgotoPorAnoHelper) listaSimplificada.get(posicao);

							// Somatorio de Valor Debitos
							jaCadastrado.setValorDebitos(jaCadastrado.getValorDebitos().add(valorDebitos));
							
							// Somatorio de Valor Nao Identificado
							jaCadastrado.setValorNaoIdentificado(jaCadastrado.getValorNaoIdentificado()
									.add(valorNaoIdentificado));
						} else {
							// Incluimos o Valor Debitos
							helper.setValorDebitos(valorDebitos);
							
							// Incluimos o Valor Nao Identificado 
							helper.setValorNaoIdentificado(helper.getValorNaoIdentificado()
									.add(valorNaoIdentificado));
							
							listaSimplificada.add(helper);
						}
				}
			}
			
			imoveisResumoArrecadacaoOutros.clear();
			imoveisResumoArrecadacaoOutros = null;
			
			imoveisResumoArrecadacaoOutrosConta.clear();
			imoveisResumoArrecadacaoOutrosConta = null;
			
			imoveisResumoArrecadacaoOutrosDebitoACobrar.clear();
			imoveisResumoArrecadacaoOutrosDebitoACobrar = null;
			
			imoveisResumoArrecadacaoOutrosGuiaPagamento.clear();
			imoveisResumoArrecadacaoOutrosGuiaPagamento = null;
			
			/**
			 * para todas as ResumoArrecadacaoAguaEsgotoHelper cria
			 * UnResumoArrecadacao
			 */
			// Declaracao dos Objetos Usados
			int anoMesReferencia = 0;
			int codigoSetorComercial = 0;
//			int numeroQuadra = 0;
			int quantidadeContas = 0;
			int quantidadePagamentos = 0;
			short indicadorRecebidasNomes = 0;
			BigDecimal volumeAgua = new BigDecimal(0);
			BigDecimal volumeEsgoto = new BigDecimal(0);
			BigDecimal volumeNaoIdentificado = new BigDecimal(0);
			valorImpostos = new BigDecimal(0);
			Date ultimaAlteracao = null;
			
			//GSubcategoria subCategoria = null;
			GClienteTipo clienteTipo = null;
			GLigacaoAguaSituacao ligacaoAguaSituacao = null;
			GUnidadeNegocio unidadeNegocio = null;
			GLocalidade localidade = null;
			GLocalidade elo = null;
//			GQuadra quadra = null;
			GLigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;
			GLigacaoEsgotoPerfil ligacaoEsgotoPerfil = null;
			GGerenciaRegional gerenciaRegional = null;
			GSetorComercial setorComercial = null;
			GDocumentoTipo documentoTipo = null;
			GPagamentoSituacao pagamentoSituacao = null;
			GLigacaoAguaPerfil ligacaoAguaPerfil = null;
			GEpocaPagamento epocaPagamento = null;
			GEsferaPoder esferaPoder = null;
			//GCategoria Gcategoria = null;
			GImovelPerfil imovelPerfil = null;
//			GRota rota = null;
			GArrecadacaoForma formaArrecadacao = null;
			GArrecadador agenteArrecadador = null;
			UnResumoArrecadacaoPorAno resumoArrecadacaoAguaEsgoto = null;
			
			BigDecimal valorDebito = new BigDecimal(0);
			
			// Preenche os Objetos com valor 0 que ser�o usados em Credito e Outros
			Integer creditoOrigem = null;
			Integer itemContabelOutros = null;
			Integer itemContabelCredito = null;
			Integer financiamentoTipoOutros = null;
			
			for (int i = 0; i < listaSimplificada.size(); i++) {
				helper = (ResumoArrecadacaoAguaEsgotoPorAnoHelper) listaSimplificada.get(i);

				// Mes ano de referencia
				anoMesReferencia = anoMesReferenciaArrecadacao;
				
				// Codigo Setor Comercial
				if (helper.getIdSetorComercial() != null) {
					codigoSetorComercial = helper.getIdCodigoSetorComercial();
				}
				
				// Numero da quadra
//				if (helper.getIdNumeroQuadra() != null) {
//					numeroQuadra = (helper.getIdNumeroQuadra());
//				}
				
				// Indicador de Recebidas no Mes
				if (helper.getIdIndicadorContasRecebidas() != null) {
					indicadorRecebidasNomes = helper.getIdIndicadorContasRecebidas().shortValue();
				}
				
				// Ultima Alteracao
				ultimaAlteracao = new Date();
				
				// Principal SubCategoria da Principal Categoria do Imovel
				GSubcategoria subCategoria = null;
				if (helper.getIdSubCategoria() != null){
					subCategoria = new GSubcategoria();
					subCategoria.setId(helper.getIdSubCategoria());
				}
				
				// Tipo do Cliente Respons�vel
				if (helper.getIdTipoCliente() != null) {
					clienteTipo = new GClienteTipo();
					clienteTipo.setId(helper.getIdTipoCliente());
				}
				
				// Situacao da Liga��o da �gua
				if (helper.getIdSituacaoLigacaoAgua() != null) {
					ligacaoAguaSituacao = new GLigacaoAguaSituacao();
					ligacaoAguaSituacao.setId(helper.getIdSituacaoLigacaoAgua());
				}
				
				// Unidade de Negocio
				if (helper.getIdUnidadeNegocio() != null) {
					unidadeNegocio = new GUnidadeNegocio();
					unidadeNegocio.setId(helper.getIdUnidadeNegocio());
				}
				
				// Localidade
				if (helper.getIdLocalidade() != null){
					localidade = new GLocalidade();
					localidade.setId(helper.getIdLocalidade());
				}
				
				// Elo
				if (helper.getIdCodigoElo() != null) {
					elo = new GLocalidade();
					elo.setId(helper.getIdCodigoElo());
				}
				
				// Quadra
//				if (helper.getIdQuadra() != null) {
//					quadra = new GQuadra();
//					quadra.setId(helper.getIdQuadra());
//				}
				
				// Situa��o da Liga��o do Esgoto
				if (helper.getIdSituacaoLigacaoEsgoto() != null){
					ligacaoEsgotoSituacao = new GLigacaoEsgotoSituacao();
					ligacaoEsgotoSituacao.setId(helper.getIdSituacaoLigacaoEsgoto());
				}
				
				// Perfil da Liga��o do Esgoto
				if (helper.getIdPerfilLigacaoEsgoto() != null){
					ligacaoEsgotoPerfil = new GLigacaoEsgotoPerfil();
					ligacaoEsgotoPerfil.setId(helper.getIdPerfilLigacaoEsgoto());
				}
				
				// Gerencia Regional
				if (helper.getIdGerenciaRegional() != null) {
					gerenciaRegional = new GGerenciaRegional();
					gerenciaRegional.setId(helper.getIdGerenciaRegional());
				}
				
				// Setor comercial
				if (helper.getIdSetorComercial() != null) {
					setorComercial = new GSetorComercial();
					setorComercial.setId(helper.getIdSetorComercial());
				}
				
				// Tipo do Documento
				if (helper.getIdTipoDocumento() != null) {
					documentoTipo = new GDocumentoTipo();
					documentoTipo.setId(helper.getIdTipoDocumento());
				}
				
				// Situacao do Pagamento
				if (helper.getIdSituacaoPagamento() != null) {
					pagamentoSituacao = new GPagamentoSituacao();
					pagamentoSituacao.setId(helper.getIdSituacaoPagamento());
				}
				
				// Perfil da Liga��o da �gua
				if (helper.getIdPerfilLigacaoAgua() != null){
					ligacaoAguaPerfil = new GLigacaoAguaPerfil();
					ligacaoAguaPerfil.setId(helper.getIdPerfilLigacaoAgua());
				}
				
				// Epoca do Pagamento
				if (helper.getIdEpocaPagamento() != null) {
					epocaPagamento = new GEpocaPagamento();
					epocaPagamento.setId(helper.getIdEpocaPagamento());
				}
				
				// Esfera de Poder
				if (helper.getIdEsferaPoder() != null){
					esferaPoder = new GEsferaPoder();
					esferaPoder.setId(helper.getIdEsferaPoder());
				}
				
				// Principal Categoria do Imovel
				GCategoria categoria = null;
				if (helper.getIdCategoria() != null){
					categoria = new GCategoria();
					categoria.setId(helper.getIdCategoria());
				}
				
				// Perfil do Imovel
				if (helper.getIdPerfilImovel() != null) {
					imovelPerfil = new GImovelPerfil();
					imovelPerfil.setId(helper.getIdPerfilImovel());
				}
				
				// Rota
//				if (helper.getIdRota() != null) {
//					rota = new GRota();
//					rota.setId(helper.getIdRota());
//				}
				
				// Forma Arrecadacao
				if (helper.getIdFormaArrecadacao() != null) {
					formaArrecadacao = new GArrecadacaoForma();
					formaArrecadacao.setId(helper.getIdFormaArrecadacao());
				}
				
				// Agente Arrecadador
				if (helper.getIdAgenteArrecadador() != null) {
					agenteArrecadador = new GArrecadador();
					agenteArrecadador.setId(helper.getIdAgenteArrecadador());
				}
				
				// Indicador Hidrometro
				indicadorHidrometro = helper.getIndicadorHidrometro();
				
				// Ano/Mes Referencia Documento
				anoMesReferenciaDocumento = null;
				if (helper.getAnoMesReferenciaDocumento() != null) {
					anoMesReferenciaDocumento = helper.getAnoMesReferenciaDocumento();
				}
				
				// Valor Nao Identificado
				volumeNaoIdentificado = new BigDecimal(0);
				if (helper.getValorNaoIdentificado() != null) {
					volumeNaoIdentificado = helper.getValorNaoIdentificado();
				}
				
				// Criamos um resumo de Arrecadacao Agua/Esgoto
				resumoArrecadacaoAguaEsgoto = new UnResumoArrecadacaoPorAno(
						anoMesReferencia , codigoSetorComercial    ,
						quantidadeContas , indicadorRecebidasNomes , volumeAgua,
						volumeEsgoto     , ultimaAlteracao         , volumeNaoIdentificado,
						subCategoria     , clienteTipo             , ligacaoAguaSituacao,
						unidadeNegocio   , localidade              , elo,
						ligacaoEsgotoSituacao   , ligacaoEsgotoPerfil,
						gerenciaRegional , setorComercial          , documentoTipo,
						pagamentoSituacao, ligacaoAguaPerfil       , epocaPagamento,
						esferaPoder      , categoria               , imovelPerfil,
						valorImpostos           , indicadorHidrometro,
						anoMesReferenciaDocumento);
				
				resumoArrecadacaoAguaEsgoto.setGerArrecadacaoForma(formaArrecadacao);
				resumoArrecadacaoAguaEsgoto.setGerArrecadador(agenteArrecadador);
				
				resumoArrecadacaoAguaEsgoto.setQuantidadePagamentos(quantidadePagamentos);
				
				// Preenche os Objetos com valor 0 que ser�o usados em CREDITO
				creditoOrigem = new Integer(0);
				itemContabelCredito = new Integer(0);

				resumoArrecadacaoAguaEsgoto.setCreditoOrigemIdCredito(creditoOrigem);
				resumoArrecadacaoAguaEsgoto.setLancamentoItemIdCredito(itemContabelCredito);
				resumoArrecadacaoAguaEsgoto.setValorDocumentosRecebidosCredito(new BigDecimal(0));
				
				/*********************************************************************************
				 * Arrecadacao OUTROS
				 *********************************************************************************/
				// Valor Debitos
				if (helper.getValorDebitos() != null) {
					valorDebito = helper.getValorDebitos();
				}
				
				// Lancamento Item Contabel
				if (helper.getIdLancamentoItemContabilOutros() != null) {
					itemContabelOutros = helper.getIdLancamentoItemContabilOutros();
				}
				
				// Financiamento Tipo
				financiamentoTipoOutros = null;
				if (helper.getIdTipoFinanciamento() != null) {
					financiamentoTipoOutros = helper.getIdTipoFinanciamento();
				}
				
				// Se o Tipo de Financiamento for igual a NULL, despresar o Pagamento
				//if (financiamentoTipoOutros != null) {
					resumoArrecadacaoAguaEsgoto.setLancamentoItemIdOutros(itemContabelOutros);
					resumoArrecadacaoAguaEsgoto.setFinanciamentoTipoIdOutros(financiamentoTipoOutros);
					resumoArrecadacaoAguaEsgoto.setValorDocumentosRecebidosOutros(valorDebito);
					
					// Inicio do Resumo Arrecadacao Outros
					//System.out.print("=====> Inserindo Resumo Arrecadacao OUTROS <=====");
					this.getControladorBatch().inserirObjetoParaBatchGerencial(resumoArrecadacaoAguaEsgoto);
				//}
				
				helper = null;
				resumoArrecadacaoAguaEsgoto = null;
			}
			//System.out.print("=================> FIM do Resumo Arrecadacao OUTROS LOCALIDADE: " + idLocalidade + " <=================");

		} catch (Exception ex) {
			System.out.println("RESUMO ARRECADACAO OUTROS POR ANO ERRO NA LOCALIDADE -> " + idLocalidade);
			ex.printStackTrace();
			throw new EJBException(ex);
		}
	}//gerarResumoArrecadacaoOUTROSPorAno
	
	/**
	 * 
	 * @author Fernando Fontelles
	 * @date 09/06/2010
	 *
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @throws ControladorException
	 */
	public void gerarResumoArrecadacaoCreditosPorAno(
			int idLocalidade, 
			int anoMesReferenciaArrecadacao) throws ControladorException {
		
		try {
			List imoveisResumoArrecadacaoCreditos = this.repositorioGerencialArrecadacao
					.getImoveisResumoArrecadacaoCreditosPorAno(idLocalidade, anoMesReferenciaArrecadacao);
			
			List<ResumoArrecadacaoAguaEsgotoPorAnoHelper> listaSimplificada = new ArrayList();
			
			// Declaracao dos Objetos Usados
			Object obj = null;
			Object[] retorno = null;
			ResumoArrecadacaoAguaEsgotoPorAnoHelper helper = null;
			BigDecimal valorImpostos = null;
			short indicadorHidrometro;
			Integer anoMesReferenciaDocumento = null;
			int posicao = 0;
			ResumoArrecadacaoAguaEsgotoPorAnoHelper jaCadastrado = null;
			
			for (int i = 0; i < imoveisResumoArrecadacaoCreditos.size(); i++) {
				obj = imoveisResumoArrecadacaoCreditos.get(i);
				
				if (obj instanceof Object[]) {
					retorno = (Object[]) obj;
					
					// Montamos um objeto de resumo, com as informacoes do retorno
					helper = new ResumoArrecadacaoAguaEsgotoPorAnoHelper(
							(Integer) retorno[1],  // Gerencia Regional
							(Integer) retorno[2],  // Unidade de Negocio
							(Integer) retorno[3],  // Codigo do Elo
							(Integer) retorno[4],  // Localidade
							(Integer) retorno[5],  // Setor Comercial
//							(Integer) retorno[6],  // Rota
//							(Integer) retorno[7],  // Quadra
							(Integer) retorno[6],  // Perfil Imovel
							(Integer) retorno[7],  // Situa��o Liga��o �gua
							(Integer) retorno[8], // Situa��o Liga��o Esgoto
							(Integer) retorno[9], // Perfil da Liga��o da �gua
							(Integer) retorno[10], // Perfil da Liga��o do Esgoto
							(Integer) retorno[11], // Tipo Documento
							(Integer) retorno[12], // Situa��o do Pagamento
							(Integer) retorno[13], // Indicador de Contas Recebidas
							(Integer) retorno[14], // �poca do Pagamento
							(Integer) retorno[15], // Codigo do Setor Comercial
//							(Integer) retorno[18], // N�mero da Quadra
							(Integer) retorno[16], // Forma de Arrecadacao
							(Integer) retorno[17]);// Agente Arrecadador

					Integer idImovel = (Integer) retorno[0]; // Codigo do imovel que esta sendo processado
					
					if (idImovel != null) {
						// Pesquisamos a esfera de poder do cliente responsavel
						helper.setIdEsferaPoder(repositorioGerencialCadastro.pesquisarEsferaPoderClienteResponsavelImovel(idImovel));
						// Pesquisamos o tipo de cliente responsavel do imovel
						helper.setIdTipoCliente(repositorioGerencialCadastro.pesquisarTipoClienteClienteResponsavelImovel(idImovel));

						// pesquisando a categoria
						// [UC0306] - Obtter principal categoria do im�vel
						Categoria categoria = null;
						categoria = this.getControladorImovel().obterPrincipalCategoriaImovel(idImovel);

						if (categoria != null) {
							helper.setIdCategoria(categoria.getId());

							// Pesquisando a principal subcategoria
							ImovelSubcategoria subcategoria =
								this.getControladorImovel().obterPrincipalSubcategoria(categoria.getId(), idImovel);

							if (subcategoria != null){
								helper.setIdSubCategoria(subcategoria.getComp_id().getSubcategoria().getId());
							}
						}
						
						// Verifica Indicador de Hidrometro para o imovel
						// [UC0307] - Obter Indicador de Exist�ncia de Hidr�metro
						String indicadorHidrometroString = new Integer(this.getControladorImovel().
								obterIndicadorExistenciaHidrometroImovel(idImovel)).toString();
						indicadorHidrometro = new Short(indicadorHidrometroString);
						helper.setIndicadorHidrometro(indicadorHidrometro);
					}else {
						// Esfera de Poder
						helper.setIdEsferaPoder(null);
						// Tipo de Cliente
						helper.setIdTipoCliente(25);
						// Categoria
						helper.setIdCategoria(1);
						// Sub-Categoria
						helper.setIdSubCategoria(10);
						// Indicador de Hidrometro
						helper.setIndicadorHidrometro(new Short("2"));
					}
					
					anoMesReferenciaDocumento = null;
					if (retorno[18] != null) {
						anoMesReferenciaDocumento = (Integer) retorno[18];
						helper.setAnoMesReferenciaDocumento(anoMesReferenciaDocumento);
					}
					
					// Verifica Epoca de Pagamento
					Integer epocaPagamento = null;
					String dataPagamento = retorno[19].toString();
					String dataVencimentoConta = "";
					if (retorno[20] != null) {
						dataVencimentoConta = retorno[20].toString();
					}
					
					//***********************************************************************
					// Verifica o Indicador de Contas Recebidas no Mes
					//***********************************************************************
					helper.setIdIndicadorContasRecebidas(this.verificarIndicadorContasRecebidasNoMes(
							anoMesReferenciaDocumento, dataPagamento));
					//***********************************************************************
					
					//String dataVencimentoGuia = "";
					//if (retorno[24] != null) {
					//	dataVencimentoGuia = retorno[24].toString();
					//}
					
					/*********************************************************************
					 * Dados da Arrecadacao CREDITOS
					 *********************************************************************/
					// Credito Origem
					Integer creditoOrigem = null;
					if (retorno[22] != null) {
						creditoOrigem = (Integer) retorno[22];
						helper.setIdCreditoOrigem(creditoOrigem);
					}
					
					// Lancamento Item Contabel
					Integer itemContabelCredito = null;
					if (retorno[23] != null) {
						itemContabelCredito = (Integer) retorno[23]; 
						helper.setIdLancamentoItemContabilCredito(itemContabelCredito);
					}
					
					// Valor Credito
					BigDecimal valorCredito = new BigDecimal(0);
					if (retorno[24] != null) {
						valorCredito = (BigDecimal) retorno[24];
					}
					
					/*********************************************************************/
					
					if (helper.getIdEpocaPagamento() == 99) {
						// Verifica Epoca de Pagamento para CONTA
						epocaPagamento = retornaValorEpocaPagamento(dataPagamento, dataVencimentoConta);
						helper.setIdEpocaPagamento(epocaPagamento);
					}
					
					//***********************************************************************
					// Verifica os Casos em que recupera os Primeiros Registros
					//***********************************************************************
					Object[] dadosPagamentoSemContaGuiaDebito = null;
					
					if (helper.getIdSetorComercial().equals(0)) {
						dadosPagamentoSemContaGuiaDebito = (Object[]) repositorioGerencialArrecadacao
							.pesquisarDadosPagamentoSemContaGuiaDebito(helper.getIdLocalidade());
						
						// Setor Comercial
						helper.setIdSetorComercial((Integer) dadosPagamentoSemContaGuiaDebito[0]);
						// Codigo Setor Comercial
						helper.setIdCodigoSetorComercial((Integer) dadosPagamentoSemContaGuiaDebito[1]);
						// Rota
//						helper.setIdRota((Integer) dadosPagamentoSemContaGuiaDebito[2]);
//						// Quadra
//						helper.setIdQuadra((Integer) dadosPagamentoSemContaGuiaDebito[3]);
//						// Numero Quadra
//						helper.setIdNumeroQuadra((Integer) dadosPagamentoSemContaGuiaDebito[4]);
					}
					
					// Codigo da Rota
//					helper.setCodigoRota(this.pesquisaCodigoRota(helper.getIdRota()).intValue());
					//***********************************************************************
					
					// Se ja existe um objeto igual a ele entao soma os
					// valores Agua, Esgoto e N�o Identificado.
					// Um objeto eh igual ao outro se ele tem todos as
					// informacoes de quebra iguais.
					if (listaSimplificada.contains(helper)) {
						posicao = listaSimplificada.indexOf(helper);
						jaCadastrado = (ResumoArrecadacaoAguaEsgotoPorAnoHelper) listaSimplificada.get(posicao);

						// Somatorio de Valor Debitos
						jaCadastrado.setValorCredito(jaCadastrado.getValorCredito().add(valorCredito));
					} else {
						// Incluimos o Valor Debitos
						helper.setValorCredito(valorCredito);
						
						listaSimplificada.add(helper);
					}
				}
			}
			
			imoveisResumoArrecadacaoCreditos.clear();
			imoveisResumoArrecadacaoCreditos = null;
			
			/**
			 * para todas as ResumoArrecadacaoAguaEsgotoHelper cria
			 * UnResumoArrecadacao
			 */
			// Declaracao dos Objetos Usados
			int anoMesReferencia = 0;
			int codigoSetorComercial = 0;
//			int numeroQuadra = 0;
			int quantidadeContas = 0;
			int quantidadePagamentos = 0;
			short indicadorRecebidasNomes = 0;
			BigDecimal volumeAgua = new BigDecimal(0);
			BigDecimal volumeEsgoto = new BigDecimal(0);
			BigDecimal volumeNaoIdentificado = new BigDecimal(0);
			valorImpostos = new BigDecimal(0);
			Date ultimaAlteracao = null;
			
			//GSubcategoria subCategoria = null;
			GClienteTipo clienteTipo = null;
			GLigacaoAguaSituacao ligacaoAguaSituacao = null;
			GUnidadeNegocio unidadeNegocio = null;
			GLocalidade localidade = null;
			GLocalidade elo = null;
//			GQuadra quadra = null;
			GLigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;
			GLigacaoEsgotoPerfil ligacaoEsgotoPerfil = null;
			GGerenciaRegional gerenciaRegional = null;
			GSetorComercial setorComercial = null;
			GDocumentoTipo documentoTipo = null;
			GPagamentoSituacao pagamentoSituacao = null;
			GLigacaoAguaPerfil ligacaoAguaPerfil = null;
			GEpocaPagamento epocaPagamento = null;
			GEsferaPoder esferaPoder = null;
			//GCategoria Gcategoria = null;
			GImovelPerfil imovelPerfil = null;
//			GRota rota = null;
			GArrecadacaoForma formaArrecadacao = null;
			GArrecadador agenteArrecadador = null;
			UnResumoArrecadacaoPorAno resumoArrecadacaoAguaEsgoto = null;
			BigDecimal valorCredito = new BigDecimal(0);
			
			// Preenche os Objetos com valor 0 que ser�o usados em Credito e Outros
			Integer creditoOrigem = null;
			Integer itemContabelCredito = null;
			
			for (int i = 0; i < listaSimplificada.size(); i++) {
				helper = (ResumoArrecadacaoAguaEsgotoPorAnoHelper) listaSimplificada.get(i);

				// Mes ano de referencia
				anoMesReferencia = anoMesReferenciaArrecadacao;
				
				// Codigo Setor Comercial
				if (helper.getIdSetorComercial() != null) {
					codigoSetorComercial = helper.getIdCodigoSetorComercial();
				}
				
				// Numero da quadra
//				if (helper.getIdNumeroQuadra() != null) {
//					numeroQuadra = (helper.getIdNumeroQuadra());
//				}
				
				// Indicador de Recebidas no Mes
				if (helper.getIdIndicadorContasRecebidas() != null) {
					indicadorRecebidasNomes = helper.getIdIndicadorContasRecebidas().shortValue();
				}
				
				// Ultima Alteracao
				ultimaAlteracao = new Date();
				
				// Principal SubCategoria da Principal Categoria do Imovel
				GSubcategoria subCategoria = null;
				if (helper.getIdSubCategoria() != null){
					subCategoria = new GSubcategoria();
					subCategoria.setId(helper.getIdSubCategoria());
				}
				
				// Tipo do Cliente Respons�vel
				if (helper.getIdTipoCliente() != null) {
					clienteTipo = new GClienteTipo();
					clienteTipo.setId(helper.getIdTipoCliente());
				}
				
				// Situacao da Liga��o da �gua
				if (helper.getIdSituacaoLigacaoAgua() != null) {
					ligacaoAguaSituacao = new GLigacaoAguaSituacao();
					ligacaoAguaSituacao.setId(helper.getIdSituacaoLigacaoAgua());
				}
				
				// Unidade de Negocio
				if (helper.getIdUnidadeNegocio() != null) {
					unidadeNegocio = new GUnidadeNegocio();
					unidadeNegocio.setId(helper.getIdUnidadeNegocio());
				}
				
				// Localidade
				if (helper.getIdLocalidade() != null){
					localidade = new GLocalidade();
					localidade.setId(helper.getIdLocalidade());
				}
				
				// Elo
				if (helper.getIdCodigoElo() != null) {
					elo = new GLocalidade();
					elo.setId(helper.getIdCodigoElo());
				}
				
				// Quadra
//				if (helper.getIdQuadra() != null) {
//					quadra = new GQuadra();
//					quadra.setId(helper.getIdQuadra());
//				}
				
				// Situa��o da Liga��o do Esgoto
				if (helper.getIdSituacaoLigacaoEsgoto() != null){
					ligacaoEsgotoSituacao = new GLigacaoEsgotoSituacao();
					ligacaoEsgotoSituacao.setId(helper.getIdSituacaoLigacaoEsgoto());
				}
				
				// Perfil da Liga��o do Esgoto
				if (helper.getIdPerfilLigacaoEsgoto() != null){
					ligacaoEsgotoPerfil = new GLigacaoEsgotoPerfil();
					ligacaoEsgotoPerfil.setId(helper.getIdPerfilLigacaoEsgoto());
				}
				
				// Gerencia Regional
				if (helper.getIdGerenciaRegional() != null) {
					gerenciaRegional = new GGerenciaRegional();
					gerenciaRegional.setId(helper.getIdGerenciaRegional());
				}
				
				// Setor comercial
				if (helper.getIdSetorComercial() != null) {
					setorComercial = new GSetorComercial();
					setorComercial.setId(helper.getIdSetorComercial());
				}
				
				// Tipo do Documento
				if (helper.getIdTipoDocumento() != null) {
					documentoTipo = new GDocumentoTipo();
					documentoTipo.setId(helper.getIdTipoDocumento());
				}
				
				// Situacao do Pagamento
				if (helper.getIdSituacaoPagamento() != null) {
					pagamentoSituacao = new GPagamentoSituacao();
					pagamentoSituacao.setId(helper.getIdSituacaoPagamento());
				}
				
				// Perfil da Liga��o da �gua
				if (helper.getIdPerfilLigacaoAgua() != null){
					ligacaoAguaPerfil = new GLigacaoAguaPerfil();
					ligacaoAguaPerfil.setId(helper.getIdPerfilLigacaoAgua());
				}
				
				// Epoca do Pagamento
				if (helper.getIdEpocaPagamento() != null) {
					epocaPagamento = new GEpocaPagamento();
					epocaPagamento.setId(helper.getIdEpocaPagamento());
				}
				
				// Esfera de Poder
				if (helper.getIdEsferaPoder() != null){
					esferaPoder = new GEsferaPoder();
					esferaPoder.setId(helper.getIdEsferaPoder());
				}
				
				// Principal Categoria do Imovel
				GCategoria categoria = null;
				if (helper.getIdCategoria() != null){
					categoria = new GCategoria();
					categoria.setId(helper.getIdCategoria());
				}
				
				// Perfil do Imovel
				if (helper.getIdPerfilImovel() != null) {
					imovelPerfil = new GImovelPerfil();
					imovelPerfil.setId(helper.getIdPerfilImovel());
				}
				
				// Rota
//				if (helper.getIdRota() != null) {
//					rota = new GRota();
//					rota.setId(helper.getIdRota());
//				}
				
				// Forma Arrecadacao
				if (helper.getIdFormaArrecadacao() != null) {
					formaArrecadacao = new GArrecadacaoForma();
					formaArrecadacao.setId(helper.getIdFormaArrecadacao());
				}
				
				// Agente Arrecadador
				if (helper.getIdAgenteArrecadador() != null) {
					agenteArrecadador = new GArrecadador();
					agenteArrecadador.setId(helper.getIdAgenteArrecadador());
				}
				
				// Indicador Hidrometro
				indicadorHidrometro = helper.getIndicadorHidrometro();
				
				// Ano/Mes Referencia Documento
				anoMesReferenciaDocumento = null;
				if (helper.getAnoMesReferenciaDocumento() != null) {
					anoMesReferenciaDocumento = helper.getAnoMesReferenciaDocumento();
				}
				
				// Criamos um resumo de Arrecadacao Agua/Esgoto
				resumoArrecadacaoAguaEsgoto = new UnResumoArrecadacaoPorAno(
						anoMesReferencia , codigoSetorComercial    , 
						quantidadeContas , indicadorRecebidasNomes , volumeAgua,
						volumeEsgoto     , ultimaAlteracao         , volumeNaoIdentificado,
						subCategoria     , clienteTipo             , ligacaoAguaSituacao,
						unidadeNegocio   , localidade              , elo,
						ligacaoEsgotoSituacao   , ligacaoEsgotoPerfil,
						gerenciaRegional , setorComercial          , documentoTipo,
						pagamentoSituacao, ligacaoAguaPerfil       , epocaPagamento,
						esferaPoder      , categoria               , imovelPerfil,
						valorImpostos           , indicadorHidrometro,
						anoMesReferenciaDocumento);
				
				resumoArrecadacaoAguaEsgoto.setGerArrecadacaoForma(formaArrecadacao);
				resumoArrecadacaoAguaEsgoto.setGerArrecadador(agenteArrecadador);
				
				resumoArrecadacaoAguaEsgoto.setQuantidadePagamentos(quantidadePagamentos);
				
				creditoOrigem = new Integer(0);
				itemContabelCredito = new Integer(0);

				// Limpa os valores da Arrecadacao OUTROS
				resumoArrecadacaoAguaEsgoto.setLancamentoItemIdOutros(new Integer(0));
				resumoArrecadacaoAguaEsgoto.setFinanciamentoTipoIdOutros(new Integer(0));
				resumoArrecadacaoAguaEsgoto.setValorDocumentosRecebidosOutros(new BigDecimal(0));
				
				/*********************************************************************************
				 * Arrecadacao CREDITOS
				 *********************************************************************************/
				// Valor Creditos
				if (helper.getValorCredito() != null) {
					valorCredito = helper.getValorCredito();
				}
				
				// Lancamento Item Contabel
				if (helper.getIdLancamentoItemContabilCredito() != null) {
					itemContabelCredito = helper.getIdLancamentoItemContabilCredito();
				}
				
				// Credito Origem
				if (helper.getIdCreditoOrigem() != null) {
					creditoOrigem = helper.getIdCreditoOrigem();
				}
				
				resumoArrecadacaoAguaEsgoto.setValorDocumentosRecebidosCredito(valorCredito);
				resumoArrecadacaoAguaEsgoto.setLancamentoItemIdCredito(itemContabelCredito);
				resumoArrecadacaoAguaEsgoto.setCreditoOrigemIdCredito(creditoOrigem);
				
				// Inicio do Resumo Arrecadacao Creditos
				//System.out.print("=====> Inserindo Resumo Arrecadacao CREDITOS <=====");
				this.getControladorBatch().inserirObjetoParaBatchGerencial(resumoArrecadacaoAguaEsgoto);
				
				helper = null;
				resumoArrecadacaoAguaEsgoto = null;
			}
			//System.out.print("=================> FIM do Resumo Arrecadacao CREDITOS LOCALIDADE: " + idLocalidade + " <=================");

		} catch (Exception ex) {
			System.out.println("RESUMO ARRECADACAO CREDITOS POR ANO ERRO NA LOCALIDADE -> " + idLocalidade);
			ex.printStackTrace();
			throw new EJBException(ex);
		}
	}//gerarResumoArrecadacaoCREDITOS
	
	/**
	 * 
	 * @author Fernando Fontelles
	 * @date 10/06/2010
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @throws ControladorException
	 */
	public void gerarResumoArrecadacaoDevolucaoPorAno(
			int idLocalidade, 
			int anoMesReferenciaArrecadacao) throws ControladorException {
		
		try {
			//System.out.print("=====> LOCALIDADE : " + idLocalidade);
			
			List imoveisResumoArrecadacaoDevolucao = this.repositorioGerencialArrecadacao
					.getImoveisResumoArrecadacaoDevolucaoPorAno(idLocalidade, anoMesReferenciaArrecadacao);
			
			if (imoveisResumoArrecadacaoDevolucao.size() > 0) {
				List<ResumoArrecadacaoAguaEsgotoPorAnoHelper> listaSimplificada = new ArrayList();
				
				// Declaracao dos Objetos Usados
				Object obj = null;
				Object[] retorno = null;
				ResumoArrecadacaoAguaEsgotoPorAnoHelper helper = null;
				BigDecimal valorAgua = new BigDecimal(0);
				BigDecimal valorEsgoto = new BigDecimal(0);
				BigDecimal valorNaoIdentificado = new BigDecimal(0);
				BigDecimal valorImpostos = new BigDecimal(0);
				short indicadorHidrometro;
				Integer anoMesReferenciaDevolucao = null;
				int posicao = 0;
				ResumoArrecadacaoAguaEsgotoPorAnoHelper jaCadastrado = null;
				
				BigDecimal valorDevolucaoClassificadas = null;
				BigDecimal valorDevolucaoNaoClassificadas = null;
				
				// pra cada objeto obter a categoria
				for (int i = 0; i < imoveisResumoArrecadacaoDevolucao.size(); i++) {
					obj = imoveisResumoArrecadacaoDevolucao.get(i);

					if (obj instanceof Object[]) {
						retorno = (Object[]) obj;
					
						helper = new ResumoArrecadacaoAguaEsgotoPorAnoHelper();
						
						helper.setIdGerenciaRegional((Integer) retorno[1]);
						helper.setIdUnidadeNegocio((Integer) retorno[2]);
						helper.setIdCodigoElo((Integer) retorno[3]);
						helper.setIdLocalidade((Integer) retorno[4]);
						helper.setIdSetorComercial((Integer) retorno[5]);
//						helper.setIdRota((Integer) retorno[6]);
//						helper.setIdQuadra((Integer) retorno[7]);
						helper.setIdPerfilImovel((Integer) retorno[6]);
						helper.setIdSituacaoLigacaoAgua((Integer) retorno[7]);
						helper.setIdSituacaoLigacaoEsgoto((Integer) retorno[8]);
						helper.setIdPerfilLigacaoAgua((Integer) retorno[9]);
						helper.setIdPerfilLigacaoEsgoto((Integer) retorno[10]);
						helper.setIdTipoDocumento((Integer) retorno[11]);
						helper.setIdSituacaoPagamento(null);
						helper.setIdEpocaPagamento(0);
						helper.setIdCodigoSetorComercial((Integer) retorno[15]);
//						helper.setIdNumeroQuadra((Integer) retorno[18]);
						// Dados de Devolucao
						valorDevolucaoClassificadas = (BigDecimal) retorno[16];
						valorDevolucaoNaoClassificadas = (BigDecimal) retorno[17];
						helper.setIdDevolucaoSituacaoAtual((Integer) retorno[18]);
						// Deixa os valores igual a zero ou Null
						helper.setValorAgua(valorAgua);
						helper.setValorEsgoto(valorEsgoto);
						helper.setValorNaoIdentificado(valorNaoIdentificado);
						helper.setValorImpostos(valorImpostos);
						helper.setQuantidadeContas(0);
						helper.setQuantidadePagamentos(0);
						helper.setIdFormaArrecadacao(null);
						helper.setIdAgenteArrecadador(null);
						helper.setAnoMesReferenciaDocumento(null);
						
						Integer idImovel = (Integer) retorno[0]; // Codigo do imovel que esta sendo processado
						
						// Pesquisamos a esfera de poder do cliente responsavel
						helper.setIdEsferaPoder(repositorioGerencialCadastro.pesquisarEsferaPoderClienteResponsavelImovel(idImovel));
						// Pesquisamos o tipo de cliente responsavel do imovel
						helper.setIdTipoCliente(repositorioGerencialCadastro.pesquisarTipoClienteClienteResponsavelImovel(idImovel));

						// pesquisando a categoria
						// [UC0306] - Obtter principal categoria do im�vel
						Categoria categoria = null;
						categoria = this.getControladorImovel().obterPrincipalCategoriaImovel(idImovel);
						
						if (categoria != null) {
							helper.setIdCategoria(categoria.getId());

							// Pesquisando a principal subcategoria
							ImovelSubcategoria subcategoria =
								this.getControladorImovel().obterPrincipalSubcategoria(categoria.getId(), idImovel);

							if (subcategoria != null){
								helper.setIdSubCategoria(subcategoria.getComp_id().getSubcategoria().getId());
							}
						}
						
						// Verifica Indicador de Hidrometro para o imovel
						// [UC0307] - Obter Indicador de Exist�ncia de Hidr�metro
						String indicadorHidrometroString = new Integer(this.getControladorImovel().
								obterIndicadorExistenciaHidrometroImovel(idImovel)).toString();
						indicadorHidrometro = new Short(indicadorHidrometroString);
						helper.setIndicadorHidrometro(indicadorHidrometro);
						
						//***********************************************************************
						// Verifica o Indicador de Contas Recebidas no Mes
						//***********************************************************************
						String dataDevolucao = retorno[12].toString();
						anoMesReferenciaDevolucao = null;
						if (retorno[13] != null) {
							anoMesReferenciaDevolucao = (Integer) retorno[13];
						}
						helper.setIdIndicadorContasRecebidas(this.verificarIndicadorContasRecebidasNoMes(
								anoMesReferenciaDevolucao, dataDevolucao));
						//***********************************************************************
						
						// Faz o Agrupamento
						if (listaSimplificada.contains(helper)) {
							
							posicao = listaSimplificada.indexOf(helper);
							jaCadastrado = (ResumoArrecadacaoAguaEsgotoPorAnoHelper) listaSimplificada.get(posicao);
							
							// Somatorio de Valor Devolucoes Classificadas
							jaCadastrado.setValorDevolucoesClassificadas(
									jaCadastrado.getValorDevolucoesClassificadas()
									.add(valorDevolucaoClassificadas));
							
							// Somatorio de Valor Devolucoes Nao Classificadas
							jaCadastrado.setValorDevolucoesNaoClassificadas(
									jaCadastrado.getValorDevolucoesNaoClassificadas()
									.add(valorDevolucaoNaoClassificadas));
						} else {
							// Incluimos o Valor Devolucoes Classificadas
							helper.setValorDevolucoesClassificadas(valorDevolucaoClassificadas);
							
							// Incluimos o Valor Devolucoes Nao Classificadas
							helper.setValorDevolucoesNaoClassificadas(valorDevolucaoNaoClassificadas);
							
							listaSimplificada.add(helper);
						}
					}
				}
				
				imoveisResumoArrecadacaoDevolucao.clear();
				imoveisResumoArrecadacaoDevolucao = null;
				/**
				 * para todas as ResumoArrecadacaoAguaEsgotoHelper cria
				 * UnResumoArrecadacao
				 */
				// Declaracao dos Objetos Usados
				int anoMesReferencia = 0;
				int codigoSetorComercial = 0;
//				int numeroQuadra = 0;
				int quantidadeContas = 0;
				int quantidadePagamentos = 0;
				short indicadorRecebidasNomes = 0;
				BigDecimal volumeAgua = null;
				BigDecimal volumeEsgoto = null;
				Date ultimaAlteracao = null;
				BigDecimal volumeNaoIdentificado = null;
				Integer anoMesReferenciaDocumento = null;
				
				GSubcategoria subCategoria = null;
				GClienteTipo clienteTipo = null;
				GLigacaoAguaSituacao ligacaoAguaSituacao = null;
				GUnidadeNegocio unidadeNegocio = null;
				GLocalidade localidade = null;
				GLocalidade elo = null;
//				GQuadra quadra = null;
				GLigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;
				GLigacaoEsgotoPerfil ligacaoEsgotoPerfil = null;
				GGerenciaRegional gerenciaRegional = null;
				GSetorComercial setorComercial = null;
				GDocumentoTipo documentoTipo = null;
				GPagamentoSituacao pagamentoSituacao = null;
				GLigacaoAguaPerfil ligacaoAguaPerfil = null;
				GEpocaPagamento epocaPagamento = null;
				GEsferaPoder esferaPoder = null;
				GCategoria Gcategoria = null;
				GImovelPerfil imovelPerfil = null;
//				GRota rota = null;
				GArrecadacaoForma formaArrecadacao = null;
				GArrecadador agenteArrecadador = null;
				UnResumoArrecadacaoPorAno resumoArrecadacaoDevolucao = null;
				GDevolucaoSituacao devolucaoSituacao = null;
				
				// Preenche os Objetos com valor 0 que ser�o usados em Credito e Outros
				Integer creditoOrigem = null;
				Integer itemContabelOutros = null;
				Integer itemContabelCredito = null;
				Integer financiamentoTipo = null;
				
				for (int i = 0; i < listaSimplificada.size(); i++) {
					helper = (ResumoArrecadacaoAguaEsgotoPorAnoHelper) listaSimplificada.get(i);

					// Montamos todo o agrupamento necessario
					
					// Mes ano de referencia
					anoMesReferencia = anoMesReferenciaArrecadacao;
					
					// Codigo Setor Comercial
					if (helper.getIdSetorComercial() != null) {
						codigoSetorComercial = helper.getIdCodigoSetorComercial();
					}
					
					// Numero da quadra
//					if (helper.getIdNumeroQuadra() != null) {
//						numeroQuadra = (helper.getIdNumeroQuadra());
//					}
					
					// Quantidades de Conta
					if (helper.getQuantidadeContas() != null) {
						quantidadeContas = helper.getQuantidadeContas();
					}
					
					// Quantidades de Pagamentos
					if (helper.getQuantidadePagamentos() != null) {
						quantidadePagamentos = helper.getQuantidadePagamentos();
					}
					
					// Indicador de Recebidas no Mes
					if (helper.getIdIndicadorContasRecebidas() != null) {
						indicadorRecebidasNomes = helper.getIdIndicadorContasRecebidas().shortValue();
					}
					
					// Volume Agua
					volumeAgua = new BigDecimal(0);
					if (helper.getValorAgua() != null) {
						volumeAgua = helper.getValorAgua();
					}
					
					// Volume Esgoto
					volumeEsgoto = new BigDecimal(0);
					if (helper.getValorAgua() != null) {
						volumeEsgoto = helper.getValorEsgoto();
					}
					
					// Ultima Alteracao
					ultimaAlteracao = new Date();
					
					// Volume Nao Identificado
					volumeNaoIdentificado = new BigDecimal(0);
					if (helper.getValorNaoIdentificado() != null) {
						volumeNaoIdentificado = helper.getValorNaoIdentificado();
					}
					
					// Principal SubCategoria da Principal Categoria do Imovel
					if (helper.getIdSubCategoria() != null){
						subCategoria = new GSubcategoria();
						subCategoria.setId(helper.getIdSubCategoria());
					}
					
					// Tipo do Cliente Respons�vel
					if (helper.getIdTipoCliente() != null) {
						clienteTipo = new GClienteTipo();
						clienteTipo.setId(helper.getIdTipoCliente());
					}
					
					// Situacao da Liga��o da �gua
					if (helper.getIdSituacaoLigacaoAgua() != null) {
						ligacaoAguaSituacao = new GLigacaoAguaSituacao();
						ligacaoAguaSituacao.setId(helper.getIdSituacaoLigacaoAgua());
					}
					
					// Unidade de Negocio
					if (helper.getIdUnidadeNegocio() != null) {
						unidadeNegocio = new GUnidadeNegocio();
						unidadeNegocio.setId(helper.getIdUnidadeNegocio());
					}
					
					// Localidade
					if (helper.getIdLocalidade() != null){
						localidade = new GLocalidade();
						localidade.setId(helper.getIdLocalidade());
					}
					
					// Elo
					if (helper.getIdCodigoElo() != null) {
						elo = new GLocalidade();
						elo.setId(helper.getIdCodigoElo());
					}
					
					// Quadra
//					if (helper.getIdQuadra() != null) {
//						quadra = new GQuadra();
//						quadra.setId(helper.getIdQuadra());
//					}
					
					// Situa��o da Liga��o do Esgoto
					if (helper.getIdSituacaoLigacaoEsgoto() != null){
						ligacaoEsgotoSituacao = new GLigacaoEsgotoSituacao();
						ligacaoEsgotoSituacao.setId(helper.getIdSituacaoLigacaoEsgoto());
					}
					
					// Perfil da Liga��o do Esgoto
					if (helper.getIdPerfilLigacaoEsgoto() != null){
						ligacaoEsgotoPerfil = new GLigacaoEsgotoPerfil();
						ligacaoEsgotoPerfil.setId(helper.getIdPerfilLigacaoEsgoto());
					}
					
					// Gerencia Regional
					if (helper.getIdGerenciaRegional() != null) {
						gerenciaRegional = new GGerenciaRegional();
						gerenciaRegional.setId(helper.getIdGerenciaRegional());
					}
					
					// Setor comercial
					if (helper.getIdSetorComercial() != null) {
						setorComercial = new GSetorComercial();
						setorComercial.setId(helper.getIdSetorComercial());
					}
					
					// Tipo do Documento
					if (helper.getIdTipoDocumento() != null) {
						documentoTipo = new GDocumentoTipo();
						documentoTipo.setId(helper.getIdTipoDocumento());
					}
					
					// Situacao do Pagamento
					if (helper.getIdSituacaoPagamento() != null) {
						pagamentoSituacao = new GPagamentoSituacao();
						pagamentoSituacao.setId(helper.getIdSituacaoPagamento());
					}
					
					// Perfil da Liga��o da �gua
					if (helper.getIdPerfilLigacaoAgua() != null){
						ligacaoAguaPerfil = new GLigacaoAguaPerfil();
						ligacaoAguaPerfil.setId(helper.getIdPerfilLigacaoAgua());
					}
					
					// Epoca do Pagamento
					if (helper.getIdEpocaPagamento() != null) {
						epocaPagamento = new GEpocaPagamento();
						epocaPagamento.setId(helper.getIdEpocaPagamento());
					}
					
					// Esfera de Poder
					if (helper.getIdEsferaPoder() != null){
						esferaPoder = new GEsferaPoder();
						esferaPoder.setId(helper.getIdEsferaPoder());
					}
					
					// Principal Categoria do Imovel
					if (helper.getIdCategoria() != null){
						Gcategoria = new GCategoria();
						Gcategoria.setId(helper.getIdCategoria());
					}
					
					// Perfil do Imovel
					if (helper.getIdPerfilImovel() != null) {
						imovelPerfil = new GImovelPerfil();
						imovelPerfil.setId(helper.getIdPerfilImovel());
					}
					
					// Rota
//					if (helper.getIdRota() != null) {
//						rota = new GRota();
//						rota.setId(helper.getIdRota());
//					}
					
					// Forma Arrecadacao
					if (helper.getIdFormaArrecadacao() != null) {
						formaArrecadacao = new GArrecadacaoForma();
						formaArrecadacao.setId(helper.getIdFormaArrecadacao());
					}
					
					// Agente Arrecadador
					if (helper.getIdAgenteArrecadador() != null) {
						agenteArrecadador = new GArrecadador();
						agenteArrecadador.setId(helper.getIdAgenteArrecadador());
					}
					
					// Valor Impostos
					valorImpostos = new BigDecimal(0);
					if (helper.getValorImpostos() != null) {
						valorImpostos = helper.getValorImpostos();
					}
					
					// Indicador Hidrometro
					indicadorHidrometro = helper.getIndicadorHidrometro();
					
					// Ano/Mes Referencia Documento
					anoMesReferenciaDocumento = null;
					if (helper.getAnoMesReferenciaDocumento() != null) {
						anoMesReferenciaDocumento = helper.getAnoMesReferenciaDocumento();
					}
					
					//******************************************
					// Dados de Devolucao
					//******************************************
					valorDevolucaoClassificadas = null;
					if (helper.getValorDevolucoesClassificadas() != null) {
						valorDevolucaoClassificadas = helper.getValorDevolucoesClassificadas();
					}
					
					valorDevolucaoNaoClassificadas = null;
					if (helper.getValorDevolucoesNaoClassificadas() != null) {
						valorDevolucaoNaoClassificadas = helper.getValorDevolucoesNaoClassificadas();
					}
					
					if (helper.getIdDevolucaoSituacaoAtual() != null) {
						devolucaoSituacao = new GDevolucaoSituacao();
						devolucaoSituacao.setId(helper.getIdDevolucaoSituacaoAtual());
					}
					
					
					// Criamos um resumo de Arrecadacao Agua/Esgoto
					resumoArrecadacaoDevolucao = new UnResumoArrecadacaoPorAno(
							anoMesReferencia , codigoSetorComercial    ,
							quantidadeContas , indicadorRecebidasNomes , volumeAgua,
							volumeEsgoto     , ultimaAlteracao         , volumeNaoIdentificado,
							subCategoria     , clienteTipo             , ligacaoAguaSituacao,
							unidadeNegocio   , localidade              , elo,
							ligacaoEsgotoSituacao   , ligacaoEsgotoPerfil,
							gerenciaRegional , setorComercial          , documentoTipo,
							pagamentoSituacao, ligacaoAguaPerfil       , epocaPagamento,
							esferaPoder      , Gcategoria              , imovelPerfil,
							valorImpostos           , indicadorHidrometro,
							anoMesReferenciaDocumento);
					
					resumoArrecadacaoDevolucao.setGerArrecadacaoForma(formaArrecadacao);
					resumoArrecadacaoDevolucao.setGerArrecadador(agenteArrecadador);
					
					resumoArrecadacaoDevolucao.setQuantidadePagamentos(quantidadePagamentos);
					
					// Preenche os Objetos com valor 0 que ser�o usados em Credito e Outros
					creditoOrigem = new Integer(0);
					itemContabelOutros = new Integer(0);
					itemContabelCredito = new Integer(0);
					financiamentoTipo = new Integer(0);
					
					resumoArrecadacaoDevolucao.setCreditoOrigemIdCredito(creditoOrigem);
					resumoArrecadacaoDevolucao.setLancamentoItemIdCredito(itemContabelCredito);
					resumoArrecadacaoDevolucao.setValorDocumentosRecebidosCredito(new BigDecimal(0));
					
					resumoArrecadacaoDevolucao.setLancamentoItemIdOutros(itemContabelOutros);
					resumoArrecadacaoDevolucao.setFinanciamentoTipoIdOutros(financiamentoTipo);
					resumoArrecadacaoDevolucao.setValorDocumentosRecebidosOutros (new BigDecimal(0));
					
					// Valores de Devolucao
					resumoArrecadacaoDevolucao.setValorDevolucoesClassificadas(valorDevolucaoClassificadas);
					resumoArrecadacaoDevolucao.setValorDevolucoesNaoClassificadas(valorDevolucaoNaoClassificadas);
					resumoArrecadacaoDevolucao.setGerDevolucaoSituacao(devolucaoSituacao);					
					
					// Insere em UN_RESUMO_ARRECADACAO
					//System.out.print("=====> Inserindo Resumo Arrecadacao DEVOLUCAO <=====");
					this.getControladorBatch().inserirObjetoParaBatchGerencial(resumoArrecadacaoDevolucao);
					
					helper = null;
					resumoArrecadacaoDevolucao = null;
				}
			}
			//System.out.print("=================> FIM do Resumo Arrecadacao DEVOLUCAO LOCALIDADE: " + idLocalidade + " <=======================================");
			
		} catch (Exception ex) {
			System.out.println("RESUMO ARRECADACAO DEVOLUCAO ERRO NA LOCALIDADE -> " + idLocalidade);
			ex.printStackTrace();
			throw new EJBException(ex);
		}
	}//gerarResumoArrecadacaoDevolucao

	/**
	 * Carrega os dados do resumo da arrecada��o do gerencial
	 * 
	 * @author Erivan Sousa
	 * @date 19/04/2012
	 * 
	 * @throws ControladorException
	 */
	public void executarCargaResumoArrecadacaoGerencialPorAno(
			int idFuncionalidadeIniciada)	throws ControladorException {
		
		int idUnidadeIniciada = 0;

		/*
		 * Registrar o in�cio do processamento da Unidade de
		 * Processamento do Batch
		*/		
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(
						idFuncionalidadeIniciada,
						UnidadeProcessamento.FUNCIONALIDADE,
						0);
		
		try {
			
			repositorioGerencialArrecadacao.executarCargaResumoArrecadacaoGerencialPorAno();
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,	idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}
	
	/**
	 * 
	 * @author Rodrigo Cabral
	 * @date 24/04/2012
	 * 
	 * @throws ErroRepositorioException
	 */
	public void executarCargaLigacoesEconomiasPorAno(
			int idFuncionalidadeIniciada)	throws ControladorException {
		
		int idUnidadeIniciada = 0;

		/*
		 * Registrar o in�cio do processamento da Unidade de
		 * Processamento do Batch
		*/		
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(
						idFuncionalidadeIniciada,
						UnidadeProcessamento.FUNCIONALIDADE,
						0);
		
		try {
		
			System.out.println("Iniciou Carga Liga��es Economias Por Ano");
			repositorioGerencialArrecadacao.executarCargaLigacoesEconomiasPorAno();
			System.out.println("Finalizou Carga Liga��es Economias Por Ano");
			
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,	idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}
	
	/**
	 * 
	 * @author Rodrigo Cabral
	 * @date 24/04/2012
	 * 
	 * @throws ErroRepositorioException
	 */
	public void executarCargaIndicadorLigacaoEconomiaPorAno(
			int idFuncionalidadeIniciada)	throws ControladorException {
		
		int idUnidadeIniciada = 0;

		/*
		 * Registrar o in�cio do processamento da Unidade de
		 * Processamento do Batch
		*/		
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(
						idFuncionalidadeIniciada,
						UnidadeProcessamento.FUNCIONALIDADE,
						0);
		
		try {
		
			System.out.println("Iniciou Carga Indicador Liga��o Economia Por Ano");
			repositorioGerencialArrecadacao.executarCargaIndicadorLigacaoEconomiaPorAno();
			System.out.println("Finalizou Carga Indicador Liga��o Economia Por Ano");
			
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,	idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}
	
	/**
	 * 
	 * @author Rodrigo Cabral
	 * @date 24/04/2012
	 * 
	 * @throws ErroRepositorioException
	 */
	public void executarCargaResumoParcelamentoPorAno(
			int idFuncionalidadeIniciada)	throws ControladorException {
		
		int idUnidadeIniciada = 0;

		/*
		 * Registrar o in�cio do processamento da Unidade de
		 * Processamento do Batch
		*/		
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(
						idFuncionalidadeIniciada,
						UnidadeProcessamento.FUNCIONALIDADE,
						0);
		
		try {
		
			System.out.println("Iniciou Carga Resumo Parcelamento Por Ano");
			repositorioGerencialArrecadacao.executarCargaResumoParcelamentoPorAno();
			System.out.println("Finalizou Carga Resumo Parcelamento Por Ano");
			
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,	idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}
	
	/**
	 * 
	 * @author Rodrigo Cabral
	 * @date 24/04/2012
	 * 
	 * @throws ErroRepositorioException
	 */
	public void executarCargaResumoPendenciaPorAno(
			int idFuncionalidadeIniciada)	throws ControladorException {
		
		int idUnidadeIniciada = 0;

		/*
		 * Registrar o in�cio do processamento da Unidade de
		 * Processamento do Batch
		*/		
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(
						idFuncionalidadeIniciada,
						UnidadeProcessamento.FUNCIONALIDADE,
						0);
		
		try {
		
			System.out.println("Iniciou Carga Resumo Pendencia Por Ano");
			repositorioGerencialArrecadacao.executarCargaResumoPendenciaPorAno();
			System.out.println("Finalizou Carga Resumo Pendencia Por Ano");
			
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,	idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}
	
	/**
	 * 
	 * @author Rodrigo Cabral
	 * @date 24/04/2012
	 * 
	 * @throws ErroRepositorioException
	 */
	public void executarCargaPendenciaSemQuadraVariosAnos(
			int idFuncionalidadeIniciada)	throws ControladorException {
		
		int idUnidadeIniciada = 0;

		/*
		 * Registrar o in�cio do processamento da Unidade de
		 * Processamento do Batch
		*/		
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(
						idFuncionalidadeIniciada,
						UnidadeProcessamento.FUNCIONALIDADE,
						0);
		
		try {
		
			System.out.println("Iniciou Carga Pendencia Sem Quadra Varios Anos");
			repositorioGerencialArrecadacao.executarCargaPendenciaSemQuadraVariosAnos();
			System.out.println("Finalizou Carga Pendencia Sem Quadra Varios Anos");
			
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,	idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}
	
	/**
	 * 
	 * @author Rafael Corr�a
	 * @date 17/04/2013
	 *
	 * @throws ControladorException
	 */
	public void gerarResumoArrecadacao(int idFuncionalidadeIniciada) throws ControladorException {
		
		int idUnidadeIniciada = 0;

		/*
		 * Registrar o in�cio do processamento da Unidade de
		 * Processamento do Batch
		*/		
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(
						idFuncionalidadeIniciada,
						UnidadeProcessamento.FUNCIONALIDADE,
						0);
		
		try {
		
			repositorioGerencialArrecadacao.gerarResumoArrecadacao();
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,	idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}
}