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
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.financeiro.bean.GerarIntegracaoContabilidadeHelper;
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
import gcom.util.filtro.ParametroSimples;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.zip.ZipOutputStream;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;

/**
 * Controlador Financeiro Juazeiro
 *
 * @author Rafael Corr�a
 * @date 30/06/2009
 */
public class ControladorFinanceiroCOSAMASEJB extends ControladorFinanceiro implements SessionBean {
	
	private static final long serialVersionUID = 1L;
	
	//==============================================================================================================
	// M�TODOS EXCLUSIVOS DA COSAMA
	//==============================================================================================================
	
	/**
	 * Retorna o controladorCadastro
	 * 
	 * @author Tiago Moreno
	 * @date 28/06/2011
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
	
	/**
	 * este caso de uso gera a integra��o para a contabilidade
	 *
	 * [UC0469] Gerar Integra��o para a Contabilidade
	 *
	 * @author Tiago Moreno
	 * @date 28/06/2011
	 *
	 * @param idLancamentoOrigem
	 * @param anoMes
	 * @param data
	 * @throws ControladorException
	 */
	
	public void gerarIntegracaoContabilidade(String idLancamentoOrigem, String anoMes, String data) throws ControladorException{
		
		/*
		 * Gera o arquivo texto de integra��o com a contabilidade. LAYOUT:
		 * 
		 * 1 - Centro de Custo - Num 2
		 * 2 - Localidade - Num 3
		 * 3 - Conta - Num 6
		 * 4 - Centro de Custo - Char 3 - - Preencher com " "
		 * 5 - C�digo da An�lise - Char 3 - Preencher com " "
		 * 6 - C�digo de Terceiros - Char 3 - Preencher com " "
		 * 7 - Referencia - Char 8 - Preencher com " "
		 * 8 - Digito - Char 1 - Preencher com " "
		 * 9 - Valor do Lancamento - Num 15 
		 * 10 - Indicador de Debito ou Cr�dito - Char 1
		 * 11 - Data de Lancamento - Char 8 - DDMMAAAA
		 * 12 - C�digo do Lancamento - Char 3 - Caso LCOR = FATURAMENTO = 33
		 * 13 - Mes/Ano do Lancamento - Char 6 - MMAAAA
		 * 14 - N�mero da P�gina - Char 3 - Fixo 001
		 */
		 

		Collection colecaoDadosGerarIntegracao = this.pesquisarGerarIntegracaoContabilidade(idLancamentoOrigem, anoMes);
		
		
		/** defini��o das vari�veis */
		StringBuilder gerarIntegracaoTxt = new StringBuilder();
		
		String dataFormatada = data.replace("/","");
		String diaMes = dataFormatada.substring(0,4);
		String ano = dataFormatada.substring(4 ,8);
		dataFormatada = diaMes + ano;
		
		/*
		 * Caso a cole��o dos dados n�o esteja vazia
		 */
		if(colecaoDadosGerarIntegracao != null && !colecaoDadosGerarIntegracao.isEmpty()){
			
			
			/*
			 * Determina se o arquivo � de faturamento ou arrecada��o 
			 * para concatenar no nome do arquivo .zip
			 */
			
			//Determinando o tipo de lancamento e setando o c�digo de lancamento para o Campo 11 - Faturamento = 033 e Arrecadacao = 007
			
			String codigoLancamento = "";
			String descricaoLancamento = "";
			if(idLancamentoOrigem.equals(LancamentoOrigem.FATURAMENTO + "")){
				codigoLancamento = "033";
				descricaoLancamento = "FATURAMENTO";
			}else if(idLancamentoOrigem.equals(LancamentoOrigem.ARRECADACAO + "")){
				codigoLancamento = "007";
				descricaoLancamento = "ARRECADACAO";
			}
			
			/*
			 * La�o para gerar o txt 
			 */
			Iterator iterator = colecaoDadosGerarIntegracao.iterator();
			while (iterator.hasNext()){
				GerarIntegracaoContabilidadeHelper gerarIntegracaoContabilidadeHelper = (GerarIntegracaoContabilidadeHelper)iterator.next();
				
				// Campo 1 - Ger�ncia Regional a partir da localidade do lancamento
				String localidadeLancamento = "";
				
				/*
				 * Para uso da localidade, usar o prefixo contabil de Conta contabil do Lancamento Contabil Item.
				 * Caso ele seja nulo, usar a localidade do Lancamento Cont�bil
				 */
				String prefixoContabil = gerarIntegracaoContabilidadeHelper.getNumeroPrefixoContabil();
				
				if (prefixoContabil != null && !prefixoContabil.equalsIgnoreCase("")) {
					localidadeLancamento = prefixoContabil;
					
					gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumeroTruncando(2, prefixoContabil+""));
				} else {
					localidadeLancamento =  gerarIntegracaoContabilidadeHelper.getIdLocalidade()+"";
					//Recupernaod a gerencia comerciail a partir da localidade.
					FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
					filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, localidadeLancamento));
					filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
					
					Collection colLocalidades = getControladorUtil().pesquisar(filtroLocalidade, Localidade.class.getName());
					
					Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colLocalidades);
					
