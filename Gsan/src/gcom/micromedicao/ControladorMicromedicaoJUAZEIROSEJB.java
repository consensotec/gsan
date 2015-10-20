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
 * R�mulo Aur�lio de Melo Souza Filho
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

import gcom.batch.UnidadeProcessamento;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.ejb.SessionBean;

import org.apache.commons.fileupload.FileItem;


/**
 * Controlador Micromedicao Juazeiro
 * 
 * @author Rafael Corr�a
 * @date 30/06/2009
 */
public class ControladorMicromedicaoJUAZEIROSEJB extends ControladorMicromedicao implements SessionBean{
	
	private static final long serialVersionUID = 1L;
	
	//==============================================================================================================
	// M�TODOS EXCLUSIVOS DE JUAZEIRO
	//==============================================================================================================

	/**
	 * [UC00083] Gerar Dados para Leitura
	 * 
	 * [SB0005] Gerar Rela��o(ROL) em TXT - JUAZEIRO
	 * 
	 * @author Raphael Rossiter
	 * @date 27/08/2009
	 * 
	 * @param colecaoRota
	 * @param anoMesCorrente
	 * @param idGrupoFaturamentoRota
	 * @param sistemaParametro
	 * @param idFuncionalidadeIniciada
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection gerarDadosPorLeituraMicroColetor(Collection colecaoRota,
			Integer anoMesCorrente, Integer idGrupoFaturamentoRota,
			SistemaParametro sistemaParametro, int idFuncionalidadeIniciada)
			throws ControladorException {

		int idUnidadeIniciada = 0;

		Iterator colecaoRotaIterator = colecaoRota.iterator();

		while (colecaoRotaIterator.hasNext()) {
			
			Rota rota = (Rota) colecaoRotaIterator.next();
			
			//-------------------------
			//
			// Registrar o in�cio do processamento da Unidade de
			// Processamento
			// do Batch
			//
			// -------------------------
			idUnidadeIniciada = getControladorBatch()
			.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada, 
			UnidadeProcessamento.ROTA, rota.getId());

			try {


				//CONTROLE DE PAGINA��O DA PESQUISA
				int numeroIndice = 0;
				int quantidadeRegistrosPesquisa = 1000;
				boolean flagTerminou = false;
				Integer qtdImoveis = 0;

				//MOVIMENTO ROTEIRO EMPRESA
				Collection<Object[]> objetosMovimentoRoteiroEmpresa = new ArrayList<Object[]>();
				
				MovimentoRoteiroEmpresa movimentoRoteiroEmpresa = null;
				// SER� DEFINIDO SE JUAZEIRO UTILIZAR� HEADER PARA FORMATA��O DO ARQUIVO TXT
				//boolean headerArquivo = true;
				boolean headerArquivo = false;

				Integer quantidadeRegistros = new Integer(0);

				Integer quantidadeMovimentoRoteiroEmpresa = new Integer(0);

				Integer quantidadeRegistrosFiscalizacao = new Integer(0);

				StringBuilder arquivoTxt = new StringBuilder();

				StringBuilder arquivoHeaderFiscalizacao = new StringBuilder();

				StringBuilder arquivoTxtFiscalizacao = new StringBuilder();

				Calendar dataCalendar = new GregorianCalendar();
				
				//QUEBRANDO POR ROTA
				Integer idRotaOld = null;

				/*
				 * Cria uma vari�vel do tipo boolean para saber se � a mesma ROTA
				 * ou outra ROTA.
				 */
				boolean mesmaRota = false;

