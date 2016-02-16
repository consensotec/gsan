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
package gcom.mobile.execucaoordemservico;

import gcom.arrecadacao.ControladorArrecadacaoLocal;
import gcom.arrecadacao.ControladorArrecadacaoLocalHome;
import gcom.arrecadacao.IRepositorioArrecadacao;
import gcom.arrecadacao.RepositorioArrecadacaoHBM;
import gcom.atendimentopublico.ControladorAtendimentoPublicoLocal;
import gcom.atendimentopublico.ControladorAtendimentoPublicoLocalHome;
import gcom.atendimentopublico.IRepositorioAtendimentoPublico;
import gcom.atendimentopublico.RepositorioAtendimentoPublicoHBM;
import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ligacaoagua.ControladorLigacaoAguaLocal;
import gcom.atendimentopublico.ligacaoagua.ControladorLigacaoAguaLocalHome;
import gcom.atendimentopublico.ligacaoagua.CorteTipo;
import gcom.atendimentopublico.ligacaoagua.FiltroCorteTipo;
import gcom.atendimentopublico.ligacaoagua.FiltroMotivoCorte;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.MotivoCorte;
import gcom.atendimentopublico.ligacaoagua.bean.DadosEfetuacaoCorteLigacaoAguaHelper;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gcom.atendimentopublico.ordemservico.ControladorOrdemServicoLocal;
import gcom.atendimentopublico.ordemservico.ControladorOrdemServicoLocalHome;
import gcom.atendimentopublico.ordemservico.FiltroFiscalizacaoSituacao;
import gcom.atendimentopublico.ordemservico.FiscalizacaoSituacao;
import gcom.atendimentopublico.ordemservico.IRepositorioOrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoFoto;
import gcom.atendimentopublico.ordemservico.RepositorioOrdemServicoHBM;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoBoletim;
import gcom.atendimentopublico.ordemservico.bean.AgenteComercialHelper;
import gcom.atendimentopublico.ordemservico.bean.SituacaoEncontradaHelper;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.ControladorClienteLocal;
import gcom.cadastro.cliente.ControladorClienteLocalHome;
import gcom.cadastro.cliente.FiltroOrgaoExpedidorRg;
import gcom.cadastro.cliente.OrgaoExpedidorRg;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.endereco.ControladorEnderecoLocal;
import gcom.cadastro.endereco.ControladorEnderecoLocalHome;
import gcom.cadastro.geografico.FiltroUnidadeFederacao;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.ControladorImovelLocalHome;
import gcom.cadastro.imovel.FiltroPocoTipo;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.PocoTipo;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.ControladorCobrancaLocal;
import gcom.cobranca.ControladorCobrancaLocalHome;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.faturamento.ControladorFaturamentoLocal;
import gcom.faturamento.ControladorFaturamentoLocalHome;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.IRepositorioFaturamento;
import gcom.faturamento.RepositorioFaturamentoHBM;
import gcom.micromedicao.ControladorMicromedicaoLocal;
import gcom.micromedicao.ControladorMicromedicaoLocalHome;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.Rota;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.micromedicao.hidrometro.FiltroHidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.FiltroHidrometroProtecao;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.HidrometroProtecao;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.mobile.execucaoordemservico.bean.ArquivoTxtOSCobrancaSmartphoneHelper;
import gcom.mobile.execucaoordemservico.bean.DadosDebitoOSCobrancaSmartphoneHelper;
import gcom.mobile.execucaoordemservico.bean.GerarArquivoTxtSmartphoneHelper;
import gcom.mobile.execucaoordemservico.bean.OrdemServicoCobrancaSmartphoneHelper;
import gcom.relatorio.mobile.execucaoOrdemServico.RelatorioErrosEncerramentoOSCobrancaBean;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.parametrosistema.ParametroSistema;
import gcom.util.ConstantesAplicacao;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ControladorUtilLocalHome;
import gcom.util.ErroRepositorioException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import org.apache.commons.io.FileUtils;

/**
 * Definição da lógica de negócio do Session Bean de ControladorExecucaoOrdemServico
 * 
 * @author André Miranda
 * @date 13/11/2015
 */
public class ControladorExecucaoOrdemServicoSEJB implements SessionBean {
	private static final long serialVersionUID = 1L;

	protected SessionContext sessionContext;

	protected IRepositorioFaturamento repositorioFaturamento;
	protected IRepositorioArrecadacao repositorioArrecadacao;
	protected IRepositorioOrdemServico repositorioOrdemServico;
	protected IRepositorioExecucaoOrdemServico repositorioExecucaoOrdemServico;
	protected IRepositorioAtendimentoPublico repositorioAtendimentoPublico;
	
	private static final boolean DESCARTARDADOSDUPLICADOSOSCOBRANCASMARTPHONE = true;
	
	public void ejbCreate() throws CreateException {
		repositorioFaturamento = RepositorioFaturamentoHBM.getInstancia();
		repositorioArrecadacao = RepositorioArrecadacaoHBM.getInstancia();
		repositorioExecucaoOrdemServico = RepositorioExecucaoOrdemServicoHBM.getInstancia();
		repositorioOrdemServico = RepositorioOrdemServicoHBM.getInstancia();
		repositorioAtendimentoPublico = RepositorioAtendimentoPublicoHBM.getInstancia();
	}

	public void ejbRemove() { }

	public void ejbActivate() { }

	public void ejbPassivate() { }

	/**
	 * Seta o valor de sessionContext
	 * @param sessionContext O novo valor de sessionContext
	 */
	public void setSessionContext(SessionContext sessionContext) {
		this.sessionContext = sessionContext;
	}

