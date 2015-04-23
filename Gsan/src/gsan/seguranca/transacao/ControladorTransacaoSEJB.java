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
package gsan.seguranca.transacao;

import gsan.atendimentopublico.ligacaoagua.CorteTipo;
import gsan.atendimentopublico.ligacaoagua.FiltroCorteTipo;
import gsan.atendimentopublico.ligacaoagua.FiltroLigacaoAgua;
import gsan.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoagua.FiltroMotivoCorte;
import gsan.atendimentopublico.ligacaoagua.FiltroSupressaoTipo;
import gsan.atendimentopublico.ligacaoagua.LigacaoAgua;
import gsan.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoagua.MotivoCorte;
import gsan.atendimentopublico.ligacaoagua.SupressaoTipo;
import gsan.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gsan.atendimentopublico.ordemservico.ControladorOrdemServicoLocal;
import gsan.atendimentopublico.ordemservico.ControladorOrdemServicoLocalHome;
import gsan.atendimentopublico.ordemservico.FiltroSupressaoMotivo;
import gsan.atendimentopublico.ordemservico.OrdemServico;
import gsan.atendimentopublico.ordemservico.ServicoTipo;
import gsan.atendimentopublico.ordemservico.SupressaoMotivo;
import gsan.atendimentopublico.registroatendimento.ControladorRegistroAtendimentoLocal;
import gsan.atendimentopublico.registroatendimento.ControladorRegistroAtendimentoLocalHome;
import gsan.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gsan.atendimentopublico.registroatendimento.MeioSolicitacao;
import gsan.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gsan.cadastro.DadosCadastraisTransacaoBatchHelper;
import gsan.cadastro.ParametrosTransacaoBatchHelper;
import gsan.cadastro.SituacaoAtualizacaoCadastral;
import gsan.cadastro.TipoAlteracaoTransacaoBatchHelper;
import gsan.cadastro.atualizacaocadastral.FiltroClienteAtualizacaoCadastral;
import gsan.cadastro.atualizacaocadastral.FiltroClienteFoneAtualizacaoCadastral;
import gsan.cadastro.atualizacaocadastral.FiltroImovelAtualizacaoCadastral;
import gsan.cadastro.atualizacaocadastral.FiltroImovelSubcategoriaAtualizacaoCadastral;
import gsan.cadastro.atualizacaocadastral.bean.ConsultarMovimentoAtualizacaoCadastralHelper;
import gsan.cadastro.atualizacaocadastral.bean.DadosTabelaAtualizacaoCadastralHelper;
import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.cliente.ClienteAtualizacaoCadastral;
import gsan.cadastro.cliente.ClienteEndereco;
import gsan.cadastro.cliente.ClienteFone;
import gsan.cadastro.cliente.ClienteFoneAtualizacaoCadastral;
import gsan.cadastro.cliente.ClienteImovel;
import gsan.cadastro.cliente.ClienteImovelFimRelacaoMotivo;
import gsan.cadastro.cliente.ClienteRelacaoTipo;
import gsan.cadastro.cliente.ClienteTipo;
import gsan.cadastro.cliente.ControladorClienteLocal;
import gsan.cadastro.cliente.ControladorClienteLocalHome;
import gsan.cadastro.cliente.FiltroCliente;
import gsan.cadastro.cliente.FiltroClienteEndereco;
import gsan.cadastro.cliente.FiltroClienteImovel;
import gsan.cadastro.cliente.FiltroClienteRelacaoTipo;
import gsan.cadastro.cliente.FiltroClienteTipo;
import gsan.cadastro.cliente.FiltroFoneTipo;
import gsan.cadastro.cliente.FiltroOrgaoExpedidorRg;
import gsan.cadastro.cliente.FiltroPessoaSexo;
import gsan.cadastro.cliente.FiltroProfissao;
import gsan.cadastro.cliente.FiltroRamoAtividade;
import gsan.cadastro.cliente.FoneTipo;
import gsan.cadastro.cliente.OrgaoExpedidorRg;
import gsan.cadastro.cliente.PessoaSexo;
import gsan.cadastro.cliente.Profissao;
import gsan.cadastro.cliente.RamoAtividade;
import gsan.cadastro.endereco.EnderecoReferencia;
import gsan.cadastro.endereco.EnderecoTipo;
import gsan.cadastro.endereco.FiltroEnderecoReferencia;
import gsan.cadastro.endereco.FiltroEnderecoTipo;
import gsan.cadastro.endereco.FiltroLogradouroBairro;
import gsan.cadastro.endereco.FiltroLogradouroCep;
import gsan.cadastro.endereco.LogradouroBairro;
import gsan.cadastro.endereco.LogradouroCep;
import gsan.cadastro.geografico.FiltroUnidadeFederacao;
import gsan.cadastro.geografico.UnidadeFederacao;
import gsan.cadastro.imovel.CadastroOcorrencia;
import gsan.cadastro.imovel.ControladorImovelLocal;
import gsan.cadastro.imovel.ControladorImovelLocalHome;
import gsan.cadastro.imovel.FiltroCadastroOcorrencia;
import gsan.cadastro.imovel.FiltroFonteAbastecimento;
import gsan.cadastro.imovel.FiltroImovel;
import gsan.cadastro.imovel.FiltroImovelPerfil;
import gsan.cadastro.imovel.FiltroImovelSubCategoria;
import gsan.cadastro.imovel.FiltroPavimentoCalcada;
import gsan.cadastro.imovel.FiltroPavimentoRua;
import gsan.cadastro.imovel.FiltroSubCategoria;
import gsan.cadastro.imovel.FonteAbastecimento;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.imovel.ImovelAtualizacaoCadastral;
import gsan.cadastro.imovel.ImovelContaEnvio;
import gsan.cadastro.imovel.ImovelPerfil;
import gsan.cadastro.imovel.ImovelSubcategoria;
import gsan.cadastro.imovel.ImovelSubcategoriaAtualizacaoCadastral;
import gsan.cadastro.imovel.ImovelSubcategoriaPK;
import gsan.cadastro.imovel.PavimentoCalcada;
import gsan.cadastro.imovel.PavimentoRua;
import gsan.cadastro.imovel.PocoTipo;
import gsan.cadastro.imovel.Subcategoria;
import gsan.cadastro.localidade.FiltroQuadra;
import gsan.cadastro.localidade.FiltroSetorComercial;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.Quadra;
import gsan.cadastro.localidade.SetorComercial;
import gsan.cadastro.unidade.UnidadeOrganizacional;
import gsan.fachada.Fachada;
import gsan.faturamento.consumotarifa.ConsumoTarifa;
import gsan.gui.ActionServletException;
import gsan.interceptor.ControleAlteracao;
import gsan.interceptor.Interceptador;
import gsan.interceptor.ObjetoTransacao;
import gsan.interceptor.RegistradorOperacao;
import gsan.relatorio.cadastro.GerarRelatorioClienteCpfCnpjValidadosHelper;
import gsan.seguranca.AtributoGrupo;
import gsan.seguranca.acesso.FiltroOperacao;
import gsan.seguranca.acesso.FiltroOperacaoTabela;
import gsan.seguranca.acesso.Operacao;
import gsan.seguranca.acesso.OperacaoEfetuada;
import gsan.seguranca.acesso.OperacaoOrdemExibicao;
import gsan.seguranca.acesso.OperacaoTabela;
import gsan.seguranca.acesso.OperacaoTabelaPK;
import gsan.seguranca.acesso.usuario.FiltroUsuarioAlteracao;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.seguranca.acesso.usuario.UsuarioAcao;
import gsan.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gsan.seguranca.acesso.usuario.UsuarioAlteracao;
import gsan.util.ConstantesJNDI;
import gsan.util.ConstantesSistema;
import gsan.util.ControladorException;
import gsan.util.ControladorUtilLocal;
import gsan.util.ControladorUtilLocalHome;
import gsan.util.ErroRepositorioException;
import gsan.util.HibernateUtil;
import gsan.util.ServiceLocator;
import gsan.util.ServiceLocatorException;
import gsan.util.SistemaException;
import gsan.util.Util;
import gsan.util.filtro.Filtro;
import gsan.util.filtro.ParametroNulo;
import gsan.util.filtro.ParametroSimples;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * Definição da lógica de negócio do Session Bean de ControladorCliente
 * 
 * @author Sávio Luiz
 * @created 25 de Abril de 2005
 */
public class ControladorTransacaoSEJB implements SessionBean {

	private static final long serialVersionUID = 1L;

	private IRepositorioTransacao repositorioTransacao = null;

	SessionContext sessionContext;

