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
package gcom.financeiro;

import gcom.cadastro.ControladorCadastroLocal;
import gcom.cadastro.ControladorCadastroLocalHome;
import gcom.cadastro.EnvioEmail;
import gcom.cadastro.sistemaparametro.NacionalFeriado;
import gcom.financeiro.lancamento.LancamentoOrigem;
import gcom.util.ConstantesJNDI;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.ZipUtil;
import gcom.util.email.ServicosEmail;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.zip.ZipOutputStream;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;


/**
 * Controlador Financeiro Juazeiro
 *
 * @author Rafael Corr�a
 * @date 30/06/2009
 */
public class ControladorFinanceiroJUAZEIROSEJB extends ControladorFinanceiro implements SessionBean {
	
	private static final long serialVersionUID = 1L;
	
	//==============================================================================================================
	// M�TODOS EXCLUSIVOS DE JUAZEIRO
	//==============================================================================================================
	
	/**
	 * Este caso de uso gera a integra��o para a contabilidade
	 *
	 * [UC0469] Gerar Integra��o para a Contabilidade
	 *
	 * @author Erivan Sousa
	 * @date 09/11/2011
	 *
	 * @param idLancamentoOrigem
	 * @param dtInicioIntervalo
	 * @param dtFimIntervalo
	 * @throws ControladorException
	 */
	public void gerarIntegracaoContabilidade(String idLancamentoOrigem, String anoMesInicial, String anoMesFinal,
			String dataLancamentoInicial, String dataLancamentoFinal, String numeroUltimoSequencial ) throws ControladorException{
		
		Collection<Object[]> colecaoDadosGerarIntegracao = null;
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		String dtInicioIntervalo = dataLancamentoInicial;
		
		String dtFimIntervalo = dataLancamentoFinal;
		
		// Validamos o se o sequencial ser� alterado
		if ( !Util.validarStringNumerica( numeroUltimoSequencial ) ){
			throw new ControladorException( "atencao.campo_texto.numero_obrigatorio", null, "�ltimo Sequencial" );
		}				
		
		try {
			
			colecaoDadosGerarIntegracao = repositorioFinanceiro.pesquisarGerarIntegracaoContabilidadeJUAZEIRO(
			idLancamentoOrigem, dtInicioIntervalo, dtFimIntervalo);
		} 
		catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
		/** defini��o das vari�veis */
		StringBuilder gerarIntegracaoTxt = new StringBuilder();
		
		/*
		 * Caso a cole��o dos dados n�o esteja vazia
		 */
		if(colecaoDadosGerarIntegracao != null && !colecaoDadosGerarIntegracao.isEmpty()){
			
			/** defini��o das vari�veis */
			int sequencialLancamento = Integer.parseInt( numeroUltimoSequencial );
			
			String tipoRegistro = "";
			String dataPagamento = "";
			BigDecimal valorLancamento = new BigDecimal("0.00");
			String codigoFichaReceita = "";
			String codigoEventoContabil = "";
			String codigoAgente = "";
			String reservado = "000000000000";
			String tipoLancamento = "I";
			String historico = "CONFORME DBA";
		
			
			/*
			 * Conte�do deste campo 
			 */
			tipoRegistro = "2";
			
			SimpleDateFormat formater = new SimpleDateFormat("ddMMyyyy");
			
			boolean icValorNegativo;
			/*
			 * La�o para gerar o txt 
			 */
			
			Collection<NacionalFeriado> feriadosNacionais = getControladorUtil().pesquisarFeriadosNacionais();
			
			for(Object[] dadosGerarIntegracao : colecaoDadosGerarIntegracao){
				
				icValorNegativo = false;
				
				//DATA DO PAGAMENTO
				if (dadosGerarIntegracao[0] != null){
					Date dataUtil = (Date) dadosGerarIntegracao[0];
					
					while (!Util.ehDiaUtil(dataUtil, feriadosNacionais, null)) {
						dataUtil = Util.adicionarNumeroDiasDeUmaData(dataUtil, 1);
					}
					
					dataPagamento = (formater.format(dataUtil)).trim();
				}
				else{
					dataPagamento = "";
				}
				
				//VALOR DO LANCAMENTO
				if (dadosGerarIntegracao[1] != null){
					valorLancamento = (BigDecimal) dadosGerarIntegracao[1];
					
					//Caso o valor do lan��mento seja negativo, torna o valor positivo e muda o tipo de lan�amento
					if(valorLancamento.compareTo(new BigDecimal("0")) < 0){
						valorLancamento = valorLancamento.multiply(new BigDecimal("-1"));
						icValorNegativo = true;
					}
				}
								
				//CODIGO DA FICHA DA RECEITA
				if (dadosGerarIntegracao[2] != null){
					codigoFichaReceita = ((String) dadosGerarIntegracao[2]).trim();
				}else{
					codigoFichaReceita = "";
				}
				
				//CODIGO DO EVENTO CONTABIL
				if (dadosGerarIntegracao[3] != null){
					codigoEventoContabil = ((String) dadosGerarIntegracao[3]).trim();
				}else{
					codigoEventoContabil = "";
				}
				
				//CODIGO DO AGENTE/BANCO
				if (dadosGerarIntegracao[4] != null){
					codigoAgente = ((String) dadosGerarIntegracao[4]).trim();
				}else{
					codigoAgente = "";
				}				
				
				//1 - TIPO REGISTRO
				gerarIntegracaoTxt.append(tipoRegistro);
				
				//2 - SEQUENCIAL DA ARRECADA��O
				gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumero(6, Integer.toString(sequencialLancamento)));
				
				//3 - DATA DO PAGAMENTO
				gerarIntegracaoTxt.append(dataPagamento);
				
				//4 - VALOR DO PAGAMENTO
				gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumero(15, valorLancamento.toString()));
				
				//5 - Caso o valor do pagamento seja positivo C�DIGO DA FICHA RECEITA, caso contr�rio CODIGO AGENTE/BANCO.
				if(!icValorNegativo){
					gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumero(12, codigoFichaReceita));
				}else{
					gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumero(12, codigoAgente));
				}
				
				//6 - CODIGO DO EVENTO CONTABIL
				gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumero(12, codigoEventoContabil));
				
				//7 - Caso o valor do pagamento seja positivo CODIGO AGENTE/BANCO, caso contr�rio C�DIGO DA FICHA RECEITA.
				if(!icValorNegativo){
					gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumero(12, codigoAgente));
				}else{
					gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumero(12, codigoFichaReceita));
				}
				
				//8 - RESERVADO
				gerarIntegracaoTxt.append(reservado);
				
				//9 - TIPO DE LANCAMENTO
				gerarIntegracaoTxt.append(tipoLancamento);
				
				//10 - HISTORICO
				gerarIntegracaoTxt.append(historico);
				
				gerarIntegracaoTxt.append(System.getProperty("line.separator"));
				
				//Iniciar com o valor 1 e incrementar com 1 a cada registro gerado.
				sequencialLancamento++;
				
			}
			
			try {
				// Alteramos os sequencial
				repositorioFinanceiro.atualizarUltimoSequencialLancamentoContabil( Integer.parseInt( idLancamentoOrigem ), --sequencialLancamento );
			} catch (NumberFormatException e) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", e);
			} catch (ErroRepositorioException e) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", e);
			}	
			
			
			/*
			 * Gerando o arquivo zip
			 */
			String nomeZip = "CONTABILIDADE_" + tipoLancamento + "_" + (dtInicioIntervalo.replace("/","_")+"_a_"+(dtFimIntervalo.replace("/", "_")));
			BufferedWriter out = null;
			ZipOutputStream zos = null;
			File compactadoTipo = new File(nomeZip + ".zip");
			File leituraTipo = new File(nomeZip + ".txt");

			/*
			 * Caso o arquivo txt n�o esteja vazio 
			 * adiciona o txt ao arquivo zip.
			 */
			if (gerarIntegracaoTxt != null && gerarIntegracaoTxt.length() != 0) {
				
				try {
					System.out.println("CRIANDO ZIP");
					zos = new ZipOutputStream(new FileOutputStream(compactadoTipo));

					out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(leituraTipo.getAbsolutePath())));
					out.write(gerarIntegracaoTxt.toString());
					out.flush();
					ZipUtil.adicionarArquivo(zos, leituraTipo);
					zos.close();
					leituraTipo.delete();
					out.close();
				} catch (IOException ex) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.sistema", ex);
				} catch (Exception e) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.sistema", e);
				}
				
				try {
					
					// Envia de Arquivo por email
					EnvioEmail envioEmail = this.getControladorCadastro()
						.pesquisarEnvioEmail(
							EnvioEmail.GERAR_INTEGRACAO_PARA_CONTABILIDADE);

					String emailRemetente = envioEmail.getEmailRemetente();
					String tituloMensagem = envioEmail.getTituloMensagem();
					String corpoMensagem = envioEmail.getCorpoMensagem();
					String emailReceptor = envioEmail.getEmailReceptor();
					
					ServicosEmail.enviarMensagemArquivoAnexado(
							emailReceptor,emailRemetente, tituloMensagem, corpoMensagem,
							compactadoTipo);
				} catch (Exception e) {
					System.out.println("Erro ao enviar email.");
				}
				
				try {
					
					// Envia de Arquivo por email
					EnvioEmail envioEmail = this.getControladorCadastro()
						.pesquisarEnvioEmail(
							EnvioEmail.GERAR_INTEGRACAO_PARA_CONTABILIDADE);

					String emailRemetente = envioEmail.getEmailRemetente();
					String tituloMensagem = envioEmail.getTituloMensagem();
					String corpoMensagem = envioEmail.getCorpoMensagem();
					String emailReceptor = envioEmail.getEmailReceptor();
					
					ServicosEmail.enviarMensagemArquivoAnexado(
							emailReceptor,emailRemetente, tituloMensagem, corpoMensagem,
							compactadoTipo);
				} catch (Exception e) {
					System.out.println("Erro ao enviar email.");
				}				
				
			}
			
		}else{			
			//Caso n�o exista informa��o para os dados informados
			
			if(idLancamentoOrigem.equals(LancamentoOrigem.FATURAMENTO + "")){
				throw new ControladorException("atencao.pesquisa.nenhum_registro_tabela", null,"Resumo Faturamento");
			}
			else if(idLancamentoOrigem.equals(LancamentoOrigem.ARRECADACAO + "")){
				throw new ControladorException("atencao.pesquisa.nenhum_registro_tabela", null,"Resumo Arrecada��o");
			}
		}
	}
	
	/**
	 * Retorna o controladorCadastro
	 * 
	 * @author Thiago Ten�rio
	 * @date 18/08/2006
	 * 
	 */
	private ControladorCadastroLocal getControladorCadastro() {
		ControladorCadastroLocalHome localHome = null;
		ControladorCadastroLocal local = null;

		ServiceLocator locator = null;
		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorCadastroLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_CADASTRO_SEJB);

			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

}