				while (!flagTerminou) {

					try {

						//PESQUISANDO MOVIMENTO ROTEIRO EMPRESA
						objetosMovimentoRoteiroEmpresa = repositorioMicromedicao
						.pesquisarImoveisParaLeituraPorColecaoRotaCAEMA(rota, numeroIndice, anoMesCorrente);

					} catch (ErroRepositorioException e) {
						throw new ControladorException("erro.sistema", e);
					}

					if (objetosMovimentoRoteiroEmpresa != null && !objetosMovimentoRoteiroEmpresa.isEmpty()) {

						qtdImoveis = qtdImoveis + objetosMovimentoRoteiroEmpresa.size();

						//CONTROLE DE PAGINA��O DA PESQUISA
						if (objetosMovimentoRoteiroEmpresa.size() < quantidadeRegistrosPesquisa) {
							flagTerminou = true;
						} 
						else {
							numeroIndice = numeroIndice + quantidadeRegistrosPesquisa;
						}						


						for (Object[] dadosMovimentoRoteiroEmpresa : objetosMovimentoRoteiroEmpresa) {

							boolean ligacaoAgua = false;
							boolean ligacaoPoco = false;

							// cria uma string builder para adicionar no arquivo
							StringBuilder arquivoTxtLinha = new StringBuilder();

							movimentoRoteiroEmpresa = (MovimentoRoteiroEmpresa) dadosMovimentoRoteiroEmpresa[0];
							Integer idQuadra = (Integer) dadosMovimentoRoteiroEmpresa[1];

							Quadra quadra = new Quadra();
							quadra.setId(idQuadra);

							Imovel imovel = new Imovel();
							imovel.setId(movimentoRoteiroEmpresa.getImovel().getId());
							imovel.setQuadra(quadra);
							movimentoRoteiroEmpresa.setImovel(imovel);
							

							// incrementa a quantidade de registros
							quantidadeRegistros = quantidadeRegistros + 1;

							quantidadeMovimentoRoteiroEmpresa = objetosMovimentoRoteiroEmpresa.size();

							//QUEBRANDO POR ROTA
							if (idRotaOld == null || 
								movimentoRoteiroEmpresa.getRota().getId().equals(idRotaOld)) {
								
								mesmaRota = true;

							} 
							else {
								
								mesmaRota = false;
							}

							if (mesmaRota) {
								
								//GERANDO O ARQUIVO TXT
								adicionarLinhaTxt(arquivoTxt, arquivoTxtLinha,
										arquivoTxtFiscalizacao,
										arquivoHeaderFiscalizacao,
										quantidadeRegistros,
										quantidadeMovimentoRoteiroEmpresa,
										quantidadeRegistrosFiscalizacao,
										movimentoRoteiroEmpresa, ligacaoAgua,
										ligacaoPoco, null,
										headerArquivo, sistemaParametro,
										idRotaOld, dataCalendar, anoMesCorrente);
							} 
							else {
								
								/*
								 * Manda o header do arquivo para true, pois agora ser� outra
								 * empresa e precisa-se de um outro header.
								 * 
								 * SER� DEFINIDO SE JUAZEIRO USAR� HEADER
								 * headerArquivo = true;
								 */
								headerArquivo = false;
								
								String anoCom2Digitos = "" + Util.obterAno(movimentoRoteiroEmpresa.getAnoMesMovimento());
								anoCom2Digitos = anoCom2Digitos.substring(2,4);

								String nomeArquivo = "ENV"
								+ Util.adicionarZerosEsquedaNumero(2, 
								"" + Util.obterMes(movimentoRoteiroEmpresa.getAnoMesMovimento()))
								+ anoCom2Digitos
								+ Util.adicionarZerosEsquedaNumero(2, 
								  movimentoRoteiroEmpresa.getLocalidade().getId().toString())
								+ Util.adicionarZerosEsquedaNumero(3,
								  movimentoRoteiroEmpresa.getCodigoRota().toString())
								+ Util.adicionarZerosEsquedaNumero(3,
								  movimentoRoteiroEmpresa.getCodigoRota().toString())
								+ ".txt";

								if (arquivoTxt != null && arquivoTxt.length() != 0) {
									
									//GERANDO O ARQUIVO TXT
									this.inserirArquivoTextoRoteiroEmpresa(
									anoMesCorrente, movimentoRoteiroEmpresa, qtdImoveis, arquivoTxt,
									nomeArquivo);
								}

								// ROTA ANTIGA.
								idRotaOld = movimentoRoteiroEmpresa.getRota().getId();

								/*
								 * Cria outra string para come�ar a criar o txt.
								 * Limpa os campos para serem usados na pr�xima empresa.
								 */
								arquivoTxt = new StringBuilder();
								quantidadeRegistros = 0;
								quantidadeRegistrosFiscalizacao = 0;
								arquivoTxtFiscalizacao = new StringBuilder();
								arquivoHeaderFiscalizacao = new StringBuilder();
								qtdImoveis = objetosMovimentoRoteiroEmpresa.size();

								adicionarLinhaTxt(arquivoTxt, arquivoTxtLinha,
										arquivoTxtFiscalizacao,
										arquivoHeaderFiscalizacao,
										quantidadeRegistros,
										quantidadeMovimentoRoteiroEmpresa,
										quantidadeRegistrosFiscalizacao,
										movimentoRoteiroEmpresa, ligacaoAgua,
										ligacaoPoco, null,
										headerArquivo, sistemaParametro,
										idRotaOld, dataCalendar, anoMesCorrente);

							}

							headerArquivo = false;

						}
						
					}else{
						flagTerminou = true;
					}
					
				}

