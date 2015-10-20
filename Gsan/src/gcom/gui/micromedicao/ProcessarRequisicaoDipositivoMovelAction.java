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
package gcom.gui.micromedicao;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroArquivoTextoRoteiroEmpresa;
import gcom.micromedicao.ServicoTipoCelular;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.util.filtro.ParametroSimples;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;

import javax.mail.SendFailedException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action utilizado
 * 
 * [UC0631] Processar Requisi��es do Dipositivo M�vel.
 * 
 * @author Thiago Nascimento
 * @since 15/08/2007
 * 
 * Atualizado por: Felipe Melo
 * Data: 04/12/2009
 * 
 */
public class ProcessarRequisicaoDipositivoMovelAction extends GcomAction {
	/**
	 * Constantes de Classe.
	 */
	private static final byte RESPOSTA_ERRO = 1;	
	private static final byte RESPOSTA_OK = 2;	
	public static final int RESPOSTA_NENHUMA_ROTA_DISPONIVEL = 3;

	//private static final int ARQUIVO_TEXTO_PARA_ENVIO = 2;
	private static final int ARQUIVO_TEXTO_ENVIADO = 3;
	private static final int ARQUIVO_TEXTO_FINALIZADO = 4;
	
	private static final int PACOTE_BAIXAR_ARQUIVO = 0;
	private static final int ATUALIZAR_MOVIMENTO = 1;
	private static final int FINALIZAR_LEITURA = 2;
	private static final int CONFIRMAR_ARQUIVO_RECEBIDO = 3;
	private static final int TESTE_CONEXAO = 4;

