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
package gcom.faturamento.conta;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.ControladorImovelLocalHome;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.faturamento.ControladorFaturamentoLocal;
import gcom.faturamento.ControladorFaturamentoLocalHome;
import gcom.faturamento.IRepositorioFaturamento;
import gcom.faturamento.bean.CalcularValoresAguaEsgotoHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.SessionContext;

/**
 * Esta classe tem como finalidade encapsular o caso de uso [UC0146] - Manter Conta, gerando
 * maior facilidade na manuten��o do mesmo.  
 *
 * @author Raphael Rossiter
 * @date 04/11/2008
 */
public class UC0146ManterConta {

private static UC0146ManterConta instancia;
	
	@SuppressWarnings("unused")
	private IRepositorioFaturamento repositorioFaturamento;
	
	@SuppressWarnings("unused")
	private SessionContext sessionContext;

	
	private UC0146ManterConta(IRepositorioFaturamento repositorioFaturamento, 
			SessionContext sessionContext) {

		this.repositorioFaturamento = repositorioFaturamento;
		this.sessionContext = sessionContext;
	}

	public static UC0146ManterConta getInstancia(IRepositorioFaturamento repositorioFaturamento,
			SessionContext sessionContext) {
		
		if (instancia == null) {
			instancia = new UC0146ManterConta(repositorioFaturamento, sessionContext);
		}
		return instancia;
	}
	

	/**
	 * Controlador Imovel 
	 *
	 * @author Raphael Rossiter
	 * @date 30/04/2008
	 *
	 * @return ControladorImovelLocal
	 */
	protected ControladorImovelLocal getControladorImovel() {
		ControladorImovelLocalHome localHome = null;
		ControladorImovelLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorImovelLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_IMOVEL_SEJB);
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
	
	/**
	 * Controlador Faturamento 
	 *
	 * @author Raphael Rossiter
	 * @date 30/04/2008
	 *
	 * @return ControladorFaturamentoLocal
	 */
	private ControladorFaturamentoLocal getControladorFaturamento() {
		ControladorFaturamentoLocalHome localHome = null;
		ControladorFaturamentoLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorFaturamentoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FATURAMENTO_SEJB);
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
	
