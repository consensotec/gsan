package gcom.cadastro;


import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atualizacaocadastral.ClienteAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.HidrometroInstalacaoHistoricoAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.ImovelAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.ImovelSubcategoriaAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.ParametroTabelaAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.bean.AtualizacaoCadastralArquivoTextoHelper;
import gcom.atualizacaocadastral.bean.AtualizarClienteAPartirDispositivoMovelHelper;
import gcom.atualizacaocadastral.bean.ComandoAtualizacaoCadastralDMHelper;
import gcom.atualizacaocadastral.bean.ConsultarRoteiroDispositivoMovelHelper;
import gcom.atualizacaocadastral.bean.DadosCadastradorHelper;
import gcom.atualizacaocadastral.bean.ImoveisRoteiroDispositivoMovelDMHelper;
import gcom.cadastro.atualizacaocadastral.bean.DadosResumoMovimentoAtualizacaoCadastralHelper;
import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificado;
import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificadoBinario;
import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificadoCritica;
import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificadoLinha;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.EmpresaCobrancaFaixa;
import gcom.cadastro.empresa.EmpresaContratoCobranca;
import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.bean.ExibirFiltrarLogradouroHelper;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.MunicipioFeriado;
import gcom.cadastro.imovel.CadastroOcorrencia;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.EntidadeBeneficente;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelInscricaoAlterada;
import gcom.cadastro.imovel.ImovelProgramaEspecial;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.imovel.bean.GerarArquivoTextoAtualizacaoCadastralHelper;
import gcom.cadastro.imovel.bean.ImovelGeracaoTabelasTemporariasCadastroHelper;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.QuadraFace;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.NacionalFeriado;
import gcom.cadastro.sistemaparametro.SistemaAlteracaoHistorico;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.sistemaparametro.bean.DadosEnvioEmailHelper;
import gcom.cadastro.tarifasocial.TarifaSocialComandoCarta;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.CobrancaAcaoAtividadeCronograma;
import gcom.faturamento.debito.DebitoTipo;
import gcom.gui.atualizacaocadastral.AtualizacaoCadastralDMOnlineHelper;
import gcom.gui.atualizacaocadastral.RelatorioImoveisInconsistentesMovimentoBean;
import gcom.gui.cadastro.imovel.FiltrarRelatorioHistoricoImoveisProgramaEspecialHelper;
import gcom.gui.cadastro.imovel.RelatorioHistoriocoImoveisProgramaEspecialHelper;
import gcom.gui.relatorio.cadastro.FiltrarRelatorioAcessoSPCHelper;
import gcom.gui.relatorio.cadastro.GerarRelatorioAlteracoesCpfCnpjHelper;
import gcom.gui.relatorio.cadastro.micromedicao.FiltrarRelatorioColetaMedidorEnergiaHelper;
import gcom.gui.relatorio.seguranca.GerarRelatorioAlteracoesSistemaColunaHelper;
import gcom.micromedicao.ArquivoTextoLigacoesHidrometroHelper;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.Rota;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.relatorio.cadastro.GerarRelatorioAtualizacaoCadastralViaInternetHelper;
import gcom.relatorio.cadastro.RelatorioAcessoSPCBean;
import gcom.relatorio.cadastro.RelatorioBoletimCadastroIndividualBean;
import gcom.relatorio.cadastro.RelatorioMensagensPendentesCadastradorBean;
import gcom.relatorio.cadastro.RelatorioQuantitativoMensagensPendentesBean;
import gcom.relatorio.cadastro.RelatorioResumoPosicaoAtualizacaoCadastralBean;
import gcom.relatorio.cadastro.RelatorioResumoSituacaoImoveisAnalistaCadastradorBean;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisAtivosNaoMedidosHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisConsumoMedioHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisFaturasAtrasoHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisProgramasEspeciaisHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisTipoConsumoHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisUltimosConsumosAguaHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisAtivosNaoMedidosHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisConsumoMedioHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisFaturasAtrasoHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisProgramasEspeciaisHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisSituacaoLigacaoAguaHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisTipoConsumoHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisUltimosConsumosAguaHelper;
import gcom.relatorio.cadastro.micromedicao.RelatorioColetaMedidorEnergiaHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.FachadaException;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;





/**
 * Interface Controlador Cobranca PADRÃO
 * 
 * @author Rômulo Aurélio 
 * @date 24/11/2009
 */

public interface IControladorCadastro {
	
	
	/**
	 * Permite inserir um Histórico Alteração de Sistema
	 * 
	 * [UC0217] Inserir Historico Alteracao Sistema
	 * 
	 * @author Thiago Tenorio
	 * @date 30/03/2006
	 * 
	 */

	public Integer inserirHistoricoAlteracaoSistema(
			SistemaAlteracaoHistorico sistemaAlteracaoHistorico)
			throws ControladorException;

	/**
	 * Metódo responsável por inserir uma Gerência Regional
	 * 
	 * [UC0000 - Inserir Gerencia Regional
	 * 
	 * @author Thiago Tenório
	 * @date 27/06/2006, 16/11/2006
	 * 
	 * @param agencia
	 *            bancaria
	 * @param usuarioLogado
	 * @return
	 * @throws ControladorException
	 */
	public Integer inserirGerenciaRegional(GerenciaRegional gerenciaRegional)
			throws ControladorException;

	/**
	 * [UC0391] Atualizar Gerência Regional.
	 * 
	 * 
	 * 
	 * 
	 * @author Thiago Tenório
	 * @date 01/11/2006
	 * 
	 * @param
	 * @throws ControladorException
	 */
	public void atualizarGerenciaRegional(GerenciaRegional gerenciaRegional)
			throws ControladorException;

	/**
	 * Pesquisa as empresas que serão processadas no emitir contas
	 * 
	 * @author Sávio Luiz
	 * @date 09/01/2007
	 * 
	 */
	public Collection pesquisarIdsEmpresa() throws ControladorException;

	
	/**
	 * 
	 * Recebe uma arquivo e pra cada linha desse arquivo ele processa o imovelCelular ou ClienteImovelCelular
	 *
	 * @author Thiago Toscano
	 * @date 16/02/2009
	 *
	 * @param file
	 * @throws ControladorException
	 */
	public void carregarImovelAtualizacaoCadastral(BufferedReader buffer) throws ControladorException;

	/**
	 * Informar Parametros do Sistema
	 * 
	 * @author Rômulo Aurélio
	 * @date 09/01/2007
	 * 
	 */

	public void informarParametrosSistema(SistemaParametro sistemaParametro,
			Usuario usuarioLogado) throws ControladorException;

