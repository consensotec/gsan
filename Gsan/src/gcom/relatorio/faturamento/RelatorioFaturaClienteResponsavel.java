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
package gcom.relatorio.faturamento;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.FaturaClienteResponsavelHelper;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioPadraoBatch;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.ZipUtil;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * classe respons�vel por criar as faturas do cliente responsavel apartir do txt
 * 
 * @author Rafael Pinto
 * 
 * @created 16/08/2007
 */
public class RelatorioFaturaClienteResponsavel extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioFaturaClienteResponsavel(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_FATURA_CLIENTE_RESPONSAVEL);
	}

	@Deprecated
	public RelatorioFaturaClienteResponsavel() {
		super(null, "");
	}

	/**
	 * < <Descri��o do m�todo>>
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
		
		System.out.println("**********************************************");
		System.out.println("ENTROU NO EXECUTAR RELAT. DE FATURA CLI. RESP.");
		System.out.println("**********************************************");

		// valor de retorno
		byte[] retorno = null;

		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		String nomeArquivo = "RELATORIO_"+((String) getParametro("nomeArquivo"));
		String nomeArquvioZip = nomeArquivo+".zip";
		
		
		RelatorioFaturaClienteResponsavelBean relatorioFaturaClienteResponsavelBean = null;
		
		final int quantidadeRegistros = 1000;

		List colecaoFaturaClienteResponsavelHelper = 
			(ArrayList) getParametro("colecaoFaturaClienteResponsavelHelper");
		
		if(colecaoFaturaClienteResponsavelHelper != null && !colecaoFaturaClienteResponsavelHelper.isEmpty()){
			
			ZipOutputStream zos = null;
			File compactadoTipo = new File(nomeArquvioZip);
			
			try {
				zos = new ZipOutputStream(new FileOutputStream(compactadoTipo));
			} catch (IOException e) {
				e.printStackTrace();
				throw new TarefaException("Erro ao gerar o arquivo zip", e);
			}

			Fachada fachada = Fachada.getInstancia();
			SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

			boolean flagTerminou = false;
			
			int totalFaturas = colecaoFaturaClienteResponsavelHelper.size();
			int quantidadeFaturas = 0;
			int parte = 1;

			while (!flagTerminou) {

				// cole��o de beans do relat�rio
				List relatorioBeans = new ArrayList();
				
				FaturaClienteResponsavelHelper faturaClienteResponsavelHelperPrimeiraFatura = null;
				FaturaClienteResponsavelHelper faturaClienteResponsavelHelperSegundaFatura = null;
				
				for (int i = 0; i < quantidadeRegistros; i++) {
					
					int index = quantidadeFaturas;
					
					FaturaClienteResponsavelHelper faturaClienteResponsavelHelper = 
						(FaturaClienteResponsavelHelper) colecaoFaturaClienteResponsavelHelper.get(index);
					
					if(faturaClienteResponsavelHelperPrimeiraFatura == null){
						faturaClienteResponsavelHelperPrimeiraFatura = faturaClienteResponsavelHelper;
					}else{
						faturaClienteResponsavelHelperSegundaFatura = faturaClienteResponsavelHelper;
					}
					
					if(faturaClienteResponsavelHelperPrimeiraFatura != null && faturaClienteResponsavelHelperSegundaFatura != null){
						
						relatorioFaturaClienteResponsavelBean = new RelatorioFaturaClienteResponsavelBean(
								faturaClienteResponsavelHelperPrimeiraFatura,
								faturaClienteResponsavelHelperSegundaFatura,
								""+quantidadeFaturas);
						
						relatorioBeans.add(relatorioFaturaClienteResponsavelBean);
						
						faturaClienteResponsavelHelperPrimeiraFatura = null;
						faturaClienteResponsavelHelperSegundaFatura = null;
					}
					
					quantidadeFaturas++;
					
					if(quantidadeFaturas == totalFaturas){
						break;
					}
					
				}
				
				//Caso tenha sobrado apenas uma fatura
				if(faturaClienteResponsavelHelperPrimeiraFatura != null){
					
					faturaClienteResponsavelHelperSegundaFatura = new FaturaClienteResponsavelHelper();
					faturaClienteResponsavelHelperSegundaFatura.setNumeroFatura(null);
					
					relatorioFaturaClienteResponsavelBean = 
						new RelatorioFaturaClienteResponsavelBean(
							faturaClienteResponsavelHelperPrimeiraFatura,
							faturaClienteResponsavelHelperSegundaFatura,
							""+quantidadeFaturas);
					
					relatorioBeans.add(relatorioFaturaClienteResponsavelBean);
				}


				if(quantidadeFaturas == totalFaturas){
					flagTerminou = true;
				}
				
				// Par�metros do relat�rio
				Map parametros = new HashMap();
				
				// adiciona os par�metros do relat�rio
				// adiciona o laudo da an�lise
				
				parametros.put("imagem", sistemaParametro.getImagemRelatorio());

				// cria uma inst�ncia do dataSource do relat�rio
				RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

				System.out.println("***************************************************");
				System.out.println("GERA REL. FATURA CLI. RESP. (Parte "+parte+") - PDF");
				System.out.println("***************************************************");

				retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_FATURA_CLIENTE_RESPONSAVEL,
						parametros, ds, tipoFormatoRelatorio);

				// ------------------------------------
				// Grava o relat�rio no sistema
				try {

					System.out.println("********************************************************");
					System.out.println("COLOCA NO ZIP O REL. FATURA CLI. RESP. (Parte "+parte+")");
					System.out.println("********************************************************");

					File leitura = new File(nomeArquivo+"Parte-"+parte+".pdf");
					
					FileOutputStream out = new FileOutputStream(leitura.getAbsolutePath());
					
					out.write(retorno);
					out.close();

					parte++;

					ZipUtil.adicionarArquivo(zos, leitura);
					
					leitura.delete();
					
				} catch (IOException e) {
					e.printStackTrace();
					throw new TarefaException("Erro ao gravar relat�rio no diretorio", e);
				}
			
			}
			try {
				System.out.println("***********************************");
				System.out.println("FINALIZA O ZIP REL. FAT. CLI. RESP.");
				System.out.println("***********************************");

				zos.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new TarefaException("Erro ao fechar o zip do relatorio", e);
			}
		}
		
		// ------------------------------------
		// Grava o relat�rio no sistema
		try {

			RelatorioPadraoBatch relatorio = 
				new RelatorioPadraoBatch(Usuario.USUARIO_BATCH);

			relatorio.addParametro("titulo","RELAT�RIO DE FATURAS CLIENTE RESPONS�VEL");
			relatorio.addParametro("tipoFormatoRelatorio", TarefaRelatorio.TIPO_PDF);
			relatorio.addParametro("nomeArquivo", nomeArquvioZip);
			
			byte[] relatorioGerado = (byte[]) relatorio.executar();
			
			persistirRelatorioConcluido(relatorioGerado, Relatorio.FATURA_CLIENTE_RESPONSAVEL,
					idFuncionalidadeIniciada);
			
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		}
			
		// retorna o relat�rio gerado
		return retorno;
	}
	


	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioFaturaClienteResponsavel", this);

	}

}