	/**
	 * < <Descrição do método>>
	 * 
	 * @exception CreateException
	 *                Descrição da exceção
	 */
	public void ejbCreate() throws CreateException {

		repositorioTransacao = RepositorioTransacaoHBM.getInstancia();
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
	 * Retorna o valor de controladorUtil
	 * 
	 * @return O valor de controladorUtil
	 */
	private ControladorUtilLocal getControladorUtil() {

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
	 * Retorna o valor de controladorCliente
	 * 
	 * @return O valor de controladorImovel
	 */
	protected ControladorClienteLocal getControladorCliente() {

		ControladorClienteLocalHome localHome = null;
		ControladorClienteLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorClienteLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_CLIENTE_SEJB);
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
	private ControladorImovelLocal getControladorImovel() {
		ControladorImovelLocalHome localHome = null;
		ControladorImovelLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorImovelLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_IMOVEL_SEJB);
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
	 * Retorna o valor de controladorRegistroAtendimento
	 * 
	 */
	private ControladorRegistroAtendimentoLocal getControladorRegistroAtendimento() {
		ControladorRegistroAtendimentoLocalHome localHome = null;
		ControladorRegistroAtendimentoLocal local = null;

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorRegistroAtendimentoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_REGISTRO_ATENDIMENTO_SEJB);

			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	/**
	 * Retorna o valor  de controladorOrdemServico
	 */
	private ControladorOrdemServicoLocal getControladorOrdemServico() {
		ControladorOrdemServicoLocalHome localHome = null;
		ControladorOrdemServicoLocal local = null;
		
		ServiceLocator locator = null;
		
		try{
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
	 * Método que consulta os usuario alteracao de uma determinada operacao com
	 * as restricoes passadas
	 * 
	 * @param idOperacao
	 * @param idUsuario
	 * @param dataInicial
	 * @param dataFinal
	 * @param horaInicial
	 * @param hotaFinal
	 * @param idTabela
	 * @param idTabelaColuna
	 * @param id1
	 * 
	 * @return
	 * @throws ControladorException
	 * 
	 * @author thiago toscano
	 * @date 17/02/2006
	 */
	public Collection pesquisarUsuarioAlteracaoDasOperacoesEfetuadas(
			Integer idUsuarioAcao, Integer idOperacao, Integer idUsuario,
			Date dataInicial, Date dataFinal, Date horaInicial, Date horaFinal,
			Hashtable<String,String> argumentos, Integer id1, String unidadeNegocio)
			throws ControladorException {

		try {
			if (null == idUsuarioAcao && null == idOperacao
					&& null == idUsuario && null == dataInicial
					&& null == dataFinal && null == horaInicial
					&& null == horaFinal && null == argumentos
					&& null == id1) {
				throw new ControladorException(
						"atencao.filtro.nenhum_parametro_informado");
			}

			Collection coll = repositorioTransacao
					.pesquisarUsuarioAlteracaoDasOperacoesEfetuadas(
							idUsuarioAcao, idOperacao, idUsuario, dataInicial,
							dataFinal, horaInicial, horaFinal, argumentos, id1, unidadeNegocio);

			/*
			 * Collection coll =
			 * repositorioTransacao.pesquisarUsuarioAlteracaoDasOperacoesEfetuadasHql(idUsuarioAcao,
			 * idOperacao, idUsuario, dataInicial, dataFinal, horaInicial,
			 * horaFinal, idTabela, idTabelaColuna,id1, numeroPagina);
			 */

			// para todas as operacoes efetuadas carregar a lista de
			// usuariosAlteracao
			if (coll != null && !coll.isEmpty()) {
				Iterator it = coll.iterator();
				while (it.hasNext()) {
					OperacaoEfetuada operacaoEfetuada = (OperacaoEfetuada) it
							.next();
					Set set = new HashSet();
					operacaoEfetuada.setUsuarioAlteracoes(set);

					// criando o filtrtro para consultar os usuarios
					FiltroUsuarioAlteracao filtroUsuarioAlteracao = new FiltroUsuarioAlteracao();
					filtroUsuarioAlteracao
							.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioAlteracao.USUARIO);
					filtroUsuarioAlteracao
							.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioAlteracao.USUARIO_ACAO);
					filtroUsuarioAlteracao
							.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioAlteracao.USUARIO_TIPO);
					filtroUsuarioAlteracao
							.adicionarParametro(new ParametroSimples(
									FiltroUsuarioAlteracao.OPERACAO_EFETUADA_ID,
									operacaoEfetuada.getId()));

					Collection usuarioalteracoes = getControladorUtil()
							.pesquisar(filtroUsuarioAlteracao,
									UsuarioAlteracao.class.getSimpleName());
					// para todos os usuarios colocar na lista
					if (usuarioalteracoes != null
							&& !usuarioalteracoes.isEmpty()) {
						Iterator itt = usuarioalteracoes.iterator();
						while (itt.hasNext()) {
							set.add(itt.next());
						}
					}
				}
			}

			return coll;
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * Método que consulta os usuario alteracao de uma determinada operacao com
	 * as restricoes passadas
	 * 
	 * @param idOperacao
	 * @param idUsuario
	 * @param dataInicial
	 * @param dataFinal
	 * @param horaInicial
	 * @param hotaFinal
	 * @param idTabela
	 * @param idTabelaColuna
	 * @param id1
	 * 
	 * @return
	 * @throws ControladorException
	 * 
	 * @author Romulo Aurelio
	 * @date 11/05/2007
	 */
	// UC12880 - Consultar Cliente.
	public Collection pesquisarUsuarioAlteracaoDasOperacoesEfetuadasHql(
			Integer idUsuarioAcao, String[] idOperacoes, String idUsuario,
			Date dataInicial, Date dataFinal, Date horaInicial, Date horaFinal,
			Hashtable<String,String> argumentos, Integer id1,
			Integer numeroPagina, String unidadeNegocio, boolean indicadorConsultarImovel) throws ControladorException {

		try {
			if (null == idUsuarioAcao && null == idUsuario && null == dataInicial
					&& null == dataFinal && null == horaInicial
					&& null == horaFinal && null == argumentos
					&& null == id1) {
				throw new ControladorException(
						"atencao.filtro.nenhum_parametro_informado");
			}

			/*
			 * Collection coll =
			 * repositorioTransacao.pesquisarUsuarioAlteracaoDasOperacoesEfetuadas(idUsuarioAcao,
			 * idOperacao, idUsuario, dataInicial, dataFinal, horaInicial,
			 * horaFinal, idTabela, idTabelaColuna,id1);
			 */
			// UC12880 - Consultar Cliente.
			Collection coll = repositorioTransacao
					.pesquisarUsuarioAlteracaoDasOperacoesEfetuadasHql(
							idUsuarioAcao, idOperacoes, idUsuario, dataInicial,
							dataFinal, horaInicial, horaFinal, argumentos, id1, numeroPagina,unidadeNegocio, indicadorConsultarImovel);

			// para todas as operacoes efetuadas carregar a lista de
			// usuariosAlteracao
			if (coll != null && !coll.isEmpty()) {
				Iterator it = coll.iterator();
				while (it.hasNext()) {
					OperacaoEfetuada operacaoEfetuada = (OperacaoEfetuada) it
							.next();
					Set set = new HashSet();
					operacaoEfetuada.setUsuarioAlteracoes(set);

					// criando o filtrtro para consultar os usuarios
					FiltroUsuarioAlteracao filtroUsuarioAlteracao = new FiltroUsuarioAlteracao();
					filtroUsuarioAlteracao
							.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioAlteracao.USUARIO);
					filtroUsuarioAlteracao
							.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioAlteracao.USUARIO_ACAO);
					filtroUsuarioAlteracao
							.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioAlteracao.USUARIO_TIPO);
					filtroUsuarioAlteracao
							.adicionarParametro(new ParametroSimples(
									FiltroUsuarioAlteracao.OPERACAO_EFETUADA_ID,
									operacaoEfetuada.getId()));

					Collection usuarioalteracoes = getControladorUtil()
							.pesquisar(filtroUsuarioAlteracao,
									UsuarioAlteracao.class.getSimpleName());
					// para todos os usuarios colocar na lista
					if (usuarioalteracoes != null
							&& !usuarioalteracoes.isEmpty()) {
						Iterator itt = usuarioalteracoes.iterator();
						while (itt.hasNext()) {
							set.add(itt.next());
						}
					}
				}
			}

			return coll;
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * Método que consulta os usuario alteracao de uma determinada operacao com
	 * as restricoes passadas
	 * 
	 * @param idOperacao
	 * @param idUsuario
	 * @param dataInicial
	 * @param dataFinal
	 * @param horaInicial
	 * @param hotaFinal
	 * @param idTabela
	 * @param idTabelaColuna
	 * @param id1
	 * 
	 * @return
	 * @throws ControladorException
	 * 
	 * @author Romulo Aurelio
	 * @date 11/05/2007
	 */

	public Collection pesquisarUsuarioAlteracaoDasOperacoesEfetuadasHqlRelatorio(
			Integer idUsuarioAcao, String[] idOperacoes, String idUsuario,
			Date dataInicial, Date dataFinal, Date horaInicial, Date horaFinal,
			Hashtable<String,String> argumentos, Integer id1, String unidadeNegocio)
			throws ControladorException {

		try {
			if (null == idUsuarioAcao && null == idUsuario && null == dataInicial
					&& null == dataFinal && null == horaInicial
					&& null == horaFinal && null == argumentos
					&& null == id1) {
				throw new ControladorException(
						"atencao.filtro.nenhum_parametro_informado");
			}

			/*
			 * Collection coll =
			 * repositorioTransacao.pesquisarUsuarioAlteracaoDasOperacoesEfetuadas(idUsuarioAcao,
			 * idOperacao, idUsuario, dataInicial, dataFinal, horaInicial,
			 * horaFinal, idTabela, idTabelaColuna,id1);
			 */

			Collection coll = repositorioTransacao
					.pesquisarUsuarioAlteracaoDasOperacoesEfetuadasHqlRelatorio(
							idUsuarioAcao, idOperacoes, idUsuario, dataInicial,
							dataFinal, horaInicial, horaFinal, argumentos, id1, unidadeNegocio);

			// para todas as operacoes efetuadas carregar a lista de
			// usuariosAlteracao
			if (coll != null && !coll.isEmpty()) {
				Iterator it = coll.iterator();
				while (it.hasNext()) {
					OperacaoEfetuada operacaoEfetuada = (OperacaoEfetuada) it
							.next();
					Set set = new HashSet();
					operacaoEfetuada.setUsuarioAlteracoes(set);

					// criando o filtrtro para consultar os usuarios
					FiltroUsuarioAlteracao filtroUsuarioAlteracao = new FiltroUsuarioAlteracao();
					filtroUsuarioAlteracao
							.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioAlteracao.USUARIO);
					filtroUsuarioAlteracao
							.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioAlteracao.USUARIO_ACAO);
					filtroUsuarioAlteracao
							.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioAlteracao.USUARIO_TIPO);
					filtroUsuarioAlteracao
							.adicionarParametro(new ParametroSimples(
									FiltroUsuarioAlteracao.OPERACAO_EFETUADA_ID,
									operacaoEfetuada.getId()));

					Collection usuarioalteracoes = getControladorUtil()
							.pesquisar(filtroUsuarioAlteracao,
									UsuarioAlteracao.class.getSimpleName());
					// para todos os usuarios colocar na lista
					if (usuarioalteracoes != null
							&& !usuarioalteracoes.isEmpty()) {
						Iterator itt = usuarioalteracoes.iterator();
						while (itt.hasNext()) {
							set.add(itt.next());
						}
					}
				}
			}

			return coll;
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * Método que registra uma operacao ao sistema
	 * 
	 * @param operacaoEfetuada
	 * @param tabelaLinhaAlteracao
	 * @param tabelaLinhaColunaAlteracoes
	 * @throws ControladorException
	 */
	public void inserirOperacaoEfetuada(Collection usuariosAcaoUsuarioHelp,
			OperacaoEfetuada operacaoEfetuada,
			TabelaLinhaAlteracao tabelaLinhaAlteracao,
			Collection<TabelaLinhaColunaAlteracao> tabelaLinhaColunaAlteracoes)
			throws ControladorException {

		// caso a operacaoEfetuada for nula
		// Nao ocorre pq o intercepto levanta execao caso nao tenha
		if (operacaoEfetuada == null) {
			operacaoEfetuada = new OperacaoEfetuada();
		}
		// caso a operacao da operacaoEfetuada for nula
		// Nao ocorre pq o interceptador levanta excecao caso nao tenha
		if (operacaoEfetuada.getOperacao() == null) {
			Operacao operacao = new Operacao();
			operacao.setId(Operacao.OPERACAO_AVISO_BANCARIO_ATUALIZAR);
			operacaoEfetuada.setOperacao(operacao);
		}

		// caso nao tenha id é pq nao foi adicionado
		if (operacaoEfetuada.getId() == null) {
			operacaoEfetuada.setUltimaAlteracao(new Date(System
					.currentTimeMillis()));
			Integer idOperacaoEfetuada = (Integer) getControladorUtil().inserirOuAtualizar(operacaoEfetuada);
			operacaoEfetuada.setId(idOperacaoEfetuada);

			if (usuariosAcaoUsuarioHelp != null
					&& !usuariosAcaoUsuarioHelp.isEmpty()) {
				Iterator it = usuariosAcaoUsuarioHelp.iterator();
				while (it.hasNext()) {
					UsuarioAcaoUsuarioHelper usuarioAcaoUsuarioHelp = (UsuarioAcaoUsuarioHelper) it
							.next();

					UsuarioAlteracao usuarioAteracao = new UsuarioAlteracao();
					usuarioAteracao.setOperacaoEfetuada(operacaoEfetuada);
					usuarioAteracao.setUltimaAlteracao(new Date(System.currentTimeMillis()));
					usuarioAteracao.setUsuario(usuarioAcaoUsuarioHelp.getUsuario());
					usuarioAteracao.setUsuarioAcao(usuarioAcaoUsuarioHelp.getUsuarioAcao());
					usuarioAteracao.setEmpresa(usuarioAcaoUsuarioHelp.getUsuario().getEmpresa());
					usuarioAteracao.setIpAlteracao(usuarioAcaoUsuarioHelp.getUsuario().getIpLogado());

					getControladorUtil().inserir(usuarioAteracao);
				}
			}
			
			// Inserir tabela linha alteracao principal -> usada para conseguir recuperar dados do objeto principal
			
		} else if (operacaoEfetuada.getDadosAdicionais() != null){
			getControladorUtil().atualizar(operacaoEfetuada);
		}

		
		if (tabelaLinhaAlteracao != null) {
			
			tabelaLinhaAlteracao.setUltimaAlteracao(new Date(System.currentTimeMillis()));
			tabelaLinhaAlteracao.setOperacaoEfetuada(operacaoEfetuada);
			
			getControladorUtil().inserir(tabelaLinhaAlteracao);

			FiltroOperacaoTabela filtroOperacaoTabela = new FiltroOperacaoTabela();
			filtroOperacaoTabela.adicionarParametro(
				new ParametroSimples(
					FiltroOperacaoTabela.TABELA_ID, tabelaLinhaAlteracao
							.getTabela().getId()));
			filtroOperacaoTabela.adicionarParametro(new ParametroSimples(
					FiltroOperacaoTabela.OPERACAO_ID, operacaoEfetuada
							.getOperacao().getId()));
			
			Collection coll = getControladorUtil().pesquisar(
					filtroOperacaoTabela, OperacaoTabela.class.getSimpleName());
			
			if (coll == null || coll.isEmpty()) {

				OperacaoTabelaPK pk = new OperacaoTabelaPK();
				pk.setOperacaoId(operacaoEfetuada.getOperacao().getId());
				pk.setTabelaId(tabelaLinhaAlteracao.getTabela().getId());

				OperacaoTabela operacaoTabela = new OperacaoTabela();
				operacaoTabela.setComp_id(pk);
				operacaoTabela.setOperacao(operacaoEfetuada.getOperacao());
				operacaoTabela.setTabela(tabelaLinhaAlteracao.getTabela());
				getControladorUtil().inserir(operacaoTabela);
			}

			if (tabelaLinhaColunaAlteracoes != null
					&& !tabelaLinhaColunaAlteracoes.isEmpty()) {
				Iterator it = tabelaLinhaColunaAlteracoes.iterator();
				while (it.hasNext()) {
					TabelaLinhaColunaAlteracao tabelaLinhaColunaAlteracao = (TabelaLinhaColunaAlteracao) it
							.next();
					tabelaLinhaColunaAlteracao
							.setTabelaLinhaAlteracao(tabelaLinhaAlteracao);
					tabelaLinhaColunaAlteracao.setUltimaAlteracao(new Date(
							System.currentTimeMillis()));
					if ( tabelaLinhaColunaAlteracao.getTabelaColuna() != null  && tabelaLinhaColunaAlteracao.getTabelaColuna().getId() != null ) {
						getControladorUtil().inserir(tabelaLinhaColunaAlteracao);
					}
				}
			}
		}
	}
	
	/**
	 * Método que registra uma operacao ao sistema sem utilizacao do Interceptador Hibernate
	 * 
	 * @param operacaoEfetuada
	 * @param tabelaLinhaAlteracao
	 * @param tabelaLinhaColunaAlteracoes
	 * @throws ControladorException
	 * @author Anderson Italo
	 * @date 02/06/2009
	 */
	public void inserirOperacaoEfetuadaBurlandoInterceptador(UsuarioAcaoUsuarioHelper usuarioAcaoUsuarioHelper,
			OperacaoEfetuada operacaoEfetuada,
			TabelaLinhaAlteracao tabelaLinhaAlteracao,
			Collection<TabelaLinhaColunaAlteracao> tabelaLinhaColunaAlteracoes)
			throws ControladorException {

		// caso a operacaoEfetuada for nula
		// Nao ocorre pq o intercepto levanta execao caso nao tenha
		if (operacaoEfetuada == null) {
			operacaoEfetuada = new OperacaoEfetuada();
		}
		
		// caso nao tenha id é pq nao foi adicionado
		if (operacaoEfetuada.getId() == null) {
			operacaoEfetuada.setUltimaAlteracao(new Date(System
					.currentTimeMillis()));
			Integer idOperacaoEfetuada = (Integer) getControladorUtil().inserirOuAtualizar(operacaoEfetuada);
			operacaoEfetuada.setId(idOperacaoEfetuada);

			if (usuarioAcaoUsuarioHelper != null) {
				
					UsuarioAlteracao usuarioAteracao = new UsuarioAlteracao();
					usuarioAteracao.setOperacaoEfetuada(operacaoEfetuada);
					usuarioAteracao.setUltimaAlteracao(new Date(System
							.currentTimeMillis()));
					usuarioAteracao.setUsuario(usuarioAcaoUsuarioHelper
							.getUsuario());
					usuarioAteracao.setUsuarioAcao(usuarioAcaoUsuarioHelper
							.getUsuarioAcao());
					usuarioAteracao.setEmpresa(usuarioAcaoUsuarioHelper
							.getUsuario().getEmpresa());
					getControladorUtil().inserir(usuarioAteracao);
			}
		} 

		
		if (tabelaLinhaAlteracao != null) {
			tabelaLinhaAlteracao.setUltimaAlteracao(new Date(System
					.currentTimeMillis()));
			tabelaLinhaAlteracao.setOperacaoEfetuada(operacaoEfetuada);
			getControladorUtil().inserir(tabelaLinhaAlteracao);

			FiltroOperacaoTabela filtroOperacaoTabela = new FiltroOperacaoTabela();
			filtroOperacaoTabela.adicionarParametro(new ParametroSimples(
					FiltroOperacaoTabela.TABELA_ID, tabelaLinhaAlteracao
							.getTabela().getId()));
			filtroOperacaoTabela.adicionarParametro(new ParametroSimples(
					FiltroOperacaoTabela.OPERACAO_ID, operacaoEfetuada
							.getOperacao().getId()));
			Collection coll = getControladorUtil().pesquisar(
					filtroOperacaoTabela, OperacaoTabela.class.getSimpleName());
			if (coll == null || coll.isEmpty()) {

				OperacaoTabelaPK pk = new OperacaoTabelaPK();
				pk.setOperacaoId(operacaoEfetuada.getOperacao().getId());
				pk.setTabelaId(tabelaLinhaAlteracao.getTabela().getId());

				OperacaoTabela operacaoTabela = new OperacaoTabela();
				operacaoTabela.setComp_id(pk);
				operacaoTabela.setOperacao(operacaoEfetuada.getOperacao());
				operacaoTabela.setTabela(tabelaLinhaAlteracao.getTabela());
				getControladorUtil().inserir(operacaoTabela);
			}

			if (tabelaLinhaColunaAlteracoes != null
					&& !tabelaLinhaColunaAlteracoes.isEmpty()) {
				Iterator it = tabelaLinhaColunaAlteracoes.iterator();
				while (it.hasNext()) {
					TabelaLinhaColunaAlteracao tabelaLinhaColunaAlteracao = (TabelaLinhaColunaAlteracao) it
							.next();
					tabelaLinhaColunaAlteracao
							.setTabelaLinhaAlteracao(tabelaLinhaAlteracao);
					tabelaLinhaColunaAlteracao.setUltimaAlteracao(new Date(
							System.currentTimeMillis()));
					if (tabelaLinhaAlteracao.getAlteracaoTipo().getId() == AlteracaoTipo.INCLUSAO && 
							tabelaLinhaColunaAlteracao.getConteudoColunaAtual() != null){
						getControladorUtil().inserir(tabelaLinhaColunaAlteracao);
						
					}else if (tabelaLinhaAlteracao.getAlteracaoTipo().getId() == AlteracaoTipo.EXCLUSAO && 
							tabelaLinhaColunaAlteracao.getConteudoColunaAnterior() != null){
							getControladorUtil().inserir(tabelaLinhaColunaAlteracao);
					}
				}
			}
		}
	}
	
	/**
	 * Metodo utilizado para efetuar o registro de transacao de um objeto helper
	 * @author Anderson Italo
	 * @date 08/06/2009
	 *
	 * @param usuario
	 * @param idTipoAlteracao
	 * @param objetoHelper
	 * @param operacaoEfetuada
	 * @param idTabela
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public void processaRegistroOperacaoObjetohelper(UsuarioAcaoUsuarioHelper usuario, Integer idTipoAlteracao, 
												     ObjetoTransacao objetoHelper, OperacaoEfetuada operacaoEfetuada, Integer idTabela) {
		
		AlteracaoTipo alteracaoTipo = new AlteracaoTipo();
		alteracaoTipo.setId(idTipoAlteracao);
		
		Tabela tabela = new Tabela();
		tabela.setId(idTabela);
		
		TabelaLinhaAlteracao tabelaLinhaAuteracao = new TabelaLinhaAlteracao();
		tabelaLinhaAuteracao.setAlteracaoTipo(alteracaoTipo);
		tabelaLinhaAuteracao.setTabela(tabela);
		
		
	    Collection<TabelaLinhaColunaAlteracao> colecaoTabelaLinhaColunaAlteracao = null;
		try {
			colecaoTabelaLinhaColunaAlteracao = pesquisarTabelaLinhaColunaAlteracoes(objetoHelper, 
														tabelaLinhaAuteracao, objetoHelper.retornarAtributosSelecionadosRegistro(), alteracaoTipo.getId());
		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
		}
	    
	    if (colecaoTabelaLinhaColunaAlteracao != null && !colecaoTabelaLinhaColunaAlteracao.isEmpty()){
	    	try {
				inserirOperacaoEfetuadaBurlandoInterceptador(usuario, operacaoEfetuada, tabelaLinhaAuteracao, colecaoTabelaLinhaColunaAlteracao);
			} catch (ControladorException e) {
				e.printStackTrace();
			}
	    }
	}
	
	/**
	 * @author Anderson Italo
	 * @date 08/06/2009
	 *
	 * @param objetoHelper
	 * @param tabelaLinhaAlteracao
	 * @param colecaoNomesAtributos
	 * @param idAlteracaoTipo
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private Collection pesquisarTabelaLinhaColunaAlteracoes(ObjetoTransacao objetoHelper, 
			TabelaLinhaAlteracao tabelaLinhaAlteracao, String [] colecaoNomesAtributos, Integer idAlteracaoTipo) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
			Collection<TabelaLinhaColunaAlteracao> colecaoRetorno = new ArrayList<TabelaLinhaColunaAlteracao>();
		String nomeMetodo="";
		Method metodo = null;
		
		for (int i = 0; i < colecaoNomesAtributos.length; i++) {
			nomeMetodo = "get";
			Object[] args = {colecaoNomesAtributos[i]};
			Class[] tipos = {String.class};
			int idTabelaColuna = 0;
			
			Object retornoMetodo = null;
			
		    metodo = objetoHelper.getClass().getMethod(nomeMetodo, tipos);
		
		    retornoMetodo = metodo.invoke(objetoHelper, args);
			
			
			if (retornoMetodo!= null){
				try {
					Annotation annotations[] = objetoHelper.getClass().getDeclaredField(colecaoNomesAtributos[i]).getAnnotations();
					
					for (int j = 0; j < annotations.length; j++) {
						if (annotations[j] instanceof ControleAlteracao){
							ControleAlteracao controleAlteracao = (ControleAlteracao) annotations[j];
							idTabelaColuna = controleAlteracao.idTabelaColuna();
						}
					}
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				}
				
				TabelaColuna tabelaColuna = new TabelaColuna();
				tabelaColuna.setId(idTabelaColuna);
				
				TabelaLinhaColunaAlteracao tabelaLinhaColunaAlteracao = new TabelaLinhaColunaAlteracao();
				
				if (retornoMetodo instanceof ObjetoTransacao){
				    if (AlteracaoTipo.INCLUSAO == idAlteracaoTipo.intValue()){
				    	tabelaLinhaColunaAlteracao.setConteudoColunaAtual(Interceptador.consultarDescricao(retornoMetodo));
				    }else{
				    	tabelaLinhaColunaAlteracao.setConteudoColunaAnterior(Interceptador.consultarDescricao(retornoMetodo));
				    }
				}
				
			    
			    tabelaLinhaColunaAlteracao.setIndicadorAtualizada(new Integer(1).shortValue());
			    tabelaLinhaColunaAlteracao.setTabelaColuna(tabelaColuna);
			    tabelaLinhaColunaAlteracao.setTabelaLinhaAlteracao(tabelaLinhaAlteracao);
			    
			    colecaoRetorno.add(tabelaLinhaColunaAlteracao);
			}
		   
		}
		
		return colecaoRetorno;
	}

	/**
	 * 
	 * Registrar Transacao - Inserir operacao efetuada
	 * @author anamaria
	 * @date 12/05/2009
	 *
	 * @param usuariosAcaoUsuarioHelp
	 * @param operacaoEfetuada
	 * @param tabelaAtualizacaoCadastral
	 * @param colecaoTabelaColunaAtualizacaoCadastral
	 * @throws ControladorException
	 */
	public void inserirOperacaoEfetuadaAtualizacaoCadastral(Collection usuariosAcaoUsuarioHelp,
			OperacaoEfetuada operacaoEfetuada,
			TabelaAtualizacaoCadastral tabelaAtualizacaoCadastral,
			Collection<TabelaColunaAtualizacaoCadastral> colecaoTabelaColunaAtualizacaoCadastral)
			throws ControladorException {

		// caso a operacaoEfetuada for nula
		// Nao ocorre pq o intercepto levanta execao caso nao tenha
		if (operacaoEfetuada == null) {
			operacaoEfetuada = new OperacaoEfetuada();
		}
		
		// caso nao tenha id é pq nao foi adicionado
		if (operacaoEfetuada.getId() == null) {
			operacaoEfetuada.setUltimaAlteracao(new Date(System
					.currentTimeMillis()));
			Integer idOperacaoEfetuada = (Integer) getControladorUtil().inserirOuAtualizar(operacaoEfetuada);
			operacaoEfetuada.setId(idOperacaoEfetuada);

			if (usuariosAcaoUsuarioHelp != null
					&& !usuariosAcaoUsuarioHelp.isEmpty()) {
				Iterator it = usuariosAcaoUsuarioHelp.iterator();
				while (it.hasNext()) {
					UsuarioAcaoUsuarioHelper usuarioAcaoUsuarioHelp = (UsuarioAcaoUsuarioHelper) it
							.next();

					UsuarioAlteracao usuarioAteracao = new UsuarioAlteracao();
					usuarioAteracao.setOperacaoEfetuada(operacaoEfetuada);
					usuarioAteracao.setUltimaAlteracao(new Date(System
							.currentTimeMillis()));
					usuarioAteracao.setUsuario(usuarioAcaoUsuarioHelp
							.getUsuario());
					usuarioAteracao.setUsuarioAcao(usuarioAcaoUsuarioHelp
							.getUsuarioAcao());
					usuarioAteracao.setEmpresa(usuarioAcaoUsuarioHelp
							.getUsuario().getEmpresa());
					getControladorUtil().inserir(usuarioAteracao);
				}
			}
			
			// Inserir tabela linha alteracao principal -> usada para conseguir recuperar dados do objeto principal
			
		} else if (operacaoEfetuada.getDadosAdicionais() != null){
			getControladorUtil().atualizar(operacaoEfetuada);
		}

		
		if (tabelaAtualizacaoCadastral != null) {
			tabelaAtualizacaoCadastral.setUltimaAlteracao(new Date(System
					.currentTimeMillis()));
			tabelaAtualizacaoCadastral.setOperacaoEfetuada(operacaoEfetuada);
			getControladorUtil().inserir(tabelaAtualizacaoCadastral);

			FiltroOperacaoTabela filtroOperacaoTabela = new FiltroOperacaoTabela();
			filtroOperacaoTabela.adicionarParametro(new ParametroSimples(
					FiltroOperacaoTabela.TABELA_ID, tabelaAtualizacaoCadastral
							.getTabela().getId()));
			filtroOperacaoTabela.adicionarParametro(new ParametroSimples(
					FiltroOperacaoTabela.OPERACAO_ID, operacaoEfetuada
							.getOperacao().getId()));
			Collection coll = getControladorUtil().pesquisar(
					filtroOperacaoTabela, OperacaoTabela.class.getSimpleName());
			if (coll == null || coll.isEmpty()) {

				OperacaoTabelaPK pk = new OperacaoTabelaPK();
				pk.setOperacaoId(operacaoEfetuada.getOperacao().getId());
				pk.setTabelaId(tabelaAtualizacaoCadastral.getTabela().getId());

				OperacaoTabela operacaoTabela = new OperacaoTabela();
				operacaoTabela.setComp_id(pk);
				operacaoTabela.setOperacao(operacaoEfetuada.getOperacao());
				operacaoTabela.setTabela(tabelaAtualizacaoCadastral.getTabela());
				getControladorUtil().inserir(operacaoTabela);
			}

			if (colecaoTabelaColunaAtualizacaoCadastral != null
					&& !colecaoTabelaColunaAtualizacaoCadastral.isEmpty()) {
				Iterator it = colecaoTabelaColunaAtualizacaoCadastral.iterator();
				while (it.hasNext()) {
					TabelaColunaAtualizacaoCadastral tabelaColunaAtualizacaoCadastral= (TabelaColunaAtualizacaoCadastral) it
							.next();
					
					// Pesquisando o objeto de TabelaColuna
					TabelaColuna tabelaColuna = tabelaColunaAtualizacaoCadastral.getTabelaColuna();
					System.out.println("tabela coluna:"+tabelaColuna.getColuna());
					if (tabelaColuna != null && tabelaColuna.getColuna() != null) {
						FiltroTabelaColuna filtroTabelaColuna = new FiltroTabelaColuna();
						filtroTabelaColuna.adicionarParametro(new ParametroSimples(FiltroTabelaColuna.TABELA_ID, tabelaAtualizacaoCadastral
								.getTabela().getId()));
						filtroTabelaColuna.adicionarParametro(new ParametroSimples(FiltroTabelaColuna.COLUNA, tabelaColuna.getColuna()));
						Collection collTabelaColuna = this.getControladorUtil().pesquisar(filtroTabelaColuna, TabelaColuna.class.getSimpleName());
						
						if (collTabelaColuna != null && !collTabelaColuna.isEmpty()) {
							tabelaColuna = (TabelaColuna) collTabelaColuna.iterator().next();
						}
						tabelaColunaAtualizacaoCadastral.setTabelaColuna(tabelaColuna);
					}
						
					tabelaColunaAtualizacaoCadastral
							.setTabelaAtualizacaoCadastral(tabelaAtualizacaoCadastral);
					tabelaColunaAtualizacaoCadastral.setUltimaAlteracao(new Date());
					getControladorUtil().inserir(tabelaColunaAtualizacaoCadastral);
				}
			}
		}
	}
	
	public Integer pesquisarUsuarioAlteracaoDasOperacoesEfetuadasHqlCount(
			Integer idUsuarioAcao,String[] idOperacoes, String idUsuario,
			Date dataInicial, Date dataFinal, Date horaInicial, Date horaFinal,
			Hashtable<String,String> argumentos, Integer id1, String unidadeNegocio)
			throws ControladorException {
		Integer retorno = null;
		try {
			retorno = (Integer) repositorioTransacao
					.pesquisarUsuarioAlteracaoDasOperacoesEfetuadasHqlCount(
							idUsuarioAcao, idOperacoes, idUsuario, dataInicial,
							dataFinal, horaInicial, horaFinal, argumentos, id1, unidadeNegocio);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		return retorno;

	}

	/**
	 * Registrar 
	 * @param objetoTransacao
	 * @throws ControladorException
	 */
	public void registrarTransacao(ObjetoTransacao objetoTransacao) 
		throws ControladorException {

		if (objetoTransacao.retornarAtributosSelecionadosRegistro() == null){
			sessionContext.setRollbackOnly();
			// Este método só deve ser chamado caso tenha sido definida 
			// a coleção de atributos selecionados para o registro da transacao
			throw new ControladorException("erro.sistema");			
		}
		Interceptador.getInstancia().verificarObjetoAlterado(objetoTransacao,
				objetoTransacao.retornarAtributosSelecionadosRegistro());	
	}
	

	/**
	 * Registrar transacao para um conjunto de atributos especificos
	 * 
	 * @param objetoTransacao
	 * @throws ControladorException
	 * @author Francisco do Nascimento
	 * @date 11/08/09
	 */
	public void registrarTransacao(ObjetoTransacao objetoTransacao, String[] atributos) 
		throws ControladorException {

		if (atributos == null){
			sessionContext.setRollbackOnly();
			// Este método só deve ser chamado caso tenha sido definida 
			// a coleção de atributos selecionados para o registro da transacao
			throw new ControladorException("erro.sistema");			
		}
		
		Interceptador.getInstancia().verificarObjetoAlterado(objetoTransacao,
				atributos);	
	}
	
	public HashMap consultarResumoInformacoesOperacaoEfetuada(OperacaoEfetuada operacaoEfetuada, int idItemAnalisado){

		
		FiltroOperacao filtroOperacao = new FiltroOperacao();
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade(
				FiltroOperacao.ARGUMENTO_PESQUISA);
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade(
				FiltroOperacao.ARGUMENTO_PESQUISA_TABELA);
		filtroOperacao.adicionarParametro(new ParametroSimples(FiltroOperacao.ID,
				operacaoEfetuada.getOperacao().getId()));
		
		Collection coll = Fachada.getInstancia().pesquisar(filtroOperacao,
				Operacao.class.getSimpleName());
		
		Operacao operacao = (Operacao) coll.iterator().next();
		// Consultando dados para o preenchimento das informações do item
		HashMap<String, Object> dados = new HashMap<String,Object>();

		try {
		
			// Tabela principal da operacao efetuada é considerada ser a tabela do argumento de pesquisa
			if (operacao.getArgumentoPesquisa() != null){
				String nomeTabela = operacao.getArgumentoPesquisa().getTabela().getNomeTabela();
				String nomeClasse = HibernateUtil.getClassName(nomeTabela);
				Object instancia = Class.forName(nomeClasse).newInstance();

				if (instancia instanceof ObjetoTransacao){ 
					ObjetoTransacao objTran = (ObjetoTransacao) instancia;
					objTran.set("id",Integer.class, idItemAnalisado);
					Filtro filtro = objTran.retornaFiltro();
					
					Collection objs = getControladorUtil().pesquisar(filtro, nomeClasse);
					
					if(objs.iterator().hasNext()){
						objTran = (ObjetoTransacao) objs.iterator().next();
						
						String[] atributos = objTran.retornarAtributosInformacoesOperacaoEfetuada();
						String[] labels = objTran.retornarLabelsInformacoesOperacaoEfetuada();
						
						for (int i = 0; i < atributos.length; i++) {
							dados.put(labels[i], objTran.get(atributos[i]));
						}					
					}
				}			
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ControladorException e) {
			e.printStackTrace();
		}	
		return dados;
	}
	
	public void ordenarTabelaLinhaColunaAlteracao(Collection linhas, int idOperacao) 
		throws ControladorException	{
		
		int[] idTCs = new int[linhas.size()];
		int i = 0;
		for (Iterator iter = linhas.iterator(); iter.hasNext();) {
			TabelaLinhaColunaAlteracao tlca = (TabelaLinhaColunaAlteracao) iter.next();
			idTCs[i++] = tlca.getTabelaColuna().getId();			
		}
		Collection ordem = null;
		try{
			ordem = repositorioTransacao.pesquisarOperacaoOrdemExibicao(idTCs, idOperacao);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
		final Map mapOrdem = new HashMap();
		Iterator iterOrdem = ordem.iterator(); 
		while (iterOrdem.hasNext()) {
			OperacaoOrdemExibicao opOrdem = (OperacaoOrdemExibicao) iterOrdem.next();
			mapOrdem.put(opOrdem.getTabelaColuna().getId(), opOrdem.getNumeroOrdem()); // idTabcoluna e ordem
		}
		
		// Ordenador pelo Id da tabela para agrupar os itens atualizados
		class ComparadorTLCA implements Comparator {
						
		    public int compare(Object obj1, Object obj2){
		    	TabelaLinhaColunaAlteracao tlca1 = (TabelaLinhaColunaAlteracao) obj1;
		    	TabelaLinhaColunaAlteracao tlca2 = (TabelaLinhaColunaAlteracao) obj2;
		    	if (obj1 instanceof TabelaLinhaColunaAlteracao && obj2 instanceof TabelaLinhaColunaAlteracao){
		    		
		    		int idTab1 = tlca1.getTabelaLinhaAlteracao().getId();
		    		int idTab2 = tlca2.getTabelaLinhaAlteracao().getId();
		    		
		    		int dif = idTab2 - idTab1;
		    		if (dif == 0){
			            Object ordem =  mapOrdem.get(tlca1.getTabelaColuna().getId());
			            int i1 = 999;
			            if (ordem != null){
			            	i1 = ((Integer) ordem).intValue();
			            }		    			
			            ordem =  mapOrdem.get(tlca2.getTabelaColuna().getId());
			            int i2 = 999;
			            if (ordem != null){
			            	i2 = ((Integer) ordem).intValue();
			            }
			            
			            dif = i1 - i2;
		    		}

		            return dif;
		    	} else {
		    		return 0;
		    	}
		    }
		}
		
		Collections.sort((List)linhas, new ComparadorTLCA());

	}

	/**
	 * Pesquisa a quantidade de registros na tabela de Operação Efetuada
	 * para os argumentos passados.
	 * 		
	 * @author Yara Taciane
	 * @date 15/07/2008
	 *
	 * @param idOperacao
	 * @param argumentoValor
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarOperacaoEfetuada(Integer idOperacao,
			Integer argumentoValor,Integer id2)throws ControladorException{
		Integer retorno = null;
		try {
			retorno =  repositorioTransacao
			.pesquisarOperacaoEfetuada(idOperacao,argumentoValor,id2);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	/**
	 * 
	 * Pesquisa os registros na TabelaLinhaColunaAlteracao para o argumento passado.
	 * 
	 * @author Yara Taciane
	 * @date 15/07/2008
	 *
	 * @param idTabelaColuna
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTabelaLinhaColunaAlteracao(Integer idObjetoAlterado,
			Integer idTabelaColuna)throws ControladorException{
		Integer retorno = null;
		try {
			retorno =  repositorioTransacao
			.pesquisarTabelaLinhaColunaAlteracao(idObjetoAlterado, idTabelaColuna);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	/**
	 * @author Ivan Sergio
	 * @date 03/06/2009
	 *
	 * @param idRegistroAlterado
	 * @param idArquivo
	 * @return
	 * @throws ControladorException
	 */
	public Collection<DadosTabelaAtualizacaoCadastralHelper> consultarDadosTabelaColunaAtualizacaoCadastral(
			Integer idRegistroAlterado,
			Integer idArquivo, Integer idImovel, Integer idCliente,Integer idTipoAlteracao)
		throws ControladorException {
		
		Collection<DadosTabelaAtualizacaoCadastralHelper> retorno = null;
		DadosTabelaAtualizacaoCadastralHelper helper = null;
		
		try {
			List listaDados = repositorioTransacao.consultarDadosTabelaColunaAtualizacaoCadastral(
					idRegistroAlterado, idArquivo, idImovel, idCliente,idTipoAlteracao);
			
			if (listaDados.size() > 0) {
				retorno = new ArrayList<DadosTabelaAtualizacaoCadastralHelper>();
				Object obj = null;
				Object[] dados = null;
				
				for (int i = 0; i < listaDados.size(); i++) {
					obj = listaDados.get(i);
					if (obj instanceof Object[]) {
						dados = (Object[]) obj;
						helper = new DadosTabelaAtualizacaoCadastralHelper();
						
						helper.setIdTabelaAtualizacaoCadastral((Integer) dados[0]); // Id da Tabela Atualizacao Cadastral
						helper.setIdTabela((Integer) dados[1]); // Id da Tabela
						helper.setDescricaoTabela((String) dados[2]); // Descricao da Tabela
						helper.setIdTabelaColuna((Integer) dados[3]); // Id da TabelaColuna
						helper.setDescricaoColuna((String) dados[4]); // Descricao da TabelaColuna
						helper.setIdTabelaColunaAtualizacaoCadastral((Integer) dados[5]); // Id da Tabela Coluna Atualizacao Cadastral
						if(dados[6] != null && !dados[6].equals("")){
							String campoAnterior = getDescricaoCampoAtualizacaoCadastral((String)dados[6], (String)dados[12]);
							if(campoAnterior != null){
								helper.setColunaValorAnterior(campoAnterior); // Valor Anterior da Coluna
							}else{
								helper.setColunaValorAnterior((String) dados[6]);// Valor Anterior da Coluna
							}
						}	
						if(dados[7] != null && !dados[7].equals("")){
							String campoAtual = getDescricaoCampoAtualizacaoCadastral((String)dados[7], (String)dados[12]);
							if(campoAtual != null){
								helper.setColunaValorAtual(campoAtual); // Valor Atual da Coluna
							}else{
								helper.setColunaValorAtual((String) dados[7]); // Valor Atual da Coluna
							}
						}
						helper.setIndicadorAutorizado((Short) dados[8]); // Indicador de Autorizado
						helper.setUltimaAtualizacao((Date) dados[9]); // Ultima Atualizacao
						helper.setIdAlteracaoTipo((Integer) dados[10]); // Id da Alteracao Tipo
						helper.setDescricaoAlteracaoTipo((String) dados[11]); // Descricao da Alteracao Tipo
						if(dados[13] != null){
							helper.setDataProcessamento((Date)dados[13]);
						}
						
						retorno.add(helper);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		return retorno;
	}
	
	private String getDescricaoCampoAtualizacaoCadastral(String campo, String coluna)
		throws ControladorException {
		String  retorno = null;
		if(coluna != null && !coluna.equals("")){
			if(coluna.equals("last_id")){
				FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
				filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoAguaSituacao.ID, campo));
				Collection pesquisa = getControladorUtil().pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
				if (pesquisa != null && !pesquisa.isEmpty()) {
					LigacaoAguaSituacao ligacaoAguaSituacao = (LigacaoAguaSituacao) Util.retonarObjetoDeColecao(pesquisa);
					retorno = ligacaoAguaSituacao.getDescricao();
				}
			}else if(coluna.equals("lest_id")){
				FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
				filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgotoSituacao.ID, campo));
				Collection pesquisa = getControladorUtil().pesquisar(filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName());
				if (pesquisa != null && !pesquisa.isEmpty()) {
					LigacaoEsgotoSituacao ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) Util.retonarObjetoDeColecao(pesquisa);
					retorno = ligacaoEsgotoSituacao.getDescricao();
				}				
			}else if(coluna.equals("pcal_id")){
				FiltroPavimentoCalcada filtroPavimentoCalcada = new FiltroPavimentoCalcada();
				filtroPavimentoCalcada.adicionarParametro(new ParametroSimples(FiltroPavimentoCalcada.ID, campo));
				Collection pesquisa = getControladorUtil().pesquisar(filtroPavimentoCalcada, PavimentoCalcada.class.getName());
				if (pesquisa != null && !pesquisa.isEmpty()) {
					PavimentoCalcada pavimentoCalcada = (PavimentoCalcada) Util.retonarObjetoDeColecao(pesquisa);
					retorno = pavimentoCalcada.getDescricao();				
				}
			}else if(coluna.equals("prua_id")){
				FiltroPavimentoRua filtroPavimentoRua = new FiltroPavimentoRua();
				filtroPavimentoRua.adicionarParametro(new ParametroSimples(FiltroPavimentoRua.ID, campo));
				Collection pesquisa = getControladorUtil().pesquisar(filtroPavimentoRua, PavimentoRua.class.getName());
				if (pesquisa != null && !pesquisa.isEmpty()) {
					PavimentoRua pavimentoRua = (PavimentoRua) Util.retonarObjetoDeColecao(pesquisa);
					retorno = pavimentoRua.getDescricao();				
				}				
			}else if(coluna.equals("ftab_id")){
				FiltroFonteAbastecimento filtroFonteAbastecimento = new FiltroFonteAbastecimento();
				filtroFonteAbastecimento.adicionarParametro(new ParametroSimples(FiltroFonteAbastecimento.ID, campo));
				Collection pesquisa = getControladorUtil().pesquisar(filtroFonteAbastecimento, FonteAbastecimento.class.getName());
				if (pesquisa != null && !pesquisa.isEmpty()) {
					FonteAbastecimento fonteAbastecimento = (FonteAbastecimento) Util.retonarObjetoDeColecao(pesquisa);
					retorno = fonteAbastecimento.getDescricao();				
				}					
			}else if(coluna.equals("crtp_id")){
				FiltroClienteRelacaoTipo filtroClienteRelacaoTipo = new FiltroClienteRelacaoTipo();
				filtroClienteRelacaoTipo.adicionarParametro(new ParametroSimples(FiltroClienteRelacaoTipo.CLIENTE_RELACAO_TIPO_ID, campo));
				Collection pesquisa = getControladorUtil().pesquisar(filtroClienteRelacaoTipo, ClienteRelacaoTipo.class.getName());
				if (pesquisa != null && !pesquisa.isEmpty()) {
					ClienteRelacaoTipo clienteRelacaoTipo = (ClienteRelacaoTipo) Util.retonarObjetoDeColecao(pesquisa);
					retorno = clienteRelacaoTipo.getDescricao();				
				}					
			}else if(coluna.equals("cltp_id")){
				FiltroClienteTipo filtroClienteTipo = new FiltroClienteTipo();
				filtroClienteTipo.adicionarParametro(new ParametroSimples(FiltroClienteTipo.ID, campo));
				Collection pesquisa = getControladorUtil().pesquisar(filtroClienteTipo, ClienteTipo.class.getName());
				if (pesquisa != null && !pesquisa.isEmpty()) {
					ClienteTipo clienteTipo = (ClienteTipo) Util.retonarObjetoDeColecao(pesquisa);
					retorno = clienteTipo.getDescricao();				
				}					
			}else if(coluna.equals("prof_id")){
				FiltroProfissao filtroProfissao = new FiltroProfissao();
				filtroProfissao.adicionarParametro(new ParametroSimples(FiltroProfissao.ID, campo));
				Collection pesquisa = getControladorUtil().pesquisar(filtroProfissao, Profissao.class.getName());
				if (pesquisa != null && !pesquisa.isEmpty()) {
					Profissao profissao = (Profissao) Util.retonarObjetoDeColecao(pesquisa);
					retorno = profissao.getDescricao();				
				}					
			}else if(coluna.equals("ratv_id")){
				FiltroRamoAtividade filtroRamoAtividade = new FiltroRamoAtividade();
				filtroRamoAtividade.adicionarParametro(new ParametroSimples(FiltroRamoAtividade.ID, campo));
				Collection pesquisa = getControladorUtil().pesquisar(filtroRamoAtividade, RamoAtividade.class.getName());
				if (pesquisa != null && !pesquisa.isEmpty()) {
					RamoAtividade ramoAtividade = (RamoAtividade) Util.retonarObjetoDeColecao(pesquisa);
					retorno = ramoAtividade.getDescricao();				
				}					
			}else if(coluna.equals("edtp_id")){
				FiltroEnderecoTipo filtroEnderecoTipo = new FiltroEnderecoTipo();
				filtroEnderecoTipo.adicionarParametro(new ParametroSimples(FiltroEnderecoTipo.ID, campo));
				Collection pesquisa = getControladorUtil().pesquisar(filtroEnderecoTipo, EnderecoTipo.class.getName());
				if (pesquisa != null && !pesquisa.isEmpty()) {
					EnderecoTipo enderecoTipo = (EnderecoTipo) Util.retonarObjetoDeColecao(pesquisa);
					retorno = enderecoTipo.getDescricao();				
				}					
			}else if(coluna.equals("edrf_id")){
				FiltroEnderecoReferencia filtroEnderecoReferencia = new FiltroEnderecoReferencia();
				filtroEnderecoReferencia.adicionarParametro(new ParametroSimples(FiltroEnderecoReferencia.ID, campo));
				Collection pesquisa = getControladorUtil().pesquisar(filtroEnderecoReferencia, EnderecoReferencia.class.getName());
				if (pesquisa != null && !pesquisa.isEmpty()) {
					EnderecoReferencia enderecoReferencia = (EnderecoReferencia) Util.retonarObjetoDeColecao(pesquisa);
					retorno = enderecoReferencia.getDescricao();				
				}					
			}else if(coluna.equals("fnet_id")){
				FiltroFoneTipo filtroFoneTipo = new FiltroFoneTipo();
				filtroFoneTipo.adicionarParametro(new ParametroSimples(FiltroFoneTipo.ID, campo));
				Collection pesquisa = getControladorUtil().pesquisar(filtroFoneTipo, FoneTipo.class.getName());
				if (pesquisa != null && !pesquisa.isEmpty()) {
					FoneTipo foneTipo = (FoneTipo) Util.retonarObjetoDeColecao(pesquisa);
					retorno = foneTipo.getDescricao();				
				}					
			}else if(coluna.equals("cocr_id")){
				FiltroCadastroOcorrencia filtroCadastroOcorrencia = new FiltroCadastroOcorrencia();
				filtroCadastroOcorrencia.adicionarParametro(new ParametroSimples(FiltroCadastroOcorrencia.ID, campo));
				Collection pesquisa = getControladorUtil().pesquisar(filtroCadastroOcorrencia, CadastroOcorrencia.class.getName());
				if (pesquisa != null && !pesquisa.isEmpty()) {
					CadastroOcorrencia cadastroOcorrencia = (CadastroOcorrencia) Util.retonarObjetoDeColecao(pesquisa);
					retorno = cadastroOcorrencia.getDescricao();				
				}					
			}					
		}
		return retorno;
	}

	/**
	 * Consultar Movimento Atualização Cadastral 
	 * 
	 * @author Ana Maria
	 * @date 02/05/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection<ConsultarMovimentoAtualizacaoCadastralHelper> pesquisarMovimentoAtualizacaoCadastral(String idArquivoTxt, 
		String idEmpresa, String idLeiturista, String exibirCampos, Collection colunaImoveisSelecionados,String matriculaImovel)throws ControladorException {

		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> retorno = null;

		try {
			retorno = repositorioTransacao.pesquisarMovimentoAtualizacaoCadastral(idArquivoTxt, idEmpresa, idLeiturista, exibirCampos, colunaImoveisSelecionados,matriculaImovel);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}
	
	/**
	 * @author Ivan Sergio
	 * @date 12/06/2009
	 *
	 * @param idAtualizacaoCadastral
	 * @param indicador
	 * @throws ControladorException
	 */
	public void atualizarIndicadorAutorizacaoColunaAtualizacaoCadastral(
			String[] idsAtualizacaoCadastral,
			Short indicador, Usuario usuarioLogado, String idImovelAtualizacaoCadastral) throws ControladorException {
		try {
			
			Integer tipoAlteracaoCadastral = null;
			
			// Varre a lista para recuperar os ids SIM selecionados
			for (int i = 0; i < idsAtualizacaoCadastral.length; i++) {
				Integer idAtualizacaoCadastral = new Integer(idsAtualizacaoCadastral[i]);
				// atualiza o indicador de autorizado para sim 
				this.repositorioTransacao.atualizarIndicadorAutorizacaoColunaAtualizacaoCadastral(idAtualizacaoCadastral, indicador);
				//verifica qual o tipo de atualização cadastral
				if(tipoAlteracaoCadastral == null){
					TabelaColunaAtualizacaoCadastral tabelaColunaAtualizacaoCadastral = this.repositorioTransacao.pesquisarTabelaColunaAtualizacaoCadastral(idAtualizacaoCadastral);
					tipoAlteracaoCadastral = tabelaColunaAtualizacaoCadastral.getTabelaAtualizacaoCadastral().getAlteracaoTipo().getId();
				}
			}
							
			// caso o indicador seja para autorizar a atualização vinda do celular
			if(indicador != null && indicador.equals(ConstantesSistema.SIM)){			
				//Caso a opção seja ALTERAÇÃO
				if(tipoAlteracaoCadastral.equals(AlteracaoTipo.ALTERACAO)){
					
					alteracaoImovelClienteAtualizacaoCadastral(idsAtualizacaoCadastral,usuarioLogado);
					
				}else{
					//Caso contrário, caso a opção seja de INCLUSÃO
					if(tipoAlteracaoCadastral.equals(AlteracaoTipo.INCLUSAO)){
						if(idImovelAtualizacaoCadastral != null && !idImovelAtualizacaoCadastral.equals("")){
							Integer idImovel = this.inserirImovelDoArquivoAtualizacaoCadastral(idImovelAtualizacaoCadastral,usuarioLogado);
							
							this.inserirClienteDoArquivoAtualizacaoCadastral(idImovelAtualizacaoCadastral, idImovel, usuarioLogado);
							this.inserirImovelSubcategoriaDoArquivoAtualizacaoCadastral(idImovelAtualizacaoCadastral, idImovel, usuarioLogado);
							this.inserirLigacaoAguaEsgotoAtualizacaoCadastral(idImovelAtualizacaoCadastral, idImovel,usuarioLogado);
							
							FiltroImovelAtualizacaoCadastral filtroImovelAtualizacaoCadastral = new FiltroImovelAtualizacaoCadastral();
							filtroImovelAtualizacaoCadastral.adicionarParametro(new ParametroSimples(
									FiltroImovelAtualizacaoCadastral.ID, idImovelAtualizacaoCadastral));
							
							Collection colImovelAtlzCadastral = getControladorUtil().pesquisar(
									filtroImovelAtualizacaoCadastral, ImovelAtualizacaoCadastral.class.getName());
							if(!Util.isVazioOrNulo(colImovelAtlzCadastral)){
								ImovelAtualizacaoCadastral imovelAtualizacaoCadastral = (ImovelAtualizacaoCadastral)
											Util.retonarObjetoDeColecao(colImovelAtlzCadastral);
								
								imovelAtualizacaoCadastral.setIndicadorAtualizado(ConstantesSistema.SIM);
								
								getControladorUtil().atualizar(imovelAtualizacaoCadastral);
							}
							
						}else{
							inclusaoImovelClienteAtualizacaoCadastral(idsAtualizacaoCadastral,usuarioLogado);
						}
						
					}else{
						remocaoImovelClienteAtualizacaoCadastral(idsAtualizacaoCadastral,usuarioLogado);
					}
				}
			}
		}catch (ControladorException ce) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ce);
		}catch (ErroRepositorioException er) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", er);
		}catch (ActionServletException e){
			sessionContext.setRollbackOnly();
			throw new ControladorException(e.getMessage());
		}
	}
	
