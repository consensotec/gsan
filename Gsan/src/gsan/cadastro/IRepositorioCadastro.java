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
package gsan.cadastro;


import gsan.atualizacaocadastral.ArquivoTextoAtualizacaoCadastralDM;
import gsan.atualizacaocadastral.ClienteAtualizacaoCadastralDM;
import gsan.atualizacaocadastral.HidrometroInstalacaoHistoricoAtualizacaoCadastralDM;
import gsan.atualizacaocadastral.ImovelAtualizacaoCadastralDM;
import gsan.atualizacaocadastral.ImovelSubcategoriaAtualizacaoCadastralDM;
import gsan.atualizacaocadastral.ParametroTabelaAtualizacaoCadastralDM;
import gsan.atualizacaocadastral.RetornoAtualizacaoCadastralDM;
import gsan.atualizacaocadastral.bean.AtualizacaoCadastralArquivoTextoHelper;
import gsan.atualizacaocadastral.bean.ConsultarRoteiroDispositivoMovelHelper;
import gsan.cadastro.atualizacaocadastral.bean.DadosResumoMovimentoAtualizacaoCadastralHelper;
import gsan.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificadoCritica;
import gsan.cadastro.cliente.ClienteImovel;
import gsan.cadastro.empresa.EmpresaCobrancaFaixa;
import gsan.cadastro.endereco.Logradouro;
import gsan.cadastro.endereco.LogradouroBairro;
import gsan.cadastro.geografico.MunicipioFeriado;
import gsan.cadastro.imovel.CadastroOcorrencia;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.imovel.ImovelProgramaEspecial;
import gsan.cadastro.imovel.ImovelSubcategoriaPK;
import gsan.cadastro.imovel.ItemMovimentoProgramaEspecial;
import gsan.cadastro.imovel.bean.ImovelGeracaoTabelasTemporariasCadastroHelper;
import gsan.cadastro.localidade.SetorComercial;
import gsan.cadastro.localidade.UnidadeNegocio;
import gsan.cadastro.sistemaparametro.NacionalFeriado;
import gsan.cadastro.sistemaparametro.bean.DadosEnvioEmailHelper;
import gsan.cadastro.tarifasocial.TarifaSocialMotivoCarta;
import gsan.cobranca.CobrancaAcaoAtividadeComando;
import gsan.gui.cadastro.imovel.FiltrarRelatorioHistoricoImoveisProgramaEspecialHelper;
import gsan.gui.relatorio.cadastro.FiltrarRelatorioAcessoSPCHelper;
import gsan.gui.relatorio.cadastro.GerarRelatorioAlteracoesCpfCnpjHelper;
import gsan.gui.relatorio.seguranca.GerarRelatorioAlteracoesSistemaColunaHelper;
import gsan.micromedicao.ArquivoTextoLigacoesHidrometroHelper;
import gsan.micromedicao.Leiturista;
import gsan.micromedicao.Rota;
import gsan.micromedicao.RotaAtualizacaoSeq;
import gsan.micromedicao.hidrometro.Hidrometro;
import gsan.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gsan.relatorio.cadastro.GerarRelatorioAtualizacaoCadastralViaInternetHelper;
import gsan.relatorio.cadastro.imovel.FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper;
import gsan.relatorio.cadastro.imovel.FiltrarRelatorioImoveisAtivosNaoMedidosHelper;
import gsan.relatorio.cadastro.imovel.FiltrarRelatorioImoveisConsumoMedioHelper;
import gsan.relatorio.cadastro.imovel.FiltrarRelatorioImoveisFaturasAtrasoHelper;
import gsan.relatorio.cadastro.imovel.FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper;
import gsan.relatorio.cadastro.imovel.FiltrarRelatorioImoveisProgramasEspeciaisHelper;
import gsan.relatorio.cadastro.imovel.FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper;
import gsan.relatorio.cadastro.imovel.FiltrarRelatorioImoveisTipoConsumoHelper;
import gsan.relatorio.cadastro.imovel.FiltrarRelatorioImoveisUltimosConsumosAguaHelper;
import gsan.relatorio.cadastro.imovel.RelatorioImoveisConsumoMedioHelper;
import gsan.relatorio.cadastro.micromedicao.RelatorioColetaMedidorEnergiaHelper;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ControladorException;
import gsan.util.ErroRepositorioException;
import gsan.util.FachadaException;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author Administrador 
 */
public interface IRepositorioCadastro {
	
	/**
	 * Pesquisa os feriados(nacionais e municipais)
	 * 
	 * @author Kássia Albuquerque
	 * @date 22/01/2007
	 * 
	 */
	public Collection pesquisarFeriado(Short tipoFeriado, String descricao, 
						Date dataFeriadoInicio, Date dataFeriadoFim, Integer idMunicipio, Integer numeroPagina)throws ErroRepositorioException;	

	/**
	 * Pesquisar quantidade de registro dos feriados(nacionais e municipais)
	 * 
	 * @author Kássia Albuquerque
	 * @date 22/01/2007
	 * 
	 */
	public Integer pesquisarFeriadoCount(Short tipoFeriado, String descricao, 
						Date dataFeriadoInicio, Date dataFeriadoFim, Integer idMunicipio)throws ErroRepositorioException;
	
	/**
	 * 
	 * Faz um Update na base
	 * 
	 * @author Kassia Albuquerque e Ana Maria
	 * @date 06/03/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarMensagemSistema(String mensagemSistema)throws ErroRepositorioException ;
	
	/**
	 * Pesquisa os dados do email do batch para ser enviado
	 * 
	 * @author Sávio Luiz
	 * @date 13/03/2007
	 * 
	 */
	public EnvioEmail pesquisarEnvioEmail(Integer idEnvioEmail)
			throws ErroRepositorioException;
	
	public DadosEnvioEmailHelper pesquisarDadosEmailSistemaParametros()
	throws ErroRepositorioException;
	
	/**
	 * Pesquisar todos ids dos setores comerciais.
	 *
	 * [UC0564 - Gerar Resumo das Instalações de Hidrômetros]
	 *
	 * @author Pedro Alexandre
	 * @date 25/04/2007
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarTodosIdsSetorComercial() throws ErroRepositorioException ;
	
	/**
	 * Migração dos dados do município de Ribeirão - O sistema
	 * gerar as tabelas cliente, cliente_endereço, imovel, cliente_imovel,
	 * imovel_subcategoria, ligacao_agua a parti da tabela Cadastro_ribeirao;
	 * 
	 * @author Ana Maria
	 * 
	 * @throws ControladorException
	 */	
	public Object[] pesquisarSetorQuadra(Integer idLocalidade)throws ErroRepositorioException;
	
	public Integer pesquisarCEP()throws ErroRepositorioException;
	
	public Integer pesquisarBairro() throws ErroRepositorioException;
	
	public Integer pesquisarLogradouroBairro(Integer codigoLogradouro) throws ErroRepositorioException;
	
	public Integer pesquisarLogradouroCep(Integer codigoLogradouro) throws ErroRepositorioException;
	
	public void inserirClienteEndereco(Integer idCliente, String numeroImovelMenor, String numeroImovelMaior,
			Integer idCep, Integer idBairro, Integer idLograd, Integer idLogradBairro, Integer idLogradCep) throws ErroRepositorioException;

	public void inserirClienteImovel(Integer idCliente, Integer idImovel, String data)throws ErroRepositorioException;	
	
	public void inserirImovelSubcategoria(Integer idImovel, Integer idSubcategoria)throws ErroRepositorioException;
	
	public void inserirLigacaoAgua(Integer idImovel, String dataBD)throws ErroRepositorioException;	
	
	public Collection pesquisarCadastroRibeiraop() throws ErroRepositorioException;
	
	public void atualizarImovelRibeirao(Integer idImovel, Integer codigo)throws ErroRepositorioException;

	/**
	 * Fim - Migração dos dados do município de Ribeirão
	 */
	
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

	throws ErroRepositorioException;

	public Collection<Integer>  pesquisarRoteiroQuadra(Integer idParametro) throws ErroRepositorioException;
	
	public Integer pesquisarQuantidadeImoveisRecebidosAtualizacaoCadastral(Integer idParametro) throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarArquivoRoteiroAtualizacaoCadastral(ConsultarRoteiroDispositivoMovelHelper helper)
			throws ErroRepositorioException;

	
	/**
	 * [UC0624] Gerar Relatório para Atualização Cadastral
	 */
	
