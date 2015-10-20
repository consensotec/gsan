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

import gcom.batch.Relatorio;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.imovel.bean.ImovelRelatorioHelper;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaSituacao;
import gcom.fachada.Fachada;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.medicao.MedicaoHistorico;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe respons�vel por criar o relat�rio de im�vel manter
 * 
 * @author Rafael Corr�a
 * @created 11 de Julho de 2005
 */
public class RelatorioManterImovelOutrosCriterios extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor da classe RelatorioAnaliseFisicoQuimicaAgua
	 */
	public RelatorioManterImovelOutrosCriterios(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_IMOVEL_OUTROS_CRITERIOS_MANTER);
	}

	/**
	 * <<Descri��o do m�todo>>
	 * 
	 * @param bairros
	 *            Description of the Parameter
	 * @param bairroParametros
	 *            Description of the Parameter
	 * @return Descri��o do retorno
	 * @exception RelatorioVazioException
	 *                Descri��o da exce��o
	 */

	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		Imovel imovelParametrosInicial = (Imovel) getParametro("imovelParametrosInicial");
		Imovel imovelParametrosFinal = (Imovel) getParametro("imovelParametrosFinal");
		ClienteImovel clienteImovelParametros = (ClienteImovel) getParametro("clienteImovelParametros");
		Municipio municipio = (Municipio) getParametro("municipio");
		Bairro bairro = (Bairro) getParametro("bairro");
		MedicaoHistorico medicaoHistoricoParametrosInicial = (MedicaoHistorico) getParametro("medicaoHistoricoParametrosInicial");
		MedicaoHistorico medicaoHistoricoParametrosFinal = (MedicaoHistorico) getParametro("medicaoHistoricoParametrosFinal");
		ConsumoHistorico consumoHistoricoParametrosInicial = (ConsumoHistorico) getParametro("consumoHistoricoParametrosInicial");
		ConsumoHistorico consumoHistoricoParametrosFinal = (ConsumoHistorico) getParametro("consumoHistoricoParametrosFinal");
		GerenciaRegional gerenciaRegional = (GerenciaRegional) getParametro("gerenciaRegionalParametro");
		Categoria categoria = (Categoria) getParametro("categoria");
		Subcategoria subcategoria = (Subcategoria) getParametro("subcategoria");
		CobrancaSituacao cobrancaSituacao = (CobrancaSituacao) getParametro("cobrancaSituacao");
		String indicadorMedicao = (String) getParametro("indicadorMedicaoParametro");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		String indicadorNivelEsgotamento = (String) getParametro("indicadorNivelEsgotamento");

		// Recupera os par�metros utilizados na forma��o da query

		// id da genrencia regional
		String gerenciaRegionalPesquisa = (String) getParametro("gerenciaRegional");
		// id da unidade negocio
		String idUnidadeNegoio = (String) getParametro("idUnidadeNegocio");

		// numero da quadra origem
		String qudraOrigem = (String) getParametro("quadraOrigem");
		// numero quadra destino
		String quadraDestino = (String) getParametro("quadraDestino");
		// lote origem
		String loteOrigem = (String) getParametro("loteOrigem");
		// lote destino
		String loteDestino = (String) getParametro("loteDestino");
		// cep
		String cep = (String) getParametro("cep");
		// id localidade origem
		String localidadeOrigem = (String) getParametro("localidadeOrigem");
		// id localidade destino
		String localidadeDestino = (String) getParametro("localidadeDestino");
		// setor comercial origem CD
		String setorComercialOrigemCD = (String) getParametro("setorComercialOrigemCD");
		// setor comercial destino CD
		String setorComercialDestinoCD = (String) getParametro("setorComercialDestinoCD");
		// cliente ID
		String clienteID = (String) getParametro("clienteID");
		// municipio ID
		String municipioID = (String) getParametro("municipioID");
		// bairro ID
		String bairroID = (String) getParametro("bairroID");
		// logradouro ID
		String logradouroID = (String) getParametro("logradouroID");
		// cliente relacao tipo ID
		String clienteRelacaoTipoID = (String) getParametro("clienteRelacaoTipoID");
		// cliente tipo ID
		String clienteTipoID = (String) getParametro("clienteTipoID");
		// imovel condominio ID
		String imovelCondominioID = (String) getParametro("imovelCondominioID");
		// imovel Principal ID
		String imovelPrincipalID = (String) getParametro("imovelPrincipalID");
		// nome Conta ID
		// String nomeContaID = (String) getParametro("nomeContaID");
		// situacao Agua
		String situacaoAgua = (String) getParametro("situacaoAgua");
		// situacao Ligacao Esgoto
		String situacaoLigacaoEsgoto = (String) getParametro("situacaoLigacaoEsgoto");
		// consumo Minimo Inicial
		String consumoMinimoInicial = (String) getParametro("consumoMinimoInicial");
		// consumo Minimo Final
		String consumoMinimoFinal = (String) getParametro("consumoMinimoFinal");
		// consumo Minimo Fixado Esgoto Inicial
		String consumoMinimoFixadoEsgotoInicial = (String) getParametro("consumoMinimoFixadoEsgotoInicial");
		// consumo Minimo Fixado Esgoto Final
		String consumoMinimoFixadoEsgotoFinal = (String) getParametro("consumoMinimoFixadoEsgotoFinal");
		// intervalo Percentual Esgoto Inicial
		String intervaloPercentualEsgotoInicial = (String) getParametro("intervaloPercentualEsgotoInicial");
		// intervalor Percentual Esgoto Final
		String intervaloPercentualEsgotoFinal = (String) getParametro("intervaloPercentualEsgotoFinal");
		// indicador Medicao
		String indicadorMedicaoPesquisa = (String) getParametro("indicadorMedicao");
		// tipo Medicao ID
		String tipoMedicaoID = (String) getParametro("tipoMedicaoID");
		// intervalo Media Minima Imovel Inicial
		String intervaloMediaMinimaImovelInicial = (String) getParametro("intervaloMediaMinimaImovelInicial");
		// intervalo Media Minima Imovel Final
		String intervaloMediaMinimaImoveFinal = (String) getParametro("intervaloMediaMinimaImoveFinal");
		// intervalo Media Minima Hidrometro Inicial
		String intervaloMediaMinimaHidrometroInicial = (String) getParametro("intervaloMediaMinimaHidrometroInicial");
		// intervalo Media Minima Hidrometro Final
		String intervaloMediaMinimaHidrometroFinal = (String) getParametro("intervaloMediaMinimaHidrometroFinal");
		// perfil Imovel ID
		String perfilImovelID = (String) getParametro("perfilImovelID");
		// categoria Imovel ID
		String categoriaImovelID = (String) getParametro("categoriaImovelID");
		// sub categoria ID
		String subCategoriaID = (String) getParametro("subCategoriaID");
		// quantidade Economias Inicial
		String quantidadeEconomiasInicial = (String) getParametro("quantidadeEconomiasInicial");
		// quantidade Economias Final
		String quantidadeEconomiasFinal = (String) getParametro("quantidadeEconomiasFinal");
		// numero Pontos Inicial
		String numeroPontosInicial = (String) getParametro("numeroPontosInicial");
		// numero Pontos Final
		String numeroPontosFinal = (String) getParametro("numeroPontosFinal");
		// numero Moradores Inicial
		String numeroMoradoresInicial = (String) getParametro("numeroMoradoresInicial");
		// numero Moradoras Final
		String numeroMoradoresFinal = (String) getParametro("numeroMoradoresFinal");
		// area Construida Inicial
		String areaConstruidaInicial = (String) getParametro("areaConstruidaInicial");
		// area Construida Final
		String areaConstruidaFinal = (String) getParametro("areaConstruidaFinal");
		// area Construida Faixa
		String areaConstruidaFaixa = (String) getParametro("areaConstruidaFaixa");
		// poco Tipo ID
		String pocoTipoID = (String) getParametro("pocoTipoID");
		// tipo Situacao Faturamento ID
		String tipoSituacaoFaturamentoID = (String) getParametro("tipoSituacaoFaturamentoID");
		// tipo Situacao Especial Cobranca ID
		String tipoSituacaoEspecialCobrancaID = (String) getParametro("tipoSituacaoEspecialCobrancaID");
		// situacao Cobranca ID
		String situacaoCobrancaID = (String) getParametro("situacaoCobrancaID");
		// dia Vencimento Alternativo
		String diaVencimentoAlternativo = (String) getParametro("diaVencimentoAlternativo");
		// ocorrencia Cadastro
		String ocorrenciaCadastro = (String) getParametro("ocorrenciaCadastro");
		// tarifa Consumo
		String tarifaConsumo = (String) getParametro("tarifaConsumo");
		// anormalidade Elo
		String anormalidadeElo = (String) getParametro("anormalidadeElo");

		// codigo da rota inicial
		String cdRotaInicial = (String) getParametro("cdRotaInicial");
		// codigo da rota final
		String cdRotaFinal = (String) getParametro("cdRotaFinal");

		// sequencial rota inicial
		String sequencialRotaInicial = (String) getParametro("sequencialRotaInicial");
		// sequencial rota final
		String sequencialRotaFinal = (String) getParametro("sequencialRotaFinal");
		
		//indicador cpf/cnpj informado
		String indicadorCpfCnpjInformado = (String) getParametro("indicadorCpfCnpjInformado");

		Fachada fachada = Fachada.getInstancia();

		Collection imoveisRelatoriosHelper = fachada
				.gerarRelatorioImovelOutrosCriterios(imovelCondominioID,
						imovelPrincipalID, situacaoAgua, consumoMinimoInicial,
						consumoMinimoFinal, situacaoLigacaoEsgoto,
						consumoMinimoFixadoEsgotoInicial,
						consumoMinimoFixadoEsgotoFinal,
						intervaloPercentualEsgotoInicial,
						intervaloPercentualEsgotoFinal,
						intervaloMediaMinimaImovelInicial,
						intervaloMediaMinimaImoveFinal,
						intervaloMediaMinimaHidrometroInicial,
						intervaloMediaMinimaHidrometroFinal, perfilImovelID,
						pocoTipoID, tipoSituacaoFaturamentoID,
						situacaoCobrancaID, tipoSituacaoEspecialCobrancaID,
						anormalidadeElo, areaConstruidaInicial,
						areaConstruidaFinal, ocorrenciaCadastro, tarifaConsumo,
						gerenciaRegionalPesquisa, localidadeOrigem,
						localidadeDestino, setorComercialOrigemCD,
						setorComercialDestinoCD, qudraOrigem, quadraDestino,
						loteOrigem, loteDestino, cep, logradouroID, bairroID,
						municipioID, tipoMedicaoID, indicadorMedicaoPesquisa,
						subCategoriaID, categoriaImovelID,
						quantidadeEconomiasInicial, quantidadeEconomiasFinal,
						diaVencimentoAlternativo, clienteID, clienteTipoID,
						clienteRelacaoTipoID, numeroPontosInicial,
						numeroPontosFinal, numeroMoradoresInicial,
						numeroMoradoresFinal, areaConstruidaFaixa,
						idUnidadeNegoio,cdRotaInicial,
						cdRotaFinal,sequencialRotaInicial,
						sequencialRotaFinal, indicadorNivelEsgotamento, indicadorCpfCnpjInformado);

		// valor de retorno
		byte[] retorno = null;

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		RelatorioManterImovelBean relatorioBean = null;

		// se a cole��o de par�metros da analise n�o for vazia
		if (imoveisRelatoriosHelper != null
				&& !imoveisRelatoriosHelper.isEmpty()) {
			// coloca a cole��o de par�metros da analise no iterator
			Iterator imovelRelatorioIterator = imoveisRelatoriosHelper
					.iterator();

			SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");

			// la�o para criar a cole��o de par�metros da analise
			while (imovelRelatorioIterator.hasNext()) {

				ImovelRelatorioHelper imovelRelatorioHelper = (ImovelRelatorioHelper) imovelRelatorioIterator
						.next();

				String imovelSubcategoriaImprimir = "";

				if (imovelRelatorioHelper.getSubcategoriaQtdEconomia() != null) {

					String[] subCategoriaQtdEconomia = imovelRelatorioHelper
							.getSubcategoriaQtdEconomia();
					int i = 0;
					while (i < imovelRelatorioHelper
							.getSubcategoriaQtdEconomia().length) {

						// Concatenar as categorias com suas
						// respectivas
						// quantidades de economias

						imovelSubcategoriaImprimir = imovelSubcategoriaImprimir
								+ subCategoriaQtdEconomia[i];

						i = i + 1;
					}
				}

				// In�cio da Constru��o do objeto
				// RelatorioManterImovelBean
				// para ser colocado no relat�rio
				relatorioBean = new RelatorioManterImovelBean(

						// C�digo da Ger�ncia Regional
						imovelRelatorioHelper.getIdGerenciaRegional() == null ? ""
								: ""
										+ imovelRelatorioHelper
												.getIdGerenciaRegional(),

						// Descri��o da Ger�ncia Regional
						imovelRelatorioHelper.getDescricaoGerenciaRegional() == null ? ""
								: imovelRelatorioHelper
										.getDescricaoGerenciaRegional(),

						// C�digo Unidade de Neg�cio
						imovelRelatorioHelper.getIdUnidadeNegocio() == null ? ""
								: ""
										+ imovelRelatorioHelper
												.getIdUnidadeNegocio(),

						// Nome Unidade de Neg�cio
						imovelRelatorioHelper.getNomeUnidadeNegocio() == null ? ""
								: imovelRelatorioHelper.getNomeUnidadeNegocio(),

						// C�digo da Localidade
						imovelRelatorioHelper.getIdLocalidade() == null ? ""
								: "" + imovelRelatorioHelper.getIdLocalidade(),

						// Descri��o da Localidade
						imovelRelatorioHelper.getDescricaoLocalidade() == null ? ""
								: imovelRelatorioHelper
										.getDescricaoLocalidade(),

						// C�digo do Setor Comercial
						imovelRelatorioHelper.getCodigoSetorComercial() == null ? ""
								: ""
										+ imovelRelatorioHelper
												.getCodigoSetorComercial(),

						// Descri��o do Setor Comercial
						imovelRelatorioHelper.getDescricaoSetorComercial() == null ? ""
								: imovelRelatorioHelper
										.getDescricaoSetorComercial(),

						// Inscri��o do Im�vel composto do c�digo da
						// localidade, c�digo do setor comercial, n�mero
						// da
						// quadra, lote e sublote
						imovelRelatorioHelper.getInscricaoImovel() == null ? ""
								: imovelRelatorioHelper.getInscricaoImovel(),

						// Matr�cula do Im�vel
						"" + imovelRelatorioHelper.getMatriculaImovel(),

						// C�digo do Cliente Usu�rio
						imovelRelatorioHelper.getClienteUsuarioId() == null ? ""
								: ""
										+ imovelRelatorioHelper
												.getClienteUsuarioId(),

						// Nome do Cliente Usu�rio
						imovelRelatorioHelper.getClienteUsuarioNome() == null ? ""
								: imovelRelatorioHelper.getClienteUsuarioNome(),

						// C�digo do Cliente Respons�vel
						imovelRelatorioHelper.getClienteResponsavelId() == null ? ""
								: ""
										+ imovelRelatorioHelper
												.getClienteResponsavelId(),

						// Nome do Cliente Respons�vel
						imovelRelatorioHelper.getClienteResponsavelNome() == null ? ""
								: imovelRelatorioHelper
										.getClienteResponsavelNome(),

						// Endere�o
						imovelRelatorioHelper.getEndereco(),

						// Indicador Im�vel Condom�nio
						imovelRelatorioHelper.getIndicadorImovelCondominio() == 1 ? "SIM"
								: "N�O",

						// Matr�cula Im�vel Condom�nio
						imovelRelatorioHelper.getMatriculaImovelCondominio() == null ? ""
								: ""
										+ imovelRelatorioHelper
												.getMatriculaImovelCondominio(),

						// Matr�cula Im�vel Principal
						imovelRelatorioHelper.getMatriculaImovelPrincipal() == null ? ""
								: ""
										+ imovelRelatorioHelper
												.getMatriculaImovelPrincipal(),

						// Perfil Im�vel
						imovelRelatorioHelper.getPerfilImovel() == null ? ""
								: imovelRelatorioHelper.getPerfilImovel(),

						// Subcategorias / Quantidade de Economias
						imovelSubcategoriaImprimir,

						// Situa��o da Liga��o de �gua
						imovelRelatorioHelper.getLigacaoAguaSituacao(),

						// Situa��o da Liga��o de Esgoto
						imovelRelatorioHelper.getLigacaoEsgotoSituacao(),

						// Tipo Pavimento Cal�ada
						imovelRelatorioHelper.getTipoPavimentoCalcada(),

						// Tipo Pavimento Rua
						imovelRelatorioHelper.getTipoPavimentoRua(),

						// Tipo de Despejo
						imovelRelatorioHelper.getTipoDespejo(),

						// Volume do Reservat�rio Superior
						imovelRelatorioHelper.getVolumeReservatorioSuperior() == null ? (imovelRelatorioHelper
								.getVolumeReservatorioSuperiorMenorFaixa() == null ? ""
								: Util
										.formatarMoedaReal(imovelRelatorioHelper
												.getVolumeReservatorioSuperiorMenorFaixa()))
								+ " a "
								+ (imovelRelatorioHelper
										.getVolumeReservatorioSuperiorMaiorFaixa() == null ? ""
										: Util
												.formatarMoedaReal(imovelRelatorioHelper
														.getVolumeReservatorioSuperiorMaiorFaixa()))
								+ " m�"
								: Util.formatarMoedaReal(imovelRelatorioHelper
										.getVolumeReservatorioInferior())
										+ " m�",

						// Volume do Reservat�rio Inferior
						imovelRelatorioHelper.getVolumeReservatorioInferior() == null ? (imovelRelatorioHelper
								.getVolumeReservatorioInferiorMenorFaixa() == null ? ""
								: Util
										.formatarMoedaReal(imovelRelatorioHelper
												.getVolumeReservatorioInferiorMenorFaixa()))
								+ " a "
								+ (imovelRelatorioHelper
										.getVolumeReservatorioInferiorMaiorFaixa() == null ? ""
										: Util
												.formatarMoedaReal(imovelRelatorioHelper
														.getVolumeReservatorioInferiorMaiorFaixa()))
								+ " m�"
								: Util.formatarMoedaReal(imovelRelatorioHelper
										.getVolumeReservatorioInferior())
										+ " m�",

						// Volume da Piscina
						imovelRelatorioHelper.getVolumePiscina() == null ? (imovelRelatorioHelper
								.getVolumePiscinaMenorFaixa() == null ? ""
								: Util.formatarMoedaReal(imovelRelatorioHelper
										.getVolumePiscinaMenorFaixa()))
								+ " a "
								+ (imovelRelatorioHelper
										.getVolumePiscinaMaiorFaixa() == null ? ""
										: Util
												.formatarMoedaReal(imovelRelatorioHelper
														.getVolumePiscinaMaiorFaixa()))
								+ " m�"
								: Util.formatarMoedaReal(imovelRelatorioHelper
										.getVolumePiscina())
										+ " m�",

						// M�dia de Consumo do Im�vel
						imovelRelatorioHelper.getConsumoMedioImovel() == null ? ""
								: ""
										+ imovelRelatorioHelper
												.getConsumoMedioImovel(),

						// �rea Constru�da
						imovelRelatorioHelper.getAreaConstruidaImovel() == null ? imovelRelatorioHelper
								.getAreaConstruidaMenorFaixa()
								+ " a "
								+ imovelRelatorioHelper
										.getAreaConstruidaMaiorFaixa() + " m�"
								: Util.formatarMoedaReal(imovelRelatorioHelper
										.getAreaConstruidaImovel())
										+ " m�",

						// N�mero de Moradores
						imovelRelatorioHelper.getNumeroMoradores() == 0 ? ""
								: ""
										+ imovelRelatorioHelper
												.getNumeroMoradores(),

						// Pontos de Utiliza��o
						imovelRelatorioHelper.getNumeroPontosUtilzacao() == 0 ? ""
								: ""
										+ imovelRelatorioHelper
												.getNumeroPontosUtilzacao(),

						// Tipo do Po�o
						imovelRelatorioHelper.getDescricaoTipoPoco() == null ? ""
								: imovelRelatorioHelper.getDescricaoTipoPoco(),

						// Jardim
						imovelRelatorioHelper.getJardim(),

						// Data da Liga��o de �gua
						imovelRelatorioHelper.getDataLigacaoAgua() == null ? ""
								: dataFormatada.format(imovelRelatorioHelper
										.getDataLigacaoAgua()),

						// Di�metro da Liga��o de �gua
						imovelRelatorioHelper.getDiametroLigacaoAgua() == null ? ""
								: imovelRelatorioHelper
										.getDiametroLigacaoAgua(),

						// Material da Liga��o de �gua
						imovelRelatorioHelper.getMaterialLigacaoAgua() == null ? ""
								: imovelRelatorioHelper
										.getMaterialLigacaoAgua(),

						// Consumo M�nimo Fixado de �gua
						imovelRelatorioHelper.getConsumoMinimoFixadoAgua() == null ? ""
								: ""
										+ imovelRelatorioHelper
												.getConsumoMinimoFixadoAgua(),

						// Data da Liga��o de Esgoto
						imovelRelatorioHelper.getDataLigacaoEsgoto() == null ? ""
								: dataFormatada.format(imovelRelatorioHelper
										.getDataLigacaoEsgoto()),

						// Di�metro da Liga��o de Esgoto
						imovelRelatorioHelper.getDiametroLigacaoEsgoto() == null ? ""
								: imovelRelatorioHelper
										.getDiametroLigacaoEsgoto(),

						// Material da Liga��o de Esgoto
						imovelRelatorioHelper.getMaterialLigacaoEsgoto() == null ? ""
								: imovelRelatorioHelper
										.getMaterialLigacaoEsgoto(),

						// Percentual da Coleta de �gua
						imovelRelatorioHelper
								.getPercentualAguaConsumidaColetada() == null ? ""
								: imovelRelatorioHelper
										.getPercentualAguaConsumidaColetada()
										.toString(),

						// Percentual de Esgoto
						imovelRelatorioHelper.getPercentual() == null ? ""
								: imovelRelatorioHelper.getPercentual()
										.toString(),

						// Consumo M�nimo Fixado de Esgoto
						imovelRelatorioHelper
								.getConsumoMinimoFixadoLigacaoEsgoto() == null ? ""
								: ""
										+ imovelRelatorioHelper
												.getConsumoMinimoFixadoLigacaoEsgoto(),

						// In�cio dos Dados do Hidr�metro Instalado na
						// Liga��o de �gua
						// N�mero
						imovelRelatorioHelper.getNumeroHidrometroAgua() == null ? ""
								: imovelRelatorioHelper
										.getNumeroHidrometroAgua(),

						// Ano de Fabrica��o
						imovelRelatorioHelper.getAnoFabricaocaHidrometroAgua() == 0 ? ""
								: ""
										+ imovelRelatorioHelper
												.getAnoFabricaocaHidrometroAgua(),

						// Capacidade
						imovelRelatorioHelper.getCapacidadeHidrometroAgua() == null ? ""
								: imovelRelatorioHelper
										.getCapacidadeHidrometroAgua(),

						// Marca
						imovelRelatorioHelper.getMarcaHidrometroAgua() == null ? ""
								: imovelRelatorioHelper
										.getMarcaHidrometroAgua(),

						// Di�metro
						imovelRelatorioHelper.getDiametroHidrometroAgua() == null ? ""
								: imovelRelatorioHelper
										.getDiametroHidrometroAgua(),

						// Tipo
						imovelRelatorioHelper.getTipoHidrometroAgua() == null ? ""
								: imovelRelatorioHelper.getTipoHidrometroAgua(),

						// Data de Instala��o
						imovelRelatorioHelper.getDataInstalacaoHidrometroAgua() == null ? ""
								: dataFormatada.format(imovelRelatorioHelper
										.getDataInstalacaoHidrometroAgua()),

						// Local de Instala��o
						imovelRelatorioHelper
								.getLocalInstalacaoHidrometroAgua() == null ? ""
								: imovelRelatorioHelper
										.getLocalInstalacaoHidrometroAgua(),

						// Tipo de Prote��o
						imovelRelatorioHelper.getTipoProtecaoHidrometroAgua() == null ? ""
								: imovelRelatorioHelper
										.getTipoProtecaoHidrometroAgua(),

						// Indicador da Exist�ncia de Cavalete
						imovelRelatorioHelper
								.getIndicadorExistenciaCavaleteAgua() == 0 ? ""
								: ""
										+ imovelRelatorioHelper
												.getIndicadorExistenciaCavaleteAgua(),

						// Fim dos Dados do Hidr�metro Instalado na
						// Liga��o
						// de �gua

						// In�cio dos Dados do Hidr�metro Instalado na
						// Sa�da
						// do Po�o
						// N�mero
						imovelRelatorioHelper.getNumeroHidrometroPoco() == null ? ""
								: imovelRelatorioHelper
										.getNumeroHidrometroPoco(),

						// Ano de Fabrica��o
						imovelRelatorioHelper.getAnoFabricacaoHidrometroPoco() == 0 ? ""
								: ""
										+ imovelRelatorioHelper
												.getAnoFabricacaoHidrometroPoco(),

						// Capacidade
						imovelRelatorioHelper.getCapacidadeHidrometroPoco() == null ? ""
								: imovelRelatorioHelper
										.getCapacidadeHidrometroPoco(),

						// Marca
						imovelRelatorioHelper.getMarcaHidrometroPoco() == null ? ""
								: imovelRelatorioHelper
										.getMarcaHidrometroPoco(),

						// Di�metro
						imovelRelatorioHelper.getDiametroHidrometroPoco() == null ? ""
								: imovelRelatorioHelper
										.getDiametroHidrometroPoco(),

						// Tipo
						imovelRelatorioHelper.getTipoHidrometroPoco() == null ? ""
								: imovelRelatorioHelper.getTipoHidrometroPoco(),

						// Data de Instala��o
						imovelRelatorioHelper.getDataInstalacaoHidrometroPoco() == null ? ""
								: dataFormatada.format(imovelRelatorioHelper
										.getDataInstalacaoHidrometroPoco()),

						// Local de Instala��o
						imovelRelatorioHelper
								.getLocalInstalacaoHidrometroPoco() == null ? ""
								: imovelRelatorioHelper
										.getLocalInstalacaoHidrometroPoco(),

						// Tipo de Prote��o
						imovelRelatorioHelper.getTipoProtecaoHidrometroPoco() == null ? ""
								: imovelRelatorioHelper
										.getTipoProtecaoHidrometroPoco(),

						// Indicador da Exist�ncia de Cavalete
						imovelRelatorioHelper
								.getIndicadorExistenciaCavaletePoco() == 0 ? ""
								: ""
										+ imovelRelatorioHelper
												.getIndicadorExistenciaCavaletePoco(),

						// Rota
						imovelRelatorioHelper.getCodigoRota().toString(),

						// N�mero Sequencial Rota
						imovelRelatorioHelper.getNumeroSequencialRota() == null ? ""
								: imovelRelatorioHelper
										.getNumeroSequencialRota().toString(),

						// id Logradouro
						imovelRelatorioHelper.getIdLogradouro() == null ? ""
								: imovelRelatorioHelper.getIdLogradouro()
										.toString(),
				
						//Indicador N�vel esgotamento
						imovelRelatorioHelper.getIndicadorNivelEsgotamento());

				// Fim dos Dados do Hidr�metro Instalado na Sa�da do
				// Po�o

				// Fim da Constru��o do objeto RelatorioManterImovelBean
				// para ser colocado no relat�rio

				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioBean);
			}
		}

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("gerenciaRegional", gerenciaRegional == null ? ""
				: gerenciaRegional.getNomeAbreviado());
		parametros.put("idLocalidadeOrigem", imovelParametrosInicial
				.getLocalidade().getId() == null ? "" : ""
				+ imovelParametrosInicial.getLocalidade().getId());
		parametros.put("idLocalidadeDestino", imovelParametrosFinal
				.getLocalidade().getId() == null ? "" : ""
				+ imovelParametrosFinal.getLocalidade().getId());
		parametros.put("nomeLocalidadeOrigem", imovelParametrosInicial
				.getLocalidade().getDescricao());
		parametros.put("nomeLocalidadeDestino", imovelParametrosFinal
				.getLocalidade().getDescricao());
		parametros.put("idSetorComercialOrigem", imovelParametrosInicial
				.getSetorComercial().getId() == null ? "" : ""
				+ imovelParametrosInicial.getSetorComercial().getCodigo());
		parametros.put("idSetorComercialDestino", imovelParametrosFinal
				.getSetorComercial().getId() == null ? "" : ""
				+ imovelParametrosFinal.getSetorComercial().getCodigo());
		parametros.put("nomeSetorComercialOrigem", imovelParametrosInicial
				.getSetorComercial().getDescricao());
		parametros.put("nomeSetorComercialDestino", imovelParametrosFinal
				.getSetorComercial().getDescricao());
		parametros.put("numeroQuadraOrigem", imovelParametrosInicial
				.getQuadra().getNumeroQuadra() == 0 ? "" : ""
				+ imovelParametrosInicial.getQuadra().getNumeroQuadra());
		parametros.put("numeroQuadraDestino", imovelParametrosFinal.getQuadra()
				.getNumeroQuadra() == 0 ? "" : ""
				+ imovelParametrosFinal.getQuadra().getNumeroQuadra());
		parametros.put("loteOrigem",
				imovelParametrosInicial.getLote() == 0 ? "" : ""
						+ imovelParametrosInicial.getLote());
		parametros.put("loteDestino", imovelParametrosFinal.getLote() == 0 ? ""
				: "" + imovelParametrosFinal.getLote());
		parametros.put("idMunicipio", municipio.getId() == null ? "" : ""
				+ municipio.getId());
		parametros.put("nomeMunicipio", municipio.getNome());
		parametros.put("idBairro", bairro.getCodigo() == 0 ? "" : ""
				+ bairro.getCodigo());
		parametros.put("nomeBairro", bairro.getNome());
		parametros.put("cep", imovelParametrosInicial.getLogradouroCep()
				.getCep() == null ? "" : imovelParametrosInicial
				.getLogradouroCep().getCep().getCodigo() == null ? "" : ""
				+ imovelParametrosInicial.getLogradouroCep().getCep()
						.getCodigo());

		parametros.put("idLogradouro", imovelParametrosInicial
				.getLogradouroCep().getLogradouro() == null ? ""
				: imovelParametrosInicial.getLogradouroCep().getLogradouro()
						.getId() == null ? "" : ""
						+ imovelParametrosInicial.getLogradouroCep()
								.getLogradouro().getId());

		parametros.put("nomeLogradouro", imovelParametrosInicial
				.getLogradouroCep().getLogradouro() == null ? ""
				: imovelParametrosInicial.getLogradouroCep().getLogradouro()
						.getNome());

		parametros
				.put(
						"idCliente",
						clienteImovelParametros.getCliente() == null ? ""
								: clienteImovelParametros.getCliente().getId() == null ? ""
										: ""
												+ clienteImovelParametros
														.getCliente().getId());

		parametros.put("nomeCliente",
				clienteImovelParametros.getCliente() == null ? ""
						: clienteImovelParametros.getCliente().getNome());
		parametros.put("tipoRelacao", clienteImovelParametros
				.getClienteRelacaoTipo().getDescricao());
		parametros.put("tipoCliente", clienteImovelParametros.getCliente()
				.getClienteTipo().getDescricao());
		parametros.put("imovelCondominio", imovelParametrosInicial
				.getImovelCondominio().getId() == null ? "" : ""
				+ imovelParametrosInicial.getImovelCondominio().getId());
		parametros.put("imovelPrincipal", imovelParametrosInicial
				.getImovelPrincipal().getId() == null ? "" : ""
				+ imovelParametrosInicial.getImovelPrincipal().getId());
		// parametros.put("nomeConta", imovelParametrosInicial.getNomeConta()
		// .getNomeConta());
		parametros.put("situacaoLigacaoAgua", imovelParametrosInicial
				.getLigacaoAguaSituacao().getDescricao());
		parametros.put("situacaoLigacaoEsgoto", imovelParametrosInicial
				.getLigacaoEsgotoSituacao().getDescricao());
		parametros.put("consumoMinimoFixadoAguaInicial",
				imovelParametrosInicial.getLigacaoAgua()
						.getNumeroConsumoMinimoAgua() == null ? null : ""
						+ imovelParametrosInicial.getLigacaoAgua()
								.getNumeroConsumoMinimoAgua());
		parametros.put("consumoMinimoFixadoAguaFinal", imovelParametrosFinal
				.getLigacaoAgua().getNumeroConsumoMinimoAgua() == null ? null
				: ""
						+ imovelParametrosFinal.getLigacaoAgua()
								.getNumeroConsumoMinimoAgua());
		parametros.put("percentualEsgotoInicial", imovelParametrosInicial
				.getLigacaoEsgoto().getPercentual() == null ? null
				: imovelParametrosInicial.getLigacaoEsgoto().getPercentual()
						.toString());
		parametros.put("percentualEsgotoFinal", imovelParametrosFinal
				.getLigacaoEsgoto().getPercentual() == null ? null
				: imovelParametrosFinal.getLigacaoEsgoto().getPercentual()
						.toString());
		parametros
				.put("consumoMinimoFixadoEsgotoInicial",
						imovelParametrosInicial.getLigacaoEsgoto()
								.getConsumoMinimo() == null ? null : ""
								+ imovelParametrosInicial.getLigacaoEsgoto()
										.getConsumoMinimo());
		parametros.put("consumoMinimoFixadoEsgotoFinal", imovelParametrosFinal
				.getLigacaoEsgoto().getConsumoMinimo() == null ? null : ""
				+ imovelParametrosFinal.getLigacaoEsgoto().getConsumoMinimo());
		parametros.put("indicadorMedicao", indicadorMedicao == null ? ""
				: indicadorMedicao.equals("comMedicao") ? "COM MEDI��O"
						: "SEM MEDI��O");
		parametros.put("tipoMedicao", medicaoHistoricoParametrosInicial
				.getMedicaoTipo().getDescricao());
		parametros
				.put(
						"mediaMinimaImovelInicial",
						consumoHistoricoParametrosInicial.getConsumoMedio() == null ? null
								: ""
										+ consumoHistoricoParametrosInicial
												.getConsumoMedio());
		parametros
				.put("mediaMinimaImovelFinal", consumoHistoricoParametrosFinal
						.getConsumoMedio() == null ? null : ""
						+ consumoHistoricoParametrosFinal.getConsumoMedio());
		parametros
				.put("mediaMinimaHidrometroInicial",
						medicaoHistoricoParametrosInicial
								.getConsumoMedioHidrometro() == null ? null
								: ""
										+ medicaoHistoricoParametrosInicial
												.getConsumoMedioHidrometro());
		parametros
				.put("mediaMinimaHidrometroFinal",
						medicaoHistoricoParametrosFinal
								.getConsumoMedioHidrometro() == null ? null
								: ""
										+ medicaoHistoricoParametrosFinal
												.getConsumoMedioHidrometro());
		parametros.put("perfilImovel", imovelParametrosInicial
				.getImovelPerfil().getDescricao());
		parametros.put("categoria", categoria.getDescricao());
		parametros.put("subCategoria", subcategoria.getDescricao());
		parametros.put("qtdeEconomiaInicial", imovelParametrosInicial
				.getQuantidadeEconomias().equals(new Short("0")) ? null : ""
				+ imovelParametrosInicial.getQuantidadeEconomias());
		parametros.put("qtdeEconomiaFinal", imovelParametrosFinal
				.getQuantidadeEconomias().equals(new Short("0")) ? null : ""
				+ imovelParametrosFinal.getQuantidadeEconomias());
		parametros.put("numeroPontosInicial", imovelParametrosInicial
				.getNumeroPontosUtilizacao() == 0 ? null : ""
				+ imovelParametrosInicial.getNumeroPontosUtilizacao());
		parametros.put("numeroPontosFinal", imovelParametrosFinal
				.getNumeroPontosUtilizacao() == 0 ? null : ""
				+ imovelParametrosFinal.getNumeroPontosUtilizacao());
		parametros.put("numeroMoradoresInicial", imovelParametrosInicial
				.getNumeroMorador() == 0 ? null : ""
				+ imovelParametrosInicial.getNumeroMorador());
		parametros.put("numeroMoradoresFinal", imovelParametrosFinal
				.getNumeroMorador() == 0 ? null : ""
				+ imovelParametrosFinal.getNumeroMorador());
		parametros.put("areaConstruidaInicial", imovelParametrosInicial
				.getAreaConstruida().equals(new BigDecimal("0")) ? null : ""
				+ imovelParametrosInicial.getAreaConstruida());
		parametros.put("areaConstruidaFinal", imovelParametrosFinal
				.getAreaConstruida().equals(new BigDecimal("0")) ? null : ""
				+ imovelParametrosFinal.getAreaConstruida());
		parametros.put("tipoPoco", imovelParametrosInicial.getPocoTipo()
				.getDescricao());
		parametros.put("tipoSituacaoEspecialFaturamento",
				imovelParametrosInicial.getFaturamentoSituacaoTipo()
						.getDescricao());
		parametros.put("tipoSituacaoEspecialCobranca", imovelParametrosInicial
				.getCobrancaSituacaoTipo().getDescricao());
		parametros.put("situacaoCobranca", cobrancaSituacao == null ? ""
				: cobrancaSituacao.getDescricao());
		parametros.put("diaVencimentoAlternativo", imovelParametrosInicial
				.getDiaVencimento() == null ? "" : ""
				+ imovelParametrosInicial.getDiaVencimento());
		parametros.put("anormalidadeElo", imovelParametrosInicial
				.getEloAnormalidade() == null ? "" : imovelParametrosInicial
				.getEloAnormalidade().getDescricao());
		parametros.put("ocorrenciaCadastro", imovelParametrosInicial
				.getCadastroOcorrencia() == null ? "" : imovelParametrosInicial
				.getCadastroOcorrencia().getDescricao());
		parametros.put("tarifaConsumo", imovelParametrosInicial
				.getConsumoTarifa() == null ? "" : imovelParametrosInicial
				.getConsumoTarifa().getDescricao());
		parametros.put("idUnidadeNegocio", idUnidadeNegoio);
		
		if (idUnidadeNegoio != null && !idUnidadeNegoio.equals("")){
			
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID, new Integer(idUnidadeNegoio)));
			Collection colecaoUnidade = fachada.pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
			
			UnidadeNegocio unidadeNegocio = (UnidadeNegocio) Util.retonarObjetoDeColecao(colecaoUnidade);
			
			parametros.put("nomeUnidadeNegocio", unidadeNegocio == null ? "" :
				unidadeNegocio.getNome());
		}
		
		
		
		
		if (indicadorNivelEsgotamento == null ){
			
			indicadorNivelEsgotamento = "3";
		}
			
		switch(Integer.parseInt(indicadorNivelEsgotamento)){
			case 1:
				parametros.put("indicadorNivelEsgotamento", "Sim");
				break;
			case 2:
				parametros.put("indicadorNivelEsgotamento", "N�o");
				break;
			case 3:
				parametros.put("indicadorNivelEsgotamento", "Todos");
				break;
		}
		
		

		RelatorioDataSource ds = new RelatorioDataSource((List) relatorioBeans);

		// exporta o relat�rio em pdf e retorna o array de bytes
		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_IMOVEL_OUTROS_CRITERIOS_MANTER,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.MANTER_IMOVEL_OUTROS_CRITERIOS,
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
		// id da genrencia regional
		String gerenciaRegional = (String) getParametro("gerenciaRegional");
		// id da genrencia regional
		String idUnidadeNegocio = (String) getParametro("idUnidadeNegocio");
		// numero da quadra origem
		String qudraOrigem = (String) getParametro("quadraOrigem");
		// numero quadra destino
		String quadraDestino = (String) getParametro("quadraDestino");
		// lote origem
		String loteOrigem = (String) getParametro("loteOrigem");
		// lote destino
		String loteDestino = (String) getParametro("loteDestino");
		// cep
		String cep = (String) getParametro("cep");
		// id localidade origem
		String localidadeOrigem = (String) getParametro("localidadeOrigem");
		// id localidade destino
		String localidadeDestino = (String) getParametro("localidadeDestino");
		// setor comercial origem CD
		String setorComercialOrigemCD = (String) getParametro("setorComercialOrigemCD");
		// setor comercial destino CD
		String setorComercialDestinoCD = (String) getParametro("setorComercialDestinoCD");
		// cliente ID
		String clienteID = (String) getParametro("clienteID");
		// municipio ID
		String municipioID = (String) getParametro("municipioID");
		// bairro ID
		String bairroID = (String) getParametro("bairroID");
		// logradouro ID
		String logradouroID = (String) getParametro("logradouroID");
		// cliente relacao tipo ID
		String clienteRelacaoTipoID = (String) getParametro("clienteRelacaoTipoID");
		// cliente tipo ID
		String clienteTipoID = (String) getParametro("clienteTipoID");
		// imovel condominio ID
		String imovelCondominioID = (String) getParametro("imovelCondominioID");
		// imovel Principal ID
		String imovelPrincipalID = (String) getParametro("imovelPrincipalID");
		// nome Conta ID
		// String nomeContaID = (String) getParametro("nomeContaID");
		// situacao Agua
		String situacaoAgua = (String) getParametro("situacaoAgua");
		// situacao Ligacao Esgoto
		String situacaoLigacaoEsgoto = (String) getParametro("situacaoLigacaoEsgoto");
		// consumo Minimo Inicial
		String consumoMinimoInicial = (String) getParametro("consumoMinimoInicial");
		// consumo Minimo Final
		String consumoMinimoFinal = (String) getParametro("consumoMinimoFinal");
		// consumo Minimo Fixado Esgoto Inicial
		String consumoMinimoFixadoEsgotoInicial = (String) getParametro("consumoMinimoFixadoEsgotoInicial");
		// consumo Minimo Fixado Esgoto Final
		String consumoMinimoFixadoEsgotoFinal = (String) getParametro("consumoMinimoFixadoEsgotoFinal");
		// intervalo Percentual Esgoto Inicial
		String intervaloPercentualEsgotoInicial = (String) getParametro("intervaloPercentualEsgotoInicial");
		// intervalor Percentual Esgoto Final
		String intervaloPercentualEsgotoFinal = (String) getParametro("intervaloPercentualEsgotoFinal");
		// indicador Medicao
		String indicadorMedicao = (String) getParametro("indicadorMedicao");
		// tipo Medicao ID
		String tipoMedicaoID = (String) getParametro("tipoMedicaoID");
		// intervalo Media Minima Imovel Inicial
		String intervaloMediaMinimaImovelInicial = (String) getParametro("intervaloMediaMinimaImovelInicial");
		// intervalo Media Minima Imovel Final
		String intervaloMediaMinimaImoveFinal = (String) getParametro("intervaloMediaMinimaImoveFinal");
		// intervalo Media Minima Hidrometro Inicial
		String intervaloMediaMinimaHidrometroInicial = (String) getParametro("intervaloMediaMinimaHidrometroInicial");
		// intervalo Media Minima Hidrometro Final
		String intervaloMediaMinimaHidrometroFinal = (String) getParametro("intervaloMediaMinimaHidrometroFinal");
		// perfil Imovel ID
		String perfilImovelID = (String) getParametro("perfilImovelID");
		// categoria Imovel ID
		String categoriaImovelID = (String) getParametro("categoriaImovelID");
		// sub categoria ID
		String subCategoriaID = (String) getParametro("subCategoriaID");
		// quantidade Economias Inicial
		String quantidadeEconomiasInicial = (String) getParametro("quantidadeEconomiasInicial");
		// quantidade Economias Final
		String quantidadeEconomiasFinal = (String) getParametro("quantidadeEconomiasFinal");
		// numero Pontos Inicial
		String numeroPontosInicial = (String) getParametro("numeroPontosInicial");
		// numero Pontos Final
		String numeroPontosFinal = (String) getParametro("numeroPontosFinal");
		// numero Moradores Inicial
		String numeroMoradoresInicial = (String) getParametro("numeroMoradoresInicial");
		// numero Moradoras Final
		String numeroMoradoresFinal = (String) getParametro("numeroMoradoresFinal");
		// area Construida Inicial
		String areaConstruidaInicial = (String) getParametro("areaConstruidaInicial");
		// area Construida Final
		String areaConstruidaFinal = (String) getParametro("areaConstruidaFinal");
		// area Construida Faixa
		String areaConstruidaFaixa = (String) getParametro("areaConstruidaFaixa");
		// poco Tipo ID
		String pocoTipoID = (String) getParametro("pocoTipoID");
		// tipo Situacao Faturamento ID
		String tipoSituacaoFaturamentoID = (String) getParametro("tipoSituacaoFaturamentoID");
		// tipo Situacao Especial Cobranca ID
		String tipoSituacaoEspecialCobrancaID = (String) getParametro("tipoSituacaoEspecialCobrancaID");
		// situacao Cobranca ID
		String situacaoCobrancaID = (String) getParametro("situacaoCobrancaID");
		// dia Vencimento Alternativo
		String diaVencimentoAlternativo = (String) getParametro("diaVencimentoAlternativo");
		// ocorrencia Cadastro
		String ocorrenciaCadastro = (String) getParametro("ocorrenciaCadastro");
		// tarifa Consumo
		String tarifaConsumo = (String) getParametro("tarifaConsumo");
		// anormalidade Elo
		String anormalidadeElo = (String) getParametro("anormalidadeElo");
		
		// codigo da rota inicial
		String cdRotaInicial = (String) getParametro("cdRotaInicial");
		// codigo da rota final
		String cdRotaFinal = (String) getParametro("cdRotaFinal");

		// sequencial rota inicial
		String sequencialRotaInicial = (String) getParametro("sequencialRotaInicial");
		// sequencial rota final
		String sequencialRotaFinal = (String) getParametro("sequencialRotaFinal");
		
		String indicadorNivelEsgotamento = (String) getParametro("indicadorNivelEsgotamento");

		Fachada fachada = Fachada.getInstancia();

		Integer quantidade = fachada.obterQuantidadaeRelacaoImoveisDebitos(
				imovelCondominioID, imovelPrincipalID, situacaoAgua,
				consumoMinimoInicial, consumoMinimoFinal,
				situacaoLigacaoEsgoto, consumoMinimoFixadoEsgotoInicial,
				consumoMinimoFixadoEsgotoFinal,
				intervaloPercentualEsgotoInicial,
				intervaloPercentualEsgotoFinal,
				intervaloMediaMinimaImovelInicial,
				intervaloMediaMinimaImoveFinal,
				intervaloMediaMinimaHidrometroInicial,
				intervaloMediaMinimaHidrometroFinal, perfilImovelID,
				pocoTipoID, tipoSituacaoFaturamentoID, situacaoCobrancaID,
				tipoSituacaoEspecialCobrancaID, anormalidadeElo,
				areaConstruidaInicial, areaConstruidaFinal, ocorrenciaCadastro,
				tarifaConsumo, gerenciaRegional, localidadeOrigem,
				localidadeDestino, setorComercialOrigemCD,
				setorComercialDestinoCD, qudraOrigem, quadraDestino,
				loteOrigem, loteDestino, cep, logradouroID, bairroID,
				municipioID, tipoMedicaoID, indicadorMedicao, subCategoriaID,
				categoriaImovelID, quantidadeEconomiasInicial,
				quantidadeEconomiasFinal, diaVencimentoAlternativo, clienteID,
				clienteTipoID, clienteRelacaoTipoID, numeroPontosInicial,
				numeroPontosFinal, numeroMoradoresInicial,
				numeroMoradoresFinal, areaConstruidaFaixa, idUnidadeNegocio,
				ConstantesSistema.GERAR_RELATORIO_IMOVEL,cdRotaInicial,
				cdRotaFinal, sequencialRotaInicial,
				sequencialRotaFinal, indicadorNivelEsgotamento);

		return quantidade.intValue();
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterImovelOutrosCriterios",
				this);
	}

}
