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
package gsan.fachada;

import gsan.arrecadacao.ControladorArrecadacaoLocal;
import gsan.arrecadacao.ControladorArrecadacaoLocalHome;
import gsan.atendimentopublico.ordemservico.ControladorOrdemServicoLocal;
import gsan.atendimentopublico.ordemservico.ControladorOrdemServicoLocalHome;
import gsan.cadastro.ControladorCadastroLocal;
import gsan.cadastro.ControladorCadastroLocalHome;
import gsan.cadastro.EnvioEmail;
import gsan.cadastro.imovel.Categoria;
import gsan.cadastro.imovel.ControladorImovelLocal;
import gsan.cadastro.imovel.ControladorImovelLocalHome;
import gsan.cobranca.ControladorCobrancaLocal;
import gsan.cobranca.ControladorCobrancaLocalHome;
import gsan.faturamento.ControladorFaturamentoLocal;
import gsan.faturamento.ControladorFaturamentoLocalHome;
import gsan.faturamento.conta.Fatura;
import gsan.faturamento.conta.FiltroFatura;
import gsan.financeiro.ControladorFinanceiroLocal;
import gsan.financeiro.ControladorFinanceiroLocalHome;
import gsan.gerencial.micromedicao.ControladorGerencialMicromedicaoLocal;
import gsan.gerencial.micromedicao.ControladorGerencialMicromedicaoLocalHome;
import gsan.micromedicao.ControladorMicromedicaoLocal;
import gsan.micromedicao.ControladorMicromedicaoLocalHome;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesJNDI;
import gsan.util.ControladorException;
import gsan.util.ControladorUtilLocal;
import gsan.util.ControladorUtilLocalHome;
import gsan.util.ErroRepositorioException;
import gsan.util.FachadaException;
import gsan.util.ServiceLocator;
import gsan.util.ServiceLocatorException;
import gsan.util.SistemaException;
import gsan.util.email.ErroEmailException;
import gsan.util.email.ServicosEmail;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.ejb.CreateException;

/**
 * Fachada batch
 * 
 * @author Thiago Toscano
 * @created 19/04/2006
 */
public class FachadaBatch {

	private static FachadaBatch instancia;

	/**
	 * Construtor da classe Fachada
	 */
	private FachadaBatch() {

	}

	/**
	 * Retorna o valor de instancia
	 * 
	 * @return O valor de instancia
	 */
	public static FachadaBatch getInstancia() {
		if (instancia == null) {
			instancia = new FachadaBatch();
		}
		return instancia;
	}

	// *************----M�todos do SERVICE LOCATOR (CONTROLADORES)
	// ----*************//
	/**
	 * Retorna o valor de controladorUtil
	 * 
	 * @return O valor de controladorUtil
	 */
	private ControladorUtilLocal getControladorUtil() {

		ControladorUtilLocalHome localHome = null;
		ControladorUtilLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorUtilLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_UTIL_SEJB);
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
	 * Retorna o valor de controladorMicromedicao
	 * 
	 * @return O valor de controladorMicromedicao
	 */
	private ControladorMicromedicaoLocal getControladorMicromedicao() {
		ControladorMicromedicaoLocalHome localHome = null;
		ControladorMicromedicaoLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorMicromedicaoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_MICROMEDICAO_SEJB);
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
	 * Retorna a interface remota de ControladorGerencial
	 * 
	 * @return A interface remota do controlador de par�metro
	 */
	private ControladorCobrancaLocal getControladorCobranca() {
		ControladorCobrancaLocalHome localHome = null;
		ControladorCobrancaLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorCobrancaLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_COBRANCA_SEJB);
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
	 * Retorna a interface remota de ControladorGerencial
	 * 
	 * @return A interface remota do controlador de par�metro
	 */
	/*private ControladorGerencialCadastroLocal getControladorGerencialCadastro() {
		ControladorGerencialCadastroLocalHome localHome = null;
		ControladorGerencialCadastroLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorGerencialCadastroLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_GERENCIAL_CADASTRO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas �
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}*/

