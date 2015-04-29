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
package gsan.tarefa;

import gsan.batch.ControladorBatchLocal;
import gsan.batch.ControladorBatchLocalHome;
import gsan.batch.FuncionalidadeIniciada;
import gsan.batch.Relatorio;
import gsan.batch.RelatorioGerado;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.RelatorioDataSource;
import gsan.relatorio.RelatorioVazioException;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesJNDI;
import gsan.util.ConstantesSistema;
import gsan.util.ControladorException;
import gsan.util.ControladorUtilLocal;
import gsan.util.ControladorUtilLocalHome;
import gsan.util.Internacionalizador;
import gsan.util.IoUtil;
import gsan.util.ServiceLocator;
import gsan.util.ServiceLocatorException;
import gsan.util.SistemaException;
import gsan.util.ZipUtil;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.zip.ZipOutputStream;

import javax.ejb.CreateException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Classe que representa uma tarefa
 * 
 * @author thiago
 * @date 24/01/2006
 */
public abstract class TarefaRelatorio extends Tarefa {

	private static final long serialVersionUID = 1L;

	public static final int TIPO_PDF = 1;

	public static final int TIPO_RTF = 2;

	public static final int TIPO_XLS = 3;

	public static final int TIPO_HTML = 4;
	
	public static final int TIPO_TXT = 5;
	
	public static final int TIPO_CSV = 6;

	public static final int INDICADOR_EXIBE_MENSAGEM = 1;

	public static final int INDICADOR_NAO_EXIBE_MENSAGEM = 2;

	public static final String PARAMETRO_LOCALE_RELATORIO = "REPORT_LOCALE";

	public static final String PARAMETRO_CAMINHO_PROPERTIES_RELATORIO = "REPORT_RESOURCE_BUNDLE";

	public static final String VALOR_PARAMETRO_CAMINHO_PROPERTIES_RELATORIO = "gsan.properties.application";

	protected String nomeRelatorio = null;


	/**
	 * M�todo que gera todos os relat�rios do sistema de acordo com o formato
	 * selecionado
	 * 
	 * @author Rodrigo Silveira
	 * @date 22/05/2006
	 * 
	 * @param nomeRelatorio
	 * @param parametrosRelatorio
	 * @param relatorioDataSource
	 * @param tipoSaidaRelatorio
	 * @return Um map com o MIME type e a representa��o bin�ria do relat�rio
	 * @throws RelatorioVazioException
	 */
	public byte[] gerarRelatorio(String nomeRelatorio, Map parametrosRelatorio,
			RelatorioDataSource relatorioDataSource, int tipoSaidaRelatorio)
			throws RelatorioVazioException {

		// valor de retorno
		ByteArrayOutputStream retorno = new ByteArrayOutputStream();
		byte[] retornoArray = null;

		// cria uma inst�ncia da classe JasperReport que vai conter o relat�rio
		// criado
		JasperReport jasperReport = null;

		InputStream stream = null;

		try {

			// carrega o arquivo do relat�rio (jasper) j� compilado
			stream = (ConstantesRelatorios.getURLRelatorio(nomeRelatorio)).openStream();

			// carrega o relat�rio compilado
			jasperReport = (JasperReport) JRLoader.loadObject(stream);

			stream.close();

			if(Internacionalizador.getLocale() != null){
				parametrosRelatorio.put(PARAMETRO_CAMINHO_PROPERTIES_RELATORIO, 
						ResourceBundle.getBundle(VALOR_PARAMETRO_CAMINHO_PROPERTIES_RELATORIO, Internacionalizador.getLocale())) ;

				parametrosRelatorio.put( PARAMETRO_LOCALE_RELATORIO, Internacionalizador.getLocale());				
			}else{
				parametrosRelatorio.put(PARAMETRO_CAMINHO_PROPERTIES_RELATORIO, 
						ResourceBundle.getBundle(VALOR_PARAMETRO_CAMINHO_PROPERTIES_RELATORIO, new Locale("pt","BR"))) ;

				parametrosRelatorio.put( PARAMETRO_LOCALE_RELATORIO, new Locale("pt","BR"));								
			}
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
					parametrosRelatorio, relatorioDataSource);

			// Verifica qual o tipo de relat�rio para definir a gera��o
			switch (tipoSaidaRelatorio) {
			case TIPO_PDF:

				JRPdfExporter exporterPDF = new JRPdfExporter();
				exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT,
						jasperPrint);
				exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM,
						retorno);
				exporterPDF.exportReport();

				break;
			case TIPO_RTF:

				JRRtfExporter exporterRTF = new JRRtfExporter();
				exporterRTF.setParameter(JRExporterParameter.JASPER_PRINT,
						jasperPrint);
				exporterRTF.setParameter(JRExporterParameter.OUTPUT_STREAM,
						retorno);
				exporterRTF.exportReport();