					if ( localidade.getCodigoCentroCusto() != null ){
						gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumero(2, localidade.getCodigoCentroCusto().substring(0, 2)+""));
					} else {
						gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumero(2, localidade.getGerenciaRegional().getId().toString().substring(0, 2)+""));
					}
				}
				
				// Campo 2 - Localidade
				gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumero(3, localidadeLancamento));
				
				//Campo 3 - Numero da conta
				//Caso o indicador de debito credito seja = 1 pegar conta tipo debito, sen�o pegar conta tipo credito.
				//Aproveitando para setar os valores do Campo 10 - Indicador de Debito ou Cr�dito (D = Debito, C = Credito)
				String indicadorDebitoCredito = " ";
				if (gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta().equals(new Integer("2"))){
					gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumero(5, gerarIntegracaoContabilidadeHelper.getNumeroContaDebito()));
					indicadorDebitoCredito = "D";
				} else {
					gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumero(5, gerarIntegracaoContabilidadeHelper.getNumeroContaCredito()));
					indicadorDebitoCredito = "C";
				}
				
				// Camp 4 - Centro de Custo - N�o Preencher
				gerarIntegracaoTxt.append(Util.completaString(" ", 3));
				
				// Campo 5 - C�digo da An�lise - N�o Preencher
				gerarIntegracaoTxt.append(Util.completaString(" ", 3));
				
				// Campo 6 - C�digo de Terceiros - N�o Preencher
				gerarIntegracaoTxt.append(Util.completaString(" ", 15));
				
				// Campo 7 - Referencia - N�o Preencher
				gerarIntegracaoTxt.append(Util.completaString(" ", 8));
				
				// Campo 8 - Digito - N�o Preencher
				gerarIntegracaoTxt.append(Util.completaString(" ", 1));
				
				// Campo 9 - Valor do lancamento (sem pontos, sem virgulas)
				gerarIntegracaoTxt.append(
						Util.adicionarZerosEsquedaNumero(15, 
								gerarIntegracaoContabilidadeHelper.getValorLancamento().toString().replace(".", "")));
				
				// Campo 10 - Indicador de Debito ou Cr�dito (D = Debito, C = Credito)
				gerarIntegracaoTxt.append(Util.completaString(indicadorDebitoCredito, 1));
				
				// Campo 11 - Data do lancamento (DDMMAAAA)
				gerarIntegracaoTxt.append(Util.completaString(dataFormatada, 8));
				
				// Campo 12 - C�digo do Lancamento - FATURAMENTO = 033 e ARRECADACAO = 007
				gerarIntegracaoTxt.append(Util.completaString(codigoLancamento, 3));
				
				// Campo 13 - M�s e Ano do Lancamento (MMAAAA)
				String mesAno = Util.formatarAnoMesParaMesAnoSemBarra(new Integer(anoMes).intValue());
				gerarIntegracaoTxt.append(Util.completaString(mesAno, 6));
				
				// Campo 14 - N�mero da P�gina - Fixo em "001"
				gerarIntegracaoTxt.append(Util.completaString("001", 3));
				
				// Quebra da linha
				gerarIntegracaoTxt.append(System.getProperty("line.separator"));
			}
			
			
			/*
			 * Gerando o arquivo zip
			 */
			String nomeZip = "CONTABILIDADE_" + descricaoLancamento + "_" + (data.replace("/","_"));
			BufferedWriter out = null;
			ZipOutputStream zos = null;
			File compactadoTipo = new File(nomeZip + ".zip");
			File leituraTipo = new File(nomeZip + ".txt");

			/*
			 * Caso oarquivo txt n�o esteja vazio 
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
					throw new ControladorException("erro.sistema", ex);
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
			//caso n�o exista informa��o para os dados informados
		}else{
			if(idLancamentoOrigem.equals(LancamentoOrigem.FATURAMENTO + "")){
				throw new ControladorException("atencao.pesquisa.nenhum_registro_tabela", null,"Resumo Faturamento");
			}else if(idLancamentoOrigem.equals(LancamentoOrigem.ARRECADACAO + "")){
				throw new ControladorException("atencao.pesquisa.nenhum_registro_tabela", null,"Resumo Faturamento");
			}
		}
	}
	
	/**
	 * Este metodo � utilizado para pesquisar os registros q ser�o
	 * usados para contru��o do txt do caso de uso 469
	 *
	 * [UC0469] Gerar Integra��o para a Contabilidade
	 *
	 * @author Tiago Moreno
	 * @date 28/06/2011
	 *
	 * @param idLancamentoOrigem
	 * @param anoMes
	 * @return
	 * @throws ControladorException
	 */
	public Collection pesquisarGerarIntegracaoContabilidade(String idLancamentoOrigem, String anoMes) 
		throws ControladorException{
		
		Collection colecaoObjetoGerar = null;
		Collection colecaoGerarIntegracaoContabilidade = null;
		
		try {

			colecaoObjetoGerar = repositorioFinanceiro.pesquisarGerarIntegracaoContabilidadeCosama(idLancamentoOrigem, anoMes);
			
			if(!colecaoObjetoGerar.isEmpty()){
				Iterator iteratorPesquisa = colecaoObjetoGerar.iterator();
				
				colecaoGerarIntegracaoContabilidade = new ArrayList();
				GerarIntegracaoContabilidadeHelper gerarIntegracaoContabilidadeHelper = null;
				Object[] objetoGerar = null;
				
				while(iteratorPesquisa.hasNext()){
					gerarIntegracaoContabilidadeHelper = new GerarIntegracaoContabilidadeHelper();
					
					objetoGerar = (Object[]) iteratorPesquisa.next();
					
					//indicador debito credito
					if(objetoGerar[10] != null){
						gerarIntegracaoContabilidadeHelper.setIndicadorDebitoConta(new Integer((Short) objetoGerar[10]));
					}
					
					//numero cartao
					if (gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta() != null
							&& gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta().equals(1)){
						gerarIntegracaoContabilidadeHelper.setNumeroCartao(new Integer((Short) objetoGerar[0]).shortValue());
					} else if (gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta() != null
							&& gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta().equals(2)){
						gerarIntegracaoContabilidadeHelper.setNumeroCartao(new Integer((Short) objetoGerar[0]).shortValue());
					} else if (gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta() != null
							&& gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta().equals(3)) {
						gerarIntegracaoContabilidadeHelper.setNumeroCartao(new Integer((Short) objetoGerar[0]).shortValue());
					}
					
					//lancamento tipo
					if(objetoGerar[1] != null){
						gerarIntegracaoContabilidadeHelper.setIdTipoLancamento(new Integer((Short) objetoGerar[1]));
					}
					
					//folha
					if(objetoGerar[2] != null){
						gerarIntegracaoContabilidadeHelper.setFolha(new Integer((Short) objetoGerar[2]));
					}
					
					//linha
					if(objetoGerar[3] != null){
						gerarIntegracaoContabilidadeHelper.setIndicadorLinha(new Integer((Short)objetoGerar[3]));
					}
					
					//prefixo contabil
					if(objetoGerar[4] != null){
						gerarIntegracaoContabilidadeHelper.setNumeroPrefixoContabil((String) objetoGerar[4]);
					}
					
					// numero da conta
					if(objetoGerar[5] != null && gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta().equals(1)){
						String numero = ((String) objetoGerar[5]).trim();
						gerarIntegracaoContabilidadeHelper.setNumeroContaCredito(numero);
						gerarIntegracaoContabilidadeHelper.setNumeroContaDebito("0");
					}else {
						String numero = ((String) objetoGerar[5]).trim();
						gerarIntegracaoContabilidadeHelper.setNumeroContaDebito(numero);
						gerarIntegracaoContabilidadeHelper.setNumeroContaCredito("0");
					}
					
					//digito
					if(objetoGerar[6] != null){
						gerarIntegracaoContabilidadeHelper.setDigito(new Integer(((String) objetoGerar[6]).trim()));
					}
					
					//terceiros
					if(objetoGerar[7] != null){
						gerarIntegracaoContabilidadeHelper.setTerceiros(new Integer(((String) objetoGerar[7]).trim()));
					}
					
					//referencia
					if(objetoGerar[8] != null){
						gerarIntegracaoContabilidadeHelper.setReferencial(new Integer(((String) objetoGerar[8]).trim()));
					}
					
					//valor lancamento
					if(objetoGerar[9] != null){
						gerarIntegracaoContabilidadeHelper.setValorLancamento((BigDecimal)objetoGerar[9]);
					}
					
					//Cartao2
					if(objetoGerar[11] != null){
						gerarIntegracaoContabilidadeHelper.setCartao2(new Integer((Short) objetoGerar[11]));
					}
					
					//Versao
					if(objetoGerar[12] != null){
						gerarIntegracaoContabilidadeHelper.setVersao(new Integer((Short) objetoGerar[12]));
					}
					
					//id localidade
					if(objetoGerar[13] != null){
						gerarIntegracaoContabilidadeHelper.setIdLocalidade((Integer)objetoGerar[13]);
					}
					
					//codigo centro custo
					if(objetoGerar[14] != null && gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta() != null
							&& gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta().equals(1)){
						gerarIntegracaoContabilidadeHelper.setCodigoCentroCustoCredito(new Integer(((String) objetoGerar[14]).trim()));
						gerarIntegracaoContabilidadeHelper.setCodigoCentroCustoDebito(new Integer(0));
					}else if(objetoGerar[14] != null && gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta() != null
							&& gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta().equals(2)){
						gerarIntegracaoContabilidadeHelper.setCodigoCentroCustoDebito(new Integer(((String) objetoGerar[14]).trim()));
						gerarIntegracaoContabilidadeHelper.setCodigoCentroCustoCredito(new Integer(0));
					}
					
					colecaoGerarIntegracaoContabilidade.add(gerarIntegracaoContabilidadeHelper);
				
					//Indicador Centro de Custo
					if ( objetoGerar[15] != null && (((Short)objetoGerar[15]).intValue() == 1) ){
						gerarIntegracaoContabilidadeHelper.setIndicadorCentroCusto( "" + objetoGerar[15] );
					}
					
					//Numero Hitorico Debito
					if ( objetoGerar[16] != null && (((Short) objetoGerar[10]).intValue() == 1 ) ) {
						gerarIntegracaoContabilidadeHelper.setNumeroHistoricoCreditoOuCredito("" + objetoGerar[16] );
					}
					
					//Numero Hitorico Credito
					if ( objetoGerar[17] != null && (((Short) objetoGerar[10]).intValue() == 2 ) ) {
						gerarIntegracaoContabilidadeHelper.setNumeroHistoricoCreditoOuCredito("" + objetoGerar[17] );
					}
				}
				
			}
			
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
		return colecaoGerarIntegracaoContabilidade;
	}
	
	
}