	/**
	 * M�todo Execute do Action
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		InputStream in = null;
		OutputStream out = null;
		try {
			in = request.getInputStream();
			DataInputStream din = new DataInputStream(in);
			
			SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
			System.out.println("SP : [EMP] - " + sistemaParametro.getNomeAbreviadoEmpresa());

			int pacote = din.readByte();

			System.out.println("Solicitacao Mobile : " + pacote);
			switch (pacote) {
			case PACOTE_BAIXAR_ARQUIVO:
				// Baixar Arquivo
				out = response.getOutputStream();
				this.baixarArquivo(din, response, out,sistemaParametro);
				break;
			case ATUALIZAR_MOVIMENTO:
				// Atualizar Movimento
				out = response.getOutputStream();
				this.atualizarMovimentacao(din, response, out);
				break;
			case FINALIZAR_LEITURA:
				// Finalizar Movimento
				out = response.getOutputStream();
				this.finalizarMovimentacao(din, response, out);
				break;
			case CONFIRMAR_ARQUIVO_RECEBIDO:
				// Finalizar Movimento
				this.confirmacaoArquivoRecebido(din);
				if(sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAER)){
				  out = response.getOutputStream();
				  response.setContentLength(RESPOSTA_OK);
				  out.write(RESPOSTA_OK);
				  out.flush();
				}
				break;
			case TESTE_CONEXAO:
				out = response.getOutputStream();
				response.setContentLength(RESPOSTA_OK);
				out.write(RESPOSTA_OK);
				out.flush();
			}

		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return null;

	}

	/**
	 * [UC0631] Processar Requisi��es do Dispositivo M�vel.
	 * 
	 * M�todo que baixa o arquivo de entrada do servidor.
	 * 
	 * @author Thiago Nascimento
	 * @date 14/08/2007
	 * 
	 * @param data
	 * @param response
	 * @throws IOException
	 */
	public void baixarArquivo(DataInputStream data,
			HttpServletResponse response, OutputStream out,SistemaParametro sistemaParametro) throws IOException {
		Fachada fachada = Fachada.getInstancia();
		long imei = data.readLong();
		System.out.println("imei: " + imei);

		// ******************************************************************
		// 08/01/2009
		// COMENTADO PARA ENTRAR S� NA PROX. VERS�O
		// String versao = data.readUTF();
		// System.out.println("versao: " + versao);
		// if(versao != null && versao.equals("v0.1")){
		// *************************************************************

		byte[] arq = fachada.baixarArquivoTextoParaLeitura(imei,ServicoTipoCelular.LEITURA);
		if (arq != null) {
			System.out.println("Inicio : Baixando arquivo Mobile");
			if(sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAER)){
				response.setContentLength(arq.length + 1);
				out.write(RESPOSTA_OK);
				out.write(arq);
				out.flush();
			}else{
				response.setContentLength(arq.length);
				out.write(arq);
				out.flush();
			}
			System.out.println("Fim: Baixando arquivo Mobile");
		} else {
			System.out.println("Erro ao Baixar arquivo Mobile");
			if(sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAER)){
			  response.setContentLength(RESPOSTA_NENHUMA_ROTA_DISPONIVEL);
			  out.write(RESPOSTA_NENHUMA_ROTA_DISPONIVEL);
			}else{
				response.setContentLength(RESPOSTA_ERRO);
				out.write(RESPOSTA_ERRO);
			}
			out.flush();
		}

		// ******************************************************************
		// else {
		// System.out.println("Erro vers�o Mobile");
		// response.setContentLength(RESPOSTA_ERRO_VERSAO);
		// OutputStream out = response.getOutputStream();
		// out.write(RESPOSTA_ERRO_VERSAO);
		// out.flush();
		// out.close();
		// }
		// ******************************************************************
	}

	/**
	 * [UC0631] Processar Requisi��es do Dispositivo M�vel.
	 * 
	 * M�todo que atualiza as movimenta��es dos leituristas.
	 * 
	 * @author Thiago Nascimento
	 * @date 14/08/2007
	 * 
	 * @throws IOException
	 * 
	 */
	public void atualizarMovimentacao(DataInputStream data,
			HttpServletResponse response, OutputStream out) throws IOException {
		Long imei = new Long(data.readLong());
		System.out.println("atualizar Movimentacao Mobile imei = " + imei);
		
		try {
			Collection<DadosMovimentacao> dados = this
					.gerarColecaoDadosMovimentacao(data, imei);


			//this.atualizarLeiturasAnormalidades(dados, response, false, out);
			Vector<DadosMovimentacao> v = new Vector<DadosMovimentacao>();
			v.addAll(dados);
			
			Fachada.getInstancia().atualizarLeituraAnormalidadeCelularCasoSistema(v);
			System.out.println("Fim: atualizarMovimentacao Mobile");

			// Resposta para o celular de OK
			response.setContentLength(1);
			// OutputStream out = response.getOutputStream();
			out.write(RESPOSTA_OK);
			out.flush();

			System.out.println("Fim: atualizar faturamento movimento celular");
		}catch (SendFailedException e) {
			e.printStackTrace();
			System.out.println("Erro ao finalizar movimento celular");
			// Resposta para o celular de OK
			response.setContentLength(1);
			// OutputStream out = response.getOutputStream();
			out.write(RESPOSTA_OK);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("Erro ao atualizar faturamento movimento celular");
			response.setContentLength(RESPOSTA_ERRO);
			out.write(RESPOSTA_ERRO);
			out.flush();
		}

	}

	/**
	 * [UC0631] Processar Requisi��es do Dispositivo M�vel.
	 * 
	 * M�todo que atualiza as movimenta��es dos leituristas.
	 * 
	 * @author Thiago Nascimento
	 * @date 14/08/2007
	 * 
	 * @throws IOException
	 * 
	 */
	public void atualizarLeiturasAnormalidades(
			Collection<DadosMovimentacao> dados, HttpServletResponse response,
			boolean finalizacao, OutputStream out) throws IOException {
		Fachada fachada = Fachada.getInstancia();
		if (!dados.isEmpty()) {
			//dados = null;
			fachada.atualiarRoteiro(dados, true);
		}

		// Resposta para o celular de OK na atualiza��o
//		if (!finalizacao) {
//			response.setContentLength(1);
//			// OutputStream out = response.getOutputStream();
//			out.write(RESPOSTA_OK);
//			out.flush();
//			// out.close();
//		}

	}
	

	/**
	 * [UC0631] Processar Requisi��es do Dispositivo M�vel.
	 * 
	 * M�todo que finaliza as movimenta��es.
	 * 
	 * @author Thiago Nascimento
	 * @date 14/08/2007
	 * 
	 * @throws IOException
	 * 
	 */
	public void finalizarMovimentacao(DataInputStream data,
			HttpServletResponse response, OutputStream out) throws IOException {
		Fachada fachada = Fachada.getInstancia();
		Long imei = new Long(data.readLong());

		System.out.println("Finalizando arquivo Mobile imei=" + imei);
		
		try{
			Collection<DadosMovimentacao> dados = this
			.gerarColecaoDadosMovimentacao(data, imei);
			
			//this.atualizarLeiturasAnormalidades(dados, response, true, out);

			// out.close();
			Vector<DadosMovimentacao> v = new Vector<DadosMovimentacao>();
			v.addAll(dados);
			//v = null;
			fachada.atualizarLeituraAnormalidadeCelularCasoSistema(v);
			
			FiltroArquivoTextoRoteiroEmpresa filtroArquivo = new FiltroArquivoTextoRoteiroEmpresa(
					FiltroArquivoTextoRoteiroEmpresa.NUMERO_SEQUENCIA_LEITURA);
			filtroArquivo
					.adicionarCaminhoParaCarregamentoEntidade(FiltroArquivoTextoRoteiroEmpresa.GRUPO_FATURAMENTO);
			filtroArquivo
					.adicionarCaminhoParaCarregamentoEntidade(FiltroArquivoTextoRoteiroEmpresa.ROTA);
			filtroArquivo.adicionarParametro(new ParametroSimples(
					FiltroArquivoTextoRoteiroEmpresa.IMEI, imei));
			filtroArquivo.adicionarParametro(new ParametroSimples(
					FiltroArquivoTextoRoteiroEmpresa.SITUACAO_TRANS_LEITURA_ID,
					SituacaoTransmissaoLeitura.EM_CAMPO));

			fachada.atualizarArquivoTextoEnviado(imei, ARQUIVO_TEXTO_ENVIADO,
					ARQUIVO_TEXTO_FINALIZADO);
			
			// Resposta para o celular de OK
			response.setContentLength(1);
			// OutputStream out = response.getOutputStream();
			out.write(RESPOSTA_OK);
			out.flush();
			
			System.out.println("Fim: finalizar movimento celular");
		}catch (SendFailedException e) {
			e.printStackTrace();
			System.out.println("Erro ao finalizar movimento celular");
			// Resposta para o celular de OK
			response.setContentLength(1);
			// OutputStream out = response.getOutputStream();
			out.write(RESPOSTA_OK);
			out.flush();
		
		} catch( Exception e ){
			e.printStackTrace();
			System.out.println("Erro ao finalizar movimento celular");
			response.setContentLength(RESPOSTA_ERRO);			
			out.write(RESPOSTA_ERRO);
			out.flush();
		}

	}

	/**
	 * 
	 * M�todo de Comfirma��o do Recebimento do Arquivo
	 * 
	 * @since 23/05/08
	 * @param data
	 * @throws IOException
	 */
	public void confirmacaoArquivoRecebido(DataInputStream data)
			throws IOException {
		Fachada fachada = Fachada.getInstancia();
		long imei = data.readLong();

		System.out.println("Confirmando Recebimento do arquivo Mobile imei = "
				+ imei);
		
		//Atualiza a situa��o do arquivo texto de "em campo" para "finalizado e n�o transmitido"
		//Altera��o feita por S�vio Luiz 
		//Data:05/04/2010
		fachada.atualizarArquivoTextoEnviado(imei, SituacaoTransmissaoLeitura.EM_CAMPO,
				SituacaoTransmissaoLeitura.FINALIZADO_NAO_TRANSMITIDO);
		
		//Atualiza a situa��o do arquivo texto de "liberado" para "em campo"
		//Altera��o feita por S�vio Luiz 
		//Data:05/04/2010
		fachada.atualizarArquivoTextoEnviado(imei, SituacaoTransmissaoLeitura.LIBERADO,
				SituacaoTransmissaoLeitura.EM_CAMPO);
		
		//Atualiza a situa��o do arquivo texto de "dispon�vel" para "liberado"
		//Altera��o feita por S�vio Luiz 
		//Data:05/04/2010
		fachada.atualizarArquivoTextoMenorSequencialLeitura(imei,SituacaoTransmissaoLeitura.DISPONIVEL,
				SituacaoTransmissaoLeitura.LIBERADO,ServicoTipoCelular.LEITURA);

	}

	/**
	 * M�todo auxiliar que gerar uma Cole��o de Dados da Movimenta��o
	 * 
	 * @param data
	 * @return
	 * @throws IOException
	 */
	private Collection<DadosMovimentacao> gerarColecaoDadosMovimentacao(
			DataInputStream data, Long imei) throws IOException {
		Collection<DadosMovimentacao> dados = new ArrayList<DadosMovimentacao>();
		int imovel = data.read();
		while (imovel != -1) {

			byte[] b = new byte[4];
			b[0] = (byte) imovel;
			b[1] = (byte) data.read();
			b[2] = (byte) data.read();
			b[3] = (byte) data.read();

			dados.add(new DadosMovimentacao(new Integer(this
					.convertArrayByteToInt(b)), new Integer(data.readInt()),
					new Integer(data.readByte()), new Date(data.readLong()),
					imei, new Byte(data.readByte()),
					new Integer(data.readInt())));

			// System.out.println("imovel=" + data.read());
			imovel = data.read();
		}
		return dados;
	}

	/**
	 * [UC0631] Processar Requisi��es do Dispositivo M�vel.
	 * 
	 * M�todo auxilar para converter um array de byte em um inteiro.
	 * 
	 * @author Thiago Nascimento
	 * @date 14/08/2007
	 * 
	 * @param b
	 * @return int
	 */
	private int convertArrayByteToInt(byte[] b) {
		int i = 0;
		i += unsignedByteToInt(b[0]) << 24;
		i += unsignedByteToInt(b[1]) << 16;
		i += unsignedByteToInt(b[2]) << 8;
		i += unsignedByteToInt(b[3]) << 0;
		return i;

	}

	/**
	 * [UC0631] Processar Requisi��es do Dispositivo M�vel.
	 * 
	 * M�todo auxiliar para converter byte em int.
	 * 
	 * @author Thiago Nascimento
	 * @date 14/08/2007
	 * 
	 * @param b
	 * @return
	 */
	private int unsignedByteToInt(byte b) {
		return (int) b & 0xFF;
	}

}