				if(movimentoRoteiroEmpresa != null){
					
					String anoCom2Digitos = "" + Util.obterAno(movimentoRoteiroEmpresa.getAnoMesMovimento());
					anoCom2Digitos = anoCom2Digitos.substring(2,4);

					String nomeArquivo = 
						"ENV" + Util.adicionarZerosEsquedaNumero(2, 
						"" + Util.obterMes(movimentoRoteiroEmpresa.getAnoMesMovimento())) 
						+ anoCom2Digitos
						+ Util.adicionarZerosEsquedaNumero(2, movimentoRoteiroEmpresa.getLocalidade().getId().toString())
						+ Util.adicionarZerosEsquedaNumero(3, movimentoRoteiroEmpresa.getCodigoRota().toString()) 
						+ Util.adicionarZerosEsquedaNumero(3, movimentoRoteiroEmpresa.getCodigoRota().toString()) 
						+ ".txt";

					if (arquivoTxt != null && arquivoTxt.length() != 0) {
						
						//GERANDO O ARQUIVO TXT
						this.inserirArquivoTextoRoteiroEmpresa(anoMesCorrente, 
							movimentoRoteiroEmpresa,
							qtdImoveis, 
							arquivoTxt, 
							nomeArquivo);

					}
				}
		

				/*
				 * Atualiza a data e a hora da realiza��o da atividade com a data e
				 * a hora correntes.
				 */
				try {

					repositorioMicromedicao.atualizarFaturamentoAtividadeCronograma(
					idGrupoFaturamentoRota, anoMesCorrente);
				} 
				catch (ErroRepositorioException e) {
					throw new ControladorException("erro.sistema", e);
				}
					
				
				