	/**
	 * @author Ana Maria
	 * @date 16/06/2009
	 *
	 * @param idArgumento
	 * @param indicador
	 * @throws ErroRepositorioException
	 */
	public void atualizarIndicadorAutorizacaoTabelaAtualizacaoCadastral(
			Integer idArgumento, Short indicador)throws ControladorException {
		try {
			this.repositorioTransacao.atualizarIndicadorAutorizacaoTabelaAtualizacaoCadastral(idArgumento, indicador);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}		
	}
	
	/**
	 * @author Ana Maria
	 * @date 25/06/2009
	 *
	 * @param idEmpresa
	 * @param idArquivo
	 * @param idLeiturista
	 * @return List
	 * @throws ErroRepositorioException
	 */
	public void pesquisarRegistroAutorizadoTabelaAtualizacaoCadastral(
			String idEmpresa, String idArquivo, String idLeiturista) throws ControladorException {
		
		try {
			List listaRegistros = repositorioTransacao.pesquisarRegistroAutorizadoTabelaAtualizacaoCadastral(idEmpresa, idArquivo, idLeiturista);
			
			if (listaRegistros.size() > 0) {
				Object obj = null;
				Object[] dados = null;
				
				for (int i = 0; i < listaRegistros.size(); i++) {
					obj = listaRegistros.get(i);
					if (obj instanceof Object[]) {
						dados = (Object[]) obj;
						if(dados[1].equals(Tabela.IMOVEL_ATUALIZACAO_CADASTRAL)){
							 
						}else if(dados[1].equals(Tabela.CLIENTE_ATUALIZACAO_CADASTRAL)){
							List listaColuna = repositorioTransacao.pesquisarRegistroAutorizadoTabelaColunaAtualizacaoCadastral((Integer) dados[2]);
							if(listaColuna.size() > 0){

								Iterator listaColunaIter = listaColuna.iterator();
								while (listaColunaIter.hasNext()) {
									Object[] coluna = (Object[]) listaColunaIter.next();
									if(coluna[0].equals("clac_nnimovel")){
										
									}
								}
							}
						}else if(dados[1].equals(Tabela.CLIENTE_FONE_ATUALIZACAO_CADASTRAL)){
							List listaColuna = repositorioTransacao.pesquisarRegistroAutorizadoTabelaColunaAtualizacaoCadastral((Integer) dados[2]);
							if(listaColuna.size() > 0){
								ClienteFone clienteFone = new ClienteFone();
								Iterator listaColunaIter = listaColuna.iterator();
								while (listaColunaIter.hasNext()) {
									Object[] coluna = (Object[]) listaColunaIter.next();
									
									if(dados[3].equals(AlteracaoTipo.ALTERACAO)){
										
										
									}else if(dados[3].equals(AlteracaoTipo.INCLUSAO)){
										Cliente cliente = new Cliente();
										cliente.setId((Integer)dados[0]);
										clienteFone.setCliente(cliente);
										if(coluna[0].equals("fnet_id")){
											FoneTipo foneTipo = new FoneTipo();
											foneTipo.setId((Integer)coluna[1]);
											clienteFone.setFoneTipo(foneTipo);	
										}
										if(coluna[0].equals("cfac_cdddd")){
											FoneTipo foneTipo = new FoneTipo();
											foneTipo.setId((Integer)coluna[1]);
											clienteFone.setFoneTipo(foneTipo);	
										}
										if(coluna[0].equals("cfac_nnfone")){
											FoneTipo foneTipo = new FoneTipo();
											foneTipo.setId((Integer)coluna[1]);
											clienteFone.setFoneTipo(foneTipo);	
										}
										if(coluna[0].equals("cfac_nnfoneramal")){
											FoneTipo foneTipo = new FoneTipo();
											foneTipo.setId((Integer)coluna[1]);
											clienteFone.setFoneTipo(foneTipo);	
										}
									}									
								}
							}
							
						}else if(dados[1].equals(Tabela.IMOVEL_SUBCATEGORIA_ATUALIZACAO_CADASTRAL)){
							
						}
					}
				}
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}		
	}
	
	/**
	 * @author Genival barbosa
	 * @date 27/07/2009
	 *
	 * @param idAtualizacaoCadastral
	 * @param indicador
	 * @throws ControladorException
	 */
	public void atualizarIndicadorAutorizacaoAtualizacaoCadastral(
			Integer idAtualizacaoCadastral,
			Short indicador) throws ControladorException {
		try {
			this.repositorioTransacao.atualizarIndicadorAutorizacaoAtualizacaoCadastral(idAtualizacaoCadastral, indicador);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}		
	}
	
	/**
	 * CRC2103 - [FS0026] - Verificar existencia de operacao inserir/manter cliente pendente de atualizacao do imovel. 
	 *
	 * @author Ivan Sergio
	 * @date 24/07/2009
	 *
	 * @param idImovel
	 * @param colecaoClientes
	 * @throws ControladorException
	 */
	public void verificarAtualizarOperacaoPendente(
			Integer idImovel,
			Collection colecaoClientes,
			Integer idUsuario) throws ControladorException {

		Integer idGrupoAtributo = null;
		
		try {
			if (colecaoClientes != null && !colecaoClientes.isEmpty()) {
				Iterator iColecaoCliente = colecaoClientes.iterator();
				while (iColecaoCliente.hasNext()) {
					ClienteImovel clienteImovel = (ClienteImovel) iColecaoCliente.next();
					Integer idOperacaoEfetuada = (Integer) repositorioTransacao.verificarOperacaoPendente(
							clienteImovel.getCliente().getId(),
							idUsuario);
					
					if (idOperacaoEfetuada != null) {
						if (clienteImovel.getClienteRelacaoTipo().getId().equals(new Integer(ClienteRelacaoTipo.PROPRIETARIO))) {
							idGrupoAtributo = AtributoGrupo.ATRIBUTOS_DO_PROPRIETARIO;
						}else if (clienteImovel.getClienteRelacaoTipo().getId().equals(new Integer(ClienteRelacaoTipo.USUARIO))) {
							idGrupoAtributo = AtributoGrupo.ATRIBUTOS_DO_USUARIO;
						}else {
							idGrupoAtributo = null;
						}
								
						repositorioTransacao.atualizarOperacaoEfetuadaPendente(idOperacaoEfetuada, idGrupoAtributo);
						repositorioTransacao.atualizarTabelaLinhaAlteracaoPendente(idOperacaoEfetuada, idImovel);
					}
				}
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	/**
	 * @author Ana Maria
	 * @date 17/12/2009
	 *
	 * @param codigoImovel
	 * @param codigoCliente
	 * @throws ErroRepositorioException
	 */
	
	public void atualizarClienteRelacaoTipoAtualizacaoCadastral(Integer codigoImovel, Integer codigoCliente) 
		throws ControladorException {
		try{
			this.repositorioTransacao.atualizarClienteRelacaoTipoAtualizacaoCadastral(codigoImovel, codigoCliente);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	private boolean validarCPFouCNPJALterado(Cliente cliente,ClienteTipo clienteTipoAlterado,String valorAtualCPFouCNPJ) throws ControladorException{
		boolean pessoaFisica = true;
		if(clienteTipoAlterado != null){
			if(clienteTipoAlterado.getIndicadorPessoaFisicaJuridica().equals(ClienteTipo.INDICADOR_PESSOA_FISICA)){
				if(valorAtualCPFouCNPJ != null){
					boolean validarCPF = Util.validacaoCPF(valorAtualCPFouCNPJ);
					if(!validarCPF){
						sessionContext.setRollbackOnly();
						throw new ControladorException("atencao.cpf.invalido", null,valorAtualCPFouCNPJ);
					}
				}
				pessoaFisica = true;
			}else{
				if(valorAtualCPFouCNPJ != null){
					boolean validarCNPJ = Util.validacaoCNPJ(valorAtualCPFouCNPJ);
					if(!validarCNPJ){
						sessionContext.setRollbackOnly();
						throw new ControladorException("atencao.cnpj.invalido", null,valorAtualCPFouCNPJ);
					}
				}
				pessoaFisica = false;
			}
		}else{
		
			if(cliente.getClienteTipo() != null && cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica().equals(ClienteTipo.INDICADOR_PESSOA_FISICA)){
				
				if(valorAtualCPFouCNPJ != null){
					boolean validarCPF = Util.validacaoCPF(valorAtualCPFouCNPJ);
					if(!validarCPF){
						sessionContext.setRollbackOnly();
						throw new ControladorException("atencao.cpf.invalido", null,valorAtualCPFouCNPJ);
					}
				}
				pessoaFisica = true;
			}else{
				if(valorAtualCPFouCNPJ != null){
					boolean validarCNPJ = Util.validacaoCNPJ(valorAtualCPFouCNPJ);
					if(!validarCNPJ){
						sessionContext.setRollbackOnly();
						throw new ControladorException("atencao.cnpj.invalido", null,valorAtualCPFouCNPJ);
					}
				}
				pessoaFisica = false;
			}
		}
		return pessoaFisica;
	}
	
	private Cliente pesquisarClienteCadastradoPeloCPFouCNPJ(Cliente clienteAtualizar,ClienteTipo clienteTipoAlterado,String numeroCPFouCNPJAlterado)  throws ControladorException{
		Cliente clienteCadastrado = null;
		try{

				if(clienteTipoAlterado != null){
					if(clienteTipoAlterado.getIndicadorPessoaFisicaJuridica().equals(ClienteTipo.INDICADOR_PESSOA_FISICA)){
						if(numeroCPFouCNPJAlterado != null){
						    clienteCadastrado = this.repositorioTransacao.obterClientePeloCPF(numeroCPFouCNPJAlterado,clienteAtualizar.getId());
						}
					}else{
						if(numeroCPFouCNPJAlterado != null){
							clienteCadastrado = this.repositorioTransacao.obterClientePeloCNPJ(numeroCPFouCNPJAlterado,clienteAtualizar.getId());
						}
					}
				}else{
				
					if(clienteAtualizar.getClienteTipo() != null && clienteAtualizar.getClienteTipo().getIndicadorPessoaFisicaJuridica().equals(ClienteTipo.INDICADOR_PESSOA_FISICA)){
						if(numeroCPFouCNPJAlterado != null){
						    clienteCadastrado = this.repositorioTransacao.obterClientePeloCPF(numeroCPFouCNPJAlterado,clienteAtualizar.getId());
						}
					}else{
						if(numeroCPFouCNPJAlterado != null){
							clienteCadastrado = this.repositorioTransacao.obterClientePeloCNPJ(numeroCPFouCNPJAlterado,clienteAtualizar.getId());
						}
					}
				}
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}	
		
		return clienteCadastrado;
	}
	
	
	/**
	 * [[UC1165] - Confirmar Alterações Cadastrais
	 * 
	 * @author Sávio Luiz
	 * @date 18/05/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	private void alteracaoImovelClienteAtualizacaoCadastral(String[] idsAtualizacaoCadastral,Usuario usuarioLogado) throws ControladorException, ActionServletException{
		
		boolean alterouCliente = false;
		boolean alterouImovel = false;
		boolean alterouClienteEndereco = false;
		boolean alterouNomeOuCPFOuCNPFCliente = false;
		Cliente cliente = null;
		Imovel imovel = null;
		ClienteEndereco clienteEndereco = null;
		RegistradorOperacao registradorOperacaoImovel = null;
        RegistradorOperacao registradorOperacaoCliente = null;
        RegistradorOperacao registradorOperacaoClienteNovo = null;
        Cliente clienteNovo = null;
        ClienteEndereco clienteEnderecoNovo = null;
        RegistradorOperacao registradorOperacaoClienteMesmoNomeCPF = null;
        Cliente clienteMesmoNomeCPF = null;
        ClienteEndereco clienteEnderecoMesmoNomeCPF = null;
        TabelaAtualizacaoCadastral tacClienteAtualizadoFone = null;
        Integer indicadorValidarCpfCnpj = null;
        
		//	Varre a lista para recuperar os ids SIM selecionados
		for (int i = 0; i < idsAtualizacaoCadastral.length; i++) {
			Integer idAtualizacaoCadastral = new Integer(idsAtualizacaoCadastral[i]);
			
			try{
			
			    // [UC-1165] - Confirmar Alterações Cadastrais
				// Autor: Sávio Luiz
				TabelaColunaAtualizacaoCadastral tabelaColunaAtualizacaoCadastral = this.repositorioTransacao.pesquisarTabelaColunaAtualizacaoCadastral(idAtualizacaoCadastral);
		
				if(tabelaColunaAtualizacaoCadastral != null && !tabelaColunaAtualizacaoCadastral.equals("")){
					
					//recupera o imóvel que será alterado
					if(imovel == null && tabelaColunaAtualizacaoCadastral.getTabelaAtualizacaoCadastral().getCodigoImovel() != null){
						imovel = getControladorImovel().pesquisarImovel(tabelaColunaAtualizacaoCadastral.getTabelaAtualizacaoCadastral().getCodigoImovel());
						
						//------------ <REGISTRAR TRANSAÇÃO>----------------------------
						registradorOperacaoImovel = new RegistradorOperacao(
								Operacao.OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL, imovel.getId(),
								imovel.getId(),
								new UsuarioAcaoUsuarioHelper(usuarioLogado,
										UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
						
						this.registrarTransacao(imovel);
						
					}
					// recupera o cliente que será alterado
					if(cliente == null && tabelaColunaAtualizacaoCadastral.getTabelaAtualizacaoCadastral().getCodigoCliente() != null){
						FiltroCliente filtroCliente = new FiltroCliente();
						filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,tabelaColunaAtualizacaoCadastral.getTabelaAtualizacaoCadastral().getCodigoCliente()));
						filtroCliente.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");
						filtroCliente.adicionarCaminhoParaCarregamentoEntidade("ramoAtividade");
						filtroCliente.adicionarCaminhoParaCarregamentoEntidade("profissao");
						filtroCliente.adicionarCaminhoParaCarregamentoEntidade("pessoaSexo");
						Collection colecaoCliente = getControladorUtil().pesquisar(filtroCliente,Cliente.class.getName());
						cliente = (Cliente) Util.retonarObjetoDeColecao(colecaoCliente);
						
						//recupera o cliente endereço do cliente que está sendo alterado
						FiltroClienteEndereco filtroClienteEndereco = new FiltroClienteEndereco();
						filtroClienteEndereco.adicionarParametro(new ParametroSimples(FiltroClienteEndereco.CLIENTE_ID,cliente.getId()));
						filtroClienteEndereco.adicionarParametro(new ParametroSimples(FiltroClienteEndereco.INDICADOR_CORRESPONDENCIA,ConstantesSistema.SIM));
						Collection colecaoClienteEndereco = getControladorUtil().pesquisar(filtroClienteEndereco,ClienteEndereco.class.getName());
						clienteEndereco = (ClienteEndereco) Util.retonarObjetoDeColecao(colecaoClienteEndereco);
						
						tacClienteAtualizadoFone = this.repositorioTransacao.obterIdTabelaAtualizacaoCadastralPorCliente(cliente.getId(),TabelaColuna.NUMERO_FONE_CLIENTE_TELEFONE);
						
						
						
						//------------ <REGISTRAR TRANSAÇÃO>----------------------------
						if(cliente != null && !cliente.equals("")){
							registradorOperacaoCliente = new RegistradorOperacao(
									Operacao.OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL, imovel.getId(),
									cliente.getId(),
									new UsuarioAcaoUsuarioHelper(usuarioLogado,
											UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
							
							this.registrarTransacao(cliente);
						}
					}
					
					if(cliente == null || cliente.equals("")){
						 //pesquisa o cliente atualizacao pelo imóvel e relação
						 ClienteAtualizacaoCadastral clienteAtualizacaoCadastralAux = getControladorCliente()
								.pesquisarClienteAtualizacaoCadastral(null, tabelaColunaAtualizacaoCadastral.getTabelaAtualizacaoCadastral().getCodigoImovel(), Integer.parseInt(""+ClienteRelacaoTipo.USUARIO));
						 
						 //seta o id do cliente cadastrado com o imóvel para ficar esse cliente com o valor anterior 
						 if(clienteAtualizacaoCadastralAux != null && !clienteAtualizacaoCadastralAux.equals("")){
							 FiltroCliente filtroCliente = new FiltroCliente();
								filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,clienteAtualizacaoCadastralAux.getIdCliente()));
								filtroCliente.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");
								filtroCliente.adicionarCaminhoParaCarregamentoEntidade("ramoAtividade");
								filtroCliente.adicionarCaminhoParaCarregamentoEntidade("profissao");
								Collection colecaoCliente = getControladorUtil().pesquisar(filtroCliente,Cliente.class.getName());
								cliente = (Cliente) Util.retonarObjetoDeColecao(colecaoCliente);
						 }
						
					}
					
					
					// verifica so o nome do cliente foi alterado
					String nomeClienteAlterado = this.repositorioTransacao.obterValorAtualTabelaColunaAtualizacaoCadastral(tabelaColunaAtualizacaoCadastral.getTabelaAtualizacaoCadastral().getId(),TabelaColuna.NOME_CLIENTE_ATU_CADASTRAL);
					
					// verifica so o cpf ou cnpj foi alterado
					String numeroCPFouCNPJAlterado = this.repositorioTransacao.obterValorAtualTabelaColunaAtualizacaoCadastral(tabelaColunaAtualizacaoCadastral.getTabelaAtualizacaoCadastral().getId(),TabelaColuna.CPF_CNPJ_CLIENTE_ATU_CADASTRAL);
						
					// Caso o campo alterado seja nome ou CPF ou CNPJ do cliente
					// e ainda não tenha sido alterado
					if((nomeClienteAlterado != null || numeroCPFouCNPJAlterado != null) && !alterouNomeOuCPFOuCNPFCliente){
						
						// verifica se o tipo do cliente foi alterado
						ClienteTipo clienteTipoAlterado = null;
						String idClienteTipoAlterado = this.repositorioTransacao.obterValorAtualTabelaColunaAtualizacaoCadastral(tabelaColunaAtualizacaoCadastral.getTabelaAtualizacaoCadastral().getId(),TabelaColuna.CLIENTE_TIPO_CLIENTE_ATU_CADASTRAL);
						if(idClienteTipoAlterado != null && !idClienteTipoAlterado.equals("")){
							FiltroClienteTipo filtroClienteTipo = new FiltroClienteTipo();
							filtroClienteTipo.adicionarParametro(new ParametroSimples(FiltroClienteTipo.ID,new Integer(idClienteTipoAlterado)));
							Collection colecaoClienteTipo = getControladorUtil().pesquisar(filtroClienteTipo,ClienteTipo.class.getName());
							clienteTipoAlterado = (ClienteTipo)Util.retonarObjetoDeColecao(colecaoClienteTipo);
						}
						
						// 1.2.2 verificar o se o CPF ou CNPJ alterado já existe
						Cliente clienteCadastrado = pesquisarClienteCadastradoPeloCPFouCNPJ(cliente,clienteTipoAlterado,numeroCPFouCNPJAlterado);
						
						//Verifica qual a relação entre o cliente e o imóvel
						ClienteRelacaoTipo clienteRelacaoTipo = this.repositorioTransacao.recuperaTipoRelacaoClienteAtualizacaoCadastral(tabelaColunaAtualizacaoCadastral.getTabelaAtualizacaoCadastral().getId());
						
						// valida o CPF ou CNPJ do cliente
						boolean pessoaFisica = validarCPFouCNPJALterado(cliente,clienteTipoAlterado,numeroCPFouCNPJAlterado);
						
						// Caso esse nome tenha sido alterado,
						// então o nome para a comparação será esse nome alterado
						if(nomeClienteAlterado != null){
							// Finalizar a relação entre o imóvel e o cliente antigo
							ClienteImovel clienteImovelFimRelacao = null;
							FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
							filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");
							filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID,imovel.getId()));
							
							if(clienteRelacaoTipo != null){
								filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO_ID,clienteRelacaoTipo.getId()));
							}
							
							filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
							Collection colecaoClienteImovel = getControladorUtil().pesquisar(filtroClienteImovel,ClienteImovel.class.getName());
							if(colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()){
								clienteImovelFimRelacao = (ClienteImovel) Util.retonarObjetoDeColecao(colecaoClienteImovel);
								clienteImovelFimRelacao.setDataFimRelacao(new Date());
								ClienteImovelFimRelacaoMotivo clienteImovelFimRelacaoMotivo = new ClienteImovelFimRelacaoMotivo();
								clienteImovelFimRelacaoMotivo.setId(ClienteImovelFimRelacaoMotivo.POR_ATU_CADASTRAL);
								clienteImovelFimRelacao.setClienteImovelFimRelacaoMotivo(clienteImovelFimRelacaoMotivo);
								registradorOperacaoImovel.registrarOperacao(clienteImovelFimRelacao);
								//atualiza o cliente imóvel usuário com o data do fim da relação e o motivo 
								getControladorUtil().atualizar(clienteImovelFimRelacao);
								
								if(clienteRelacaoTipo == null){
									clienteRelacaoTipo = clienteImovelFimRelacao.getClienteRelacaoTipo();
								}
							}
							
							// cria uma nova relação entre o imóvel e o cliente cadastrado ou um novo
							// cliente caso o nome dos clientes não sejam o mesmo
							ClienteImovel clienteImovelNovaRelacao = new ClienteImovel();
							clienteImovelNovaRelacao.setImovel(imovel);
							// caso mesmo nome, então a nova relação será com o cliente já cadastrado
							if(clienteCadastrado != null && clienteCadastrado.getNome().equals(nomeClienteAlterado)){	
								clienteImovelNovaRelacao.setCliente(clienteCadastrado);
								//usa esse cliente para atualizar outros campos que foram atualizados na atualização cadastral
								registradorOperacaoClienteMesmoNomeCPF = new RegistradorOperacao(
										Operacao.OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL, imovel.getId(),
										clienteCadastrado.getId(),
										new UsuarioAcaoUsuarioHelper(usuarioLogado,
												UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
								
								this.registrarTransacao(clienteCadastrado);
								clienteMesmoNomeCPF = clienteCadastrado;
								
								//se o telefone foi alterado para o cliente que estava sendo atualizado,
								//o telefone será atualizado para o nome cliente
								if(tacClienteAtualizadoFone != null){
									tacClienteAtualizadoFone.setCodigoCliente(clienteMesmoNomeCPF.getId());
									getControladorUtil().atualizar(tacClienteAtualizadoFone);
								}
								
								// recupera o cliente endereço do cliente que está sendo alterado
								FiltroClienteEndereco filtroClienteEndereco = new FiltroClienteEndereco();
								filtroClienteEndereco.adicionarParametro(new ParametroSimples(FiltroClienteEndereco.CLIENTE_ID,clienteMesmoNomeCPF.getId()));
								filtroClienteEndereco.adicionarParametro(new ParametroSimples(FiltroClienteEndereco.INDICADOR_CORRESPONDENCIA,ConstantesSistema.SIM));
								Collection colecaoClienteEndereco = getControladorUtil().pesquisar(filtroClienteEndereco,ClienteEndereco.class.getName());
								clienteEnderecoMesmoNomeCPF = (ClienteEndereco) Util.retonarObjetoDeColecao(colecaoClienteEndereco);
								
							}else{
								// caso não seja o mesmo nome, então cria um novo cliente
								// com as caracteristicas do cliente que está sendo alterado
								clienteNovo = new Cliente(cliente);
								if(nomeClienteAlterado != null){
								  clienteNovo.setNome(nomeClienteAlterado);
								}
								if(numeroCPFouCNPJAlterado != null){
									indicadorValidarCpfCnpj = getControladorCliente().pesquisarIndicadorValidarCpfCnpjCliente(cliente.getId());
									
									if(indicadorValidarCpfCnpj != null && indicadorValidarCpfCnpj.equals(ConstantesSistema.SIM.intValue())){
										throw new ActionServletException("atencao.cpf_cnpj_validado");
									}
									
									if(pessoaFisica){
										clienteNovo.setCpf(numeroCPFouCNPJAlterado);
										clienteNovo.setIndicadorValidaCpfCnpj(ConstantesSistema.SIM.intValue());
									}else{
										clienteNovo.setCnpj(numeroCPFouCNPJAlterado);
										clienteNovo.setIndicadorValidaCpfCnpj(ConstantesSistema.SIM.intValue());
									}
									
									
									
									if(clienteCadastrado != null){
										// Limpar CPF ou CNPJ do cliente cadastrado
										// e regsitra a transação desse cliente
										if(pessoaFisica){
											clienteCadastrado.setCpf(null);
											clienteCadastrado.setUltimaAlteracao(new Date());
											
										}else{
											clienteCadastrado.setCnpj(null);
											clienteCadastrado.setUltimaAlteracao(new Date());
										}
										RegistradorOperacao registradorOperacao = new RegistradorOperacao(
												Operacao.OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL, imovel.getId(),
												clienteCadastrado.getId(),
												new UsuarioAcaoUsuarioHelper(usuarioLogado,
														UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
										registradorOperacao.registrarOperacao(clienteCadastrado);
										this.registrarTransacao(clienteCadastrado);
										getControladorUtil().atualizar(clienteCadastrado);
									}
									
								}else{
									//caso o CPF ou CNPJ do cliente seja diferente de nulo
									if((cliente.getCpf() != null && !cliente.getCpf().trim().equals("")) ||
											(cliente.getCnpj() != null && cliente.getCnpj().trim().equals(""))){
										
										if(pessoaFisica){
											cliente.setCpf(null);
											cliente.setUltimaAlteracao(new Date());
											
										}else{
											cliente.setCnpj(null);
											cliente.setUltimaAlteracao(new Date());
										}
										registradorOperacaoCliente.registrarOperacao(cliente);
										
									}
									
									
								}
								
								registradorOperacaoClienteNovo = new RegistradorOperacao(
										Operacao.OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL, imovel.getId(),
										clienteNovo.getId(),
										new UsuarioAcaoUsuarioHelper(usuarioLogado,
												UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
								
								registradorOperacaoClienteNovo.registrarOperacao(clienteNovo);
								
								clienteNovo.setIndicadorEnviarSms(ConstantesSistema.NAO);
								clienteNovo.setIndicadorEnviarEmail(ConstantesSistema.NAO);
								
								Integer idClienteNovo = (Integer)getControladorUtil().inserir(clienteNovo);
								clienteNovo.setId(idClienteNovo);
								
								clienteEnderecoNovo = new ClienteEndereco(clienteEndereco);
								clienteEnderecoNovo.setCliente(clienteNovo);
								clienteEnderecoNovo.setUltimaAlteracao(new Date());
								
								registradorOperacaoClienteNovo.registrarOperacao(clienteEnderecoNovo);
								
								Integer idClienteEnderecoNovo = (Integer)getControladorUtil().inserir(clienteEnderecoNovo);
								clienteEnderecoNovo.setId(idClienteEnderecoNovo);

								clienteImovelNovaRelacao.setCliente(clienteNovo);
								//alterouCliente = true;
								//alterouClienteEndereco = true;
								
								//se o telefone foi alterado para o cliente que estava sendo atualizado,
								//o telefone será atualizado para o nome cliente
								if(tacClienteAtualizadoFone != null){
									tacClienteAtualizadoFone.setCodigoCliente(clienteNovo.getId());
									getControladorUtil().atualizar(tacClienteAtualizadoFone);
								}
							}
							
							clienteImovelNovaRelacao.setDataInicioRelacao(new Date());
							clienteImovelNovaRelacao.setClienteRelacaoTipo(clienteRelacaoTipo != null ? clienteRelacaoTipo : new ClienteRelacaoTipo(ClienteRelacaoTipo.USUARIO.intValue()));
							clienteImovelNovaRelacao.setIndicadorNomeConta(clienteImovelFimRelacao != null ? clienteImovelFimRelacao.getIndicadorNomeConta():ConstantesSistema.NAO);
							clienteImovelNovaRelacao.setUltimaAlteracao(new Date());
							registradorOperacaoImovel.registrarOperacao(clienteImovelNovaRelacao);
							getControladorUtil().inserir(clienteImovelNovaRelacao);
						}else{
							//Caso o nome não tenha sido alterado (nesse caso o CPF ou CNPJ do cliente foi alterado)
							// Verifica se o cliente cadastrado tem o mesmo nome
							if(clienteCadastrado != null && clienteCadastrado.getNome().equals(cliente.getNome())){
								
								indicadorValidarCpfCnpj = getControladorCliente().pesquisarIndicadorValidarCpfCnpjCliente(clienteCadastrado.getId());
								
								if(indicadorValidarCpfCnpj != null && indicadorValidarCpfCnpj.equals(ConstantesSistema.SIM.intValue())){
									throw new ActionServletException("atencao.cpf_cnpj_validado");
								}
								
								// Finalizar a relação entre o imóvel e o cliente antigo
								ClienteImovel clienteImovelFimRelacao = null;
								FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
								filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID,imovel.getId()));
								
								if(clienteRelacaoTipo != null){
									filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO_ID,clienteRelacaoTipo.getId()));
								}
								
								filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_ID,cliente.getId()));
								filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
								filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.CLIENTE_RELACAO_TIPO);
								Collection colecaoClienteImovel = getControladorUtil().pesquisar(filtroClienteImovel,ClienteImovel.class.getName());
								if(colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()){
									clienteImovelFimRelacao = (ClienteImovel) Util.retonarObjetoDeColecao(colecaoClienteImovel);
									clienteImovelFimRelacao.setDataFimRelacao(new Date());
									ClienteImovelFimRelacaoMotivo clienteImovelFimRelacaoMotivo = new ClienteImovelFimRelacaoMotivo();
									clienteImovelFimRelacaoMotivo.setId(ClienteImovelFimRelacaoMotivo.POR_ATU_CADASTRAL);
									clienteImovelFimRelacao.setClienteImovelFimRelacaoMotivo(clienteImovelFimRelacaoMotivo);
									registradorOperacaoImovel.registrarOperacao(clienteImovelFimRelacao);
									//atualiza o cliente imóvel usuário com o data do fim da relação e o motivo 
									getControladorUtil().atualizar(clienteImovelFimRelacao);
									
									if(clienteRelacaoTipo == null){
										clienteRelacaoTipo = clienteImovelFimRelacao.getClienteRelacaoTipo();
									}
								
									// cria uma nova relação entre o imóvel e o cliente cadastrado ou um novo
									// cliente caso o nome dos clientes não sejam o mesmo
									ClienteImovel clienteImovelNovaRelacao = new ClienteImovel();
									clienteImovelNovaRelacao.setImovel(imovel);
									clienteImovelNovaRelacao.setCliente(clienteCadastrado);										
									clienteImovelNovaRelacao.setDataInicioRelacao(new Date());
									clienteImovelNovaRelacao.setClienteRelacaoTipo(clienteRelacaoTipo);
									clienteImovelNovaRelacao.setIndicadorNomeConta(clienteImovelFimRelacao != null ? clienteImovelFimRelacao.getIndicadorNomeConta():ConstantesSistema.NAO);
									clienteImovelNovaRelacao.setUltimaAlteracao(new Date());
									registradorOperacaoImovel.registrarOperacao(clienteImovelNovaRelacao);
									getControladorUtil().inserir(clienteImovelNovaRelacao);
								}
								
								alterouCliente = true;
								
								clienteMesmoNomeCPF = clienteCadastrado;
								
								// recupera o cliente endereço do cliente que está sendo alterado
								FiltroClienteEndereco filtroClienteEndereco = new FiltroClienteEndereco();
								filtroClienteEndereco.adicionarParametro(new ParametroSimples(FiltroClienteEndereco.CLIENTE_ID,clienteMesmoNomeCPF.getId()));
								filtroClienteEndereco.adicionarParametro(new ParametroSimples(FiltroClienteEndereco.INDICADOR_CORRESPONDENCIA,ConstantesSistema.SIM));
								Collection colecaoClienteEndereco = getControladorUtil().pesquisar(filtroClienteEndereco,ClienteEndereco.class.getName());
								clienteEnderecoMesmoNomeCPF = (ClienteEndereco) Util.retonarObjetoDeColecao(colecaoClienteEndereco);
							}else{
								//Caso contrário, se existe o cliente cadastrado e o nome não é o mesmo
								if(clienteCadastrado != null){
									// Limpar CPF ou CNPJ do cliente cadastrado
									// e regsitra a transação desse cliente
									if(pessoaFisica){
										clienteCadastrado.setCpf(null);
										clienteCadastrado.setUltimaAlteracao(new Date());
										
									}else{
										clienteCadastrado.setCnpj(null);
										clienteCadastrado.setUltimaAlteracao(new Date());
									}
									RegistradorOperacao registradorOperacao = new RegistradorOperacao(
											Operacao.OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL, imovel.getId(),
											clienteCadastrado.getId(),
											new UsuarioAcaoUsuarioHelper(usuarioLogado,
													UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
									registradorOperacao.registrarOperacao(clienteCadastrado);
									this.registrarTransacao(clienteCadastrado);
									getControladorUtil().atualizar(clienteCadastrado);
								}
								
								if(cliente != null){
									indicadorValidarCpfCnpj = getControladorCliente().pesquisarIndicadorValidarCpfCnpjCliente(cliente.getId());
									
									if(indicadorValidarCpfCnpj != null && indicadorValidarCpfCnpj.equals(ConstantesSistema.SIM.intValue())){
										throw new ActionServletException("atencao.cpf_cnpj_validado");
									}
								}
								
								//atualiza o CPF ou CNPJ do cliente que está sendo atualizado
								if(pessoaFisica){
									cliente.setCpf(numeroCPFouCNPJAlterado);
									cliente.setCnpj(null);
									cliente.setIndicadorValidaCpfCnpj(ConstantesSistema.SIM.intValue());
								}else{
									cliente.setCnpj(numeroCPFouCNPJAlterado);
									cliente.setCpf(null);
									cliente.setIndicadorValidaCpfCnpj(ConstantesSistema.SIM.intValue());
								}
								alterouCliente = true;
						    }	
					    }
						alterouNomeOuCPFOuCNPFCliente = true;
						continue;
					}
												
					//1.12 Caso o campo alterado seja cliente tipo do cliente
					if(tabelaColunaAtualizacaoCadastral.getTabelaColuna() != null && 
							(tabelaColunaAtualizacaoCadastral.getTabelaColuna().getId().equals(TabelaColuna.CLIENTE_TIPO_CLIENTE_ATU_CADASTRAL))){
						FiltroClienteTipo filtroClienteTipo = new FiltroClienteTipo();
						if(tabelaColunaAtualizacaoCadastral.getColunaValorAtual() != null && !tabelaColunaAtualizacaoCadastral.getColunaValorAtual().equals("")){
							filtroClienteTipo.adicionarParametro(new ParametroSimples(FiltroClienteTipo.ID,tabelaColunaAtualizacaoCadastral.getColunaValorAtual()));
							Collection colecaoClienteTipo = getControladorUtil().pesquisar(filtroClienteTipo,ClienteTipo.class.getName());
							if(colecaoClienteTipo != null && !colecaoClienteTipo.isEmpty()){
								ClienteTipo clienteTipo = (ClienteTipo) Util.retonarObjetoDeColecao(colecaoClienteTipo);
								if(clienteNovo != null || clienteMesmoNomeCPF != null){
									if(clienteNovo != null){
										clienteNovo.setClienteTipo(clienteTipo);
									}else{
										clienteMesmoNomeCPF.setClienteTipo(clienteTipo);
									}
								}else{
								    cliente.setClienteTipo(clienteTipo);
								}
								
							}
						}else{
							if(clienteNovo != null || clienteMesmoNomeCPF != null){
								if(clienteNovo != null){
									clienteNovo.setClienteTipo(null);
								}else{
									clienteMesmoNomeCPF.setClienteTipo(null);
								}
							}else{
							    cliente.setClienteTipo(null);
							}
						}
						alterouCliente = true;
						continue;
					}
					//1.13 Caso o campo alterado seja profissão do cliente
					if(tabelaColunaAtualizacaoCadastral.getTabelaColuna() != null && 
							(tabelaColunaAtualizacaoCadastral.getTabelaColuna().getId().equals(TabelaColuna.PROFISSAO_CLIENTE_ATU_CADASTRAL))){
						FiltroProfissao filtroProfissao = new FiltroProfissao();
						if(tabelaColunaAtualizacaoCadastral.getColunaValorAtual() != null && !tabelaColunaAtualizacaoCadastral.getColunaValorAtual().equals("")){
							filtroProfissao.adicionarParametro(new ParametroSimples(FiltroProfissao.ID,tabelaColunaAtualizacaoCadastral.getColunaValorAtual()));
							Collection colecaoProfissao = getControladorUtil().pesquisar(filtroProfissao,Profissao.class.getName());
							if(colecaoProfissao != null && !colecaoProfissao.isEmpty()){
								Profissao profissao = (Profissao) Util.retonarObjetoDeColecao(colecaoProfissao);
								if(clienteNovo != null || clienteMesmoNomeCPF != null){
									if(clienteNovo != null){
										clienteNovo.setProfissao(profissao);
									}else{
										clienteMesmoNomeCPF.setProfissao(profissao);
									}
								}else{
								    cliente.setProfissao(profissao);
								}
								
							}
						}else{
							if(clienteNovo != null || clienteMesmoNomeCPF != null){
								if(clienteNovo != null){
									clienteNovo.setProfissao(null);
								}else{
									clienteMesmoNomeCPF.setProfissao(null);
								}
							}else{
							    cliente.setProfissao(null);
							}
						}
						alterouCliente = true;
						continue;
					}
					//1.14 Caso o campo alterado seja ramo de atividade do cliente
					if(tabelaColunaAtualizacaoCadastral.getTabelaColuna() != null && 
							(tabelaColunaAtualizacaoCadastral.getTabelaColuna().getId().equals(TabelaColuna.RAMO_ATIVIDADE_CLIENTE_ATU_CADASTRAL))){
						FiltroRamoAtividade filtroRamoAtividade = new FiltroRamoAtividade();
						if(tabelaColunaAtualizacaoCadastral.getColunaValorAtual() != null && !tabelaColunaAtualizacaoCadastral.getColunaValorAtual().equals("")){
							filtroRamoAtividade.adicionarParametro(new ParametroSimples(FiltroRamoAtividade.ID,tabelaColunaAtualizacaoCadastral.getColunaValorAtual()));
							Collection colecaoRamoAtividade = getControladorUtil().pesquisar(filtroRamoAtividade,RamoAtividade.class.getName());
							if(colecaoRamoAtividade != null && !colecaoRamoAtividade.isEmpty()){
								RamoAtividade ramoAtividade = (RamoAtividade) Util.retonarObjetoDeColecao(colecaoRamoAtividade);
								if(clienteNovo != null || clienteMesmoNomeCPF != null){
									if(clienteNovo != null){
										clienteNovo.setRamoAtividade(ramoAtividade);
									}else{
										clienteMesmoNomeCPF.setRamoAtividade(ramoAtividade);
									}
								}else{
									cliente.setRamoAtividade(ramoAtividade);
								}
								
							}
						}else{
							if(clienteNovo != null || clienteMesmoNomeCPF != null){
								if(clienteNovo != null){
									clienteNovo.setRamoAtividade(null);
								}else{
									clienteMesmoNomeCPF.setRamoAtividade(null);
								}
							}else{
								cliente.setRamoAtividade(null);
							}
						}
						alterouCliente = true;
						continue;
					}
					
					// Caso o campo alterado seja sexo da pessoa
					if(tabelaColunaAtualizacaoCadastral.getTabelaColuna() != null && 
							(tabelaColunaAtualizacaoCadastral.getTabelaColuna().getId().equals(TabelaColuna.SEXO_CLIENTE_ATUALIZACAO_CADASTRAL))){
						FiltroPessoaSexo filtroPessoaSexo = new FiltroPessoaSexo();
						filtroPessoaSexo.adicionarParametro(new ParametroSimples(FiltroPessoaSexo.DESCRICAO,tabelaColunaAtualizacaoCadastral.getColunaValorAtual()));
						Collection colecaoPessoaSexo = getControladorUtil().pesquisar(filtroPessoaSexo,PessoaSexo.class.getName());
						if(colecaoPessoaSexo != null && !colecaoPessoaSexo.isEmpty()){
							PessoaSexo pessoaSexo = (PessoaSexo) Util.retonarObjetoDeColecao(colecaoPessoaSexo);
							if(clienteNovo != null || clienteMesmoNomeCPF != null){
								if(clienteNovo != null){
									clienteNovo.setPessoaSexo(pessoaSexo);
								}else{
									clienteMesmoNomeCPF.setPessoaSexo(pessoaSexo);
								}
							}else{
								cliente.setPessoaSexo(pessoaSexo);
							}
							alterouCliente = true;
						}
						continue;
					}
					
					// 1.7 Caso o campo alterado seja número do imóvel de cliente endereço
					if(tabelaColunaAtualizacaoCadastral.getTabelaColuna() != null && 
							(tabelaColunaAtualizacaoCadastral.getTabelaColuna().getId().equals(TabelaColuna.NUMERO_IMOVEL_CLIENTE_ENDERECO_ATU_CADASTRAL))){
						if(clienteEnderecoNovo != null || clienteEnderecoMesmoNomeCPF != null){
							if(clienteEnderecoNovo != null){
								clienteEnderecoNovo.setNumero(tabelaColunaAtualizacaoCadastral.getColunaValorAtual());
								alterouClienteEndereco = true;
							}else{
								clienteEnderecoMesmoNomeCPF.setNumero(tabelaColunaAtualizacaoCadastral.getColunaValorAtual());
								alterouClienteEndereco = true;
							}
						}else{
							if(clienteEndereco != null){
								clienteEndereco.setNumero(tabelaColunaAtualizacaoCadastral.getColunaValorAtual());
								alterouClienteEndereco = true;
							}
						}
						continue;
					}	
					// 1.8 Caso o campo alterado seja nome ou razão social do cliente
					if(tabelaColunaAtualizacaoCadastral.getTabelaColuna() != null && 
							(tabelaColunaAtualizacaoCadastral.getTabelaColuna().getId().equals(TabelaColuna.COMPLEMENTO_CLIENTE_ENDERECO_ATU_CADASTRAL))){
						if(clienteEnderecoNovo != null || clienteEnderecoMesmoNomeCPF != null){
							if(clienteEnderecoNovo != null){
								clienteEnderecoNovo.setComplemento(tabelaColunaAtualizacaoCadastral.getColunaValorAtual());
								alterouClienteEndereco = true;
							}else{
								clienteEnderecoMesmoNomeCPF.setComplemento(tabelaColunaAtualizacaoCadastral.getColunaValorAtual());
								alterouClienteEndereco = true;
							}
						}else{
							if(clienteEndereco != null){
							clienteEndereco.setComplemento(tabelaColunaAtualizacaoCadastral.getColunaValorAtual());
							alterouClienteEndereco = true;
							}
						}
						continue;
					}
					
					// 1.3 Caso o campo alterado corresponda a número de moradores
					if(tabelaColunaAtualizacaoCadastral.getTabelaColuna() != null && 
							(tabelaColunaAtualizacaoCadastral.getTabelaColuna().getId().equals(TabelaColuna.NUMERO_MORADORES_IMOVEL_ATU_CADASTRAL))){
						imovel.setNumeroMorador(new Short(tabelaColunaAtualizacaoCadastral.getColunaValorAtual()));
						alterouImovel = true;
						continue;
					}
					// 1.4 Caso o campo alterado corresponda a código do perfil
					if(tabelaColunaAtualizacaoCadastral.getTabelaColuna() != null && 
							(tabelaColunaAtualizacaoCadastral.getTabelaColuna().getId().equals(TabelaColuna.CODIGO_PERFIL_IMOVEL_ATU_CADASTRAL))){
						FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
						filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.ID,tabelaColunaAtualizacaoCadastral.getColunaValorAtual()));
						Collection colecaoImovelPerfil = getControladorUtil().pesquisar(filtroImovelPerfil,ImovelPerfil.class.getName());
						if(colecaoImovelPerfil != null && !colecaoImovelPerfil.isEmpty()){
							ImovelPerfil imovelPerfil = (ImovelPerfil) Util.retonarObjetoDeColecao(colecaoImovelPerfil);
							imovel.setImovelPerfil(imovelPerfil);
							alterouImovel = true;
							
						}
						continue;
					}
					// 1.5 Caso o campo alterado corresponda a número do imóvel
					if(tabelaColunaAtualizacaoCadastral.getTabelaColuna() != null && 
							(tabelaColunaAtualizacaoCadastral.getTabelaColuna().getId().equals(TabelaColuna.NUMERO_IMOVEL_ATU_CADASTRAL))){
						imovel.setNumeroImovel(tabelaColunaAtualizacaoCadastral.getColunaValorAtual());
						alterouImovel = true;
						continue;
					}
					// 1.6 Caso o campo alterado corresponda ao complemento do endereço
					if(tabelaColunaAtualizacaoCadastral.getTabelaColuna() != null && 
							(tabelaColunaAtualizacaoCadastral.getTabelaColuna().getId().equals(TabelaColuna.COMPLEMENTO_ENDERECO_IMOVEL_ATU_CADASTRAL))){
						imovel.setComplementoEndereco(tabelaColunaAtualizacaoCadastral.getColunaValorAtual());
						alterouImovel = true;
						continue;
					}
					
					//Caso o campo alterado corresponda ao cadastro da ocorrência
					if(tabelaColunaAtualizacaoCadastral.getTabelaColuna() != null && 
							(tabelaColunaAtualizacaoCadastral.getTabelaColuna().getId().equals(TabelaColuna.CODIGO_CADASTRO_OCORRENCIA_IMOVEL_ATU_CADASTRAL))){
						FiltroCadastroOcorrencia filtroCadastroOcorrencia = new FiltroCadastroOcorrencia();
						if(tabelaColunaAtualizacaoCadastral.getColunaValorAtual() != null && !tabelaColunaAtualizacaoCadastral.getColunaValorAtual().equals("")){
							filtroCadastroOcorrencia.adicionarParametro(new ParametroSimples(FiltroCadastroOcorrencia.ID,tabelaColunaAtualizacaoCadastral.getColunaValorAtual()));
							Collection colecaoCadastroOcorrencia = getControladorUtil().pesquisar(filtroCadastroOcorrencia,CadastroOcorrencia.class.getName());
							if(colecaoCadastroOcorrencia != null && !colecaoCadastroOcorrencia.isEmpty()){
								CadastroOcorrencia cadastroOcorrencia = (CadastroOcorrencia) Util.retonarObjetoDeColecao(colecaoCadastroOcorrencia);
								imovel.setCadastroOcorrencia(cadastroOcorrencia);
								alterouImovel = true;
								
							}
						}else{
							imovel.setCadastroOcorrencia(null);
							alterouImovel = true;
						}
						continue;
					}
					
					// Caso o campo alterado corresponda a situação da ligação de água
					if(tabelaColunaAtualizacaoCadastral.getTabelaColuna() != null && 
							(tabelaColunaAtualizacaoCadastral.getTabelaColuna().getId().equals(TabelaColuna.ID_SITUACAO_LIGACAO_AGUA_IMOVEL_ATU_CADASTRAL))){
						
						boolean alterouSituacaoImovel = atualizaSituacaoLigacaoAguaImovel(imovel,cliente.getId(),
								Util.converterStringParaInteger(tabelaColunaAtualizacaoCadastral.getColunaValorAnterior()),
								Util.converterStringParaInteger(tabelaColunaAtualizacaoCadastral.getColunaValorAtual()),
								usuarioLogado);
						if(alterouSituacaoImovel){
						  alterouImovel = true;
						}
						continue;
					}
					
					// Caso o campo alterado corresponda a situação da ligação de esgoto
					if(tabelaColunaAtualizacaoCadastral.getTabelaColuna() != null && 
							(tabelaColunaAtualizacaoCadastral.getTabelaColuna().getId().equals(TabelaColuna.ID_SITUACAO_LIGACAO_ESGOTO_IMOVEL_ATU_CADASTRAL))){
						
						boolean alterouSituacaoImovel = atualizaSituacaoLigacaoEsgotoImovel(imovel,cliente.getId(),
								Util.converterStringParaInteger(tabelaColunaAtualizacaoCadastral.getColunaValorAnterior()),
								Util.converterStringParaInteger(tabelaColunaAtualizacaoCadastral.getColunaValorAtual()),
								usuarioLogado);
						if(alterouSituacaoImovel){
						  alterouImovel = true;
						}
					}

					// 1.10 Caso o campo alterado corresponda a quantidade de economias
					if(tabelaColunaAtualizacaoCadastral.getTabelaColuna() != null && 
							(tabelaColunaAtualizacaoCadastral.getTabelaColuna().getId().equals(TabelaColuna.QUANTIDADES_ECONOMIAS_IMOVEL_SUBCATEGORIA_ATU_CADASTRAL))){
						ImovelSubcategoria imovelSubcategoria = this.repositorioTransacao.recuperaImovelSubcategoriaAtualizacaoCadastral(tabelaColunaAtualizacaoCadastral.getTabelaAtualizacaoCadastral().getId());
						if(imovelSubcategoria != null){
							imovelSubcategoria.setQuantidadeEconomias(new Short(tabelaColunaAtualizacaoCadastral.getColunaValorAtual()));
							registradorOperacaoImovel.registrarOperacao(imovelSubcategoria);
							getControladorUtil().atualizar(imovelSubcategoria);
							
						}
						continue;
					}
					
				}
				
			} catch (ErroRepositorioException e) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", e);
			}	
			
		}
		
		// atualiza o cliente endereço
		if(alterouClienteEndereco){
			
			if(clienteEnderecoNovo != null || clienteEnderecoMesmoNomeCPF != null){
				if(clienteEnderecoNovo != null){
					if(registradorOperacaoClienteNovo == null){
						registradorOperacaoClienteNovo = new RegistradorOperacao(
								Operacao.OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL, imovel.getId(),
								clienteEnderecoNovo.getId(),
								new UsuarioAcaoUsuarioHelper(usuarioLogado,
										UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
					}
					
					registradorOperacaoClienteNovo.registrarOperacao(clienteEnderecoNovo);
					getControladorUtil().atualizar(clienteEnderecoNovo);
				}else{
					if(registradorOperacaoClienteMesmoNomeCPF == null){
						registradorOperacaoClienteMesmoNomeCPF = new RegistradorOperacao(
								Operacao.OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL, imovel.getId(),
								clienteEnderecoMesmoNomeCPF.getId(),
								new UsuarioAcaoUsuarioHelper(usuarioLogado,
										UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
					}
					
					registradorOperacaoClienteMesmoNomeCPF.registrarOperacao(clienteEnderecoMesmoNomeCPF);
					getControladorUtil().atualizar(clienteEnderecoMesmoNomeCPF);
				}
				
			}else{
				if(registradorOperacaoCliente == null){
					registradorOperacaoCliente = new RegistradorOperacao(
							Operacao.OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL, imovel.getId(),
							clienteEndereco.getId(),
							new UsuarioAcaoUsuarioHelper(usuarioLogado,
									UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
				}
				
				registradorOperacaoCliente.registrarOperacao(clienteEndereco);
								
				getControladorUtil().atualizar(clienteEndereco);
			}
		}
		//atualiza o cliente
		if(alterouCliente && cliente != null){
			
			if(clienteNovo != null ||  clienteMesmoNomeCPF != null){
				if(clienteNovo != null){
					if(registradorOperacaoClienteNovo == null){
						registradorOperacaoClienteNovo = new RegistradorOperacao(
								Operacao.OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL, imovel.getId(),
								clienteNovo.getId(),
								new UsuarioAcaoUsuarioHelper(usuarioLogado,
										UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
					}
					
					registradorOperacaoClienteNovo.registrarOperacao(clienteNovo);
					getControladorUtil().atualizar(clienteNovo);
				}else{
					if(registradorOperacaoClienteMesmoNomeCPF == null){
						registradorOperacaoClienteMesmoNomeCPF = new RegistradorOperacao(
								Operacao.OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL, imovel.getId(),
								clienteMesmoNomeCPF.getId(),
								new UsuarioAcaoUsuarioHelper(usuarioLogado,
										UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
					}
					
					registradorOperacaoClienteMesmoNomeCPF.registrarOperacao(clienteMesmoNomeCPF);
					getControladorUtil().atualizar(clienteMesmoNomeCPF);
				}
			}else{
				if(registradorOperacaoCliente == null){
					registradorOperacaoCliente = new RegistradorOperacao(
							Operacao.OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL, imovel.getId(),
							cliente.getId(),
							new UsuarioAcaoUsuarioHelper(usuarioLogado,
									UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
				}
				
				registradorOperacaoCliente.registrarOperacao(cliente);
				getControladorUtil().atualizar(cliente);
			}
		}
		//atualiza o imóvel
		if(alterouImovel && imovel != null){
			if(registradorOperacaoImovel == null){
				registradorOperacaoImovel = new RegistradorOperacao(
						Operacao.OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL, imovel.getId(),
						imovel.getId(),
						new UsuarioAcaoUsuarioHelper(usuarioLogado,
								UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
			}
			
			registradorOperacaoImovel.registrarOperacao(imovel);
			getControladorUtil().atualizar(imovel);
		}
	}
	
	/**
	 * [[UC1165] - Confirmar Alterações Cadastrais
	 * 
	 * @author Sávio Luiz
	 * @date 18/05/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void inclusaoImovelClienteAtualizacaoCadastral(String[] idsAtualizacaoCadastral,Usuario usuarioLogado) throws ControladorException, ActionServletException{
		
		boolean inserirCliente = false;
		boolean inserirImovelSubcategoria = false;
		boolean inserirClienteFone = false;
		Cliente cliente = null;
		Imovel imovel = null;
		ClienteEndereco clienteEndereco = null;
		ImovelSubcategoria imovelSubcategoriaNovo = null;
		ImovelSubcategoriaPK imovelSubcategoriaPKNovo = null;
		RegistradorOperacao registradorOperacaoImovel = null;
		Cliente clienteNovoInserir = null;
		Collection<ClienteFone> colecaoClienteFoneNovo = new ArrayList();
		ClienteFone clienteFoneInserir = null;
		ClienteImovel clienteImovelInserir = null;
		ClienteImovel clienteImovel = null;
		Integer indicadorValidaCpfCnpjCliente = null;
		
		//	Varre a lista para recuperar os ids SIM selecionados
		for (int i = 0; i < idsAtualizacaoCadastral.length; i++) {
			Integer idAtualizacaoCadastral = new Integer(idsAtualizacaoCadastral[i]);
			
			try{
			
			    // [UC-1165] - Confirmar Alterações Cadastrais
				// Autor: Sávio Luiz
				TabelaColunaAtualizacaoCadastral tabelaColunaAtualizacaoCadastral = this.repositorioTransacao.pesquisarTabelaColunaAtualizacaoCadastral(idAtualizacaoCadastral);
		
				if(tabelaColunaAtualizacaoCadastral != null && !tabelaColunaAtualizacaoCadastral.equals("")){
					
					// recupera o cliente que será alterado
					if(cliente == null && tabelaColunaAtualizacaoCadastral.getTabelaAtualizacaoCadastral().getCodigoCliente() != null){
						FiltroCliente filtroCliente = new FiltroCliente();
						filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,tabelaColunaAtualizacaoCadastral.getTabelaAtualizacaoCadastral().getCodigoCliente()));
						filtroCliente.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");
						filtroCliente.adicionarCaminhoParaCarregamentoEntidade("ramoAtividade");
						filtroCliente.adicionarCaminhoParaCarregamentoEntidade("profissao");
						Collection colecaoCliente = getControladorUtil().pesquisar(filtroCliente,Cliente.class.getName());
						cliente = (Cliente) Util.retonarObjetoDeColecao(colecaoCliente);
						
						if(cliente == null || cliente.equals("")){
							 //pesquisa o cliente atualizacao pelo imóvel e relação
							 ClienteAtualizacaoCadastral clienteAtualizacaoCadastralAux = getControladorCliente()
									.pesquisarClienteAtualizacaoCadastral(null, tabelaColunaAtualizacaoCadastral.getTabelaAtualizacaoCadastral().getCodigoImovel(), Integer.parseInt(""+ClienteRelacaoTipo.USUARIO));
							 
							 //seta o id do cliente cadastrado com o imóvel para ficar esse cliente com o valor anterior 
							 if(clienteAtualizacaoCadastralAux != null && !clienteAtualizacaoCadastralAux.equals("")){
								 filtroCliente = new FiltroCliente();
									filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,clienteAtualizacaoCadastralAux.getIdCliente()));
									filtroCliente.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");
									filtroCliente.adicionarCaminhoParaCarregamentoEntidade("ramoAtividade");
									filtroCliente.adicionarCaminhoParaCarregamentoEntidade("profissao");
									colecaoCliente = getControladorUtil().pesquisar(filtroCliente,Cliente.class.getName());
									cliente = (Cliente) Util.retonarObjetoDeColecao(colecaoCliente);
							 }
							
						}
						
						//recupera o cliente endereço do cliente que está sendo alterado
						if(cliente != null && !cliente.equals("")){
						FiltroClienteEndereco filtroClienteEndereco = new FiltroClienteEndereco();
						filtroClienteEndereco.adicionarParametro(new ParametroSimples(FiltroClienteEndereco.CLIENTE_ID,cliente.getId()));
						filtroClienteEndereco.adicionarParametro(new ParametroSimples(FiltroClienteEndereco.INDICADOR_CORRESPONDENCIA,ConstantesSistema.SIM));
						Collection colecaoClienteEndereco = getControladorUtil().pesquisar(filtroClienteEndereco,ClienteEndereco.class.getName());
						clienteEndereco = (ClienteEndereco) Util.retonarObjetoDeColecao(colecaoClienteEndereco);
						
						}
					}
					
					//recupera o imóvel que será alterado
					if(imovel == null && tabelaColunaAtualizacaoCadastral.getTabelaAtualizacaoCadastral().getCodigoImovel() != null){
						imovel = getControladorImovel().pesquisarImovel(tabelaColunaAtualizacaoCadastral.getTabelaAtualizacaoCadastral().getCodigoImovel());
						
						//------------ <REGISTRAR TRANSAÇÃO>----------------------------
						registradorOperacaoImovel = new RegistradorOperacao(
								Operacao.OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL, imovel.getId(),
								imovel.getId(),
								new UsuarioAcaoUsuarioHelper(usuarioLogado,
										UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
						
						registradorOperacaoImovel.registrarOperacao(imovel);
						this.registrarTransacao(imovel);
						
					}
						
					// verifica se a subcategoria ja existe
					if(imovelSubcategoriaNovo == null && tabelaColunaAtualizacaoCadastral.getTabelaAtualizacaoCadastral().getCodigoImovel() != null){
						imovelSubcategoriaNovo = new ImovelSubcategoria();
						imovelSubcategoriaPKNovo = new ImovelSubcategoriaPK();
						imovelSubcategoriaPKNovo.setImovel(imovel);
					}
					// verificar se o cliente já existe
					if(clienteNovoInserir == null && tabelaColunaAtualizacaoCadastral.getTabelaAtualizacaoCadastral().getCodigoCliente() != null){
						clienteNovoInserir = new Cliente();
						String idCliente = this.repositorioTransacao.obterValorAtualTabelaColunaAtualizacaoCadastral(tabelaColunaAtualizacaoCadastral.getTabelaAtualizacaoCadastral().getId(),TabelaColuna.CODIGO_CLIENTE_ATU_CADASTRAL);
						if(idCliente != null && !idCliente.equals("")){
							clienteNovoInserir.setId(new Integer(idCliente));
						}
					}
					
					// verificar se o cliente já existe
					if(clienteFoneInserir == null && tabelaColunaAtualizacaoCadastral.getTabelaAtualizacaoCadastral().getCodigoCliente() != null){
						String numeroTelefone = this.repositorioTransacao.obterValorAtualTabelaColunaAtualizacaoCadastral(tabelaColunaAtualizacaoCadastral.getTabelaAtualizacaoCadastral().getId(),TabelaColuna.NUMERO_FONE_CLIENTE_TELEFONE);
						if(numeroTelefone != null){
							clienteFoneInserir = new ClienteFone();
							clienteFoneInserir.setIdTabelaAtualizacaoCadastralAux(tabelaColunaAtualizacaoCadastral.getTabelaAtualizacaoCadastral().getId());
						}
					}else{
						if(tabelaColunaAtualizacaoCadastral.getTabelaAtualizacaoCadastral().getCodigoCliente() != null){
							// caso o id de atualização cadastral seja diferente do cliente que está sendo inserido
							if(clienteFoneInserir.getFoneTipo() != null && !clienteFoneInserir.getIdTabelaAtualizacaoCadastralAux().equals(tabelaColunaAtualizacaoCadastral.getTabelaAtualizacaoCadastral().getId())){
								colecaoClienteFoneNovo.add(clienteFoneInserir);
								if(colecaoClienteFoneNovo.size() > 1){
									boolean achouCliFone = false;
									for(ClienteFone cliFone : colecaoClienteFoneNovo){
										if(cliFone.getIdTabelaAtualizacaoCadastralAux().equals(tabelaColunaAtualizacaoCadastral.getTabelaAtualizacaoCadastral().getId())){
											clienteFoneInserir = cliFone;
											break;
										}
									}
									if(!achouCliFone){
										clienteFoneInserir = new ClienteFone();
										clienteFoneInserir.setIdTabelaAtualizacaoCadastralAux(tabelaColunaAtualizacaoCadastral.getTabelaAtualizacaoCadastral().getId());
									}	
								}else{
									clienteFoneInserir = new ClienteFone();
									clienteFoneInserir.setIdTabelaAtualizacaoCadastralAux(tabelaColunaAtualizacaoCadastral.getTabelaAtualizacaoCadastral().getId());
								}
							}
						}
					}
					
					// Caso o campo incluído corresponda a quantidade de economias
					if(tabelaColunaAtualizacaoCadastral.getTabelaColuna() != null && 
							(tabelaColunaAtualizacaoCadastral.getTabelaColuna().getId().equals(TabelaColuna.QUANTIDADES_ECONOMIAS_IMOVEL_SUBCATEGORIA_ATU_CADASTRAL))){
						imovelSubcategoriaNovo.setQuantidadeEconomias(new Short(tabelaColunaAtualizacaoCadastral.getColunaValorAtual()));
						inserirImovelSubcategoria = true;
						continue;
					}
					// Caso o campo incluído corresponda a código da subcategoria
					if(tabelaColunaAtualizacaoCadastral.getTabelaColuna() != null && 
							(tabelaColunaAtualizacaoCadastral.getTabelaColuna().getId().equals(TabelaColuna.CODIGO_SUBCATEGORIA_IMOVEL_SUBCATEGORIA_ATU_CADASTRAL))){
						FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria();
						filtroSubCategoria.adicionarCaminhoParaCarregamentoEntidade(FiltroSubCategoria.CATEGORIA);
						filtroSubCategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.ID,tabelaColunaAtualizacaoCadastral.getColunaValorAtual()));
						Collection colecaoSubcategoria = getControladorUtil().pesquisar(filtroSubCategoria,Subcategoria.class.getName());
						if(colecaoSubcategoria != null && !colecaoSubcategoria.isEmpty()){
							Subcategoria subcategoria = (Subcategoria)Util.retonarObjetoDeColecao(colecaoSubcategoria);
							subcategoria.setId(new Integer(tabelaColunaAtualizacaoCadastral.getColunaValorAtual()));
							imovelSubcategoriaPKNovo.setSubcategoria(subcategoria);
							imovelSubcategoriaNovo.setComp_id(imovelSubcategoriaPKNovo);
							inserirImovelSubcategoria = true;
						}
						continue;
					}
					// Caso o campo incluído corresponda a código da categoria
					if(tabelaColunaAtualizacaoCadastral.getTabelaColuna() != null && 
							(tabelaColunaAtualizacaoCadastral.getTabelaColuna().getId().equals(TabelaColuna.CODIGO_CATEGORIA_IMOVEL_SUBCATEGORIA_ATU_CADASTRAL))){
						// CAEMA usa o código da subcategoria como o id da subcategoria
						continue;
					}
					
					// Caso o campo alterado seja nome
					// e ainda não tenha sido alterado
					if(tabelaColunaAtualizacaoCadastral.getTabelaColuna() != null && 
						tabelaColunaAtualizacaoCadastral.getTabelaColuna().getId().equals(TabelaColuna.NOME_CLIENTE_ATU_CADASTRAL)){
						clienteNovoInserir.setNome(tabelaColunaAtualizacaoCadastral.getColunaValorAtual());
						inserirCliente = true;
						continue;
					}
					// Caso o campo alterado seja CPF ou CNPJ do cliente
					// e ainda não tenha sido alterado
					if(tabelaColunaAtualizacaoCadastral.getTabelaColuna() != null && 
						tabelaColunaAtualizacaoCadastral.getTabelaColuna().getId().equals(TabelaColuna.CPF_CNPJ_CLIENTE_ATU_CADASTRAL)){
						
						indicadorValidaCpfCnpjCliente = getControladorCliente().pesquisarIndicadorValidarCpfCnpjCliente(cliente.getId());
						
						if(indicadorValidaCpfCnpjCliente != null && indicadorValidaCpfCnpjCliente.equals(ConstantesSistema.SIM.intValue())){
							throw new ActionServletException("atencao.cpf_cnpj_validado");
						}
						
						// verifica se o tipo do cliente foi alterado
						ClienteTipo clienteTipoAlterado = null;
						String idClienteTipoAlterado = this.repositorioTransacao.obterValorAtualTabelaColunaAtualizacaoCadastral(tabelaColunaAtualizacaoCadastral.getTabelaAtualizacaoCadastral().getId(),TabelaColuna.CLIENTE_TIPO_CLIENTE_ATU_CADASTRAL);
						if(idClienteTipoAlterado != null && !idClienteTipoAlterado.equals("")){
							FiltroClienteTipo filtroClienteTipo = new FiltroClienteTipo();
							filtroClienteTipo.adicionarParametro(new ParametroSimples(FiltroClienteTipo.ID,new Integer(idClienteTipoAlterado)));
							Collection colecaoClienteTipo = getControladorUtil().pesquisar(filtroClienteTipo,ClienteTipo.class.getName());
							clienteTipoAlterado = (ClienteTipo)Util.retonarObjetoDeColecao(colecaoClienteTipo);
						}
						
						// valida o CPF ou CNPJ do cliente
						boolean pessoaFisica = validarCPFouCNPJALterado(clienteNovoInserir,clienteTipoAlterado,tabelaColunaAtualizacaoCadastral.getColunaValorAtual());
						if(pessoaFisica){
							clienteNovoInserir.setCpf(tabelaColunaAtualizacaoCadastral.getColunaValorAtual());
							clienteNovoInserir.setIndicadorValidaCpfCnpj(ConstantesSistema.SIM.intValue());
						}else{
							clienteNovoInserir.setCnpj(tabelaColunaAtualizacaoCadastral.getColunaValorAtual());
							clienteNovoInserir.setIndicadorValidaCpfCnpj(ConstantesSistema.SIM.intValue());
						}
						
						inserirCliente = true;
						continue;
					}
													
												
					// Caso o campo alterado seja cliente relação tipo do cliente imóvel
					if(tabelaColunaAtualizacaoCadastral.getTabelaColuna() != null && 
							(tabelaColunaAtualizacaoCadastral.getTabelaColuna().getId().equals(TabelaColuna.CLIENTE_RELACAO_TIPO_CLIENTE_IMOVEL_ATU_CADASTRAL))){
						FiltroClienteRelacaoTipo filtroClienteRelacaoTipo = new FiltroClienteRelacaoTipo();
						filtroClienteRelacaoTipo.adicionarParametro(new ParametroSimples(FiltroClienteRelacaoTipo.CLIENTE_RELACAO_TIPO_ID,tabelaColunaAtualizacaoCadastral.getColunaValorAtual()));
						Collection colecaoClienteRelacaoTipo = getControladorUtil().pesquisar(filtroClienteRelacaoTipo,ClienteRelacaoTipo.class.getName());
						if(colecaoClienteRelacaoTipo != null && !colecaoClienteRelacaoTipo.isEmpty()){
							ClienteRelacaoTipo clienteRelacaoTipo = (ClienteRelacaoTipo) Util.retonarObjetoDeColecao(colecaoClienteRelacaoTipo);
							
							// cria uma nova relação entre o imóvel e o cliente cadastrado que tem o mesmo nome,
							// vindo de atualização cadastral
							clienteImovelInserir = new ClienteImovel();
							clienteImovelInserir.setImovel(imovel);
							//caso não seja um cliente novo, então seta o cliente já cadastrado
							if(clienteNovoInserir != null && clienteNovoInserir.getId() != null && !clienteNovoInserir.getId().equals("")){
							  clienteImovelInserir.setCliente(clienteNovoInserir);
							}else{
								clienteImovelInserir.setCliente(cliente);	
							}
							clienteImovelInserir.setDataInicioRelacao(new Date());
							clienteImovelInserir.setClienteRelacaoTipo(clienteRelacaoTipo);
							FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
							filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID,imovel.getId()));
							filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.INDICADOR_NOME_CONTA,ConstantesSistema.SIM));
							filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
							Collection colecaoClienteImovel = getControladorUtil().pesquisar(filtroClienteImovel,ClienteImovel.class.getName());
							// Caso não tenha o nome da conta então será inserido um novo com o nome da conta
							if(colecaoClienteImovel != null && !colecaoClienteImovel.equals("")){
								clienteImovelInserir.setIndicadorNomeConta(new Short("2"));	
							}else{
								clienteImovelInserir.setIndicadorNomeConta(new Short("1"));
							}
							
							clienteImovelInserir.setUltimaAlteracao(new Date());
							
							inserirCliente = true;
						}
						continue;	
					}
					//1.12 Caso o campo alterado seja cliente tipo do cliente
					if(tabelaColunaAtualizacaoCadastral.getTabelaColuna() != null && 
							(tabelaColunaAtualizacaoCadastral.getTabelaColuna().getId().equals(TabelaColuna.CLIENTE_TIPO_CLIENTE_ATU_CADASTRAL))){
						FiltroClienteTipo filtroClienteTipo = new FiltroClienteTipo();
						filtroClienteTipo.adicionarParametro(new ParametroSimples(FiltroClienteTipo.ID,tabelaColunaAtualizacaoCadastral.getColunaValorAtual()));
						Collection colecaoClienteTipo = getControladorUtil().pesquisar(filtroClienteTipo,ClienteTipo.class.getName());
						if(colecaoClienteTipo != null && !colecaoClienteTipo.isEmpty()){
							ClienteTipo clienteTipo = (ClienteTipo) Util.retonarObjetoDeColecao(colecaoClienteTipo);
							clienteNovoInserir.setClienteTipo(clienteTipo);
							inserirCliente = true; 
						}
						continue;
					}
					//1.13 Caso o campo alterado seja profissão do cliente
					if(tabelaColunaAtualizacaoCadastral.getTabelaColuna() != null && 
							(tabelaColunaAtualizacaoCadastral.getTabelaColuna().getId().equals(TabelaColuna.PROFISSAO_CLIENTE_ATU_CADASTRAL))){
						FiltroProfissao filtroProfissao = new FiltroProfissao();
						if(tabelaColunaAtualizacaoCadastral.getColunaValorAtual() != null && !tabelaColunaAtualizacaoCadastral.getColunaValorAtual().equals("")){
							filtroProfissao.adicionarParametro(new ParametroSimples(FiltroProfissao.ID,tabelaColunaAtualizacaoCadastral.getColunaValorAtual()));
							Collection colecaoProfissao = getControladorUtil().pesquisar(filtroProfissao,Profissao.class.getName());
							if(colecaoProfissao != null && !colecaoProfissao.isEmpty()){
								Profissao profissao = (Profissao) Util.retonarObjetoDeColecao(colecaoProfissao);
								clienteNovoInserir.setProfissao(profissao);
								inserirCliente = true;
							}
						}else{
							clienteNovoInserir.setProfissao(null);
							inserirCliente = true;
						}
						continue;
					}
					//1.14 Caso o campo alterado seja ramo de atividade do cliente
					if(tabelaColunaAtualizacaoCadastral.getTabelaColuna() != null && 
							(tabelaColunaAtualizacaoCadastral.getTabelaColuna().getId().equals(TabelaColuna.RAMO_ATIVIDADE_CLIENTE_ATU_CADASTRAL))){
						FiltroRamoAtividade filtroRamoAtividade = new FiltroRamoAtividade();
						if(tabelaColunaAtualizacaoCadastral.getColunaValorAtual() != null && !tabelaColunaAtualizacaoCadastral.getColunaValorAtual().equals("")){
							filtroRamoAtividade.adicionarParametro(new ParametroSimples(FiltroRamoAtividade.ID,tabelaColunaAtualizacaoCadastral.getColunaValorAtual()));
							Collection colecaoRamoAtividade = getControladorUtil().pesquisar(filtroRamoAtividade,RamoAtividade.class.getName());
							if(colecaoRamoAtividade != null && !colecaoRamoAtividade.isEmpty()){
								RamoAtividade ramoAtividade = (RamoAtividade) Util.retonarObjetoDeColecao(colecaoRamoAtividade);
								clienteNovoInserir.setRamoAtividade(ramoAtividade);
								inserirCliente = true;
							}
						}else{
							clienteNovoInserir.setRamoAtividade(null);
							inserirCliente = true;
						}
						continue;
					}
					
					// Caso o campo alterado seja sexo da pessoa
					if(tabelaColunaAtualizacaoCadastral.getTabelaColuna() != null && 
							(tabelaColunaAtualizacaoCadastral.getTabelaColuna().getId().equals(TabelaColuna.SEXO_CLIENTE_ATUALIZACAO_CADASTRAL))){
						FiltroPessoaSexo filtroPessoaSexo = new FiltroPessoaSexo();
						filtroPessoaSexo.adicionarParametro(new ParametroSimples(FiltroPessoaSexo.DESCRICAO,tabelaColunaAtualizacaoCadastral.getColunaValorAtual()));
						Collection colecaoPessoaSexo = getControladorUtil().pesquisar(filtroPessoaSexo,PessoaSexo.class.getName());
						if(colecaoPessoaSexo != null && !colecaoPessoaSexo.isEmpty()){
							PessoaSexo pessoaSexo = (PessoaSexo) Util.retonarObjetoDeColecao(colecaoPessoaSexo);
							clienteNovoInserir.setPessoaSexo(pessoaSexo);
							inserirCliente = true;
						}
						continue;
					}
					
					// Caso o campo alterado seja o tipo do telefone
					if(tabelaColunaAtualizacaoCadastral.getTabelaColuna() != null && 
							(tabelaColunaAtualizacaoCadastral.getTabelaColuna().getId().equals(TabelaColuna.TIPO_FONE_CLIENTE_TELEFONE))){
						FiltroFoneTipo filtroFoneTipo = new FiltroFoneTipo();
						filtroFoneTipo.adicionarParametro(new ParametroSimples(FiltroFoneTipo.ID,tabelaColunaAtualizacaoCadastral.getColunaValorAtual()));
						Collection colecaoFoneTipo = getControladorUtil().pesquisar(filtroFoneTipo,FoneTipo.class.getName());
						if(colecaoFoneTipo != null && !colecaoFoneTipo.isEmpty()){
							FoneTipo foneTipo = (FoneTipo) Util.retonarObjetoDeColecao(colecaoFoneTipo);
							clienteFoneInserir.setFoneTipo(foneTipo);
							inserirClienteFone = true;
						}
						continue;
					}
					
					// Caso o campo alterado seja o ddd do telefone
					if(tabelaColunaAtualizacaoCadastral.getTabelaColuna() != null && 
						tabelaColunaAtualizacaoCadastral.getTabelaColuna().getId().equals(TabelaColuna.DDD_CLIENTE_TELEFONE)){
						clienteFoneInserir.setDdd(tabelaColunaAtualizacaoCadastral.getColunaValorAtual());
						inserirClienteFone = true;
						continue;
					}
					// Caso o campo alterado seja o dnumero do telefone
					if(tabelaColunaAtualizacaoCadastral.getTabelaColuna() != null && 
						tabelaColunaAtualizacaoCadastral.getTabelaColuna().getId().equals(TabelaColuna.NUMERO_FONE_CLIENTE_TELEFONE)){
						clienteFoneInserir.setTelefone(tabelaColunaAtualizacaoCadastral.getColunaValorAtual());
						inserirClienteFone = true;
						continue;
					}
					// Caso o campo alterado seja o dnumero do telefone
					if(tabelaColunaAtualizacaoCadastral.getTabelaColuna() != null && 
						tabelaColunaAtualizacaoCadastral.getTabelaColuna().getId().equals(TabelaColuna.INDICADOR_FONE_PADRAO_CLIENTE_TELEFONE)){
						Short indicadorFonePadrao = null;
						if(tabelaColunaAtualizacaoCadastral.getColunaValorAtual().equals("Sim")){
							indicadorFonePadrao = 1;
						}else{
							indicadorFonePadrao = 2;
						}
						clienteFoneInserir.setIndicadorTelefonePadrao(indicadorFonePadrao);
						inserirClienteFone = true;
						continue;
					}
				}
				
			} catch (ErroRepositorioException e) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", e);
			}	
			
		}
		
		// insere o imóvel subcategoria novo
		if(imovelSubcategoriaNovo != null && inserirImovelSubcategoria){
			FiltroImovelSubCategoria filtroImovelSubCategoria = new FiltroImovelSubCategoria();
			filtroImovelSubCategoria.adicionarParametro(new ParametroSimples(FiltroImovelSubCategoria.IMOVEL_ID,imovelSubcategoriaNovo.getComp_id().getImovel().getId()));
			filtroImovelSubCategoria.adicionarParametro(new ParametroSimples(FiltroImovelSubCategoria.SUBCATEGORIA_ID,imovelSubcategoriaNovo.getComp_id().getSubcategoria().getId()));
			Collection colecaoImovelSubcategoria = getControladorUtil().pesquisar(filtroImovelSubCategoria,ImovelSubcategoria.class.getName());
			if(colecaoImovelSubcategoria == null || colecaoImovelSubcategoria.isEmpty()){
				imovelSubcategoriaNovo.setUltimaAlteracao(new Date());
				registradorOperacaoImovel.registrarOperacao(imovelSubcategoriaNovo);
				getControladorUtil().inserir(imovelSubcategoriaNovo);
			}
		}
		
		//insere o cliente novo ou a nova relação entre o cliente e o imóvel	
		if(clienteNovoInserir != null && (inserirCliente || inserirClienteFone)){
			//	caso não seja um cliente novo, o id do cliente for diferente de nulo
			
			//Registrar transação
			RegistradorOperacao registradorOperacao = null;
			ClienteEndereco clienteEnderecoNovo = null;
			boolean novoCliente = false;
			
			if(clienteNovoInserir != null && clienteNovoInserir.getId() != null && !clienteNovoInserir.getId().equals("")){
				String numeroCPFouCNPJAlterado = null;
				if(clienteNovoInserir.getClienteTipo() != null && clienteNovoInserir.getClienteTipo().getIndicadorPessoaFisicaJuridica().equals(ClienteTipo.INDICADOR_PESSOA_FISICA)){
					numeroCPFouCNPJAlterado = clienteNovoInserir.getCpf();
				}else{
					numeroCPFouCNPJAlterado = clienteNovoInserir.getCnpj();
				}
				// verificar o se o CPF ou CNPJ alterado já existe
				Cliente clienteCadastrado = pesquisarClienteCadastradoPeloCPFouCNPJ(clienteNovoInserir,clienteNovoInserir.getClienteTipo(),numeroCPFouCNPJAlterado);
				
				// caso mesmo nome, então a nova relação será com o cliente já cadastrado
				if(clienteCadastrado != null && clienteCadastrado.getNome().equals(clienteNovoInserir.getNome())){
					//cliente cadastrado recebe os campos de cliente novo inserir
					clienteCadastrado.setClienteTipo(clienteNovoInserir.getClienteTipo());
					clienteCadastrado.setRamoAtividade(clienteNovoInserir.getRamoAtividade());
					clienteCadastrado.setProfissao(clienteNovoInserir.getProfissao());
					clienteCadastrado.setPessoaSexo(clienteNovoInserir.getPessoaSexo());
					//cliente inserir recebe o cliente cadastrado
					clienteNovoInserir = clienteCadastrado;
					clienteImovelInserir.setCliente(clienteNovoInserir);
					
				}else{
					
					clienteNovoInserir.setUltimaAlteracao(new Date());
					clienteNovoInserir.setIndicadorAcaoCobranca(ConstantesSistema.NAO);
					clienteNovoInserir.setIndicadorAcrescimos(ConstantesSistema.NAO);
					clienteNovoInserir.setIndicadorGeraArquivoTexto(ConstantesSistema.NAO);
					clienteNovoInserir.setIndicadorGeraFaturaAntecipada(ConstantesSistema.NAO);
					clienteNovoInserir.setIndicadorPermiteNegativacao(ConstantesSistema.NAO);
					clienteNovoInserir.setIndicadorUso(ConstantesSistema.NAO);
					clienteNovoInserir.setIndicadorUsoNomeFantasiaConta(ConstantesSistema.NAO);
					clienteNovoInserir.setIndicadorVencimentoMesSeguinte(ConstantesSistema.NAO);					
					
					
					//inseri o cliente endereço do cliente para o novo cliente
					clienteEnderecoNovo = new ClienteEndereco(clienteEndereco);
					clienteEnderecoNovo.setUltimaAlteracao(new Date());
					
					novoCliente = true;
					
				}
			}else{
				clienteNovoInserir = cliente;
				if(clienteImovelInserir != null){
				  clienteImovelInserir.setCliente(clienteNovoInserir);
				}
			}
			
			// Registrar transação
			registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL, imovel.getId(),
					clienteNovoInserir.getId(),
					new UsuarioAcaoUsuarioHelper(usuarioLogado,
							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
			registradorOperacao.registrarOperacao(clienteNovoInserir);
			
			
			
			if(novoCliente){
				Integer idCliente = (Integer)getControladorUtil().inserir(clienteNovoInserir);
				clienteNovoInserir.setId(idCliente);
				
				clienteEnderecoNovo.setCliente(clienteNovoInserir);
				
				registradorOperacao.registrarOperacao(clienteEnderecoNovo);
				Integer idClienteEnderecoNovo = (Integer)getControladorUtil().inserir(clienteEnderecoNovo);
				clienteEnderecoNovo.setId(idClienteEnderecoNovo);
			}else{
				this.registrarTransacao(clienteNovoInserir);
			}
			
			
			
			if(clienteImovelInserir != null){
				FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
				filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID,imovel));
				filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO_ID,clienteImovelInserir.getClienteRelacaoTipo().getId()));
//				filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_ID,clienteImovelInserir.getCliente().getId()));
				filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
				Collection colecaoClienteImovel = getControladorUtil().pesquisar(filtroClienteImovel,ClienteImovel.class.getName());
				if(colecaoClienteImovel == null || colecaoClienteImovel.isEmpty()){
					registradorOperacaoImovel.registrarOperacao(clienteImovelInserir);
					getControladorUtil().inserir(clienteImovelInserir);
				}else{
					clienteImovel = (ClienteImovel) Util.retonarObjetoDeColecao(colecaoClienteImovel);
					clienteImovel.setDataInicioRelacao(new Date());
					clienteImovel.setCliente(clienteNovoInserir);
					clienteImovel.setUltimaAlteracao(new Date());
					
					getControladorUtil().atualizar(clienteImovel);
				}
			}
			
			if(colecaoClienteFoneNovo != null && colecaoClienteFoneNovo.size() >= 1){
				for(ClienteFone clienteFone : colecaoClienteFoneNovo){
					if(!clienteFone.getIdTabelaAtualizacaoCadastralAux().equals(clienteFoneInserir.getIdTabelaAtualizacaoCadastralAux())){
						clienteFone.setCliente(clienteNovoInserir);
						clienteFone.setUltimaAlteracao(new Date());
						registradorOperacao.registrarOperacao(clienteFone);
						getControladorUtil().inserir(clienteFone);
					}
					
				}
				clienteFoneInserir.setCliente(clienteNovoInserir);
				clienteFoneInserir.setCliente(clienteNovoInserir);
				clienteFoneInserir.setUltimaAlteracao(new Date());
				registradorOperacao.registrarOperacao(clienteFoneInserir);
				getControladorUtil().inserir(clienteFoneInserir);
				
				
			}else{
				if(inserirClienteFone){
					clienteFoneInserir.setCliente(clienteNovoInserir);
					clienteFoneInserir.setUltimaAlteracao(new Date());
					registradorOperacao.registrarOperacao(clienteFoneInserir);
					getControladorUtil().inserir(clienteFoneInserir);
				}
			}
		}
	}
	
	/**
	 * [[UC1165] - Confirmar Alterações Cadastrais
	 * 
	 * @author Sávio Luiz
	 * @date 18/05/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void remocaoImovelClienteAtualizacaoCadastral(String[] idsAtualizacaoCadastral,Usuario usuarioLogado) throws ControladorException{
		
		
		Cliente cliente = null;
		Imovel imovel = null;
		RegistradorOperacao registradorOperacaoImovel = null;
		RegistradorOperacao registradorOperacaoCliente = null;
		
		//	Varre a lista para recuperar os ids SIM selecionados
		for (int i = 0; i < idsAtualizacaoCadastral.length; i++) {
			Integer idAtualizacaoCadastral = new Integer(idsAtualizacaoCadastral[i]);
			
			try{
			
			    // [UC-1165] - Confirmar Alterações Cadastrais
				// Autor: Sávio Luiz
				TabelaColunaAtualizacaoCadastral tabelaColunaAtualizacaoCadastral = this.repositorioTransacao.pesquisarTabelaColunaAtualizacaoCadastral(idAtualizacaoCadastral);
		
				if(tabelaColunaAtualizacaoCadastral != null && !tabelaColunaAtualizacaoCadastral.equals("")){
					
					//recupera o imóvel que será alterado
					if(imovel == null && tabelaColunaAtualizacaoCadastral.getTabelaAtualizacaoCadastral().getCodigoImovel() != null){
						imovel = getControladorImovel().pesquisarImovel(tabelaColunaAtualizacaoCadastral.getTabelaAtualizacaoCadastral().getCodigoImovel());
						
						//------------ <REGISTRAR TRANSAÇÃO>----------------------------
						registradorOperacaoImovel = new RegistradorOperacao(
								Operacao.OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL, imovel.getId(),
								imovel.getId(),
								new UsuarioAcaoUsuarioHelper(usuarioLogado,
										UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
						
						registradorOperacaoImovel.registrarOperacao(imovel);
						this.registrarTransacao(imovel);
						
					}
					
					// recupera o cliente que será alterado
					if(cliente == null && tabelaColunaAtualizacaoCadastral.getTabelaAtualizacaoCadastral().getCodigoCliente() != null){
						FiltroCliente filtroCliente = new FiltroCliente();
						filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,tabelaColunaAtualizacaoCadastral.getTabelaAtualizacaoCadastral().getCodigoCliente()));
						filtroCliente.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");
						filtroCliente.adicionarCaminhoParaCarregamentoEntidade("ramoAtividade");
						filtroCliente.adicionarCaminhoParaCarregamentoEntidade("profissao");
						Collection colecaoCliente = getControladorUtil().pesquisar(filtroCliente,Cliente.class.getName());
						cliente = (Cliente) Util.retonarObjetoDeColecao(colecaoCliente);
						
						//------------ <REGISTRAR TRANSAÇÃO>----------------------------
						if(cliente != null && !cliente.equals("")){
							registradorOperacaoCliente = new RegistradorOperacao(
									Operacao.OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL, imovel.getId(),
									cliente.getId(),
									new UsuarioAcaoUsuarioHelper(usuarioLogado,
											UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
							
							registradorOperacaoCliente.registrarOperacao(cliente);
							this.registrarTransacao(cliente);
						}
					}

						
					// Caso contrário, caso a opção seja de EXCLUSÃO
					// 1.9 Caso os campos removidos correspondam a subcategoria do imóvel
					if(tabelaColunaAtualizacaoCadastral.getTabelaColuna() != null && 
							(tabelaColunaAtualizacaoCadastral.getTabelaColuna().getId().equals(TabelaColuna.CODIGO_SUBCATEGORIA_IMOVEL_SUBCATEGORIA_ATU_CADASTRAL))){
						ImovelSubcategoria imovelSubcategoria = this.repositorioTransacao.recuperaImovelSubcategoriaAtualizacaoCadastral(tabelaColunaAtualizacaoCadastral.getTabelaAtualizacaoCadastral().getId());
						if(imovelSubcategoria != null){
							FiltroImovelSubCategoria filtroImovelSubCategoria = new FiltroImovelSubCategoria();
							filtroImovelSubCategoria.adicionarParametro(new ParametroSimples(FiltroImovelSubCategoria.IMOVEL_ID,imovel.getId()));
							Collection colecaoImoveisSub = getControladorUtil().pesquisar(filtroImovelSubCategoria,ImovelSubcategoria.class.getName());
							// verificar se vai ficar sem nehum imóvel subcategoria
							if(colecaoImoveisSub != null && colecaoImoveisSub.size() == 1){
								sessionContext.setRollbackOnly();
								throw new ControladorException("atencao.senha.invalida", null,"Imóvel não pode ficar sem Subcategoria.Cadastre uma Subcategoria.");
							}else{
								registradorOperacaoImovel.registrarOperacao(imovelSubcategoria);
								getControladorUtil().remover(imovelSubcategoria);
							}
						}
						continue;
					}

					// Caso os campos removidos correspondam a telefone do cliente
					if(tabelaColunaAtualizacaoCadastral.getTabelaColuna() != null && 
							(tabelaColunaAtualizacaoCadastral.getTabelaColuna().getId().equals(TabelaColuna.ID_CLIENTE_TELEFONE_ATU_CADASTRAL))){
						ClienteFone clienteFone = this.repositorioTransacao.recuperaClienteFoneAtualizacaoCadastral(tabelaColunaAtualizacaoCadastral.getTabelaAtualizacaoCadastral().getId());
						if(clienteFone != null){
								registradorOperacaoCliente.registrarOperacao(clienteFone);
								getControladorUtil().remover(clienteFone);
						}
						continue;
					}
					
					//caso os campos removidos correspondam a a relação entre o cliente e o imóvel 
					if(tabelaColunaAtualizacaoCadastral.getTabelaColuna() != null && 
							(tabelaColunaAtualizacaoCadastral.getTabelaColuna().getId().equals(TabelaColuna.ID_CLIENTE_ATU_CADASTRAL))){
						// Verifica qual a relação entre o cliente e o imóvel
						ClienteRelacaoTipo clienteRelacaoTipo = this.repositorioTransacao.recuperaTipoRelacaoClienteAtualizacaoCadastral(tabelaColunaAtualizacaoCadastral.getTabelaAtualizacaoCadastral().getId());
						
						// Finalizar a relação entre o imóvel e o cliente antigo
						FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
						filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");
						filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID,imovel.getId()));
						filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO_ID,clienteRelacaoTipo.getId()));
						filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_ID,cliente.getId()));
						filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
						Collection colecaoClienteImovel = getControladorUtil().pesquisar(filtroClienteImovel,ClienteImovel.class.getName());
						if(colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()){
							ClienteImovel clienteImovel = (ClienteImovel) Util.retonarObjetoDeColecao(colecaoClienteImovel);
							clienteImovel.setDataFimRelacao(new Date());
							ClienteImovelFimRelacaoMotivo clienteImovelFimRelacaoMotivo = new ClienteImovelFimRelacaoMotivo();
							clienteImovelFimRelacaoMotivo.setId(ClienteImovelFimRelacaoMotivo.POR_ATU_CADASTRAL);
							clienteImovel.setClienteImovelFimRelacaoMotivo(clienteImovelFimRelacaoMotivo);
							registradorOperacaoImovel.registrarOperacao(clienteImovel);
							//atualiza o cliente imóvel usuário com o data do fim da relação e o motivo 
							getControladorUtil().atualizar(clienteImovel);
						}
						continue;
					}
				}
				
			} catch (ErroRepositorioException e) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", e);
			}	
			
		}
		
	}
	
	/**
	 * [[UC1165] - Confirmar Alterações Cadastrais
	 * 
	 * @author Sávio Luiz
	 * @date 24/05/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean atualizaSituacaoLigacaoAguaImovel(Imovel imovel,Integer idCliente,
			Integer situacaoLigacaoAnterior,Integer situacaoLigacaoAtual,
			Usuario usuarioLogado) throws ControladorException{
		boolean alterouSituacaoLigacao = false;
		
		//caso a situação anterior seja igual Ligado
		if(situacaoLigacaoAnterior.equals(LigacaoAguaSituacao.LIGADO)){
			//caso a situação atual seja igual a Potencial ou Factivel
			if(situacaoLigacaoAtual.equals(LigacaoAguaSituacao.FACTIVEL)){
				alterouSituacaoLigacao = true;
			}else{
				//pesquisa a ligação de água
				FiltroLigacaoAgua filtroLigacaoAgua = new FiltroLigacaoAgua();
				filtroLigacaoAgua.adicionarParametro(new ParametroSimples(FiltroLigacaoAgua.ID, imovel.getId()));
				Collection colecaoLigacaoAgua = getControladorUtil().pesquisar(filtroLigacaoAgua, LigacaoAgua.class.getName());
				LigacaoAgua ligacaoAgua = (LigacaoAgua) Util.retonarObjetoDeColecao(colecaoLigacaoAgua);
				//caso a ligação de água seja diferente de nulo
				if(ligacaoAgua != null){
					// se a situação da ligação atual for igual a cortado
					if(situacaoLigacaoAtual.equals(LigacaoAguaSituacao.CORTADO)){
						//atualiza os dados de corte da ligação de água
						ligacaoAgua.setDataCorte(new Date());
						FiltroMotivoCorte filtroMotivoCorte = new FiltroMotivoCorte();
						filtroMotivoCorte.adicionarParametro(new ParametroSimples(FiltroMotivoCorte.ID, MotivoCorte.ATUALIZACAO_CADASTRAL));
						Collection colecaoMotivoCorte = getControladorUtil().pesquisar(filtroMotivoCorte, MotivoCorte.class.getName());
						MotivoCorte motivoCorte = (MotivoCorte) Util.retonarObjetoDeColecao(colecaoMotivoCorte);
						ligacaoAgua.setMotivoCorte(motivoCorte);
						FiltroCorteTipo filtroCorteTipo = new FiltroCorteTipo();
						filtroCorteTipo.adicionarParametro(new ParametroSimples(FiltroCorteTipo.ID, CorteTipo.RAMAL_ID));
						Collection colecaoCorteTipo = getControladorUtil().pesquisar(filtroCorteTipo, CorteTipo.class.getName());
						CorteTipo corteTipo = (CorteTipo) Util.retonarObjetoDeColecao(colecaoCorteTipo);
						ligacaoAgua.setCorteTipo(corteTipo);
						ligacaoAgua.setUltimaAlteracao(new Date());
						getControladorUtil().atualizar(ligacaoAgua);
						alterouSituacaoLigacao = true;
					}
					
					// se a situação da ligação atual for igual a cortado
					if(situacaoLigacaoAtual.equals(LigacaoAguaSituacao.SUPRIMIDO)){
						//atualiza os dados de corte da ligação de água
						ligacaoAgua.setDataSupressao(new Date());
						FiltroSupressaoMotivo filtroSupressaoMotivo = new FiltroSupressaoMotivo();
						filtroSupressaoMotivo.adicionarParametro(new ParametroSimples(FiltroSupressaoMotivo.ID, SupressaoMotivo.ATUALIZACAO_CADASTRAL));
						Collection colecaoSupressaoMotivo = getControladorUtil().pesquisar(filtroSupressaoMotivo, SupressaoMotivo.class.getName());
						SupressaoMotivo supressaoMotivo = (SupressaoMotivo) Util.retonarObjetoDeColecao(colecaoSupressaoMotivo);
						ligacaoAgua.setSupressaoMotivo(supressaoMotivo);
						FiltroSupressaoTipo filtroSupressaoTipo = new FiltroSupressaoTipo();
						filtroSupressaoTipo.adicionarParametro(new ParametroSimples(FiltroSupressaoTipo.ID, SupressaoTipo.SUPRESSAO_TIPO_ID));
						Collection colecaoSupressaoTipo = getControladorUtil().pesquisar(filtroSupressaoTipo, SupressaoTipo.class.getName());
						SupressaoTipo supressaoTipo = (SupressaoTipo) Util.retonarObjetoDeColecao(colecaoSupressaoTipo);
						ligacaoAgua.setSupressaoTipo(supressaoTipo);
						ligacaoAgua.setUltimaAlteracao(new Date());
						getControladorUtil().atualizar(ligacaoAgua);
						alterouSituacaoLigacao = true;
					}
				}
			}
		}
		
		//caso a situação anterior seja igual Cortado
		if(situacaoLigacaoAnterior.equals(LigacaoAguaSituacao.CORTADO)){
			//caso a situação atual seja igual a Potencial ou Factivel
			if(situacaoLigacaoAtual.equals(LigacaoAguaSituacao.FACTIVEL)){
				alterouSituacaoLigacao = true;
			}else{
				//pesquisa a ligação de água
				FiltroLigacaoAgua filtroLigacaoAgua = new FiltroLigacaoAgua();
				filtroLigacaoAgua.adicionarParametro(new ParametroSimples(FiltroLigacaoAgua.ID, imovel.getId()));
				Collection colecaoLigacaoAgua = getControladorUtil().pesquisar(filtroLigacaoAgua, LigacaoAgua.class.getName());
				LigacaoAgua ligacaoAgua = (LigacaoAgua) Util.retonarObjetoDeColecao(colecaoLigacaoAgua);
				//caso a ligação de água seja diferente de nulo
				if(ligacaoAgua != null){
					// se a situação da ligação atual for igual a Ligado
					if(situacaoLigacaoAtual.equals(LigacaoAguaSituacao.LIGADO)){
						//atualiza os dados de corte da ligação de água
						ligacaoAgua.setDataReligacao(new Date());
						ligacaoAgua.setUltimaAlteracao(new Date());
						getControladorUtil().atualizar(ligacaoAgua);
						alterouSituacaoLigacao = true;
					}
					
					// se a situação da ligação atual for igual a cortado
					if(situacaoLigacaoAtual.equals(LigacaoAguaSituacao.SUPRIMIDO)){
						//atualiza os dados de corte da ligação de água
						ligacaoAgua.setDataSupressao(new Date());
						FiltroSupressaoMotivo filtroSupressaoMotivo = new FiltroSupressaoMotivo();
						filtroSupressaoMotivo.adicionarParametro(new ParametroSimples(FiltroSupressaoMotivo.ID, SupressaoMotivo.ATUALIZACAO_CADASTRAL));
						Collection colecaoSupressaoMotivo = getControladorUtil().pesquisar(filtroSupressaoMotivo, SupressaoMotivo.class.getName());
						SupressaoMotivo supressaoMotivo = (SupressaoMotivo) Util.retonarObjetoDeColecao(colecaoSupressaoMotivo);
						ligacaoAgua.setSupressaoMotivo(supressaoMotivo);
						FiltroSupressaoTipo filtroSupressaoTipo = new FiltroSupressaoTipo();
						filtroSupressaoTipo.adicionarParametro(new ParametroSimples(FiltroSupressaoTipo.ID, SupressaoTipo.SUPRESSAO_TIPO_ID));
						Collection colecaoSupressaoTipo = getControladorUtil().pesquisar(filtroSupressaoTipo, SupressaoTipo.class.getName());
						SupressaoTipo supressaoTipo = (SupressaoTipo) Util.retonarObjetoDeColecao(colecaoSupressaoTipo);
						ligacaoAgua.setSupressaoTipo(supressaoTipo);
						ligacaoAgua.setUltimaAlteracao(new Date());
						getControladorUtil().atualizar(ligacaoAgua);
						alterouSituacaoLigacao = true;
					}
				}
			}
		}
		
		//caso a situação anterior seja igual Ligado
		if(situacaoLigacaoAnterior.equals(LigacaoAguaSituacao.SUPRIMIDO)){
			//caso a situação atual seja igual a Potencial ou Factivel
			if(situacaoLigacaoAtual.equals(LigacaoAguaSituacao.FACTIVEL)){
				alterouSituacaoLigacao = true;
			}else{
				//pesquisa a ligação de água
				FiltroLigacaoAgua filtroLigacaoAgua = new FiltroLigacaoAgua();
				filtroLigacaoAgua.adicionarParametro(new ParametroSimples(FiltroLigacaoAgua.ID, imovel.getId()));
				Collection colecaoLigacaoAgua = getControladorUtil().pesquisar(filtroLigacaoAgua, LigacaoAgua.class.getName());
				LigacaoAgua ligacaoAgua = (LigacaoAgua) Util.retonarObjetoDeColecao(colecaoLigacaoAgua);
				//caso a ligação de água seja diferente de nulo
				if(ligacaoAgua != null){
					// se a situação da ligação atual for igual a Ligado
					if(situacaoLigacaoAtual.equals(LigacaoAguaSituacao.LIGADO)){
						//atualiza os dados de corte da ligação de água
						ligacaoAgua.setDataRestabelecimentoAgua(new Date());
						ligacaoAgua.setUltimaAlteracao(new Date());
						getControladorUtil().atualizar(ligacaoAgua);
						alterouSituacaoLigacao = true;
					}
					
					// se a situação da ligação atual for igual a cortado
					if(situacaoLigacaoAtual.equals(LigacaoAguaSituacao.CORTADO)){
						//atualiza os dados de corte da ligação de água
						ligacaoAgua.setDataCorte(new Date());
						FiltroMotivoCorte filtroMotivoCorte = new FiltroMotivoCorte();
						filtroMotivoCorte.adicionarParametro(new ParametroSimples(FiltroMotivoCorte.ID, MotivoCorte.ATUALIZACAO_CADASTRAL));
						Collection colecaoMotivoCorte = getControladorUtil().pesquisar(filtroMotivoCorte, MotivoCorte.class.getName());
						MotivoCorte motivoCorte = (MotivoCorte) Util.retonarObjetoDeColecao(colecaoMotivoCorte);
						ligacaoAgua.setMotivoCorte(motivoCorte);
						FiltroCorteTipo filtroCorteTipo = new FiltroCorteTipo();
						filtroCorteTipo.adicionarParametro(new ParametroSimples(FiltroCorteTipo.ID, CorteTipo.RAMAL_ID));
						Collection colecaoCorteTipo = getControladorUtil().pesquisar(filtroCorteTipo, CorteTipo.class.getName());
						CorteTipo corteTipo = (CorteTipo) Util.retonarObjetoDeColecao(colecaoCorteTipo);
						ligacaoAgua.setCorteTipo(corteTipo);
						ligacaoAgua.setUltimaAlteracao(new Date());
						getControladorUtil().atualizar(ligacaoAgua);
						alterouSituacaoLigacao = true;
					}
				}
			}
		}
		
		if(alterouSituacaoLigacao){
			//atualiza a situação da ligação de água do imóvel
			FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
			filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoAguaSituacao.ID, situacaoLigacaoAtual));
			Collection colecaoLigacaoAguaSituacao = getControladorUtil().pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
			LigacaoAguaSituacao ligacaoAguaSituacao = (LigacaoAguaSituacao) Util.retonarObjetoDeColecao(colecaoLigacaoAguaSituacao);
			imovel.setLigacaoAguaSituacao(ligacaoAguaSituacao);
			//cria o registro de atendimento para a situação da ligação alterada
			FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
			filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.CODIGO_CONSTANTE, SolicitacaoTipoEspecificacao.CODIGO_CONSTANTE_ATUALIZACAO_CADASTRAL));
			Collection colecaoSolicitacaoTipoEspecificacao = getControladorUtil().pesquisar(filtroSolicitacaoTipoEspecificacao, SolicitacaoTipoEspecificacao.class.getName());
			if(colecaoSolicitacaoTipoEspecificacao != null && !colecaoSolicitacaoTipoEspecificacao.isEmpty()){
				SolicitacaoTipoEspecificacao SolicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao) Util.retonarObjetoDeColecao(colecaoSolicitacaoTipoEspecificacao);
				getControladorRegistroAtendimento().inserirRASituacaoLigacaoImovel(MeioSolicitacao.ATUALIZACAO_CADASTRAL_CELULAR,
						SolicitacaoTipoEspecificacao.getId(), imovel.getId(), usuarioLogado.getUnidadeOrganizacional().getId(), usuarioLogado.getId(), idCliente);
			}	
		}else{
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.senha.invalida", null,"Alteração da Situção da Ligação de Água não permitida");
		}
		
		return alterouSituacaoLigacao;	
	}
	
	/**
	 * [[UC1165] - Confirmar Alterações Cadastrais
	 * 
	 * @author Sávio Luiz
	 * @date 24/05/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean atualizaSituacaoLigacaoEsgotoImovel(Imovel imovel,Integer idCliente,
			Integer situacaoLigacaoAnterior,Integer situacaoLigacaoAtual,
			Usuario usuarioLogado) throws ControladorException{
		boolean alterouSituacaoLigacao = false;
		
		//caso a situação anterior seja igual Ligado
		if(situacaoLigacaoAnterior.equals(LigacaoEsgotoSituacao.LIGADO)){
			//caso a situação atual seja igual a Potencial ou Factivel
			if(situacaoLigacaoAtual.equals(LigacaoEsgotoSituacao.FACTIVEL)){
				//atualiza a situação da ligação de água do imóvel
				FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
				filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgotoSituacao.ID, situacaoLigacaoAtual));
				Collection colecaoLigacaoEsgotoSituacao = getControladorUtil().pesquisar(filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName());
				LigacaoEsgotoSituacao ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) Util.retonarObjetoDeColecao(colecaoLigacaoEsgotoSituacao);
				imovel.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);
				//cria o registro de atendimento para a situação da ligação alterada
				FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
				filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.CODIGO_CONSTANTE, SolicitacaoTipoEspecificacao.CODIGO_CONSTANTE_ATUALIZACAO_CADASTRAL));
				Collection colecaoSolicitacaoTipoEspecificacao = getControladorUtil().pesquisar(filtroSolicitacaoTipoEspecificacao, SolicitacaoTipoEspecificacao.class.getName());
				if(colecaoSolicitacaoTipoEspecificacao != null && !colecaoSolicitacaoTipoEspecificacao.isEmpty()){
					SolicitacaoTipoEspecificacao SolicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao) Util.retonarObjetoDeColecao(colecaoSolicitacaoTipoEspecificacao);
					getControladorRegistroAtendimento().inserirRASituacaoLigacaoImovel(MeioSolicitacao.ATUALIZACAO_CADASTRAL_CELULAR,
							SolicitacaoTipoEspecificacao.getId(), imovel.getId(), usuarioLogado.getUnidadeOrganizacional().getId(), usuarioLogado.getId(), idCliente);
				}
				alterouSituacaoLigacao = true;
			}
			
		}
		
		if(!alterouSituacaoLigacao){
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.senha.invalida", null,"Alteração da Situção da Ligação de Água não permitida");
		}
		
		return alterouSituacaoLigacao;	
	}
	
	/**
	 * @author Davi Menezes
	 * @date 06/07/2012
	 * 
	 * @param usuarioLogado
	 * @param idImovelAtualizacaoCadastral
	 * 
	 */
	private Integer inserirImovelDoArquivoAtualizacaoCadastral(String idImovelAtualizacaoCadastral, Usuario usuarioLogado) throws ControladorException, ActionServletException {
		FiltroImovelAtualizacaoCadastral filtro = new FiltroImovelAtualizacaoCadastral();
		filtro.adicionarParametro(new ParametroSimples(FiltroImovelAtualizacaoCadastral.ID, idImovelAtualizacaoCadastral));
		filtro.adicionarCaminhoParaCarregamentoEntidade("imovel");
		
		Integer idImovel = null;
		
		Collection<?> colImovelAtualizacaoCadastral = getControladorUtil().pesquisar(filtro, ImovelAtualizacaoCadastral.class.getName());
		if(!Util.isVazioOrNulo(colImovelAtualizacaoCadastral)){
			ImovelAtualizacaoCadastral imovelAtualizacaoCadastral = (ImovelAtualizacaoCadastral) Util.retonarObjetoDeColecao(colImovelAtualizacaoCadastral);
			
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.LOCALIDADE_ID, imovelAtualizacaoCadastral.getIdLocalidade()));
			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.SETOR_COMERCIAL_CODIGO, imovelAtualizacaoCadastral.getCodigoSetorComercial()));
			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.QUADRA_NUMERO, imovelAtualizacaoCadastral.getNumeroQuadra()));
			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.LOTE, imovelAtualizacaoCadastral.getLote()));
			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.SUBLOTE, imovelAtualizacaoCadastral.getSubLote()));
			
			Collection<?> colImovel = getControladorUtil().pesquisar(filtroImovel, Imovel.class.getName());
			if(!Util.isVazioOrNulo(colImovel)){
				throw new ActionServletException("atencao.inscricao_imovel_existente");
			}
			
			Imovel imovel = new Imovel();
			
			if(imovelAtualizacaoCadastral.getIdLocalidade() != null){
				Localidade localidade = new Localidade(imovelAtualizacaoCadastral.getIdLocalidade());
				imovel.setLocalidade(localidade);
			}
			
			if(Integer.valueOf(imovelAtualizacaoCadastral.getCodigoSetorComercial()) != null){
				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, 
						imovelAtualizacaoCadastral.getCodigoSetorComercial()));
				
				if(imovelAtualizacaoCadastral.getIdLocalidade() != null){
					filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE,
						imovelAtualizacaoCadastral.getIdLocalidade()));
				}
				
				Collection<?> colSetorComercial = getControladorUtil().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
				if(!Util.isVazioOrNulo(colSetorComercial)){
					SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colSetorComercial);
					imovel.setSetorComercial(setorComercial);
				}
			}
			
			if(Integer.valueOf(imovelAtualizacaoCadastral.getNumeroQuadra()) != null){
				FiltroQuadra filtroQuadra = new FiltroQuadra();
				filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, 
						imovelAtualizacaoCadastral.getNumeroQuadra()));
				
				if(Integer.valueOf(imovelAtualizacaoCadastral.getCodigoSetorComercial()) != null){
					filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, 
							imovelAtualizacaoCadastral.getCodigoSetorComercial()));
				}
				
				if(Integer.valueOf(imovelAtualizacaoCadastral.getIdLocalidade()) != null){
					filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, 
							imovelAtualizacaoCadastral.getIdLocalidade()));
				}
				
				Collection<?> colQuadra = getControladorUtil().pesquisar(filtroQuadra, Quadra.class.getName());
				if(!Util.isVazioOrNulo(colQuadra)){
					Quadra quadra = (Quadra) Util.retonarObjetoDeColecao(colQuadra);
					imovel.setQuadra(quadra);
				}
			}
			
			imovel.setLote(imovelAtualizacaoCadastral.getLote());
			imovel.setSubLote(imovelAtualizacaoCadastral.getSubLote());
			imovel.setNumeroImovel(imovelAtualizacaoCadastral.getNumeroImovel());
			imovel.setComplementoEndereco(imovelAtualizacaoCadastral.getComplementoEndereco());
			imovel.setIndicadorImovelCondominio(new Short("2"));
			
			if(Integer.valueOf(imovelAtualizacaoCadastral.getCodigoCep()) != null && 
					Integer.valueOf(imovelAtualizacaoCadastral.getIdLogradouro()) != null){
				
				FiltroLogradouroCep filtroLogradouroCep = new FiltroLogradouroCep();
				filtroLogradouroCep.adicionarParametro(new ParametroSimples(FiltroLogradouroCep.ID_LOGRADOURO, 
						imovelAtualizacaoCadastral.getIdLogradouro()));
				filtroLogradouroCep.adicionarParametro(new ParametroSimples(FiltroLogradouroCep.CODIGO_CEP, 
						imovelAtualizacaoCadastral.getCodigoCep()));
				
				Collection<?> colLogradouroCep = getControladorUtil().pesquisar(filtroLogradouroCep, LogradouroCep.class.getName());
				if(!Util.isVazioOrNulo(colLogradouroCep)){
					LogradouroCep logradouroCep = (LogradouroCep) Util.retonarObjetoDeColecao(colLogradouroCep);
					imovel.setLogradouroCep(logradouroCep);
					imovel.setCep(logradouroCep.getCep());
				}
			}
			
			if(imovelAtualizacaoCadastral.getCodigoImovelPrincipal() != null){
				Imovel imovelPrincipal = new Imovel(imovelAtualizacaoCadastral.getCodigoImovelPrincipal());
				imovel.setImovelPrincipal(imovelPrincipal);
			}
			
			imovel.setVolumeReservatorioSuperior(imovelAtualizacaoCadastral.getVolumeReservatorioSuperior());
			imovel.setVolumeReservatorioInferior(imovelAtualizacaoCadastral.getVolumeReservatorioInferior());
			imovel.setVolumePiscina(imovelAtualizacaoCadastral.getVolumePiscina());
			
			if(imovelAtualizacaoCadastral.getIdPavimentoCalcada() != null && imovelAtualizacaoCadastral.getIdPavimentoCalcada() != 0){
				PavimentoCalcada pavimentoCalcada = new PavimentoCalcada(imovelAtualizacaoCadastral.getIdPavimentoCalcada());
				imovel.setPavimentoCalcada(pavimentoCalcada);
			}
			
			if(imovelAtualizacaoCadastral.getIdPavimentoRua() != null && imovelAtualizacaoCadastral.getIdPavimentoRua() != 0){
				PavimentoRua pavimentoRua = new PavimentoRua(imovelAtualizacaoCadastral.getIdPavimentoRua());
				imovel.setPavimentoRua(pavimentoRua);
			}
			
			if(imovelAtualizacaoCadastral.getIdFonteAbastecimento() != null && imovelAtualizacaoCadastral.getIdFonteAbastecimento() != 0){
				FonteAbastecimento fonteAbastecimento = new FonteAbastecimento();
				fonteAbastecimento.setId(imovelAtualizacaoCadastral.getIdFonteAbastecimento());
				imovel.setFonteAbastecimento(fonteAbastecimento);
			}
			
			if(imovelAtualizacaoCadastral.getIdLigacaoAguaSituacao() != null && imovelAtualizacaoCadastral.getIdLigacaoAguaSituacao() != 0){
				LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
				ligacaoAguaSituacao.setId(imovelAtualizacaoCadastral.getIdLigacaoAguaSituacao());
				imovel.setLigacaoAguaSituacao(ligacaoAguaSituacao);
			}
			
			if(imovelAtualizacaoCadastral.getIdLigacaoEsgotoSituacao() != null && imovelAtualizacaoCadastral.getIdLigacaoEsgotoSituacao() != 0){
				LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
				ligacaoEsgotoSituacao.setId(imovelAtualizacaoCadastral.getIdLigacaoEsgotoSituacao());
				imovel.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);
			}
			
			if(imovelAtualizacaoCadastral.getIdImovelPerfil() != null && imovelAtualizacaoCadastral.getIdImovelPerfil() != 0){
				ImovelPerfil imovelPerfil = new ImovelPerfil();
				imovelPerfil.setId(imovelAtualizacaoCadastral.getIdImovelPerfil());
				imovel.setImovelPerfil(imovelPerfil);
			}
			
			if(imovelAtualizacaoCadastral.getIdPocoTipo() != null && imovelAtualizacaoCadastral.getIdPocoTipo() != 0){
				PocoTipo pocoTipo = new PocoTipo();
				pocoTipo.setId(imovelAtualizacaoCadastral.getIdPocoTipo());
				imovel.setPocoTipo(pocoTipo);
			}
			
			ConsumoTarifa consumoTarifa = new ConsumoTarifa();
			consumoTarifa.setId(ConsumoTarifa.CONSUMO_NORMAL);
			
			ImovelContaEnvio imovelContaEnvio = new ImovelContaEnvio();
			imovelContaEnvio.setId(ImovelContaEnvio.ENVIAR_IMOVEL);
			
			imovel.setImovelContaEnvio(imovelContaEnvio);
			imovel.setConsumoTarifa(consumoTarifa);
			imovel.setNumeroPontosUtilizacao(imovelAtualizacaoCadastral.getNumeroPontosUtilizacao());
			imovel.setNumeroMorador(imovelAtualizacaoCadastral.getNumeroMorador());
			imovel.setNumeroIptu(imovelAtualizacaoCadastral.getNumeroIptu());
			imovel.setNumeroMedidorEnergia(imovelAtualizacaoCadastral.getNumeroMedidirEnergia());
			imovel.setNumeroCelpe(imovelAtualizacaoCadastral.getNumeroContratoEnergia());
			imovel.setIndicadorEmissaoExtratoFaturamento(ConstantesSistema.INDICADOR_USO_DESATIVO);
			imovel.setIndicadorDebitoConta(ConstantesSistema.INDICADOR_USO_DESATIVO);
			imovel.setCoordenadaX(imovelAtualizacaoCadastral.getCoordenadaX());
			imovel.setCoordenadaY(imovelAtualizacaoCadastral.getCoordenadaY());
			imovel.setAreaConstruida(imovelAtualizacaoCadastral.getAreaConstruida());
			imovel.setIndicadorJardim(imovelAtualizacaoCadastral.getIndicadorJardim());
			imovel.setNumeroSequencialRota(imovelAtualizacaoCadastral.getNumeroSequencialRota());
			imovel.setIndicadorVencimentoMesSeguinte(ConstantesSistema.INDICADOR_USO_DESATIVO);
			imovel.setIndicadorNivelInstalacaoEsgoto(ConstantesSistema.INDICADOR_USO_DESATIVO);
			imovel.setIndicadorImovelAreaComum(ConstantesSistema.INDICADOR_USO_DESATIVO);
			imovel.setIndicadorExclusao(ConstantesSistema.NAO);
			imovel.setUltimaAlteracao(new Date());
			
			if(imovelAtualizacaoCadastral.getIdCadastroOcorrencia() != null && imovelAtualizacaoCadastral.getIdCadastroOcorrencia() != 0){
				CadastroOcorrencia cadastroOcorrencia = new CadastroOcorrencia();
				cadastroOcorrencia.setId(imovelAtualizacaoCadastral.getIdCadastroOcorrencia());
				imovel.setCadastroOcorrencia(cadastroOcorrencia);
			}
			
			if(imovelAtualizacaoCadastral.getIdEnderecoReferencia() != null && imovelAtualizacaoCadastral.getIdEnderecoReferencia() != 0){
				EnderecoReferencia enderecoReferencia = new EnderecoReferencia();
				enderecoReferencia.setId(imovelAtualizacaoCadastral.getIdEnderecoReferencia());
				imovel.setEnderecoReferencia(enderecoReferencia);
			}
			
			if(imovelAtualizacaoCadastral.getIdLogradouro() != null && imovelAtualizacaoCadastral.getIdBairro() != null){
				FiltroLogradouroBairro filtroLogradouroBairro = new FiltroLogradouroBairro();
				filtroLogradouroBairro.adicionarParametro(new ParametroSimples(FiltroLogradouroBairro.ID_BAIRRO, 
						imovelAtualizacaoCadastral.getIdBairro()));
				filtroLogradouroBairro.adicionarParametro(new ParametroSimples(FiltroLogradouroBairro.ID_LOGRADOURO,
						imovelAtualizacaoCadastral.getIdLogradouro()));
				
				Collection<?> colLogradouroBairro = getControladorUtil().pesquisar(filtroLogradouroBairro, 
						LogradouroBairro.class.getName());
				
				if(!Util.isVazioOrNulo(colLogradouroBairro)){
					LogradouroBairro logradouroBairro = (LogradouroBairro) Util.retonarObjetoDeColecao(colLogradouroBairro);
					imovel.setLogradouroBairro(logradouroBairro);
					imovel.setLogradouro(logradouroBairro.getLogradouro());
					imovel.setBairro(logradouroBairro.getBairro());
				}
			}
			
			if(imovelAtualizacaoCadastral.getIdSituacaoAtualizacaoCadastral() != null){
				SituacaoAtualizacaoCadastral situacao = new SituacaoAtualizacaoCadastral();
				situacao.setId(imovelAtualizacaoCadastral.getIdSituacaoAtualizacaoCadastral());
				imovel.setSituacaoAtualizacaoCadastral(situacao);
			}
			
			idImovel = (Integer) getControladorUtil().inserir(imovel);
			imovel.setId(idImovel);
			
		}
		
		return idImovel;
	}
	
	private void inserirClienteDoArquivoAtualizacaoCadastral(String idImovelAtualizacaoCadastral, Integer idImovel, Usuario usuarioLogado) throws ControladorException {
		FiltroClienteAtualizacaoCadastral filtro = new FiltroClienteAtualizacaoCadastral();
		filtro.adicionarParametro(new ParametroSimples(FiltroClienteAtualizacaoCadastral.ID_IMOVEL_ATUALIZACAO_CADASTRAL, 
				idImovelAtualizacaoCadastral));
		
		Collection<?> colClienteAtualizacaoCadastral = this.getControladorUtil().pesquisar(filtro, ClienteAtualizacaoCadastral.class.getName()); 
		if(!Util.isVazioOrNulo(colClienteAtualizacaoCadastral)){
			Cliente novoCliente = new Cliente();
			
			ClienteAtualizacaoCadastral clienteAtualizacaoCadastral = (ClienteAtualizacaoCadastral) 
					Util.retonarObjetoDeColecao(colClienteAtualizacaoCadastral);
			
			if(clienteAtualizacaoCadastral.getCpfCnpj() != null){
				FiltroCliente filtroCliente = new FiltroCliente();
				if(clienteAtualizacaoCadastral.getCpfCnpj().length() == 11){
					filtroCliente.adicionarParametro(new ParametroSimples(
						FiltroCliente.CPF, clienteAtualizacaoCadastral.getCpfCnpj()));
				}else{
					filtroCliente.adicionarParametro(new ParametroSimples(
						FiltroCliente.CNPJ, clienteAtualizacaoCadastral.getCpfCnpj()));
				}
				
				Collection<?> colCliente = getControladorUtil().pesquisar(filtroCliente, Cliente.class.getName());
				novoCliente = (Cliente) Util.retonarObjetoDeColecao(colCliente);
			}
			
			if(novoCliente == null){
				novoCliente = new Cliente();
				
				if(clienteAtualizacaoCadastral.getIdClienteTipo() != null){
					ClienteTipo clienteTipo = new ClienteTipo();
					clienteTipo.setId(clienteAtualizacaoCadastral.getIdClienteTipo());
					novoCliente.setClienteTipo(clienteTipo);
				}
				
				if(clienteAtualizacaoCadastral.getIdRamoAtividade() != null){
					RamoAtividade ramoAtividade = new RamoAtividade();
					ramoAtividade.setId(clienteAtualizacaoCadastral.getIdRamoAtividade());
					novoCliente.setRamoAtividade(ramoAtividade);
				}
				
				if(clienteAtualizacaoCadastral.getCpfCnpj() != null){
					if(clienteAtualizacaoCadastral.getCpfCnpj().length() == 11){
						novoCliente.setCpf(clienteAtualizacaoCadastral.getCpfCnpj());
					}else{
						novoCliente.setCnpj(clienteAtualizacaoCadastral.getCpfCnpj());
					}
				}
				
				novoCliente.setNome(clienteAtualizacaoCadastral.getNomeCliente());
				novoCliente.setNomeMae(clienteAtualizacaoCadastral.getNomeMae());
				novoCliente.setEmail(clienteAtualizacaoCadastral.getEmail());
				novoCliente.setRg(clienteAtualizacaoCadastral.getRg());
				novoCliente.setDataEmissaoRg(clienteAtualizacaoCadastral.getDataEmissaoRg());
				novoCliente.setDataNascimento(clienteAtualizacaoCadastral.getDataNascimento());
				novoCliente.setIndicadorAcrescimos(ConstantesSistema.NAO);
				novoCliente.setIndicadorGeraArquivoTexto(ConstantesSistema.NAO);
				novoCliente.setIndicadorVencimentoMesSeguinte(ConstantesSistema.NAO);
				novoCliente.setIndicadorGeraFaturaAntecipada(ConstantesSistema.NAO);
				novoCliente.setIndicadorUsoNomeFantasiaConta(ConstantesSistema.NAO);
				novoCliente.setIndicadorPermiteNegativacao(ConstantesSistema.NAO);
				novoCliente.setIndicadorNegativacaoPeriodo(ConstantesSistema.NAO);
				novoCliente.setIndicadorUso(ConstantesSistema.SIM);
				novoCliente.setIndicadorValidaCpfCnpj(Integer.parseInt(ConstantesSistema.SIM.toString()));
				novoCliente.setUltimaAlteracao(new Date());
				
				if(clienteAtualizacaoCadastral.getIdProfissao() != null){
					Profissao profissao = new Profissao();
					profissao.setId(clienteAtualizacaoCadastral.getIdProfissao());
					novoCliente.setProfissao(profissao);
				}
				
				if(clienteAtualizacaoCadastral.getIdPessoaSexo() != null){
					PessoaSexo pessoaSexo = new PessoaSexo();
					pessoaSexo.setId(clienteAtualizacaoCadastral.getIdPessoaSexo());
					novoCliente.setPessoaSexo(pessoaSexo);
				}
				
				if(clienteAtualizacaoCadastral.getDsAbreviadaOrgaoExpedidorRg() != null){
					FiltroOrgaoExpedidorRg filtroOrgaoExpedidorRg = new FiltroOrgaoExpedidorRg();
					filtroOrgaoExpedidorRg.adicionarParametro(new ParametroSimples(FiltroOrgaoExpedidorRg.DESCRICAO_ABREVIADA, 
							clienteAtualizacaoCadastral.getDsAbreviadaOrgaoExpedidorRg()));
					
					Collection<?> colOrgaoExpedidor = getControladorUtil().pesquisar(filtroOrgaoExpedidorRg, 
							OrgaoExpedidorRg.class.getName());
					
					if(!Util.isVazioOrNulo(colOrgaoExpedidor)){
						OrgaoExpedidorRg orgaoExpedidorRg = (OrgaoExpedidorRg) Util.retonarObjetoDeColecao(colOrgaoExpedidor);
						novoCliente.setOrgaoExpedidorRg(orgaoExpedidorRg);
					}
				}
				
				if(clienteAtualizacaoCadastral.getDsUFSiglaOrgaoExpedidorRg() != null){
					FiltroUnidadeFederacao filtroUnidadeFederacao = new FiltroUnidadeFederacao();
					filtroUnidadeFederacao.adicionarParametro(new ParametroSimples(FiltroUnidadeFederacao.SIGLA, 
							clienteAtualizacaoCadastral.getDsUFSiglaOrgaoExpedidorRg()));
					
					Collection<?> colUFSigla = getControladorUtil().pesquisar(filtroUnidadeFederacao, 
							UnidadeFederacao.class.getName());
					
					if(!Util.isVazioOrNulo(colUFSigla)){
						UnidadeFederacao unidadeFederacao = (UnidadeFederacao) Util.retonarObjetoDeColecao(colUFSigla);
						novoCliente.setUnidadeFederacao(unidadeFederacao);
					}
				}
				
				Integer idCliente = (Integer) getControladorUtil().inserir(novoCliente);
				novoCliente.setId(idCliente);
				
				this.inserirClienteFoneDoArquivoAtualizacaoCadastral(clienteAtualizacaoCadastral.getId(), idCliente, usuarioLogado);
				this.inserirClienteEnderecoDoArquivoAtualizacaoCadastral(idImovel, novoCliente, usuarioLogado);
				this.inserirClienteImovelDoArquivoAtualizacaoCadastral(idCliente, idImovel, 
						clienteAtualizacaoCadastral, usuarioLogado);
			}else{
				this.inserirClienteFoneDoArquivoAtualizacaoCadastral(clienteAtualizacaoCadastral.getId(), 
						novoCliente.getId(), usuarioLogado);
				this.inserirClienteEnderecoDoArquivoAtualizacaoCadastral(idImovel, novoCliente, usuarioLogado);
				this.inserirClienteImovelDoArquivoAtualizacaoCadastral(novoCliente.getId(), idImovel, 
						clienteAtualizacaoCadastral, usuarioLogado);
			}
		}
	}

	private void inserirClienteEnderecoDoArquivoAtualizacaoCadastral( 
			Integer idImovel, Cliente cliente, Usuario usuarioLogado) throws ControladorException {
		
		if(idImovel != null){
			Imovel imovel = getControladorImovel().pesquisarImovel(idImovel);
			
			EnderecoTipo enderecoTipo = new EnderecoTipo();
			enderecoTipo.setId(new Integer("1"));
			
			ClienteEndereco clienteEndereco = new ClienteEndereco();
			clienteEndereco.setCliente(cliente);
			clienteEndereco.setEnderecoReferencia(imovel.getEnderecoReferencia());
			clienteEndereco.setEnderecoTipo(enderecoTipo);
			clienteEndereco.setNumero(imovel.getNumeroImovel());
			clienteEndereco.setComplemento(imovel.getComplementoEndereco());
			clienteEndereco.setLogradouroCep(imovel.getLogradouroCep());
			clienteEndereco.setLogradouroBairro(imovel.getLogradouroBairro());
			clienteEndereco.setIndicadorEnderecoCorrespondencia(ConstantesSistema.SIM);
			clienteEndereco.setPerimetroInicial(imovel.getPerimetroInicial());
			clienteEndereco.setPerimetroFinal(imovel.getPerimetroFinal());
			clienteEndereco.setLogradouro(imovel.getLogradouro());
			clienteEndereco.setBairro(imovel.getBairro());
			clienteEndereco.setCep(imovel.getCep());
			clienteEndereco.setUltimaAlteracao(new Date());
			
			Integer idClienteEndereco = (Integer) getControladorUtil().inserir(clienteEndereco);
			clienteEndereco.setId(idClienteEndereco);
		}
	}

	private void inserirClienteFoneDoArquivoAtualizacaoCadastral(Integer idClienteAtualizacaoCadastral, Integer idCliente, Usuario usuarioLogado) throws ControladorException {
		if(idClienteAtualizacaoCadastral != null){
			FiltroClienteFoneAtualizacaoCadastral filtro = new FiltroClienteFoneAtualizacaoCadastral();
			filtro.adicionarParametro(new ParametroSimples(FiltroClienteFoneAtualizacaoCadastral.
					ID_CLIENTE_ATUALIZACAO_CADASTRAL, idClienteAtualizacaoCadastral));
			
			Collection<?> colClienteFone = this.getControladorUtil().pesquisar(filtro, ClienteFoneAtualizacaoCadastral.class.getName());
			
			if(!Util.isVazioOrNulo(colClienteFone)){
				ClienteFoneAtualizacaoCadastral clienteFoneAtualizacaoCadastral = (ClienteFoneAtualizacaoCadastral) 
						Util.retonarObjetoDeColecao(colClienteFone);
				
				ClienteFone novoClienteFone = new ClienteFone();
				
				Cliente cliente = new Cliente();
				cliente.setId(idCliente);
				
				novoClienteFone.setCliente(cliente);
				novoClienteFone.setDdd(clienteFoneAtualizacaoCadastral.getDdd());
				novoClienteFone.setTelefone(Util.truncarString(clienteFoneAtualizacaoCadastral.getTelefone(), 8));
				novoClienteFone.setRamal(clienteFoneAtualizacaoCadastral.getRamal());
				novoClienteFone.setIndicadorTelefonePadrao(clienteFoneAtualizacaoCadastral.getIndicadorFonePadrao());
				novoClienteFone.setContato(null);
				novoClienteFone.setUltimaAlteracao(new Date());
				
				if(clienteFoneAtualizacaoCadastral.getIdFoneTipo() != null){
					FoneTipo foneTipo = new FoneTipo();
					foneTipo.setId(clienteFoneAtualizacaoCadastral.getIdFoneTipo());
					novoClienteFone.setFoneTipo(foneTipo);
				}
				
				Integer idClienteFone = (Integer) getControladorUtil().inserir(novoClienteFone);
				novoClienteFone.setId(idClienteFone);
				
			}
		}
	}
	
	private void inserirClienteImovelDoArquivoAtualizacaoCadastral(Integer idCliente, Integer idImovel, 
			ClienteAtualizacaoCadastral clienteAtualizacaoCadastral, Usuario usuarioLogado) throws ControladorException {
		
		if(clienteAtualizacaoCadastral != null && idImovel != null){
			Imovel imovel = new Imovel();
			imovel.setId(idImovel);
			
			Cliente cliente = new Cliente();
			cliente.setId(idCliente);
			
			ClienteImovel clienteImovel = new ClienteImovel();
			clienteImovel.setCliente(cliente);
			clienteImovel.setImovel(imovel);
			clienteImovel.setDataInicioRelacao(new Date());
			clienteImovel.setDataFimRelacao(null);
			clienteImovel.setClienteImovelFimRelacaoMotivo(null);
			clienteImovel.setUltimaAlteracao(new Date());
			
			if(clienteAtualizacaoCadastral.getIdClienteRelacaoTipo() != null){
				Integer idClienteRelacaoTipo = clienteAtualizacaoCadastral.getIdClienteRelacaoTipo();
				ClienteRelacaoTipo clienteRelacaoTipo = new ClienteRelacaoTipo(idClienteRelacaoTipo);
				clienteImovel.setClienteRelacaoTipo(clienteRelacaoTipo);
				
				if(String.valueOf(idClienteRelacaoTipo).equals("2")){
					clienteImovel.setIndicadorNomeConta(new Short("1"));
				}else{
					clienteImovel.setIndicadorNomeConta(new Short("2"));
				}
			}
			
			Integer idClienteImovel = (Integer) getControladorUtil().inserir(clienteImovel);
			clienteImovel.setId(idClienteImovel);
			
		}
	}
	
	private void inserirImovelSubcategoriaDoArquivoAtualizacaoCadastral(String idImovelAtualizacaoCadastral, 
			Integer idImovel, Usuario usuarioLogado) throws ControladorException {
		
		FiltroImovelSubcategoriaAtualizacaoCadastral filtro = new FiltroImovelSubcategoriaAtualizacaoCadastral();
		filtro.adicionarParametro(new ParametroSimples(FiltroImovelSubcategoriaAtualizacaoCadastral.
				ID_IMOVEL_ATUALIZACAO_CADASTRAL, idImovelAtualizacaoCadastral));
		
		Collection<?> colImovelSubcategoria = getControladorUtil().pesquisar(filtro, ImovelSubcategoriaAtualizacaoCadastral.class.getName());
		
		if(!Util.isVazioOrNulo(colImovelSubcategoria)){
			ImovelSubcategoriaAtualizacaoCadastral imovelSubcategoriaAtualizacaoCadastral = (ImovelSubcategoriaAtualizacaoCadastral)
					Util.retonarObjetoDeColecao(colImovelSubcategoria);
			
			Imovel imovel = new Imovel(idImovel);

			ImovelSubcategoriaPK imovelSubcategoriaPk = new ImovelSubcategoriaPK();
			imovelSubcategoriaPk.setImovel(imovel);
			
			if(imovelSubcategoriaAtualizacaoCadastral.getIdSubcategoria() != null){
				Subcategoria subCategoria = new Subcategoria();
				subCategoria.setId(imovelSubcategoriaAtualizacaoCadastral.getIdSubcategoria());
				imovelSubcategoriaPk.setSubcategoria(subCategoria);
			}
			
			ImovelSubcategoria imovelSubcategoria = new ImovelSubcategoria();
			imovelSubcategoria.setComp_id(imovelSubcategoriaPk);
			imovelSubcategoria.setQuantidadeEconomias(imovelSubcategoriaAtualizacaoCadastral.getQuantidadeEconomias());
			imovelSubcategoria.setUltimaAlteracao(new Date());
			
			getControladorUtil().inserir(imovelSubcategoria);
			
		}
	}
	
	private void inserirLigacaoAguaEsgotoAtualizacaoCadastral(String idImovelAtualizacaoCadastral, 
			Integer idImovel, Usuario usuario) throws ControladorException {
		
		if(idImovelAtualizacaoCadastral != null && !idImovelAtualizacaoCadastral.equals("") 
					&& idImovel != null && idImovel != 0){
			
			FiltroImovelAtualizacaoCadastral filtroImovelAtualizacaoCadastral = new FiltroImovelAtualizacaoCadastral();
			filtroImovelAtualizacaoCadastral.adicionarParametro(new ParametroSimples(
					FiltroImovelAtualizacaoCadastral.ID, idImovelAtualizacaoCadastral));
			filtroImovelAtualizacaoCadastral.adicionarCaminhoParaCarregamentoEntidade(
					FiltroImovelAtualizacaoCadastral.LIGACAO_AGUA_DIAMETRO);
			filtroImovelAtualizacaoCadastral.adicionarCaminhoParaCarregamentoEntidade(
					FiltroImovelAtualizacaoCadastral.LIGACAO_AGUA_MATERIAL);
			filtroImovelAtualizacaoCadastral.adicionarCaminhoParaCarregamentoEntidade(
					FiltroImovelAtualizacaoCadastral.LIGACAO_AGUA_PERFIL);
			filtroImovelAtualizacaoCadastral.adicionarCaminhoParaCarregamentoEntidade(
					FiltroImovelAtualizacaoCadastral.LIGACAO_ESGOTO_DIAMETRO);
			filtroImovelAtualizacaoCadastral.adicionarCaminhoParaCarregamentoEntidade(
					FiltroImovelAtualizacaoCadastral.LIGACAO_ESGOTO_MATERIAL);
			filtroImovelAtualizacaoCadastral.adicionarCaminhoParaCarregamentoEntidade(
					FiltroImovelAtualizacaoCadastral.LIGACAO_ESGOTO_PERFIL);
			
			Collection<?> colImovelAtualizacaoCadastral = getControladorUtil().pesquisar(
					filtroImovelAtualizacaoCadastral, ImovelAtualizacaoCadastral.class.getName());
			
			if(!Util.isVazioOrNulo(colImovelAtualizacaoCadastral)){
				ImovelAtualizacaoCadastral imovelAtualizacaoCadastral = (ImovelAtualizacaoCadastral)
						Util.retonarObjetoDeColecao(colImovelAtualizacaoCadastral);
				
				if(imovelAtualizacaoCadastral.getLigacaoAguaPerfil() != null && imovelAtualizacaoCadastral.getLigacaoEsgotoPerfil() != null){
				
					if(imovelAtualizacaoCadastral.getIndicadorHidrometro().equals(ConstantesSistema.NAO)){
						LigacaoAgua ligacaoAgua = new LigacaoAgua();
						ligacaoAgua.setId(idImovel);
						ligacaoAgua.setImovel(new Imovel(idImovel));
						ligacaoAgua.setDataLigacao(imovelAtualizacaoCadastral.getDataLigacaoAgua());
						ligacaoAgua.setLigacaoAguaDiametro(imovelAtualizacaoCadastral.getLigacaoAguaDiametro());
						ligacaoAgua.setLigacaoAguaMaterial(imovelAtualizacaoCadastral.getLigacaoAguaMaterial());
						ligacaoAgua.setLigacaoAguaPerfil(imovelAtualizacaoCadastral.getLigacaoAguaPerfil());
						ligacaoAgua.setUltimaAlteracao(new Date());
						
						getControladorUtil().inserir(ligacaoAgua);
						
						LigacaoEsgoto ligacaoEsgoto = new LigacaoEsgoto();
						ligacaoEsgoto.setId(idImovel);
						ligacaoEsgoto.setImovel(new Imovel(idImovel));
						ligacaoEsgoto.setDataLigacao(imovelAtualizacaoCadastral.getDataLigacaoEsgoto());
						ligacaoEsgoto.setLigacaoEsgotoDiametro(imovelAtualizacaoCadastral.getLigacaoEsgotoDiametro());
						ligacaoEsgoto.setLigacaoEsgotoMaterial(imovelAtualizacaoCadastral.getLigacaoEsgotoMaterial());
						ligacaoEsgoto.setLigacaoEsgotoPerfil(imovelAtualizacaoCadastral.getLigacaoEsgotoPerfil());
						ligacaoEsgoto.setPercentualAguaConsumidaColetada(imovelAtualizacaoCadastral.getPercentualColeta());
						ligacaoEsgoto.setPercentual(BigDecimal.ZERO);
						ligacaoEsgoto.setIndicadorCaixaGordura(ConstantesSistema.NAO);
						ligacaoEsgoto.setIndicadorLigacaoEsgoto(ConstantesSistema.SIM);
						ligacaoEsgoto.setUltimaAlteracao(new Date());
						
						getControladorUtil().inserir(ligacaoEsgoto);
					}else{
						//Gerar Ordem de Serviço
						ServicoTipo servicoTipo = new ServicoTipo();
						
						//Verificar Código Constante
						servicoTipo.setId(95);
						
						OrdemServico ordemServico = new OrdemServico();
						ordemServico.setServicoTipo(servicoTipo);
						
						Imovel imovel = new Imovel();
						imovel.setId(idImovel);
						
						UnidadeOrganizacional unidadeOrganizacional = usuario.getUnidadeOrganizacional();
						
						getControladorOrdemServico().gerarOrdemServicoSeletiva(
								ordemServico, unidadeOrganizacional, imovel, usuario);
					}
				}
			}
		}
	}
	
	/**
	 * [UC1537] - Gerar Relatório Clientes com CPF CNPJ Validados e Informado no Mobile
	 * @author Diogo Luiz
	 * @date 21/08/2013
	 * 
	 * @param idArquivo
	 * @return boolean
	 */
	
	public boolean pesquisarExistenciaIdentificadorArquivo(String idArquivo){	
		
		boolean verificarIdArquivo = false;
		Integer idIdentArquivo = Integer.parseInt(idArquivo);
		Collection colecaoArquivo = null;
		
		if(idIdentArquivo != null && !idIdentArquivo.equals("")){
			try {
				colecaoArquivo = this.repositorioTransacao.pesquisarExistenciaIdentificadorArquivo(idIdentArquivo);
			} catch (ErroRepositorioException e) {				
				e.printStackTrace();
			}
		}
		
		if(!colecaoArquivo.isEmpty() && colecaoArquivo != null){
			verificarIdArquivo = true;
		}		
		
		return verificarIdArquivo;
		
	}
	
	
	/**
	 * [UC1537] - Gerar Relatório Clientes com CPF CNPJ Validados e Informado no Mobile
	 * @author Diogo Luiz
	 * @date 22/08/2013
	 * 
	 * @return boolean
	 */
	
	public boolean pesquisarExistenciaDados(String idEmpresa,
			String periodoInicial, String periodoFinal, String idLeiturista){
		
		boolean verificarArquivo = false;
		String empresa = idEmpresa;
		Collection colecaoArquivo = null;		
		
		if((empresa != null && !empresa.equals("")) && (periodoInicial != null && !periodoInicial.equals(""))
				&& (periodoFinal != null && !periodoFinal.equals(""))){
			
			try {
				colecaoArquivo = this.repositorioTransacao.pesquisarExistenciaDados(idEmpresa, periodoInicial, periodoFinal, idLeiturista);
			} catch (ErroRepositorioException e) {				
				e.printStackTrace();
			}
			
		}else{
			throw new ActionServletException("atencao.dados.vazio");
		}		
		
		if(colecaoArquivo != null && !colecaoArquivo.equals("")){
			verificarArquivo = true;
		}
		
		return verificarArquivo;
	}

	
	
	/**
	 * [UC1537] - Gerar Relatório Clientes com CPF CNPJ Validados e Informado no Mobile
	 * @author Diogo Luiz
	 * @date 22/08/2013
	 * 	  
	 */
	public Collection<GerarRelatorioClienteCpfCnpjValidadosHelper> gerarRelatorioClienteCpfCnpjValidados(String idArquivo)
	throws ControladorException{
		Integer idIdentArquivo = Integer.parseInt(idArquivo);
		Collection colecaoArquivo = null;
		Collection <GerarRelatorioClienteCpfCnpjValidadosHelper> colecaoHelper = null; 
		GerarRelatorioClienteCpfCnpjValidadosHelper helper = null;
		
		if(idIdentArquivo != null && !idIdentArquivo.equals("")){
			try {
				colecaoArquivo = this.repositorioTransacao.gerarRelatorioClienteCpfCnpjValidados(idIdentArquivo);
				
				if(!Util.isVazioOrNulo(colecaoArquivo)){
					colecaoHelper = new ArrayList<GerarRelatorioClienteCpfCnpjValidadosHelper>();
					
					Iterator it = colecaoArquivo.iterator();
					
					while(it.hasNext()){
						helper = new GerarRelatorioClienteCpfCnpjValidadosHelper();
						Object[] array = (Object[]) it.next();
						
						if((Integer) array[0] != null){
							helper.setCodCliente((Integer) array[0]);
						}
						
						if((String) array[1] != null){
							helper.setClieGsan((String) array[1]);
							helper.setClieMobile((String) array[1]);
						}
						
						if((String) array[2] != null){
							helper.setValorAtual((String) array[2]);
						}
						
						if((String) array[3] != null){
							helper.setValorAnterior((String) array[3]);
						}
						
						colecaoHelper.add(helper);
					}					
				}				
			} catch (ErroRepositorioException e) {				
				e.printStackTrace();
				throw new ControladorException("erro.sistema", e);
			}
		}
		return colecaoHelper;		
	}	
	/**
	 * [UC1537] - Gerar Relatório Clientes com CPF CNPJ Validados e Informado no Mobile
	 * @author Diogo Luiz
	 * @date 22/08/2013
	 * 	  
	 */	
	public Collection<GerarRelatorioClienteCpfCnpjValidadosHelper> gerarRelatorioClienteCpfCnpjValidados(String idEmpresa, 
			String periodoInicial, String periodoFinal, String idLeiturista)
			throws ControladorException{
				
				Collection colecaoArquivo = null;
				Collection <GerarRelatorioClienteCpfCnpjValidadosHelper> colecaoHelper = null; 
				GerarRelatorioClienteCpfCnpjValidadosHelper helper = null;				
				
					try {
						colecaoArquivo = this.repositorioTransacao.gerarRelatorioClienteCpfCnpjValidados(idEmpresa, 
								periodoInicial, periodoFinal, idLeiturista);
						
						if(!Util.isVazioOrNulo(colecaoArquivo)){
							colecaoHelper = new ArrayList<GerarRelatorioClienteCpfCnpjValidadosHelper>();
							
							Iterator it = colecaoArquivo.iterator();
							
							while(it.hasNext()){
								helper = new GerarRelatorioClienteCpfCnpjValidadosHelper();
								Object[] array = (Object[]) it.next();
								
								if((Integer) array[0] != null){
									helper.setCodCliente((Integer) array[0]);
								}
								
								if((String) array[1] != null){
									helper.setClieGsan((String) array[1]);
									helper.setClieMobile((String) array[1]);
								}
								
								if((String) array[2] != null){
									helper.setValorAtual((String) array[2]);
								}
								
								if((String) array[3] != null){
									helper.setValorAnterior((String) array[3]);
								}
								
								colecaoHelper.add(helper);
							}					
						}				
					} catch (ErroRepositorioException e) {				
						e.printStackTrace();
						throw new ControladorException("erro.sistema", e);
					}
				
				return colecaoHelper;		
			}

	/**
	 * @author André Miranda
	 * @since 13/10/2014
	 * 
	 * @param colecaoHelper
	 * @param tabelaLinhaAlteracao
	 * @param idAlteracaoTipo
	 * @return
	 */
	private Collection pesquisarTabelaLinhaColunaAlteracaoDadosCadastrais(Collection<ParametrosTransacaoBatchHelper> colecaoHelper, 
			TabelaLinhaAlteracao tabelaLinhaAlteracao, Integer idAlteracaoTipo) {
		Collection<TabelaLinhaColunaAlteracao> colecaoRetorno = new ArrayList<TabelaLinhaColunaAlteracao>();

		for (ParametrosTransacaoBatchHelper help : colecaoHelper) {
			int idTabelaColuna = Integer.valueOf(help.getIdTabelaColuna());

			TabelaColuna tabelaColuna = new TabelaColuna();
			tabelaColuna.setId(idTabelaColuna);

			TabelaLinhaColunaAlteracao tabelaLinhaColunaAlteracao = new TabelaLinhaColunaAlteracao();

	    	tabelaLinhaColunaAlteracao.setConteudoColunaAtual(help.getValorNovo());
	    	tabelaLinhaColunaAlteracao.setConteudoColunaAnterior(help.getValorAnterior());
		    tabelaLinhaColunaAlteracao.setIndicadorAtualizada(new Integer(1).shortValue());
		    tabelaLinhaColunaAlteracao.setTabelaColuna(tabelaColuna);
		    tabelaLinhaColunaAlteracao.setTabelaLinhaAlteracao(tabelaLinhaAlteracao);

		    colecaoRetorno.add(tabelaLinhaColunaAlteracao);
		}

		return colecaoRetorno;
	}

	/**
	 * @author André Miranda
	 * @since 13/10/2014
	 * 
	 * @param usuarioAcaoUsuarioHelper
	 * @param operacaoEfetuada
	 * @param tabelaLinhaAlteracao
	 * @param tabelaLinhaColunaAlteracoes
	 * @throws ControladorException
	 */
	public void inserirOperacaoEfetuadaDadosCadastrais(UsuarioAcaoUsuarioHelper usuarioAcaoUsuarioHelper,
			OperacaoEfetuada operacaoEfetuada, TabelaLinhaAlteracao tabelaLinhaAlteracao,
			Collection<TabelaLinhaColunaAlteracao> tabelaLinhaColunaAlteracoes)
			throws ControladorException {

		// caso a operacaoEfetuada for nula
		// Nao ocorre pq o intercepto levanta execao caso nao tenha
		if (operacaoEfetuada == null) {
			operacaoEfetuada = new OperacaoEfetuada();
		}

		// caso nao tenha id é pq nao foi adicionado
		if (operacaoEfetuada.getId() == null) {
			operacaoEfetuada.setUltimaAlteracao(new Date());
			Integer idOperacaoEfetuada = (Integer) getControladorUtil().inserirOuAtualizar(operacaoEfetuada);
			operacaoEfetuada.setId(idOperacaoEfetuada);

			if (usuarioAcaoUsuarioHelper != null) {
				UsuarioAlteracao usuarioAteracao = new UsuarioAlteracao();
				usuarioAteracao.setOperacaoEfetuada(operacaoEfetuada);
				usuarioAteracao.setUltimaAlteracao(new Date());
				usuarioAteracao.setUsuario(usuarioAcaoUsuarioHelper.getUsuario());
				usuarioAteracao.setUsuarioAcao(usuarioAcaoUsuarioHelper.getUsuarioAcao());
				usuarioAteracao.setEmpresa(usuarioAcaoUsuarioHelper.getUsuario().getEmpresa());
				getControladorUtil().inserir(usuarioAteracao);
			}
		}

		if (tabelaLinhaAlteracao != null) {
			tabelaLinhaAlteracao.setUltimaAlteracao(new Date());
			tabelaLinhaAlteracao.setOperacaoEfetuada(operacaoEfetuada);
			getControladorUtil().inserir(tabelaLinhaAlteracao);

			FiltroOperacaoTabela filtro = new FiltroOperacaoTabela();
			filtro.adicionarParametro(new ParametroSimples(FiltroOperacaoTabela.TABELA_ID,
					tabelaLinhaAlteracao.getTabela().getId()));
			filtro.adicionarParametro(new ParametroSimples(FiltroOperacaoTabela.OPERACAO_ID,
					operacaoEfetuada.getOperacao().getId()));
			Collection coll = getControladorUtil().pesquisar(filtro, OperacaoTabela.class.getSimpleName());

			if (coll == null || coll.isEmpty()) {
				OperacaoTabelaPK pk = new OperacaoTabelaPK();
				pk.setOperacaoId(operacaoEfetuada.getOperacao().getId());
				pk.setTabelaId(tabelaLinhaAlteracao.getTabela().getId());

				OperacaoTabela operacaoTabela = new OperacaoTabela();
				operacaoTabela.setComp_id(pk);
				operacaoTabela.setOperacao(operacaoEfetuada.getOperacao());
				operacaoTabela.setTabela(tabelaLinhaAlteracao.getTabela());
				getControladorUtil().inserir(operacaoTabela);
			}

			if (tabelaLinhaColunaAlteracoes != null) {
				for (TabelaLinhaColunaAlteracao tabelaLinha : tabelaLinhaColunaAlteracoes) {
					tabelaLinha.setTabelaLinhaAlteracao(tabelaLinhaAlteracao);
					tabelaLinha.setUltimaAlteracao(new Date());
					getControladorUtil().inserir(tabelaLinha);
						
				}
			}
		}
	}
	
	/**
	 * Metodo responsável por registrar transações durante o processo bacth.
	 * 
	 * @author André Miranda
	 * @since 13/10/2014
	 * 
	 * @param usuario
	 * @param helper
	 * @param operacaoEfetuada
	 * @throws ControladorException
	 */
	public void processaRegistroOperacaoDadosCadastrais(UsuarioAcaoUsuarioHelper usuario, DadosCadastraisTransacaoBatchHelper helper, 
			OperacaoEfetuada operacaoEfetuada) throws ControladorException {
		
		for (TipoAlteracaoTransacaoBatchHelper tipoAlteracaoHelper : helper.getColecaoTipoAlteracaoTransacao()) {
			if ( tipoAlteracaoHelper.getColecaoParametrosTransacao() == null || tipoAlteracaoHelper.getColecaoParametrosTransacao().isEmpty() ) {
				continue;
			}
			
			AlteracaoTipo alteracaoTipo = new AlteracaoTipo();
			alteracaoTipo.setId(tipoAlteracaoHelper.getTipoAlteracao());
			
			Tabela tabela = new Tabela();
			tabela.setId(tipoAlteracaoHelper.getIdTabela());
			
			TabelaLinhaAlteracao tabelaLinhaAlteracao = new TabelaLinhaAlteracao();
			tabelaLinhaAlteracao.setAlteracaoTipo(alteracaoTipo);
			tabelaLinhaAlteracao.setTabela(tabela);

		    Collection<TabelaLinhaColunaAlteracao> colecaoTabelaLinhaColunaAlteracao =
		    		pesquisarTabelaLinhaColunaAlteracaoDadosCadastrais(tipoAlteracaoHelper.getColecaoParametrosTransacao(), 
					tabelaLinhaAlteracao,  alteracaoTipo.getId());
		    
		    if (colecaoTabelaLinhaColunaAlteracao != null && !colecaoTabelaLinhaColunaAlteracao.isEmpty()){
		    	try {
		    		inserirOperacaoEfetuadaDadosCadastrais(usuario, operacaoEfetuada, tabelaLinhaAlteracao, colecaoTabelaLinhaColunaAlteracao);
				} catch (ControladorException e) {
					e.printStackTrace();
				}
		    }
		}
	}
}