	/**
	 * [UC0146] - Manter Conta
	 * 
	 * Retificar Conjunto de Conta
	 *
	 * @author Raphael Rossiter
	 * @date 04/11/2008
	 *
	 * @param colecaoContas
	 * @param identificadores
	 * @param sistemaParametro
	 * @param usuarioLogado
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection gerarColecaoContaSelecaoParaRetificacao(Collection<Conta> colecaoContas, String identificadores, 
			SistemaParametro sistemaParametro, Usuario usuarioLogado) throws ControladorException {
		
		Collection<Conta> colecaoContasSelecionadas = new ArrayList();
		
		Conta contaColecao;
		
		//Verifica se houve sele��o de contas
		String[] arrayIdentificadores = null;
		
		if (identificadores != null) {
			arrayIdentificadores = identificadores.split(",");
		}
		
		Iterator colecaoContasIt = colecaoContas.iterator();
		
		while (colecaoContasIt.hasNext()) {
			
			contaColecao = (Conta) colecaoContasIt.next();

			if (identificadores != null) {
				
				for (int index = 0; index < arrayIdentificadores.length; index++) {
					
					String dadosConta = arrayIdentificadores[index];
					String[] idUltimaAlteracao = dadosConta.split("-");

					// Contas que sofrer�o altera��o na sua data de vencimento
					if (contaColecao.getId().equals(new Integer(idUltimaAlteracao[0]))) {
						
						if (sistemaParametro.getIndicadorLimiteAlteracaoVencimento().equals(ConstantesSistema.SIM)) {
							//[FS0023] - Verificar Limite de Altera��o de vencimento.	
							this.getControladorFaturamento().verificarQuantidadeAlteracoesVencimentoConta(contaColecao.getId());
						}
						
						colecaoContasSelecionadas.add(contaColecao);
					}
				}
				
			} 
			else {
				
				colecaoContasSelecionadas.add(contaColecao);
			}
		}
		
		return colecaoContasSelecionadas;
	}
	
	/**
	 * Retificar Conjunto de Conta
	 * 
	 * @author Raphael Rossiter
	 * @date 21/08/2007
	 * 
	 * @throws ControladorException
	 */
	public void retificarConjuntoConta(Collection<Conta> colecaoContas, 
		LigacaoAguaSituacao ligacaoAguaSituacao, Integer consumoAgua,
		LigacaoEsgotoSituacao ligacaoEsgotoSituacao, Integer consumoEsgoto, Date dataVencimento,
		ContaMotivoRetificacao contaMotivoRetificacao, Short indicadorCategoriaEconomiaConta, SistemaParametro sistemaParametro, Usuario usuarioLogado,
		boolean substituirClienteConta) throws ControladorException {

		boolean dataVencimentoNula = false;
		if (colecaoContas != null && !colecaoContas.isEmpty()) {

			Iterator colecaoContasIterator = colecaoContas.iterator();

			while (colecaoContasIterator.hasNext()) {

				Conta conta = (Conta) colecaoContasIterator.next();
				
				Collection colecaoCategoriaOUSubcategoria = null;
				
				if (sistemaParametro.getIndicadorTarifaCategoria().equals(SistemaParametro.INDICADOR_TARIFA_SUBCATEGORIA)){
	        		
					if (indicadorCategoriaEconomiaConta.equals(ConstantesSistema.SIM)) {
						// [UC0108] - Quantidade de economias por categoria
						colecaoCategoriaOUSubcategoria = this.getControladorImovel()
							.obterQuantidadeEconomiasContaCategoriaPorSubcategoria(conta);
					} else {
						colecaoCategoriaOUSubcategoria = this.getControladorImovel().obterQuantidadeEconomiasSubCategoria(conta.getImovel().getId());
					}
	        	}
	        	else{
	        		
	        		if (indicadorCategoriaEconomiaConta.equals(ConstantesSistema.SIM)) {
	        			// [UC0108] - Quantidade de economias por categoria
	        			colecaoCategoriaOUSubcategoria = this.getControladorImovel().obterQuantidadeEconomiasContaCategoria(conta);
	        		} else {
	        			colecaoCategoriaOUSubcategoria = this.getControladorImovel().obterQuantidadeEconomiasCategoria(conta.getImovel());
	        		}
	        	}
				

				Collection colecaoCreditoRealizado = this.getControladorFaturamento().obterCreditosRealizadosConta(conta);

				Collection colecaoDebitoCobrado = this.getControladorFaturamento().obterDebitosCobradosConta(conta);

				//[UC0120] - Calcular Valores de �gua e/ou Esgoto
				Collection<CalcularValoresAguaEsgotoHelper> valoresConta = 
				this.getControladorFaturamento().calcularValoresConta(
						
				Util.formatarAnoMesParaMesAno(conta.getReferencia()), 
						conta.getImovel().getId().toString(), ligacaoAguaSituacao.getId(), 
						ligacaoEsgotoSituacao.getId(), colecaoCategoriaOUSubcategoria, consumoAgua.toString(), 
						consumoEsgoto.toString(), conta.getPercentualEsgoto().toString(), conta
						.getConsumoTarifa().getId(), usuarioLogado);
				
				//[UC0150] - Retificar Conta
				Conta contaParaRetificacao =  this.getControladorFaturamento().pesquisarContaRetificacao(conta.getId());
				
				/*
				 * RM 6873
				 * 
				 * OBJ: N�o permitir que a conta retificada fique com valor negativo.
				 * 
				 * NEG�CIO: Verifica se o valor da conta retificada ser� MAIOR ou IGUAL a zero, caso o retorno seja positivo,
				 * continuar com a retifica��o da conta, caso contr�rio, passar para a pr�xima conta.
				 * 
				 * Colocado por Raphael Rossiter em 12/03/2012
				 */
				boolean podeRetificarConta = this.getControladorFaturamento().verificarNaoRetificacaoConta(conta.getId(), valoresConta);
				
				/*
				 * Verifica se a data do vencimento da conta foi preenchida. 
				 * Se n�o for, utilizar a data de vencimento original da conta.
				 */
				if (dataVencimento == null || dataVencimento.equals("") || dataVencimentoNula){
					
					dataVencimento = contaParaRetificacao.getDataVencimentoOriginal();
					dataVencimentoNula = true;
				}
				
				if (podeRetificarConta){
					
					this.getControladorFaturamento().retificarConta(
							contaParaRetificacao.getReferencia(), contaParaRetificacao, contaParaRetificacao.getImovel(), colecaoDebitoCobrado,
							colecaoCreditoRealizado, ligacaoAguaSituacao, ligacaoEsgotoSituacao,
							colecaoCategoriaOUSubcategoria, consumoAgua.toString(), consumoEsgoto.toString(),
							contaParaRetificacao.getPercentualEsgoto().toString(), dataVencimento, valoresConta, 
							contaMotivoRetificacao, null, usuarioLogado, contaParaRetificacao.getConsumoTarifa().getId().toString(),
							false, null, null, false, null, null, null, null, null, substituirClienteConta);
				}
			}
		}
	}
}