				//	Encerra a unidade de Faturamento
				getControladorBatch().encerrarUnidadeProcessamentoBatch(
				null, idUnidadeIniciada, false);
				

			} catch (Exception e) { 
				
				/*
				 * Este catch serve para interceptar qualquer exce��o que o processo batch
				 * venha a lan�ar e garantir que a unidade de processamento do batch 
				 * ser� atualizada com o erro ocorrido.
				 */
				e.printStackTrace();

				getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
				idUnidadeIniciada, true);
			}
		}
		
		return null;
	}
	
	/**
	 * [UC0083]- Gerar dados para Leitura Author: R�mulo Aur�lio Date:
	 * 08/07/2008
	 * 
	 * @param arquivoTxt
	 * @param arquivoTxtLinha
	 * @param arquivoTxtFiscalizacao
	 * @param arquivoHeaderFiscalizacao
	 * @param quantidadeRegistros
	 * @param quantidadeMovimentoRoteiroEmpresa
	 * @param quantidadeRegistrosFiscalizacao
	 * @param movimentoRoteiroEmpresa
	 * @param ligacaoAgua
	 * @param ligacaoPoco
	 * @param idFaturamentoGrupoOld
	 * @param headerArquivo
	 * @param sistemaParametro
	 * @param idRotaOld
	 * @param dataCalendar
	 * @param anoMesCorrente
	 */
	private void adicionarLinhaTxt(StringBuilder arquivoTxt,
			StringBuilder arquivoTxtLinha,
			StringBuilder arquivoTxtFiscalizacao,
			StringBuilder arquivoHeaderFiscalizacao,
			Integer quantidadeRegistros,
			Integer quantidadeMovimentoRoteiroEmpresa,
			Integer quantidadeRegistrosFiscalizacao,
			MovimentoRoteiroEmpresa movimentoRoteiroEmpresa,
			boolean ligacaoAgua, boolean ligacaoPoco,
			Integer idFaturamentoGrupoOld, boolean headerArquivo,
			SistemaParametro sistemaParametro, Integer idRotaOld,
			Calendar dataCalendar, Integer anoMesCorrente) throws ControladorException{

		
		//M�s de refer�ncia (MM) (Num�rico)
		arquivoTxtLinha.append("" + Util.adicionarZerosEsquedaNumeroTruncando(2,
		"" + Util.obterMes(movimentoRoteiroEmpresa.getAnoMesMovimento())) + "#");
		
		
		//Ano de refer�ncia (AAAA) (Num�rico)
		arquivoTxtLinha.append("" + Util.adicionarZerosEsquedaNumeroTruncando(4,
		"" + Util.obterAno(movimentoRoteiroEmpresa.getAnoMesMovimento())) + "#");
		
		
		//Matr�cula do Im�vel sem d�gito (Num�rico)
		String matriculaImovel = movimentoRoteiroEmpresa.getImovel()
		.getId().toString();
		
		String matriculaImovelSemDigito = matriculaImovel.substring(0, matriculaImovel.length() - 1);
		
		arquivoTxtLinha.append("" + Util.adicionarZerosEsquedaNumeroTruncando(5,
		matriculaImovelSemDigito) + "#");
		
		
		//D�gito da Matr�cula do Im�vel (Num�rico)
		arquivoTxtLinha.append(matriculaImovel.substring(matriculaImovel.length() - 1, 
		matriculaImovel.length()) + "#");
		
		
		//Localidade (Num�rico)
		arquivoTxtLinha.append("" + Util.adicionarZerosEsquedaNumeroTruncando(2,
		movimentoRoteiroEmpresa.getLocalidade().getId().toString()) + "#");
		
		//Nome da Localidade  (Alfanum�rico)
		if (movimentoRoteiroEmpresa.getNomeLocalidade() != null){
			
			arquivoTxtLinha.append(completaString(movimentoRoteiroEmpresa
			.getNomeLocalidade(), 15) + "#");
		}
		else{
			arquivoTxtLinha.append(completaString("", 15) + "#");
		}
		
		
		//Rota (Num�rico)
		if (movimentoRoteiroEmpresa.getCodigoRota() != null){
			
			arquivoTxtLinha.append("" + Util.adicionarZerosEsquedaNumeroTruncando(3,
			movimentoRoteiroEmpresa.getCodigoRota().toString()) + "#");
		}
		else{
			arquivoTxtLinha.append("" + Util.adicionarZerosEsquedaNumeroTruncando(3, "") + "#");
		}
		
		
		//Seq��ncia do Im�vel na Rota
		if (movimentoRoteiroEmpresa.getNumeroSequencialRota() != null){
			
			arquivoTxtLinha.append("" + Util.adicionarZerosEsquedaNumeroTruncando(6,
			movimentoRoteiroEmpresa.getNumeroSequencialRota().toString()) + "#");
		}
		else{
			arquivoTxtLinha.append("" + Util.adicionarZerosEsquedaNumeroTruncando(6, "") + "#");
		}
		
		
		//Fixo
		arquivoTxtLinha.append(" " + "#");
		
		
		//Quadra  do Im�vel (Num�rico)
		arquivoTxtLinha.append("" + Util.adicionarZerosEsquedaNumeroTruncando(4,
		movimentoRoteiroEmpresa.getNumeroQuadra().toString()) + "#");
		
		
		//Lote do Im�vel (Num�rico)
		arquivoTxtLinha.append("" + Util.adicionarZerosEsquedaNumeroTruncando(4,
		movimentoRoteiroEmpresa.getNumeroLoteImovel().toString()) + "#");
		
		
		//Nome do Cliente (Alfanum�rico)
		if (movimentoRoteiroEmpresa.getNomeCliente() != null){
			
			arquivoTxtLinha.append(completaString(movimentoRoteiroEmpresa
			.getNomeCliente(), 45) + "#");
		}
		else{
			arquivoTxtLinha.append(completaString("", 45) + "#");
		}
		
		
		//C�digo do Grupo de Faturamento
		arquivoTxtLinha.append("" + Util.adicionarZerosEsquedaNumeroTruncando(4,
		movimentoRoteiroEmpresa.getFaturamentoGrupo().getId().toString()) + "#");
		
		
		//Endere�o
		Imovel imovelEndereco = this.getControladorEndereco()
		.pesquisarImovelParaEndereco(movimentoRoteiroEmpresa.getImovel().getId());
		
		arquivoTxtLinha.append(completaString(
		imovelEndereco.getEnderecoFormatado(), 44) + "#");
		
		
		//Complemento do endere�o do im�vel (Alfanum�rico)
		if (movimentoRoteiroEmpresa.getComplementoEndereco() != null){
			
			arquivoTxtLinha.append(completaString(movimentoRoteiroEmpresa.getNumeroImovel().trim()+"-"+movimentoRoteiroEmpresa.getComplementoEndereco(), 10) + "#");
		}
		else{
			arquivoTxtLinha.append(completaString(movimentoRoteiroEmpresa.getNumeroImovel().trim(), 10) + "#");
		}
		
		
		//Bairro do im�vel (Alfanum�rico)
		if (movimentoRoteiroEmpresa.getNomeBairro() != null){
			
			arquivoTxtLinha.append(completaString(movimentoRoteiroEmpresa.getNomeBairro(), 30) + "#");
		}
		else{
			arquivoTxtLinha.append(completaString("", 30) + "#");
		}
		
		
		//C�digo da Marca do Hidr�metro (Num�rico)
		if (movimentoRoteiroEmpresa.getHidrometroMarca() != null){
			
			arquivoTxtLinha.append("" + Util.adicionarZerosEsquedaNumeroTruncando(2,
			movimentoRoteiroEmpresa.getHidrometroMarca().getId().toString()) + "#");
		}
		else{
			arquivoTxtLinha.append("" + Util.adicionarZerosEsquedaNumeroTruncando(2, "") + "#");
		}
		
		
		//Descri��o da Marca do Hidr�metro (Alfanum�rico)
		if (movimentoRoteiroEmpresa.getHidrometroMarca() != null){
			
			arquivoTxtLinha.append(completaString(
			movimentoRoteiroEmpresa.getHidrometroMarca().getDescricao(), 15) + "#");
		}
		else{
			arquivoTxtLinha.append(completaString("", 15) + "#");
		}
		
		
		//N�mero do Hidr�metro (Alfanum�rico)
		if (movimentoRoteiroEmpresa.getNumeroHidrometro() != null){
			
			arquivoTxtLinha.append(completaString(
			movimentoRoteiroEmpresa.getNumeroHidrometro(), 10) + "#");
		}
		else{
			arquivoTxtLinha.append(completaString("", 10) + "#");
		}
		
		
		//C�digo da Situa��o da Liga��o de �gua do Im�vel (Num�rico)
		arquivoTxtLinha.append("" + Util.adicionarZerosEsquedaNumeroTruncando(2,
		movimentoRoteiroEmpresa.getLigacaoAguaSituacao().getId().toString()) + "#");

		//Descri��o da Situa��o da Liga��o de �gua do Im�vel (Alfanum�rico)
		arquivoTxtLinha.append(completaString(
		movimentoRoteiroEmpresa.getDescricaoLigacaoAguaSituacao(), 15) + "#");
		
		//DATA DA LEITURA ANTERIOR
		if (movimentoRoteiroEmpresa.getDataLeituraAnterior() != null){
			
			//DIA
			arquivoTxtLinha.append("" + Util.adicionarZerosEsquedaNumeroTruncando(2,
			String.valueOf(Util.getDiaMes(movimentoRoteiroEmpresa.getDataLeituraAnterior()))) + "#");
			
			//M�S
			arquivoTxtLinha.append("" + Util.adicionarZerosEsquedaNumeroTruncando(2,
			String.valueOf(Util.getMes(movimentoRoteiroEmpresa.getDataLeituraAnterior()))) + "#");
			
			//ANO
			arquivoTxtLinha.append("" + Util.adicionarZerosEsquedaNumeroTruncando(4,
			String.valueOf(Util.getAno(movimentoRoteiroEmpresa.getDataLeituraAnterior()))) + "#");
		}
		else{
			
			arquivoTxtLinha.append("" + Util.adicionarZerosEsquedaNumeroTruncando(2, "") + "#");
			arquivoTxtLinha.append("" + Util.adicionarZerosEsquedaNumeroTruncando(2, "") + "#");
			arquivoTxtLinha.append("" + Util.adicionarZerosEsquedaNumeroTruncando(4, "") + "#");
		}		
		
		//Leitura Anterior (Num�rico)
		if (movimentoRoteiroEmpresa.getNumeroLeituraAnterior() != null){
			
			arquivoTxtLinha.append("" + Util.adicionarZerosEsquedaNumeroTruncando(6,
			movimentoRoteiroEmpresa.getNumeroLeituraAnterior().toString()) + "#");
		}
		else{
			arquivoTxtLinha.append("" + Util.adicionarZerosEsquedaNumeroTruncando(6, "") + "#");
		}
		
		//Leitura Atual (Num�rico)
		arquivoTxtLinha.append("      #");
		
		//Consumo Anterior (Num�rico)
		if (movimentoRoteiroEmpresa.getNumeroConsumoFaturadoMenos1Mes() != null){
			
			arquivoTxtLinha.append("" + Util.adicionarZerosEsquedaNumeroTruncando(6,
			movimentoRoteiroEmpresa.getNumeroConsumoFaturadoMenos1Mes().toString()) + "#");
		}
		else{
			arquivoTxtLinha.append("" + Util.adicionarZerosEsquedaNumeroTruncando(6, "") + "#");
		}
		
		
		//MEDIA
		if (movimentoRoteiroEmpresa.getNumeroConsumoMedio() != null){
			
			arquivoTxtLinha.append("" + Util.adicionarZerosEsquedaNumeroTruncando(6,
			movimentoRoteiroEmpresa.getNumeroConsumoMedio().toString()) + "#");
		}
		else{
			arquivoTxtLinha.append("" + Util.adicionarZerosEsquedaNumeroTruncando(6, "") + "#");
		}
		
		
		//Fixo
		arquivoTxtLinha.append("050#");
		
		
		//Data (DDMMAAAA) (Alfanum�rico)
		arquivoTxtLinha.append(completaString("", 8) + "#");
		
		//Hora (HH:MM) 
		arquivoTxtLinha.append(completaString("", 5) + "#");

		//C�digo do Leiturista (Num�rico)
		arquivoTxtLinha.append(completaString("", 2) + "#");
		
		//Nome do Leiturista (Alfanum�rico)
		arquivoTxtLinha.append(completaString("", 15) + "#");
		
		//C�digo da Anormalidade Anterior (Num�rico)
		if (movimentoRoteiroEmpresa.getCodigoAnormalidadeAnterior() != null){
			
			arquivoTxtLinha.append("" + Util.adicionarZerosEsquedaNumeroTruncando(2,
			movimentoRoteiroEmpresa.getCodigoAnormalidadeAnterior().toString()) + "#");
		}
		else{
			arquivoTxtLinha.append("" + Util.adicionarZerosEsquedaNumeroTruncando(2, "") + "#");
		}
		
		
		//Descri��o da Anormalidade (Alfanum�rico)
		if (movimentoRoteiroEmpresa.getDescricaoAnormalidadeAnterior() != null){
			
			arquivoTxtLinha.append(completaString(
			movimentoRoteiroEmpresa.getDescricaoAnormalidadeAnterior(), 20) + "#");
		}
		else{
			arquivoTxtLinha.append(completaString("", 20) + "#");
		}


		//Fixo
		arquivoTxtLinha.append(completaString("", 2) + "#");
		
		//Fixo
		arquivoTxtLinha.append(completaString("", 2) + "#");
		
		//Quantidade de Economias (Num�rico)
		if (movimentoRoteiroEmpresa.getQuantidadeEconomias() != null){
			
			arquivoTxtLinha.append("" + Util.adicionarZerosEsquedaNumeroTruncando(3,
			movimentoRoteiroEmpresa.getQuantidadeEconomias().toString()) + "#");
		}
		else{
			arquivoTxtLinha.append("" + Util.adicionarZerosEsquedaNumeroTruncando(3, "") + "#");
		}
		
		
		//Tipo da Categoria (Alfanum�rico)
		if (movimentoRoteiroEmpresa.getDescricaoAbreviadaCategoriaImovel() != null){
			
			arquivoTxtLinha.append(movimentoRoteiroEmpresa
			.getDescricaoAbreviadaCategoriaImovel().substring(0, 1) + "#");
		}
		else{
			arquivoTxtLinha.append(completaString("", 1) + "#");
		}
		
		
		//Qtd de Economias 2
		arquivoTxtLinha.append("000#");
		
		//Categoria 2
		arquivoTxtLinha.append(" #");
		
		//Qtd de Economias 3
		arquivoTxtLinha.append("000#");
		
		//Categoria 3
		arquivoTxtLinha.append(" #");
		
		//Qtd de Economias 4
		arquivoTxtLinha.append("000#");
		
		//Categoria 4
		arquivoTxtLinha.append(" #");
		
		//Indicador do Movimento (Fixo)
		arquivoTxtLinha.append("0");

		
		
		// Registro Detalhe
		arquivoTxt.append(arquivoTxtLinha);

		arquivoTxt.append(System.getProperty("line.separator"));

	}
	
	
	/**
	 * [UC0082] Registrar Leituras e Anormalidades 
	 *
	 * @author Raphael Rossiter
	 * @date 03/09/2009
	 *
	 * @param idFaturamentoGrupo
	 * @param fileItem
	 * @return Collection<MedicaoHistorico>
	 * @throws ControladorException
	 */
	public Collection<MedicaoHistorico> processarArquivoTextoParaRegistrarLeiturasAnormalidades(
			Integer idFaturamentoGrupo, FileItem fileItem) throws ControladorException{
		
		Collection<MedicaoHistorico> colecaoMedicaoHistorico = new ArrayList();
		
		// ABRINDO O ARQUIVO
		InputStreamReader reader;
		try {
			reader = new InputStreamReader(fileItem.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
			throw new ControladorException("erro.importacao.nao_concluida");
		}

		// PREPARANDO OBJETO PARA LER O ARQUIVO
		BufferedReader buffer = new BufferedReader(reader);
		
		//VARI�VEl PARA LER O ARQUIVO LINHA A LINHA
		boolean eof = false;
		
		//CARREGANDO OS DADOS DO GRUPO DE FATURAMENTO ONDE SER�O REGISTRADAS AS LEITURAS
		FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
		
		filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
		FiltroFaturamentoGrupo.ID, idFaturamentoGrupo));
		
		Collection colecaoFaturamentoGrupo = this.getControladorUtil()
		.pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
		
		FaturamentoGrupo faturamentoGrupo = null;
		
		if (colecaoFaturamentoGrupo != null && !colecaoFaturamentoGrupo.isEmpty()) {
			
			faturamentoGrupo = (FaturamentoGrupo) Util
			.retonarObjetoDeColecao(colecaoFaturamentoGrupo);
		} 
		else {
			
			throw new ControladorException("atencao.entidade.inexistente",null,
			"Faturamento Grupo");
		}
		
		while (!eof) {
			
			// PEGANDO A LINHA DO ARQUIVO
			String linhaLida;
			try {
				linhaLida = buffer.readLine();
			} catch (IOException e) {
				e.printStackTrace();
				throw new ControladorException("erro.importacao.nao_concluida");
			}

			if (linhaLida == null) {
				
				// �LTIMA LINHA DO ARQUIVO
				eof = true;
			} 
			else if (linhaLida.length() == 377){

				// VARI�VEIS QUE SER�O UTILIZADAS PARA GERA��O DA MEDI��O HIST�RICO
				MedicaoHistorico medicaoHistorico = new MedicaoHistorico();

				MedicaoTipo medicaoTipo = new MedicaoTipo();
				Leiturista leiturista = new Leiturista();
				LeituraAnormalidade leituraAnormalidade = new LeituraAnormalidade();
				Imovel imovel = new Imovel();
				Localidade localidade = new Localidade();
				SetorComercial setorComercial = new SetorComercial();
				Quadra quadra = new Quadra();
				
				//M�S E ANO DA LEITURA
				String anoMesLeitura = linhaLida.substring(3, 7) + linhaLida.substring(0, 2);
				
				//VALIDANDO O M�S E ANO DA LEITURA
				boolean anoMesInvalido = Util.validarAnoMesSemBarra(anoMesLeitura);
				
				if (anoMesInvalido) {
					
					throw new ControladorException("atencao.anomes.faturamento.invalido");
				}
				
				if (!faturamentoGrupo.getAnoMesReferencia().equals(
					Integer.parseInt(anoMesLeitura))) {
					
					throw new ControladorException(
					"atencao.anomes.faturamento.nao.corresponde");
				}
				
				//ID DO GRUPO DE FATURAMENTO INFORMADO NO ARQUIVO
				String idFaturamentoHeaderString = linhaLida.substring(104, 108).trim();
				Integer idFaturamentoHeader = null;
				
				if (!idFaturamentoHeaderString.equals("")) {
					
					idFaturamentoHeader = new Integer(linhaLida.substring(104, 108));
				}
				
				if (idFaturamentoHeader != null && 
					!idFaturamentoGrupo.equals(idFaturamentoHeader)) {
					
					throw new ControladorException("atencao.nao.grupo.faturamento");
				}

				//INICIANDO O PROCESSO DE LEITURA DO ARQUIVO
				
				// ID DO LEITURISTA
				String idLeiturista = linhaLida.substring(303, 305).trim();
				
				Funcionario funcionario = new Funcionario();
							
				if (!idLeiturista.equals("")) {
					FiltroLeiturista filtroLeiturista = new FiltroLeiturista();
					filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade("funcionario");
					filtroLeiturista.adicionarParametro(new ParametroSimples(FiltroLeiturista.ID,idLeiturista));
					Collection colecaoLeiturista = getControladorUtil().pesquisar(filtroLeiturista,Leiturista.class.getName());
					leiturista = (Leiturista)Util.retonarObjetoDeColecao(colecaoLeiturista);
					if(leiturista != null && !leiturista.equals("")){
					  funcionario = leiturista.getFuncionario();
					}
					
				} 
				else {
								
					leiturista.setId(null);
				}
				

				medicaoHistorico.setLeiturista(leiturista);
				
				medicaoHistorico.setFuncionario(funcionario);

				//DATA DE LEITURA
				String dataColeta = linhaLida.substring(288, 296);
				
				//caso o im�vel n�o tenha sico coletado a leitura
				if(dataColeta.equals("00000   ")){
					continue;
				}
				medicaoHistorico.setDataLeituraParaRegistrar(dataColeta);

				//IM�VEL
				String idImovel = linhaLida.substring(8, 13) + linhaLida.substring(14, 15);
				
				System.out.println(idImovel);
							
				if (!idImovel.equals("")) {
					
					imovel.setId(new Integer(idImovel));
				} 
				else {
					
					imovel.setId(null);
				}

				//LOCALIDADE
				String idLocalidade = linhaLida.substring(16, 18).trim();
				
				if (!idLocalidade.equals("")) {
					
					localidade.setId(new Integer(linhaLida.substring(16, 18)));
				} 
				else {
					
					localidade.setId(null);
				}
				
				imovel.setLocalidade(localidade);
								
				//C�DIGO DO SETOR COMERCIAL
				String codigoSetorComercial = linhaLida.substring(35, 38).trim();
							
				if (!codigoSetorComercial.equals("")) {
								
					setorComercial.setCodigo(new Integer(linhaLida.substring(35, 38)));
				} 
				else {
								
					setorComercial.setCodigo(0);
				}
							
				imovel.setSetorComercial(setorComercial);

				//N�MERO DA QUADRA
				String numeroQuadra = linhaLida.substring(48, 52).trim();
							
				if (!numeroQuadra.equals("")) {
								
					quadra.setNumeroQuadra(new Integer(linhaLida.substring(48, 52)));
				} 
				else {
								
					quadra.setNumeroQuadra(0);
				}
							
				imovel.setQuadra(quadra);

				//LOTE
				String lote = linhaLida.substring(53, 57).trim();
							
				if (!lote.equals("")) {
								
					imovel.setLote(new Short(lote));
				} 
				else {
								
					imovel.setLote(new Short("0"));
				}
							
				//SUBLOTE
				imovel.setSubLote(new Short("0"));

				medicaoHistorico.setImovel(imovel);
							
				// TIPO DE MEDI��O
				String idMedicaoTipo = "1";
				medicaoTipo.setId(new Integer(idMedicaoTipo));
							
				medicaoHistorico.setMedicaoTipo(medicaoTipo);

				// LEITURA DO HIDR�METRO
				String leituraHidrometro = null;

				leituraHidrometro = linhaLida.substring(263, 269).trim();
							
				if (!leituraHidrometro.equals("")) {
								
					try {
									
						medicaoHistorico.setLeituraAtualInformada(new Integer(
						leituraHidrometro));
					} 
					catch (NumberFormatException ex) {
									
						medicaoHistorico.setLeituraAtualInformada(0);
					}
				} 
				else {
								
					medicaoHistorico.setLeituraAtualInformada(null);
				}

				//ANORMALIDADE
				String idLeituraAnormalidade = linhaLida.substring(322, 324).trim();

				if (!idLeituraAnormalidade.equals("")) {
								
					try {
									
						leituraAnormalidade.setId(new Integer(idLeituraAnormalidade));
					} 
					catch (NumberFormatException ex) {
									
						leituraAnormalidade.setId(0);
					}
				} 
				else {
								
					leituraAnormalidade.setId(null);
				}
							
				medicaoHistorico
				.setLeituraAnormalidadeInformada(leituraAnormalidade);

				// INDICADOR DE CONFIRMA��O DA LEITURA
				if (medicaoHistorico.getLeituraAtualInformada() != null){
					
					medicaoHistorico.setIndicadorConfirmacaoLeitura("1");
				}
				else{
					
					medicaoHistorico.setIndicadorConfirmacaoLeitura("2");
				}
				
				//INSERINDO A MEDI��O HISTORICO QUE FOI GERADA A PARTIR DO ARQUIVO TXT
				colecaoMedicaoHistorico.add(medicaoHistorico);
			}
			else{
				
				//�LTIMA LINHA DO ARQUIVO
				eof = true;
			}
		}
		
		// FECHANDO O ARQUIVO
		try {
			
			buffer.close();
			reader.close();
			fileItem.getInputStream().close();
		} 
		catch (IOException e) {
			e.printStackTrace();
			throw new ControladorException("erro.importacao.nao_concluida");
		}
		
		
		
		return colecaoMedicaoHistorico;
	}
}