	/**
	 * Retorna o controladorOrdemServico
	 * 
	 * @author Leonardo Regis
	 * @date 18/08/2006
	 * 
	 */
	private ControladorOrdemServicoLocal getControladorOrdemServico() {
		ControladorOrdemServicoLocalHome localHome = null;
		ControladorOrdemServicoLocal local = null;

		ServiceLocator locator = null;
		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorOrdemServicoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_ORDEM_SERVICO_SEJB);

			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	/**
	 * Retorna a interface remota de ControladorGerencial
	 * 
	 * @return A interface remota do controlador de par�metro
	 */
	private ControladorFinanceiroLocal getControladorFinanceiro() {
		ControladorFinanceiroLocalHome localHome = null;
		ControladorFinanceiroLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorFinanceiroLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FINANCEIRO_SEJB);
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
	 * Retorna a interface remota de ControladorGerencial
	 * 
	 * @return A interface remota do controlador de par�metro
	 */
	/*private ControladorGerencialFaturamentoLocal getControladorGerencialFaturamento() {
		ControladorGerencialFaturamentoLocalHome localHome = null;
		ControladorGerencialFaturamentoLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorGerencialFaturamentoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_GERENCIAL_FATURAMENTO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas �
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}*/

	/**
	 * Retorna a interface remota de ControladorGerencial
	 * 
	 * @return A interface remota do controlador de par�metro
	 */
	/*private ControladorGerencialCobrancaLocal getControladorGerencialCobranca() {
		ControladorGerencialCobrancaLocalHome localHome = null;
		ControladorGerencialCobrancaLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorGerencialCobrancaLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_GERENCIAL_COBRANCA_SEJB);
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
*/
	/**
	 * Retorna o valor de controladorArrecadacao
	 * 
	 * @return O valor de controladorCliente
	 */
	private ControladorArrecadacaoLocal getControladorArrecadacao() {
		ControladorArrecadacaoLocalHome localHome = null;
		ControladorArrecadacaoLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorArrecadacaoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_ARRECADACAO_SEJB);
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
	 * Retorna o valor de controladorImovel
	 * 
	 * @return O valor de controladorImovel
	 */
	private ControladorImovelLocal getControladorImovel() {
		ControladorImovelLocalHome localHome = null;
		ControladorImovelLocal local = null;

		// pega a inst�ncia do ServiceLocator.

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
	 * Retorna o valor de controladorLocalidade
	 * 
	 * @return O valor de controladorLocalidade
	 */
	private ControladorFaturamentoLocal getControladorFaturamento() {
		ControladorFaturamentoLocalHome localHome = null;
		ControladorFaturamentoLocal local = null;

		// pega a inst�ncia do ServiceLocator.

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
	 * Retorna a interface remota de ControladorGerencial
	 * 
	 * @return A interface remota do controlador de par�metro
	 */
	private ControladorGerencialMicromedicaoLocal getControladorGerencialMicromedicao() {
		ControladorGerencialMicromedicaoLocalHome localHome = null;
		ControladorGerencialMicromedicaoLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorGerencialMicromedicaoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_GERENCIAL_MICROMEDICAO_SEJB);
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
	 * [UC0319] Gerar Movimento de D�bito Autom�tico para o banco
	 * 
	 * pesquisa os movimentos de d�bito autom�tico para o banco,referentes ao
	 * grupo e ano/m�s de faturamento informados
	 * 
	 * [SB0002] - Carregar Lista de Bancos
	 * 
	 * @author S�vio Luiz
	 * @date 18/04/2006
	 * 
	 * @param idFaturamentoGrupo,anoMesReferenciaFaturamento,idBanco
	 * @return Cole��o de DebitoAutomaticoMovimento
	 * @throws ControladorException
	 * 
	 * 
	 * public Collection<DebitoAutomaticoMovimento>
	 * pesquisaDebitoAutomaticoMovimento( Integer idFaturamentoGrupo, Integer
	 * anoMesReferenciaFaturamento, Integer idBanco){ try { return
	 * this.getControladorArrecadacao().pesquisaDebitoAutomaticoMovimento(idFaturamentoGrupo,anoMesReferenciaFaturamento,
	 * idBanco); } catch (ControladorException ex) { throw new
	 * FachadaException(ex.getMessage(), ex, ex .getParametroMensagem()); } }
	 * 
	 */

	/**
	 * M�todo que gera o resumo das liga��es e economias
	 * 
	 * [UC0275] - Gerar Resumo das Liga��es/Economias
	 * 
	 * @author Thiago Toscano
	 * @date 19/04/2006
	 */
	/*
	 * public void gerarResumoLigacoesEconomias() throws ErroEmailException {
	 * try { this.getControladorGerencialCadastro()
	 * .gerarResumoLigacoesEconomias();
	 * ServicosEmail.enviarMensagem("gsan@compesa.com.br",
	 * "aryed@compesa.com.br", "Projeto GCOM", "Aviso de Conclus�o do Batch",
	 * "[UC0275] - gerarResumoLigacoesEconomias finalizou"); } catch (Exception
	 * ex) { ex.printStackTrace(); ServicosEmail
	 * .enviarMensagem("gsan@compesa.com.br", "aryed@compesa.com.br", "Projeto
	 * GCOM", "Aviso de Conclus�o do Batch", "[UC0275] -
	 * gerarResumoLigacoesEconomias finalizou com FALHA"); } }
	 */
	/**
	 * M�todo que gera o resumo das situacoes especial de faturamento
	 * 
	 * [UC0341] -
	 * 
	 * @author Thiago Toscano
	 * @date 19/04/2006
	 */
	/*
	 * public void gerarResumoSituacaoEspecialFaturamento() throws
	 * ErroEmailException { try { this.getControladorGerencialFaturamento()
	 * .gerarResumoSituacaoEspecialFaturamento(); ServicosEmail
	 * .enviarMensagem("gsan@compesa.com.br", "aryed@compesa.com.br", "Projeto
	 * GCOM", "Aviso de Conclus�o do Batch", "[UC0341] -
	 * gerarResumoSituacaoEspecialFaturamento finalizou"); } catch (Exception
	 * ex) { ex.printStackTrace(); ServicosEmail
	 * .enviarMensagem("gsan@compesa.com.br", "aryed@compesa.com.br", "Projeto
	 * GCOM", "Aviso de Conclus�o do Batch", "[UC0341] -
	 * gerarResumoSituacaoEspecialFaturamento finalizou com FALHA"); } }
	 */
	/**
	 * M�todo que gera o resumo das situacoes especial de Cobranca
	 * 
	 * [UC0346] -
	 * 
	 * @author Thiago Toscano
	 * @throws ErroEmailException
	 * @date 19/04/2006
	 */
	public void gerarResumoSituacaoEspecialCobranca() throws ErroEmailException {
		try {
			EnvioEmail envioEmail = getControladorCadastro()
					.pesquisarEnvioEmail(
							EnvioEmail.GERAR_RESUMO_SITUACAO_ESPECIAL_COBRANCA);
			String remetente = envioEmail.getEmailRemetente();
			String receptor = envioEmail.getEmailReceptor();
			String titulo = envioEmail.getTituloMensagem();
			String corpoMensagem = envioEmail.getCorpoMensagem();
			/*
			 * this.getControladorGerencialCobranca()
			 * .gerarResumoSituacaoEspecialCobranca();
			 */ServicosEmail.enviarMensagem(remetente, receptor, titulo,
					corpoMensagem);

		} catch (Exception ex) {
			try {
				EnvioEmail envioEmail = getControladorCadastro()
						.pesquisarEnvioEmail(
								EnvioEmail.GERAR_RESUMO_SITUACAO_ESPECIAL_COBRANCA_FALHA);
				String remetente = envioEmail.getEmailRemetente();
				String receptor = envioEmail.getEmailReceptor();
				String titulo = envioEmail.getTituloMensagem();
				String corpoMensagem = envioEmail.getCorpoMensagem();
				ex.printStackTrace();
				ServicosEmail.enviarMensagem(remetente, receptor, titulo,
						corpoMensagem);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public Categoria obterPrincipalCategoriaImovel(Integer idImovel) {
		try {
			return this.getControladorImovel().obterPrincipalCategoriaImovel(
					idImovel);
		} catch (ControladorException ex) {
			throw new FachadaException(ex.getMessage(), ex, ex
					.getParametroMensagem());
		}
	}

	/**
	 * Este caso de uso fera o resumo da pend�ncia
	 * 
	 * [UC0335] Gerar Resumo da Pend�ncia
	 * 
	 * <Breve descri��o sobre o subfluxo>
	 * 
	 * gerarResumoPendencia
	 * 
	 * @author Roberta Costa
	 * @date 15/05/2006
	 * 
	 * @throws ControladorException
	 */
	/*
	 * public void gerarResumoPendencia() throws ErroEmailException { try {
	 * this.getControladorGerencialCobranca().gerarResumoPendencia(890);
	 * ServicosEmail.enviarMensagem("gsan@compesa.com.br",
	 * "aryed@compesa.com.br", "Projeto GCOM", "Aviso de Conclus�o do Batch",
	 * "[UC0335] - gerarResumoPendencia finalizou"); } catch (Exception ex) {
	 * ex.printStackTrace(); ServicosEmail.enviarMensagem("gsan@compesa.com.br",
	 * "aryed@compesa.com.br", "Projeto GCOM", "Aviso de Conclus�o do Batch",
	 * "[UC0335] - gerarResumoPendencia finalizou com FALHA"); } }
	 */

	/**
	 * Este caso de uso fera o resumo anormalidade
	 * 
	 * [UC0343] Gerar Resumo de Anormalidade
	 * 
	 * <Breve descri��o sobre o subfluxo>
	 * 
	 * 
	 * @author Fl�vio Cordeiro
	 * @throws ErroEmailException
	 * @date 15/05/2006
	 * 
	 * @throws ControladorException
	 */
	public void gerarResumoAnormalidadeConsumo() throws ErroEmailException {
		try {
			this.getControladorGerencialMicromedicao()
					.gerarResumoAnormalidadeConsumo();
			EnvioEmail envioEmail = getControladorCadastro()
					.pesquisarEnvioEmail(
							EnvioEmail.GERAR_RESUMO_ANORMALIDADE_CONSUMO);
			String remetente = envioEmail.getEmailRemetente();
			String receptor = envioEmail.getEmailReceptor();
			String titulo = envioEmail.getTituloMensagem();
			String corpoMensagem = envioEmail.getCorpoMensagem();
			ServicosEmail.enviarMensagem(remetente, receptor, titulo,
					corpoMensagem);

		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				EnvioEmail envioEmail = getControladorCadastro()
						.pesquisarEnvioEmail(
								EnvioEmail.GERAR_RESUMO_ANORMALIDADE_CONSUMO_FALHA);
				String remetente = envioEmail.getEmailRemetente();
				String receptor = envioEmail.getEmailReceptor();
				String titulo = envioEmail.getTituloMensagem();
				String corpoMensagem = envioEmail.getCorpoMensagem();
				ServicosEmail.enviarMensagem(remetente, receptor, titulo,
						corpoMensagem);
			} catch (Exception e) {
				ex.printStackTrace();
			}

		}
	}

	/**
	 * Este caso de uso fera o resumo da pend�ncia
	 * 
	 * [UC0335] Gerar Resumo da Pend�ncia
	 * 
	 * <Breve descri��o sobre o subfluxo>
	 * 
	 * gerarResumoPendencia
	 * 
	 * @author Roberta Costa
	 * @date 15/05/2006
	 * 
	 * @throws ControladorException
	 */
	public void gerarResumoAnormalidadeLeitura() throws ErroEmailException {
		try {
			/*
			 * this.getControladorGerencialMicromedicao()
			 * .gerarResumoAnormalidadeLeitura();
			 */EnvioEmail envioEmail = getControladorCadastro()
					.pesquisarEnvioEmail(
							EnvioEmail.GERAR_RESUMO_ANORMALIDADE_LEITURA);
			String remetente = envioEmail.getEmailRemetente();
			String receptor = envioEmail.getEmailReceptor();
			String titulo = envioEmail.getTituloMensagem();
			String corpoMensagem = envioEmail.getCorpoMensagem();
			ServicosEmail.enviarMensagem(remetente, receptor, titulo,
					corpoMensagem);

		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				EnvioEmail envioEmail = getControladorCadastro()
						.pesquisarEnvioEmail(
								EnvioEmail.GERAR_RESUMO_ANORMALIDADE_LEITURA_FALHA);
				String remetente = envioEmail.getEmailRemetente();
				String receptor = envioEmail.getEmailReceptor();
				String titulo = envioEmail.getTituloMensagem();
				String corpoMensagem = envioEmail.getCorpoMensagem();
				ServicosEmail.enviarMensagem(remetente, receptor, titulo,
						corpoMensagem);
			} catch (Exception e) {
				ex.printStackTrace();
			}

		}
	}

	/**
	 * Met�do respons�vel por emitir os txts das contas.
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * @author S�vio Luiz
	 * @date 15/05/2006
	 * 
	 * @param colecaoConta
	 * @throws ControladorException
	 */
	/*
	 * public void emitirContas(Collection colecaoContas) throws
	 * ErroEmailException { try {
	 * this.getControladorFaturamento().emitirContas();
	 * ServicosEmail.enviarMensagem("gsan@compesa.com.br",
	 * "aryed@compesa.com.br", "Projeto GCOM", "Aviso de Conclus�o do Batch",
	 * "[UC0348] - emitirContas finalizou"); } catch (Exception ex) {
	 * ex.printStackTrace(); ServicosEmail.enviarMensagem("gsan@compesa.com.br",
	 * "aryed@compesa.com.br", "Projeto GCOM", "Aviso de Conclus�o do Batch",
	 * "[UC0348] - emitirContas finalizou com FALHA"); } }
	 */
	/**
	 * 
	 * Este caso de uso permite a emiss�o de um ou mais documentos de cobran�a
	 * 
	 * [UC0349] Emitir Documento de Cobran�a
	 * 
	 * @author Raphael Rossiter
	 * @data 26/05/2006
	 * 
	 * @param
	 * @return void
	 */
	/*
	 * public void emitirDocumentoCobranca() throws ErroEmailException { try { //
	 * this.getControladorCobranca().emitirDocumentoCobranca();
	 * ServicosEmail.enviarMensagem("gsan@compesa.com.br",
	 * "aryed@compesa.com.br", "Projeto GCOM", "Aviso de Conclus�o do Batch",
	 * "[UC0349] - emitirDocumentoCobranca finalizou"); } catch (Exception ex) {
	 * ex.printStackTrace(); try{ EnvioEmail envioEmail =
	 * getControladorCadastro().pesquisarEnvioEmail(EnvioEmail.GERAR_RESUMO_ANORMALIDADE_LEITURA_FALHA);
	 * String remetente = envioEmail.getEmailRemetente(); String receptor =
	 * envioEmail.getEmailReceptor(); String titulo =
	 * envioEmail.getTituloMensagem(); String corpoMensagem =
	 * envioEmail.getCorpoMensagem(); ServicosEmail .enviarMensagem(remetente,
	 * receptor, titulo, corpoMensagem); } catch (Exception e) {
	 * ex.printStackTrace(); } } }
	 */

	/**
	 * Pesquisa todas as contas para testar o batch
	 * 
	 * 
	 * @author S�vio Luiz
	 * @date 02/06/2006
	 * 
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarIdsTodasConta() throws ErroEmailException {
		Collection retorno = null;
		try {
			retorno = this.getControladorFaturamento().pesquisarIdsTodasConta();
			EnvioEmail envioEmail = getControladorCadastro()
					.pesquisarEnvioEmail(EnvioEmail.PESQUISAR_IDS_TODAS_CONTAS);
			String remetente = envioEmail.getEmailRemetente();
			String receptor = envioEmail.getEmailReceptor();
			String titulo = envioEmail.getTituloMensagem();
			String corpoMensagem = envioEmail.getCorpoMensagem();
			ServicosEmail.enviarMensagem(remetente, receptor, titulo,
					corpoMensagem);

			return retorno;

		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				EnvioEmail envioEmail = getControladorCadastro()
						.pesquisarEnvioEmail(
								EnvioEmail.PESQUISAR_IDS_TODAS_CONTAS_FALHA);
				String remetente = envioEmail.getEmailRemetente();
				String receptor = envioEmail.getEmailReceptor();
				String titulo = envioEmail.getTituloMensagem();
				String corpoMensagem = envioEmail.getCorpoMensagem();
				ServicosEmail.enviarMensagem(remetente, receptor, titulo,
						corpoMensagem);
			} catch (Exception e) {
				ex.printStackTrace();
			}

		}
		return retorno;
	}

	/**
	 * Este caso de uso permite classificar os pagamentos e as devolu��es no m�s
	 * de arrecada��o corrente
	 * 
	 * [UC0300] Classificar Pagamentos e Devolu��es
	 * 
	 * @author Raphael Rossiter
	 * @date 18/04/2006
	 * 
	 * @param
	 * @return void
	 */
	public void classificarPagamentosDevolucoes() throws ErroEmailException {
			Collection<Integer> localidade = new ArrayList();
			
			localidade.add(123);
			
			try {
				this.getControladorArrecadacao().classificarPagamentosDevolucoes(localidade,0);
			} catch (ControladorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	/**
	 * Este caso de uso gera todas as faturas do cliente respons�vel pelo im�vel
	 * 
	 * [UC0320] Gerar Fatura de Cliente Respons�vel
	 * 
	 * @author Pedro Alexandre
	 * @date 02/06/2006
	 * 
	 */
	public void gerarFaturaClienteResponsavel(Integer idFuncionalidadeIniciada) throws ErroEmailException {
		try {
			this.getControladorFaturamento().gerarFaturaClienteResponsavel(idFuncionalidadeIniciada);
			EnvioEmail envioEmail = getControladorCadastro()
					.pesquisarEnvioEmail(
							EnvioEmail.GERAR_FATURAMENTO_CLIENTE_RESPONSAVEL);
			String remetente = envioEmail.getEmailRemetente();
			String receptor = envioEmail.getEmailReceptor();
			String titulo = envioEmail.getTituloMensagem();
			String corpoMensagem = envioEmail.getCorpoMensagem();
			ServicosEmail.enviarMensagem(remetente, receptor, titulo,
					corpoMensagem);

		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				EnvioEmail envioEmail = getControladorCadastro()
						.pesquisarEnvioEmail(
								EnvioEmail.GERAR_FATURAMENTO_CLIENTE_RESPONSAVEL_FALHA);
				String remetente = envioEmail.getEmailRemetente();
				String receptor = envioEmail.getEmailReceptor();
				String titulo = envioEmail.getTituloMensagem();
				String corpoMensagem = envioEmail.getCorpoMensagem();
				ServicosEmail.enviarMensagem(remetente, receptor, titulo,
						corpoMensagem);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * Este caso de uso emite(via txt) todas as faturas do cliente respons�vel
	 * pelo im�vel
	 * 
	 * [UC0321] Emitir Fatura de Cliente Respons�vel
	 * 
	 * @author Pedro Alexandre
	 * @date 02/06/2006
	 * 
	 */
	/*
	 * public void emitirFaturaClienteResponsavel() throws ErroEmailException {
	 * try { this.getControladorFaturamento().gerarFaturaClienteResponsavel();
	 * ServicosEmail.enviarMensagem("gsan@compesa.com.br",
	 * "aryed@compesa.com.br", "Projeto GCOM", "Aviso de Conclus�o do Batch",
	 * "[UC0321] - emitirFaturaClienteResponsavel finalizou"); } catch
	 * (Exception ex) { ex.printStackTrace(); ServicosEmail
	 * .enviarMensagem("gsan@compesa.com.br", "aryed@compesa.com.br", "Projeto
	 * GCOM", "Aviso de Conclus�o do Batch", "[UC0321] -
	 * emitirFaturaClienteResponsavel finalizou com FALHA"); } }
	 */

	/**
	 * Encerra a arrecada��o do ano/m�s atual
	 * 
	 * [UC0276] Encerrar Arrecada��o do M�s
	 * 
	 * @author Pedro Alexandre
	 * @date 16/05/2006
	 * 
	 * @throws ControladorException
	 */
	public void encerrarArrecadacaoMes(
			Collection<Integer> colecaoIdsLocalidades,
			int idFuncionalidadeIniciada) throws ErroEmailException {
		try {
			this.getControladorArrecadacao().encerrarArrecadacaoMes(
					colecaoIdsLocalidades, idFuncionalidadeIniciada);
			EnvioEmail envioEmail = getControladorCadastro()
					.pesquisarEnvioEmail(EnvioEmail.ENCERRAR_ARRECADACAO_MES);
			String remetente = envioEmail.getEmailRemetente();
			String receptor = envioEmail.getEmailReceptor();
			String titulo = envioEmail.getTituloMensagem();
			String corpoMensagem = envioEmail.getCorpoMensagem();
			ServicosEmail.enviarMensagem(remetente, receptor, titulo,
					corpoMensagem);

		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				EnvioEmail envioEmail = getControladorCadastro()
						.pesquisarEnvioEmail(
								EnvioEmail.ENCERRAR_ARRECADACAO_MES_FALHA);
				String remetente = envioEmail.getEmailRemetente();
				String receptor = envioEmail.getEmailReceptor();
				String titulo = envioEmail.getTituloMensagem();
				String corpoMensagem = envioEmail.getCorpoMensagem();
				ServicosEmail.enviarMensagem(remetente, receptor, titulo,
						corpoMensagem);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * Gerar dados di�rios da arrecada��o
	 * 
	 * [UC0301] Gerar Dados Di�rios da Arrecada��o
	 * 
	 * @author Pedro Alexandre
	 * @date 16/05/2006
	 * 
	 * @throws ControladorException
	 */
	/*
	 * public void gerarDadosDiariosArrecadacao() throws ErroEmailException {
	 * try { // this.getControladorArrecadacao().gerarDadosDiariosArrecadacao();
	 * ServicosEmail.enviarMensagem("gsan@compesa.com.br",
	 * "aryed@compesa.com.br", "Projeto GCOM", "Aviso de Conclus�o do Batch",
	 * "[UC0301] - gerarDadosDiariosArrecadacao finalizou"); } catch (Exception
	 * ex) { ex.printStackTrace(); ServicosEmail
	 * .enviarMensagem("gsan@compesa.com.br", "aryed@compesa.com.br", "Projeto
	 * GCOM", "Aviso de Conclus�o do Batch", "[UC0301] -
	 * gerarDadosDiariosArrecadacao finalizou com FALHA"); } }
	 */

	/**
	 * Este caso de uso gera todas as faturas do cliente respons�vel pelo im�vel
	 * 
	 * @author Pedro
	 * @date 02/06/2006
	 * 
	 */
	public void gerarLancamentoContabeisArrecadacao(Integer anoMes)
			throws ErroEmailException {
		try {
			this.getControladorFinanceiro()
					.gerarLancamentoContabeisArrecadacao(anoMes,1,1);
			EnvioEmail envioEmail = getControladorCadastro()
					.pesquisarEnvioEmail(
							EnvioEmail.GERAR_MOVIMENTO_CONTABEIS_ARRECADACAO);
			String remetente = envioEmail.getEmailRemetente();
			String receptor = envioEmail.getEmailReceptor();
			String titulo = envioEmail.getTituloMensagem();
			String corpoMensagem = envioEmail.getCorpoMensagem();
			ServicosEmail.enviarMensagem(remetente, receptor, titulo,
					corpoMensagem);

		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				EnvioEmail envioEmail = getControladorCadastro()
						.pesquisarEnvioEmail(
								EnvioEmail.GERAR_MOVIMENTO_CONTABEIS_ARRECADACAO_FALHA);
				String remetente = envioEmail.getEmailRemetente();
				String receptor = envioEmail.getEmailReceptor();
				String titulo = envioEmail.getTituloMensagem();
				String corpoMensagem = envioEmail.getCorpoMensagem();
				ServicosEmail.enviarMensagem(remetente, receptor, titulo,
						corpoMensagem);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * [UC0213] Desfazer Parcelamentos Por Entrada Nao Paga
	 * 
	 * @author Fernanda Paiva
	 * @date 02/06/2006
	 * 
	 */
	public void desfazerParcelamentosPorEntradaNaoPaga()
			throws ErroEmailException {
		try {
			/*this.getControladorCobranca()
					.desfazerParcelamentosPorEntradaNaoPaga();*/
			EnvioEmail envioEmail = getControladorCadastro()
					.pesquisarEnvioEmail(
							EnvioEmail.DESFAZER_PARCELAMENTO_POR_ENTRADA_NAO_PAGA);
			String remetente = envioEmail.getEmailRemetente();
			String receptor = envioEmail.getEmailReceptor();
			String titulo = envioEmail.getTituloMensagem();
			String corpoMensagem = envioEmail.getCorpoMensagem();
			ServicosEmail.enviarMensagem(remetente, receptor, titulo,
					corpoMensagem);

		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				EnvioEmail envioEmail = getControladorCadastro()
						.pesquisarEnvioEmail(
								EnvioEmail.DESFAZER_PARCELAMENTO_POR_ENTRADA_NAO_PAGA_FALHA);
				String remetente = envioEmail.getEmailRemetente();
				String receptor = envioEmail.getEmailReceptor();
				String titulo = envioEmail.getTituloMensagem();
				String corpoMensagem = envioEmail.getCorpoMensagem();
				ServicosEmail.enviarMensagem(remetente, receptor, titulo,
						corpoMensagem);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 * 
	 * @author Rafael Pinto
	 * @date 22/11/2006
	 * 
	 * @param anoMesReferenciaContabil
	 * @throws ControladorException
	 */
//	public void gerarResumoDevedoresDuvidosos(int anoMesReferenciaContabil)
//			throws ErroEmailException {
//
//		try {
//
//			this.getControladorFinanceiro().gerarResumoDevedoresDuvidosos(
//					anoMesReferenciaContabil);
//
//			EnvioEmail envioEmail = getControladorCadastro()
//					.pesquisarEnvioEmail(
//							EnvioEmail.GERAR_RESUMO_DEVEDORES_DUVIDOSOS);
//			String remetente = envioEmail.getEmailRemetente();
//			String receptor = envioEmail.getEmailReceptor();
//			String titulo = envioEmail.getTituloMensagem();
//			String corpoMensagem = envioEmail.getCorpoMensagem();
//			ServicosEmail.enviarMensagem(remetente, receptor, titulo,
//					corpoMensagem);
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//
//			try {
//				EnvioEmail envioEmail = getControladorCadastro()
//						.pesquisarEnvioEmail(
//								EnvioEmail.GERAR_RESUMO_DEVEDORES_DUVIDOSOS_FALHA);
//				String remetente = envioEmail.getEmailRemetente();
//				String receptor = envioEmail.getEmailReceptor();
//				String titulo = envioEmail.getTituloMensagem();
//				String corpoMensagem = envioEmail.getCorpoMensagem();
//				ServicosEmail.enviarMensagem(remetente, receptor, titulo,
//						corpoMensagem);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//		}
//	}

	/**
	 * [UC0493] Emitir de Extrato de Consumo de Im�vel Condom�nio
	 * 
	 * Fl�vio Cordeiro 08/01/2007
	 */
	/*
	 * public void emitirExtratoConsumoImovelCondominio(String
	 * anoMesFaturamento, String idFaturamento) {
	 * 
	 * this.getControladorFaturamento().emitirExtratoConsumoImovelCondominio(anoMesFaturamento,
	 * idFaturamento); }
	 */
	/**
	 * Rotina Batch que encerra todas as OS de um servi�o tipo especifico que
	 * n�o tenha RA
	 * 
	 * 
	 * @author S�vio Luiz
	 * @date 23/02/2007
	 * 
	 * @throws ControladorException
	 */
	public void encerrarOSDoServicoTipoSemRA(Usuario usuarioLogado,
			Integer idServicoTipo) {
		try {
			this.getControladorOrdemServico().encerrarOSDoServicoTipoSemRA(
					usuarioLogado, idServicoTipo);
		} catch (Exception ex) {
			ex.printStackTrace();
			/*
			 * ServicosEmail.enviarMensagem("gsan@compesa.com.br",
			 * "aryed@compesa.com.br", "Projeto GCOM", "Aviso de Conclus�o do
			 * Batch", "[UC0335] - gerarResumoPendencia finalizou com FALHA");
			 */
		}
	}

//	public void gerarResumoAcoesCobrancaCronograma() throws ErroEmailException {
//		try {
//			Collection colecaoCobrancaGrupoCronogramaMes = getControladorCobranca()
//					.pesquisarCobrancaGrupoCronogramaMes();
//
//			this.getControladorCobranca().gerarResumoAcoesCobrancaCronograma(
//					colecaoCobrancaGrupoCronogramaMes, 0);
//			/*
//			 * ServicosEmail.enviarMensagem("gsan@compesa.com.br",
//			 * "aryed@compesa.com.br", "Projeto GCOM", "Aviso de Conclus�o do
//			 * Batch", "[UC0335] - gerarResumoPendencia finalizou");
//			 */
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			/*
//			 * ServicosEmail.enviarMensagem("gsan@compesa.com.br",
//			 * "aryed@compesa.com.br", "Projeto GCOM", "Aviso de Conclus�o do
//			 * Batch", "[UC0335] - gerarResumoPendencia finalizou com FALHA");
//			 */
//		}
//	}

	/**
	 * Metodo criado para criar os debitos para os parcelamentos q tenham juros
	 * e nao tenha criado o debito dos juros DBTP_ID = 44
	 * 
	 * @author Fl�vio Cordeiro
	 * @date 23/02/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void gerarDebitoCobrarNaoCriados() {
		this.getControladorCobranca().gerarDebitoCobrarNaoCriados();
	}
	

	public void gerarHistorico() {
		try {
			Integer localidade = 12;
			Integer anomes = 200703;
			this.getControladorFaturamento().gerarHistoricoParaEncerrarFaturamento(anomes,localidade,false,0);
		} catch (ControladorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void encerrarArrecadacao() {
		try {
			Collection<Integer> localidade = new ArrayList();
			localidade.add(60);
			this.getControladorArrecadacao().encerrarArrecadacaoMes(localidade,0);
		} catch (ControladorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void emitirFaturaClienteResponsavel() {
		try {
			
			FiltroFatura filtroFatura = new FiltroFatura();
			filtroFatura.adicionarParametro(new ParametroSimples(FiltroFatura.ANO_MES_REFERENCIA,200703));
			
			Collection faturas = new ArrayList();
			
			faturas = getControladorUtil().pesquisar(filtroFatura, Fatura.class.getName());
			this.getControladorFaturamento().emitirFaturaClienteResponsavel(faturas, 200703);
		} catch (ControladorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void gerarLancamentosContabeisFaturamento() {
		try {
			System.out.println("Inicio " + new Date());
			Collection<Integer> idsLocalidade = this.getControladorFinanceiro().pesquisarIdsLocalidadesParaGerarLancamentosContabeisFaturamento(200703);
			
			for(Integer idLocalidade : idsLocalidade){
				this.getControladorFinanceiro().gerarLancamentosContabeisFaturamento(200702,idLocalidade,0);
			}
			
			System.out.println("Fim " + new Date());
		} catch (ControladorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void gerarLancamentosContabeisArrecadacao() {
		try {
			System.out.println("Inicio " + new Date());
			Collection<Integer> idsLocalidade = this.getControladorFinanceiro().pesquisarIdsLocalidadesParaGerarLancamentosContabeisArrecadacao(200702);
			
			for(Integer idLocalidade : idsLocalidade){
				this.getControladorFinanceiro().gerarLancamentoContabeisArrecadacao(200702,idLocalidade,0);
			}
			
			System.out.println("Fim " + new Date());
		} catch (ControladorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void efetuarRateioConsumo() {
		try {
			Collection rotas = new ArrayList();
			rotas.add(1150);
			
			this.getControladorMicromedicao().efetuarRateioDeConsumo(rotas,200705,0);
			
		} catch (ControladorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}