	/**
	 * [UC0534] Inserir Feriado
	 * 
	 * @author Kassia Albuquerque
	 * @date 17/01/2007
	 * 
	 */
	public Integer inserirFeriado(NacionalFeriado nacionalFeriado,
			MunicipioFeriado municipioFeriado, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * Pesquisa os feriados(nacionais e municipais)
	 * 
	 * @author Kássia Albuquerque
	 * @date 22/01/2007
	 * 
	 */
	public Collection pesquisarFeriado(Short tipoFeriado, String descricao,
			Date dataFeriado, Date dataFeriadoFim, Integer idMunicipio,
			Integer numeroPagina) throws ControladorException;

	/**
	 * Pesquisar quantidade de registro dos feriados(nacionais e municipais)
	 * 
	 * @author Kássia Albuquerque
	 * @date 22/01/2007
	 * 
	 */
	public Integer pesquisarFeriadoCount(Short tipoFeriado, String descricao,
			Date dataFeriadoInicio, Date dataFeriadoFim, Integer idMunicipio)
			throws ControladorException;

	/**
	 * [UC0535] Manter Feriado [SB0001] Atualizar Feriado
	 * 
	 * @author Kassia Albuquerque
	 * @date 27/01/2006
	 * 
	 * @pparam feriado
	 * @throws ControladorException
	 */

	public void atualizarFeriado(NacionalFeriado nacionalFeriado,
			MunicipioFeriado municipioFeriado, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * [UC0535] Manter Feriado
	 * 
	 * Remover Feriado
	 * 
	 * @author Kassia Albuquerque
	 * @date 29/01/2007
	 * 
	 * @pparam municipio
	 * @throws ControladorException
	 */
	public void removerFeriado(String[] ids, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * Pesquisar os ids do Setor comercial pela localidade
	 * 
	 * @author Ana Maria
	 * @date 07/02/2007
	 * 
	 * @return Collection<Integer>
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsSetorComercial(Integer idLocalidade)
			throws ControladorException;
	
	/**
	 * Informar Mensagem do Sistema 
	 * 
	 * @author Kássia Albuquerque
	 * @date 02/03/2007
	 * 
	 */
	public void atualizarMensagemSistema(SistemaParametro sistemaParametro,Usuario usuarioLogado)
			throws ControladorException;
	
	/**
	 * Pesquisa os dados do email do batch para ser enviado
	 * 
	 * @author Sávio Luiz
	 * @date 13/03/2007
	 * 
	 */
	public EnvioEmail pesquisarEnvioEmail(Integer idEnvioEmail)
			throws ControladorException;
	
	public DadosEnvioEmailHelper pesquisarDadosEmailSistemaParametros()
	throws ControladorException;
	/**
	 * [UC????] Inserir Funcionario
	 * 
	 * @author Rômulo Aurélio
	 * @date 12/04/2007
	 * 
	 */
	public void inserirFuncionario(Funcionario funcionario,
			 Usuario usuarioLogado)
			throws ControladorException;
	
	/**
	 * [UC????] Atualizar Funcionario
	 * 
	 * @author Rômulo Aurélio
	 * @date 17/04/2007
	 * 
	 * @param funcionario, usuarioLogado, idFuncionario
	 * 
	 */
	public void atualizarFuncionario(Funcionario funcionario,
			 Usuario usuarioLogado)	throws ControladorException;
	
	/**
	 * Pesquisar todos ids dos setores comerciais.
	 *
	 * [UC0564 - Gerar Resumo das Instalações de Hidrômetros]
	 *
	 * @author Pedro Alexandre
	 * @date 25/04/2007
	 *
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarTodosIdsSetorComercial() throws ControladorException;
	
	/**
	 * Este caso de uso permite a emissão de boletins de cadastro
	 * 
	 * [UC0582] Emitir Boletim de Cadastro
	 * 
	 * @author Rafael Corrêa
	 * @data 15/05/2007
	 * 
	 * @param
	 * @return void
	 */
	public void emitirBoletimCadastro(
			CobrancaAcaoAtividadeCronograma cronogramaAtividadeAcaoCobranca,
			CobrancaAcaoAtividadeComando comandoAtividadeAcaoCobranca, Date dataAtualPesquisa,
			CobrancaAcao cobrancaAcao, int idFuncionalidadeIniciada)
			throws ControladorException;
	
	/**
	 * Metódo responsável por inserir um Cliente Tipo
	 * 
	 * [UC???? - Inserir Cliente Tipo
	 * 
	 * @author Thiago Tenório
	 * @date 18/06/2007
	 * 
	 * @param ClienteTipo
	 *           
	 * @param usuarioLogado
	 * @return
	 * @throws ControladorException
	 */
	public Integer inserirClienteTipo(
			ClienteTipo clienteTipo, Usuario usuarioLogado)
			throws ControladorException;
	
	
	/**
	 * Metódo responsável por inserir um Cliente Tipo
	 * 
	 * [UC???? - Inserir Cliente Tipo
	 * 
	 * @author Thiago Tenório
	 * @date 18/06/2007
	 * 
	 * @param ClienteTipo
	 *           
	 * @param usuarioLogado
	 * @return
	 * @throws ControladorException
	 */
	public void atualizarClienteTipo(
			ClienteTipo clienteTipo)
			throws ControladorException;
	
	/**
	 * Este caso de uso permite a emissão de boletins de cadastro
	 * 
	 * [UC0582] Emitir Boletim de Cadastro pelo Filtro Imóvel por Outros Critérios
	 * 
	 * @param
	 * @return void
	 */
	public byte[] emitirBoletimCadastro(String idImovelCondominio, String idImovelPrincipal,
			String idSituacaoLigacaoAgua, String consumoMinimoInicialAgua,
			String consumoMinimoFinalAgua, String idSituacaoLigacaoEsgoto,
			String consumoMinimoInicialEsgoto, String consumoMinimoFinalEsgoto,
			String intervaloValorPercentualEsgotoInicial,
			String intervaloValorPercentualEsgotoFinal,
			String intervaloMediaMinimaImovelInicial,
			String intervaloMediaMinimaImovelFinal,
			String intervaloMediaMinimaHidrometroInicial,
			String intervaloMediaMinimaHidrometroFinal,
			String idImovelPerfil, String idPocoTipo,
			String idFaturamentoSituacaoTipo, String idCobrancaSituacaoTipo,
			String idSituacaoEspecialCobranca, String idEloAnormalidade,
			String areaConstruidaInicial, String areaConstruidaFinal,
			String idCadastroOcorrencia, String idConsumoTarifa,
			String idGerenciaRegional, String idLocalidadeInicial,
			String idLocalidadeFinal, String setorComercialInicial,
			String setorComercialFinal, String quadraInicial,
			String quadraFinal, String loteOrigem, String loteDestno,
			String cep, String logradouro, String bairro, String municipio,
			String idTipoMedicao, String indicadorMedicao,
			String idSubCategoria, String idCategoria,
			String quantidadeEconomiasInicial, String quantidadeEconomiasFinal,
			String diaVencimento, String idCliente, String idClienteTipo,
			String idClienteRelacaoTipo, String numeroPontosInicial,
			String numeroPontosFinal, String numeroMoradoresInicial,
			String numeroMoradoresFinal, String idAreaConstruidaFaixa,
			String idUnidadeNegocio, String indicadorCpfCnpj, String cpfCnpj) throws ControladorException;
	
	/**
	 * 
	 * Pesquisa os imóveis do cliente de acordo com o tipo de relação
	 * 
	 * 
	 * 
	 * [UC0251] Gerar Atividade de Ação de Cobrança [SB0001] Gerar Atividade de
	 * 
	 * Ação de Cobrança para os Imóveis do Cliente
	 * 
	 * 
	 * 
	 * @author Sávio Luiz
	 * 
	 * @created 23/11/2007
	 * 
	 * 
	 * 
	 * @param cliente
	 * 
	 * @param relacaoClienteImovel
	 * 
	 * @return
	 * 
	 * @throws ErroRepositorioException
	 * 
	 */

	public Collection pesquisarClientesSubordinados(Integer idCliente)

	throws ControladorException;
	
	/**
	 * [UC0725] Gerar Relatório de Imóveis por Situação da Ligação de Agua
	 * 
	 * @author Rafael Pinto
	 * @date 03/12/2007
	 * 
	 * @param FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioImoveisSituacaoLigacaoAguaHelper> pesquisarRelatorioImoveisSituacaoLigacaoAgua(
		FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper filtro) 
		throws ControladorException;
	
	/**
	 * [UC0725] Gerar Relatório de Imóveis por Situação da Ligação de Agua
	 * 
	 * Pesquisa o Total Registro
	 * 
	 * @author Rafael Pinto
	 * @date 04/12/2007
	 * 
	 * @param FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper
	 * 
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisSituacaoLigacaoAgua(
		FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper filtro) 
		throws ControladorException;
	
	/**
	 * [UC0726] Gerar Relatório de Imóveis com Faturas em Atraso
	 * 
	 * @author Bruno Barros
	 * @date 06/12/2007
	 * 
	 * @param FiltrarRelatorioImoveisFaturasAtrasoHelper
	 * 
	 * @return Collection<RelatorioImoveisFaturasAtrasoHelper>
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioImoveisFaturasAtrasoHelper> pesquisarRelatorioImoveisFaturasAtrasoAgrupadasLocalizacao(
		FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) 
		throws ControladorException;

	
	/**
	 *[UC0726] - Gerar Relatório de Imóveis com Faturas em Atraso
	 *
	 *@since 31/08/2009
	 *@author Marlon Patrick
	 */
	public Collection<RelatorioImoveisFaturasAtrasoHelper> pesquisarRelatorioImoveisFaturasAtrasoAgrupadasCliente(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) 
			throws ControladorException;

	/**
	 * [UC0726] Gerar Relatório de Imóveis com Faturas em Atraso
	 * 
	 * Pesquisa o Total Registro
	 * 
	 * @author Bruno Barros
	 * @date 06/12/2007
	 * 
	 * @param FiltrarRelatorioImoveisFaturasAtrasoHelper
	 * 
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisFaturasAtrasoLocalizacao(
		FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) 
		throws ControladorException;

	/**
	 *[UC0726] - Gerar Relatório de Imóveis com Faturas em Atraso
	 *
	 *@since 31/08/2009
	 *@author Marlon Patrick
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisFaturasAtrasoCliente(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) 
			throws ControladorException;

	/**
	 * [UC0727] Gerar Relatório de Imóveis por Consumo Medio
	 * 
	 * @author Bruno Barros
	 * @date 17/12/2007
	 * 
	 * @param FiltrarRelatorioImoveisConsumoMedioHelper
	 * 
	 * @return Collection<RelatorioImoveisConsumoMedioHelper>
	 * @throws ControladorException
	 */
	public Collection<RelatorioImoveisConsumoMedioHelper> pesquisarRelatorioImoveisConsumoMedio(
			FiltrarRelatorioImoveisConsumoMedioHelper filtro) throws ControladorException;
	
	/**
	 * Pesquisa a quantidade de imoveis para  o relatorio de imoveis por consumo medio
	 * 
	 * @author Bruno Barros
	 * @data 17/12/2007
	 * 
	 * @param filtro
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisConsumoMedio(
			FiltrarRelatorioImoveisConsumoMedioHelper filtro) throws ControladorException;
	
	/**
	 * [UC0731] Gerar Relatório de Imóveis com os Ultimos Consumos de Agua
	 * 
	 * @author Rafael Pinto
	 * @date 18/12/2007
	 * 
	 * @param FiltrarRelatorioImoveisUltimosConsumosAguaHelper
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioImoveisUltimosConsumosAguaHelper> pesquisarRelatorioImoveisUltimosConsumosAgua(
			FiltrarRelatorioImoveisUltimosConsumosAguaHelper filtro) 
		throws ControladorException;
	
	/**
	 * [UC0731] Gerar Relatório de Imóveis com os Ultimos Consumos de Agua
	 * 
	 * @author Rafael Pinto
	 * @date 19/12/2007
	 * 
	 * @param FiltrarRelatorioImoveisUltimosConsumosAguaHelper
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisUltimosConsumosAgua(
			FiltrarRelatorioImoveisUltimosConsumosAguaHelper filtro) 
		throws ControladorException;
	
	
	/**
	 * [UC00728] Gerar Relatório de Imóveis Ativos e Não Medidos
	 * 
	 * @author Rafael Pinto
	 * @date 03/01/2008
	 * 
	 * @param FiltrarRelatorioImoveisAtivosNaoMedidosHelper
	 * 
	 * @return Collection<RelatorioImoveisAtivosNaoMedidosHelper>
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioImoveisAtivosNaoMedidosHelper> pesquisarRelatorioImoveisAtivosNaoMedidos(
			FiltrarRelatorioImoveisAtivosNaoMedidosHelper filtro) 
		throws ControladorException;
	
	/**
	 * [UC00728] Gerar Relatório de Imóveis Ativos e Não Medidos
	 * 
	 * @author Rafael Pinto
	 * @date 03/01/2008
	 * 
	 * @param FiltrarRelatorioImoveisAtivosNaoMedidosHelper
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisAtivosNaoMedidos(
			FiltrarRelatorioImoveisAtivosNaoMedidosHelper filtro) 
		throws ControladorException;
	
	/**
	 * [UC00730] Gerar Relatório de Imóveis com Faturas Recentes em Dia e Faturas Antigas em Atraso
	 * 
	 * @author Rafael Pinto
	 * @date 08/01/2008
	 * 
	 * @param FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper
	 * 
	 * @return Collection<RelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper>
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper> pesquisarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtraso(
			FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper filtro) 
		throws ControladorException;	
	
	/**
	 * [UC00730] Gerar Relatório de Imóveis com Faturas Recentes em Dia e Faturas Antigas em Atraso
	 * 
	 * @author Rafael Pinto
	 * @date 08/01/2008
	 * 
	 * @param FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTotalRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtraso(
			FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper filtro) 
		throws ControladorException;	
	
	/**
	 * [UC0729] Gerar Relatório de Imóveis por Tipo de Consumo
	 * 
	 * @author Bruno Barros
	 * @date 10/01/2008
	 * 
	 * @param RelatorioImoveisTipoConsumoHelper
	 * 
	 * @return Collection<RelatorioImoveisTipoConsumoHelper>
	 * @throws FachadaException
	 */
	public Collection<RelatorioImoveisTipoConsumoHelper> pesquisarRelatorioImoveisTipoConsumo(
		FiltrarRelatorioImoveisTipoConsumoHelper filtro)
		throws ControladorException;
	
	/**
	 * [UC0729] Gerar Relatório de Imóveis por Tipo de Consumo
	 * 
	 * @author Bruno Barros
	 * @date 10/01/2008
	 * 
	 * @param RelatorioImoveisTipoConsumoHelper
	 * 
	 * @return Collection<RelatorioImoveisTipoConsumoHelper>
	 * @throws FachadaException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisTipoConsumo(
			FiltrarRelatorioImoveisTipoConsumoHelper filtro) 
			throws ControladorException;	

	/**
	 * [UC0762] Gerar Arquivo Texto com Dados Cadastrais - CAERN 
	 * @author Tiago Moreno
	 * @date 08/04/2008
	 * 
	 * @param ArquivoTextoDadosCadastraisHelper
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public void gerarArquivoTextoDadosCadastrais(
			ArquivoTextoDadosCadastraisHelper arquivoTextoDadosCadastraisHelper) throws ControladorException;
	
	/**
	 * [UC0763] Gerar Arquivo Texto de Ligacoes com Hidrometro - CAERN 
	 * @author Tiago Moreno
	 * @date 10/04/2008
	 * 
	 * @param ArquivoTextoLigacoesHidrometroHelper
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Collection<HidrometroInstalacaoHistorico> recuperaImoveisArquivoTextoLigacoesHidrometro(
			ArquivoTextoLigacoesHidrometroHelper arquivoTextoLigacoesHidrometroHelper) throws ControladorException;
    
    /**
     * Pesquisar os todos os ids de Setor comercial 
     * 
     * @author Vivianne Sousa
     * @date 07/02/2007
     * 
     * @return Collection<Integer>
     * @throws ErroRepositorioException
     */
    public Collection<Integer> pesquisarIdsCodigosSetorComercial()
            throws ControladorException;
    
    /**
     * [UC0330] Inserir Mensagem da Conta
	 *
	 * [SB0001] Pesquisar Setor Comercial
     *
     * @author Raphael Rossiter
     * @date 25/06/2008
     *
     * @param tipoArgumento
     * @param indiceInicial
     * @param indiceFinal
     * @param anoMesReferencia
     * @return Collection
     * @throws ControladorException
     */
    public Collection pesquisarSetorComercialPorQualidadeAgua(int tipoArgumento, BigDecimal indiceInicial, 
		BigDecimal indiceFinal, Integer anoMesReferencia) throws ControladorException;
    
	
	/**
	 * [UC0726] Gerar Relatório de Imóveis com Faturas em Atraso
	 * 
	 * @author Flávio Leonardo
	 * @date 10/09/2008
	 * 
	 * @param FiltrarRelatorioImoveisFaturasAtrasoHelper
	 * 
	 * @return Collection<RelatorioImoveisSituacaoLigacaoAguaHelper>
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioImoveisFaturasAtrasoHelper> pesquisarRelatorioImoveisFaturasAtrasoDescritasLocalizacao(
		FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) 
		throws ControladorException;

	/**
	 * [UC0726] Gerar Relatório de Imóveis com Faturas em Atraso
	 *
	 *@since 02/09/2009
	 *@author Marlon Patrick
	 */
	public Collection<RelatorioImoveisFaturasAtrasoHelper> pesquisarRelatorioImoveisFaturasAtrasoDescritasCliente(
		FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) 
		throws ControladorException;

     /**
     * [UC0xxx] Inserir Unidade de Negocio
     * 
     * 
     * @author Rômulo Aurélio
     * @date 29/09/2008
     * 
     * 
     * @return Integer
     * @throws ControladorException 
     * @throws ControladorException
     */
    
    
    public Integer inserirUnidadeNegocio(UnidadeNegocio unidadeNegocio, 
            Usuario usuarioLogado) throws ControladorException;
    
    /**
	 * [UC0???] Atualizar Unidade de Negocio
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 29/09/2008
	 * 
	 * 
	 * @throws ControladorException 
	 * @throws ControladorException
	 */

	public void atualizarUnidadeNegocio(UnidadeNegocio unidadeNegocio,
			Usuario usuarioLogado) throws ControladorException;
	
	
	/**
     * [UC0789] Inserir Empresa
     * 
     * 
     * @author Rômulo Aurélio
     * @date 29/09/2008
     * 
     * 
     * @return Integer
     * @throws ControladorException
     */
    
    public Integer inserirEmpresa(Empresa empresa, EmpresaContratoCobranca empresaCobranca,
            Usuario usuarioLogado, List<EmpresaCobrancaFaixa> colecaoEmpresaCobrancaFaixa) throws ControladorException;
    
    
    /**
	 * [UC0784] Manter Empresa
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 29/09/2008
	 * 
	 * 
	 * @throws ControladorException
	 */

    
    public void atualizarEmpresa(Empresa empresa,
			EmpresaContratoCobranca empresaCobranca, Usuario usuarioLogado,
			List<EmpresaCobrancaFaixa> colecaoEmpresaCobrancaFaixa,
			List<EmpresaCobrancaFaixa> colecaoEmpresaCobrancaFaixaRemover)
			throws ControladorException ;
    
	
    /**
	 * [UC0831] Gerar Tabelas para Atualização Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 25/08/2008
	 */

	public void obterImovelClienteProprietarioUsuario(Integer idSetor , Integer idFuncionalidadeIniciada,
			ImovelGeracaoTabelasTemporariasCadastroHelper helper) throws ControladorException;
	
	/**
	 * Gerar Arquivo Texto da Atualização Cadastral 
	 * para Dispositivo Móvel
	 * 
	 * @param helper
	 * 
	 * @author Ana Maria
     * @date 17/09/2008
	 * @exception ControladorException
	 */
    
	public void gerarArquivoTextoAtualizacaoCadastralDispositivoMovel(Integer idFuncionalidadeIniciada, GerarArquivoTextoAtualizacaoCadastralHelper helper)
		throws ControladorException;
    
    /**
     * 
     * [UC0535] Manter Feriado
     * 
     * @author bruno
     * @date 12/01/2009
     *
     * @param indicadorTipoFeriado
     * @param anoOrigemFeriado
     * @param anoDestinoFeriado
     */
    public void espelharFeriados( 
            String indicadorTipoFeriado, 
            String anoOrigemFeriado, 
            String anoDestinoFeriado )
        throws ControladorException;   
    
    /**
	 * [UC0880] - Gerar Movimento de Extensao de Contas em Cobranca por Empresa
	 * 
	 * @author Rômulo Aurélio
	 * @date 09/02/2009
	 * 
	 * @param idRota
	 * @param anoMesReferencia
	 * @return boolean
	 * @throws ControladorException
	 */
    public Collection pesquisarLocalidades() throws ControladorException ;
    

	/**
	 * [UC0890]Consultar Arquivo Texto Atualização Cadastral 
	 * 
	 * @author Ana Maria
	 * @date 04/03/2009
	 * 
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection pesquisarArquivoTextoAtualizacaoCadastro(String idEmpresa, 
			String idLocalidade, String idAgenteComercial, String idSituacaoTransmissao)throws ControladorException;
	
	/**
	 * [UC0890]Consultar Arquivo Texto Atualização Cadastral 
	 * 
	 * @author Ana Maria
	 * @date 04/03/2009
	 * 
	 * @return Collection
	 * @throws ControladorException
	 */
	public ArquivoTextoAtualizacaoCadastral pesquisarArquivoTextoAtualizacaoCadastro(String idArquivoTxt)
		throws ControladorException;
	
	/**
	 * 
	 * [UC0890]Consultar Arquivo Texto Atualização Cadastral 
	 * 
	 * @author Ana Maria
	 * @date 05/03/2009
	 * 
	 * @return void
	 * @throws ControladorException
	 */
	public void atualizarArquivoTextoAtualizacaoCadstral(Integer idArquivoTxt)
		throws ControladorException;

	/** Método para verificar o Cliente é um funcionário
	 * 
	 * @author Vinicius Medeiros
	 * @date 08/04/2009
	 *
	 * @param idCliente
	 * @return
	 * @throws ControladorException
	 */
	
	 public Integer clienteSelecionadoFuncionario(Integer idCliente)
		throws ControladorException;
	 
	
	/**
	 * [UC0024] Inserir Quadra
	 *
	 * @author Raphael Rossiter
	 * @date 03/04/2009
	 *
	 * @param quadraFaceNova
	 * @param colecaoQuadraFace
	 * @throws ControladorException
	 */
	public void validarQuadraFace(QuadraFace quadraFaceNova, Collection colecaoQuadraFace, boolean verificarExistencia) 
		throws ControladorException;
        
	/**
	 * Pesquisa a Quadra Face atraves da quadra associada
	 * 
	 * Autor: Arthur Carvalho
	 * 
	 * Data: 28/04/2009
	 */
	public Collection<Object[]> pesquisarQuadraFaceAssociadaQuadra(Integer idQuadra)
			throws ControladorException ;
	
	/**
	 * Valida se o cliente é uma pessoa jurídica. Se não for, lança uma exceção.
	 * 
	 * [FS0002] do [UC0915] Inserir Entidade Beneficente
	 * 
	 * @author Samuel Valerio
	 * @date 11/06/2009
	 * @since 4.1.6.4
	 * 
	 * @param cliente Cliente a ser verificado
	 * @throws ControladorException 
	 */
	public void validarSeClienteEhPessoaJuridica(Cliente cliente) throws ControladorException;
	
	/**
	 * Valida se o tipo do débito não é gerado automaticamente. Se for, lança uma exceção.
	 * 
	 * [FS0004] do [UC0915] Inserir Entidade Beneficente
	 * 
	 * @author Samuel Valerio
	 * @date 12/06/2009
	 * @since 4.1.6.4
	 * 
	 * @param debitoTipo Tipo de débito a ser verificado
	 * @throws ControladorException
	 */
	public void validarSeDebitoTipoNaoEhGeradoAutomaticamente(DebitoTipo debitoTipo) throws ControladorException;
	
	/**
	 * Valida se já não existe uma entidade beneficente com aquele cliente. Se houver, lança uma exceção.
	 * 
	 * [FS0006] do [UC0915] Inserir Entidade Beneficente
	 * 
	 * @author Samuel Valerio
	 * @date 12/06/2009
	 * @since 4.1.6.4
	 * 
	 * @param entidadeBeneficente Entidade beneficente a ser verificada
	 * @throws ControladorException
	 */
	public void validarPreExistenciaEntidadeBeneficente(EntidadeBeneficente entidadeBeneficente) throws ControladorException;
	
	/**
	 * [UC0915] Inserir Entidade Beneficente
	 * 
	 * @author Samuel Valerio
	 * @date 12/06/2009
	 * @since 4.1.6.4
	 * 
	 * @param entidadeBeneficente Entidade beneficente a ser inserida
	 * @throws ControladorException
	 */
	public Integer inserirEntidadeBeneficente(EntidadeBeneficente entidadeBeneficente) throws ControladorException;
	
	/**
	 * [UC0830] Gerar Tabelas para Atualização Cadastral via celular 
	 * 
	 * @author Ana Maria
	 * @date 22/06/2009
	 * 
	 * @return Collection<Integer>
	 * @throws ControladorException
	 */

	public Collection<Integer> pesquisarSetorComercialGeracaoTabelasTemporarias(ImovelGeracaoTabelasTemporariasCadastroHelper helper) 
			throws ControladorException;
	
	/**
	 * [UC0830] Gerar Tabelas para Atualização Cadastral via celular 
	 * 
	 * @author Ana Maria
	 * @date 22/06/2009
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */

	public Collection<Integer> obterIdsImovelGeracaoTabelasTemporarias(ImovelGeracaoTabelasTemporariasCadastroHelper helper) 
			throws ControladorException;
	
	/**
	 * [UC0912] Gerar Boletim de Custo Atualização Cadastral
	 *
	 * @author Vivianne Sousa
	 * @date 25/06/2009
	 *
	 * @param idEmpresa
	 * @param data
	 * @throws ControladorException
	 */
	public Object[] gerarBoletimCustoAtualizacaoCadastral(
			Empresa empresa, Date dataAtualizacaoInicio, Date dataAtualizacaoFim)throws ControladorException;

	/**
	 * [UC0925] Emitir Boletos
	 *
	 * @author Vivianne Sousa
	 * @date 10/07/2009
	 */
	public void emitirBoletos(Integer idFuncionalidadeIniciada,Integer grupo, Integer entidadeBeneficente)throws ControladorException;
	
	/**
	 * Obtém a quantidade de economias da categoria, levando em consideração o
	 * fator de economias
	 * 
	 * @author Rafael Corrêa
	 * @date 09/08/2009
	 * 
	 * @throws ControladorException
	 */
	public int obterQuantidadeEconomiasCategoria(Categoria categoria) throws ControladorException;
	
	/**
	 * Obtém a quantidade de economias da subcategoria, levando em consideração o
	 * fator de economias
	 * 
	 * @author Rafael Corrêa
	 * @date 09/08/2009
	 * 
	 * @throws ControladorException
	 */
	public int obterQuantidadeEconomiasSubcategoria(Subcategoria subcategoria) throws ControladorException;
	
	/**
	 * [UC0407]-Filtrar Imóveis para Inserir ou Manter Conta
	 * [FS0011]-Verificar a abrangência do código do usuário
	 *
	 * @author Vivianne Sousa
	 * @date 31/07/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public UnidadeNegocio pesquisarUnidadeNegocioUsuario(Integer idUsuario)throws ControladorException;

	/**
	 * [UC0928]-Manter Situação Especial de Faturamento
	 * [FS0003]-Verificar a existência do setor
	 *
	 * @author Marlon Patrick
	 * @date 11/08/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public boolean verificarExistenciaSetorComercial(Integer idSetorComercial)throws ControladorException;
	
	/**
	 * [UCXXXX] Excluir Imoveis Da Tarifa Social 
	 * CRC - 2113
	 * 
	 * @author Genival Barbosa
	 * @date 15/09/2009
	 */	
	public void excluirImoveisDaTarifaSocial(Integer idSetor , Integer idFuncionalidadeIniciada, Integer anoMesFaturamento) 
			throws ControladorException;

	/**
	 * [UC0727] Gerar Relatório de Imóveis por Consumo Medio
	 * 
	 * @author Arthur Carvalho
	 * @date 02/10/2009
	 * 
	 * @param FiltrarRelatorioImoveisConsumoMedioHelper
	 * 
	 * @return quantidade de imoveis
	 * @throws ControladorException
	 */
	public Integer pesquisarRelatorioImoveisConsumoMedioCount(
			FiltrarRelatorioImoveisConsumoMedioHelper filtro) throws ControladorException;
	
	/**
	 * @author Arthur Carvalho
	 * @date 08/10/2009
	 * 
	 * @return quantidade de imoveis
	 * @throws ControladorException
	 */
	public Integer pesquisarImovelAtualizacaoCadastralComIndicadorExclusaoCount() throws ControladorException;

	

	/**
	 * [UC0969] Importar arquivo de atualização cadastral simplificado
	 * 
	 * @author Samuel Valerio
	 * @date 22/10/2009
	 * 
	 * @param arquivo Arquivo texto a ser importado
	 * @return Id do arquivo texto recém-inserido
	 * @throws ControladorException
	 */
	public Integer inserirArquivoTextoAtualizacaoCadastralSimplificado(AtualizacaoCadastralSimplificado arquivo, AtualizacaoCadastralSimplificadoBinario arquivoBinario,Collection<AtualizacaoCadastralSimplificadoLinha> linhas) throws ControladorException;

	/**
	 * Busca as críticas existentes para o arquivo passado como parâmetro.
	 * 
	 * [UC0969] Importar arquivo de atualização cadastral simplificado
	 * 
	 * @author Samuel Valerio
	 * @date 22/10/2009
	 * 
	 * @param idArquivo Id do arquivo texto importado
	 * @return Críticas existentes para o arquivo.
	 * @throws ControladorException
	 */
	public Collection<AtualizacaoCadastralSimplificadoCritica> pesquisarAtualizacaoCadastralSimplificadoCritica(int idArquivo) throws ControladorException;
	/**
	 * 
	 * [UC0973] Inserir Imóvel em Programa Especial
	 * [FS0004] Validar dados do imóvel no programa especial
	 * @author Hugo Amorim
	 * @since 17/12/2009
	 *
	 */
	public void validarDadosInserirImovelProgramaEspecial(ImovelProgramaEspecial imovelProgramaEspecial) throws ControladorException;
	
	/**
	 * 
	 * [UC0976] Suspender Imóvel em Programa Especial
	 * [FS0004] Validar dados da suspensão imóvel no programa especial
	 * @author Hugo Amorim
	 * @since 21/12/2009
	 *
	 */
	public void validarDadosSuspensaoImovelProgramaEspecial(
			ImovelProgramaEspecial imovelProgramaEspecial) throws ControladorException;
	
	/**
	 * 
	 * [UC0976] Suspender Imóvel em Programa Especial
	 *  	Suspende Imóvel em Programa Especial
	 * @author Hugo Amorim
	 * @since 13/01/2010
	 *
	 */
	public void suspenderImovelEmProgramaEspecialOnline(ImovelProgramaEspecial imovelProgramaEspecial,
			Usuario usuarioLogado,Short formaSuspensao) throws ControladorException;
	
	/**
	 * 
	 * [UC0973] Inserir Imóvel em Programa Especial
	 *  	Inseri Imóvel em Programa Especial
	 * @author Hugo Amorim
	 * @since 13/01/2010
	 *
	 */
	public Integer inserirImovelEmProgramaEspecial(ImovelProgramaEspecial imovelProgramaEspecial,
			Usuario usuarioLogado) throws ControladorException;
	
	/**
	 * 
	 * [UC0976] Suspender Imóvel em Programa Especial Batch
	 *  	Suspende Imóveis ativos no Programa Especial
	 * @author Hugo Amorim
	 * @since 13/01/2010
	 *
	 */
	public void suspenderImovelEmProgramaEspecialBatch(int idFuncionalidadeIniciada,
			Usuario usuarioLogado,Rota rota)
		throws ControladorException;
	
	/**
	 * [UC0979] Gerar Relatório de Imóveis em Programas Especiais
	 * 
	 * @author Hugo Leonardo
	 * @date 19/01/2010
	 * 
	 * @param RelatorioImoveisProgramasEspeciaisHelper
	 * 
	 * @return Collection<RelatorioImoveisProgramasEspeciaisHelper>
	 * @throws ControladorException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisProgramaEspecial(
			FiltrarRelatorioImoveisProgramasEspeciaisHelper filtro) 
			throws ControladorException;
	
	/**
	 * [UC0979] Gerar Relatório de Imóveis em Programas Especiais Analitico
	 * 
	 * @author Hugo Leonardo
	 * @date 18/01/2010
	 * 
	 * @param RelatorioImoveisProgramasEspeciaisHelper
	 * 
	 * @return Collection<RelatorioImoveisProgramasEspeciaisHelper>
	 * @throws ControladorException
	 */
	public Collection<RelatorioImoveisProgramasEspeciaisHelper> pesquisarRelatorioImoveisProgramasEspeciaisAnalitico(
		FiltrarRelatorioImoveisProgramasEspeciaisHelper filtro)
		throws ControladorException;
	
	/**
	 * [UC0979] Gerar Relatório de Imóveis em Programas Especiais - Relatório Sintético
	 * 
	 * @author Hugo Leonardo
	 * @date 25/01/2010
	 * 
	 * @param RelatorioImoveisProgramasEspeciaisHelper
	 * 
	 * @return Collection<RelatorioImoveisProgramasEspeciaisHelper>
	 * @throws ControladorException
	 */
	
	public Collection pesquisarRelatorioImoveisProgramasEspeciaisSintetico(
			FiltrarRelatorioImoveisProgramasEspeciaisHelper helper)
			throws ControladorException; 
	
	/**
	 * 
	 * [UC0973] Inserir Imóvel em Programa Especial
	 * 
	 * Verificar se existe parcelamento para o Imovel em Programa Especial.
	 * 
	 * @author Hugo Leonardo
     * @throws ControladorException 
	 * @since 10/02/2010
	 *
	 */
    public Boolean verificarExistenciaParcelamentoImovel(Integer idImovel) throws ControladorException;
    
    /**
	 * [UC0999] Gerar Relatório de Coleta de Medidor de Energia.
	 * 
	 * @author Hugo Leonardo
	 * @date 09/03/2010
	 * 
	 * @param FiltrarRelatorioColetaMedidorEnergiaHelper
	 * 
	 * @return Collection<RelatorioColetaMedidorEnergiaHelper>
	 * @throws ControladorException
	 */
	public Collection<RelatorioColetaMedidorEnergiaHelper> pesquisarRelatorioColetaMedidorEnergia(
			FiltrarRelatorioColetaMedidorEnergiaHelper helper)
		throws ControladorException;
	
    /**
	 * [UC0999] Gerar Relatório de Coleta de Medidor de Energia.
	 * 
	 * Obtém a quantidade de imoveis de acordo com o filtro.
	 * 
	 * @author Hugo Leonardo
	 * @date 09/03/2010
	 * 
	 * @param FiltrarRelatorioColetaMedidorEnergiaHelper
	 * 
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer countRelatorioColetaMedidorEnergia(
			FiltrarRelatorioColetaMedidorEnergiaHelper helper) 
			throws ControladorException;
	
    /**
	 * [UC1011] Emitir Boletim de Cadastro Individual.
	 * 
	 * Criar Dados para Relatório de Boletim de Cadastro Individual
	 * 
	 * @author Hugo Leonardo
	 * @date 24/03/2010
	 * 
	 * @param idImovel
	 * 
	 * @return RelatorioBoletimCadastroIndividualBean
	 * @throws ControladorException
	 */
	public RelatorioBoletimCadastroIndividualBean criarDadosRelatorioBoletimCadastroIndividual(
			Integer idImovel) throws ControladorException;
	
	/**
	 * 
	 * Batch criado para atualização da coluna codigo debito automatico do imovel.
	 * 
	 * @author Hugo Amorim
	 * @date 30/03/2010	
	 */
	public void atualizarCodigoDebitoAutomatico(Integer integer,
			SetorComercial setorComercial) throws ControladorException;
	
    /**
     * [UC0811] Processar Requisições do Dispositivo Móvel Impressao Simultanea.
     * 
     * Método que baixa a nova versão do JAD do mobile para o celular
     * 
     * @author Bruno Barros
     * @date 08/06/2010
     *  
     * @param 
     * @throws IOException
     */   
    public byte[] baixarNovaVersaoJad() throws ControladorException;
    
    /**
     * [UC0811] Processar Requisições do Dispositivo Móvel Impressao Simultanea.
     * 
     * Método que baixa a nova versão do JAR do mobile para o celular
     * 
     * @author Bruno Barros
     * @date 08/06/2010
     *  
     * @param 
     * @throws IOException
     */   
    public byte[] baixarNovaVersaoJar() throws ControladorException;
	
    /**
     * 
     * @author Fernando Fontelles
     * @date 07/07/2010
     * 
     * @param idImovel
     * @return
     * @throws ControladorException
     */
    public boolean verificarSituacaoImovelCobrancaJudicial(Integer idImovel) throws ControladorException;
    
    /**
     * 
     * @author Fernando Fontelles
     * @date 07/07/2010
     * 
     * @param idImovel
     * @return
     * @throws ControladorException
     */
    public boolean verificarSituacaoImovelNegativacao( Integer idImovel ) throws ControladorException;
    
    /**
     * 
     * [UC1036] - Inserir Cadastro de Email do Cliente
     * 
     * @author Fernando Fontelles
     * @date 09/07/2010
     * 
     * @param idCliente
     * @param nomeClienteAnterior
     * @param cpfAnterior
     * @param cnpjAnterior
     * @param emailAnterior
     * @param nomeSolicitante
     * @param cpfSolicitante
     * @param nomeClienteAtual
     * @param cpfClienteAtual
     * @param cnpjClienteAtual
     * @param emailAtual
     * @return
     */
    public Integer inserirCadastroEmailCliente( Integer idCliente, String nomeClienteAnterior, 
     		String cpfAnterior, String cnpjAnterior, String emailAnterior, String nomeSolicitante, 
     		String cpfSolicitante, String nomeClienteAtual, String cpfClienteAtual,
 			String cnpjClienteAtual, String emailAtual) throws ControladorException;
    
    /**
     * [UC1074] Gerar Relatório Alterações no Sistema por Coluna
     * 
     * @author Hugo Amorim
     * @date 08/09/2010
     */
 	public Collection pesquisarDadosRelatorioAlteracoesSistemaColuna(GerarRelatorioAlteracoesSistemaColunaHelper helper)
 		throws ControladorException;
 	
 	/**
     * [UC1074] Gerar Relatório Alterações no Sistema por Coluna
     * 
     * [FS0007] 
     * 
     * @author Hugo Amorim
     * @date 08/09/2010
     */
 	public boolean verificarRelacaoColuna(Integer idColuna) throws ControladorException;
 	
 	
	/**
     * [UC1076] Gerar Relatório Atualizações Cadastrais Via Internet.
     * 
     * @author Daniel Alves
     * @date 28/09/2010
     * Consulta do Relatório Analítico
     */
 	public Collection pesquisarDadosRelatorioAtualizacaoCadastralViaInternet(GerarRelatorioAtualizacaoCadastralViaInternetHelper filtro)
 		throws ControladorException;
    
    /**
	 * 
	 * [UC0113] - Faturar Grupo de Faturamento
	 * 
	 * @author Rômulo Aurélio
	 * @date 28/09/2010
	 * 
	 * @return
	 */
	public ClienteImovel pesquisarClienteResponsavelComEsferaPoderPublico(
			Integer idImovel) throws ControladorException ;
 	/**
     * [UC1076] Gerar Relatório Atualizações Cadastrais Via Internet.
     * 
     * @author Daniel Alves
     * @date 28/09/2010
     * Consulta do Relatório Resumo
     */
 	public Collection pesquisarDadosRelatorioResumoAtualizacaoCadastralViaInternet(GerarRelatorioAtualizacaoCadastralViaInternetHelper filtro)
 		throws ControladorException;
 	
 	/**
     * [UC1076] Gerar Relatório Atualizações Cadastrais Via Internet.
     * 
     * @author Hugo Amorim de Lyra
     * @date 06/10/2010
     */
 	public Integer countRelatorioAtualizacaoCadastralViaInternet(GerarRelatorioAtualizacaoCadastralViaInternetHelper helper)
 		throws ControladorException;
 	
	/**
	 * [UC1121] Gerar Relatório de Imóveis com Alteração de Inscrição Via Batch
	 *
	 * @author Hugo Leonardo
	 * @date 19/01/2011
	 *
	 * @throws ControladorException
	 */
	public Collection<ImovelInscricaoAlterada> pesquisarRelatorioImoveisAlteracaoInscricaoViaBatch( 
			FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper relatorioHelper) throws ControladorException;
	
	/**
	 * [UC1121] Gerar Relatório de Imóveis com Alteração de Inscrição Via Batch.
	 *
	 * @author Hugo Leonardo
	 * @date 19/01/2011
	 *
	 * @throws ControladorException
	 */
	public Integer countTotalRelatorioImoveisAlteracaoInscricaoViaBatch( 
			FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper relatorioHelper) 
		throws ControladorException;
	  
	/**
     * [UC1124] Gerar Relatório de Alterações de CPF/CNPJ
     * 
     * @author Mariana Victor
     * @date 16/02/2011
     */
 	public Collection<Object[]> pesquisarDadosRelatorioAlteracoesCpfCnpj(GerarRelatorioAlteracoesCpfCnpjHelper helper)
 		throws ControladorException;
 	
	/**
	 * Solicitar Conta em Braile.
	 * 
	 * [UC1128] Solicitar Conta Braile
	 * 
	 * @author Hugo Leonardo
	 * @date 04/03/2011
	 * 
	 */
    public Integer inserirSolicitacaoContaBraile(ContaBraileHelper contaBraileHelper) 
    	throws ControladorException;
    
    /**
	 * UC1162 – AUTORIZAR ALTERACAO INSCRICAO IMOVEL
	 * @author Rodrigo Cabral
	 * @date 05/06/2011
	 */
	public Collection pesquisaImovelInscricaoAlterada(ImovelInscricaoAlteradaHelper helper)
		throws ControladorException;
	
 	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarLocalidadesPorGerencia(Integer idGerenciaRegional)throws ControladorException;
	
		/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarLocalidadesPorUnidadeNegocio(Integer idUnidadeNegocio)throws ControladorException;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarLocalidade()throws ControladorException;
	
	/**
     * [UC1160] Processar Comando Gerado Carta Tarifa Social  
     * 
     * @author: Vivianne Sousa
     * @date: 24/03/2011
     */
    public void processarComandoGerado(Integer idLocalidade , Integer idFuncionalidadeIniciada,
    		TarifaSocialComandoCarta tarifaSocialComandoCarta)throws ControladorException;
    
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * [SB0007]-Gera Arquivo TXT das Cartas
	 * 
	 * @author Vivianne Sousa
	 * @date 29/03/2011
	 * 
	 * @throws ControladorException
	 */
	public void gerarCartaTarifaSocial(TarifaSocialComandoCarta tscc,Integer idFuncionalidadeIniciada)throws ControladorException;
	
	/**
	 * [UC1161]Retirar Imóvel da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 04/04/2011
	 * 
	 * @throws ControladorException
	 */
	public void retirarImovelTarifaSocial(Integer idLocalidade,	int idFuncionalidadeIniciada) throws ControladorException;
	
	/**
	 * [UC1161]Retirar Imóvel da Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 01/04/2011
	 * 
	 * @throws ControladorException
	 */
	public void retirarImovelTarifaSocial(TarifaSocialComandoCarta tscc,int idFuncionalidadeIniciada) throws ControladorException ;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 02/05/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarLocalidadesPorGerenciaEUnidade(Integer idGerenciaRegional
			,Integer idUnidadeNegocio)throws ControladorException;
	
	/**
	 * [UC1170] Gerar Relatório Acesso ao SPC
	 *  
	 * @author: Diogo Peixoto
	 * @date: 06/05/2011
	 * 
	 * @param FiltrarRelatorioAcessoSPCHelper
	 * @return Collection<RelatorioAcessoSPCBean>
	 * @throws ControladorException
	 */
	public Collection<RelatorioAcessoSPCBean> filtrarRelatorioAcessoSPC(FiltrarRelatorioAcessoSPCHelper filtrarRelatorioAcessoSPCHelper) throws ControladorException;
	
	/**
	 * [UC1170] Gerar Relatório Acesso ao SPC
	 *  
	 * @author: Diogo Peixoto
	 * @date: 06/05/2011
	 * 
	 * @param FiltrarRelatorioAcessoSPCHelper
	 * @return Collection<RelatorioAcessoSPCBean>
	 * @throws ControladorException
	 */
	public void atualizarGrauImportancia(LogradouroBairro logradouroBairro, Integer grauImportancia,Usuario usuario) throws ControladorException;

	
	
	
	 /**
     * Obtém a coleção de categorias.
     * 
     * @author Hugo Azevedo
     * @date 22/06/2011
     * 
     * @throws ControladorException
     */

	public Collection obterCategorias() throws ControladorException;
	
	
	/**
     * Obtém a coleção de perfis de imóveis.
     * 
     * @author Hugo Azevedo
     * @date 22/06/2011
     * 
     * @throws ControladorException
     */

	public Collection obterPerfisImoveis() throws ControladorException;
	
	/**
	 * [UC0060] Inserir Parametros do Sistema
	 * Validar documentos da loja virtual
	 * 
	 * @author Erivan Sousa
	 * @date 15/07/2011
	 * 
	 * @param byte[], String
	 * @throws ControladorException
	 */
	public void validarSistemaParametroLojaVirtual(byte[] fileData, String extensao) throws ControladorException;
	

	/**
	 * [MA2011061013]
	 * 
	 * @author Paulo Diniz
	 * @date 02/07/2011
	 * 
	 * @param idImovel
	 * 
	 * @return HidrometroMovimentado
	 * @throws ControladorException
	 */
	public  List<HidrometroInstalacaoHistorico> pesquisarHidrometroPeloIdImovel(Integer idImovel) throws ControladorException;
	
	/**
	 * [UC0588 / UC0589] Verifica existência do DDD
	 * 
	 * @author Nathalia Santos
	 * @data 23/09/2011
	 */
	public Boolean verificarDdd(Short Ddd) throws ControladorException;

	/**
	 * [UC0588 / UC0589] Verifica existência do funcionáriio ou do cliente
	 * 
	 * @author Nathalia Santos
	 * @data 03/10/2011
	 */
	public Boolean pesquisarFuncionarioOuCliente(Integer IdFuncionario, Integer idCliente) throws ControladorException;
	
	/**
	 * [UC0032] Inserir Logradouro - [UC0033] Manter Logradouro
	 * 
	 * Proposta: 05/10/2011 - Tiago Moreno - PE2011065447 - Verificar existência de Logradouro com mesmo nome
	 *
	 * [FS0012] - Verificar existência de Logradouro com mesmo nome
	 * 
	 * @author Thúlio Araújo
	 * @since 10/10/2011
	 * @param logradouroNome
	 * @return Collection<Logradouro>
	 * @throws ControladorException
	 */
	public Collection<Logradouro> pesquisarLogradouroMesmoNome(String logradouroNome, Integer idMunicipio) 
			throws ControladorException;
	
	/**
	 * [UC0032] Inserir Logradouro - [UC0033] Manter Logradouro
	 * 
	 * Proposta: 05/10/2011 - Tiago Moreno - PE2011065447 - Verificar existência de Logradouro com mesmo nome
	 *
	 * [FS0012] - Verificar existência de Logradouro com mesmo nome
	 * 
	 * @author Thúlio Araújo
	 * @since 10/10/2011
	 * @param logradouroNome
	 * @return Collection<Logradouro>
	 * @throws ErroRepositorioException
	 */
	public Collection<ExibirFiltrarLogradouroHelper> filtrarLogradouroMesmoNome(String logradouroNome, Integer numeroPagina, Integer idMunicipio) 
			throws ControladorException;
	
	/**
	 * [UC0032] Inserir Logradouro - [UC0033] Manter Logradouro
	 * 
	 * Proposta: 05/10/2011 - Tiago Moreno - PE2011065447 - Verificar existência de Logradouro com mesmo nome
	 *
	 * [FS0012] - Verificar existência de Logradouro com mesmo nome
	 * 
	 * Método usado para retornar a quantidade de logradouros com o mesmo nome
	 * 
	 * @author Thúlio Araújo
	 * @since 10/10/2011
	 * @param logradouroNome
	 * @return Collection<Logradouro>
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeLogradouroMesmoNome(String logradouroNome, Integer idMunicipio) 
			throws ControladorException;
	/**
	 * Método que pesquisa uma 
	 * EmpresaCobrancaFaixa pelo id
	 * 
	 * @author Raimundo Martins
	 * @date 24/10/2011
	 * */
	public EmpresaCobrancaFaixa pesquisarEmpresaCobrancaFaixa(Integer idCobrancaFaixa) throws ControladorException;
	
	/** Pesquisa se o Imovel teve a inscricao alterada para excluido. 
	 * @author Arthur Carvalho
	 * @date 31/10/11
	 * @param idImovel
	 * @return
	 */
	public boolean verificaImovelExcluidoFinalFaturamento(Integer idImovel) throws ControladorException ;
	
	/**
	 * Atualizar nome do usuario pelo id de funcionario
	 * 
	 * @author Erivan Sousa
	 * @date 06/12/2011
	 * 
	 * @param idFuncionario
	 * @param nomeFuncionario
	 * 
	 * @throws ControladorException
	 */

	public void atualizarNomeUsuarioComIdFuncionario(Integer idFuncionario,	String nomeFuncionario)throws ControladorException;

	
	/**
	 * [UC0831] Gerar Tabelas para Atualização Cadastral via celular
	 * 
	 * @author Rafael Pinto
	 * @date 21/12/2011
	 */
	public void obterImovelClienteProprietarioUsuario(ImovelGeracaoTabelasTemporariasCadastroHelper helper)
			throws ControladorException ;
	
	/**
	 * [UC0831] Gerar Tabelas para Atualização Cadastral via celular
	 * 
	 * @author Rafael Pinto
	 * @date 27/12/2011
	 */
	
	public void validarImovelGerarTabelasTemporarias(Integer idImovel)
		throws ControladorException ;
	
	
	/**
	 *  Verifica se a esfera do poder permite gerar certidao negativa
	 * @param matricula
	 * @return
	 * @throws ControladorException
	 */
	public boolean esferaPoderPermiteGerarCertidaoNegativa(Integer matricula) throws ControladorException;
	
	/**
	 * Pega o id do Tipo de Cliente, através do id do imóvel
	 * 
	 * @author Rodrigo Cabral
	 * @date 08/06/2012
	 */
	
	public Integer obterClienteTipoId(Integer idImovel)
		throws ControladorException ;
	
	
	/**
	 * 
	 * [UC1367] Registrar Movimento do Programa Especial
	 * [SB0002] Inserir Imóveis em Programa Especial
	 * 
	 * @author Hugo Azevedo
	 * @date 20/08/2012
	 * 
	 */
	public Integer inserirImoveisProgramaEspecial(
			Collection<Integer> colecaoIdsImovel, String acao,
			String anoMesReferencia, String cancelarItensFatura,
			String retirarContasProgEspecial, String sitEspecialCobranca,
			String observacao, String nomeArquivo, byte[] dadosArquivo, Usuario usuario) throws ControladorException;
	
	/**
	 * 
	 * [UC1367] Registrar Movimento do Programa Especial
	 * [SB0003] Suspender Imóveis em Programa Especial
	 * 
	 * @author Hugo Azevedo
	 * @date 20/08/2012
	 * 
	 */
	public Integer suspenderImoveisProgramaEspecial(
			Collection<Integer> colecaoIdsImovel, String acao,
			String anoMesReferencia, String cancelarItensFatura,
			String retirarContasProgEspecial, String sitEspecialCobranca,
			String observacao, String nomeArquivo, byte[] dadosArquivo, Usuario usuario) throws ControladorException;
	
	/**
	 * 
	 * [UC1367] Registrar Movimento do Programa Especial
	 * [SB0005] Gerar Relatório do Movimento do Programa Especial
	 * 
	 * @author Hugo Azevedo
	 * @param colecaoIdsImovel 
	 * @date 21/08/2012
	 * 
	 */
	public Collection obterDadosRelatorioMovimentoProgramaEspecial(Integer idMovimento) throws ControladorException;
	
	/**
	 * 
	 * [UC1367] Registrar Movimento do Programa Especial
	 * [FS0006] Verificar Arquivo Processado
	 * 
	 * @author Hugo Azevedo
	 * @param form 
	 * @date 21/08/2012
	 * 
	 */
	public boolean verificarProcessamentoArquivoMovimentoProgramaEspecial(String fileName) throws ControladorException;
	
	/**
	 * @author Carlos Chaves
	 * @date 01/09/2012
	 * * @param
	 * @throws IOException
	 */
	public Object[] baixarNovaVersaoApk(Integer idSistemaAndroid) throws ControladorException;
	
	/**
	 * [UC1373] Gerar Relatorio Histórico Imóvel Programa Especial.
	 
	 * @author Jonathan Marcos
	 * @date 02/05/2013
	 * 
	 */
	public Collection<RelatorioHistoriocoImoveisProgramaEspecialHelper> pesquisarRelatorioHistoriocoImoveisProgramaEspecial(
		FiltrarRelatorioHistoricoImoveisProgramaEspecialHelper filtro) 
	throws ControladorException;
	
	/**
	 * Valida se o valor informado no form é um bigdecimal 
	 * @author Fernanda
	 * @param valor
	 * @param dff
	 * @param campo
	 * @return
	 * @throws ControladorException
	 */
	public String validaBigDecimal(String valor,DecimalFormat dff, String campo) throws ControladorException;
	
	/**
	 * [UC1527] - Inserir Ocorrencia Operacional
	 * 
	 * @author Rômulo Aurélio
	 * @date 12/07/2013
	 * 
	 */
	public Collection<Localidade> obterLocalidadesdoMunicipio(Integer idMunicipio) throws ControladorException;
	
	/**
	 * @author Jonathan Marcos
	 * @date 11/02/2014
	 * RM10044
	 */
	public Short verificarClienteTipoProgramaEspecial(Integer idImovel)
		throws ControladorException;
	
	
	/**
	 * 
	 * [UC0738] Gerar Certidão Negativa por Imóvel
	 * [FS0006] Validar CPF/CNPJ         
	 * 
	 * @author Hugo Azevedo
	 * @date 07/03/2014
	 * 
	 */
	public boolean verificarClienteImovelCpfCnpjValidos(Integer idImovel) 
			throws ControladorException;
	
	/**
	 * @author Jonathan Marcos
	 * @date 06/08/2014 
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void batchEnviarEMAILCobrancaFaturamento(Integer idFuncionalidadeIniciada)  
			throws ControladorException;
	
	/**
	 * [UC1619] - Processar Retorno do SMS
	 * 
	 * @author Raphael Rossiter
	 * @date 06/08/2014
	 * 
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void retornoSMSEmLote(Integer idFuncionalidadeIniciada)  throws ControladorException;
	

	/**
	 * @author Jonathan Marcos
	 * @date 06/08/2014 
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void batchTransmitirSMSEmLoteCobrancaFaturamento(Integer idFuncionalidadeIniciada) 
			throws ControladorException;

	
	public void gerarRoteiroDispositivoMovel(Integer idFuncionalidadeIniciada, Collection<String> colecaoImoveis, 
			ComandoAtualizacaoCadastralDMHelper helper)	throws ControladorException;
		/**
	 * [UC 1392] Consultar Roteiro Dispositivo Móvel Atualização Cadastral
	 * [IT 0006] Exibir Dados Cadastrador
	 */
	public Collection<DadosCadastradorHelper> pesquisarDadosCadastrador(Integer idParametroAtualizacaoCadastral)
			throws ControladorException;
	
	/**
	 * [UC1392] - Consultar Roteiro Dispositivo Móvel Atualização Cadastral
	 * 
	 * @author Davi Menezes
	 * @date 28/11/2012
	 *
	 */
	public void atualizarListaAtualizacaoCadastralArquivoTexto(
			Collection<AtualizacaoCadastralArquivoTextoHelper> colecaoAtualizacaoCadastralArquivoTexto,
			Integer idSituacaoLeituraNova, Leiturista leiturista, Date date) throws ControladorException;
	
	/**
	 * [UC 1392] - Consultar Roteiro Dispositivo Movel
	 * 
	 * [IT 0003] - Selecionar Arquivos
	 * 
	 * @author Diogo Luiz
	 * @date 27/08/2014
	 */
	public Collection<AtualizacaoCadastralArquivoTextoHelper> pesquisarArquivoRoteiroAtualizacaoCadastral (
			ConsultarRoteiroDispositivoMovelHelper helper) throws ControladorException;
	
	/**
	 * [UC1618] - Enviar SMS em lote
	 * 
	 * @author Rodrigo Cabral
	 * @date 24/09/2014
	 * 
	 * @throws ControladorException
	 */
	public ContaEmpresaSMS recuperarDadosContaEmpresaSMS() throws ControladorException;
	
	
	/**
	 * @author Diogo Luiz
	 * @date 26/08/2014
	 * 
	 * @param idLocalidade
	 * @param codigoSetorComercial
	 * @param idsQuadrasSelecionadasArray
	 * @param colLigacaoAguaSituacao
	 * @param clienteUsuario
	 * @param indicadorSituacaoImovel
	 * @return
	 * @throws ControladorException
	 */
	public Collection<ImoveisRoteiroDispositivoMovelDMHelper> pesquisarImoveisRoteiroDispositivoMovel(String idLocalidade, 
		String codigoSetorComercial, Collection<Integer> quadrasSelecionadas, 
		Collection<Integer> colecaoLigacaoAguaSituacao, String clienteUsuario, String[] indicadorSituacaoImovel) throws ControladorException;
	
	
	public Collection<Integer>  pesquisarImoveisAtualizacaoCadastral(Integer idParametro) throws ControladorException;

	/**
	 * [UC1392] - Consultar Roteiro Dispositivo Movel Atualizacao Cadastral
	 * 
	 * @author André Miranda
	 * @since 01/09/2014
	 * 
	 * @param idParametro
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarRoteiroQuadra(Integer idParametro) throws ControladorException;
	
	
	/**
	 * @author Diogo Luiz
	 * @date 04/09/2014
	 * 
	 * @param helper
	 * @return
	 * @throws ControladorException
	 */
	public void atualizarSituacaoTransmissaoImovel(Integer idComando) throws ControladorException;

	public void atualizarSituacaoDoArquivo(int idAtualizacaoCadastralArquivoTexto, Integer situacaoArquivo) throws ControladorException;

	/**
	 * Método responsável por fazer todo o processamento do atualização
	 * cadastral vindo do tablet
	 * 
	 * @author Jonathan Marcos
	 * @since 25/09/2014
	 * @param nomePasta
	 * @throws ControladorException
	 */
	public void processarRecebimentoDadosImoveisTablet(String nomePasta) 
			throws ControladorException;

	/**
	 * Método responsável pela pesquisa das ocorrências de cadastro de imóvel
	 * de atualização cadastral.
	 * 
	 * @author André Miranda
	 * @since 01/10/2014
	 * @param idImovel
	 * @return
	 * @throws ControladorException
	 */
	public List<CadastroOcorrencia> obterOcorrenciasImovelAtualizacaoCadastral(Integer imac) throws ControladorException;

	/**
	 * Método responsável pela pesquisa de imóvel de atualização cadastral.
	 * 
	 * @author André Miranda
	 * @since 01/10/2014
	 * @param idImovel
	 * @return
	 * @throws ControladorException
	 */
	public ImovelAtualizacaoCadastralDM pesquisarImovelAtualizacaoCadastralDM(Integer idImovel) throws ControladorException;

	/**
	 * Método responsável pela pesquisa dos clientes de imóvel
	 * de atualização cadastral.
	 * 
	 * @author André Miranda
	 * @since 01/10/2014
	 * @param idImovel
	 * @return
	 * @throws ControladorException
	 */
	public List<ClienteAtualizacaoCadastralDM> pesquisarClienteAtualizacaoCadastralDM(Integer idImovel) throws ControladorException;

	/**
	 * Método responsável pela pesquisa das subcategorias de imóvel
	 * de atualização cadastral.
	 * 
	 * @author André Miranda
	 * @since 01/10/2014
	 * @param idImovel
	 * @return
	 * @throws ControladorException
	 */
	public List<ImovelSubcategoriaAtualizacaoCadastralDM> pesquisarSubCategoriaAtualizacaoCadastralDM(Integer idImovel) throws ControladorException;

	/**
	 * Método responsável pela pesquisa dos hidrometrosInstalacao de imóvel
	 * de atualização cadastral.
	 * 
	 * @author André Miranda
	 * @since 01/10/2014
	 * @param idImovel
	 * @param medicaoTipo
	 * @return
	 * @throws ControladorException
	 */
	public List<HidrometroInstalacaoHistoricoAtualizacaoCadastralDM> pesquisarHidrometroInstalacaoHistoricoAtualizacaoCadastralDM(
			Integer idImovel, Integer medicaoTipo) throws ControladorException;

	/**
	 * Método responsável pela pesquisa de logradouro de imóvel
	 * de atualização cadastral.
	 * 
	 * @author André Miranda
	 * @since 01/10/2014
	 * @param idLogradouro
	 * @return
	 * @throws ControladorException
	 */
	public Logradouro pesquisarLogradouroImovelAtualizacaoCadastralDM(Long idLogradouro) throws ControladorException;
	

	/**
	 * [UC 1391] - Gerar Roteiro Dispositivo Móvel
	 * 
	 * @author Diogo Luiz
	 * @throws ErroRepositorioException 
	 * @date 27/08/2014
	 */
	public void gerarTabelasTemporarias(Collection colecaoIdsImovel, Empresa empresa, ParametroTabelaAtualizacaoCadastralDM parametro) 
			throws ControladorException, ErroRepositorioException;
	
	/**
	 * Método responsável por fazer 
	 * o processamento do imovel
	 * vindo do tablet
	 * @author Jonathan Marcos
	 * @since 09/10/2014
	 * @throws ControladorException
	 */
	public void processarRecebimentoDadosImovelTabletOnline(AtualizacaoCadastralDMOnlineHelper helper) 
			throws ControladorException;
	
	/**
	  * [UC1288] Atualizar Dados Cadastrais do Imóvel pelo Recadastramento
	  * [SB0001] Inserir Registro no retorno da Atualizacao Cadastral
	  *  
	  * @author André Miranda
	  * @since 08/10/2014
	  */
	public void inserirRetornoAtualizacaoCadastralDM(Integer idImovel,
			Integer idCliente, Integer idAtributoAtualizacaoCadastral,
			Short codigoSituacao, Integer idMensagemAtualizacaoCadastral,
			Integer idParametroTabelaAtualizacaoCadastro,
			ImovelAtualizacaoCadastralDM imovelAtualizacaoCadastral,
			Integer idMedicaoTipo) throws ControladorException;
	
	/**
	 * [UC1222] Atualizar Cliente a Partir do Dispositivo Mï¿½vel
	 * 
	 * [SB0003] - Substituir Cliente Usuário do Imóvel
	 * 
	 * @author Mariana Victor
	 * @date 27/09/2011
	 * 
	 * @param Integer
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public boolean substituirClienteUsuarioDoImovel(Integer idCliente,
			Integer idClienteAnterior,
			AtualizarClienteAPartirDispositivoMovelHelper helper, 
			Cliente clienteAnterior)
			throws ControladorException;
	
	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 29/09/2011
	 * 
	 * @param Integer
	 * 
	 * @return 
	 * @throws ControladorException
	 */
	public void atualizarDadosFoneClienteAtual(Integer idClienteAtualizacaoCadastral, Integer idCliente, ImovelAtualizacaoCadastralDM imovelAtualizacaoCadastral) throws ControladorException;

	
	/**
	 * [UC1288] Atualizar Dados Cadastrais do Imóvel pelo Recadastramento
	 * 
	 * @author 
	 * @date 09/10/2014
	 *
	 */
	public void atualizarDadosCadastraisImovelRecadastramento(Integer idFuncionalidadeIniciada) 
			throws ControladorException;
	
	/**
	 * [UC1299] Atualizar Cliente para Atualização Cadastral
	 * 
	 * @author Bruno Sá Barreto
	 * @since 13/10/2014
	 */
	public boolean atualizarClienteAtualizacaoCadastral(ImovelAtualizacaoCadastralDM imovelAtualizacaoCadastral, ClienteAtualizacaoCadastralDM clienteAtualizacaoCadastral, 
			ParametroTabelaAtualizacaoCadastralDM ptac, Imovel imovel, LigacaoAguaSituacao ligacaoAguaSituacao , LigacaoEsgotoSituacao ligacaoEsgotoSituacao, SistemaParametro sistemaParametro) 
					throws ControladorException;
	
	/**
	 * Método responsável por<br>
	 * pesquisar nomes dos usuários<br>
	 * que são leituristas associados<br>
	 * a empresa
	 * @author Jonathan Marcos
	 * @since 21/10/2014
	 * @param idEmpresa
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection<Usuario> pesquisarUsuarioAtuCadastral(Integer idEmpresa) 
			throws ControladorException;
	
	/**
	 * @author Jonathan Marcos
	 * @since 17/10/2014
	 * @param imovel
	 * @param idParametroTabelaAtualizacaoCadastro
	 * @param imovelAtualizacaoCadastralDM
	 * @param colecaoHelper
	 * @return boolean
	 * @throws ControladorException
	 */
	public LigacaoAgua efetuarLigacaoAguaAtualizacaoCadastral(Imovel imovel, Integer idParametroTabelaAtualizacaoCadastro, 
			ImovelAtualizacaoCadastralDM imovelAtualizacaoCadastralDM, 
			Collection<ParametrosTransacaoBatchHelper> colecaoHelper, 
			boolean inserirRetornoAtlzCadastral, Date dataLigacao) 
			throws ControladorException;

	/**
	 * [UC1292] Efetuar Instalação de Hidrômetro para Atualização Cadastral
	 * 
	 * @author André Miranda
	 * @since 08/10/2014
	 */
	public void efetuarInstalacaoHidrometroAtualizacaoCadastral(
			Imovel imovel,
			ImovelAtualizacaoCadastralDM imovelAtualizacaoCadastral,
			Integer medicaoTipo,
			Integer idParametro,
			HidrometroInstalacaoHistoricoAtualizacaoCadastralDM hidrometroInstHistAtlzCad,
			boolean funcionalidadeOnline, Usuario usuarioLogado)
			throws ControladorException;
	
	/**
	 * @author Jonathan Marcos
	 * @since 17/10/2014
	 * @param imovel
	 * @param imovelAtualizacaoCadastralDM
	 * @param medicaoTipo
	 * @param idParametroTabelaAtualizacaoCadastro
	 * @param colecaoHelper
	 * @return boolean
	 * @throws ControladorException
	 */
	public boolean efetuarLigacaoEsgotoAtualizacaoCadastral(Imovel imovel, ImovelAtualizacaoCadastralDM imovelAtualizacaoCadastralDM, 
			Integer idParametroTabelaAtualizacaoCadastro, Collection<ParametrosTransacaoBatchHelper> colecaoHelper, boolean inserirRetornoAtlzCadastral)
					throws ControladorException;
	
	/**
	 * [UC1290] Inserir ou Atualizar Imóvel Atualização Cadastral
	 * [SB0019] Atualizar CEP do imóvel
	 * 
	 * @author André Miranda
	 * @since 13/10/2014
	 */
	public void atualizarCepImovelAtualizacaoCadastral(Imovel imovel, Cep cep, Logradouro logradouro,
			Collection<ParametrosTransacaoBatchHelper> colecaoTransacaoHelper) throws ControladorException;
	
	/**
	 * [UC1290] Inserir ou Atualizar Imóvel Atualização Cadastral
	 * [SB0021] Atualizar Bairro do imóvel
	 * 
	 * @author Vivianne Sousa
	 * @since 20/11/2014
	 */
	public void atualizarBairroImovelAtualizacaoCadastral(Imovel imovel, Bairro bairro, Logradouro logradouro,
			Collection<ParametrosTransacaoBatchHelper> colecaoTransacaoHelper) throws ControladorException;
	
	/**
	 * [UC1443] - Gerar Relatorio de Novos Logradouros
	 * 
	 * @author Anderson Cabral, Bruno Sá Barreto
	 * @since 15/03/2013
	 * 
	 * @param idEmpresa
	 * @param idLocalidade
	 * @return  Collection<Object[]>
	 * @throws ControladorException
	 */
	public Collection<Object[]>  pesquisarLogradouroAtlzCad(String idEmpresa, String idLocalidade) throws ControladorException;
	
	/**
	 * [UC1443] - Gerar Relatorio de Novos Logradouros
	 * 
	 * @author Anderson Cabral
	 * @author Bruno Sá Barreto
	 * @since 15/03/2013
	 * 
	 * @param idLogradouro
	 * @return Collection<Object[]> 
	 */
	public Collection<Object[]>  pesquisarLogradouroBairroAtlzCad(String idLogradouro) throws ControladorException;
	
	/**
	 * [UC1443] - Gerar Relatorio de Novos Logradouros
	 * 
	 * @author Anderson Cabral
	 * @author Bruno Sá Barreto
	 * @since 15/03/2013
	 * 
	 * @param idLogradouro
	 * @return  Collection<Object[]>
	 * @throws ControladorException
	 */
	public Collection<Object[]>  pesquisarLogradouroCepAtlzCad(String idLogradouro) throws ControladorException;
	
	/**
	 * [UC1442] Inserir Novos Logradouros Atualização Cadastral
	 * IT010 - Pesquisar Imóveis associados ao Logradouro
	 * 
	 * @author Anderson Cabral
	 * @author Bruno Sá Barreto
	 * @since 27/12/2013
	 * 
	 * @param idLogradouro
	 * @return ArrayList<Integer>
	 * @throws ErroRepositorioException
	 */
	public ArrayList<Integer> pesquisarImovelAtualizacaoCadastralPorLogradouro(Integer idLogradouro ) throws ControladorException;
	
	/**
	 * [UC1443] - Gerar Relatorio de Novos Logradouros
	 * 
	 * @author  Bruno Sá Barreto
	 * @since 15/03/2013
	 * 
	 * @param idEmpresa
	 * @param idLocalidade
	 * @return  Collection<Object[]>
	 * @throws ControladorException
	 */
	public Collection<Object[]>  pesquisarLogradouroAtlzCad(String idEmpresa, String idLocalidade, String idsSelecionados) throws ControladorException;
	
	/**
	 * [UC1442] Inserir Novos Logradouros Atualizacao Cadastral
	 * [FS0002] Verificar CEP Associado a Logradouro
	 * 
	 * @author Bruno Sá Barreto
	 * 
	 * @since 22/11/2014
	 * 
	 * @param codigoCep
	 * @return boolean 
	 */
	public boolean verificaSeExisteAssociacaoCepLogradouro(Integer codigo) throws ControladorException;
	
	/**
	 *	Pesquisa na base e retorna
	 * o objeto associado ao id passado
	 *  como parâmetro.
	 *   
	 * @author Bruno Sá Barreto
	 * @since 22/11/2014
	 *
	 * @return LogradouroBairro
	 * @param idLogradouroBairro
	 */
	public LogradouroBairro pesquisarLogradouroBairroPorId(Integer idLogradouroBairro)
			throws ControladorException;

	/**
	 * [UC1292] Efetuar Instalação de Hidrômetro para Atualização Cadastral
	 * [FS0001] Verificar preenchimento dos campos
	 * 
	 * @author André Miranda
	 * @since 08/10/2014
	 */
	public boolean verificarSituacaoHidrometro(Imovel imovel, Integer idParametroTabelaAtualizacaoCadastro,
			Hidrometro hidrometro, ImovelAtualizacaoCadastralDM imovelAtualizacaoCadastral,	Integer medicaoTipo,
			boolean funcionalidadeOnline) throws ControladorException;
	
	
	/**
	 * [UC 1312] Gerar Resumo da Situação dos imóveis por cadastrador/analista
	 * 
	 * @author Cesar Medeiros
	 * @since 04/12/2014
	 * 
	 * @param Helper
	 * @return Collection<>
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioResumoSituacaoImoveisAnalistaCadastradorBean> pesquisarResumoSituacaoImoveis(
		DadosResumoMovimentoAtualizacaoCadastralHelper helper) throws ControladorException;
	
	
	/**
	 * [UC 1313] Gerar Resumo Quantitativo de Mensagens Pendentes por Cadastrador
	 * 
	 * @author Cesar Medeiros
	 * @since 09/12/2014
	 * 
	 * @param Helper
	 * @return Collection<>
	 * @throws ControladorException
	 */
	public Collection<RelatorioMensagensPendentesCadastradorBean> pesquisarResumoQuantitativoMensagensPendentesCadastrador(
		DadosResumoMovimentoAtualizacaoCadastralHelper helper) throws ControladorException;
	
	
	/**
	 * [UC 1314] Gerar Resumo Quantitativo de Mensagens Pendentes
	 * 
	 * @author Cesar Medeiros
	 * @since 09/12/2014
	 * 
	 * @param Helper
	 * @return Collection<>
	 * @throws ControladorException
	 */
	public Collection<RelatorioQuantitativoMensagensPendentesBean> pesquisarResumoQuantitativoMensagensPendentes(
		DadosResumoMovimentoAtualizacaoCadastralHelper helper) throws ControladorException;
	
	
	

	/**
	 * Método responsável por<br>
	 * pesquisar se existe o<br>
	 * arquivo KMZ cadastrado
	 * para Setor Comercial cadastrado
	 * @author Jonathan Marcos
	 * @since 09/12/2014
	 * @param idLocalidade
	 * @param codigoSetorComercial
	 * @return Object[]
	 * @throws ControladorException
	 */
	public Object[] pesquisarSetorComercialMapaKMZ(Integer idLocalidade,Integer codigoSetorComercial)
			throws ControladorException;
	
	
	/** [UC1391] - Gerar Roteiro Dispositivo Móvel Atualização Cadastral
	 * 	[FE0003] - Verificar existência de mapa do setor comercial
	 * 
	 * Caso não exista mapa associado ao setor comercial 
	 * (ATUALIZACAOCADASTRAL.MAPA_ATLZ_CAD_DM onde STCM_ID 
	 * = ID do setor comercial)
	 *   
	 * @author Bruno Sá Barreto
	 * @since 09/12/2014
	 *
	 * @return resultado
	 * @param idSetorComercial
	 */
	public boolean verificarExistenciaMapaSetorComercial(Integer idLocalidade,Integer codigoSetorComercial)
			throws ControladorException;
	
	/** [UC1393] - Processar Requisição do Dispositivo Móvel Atualização Cadastral 
	 *  retorna o arquivo de mapa
	 *  do setor comercial caso ele
	 *  exista.
	 *   
	 * @author Bruno Sá Barreto
	 * @since 11/12/2014
	 */
	public byte[] pesquisarArquivoMapSetorComercial(Integer idLocalidade,Integer codigoSetorComercial) 
			throws ControladorException;
	
	/** 
	 * Verifica se o id de um logradouro existe na base de dados
	 * retorna o booleano afirmando ou negando a assertiva.
	 * 
	 * @author Bruno Sá Barreto
	 * @since 12/12/2014
	 *
	 * @return resultado
	 * @param idLogradouro
	 */
	public boolean verificarSeLogradouroExisteNoGsan(Long idLogradouro)
			throws ControladorException;
	
	/** 
	 * retorna o logr_id do imóvel
	 * 
	 * @author Bruno Sá Barreto
	 * @since 12/12/2014
	 *
	 * @return resultado
	 * @param idLogradouro
	 */
	public Long pesquisarIdLogradouroDoImovel(Integer idImovel)
			throws ControladorException;
	
	
	/**
	 * [UC 1311] Gerar Resumo da Posição de Atualização Cadastral
	 * 
	 * @author Cesar Medeiros
	 * @since 15/12/2014
	 * 
	 * @param Helper
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection<RelatorioResumoPosicaoAtualizacaoCadastralBean> pesquisarResumoPosicaoAtualizacaoCadastral(
		DadosResumoMovimentoAtualizacaoCadastralHelper helper) throws ControladorException;
	
	/**
	 * [UC1297] Atualizar Dados Cadastrais para Imoveis Inconsistentes
	 * 
	 * [SB0006] Relatório dos Imoveis Inconsistentes
	 * 
	 * @author Davi Menezes
	 * @author Bruno Sá Barreto 
	 * 
	 * @date 26/03/2012
	 * 
	 * @return Collection<RelatorioImoveisInconsistentesBean>
	 * @throws ControladorException
	 */
	public Collection<RelatorioImoveisInconsistentesMovimentoBean> pesquisarRelatorioImoveisInconsistentesMovimento(String [] idsRegistro,
			String idLocalidade, String codigoSetorComercial, String numeroQuadras, String idCadastrador, String indicadorSituacaoMovimento,
			String tipoInconsistencia)throws ControladorException;
	
	/**
	 * [UC1669] Atualizar Dados nas Tabelas Resumos Grenciais Faturamento
	 * 
	 * @author Fábio Aguiar
	 * @since 04/02/2015
	 * 
	 * @param matriculaImovel
	 * @return ObterDebitoImovelOuClienteHelper
	 * @throws ControladorException
	 */
	public void gerarResumoCadastroAtualizaDados(Integer matriculaImovel) throws ControladorException;

	/**
	 * [UC1290] Inserir ou Atualizar Imóvel Atualização Cadastral [SB0020]
	 * Calcular Valor de Água e/ou Esgoto
	 * 
	 * @author Vivianne Sousa
	 * @date 20/10/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection obterQtdeEconomiaPorCategoriaAtlzCadastral(Integer idImovelAtlzCadDM) throws ControladorException;

	/**
	 * [UC1290] Inserir ou Atualizar Imóvel Atualização Cadastral [SB0020]
	 * Calcular Valor de Água e/ou Esgoto
	 * 
	 * @author Vivianne Sousa
	 * @date 20/10/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection obterQtdeEconomiaPorSubcategoriaAtlzCadastral(Integer idImovelAtlzCadDM) throws ControladorException;
	/**
	 * [UC1290] Inserir ou Atualizar Imóvel Atualização Cadastral 
	 * @author Vivianne Sousa
	 * @date 21/07/2015
	 *
	 * @param imovelAtlzCad
	 * @param idOS
	 * @param ligacaoTipo
	 */
	public void inserirOrdemServicoAtuCadastral(ImovelAtualizacaoCadastralDM imovelAtlzCad, 
			Integer idOS, LigacaoTipo ligacaoTipo) throws ControladorException;
	
	/**
	 * [UC1290] Inserir ou Atualizar Imóvel Atualização Cadastral [SB0018]
	 * Alterar Situação da Ligação de Esgoto de CORTADO para LIGADO
	 * 
	 * @author André Miranda
	 * @since 13/10/2014
	 */
	public Integer alterarSituacaoLigacaoEsgotoCortadoParaLigado(Imovel imovel, Usuario usuario)
			throws ControladorException ;
	
	/**
	 *[0025] Manter Quadra
	 *
	 * @author Cesar Medeiros
	 * @since 10/08/2015
	 */
	public Collection<Integer> obterIdImoveisLigacaoAguaSituacao(String idQuadra)
			throws ControladorException;
	
	/**
	 *[0025] Manter Quadra
	 *
	 * @author Cesar Medeiros
	 * @since 10/08/2015
	 */
	public Collection<Integer> obterIdImoveisLigacaoAguaSituacaoNaoFactivel(String idQuadra)
			throws ControladorException;
	
	/**
	 *[0025] Manter Quadra
	 *
	 * @author Cesar Medeiros
	 * @since 10/08/2015
	 */
	public Collection<Integer> obterIdImoveisLigacaoAguaSituacaoFactivel(String idQuadra)
			throws ControladorException;
	
	/**
	 *[0025] Manter Quadra
	 *
	 * @author Cesar Medeiros
	 * @since 11/08/2015
	 */
	public Collection<Integer> obterIdImoveisLigacaoEsgotoSituacao(String idQuadra)
			throws ControladorException;
	
	/**
	 *[0025] Manter Quadra
	 *
	 * @author Cesar Medeiros
	 * @since 11/08/2015
	 */
	public Collection<Integer> obterIdImoveisLigacaoEsgotoSituacaoNaoFactivel(String idQuadra)
			throws ControladorException;
	
	/**
	 *[0025] Manter Quadra
	 *
	 * @author Cesar Medeiros
	 * @since 11/08/2015
	 */
	public Collection<Integer> obterIdImoveisLigacaoEsgotoSituacaoFactivel(String idQuadra)
			throws ControladorException;
}

