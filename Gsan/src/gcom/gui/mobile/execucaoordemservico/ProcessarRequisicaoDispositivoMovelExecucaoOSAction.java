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
* Davi Menezes de Oliveira
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

package gcom.gui.mobile.execucaoordemservico;

import gcom.cadastro.SistemaAndroid;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.mobile.execucaoordemservico.ArquivoTextoOSCobranca;
import gcom.util.ControladorException;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1487] Processar Arquivo Texto Dispositivo Movel Execu��o de OS
 *
 * @author Bruno Barros
 * @date 17/06/2013
 */
public class ProcessarRequisicaoDispositivoMovelExecucaoOSAction extends GcomAction {
	// Tipos de Resposta
	private static final char RESPOSTA_ERRO = '#';
	private static final byte RESPOSTA_OK = '*';

	// Tipos de Requisi��o
	private static final short BAIXAR_ARQUIVO = 2;
	private static final short ATUALIZAR_MOVIMENTO = 3;
	private static final short RECEBER_FOTO = 4;
	private static final short FINALIZAR_ROTEIRO = 5;
	private static final short ENCERRAR_ARQUIVO_RETORNO = 7;
	private static final short SINAL_ROTA_INICIALIZADA = 10;
	private static final int BAIXAR_APK = 11;
	private static final int VERIFICAR_VERSAO = 12;

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) {
		InputStream in = null;
		OutputStream out = null;
		DataInputStream din = null;

		// Verificamos qual foi a a��o solicitada pelo dispositivo m�vel
		try {
			in = request.getInputStream();
			out = response.getOutputStream();
			din = new DataInputStream(in);

			// O primeiro byte da requisi��o possue sempre o tipo da requisi��o
			// feita pelo dispositivo m�vel
			int acaoSolicitada = 0;

			try {
				acaoSolicitada = din.readByte();
			} catch (Exception ignorar) {
			}

			switch (acaoSolicitada) {
			case BAIXAR_ARQUIVO:
				// No caso de download de arquivo, � sempre passado o imei do celular
				long imei = din.readLong();
				String mac = "-1";

				try {
					mac = din.readUTF();
				} catch (Exception ignorar) {
				}

				this.baixarArquivo(imei, mac, response, out);
				break;
			case ATUALIZAR_MOVIMENTO:
				this.atualizarMovimentacao(din, response, out);
				break;
			case FINALIZAR_ROTEIRO:
				this.atualizarMovimentacao(din, response, out);
				break;
			case RECEBER_FOTO:
				this.receberFoto(din, response, out);
				break;
			case SINAL_ROTA_INICIALIZADA:
				// Atualizar Situacao do Arquivo
				long idArquivoTextoOSCobrancaSmartphone = din.readLong();
				this.atualizarSituacaoArquivo(response, out, idArquivoTextoOSCobrancaSmartphone);
				break;
			case BAIXAR_APK:
				out = response.getOutputStream();
				this.baixarNovaVersao(response, out);
				break;
			case VERIFICAR_VERSAO:
				this.verificarVersao(response, out);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			// Caso aconte�a qualquer problema, retornamos para o dispositivo
			// m�vel o caracter de erro no processamento da requisi��o

			response.setContentLength(1);

			try {
				out.write(RESPOSTA_ERRO);
				out.flush();
				System.out.println("ERRO: NO PROCESSAMENTO DO GSAN. RETORNOU " + RESPOSTA_ERRO + " PARA O CELULAR /n/n/n");
				e.printStackTrace();
			} catch (IOException ioe) {
				System.out.println("ERRO: NO PROCESSAMENTO DO GSAN. N�O ENVIOU RESPOSTA PARA O CELULAR /n/n/n");
				ioe.printStackTrace();
				throw new ActionServletException(ioe.getMessage());
			}
		} finally {
			try {
				if (in != null) { in.close(); }
				if (out != null) { out.close(); }
			} catch (IOException e) {
				e.printStackTrace();
				throw new ActionServletException(e.getMessage());
			}
		}

		return null;
	}

	/**
	 * [UC-1487] Processar Arquivo Texto Dispositivo Movel Execu��o de OS
	 * 
	 * Metodo que ira pesquisar o arquivo que sera carregado no celular
	 * 
	 * @autor Bruno Barros
	 * @date 18/06/2013
	 * 
	 * 
	 * @param BufferedReader
	 * @throws ControladorException
	 */
	private void atualizarMovimentacao(DataInputStream din, HttpServletResponse response, OutputStream out) throws IOException {
		InputStreamReader reader = new InputStreamReader(din, "UTF-8");
		BufferedReader buffer = new BufferedReader(reader);

		try {
			System.out.println("INICIO: Atualizando movimentacao da Execu��o de OS");

			getFachada().atualizarMovimentacaoExecucaoOS(buffer, null);
			out.write(RESPOSTA_OK);
			out.flush();

			System.out.println("FIM: Atualizando movimentacao da Execu��o de OS ");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR: Ao atualizar o arquivo de Execu��o de OS");
			response.setContentLength(1);
			out.write(RESPOSTA_ERRO);
			out.flush();
		}
	}

	/**
	 * Atualiza o arquivo texto para situacao "EM CAMPO" 
	 *
	 * @author Bruno Barros
	 * @date 27/10/2011
	 *
	 * @param idArquivoTextoVisitaCampo
	 * @param response
	 * @param out
	 * 
	 * @throws IOException
	 */
	private void atualizarSituacaoArquivo(HttpServletResponse response, 
		OutputStream out, long idArquivoTexto) throws IOException {
		try {
			// Atualiza o arquivo em campo
			System.out.println("INICIO: Atualizando a situacao do arquivo ID: " + idArquivoTexto);

			ArquivoTextoOSCobranca arquivoTexto = getFachada().pesquisar(ArquivoTextoOSCobranca.class,
					(int) idArquivoTexto);

			if (arquivoTexto != null) {
				SituacaoTransmissaoLeitura situacao = new SituacaoTransmissaoLeitura(
						SituacaoTransmissaoLeitura.EM_CAMPO);
				arquivoTexto.setSituacao(situacao);
				arquivoTexto.setDataEnvio(new Date());

				this.getFachada().atualizar(arquivoTexto);
			}

			out.write(RESPOSTA_OK);
			out.flush();

			System.out.println("FIM: Atualizando a situacao do arquivo ID: " + idArquivoTexto);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR: Ao atualizar situacao do arquivo em campo ID:" + idArquivoTexto);
			response.setContentLength(1);

			out.write(RESPOSTA_ERRO);
			out.flush();
		}
	}

	/**
	 * [UC-1487] Processar Arquivo Texto Dispositivo Movel Execu��o de OS
	 * 
	 * Metodo que ira pesquisar o arquivo que sera carregado no celular
	 * 
	 * @autor Bruno Barros
	 * @date 17/06/2013
	 * 
	 * @param imei - Imei do aparalho que ira receber o arquivo
	 * 
	 * @return array de bytes com o arquivo
	 *         
	 * @throws ControladorException
	 */
	private void baixarArquivo(long imei, String mac, HttpServletResponse response, OutputStream out) throws IOException {
		try {
			// Arquivo retornado
			byte[] arquivo = getFachada().baixarArquivoTextoExecucaoOS(imei/*, mac*/);

			if (arquivo != null) {
				System.out.println("INICIO: Baixando arquivo texto Execu��o OS IMEI: " + imei);

				// Parametro que identifica que o tipo de arquivo da rota est�
				// sendo enviado
				String parametroArquivoBaixarRota = "";

				// 1 do tipo de resposta ok + parametro Arquivo baixar rota +
				// tamanho do arquivo rota
				response.setContentLength(1 + parametroArquivoBaixarRota.getBytes().length + arquivo.length);

				out.write(RESPOSTA_OK);
				out.write(parametroArquivoBaixarRota.getBytes());
				out.write(arquivo);

				out.flush();

				System.out.println("FIM: Baixando arquivo texto Execu��o OS IMEI: " + imei);
			} else {
				System.out.println("ERROR: Nao existe arquivo texto Execu��o OS IMEI: " + imei);
				response.setContentLength(1);

				out.write(RESPOSTA_ERRO);
				out.flush();
			}
		} catch (Exception e) {
			System.out.println("ERROR: Ao baixar arquivo texto de fiscalizacacao de anormalidade");
			response.setContentLength(1);
			out.write(RESPOSTA_ERRO);
			out.flush();
		}
	}

	/**
	 * [UC-1487] Processar Arquivo Texto Dispositivo Movel Execu��o de OS
	 * 
	 * Metodo que ira pesquisar o arquivo que sera carregado no celular
	 * 
	 * @autor Bruno Barros
	 * @date 17/06/2013
	 * 
	 * @param imei - Imei do aparalho que ira receber o arquivo
	 * 
	 * @return array de bytes com o arquivo
	 *         
	 * @throws ControladorException
	 */
	private void receberFoto(DataInputStream din, HttpServletResponse response, OutputStream out) throws IOException {
		try {
			System.out.println("INICIO: Recebendo foto fiscalizacao anormalidade ");

			int numeroOS = din.readInt();
			int tipoFoto = din.readInt();
			int idArquivo = din.readInt();
			BigDecimal coordenadaX = new BigDecimal(din.readUTF());
			BigDecimal coordenadaY = new BigDecimal(din.readUTF());
			long fileSize = din.readLong();

			byte[] bytesFoto = new byte[(int) fileSize];
			int read = 0;
			int numRead = 0;
			while (read < bytesFoto.length && (numRead = din.read(bytesFoto, read, bytesFoto.length - read)) >= 0) {
				read = read + numRead;
			}

			// inserimos na base
			System.out.println("INICIO: Recebendo foto de OS de Cobranca Smartphone :" + numeroOS + " TIPO FOTO:" + tipoFoto);

			getFachada().inserirFotoOrdemServicoCobrancaSmartphone(idArquivo, numeroOS, tipoFoto, bytesFoto, coordenadaX, coordenadaY);

			System.out.println("FIM: Recebendo foto de OS de Cobranca Smartphone:" + numeroOS + " TIPO FOTO:" + tipoFoto);

			response.setContentLength(1 + read + bytesFoto.length);

			out.write(RESPOSTA_OK);
			out.flush();

			System.out.println("FIM: Recebendo foto fiscalizacao anormalidade ");
		} catch (Exception e) {
			System.out.println("ERROR: Ao receber a foto de fiscaliza��o de anormalidade");
			response.setContentLength(1);
			out.write(RESPOSTA_ERRO);
			out.flush();
		}
	}

	private void verificarVersao(HttpServletResponse response, OutputStream out) throws IOException {
		try {
			Object[] obj = getFachada().baixarNovaVersaoApk(SistemaAndroid.GSANEOS);

			if (obj == null)
				throw new Exception();

			String versao = (String) obj[1].toString();

			System.out.println("Inicio: Baixando valor da NOVA VERS�O, GSANEOS");

			out.write(RESPOSTA_OK);
			out.write(versao.getBytes());
			out.flush();

			System.out.println("Fim: Baixando valor NOVA VERS�O, ARQUIVO APK GSANEOS");
		} catch (Exception e) {
			System.out.println("Erro ao Baixar valor da VERS�O GSANEOS");
			response.setContentLength(1);
			out.write(RESPOSTA_ERRO);
			out.flush();
		}
	}

	private void baixarNovaVersao(HttpServletResponse response, OutputStream out) throws IOException {
		try {
			Object[] obj = getFachada().baixarNovaVersaoApk(SistemaAndroid.GSANEOS);
			if (obj == null)
				throw new Exception();

			byte[] arq = (byte[]) obj[0];

			System.out.println("Inicio : Baixando nova vers�o, arquivo APK GSANEOS");
			response.setContentLength(arq.length);

			out.write(RESPOSTA_OK);

			out.write(arq);
			out.flush();

			System.out.println("Fim: Baixando nova vers�o, arquivo APK GSANEOS");
		} catch (Exception e) {
			System.out.println("Erro ao baixar nova vers�o do GSANEOS");
			response.setContentLength(1);
			out.write(RESPOSTA_ERRO);
			out.flush();
		}
	}	
}
