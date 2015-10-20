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
package gcom.micromedicao;

import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.batch.UnidadeProcessamento;
import gcom.cadastro.EnvioEmail;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.leitura.LeituraTipo;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;

/**
 * Controlador Faturamento COMPESA
 *
 * @author Raphael Rossiter
 * @date 25/07/2006
 */
public class ControladorMicromedicaoCOMPESASEJB extends ControladorMicromedicao implements SessionBean {
	private static final long serialVersionUID = 1L;
	//==============================================================================================================
	// M�TODOS EXCLUSIVOS DA COMPESA
	//==============================================================================================================

	
	
	/**
	 * [UC0083] - Gerar Dados para Leitura [SF0001] - Gerar Arquivo Convencional
	 * ou Rela��o Autor: S�vio Luiz, Pedro Alexandre Data: 21/12/2005,
	 * 15/10/2007
	 */
	/**
	 * @param colecaoRota
	 * @param anoMesCorrente
	 * @param idGrupoFaturamentoRota
	 * @param idFuncionalidadeIniciada
	 * @return
	 * @throws ControladorException
	 */
	public Collection gerarDadosPorLeituraMicroColetor(Collection colecaoRota,
			Integer anoMesCorrente, Integer idGrupoFaturamentoRota,SistemaParametro sistemaParametro,
			int idFuncionalidadeIniciada) throws ControladorException {

		int idUnidadeIniciada = 0;

		try {
			Integer idLeituraTipo = LeituraTipo.MICROCOLETOR;

			// -------------------------
			//
			// Registrar o in�cio do processamento da Unidade de
			// Processamento
			// do Batch
			//
			// -------------------------
//			idUnidadeIniciada = getControladorBatch()
//					.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
//							UnidadeProcessamento.FUNCIONALIDADE, 0);

			

			int numeroIndice = 0;
			int quantidadeRegistrosPesquisa = 1000;
			boolean flagTerminou = false;
			
			/*SistemaParametro sistemaParametro = null;

			try {
				sistemaParametro = repositorioUtil
						.pesquisarParametrosDoSistema();
			} catch (ErroRepositorioException e) {
				throw new ControladorException("erro.sistema", e);
			}	*/		

			// inicializa uma cole��o de imoveis
			Collection objetosImoveis = new ArrayList();

			EnvioEmail envioEmail = getControladorCadastro()
					.pesquisarEnvioEmail(
							EnvioEmail.GERAR_DADOS_PARA_LEITURA_MICRO_COLETOR);

			while (!flagTerminou) {

				// cria uma cole��o de im�vel por rota
				Collection imoveisPorRota = null;
				try {
					// recupera todos os im�veis da cole��o de rotas do tipo
					// convencional

					imoveisPorRota = repositorioMicromedicao
							.pesquisarImoveisPorRotaOrdenadoPorInscricao(
									colecaoRota, idLeituraTipo, numeroIndice, sistemaParametro.getNomeAbreviadoEmpresa());

				} catch (ErroRepositorioException e) {
					throw new ControladorException("erro.sistema", e);
				}

				if (imoveisPorRota != null && !imoveisPorRota.isEmpty()) {

					if (imoveisPorRota.size() < quantidadeRegistrosPesquisa) {
						flagTerminou = true;
					} else {
						numeroIndice = numeroIndice
								+ quantidadeRegistrosPesquisa;
					}

					Iterator imovelporRotaIterator = imoveisPorRota.iterator();
					while (imovelporRotaIterator.hasNext()) {
						// cria um array de objetos para pegar os parametros
						// de
						// retorno da pesquisa

						Object[] arrayImoveisPorRota = (Object[]) imovelporRotaIterator
								.next();

						// instancia um im�vel
						Imovel imovel = new Imovel();
						if (arrayImoveisPorRota[0] != null) {
							// seta o id no imovel
							imovel.setId((Integer) arrayImoveisPorRota[0]);
						}

						if (arrayImoveisPorRota[1] != null) {
							// instancia uma localidade para ser setado no
							// im�vel
							Localidade localidade = new Localidade();
							localidade.setId((Integer) arrayImoveisPorRota[1]);
							imovel.setLocalidade(localidade);
						}

						if (arrayImoveisPorRota[2] != null) {
							// instancia um setor comercial para ser setado
							// no
							// im�vel
							SetorComercial setorComercial = new SetorComercial();
							setorComercial
									.setCodigo(Integer
											.parseInt(arrayImoveisPorRota[2]
													.toString()));
							imovel.setSetorComercial(setorComercial);
						}
						Quadra quadra = new Quadra();
						if (arrayImoveisPorRota[3] != null) {
							// instancia uma quadra para ser setado no
							// im�vel

							Integer numeroQuadra = (Integer) arrayImoveisPorRota[3];
							quadra.setNumeroQuadra(numeroQuadra);
							imovel.setQuadra(quadra);
						}

						if (arrayImoveisPorRota[4] != null) {
							// seta o lote no im�vel
							imovel.setLote(Short
									.parseShort(arrayImoveisPorRota[4]
											.toString()));
						}

						if (arrayImoveisPorRota[5] != null) {
							// seta o lote no im�vel
							imovel.setSubLote(Short
									.parseShort(arrayImoveisPorRota[5]
											.toString()));
						}
						if (arrayImoveisPorRota[6] != null) {
							// instancia uma imovel perfil para ser setado
							// no
							// im�vel
							ImovelPerfil imovelPerfil = new ImovelPerfil();
							imovelPerfil
									.setId((Integer) arrayImoveisPorRota[6]);
							imovel.setImovelPerfil(imovelPerfil);
						}

						LigacaoAgua ligacaoAgua = new LigacaoAgua();
						if (arrayImoveisPorRota[7] != null) {
							// instancia uma liga��o agua para ser setado no
							// im�vel

							ligacaoAgua.setId((Integer) arrayImoveisPorRota[7]);
						}
						// instancia um hidrometro instala��o historico para
						// ser
						// colocado na ligacao agua
						HidrometroInstalacaoHistorico hidrometroInstalacaoHistoricoLigacaoAgua = new HidrometroInstalacaoHistorico();
						if (arrayImoveisPorRota[8] != null) {

							hidrometroInstalacaoHistoricoLigacaoAgua
									.setId((Integer) arrayImoveisPorRota[8]);
							MedicaoTipo medicaoTipo = new MedicaoTipo();
							medicaoTipo
									.setId((Integer) arrayImoveisPorRota[26]);
							hidrometroInstalacaoHistoricoLigacaoAgua
									.setMedicaoTipo(medicaoTipo);
							ligacaoAgua
									.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistoricoLigacaoAgua);

						}
						imovel.setLigacaoAgua(ligacaoAgua);

						// //instancia um hidrometro instala��o historico
						// para
						// ser colocado no imovel
						HidrometroInstalacaoHistorico hidrometroInstalacaoHistoricoImovel = new HidrometroInstalacaoHistorico();
						if (arrayImoveisPorRota[9] != null) {

							hidrometroInstalacaoHistoricoImovel
									.setId((Integer) arrayImoveisPorRota[9]);
							MedicaoTipo medicaoTipo = new MedicaoTipo();
							medicaoTipo
									.setId((Integer) arrayImoveisPorRota[27]);
							hidrometroInstalacaoHistoricoImovel
									.setMedicaoTipo(medicaoTipo);
							imovel
									.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistoricoImovel);

						}
						// instancia a rota
						Rota rotaImovel = new Rota();

						if (arrayImoveisPorRota[10] != null) {
							// seta o id da rota
							rotaImovel.setId((Integer) arrayImoveisPorRota[10]);
						}
						if (arrayImoveisPorRota[11] != null) {
							// seta o indicador fiscalizador suprimido na
							// rota
							rotaImovel.setIndicadorFiscalizarSuprimido(Short
									.parseShort(arrayImoveisPorRota[11]
											.toString()));
						}
						if (arrayImoveisPorRota[12] != null) {
							// seta o indicador fiscalizador cortado na rota
							rotaImovel.setIndicadorFiscalizarCortado(Short
									.parseShort(arrayImoveisPorRota[12]
											.toString()));
						}
						if (arrayImoveisPorRota[13] != null) {
							// seta o indicador gerar fiscalizacao na rota
							rotaImovel.setIndicadorGerarFiscalizacao(Short
									.parseShort(arrayImoveisPorRota[13]
											.toString()));
						}
						if (arrayImoveisPorRota[14] != null) {
							// seta o indicador fgerar falsa faixa na rota
							rotaImovel.setIndicadorGerarFalsaFaixa(Short
									.parseShort(arrayImoveisPorRota[14]
											.toString()));
						}
						if (arrayImoveisPorRota[15] != null) {
							// seta o percentual geracao fiscalizacao na
							// rota
							rotaImovel
									.setPercentualGeracaoFiscalizacao((BigDecimal) (arrayImoveisPorRota[15]));
						}
						if (arrayImoveisPorRota[16] != null) {
							// seta o percentual geracao faixa falsa na rota
							rotaImovel
									.setPercentualGeracaoFaixaFalsa((BigDecimal) (arrayImoveisPorRota[16]));
						}
						// instancia a empresa
						Empresa empresa = new Empresa();
						if (arrayImoveisPorRota[17] != null) {

							// seta o id na empresa
							empresa.setId((Integer) arrayImoveisPorRota[17]);

						}
						if (arrayImoveisPorRota[18] != null) {

							// seta a descri��o abreviada na empresa
							empresa
									.setDescricaoAbreviada(arrayImoveisPorRota[18]
											.toString());

						}
						if (arrayImoveisPorRota[19] != null) {

							// seta email da empresa
							empresa
									.setEmail(arrayImoveisPorRota[19]
											.toString());

						}
						if (arrayImoveisPorRota[28] != null) {

							// seta email da empresa
							empresa.setDescricao(arrayImoveisPorRota[28]
									.toString());

						}
						// seta a empresa na rota
						rotaImovel.setEmpresa(empresa);
						// instancia o faturamento
						FaturamentoGrupo faturamentoGrupo = new FaturamentoGrupo();
						if (arrayImoveisPorRota[20] != null) {
							// seta o id no faturamentGrupo
							faturamentoGrupo
									.setId((Integer) arrayImoveisPorRota[20]);

						}
						if (arrayImoveisPorRota[21] != null) {
							// seta o descri��o no faturamentGrupo
							faturamentoGrupo
									.setDescricao((String) arrayImoveisPorRota[21]);
						}
						// seta o faturamento na rota
						rotaImovel.setFaturamentoGrupo(faturamentoGrupo);
						if (arrayImoveisPorRota[22] != null) {
							// instancia a liga��o esgoto situa��o
							LeituraTipo leituraTipo = new LeituraTipo();
							// seta o id na liga��o esgoto situa��o
							leituraTipo
									.setId((Integer) arrayImoveisPorRota[22]);
							// seta a liga��o esgoto situa��o no imovel
							rotaImovel.setLeituraTipo(leituraTipo);
						}

						// seta a rota na quadra
						quadra.setRota(rotaImovel);
						// seta a quadra no imovel
						imovel.setQuadra(quadra);
						if (arrayImoveisPorRota[23] != null) {
							// instancia a liga��o agua situa��o
							LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
							// seta o id na liga��o agua situa��o
							ligacaoAguaSituacao
									.setId((Integer) arrayImoveisPorRota[23]);
							ligacaoAguaSituacao
							.setIndicadorFaturamentoSituacao((Short) arrayImoveisPorRota[33]);
							// seta a liga��o agua situa��o no imovel
							imovel.setLigacaoAguaSituacao(ligacaoAguaSituacao);
						}
						if (arrayImoveisPorRota[24] != null) {
							// instancia a liga��o esgoto situa��o
							LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
							// seta o id na liga��o esgoto situa��o
							ligacaoEsgotoSituacao
									.setId((Integer) arrayImoveisPorRota[24]);
							ligacaoEsgotoSituacao
							.setIndicadorFaturamentoSituacao((Short) arrayImoveisPorRota[34]);
							// seta a liga��o esgoto situa��o no imovel
							imovel
									.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);
						}

						if (arrayImoveisPorRota[25] != null) {
							// instancia o faturamento situacao tipo
							FaturamentoSituacaoTipo faturamentoSituacaoTipo = new FaturamentoSituacaoTipo();
							// seta o id no faturamento situacao tipo
							faturamentoSituacaoTipo
									.setIndicadorParalisacaoLeitura((Short) arrayImoveisPorRota[25]);
							// seta a liga��o esgoto situa��o no imovel
							imovel
									.setFaturamentoSituacaoTipo(faturamentoSituacaoTipo);
						}

						// adiciona na cole��o de imoveis
						objetosImoveis.add(imovel);

					}

				} else {
					flagTerminou = true;
				}
			}
			

			// Instancia uma cole��o que ser� usada para gerar o arquivo txt.
			Collection<Imovel> imoveisParaSerGerados = new ArrayList();

			Iterator imovelIterator = objetosImoveis.iterator();
			while (imovelIterator.hasNext()) {
				// Recupera o imovel da cole��o
				Imovel imovel = (Imovel) imovelIterator.next();


				// variavel respons�vel para entrar em uma das 4 condic�es
				// abaixo
				boolean achouImovel = false;

				/**
				 * [SF0002] - Verificar situa��o especial de faturamento Autor:
				 * S�vio Luiz Data: 21/12/2005
				 */
				// caso no imovel o faturamento situa��o grupo seja diferente de
				// nulo e igual a leitura
				// n�o realizada ent�o n�o seleciona o imovel caso contrario
				// seleciona.
				if (imovel.getFaturamentoSituacaoTipo() == null
						|| !imovel
								.getFaturamentoSituacaoTipo()
								.equals(
										FaturamentoSituacaoTipo.INDICADOR_PARALIZACAO_LEITURA_NAO_REALIZADA)) {

					if (!achouImovel) {
						// Verifica se a situa��o da liga��o agua � diferente de
						// nulo
						// Se for verifica se est� ligado ou cortado
						if (imovel.getLigacaoAguaSituacao() != null
								&& imovel.getLigacaoAguaSituacao().getIndicadorFaturamentoSituacao() != null
								&& (imovel.getLigacaoAguaSituacao().getIndicadorFaturamentoSituacao()
										.equals(LigacaoAguaSituacao.FATURAMENTO_ATIVO))) {
							// Se for ligado ou cortado ent�o
							// Verifica se a liga��o agua � diferente de nulo
							// se for verifica se o id da liga��o agua � igual
							// ao id
							// do
							// imovel e
							// se o id do hist�rico da instala��o do hidrometro
							// �
							// diferente de
							// null

							if (imovel.getLigacaoAgua() != null
									&& imovel.getLigacaoAgua().getId() != null
									&& (imovel.getLigacaoAgua().getId().equals(
											imovel.getId())
											&& imovel
													.getLigacaoAgua()
													.getHidrometroInstalacaoHistorico() != null && imovel
											.getLigacaoAgua()
											.getHidrometroInstalacaoHistorico()
											.getId() != null)) {

								imoveisParaSerGerados.add(imovel);
								achouImovel = true;

							}
						}
					}
					if (!achouImovel) {
						// Verifica se a situa��o da liga��o esgoto � diferente
						// de nulo
						// Se for verifica se est� ligado
						if (imovel.getLigacaoEsgotoSituacao() != null
								&& imovel.getLigacaoEsgotoSituacao().getIndicadorFaturamentoSituacao() != null
								&& (imovel.getLigacaoEsgotoSituacao().getIndicadorFaturamentoSituacao()
										.equals(LigacaoEsgotoSituacao.FATURAMENTO_ATIVO))) {
							// Verifica se o id do hidrometro historico �
							// diferente de
							// nulo na tabela imovel
							if (imovel.getHidrometroInstalacaoHistorico() != null
									&& imovel
											.getHidrometroInstalacaoHistorico()
											.getId() != null) {
								imoveisParaSerGerados.add(imovel);
								achouImovel = true;
							}
						}
					}
					if (!achouImovel) {
						// Verifica se a situa��o da liga��o agua � diferente de
						// nulo
						// Se for verifica se est� suprimido
						if (imovel.getLigacaoAguaSituacao() != null
								&& imovel.getLigacaoAguaSituacao().getId() != null
								&& imovel.getLigacaoAguaSituacao().getId()
										.equals(LigacaoAguaSituacao.SUPRIMIDO)) {
							// verifica se o indicador de fiscaliza��o suprimido
							// �
							// diferente de nulo
							// se for verifica se est� ativo
							if (imovel.getQuadra().getRota()
									.getIndicadorFiscalizarSuprimido() != null
									&& imovel
											.getQuadra()
											.getRota()
											.getIndicadorFiscalizarSuprimido()
											.equals(
													Rota.INDICADOR_SUPRIMIDO_ATIVO)) {
								imoveisParaSerGerados.add(imovel);
								achouImovel = true;
							}

						}
					}
					if (!achouImovel) {
						// Verifica se a situa��o da liga��o agua � diferente de
						// nulo
						// Se for verifica se est� cortado
						if ((imovel.getLigacaoAguaSituacao() != null && imovel
								.getLigacaoAguaSituacao().getId() != null)
								&& (imovel.getLigacaoAguaSituacao().getId()
										.equals(LigacaoAguaSituacao.CORTADO))) {

							// Se for cortado ent�o
							// Verifica se a liga��o agua � diferente de nulo
							// se for verifica se o id da liga��o agua � igual
							// ao id do
							// imovel e
							// se o id do hist�rico da instala��o do hidrometro
							// � null
							if (imovel.getLigacaoAgua() != null
									&& imovel.getLigacaoAgua().getId() != null
									&& (imovel.getLigacaoAgua().getId().equals(
											imovel.getId()) && (imovel
											.getLigacaoAgua()
											.getHidrometroInstalacaoHistorico() == null || imovel
											.getLigacaoAgua()
											.getHidrometroInstalacaoHistorico()
											.getId() == null))) {
								
								// verifica se o indicador de fiscaliza��o
								// cortado �
								// diferente de nulo
								// se for verifica se est� ativo
								if (imovel.getQuadra().getRota()
										.getIndicadorFiscalizarCortado() != null
										&& imovel
												.getQuadra()
												.getRota()
												.getIndicadorFiscalizarCortado()
												.equals(
														Rota.INDICADOR_CORTADO_ATIVO)) {
									imoveisParaSerGerados.add(imovel);
									achouImovel = true;
								}
							}

						}
					}

				}

			}
			String idGrupoFaturamento = null;
			if (imoveisParaSerGerados != null
					&& !imoveisParaSerGerados.isEmpty()) {

				String nomeEmpresaAbreviado = null;

				// pega o id da empresa do objeto imovel.
				Integer idEmpresaOld = null;

				// cria uma variavel do tipo boolean para saber se � a mesma
				// empresa
				// ou
				// outra empresa.
				boolean mesmaEmpresa = false;

				// � usado para na faixa falsa saber se o hidrometro foi
				// selecionado
				// ou
				// n�o
				boolean hidrometroSelecionado = false;

				// � usado para criar o header do arquivo de leitura
				boolean headerArquivo = true;

				boolean headerFiscalizacao = true;

				Integer quantidadeRegistros = 0;

				Integer quantidadeImoveis = 0;

				Integer quantidadeRegistrosFiscalizacao = 0;

				String quantidadeRegistrosString = null;
				String quantidadeRegistrosFiscalizacaoString = null;

				// String quantidadeRegistrosFiscalizacaoString = null;

				StringBuilder arquivoTxt = new StringBuilder();

				StringBuilder arquivoHeaderFiscalizacao = new StringBuilder();

				StringBuilder arquivoTxtFiscalizacao = new StringBuilder();

				// cria as strings para mandar para o email
				String emailReceptor = null;
				String emailRemetente = null;
				String tituloMensagem = null;
				String corpoMensagem = null;

				Calendar dataCalendar = new GregorianCalendar();

				String ano = null;
				String mes = null;
				String dia = null;

				ListIterator imovelParaSerGeradoIterator = ((List) imoveisParaSerGerados)
						.listIterator(0);

				Imovel imovelParaSerGerado = null;
				// ListIterator imovelParaSerGeradoIterator = (ListIterator)
				// imoveisParaSerGerados
				// .iterator();

				while (imovelParaSerGeradoIterator.hasNext()) {
					boolean ligacaoAgua = false;
					boolean ligacaoPoco = false;

					// cria uma string builder para adicionar no arquivo que
					// ser�
					// mandado para a empresa
					// como tamb�m para ser adicionado no arquivo de
					// fiscaliza��o.

					StringBuilder arquivoTxtLinha = new StringBuilder();

					imovelParaSerGerado = (Imovel) imovelParaSerGeradoIterator
							.next();

					// se for para criar o header do arquivo
					if (headerArquivo) {

						//*******************************************
						
						
						//	-------------------------
						//
						// Registrar o in�cio do processamento da Unidade de
						// Processamento
						// do Batch
						//
						// -------------------------
						idUnidadeIniciada = getControladorBatch()
								.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
										UnidadeProcessamento.ROTA, imovelParaSerGerado.getQuadra()
										.getRota().getId());
						
						
						// pega o id da empresa do objeto imovel.
						idEmpresaOld = imovelParaSerGerado.getQuadra()
								.getRota().getEmpresa().getId();

						nomeEmpresaAbreviado = completaString(
								imovelParaSerGerado.getQuadra().getRota()
										.getEmpresa().getDescricaoAbreviada(),
								1);
						idGrupoFaturamento = completaString(imovelParaSerGerado
								.getQuadra().getRota().getFaturamentoGrupo()
								.getId().toString(), 2);

						ano = "" + dataCalendar.get(Calendar.YEAR);
						mes = "" + (dataCalendar.get(Calendar.MONTH) + 1);
						dia = "" + dataCalendar.get(Calendar.DAY_OF_MONTH);

						mes = Util.adicionarZerosEsquedaNumero(2, mes);
						dia = Util.adicionarZerosEsquedaNumero(2, dia);

						arquivoTxt.append(nomeEmpresaAbreviado + "T"
								+ idGrupoFaturamento + anoMesCorrente + dia
								+ mes + ano + "000000");

						// manda o header do arquivo para falso
						headerArquivo = false;

						arquivoHeaderFiscalizacao.append(arquivoTxt);
						arquivoTxt.append(System.getProperty("line.separator"));

					}

					// Verifica se a empresa da rota que est� na cole��o � igual
					// a
					// empresa anterior
					if (imovelParaSerGerado.getQuadra().getRota().getEmpresa()
							.getId().equals(idEmpresaOld)) {
						mesmaEmpresa = true;

					} else {
						mesmaEmpresa = false;

					}

					if (mesmaEmpresa) {
						// incrementa a quantidade de registros
						quantidadeRegistros = quantidadeRegistros + 1;

						quantidadeImoveis = quantidadeImoveis + 1;

						if (imovelParaSerGerado.getLigacaoAgua() != null
								&& imovelParaSerGerado.getLigacaoAgua().getId() != null
								&& imovelParaSerGerado.getLigacaoAgua()
										.getHidrometroInstalacaoHistorico() != null
								&& imovelParaSerGerado.getLigacaoAgua()
										.getHidrometroInstalacaoHistorico()
										.getId() != null) {
							ligacaoAgua = true;
						}
						if (imovelParaSerGerado
								.getHidrometroInstalacaoHistorico() != null
								&& imovelParaSerGerado
										.getHidrometroInstalacaoHistorico()
										.getId() != null) {
							ligacaoPoco = true;
						}

						// inscri��o do imovel

						arquivoTxtLinha.append(Util
								.adicionarZerosEsquedaNumero(3, ""
										+ imovelParaSerGerado.getLocalidade()
												.getId()));
						arquivoTxtLinha.append(Util
								.adicionarZerosEsquedaNumero(3, ""
										+ imovelParaSerGerado
												.getSetorComercial()
												.getCodigo()));
						arquivoTxtLinha.append(Util
								.adicionarZerosEsquedaNumero(3, ""
										+ imovelParaSerGerado.getQuadra()
												.getNumeroQuadra()));

						arquivoTxtLinha.append(Util
								.adicionarZerosEsquedaNumero(4, ""
										+ +imovelParaSerGerado.getLote()));

						arquivoTxtLinha.append(Util
								.adicionarZerosEsquedaNumero(3, ""
										+ imovelParaSerGerado.getSubLote()));

						// caso seja tipo liga��o agua e po�o cria a string
						// primeiro
						// com
						// tipo
						// liga��o agua
						if (ligacaoAgua && ligacaoPoco) {

							if (imovelParaSerGerado.getLigacaoAgua() != null
									&& imovelParaSerGerado.getLigacaoAgua()
											.getHidrometroInstalacaoHistorico() != null
									&& imovelParaSerGerado.getLigacaoAgua()
											.getHidrometroInstalacaoHistorico()
											.getId() != null
									&& !imovelParaSerGerado.getLigacaoAgua()
											.getHidrometroInstalacaoHistorico()
											.getId().equals("")) {
								arquivoTxtLinha
										.append(Util
												.completaString(
														""
																+ imovelParaSerGerado
																		.getLigacaoAgua()
																		.getHidrometroInstalacaoHistorico()
																		.getMedicaoTipo()
																		.getId(),
														1));
							}
							// caso n�o seja
						} else {
							// caso seja tipo liga��o agua cria a string com
							// tipo
							// liga��o agua
							if (ligacaoAgua) {
								if (imovelParaSerGerado.getLigacaoAgua() != null
										&& imovelParaSerGerado
												.getLigacaoAgua()
												.getHidrometroInstalacaoHistorico() != null
										&& imovelParaSerGerado
												.getLigacaoAgua()
												.getHidrometroInstalacaoHistorico()
												.getId() != null
										&& !imovelParaSerGerado
												.getLigacaoAgua()
												.getHidrometroInstalacaoHistorico()
												.getId().equals("")) {
									arquivoTxtLinha
											.append(Util
													.completaString(
															""
																	+ imovelParaSerGerado
																			.getLigacaoAgua()
																			.getHidrometroInstalacaoHistorico()
																			.getMedicaoTipo()
																			.getId(),
															1));
								}
							} else {
								// caso seja tipo liga��o po�o cria a string com
								// tipo
								// liga��o po�o
								if (ligacaoPoco) {
									if (imovelParaSerGerado
											.getHidrometroInstalacaoHistorico() != null
											&& imovelParaSerGerado
													.getHidrometroInstalacaoHistorico()
													.getId() != null
											&& !imovelParaSerGerado
													.getHidrometroInstalacaoHistorico()
													.getId().equals("")) {
										arquivoTxtLinha
												.append(Util
														.completaString(
																""
																		+ imovelParaSerGerado
																				.getHidrometroInstalacaoHistorico()
																				.getMedicaoTipo()
																				.getId(),
																1));
									}
								}
							}
						}

						// id do grupo de faturamento
						arquivoTxtLinha
								.append(Util.adicionarZerosEsquedaNumero(2, ""
										+ imovelParaSerGerado.getQuadra()
												.getRota()
												.getFaturamentoGrupo().getId()));

						// matricula do im�vel
						arquivoTxtLinha.append(Util
								.adicionarZerosEsquedaNumero(8, ""
										+ +imovelParaSerGerado.getId()));

						// id do perfil do imovel
						arquivoTxtLinha.append(Util
								.adicionarZerosEsquedaNumero(1, ""
										+ imovelParaSerGerado.getImovelPerfil()
												.getId()));

						String nomeClienteUsuario = null;
						try {
							// Pesquisa o nome do cliente que tem o tipo de
							// rela��o
							// usu�rio.
							nomeClienteUsuario = repositorioClienteImovel
									.pesquisarNomeClientePorImovel(imovelParaSerGerado
											.getId());
						} catch (ErroRepositorioException e) {
							throw new ControladorException("erro.sistema", e);
						}
						// nome do cliente usu�rio
						arquivoTxtLinha.append(completaString(
								nomeClienteUsuario, 25));

						// Pesquisa o endere�o do imovel passando o id
						String enderecoImovel = getControladorEndereco()
								.pesquisarEnderecoFormatado(
										imovelParaSerGerado.getId());
						if (enderecoImovel != null
								&& !enderecoImovel.equals("")) {
							// endere�o do im�vel
							arquivoTxtLinha.append(completaString(
									enderecoImovel, 50));
						} else {
							arquivoTxtLinha.append(completaString("", 50));
						}

						// Dados do Hidrometro

						// caso seja tipo liga��o agua e po�o cria a string
						// primeiro
						// com
						// tipo
						// liga��o agua
						Short numeroDigitosHidrometro = null;
						StringBuilder dadosHidrometro = null;
						if (ligacaoAgua && ligacaoPoco) {
							Object[] dadosHidroemtroNumeroLeitura = pesquisarDadosHidrometroTipoLigacaoAgua(imovelParaSerGerado);
							dadosHidrometro = (StringBuilder) dadosHidroemtroNumeroLeitura[0];
							numeroDigitosHidrometro = (Short) dadosHidroemtroNumeroLeitura[1];
							arquivoTxtLinha.append(dadosHidrometro);
							// caso n�o seja
						} else {
							// caso seja tipo liga��o agua cria a string com
							// tipo
							// liga��o agua
							if (ligacaoAgua) {
								Object[] dadosHidroemtroNumeroLeitura = pesquisarDadosHidrometroTipoLigacaoAgua(imovelParaSerGerado);
								dadosHidrometro = (StringBuilder) dadosHidroemtroNumeroLeitura[0];
								numeroDigitosHidrometro = (Short) dadosHidroemtroNumeroLeitura[1];
								arquivoTxtLinha.append(dadosHidrometro);
								// caso n�o seja
							} else {
								// caso seja tipo liga��o po�o cria a string com
								// tipo
								// liga��o po�o
								if (ligacaoPoco) {
									Object[] dadosHidroemtroNumeroLeitura = pesquisarDadosHidrometroTipoPoco(imovelParaSerGerado);
									dadosHidrometro = (StringBuilder) dadosHidroemtroNumeroLeitura[0];
									numeroDigitosHidrometro = (Short) dadosHidroemtroNumeroLeitura[1];
									arquivoTxtLinha.append(dadosHidrometro);

									// caso n�o seja nem um nem outro ent�o pode
									// chamar
									// qualquer um dos m�todos
									// pois os dois fazem a verifica��o e
									// retorna
									// strings
									// vazia e
									// a data cpm zeros
								} else {
									Object[] dadosHidroemtroNumeroLeitura = pesquisarDadosHidrometroTipoPoco(imovelParaSerGerado);
									dadosHidrometro = (StringBuilder) dadosHidroemtroNumeroLeitura[0];
									numeroDigitosHidrometro = (Short) dadosHidroemtroNumeroLeitura[1];
									arquivoTxtLinha.append(dadosHidrometro);
								}
							}
						}

						// id da ligacao agua situa��o
						if (imovelParaSerGerado.getLigacaoAguaSituacao() != null
								&& imovelParaSerGerado.getLigacaoAguaSituacao()
										.getId() != null) {
							// Situa��o da liga��o de agua
							arquivoTxtLinha.append(completaString(""
									+ imovelParaSerGerado
											.getLigacaoAguaSituacao().getId(),
									1));
						} else {
							// Situa��o da liga��o de agua
							arquivoTxtLinha.append(completaString("", 1));
						}
						// id da ligacao esgoto situa��o
						if (imovelParaSerGerado.getLigacaoEsgotoSituacao() != null
								&& imovelParaSerGerado
										.getLigacaoEsgotoSituacao().getId() != null) {
							// Situa��o de liga��o esgoto
							arquivoTxtLinha.append(completaString(
									""
											+ imovelParaSerGerado
													.getLigacaoEsgotoSituacao()
													.getId(), 1));
						} else {
							// Situa��o de liga��o esgoto
							arquivoTxtLinha.append(completaString("", 1));

						}

						// pega as descri��es das categorias do imovel

						Categoria categoria = getControladorImovel()
								.obterDescricoesCategoriaImovel(
										imovelParaSerGerado);

						// quantidade de economias
						arquivoTxtLinha.append(completaString(categoria
								.getDescricaoAbreviada(), 3));
						// [UC0086 - Obter quantidade de economias]
						int quantidadeEconomias = getControladorImovel()
								.obterQuantidadeEconomias(imovelParaSerGerado);
						// quantidade de economias
						arquivoTxtLinha.append(Util
								.adicionarZerosEsquedaNumero(3, ""
										+ quantidadeEconomias));

						// Leitura anterior

						Integer anoMesAnterior = Util
								.subtrairData(anoMesCorrente);
						String leituraAnterior = null;
						Integer idMedicaoTipo = null;
						MedicaoHistorico medicaoHistorico = null;
						Object[] retorno = pesquisaLeituraAnterior(ligacaoAgua,
								ligacaoPoco, anoMesAnterior,
								imovelParaSerGerado);
						// verifica se a leitura anterior � diferente de nula
						if (retorno[0] != null) {
							leituraAnterior = retorno[0].toString();
						}
						// verifica se a leitura situa��o atual � diferente de
						// nula
						if (retorno[1] != null) {
							medicaoHistorico = (MedicaoHistorico) retorno[1];
						}
						// verifica se o id da medi��o tipo � diferente de nula
						if (retorno[2] != null) {
							idMedicaoTipo = (Integer) retorno[2];
						}

						// verifica se a leitura anterior � diferente de nula
						// para
						// ser
						// jogado no arquivo
						// txt
						if (leituraAnterior != null) {
							arquivoTxtLinha.append(Util
									.adicionarZerosEsquedaNumero(6, ""
											+ leituraAnterior));
							// caso contrario coloca a string com zeros
						} else {
							arquivoTxtLinha.append(Util
									.adicionarZerosEsquedaNumero(6, ""));
						}

						// Faixa de leitura esperada

						Object[] faixaInicialFinal = pesquisarFaixaEsperadaOuFalsa(
								imovelParaSerGerado, dadosHidrometro,
								leituraAnterior, medicaoHistorico,
								idMedicaoTipo, sistemaParametro,
								hidrometroSelecionado, numeroDigitosHidrometro);

						StringBuilder faixaInicialFinalString = (StringBuilder) faixaInicialFinal[0];
						hidrometroSelecionado = Boolean
								.parseBoolean(faixaInicialFinal[1].toString());

						boolean faixaFalsaLeitura = Boolean
								.parseBoolean(faixaInicialFinal[2].toString());

						int faixaInicialEsperada = 0;
						int faixaFinalEsperada = 0;
						if (faixaFalsaLeitura) {
							faixaInicialEsperada = Integer
									.parseInt(faixaInicialFinal[3].toString());

							faixaFinalEsperada = Integer
									.parseInt(faixaInicialFinal[4].toString());
						}

						arquivoTxtLinha.append(faixaInicialFinalString);

						arquivoTxt.append(arquivoTxtLinha);

						// Gerar Fiscaliza��o de leitura

						// Rota rota1 =
						// imovelParaSerGerado.getQuadra().getRota();

						Short indicadorLeituraParametro = sistemaParametro
								.getIndicadorUsoFiscalizadorLeitura();

						Short indicadorGerarFiscalizacao = imovelParaSerGerado
								.getQuadra().getRota()
								.getIndicadorGerarFiscalizacao();

						boolean gerarArquivoFiscalizacao = false;

						if (indicadorLeituraParametro
								.equals(SistemaParametro.INDICADOR_USO_FISCALIZADOR_LEITURA_SISTEMA_PARAMETRO)
								|| indicadorLeituraParametro
										.equals(SistemaParametro.INDICADOR_USO_FISCALIZADOR_LEITURA_ROTA)) {
							if (indicadorGerarFiscalizacao != null
									&& indicadorGerarFiscalizacao
											.equals(Rota.INDICADOR_GERAR_FISCALIZACAO)) {
								if (headerFiscalizacao) {
									arquivoTxtFiscalizacao
											.append(arquivoHeaderFiscalizacao);
									arquivoTxtFiscalizacao.append(System
											.getProperty("line.separator"));
								}
								headerFiscalizacao = false;

								quantidadeRegistrosFiscalizacao = quantidadeRegistrosFiscalizacao + 1;
								gerarArquivoFiscalizacao = true;
								// caso seja indicado que seja gerado
								// fiscaliza��o
								// de
								// leitura
								// verifica se foi gerado a faixa falsa se foi
								// ent�o pega o txt da leitura (arquivoTxt)
								// mudando
								// so a
								// faixa de leitura
								// para a esperada.
								if (faixaFalsaLeitura) {
									arquivoTxtLinha.replace(139, 145, Util
											.adicionarZerosEsquedaNumero(6, ""
													+ faixaInicialEsperada));

									arquivoTxtLinha.replace(145, 151, Util
											.adicionarZerosEsquedaNumero(6, ""
													+ faixaFinalEsperada));

									arquivoTxtFiscalizacao
											.append(arquivoTxtLinha);
								} else {
									arquivoTxtFiscalizacao
											.append(arquivoTxtLinha);
								}

								arquivoTxtFiscalizacao.append(System
										.getProperty("line.separator"));
							}
						}

						arquivoTxt.append(System.getProperty("line.separator"));

						// caso seja liga��o de agua e de po�o ent�o �
						// necessario
						// criar
						// duas linhas txt
						// uma para agua e outra para po�o mudando s� os dados
						// do
						// hidrometro
						if (ligacaoAgua && ligacaoPoco) {
							// cria uma variavel para pegar os dados do
							// arquivoTxt
							StringBuilder arquivoTxtLigacaoPoco = new StringBuilder();
							arquivoTxtLigacaoPoco.append(arquivoTxtLinha);
							// dados do hidrometro tipo poco
							Object[] dadosHidroemtroNumeroLeitura = pesquisarDadosHidrometroTipoPoco(imovelParaSerGerado);
							dadosHidrometro = (StringBuilder) dadosHidroemtroNumeroLeitura[0];
							numeroDigitosHidrometro = (Short) dadosHidroemtroNumeroLeitura[1];

							// muda os dados do hidrometro do arquivoTxt para os
							// dados
							// do hidrometro
							// tipo po�o
							arquivoTxtLigacaoPoco.replace(103, 125,
									dadosHidrometro.toString());

							if (imovelParaSerGerado
									.getHidrometroInstalacaoHistorico().getId() != null
									&& imovelParaSerGerado
											.getHidrometroInstalacaoHistorico()
											.getId().equals("")) {
								arquivoTxtLigacaoPoco
										.replace(
												16,
												17,
												""
														+ imovelParaSerGerado
																.getHidrometroInstalacaoHistorico()
																.getMedicaoTipo()
																.getId());
							}

							// Leitura anterior

							// liga��o agua recebe falso, pois ja foi calculado
							// para
							// tipo agua e agora � preciso
							// ver a leitura anterior do tipo poco
							ligacaoAgua = false;

							String leituraAnteriorTipoPoco = null;

							Object[] retornoTipoPoco = pesquisaLeituraAnterior(
									ligacaoAgua, ligacaoPoco, anoMesAnterior,
									imovelParaSerGerado);
							// verifica se a leitura anterior � diferente de
							// nula
							if (retornoTipoPoco[0] != null) {
								leituraAnteriorTipoPoco = retornoTipoPoco[0]
										.toString();
							}
							// verifica se a medicao historico � diferente de
							// nula
							if (retornoTipoPoco[1] != null) {
								medicaoHistorico = (MedicaoHistorico) retornoTipoPoco[1];
							}
							// verifica se o id da medi��o tipo � diferente de
							// nula
							if (retornoTipoPoco[2] != null) {
								idMedicaoTipo = (Integer) retornoTipoPoco[2];
							}

							// verifica se a leitura anterior � diferente de
							// nula
							// para
							// ser
							// jogado no arquivo
							// txt
							if (leituraAnteriorTipoPoco != null) {
								// muda os dados do hidrometro para os dados do
								// hidrometro tipo po�o
								arquivoTxtLigacaoPoco.replace(133, 139,
										leituraAnteriorTipoPoco);
							} else {

								// muda os dados do hidrometro para os dados do
								// hidrometro tipo po�o
								arquivoTxtLigacaoPoco.replace(133, 139, Util
										.adicionarZerosEsquedaNumero(6, ""));
							}

							// Faixa de leitura esperada
							Object[] faixaInicialFinalTipoPoco = pesquisarFaixaEsperadaOuFalsa(
									imovelParaSerGerado, dadosHidrometro,
									leituraAnteriorTipoPoco, medicaoHistorico,
									idMedicaoTipo, sistemaParametro,
									hidrometroSelecionado,
									numeroDigitosHidrometro);

							faixaInicialFinalString = (StringBuilder) faixaInicialFinalTipoPoco[0];
							hidrometroSelecionado = Boolean
									.parseBoolean(faixaInicialFinal[1]
											.toString());

							faixaFalsaLeitura = Boolean
									.parseBoolean(faixaInicialFinal[2]
											.toString());

							faixaInicialEsperada = 0;
							faixaFinalEsperada = 0;
							if (faixaFalsaLeitura) {
								faixaInicialEsperada = Integer
										.parseInt(faixaInicialFinal[3]
												.toString());

								faixaFinalEsperada = Integer
										.parseInt(faixaInicialFinal[4]
												.toString());
							}

							// muda os dados do hidrometro para os dados do
							// hidrometro tipo po�o
							arquivoTxtLigacaoPoco.replace(139, 151,
									faixaInicialFinalString.toString());

							arquivoTxt.append(arquivoTxtLigacaoPoco);

							arquivoTxt.append(System
									.getProperty("line.separator"));

							quantidadeRegistros = quantidadeRegistros + 1;

							// Gerar Fiscaliza��o de leitura

							// caso seja indicado que seja gerado fiscaliza��o
							// de
							// leitura
							// verifica se foi gerado a faixa falsa se foi
							// ent�o pega o txt da leitura (arquivoTxt) mudando
							// so a
							// faixa de leitura
							// para a esperada.
							if (gerarArquivoFiscalizacao) {
								quantidadeRegistrosFiscalizacao = quantidadeRegistrosFiscalizacao + 1;

								if (faixaInicialEsperada != 0
										&& faixaFinalEsperada != 0) {
									arquivoTxtLigacaoPoco.replace(139, 145,
											Util.adicionarZerosEsquedaNumero(6,
													"" + faixaInicialEsperada));

									arquivoTxtLigacaoPoco.replace(145, 151,
											Util.adicionarZerosEsquedaNumero(6,
													"" + faixaFinalEsperada));
									arquivoTxtFiscalizacao
											.append(arquivoTxtLigacaoPoco);

								} else {
									arquivoTxtFiscalizacao
											.append(arquivoTxtLigacaoPoco);
								}

								arquivoTxtFiscalizacao.append(System
										.getProperty("line.separator"));
							}

						}

					} else {

						// cria o header final da string para mandar para a
						// empresa.
						Imovel ultimoImovelEmpresa = (Imovel) ((List) imoveisParaSerGerados)
								.get(quantidadeImoveis - 1);
						ano = "" + dataCalendar.get(Calendar.YEAR);
						mes = "" + (dataCalendar.get(Calendar.MONTH) + 1);
						dia = "" + dataCalendar.get(Calendar.DAY_OF_MONTH);

						mes = Util.adicionarZerosEsquedaNumero(2, mes);
						dia = Util.adicionarZerosEsquedaNumero(2, dia);

						nomeEmpresaAbreviado = completaString(
								ultimoImovelEmpresa.getQuadra().getRota()
										.getEmpresa().getDescricaoAbreviada(),
								1);
						idGrupoFaturamento = completaString(ultimoImovelEmpresa
								.getQuadra().getRota().getFaturamentoGrupo()
								.getId().toString(), 2);

						quantidadeRegistrosString = Util
								.adicionarZerosEsquedaNumero(6, ""
										+ quantidadeRegistros);
						quantidadeRegistrosFiscalizacaoString = Util
								.adicionarZerosEsquedaNumero(6, ""
										+ quantidadeRegistrosFiscalizacao);

						arquivoTxt.append(nomeEmpresaAbreviado + "T"
								+ idGrupoFaturamento + anoMesCorrente + dia
								+ mes + ano + quantidadeRegistrosString);

						if (!headerFiscalizacao) {
							/* quantidadeRegistrosFiscalizacaoString = */Util
									.adicionarZerosEsquedaNumero(6, ""
											+ quantidadeRegistrosFiscalizacao);
							arquivoTxtFiscalizacao.append(nomeEmpresaAbreviado
									+ "T" + idGrupoFaturamento + anoMesCorrente
									+ dia + mes + ano
									+ quantidadeRegistrosFiscalizacaoString);

							headerFiscalizacao = true;
						}

						// manda o header do arquivo para true,pois agora ser�
						// outra
						// empresa e precisa-se de um outro header
						headerArquivo = true;

						headerFiscalizacao = true;

						if (imovelParaSerGerado.getQuadra().getRota()
								.getEmpresa().getEmail() != null) {
							emailReceptor = imovelParaSerGerado.getQuadra()
									.getRota().getEmpresa().getEmail().trim();
						} else {
							emailReceptor = envioEmail.getEmailReceptor();
						}
						if (sistemaParametro.getDescricaoEmail() != null) {
							emailRemetente = sistemaParametro
									.getDescricaoEmail().trim();
						} else {
							emailRemetente = envioEmail.getEmailReceptor();
						}

						tituloMensagem = imovelParaSerGerado.getQuadra()
								.getRota().getFaturamentoGrupo().getDescricao();

						corpoMensagem = imovelParaSerGerado.getQuadra()
								.getRota().getFaturamentoGrupo().getDescricao();

						String nomeArquivo = ultimoImovelEmpresa.getQuadra()
								.getRota().getEmpresa().getDescricao()
								+ "_GR" + idGrupoFaturamento + ano + mes;
						if (arquivoTxt != null && arquivoTxt.length() != 0) {
							// chama o metodo que cria o txt e manda para o
							// e-mail
							// arquivo de leitura que ser� mandado para a
							// empresa.
							mandaArquivoLeituraEmail(arquivoTxt, emailReceptor,
									emailRemetente, tituloMensagem,
									corpoMensagem, nomeArquivo);
						}
						if (arquivoTxtFiscalizacao != null
								&& arquivoTxtFiscalizacao.length() != 0) {
							// chama o metodo que cria o txt e manda para o
							// e-mail
							// arquivo de leitura de fiscaliza��o que ser�
							// mandado
							// para
							// a
							// compesa.
							mandaArquivoLeituraEmail(arquivoTxtFiscalizacao,
									emailReceptor, emailRemetente,
									tituloMensagem + " - FISCALIZACAO LEITURA",
									corpoMensagem, nomeArquivo);
						}

						// pega o id da empresa do objeto imovel.
						idEmpresaOld = imovelParaSerGerado.getQuadra()
								.getRota().getEmpresa().getId();

						// manda a string para formar o txt e mandar para o
						// e-mail

						// retorna o iterator para criar a primeira linha do
						// proximo
						// txt.
						imovelParaSerGeradoIterator.previous();
						// cria outra string para come�ar a criar o txt.
						// limpa os campos para serem usados na pr�xima empresa
						arquivoTxt = new StringBuilder();
						quantidadeRegistros = 0;
						quantidadeRegistrosFiscalizacao = 0;
						arquivoTxtFiscalizacao = new StringBuilder();
						arquivoHeaderFiscalizacao = new StringBuilder();

					}
					// Encerra a unidade de Faturamento

					getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
							idUnidadeIniciada, false);
				}
				// cria o header final da ultima string para mandar para a
				// empresa
				Imovel ultimoImovelEmpresa = (Imovel) ((List) imoveisParaSerGerados)
						.get(quantidadeImoveis - 1);
				ano = "" + dataCalendar.get(Calendar.YEAR);
				mes = "" + (dataCalendar.get(Calendar.MONTH) + 1);
				dia = "" + dataCalendar.get(Calendar.DAY_OF_MONTH);

				mes = Util.adicionarZerosEsquedaNumero(2, "" + mes);
				dia = Util.adicionarZerosEsquedaNumero(2, "" + dia);

				nomeEmpresaAbreviado = completaString(ultimoImovelEmpresa
						.getQuadra().getRota().getEmpresa()
						.getDescricaoAbreviada(), 1);
				idGrupoFaturamento = completaString(ultimoImovelEmpresa
						.getQuadra().getRota().getFaturamentoGrupo().getId()
						.toString(), 2);

				quantidadeRegistrosString = Util.adicionarZerosEsquedaNumero(6,
						"" + quantidadeRegistros);

				arquivoTxt.append(nomeEmpresaAbreviado + "T"
						+ idGrupoFaturamento + anoMesCorrente + dia + mes + ano
						+ quantidadeRegistrosString);

				if (!headerFiscalizacao) {
					quantidadeRegistrosFiscalizacaoString = Util
							.adicionarZerosEsquedaNumero(6, ""
									+ quantidadeRegistrosFiscalizacao);
					arquivoTxtFiscalizacao.append(nomeEmpresaAbreviado + "T"
							+ idGrupoFaturamento + anoMesCorrente + ano + mes
							+ dia + quantidadeRegistrosFiscalizacaoString);

					headerFiscalizacao = true;
				}

				if (imovelParaSerGerado.getQuadra().getRota().getEmpresa()
						.getEmail() != null) {
					emailReceptor = imovelParaSerGerado.getQuadra().getRota()
							.getEmpresa().getEmail().trim();
				} else {
					emailReceptor = envioEmail.getEmailReceptor();
				}
				if (sistemaParametro.getDescricaoEmail() != null) {
					emailRemetente = sistemaParametro.getDescricaoEmail()
							.trim();
				} else {
					emailRemetente = envioEmail.getEmailRemetente();
				}
				tituloMensagem = imovelParaSerGerado.getQuadra().getRota()
						.getFaturamentoGrupo().getDescricao();

				corpoMensagem = imovelParaSerGerado.getQuadra().getRota()
						.getFaturamentoGrupo().getDescricao();

				String nomeArquivo = ultimoImovelEmpresa.getQuadra().getRota()
						.getEmpresa().getDescricao()
						+ "_GR" + idGrupoFaturamento + ano + mes;
				// chama o metodo que cria o txt e manda para o e-mail
				// arquivo de leitura que ser� mandado para a empresa.
				if (arquivoTxt != null && arquivoTxt.length() != 0) {
					mandaArquivoLeituraEmail(arquivoTxt, emailReceptor,
							emailRemetente, tituloMensagem, corpoMensagem,
							nomeArquivo);
				}

				// chama o metodo que cria o txt e manda para o e-mail
				// arquivo de leitura de fiscaliza��o que ser� mandado para a
				// compesa.
				if (arquivoTxtFiscalizacao != null
						&& arquivoTxtFiscalizacao.length() != 0) {
					mandaArquivoLeituraEmail(arquivoTxtFiscalizacao,
							emailReceptor, emailRemetente,
							tituloMensagem + " - FISCALIZACAO LEITURA",
							corpoMensagem, nomeArquivo);
				}

			}

			// atualiza a data e a hora da realiza��o da atividade com a data e
			// a hora correntes
			try {

				repositorioMicromedicao
						.atualizarFaturamentoAtividadeCronograma(
								idGrupoFaturamentoRota, anoMesCorrente);
			} catch (ErroRepositorioException e) {
				throw new ControladorException("erro.sistema", e);
			}

			

		} catch (Exception e) { // Este catch serve para interceptar
			// qualquer exce��o que o processo batch
			// venha a lan�ar e garantir que a unidade
			// de processamento do batch ser� atualizada
			// com o erro ocorrido
			e.printStackTrace();

			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
					idUnidadeIniciada, true);

			throw new EJBException(e);
		}
		return null;

	}

	
	
	
	
	
}