				break;
			case TIPO_XLS:

				JRXlsExporter exporterXLS = new JRXlsExporter();
				exporterXLS.setParameter(JRExporterParameter.JASPER_PRINT,
						jasperPrint);
				exporterXLS.setParameter(JRExporterParameter.OUTPUT_STREAM,
						retorno);
				exporterXLS.setParameter(
						JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
						Boolean.TRUE);
				exporterXLS.setParameter(JRXlsExporterParameter.IS_AUTO_DETECT_CELL_TYPE, Boolean.TRUE);
				exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
				exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
				 
				
//				exporterXLS.setParameter(JRXlsExporterParameter.IS_AUTO_DETECT_CELL_TYPE, false);
//				exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, true);


				exporterXLS.exportReport();

				break;
			case TIPO_HTML:

				// Para evitar problemas de concorr�ncia, o nome do arquivo html
				// gerado possui um n�mero aleat�rio no nome
				int numeroNomeRelatorio = new Double((Math.random() * 10000))
						.intValue();
				JasperExportManager.exportReportToHtmlFile(jasperPrint,
						"relatorio" + numeroNomeRelatorio + ".html");

				// pegar o arquivo, zipar pasta e arquivo e escrever no stream
				// criar o arquivo zip
				File arquivoZip = File.createTempFile("zipHtml"
						+ numeroNomeRelatorio, ".zip");

				ZipOutputStream zos = new ZipOutputStream(
						new FileOutputStream(arquivoZip));

				// Pega o arquivo gerado do relat�rio
				File pagina = new File("relatorio" + numeroNomeRelatorio
						+ ".html");
				// Pega a pasta que acompanha o arquivo do relat�rio gerado
				File pastaDeImagens = new File("relatorio"
						+ numeroNomeRelatorio + ".html_files");
				ZipUtil.adicionarArquivo(zos, pagina);
				ZipUtil.adicionarPasta(pastaDeImagens, zos);
				// close the stream
				zos.close();

				FileInputStream inputStream = new FileInputStream(
						arquivoZip);

				int INPUT_BUFFER_SIZE = 1024;
				byte[] temp = new byte[INPUT_BUFFER_SIZE];
				int numBytesRead = 0;

				while ((numBytesRead = inputStream.read(temp, 0,
						INPUT_BUFFER_SIZE)) != -1) {
					retorno.write(temp, 0, numBytesRead);
				}

				inputStream.close();
				inputStream = null;

				// Apaga todo o conte�do gerado
				pagina.delete();
				IoUtil.deleteDir(pastaDeImagens);
				arquivoZip.delete();

