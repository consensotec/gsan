/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.financeiro;

import gcom.cadastro.ControladorCadastroLocal;
import gcom.cadastro.ControladorCadastroLocalHome;
import gcom.cadastro.EnvioEmail;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.financeiro.bean.GerarIntegracaoContabilidadeHelper;
import gcom.financeiro.bean.GerarResumoDevedoresDuvidososHelper;
import gcom.financeiro.lancamento.LancamentoItem;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.financeiro.lancamento.LancamentoOrigem;
import gcom.financeiro.lancamento.LancamentoTipo;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipOutputStream;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;

/**
 * Controlador Financeiro CAERN
 *
 * @author Raphael Rossiter
 * @date 26/06/2006
 */
public class ControladorFinanceiroCAERNSEJB extends ControladorFinanceiro implements SessionBean{

	private static final long serialVersionUID = 1L;
	
	//==============================================================================================================
	// MÉTODOS EXCLUSIVOS DA CAERN
	//==============================================================================================================
	
	
	/**
	 * Retorna o controladorCadastro
	 * 
	 * @author Thiago Tenório
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
	
	/**
	 * este caso de uso gera a integração para a contabilidade
	 *
	 * [UC0469] Gerar Integração para a Contabilidade
	 *
	 * @author Pedro Alexandre
	 * @date 28/05/2007
	 *
	 * @param idLancamentoOrigem
	 * @param anoMes
	 * @param data
	 * @throws ControladorException
	 */
	public void gerarIntegracaoContabilidade(String idLancamentoOrigem, String anoMes, String data) throws ControladorException{
		
		/*
		 * Pesquisa os dados para gerar a integração para a contabilidade.
		 * 
		 * 0 - número do cartão
		 * 1 - código tipo
		 * 2 - número folha
		 * 3 - indicador linha
		 * 4 - prefixo contábil
		 * 5 - número conta
		 * 6 - número dígito
		 * 7 - número terceiros
		 * 8 - código referência
		 * 9 - valor lançamento
		 * 10 - indicador débito crédito
		 * 11 - número cartão 2
		 * 12 - número versão
		 * 13 - id da localidade
		 * 14 - código centro custo
		 * 
		 */
		Collection<Object[]> colecaoDadosGerarIntegracao = null;

		colecaoDadosGerarIntegracao = this.pesquisarGerarIntegracaoContabilidade(idLancamentoOrigem, anoMes);
		
		/** definição das variáveis */
		StringBuilder gerarIntegracaoTxt = new StringBuilder();
		String dataFormatada = data.replace("/","");
		
		/*
		 * Caso a coleção dos dados não esteja vazia
		 */
		if(colecaoDadosGerarIntegracao != null && !colecaoDadosGerarIntegracao.isEmpty()){
			
			/** definição das variáveis */
			
			Short numeroCartao = null;
			String creditoDebito = "";
			BigDecimal valorLancamento = null;
			
			/*
			 * Laço para gerar o txt 
			 */
			Iterator iterator = colecaoDadosGerarIntegracao.iterator();
			while (iterator.hasNext()){
				GerarIntegracaoContabilidadeHelper gerarIntegracaoContabilidadeHelper = (GerarIntegracaoContabilidadeHelper)iterator.next();
				//número do cartão 
				numeroCartao = gerarIntegracaoContabilidadeHelper.getNumeroCartao();
					
				//CreditoDebito
				creditoDebito = gerarIntegracaoContabilidadeHelper.getCreditoDebito();
		
				//valor do lançamento
				valorLancamento = (BigDecimal) gerarIntegracaoContabilidadeHelper.getValorLancamento();
				
				
				/*
				 * Inicio da geração do txt
				 */
				//Cartao
				gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumero(3,numeroCartao + ""));
				//Sequencial
				gerarIntegracaoTxt.append("01");
				//Lote
				gerarIntegracaoTxt.append("8888");
				//Documento
				gerarIntegracaoTxt.append("200001");
				//Linha
				gerarIntegracaoTxt.append("01");
				//data completa
				gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumero(8,dataFormatada));
				//CreditoDebito
				gerarIntegracaoTxt.append(Util.completaStringComEspacoAEsquerda(creditoDebito.trim(), 1));
				//COnta Debito
				gerarIntegracaoTxt.append(Util.completaStringComEspacoAEsquerda(gerarIntegracaoContabilidadeHelper.getNumeroContaDebito()+"", 20));
				//COnta Debito
				gerarIntegracaoTxt.append(Util.completaStringComEspacoAEsquerda(gerarIntegracaoContabilidadeHelper.getNumeroContaCredito()+"", 20));
				//Moeda
				gerarIntegracaoTxt.append("SSSSS");
				//Valor Lancamento
				gerarIntegracaoTxt.append(Util.completaStringComEspacoAEsquerda((valorLancamento + "").replace(".",""), 17));
				//LCO_HISTORICO
				if(idLancamentoOrigem.equals(LancamentoOrigem.FATURAMENTO + "")){
					gerarIntegracaoTxt.append(Util.completaStringComEspacoAEsquerda("VL FATURAMENTO", 15));
				}else if(idLancamentoOrigem.equals(LancamentoOrigem.ARRECADACAO + "")){
					gerarIntegracaoTxt.append(Util.completaStringComEspacoAEsquerda("VL ARRECADACAO", 15));
				}
				else if(idLancamentoOrigem.equals(LancamentoOrigem.DEVEDORES_DUVIDOSOS + "")){
					gerarIntegracaoTxt.append(Util.completaStringComEspacoAEsquerda("VL DEVDUVIDOSOS", 15));
				}
				//MesAno
				gerarIntegracaoTxt.append(Util.completaStringComEspacoAEsquerda(Util.formatarAnoMesParaMesAnoSemBarra(new Integer(anoMes))+"",6));
				//COdigo Custo Debito
				gerarIntegracaoTxt.append(Util.completaStringComEspacoAEsquerda(gerarIntegracaoContabilidadeHelper.getCodigoCentroCustoDebito()+"", 9));
				//COdigo Custo Debito
				gerarIntegracaoTxt.append(Util.completaStringComEspacoAEsquerda(gerarIntegracaoContabilidadeHelper.getCodigoCentroCustoCredito()+"", 9));
				//dia mes ano fechamento
				gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumero(8,dataFormatada));
				//FILLER
				gerarIntegracaoTxt.append(Util.completaStringComEspacoAEsquerda("DDDD", 4));
				//ANOMES
				gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumero(6,anoMes));
				//FILLER
				gerarIntegracaoTxt.append(Util.completaStringComEspacoAEsquerda("LANCAMENTO GCOM", 15));
				//FILLER
				gerarIntegracaoTxt.append(Util.completaStringComEspacoAEsquerda("", 318));
				//FILLER
				gerarIntegracaoTxt.append(Util.completaStringComEspacoAEsquerda("", 33));
				//Quebra de Linha
				gerarIntegracaoTxt.append("\r\n");//System.getProperty("line.separator"));

			}
			/*
			 * Determina se o arquivo é de faturamento ou arrecadação 
			 * para concatenar no nome do arquivo .zip
			 */
			String descricaoLancamento = "";
			if(idLancamentoOrigem.equals(LancamentoOrigem.FATURAMENTO + "")){
				descricaoLancamento = "FATURAMENTO";
			}else if(idLancamentoOrigem.equals(LancamentoOrigem.ARRECADACAO + "")){
				descricaoLancamento = "ARRECADACAO";
			}
			else if(idLancamentoOrigem.equals(LancamentoOrigem.DEVEDORES_DUVIDOSOS + "")){
				descricaoLancamento = "DEVDUVIDOSOS";
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
			 * Caso oarquivo txt não esteja vazio 
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
			
			//caso não exista informação para os dados informados
		}else{
			if(idLancamentoOrigem.equals(LancamentoOrigem.FATURAMENTO + "")){
				throw new ControladorException("atencao.pesquisa.nenhum_registro_tabela", null,"Resumo Faturamento");
			}else if(idLancamentoOrigem.equals(LancamentoOrigem.ARRECADACAO + "")){
				throw new ControladorException("atencao.pesquisa.nenhum_registro_tabela", null,"Resumo Arrecadação");
			}
			else if(idLancamentoOrigem.equals(LancamentoOrigem.DEVEDORES_DUVIDOSOS + "")){
				throw new ControladorException("atencao.pesquisa.nenhum_registro_tabela", null,"Resumo Devedores Duvidosos");
			}
		}
	}
	
	
	
	/**
	 * Este metodo é utilizado para pesquisar os registros q serão
	 * usados para contrução do txt do caso de uso
	 *
	 * [UC0469] Gerar Integração para a Contabilidade
	 *
	 * @author Flávio Leonardo
	 * @date 28/05/2007
	 *
	 * @param idLancamentoOrigem
	 * @param anoMes
	 * @return
	 * @throws ControladorException
	 */
	public Collection pesquisarGerarIntegracaoContabilidade(String idLancamentoOrigem, String anoMes) throws ControladorException{
		
		Collection colecaoObjetoGerar = null;
		Collection colecaoGerarIntegracaoContabilidade = null;
		
		try {

			colecaoObjetoGerar = repositorioFinanceiro.pesquisarGerarIntegracaoContabilidadeCaern(idLancamentoOrigem, anoMes);
			
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
					
					//LCO_DEB_CRE
					if(gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta() != null
							&& gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta().equals(1)){
						gerarIntegracaoContabilidadeHelper.setCreditoDebito("C");
					}else if(gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta() != null
							&& gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta().equals(2)){
						gerarIntegracaoContabilidadeHelper.setCreditoDebito("D");
					}
					
					//numero cartao
					if(gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta() != null
							&& gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta().equals(1)){
						gerarIntegracaoContabilidadeHelper.setNumeroCartao(new Short("402"));
					}else if(gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta() != null
							&& gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta().equals(2)){
						gerarIntegracaoContabilidadeHelper.setNumeroCartao(new Short("401"));
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
					
					//conta
					if(objetoGerar[5] != null && gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta().equals(1)){
						String numero = ((String) objetoGerar[5]).trim();
						gerarIntegracaoContabilidadeHelper.setNumeroContaCredito(numero);
						gerarIntegracaoContabilidadeHelper.setNumeroContaDebito("");
					}else if(objetoGerar[5] != null && gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta() != null
							&& gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta().equals(2)){
						String numero = ((String) objetoGerar[5]).trim();
						gerarIntegracaoContabilidadeHelper.setNumeroContaDebito(numero);
						gerarIntegracaoContabilidadeHelper.setNumeroContaCredito("");
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
					
//					Versao
					if(objetoGerar[12] != null){
						gerarIntegracaoContabilidadeHelper.setVersao(new Integer((Short) objetoGerar[12]));
					}
					
					/*//id localidade
					if(objetoGerar[13] != null){
						gerarIntegracaoContabilidadeHelper.setIdLocalidade((Integer)objetoGerar[13]);
					}*/
					
					// Indicador Centro Custo
					if ( objetoGerar[14] != null){
						gerarIntegracaoContabilidadeHelper.setIndicadorCentroCusto( "" + objetoGerar[14] );
					}
					
					//codigo centro custo
					if(objetoGerar[13] != null 
						&& gerarIntegracaoContabilidadeHelper.getCreditoDebito().equalsIgnoreCase("C")
						&& gerarIntegracaoContabilidadeHelper.getIndicadorCentroCusto()!=null
						&& gerarIntegracaoContabilidadeHelper.getIndicadorCentroCusto().equals(ConstantesSistema.SIM+"")){
						gerarIntegracaoContabilidadeHelper.setCodigoCentroCustoCredito(new Integer(((String) objetoGerar[13]).trim()));
					}else{
						gerarIntegracaoContabilidadeHelper.setCodigoCentroCustoCredito(new Integer(0));
					}
					
					if(objetoGerar[13] != null 
						&& gerarIntegracaoContabilidadeHelper.getCreditoDebito().equalsIgnoreCase("D")
						&& gerarIntegracaoContabilidadeHelper.getIndicadorCentroCusto()!=null
						&& gerarIntegracaoContabilidadeHelper.getIndicadorCentroCusto().equals(ConstantesSistema.SIM+"")){
						gerarIntegracaoContabilidadeHelper.setCodigoCentroCustoDebito(new Integer(((String) objetoGerar[13]).trim()));
					}else{
						gerarIntegracaoContabilidadeHelper.setCodigoCentroCustoDebito(new Integer(0));
					}
					
					colecaoGerarIntegracaoContabilidade.add(gerarIntegracaoContabilidadeHelper);
				}
			}
			
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
		return colecaoGerarIntegracaoContabilidade;
	}
	
	/**
	 * Metodo responsvel por gerar o resumo devedores duvidosos do tipo Perda Fiscal
	 * @author Arthur Carvalho, Anderson Cabral
	 * @date 30/11/2011, 04/02/2013
	 * 
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idPerdaTipo
	 * @param idUnidadeIniciada
	 * @throws ControladorException
	 */
	public synchronized void perdasFiscais(int anoMesReferenciaContabil, Integer idLocalidade, Integer idPerdaTipo, Integer idUnidadeIniciada ) throws ControladorException {
		
		
		Collection<GerarResumoDevedoresDuvidososHelper> colecaoGerarResumoDevedoresHelperTemp = null;
		List<GerarResumoDevedoresDuvidososHelper> colecaoGerarResumoDevedoresHelperPrincipal = new ArrayList<GerarResumoDevedoresDuvidososHelper>();
		Integer anoMesArrecadacao = null;
		
		try {
		//Recupera os parametros dos devedores duvidosos.
		ParametrosDevedoresDuvidosos parametrosDevedoresDuvidosos = this.repositorioFinanceiro.pesquisarParametrosDevedoresDuvidosos(anoMesReferenciaContabil);			
		
		//[FS0001] - Verificar existência dos parametros
		if(parametrosDevedoresDuvidosos == null || parametrosDevedoresDuvidosos.equals("" )){
			throw new ControladorException("atencao.naocadastrado.referencia_contabil");
		}
		
		BigDecimal valorLimiteBaixado = parametrosDevedoresDuvidosos.getValorABaixar();
		BigDecimal valorTotalJaBaixado = parametrosDevedoresDuvidosos.getValorBaixado();
		
		//5.4-Caso o valor total dos valores baixados seja maior
		//5.5-Caso contrário,processar o grupo de contas do próximo imóvel
		if(valorTotalJaBaixado.compareTo(valorLimiteBaixado) != 1) {
			
			String anoMesString = ""+ parametrosDevedoresDuvidosos.getAnoMesReferenciaContabil();
			
			Collection<Integer> colecaoQuadraId = this.repositorioFinanceiro.pesquisarIdsQuadrasParaGerarResumoDevedoresDuvidosos(idLocalidade);
			
			//ATUALIZA AS CONTAS
			if ( colecaoQuadraId != null ) {
				
				//Recupera o percentual permitido e calcula o valor limite permitido de estouro
				BigDecimal percentualPermitido = new BigDecimal(0.10);
				BigDecimal valorLimiteBaixadoComPercentual = valorLimiteBaixado.add(valorLimiteBaixado.multiply(percentualPermitido));
				
				String esferasString = 
					EsferaPoder.MUNICIPAL + ", " + 
					EsferaPoder.FEDERAL + ", " + 
					EsferaPoder.PARTICULAR + ", " +
					EsferaPoder.ESTADUAL;
				
				Collection<Short> esferasCollection = new ArrayList<Short>();
				esferasCollection.add(EsferaPoder.MUNICIPAL);
				esferasCollection.add(EsferaPoder.ESTADUAL);
				esferasCollection.add(EsferaPoder.FEDERAL);
				esferasCollection.add(EsferaPoder.PARTICULAR);
				
				Iterator<Integer> iteratorIdsQuadra = colecaoQuadraId.iterator();
				while ( iteratorIdsQuadra.hasNext() ) {
					
					Integer idQuadra = iteratorIdsQuadra.next();
					BigDecimal valorTotalValoresBaixados = BigDecimal.ZERO;
				
					////Pesquisa o valor total das contas que estão na situação de Devedor Duvidosos
					BigDecimal valorTotal = this.repositorioFinanceiro.obterValorTotalContasDevedoresDuvidosos( anoMesReferenciaContabil,
																idLocalidade, idQuadra, anoMesString, parametrosDevedoresDuvidosos.getId(), esferasCollection);
					
					if(valorTotal != null){
						valorTotalValoresBaixados = valorTotalValoresBaixados.add(valorTotal);
					}
					
					if ( valorTotalValoresBaixados == null ) {
						valorTotalValoresBaixados = BigDecimal.ZERO;
					}
					
					parametrosDevedoresDuvidosos = this.repositorioFinanceiro.pesquisarParametrosDevedoresDuvidosos(anoMesReferenciaContabil);
					
					valorTotalJaBaixado = parametrosDevedoresDuvidosos.getValorBaixado().add(valorTotalValoresBaixados);
					
					if(valorTotalJaBaixado.compareTo(valorLimiteBaixadoComPercentual) != 1){
						
						//7.Atualiza nos parametros para baixa das contas dos devedores duvidosos a data e hora
						//do processamento e o valor total baixado
						this.repositorioFinanceiro.atualizarValorBaixadoParametrosDevedoresDuvidosos(anoMesReferenciaContabil ,valorTotalValoresBaixados);
						
						this.repositorioFinanceiro.atualizaContaAnoMesReferenciaContabilDevedoresDuvidososPorEsferaDoPoder(anoMesReferenciaContabil, 
								idLocalidade, idQuadra, parametrosDevedoresDuvidosos.getId(), idPerdaTipo, esferasString);
						
						
						//[SB0001] - Acumular o resumo dos devedores duvidosos
						colecaoGerarResumoDevedoresHelperTemp = this.acumularResumoDevedoresDuvidososPorEsferaDoPoder(anoMesReferenciaContabil,idLocalidade, idQuadra ,
									PerdasTipo.PERDAS_FISCAIS);
						
						//Caso a coleção temporaria nao esteja vazia 
						//acumula os registros que estao na mesma quebra
						//e adiciona os novos registros.
						if(colecaoGerarResumoDevedoresHelperTemp != null && !colecaoGerarResumoDevedoresHelperTemp.isEmpty()){
							
							if(colecaoGerarResumoDevedoresHelperPrincipal.isEmpty()){
								colecaoGerarResumoDevedoresHelperPrincipal.addAll(colecaoGerarResumoDevedoresHelperTemp);
								colecaoGerarResumoDevedoresHelperTemp = null;
							}else{
								for(GerarResumoDevedoresDuvidososHelper gerarResumoDevedoresDuvidososHelperTemp : colecaoGerarResumoDevedoresHelperTemp ){
									if(colecaoGerarResumoDevedoresHelperPrincipal.contains(gerarResumoDevedoresDuvidososHelperTemp)){
										int posicao = colecaoGerarResumoDevedoresHelperPrincipal.indexOf(gerarResumoDevedoresDuvidososHelperTemp);

										GerarResumoDevedoresDuvidososHelper jaCadastrado = colecaoGerarResumoDevedoresHelperPrincipal.get(posicao);
										
										if ( gerarResumoDevedoresDuvidososHelperTemp.getValorBaixado()  != null && 
												!gerarResumoDevedoresDuvidososHelperTemp.getValorBaixado().equals("") && 
												!gerarResumoDevedoresDuvidososHelperTemp.getValorBaixado().equals("0")) {
											if (jaCadastrado.getValorBaixado() != null) {
												jaCadastrado.setValorBaixado(jaCadastrado.getValorBaixado().add(gerarResumoDevedoresDuvidososHelperTemp.getValorBaixado()));
											} else {
												jaCadastrado.setValorBaixado(gerarResumoDevedoresDuvidososHelperTemp.getValorBaixado());
											}
										}
										
									}else{
										
										if ( gerarResumoDevedoresDuvidososHelperTemp.getValorBaixado() != null && 
												!gerarResumoDevedoresDuvidososHelperTemp.getValorBaixado().equals("") && 
												!gerarResumoDevedoresDuvidososHelperTemp.getValorBaixado().equals("0") ) {
											colecaoGerarResumoDevedoresHelperPrincipal.add(gerarResumoDevedoresDuvidososHelperTemp);
										}
									}
								}
								colecaoGerarResumoDevedoresHelperTemp = null;
							}
						}
					}
				}
				
			
				Collection<ResumoDevedoresDuvidosos> colecaoDevedoresDuvidosos = new ArrayList<ResumoDevedoresDuvidosos>();
				
				GerenciaRegional gerenciaRegional = new GerenciaRegional();
				Integer idGerenciaRegional = this.getControladorLocalidade().pesquisarIdGerenciaParaLocalidade(idLocalidade);
				gerenciaRegional.setId(idGerenciaRegional);
				Localidade localidade = new Localidade();
				localidade.setId(idLocalidade);
				
				//6.Inserir as linhas acumuladas do resumo dos devedores duvidosos
				if(colecaoGerarResumoDevedoresHelperPrincipal != null && !colecaoGerarResumoDevedoresHelperPrincipal.isEmpty()){
					for(GerarResumoDevedoresDuvidososHelper temp : colecaoGerarResumoDevedoresHelperPrincipal){
						//Caso o valor seja maior que zero o resumo vai ser inserido 
						//caso contr?rio passar para o proximo registro.
						if(temp.getValorBaixado() != null && temp.getValorBaixado().compareTo(BigDecimal.ZERO) == 1){
							LancamentoItem lancamentoItem = null ;
							LancamentoTipo lancamentoTipo = null;
							LancamentoItemContabil lancamentoItemContabil = null;
							Categoria categoria = null;
							
							if(temp.getIdCategoria() != null){
								categoria = new Categoria();
								categoria.setId(temp.getIdCategoria());
							}
							
							if(temp.getIdLancamentoItem() != null ){
								lancamentoItem = new LancamentoItem();
								lancamentoItem.setId(temp.getIdLancamentoItem());
							}
	
							if(temp.getIdLancamentoTipo() != null ){
								lancamentoTipo = new LancamentoTipo();
								lancamentoTipo.setId(temp.getIdLancamentoTipo());
							}
	
							if(temp.getIdLancamentoItemContabil() != null ){
								lancamentoItemContabil = new LancamentoItemContabil();
								lancamentoItemContabil.setId(temp.getIdLancamentoItemContabil());
							}
							
							SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();
							if ( sistemaParametro != null ) {
								anoMesArrecadacao = sistemaParametro.getAnoMesArrecadacao();
							}
							PerdasTipo perdasTipo = new PerdasTipo();
							perdasTipo.setId(PerdasTipo.PERDAS_FISCAIS);
							
							ResumoDevedoresDuvidosos resumoDevedoresDuvidosos = new ResumoDevedoresDuvidosos(
									anoMesReferenciaContabil, 
									anoMesArrecadacao, 
									temp.getNumeroSequenciaTipoLancamento(), 
									temp.getNumeroSequencialItemTipoLancamento(), 
									temp.getValorBaixado(), 
									new Date(), 
									gerenciaRegional,
									localidade,
									categoria,
									lancamentoItemContabil,
									lancamentoTipo,
									lancamentoItem,
									perdasTipo);
							
							colecaoDevedoresDuvidosos.add(resumoDevedoresDuvidosos);
						}
					}
				}

				if ( colecaoDevedoresDuvidosos != null ) {
					//Inserindo o resumo
					getControladorBatch().inserirColecaoObjetoParaBatch(colecaoDevedoresDuvidosos);
					colecaoDevedoresDuvidosos = null;
				}
			}
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);
			
		}else{
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);
		}
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex, idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}
	
	/**
	 * 
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 * 
	 * 
	 * @author Arthur Carvalho, Anderson Cabral
	 * @date 30/11/2010, 04/02/2013
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idParametrosDevedoresDuvidosos
	 * @return
	 * @throws ControladorException
	 */
	protected Collection<GerarResumoDevedoresDuvidososHelper> acumularResumoDevedoresDuvidososPorEsferaDoPoder(int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra, Integer idTipoPerda)	throws ControladorException {
	    
	    Collection<GerarResumoDevedoresDuvidososHelper> colecaoRetorno = new ArrayList();
	    GerarResumoDevedoresDuvidososHelper gerarResumoDevedoresDuvidososHelper = null;
	    Collection<Object[]> colecaoDadosTemporaria = null;
	    final Short ZERO = 0;
	    
	    Short maxSequencialImpressaoMais10 = this.getControladorFaturamento().recuperarValorMaximoSequencialImpressaoMais10();
	    
		try {

			Integer idGerenciaRegional = this.getControladorLocalidade().pesquisarIdGerenciaParaLocalidade(idLocalidade);
	
			//TIPO DE FINANCIAMENTO = AGUA
			Collection<Object[]> valorAguaDevedoresDuvidosos = this.repositorioFinanceiro.
						pesquisarValorAguaAgrupadoPorCategoriaDevedoresDuvidosos( anoMesReferenciaContabil, idLocalidade, idQuadra ,
							idTipoPerda);
			
			if ( valorAguaDevedoresDuvidosos != null ) {

				Iterator iteratorValorAguaCategoria = valorAguaDevedoresDuvidosos.iterator();
				while (iteratorValorAguaCategoria.hasNext()) {
					Object[] objeto = (Object[]) iteratorValorAguaCategoria.next();
					BigDecimal valorAgua = (BigDecimal) objeto[0];
					Integer categoriaId = (Integer) objeto[1];
				
					gerarResumoDevedoresDuvidososHelper = new GerarResumoDevedoresDuvidososHelper(idLocalidade, 
							idGerenciaRegional, 
							categoriaId, 
							LancamentoTipo.AGUA, 
							LancamentoItem.AGUA, 
							null, 
							new Short("100"), 
							ZERO, 
							valorAgua);
					colecaoRetorno.add(gerarResumoDevedoresDuvidososHelper);
				}
			}
				
			//TIPO DE FINANCIAMENTO = ESGOTO
			Collection<Object[]> valorEsgotoDevedoresDuvidosos = this.repositorioFinanceiro.
			pesquisarValorEsgotoAgrupadoPorCategoriaDevedoresDuvidosos( anoMesReferenciaContabil, idLocalidade,  idQuadra, idTipoPerda);
			
			if ( valorEsgotoDevedoresDuvidosos != null ) {

				Iterator iteratorValorEsgotoCategoria = valorEsgotoDevedoresDuvidosos.iterator();
				while (iteratorValorEsgotoCategoria.hasNext()) {
					Object[] objeto = (Object[]) iteratorValorEsgotoCategoria.next();
					BigDecimal valorEsgoto = (BigDecimal) objeto[0];
					Integer categoriaId = (Integer) objeto[1];
				
					gerarResumoDevedoresDuvidososHelper = new GerarResumoDevedoresDuvidososHelper(idLocalidade, 
							idGerenciaRegional, 
							categoriaId, 
							LancamentoTipo.ESGOTO, 
							LancamentoItem.ESGOTO, 
							null, 
							new Short("200"), 
							ZERO, 
							valorEsgoto);
					colecaoRetorno.add(gerarResumoDevedoresDuvidososHelper);
				}
			}
				
			//TIPO DE FINANCIAMENTO = PARCELAMENTOS COBRADOS ( agua )
			Collection<Object[]> valorAguaParcelamentoDevedoresDuvidosos = this.repositorioFinanceiro.
				pesquisarValorAguaParcelamentoAgrupadoPorCategoriaDevedoresDuvidosos( anoMesReferenciaContabil, idLocalidade, idQuadra, idTipoPerda);
			
			if ( valorAguaParcelamentoDevedoresDuvidosos != null ) {

				Iterator iteratorValorAguaParcelamentoCategoria = valorAguaParcelamentoDevedoresDuvidosos.iterator();
				while (iteratorValorAguaParcelamentoCategoria.hasNext()) {
					Object[] objeto = (Object[]) iteratorValorAguaParcelamentoCategoria.next();
					BigDecimal valorAguaParcelamento = (BigDecimal) objeto[0];
					Integer categoriaId = (Integer) objeto[1];
				
					gerarResumoDevedoresDuvidososHelper = new GerarResumoDevedoresDuvidososHelper(idLocalidade, 
							idGerenciaRegional, 
							categoriaId, 
							LancamentoTipo.PARCELAMENTOS_COBRADOS, 
							LancamentoItem.AGUA, 
							null, 
							new Short("1000"), 
							new Short("10"), 
							valorAguaParcelamento);
					colecaoRetorno.add(gerarResumoDevedoresDuvidososHelper);
				}
			}
				
			//TIPO DE FINANCIAMENTO = PARCELAMENTOS COBRADOS ( esgoto )
			Collection<Object[]> valorEsgotoParcelamentoDevedoresDuvidosos = this.repositorioFinanceiro.
			pesquisarValorEsgotoParcelamentoAgrupadoPorCategoriaDevedoresDuvidosos( anoMesReferenciaContabil, idLocalidade, idQuadra,  idTipoPerda);
		
			if ( valorEsgotoParcelamentoDevedoresDuvidosos != null ) {

				Iterator iteratorValorEsgotoParcelamentoCategoria = valorEsgotoParcelamentoDevedoresDuvidosos.iterator();
				while (iteratorValorEsgotoParcelamentoCategoria.hasNext()) {
					Object[] objeto = (Object[]) iteratorValorEsgotoParcelamentoCategoria.next();
					BigDecimal valorEsgotoParcelamento = (BigDecimal) objeto[0];
					Integer categoriaId = (Integer) objeto[1];
				
					gerarResumoDevedoresDuvidososHelper = new GerarResumoDevedoresDuvidososHelper(idLocalidade, 
							idGerenciaRegional, 
							categoriaId, 
							LancamentoTipo.PARCELAMENTOS_COBRADOS, 
							LancamentoItem.ESGOTO, 
							null, 
							new Short("1000"), 
							new Short("20"), 
							valorEsgotoParcelamento);
					colecaoRetorno.add(gerarResumoDevedoresDuvidososHelper);
				}
			}
			
			//TIPO DE FINANCIAMENTO = PARCELAMENTOS COBRADOS ( grupo contabil )
			colecaoDadosTemporaria = repositorioFinanceiro.pesquisarValorServicoParceladoDevedoresDuvidosos(anoMesReferenciaContabil,idLocalidade, idQuadra,
				idTipoPerda);
			
			if (colecaoDadosTemporaria != null && !colecaoDadosTemporaria.isEmpty()) {
				
				for (Object[] dadosDebitoCobrado : colecaoDadosTemporaria) {
					
					BigDecimal valorDebito =(BigDecimal) dadosDebitoCobrado[0];
					Short numeroSequencialImpressao = (Short) dadosDebitoCobrado[1]; 
					Integer idLancamentoItemContabil = (Integer) dadosDebitoCobrado[2];
					Integer categoriaId = (Integer) dadosDebitoCobrado[3];
					
					if(valorDebito != null && valorDebito.compareTo(BigDecimal.ZERO) == 1){

						gerarResumoDevedoresDuvidososHelper = new GerarResumoDevedoresDuvidososHelper(idLocalidade, 
								idGerenciaRegional, 
								categoriaId, 
								LancamentoTipo.PARCELAMENTOS_COBRADOS, 
								LancamentoItem.GRUPO_CONTABIL, 
								idLancamentoItemContabil, 
								new Short("1000"), 
								numeroSequencialImpressao, 
								valorDebito);
						
						colecaoRetorno.add(gerarResumoDevedoresDuvidososHelper);
					}
				}
			}
				
			//TIPO DE FINANCIAMENTO = PARCELAMENTOS COBRADOS ( juros )
			Collection<Object[]> valorJurosParcelamentoDevedoresDuvidosos = this.repositorioFinanceiro.
				pesquisarValorJurosDoParcelamentoDevedoresDuvidosos( anoMesReferenciaContabil, idLocalidade, idQuadra, idTipoPerda);
		
			if ( valorJurosParcelamentoDevedoresDuvidosos != null ) {

				Iterator iteratorValorJurosParcelamentoCategoria = valorJurosParcelamentoDevedoresDuvidosos.iterator();
				while (iteratorValorJurosParcelamentoCategoria.hasNext()) {
					Object[] objeto = (Object[]) iteratorValorJurosParcelamentoCategoria.next();
					BigDecimal valorJurosParcelamento = (BigDecimal) objeto[0];
					Integer categoriaId = (Integer) objeto[1];
				
					gerarResumoDevedoresDuvidososHelper = new GerarResumoDevedoresDuvidososHelper(idLocalidade, 
							idGerenciaRegional, 
							categoriaId, 
							LancamentoTipo.PARCELAMENTOS_COBRADOS, 
							LancamentoItem.JUROS, 
							null, 
							new Short("1000"), 
							maxSequencialImpressaoMais10, 
							valorJurosParcelamento);
					colecaoRetorno.add(gerarResumoDevedoresDuvidososHelper);
				}
			}
				
			//TIPO DE FINANCIAMENTO = FINANCIAMENTOS COBRADOS ( grupo contabil )
			colecaoDadosTemporaria  = repositorioFinanceiro.pesquisarValorPorTipoFinanciamentoDevedoresDuvidosos( anoMesReferenciaContabil,
					idLocalidade, idQuadra, idTipoPerda);
			
			if (colecaoDadosTemporaria != null && !colecaoDadosTemporaria.isEmpty()) {
				
				for (Object[] dadosDebitoCobrado : colecaoDadosTemporaria) {
					BigDecimal valorDebito =(BigDecimal) dadosDebitoCobrado[0];
					Short numeroSequencialImpressao = (Short) dadosDebitoCobrado[1]; 
					Integer idLancamentoItemContabil = (Integer) dadosDebitoCobrado[2];
					Integer categoriaId = (Integer) dadosDebitoCobrado[3];
					
					if(valorDebito != null && valorDebito.compareTo(BigDecimal.ZERO) == 1){
						gerarResumoDevedoresDuvidososHelper = new GerarResumoDevedoresDuvidososHelper(idLocalidade, 
								idGerenciaRegional, 
								categoriaId, 
								LancamentoTipo.FINANCIAMENTOS_COBRADOS, 
								LancamentoItem.GRUPO_CONTABIL, 
								idLancamentoItemContabil, 
								new Short("1300"), 
								numeroSequencialImpressao, 
								valorDebito);
						
						colecaoRetorno.add(gerarResumoDevedoresDuvidososHelper);
					}
				}
				
			}
			
			//TIPO DE FINANCIAMENTO = DEVOLUCAO
			Collection<Object[]> colecaoDevolucoes  = repositorioFinanceiro.pesquisarDevolucoesValoresContaDevedoresDuvidosos( anoMesReferenciaContabil,
					idLocalidade, idQuadra, idTipoPerda);
			
			if (colecaoDevolucoes != null && !colecaoDevolucoes.isEmpty()) {
				
				for (Object[] dadosDebitoCobrado : colecaoDevolucoes) {
					
					BigDecimal valorCredito =(BigDecimal) dadosDebitoCobrado[0];
					Short numeroSequencialImpressao = (Short) dadosDebitoCobrado[1]; 
					Integer idLancamentoItemContabil = (Integer) dadosDebitoCobrado[2]; 
					Integer categoriaId = (Integer) dadosDebitoCobrado[3];
					
					if(valorCredito != null ){
						gerarResumoDevedoresDuvidososHelper = new GerarResumoDevedoresDuvidososHelper(idLocalidade, 
								idGerenciaRegional, 
								categoriaId, 
								LancamentoTipo.DEVOLUCAO__VALORES_EM_CONTA_INT, 
								LancamentoItem.CREDITOS_PARA_COBRANCA_INDEVIDA, 
								idLancamentoItemContabil, 
								new Short("1400"), 
								numeroSequencialImpressao, 
								valorCredito);
						
						colecaoRetorno.add(gerarResumoDevedoresDuvidososHelper);
					}
				}
			}
			
			/***
			 *
			 *[SB0020] - Acumular valor de perda fiscais Estadual 
			 *
			 **/
			Collection<Object[]> valoresPerdasFiscais = 
				this.repositorioFinanceiro.pesquisarValorTotalEsferaPoderEstadualAgrupadoPorCategoriaDevedoresDuvidosos( 
				anoMesReferenciaContabil, 
				idLocalidade, 
				idQuadra ,
				idTipoPerda) ;
			
			if ( valoresPerdasFiscais != null ) {

				Iterator iteratorValoresPerdasFiscaisCategoria = valoresPerdasFiscais.iterator();
				while (iteratorValoresPerdasFiscaisCategoria.hasNext()) {
					Object[] objeto = (Object[]) iteratorValoresPerdasFiscaisCategoria.next();
					BigDecimal valor = (BigDecimal) objeto[0];
					Integer categoriaId = (Integer) objeto[1];
				
					gerarResumoDevedoresDuvidososHelper = new GerarResumoDevedoresDuvidososHelper(idLocalidade, 
							idGerenciaRegional, 
							categoriaId, 
							LancamentoTipo.ESFERA_ESTADUAL,
							LancamentoItem.ESFERA_ESTADUAL,
							null, 
							new Short("1600"), 
							ZERO, 
							valor);
					colecaoRetorno.add(gerarResumoDevedoresDuvidososHelper);
				}
			}
		
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
		
		return colecaoRetorno;
	}

	
	
}
