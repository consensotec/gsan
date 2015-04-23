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
package gsan.financeiro;

import gsan.arrecadacao.Arrecadador;
import gsan.arrecadacao.ControladorArrecadacaoLocal;
import gsan.arrecadacao.ControladorArrecadacaoLocalHome;
import gsan.arrecadacao.FiltroArrecadador;
import gsan.arrecadacao.IRepositorioArrecadacao;
import gsan.arrecadacao.RecebimentoTipo;
import gsan.arrecadacao.RepositorioArrecadacaoHBM;
import gsan.arrecadacao.ResumoArrecadacao;
import gsan.arrecadacao.aviso.AvisoBancario;
import gsan.arrecadacao.banco.Banco;
import gsan.arrecadacao.banco.ContaBancaria;
import gsan.arrecadacao.banco.FiltroBanco;
import gsan.batch.ControladorBatchLocal;
import gsan.batch.ControladorBatchLocalHome;
import gsan.batch.ProcessoIniciado;
import gsan.batch.UnidadeProcessamento;
import gsan.cadastro.ControladorCadastroLocal;
import gsan.cadastro.ControladorCadastroLocalHome;
import gsan.cadastro.EnvioEmail;
import gsan.cadastro.cliente.EsferaPoder;
import gsan.cadastro.endereco.ControladorEnderecoLocal;
import gsan.cadastro.endereco.ControladorEnderecoLocalHome;
import gsan.cadastro.geografico.Municipio;
import gsan.cadastro.imovel.Categoria;
import gsan.cadastro.imovel.CategoriaTipo;
import gsan.cadastro.imovel.ControladorImovelLocal;
import gsan.cadastro.imovel.ControladorImovelLocalHome;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.imovel.ImovelPerfil;
import gsan.cadastro.imovel.ImovelSubcategoria;
import gsan.cadastro.imovel.ImovelSubcategoriaPK;
import gsan.cadastro.imovel.Subcategoria;
import gsan.cadastro.localidade.ControladorLocalidadeLocal;
import gsan.cadastro.localidade.ControladorLocalidadeLocalHome;
import gsan.cadastro.localidade.GerenciaRegional;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.Quadra;
import gsan.cadastro.localidade.SetorComercial;
import gsan.cadastro.localidade.UnidadeNegocio;
import gsan.cadastro.sistemaparametro.NacionalFeriado;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cobranca.CobrancaSituacao;
import gsan.cobranca.ControladorCobrancaLocal;
import gsan.cobranca.ControladorCobrancaLocalHome;
import gsan.cobranca.DocumentoTipo;
import gsan.faturamento.ControladorFaturamentoLocal;
import gsan.faturamento.ControladorFaturamentoLocalHome;
import gsan.faturamento.FaturamentoGrupo;
import gsan.faturamento.IRepositorioFaturamento;
import gsan.faturamento.RepositorioFaturamentoHBM;
import gsan.faturamento.conta.Conta;
import gsan.financeiro.bean.AcumularValoresHelper;
import gsan.financeiro.bean.GerarIntegracaoContabilidadeHelper;
import gsan.financeiro.bean.GerarResumoDevedoresDuvidososHelper;
import gsan.financeiro.bean.ParametrosPerdasFiscaisHelper;
import gsan.financeiro.bean.ParametrosPerdasFiscaisItensHelper;
import gsan.financeiro.bean.ResumoDevedoresDuvidososRelatorioHelper;
import gsan.financeiro.lancamento.IRepositorioLancamentoItemContabil;
import gsan.financeiro.lancamento.LancamentoContabil;
import gsan.financeiro.lancamento.LancamentoContabilItem;
import gsan.financeiro.lancamento.LancamentoItem;
import gsan.financeiro.lancamento.LancamentoItemContabil;
import gsan.financeiro.lancamento.LancamentoOrigem;
import gsan.financeiro.lancamento.LancamentoTipo;
import gsan.financeiro.lancamento.RepositorioLancamentoItemContabilHBM;
import gsan.gui.ActionServletException;
import gsan.integracao.IRepositorioIntegracao;
import gsan.integracao.RepositorioIntegracaoHBM;
import gsan.relatorio.financeiro.RelatorioEvolucaoContasAReceberContabilBean;
import gsan.relatorio.financeiro.RelatorioParametrosContabeisArrecadacaoBean;
import gsan.relatorio.financeiro.RelatorioParametrosContabeisFaturamentoBean;
import gsan.relatorio.financeiro.RelatorioSaldoContasAReceberContabilBean;
import gsan.relatorio.financeiro.RelatorioVolumesConsumidosNaoFaturadosBean;
import gsan.relatorio.financeiro.ResumoReceitaHelper;
import gsan.util.ConstantesJNDI;
import gsan.util.ConstantesSistema;
import gsan.util.ControladorException;
import gsan.util.ControladorUtilLocal;
import gsan.util.ControladorUtilLocalHome;
import gsan.util.ErroRepositorioException;
import gsan.util.IoUtil;
import gsan.util.ServiceLocator;
import gsan.util.ServiceLocatorException;
import gsan.util.SistemaException;
import gsan.util.Util;
import gsan.util.ZipUtil;
import gsan.util.email.ServicosEmail;
import gsan.util.filtro.ParametroSimples;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * Controlador Financeiro PADRÃO
 * 
 * @author Raphael Rossiter
 * @date 26/06/2007
 */
public class ControladorFinanceiro implements SessionBean {
	
	protected static final long serialVersionUID = 1L;
	SessionContext sessionContext;

	protected IRepositorioFinanceiro repositorioFinanceiro = null;
	protected IRepositorioLancamentoItemContabil repositorioLancamentoItemContabil = null;
	protected IRepositorioFaturamento repositorioFaturamento = null;
	protected IRepositorioArrecadacao repositorioArrecadacao = null;
	protected IRepositorioIntegracao repositorioIntegracao = null;

	/**
	 * < <Descrição do método>>
	 * 
	 * @exception CreateException
	 *                Descrição da exceção
	 */
	public void ejbCreate() throws CreateException {

		repositorioFinanceiro = RepositorioFinanceiroHBM.getInstancia();
		repositorioLancamentoItemContabil = RepositorioLancamentoItemContabilHBM.getInstancia();
		repositorioFaturamento = RepositorioFaturamentoHBM.getInstancia();
		repositorioArrecadacao = RepositorioArrecadacaoHBM.getInstancia();
		repositorioIntegracao = RepositorioIntegracaoHBM.getInstancia();

	}

	/**
	 * < <Descrição do método>>
	 */
	public void ejbRemove() {
	}

	/**
	 * < <Descrição do método>>
	 */
	public void ejbActivate() {
	}

	/**
	 * < <Descrição do método>>
	 */
	public void ejbPassivate() {
	}

	/**
	 * Seta o valor de sessionContext
	 * 
	 * @param sessionContext
	 *            O novo valor de sessionContext
	 */
	public void setSessionContext(SessionContext sessionContext) {
		this.sessionContext = sessionContext;
	}

	/**
	 * Retorna o valor de controladorArrecadacao
	 * 
	 * @return O valor de controladorCliente
	 */
	protected ControladorArrecadacaoLocal getControladorArrecadacao() {
		ControladorArrecadacaoLocalHome localHome = null;
		ControladorArrecadacaoLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorArrecadacaoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_ARRECADACAO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	/**
	 * Retorna o valor de controladorUtil
	 * 
	 * @return O valor de controladorUtil
	 */
	protected ControladorUtilLocal getControladorUtil() {

		ControladorUtilLocalHome localHome = null;
		ControladorUtilLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorUtilLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_UTIL_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas à
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}
	
    /**
     * Retorna o valor de controladorEndereco
     * 
     * @return O valor de controladorEndereco
     */
    private ControladorEnderecoLocal getControladorEndereco() {

        ControladorEnderecoLocalHome localHome = null;
        ControladorEnderecoLocal local = null;

        // pega a instância do ServiceLocator.

        ServiceLocator locator = null;

        try {
            locator = ServiceLocator.getInstancia();

            localHome = (ControladorEnderecoLocalHome) locator
                    .getLocalHome(ConstantesJNDI.CONTROLADOR_ENDERECO_SEJB);
            // guarda a referencia de um objeto capaz de fazer chamadas à
            // objetos remotamente
            local = localHome.create();

            return local;
        } catch (CreateException e) {
            throw new SistemaException(e);
        } catch (ServiceLocatorException e) {
            throw new SistemaException(e);
        }
    }    
    
	protected ControladorFaturamentoLocal getControladorFaturamento() {
		ControladorFaturamentoLocalHome localHome = null;
		ControladorFaturamentoLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorFaturamentoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FATURAMENTO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas à
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
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
	 * Retorna o valor de controladorLocalidade
	 * 
	 * @return O valor de controladorLocalidade
	 */
	protected ControladorLocalidadeLocal getControladorLocalidade() {
		ControladorLocalidadeLocalHome localHome = null;
		ControladorLocalidadeLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorLocalidadeLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_LOCALIDADE_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas à
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	/**
	 * Retorna o valor de controladorImovel
	 * 
	 * @return O valor de controladorImovel
	 */
	protected ControladorImovelLocal getControladorImovel() {
		ControladorImovelLocalHome localHome = null;
		ControladorImovelLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorImovelLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_IMOVEL_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas à
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}	
	
	protected ControladorBatchLocal getControladorBatch() {
		ControladorBatchLocalHome localHome = null;
		ControladorBatchLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorBatchLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_BATCH_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas à
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	/**
	 * [UC0175] - Gerar Lançamentos Contábeis do Faturamento
	 * Author: Raphael Rossiter, Pedro Alexandre 
	 * Data: 16/01/2006, 23/05/2007
	 * 
	 * Gera os lançamentos contábeis a partir dos dados selecionados na tabela RESUMO_FATURAMENTO
	 * 
	 * @param anoMesReferenciaFaturamento
	 * @param idLocalidade
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void gerarLancamentosContabeisFaturamento(
		Integer anoMesReferenciaFaturamento, 
		Integer idLocalidade, 
		int idFuncionalidadeIniciada) 
		throws ControladorException{
		
		//[FS0001 - Validar ano/mês do Faturamento]
		Integer anoMesFaturamentoAtual =  
			getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento();
		
		if(anoMesReferenciaFaturamento.intValue() >anoMesFaturamentoAtual.intValue()){
			//levanta a exceção para a próxima camada
			throw new ControladorException("atencao.mes_ano.faturamento.inferior",
				null,
				Util.formatarAnoMesParaMesAno(anoMesFaturamentoAtual.toString()));
		}

		
		int idUnidadeIniciada = 0;

		/*
		 * Registrar o início do processamento da Unidade de
		 * Processamento do Batch
		*/
		idUnidadeIniciada = 
			getControladorBatch().iniciarUnidadeProcessamentoBatch(
				idFuncionalidadeIniciada,
				UnidadeProcessamento.LOCALIDADE,
				(idLocalidade));
		
		try {
			
			repositorioFinanceiro.removerLancamentosContabeisEItens(idLocalidade, anoMesReferenciaFaturamento, LancamentoOrigem.FATURAMENTO);
			
			/*
			 *  Pesquisa os dados do resumo do faturamento para o ano/mês de referência atual e 
			 *  para a localidade informada.
			 *  
			 *  0 - id da localidade
			 *  1 - id do tipo de lançamento
			 *  2 - id do item de lançamento
			 *  3 - id do item de lançamento contábil
			 *  4 - id da categoria
			 *  5 - soma do valor do resumo do faturamento 
			 */
			Collection<Object[]> colecaoDadosResumoFaturamento = 
				repositorioFinanceiro.obterDadosResumoFaturamento(
					anoMesReferenciaFaturamento, 
					idLocalidade);
		
			/*
			 * Caso exista resumo de faturamento para a localidade e o ano/mês 
			 * cria o lancamento contábil junto com seus items 
			 * para cada conjunto de mesmo tipo de lançamento
			 */
			if (colecaoDadosResumoFaturamento != null && !colecaoDadosResumoFaturamento.isEmpty()){
				
				//flag utilizada somente quando for a primeira entrada
				boolean flagPrimeiraVez = true;
				int idTipoLancamentoTemp = -1;
				Collection<Object[]> colecaoDadosResumoPorTipoLancamento = new ArrayList();
				
				// definição da origem do lançamento
				LancamentoOrigem lancamentoOrigem = new LancamentoOrigem();
				lancamentoOrigem.setId(LancamentoOrigem.FATURAMENTO);
				
				//Cria a variável que vai armazenar o lançamento contábil
				LancamentoContabil lancamentoContabilInsert = null;
				
				//laço para gerar os lançamentos por grupo de tipo de lançamento
				for(Object[] dadosResumoFaturamento : colecaoDadosResumoFaturamento){
					
					//recupera o tipo de lançamento atual 
					Integer idTipoLancamento = (Integer) dadosResumoFaturamento[1];
					
					/*
					 * Caso seja a primeira entrada do "for"
					 * adiciona os dados a coleção e atualiza o item temporario
					 * criando também o lançamento contabil que ira ser inserindo
					 * junto com seus items.
					 * 
					 *  Caso contrário (não seja a primeira entrada do laço "for")
					 *  verifica se o item de lançamento mudou, caso não tenha mudado 
					 *  adiciona os dados ao conjunto do mesmo item
					 *  caso contrário, se mudou o item de lançamento o conjunto está fechado
					 *  para o lançamento contábil e chama o me´todo para inserir o
					 *  lançamento contábil junto com seus itens. 
					 */
					if(flagPrimeiraVez){
						colecaoDadosResumoPorTipoLancamento.add(dadosResumoFaturamento);
						flagPrimeiraVez = false;
						idTipoLancamentoTemp = idTipoLancamento;
						
						LancamentoTipo tipoLancamento = new LancamentoTipo();
						tipoLancamento.setId(idTipoLancamento);
						
						Localidade localidade = new Localidade();
						localidade.setId(idLocalidade);
						
						//cria o lançamento contábil para ser inserido 
						lancamentoContabilInsert = new LancamentoContabil();
						lancamentoContabilInsert.setAnoMes(anoMesReferenciaFaturamento);
						lancamentoContabilInsert.setLancamentoOrigem(lancamentoOrigem);
						lancamentoContabilInsert.setLancamentoTipo(tipoLancamento);
						lancamentoContabilInsert.setLocalidade(localidade);
						lancamentoContabilInsert.setRecebimentoTipo(null);
						lancamentoContabilInsert.setUltimaAlteracao(new Date());
					}else{
						/*
						 * Caso ainda seja o mesmo item adicona os dados para 
						 * ser gerado os itens do lançamento para o mesmo lançamento.
						 * Caso contrário chama o metódo para inseri os itens e o lançamento contábil.
						 */
						if(idTipoLancamento == idTipoLancamentoTemp){
							colecaoDadosResumoPorTipoLancamento.add(dadosResumoFaturamento);
						}else{
							/* metódo para inserir o lançamento contábil assim como seus itens */
							this.inserirLancamentoContabilItemFaturamento(
								lancamentoContabilInsert, 
								colecaoDadosResumoPorTipoLancamento);
							
							//limpaa coleção e adiciona os dados do resumo atual
							colecaoDadosResumoPorTipoLancamento.clear();
							colecaoDadosResumoPorTipoLancamento.add(dadosResumoFaturamento);
							
							LancamentoTipo tipoLancamento = new LancamentoTipo();
							tipoLancamento.setId(idTipoLancamento);
							
							Localidade localidade = new Localidade();
							localidade.setId(idLocalidade);
							
							//cria o lançamento contábil que vai ser inserido
							lancamentoContabilInsert = new LancamentoContabil();
							lancamentoContabilInsert.setAnoMes(anoMesReferenciaFaturamento);
							lancamentoContabilInsert.setLancamentoOrigem(lancamentoOrigem);
							lancamentoContabilInsert.setLancamentoTipo(tipoLancamento);
							lancamentoContabilInsert.setLocalidade(localidade);
							lancamentoContabilInsert.setRecebimentoTipo(null);
							lancamentoContabilInsert.setUltimaAlteracao(new Date());
							
							//atualiza o tipo de lançamento temporário com o novo valor
							idTipoLancamentoTemp = idTipoLancamento;
						}
					}
				}
				
				/*
				 * Último registro
				 * Esse "if" é para verificar se ainda existe um último registro na coleção
				 * caso exista algum item, adiciona o lançamento contábil junto com o item. 
				 */
				if(colecaoDadosResumoPorTipoLancamento != null && colecaoDadosResumoPorTipoLancamento.size() > 0 ){
					this.inserirLancamentoContabilItemFaturamento(
						lancamentoContabilInsert, 
						colecaoDadosResumoPorTipoLancamento);
					colecaoDadosResumoPorTipoLancamento = null;
				}
			}
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);
		
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}
	
	
	/**
	 * Gera o lançamento contábil junto com seus itens. 
	 *
	 * [UC0175] - Gerar Lançamentos Contábeis do Faturamento
	 *
	 * @author Pedro Alexandre
	 * @date 24/05/2007
	 *
	 * @param lancamentoContabil
	 * @param colecaoDadosResumoPorTipoLancamento
	 * @throws ControladorException
	 */
	protected void inserirLancamentoContabilItemFaturamento(LancamentoContabil lancamentoContabil,Collection<Object[]> colecaoDadosResumoPorTipoLancamento) throws ControladorException {
		try{
			/*
			 * Caso exista dados para os itens do resumo do faturamento.
			 */
			if(colecaoDadosResumoPorTipoLancamento != null && !colecaoDadosResumoPorTipoLancamento.isEmpty()){
				
				Collection colecaoLancamentoContabilItem = new ArrayList();
				
				//flag que indica se o lançamento contábil já foi inserido ou não. 
				boolean flagInseridoLancamentoContabil = false;
				
				/*
				 * Dados do resumo do faturamento
				 *  0 - id da localidade
				 *  1 - id do tipo de lançamento
				 *  2 - id do item de lançamento
				 *  3 - id do item de lançamento contábil
				 *  4 - id da categoria
				 *  5 - soma do valor do resumo do faturamento
				 */
				for(Object[] dadosResumoFaturamento : colecaoDadosResumoPorTipoLancamento){
					
					//recupera os dados do resumo do faturamento
					Integer idLancamentoTipo =         (Integer) dadosResumoFaturamento[1];
					Integer idLancamentoItem =         (Integer) dadosResumoFaturamento[2];
					Integer idLancamentoItemContabil = (Integer) dadosResumoFaturamento[3];
					Integer idCategoria =              (Integer) dadosResumoFaturamento[4];
					BigDecimal valorLancamento =       (BigDecimal) dadosResumoFaturamento[5]; 
					
					/* 
					 * Verifica se existe conta contábil para o item que vai ser inserido 
					 * 
					 * 0 - id conta contábil do débito
					 * 1 - id conta contábil crédito 
					 * 2 - descrição do histórico do débito
					 * 3 - descrição do histórico do crédito
					 */
					Object[] dadosContaContabil = this.repositorioFinanceiro.obterParametrosContabilFaturamento(idCategoria, idLancamentoItemContabil, idLancamentoItem, idLancamentoTipo);
					
					if(dadosContaContabil != null){
						Integer idLancamentoContabil = null;
						/*
						 * Caso exista dados para a conta contábil e o item contábil 
						 * ainda não foi inserido 
						 * inseri o item contábil na base.
						 */
						if(!flagInseridoLancamentoContabil){
							idLancamentoContabil = (Integer)getControladorUtil().inserir(lancamentoContabil);
							lancamentoContabil.setId(idLancamentoContabil);
							flagInseridoLancamentoContabil = true;
						}
						
						//recupera os dados da conta contábil para o item do resumo do faturamento.
						Integer idContaContabilDebito = (Integer) dadosContaContabil[0];
						Integer idContaContabilCredito = (Integer) dadosContaContabil[1];
						String descricaoHistoricoDebito = (String) dadosContaContabil[2];
						String descricaoHistoricoCredito = (String) dadosContaContabil[3];
						
						//cria os indicadores de débito e crédito.
						Short indicadorDebito = new Short("2"); 
						Short indicadorCredito = new Short("1");
						
						Date ultimaAlteracao = new Date();
						
						//cria as contas contábeis de crédito e débito.
						ContaContabil contaContabilCredito = new ContaContabil();
						contaContabilCredito.setId(idContaContabilCredito);
						
						ContaContabil contaContabilDebito = new ContaContabil();
						contaContabilDebito.setId(idContaContabilDebito);
						
						/**  Item de crédito */
						LancamentoContabilItem lancamentoContabilItemCredito = new LancamentoContabilItem(indicadorCredito, 
								valorLancamento, 
								descricaoHistoricoCredito, 
								ultimaAlteracao, 
								lancamentoContabil, 
								contaContabilCredito);
						
						colecaoLancamentoContabilItem.add(lancamentoContabilItemCredito);
						
						/** Item de débito */
						LancamentoContabilItem lancamentoContabilItemDebito = new LancamentoContabilItem(indicadorDebito, 
								valorLancamento, 
								descricaoHistoricoDebito, 
								ultimaAlteracao, 
								lancamentoContabil, 
								contaContabilDebito);
						
						colecaoLancamentoContabilItem.add(lancamentoContabilItemDebito);
						
					}
				}
				//inserios itens de lançamento contábeis. 
				getControladorBatch().inserirColecaoObjetoParaBatch(colecaoLancamentoContabilItem); 
			}
	
		} catch (Exception ex) {
			throw new EJBException(ex);
		}
	}
	
	
	public void inserirLancamentoContabilItem(LancamentoContabil lancamentoContabil, int etapa, 
			BigDecimal valorTotalResidencial, BigDecimal valorTotalComercial, BigDecimal valorTotalIndustrial,
			BigDecimal valorTotalPublico, Collection<AcumularValoresHelper> 
			colecaoAcumularValoresPorLancamentoItemContabil, Collection<AcumularValoresHelper> 
			colecaoAcumularValoresPorLancamentoItemCategoria) throws ControladorException {
		
		LancamentoContabilItem lancamentoContabilItem = new LancamentoContabilItem();
		lancamentoContabilItem.setLancamentoContabil(lancamentoContabil);
		
		
		//Utilizado dentro do switch
		LancamentoItem lancamentoItem = new LancamentoItem();
		Categoria categoria = new Categoria();
        
		switch (lancamentoContabil.getLancamentoTipo().getId().intValue()) {
		
			case LancamentoTipo.AGUA_INT:
				
				/*
				* ETAPA Nº 01
				* Razão Contábil = 12 e Conta Contábil = 111
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				if (etapa == 1){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("111"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_DEBITO);
					
								
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 2, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 02
				* Razão Contábil = 12 e Conta Contábil = 112
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = PUBLICO 
				*/
				else if (etapa == 2){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("112"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_DEBITO);
					
					
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 3, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil, 
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 03
				* Razão Contábil = 31 e Conta Contábil = 111
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = RESIDENCIAL 
				*/
				else if (etapa == 3){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("31"), new Integer("111"), valorTotalResidencial, null, 
			        null, null, ConstantesSistema.INDICADOR_CREDITO);				
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 4, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 04
				* Razão Contábil = 31 e Conta Contábil = 112
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = COMERCIAL 
				*/
				else if (etapa == 4){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("31"), new Integer("112"), null, valorTotalComercial, 
			        null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 5, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 05
				* Razão Contábil = 31 e Conta Contábil = 113
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = INDUSTRIAL 
				*/
				else if (etapa == 5){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("31"), new Integer("113"), null, null, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 6, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 06
				* Razão Contábil = 31 e Conta Contábil = 114
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = PUBLICO 
				*/
				else {
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("31"), new Integer("114"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_CREDITO);
				}
			
				break;
			
				
			case LancamentoTipo.ESGOTO_INT:

				
				/*
				* ETAPA Nº 01
				* Razão Contábil = 12 e Conta Contábil = 111
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				if (etapa == 1){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("111"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_DEBITO);					
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 2, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 02
				* Razão Contábil = 12 e Conta Contábil = 112
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = PUBLICO 
				*/
				else if (etapa == 2){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("112"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_DEBITO);					
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 3, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 03
				* Razão Contábil = 31 e Conta Contábil = 211
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = RESIDENCIAL 
				*/
				else if (etapa == 3){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("31"), new Integer("211"), valorTotalResidencial, null, 
			        null, null, ConstantesSistema.INDICADOR_CREDITO);					
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 4, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 04
				* Razão Contábil = 31 e Conta Contábil = 212
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = COMERCIAL 
				*/
				else if (etapa == 4){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("31"), new Integer("212"), null, valorTotalComercial, 
			        null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 5, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 05
				* Razão Contábil = 31 e Conta Contábil = 213
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = INDUSTRIAL 
				*/
				else if (etapa == 5){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("31"), new Integer("213"), null, null, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_CREDITO);					
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 6, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 06
				* Razão Contábil = 31 e Conta Contábil = 214
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = PUBLICO 
				*/
				else {
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("31"), new Integer("214"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_CREDITO);				
				
				}
				
				break;
				
				
			case LancamentoTipo.FINANCIAMENTOS_INCLUIDOS_CURTO_PRAZO_INT:
				
				
				/*
				* ETAPA Nº 01
				* Razão Contábil = 12 e Conta Contábil = 151
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				if (etapa == 1){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("151"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 2, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 02
				* Razão Contábil = 12 e Conta Contábil = 152
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = PUBLICO 
				*/
				else if (etapa == 2){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("152"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 3, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 03
				* contaContabil = CNCT_ID da tabela LANCAMENTO_ITEM_CONTABIL com LICT_ID = id do ítem de lançamento contábil
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = Valor acumulado para cada ítem de lançamento contábil  
				*/
				else {
					
					this.inserirLancamentoContabilItemAcumulandoItems(lancamentoContabil, 
					colecaoAcumularValoresPorLancamentoItemContabil, ConstantesSistema.INDICADOR_CREDITO);
					
				}

				
				break;
				
				
			case LancamentoTipo.FINANCIAMENTOS_INCLUIDOS_LONGO_PRAZO_INT:
				
				
				/*
				* ETAPA Nº 01
				* Razão Contábil = 13 e Conta Contábil = 151
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				if (etapa == 1){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("13"), new Integer("151"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 2, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 02
				* Razão Contábil = 13 e Conta Contábil = 152
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = PUBLICO 
				*/
				else if (etapa == 2){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("13"), new Integer("152"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_DEBITO);
				
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 3, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 03
				* contaContabil = CNCT_ID da tabela LANCAMENTO_ITEM_CONTABIL com LICT_ID = id do ítem de lançamento contábil
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = Valor acumulado para cada ítem de lançamento contábil  
				*/
				else {
					
					this.inserirLancamentoContabilItemAcumulandoItems(lancamentoContabil, 
					colecaoAcumularValoresPorLancamentoItemContabil, ConstantesSistema.INDICADOR_CREDITO);
					
				}

				
				break;

				
			case LancamentoTipo.FATURAMENTO_ADICIONAL_GUIA_PAGAMENTO_INT:
				
				/*
				* ETAPA Nº 01
				* Razão Contábil = 12 e Conta Contábil = 111
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				if (etapa == 1){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("111"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 2, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 02
				* Razão Contábil = 12 e Conta Contábil = 112
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = PUBLICO 
				*/
				else if (etapa == 2){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("112"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 3, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 03
				* contaContabil = CNCT_ID da tabela LANCAMENTO_ITEM_CONTABIL com LICT_ID = id do ítem de lançamento contábil
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = Valor acumulado para cada ítem de lançamento contábil  
				*/
				else {
					
					this.inserirLancamentoContabilItemAcumulandoItems(lancamentoContabil, 
					colecaoAcumularValoresPorLancamentoItemContabil, ConstantesSistema.INDICADOR_CREDITO);
					
				}
				
				
				break;
				
				
			case LancamentoTipo.FINANCIAMENTOS_CANCELADOS_CURTO_PRAZO_INT:
				
				/*
				* ETAPA Nº 01
				* contaContabil = CNCT_ID da tabela LANCAMENTO_ITEM_CONTABIL com LICT_ID = id do ítem de lançamento contábil
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = Valor acumulado para cada ítem de lançamento contábil  
				*/
				if (etapa == 1){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoItems(lancamentoContabil, 
					colecaoAcumularValoresPorLancamentoItemContabil, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 2, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 02
				* Razão Contábil = 12 e Conta Contábil = 151
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 2){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("151"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 3, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 03
				* Razão Contábil = 12 e Conta Contábil = 152
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = PUBLICO 
				*/
				else {
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("152"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_CREDITO);
					
				}
				
				
				break;
				
				
			case LancamentoTipo.FINANCIAMENTOS_CANCELADOS_LONGO_PRAZO_INT:
				
				/*
				* ETAPA Nº 01
				* contaContabil = CNCT_ID da tabela LANCAMENTO_ITEM_CONTABIL com LICT_ID = id do ítem de lançamento contábil
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = Valor acumulado para cada ítem de lançamento contábil  
				*/
				if (etapa == 1){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoItems(lancamentoContabil, 
					colecaoAcumularValoresPorLancamentoItemContabil, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 2, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 02
				* Razão Contábil = 13 e Conta Contábil = 151
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 2){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("13"), new Integer("151"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 3, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 03
				* Razão Contábil = 13 e Conta Contábil = 152
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = PUBLICO 
				*/
				else {
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("13"), new Integer("152"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_CREDITO);
					
				}
				
				
				break;
	
				
			case LancamentoTipo.CANCELAMENTOS_POR_REFATURAMENTO_INT:
				
				/*
				* ETAPA Nº 01
				* Razão Contábil = 31 e Conta Contábil = 111
				* Indicador Débito/Crédito = Débito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + RESIDENCIAL 
				*/
				if (etapa == 1){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.RESIDENCIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("111"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 2, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 02
				* Razão Contábil = 31 e Conta Contábil = 112
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + COMERCIAL 
				*/
				else if (etapa == 2){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.COMERCIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("112"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 3, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 03
				* Razão Contábil = 31 e Conta Contábil = 113
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + INDUSTRIAL 
				*/
				else if (etapa == 3){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.INDUSTRIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("113"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 4, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 04
				* Razão Contábil = 31 e Conta Contábil = 114
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + PUBLICO 
				*/
				else if (etapa == 4){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("114"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 5, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 05
				* Razão Contábil = 12 e Conta Contábil = 111
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 5){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("111"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 6, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 06
				* Razão Contábil = 12 e Conta Contábil = 112
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + PUBLICO 
				*/
				else if (etapa == 6){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("112"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 7, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 07
				* Razão Contábil = 31 e Conta Contábil = 211
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + RESIDENCIAL 
				*/
				else if (etapa == 7){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("211"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 8, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 08
				* Razão Contábil = 31 e Conta Contábil = 212
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + COMERCIAL 
				*/
				else if (etapa == 8){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.COMERCIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("212"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 9, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 09
				* Razão Contábil = 31 e Conta Contábil = 213
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + INDUSTRIAL 
				*/
				else if (etapa == 9){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.INDUSTRIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("213"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 10, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 10
				* Razão Contábil = 31 e Conta Contábil = 214
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + PUBLICO 
				*/
				else if (etapa == 10){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("214"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 11, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 11
				* Razão Contábil = 12 e Conta Contábil = 111
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 11){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("111"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 12, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 12
				* Razão Contábil = 12 e Conta Contábil = 112
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + PUBLICO 
				*/
				else if (etapa == 12){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("112"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 13, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 13
				* contaContabil = CNCT_ID da tabela LANCAMENTO_ITEM_CONTABIL com LICT_ID = id do ítem de lançamento contábil
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = Valor acumulado para cada ítem de lançamento contábil  
				*/
				else if (etapa == 13){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoItems(lancamentoContabil, 
					colecaoAcumularValoresPorLancamentoItemContabil, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 14, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 14
				* Razão Contábil = 12 e Conta Contábil = 111
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 14){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("111"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 15, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 15
				* Razão Contábil = 12 e Conta Contábil = 112
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = PUBLICO 
				*/
				else {
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("112"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_CREDITO);
				}
				
				break;
				
				
			case LancamentoTipo.PARCELAMENTOS_COBRADOS_SUP_CANCELAMENTOS_POR_REFATURAMENTO_INT:
				
				/*
				* ETAPA Nº 01
				* Razão Contábil = 31 e Conta Contábil = 111
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + RESIDENCIAL 
				*/
				if (etapa == 1){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.RESIDENCIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("111"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 2, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 02
				* Razão Contábil = 31 e Conta Contábil = 112
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + COMERCIAL 
				*/
				else if (etapa == 2){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.COMERCIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("112"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 3, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 03
				* Razão Contábil = 31 e Conta Contábil = 113
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + INDUSTRIAL 
				*/
				else if (etapa == 3){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.INDUSTRIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("113"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 4, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 04
				* Razão Contábil = 31 e Conta Contábil = 114
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + PUBLICO 
				*/
				else if (etapa == 4){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("114"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 5, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 05
				* Razão Contábil = 12 e Conta Contábil = 111
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 5){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("111"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 6, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 06
				* Razão Contábil = 12 e Conta Contábil = 112
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + PUBLICO 
				*/
				else if (etapa == 6){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("112"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 7, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 07
				* Razão Contábil = 31 e Conta Contábil = 211
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + RESIDENCIAL 
				*/
				else if (etapa == 7){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("211"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 8, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 08
				* Razão Contábil = 31 e Conta Contábil = 212
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + COMERCIAL 
				*/
				else if (etapa == 8){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.COMERCIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("212"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 9, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 10
				* Razão Contábil = 31 e Conta Contábil = 214
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + PUBLICO 
				*/
				else if (etapa == 10){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("214"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 11, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 11
				* Razão Contábil = 12 e Conta Contábil = 111
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 11){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("111"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 12, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 12
				* Razão Contábil = 12 e Conta Contábil = 112
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + PUBLICO 
				*/
				else if (etapa == 12){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("112"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 13, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 13
				* contaContabil = CNCT_ID da tabela LANCAMENTO_ITEM_CONTABIL com LICT_ID = id do ítem de lançamento contábil
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = Valor acumulado para cada ítem de lançamento contábil  
				*/
				else if (etapa == 13){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoItems(lancamentoContabil, 
					colecaoAcumularValoresPorLancamentoItemContabil, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 14, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 14
				* Razão Contábil = 12 e Conta Contábil = 111
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 14){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("111"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 15, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 15
				* Razão Contábil = 12 e Conta Contábil = 112
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = PUBLICO 
				*/
				else if (etapa == 15){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("112"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 16, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 16
				* Razão Contábil = 31 e Conta Contábil = 122
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(JUROS) 
				*/
				else if (etapa == 16){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.JUROS);
					
					this.inserirLancamentoContabilItemAcumulandoItemEspecifico(lancamentoContabil, 
			        new Short("31"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria,
			        lancamentoItem, ConstantesSistema.INDICADOR_DEBITO);
					
					
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 17, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 17
				* Razão Contábil = 12 e Conta Contábil = 111
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(JUROS) + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 17){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.JUROS);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("111"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 18, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 18
				* Razão Contábil = 12 e Conta Contábil = 112
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(JUROS) + PUBLICO 
				*/
				else {
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.JUROS);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("112"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
				}
				
				break;
				
				
			case LancamentoTipo.INCLUSOES_POR_REFATURAMENTO_INT:
				
				/*
				* ETAPA Nº 01
				* Razão Contábil = 12 e Conta Contábil = 111
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				if (etapa == 1){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("111"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 2, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 02
				* Razão Contábil = 12 e Conta Contábil = 112
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + PUBLICO 
				*/
				else if (etapa == 2){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("112"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 3, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 03
				* Razão Contábil = 31 e Conta Contábil = 111
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + RESIDENCIAL 
				*/
				else if (etapa == 3){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.RESIDENCIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("111"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 4, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 04
				* Razão Contábil = 31 e Conta Contábil = 112
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + COMERCIAL 
				*/
				else if (etapa == 4){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.COMERCIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("112"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 5, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 05
				* Razão Contábil = 31 e Conta Contábil = 113
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + INDUSTRIAL 
				*/
				else if (etapa == 5){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.INDUSTRIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("113"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 6, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 06
				* Razão Contábil = 31 e Conta Contábil = 114
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + PUBLICO 
				*/
				else if (etapa == 6){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("114"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 7, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 07
				* Razão Contábil = 12 e Conta Contábil = 111
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 7){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("111"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 8, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 08
				* Razão Contábil = 12 e Conta Contábil = 112
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + PUBLICO 
				*/
				else if (etapa == 8){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("112"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 9, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 09
				* Razão Contábil = 31 e Conta Contábil = 211
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + RESIDENCIAL 
				*/
				else if (etapa == 9){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("211"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 10, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 10
				* Razão Contábil = 31 e Conta Contábil = 212
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + COMERCIAL 
				*/
				else if (etapa == 10){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.COMERCIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("212"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 11, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 11
				* Razão Contábil = 31 e Conta Contábil = 213
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + INDUSTRIAL 
				*/
				else if (etapa == 11){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.INDUSTRIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("213"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 12, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 12
				* Razão Contábil = 31 e Conta Contábil = 214
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + PUBLICO 
				*/
				else if (etapa == 12){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("214"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 13, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 13
				* Razão Contábil = 12 e Conta Contábil = 111
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 13){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("111"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 14, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 14
				* Razão Contábil = 12 e Conta Contábil = 112
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = PUBLICO 
				*/
				else if (etapa == 14){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("112"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 15, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 15
				* contaContabil = CNCT_ID da tabela LANCAMENTO_ITEM_CONTABIL com LICT_ID = id do ítem de lançamento contábil
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = Valor acumulado para cada ítem de lançamento contábil  
				*/
				else {
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoItems(lancamentoContabil, 
					colecaoAcumularValoresPorLancamentoItemContabil, ConstantesSistema.INDICADOR_CREDITO);
					
				}
				
				break;
				
				
			case LancamentoTipo.FINANCIAMENTOS_COBRADOS_INT:
				
				/*
				* ETAPA Nº 01
				* Razão Contábil = 12 e Conta Contábil = 111
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				if (etapa == 1){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("111"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 2, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 02
				* Razão Contábil = 12 e Conta Contábil = 112
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = PUBLICO 
				*/
				else if (etapa == 2){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("112"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 3, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 03
				* Razão Contábil = 12 e Conta Contábil = 151
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 3){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("151"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 4, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 04
				* Razão Contábil = 12 e Conta Contábil = 152
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = PUBLICO 
				*/
				else {
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("152"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_CREDITO);
				
				}
				
				break;
				
				
			case LancamentoTipo.FINANCIAMENTOS_TRANSFERIDOS_CURTO_PRAZO_INT:
				
				/*
				* ETAPA Nº 01
				* Razão Contábil = 12 e Conta Contábil = 151
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				if (etapa == 1){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("151"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 2, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 02
				* Razão Contábil = 12 e Conta Contábil = 152
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = PUBLICO 
				*/
				else if (etapa == 2){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("152"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 3, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 03
				* Razão Contábil = 13 e Conta Contábil = 151
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 3){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("13"), new Integer("151"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 4, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 04
				* Razão Contábil = 13 e Conta Contábil = 152
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = PUBLICO 
				*/
				else {
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("13"), new Integer("152"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_CREDITO);
				
				}
				
				break;
				
				
			case LancamentoTipo.PARCELAMENTOS_REALIZADOS_CURTO_PRAZO_INT:
				
				/*
				* ETAPA Nº 01
				* Razão Contábil = 12 e Conta Contábil = 121
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(DOCUMENTOS_EMITIDOS) + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				if (etapa == 1){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.DOCUMENTOS_EMITIDOS);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("121"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 2, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 02
				* Razão Contábil = 12 e Conta Contábil = 122
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(DOCUMENTOS_EMITIDOS) + PUBLICO 
				*/
				else if (etapa == 2){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.DOCUMENTOS_EMITIDOS);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 3, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 03
				* Razão Contábil = 12 e Conta Contábil = 111
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(DOCUMENTOS_EMITIDOS) + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 3){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.DOCUMENTOS_EMITIDOS);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("111"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 4, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 04
				* Razão Contábil = 12 e Conta Contábil = 112
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(DOCUMENTOS_EMITIDOS) + PUBLICO 
				*/
				else if (etapa == 4){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.DOCUMENTOS_EMITIDOS);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("112"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 5, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 05
				* Razão Contábil = 12 e Conta Contábil = 121
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(FINANCIAMENTOS_A_COBRAR_CURTO_PRAZO)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 5){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.FINANCIAMENTOS_A_COBRAR_CURTO_PRAZO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("121"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 6, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 06
				* Razão Contábil = 12 e Conta Contábil = 122
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(FINANCIAMENTOS_A_COBRAR_CURTO_PRAZO) + PUBLICO 
				*/
				else if (etapa == 6){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.FINANCIAMENTOS_A_COBRAR_CURTO_PRAZO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 7, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 07
				* Razão Contábil = 12 e Conta Contábil = 151
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(FINANCIAMENTOS_A_COBRAR_CURTO_PRAZO)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 7){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.FINANCIAMENTOS_A_COBRAR_CURTO_PRAZO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("151"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 8, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 08
				* Razão Contábil = 12 e Conta Contábil = 152
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(FINANCIAMENTOS_A_COBRAR_CURTO_PRAZO) + PUBLICO 
				*/
				else if (etapa == 8){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.FINANCIAMENTOS_A_COBRAR_CURTO_PRAZO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("152"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 9, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 09
				* Razão Contábil = 12 e Conta Contábil = 121
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(FINANCIAMENTOS_A_COBRAR_LONGO_PRAZO)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 9){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.FINANCIAMENTOS_A_COBRAR_LONGO_PRAZO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("121"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 10, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 10
				* Razão Contábil = 12 e Conta Contábil = 122
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(FINANCIAMENTOS_A_COBRAR_LONGO_PRAZO) + PUBLICO 
				*/
				else if (etapa == 10){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.FINANCIAMENTOS_A_COBRAR_LONGO_PRAZO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 11, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 11
				* Razão Contábil = 13 e Conta Contábil = 151
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(FINANCIAMENTOS_A_COBRAR_LONGO_PRAZO)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 11){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.FINANCIAMENTOS_A_COBRAR_LONGO_PRAZO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("151"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 12, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 12
				* Razão Contábil = 13 e Conta Contábil = 152
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(FINANCIAMENTOS_A_COBRAR_LONGO_PRAZO) + PUBLICO 
				*/
				else if (etapa == 12){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.FINANCIAMENTOS_A_COBRAR_LONGO_PRAZO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("152"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 13, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 13
				* Razão Contábil = 12 e Conta Contábil = 121
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(PARCELAMENTO_A_COBRAR_LONGO_PRAZO)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 13){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.PARCELAMENTOS_A_COBRAR_LONGO_PRAZO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("121"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 14, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 14
				* Razão Contábil = 12 e Conta Contábil = 122
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(PARCELAMENTO_A_COBRAR_LONGO_PRAZO) + PUBLICO 
				*/
				else if (etapa == 14){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.PARCELAMENTOS_A_COBRAR_LONGO_PRAZO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 15, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 15
				* Razão Contábil = 13 e Conta Contábil = 121
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(PARCELAMENTO_A_COBRAR_LONGO_PRAZO)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 15){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.PARCELAMENTOS_A_COBRAR_LONGO_PRAZO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("121"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 16, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 16
				* Razão Contábil = 13 e Conta Contábil = 122
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(PARCELAMENTO_A_COBRAR_LONGO_PRAZO) + PUBLICO 
				*/
				else if (etapa == 16){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.PARCELAMENTOS_A_COBRAR_LONGO_PRAZO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 17, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 17
				* Razão Contábil = 12 e Conta Contábil = 121
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(JUROS_COBRADOS)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 17){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.JUROS_COBRADOS);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("121"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 18, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 18
				* Razão Contábil = 12 e Conta Contábil = 122
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(JUROS_COBRADOS) + PUBLICO 
				*/
				else if (etapa == 18){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.JUROS_COBRADOS);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 19, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 19
				* Razão Contábil = 31 e Conta Contábil = 122
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(JUROS_COBRADOS) 
				*/
				else if (etapa == 19){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.JUROS_COBRADOS);
					
					this.inserirLancamentoContabilItemAcumulandoItemEspecifico(lancamentoContabil, 
			        new Short("31"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria,
			        lancamentoItem, ConstantesSistema.INDICADOR_CREDITO);
					
					
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 20, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 20
				* Razão Contábil = 31 e Conta Contábil = 122
				* Indicador Débito/Crédito =  Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(JUROS_CANCELADOS) 
				*/
				else if (etapa == 20){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.JUROS_CANCELADOS);
					
					this.inserirLancamentoContabilItemAcumulandoItemEspecifico(lancamentoContabil, 
			        new Short("31"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria,
			        lancamentoItem, ConstantesSistema.INDICADOR_DEBITO);
					
					
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 21, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 21
				* Razão Contábil = 12 e Conta Contábil = 121
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(JUROS_CANCELADOS)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 21){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.JUROS_CANCELADOS);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("121"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 22, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 23
				* Razão Contábil = 12 e Conta Contábil = 122
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(JUROS_CANCELADOS) + PUBLICO 
				*/
				else if (etapa == 22){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.JUROS_CANCELADOS);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 23, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 23
				* Razão Contábil = 12 e Conta Contábil = 121
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(DEBITOS_ANTERIORES_PARA_RECOBRANCA)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 23){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.DEBITOS_ANTERIORES_PARA_RECOBRANCA);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("121"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 24, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 24
				* Razão Contábil = 12 e Conta Contábil = 122
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(DEBITOS_ANTERIORES_PARA_RECOBRANCA) + PUBLICO 
				*/
				else if (etapa == 24){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.DEBITOS_ANTERIORES_PARA_RECOBRANCA);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 25, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 25
				* Razão Contábil = 12 e Conta Contábil = 111
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(DEBITOS_ANTERIORES_PARA_RECOBRANCA)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 25){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.DEBITOS_ANTERIORES_PARA_RECOBRANCA);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("111"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 26, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 26
				* Razão Contábil = 12 e Conta Contábil = 112
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(DEBITOS_ANTERIORES_PARA_RECOBRANCA) + PUBLICO 
				*/
				else {
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.DEBITOS_ANTERIORES_PARA_RECOBRANCA);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("112"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
				
				}
				
				break;
				
				
			case LancamentoTipo.PARCELAMENTOS_REALIZADOS_LONGO_PRAZO_INT:
				
				/*
				* ETAPA Nº 01
				* Razão Contábil = 13 e Conta Contábil = 121
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(DOCUMENTOS_EMITIDOS)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				if (etapa == 1){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.DOCUMENTOS_EMITIDOS);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("121"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 2, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 02
				* Razão Contábil = 13 e Conta Contábil = 122
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(DOCUMENTOS_EMITIDOS) + PUBLICO 
				*/
				else if (etapa == 2){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.DOCUMENTOS_EMITIDOS);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 3, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 03
				* Razão Contábil = 12 e Conta Contábil = 111
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(DOCUMENTOS_EMITIDOS)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 3){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.DOCUMENTOS_EMITIDOS);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("111"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 4, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 04
				* Razão Contábil = 12 e Conta Contábil = 112
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(DOCUMENTOS_EMITIDOS) + PUBLICO 
				*/
				else if (etapa == 4){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.DOCUMENTOS_EMITIDOS);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("112"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 5, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 05
				* Razão Contábil = 13 e Conta Contábil = 121
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(FINANCIAMENTOS_A_COBRAR_CURTO_PRAZO)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 5){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.FINANCIAMENTOS_A_COBRAR_CURTO_PRAZO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("121"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 6, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 06
				* Razão Contábil = 13 e Conta Contábil = 122
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(FINANCIAMENTOS_A_COBRAR_CURTO_PRAZO) + PUBLICO 
				*/
				else if (etapa == 6){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.FINANCIAMENTOS_A_COBRAR_CURTO_PRAZO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 7, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 07
				* Razão Contábil = 12 e Conta Contábil = 151
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(FINANCIAMENTOS_A_COBRAR_CURTO_PRAZO)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 7){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.FINANCIAMENTOS_A_COBRAR_CURTO_PRAZO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("151"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 8, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 08
				* Razão Contábil = 12 e Conta Contábil = 152
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(FINANCIAMENTOS_A_COBRAR_CURTO_PRAZO) + PUBLICO 
				*/
				else if (etapa == 8){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.FINANCIAMENTOS_A_COBRAR_CURTO_PRAZO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("152"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 9, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 09
				* Razão Contábil = 13 e Conta Contábil = 121
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(FINANCIAMENTOS_A_COBRAR_LONGO_PRAZO)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 9){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.FINANCIAMENTOS_A_COBRAR_LONGO_PRAZO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("121"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 10, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 10
				* Razão Contábil = 13 e Conta Contábil = 122
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(FINANCIAMENTOS_A_COBRAR_LONGO_PRAZO) + PUBLICO 
				*/
				else if (etapa == 10){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.FINANCIAMENTOS_A_COBRAR_LONGO_PRAZO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 11, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 11
				* Razão Contábil = 13 e Conta Contábil = 151
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(FINANCIAMENTOS_A_COBRAR_LONGO_PRAZO)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 11){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.FINANCIAMENTOS_A_COBRAR_LONGO_PRAZO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("151"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 12, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 12
				* Razão Contábil = 13 e Conta Contábil = 152
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(FINANCIAMENTOS_A_COBRAR_LONGO_PRAZO) + PUBLICO 
				*/
				else if (etapa == 12){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.FINANCIAMENTOS_A_COBRAR_LONGO_PRAZO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("152"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 13, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 13
				* Razão Contábil = 13 e Conta Contábil = 121
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(PARCELAMENTO_A_COBRAR_CURTO_PRAZO)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 13){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.PARCELAMENTOS_A_COBRAR_CURTO_PRAZO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("121"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 14, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 14
				* Razão Contábil = 13 e Conta Contábil = 122
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(PARCELAMENTO_A_COBRAR_LONGO_PRAZO) + PUBLICO 
				*/
				else if (etapa == 14){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.PARCELAMENTOS_A_COBRAR_CURTO_PRAZO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 15, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 15
				* Razão Contábil = 12 e Conta Contábil = 121
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(PARCELAMENTO_A_COBRAR_CURTO_PRAZO)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 15){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.PARCELAMENTOS_A_COBRAR_CURTO_PRAZO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("121"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 16, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 16
				* Razão Contábil = 12 e Conta Contábil = 122
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(PARCELAMENTO_A_COBRAR_LONGO_PRAZO) + PUBLICO 
				*/
				else if (etapa == 16){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.PARCELAMENTOS_A_COBRAR_CURTO_PRAZO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 17, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 17
				* Razão Contábil = 13 e Conta Contábil = 121
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(JUROS_COBRADOS)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 17){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.JUROS_COBRADOS);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("121"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 18, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 18
				* Razão Contábil = 13 e Conta Contábil = 122
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(JUROS_COBRADOS) + PUBLICO 
				*/
				else if (etapa == 18){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.JUROS_COBRADOS);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 19, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 19
				* Razão Contábil = 31 e Conta Contábil = 122
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(JUROS_COBRADOS) 
				*/
				else if (etapa == 19){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.JUROS_COBRADOS);
					
					this.inserirLancamentoContabilItemAcumulandoItemEspecifico(lancamentoContabil, 
			        new Short("31"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria,
			        lancamentoItem, ConstantesSistema.INDICADOR_DEBITO);
					
					
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 20, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 20
				* Razão Contábil = 31 e Conta Contábil = 122
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(JUROS_CANCELADOS) 
				*/
				else if (etapa == 20){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.JUROS_CANCELADOS);
					
					this.inserirLancamentoContabilItemAcumulandoItemEspecifico(lancamentoContabil, 
			        new Short("31"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria,
			        lancamentoItem, ConstantesSistema.INDICADOR_DEBITO);
					
					
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 21, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 21
				* Razão Contábil = 13 e Conta Contábil = 121
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(JUROS_CANCELADOS)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 21){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.JUROS_CANCELADOS);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("121"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 22, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 22
				* Razão Contábil = 13 e Conta Contábil = 122
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(JUROS_CANCELADOS) + PUBLICO 
				*/
				else {
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.JUROS_COBRADOS);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
				
				}
				
				break;
				
				
			case LancamentoTipo.PARCELAMENTOS_CANCELADOS_CURTO_PRAZO_INT:
				
				/*
				* ETAPA Nº 01
				* Razão Contábil = 31 e Conta Contábil = 111
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + RESIDENCIAL 
				*/
				if (etapa == 1){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.RESIDENCIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("111"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 2, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 02
				* Razão Contábil = 31 e Conta Contábil = 112
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + COMERCIAL 
				*/
				else if (etapa == 2){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.COMERCIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("112"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 3, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 03
				* Razão Contábil = 31 e Conta Contábil = 113
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + INDUSTRIAL 
				*/
				else if (etapa == 3){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.INDUSTRIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("113"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 4, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 04
				* Razão Contábil = 31 e Conta Contábil = 114
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + PUBLICO 
				*/
				else if (etapa == 4){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("114"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 5, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 05
				* Razão Contábil = 12 e Conta Contábil = 121
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 5){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("121"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 6, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 06
				* Razão Contábil = 12 e Conta Contábil = 122
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + PUBLICO 
				*/
				else if (etapa == 6){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 7, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 07
				* Razão Contábil = 31 e Conta Contábil = 211
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + RESIDENCIAL 
				*/
				else if (etapa == 7){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("211"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 8, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 08
				* Razão Contábil = 31 e Conta Contábil = 212
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + COMERCIAL 
				*/
				else if (etapa == 8){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.COMERCIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("212"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 9, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 09
				* Razão Contábil = 31 e Conta Contábil = 213
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + INDUSTRIAL 
				*/
				else if (etapa == 9){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.INDUSTRIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("213"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 10, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 10
				* Razão Contábil = 31 e Conta Contábil = 214
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + PUBLICO 
				*/
				else if (etapa == 10){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("214"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 11, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 11
				* Razão Contábil = 12 e Conta Contábil = 121
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 11){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("121"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 12, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 12
				* Razão Contábil = 12 e Conta Contábil = 122
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + PUBLICO 
				*/
				else if (etapa == 12){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 13, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 13
				* contaContabil = CNCT_ID da tabela LANCAMENTO_ITEM_CONTABIL com LICT_ID = id do ítem de lançamento contábil
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = Valor acumulado para cada ítem de lançamento contábil  
				*/
				else if (etapa == 13){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoItems(lancamentoContabil, 
					colecaoAcumularValoresPorLancamentoItemContabil, ConstantesSistema.INDICADOR_DEBITO);
					
					
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 14, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
					
				}
				
				/*
				* ETAPA Nº 14
				* Razão Contábil = 12 e Conta Contábil = 121
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 14){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("121"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 15, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 15
				* Razão Contábil = 12 e Conta Contábil = 122
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = PUBLICO 
				*/
				else if (etapa == 15){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("122"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_CREDITO);
					
					
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 16, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil, 
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 16
				* Razão Contábil = 31 e Conta Contábil = 122
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(JUROS) 
				*/
				else if (etapa == 16){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.JUROS);
					
					this.inserirLancamentoContabilItemAcumulandoItemEspecifico(lancamentoContabil, 
			        new Short("31"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria,
			        lancamentoItem, ConstantesSistema.INDICADOR_DEBITO);
					
					
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 17, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 17
				* Razão Contábil = 12 e Conta Contábil = 121
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(JUROS)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 17){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.JUROS);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("121"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 18, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 18
				* Razão Contábil = 12 e Conta Contábil = 122
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(JUROS) + PUBLICO 
				*/
				else {
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.JUROS);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("12"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
				
				}
				
				
				break;
				
				
			case LancamentoTipo.PARCELAMENTOS_CANCELADOS_LONGO_PRAZO_INT:
				
				/*
				* ETAPA Nº 01
				* Razão Contábil = 31 e Conta Contábil = 111
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + RESIDENCIAL 
				*/
				if (etapa == 1) {
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.RESIDENCIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("111"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
				
					
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 2, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 02
				* Razão Contábil = 31 e Conta Contábil = 112
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + COMERCIAL 
				*/
				else if (etapa == 2) {
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.COMERCIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("112"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
				
					
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 3, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 03
				* Razão Contábil = 31 e Conta Contábil = 113
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + INSDUSTRIAL 
				*/
				else if (etapa == 3) {
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.INDUSTRIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("113"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
				
					
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 4, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 04
				* Razão Contábil = 31 e Conta Contábil = 114
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + PUBLICO 
				*/
				else if (etapa == 4) {
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("114"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
				
					
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 5, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 05
				* Razão Contábil = 13 e Conta Contábil = 121
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 5){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("121"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 6, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 06
				* Razão Contábil = 13 e Conta Contábil = 122
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(AGUA) + PUBLICO 
				*/
				else if (etapa == 6) {
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.AGUA);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
				
					
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 7, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 07
				* Razão Contábil = 31 e Conta Contábil = 211
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + RESIDENCIAL 
				*/
				else if (etapa == 7) {
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("211"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
				
					
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 8, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 08
				* Razão Contábil = 31 e Conta Contábil = 212
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + COMERCIAL 
				*/
				else if (etapa == 8) {
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.COMERCIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("212"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
				
					
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 9, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 09
				* Razão Contábil = 31 e Conta Contábil = 213
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + INDUSTRIAL 
				*/
				else if (etapa == 9) {
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.INDUSTRIAL);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("213"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
				
					
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 10, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 10
				* Razão Contábil = 31 e Conta Contábil = 214
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + PUBLICO 
				*/
				else if (etapa == 10) {
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("31"), new Integer("214"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_DEBITO);
				
					
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 11, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 11
				* Razão Contábil = 13 e Conta Contábil = 121
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 11){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("121"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 12, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 12
				* Razão Contábil = 13 e Conta Contábil = 122
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(ESGOTO) + PUBLICO 
				*/
				else if (etapa == 12) {
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.ESGOTO);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
				
					
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 13, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 13
				* contaContabil = CNCT_ID da tabela LANCAMENTO_ITEM_CONTABIL com LICT_ID = id do ítem de lançamento contábil
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = Valor acumulado para cada ítem de lançamento contábil  
				*/
				else if (etapa == 13){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoItems(lancamentoContabil, 
					colecaoAcumularValoresPorLancamentoItemContabil, ConstantesSistema.INDICADOR_DEBITO);
					
					
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 14, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
					
				}
				
				/*
				* ETAPA Nº 14
				* Razão Contábil = 13 e Conta Contábil = 121
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 14){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("13"), new Integer("121"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 15, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 15
				* Razão Contábil = 12 e Conta Contábil = 122
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = PUBLICO 
				*/
				else if (etapa == 15){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("122"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_CREDITO);
					
					
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 16, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil, 
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 16
				* Razão Contábil = 31 e Conta Contábil = 122
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = LANCAMENTO_ITEM(JUROS) 
				*/
				else if (etapa == 16){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.JUROS);
					
					this.inserirLancamentoContabilItemAcumulandoItemEspecifico(lancamentoContabil, 
			        new Short("31"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria,
			        lancamentoItem, ConstantesSistema.INDICADOR_DEBITO);
					
					
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 17, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 17
				* Razão Contábil = 13 e Conta Contábil = 121
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(JUROS)
				*  + RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 17){
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.JUROS);
					categoria.setId(Categoria.RESIDENCIAL);
					
					//Para os casos que trabalham com mais de uma categoria
					Categoria categoriaAuxilar1 = new Categoria();
					categoriaAuxilar1.setId(Categoria.COMERCIAL);
					
					Categoria categoriaAuxilar2 = new Categoria();
					categoriaAuxilar2.setId(Categoria.INDUSTRIAL);
					
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("121"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, categoriaAuxilar1, categoriaAuxilar2, 
					ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 18, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 18
				* Razão Contábil = 13 e Conta Contábil = 122
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = LANCAMENTO_ITEM(JUROS) + PUBLICO 
				*/
				else {
					
					//Inseri o registro de acordo com os dados passados
					lancamentoItem.setId(LancamentoItem.JUROS);
					categoria.setId(Categoria.PUBLICO);
					
					this.inserirLancamentoContabilItemAcumulandoItemsCategorias(lancamentoContabil, 
					new Short("13"), new Integer("122"), colecaoAcumularValoresPorLancamentoItemCategoria, 
					lancamentoItem, categoria, null, null, ConstantesSistema.INDICADOR_CREDITO);
				
				}
				
				
				break;
				
				
			case LancamentoTipo.PARCELAMENTOS_COBRADOS_INT:
				
				/*
				* ETAPA Nº 01
				* Razão Contábil = 12 e Conta Contábil = 111
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				if (etapa == 1){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("111"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 2, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 02
				* Razão Contábil = 12 e Conta Contábil = 112
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = PUBLICO 
				*/
				else if (etapa == 2){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("112"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_DEBITO);
					
					
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 3, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil, 
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 03
				* Razão Contábil = 12 e Conta Contábil = 121
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 3){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("121"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 4, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 04
				* Razão Contábil = 12 e Conta Contábil = 122
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = PUBLICO 
				*/
				else {
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("122"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_CREDITO);
					
				}
				
				break;
				
				
			case LancamentoTipo.PARCELAMENTOS_TRASFERIDOS_PARA_CURTO_PRAZO_INT:
				
				/*
				* ETAPA Nº 01
				* Razão Contábil = 12 e Conta Contábil = 121
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				if (etapa == 1){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("121"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 2, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 02
				* Razão Contábil = 12 e Conta Contábil = 122
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = PUBLICO 
				*/
				else if (etapa == 2){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("122"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_DEBITO);
					
					
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 3, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil, 
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 03
				* Razão Contábil = 13 e Conta Contábil = 121
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 3){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("13"), new Integer("121"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 4, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 04
				* Razão Contábil = 13 e Conta Contábil = 122
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = PUBLICO 
				*/
				else {
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("13"), new Integer("122"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_CREDITO);
					
				}
				
				break;
				
				
			case LancamentoTipo.DEBITOS_ANTERIORES_COBRADOS_INT:
				
				/*
				* ETAPA Nº 01
				* Razão Contábil = 12 e Conta Contábil = 111
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				if (etapa == 1){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("111"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_DEBITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 2, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 02
				* Razão Contábil = 12 e Conta Contábil = 112
				* Indicador Débito/Crédito = Débito (2)
				* ValorItemFaturamento = PUBLICO 
				*/
				else if (etapa == 2){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("112"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_DEBITO);
					
					
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 3, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil, 
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 03
				* Razão Contábil = 12 e Conta Contábil = 121
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = RESIDENCIAL + COMERCIAL + INDUSTRIAL 
				*/
				else if (etapa == 3){
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("121"), valorTotalResidencial, valorTotalComercial, 
			        valorTotalIndustrial, null, ConstantesSistema.INDICADOR_CREDITO);
					
						
					//Direciona para próxima ETAPA
					this.inserirLancamentoContabilItem(lancamentoContabil, 4, valorTotalResidencial,
					valorTotalComercial, valorTotalIndustrial, valorTotalPublico, 
					colecaoAcumularValoresPorLancamentoItemContabil,
					colecaoAcumularValoresPorLancamentoItemCategoria);
				}
				
				/*
				* ETAPA Nº 04
				* Razão Contábil = 12 e Conta Contábil = 122
				* Indicador Débito/Crédito = Crédito (1)
				* ValorItemFaturamento = PUBLICO 
				*/
				else {
					
					//Inseri o registro de acordo com os dados passados
					this.inserirLancamentoContabilItemAcumulandoCategoria(lancamentoContabil, 
			        new Short("12"), new Integer("122"), null, null, 
			        null, valorTotalPublico, ConstantesSistema.INDICADOR_CREDITO);
					
				}
				
				break;
				
				
			default:
			
				break;
		}
	}
	
	
	
	/**
	 * [UC00175] - Gerar Lançamentos Contábeis
	 * Author: Raphael Rossiter
	 * Data: 17/01/2006
	 * 
	 * Auxilia no cadastramento de um objeto do tipo LANCAMENTO_CONTABIL_ITEM quando a situação
	 * exige que o valor do faturamento seja acumulado por CATEGORIA.
	 *
	 * @param lancamentoContabil
	 * @param numeroRazao
	 * @param numeroConta
	 * @param valorTotalResidencial
	 * @param valorTotalComercial
	 * @param valorTotalIndustrial
	 * @param valorTotalPublico
	 * @param indicadorDebitoCredito
	 * @throws ControladorException
	 */
	protected void inserirLancamentoContabilItemAcumulandoCategoria(LancamentoContabil lancamentoContabil, 
			Short numeroRazao, Integer numeroConta, BigDecimal valorTotalResidencial, BigDecimal valorTotalComercial, 
			BigDecimal valorTotalIndustrial, BigDecimal valorTotalPublico, Short indicadorDebitoCredito) throws ControladorException {
		
		LancamentoContabilItem lancamentoContabilItem = new LancamentoContabilItem();
		lancamentoContabilItem.setLancamentoContabil(lancamentoContabil);
		
		ContaContabil contaContabil = null;
		BigDecimal valorItemFaturamento = new BigDecimal("0.00");
		
		try {

			contaContabil = repositorioFinanceiro.obterContaContabil(numeroRazao, numeroConta);
			
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
		
		if (valorTotalResidencial != null){
			valorItemFaturamento = valorItemFaturamento.add(valorTotalResidencial);
		}
		
		if (valorTotalComercial != null){
			valorItemFaturamento = valorItemFaturamento.add(valorTotalComercial);
		}
		
		if (valorTotalIndustrial != null){
			valorItemFaturamento = valorItemFaturamento.add(valorTotalIndustrial);
		}
		
		if (valorTotalPublico != null){
			valorItemFaturamento = valorItemFaturamento.add(valorTotalPublico);
		}
		
		
		lancamentoContabilItem.setContaContabil(contaContabil);
		lancamentoContabilItem.setValorLancamento(valorItemFaturamento);
		lancamentoContabilItem.setIndicadorDebitoCredito(indicadorDebitoCredito);
	
	
		this.getControladorUtil().inserir(lancamentoContabilItem);

	}
	
	
	
	/**
	 * [UC00175] - Gerar Lançamentos Contábeis
	 * Author: Raphael Rossiter
	 * Data: 17/01/2006
	 * 
	 * Auxilia no cadastramento de um ou vários objetos do tipo LANCAMENTO_CONTABIL_ITEM quando a situação
	 * exige que o valor do faturamento seja acumulado por LANCAMENTO_ITEM_CONTABIL.
	 * 
	 * @param lancamentoContabil
	 * @param colecaoAcumularValoresPorLancamentoItemContabil
	 * @param indicadorCreditoDebito
	 * @throws ControladorException
	 */
	protected void inserirLancamentoContabilItemAcumulandoItems(LancamentoContabil lancamentoContabil, 
			Collection<AcumularValoresHelper> colecaoAcumularValoresPorLancamentoItemContabil, 
			Short indicadorDebitoCredito) throws ControladorException {
		
		LancamentoContabilItem lancamentoContabilItem = new LancamentoContabilItem();
		lancamentoContabilItem.setLancamentoContabil(lancamentoContabil);
		
		ContaContabil contaContabil = null;
		
		Iterator colecaoAcumularValoresPorLancamentoItemContabilIt = 
		colecaoAcumularValoresPorLancamentoItemContabil.iterator();
			
		AcumularValoresHelper acumularValoresPorLancamentoItemContabil = null;
			
		LancamentoItemContabil lancamentoItemContabil = new LancamentoItemContabil();
			
		while (colecaoAcumularValoresPorLancamentoItemContabilIt.hasNext()){
			
			acumularValoresPorLancamentoItemContabil = (AcumularValoresHelper)
			colecaoAcumularValoresPorLancamentoItemContabilIt.next();
				
			lancamentoItemContabil.setId(acumularValoresPorLancamentoItemContabil
			.getIdLancamentoItemContabil());
				
			try {

				contaContabil = repositorioFinanceiro.obterContaContabil(lancamentoItemContabil);
					
			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", ex);
			}
				
			lancamentoContabilItem.setContaContabil(contaContabil);
			lancamentoContabilItem.setValorLancamento(acumularValoresPorLancamentoItemContabil
			.getValorItemFaturamento());
			lancamentoContabilItem.setIndicadorDebitoCredito(indicadorDebitoCredito);
				
				
			this.getControladorUtil().inserir(lancamentoContabilItem);
		}
	}
	
	
	
	/**
	 * 
	 * @param lancamentoContabil
	 * @param numeroRazao
	 * @param numeroConta
	 * @param colecaoAcumularValoresPorLancamentoItemContabilCategoria
	 * @param lancamentoItem
	 * @param categoria1
	 * @param categoria2
	 * @param categoria3
	 * @param indicadorDebitoCredito
	 * @throws ControladorException
	 */
	protected void inserirLancamentoContabilItemAcumulandoItemsCategorias(LancamentoContabil lancamentoContabil, 
			Short numeroRazao, Integer numeroConta, Collection<AcumularValoresHelper> 
			colecaoAcumularValoresPorLancamentoItemContabilCategoria, 
			LancamentoItem lancamentoItem, Categoria categoria1, Categoria categoria2,
			Categoria categoria3, Short indicadorDebitoCredito) 
			throws ControladorException {
		
		LancamentoContabilItem lancamentoContabilItem = new LancamentoContabilItem();
		lancamentoContabilItem.setLancamentoContabil(lancamentoContabil);
		
		ContaContabil contaContabil = null;
		
		try {

			contaContabil = repositorioFinanceiro.obterContaContabil(numeroRazao, numeroConta);
				
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
		
		Iterator colecaoAcumularValoresPorLancamentoItemContabilCategoriaIt = 
		colecaoAcumularValoresPorLancamentoItemContabilCategoria.iterator();
			
		AcumularValoresHelper acumularValoresPorLancamentoItemContabilCategoria = null;

		//Para os casos que trabalham com mais de uma categoria
		BigDecimal valorAcumuladoLancamentoItemCategoria = new BigDecimal("0.00");
		
		while (colecaoAcumularValoresPorLancamentoItemContabilCategoriaIt.hasNext()){
			
			acumularValoresPorLancamentoItemContabilCategoria = (AcumularValoresHelper)
			colecaoAcumularValoresPorLancamentoItemContabilCategoriaIt.next();
				
			
			//Para acumular com três categorias
			if (categoria2 != null && categoria3 != null){

				if (acumularValoresPorLancamentoItemContabilCategoria.getIdLancamentoItem().equals(
					lancamentoItem.getId()) && 
					(acumularValoresPorLancamentoItemContabilCategoria.getIdCategoria().equals(
					categoria1.getId()) || 
					acumularValoresPorLancamentoItemContabilCategoria.getIdCategoria().equals(
					categoria2.getId()) || 
					acumularValoresPorLancamentoItemContabilCategoria.getIdCategoria().equals(
					categoria3.getId()))){
					
					
					valorAcumuladoLancamentoItemCategoria.add(acumularValoresPorLancamentoItemContabilCategoria
					.getValorItemFaturamento());
				
				}
			}
			//Para acumular com apenas uma categoria
			else{
			
				if (acumularValoresPorLancamentoItemContabilCategoria.getIdLancamentoItem().equals(
					lancamentoItem.getId()) && 
					acumularValoresPorLancamentoItemContabilCategoria.getIdCategoria().equals(
					categoria1.getId())){
						
					lancamentoContabilItem.setContaContabil(contaContabil);
					lancamentoContabilItem.setValorLancamento(acumularValoresPorLancamentoItemContabilCategoria
					.getValorItemFaturamento());
					lancamentoContabilItem.setIndicadorDebitoCredito(indicadorDebitoCredito);
						
					this.getControladorUtil().inserir(lancamentoContabilItem);
						
					break;
						
				}
			}
		}
		
		
		//Para os casos que trabalham com mais de uma categoria
		if (categoria2 != null && categoria3 != null){

			lancamentoContabilItem.setContaContabil(contaContabil);
			lancamentoContabilItem.setValorLancamento(valorAcumuladoLancamentoItemCategoria);
			lancamentoContabilItem.setIndicadorDebitoCredito(indicadorDebitoCredito);
				
			this.getControladorUtil().inserir(lancamentoContabilItem);
		}
	}
	
	
	
	protected void inserirLancamentoContabilItemAcumulandoItemEspecifico(LancamentoContabil lancamentoContabil, 
			Short numeroRazao, Integer numeroConta, Collection<AcumularValoresHelper> 
			colecaoAcumularValoresPorLancamentoItemContabil, LancamentoItem lancamentoItem, 
			Short indicadorDebitoCredito) throws ControladorException {
		
		LancamentoContabilItem lancamentoContabilItem = new LancamentoContabilItem();
		lancamentoContabilItem.setLancamentoContabil(lancamentoContabil);
		
		ContaContabil contaContabil = null;
		
		try {

			contaContabil = repositorioFinanceiro.obterContaContabil(numeroRazao, numeroConta);
				
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

		
		Iterator colecaoAcumularValoresPorLancamentoItemContabilIt = 
		colecaoAcumularValoresPorLancamentoItemContabil.iterator();
			
		AcumularValoresHelper acumularValoresPorLancamentoItemContabil = null;
			
		BigDecimal valorItemFaturamento = new BigDecimal("0.00");
		
			
		while (colecaoAcumularValoresPorLancamentoItemContabilIt.hasNext()){
			
			acumularValoresPorLancamentoItemContabil = (AcumularValoresHelper)
			colecaoAcumularValoresPorLancamentoItemContabilIt.next();
				
			if (acumularValoresPorLancamentoItemContabil.getIdLancamentoItem().equals(
				lancamentoItem.getId())){
			
				valorItemFaturamento.add(acumularValoresPorLancamentoItemContabil
				.getValorItemFaturamento());
				
			}
		}
		
		
		if (!valorItemFaturamento.equals(new BigDecimal("0.00"))){
		
			lancamentoContabilItem.setContaContabil(contaContabil);
			lancamentoContabilItem.setValorLancamento(valorItemFaturamento);
			lancamentoContabilItem.setIndicadorDebitoCredito(indicadorDebitoCredito);
			
			this.getControladorUtil().inserir(lancamentoContabilItem);
			
		}
		
	}

	/**
	 * Pesquisa uma coleção de lançamento de item contábil
	 * 	 
	 * @return Coleção de Lançamentos de Item Contábil 
	 * @exception ErroRepositorioException   Erro no hibernate
	 */
	public Collection<LancamentoItemContabil> pesquisarLancamentoItemContabil() throws ControladorException{
		try{
			//pesquisa os lançamentos de item contábil existentes no sisitema
			return repositorioLancamentoItemContabil.pesquisarLancamentoItemContabil();
			
				//erro no hibernate
			} catch (ErroRepositorioException ex) {
				
				//levanta a exceção para a próxima camada
				throw new ControladorException("erro.sistema", ex);
			}		
	}
	
	/**
	 * Gera Lançamentos Contábeis do Faturamento
	 *
	 * [UC000348] - Gerar Lançamento Contábeis da Arrecadação
	 *
	 * @author Rafael Santos, Pedro Alexandre
	 * @date 22/05/2006, 25/05/2007
	 *
	 * @param anoMesArrecadacao
	 * @throws ControladorException 
	 */
	public void gerarLancamentoContabeisArrecadacao(Integer anoMesReferenciaArrecadacao, Integer idLocalidade, int idFuncionalidadeIniciada) throws ControladorException {
		
		
		int idUnidadeIniciada = 0;

		/*
		 * Registrar o início do processamento da Unidade de
		 * Processamento do Batch
		 * 
		*/
		idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,UnidadeProcessamento.LOCALIDADE,(idLocalidade));
		
		try {
			Integer anoMesArrecadacaoAtual =  getControladorUtil().pesquisarParametrosDoSistema().getAnoMesArrecadacao();
			if(anoMesReferenciaArrecadacao.intValue() >anoMesArrecadacaoAtual.intValue()){
				//levanta a exceção para a próxima camada
				throw new ControladorException("atencao.mes_ano.arrecadacao.inferior",null,Util.formatarAnoMesParaMesAno(anoMesArrecadacaoAtual.toString()));
			}
			
			repositorioFinanceiro.removerLancamentosContabeisEItens(idLocalidade, anoMesReferenciaArrecadacao, LancamentoOrigem.ARRECADACAO);
			
			/*
			 *  Pesquisa os dados do resumo da arrecadação para o ano/mês de referência atual e 
			 *  para a localidade informada.
			 *  
			 *  0 - id da localidade
			 *  1 - id do tipo de recebimento
			 *  2 - id do tipo de lançamento
			 *  3 - id do item de lançamento
			 *  4 - id do item de lançamento contábil
			 *  5 - id da categoria
			 *  6 - soma do valor do resumo da arrecadação 
			 */
			Collection<Object[]> colecaoDadosResumoArrecadacao = repositorioFinanceiro.obterDadosResumoArrecadacao(anoMesReferenciaArrecadacao, idLocalidade);
		
			/*
			 * Caso exista resumo da arrecadação para a localidade e o ano/mês 
			 * cria o lançamento contábil junto com seus items 
			 * para cada conjunto de mesmo tipo de lançamento
			 */
			if (colecaoDadosResumoArrecadacao != null && !colecaoDadosResumoArrecadacao.isEmpty()){
				
				//flag utilizada somente quando for a primeira entrada
				boolean flagPrimeiraVez = true;
				int idTipoLancamentoTemp = -1;
				Collection<Object[]> colecaoDadosResumoPorTipoLancamento = new ArrayList();
				
				// definição da origem do lançamento
				LancamentoOrigem lancamentoOrigem = new LancamentoOrigem();
				lancamentoOrigem.setId(LancamentoOrigem.ARRECADACAO);
				
				//Cria a variável que vai armazenar o lançamento contábil
				LancamentoContabil lancamentoContabilInsert = null;
				
				//laço para gerar os lançamentos por grupo de tipo de lançamento
				for(Object[] dadosResumoArrecadacao : colecaoDadosResumoArrecadacao){
					
					//recupera o id do tipo de recebimento
					Integer idRecebimentoTipo = (Integer) dadosResumoArrecadacao[1];
					
					//recupera o tipo de lançamento atual 
					Integer idTipoLancamento = (Integer) dadosResumoArrecadacao[2];
					
					/*
					 * Caso seja a primeira entrada do "for"
					 * adiciona os dados a coleção e atualiza o item temporario
					 * criando também o lançamento contabil que ira ser inserindo
					 * junto com seus items.
					 * 
					 *  Caso contrário (não seja a primeira entrada do laço "for")
					 *  verifica se o item de lançamento mudou, caso não tenha mudado 
					 *  adiciona os dados ao conjunto do mesmo item
					 *  caso contrário, se mudou o item de lançamento o conjunto está fechado
					 *  para o lançamento contábil e chama o método para inserir o
					 *  lançamento contábil junto com seus itens. 
					 */
					if(flagPrimeiraVez){
						colecaoDadosResumoPorTipoLancamento.add(dadosResumoArrecadacao);
						flagPrimeiraVez = false;
						idTipoLancamentoTemp = idTipoLancamento;
						
						RecebimentoTipo recebimentoTipo = new RecebimentoTipo();
						recebimentoTipo.setId(idRecebimentoTipo);
						
						LancamentoTipo tipoLancamento = new LancamentoTipo();
						tipoLancamento.setId(idTipoLancamento);
						
						Localidade localidade = new Localidade();
						localidade.setId(idLocalidade);
						
						//cri o lançamento contábil que vai ser inserido
						lancamentoContabilInsert = new LancamentoContabil();
						lancamentoContabilInsert.setAnoMes(anoMesReferenciaArrecadacao);
						lancamentoContabilInsert.setLancamentoOrigem(lancamentoOrigem);
						lancamentoContabilInsert.setLancamentoTipo(tipoLancamento);
						lancamentoContabilInsert.setLocalidade(localidade);
						lancamentoContabilInsert.setRecebimentoTipo(recebimentoTipo);
						lancamentoContabilInsert.setUltimaAlteracao(new Date());
					}else{
						/*
						 * Caso ainda seja o mesmo item adicona os dados para 
						 * ser gerado os itens do lançamento para o mesmo lançamento.
						 * Caso contrário chama o metódo para inseri os itens e o lançamento contábil.
						 */
						if(idTipoLancamento == idTipoLancamentoTemp){
							colecaoDadosResumoPorTipoLancamento.add(dadosResumoArrecadacao);
						}else{
							/* metódo para inserir o lançamento contábil assim como seus itens */
							this.inserirLancamentoContabilItemArrecadacao(lancamentoContabilInsert, colecaoDadosResumoPorTipoLancamento);
							
							//limpaa coleção e adiciona os dados do resumo atual
							colecaoDadosResumoPorTipoLancamento.clear();
							colecaoDadosResumoPorTipoLancamento.add(dadosResumoArrecadacao);
							
							RecebimentoTipo recebimentoTipo = new RecebimentoTipo();
							recebimentoTipo.setId(idRecebimentoTipo);
							
							LancamentoTipo tipoLancamento = new LancamentoTipo();
							tipoLancamento.setId(idTipoLancamento);
							
							Localidade localidade = new Localidade();
							localidade.setId(idLocalidade);
							
							//cria o lançamento contábil que vai ser inserido
							lancamentoContabilInsert = new LancamentoContabil();
							lancamentoContabilInsert.setAnoMes(anoMesReferenciaArrecadacao);
							lancamentoContabilInsert.setLancamentoOrigem(lancamentoOrigem);
							lancamentoContabilInsert.setLancamentoTipo(tipoLancamento);
							lancamentoContabilInsert.setLocalidade(localidade);
							lancamentoContabilInsert.setRecebimentoTipo(recebimentoTipo);
							lancamentoContabilInsert.setUltimaAlteracao(new Date());
							
							//atualiza o tipo de lançamento temporário com o novo valor
							idTipoLancamentoTemp = idTipoLancamento;
						}
					}
				}
				
				/*
				 * Último registro
				 * Esse "if" é para verificar se ainda existe um último registro na coleção
				 * caso exista algum item, adiciona o lançamento contábil junto com o item. 
				 */
				if(colecaoDadosResumoPorTipoLancamento != null && colecaoDadosResumoPorTipoLancamento.size() > 0 ){
					this.inserirLancamentoContabilItemArrecadacao(lancamentoContabilInsert, colecaoDadosResumoPorTipoLancamento);
					colecaoDadosResumoPorTipoLancamento = null;
				}
			}
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);
		
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}
	
	
	/**
	 * Este metodo é utilizado para pesquisar os registros q serão
	 * usados para contrução do txt do caso de uso
	 *
	 * [UC0469] Gerar Integração para a Contabilidade
	 *
	 * @author Pedro Alexandre
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

			colecaoObjetoGerar = repositorioFinanceiro.pesquisarGerarIntegracaoContabilidade(idLancamentoOrigem, anoMes);
			
			if(!colecaoObjetoGerar.isEmpty()){
				Iterator iteratorPesquisa = colecaoObjetoGerar.iterator();
				
				colecaoGerarIntegracaoContabilidade = new ArrayList();
				GerarIntegracaoContabilidadeHelper gerarIntegracaoContabilidadeHelper = null;
				Object[] objetoGerar = null;
				
				while(iteratorPesquisa.hasNext()){
					gerarIntegracaoContabilidadeHelper = new GerarIntegracaoContabilidadeHelper();
					
					objetoGerar = (Object[]) iteratorPesquisa.next();
					
					//numero cartao
					if(objetoGerar[0] != null){
						gerarIntegracaoContabilidadeHelper.setNumeroCartao((Short)objetoGerar[0]);
					}

					//lancamento tipo
					if(objetoGerar[1] != null){
						gerarIntegracaoContabilidadeHelper.setIdTipoLancamento((Integer) objetoGerar[1]);
					}
					
					//folha
					if(objetoGerar[2] != null){
						gerarIntegracaoContabilidadeHelper.setFolha((Integer) objetoGerar[2]);
					}
					
					//linha
					if(objetoGerar[3] != null){
						gerarIntegracaoContabilidadeHelper.setIndicadorLinha((Integer)objetoGerar[3]);
					}
					
					//prefixo contabil
					if(objetoGerar[4] != null){
						gerarIntegracaoContabilidadeHelper.setNumeroPrefixoContabil((String) objetoGerar[4]);
					}
					
					//conta
					if(objetoGerar[5] != null){
						gerarIntegracaoContabilidadeHelper.setCont((Integer) objetoGerar[5]);
					}
					
					//analise
					if(objetoGerar[6] != null){
						gerarIntegracaoContabilidadeHelper.setAnalise((Integer) objetoGerar[6]);
					}
					
					//digito
					if(objetoGerar[7] != null){
						gerarIntegracaoContabilidadeHelper.setDigito((Integer) objetoGerar[7]);
					}
					
					//terceiros
					if(objetoGerar[8] != null){
						gerarIntegracaoContabilidadeHelper.setTerceiros((Integer) objetoGerar[8]);
					}
					
					//referencia
					if(objetoGerar[9] != null){
						gerarIntegracaoContabilidadeHelper.setReferencial((Integer) objetoGerar[9]);
					}
					
					//valor lancamento
					if(objetoGerar[10] != null){
						gerarIntegracaoContabilidadeHelper.setValorLancamento((BigDecimal)objetoGerar[10]);
					}
					
					//indicador debito credito
					if(objetoGerar[11] != null){
						gerarIntegracaoContabilidadeHelper.setIndicadorDebitoConta((Integer) objetoGerar[11]);
					}
					
					//indicador debito credito
					if(objetoGerar[12] != null){
						gerarIntegracaoContabilidadeHelper.setCartao2((Integer) objetoGerar[12]);
					}
					
					//id localidade
					if(objetoGerar[13] != null){
						gerarIntegracaoContabilidadeHelper.setIdLocalidade((Integer) objetoGerar[13]);
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
	 * [UC0469] Gerar Integração para a Contabilidade
	 * 
	 * [SB0005] Realizar integração com sistema ALPHA para COMPESA
	 *
	 * @author Rafael Pinto
	 * @date 19/10/2011
	 *
	 * @param colecaoDadosGerarIntegracao
	 * @param dataLancamento
	 * @param idLancamentoOrigem
	 * 
	 * @throws ControladorException
	 */
	private void enviarDadosParaSistemaIFS(Collection<Object[]> colecaoDadosGerarIntegracao,
		String dataLancamento,String idLancamentoOrigem, String descricaoLancamentoOrigem) throws ControladorException{
		
		if(colecaoDadosGerarIntegracao != null && !colecaoDadosGerarIntegracao.isEmpty()){
	        
	        String ano = dataLancamento.substring(6,10);
	        String mes = dataLancamento.substring(3,5);
	        
	        String descricaoMes = Util.retornaDescricaoMes(new Integer(mes).intValue());
	        
	        String descricaoLancamento = "";
	        
	        descricaoLancamento += "VALOR "+ descricaoLancamentoOrigem+" DO MES DE " + descricaoMes + "/"+ano;
	        
			try {
				
				
				Integer chave = this.repositorioIntegracao.valorMaximoChaveIFS();
				
				if(chave == null){
					chave = 1;
				}else{
					chave++;
				}
				Integer contador = new Integer(1);
				
				for(Object[] dadosGerarIntegracao : colecaoDadosGerarIntegracao){
					
	                
	                String numeroConta = (String) dadosGerarIntegracao[0];
	                String centroCusto = (String) dadosGerarIntegracao[1];
	                BigDecimal valorDebito  = (BigDecimal) dadosGerarIntegracao[2];
	                BigDecimal valorCredito = (BigDecimal) dadosGerarIntegracao[3];
	                Integer codigoLocalidade = (Integer) dadosGerarIntegracao[5];
	                Integer codigoMunicipio = (Integer) dadosGerarIntegracao[6];
	                
//	                if(valorDebito == null){
//	                	valorDebito = BigDecimal.ZERO;
//	                }
//	                
//	                if(valorCredito == null){
//	                	valorCredito = BigDecimal.ZERO;
//	                }
	                
	                String localidade = "";
	                if(codigoLocalidade != null){
	                	localidade = codigoLocalidade.toString();
	                }

	                String municipio = "";
	                if(codigoMunicipio != null){
	                	municipio = codigoMunicipio.toString();
	                }
	                
					
					// 1. Valor fixo "10"
					String company = "10";
					// 2. Valor fixo "COM" para indicar que o lançamento está sendo carregado 
					String voucher_type = "COM";
					// 3. Valor fixo "BRL"
					String currency_code = "BRL";
					// 4. Numero seqüencial
					String load_id = ""+chave;
					// 5. Data do lançamento
					Date transaction_date = Util.converteStringParaDate(dataLancamento) ;
					// 6. Código da Conta Contábil a ser contabilizada.
					String accounting_cod = numeroConta;
					// 7. Código do Centro de Custo, se o lançamento estiver por centro de custo.
					String cost_center = centroCusto;
					// 8. Ano em que o lançamento foi feito formato YYYY (2010, 2011 e 2012)
					String	accounting_year = ano;
					// 9. Mês em que o lançamento foi feito formato MM (09, 10  e 11)
					String accounting_period = mes;
					//10. O valor de debito do lançamento contábil.
					BigDecimal debet_amount = valorDebito;
					//11. O valor de Credito do lançamento contábil.
					BigDecimal credit_amount = valorCredito;
					//12. Campo para facilitar a identificação do lançamento.
					String	text = descricaoLancamento;
					//13. Código da Localidade, se o lançamento estiver por Localidade.
					String code_c = localidade;
					//14. Código do Município, se o lançamento estiver por Município.		
					String code_d = municipio;
					//15. NULL
					String code_g = null;
					//16. Código da Unidade Orçamentária, se o lançamento estiver por Orçamentária
					String code_h = null;
					//17. NULL
					String activity_sequence = null;
					//18. Valor fixo "NOK" (que significa que o registro ainda não foi integrado). 
					// Depois que executar a interface o IFS passa o mesmo para OK.
					String status = "NOK";
					//19. Valor fixo "I" (que significa que é inclusão de um novo registro).
					String transaction_type = "I";
					//20. NULL
					String error_text = null;
					//21.
					Integer record_no = contador;
					//22.
					Date rowversion = new Date();
					
						
					this.repositorioIntegracao.exportarDadosContabilidadeIFS(
						company, 
						voucher_type, 
						currency_code, 
						load_id, 
						transaction_date, 
						accounting_cod, 
						cost_center, 
						accounting_year, 
						accounting_period, 
						debet_amount, 
						credit_amount, 
						text, 
						code_c, 
						code_d, 
						code_g, 
						code_h, 
						activity_sequence, 
						status, 
						transaction_type, 
						error_text, 
						record_no, 
						rowversion);
						
					contador++;
					
				}
			
			} catch (ErroRepositorioException e) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema",e);
			}
			
		}	
	}
	
	
	
	
	/**
	 * [UC0469] Gerar Integração para a Contabilidade
	 * 
	 * [SB0001] Realizar integração com sistema ALPHA para COMPESA
	 *
	 * @author Rafael Pinto
	 * @date 19/10/2011
	 *
	 * @param colecaoDadosGerarIntegracao
	 * @param dataLancamento
	 * @param idLancamentoOrigem
	 * 
	 * @throws ControladorException
	 */
	
	private void gerarArquivoTextoContabilidade(Collection<Object[]> colecaoDadosGerarIntegracao,
		String idLancamentoOrigem, String data) throws ControladorException{
        
        String ano = data.substring(6,10);
        String mes = data.substring(3,5);
        String dia = data.substring(0,2);
        String anoMesDia = ano+"/"+mes+"/"+dia;
        
        String descricaoMes = Util.retornaDescricaoMes(new Integer(mes).intValue());
       
        /*
         * Determina se o arquivo é de faturamento ou arrecadação
         * para concatenar no nome do arquivo .zip
         */
        String descricaoLancamento = "";
        String historico = "VALOR ";
        if(idLancamentoOrigem.equals(LancamentoOrigem.FATURAMENTO + "")){
            descricaoLancamento = "FATURAMENTO";
            historico += "FATURAMENTO DO MES DE " + descricaoMes + "/"+ano;
        }else if(idLancamentoOrigem.equals(LancamentoOrigem.ARRECADACAO + "")){
            descricaoLancamento = "ARRECADACAO";
            historico += "ARRECADACAO DO MES DE " + descricaoMes + "/"+ano;
        }else if(idLancamentoOrigem.equals(LancamentoOrigem.DEVEDORES_DUVIDOSOS + "")){
            descricaoLancamento = "DEVEDORES_DUVIDOSOS";
            historico += "DEVEDORES DUVIDOSOS DO MES DE " + descricaoMes + "/"+ano;
        }
		
		/** definição das variáveis */
        StringBuilder gerarIntegracaoTxt = new StringBuilder();

		String numeroConta = null;
        String centroCusto = null;
        BigDecimal valorDebito = null;
        BigDecimal valorCredito = null;
        String moeda = "BRL";

        /*
         * Laço para gerar o txt
         */
        for(Object[] dadosGerarIntegracao : colecaoDadosGerarIntegracao){
           
           
            numeroConta = (String) dadosGerarIntegracao[0];                           
            centroCusto = (String) dadosGerarIntegracao[1];                           
            valorDebito  = (BigDecimal) dadosGerarIntegracao[2];                       
            valorCredito = (BigDecimal) dadosGerarIntegracao[3];                           
           
            /*
             * Inicio da geração do txt
             */
           
            // SEQ = 1
            // CAMPO = DISPONIVEL
            // INICIO = 1 FIM = 1
            // TAMANHO = 1
            gerarIntegracaoTxt.append(";");
           
            // SEQ = 2
            // CAMPO = ANO
            // INICIO = 2 FIM = 5
            // TAMANHO = 4
            gerarIntegracaoTxt.append(Util.truncarString(ano, 4));
            gerarIntegracaoTxt.append(";");
           
            // SEQ = 3
            // CAMPO = CONTA CONTABIL
            // INICIO = 6 FIM = 13
            // TAMANHO = 8
            gerarIntegracaoTxt.append(Util.truncarString(numeroConta, 8));
            gerarIntegracaoTxt.append(";");
           
            // SEQ = 4
            // CAMPO = CENTRO CUSTO
            // INICIO = 14 FIM = 23
            // TAMANHO = 10
            if(centroCusto!=null){
                gerarIntegracaoTxt.append(centroCusto);
            }
            gerarIntegracaoTxt.append(";");               

            // SEQ = 5
            // CAMPO = LOCALIDADE
            // INICIO = 24 FIM = 73
            // TAMANHO = 50
            gerarIntegracaoTxt.append(";");
           
            // SEQ = 6
            // CAMPO = MUNICIPIO
            // INICIO = 74 FIM = 123
            // TAMANHO = 50

            gerarIntegracaoTxt.append(";");
           
            // SEQ = 7
            // CAMPO = DISPONIVEL
            // INICIO = 124 FIM = 129
            // TAMANHO = 6

            gerarIntegracaoTxt.append(";");
            gerarIntegracaoTxt.append(";");
            gerarIntegracaoTxt.append(";");
            gerarIntegracaoTxt.append(";");
            gerarIntegracaoTxt.append(";");
            gerarIntegracaoTxt.append(";");
           
            // SEQ = 8
            // CAMPO = MOEDA
            // INICIO = 130 FIM = 132
            // TAMANHO = 3
            gerarIntegracaoTxt.append(moeda);
            gerarIntegracaoTxt.append(";");
           
            // SEQ = 9
            // CAMPO = VALOR DEBITO
            // INICIO = 133 FIM = 143
            // TAMANHO = 11
            if(valorDebito!= null){
                gerarIntegracaoTxt.append(valorDebito.toString());
            }
            gerarIntegracaoTxt.append(";");
           
            // SEQ = 10
            // CAMPO = VALOR CREDITO
            // INICIO = 144 FIM = 154
            // TAMANHO = 11
            if(valorCredito!=null){
                gerarIntegracaoTxt.append(valorCredito.toString());   
            }
            gerarIntegracaoTxt.append(";");
           
            // SEQ = 11
            // CAMPO = DISPONIVEL
            // INICIO = 155 FIM = 155
            // TAMANHO = 1
            gerarIntegracaoTxt.append(";");
           
            // SEQ = 12
            // CAMPO = VALOR DEBITO
            // INICIO = 156 FIM = 166
            // TAMANHO = 11
            if(valorDebito!= null){
                gerarIntegracaoTxt.append(valorDebito.toString());
            }
            gerarIntegracaoTxt.append(";");
           
            // SEQ = 13
            // CAMPO = VALOR CREDITO
            // INICIO = 167 FIM = 177
            // TAMANHO = 11
            if(valorCredito!=null){
                gerarIntegracaoTxt.append(valorCredito.toString());   
            }
            gerarIntegracaoTxt.append(";");
           
            // SEQ = 14
            // CAMPO = DISPONIVEL
            // INICIO = 178 FIM = 182
            // TAMANHO = 5
            gerarIntegracaoTxt.append(";");
            gerarIntegracaoTxt.append(";");
            gerarIntegracaoTxt.append(";");
            gerarIntegracaoTxt.append(";");
            gerarIntegracaoTxt.append(";");
           
            // SEQ = 15
            // CAMPO = HISTORICO
            // INICIO = 183 FIM = 438
            // TAMANHO = 255
            gerarIntegracaoTxt.append(historico);
            gerarIntegracaoTxt.append(";");
           
           
            // SEQ = 16
            // CAMPO = DISPONIVEL
            // INICIO = 439 FIM = 443
            // TAMANHO = 5
            gerarIntegracaoTxt.append(";");
            gerarIntegracaoTxt.append(";");
            gerarIntegracaoTxt.append(";");
            gerarIntegracaoTxt.append(";");
            gerarIntegracaoTxt.append(";");
            gerarIntegracaoTxt.append(";");
           
            // SEQ = 16
            // CAMPO = DATA
            // INICIO = 444 FIM = 453
            // TAMANHO = 10
            gerarIntegracaoTxt.append(anoMesDia);
           
            gerarIntegracaoTxt.append(System.getProperty("line.separator"));
           
        }
        
        /*
         * Caso oarquivo txt não esteja vazio
         * adiciona o txt ao arquivo zip.
         */
        
        /*
         * Gerando o arquivo zip
         */
        String nomeZip = "CONTABILIDADE_" + descricaoLancamento + "_" + (data.replace("/","_"));
        BufferedWriter out = null;
        ZipOutputStream zos = null;
        File compactadoTipo = new File(nomeZip + ".zip");
        File leituraTipo = new File(nomeZip + ".txt");

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
	       
        Collection<Object[]> colecaoDadosGerarIntegracao = null;
        
        try {
            colecaoDadosGerarIntegracao = repositorioFinanceiro.pesquisarGerarIntegracaoContabilidade(idLancamentoOrigem, anoMes);
        } catch (ErroRepositorioException ex) {
            throw new ControladorException("erro.sistema", ex);
        }
        
        
        FiltroLancamentoOrigem filtroLancamentoOrigem = new FiltroLancamentoOrigem();
        filtroLancamentoOrigem.adicionarParametro( new ParametroSimples(FiltroLancamentoOrigem.ID, idLancamentoOrigem));
        
        Collection<LancamentoOrigem> colecaoLancamentoOrigem = getControladorUtil().pesquisar(filtroLancamentoOrigem, LancamentoOrigem.class.getName());
        
        LancamentoOrigem lancamentoOrigem = (LancamentoOrigem) Util.retonarObjetoDeColecao(colecaoLancamentoOrigem);
       
        /*
         * Caso a coleção dos dados não esteja vazia
         */
        if(colecaoDadosGerarIntegracao != null && !colecaoDadosGerarIntegracao.isEmpty()){
        	
        	//[SB0005] Realizar integração com sistema IFS para COMPESA.
        	this.enviarDadosParaSistemaIFS(colecaoDadosGerarIntegracao, data, idLancamentoOrigem, lancamentoOrigem.getDescricao());

        	//[SB0001] Gera  arquivo txt para a Compesa
        	this.gerarArquivoTextoContabilidade(colecaoDadosGerarIntegracao, idLancamentoOrigem, data);
        	
        }else{
        	 // Caso não exista informação para os dados informados
             throw new ControladorException("atencao.pesquisa.nenhum_registro_tabela", null, "financeiro.lancamento_origem");
        }
    }
	
	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 25/10/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void apagarResumoDevedoresDuvidosos(int anoMesReferenciaContabil, Integer idLocalidade, Integer idTipoPerda, Integer indicadorReprocessamento , 
			int idFuncionalidadeIniciada)	throws ControladorException {
		
		System.out.println("Localidade " + idLocalidade);
	
		int idUnidadeIniciada = 0;

		/*
		 * Registrar o início do processamento da Unidade de
		 * Processamento do Batch
		*/
		idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,UnidadeProcessamento.LOCALIDADE,(idLocalidade));
		
		try {
			
			//Recupera os parâmetros dos devedores duvidosos.
			ParametrosDevedoresDuvidosos parametrosDevedoresDuvidosos = this.repositorioFinanceiro.pesquisarParametrosDevedoresDuvidosos(anoMesReferenciaContabil);
			
			//[FS0001] - Verificar existência dos parâmetros
			if(parametrosDevedoresDuvidosos == null){
				throw new ControladorException("atencao.naocadastrado.referencia_contabil");
			}
			
			// 2-Caso seja um reprocessamento - [SB0004] - Processar Perdas Societárias
			if ( idTipoPerda != null && idTipoPerda.equals(String.valueOf(PerdasTipo.PROVISAO_PERDAS_SOCIETARIAS)) ){
				
				ParametrosPerdasSocietarias parametrosPerdasSocietarias = this.repositorioFinanceiro.pesquisarParametrosPerdasSocietarias(anoMesReferenciaContabil);
				
				if ( indicadorReprocessamento != null && indicadorReprocessamento.equals(new Integer(1) ) ) {
					//[SB0005] - Excluir Geração Perdas Societárias
					if ( parametrosPerdasSocietarias.getIndicadorGeracaoReal() == ConstantesSistema.SIM ) {
						//[SB0006-Retirar Baixa das Contas]
						this.repositorioFinanceiro.retiraBaixaDasContasPerdasSocietarias(anoMesReferenciaContabil);
					}
					//[SB0008-Excluir Resumo]
					this.repositorioFinanceiro.deletarResumoDevedoresDuvidososPerdasTipo(anoMesReferenciaContabil, idTipoPerda);
				}
			
				// 2-Caso seja um reprocessamento - [SB0012] - Processar Perdas Órgãos Públicos	
			} else if ( idTipoPerda != null && idTipoPerda.equals(String.valueOf(PerdasTipo.PERDAS_ORGAO_PUBLICO)) ){
					
					if ( indicadorReprocessamento != null && indicadorReprocessamento.equals(new Integer(1) ) ) {
						
						//[SB0014] - Retira Baixa das Contas Perdas Órgãos Públicos 
						this.repositorioFinanceiro.retiraBaixaDasContasPerdasOrgaoPublico(anoMesReferenciaContabil);

						//[SB0008-Excluir Resumo]
						this.repositorioFinanceiro.deletarResumoDevedoresDuvidososPerdasTipo(anoMesReferenciaContabil, idTipoPerda);
					}
					
			} else if ( idTipoPerda != null && idTipoPerda.equals(String.valueOf(PerdasTipo.RECUPERACAO_DA_PROVISAO_DE_PERDAS_SOCIETARIAS)) ){
				
				ResumoDevedoresDuvidosos resumoDevedoresDuvidosos = this.repositorioFinanceiro.pesquisarResumoDevedoresDuvidososTipoPerda(anoMesReferenciaContabil, idTipoPerda);
				
				if (resumoDevedoresDuvidosos != null && resumoDevedoresDuvidosos.getId() != null ) {
					
					//exclui o resumo dos devedores duvidosos,referente ao ano/mês referência contábil
					this.repositorioFinanceiro.removeResumoDevedoresDuvidososPorAnoMesReferenciaContabil(anoMesReferenciaContabil,idLocalidade, idTipoPerda);
				}
				
			} else if ( parametrosDevedoresDuvidosos.getDataProcessamento() != null ) {
				
				Collection<Integer> colecaoQuadraId = this.repositorioFinanceiro.pesquisarIdsQuadrasParaGerarResumoDevedoresDuvidosos(idLocalidade);
				if ( colecaoQuadraId != null ) {
					
					Iterator<Integer> iteratorIdsQuadra = colecaoQuadraId.iterator();
					while ( iteratorIdsQuadra.hasNext() ) {
						Integer idQuadra = (Integer) iteratorIdsQuadra.next();
						//o sistema atualiza com o valor nulo o ano/mês referência contábil das contas baixadas
						//contabilmente no ano/mês referência contábil
						this.repositorioFinanceiro.atualizaContaAnoMesReferenciaContabil(anoMesReferenciaContabil, idLocalidade, idQuadra, idTipoPerda);
					}
				}
				
				//exclui o resumo dos devedores duvidosos,referente ao ano/mês referência contábil
				this.repositorioFinanceiro.removeResumoDevedoresDuvidososPorAnoMesReferenciaContabil(anoMesReferenciaContabil,idLocalidade, idTipoPerda);
				parametrosDevedoresDuvidosos.setValorBaixado(BigDecimal.ZERO);
				getControladorUtil().atualizar(parametrosDevedoresDuvidosos);
			}
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex, idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}
	

	/**
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 *	Esse caso de uso foi dividido em 3 steps:
	 *
	 *	1 - apagarResumoDevedoresDuvidosos
	 *	2 - gerarResumoDevedoresDuvidosos
	 *  3 - atualiza
	 *
	 * Gera o resumo dos devedores duvidosos e marca as contas baixadas contabilmente.
	 *
	 * @author Rafael Pinto, Pedro Alexandre,Vivianne Sousa, Arthur Carvalho
	 * @date 22/11/2006, 06/06/2007,09/09/2009 , 30/11/2010
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */		
public void gerarResumoDevedoresDuvidosos(int anoMesReferenciaContabil, Integer idLocalidade, Integer idPerdaTipo,  int idFuncionalidadeIniciada)	throws ControladorException {
		
		int idUnidadeIniciada = 0;

		/*
		 * Registrar o início do processamento da Unidade de
		 * Processamento do Batch
		*/
		idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,UnidadeProcessamento.LOCALIDADE,(idLocalidade));
		
		try {
			
			if ( idPerdaTipo == PerdasTipo.PERDAS_FISCAIS ) {
			
				perdasFiscais( anoMesReferenciaContabil,  idLocalidade,  idPerdaTipo, idUnidadeIniciada);
			
			} else if ( idPerdaTipo == PerdasTipo.PROVISAO_PERDAS_SOCIETARIAS ) {
				
				provisaoPerdasSocietarias(anoMesReferenciaContabil,  idLocalidade,  idPerdaTipo, idUnidadeIniciada ) ;
				
			} else if ( idPerdaTipo == PerdasTipo.PERDAS_ORGAO_PUBLICO ) {
				
				perdasOrgaoPublico(anoMesReferenciaContabil,  idLocalidade,  idPerdaTipo, idUnidadeIniciada);
				
			} else if ( idPerdaTipo == PerdasTipo.RECUPERACAO_DA_PROVISAO_DE_PERDAS_SOCIETARIAS ) {
				
				recuperacaoProvisaoPerdasSocietarias(anoMesReferenciaContabil,  idLocalidade,  idPerdaTipo, idUnidadeIniciada);
				
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
	 * @author Arthur Carvalho
	 * @date 30/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idParametrosDevedoresDuvidosos
	 * @return
	 * @throws ControladorException
	 */
	protected Collection<GerarResumoDevedoresDuvidososHelper> acumularResumoDevedoresDuvidosos(int anoMesReferenciaContabil, 
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
		
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
		
		return colecaoRetorno;
	}
	
	
	
	
	/**
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 *
	 * Gera o resumo dos devedores duvidosos e marca as contas baixadas contabilmente.
	 *
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */		
	public void atualizarResumoDevedoresDuvidosos(int anoMesReferenciaContabil, Integer idPerdaTipo, int idFuncionalidadeIniciada)	throws ControladorException {
	
		int idUnidadeIniciada = 0;

		/*
		 * Registrar o início do processamento da Unidade de
		 * Processamento do Batch
		*/
		idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,UnidadeProcessamento.RELATORIO,(0));
		
		try {
			BigDecimal valorUltrapassado = new BigDecimal(0);
			
			Object[] parametrosARemover = new Object[2];
			boolean primeiraVez = true;
			
			//Recupera os parâmetros dos devedores duvidosos.
			ParametrosDevedoresDuvidosos parametrosDevedoresDuvidosos = this.repositorioFinanceiro.pesquisarParametrosDevedoresDuvidosos(anoMesReferenciaContabil);
			
			if ( parametrosDevedoresDuvidosos != null && parametrosDevedoresDuvidosos.getId() != null) {
				repositorioFinanceiro.atualizarValorBaixadoParametrosDevedoresDuvidosos(anoMesReferenciaContabil, idPerdaTipo, parametrosDevedoresDuvidosos.getId());
				
				parametrosDevedoresDuvidosos = this.repositorioFinanceiro.pesquisarParametrosDevedoresDuvidosos(anoMesReferenciaContabil);
				
				if (parametrosDevedoresDuvidosos.getValorBaixado().compareTo(parametrosDevedoresDuvidosos.getValorABaixar()) == 1 ) {
			
					Collection<Object[]> colecaoValorBaixadoELocalidade  = repositorioFinanceiro.pesquisarValorBaixadoAgrupadoPorLocalidadeResumoDevedoresDuvidosos( anoMesReferenciaContabil )  ;
					
					if (colecaoValorBaixadoELocalidade != null && !colecaoValorBaixadoELocalidade.isEmpty()) {
						
						for (Object[] valorBaixadoELocalidade : colecaoValorBaixadoELocalidade) {
							
							BigDecimal valorBaixado =(BigDecimal) valorBaixadoELocalidade[0];
							
							valorUltrapassado = parametrosDevedoresDuvidosos.getValorBaixado().subtract(parametrosDevedoresDuvidosos.getValorABaixar());
							
							if ( (valorBaixado.compareTo(valorUltrapassado) == 1) && primeiraVez) {
								parametrosARemover = valorBaixadoELocalidade;
								primeiraVez = false;
							} else if ( valorBaixado.compareTo(valorUltrapassado) == 1 ) {
								BigDecimal valorBaixadoJaCadastrado =(BigDecimal) parametrosARemover[0];
								
								if ( valorBaixado.compareTo(valorBaixadoJaCadastrado) == -1 ) {
									parametrosARemover = valorBaixadoELocalidade;
								}
							}
						}
					}
					
					
					//exclui o resumo dos devedores duvidosos,referente ao ano/mês referência contábil
					BigDecimal valorBaixadoARemover = (BigDecimal) parametrosARemover[0];
					Integer idLocalidade = (Integer) parametrosARemover[1];
					this.repositorioFinanceiro.removeResumoDevedoresDuvidososPorAnoMesReferenciaContabil(anoMesReferenciaContabil, idLocalidade, idPerdaTipo);
					this.repositorioFinanceiro.atualizaContaAnoMesReferenciaContabil(anoMesReferenciaContabil, idLocalidade);
					
					parametrosDevedoresDuvidosos.setValorBaixado(parametrosDevedoresDuvidosos.getValorBaixado().subtract(valorBaixadoARemover));
					
					getControladorUtil().atualizar(parametrosDevedoresDuvidosos);
				}
			}
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex, idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}
	
	
	/**
	 * [UC0345] - Gerar Relatorio de Resumo da Arrecadação
	 *
	 * @author Vivianne Sousa
	 * @date 10/04/2007
	 *
	 * @param idLancamentoTipo
	 * @throws ErroRepositorioException
	 */	
	public String obterDescricaoLancamentoTipo(Integer idLancamentoTipo) 
		throws ControladorException{
		try {
			return repositorioFinanceiro
					.obterDescricaoLancamentoTipo(idLancamentoTipo);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	
	/**
	 * Pesquisa as localidades que tem resumo de faturamento 
	 * para o ano/mês de faturamento informado.
	 *
	 * [UC00175] Gerar Lançamentos Contábeis do Faturamento
	 *
	 * @author Pedro Alexandre
	 * @date 25/05/2007
	 *
	 * @param anoMesFaturamento
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarIdsLocalidadesParaGerarLancamentosContabeisFaturamento(Integer anoMesFaturamento) throws ControladorException{
		try{
			//pesquisa os lançamentos de item contábil existentes no sisitema
			return 
				this.repositorioFinanceiro.pesquisarIdsLocalidadesParaGerarLancamentosContabeisFaturamento(
						anoMesFaturamento);
			
				//erro no hibernate
			} catch (ErroRepositorioException ex) {
				//levanta a exceção para a próxima camada
				throw new ControladorException("erro.sistema", ex);
			}		
	}
	
	/**
	 * Gera o lançamento contábil junto com seus itens. 
	 *
	 * [UC0348] - Gerar Lançamentos Contábeis da Arrecadação
	 *
	 * @author Pedro Alexandre
	 * @date 31/05/2007
	 *
	 * @param lancamentoContabil
	 * @param colecaoDadosResumoPorTipoLancamento
	 * @throws ControladorException
	 */
	protected void inserirLancamentoContabilItemArrecadacao(LancamentoContabil lancamentoContabil,Collection<Object[]> colecaoDadosResumoPorTipoLancamento) throws ControladorException {
		try{
			/*
			 * Caso exista dados para os itens do resumo da arrecadação
			 * inseri os itens do lançamento contábil.  
			 */
			if(colecaoDadosResumoPorTipoLancamento != null && !colecaoDadosResumoPorTipoLancamento.isEmpty()){
				
				Collection colecaoLancamentoContabilItem = new ArrayList();
				
				//flaq que indica se o lançamento contábil já foi inserido
				boolean flagInseridoLancamentoContabil = false;
				
				/*
				 * Dados do resumo da arrecadação
				 * 
				 *  0 - id da localidade
				 *  1 - id do tipo de recebimento
				 *  2 - id do tipo de lançamento
				 *  3 - id do item de lançamento
				 *  4 - id do item de lançamento contábil
				 *  5 - id da categoria
				 *  6 - soma do valor do resumo da arrecadação 
				 */
				for(Object[] dadosResumoFaturamento : colecaoDadosResumoPorTipoLancamento){
					
					//recupera os dados do resumo do faturamento
					Integer idTipoRecebimento = 	   (Integer) dadosResumoFaturamento[1];
					Integer idLancamentoTipo =         (Integer) dadosResumoFaturamento[2];
					Integer idLancamentoItem =         (Integer) dadosResumoFaturamento[3];
					Integer idLancamentoItemContabil = (Integer) dadosResumoFaturamento[4];
					Integer idCategoria =              (Integer) dadosResumoFaturamento[5];
					BigDecimal valorLancamento =       (BigDecimal) dadosResumoFaturamento[6]; 
					
					/* 
					 * Verifica se existe conta contábil para o item que vai ser inserido 
					 * 
					 * 0 - id conta contábil do débito
					 * 1 - id conta contábil crédito 
					 * 2 - descrição do histórico do débito
					 * 3 - descrição do histórico do crédito
					 */
					Object[] dadosContaContabil = this.repositorioFinanceiro.obterParametrosContabilArrecadacao(idTipoRecebimento, idCategoria, idLancamentoItemContabil, idLancamentoItem, idLancamentoTipo);
					
					if(dadosContaContabil != null){
						Integer idLancamentoContabil = null;
						/*
						 * Caso exista dados para a conta contábil do item do resumo da arrecadação
						 * e o lançamento contábil não foi inserido ainda 
						 * inseri o lançamento contábil na base.
						 */
						if(!flagInseridoLancamentoContabil){
							idLancamentoContabil = (Integer)getControladorUtil().inserir(lancamentoContabil);
							lancamentoContabil.setId(idLancamentoContabil);
							flagInseridoLancamentoContabil = true;
						}
						
						//recupera os dados da conta contábil.
						Integer idContaContabilDebito = (Integer) dadosContaContabil[0];
						Integer idContaContabilCredito = (Integer) dadosContaContabil[1];
						String descricaoHistoricoDebito = (String) dadosContaContabil[2];
						String descricaoHistoricoCredito = (String) dadosContaContabil[3];
						
						//cria os indicadores de débito e crédito.
						Short indicadorDebito = new Short("2"); 
						Short indicadorCredito = new Short("1");
						
						Date ultimaAlteracao = new Date();
						
						//cria as contas de débito e crédito.
						ContaContabil contaContabilCredito = new ContaContabil();
						contaContabilCredito.setId(idContaContabilCredito);
						
						ContaContabil contaContabilDebito = new ContaContabil();
						contaContabilDebito.setId(idContaContabilDebito);
						
						/**  Item de crédito */
						LancamentoContabilItem lancamentoContabilItemCredito = new LancamentoContabilItem(indicadorCredito, 
								valorLancamento, 
								descricaoHistoricoCredito, 
								ultimaAlteracao, 
								lancamentoContabil, 
								contaContabilCredito);
						
						colecaoLancamentoContabilItem.add(lancamentoContabilItemCredito);
						
						/** Item de débito */
						LancamentoContabilItem lancamentoContabilItemDebito = new LancamentoContabilItem(indicadorDebito, 
								valorLancamento, 
								descricaoHistoricoDebito, 
								ultimaAlteracao, 
								lancamentoContabil, 
								contaContabilDebito);
						
						colecaoLancamentoContabilItem.add(lancamentoContabilItemDebito);
						
					}
				}
				
				getControladorBatch().inserirColecaoObjetoParaBatch(colecaoLancamentoContabilItem); 
			}
	
		} catch (Exception ex) {
			throw new EJBException(ex);
		}
	}
	
	/**
	 * Pesquisa as localidades que tem resumo de arrecadação 
	 * para o ano/mês de arrecadação informado.
	 *
	 * [UC00348] Gerar Lançamentos Contábeis da arrecadação
	 *
	 * @author Pedro Alexandre
	 * @date 31/05/2007
	 *
	 * @param anoMesArrecadacao
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarIdsLocalidadesParaGerarLancamentosContabeisArrecadacao(Integer anoMesArrecadacao) throws ControladorException{
		try{
			return this.repositorioFinanceiro.pesquisarIdsLocalidadesParaGerarLancamentosContabeisArrecadacao(anoMesArrecadacao);
			
				//erro no hibernate
			} catch (ErroRepositorioException ex) {
				//levanta a exceção para a próxima camada
				throw new ControladorException("erro.sistema", ex);
			}		
	}
	
	
	/**
	 * Pesquisa a coleção de ids das localidades para processar o resumo 
	 * dos devedores duvidosos.
	 *
	 * [UC0485] Gerar Resumo dos Devedores Duvidosos
	 *
	 * @author Pedro Alexandre
	 * @date 25/06/2007
	 *
	 * @param anoMesReferenciaContabil
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarIdsLocalidadesParaGerarResumoDevedoresDuvidosos(Integer anoMesReferenciaContabil) throws ControladorException{
		try{
			return this.repositorioFinanceiro.pesquisarIdsLocalidadesGerarResumoDevedoresDuvidosos(anoMesReferenciaContabil);
			
				//erro no hibernate
			} catch (ErroRepositorioException ex) {
				//levanta a exceção para a próxima camada
				throw new ControladorException("erro.sistema", ex);
			}		
	}
	
//	***********************************************************************************************************************
//	******************************** PARTE PARA SEER COLOCADA NO CONTROLADOR CAERN
		

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
		/*public Collection pesquisarGerarIntegracaoContabilidadeCaern(String idLancamentoOrigem, String anoMes) throws ControladorException{
			
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
						
//						Versao
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
					}
				}
				
			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", ex);
			}
			
			return colecaoGerarIntegracaoContabilidade;
		}
		*/
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
		public void gerarIntegracaoContabilidadeCaern(String idLancamentoOrigem, String anoMes, String data) throws ControladorException{
			
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

			colecaoDadosGerarIntegracao = this.pesquisarGerarIntegracaoContabilidade/*Caern*/(idLancamentoOrigem, anoMes);
			
			/** definição das variáveis */
			StringBuilder gerarIntegracaoTxt = new StringBuilder();
			String dataFormatada = data.replace("/","");
			
			/*
			 * Caso a coleção dos dados não esteja vazia
			 */
			if(colecaoDadosGerarIntegracao != null && !colecaoDadosGerarIntegracao.isEmpty()){
				
				
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
				//caso não exista informação para os dados informados
			}else{
				if(idLancamentoOrigem.equals(LancamentoOrigem.FATURAMENTO + "")){
					throw new ControladorException("atencao.pesquisa.nenhum_registro_tabela", null,"Resumo Faturamento");
				}else if(idLancamentoOrigem.equals(LancamentoOrigem.ARRECADACAO + "")){
					throw new ControladorException("atencao.pesquisa.nenhum_registro_tabela", null,"Resumo Faturamento");
				}
			}
		}
		
		
//	***********************************************************************************************************************

	/**
	 * Inserir o processo para gerar o resumo dos devedores duvidosos. 
	 *
	 * [UC0485] Gerar Resumo dos Devedores Duvidosos
	 *
	 * @author Pedro Alexandre
	 * @date 18/06/2007
	 *
	 * @param processoIniciado
	 * @param dadosProcessamento
	 * @return
	 * @throws ControladorException
	 */
	public Integer gerarResumoDevedoresDuvidosos(ProcessoIniciado processoIniciado,Map<String, Object> dadosProcessamento)	throws ControladorException {
		//Recupera o ano/mês de referência contábil.
		Integer anoMesReferenciaContabil = new Integer((String) dadosProcessamento.get("anoMesReferenciaContabil"));
		
		ParametrosDevedoresDuvidosos parametrosDevedoresDuvidosos;
		try {

			//[FS0001] - Verificar existência dos parâmetros
			parametrosDevedoresDuvidosos = this.repositorioFinanceiro.pesquisarParametrosDevedoresDuvidosos(anoMesReferenciaContabil);
			if(parametrosDevedoresDuvidosos == null){
				throw new ControladorException("atencao.naocadastrado.referencia_contabil");
			}else{
				if(parametrosDevedoresDuvidosos.getDataProcessamento() != null){
					//2-Caso seja um reprocessamento zerar o valor baixado
					parametrosDevedoresDuvidosos.setValorBaixado(BigDecimal.ZERO);
					this.getControladorUtil().atualizar(parametrosDevedoresDuvidosos);
				}
	
			}
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}	
		//chama o metódo para inserir o processo de gerar resumo devedores duvidosos
		return this.getControladorBatch().inserirProcessoIniciado(processoIniciado,dadosProcessamento);
	}
	
	 /**
     * [UC0714] Gerar Contas a Receber Contábil
     *
     * Método responsável pela geração de contas a receber contábil
     *
     * @author Rafael Corrêa
     * @date 08/11/2007
     *
     * @param idLocalidade
     * @throws ControladorException
     */
    public void gerarContasAReceberContabil(Integer idLocalidade,
            int idFuncionalidadeIniciada) throws ControladorException {

        System.out.println("LOCALIDADE " + idLocalidade);
        int idUnidadeIniciada = 0;

        // -------------------------
        //
        // Registrar o início do processamento da Unidade de
        // Processamento
        // do Batch
        //
        // -------------------------
        idUnidadeIniciada = getControladorBatch()
                .iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
                        UnidadeProcessamento.LOCALIDADE, idLocalidade);

        try {
        
        	SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();

        	if (!sistemaParametro.getAnoMesArrecadacao().equals(sistemaParametro.getAnoMesFaturamento())) {
            	throw new ControladorException("atencao.arrecadacao_ou_faturamento_nao_encerrados");
        	}


            int anoMesAnteriorFaturamento = Util.subtrairMesDoAnoMes(sistemaParametro.getAnoMesFaturamento(), 1);
            int anoMesArrecadacao = sistemaParametro.getAnoMesArrecadacao(); 
            	
            Collection colecaoContasAReceberContabil = new ArrayList();

            // item 2
            // exclui os dados do saldo de contas a receber contábil do mês de
            // referência do faturamento já encerrado
            repositorioFinanceiro.removerContasAReceberContabil(anoMesAnteriorFaturamento, idLocalidade);

            // Valores de Água e Esgoto
            adicionarContaAReceberContabilAguaEsgotoImpostos(idLocalidade,
                    anoMesAnteriorFaturamento, colecaoContasAReceberContabil);

            // Débitos Cobrados
            adicionarContaAReceberContabilDebitosCobrados(idLocalidade,
                    anoMesAnteriorFaturamento, colecaoContasAReceberContabil);

            // Guias de Pagamento
            adicionarContaAReceberContabilGuiasPagamento(idLocalidade,
                    anoMesAnteriorFaturamento, colecaoContasAReceberContabil);

            // Créditos Realizados
            adicionarContaAReceberContabilCreditosRealizados(idLocalidade,
                    anoMesAnteriorFaturamento, colecaoContasAReceberContabil);

            // Débitos a Cobrar
            adicionarContaAReceberContabilDebitosACobrar(idLocalidade,
                    anoMesAnteriorFaturamento, colecaoContasAReceberContabil);

            // Créditos a Realizar
            adicionarContaAReceberContabilCreditosARealizar(idLocalidade,
                    anoMesAnteriorFaturamento, colecaoContasAReceberContabil);

            //adicionado por Vivianne Sousa 14/08/2009 - Aryed Lins
            adicionarContaAReceberContabilValoresContabilizadosComoPerdas(
                    idLocalidade, anoMesAnteriorFaturamento, colecaoContasAReceberContabil);
            
            //adicionado por Vivianne Sousa 17/08/2009 - Aryed Lins
            adicionarContaAReceberContabilRecebimentosNaoIdentificados(
                    idLocalidade, anoMesAnteriorFaturamento,
                    anoMesArrecadacao, colecaoContasAReceberContabil);

            if (colecaoContasAReceberContabil != null
                    && !colecaoContasAReceberContabil.isEmpty()) {
                getControladorBatch().inserirColecaoObjetoParaBatch(
                        colecaoContasAReceberContabil);
            }

            getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
                    idUnidadeIniciada, false);

            System.out
                    .println("fim da geração " + "Localidade " + idLocalidade);

        } catch (Exception e) {

            getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
                    idUnidadeIniciada, true);
            throw new EJBException(e);
        }
    }

    /**
     * [UC0714] Gerar Contas a Receber Contábil
     *
     * Cria o objeto de ContaAReceberContabil de acordo com os parâmetros
     * passados
     *
     * @author Rafael Corrêa
     * @date 08/11/2007
     *
     */
    private ContaAReceberContabil criarContaAReceberContabil(
            int anoMesReferencia, Integer idGerenciaRegional,
            Integer idUnidadeNegocio, Integer idLocalidade,
            Integer idCategoria, BigDecimal valorItem,
            Integer idLancamentoTipo, int sequenciaLancamentoTipo,
            Integer idLancamentoItem, int sequenciaLancamentoItem,
            Integer idLancamentoItemContabil) {

        ContaAReceberContabil retorno = new ContaAReceberContabil();

        retorno.setAnoMesReferencia(anoMesReferencia);

        // Gerência Regional
        GerenciaRegional gerenciaRegional = new GerenciaRegional();
        gerenciaRegional.setId(idGerenciaRegional);
        retorno.setGerenciaRegional(gerenciaRegional);

        // Unidade Negócio
        UnidadeNegocio unidadeNegocio = new UnidadeNegocio();
        unidadeNegocio.setId(idUnidadeNegocio);
        retorno.setUnidadeNegocio(unidadeNegocio);

        // Localidade
        Localidade localidade = new Localidade();
        localidade.setId(idLocalidade);
        retorno.setLocalidade(localidade);

        // Categoria
        Categoria categoria = new Categoria();
        categoria.setId(idCategoria);
        retorno.setCategoria(categoria);

        // Valor Acumulado
        retorno.setValorItemLancamento(valorItem);

        // Lançamento Tipo
        LancamentoTipo lancamentoTipo = new LancamentoTipo();
        lancamentoTipo.setId(idLancamentoTipo);
        retorno.setLancamentoTipo(lancamentoTipo);

        // Seqüência do Lançamento Tipo
        retorno.setNumeroSequenciaTipoLancamento(sequenciaLancamentoTipo);

        // Lançamento Item
        LancamentoItem lancamentoItem = new LancamentoItem();
        lancamentoItem.setId(idLancamentoItem);
        retorno.setLancamentoItem(lancamentoItem);

        // Seqüência do Lançamento Item
        retorno.setNumeroSequenciaItemTipoLancamento(sequenciaLancamentoItem);
       
        // Lançamento Item Contábil
        if (idLancamentoItemContabil != null) {
            LancamentoItemContabil lancamentoItemContabil = new LancamentoItemContabil();
            lancamentoItemContabil.setId(idLancamentoItemContabil);
            retorno.setLancamentoItemContabil(lancamentoItemContabil);
        }
           
       
        //Colocado por Raphael Rossiter em 21/02/2008
        retorno.setUltimaAlteracao(new Date());

        return retorno;

    }

    /**
     * [UC0714] Gerar Contas a Receber Contábil
     *
     * Adiciona os dados de água e esgoto
     *
     * @author Rafael Corrêa
     * @date 08/11/2007
     *
     */
    private void adicionarContaAReceberContabilAguaEsgotoImpostos(Integer idLocalidade,
            int anoMesAnteriorFaturamento,
            Collection colecaoContasAReceberContabil)
            throws ErroRepositorioException {
    	
        Collection colecaoDadosValorAguaEsgoto = repositorioFinanceiro
                .pesquisarDadosContasCategoriaValorAguaEsgoto(
                        anoMesAnteriorFaturamento, idLocalidade);

        if (colecaoDadosValorAguaEsgoto != null
                && !colecaoDadosValorAguaEsgoto.isEmpty()) {

            Iterator colecaoDadosValorAguaEsgotoIterator = colecaoDadosValorAguaEsgoto
                    .iterator();

            while (colecaoDadosValorAguaEsgotoIterator.hasNext()) {

                Object[] dadosValorAguaEsgoto = (Object[]) colecaoDadosValorAguaEsgotoIterator
                        .next();

                Integer idGerenciaRegionalConta = (Integer) dadosValorAguaEsgoto[0];
                Integer idUnidadeNegocioConta = (Integer) dadosValorAguaEsgoto[1];
                Integer idLocalidadeConta = (Integer) dadosValorAguaEsgoto[2];
                Integer idCategoriaConta = (Integer) dadosValorAguaEsgoto[3];

                BigDecimal valorAgua = (BigDecimal) dadosValorAguaEsgoto[4];

                BigDecimal valorEsgoto = (BigDecimal) dadosValorAguaEsgoto[5];

                // Água
                if (valorAgua != null
                        && valorAgua.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento, idGerenciaRegionalConta,
                            idUnidadeNegocioConta, idLocalidadeConta,
                            idCategoriaConta, valorAgua,
                            LancamentoTipo.DOCUMENTOS_EMITIDOS, 100,
                            LancamentoItem.AGUA, 10, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

                // Esgoto
                if (valorEsgoto != null
                        && valorEsgoto.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento, idGerenciaRegionalConta,
                            idUnidadeNegocioConta, idLocalidadeConta,
                            idCategoriaConta, valorEsgoto,
                            LancamentoTipo.DOCUMENTOS_EMITIDOS, 100,
                            LancamentoItem.ESGOTO, 20, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);

                }

            }

            colecaoDadosValorAguaEsgoto = null;

        }
        
        Collection colecaoDadosValorImpostos = repositorioFinanceiro
        .pesquisarDadosContasCategoriaValorImpostos(
						anoMesAnteriorFaturamento, idLocalidade);

		if (colecaoDadosValorImpostos != null
				&& !colecaoDadosValorImpostos.isEmpty()) {

			Iterator colecaoDadosValorImpostosIterator = colecaoDadosValorImpostos
					.iterator();

			while (colecaoDadosValorImpostosIterator.hasNext()) {

				Object[] dadosValorImpostos = (Object[]) colecaoDadosValorImpostosIterator
						.next();

				Integer idGerenciaRegionalConta = (Integer) dadosValorImpostos[0];
				Integer idUnidadeNegocioConta = (Integer) dadosValorImpostos[1];
				Integer idLocalidadeConta = (Integer) dadosValorImpostos[2];
				Integer idCategoriaConta = (Integer) dadosValorImpostos[3];

				BigDecimal valorImpostos = (BigDecimal) dadosValorImpostos[4];

				// Valor dos Impostos
				if (valorImpostos != null
						&& valorImpostos.compareTo(new BigDecimal("0.00")) > 0) {

					// Cria o objeto com os valores passados
					ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
							anoMesAnteriorFaturamento, idGerenciaRegionalConta,
							idUnidadeNegocioConta, idLocalidadeConta,
							idCategoriaConta, valorImpostos.multiply(new BigDecimal("-1")),
							LancamentoTipo.DOCUMENTOS_EMITIDOS, 310,
							LancamentoItem.IMPOSTOS_DEDUZIDOS, 10, null);

					colecaoContasAReceberContabil.add(contaAReceberContabil);
				}

			}

			colecaoDadosValorImpostos = null;

		}
    }

    /**
	 * [UC0714] Gerar Contas a Receber Contábil
	 * 
	 * Adiciona os dados de débitos cobrados
	 * 
	 * @author Rafael Corrêa
	 * @date 08/11/2007
	 * 
	 */
    private void adicionarContaAReceberContabilDebitosCobrados(
            Integer idLocalidade, int anoMesAnteriorFaturamento,
            Collection colecaoContasAReceberContabil)
            throws ErroRepositorioException {

        // Serviço
        Collection colecaoDadosDebitosCobradosServico = repositorioFinanceiro
                .pesquisarDadosDebitosCobradosCategoriaServico(
                        anoMesAnteriorFaturamento, idLocalidade);

        if (colecaoDadosDebitosCobradosServico != null
                && !colecaoDadosDebitosCobradosServico.isEmpty()) {

            Iterator colecaoDadosDebitosCobradosServicoIterator = colecaoDadosDebitosCobradosServico
                    .iterator();

            while (colecaoDadosDebitosCobradosServicoIterator.hasNext()) {

                Object[] dadosDebitosCobradosServico = (Object[]) colecaoDadosDebitosCobradosServicoIterator
                        .next();

                Integer idGerenciaRegionalConta = (Integer) dadosDebitosCobradosServico[0];
                Integer idUnidadeNegocioConta = (Integer) dadosDebitosCobradosServico[1];
                Integer idLocalidadeConta = (Integer) dadosDebitosCobradosServico[2];
                Integer idCategoriaConta = (Integer) dadosDebitosCobradosServico[3];
                Integer idLancamentoItemContabil = (Integer) dadosDebitosCobradosServico[4];

                BigDecimal valorCategoria = (BigDecimal) dadosDebitosCobradosServico[5];

                if (valorCategoria != null
                        && valorCategoria.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento, idGerenciaRegionalConta,
                            idUnidadeNegocioConta, idLocalidadeConta,
                            idCategoriaConta, valorCategoria,
                            LancamentoTipo.DOCUMENTOS_EMITIDOS, 100,
                            LancamentoItem.FINANCIAMENTOS_COBRADOS, 30, idLancamentoItemContabil);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

            }

            colecaoDadosDebitosCobradosServico = null;

        }

        // Parcelamento
        Collection colecaoDadosDebitosCobradosParcelamento = repositorioFinanceiro
                .pesquisarDadosDebitosCobradosCategoriaParcelamento(
                        anoMesAnteriorFaturamento, idLocalidade);

        if (colecaoDadosDebitosCobradosParcelamento != null
                && !colecaoDadosDebitosCobradosParcelamento.isEmpty()) {

            Iterator colecaoDadosDebitosCobradosParcelamentoIterator = colecaoDadosDebitosCobradosParcelamento
                    .iterator();

            while (colecaoDadosDebitosCobradosParcelamentoIterator.hasNext()) {

                Object[] dadosDebitosCobradosParcelamento = (Object[]) colecaoDadosDebitosCobradosParcelamentoIterator
                        .next();

                Integer idGerenciaRegionalConta = (Integer) dadosDebitosCobradosParcelamento[0];
                Integer idUnidadeNegocioConta = (Integer) dadosDebitosCobradosParcelamento[1];
                Integer idLocalidadeConta = (Integer) dadosDebitosCobradosParcelamento[2];
                Integer idCategoriaConta = (Integer) dadosDebitosCobradosParcelamento[3];
                Integer idLancamentoItemContabil = (Integer) dadosDebitosCobradosParcelamento[4];

                BigDecimal valorCategoria = (BigDecimal) dadosDebitosCobradosParcelamento[5];

                if (valorCategoria != null
                        && valorCategoria.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento, idGerenciaRegionalConta,
                            idUnidadeNegocioConta, idLocalidadeConta,
                            idCategoriaConta, valorCategoria,
                            LancamentoTipo.DOCUMENTOS_EMITIDOS, 100,
                            LancamentoItem.PARCELAMENTOS_COBRADOS, 40, idLancamentoItemContabil);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

            }

            colecaoDadosDebitosCobradosParcelamento = null;

        }

    }

    /**
     * [UC0714] Gerar Contas a Receber Contábil
     *
     * Adiciona os dados das guias de pagamento
     *
     * @author Rafael Corrêa
     * @date 08/11/2007
     *
     */
    private void adicionarContaAReceberContabilGuiasPagamento(
            Integer idLocalidade, int anoMesAnteriorFaturamento,
            Collection colecaoContasAReceberContabil)
            throws ErroRepositorioException {

        // Entradas de Parcelamento
        Collection colecaoDadosGuiasPagamentoEntradaParcelamento = repositorioFinanceiro
                .pesquisarDadosGuiasPagamentoCategoriaEntradaParcelamento(
                        anoMesAnteriorFaturamento, idLocalidade);

        if (colecaoDadosGuiasPagamentoEntradaParcelamento != null
                && !colecaoDadosGuiasPagamentoEntradaParcelamento.isEmpty()) {

            Iterator colecaoDadosGuiasPagamentoEntradaParcelamentoIterator = colecaoDadosGuiasPagamentoEntradaParcelamento
                    .iterator();

            while (colecaoDadosGuiasPagamentoEntradaParcelamentoIterator
                    .hasNext()) {

                Object[] dadosGuiasPagamentoEntradaParcelamento = (Object[]) colecaoDadosGuiasPagamentoEntradaParcelamentoIterator
                        .next();

                Integer idGerenciaRegionalConta = (Integer) dadosGuiasPagamentoEntradaParcelamento[0];
                Integer idUnidadeNegocioConta = (Integer) dadosGuiasPagamentoEntradaParcelamento[1];
                Integer idLocalidadeConta = (Integer) dadosGuiasPagamentoEntradaParcelamento[2];
                Integer idCategoriaConta = (Integer) dadosGuiasPagamentoEntradaParcelamento[3];

                BigDecimal valorCategoria = (BigDecimal) dadosGuiasPagamentoEntradaParcelamento[4];

                if (valorCategoria != null
                        && valorCategoria.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento, idGerenciaRegionalConta,
                            idUnidadeNegocioConta, idLocalidadeConta,
                            idCategoriaConta, valorCategoria,
                            LancamentoTipo.DOCUMENTOS_EMITIDOS, 200,
                            LancamentoItem.ENTRADAS_PARCELAMENTO, 10, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

            }

            colecaoDadosGuiasPagamentoEntradaParcelamento = null;

        }

        // Serviço
        Collection colecaoDadosGuiaPagamentoServico = repositorioFinanceiro
                .pesquisarDadosGuiasPagamentoCategoriaServico(
                        anoMesAnteriorFaturamento, idLocalidade);

        if (colecaoDadosGuiaPagamentoServico != null
                && !colecaoDadosGuiaPagamentoServico.isEmpty()) {

            Iterator colecaoDadosGuiaPagamentoServicoIterator = colecaoDadosGuiaPagamentoServico
                    .iterator();

            while (colecaoDadosGuiaPagamentoServicoIterator.hasNext()) {

                Object[] dadosGuiaPagamentoServico = (Object[]) colecaoDadosGuiaPagamentoServicoIterator
                        .next();

                Integer idGerenciaRegionalConta = (Integer) dadosGuiaPagamentoServico[0];
                Integer idUnidadeNegocioConta = (Integer) dadosGuiaPagamentoServico[1];
                Integer idLocalidadeConta = (Integer) dadosGuiaPagamentoServico[2];
                Integer idCategoriaConta = (Integer) dadosGuiaPagamentoServico[3];
                Integer idLancamentoItemContabil = (Integer) dadosGuiaPagamentoServico[4];

                BigDecimal valorCategoria = (BigDecimal) dadosGuiaPagamentoServico[5];

                if (valorCategoria != null
                        && valorCategoria.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento, idGerenciaRegionalConta,
                            idUnidadeNegocioConta, idLocalidadeConta,
                            idCategoriaConta, valorCategoria,
                            LancamentoTipo.DOCUMENTOS_EMITIDOS, 200,
                            LancamentoItem.GUIAS_PAGAMENTO, 20, idLancamentoItemContabil);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

            }

            colecaoDadosGuiaPagamentoServico = null;

        }

    }

    /**
     * [UC0714] Gerar Contas a Receber Contábil
     *
     * Adiciona os dados de créditos realizados
     *
     * @author Rafael Corrêa
     * @date 08/11/2007
     *
     */
    private void adicionarContaAReceberContabilCreditosRealizados(
            Integer idLocalidade, int anoMesAnteriorFaturamento,
            Collection colecaoContasAReceberContabil)
            throws ErroRepositorioException {

        // Pagamento em duplicidade ou excesso
        Collection colecaoDadosCreditosRealizadosPagamentoExcesso = repositorioFinanceiro
                .pesquisarDadosCreditosRealizadosCategoriaPagamentoExcesso(
                        anoMesAnteriorFaturamento, idLocalidade);

        if (colecaoDadosCreditosRealizadosPagamentoExcesso != null
                && !colecaoDadosCreditosRealizadosPagamentoExcesso.isEmpty()) {

            Iterator colecaoDadosCreditosRealizadosPagamentoExcessoIterator = colecaoDadosCreditosRealizadosPagamentoExcesso
                    .iterator();

            while (colecaoDadosCreditosRealizadosPagamentoExcessoIterator
                    .hasNext()) {

                Object[] dadosCreditosRealizadosPagamentoExcesso = (Object[]) colecaoDadosCreditosRealizadosPagamentoExcessoIterator
                        .next();

                Integer idGerenciaRegionalConta = (Integer) dadosCreditosRealizadosPagamentoExcesso[0];
                Integer idUnidadeNegocioConta = (Integer) dadosCreditosRealizadosPagamentoExcesso[1];
                Integer idLocalidadeConta = (Integer) dadosCreditosRealizadosPagamentoExcesso[2];
                Integer idCategoriaConta = (Integer) dadosCreditosRealizadosPagamentoExcesso[3];

                BigDecimal valorCategoria = (BigDecimal) dadosCreditosRealizadosPagamentoExcesso[4];

                if (valorCategoria != null
                        && valorCategoria.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento, idGerenciaRegionalConta,
                            idUnidadeNegocioConta, idLocalidadeConta,
                            idCategoriaConta, valorCategoria.multiply(new BigDecimal("-1")),
                            LancamentoTipo.DOCUMENTOS_EMITIDOS, 300,
                            LancamentoItem.CONTAS_PAGA_EM_DUPLICIDADE_EXCESSO,
                            10, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

            }

            colecaoDadosCreditosRealizadosPagamentoExcesso = null;

        }

        // Descontos concedidos no parcelamento
        Collection colecaoDadosCreditosRealizadosDescontoParcelamento = repositorioFinanceiro
                .pesquisarDadosCreditosRealizadosCategoriaDescontoParcelamento(
                        anoMesAnteriorFaturamento, idLocalidade);

        if (colecaoDadosCreditosRealizadosDescontoParcelamento != null
                && !colecaoDadosCreditosRealizadosDescontoParcelamento
                        .isEmpty()) {

            Iterator colecaoDadosCreditosRealizadosDescontoParcelamentoIterator = colecaoDadosCreditosRealizadosDescontoParcelamento
                    .iterator();

            while (colecaoDadosCreditosRealizadosDescontoParcelamentoIterator
                    .hasNext()) {

                Object[] dadosCreditosRealizadosDescontoParcelamento = (Object[]) colecaoDadosCreditosRealizadosDescontoParcelamentoIterator
                        .next();

                Integer idGerenciaRegionalConta = (Integer) dadosCreditosRealizadosDescontoParcelamento[0];
                Integer idUnidadeNegocioConta = (Integer) dadosCreditosRealizadosDescontoParcelamento[1];
                Integer idLocalidadeConta = (Integer) dadosCreditosRealizadosDescontoParcelamento[2];
                Integer idCategoriaConta = (Integer) dadosCreditosRealizadosDescontoParcelamento[3];

                BigDecimal valorCategoria = (BigDecimal) dadosCreditosRealizadosDescontoParcelamento[4];

                if (valorCategoria != null
                        && valorCategoria.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento,
                            idGerenciaRegionalConta,
                            idUnidadeNegocioConta,
                            idLocalidadeConta,
                            idCategoriaConta,
                            valorCategoria.multiply(new BigDecimal("-1")),
                            LancamentoTipo.DOCUMENTOS_EMITIDOS,
                            300,
                            LancamentoItem.DESCONTOS_CONCEDIDOS_NO_PARCELAMENTO,
                            20, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

            }

            colecaoDadosCreditosRealizadosDescontoParcelamento = null;

        }

        // Descontos condicionais
        Collection colecaoDadosCreditosRealizadosDescontoCondicional = repositorioFinanceiro
                .pesquisarDadosCreditosRealizadosCategoriaDescontoCondicional(
                        anoMesAnteriorFaturamento, idLocalidade);

        if (colecaoDadosCreditosRealizadosDescontoCondicional != null
                && !colecaoDadosCreditosRealizadosDescontoCondicional.isEmpty()) {

            Iterator colecaoDadosCreditosRealizadosDescontoCondicionalIterator = colecaoDadosCreditosRealizadosDescontoCondicional
                    .iterator();

            while (colecaoDadosCreditosRealizadosDescontoCondicionalIterator
                    .hasNext()) {

                Object[] dadosCreditosRealizadosDescontoCondicional = (Object[]) colecaoDadosCreditosRealizadosDescontoCondicionalIterator
                        .next();

                Integer idGerenciaRegionalConta = (Integer) dadosCreditosRealizadosDescontoCondicional[0];
                Integer idUnidadeNegocioConta = (Integer) dadosCreditosRealizadosDescontoCondicional[1];
                Integer idLocalidadeConta = (Integer) dadosCreditosRealizadosDescontoCondicional[2];
                Integer idCategoriaConta = (Integer) dadosCreditosRealizadosDescontoCondicional[3];

                BigDecimal valorCategoria = (BigDecimal) dadosCreditosRealizadosDescontoCondicional[4];

                if (valorCategoria != null
                        && valorCategoria.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento, idGerenciaRegionalConta,
                            idUnidadeNegocioConta, idLocalidadeConta,
                            idCategoriaConta, valorCategoria.multiply(new BigDecimal("-1")),
                            LancamentoTipo.DOCUMENTOS_EMITIDOS, 300,
                            LancamentoItem.DESCONTOS_CONDICIONAIS, 30, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

            }

            colecaoDadosCreditosRealizadosDescontoCondicional = null;

        }

        // Descontos incondicionais
        Collection colecaoDadosCreditosRealizadosDescontoIncondicional = repositorioFinanceiro
                .pesquisarDadosCreditosRealizadosCategoriaDescontoIncondicional(
                        anoMesAnteriorFaturamento, idLocalidade);

        if (colecaoDadosCreditosRealizadosDescontoIncondicional != null
                && !colecaoDadosCreditosRealizadosDescontoIncondicional
                        .isEmpty()) {

            Iterator colecaoDadosCreditosRealizadosDescontoIncondicionalIterator = colecaoDadosCreditosRealizadosDescontoIncondicional
                    .iterator();

            while (colecaoDadosCreditosRealizadosDescontoIncondicionalIterator
                    .hasNext()) {

                Object[] dadosCreditosRealizadosDescontoIncondicional = (Object[]) colecaoDadosCreditosRealizadosDescontoIncondicionalIterator
                        .next();

                Integer idGerenciaRegionalConta = (Integer) dadosCreditosRealizadosDescontoIncondicional[0];
                Integer idUnidadeNegocioConta = (Integer) dadosCreditosRealizadosDescontoIncondicional[1];
                Integer idLocalidadeConta = (Integer) dadosCreditosRealizadosDescontoIncondicional[2];
                Integer idCategoriaConta = (Integer) dadosCreditosRealizadosDescontoIncondicional[3];

                BigDecimal valorCategoria = (BigDecimal) dadosCreditosRealizadosDescontoIncondicional[4];

                if (valorCategoria != null
                        && valorCategoria.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento, idGerenciaRegionalConta,
                            idUnidadeNegocioConta, idLocalidadeConta,
                            idCategoriaConta, valorCategoria.multiply(new BigDecimal("-1")),
                            LancamentoTipo.DOCUMENTOS_EMITIDOS, 300,
                            LancamentoItem.DESCONTOS_INCONDICIONAIS, 40, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

            }

            colecaoDadosCreditosRealizadosDescontoIncondicional = null;

        }

        // Ajustes para Zerar a Conta
        Collection colecaoDadosCreditosRealizadosAjusteZerarConta = repositorioFinanceiro
                .pesquisarDadosCreditosRealizadosCategoriaAjusteZerarConta(
                        anoMesAnteriorFaturamento, idLocalidade);

        if (colecaoDadosCreditosRealizadosAjusteZerarConta != null
                && !colecaoDadosCreditosRealizadosAjusteZerarConta.isEmpty()) {

            Iterator colecaoDadosCreditosRealizadosAjusteZerarContaIterator = colecaoDadosCreditosRealizadosAjusteZerarConta
                    .iterator();

            while (colecaoDadosCreditosRealizadosAjusteZerarContaIterator
                    .hasNext()) {

                Object[] dadosCreditosRealizadosAjusteZerarConta = (Object[]) colecaoDadosCreditosRealizadosAjusteZerarContaIterator
                        .next();

                Integer idGerenciaRegionalConta = (Integer) dadosCreditosRealizadosAjusteZerarConta[0];
                Integer idUnidadeNegocioConta = (Integer) dadosCreditosRealizadosAjusteZerarConta[1];
                Integer idLocalidadeConta = (Integer) dadosCreditosRealizadosAjusteZerarConta[2];
                Integer idCategoriaConta = (Integer) dadosCreditosRealizadosAjusteZerarConta[3];

                BigDecimal valorCategoria = (BigDecimal) dadosCreditosRealizadosAjusteZerarConta[4];

                if (valorCategoria != null
                        && valorCategoria.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento, idGerenciaRegionalConta,
                            idUnidadeNegocioConta, idLocalidadeConta,
                            idCategoriaConta, valorCategoria.multiply(new BigDecimal("-1")),
                            LancamentoTipo.DOCUMENTOS_EMITIDOS, 300,
                            LancamentoItem.AJUSTES_PARA_ZERAR_CONTA, 50, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

            }

            colecaoDadosCreditosRealizadosAjusteZerarConta = null;

        }

        // Devoluções
        Collection colecaoDadosCreditosRealizadosDevolucao = repositorioFinanceiro
                .pesquisarDadosCreditosRealizadosCategoriaDevolucao(
                        anoMesAnteriorFaturamento, idLocalidade);

        if (colecaoDadosCreditosRealizadosDevolucao != null
                && !colecaoDadosCreditosRealizadosDevolucao.isEmpty()) {

            Iterator colecaoDadosCreditosRealizadosDevolucaoIterator = colecaoDadosCreditosRealizadosDevolucao
                    .iterator();

            while (colecaoDadosCreditosRealizadosDevolucaoIterator.hasNext()) {

                Object[] dadosCreditosRealizadosDevolucao = (Object[]) colecaoDadosCreditosRealizadosDevolucaoIterator
                        .next();

                Integer idGerenciaRegionalConta = (Integer) dadosCreditosRealizadosDevolucao[0];
                Integer idUnidadeNegocioConta = (Integer) dadosCreditosRealizadosDevolucao[1];
                Integer idLocalidadeConta = (Integer) dadosCreditosRealizadosDevolucao[2];
                Integer idCategoriaConta = (Integer) dadosCreditosRealizadosDevolucao[3];

                BigDecimal valorCategoria = (BigDecimal) dadosCreditosRealizadosDevolucao[4];

                if (valorCategoria != null
                        && valorCategoria.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento, idGerenciaRegionalConta,
                            idUnidadeNegocioConta, idLocalidadeConta,
                            idCategoriaConta, valorCategoria.multiply(new BigDecimal("-1")),
                            LancamentoTipo.DOCUMENTOS_EMITIDOS, 300,
                            LancamentoItem.VALORES_COBRADOS_INDEVIDAMENTE, 60, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

            }

            colecaoDadosCreditosRealizadosDevolucao = null;

        }

    }

    /**
     * [UC0714] Gerar Contas a Receber Contábil
     *
     * Adiciona os dados de débitos a cobrar
     *
     * @author Rafael Corrêa
     * @date 08/11/2007
     *
     */
    private void adicionarContaAReceberContabilDebitosACobrar(
            Integer idLocalidade, int anoMesAnteriorFaturamento,
            Collection colecaoContasAReceberContabil)
            throws ErroRepositorioException {

        // Serviço
        Collection colecaoDadosDebitosACobrarServico = repositorioFinanceiro
                .pesquisarDadosDebitoACobrarCategoriaServico(
                        anoMesAnteriorFaturamento, idLocalidade);

        if (colecaoDadosDebitosACobrarServico != null
                && !colecaoDadosDebitosACobrarServico.isEmpty()) {

            Iterator colecaoDadosDebitosACobrarServicoIterator = colecaoDadosDebitosACobrarServico
                    .iterator();

            while (colecaoDadosDebitosACobrarServicoIterator.hasNext()) {

                Object[] dadosDebitosACobrarServico = (Object[]) colecaoDadosDebitosACobrarServicoIterator
                        .next();

                Integer idGerenciaRegionalDebito = (Integer) dadosDebitosACobrarServico[0];
                Integer idUnidadeNegocioDebito = (Integer) dadosDebitosACobrarServico[1];
                Integer idLocalidadeDebito = (Integer) dadosDebitosACobrarServico[2];
                Integer idCategoriaDebito = (Integer) dadosDebitosACobrarServico[3];
                Integer idLancamentoItemContabil = (Integer) dadosDebitosACobrarServico[4];

                BigDecimal valorCurtoPrazo = (BigDecimal) dadosDebitosACobrarServico[5];
                BigDecimal valorLongoPrazo = (BigDecimal) dadosDebitosACobrarServico[6];

                // Curto Prazo
                if (valorCurtoPrazo != null
                        && valorCurtoPrazo.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento,
                            idGerenciaRegionalDebito, idUnidadeNegocioDebito,
                            idLocalidadeDebito, idCategoriaDebito,
                            valorCurtoPrazo,
                            LancamentoTipo.FINANCIAMENTOS_A_COBRAR, 400,
                            LancamentoItem.FINANCIAMENTOS_A_COBRAR_CURTO_PRAZO,
                            10, idLancamentoItemContabil);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

                // Longo Prazo
                if (valorLongoPrazo != null
                        && valorLongoPrazo.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento,
                            idGerenciaRegionalDebito, idUnidadeNegocioDebito,
                            idLocalidadeDebito, idCategoriaDebito,
                            valorLongoPrazo,
                            LancamentoTipo.FINANCIAMENTOS_A_COBRAR, 400,
                            LancamentoItem.FINANCIAMENTOS_A_COBRAR_LONGO_PRAZO,
                            20, idLancamentoItemContabil);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

            }

            colecaoDadosDebitosACobrarServico = null;

        }

        // Documentos Emitidos
        Collection colecaoDadosDebitosACobrarDocumentosEmitidos = repositorioFinanceiro
                .pesquisarDadosDebitoACobrarCategoriaDocumentosEmitidos(
                        anoMesAnteriorFaturamento, idLocalidade);

        if (colecaoDadosDebitosACobrarDocumentosEmitidos != null
                && !colecaoDadosDebitosACobrarDocumentosEmitidos.isEmpty()) {

            Iterator colecaoDadosDebitosACobrarDocumentosEmitidosIterator = colecaoDadosDebitosACobrarDocumentosEmitidos
                    .iterator();

            while (colecaoDadosDebitosACobrarDocumentosEmitidosIterator
                    .hasNext()) {

                Object[] dadosDebitosACobrarDocumentosEmitidos = (Object[]) colecaoDadosDebitosACobrarDocumentosEmitidosIterator
                        .next();

                Integer idGerenciaRegionalDebito = (Integer) dadosDebitosACobrarDocumentosEmitidos[0];
                Integer idUnidadeNegocioDebito = (Integer) dadosDebitosACobrarDocumentosEmitidos[1];
                Integer idLocalidadeDebito = (Integer) dadosDebitosACobrarDocumentosEmitidos[2];
                Integer idCategoriaDebito = (Integer) dadosDebitosACobrarDocumentosEmitidos[3];

                BigDecimal valorCurtoPrazo = (BigDecimal) dadosDebitosACobrarDocumentosEmitidos[4];
                BigDecimal valorLongoPrazo = (BigDecimal) dadosDebitosACobrarDocumentosEmitidos[5];

                // Curto Prazo
                if (valorCurtoPrazo != null
                        && valorCurtoPrazo.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento,
                            idGerenciaRegionalDebito, idUnidadeNegocioDebito,
                            idLocalidadeDebito, idCategoriaDebito,
                            valorCurtoPrazo,
                            LancamentoTipo.PARCELAMENTOS_A_COBRAR_CURTO_PRAZO,
                            500, LancamentoItem.DOCUMENTOS_EMITIDOS, 10, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

                // Longo Prazo
                if (valorLongoPrazo != null
                        && valorLongoPrazo.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento,
                            idGerenciaRegionalDebito, idUnidadeNegocioDebito,
                            idLocalidadeDebito, idCategoriaDebito,
                            valorLongoPrazo,
                            LancamentoTipo.PARCELAMENTOS_A_COBRAR_LONGO_PRAZO,
                            600, LancamentoItem.DOCUMENTOS_EMITIDOS, 10, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

            }

            colecaoDadosDebitosACobrarDocumentosEmitidos = null;

        }

        // Financimentos A Cobrar de Curto Prazo
        Collection colecaoDadosDebitosACobrarFinanciamentosCurtoPrazo = repositorioFinanceiro
                .pesquisarDadosDebitoACobrarCategoriaFinancimentosCurtoPrazo(
                        anoMesAnteriorFaturamento, idLocalidade);

        if (colecaoDadosDebitosACobrarFinanciamentosCurtoPrazo != null
                && !colecaoDadosDebitosACobrarFinanciamentosCurtoPrazo
                        .isEmpty()) {

            Iterator colecaoDadosDebitosACobrarFinanciamentosCurtoPrazoIterator = colecaoDadosDebitosACobrarFinanciamentosCurtoPrazo
                    .iterator();

            while (colecaoDadosDebitosACobrarFinanciamentosCurtoPrazoIterator
                    .hasNext()) {

                Object[] dadosDebitosACobrarFinanciamentosCurtoPrazo = (Object[]) colecaoDadosDebitosACobrarFinanciamentosCurtoPrazoIterator
                        .next();

                Integer idGerenciaRegionalDebito = (Integer) dadosDebitosACobrarFinanciamentosCurtoPrazo[0];
                Integer idUnidadeNegocioDebito = (Integer) dadosDebitosACobrarFinanciamentosCurtoPrazo[1];
                Integer idLocalidadeDebito = (Integer) dadosDebitosACobrarFinanciamentosCurtoPrazo[2];
                Integer idCategoriaDebito = (Integer) dadosDebitosACobrarFinanciamentosCurtoPrazo[3];

                BigDecimal valorCurtoPrazo = (BigDecimal) dadosDebitosACobrarFinanciamentosCurtoPrazo[4];
                BigDecimal valorLongoPrazo = (BigDecimal) dadosDebitosACobrarFinanciamentosCurtoPrazo[5];

                // Curto Prazo
                if (valorCurtoPrazo != null
                        && valorCurtoPrazo.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento,
                            idGerenciaRegionalDebito, idUnidadeNegocioDebito,
                            idLocalidadeDebito, idCategoriaDebito,
                            valorCurtoPrazo,
                            LancamentoTipo.PARCELAMENTOS_A_COBRAR_CURTO_PRAZO,
                            500,
                            LancamentoItem.FINANCIAMENTOS_A_COBRAR_CURTO_PRAZO,
                            20, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

                // Longo Prazo
                if (valorLongoPrazo != null
                        && valorLongoPrazo.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento,
                            idGerenciaRegionalDebito, idUnidadeNegocioDebito,
                            idLocalidadeDebito, idCategoriaDebito,
                            valorLongoPrazo,
                            LancamentoTipo.PARCELAMENTOS_A_COBRAR_LONGO_PRAZO,
                            600,
                            LancamentoItem.FINANCIAMENTOS_A_COBRAR_CURTO_PRAZO,
                            20, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

            }

            colecaoDadosDebitosACobrarFinanciamentosCurtoPrazo = null;

        }

        // Financimentos A Cobrar de Longo Prazo
        Collection colecaoDadosDebitosACobrarFinanciamentosLongoPrazo = repositorioFinanceiro
                .pesquisarDadosDebitoACobrarCategoriaFinancimentosLongoPrazo(
                        anoMesAnteriorFaturamento, idLocalidade);

        if (colecaoDadosDebitosACobrarFinanciamentosLongoPrazo != null
                && !colecaoDadosDebitosACobrarFinanciamentosLongoPrazo
                        .isEmpty()) {

            Iterator colecaoDadosDebitosACobrarFinanciamentosLongoPrazoIterator = colecaoDadosDebitosACobrarFinanciamentosLongoPrazo
                    .iterator();

            while (colecaoDadosDebitosACobrarFinanciamentosLongoPrazoIterator
                    .hasNext()) {

                Object[] dadosDebitosACobrarFinanciamentosLongoPrazo = (Object[]) colecaoDadosDebitosACobrarFinanciamentosLongoPrazoIterator
                        .next();

                Integer idGerenciaRegionalDebito = (Integer) dadosDebitosACobrarFinanciamentosLongoPrazo[0];
                Integer idUnidadeNegocioDebito = (Integer) dadosDebitosACobrarFinanciamentosLongoPrazo[1];
                Integer idLocalidadeDebito = (Integer) dadosDebitosACobrarFinanciamentosLongoPrazo[2];
                Integer idCategoriaDebito = (Integer) dadosDebitosACobrarFinanciamentosLongoPrazo[3];

                BigDecimal valorCurtoPrazo = (BigDecimal) dadosDebitosACobrarFinanciamentosLongoPrazo[4];
                BigDecimal valorLongoPrazo = (BigDecimal) dadosDebitosACobrarFinanciamentosLongoPrazo[5];

                // Curto Prazo
                if (valorCurtoPrazo != null
                        && valorCurtoPrazo.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento,
                            idGerenciaRegionalDebito, idUnidadeNegocioDebito,
                            idLocalidadeDebito, idCategoriaDebito,
                            valorCurtoPrazo,
                            LancamentoTipo.PARCELAMENTOS_A_COBRAR_CURTO_PRAZO,
                            500,
                            LancamentoItem.FINANCIAMENTOS_A_COBRAR_LONGO_PRAZO,
                            30, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

                // Longo Prazo
                if (valorLongoPrazo != null
                        && valorLongoPrazo.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento,
                            idGerenciaRegionalDebito, idUnidadeNegocioDebito,
                            idLocalidadeDebito, idCategoriaDebito,
                            valorLongoPrazo,
                            LancamentoTipo.PARCELAMENTOS_A_COBRAR_LONGO_PRAZO,
                            600,
                            LancamentoItem.FINANCIAMENTOS_A_COBRAR_LONGO_PRAZO,
                            30, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

            }

            colecaoDadosDebitosACobrarFinanciamentosLongoPrazo = null;

        }

        // Parcelamentos A Cobrar de Curto Prazo
        Collection colecaoDadosDebitosACobrarParcelamentosCurtoPrazo = repositorioFinanceiro
                .pesquisarDadosDebitoACobrarCategoriaParcelamentosCurtoPrazo(
                        anoMesAnteriorFaturamento, idLocalidade);

        if (colecaoDadosDebitosACobrarParcelamentosCurtoPrazo != null
                && !colecaoDadosDebitosACobrarParcelamentosCurtoPrazo.isEmpty()) {

            Iterator colecaoDadosDebitosACobrarParcelamentosCurtoPrazoIterator = colecaoDadosDebitosACobrarParcelamentosCurtoPrazo
                    .iterator();

            while (colecaoDadosDebitosACobrarParcelamentosCurtoPrazoIterator
                    .hasNext()) {

                Object[] dadosDebitosACobrarParcelamentosCurtoPrazo = (Object[]) colecaoDadosDebitosACobrarParcelamentosCurtoPrazoIterator
                        .next();

                Integer idGerenciaRegionalDebito = (Integer) dadosDebitosACobrarParcelamentosCurtoPrazo[0];
                Integer idUnidadeNegocioDebito = (Integer) dadosDebitosACobrarParcelamentosCurtoPrazo[1];
                Integer idLocalidadeDebito = (Integer) dadosDebitosACobrarParcelamentosCurtoPrazo[2];
                Integer idCategoriaDebito = (Integer) dadosDebitosACobrarParcelamentosCurtoPrazo[3];

                BigDecimal valorCurtoPrazo = (BigDecimal) dadosDebitosACobrarParcelamentosCurtoPrazo[4];
                BigDecimal valorLongoPrazo = (BigDecimal) dadosDebitosACobrarParcelamentosCurtoPrazo[5];

                // Curto Prazo
                if (valorCurtoPrazo != null
                        && valorCurtoPrazo.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento,
                            idGerenciaRegionalDebito, idUnidadeNegocioDebito,
                            idLocalidadeDebito, idCategoriaDebito,
                            valorCurtoPrazo,
                            LancamentoTipo.PARCELAMENTOS_A_COBRAR_CURTO_PRAZO,
                            500,
                            LancamentoItem.PARCELAMENTOS_A_COBRAR_CURTO_PRAZO,
                            40, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

                // Longo Prazo
                if (valorLongoPrazo != null
                        && valorLongoPrazo.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento,
                            idGerenciaRegionalDebito, idUnidadeNegocioDebito,
                            idLocalidadeDebito, idCategoriaDebito,
                            valorLongoPrazo,
                            LancamentoTipo.PARCELAMENTOS_A_COBRAR_LONGO_PRAZO,
                            600,
                            LancamentoItem.PARCELAMENTOS_A_COBRAR_CURTO_PRAZO,
                            40, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

            }

            colecaoDadosDebitosACobrarParcelamentosCurtoPrazo = null;

        }

        // Parcelamentos A Cobrar de Longo Prazo
        Collection colecaoDadosDebitosACobrarParcelamentosLongoPrazo = repositorioFinanceiro
                .pesquisarDadosDebitoACobrarCategoriaParcelamentosLongoPrazo(
                        anoMesAnteriorFaturamento, idLocalidade);

        if (colecaoDadosDebitosACobrarParcelamentosLongoPrazo != null
                && !colecaoDadosDebitosACobrarParcelamentosLongoPrazo.isEmpty()) {

            Iterator colecaoDadosDebitosACobrarParcelamentosLongoPrazoIterator = colecaoDadosDebitosACobrarParcelamentosLongoPrazo
                    .iterator();

            while (colecaoDadosDebitosACobrarParcelamentosLongoPrazoIterator
                    .hasNext()) {

                Object[] dadosDebitosACobrarParcelamentosLongoPrazo = (Object[]) colecaoDadosDebitosACobrarParcelamentosLongoPrazoIterator
                        .next();

                Integer idGerenciaRegionalDebito = (Integer) dadosDebitosACobrarParcelamentosLongoPrazo[0];
                Integer idUnidadeNegocioDebito = (Integer) dadosDebitosACobrarParcelamentosLongoPrazo[1];
                Integer idLocalidadeDebito = (Integer) dadosDebitosACobrarParcelamentosLongoPrazo[2];
                Integer idCategoriaDebito = (Integer) dadosDebitosACobrarParcelamentosLongoPrazo[3];

                BigDecimal valorCurtoPrazo = (BigDecimal) dadosDebitosACobrarParcelamentosLongoPrazo[4];
                BigDecimal valorLongoPrazo = (BigDecimal) dadosDebitosACobrarParcelamentosLongoPrazo[5];

                // Curto Prazo
                if (valorCurtoPrazo != null
                        && valorCurtoPrazo.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento,
                            idGerenciaRegionalDebito, idUnidadeNegocioDebito,
                            idLocalidadeDebito, idCategoriaDebito,
                            valorCurtoPrazo,
                            LancamentoTipo.PARCELAMENTOS_A_COBRAR_CURTO_PRAZO,
                            500,
                            LancamentoItem.PARCELAMENTOS_A_COBRAR_LONGO_PRAZO,
                            50, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

                // Longo Prazo
                if (valorLongoPrazo != null
                        && valorLongoPrazo.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento,
                            idGerenciaRegionalDebito, idUnidadeNegocioDebito,
                            idLocalidadeDebito, idCategoriaDebito,
                            valorLongoPrazo,
                            LancamentoTipo.PARCELAMENTOS_A_COBRAR_LONGO_PRAZO,
                            600,
                            LancamentoItem.PARCELAMENTOS_A_COBRAR_LONGO_PRAZO,
                            50, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

            }

            colecaoDadosDebitosACobrarParcelamentosLongoPrazo = null;

        }
       
        // Juros Cobrados
        Collection colecaoDadosJurosCobrados = repositorioFinanceiro
                .pesquisarDadosDebitoACobrarCategoriaJurosCobrados(
                        anoMesAnteriorFaturamento, idLocalidade);

        if (colecaoDadosJurosCobrados != null
                && !colecaoDadosJurosCobrados.isEmpty()) {

            Iterator colecaoDadosJurosCobradosIterator = colecaoDadosJurosCobrados
                    .iterator();

            while (colecaoDadosJurosCobradosIterator
                    .hasNext()) {

                Object[] dadosJurosCobrados = (Object[]) colecaoDadosJurosCobradosIterator
                        .next();

                Integer idGerenciaRegionalDebito = (Integer) dadosJurosCobrados[0];
                Integer idUnidadeNegocioDebito = (Integer) dadosJurosCobrados[1];
                Integer idLocalidadeDebito = (Integer) dadosJurosCobrados[2];
                Integer idCategoriaDebito = (Integer) dadosJurosCobrados[3];

                BigDecimal valorCurtoPrazo = (BigDecimal) dadosJurosCobrados[4];
                BigDecimal valorLongoPrazo = (BigDecimal) dadosJurosCobrados[5];

                // Curto Prazo
                if (valorCurtoPrazo != null
                        && valorCurtoPrazo.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento,
                            idGerenciaRegionalDebito, idUnidadeNegocioDebito,
                            idLocalidadeDebito, idCategoriaDebito,
                            valorCurtoPrazo,
                            LancamentoTipo.PARCELAMENTOS_A_COBRAR_CURTO_PRAZO,
                            500,
                            LancamentoItem.JUROS_COBRADOS,
                            55, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

                // Longo Prazo
                if (valorLongoPrazo != null
                        && valorLongoPrazo.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento,
                            idGerenciaRegionalDebito, idUnidadeNegocioDebito,
                            idLocalidadeDebito, idCategoriaDebito,
                            valorLongoPrazo,
                            LancamentoTipo.PARCELAMENTOS_A_COBRAR_LONGO_PRAZO,
                            600,
                            LancamentoItem.JUROS_COBRADOS,
                            55, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

            }

            colecaoDadosJurosCobrados = null;

        }

        // Arrasto
        Collection colecaoDadosDebitosACobrarArrasto = repositorioFinanceiro
                .pesquisarDadosDebitoACobrarCategoriaArrasto(
                        anoMesAnteriorFaturamento, idLocalidade);

        if (colecaoDadosDebitosACobrarArrasto != null
                && !colecaoDadosDebitosACobrarArrasto.isEmpty()) {

            Iterator colecaoDadosDebitosACobrarArrastoIterator = colecaoDadosDebitosACobrarArrasto
                    .iterator();

            while (colecaoDadosDebitosACobrarArrastoIterator.hasNext()) {

                Object[] dadosDebitosACobrarArrasto = (Object[]) colecaoDadosDebitosACobrarArrastoIterator
                        .next();

                Integer idGerenciaRegionalDebito = (Integer) dadosDebitosACobrarArrasto[0];
                Integer idUnidadeNegocioDebito = (Integer) dadosDebitosACobrarArrasto[1];
                Integer idLocalidadeDebito = (Integer) dadosDebitosACobrarArrasto[2];
                Integer idCategoriaDebito = (Integer) dadosDebitosACobrarArrasto[3];

                BigDecimal valorCategoria = (BigDecimal) dadosDebitosACobrarArrasto[4];

                if (valorCategoria != null
                        && valorCategoria.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento,
                            idGerenciaRegionalDebito, idUnidadeNegocioDebito,
                            idLocalidadeDebito, idCategoriaDebito,
                            valorCategoria,
                            LancamentoTipo.PARCELAMENTOS_A_COBRAR_CURTO_PRAZO,
                            500,
                            LancamentoItem.DEBITOS_ANTERIORES_PARA_RECOBRANCA,
                            60, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

            }

            colecaoDadosDebitosACobrarArrasto = null;

        }

    }

    /**
     * [UC0714] Gerar Contas a Receber Contábil
     *
     * Adiciona os dados de créditos a realizar
     *
     * @author Rafael Corrêa, Ivan Sergio
     * @date 08/11/2007, 30/12/2010
     *
     */
    private void adicionarContaAReceberContabilCreditosARealizar(
            Integer idLocalidade, int anoMesAnteriorFaturamento,
            Collection colecaoContasAReceberContabil)
            throws ErroRepositorioException {

        // Descontos Concedidos no Parcelamento
        Collection colecaoDadosCreditosARealizarDescontoParcelamento = repositorioFinanceiro
                .pesquisarDadosCreditosARealizarCategoriaDescontosParcelamento(
                        anoMesAnteriorFaturamento, idLocalidade);
        
        // Valor Residual Descontos Concedidos no Parcelamento
        Collection<Object[]> colecaoDadosCreditosARealizarValorResidualDescontoParcelamento = repositorioFinanceiro
                .pesquisarDadosCreditosARealizarCategoriaValorResidualDescontosParcelamento(
                        anoMesAnteriorFaturamento, idLocalidade);

        if (colecaoDadosCreditosARealizarDescontoParcelamento != null
                && !colecaoDadosCreditosARealizarDescontoParcelamento.isEmpty()) {

            Iterator colecaoDadosCreditosARealizarDescontoParcelamentoIterator = colecaoDadosCreditosARealizarDescontoParcelamento
                    .iterator();

            while (colecaoDadosCreditosARealizarDescontoParcelamentoIterator
                    .hasNext()) {

                Object[] dadosCreditosARealizarDescontoParcelamento = (Object[]) colecaoDadosCreditosARealizarDescontoParcelamentoIterator
                        .next();

                Integer idGerenciaRegionalCredito = (Integer) dadosCreditosARealizarDescontoParcelamento[0];
                Integer idUnidadeNegocioCredito = (Integer) dadosCreditosARealizarDescontoParcelamento[1];
                Integer idLocalidadeCredito = (Integer) dadosCreditosARealizarDescontoParcelamento[2];
                Integer idCategoriaCredito = (Integer) dadosCreditosARealizarDescontoParcelamento[3];

                BigDecimal valorCurtoPrazo = (BigDecimal) dadosCreditosARealizarDescontoParcelamento[4];
                BigDecimal valorLongoPrazo = (BigDecimal) dadosCreditosARealizarDescontoParcelamento[5];
                
                // Verifica se existe valor residual para a mesma categoria, caso exista acumula o valor
                if (colecaoDadosCreditosARealizarValorResidualDescontoParcelamento != null && !colecaoDadosCreditosARealizarValorResidualDescontoParcelamento.isEmpty()) {
                	
                	Collection colecaoRemovidos = new ArrayList<Object[]>();
                	
                	for (Object[] dadosCreditosARealizarValorDescontoParcelamento : colecaoDadosCreditosARealizarValorResidualDescontoParcelamento) {
                        Integer idCategoriaCreditoValorResidual = (Integer) dadosCreditosARealizarValorDescontoParcelamento[3];

                        BigDecimal valorResidual = (BigDecimal) dadosCreditosARealizarValorDescontoParcelamento[4];
                        
                        if (idCategoriaCreditoValorResidual.equals(idCategoriaCredito)) {
                        	if (valorResidual != null) {
                        		valorCurtoPrazo = valorCurtoPrazo.add(valorResidual);
                        	}
                        	colecaoRemovidos.add(dadosCreditosARealizarValorDescontoParcelamento);
                        }
                        
					}
                	
                	colecaoDadosCreditosARealizarValorResidualDescontoParcelamento.removeAll(colecaoRemovidos);
                	
                }

                // Curto Prazo
                if (valorCurtoPrazo != null
                        && valorCurtoPrazo.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento,
                            idGerenciaRegionalCredito, idUnidadeNegocioCredito,
                            idLocalidadeCredito, idCategoriaCredito,
                            valorCurtoPrazo.multiply(new BigDecimal("-1")),
                            LancamentoTipo.PARCELAMENTOS_A_COBRAR_CURTO_PRAZO,
                            500, LancamentoItem.DESCONTOS_CONCEDIDOS, 70, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

                // Longo Prazo
                if (valorLongoPrazo != null
                        && valorLongoPrazo.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento,
                            idGerenciaRegionalCredito, idUnidadeNegocioCredito,
                            idLocalidadeCredito, idCategoriaCredito,
                            valorLongoPrazo.multiply(new BigDecimal("-1")),
                            LancamentoTipo.PARCELAMENTOS_A_COBRAR_LONGO_PRAZO,
                            600, LancamentoItem.DESCONTOS_CONCEDIDOS, 60, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

            }

            colecaoDadosCreditosARealizarDescontoParcelamento = null;

        }
        
        // Verifica se restou algum dado que não estava presente na colecaoDadosCreditosARealizarDescontoParcelamento
        if (colecaoDadosCreditosARealizarValorResidualDescontoParcelamento != null
                && !colecaoDadosCreditosARealizarValorResidualDescontoParcelamento.isEmpty()) {
        	for (Object[] dadosCreditosARealizarValorDescontoParcelamento : colecaoDadosCreditosARealizarValorResidualDescontoParcelamento) {
        		
        		Integer idGerenciaRegionalCreditoValorResidual = (Integer) dadosCreditosARealizarValorDescontoParcelamento[0];
        		Integer idUnidadeNegocioCreditoValorResidual = (Integer) dadosCreditosARealizarValorDescontoParcelamento[1];
                Integer idLocalidadeCreditoValorResidual = (Integer) dadosCreditosARealizarValorDescontoParcelamento[2];
                Integer idCategoriaCreditoValorResidual = (Integer) dadosCreditosARealizarValorDescontoParcelamento[3];
                BigDecimal valorResidual = (BigDecimal) dadosCreditosARealizarValorDescontoParcelamento[4];
                
                if (valorResidual != null
                        && valorResidual.compareTo(new BigDecimal("0.00")) > 0) {
                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento,
                            idGerenciaRegionalCreditoValorResidual, idUnidadeNegocioCreditoValorResidual,
                            idLocalidadeCreditoValorResidual, idCategoriaCreditoValorResidual,
                            valorResidual.multiply(new BigDecimal("-1")),
                            LancamentoTipo.PARCELAMENTOS_A_COBRAR_CURTO_PRAZO,
                            500, LancamentoItem.DESCONTOS_CONCEDIDOS, 70, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }
			}
        	
        	colecaoDadosCreditosARealizarValorResidualDescontoParcelamento = null;
        }

        // Devoluções
        Collection colecaoDadosCreditosARealizarDevolucao = repositorioFinanceiro
                .pesquisarDadosCreditosARealizarCategoriaDevolucao(
                        anoMesAnteriorFaturamento, idLocalidade);
        
        // Valor Residual Devolução
        Collection<Object[]> colecaoDadosCreditosARealizarValorResidualDevolucao = repositorioFinanceiro
                .pesquisarDadosCreditosARealizarCategoriaValorResidualDevolucao(
                        anoMesAnteriorFaturamento, idLocalidade);

        if (colecaoDadosCreditosARealizarDevolucao != null
                && !colecaoDadosCreditosARealizarDevolucao.isEmpty()) {

            Iterator colecaoDadosCreditosARealizarDevolucaoIterator = colecaoDadosCreditosARealizarDevolucao
                    .iterator();

            while (colecaoDadosCreditosARealizarDevolucaoIterator.hasNext()) {

                Object[] dadosCreditosARealizarDevolucao = (Object[]) colecaoDadosCreditosARealizarDevolucaoIterator
                        .next();

                Integer idGerenciaRegionalCredito = (Integer) dadosCreditosARealizarDevolucao[0];
                Integer idUnidadeNegocioCredito = (Integer) dadosCreditosARealizarDevolucao[1];
                Integer idLocalidadeCredito = (Integer) dadosCreditosARealizarDevolucao[2];
                Integer idCategoriaCredito = (Integer) dadosCreditosARealizarDevolucao[3];

                BigDecimal valorCategoria = (BigDecimal) dadosCreditosARealizarDevolucao[4];
                
                // Verifica se existe valor residual para a mesma categoria, caso exista acumula o valor
                if (colecaoDadosCreditosARealizarValorResidualDevolucao != null && !colecaoDadosCreditosARealizarValorResidualDevolucao.isEmpty()) {
                	
                	Collection colecaoRemovidos = new ArrayList<Object[]>();
                	
                	for (Object[] dadosCreditosARealizarValorResidualDevolucao : colecaoDadosCreditosARealizarValorResidualDevolucao) {
                        Integer idCategoriaCreditoValorResidual = (Integer) dadosCreditosARealizarValorResidualDevolucao[3];

                        BigDecimal valorResidual = (BigDecimal) dadosCreditosARealizarValorResidualDevolucao[4];
                        
                        if (idCategoriaCreditoValorResidual.equals(idCategoriaCredito)) {
                        	
                        	if (valorResidual != null) {
                        		valorCategoria = valorCategoria.add(valorResidual);
                        	}
                        	
                        	colecaoRemovidos.add(dadosCreditosARealizarValorResidualDevolucao);
                        	break;
                        	
                        }
                        
					}
                	
                	colecaoDadosCreditosARealizarValorResidualDevolucao.removeAll(colecaoRemovidos);
                	
                }

                if (valorCategoria != null
                        && valorCategoria.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento,
                            idGerenciaRegionalCredito, idUnidadeNegocioCredito,
                            idLocalidadeCredito, idCategoriaCredito,
                            valorCategoria.multiply(new BigDecimal("-1")),
                            LancamentoTipo.CREDITOS_A_REALIZAR, 400,
                            LancamentoItem.VALORES_COBRADOS_INDEVIDAMENTE, 30, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

            }

            colecaoDadosCreditosARealizarDevolucao = null;

        }
        
        // Verifica se restou algum dado que não estava presente na colecaoDadosCreditosARealizarDevolucao
        if (colecaoDadosCreditosARealizarValorResidualDevolucao != null
                && !colecaoDadosCreditosARealizarValorResidualDevolucao.isEmpty()) {
        	for (Object[] dadosCreditosARealizarValorResidualDevolucao : colecaoDadosCreditosARealizarValorResidualDevolucao) {
        		
        		Integer idGerenciaRegionalCreditoValorResidual = (Integer) dadosCreditosARealizarValorResidualDevolucao[0];
        		Integer idUnidadeNegocioCreditoValorResidual = (Integer) dadosCreditosARealizarValorResidualDevolucao[1];
                Integer idLocalidadeCreditoValorResidual = (Integer) dadosCreditosARealizarValorResidualDevolucao[2];
                Integer idCategoriaCreditoValorResidual = (Integer) dadosCreditosARealizarValorResidualDevolucao[3];
                BigDecimal valorResidual = (BigDecimal) dadosCreditosARealizarValorResidualDevolucao[4];
                
                if (valorResidual != null
                        && valorResidual.compareTo(new BigDecimal("0.00")) > 0) {
                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento,
                            idGerenciaRegionalCreditoValorResidual, idUnidadeNegocioCreditoValorResidual,
                            idLocalidadeCreditoValorResidual, idCategoriaCreditoValorResidual,
                            valorResidual.multiply(new BigDecimal("-1")),
                            LancamentoTipo.CREDITOS_A_REALIZAR, 400,
                            LancamentoItem.VALORES_COBRADOS_INDEVIDAMENTE, 30, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }
			}
        	
        	colecaoDadosCreditosARealizarValorResidualDevolucao = null;
        }

        // Descontos incondicionais
        Collection colecaoDadosCreditosARealizarDescontoIncondicional = repositorioFinanceiro
                .pesquisarDadosCreditosARealizarCategoriaDescontoIncondicional(
                        anoMesAnteriorFaturamento, idLocalidade);
        
        // Valor Residual Descontos incondicionais
        Collection<Object[]> colecaoDadosCreditosARealizarValorResidualDescontoIncondicional = repositorioFinanceiro
                .pesquisarDadosCreditosARealizarCategoriaValorResidualDescontoIncondicional(
                        anoMesAnteriorFaturamento, idLocalidade);

        if (colecaoDadosCreditosARealizarDescontoIncondicional != null
                && !colecaoDadosCreditosARealizarDescontoIncondicional
                        .isEmpty()) {

            Iterator colecaoDadosCreditosARealizarDescontoIncondicionalIterator = colecaoDadosCreditosARealizarDescontoIncondicional
                    .iterator();

            while (colecaoDadosCreditosARealizarDescontoIncondicionalIterator
                    .hasNext()) {

                Object[] dadosCreditosARealizarDescontoIncondicional = (Object[]) colecaoDadosCreditosARealizarDescontoIncondicionalIterator
                        .next();

                Integer idGerenciaRegionalCredito = (Integer) dadosCreditosARealizarDescontoIncondicional[0];
                Integer idUnidadeNegocioCredito = (Integer) dadosCreditosARealizarDescontoIncondicional[1];
                Integer idLocalidadeCredito = (Integer) dadosCreditosARealizarDescontoIncondicional[2];
                Integer idCategoriaCredito = (Integer) dadosCreditosARealizarDescontoIncondicional[3];

                BigDecimal valorCategoria = (BigDecimal) dadosCreditosARealizarDescontoIncondicional[4];
                
                // Verifica se existe valor residual para a mesma categoria, caso exista acumula o valor
                if (colecaoDadosCreditosARealizarValorResidualDescontoIncondicional != null && !colecaoDadosCreditosARealizarValorResidualDescontoIncondicional.isEmpty()) {
                	
                	Collection colecaoRemovidos = new ArrayList<Object[]>();
                	
                	for (Object[] dadosCreditosARealizarValorResidualDescontoIncondicional : colecaoDadosCreditosARealizarValorResidualDescontoIncondicional) {
                        Integer idCategoriaCreditoValorResidual = (Integer) dadosCreditosARealizarValorResidualDescontoIncondicional[3];

                        BigDecimal valorResidual = (BigDecimal) dadosCreditosARealizarValorResidualDescontoIncondicional[4];
                        
                        if (idCategoriaCreditoValorResidual.equals(idCategoriaCredito)) {
                        	
                        	if (valorResidual != null) {
                        		valorCategoria = valorCategoria.add(valorResidual);
                        	}
                        	
                        	colecaoRemovidos.add(dadosCreditosARealizarValorResidualDescontoIncondicional);
                        	break;
                        	
                        }
                        
					}
                	
                	colecaoDadosCreditosARealizarValorResidualDescontoIncondicional.removeAll(colecaoRemovidos);
                	
                }

                if (valorCategoria != null
                        && valorCategoria.compareTo(new BigDecimal("0.00")) > 0) {

                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento,
                            idGerenciaRegionalCredito, idUnidadeNegocioCredito,
                            idLocalidadeCredito, idCategoriaCredito,
                            valorCategoria.multiply(new BigDecimal("-1")),
                            LancamentoTipo.CREDITOS_A_REALIZAR, 400,
                            LancamentoItem.DESCONTOS_INCONDICIONAIS, 40, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }

            }

            colecaoDadosCreditosARealizarDescontoIncondicional = null;
        }
        
        // Verifica se restou algum dado que não estava presente na colecaoDadosCreditosARealizarDescontoIncondicional
        if (colecaoDadosCreditosARealizarValorResidualDescontoIncondicional != null
                && !colecaoDadosCreditosARealizarValorResidualDescontoIncondicional.isEmpty()) {
        	for (Object[] dadosCreditosARealizarValorResidualDescontoIncondicional : colecaoDadosCreditosARealizarValorResidualDescontoIncondicional) {
        		
        		Integer idGerenciaRegionalCreditoValorResidual = (Integer) dadosCreditosARealizarValorResidualDescontoIncondicional[0];
        		Integer idUnidadeNegocioCreditoValorResidual = (Integer) dadosCreditosARealizarValorResidualDescontoIncondicional[1];
                Integer idLocalidadeCreditoValorResidual = (Integer) dadosCreditosARealizarValorResidualDescontoIncondicional[2];
                Integer idCategoriaCreditoValorResidual = (Integer) dadosCreditosARealizarValorResidualDescontoIncondicional[3];
                BigDecimal valorResidual = (BigDecimal) dadosCreditosARealizarValorResidualDescontoIncondicional[4];
                
                if (valorResidual != null
                        && valorResidual.compareTo(new BigDecimal("0.00")) > 0) {
                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
                            anoMesAnteriorFaturamento,
                            idGerenciaRegionalCreditoValorResidual, idUnidadeNegocioCreditoValorResidual,
                            idLocalidadeCreditoValorResidual, idCategoriaCreditoValorResidual,
                            valorResidual.multiply(new BigDecimal("-1")),
                            LancamentoTipo.CREDITOS_A_REALIZAR, 400,
                            LancamentoItem.DESCONTOS_INCONDICIONAIS, 40, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }
			}
        	
        	colecaoDadosCreditosARealizarValorResidualDescontoIncondicional = null;
        }
        
		// Pagamento em duplicidade ou excesso
		Collection colecaoDadosCreditosARealizarPagamentoExcesso = repositorioFinanceiro
				.pesquisarDadosCreditosARealizarCategoriaPagamentoExcesso(
						anoMesAnteriorFaturamento, idLocalidade);
		
        // Valor Residual Pagamentos em Duplicidade ou Excesso
        Collection<Object[]> colecaoDadosCreditosARealizarValorResidualPagamentoExcesso = repositorioFinanceiro
                .pesquisarDadosCreditosARealizarCategoriaValorResidualPagamentoExcesso(
                        anoMesAnteriorFaturamento, idLocalidade);

		if (colecaoDadosCreditosARealizarPagamentoExcesso != null
				&& !colecaoDadosCreditosARealizarPagamentoExcesso.isEmpty()) {

			Iterator colecaoDadosCreditosARealizarPagamentoExcessoIterator = colecaoDadosCreditosARealizarPagamentoExcesso
					.iterator();

			while (colecaoDadosCreditosARealizarPagamentoExcessoIterator
					.hasNext()) {

				Object[] dadosCreditosARealizarPagamentoExcesso = (Object[]) colecaoDadosCreditosARealizarPagamentoExcessoIterator
						.next();

				Integer idGerenciaRegionalCredito = (Integer) dadosCreditosARealizarPagamentoExcesso[0];
				Integer idUnidadeNegocioCredito = (Integer) dadosCreditosARealizarPagamentoExcesso[1];
				Integer idLocalidadeCredito = (Integer) dadosCreditosARealizarPagamentoExcesso[2];
				Integer idCategoriaCredito = (Integer) dadosCreditosARealizarPagamentoExcesso[3];

				BigDecimal valorCategoria = (BigDecimal) dadosCreditosARealizarPagamentoExcesso[4];
				
				
                // Verifica se existe valor residual para a mesma categoria, caso exista acumula o valor
                if (colecaoDadosCreditosARealizarValorResidualPagamentoExcesso != null
						&& !colecaoDadosCreditosARealizarValorResidualPagamentoExcesso
								.isEmpty()) {
                	
                	Collection colecaoRemovidos = new ArrayList<Object[]>();
                	
                	for (Object[] dadosCreditosARealizarValorResidualPagamentoExcesso : colecaoDadosCreditosARealizarValorResidualPagamentoExcesso) {
                        Integer idCategoriaCreditoValorResidual = (Integer) dadosCreditosARealizarValorResidualPagamentoExcesso[3];

                        BigDecimal valorResidual = (BigDecimal) dadosCreditosARealizarValorResidualPagamentoExcesso[4];
                        
                        if (idCategoriaCreditoValorResidual.equals(idCategoriaCredito)) {
                        	
                        	if (valorResidual != null) {
                        		valorCategoria = valorCategoria.add(valorResidual);
                        	}
                        	
                        	colecaoRemovidos.add(dadosCreditosARealizarValorResidualPagamentoExcesso);
                        	break;
                        	
                        }
                        
					}
                	
                	colecaoDadosCreditosARealizarValorResidualPagamentoExcesso.removeAll(colecaoRemovidos);
                	
                }

				if (valorCategoria != null
						&& valorCategoria.compareTo(new BigDecimal("0.00")) > 0) {

					// Cria o objeto com os valores passados
					ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
							anoMesAnteriorFaturamento,
							idGerenciaRegionalCredito, idUnidadeNegocioCredito,
							idLocalidadeCredito, idCategoriaCredito,
							valorCategoria.multiply(new BigDecimal("-1")),
							LancamentoTipo.CREDITOS_A_REALIZAR, 400,
							LancamentoItem.CONTAS_PAGA_EM_DUPLICIDADE_EXCESSO,
							25, null);

					colecaoContasAReceberContabil.add(contaAReceberContabil);
				}

			}

			colecaoDadosCreditosARealizarPagamentoExcesso = null;

		}
		
        // Verifica se restou algum dado que não estava presente na colecaoDadosCreditosARealizarValorResidualPagamentoExcesso
        if (colecaoDadosCreditosARealizarValorResidualPagamentoExcesso != null
                && !colecaoDadosCreditosARealizarValorResidualPagamentoExcesso.isEmpty()) {
        	for (Object[] dadosCreditosARealizarValorResidualPagamentoExcesso : colecaoDadosCreditosARealizarValorResidualPagamentoExcesso) {
        		
        		Integer idGerenciaRegionalCreditoValorResidual = (Integer) dadosCreditosARealizarValorResidualPagamentoExcesso[0];
        		Integer idUnidadeNegocioCreditoValorResidual = (Integer) dadosCreditosARealizarValorResidualPagamentoExcesso[1];
                Integer idLocalidadeCreditoValorResidual = (Integer) dadosCreditosARealizarValorResidualPagamentoExcesso[2];
                Integer idCategoriaCreditoValorResidual = (Integer) dadosCreditosARealizarValorResidualPagamentoExcesso[3];
                BigDecimal valorResidual = (BigDecimal) dadosCreditosARealizarValorResidualPagamentoExcesso[4];
                
                if (valorResidual != null
                        && valorResidual.compareTo(new BigDecimal("0.00")) > 0) {
                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
							anoMesAnteriorFaturamento,
							idGerenciaRegionalCreditoValorResidual,
							idUnidadeNegocioCreditoValorResidual,
							idLocalidadeCreditoValorResidual,
							idCategoriaCreditoValorResidual, valorResidual
									.multiply(new BigDecimal("-1")),
							LancamentoTipo.CREDITOS_A_REALIZAR, 400,
							LancamentoItem.CONTAS_PAGA_EM_DUPLICIDADE_EXCESSO,
							25, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }
			}
        	
        	colecaoDadosCreditosARealizarValorResidualPagamentoExcesso = null;
        }

		// Descontos condicionais
		Collection colecaoDadosCreditosARealizarDescontoCondicional = repositorioFinanceiro
				.pesquisarDadosCreditosARealizarCategoriaDescontoCondicional(
						anoMesAnteriorFaturamento, idLocalidade);
		
        // Valor Residual Descontos Condicionais
        Collection<Object[]> colecaoDadosCreditosARealizarValorResidualDescontoCondicional = repositorioFinanceiro
                .pesquisarDadosCreditosARealizarCategoriaValorResidualDescontoCondicional(
                        anoMesAnteriorFaturamento, idLocalidade);

		if (colecaoDadosCreditosARealizarDescontoCondicional != null
				&& !colecaoDadosCreditosARealizarDescontoCondicional.isEmpty()) {

			Iterator colecaoDadosCreditosARealizarDescontoCondicionalIterator = colecaoDadosCreditosARealizarDescontoCondicional
					.iterator();

			while (colecaoDadosCreditosARealizarDescontoCondicionalIterator
					.hasNext()) {

				Object[] dadosCreditosARealizarDescontoCondicional = (Object[]) colecaoDadosCreditosARealizarDescontoCondicionalIterator
						.next();

				Integer idGerenciaRegionalCredito = (Integer) dadosCreditosARealizarDescontoCondicional[0];
				Integer idUnidadeNegocioCredito = (Integer) dadosCreditosARealizarDescontoCondicional[1];
				Integer idLocalidadeCredito = (Integer) dadosCreditosARealizarDescontoCondicional[2];
				Integer idCategoriaCredito = (Integer) dadosCreditosARealizarDescontoCondicional[3];

				BigDecimal valorCategoria = (BigDecimal) dadosCreditosARealizarDescontoCondicional[4];
				
                // Verifica se existe valor residual para a mesma categoria, caso exista acumula o valor
                if (colecaoDadosCreditosARealizarValorResidualDescontoCondicional != null
						&& !colecaoDadosCreditosARealizarValorResidualDescontoCondicional
								.isEmpty()) {
                	
                	Collection colecaoRemovidos = new ArrayList<Object[]>();
                	
                	for (Object[] dadosCreditosARealizarValorResidualDescontoCondicional : colecaoDadosCreditosARealizarValorResidualDescontoCondicional) {
                        Integer idCategoriaCreditoValorResidual = (Integer) dadosCreditosARealizarValorResidualDescontoCondicional[3];

                        BigDecimal valorResidual = (BigDecimal) dadosCreditosARealizarValorResidualDescontoCondicional[4];
                        
                        if (idCategoriaCreditoValorResidual.equals(idCategoriaCredito)) {
                        	
                        	if (valorResidual != null) {
                        		valorCategoria = valorCategoria.add(valorResidual);
                        	}
                        	
                        	colecaoRemovidos.add(dadosCreditosARealizarValorResidualDescontoCondicional);
                        	break;
                        	
                        }
                        
					}
                	
                	colecaoDadosCreditosARealizarValorResidualDescontoCondicional.removeAll(colecaoRemovidos);
                	
                }

				if (valorCategoria != null
						&& valorCategoria.compareTo(new BigDecimal("0.00")) > 0) {

					// Cria o objeto com os valores passados
					ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
							anoMesAnteriorFaturamento,
							idGerenciaRegionalCredito, idUnidadeNegocioCredito,
							idLocalidadeCredito, idCategoriaCredito,
							valorCategoria.multiply(new BigDecimal("-1")),
							LancamentoTipo.CREDITOS_A_REALIZAR, 400,
							LancamentoItem.DESCONTOS_CONDICIONAIS, 35, null);

					colecaoContasAReceberContabil.add(contaAReceberContabil);
				}

			}

			colecaoDadosCreditosARealizarDescontoCondicional = null;

		}
		
        // Verifica se restou algum dado que não estava presente na colecaoDadosCreditosARealizarValorResidualDescontoCondicional
        if (colecaoDadosCreditosARealizarValorResidualDescontoCondicional != null
                && !colecaoDadosCreditosARealizarValorResidualDescontoCondicional.isEmpty()) {
        	for (Object[] dadosCreditosARealizarValorResidualDescontoCondicional : colecaoDadosCreditosARealizarValorResidualDescontoCondicional) {
        		
        		Integer idGerenciaRegionalCreditoValorResidual = (Integer) dadosCreditosARealizarValorResidualDescontoCondicional[0];
        		Integer idUnidadeNegocioCreditoValorResidual = (Integer) dadosCreditosARealizarValorResidualDescontoCondicional[1];
                Integer idLocalidadeCreditoValorResidual = (Integer) dadosCreditosARealizarValorResidualDescontoCondicional[2];
                Integer idCategoriaCreditoValorResidual = (Integer) dadosCreditosARealizarValorResidualDescontoCondicional[3];
                BigDecimal valorResidual = (BigDecimal) dadosCreditosARealizarValorResidualDescontoCondicional[4];
                
                if (valorResidual != null
                        && valorResidual.compareTo(new BigDecimal("0.00")) > 0) {
                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
							anoMesAnteriorFaturamento,
							idGerenciaRegionalCreditoValorResidual,
							idUnidadeNegocioCreditoValorResidual,
							idLocalidadeCreditoValorResidual,
							idCategoriaCreditoValorResidual, valorResidual
									.multiply(new BigDecimal("-1")),
							LancamentoTipo.CREDITOS_A_REALIZAR, 400,
							LancamentoItem.DESCONTOS_CONDICIONAIS, 35, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }
			}
        	
        	colecaoDadosCreditosARealizarValorResidualDescontoCondicional = null;
        }

		// Ajustes para Zerar a Conta
		Collection colecaoDadosCreditosARealizarAjusteZerarConta = repositorioFinanceiro
				.pesquisarDadosCreditosARealizarCategoriaAjusteZerarConta(
						anoMesAnteriorFaturamento, idLocalidade);
		
        // Valor Residual Ajustes para Zerar a Conta
        Collection<Object[]> colecaoDadosCreditosARealizarValorResidualAjusteZerarConta = repositorioFinanceiro
                .pesquisarDadosCreditosARealizarCategoriaValorResidualAjusteZerarConta(
                        anoMesAnteriorFaturamento, idLocalidade);

		if (colecaoDadosCreditosARealizarAjusteZerarConta != null
				&& !colecaoDadosCreditosARealizarAjusteZerarConta.isEmpty()) {

			Iterator colecaoDadosCreditosARealizarAjusteZerarContaIterator = colecaoDadosCreditosARealizarAjusteZerarConta
					.iterator();

			while (colecaoDadosCreditosARealizarAjusteZerarContaIterator
					.hasNext()) {

				Object[] dadosCreditosARealizarAjusteZerarConta = (Object[]) colecaoDadosCreditosARealizarAjusteZerarContaIterator
						.next();

				Integer idGerenciaRegionalCredito = (Integer) dadosCreditosARealizarAjusteZerarConta[0];
				Integer idUnidadeNegocioCredito = (Integer) dadosCreditosARealizarAjusteZerarConta[1];
				Integer idLocalidadeCredito = (Integer) dadosCreditosARealizarAjusteZerarConta[2];
				Integer idCategoriaCredito = (Integer) dadosCreditosARealizarAjusteZerarConta[3];

				BigDecimal valorCategoria = (BigDecimal) dadosCreditosARealizarAjusteZerarConta[4];
				
                // Verifica se existe valor residual para a mesma categoria, caso exista acumula o valor
                if (colecaoDadosCreditosARealizarValorResidualAjusteZerarConta != null
						&& !colecaoDadosCreditosARealizarValorResidualAjusteZerarConta
								.isEmpty()) {
                	
                	Collection colecaoRemovidos = new ArrayList<Object[]>();
                	
                	for (Object[] dadosCreditosARealizarValorResidualAjusteZerarConta : colecaoDadosCreditosARealizarValorResidualAjusteZerarConta) {
                        Integer idCategoriaCreditoValorResidual = (Integer) dadosCreditosARealizarValorResidualAjusteZerarConta[3];

                        BigDecimal valorResidual = (BigDecimal) dadosCreditosARealizarValorResidualAjusteZerarConta[4];
                        
                        if (idCategoriaCreditoValorResidual.equals(idCategoriaCredito)) {
                        	
                        	if (valorResidual != null) {
                        		valorCategoria = valorCategoria.add(valorResidual);
                        	}
                        	
                        	colecaoRemovidos.add(dadosCreditosARealizarValorResidualAjusteZerarConta);
                        	break;
                        	
                        }
                        
					}
                	
                	colecaoDadosCreditosARealizarValorResidualAjusteZerarConta.removeAll(colecaoRemovidos);
                	
                }

				if (valorCategoria != null
						&& valorCategoria.compareTo(new BigDecimal("0.00")) > 0) {

					// Cria o objeto com os valores passados
					ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
							anoMesAnteriorFaturamento,
							idGerenciaRegionalCredito, idUnidadeNegocioCredito,
							idLocalidadeCredito, idCategoriaCredito,
							valorCategoria.multiply(new BigDecimal("-1")),
							LancamentoTipo.CREDITOS_A_REALIZAR, 400,
							LancamentoItem.AJUSTES_PARA_ZERAR_CONTA, 50, null);

					colecaoContasAReceberContabil.add(contaAReceberContabil);
				}

			}

			colecaoDadosCreditosARealizarAjusteZerarConta = null;

		}
		
        // Verifica se restou algum dado que não estava presente na colecaoDadosCreditosARealizarValorResidualAjusteZerarConta
        if (colecaoDadosCreditosARealizarValorResidualAjusteZerarConta != null
                && !colecaoDadosCreditosARealizarValorResidualAjusteZerarConta.isEmpty()) {
        	for (Object[] dadosCreditosARealizarValorResidualAjusteZerarConta : colecaoDadosCreditosARealizarValorResidualAjusteZerarConta) {
        		
        		Integer idGerenciaRegionalCreditoValorResidual = (Integer) dadosCreditosARealizarValorResidualAjusteZerarConta[0];
        		Integer idUnidadeNegocioCreditoValorResidual = (Integer) dadosCreditosARealizarValorResidualAjusteZerarConta[1];
                Integer idLocalidadeCreditoValorResidual = (Integer) dadosCreditosARealizarValorResidualAjusteZerarConta[2];
                Integer idCategoriaCreditoValorResidual = (Integer) dadosCreditosARealizarValorResidualAjusteZerarConta[3];
                BigDecimal valorResidual = (BigDecimal) dadosCreditosARealizarValorResidualAjusteZerarConta[4];
                
                if (valorResidual != null
                        && valorResidual.compareTo(new BigDecimal("0.00")) > 0) {
                    // Cria o objeto com os valores passados
                    ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
							anoMesAnteriorFaturamento,
							idGerenciaRegionalCreditoValorResidual,
							idUnidadeNegocioCreditoValorResidual,
							idLocalidadeCreditoValorResidual,
							idCategoriaCreditoValorResidual, valorResidual
									.multiply(new BigDecimal("-1")),
							LancamentoTipo.CREDITOS_A_REALIZAR, 400,
							LancamentoItem.AJUSTES_PARA_ZERAR_CONTA, 50, null);

                    colecaoContasAReceberContabil.add(contaAReceberContabil);
                }
			}
        	
        	colecaoDadosCreditosARealizarValorResidualAjusteZerarConta = null;
        }

	}
	
	/**
	 * [UC0751] Gerar Valor Referente a Volumes Consumidos e Não Faturados
	 * 
	 * Método responsável pela geração de valor dos volumes consumidos e não faturados
	 * 
	 * @author Rafael Corrêa, Pedro Alexandre
	 * @date 19/02/2008, 08/07/2008
	 * 
	 * @param idLocalidade
	 * @throws ControladorException
	 */
	public void gerarValorVolumesConsumidosNaoFaturados(Integer idLocalidade, int idFuncionalidadeIniciada) throws ControladorException {

		System.out.println("LOCALIDADE " + idLocalidade);
		int idUnidadeIniciada = 0;

		// -------------------------
		//
		// Registrar o início do processamento da Unidade de
		// Processamento
		// do Batch
		//
		// -------------------------
		idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,UnidadeProcessamento.LOCALIDADE, idLocalidade);

		SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();


		try {
			//Sistema verifica se todos os grupos já foram faturados
			Collection<FaturamentoGrupo> colecaoFaturamentoGrupoNaoFaturados = repositorioFaturamento.pesquisarFaturamentoGrupoNaoFaturados(sistemaParametro.getAnoMesFaturamento());
			//[FS0002] - Verificar existência de grupo não faturado
			if (colecaoFaturamentoGrupoNaoFaturados != null	&& !colecaoFaturamentoGrupoNaoFaturados.isEmpty()) {
				throw new ControladorException("atencao.existe.grupo.nao.faturado");
			}

			Collection colecaoValorVolumesConsumidosNaoFaturados = new ArrayList();

			// item 2
			// exclui os dados referentes ao valor dos volumes consumidos e não
			// faturados do mês de referência do faturamento corrente
			repositorioFinanceiro.removerValorVolumesConsumidosNaoFaturados(sistemaParametro.getAnoMesFaturamento(), idLocalidade);

			Integer idGerenciaRegional = this.getControladorLocalidade().pesquisarIdGerenciaParaLocalidade(idLocalidade);
			//System.out.println("Gerencia Regional -> " + idGerenciaRegional);
			
			Integer idUnidadeNegocio = this.getControladorLocalidade().pesquisarIdUnidadeNegocioParaLocalidade(idLocalidade);
			
			System.out.println("Pesquisando");
			// Valores de Água e Esgoto
			this.adicionarValorVolumesConsumidosNaoFaturadosAguaEsgoto(idLocalidade,sistemaParametro.getAnoMesFaturamento(), colecaoValorVolumesConsumidosNaoFaturados,idGerenciaRegional,idUnidadeNegocio);
			System.out.println("Fim Pesquisando");
			
			if (colecaoValorVolumesConsumidosNaoFaturados != null && !colecaoValorVolumesConsumidosNaoFaturados.isEmpty()) {
				getControladorBatch().inserirColecaoObjetoParaBatch(colecaoValorVolumesConsumidosNaoFaturados);
			}

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);

			System.out.println("fim da geração " + "Localidade " + idLocalidade);

		} catch (Exception e) {
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,idUnidadeIniciada, true);
			throw new EJBException(e);
		}
	}
	
	/**
	 * [UC0714] Gerar Contas a Receber Contábil
	 * 
	 * Cria o objeto de ContaAReceberContabil de acordo com os parâmetros
	 * passados
	 * 
	 * @author Rafael Corrêa
	 * @date 08/11/2007
	 * 
	 */
	private ValorVolumesConsumidosNaoFaturado criarValorVolumesConsumidosNaoFaturado(
			int anoMesReferencia, Integer idUnidadeNegocio,
			Integer idGerenciaRegional, Integer idLocalidade,
			Integer idCategoria, BigDecimal valorItem,
			Integer idLancamentoTipo, int sequenciaLancamentoTipo,
			Integer idLancamentoItem, int sequenciaLancamentoItem) {

		ValorVolumesConsumidosNaoFaturado retorno = new ValorVolumesConsumidosNaoFaturado();

		retorno.setAnoMesReferencia(anoMesReferencia);

		// Gerência Regional
		GerenciaRegional gerenciaRegional = new GerenciaRegional();
		gerenciaRegional.setId(idGerenciaRegional);
		retorno.setGerenciaRegional(gerenciaRegional);

		// Unidade Negócio
		UnidadeNegocio unidadeNegocio = new UnidadeNegocio();
		unidadeNegocio.setId(idUnidadeNegocio);
		retorno.setUnidadeNegocio(unidadeNegocio);

		// Localidade
		Localidade localidade = new Localidade();
		localidade.setId(idLocalidade);
		retorno.setLocalidade(localidade);

		// Categoria
		Categoria categoria = new Categoria();
		categoria.setId(idCategoria);
		retorno.setCategoria(categoria);

		// Valor Acumulado
		retorno.setValorItemLancamento(valorItem);

		// Lançamento Tipo
		LancamentoTipo lancamentoTipo = new LancamentoTipo();
		lancamentoTipo.setId(idLancamentoTipo);
		retorno.setLancamentoTipo(lancamentoTipo);

		// Seqüência do Lançamento Tipo
		retorno.setNumeroSequenciaTipoLancamento(sequenciaLancamentoTipo);

		// Lançamento Item
		LancamentoItem lancamentoItem = new LancamentoItem();
		lancamentoItem.setId(idLancamentoItem);
		retorno.setLancamentoItem(lancamentoItem);

		// Seqüência do Lançamento Item
		retorno.setNumeroSequenciaItemTipoLancamento(sequenciaLancamentoItem);

		return retorno;

	}
	
	/**
	 * [UC0751] Gerar Valor Referente a Volumes Consumidos e Não Faturados
	 * 
	 * Adiciona os dados de água e esgoto
	 * 
	 * @author Rafael Corrêa, Pedro Alexandre
	 * @date 08/11/2007, 08/06/2008
	 * 
	 */
	private void adicionarValorVolumesConsumidosNaoFaturadosAguaEsgoto(
			Integer idLocalidade,
			int anoMesFaturamento,
			Collection colecaoValorVolumesConsumidosNaoFaturados, 
			Integer idGerenciaRegional, 
			Integer idUnidadeNegocio)	throws ErroRepositorioException {
		
		int mes = Util.obterMes(anoMesFaturamento);
		int ano = Util.obterAno(anoMesFaturamento);
		
		int dia = new Integer(Util.obterUltimoDiaMes(mes, ano));
		
		Date ultimoDiaMesFaturamento = Util.criarData(dia, mes, ano);
		
		Collection colecaoDadosValorAguaEsgoto = repositorioFinanceiro.pesquisarDadosValorVolumesConsumidosNaoFaturadosAguaEsgoto(anoMesFaturamento, idLocalidade, ultimoDiaMesFaturamento);

		if (colecaoDadosValorAguaEsgoto != null	&& !colecaoDadosValorAguaEsgoto.isEmpty()) {

			Iterator colecaoDadosValorAguaEsgotoIterator = colecaoDadosValorAguaEsgoto.iterator();

			while (colecaoDadosValorAguaEsgotoIterator.hasNext()) {

				Object[] dadosValorAguaEsgoto = (Object[]) colecaoDadosValorAguaEsgotoIterator.next();

				Integer idCategoriaConta = (Integer) dadosValorAguaEsgoto[0];

				BigDecimal valorAgua = (BigDecimal) dadosValorAguaEsgoto[1];

				BigDecimal valorEsgoto = (BigDecimal) dadosValorAguaEsgoto[2];

				// Água
				if (valorAgua != null && valorAgua.compareTo(BigDecimal.ZERO) != 0) {

					// Cria o objeto com os valores passados
					ValorVolumesConsumidosNaoFaturado volumesConsumidosNaoFaturado = this.criarValorVolumesConsumidosNaoFaturado(
							anoMesFaturamento, 
							idUnidadeNegocio,
							idGerenciaRegional,
							idLocalidade,
							idCategoriaConta, 
							valorAgua,
							LancamentoTipo.VALOR_REFERENTE_VOLUMES_NAO_FATURADOS, 
							100,
							LancamentoItem.AGUA, 
							10);

					colecaoValorVolumesConsumidosNaoFaturados.add(volumesConsumidosNaoFaturado);
				}

				// Esgoto
				if (valorEsgoto != null	&& valorEsgoto.compareTo(BigDecimal.ZERO) != 0) {

					// Cria o objeto com os valores passados
					ValorVolumesConsumidosNaoFaturado volumesConsumidosNaoFaturado = this.criarValorVolumesConsumidosNaoFaturado(
							anoMesFaturamento, 
							idUnidadeNegocio,
							idGerenciaRegional,
							idLocalidade,
							idCategoriaConta, 
							valorEsgoto,
							LancamentoTipo.VALOR_REFERENTE_VOLUMES_NAO_FATURADOS, 
							100,
							LancamentoItem.ESGOTO, 
							20);

					colecaoValorVolumesConsumidosNaoFaturados.add(volumesConsumidosNaoFaturado);

				}

			}
			colecaoDadosValorAguaEsgoto = null;
		}
	}

	
	/**
	 * <Breve descrição sobre o caso de uso>
	 * 
	 * <Identificador e nome do caso de uso>
	 * 
	 * @author Pedro Alexandre
	 * @date 18/06/2007
	 * 
	 * @param processoIniciado
	 * @param dadosProcessamento
	 * @return
	 * @throws ControladorException
	 */
	public ParametrosDevedoresDuvidosos pesquisarParametrosDevedoresDuvidosos(Integer anoMesReferenciaContabil)	throws ControladorException {
		try {
			return this.repositorioFinanceiro.pesquisarParametrosDevedoresDuvidosos(anoMesReferenciaContabil);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	
	
	/**
	 * Gera os lançamentos dos devedores duvidosos.
	 *
	 * [UC0486] Gerar Lançamentos Contábeis dos Devedores Duvidosos
	 *
	 * @author Pedro Alexandre
	 * @date 21/06/2007
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void gerarLancamentosContabeisDevedoresDuvidosos(Integer anoMesReferenciaContabil, Integer idLocalidade, Integer idTipoPerda, int idFuncionalidadeIniciada) throws ControladorException{
		
		
		System.out.println("Localidade " + idLocalidade);

		int idUnidadeIniciada = 0;

		/*
		 * Registrar o início do processamento da Unidade de
		 * Processamento do Batch
		*/
		idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,UnidadeProcessamento.LOCALIDADE,(idLocalidade));

		
		//[FS0001 - Validar Referência Contábil]
		Integer anoMesArrecadacaoAtual =  getControladorUtil().pesquisarParametrosDoSistema().getAnoMesArrecadacao();
		if(anoMesReferenciaContabil.intValue() >anoMesArrecadacaoAtual.intValue()){
			//levanta a exceção para a próxima camada
			throw new ControladorException("atencao.mes_ano.contabil.superior",null,Util.formatarAnoMesParaMesAno(anoMesArrecadacaoAtual.toString()));
		}
		
		boolean gerarRelatorio = true;
		//[FS0003] - Validar Tipo de Geração
		if ( idTipoPerda.equals(new Integer(2) ) ) {
			try {
				ParametrosPerdasSocietarias parametrosPerdasSocietarias = this.repositorioFinanceiro.pesquisarParametrosPerdasSocietarias(anoMesReferenciaContabil);
				
				if ( parametrosPerdasSocietarias.getIndicadorGeracaoReal() == ConstantesSistema.NAO ){
					gerarRelatorio = false;
				}
				
			} catch (ErroRepositorioException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if ( !gerarRelatorio ) {
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);
		} else {
		

			FiltroLancamentoOrigem filtroLancamentoOrigem = new FiltroLancamentoOrigem();
			filtroLancamentoOrigem.adicionarParametro( new ParametroSimples(FiltroLancamentoOrigem.PERDAS_TIPO, idTipoPerda));
			
			Integer lancamentoOrigemID = null; 
			Collection<LancamentoOrigem> colecaoLancamentoOrigem = getControladorUtil().pesquisar(filtroLancamentoOrigem, LancamentoOrigem.class.getName());
			if ( colecaoLancamentoOrigem != null && !colecaoLancamentoOrigem.isEmpty() ) {
				LancamentoOrigem origem = (LancamentoOrigem) Util.retonarObjetoDeColecao(colecaoLancamentoOrigem);
				lancamentoOrigemID = origem.getId();
			}
			
			
			//remove os lançamentos contábeis já gerados
			this.removerLancamentoContabil(anoMesReferenciaContabil,idLocalidade, lancamentoOrigemID);
			
			try {
				
				/*
				 *  Pesquisa os dados do resumo dos devedores duvidosos 
				 *  para o ano/mês de referência atual e para a localidade informada.
				 *  
				 *  0 - id da localidade
				 *  1 - id do tipo de lançamento
				 *  2 - id do item de lançamento
				 *  3 - id do item de lançamento contábil
				 *  4 - id da categoria
				 *  5 - soma do valor do resumo dos devedores duvidosos 
				 */
				Collection<Object[]> colecaoDadosResumoDevedoresDuvidosos = repositorioFinanceiro.obterDadosResumoDevedoresDuvidosos(anoMesReferenciaContabil, idLocalidade, idTipoPerda);
			
				/*
				 * Caso exista resumo de devedores duvidosos para a localidade e o ano/mês 
				 * cria o lancamento contábil junto com seus items 
				 * para cada conjunto de mesmo tipo de lançamento
				 */
				if (colecaoDadosResumoDevedoresDuvidosos != null && !colecaoDadosResumoDevedoresDuvidosos.isEmpty()){
					
					//flag utilizada somente quando for a primeira entrada
					boolean flagPrimeiraVez = true;
					int idTipoLancamentoTemp = -1;
					Collection<Object[]> colecaoDadosResumoPorTipoLancamento = new ArrayList();
					
					// definição da origem do lançamento
					LancamentoOrigem lancamentoOrigem = new LancamentoOrigem();
					lancamentoOrigem.setId(lancamentoOrigemID);
					
					//Cria a variável que vai armazenar o lançamento contábil
					LancamentoContabil lancamentoContabilInsert = null;
					
					//laço para gerar os lançamentos por grupo de tipo de lançamento
					for(Object[] dadosResumoDevedoresDuvidosos : colecaoDadosResumoDevedoresDuvidosos){
						
						//recupera o tipo de lançamento atual 
						Integer idTipoLancamento = (Integer) dadosResumoDevedoresDuvidosos[1];
						
						/*
						 * Caso seja a primeira entrada do "for"
						 * adiciona os dados a coleção e atualiza o item temporario
						 * criando também o lançamento contabil que ira ser inserido
						 * junto com seus items.
						 * 
						 *  Caso contrário (não seja a primeira entrada do laço "for")
						 *  verifica se o item de lançamento mudou, caso não tenha mudado 
						 *  adiciona os dados ao conjunto do mesmo item
						 *  caso contrário, se mudou o item de lançamento o conjunto está fechado
						 *  para o lançamento contábil e chama o método para inserir o
						 *  lançamento contábil junto com seus itens. 
						 */
						if(flagPrimeiraVez){
							colecaoDadosResumoPorTipoLancamento.add(dadosResumoDevedoresDuvidosos);
							flagPrimeiraVez = false;
							idTipoLancamentoTemp = idTipoLancamento;
							
							LancamentoTipo tipoLancamento = new LancamentoTipo();
							tipoLancamento.setId(idTipoLancamento);
							
							Localidade localidade = new Localidade();
							localidade.setId(idLocalidade);
							
							//cria o lançamento contábil para ser inserido 
							lancamentoContabilInsert = new LancamentoContabil();
							lancamentoContabilInsert.setAnoMes(anoMesReferenciaContabil);
							lancamentoContabilInsert.setLancamentoOrigem(lancamentoOrigem);
							lancamentoContabilInsert.setLancamentoTipo(tipoLancamento);
							lancamentoContabilInsert.setLocalidade(localidade);
							lancamentoContabilInsert.setRecebimentoTipo(null);
							lancamentoContabilInsert.setUltimaAlteracao(new Date());
						}else{
							/*
							 * Caso ainda seja o mesmo item adicona os dados para 
							 * ser gerado os itens do lançamento para o mesmo lançamento.
							 * Caso contrário chama o metódo para inseri os itens e o lançamento contábil.
							 */
							if(idTipoLancamento == idTipoLancamentoTemp){
								colecaoDadosResumoPorTipoLancamento.add(dadosResumoDevedoresDuvidosos);
							}else{
								/* metódo para inserir o lançamento contábil assim como seus itens */
								this.inserirLancamentoContabilItemDevedoresDuvidosos(lancamentoContabilInsert, colecaoDadosResumoPorTipoLancamento, idTipoPerda);
								
								//limpa coleção e adiciona os dados do resumo atual
								colecaoDadosResumoPorTipoLancamento.clear();
								colecaoDadosResumoPorTipoLancamento.add(dadosResumoDevedoresDuvidosos);
								
								LancamentoTipo tipoLancamento = new LancamentoTipo();
								tipoLancamento.setId(idTipoLancamento);
								
								Localidade localidade = new Localidade();
								localidade.setId(idLocalidade);
								
								//cria o lançamento contábil que vai ser inserido
								lancamentoContabilInsert = new LancamentoContabil();
								lancamentoContabilInsert.setAnoMes(anoMesReferenciaContabil);
								lancamentoContabilInsert.setLancamentoOrigem(lancamentoOrigem);
								lancamentoContabilInsert.setLancamentoTipo(tipoLancamento);
								lancamentoContabilInsert.setLocalidade(localidade);
								lancamentoContabilInsert.setRecebimentoTipo(null);
								lancamentoContabilInsert.setUltimaAlteracao(new Date());
								
								//atualiza o tipo de lançamento temporário com o novo valor
								idTipoLancamentoTemp = idTipoLancamento;
							}
						}
					}
					
					/*
					 * Último registro
					 * Esse "if" é para verificar se ainda existe um último registro na coleção
					 * caso exista algum item, adiciona o lançamento contábil junto com o item. 
					 */
					if(colecaoDadosResumoPorTipoLancamento != null && colecaoDadosResumoPorTipoLancamento.size() > 0 ){
						this.inserirLancamentoContabilItemDevedoresDuvidosos(lancamentoContabilInsert, colecaoDadosResumoPorTipoLancamento, idTipoPerda);
						colecaoDadosResumoPorTipoLancamento = null;
					}
				}
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);
		
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
		}
			
	}
	
	
	/**
	 * Gera o lançamento contábil junto com seus itens. 
	 *
	 * [UC0486] - Gerar Lançamentos Contábeis dos Devedores Duvidosos 
	 *
	 * @author Pedro Alexandre
	 * @date 21/06/2007
	 *
	 * @param lancamentoContabil
	 * @param colecaoDadosResumoPorTipoLancamento
	 * @throws ControladorException
	 */
	protected void inserirLancamentoContabilItemDevedoresDuvidosos(LancamentoContabil lancamentoContabil,Collection<Object[]> colecaoDadosResumoPorTipoLancamento, 
			Integer idTipoPerda ) throws ControladorException {
		try{
			/*
			 * Caso exista dados para os itens do resumo do faturamento.
			 */
			if(colecaoDadosResumoPorTipoLancamento != null && !colecaoDadosResumoPorTipoLancamento.isEmpty()){
				
				Collection colecaoLancamentoContabilItem = new ArrayList();
				
				//flag que indica se o lançamento contábil já foi inserido ou não. 
				boolean flagInseridoLancamentoContabil = false;
				
				/*
				 * Dados do resumo dos devedores duvidosos
				 *  0 - id da localidade
				 *  1 - id do tipo de lançamento
				 *  2 - id do item de lançamento
				 *  3 - id do item de lançamento contábil
				 *  4 - id da categoria
				 *  5 - soma do valor do resumo dos devedores duvidosos
				 */
				for(Object[] dadosResumoDevedoresDuvidosos : colecaoDadosResumoPorTipoLancamento){
					
					//recupera os dados do resumo dos devedores duvidosos
					Integer idLancamentoTipo =         (Integer) dadosResumoDevedoresDuvidosos[1];
					Integer idLancamentoItem =         (Integer) dadosResumoDevedoresDuvidosos[2];
					Integer idLancamentoItemContabil = (Integer) dadosResumoDevedoresDuvidosos[3];
					Integer idCategoria =              (Integer) dadosResumoDevedoresDuvidosos[4];
					BigDecimal valorLancamento =       (BigDecimal) dadosResumoDevedoresDuvidosos[5]; 
					
					/* 
					 * Verifica se existe conta contábil para o item que vai ser inserido 
					 * 
					 * 0 - id conta contábil do débito
					 * 1 - id conta contábil crédito 
					 * 2 - descrição do histórico do débito
					 * 3 - descrição do histórico do crédito
					 */
					Object[] dadosContaContabil = this.repositorioFinanceiro.obterParametrosContabilDevedoresDuvidosos(idCategoria, idLancamentoItemContabil, idLancamentoItem, 
						idLancamentoTipo, idTipoPerda);
					
					if(dadosContaContabil != null){
						Integer idLancamentoContabil = null;
						/*
						 * Caso exista dados para a conta contábil e o item contábil 
						 * ainda não foi inserido 
						 * inseri o item contábil na base.
						 */
						if(!flagInseridoLancamentoContabil){
							idLancamentoContabil = (Integer)getControladorUtil().inserir(lancamentoContabil);
							lancamentoContabil.setId(idLancamentoContabil);
							flagInseridoLancamentoContabil = true;
						}
						
						//recupera os dados da conta contábil para o item 
						//do resumo dos devedores duvidosos.
						Integer idContaContabilDebito = (Integer) dadosContaContabil[0];
						Integer idContaContabilCredito = (Integer) dadosContaContabil[1];
						String descricaoHistoricoDebito = (String) dadosContaContabil[2];
						String descricaoHistoricoCredito = (String) dadosContaContabil[3];
						
						//cria os indicadores de débito e crédito.
						Short indicadorDebito = new Short("2"); 
						Short indicadorCredito = new Short("1");
						
						Date ultimaAlteracao = new Date();
						
						//cria as contas contábeis de crédito e débito.
						ContaContabil contaContabilCredito = new ContaContabil();
						contaContabilCredito.setId(idContaContabilCredito);
						
						ContaContabil contaContabilDebito = new ContaContabil();
						contaContabilDebito.setId(idContaContabilDebito);
						
						/**  Item de crédito */
						LancamentoContabilItem lancamentoContabilItemCredito = new LancamentoContabilItem(indicadorCredito, 
								valorLancamento, 
								descricaoHistoricoCredito, 
								ultimaAlteracao, 
								lancamentoContabil, 
								contaContabilCredito);
						
						colecaoLancamentoContabilItem.add(lancamentoContabilItemCredito);
						
						/** Item de débito */
						LancamentoContabilItem lancamentoContabilItemDebito = new LancamentoContabilItem(indicadorDebito, 
								valorLancamento, 
								descricaoHistoricoDebito, 
								ultimaAlteracao, 
								lancamentoContabil, 
								contaContabilDebito);
						
						colecaoLancamentoContabilItem.add(lancamentoContabilItemDebito);
						
					}
				}
				//inseri os itens de lançamento contábeis. 
				getControladorBatch().inserirColecaoObjetoParaBatch(colecaoLancamentoContabilItem); 
			}
	
		} catch (Exception ex) {
			throw new EJBException(ex);
		}
	}
	
	/**
	 * Pesquisa a coleção de ids das localidades para processar o lançamentos  
	 * contábeis dos devedores duvidosos.
	 *
	 * [UC0485] Gerar Lançamentos Contábeis dos Devedores Duvidosos
	 *
	 * @author Pedro Alexandre
	 * @date 25/06/2007
	 *
	 * @param anoMesReferenciaContabil
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarIdsLocalidadesParaGerarLancamentosContabeisDevedoresDuvidosos(Integer anoMesReferenciaContabil) throws ControladorException{
		try{
			return this.repositorioFinanceiro.pesquisarIdsLocalidadesGerarLancamentosContabeisDevedoresDuvidosos(anoMesReferenciaContabil);
			
				//erro no hibernate
			} catch (ErroRepositorioException ex) {
				//levanta a exceção para a próxima camada
				throw new ControladorException("erro.sistema", ex);
			}		
	}

	/**
	 * Remove os lançamentos contábeis e seus respectivos itens 
	 * de acordo com os parâmetros informados. 
	 *
	 * @author Pedro Alexandre
	 * @date 26/06/2007
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idLancamentoOrigem
	 * @throws ControladorException
	 */
	public void removerLancamentoContabil(Integer anoMesReferenciaContabil, Integer idLocalidade, Integer idLancamentoOrigem) throws ControladorException{
		try{
			Collection<Integer> colecaoIdsLancamentosContabeis = this.repositorioFinanceiro.pesquisarIdsLancamentosContabeis(anoMesReferenciaContabil, idLocalidade,idLancamentoOrigem);
			
			if(colecaoIdsLancamentosContabeis != null && !colecaoIdsLancamentosContabeis.isEmpty()){
				for(Integer idLancamentoContabil : colecaoIdsLancamentosContabeis){
					this.repositorioFinanceiro.removerItensLancamentoContabil(idLancamentoContabil);
				}
				
				this.repositorioFinanceiro.removerLancamentosContabeis(colecaoIdsLancamentosContabeis);
				colecaoIdsLancamentosContabeis = null;
			}
				//erro no hibernate
			} catch (ErroRepositorioException ex) {
				//levanta a exceção para a próxima camada
				throw new ControladorException("erro.sistema", ex);
			}		
	}
	
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Consulta ResumoDevedoresDuvidosos para a geração do relatório 
	 * [UC0487] Gerar Relatório de Resumo de Devedores Duvidosos
	 * de acordo com a opção de totalização.
	 * 
	 * [SB0001] Verificar tipo de Geração
	 * 
	 * @author Vivianne Sousa, Davi Menezes
	 * @created 20/07/2007, 17/11/2011
	 * 
	 * @param opcaoTotalizacao
	 * @return
	 * @throws ControladorException
	 */
	public Collection consultarResumoDevedoresDuvidososRelatorio(
			String opcaoTotalizacao, int mesAnoReferencia,
			Integer gerenciaRegional, Integer localidade, Integer unidadeNegocio, Integer tipoPerda)
			throws ControladorException {

		Collection retorno = new ArrayList();
		Collection colecaoResumoDevedoresDuvidososRelatorio = null;

		// Converter de mesAno para anoMes para que funcione nas consultas
		int anoMesReferencia = Util.formatarMesAnoParaAnoMes(mesAnoReferencia);
		boolean consultarResumoDevedoresDuvidososRelatorio = true;
		
		try {

			if (opcaoTotalizacao.equals("estado")) {
				colecaoResumoDevedoresDuvidososRelatorio = repositorioFinanceiro
						.consultarResumoDevedoresDuvidososRelatorioPorEstado(anoMesReferencia, tipoPerda);

			} else if (opcaoTotalizacao.equals("estadoGerencia")) {
				colecaoResumoDevedoresDuvidososRelatorio = repositorioFinanceiro
						.consultarResumoDevedoresDuvidososRelatorioPorEstadoPorGerenciaRegional(anoMesReferencia, tipoPerda);

			} else if (opcaoTotalizacao.equals("estadoUnidadeNegocio")) {
				
				consultarResumoDevedoresDuvidososRelatorio = false;
				
				retorno = consultarResumoDevedoresDuvidososRelatorioEstadoPorUnidadeNegocio(anoMesReferencia, tipoPerda);
				
			} else if (opcaoTotalizacao.equals("estadoLocalidade")) {
				
				consultarResumoDevedoresDuvidososRelatorio = false;
				
				retorno = consultarResumoDevedoresDuvidososRelatorioPorEstadoPorLocalidade(anoMesReferencia, tipoPerda);

			} else if (opcaoTotalizacao.equals("gerenciaRegional")) {
				colecaoResumoDevedoresDuvidososRelatorio = repositorioFinanceiro
						.consultarResumoDevedoresDuvidososRelatorioPorGerenciaRegional(anoMesReferencia, gerenciaRegional, tipoPerda);

			} else if (opcaoTotalizacao.equals("gerenciaRegionalLocalidade")) {
				colecaoResumoDevedoresDuvidososRelatorio = repositorioFinanceiro
						.consultarResumoDevedoresDuvidososRelatorioPorGerenciaRegionalPorLocalidade(
								anoMesReferencia, gerenciaRegional, tipoPerda);

			} else if (opcaoTotalizacao.equals("unidadeNegocio")) {
				colecaoResumoDevedoresDuvidososRelatorio = repositorioFinanceiro
						.consultarResumoDevedoresDuvidososRelatorioPorUnidadeNegocio(
								anoMesReferencia, unidadeNegocio, tipoPerda);				
				
			} else if (opcaoTotalizacao.equals("localidade")) {
				colecaoResumoDevedoresDuvidososRelatorio = repositorioFinanceiro
						.consultarResumoDevedoresDuvidososRelatorioPorLocalidade(
								anoMesReferencia, localidade, tipoPerda);
			}
			
			
			if (consultarResumoDevedoresDuvidososRelatorio){
				
				Iterator iterator = colecaoResumoDevedoresDuvidososRelatorio.iterator();

				// Prepara cada linha do relatório

				String tipoLancamento = null;
				String itemLancamento = null;
				String itemContabel = null;

				String descGerenciaRegionalAnterior = null;
				String idGerenciaRegionalAnterior = null;
				String descLocalidadeAnterior = null;
				String idLocalidadeAnterior = null;
				String descLancamentoTipoSuperior = "";
				
				String descUnidadeNegocioAnterior = null;
				String idUnidadeNegocioAnterior = null;

				Object[] elementAnterior = new Object[13];
				BigDecimal[] arrayValores = new BigDecimal[5];

				Boolean agrupaPorGerencia = false;
				if (opcaoTotalizacao.equalsIgnoreCase("estadoGerencia")|| opcaoTotalizacao.equalsIgnoreCase("gerenciaRegional")) {
					agrupaPorGerencia = true;
				}

				Boolean agrupaPorLocalidade = false;
				if (opcaoTotalizacao.equalsIgnoreCase("estadoLocalidade")|| opcaoTotalizacao.equalsIgnoreCase("gerenciaRegionalLocalidade")|| opcaoTotalizacao.equalsIgnoreCase("localidade")) {
					agrupaPorLocalidade = true;
				}

				Boolean agrupaPorUnidadeNegocio = false;
				if (opcaoTotalizacao.equalsIgnoreCase("unidadeNegocio") || opcaoTotalizacao.equalsIgnoreCase("estadoUnidadeNegocio")) {
					agrupaPorUnidadeNegocio = true;
				}
				
				while (iterator.hasNext()) {
					Object[] element = null;
					String tempTipoLancamento = null;
					String tempItemLancamento = null;
					String tempItemContabel = null;

					element = (Object[]) iterator.next();

					if (tipoLancamento == null) {
						tipoLancamento = (String) element[1];
						itemLancamento = (String) element[2];
						itemContabel = (String) element[3];
					}

					tempTipoLancamento = (String) element[1];
					tempItemLancamento = (String) element[2];
					tempItemContabel = (String) element[3];

					boolean condicaoIgual = true;
					// compara se o registro atual eh do
					// mesmo tipo de Recebimento, mesmo tipo de lançamento
					// e mesmo item de lançamento do registro anterior
					if (tipoLancamento.equals(tempTipoLancamento) && itemLancamento.equals(tempItemLancamento)) {

						// se o registro possuir item contabel
						// compara se eh do mesmo item contabel do registro anterior
						if (itemContabel == null && tempItemContabel == null
							|| (itemContabel != null && tempItemContabel != null && itemContabel.equals(tempItemContabel))) {
						
							// se for agrupado por gerencia
							// compara se o registro atual eh da
							// mesma gerencia regional do registro anterior
							if (!agrupaPorGerencia || descGerenciaRegionalAnterior == null || 
							(agrupaPorGerencia	&& descGerenciaRegionalAnterior.equalsIgnoreCase((String) element[9]))) {

								switch (((Integer) element[8]).intValue()) {
								case 1:
									arrayValores[0] = (BigDecimal) element[0];
									break;
								case 2:
									arrayValores[1] = (BigDecimal) element[0];
									break;
								case 3:
									arrayValores[2] = (BigDecimal) element[0];
									break;
								case 4:
									arrayValores[4] = (BigDecimal) element[0];
									break;
								}
							} else {
								condicaoIgual = false;
							}

						} else {

							condicaoIgual = false;
						}

					} else {

						condicaoIgual = false;

					}
					
					if (!condicaoIgual) {
						
						ResumoDevedoresDuvidososRelatorioHelper ResumoDevedoresDuvidososRelatorioHelper = 
							new ResumoDevedoresDuvidososRelatorioHelper(
								(BigDecimal[]) arrayValores,
								(String) elementAnterior[1],
								(String) elementAnterior[2],
								(String) elementAnterior[3],
								(Short) elementAnterior[4],
								(Short) elementAnterior[5], (Integer) elementAnterior[6],
								(Integer) elementAnterior[7],
								 false,
								descGerenciaRegionalAnterior,
								idGerenciaRegionalAnterior, descLocalidadeAnterior,
								idLocalidadeAnterior,descLancamentoTipoSuperior,
								descUnidadeNegocioAnterior,idUnidadeNegocioAnterior);

						retorno.add(ResumoDevedoresDuvidososRelatorioHelper);
						
					
						arrayValores = new BigDecimal[5];
						switch (((Integer) element[8]).intValue()) {
						case 1:
							arrayValores[0] = (BigDecimal) element[0];
							break;
						case 2:
							arrayValores[1] = (BigDecimal) element[0];
							break;
						case 3:
							arrayValores[2] = (BigDecimal) element[0];
							break;
						case 4:
							arrayValores[4] = (BigDecimal) element[0];
							break;
						}

					}
					
					
					
					elementAnterior[1] = element[1]; // descricaoTipoLancamento
					if (((String)element[1]).equalsIgnoreCase((String)element[2])){
						elementAnterior[2] = null; // descricaoItemLancamento
					}else{
						elementAnterior[2] = element[2]; // descricaoItemLancamento
					}
					
					elementAnterior[3] = element[3]; // descricaoItemContabil
					elementAnterior[4] = element[4]; // indicadorImpressao
					elementAnterior[5] = element[5]; // indicadorTotal
					elementAnterior[6] = element[6]; // lancamentoTipo
					elementAnterior[7] = element[7]; // lancamentoTipoSuperior

					

					// identifica pelo que vai ser "quebrado" o relátorio
					if (agrupaPorGerencia) { 
						// quebra página por Gerência Regional e não mostra a Localidade
						descGerenciaRegionalAnterior = "" + element[9];
						idGerenciaRegionalAnterior = "" + element[10];
					}
					if (agrupaPorLocalidade) {
						if (opcaoTotalizacao.equalsIgnoreCase("estadoLocalidade")
								|| opcaoTotalizacao
										.equalsIgnoreCase("gerenciaRegionalLocalidade")) {
							// quebra a página por Localidade e mostra a Gerência
							// Regional
							descGerenciaRegionalAnterior = "" + element[9];
							idGerenciaRegionalAnterior = "" + element[10];
							descLocalidadeAnterior = "" + element[11];
							idLocalidadeAnterior = "" + element[12];
						} else {
							// quebra a página por Localidade e não mostra a Gerência
							// Regional
							descLocalidadeAnterior = "" + element[9];
							idLocalidadeAnterior = "" + element[10];
						}
					}
					
					if (agrupaPorUnidadeNegocio){
						descUnidadeNegocioAnterior = "" + element[9];
						idUnidadeNegocioAnterior = "" + element[10];
						
					}

					tipoLancamento = tempTipoLancamento;
					itemLancamento = tempItemLancamento;
					itemContabel = tempItemContabel;

				}

				if (colecaoResumoDevedoresDuvidososRelatorio != null
						&& !colecaoResumoDevedoresDuvidososRelatorio.isEmpty()) {
					// adiciona a ultima linha
			
					ResumoDevedoresDuvidososRelatorioHelper ResumoDevedoresDuvidososRelatorioHelper = 
						new ResumoDevedoresDuvidososRelatorioHelper(
							(BigDecimal[]) arrayValores,
							(String) elementAnterior[1],
							(String) elementAnterior[2],
							(String) elementAnterior[3],
							(Short) elementAnterior[4],
							(Short) elementAnterior[5], (Integer) elementAnterior[6],
							(Integer) elementAnterior[7],
							 false,
							descGerenciaRegionalAnterior,
							idGerenciaRegionalAnterior, descLocalidadeAnterior,
							idLocalidadeAnterior,descLancamentoTipoSuperior,
							descUnidadeNegocioAnterior,idUnidadeNegocioAnterior);

					retorno.add(ResumoDevedoresDuvidososRelatorioHelper);
				}
				
			}
			

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		return retorno;
	}

	/**
	 * Consulta ResumoDevedoresDuvidosos para a geração do relatório 
	 * [UC0487] Gerar Relatório de Resumo de Devedores Duvidosos
	 * de acordo com a opção de totalização.
	 * 
	 * @author Vivianne Sousa, Davi Menezes
	 * @created 20/07/2007, 17/11/2011
	 * 
	 * @param opcaoTotalizacao
	 * @return
	 * @throws ControladorException
	 */
	public Collection consultarResumoDevedoresDuvidososRelatorioEstadoPorUnidadeNegocio(int anoMesReferencia, int tipoPerda)
			throws ControladorException {

		Collection colecaoResumoDevedoresDuvidososRelatorioEstadoPorUnidadeNegocio = new ArrayList();
		Collection retorno = new ArrayList();
		
		try {
			colecaoResumoDevedoresDuvidososRelatorioEstadoPorUnidadeNegocio = 
				repositorioFinanceiro.consultarResumoDevedoresDuvidososRelatorioEstadoPorUnidadeNegocio(anoMesReferencia, tipoPerda);
			
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
		
		Iterator iterator = colecaoResumoDevedoresDuvidososRelatorioEstadoPorUnidadeNegocio.iterator();

		// Prepara cada linha do relatório
		String tipoLancamento = null;
		String itemLancamento = null;
		String itemContabel = null;

		String descGerenciaRegionalAnterior = null;
		String idGerenciaRegionalAnterior = null;
		String descLocalidadeAnterior = null;
		String idLocalidadeAnterior = null;
		String descLancamentoTipoSuperior = "";
		
		String descUnidadeNegocioAnterior = null;
		String idUnidadeNegocioAnterior = null;

		Object[] elementAnterior = new Object[13];
		BigDecimal[] arrayValores = new BigDecimal[5];

		while (iterator.hasNext()) {
			Object[] element = null;
			String tempTipoLancamento = null;
			String tempItemLancamento = null;
			String tempItemContabel = null;

			element = (Object[]) iterator.next();

			if (tipoLancamento == null) {
				tipoLancamento = (String) element[1];
				itemLancamento = (String) element[2];
				itemContabel = (String) element[3];
			}

			tempTipoLancamento = (String) element[1];
			tempItemLancamento = (String) element[2];
			tempItemContabel = (String) element[3];

			boolean condicaoIgual = true;
			// compara se o registro atual eh do
			// mesmo tipo de Recebimento, mesmo tipo de lançamento
			// e mesmo item de lançamento do registro anterior
			if (tipoLancamento.equals(tempTipoLancamento) && itemLancamento.equals(tempItemLancamento)) {

				// se o registro possuir item contabel
				// compara se eh do mesmo item contabel do registro anterior
				if (itemContabel == null && tempItemContabel == null
					|| (itemContabel != null && tempItemContabel != null && itemContabel.equals(tempItemContabel))) {
				
					// se for agrupado por gerencia
					// compara se o registro atual eh da
					// mesma gerencia regional do registro anterior

					switch (((Integer) element[8]).intValue()) {
					case 1:
						arrayValores[0] = (BigDecimal) element[0];
						break;
					case 2:
						arrayValores[1] = (BigDecimal) element[0];
						break;
					case 3:
						arrayValores[2] = (BigDecimal) element[0];
						break;
					case 4:
						arrayValores[4] = (BigDecimal) element[0];
						break;
					}

				} else {

					condicaoIgual = false;
				}

			} else {

				condicaoIgual = false;

			}
			
			if (!condicaoIgual) {
				
				ResumoDevedoresDuvidososRelatorioHelper ResumoDevedoresDuvidososRelatorioHelper = 
					new ResumoDevedoresDuvidososRelatorioHelper(
						(BigDecimal[]) arrayValores,
						(String) elementAnterior[1],
						(String) elementAnterior[2],
						(String) elementAnterior[3],
						(Short) elementAnterior[4],
						(Short) elementAnterior[5], (Integer) elementAnterior[6],
						(Integer) elementAnterior[7],
						 false,
						descGerenciaRegionalAnterior,
						idGerenciaRegionalAnterior, descLocalidadeAnterior,
						idLocalidadeAnterior,descLancamentoTipoSuperior,
						descUnidadeNegocioAnterior,idUnidadeNegocioAnterior);

				retorno.add(ResumoDevedoresDuvidososRelatorioHelper);
				
			
				arrayValores = new BigDecimal[5];
				switch (((Integer) element[8]).intValue()) {
				case 1:
					arrayValores[0] = (BigDecimal) element[0];
					break;
				case 2:
					arrayValores[1] = (BigDecimal) element[0];
					break;
				case 3:
					arrayValores[2] = (BigDecimal) element[0];
					break;
				case 4:
					arrayValores[4] = (BigDecimal) element[0];
					break;
				}

			}
			
			if (idGerenciaRegionalAnterior != null && !idGerenciaRegionalAnterior.equals("" + element[14])){
				//quebra por gerencia
				retorno = consultarResumoDevedoresDuvidososRelatorioPorGerenciaRegional(anoMesReferencia,new Integer (idGerenciaRegionalAnterior),retorno, tipoPerda);
			}
			
			elementAnterior[1] = element[1]; // descricaoTipoLancamento
			if (((String)element[1]).equalsIgnoreCase((String)element[2])){
				elementAnterior[2] = null; // descricaoItemLancamento
			}else{
				elementAnterior[2] = element[2]; // descricaoItemLancamento
			}
			
			elementAnterior[3] = element[3]; // descricaoItemContabil
			elementAnterior[4] = element[4]; // indicadorImpressao
			elementAnterior[5] = element[5]; // indicadorTotal
			elementAnterior[6] = element[6]; // lancamentoTipo
			elementAnterior[7] = element[7]; // lancamentoTipoSuperior

			descUnidadeNegocioAnterior = "" + element[9];
			idUnidadeNegocioAnterior = "" + element[10];
				
			descGerenciaRegionalAnterior = "" + element[13];
			idGerenciaRegionalAnterior = "" + element[14];
				
			tipoLancamento = tempTipoLancamento;
			itemLancamento = tempItemLancamento;
			itemContabel = tempItemContabel;

		}

		if (colecaoResumoDevedoresDuvidososRelatorioEstadoPorUnidadeNegocio != null
				&& !colecaoResumoDevedoresDuvidososRelatorioEstadoPorUnidadeNegocio.isEmpty()) {
			// adiciona a ultima linha
	
			ResumoDevedoresDuvidososRelatorioHelper ResumoDevedoresDuvidososRelatorioHelper = 
				new ResumoDevedoresDuvidososRelatorioHelper(
					(BigDecimal[]) arrayValores,
					(String) elementAnterior[1],
					(String) elementAnterior[2],
					(String) elementAnterior[3],
					(Short) elementAnterior[4],
					(Short) elementAnterior[5], (Integer) elementAnterior[6],
					(Integer) elementAnterior[7],
					 false,
					descGerenciaRegionalAnterior,
					idGerenciaRegionalAnterior, descLocalidadeAnterior,
					idLocalidadeAnterior,descLancamentoTipoSuperior,
					descUnidadeNegocioAnterior,idUnidadeNegocioAnterior);

			retorno.add(ResumoDevedoresDuvidososRelatorioHelper);
			
			retorno = consultarResumoDevedoresDuvidososRelatorioPorGerenciaRegional(anoMesReferencia,new Integer (idGerenciaRegionalAnterior),retorno, tipoPerda);
			
		}

		return retorno;
		
	
	}
	
	
	/**
	 * Consulta ResumoDevedoresDuvidosos para a geração do relatório 
	 * [UC0487] Gerar Relatório de Resumo de Devedores Duvidosos
	 * de acordo com a opção de totalização.
	 * 
	 * @author Vivianne Sousa, Davi Menezes
	 * @created 20/07/2007, 17/11/2011
	 * 
	 * @param opcaoTotalizacao
	 * @return
	 * @throws ControladorException
	 */
	public Collection consultarResumoDevedoresDuvidososRelatorioPorGerenciaRegional(int anoMesReferencia, 
			Integer idGerenciaRegional , Collection colecaoResumoDevedoresDuvidososRelatorio, int tipoPerda)
			throws ControladorException {

		Collection colecaoResumoDevedoresDuvidososRelatorioPorGerenciaRegional = new ArrayList();
		
		try {
			colecaoResumoDevedoresDuvidososRelatorioPorGerenciaRegional = 
				repositorioFinanceiro.consultarResumoDevedoresDuvidososRelatorioPorGerenciaRegional(anoMesReferencia, idGerenciaRegional, tipoPerda);
			
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
		
		Iterator iteratorResumoDevedoresDuvidososRelatorioPorGerenciaRegional = colecaoResumoDevedoresDuvidososRelatorioPorGerenciaRegional.iterator();

		// Prepara cada linha do relatório
		String tipoLancamento = null;
		String itemLancamento = null;
		String itemContabel = null;

		String descGerenciaRegionalAnterior = null;
		String idGerenciaRegionalAnterior = null;
		String descLocalidadeAnterior = null;
		String idLocalidadeAnterior = null;
		String descLancamentoTipoSuperior = "";
		
		String descUnidadeNegocioAnterior = null;
		String idUnidadeNegocioAnterior = null;

		Object[] elementAnterior = new Object[13];
		BigDecimal[] arrayValores = new BigDecimal[5];

		Boolean agrupaPorGerencia = true;
		
		while (iteratorResumoDevedoresDuvidososRelatorioPorGerenciaRegional.hasNext()) {
			Object[] element = null;
			String tempTipoLancamento = null;
			String tempItemLancamento = null;
			String tempItemContabel = null;

			element = (Object[]) iteratorResumoDevedoresDuvidososRelatorioPorGerenciaRegional.next();

			if (tipoLancamento == null) {
				tipoLancamento = (String) element[1];
				itemLancamento = (String) element[2];
				itemContabel = (String) element[3];
			}

			tempTipoLancamento = (String) element[1];
			tempItemLancamento = (String) element[2];
			tempItemContabel = (String) element[3];

			boolean condicaoIgual = true;
			// compara se o registro atual eh do
			// mesmo tipo de Recebimento, mesmo tipo de lançamento
			// e mesmo item de lançamento do registro anterior
			if (tipoLancamento.equals(tempTipoLancamento) && itemLancamento.equals(tempItemLancamento)) {

				// se o registro possuir item contabel
				// compara se eh do mesmo item contabel do registro anterior
				if (itemContabel == null && tempItemContabel == null
					|| (itemContabel != null && tempItemContabel != null && itemContabel.equals(tempItemContabel))) {
				
					// se for agrupado por gerencia
					// compara se o registro atual eh da
					// mesma gerencia regional do registro anterior
					if (!agrupaPorGerencia || descGerenciaRegionalAnterior == null || 
					(agrupaPorGerencia	&& descGerenciaRegionalAnterior.equalsIgnoreCase((String) element[9]))) {

						switch (((Integer) element[8]).intValue()) {
						case 1:
							arrayValores[0] = (BigDecimal) element[0];
							break;
						case 2:
							arrayValores[1] = (BigDecimal) element[0];
							break;
						case 3:
							arrayValores[2] = (BigDecimal) element[0];
							break;
						case 4:
							arrayValores[4] = (BigDecimal) element[0];
							break;
						}
					} else {
						condicaoIgual = false;
					}

				} else {

					condicaoIgual = false;
				}

			} else {

				condicaoIgual = false;

			}
			
			if (!condicaoIgual) {
				
				ResumoDevedoresDuvidososRelatorioHelper ResumoDevedoresDuvidososRelatorioHelper = 
					new ResumoDevedoresDuvidososRelatorioHelper(
						(BigDecimal[]) arrayValores,
						(String) elementAnterior[1],
						(String) elementAnterior[2],
						(String) elementAnterior[3],
						(Short) elementAnterior[4],
						(Short) elementAnterior[5], (Integer) elementAnterior[6],
						(Integer) elementAnterior[7],
						 false,
						descGerenciaRegionalAnterior,
						idGerenciaRegionalAnterior, descLocalidadeAnterior,
						idLocalidadeAnterior,descLancamentoTipoSuperior,
						descUnidadeNegocioAnterior,idUnidadeNegocioAnterior);

				colecaoResumoDevedoresDuvidososRelatorio.add(ResumoDevedoresDuvidososRelatorioHelper);
				
			
				arrayValores = new BigDecimal[5];
				switch (((Integer) element[8]).intValue()) {
				case 1:
					arrayValores[0] = (BigDecimal) element[0];
					break;
				case 2:
					arrayValores[1] = (BigDecimal) element[0];
					break;
				case 3:
					arrayValores[2] = (BigDecimal) element[0];
					break;
				case 4:
					arrayValores[4] = (BigDecimal) element[0];
					break;
				}

			}
			
			elementAnterior[1] = element[1]; // descricaoTipoLancamento
			if (((String)element[1]).equalsIgnoreCase((String)element[2])){
				elementAnterior[2] = null; // descricaoItemLancamento
			}else{
				elementAnterior[2] = element[2]; // descricaoItemLancamento
			}
			
			elementAnterior[3] = element[3]; // descricaoItemContabil
			elementAnterior[4] = element[4]; // indicadorImpressao
			elementAnterior[5] = element[5]; // indicadorTotal
			elementAnterior[6] = element[6]; // lancamentoTipo
			elementAnterior[7] = element[7]; // lancamentoTipoSuperior

			descGerenciaRegionalAnterior = "" + element[9];
			idGerenciaRegionalAnterior = "" + element[10];
			
			tipoLancamento = tempTipoLancamento;
			itemLancamento = tempItemLancamento;
			itemContabel = tempItemContabel;

		}

		if (colecaoResumoDevedoresDuvidososRelatorioPorGerenciaRegional != null
				&& !colecaoResumoDevedoresDuvidososRelatorioPorGerenciaRegional.isEmpty()) {
			// adiciona a ultima linha
	
			ResumoDevedoresDuvidososRelatorioHelper ResumoDevedoresDuvidososRelatorioHelper = 
				new ResumoDevedoresDuvidososRelatorioHelper(
					(BigDecimal[]) arrayValores,
					(String) elementAnterior[1],
					(String) elementAnterior[2],
					(String) elementAnterior[3],
					(Short) elementAnterior[4],
					(Short) elementAnterior[5], (Integer) elementAnterior[6],
					(Integer) elementAnterior[7],
					 false,
					descGerenciaRegionalAnterior,
					idGerenciaRegionalAnterior, descLocalidadeAnterior,
					idLocalidadeAnterior,descLancamentoTipoSuperior,
					descUnidadeNegocioAnterior,idUnidadeNegocioAnterior);

			colecaoResumoDevedoresDuvidososRelatorio.add(ResumoDevedoresDuvidososRelatorioHelper);
		}

		return colecaoResumoDevedoresDuvidososRelatorio;
	
	}
	
	/**
	 * Consulta ResumoDevedoresDuvidosos para a geração do relatório 
	 * [UC0487] Gerar Relatório de Resumo de Devedores Duvidosos
	 * de acordo com a opção de totalização.
	 * 
	 * @author Vivianne Sousa, Davi Menezes
	 * @created 20/07/2007, 17/11/2011
	 * 
	 * @param opcaoTotalizacao
	 * @return
	 * @throws ControladorException
	 */
	public Collection consultarResumoDevedoresDuvidososRelatorioPorEstadoPorLocalidade(int anoMesReferencia, int tipoPerda)
			throws ControladorException {

		Collection colecaoResumoDevedoresDuvidososRelatorioEstadoPorLocalidade = new ArrayList();
		Collection retorno = new ArrayList();
		
		try {
			colecaoResumoDevedoresDuvidososRelatorioEstadoPorLocalidade = 
				repositorioFinanceiro.consultarResumoDevedoresDuvidososRelatorioPorEstadoPorLocalidade(anoMesReferencia, tipoPerda);
			
			if (colecaoResumoDevedoresDuvidososRelatorioEstadoPorLocalidade != null &&
					!colecaoResumoDevedoresDuvidososRelatorioEstadoPorLocalidade.isEmpty()){
				
				Iterator iterator = colecaoResumoDevedoresDuvidososRelatorioEstadoPorLocalidade.iterator();

				// Prepara cada linha do relatório

				String tipoLancamento = null;
				String itemLancamento = null;
				String itemContabel = null;

				String descGerenciaRegionalAnterior = null;
				String idGerenciaRegionalAnterior = null;
				String descLocalidadeAnterior = null;
				String idLocalidadeAnterior = null;
				String descLancamentoTipoSuperior = "";
				
				String descUnidadeNegocioAnterior = null;
				String idUnidadeNegocioAnterior = null;

				Object[] elementAnterior = new Object[13];
				BigDecimal[] arrayValores = new BigDecimal[5];

				
				while (iterator.hasNext()) {
					Object[] element = null;
					String tempTipoLancamento = null;
					String tempItemLancamento = null;
					String tempItemContabel = null;

					element = (Object[]) iterator.next();

					if (tipoLancamento == null) {
						tipoLancamento = (String) element[1];
						itemLancamento = (String) element[2];
						itemContabel = (String) element[3];
					}

					tempTipoLancamento = (String) element[1];
					tempItemLancamento = (String) element[2];
					tempItemContabel = (String) element[3];

					boolean condicaoIgual = true;
					// compara se o registro atual eh do
					// mesmo tipo de Recebimento, mesmo tipo de lançamento
					// e mesmo item de lançamento do registro anterior
					if (tipoLancamento.equals(tempTipoLancamento) && itemLancamento.equals(tempItemLancamento)) {

						// se o registro possuir item contabel
						// compara se eh do mesmo item contabel do registro anterior
						if (itemContabel == null && tempItemContabel == null
							|| (itemContabel != null && tempItemContabel != null && itemContabel.equals(tempItemContabel))) {
						
						
								switch (((Integer) element[8]).intValue()) {
								case 1:
									arrayValores[0] = (BigDecimal) element[0];
									break;
								case 2:
									arrayValores[1] = (BigDecimal) element[0];
									break;
								case 3:
									arrayValores[2] = (BigDecimal) element[0];
									break;
								case 4:
									arrayValores[4] = (BigDecimal) element[0];
									break;
								}
						

						} else {

							condicaoIgual = false;
						}

					} else {

						condicaoIgual = false;

					}
					
					if (!condicaoIgual) {
						
						ResumoDevedoresDuvidososRelatorioHelper ResumoDevedoresDuvidososRelatorioHelper = 
							new ResumoDevedoresDuvidososRelatorioHelper(
								(BigDecimal[]) arrayValores,
								(String) elementAnterior[1],
								(String) elementAnterior[2],
								(String) elementAnterior[3],
								(Short) elementAnterior[4],
								(Short) elementAnterior[5], (Integer) elementAnterior[6],
								(Integer) elementAnterior[7],
								 false,
								descGerenciaRegionalAnterior,
								idGerenciaRegionalAnterior, descLocalidadeAnterior,
								idLocalidadeAnterior,descLancamentoTipoSuperior,
								descUnidadeNegocioAnterior,idUnidadeNegocioAnterior);

						retorno.add(ResumoDevedoresDuvidososRelatorioHelper);
						
					
						arrayValores = new BigDecimal[5];
						switch (((Integer) element[8]).intValue()) {
						case 1:
							arrayValores[0] = (BigDecimal) element[0];
							break;
						case 2:
							arrayValores[1] = (BigDecimal) element[0];
							break;
						case 3:
							arrayValores[2] = (BigDecimal) element[0];
							break;
						case 4:
							arrayValores[4] = (BigDecimal) element[0];
							break;
						}

					}
					
					
					if (idUnidadeNegocioAnterior != null && !idUnidadeNegocioAnterior.equals("" + element[16])){
						//quebra por Unidade Negocio
						retorno = consultarResumoDevedoresDuvidososRelatorioPorUnidadeNegocio(
								anoMesReferencia,new Integer (idUnidadeNegocioAnterior),retorno, tipoPerda);
						
					}
					
					if (idGerenciaRegionalAnterior != null && !idGerenciaRegionalAnterior.equals("" + element[10])){
						//quebra por gerencia
						retorno = consultarResumoDevedoresDuvidososRelatorioPorGerenciaRegional(anoMesReferencia,new Integer (idGerenciaRegionalAnterior),retorno, tipoPerda);
						
					}
					
					elementAnterior[1] = element[1]; // descricaoTipoLancamento
					if (((String)element[1]).equalsIgnoreCase((String)element[2])){
						elementAnterior[2] = null; // descricaoItemLancamento
					}else{
						elementAnterior[2] = element[2]; // descricaoItemLancamento
					}
					
					elementAnterior[3] = element[3]; // descricaoItemContabil
					elementAnterior[4] = element[4]; // indicadorImpressao
					elementAnterior[5] = element[5]; // indicadorTotal
					elementAnterior[6] = element[6]; // lancamentoTipo
					elementAnterior[7] = element[7]; // lancamentoTipoSuperior

				
					descGerenciaRegionalAnterior = "" + element[9];
					idGerenciaRegionalAnterior = "" + element[10];
					descLocalidadeAnterior = "" + element[11];
					idLocalidadeAnterior = "" + element[12];
					descUnidadeNegocioAnterior = "" + element[15];
					idUnidadeNegocioAnterior = "" + element[16];
						

					tipoLancamento = tempTipoLancamento;
					itemLancamento = tempItemLancamento;
					itemContabel = tempItemContabel;

				}

				if (colecaoResumoDevedoresDuvidososRelatorioEstadoPorLocalidade != null
						&& !colecaoResumoDevedoresDuvidososRelatorioEstadoPorLocalidade.isEmpty()) {
					// adiciona a ultima linha
			
					ResumoDevedoresDuvidososRelatorioHelper ResumoDevedoresDuvidososRelatorioHelper = 
						new ResumoDevedoresDuvidososRelatorioHelper(
							(BigDecimal[]) arrayValores,
							(String) elementAnterior[1],
							(String) elementAnterior[2],
							(String) elementAnterior[3],
							(Short) elementAnterior[4],
							(Short) elementAnterior[5], (Integer) elementAnterior[6],
							(Integer) elementAnterior[7],
							 false,
							descGerenciaRegionalAnterior,
							idGerenciaRegionalAnterior, descLocalidadeAnterior,
							idLocalidadeAnterior,descLancamentoTipoSuperior,
							descUnidadeNegocioAnterior,idUnidadeNegocioAnterior);

					retorno.add(ResumoDevedoresDuvidososRelatorioHelper);
					
					retorno = consultarResumoDevedoresDuvidososRelatorioPorUnidadeNegocio(anoMesReferencia,new Integer (idUnidadeNegocioAnterior),retorno, tipoPerda);
					
					retorno = consultarResumoDevedoresDuvidososRelatorioPorGerenciaRegional(anoMesReferencia,new Integer (idGerenciaRegionalAnterior),retorno, tipoPerda);
					
					retorno = consultarResumoDevedoresDuvidososRelatorioPorEstado(anoMesReferencia,retorno, tipoPerda);
				}
			}
			
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}
	
	
	/**
	 * Consulta ResumoDevedoresDuvidosos para a geração do relatório 
	 * [UC0487] Gerar Relatório de Resumo de Devedores Duvidosos
	 * de acordo com a opção de totalização.
	 * 
	 * @author Vivianne Sousa, Davi Menezes
	 * @created 20/07/2007, 17/11/2011
	 * 
	 * @param opcaoTotalizacao
	 * @return
	 * @throws ControladorException
	 */
	public Collection consultarResumoDevedoresDuvidososRelatorioPorUnidadeNegocio(int anoMesReferencia, 
			Integer idUnidadeNegocio , Collection colecaoResumoDevedoresDuvidososRelatorio, int tipoPerda)
			throws ControladorException {

		Collection colecaoResumoDevedoresDuvidososRelatorioPorUnidadeNegocio = new ArrayList();
		
		try {
			colecaoResumoDevedoresDuvidososRelatorioPorUnidadeNegocio = 
				repositorioFinanceiro.consultarResumoDevedoresDuvidososRelatorioPorUnidadeNegocio(anoMesReferencia, idUnidadeNegocio, tipoPerda);
			
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
		
		Iterator iterator = colecaoResumoDevedoresDuvidososRelatorioPorUnidadeNegocio.iterator();
		
		String tipoLancamento = null;
		String itemLancamento = null;
		String itemContabel = null;

		String descGerenciaRegionalAnterior = null;
		String idGerenciaRegionalAnterior = null;
		String descLocalidadeAnterior = null;
		String idLocalidadeAnterior = null;
		String descLancamentoTipoSuperior = "";
		
		String descUnidadeNegocioAnterior = null;
		String idUnidadeNegocioAnterior = null;

		Object[] elementAnterior = new Object[13];
		BigDecimal[] arrayValores = new BigDecimal[5];

		while (iterator.hasNext()) {
			Object[] element = null;
			String tempTipoLancamento = null;
			String tempItemLancamento = null;
			String tempItemContabel = null;

			element = (Object[]) iterator.next();

			if (tipoLancamento == null) {
				tipoLancamento = (String) element[1];
				itemLancamento = (String) element[2];
				itemContabel = (String) element[3];
			}

			tempTipoLancamento = (String) element[1];
			tempItemLancamento = (String) element[2];
			tempItemContabel = (String) element[3];

			boolean condicaoIgual = true;
			// compara se o registro atual eh do
			// mesmo tipo de Recebimento, mesmo tipo de lançamento
			// e mesmo item de lançamento do registro anterior
			if (tipoLancamento.equals(tempTipoLancamento) && itemLancamento.equals(tempItemLancamento)) {

				// se o registro possuir item contabel
				// compara se eh do mesmo item contabel do registro anterior
				if (itemContabel == null && tempItemContabel == null
					|| (itemContabel != null && tempItemContabel != null && itemContabel.equals(tempItemContabel))) {
				
						switch (((Integer) element[8]).intValue()) {
						case 1:
							arrayValores[0] = (BigDecimal) element[0];
							break;
						case 2:
							arrayValores[1] = (BigDecimal) element[0];
							break;
						case 3:
							arrayValores[2] = (BigDecimal) element[0];
							break;
						case 4:
							arrayValores[4] = (BigDecimal) element[0];
							break;
						}

				} else {

					condicaoIgual = false;
				}

			} else {

				condicaoIgual = false;

			}
			
			if (!condicaoIgual) {
				
				ResumoDevedoresDuvidososRelatorioHelper ResumoDevedoresDuvidososRelatorioHelper = 
					new ResumoDevedoresDuvidososRelatorioHelper(
						(BigDecimal[]) arrayValores,
						(String) elementAnterior[1],
						(String) elementAnterior[2],
						(String) elementAnterior[3],
						(Short) elementAnterior[4],
						(Short) elementAnterior[5], (Integer) elementAnterior[6],
						(Integer) elementAnterior[7],
						 false,
						descGerenciaRegionalAnterior,
						idGerenciaRegionalAnterior, descLocalidadeAnterior,
						idLocalidadeAnterior,descLancamentoTipoSuperior,
						descUnidadeNegocioAnterior,idUnidadeNegocioAnterior);

				colecaoResumoDevedoresDuvidososRelatorio.add(ResumoDevedoresDuvidososRelatorioHelper);
				
				arrayValores = new BigDecimal[5];
				switch (((Integer) element[8]).intValue()) {
				case 1:
					arrayValores[0] = (BigDecimal) element[0];
					break;
				case 2:
					arrayValores[1] = (BigDecimal) element[0];
					break;
				case 3:
					arrayValores[2] = (BigDecimal) element[0];
					break;
				case 4:
					arrayValores[4] = (BigDecimal) element[0];
					break;
				}

			}
			
			elementAnterior[1] = element[1]; // descricaoTipoLancamento
			if (((String)element[1]).equalsIgnoreCase((String)element[2])){
				elementAnterior[2] = null; // descricaoItemLancamento
			}else{
				elementAnterior[2] = element[2]; // descricaoItemLancamento
			}
			
			elementAnterior[3] = element[3]; // descricaoItemContabil
			elementAnterior[4] = element[4]; // indicadorImpressao
			elementAnterior[5] = element[5]; // indicadorTotal
			elementAnterior[6] = element[6]; // lancamentoTipo
			elementAnterior[7] = element[7]; // lancamentoTipoSuperior

			descGerenciaRegionalAnterior = "" + element[13];
			idGerenciaRegionalAnterior = "" + element[14];
			descUnidadeNegocioAnterior = "" + element[9];
			idUnidadeNegocioAnterior = "" + element[10];

			tipoLancamento = tempTipoLancamento;
			itemLancamento = tempItemLancamento;
			itemContabel = tempItemContabel;

		}

		if (colecaoResumoDevedoresDuvidososRelatorio != null
				&& !colecaoResumoDevedoresDuvidososRelatorio.isEmpty()) {
			// adiciona a ultima linha
	
			ResumoDevedoresDuvidososRelatorioHelper ResumoDevedoresDuvidososRelatorioHelper = 
				new ResumoDevedoresDuvidososRelatorioHelper(
					(BigDecimal[]) arrayValores,
					(String) elementAnterior[1],
					(String) elementAnterior[2],
					(String) elementAnterior[3],
					(Short) elementAnterior[4],
					(Short) elementAnterior[5], (Integer) elementAnterior[6],
					(Integer) elementAnterior[7],
					 false,
					descGerenciaRegionalAnterior,
					idGerenciaRegionalAnterior, descLocalidadeAnterior,
					idLocalidadeAnterior,descLancamentoTipoSuperior,
					descUnidadeNegocioAnterior,idUnidadeNegocioAnterior);

			colecaoResumoDevedoresDuvidososRelatorio.add(ResumoDevedoresDuvidososRelatorioHelper);
		}

		return colecaoResumoDevedoresDuvidososRelatorio;
	}

	
	
	/**
	 * Consulta ResumoDevedoresDuvidosos para a geração do relatório 
	 * [UC0487] Gerar Relatório de Resumo de Devedores Duvidosos
	 * de acordo com a opção de totalização.
	 * 
	 * @author Vivianne Sousa
	 * @created 20/07/2007
	 * 
	 * @param opcaoTotalizacao
	 * @return
	 * @throws ControladorException
	 */
	public Collection consultarResumoDevedoresDuvidososRelatorioPorEstado(int anoMesReferencia, 
			Collection colecaoResumoDevedoresDuvidososRelatorio, int tipoPerda )
			throws ControladorException {

		Collection colecaoResumoDevedoresDuvidososRelatorioPorEstado = new ArrayList();
		
		try {
			colecaoResumoDevedoresDuvidososRelatorioPorEstado = 
				repositorioFinanceiro.consultarResumoDevedoresDuvidososRelatorioPorEstado(anoMesReferencia, tipoPerda);
			
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
		
		Iterator iterator = colecaoResumoDevedoresDuvidososRelatorioPorEstado.iterator();
		
		String tipoLancamento = null;
		String itemLancamento = null;
		String itemContabel = null;

		String descGerenciaRegionalAnterior = null;
		String idGerenciaRegionalAnterior = null;
		String descLocalidadeAnterior = null;
		String idLocalidadeAnterior = null;
		String descLancamentoTipoSuperior = "";
		
		String descUnidadeNegocioAnterior = null;
		String idUnidadeNegocioAnterior = null;

		Object[] elementAnterior = new Object[13];
		BigDecimal[] arrayValores = new BigDecimal[5];

		while (iterator.hasNext()) {
			Object[] element = null;
			String tempTipoLancamento = null;
			String tempItemLancamento = null;
			String tempItemContabel = null;

			element = (Object[]) iterator.next();

			if (tipoLancamento == null) {
				tipoLancamento = (String) element[1];
				itemLancamento = (String) element[2];
				itemContabel = (String) element[3];
			}

			tempTipoLancamento = (String) element[1];
			tempItemLancamento = (String) element[2];
			tempItemContabel = (String) element[3];

			boolean condicaoIgual = true;
			// compara se o registro atual eh do
			// mesmo tipo de Recebimento, mesmo tipo de lançamento
			// e mesmo item de lançamento do registro anterior
			if (tipoLancamento.equals(tempTipoLancamento) && itemLancamento.equals(tempItemLancamento)) {

				// se o registro possuir item contabel
				// compara se eh do mesmo item contabel do registro anterior
				if (itemContabel == null && tempItemContabel == null
					|| (itemContabel != null && tempItemContabel != null && itemContabel.equals(tempItemContabel))) {

						switch (((Integer) element[8]).intValue()) {
						case 1:
							arrayValores[0] = (BigDecimal) element[0];
							break;
						case 2:
							arrayValores[1] = (BigDecimal) element[0];
							break;
						case 3:
							arrayValores[2] = (BigDecimal) element[0];
							break;
						case 4:
							arrayValores[4] = (BigDecimal) element[0];
							break;
						}

				} else {

					condicaoIgual = false;
				}

			} else {

				condicaoIgual = false;

			}
			
			if (!condicaoIgual) {
				
				ResumoDevedoresDuvidososRelatorioHelper ResumoDevedoresDuvidososRelatorioHelper = 
					new ResumoDevedoresDuvidososRelatorioHelper(
						(BigDecimal[]) arrayValores,
						(String) elementAnterior[1],
						(String) elementAnterior[2],
						(String) elementAnterior[3],
						(Short) elementAnterior[4],
						(Short) elementAnterior[5], (Integer) elementAnterior[6],
						(Integer) elementAnterior[7],
						 false,
						descGerenciaRegionalAnterior,
						idGerenciaRegionalAnterior, descLocalidadeAnterior,
						idLocalidadeAnterior,descLancamentoTipoSuperior,
						descUnidadeNegocioAnterior,idUnidadeNegocioAnterior);

				colecaoResumoDevedoresDuvidososRelatorio.add(ResumoDevedoresDuvidososRelatorioHelper);
				
			
				arrayValores = new BigDecimal[5];
				switch (((Integer) element[8]).intValue()) {
				case 1:
					arrayValores[0] = (BigDecimal) element[0];
					break;
				case 2:
					arrayValores[1] = (BigDecimal) element[0];
					break;
				case 3:
					arrayValores[2] = (BigDecimal) element[0];
					break;
				case 4:
					arrayValores[4] = (BigDecimal) element[0];
					break;
				}

			}
			
			elementAnterior[1] = element[1]; // descricaoTipoLancamento
			if (((String)element[1]).equalsIgnoreCase((String)element[2])){
				elementAnterior[2] = null; // descricaoItemLancamento
			}else{
				elementAnterior[2] = element[2]; // descricaoItemLancamento
			}
			
			elementAnterior[3] = element[3]; // descricaoItemContabil
			elementAnterior[4] = element[4]; // indicadorImpressao
			elementAnterior[5] = element[5]; // indicadorTotal
			elementAnterior[6] = element[6]; // lancamentoTipo
			elementAnterior[7] = element[7]; // lancamentoTipoSuperior

			tipoLancamento = tempTipoLancamento;
			itemLancamento = tempItemLancamento;
			itemContabel = tempItemContabel;

		}

		if (colecaoResumoDevedoresDuvidososRelatorioPorEstado != null
				&& !colecaoResumoDevedoresDuvidososRelatorioPorEstado.isEmpty()) {
			// adiciona a ultima linha
	
			ResumoDevedoresDuvidososRelatorioHelper ResumoDevedoresDuvidososRelatorioHelper = 
				new ResumoDevedoresDuvidososRelatorioHelper(
					(BigDecimal[]) arrayValores,
					(String) elementAnterior[1],
					(String) elementAnterior[2],
					(String) elementAnterior[3],
					(Short) elementAnterior[4],
					(Short) elementAnterior[5], (Integer) elementAnterior[6],
					(Integer) elementAnterior[7],
					 false,
					descGerenciaRegionalAnterior,
					idGerenciaRegionalAnterior, descLocalidadeAnterior,
					idLocalidadeAnterior,descLancamentoTipoSuperior,
					descUnidadeNegocioAnterior,idUnidadeNegocioAnterior);

			colecaoResumoDevedoresDuvidososRelatorio.add(ResumoDevedoresDuvidososRelatorioHelper);
		}
		
		return colecaoResumoDevedoresDuvidososRelatorio;
	}

	
	/**
	 * [UC0718] Gerar Relatório de Evolucao do Contas a Receber Contabil
	 * 
	 * @author Francisco Junior, Ivan Sergio
	 * @date 02/01/08, 20/07/2010
	 * @alteracao - 20/07/2010: Alterado a posicao e descricao do item IMPOSTOS DEDUZIDOS;
	 * 							Os valores do item IMPOSTOS DEDUZIDOS sao obtidos a partir da tabela
	 * 							RESUMO ARRECADACAO sequencias 1200, 1300, 1400 e 1500 (lcit_id = 19, 20, 21 e 22);
	 * 			  - 28/07/2010: Modificado o sinal de Impostos Deduzidos para subtrair;
	 * 							Adicionado o item Impostos de Meses Anteriores Classificados no Mes;
	 * 
	 * @param opcaoTotalizacao
	 * @param mesAno
	 * @param codigoGerencia
	 * @param codigoLocalidade
	 * @param unidadeNegocio
	 * @return Colecao 
	 * @throws ControladorException
	 */
	public Collection<RelatorioEvolucaoContasAReceberContabilBean> consultarDadosEvolucaoContasAReceberContabilRelatorio(String opcaoTotalizacao,
		int mesAno, Integer codigoGerencia, Integer codigoLocalidade, Integer codigoMunicipio, Integer unidadeNegocio) throws ControladorException {
	
		// este mapeamento é composto de uma descrição do grupo como chave (localidade, gerencia ou unidade de negocio)
		// e a coleção de valores associado a esta chave
		HashMap<Integer, BigDecimal[][]> dadosAgrupados = new HashMap<Integer, BigDecimal[][]>();  
		HashMap<Integer, String> labelChaves = new HashMap<Integer, String>();
		int anoMes = Util.formatarMesAnoParaAnoMes(mesAno);
		// As linhas de detalhes são fixas e nesta ordem 
		String descricoes[] = {
			"S A L D O    A N T E R I O R", 		// linha 1 
			"(+) FATURAMENTO AGUA",					// linha 2
			"(+) FATURAMENTO ESGOTO",				// linha 3
			"(+) FINANCIAMENTOS INCLUIDOS",			// linha 4
			"(+) JUROS DE PARCELAMENTO",			// linha 5
			"(+) GUIAS DE PAGAMENTO",				// linha 6
			"(+) INCLUSOES POR REFATURAMENTO",		// linha 7
			"(=) FATURAMENTO BRUTO DO MES",			// linha 8
			"(-) FINANCIAMENTOS CANCELADOS",		// linha 9	
			"(-) PARCELAMENTOS CANCELADOS",			// linha 10
			"(-) CANCELAMENTOS POR REFATURAMENTO",	// linha 11
			"(-) CRÉDITOS A REALIZAR POR COBRANÇA INDEVIDA", // linha 12
			"(-) DESCONTOS INCONDICIONAIS INCLUÍDOS", // linha 13
			"(-) GUIAS DE DEVOLUÇÃO DE VALORES COBRADOS INDEVIDAMENTE INCLUÍDAS", // linha 14
			"(=) FATURAMENTO LÍQUIDO DO MES",		// linha 15 -> 15
			"(-) DESCONTOS CONCEDIDOS NO PARCELAMENTO",	// linha 16 -> 16
			"(-) OUTROS CRÉDITOS INCLUÍDOS",	// linha 17 -> 17
//			"(+) IMPOSTOS CANCELADOS POR REFATURAMENTO",// linha 18 -> 18
//			"(-) IMPOSTOS INCLUÍDOS POR REFATURAMENTO",	// linha 19 -> 19
			"(+) OUTROS CRÉDITOS CANCELADOS POR REFATURAMENTO",	// linha 18 -> 20 - > 18
			"(-) OUTROS CRÉDITOS CONCEDIDOS POR REFATURAMENTO",	// linha 19 -> 21 - > 19
			"(-) RECEBIMENTOS CLASSIFICADOS",		// linha 20 -> 22 -> 20
			"(-) IMPOSTOS DEDUZIDOS NA ARRECADACAO",	// linha 21 -> 23 -> 21
			"(+) RECEBIMENTO DE DESCONTOS POR CREDITO",	// linha 22
			"(-) RECEBIMENTOS DE MESES ANTERIORES CLASSIFICADOS NO MES", // linha 23 -> 24 ->22
			"(-) IMPOSTOS DE MESES ANTERIORES CLASSIFICADOS NO MES", // linha -> 24 -> 23
			"(-) CANCELAMENTOS POR PRESCRICAO", // linha 25 -> 26 -> 24
			"S A L D O    A T U A L"				// linha 26 -> 27 ->25
			};
	 
		Collection pesquisaSaldo = new ArrayList();
		Collection pesquisaDadosFaturamento = new ArrayList();
		Collection pesquisaDadosRecebimentos = new ArrayList();
		int anoMesAnterior = Util.subtraiAteSeisMesesAnoMesReferencia(anoMes, 1);
		try {
	
			if (opcaoTotalizacao.equals("estado")) {
				// quando os parametros gerencia, unidade de negocio e localidade são passados nulos, 
				// a pesquisa é feita sem restrição, trazendo os dados referentes a todo o estado.
				pesquisaSaldo = 
					repositorioFinanceiro.consultarSaldoEvolucaoContasAReceberContabilRelatorioPorEstado(
						anoMesAnterior, null, null, null, null);
				pesquisaDadosFaturamento = 
					repositorioFinanceiro.consultarDadosEvolucaoContasAReceberContabilRelatorioPorEstado(
						anoMes, null, null, null, null);
				pesquisaDadosRecebimentos = 
					repositorioFinanceiro.consultarRecebimentosContasAReceberContabilRelatorioPorEstado(
						anoMes, null, null, null, null);
	
			} else if (opcaoTotalizacao.equals("estadoGerencia")) {
				pesquisaSaldo = 
					repositorioFinanceiro
					.consultarSaldoEvolucaoContasAReceberContabilRelatorioPorGerenciaRegional(
						anoMesAnterior);				
				pesquisaDadosFaturamento = 
					repositorioFinanceiro
					.consultarDadosEvolucaoContasAReceberContabilRelatorioPorGerenciaRegional(
						anoMes);
				pesquisaDadosRecebimentos = 
					repositorioFinanceiro
					.consultarRecebimentosContasAReceberContabilRelatorioPorGerenciaRegional(
						anoMes);
				
			} else if (opcaoTotalizacao.equals("estadoUnidadeNegocio")) {
				pesquisaSaldo = 
					repositorioFinanceiro
					.consultarSaldoEvolucaoContasAReceberContabilRelatorioPorUnidadeNegocio(
						anoMesAnterior, null);
				pesquisaDadosFaturamento = 
					repositorioFinanceiro
					.consultarDadosEvolucaoContasAReceberContabilRelatorioPorUnidadeNegocio(
						anoMes, null);
				pesquisaDadosRecebimentos = 
					repositorioFinanceiro
					.consultarRecebimentosContasAReceberContabilRelatorioPorUnidadeNegocio(
						anoMes, null);
	
			} else if (opcaoTotalizacao.equals("estadoLocalidade")) {
				pesquisaSaldo = 
					repositorioFinanceiro
					.consultarSaldoEvolucaoContasAReceberContabilRelatorioPorLocalidade(
						anoMesAnterior, null, null);
				pesquisaDadosFaturamento = 
					repositorioFinanceiro
					.consultarDadosEvolucaoContasAReceberContabilRelatorioPorLocalidade(
						anoMes, null, null);
				pesquisaDadosRecebimentos = 
					repositorioFinanceiro
					.consultarRecebimentosContasAReceberContabilRelatorioPorLocalidade(
						anoMes, null, null);
	
			} else if (opcaoTotalizacao.equals("estadoMunicipio")) {
				pesquisaSaldo = repositorioFinanceiro.
					consultarSaldoEvolucaoContasAReceberContabilRelatorioPorMunicipio(anoMesAnterior);
				pesquisaDadosFaturamento = repositorioFinanceiro.
					consultarDadosEvolucaoContasAReceberContabilRelatorioPorMunicipio(anoMes);
				pesquisaDadosRecebimentos = repositorioFinanceiro.
					consultarRecebimentosContasAReceberContabilRelatorioPorMunicipio(anoMes);
	
			} else if (opcaoTotalizacao.equals("gerenciaRegional")) {
				pesquisaSaldo = 
					repositorioFinanceiro.consultarSaldoEvolucaoContasAReceberContabilRelatorioPorEstado(
						anoMesAnterior, codigoGerencia, null, null, null);
				pesquisaDadosFaturamento = 
					repositorioFinanceiro.consultarDadosEvolucaoContasAReceberContabilRelatorioPorEstado(
						anoMes, codigoGerencia, null, null, null);
				pesquisaDadosRecebimentos = 
					repositorioFinanceiro.consultarRecebimentosContasAReceberContabilRelatorioPorEstado(
						anoMes, codigoGerencia, null, null, null);
			} else if (opcaoTotalizacao.equals("gerenciaRegionalUnidadeNegocio")) {
				pesquisaSaldo = 
					repositorioFinanceiro.consultarSaldoEvolucaoContasAReceberContabilRelatorioPorUnidadeNegocio(
						anoMesAnterior, codigoGerencia);
				pesquisaDadosFaturamento = 
					repositorioFinanceiro.consultarDadosEvolucaoContasAReceberContabilRelatorioPorUnidadeNegocio(
						anoMes, codigoGerencia);
				pesquisaDadosRecebimentos = 
					repositorioFinanceiro.consultarRecebimentosContasAReceberContabilRelatorioPorUnidadeNegocio(
						anoMes, codigoGerencia);
			} else if (opcaoTotalizacao.equals("gerenciaRegionalLocalidade")) {
				pesquisaSaldo = 
					repositorioFinanceiro
					.consultarSaldoEvolucaoContasAReceberContabilRelatorioPorLocalidade(
						anoMesAnterior, codigoGerencia, null);
				pesquisaDadosFaturamento = 
					repositorioFinanceiro
					.consultarDadosEvolucaoContasAReceberContabilRelatorioPorLocalidade(
						anoMes, codigoGerencia, null);
				pesquisaDadosRecebimentos = 
					repositorioFinanceiro
					.consultarRecebimentosContasAReceberContabilRelatorioPorLocalidade(
						anoMes, codigoGerencia, null);
	
			} else if (opcaoTotalizacao.equals("unidadeNegocioLocalidade")) {
				pesquisaSaldo = 
					repositorioFinanceiro
					.consultarSaldoEvolucaoContasAReceberContabilRelatorioPorLocalidade(
						anoMesAnterior, null, unidadeNegocio);
				pesquisaDadosFaturamento = 
					repositorioFinanceiro
					.consultarDadosEvolucaoContasAReceberContabilRelatorioPorLocalidade(
						anoMes, null, unidadeNegocio);
				pesquisaDadosRecebimentos = 
					repositorioFinanceiro
					.consultarRecebimentosContasAReceberContabilRelatorioPorLocalidade(
						anoMes, null, unidadeNegocio);
			} else if (opcaoTotalizacao.equals("unidadeNegocio")) {
				pesquisaSaldo = 
					repositorioFinanceiro.consultarSaldoEvolucaoContasAReceberContabilRelatorioPorEstado(
						anoMesAnterior, null, unidadeNegocio, null, null);
				pesquisaDadosFaturamento = 
					repositorioFinanceiro.consultarDadosEvolucaoContasAReceberContabilRelatorioPorEstado(
						anoMes, null, unidadeNegocio, null, null);
				pesquisaDadosRecebimentos = 
					repositorioFinanceiro.consultarRecebimentosContasAReceberContabilRelatorioPorEstado(
						anoMes, null, unidadeNegocio, null, null);				
			} else if (opcaoTotalizacao.equals("localidade")) {
				pesquisaSaldo = 
					repositorioFinanceiro.consultarSaldoEvolucaoContasAReceberContabilRelatorioPorEstado(
						anoMesAnterior, null, null, codigoLocalidade, null);
				pesquisaDadosFaturamento = 
					repositorioFinanceiro.consultarDadosEvolucaoContasAReceberContabilRelatorioPorEstado(
						anoMes, null, null, codigoLocalidade, null);
				pesquisaDadosRecebimentos = 
					repositorioFinanceiro.consultarRecebimentosContasAReceberContabilRelatorioPorEstado(
						anoMes, null, null, codigoLocalidade, null);
			} else if (opcaoTotalizacao.equals("municipio")) {
				pesquisaSaldo = 
					repositorioFinanceiro.consultarSaldoEvolucaoContasAReceberContabilRelatorioPorEstado(
						anoMesAnterior, null, null, null, codigoMunicipio);
				pesquisaDadosFaturamento = 
					repositorioFinanceiro.consultarDadosEvolucaoContasAReceberContabilRelatorioPorEstado(
						anoMes, null, null, null, codigoMunicipio);
				pesquisaDadosRecebimentos = 
					repositorioFinanceiro.consultarRecebimentosContasAReceberContabilRelatorioPorEstado(
						anoMes, null, null, null, codigoMunicipio);
			}
			// Tratando a consulta dos saldos em conta a receber contabil
			Iterator iter = pesquisaSaldo.iterator();
			Integer idDescAnterior = -1;
			while (iter.hasNext()){
				Object[] element = (Object[]) iter.next();
				String desc = (String) element[0];
				Integer idDesc = (Integer) element[1];
				labelChaves.put(idDesc, desc);
				
				Integer codigo = (Integer) element[3];
				// recupera o conjunto de valores atual para a chave 'desc'
				BigDecimal[][] valoresItem = dadosAgrupados.get(idDesc);
				// quando a descrição atual for diferente da anterior é pq é hora de criar um novo grupo de valoresItem
				if (idDesc.intValue() != idDescAnterior.intValue()){				
					valoresItem = new BigDecimal[descricoes.length][2];
					for (int i = 0; i < valoresItem.length; i++) {
						valoresItem[i][0] = new BigDecimal(0);
						valoresItem[i][1] = new BigDecimal(0);
					}
					// SALDO ANTERIOR
					valoresItem[0] = new BigDecimal[2];										
					valoresItem[0][0] = new BigDecimal(0); // PARTICULAR
					valoresItem[0][1] = new BigDecimal(0); // PUBLICO
					dadosAgrupados.put(idDesc, valoresItem);
				}
				// no select retornou o codigo do tipo de categoria (1 ou 2)
				// entao em cada linha de resultado devemos verificar se foi do particular ou privado
				if (codigo.intValue() == CategoriaTipo.PARTICULAR){
					valoresItem[0][0] = (BigDecimal) element[4];	// PARTICULAR
				} else {
					valoresItem[0][1] = (BigDecimal) element[4];    // PUBLICO
				}				
				idDescAnterior = idDesc;				
			}
			
			// conjunto de valores: cada linha é um item diretamente associado ao array descricoes
			// e na primeira coluna o valor de particular, na segunda, o valor de privado
			// na definição do bean do relatório, o valor total é definido como a soma destes dois em cada linha
			BigDecimal[][] valoresItem = null; 
			idDescAnterior = -1;
			
			//Tratando dados de faturamento
			iter = pesquisaDadosFaturamento.iterator();
			while (iter.hasNext()){
				Object[] element = (Object[]) iter.next();
				String desc = (String) element[0];
				Integer idDesc = (Integer) element[1];	
				labelChaves.put(idDesc, desc);
				
				Short sequenciaTipoLancamento = (Short) element[2];
				Short sequenciaItemTipoLancamento = (Short) element[3];
				Integer tipoCategoria = (Integer) element[4];
				BigDecimal valorItem = (BigDecimal) element[5];
				
				if (idDescAnterior.intValue() != idDesc.intValue()){
					// continuando o array de valoresItem iniciado com o item SALDO ANTERIOR
					valoresItem = dadosAgrupados.get(idDesc);
					if (valoresItem == null){
						valoresItem = new BigDecimal[descricoes.length][2];
						for (int i = 0; i < valoresItem.length; i++) {
							valoresItem[i][0] = new BigDecimal(0);
							valoresItem[i][1] = new BigDecimal(0);
						}
						dadosAgrupados.put(idDesc, valoresItem);
					}
				} 
				idDescAnterior = idDesc;
				
				// o indice diz respeito a posicao deste item na exibição, que é também 
				// a posição no array valoresItem
				short indiceTipoLancamento = -1;
				switch (sequenciaTipoLancamento.shortValue()) {				
				case ResumoFaturamento.FATURAMENTO_AGUA_SEQUENCIA_TIPO_LANCAMENTO:
					indiceTipoLancamento = 1;
					break;
				case ResumoFaturamento.FATURAMENTO_ESGOTO_SEQUENCIA_TIPO_LANCAMENTO:
					indiceTipoLancamento = 2;
					break;
				case ResumoFaturamento.FINANCIAMENTOS_CANCELADOS_SEQUENCIA_TIPO_LANCAMENTO_1:
				case ResumoFaturamento.FINANCIAMENTOS_CANCELADOS_SEQUENCIA_TIPO_LANCAMENTO_2:
					indiceTipoLancamento = 8;
					break;
				case ResumoFaturamento.FINANCIAMENTOS_INCLUIDOS_SEQUENCIA_TIPO_LANCAMENTO_1:
				case ResumoFaturamento.FINANCIAMENTOS_INCLUIDOS_SEQUENCIA_TIPO_LANCAMENTO_2:					
					indiceTipoLancamento = 3;
					break;
				case ResumoFaturamento.GUIAS_PAGAMENTO_SEQUENCIA_TIPO_LANCAMENTO:					
					indiceTipoLancamento = 5;
					break;					
	//			case ResumoFaturamento.IMPOSTOS_DEDUZIDOS_SEQUENCIA_TIPO_LANCAMENTO:					
	//				indiceTipoLancamento = 22;
	//				break;
				case ResumoFaturamento.JUROS_PARCELAMENTO_SEQUENCIA_TIPO_LANCAMENTO_1:
				case ResumoFaturamento.JUROS_PARCELAMENTO_SEQUENCIA_TIPO_LANCAMENTO_2:
					indiceTipoLancamento = 4;
					break;
				case ResumoFaturamento.PARCELAMENTOS_CANCELADOS_SEQUENCIA_TIPO_LANCAMENTO_1:
				case ResumoFaturamento.PARCELAMENTOS_CANCELADOS_SEQUENCIA_TIPO_LANCAMENTO_2:
					indiceTipoLancamento = 9;
					break;
				case ResumoFaturamento.CREDITOS_A_REALIZAR_POR_COBRANCA_INDEVIDA_SEQUENCIA_TIPO_LANCAMENTO:
					indiceTipoLancamento = 11;
					break;
				case ResumoFaturamento.DESCONTOS_INCONDICIONAIS_INCLUIDOS_SEQUENCIA_TIPO_LANCAMENTO:
					indiceTipoLancamento = 12;
					break;
				case ResumoFaturamento.GUIAS_DE_DEVOLUCOES_DE_VALORES_COBRADOS_SEQUENCIA_TIPO_LANCAMENTO:
					indiceTipoLancamento = 13;
					break;
//				case ResumoFaturamento.VALORES_CREDITADOS_SEQUENCIA_TIPO_LANCAMENTO_1:
//				case ResumoFaturamento.VALORES_CREDITADOS_SEQUENCIA_TIPO_LANCAMENTO_2:
				case ResumoFaturamento.VALORES_CREDITADOS_SEQUENCIA_TIPO_LANCAMENTO_3:
					indiceTipoLancamento = 16;
					break;					
				case ResumoFaturamento.DESCONTOS_CONCEDIDOS_PARCELAMENTO_SEQUENCIA_TIPO_LANCAMENTO_1:
				case ResumoFaturamento.DESCONTOS_CONCEDIDOS_PARCELAMENTO_SEQUENCIA_TIPO_LANCAMENTO_2:
					if (sequenciaItemTipoLancamento.shortValue() == ResumoFaturamento.
							DESCONTOS_CONCEDIDOS_PARCELAMENTO_SEQUENCIA_ITEM_TIPO_LANCAMENTO){
						indiceTipoLancamento = 15;						
					} else {
						continue;
					}
					break;					
//				case ResumoFaturamento.IMPOSTOS_CANCELADOS_POR_REFATURAMENTO_SEQUENCIA_TIPO_LANCAMENTO:
//					indiceTipoLancamento = 17;
//					break;					
//				case ResumoFaturamento.IMPOSTOS_INCLUIDOS_POR_REFATURAMENTO_SEQUENCIA_TIPO_LANCAMENTO:
//					indiceTipoLancamento = 18;
//					break;					
				case ResumoFaturamento.OUTROS_CREDITOS_CANCELADOS_POR_REFATURAMENTO_SEQUENCIA_TIPO_LANCAMENTO:
					indiceTipoLancamento = 17;
					break;					
				case ResumoFaturamento.OUTROS_CREDITOS_CONCEDIDOS_POR_REFATURAMENTO_SEQUENCIA_TIPO_LANCAMENTO:
					indiceTipoLancamento = 18;
					break;					
				case ResumoFaturamento.CANCELAMENTOS_POR_PRESCRICAO_SEQUENCIA_TIPO_LANCAMENTO:
					indiceTipoLancamento = 24;
					break;
				default:
					if (sequenciaTipoLancamento.shortValue() >= 
						ResumoFaturamento.INCLUSOES_POR_REFATURAMENTO_SEQUENCIA_TIPO_LANCAMENTO_1
						&& sequenciaTipoLancamento.shortValue() <=	
						ResumoFaturamento.INCLUSOES_POR_REFATURAMENTO_SEQUENCIA_TIPO_LANCAMENTO_2){
						//535 = total das inclusoes por refaturamento, não deve ser somado aos iténs
						if(sequenciaTipoLancamento.shortValue() != 535){
							indiceTipoLancamento = 6;
						}else{
							System.out.println("teste");
						}
					} else if (sequenciaTipoLancamento.shortValue() >= 
						ResumoFaturamento.CANCELAMENTOS_POR_REFATURAMENTO_SEQUENCIA_TIPO_LANCAMENTO_1
						&& sequenciaTipoLancamento.shortValue() <=
						ResumoFaturamento.CANCELAMENTOS_POR_REFATURAMENTO_SEQUENCIA_TIPO_LANCAMENTO_2){
						indiceTipoLancamento = 10;
					} else {
						// existem valores de sequenciaTipoLancamento que nao serao usados nesse relatorio 
						continue;						
					}
				}
				if(indiceTipoLancamento != -1){
					if (tipoCategoria.intValue() == CategoriaTipo.PARTICULAR){
						if (valoresItem[indiceTipoLancamento][0] == null){ // PARTICULAR
							valoresItem[indiceTipoLancamento][0] = valorItem;
						} else {
							valoresItem[indiceTipoLancamento][0] = valoresItem[indiceTipoLancamento][0].add(valorItem);	
						}					 
					} else {
						if (valoresItem[indiceTipoLancamento][1] == null){ // PUBLICO
							valoresItem[indiceTipoLancamento][1] = valorItem;
						} else {
							valoresItem[indiceTipoLancamento][1] = valoresItem[indiceTipoLancamento][1].add(valorItem);	
						}
					}
				}
				
				
			}
			
			idDescAnterior = -1;
	
			// Pesquisando dados de recebimentos referentes ao preenchimento das linhas 15 e 16			
			iter = pesquisaDadosRecebimentos.iterator();
			while (iter.hasNext()){
				Object[] element = (Object[]) iter.next();
				String desc = (String) element[0];
				Integer idDesc = (Integer) element[1];			
				labelChaves.put(idDesc, desc);
				
				Short sequenciaTipoLancamento = (Short) element[2];
				Integer tipoCategoria = (Integer) element[3];
				BigDecimal valorItem = (BigDecimal) element[4];
	
				if (idDescAnterior.intValue() != idDesc.intValue()){
					// continuando o array de valoresItem iniciado com o item SALDO ANTERIOR
					valoresItem = dadosAgrupados.get(idDesc);
					if (valoresItem == null){
						valoresItem = new BigDecimal[descricoes.length][2];
						for (int i = 0; i < valoresItem.length; i++) {
							valoresItem[i][0] = new BigDecimal(0);
							valoresItem[i][1] = new BigDecimal(0);
						}
						dadosAgrupados.put(idDesc, valoresItem);
					}
				} 
				idDescAnterior = idDesc;
				
				short indiceTipoLancamento = -1;
				short indicadorOperacao = 1; // 1=> SOMAR .... 2=>SUBTRAIR
				switch (sequenciaTipoLancamento.shortValue()) {
					case ResumoArrecadacao.RECEBIMENTOS_CLASSIFICADOS_SEQUENCIA_TIPO_LANCAMENTO_1:
						indiceTipoLancamento = 19;
						indicadorOperacao = 1;
						break;
					case ResumoArrecadacao.RECEBIMENTOS_CLASSIFICADOS_SEQUENCIA_TIPO_LANCAMENTO_2:
						indiceTipoLancamento = 19;
						indicadorOperacao = 2;
						break;
					case ResumoArrecadacao.RECEBIMENTOS_MESES_SEQUENCIA_TIPO_LANCAMENTO_1:
						indiceTipoLancamento = 22;
						indicadorOperacao = 1;
						break;
					case ResumoArrecadacao.RECEBIMENTOS_MESES_SEQUENCIA_TIPO_LANCAMENTO_2:
						indiceTipoLancamento = 22;
						indicadorOperacao = 2;
						break;
					case ResumoArrecadacao.IMPOSTOS_DEDUZIDOS_NA_ARRECADACAO_SEQUENCIA_TIPO_LANCAMENTO_1:	
					case ResumoArrecadacao.IMPOSTOS_DEDUZIDOS_NA_ARRECADACAO_SEQUENCIA_TIPO_LANCAMENTO_2:
					case ResumoArrecadacao.IMPOSTOS_DEDUZIDOS_NA_ARRECADACAO_SEQUENCIA_TIPO_LANCAMENTO_3:
					case ResumoArrecadacao.IMPOSTOS_DEDUZIDOS_NA_ARRECADACAO_SEQUENCIA_TIPO_LANCAMENTO_4:
						indiceTipoLancamento = 20;
						indicadorOperacao = 1;
						break;
					case ResumoArrecadacao.IMPOSTOS_MESES_SEQUENCIA_TIPO_LANCAMENTO_1:	
					case ResumoArrecadacao.IMPOSTOS_MESES_SEQUENCIA_TIPO_LANCAMENTO_2:
					case ResumoArrecadacao.IMPOSTOS_MESES_SEQUENCIA_TIPO_LANCAMENTO_3:
					case ResumoArrecadacao.IMPOSTOS_MESES_SEQUENCIA_TIPO_LANCAMENTO_4:
						indiceTipoLancamento = 23;
						indicadorOperacao = 1;
						break;
					case ResumoArrecadacao.RECEBIMENTOS_DESCONTOS_POR_CREDITO:
						indiceTipoLancamento = 21;
						indicadorOperacao = 1;
						break;
					default:
						continue;
				}
				if (tipoCategoria.intValue() == CategoriaTipo.PARTICULAR){
					if (valoresItem[indiceTipoLancamento][0] == null){ // PARTICULAR
						valoresItem[indiceTipoLancamento][0] = valorItem;
					} else {
						if (indicadorOperacao == 1){
							valoresItem[indiceTipoLancamento][0] = valoresItem[indiceTipoLancamento][0].add(valorItem);	
						} else {
							valoresItem[indiceTipoLancamento][0] = valoresItem[indiceTipoLancamento][0].subtract(valorItem);
						}
							
					}					 
				} else {
					if (valoresItem[indiceTipoLancamento][1] == null){ // PUBLICO
						valoresItem[indiceTipoLancamento][1] = valorItem;
					} else {
						if (indicadorOperacao == 1){
							valoresItem[indiceTipoLancamento][1] = valoresItem[indiceTipoLancamento][1].add(valorItem);
						} else {
							valoresItem[indiceTipoLancamento][1] = valoresItem[indiceTipoLancamento][1].subtract(valorItem);
						}
					}
				}								
			}
						
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	
		Collection<RelatorioEvolucaoContasAReceberContabilBean> dados = 
			new ArrayList<RelatorioEvolucaoContasAReceberContabilBean>();
	
		// Colocando em ordem alfabetica as descricoes dos grupos
		ArrayList chavesOrdenadas = new ArrayList(dadosAgrupados.keySet());
		Collections.sort(chavesOrdenadas);
		for (Iterator iter = chavesOrdenadas.iterator(); iter.hasNext();) {
			Integer chave = (Integer) iter.next();
			BigDecimal[][] valoresItem = dadosAgrupados.get(chave);
			
			// Calculando a linha 8 Faturamento Bruto do mes : SOMA(valor2:valor7)
			for (int ind = 1; ind < 7; ind++) {
				valoresItem[7][0] = valoresItem[7][0].add(valoresItem[ind][0]); // PARTICULAR
				valoresItem[7][1] = valoresItem[7][1].add(valoresItem[ind][1]); // PUBLICO
			}
			//	Calculando a linha 15 Faturamento Liquido do mes : Faturamento Bruto (valor8) - SOMA(valor9:valor14)
			valoresItem[14][0] = valoresItem[7][0];
			valoresItem[14][1] = valoresItem[7][1];
			for (int ind = 8; ind < 14; ind++) {
				valoresItem[14][0] = valoresItem[14][0].subtract(valoresItem[ind][0]); // PARTICULAR
				valoresItem[14][1] = valoresItem[14][1].subtract(valoresItem[ind][1]); // PUBLICO
			}
	
			// calculando a linha 26 - Saldo Atual: V1 + V12 + V16 + V17 + V21 + V24 - (V13, V14, V15. V22, V18, V19, V20)
			// PARTICULAR
			valoresItem[25][0] = valoresItem[0][0].add(valoresItem[14][0])
				.add(valoresItem[17][0]).subtract(valoresItem[15][0]).subtract(valoresItem[16][0])
				.subtract(valoresItem[20][0]).subtract(valoresItem[18][0])
				.subtract(valoresItem[19][0]).subtract(valoresItem[22][0]).subtract(valoresItem[23][0])
				.subtract(valoresItem[24][0]).add(valoresItem[21][0]);
			// PUBLICO
			valoresItem[25][1] = valoresItem[0][1].add(valoresItem[14][1])
				.add(valoresItem[17][1]).subtract(valoresItem[15][1]).subtract(valoresItem[16][1])
				.subtract(valoresItem[20][1]).subtract(valoresItem[18][1])
				.subtract(valoresItem[19][1]).subtract(valoresItem[22][1]).subtract(valoresItem[23][1])
				.subtract(valoresItem[24][1]).add(valoresItem[21][1]);
			
	
			for (int i=0; i < descricoes.length; i++) {							
				RelatorioEvolucaoContasAReceberContabilBean bean = new 
					RelatorioEvolucaoContasAReceberContabilBean((BigDecimal[]) valoresItem[i], descricoes[i]);
				
				if (opcaoTotalizacao.equals("estado")){
					bean.setTipoGrupo("TOTAL DO ESTADO");
				} else if (opcaoTotalizacao.equals("estadoGerencia")) {					
					bean.setTipoGrupo("Gerência Regional");					
				
				} else if (opcaoTotalizacao.equals("estadoLocalidade") 					
					|| opcaoTotalizacao.equals("gerenciaRegionalLocalidade")
					|| opcaoTotalizacao.equals("unidadeNegocioLocalidade")) {
					bean.setTipoGrupo("Localidade");					
				} else if (opcaoTotalizacao.equals("estadoUnidadeNegocio") 
						|| opcaoTotalizacao.equals("gerenciaRegionalUnidadeNegocio")) {
					bean.setTipoGrupo("Unidade de Negócio");					
				} else if (opcaoTotalizacao.equalsIgnoreCase("estadoMunicipio")){
					bean.setTipoGrupo("Município");
				}
				
				bean.setDescricaoGrupo(labelChaves.get(chave));				
				dados.add(bean);			
			}
			
		}		
	
		return dados;
	}
		
	public Collection consultarDadosRelatorioSaldoContasAReceberContabil(String opcaoTotalizacao,
			int mesAno, Integer gerencia, Integer unidadeNegocio, Integer localidade, Integer municipio) 
	throws ControladorException{
		Collection pesquisaDados = new ArrayList();
		HashMap<String, RelatorioSaldoContasAReceberContabilBean> dadosAgrupados = 
			new HashMap<String, RelatorioSaldoContasAReceberContabilBean>();
		int anoMes = Util.formatarMesAnoParaAnoMes(mesAno);
		try {
			pesquisaDados = repositorioFinanceiro.consultarDadosRelatorioSaldoContasAReceberContabil(
					opcaoTotalizacao, anoMes, gerencia, unidadeNegocio, localidade, municipio);
			
			String tipoGrupo = "";
			if(opcaoTotalizacao.equalsIgnoreCase("estadoGerencia")){
				tipoGrupo = "Gerência";
			} else if(opcaoTotalizacao.equalsIgnoreCase("estadoLocalidade") ||
					opcaoTotalizacao.equalsIgnoreCase("gerenciaRegionalLocalidade") ||
					opcaoTotalizacao.equalsIgnoreCase("unidadeNegocioLocalidade")){
				tipoGrupo = "Localidade";
			} else if(opcaoTotalizacao.equalsIgnoreCase("estadoUnidadeNegocio") ||
					opcaoTotalizacao.equalsIgnoreCase("gerenciaRegionalUnidadeNegocio")){
				tipoGrupo = "Unidade de Negócio";
			} else if(opcaoTotalizacao.equalsIgnoreCase("municipio") ||
					opcaoTotalizacao.equalsIgnoreCase("estadoMunicipio")){
				tipoGrupo = "Município";
			}
			
			// nomeItemGrupo, idItemGrupo, descricaoLancamentoTipo, descricaoLancamentoItem, numeroConta, soma(valor)
			Iterator iter = pesquisaDados.iterator();
			BigDecimal totalizadorSomaSemPerdaParticular = new BigDecimal("0");
			BigDecimal totalizadorSomaSemPerdaPublico = new BigDecimal("0");
			BigDecimal totalizadorSomaSemPerda = new BigDecimal("0");
			
			BigDecimal totalizadorSomaSemPerdaResidendial = new BigDecimal("0");
			BigDecimal totalizadorSomaSemPerdaComercial = new BigDecimal("0");
			BigDecimal totalizadorSomaSemPerdaIndustrial = new BigDecimal("0");
			
			//************************************************************
			//CRC???
			//Autor: Ivan Sergio
			//Solicitante: Eduardo
			//19/07/2010
			//Removido o item IMPOSTOS DEDUZIDOS e adicionando no final
			//do relatorio.
			//************************************************************
			String descImpostosDeduzidos = "";
			BigDecimal valorImpostosDeduzidosParticular = new BigDecimal(0);
			BigDecimal valorImpostosDeduzidosPublico = new BigDecimal(0);
			BigDecimal valorImpostosDeduzidosTotal = new BigDecimal(0);
			
			//************************************************************
			//CRC3294
			//Autor: Ivan Sergio
			//Data: 30/11/2010
			//************************************************************
			int indicadorFinCobCurtoPrazo = 0;
			int indicadorFinCobLongoPrazo = 0;
			RelatorioSaldoContasAReceberContabilBean beanFinCobCurtoPrazo = null;
			RelatorioSaldoContasAReceberContabilBean beanFinCobLongoPrazo = null;
			String chaveFinCobCurtoPrazo = "";
			String chaveFinCobLongoPrazo = "";
			Boolean primeiraVez = true;
			Integer idItemGrupoAnterior = null;
			
			
			while(iter.hasNext()){
				
				Object[] objetos = (Object[]) iter.next();
				
				Integer idGerenciaRegional = (Integer) objetos[0];
				String nomeGerenciaRegional = (String) objetos[1]; 
				Integer idUnidadeNegocio = (Integer) objetos[2];
				String nomeUnidadeNegocio = (String) objetos[3]; 
				String codigoCentroCusto = (String) objetos[4]; 
				String nomeItemGrupo = (String) objetos[5];
				Integer idItemGrupo = (Integer) objetos[6];
				Integer numSequenciaTipoLancamento = (Integer) objetos[7];
				Integer numSequenciaLancamentoItem = (Integer) objetos[8];
				String descricaoLancamentoTipo = (String) objetos[9];
				String descricaoLancamentoItem = (String) objetos[10];
				Integer numeroConta = (Integer) objetos[11];
				Integer tipoCategoria = (Integer) objetos[12];
				BigDecimal somaValor = (BigDecimal) objetos[13];
				String descricaoItemContabil = ( String ) objetos[14];
				Integer idLancamentoTipo = (Integer) objetos[15];
				Integer idCategoria = (Integer) objetos[16];
				
				if (primeiraVez){
					idItemGrupoAnterior = idItemGrupo;
					primeiraVez = false;
				}
				
				if (!idItemGrupoAnterior.equals(idItemGrupo)){
					
					idItemGrupoAnterior = idItemGrupo;
					totalizadorSomaSemPerdaParticular = new BigDecimal("0");
					totalizadorSomaSemPerdaPublico = new BigDecimal("0");
					totalizadorSomaSemPerda = new BigDecimal("0");
					
					totalizadorSomaSemPerdaResidendial = new BigDecimal("0");
					totalizadorSomaSemPerdaComercial = new BigDecimal("0");
					totalizadorSomaSemPerdaIndustrial = new BigDecimal("0");
				}
				
				//*****************************************************************************
				// Verifica se adiciona o subtotal para Financiamento Cobrar CURTO Prazo
				//*****************************************************************************
				if (numSequenciaTipoLancamento.equals(400) && numSequenciaLancamentoItem.equals(10) &&
						indicadorFinCobCurtoPrazo == 0) {
					
					indicadorFinCobCurtoPrazo = 1;
					beanFinCobCurtoPrazo = new RelatorioSaldoContasAReceberContabilBean(
							idGerenciaRegional, nomeGerenciaRegional, idUnidadeNegocio,
							nomeUnidadeNegocio, codigoCentroCusto, tipoGrupo, idItemGrupo,
							nomeItemGrupo, descricaoLancamentoTipo, descricaoLancamentoItem,
							"                            SUBTOTAL",
							totalizadorSomaSemPerdaParticular, totalizadorSomaSemPerdaPublico,
							totalizadorSomaSemPerda);
					
					beanFinCobCurtoPrazo.setSequenciaLancamentoTipo(400);
					beanFinCobCurtoPrazo.setSequenciaLancamentoItem(11); // Serve apenas para ordenacao
					beanFinCobCurtoPrazo.setIdLancamentoTipo(idLancamentoTipo);
					
					if (tipoCategoria.intValue() == CategoriaTipo.PARTICULAR.intValue()){
						beanFinCobCurtoPrazo.setValorItemParticular(somaValor); // PARTICULAR
						
						if (idCategoria.equals(Categoria.RESIDENCIAL)) {
							beanFinCobCurtoPrazo.setValorItemResidencial(somaValor);
						}else if (idCategoria.equals(Categoria.COMERCIAL)) {
							beanFinCobCurtoPrazo.setValorItemComercial(somaValor);
						}else if (idCategoria.equals(Categoria.INDUSTRIAL)) {
							beanFinCobCurtoPrazo.setValorItemIndustrial(somaValor);
						}
					}else {
						beanFinCobCurtoPrazo.setValorItemPublico(somaValor); // PUBLICO	
					}
					
				}else if (numSequenciaTipoLancamento.equals(400) && numSequenciaLancamentoItem.equals(10) &&
						indicadorFinCobCurtoPrazo == 1) {
					
					chaveFinCobCurtoPrazo = "z" + nomeItemGrupo + descricaoLancamentoTipo + descricaoLancamentoItem + descricaoItemContabil;
					
					if (tipoCategoria.intValue() == CategoriaTipo.PARTICULAR.intValue()){
						beanFinCobCurtoPrazo.setValorItemParticular(beanFinCobCurtoPrazo.getValorItemParticular().add(somaValor)); // PARTICULAR
						
						if (idCategoria.equals(Categoria.RESIDENCIAL)) {
							beanFinCobCurtoPrazo.setValorItemResidencial(
									beanFinCobCurtoPrazo.getValorItemResidencial().add(somaValor));
						}else if (idCategoria.equals(Categoria.COMERCIAL)) {
							beanFinCobCurtoPrazo.setValorItemComercial(
									beanFinCobCurtoPrazo.getValorItemComercial().add(somaValor));
						}else if (idCategoria.equals(Categoria.INDUSTRIAL)) {
							beanFinCobCurtoPrazo.setValorItemIndustrial(
									beanFinCobCurtoPrazo.getValorItemIndustrial().add(somaValor));
						}
					}else {
						beanFinCobCurtoPrazo.setValorItemPublico(
								beanFinCobCurtoPrazo.getValorItemPublico().add(somaValor)); // PUBLICO
					}
					
				}else if (indicadorFinCobCurtoPrazo == 1) {
					dadosAgrupados.put(chaveFinCobCurtoPrazo, beanFinCobCurtoPrazo);
					indicadorFinCobCurtoPrazo = 2;
				}
				//*****************************************************************************
				
				
				//*****************************************************************************
				// Verifica se adiciona o subtotal para Financiamento Cobrar LONGO Prazo
				//*****************************************************************************
				if (numSequenciaTipoLancamento.equals(400) && numSequenciaLancamentoItem.equals(20) &&
						indicadorFinCobLongoPrazo == 0) {
					
					indicadorFinCobLongoPrazo = 1;
					beanFinCobLongoPrazo = new RelatorioSaldoContasAReceberContabilBean(
							idGerenciaRegional, nomeGerenciaRegional, idUnidadeNegocio,
							nomeUnidadeNegocio, codigoCentroCusto, tipoGrupo, idItemGrupo,
							nomeItemGrupo, descricaoLancamentoTipo, descricaoLancamentoItem,
							"                            SUBTOTAL",
							totalizadorSomaSemPerdaParticular, totalizadorSomaSemPerdaPublico,
							totalizadorSomaSemPerda);
					
					beanFinCobLongoPrazo.setSequenciaLancamentoTipo(400);
					beanFinCobLongoPrazo.setSequenciaLancamentoItem(21); // Serve apenas para ordenacao
					
					if (tipoCategoria.intValue() == CategoriaTipo.PARTICULAR.intValue()){
						beanFinCobLongoPrazo.setValorItemParticular(somaValor); // PARTICULAR
						
						if (idCategoria.equals(Categoria.RESIDENCIAL)) {
							beanFinCobLongoPrazo.setValorItemResidencial(somaValor);
						}else if (idCategoria.equals(Categoria.COMERCIAL)) {
							beanFinCobLongoPrazo.setValorItemComercial(somaValor);
						}else if (idCategoria.equals(Categoria.INDUSTRIAL)) {
							beanFinCobLongoPrazo.setValorItemIndustrial(somaValor);
						}
					}else {
						beanFinCobLongoPrazo.setValorItemPublico(somaValor); // PUBLICO	
					}
					
				}else if (numSequenciaTipoLancamento.equals(400) && numSequenciaLancamentoItem.equals(20) &&
						indicadorFinCobLongoPrazo == 1) {
					
					chaveFinCobLongoPrazo = "zz" + nomeItemGrupo + descricaoLancamentoTipo + descricaoLancamentoItem + descricaoItemContabil;
					
					if (tipoCategoria.intValue() == CategoriaTipo.PARTICULAR.intValue()){
						beanFinCobLongoPrazo.setValorItemParticular(beanFinCobLongoPrazo.getValorItemParticular().add(somaValor)); // PARTICULAR
						
						if (idCategoria.equals(Categoria.RESIDENCIAL)) {
							beanFinCobLongoPrazo.setValorItemResidencial(
									beanFinCobLongoPrazo.getValorItemResidencial().add(somaValor));
						}else if (idCategoria.equals(Categoria.COMERCIAL)) {
							beanFinCobLongoPrazo.setValorItemComercial(
									beanFinCobLongoPrazo.getValorItemComercial().add(somaValor));
						}else if (idCategoria.equals(Categoria.INDUSTRIAL)) {
							beanFinCobLongoPrazo.setValorItemIndustrial(
									beanFinCobLongoPrazo.getValorItemIndustrial().add(somaValor));
						}
					}else {
						beanFinCobLongoPrazo.setValorItemPublico(
								beanFinCobLongoPrazo.getValorItemPublico().add(somaValor)); // PUBLICO
					}
					
				}else if (indicadorFinCobLongoPrazo == 1) {
					dadosAgrupados.put(chaveFinCobLongoPrazo, beanFinCobLongoPrazo);
					indicadorFinCobLongoPrazo = 2;
				}
				//*****************************************************************************
				
				
				String chave = nomeItemGrupo + descricaoLancamentoTipo + descricaoLancamentoItem + descricaoItemContabil;
				
				RelatorioSaldoContasAReceberContabilBean bean = dadosAgrupados.get(chave);
				
				if (bean == null){
					bean = new RelatorioSaldoContasAReceberContabilBean
					(idGerenciaRegional, nomeGerenciaRegional, idUnidadeNegocio, nomeUnidadeNegocio,
							codigoCentroCusto, tipoGrupo, idItemGrupo, nomeItemGrupo, descricaoLancamentoTipo, 
							descricaoLancamentoItem, descricaoItemContabil, totalizadorSomaSemPerdaParticular, 
							totalizadorSomaSemPerdaPublico, totalizadorSomaSemPerda);
					dadosAgrupados.put(chave, bean);
					bean.setSequenciaLancamentoTipo(numSequenciaTipoLancamento);
					bean.setSequenciaLancamentoItem(numSequenciaLancamentoItem);
				} 
				
				if (numSequenciaTipoLancamento.equals(310)) {
					descImpostosDeduzidos = descricaoLancamentoItem;
					if (tipoCategoria.intValue() == CategoriaTipo.PARTICULAR.intValue()) {
						valorImpostosDeduzidosParticular = somaValor;
					}
					if (tipoCategoria.intValue() == CategoriaTipo.PUBLICO.intValue()) {
						valorImpostosDeduzidosPublico = somaValor;
					}
					valorImpostosDeduzidosTotal = valorImpostosDeduzidosTotal.add(somaValor);
				}
				bean.setDescImpostosDeduzidos(descImpostosDeduzidos);
				bean.setValorImpostosDeduzidosParticular(valorImpostosDeduzidosParticular);
				bean.setValorImpostosDeduzidosPublico(valorImpostosDeduzidosPublico);
				bean.setValorImpostosDeduzidosTotal(valorImpostosDeduzidosTotal);
				bean.setIdLancamentoTipo(idLancamentoTipo);
				
				if (tipoCategoria.intValue() == CategoriaTipo.PARTICULAR.intValue()){
					bean.setValorItemParticular(bean.getValorItemParticular().add(somaValor)); // PARTICULAR
					
					if (idCategoria.equals(Categoria.RESIDENCIAL)) {
						bean.setValorItemResidencial(somaValor);
					}else if (idCategoria.equals(Categoria.COMERCIAL)) {
						bean.setValorItemComercial(somaValor);
					}else if (idCategoria.equals(Categoria.INDUSTRIAL)) {
						bean.setValorItemIndustrial(somaValor);
					}
					
					bean.setCodigoContabilParticular(numeroConta);
					
					//Total geral em perdas
					if (idLancamentoTipo.intValue() != LancamentoTipo.VALORES_CONTABILIZADOS_COMO_PERDAS.intValue()
							&& idLancamentoTipo.intValue() != LancamentoTipo.RECEBIMENTOS_NAO_IDENTIFICADOS.intValue() ){
						
						if (!numSequenciaTipoLancamento.equals(310)) {
							totalizadorSomaSemPerdaParticular = totalizadorSomaSemPerdaParticular.add(somaValor);
							
							if (idCategoria.equals(Categoria.RESIDENCIAL)) {
								totalizadorSomaSemPerdaResidendial = 
									totalizadorSomaSemPerdaResidendial.add(somaValor);
							}else if (idCategoria.equals(Categoria.COMERCIAL)) {
								totalizadorSomaSemPerdaComercial =
									totalizadorSomaSemPerdaComercial.add(somaValor);
							}else if (idCategoria.equals(Categoria.INDUSTRIAL)) {
								totalizadorSomaSemPerdaIndustrial =
									totalizadorSomaSemPerdaIndustrial.add(somaValor);
							}
						}
						bean.setTotalGeralSemPerdasParticular(totalizadorSomaSemPerdaParticular);
						totalizadorSomaSemPerda = totalizadorSomaSemPerdaParticular.add(totalizadorSomaSemPerdaPublico);
						bean.setTotalGeralSemPerdas(totalizadorSomaSemPerda);
						
						bean.setTotalGeralSemPerdasResidencial(totalizadorSomaSemPerdaResidendial);
						bean.setTotalGeralSemPerdasComercial(totalizadorSomaSemPerdaComercial);
						bean.setTotalGeralSemPerdasIndustrial(totalizadorSomaSemPerdaIndustrial);
					}
					
				} else {
					bean.setValorItemPublico(somaValor); // PUBLICO					
					bean.setCodigoContabilPublico(numeroConta);
					
					//Total geral em perdas
					if (idLancamentoTipo.intValue() != LancamentoTipo.VALORES_CONTABILIZADOS_COMO_PERDAS.intValue()
							&& idLancamentoTipo.intValue() != LancamentoTipo.RECEBIMENTOS_NAO_IDENTIFICADOS.intValue() ){
						
						if (!numSequenciaTipoLancamento.equals(310)) {
							totalizadorSomaSemPerdaPublico = totalizadorSomaSemPerdaPublico.add(somaValor);
						}
						bean.setTotalGeralSemPerdasPublico(totalizadorSomaSemPerdaPublico);
						totalizadorSomaSemPerda = totalizadorSomaSemPerdaParticular.add(totalizadorSomaSemPerdaPublico);
						bean.setTotalGeralSemPerdas(totalizadorSomaSemPerda);
					}
				}
			} 
		}catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
		return new ArrayList(dadosAgrupados.values());
	}
	
    /**
     * [UC0799] - Gerar Txt das Contas Baixadas Contabilmente
     * 
     * @author: Vivianne Sousa
     * @date: 09/04/2008 
     */
    public void gerarTXTContasBaixadasContabilmente(
            Map parametros, Integer idSetorComercial, Integer idFuncionalidadeIniciada, Integer faixa)
            throws ControladorException{
        
        int idUnidadeIniciada = 0;

        
        try {
        /*
         * Registrar o início do processamento da Unidade de
         * Processamento do Batch
        */
                    
            idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,UnidadeProcessamento.SETOR_COMERCIAL,(idSetorComercial));

            Integer referenciaInicial = (Integer)parametros.get("referenciaInicial");
            Integer referenciaFinal = (Integer)parametros.get("referenciaFinal");
            Short tipo = (Short)parametros.get("tipo");
            Short periodicidade = (Short)parametros.get("periodicidade");
            
            Map contasBaixadasContabilmenteMap = null;
            StringBuilder contasBaixadasContabilmenteTXT = new StringBuilder();
            
            String nomeZip = "";    
            String nomeDiretorio = "";
            if(periodicidade.equals(ConstantesSistema.ACUMULADO)){
                nomeDiretorio = "ContasBContabilAcumulado" + referenciaFinal;
                
            }else{
                
                if(referenciaInicial.equals(referenciaFinal)){
                    nomeDiretorio = "ContasBContabilMensal"+referenciaInicial;
                }else{
                    nomeDiretorio = "ContasBContabilMensal"+referenciaInicial + "A" + referenciaFinal;
                }    
            }
            IoUtil.criarDiretorio(nomeDiretorio);
            
            if(tipo.equals(ConstantesSistema.ANALITICO)){
                
                Collection colecaoQuadras = getControladorLocalidade().pesquisarIdQuadraPorSetorComercial(idSetorComercial);
                Iterator iterator = colecaoQuadras.iterator();

                while (iterator.hasNext()) {

                    Integer idQuadra = (Integer) iterator.next();
                   
                    contasBaixadasContabilmenteMap = this
                                .consultarDadosContasBaixadasContabilmenteTXT(
                                        referenciaInicial, referenciaFinal, idQuadra, faixa, periodicidade);
                        
                    StringBuilder txtContasBaixadasContabilmentePorQuadra =  (StringBuilder)contasBaixadasContabilmenteMap.get("linhasTXT");
                    if (txtContasBaixadasContabilmentePorQuadra != null){
                        contasBaixadasContabilmenteTXT.append(txtContasBaixadasContabilmentePorQuadra);
                    }
                }
                
                nomeZip = "CONTAS_BAIXADAS_CONTABILMENTE_"
                    + Util.adicionarZerosEsquedaNumero(3,"" + idSetorComercial)  + "_F" + faixa ;
                
            }else if (tipo.equals(ConstantesSistema.SINTETICO)){
                
                contasBaixadasContabilmenteTXT.append(this.
                    consultarSomatorioValorContasBaixadasContabilmenteTXT(
                            referenciaInicial, referenciaFinal, periodicidade));
                
                nomeZip = "CONTAS_BAIXADAS_CONTABILMENTE_SINTETICO"  ;
                
            }
            
            
            if (contasBaixadasContabilmenteTXT != null
                    && contasBaixadasContabilmenteTXT.length() != 0) {

                BufferedWriter out = null;
                File arquivoTxt = new File(nomeDiretorio + "/" + nomeZip + ".txt");
                
                System.out.println("CRIANDO ZIP -- " + idSetorComercial);
                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivoTxt.getAbsolutePath())));
                out.write(contasBaixadasContabilmenteTXT.toString());
                out.flush();
                out.close();
                
                out = null;
                arquivoTxt = null;
                contasBaixadasContabilmenteTXT = null;
                
            }
            
            
            getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);
        
        } catch (Exception ex) {
            ex.printStackTrace();
            getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,idUnidadeIniciada, true);
            throw new EJBException(ex);
        }
        
        
    }
    
    
    /**
     * [UC0799] - Gerar Txt das Contas Baixadas Contabilmente
     * 
     * @author: Vivianne Sousa
     * @param quantidadeMaxima 
     * @param indice 
     * @date: 09/04/2008 
     */
    public Map
            consultarDadosContasBaixadasContabilmenteTXT(
                Integer referenciaInicio, Integer referenciaFinal,
                Integer idQuadra, Integer faixa,Short periodicidade)throws ControladorException{
        
        StringBuilder contasBaixadasContabilmenteTXT = null;
        Collection colecaoDadosContasBaixadasContabilmente = null;
        
        Map retorno = new HashMap();
        
        try {
            
            if (faixa.equals(ConstantesSistema.FAIXA_1)){
                
                colecaoDadosContasBaixadasContabilmente = repositorioFinanceiro
                .consultarDadosContasBaixadasContabilmentePorQuadraFaixa1(
                        referenciaInicio, referenciaFinal, idQuadra, periodicidade);
                
            }else if (faixa.equals(ConstantesSistema.FAIXA_2)){
                
                colecaoDadosContasBaixadasContabilmente = repositorioFinanceiro
                .consultarDadosContasBaixadasContabilmentePorQuadraFaixa2(
                        referenciaInicio, referenciaFinal, idQuadra, periodicidade);
                
            }else if (faixa.equals(ConstantesSistema.FAIXA_3)){
                
                colecaoDadosContasBaixadasContabilmente = repositorioFinanceiro
                .consultarDadosContasBaixadasContabilmentePorQuadraFaixa3(
                        referenciaInicio, referenciaFinal, idQuadra,periodicidade);
            }

        if (colecaoDadosContasBaixadasContabilmente != null 
                && !colecaoDadosContasBaixadasContabilmente.isEmpty()) {

            Iterator iteratorColecaoDadosContasBaixadasContabilmente = colecaoDadosContasBaixadasContabilmente.iterator();
            contasBaixadasContabilmenteTXT = new StringBuilder();
            
            StringBuilder contaTxt = null;
            Imovel imovel = null;
            Localidade localidade = null;
            SetorComercial setorComercial = null;
            Quadra quadra = null;
            
            while (iteratorColecaoDadosContasBaixadasContabilmente.hasNext()) {

                Object[] arrayDados = (Object[]) 
                    iteratorColecaoDadosContasBaixadasContabilmente.next();

                contaTxt = new StringBuilder();
                imovel = new Imovel();
                
                //referencia Baixa Contabil
                String referenciaBaixaContabil = "";
                if (arrayDados[0] != null) {
                    referenciaBaixaContabil = Util.formatarAnoMesParaMesAno((Integer) arrayDados[0]);
                }
                
                //matricula e endereço do imovel 
                String matriculaImovel = "";
                String endereco = "";
                if (arrayDados[1] != null) {
                    matriculaImovel = ((Integer) arrayDados[1]).toString();
                    
                    endereco = getControladorEndereco().pesquisarEndereco((Integer) arrayDados[1]);
                }
                
                //referencia da fatura
                String referenciaFatura = "";
                if (arrayDados[2] != null) {
                    referenciaFatura = Util.formatarAnoMesParaMesAno((Integer) arrayDados[2]);
                }
                
                //id Localidade
                localidade = new Localidade();
                if (arrayDados[3] != null) {
                    localidade.setId((Integer) arrayDados[3]);
                    imovel.setLocalidade(localidade);
                }
                
                //codigo do setor comercial
                setorComercial = new SetorComercial();
                if (arrayDados[4] != null) {
                    setorComercial.setId((Integer) arrayDados[4]);
                    imovel.setSetorComercial(setorComercial);
                }
                
                //numero da quadra
                if (arrayDados[5] != null) {
                    quadra = new Quadra();
                    quadra.setNumeroQuadra((Integer) arrayDados[5]);
                    imovel.setQuadra(quadra);
                }
                
                //lote
                if (arrayDados[6] != null) {
                    imovel.setLote((Short) arrayDados[6]);
                }
                
                //sublote
                if (arrayDados[7] != null) {
                    imovel.setLote((Short) arrayDados[7]);
                }
                
                //valor da conta
                String valorContaFormatado = "";
                if (arrayDados[8] != null) {
                    BigDecimal valorConta = (BigDecimal) arrayDados[8];
                    valorContaFormatado = Util.formatarMoedaReal(valorConta);
                }
                
                //nome do usuario
                String nomeUsuario = "";
                if (arrayDados[9] != null) {
                    nomeUsuario = (String) arrayDados[9];
                }
                
                //situação de ligacao agua
                String situacaoAgua = "";
                if (arrayDados[10] != null) {
                    situacaoAgua = (String) arrayDados[10];
                }
                
                //situação de ligação esgoto
                String situacaoEsgoto = "";
                if (arrayDados[11] != null) {
                    situacaoEsgoto = (String) arrayDados[11];
                }
                
                contaTxt.append(Util.completaString(referenciaBaixaContabil,10));
                contaTxt.append(Util.completaString(matriculaImovel,12));
                contaTxt.append(Util.completaString(imovel.getInscricaoFormatada(),23));
                contaTxt.append(Util.completaString(nomeUsuario,53));
                contaTxt.append(Util.completaString(endereco,53));
                contaTxt.append(Util.completaString(situacaoAgua,23));
                contaTxt.append(Util.completaString(situacaoEsgoto,23));
                contaTxt.append(Util.completaString(referenciaFatura,10));
                contaTxt.append(Util.completaStringComEspacoAEsquerda(valorContaFormatado,15));
                
                contasBaixadasContabilmenteTXT.append(contaTxt.toString());
               
                contaTxt = null;
                contasBaixadasContabilmenteTXT.append(System.getProperty("line.separator"));
                
                iteratorColecaoDadosContasBaixadasContabilmente.remove();
            }
        }
        
        colecaoDadosContasBaixadasContabilmente = null;
        retorno.put("linhasTXT",contasBaixadasContabilmenteTXT);
        
        return retorno;
        
        } catch (ErroRepositorioException ex) {
            throw new ControladorException("erro.sistema", ex);
      }

    }
    

    /**
     * [UC0799] - Gerar Txt das Contas Baixadas Contabilmente
     * 
     * @author: Vivianne Sousa
     * @date: 20/05/2008 
     */
    public Map
            consultarSomatorioValorContasBaixadasContabilmenteFaixaTXT(
                Integer referenciaInicio, Integer referenciaFinal,Integer faixa,Short periodicidade)throws ControladorException{
        
        StringBuilder somatorioValorContasBaixadasContabilmenteTXT = null;
        Collection colecaoSomatorioValorContasBaixadasContabilmente = null;
        String mensagemFaixa = "";
        
        Map retorno = new HashMap();
        
        try {
        	
        	BigDecimal valorDoacoes = null;
            
            if (faixa.equals(ConstantesSistema.FAIXA_1)){
                
                colecaoSomatorioValorContasBaixadasContabilmente = repositorioFinanceiro
                .consultarSomatorioValorContasBaixadasContabilmenteFaixa1(
                        referenciaInicio, referenciaFinal, periodicidade);
                
                mensagemFaixa = ConstantesSistema.MENSAGEM_FAIXA_1;
                
                System.out.println("FAIXA_1");
                
                valorDoacoes = consultarSomatorioValorDoacoesContasBaixadasContabilmente(
						referenciaInicio, referenciaFinal, periodicidade);
                
            }else if (faixa.equals(ConstantesSistema.FAIXA_2)){
                
                colecaoSomatorioValorContasBaixadasContabilmente = repositorioFinanceiro
                .consultarSomatorioValorContasBaixadasContabilmenteFaixa2(
                        referenciaInicio, referenciaFinal, periodicidade);
                
                mensagemFaixa = ConstantesSistema.MENSAGEM_FAIXA_2;
                
                System.out.println("FAIXA_2");
            }else if (faixa.equals(ConstantesSistema.FAIXA_3)){
                
                colecaoSomatorioValorContasBaixadasContabilmente = repositorioFinanceiro
                .consultarSomatorioValorContasBaixadasContabilmenteFaixa3(
                        referenciaInicio, referenciaFinal, periodicidade);
                
                mensagemFaixa = ConstantesSistema.MENSAGEM_FAIXA_3;
                System.out.println("FAIXA_3");
            }
            
        BigDecimal valorAcumulado = BigDecimal.ZERO;
        valorAcumulado = valorAcumulado.setScale(2, BigDecimal.ROUND_HALF_DOWN);
        
        if (colecaoSomatorioValorContasBaixadasContabilmente != null 
                && !colecaoSomatorioValorContasBaixadasContabilmente.isEmpty()) {

            Iterator iteratorColecaoSomatorioValorContasBaixadasContabilmente = colecaoSomatorioValorContasBaixadasContabilmente.iterator();
            somatorioValorContasBaixadasContabilmenteTXT = new StringBuilder();
            
            while (iteratorColecaoSomatorioValorContasBaixadasContabilmente.hasNext()) {

            	Object valorObject = iteratorColecaoSomatorioValorContasBaixadasContabilmente.next();
            	
            	if (valorObject != null){
            		 BigDecimal valor = (BigDecimal) valorObject;
            		 valorAcumulado = valorAcumulado.add(valor);
            	}
            	
            }
            
            if (valorDoacoes != null) {
            	valorAcumulado = valorAcumulado.subtract(valorDoacoes);
            }
            
            //valor da conta
            String valorFormatado = "";
            valorFormatado = Util.formatarMoedaReal(valorAcumulado);
            
            somatorioValorContasBaixadasContabilmenteTXT.append(Util.completaString(mensagemFaixa,50));
            somatorioValorContasBaixadasContabilmenteTXT.append(Util.completaStringComEspacoAEsquerda(valorFormatado,15));
            
            somatorioValorContasBaixadasContabilmenteTXT.append(System.getProperty("line.separator"));
           
        }
        
        colecaoSomatorioValorContasBaixadasContabilmente = null;
        
        retorno.put("linhasTXT",somatorioValorContasBaixadasContabilmenteTXT);
        retorno.put("somatorio",valorAcumulado);
        
        return retorno;
        
        } catch (ErroRepositorioException ex) {
            throw new ControladorException("erro.sistema", ex);
      }

    }

    
    
    /**
     * [UC0799] - Gerar Txt das Contas Baixadas Contabilmente
     * 
     * @author: Vivianne Sousa
     * @date: 26/05/2008 
     */
    public StringBuilder
            consultarSomatorioValorContasBaixadasContabilmenteTXT(
                Integer referenciaInicial, Integer referenciaFinal,Short periodicidade)throws ControladorException{
        
        StringBuilder somatorioValorContasBaixadasContabilmenteTXT = new StringBuilder();
        Map contasBaixadasContabilmenteMap = null;
        
        StringBuilder txtSomatorioValorContasBaixadasContabilmente = null;
        BigDecimal somatorioValor = BigDecimal.ZERO;
        BigDecimal somatorioValorTotal = BigDecimal.ZERO;
        
        String cabecalho = "RELAÇÃO DAS CONTAS BAIXADAS CONTABILMENTE EM " 
            + Util.formatarAnoMesParaMesAno(referenciaFinal);
        somatorioValorContasBaixadasContabilmenteTXT.append(Util.completaStringComEspacoAEsquerda("",10));
        somatorioValorContasBaixadasContabilmenteTXT.append(cabecalho);
        somatorioValorContasBaixadasContabilmenteTXT.append(System.getProperty("line.separator"));
        
        
        contasBaixadasContabilmenteMap = this.consultarSomatorioValorContasBaixadasContabilmenteFaixaTXT(
                referenciaInicial, referenciaFinal, ConstantesSistema.FAIXA_1, periodicidade);
        
        txtSomatorioValorContasBaixadasContabilmente = 
            (StringBuilder)contasBaixadasContabilmenteMap.get("linhasTXT");
        if (txtSomatorioValorContasBaixadasContabilmente != null){
            somatorioValorContasBaixadasContabilmenteTXT.append(txtSomatorioValorContasBaixadasContabilmente);
            somatorioValor = (BigDecimal)contasBaixadasContabilmenteMap.get("somatorio");
            somatorioValorTotal = somatorioValor;
        }
        
        
        contasBaixadasContabilmenteMap = null;
        contasBaixadasContabilmenteMap = this.consultarSomatorioValorContasBaixadasContabilmenteFaixaTXT(
                referenciaInicial, referenciaFinal, ConstantesSistema.FAIXA_2, periodicidade);

        txtSomatorioValorContasBaixadasContabilmente = 
            (StringBuilder)contasBaixadasContabilmenteMap.get("linhasTXT");
        if (txtSomatorioValorContasBaixadasContabilmente != null){
            somatorioValorContasBaixadasContabilmenteTXT.append(txtSomatorioValorContasBaixadasContabilmente);
            somatorioValor = (BigDecimal)contasBaixadasContabilmenteMap.get("somatorio");
            somatorioValorTotal = somatorioValorTotal.add(somatorioValor);
        }
        
        
        contasBaixadasContabilmenteMap = null;
        contasBaixadasContabilmenteMap = this.consultarSomatorioValorContasBaixadasContabilmenteFaixaTXT(
                referenciaInicial, referenciaFinal, ConstantesSistema.FAIXA_3, periodicidade);

        txtSomatorioValorContasBaixadasContabilmente = 
            (StringBuilder)contasBaixadasContabilmenteMap.get("linhasTXT");
        if (txtSomatorioValorContasBaixadasContabilmente != null){
            somatorioValorContasBaixadasContabilmenteTXT.append(txtSomatorioValorContasBaixadasContabilmente);
            somatorioValor = (BigDecimal)contasBaixadasContabilmenteMap.get("somatorio");
            somatorioValorTotal = somatorioValorTotal.add(somatorioValor);
        }
        
        
        somatorioValorContasBaixadasContabilmenteTXT.append(System.getProperty("line.separator"));
        somatorioValorContasBaixadasContabilmenteTXT.append(Util.completaString("VALOR TOTAL",50));
        somatorioValorContasBaixadasContabilmenteTXT.append(Util.completaStringComEspacoAEsquerda(
                Util.formatarMoedaReal(somatorioValorTotal),15));
        
        return somatorioValorContasBaixadasContabilmenteTXT;
        
       
    }
    
	/**
	 * [UC0824] Gerar Relatório dos Parâmetros Contábeis
	 * 
	 * @author Bruno Barros
	 * @date 08/07/2008
	 * 
	 * @return Collection<RelatorioParametrosContabeisFaturamentoBean>
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioParametrosContabeisFaturamentoBean> 
		pesquisarDadosRelatorioParametrosContabeisFaturamento( String referenciaContabil ) throws ControladorException {
		
		Collection pesquisaDados = new ArrayList();
		Integer anoMes = null;
		
		// Verificamos se o ano mes de referencia foi informado
		if ( referenciaContabil != null && !referenciaContabil.equals( "" )){
			anoMes = Integer.parseInt( Util.formatarMesAnoParaAnoMesSemBarra( referenciaContabil ) );
		}
		
		Collection<RelatorioParametrosContabeisFaturamentoBean> colRetorno = new ArrayList();		
		
		try {
			pesquisaDados = repositorioFinanceiro.pesquisarDadosRelatorioParametrosContabeisFaturamento( anoMes );			
			
			// Montamos os dados
			Iterator iter = pesquisaDados.iterator();
			
			while(iter.hasNext()){
				Object[] objetos = (Object[]) iter.next();
				
				RelatorioParametrosContabeisFaturamentoBean bean = new RelatorioParametrosContabeisFaturamentoBean(
						(String) objetos[0], // Descricao do tipo de lancamento
						(String) objetos[1], // Descricao do Item de lancamento
						(String) objetos[2], // Descricao do Item de lancamento contabil
						(String) objetos[3], // Descricao da categoria
						(String) objetos[4], // Numero da conta para debito
						(String) objetos[5], // Numero da conta para credito
						( objetos.length > 6 ? (BigDecimal) objetos[6] : null ) // So informamos o valor caso esse tenha sido calculado  				
				);
                
				colRetorno.add( bean );
			}
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
		
		return colRetorno;		
	}
    
    /**
     * [UC0824] Gerar Relatório dos Parâmetros Contábeis
     * 
     * @author Bruno Barros
     * @date 08/07/2008
     * 
     * @return Collection<RelatorioParametrosContabeisArrecadacaoBean>
     * @throws ErroRepositorioException
     */
    public Collection<RelatorioParametrosContabeisArrecadacaoBean> 
        pesquisarDadosRelatorioParametrosContabeisArrecadacao( String referenciaContabil ) throws ControladorException {
        
        Collection pesquisaDados = new ArrayList();
        Integer anoMes = null;
        
        // Verificamos se o ano mes de referencia foi informado
        if ( referenciaContabil != null && !referenciaContabil.equals( "" )){
            anoMes = Integer.parseInt( Util.formatarMesAnoParaAnoMesSemBarra( referenciaContabil ) );
        }
        
        Collection<RelatorioParametrosContabeisArrecadacaoBean> colRetorno = new ArrayList();       
        
        try {
            pesquisaDados = repositorioFinanceiro.pesquisarDadosRelatorioParametrosContabeisArrecadacao( anoMes );          
            
            // Montamos os dados
            Iterator iter = pesquisaDados.iterator();
            
            while(iter.hasNext()){
                Object[] objetos = (Object[]) iter.next();
                
                RelatorioParametrosContabeisArrecadacaoBean bean = new RelatorioParametrosContabeisArrecadacaoBean(
                        (String) objetos[0], // Descricao do tipo de recebimento
                        (String) objetos[1], // Descricao do tipo de lancamento
                        (String) objetos[2], // Descricao do Item de lancamento
                        (String) objetos[3], // Descricao do Item de lancamento contabil
                        (String) objetos[4], // Descricao da categoria
                        (String) objetos[5], // Numero da conta para crédito
                        (String) objetos[6], // Numero da conta para debito
                        ( objetos.length > 7 ? (BigDecimal) objetos[7] : null ) // So informamos o valor caso esse tenha sido calculado                 
                );
                
                colRetorno.add( bean );
            }
        } catch (ErroRepositorioException e) {
            sessionContext.setRollbackOnly();
            throw new ControladorException("erro.sistema", e);
        }
        
        return colRetorno;      
    }
    

	/**
 	 * [UC0822] Gerar Relatório do Valor Referente a Volumes Consumidos e Não Faturados
 	 * 
 	 * @author Victor Cisneiros
 	 * @date 15/07/2008
 	 */
     public List<RelatorioVolumesConsumidosNaoFaturadosBean> pesquisarVolumesConsumidosNaoFaturados(
     		Integer mesAno, String opcaoTotalizacao, Integer idEntidade) throws ControladorException { 
    	 try {
    		 return repositorioFinanceiro.pesquisarVolumesConsumidosNaoFaturados(mesAno, opcaoTotalizacao, idEntidade);
    	 } catch (ErroRepositorioException e) {
    		 throw new ControladorException(e.getMessage(), e);
    	 }
     }
     
     /**
 	 * [UC0714] Gerar Contas a Receber Contábil
 	 * Adiciona os Valores Contabilizados como Perdas  
 	 * @author Vivianne Sousa
 	 * @date 14/08/2009
 	 */
     private void adicionarContaAReceberContabilValoresContabilizadosComoPerdas(
             Integer idLocalidade, int anoMesAnteriorFaturamento,
             Collection colecaoContasAReceberContabil)
             throws ErroRepositorioException {
    	 
    	 Map<Integer, BigDecimal> mapValoresContabilizadosComoPerdas = null;
    	 Integer gerenciaRegionalId = null;
         Integer unidadeNegocioId = null;
         Integer localidadeId = null;
    	 
    	 Collection colecaoDadosContaAguaEsgoto = repositorioFinanceiro
    	 	.pesquisarDadosContasCategoriaValorAguaEsgoto(idLocalidade);

		 if (colecaoDadosContaAguaEsgoto != null && !colecaoDadosContaAguaEsgoto.isEmpty()) {
			 //Acumula CTCG_VLAGUA a CTCG_VLESGOTO da tabela CONTA_CATEGORIA e 
			 //da tabela CONTA com CNTA_AMREFERENCIABAIXACONTABIL diferente de nulo
			 
			 mapValoresContabilizadosComoPerdas = new HashMap();
		     Iterator colecaoDadosContaAguaEsgotoIterator = colecaoDadosContaAguaEsgoto.iterator();
		     while (colecaoDadosContaAguaEsgotoIterator.hasNext()) {
		         Object[] dadosContaAguaEsgoto = (Object[]) colecaoDadosContaAguaEsgotoIterator.next();
		    	 
		         if(gerenciaRegionalId == null){
		        	 gerenciaRegionalId = (Integer) dadosContaAguaEsgoto[0];
		        	 unidadeNegocioId = (Integer) dadosContaAguaEsgoto[1];
		        	 localidadeId = (Integer) dadosContaAguaEsgoto[2];
		         }
		         
                 Integer idCategoriaConta = (Integer) dadosContaAguaEsgoto[3];
                 
                 BigDecimal valorAgua = new BigDecimal("0.00");
                 if (dadosContaAguaEsgoto[4] != null) {
                	 valorAgua = (BigDecimal) dadosContaAguaEsgoto[4]; 
                 }
                 
                 BigDecimal valorEsgoto = new BigDecimal("0.00");
                 if (dadosContaAguaEsgoto[5] != null) {
                	 valorEsgoto = (BigDecimal) dadosContaAguaEsgoto[5];
                 }
                 
                 BigDecimal valorCategoria = valorAgua.add(valorEsgoto);
                 
                 if (!mapValoresContabilizadosComoPerdas.containsKey(idCategoriaConta)) {
                	 mapValoresContabilizadosComoPerdas.put(idCategoriaConta,BigDecimal.ZERO);
				 }

                 mapValoresContabilizadosComoPerdas.put(idCategoriaConta,
                		 mapValoresContabilizadosComoPerdas.get(idCategoriaConta).add(valorCategoria));
                 
		     }
		 }    	 
    	 
		 
		 Collection colecaoDadosDebitoCobrado = repositorioFinanceiro
 	 	.pesquisarDadosDebitosCobradosCategoria(idLocalidade);

		 if (colecaoDadosDebitoCobrado != null && !colecaoDadosDebitoCobrado.isEmpty()) {
			 //Acumula DCCG_VLCATEGORIA da tabela DEBITO_COBRADO_CATEGORIA 
			 //com DBCB_ID=DBCB_ID da tabela DEBITO_COBRADO e 
			 //CNTA_ID=CNTA_ID da tabela CONTA com CNTA_AMREFERENCIABAIXACONTABIL diferente de nulo	 
			 
		     Iterator colecaoDadosDebitoCobradoIterator = colecaoDadosDebitoCobrado.iterator();
		     while (colecaoDadosDebitoCobradoIterator.hasNext()) {
		         Object[] dadosDebitoCobrado = (Object[]) colecaoDadosDebitoCobradoIterator.next();
		    	 
		         if(gerenciaRegionalId == null){
		        	 gerenciaRegionalId = (Integer) dadosDebitoCobrado[0];
		        	 unidadeNegocioId = (Integer) dadosDebitoCobrado[1];
		        	 localidadeId = (Integer) dadosDebitoCobrado[2];
		         }
		         
		         Integer idCategoriaConta = (Integer) dadosDebitoCobrado[3];
		         
		         BigDecimal valorCategoria = new BigDecimal("0.00");
                 if (dadosDebitoCobrado[4] != null) {
                	 valorCategoria = (BigDecimal)dadosDebitoCobrado[4];
                 }
                 
                 if (!mapValoresContabilizadosComoPerdas.containsKey(idCategoriaConta)) {
                	 mapValoresContabilizadosComoPerdas.put(idCategoriaConta,BigDecimal.ZERO);
				 }

                 mapValoresContabilizadosComoPerdas.put(idCategoriaConta,
                		 mapValoresContabilizadosComoPerdas.get(idCategoriaConta).add(valorCategoria));
                 

		     }
		 }    	 
		 
		 Collection colecaoDadosCreditoRealizado = repositorioFinanceiro
	 	 	.pesquisarDadosCreditosRealizadosCategoria(idLocalidade);

		 if (colecaoDadosCreditoRealizado != null && !colecaoDadosCreditoRealizado.isEmpty()) {
			 //Acumula negativamente CRCG_VLCATEGORIA da tabela CREDITO_REALIZADO_CATEGORIA
			 //com CRRZ_ID=CRRZ_ID da tabela CREDITO_REALIZADO e 
			 //CNTA_ID=CNTA_ID da tabela CONTA com CNTA_AMREFERENCIABAIXACONTABIL diferente de nulo	 
			 
		     Iterator colecaoDadosCreditoRealizadoIterator = colecaoDadosCreditoRealizado.iterator();
		     while (colecaoDadosCreditoRealizadoIterator.hasNext()) {
		         Object[] dadosCreditoRealizado = (Object[]) colecaoDadosCreditoRealizadoIterator.next();
		    	 
		         if(gerenciaRegionalId == null){
		        	 gerenciaRegionalId = (Integer) dadosCreditoRealizado[0];
		        	 unidadeNegocioId = (Integer) dadosCreditoRealizado[1];
		        	 localidadeId = (Integer) dadosCreditoRealizado[2];
		         }
		         
		         Integer idCategoriaConta = (Integer) dadosCreditoRealizado[3];
		         
		         BigDecimal valorCategoria = new BigDecimal("0.00");
                 if (dadosCreditoRealizado[4] != null) {
                	 valorCategoria = (BigDecimal)dadosCreditoRealizado[4];
                 }
            
                 if (!mapValoresContabilizadosComoPerdas.containsKey(idCategoriaConta)) {
                	 mapValoresContabilizadosComoPerdas.put(idCategoriaConta,BigDecimal.ZERO);
				 }

                 mapValoresContabilizadosComoPerdas.put(idCategoriaConta,
                		 mapValoresContabilizadosComoPerdas.get(idCategoriaConta).subtract(valorCategoria));

		     }
		 }    	 
			 
		 if(mapValoresContabilizadosComoPerdas != null){
			 //Pesquisa a coleção de categorias no sistema
			 Collection<Integer> colecaoIdsCategorias = this.repositorioArrecadacao.pesquisarIdsCategorias();
			 
			 for (Integer idCategoria : colecaoIdsCategorias) {
				 if (mapValoresContabilizadosComoPerdas.containsKey(idCategoria)) {
					 if (mapValoresContabilizadosComoPerdas.get(idCategoria).doubleValue() > 0.00) {
						
						  BigDecimal valorCategoria = mapValoresContabilizadosComoPerdas.get(idCategoria);
						
						  //Gravar o valor com sinal negativo
						  ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
			                        anoMesAnteriorFaturamento, gerenciaRegionalId,
			                        unidadeNegocioId, localidadeId,
			                        idCategoria, valorCategoria.multiply(new BigDecimal("-1")),
			                        LancamentoTipo.VALORES_CONTABILIZADOS_COMO_PERDAS, 700,
			                        LancamentoItem.VALORES_CONTABILIZADOS_COMO_PERDAS, 60, null);
		
			                colecaoContasAReceberContabil.add(contaAReceberContabil);
						
					 }
				 }
			 }			
		 }

     }
     
     /**
  	 * [UC0714] Gerar Contas a Receber Contábil
  	 * Adiciona os Recebimentos Não Identificados
  	 * @author Vivianne Sousa, Ivan Sergio
  	 * @date 17/08/2009, 08/11/2010
  	 * @alteracao: 08/11/2010 - RM2477 - Alteração no Relatório de Saldo de contas contábil (Item 4)
  	 */
      private void adicionarContaAReceberContabilRecebimentosNaoIdentificados(
              Integer idLocalidade, int anoMesAnteriorFaturamento,
              int anoMesArrecadacao, Collection colecaoContasAReceberContabil)
              throws ControladorException,ErroRepositorioException {
     	 
    	  Localidade localidade = repositorioFinanceiro.pesquisarUnidadeNegocioEGerenciaDaLocalidade(idLocalidade);
     	  Integer idGerenciaRegional = localidade.getGerenciaRegional().getId();
          Integer idUnidadeNegocio = localidade.getUnidadeNegocio().getId();
          
          Collection colecaoValorPagamentoImovel = repositorioFinanceiro
	 	 	.pesquisarValorPagamentoImovel(idLocalidade,anoMesArrecadacao);
          
          Map<Integer, BigDecimal> mapRecebimentosNaoIdentificados = null;

		 if (colecaoValorPagamentoImovel != null && !colecaoValorPagamentoImovel.isEmpty()) {
			 
			 Iterator iterValorPagamentoImovel = colecaoValorPagamentoImovel.iterator(); 
			 BigDecimal valorPagamento = BigDecimal.ZERO;
			 Integer idImovel = null;
			 Imovel imovel = null;
			 mapRecebimentosNaoIdentificados = new HashMap();
			 
			 while (iterValorPagamentoImovel.hasNext()) {
				Object[] arrayDadosPagamento = (Object[]) iterValorPagamentoImovel.next();
				
				idImovel = (Integer) arrayDadosPagamento[0];
				imovel = new Imovel();
				imovel.setId(idImovel);
				
				valorPagamento = (BigDecimal) arrayDadosPagamento[1];

				if (idImovel != null) {

					// [UC0108 - Obter Quantidade de Economias por Categoria]
					Collection colecaoCategoriasImovel = getControladorImovel().
							obterQuantidadeEconomiasCategoria(imovel);
					
					Iterator iteratorColecaoCategoriasImovel = colecaoCategoriasImovel.iterator();

					// [UC0185 - Obter Valor por Categoria]
					Iterator iteratorColecaoValorPagamentoPorCategoria = (getControladorImovel()
							.obterValorPorCategoria(colecaoCategoriasImovel,valorPagamento)).iterator();

					while (iteratorColecaoCategoriasImovel.hasNext()
							&& iteratorColecaoValorPagamentoPorCategoria.hasNext()) {
						
						Categoria categoria = (Categoria) iteratorColecaoCategoriasImovel.next();
						valorPagamento = (BigDecimal) iteratorColecaoValorPagamentoPorCategoria.next();

						if (!mapRecebimentosNaoIdentificados.containsKey(categoria.getId())) {
							mapRecebimentosNaoIdentificados.put(categoria.getId(),BigDecimal.ZERO);
						}

						mapRecebimentosNaoIdentificados.put(categoria.getId(),
								mapRecebimentosNaoIdentificados.get(categoria.getId()).add(valorPagamento));
					}

				} else {
					if (!mapRecebimentosNaoIdentificados.containsKey(Categoria.RESIDENCIAL)) {
						mapRecebimentosNaoIdentificados.put(Categoria.RESIDENCIAL,BigDecimal.ZERO);
					}
					mapRecebimentosNaoIdentificados.put(Categoria.RESIDENCIAL,
						mapRecebimentosNaoIdentificados.get(Categoria.RESIDENCIAL).add(valorPagamento));
				} 
			}				
				
			 //Pesquisa a coleção de categorias no sistema
			 Collection<Integer> colecaoIdsCategorias = this.repositorioArrecadacao.pesquisarIdsCategorias();
			 
			 for (Integer idCategoria : colecaoIdsCategorias) {
				 if (mapRecebimentosNaoIdentificados.containsKey(idCategoria)) {
					 if (mapRecebimentosNaoIdentificados.get(idCategoria).doubleValue() > 0.00) {
						
						  BigDecimal valorCategoria = mapRecebimentosNaoIdentificados.get(idCategoria);
						  //Gravar o valor com sinal negativo
						  ContaAReceberContabil contaAReceberContabil = criarContaAReceberContabil(
			                        anoMesAnteriorFaturamento, idGerenciaRegional,
			                        idUnidadeNegocio, idLocalidade,
			                        idCategoria, valorCategoria.multiply(new BigDecimal("-1")),
			                        LancamentoTipo.VALORES_CONTABILIZADOS_COMO_PERDAS, 800,
			                        LancamentoItem.RECEBIMENTOS_NAO_IDENTIFICADOS, 60, null);
		
			                colecaoContasAReceberContabil.add(contaAReceberContabil);
						
					 }
				 }
			 }			 
			 
		 }
          
      }
      
      
    /**
     * [UC0992] Gerar Lançamentos Contábeis dos Avisos Bancários 
     *
     * @author Raphael Rossiter
     * @date 22/02/2010
     *
     * @param anoMesArrecadacao
     * @param idFuncionalidadeIniciada
     * @throws ControladorException
     */
    public void gerarLancamentosContabeisAvisosBancarios(Integer anoMesArrecadacao, 
    	int idFuncionalidadeIniciada) throws ControladorException {

    	int idUnidadeIniciada = 0;

    	idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(
    	idFuncionalidadeIniciada, UnidadeProcessamento.FUNCIONALIDADE, 0);
    	  
    	try{
    		  
    		/*
    		 * O sistema seleciona, a partir da tabela AVISO_BANCARIO com ano/mês de referência da arrecadação 
    		 * (AVBC_AMREFERENCIAARRECADACAO) menor ou igual ao ano/mês da arrecadação recebido e com 
    		 * valor contabilizado (AVBC_VLCONTABILIZADO) diferente do valor realizado (AVBC_VLREALIZADO)  
    		 * e com valor realizado maior que zero ordenando pelo arrecadador (ARRC_ID) e pela data 
    		 * de realização (AVBC_DTREALIZADA);
    		 */
    		Collection colecaoAvisosBancarios = this.pesquisarAvisosBancariosParaGerarLancamentosContabeis(
    		anoMesArrecadacao);
    		
    		
    		if (colecaoAvisosBancarios != null && !colecaoAvisosBancarios.isEmpty()){
    			
    			/*
    			 * O sistema gera os lançamentos contábeis na tabela LANCAMENTO_CONTABIL para o mês e ano 
    			 * da arrecadação (LCNB_AMLANCAMENTO) e  origem com o valor correspondente a aviso 
    			 * bancário (LCOR_ID)
    			 */
    			LancamentoContabil lancamentoContabil = 
    			this.gerarLancamentoContabilParaAvisoBancario(anoMesArrecadacao);
    			
    			Iterator iterator = colecaoAvisosBancarios.iterator();
    			
    			while (iterator.hasNext()){
    				
    				AvisoBancario avisoBancario = (AvisoBancario) iterator.next();
    				
    				/*
    				 * O sistema determina o valor a ser contabilizado, que será o valor realizado 
    				 * (AVBC_VLREALIZADO) menos o valor contabilizado (AVBC_VLCONTABILIZADO);
    				 */
    				BigDecimal valorASerContabilizado = avisoBancario.getValorRealizado()
    				.subtract(avisoBancario.getValorContabilizado());
    				
    				/*
    				 * Para os avisos bancários de crédito (AVBC_ICCREDITODEBITO=1) e com o valor a ser 
    				 * contabilizado positivo OU
    				 * 
    				 * Para os avisos bancários de débito (AVBC_ICCREDITODEBITO=2) e com valor a ser 
    				 * contabilizado negativo:
    				 */
    				if ((avisoBancario.getIndicadorCreditoDebito().equals(AvisoBancario.INDICADOR_CREDITO) &&
    					valorASerContabilizado.compareTo(new BigDecimal("0.00")) > 0) ||
    					(avisoBancario.getIndicadorCreditoDebito().equals(AvisoBancario.INDICADOR_DEBITO) &&
            			valorASerContabilizado.compareTo(new BigDecimal("0.00")) < 0)){
    					
    					/*
    					 * CONTA CONTÁBIL = CNCT_IDDEBITO da tabela CONTA_CONTABIL com o nome da conta 
    					 * (CNCT_NMCONTA) correspondente a BANCO
    					 * 
    					 * CÓDIGO TERCEIRO = (CTBC_NNCONTACONTABIL da tabela CONTA_BANCARIA com CTBC_ID 
    					 * igual a CTBC_ID da tabela AVISO_BANCARIO)
    					 */
    					ContaContabil contaContabilDebito = this.pesquisarContaContabilPorNomeConta("BANCO");
    					
    					this.gerarLancamentoContabilItem(anoMesArrecadacao, lancamentoContabil, 
    					AvisoBancario.INDICADOR_DEBITO, avisoBancario, contaContabilDebito, valorASerContabilizado.abs(), 
    					avisoBancario.getContaBancaria().getNumeroContaContabil());
    					
    					/*
    					 * CONTA CONTÁBIL = CNCT_IDDEBITO da tabela CONTA_CONTABIL com o nome da conta 
    					 * (CNCT_NMCONTA) correspondente a ARRECADACAO A DISCRIMINAR
    					 * 
    					 * CÓDIGO TERCEIRO = NULL
    					 */
    					ContaContabil contaContabilCredito = this.pesquisarContaContabilPorNomeConta("ARRECADACAO A DISCRIMINAR");
    					
    					this.gerarLancamentoContabilItem(anoMesArrecadacao, lancamentoContabil, 
    					AvisoBancario.INDICADOR_CREDITO, avisoBancario, contaContabilCredito, valorASerContabilizado.abs(), 
    					null);
    				}
    				
    				/*
    				 * Para os avisos bancários de débito (AVBC_ICCREDITODEBITO=2) e com o valor a ser 
    				 * contabilizado positivo OU
    				 * 
    				 * Para os avisos bancários de crédito (AVBC_ICCREDITODEBITO=1) e com valor a ser 
    				 * contabilizado negativo
    				 */
    				else{
    					
    					/*
    					 * CONTA CONTÁBIL = CNCT_IDDEBITO da tabela CONTA_CONTABIL com o nome da conta 
    					 * (CNCT_NMCONTA) correspondente a ARRECADACAO A DISCRIMINAR
    					 * 
    					 * CÓDIGO TERCEIRO = NULL
    					 */
    					ContaContabil contaContabilDebito = this.pesquisarContaContabilPorNomeConta("ARRECADACAO A DISCRIMINAR");
    					
    					this.gerarLancamentoContabilItem(anoMesArrecadacao, lancamentoContabil, 
    					AvisoBancario.INDICADOR_DEBITO, avisoBancario, contaContabilDebito, valorASerContabilizado.abs(),
    					null);
    					
    					/*
    					 * CONTA CONTÁBIL = CNCT_IDDEBITO da tabela CONTA_CONTABIL com o nome da conta 
    					 * (CNCT_NMCONTA) correspondente a BANCO
    					 * 
    					 * CÓDIGO TERCEIRO = (CTBC_NNCONTACONTABIL da tabela CONTA_BANCARIA com CTBC_ID 
    					 * igual a CTBC_ID da tabela AVISO_BANCARIO)
    					 */
    					ContaContabil contaContabilCredito = this.pesquisarContaContabilPorNomeConta("BANCO");
    					
    					this.gerarLancamentoContabilItem(anoMesArrecadacao, lancamentoContabil, 
    					AvisoBancario.INDICADOR_CREDITO, avisoBancario, contaContabilCredito, valorASerContabilizado.abs(), 
    					avisoBancario.getContaBancaria().getNumeroContaContabil());
    				}
    				
    				
    				/*
    				 * O sistema atualiza a tabela AVISO_BANCARIO somando o valor a ser contabilizado 
    				 * (considerando o sinal) no valor contabilizado (AVBC_VLCONTABILIZADO);
    				 */
    				BigDecimal valorContabilizado = avisoBancario.getValorContabilizado()
    				.add(valorASerContabilizado);
    				
    				this.atualizarValorContabilizado(avisoBancario.getId(), valorContabilizado);
    			}
    		}
    		
    		
    		//ENCERRANDO A UNIDADE INICIADA
    		getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);
    	}
    	catch (Exception ex) {

    		/*
			 * Este catch serve para interceptar qualquer exceção que o processo batch venha a lançar e 
			 * garantir que a unidade de processamento do batch será atualizada com o erro ocorrido.
			 */
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex, idUnidadeIniciada, true);
			throw new EJBException(ex);
    	}
    }
    
    
    /**
     * [UC0992] Gerar Lançamentos Contábeis dos Avisos Bancários
     *
     * @author Raphael Rossiter
     * @date 22/02/2010
     *
     * @param anoMesReferenciaArrecadacao
     * @return Collection
     * @throws ControladorException
     */
    protected Collection pesquisarAvisosBancariosParaGerarLancamentosContabeis(Integer anoMesReferenciaArrecadacao) 
    	throws ControladorException {
		
		Collection retorno = null;
		
		try {
			
			retorno = repositorioFinanceiro.pesquisarAvisosBancariosParaGerarLancamentosContabeis(
			anoMesReferenciaArrecadacao);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		
		return retorno;
	}
    
    
    /**
     * [UC0992] Gerar Lançamentos Contábeis dos Avisos Bancários 
     *
     * @author Raphael Rossiter
     * @date 22/02/2010
     *
     * @param anoMesReferenciaArrecadacao
     * @return LancamentoContabil
     * @throws ControladorException
     */
    protected LancamentoContabil gerarLancamentoContabilParaAvisoBancario(Integer anoMesReferenciaArrecadacao) 
	throws ControladorException {
    	
    	LancamentoContabil lancamentoContabil = new LancamentoContabil();
    	
    	//ANO MÊS LANÇAMENTO = Ano/mês de referência da arrecadação recebido
    	lancamentoContabil.setAnoMes(anoMesReferenciaArrecadacao.intValue());
    	
    	//ORIGEM DO LANÇAMENTO = Valor correspondente a aviso bancário da tabela LANCAMENTO_ORIGEM
    	LancamentoOrigem lancamentoOrigem = new LancamentoOrigem();
    	lancamentoOrigem.setId(LancamentoOrigem.AVISO_BANCARIO);
    	lancamentoContabil.setLancamentoOrigem(lancamentoOrigem);
    	
    	/*
    	 * TIPO DO LANCAMENTO = Tipo de Lançamento (LCTP_ID da tabela LANCAMENTO_TIPO com o 
    	 * valor correspondente a aviso bancário)
    	 */
    	LancamentoTipo lancamentoTipo = new LancamentoTipo();
    	lancamentoTipo.setId(LancamentoTipo.AVISO_BANCARIO);
    	lancamentoContabil.setLancamentoTipo(lancamentoTipo);
    	
    	//LOCALIDADE = LOCA_ID da tabela LOCALIDADE com LOCA_ICSEDE = 1
		Localidade localidadeSede = this.getControladorCobranca().pesquisarLocalidadeSede();
		lancamentoContabil.setLocalidade(localidadeSede);
    	
    	//ÚLTIMA ALTERAÇÃO
    	lancamentoContabil.setUltimaAlteracao(new Date());
    	
    	//INSERINDO O LANÇAMENTO CONTÁBIL
    	Integer idLancamentoContabil = (Integer) this.getControladorUtil().inserir(lancamentoContabil);
    	lancamentoContabil.setId(idLancamentoContabil);
    	
    	return lancamentoContabil;
    }
    
    
    /**
     * [UC0992] Gerar Lançamentos Contábeis dos Avisos Bancários 
     *
     * @author Raphael Rossiter
     * @date 22/02/2010
     *
     * @param anoMesReferenciaArrecadacao
     * @param lancamentoContabil
     * @param indicadorCreditoDebito
     * @param avisoBancario
     * @param contaContabil
     * @param valorLancamento
     * @param codigoTerceiro
     * @return LancamentoContabilItem
     * @throws ControladorException
     */
    protected LancamentoContabilItem gerarLancamentoContabilItem(Integer anoMesReferenciaArrecadacao,
    		LancamentoContabil lancamentoContabil, Short indicadorCreditoDebito,
    		AvisoBancario avisoBancario, ContaContabil contaContabil, 
    		BigDecimal valorLancamento, Integer codigoTerceiro) throws ControladorException {
    	
    	LancamentoContabilItem lancamentoContabilItem = new LancamentoContabilItem();
    	
    	//LANCAMENTO CONTABIL
    	lancamentoContabilItem.setLancamentoContabil(lancamentoContabil);
    	
    	//INDICADOR DEBITO/CREDITO = 2
    	lancamentoContabilItem.setIndicadorDebitoCredito(indicadorCreditoDebito);
    	
    	//VALOR DO LANÇAMENTO
    	lancamentoContabilItem.setValorLancamento(valorLancamento);
    	
    	//CONTA CONTABIL
    	lancamentoContabilItem.setContaContabil(contaContabil);
    	
    	/*
    	 * DESCRIÇÃO HISTÓRICO = Descrição da forma de arrecadação (ARFM_DSARRECADACAOFORMA da tabela ARRECADACAO_FORMA 
    	 * com ARFM_ID da tabela AVISO_BANCARIO) concatenado com a data de realização do aviso 
    	 * bancário (AVBC_DTREALIZADA) no formato DD/MM/AAAA e com a data de lançamento do aviso 
    	 * bancário (AVBC_DTLANCAMENTO) no formato DD/MM/AAAA. Separar os campos com ponto e vírgula.
    	 */
    	String descricaoArrecadacaoForma = "";
    	
    	if (avisoBancario.getDataRealizada() != null){
    		
    		descricaoArrecadacaoForma = descricaoArrecadacaoForma +
    		Util.formatarData(avisoBancario.getDataRealizada()) + ";";
    	}

    	descricaoArrecadacaoForma = descricaoArrecadacaoForma + 
    	Util.formatarData(avisoBancario.getDataLancamento());
    	
    	if (avisoBancario.getArrecadacaoForma() != null){
    		
    		descricaoArrecadacaoForma = descricaoArrecadacaoForma + ";" + 
    		Util.completaString(avisoBancario.getArrecadacaoForma().getDescricao(), 38);
    	}
    	
    	
    	lancamentoContabilItem.setDescricaoHistorico(descricaoArrecadacaoForma);
    	
    	//CÓDIGO TERCEIRO = 
    	lancamentoContabilItem.setCodigoTerceiro(codigoTerceiro);
    	
    	/*
    	 * Caso o ano/mês de referência da arrecadação recebido seja igual ao ano/mês da data de realização do 
    	 * aviso bancário (AVBC_DTREALIZADA), atribuir a data de realização do aviso bancário;
    	 * 
    	 * Caso contrário atribuir a data do último dia do ano/mês de referência da arrecadação recebido. 
    	 */
    	if (avisoBancario.getDataRealizada() != null &&
    		anoMesReferenciaArrecadacao.equals(Util.getAnoMesComoInteger(avisoBancario.getDataRealizada()))){
    		
    		lancamentoContabilItem.setDataLancamento(avisoBancario.getDataRealizada());
    	}
    	else{
    		
    		Date dataLancamento = Util.obterUltimaDataMes(Util.obterMes(anoMesReferenciaArrecadacao),
    		Util.obterAno(anoMesReferenciaArrecadacao));
    		
    		lancamentoContabilItem.setDataLancamento(dataLancamento);
    	}
    	
    	//ÚLTIMA ALTERAÇÃO
    	lancamentoContabilItem.setUltimaAlteracao(new Date());
    	
    	//INSERINDO O LANÇAMENTO CONTÁBIL ÍTEM
    	Integer idLancamentoContabilItem = (Integer) this.getControladorUtil().inserir(lancamentoContabilItem);
    	lancamentoContabilItem.setId(idLancamentoContabilItem);
    	
    	return lancamentoContabilItem;
    }
    
    
    /**
	 * [UC0992] Gerar Lançamentos Contábeis dos Avisos Bancários 
	 *
	 * @author Raphael Rossiter
	 * @date 22/02/2010
	 *
	 * @param nomeConta
	 * @return ContaContabil
	 * @throws ControladorException
	 */
	public ContaContabil pesquisarContaContabilPorNomeConta(String nomeConta) 
	throws ControladorException{
	
		ContaContabil retorno = null;
		
		try {
			
			retorno = repositorioFinanceiro.pesquisarContaContabilPorNomeConta(nomeConta);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		
		return retorno;
	}
	
	
	/**
	 * [UC0992] Gerar Lançamentos Contábeis dos Avisos Bancários 
	 *
	 * @author Raphael Rossiter
	 * @date 22/02/2010
	 *
	 * @param idAvisoBancario
	 * @param valorContabilizado
	 * @throws ControladorException
	 */
	protected void atualizarValorContabilizado(Integer idAvisoBancario, BigDecimal valorContabilizado)
			throws ControladorException {
		
		try {
			
			repositorioFinanceiro.atualizarValorContabilizado(idAvisoBancario, valorContabilizado);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	
	/**
	 * @author Raphael Rossiter
	 * @date 24/02/2010
	 *
	 * @return O valor de controladorCobrancaLocal
	 */
	protected ControladorCobrancaLocal getControladorCobranca() {

		ControladorCobrancaLocalHome localHome = null;
		ControladorCobrancaLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorCobrancaLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_COBRANCA_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas à
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}
	
	
	/**
	 * [UC0989] Gerar Resumo de Documentos a Receber
	 *
	 * @author Raphael Rossiter
	 * @date 10/03/2010
	 *
	 * @param idLocalidade
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void gerarResumoDocumentosAReceber(Integer idLocalidade,
            int idFuncionalidadeIniciada) throws ControladorException {

        System.out.println("LOCALIDADE " + idLocalidade);
        int idUnidadeIniciada = 0;

        // -------------------------
        //
        // Registrar o início do processamento da Unidade de
        // Processamento
        // do Batch
        //
        // -------------------------
        idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(
        idFuncionalidadeIniciada, UnidadeProcessamento.LOCALIDADE, idLocalidade);

        try {

        	Collection colecaoDocumentosAReceberResumo = new ArrayList();
        	
        	/*
        	 * O sistema verifica se o resumo já foi gerado para o último faturamento encerrado;
        	 * Caso existam, o sistema exclui os dados de referência igual à última referência de 
        	 * faturamento gerada (ocorrências da tabela DOCUMENTOS_A_RECEBER_RESUMO com 
        	 * DRRS_AMREFERENCIADOCUMENTOS=PARM_AMREFERENCIAFATURAMENTO -1 da tabela SISTEMA_PARAMETROS);
        	 */
        	SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();
        	
        	int anoMesReferenciaFaturamentoMenosUm = Util.subtrairMesDoAnoMes(
        	sistemaParametro.getAnoMesFaturamento(), 1);

        	this.removerDocumentosAReceberFaixaResumo(anoMesReferenciaFaturamentoMenosUm, idLocalidade);
        	this.removerDocumentosAReceberResumo(anoMesReferenciaFaturamentoMenosUm, idLocalidade);
        	
        	//[SB0001 – Gerar resumo a partir de contas];
            this.gerarResumoAPartirConta(anoMesReferenciaFaturamentoMenosUm, idLocalidade, 
            		colecaoDocumentosAReceberResumo);
            
            //[SB0002 – Gerar resumo a partir de guias de pagamento]
            this.gerarResumoAPartirGuiaPagamento(anoMesReferenciaFaturamentoMenosUm, idLocalidade, 
            		colecaoDocumentosAReceberResumo);
            
            //[SB0003 – Gerar resumo a partir de débitos a cobrar]
            this.gerarResumoAPartirDebitoACobrar(anoMesReferenciaFaturamentoMenosUm, idLocalidade, 
            		colecaoDocumentosAReceberResumo);
            
            //[SB0004 – Gerar resumo a partir de créditos a realizar]
            this.gerarResumoAPartirCreditoARealizar(anoMesReferenciaFaturamentoMenosUm, idLocalidade, 
            		colecaoDocumentosAReceberResumo);

            //ENCERRANDO A UNIDADE DE PROCESSAMENTO
            getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
            idUnidadeIniciada, false);

            System.out.println("FIM DA GERAÇÃO - Localidade " + idLocalidade);

        } 
        catch (Exception e) {

            getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
            idUnidadeIniciada, true);
            
            throw new EJBException(e);
        }
    }
	
	/**
	 * [UC0989] Gerar Resumo de Documentos a Receber 
	 *
	 * @author Raphael Rossiter
	 * @date 10/03/2010
	 *
	 * @param anoMesReferenciaRecebimentos
	 * @param idLocalidade
	 * @throws ErroRepositorioException
	 */
	public void removerDocumentosAReceberResumo(int anoMesReferenciaFaturamento, 
		Integer idLocalidade) throws ControladorException {
		
		try {
			
			repositorioFinanceiro.removerDocumentosAReceberResumo(anoMesReferenciaFaturamento, 
			idLocalidade);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC0989] Gerar Resumo de Documentos a Receber
	 * 
	 * [SB0001 – Gerar resumo a partir de contas]
	 *
	 * @author Raphael Rossiter
	 * @date 10/03/2010
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param colecaoDocumentosAReceberResumo
	 * @throws ControladorException
	 */
	public void gerarResumoAPartirConta(int anoMesReferenciaFaturamento, Integer idLocalidade,
		Collection<DocumentosAReceberResumo> colecaoDocumentosAReceberResumo) throws ControladorException{
		
		Collection<Object[]> colecaoResumoContas = null;
		
		try {
			
			colecaoResumoContas = repositorioFinanceiro.pesquisarContasAReceberParaResumo(
			anoMesReferenciaFaturamento, idLocalidade);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		//GERANDO O RESUMO POR DOCUMENTO
		this.gerarDocumentosAReceberResumo(anoMesReferenciaFaturamento, colecaoResumoContas);
	}
	
	/**
	 * [UC0989] Gerar Resumo de Documentos a Receber
	 * 
	 * [SB0002 – Gerar resumo a partir de guias de pagamento]
	 *
	 * @author Raphael Rossiter
	 * @date 10/03/2010
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param colecaoDocumentosAReceberResumo
	 * @throws ControladorException
	 */
	public void gerarResumoAPartirGuiaPagamento(int anoMesReferenciaFaturamento, Integer idLocalidade,
		Collection<DocumentosAReceberResumo> colecaoDocumentosAReceberResumo) throws ControladorException{
			
		Collection<Object[]> colecaoResumoGuiasPagamento = null;
			
		try {
				
			colecaoResumoGuiasPagamento = repositorioFinanceiro.pesquisarGuiasPagamentoAReceberParaResumo(
			anoMesReferenciaFaturamento, idLocalidade);
				
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
			
		//GERANDO O RESUMO POR DOCUMENTO
		this.gerarDocumentosAReceberResumo(anoMesReferenciaFaturamento, colecaoResumoGuiasPagamento);
	}
	
	/**
	 * [UC0989] Gerar Resumo de Documentos a Receber
	 * 
	 * [SB0003 – Gerar resumo a partir de débitos a cobrar]
	 *
	 * @author Raphael Rossiter
	 * @date 10/03/2010
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param colecaoDocumentosAReceberResumo
	 * @throws ControladorException
	 */
	public void gerarResumoAPartirDebitoACobrar(int anoMesReferenciaFaturamento, Integer idLocalidade,
		Collection<DocumentosAReceberResumo> colecaoDocumentosAReceberResumo) throws ControladorException{
				
		Collection<Object[]> colecaoResumoDebitosACobrar = null;
				
		try {
					
			colecaoResumoDebitosACobrar = repositorioFinanceiro.pesquisarDebitosACobrarAReceberParaResumo(
			anoMesReferenciaFaturamento, idLocalidade);
					
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
				
		//GERANDO O RESUMO POR DOCUMENTO
		this.gerarDocumentosAReceberResumo(anoMesReferenciaFaturamento, colecaoResumoDebitosACobrar);
	}
	
	/**
	 * [UC0989] Gerar Resumo de Documentos a Receber
	 * 
	 * [SB0004 – Gerar resumo a partir de créditos a realizar]
	 *
	 * @author Raphael Rossiter
	 * @date 10/03/2010
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param colecaoDocumentosAReceberResumo
	 * @throws ControladorException
	 */
	public void gerarResumoAPartirCreditoARealizar(int anoMesReferenciaFaturamento, Integer idLocalidade,
		Collection<DocumentosAReceberResumo> colecaoDocumentosAReceberResumo) throws ControladorException{
					
		Collection<Object[]> colecaoResumoCreditoARealizar = null;
					
		try {
						
			colecaoResumoCreditoARealizar = repositorioFinanceiro.pesquisarCreditosARealizarAReceberParaResumo(
			anoMesReferenciaFaturamento, idLocalidade);
						
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
					
		//GERANDO O RESUMO POR DOCUMENTO
		this.gerarDocumentosAReceberResumo(anoMesReferenciaFaturamento, colecaoResumoCreditoARealizar);
	}
	
	/**
	 * [UC0989] Gerar Resumo de Documentos a Receber
	 *
	 * @author Raphael Rossiter, Mariana Victor
	 * @date 11/03/2010, 29/03/2011
	 *
	 * @param anoMesReferenciaFaturamento
	 * @param colecaoDocumentosAcumulados
	 * @return Collection<DocumentosAReceberResumo>
	 * @throws ControladorException
	 */
	public void gerarDocumentosAReceberResumo(int anoMesReferenciaFaturamento,
			Collection<Object[]> colecaoDocumentosAcumulados) throws ControladorException{
		
		Collection<DocumentosAReceberFaixaResumo> colecaoDocumentosAReceberFaixaResumo = new ArrayList();
		
		
		if (colecaoDocumentosAcumulados != null && !colecaoDocumentosAcumulados.isEmpty()){
			
			Iterator iterator = colecaoDocumentosAcumulados.iterator();
			DocumentosAReceberResumo documentosAReceberResumo = null;
			Date dataVencimentoDocumentoAuxiliar = null;
			
			while (iterator.hasNext()){
				
				Object[] dadosAcumulados = (Object[]) iterator.next();
				
				
					//GERENCIA REGIONAL
					GerenciaRegional gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId((Integer) dadosAcumulados[0]);
					
					//UNIDADE DE NEGOCIO
					UnidadeNegocio unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId((Integer) dadosAcumulados[1]);
					
					//LOCALIDADE
					Localidade localidade = new Localidade();
					localidade.setId((Integer) dadosAcumulados[2]);
					
					//IMOVEL PERFIL
					ImovelPerfil imovelPerfil = new ImovelPerfil();
					imovelPerfil.setId((Integer) dadosAcumulados[3]);
					
					//ESFERA PODER
					EsferaPoder esferaPoder = new EsferaPoder();
					esferaPoder.setId((Integer) dadosAcumulados[4]);
					
					//CATEGORIA
					Categoria categoria = new Categoria();
					categoria.setId((Integer) dadosAcumulados[5]);
					
					//DOCUMENTO TIPO
					DocumentoTipo documentoTipo = new DocumentoTipo();
					documentoTipo.setId((Integer) dadosAcumulados[6]);
					
					/*
					 * QUANTIDADE DE DIAS VENCIDOS
					 * 
					 * Para os documentos do tipo CONTA E GUIA DE PAGAMENTO:
					 * Calcular a quantidade de dias vencidos a partir da data de vencimento do documento em 
					 * relação ao último dia do mês de referência do faturamento (data referente ao último dia 
					 * de PARM_AMREFERENCIAFATURAMENTO da tabela SISTEMA_PARAMETROS menos a data de 
					 * vencimento do documento);
					 * 
					 * Caso contrário atribuir 0 (zero) a quantidade de dias de vencimento
					 */
					Integer quantidadeDiasVencido = 0;
					
					Date dataVencimentoDocumento = null;
					
					if (documentoTipo.getId().equals(DocumentoTipo.CONTA) ||
						documentoTipo.getId().equals(DocumentoTipo.GUIA_PAGAMENTO)){
						
						dataVencimentoDocumento = (Date) dadosAcumulados[7];
						
						Date dataUltimoDiaReferenciaFaturamento = Util.obterUltimaDataMes(Util.obterMes(
						anoMesReferenciaFaturamento), Util.obterAno(anoMesReferenciaFaturamento));
						
						if (dataUltimoDiaReferenciaFaturamento.compareTo(dataVencimentoDocumento) > 0){
							
							quantidadeDiasVencido = Util.obterQuantidadeDiasEntreDuasDatas(dataVencimentoDocumento,
							dataUltimoDiaReferenciaFaturamento);
						}
					}
					
					if (documentosAReceberResumo != null
							&& !(documentosAReceberResumo.getGerenciaRegional().getId().equals(gerenciaRegional.getId())
								&& documentosAReceberResumo.getUnidadeNegocio().getId().equals(unidadeNegocio.getId())
								&& documentosAReceberResumo.getLocalidade().getId().equals(localidade.getId())
							    && documentosAReceberResumo.getImovelPerfil().getId().equals(imovelPerfil.getId())
								&& documentosAReceberResumo.getEsferaPoder().getId().equals(esferaPoder.getId())
								&& documentosAReceberResumo.getCategoria().getId().equals(categoria.getId())
								&& documentosAReceberResumo.getDocumentoTipo().getId().equals(documentoTipo.getId())
								&& ((dataVencimentoDocumento == null && dataVencimentoDocumentoAuxiliar == null)
										|| (dataVencimentoDocumento != null && dataVencimentoDocumentoAuxiliar != null
												&& dataVencimentoDocumento.compareTo(dataVencimentoDocumentoAuxiliar) == 0)))) {
						
						
						this.inserirDocumentosAReceberResumo(documentosAReceberResumo, colecaoDocumentosAReceberFaixaResumo);
						
						colecaoDocumentosAReceberFaixaResumo = new ArrayList();
						
						documentosAReceberResumo = new DocumentosAReceberResumo();
						dataVencimentoDocumentoAuxiliar = null;
						
												
					}
					
					if (dataVencimentoDocumento != null) {
						dataVencimentoDocumentoAuxiliar = (Date) dataVencimentoDocumento.clone();
					} else {
						dataVencimentoDocumentoAuxiliar = null;
					}
					
					if (documentosAReceberResumo == null) {
						documentosAReceberResumo = new DocumentosAReceberResumo();
						dataVencimentoDocumentoAuxiliar = null;
					}
					
					/*
					 * Ano e Mês de Referência dos documentos a receber (PARM_AMREFERENCIAFATURAMENTO - 1 da 
					 * tabela SISTEMA_PARAMETROS)
					 */
					documentosAReceberResumo.setAnoMesReferenciaRecebimentos(anoMesReferenciaFaturamento);
					
					documentosAReceberResumo.setGerenciaRegional(gerenciaRegional);
					documentosAReceberResumo.setUnidadeNegocio(unidadeNegocio);
					documentosAReceberResumo.setLocalidade(localidade);
					documentosAReceberResumo.setImovelPerfil(imovelPerfil);
					documentosAReceberResumo.setEsferaPoder(esferaPoder);
					documentosAReceberResumo.setCategoria(categoria);
					documentosAReceberResumo.setDocumentoTipo(documentoTipo);
					documentosAReceberResumo.setQuantidadeDiasVencidos(quantidadeDiasVencido);
					
					/*
					 * INDICADOR SITUCAO DOCUMENTO
					 * Para os documentos do tipo CONTA E GUIA DE PAGAMENTO colocar 2 - VENCIDO, já para os documentos
					 * do tipo DEBITO A COBRAR e CREDITO A REALIZAR colocar 1 - A VENCER
					 */
					if (documentosAReceberResumo.getDocumentoTipo().getId().equals(DocumentoTipo.CONTA) ||
						documentosAReceberResumo.getDocumentoTipo().getId().equals(DocumentoTipo.GUIA_PAGAMENTO)){
						
						if (quantidadeDiasVencido == 0){
							
							documentosAReceberResumo.setIndicadorSituacaoDocumentos(
							DocumentosAReceberResumo.DOCUMENTO_A_VENCER);
						}
						else{
							
							documentosAReceberResumo.setIndicadorSituacaoDocumentos(
							DocumentosAReceberResumo.DOCUMENTO_VENCIDO);
						}
					}
					else{
						
						documentosAReceberResumo.setIndicadorSituacaoDocumentos(
						DocumentosAReceberResumo.DOCUMENTO_A_VENCER);
					}
					
					
					documentosAReceberResumo.setQuantidadeDiasVencidos(quantidadeDiasVencido);
					

					DocumentosAReceberFaixaResumo documentosAReceberFaixaResumo = new DocumentosAReceberFaixaResumo();
					
					
					//QUANTIDADE DE DOCUMENTOS
					if (documentosAReceberResumo.getDocumentoTipo().getId().equals(DocumentoTipo.CONTA) ||
						documentosAReceberResumo.getDocumentoTipo().getId().equals(DocumentoTipo.GUIA_PAGAMENTO)){

						documentosAReceberFaixaResumo.setQuantidadeDocumentos((Integer) dadosAcumulados[8]);
						
						if (documentosAReceberResumo.getQuantidadeDocumentos() != null
								&& documentosAReceberResumo.getQuantidadeDocumentos() > 0) {
							documentosAReceberResumo.setQuantidadeDocumentos(documentosAReceberResumo.getQuantidadeDocumentos()
									+ (Integer) dadosAcumulados[8]);
						} else {
							documentosAReceberResumo.setQuantidadeDocumentos((Integer) dadosAcumulados[8]);
						}
					}
					else{
						documentosAReceberFaixaResumo.setQuantidadeDocumentos((Integer) dadosAcumulados[7]);
						
						if (documentosAReceberResumo.getQuantidadeDocumentos() != null
								&& documentosAReceberResumo.getQuantidadeDocumentos() > 0) {
							documentosAReceberResumo.setQuantidadeDocumentos(documentosAReceberResumo.getQuantidadeDocumentos()
									+ (Integer) dadosAcumulados[7]);
						} else {
							documentosAReceberResumo.setQuantidadeDocumentos((Integer) dadosAcumulados[7]);
						}
					}
					
					//VALOR DOS DOCUMENTOS
					if (documentosAReceberResumo.getDocumentoTipo().getId().equals(DocumentoTipo.CONTA)){
						
						Conta contaParaCalculo = new Conta();
						
						contaParaCalculo.setValorAgua((BigDecimal) dadosAcumulados[9]);
						contaParaCalculo.setValorEsgoto((BigDecimal) dadosAcumulados[10]);
						contaParaCalculo.setDebitos((BigDecimal) dadosAcumulados[11]);
						contaParaCalculo.setValorCreditos((BigDecimal) dadosAcumulados[12]);
						
						/*
						 Alterado por Tiago Moreno
						 Solicitado por Eduardo Borges
						 Em 12/12/2011
						 
						 Desprezar os impostos dos documentos no calculo do valor do documento.
						 */
						
						//contaParaCalculo.setValorImposto((BigDecimal) dadosAcumulados[13]);
						contaParaCalculo.setValorImposto(BigDecimal.ZERO);

						/*Fim da alteração de Tiago Moreno*/
						
						documentosAReceberFaixaResumo.setValorDocumentos(contaParaCalculo.getValorTotalContaBigDecimal());
						
						if (documentosAReceberResumo.getValorDocumentos() != null && !documentosAReceberResumo.getValorDocumentos().equals(BigDecimal.ZERO)) {
							documentosAReceberResumo.setValorDocumentos(documentosAReceberResumo.getValorDocumentos()
									.add(contaParaCalculo.getValorTotalContaBigDecimal()));
						} else {
							documentosAReceberResumo.setValorDocumentos(contaParaCalculo.getValorTotalContaBigDecimal());
						}
					}
					else if (documentosAReceberResumo.getDocumentoTipo().getId().equals(DocumentoTipo.GUIA_PAGAMENTO)){

						documentosAReceberFaixaResumo.setValorDocumentos((BigDecimal) dadosAcumulados[9]);
						
						if (documentosAReceberResumo.getValorDocumentos() != null && !documentosAReceberResumo.getValorDocumentos().equals(BigDecimal.ZERO)) {
							documentosAReceberResumo.setValorDocumentos(documentosAReceberResumo.getValorDocumentos()
									.add((BigDecimal) dadosAcumulados[9]));
						} else {
							documentosAReceberResumo.setValorDocumentos((BigDecimal) dadosAcumulados[9]);
						}
					}
					else{
						documentosAReceberFaixaResumo.setValorDocumentos((BigDecimal) dadosAcumulados[8]);
						
						if (documentosAReceberResumo.getValorDocumentos() != null && !documentosAReceberResumo.getValorDocumentos().equals(BigDecimal.ZERO)) {
							documentosAReceberResumo.setValorDocumentos(documentosAReceberResumo.getValorDocumentos()
									.add((BigDecimal) dadosAcumulados[8]));
						} else {
							documentosAReceberResumo.setValorDocumentos((BigDecimal) dadosAcumulados[8]);
						}
					}
					
					//ULTIMA ALTERACAO
					documentosAReceberResumo.setUltimaAlteracao(new Date());
					

					//FAIXA
					FaixaDocumentosAReceber faixaDocumentosAReceber = new FaixaDocumentosAReceber();
					if (documentosAReceberResumo.getDocumentoTipo().getId().equals(DocumentoTipo.CONTA)){
						faixaDocumentosAReceber.setId((Integer) dadosAcumulados[14]);
					} else if (documentosAReceberResumo.getDocumentoTipo().getId().equals(DocumentoTipo.GUIA_PAGAMENTO)){
						faixaDocumentosAReceber.setId((Integer) dadosAcumulados[10]);
					} else {
						faixaDocumentosAReceber.setId((Integer) dadosAcumulados[9]);
					}
					
					documentosAReceberFaixaResumo.setFaixaDocumentosAReceber(faixaDocumentosAReceber);
					documentosAReceberFaixaResumo.setUltimaAlteracao(new Date());
					colecaoDocumentosAReceberFaixaResumo.add(documentosAReceberFaixaResumo);
					
					if (!iterator.hasNext()) {
						this.inserirDocumentosAReceberResumo(documentosAReceberResumo, colecaoDocumentosAReceberFaixaResumo);
						colecaoDocumentosAReceberFaixaResumo = new ArrayList();
					}

			}
		}

	}
	
	private void inserirDocumentosAReceberResumo(DocumentosAReceberResumo documentosAReceberResumo,
			Collection<DocumentosAReceberFaixaResumo> colecaoDocumentosAReceberFaixaResumo) throws ControladorException{
		Integer idDocumentosAReceberResumo = (Integer) getControladorBatch().inserirObjetoParaBatch(
				documentosAReceberResumo);
		
		if (colecaoDocumentosAReceberFaixaResumo != null && !colecaoDocumentosAReceberFaixaResumo.isEmpty()) {
			DocumentosAReceberResumo documentosAReceberResumoInserido = new DocumentosAReceberResumo();
			documentosAReceberResumoInserido.setId(idDocumentosAReceberResumo);
			
			Iterator iteratorDocumentosFaixa = colecaoDocumentosAReceberFaixaResumo.iterator();
			while (iteratorDocumentosFaixa.hasNext()) {
				DocumentosAReceberFaixaResumo documentosAReceberFaixaResumo = (DocumentosAReceberFaixaResumo) iteratorDocumentosFaixa.next();
				
				documentosAReceberFaixaResumo.setDocumentosAReceberResumo(documentosAReceberResumoInserido);
				
				getControladorBatch().inserirObjetoParaBatch(
						documentosAReceberFaixaResumo);
				
			}
			
		}
	}
	
    /**
     * [UC 0982] Gerar Resumo da Receita
     * autor: Flávio Cordeiro
     * data: 22/02/2010
     *
     * Este caso de uso gera o resumo da receita aberta e será executado
     * através de um batch
     */
    
    public void gerarResumoReceita(int idFuncionalidadeIniciada) throws ControladorException{
		
  	 int idUnidadeIniciada = 0;		

		 idUnidadeIniciada = 
			getControladorBatch().iniciarUnidadeProcessamentoBatch(
				idFuncionalidadeIniciada,
				UnidadeProcessamento.FUNCIONALIDADE,0);
  	  
  	try {
			//1. apagar dados da tabela resumo_receita
			this.apagarDadosResumoReceitaMesAnoArrecadacao();
			  
			SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();
			  
			int anoMes = sistemaParametro.getAnoMesArrecadacao();
			String mes = (anoMes+"").substring(4,6);
			String ano = (anoMes+"").substring(0,4);
			String ultimoDiaMes = Util.obterUltimoDiaMes(new Integer(mes), new Integer(ano));
			  
			String dataFinal = ultimoDiaMes+"/"+mes+"/"+ano;
			String dataInicial = "01/"+mes+"/"+ano;
			  
			this.inserirDadosContasResumoReceita(dataInicial, dataFinal, sistemaParametro);
			this.inserirDadosContasHistoricoResumoReceita(dataInicial, dataFinal, sistemaParametro);
			this.inserirDadosContasOutrosResumoReceita(dataInicial, dataFinal, sistemaParametro);
			
			
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(
					null, idUnidadeIniciada, false);
			
		} catch (ControladorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
					idUnidadeIniciada, true);
		}
    }
    
    /**
     * [UC 0982] Gerar Resumo da Receita
     * autor: Flávio Cordeiro
     * data: 22/02/2010
     *
     * Este caso de uso gera o resumo da receita aberta e será executado
     * através de um batch
     */
    public void inserirDadosContasResumoReceita(String dataInicial, String dataFinal, SistemaParametro sistemaParametro){
  	  	
  	  try{
  	  //[SB001] - Resumo dos Pagamentos de Contas
			Collection colecaoImpostos =
				this.pesquisarImpostoResumoPagamentoConta(Util.converteStringParaDate(dataInicial),
					Util.converteStringParaDate(dataFinal));
			
			Collection colecaoContas =
				this.pesquisarResumoPagamentoConta(Util.converteStringParaDate(dataInicial),
					Util.converteStringParaDate(dataFinal));
			
			Collection colecaoDividaAtiva = 
				this.pesquisarDividaAtivaResumoPagamentoConta(Util.converteStringParaDate(dataInicial),
					Util.converteStringParaDate(dataFinal));
			
			Collection colecaoCredito = pesquisarResumoPagamentoContaCredito(Util.converteStringParaDate(dataInicial),
					Util.converteStringParaDate(dataFinal));
			
			Collection colecaoServico = pesquisarResumoPagamentoContaServico(Util.converteStringParaDate(dataInicial),
					Util.converteStringParaDate(dataFinal));
			
			//---INSERIR
			
			ResumoReceitaHelper helper = null;
			ResumoReceita resumoReceitaInserir = null;
			Arrecadador arrecadador = null;
			Banco banco = null;
			ContaBancaria contaBancaria= null;
			GerenciaRegional gerenciaRegional = null;
			UnidadeNegocio unidadeNegocio = null;
			Localidade localidade = null;
			ContaContabil contabil = null;
			Categoria categoria = null;
			
			//apartir da colecaoContas
			if(colecaoContas != null && !colecaoContas.isEmpty()){
				Iterator<ResumoReceitaHelper> iteratorContas = colecaoContas.iterator();
				
				while(iteratorContas.hasNext()){
					helper = iteratorContas.next();
					
					resumoReceitaInserir = new ResumoReceita();
					resumoReceitaInserir.setAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao());
					
					arrecadador = new Arrecadador();
					arrecadador.setId(helper.getArrecadadorId());
					contaBancaria = new ContaBancaria();
					contaBancaria.setId(helper.getContaBancariaId());
					banco = new Banco();
					banco.setId(helper.getBancoId());
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getGerenciaRegionalId());
					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(helper.getUnidadeNegocioId());
					localidade = new Localidade();
					localidade.setId(helper.getLocalidadeId());
					categoria = new Categoria();
					categoria.setId(helper.getCategoriaId());
					
					resumoReceitaInserir.setArrecadador(arrecadador);
					resumoReceitaInserir.setContaBancaria(contaBancaria);
					resumoReceitaInserir.setBanco(banco);
					resumoReceitaInserir.setGerenciaRegional(gerenciaRegional);
					resumoReceitaInserir.setLocalidade(localidade);
					resumoReceitaInserir.setDataRealizada(helper.getDataRealizacao());
					resumoReceitaInserir.setUltimaAlteracao(new Date());
					resumoReceitaInserir.setCategoria(categoria);
					resumoReceitaInserir.setUnidadeNegocio(unidadeNegocio);
					
					if(helper.getSomaAgua() != null && !helper.getSomaAgua().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
						
						if (helper.getReceitaIntra().booleanValue()) {
							contabil.setId(new Integer(ContaContabil.RECEITA_AGUA_INTRA));
						} else {
							contabil.setId(new Integer(ContaContabil.RECEITA_AGUA));
						}
						
						resumoReceitaInserir.setContaContabil(contabil);
						resumoReceitaInserir.setValorReceita(helper.getSomaAgua());
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
					if(helper.getSomaEsgoto() != null && !helper.getSomaEsgoto().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
						
						if (helper.getReceitaIntra().booleanValue()) {
							contabil.setId(new Integer(ContaContabil.RECEITA_ESGOTO_INTRA));
						} else {
							contabil.setId(new Integer(ContaContabil.RECEITA_ESGOTO));
						}
						
						resumoReceitaInserir.setContaContabil(contabil);
						resumoReceitaInserir.setValorReceita(helper.getSomaEsgoto());
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
				}
			}
			
			if(colecaoCredito != null && !colecaoCredito.isEmpty()){
				Iterator<ResumoReceitaHelper> iteratorCredito = colecaoCredito.iterator();
				
				while(iteratorCredito.hasNext()){
					helper = iteratorCredito.next();
					
					resumoReceitaInserir = new ResumoReceita();
					resumoReceitaInserir.setAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao());
					
					arrecadador = new Arrecadador();
					arrecadador.setId(helper.getArrecadadorId());
					contaBancaria = new ContaBancaria();
					contaBancaria.setId(helper.getContaBancariaId());
					banco = new Banco();
					banco.setId(helper.getBancoId());
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getGerenciaRegionalId());
					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(helper.getUnidadeNegocioId());
					localidade = new Localidade();
					localidade.setId(helper.getLocalidadeId());
					categoria = new Categoria();
					categoria.setId(helper.getCategoriaId());
					
					resumoReceitaInserir.setArrecadador(arrecadador);
					resumoReceitaInserir.setContaBancaria(contaBancaria);
					resumoReceitaInserir.setBanco(banco);
					resumoReceitaInserir.setGerenciaRegional(gerenciaRegional);
					resumoReceitaInserir.setLocalidade(localidade);
					resumoReceitaInserir.setDataRealizada(helper.getDataRealizacao());
					resumoReceitaInserir.setUltimaAlteracao(new Date());
					resumoReceitaInserir.setCategoria(categoria);
					resumoReceitaInserir.setUnidadeNegocio(unidadeNegocio);
					
					if(helper.getSomaCredito() != null && !helper.getSomaCredito().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
						
						if (helper.getReceitaIntra().booleanValue()) {
							contabil.setId(new Integer(ContaContabil.RECEITA_CREDITO_INTRA));
						} else {
							contabil.setId(new Integer(ContaContabil.RECEITA_CREDITO));
						}
						
						resumoReceitaInserir.setContaContabil(contabil);
						BigDecimal valorInserir = new BigDecimal("-"+helper.getSomaCredito());
						resumoReceitaInserir.setValorReceita(valorInserir);
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
				}
			}
			
			if(colecaoServico != null && !colecaoServico.isEmpty()){
				Iterator<ResumoReceitaHelper> iteratorServico = colecaoServico.iterator();
				
				while(iteratorServico.hasNext()){
					helper = iteratorServico.next();
					
					resumoReceitaInserir = new ResumoReceita();
					resumoReceitaInserir.setAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao());
					
					arrecadador = new Arrecadador();
					arrecadador.setId(helper.getArrecadadorId());
					contaBancaria = new ContaBancaria();
					contaBancaria.setId(helper.getContaBancariaId());
					banco = new Banco();
					banco.setId(helper.getBancoId());
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getGerenciaRegionalId());
					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(helper.getUnidadeNegocioId());
					localidade = new Localidade();
					localidade.setId(helper.getLocalidadeId());
					categoria = new Categoria();
					categoria.setId(helper.getCategoriaId());
					
					resumoReceitaInserir.setArrecadador(arrecadador);
					resumoReceitaInserir.setContaBancaria(contaBancaria);
					resumoReceitaInserir.setBanco(banco);
					resumoReceitaInserir.setGerenciaRegional(gerenciaRegional);
					resumoReceitaInserir.setLocalidade(localidade);
					resumoReceitaInserir.setDataRealizada(helper.getDataRealizacao());
					resumoReceitaInserir.setUltimaAlteracao(new Date());
					resumoReceitaInserir.setCategoria(categoria);
					resumoReceitaInserir.setUnidadeNegocio(unidadeNegocio);
										
					if(helper.getSomaServico() != null && !helper.getSomaServico().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
						//contabil.setId(new Integer(ContaContabil.RECEITA_SERVICOS));
						contabil.setId(helper.getContaContabil());
						resumoReceitaInserir.setContaContabil(contabil);
						resumoReceitaInserir.setValorReceita(helper.getSomaServico());
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
				}
			}
			
			//apartir da colecaoImpostos
			if(colecaoImpostos != null && !colecaoImpostos.isEmpty()){
				Iterator<ResumoReceitaHelper> iteratorImpostos = colecaoImpostos.iterator();
				
				while(iteratorImpostos.hasNext()){
					helper = iteratorImpostos.next();
					
					resumoReceitaInserir = new ResumoReceita();
					resumoReceitaInserir.setAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao());
					
					arrecadador = new Arrecadador();
					arrecadador.setId(helper.getArrecadadorId());
					contaBancaria = new ContaBancaria();
					contaBancaria.setId(helper.getContaBancariaId());
					banco = new Banco();
					banco.setId(helper.getBancoId());
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getGerenciaRegionalId());
					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(helper.getUnidadeNegocioId());
					localidade = new Localidade();
					localidade.setId(helper.getLocalidadeId());
					categoria = new Categoria();
					categoria.setId(helper.getCategoriaId());
					
					resumoReceitaInserir.setArrecadador(arrecadador);
					resumoReceitaInserir.setContaBancaria(contaBancaria);
					resumoReceitaInserir.setBanco(banco);
					resumoReceitaInserir.setGerenciaRegional(gerenciaRegional);
					System.out.println("categoria imposto:"+ categoria.getId());
					resumoReceitaInserir.setCategoria(categoria);
					resumoReceitaInserir.setLocalidade(localidade);
					resumoReceitaInserir.setDataRealizada(helper.getDataRealizacao());
					resumoReceitaInserir.setUltimaAlteracao(new Date());
					resumoReceitaInserir.setUnidadeNegocio(unidadeNegocio);
					
					if(helper.getSomaImposto() != null && !helper.getSomaImposto().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
						
						if (helper.getReceitaIntra().booleanValue()) {
							contabil.setId(new Integer(ContaContabil.RECEITA_IMPOSTOS_INTRA));
						} else {
							contabil.setId(new Integer(ContaContabil.RECEITA_IMPOSTOS));
						}
						
						resumoReceitaInserir.setContaContabil(contabil);
						BigDecimal valorInserir = new BigDecimal("-"+helper.getValorTotal());
						resumoReceitaInserir.setValorReceita(valorInserir);
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
					
				}
			}
			
			//apartir da colecaoDividaAtiva
			if(colecaoDividaAtiva != null && !colecaoDividaAtiva.isEmpty()){
				Iterator<ResumoReceitaHelper> iteratorDividaAtiva = colecaoDividaAtiva.iterator();
				
				while(iteratorDividaAtiva.hasNext()){
					helper = iteratorDividaAtiva.next();
					
					resumoReceitaInserir = new ResumoReceita();
					resumoReceitaInserir.setAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao());
					
					arrecadador = new Arrecadador();
					arrecadador.setId(helper.getArrecadadorId());
					contaBancaria = new ContaBancaria();
					contaBancaria.setId(helper.getContaBancariaId());
					banco = new Banco();
					banco.setId(helper.getBancoId());
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getGerenciaRegionalId());
					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(helper.getUnidadeNegocioId());
					localidade = new Localidade();
					localidade.setId(helper.getLocalidadeId());
					categoria = new Categoria();
					categoria.setId(helper.getCategoriaId());
					
					
					resumoReceitaInserir.setArrecadador(arrecadador);
					resumoReceitaInserir.setContaBancaria(contaBancaria);
					resumoReceitaInserir.setBanco(banco);
					resumoReceitaInserir.setGerenciaRegional(gerenciaRegional);
					resumoReceitaInserir.setLocalidade(localidade);
					resumoReceitaInserir.setDataRealizada(helper.getDataRealizacao());
					resumoReceitaInserir.setUltimaAlteracao(new Date());
					resumoReceitaInserir.setCategoria(categoria);
					resumoReceitaInserir.setUnidadeNegocio(unidadeNegocio);
					
					if(helper.getSomaDividaAtiva() != null && !helper.getSomaDividaAtiva().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
						contabil.setId(new Integer(ContaContabil.RECEITA_DIVIDA_ATIVA));
						resumoReceitaInserir.setContaContabil(contabil);
						resumoReceitaInserir.setValorReceita(helper.getValorTotal());
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
					
				}
			}
  	  } catch (ControladorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * [UC 0982] Gerar Resumo da Receita
     * autor: Flávio Cordeiro
     * data: 22/02/2010
     *
     * Este caso de uso gera o resumo da receita aberta e será executado
     * através de um batch
     */
    public void inserirDadosContasHistoricoResumoReceita(String dataInicial, String dataFinal, SistemaParametro sistemaParametro){
	  	
  	  try{
  	  //[SB001] - Resumo dos Pagamentos de Contas
			Collection colecaoImpostos =
				this.pesquisarImpostoHistoricoResumoPagamentoConta(Util.converteStringParaDate(dataInicial),
					Util.converteStringParaDate(dataFinal));
			
			Collection colecaoContas =
				this.pesquisarResumoHistoricoPagamentoConta(Util.converteStringParaDate(dataInicial),
					Util.converteStringParaDate(dataFinal));
			
			Collection colecaoDividaAtiva = 
				this.pesquisarDividaAtivaHistoricoResumoPagamentoConta(Util.converteStringParaDate(dataInicial),
					Util.converteStringParaDate(dataFinal));
			
			Collection colecaoCredito = pesquisarResumoHistoricoPagamentoContaCredito(Util.converteStringParaDate(dataInicial),
					Util.converteStringParaDate(dataFinal));
			
			Collection colecaoServico = pesquisarResumoHistoricoPagamentoContaServico(Util.converteStringParaDate(dataInicial),
					Util.converteStringParaDate(dataFinal));
			
			//---INSERIR
			
			ResumoReceitaHelper helper = null;
			ResumoReceita resumoReceitaInserir = null;
			Arrecadador arrecadador = null;
			Banco banco = null;
			ContaBancaria contaBancaria= null;
			GerenciaRegional gerenciaRegional = null;
			UnidadeNegocio unidadeNegocio = null;
			Localidade localidade = null;
			ContaContabil contabil = null;
			Categoria categoria = null;
			
			//apartir da colecaoContas
			if(colecaoContas != null && !colecaoContas.isEmpty()){
				Iterator<ResumoReceitaHelper> iteratorContas = colecaoContas.iterator();
				
				while(iteratorContas.hasNext()){
					helper = iteratorContas.next();
					
					resumoReceitaInserir = new ResumoReceita();
					resumoReceitaInserir.setAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao());
					
					arrecadador = new Arrecadador();
					arrecadador.setId(helper.getArrecadadorId());
					contaBancaria = new ContaBancaria();
					contaBancaria.setId(helper.getContaBancariaId());
					banco = new Banco();
					banco.setId(helper.getBancoId());
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getGerenciaRegionalId());
					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(helper.getUnidadeNegocioId());
					localidade = new Localidade();
					localidade.setId(helper.getLocalidadeId());
					categoria = new Categoria();
					categoria.setId(helper.getCategoriaId());
					
					resumoReceitaInserir.setArrecadador(arrecadador);
					resumoReceitaInserir.setContaBancaria(contaBancaria);
					resumoReceitaInserir.setBanco(banco);
					resumoReceitaInserir.setGerenciaRegional(gerenciaRegional);
					resumoReceitaInserir.setLocalidade(localidade);
					resumoReceitaInserir.setDataRealizada(helper.getDataRealizacao());
					resumoReceitaInserir.setUltimaAlteracao(new Date());
					resumoReceitaInserir.setCategoria(categoria);
					resumoReceitaInserir.setUnidadeNegocio(unidadeNegocio);
					
					if(helper.getSomaAgua() != null && !helper.getSomaAgua().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
						
						if (helper.getReceitaIntra().booleanValue()) {
							contabil.setId(new Integer(ContaContabil.RECEITA_AGUA_INTRA));
						} else {
							contabil.setId(new Integer(ContaContabil.RECEITA_AGUA));
						}
						
						resumoReceitaInserir.setContaContabil(contabil);
						resumoReceitaInserir.setValorReceita(helper.getSomaAgua());
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
					if(helper.getSomaEsgoto() != null && !helper.getSomaEsgoto().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
						
						if (helper.getReceitaIntra().booleanValue()) {
							contabil.setId(new Integer(ContaContabil.RECEITA_ESGOTO_INTRA));
						} else {
							contabil.setId(new Integer(ContaContabil.RECEITA_ESGOTO));
						}
						
						resumoReceitaInserir.setContaContabil(contabil);
						resumoReceitaInserir.setValorReceita(helper.getSomaEsgoto());
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
				}
			}
			
			if(colecaoCredito != null && !colecaoCredito.isEmpty()){
				Iterator<ResumoReceitaHelper> iteratorCredito = colecaoCredito.iterator();
				
				while(iteratorCredito.hasNext()){
					helper = iteratorCredito.next();
					
					resumoReceitaInserir = new ResumoReceita();
					resumoReceitaInserir.setAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao());
					
					arrecadador = new Arrecadador();
					arrecadador.setId(helper.getArrecadadorId());
					contaBancaria = new ContaBancaria();
					contaBancaria.setId(helper.getContaBancariaId());
					banco = new Banco();
					banco.setId(helper.getBancoId());
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getGerenciaRegionalId());
					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(helper.getUnidadeNegocioId());
					localidade = new Localidade();
					localidade.setId(helper.getLocalidadeId());
					categoria = new Categoria();
					categoria.setId(helper.getCategoriaId());
					
					resumoReceitaInserir.setArrecadador(arrecadador);
					resumoReceitaInserir.setContaBancaria(contaBancaria);
					resumoReceitaInserir.setBanco(banco);
					resumoReceitaInserir.setGerenciaRegional(gerenciaRegional);
					resumoReceitaInserir.setLocalidade(localidade);
					resumoReceitaInserir.setDataRealizada(helper.getDataRealizacao());
					resumoReceitaInserir.setUltimaAlteracao(new Date());
					resumoReceitaInserir.setCategoria(categoria);
					resumoReceitaInserir.setUnidadeNegocio(unidadeNegocio);
					
					if(helper.getSomaCredito() != null && !helper.getSomaCredito().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
						
						if (helper.getReceitaIntra().booleanValue()) {
							contabil.setId(new Integer(ContaContabil.RECEITA_CREDITO_INTRA));
						} else {
							contabil.setId(new Integer(ContaContabil.RECEITA_CREDITO));
						}
						
						resumoReceitaInserir.setContaContabil(contabil);
						BigDecimal valorInserir = new BigDecimal("-"+helper.getSomaCredito());
						resumoReceitaInserir.setValorReceita(valorInserir);
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
				}
			}
			
			if(colecaoServico != null && !colecaoServico.isEmpty()){
				Iterator<ResumoReceitaHelper> iteratorServico = colecaoServico.iterator();
				
				while(iteratorServico.hasNext()){
					helper = iteratorServico.next();
					
					resumoReceitaInserir = new ResumoReceita();
					resumoReceitaInserir.setAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao());
					
					arrecadador = new Arrecadador();
					arrecadador.setId(helper.getArrecadadorId());
					contaBancaria = new ContaBancaria();
					contaBancaria.setId(helper.getContaBancariaId());
					banco = new Banco();
					banco.setId(helper.getBancoId());
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getGerenciaRegionalId());
					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(helper.getUnidadeNegocioId());
					localidade = new Localidade();
					localidade.setId(helper.getLocalidadeId());
					categoria = new Categoria();
					categoria.setId(helper.getCategoriaId());
					
					resumoReceitaInserir.setArrecadador(arrecadador);
					resumoReceitaInserir.setContaBancaria(contaBancaria);
					resumoReceitaInserir.setBanco(banco);
					resumoReceitaInserir.setGerenciaRegional(gerenciaRegional);
					resumoReceitaInserir.setLocalidade(localidade);
					resumoReceitaInserir.setDataRealizada(helper.getDataRealizacao());
					resumoReceitaInserir.setUltimaAlteracao(new Date());
					resumoReceitaInserir.setCategoria(categoria);
					resumoReceitaInserir.setUnidadeNegocio(unidadeNegocio);
					
					if(helper.getSomaServico() != null && !helper.getSomaServico().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
//						contabil.setId(new Integer(ContaContabil.RECEITA_SERVICOS));
						contabil.setId(helper.getContaContabil());
						resumoReceitaInserir.setContaContabil(contabil);
						resumoReceitaInserir.setValorReceita(helper.getSomaServico());
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
				}
			}
			
			//apartir da colecaoImpostos
			if(colecaoImpostos != null && !colecaoImpostos.isEmpty()){
				Iterator<ResumoReceitaHelper> iteratorImpostos = colecaoImpostos.iterator();
				while(iteratorImpostos.hasNext()){
					helper = iteratorImpostos.next();
					
					resumoReceitaInserir = new ResumoReceita();
					resumoReceitaInserir.setAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao());
					
					arrecadador = new Arrecadador();
					arrecadador.setId(helper.getArrecadadorId());
					contaBancaria = new ContaBancaria();
					contaBancaria.setId(helper.getContaBancariaId());
					banco = new Banco();
					banco.setId(helper.getBancoId());
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getGerenciaRegionalId());
					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(helper.getUnidadeNegocioId());
					localidade = new Localidade();
					localidade.setId(helper.getLocalidadeId());
					categoria = new Categoria();
					categoria.setId(helper.getCategoriaId());
					
					resumoReceitaInserir.setArrecadador(arrecadador);
					resumoReceitaInserir.setContaBancaria(contaBancaria);
					resumoReceitaInserir.setBanco(banco);
					resumoReceitaInserir.setGerenciaRegional(gerenciaRegional);
					resumoReceitaInserir.setLocalidade(localidade);
					resumoReceitaInserir.setDataRealizada(helper.getDataRealizacao());
					resumoReceitaInserir.setUltimaAlteracao(new Date());
					resumoReceitaInserir.setCategoria(categoria);
					resumoReceitaInserir.setUnidadeNegocio(unidadeNegocio);
					
					if(helper.getSomaImposto() != null && !helper.getSomaImposto().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();

						if (helper.getReceitaIntra().booleanValue()) {
							contabil.setId(new Integer(ContaContabil.RECEITA_IMPOSTOS_INTRA));
						} else {
							contabil.setId(new Integer(ContaContabil.RECEITA_IMPOSTOS));
						}
						
						resumoReceitaInserir.setContaContabil(contabil);
						BigDecimal valorInserir = new BigDecimal("-"+helper.getValorTotal());
						resumoReceitaInserir.setValorReceita(valorInserir);
						System.out.println("conta contabil:"+contabil.getId().toString() + " valor:"+helper.getValorTotal());
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
					
				}
			}
			
			//apartir da colecaoDividaAtiva
			if(colecaoDividaAtiva != null && !colecaoDividaAtiva.isEmpty()){
				Iterator<ResumoReceitaHelper> iteratorDividaAtiva = colecaoDividaAtiva.iterator();
				
				while(iteratorDividaAtiva.hasNext()){
					helper = iteratorDividaAtiva.next();
					
					resumoReceitaInserir = new ResumoReceita();
					resumoReceitaInserir.setAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao());
					
					arrecadador = new Arrecadador();
					arrecadador.setId(helper.getArrecadadorId());
					contaBancaria = new ContaBancaria();
					contaBancaria.setId(helper.getContaBancariaId());
					banco = new Banco();
					banco.setId(helper.getBancoId());
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getGerenciaRegionalId());
					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(helper.getUnidadeNegocioId());
					localidade = new Localidade();
					localidade.setId(helper.getLocalidadeId());
					categoria = new Categoria();
					categoria.setId(helper.getCategoriaId());
					
					resumoReceitaInserir.setArrecadador(arrecadador);
					resumoReceitaInserir.setContaBancaria(contaBancaria);
					resumoReceitaInserir.setBanco(banco);
					resumoReceitaInserir.setGerenciaRegional(gerenciaRegional);
					resumoReceitaInserir.setLocalidade(localidade);
					resumoReceitaInserir.setDataRealizada(helper.getDataRealizacao());
					resumoReceitaInserir.setUltimaAlteracao(new Date());
					resumoReceitaInserir.setCategoria(categoria);
					resumoReceitaInserir.setUnidadeNegocio(unidadeNegocio);
					
					if(helper.getSomaDividaAtiva() != null && !helper.getSomaDividaAtiva().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
						contabil.setId(new Integer(ContaContabil.RECEITA_DIVIDA_ATIVA));
						resumoReceitaInserir.setContaContabil(contabil);
						resumoReceitaInserir.setValorReceita(helper.getValorTotal());
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
					
				}
			}
  	  } catch (ControladorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * [UC 0982] Gerar Resumo da Receita
     * autor: Flávio Cordeiro
     * data: 22/02/2010
     *
     * Este caso de uso gera o resumo da receita aberta e será executado
     * através de um batch
     */
    public void inserirDadosContasOutrosResumoReceita(String dataInicial, String dataFinal, SistemaParametro sistemaParametro){
	  	
  	  try{
  		  
  		  	Collection colecaoGuias = 
				this.pesquisarPagamentoGuiaResumoPagamentoConta(Util.converteStringParaDate(dataInicial),
					Util.converteStringParaDate(dataFinal));
  		  
			Collection colecaoGuiasHistorico = 
				this.pesquisarPagamentoGuiaHistoricoResumoPagamentoConta(Util.converteStringParaDate(dataInicial),
					Util.converteStringParaDate(dataFinal));
			
			Collection colecaoOutrasReceitas = 
				this.pesquisarOutrasReceitasResumoPagamentoConta(Util.converteStringParaDate(dataInicial),
					Util.converteStringParaDate(dataFinal));
			
			Collection colecaoOutrasReceitasHistorico =
				this.pesquisarOutrasReceitasHistoricoResumoPagamentoConta(Util.converteStringParaDate(dataInicial),
					Util.converteStringParaDate(dataFinal));
			
			Collection colecaoNaoClassificado = 
				this.pesquisarPagamentoNaoClassificadoResumoPagamentoConta(Util.converteStringParaDate(dataInicial),
					Util.converteStringParaDate(dataFinal));
			
			Collection colecaoNaoClassificadoHistorico = 
				this.pesquisarPagamentoNaoClassificadoHistoricoResumoPagamentoConta(Util.converteStringParaDate(dataInicial),
					Util.converteStringParaDate(dataFinal));
			
			Collection colecaoDebitoACobrar = 
				this.pesquisarPagamentoDebitoCobrarResumoPagamentoConta(Util.converteStringParaDate(dataInicial),
					Util.converteStringParaDate(dataFinal));
			
			Collection colecaoDebitoACobrarHistorico = 
				this.pesquisarPagamentoDebitoCobrarHistoricoResumoPagamentoConta(Util.converteStringParaDate(dataInicial),
					Util.converteStringParaDate(dataFinal));
			
			Collection colecaoHistoricoSemCorrespondente = 
				this.pesquisarPagamentoHistoricoSemCorrespondenteResumoPagamentoConta(Util.converteStringParaDate(dataInicial),
					Util.converteStringParaDate(dataFinal));
			
			Collection colecaoDevolucaoAvisoBancario = 
				this.pesquisarDevolucaoAvisoBancarioResumo(Util.converteStringParaDate(dataInicial),
					Util.converteStringParaDate(dataFinal));
			
			Collection colecaoDevolucaoAvisoBancarioHistorico = 
				this.pesquisarDevolucaoAvisoBancarioHistoricoResumo(Util.converteStringParaDate(dataInicial),
					Util.converteStringParaDate(dataFinal));
			
			//---INSERIR
			
			ResumoReceitaHelper helper = null;
			ResumoReceita resumoReceitaInserir = null;
			Arrecadador arrecadador = null;
			Banco banco = null;
			ContaBancaria contaBancaria= null;
			GerenciaRegional gerenciaRegional = null;
			UnidadeNegocio unidadeNegocio = null;
			Localidade localidade = null;
			ContaContabil contabil = null;
			Categoria categoria = null;
			
			//apartir
			if(colecaoGuias != null && !colecaoGuias.isEmpty()){
				Iterator<ResumoReceitaHelper> iteratorContas = colecaoGuias.iterator();
				
				while(iteratorContas.hasNext()){
					helper = iteratorContas.next();
					
					resumoReceitaInserir = new ResumoReceita();
					resumoReceitaInserir.setAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao());
					
					arrecadador = new Arrecadador();
					arrecadador.setId(helper.getArrecadadorId());
					contaBancaria = new ContaBancaria();
					contaBancaria.setId(helper.getContaBancariaId());
					banco = new Banco();
					banco.setId(helper.getBancoId());
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getGerenciaRegionalId());
					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(helper.getUnidadeNegocioId());
					localidade = new Localidade();
					localidade.setId(helper.getLocalidadeId());
					categoria = new Categoria();
					categoria.setId(helper.getCategoriaId());
					
					resumoReceitaInserir.setArrecadador(arrecadador);
					resumoReceitaInserir.setContaBancaria(contaBancaria);
					resumoReceitaInserir.setBanco(banco);
					resumoReceitaInserir.setGerenciaRegional(gerenciaRegional);
					resumoReceitaInserir.setLocalidade(localidade);
					resumoReceitaInserir.setDataRealizada(helper.getDataRealizacao());
					resumoReceitaInserir.setUltimaAlteracao(new Date());
					resumoReceitaInserir.setCategoria(categoria);
					resumoReceitaInserir.setUnidadeNegocio(unidadeNegocio);
					
					if(helper.getSomaPagamentoGuia() != null && !helper.getSomaPagamentoGuia().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
						contabil.setId(new Integer(helper.getContaContabil()));
						resumoReceitaInserir.setContaContabil(contabil);
						resumoReceitaInserir.setValorReceita(helper.getSomaPagamentoGuia());
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
				}
			}
			
			if(colecaoGuiasHistorico != null && !colecaoGuiasHistorico.isEmpty()){
				Iterator<ResumoReceitaHelper> iteratorContas = colecaoGuiasHistorico.iterator();
				
				while(iteratorContas.hasNext()){
					helper = iteratorContas.next();
					
					resumoReceitaInserir = new ResumoReceita();
					resumoReceitaInserir.setAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao());
					
					arrecadador = new Arrecadador();
					arrecadador.setId(helper.getArrecadadorId());
					contaBancaria = new ContaBancaria();
					contaBancaria.setId(helper.getContaBancariaId());
					banco = new Banco();
					banco.setId(helper.getBancoId());
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getGerenciaRegionalId());
					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(helper.getUnidadeNegocioId());
					localidade = new Localidade();
					localidade.setId(helper.getLocalidadeId());
					categoria = new Categoria();
					categoria.setId(helper.getCategoriaId());
					
					resumoReceitaInserir.setArrecadador(arrecadador);
					resumoReceitaInserir.setContaBancaria(contaBancaria);
					resumoReceitaInserir.setBanco(banco);
					resumoReceitaInserir.setGerenciaRegional(gerenciaRegional);
					resumoReceitaInserir.setLocalidade(localidade);
					resumoReceitaInserir.setDataRealizada(helper.getDataRealizacao());
					resumoReceitaInserir.setUnidadeNegocio(unidadeNegocio);
					resumoReceitaInserir.setUltimaAlteracao(new Date());
					resumoReceitaInserir.setCategoria(categoria);
					
					if(helper.getSomaPagamentoGuia() != null && !helper.getSomaPagamentoGuia().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
						contabil.setId(new Integer(helper.getContaContabil()));
						resumoReceitaInserir.setContaContabil(contabil);
						resumoReceitaInserir.setValorReceita(helper.getSomaPagamentoGuia());
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
				}
			}
			
			//apartir
			if(colecaoOutrasReceitas != null && !colecaoOutrasReceitas.isEmpty()){
				Iterator<ResumoReceitaHelper> iteratorOutrasReceitas = colecaoOutrasReceitas.iterator();
				
				while(iteratorOutrasReceitas.hasNext()){
					helper = iteratorOutrasReceitas.next();
					
					resumoReceitaInserir = new ResumoReceita();
					resumoReceitaInserir.setAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao());
					
					arrecadador = new Arrecadador();
					arrecadador.setId(helper.getArrecadadorId());
					contaBancaria = new ContaBancaria();
					contaBancaria.setId(helper.getContaBancariaId());
					banco = new Banco();
					banco.setId(helper.getBancoId());
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getGerenciaRegionalId());
					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(helper.getUnidadeNegocioId());
					localidade = new Localidade();
					localidade.setId(helper.getLocalidadeId());
					categoria = new Categoria();
					categoria.setId(helper.getCategoriaId());
					
					resumoReceitaInserir.setArrecadador(arrecadador);
					resumoReceitaInserir.setContaBancaria(contaBancaria);
					resumoReceitaInserir.setBanco(banco);
					resumoReceitaInserir.setGerenciaRegional(gerenciaRegional);
					resumoReceitaInserir.setLocalidade(localidade);
					resumoReceitaInserir.setDataRealizada(helper.getDataRealizacao());
					resumoReceitaInserir.setUltimaAlteracao(new Date());
					resumoReceitaInserir.setCategoria(categoria);
					resumoReceitaInserir.setUnidadeNegocio(unidadeNegocio);
					System.out.println("imovel:" + helper.getImovelId());
					System.out.println("categoria:" + helper.getCategoriaId());
					
					if(helper.getSomaOutrasReceitas() != null && !helper.getSomaOutrasReceitas().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
						
						if (helper.getReceitaIntra().booleanValue()) {
							contabil.setId(new Integer(ContaContabil.OUTRAS_RECEITAS_INTRA));
						} else {
							contabil.setId(new Integer(ContaContabil.OUTRAS_RECEITAS));
						}
						
						resumoReceitaInserir.setContaContabil(contabil);
						resumoReceitaInserir.setValorReceita(helper.getValorTotal());
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
					
				}
			}
			
			//apartir
			if(colecaoOutrasReceitasHistorico != null && !colecaoOutrasReceitasHistorico.isEmpty()){
				Iterator<ResumoReceitaHelper> iteratorOutrasReceitas = colecaoOutrasReceitasHistorico.iterator();
				System.out.println("INICIO OUTRAS RECEITAS HISTORICO\n");
				while(iteratorOutrasReceitas.hasNext()){
					helper = iteratorOutrasReceitas.next();
					
					resumoReceitaInserir = new ResumoReceita();
					resumoReceitaInserir.setAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao());
					
					arrecadador = new Arrecadador();
					arrecadador.setId(helper.getArrecadadorId());
					contaBancaria = new ContaBancaria();
					contaBancaria.setId(helper.getContaBancariaId());
					banco = new Banco();
					banco.setId(helper.getBancoId());
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getGerenciaRegionalId());
					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(helper.getUnidadeNegocioId());
					localidade = new Localidade();
					localidade.setId(helper.getLocalidadeId());
					categoria = new Categoria();
					categoria.setId(helper.getCategoriaId());
					
					resumoReceitaInserir.setArrecadador(arrecadador);
					resumoReceitaInserir.setContaBancaria(contaBancaria);
					resumoReceitaInserir.setBanco(banco);
					resumoReceitaInserir.setGerenciaRegional(gerenciaRegional);
					resumoReceitaInserir.setLocalidade(localidade);
					resumoReceitaInserir.setDataRealizada(helper.getDataRealizacao());
					resumoReceitaInserir.setUltimaAlteracao(new Date());
					resumoReceitaInserir.setCategoria(categoria);
					resumoReceitaInserir.setUnidadeNegocio(unidadeNegocio);
					System.out.println("imovel:" + helper.getImovelId());
					System.out.println("categoria:" + helper.getCategoriaId());
					
					if(helper.getSomaOutrasReceitas() != null && !helper.getSomaOutrasReceitas().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
						contabil.setId(helper.getContaContabil());
						System.out.println("CONTABIL OUTROS HISTORICO: "+contabil.getId());
						resumoReceitaInserir.setContaContabil(contabil);
						resumoReceitaInserir.setValorReceita(helper.getValorTotal());
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
					
				}
			}
			
			//apartir
			if(colecaoNaoClassificado != null && !colecaoNaoClassificado.isEmpty()){
				Iterator<ResumoReceitaHelper> iteratorNaoClassificados = colecaoNaoClassificado.iterator();
				
				while(iteratorNaoClassificados.hasNext()){
					helper = iteratorNaoClassificados.next();
					
					resumoReceitaInserir = new ResumoReceita();
					resumoReceitaInserir.setAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao());
					
					arrecadador = new Arrecadador();
					arrecadador.setId(helper.getArrecadadorId());
					contaBancaria = new ContaBancaria();
					contaBancaria.setId(helper.getContaBancariaId());
					banco = new Banco();
					banco.setId(helper.getBancoId());
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getGerenciaRegionalId());
					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(helper.getUnidadeNegocioId());
					localidade = new Localidade();
					localidade.setId(helper.getLocalidadeId());
					categoria = new Categoria();
					categoria.setId(helper.getCategoriaId());
					
					resumoReceitaInserir.setArrecadador(arrecadador);
					resumoReceitaInserir.setContaBancaria(contaBancaria);
					resumoReceitaInserir.setBanco(banco);
					resumoReceitaInserir.setGerenciaRegional(gerenciaRegional);
					resumoReceitaInserir.setLocalidade(localidade);
					resumoReceitaInserir.setDataRealizada(helper.getDataRealizacao());
					resumoReceitaInserir.setUltimaAlteracao(new Date());
					resumoReceitaInserir.setCategoria(categoria);
					resumoReceitaInserir.setUnidadeNegocio(unidadeNegocio);
					
					if(helper.getSomaPagamentoNaoClassificado() != null && !helper.getSomaPagamentoNaoClassificado().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
						
						if (helper.getReceitaIntra().booleanValue()) {
							contabil.setId(new Integer(ContaContabil.PAGAMENTO_NAO_CLASSIFICADO_INTRA));
						} else {
							contabil.setId(new Integer(ContaContabil.PAGAMENTO_NAO_CLASSIFICADO));
						}
						
						resumoReceitaInserir.setContaContabil(contabil);
						resumoReceitaInserir.setValorReceita(helper.getValorTotal());
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
					
				}
			}
				
//				apartir
			if(colecaoNaoClassificadoHistorico != null && !colecaoNaoClassificadoHistorico.isEmpty()){
				Iterator<ResumoReceitaHelper> iteratorNaoClassificados = colecaoNaoClassificadoHistorico.iterator();
				
				while(iteratorNaoClassificados.hasNext()){
					helper = iteratorNaoClassificados.next();
					
					resumoReceitaInserir = new ResumoReceita();
					resumoReceitaInserir.setAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao());
					
					arrecadador = new Arrecadador();
					arrecadador.setId(helper.getArrecadadorId());
					contaBancaria = new ContaBancaria();
					contaBancaria.setId(helper.getContaBancariaId());
					banco = new Banco();
					banco.setId(helper.getBancoId());
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getGerenciaRegionalId());
					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(helper.getUnidadeNegocioId());
					localidade = new Localidade();
					localidade.setId(helper.getLocalidadeId());
					categoria = new Categoria();
					categoria.setId(helper.getCategoriaId());
					
					resumoReceitaInserir.setArrecadador(arrecadador);
					resumoReceitaInserir.setContaBancaria(contaBancaria);
					resumoReceitaInserir.setBanco(banco);
					resumoReceitaInserir.setGerenciaRegional(gerenciaRegional);
					resumoReceitaInserir.setLocalidade(localidade);
					resumoReceitaInserir.setDataRealizada(helper.getDataRealizacao());
					resumoReceitaInserir.setUltimaAlteracao(new Date());
					resumoReceitaInserir.setCategoria(categoria);
					resumoReceitaInserir.setUnidadeNegocio(unidadeNegocio);
					
					if(helper.getSomaPagamentoNaoClassificado() != null && !helper.getSomaPagamentoNaoClassificado().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
						
						if (helper.getReceitaIntra().booleanValue()) {
							contabil.setId(new Integer(ContaContabil.HISTORICO_PAG_NAO_CLASSIFICADO_INTRA));
						} else {
							contabil.setId(new Integer(ContaContabil.HISTORICO_PAG_NAO_CLASSIFICADO));
						}
						
						resumoReceitaInserir.setContaContabil(contabil);
						resumoReceitaInserir.setValorReceita(helper.getValorTotal());
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
					
				}
			}
			
			//apartir
			if(colecaoDebitoACobrar != null && !colecaoDebitoACobrar.isEmpty()){
				Iterator<ResumoReceitaHelper> iteratorDebitoCobrar = colecaoDebitoACobrar.iterator();
				
				while(iteratorDebitoCobrar.hasNext()){
					helper = iteratorDebitoCobrar.next();
					
					resumoReceitaInserir = new ResumoReceita();
					resumoReceitaInserir.setAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao());
					
					arrecadador = new Arrecadador();
					arrecadador.setId(helper.getArrecadadorId());
					contaBancaria = new ContaBancaria();
					contaBancaria.setId(helper.getContaBancariaId());
					banco = new Banco();
					banco.setId(helper.getBancoId());
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getGerenciaRegionalId());
					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(helper.getUnidadeNegocioId());
					localidade = new Localidade();
					localidade.setId(helper.getLocalidadeId());
					categoria = new Categoria();
					categoria.setId(helper.getCategoriaId());
					
					resumoReceitaInserir.setArrecadador(arrecadador);
					resumoReceitaInserir.setContaBancaria(contaBancaria);
					resumoReceitaInserir.setBanco(banco);
					resumoReceitaInserir.setGerenciaRegional(gerenciaRegional);
					resumoReceitaInserir.setLocalidade(localidade);
					resumoReceitaInserir.setDataRealizada(helper.getDataRealizacao());
					resumoReceitaInserir.setUltimaAlteracao(new Date());
					resumoReceitaInserir.setCategoria(categoria);
					resumoReceitaInserir.setUnidadeNegocio(unidadeNegocio);
					
					if(helper.getSomaPagamentoDebCobrar() != null && !helper.getSomaPagamentoDebCobrar().equals(BigDecimal.ZERO)){
						System.out.println("contaCOntabil:"+ helper.getContaContabil());
						contabil = new ContaContabil();
						contabil.setId(new Integer(helper.getContaContabil()));
						//contabil.setId(new Integer("18"));
						resumoReceitaInserir.setContaContabil(contabil);
						resumoReceitaInserir.setValorReceita(helper.getSomaPagamentoDebCobrar());
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
					
				}
			}
			
//			apartir
			if(colecaoDebitoACobrarHistorico != null && !colecaoDebitoACobrarHistorico.isEmpty()){
				Iterator<ResumoReceitaHelper> iteratorDebitoCobrar = colecaoDebitoACobrarHistorico.iterator();
				
				while(iteratorDebitoCobrar.hasNext()){
					helper = iteratorDebitoCobrar.next();
					
					resumoReceitaInserir = new ResumoReceita();
					resumoReceitaInserir.setAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao());
					
					arrecadador = new Arrecadador();
					arrecadador.setId(helper.getArrecadadorId());
					contaBancaria = new ContaBancaria();
					contaBancaria.setId(helper.getContaBancariaId());
					banco = new Banco();
					banco.setId(helper.getBancoId());
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getGerenciaRegionalId());
					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(helper.getUnidadeNegocioId());
					localidade = new Localidade();
					localidade.setId(helper.getLocalidadeId());
					categoria = new Categoria();
					categoria.setId(helper.getCategoriaId());
					
					resumoReceitaInserir.setArrecadador(arrecadador);
					resumoReceitaInserir.setContaBancaria(contaBancaria);
					resumoReceitaInserir.setBanco(banco);
					resumoReceitaInserir.setGerenciaRegional(gerenciaRegional);
					resumoReceitaInserir.setLocalidade(localidade);
					resumoReceitaInserir.setDataRealizada(helper.getDataRealizacao());
					resumoReceitaInserir.setUltimaAlteracao(new Date());
					resumoReceitaInserir.setCategoria(categoria);
					resumoReceitaInserir.setUnidadeNegocio(unidadeNegocio);
					
					if(helper.getSomaPagamentoDebCobrar() != null && !helper.getSomaPagamentoDebCobrar().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
						contabil.setId(new Integer(helper.getContaContabil()));
						//contabil.setId(new Integer("18"));
						resumoReceitaInserir.setContaContabil(contabil);
						resumoReceitaInserir.setValorReceita(helper.getSomaPagamentoDebCobrar());
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
					
				}
			}
			
//			apartir
			if(colecaoHistoricoSemCorrespondente != null && !colecaoHistoricoSemCorrespondente.isEmpty()){
				Iterator<ResumoReceitaHelper> iteratorSemCorrespondente = colecaoHistoricoSemCorrespondente.iterator();
				
				while(iteratorSemCorrespondente.hasNext()){
					helper = iteratorSemCorrespondente.next();
					
					resumoReceitaInserir = new ResumoReceita();
					resumoReceitaInserir.setAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao());
					
					arrecadador = new Arrecadador();
					arrecadador.setId(helper.getArrecadadorId());
					contaBancaria = new ContaBancaria();
					contaBancaria.setId(helper.getContaBancariaId());
					banco = new Banco();
					banco.setId(helper.getBancoId());
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getGerenciaRegionalId());
					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(helper.getUnidadeNegocioId());
					localidade = new Localidade();
					localidade.setId(helper.getLocalidadeId());
					categoria = new Categoria();
					categoria.setId(helper.getCategoriaId());
					
					resumoReceitaInserir.setArrecadador(arrecadador);
					resumoReceitaInserir.setContaBancaria(contaBancaria);
					resumoReceitaInserir.setBanco(banco);
					resumoReceitaInserir.setGerenciaRegional(gerenciaRegional);
					resumoReceitaInserir.setLocalidade(localidade);
					resumoReceitaInserir.setDataRealizada(helper.getDataRealizacao());
					resumoReceitaInserir.setUltimaAlteracao(new Date());
					resumoReceitaInserir.setCategoria(categoria);
					resumoReceitaInserir.setUnidadeNegocio(unidadeNegocio);
					
					if(helper.getSomaPagamentoHistoricoSemCorrespondente() != null && !helper.getSomaPagamentoHistoricoSemCorrespondente().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
						
						if (helper.getReceitaIntra().booleanValue()) {
							contabil.setId(new Integer(ContaContabil.PAG_HISTORICO_SEM_CORRESPONDENTE_INTRA));
						} else {
							contabil.setId(new Integer(ContaContabil.PAG_HISTORICO_SEM_CORRESPONDENTE));
						}
						
						resumoReceitaInserir.setContaContabil(contabil);
						resumoReceitaInserir.setValorReceita(helper.getValorTotal());
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
					
				}
			}
			
			
			//apartir
			if(colecaoDevolucaoAvisoBancario != null && !colecaoDevolucaoAvisoBancario.isEmpty()){
				Iterator<ResumoReceitaHelper> iteratorDevolucaoAvisoBancario = colecaoDevolucaoAvisoBancario.iterator();
				
				while(iteratorDevolucaoAvisoBancario.hasNext()){
					helper = iteratorDevolucaoAvisoBancario.next();
					
					resumoReceitaInserir = new ResumoReceita();
					resumoReceitaInserir.setAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao());
					
					arrecadador = new Arrecadador();
					arrecadador.setId(helper.getArrecadadorId());
					contaBancaria = new ContaBancaria();
					contaBancaria.setId(helper.getContaBancariaId());
					banco = new Banco();
					banco.setId(helper.getBancoId());
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getGerenciaRegionalId());
					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(helper.getUnidadeNegocioId());
					localidade = new Localidade();
					localidade.setId(helper.getLocalidadeId());
					categoria = new Categoria();
					categoria.setId(helper.getCategoriaId());
					
					resumoReceitaInserir.setArrecadador(arrecadador);
					resumoReceitaInserir.setContaBancaria(contaBancaria);
					resumoReceitaInserir.setBanco(banco);
					resumoReceitaInserir.setGerenciaRegional(gerenciaRegional);
					resumoReceitaInserir.setLocalidade(localidade);
					resumoReceitaInserir.setDataRealizada(helper.getDataRealizacao());
					resumoReceitaInserir.setUltimaAlteracao(new Date());
					resumoReceitaInserir.setCategoria(categoria);
					resumoReceitaInserir.setUnidadeNegocio(unidadeNegocio);
					
					if(helper.getSomaPagamentoNaoClassificado() != null && !helper.getSomaPagamentoNaoClassificado().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
						
						if (helper.getReceitaIntra().booleanValue()) {
							contabil.setId(new Integer(ContaContabil.DEVOLUCAO_AVISO_BANCARIO_INTRA));
						} else {
							contabil.setId(new Integer(ContaContabil.DEVOLUCAO_AVISO_BANCARIO));
						}
						
						resumoReceitaInserir.setContaContabil(contabil);
						resumoReceitaInserir.setValorReceita(new BigDecimal("-"+helper.getValorTotal()));
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
					
				}
			}
			
			
			//apartir
			if(colecaoDevolucaoAvisoBancarioHistorico != null && !colecaoDevolucaoAvisoBancarioHistorico.isEmpty()){
				Iterator<ResumoReceitaHelper> iteratorDevolucaoAvisoBancario = colecaoDevolucaoAvisoBancarioHistorico.iterator();
				
				while(iteratorDevolucaoAvisoBancario.hasNext()){
					helper = iteratorDevolucaoAvisoBancario.next();
					
					resumoReceitaInserir = new ResumoReceita();
					resumoReceitaInserir.setAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao());
					
					arrecadador = new Arrecadador();
					arrecadador.setId(helper.getArrecadadorId());
					contaBancaria = new ContaBancaria();
					contaBancaria.setId(helper.getContaBancariaId());
					banco = new Banco();
					banco.setId(helper.getBancoId());
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getGerenciaRegionalId());
					unidadeNegocio = new UnidadeNegocio();
					unidadeNegocio.setId(helper.getUnidadeNegocioId());
					localidade = new Localidade();
					localidade.setId(helper.getLocalidadeId());
					categoria = new Categoria();
					categoria.setId(helper.getCategoriaId());
					
					resumoReceitaInserir.setArrecadador(arrecadador);
					resumoReceitaInserir.setContaBancaria(contaBancaria);
					resumoReceitaInserir.setBanco(banco);
					resumoReceitaInserir.setGerenciaRegional(gerenciaRegional);
					resumoReceitaInserir.setLocalidade(localidade);
					resumoReceitaInserir.setDataRealizada(helper.getDataRealizacao());
					resumoReceitaInserir.setUltimaAlteracao(new Date());
					resumoReceitaInserir.setCategoria(categoria);
					resumoReceitaInserir.setUnidadeNegocio(unidadeNegocio);
					
					if(helper.getSomaPagamentoNaoClassificado() != null && !helper.getSomaPagamentoNaoClassificado().equals(BigDecimal.ZERO)){
						contabil = new ContaContabil();
						
						if (helper.getReceitaIntra().booleanValue()) {
							contabil.setId(new Integer(ContaContabil.DEVOLUCAO_AVISO_BANCARIO_INTRA));
						} else {
							contabil.setId(new Integer(ContaContabil.DEVOLUCAO_AVISO_BANCARIO));
						}
						
						resumoReceitaInserir.setContaContabil(contabil);
						resumoReceitaInserir.setValorReceita(new BigDecimal("-"+helper.getValorTotal()));
						
						resumoReceitaInserir.setId(null);
						getControladorUtil().inserir(resumoReceitaInserir);
					}
					
				}
			}
			
			
  	  } catch (ControladorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * [UC 0982] Gerar Resumo da Receita
     * 
     * 1.O sistema apaga os dados da tabela RESUMO_RECEITA referente ao Mês/Ano da 
     * arrecadação que está aberta.
     *
     * Autor: Flávio Cordeiro
     */
    public void apagarDadosResumoReceitaMesAnoArrecadacao(){
  	  try {
			
  		SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();
			
			FiltroResumoReceita filtroResumoReceita = new FiltroResumoReceita();
			filtroResumoReceita.adicionarParametro(
					new ParametroSimples(FiltroResumoReceita.ANO_MES_REFERENCIA, 
					sistemaParametro.getAnoMesArrecadacao()));
			
			Collection<ResumoReceita> colecaoResumoReceita = getControladorUtil().pesquisar(filtroResumoReceita, ResumoReceita.class.getName());
			
			if(!colecaoResumoReceita.isEmpty()){
				Iterator iter = colecaoResumoReceita.iterator();
				ResumoReceita resumoReceita = null;
				
				while (iter.hasNext()) {
					resumoReceita = (ResumoReceita)iter.next();
					getControladorUtil().remover(resumoReceita);
				}	
			}
			
		} catch (ControladorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
  /**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarResumoPagamentoConta(dataInicial, dataFinal);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceitaHelper helper = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					objeto = (Object[]) iteratorObjetos.next();
					helper = new ResumoReceitaHelper();
					
					if(objeto[0] != null){
						helper.setGerenciaRegionalId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						helper.setUnidadeNegocioId((Integer)objeto[1]);
					}
					if(objeto[2] != null){
						helper.setLocalidadeId((Integer)objeto[2]);
					}
					if(objeto[3] != null){
						helper.setCategoriaId((Integer)objeto[3]);
					}
					
					if(objeto[4] != null){
						helper.setArrecadadorId((Integer)objeto[4]);
					}
					if(objeto[5] != null){
						helper.setBancoId((Integer)objeto[5]);
					}					
					if(objeto[6] != null){
						helper.setContaBancariaId((Integer)objeto[6]);
					}
					if(objeto[7] != null){
						helper.setDataRealizacao((Timestamp)objeto[7]);
					}
					if(objeto[8] != null){
						helper.setReceitaIntra((Boolean)objeto[8]);
					}
					if(objeto[9] != null){
						helper.setSomaAgua((BigDecimal)objeto[9]);
					}
					if(objeto[10] != null){
						helper.setSomaEsgoto((BigDecimal)objeto[10]);
					}
					
					colecaoRetorno.add(helper);
					
				}
			}
			
			
		} catch (ErroRepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return colecaoRetorno;
	}
	
	 /**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarResumoPagamentoContaCredito(Date dataInicial, Date dataFinal)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarResumoPagamentoContaCredito(dataInicial, dataFinal);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceitaHelper helper = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					objeto = (Object[]) iteratorObjetos.next();
					helper = new ResumoReceitaHelper();
					
					if(objeto[0] != null){
						helper.setGerenciaRegionalId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						helper.setUnidadeNegocioId((Integer)objeto[1]);
					}
					if(objeto[2] != null){
						helper.setLocalidadeId((Integer)objeto[2]);
					}
					if(objeto[3] != null){
						helper.setCategoriaId((Integer)objeto[3]);
					}
					if(objeto[4] != null){
						helper.setArrecadadorId((Integer)objeto[4]);
					}
					if(objeto[5] != null){
						helper.setBancoId((Integer)objeto[5]);
					}					
					if(objeto[6] != null){
						helper.setContaBancariaId((Integer)objeto[6]);
					}
					if(objeto[7] != null){
						helper.setDataRealizacao((Timestamp)objeto[7]);
					}
					if(objeto[8] != null){
						helper.setReceitaIntra((Boolean)objeto[8]);
					}
					if(objeto[9] != null){
						helper.setSomaCredito((BigDecimal)objeto[9]);
					}
					
					colecaoRetorno.add(helper);
					
				}
			}
			
			
		} catch (ErroRepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return colecaoRetorno;
	}
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarResumoHistoricoPagamentoContaCredito(Date dataInicial, Date dataFinal)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarResumoHistoricoPagamentoContaCredito(dataInicial, dataFinal);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceitaHelper helper = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					objeto = (Object[]) iteratorObjetos.next();
					helper = new ResumoReceitaHelper();
					
					if(objeto[0] != null){
						helper.setGerenciaRegionalId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						helper.setUnidadeNegocioId((Integer)objeto[1]);
					}
					if(objeto[2] != null){
						helper.setLocalidadeId((Integer)objeto[2]);
					}
					if(objeto[3] != null){
						helper.setCategoriaId((Integer)objeto[3]);
					}
					if(objeto[4] != null){
						helper.setArrecadadorId((Integer)objeto[4]);
					}
					if(objeto[5] != null){
						helper.setBancoId((Integer)objeto[5]);
					}					
					if(objeto[6] != null){
						helper.setContaBancariaId((Integer)objeto[6]);
					}
					if(objeto[7] != null){
						helper.setDataRealizacao((Timestamp)objeto[7]);
					}
					if(objeto[8] != null){
						helper.setReceitaIntra((Boolean)objeto[8]);
					}
					if(objeto[9] != null){
						helper.setSomaCredito((BigDecimal)objeto[9]);
					}
					
					colecaoRetorno.add(helper);
					
				}
			}
			
			
		} catch (ErroRepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return colecaoRetorno;
	}
	
	
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarResumoPagamentoContaServico(Date dataInicial, Date dataFinal)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarResumoPagamentoContaServico(dataInicial, dataFinal);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceitaHelper helper = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					objeto = (Object[]) iteratorObjetos.next();
					helper = new ResumoReceitaHelper();
					
					if(objeto[0] != null){
						helper.setGerenciaRegionalId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						helper.setUnidadeNegocioId((Integer)objeto[1]);
					}
					if(objeto[2] != null){
						helper.setLocalidadeId((Integer)objeto[2]);
					}
					if(objeto[3] != null){
						helper.setCategoriaId((Integer)objeto[3]);
					}
					if(objeto[4] != null){
						helper.setArrecadadorId((Integer)objeto[4]);
					}
					if(objeto[5] != null){
						helper.setBancoId((Integer)objeto[5]);
					}					
					if(objeto[6] != null){
						helper.setContaBancariaId((Integer)objeto[6]);
					}
					if(objeto[7] != null){
						helper.setDataRealizacao((Timestamp)objeto[7]);
					}
					if(objeto[8] != null){
						helper.setSomaServico((BigDecimal)objeto[8]);
					}
					
					if(objeto[9] != null){
						helper.setContaContabil((Integer)objeto[9]);
					}
					
					colecaoRetorno.add(helper);
					
				}
			}
			
			
		} catch (ErroRepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return colecaoRetorno;
	}
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarResumoHistoricoPagamentoContaServico(Date dataInicial, Date dataFinal)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarResumoHistoricoPagamentoContaServico(dataInicial, dataFinal);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceitaHelper helper = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					objeto = (Object[]) iteratorObjetos.next();
					helper = new ResumoReceitaHelper();
					
					if(objeto[0] != null){
						helper.setGerenciaRegionalId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						helper.setUnidadeNegocioId((Integer)objeto[1]);
					}
					if(objeto[2] != null){
						helper.setLocalidadeId((Integer)objeto[2]);
					}
					if(objeto[3] != null){
						helper.setCategoriaId((Integer)objeto[3]);
					}
					if(objeto[4] != null){
						helper.setArrecadadorId((Integer)objeto[4]);
					}
					if(objeto[5] != null){
						helper.setBancoId((Integer)objeto[5]);
					}					
					if(objeto[6] != null){
						helper.setContaBancariaId((Integer)objeto[6]);
					}
					if(objeto[7] != null){
						helper.setDataRealizacao((Timestamp)objeto[7]);
					}
					if(objeto[8] != null){
						helper.setSomaServico((BigDecimal)objeto[8]);
					}
					if ( objeto[9] != null ){
						helper.setContaContabil((Integer)objeto[9]);
					}
					
					colecaoRetorno.add(helper);
					
				}
			}
			
			
		} catch (ErroRepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return colecaoRetorno;
	}
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarImpostoResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		Collection colecaoAgrupada = new ArrayList();
		
		List<ResumoReceitaHelper> quebra = 
            new ArrayList<ResumoReceitaHelper>();
		
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarImpostoResumoPagamentoConta(dataInicial, dataFinal);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceitaHelper helper = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					objeto = (Object[]) iteratorObjetos.next();
					helper = new ResumoReceitaHelper();
					
					if(objeto[0] != null){
						helper.setGerenciaRegionalId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						helper.setUnidadeNegocioId((Integer)objeto[1]);
					}
					if(objeto[2] != null){
						helper.setLocalidadeId((Integer)objeto[2]);
					}
					if(objeto[3] != null){
						helper.setImovelId((Integer)objeto[3]);
					}
					if(objeto[4] != null){
						helper.setDataRealizacao((Timestamp)objeto[4]);
					}
					if(objeto[5] != null){
						helper.setLocalidadeNome((String)objeto[5]);
					}
					if(objeto[6] != null){
						helper.setSomaImposto((BigDecimal)objeto[6]);
					}
					if(objeto[7] != null){
						helper.setContaBancariaId((Integer)objeto[7]);
					}
					if(objeto[8] != null){
						helper.setBancoId((Integer)objeto[8]);
					}
					if(objeto[9] != null){
						helper.setArrecadadorId((Integer)objeto[9]);
					}
					if(objeto[10] != null){
						helper.setReceitaIntra((Boolean)objeto[10]);
					}
					
					//dividir valores por categoria
					if(objeto[6] != null){
						Collection<ImovelSubcategoria> colecaoImovelSubCategoria = new ArrayList();
						if(objeto[3] != null && !objeto[3].equals("")){
							colecaoImovelSubCategoria = 
								getControladorImovel().pesquisarCategoriasImovel((Integer)objeto[3]);
						}else{
							ImovelSubcategoria imovelSubcategoria = new ImovelSubcategoria();
							ImovelSubcategoriaPK imovelSubcategoriaPK = new ImovelSubcategoriaPK();
							Subcategoria subcategoria = new Subcategoria();
							Categoria categoria = new Categoria();

							categoria.setId(Categoria.RESIDENCIAL);
							categoria.setQuantidadeEconomiasCategoria(1);
							subcategoria.setCategoria(categoria);
							imovelSubcategoriaPK.setSubcategoria(subcategoria);
							imovelSubcategoria.setComp_id(imovelSubcategoriaPK);
							
							colecaoImovelSubCategoria.add(imovelSubcategoria);
						}
						
						BigDecimal valorCategoria = BigDecimal.ZERO;
						if(!colecaoImovelSubCategoria.isEmpty()){
							Iterator iteratorSubCategoria = colecaoImovelSubCategoria.iterator();
							Collection colecaoCategoria = new ArrayList();
							while(iteratorSubCategoria.hasNext()){
								ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria)iteratorSubCategoria.next();
								Categoria categoria = imovelSubcategoria.getComp_id().getSubcategoria().getCategoria();
								colecaoCategoria.add(categoria);
							}
							
							Collection colecaoValores = getControladorImovel().obterValorPorCategoria(
									colecaoCategoria, helper.getSomaImposto());
							
							Iterator iteratorCategoria = colecaoCategoria.iterator();
							Categoria categoriaImovel = null;
							
							ResumoReceitaHelper resumoHelper = null;
							
							Iterator iteValorCategoria = colecaoValores.iterator();
							
							while(iteratorCategoria.hasNext() && iteValorCategoria.hasNext()){
								categoriaImovel = (Categoria)iteratorCategoria.next();
								
//								valorCategoria = (BigDecimal)colecaoValores.iterator().next();
								valorCategoria = (BigDecimal)iteValorCategoria.next();
								resumoHelper = new ResumoReceitaHelper(helper);
								resumoHelper.setCategoriaId(categoriaImovel.getId());
								resumoHelper.setSomaImposto(valorCategoria);
																
								colecaoRetorno.add(resumoHelper);
								
							}
						}
					}
					
				}
				if ( colecaoRetorno != null && !colecaoRetorno.isEmpty() ){
					
					Iterator iteColRetorno = colecaoRetorno.iterator();
												
					while ( iteColRetorno.hasNext() ){
						
						ResumoReceitaHelper resumoHelper = (ResumoReceitaHelper)iteColRetorno.next();
						
						// Verificamos se o objeto ja possue uma quebra cadastrada
		                if (quebra.contains(resumoHelper)) {
		                    int posicao = quebra.indexOf(resumoHelper);
		                    ResumoReceitaHelper jaCadastrado = (ResumoReceitaHelper) quebra.get(posicao);
		                    
		                    jaCadastrado.setValorTotal(jaCadastrado.getValorTotal().add(resumoHelper.retornaValorTotal()));
		                    
		                } else {
		                	
		                	
		                	resumoHelper.setValorTotal(resumoHelper.retornaValorTotal());
		                	
		                    quebra.add(resumoHelper);
		                    
		                }
						
						//--------------------------------------
						
					}
					
				}
//			Agrupar Valores
//				colecaoAgrupada = agruparDadosColecaoResumoreceita(colecaoRetorno);
				
				colecaoAgrupada = quebra;
			}
			
			
		} catch (ErroRepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return colecaoAgrupada;
	}
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarImpostoHistoricoResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		Collection colecaoAgrupada = new ArrayList();
		
		List<ResumoReceitaHelper> quebra = 
            new ArrayList<ResumoReceitaHelper>();
		
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarImpostoHistoricoResumoPagamentoConta(dataInicial, dataFinal);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceitaHelper helper = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					objeto = (Object[]) iteratorObjetos.next();
					helper = new ResumoReceitaHelper();
					
					if(objeto[0] != null){
						helper.setGerenciaRegionalId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						helper.setUnidadeNegocioId((Integer)objeto[1]);
					}
					if(objeto[2] != null){
						helper.setLocalidadeId((Integer)objeto[2]);
					}
					if(objeto[3] != null){
						helper.setImovelId((Integer)objeto[3]);
					}
					if(objeto[4] != null){
						helper.setDataRealizacao((Timestamp)objeto[4]);
					}
					if(objeto[5] != null){
						helper.setLocalidadeNome((String)objeto[5]);
					}
					if(objeto[6] != null){
						helper.setSomaImposto((BigDecimal)objeto[6]);
					}
					if(objeto[7] != null){
						helper.setContaBancariaId((Integer)objeto[7]);
					}
					if(objeto[8] != null){
						helper.setBancoId((Integer)objeto[8]);
					}
					if(objeto[9] != null){
						helper.setArrecadadorId((Integer)objeto[9]);
					}
					if(objeto[10] != null){
						helper.setReceitaIntra((Boolean)objeto[10]);
					}
					
					//dividir valores por categoria
					if(helper.getSomaImposto() != null && objeto[3] != null){
						Collection<ImovelSubcategoria> colecaoImovelSubCategoria = new ArrayList();
						if(objeto[3] != null && !objeto[3].equals("")){
							colecaoImovelSubCategoria = 
								getControladorImovel().pesquisarCategoriasImovel((Integer)objeto[3]);
						}else{
							ImovelSubcategoria imovelSubcategoria = new ImovelSubcategoria();
							ImovelSubcategoriaPK imovelSubcategoriaPK = new ImovelSubcategoriaPK();
							Subcategoria subcategoria = new Subcategoria();
							Categoria categoria = new Categoria();

							categoria.setId(Categoria.RESIDENCIAL);
							categoria.setQuantidadeEconomiasCategoria(1);
							subcategoria.setCategoria(categoria);
							imovelSubcategoriaPK.setSubcategoria(subcategoria);
							imovelSubcategoria.setComp_id(imovelSubcategoriaPK);
							
							colecaoImovelSubCategoria.add(imovelSubcategoria);
						}
						
						BigDecimal valorCategoria = BigDecimal.ZERO;
						if(!colecaoImovelSubCategoria.isEmpty()){
							Iterator iteratorSubCategoria = colecaoImovelSubCategoria.iterator();
							Collection colecaoCategoria = new ArrayList();
							while(iteratorSubCategoria.hasNext()){
								ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria)iteratorSubCategoria.next();
								Categoria categoria = imovelSubcategoria.getComp_id().getSubcategoria().getCategoria();
								colecaoCategoria.add(categoria);
							}
							
							Collection colecaoValores = getControladorImovel().obterValorPorCategoria(
									colecaoCategoria, helper.getSomaImposto());
							
							Iterator iteratorCategoria = colecaoCategoria.iterator();
							Categoria categoriaImovel = null;
							
							ResumoReceitaHelper resumoHelper = null;
							
							Iterator iteValorCategoria = colecaoValores.iterator();
							
							while(iteratorCategoria.hasNext() && iteValorCategoria.hasNext()){
								categoriaImovel = (Categoria)iteratorCategoria.next();
								
//								valorCategoria = (BigDecimal)colecaoValores.iterator().next();
								valorCategoria = (BigDecimal)iteValorCategoria.next();
								resumoHelper = new ResumoReceitaHelper(helper);
								resumoHelper.setCategoriaId(categoriaImovel.getId());
								resumoHelper.setSomaImposto(valorCategoria);
								
								colecaoRetorno.add(resumoHelper);
								
							}
						}
					}
					
				}
				if ( colecaoRetorno != null && !colecaoRetorno.isEmpty() ){
					
					Iterator iteColRetorno = colecaoRetorno.iterator();
												
					while ( iteColRetorno.hasNext() ){
						
						ResumoReceitaHelper resumoHelper = (ResumoReceitaHelper)iteColRetorno.next();
						
						// Verificamos se o objeto ja possue uma quebra cadastrada
		                if (quebra.contains(resumoHelper)) {
		                    int posicao = quebra.indexOf(resumoHelper);
		                    ResumoReceitaHelper jaCadastrado = (ResumoReceitaHelper) quebra.get(posicao);
		                    
		                    jaCadastrado.setValorTotal(jaCadastrado.getValorTotal().add(resumoHelper.retornaValorTotal()));
		                    
		                } else {
		                	
		                	
		                	resumoHelper.setValorTotal(resumoHelper.retornaValorTotal());
		                	
		                    quebra.add(resumoHelper);
		                    
		                }
						
						//--------------------------------------
						
					}
					
				}
//			Agrupar Valores
//				colecaoAgrupada = agruparDadosColecaoResumoreceita(colecaoRetorno);
				
				colecaoAgrupada = quebra;

			}
			
			
		} catch (ErroRepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return colecaoAgrupada;
	}
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarDividaAtivaResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		Collection colecaoAgrupada = new ArrayList();
		
		List<ResumoReceitaHelper> quebra = 
            new ArrayList<ResumoReceitaHelper>();
		
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarDividaAtivaResumoPagamentoConta(dataInicial, dataFinal);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceitaHelper helper = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					objeto = (Object[]) iteratorObjetos.next();
					helper = new ResumoReceitaHelper();
					
					if(objeto[0] != null){
						helper.setGerenciaRegionalId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						helper.setUnidadeNegocioId((Integer)objeto[1]);
					}
					if(objeto[2] != null){
						helper.setLocalidadeId((Integer)objeto[2]);
					}
					if(objeto[3] != null){
						helper.setImovelId((Integer)objeto[3]);
					}
					if(objeto[4] != null){
						helper.setDataRealizacao((Timestamp)objeto[4]);
					}
					if(objeto[5] != null){
						helper.setSomaDividaAtiva((BigDecimal)objeto[5]);
					}
					if(objeto[6] != null){
						helper.setContaBancariaId((Integer)objeto[6]);
					}
					if(objeto[7] != null){
						helper.setBancoId((Integer)objeto[7]);
					}
					if(objeto[8] != null){
						helper.setArrecadadorId((Integer)objeto[8]);
					}
					
					//dividir valores por categoria
					if(objeto[6] != null){
						Collection<ImovelSubcategoria> colecaoImovelSubCategoria = new ArrayList();
						if(objeto[3] != null && !objeto[3].equals("")){
							colecaoImovelSubCategoria = 
								getControladorImovel().pesquisarCategoriasImovel((Integer)objeto[3]);
						}else{
							ImovelSubcategoria imovelSubcategoria = new ImovelSubcategoria();
							ImovelSubcategoriaPK imovelSubcategoriaPK = new ImovelSubcategoriaPK();
							Subcategoria subcategoria = new Subcategoria();
							Categoria categoria = new Categoria();

							categoria.setId(Categoria.RESIDENCIAL);
							categoria.setQuantidadeEconomiasCategoria(1);
							subcategoria.setCategoria(categoria);
							imovelSubcategoriaPK.setSubcategoria(subcategoria);
							imovelSubcategoria.setComp_id(imovelSubcategoriaPK);
							
							colecaoImovelSubCategoria.add(imovelSubcategoria);
						}
						
						BigDecimal valorCategoria = BigDecimal.ZERO;
						if(!colecaoImovelSubCategoria.isEmpty()){
							Iterator iteratorSubCategoria = colecaoImovelSubCategoria.iterator();
							Collection colecaoCategoria = new ArrayList();
							while(iteratorSubCategoria.hasNext()){
								ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria)iteratorSubCategoria.next();
								Categoria categoria = imovelSubcategoria.getComp_id().getSubcategoria().getCategoria();
								colecaoCategoria.add(categoria);
							}
							Collection colecaoValores = getControladorImovel().obterValorPorCategoria(
									colecaoCategoria, helper.getSomaDividaAtiva());
							
							Iterator iteratorCategoria = colecaoCategoria.iterator();
							Categoria categoriaImovel = null;
							
							ResumoReceitaHelper resumoHelper = null;
							
							Iterator iteValorCategoria = colecaoValores.iterator();
							
							while(iteratorCategoria.hasNext() && iteValorCategoria.hasNext()){
								categoriaImovel = (Categoria)iteratorCategoria.next();
								
//								valorCategoria = (BigDecimal)colecaoValores.iterator().next();
								valorCategoria = (BigDecimal)iteValorCategoria.next();
								resumoHelper = new ResumoReceitaHelper(helper);
								resumoHelper.setCategoriaId(categoriaImovel.getId());
								resumoHelper.setSomaDividaAtiva(valorCategoria);
								
								colecaoRetorno.add(resumoHelper);
								
							}
						}
					}
					
				}
				
				if ( colecaoRetorno != null && !colecaoRetorno.isEmpty() ){
					
					Iterator iteColRetorno = colecaoRetorno.iterator();
												
					while ( iteColRetorno.hasNext() ){
						
						ResumoReceitaHelper resumoHelper = (ResumoReceitaHelper)iteColRetorno.next();
						
						// Verificamos se o objeto ja possue uma quebra cadastrada
		                if (quebra.contains(resumoHelper)) {
		                    int posicao = quebra.indexOf(resumoHelper);
		                    ResumoReceitaHelper jaCadastrado = (ResumoReceitaHelper) quebra.get(posicao);
		                    
		                    jaCadastrado.setValorTotal(jaCadastrado.getValorTotal().add(resumoHelper.retornaValorTotal()));
		                    
		                } else {
		                	
		                	
		                	resumoHelper.setValorTotal(resumoHelper.retornaValorTotal());
		                	
		                    quebra.add(resumoHelper);
		                    
		                }
						
						//--------------------------------------
						
					}
					
				}
//			Agrupar Valores
//				colecaoAgrupada = agruparDadosColecaoResumoreceita(colecaoRetorno);
				
				colecaoAgrupada = quebra;
				
			}
			
			
		} catch (ErroRepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return colecaoAgrupada;
	}
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarDividaAtivaHistoricoResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		Collection colecaoAgrupada = new ArrayList();
		
		List<ResumoReceitaHelper> quebra = 
            new ArrayList<ResumoReceitaHelper>();
		
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarDividaAtivaHistoricoResumoPagamentoConta(dataInicial, dataFinal);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceitaHelper helper = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					objeto = (Object[]) iteratorObjetos.next();
					helper = new ResumoReceitaHelper();
					
					if(objeto[0] != null){
						helper.setGerenciaRegionalId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						helper.setUnidadeNegocioId((Integer)objeto[1]);
					}
					if(objeto[2] != null){
						helper.setLocalidadeId((Integer)objeto[2]);
					}
					if(objeto[3] != null){
						helper.setImovelId((Integer)objeto[3]);
					}
					if(objeto[4] != null){
						helper.setDataRealizacao((Timestamp)objeto[4]);
					}
					if(objeto[5] != null){
						helper.setSomaDividaAtiva((BigDecimal)objeto[5]);
					}
					if(objeto[6] != null){
						helper.setContaBancariaId((Integer)objeto[6]);
					}
					if(objeto[7] != null){
						helper.setBancoId((Integer)objeto[7]);
					}
					if(objeto[8] != null){
						helper.setArrecadadorId((Integer)objeto[8]);
					}
					
					//dividir valores por categoria
					if(objeto[5] != null && objeto[3] != null){
						
						Collection<ImovelSubcategoria> colecaoImovelSubCategoria = new ArrayList();
						if(objeto[3] != null && !objeto[3].equals("")){
							colecaoImovelSubCategoria = 
								getControladorImovel().pesquisarCategoriasImovel((Integer)objeto[3]);
						}else{
							ImovelSubcategoria imovelSubcategoria = new ImovelSubcategoria();
							ImovelSubcategoriaPK imovelSubcategoriaPK = new ImovelSubcategoriaPK();
							Subcategoria subcategoria = new Subcategoria();
							Categoria categoria = new Categoria();

							categoria.setId(Categoria.RESIDENCIAL);
							categoria.setQuantidadeEconomiasCategoria(1);
							subcategoria.setCategoria(categoria);
							imovelSubcategoriaPK.setSubcategoria(subcategoria);
							imovelSubcategoria.setComp_id(imovelSubcategoriaPK);
							
							colecaoImovelSubCategoria.add(imovelSubcategoria);
						}
						
						BigDecimal valorCategoria = BigDecimal.ZERO;
						if(!colecaoImovelSubCategoria.isEmpty()){
							Iterator iteratorSubCategoria = colecaoImovelSubCategoria.iterator();
							Collection colecaoCategoria = new ArrayList();
							while(iteratorSubCategoria.hasNext()){
								ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria)iteratorSubCategoria.next();
								Categoria categoria = imovelSubcategoria.getComp_id().getSubcategoria().getCategoria();
								colecaoCategoria.add(categoria);
							}
							Collection colecaoValores = getControladorImovel().obterValorPorCategoria(
									colecaoCategoria, (BigDecimal)objeto[5]);
							
							Iterator iteratorCategoria = colecaoCategoria.iterator();
							Categoria categoriaImovel = null;
							
							ResumoReceitaHelper resumoHelper = null;
							
							Iterator iteValorCategoria = colecaoValores.iterator();
							
							while(iteratorCategoria.hasNext() && iteValorCategoria.hasNext()){
								categoriaImovel = (Categoria)iteratorCategoria.next();
								
//								valorCategoria = (BigDecimal)colecaoValores.iterator().next();
								valorCategoria = (BigDecimal)iteValorCategoria.next();
								resumoHelper = new ResumoReceitaHelper(helper);
								resumoHelper.setCategoriaId(categoriaImovel.getId());
								resumoHelper.setSomaDividaAtiva(valorCategoria);
								resumoHelper.setCategoriaId(categoriaImovel.getId());
								
								colecaoRetorno.add(resumoHelper);
								
							}
						}
					}
					
				}
				
				if ( colecaoRetorno != null && !colecaoRetorno.isEmpty() ){
					
					Iterator iteColRetorno = colecaoRetorno.iterator();
												
					while ( iteColRetorno.hasNext() ){
						
						ResumoReceitaHelper resumoHelper = (ResumoReceitaHelper)iteColRetorno.next();
						
						// Verificamos se o objeto ja possue uma quebra cadastrada
		                if (quebra.contains(resumoHelper)) {
		                    int posicao = quebra.indexOf(resumoHelper);
		                    ResumoReceitaHelper jaCadastrado = (ResumoReceitaHelper) quebra.get(posicao);
		                    
		                    jaCadastrado.setValorTotal(jaCadastrado.getValorTotal().add(resumoHelper.retornaValorTotal()));
		                    
		                } else {
		                	
		                	
		                	resumoHelper.setValorTotal(resumoHelper.retornaValorTotal());
		                	
		                    quebra.add(resumoHelper);
		                    
		                }
						
						//--------------------------------------
						
					}
					
				}
//			Agrupar Valores
//				colecaoAgrupada = agruparDadosColecaoResumoreceita(colecaoRetorno);
				
				colecaoAgrupada = quebra;
				
			}
			
			
		} catch (ErroRepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return colecaoAgrupada;
	}
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarResumoHistoricoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarResumoHistoricoPagamentoConta(dataInicial, dataFinal);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceitaHelper helper = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					objeto = (Object[]) iteratorObjetos.next();
					helper = new ResumoReceitaHelper();
					
					if(objeto[0] != null){
						helper.setGerenciaRegionalId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						helper.setUnidadeNegocioId((Integer)objeto[1]);
					}
					if(objeto[2] != null){
						helper.setLocalidadeId((Integer)objeto[2]);
					}
					if(objeto[3] != null){
						helper.setCategoriaId((Integer)objeto[3]);
					}
					
					if(objeto[4] != null){
						helper.setArrecadadorId((Integer)objeto[4]);
					}
					if(objeto[5] != null){
						helper.setContaBancariaId((Integer)objeto[5]);
					}
					if(objeto[6] != null){
						helper.setBancoId((Integer)objeto[6]);
					}					
					if(objeto[7] != null){
						helper.setDataRealizacao((Timestamp)objeto[7]);
					}
					if(objeto[8] != null){
						helper.setReceitaIntra((Boolean)objeto[8]);
					}
					if(objeto[9] != null){
						helper.setSomaAgua((BigDecimal)objeto[9]);
					}
					if(objeto[10] != null){
						helper.setSomaEsgoto((BigDecimal)objeto[10]);
					}
					
					colecaoRetorno.add(helper);
					
				}
			}
			
			
		} catch (ErroRepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return colecaoRetorno;
	}
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarOutrasReceitasResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		Collection colecaoAgrupada = new ArrayList();
		
		List<ResumoReceitaHelper> quebra = 
            new ArrayList<ResumoReceitaHelper>();
		
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarOutrasReceitasResumoPagamentoConta(dataInicial, dataFinal);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceitaHelper helper = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					objeto = (Object[]) iteratorObjetos.next();
					helper = new ResumoReceitaHelper();
					
					if(objeto[0] != null){
						helper.setGerenciaRegionalId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						helper.setUnidadeNegocioId((Integer)objeto[1]);
					}
					if(objeto[2] != null){
						helper.setLocalidadeId((Integer)objeto[2]);
					}
					if(objeto[3] != null){
						helper.setImovelId((Integer)objeto[3]);
					}
					if(objeto[4] != null){
						helper.setDataRealizacao((Timestamp)objeto[4]);
					}
					if(objeto[5] != null){
						helper.setSomaOutrasReceitas((BigDecimal)objeto[5]);
					}
					if(objeto[6] != null){
						helper.setContaBancariaId((Integer)objeto[6]);
					}
					if(objeto[7] != null){
						helper.setBancoId((Integer)objeto[7]);
					}
					if(objeto[8] != null){
						helper.setArrecadadorId((Integer)objeto[8]);
					}
					if(objeto[9] != null){
						helper.setCategoriaId((Integer)objeto[9]);
					}
					if(objeto[10] != null){
						helper.setReceitaIntra((Boolean)objeto[10]);
					}
					
					//dividir valores por categoria
					if(objeto[5] != null && objeto[3] != null){
						Collection<ImovelSubcategoria> colecaoImovelSubCategoria = new ArrayList();
						if(objeto[3] != null && !objeto[3].equals("")){
							colecaoImovelSubCategoria = 
								getControladorImovel().pesquisarCategoriasImovel((Integer)objeto[3]);
						}else{
							ImovelSubcategoria imovelSubcategoria = new ImovelSubcategoria();
							ImovelSubcategoriaPK imovelSubcategoriaPK = new ImovelSubcategoriaPK();
							Subcategoria subcategoria = new Subcategoria();
							Categoria categoria = new Categoria();

							categoria.setId(Categoria.RESIDENCIAL);
							categoria.setQuantidadeEconomiasCategoria(1);
							subcategoria.setCategoria(categoria);
							imovelSubcategoriaPK.setSubcategoria(subcategoria);
							imovelSubcategoria.setComp_id(imovelSubcategoriaPK);
							
							colecaoImovelSubCategoria.add(imovelSubcategoria);
						}
						
						BigDecimal valorCategoria = BigDecimal.ZERO;
						if(!colecaoImovelSubCategoria.isEmpty()){
							Iterator iteratorSubCategoria = colecaoImovelSubCategoria.iterator();
							Collection colecaoCategoria = new ArrayList();
							while(iteratorSubCategoria.hasNext()){
								ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria)iteratorSubCategoria.next();
								Categoria categoria = imovelSubcategoria.getComp_id().getSubcategoria().getCategoria();
								colecaoCategoria.add(categoria);
							}
							Collection colecaoValores = getControladorImovel().obterValorPorCategoria(
									colecaoCategoria, (BigDecimal)objeto[5]);
							
							Iterator iteratorCategoria = colecaoCategoria.iterator();
							Categoria categoriaImovel = null;
							
							ResumoReceitaHelper resumoHelper = null;
							
							Iterator iteValorCategoria = colecaoValores.iterator();
							
							while(iteratorCategoria.hasNext() && iteValorCategoria.hasNext()){
								categoriaImovel = (Categoria)iteratorCategoria.next();
								
//								valorCategoria = (BigDecimal)colecaoValores.iterator().next();
								valorCategoria = (BigDecimal)iteValorCategoria.next();
								resumoHelper = new ResumoReceitaHelper(helper);
								resumoHelper.setCategoriaId(categoriaImovel.getId());
								resumoHelper.setSomaOutrasReceitas(valorCategoria);
								
								colecaoRetorno.add(resumoHelper);
								
							}
						}
					}
					
				}
				
				if ( colecaoRetorno != null && !colecaoRetorno.isEmpty() ){
					
					Iterator iteColRetorno = colecaoRetorno.iterator();
												
					while ( iteColRetorno.hasNext() ){
						
						ResumoReceitaHelper resumoHelper = (ResumoReceitaHelper)iteColRetorno.next();
						
						// Verificamos se o objeto ja possue uma quebra cadastrada
		                if (quebra.contains(resumoHelper)) {
		                    int posicao = quebra.indexOf(resumoHelper);
		                    ResumoReceitaHelper jaCadastrado = (ResumoReceitaHelper) quebra.get(posicao);
		                    
		                    jaCadastrado.setValorTotal(jaCadastrado.getValorTotal().add(resumoHelper.retornaValorTotal()));
		                    
		                } else {
		                	
		                	
		                	resumoHelper.setValorTotal(resumoHelper.retornaValorTotal());
		                	
		                    quebra.add(resumoHelper);
		                    
		                }
						
						//--------------------------------------
						
					}
					
				}
//			Agrupar Valores
//				colecaoAgrupada = agruparDadosColecaoResumoreceita(colecaoRetorno);
				
				colecaoAgrupada = quebra;
			}
			
			
		} catch (ErroRepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return colecaoAgrupada;
	}
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarPagamentoGuiaResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarPagamentoGuiaResumoPagamentoConta(dataInicial, dataFinal);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceitaHelper helper = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					objeto = (Object[]) iteratorObjetos.next();
					helper = new ResumoReceitaHelper();
					
					if(objeto[0] != null){
						helper.setGerenciaRegionalId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						helper.setUnidadeNegocioId((Integer)objeto[1]);
					}
					if(objeto[2] != null){
						helper.setLocalidadeId((Integer)objeto[2]);
					}
					if(objeto[3] != null){
						helper.setCategoriaId((Integer)objeto[3]);
					}
					if(objeto[4] != null){
						helper.setDataRealizacao((Timestamp)objeto[4]);
					}
					if(objeto[5] != null){
						helper.setSomaPagamentoGuia((BigDecimal)objeto[5]);
					}
					if(objeto[6] != null){
						helper.setContaBancariaId((Integer)objeto[6]);
					}
					if(objeto[7] != null){
						helper.setBancoId((Integer)objeto[7]);
					}
					if(objeto[8] != null){
						helper.setArrecadadorId((Integer)objeto[8]);
					}
					if(objeto[9] != null){
						helper.setContaContabil((Integer)objeto[9]);
					}
					
					colecaoRetorno.add(helper);
								
				}

			}
			
			
		} catch (ErroRepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return colecaoRetorno;
	}
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarPagamentoGuiaHistoricoResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarPagamentoGuiaHistoricoResumoPagamentoConta(dataInicial, dataFinal);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceitaHelper helper = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					objeto = (Object[]) iteratorObjetos.next();
					helper = new ResumoReceitaHelper();
					
					if(objeto[0] != null){
						helper.setGerenciaRegionalId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						helper.setUnidadeNegocioId((Integer)objeto[1]);
					}
					if(objeto[2] != null){
						helper.setLocalidadeId((Integer)objeto[2]);
					}
					if(objeto[3] != null){
						helper.setCategoriaId((Integer)objeto[3]);
					}
					if(objeto[4] != null){
						helper.setDataRealizacao((Timestamp)objeto[4]);
					}
					if(objeto[5] != null){
						helper.setSomaPagamentoGuia((BigDecimal)objeto[5]);
					}
					if(objeto[6] != null){
						helper.setContaBancariaId((Integer)objeto[6]);
					}
					if(objeto[7] != null){
						helper.setBancoId((Integer)objeto[7]);
					}
					if(objeto[8] != null){
						helper.setArrecadadorId((Integer)objeto[8]);
					}
					
					if (objeto[9] != null){
						helper.setContaContabil((Integer)objeto[9]);
					}
					
					colecaoRetorno.add(helper);
								
				}

			}
			
			
		} catch (ErroRepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return colecaoRetorno;
	}
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarPagamentoNaoClassificadoResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		Collection colecaoAgrupada = new ArrayList();
		
		List<ResumoReceitaHelper> quebra = 
            new ArrayList<ResumoReceitaHelper>();
		
		//------------------------------------------
		
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarPagamentoNaoClassificadoResumoPagamentoConta(dataInicial, dataFinal);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceitaHelper helper = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					
//					ResumoReceitaHelper resumoHelper = null;
					
					objeto = (Object[]) iteratorObjetos.next();
					helper = new ResumoReceitaHelper();
					
					if(objeto[0] != null){
						helper.setGerenciaRegionalId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						helper.setUnidadeNegocioId((Integer)objeto[1]);
					}
					if(objeto[2] != null){
						helper.setLocalidadeId((Integer)objeto[2]);
					}
					if(objeto[3] != null && !objeto[3].equals("")){
						helper.setImovelId((Integer)objeto[3]);
					}
					if(objeto[4] != null){
						helper.setDataRealizacao((Timestamp)objeto[4]);
					}
					if(objeto[5] != null){
						helper.setSomaPagamentoNaoClassificado((BigDecimal)objeto[5]);
					}
					if(objeto[6] != null){
						helper.setContaBancariaId((Integer)objeto[6]);
					}
					if(objeto[7] != null){
						helper.setBancoId((Integer)objeto[7]);
					}
					if(objeto[8] != null){
						helper.setArrecadadorId((Integer)objeto[8]);
					}
					if(objeto[9] != null){
						helper.setReceitaIntra((Boolean)objeto[9]);
					}
					helper.setCategoriaId(Categoria.RESIDENCIAL);
					
//					dividir valores por categoria
					if(objeto[5] != null){
						Collection<ImovelSubcategoria> colecaoImovelSubCategoria = new ArrayList();
						if(helper.getImovelId() != null && !helper.getImovelId().equals("")){
							//System.out.println("Imovel:"+ helper.getImovelId());
							colecaoImovelSubCategoria = 
								getControladorImovel().pesquisarCategoriasImovel(helper.getImovelId());
						}else{
							ImovelSubcategoria imovelSubcategoria = new ImovelSubcategoria();
							ImovelSubcategoriaPK imovelSubcategoriaPK = new ImovelSubcategoriaPK();
							Subcategoria subcategoria = new Subcategoria();
							Categoria categoria = new Categoria();

							categoria.setId(Categoria.RESIDENCIAL);
							categoria.setQuantidadeEconomiasCategoria(1);
							subcategoria.setCategoria(categoria);
							imovelSubcategoriaPK.setSubcategoria(subcategoria);
							imovelSubcategoria.setComp_id(imovelSubcategoriaPK);
							
							colecaoImovelSubCategoria.add(imovelSubcategoria);
						}
												
						BigDecimal valorCategoria = BigDecimal.ZERO;
						if(!colecaoImovelSubCategoria.isEmpty()){
							Iterator iteratorSubCategoria = colecaoImovelSubCategoria.iterator();
							Collection colecaoCategoria = new ArrayList();
							while(iteratorSubCategoria.hasNext()){
								ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria)iteratorSubCategoria.next();
								Categoria categoria = imovelSubcategoria.getComp_id().getSubcategoria().getCategoria();
								colecaoCategoria.add(categoria);
							}
							Collection colecaoValores = getControladorImovel().obterValorPorCategoria(
									colecaoCategoria, (BigDecimal)objeto[5]);
							
							Iterator iteratorCategoria = colecaoCategoria.iterator();
							Categoria categoriaImovel = null;
							
							ResumoReceitaHelper resumoHelper = null;
							
							System.out.println("=========================================================");
							System.out.println("INICIO DE PAGAMENTO NAO CLASSIFICADOS");
							System.out.println("========================================================\n");
							System.out.println("Valor total: "+(BigDecimal)objeto[5]);
							
							Iterator iteValorCategoria = colecaoValores.iterator();
							
							while(iteratorCategoria.hasNext() && iteValorCategoria.hasNext()){
								categoriaImovel = (Categoria)iteratorCategoria.next();
								
//								valorCategoria = (BigDecimal)colecaoValores.iterator().next();
								valorCategoria = (BigDecimal)iteValorCategoria.next();
								resumoHelper = new ResumoReceitaHelper(helper);
								resumoHelper.setCategoriaId(categoriaImovel.getId());
								resumoHelper.setSomaPagamentoNaoClassificado(valorCategoria);
								
								
								System.out.println("Valor Categoria: "+valorCategoria);
								
								colecaoRetorno.add(resumoHelper);
								
							}
							
							System.out.println("=============================================================");
							
						}
					}
					
				}
				
				if ( colecaoRetorno != null && !colecaoRetorno.isEmpty() ){
				
					Iterator iteColRetorno = colecaoRetorno.iterator();
												
					while ( iteColRetorno.hasNext() ){
						
						ResumoReceitaHelper resumoHelper = (ResumoReceitaHelper)iteColRetorno.next();
						
						// Verificamos se o objeto ja possue uma quebra cadastrada
//		                if (quebra.contains(resumoHelper)) {
//		                    int posicao = quebra.indexOf(resumoHelper);
//		                    ResumoReceitaHelper jaCadastrado = (ResumoReceitaHelper) quebra.get(posicao);
//		                    
//		                    jaCadastrado.setValorTotal(jaCadastrado.getValorTotal().add(resumoHelper.retornaValorTotal()));
//		                    
//		                } else {
//		                	
		                	
		                	resumoHelper.setValorTotal(resumoHelper.retornaValorTotal());
		                	
		                    quebra.add(resumoHelper);
		                    
		                //}
						
						//--------------------------------------
						
					}
					
				}
//			Agrupar Valores
//				colecaoAgrupada = agruparDadosColecaoResumoreceita(colecaoRetorno);
				
				colecaoAgrupada = quebra;

			}
			
			
		} catch (ErroRepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return colecaoAgrupada;
	}
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarPagamentoNaoClassificadoHistoricoResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		Collection colecaoAgrupada = new ArrayList();
		
		List<ResumoReceitaHelper> quebra = 
            new ArrayList<ResumoReceitaHelper>();
		
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarPagamentoNaoClassificadoHistoricoResumoPagamentoConta(dataInicial, dataFinal);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceitaHelper helper = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					objeto = (Object[]) iteratorObjetos.next();
					helper = new ResumoReceitaHelper();
					
					if(objeto[0] != null){
						helper.setGerenciaRegionalId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						helper.setUnidadeNegocioId((Integer)objeto[1]);
					}
					if(objeto[2] != null){
						helper.setLocalidadeId((Integer)objeto[2]);
					}
					if(objeto[3] != null){
						helper.setImovelId((Integer)objeto[3]);
					}
					if(objeto[4] != null){
						helper.setDataRealizacao((Timestamp)objeto[4]);
					}
					if(objeto[5] != null){
						helper.setSomaPagamentoNaoClassificado((BigDecimal)objeto[5]);
					}
					if(objeto[6] != null){
						helper.setContaBancariaId((Integer)objeto[6]);
					}
					if(objeto[7] != null){
						helper.setBancoId((Integer)objeto[7]);
					}
					if(objeto[8] != null){
						helper.setArrecadadorId((Integer)objeto[8]);
					}
					if(objeto[9] != null){
						helper.setReceitaIntra((Boolean)objeto[9]);
					}
					helper.setCategoriaId(Categoria.RESIDENCIAL);
					
//					dividir valores por categoria
					if(objeto[5] != null){
						
						Collection<ImovelSubcategoria> colecaoImovelSubCategoria = new ArrayList();
						if(objeto[3] != null && !objeto[3].equals("")){
							colecaoImovelSubCategoria = 
								getControladorImovel().pesquisarCategoriasImovel((Integer)objeto[3]);
						}else{
							ImovelSubcategoria imovelSubcategoria = new ImovelSubcategoria();
							ImovelSubcategoriaPK imovelSubcategoriaPK = new ImovelSubcategoriaPK();
							Subcategoria subcategoria = new Subcategoria();
							Categoria categoria = new Categoria();

							categoria.setId(Categoria.RESIDENCIAL);
							categoria.setQuantidadeEconomiasCategoria(1);
							subcategoria.setCategoria(categoria);
							imovelSubcategoriaPK.setSubcategoria(subcategoria);
							imovelSubcategoria.setComp_id(imovelSubcategoriaPK);
							
							colecaoImovelSubCategoria.add(imovelSubcategoria);
						}
						
						BigDecimal valorCategoria = BigDecimal.ZERO;
						if(!colecaoImovelSubCategoria.isEmpty()){
							Iterator iteratorSubCategoria = colecaoImovelSubCategoria.iterator();
							Collection colecaoCategoria = new ArrayList();
							while(iteratorSubCategoria.hasNext()){
								ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria)iteratorSubCategoria.next();
								Categoria categoria = imovelSubcategoria.getComp_id().getSubcategoria().getCategoria();
								colecaoCategoria.add(categoria);
							}
							Collection colecaoValores = getControladorImovel().obterValorPorCategoria(
									colecaoCategoria, (BigDecimal)objeto[5]);
							
							Iterator iteratorCategoria = colecaoCategoria.iterator();
							Categoria categoriaImovel = null;
							
							ResumoReceitaHelper resumoHelper = null;
							
							Iterator iteValorCategoria = colecaoValores.iterator();
							
							while(iteratorCategoria.hasNext() && iteValorCategoria.hasNext()){
								categoriaImovel = (Categoria)iteratorCategoria.next();
								
//								valorCategoria = (BigDecimal)colecaoValores.iterator().next();
								valorCategoria = (BigDecimal)iteValorCategoria.next();
								resumoHelper = new ResumoReceitaHelper(helper);
								resumoHelper.setCategoriaId(categoriaImovel.getId());
								resumoHelper.setSomaPagamentoNaoClassificado(valorCategoria);
								
								colecaoRetorno.add(resumoHelper);
								
							}
						}
					}
								
				}
				
				if ( colecaoRetorno != null && !colecaoRetorno.isEmpty() ){
					
					Iterator iteColRetorno = colecaoRetorno.iterator();
												
					while ( iteColRetorno.hasNext() ){
						
						ResumoReceitaHelper resumoHelper = (ResumoReceitaHelper)iteColRetorno.next();
						
						// Verificamos se o objeto ja possue uma quebra cadastrada
		                if (quebra.contains(resumoHelper)) {
		                    int posicao = quebra.indexOf(resumoHelper);
		                    ResumoReceitaHelper jaCadastrado = (ResumoReceitaHelper) quebra.get(posicao);
		                    
		                    jaCadastrado.setValorTotal(jaCadastrado.getValorTotal().add(resumoHelper.retornaValorTotal()));
		                    
		                } else {
		                	
		                	
		                	resumoHelper.setValorTotal(resumoHelper.retornaValorTotal());
		                	
		                    quebra.add(resumoHelper);
		                    
		                }
						
						//--------------------------------------
						
					}
				}
					
				
//			Agrupar Valores
//				colecaoAgrupada = agruparDadosColecaoResumoreceita(colecaoRetorno);
					
				colecaoAgrupada = quebra;

			}
			
			
		} catch (ErroRepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return colecaoAgrupada;
	}
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarPagamentoDebitoCobrarResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarPagamentoDebitoCobrarResumoPagamentoConta(dataInicial, dataFinal);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceitaHelper helper = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					objeto = (Object[]) iteratorObjetos.next();
					helper = new ResumoReceitaHelper();
					
					if(objeto[0] != null){
						helper.setGerenciaRegionalId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						helper.setUnidadeNegocioId((Integer)objeto[1]);
					}
					if(objeto[2] != null){
						helper.setLocalidadeId((Integer)objeto[2]);
					}
					helper.setCategoriaId(Categoria.RESIDENCIAL);
					if(objeto[3] != null){
						helper.setCategoriaId((Integer)objeto[3]);
					}
					if(objeto[4] != null){
						helper.setDataRealizacao((Timestamp)objeto[4]);
					}
					if(objeto[5] != null){
						helper.setSomaPagamentoDebCobrar((BigDecimal)objeto[5]);
					}
					if(objeto[6] != null){
						helper.setContaBancariaId((Integer)objeto[6]);
					}
					if(objeto[7] != null){
						helper.setBancoId((Integer)objeto[7]);
					}
					if(objeto[8] != null){
						helper.setArrecadadorId((Integer)objeto[8]);
					}
					if(objeto[9] != null){
						helper.setContaContabil((Integer)objeto[9]);
					}
					
					colecaoRetorno.add(helper);
								
				}

			}
			
			
		} catch (ErroRepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return colecaoRetorno;
	}
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarPagamentoDebitoCobrarHistoricoResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarPagamentoDebitoCobrarHistoricoResumoPagamentoConta(dataInicial, dataFinal);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceitaHelper helper = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					objeto = (Object[]) iteratorObjetos.next();
					helper = new ResumoReceitaHelper();
					
					if(objeto[0] != null){
						helper.setGerenciaRegionalId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						helper.setUnidadeNegocioId((Integer)objeto[1]);
					}
					if(objeto[2] != null){
						helper.setLocalidadeId((Integer)objeto[2]);
					}
					helper.setCategoriaId(Categoria.RESIDENCIAL);
					if(objeto[3] != null){
						helper.setCategoriaId((Integer)objeto[3]);
					}
					if(objeto[4] != null){
						helper.setDataRealizacao((Timestamp)objeto[4]);
					}
					if(objeto[5] != null){
						helper.setSomaPagamentoDebCobrar((BigDecimal)objeto[5]);
					}
					if(objeto[6] != null){
						helper.setContaBancariaId((Integer)objeto[6]);
					}
					if(objeto[7] != null){
						helper.setBancoId((Integer)objeto[7]);
					}
					if(objeto[8] != null){
						helper.setArrecadadorId((Integer)objeto[8]);
					}
					if(objeto[9] != null){
						helper.setContaContabil((Integer)objeto[9]);
					}
					
					
					colecaoRetorno.add(helper);
								
				}

			}
			
			
		} catch (ErroRepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return colecaoRetorno;
	}
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarPagamentoHistoricoSemCorrespondenteResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		Collection colecaoAgrupada = new ArrayList();
		
		List<ResumoReceitaHelper> quebra = 
            new ArrayList<ResumoReceitaHelper>();
		
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarPagamentoHistoricoSemCorrespondenteResumoPagamentoConta(dataInicial, dataFinal);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceitaHelper helper = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					objeto = (Object[]) iteratorObjetos.next();
					helper = new ResumoReceitaHelper();
					
					if(objeto[0] != null){
						helper.setGerenciaRegionalId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						helper.setUnidadeNegocioId((Integer)objeto[1]);
					}
					if(objeto[2] != null){
						helper.setLocalidadeId((Integer)objeto[2]);
					}
					if(objeto[3] != null){
						helper.setImovelId((Integer)objeto[3]);
					}
					if(objeto[4] != null){
						helper.setDataRealizacao((Timestamp)objeto[4]);
					}
					if(objeto[5] != null){
						helper.setSomaPagamentoHistoricoSemCorrespondente((BigDecimal)objeto[5]);
					}
					if(objeto[6] != null){
						helper.setContaBancariaId((Integer)objeto[6]);
					}
					if(objeto[7] != null){
						helper.setBancoId((Integer)objeto[7]);
					}
					if(objeto[8] != null){
						helper.setArrecadadorId((Integer)objeto[8]);
					}
					if(objeto[9] != null){
						helper.setReceitaIntra((Boolean)objeto[9]);
					}
					helper.setCategoriaId(Categoria.RESIDENCIAL);
					
//					dividir valores por categoria
					if(objeto[5] != null){
						Collection<ImovelSubcategoria> colecaoImovelSubCategoria = new ArrayList();
						if(objeto[3] != null && !objeto[3].equals("")){
							colecaoImovelSubCategoria = 
								getControladorImovel().pesquisarCategoriasImovel((Integer)objeto[3]);
						}else{
							ImovelSubcategoria imovelSubcategoria = new ImovelSubcategoria();
							ImovelSubcategoriaPK imovelSubcategoriaPK = new ImovelSubcategoriaPK();
							Subcategoria subcategoria = new Subcategoria();
							Categoria categoria = new Categoria();

							categoria.setId(Categoria.RESIDENCIAL);
							categoria.setQuantidadeEconomiasCategoria(1);
							subcategoria.setCategoria(categoria);
							imovelSubcategoriaPK.setSubcategoria(subcategoria);
							imovelSubcategoria.setComp_id(imovelSubcategoriaPK);
							
							colecaoImovelSubCategoria.add(imovelSubcategoria);
						}
						
						BigDecimal valorCategoria = BigDecimal.ZERO;
						if(!colecaoImovelSubCategoria.isEmpty()){
							Iterator iteratorSubCategoria = colecaoImovelSubCategoria.iterator();
							Collection colecaoCategoria = new ArrayList();
							while(iteratorSubCategoria.hasNext()){
								ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria)iteratorSubCategoria.next();
								Categoria categoria = imovelSubcategoria.getComp_id().getSubcategoria().getCategoria();
								colecaoCategoria.add(categoria);
							}
							Collection colecaoValores = getControladorImovel().obterValorPorCategoria(
									colecaoCategoria, (BigDecimal)objeto[5]);
							
							Iterator iteratorCategoria = colecaoCategoria.iterator();
							Categoria categoriaImovel = null;
							
							ResumoReceitaHelper resumoHelper = null;
							
							Iterator iteValorCategoria = colecaoValores.iterator();
							
							while(iteratorCategoria.hasNext() && iteValorCategoria.hasNext()){
								categoriaImovel = (Categoria)iteratorCategoria.next();
								
//								valorCategoria = (BigDecimal)colecaoValores.iterator().next();
								valorCategoria = (BigDecimal)iteValorCategoria.next();
								resumoHelper = new ResumoReceitaHelper(helper);
								resumoHelper.setCategoriaId(categoriaImovel.getId());
								resumoHelper.setSomaPagamentoHistoricoSemCorrespondente(valorCategoria);
								
								colecaoRetorno.add(resumoHelper);
								
							}
						}
					}
								
				}
				
				
				//Agrupar Valores
//				colecaoAgrupada = agruparDadosColecaoResumoreceita(colecaoRetorno);
				
				if ( colecaoRetorno != null && !colecaoRetorno.isEmpty() ){
					
					Iterator iteColRetorno = colecaoRetorno.iterator();
												
					while ( iteColRetorno.hasNext() ){
						
						ResumoReceitaHelper resumoHelper = (ResumoReceitaHelper)iteColRetorno.next();
						
						// Verificamos se o objeto ja possue uma quebra cadastrada
		                if (quebra.contains(resumoHelper)) {
		                    int posicao = quebra.indexOf(resumoHelper);
		                    ResumoReceitaHelper jaCadastrado = (ResumoReceitaHelper) quebra.get(posicao);
		                    
		                    jaCadastrado.setValorTotal(jaCadastrado.getValorTotal().add(resumoHelper.retornaValorTotal()));
		                    
		                } else {
		                	
		                	
		                	resumoHelper.setValorTotal(resumoHelper.retornaValorTotal());
		                	
		                    quebra.add(resumoHelper);
		                    
		                }
						
						//--------------------------------------
						
					}
				}
				
				colecaoAgrupada = quebra;
				
			}
			
			
		} catch (ErroRepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return colecaoAgrupada;
	}
	
	
	public Collection agruparDadosColecaoResumoreceita(Collection colecaoResumo){
		Collection retorno = null;
		
		ResumoReceitaHelper resumoReceitaHelper = null;
		ResumoReceitaHelper resumoReceitaAtual = null;
		retorno = new ArrayList();
		
		if(!colecaoResumo.isEmpty()){
			BigDecimal valorTotal = BigDecimal.ZERO;
			
			Iterator iteratorResumo = colecaoResumo.iterator();
			
	  		while(iteratorResumo.hasNext()) {
	  			
	  			resumoReceitaAtual = (ResumoReceitaHelper)iteratorResumo.next();
	  			
	  			if(resumoReceitaHelper != null 
	  			   && resumoReceitaHelper.getArrecadadorId().equals(resumoReceitaAtual.getArrecadadorId()) 
	  			   && resumoReceitaHelper.getBancoId().equals(resumoReceitaAtual.getBancoId())
	  			   && resumoReceitaHelper.getGerenciaRegionalId().equals(resumoReceitaAtual.getGerenciaRegionalId())
	  			   && resumoReceitaHelper.getUnidadeNegocioId().equals(resumoReceitaAtual.getUnidadeNegocioId())
	  			   && resumoReceitaHelper.getLocalidadeId().equals(resumoReceitaAtual.getLocalidadeId())
	  			   && resumoReceitaHelper.getDataRealizacao().equals(resumoReceitaAtual.getDataRealizacao())){
	  				
	  				if(valorTotal.equals(BigDecimal.ZERO)){
	  					valorTotal = valorTotal.add(resumoReceitaHelper.retornaValorTotal());
	  				}else{
	  					valorTotal = valorTotal.add(valorTotal);
	  				}
	  				resumoReceitaHelper.setValorTotal(valorTotal);
	  				
	  				//retorno.add(resumoReceitaHelper);
	  					  				
	  			}else{
	  				if(resumoReceitaHelper == null){
	  					resumoReceitaHelper = resumoReceitaAtual;
	  				}
	  				
	  				valorTotal = valorTotal.add(resumoReceitaHelper.retornaValorTotal());
	  					  				
	  				resumoReceitaHelper.setValorTotal(valorTotal);
	  				
					retorno.add(resumoReceitaHelper);
					
					valorTotal = BigDecimal.ZERO;
	  				
	  				if(iteratorResumo.hasNext()){
	  					resumoReceitaHelper = (ResumoReceitaHelper)iteratorResumo.next();
	  					if(!iteratorResumo.hasNext()){
	  		  				valorTotal = valorTotal.add(resumoReceitaHelper.retornaValorTotal());
	  						resumoReceitaHelper.setValorTotal(valorTotal);
	  		  				
	  	  					retorno.add(resumoReceitaHelper);
	  					}
	  				}
	  			}
			}
		}
		
		return retorno;
	}
 
	
	public Collection pesquisarResumoReceitaAgrupadoPorBanco(ResumoReceitaHelper resumo)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarResumoReceitaAgrupadoPorBanco(resumo);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceita receita = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					objeto = (Object[]) iteratorObjetos.next();
					receita = new ResumoReceita();
					
					if(objeto[0] != null){
						Banco banco = new Banco();
						FiltroBanco filtroBanco = new FiltroBanco();
						filtroBanco.adicionarParametro(new ParametroSimples(FiltroBanco.ID, (Integer)objeto[0]));
						Collection colecao = getControladorUtil().pesquisar(filtroBanco, Banco.class.getName());
						banco = (Banco)Util.retonarObjetoDeColecao(colecao);
						receita.setBanco(banco);
					}
					if(objeto[1] != null){
						ContaBancaria contaBancaria = new ContaBancaria();
						contaBancaria.setNumeroConta((String)objeto[1]);
						receita.setContaBancaria(contaBancaria);
					}
					if(objeto[2] != null){
						ContaContabil contaContabil = new ContaContabil();
						contaContabil.setNumeroConta((String)objeto[2]);
						receita.setContaContabil(contaContabil);
					}
					if(objeto[3] != null){
						receita.setValorReceita((BigDecimal)objeto[3]);
					}
					
					colecaoRetorno.add(receita);
								
				}

			}
			
			
		} catch (ErroRepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return colecaoRetorno;
		
	}
	
	
	public Collection pesquisarResumoReceitaRelatorioAnalitico(ResumoReceitaHelper resumo)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarResumoReceitaRelatorioAnalitico(resumo);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceita receita = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
				objeto = (Object[]) iteratorObjetos.next();
				receita = new ResumoReceita();
				
				if(objeto[0] != null){
					Banco banco = null;
					FiltroBanco filtroBanco = new FiltroBanco();
					filtroBanco.adicionarParametro(new ParametroSimples(FiltroBanco.ID, (Integer)objeto[0]));
					Collection colecao = getControladorUtil().pesquisar(filtroBanco, Banco.class.getName());
					banco = (Banco)Util.retonarObjetoDeColecao(colecao);
					receita.setBanco(banco);
				}
				if(objeto[1] != null){
					ContaBancaria contaBancaria = new ContaBancaria();
					contaBancaria.setNumeroConta((String)objeto[1]);
					receita.setContaBancaria(contaBancaria);
				}
				if(objeto[2] != null){
					Arrecadador arrecadador = null;
					FiltroArrecadador filtroArrecadador = new FiltroArrecadador();
					filtroArrecadador.adicionarParametro(new ParametroSimples(FiltroArrecadador.ID, (Integer)objeto[2]));
					Collection colecaoArrecadador = getControladorUtil().pesquisar(filtroArrecadador, Arrecadador.class.getName());
					arrecadador = (Arrecadador)Util.retonarObjetoDeColecao(colecaoArrecadador);
					receita.setArrecadador(arrecadador);
				}
				if(objeto[3] != null){
					receita.setDataRealizada((Date)objeto[3]);
				}
				if(objeto[4] != null){
					ContaContabil contaContabil = new ContaContabil();
					contaContabil.setNumeroConta((String)objeto[4]);
					receita.setContaContabil(contaContabil);
				}
				if(objeto[5] != null){
					receita.setValorReceita((BigDecimal)objeto[5]);
				}
				
				colecaoRetorno.add(receita);
							
			}

			}
			
			
	} catch (ErroRepositorioException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return colecaoRetorno;
		
	}
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fernando Fontelles
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarOutrasReceitasHistoricoResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		Collection colecaoAgrupada = new ArrayList();
		

		List<ResumoReceitaHelper> quebra = 
		            new ArrayList<ResumoReceitaHelper>();
		
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarOutrasReceitasHistoricoResumoPagamentoConta(dataInicial, dataFinal);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceitaHelper helper = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					objeto = (Object[]) iteratorObjetos.next();
					helper = new ResumoReceitaHelper();
					
					if(objeto[0] != null){
						helper.setGerenciaRegionalId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						helper.setUnidadeNegocioId((Integer)objeto[1]);
					}
					if(objeto[2] != null){
						helper.setLocalidadeId((Integer)objeto[2]);
					}
					if(objeto[3] != null){
						helper.setImovelId((Integer)objeto[3]);
					}
					if(objeto[4] != null){
						helper.setDataRealizacao((Timestamp)objeto[4]);
					}
					if(objeto[5] != null){
						helper.setSomaOutrasReceitas((BigDecimal)objeto[5]);
					}
					if(objeto[6] != null){
						helper.setContaBancariaId((Integer)objeto[6]);
					}
					if(objeto[7] != null){
						helper.setBancoId((Integer)objeto[7]);
					}
					if(objeto[8] != null){
						helper.setArrecadadorId((Integer)objeto[8]);
					}

					if(objeto[9] != null){
						helper.setContaContabil((Integer)objeto[9]);
					}
					
					helper.setCategoriaId(Categoria.RESIDENCIAL);
					
					//dividir valores por categoria
					if(objeto[5] != null /*&& objeto[3] != null*/){
						Collection<ImovelSubcategoria> colecaoImovelSubCategoria = new ArrayList();
						if(objeto[3] != null && !objeto[3].equals("")){
							colecaoImovelSubCategoria = 
								getControladorImovel().pesquisarCategoriasImovel((Integer)objeto[3]);
						}else{
							ImovelSubcategoria imovelSubcategoria = new ImovelSubcategoria();
							ImovelSubcategoriaPK imovelSubcategoriaPK = new ImovelSubcategoriaPK();
							Subcategoria subcategoria = new Subcategoria();
							Categoria categoria = new Categoria();

							categoria.setId(Categoria.RESIDENCIAL);
							categoria.setQuantidadeEconomiasCategoria(1);
							subcategoria.setCategoria(categoria);
							imovelSubcategoriaPK.setSubcategoria(subcategoria);
							imovelSubcategoria.setComp_id(imovelSubcategoriaPK);
							
							colecaoImovelSubCategoria.add(imovelSubcategoria);
						}
						
						BigDecimal valorCategoria = BigDecimal.ZERO;
						if(!colecaoImovelSubCategoria.isEmpty()){
							Iterator iteratorSubCategoria = colecaoImovelSubCategoria.iterator();
							Collection colecaoCategoria = new ArrayList();
							while(iteratorSubCategoria.hasNext()){
								ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria)iteratorSubCategoria.next();
								Categoria categoria = imovelSubcategoria.getComp_id().getSubcategoria().getCategoria();
								colecaoCategoria.add(categoria);
							}
							Collection colecaoValores = getControladorImovel().obterValorPorCategoria(
									colecaoCategoria, (BigDecimal)objeto[5]);
							
							Iterator iteratorCategoria = colecaoCategoria.iterator();
							Categoria categoriaImovel = null;
							
							ResumoReceitaHelper resumoHelper = null;
							
							Iterator iteValorCategoria = colecaoValores.iterator();
							
							while(iteratorCategoria.hasNext() && iteValorCategoria.hasNext()){
								categoriaImovel = (Categoria)iteratorCategoria.next();
								
//								valorCategoria = (BigDecimal)colecaoValores.iterator().next();
								valorCategoria = (BigDecimal)iteValorCategoria.next();
								
								resumoHelper = new ResumoReceitaHelper(helper);
								if ( objeto[9] != null ){
									resumoHelper.setContaContabil((Integer)objeto[9]);
								}
								resumoHelper.setCategoriaId(categoriaImovel.getId());
								resumoHelper.setSomaOutrasReceitas(valorCategoria);
								
								colecaoRetorno.add(resumoHelper);
								
							}
							
						}
					}
					
				}
				
				if ( colecaoRetorno != null && !colecaoRetorno.isEmpty() ){
					
					Iterator iteColRetorno = colecaoRetorno.iterator();
												
					while ( iteColRetorno.hasNext() ){
						
						ResumoReceitaHelper resumoHelper = (ResumoReceitaHelper)iteColRetorno.next();
						
						// Verificamos se o objeto ja possue uma quebra cadastrada
		                if (quebra.contains(resumoHelper)) {
		                    int posicao = quebra.indexOf(resumoHelper);
		                    ResumoReceitaHelper jaCadastrado = (ResumoReceitaHelper) quebra.get(posicao);
		                    
		                    jaCadastrado.setValorTotal(jaCadastrado.getValorTotal().add(resumoHelper.retornaValorTotal()));
		                    
		                    
		                } else {
		                	
		                	
		                	resumoHelper.setValorTotal(resumoHelper.retornaValorTotal());
		                	
		                    quebra.add(resumoHelper);
		                    
		                }
						
						//--------------------------------------
						
					}
					
				}
								
//			Agrupar Valores
//				colecaoAgrupada = agruparDadosColecaoResumoreceita(colecaoRetorno);
				
				colecaoAgrupada = quebra;
			}
			
			
		} catch (ErroRepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return colecaoAgrupada;
	}
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fernando Fontelles Filho
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarDevolucaoAvisoBancarioResumo(Date dataInicial, Date dataFinal)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		Collection colecaoAgrupada = new ArrayList();
		
		List<ResumoReceitaHelper> quebra = 
            new ArrayList<ResumoReceitaHelper>();
		
		//------------------------------------------
		
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarDevolucaoAvisoBancarioResumo(dataInicial, dataFinal);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceitaHelper helper = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					
//					ResumoReceitaHelper resumoHelper = null;
					
					objeto = (Object[]) iteratorObjetos.next();
					helper = new ResumoReceitaHelper();
					
					if(objeto[0] != null){
						helper.setGerenciaRegionalId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						helper.setUnidadeNegocioId((Integer)objeto[1]);
					}
					if(objeto[2] != null){
						helper.setLocalidadeId((Integer)objeto[2]);
					}
					if(objeto[3] != null && !objeto[3].equals("")){
						helper.setImovelId((Integer)objeto[3]);
					}
					if(objeto[4] != null){
						helper.setDataRealizacao((Timestamp)objeto[4]);
					}
					if(objeto[5] != null){
						helper.setSomaPagamentoNaoClassificado((BigDecimal)objeto[5]);
					}
					if(objeto[6] != null){
						helper.setContaBancariaId((Integer)objeto[6]);
					}
					if(objeto[7] != null){
						helper.setBancoId((Integer)objeto[7]);
					}
					if(objeto[8] != null){
						helper.setArrecadadorId((Integer)objeto[8]);
					}
					if(objeto[9] != null){
						helper.setReceitaIntra((Boolean)objeto[9]);
					}
					helper.setCategoriaId(Categoria.RESIDENCIAL);
					
//					dividir valores por categoria
					if(objeto[5] != null){
						Collection<ImovelSubcategoria> colecaoImovelSubCategoria = new ArrayList();
						if(helper.getImovelId() != null && !helper.getImovelId().equals("")){
							//System.out.println("Imovel:"+ helper.getImovelId());
							colecaoImovelSubCategoria = 
								getControladorImovel().pesquisarCategoriasImovel(helper.getImovelId());
						}else{
							ImovelSubcategoria imovelSubcategoria = new ImovelSubcategoria();
							ImovelSubcategoriaPK imovelSubcategoriaPK = new ImovelSubcategoriaPK();
							Subcategoria subcategoria = new Subcategoria();
							Categoria categoria = new Categoria();

							categoria.setId(Categoria.RESIDENCIAL);
							categoria.setQuantidadeEconomiasCategoria(1);
							subcategoria.setCategoria(categoria);
							imovelSubcategoriaPK.setSubcategoria(subcategoria);
							imovelSubcategoria.setComp_id(imovelSubcategoriaPK);
							
							colecaoImovelSubCategoria.add(imovelSubcategoria);
						}
												
						BigDecimal valorCategoria = BigDecimal.ZERO;
						if(!colecaoImovelSubCategoria.isEmpty()){
							Iterator iteratorSubCategoria = colecaoImovelSubCategoria.iterator();
							Collection colecaoCategoria = new ArrayList();
							while(iteratorSubCategoria.hasNext()){
								ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria)iteratorSubCategoria.next();
								Categoria categoria = imovelSubcategoria.getComp_id().getSubcategoria().getCategoria();
								colecaoCategoria.add(categoria);
							}
							Collection colecaoValores = getControladorImovel().obterValorPorCategoria(
									colecaoCategoria, (BigDecimal)objeto[5]);
							
							Iterator iteratorCategoria = colecaoCategoria.iterator();
							Categoria categoriaImovel = null;
							
							ResumoReceitaHelper resumoHelper = null;
							
							Iterator iteValorCategoria = colecaoValores.iterator();
							
							while(iteratorCategoria.hasNext() && iteValorCategoria.hasNext()){
								categoriaImovel = (Categoria)iteratorCategoria.next();
								
//								valorCategoria = (BigDecimal)colecaoValores.iterator().next();
								valorCategoria = (BigDecimal)iteValorCategoria.next();
								resumoHelper = new ResumoReceitaHelper(helper);
								resumoHelper.setCategoriaId(categoriaImovel.getId());
								resumoHelper.setSomaPagamentoNaoClassificado(valorCategoria);
								
								colecaoRetorno.add(resumoHelper);
								
							}
						}
					}
					
				}
				
				if ( colecaoRetorno != null && !colecaoRetorno.isEmpty() ){
				
					Iterator iteColRetorno = colecaoRetorno.iterator();
												
					while ( iteColRetorno.hasNext() ){
						
						ResumoReceitaHelper resumoHelper = (ResumoReceitaHelper)iteColRetorno.next();
						
						// Verificamos se o objeto ja possue uma quebra cadastrada
		                if (quebra.contains(resumoHelper)) {
		                    int posicao = quebra.indexOf(resumoHelper);
		                    ResumoReceitaHelper jaCadastrado = (ResumoReceitaHelper) quebra.get(posicao);
		                    
		                    jaCadastrado.setValorTotal(jaCadastrado.getValorTotal().add(resumoHelper.retornaValorTotal()));
		                    
		                } else {
		                	
		                	
		                	resumoHelper.setValorTotal(resumoHelper.retornaValorTotal());
		                	
		                    quebra.add(resumoHelper);
		                    
		                }
						
						//--------------------------------------
						
					}
					
				}
//			Agrupar Valores
//				colecaoAgrupada = agruparDadosColecaoResumoreceita(colecaoRetorno);
				
				colecaoAgrupada = quebra;

			}
			
			
		} catch (ErroRepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return colecaoAgrupada;
	}
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fernando Fontelles Filho
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarDevolucaoAvisoBancarioHistoricoResumo(Date dataInicial, Date dataFinal)
		throws ControladorException{
		
		Collection colecaoPesquisada = null;
		Collection colecaoRetorno = new ArrayList();
		Collection colecaoAgrupada = new ArrayList();
		
		List<ResumoReceitaHelper> quebra = 
            new ArrayList<ResumoReceitaHelper>();
		
		//------------------------------------------
		
		try {
			colecaoPesquisada = repositorioFinanceiro.pesquisarDevolucaoAvisoBancarioHistoricoResumo(dataInicial, dataFinal);
			
			if(!colecaoPesquisada.isEmpty()){
				Object[] objeto = null;
				ResumoReceitaHelper helper = null;
				
				Iterator iteratorObjetos = colecaoPesquisada.iterator();
				
				while (iteratorObjetos.hasNext()) {
					
//					ResumoReceitaHelper resumoHelper = null;
					
					objeto = (Object[]) iteratorObjetos.next();
					helper = new ResumoReceitaHelper();
					
					if(objeto[0] != null){
						helper.setGerenciaRegionalId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						helper.setUnidadeNegocioId((Integer)objeto[1]);
					}
					if(objeto[2] != null){
						helper.setLocalidadeId((Integer)objeto[2]);
					}
					if(objeto[3] != null && !objeto[3].equals("")){
						helper.setImovelId((Integer)objeto[3]);
					}
					if(objeto[4] != null){
						helper.setDataRealizacao((Timestamp)objeto[4]);
					}
					if(objeto[5] != null){
						helper.setSomaPagamentoNaoClassificado((BigDecimal)objeto[5]);
					}
					if(objeto[6] != null){
						helper.setContaBancariaId((Integer)objeto[6]);
					}
					if(objeto[7] != null){
						helper.setBancoId((Integer)objeto[7]);
					}
					if(objeto[8] != null){
						helper.setArrecadadorId((Integer)objeto[8]);
					}
					if(objeto[9] != null){
						helper.setReceitaIntra((Boolean)objeto[9]);
					}
					helper.setCategoriaId(Categoria.RESIDENCIAL);
					
//					dividir valores por categoria
					if(objeto[5] != null){
						Collection<ImovelSubcategoria> colecaoImovelSubCategoria = new ArrayList();
						if(helper.getImovelId() != null && !helper.getImovelId().equals("")){
							//System.out.println("Imovel:"+ helper.getImovelId());
							colecaoImovelSubCategoria = 
								getControladorImovel().pesquisarCategoriasImovel(helper.getImovelId());
						}else{
							ImovelSubcategoria imovelSubcategoria = new ImovelSubcategoria();
							ImovelSubcategoriaPK imovelSubcategoriaPK = new ImovelSubcategoriaPK();
							Subcategoria subcategoria = new Subcategoria();
							Categoria categoria = new Categoria();

							categoria.setId(Categoria.RESIDENCIAL);
							categoria.setQuantidadeEconomiasCategoria(1);
							subcategoria.setCategoria(categoria);
							imovelSubcategoriaPK.setSubcategoria(subcategoria);
							imovelSubcategoria.setComp_id(imovelSubcategoriaPK);
							
							colecaoImovelSubCategoria.add(imovelSubcategoria);
						}
												
						BigDecimal valorCategoria = BigDecimal.ZERO;
						if(!colecaoImovelSubCategoria.isEmpty()){
							Iterator iteratorSubCategoria = colecaoImovelSubCategoria.iterator();
							Collection colecaoCategoria = new ArrayList();
							while(iteratorSubCategoria.hasNext()){
								ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria)iteratorSubCategoria.next();
								Categoria categoria = imovelSubcategoria.getComp_id().getSubcategoria().getCategoria();
								colecaoCategoria.add(categoria);
							}
							Collection colecaoValores = getControladorImovel().obterValorPorCategoria(
									colecaoCategoria, (BigDecimal)objeto[5]);
							
							Iterator iteratorCategoria = colecaoCategoria.iterator();
							Categoria categoriaImovel = null;
							
							ResumoReceitaHelper resumoHelper = null;
							
							Iterator iteValorCategoria = colecaoValores.iterator();
							
							while(iteratorCategoria.hasNext() && iteValorCategoria.hasNext()){
								categoriaImovel = (Categoria)iteratorCategoria.next();
								
//								valorCategoria = (BigDecimal)colecaoValores.iterator().next();
								valorCategoria = (BigDecimal)iteValorCategoria.next();
								resumoHelper = new ResumoReceitaHelper(helper);
								resumoHelper.setCategoriaId(categoriaImovel.getId());
								resumoHelper.setSomaPagamentoNaoClassificado(valorCategoria);
								
								colecaoRetorno.add(resumoHelper);
								
							}
						}
					}
					
				}
				
				if ( colecaoRetorno != null && !colecaoRetorno.isEmpty() ){
				
					Iterator iteColRetorno = colecaoRetorno.iterator();
												
					while ( iteColRetorno.hasNext() ){
						
						ResumoReceitaHelper resumoHelper = (ResumoReceitaHelper)iteColRetorno.next();
						
						// Verificamos se o objeto ja possue uma quebra cadastrada
		                if (quebra.contains(resumoHelper)) {
		                    int posicao = quebra.indexOf(resumoHelper);
		                    ResumoReceitaHelper jaCadastrado = (ResumoReceitaHelper) quebra.get(posicao);
		                    
		                    jaCadastrado.setValorTotal(jaCadastrado.getValorTotal().add(resumoHelper.retornaValorTotal()));
		                    
		                } else {
		                	
		                	
		                	resumoHelper.setValorTotal(resumoHelper.retornaValorTotal());
		                	
		                    quebra.add(resumoHelper);
		                    
		                }
						
						//--------------------------------------
						
					}
					
				}
//			Agrupar Valores
//				colecaoAgrupada = agruparDadosColecaoResumoreceita(colecaoRetorno);
				
				colecaoAgrupada = quebra;

			}
			
			
		} catch (ErroRepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return colecaoAgrupada;
	}
	
	/**
     * [UC0799] - Gerar Txt das Contas Baixadas Contabilmente
     * 
     * @author: Rodrigo Cabral
     * @date: 16/03/2011
     */
    public Map
            consultarSomatorioValorContasBaixadasContabilmenteFaixa(
                Integer referenciaInicio, Integer referenciaFinal,Integer faixa,Short periodicidade)throws ControladorException{
        
        Collection colecaoSomatorioValorContasBaixadasContabilmente = null;
        
        Map retorno = new HashMap();
        
        try {
            
            if (faixa.equals(ConstantesSistema.FAIXA_1)){
                
                colecaoSomatorioValorContasBaixadasContabilmente = repositorioFinanceiro
                .consultarSomatorioValorContasBaixadasContabilmenteFaixa1(
                        referenciaInicio, referenciaFinal, periodicidade);
                
            }else if (faixa.equals(ConstantesSistema.FAIXA_2)){
                
                colecaoSomatorioValorContasBaixadasContabilmente = repositorioFinanceiro
                .consultarSomatorioValorContasBaixadasContabilmenteFaixa2(
                        referenciaInicio, referenciaFinal, periodicidade);
                
            }else if (faixa.equals(ConstantesSistema.FAIXA_3)){
                
                colecaoSomatorioValorContasBaixadasContabilmente = repositorioFinanceiro
                .consultarSomatorioValorContasBaixadasContabilmenteFaixa3(
                        referenciaInicio, referenciaFinal, periodicidade);
                
            }
            
            BigDecimal valorAcumulado = BigDecimal.ZERO;
            valorAcumulado = valorAcumulado.setScale(2, BigDecimal.ROUND_HALF_DOWN);
            
            if (colecaoSomatorioValorContasBaixadasContabilmente != null 
                    && !colecaoSomatorioValorContasBaixadasContabilmente.isEmpty()) {

                Iterator iteratorColecaoSomatorioValorContasBaixadasContabilmente = colecaoSomatorioValorContasBaixadasContabilmente.iterator();
               
                while (iteratorColecaoSomatorioValorContasBaixadasContabilmente.hasNext()) {

                	Object valorObject = iteratorColecaoSomatorioValorContasBaixadasContabilmente.next();
                	
                	if (valorObject != null){
                		 BigDecimal valor = (BigDecimal) valorObject;
                		 valorAcumulado = valorAcumulado.add(valor);
                	}
                	
                }   
               
            }
            
        retorno.put("somatorio",valorAcumulado);
       
        return retorno;
        
        } catch (ErroRepositorioException ex) {
            throw new ControladorException("erro.sistema", ex);
      }

    }
    
	/**
     * [UC0799] - Gerar Txt das Contas Baixadas Contabilmente
     * 
     * @author: Rafael Corrêa
     * 
     * @date: 29/05/2013
     */
    public BigDecimal consultarSomatorioValorDoacoesContasBaixadasContabilmente(
                Integer referenciaInicio, Integer referenciaFinal, Short periodicidade) throws ControladorException {
        
        BigDecimal retorno = BigDecimal.ZERO;
        retorno = retorno.setScale(2, BigDecimal.ROUND_HALF_DOWN);
        
        try {
            
        	Collection<BigDecimal> colecaoSomatorioValorDoacoes = repositorioFinanceiro
                .consultarSomatorioValorDoacoesContasBaixadasContabilmente(
                        referenciaInicio, referenciaFinal, periodicidade);
        	
        	for (BigDecimal valorDoacoes : colecaoSomatorioValorDoacoes) {
        		if (valorDoacoes != null) {
        			retorno = retorno.add(valorDoacoes);
        		}
			}
        	
        } catch (ErroRepositorioException ex) {
            throw new ControladorException("erro.sistema", ex);
        }
        
        return retorno;

    }

	/**
	 * [UC0989] Gerar Resumo de Documentos a Receber 
	 *
	 * @author Mariana Victor
	 * @date 28/03/2011
	 *
	 * @param anoMesReferenciaRecebimentos
	 * @param idLocalidade
	 * @throws ErroRepositorioException
	 */
	public void removerDocumentosAReceberFaixaResumo(int anoMesReferenciaFaturamento, 
		Integer idLocalidade) throws ControladorException {
		
		try {
			
			repositorioFinanceiro.removerDocumentosAReceberFaixaResumo(anoMesReferenciaFaturamento, 
			idLocalidade);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	
	/**
	 * [UC0485] Gerar Resumo dos Devedores Duvidosos
	 * [FS0009-Pesquisar Resumo Perdas Órgão Público] 
     *
	 * @author Arthur Carvalho
	 * @date 17/11/2011
	 * @param anoMesReferenciaContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean pesquisarResumoDevedoresDuvidososPerdasOrgaoPublico( int anoMesReferenciaContabil , int idTipoPerda )  throws ControladorException {
		boolean retorno = false;
		
		try {
			
			retorno = repositorioFinanceiro.pesquisarResumoDevedoresDuvidososPerdasOrgaoPublico(anoMesReferenciaContabil, idTipoPerda);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		return retorno;
	}
	
	
	
	/**
	 * O sistema verifica se existe contas que atendam os critérios informados para baixa societária
	 * 
	 * [UC0485] Gerar Resumo dos Devedores Duvidosos
	 * [SB0003] - Validar Critérios Para Perdas Societárias
	 * 
	 *  @author Arthur Carvalho
	 *  @date 18/11/2011
	 *   
	 * @return
	 * @throws ControladorException
	 */
	public Integer pesquisaQuantidadeContasBaixaSocietaria(String anoMesInicial, String anoMesFinal, String numeroDeMesesInformados, 
			String indicadorCategoriaResidencial, String indicadorCategoriaComercial, String indicadorCategoriaIndustrial, String indicadorCategoriaPublica,
			String indicadorEsferaParticular, String indicadorEsferaMunicipal, String indicadorEsferaEstadual, String indicadorEsferaFederal ) throws ControladorException {
		
		Integer retorno = null;
		
		Collection<Integer> colecaoEsferaPoder = new ArrayList<Integer>();
		Collection<Integer> colecaoCategoria = new ArrayList<Integer>();
		
		//ColecaoEsferaPoder
		if ( indicadorEsferaParticular.equals(String.valueOf(ConstantesSistema.SIM)) ) {
			colecaoEsferaPoder.add(Integer.valueOf(EsferaPoder.PARTICULAR));
		}
		if ( indicadorEsferaMunicipal.equals(String.valueOf(ConstantesSistema.SIM)) ) {
			colecaoEsferaPoder.add(Integer.valueOf(EsferaPoder.MUNICIPAL));
		}
		if ( indicadorEsferaEstadual.equals(String.valueOf(ConstantesSistema.SIM)) ) {
			colecaoEsferaPoder.add(Integer.valueOf(EsferaPoder.ESTADUAL));
		}
		if ( indicadorEsferaFederal.equals(String.valueOf(ConstantesSistema.SIM)) ) {
			colecaoEsferaPoder.add(Integer.valueOf(EsferaPoder.FEDERAL));
		}
		
		//ColecaoCategoria
		if (indicadorCategoriaResidencial.equals(String.valueOf(ConstantesSistema.SIM)) ) {
			colecaoCategoria.add(Categoria.RESIDENCIAL);
		}
		if ( indicadorCategoriaComercial.equals(String.valueOf(ConstantesSistema.SIM)) ) {
			colecaoCategoria.add(Categoria.COMERCIAL);
		}
		if ( indicadorCategoriaIndustrial.equals(String.valueOf(ConstantesSistema.SIM)) ) {
			colecaoCategoria.add(Categoria.INDUSTRIAL);
		}
		if ( indicadorCategoriaPublica.equals(String.valueOf(ConstantesSistema.SIM)) ) {
			colecaoCategoria.add(Categoria.PUBLICO);
		}
		
		
		Integer anoMesMenosNumeroDeMesesInformados = Util.subtrairMesDoAnoMes(Util.recuperaAnoMesDaData(new Date()), Integer.valueOf(numeroDeMesesInformados));
		
		
		
		try {
			
			retorno = repositorioFinanceiro.pesquisaQuantidadeContasBaixaSocietaria(anoMesInicial, anoMesFinal, anoMesMenosNumeroDeMesesInformados, 
												colecaoEsferaPoder, colecaoCategoria);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		return retorno;
		
	}
	
	
	/**
	 * O sistema verifica se existe contas que atendam os critérios informados para baixa societária
	 * 
	 * [UC0485] Gerar Resumo dos Devedores Duvidosos
	 * [SB0003] - Validar Critérios Para Perdas Societárias
	 * 
	 *  @author Arthur Carvalho
	 *  @date 18/11/2011
	 *   
	 * @return
	 * @throws ControladorException
	 */
	public Integer pesquisaQuantidadeContasBaixaPerdasOrgaoPublico(String numeroDeMesesInformados, String indicadorEsferaPoderMunicipal, 
			String indicadorEsferaPoderFederal ) throws ControladorException {
		
		Integer retorno = null;
		
		Collection<Integer> colecaoEsferaPoder = new ArrayList<Integer>();
		Collection<Integer> colecaoCategoria = new ArrayList<Integer>();
		
		//ColecaoEsferaPoder
		if ( indicadorEsferaPoderMunicipal.equals(String.valueOf(ConstantesSistema.SIM)) ) {
			colecaoEsferaPoder.add(Integer.valueOf(EsferaPoder.MUNICIPAL));
		}

		if ( indicadorEsferaPoderFederal.equals(String.valueOf(ConstantesSistema.SIM)) ) {
			colecaoEsferaPoder.add(Integer.valueOf(EsferaPoder.FEDERAL));
		}
		
		try {
			
			retorno = repositorioFinanceiro.pesquisaQuantidadeContasBaixaPerdasOrgaoPublico(new Integer(numeroDeMesesInformados),  colecaoEsferaPoder);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		return retorno;
		
	}
	
	/**
	 * Pesquisa os parâmetros Perdas Societarias por ano/mês de referência contábil.
	 *
	 * [UC0485] Gerar Resumo dos Devedores Duvidosos
	 *[SB0004] - Processar Perdas Societárias
	 *
	 * @author Arthur Carvalho
	 * @date 21/11/2011
	 *
	 * @param anoMesReferenciaContabil
	 * @return
	 * @throws ControladorException
	 */
	public ParametrosPerdasSocietarias pesquisarParametrosPerdasSocietarias(Integer anoMesReferenciaContabil) throws ControladorException {
		ParametrosPerdasSocietarias parametrosPerdasSocietarias = null;
		
		try {
			
			parametrosPerdasSocietarias = repositorioFinanceiro.pesquisarParametrosPerdasSocietarias(anoMesReferenciaContabil);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		return parametrosPerdasSocietarias;
		
	}
	
	/**
	 * Pesquisa os parâmetros Perdas Societarias por ano/mês de referência contábil.
	 *
	 * [UC0485] Gerar Resumo dos Devedores Duvidosos
	 * [SB0007-Excluir Parâmetros Perdas Societárias];    
	 *
	 * @author Arthur Carvalho
	 * @date 21/11/2011
	 *
	 * @param anoMesReferenciaContabil
	 * @return
	 * @throws ControladorException
	 */
	public void deletarParametrosPerdasSocietarias(Integer anoMesReferenciaContabil) throws ControladorException {
		
		try {
			repositorioFinanceiro.deletarParametrosPerdasSocietarias(anoMesReferenciaContabil);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * Pesquisa os parâmetros Perdas Orgao Publico por ano/mês de referência contábil.
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 * [SB0012] - Processar Perdas Órgãos Públicos
	 * 
	 * @author Arthur Carvalho
	 * @date 21/11/2011
	 *
	 * @param anoMesReferenciaContabil
	 * @return
	 * @throws ControladorException
	 */
	public ParametrosPerdasOrgaoPublico pesquisarParametrosPerdasOrgaoPublico(Integer anoMesReferenciaContabil) throws ControladorException {
		ParametrosPerdasOrgaoPublico parametrosPerdas = null;
		
		try {
			
			parametrosPerdas = repositorioFinanceiro.pesquisarParametrosPerdasOrgaoPublico(anoMesReferenciaContabil);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		return parametrosPerdas;
		
	}
	
	/**
	 * Pesquisa os parâmetros Perdas Orgao Publico por ano/mês de referência contábil.
	 *
	 * [UC0485] Gerar Resumo dos Devedores Duvidosos
	 * [SB0015-Excluir Parâmetros Perdas Órgãos Públicos
	 * @author Arthur Carvalho
	 * @date 21/11/2011
	 *
	 * @param anoMesReferenciaContabil
	 * @return
	 * @throws ControladorException
	 */
	public void deletarParametrosPerdasOrgaoPublico(Integer anoMesReferenciaContabil) throws ControladorException {
		
		try {
			repositorioFinanceiro.deletarParametrosPerdasOrgaoPublico(anoMesReferenciaContabil);
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	
	
	
	/**
	 * Metodo responsvel por gerar o resumo devedores duvidosos do tipo Perda Fiscal
	 * @author Arthur Carvalho
	 * @date 30/11/2011
	 * 
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idPerdaTipo
	 * @param idUnidadeIniciada
	 * @throws ControladorException
	 */
	public void perdasFiscais(int anoMesReferenciaContabil, Integer idLocalidade, Integer idPerdaTipo, Integer idUnidadeIniciada ) throws ControladorException {
		
		
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
		

		BigDecimal valorTotalValoresBaixados = BigDecimal.ZERO;
		
		//5.4-Caso o valor total dos valores baixados seja maior
		//5.5-Caso contrário,processar o grupo de contas do próximo imóvel
		if(valorTotalJaBaixado.compareTo(valorLimiteBaixado) != 1) {
			
			String anoMesString = ""+ parametrosDevedoresDuvidosos.getAnoMesReferenciaContabil();
			
			Collection<Integer> colecaoQuadraId = this.repositorioFinanceiro.pesquisarIdsQuadrasParaGerarResumoDevedoresDuvidosos(idLocalidade);
			
			//ATUALIZA AS CONTAS
			if ( colecaoQuadraId != null ) {
				
				Iterator<Integer> iteratorIdsQuadra = colecaoQuadraId.iterator();
				while ( iteratorIdsQuadra.hasNext() ) {
					
					Integer idQuadra = (Integer) iteratorIdsQuadra.next();
				
					////Pesquisa o valor total das contas que estão na situação de Devedor Duvidosos
					valorTotalValoresBaixados =  this.repositorioFinanceiro.obterValorTotalContasDevedoresDuvidosos( anoMesReferenciaContabil,
							idLocalidade, idQuadra, anoMesString, parametrosDevedoresDuvidosos.getId() ) ;
					
					//Recupera o percentual permitido e calcula o valor limite permitido de estouro
					BigDecimal percentualPermitido = new BigDecimal(0.10);
					BigDecimal valorLimiteBaixadoComPercentual = valorLimiteBaixado.add(valorLimiteBaixado.multiply(percentualPermitido));
					if ( valorTotalValoresBaixados == null ) {
						valorTotalValoresBaixados = BigDecimal.ZERO;
					}
					
					parametrosDevedoresDuvidosos = this.repositorioFinanceiro.pesquisarParametrosDevedoresDuvidosos(anoMesReferenciaContabil);
					
					valorTotalJaBaixado = parametrosDevedoresDuvidosos.getValorBaixado().add(valorTotalValoresBaixados);
					
					if(valorTotalJaBaixado.compareTo(valorLimiteBaixadoComPercentual) != 1){
					
						//7.Atualiza nos par?metros para baixa das contas dos devedores duvidosos a data e hora
						//do processamento e o valor total baixado
						this.repositorioFinanceiro.atualizarValorBaixadoParametrosDevedoresDuvidosos(anoMesReferenciaContabil, valorTotalValoresBaixados);
						
						this.repositorioFinanceiro.atualizaContaAnoMesReferenciaContabilDevedoresDuvidosos(anoMesReferenciaContabil, 
								idLocalidade, idQuadra ,parametrosDevedoresDuvidosos.getId() , idPerdaTipo);
						
						
						//[SB0001] - Acumular o resumo dos devedores duvidosos
						colecaoGerarResumoDevedoresHelperTemp = this.acumularResumoDevedoresDuvidosos(anoMesReferenciaContabil,idLocalidade, idQuadra ,
									PerdasTipo.PERDAS_FISCAIS);
						
						//Caso a coleção temporaria não esteja vazia 
						//acumula os registros que estão na mesma quebra
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
						//caso contr?rio passar para o pr?ximo registro.
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
	
	
	public void provisaoPerdasSocietarias(int anoMesReferenciaContabil, Integer idLocalidade, Integer idPerdaTipo, Integer idUnidadeIniciada ) throws ControladorException {
		
		
		Collection<GerarResumoDevedoresDuvidososHelper> colecaoGerarResumoDevedoresHelperTemp = null;
		List<GerarResumoDevedoresDuvidososHelper> colecaoGerarResumoDevedoresHelperPrincipal = new ArrayList<GerarResumoDevedoresDuvidososHelper>();
		Integer anoMesArrecadacao = null;
		
		try {

			ParametrosPerdasSocietarias parametrosPerdasSocietarias = this.repositorioFinanceiro.pesquisarParametrosPerdasSocietarias(anoMesReferenciaContabil);
			
			Collection<Integer> colecaoEsferaPoder = new ArrayList<Integer>();
			Collection<Integer> colecaoCategoria = new ArrayList<Integer>();
			
			Integer anoMesMenosNumeroDeMesesInformados = Util.subtrairMesDoAnoMes(Util.recuperaAnoMesDaData(new Date()), 
				Integer.valueOf( parametrosPerdasSocietarias.getNumeroMesesReferenciaInferior()) );
			

			Collection<Integer> colecaoQuadraId = this.repositorioFinanceiro.pesquisarIdsQuadrasParaGerarResumoDevedoresDuvidosos(idLocalidade);
			
			//ATUALIZA AS CONTAS
			if ( colecaoQuadraId != null ) {
				
				Iterator<Integer> iteratorIdsQuadra = colecaoQuadraId.iterator();
				while ( iteratorIdsQuadra.hasNext() ) {
					
					Integer idQuadra = (Integer) iteratorIdsQuadra.next();
					
					this.repositorioFinanceiro.atualizaContaPerdasSocietarias(anoMesReferenciaContabil, parametrosPerdasSocietarias.getAnoMesReferenciaBaixaInicial(), 
						parametrosPerdasSocietarias.getAnoMesReferenciaBaixaFinal(), anoMesMenosNumeroDeMesesInformados, parametrosPerdasSocietarias, idLocalidade, idQuadra);
						
					//[SB0001] - Acumular o resumo dos devedores duvidosos
					colecaoGerarResumoDevedoresHelperTemp = this.acumularResumoDevedoresDuvidosos(anoMesReferenciaContabil,idLocalidade, idQuadra ,
									PerdasTipo.PROVISAO_PERDAS_SOCIETARIAS);
						
					//Caso a colecao temporaria nao esteja vazia 
					//acumula os registros que estao na mesma quebra
					//e adiciona os novos registros.
					//Contas parceladas
					colecaoGerarResumoDevedoresHelperPrincipal = agruparValorConta(colecaoGerarResumoDevedoresHelperTemp , colecaoGerarResumoDevedoresHelperPrincipal);
					colecaoGerarResumoDevedoresHelperTemp = null;
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
						//caso contrario passar para o proximo registro.
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
							perdasTipo.setId(PerdasTipo.PROVISAO_PERDAS_SOCIETARIAS);
							
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
			
		
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex, idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}
	
	
	/**
	 * @author Arthur Carvalho
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idPerdaTipo
	 * @param idUnidadeIniciada
	 * @throws ControladorException
	 */
	public void perdasOrgaoPublico(int anoMesReferenciaContabil, Integer idLocalidade, Integer idPerdaTipo, Integer idUnidadeIniciada ) throws ControladorException {
		
		
		Collection<GerarResumoDevedoresDuvidososHelper> colecaoGerarResumoDevedoresHelperTemp = null;
		List<GerarResumoDevedoresDuvidososHelper> colecaoGerarResumoDevedoresHelperPrincipal = new ArrayList<GerarResumoDevedoresDuvidososHelper>();
		Integer anoMesArrecadacao = null;
		
		try {

			ParametrosPerdasOrgaoPublico parametrosPerdasOrgaoPublico = this.repositorioFinanceiro.pesquisarParametrosPerdasOrgaoPublico(anoMesReferenciaContabil);
			

			Collection<Integer> colecaoQuadraId = this.repositorioFinanceiro.pesquisarIdsQuadrasParaGerarResumoDevedoresDuvidosos(idLocalidade);
			
			
			//ATUALIZA AS CONTAS
			if ( colecaoQuadraId != null ) {
				
				Iterator<Integer> iteratorIdsQuadra = colecaoQuadraId.iterator();
				while ( iteratorIdsQuadra.hasNext() ) {
					
					Integer idQuadra = (Integer) iteratorIdsQuadra.next();
					
					this.repositorioFinanceiro.atualizaContaPerdasOrgaoPublico(anoMesReferenciaContabil, 
						parametrosPerdasOrgaoPublico, idLocalidade, idQuadra);
						
					//[SB0001] - Acumular o resumo dos devedores duvidosos
					colecaoGerarResumoDevedoresHelperTemp = this.acumularResumoDevedoresDuvidosos(anoMesReferenciaContabil,idLocalidade, idQuadra ,
									PerdasTipo.PERDAS_ORGAO_PUBLICO);
					
					colecaoGerarResumoDevedoresHelperPrincipal = agruparValorConta(colecaoGerarResumoDevedoresHelperTemp , colecaoGerarResumoDevedoresHelperPrincipal);
					colecaoGerarResumoDevedoresHelperTemp = null;
					
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
						//caso contrario passar para o proximo registro.
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
							perdasTipo.setId(PerdasTipo.PERDAS_ORGAO_PUBLICO);
							
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
			
		
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex, idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}
	
	
	private void recuperacaoProvisaoPerdasSocietarias(int anoMesReferenciaContabil, Integer idLocalidade, Integer idPerdaTipo, Integer idUnidadeIniciada ) 
			throws ControladorException {
		
		Collection<GerarResumoDevedoresDuvidososHelper> colecaoGerarResumoDevedoresHelperTemp = null;
		List<GerarResumoDevedoresDuvidososHelper> colecaoGerarResumoDevedoresHelperPrincipal = new ArrayList<GerarResumoDevedoresDuvidososHelper>();
		Integer anoMesArrecadacao = null;
		
		try {

			
			Collection<Integer> colecaoEsferaPoder = new ArrayList<Integer>();
			Collection<Integer> colecaoCategoria = new ArrayList<Integer>();
			

			Collection<Integer> colecaoQuadraId = this.repositorioFinanceiro.pesquisarIdsQuadrasParaGerarResumoDevedoresDuvidosos(idLocalidade);
			
			//ATUALIZA AS CONTAS
			if ( colecaoQuadraId != null ) {
				
				Iterator<Integer> iteratorIdsQuadra = colecaoQuadraId.iterator();
				while ( iteratorIdsQuadra.hasNext() ) {
					
					Integer idQuadra = (Integer) iteratorIdsQuadra.next();
					
					//Contas pagas
					colecaoGerarResumoDevedoresHelperTemp = this.acumularResumoDevedoresDuvidososRecuperacaoPerdasSocietariasPagamento(anoMesReferenciaContabil,idLocalidade, idQuadra ,
									PerdasTipo.RECUPERACAO_DA_PROVISAO_DE_PERDAS_SOCIETARIAS);
					
					colecaoGerarResumoDevedoresHelperPrincipal = agruparValorConta(colecaoGerarResumoDevedoresHelperTemp , colecaoGerarResumoDevedoresHelperPrincipal);
					colecaoGerarResumoDevedoresHelperTemp = null;
					
					//Contas retificadas
					colecaoGerarResumoDevedoresHelperTemp = this.acumularResumoDevedoresDuvidososRecuperacaoPerdasSocietariasRetificada(anoMesReferenciaContabil,idLocalidade, idQuadra ,
						PerdasTipo.RECUPERACAO_DA_PROVISAO_DE_PERDAS_SOCIETARIAS);
		
					colecaoGerarResumoDevedoresHelperPrincipal = agruparValorConta(colecaoGerarResumoDevedoresHelperTemp , colecaoGerarResumoDevedoresHelperPrincipal);
					colecaoGerarResumoDevedoresHelperTemp = null;
					
					//Contas parceladas
					colecaoGerarResumoDevedoresHelperTemp = this.acumularResumoDevedoresDuvidososRecuperacaoPerdasSocietariasParcelada(anoMesReferenciaContabil,idLocalidade, idQuadra ,
						PerdasTipo.RECUPERACAO_DA_PROVISAO_DE_PERDAS_SOCIETARIAS);
		
					colecaoGerarResumoDevedoresHelperPrincipal = agruparValorConta(colecaoGerarResumoDevedoresHelperTemp , colecaoGerarResumoDevedoresHelperPrincipal);
					colecaoGerarResumoDevedoresHelperTemp = null;
										
				
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
						//caso contrario passar para o proximo registro.
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
							perdasTipo.setId(PerdasTipo.RECUPERACAO_DA_PROVISAO_DE_PERDAS_SOCIETARIAS);
							
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
	 * @author Arthur Carvalho
	 * @date 30/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idParametrosDevedoresDuvidosos
	 * @return
	 * @throws ControladorException
	 */
	protected Collection<GerarResumoDevedoresDuvidososHelper> acumularResumoDevedoresDuvidososRecuperacaoPerdasSocietariasPagamento(int anoMesReferenciaContabil, 
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
						pesquisarValorAguaAgrupadoPorCategoriaDevedoresDuvidososRecuperacaoPerdasSocietariasPagamento( anoMesReferenciaContabil, idLocalidade, idQuadra);
			
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
			pesquisarValorEsgotoAgrupadoPorCategoriaDevedoresDuvidososRecuperacaoPerdasSocietariasPagamento( anoMesReferenciaContabil, idLocalidade,  idQuadra);
			
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
					pesquisarValorAguaParcelamentoAgrupadoPorCategoriaDevedoresDuvidososRecuperacaoPerdasSocietariasPagamento( anoMesReferenciaContabil, idLocalidade, idQuadra);
			
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
					pesquisarValorEsgotoParcelamentoAgrupadoPorCategoriaDevedoresDuvidososRecuperacaoPerdasSocietariasPagamento( anoMesReferenciaContabil, idLocalidade, idQuadra);
		
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
			colecaoDadosTemporaria = repositorioFinanceiro.pesquisarValorServicoParceladoDevedoresDuvidososRecuperacaoPerdasSocietariasPagamento(anoMesReferenciaContabil,idLocalidade, 
				idQuadra);
			
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
					pesquisarValorJurosDoParcelamentoDevedoresDuvidososRecuperacaoPerdasSocietariasPagamento( anoMesReferenciaContabil, idLocalidade, idQuadra);
		
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
			colecaoDadosTemporaria  = repositorioFinanceiro.pesquisarValorPorTipoFinanciamentoDevedoresDuvidososRecuperacaoPerdasSocietariasPagamento( anoMesReferenciaContabil,
					idLocalidade, idQuadra);
			
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
			Collection<Object[]> colecaoDevolucoes  = repositorioFinanceiro.pesquisarDevolucoesValoresContaDevedoresDuvidososRecuperacaoPerdasSocietariasPagamento( 
				anoMesReferenciaContabil,idLocalidade, idQuadra);
			
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
		
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
		
		return colecaoRetorno;
	}
	
	/**
	 * 
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 * 
	 * 
	 * @author Arthur Carvalho
	 * @date 30/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idParametrosDevedoresDuvidosos
	 * @return
	 * @throws ControladorException
	 */
	protected Collection<GerarResumoDevedoresDuvidososHelper> acumularResumoDevedoresDuvidososRecuperacaoPerdasSocietariasRetificada(int anoMesReferenciaContabil, 
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
					pesquisarValorAguaAgrupadoPorCategoriaDevedoresDuvidososRecuperacaoPerdasSocietariasRetificadas( anoMesReferenciaContabil, idLocalidade, idQuadra);
			
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
					pesquisarValorEsgotoAgrupadoPorCategoriaDevedoresDuvidososRecuperacaoPerdasSocietariasRetificadas( anoMesReferenciaContabil, idLocalidade,  idQuadra);
			
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
					pesquisarValorAguaParcelamentoAgrupadoPorCategoriaDevedoresDuvidososRecuperacaoPerdasSocietariasRetificadas( anoMesReferenciaContabil, idLocalidade, idQuadra);
			
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
					pesquisarValorEsgotoParcelamentoAgrupadoPorCategoriaDevedoresDuvidososRecuperacaoPerdasSocietariasRetificadas( anoMesReferenciaContabil, idLocalidade, idQuadra);
		
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
			colecaoDadosTemporaria = repositorioFinanceiro.pesquisarValorServicoParceladoDevedoresDuvidososRecuperacaoPerdasSocietariasRetificadas(anoMesReferenciaContabil,idLocalidade, 
				idQuadra);
			
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
					pesquisarValorJurosDoParcelamentoDevedoresDuvidososRecuperacaoPerdasSocietariasRetificadas( anoMesReferenciaContabil, idLocalidade, idQuadra);
		
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
			colecaoDadosTemporaria  = repositorioFinanceiro.pesquisarValorPorTipoFinanciamentoDevedoresDuvidososRecuperacaoPerdasSocietariasRetificadas( 
					anoMesReferenciaContabil, idLocalidade, idQuadra);
			
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
			Collection<Object[]> colecaoDevolucoes  = repositorioFinanceiro.pesquisarDevolucoesValoresContaDevedoresDuvidososRecuperacaoPerdasSocietariasRetificadas( 
				anoMesReferenciaContabil,idLocalidade, idQuadra);
			
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
		
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
		
		return colecaoRetorno;
	}
	
	
	/**
	 * 
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 * 
	 * 
	 * @author Arthur Carvalho
	 * @date 30/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idParametrosDevedoresDuvidosos
	 * @return
	 * @throws ControladorException
	 */
	protected Collection<GerarResumoDevedoresDuvidososHelper> acumularResumoDevedoresDuvidososRecuperacaoPerdasSocietariasParcelada(int anoMesReferenciaContabil, 
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
					pesquisarValorAguaAgrupadoPorCategoriaDevedoresDuvidososRecuperacaoPerdasSocietariasParceladas( anoMesReferenciaContabil, idLocalidade, idQuadra);
			
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
					pesquisarValorEsgotoAgrupadoPorCategoriaDevedoresDuvidososRecuperacaoPerdasSocietariasParceladas( anoMesReferenciaContabil, idLocalidade,  idQuadra);
			
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
					pesquisarValorAguaParcelamentoAgrupadoPorCategoriaDevedoresDuvidososRecuperacaoPerdasSocietariasParceladas( anoMesReferenciaContabil, idLocalidade, idQuadra);
			
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
					pesquisarValorEsgotoParcelamentoAgrupadoPorCategoriaDevedoresDuvidososRecuperacaoPerdasSocietariasParceladas( anoMesReferenciaContabil, idLocalidade, idQuadra);
		
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
			colecaoDadosTemporaria = repositorioFinanceiro.pesquisarValorServicoParceladoDevedoresDuvidososRecuperacaoPerdasSocietariasParceladas(anoMesReferenciaContabil,idLocalidade, 
				idQuadra);
			
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
					pesquisarValorJurosDoParcelamentoDevedoresDuvidososRecuperacaoPerdasSocietariasParceladas( anoMesReferenciaContabil, idLocalidade, idQuadra);
		
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
			colecaoDadosTemporaria  = repositorioFinanceiro.pesquisarValorPorTipoFinanciamentoDevedoresDuvidososRecuperacaoPerdasSocietariasParceladas( 
					anoMesReferenciaContabil, idLocalidade, idQuadra);
			
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
			Collection<Object[]> colecaoDevolucoes  = repositorioFinanceiro.pesquisarDevolucoesValoresContaDevedoresDuvidososRecuperacaoPerdasSocietariasParceladas( 
				anoMesReferenciaContabil,idLocalidade, idQuadra);
			
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
		
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
		
		return colecaoRetorno;
	}
	
	
	private List<GerarResumoDevedoresDuvidososHelper> agruparValorConta(Collection<GerarResumoDevedoresDuvidososHelper> colecaoGerarResumoDevedoresHelperTemp , 
			List<GerarResumoDevedoresDuvidososHelper> colecaoGerarResumoDevedoresHelperPrincipal) {
		//Caso a colecao temporaria nao esteja vazia 
		//acumula os registros que estao na mesma quebra
		//e adiciona os novos registros.
		if(colecaoGerarResumoDevedoresHelperTemp != null && !colecaoGerarResumoDevedoresHelperTemp.isEmpty()){
			
			if(colecaoGerarResumoDevedoresHelperPrincipal.isEmpty()){
				colecaoGerarResumoDevedoresHelperPrincipal.addAll(colecaoGerarResumoDevedoresHelperTemp);
			}else{
				for(GerarResumoDevedoresDuvidososHelper gerarResumoDevedoresDuvidososHelperTemp : colecaoGerarResumoDevedoresHelperTemp ){
					if(colecaoGerarResumoDevedoresHelperPrincipal.contains(gerarResumoDevedoresDuvidososHelperTemp)){
						int posicao = colecaoGerarResumoDevedoresHelperPrincipal.indexOf(gerarResumoDevedoresDuvidososHelperTemp);

						GerarResumoDevedoresDuvidososHelper jaCadastrado = colecaoGerarResumoDevedoresHelperPrincipal.get(posicao);
						
						if ( gerarResumoDevedoresDuvidososHelperTemp.getValorBaixado()  != null && 
								!gerarResumoDevedoresDuvidososHelperTemp.getValorBaixado().equals("") && 
								!gerarResumoDevedoresDuvidososHelperTemp.getValorBaixado().equals("0") &&
								jaCadastrado.getValorBaixado() != null ) {
							
							jaCadastrado.setValorBaixado(jaCadastrado.getValorBaixado().add(gerarResumoDevedoresDuvidososHelperTemp.getValorBaixado()));
						}
						
					}else{
						
						if ( gerarResumoDevedoresDuvidososHelperTemp.getValorBaixado() != null && 
								!gerarResumoDevedoresDuvidososHelperTemp.getValorBaixado().equals("") && 
								!gerarResumoDevedoresDuvidososHelperTemp.getValorBaixado().equals("0") ) {
							colecaoGerarResumoDevedoresHelperPrincipal.add(gerarResumoDevedoresDuvidososHelperTemp);
						}
					}
				}
			}
		}
		return colecaoGerarResumoDevedoresHelperPrincipal;
	}
	
	/**
	 * Gera os lançamentos dos devedores duvidosos.
	 *
	 * [UC0841] Gerar Lançamentos Contábeis Volumes Consumidos Não Faturados
	 *
	 * @author Arthur Carvalho
	 * @date 28/11/2011
	 *
	 * @param idLocalidade
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void gerarLancamentosContabeisVolumesConsumidosNaoFaturados( Integer idLocalidade,  int idFuncionalidadeIniciada) throws ControladorException{
		
		
		System.out.println("Localidade " + idLocalidade);

		int idUnidadeIniciada = 0;

		/*
		 * Registrar o início do processamento da Unidade de
		 * Processamento do Batch
		*/
		idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,UnidadeProcessamento.LOCALIDADE,(idLocalidade));

		Integer idTipoPerda = PerdasTipo.VOLUMES_CONSUMIDOS_NAO_FATURADOS;
		
		//[FS0001 - Validar Referência Contábil]
		Integer anoMesFaturamentoAtual =  getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento();
	
		
		FiltroLancamentoOrigem filtroLancamentoOrigem = new FiltroLancamentoOrigem();
		filtroLancamentoOrigem.adicionarParametro( new ParametroSimples(FiltroLancamentoOrigem.PERDAS_TIPO, idTipoPerda));
		
		Integer lancamentoOrigemID = null; 
		Collection<LancamentoOrigem> colecaoLancamentoOrigem = getControladorUtil().pesquisar(filtroLancamentoOrigem, LancamentoOrigem.class.getName());
		if ( colecaoLancamentoOrigem != null && !colecaoLancamentoOrigem.isEmpty() ) {
			LancamentoOrigem origem = (LancamentoOrigem) Util.retonarObjetoDeColecao(colecaoLancamentoOrigem);
			lancamentoOrigemID = origem.getId();
		}

		//remove os lançamentos contábeis já gerados
		this.removerLancamentoContabil(anoMesFaturamentoAtual,idLocalidade, lancamentoOrigemID);
		
		try {
			
			/*
			 *  Pesquisa os dados do resumo dos devedores duvidosos 
			 *  para o ano/mês de referência atual e para a localidade informada.
			 *  
			 *  0 - id da localidade
			 *  1 - id do tipo de lançamento
			 *  2 - id do item de lançamento
			 *  3 - id do item de lançamento contábil
			 *  4 - id da categoria
			 *  5 - soma do valor do resumo dos devedores duvidosos 
			 */
			Collection<Object[]> colecaoDadosResumoDevedoresDuvidosos = repositorioFinanceiro.obterDadosResumoVolumesConsumidosNaoFaturados(anoMesFaturamentoAtual, idLocalidade);
		
			/*
			 * Caso exista resumo de devedores duvidosos para a localidade e o ano/mês 
			 * cria o lancamento contábil junto com seus items 
			 * para cada conjunto de mesmo tipo de lançamento
			 */
			if (colecaoDadosResumoDevedoresDuvidosos != null && !colecaoDadosResumoDevedoresDuvidosos.isEmpty()){
				
				//flag utilizada somente quando for a primeira entrada
				boolean flagPrimeiraVez = true;
				int idTipoLancamentoTemp = -1;
				Collection<Object[]> colecaoDadosResumoPorTipoLancamento = new ArrayList();
				
				// definição da origem do lançamento
				LancamentoOrigem lancamentoOrigem = new LancamentoOrigem();
				lancamentoOrigem.setId(lancamentoOrigemID);
				
				//Cria a variável que vai armazenar o lançamento contábil
				LancamentoContabil lancamentoContabilInsert = null;
				
				//laço para gerar os lançamentos por grupo de tipo de lançamento
				for(Object[] dadosResumoDevedoresDuvidosos : colecaoDadosResumoDevedoresDuvidosos){
					
					//recupera o tipo de lançamento atual 
					Integer idTipoLancamento = (Integer) dadosResumoDevedoresDuvidosos[1];
					
					/*
					 * Caso seja a primeira entrada do "for"
					 * adiciona os dados a coleção e atualiza o item temporario
					 * criando também o lançamento contabil que ira ser inserido
					 * junto com seus items.
					 * 
					 *  Caso contrário (não seja a primeira entrada do laço "for")
					 *  verifica se o item de lançamento mudou, caso não tenha mudado 
					 *  adiciona os dados ao conjunto do mesmo item
					 *  caso contrário, se mudou o item de lançamento o conjunto está fechado
					 *  para o lançamento contábil e chama o método para inserir o
					 *  lançamento contábil junto com seus itens. 
					 */
					if(flagPrimeiraVez){
						colecaoDadosResumoPorTipoLancamento.add(dadosResumoDevedoresDuvidosos);
						flagPrimeiraVez = false;
						idTipoLancamentoTemp = idTipoLancamento;
						
						LancamentoTipo tipoLancamento = new LancamentoTipo();
						tipoLancamento.setId(idTipoLancamento);
						
						Localidade localidade = new Localidade();
						localidade.setId(idLocalidade);
						
						//cria o lançamento contábil para ser inserido 
						lancamentoContabilInsert = new LancamentoContabil();
						lancamentoContabilInsert.setAnoMes(anoMesFaturamentoAtual);
						lancamentoContabilInsert.setLancamentoOrigem(lancamentoOrigem);
						lancamentoContabilInsert.setLancamentoTipo(tipoLancamento);
						lancamentoContabilInsert.setLocalidade(localidade);
						lancamentoContabilInsert.setRecebimentoTipo(null);
						lancamentoContabilInsert.setUltimaAlteracao(new Date());
					}else{
						/*
						 * Caso ainda seja o mesmo item adicona os dados para 
						 * ser gerado os itens do lançamento para o mesmo lançamento.
						 * Caso contrário chama o metódo para inseri os itens e o lançamento contábil.
						 */
						if(idTipoLancamento == idTipoLancamentoTemp){
							colecaoDadosResumoPorTipoLancamento.add(dadosResumoDevedoresDuvidosos);
						}else{
							/* metódo para inserir o lançamento contábil assim como seus itens */
							this.inserirLancamentoContabilItemVolumesConsumidosNaoFaturados(lancamentoContabilInsert, colecaoDadosResumoPorTipoLancamento, idTipoPerda);
							
							//limpa coleção e adiciona os dados do resumo atual
							colecaoDadosResumoPorTipoLancamento.clear();
							colecaoDadosResumoPorTipoLancamento.add(dadosResumoDevedoresDuvidosos);
							
							LancamentoTipo tipoLancamento = new LancamentoTipo();
							tipoLancamento.setId(idTipoLancamento);
							
							Localidade localidade = new Localidade();
							localidade.setId(idLocalidade);
							
							//cria o lançamento contábil que vai ser inserido
							lancamentoContabilInsert = new LancamentoContabil();
							lancamentoContabilInsert.setAnoMes(anoMesFaturamentoAtual);
							lancamentoContabilInsert.setLancamentoOrigem(lancamentoOrigem);
							lancamentoContabilInsert.setLancamentoTipo(tipoLancamento);
							lancamentoContabilInsert.setLocalidade(localidade);
							lancamentoContabilInsert.setRecebimentoTipo(null);
							lancamentoContabilInsert.setUltimaAlteracao(new Date());
							
							//atualiza o tipo de lançamento temporário com o novo valor
							idTipoLancamentoTemp = idTipoLancamento;
						}
					}
				}
				
				/*
				 * Último registro
				 * Esse "if" é para verificar se ainda existe um último registro na coleção
				 * caso exista algum item, adiciona o lançamento contábil junto com o item. 
				 */
				if(colecaoDadosResumoPorTipoLancamento != null && colecaoDadosResumoPorTipoLancamento.size() > 0 ){
					this.inserirLancamentoContabilItemVolumesConsumidosNaoFaturados(lancamentoContabilInsert, colecaoDadosResumoPorTipoLancamento, idTipoPerda);
					colecaoDadosResumoPorTipoLancamento = null;
				}
			}
		
		getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);
	
	} catch (Exception ex) {
		ex.printStackTrace();
		getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,idUnidadeIniciada, true);
		throw new EJBException(ex);
	}
			
	}
	
	/**
	 * Gera o lançamento contábil junto com seus itens. 
	 *
	 * @author Arthur Carvalho
	 *
	 * @param lancamentoContabil
	 * @param colecaoDadosResumoPorTipoLancamento
	 * @throws ControladorException
	 */
	protected void inserirLancamentoContabilItemVolumesConsumidosNaoFaturados(LancamentoContabil lancamentoContabil,Collection<Object[]> colecaoDadosResumoPorTipoLancamento, 
			Integer idPerdaTipo ) throws ControladorException {
		try{
			/*
			 * Caso exista dados para os itens do resumo do faturamento.
			 */
			if(colecaoDadosResumoPorTipoLancamento != null && !colecaoDadosResumoPorTipoLancamento.isEmpty()){
				
				Collection colecaoLancamentoContabilItem = new ArrayList();
				
				//flag que indica se o lançamento contábil já foi inserido ou não. 
				boolean flagInseridoLancamentoContabil = false;
				
				/*
				 * Dados do resumo dos devedores duvidosos
				 *  0 - id da localidade
				 *  1 - id do tipo de lançamento
				 *  2 - id do item de lançamento
				 *  3 - id do item de lançamento contábil
				 *  4 - id da categoria
				 *  5 - soma do valor do resumo dos devedores duvidosos
				 */
				for(Object[] dadosResumoDevedoresDuvidosos : colecaoDadosResumoPorTipoLancamento){
					
					//recupera os dados do resumo dos devedores duvidosos
					Integer idLancamentoTipo =         (Integer) dadosResumoDevedoresDuvidosos[1];
					Integer idLancamentoItem =         (Integer) dadosResumoDevedoresDuvidosos[2];
					Integer idCategoria =              (Integer) dadosResumoDevedoresDuvidosos[3];
					BigDecimal valorLancamento =       (BigDecimal) dadosResumoDevedoresDuvidosos[4]; 
					
					/* 
					 * Verifica se existe conta contábil para o item que vai ser inserido 
					 * 
					 * 0 - id conta contábil do débito
					 * 1 - id conta contábil crédito 
					 * 2 - descrição do histórico do débito
					 * 3 - descrição do histórico do crédito
					 */
					Object[] dadosContaContabil = this.repositorioFinanceiro.obterParametrosContabilVolumesConsumidosNaoFaturados(idCategoria, idLancamentoItem, 
						idLancamentoTipo);
					
					if(dadosContaContabil != null){
						Integer idLancamentoContabil = null;
						/*
						 * Caso exista dados para a conta contábil e o item contábil 
						 * ainda não foi inserido 
						 * inseri o item contábil na base.
						 */
						if(!flagInseridoLancamentoContabil){
							idLancamentoContabil = (Integer)getControladorUtil().inserir(lancamentoContabil);
							lancamentoContabil.setId(idLancamentoContabil);
							flagInseridoLancamentoContabil = true;
						}
						
						//recupera os dados da conta contábil para o item 
						//do resumo dos devedores duvidosos.
						Integer idContaContabilDebito = (Integer) dadosContaContabil[0];
						Integer idContaContabilCredito = (Integer) dadosContaContabil[1];
						String descricaoHistoricoDebito = (String) dadosContaContabil[2];
						String descricaoHistoricoCredito = (String) dadosContaContabil[3];
						
						//cria os indicadores de débito e crédito.
						Short indicadorDebito = new Short("2"); 
						Short indicadorCredito = new Short("1");
						
						Date ultimaAlteracao = new Date();
						
						//cria as contas contábeis de crédito e débito.
						ContaContabil contaContabilCredito = new ContaContabil();
						contaContabilCredito.setId(idContaContabilCredito);
						
						ContaContabil contaContabilDebito = new ContaContabil();
						contaContabilDebito.setId(idContaContabilDebito);
						
						//Caso seja maior que zero
						if ( valorLancamento.compareTo(BigDecimal.ZERO) == -1 ) {
							/**  Item de crédito */
							LancamentoContabilItem lancamentoContabilItemCredito = new LancamentoContabilItem(indicadorCredito, 
									valorLancamento, 
									descricaoHistoricoCredito, 
									ultimaAlteracao, 
									lancamentoContabil, 
									contaContabilDebito);
							
							colecaoLancamentoContabilItem.add(lancamentoContabilItemCredito);
							
							/** Item de débito */
							LancamentoContabilItem lancamentoContabilItemDebito = new LancamentoContabilItem(indicadorDebito, 
									valorLancamento, 
									descricaoHistoricoDebito, 
									ultimaAlteracao, 
									lancamentoContabil, 
									contaContabilCredito);
							
							colecaoLancamentoContabilItem.add(lancamentoContabilItemDebito);
						
						} else {
							
							/**  Item de crédito */
							LancamentoContabilItem lancamentoContabilItemCredito = new LancamentoContabilItem(indicadorCredito, 
									valorLancamento, 
									descricaoHistoricoCredito, 
									ultimaAlteracao, 
									lancamentoContabil, 
									contaContabilCredito);
							
							colecaoLancamentoContabilItem.add(lancamentoContabilItemCredito);
						
							/** Item de débito */
							LancamentoContabilItem lancamentoContabilItemDebito = new LancamentoContabilItem(indicadorDebito, 
									valorLancamento, 
									descricaoHistoricoDebito, 
									ultimaAlteracao, 
									lancamentoContabil, 
									contaContabilDebito);
							
							colecaoLancamentoContabilItem.add(lancamentoContabilItemDebito);
							
						}
					}
				}
				//inseri os itens de lançamento contábeis. 
				getControladorBatch().inserirColecaoObjetoParaBatch(colecaoLancamentoContabilItem); 
			}
	
		} catch (Exception ex) {
			throw new EJBException(ex);
		}
	}

	/**
	 * [UC1272] Gerar Arquivos EFD-PIS/Confins
	 * 
	 * [SB0001] Gerar registro C600 do EFD-PIS/Confins Opção 1
	 * [SB0004] Gerar registro C600 do EFD-PIS/Confins Opção 2
	 * 
	 * @author Raphael Rossiter
	 * @date 31/01/2012
	 * 
	 * @param anoMesReferencia
	 * @param municipio
	 * @param indicadorTipoGeracao
	 * @return StringBuilder
	 * @throws ControladorException
	 */
	public StringBuilder gerarLinhaRegistroC600(Integer anoMesReferencia, Municipio municipio, Short indicadorTipoGeracao) throws ControladorException {
		
		StringBuilder arquivoTextoRegistroC600 = new StringBuilder();
		
		//01 - REG
		arquivoTextoRegistroC600.append("|C600");
		
		//02 - COD_MOD
		arquivoTextoRegistroC600.append("|29");
		
		//03 - COD_MUN
		if (municipio != null){
			arquivoTextoRegistroC600.append("|" + municipio.getCodigoIbge().trim());
		}
		else{
			arquivoTextoRegistroC600.append("|");
		}
		
		//04 - SER
		arquivoTextoRegistroC600.append("|");
		
		//05 - SUB
		arquivoTextoRegistroC600.append("|");
		
		//06 - COD_CONS
		arquivoTextoRegistroC600.append("|");
		
		//07 - QTD_CONS
		Integer quantidadeContasResumoFaturamentoSimulacao = 
			this.obterQuantidadeContasResumoFaturamentoSimulacao(anoMesReferencia, municipio);
		
		if (quantidadeContasResumoFaturamentoSimulacao != null){
			
			arquivoTextoRegistroC600.append("|" + quantidadeContasResumoFaturamentoSimulacao.intValue());
		}
		else{
			
			arquivoTextoRegistroC600.append("|0");
		}
		
		//08 - QTD_CANC
		arquivoTextoRegistroC600.append("|");
		
		//09 - DT_DOC
		Date ultimaDiaMes = Util.obterUltimaDataMes(Util.obterMes(anoMesReferencia), Util.obterAno(anoMesReferencia));
		arquivoTextoRegistroC600.append("|" + Util.formatarDataSemBarraDDMMAAAA(ultimaDiaMes));
		
		//10 - VL_DOC
		BigDecimal valorCobradoEmConta = null;
		if(indicadorTipoGeracao.equals(Short.parseShort("1"))){
			
			valorCobradoEmConta = this.obterValorItemArquivoEFDPisConfins(
				Collections.singletonList(LancamentoTipo.TOTAL_COBRADO_NAS_CONTAS), 
				Collections.singletonList(LancamentoItem.TOTAL_COBRADO_CONTAS), anoMesReferencia, municipio);
		}else{
			valorCobradoEmConta = this.obterValorItemArquivoEFDPisConfins(
				Collections.singletonList(LancamentoTipo.RECEITA_LIQUIDA), 
				Collections.singletonList(LancamentoItem.TOTAL), anoMesReferencia, municipio);
		}
		
		if (valorCobradoEmConta != null){
		
			arquivoTextoRegistroC600.append("|" + (String.valueOf(valorCobradoEmConta.setScale(2)).replace(".",",")));
		}
		else{
			
			arquivoTextoRegistroC600.append("|0,00");
		}
		
		//11 - VL_DESC
		Collection<Integer> colecaoLancamentoTipo = new ArrayList();
		if(indicadorTipoGeracao.equals(Short.parseShort("1"))){
			colecaoLancamentoTipo.add(LancamentoTipo.DEVOLUCAO__VALORES_EM_CONTA);
			colecaoLancamentoTipo.add(LancamentoTipo.IMPOSTOS_DEDUZIDOS_EM_CONTA);
			colecaoLancamentoTipo.add(LancamentoTipo.VALORES_COBRADOS_INDEVIDAMENTE);
		
			BigDecimal valorDesconto = this.obterValorItemArquivoEFDPisConfins(
					colecaoLancamentoTipo, null, anoMesReferencia, municipio);
			
			if (valorDesconto != null){
			
				arquivoTextoRegistroC600.append("|" + (String.valueOf(valorDesconto.setScale(2)).replace(".",",")));
			}else{			
				arquivoTextoRegistroC600.append("|0,00");
			}
		}else{
			arquivoTextoRegistroC600.append("|");
		}
		
		//12 - CONS
		arquivoTextoRegistroC600.append("|");
		
		//13 - VL_FORN
		arquivoTextoRegistroC600.append("|");
		
		//14 - VL_SERV_NT
		arquivoTextoRegistroC600.append("|");
		
		//15 - VL_TERC
		arquivoTextoRegistroC600.append("|");
		
		//16 - VL_DA
		arquivoTextoRegistroC600.append("|");
		
		//17 - VL_BC_ICMS
		arquivoTextoRegistroC600.append("|");
		
		//18 - VL_ICMS
		arquivoTextoRegistroC600.append("|");
		
		//19 - VL_BC_ICMS_ST
		arquivoTextoRegistroC600.append("|");
		
		//20 - VL_ICMS_ST
		arquivoTextoRegistroC600.append("|");
		
		//21 - VL_PIS e 22 - VL_COFINS
		colecaoLancamentoTipo.clear();
		Collection<Integer> colecaoLancamentoItem = new ArrayList();
		
		if(indicadorTipoGeracao.equals(Short.parseShort("1"))){
			colecaoLancamentoTipo.add(LancamentoTipo.AGUA);
			colecaoLancamentoTipo.add(LancamentoTipo.ESGOTO);
		
			colecaoLancamentoItem.add(LancamentoItem.AGUA);
			colecaoLancamentoItem.add(LancamentoItem.ESGOTO);
		}else{
			colecaoLancamentoTipo.add(LancamentoTipo.RECEITA_LIQUIDA);
			colecaoLancamentoItem.add(LancamentoItem.TOTAL);
		}
		BigDecimal valorAguaEsgoto = this.obterValorItemArquivoEFDPisConfins(
				colecaoLancamentoTipo, colecaoLancamentoItem, anoMesReferencia, municipio);
		
		if (valorAguaEsgoto != null){
			
			BigDecimal valorPis = valorAguaEsgoto.multiply(new BigDecimal("0.0165"));
			BigDecimal valorCofins = valorAguaEsgoto.multiply(new BigDecimal("0.076"));
			
			arquivoTextoRegistroC600.append("|" + (String.valueOf(valorPis.setScale(2, BigDecimal.ROUND_HALF_DOWN)).replace(".",",")));
			arquivoTextoRegistroC600.append("|" + (String.valueOf(valorCofins.setScale(2, BigDecimal.ROUND_HALF_DOWN)).replace(".",",")));
		}
		else{
			
			arquivoTextoRegistroC600.append("|0,00");
			arquivoTextoRegistroC600.append("|0,00");
		}
		
		
		arquivoTextoRegistroC600.append("|");
		arquivoTextoRegistroC600.append(System.getProperty("line.separator"));
		
		return arquivoTextoRegistroC600;
	}
	
	/**
	 * [UC1272]GerarArquivoEFD-PIS-Confins
	 * 
	 * Gera o Arquivo EFD Pis/Confins segundo o tipo e anoMes informardo
	 * 
	 * @author Raphael Rossiter, Erivan Sousa
	 * @param tipoArquivo
	 * @param ArquivoEFDPisConfins
	 * @throws ControladorException
	 * 
	 * @date 30/01/2012
	 */
	public File gerarArquivoEFDPisConfins(int tipoArquivo[], Integer anoMesReferencia, boolean quebraPorMunicipio, Short indicadorTipoGeracao) throws ControladorException {
		
		StringBuilder arquivoTextoRegistro = new StringBuilder();
		
		/*
		 * 1. O sistema deverá gerar o arquivo TXT do tipo C600 (uma linha para cada município) conforme layout e correspondência de campos 
		 * a seguir, a partir das linhas da tabela de Resumo do Faturamento com mês/ano de referência igual ao mês/ano de referência 
		 * informado e agrupadas por município (ocorrências da tabela FINANCEIRO.RESUMO_FATURAMENTO com RFAT_AMREFERENCIA= ano e mês 
		 * informados pelo usuário, agrupadas por MUNI_CDIBGE da tabela MUNICIPIO com MUNI_ID=MUNI_IDPRINCIPAL da tabela LOCALIDADE 
		 * com LOCA_ID=LOCA_ID  da tabela FINANCEIRO.RESUMO_FATURAMENTO);
		 */
		int tipoArquivoAtual = -1;
		
		if (quebraPorMunicipio){
		
			Collection<Municipio> colecaoMunicipios = this.obterMunicipiosGerarArquivoEFDPisConfins(anoMesReferencia);
			
			if(colecaoMunicipios == null || colecaoMunicipios.isEmpty()){
				throw new ControladorException("atencao.pesquisa.nenhumresultado");
			}
		
			Iterator iterator = colecaoMunicipios.iterator();
			Municipio municipio = null;
			int count = 1;
			int quantidade = colecaoMunicipios.size();
		
			while(iterator.hasNext()){
			
				municipio = (Municipio) iterator.next();

				for (int i = 0; i < tipoArquivo.length; i++) {
					tipoArquivoAtual = tipoArquivo[i];
			
					switch (tipoArquivoAtual) {
					case ConstantesSistema.NUMERO_NAO_INFORMADO:
						//REGISTRO C600
						arquivoTextoRegistro.append(this.gerarLinhaRegistroC600(anoMesReferencia, municipio, indicadorTipoGeracao));
				
						//REGISTRO C601
						arquivoTextoRegistro.append(this.registroC601(anoMesReferencia, municipio, indicadorTipoGeracao));
				
						//REGISTRO C605
						arquivoTextoRegistro.append(this.registroC605(anoMesReferencia, municipio, indicadorTipoGeracao));
				
						//REGISTRO F100
						arquivoTextoRegistro.append(this.registroF100(anoMesReferencia, municipio));
				
						//REGISTRO F600
						arquivoTextoRegistro.append(this.registroF600(anoMesReferencia, municipio));

						//REGISTRO M230
						arquivoTextoRegistro.append(this.registroM230(anoMesReferencia, municipio));
						
						//REGISTRO M630
						arquivoTextoRegistro.append(this.registroM630(anoMesReferencia, municipio));
				
				
						break;
			
					case ConstantesSistema.TIPO_C600:
						//REGISTRO C600
						arquivoTextoRegistro.append(this.gerarLinhaRegistroC600(anoMesReferencia, municipio, indicadorTipoGeracao));
				
						//REGISTRO C601
						arquivoTextoRegistro.append(this.registroC601(anoMesReferencia, municipio, indicadorTipoGeracao));
				
						//REGISTRO C605
						arquivoTextoRegistro.append(this.registroC605(anoMesReferencia, municipio, indicadorTipoGeracao));
					
						break;
					case ConstantesSistema.TIPO_F100:					
						//REGISTRO F100
						arquivoTextoRegistro.append(this.registroF100(anoMesReferencia, municipio));
					
						break;
					case ConstantesSistema.TIPO_F600:
						//REGISTRO F600
						arquivoTextoRegistro.append(registroF600(anoMesReferencia, municipio));
				
						break;
					case ConstantesSistema.TIPO_M230:
						//REGISTRO M230
						arquivoTextoRegistro.append(this.registroM230(anoMesReferencia, municipio));
				
						break;
					case ConstantesSistema.TIPO_M630:
						//REGISTRO M630
						arquivoTextoRegistro.append(this.registroM630(anoMesReferencia, municipio));
				
						break;

					default:
						break;
					}//fim do switch
				}//fim do for		
				System.out.println("Quantidade = " + count + "/" + quantidade);
			
				count++;
			}//fim do while
		}
		else{
			for (int i = 0; i < tipoArquivo.length; i++) {
				tipoArquivoAtual = tipoArquivo[i];
				switch (tipoArquivoAtual) {
				case ConstantesSistema.NUMERO_NAO_INFORMADO:
					//REGISTRO C600
					arquivoTextoRegistro.append(this.gerarLinhaRegistroC600(anoMesReferencia, null, indicadorTipoGeracao));
			
					//REGISTRO C601
					arquivoTextoRegistro.append(this.registroC601(anoMesReferencia, null, indicadorTipoGeracao));
				
					//REGISTRO C605
					arquivoTextoRegistro.append(this.registroC605(anoMesReferencia, null, indicadorTipoGeracao));
			
					//REGISTRO F100
					arquivoTextoRegistro.append(this.registroF100(anoMesReferencia, null));
			
					//REGISTRO F600
					arquivoTextoRegistro.append(this.registroF600(anoMesReferencia, null));
					
					//REGISTRO M230
					arquivoTextoRegistro.append(this.registroM230(anoMesReferencia, null));
					
					//REGISTRO M630
					arquivoTextoRegistro.append(this.registroM630(anoMesReferencia, null));
			
					break;
				case ConstantesSistema.TIPO_C600:				
					//REGISTRO C600
					arquivoTextoRegistro.append(this.gerarLinhaRegistroC600(anoMesReferencia, null, indicadorTipoGeracao));
			
					//REGISTRO C601
					arquivoTextoRegistro.append(this.registroC601(anoMesReferencia, null, indicadorTipoGeracao));
			
					//REGISTRO C605
					arquivoTextoRegistro.append(this.registroC605(anoMesReferencia, null, indicadorTipoGeracao));
			
					break;
				case ConstantesSistema.TIPO_F100:				
					//REGISTRO F100
					arquivoTextoRegistro.append(this.registroF100(anoMesReferencia, null));
			
					break;
				case ConstantesSistema.TIPO_F600:
					//REGISTRO F600
					arquivoTextoRegistro.append(this.registroF600(anoMesReferencia, null));
			
					break;
				case ConstantesSistema.TIPO_M230:
					//REGISTRO M230
					arquivoTextoRegistro.append(this.registroM230(anoMesReferencia, null));
			
					break;
				case ConstantesSistema.TIPO_M630:
					//REGISTRO M630
					arquivoTextoRegistro.append(this.registroM630(anoMesReferencia, null));
			
					break;

				default:
					break;
				}//fim do switch
			}
		}//fim do else
		/*
		 * Gerando o arquivo zip
		 */
		String nomeZip = "TESTE";
		BufferedWriter out = null;
		ZipOutputStream zos = null;
		File compactadoTipo = new File(nomeZip + ".zip");
		File leituraTipo = new File(nomeZip + ".txt");
		
		/*
		 * Caso o arquivo txt não esteja vazio 
		 * adiciona o txt ao arquivo zip.
		 */
		if (arquivoTextoRegistro != null && arquivoTextoRegistro.length() != 0) {
			
			try {
				System.out.println("CRIANDO ZIP");
				zos = new ZipOutputStream(new FileOutputStream(compactadoTipo));

				out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(leituraTipo.getAbsolutePath())));
				out.write(arquivoTextoRegistro.toString());
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
		
		return compactadoTipo;
	}
	
	/**
	 * [UC1272] Gerar Arquivos EFD-PIS/Confins
	 * 
	 * [SB0001] Gerar registro C600 do EFD-PIS/Confins Opção 1
	 * [SB0004] Gerar registro C600 do EFD-PIS/Confins Opção 2
	 * 
	 * @author Raphael Rossiter
	 * @date 31/01/2012
	 * 
	 * @param anoMesReferencia
	 * @return Collection<Municipio>
	 * @throws ControladorException
	 */
	protected Collection<Municipio> obterMunicipiosGerarArquivoEFDPisConfins(Integer anoMesReferencia) throws ControladorException{
		
		Collection<Municipio> retorno = null;
		
		try{
			 
			retorno = repositorioFinanceiro.obterMunicipiosGerarArquivoEFDPisConfins(anoMesReferencia);
		}
		catch (ErroRepositorioException e) {
			e.printStackTrace();
			new ControladorException(e.getMessage());
		}
		
		return retorno;
	}
	
	/**
	 * [UC1272] Gerar Arquivos EFD-PIS/Confins
	 * 
	 * [SB0001] Gerar registro C600 do EFD-PIS/Confins Opção 1
	 * [SB0004] Gerar registro C600 do EFD-PIS/Confins Opção 2
	 * 
	 * @author Raphael Rossiter
	 * @date 31/01/2012
	 * 
	 * @param anoMesReferencia
	 * @param idMunicipio
	 * @return Integer
	 * @throws ControladorException
	 */
	protected Integer obterQuantidadeContasResumoFaturamentoSimulacao(Integer anoMesReferencia, Municipio municipio) throws ControladorException{
		
		Integer retorno = null;
		
		try{
			 
			if (municipio != null){
				
				retorno = repositorioFinanceiro.obterQuantidadeContasResumoFaturamentoSimulacao(anoMesReferencia, municipio.getId());
			}
			else{
				
				retorno = repositorioFinanceiro.obterQuantidadeContasResumoFaturamentoSimulacao(anoMesReferencia);
			}
		}
		catch (ErroRepositorioException e) {
			e.printStackTrace();
			new ControladorException(e.getMessage());
		}
		
		return retorno;
	}
	
	/**
	 * [UC1272] Gerar Arquivos EFD-PIS/Confins
	 * 
	 * [SB0001] Gerar registro C600 do EFD-PIS/Confins Opção 1
	 * [SB0004] Gerar registro C600 do EFD-PIS/Confins Opção 2
	 * 
	 * @author Raphael Rossiter
	 * @date 31/01/2012
	 * 
	 * @param anoMesReferencia
	 * @param idMunicipio
	 * @return Integer
	 * @throws ControladorException
	 */
	protected BigDecimal obterValorItemArquivoEFDPisConfins(Collection<Integer> lancTipo, 
			Collection<Integer> lancItem, int anoMesReferencia, Municipio municipio) throws ControladorException{
		
		BigDecimal retorno = null;
		
		try{
			 
			if (municipio != null){
				
				retorno = repositorioFinanceiro.obterValorItemArquivoEFDPisConfins(lancTipo, lancItem, anoMesReferencia, municipio.getId());
			}
			else{
				
				retorno = repositorioFinanceiro.obterValorItemArquivoEFDPisConfins(lancTipo, lancItem, anoMesReferencia);
			}
			
		}
		catch (ErroRepositorioException e) {
			e.printStackTrace();
			new ControladorException(e.getMessage());
		}
		
		return retorno;
	}
	
	/**
	 * [UC1272]GerarArquivoEFD-PIS-Confins
	 * [SB0002] - Gerar registro C601 do EFD-PIS/Confins Opção 1
	 * [SB0005] - Gerar registro C601 do EFD-PIS/Confins Opção 2
	 * 
	 * Gerar linhas Registro C601
	 * 
	 * @author Erivan Sousa
	 * @param anoMesReferencia
	 * @param municipio
	 * @param indicadorTipoGeracao
	 * @return StringBuilder com linhas do registro C601
	 * @throws ControladorException 
	 * 
	 * @date 30/01/2012
	 * */	
	private StringBuilder registroC601(int anoMesReferencia, Municipio municipio, Short indicadorTipoGeracao) throws ControladorException{
		StringBuilder builder = new StringBuilder();
		
		Collection<Integer> colecaoLancamentoTipo = null;
		Collection<Integer> colecaoLancamentoItem = null;
		BigDecimal vlItemFaturamento = null;
		
		//Monta a primeira linha
		if(indicadorTipoGeracao.equals(Short.parseShort("1"))){
			colecaoLancamentoTipo = new ArrayList(Arrays.asList(new Integer[]{LancamentoTipo.AGUA,
			                                                                  LancamentoTipo.ESGOTO}));
			colecaoLancamentoItem = new ArrayList(Arrays.asList(new Integer[]{LancamentoItem.AGUA,
			                                                                  LancamentoItem.ESGOTO}));
		}else{
			colecaoLancamentoTipo = new ArrayList(Arrays.asList(new Integer[]{LancamentoTipo.RECEITA_LIQUIDA}));
			colecaoLancamentoItem = new ArrayList(Arrays.asList(new Integer[]{LancamentoItem.TOTAL}));
		}
		
		
		vlItemFaturamento = this.obterValorItemArquivoEFDPisConfins(colecaoLancamentoTipo, colecaoLancamentoItem, anoMesReferencia, municipio);
		//Primeira linha
		if(indicadorTipoGeracao.equals(Short.parseShort("1"))){
			
			/* - Erivan -
			 * [SB0002] – Gerar registro C601 do EFD-PIS/Confins Opção 1
			 */
			builder.append("|C601");//REG
			builder.append("|29");//COD_MOD
			if(municipio != null){
				builder.append("|" + municipio.getCodigoIbge().trim());//COD_MUM
			}else{
				builder.append("|");//COD_MUM
			}
			builder.append("|");//SER
			builder.append("|");//SUB
			builder.append("|01");//CST_COFINS
			builder.append("|"+ (String.valueOf(vlItemFaturamento.setScale(2)).replace(".",",")));//VL_ITEM
			builder.append("|"+ (String.valueOf(vlItemFaturamento.setScale(2)).replace(".",",")));//VL_BC_COFINS
			builder.append("|1,65");//ALIQ_COFINS
			
			BigDecimal valorPis = vlItemFaturamento.multiply(new BigDecimal("0.0165"));
			builder.append("|" + (String.valueOf(valorPis.setScale(2, BigDecimal.ROUND_HALF_DOWN)).replace(".",",")));//VL_PIS
			
			builder.append("||");//COD_CTA
		}else{
			
			/* - Erivan - 
			 * [SB0005] – Gerar registro C601 do EFD-PIS/Confins Opção 2
			 */
			builder.append("|C601");//REG
			builder.append("|01");//CST_COFINS
			builder.append("|"+ (String.valueOf(vlItemFaturamento.setScale(2)).replace(".",",")));//VL_ITEM
			builder.append("|"+ (String.valueOf(vlItemFaturamento.setScale(2)).replace(".",",")));//VL_BC_COFINS
			builder.append("|1,65");//ALIQ_COFINS
			
			BigDecimal valorPis = vlItemFaturamento.multiply(new BigDecimal("0.0165"));
			builder.append("|" + (String.valueOf(valorPis.setScale(2, BigDecimal.ROUND_HALF_DOWN)).replace(".",",")));//VL_PIS
			
			builder.append("||");//COD_CTA
		}
		builder.append(System.getProperty("line.separator"));
		
		//Limpa objetos
		colecaoLancamentoTipo = null;
		colecaoLancamentoItem = null;
		vlItemFaturamento = null;
				
		//Monta a segunda linha;
		//A segunda linha só é gerada quando o indicador de tipo de geracao for igual ao tipo de geracao 1
		if(indicadorTipoGeracao.equals(Short.parseShort("1"))){
			colecaoLancamentoTipo = new ArrayList(Arrays.asList(new Integer[]{LancamentoTipo.FINANCIAMENTOS_COBRADOS, 
				                                                                  LancamentoTipo.PARCELAMENTOS_COBRADOS, 
				                                                                  LancamentoTipo.DOACOES_COBRADAS_EM_CONTA}));
		
			vlItemFaturamento = this.obterValorItemArquivoEFDPisConfins(colecaoLancamentoTipo, colecaoLancamentoItem, anoMesReferencia, municipio);
		
			//segunda linha
			/* - Erivan -
			 * [SB0002] – Gerar registro C601 do EFD-PIS/Confins Opção 1
			 */
			builder.append("|C601");//REG
			builder.append("|29");//COD_MOD
			if(municipio != null){
				builder.append("|" + municipio.getCodigoIbge().trim());//COD_MUM
			}else{
				builder.append("|");//COD_MUM
			}
			builder.append("|");//SER
			builder.append("|");//SUB
			builder.append("|07");//CST_COFINS
			builder.append("|" + (String.valueOf(vlItemFaturamento.setScale(2, BigDecimal.ROUND_HALF_DOWN)).replace(".",",")));//VL_ITEM
			builder.append("|0,00");//VL_BC_COFINS
			builder.append("|1,65");//ALIQ_COFINS
			builder.append("|0,00");//VL_COFINS
			builder.append("||");//COD_CTA
			builder.append(System.getProperty("line.separator"));
		}
		return builder;
	} 
	
	/**
	 * [UC1272]GerarArquivoEFD-PIS-Confins
	 * [SB0003] - Gerar registro C605 do EFD-PIS/Confins Opção 1
	 * [SB0006] - Gerar registro C605 do EFD-PIS/Confins Opção 2
	 * 
	 * Gerar linhas Registro C605
	 * 
	 * @author Erivan Sousa
	 * @param anoMesReferencia
	 * @param municipio
	 * @param indicadorTipoGeracao
	 * @return StringBuilder com linhas do registro C605
	 * @throws ControladorException 
	 * 
	 * @date 30/01/2012
	 * */
	private StringBuilder registroC605(int anoMesReferencia, Municipio municipio, Short indicadorTipoGeracao) throws ControladorException{
		StringBuilder builder = new StringBuilder();
		
		Collection<Integer> colecaoLancamentoTipo = null;
		Collection<Integer> colecaoLancamentoItem = null;
		BigDecimal vlItemFaturamento = null;
		
		//Monta a primeira linha
		if(indicadorTipoGeracao.equals(Short.parseShort("1"))){
			colecaoLancamentoTipo = new ArrayList(Arrays.asList(new Integer[]{LancamentoTipo.AGUA,
			                                                                  LancamentoTipo.ESGOTO}));
			colecaoLancamentoItem = new ArrayList(Arrays.asList(new Integer[]{LancamentoItem.AGUA,
			                                                                  LancamentoItem.ESGOTO}));
		}else{
			colecaoLancamentoTipo = new ArrayList(Arrays.asList(new Integer[]{LancamentoTipo.RECEITA_LIQUIDA}));
			colecaoLancamentoItem = new ArrayList(Arrays.asList(new Integer[]{LancamentoItem.TOTAL}));
		}
		
		
		vlItemFaturamento = this.obterValorItemArquivoEFDPisConfins(colecaoLancamentoTipo, colecaoLancamentoItem, anoMesReferencia, municipio);
		
		//Primeira linha
		if(indicadorTipoGeracao.equals(Short.parseShort("1"))){
			/* - Erivan -
			 * [SB0003] - Gerar registro C605 do EFD-PIS/Confins Opção 1
			 */
			builder.append("|C605");//REG
			builder.append("|29");//COD_MOD
			if(municipio != null){
				builder.append("|" + municipio.getCodigoIbge().trim());//COD_MUM
			}else{
				builder.append("|");//COD_MUM
			}
			builder.append("|");//SER
			builder.append("|");//SUB
			builder.append("|01");//CST_COFINS
			builder.append("|"+ (String.valueOf(vlItemFaturamento.setScale(2)).replace(".",",")));//VL_ITEM
			builder.append("|"+ (String.valueOf(vlItemFaturamento.setScale(2)).replace(".",",")));//VL_BC_COFINS
			builder.append("|7,60");//ALIQ_COFINS
			
			BigDecimal valorCofins = vlItemFaturamento.multiply(new BigDecimal("0.076"));
			builder.append("|" + (String.valueOf(valorCofins.setScale(2, BigDecimal.ROUND_HALF_DOWN)).replace(".",",")));//VL_COFINS
			
			builder.append("||");//COD_CTA
			
		}else{
			/* - Erivan -
			 * [SB0006] - Gerar registro C605 do EFD-PIS/Confins Opção 2
			 */
			builder.append("|C605");//REG
			builder.append("|01");//CST_COFINS
			builder.append("|"+ (String.valueOf(vlItemFaturamento.setScale(2)).replace(".",",")));//VL_ITEM
			builder.append("|"+ (String.valueOf(vlItemFaturamento.setScale(2)).replace(".",",")));//VL_BC_COFINS
			builder.append("|7,60");//ALIQ_COFINS
			
			BigDecimal valorCofins = vlItemFaturamento.multiply(new BigDecimal("0.076"));
			builder.append("|" + (String.valueOf(valorCofins.setScale(2, BigDecimal.ROUND_HALF_DOWN)).replace(".",",")));//VL_COFINS
			
			builder.append("||");//COD_CTA
		}
		builder.append(System.getProperty("line.separator"));
		
		//Limpa objetos
		colecaoLancamentoTipo = null;
		colecaoLancamentoItem = null;
		vlItemFaturamento = null;
				
		//Monta a segunda linha;
		//A segunda linha só é gerada quando o indicador de tipo de geracao for igual ao tipo de geracao 1
		if(indicadorTipoGeracao.equals(Short.parseShort("1"))){
			colecaoLancamentoTipo = new ArrayList(Arrays.asList(new Integer[]{LancamentoTipo.FINANCIAMENTOS_COBRADOS, 
				                                                                  LancamentoTipo.PARCELAMENTOS_COBRADOS, 
				                                                                  LancamentoTipo.DOACOES_COBRADAS_EM_CONTA}));
		
			vlItemFaturamento = this.obterValorItemArquivoEFDPisConfins(colecaoLancamentoTipo, colecaoLancamentoItem, anoMesReferencia, municipio);
		
			//segunda linha
			/* - Erivan -
			 * [SB0003] - Gerar registro C605 do EFD-PIS/Confins Opção 1
			 */
			builder.append("|C605");//REG
			builder.append("|29");//COD_MOD
			if(municipio != null){
				builder.append("|" + municipio.getCodigoIbge().trim());//COD_MUM
			}else{
				builder.append("|");//COD_MUM
			}
			builder.append("|");//SER
			builder.append("|");//SUB
			builder.append("|07");//CST_COFINS
			builder.append("|"+String.valueOf(vlItemFaturamento.setScale(2, BigDecimal.ROUND_HALF_DOWN)).replace(".", ","));//VL_ITEM
			builder.append("|0,00");//VL_BC_COFINS
			builder.append("|7,60");//ALIQ_COFINS
			builder.append("|0,00");//VL_COFINS
			builder.append("||");//COD_CTA
			builder.append(System.getProperty("line.separator"));
		}
		return builder;
	}
	
	/**
	 * [UC1272]GerarArquivoEFD-PIS-Confins
	 * [SB0007] - Gerar registro F100 do EFD-PIS/Confins 
	 * 
	 * Gerar linhas Registro F100
	 * 
	 * @author Erivan Sousa
	 * @param anoMesReferencia
	 * @param municipio
	 * @return StringBuilder com linhas do registro F100
	 * @throws ControladorException 
	 * 
	 * @date 31/01/2012
	 * */
	private StringBuilder registroF100(int anoMesReferencia, Municipio municipio) throws ControladorException{
		StringBuilder builder = new StringBuilder();
		
		String ultimoDiaMes = Util.formatarDataSemBarraDDMMAAAA(Util.obterUltimaDataMes(Util.obterMes(anoMesReferencia), 
			Util.obterAno(anoMesReferencia)));
		
		Collection<Integer> colecaoLancamentoTipo = null;
		Collection<Integer> colecaoLancamentoItem = null;
		BigDecimal vlItemFaturamento = null;
		
		//Monta a primeira linha
		colecaoLancamentoTipo = new ArrayList(Arrays.asList(new Integer[]{3,4,5}));
		colecaoLancamentoItem = new ArrayList(Arrays.asList(new Integer[]{3}));
		
		
		vlItemFaturamento = this.obterValorItemArquivoEFDPisConfins(colecaoLancamentoTipo, colecaoLancamentoItem, anoMesReferencia, municipio);
		
		//Primeira linha (Financiamentos incluidos)
		builder.append("|F100");//REG
		builder.append("|01");//IND_OPER
		builder.append("|");//COD_PART
		builder.append("|");//COD_ITEM
		builder.append("|"+ultimoDiaMes);//DT_OPER
		builder.append("|" + String.valueOf(vlItemFaturamento.setScale(2, BigDecimal.ROUND_HALF_DOWN)).replace(".", ","));//VL_OPER
		builder.append("|01");//CST_PIS
		builder.append("|" + String.valueOf(vlItemFaturamento.setScale(2, BigDecimal.ROUND_HALF_DOWN)).replace(".", ","));//VL_BC_PIS
		builder.append("|1,65");//ALIQ_PIS
		builder.append("|" + String.valueOf(vlItemFaturamento.multiply(new BigDecimal(0.0165)).setScale(2, BigDecimal.ROUND_HALF_DOWN)).replace(".", ","));//VL_PIS
		builder.append("|01");//CST_COFINS
		builder.append("|" + String.valueOf(vlItemFaturamento.setScale(2, BigDecimal.ROUND_HALF_DOWN)).replace(".", ","));//CL_BC_COFINS
		builder.append("|7,60");//ALIQ_COFINS
		builder.append("|" + String.valueOf(vlItemFaturamento.multiply(new BigDecimal(0.0760)).setScale(2, BigDecimal.ROUND_HALF_DOWN)).replace(".", ","));//VL_COFINS
		builder.append("|");//NAT_BC_CRED
		builder.append("|");//IND_ORIG_CRED
		builder.append("|");//COD_CTA
		builder.append("|");//COD_CCUS
		builder.append("||");//DESC_DOC_OPER
		builder.append(System.getProperty("line.separator"));
		
		//Limpa objetos
		colecaoLancamentoTipo = null;
		colecaoLancamentoItem = null;
		vlItemFaturamento = null;
				
		//Monta a segunda linha;
		colecaoLancamentoTipo = new ArrayList(Arrays.asList(new Integer[]{15,16}));
		colecaoLancamentoItem = new ArrayList(Arrays.asList(new Integer[]{13}));
		
		vlItemFaturamento =this.obterValorItemArquivoEFDPisConfins(colecaoLancamentoTipo, colecaoLancamentoItem, anoMesReferencia, municipio);
		
		//segunda linha (Valor dos Juros)
		builder.append("|F100");//REG
		builder.append("|02");//IND_OPER
		builder.append("|");//COD_PART
		builder.append("|");//COD_ITEM
		builder.append("|"+ultimoDiaMes);//DT_OPER
		builder.append("|" + String.valueOf(vlItemFaturamento.setScale(2, BigDecimal.ROUND_HALF_DOWN)).replace(".", ","));//VL_OPER
		builder.append("|06");//CST_PIS
		builder.append("|" + String.valueOf(vlItemFaturamento.setScale(2, BigDecimal.ROUND_HALF_DOWN)).replace(".", ","));//VL_BC_PIS
		builder.append("|0,00");//ALIQ_PIS
		builder.append("|0,00");//VL_PIS
		builder.append("|06");//CST_COFINS
		builder.append("|" + String.valueOf(vlItemFaturamento.setScale(2, BigDecimal.ROUND_HALF_DOWN)).replace(".", ","));//CL_BC_COFINS
		builder.append("|0,00");//ALIQ_COFINS
		builder.append("|0,00");//VL_COFINS
		builder.append("|");//NAT_BC_CRED
		builder.append("|");//IND_ORIG_CRED
		builder.append("|");//COD_CTA
		builder.append("|");//COD_CCUS
		builder.append("||");//DESC_DOC_OPER
		builder.append(System.getProperty("line.separator"));
		
		//Limpa objetos
		colecaoLancamentoTipo = null;
		colecaoLancamentoItem = null;
		vlItemFaturamento = null;
						
		//Monta a terceira linha;
		colecaoLancamentoTipo = new ArrayList(Arrays.asList(new Integer[]{11}));
		colecaoLancamentoItem = new ArrayList(Arrays.asList(new Integer[]{1,2,3,49}));
				
		vlItemFaturamento = this.obterValorItemArquivoEFDPisConfins(colecaoLancamentoTipo, colecaoLancamentoItem, anoMesReferencia, municipio);
				
		//terceira linha (Valor dos Juros)
		builder.append("|F100");//REG
		builder.append("|01");//IND_OPER
		builder.append("|");//COD_PART
		builder.append("|");//COD_ITEM
		builder.append("|"+ultimoDiaMes);//DT_OPER
		builder.append("|" + String.valueOf(vlItemFaturamento.setScale(2, BigDecimal.ROUND_HALF_DOWN)).replace(".", ","));//VL_OPER
		builder.append("|01");//CST_PIS
		builder.append("|" + String.valueOf(vlItemFaturamento.setScale(2, BigDecimal.ROUND_HALF_DOWN)).replace(".", ","));//VL_BC_PIS
		builder.append("|1,65");//ALIQ_PIS
		builder.append("|" + String.valueOf(vlItemFaturamento.multiply(new BigDecimal(0.0165)).setScale(2, BigDecimal.ROUND_HALF_DOWN)).replace(".", ","));//VL_PIS
		builder.append("|01");//CST_COFINS
		builder.append("|" + String.valueOf(vlItemFaturamento.setScale(2, BigDecimal.ROUND_HALF_DOWN)).replace(".", ","));//CL_BC_COFINS
		builder.append("|7,60");//ALIQ_COFINS
		builder.append("|" + String.valueOf(vlItemFaturamento.multiply(new BigDecimal(0.0760)).setScale(2, BigDecimal.ROUND_HALF_DOWN)).replace(".", ","));//VL_COFINS
		builder.append("|");//NAT_BC_CRED
		builder.append("|");//IND_ORIG_CRED
		builder.append("|");//COD_CTA
		builder.append("|");//COD_CCUS
		builder.append("||");//DESC_DOC_OPER
		builder.append(System.getProperty("line.separator"));
		
		//Limpa objetos
		colecaoLancamentoTipo = null;
		colecaoLancamentoItem = null;
		vlItemFaturamento = null;
						
		//Monta a quarta linha;
		colecaoLancamentoTipo = new ArrayList(Arrays.asList(new Integer[]{7,8,18,19}));
		colecaoLancamentoItem = new ArrayList(Arrays.asList(new Integer[]{1,2,3}));
					
		vlItemFaturamento = this.obterValorItemArquivoEFDPisConfins(colecaoLancamentoTipo, colecaoLancamentoItem, anoMesReferencia, municipio);
						
		//quarta linha (Financiamentos cancelados)
		builder.append("|F100");//REG
		builder.append("|00");//IND_OPER
		builder.append("|");//COD_PART
		builder.append("|");//COD_ITEM
		builder.append("|"+ultimoDiaMes);//DT_OPER
		builder.append("|" + String.valueOf(vlItemFaturamento.setScale(2, BigDecimal.ROUND_HALF_DOWN)).replace(".", ","));//VL_OPER
		builder.append("|50");//CST_PIS
		builder.append("|" + String.valueOf(vlItemFaturamento.setScale(2, BigDecimal.ROUND_HALF_DOWN)).replace(".", ","));//VL_BC_PIS
		builder.append("|1,65");//ALIQ_PIS
		builder.append("|"+ String.valueOf(vlItemFaturamento.multiply(new BigDecimal(0.0165)).setScale(2, BigDecimal.ROUND_HALF_DOWN)).replace(".", ","));//VL_PIS
		builder.append("|50");//CST_COFINS
		builder.append("|" + String.valueOf(vlItemFaturamento.setScale(2, BigDecimal.ROUND_HALF_DOWN)).replace(".", ","));//CL_BC_COFINS
		builder.append("|7,60");//ALIQ_COFINS
		builder.append("|" + String.valueOf(vlItemFaturamento.multiply(new BigDecimal(0.0760)).setScale(2, BigDecimal.ROUND_HALF_DOWN)).replace(".", ","));//VL_COFINS
		builder.append("|");//NAT_BC_CRED
		builder.append("|");//IND_ORIG_CRED
		builder.append("|");//COD_CTA
		builder.append("|");//COD_CCUS
		builder.append("||");//DESC_DOC_OPER
		builder.append(System.getProperty("line.separator"));
		
		//Limpa objetos
		colecaoLancamentoTipo = null;
		colecaoLancamentoItem = null;
		vlItemFaturamento = null;
						
		//Monta a quinta linha;
		colecaoLancamentoTipo = new ArrayList(Arrays.asList(new Integer[]{LancamentoTipo.CANCELAMENTOS_POR_REFATURAMENTO,
		                                                                  LancamentoTipo.PARCELAMENTOS_COBRADOS_SUP_CANCELAMENTOS_POR_REFATURAMENTO,
		                                                                  LancamentoTipo.CREDITOS_REALIZADOS_SUP_CANCELAMENTO_POR_REFATURAMENTO}));
		colecaoLancamentoItem = new ArrayList(Arrays.asList(new Integer[]{LancamentoItem.AGUA,
		                                                                  LancamentoItem.ESGOTO,
		                                                                  LancamentoItem.GRUPO_CONTABIL,
		                                                                  LancamentoItem.CREDITOS_PARA_COBRANCA_INDEVIDA_INCLUIDOS}));
				
		vlItemFaturamento = this.obterValorItemArquivoEFDPisConfins(colecaoLancamentoTipo, colecaoLancamentoItem, anoMesReferencia, municipio);
				
		//quinta linha (Cancelamentos por refaturamento)
		builder.append("|F100");//REG
		builder.append("|00");//IND_OPER
		builder.append("|");//COD_PART
		builder.append("|");//COD_ITEM
		builder.append("|"+ultimoDiaMes);//DT_OPER
		builder.append("|" + String.valueOf(vlItemFaturamento.setScale(2, BigDecimal.ROUND_HALF_DOWN)).replace(".", ","));//VL_OPER
		builder.append("|50");//CST_PIS
		builder.append("|" + String.valueOf(vlItemFaturamento.setScale(2, BigDecimal.ROUND_HALF_DOWN)).replace(".", ","));//VL_BC_PIS
		builder.append("|1,65");//ALIQ_PIS
		builder.append("|"+ String.valueOf(vlItemFaturamento.multiply(new BigDecimal(0.0165)).setScale(2, BigDecimal.ROUND_HALF_DOWN)).replace(".", ","));//VL_PIS
		builder.append("|50");//CST_COFINS
		builder.append("|" + String.valueOf(vlItemFaturamento.setScale(2, BigDecimal.ROUND_HALF_DOWN)).replace(".", ","));//CL_BC_COFINS
		builder.append("|7,60");//ALIQ_COFINS
		builder.append("|" + String.valueOf(vlItemFaturamento.multiply(new BigDecimal(0.0760)).setScale(2, BigDecimal.ROUND_HALF_DOWN)).replace(".", ","));//VL_COFINS
		builder.append("|");//NAT_BC_CRED
		builder.append("|");//IND_ORIG_CRED
		builder.append("|");//COD_CTA
		builder.append("|");//COD_CCUS
		builder.append("||");//DESC_DOC_OPER
		builder.append(System.getProperty("line.separator"));
		
		//Limpa objetos
		colecaoLancamentoTipo = null;
		colecaoLancamentoItem = null;
		vlItemFaturamento = null;
						
		//Monta a sexta linha;
		colecaoLancamentoTipo = new ArrayList(Arrays.asList(new Integer[]{LancamentoTipo.CREDITOS_A_REALIZAR_POR_COBRANCA_INDEVIDA_INCLUIDOS,
		                                                                  LancamentoTipo.DESCONTOS_INCONDICIONAIS_INCLUIDOS,
		                                                                  LancamentoTipo.GUIAS_DEVOLUCAO_VALORES_COBRADOS_INDEVIDAMENTE_INCLUIDOS}));
				
		vlItemFaturamento = this.obterValorItemArquivoEFDPisConfins(colecaoLancamentoTipo, colecaoLancamentoItem, anoMesReferencia, municipio);
			
		//sexta linha (Creditos/Guias de devolucao)
		builder.append("|F100");//REG
		builder.append("|00");//IND_OPER
		builder.append("|");//COD_PART
		builder.append("|");//COD_ITEM
		builder.append("|"+ultimoDiaMes);//DT_OPER
		builder.append("|" + String.valueOf(vlItemFaturamento.setScale(2, BigDecimal.ROUND_HALF_DOWN)).replace(".", ","));//VL_OPER
		builder.append("|50");//CST_PIS
		builder.append("|" + String.valueOf(vlItemFaturamento.setScale(2, BigDecimal.ROUND_HALF_DOWN)).replace(".", ","));//VL_BC_PIS
		builder.append("|1,65");//ALIQ_PIS
		builder.append("|"+ String.valueOf(vlItemFaturamento.multiply(new BigDecimal(0.0165)).setScale(2, BigDecimal.ROUND_HALF_DOWN)).replace(".", ","));//VL_PIS
		builder.append("|50");//CST_COFINS
		builder.append("|" + String.valueOf(vlItemFaturamento.setScale(2, BigDecimal.ROUND_HALF_DOWN)).replace(".", ","));//CL_BC_COFINS
		builder.append("|7,60");//ALIQ_COFINS
		builder.append("|" + String.valueOf(vlItemFaturamento.multiply(new BigDecimal(0.0760)).setScale(2, BigDecimal.ROUND_HALF_DOWN)).replace(".", ","));//VL_COFINS
		builder.append("|");//NAT_BC_CRED
		builder.append("|");//IND_ORIG_CRED
		builder.append("|");//COD_CTA
		builder.append("|");//COD_CCUS
		builder.append("||");//DESC_DOC_OPER
		builder.append(System.getProperty("line.separator"));
		
		return builder;
	} 
	
	/**
	 * [UC1272]GerarArquivoEFD-PIS-Confins
	 * [SB0008] - Gerar registro F600 do EFD-PIS/Confins 
	 * 
	 * Gerar linhas Registro F600
	 * 
	 * @author Erivan Sousa
	 * @param anoMesReferencia
	 * @param municipio
	 * @return StringBuilder com linhas do registro F600
	 * @throws ControladorException 
	 * 
	 * @date 31/01/2012
	 * */
	private StringBuilder registroF600(int anoMesReferencia, Municipio municipio) throws ControladorException{
		StringBuilder builder = new StringBuilder();
		
		String ultimoDiaMes = Util.formatarDataSemBarraDDMMAAAA(Util.obterUltimaDataMes(Util.obterMes(anoMesReferencia), 
			Util.obterAno(anoMesReferencia)));
		
		Collection colecaoRegistros;
		try {
			colecaoRegistros = repositorioFinanceiro.obterDadosArquivoEFDPisConfinsF600(anoMesReferencia, municipio);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
		
		Iterator iteratorRegistros = colecaoRegistros.iterator();
		
		while(iteratorRegistros.hasNext()){
			Object[] registro = (Object[])iteratorRegistros.next();
			
			//Linha Registro F-600
			builder.append("|F600");//REG
			builder.append("|01");//IND_NAT_RET
			builder.append("|"+ultimoDiaMes);//DT_RET
			builder.append("|" + String.valueOf(registro[1]).replace(".", ","));//VL_BC_RET
			builder.append("|" + String.valueOf(registro[2]).replace(".", ","));//VL_RET
			builder.append("|");//COD_REC
			builder.append("|");//IND_NAT_REC
			builder.append("|" + (String)registro[0]);//CNPJ
			builder.append("|" + String.valueOf(registro[3]).replace(".", ","));//VL_RET_PIS
			builder.append("|" + String.valueOf(registro[4]).replace(".", ","));//VL_RET_COFINS
			builder.append("|0|");//IND_DEC			
			builder.append(System.getProperty("line.separator"));
			
		}
		
		return builder;
	}
	

	/**
	 * [UC1272]GerarArquivoEFD-PIS-Confins
	 * [SB0009] - Gerar registro M230 do EFD-PIS/Confins 
	 * 
	 * Gerar linhas Registro M230
	 * 
	 * @author Erivan Sousa
	 * @param anoMesReferencia
	 * @param municipio
	 * @return StringBuilder com linhas do registro M230
	 * @throws ControladorException 
	 * 
	 * @date 06/03/2012
	 * */
	private StringBuilder registroM230(int anoMesReferencia, Municipio municipio) throws ControladorException{
		StringBuilder builder = new StringBuilder();
		
		Collection colecaoRegistros;
		try {
			colecaoRegistros = repositorioFinanceiro.obterDadosArquivoEFDPisConfinsM230_M630(anoMesReferencia, municipio);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
		
		Iterator iteratorRegistros = colecaoRegistros.iterator();
		
		while(iteratorRegistros.hasNext()){
			Object[] registro = (Object[])iteratorRegistros.next();
			
			BigDecimal vlNaoReceb = null;
			if((new BigDecimal(String.valueOf(registro[1])).subtract(new BigDecimal(String.valueOf(registro[2])))).doubleValue() > 0){
				vlNaoReceb = new BigDecimal(String.valueOf(registro[1])).subtract(new BigDecimal(String.valueOf(registro[2])));
			}else{
				vlNaoReceb = new BigDecimal(String.valueOf(registro[1]));
			}
			
			//Linha registro M230
			builder.append("|M230");//REG
			builder.append("|" + String.valueOf(registro[0]).trim());//CNPJ
			builder.append("|" + String.valueOf(new BigDecimal(String.valueOf(registro[1])).setScale(2, BigDecimal.ROUND_HALF_DOWN)).replace(".", ","));//VL_VEND
			builder.append("|" + String.valueOf(vlNaoReceb.setScale(2, BigDecimal.ROUND_HALF_DOWN)).replace(".", ","));//VL_NAO_RECEB
			builder.append("|" + String.valueOf(vlNaoReceb.multiply(new BigDecimal(0.0165)).setScale(2, BigDecimal.ROUND_HALF_DOWN)).replace(".", ","));//VL_CONT_DIF
			builder.append("|");//VL_CRED_DIF
			builder.append("|");//COD_CRED
			builder.append(System.getProperty("line.separator"));
			
		}
		
		return builder;
	}
	
	/**
	 * [UC1272]GerarArquivoEFD-PIS-Confins
	 * [SB0010] - Gerar registro M630 do EFD-PIS/Confins 
	 * 
	 * Gerar linhas Registro M630
	 * 
	 * @author Erivan Sousa
	 * @param anoMesReferencia
	 * @param municipio
	 * @return StringBuilder com linhas do registro M630
	 * @throws ControladorException 
	 * 
	 * @date 06/03/2012
	 * */
	private StringBuilder registroM630(int anoMesReferencia, Municipio municipio) throws ControladorException{
		StringBuilder builder = new StringBuilder();
		
		Collection colecaoRegistros;
		try {
			colecaoRegistros = repositorioFinanceiro.obterDadosArquivoEFDPisConfinsM230_M630(anoMesReferencia, municipio);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
		
		Iterator iteratorRegistros = colecaoRegistros.iterator();
		
		while(iteratorRegistros.hasNext()){
			Object[] registro = (Object[])iteratorRegistros.next();
			
			BigDecimal vlNaoReceb = null;
			if((new BigDecimal(String.valueOf(registro[1])).subtract(new BigDecimal(String.valueOf(registro[2])))).doubleValue() > 0){
				vlNaoReceb = new BigDecimal(String.valueOf(registro[1])).subtract(new BigDecimal(String.valueOf(registro[2])));
			}else{
				vlNaoReceb = new BigDecimal(String.valueOf(registro[1]));
			}
			
			//Linha registro M630
			builder.append("|M630");//REG
			builder.append("|" + String.valueOf(registro[0]).trim());//CNPJ
			builder.append("|" + String.valueOf(new BigDecimal(String.valueOf(registro[1])).setScale(2, BigDecimal.ROUND_HALF_DOWN)).replace(".", ","));//VL_VEND
			builder.append("|" + String.valueOf(vlNaoReceb.setScale(2, BigDecimal.ROUND_HALF_DOWN)).replace(".", ","));//VL_NAO_RECEB
			builder.append("|" + String.valueOf(vlNaoReceb.multiply(new BigDecimal(0.0760)).setScale(2, BigDecimal.ROUND_HALF_DOWN)).replace(".", ","));//VL_CONT_DIF
			builder.append("|");//VL_CRED_DIF
			builder.append("|");//COD_CRED
			builder.append(System.getProperty("line.separator"));
			
		}
		
		return builder;
	}

	
	/**
	 * 
	 * @author Vivianne Sousa
	 * @date 12/04/2013
	 * 
	 * @throws ErroRepositorioException
	 */
	public void gerarContasAReceberContabilAnaliticoTabelaAuxiliar(
			int idFuncionalidadeIniciada)	throws ControladorException {
		
		int idUnidadeIniciada = 0;

		/*
		 * Registrar o inicio do processamento da Unidade de
		 * Processamento do Batch
		*/		
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(
						idFuncionalidadeIniciada,
						UnidadeProcessamento.FUNCIONALIDADE,
						0);
		
		try {
			
			repositorioFinanceiro.gerarContasAReceberContabilAnaliticoTabelaAuxiliar();
							
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,	idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}
	
	/**
	 * 
	 * @author Vivianne Sousa
	 * @date 12/04/2013
	 * 
	 * @throws ErroRepositorioException
	 */
	public void gerarContasAReceberContabilAnalitico(
			int idFuncionalidadeIniciada)	throws ControladorException {
		
		int idUnidadeIniciada = 0;

		/*
		 * Registrar o inicio do processamento da Unidade de
		 * Processamento do Batch
		*/		
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(
						idFuncionalidadeIniciada,
						UnidadeProcessamento.FUNCIONALIDADE,
						0);
		
		try {
			
			repositorioFinanceiro.gerarContasAReceberContabilAnalitico();
							
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,	idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}
	
	
	/**
	 * 
	 * @author Vivianne Sousa
	 * @date 12/04/2013
	 * 
	 * @throws ErroRepositorioException
	 */
	public void gerarContasAReceberContabilSintetico(
			int idFuncionalidadeIniciada)	throws ControladorException {
		
		int idUnidadeIniciada = 0;

		/*
		 * Registrar o inicio do processamento da Unidade de
		 * Processamento do Batch
		*/		
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(
						idFuncionalidadeIniciada,
						UnidadeProcessamento.FUNCIONALIDADE,
						0);
		
		try {
			
			repositorioFinanceiro.gerarContasAReceberContabilSintetico();
							
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,	idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}
	
	/**
	 * @author Ricardo Germinio
	 * @date 10/12/2012
	 * 
	 * @throws ErroRepositorioException
	 */
	public void inserirParametrosPerdasFiscais(ParametrosPerdasFiscaisHelper helper) throws ControladorException{
		
			
		ParametrosDevedoresDuvidosos parametrosDevedoresDuvidosos = new ParametrosDevedoresDuvidosos();
		parametrosDevedoresDuvidosos.setAnoMesReferenciaContabil(helper.getAnoMesReferenciaContabil());
		parametrosDevedoresDuvidosos.setValorABaixar(helper.getValorABaixar());
		parametrosDevedoresDuvidosos.setValorBaixado(new BigDecimal (0));
		parametrosDevedoresDuvidosos.setUltimaAlteracao(new Date());
		Integer idParametrosDevedoresDuvidosos = (Integer)getControladorUtil().inserir(parametrosDevedoresDuvidosos);
		parametrosDevedoresDuvidosos.setId(idParametrosDevedoresDuvidosos);
		Collection<ParametrosPerdasFiscaisItensHelper> collParametrosPerdasFiscaisItensHelper = helper.getCollParametrosPerdasFiscaisItensHelper();
		if(collParametrosPerdasFiscaisItensHelper != null && !collParametrosPerdasFiscaisItensHelper.isEmpty()){
			for(ParametrosPerdasFiscaisItensHelper helperItens: collParametrosPerdasFiscaisItensHelper){
				ParametrosDevedoresDuvidososItem parametrosDevedoresDuvidososItens = new ParametrosDevedoresDuvidososItem();
				
				parametrosDevedoresDuvidososItens.setValorLimite(helperItens.getValorLimite());
				parametrosDevedoresDuvidososItens.setNumeroMeses(helperItens.getNumeroMeses());
				
				parametrosDevedoresDuvidosos.setId(idParametrosDevedoresDuvidosos);
				parametrosDevedoresDuvidososItens.setParametrosDevedoresDuvidosos(parametrosDevedoresDuvidosos);
				
				
				CobrancaSituacao cobrancaSituacao = new CobrancaSituacao();
				if(helperItens.getSituacaoCobranca() != null && !helperItens.getSituacaoCobranca().equals("")){
					cobrancaSituacao.setId((Integer) helperItens.getSituacaoCobranca());
					parametrosDevedoresDuvidososItens.setCobrancaSituacao(cobrancaSituacao);
				} 					
			
			
				
				parametrosDevedoresDuvidososItens.setUltimaAlteracao(new Date());
				Integer idParametrosDevedoresDuvidososItens = (Integer) getControladorUtil().inserir(parametrosDevedoresDuvidososItens);
				parametrosDevedoresDuvidososItens.setId(idParametrosDevedoresDuvidososItens);
			}
		}
	}
	
	/**
	 * @author Ricardo Germinio
	 * @date 10/12/2012
	 * 
	 * @throws ErroRepositorioException
	 */
	public Boolean pesquisarAnoMesReferencia(Integer anoMesReferenciaContabil)
			throws ControladorException{
		try{
			return repositorioFinanceiro.pesquisarAnoMesReferencia(anoMesReferenciaContabil);
		}catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	
}

	/**
	 * @author Ricardo Germinio
	 * @date 10/12/2012
	 * 
	 * @throws ErroRepositorioException
	 */
	public void inserirParametrosPerdasFiscaisItem(ParametrosPerdasFiscaisHelper helper) throws ControladorException{
		
			
		Collection<ParametrosPerdasFiscaisItensHelper> collParametrosPerdasFiscaisItensHelper = helper.getCollParametrosPerdasFiscaisItensHelper();
		
		if(collParametrosPerdasFiscaisItensHelper != null && !collParametrosPerdasFiscaisItensHelper.isEmpty()){
			for(ParametrosPerdasFiscaisItensHelper helperItens: collParametrosPerdasFiscaisItensHelper){
				ParametrosDevedoresDuvidososItem parametrosDevedoresDuvidososItens = new ParametrosDevedoresDuvidososItem();
				
				if(helperItens.getValorLimite() != null && !helperItens.getValorLimite().equals("")){
					parametrosDevedoresDuvidososItens.setValorLimite(helperItens.getValorLimite());
				} else {
					throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"o Valor limite");
				}
				if(helperItens.getNumeroMeses() != null && !helperItens.getNumeroMeses().equals("")){
					parametrosDevedoresDuvidososItens.setNumeroMeses(helperItens.getNumeroMeses());
				} else {
					throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"o Numero de Meses");
				}
				ParametrosDevedoresDuvidosos parametrosDevedoresDuvidosos = new ParametrosDevedoresDuvidosos();
				parametrosDevedoresDuvidosos.setId(helper.getIdParametrosPerdasFiscais());
				if(helper.getValorABaixar() != null && !helper.getValorABaixar().equals("")){
					parametrosDevedoresDuvidosos.setValorABaixar(helper.getValorABaixar());
				} else {
					throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"o Valor a Baixar");
				}
				parametrosDevedoresDuvidososItens.setParametrosDevedoresDuvidosos(parametrosDevedoresDuvidosos);
				
				
				CobrancaSituacao cobrancaSituacao = new CobrancaSituacao();
				if(helperItens.getSituacaoCobranca() != null && !helperItens.getSituacaoCobranca().equals("")){
					cobrancaSituacao.setId((Integer) helperItens.getSituacaoCobranca());
					parametrosDevedoresDuvidososItens.setCobrancaSituacao(cobrancaSituacao);
				} 				
			
			
				
				parametrosDevedoresDuvidososItens.setUltimaAlteracao(new Date());
				Integer idParametrosDevedoresDuvidososItens = (Integer) getControladorUtil().inserir(parametrosDevedoresDuvidososItens);
				parametrosDevedoresDuvidososItens.setId(idParametrosDevedoresDuvidososItens);
			}
		}
	}
	
	/**
	 * @author Ricardo Germinio
	 * @date 10/12/2012
	 * 
	 * @throws ErroRepositorioException
	 */
	public void removeParametrosPerdasFiscaisItens(int idParametrosPerdasFiscais)
			throws ControladorException{
		
		try{
			this.repositorioFinanceiro.removeParametrosPerdasFiscaisItens(idParametrosPerdasFiscais);
	   	} catch (ErroRepositorioException ex) {
			//levanta a exceção para a próxima camada
			throw new ControladorException("erro.sistema", ex);
		}			
	}
	
	/**
	 * @author Ricardo Germinio
	 * @date 10/12/2012
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarValorABaixarParametrosDevedoresDuvidosos(ParametrosPerdasFiscaisHelper parametrosPerdasFiscaisHelper)
			throws ControladorException{
		
		try {
			repositorioFinanceiro.atualizarValorABaixarParametrosDevedoresDuvidosos(parametrosPerdasFiscaisHelper.getIdParametrosPerdasFiscais(), parametrosPerdasFiscaisHelper.getValorABaixar());
			
			if(parametrosPerdasFiscaisHelper.getCollParametrosPerdasFiscaisItensHelper() != null && !parametrosPerdasFiscaisHelper.getCollParametrosPerdasFiscaisItensHelper().isEmpty()){
    				removeParametrosPerdasFiscaisItens(parametrosPerdasFiscaisHelper.getIdParametrosPerdasFiscais());
    				inserirParametrosPerdasFiscaisItem(parametrosPerdasFiscaisHelper);
    		} else{
    			throw new ControladorException("atencao.nao_existem_itens");
    		}
			
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * @author Ricardo Germinio
	 * @date 10/12/2012
	 * 
	 * @throws ErroRepositorioException
	 */
	public ParametrosPerdasFiscaisHelper pesquisarParametrosPerdasFiscais(ParametrosPerdasFiscaisHelper parametrosPerdasFiscaisHelper)
			throws ControladorException {
		try{
			return repositorioFinanceiro.pesquisarParametrosPerdasFiscais(parametrosPerdasFiscaisHelper);
		}catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}
	
	/**
	 * Este caso de uso gera a integração para a contabilidade
	 *
	 * [UC0469] Gerar Integração para a Contabilidade
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
		
		String dtInicioIntervalo = format.format(
			Util.gerarDataInicialApartirAnoMesRefencia(
				Integer.parseInt(anoMesInicial)));
		
		String dtFimIntervalo = format.format(
			Util.gerarDataApartirAnoMesRefencia(
				Integer.parseInt(anoMesFinal)));

		// Validamos o se o sequencial será alterado
		if ( !Util.validarStringNumerica( numeroUltimoSequencial ) ){
			throw new ControladorException( "atencao.campo_texto.numero_obrigatorio", null, "Último Sequencial" );
		}
		
		try {			
			colecaoDadosGerarIntegracao = repositorioFinanceiro.pesquisarGerarIntegracaoContabilidadeJUAZEIRO(
			idLancamentoOrigem, dtInicioIntervalo, dtFimIntervalo);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
		/** definição das variáveis */
		StringBuilder gerarIntegracaoTxt = new StringBuilder();
		
		/*
		 * Caso a coleção dos dados não esteja vazia
		 */
		if(colecaoDadosGerarIntegracao != null && !colecaoDadosGerarIntegracao.isEmpty()){
			
			/** definição das variáveis */
			int sequencialLancamento = Integer.parseInt( numeroUltimoSequencial );
			++sequencialLancamento;
			
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
			 * Conteúdo deste campo 
			 */
			tipoRegistro = "2";
			
			SimpleDateFormat formater = new SimpleDateFormat("ddMMyyyy");
			
			boolean icValorNegativo;
			/*
			 * Laço para gerar o txt 
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
					
					//Caso o valor do lançãmento seja negativo, torna o valor positivo e muda o tipo de lançamento
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
				
				//2 - SEQUENCIAL DA ARRECADAÇÃO
				gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumero(6, Integer.toString(sequencialLancamento)));
				
				//3 - DATA DO PAGAMENTO
				gerarIntegracaoTxt.append(dataPagamento);
				
				//4 - VALOR DO PAGAMENTO
				gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumero(15, valorLancamento.toString()));
				
				//5 - Caso o valor do pagamento seja positivo CÓDIGO DA FICHA RECEITA, caso contrário CODIGO AGENTE/BANCO.
				if(!icValorNegativo){
					gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumero(12, codigoFichaReceita));
				}else{
					gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumero(12, codigoAgente));
				}
				
				//6 - CODIGO DO EVENTO CONTABIL
				gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumero(12, codigoEventoContabil));
				
				//7 - Caso o valor do pagamento seja positivo CODIGO AGENTE/BANCO, caso contrário CÓDIGO DA FICHA RECEITA.
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
			 * Caso o arquivo txt não esteja vazio 
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
			
		}else{			
			//Caso não exista informação para os dados informados
			
			if(idLancamentoOrigem.equals(LancamentoOrigem.FATURAMENTO + "")){
				throw new ControladorException("atencao.pesquisa.nenhum_registro_tabela", null,"Resumo Faturamento");
			}
			else if(idLancamentoOrigem.equals(LancamentoOrigem.ARRECADACAO + "")){
				throw new ControladorException("atencao.pesquisa.nenhum_registro_tabela", null,"Resumo Arrecadação");
			}
		}
	}
	
}