				break;
			default:
				throw new RelatorioVazioException(
						"Escolha um tipo de relat�rio");

			}

			retornoArray = retorno.toByteArray();

		} catch (JRException ex) {
			// erro ao cria o relat�rio
			ex.printStackTrace();
			throw new SistemaException(ex, "Erro ao criar relat�rio");
		} catch (IOException e) {
			e.printStackTrace();
			throw new SistemaException(e, "Erro ao criar relat�rio");
		}finally{
			if (stream != null) {
				try {
					stream.close();					
				} catch (IOException e) {
					e.printStackTrace();
					throw new SistemaException(e, "Erro ao fechar relat�rio");
				}
			}
		}

		return retornoArray;
	}

	public TarefaRelatorio(Usuario usuario, String nomeRelatorio) {

		super(usuario, ConstantesSistema.NUMERO_NAO_INFORMADO);
		this.nomeRelatorio = nomeRelatorio;
	}

	public abstract int calcularTotalRegistrosRelatorio();

	public String getNomeRelatorio() {
		return nomeRelatorio;
	}

	public void persistirRelatorioConcluido(byte[] relatorioConcluido,
			int idRelatorio, int idFuncionalidadeIniciada)
			throws ControladorException {

		if (idFuncionalidadeIniciada != 0
				&& idFuncionalidadeIniciada != ConstantesSistema.NUMERO_NAO_INFORMADO) {
			RelatorioGerado relatorioGerado = new RelatorioGerado();
			relatorioGerado.setArquivoRelatorio(relatorioConcluido);
			relatorioGerado.setNumeroPaginas(0);
			relatorioGerado.setUltimaAlteracao(new Date());

			Relatorio relatorio = new Relatorio();
			relatorio.setId(idRelatorio);

			relatorioGerado.setRelatorio(relatorio);

			FuncionalidadeIniciada funcionalidadeIniciada = new FuncionalidadeIniciada();
			funcionalidadeIniciada.setId(idFuncionalidadeIniciada);
			relatorioGerado.setFuncionalidadeIniciada(funcionalidadeIniciada);

			getControladorUtil().inserir(relatorioGerado);
		}

	}

	public final void execute(JobExecutionContext arg0)
			throws JobExecutionException {

		Integer idFuncionalidadeIniciada = (Integer) arg0.getJobDetail()
				.getJobDataMap().get("idFuncionalidadeIniciada");

		try {

			// ------------------------------------
			// ------------------------------------
			// Inicia a Funcionalidade Iniciada para execu��o do relat�rio
			// ------------------------------------
			// ------------------------------------
			if (idFuncionalidadeIniciada != null) {
				getControladorBatch().iniciarFuncionalidadeIniciadaRelatorio(
						idFuncionalidadeIniciada);
			}

			this.setIdFuncionalidadeIniciada(idFuncionalidadeIniciada);
			this.setUsuario((Usuario) arg0.getJobDetail().getJobDataMap().get(
					"usuario"));
			this.setParametroTarefa((Set) arg0.getJobDetail().getJobDataMap()
					.get("parametroTarefa"));
			this.executar();

			// ------------------------------------
			// ------------------------------------
			// Encerra a Funcionalidade Iniciada
			// ------------------------------------
			// ------------------------------------
			if (idFuncionalidadeIniciada != null) {
				getControladorBatch().encerrarFuncionalidadeIniciadaRelatorio(
						idFuncionalidadeIniciada, false);

			}

		} catch (Exception e) {
			e.printStackTrace();
			// ------------------------------------
			// ------------------------------------
			// Encerra a Funcionalidade Iniciada indicando erro
			// ------------------------------------
			// ------------------------------------
			if (idFuncionalidadeIniciada != null) {
				try {
					getControladorBatch()
							.encerrarFuncionalidadeIniciadaRelatorio(
									idFuncionalidadeIniciada, true);
				} catch (ControladorException e1) {

					e1.printStackTrace();
				}
			}

		}

	}

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
	 * M�todo que gera relat�rios em TXT e CSV
	 * 
	 * @author Carlos Chaves
	 * @date 05/01/2013
	 * 
	 * @param String relatorio
	 * @param tipoSaidaRelatorio
	 * @return Um map com o MIME type e a representa��o bin�ria do relat�rio
	 * @throws RelatorioVazioException
	 */
	public byte[] gerarRelatorio(StringBuilder relatorio, int tipoSaidaRelatorio)
			throws RelatorioVazioException {

		// valor de retorno
		ByteArrayOutputStream retorno = new ByteArrayOutputStream();
		byte[] retornoArray = null;
		InputStream stream = null;
        BufferedWriter out = null;
        File relatorioFile = null;
        FileInputStream inputStream;
        int INPUT_BUFFER_SIZE = 1024;
        byte[] temp = new byte[INPUT_BUFFER_SIZE];
        int numBytesRead = 0;
        
		try {

			// Verifica qual o tipo de relat�rio para definir a gera��o
			switch (tipoSaidaRelatorio) {
		
			case TIPO_TXT:
				relatorioFile = new File("relatorio.txt");
	            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(relatorioFile.getAbsolutePath())));
	            out.write(relatorio.toString());
	            out.flush();
	       
	            inputStream = new FileInputStream(relatorioFile);

	            // Escrevemos aos poucos
	            while ((numBytesRead = inputStream.read(temp, 0, INPUT_BUFFER_SIZE)) != -1) {
	                retorno.write(temp, 0, numBytesRead);
	            }

	            // Fechamos o inputStream
	            out.close();
	            inputStream.close();
	            retorno.close();

	            inputStream = null;
	            relatorioFile.delete();
			
				break;
				
			case TIPO_CSV:
				relatorioFile = new File("relatorio.csv");

	            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(relatorioFile.getAbsolutePath())));
	            out.write(relatorio.toString());
	            out.flush();
	            inputStream = new FileInputStream(relatorioFile);

	            // Escrevemos aos poucos
	            while ((numBytesRead = inputStream.read(temp, 0, INPUT_BUFFER_SIZE)) != -1) {
	                retorno.write(temp, 0, numBytesRead);
	            }

	            // Fechamos o inputStream
	            out.close();
	            inputStream.close();
	            retorno.close();
	            
	            inputStream = null;
	            relatorioFile.delete();
	            
				break;
			default:
				throw new RelatorioVazioException(
						"Escolha um tipo de relat�rio");

			}

			retornoArray = retorno.toByteArray();

		}catch (IOException e) {
			e.printStackTrace();
			throw new SistemaException(e, "Erro ao criar relat�rio");
		}finally{
			if (stream != null) {
				try {
					stream.close();					
				} catch (IOException e) {
					e.printStackTrace();
					throw new SistemaException(e, "Erro ao fechar relat�rio");
				}
			}
		}

		return retornoArray;
	}

}