	/**
	 * Retorna o ControladorUtil
	 */
	private ControladorUtilLocal getControladorUtil() {
		try {
			ControladorUtilLocalHome localHome = (ControladorUtilLocalHome)
					ServiceLocator.getInstancia()
					.getLocalHome(ConstantesJNDI.CONTROLADOR_UTIL_SEJB);
			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	/**
	 * Retorna o ControladorCliente
	 */
	private ControladorClienteLocal getControladorCliente() {
		try {
			ControladorClienteLocalHome localHome = (ControladorClienteLocalHome)
					ServiceLocator.getInstancia()
					.getLocalHome(ConstantesJNDI.CONTROLADOR_CLIENTE_SEJB);
			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	/**
	 * Retorna o ControladorEndereco
	 */
	private ControladorEnderecoLocal getControladorEndereco() {
		try {
			ControladorEnderecoLocalHome localHome = (ControladorEnderecoLocalHome)
					ServiceLocator.getInstancia()
					.getLocalHome(ConstantesJNDI.CONTROLADOR_ENDERECO_SEJB);
			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	/**
	 * Retorna o ControladorImovel
	 */
	private ControladorImovelLocal getControladorImovel() {
		try {
			ControladorImovelLocalHome localHome = (ControladorImovelLocalHome)
					ServiceLocator.getInstancia()
					.getLocalHome(ConstantesJNDI.CONTROLADOR_IMOVEL_SEJB);
			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	/**
	 * Retorna o ControladorFaturamento
	 */
	private ControladorFaturamentoLocal getControladorFaturamento() {
		try {
			ControladorFaturamentoLocalHome localHome = (ControladorFaturamentoLocalHome)
					ServiceLocator.getInstancia()
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FATURAMENTO_SEJB);
			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	/**
	 * Retorna o ControladorCobranca
	 */
	private ControladorCobrancaLocal getControladorCobranca() {
		try {
			ControladorCobrancaLocalHome localHome = (ControladorCobrancaLocalHome)
					ServiceLocator.getInstancia()
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_COBRANCA_SEJB);
			return localHome.create();
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	protected ControladorMicromedicaoLocal getControladorMicromedicao() {
		ControladorMicromedicaoLocalHome localHome = null;
		ControladorMicromedicaoLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorMicromedicaoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_MICROMEDICAO_SEJB);
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
	private ControladorOrdemServicoLocal getControladorOrdemServico() {

		ControladorOrdemServicoLocalHome localHome = null;
		ControladorOrdemServicoLocal local = null;

		// pega a instância do ServiceLocator.
		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorOrdemServicoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_ORDEM_SERVICO_SEJB);
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
	
	private ControladorLigacaoAguaLocal getControladorLigacaoAgua() {
		ControladorLigacaoAguaLocalHome localHome = null;
		ControladorLigacaoAguaLocal local = null;

		ServiceLocator locator = null;
		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorLigacaoAguaLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_LIGACAO_AGUA_SEJB);
			local = localHome.create();
			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	/**
	 * Retorna o valor de controladorAtendimentoPublico
	 * 
	 * @return O valor de controladorAtendimentoPublico
	 */
	protected ControladorAtendimentoPublicoLocal getControladorAtendimentoPublico() {

		ControladorAtendimentoPublicoLocalHome localHome = null;
		ControladorAtendimentoPublicoLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorAtendimentoPublicoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_ATENDIMENTO_PUBLICO_SEJB);
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

	protected ControladorArrecadacaoLocal getControladorArrecadacao() {
		ControladorArrecadacaoLocalHome localHome = null;
		ControladorArrecadacaoLocal local = null;

		// pega a inst?ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorArrecadacaoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_ARRECADACAO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas ?
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
	 * [UC1497] - Gerar Arquivo Texto de Ordens de Servico para Smartphone
	 *  
	 * @author André Miranda
	 * @date 13/11/2015
	 */
	public Collection<Empresa> validarEmpresaPrincipal(Usuario usuario) throws ControladorException {
		Collection<Empresa> colecaoEmpresa = new ArrayList<Empresa>();

		if (!usuario.getEmpresa().getIndicadorEmpresaPrincipal().equals(Empresa.INDICADOR_EMPRESA_PRINCIPAL)) {
			colecaoEmpresa.add(usuario.getEmpresa());
			return colecaoEmpresa;
		}

		FiltroEmpresa filtro = new FiltroEmpresa(FiltroEmpresa.DESCRICAO);
		filtro.setConsultaSemLimites(true);
		filtro.adicionarParametro(new ParametroSimples(FiltroEmpresa.INDICADORUSO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		colecaoEmpresa = this.getControladorUtil().pesquisar(filtro, Empresa.class);

		if (Util.isVazioOrNulo(colecaoEmpresa)) {
			throw new ControladorException("atencao.entidade_sem_dados_para_selecao", null, "EMPRESA");
		}

		return colecaoEmpresa;
	}
	
	/**
	 * [UC1498] - Consultar Arquivo Texto de Ordens de Serviço para Smartphone (Novo)
	 *
	 * @author Jean Varela
	 * @throws ErroRepositorioException 
	 * @date   16/11/2015	
	 */
	public Collection<ArquivoTxtOSCobrancaSmartphoneHelper> buscarColecaoArquivoTextoOSCobrancaSmartphone(Integer idEmpresa, Integer idTipoServico, 
							Integer referenciaCronograma, Integer idCobrancaGrupo, Integer[] idsLocalidade,
							Integer idComandoEventual,Integer idAgenteComercial,Integer idSituacao)  throws  ControladorException{
		
		try{
			Collection<Object[]> colecao =repositorioExecucaoOrdemServico.consultarDadosArquivoTextoOSCobranca(idEmpresa, idTipoServico, referenciaCronograma, idCobrancaGrupo, idsLocalidade, idComandoEventual,idAgenteComercial,idSituacao);
			List<ArquivoTxtOSCobrancaSmartphoneHelper> arquivos = new ArrayList<ArquivoTxtOSCobrancaSmartphoneHelper>();
			
			if (!Util.isVazioOrNulo(colecao)) {
				Iterator<Object[] > iterator = colecao.iterator();
				while (iterator.hasNext()) {					
					Object[] array = (Object[]) iterator.next();
					
					ArquivoTxtOSCobrancaSmartphoneHelper arquivo = new ArquivoTxtOSCobrancaSmartphoneHelper();

					arquivo.setIdLocalidade((Integer) array[0]);
					arquivo.setIdSetor((Integer) array[1]);
					arquivo.setIdRota((Integer) array[2]);
					arquivo.setQtdOrdemServico((Integer) array[3]);
					arquivo.setNomeCliente((String) array[4]);
					arquivo.setSituacao((String) array[5]);
					arquivo.setDataLiberacao((Date) array[6]);
					arquivo.setIdArquivo((Integer) array[7]);
					arquivo.setIdSituacao((Integer) array[8]);
					arquivo.setImei((Long) array[9]);
					arquivo.setIdLeiturista((Integer) array[10]);	
					arquivo.setNomeFuncionario((String) array[11]);
					arquivo.setNnQuadra((Integer) array[12]);
					
					Integer quantidadeOSRecebida = repositorioExecucaoOrdemServico.pesquisarQtdeExecucaoOSOrdemServico(arquivo.getIdArquivo());
					arquivo.setQuantidadeOSRecebida(quantidadeOSRecebida);
					
					if (arquivo.getIdArquivo() != null){
						List<Integer> colecaoIdentificadoSetores = repositorioExecucaoOrdemServico.pesquisarSetorArquivoTextoOSCobrancaItem(arquivo.getIdArquivo());
						arquivo.setIdsSetor(colecaoIdentificadoSetores);
						arquivo.setIdsRota(repositorioExecucaoOrdemServico.pesquisarRotaArquivoTextoOSCobrancaItem(arquivo.getIdArquivo()));
						arquivo.setIdsLocalidade(repositorioExecucaoOrdemServico.pesquisarLocalidadeArquivoTextoOSCobrancaItem(arquivo.getIdArquivo()));
						arquivo.setNnQuadras(repositorioExecucaoOrdemServico.pesquisarQuadrasArquivoTextoOSCobrancaItem(arquivo.getIdArquivo()));
					}
					
					arquivos.add(arquivo);
				}
			}
			return arquivos;
		}catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	/**
	 * [UC1498] - Consultar Arquivo Texto de Ordens de Serviço para Smartphone (Novo)
	 *
	 * @author Jean Varela
	 * @throws ErroRepositorioException 
	 * @date   16/11/2015	
	 */
	public void atualizarListaArquivoTextoOSCobrancaSmartphone(
			Collection<ArquivoTxtOSCobrancaSmartphoneHelper> colecaoArquivoTextoOSCobrancaSmartphone,
			Integer idSituacaoLeituraNova, Leiturista leiturista) throws ControladorException {

		try {

			if (idSituacaoLeituraNova.intValue() == SituacaoTransmissaoLeitura.LIBERADO
				|| idSituacaoLeituraNova.intValue() == SituacaoTransmissaoLeitura.EM_CAMPO) {
				
				for (ArquivoTxtOSCobrancaSmartphoneHelper arquivoTxtOSCobrancaSmartphoneHelper : colecaoArquivoTextoOSCobrancaSmartphone) {
					Collection<Integer> idsArquivosLeiturista = repositorioExecucaoOrdemServico
							.pesquisarArquivosEmAbertoPorLeiturista(arquivoTxtOSCobrancaSmartphoneHelper.getIdArquivo());

					if (idsArquivosLeiturista != null && !idsArquivosLeiturista.isEmpty()) {
						throw new ControladorException("atencao.arquivo_txt_leiturista_nao_liberado");
					}
				}
			}
			Date dataLiberacao = null;
			if(idSituacaoLeituraNova.equals(SituacaoTransmissaoLeitura.LIBERADO) ){
				dataLiberacao = new Date();
			}
			repositorioExecucaoOrdemServico.atualizarListaArquivoTextoOSCobrancaSmartphone(
											colecaoArquivoTextoOSCobrancaSmartphone, idSituacaoLeituraNova, leiturista, dataLiberacao);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC1497] - Gerar Arquivo Texto de Ordens de Serviço para Smartphone
	 * [IT0005] - Exibir Lista de Localidades
	 * 
	 * @author André Miranda
	 * @date 13/11/2015
	 */
	public Collection<Localidade> pesquisarLocalidadeGrupoCobranca(Integer idGrupoCobranca) throws ControladorException {
		try {
			return repositorioExecucaoOrdemServico.pesquisarLocalidadeGrupoCobranca(idGrupoCobranca);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC1497] - Gerar Arquivo Texto de Ordens de Serviço para Smartphone
	 * [IT0017] - Exibir Lista de Comandos Eventuais
	 * 
	 * @author André Miranda
	 * @date 13/11/2015
	 */
	public Collection<CobrancaAcaoAtividadeComando> pesquisarComandosEventuais(Integer idServicoTipo, Integer idEmpresa, Date dataInicial, Date dataFinal)
			throws ControladorException {
		try {
			return repositorioExecucaoOrdemServico.pesquisarComandosEventuais(idServicoTipo,
					idEmpresa, dataInicial, dataFinal);
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * [UC1497] - Gerar Arquivo Texto de Ordens de Serviço para Smartphone
	 * [SB0001] - Exibir Totais Ordens de Serviço
	 * [IT0018] - Selecionar Ordens de Serviço Cronograma
	 * 
	 * @author André Miranda
	 * @date 13/11/2015
	 */
	public List<GerarArquivoTxtSmartphoneHelper> consultarQuantidadeOrdemServicoCronograma(
			Integer idCobrancaGrupo, Integer idEmpresa, Integer referencia, Integer idTipoServico, Integer[] idsLocalidade)
			throws ControladorException {
		List<GerarArquivoTxtSmartphoneHelper> retorno = new ArrayList<GerarArquivoTxtSmartphoneHelper>();

		try {
			Collection<Object[]> colecao = repositorioExecucaoOrdemServico
					.consultarQuantidadeOrdemServicoCronograma(idCobrancaGrupo, idEmpresa, referencia,
							idTipoServico, idsLocalidade);

			GerarArquivoTxtSmartphoneHelper helper;
			for (Object[] obj : colecao) {
				helper = new GerarArquivoTxtSmartphoneHelper();
				helper.setIdLocalidade((Integer) obj[0]);
				helper.setDescricaoLocalidade((String) obj[1]);
				helper.setIdSetor((Integer) obj[2]);
				helper.setCdSetor((Integer) obj[3]);
				helper.setIdRota((Integer) obj[4]);
				helper.setCdRota((Integer) obj[5]);
				helper.setDataGeracao((Date) obj[6]);
				helper.setQtd((Integer) obj[7]);
				retorno.add(helper);
			}
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}

	/**
	 * [UC1497] - Gerar Arquivo Texto de Ordens de Serviço para Smartphone
	 * [SB0001] - Exibir Totais Ordens de Serviço
	 * [IT0019] - Selecionar Ordens de Serviço Comando
	 * 
	 * @author André Miranda
	 * @date 13/11/2015
	 */
	public List<GerarArquivoTxtSmartphoneHelper> consultarQuantidadeOrdemServicoComando(Integer idComando,
			Integer idEmpresa, Integer idTipoServico) throws ControladorException {
		List<GerarArquivoTxtSmartphoneHelper> retorno = new ArrayList<GerarArquivoTxtSmartphoneHelper>();

		try {
			Collection<Object[]> colecao = repositorioExecucaoOrdemServico
					.consultarQuantidadeOrdemServicoComando(idComando, idEmpresa, idTipoServico);

			GerarArquivoTxtSmartphoneHelper helper;
			for (Object[] obj : colecao) {
				helper = new GerarArquivoTxtSmartphoneHelper();
				helper.setIdLocalidade((Integer) obj[0]);
				helper.setDescricaoLocalidade((String) obj[1]);
				helper.setIdSetor((Integer) obj[2]);
				helper.setCdSetor((Integer) obj[3]);
				helper.setIdRota((Integer) obj[4]);
				helper.setCdRota((Integer) obj[5]);
				helper.setDataGeracao((Date) obj[6]);
				helper.setQtd((Integer) obj[7]);
				retorno.add(helper);
			}
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		return retorno;
	}
	
	/**
	 * [UC1497] - Gerar Arquivo Texto de Ordens de Serviço para Smartphone
	 * [SB0002] - Exibir Ordens de Serviço
	 * [IT0021] - Selecionar Ordens de Serviço Cronograma das rotas selecionadas
	 * 
	 * @author André Miranda
	 * @date 13/11/2015
	 */
	public List<GerarArquivoTxtSmartphoneHelper> consultarOrdemServicoCronograma(Integer[] idsRota,
			Integer idEmpresa, Integer referencia, Integer idTipoServico)
			throws ControladorException {
		Map<String, GerarArquivoTxtSmartphoneHelper> mapaHelpers = new LinkedHashMap<String, GerarArquivoTxtSmartphoneHelper>();

		try {
			Collection<Object[]> colecao = repositorioExecucaoOrdemServico
					.consultarOrdemServicoCronograma(idsRota, idEmpresa,
							referencia, idTipoServico);

			GerarArquivoTxtSmartphoneHelper helper;
			for (Object[] obj : colecao) {
				Integer idOS = (Integer) obj[0];
				Integer idImovel = (Integer) obj[5];
				Integer idCobrancaDocumento = (Integer) obj[7];

				if (idTipoServico.equals(ServicoTipo.TIPO_DESLIGAMENTO_RAMAL_AGUA_ORDEM_CAERN)) {
					if (!verificarDebitosImovel(idImovel.toString(), idCobrancaDocumento)) {
						continue;
					}
				}

				helper = new GerarArquivoTxtSmartphoneHelper();
				helper.setIdLocalidade((Integer) obj[1]);
				helper.setDescricaoLocalidade((String) obj[2]);
				helper.setCdSetor((Integer) obj[3]);
				helper.setCdRota((Integer) obj[4]);
				helper.setNnQuadra((Integer) obj[8]);

				if (mapaHelpers.containsKey(helper.getKey())) {
					helper = mapaHelpers.get(helper.getKey());
				} else {
					mapaHelpers.put(helper.getKey(), helper);
				}

				helper.adicionarOS(idOS);
			}
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		return new ArrayList<GerarArquivoTxtSmartphoneHelper>(mapaHelpers.values());
	}

	/**
	 * [UC1497] - Gerar Arquivo Texto de Ordens de Serviço para Smartphone
	 * [SB0002] - Exibir Ordens de Serviço
	 * [IT0022] - Selecionar Ordens de Serviço Comando das rotas selecionadas
	 * 
	 * @author André Miranda
	 * @date 13/11/2015
	 */
	public List<GerarArquivoTxtSmartphoneHelper> consultarOrdemServicoComando(Integer[] idsRota,
			Integer idEmpresa, Integer idTipoServico, Integer idComando) throws ControladorException {
		Map<String, GerarArquivoTxtSmartphoneHelper> mapaHelpers = new LinkedHashMap<String, GerarArquivoTxtSmartphoneHelper>();

		try {
			Collection<Object[]> colecao = repositorioExecucaoOrdemServico.consultarOrdemServicoComando(
					idsRota, idEmpresa, idTipoServico, idComando);

			GerarArquivoTxtSmartphoneHelper helper;
			for (Object[] obj : colecao) {
				Integer idOS = (Integer) obj[0];
				Integer idImovel = (Integer) obj[5];
				Integer idCobrancaDocumento = (Integer) obj[7];

				if (idTipoServico.equals(ServicoTipo.TIPO_DESLIGAMENTO_RAMAL_AGUA_ORDEM_CAERN)) {
					if (!verificarDebitosImovel(idImovel.toString(), idCobrancaDocumento)) {
						continue;
					}
				}

				helper = new GerarArquivoTxtSmartphoneHelper();
				helper.setIdLocalidade((Integer) obj[1]);
				helper.setDescricaoLocalidade((String) obj[2]);
				helper.setCdSetor((Integer) obj[3]);
				helper.setCdRota((Integer) obj[4]);
				helper.setNnQuadra((Integer) obj[8]);

				if (mapaHelpers.containsKey(helper.getKey())) {
					helper = mapaHelpers.get(helper.getKey());
				} else {
					mapaHelpers.put(helper.getKey(), helper);
				}

				helper.adicionarOS(idOS);
			}
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		return new ArrayList<GerarArquivoTxtSmartphoneHelper>(mapaHelpers.values());
	}

	/**
	 * [UC1497] - Gerar Arquivo Texto de Ordens de Serviço para Smartphone
	 * [IT0012] - Verificar Débitos Imóvel
	 * 
	 * @author André Miranda
	 * @param idDocumentoCobranca 
	 * @throws ControladorException 
	 * @throws ErroRepositorioException 
	 * @date 13/11/2015
	 */
	private boolean verificarDebitosImovel(String idImovel, Integer idCobrancaDocumento)
			throws ControladorException, ErroRepositorioException {
		// [UC0067] Obter Débito do Imóvel ou Cliente
		ObterDebitoImovelOuClienteHelper imovelDebitoCredito = this.getControladorCobranca()
				.obterDebitoImovelOuCliente(ConstantesSistema.SIM, // indicadorDebito
						idImovel, // idImovel
						null, // codigoCliente
						null, // clienteRelacaoTipo
						"190001", // anoMesInicialReferenciaDebito
						"209912", // anoMesFinalReferenciaDebito
						Util.converteStringParaDate("01/01/1900"), // anoMesInicialVencimentoDebito
						new Date(), // anoMesFinalVencimentoDebito
						ConstantesSistema.SIM, // indicadorPagamento
						ConstantesSistema.SIM, // indicadorConta
						ConstantesSistema.NAO, // indicadorDebitoACobrar
						ConstantesSistema.NAO, // indicadorCreditoARealizar
						ConstantesSistema.NAO, // indicadorNotasPromissorias
						ConstantesSistema.NAO, // indicadorGuiasPagamento
						ConstantesSistema.NAO, // indicadorCalcularAcrescimoImpontualidade
						true, // indicadorContas
						ConstantesSistema.NAO); // indicadorCalcularAcrescimoImpontualidadeGuiaPagamentoExtratoDebito 

		Collection<ContaValoresHelper> colecaoContas = imovelDebitoCredito.getColecaoContasValoresImovel();

		if (Util.isVazioOrNulo(colecaoContas)) {
			return false;
		}

		Set<Integer> idsConta = new HashSet<Integer>();
		for (ContaValoresHelper helper : colecaoContas) {
			idsConta.add(helper.getConta().getId());
		}

		List<Integer> idsContaDocumentoItem = repositorioExecucaoOrdemServico
				.pesquisarIdContaCobrancaDocumentoItem(idCobrancaDocumento);

		for (Integer id : idsContaDocumentoItem) {
			if (idsConta.contains(id)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * [UC1497] - Gerar Arquivo Texto de Ordens de Servico para Smartphone
	 * [IT0011] - Gerar Arquivo Texto de Ordens de Serviço
	 * 
	 * @author Vivianne Sousa	
	 * @date 19/11/2015
	 */
	public void inserirParametrosArquivoTextoOSCobranca(ParametrosArquivoTextoOSCobranca parametros,
			Integer idLeiturista, Integer[] idsRota, Collection<Integer[]> colecaoOS) throws ControladorException {
		// Verificar se o usuário selecionou alguma os. Caso não, selecionar todas as OS do filtro informado
		if(Util.isVazioOrNulo(colecaoOS)) {
			Empresa empresa = parametros.getEmpresa();
			ServicoTipo servicoTipo = parametros.getServicoTipo();
			CobrancaAcaoAtividadeComando comando = parametros.getCobrancaAcaoAtividadeComando();

			List<GerarArquivoTxtSmartphoneHelper> lista;

			if(comando != null && Util.verificarIdNaoVazio(comando.getId())) {
				lista = consultarOrdemServicoComando(
						idsRota, empresa.getId(), servicoTipo.getId(), comando.getId());
			} else {
				Integer referencia = parametros.getAnoMesReferenciaCronograma();

				lista = consultarOrdemServicoCronograma(
						idsRota, empresa.getId(), referencia, servicoTipo.getId());
			}

			colecaoOS = new ArrayList<Integer[]>();
			for (GerarArquivoTxtSmartphoneHelper helper : lista) {
				for (Integer idOS : helper.getListaOS()) {
					Integer[] dados = { idOS, helper.getIdLocalidade(), helper.getCdSetor(),
							helper.getCdRota(), helper.getNnQuadra() };
					colecaoOS.add(dados);
				}
			}
		}

		if (Util.isVazioOrNulo(colecaoOS)) {
			throw new ControladorException("atencao.nenhuma_os_selecionada");
		}

		ParametroSistema parametro = getControladorUtil().pesquisarParametrosDoSistemaNovo(ParametroSistema.NUMERO_LIMITE_OS_COBRANCA);
		Integer limite = parametro.getValorParametro() != null ? new Integer(parametro.getValorParametro()) : null ;
		if (limite != null && colecaoOS.size() > limite.intValue()){
			throw new ControladorException("atencao.quantidade_os_superior_limite", null, limite.toString());
		}

		// INSERINDO OS PARÂMETROS
		parametros.setUltimaAlteracao(new Date());
		Integer id = (Integer) this.getControladorUtil().inserir(parametros);
		parametros.setId(id);

		// INSERINDO AS LOCALIDADES DOS PARÂMETROS
		Set<Integer> idsLocalidade = new HashSet<Integer>();

		for (Integer[] os : colecaoOS) {
			Integer idLocalidade = os[1];
			if (idsLocalidade.contains(idLocalidade))
				continue;
			idsLocalidade.add(idLocalidade);

			this.getControladorUtil().inserir(new ParametrosArquivoTextoOSLocalidade(
					parametros, new Localidade(idLocalidade), new Date()));
		}

		// GERANDO ARQUIVO
		ArquivoTextoOSCobranca arquivoTextoOSCobranca = new ArquivoTextoOSCobranca();
		SituacaoTransmissaoLeitura situacaoTransmissaoLeitura = new SituacaoTransmissaoLeitura();
		situacaoTransmissaoLeitura.setId(SituacaoTransmissaoLeitura.DISPONIVEL);
		arquivoTextoOSCobranca.setSituacao(situacaoTransmissaoLeitura);
		arquivoTextoOSCobranca.setQuantidadeOrdemServico(colecaoOS.size());
		arquivoTextoOSCobranca.setParametrosArquivoTextoOSCobranca(parametros);
		arquivoTextoOSCobranca.setLeiturista(new Leiturista(idLeiturista));
		arquivoTextoOSCobranca.setUltimaAlteracao(new Date());
		// INSERINDO O ARQUIVO
		Integer idArquivoTextoOSCobranca = (Integer) this.getControladorUtil().inserir(arquivoTextoOSCobranca);
		arquivoTextoOSCobranca.setId(idArquivoTextoOSCobranca);

		// INSERINDO OS ITENS DO ARQUIVO
		for(Object[] infoOS : colecaoOS) {
			Integer idOS = (Integer) infoOS[0];
			Integer idLocalidade = (Integer) infoOS[1];
			Integer cdSetor = (Integer) infoOS[2];
			Integer cdRota = (Integer) infoOS[3];
			Integer nnQuadra = (Integer) infoOS[4];

			OrdemServico ordemServico = getControladorUtil().pesquisar(OrdemServico.class, idOS);

			ArquivoTextoOSCobrancaItem arquivoTextoOSCobrancaItem = new ArquivoTextoOSCobrancaItem();
			arquivoTextoOSCobrancaItem.setArquivoTextoOSCobranca(arquivoTextoOSCobranca);
			arquivoTextoOSCobrancaItem.setOrdemServico(ordemServico);
			arquivoTextoOSCobrancaItem.setLocalidade(new Localidade(idLocalidade));
			arquivoTextoOSCobrancaItem.setCodigoSetorComercial(cdSetor);
			arquivoTextoOSCobrancaItem.setCodigoRota(cdRota);
			arquivoTextoOSCobrancaItem.setNumeroQuadra(nnQuadra);
			arquivoTextoOSCobrancaItem.setUltimaAlteracao(new Date());

			this.getControladorUtil().inserir(arquivoTextoOSCobrancaItem);

			// Criamos o registro de como o cliente foi enviado
			Integer idImovel = ordemServico.getImovel().getId();

			Collection<?> colecaoClienteImovel = this.getControladorCliente().obterClienteImovelporRelacaoTipo(idImovel, ClienteRelacaoTipo.USUARIO.intValue());
			ClienteImovel clienteImovel = (ClienteImovel) Util.retonarObjetoDeColecao(colecaoClienteImovel);
			Cliente cliente = clienteImovel.getCliente();

			Collection<ClienteFone> colecaoFones = this.getControladorCliente().pesquisarClienteFone(clienteImovel.getCliente().getId());
			ClienteFone fonePrincipal = null;

			if (!Util.isVazioOrNulo(colecaoFones)) {
				for (ClienteFone fone : colecaoFones) {
					if (fone.getIndicadorTelefonePadrao() != null && fone.getIndicadorTelefonePadrao() == 1) {
						fonePrincipal = fone;
						break;
					}
				}
			}

			ArquivoTextoOSCobrancaCliente atocc = new ArquivoTextoOSCobrancaCliente( 
					arquivoTextoOSCobranca,
					ordemServico,
					cliente.getId(),
					cliente.getNome(),
					cliente.getCpf(),
					cliente.getCnpj(),
					cliente.getRg(),
					(fonePrincipal != null ? fonePrincipal.getDdd() : null),
					(fonePrincipal != null ? fonePrincipal.getTelefone() : null),
					(fonePrincipal != null ? fonePrincipal.getRamal() : null),
					cliente.getOrgaoExpedidorRg(),
					cliente.getUnidadeFederacao());

			this.getControladorUtil().inserir(atocc);
		}
	}

	/**
	 * [UC01484] - Gerar Arquivo de Ida para Execucao de OS de Cobranca Android
	 * 
	 * @author Vivianne Sousa	
	 * @date 19/11/2015
	 */
	public byte[] gerarArquivoTextoIdaExecucaoOSCobranca(Integer idArquivoTextoOSCobranca) throws ControladorException{
		byte[] retorno = null;
		SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();

		try {
			//OBTENDO AS ORDENS DE SERVIÇO
			Collection<Object[]> colecaoOS = this.consultarArquivoTextoOSCobrancaItem(idArquivoTextoOSCobranca);
			
			if(colecaoOS != null && !colecaoOS.isEmpty()){
				
				Object[] infoUsuario = Util.retonarObjetoDeColecaoArray(colecaoOS);
				
				//INICIALIZANDO O ARQUIVO TEXTO
				StringBuilder arquivoParametros = new StringBuilder();
				StringBuilder arquivoOS = new StringBuilder();
				StringBuilder arquivoFINAL = new StringBuilder();
				StringBuilder arquivoDebitosOS = new StringBuilder();
				
				//TOOO0 - DADOS DOS PARÂMETROS DO ARQUIVO
				arquivoParametros.append("00|");
				arquivoParametros.append(idArquivoTextoOSCobranca + "|");
				arquivoParametros.append(sistemaParametro.getNumero0800Empresa() + "|");
				arquivoParametros.append(System.getProperty("line.separator"));
	
				//TOOO1 - AGENTE COMERCIAL
				arquivoParametros.append("01|");
				//idUsuario
				arquivoParametros.append(infoUsuario[9] + "|");
				//loginUsuario
				arquivoParametros.append(infoUsuario[10] + "|");
				//senhaUsuario
				arquivoParametros.append(infoUsuario[11] + "|");
				//imeiLeiturista
				arquivoParametros.append(infoUsuario[12] + "|");
	//			arquivoParametros.append(infoUsuario[14]);
				arquivoParametros.append(System.getProperty("line.separator"));

				//TOOO2 - ORGÃOS EXPEDIDORES  DE RG
				FiltroOrgaoExpedidorRg filtroOrgaoExpedidorRg = new FiltroOrgaoExpedidorRg();
				filtroOrgaoExpedidorRg.adicionarParametro(new ParametroSimples(FiltroOrgaoExpedidorRg.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
				Collection<OrgaoExpedidorRg> colecaoOrgaoExpedidorRg = this.getControladorUtil().pesquisar(filtroOrgaoExpedidorRg, OrgaoExpedidorRg.class);
				if (colecaoOrgaoExpedidorRg != null && !colecaoOrgaoExpedidorRg.isEmpty()){
					Iterator<OrgaoExpedidorRg> itColecaoOrgaoExpedidorRg = colecaoOrgaoExpedidorRg.iterator();
					while(itColecaoOrgaoExpedidorRg.hasNext()){
						OrgaoExpedidorRg orgaoExpedidorRg = (OrgaoExpedidorRg) itColecaoOrgaoExpedidorRg.next();
						arquivoParametros.append("02|");
						arquivoParametros.append(orgaoExpedidorRg.getId().intValue() + "|");
						arquivoParametros.append(orgaoExpedidorRg.getDescricao());
						arquivoParametros.append(System.getProperty("line.separator"));
					}
				}

				//TOOO3 - UNIDADES DA FEDERAÇÃO
				FiltroUnidadeFederacao filtroUnidadeFederacao = new FiltroUnidadeFederacao();
				Collection<UnidadeFederacao> colecaoUnidadeFederacao = this.getControladorUtil().pesquisar(filtroUnidadeFederacao, UnidadeFederacao.class);
				if (colecaoUnidadeFederacao != null && !colecaoUnidadeFederacao.isEmpty()){
					Iterator<UnidadeFederacao> itColecaoUnidadeFederacao = colecaoUnidadeFederacao.iterator();
					while(itColecaoUnidadeFederacao.hasNext()){
						UnidadeFederacao unidadeFederacao = (UnidadeFederacao) itColecaoUnidadeFederacao.next();
						arquivoParametros.append("03|");
						arquivoParametros.append(unidadeFederacao.getId().intValue() + "|");
						arquivoParametros.append(unidadeFederacao.getDescricao()  + "|");
						arquivoParametros.append(unidadeFederacao.getSigla());
						arquivoParametros.append(System.getProperty("line.separator"));
					}
				}

				//TOOO4 - MOTIVOS DE ENCERRAMENTO
				FiltroAtendimentoMotivoEncerramento filtroAtendimentoMotivoEncerramento = new FiltroAtendimentoMotivoEncerramento();
				filtroAtendimentoMotivoEncerramento.adicionarParametro(new ParametroSimples(FiltroAtendimentoMotivoEncerramento.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
				Collection<AtendimentoMotivoEncerramento> colecaoAtendimentoMotivoEncerramento = 
						this.getControladorUtil().pesquisar(filtroAtendimentoMotivoEncerramento, AtendimentoMotivoEncerramento.class);
				if (colecaoAtendimentoMotivoEncerramento != null && !colecaoAtendimentoMotivoEncerramento.isEmpty()){
					Iterator<AtendimentoMotivoEncerramento> itColecaoAtendimentoMotivoEncerramento = colecaoAtendimentoMotivoEncerramento.iterator();
					while(itColecaoAtendimentoMotivoEncerramento.hasNext()){
						AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = (AtendimentoMotivoEncerramento) itColecaoAtendimentoMotivoEncerramento.next();
						arquivoParametros.append("04|");
						arquivoParametros.append(atendimentoMotivoEncerramento.getId().intValue() + "|");
						arquivoParametros.append(atendimentoMotivoEncerramento.getDescricao() + "|");
						arquivoParametros.append(atendimentoMotivoEncerramento.getIndicadorExecucao());
						arquivoParametros.append(System.getProperty("line.separator"));
					}
				}

				//TOOO5 - SITUAÇÃO DA FISCALIZAÇÃO
				FiltroFiscalizacaoSituacao filtroFiscalizacaoSituacao = new FiltroFiscalizacaoSituacao();
				Collection<FiscalizacaoSituacao> colecaoFiscalizacaoSituacao = 
						this.getControladorUtil().pesquisar(filtroFiscalizacaoSituacao, FiscalizacaoSituacao.class);
				if (colecaoFiscalizacaoSituacao != null && !colecaoFiscalizacaoSituacao.isEmpty()){
					Iterator<FiscalizacaoSituacao> itColecaoFiscalizacaoSituacao = colecaoFiscalizacaoSituacao.iterator();
					while(itColecaoFiscalizacaoSituacao.hasNext()){
						FiscalizacaoSituacao fiscalizacaoSituacao = (FiscalizacaoSituacao) itColecaoFiscalizacaoSituacao.next();
						arquivoParametros.append("05|");
						arquivoParametros.append(fiscalizacaoSituacao.getId().intValue() + "|");
						arquivoParametros.append(fiscalizacaoSituacao.getDescricaoFiscalizacaoSituacao() + "|");
						if(fiscalizacaoSituacao.getDocumentoTipo() != null){
							arquivoParametros.append(fiscalizacaoSituacao.getDocumentoTipo().getId());	
						}else{
							arquivoParametros.append("");
						}
						arquivoParametros.append(System.getProperty("line.separator"));
					}
				}

				//TOOO6 - DOCUMENTO TIPO
				Collection<DocumentoTipo> colecaoDocumentoTipo = repositorioExecucaoOrdemServico.pesquisarDocumentoTipoDaSituacaoFiscalizacao();
				if (!Util.isVazioOrNulo(colecaoDocumentoTipo)){
					for (DocumentoTipo documentoTipo : colecaoDocumentoTipo) {
						arquivoParametros.append("06|");
						arquivoParametros.append(documentoTipo.getId() + "|");
						arquivoParametros.append(documentoTipo.getDescricaoDocumentoTipo());
						arquivoParametros.append(System.getProperty("line.separator"));
					}
				}

				//TOOO7 - TIPO DE POÇO
				FiltroPocoTipo filtroPocoTipo = new FiltroPocoTipo();
				filtroPocoTipo.adicionarParametro(new ParametroSimples(FiltroPocoTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
				Collection<PocoTipo> colecaoPocoTipo = this.getControladorUtil().pesquisar(filtroPocoTipo, PocoTipo.class);
				if (colecaoPocoTipo != null && !colecaoPocoTipo.isEmpty()){
					Iterator<PocoTipo> itColecaoPocoTipo = colecaoPocoTipo.iterator();
					while(itColecaoPocoTipo.hasNext()){
						PocoTipo pocoTipo = (PocoTipo) itColecaoPocoTipo.next();
						arquivoParametros.append("07|");
						arquivoParametros.append(pocoTipo.getId().intValue() + "|");
						arquivoParametros.append(pocoTipo.getDescricao());
						arquivoParametros.append(System.getProperty("line.separator"));
					}
				}
				
				//TOOO8 - HIDRÔMETRO PROTEÇÃO
				FiltroHidrometroProtecao filtroHidrometroProtecao = new FiltroHidrometroProtecao();
				filtroHidrometroProtecao.adicionarParametro(new ParametroSimples(FiltroHidrometroProtecao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
				Collection<HidrometroProtecao> colecaoHidrometroProtecao = 
						this.getControladorUtil().pesquisar(filtroHidrometroProtecao, HidrometroProtecao.class);
				if (colecaoHidrometroProtecao != null && !colecaoHidrometroProtecao.isEmpty()){
					Iterator<HidrometroProtecao> itColecaoHidrometroProtecao = colecaoHidrometroProtecao.iterator();
					while(itColecaoHidrometroProtecao.hasNext()){
						HidrometroProtecao hidrometroProtecao = (HidrometroProtecao) itColecaoHidrometroProtecao.next();
						arquivoParametros.append("08|");
						arquivoParametros.append(hidrometroProtecao.getId().intValue() + "|");
						arquivoParametros.append(hidrometroProtecao.getDescricao());
						arquivoParametros.append(System.getProperty("line.separator"));
					}
				}
				
				//TOOO9 - MOTIVOS DE SUPRESSÃO
				
				//TOO10 - MOTIVOS DE CORTE
				FiltroMotivoCorte filtroMotivoCorte = new FiltroMotivoCorte();
				filtroMotivoCorte.adicionarParametro(new ParametroSimples(FiltroMotivoCorte.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
				Collection<MotivoCorte> colecaoMotivoCorte = this.getControladorUtil().pesquisar(filtroMotivoCorte, MotivoCorte.class);
				if (colecaoMotivoCorte != null && !colecaoMotivoCorte.isEmpty()){
					Iterator<MotivoCorte> itColecaoMotivoCorte = colecaoMotivoCorte.iterator();
					while(itColecaoMotivoCorte.hasNext()){
						MotivoCorte motivoCorte = (MotivoCorte) itColecaoMotivoCorte.next();
						arquivoParametros.append("10|");
						arquivoParametros.append(motivoCorte.getId().intValue() + "|");
						arquivoParametros.append(motivoCorte.getDescricao());
						arquivoParametros.append(System.getProperty("line.separator"));
					}
				}
				
				//TOO11 - TIPOS DE SERVIÇO
				Collection<ServicoTipo> colecaoServicoTipo =  this.repositorioOrdemServico.pesquisarServicoTipo();
				if (colecaoServicoTipo != null && !colecaoServicoTipo.isEmpty()){
					Iterator<ServicoTipo> itColecaoServicoTipo = colecaoServicoTipo.iterator();
					while(itColecaoServicoTipo.hasNext()){
						ServicoTipo servicoTipo = itColecaoServicoTipo.next();
						arquivoParametros.append("11|");
						arquivoParametros.append(servicoTipo.getId().intValue() + "|");
						arquivoParametros.append(servicoTipo.getDescricao()  + "|");
						if (servicoTipo.getConstanteFuncionalidadeTipoServico() != null) {
							arquivoParametros.append(servicoTipo.getConstanteFuncionalidadeTipoServico() + "|");
						}
						arquivoParametros.append(servicoTipo.getIndicadorBoletim()  + "|");
						arquivoParametros.append(System.getProperty("line.separator"));
					}
				}
				
				//TOO12 - TIPOS DE SUPRESSÃO
				
				//TOO13 - LOCAL DE INSTALAÇÃO DO HIDRÔMETRO
				FiltroHidrometroLocalInstalacao filtroHidrometroLocalInstalacao = new FiltroHidrometroLocalInstalacao();
				filtroHidrometroLocalInstalacao.adicionarParametro(new ParametroSimples(FiltroHidrometroLocalInstalacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
				Collection<HidrometroLocalInstalacao> colecaoHidrometroLocalInstalacao = 
						this.getControladorUtil().pesquisar(filtroHidrometroLocalInstalacao, HidrometroLocalInstalacao.class);
				if (colecaoHidrometroLocalInstalacao != null && !colecaoHidrometroLocalInstalacao.isEmpty()){
					Iterator<HidrometroLocalInstalacao> itColecaoHidrometroLocalInstalacao = colecaoHidrometroLocalInstalacao.iterator();
					while(itColecaoHidrometroLocalInstalacao.hasNext()){
						HidrometroLocalInstalacao hidrometroLocalInstalacao = (HidrometroLocalInstalacao) itColecaoHidrometroLocalInstalacao.next();
						arquivoParametros.append("13|");
						arquivoParametros.append(hidrometroLocalInstalacao.getId().intValue() + "|");
						arquivoParametros.append(hidrometroLocalInstalacao.getDescricao());
						arquivoParametros.append(System.getProperty("line.separator"));
					}
				}
				
				//TOO14 - TIPOS DE CORTE
				FiltroCorteTipo filtroCorteTipo = new FiltroCorteTipo();
				filtroCorteTipo.adicionarParametro(new ParametroSimples(FiltroCorteTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
				Collection<CorteTipo> colecaoCorteTipo = this.getControladorUtil().pesquisar(filtroCorteTipo, CorteTipo.class);
				if (colecaoCorteTipo != null && !colecaoCorteTipo.isEmpty()){
					Iterator<CorteTipo> itColecaoCorteTipo = colecaoCorteTipo.iterator();
					while(itColecaoCorteTipo.hasNext()){
						CorteTipo corteTipo = (CorteTipo) itColecaoCorteTipo.next();
						arquivoParametros.append("14|");
						arquivoParametros.append(corteTipo.getId().intValue() + "|");
						arquivoParametros.append(corteTipo.getDescricao());
						arquivoParametros.append(System.getProperty("line.separator"));
					}
				}
				
				//TOO15 - TIPO SITUAÇÃO OS
				arquivoParametros.append("15|");
				arquivoParametros.append("8|");
				arquivoParametros.append("ENCERRAMENTO OS");
				arquivoParametros.append(System.getProperty("line.separator"));
				
				arquivoParametros.append("15|");
				arquivoParametros.append("11|");
				arquivoParametros.append("ANTES DA EXECUCAO");
				arquivoParametros.append(System.getProperty("line.separator"));
				
				arquivoParametros.append("15|");
				arquivoParametros.append("12|");
				arquivoParametros.append("DURANTE A EXECUCAO");
				arquivoParametros.append(System.getProperty("line.separator"));
				
				arquivoParametros.append("15|");
				arquivoParametros.append("13|");
				arquivoParametros.append("APOS A EXECUCAO");
				arquivoParametros.append(System.getProperty("line.separator"));
				
				arquivoParametros.append("15|");
				arquivoParametros.append("14|");
				arquivoParametros.append("FACHADA DO IMOVEL");
				arquivoParametros.append(System.getProperty("line.separator"));
				
				arquivoParametros.append("15|");
				arquivoParametros.append("16|");
				arquivoParametros.append("SITUACAO ENCONTRADA");
				arquivoParametros.append(System.getProperty("line.separator"));
			
				//TOO21 - TIPOS DE SERVIÇO DO BOLETIM
				Collection<ServicoTipoBoletim> colecaoServicoTipoBoletim = this.repositorioOrdemServico.pesquisarServicoTipoBoletim();
				if (colecaoServicoTipoBoletim != null && !colecaoServicoTipoBoletim.isEmpty()){
					for (ServicoTipoBoletim servicoTipoBoletim : colecaoServicoTipoBoletim) {
						arquivoParametros.append("21|");
						arquivoParametros.append(servicoTipoBoletim.getId() + "|");
						arquivoParametros.append(servicoTipoBoletim.getIndicadorPavimento()  + "|");
						arquivoParametros.append(System.getProperty("line.separator"));
					}
				}
				
				Iterator<Object[]> itColecaoOS = colecaoOS.iterator();
				Integer idLocalidade = null;
				Integer sequencialOS = 0;
				
				arquivoFINAL.append(arquivoParametros);
				
				while (itColecaoOS.hasNext()){				
					Object[] infoOS = itColecaoOS.next();
					if ( idLocalidade == null ){
						idLocalidade = (Integer)infoOS[0];
					}
					sequencialOS++;
					arquivoOS.append(this.gerarArquivoTextoIdaExecucaoOSCobrancaItem(infoOS, sequencialOS));
					//[IT0021] Selecionar Débitos da OS
					arquivoDebitosOS.append(this.gerarArquivoTextoExecucaoOSDebitos((Integer) infoOS[1]));
				}
				
				//GERANDO ARQUIVO EM BYTES
				arquivoFINAL.append(arquivoOS);
				arquivoFINAL.append(arquivoDebitosOS);
				
				retorno = this.gerarArquivoTextoIdaExecucaoOSCobrancaEmByte(arquivoFINAL);
			}
		
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			new ControladorException("erro.sistema", ex);
		}
		return retorno;
	}
	
	/**
	 * [UC01484] - Gerar Arquivo de Ida para Execucao de OS de Cobranca Android
	 * 
	 * @author Vivianne Sousa	
	 * @date 19/11/2015
	 */
	public Collection<Object[]> consultarArquivoTextoOSCobrancaItem(Integer idArquivoTextoOSCobranca) throws ControladorException{
		try{			
			return repositorioExecucaoOrdemServico.consultarArquivoTextoOSCobrancaItem(idArquivoTextoOSCobranca);
		}catch(ErroRepositorioException e){
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	/**
	 * [UC01484] - Gerar Arquivo de Ida para Execucao de OS de Cobranca Android
	 * 
	 * @author Vivianne Sousa	
	 * @date 19/11/2015
	 */
	private StringBuilder gerarArquivoTextoIdaExecucaoOSCobrancaItem(Object[] infoOS, Integer sequencialOS) throws ControladorException{
		StringBuilder arquivoOS = new StringBuilder();
		try {
			Integer idOS = (Integer) infoOS[1];
			Integer idServicoTipo = (Integer) infoOS[2];
			Integer idImovel = (Integer) infoOS[3];
			String  dsImovelPerfil = (String) infoOS[4];
			Integer idCategoriaPrincipal = (Integer) infoOS[5];
			String  dsCategoriaPrincipal = (String) infoOS[6];
			String  dsLigacaoAguaSituacao = (String) infoOS[7];
			String  dsLigacaoEsgotoSituacao = (String) infoOS[8];
			Integer idLocalidade = (Integer) infoOS[14];
			Date dtGeracaoOS = (Date) infoOS[15];

			//TIPO DO ARQUIVO
			arquivoOS.append("16|");
			//OS
			arquivoOS.append(idOS + "|");
			//TIPO DE SERVIÇO
			arquivoOS.append(idServicoTipo + "|");
			//SEQUENCIAL DA OS GERADA
			arquivoOS.append(sequencialOS + "|");
			//ENDEREÇO ABREVIADO O IMÓVEL
			String endereco = this.getControladorEndereco().pesquisarEnderecoFormatado(idImovel);
			arquivoOS.append(endereco + "|");
			//INSCRIÇÃO DO IMÓVEL
			String inscricao = this.getControladorImovel().pesquisarInscricaoImovel(idImovel);
			arquivoOS.append(inscricao + "|");
			//MATRÍCULA
			arquivoOS.append(idImovel + "|");
			//PERFIL
			arquivoOS.append(dsImovelPerfil + "|");
			//QUANTIDADE DE ECONOMIAS DA CATEGORIA PRINCIPAL
			Integer quantidadeEconomiasCategoriaPrincipal = 0;
			
			Imovel imovel = new Imovel();
			imovel.setId(idImovel);
			Collection<?> colecaoQuantidadeEconomias = this.getControladorImovel().obterQuantidadeEconomiasCategoria(imovel);
			Iterator<?> itColecaoQuantidadeEconomias = colecaoQuantidadeEconomias.iterator();
			while(itColecaoQuantidadeEconomias.hasNext()){
				Categoria categoria = (Categoria) itColecaoQuantidadeEconomias.next();
				if (categoria.getId().equals(idCategoriaPrincipal)){
					quantidadeEconomiasCategoriaPrincipal = categoria.getQuantidadeEconomiasCategoria();
					break;
				}
			}
			arquivoOS.append(quantidadeEconomiasCategoriaPrincipal.intValue() + "|");
			//DESCRIÇÃO DA CATEGORIA PRINCIPAL
			arquivoOS.append(dsCategoriaPrincipal + "|");
			//DESCRIÇÃO DO GRUPO DE FATURAMENTO
			FaturamentoGrupo faturamentoGrupo = this.getControladorImovel().pesquisarGrupoImovel(idImovel);
			arquivoOS.append(faturamentoGrupo.getDescricao() + "|");
			//SITUAÇÃO DE ÁGUA
			arquivoOS.append(dsLigacaoAguaSituacao + "|");
			//SITUAÇÃO DE ESGOTO
			arquivoOS.append(dsLigacaoEsgotoSituacao + "|");
			//DADOS DO HIDROMETRO
			Object[] informacoesHidrometro = null;
			Object[] numeroLeitura = null;
			Integer idRegistroAtendimento = null;
			Integer tipoMedicao = null;
			Integer tipoHidrometro = null;

			idRegistroAtendimento = repositorioOrdemServico.obterRegistroAtendimento(idOS);
			if (idRegistroAtendimento == null) {
				tipoMedicao = MedicaoTipo.LIGACAO_AGUA;
			} else {
				tipoMedicao = repositorioOrdemServico.obterTipoMedicao(idOS);
			}
			tipoHidrometro = repositorioOrdemServico.obterTipoHidrometro(tipoMedicao, idOS);
			numeroLeitura = repositorioOrdemServico.obterNumeroLeitura(idOS, tipoMedicao);
			if (tipoHidrometro != null) {
				informacoesHidrometro = repositorioOrdemServico
						.obterDadosHidrometros(tipoHidrometro, tipoMedicao, idOS);
			}

			/*
			 * autor:Jonathan Marcos
			 * data:01/11/2013
			 * [UC1484] Gerar Arquivo de Ida para Execucao de OS de Cobranca Android
			 * [RM9458]
			 * [Observacao] Acrescentar Tipo de Registro 18
			 */
			//TIPO DE MEDICAO
			arquivoOS.append(String.valueOf(tipoMedicao)+ "|");
			if(tipoHidrometro==null){
				//TIPO HIDROMETRO
				arquivoOS.append("|");
			}else{
				//TIPO HIDROMETRO
				arquivoOS.append(String.valueOf(tipoHidrometro) + "|");
			}
			if(informacoesHidrometro!=null && tipoHidrometro!=null){
				//NUMERO HIDROMETRO
				arquivoOS.append(String.valueOf(informacoesHidrometro[0])+ "|");
				//SITUACAO HIDROMETRO
				arquivoOS.append(String.valueOf(informacoesHidrometro[1])+ "|");
				// LOCAL DE INSTALACAO DO HIDROMETRO
				arquivoOS.append(String.valueOf(informacoesHidrometro[2])+ "|");
				// PROTECAO HIDROMETRO
				arquivoOS.append(String.valueOf(informacoesHidrometro[3])+ "|");
				//INDICADOR CAVALETE HIDROMETRO
				arquivoOS.append(informacoesHidrometro[4] +"|");
				//HIDROMETRO MARCA
				arquivoOS.append(informacoesHidrometro[5] +"|");
			}else{
				arquivoOS.append("||||||");
			}
			//NUMERO DA LEITURA
			if(numeroLeitura!=null){
				arquivoOS.append(String.valueOf(Integer.valueOf(numeroLeitura[0].toString()))+ "|");
			}else{
				arquivoOS.append("|");
			}
			//DADOS DO CLIENTE USUÁRIO
			Collection<?> colecaoClienteImovel = this.getControladorCliente().obterClienteImovelporRelacaoTipo(idImovel, ClienteRelacaoTipo.USUARIO.intValue());
			ClienteImovel clienteImovel = (ClienteImovel) Util.retonarObjetoDeColecao(colecaoClienteImovel);
			//NOME DO CLIENTE USUÁRIO
			arquivoOS.append(clienteImovel.getCliente().getNome() + "|");
			//CPF DO CLIENTE USUÁRIO
			if (clienteImovel.getCliente().getCpf() != null){
				arquivoOS.append(clienteImovel.getCliente().getCpf() + "|");
			}else{
				arquivoOS.append("|");
			}
			//CNPJ DO CLIENTE USUÁRIO
			if (clienteImovel.getCliente().getCnpj() != null){
				arquivoOS.append(clienteImovel.getCliente().getCnpj() + "|");
			}else{
				arquivoOS.append("|");
			}
			//RG DO CLIENTE USUÁRIO
			if (clienteImovel.getCliente().getRg() != null){
				arquivoOS.append(clienteImovel.getCliente().getRg() + "|");
			}else{
				arquivoOS.append("|");
			}
			//ORGÃO EXPEDIDOR RG
			arquivoOS.append((clienteImovel.getCliente().getOrgaoExpedidorRg() !=null ?				
					 clienteImovel.getCliente().getOrgaoExpedidorRg().getId() + "|" : "|" ) );
			//UNIDADE DA FEDERAÇÃO RG
			arquivoOS.append(( clienteImovel.getCliente().getUnidadeFederacao() != null ?
					  clienteImovel.getCliente().getUnidadeFederacao().getId() + "|" : "|" ) );
			//TELEFONE
			boolean achou = false;
			Collection<ClienteFone> colecaoFones = this.getControladorCliente().pesquisarClienteFone(clienteImovel.getCliente().getId());
			if( !Util.isVazioOrNulo(colecaoFones) ){
				for(ClienteFone fone : colecaoFones){
					if(fone.getIndicadorTelefonePadrao()!=null && fone.getIndicadorTelefonePadrao() == 1){
						achou = true;
						//DDD DO PRINCIPAL TELEFONE
						if ( fone.getDdd() != null ){
							arquivoOS.append( fone.getDdd()+"|");	
						}else{
							arquivoOS.append( "|");
						}
						// PRINCIPAL TELEFONE
						if ( fone.getTelefone() != null ){
							arquivoOS.append( fone.getTelefone()+"|");	
						}else{
							arquivoOS.append( "|");
						}
						// RAMAL DO PRINCIPAL TELEFONE
						if ( fone.getRamal() != null ){
							arquivoOS.append( fone.getRamal()+"|");	
						}else{
							arquivoOS.append( "|");
						}
					}
				}
			}
			if ( !achou ) {
				//DDD DO PRINCIPAL TELEFONE
				arquivoOS.append( "|");
				//NUMERO DO PRINCIPAL TELEFONE
				arquivoOS.append("|");
				//DDD DO PRINCIPAL TELEFONE
				arquivoOS.append("|");
			}
			// Consumo médio
			arquivoOS.append( this.getControladorFaturamento().obterValorConsumoMedio6meses( imovel.getId() ) + "|" );

			// Descrição do Grupo de Cobrança
			Rota rota = this.getControladorImovel().pesquisarRotaImovel(idImovel);
			arquivoOS.append(rota.getCobrancaGrupo().getDescricaoAbreviada() + "|");

			// Vencimento documento
			Integer numeroDiasVencto = repositorioExecucaoOrdemServico.obterNumDiasVencimentoCobrancaAcao(idOS);
			arquivoOS.append(Util.formatarData(Util.adicionarNumeroDiasDeUmaData(dtGeracaoOS, numeroDiasVencto)) + "|");

			// Código da rota
			arquivoOS.append(rota.getCodigo() + "|");

			// Sequencial da rota
			arquivoOS.append(this.getControladorImovel().pesquisarSequencialRota(idImovel) + "|");

	        // Endereco Atendimento
	        FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
	        filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidade));
	        filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroCep");
	        filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
	        filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro");
	        filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
	        filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
	        filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
	        filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro");
	        filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro");
	        filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio");
	        filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
	        filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
	        Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(getControladorUtil()
	        		.pesquisar(filtroLocalidade, Localidade.class));

	        arquivoOS.append(Util.formatarCampoParaConcatenacao(localidade.getEnderecoFormatadoTituloAbreviado()));

	        // TELEFONE DA LOCALIDADE COM DDD 
	        String dddMunicipio = "";
	        try {
	        	dddMunicipio = "" + localidade.getLogradouroBairro().getBairro().getMunicipio().getDdd();
	        } catch(NullPointerException ignorar) {}

	        String fone = localidade.getFone() == null ? "" : localidade.getFone();
	        arquivoOS.append(Util.formatarCampoParaConcatenacao(dddMunicipio + fone));

	        // Obtém a representação numérica do código de barras
	        String representacaoNumericaFormatada = "";
	        CobrancaDocumento cobrancaDocumento = repositorioExecucaoOrdemServico.pesquisarDocumentoCobrancaOS(idOS);

	        if (idServicoTipo == ServicoTipo.TIPO_VISITA_DE_COBRANCA ||
	        	idServicoTipo == ServicoTipo.TIPO_DESLIGAMENTO_RAMAL_AGUA_ORDEM_CAERN) {
				String representacaoNumerica = this.getControladorArrecadacao().
						obterRepresentacaoNumericaCodigoBarra(5,
									cobrancaDocumento.getValorDocumento(),
									cobrancaDocumento.getLocalidade().getId(),
									cobrancaDocumento.getImovel().getId(),
									null, null, null, null,
									String.valueOf(cobrancaDocumento.getNumeroSequenciaDocumento()),
									cobrancaDocumento.getDocumentoTipo().getId(),
									null, null, null);

				// Formata a representação númerica do código de barras
				representacaoNumericaFormatada = representacaoNumerica.substring(0, 11)
						+ " " + representacaoNumerica.substring(11, 12)
						+ " " + representacaoNumerica.substring(12, 23)
						+ " " + representacaoNumerica.substring(23, 24)
						+ " " + representacaoNumerica.substring(24, 35)
						+ " " + representacaoNumerica.substring(35, 36)
						+ " " + representacaoNumerica.substring(36, 47)
						+ " " + representacaoNumerica.substring(47, 48);
	        }
			arquivoOS.append(representacaoNumericaFormatada + "|");

			// Número Sequência Documento Cobrança
			arquivoOS.append(cobrancaDocumento.getNumeroSequenciaDocumento() + "|");

			// Quantidade Economias por Categoria (RES, COM, IND, PUB)
			Collection<Categoria> categorias = getControladorImovel().obterQuantidadeEconomiasCategoria(imovel);

			int[] quantidades = { 0, 0, 0, 0 };

			for (Categoria c : categorias) {
				int qtd = c.getQuantidadeEconomiasCategoria();

				if (c.getId() == Categoria.RESIDENCIAL_INT)
					quantidades[0] = qtd;
				else if (c.getId() == Categoria.COMERCIAL_INT)
					quantidades[1] = qtd;
				else if (c.getId() == Categoria.INDUSTRIAL_INT)
					quantidades[2] = qtd;
				else if (c.getId() == Categoria.PUBLICO_INT)
					quantidades[3] = qtd;
			}

			arquivoOS.append(String.format("%s|%s|%s|%s|", quantidades[0],
					quantidades[1], quantidades[2], quantidades[3]));

			// Validade documento
			Integer numeroDiasValidade = repositorioExecucaoOrdemServico.obterNumDiasValidadeCobrancaAcao(idOS);
			arquivoOS.append(Util.formatarData(Util.adicionarNumeroDiasDeUmaData(dtGeracaoOS, numeroDiasValidade)) + "|");
			
			arquivoOS.append(System.getProperty("line.separator"));
		} catch(ErroRepositorioException e){
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}

		return arquivoOS;
	}

	/**
	 *
	 * [UC1497] Gerar Arquivo Texto de Ordens de Servico para Smartphone
	 * [IT0021] Selecionar Débitos da OS
	 * 
	 * @author Hugo Azevedo
	 * @date 24/10/2013
	 */
	private StringBuilder gerarArquivoTextoExecucaoOSDebitos(Integer idOS) throws ControladorException{
		StringBuilder retorno = new StringBuilder();
		try {
			//[IT0020] Obter Itens do Documento de Cobrança
			Collection<Object[]> colecaoItensDocCobranca = this.repositorioExecucaoOrdemServico.obterItensDocCobranca(idOS);
			if(colecaoItensDocCobranca != null && !colecaoItensDocCobranca.isEmpty()){
				Iterator<Object[]> it = colecaoItensDocCobranca.iterator();
				while(it.hasNext()){
					Object[] obj = it.next();
					//Tipo de registro
					retorno.append("17|");
					//Id da Ordem de Serviço Informada
					retorno.append(idOS.toString()+"|");
					//Ano/mês da Conta
					retorno.append(Util.formatarAnoMesParaMesAno((Integer)obj[0])+"|");
					//Data de Vencimento da Conta
					retorno.append(Util.formatarData((Date)obj[1])+"|");
					//Situação da Conta
					retorno.append((String)obj[2]+"|");
					//Valor da Conta
					retorno.append(Util.formatarMoedaReal((BigDecimal)obj[3]));
					//Pula de linha
					retorno.append(System.getProperty("line.separator"));
				}
			}
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
		return retorno;
	}
	
	private byte[] gerarArquivoTextoIdaExecucaoOSCobrancaEmByte(StringBuilder arquivo) throws ControladorException{
		byte[] retorno = null;
		BufferedWriter out = null;
		try {
			long timestamp = System.currentTimeMillis();
            File leitura = new File("GSANEOS_" + timestamp + ".txt");
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(leitura.getAbsolutePath())));
            out.write(arquivo.toString());
            out.flush();
            retorno = Util.converterFileParaArrayByte(leitura);
            leitura.delete();
        } catch (IOException e) {
            sessionContext.setRollbackOnly();
            throw new ControladorException("erro.sistema", e);
        } finally {
        	try { out.close(); } catch(Exception ignorar) {}
        }
		return retorno;
	}
	
	/**
	 * [UC1498] - Consultar Arquivo Texto de Ordens de Serviço para Smartphone (Novo)
	 *
	 * Foi necessário criar outro método, pois a consulta do agente comercial foi alterada.
	 *
	 * @author Jean Varela
	 * @date   19/11/2015	
	 */
	public Collection<AgenteComercialHelper>  pesquisarColecaoAgenteComercial(Integer idEmpresa) throws ControladorException{
		try {

			Collection<Object[]> retornoQuery = this.repositorioExecucaoOrdemServico.obterColecaoAgenteComercial(idEmpresa);
			Collection<AgenteComercialHelper> retorno = new ArrayList<AgenteComercialHelper>();

			Iterator<Object[]> it = retornoQuery.iterator();
			while (it.hasNext()) {
				AgenteComercialHelper helper = new AgenteComercialHelper();
				Object[] obj = (Object[]) it.next();

				helper.setId((Integer) obj[0]);

				String nomeCliente = (String) obj[1];
				String nomeFuncionario = (String) obj[2];

				if (nomeFuncionario != null && !"".equals(nomeFuncionario))
					helper.setNome(nomeFuncionario);
				else
					helper.setNome(nomeCliente);

				retorno.add(helper);
			}

			return retorno;
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	/**
	 * [UC1499] Consultar Dados Arquivo Texto OS Cobrança para Smartphone
	 * Retorna as informações das ordens de serviço
	 * 
	 * @author Bruno Barros,Vivianne Sousa
	 * @date 27/06/2013,24/11/2015
	 * 
	 * @param Integer
	 * 
	 * @return Collection<OrdemServicoCobrancaSmartphoneHelper>
	 * @throws ControladorException
	 */
	public Collection<OrdemServicoCobrancaSmartphoneHelper> pesquisarDadosOrdensServicoCobrancaSmartphone(
			Integer idArquivoTexto) throws ControladorException {
		Collection<OrdemServicoCobrancaSmartphoneHelper> colecaoHelper = null;

		try {
			Collection<Object[]> colecaoDadosOrdensServico = this.repositorioExecucaoOrdemServico
					.pesquisarDadosOrdensServicoCobrancaSmartphone(idArquivoTexto);

			if (colecaoDadosOrdensServico != null && !colecaoDadosOrdensServico.isEmpty()) {
				Iterator<Object[]> iterator = colecaoDadosOrdensServico.iterator();
				colecaoHelper = new ArrayList<OrdemServicoCobrancaSmartphoneHelper>();

				while (iterator.hasNext()) {
					Object[] dadosOrdemServico = (Object[]) iterator.next();
					OrdemServicoCobrancaSmartphoneHelper helper = new OrdemServicoCobrancaSmartphoneHelper();

					if (dadosOrdemServico[0] != null) {
						Integer idOrdemServico = (Integer) dadosOrdemServico[0];
						helper.setIdOrdemServico(idOrdemServico.toString());
					}
					if (dadosOrdemServico[1] != null) {
						Integer idImovel = (Integer) dadosOrdemServico[1];
						helper.setMatricula(idImovel.toString());
					}
					if (dadosOrdemServico[2] != null) {
						Short situacaoOS = (Short) dadosOrdemServico[2];
						helper.setCodigoSituacao(situacaoOS.toString());
						// 2.3.1. Pendente (Caso ORSE_CDSITUACAO da tabela ORDEM_SERVICO igual a 1).
						if (situacaoOS.compareTo(ConstantesSistema.SIM) == 0) {
							helper.setSituacao("Pendente");
						}
						// 2.3.2. Encerrada (Caso ORSE_CDSITUACAO da tabela ORDEM_SERVICO igual a 2).
						if (situacaoOS.compareTo(ConstantesSistema.NAO) == 0) {
							helper.setSituacao("Encerrada");
						}
					}
					if (dadosOrdemServico[3] != null) {
						Date dataRecebimento = (Date) dadosOrdemServico[3];
						helper.setDataHoraRecebimento(Util.formatarDataComHora(dataRecebimento));
					}
					// 2.5. Conferida
					if (dadosOrdemServico[4] != null) {
						Integer indicadorConferida = (Integer) dadosOrdemServico[4];
						// 2.5.1. Sim (Caso ATRV_ICCONFERIDA da tabela
						// ARQ_TXT_RET_VISITA_CAMPO com valor igual a 1).
						if (indicadorConferida.compareTo(new Integer(ConstantesSistema.SIM)) == 0) {
							helper.setOrdemServicoConferida("Sim");
						}
						// 2.5.2. Não (Caso ATRV_ICCONFERIDA da tabela
						// ARQ_TXT_RET_VISITA_CAMPO com valor igual a 2).
						if (indicadorConferida.compareTo(new Integer(ConstantesSistema.NAO)) == 0) {
							helper.setOrdemServicoConferida("Não");
						}
					}
					// SB0002 - Informações do Imóvel.
					// 1. Inscrição
					String inscricao = this.getControladorImovel()
						.pesquisarInscricaoImovel(new Integer(helper.getMatricula()));
					helper.setInscricao(inscricao);

					// 2. Endereço Abreviado <<Inclui>> [UC0483 - Obter Endereço Abreviado]
					String enderecoAbreviado = this.getControladorEndereco()
							.obterEnderecoAbreviadoImovel(new Integer(helper.getMatricula()));
					helper.setEnderecoAbreviado(enderecoAbreviado);

					// 3. Categoria Principal <<Inclui>> [UC0306 - Obter Principal Categoria do Imóvel]
					Categoria categoria = getControladorImovel()
							.obterPrincipalCategoriaImovel(new Integer(helper.getMatricula()));
					helper.setCategoriaPrincipal(categoria.getDescricao());

					// 4. Quantidade de Economias <<Inclui>> [UC0062 - Obter Quantidade de Economias]
					Imovel imovel = new Imovel();
					imovel.setId(new Integer(helper.getMatricula()));
					Integer qtdEconomiaImovel = this.getControladorImovel().obterQuantidadeEconomias(imovel);
					helper.setQuantidadeEconomias(qtdEconomiaImovel.toString());

					// 5. Grupo de Faturamento
					if (dadosOrdemServico[5] != null) {
						Integer grupoFaturamento = (Integer) dadosOrdemServico[5];
						helper.setGrupoFaturamento(grupoFaturamento.toString());
					}
					// 6. Situação da Ligação de água
					if (dadosOrdemServico[6] != null) {
						helper.setSituacaoLigacaoAgua((String) dadosOrdemServico[6]);
					}
					// 7. Consumo Médio <<Inclui>> [UC0068 - Obter Consumo Médio do Imóvel]
					Integer consumoMedio = this.getControladorMicromedicao()
							.pesquisarConsumoMedioImovel(new Integer(helper.getMatricula()));
					if (consumoMedio != null && !consumoMedio.equals("")) {
						helper.setConsumoMedio(consumoMedio.toString());
					}
					// 8. Situação da Ligação de Esgoto
					if (dadosOrdemServico[7] != null) {
						helper.setSituacaoLigacaoEsgoto((String) dadosOrdemServico[7]);
					}
					// 9. Consumo Fixo de Esgoto
					if (dadosOrdemServico[8] != null) {
						Integer consumoFixoEsgoto = (Integer) dadosOrdemServico[8];
						helper.setConsumoFixoEsgoto(consumoFixoEsgoto.toString());
					}
					if (dadosOrdemServico[9] != null) {
						Date ultimaAlteracao = (Date) dadosOrdemServico[9];
						helper.setUltimaAlteracao(Util.formatarDataComHora(ultimaAlteracao));
					}
					// 11. Tipo de Serviço
					if (dadosOrdemServico[10] != null) {
						helper.setTipoServico((String) dadosOrdemServico[10]);
					}
					// 12. Indicador Fiscalizacao Infracao
					if (dadosOrdemServico[11] != null) {
						helper.setIndicadorFiscalizacaoInfracao(dadosOrdemServico[11] + "");
					}
					// 12 id do Arquivo
					helper.setIdArquivo(idArquivoTexto + "");

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
	 * [UC1500] - Consultar Dados OS de Cobrança para Smartphone
	 * Metodo que vai retornar as fotos de uma determinada ordem de servico passada no parametro.
	 * 
	 * @author Bruno Barros
	 * @date 04/07/2013
	 * 
	 * @param Integer
	 *            - Id do arquivo a qual a foto pertence
	 * @param Integer
	 *            - Id da Ordem de Servico das fotos
	 * 
	 * @return Collection<ExecucaoOSFoto> - Colecao das Fotos da OS
	 * @throws FachadaExpcetion
	 */
	public Collection<ExecucaoOSFoto> pesquisarFotosOSCobrancaSmartphone(
			Integer idArquivo, Integer idOS) throws ControladorException {

		FiltroExecucaoOSFoto filtro = new FiltroExecucaoOSFoto();
		filtro.adicionarParametro(new ParametroSimples(FiltroExecucaoOSFoto.ID_ORDEM_SERVICO, idOS));
		filtro.adicionarParametro(new ParametroSimples(FiltroExecucaoOSFoto.ID_ARQUIVO, idArquivo));
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroExecucaoOSFoto.CAMINHO_SITUACAO);
		Collection<ExecucaoOSFoto> colFoto = this.getControladorUtil()
				.pesquisar(filtro, ExecucaoOSFoto.class.getName());

		return colFoto;
	}
	
	/**
	 * [UC1500] Consultar Dados OS de Cobrança para Smartphone
	 * 
	 * @author Rafael Corrêa,Vivianne Sousa
	 * @date 18/06/2013, 25/11/2015
	 */
	public RelatorioErrosEncerramentoOSCobrancaBean efetuarExecucaoOSCobrancaSmartphone(Integer idArquivo, Integer idOS, DadosDebitoOSCobrancaSmartphoneHelper helperDebito, Usuario usuario) throws ControladorException {				
		
		IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();
		integracaoComercialHelper.setVeioEncerrarOS(true);
		integracaoComercialHelper.setUsuarioLogado(usuario);
		OrdemServico ordemServico = getControladorOrdemServico().recuperaOSPorId(idOS);
		RelatorioErrosEncerramentoOSCobrancaBean bean = null;
		
		try{		
			Object[] dadosEncerramento = repositorioExecucaoOrdemServico.pesquisarDadosEncerramentoOSCobrancaSmartphone(idArquivo, idOS);
			
			Date dataExecucao = (Date) dadosEncerramento[0];
			Integer idMotivoEncerramento = (Integer) dadosEncerramento[1];
			Short indicadorExecucao = (Short) dadosEncerramento[2];
			String parecerEncerramento = (String) dadosEncerramento[3];
			Short indicadorVistoria = (Short) dadosEncerramento[4];
			
			Collection<ExecucaoOSFoto> fotos = repositorioExecucaoOrdemServico.pesquisarFotosOSCobrancaSmartphone(idArquivo, idOS);
			
			Collection<OrdemServicoFoto> colecaoOrdemServicoFoto = this.criarColecaoOrdemServicoFoto(fotos, ordemServico);
			
			if (indicadorExecucao.equals(ConstantesSistema.SIM)) { 
		
				Short indicadorPavimento = (Short) dadosEncerramento[5];
	
				String idServicoTipoReferenciaOS = null;
				if (dadosEncerramento[6] != null) {
					idServicoTipoReferenciaOS = ((Integer) dadosEncerramento[6]).toString();
				}
				
				String idOSReferencia = null;
				if (ordemServico.getOsReferencia() != null) {
					idOSReferencia = ordemServico.getOsReferencia().getId().toString();
				}
				
				if (idServicoTipoReferenciaOS != null) {
					getControladorOrdemServico().encerrarOSComExecucaoComReferencia(
							idOS, 
							dataExecucao, // dataEncerramento? 
							usuario, 
							idMotivoEncerramento.toString(), 
							new Date(), 
							parecerEncerramento, 
							""+indicadorPavimento, 
							null, //pavimento? 
							null, //idTipoRetornoOSReferida? 
							null, //indicadorDeferimento? 
							null, //indicadorTrocaServicoTipo? 
							ordemServico.getServicoTipo().getId().toString(), 
							idOSReferencia, 
							idServicoTipoReferenciaOS, 
							null, //colecaoManterDadosAtividadesOrdemServicoHelper? 
							null, //integracaoComercialHelper? 
							ordemServico.getServicoTipo().getId().toString(), //tipoServicoOSId? 
							null, //osFiscalizacao? 
							""+indicadorVistoria, 
							null, //codigoRetornoVistoriaOs? 
							null, //ordemServicoBoletim? 
							ConstantesSistema.SIM, //indicadorServicoAceito? 
							colecaoOrdemServicoFoto);
				} else {
					getControladorOrdemServico().encerrarOSComExecucaoSemReferencia(
							idOS, 
							dataExecucao, // dataEncerramento? 
							usuario, 
							idMotivoEncerramento.toString(), 
							new Date(), 
							parecerEncerramento, 
							""+indicadorPavimento, 
							null, //pavimento? 
							null, //colecaoManterDadosAtividadesOrdemServicoHelper? 
							null, //integracaoComercialHelper? 
							ordemServico.getServicoTipo().getId().toString(), //tipoServicoOSId? 
							null, //osFiscalizacao? 
							""+indicadorVistoria, 
							null, //codigoRetornoVistoriaOs?
							null, //ordemServicoPavimento?
							null, //ordemServicoBoletim? 
							ConstantesSistema.SIM, //indicadorServicoAceito? 
							colecaoOrdemServicoFoto);
				}
				
				if (ordemServico.getServicoTipo().getConstanteFuncionalidadeTipoServico().equals(ServicoTipo.TIPO_CORTE_LIGACAO_AGUA)) {
					this.obterDadosCorteOSCobrancaSmartphone(idArquivo, ordemServico, helperDebito, integracaoComercialHelper);
					getControladorLigacaoAgua().efetuarCorteLigacaoAgua(integracaoComercialHelper);
				
				} else if (ordemServico.getServicoTipo().getConstanteFuncionalidadeTipoServico().equals(ServicoTipo.FISCALIZACAO_IMOVEL)) {
					informarRetornoFiscalizacaoOSCobrancaSmartphone(idArquivo, ordemServico, usuario);
				
				} 
			} else {
				getControladorOrdemServico().encerrarOSSemExecucao(idOS, 
						dataExecucao, 
						usuario, 
						idMotivoEncerramento.toString(), 
						new Date(), 
						parecerEncerramento, 
						null, //osFiscalizacao? 
						""+indicadorVistoria, 
						null, //codigoRetornoVistoriaOs? 
						null, //ordemServicoBoletim? 
						ConstantesSistema.SIM, //indicadorServicoAceito? 
						colecaoOrdemServicoFoto);
			}
	//			// Atualiza o cliente caso necessário.
	//			this.getControladorAtendimentoPublico().atualizarClienteAPartirDoDispositivoMovelCobrancaSmartphone( idArquivo, idOS, usuario );
			
			repositorioExecucaoOrdemServico.atualizarSituacaoOSSmartphone(idArquivo, idOS, OrdemServico.SITUACAO_ENCERRADO);
			
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		} catch (ControladorException e) {
			sessionContext.setRollbackOnly();
			bean = new RelatorioErrosEncerramentoOSCobrancaBean();
			bean.setNumeroOS(idOS.toString());
			bean.setErro(ConstantesAplicacao.get(e.getMessage(), e.getParametroMensagem().toArray(new String[e.getParametroMensagem().size()])));
		}
	
		return bean;
	}
	
	/**
	 * [UC1500] - Consultar Dados OS de Cobrança para Smartphone
	 * Metodo que vai retornar as fotos de uma determinada ordem de servico passada no parametro.
	 * 
	 * @author Bruno Barros
	 * @date 04/07/2013
	 * 
	 * @param Integer
	 *            - Id do arquivo a qual a foto pertence
	 * @param Integer
	 *            - Id da Ordem de Servico das fotos
	 * @param Integer
	 *            - Id da Situacao da foto
	 * 
	 * @return ExecucaoOSFoto - Dados da foto
	 * @throws FachadaExpcetion
	 */
	public ExecucaoOSFoto pesquisarFotosOSCobrancaSmartphone(Integer idArquivo,
			Integer idOS, Integer idSituacao) throws ControladorException {
		
		FiltroExecucaoOSFoto filtro = new FiltroExecucaoOSFoto();
		filtro.adicionarParametro(new ParametroSimples(FiltroExecucaoOSFoto.ID_ORDEM_SERVICO, idOS));
		filtro.adicionarParametro(new ParametroSimples(FiltroExecucaoOSFoto.ID_ARQUIVO, idArquivo));
		filtro.adicionarParametro(new ParametroSimples(FiltroExecucaoOSFoto.ID_SITUACAO, idSituacao));
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroExecucaoOSFoto.CAMINHO_SITUACAO);
		ExecucaoOSFoto foto = (ExecucaoOSFoto) Util.retonarObjetoDeColecao(this
				.getControladorUtil().pesquisar(filtro,ExecucaoOSFoto.class.getName()));

		return foto;
	}
	
	/**
	 * [UC1500] Consultar Dados OS de Cobrança para Smartphone
	 * 
	 * @author Rafael Corrêa
	 * @date 25/06/2013
	 */
	private Collection<OrdemServicoFoto> criarColecaoOrdemServicoFoto(Collection<ExecucaoOSFoto> fotos, OrdemServico ordemServico) throws ControladorException {
		Collection<OrdemServicoFoto> retorno = new ArrayList<OrdemServicoFoto>();
		
		for (ExecucaoOSFoto execucaoOSFoto : fotos) {
			OrdemServicoFoto osFoto = new OrdemServicoFoto();
			
			osFoto.setOrdemServico(ordemServico);
			osFoto.setFotoSituacao(execucaoOSFoto.getFotoSituacaoOrdemServico());
			osFoto.setDataFoto(execucaoOSFoto.getDataFoto());
			osFoto.setDescricaoFoto(execucaoOSFoto.getDescricaoFoto());
			osFoto.setFotoOrdemServico(execucaoOSFoto.getFotoOrdemServico());
			osFoto.setObservacaoFoto(execucaoOSFoto.getObservacaoFoto());
			osFoto.setCoordenadaX(execucaoOSFoto.getCoordenadaX());
			osFoto.setCoordenadaY(execucaoOSFoto.getCoordenadaY());
			osFoto.setUltimaAlteracao(new Date());			
			retorno.add(osFoto);
		}
		return retorno;
	}
	
	/**
	 * [UC1500] Consultar Dados OS de Cobrança para Smartphone
	 * 
	 * @author Rafael Corrêa
	 * @date 18/06/2013
	 */
	private void obterDadosCorteOSCobrancaSmartphone(Integer idArquivo, OrdemServico ordemServico, 
			DadosDebitoOSCobrancaSmartphoneHelper helperDebito, IntegracaoComercialHelper integracaoComercialHelper) throws ControladorException {
		
		try {
			Object[] dadosCorte = repositorioExecucaoOrdemServico.pesquisarDadosCorteOSCobrancaSmartphone(idArquivo, ordemServico.getId());
			
			if (dadosCorte != null) {
				DadosEfetuacaoCorteLigacaoAguaHelper helperCorte = new DadosEfetuacaoCorteLigacaoAguaHelper();
				
				Imovel imovel = ordemServico.getImovel();
				
				LigacaoAgua ligacaoAgua = new LigacaoAgua();
				ligacaoAgua.setId(imovel.getId());
				
				MotivoCorte motivoCorte = null;
				CorteTipo corteTipo = null;
				HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico();
				
				if (dadosCorte[0] != null) {
					motivoCorte = new MotivoCorte();
					motivoCorte.setId((Integer) dadosCorte[0]);
				}
				
				ligacaoAgua.setMotivoCorte(motivoCorte);
				
				if (dadosCorte[1] != null) {
					corteTipo = new CorteTipo();
					corteTipo.setId((Integer) dadosCorte[1]);
				}
				
				ligacaoAgua.setCorteTipo(corteTipo);
				
				if (dadosCorte[2] != null) {
					ligacaoAgua.setNumeroSeloCorte((Integer) dadosCorte[2]);
				} else {
					ligacaoAgua.setNumeroSeloCorte(null);
				}
				
				if (hidrometroInstalacaoHistorico != null) {
					if (dadosCorte[3] != null) {
						hidrometroInstalacaoHistorico.setNumeroLeituraCorte((Integer) dadosCorte[3]);
					} else {
						hidrometroInstalacaoHistorico.setNumeroLeituraCorte(null);
					}
					
					hidrometroInstalacaoHistorico.setUltimaAlteracao(new Date());
				}
				
				if (dadosCorte[4] != null) {
					ligacaoAgua.setDataCorte((Date) dadosCorte[4]);
				} else {
					ligacaoAgua.setDataCorte(null);
				}
				
				LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
				ligacaoAguaSituacao.setId(LigacaoAguaSituacao.CORTADO);
				
				imovel.setLigacaoAguaSituacao(ligacaoAguaSituacao);
				imovel.setUltimaAlteracao(new Date());
				ligacaoAgua.setUltimaAlteracao(new Date());
				ligacaoAgua.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);
				imovel.setLigacaoAgua(ligacaoAgua);
				
				setDadosDebitoOS(ordemServico, helperDebito, integracaoComercialHelper);
				helperCorte.setQtdeParcelas(new Integer(integracaoComercialHelper.getQtdParcelas()));
				
				ordemServico.setIndicadorComercialAtualizado(ConstantesSistema.SIM);
				ordemServico.setUltimaAlteracao(new Date());
				
				helperCorte.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);
				helperCorte.setLigacaoAgua(ligacaoAgua);
				helperCorte.setImovel(imovel);
				helperCorte.setOrdemServico(ordemServico);
				
				integracaoComercialHelper.setOrdemServico(ordemServico);
				integracaoComercialHelper.setDadosEfetuacaoCorteLigacaoAguaHelper(helperCorte);
			}
			
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	/**
	 * [UC1500] Consultar Dados OS de Cobrança para Smartphone
	 * 
	 * @author Rafael Corrêa
	 * @date 20/06/2013
	 */
	private void setDadosDebitoOS(OrdemServico ordemServico,
			DadosDebitoOSCobrancaSmartphoneHelper helperDebito,
			IntegracaoComercialHelper integracaoComercialHelper) throws ControladorException {
		
		if (helperDebito != null) {
			ordemServico.setServicoNaoCobrancaMotivo(helperDebito.getServicoNaoCobrancaMotivo());
			ordemServico.setValorAtual(helperDebito.getValorDebito());
			ordemServico.setPercentualCobranca(helperDebito.getPercentualCobranca());
			integracaoComercialHelper.setQtdParcelas(""+helperDebito.getQuantidadeParcelas());
		} else {
			if (ordemServico.getServicoTipo().getDebitoTipo() != null) {
				BigDecimal valorDebito = getControladorAtendimentoPublico().obterValorDebito(ordemServico.getServicoTipo().getId(), 
						ordemServico.getImovel().getId(), MedicaoTipo.LIGACAO_AGUA.shortValue());

				if (valorDebito != null) {
					ordemServico.setValorAtual(valorDebito);
					ordemServico.setPercentualCobranca(new BigDecimal(100));
					integracaoComercialHelper.setQtdParcelas("1");
				}
			}
		}
	}

	/**
	 * [UC1500] Consultar Dados OS de Cobrança para Smartphone
	 * 
	 * @author Rafael Corrêa
	 * @date 21/06/2013
	 */
	private void informarRetornoFiscalizacaoOSCobrancaSmartphone(Integer idArquivo, OrdemServico ordemServico, Usuario usuario) throws ControladorException {
		
		Integer idCobrancaDocumento = null;
		String codigoDocumentoEntregue = null;
		
		Collection<SituacaoEncontradaHelper> colecaoSituacaoEncontrada = this.pesquisarDadosFiscalizacaoOSCobrancaSmartphone(idArquivo, ordemServico.getId());
		if (colecaoSituacaoEncontrada != null && !colecaoSituacaoEncontrada.isEmpty()) {
			SituacaoEncontradaHelper helper = (SituacaoEncontradaHelper) colecaoSituacaoEncontrada.iterator().next();
			idCobrancaDocumento = helper.getIdDocumentoCobranca();
			if (helper.getCodigoDocumentoEntregue() != null) {
				if(helper.getCodigoDocumentoEntregue().equals(DocumentoTipo.AUTO_DE_INFRACAO)){
					codigoDocumentoEntregue = ConstantesSistema.DOCUMENTO_ENTREGUE_AUTO_INFRACAO.toString();
				}else if(helper.getCodigoDocumentoEntregue().equals(DocumentoTipo.SOLICITACAO_DE_COMPARECIMENTO)){
					codigoDocumentoEntregue = ConstantesSistema.DOCUMENTO_ENTREGUE_SOLICITACAO_COMPARECIMENTO.toString();
				}
			}
		}
		LigacaoEsgoto ligacaoEsgoto = getControladorUtil().pesquisar(LigacaoEsgoto.class, ordemServico.getImovel().getId());
		
		getControladorOrdemServico().informarRetornoOSFiscalizacao(ordemServico.getId(), 
				codigoDocumentoEntregue, 
				ordemServico.getImovel().getLigacaoAguaSituacao().getId(), 
				ordemServico.getImovel().getLigacaoEsgotoSituacao().getId(),
				ordemServico.getImovel().getId(), 
				MedicaoTipo.LIGACAO_AGUA.toString(), //indicadorMedicaoTipo? 
				null, //indicadorGeracaoDebito? 
				idCobrancaDocumento, 
				usuario, 
				null, //dadosAutoInfracao? 
				ConstantesSistema.SIM, //confirmaAtualizacaoSituacaoLigacaoAgua? 
				ConstantesSistema.SIM, //confirmaAtualizacaoSituacaoLigacaoEsgoto? 
				colecaoSituacaoEncontrada, 
				ligacaoEsgoto, //ligacaoEsgoto? 
				ordemServico.getImovel().getLigacaoEsgotoSituacao().getId().toString());
	}
	
	/**
	 * [UC1499] Consultar Dados Arquivo Texto OS Cobranca para Smartphone
	 * 
	 * @author Rafael Correa
	 * @date 25/06/2013
	 */
	public Collection<SituacaoEncontradaHelper> pesquisarDadosFiscalizacaoOSCobrancaSmartphone(Integer idArquivo, Integer idOS)
			throws ControladorException {
		
		Collection<SituacaoEncontradaHelper> retorno = new ArrayList<SituacaoEncontradaHelper>();
		
		try {
			Collection<Object[]> colecaoDadosOSFiscalizacao = repositorioExecucaoOrdemServico.pesquisarDadosFiscalizacaoOSCobrancaSmartphone(idArquivo, idOS);
			
			for (Object[] dadosOSFiscalizacao : colecaoDadosOSFiscalizacao) {
				SituacaoEncontradaHelper helper = new SituacaoEncontradaHelper();
				
				helper.setIdDocumentoCobranca((Integer) dadosOSFiscalizacao[0]);
				helper.setCodigoDocumentoEntregue((Integer) dadosOSFiscalizacao[1]);
				
				helper.setDataFiscalizacao((Date) dadosOSFiscalizacao[2]);
				
				Integer idFiscalizacaoSituacao = (Integer) dadosOSFiscalizacao[3];
				
				FiltroFiscalizacaoSituacao filtroFiscalizacaoSituacao = new FiltroFiscalizacaoSituacao();
				filtroFiscalizacaoSituacao.adicionarParametro(new ParametroSimples(FiltroFiscalizacaoSituacao.ID, idFiscalizacaoSituacao));
				FiscalizacaoSituacao fiscalizacaoSituacao = ( FiscalizacaoSituacao )Util.retonarObjetoDeColecao( this.getControladorUtil().pesquisar(filtroFiscalizacaoSituacao, FiscalizacaoSituacao.class.getName() ) );
				
				helper.setFiscalizacaoSituacao(fiscalizacaoSituacao);
				
				retorno.add(helper);
			}
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
		
		return retorno;
	}
	
	/**
	 * [UC-1498] Consultar Arquivo Texto de Ordens de Serviço para Smartphone
	 * [IT0016] Excluir Arquivo
	 * 
	 * Exclui os arquivos informados
	 * 
	 * @author Bruno Barros
	 * @date 19/09/2013
	 * @param ids
	 * @throws ErroRepositorioException
	 * 
	 */
	public void excluirArquivoTextoOrdensServicoCobranca(String[] ids)
			throws ControladorException {

		try {
			// Verificamos se todos os arquivos informados estão com situação de
			// disponível
			Integer qtdOSSituacaoDiferenteDisponivel = this.repositorioExecucaoOrdemServico
													   .verificarSituacaoArquivosOSCobrancaParaExclusao(ids);

			if (qtdOSSituacaoDiferenteDisponivel != null
				&& qtdOSSituacaoDiferenteDisponivel.intValue() > ConstantesSistema.ZERO) {
				throw new ControladorException("atencao.nem_todas_os_com_situacao_disponível");
			}

			// Excluimos os Arquivos
			this.repositorioExecucaoOrdemServico.excluirArquivosOSCobranca(ids);

		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	
	/**
	 * [UC1487] Processar Arquivo Texto Dispositivo Movel Execução de OS
	 * 
	 * @author Bruno Barros, Mariana Victor, Rodrigo Cabral
	 * @date 18/06/2013, 12/11/2013, 01/12/2015
	 * 
	 * @author André Miranda
	 * @date 24/12/2015
	 * 
	 * @param buffer Buffer do arquivo texto
	 * @param pasta Pasta onde o arquivo zip foi extraído
	 * @throws ControladorException
	 */
	public void atualizarMovimentacaoExecucaoOS(BufferedReader buffer, File pasta) throws ControladorException {
		String linha = "";

		try {
			if (buffer == null) {
				throw new ControladorException("atencao.arquivo_sem_dados");
			}

			Integer idArquivo = null;
			Short icFinalizacao = null;

			while ((linha = buffer.readLine()) != null) {
				List<String> colunas = Util.split(linha);
				int tipoRetorno = Integer.parseInt(colunas.get(0));

				if (tipoRetorno == 0) {
					if (colunas.size() < 3) {
						throw new ControladorException("atencao.arquivo_invalido");
					}
					idArquivo = Integer.parseInt(colunas.get(1));
					icFinalizacao = Short.parseShort(colunas.get(2));
					continue;
				}

				if (tipoRetorno == 1) {
					ExecucaoOSOrdemServico os = new ExecucaoOSOrdemServico(idArquivo, linha);
					FiltroExecucaoOSOrdemServico filtro = new FiltroExecucaoOSOrdemServico();
					filtro.adicionarParametro(new ParametroSimples(FiltroExecucaoOSOrdemServico.ID_ARQUIVO, idArquivo));
					filtro.adicionarParametro(new ParametroSimples(FiltroExecucaoOSOrdemServico.ID_ORDEM_SERVICO, os.getOrdemServico().getId()));
					ExecucaoOSOrdemServico objetoBase = (ExecucaoOSOrdemServico)
							Util.retonarObjetoDeColecao(getControladorUtil().pesquisar(filtro, ExecucaoOSOrdemServico.class));

					// Caso já exista na base, descarta ou não dependendo da flag
					if (objetoBase == null) {
						getControladorUtil().inserir(os);
					} else if (!DESCARTARDADOSDUPLICADOSOSCOBRANCASMARTPHONE) {
						getControladorUtil().inserir(os);
					}
					continue;
				}

				if (tipoRetorno == 2) {
					ExecucaoOSCliente oscc = new ExecucaoOSCliente(idArquivo, linha);

					FiltroExecucaoOSCliente filtro = new FiltroExecucaoOSCliente();
					filtro.adicionarParametro(new ParametroSimples(FiltroExecucaoOSCliente.ID_ARQUIVO, idArquivo));
					filtro.adicionarParametro(new ParametroSimples(FiltroExecucaoOSCliente.ID_ORDEM_SERVICO, oscc.getOrdemServico().getId()));
					ExecucaoOSCliente objetoBase = (ExecucaoOSCliente)
							Util.retonarObjetoDeColecao(getControladorUtil().pesquisar(filtro, ExecucaoOSCliente.class));

					// Caso já exista na base, descarta ou não dependendo da flag
					if (objetoBase == null) {
						getControladorUtil().inserir(oscc);
					} else if (!DESCARTARDADOSDUPLICADOSOSCOBRANCASMARTPHONE) {
						getControladorUtil().inserir(oscc);
					}
					continue;
				}

				if (tipoRetorno == 3) {
					ExecucaoOSCorte osco = new ExecucaoOSCorte(idArquivo, linha);
					FiltroExecucaoOSCorte filtro = new FiltroExecucaoOSCorte();
					filtro.adicionarParametro(new ParametroSimples(FiltroExecucaoOSCorte.ID_ARQUIVO, idArquivo));
					filtro.adicionarParametro(new ParametroSimples(FiltroExecucaoOSCorte.ID_ORDEM_SERVICO, osco.getOrdemServico().getId()));
					ExecucaoOSCorte objetoBase = (ExecucaoOSCorte)
							Util.retonarObjetoDeColecao(getControladorUtil().pesquisar(filtro, ExecucaoOSCorte.class));

					// Caso já exista na base, descarta ou não dependendo da flag
					if (objetoBase == null) {
						getControladorUtil().inserir(osco);
					} else if (!DESCARTARDADOSDUPLICADOSOSCOBRANCASMARTPHONE) {
						getControladorUtil().inserir(osco);
					}
					continue;
				}

				if (tipoRetorno == 5) {
					ExecucaoOSFiscalizacao osfi = new ExecucaoOSFiscalizacao(idArquivo, linha);
					FiltroExecucaoOSFiscalizacao filtro = new FiltroExecucaoOSFiscalizacao();
					filtro.adicionarParametro(new ParametroSimples(FiltroExecucaoOSFiscalizacao.ID_ARQUIVO,idArquivo));
					filtro.adicionarParametro(new ParametroSimples(FiltroExecucaoOSFiscalizacao.ID_ORDEM_SERVICO, osfi.getOrdemServico().getId()));
					ExecucaoOSFiscalizacao objetoBase = (ExecucaoOSFiscalizacao)
							Util.retonarObjetoDeColecao(getControladorUtil().pesquisar(filtro, ExecucaoOSFiscalizacao.class));

					// Caso já exista na base, descarta ou não dependendo da flag
					if (objetoBase == null) {
						getControladorUtil().inserir(osfi);
					} else if (!DESCARTARDADOSDUPLICADOSOSCOBRANCASMARTPHONE) {
						getControladorUtil().inserir(osfi);
					}
					continue;
				}

				if (tipoRetorno == 6) {
					ExecucaoOSSituacoesEncontradas osse = new ExecucaoOSSituacoesEncontradas(idArquivo, linha);
					FiltroExecucaoOSSituacoesEncontradas filtro = new FiltroExecucaoOSSituacoesEncontradas();
					filtro.adicionarParametro(new ParametroSimples(FiltroExecucaoOSSituacoesEncontradas.ID_ARQUIVO, idArquivo));
					filtro.adicionarParametro(new ParametroSimples(FiltroExecucaoOSSituacoesEncontradas.ID_ORDEM_SERVICO, osse.getOrdemServico().getId()));
					filtro.adicionarParametro(new ParametroSimples(FiltroExecucaoOSSituacoesEncontradas.ID_FISCALIZACAO_SITUACAO, osse.getOrdemServico().getId()));
					ExecucaoOSSituacoesEncontradas objetoBase = (ExecucaoOSSituacoesEncontradas)
							Util.retonarObjetoDeColecao(getControladorUtil().pesquisar(filtro, ExecucaoOSSituacoesEncontradas.class));

					// Caso já exista na base, descarta ou não dependendo da flag
					if (objetoBase == null) {
						getControladorUtil().inserir(osse);
					} else if (!DESCARTARDADOSDUPLICADOSOSCOBRANCASMARTPHONE) {
						getControladorUtil().inserir(osse);
					}
					continue;
				}

				if (tipoRetorno == 7) {
					ExecucaoOSVisita osvi = new ExecucaoOSVisita(idArquivo, linha);
					FiltroExecucaoOSVisita filtro = new FiltroExecucaoOSVisita();
					filtro.adicionarParametro(new ParametroSimples(FiltroExecucaoOSVisita.ID_ARQUIVO, idArquivo));
					filtro.adicionarParametro(new ParametroSimples(FiltroExecucaoOSVisita.ID_ORDEM_SERVICO, osvi.getOrdemServico().getId()));
					ExecucaoOSVisita objetoBase = (ExecucaoOSVisita)
							Util.retonarObjetoDeColecao(getControladorUtil().pesquisar(filtro, ExecucaoOSVisita.class));

					// Caso já exista na base, descarta ou não dependendo da flag
					if (objetoBase == null) {
						getControladorUtil().inserir(osvi);
					} else if (!DESCARTARDADOSDUPLICADOSOSCOBRANCASMARTPHONE) {
						getControladorUtil().inserir(osvi);
					}
					continue;
				}

				if (tipoRetorno == 10) {
					int numeroOS = Integer.parseInt(colunas.get(1));
					int tipoFoto = Integer.parseInt(colunas.get(2));
					BigDecimal coordenadaX = new BigDecimal(colunas.get(3));
					BigDecimal coordenadaY = new BigDecimal(colunas.get(4));
					File file = new File(pasta, colunas.get(5));
					byte[] bytesFoto = FileUtils.readFileToByteArray(file);

					inserirFotoOrdemServicoCobrancaSmartphone(idArquivo, numeroOS, tipoFoto, bytesFoto, coordenadaX, coordenadaY);
					continue;
				}
			}

			if (icFinalizacao.shortValue() == SituacaoTransmissaoLeitura.FINALIZADO) {
				FiltroArquivoTextoOSCobranca filtro = new FiltroArquivoTextoOSCobranca();
				filtro.adicionarParametro(new ParametroSimples(FiltroArquivoTextoOSCobranca.ID, idArquivo));
				ArquivoTextoOSCobranca arquivo = (ArquivoTextoOSCobranca) Util.retonarObjetoDeColecao(
						getControladorUtil().pesquisar(filtro, ArquivoTextoOSCobranca.class));
				SituacaoTransmissaoLeitura situacaoTransmissaoLeitura = new SituacaoTransmissaoLeitura(new Integer(icFinalizacao));
				arquivo.setSituacao(situacaoTransmissaoLeitura);
				arquivo.setUltimaAlteracao(new Date());
				getControladorUtil().atualizar(arquivo);
			}
		} catch (Exception e) {
			throw new ControladorException(e.getMessage(), e);
		}
	}
	
	/**
	 * [UC1499] Consultar Dados Arquivo Texto OS Cobranca para Smartphone
	 * 
	 * @author Vivianne Sousa
	 * @date 02/12/2015
	 */
	public Collection<Localidade> pesquisarLocalidadesArquivo(Integer idArquivo ) 
			throws ControladorException {
		try {
			Collection<String> colDesLocalidade = this.repositorioExecucaoOrdemServico.pesquisarLocalidadesArquivo(idArquivo);
			Collection<Localidade> colLocalidade = null;
			if(!Util.isVazioOrNulo(colDesLocalidade)){
				colLocalidade = new ArrayList<Localidade>();
				for (String descricaoLocalidade : colDesLocalidade) {
					Localidade localidade = new Localidade();
					localidade.setDescricao(descricaoLocalidade);
					colLocalidade.add(localidade);
				}
			}
			return colLocalidade;
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
	}
	

	/**
	 * [UC-1487] Processar Arquivo Texto Dispositivo Movel Execução de OS
	 * 
	 * Metodo que ira pesquisar o arquivo que sera carregado no celular
	 * 
	 * @autor Bruno Barros,Jean Varela
	 * @date 17/06/2013, 01/12/2015
	 * 
	 * @param imei  - Imei do aparalho que ira receber o arquivo
	 * 
	 * @return array de bytes com o arquivo
	 * 
	 * @throws ControladorException
	 */
	public byte[] baixarArquivoTextoExecucaoOS(long imei) throws ControladorException {

		byte[] arquivo = null;
		
		// Localizamos o leiturista que possue o imei cadastrado
		FiltroLeiturista fl = new FiltroLeiturista();
		if (imei != 0) {
			fl.adicionarParametro(new ParametroSimples(FiltroLeiturista.IMEI, imei));
		} else {
			// fl.adicionarParametro(new ParametroSimples(FiltroLeiturista.MAC, mac));
		}

		Leiturista leiturista = (Leiturista) Util.retonarObjetoDeColecao(getControladorUtil().
																pesquisar(fl,Leiturista.class.getName()));

		if (leiturista == null) {
			throw new ControladorException("atencao.nenhum_leiturista_cadastrado_imei", null, imei + "");
		}

		// Localizamos o arquivo liberado
		Integer idArquivo;
		try {
			idArquivo = repositorioExecucaoOrdemServico.pesquisarArquivoTextoExecucaoOS(leiturista.getId());

			if (idArquivo == null) {
				throw new ControladorException("atencao.nenhum_arquivo_liberado_execucao_os", null, imei + "");
			}

			arquivo = gerarArquivoTextoIdaExecucaoOSCobranca(idArquivo);

			if (arquivo == null) {
				throw new ControladorException("atencao.nenhum_arquivo_liberado_execucao_os", null, imei + "");
			}
		} catch (ErroRepositorioException e) {
			throw new ControladorException(e.getMessage(), e);
		}
		
		return arquivo;
	}
	
	/**
	 * [UC1499] Consultar Dados Arquivo Texto OS Cobranca para Smartphone
	 * 
	 * @author Rafael Correa
	 * @date 01/07/2013
	 */
	public Collection<RelatorioErrosEncerramentoOSCobrancaBean> efetuarExecucaoColecaoOSCobrancaSmartphone(
			Integer idArquivo, Collection<Integer> idsOS, Usuario usuario) throws ControladorException{
		
		Collection<RelatorioErrosEncerramentoOSCobrancaBean> retorno = new ArrayList<RelatorioErrosEncerramentoOSCobrancaBean>();
		for (Integer idOS : idsOS) {
			
			try{
			RelatorioErrosEncerramentoOSCobrancaBean bean = this.efetuarExecucaoOSCobrancaSmartphone(idArquivo, idOS, null, usuario);
			if(bean != null){
				retorno.add(bean);
			}
			} catch (ControladorException e) {
				sessionContext.setRollbackOnly();
			} 
		}
		return retorno;
	}
	
	/**
	 * [UC1498] Consultar Arquivo Texto de Ordens de Serviço para Smartphone
	 * @author Vivianne Sousa
	 * @date 04/12/2015
	 */
	public void pesquisarArquivoLiberadoEmCampoPorAgenteComercial(List<ArquivoTxtOSCobrancaSmartphoneHelper> arquivos) throws ControladorException{
		try {
			Set<Integer> leiturista = new HashSet<Integer>();
			for (ArquivoTxtOSCobrancaSmartphoneHelper arquivo : arquivos) {
				Integer idLeiturista = arquivo.getIdLeiturista();
				//Verifica se existe arquivo liberado ou em campo para o agente comercial no banco de dados
				String dadosArquivo = repositorioExecucaoOrdemServico.pesquisarArquivoEmCampoPorAgenteComercial(idLeiturista);
				if(dadosArquivo != null && !dadosArquivo.equals("")){
					throw new ControladorException("atencao.agente_comercial_com_arquivo_liberado", null, dadosArquivo);
				}
				//Verifica se existe outro arquivo sendo liberado com o mesmo agente comercial
				if (leiturista.contains(idLeiturista)) {
					throw new ControladorException("atencao.arquivos_liberados_mesmo_agente_comercial", null, dadosArquivo);
				}
				leiturista.add(idLeiturista);
			}
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	/**
	 * [UC1487] Processar Requisicao Dispositivo Movel Execucao OS
	 * 
	 * Recebe as fotos envidas pelo sistema gsaneos e as persiste na base de dados
	 * 
	 * @author Bruno Barros
	 * @date 03/07/2013
	 * 
	 * @author André Miranda
	 * @date 08/12/2015
	 * 
	 * @param numeroOS Número da Ordem de serviço a qual a OS pertence
	 * @param tipoFoto Tipo da foto enviada ( Antes da Execução, Durante a Execução, Após a Execução, Fachada do Imóvel
	 * @param foto Array de bytes contendo a foto
	 * @throws ControladorException
	 */
	public void inserirFotoOrdemServicoCobrancaSmartphone(int idArquivo, int numeroOS, int tipoFoto, byte[] foto,BigDecimal coordenadaX, BigDecimal coordenadaY)
			throws ControladorException {
		ExecucaoOSFoto execucaoOSFoto = new ExecucaoOSFoto(idArquivo, numeroOS, tipoFoto, foto, coordenadaX, coordenadaY);

		FiltroExecucaoOSFoto filtro = new FiltroExecucaoOSFoto();
		filtro.adicionarParametro(new ParametroSimples(FiltroExecucaoOSFoto.ID_ARQUIVO, idArquivo));
		filtro.adicionarParametro(new ParametroSimples(FiltroExecucaoOSFoto.ID_ORDEM_SERVICO, numeroOS));
		filtro.adicionarParametro(new ParametroSimples(FiltroExecucaoOSFoto.ID_SITUACAO, tipoFoto));

		ExecucaoOSFoto osFotoPesquisaBase = (ExecucaoOSFoto) Util.retonarObjetoDeColecao(getControladorUtil()
				.pesquisar(filtro, ExecucaoOSFoto.class));

		if (osFotoPesquisaBase == null) {
			this.getControladorUtil().inserir(execucaoOSFoto);
		} else if (!DESCARTARDADOSDUPLICADOSOSCOBRANCASMARTPHONE) {
			this.getControladorUtil().inserir(execucaoOSFoto);
		}
	}
	
	/**
	 * [UC1498] - Consultar Arquivo Texto de Ordens de Serviço para Smartphone (Novo)
	 *
	 * Esse método foi criado, pois o mesmo apenas é utilizado na tela de transmitir 
	 * arquivo texto.	
	 *
	 * @author Jean Varela
	 * @date   10/12/2015	
	 */
	public byte[] baixarArquivoTextoExecucaoOrdemServico(long imei,Integer idArquivo) throws ControladorException {

		byte[] arquivo = null;
		
		// Localizamos o leiturista que possue o imei cadastrado
		FiltroLeiturista filtroLeiturista = new FiltroLeiturista();
		if (imei != 0) {
			filtroLeiturista.adicionarParametro(new ParametroSimples(FiltroLeiturista.IMEI, imei));
		}
		
		Leiturista leiturista = (Leiturista) Util.retonarObjetoDeColecao(getControladorUtil().
																pesquisar(filtroLeiturista,Leiturista.class));

		if (leiturista == null) {
			throw new ControladorException("atencao.nenhum_leiturista_cadastrado_imei", null, imei + "");
		}
		
		arquivo = gerarArquivoTextoIdaExecucaoOSCobranca(idArquivo);

		if (arquivo == null) {
			throw new ControladorException("atencao.nenhum_arquivo_liberado_execucao_os", null, imei + "");
		}
		
		return arquivo;
	}
}