	public Collection pesquisarRelatorioAtualizacaoCadastral(Collection idLocalidades,
			Collection idSetores, Collection idQuadras, String rotaInicial,
			String rotaFinal, String sequencialRotaInicial, String sequencialRotaFinal) 
		throws ErroRepositorioException;
	
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
	public Collection<Object[]> pesquisarRelatorioImoveisSituacaoLigacaoAgua(
		FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper filtro) 
		throws ErroRepositorioException;
	
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
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisSituacaoLigacaoAgua(
		FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper filtro) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0726] Gerar Relatório de Imóveis com Faturas em Atraso
	 * 
	 * @author Bruno Barros
	 * @date 06/12/2007
	 * 
	 * @param FiltrarRelatorioImoveisFaturasAtraso
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarRelatorioImoveisFaturasAtrasoAgrupadasLocalizacao(
		FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) 
		throws ErroRepositorioException;

	
	/**
	 *[UC0726] - Gerar Relatório de Imóveis com Faturas em Atraso
	 *
	 *@since 31/08/2009
	 *@author Marlon Patrick
	 */
	public Collection<Object[]> pesquisarRelatorioImoveisFaturasAtrasoAgrupadasCliente(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) 
			throws ErroRepositorioException;

	/**
	 * [UC0726] Gerar Relatório de Imóveis com Faturas em Atraso
	 * 
	 * Pesquisa o Total Registro
	 * 
	 * @author Bruno Barros
	 * @date 04/12/2007
	 * 
	 * @param FiltrarRelatorioImoveisFaturasAtrasoHelper
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisFaturasAtrasoLocalizacao(
		FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) 
		throws ErroRepositorioException;	

	
	/**
	 *[UC0726] - Gerar Relatório de Imóveis com Faturas em Atraso
	 *
	 *@since 31/08/2009
	 *@author Marlon Patrick
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisFaturasAtrasoCliente(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) 
			throws ErroRepositorioException;	

	
	/**
	 * [UC0727] Gerar Relatório de Imóveis por Consumo Medio
	 * 
	 * @author Bruno Barros
	 * @date 17/12/2007
	 * 
	 * @param FiltrarRelatorioImoveisConsumoMedio
	 * 
	 * @return Collection<FiltrarRelatorioImoveisConsumoMedio[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioImoveisConsumoMedioHelper> pesquisarRelatorioImoveisConsumoMedio(
		FiltrarRelatorioImoveisConsumoMedioHelper filtro, Integer anoMesFaturamento) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0727] Gerar Relatório de Imóveis por Consumo Medio
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
			FiltrarRelatorioImoveisConsumoMedioHelper filtro, Integer anoMesFaturamento) 
		throws ErroRepositorioException;
	
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
	public Collection<Object[]> pesquisarRelatorioImoveisUltimosConsumosAgua(
		FiltrarRelatorioImoveisUltimosConsumosAguaHelper filtro) 
		throws ErroRepositorioException;
	
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
	public Integer pesquisarTotalRegistroRelatorioImoveisUltimosConsumosAgua(
		FiltrarRelatorioImoveisUltimosConsumosAguaHelper filtro) 
		throws ErroRepositorioException;
	
	/**
	 * [UC00728] Gerar Relatório de Imóveis Ativos e Não Medidos
	 * 
	 * @author Rafael Pinto
	 * @date 03/01/2008
	 * 
	 * @param FiltrarRelatorioImoveisAtivosNaoMedidosHelper
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarRelatorioImoveisAtivosNaoMedidos(
			FiltrarRelatorioImoveisAtivosNaoMedidosHelper filtro) 
		throws ErroRepositorioException;
	
	/**
	 * [UC00728] Gerar Relatório de Imóveis Ativos e Não Medidos
	 * 
	 * @author Rafael Pinto
	 * @date 03/01/2008
	 * 
	 * @param FiltrarRelatorioImoveisAtivosNaoMedidosHelper
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisAtivosNaoMedidos(
			FiltrarRelatorioImoveisAtivosNaoMedidosHelper filtro) 
		throws ErroRepositorioException;	
	
	/**
	 * [UC00730] Gerar Relatório de Imóveis com Faturas Recentes em Dia e Faturas Antigas em Atraso
	 * 
	 * @author Rafael Pinto
	 * @date 08/01/2008
	 * 
	 * @param FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtraso(
			FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper filtro) 
		throws ErroRepositorioException;
	
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
	public Integer pesquisarTotalRegistroRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtraso(
			FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper filtro) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0729] Gerar Relatório de Imóveis por Tipo Consumo
	 * 
	 * @author Bruno Barros
	 * @date 14/01/2008
	 * 
	 * @param FiltrarRelatorioImoveisTipoConsumo
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarRelatorioImoveisTipoConsumo(
		FiltrarRelatorioImoveisTipoConsumoHelper filtro) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0729] Gerar Relatório de Imóveis por Tipo Consumo
	 * 
	 * @author Bruno Barros
	 * @date 14/01/2008
	 * 
	 * @param FiltrarRelatorioImoveisTipoConsumo
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisTipoConsumo(
			FiltrarRelatorioImoveisTipoConsumoHelper filtro) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0762] Gerar Arquivo Texto com Dados Cadastrais - CAERN
	 * 
	 *  A pesquisa retorna uma colecao de Imoveis para que a partir
	 *  daí comece a geracao das linhas TXTs.
	 *  
	 * @author Tiago Moreno
	 * @date 08/04/2008
	 * 
	 * @param ArquivoTextoDadosCadastraisHelper
	 * 
	 * @return Collection<Imovel>
	 * @throws ControladorException
	 */
	public Collection<Imovel> pesquisarImovelArquivoTextoDadosCadastrais(
			ArquivoTextoDadosCadastraisHelper objeto)
				throws ErroRepositorioException;
	
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
	
	public Collection<HidrometroInstalacaoHistorico> pesquisarImovelArquivoTextoLigacoesHidrometro(
			ArquivoTextoLigacoesHidrometroHelper objeto) 
				throws ErroRepositorioException;
	
	/**
	 * Pesquisa o id localidade,codigo setor e codigo da rota 
	 * apartir do id da rota
	 * 
	 * @author Rafael Pinto

	 * @date 02/06/2008
	 * 
	 * @throws ErroRepositorioException
	 * @return Object[3] onde :
	 * 
	 * Object[0]=id localidade
	 * Object[1]=codigo do setor
	 * Object[2]=codigo da rota
	 */
	public Object[] pesquisarDadosRotaEntregaContaPorRota(Integer idRota)
			throws ErroRepositorioException ;
	
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
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarSetorComercialPorQualidadeAgua(int tipoArgumento, BigDecimal indiceInicial, 
			BigDecimal indiceFinal, Integer anoMesReferencia) throws ErroRepositorioException ;
	
	/**
	 * [UC0831] Gerar Tabelas para Atualização Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 25/08/2008
	 * 
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */

	public Object[] obterImovelGeracaoTabelasTemporarias(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC0831] Gerar Tabelas para Atualização Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 20/09/2008
	 * 
	 * @return ImovelSubcategoria
	 * @throws ErroRepositorioException
	 */

	public Collection obterImovelSubcategoriaAtualizacaoCadastral(Integer idImovel) throws ErroRepositorioException;
		

	/**
	 * [UC0726] Gerar Relatório de Imóveis com Faturas em Atraso Contas Descritas
	 * 
	 * @author Flávio Leonardo
	 * @date 08/09/2008
	 * 
	 * @param FiltrarRelatorioImoveisFaturasAtraso
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarRelatorioImoveisFaturasAtrasoDescritasLocalizacao(
		FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) 
		throws ErroRepositorioException;

	/**
	 * [UC0726] Gerar Relatório de Imóveis com Faturas em Atraso Contas Descritas
	 *
	 *@since 02/09/2009
	 *@author Marlon Patrick
	 */
	public Collection<Object[]> pesquisarRelatorioImoveisFaturasAtrasoDescritasCliente(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) 
			throws ErroRepositorioException;

    /**
     * 
     * [UC0535] Manter Feriado
     * 
     * @author bruno
     * @date 12/01/2009
     *
     * @param anoOrigemFeriado
     */
    public Collection<NacionalFeriado> pesquisarFeriadosNacionais( 
            String anoOrigemFeriado  )
        throws ErroRepositorioException;
    
    /**
     * 
     * [UC0535] Manter Feriado
     * 
     * @author bruno
     * @date 12/01/2009
     *
     * @param anoOrigemFeriado
     */
    public Collection<MunicipioFeriado> pesquisarFeriadosMunicipais( 
            String anoOrigemFeriado  )
        throws ErroRepositorioException; 
    
    /**
     * 
     * [UC0535] Manter Feriados
     *
     * @author bruno
     * @date 13/01/2009
     *
     * @param anoDestino
     * @throws ErroRepositorioException
     */
    public void excluirFeriadosNacionais( String anoDestino ) 
        throws ErroRepositorioException;
        
    /**
     * 
     * [UC0535] Manter Feriados
     *
     * @author bruno
     * @date 13/01/2009
     *
     * @param anoDestino
     * @throws ErroRepositorioException
     */
    public void excluirFeriadosMunicipais( String anoDestino ) 
        throws ErroRepositorioException;
    
    /**
	 * [UC 1392] Consultar Roteiro Dispositivo Móvel Atualização Cadastral
	 * [IT 0006] Exibir Dados Cadastrador
	 */
	public Collection pesquisarDadosCadastrador(Integer idParametroAtualizacaoCadastral) throws ErroRepositorioException;
    
    /**
	 * @author Anderson Cabral
	 * @since 17/10/2013
	 */
    public ArquivoTextoAtualizacaoCadastralDM pesquisarArquivoAtlzCadEmCampoPorCadastrador(Integer idCadastrador)  throws ErroRepositorioException;
    
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
    public Collection pesquisarLocalidades() throws ErroRepositorioException ;
    
    /**
	 * [UC1392] - Consultar Roteiro Dispositivo Móvel Atualização Cadastral
	 * 
	 * @author Davi Menezes
	 * @date 28/11/2012
	 *
	 */
	public void atualizarListaAtualizacaoCadastralArquivoTexto(
			Collection<AtualizacaoCadastralArquivoTextoHelper> colecaoAtualizacaoCadastralArquivoTexto,
			Integer idSituacaoLeituraNova, Leiturista leiturista, Date date) throws ErroRepositorioException;
    
	/**
	 * [UC0890]Consultar Arquivo Texto Atualização Cadastral 
	 * 
	 * @author Ana Maria
	 * @date 04/03/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarArquivoTextoAtualizacaoCadastro(String idEmpresa, 
			String idLocalidade, String idAgenteComercial, String idSituacaoTransmissao)throws ErroRepositorioException;
	/**
	 * [UC0890]Consultar Arquivo Texto Atualização Cadastral 
	 * 
	 * @author Ana Maria
	 * @date 04/03/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public ArquivoTextoAtualizacaoCadastral pesquisarArquivoTextoAtualizacaoCadastro(String idArquivoTxt)
		throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC0890]Consultar Arquivo Texto Atualização Cadastral 
	 * 
	 * @author Ana Maria
	 * @date 05/03/2009
	 * 
	 * @return void
	 * @throws ErroRepositorioException
	 */
	public void atualizarArquivoTextoAtualizacaoCadstral(Integer idArquivoTxt, Integer idSituacaoTransmissao)
			throws ErroRepositorioException;
	
	/**
	 * [UC0830] Gerar Tabelas para Atualização Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 25/08/2008
	 * 
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */

	public Collection<Integer>  obterIdsImovelGeracaoTabelasTemporarias(Integer idSetor, ImovelGeracaoTabelasTemporariasCadastroHelper helper) 
	throws ErroRepositorioException;
	
	/**
	 * [UC0830] Gerar Tabelas para Atualização Cadastral via celular 
	 * 
	 * @author Ana Maria
	 * @date 26/03/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImovelDebitoAtualizacaoCadastral(Collection colecaoIdsImovel)
	throws ErroRepositorioException;
	
    /**
	 * [UC0893] - Inserir Unidade de Negocio
	 * 
	 * Verificar se o Cliente Selecionado existe na tabela Funcionario
	 * 
	 * @author Vinicius Medeiros
	 * @date 08/04/2009
	 * 
	 * @param idCliente
	 * @throws ControladorException
	 */
    
    public Integer verificarClienteSelecionadoFuncionario(Integer idCliente) 
    	throws ErroRepositorioException ;

    /**
	 * Pesquisa a(s) quadra face associada a quadra 
	 * 
	 * Autor: Arthur Carvalho
	 * 
	 * Data: 28/04/2009
	 */
	public Collection<Object[]> pesquisarQuadraFaceAssociadaQuadra(Integer idQuadra) 
			throws ErroRepositorioException ;
	
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
	throws ErroRepositorioException;

	/**
	 * [UC0912] Gerar Boletim de Custo Atualização Cadastral
	 *
	 * O sistema seleciona as operações efetuadas pela empresa no período informado e com imóvel associado
	 * [SB0001 - Selecionar Operações Efetuadas com Imóvel Associado].
	 * 
	 * @author Vivianne Sousa
	 * @date 25/06/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOperacoesEfetuadasComImovelAssociado(Date dataInicio, Date dataFim,Integer idEmpresa)
		throws ErroRepositorioException;
	/**
	 * [UC0912] Gerar Boletim de Custo Atualização Cadastral
	 *
	 * O sistema seleciona as operações efetuadas pela empresa no período informado e sem imóvel associado
	 * [SB0002] - Selecionar Operações Efetuadas sem Imóvel Associado
	 * 
	 * @author Vivianne Sousa
	 * @date 25/06/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOperacoesEfetuadasSemImovelAssociado(Date dataInicio, Date dataFim,Integer idEmpresa)
		throws ErroRepositorioException;
	
	/**
	 * [UC0912] Gerar Boletim de Custo Atualização Cadastral
	 *
	 * O sistema obtém os dados do contrato com a empresa 
	 * (a partir da tabela EMPRESA_CONTRATO_CADASTRO com EMPR_ID=Id da empresa retornado 
	 * e ECCD_DTFINALCONTRATO maior ou igual à data corrente e ECCD_DTCANCELCONTRATO com o valor nulo)
	 * 
	 * @author Vivianne Sousa
	 * @date 25/06/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public EmpresaContratoCadastro pesquisarEmpresaContratoCadastro(Integer idEmpresa)
		throws ErroRepositorioException;
	
	/**
	 * [UC0912] Gerar Boletim de Custo Atualização Cadastral
	 *
	 * O sistema seleciona os atributos que compõem o boletim 
	 * (a partir da tabela ATRIBUTO ordenando pelo grupo do atributo (ATGR_ID) 
	 * e pela ordem de emissão (ATRB_NNORDEMEMISSAO)).
	 * 
	 * @author Vivianne Sousa
	 * @date 26/06/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarAtributosBoletim()
		throws ErroRepositorioException;
	
	/**
	 * [UC0912] Gerar Boletim de Custo Atualização Cadastral
	 *
	 * Valor de Atualização do Atributo 
	 * (ECCA_VLATUALIZACAO da tabela EMPRESA_CONTRATO_CADASTRO_ATRIBUTO 
	 * com ATRB_ID=ATRB_ID da tabela ATRIBUTO e 
	 * ECCD_ID=ECCD_ID da tabela EMPRESA_CONTRATO_CADASTRO);
	 * 
	 * @author Vivianne Sousa
	 * @date 26/06/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarValorAtualizacaoAtributo(
			Integer idAtributo,Integer idEmpresaContratoCadastro)throws ErroRepositorioException;
	
	/**
	 * [UC0925] Emitir Boletos
	 *
	 * @author Vivianne Sousa
	 * @date 09/07/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosBoleto(int quantidadeInicio, Integer grupo, String nomeEmpresa)throws ErroRepositorioException;
	
	/**
	 * [UC0925] Emitir Boletos
	 *
	 * retrona DBTP_VLLIMITE para DBTP_ID = idDebitoTipo
	 *
	 * @author Vivianne Sousa
	 * @date 09/07/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarValorLimiteDebitoTipo(Integer idDebitoTipo)throws ErroRepositorioException;
	
	/**
	 * [UC0407]-Filtrar Imóveis para Inserir ou Manter Conta
	 * [FS0011]-Verificar a abrangência do código do usuário
	 *
	 * @author Vivianne Sousa
	 * @date 31/07/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public UnidadeNegocio pesquisarUnidadeNegocioUsuario(Integer idUsuario)throws ErroRepositorioException;
	
	/**
	 * [UCXXXX]- Excluir Imoveis Da Tarifa Social
	 * CRC - 2113
	 * 
	 * @author Genival Barbosa
	 * @date 15/09/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public List pesquisarImoveisExcluirDaTarifaSocial(Integer idSetor, Integer anoMesFaturamento)throws ErroRepositorioException;

	/**
	 * [UCXXXX]- Excluir Imoveis Da Tarifa Social
	 * CRC - 2113
	 * 
	 * @author Genival Barbora
	 * @date 15/09/2009
	 * 
	 * @return void
	 * @throws ErroRepositorioException
	 */
	public void atualizarExcluirDaTarifaSocialTabelaDadoEconomia(String idImovel)throws ErroRepositorioException;
	
	/**
	 * [UCXXXX]- Excluir Imoveis Da Tarifa Social
	 * CRC - 2113
	 * 
	 * @author Genival Barbora
	 * @date 15/09/2009
	 * 
	 * @return void
	 * @throws ErroRepositorioException
	 */
	public void atualizarExcluirDaTarifaSocialTabelaImovel(String idImovel)throws ErroRepositorioException;
	
	/**
	 * [UC0727] Gerar Relatório de Imóveis por Consumo Medio
	 * 
	 * @author Arthur Carvalho
	 * @date 02/10/2009
	 * 
	 * @param FiltrarRelatorioImoveisConsumoMedio
	 * 
	 * @return quantidade de imoveis
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarRelatorioImoveisConsumoMedioCount(
		FiltrarRelatorioImoveisConsumoMedioHelper filtro, Integer anoMesFaturamento) 
		throws ErroRepositorioException;
	
	/**
	 *  Gerar Relatório de Imóveis 
	 * 
	 * @author Arthur Carvalho
	 * @date 08/10/2009
	 * 
	 * @return quantidade de imoveis
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarImovelAtualizacaoCadastralComIndicadorExclusaoCount() throws ErroRepositorioException;
	
	
	/**
	 *  Pesquisar Ids dos Imoveis com siac_id = 0 and empresa = a empresa do leiturista 
	 * 
	 * @author Arthur Carvalho
	 * @date 27/10/2009
	 * 
	 * @return quantidade de imoveis
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsImoveisAtualizacaoCadastral(Integer idEmpresaLeiturista) throws ErroRepositorioException;
	
	/**
	 * Pesquisa as críticas existentes para um determinado arquivo importado da atualizacao cadastral simplificado.
	 * 
	 * [UC0969] Importar arquivo de atualização cadastral simplificado
	 * 
	 * @author Samuel Valerio
	 * @date 22/10/2009
	 * 
	 * @param idArquivo Id do arquivo
	 * @return Coleção de críticas do arquivo
	 */
	public Collection<AtualizacaoCadastralSimplificadoCritica> pesquisarAtualizacaoCadastralSimplificadoCritica(int idArquivo) throws ErroRepositorioException;
	
	/**
	 * [UC0925] Emitir Boletos
	 *
	 * retrona DBTP_VLSUGERIDO para DBTP_ID = idDebitoTipo
	 *
	 * @author Rômulo Aurélio
	 * @date 22/12/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarValorSugeridoDebitoTipo(
			Integer idDebitoTipo)throws ErroRepositorioException;
	/**
	 * [UC0890]Consultar Arquivo Texto Atualização Cadastral 
	 * 
	 * @author Ana Maria
	 * @date 04/03/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public ArquivoTextoAtualizacaoCadastral pesquisarArquivoTextoAtualizacaoCadastro(String idArquivoTxt, Integer idSituacaoTransmissao)
		throws ErroRepositorioException;

	/**
	 * [UC0979] Gerar Relatório de Imóveis em Programas Especiais Analitico
	 * 
	 * @author Hugo Leonardo
	 * @date 18/01/2010
	 * 
	 * @param RelatorioImoveisProgramasEspeciaisHelper
	 * 
	 * @return Collection<RelatorioImoveisProgramasEspeciaisHelper>
	 * @throws FachadaException
	 */
	public Collection pesquisarRelatorioImoveisProgramasEspeciaisAnalitico(
			FiltrarRelatorioImoveisProgramasEspeciaisHelper helper) throws ErroRepositorioException;
	
	/**
	 * [UC0979] Gerar Relatório de Imóveis em Programas Especiais Sem Hidrometro
	 * 
	 * @author Hugo Leonardo
	 * @date 25/01/2010
	 * 
	 * @param RelatorioImoveisProgramasEspeciaisHelper
	 * 
	 * @return Collection<RelatorioImoveisProgramasEspeciaisHelper>
	 * @throws FachadaException
	 */
	public Collection pesquisarRelatorioImoveisProgramasEspeciaisSintetico(
			FiltrarRelatorioImoveisProgramasEspeciaisHelper helper) throws ErroRepositorioException;
	
	/**
	 * [UC0979] Gerar Relatório de Imóveis em Programas Especiais
	 * 
	 * @author Hugo Leonardo
	 * @date 18/01/2010
	 * 
	 * @param RelatorioImoveisProgramasEspeciaisHelper
	 * 
	 * @return Collection<RelatorioImoveisProgramasEspeciaisHelper>
	 * @throws FachadaException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisProgramaEspecial(
			FiltrarRelatorioImoveisProgramasEspeciaisHelper helper) throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC0976] Suspender Imóvel em Programa Especial Batch
	 *  	Pesquisa imoveis para execução do batch
	 * @author Hugo Amorim
	 * @since 13/01/2010
	 *
	 */
	public Collection pesquisarImovelEmProgramaEspecial(
			Integer idPerfilProgramaEspecial,
			Rota rota,
			int numeroIndice, 
			int quantidadeRegistros)
		throws ErroRepositorioException;
	
	/**
	 * [UC0973] Inserir Imóvel em Programa Especial
	 * 
	 * @author Hugo Leonardo
	 * @date 10/02/2010
	 * 
	 * @param idImovel
	 * 
	 * @return Quantidade de Parcelamentos do Imóvel
	 * @throws FachadaException
	 */
	public Integer verificarExistenciaParcelamentoImovel(Integer idImovel)
			throws ErroRepositorioException;
	
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
			String faturamentoGrupo, String idLocalidadeInicial, String idLocalidadeFinal, 
			String idSetorComercialInicial, String idSetorComercialFinal,
			String rotaInicial, String rotaFinal, 
			String sequencialRotaInicial, String sequencialRotaFinal) throws ErroRepositorioException;
	
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
	public Integer pesquisarTotalRegistroRelatorioColetaMedidorEnergia(
			String faturamentoGrupo, String idLocalidadeInicial, String idLocalidadeFinal,
			String idSetorComercialInicial, String idSetorComercialFinal,
			String rotaInicial, String rotaFinal,
			String sequencialRotaInicial, String sequencialRotaFinal) throws ErroRepositorioException;
	/**
	 * 
	 * Batch criado para atualização da coluna codigo debito automatico do imovel.
	 * 
	 * @author Hugo Amorim
	 * @date 30/03/2010	
	 */
	public Collection<Integer> pesquisarIdsImoveisDoSetorComercial(Integer idSetor,
			int quantidadeInicio, int quantidadeMaxima)throws ErroRepositorioException;
	
	/**
	 * 
	 * Batch criado para atualização da coluna codigo debito automatico do imovel.
	 * 
	 * @author Hugo Amorim
	 * @date 30/03/2010	
	 */
	public void atualizarCodigoDebitoAutomatico(Integer idImovel,
			Integer codigoDebitoAutomatico)throws ErroRepositorioException;
	
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
    public byte[] baixarNovaVersaoJad() throws ErroRepositorioException;
    
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
    public byte[] baixarNovaVersaoJar() throws ErroRepositorioException;  
    
    /**
     * 
     * @author Fernando Fontelles
     * @date 07/07/2010
     * 
     * @param idImovel
     * @return
     * @throws ErroRepositorioException
     */
    public boolean verificarSituacaoImovelCobrancaJudicial(Integer idImovel) throws ErroRepositorioException; 
    
    /**
     * 
     * @author Fernando Fontelles
     * @date 07/07/2010
     * 
     * @param idImovel
     * @return
     * @throws ControladorException
     */
    public boolean verificarSituacaoImovelNegativacao( Integer idImovel ) throws ErroRepositorioException;
    
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
 			String cnpjClienteAtual, String emailAtual) throws ErroRepositorioException;
    
    /**
     * Atualiza o sequencial de rota do imóvel correspondente ao
     * RotaAtualizacaoSeq recebido 
     *  
     * @author bruno
     * @date 11/08/2010
     * 
     * @param rotaAtualizacaoSeq - Registro da tabela micromedicao.rota_atualizacao_sequencial
     * 
     * @throws ErroRepositorioException
     */
    public void atualizarSequenciaRotaImovel( 
            RotaAtualizacaoSeq seq )
            throws ErroRepositorioException;
    /**
     * 
     * @author Rômulo Aurélio
     * @date 28/09/2010
     * 
     * @param idImovel
     * @return
     * @throws ControladorException
     */
    public ClienteImovel pesquisarClienteResponsavelComEsferaPoderPublico( Integer idImovel ) 
    		throws ErroRepositorioException;
    
    /**
     * [UC1074] Gerar Relatório Alterações no Sistema por Coluna
     *  	-TIPO USUARIO
     * @author Hugo Amorim
     * @date 08/09/2010
     */
 	public Collection<Object[]> pesquisarDadosRelatorioAlteracoesSistemaColunaPorUsuario(GerarRelatorioAlteracoesSistemaColunaHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1074] Gerar Relatório Alterações no Sistema por Coluna
     * 		-TIPO LOCALIDADE
     * @author Hugo Amorim
     * @date 08/09/2010
     */
 	public Collection<Object[]> pesquisarDadosRelatorioAlteracoesSistemaColunaPorLocalidade(GerarRelatorioAlteracoesSistemaColunaHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1074] Gerar Relatório Alterações no Sistema por Coluna
     * 
     * [FS0007] 
     * 
     * @author Hugo Amorim
     * @date 08/09/2010
     */
    public boolean verificarRelacaoColuna(Integer idColuna) throws ErroRepositorioException;
    
    
    
    /**
     * [UC1076] Gerar Relatório Atualizações Cadastrais Via Internet.
     * 
     * @author Daniel Alves
     * @date 28/09/2010
     */
 	public Collection<Object[]> pesquisarDadosRelatorioAtualizacaoCadastralViaInternet(GerarRelatorioAtualizacaoCadastralViaInternetHelper helper)
 		throws ErroRepositorioException;
 	
 	
 	/**
     * [UC1076] Gerar Relatório Atualizações Cadastrais Via Internet.
     * 
     * @author Daniel Alves
     * @date 28/09/2010
     */
 	public Collection<Object[]> pesquisarDadosRelatorioResumoAtualizacaoCadastralViaInternet(GerarRelatorioAtualizacaoCadastralViaInternetHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1076] Gerar Relatório Atualizações Cadastrais Via Internet.
     * 
     * @author Hugo Amorim de Lyra
     * @date 06/10/2010
     */
 	public Integer countRelatorioAtualizacaoCadastralViaInternet(GerarRelatorioAtualizacaoCadastralViaInternetHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
	 * [UC1121] Gerar Relatório de Imóveis com Alteração de Inscrição Via Batch.
	 *
	 * @author Hugo Leonardo
	 * @date 19/01/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioImoveisAlteracaoInscricaoViaBatch( 
			FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper relatorioHelper) 
		throws ErroRepositorioException;
 	
	/**
	 * [UC1121] Gerar Relatório de Imóveis com Alteração de Inscrição Via Batch.
	 *
	 * @author Hugo Leonardo
	 * @date 19/01/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Integer countTotalRelatorioImoveisAlteracaoInscricaoViaBatch( 
			FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper relatorioHelper) 
		throws ErroRepositorioException;

    /**
     * [UC1124] Gerar Relatório de Alterações de CPF/CNPJ
     * 
     * @author Mariana Victor
     * @date 16/02/2011
     */
 	public Collection<Object[]> pesquisarDadosRelatorioAlteracoesCpfCnpjPorUsuario(GerarRelatorioAlteracoesCpfCnpjHelper helper)
 		throws ErroRepositorioException;

    /**
     * [UC1124] Gerar Relatório de Alterações de CPF/CNPJ
     * 
     * @author Mariana Victor
     * @date 17/02/2011
     */
 	public Collection<Object[]> pesquisarDadosRelatorioAlteracoesCpfCnpjPorLocalidade(GerarRelatorioAlteracoesCpfCnpjHelper helper)
 		throws ErroRepositorioException;
 	
 	/**
     * [UC1124] Gerar Relatório de Alterações de CPF/CNPJ por Meio de Solicitação
     * 
     * @author Mariana Victor
     * @date 16/02/2011
     */
 	public Collection<Object[]> pesquisarDadosRelatorioAlteracoesCpfCnpjPorMeio(GerarRelatorioAlteracoesCpfCnpjHelper helper)
 		throws ErroRepositorioException;

 	/**
	 * UC1162 – AUTORIZAR ALTERACAO INSCRICAO IMOVEL
	 * @author Rodrigo Cabral
	 * @date 05/06/2011
	 */
	public Collection pesquisaImovelInscricaoAlterada(ImovelInscricaoAlteradaHelper helper)
		throws ErroRepositorioException;
	

	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarLocalidadesPorGerencia(Integer idGerenciaRegional)throws ErroRepositorioException;
	
		/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarLocalidadesPorUnidadeNegocio(Integer idUnidadeNegocio)throws ErroRepositorioException;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarLocalidade()throws ErroRepositorioException ;
	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public TarifaSocialMotivoCarta pesquisarTarifaSocialMotivoCarta(
			Integer idTarifaSocialMotivoCarta)throws ErroRepositorioException;

	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * 
	 * @author Vivianne Sousa
	 * @date 02/05/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarLocalidadesPorGerenciaEUnidade(Integer idGerenciaRegional
			,Integer idUnidadeNegocio)throws ErroRepositorioException;
	
	/**
	 * [UC1170] Gerar Relatório Acesso ao SPC
	 *  
	 * @author: Diogo Peixoto
	 * @date: 06/05/2011
	 * 
	 * @param FiltrarRelatorioAcessoSPCHelper
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> filtrarRelatorioAcessoSPC(FiltrarRelatorioAcessoSPCHelper filtrarRelatorioAcessoSPCHelper) throws ErroRepositorioException;

	
	
	/**
     * Obtém a coleção de categorias.
     * 
     * @author Hugo Azevedo
     * @date 22/06/2011
     * 
     * @throws ErroRepositorioException
     */
	
	public Collection obterCategorias() throws ErroRepositorioException;
	
	
	/**
     * Obtém a coleção de perfis dos imóveis.
     * 
     * @author Hugo Azevedo
     * @date 22/06/2011
     * 
     * @throws ErroRepositorioException
     */
	
	public Collection obterPerfisImoveis() throws ErroRepositorioException;
	
	

	/**
	 * [UC1182] Recepcionar Arquivo TXT Encerramento OS Cobrança
	 * 
	 * Consulta chamada pelo "[FS0010 – Validar Identificação do Usuário.]" 
	 * 
	 * @author Mariana Victor
	 * @data 21/06/2011
	 */
	public Boolean verificarIdentificacaoUsuario(Integer idUsuario) throws ErroRepositorioException;

	/**
	 * [UC1182] Recepcionar Arquivo TXT Encerramento OS Cobrança
	 * 
	 * Consulta chamada pelo "[FS0010 – Validar Identificação do Usuário.]" 
	 * 
	 * @author Mariana Victor
	 * @data 21/06/2011
	 */
	public Boolean verificarUsuarioEmpresaComandoCobranca(Integer idUsuario, Integer idComando) throws ErroRepositorioException;

	/**
	 * [UC1182] Recepcionar Arquivo TXT Encerramento OS Cobrança
	 * 
	 * Pesquisa o email da Empresa 
	 * 
	 * @author Mariana Victor
	 * @data 22/06/2011
	 */
	public String pesquisarEmailEmpresa(Integer idEmpresa) throws ErroRepositorioException;
	
	
	/**
	 * [UC34] Importância Logradouro
	 * 
	 * Atualiza a Importância do Logradouro
	 * 
	 * @author Fernanda Almeida
	 * @data 30/06/2011
	 */
	public void atualizarGrauImportancia(LogradouroBairro logradouroBairro, Integer grauImportancia) throws ErroRepositorioException;
	
	/**
	 * [MA2011061013]
	 * 
	 * @author Paulo Diniz
	 * @date 02/07/2011
	 * 
	 * @param idImovel
	 * 
	 * @return HidrometroMovimentado
	 * @throws ErroRepositorioException
	 */
	public  List<HidrometroInstalacaoHistorico> pesquisarHidrometroPeloIdImovel(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC0588 / UC0589] Verifica existência do DDD
	 * 
	 * @author Nathalia Santos
	 * @data 23/09/2011
	 */
	public Boolean verificarDdd(Short Ddd) throws ErroRepositorioException;
	
	/**
	 * [UC0588 / UC0589] Verifica existência do funcionáriio ou do cliente
	 * 
	 * @author Nathalia Santos
	 * @throws ErroRepositorioException 
	 * @data 03/10/2011
	 */
	public  Boolean pesquisarFuncionarioOuCliente(Integer IdFuncionario, Integer idCliente) throws ErroRepositorioException;

		
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
	public Collection<Logradouro> pesquisarLogradouroMesmoNome(String logradouroNome, Integer idMunicipio) throws ErroRepositorioException;
	
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
	public Collection<Logradouro> filtrarLogradouroMesmoNome(String logradouroNome, Integer numeroPagina, Integer idMunicipio) 
			throws ErroRepositorioException;
	
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
			throws ErroRepositorioException;

	/**
	 * Método que pesquisa uma 
	 * EmpresaCobrancaFaixa pelo id
	 * 
	 * @author Raimundo Martins
	 * @date 24/10/2011
	 * */
	public EmpresaCobrancaFaixa pesquisarEmpresaCobrancaFaixa(Integer idCobrancaFaixa) throws ErroRepositorioException;
	
	/**
	 * Atualizar nome do usuario com id de funcionario igual ao informado 
	 * 
	 * @author Erivan Sousa
	 * @date 06/12/2011
	 * 
	 * @param idFuncionario
	 * @param nomeFuncionario
	 * 
	 * @throws ErroRepositorioException
	 */

	public void atualizarNomeUsuarioComIdFuncionario(Integer idFuncionario,	String nomeFuncionario)throws ErroRepositorioException;
	
	/**
	 * [UC 1256] Retirar Imóveis e Contas das Empresas de Cobrança
	 * 
	 * Método que atualiza a situação de cobrança do imovel
	 * 
	 * @author Raimundo Martins
	 * @date 16/12/2010
	 * */
	
	public void atualizarImovelCobrancaSituacao(Integer idImovel, Integer idCobrancaSituacao)throws ErroRepositorioException;
	
	/**
	 * [UC 1256] Retirar Imóveis e Contas das Empresas de Cobrança
	 * 
	 * Método que atualiza a situação do imovel
	 * 
	 * @author Raimundo Martins
	 * @date 16/12/2010
	 * */
	
	public void atualizarImovelSituacao(Integer idImovel)throws ErroRepositorioException;
	
	/**
	 * [UC 1256] Retirar Imóveis e Contas das Empresas de Cobrança
	 * 
	 * Método que pesquisa os dados de Motivo de Retirada Cobrança
	 * 
	 * @author Raimundo Martins
	 * @date 02/02/2012
	 * */
	public Collection<MotivoRetiradaCobranca> pesquisarDadosMotivoRetiradaCobranca() throws ErroRepositorioException;
	
	/**
	 * 
	 * Pega o id do Tipo de Cliente, através do id do imóvel
	 * 
	 * @author Rodrigo Cabral
	 * @date 08/06/2012
	 * */
	
	public Integer obterClienteTipoId(Integer idImovel)throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC1367] Registrar Movimento do Programa Especial
	 * [SB0002] Inserir Imóveis em Programa Especial
	 * 
	 * @author Hugo Azevedo
	 * @date 20/08/2012
	 * 
	 */
	public ItemMovimentoProgramaEspecial pesquisarItemMovimentoProgramaEspecial(Integer idImovel, Integer idMovimento) throws ErroRepositorioException;
	
	
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
	public Collection obterDadosRelatorioMovimentoProgramaEspecial(Integer idMovimento) throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC1367] Registrar Movimento do Programa Especial
	 * [SB0003] Suspender Imóveis em Programa Especial
	 * 
	 * @author Hugo Azevedo
	 * @date 22/08/2012
	 * 
	 */
	public ImovelProgramaEspecial obterImovelProgramaEspecial(Integer idImovel) throws ErroRepositorioException;
	
	
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
	public Integer verificarProcessamentoArquivoMovimentoProgramaEspecial(String fileName) throws ErroRepositorioException;
	
	/**
	 * [UC1309] Download nova versão Sistemas Android
	 * 
	 * @author Carlos Chaves
	 * @date 01/09/2012
	 * 
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] baixarNovaVersaoApk( Integer idSistemaAndroid ) throws ErroRepositorioException;
	
	/**
	 * [UC1373] Gerar Relatório Histórico Imóveis Programa Especial.
	 * 
	 * @author Jonathan Marcos
	 * @date 02/05/2013
	 *  
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarRelatorioHistoriocoImoveisProgramaEspecial(
		FiltrarRelatorioHistoricoImoveisProgramaEspecialHelper filtro) 
	throws ErroRepositorioException;

	/**
	 * 
	 * [UC1527] Inserir Ocorrência Operacional
	 * 
	 * @author Rômulo Aurélio
	 * @date 12/07/2013
	 * 
	 */
	public Collection<Object> obterLocalidadesdoMunicipio(Integer idMunicipio) throws ErroRepositorioException;
	
	/**
	 * @author Jonathan Marcos
	 * @date 11/02/2014
	 * RM10044
	 */
	public Short verificarClienteTipoProgramaEspecial(Integer idImovel)
		throws ErroRepositorioException;
	
	
	/**
	 * 
	 * [UC0738] Gerar Certidão Negativa por Imóvel
	 * [FS0006] Validar CPF/CNPJ         
	 * 
	 * @author Hugo Azevedo
	 * @date 07/03/2014
	 * 
	 */
	public ClienteImovel obterClienteImovel(Integer idImovel) throws ErroRepositorioException;
	
	public Collection<Object[]> obterMensagemEmailFaturamentoCobrancaParaEnviar(String dataPrevistaEnvio,String dataLimiteEnvio) 
			throws ErroRepositorioException;
	
	public Collection<Object[]> obterMensagemEmailFaturamentoCobrancaDataLimite(String dataCorrente) 
			throws ErroRepositorioException;
	
	/*
	 * [UC1619] - Processar Retorno do SMS
	 * 
	 * [FS0001] - Gerar Lista de Lotes de SMS pendentes 
	 * 
	 * @author Raphael Rossiter
	 * @date 07/08/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer[]> pesquisarLotesSMSPendentes() throws ErroRepositorioException ;
	
	/*
	 * [UC1619] - Processar Retorno do SMS
	 * 
	 * [SB0007] - Atualizar total SMS enviados 
	 * 
	 * @author Raphael Rossiter
	 * @date 07/08/2014
	 * 
	 * @param cobrancaAcaoAtividadeComando
	 * @throws ErroRepositorioException
	 */
	public void atualizarTotalSMSEnviado(CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando) throws ErroRepositorioException;
	
	/*
	 * [UC1619] - Processar Retorno do SMS
	 * 
	 * [SB0006] - Atualizar contador de erro no envio por lote 
	 * 
	 * @author Raphael Rossiter
	 * @date 11/08/2014
	 * 
	 * @param smsSequenciaEnvio
	 * @throws ErroRepositorioException
	 */
	public void atualizarTotalSMSErro(SMSSequenciaEnvio smsSequenciaEnvio) throws ErroRepositorioException ;
	
	/*
	 * [UC1619] - Processar Retorno do SMS
	 * 
	 * [SB0005] - Atualizar Tentativas de envio SMS 
	 * 
	 * @author Raphael Rossiter
	 * @date 11/08/2014
	 * 
	 * @param mensagemSMSFaturamentoCobranca
	 * @throws ErroRepositorioException
	 */
	public void atualizarTotalSMSTentativa(MensagemSMSFaturamentoCobranca mensagemSMSFaturamentoCobranca) throws ErroRepositorioException ;

	
	public Collection<Object> obterCobrancaAcaoAtividadeComandoEmail(String dataCorrente) 
			throws ErroRepositorioException;
	
	public Collection<Object[]> obterMensagemSMSFaturamentoCobrancaParaEnviar(String dataPrevistaEnvio,String dataLimiteEnvio,
			Integer codigoRetornoGerado) throws ErroRepositorioException;
	
	public Integer obterQuantidadeSMSGerados(String dataPrevistaEnvio,String dataLimiteEnvio,Integer codigoRetornoGerado) 
			throws ErroRepositorioException;
	
	public Collection<Object[]> obterMensagemSMSFaturamentoCobrancaDataLimite(String dataCorrente) 
			throws ErroRepositorioException;
	

	/*
	 * [UC1619] - Processar Retorno do SMS
	 * 
	 * [SB0005] - Atualizar Tentativas de envio SMS 
	 * 
	 * @author Raphael Rossiter
	 * @date 11/08/2014
	 * 
	 * @param mensagemSMSFaturamentoCobranca
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> obterMensagemSMSFaturamentoCobrancaParaRenviar(String dataPrevistaEnvio,String dataLimiteEnvio,
		Integer codigoRetornoGerado) throws ErroRepositorioException;

	/**
	 * [UC 1391] - Gerar Roteiro Dispositivo Movel
	 * 
	 * @author Diogo Luiz
	 * @date 27/08/2014
	 */
	public Collection<Object []> pesquisarBairrosImovel(Integer idLocalidade) throws ErroRepositorioException;

	

	/*
	 * [UC1618] - Enviar SMS em lote
	 * 
	 * @author Raphael Rossiter
	 * @date 26/08/2014
	 * 
	 * @param idSmsSequenciaEnvio
	 * @throws ErroRepositorioException
	 */
	public void atualizarDataEnvio(Integer idSmsSequenciaEnvio) throws ErroRepositorioException ;
	
	/**
	 * [UC1618] - Enviar SMS em lote
	 * 
	 * @author Rodrigo Cabral
	 * @date 24/09/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public ContaEmpresaSMS recuperarDadosContaEmpresaSMS() throws ErroRepositorioException ;

	public Collection<Object []> pesquisarCepBairros(Collection<Integer> colecaoBairros) throws ErroRepositorioException;
	
	public Collection<Object []> pesquisarLogradouro(Collection<Integer> colecaoBairros) throws ErroRepositorioException;
	
	public Collection<Object []> pesquisarLogradouroBairros(Collection<Integer> colecaoBairros) throws ErroRepositorioException;
	
	/**
	 * @author Diogo Luiz
	 * @date 27/08/2014
	 * 
	 * @param colecaoBairros
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object []> pesquisarLogradouroCepBairros(Collection<Integer> colecaoBairros) throws ErroRepositorioException;
	
	
	/**
	 * [UC 1391] - Gerar Roteiro Dispositivo Movel
	 * 
	 * [SB 0002] - Inserir Imóvel no Ambiente Virtual
	 * 
	 * @author Diogo Luiz
	 * @date 27/08/2014
	 * 
	 * 
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */

	public Object[] obterDadosImovelInsricaoResetorizada(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC1297] Pesquisar Imovel Subcategoria Atualização Cadastral
	 * 
	 * @author Diogo Luiz
	 * @since 27/08/2014
	 * 
	 * @param idImovelAtualizacaoCadastral
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<ImovelSubcategoriaAtualizacaoCadastralDM> pesquisarSubCategoriaAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral) 
			throws ErroRepositorioException;
	
	/**
	 * Pesquisar dados do Cliente Atualização Cadastral
	 * 
	 * @return ClienteAtualizacaoCadastral
	 * 
	 * @author Diogo Luiz
     * @date 27/08/2014
	 * @exception ErroRepositorioException
	 */
	public Collection<ClienteAtualizacaoCadastralDM> pesquisarClienteAtualizacaoCadastralClienteUsuario(Integer idImovelAtualizacaoCadastral) 
			throws ErroRepositorioException ;
	
	/**
	 * @author Diogo Luiz
	 * @date 27/08/2014
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection obterDadosHidrometroInstalacaoHistorico(Integer idImovel) throws ErroRepositorioException;
	
	public Collection<Integer>  pesquisarImoveisAtualizacaoCadastral(Integer idParametro) throws ErroRepositorioException;
	
	/**
	 * Pesquisa se o imovel ja foi transmitido do tablet pro pre-gsan
	 * 
	 * @author Diogo Luiz
	 * @since 04/09/2014
	 * 
	 * @param idImovel
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarImovelAtlzCadastralJaTransmitido(Integer idImovel, Integer idComando)throws ErroRepositorioException;
	
	/**
	 * Pesquisa se o imovel novo ja foi transmitido do tablet pro pre-gsan
	 * 
	 * @author Diogo Luiz
	 * @since 04/09/2014
	 * 
	 * @param codigoImovel
	 * @param idComando
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarImovelAtlzCadastralNovoJaTransmitido(String codigoImovel, Integer idComando)
		throws ErroRepositorioException;
	
	/**
	 * Metodo responsavel que verifica se o imovel contem todos os itens obrigatorios.
	 * 
	 * @param codigoImovelAtualizacaoCadastra
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIntegridadeImovelAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral)throws ErroRepositorioException;
	
	/**
	 *  Metodo responsavel por remover todos os dados do imovel atualizacao cadastral, caso ocorra erro no carregamento
	 * @param codigoImovel
	 * @throws ErroRepositorioException
	 */
	public void excluirDadosImovelAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral)throws ErroRepositorioException ;
	
	/**
	 * @author Diogo Luiz
	 * @param codigo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdImovelAtualizacaoCadastral(String codigo) throws ErroRepositorioException ;
	
	/**
	 * @author Diogo Luiz
	 * @param codigo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdClienteAtualizacaoCadastral(String codigo) throws ErroRepositorioException ;
	
	/**
	 * Método responsável por
	 * pesquisar os cep do 
	 * banco extraido do 
	 * tablet
	 * @author Jonathan Marcos
	 * @since 30/09/2014
	 * @param statement
	 * @return ResultSet
	 * @throws ErroRepositorioException
	 */
	public ResultSet pesquisarCepTablet(Connection connection) 
			throws ErroRepositorioException;
	
	/**
	 * Método responsável por
	 * pesquisar os logradouros do 
	 * banco extraido do tablet
	 * @author Jonathan Marcos
	 * @since 30/09/2014
	 * @param statement
	 * @return ResultSet
	 * @throws ErroRepositorioException
	 */
	public ResultSet pesquisarLogradouroTablet(Connection connection)
		throws ErroRepositorioException;
	
	/**
	 * Método responsável por
	 * pesquisar os logradouros bairros
	 * do banco extraido do tablet
	 * @author Jonathan Marcos
	 * @since 30/09/2014
	 * @param statement
	 * @return ResultSet
	 * @throws ErroRepositorioException
	 */
	public ResultSet pesquisarLogradouroBairroTablet(Connection connection,Integer idLogradouroTablet)
		throws ErroRepositorioException;
	
	/**
	 * Método responsável por
	 * pesquisar os logradouros ceps
	 * do banco extraido do tablet
	 * @author Jonathan Marcos
	 * @since 30/09/2014
	 * @param statement
	 * @return ResultSet
	 * @throws ErroRepositorioException
	 */
	public ResultSet pesquisarLogradouroCepTablet(Connection connection)
		throws ErroRepositorioException;
	
	/**
	 * Método responsável por
	 * pesquisar os imoveis
	 * do banco extraido do tablet
	 * @author Jonathan Marcos
	 * @since 30/09/2014
	 * @param statement
	 * @return ResultSet
	 * @throws ErroRepositorioException
	 */
	public ResultSet pesquisarImovelAtualizacaoCadastralTablet(Connection connection)
		throws ErroRepositorioException;
	
	/**
	 * Método responsável por
	 * pesquisar os clientes
	 * do banco extraido do tablet
	 * @author Jonathan Marcos
	 * @since 30/09/2014
	 * @param statement
	 * @return ResultSet
	 * @throws ErroRepositorioException
	 */
	public ResultSet pesquisarClienteAtualizacaoCadastralTablet(Connection connection,Integer 
			idImovelAtualizacaoCadastral) throws ErroRepositorioException;
	
	/**
	 * Método responsável por
	 * pesquisar os cliente fone
	 * do banco extraido do tablet
	 * @author Jonathan Marcos
	 * @since 30/09/2014
	 * @param statement
	 * @return ResultSet
	 * @throws ErroRepositorioException
	 */
	public ResultSet pesquisarClienteFoneAtualizacaoCadastral(Connection connection,Integer 
			idClienteAtualizacaoCadastral) throws ErroRepositorioException;
	
	/**
	 * Método responsável por
	 * pesquisar os hidrometro instalação
	 * histórico
	 * do banco extraido do tablet
	 * @author Jonathan Marcos
	 * @since 30/09/2014
	 * @param statement
	 * @return ResultSet
	 * @throws ErroRepositorioException
	 */
	public ResultSet pesquisarHidrometroInstalacaoHistoricoAtualizacaoCadastral(Connection connection,Integer
			idImovelAtualizacaoCadastral) throws ErroRepositorioException;
	
	/**
	 * Método responsável por
	 * pesquisar os imovel subcategoria
	 * do banco extraido do tablet
	 * @author Jonathan Marcos
	 * @since 30/09/2014
	 * @param statement
	 * @return ResultSet
	 * @throws ErroRepositorioException
	 */
	public ResultSet pesquisarImovelSubcategoriaAtualizacaoCadastral(Connection connection,Integer
			idImovelAtualizacaoCadastral) throws ErroRepositorioException;
	
	/**
	 * Método responsável por
	 * pesquisar os imovel ocorrência
	 * do banco extraido do tablet
	 * @author Jonathan Marcos
	 * @since 30/09/2014
	 * @param statement
	 * @return ResultSet
	 * @throws ErroRepositorioException
	 */
	public ResultSet pesquisarImovelOcorrenciaAtualizacaoCadastral(Connection connection,Integer
			idImovelAtualizacaoCadastral) throws ErroRepositorioException;

	/**
	 * Método responsável pela pesquisa das ocorrências de cadastro de imóvel
	 * de atualização cadastral.
	 * 
	 * @author André Miranda
	 * @since 01/10/2014
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List<CadastroOcorrencia> obterOcorrenciasImovelAtualizacaoCadastralDM(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * Método responsável pela pesquisa dos clientes de imóvel
	 * de atualização cadastral.
	 * 
	 * @author André Miranda
	 * @since 01/10/2014
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List<ClienteAtualizacaoCadastralDM> pesquisarClienteAtualizacaoCadastralDM(Integer idImovel) throws ErroRepositorioException;

	/**
	 * Método responsável pela pesquisa das subcategorias de imóvel
	 * de atualização cadastral.
	 * 
	 * @author André Miranda
	 * @since 01/10/2014
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List<ImovelSubcategoriaAtualizacaoCadastralDM> pesquisarSubCategoriaAtualizacaoCadastralDM(Integer idImovel) throws ErroRepositorioException;

	/**
	 * Método responsável pela pesquisa dos hidrometrosInstalacao de imóvel
	 * de atualização cadastral.
	 * 
	 * @author André Miranda
	 * @since 01/10/2014
	 * @param idImovel
	 * @param medicaoTipo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List<HidrometroInstalacaoHistoricoAtualizacaoCadastralDM> pesquisarHidrometroInstalacaoHistoricoAtualizacaoCadastralDM(
			Integer idImovel, Integer medicaoTipo) throws ErroRepositorioException;
	/**
	 * Método responsável por
	 * pesquisar os path das fotos
	 * dos imoveis atualizados 
	 * do banco extraido do tablet
	 * @author Jonathan Marcos
	 * @since 01/10/2014
	 * @param statement
	 * @return ResultSet
	 * @throws ErroRepositorioException
	 */
	public ResultSet pesquisarImovelFotoAtualizacaoCadastral(Connection connection)
		throws ErroRepositorioException;

	/**
	 * [UC1297] Pesquisar Imovel Atualização Cadastral
	 * 
	 * @author André Miranda
	 * @since 08/10/2014
	 *
	 * @param numeroHidrometro
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Hidrometro pesquisarHidrometroPeloNumero(String numeroHidrometro) throws ErroRepositorioException;
	
	/**
	 * [UC1292] Efetuar Instalação de Hidrômetro para Atualização Cadastral
	 * [SB0002] Atualizar Imóvel/Ligação de Água
	 * 
	 * @author André Miranda
	 * @since 08/10/2014
	 * 
	 * @param idImovel
	 * @param idHidrometroInstalacaoHistorico
	 * @throws ErroRepositorioException
	 */
	public void atualizarImovel(Integer idImovel, Integer idHidrometroInstalacaoHistorico) throws ErroRepositorioException;

	/**
	 * [UC1292] Efetuar Instalação de Hidrômetro para Atualização Cadastral
	 * [SB0002] Atualizar Imóvel/Ligação de Água
	 * 
	 * @author André Miranda
	 * @since 08/10/2014
	 * 
	 * @param idImovel
	 * @param idHidrometroInstalacaoHistorico
	 * @throws ErroRepositorioException
	 */
	public void atualizarLigacaoAgua(Integer idImovel, Integer idHidrometroInstalacaoHistorico) throws ErroRepositorioException;
	
	/**
	 * [UC1288] Atualizar Dados Cadastrais do Imóvel pelo Recadastramento
	 * 
	 * @author André Miranda
	 * @since 10/10/2014
	 * 
	 * @param indicadorAtualizacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<ImovelAtualizacaoCadastralDM> pesquisarImovelAtualizacaoCadastralDM(Short indicadorAtualizacao,
			Short indicadorDadosRetorno, Integer idLocalidade, int quantidadeRegistros ) throws ErroRepositorioException;

	/**
	 * Pesquisar dados do Parametro da Atualização Cadastral
	 * 
	 * @return ParametroTabelaAtualizacaoCadastro
	 * 
	 * @author André Miranda
     * @date 13/10/2014
	 * @exception ErroRepositorioException
	 */
	public ParametroTabelaAtualizacaoCadastralDM pesquisarParametroTabelaAtualizacaoCadastroDM(Integer idParametro)
		throws ErroRepositorioException;

	/**
	 * [UC1288] Atualizar Dados Cadastrais do Imóvel pelo Recadastramento
	 * [SB0008] Validar Cliente usuario do imovel
	 * 
	 * @author André Miranda
	 * @since 13/10/2014
	 * 
	 * @param idImovelAtualizacaoCadastral
	 * @return
	 * @throws ErroRepositorioException
	 */
	public String obterCpfCnpjClienteUsuarioAtualizacaoCadastralDM(Integer idImovelAtualizacaoCadastral)
			throws ErroRepositorioException;

	/**
	 * Pesquisar dados do Retorno Atualização Cadastral
	 * 
	 * @author André Miranda
	 * @since 13/10/2014
	 * 
	 * @return RetornoAtualizacaoCadastral
	 * @exception ErroRepositorioException
	 */
	public Collection<RetornoAtualizacaoCadastralDM> pesquisarRetornoAtualizacaoCadastralDM(Integer idImovelAtualizacaoCadastral)
		throws ErroRepositorioException;

	/**
	 * Pesquisar dados do Cliente Atualização Cadastral
	 * 
	 * @return ClienteAtualizacaoCadastral
	 * 
	 * @author André Miranda
     * @date 13/10/2014
	 * @exception ErroRepositorioException
	 */
	public Collection<ClienteAtualizacaoCadastralDM> pesquisarClienteAtualizacaoCadastralDM(Integer idImovel,
			Integer clienteRelacaoTipo ) throws ErroRepositorioException;

	/**
	 * [UC1290] Inserir ou Atualizar Imóvel Atualização Cadastral
	 * [SB0003] Validar Atributo Economias
	 * 
	 * @author André Miranda
	 * @since 13/10/2014
	 * 
	 * @param idImovelAtlzCad
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Short obterQuantidadeEconomiaAtualizacaoCadastralDM(Integer idImovelAtlzCad) throws ErroRepositorioException;

	/**
	 * [UC1290] Inserir ou Atualizar Imóvel Atualização Cadastral
	 * [SB0001] Validar Atributo Categoria
	 * 
	 * @author André Miranda
	 * @since 14/10/2014
	 * 
	 * @param idImovelAtlzCad
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> obterCategoriaAtualizacaoCadastralDM(Integer idImovelAtlzCad) throws ErroRepositorioException;

	/**
	 * [UC1290] Inserir ou Atualizar Imóvel Atualização Cadastral
	 * [SB0001] Validar Atributo Categoria
	 * 
	 * @author André Miranda
	 * @since 14/10/2014
	 * 
	 * @param idImovelAtlzCad
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> obterSubcategoriaAtualizacaoCadastral(Integer idImovelAtlzCad) throws ErroRepositorioException;

	/**
	 * Pesquisa o setor comercial passando como parametro o id da localidade e o
	 * codigo do setor
	 * 
	 * @param idLocalidade
	 * @param codigoSetor
	 * @return
	 * @throws ErroRepositorioException
	 */
	public SetorComercial pesquisarSetorComercial(int idLocalidade,	Integer codigoSetor) throws ErroRepositorioException;
	
	/**
	 * [UC1288] Atualizar Dados Cadastrais do Imóvel pelo Recadastramento
	 * [SB0010] - Verificar Alteração do Cliente por Usuário da COMPESA
	 * 
	 * @author Arthur Carvalho
	 * 
	 * @param idImovel
	 * @param dataEnvio
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean  verificarRegistroAtendimentoAtualizacaoCadastral(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC1290] Inserir ou Atualizar Imóvel Atualização Cadastral
	 * [SB0020] Calcular Valor de Água e/ou Esgoto
	 * 
	 * @author Vivianne Sousa
	 * @date 20/10/2014
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection obterQtdeEconomiaPorCategoriaAtlzCadastral( 
			Integer idImovelAtualizacaoCadastralDM) throws ErroRepositorioException;
	
	/**
	 * [UC1290] Inserir ou Atualizar Imóvel Atualização Cadastral
	 * [SB0020] Calcular Valor de Água e/ou Esgoto
	 * 
	 * @author Vivianne Sousa
	 * @date 20/10/2014
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection obterQtdeEconomiaPorSubcategoriaAtlzCadastral( 
			Integer idImovelAtualizacaoCadastralDM) throws ErroRepositorioException;
	
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
	public Collection<Usuario> pesquisarUsuarioAtualizacaoCadastral(Integer idEmpresa) 
			throws ErroRepositorioException;
	
	/**
	 * 
	 * @return ImovelSubcategoriaAtualizacaoCadastral
	 * 
	 * @author Arthur Carvalho
     * @date 12/03/2012
	 * @exception ErroRepositorioException
	 */
	public Collection<ImovelSubcategoriaAtualizacaoCadastralDM> pesquisarImovelSubcategoriaAtualizacaoCadastralDM(Integer idImovelAtualizacaoCadastral)
		throws ErroRepositorioException;
	
	/**
	 * [UC1297] Atualizar Dados Cadastrais para Imóveis Inconsistentes
	 * [SB0008] Identificar atributo
	 * 
	 * @author Bruno Sá Barreto
	 * @date 20/10/2014
	 *
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeEconomiasImovelAtualizacaoCadastralDM(Integer idImovelAtualizacaoCadastral)
			throws ErroRepositorioException;
	
	/**
	 * @author Jonathan Marcos
	 * @since 11/11/2014
	 * @param idImac
	 * @param codigoSetor
	 * @param idLocalidade
	 * @return Short
	 * @throws ErroRepositorioException
	 */
	public Short pesquisarIndicadorRedeAguaAtualizacaoCadastralDM(Integer idImac,Integer codigoSetor,
			Integer idLocalidade) throws ErroRepositorioException;

	/**
	 * [UC1290] Inserir ou Atualizar Imóvel Atualização Cadastral
	 * [SB0004] Atualizar Categoria/Subcategoria e Economia do Imóvel
	 * 
	 * Este método altera a subcategoria da chave composta do relacionamento de imovelsubcategoria
	 * 
	 * @author Bruno Sá Barreto
	 * @date 12/11/2014
	 *
	 * @throws ErroRepositorioException
	 */
	public void atualizarChaveSubcategoriaImovel(ImovelSubcategoriaPK comp_id,
			Integer idSubcategoriaAtlzCad) throws ErroRepositorioException;
	
	/**
	 * @author Jonathan Marcos
	 * @since 11/11/2014
	 * @param idImac
	 * @param codigoSetor
	 * @param idLocalidade
	 * @return Short
	 * @throws ErroRepositorioException
	 */
	public Short pesquisarIndicadorRedeEsgotoAtualizacaoCadastralDM(Integer idImac,Integer codigoSetor,
			Integer idLocalidade) throws ErroRepositorioException;
	
	/**
	 * [UC1443] - Gerar Relatorio de Novos Logradouros
	 * 
	 * @author Anderson Cabral, Bruno Sá Barreto
	 * @since 15/03/2013
	 * 
	 * @param idEmpresa
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]>  pesquisarLogradouroAtlzCad(String idEmpresa, String idLocalidade) throws ErroRepositorioException;
	
	/**
	 * [UC1443] - Gerar Relatorio de Novos Logradouros
	 * 
	 * @author Anderson Cabral
	 * @author Bruno Sá Barreto
	 * @since 15/03/2013
	 * 
	 * @param idLogradouro
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]>  pesquisarLogradouroBairroAtlzCad(String idLogradouro) throws ErroRepositorioException;
	
	/**
	 * [UC1443] - Gerar Relatorio de Novos Logradouros
	 * 
	 * @author Anderson Cabral
	 * @author Bruno Sá Barreto
	 * @since 15/03/2013
	 * 
	 * @param idLogradouro
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]>  pesquisarLogradouroCepAtlzCad(String idLogradouro) throws ErroRepositorioException;
	
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
	public ArrayList<Integer> pesquisarImovelAtualizacaoCadastralPorLogradouro(Integer idLogradouro ) throws ErroRepositorioException;
	
	/**
	 * [UC1443] - Gerar Relatorio de Novos Logradouros
	 * 
	 * @author  Bruno Sá Barreto
	 * @since 15/03/2013
	 * 
	 * @param idEmpresa
	 * @param idLocalidade
	 * @param idsSelecionados
	 * @return  Collection<Object[]>
	 * @throws ControladorException
	 */
	public Collection<Object[]>  pesquisarLogradouroAtlzCad(String idEmpresa, String idLocalidade, String idsSelecionados) throws ErroRepositorioException;

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
	public boolean verificaSeExisteAssociacaoCepLogradouro(Integer codigo) throws ErroRepositorioException;
	
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
			throws ErroRepositorioException;
	
	
	
	/**
	 * [UC1312] Gerar Resumo da Situação dos imóveis por cadastrador/analista
	 * 
	 * @author Mariana Victor
	 * @date 12/04/2012
	 * 
	 * @return Date
	 * @throws ErroRepositorioException
	 */
	
	public Collection<Object []> pesquisarResumoSituacaoImoveisPorCadastradorAnalista(DadosResumoMovimentoAtualizacaoCadastralHelper helper)
			throws ErroRepositorioException;
		
	
	/**
	 * [UC 1313] Gerar Resumo Quantitativo de Mensagens Pendentes por Cadastrador
	 * 
	 * @author Cesar Medeiros
	 * @since 09/10/2014
	 * 
	 * @param Helper
	 * @return Collection<>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarResumoQuantitativoMensagensPendentesCadastrador(
		DadosResumoMovimentoAtualizacaoCadastralHelper helper) throws ErroRepositorioException;
	
	
	/**
	 * [UC 1314] Gerar Resumo Quantitativo de Mensagens Pendentes
	 * 
	 * @author Cesar Medeiros
	 * @since 09/12/2014
	 * 
	 * @param Helper
	 * @return Collection<>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarResumoQuantitativoMensagensPendentes(
		DadosResumoMovimentoAtualizacaoCadastralHelper helper) throws ErroRepositorioException;
	
	
	/**
	 * Recupera quantidade de imoveis enviados para atualziação cadastral
	 * 
	 * @author Cesar Medeiros
	 * @date 09/12/2014
	 */
	public Integer recuperaQtdeImoveisPorLocalidadeEEmpresa(
			Integer idLocalidade, Integer idEmpresa)throws ErroRepositorioException;
	
	
	/**
	 * Recupera quantidade de imoveis com algum tipo de inconsistencia enviados para atualziacao cadastral
	 * 
	 * @author Cesar Medeiros
	 * @date 09/12/2014
	 */
	public Integer recuperaQtdeImoveisComInconsistenciasPorLocalidadeEEmpresa(
			Integer idLocalidade, Integer idEmpresa)
			throws ErroRepositorioException;
	
	
	
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
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarSetorComercialMapaKMZ(Integer idLocalidade,Integer codigoSetorComercial)
			throws ErroRepositorioException;
	
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
	 * @param codigoSetorComercial
	 */
	public boolean verificarExistenciaMapaSetorComercial(Integer idLocalidade,Integer codigoSetorComercial)
			throws ErroRepositorioException;
	
	/** [UC1393] - Processar Requisição do Dispositivo Móvel Atualização Cadastral 
	 *  retorna o arquivo de mapa
	 *  do setor comercial caso ele
	 *  exista.
	 *   
	 * @author Bruno Sá Barreto
	 * @since 11/12/2014
	 *
	 */
	public byte[] pesquisarArquivoMapSetorComercial(Integer idLocalidade,Integer codigoSetorComercial) throws ErroRepositorioException;
	/**
	 * [UC 1311] Gerar Resumo da Posição de Atualização Cadastral
	 * 
	 * @author Cesar Medeiros
	 * @since 15/12/2014
	 * 
	 * @param Helper
	 * @return Collection<Object []>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object []> pesquisarResumoPosicaoAtualizacaoCadastral(DadosResumoMovimentoAtualizacaoCadastralHelper helper)
		throws ErroRepositorioException;
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
			throws ErroRepositorioException;
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
			throws ErroRepositorioException;
	
	/**
	 * [UC1669] Atualizar Dados nas Tabelas Resumos Grenciais Faturamento
	 * @author Fábio Aguiar
	 * @throws ControladorException 
	 * @data 04/02/2015
	 * 
	 * @throws ControladorException 
	 * */
	public void gerarResumoCadastroAtualizaDados()  throws ErroRepositorioException, SQLException;
	
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
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarRelatorioImoveisInconsistentesMovimento(Integer idMovimento, Date dataMovimento,
			String idLocalidade, String codigoSetorComercial, String numeroQuadras, String idCadastrador, String indicadorSituacaoMovimento,
			String tipoInconsistencia)throws ErroRepositorioException;
	
}
