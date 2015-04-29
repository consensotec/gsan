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
package gsan.atendimentopublico.ordemservico;

import gsan.atendimentopublico.ordemservico.bean.ArquivoTxtOSVisitaHelper;
import gsan.atendimentopublico.ordemservico.bean.DadosAtualizacaoOSParaInstalacaoHidrometroHelper;
import gsan.atendimentopublico.ordemservico.bean.OSPavimentoHelper;
import gsan.atendimentopublico.ordemservico.bean.OrdemRepavimentacaoProcessoAceiteHelper;
import gsan.atendimentopublico.ordemservico.bean.PesquisarFiscalizarOSAcompanhamentoHelper;
import gsan.atendimentopublico.ordemservico.bean.PesquisarOrdemServicoHelper;
import gsan.atendimentopublico.ordemservico.bean.RegistroTipo14Helper;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.Quadra;
import gsan.cadastro.localidade.SetorComercial;
import gsan.cadastro.unidade.UnidadeOrganizacional;
import gsan.gui.relatorio.atendimentopublico.FiltrarOrdensServicosComandoOrdemSeletivaHelper;
import gsan.gui.relatorio.atendimentopublico.FiltrarRelatorioOSSituacaoHelper;
import gsan.micromedicao.Leiturista;
import gsan.micromedicao.leitura.LeituraAnormalidade;
import gsan.operacional.DistritoOperacional;
import gsan.relatorio.atendimentopublico.bean.ImovelEmissaoOrdensSeletivasHelper;
import gsan.relatorio.atendimentopublico.ordemservico.FiltrarBoletimCustoPavimentoHelper;
import gsan.util.ControladorException;
import gsan.util.ErroRepositorioException;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface IRepositorioOrdemServico {

	/**
	 * [UC0450] Pesquisar Ordem de Servico
	 * 
	 * [SB001] Selecionar Ordem de Servico por Situa��o [SB002] Selecionar Ordem
	 * de Servico por Situa��o da Programa��o [SB003] Selecionar Ordem de
	 * Servico por Matricula do Imovel [SB004] Selecionar Ordem de Servico por
	 * Codigo do Cliente [SB005] Selecionar Ordem de Servico por Unidade
	 * Superior [SB006] Selecionar Ordem de Servico por Munic�pio [SB007]
	 * Selecionar Ordem de Servico por Bairro [SB008] Selecionar Ordem de
	 * Servico por Bairro Logradouro
	 * 
	 * @author Rafael Pinto
	 * @date 18/08/2006
	 * 
	 * @param PesquisarOrdemServicoHelper
	 * 
	 * @return Collection<OrdemServico>
	 * @throws ControladorException
	 */
	public Collection<OrdemServico> pesquisarOrdemServico(
			PesquisarOrdemServicoHelper filtro) throws ErroRepositorioException;

	/**
	 * [UC0454] Obter Descri��o da situa��o da OS
	 * 
	 * Verificar a situa��o(ORSE_CDSITUACAO) da ordem de servi�o
	 * 
	 * 
	 * @author Leonardo Regis
	 * @date 11/08/2006
	 * 
	 * 
	 * @param idOS
	 * @throws ControladorException
	 */
	public Short verificaSituacaoOS(Integer idOrdemServico)
			throws ErroRepositorioException;

	/**
	 * [UC0441] Consultar Dados da Ordem de Servi�o
	 * 
	 * @author Leonardo Regis
	 * @date 15/08/2006
	 * 
	 * @param idOrdemServico
	 * @exception ErroRepositorioExceptions
	 */
	public OrdemServico consultarDadosOS(Integer idOrdemServico)
			throws ErroRepositorioException;

	/**
	 * [UC0427] Tramitar Registro Atendimento
	 * 
	 * Verifica Exist�ncia de Ordem de Servi�o Programada
	 * 
	 * @author Leonardo Regis
	 * @date 19/08/2006
	 * 
	 * @param idOS
	 * @return boolean
	 * @throws ErroRepositorioException
	 */
	public boolean verificarExistenciaOSProgramada(Integer idOS)
			throws ErroRepositorioException;

	/**
	 * [UC0450] Pesquisar Ordem Servi�o
	 * 
	 * Caso exista registro de atendimento abertos para a unidade de geracao
	 * informada (existe ocorr�ncia na tabela ORDEM_SERVICO com
	 * ORDEM_SERVICO_UNIDADE=Id da Unidade de Atendimento e ATTP_ID=1 -
	 * ABRIR/REGISTRAR)
	 * 
	 * @author Rafael Pinto
	 * @date 19/08/2006
	 * 
	 * @param idUnidadeGeracao
	 * @return Collection<Integer>
	 * @throws ControladorException
	 */
	public Collection<Integer> recuperaOSPorUnidadeGeracao(
			Integer idUnidadeGeracao) throws ErroRepositorioException;

	/**
	 * [UC0450] Pesquisar Ordem Servi�o
	 * 
	 * Caso exista registro de atendimento abertos para a unidade de geracao
	 * informada (existe ocorr�ncia na tabela ORDEM_SERVICO com
	 * ORDEM_SERVICO_UNIDADE=Id da Unidade de Atendimento e ATTP_ID=1 -
	 * ABRIR/REGISTRAR)
	 * 
	 * @author Rafael Pinto
	 * @date 19/08/2006
	 * 
	 * @param idUnidadeGeracao
	 * @return Collection<Integer>
	 * @throws ControladorException
	 */
	public Collection<Integer> recuperaOSPorUnidadeAtual(Integer idUnidadeAtual)
			throws ErroRepositorioException;

	/**
	 * [UC0450] Pesquisar Ordem Servi�o
	 * 
	 * Seleciona ordem de servi�o por codigo do cliente atraves do RASolicitante
	 * 
	 * @author Rafael Pinto
	 * @date 19/08/2006
	 * 
	 * @param codigoCliente
	 * @return Collection<Integer>
	 * @throws ControladorException
	 */
	public Collection<Integer> recuperaOSPorCodigoClienteRASolicitante(
			Integer codigoCliente) throws ErroRepositorioException;

	/**
	 * [UC0450] Pesquisar Ordem Servi�o
	 * 
	 * Seleciona ordem de servi�o por codigo do cliente atraves do cliente
	 * imovel
	 * 
	 * @author Rafael Pinto
	 * @date 19/08/2006
	 * 
	 * @param codigoCliente
	 * @return Collection<Integer>
	 * @throws ControladorException
	 */
	public Collection<Integer> recuperaOSPorCodigoCliente(Integer codigoCliente, String origemOrdemServico)
			throws ErroRepositorioException;

	/**
	 * [UC0450] Pesquisar Ordem Servi�o
	 * 
	 * Seleciona ordem de servi�o por codigo do cliente atraves do documento
	 * cobran�a
	 * 
	 * @author Rafael Pinto
	 * @date 19/08/2006
	 * 
	 * @param codigoCliente
	 * @return Collection<Integer>
	 * @throws ControladorException
	 */
	public Collection<Integer> recuperaOSPorCodigoClienteCobrancaDocumento(
			Integer codigoCliente) throws ErroRepositorioException;

	/**
	 * Pesquisar Atividades
	 * 
	 * Seleciona os atividades no array de servico tipo.
	 * 
	 * @author Leandro Cavalcanti
	 * @date 19/08/2006
	 * 
	 * @param Collection
	 *            <Integer>
	 * @return Collection<Integer>
	 * @throws ControladorException
	 */
	public Collection<Integer> recuperarAtividadesServicoTipo(
			Collection<Integer> atividades) throws ErroRepositorioException;

	/**
	 * Pesquisar Materiais
	 * 
	 * Seleciona os materiais no array de servico tipo.
	 * 
	 * @author Leandro Cavalcanti
	 * @date 19/08/2006
	 * 
	 * @param Collection
	 *            <Integer>
	 * @return Collection<Integer>
	 * @throws ControladorException
	 */
	public Collection<Integer> recuperarMaterialServicoTipo(
			Collection<Integer> materiais) throws ErroRepositorioException;

	/**
	 * [UC0413] Pesquisar Tipo de Servi�o
	 * 
	 * select a.svtp_id from ATENDIMENTOPUBLICO.SERVICO_TIPO A,
	 * ATENDIMENTOPUBLICO.SERVICO_TIPO_ATIVIDADE B,
	 * ATENDIMENTOPUBLICO.SERVICO_TIPO_MATERIAL C WHERE A.SVTP_DSSERVICOTIPO
	 * LIKE '%DESC%' AND A.SVTP_DSABREVIADO LIKE '%DESC%' AND (A.SVTP_VLSERVICO >=
	 * 000000 AND A.SVTP_VLSERVICO <= 99999) AND A.SVTP_ICPAVIMENTO = 1 OU 2 and
	 * A.SVTP_ICATUALIZACOMERCIAL = 1 OU 2 AND A.SVTP_ICTERCEIRIZADO = 1 OU 2
	 * AND A.SVTP_CDSERVICOTIPO = ("O" OR "C") AND (A.SVTP_NNTEMPOMEDIOEXECUCAO >=
	 * 0000 AND A.SVTP_NNTEMPOMEDIOEXECUCAO <= 9999) AND DBTP_ID = ID INFORMADO
	 * AND AND CRTP_ID = ID INFORMADO AND STSG_ID = ID INFORMADO AND STRF_ID =
	 * ID INFORMADO AND STPR_ID = ID INFORMADO AND A.SVTP_ID = B.SVTP_ID AND
	 * B.ATIV_ID IN (ID's INFORMADOS) AND A.SVTP_ID = C.SVTP_ID AND C.MATE_ID IN
	 * (ID's INFORMADOS)
	 * 
	 * @author Leandro Cavalcanti
	 * @date 17/08/2006
	 * 
	 */
	public Collection<ServicoTipo> filtrarST(ServicoTipo servicoTipo,
			Collection colecaoAtividades, Collection colecaoMaterial,
			String valorServicoInicial, String valorServicoFinal,
			String tempoMedioExecucaoInicial, String tempoMedioExecucaoFinal,
			String tipoPesquisa, String tipoPesquisaAbreviada,
			Integer numeroPaginasPesquisa) throws ErroRepositorioException;

	/**
	 * [UC0413] Pesquisar Tipo de Servi�o
	 * 
	 * Faz o count para saber q quantidade total de registros retornados
	 * 
	 * @author Leandro Cavalcanti
	 * @date 17/08/2006
	 * 
	 */
	public Integer filtrarSTCount(ServicoTipo servicoTipo,
			Collection colecaoAtividades, Collection colecaoMaterial,
			String valorServicoInicial, String valorServicoFinal,
			String tempoMedioExecucaoInicial, String tempoMedioExecucaoFinal,
			String tipoPesquisa, String tipoPesquisaAbreviada)
			throws ErroRepositorioException;

	/**
	 * [UC413]- Pesquisar Tipo de Servi�o
	 * 
	 * Pesquisar o Objeto servicoTipo referente ao idTiposervico recebido na
	 * descri��o da pesquisa, onde o mesmo sera detalhado.
	 * 
	 * @author Leandro Cavalcanti
	 * @date 23/08/2006
	 * 
	 * @param idImovel
	 * @param idSolicitacaoTipoEspecificacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarServicoTipo(Integer idTipoServico)
			throws ErroRepositorioException;

	/**
	 * Pesquisar Materiais pelo idServicoTipo na tabela de ServicoTipoMaterial
	 * 
	 * Recupera os materiais do servico tipo material
	 * 
	 * @author Leandro Cavalcanti
	 * @date 24/08/2006
	 * 
	 * @param Integer
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Collection recuperarMaterialServicoTipoConsulta(
			Integer idServicoTipoMaterial) throws ErroRepositorioException;

	/**
	 * Pesquisar Atividades pelo idServicoTipo na tabela de ServicoTipoAtividade
	 * 
	 * Recupera os Atividades do servico tipo Atividade
	 * 
	 * @author Leandro Cavalcanti
	 * @date 24/08/2006
	 * 
	 * @param Integer
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Collection recuperarAtividadeServicoTipoConsulta(
			Integer idServicoTipoMaterial) throws ErroRepositorioException;

	/**
	 * [UC0367] Atualizar Dados da Liga��o Agua
	 * 
	 * Consulta a ordem de servico pelo id
	 * 
	 * @author Rafael Pinto
	 * @date 24/08/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public OrdemServico recuperaOSPorId(Integer idOS)
			throws ErroRepositorioException;

	/**
	 * 
	 * [UC0430] - Gerar Ordem de Servi�o
	 * 
	 * @author lms
	 * @date 14/08/2006
	 */
	public boolean existeOrdemServicoDiferenteEncerrado(
			Integer idregistroAtendimento) throws ErroRepositorioException;

	/**
	 * 
	 * [UC0430] - Gerar Ordem de Servi�o
	 * 
	 * @author lms
	 * @date 14/08/2006
	 */
	public OrdemServico pesquisarOrdemServicoDiferenteEncerrado(
			Integer idregistroAtendimento, Integer idServicoTipo)
			throws ErroRepositorioException;

	/**
	 * [UC0430] Gerar Ordem de Servi�o
	 * 
	 * @author lms
	 * @date 17/08/2006
	 */
	public ServicoTipo pesquisarSevicoTipo(Integer id)
			throws ErroRepositorioException;

	/**
	 * [UC0471] Obter Dados da Equipe
	 * 
	 * @author Raphael Rossiter
	 * @date 01/09/2006
	 * 
	 * @return idQuipe
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarEquipe(Integer idEquipe)
			throws ErroRepositorioException;

	/**
	 * [UC0471] Obter Dados da Equipe
	 * 
	 * @author Raphael Rossiter
	 * @date 01/09/2006
	 * 
	 * @return idQuipe
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarEquipeComponentes(Integer idEquipe)
			throws ErroRepositorioException;

	/**
	 * [UC0456] Elaborar Roteiro de Programa��o de Ordens de Servi�o
	 * 
	 * @author Rafael Pinto
	 * @date 04/09/2006
	 */
	public Collection<ServicoTipo> pesquisarServicoTipoPorUnidade(
			Integer unidadeLotacao) throws ErroRepositorioException;

	/**
	 * [UC0456] Elaborar Roteiro de Programa��o de Ordens de Servi�o
	 * 
	 * @author Rafael Pinto
	 * @date 04/09/2006
	 */
	public Collection<ServicoTipo> pesquisarServicoTipoPorRA(
			Collection<Integer> idsRa) throws ErroRepositorioException;

	/**
	 * [UC0456] Elaborar Roteiro de Programa��o de Ordens de Servi�o
	 * 
	 * @author Rafael Pinto
	 * @date 04/09/2006
	 */
	public Collection<ServicoPerfilTipo> pesquisarServicoPerfilTipoPorUnidade(
			Integer unidadeLotacao) throws ErroRepositorioException;

	/**
	 * [UC0456] Elaborar Roteiro de Programa��o de Ordens de Servi�o
	 * 
	 * @author Rafael Pinto
	 * @date 04/09/2006
	 */
	public Collection<Localidade> pesquisarLocalidadePorUnidade(Integer unidade)
			throws ErroRepositorioException;

	/**
	 * [UC0456] Elaborar Roteiro de Programa��o de Ordens de Servi�o
	 * 
	 * @author Rafael Pinto
	 * @date 04/09/2006
	 */
	public Collection<SetorComercial> pesquisarSetorComercialPorUnidade(
			Integer unidade) throws ErroRepositorioException;

	/**
	 * [UC0456] Elaborar Roteiro de Programa��o de Ordens de Servi�o
	 * 
	 * @author Rafael Pinto
	 * @date 04/09/2006
	 */
	public Collection<SetorComercial> pesquisarSetorComercialPorRA(
			Collection<Integer> idsRa) throws ErroRepositorioException;

	/**
	 * [UC0456] Elaborar Roteiro de Programa��o de Ordens de Servi�o
	 *
	 * @author Rafael Pinto, Raphael Rossiter
	 * @date 04/09/2006, 22/05/2009
	 *
	 * @param idsRa
	 * @return Collection<DistritoOperacional>
	 * @throws ErroRepositorioException
	 */
	public Collection<DistritoOperacional> pesquisarDistritoOperacionalPorRAPelaQuadra(
			Collection<Integer> idsRa) throws ErroRepositorioException ;
	
	/**
	 * [UC0456] Elaborar Roteiro de Programa��o de Ordens de Servi�o
	 *
	 * @author Rafael Pinto, Raphael Rossiter
	 * @date 04/09/2006, 22/05/2009
	 *
	 * @param idsRa
	 * @return Collection<DistritoOperacional>
	 * @throws ErroRepositorioException
	 */
	public Collection<DistritoOperacional> pesquisarDistritoOperacionalPorRAPelaQuadraFace(
			Collection<Integer> idsRa) throws ErroRepositorioException ;

	/**
	 * [UC0456] Elaborar Roteiro de Programa��o de Ordens de Servi�o
	 *
	 * @author Rafael Pinto, Vivianne Sousa, Raphael Rossiter
	 * @date 04/09/2006, 13/06/2008, 22/05/2009
	 *
	 * @param unidade
	 * @return Collection<DistritoOperacional>
	 * @throws ErroRepositorioException
	 */

	public Collection<DistritoOperacional> pesquisarDistritoOperacionalPorUnidade(
			Integer unidade) throws ErroRepositorioException;

	/**
	 * [UC0456] Elaborar Roteiro de Programa��o de Ordens de Servi�o
	 * 
	 * @author Rafael Pinto
	 * @date 04/09/2006
	 */
	public Collection<Localidade> pesquisarLocalidadePorRA(
			Collection<Integer> idsRa) throws ErroRepositorioException;

	/**
	 * [UC0465] Filtrar Ordem Servi�o
	 * 
	 * Recupera OS programadas
	 * 
	 * @author Leonardo Regis
	 * @date 05/09/2006
	 * 
	 * @param situacaoProgramacao
	 * @return Collection<Integer>
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> recuperaOSPorProgramacao(
			short situacaoProgramacao) throws ErroRepositorioException;

	/**
	 * [UC0450] Pesquisar Ordem de Servico
	 * 
	 * [SB003] Selecionar Ordem de Servico por Criterio de Sele��o
	 * 
	 * @author Rafael Pinto
	 * @date 07/09/2006
	 * 
	 * @param PesquisarOrdemServicoHelper
	 * 
	 * @return Collection<OrdemServico>
	 * @throws ControladorException
	 */
	public Collection<OrdemServico> pesquisarOrdemServicoElaborarProgramacao(
			PesquisarOrdemServicoHelper filtro) throws ErroRepositorioException;

	/**
	 * [UC0465] Filtrar Ordem Servi�o
	 * 
	 * Recupera OS por Data de Programa��o
	 * 
	 * @author Leonardo Regis
	 * @date 05/09/2006
	 * 
	 * @param dataProgramacaoInicial
	 * @param dataProgramacaoFinal
	 * @return Collection<Integer>
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> recuperaOSPorDataProgramacao(
			Date dataProgramacaoInicial, Date dataProgramacaoFinal)
			throws ErroRepositorioException;

	/**
	 * [UC0462] Obter Dados das Atividades da OS
	 * 
	 * obter OsAtividadeMaterialExecucao por OS
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param idOS
	 * @return Collection<OsAtividadeMaterialExecucao>
	 * @throws ErroRepositorioException
	 */
	public Collection<OsAtividadeMaterialExecucao> obterOsAtividadeMaterialExecucaoPorOS(
			Integer idOS) throws ErroRepositorioException;

	/**
	 * [UC0462] Obter Dados das Atividades da OS
	 * 
	 * obter OsAtividadePeriodoExecucao por OS
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param idOS
	 * @return Collection<OsExecucaoEquipe>
	 * @throws ErroRepositorioException
	 */
	public Collection<OsExecucaoEquipe> obterOsExecucaoEquipePorOS(Integer idOS)
			throws ErroRepositorioException;

	/**
	 * [UC0456] Elaborar Roteiro de Programa��o de Ordens de Servi�o
	 * 
	 * @author Rafael Pinto
	 * @date 11/09/2006
	 * 
	 * @param dataRoteiro
	 * @return collection
	 * @throws ErroRepositorioException
	 */
	public Collection<OrdemServicoProgramacao> recuperaOSProgramacaoPorDataRoteiro(
			Date dataRoteiro) throws ErroRepositorioException;

	/**
	 * [UC0462] Obter Dados das Atividades da OS
	 * 
	 * obter OsAtividadeMaterialExecucao por OS
	 * 
	 * @author Leonardo Regis
	 * @date 14/09/2006
	 * 
	 * @param idOS
	 * @param idAtividade
	 * @return Collection<OsAtividadeMaterialExecucao>
	 * @throws ErroRepositorioException
	 */
	public Collection<OsAtividadeMaterialExecucao> obterOsAtividadeMaterialExecucaoPorOS(
			Integer idOS, Integer idAtividade) throws ErroRepositorioException;

	/**
	 * [UC0462] Obter Dados das Atividades da OS
	 * 
	 * obter OsAtividadePeriodoExecucao por OS
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param idOS
	 * @param idAtividade
	 * @return OsExecucaoEquipe
	 * @throws ErroRepositorioException
	 */
	public Collection<OsExecucaoEquipe> obterOsExecucaoEquipePorOS(
			Integer idOS, Integer idAtividade) throws ErroRepositorioException;

	/**
	 * [UC0460] Obter Carga de Trabalho da Equipe
	 * 
	 * obter programada��es ativas por os e situacao
	 * 
	 * @author Leonardo Regis
	 * @date 14/09/2006
	 * 
	 * @param idOS
	 * @return Collection<OrdemServicoProgramacao>
	 * @throws ErroRepositorioException
	 */
	public Collection<OrdemServicoProgramacao> obterProgramacoesAtivasPorOs(
			Integer idOS) throws ErroRepositorioException;

	/**
	 * [UC0460] Obter Carga de Trabalho da Equipe
	 * 
	 * obter o somat�rio de horas para a OS informada e para o dia do roteiro
	 * solicitado
	 * 
	 * @author Leonardo Regis
	 * @date 15/09/2006
	 * 
	 * @param idEquipe
	 * @param dataRoteiro
	 * @return OsExecucaoEquipe
	 * @throws ErroRepositorioException
	 */
	public Collection<OsExecucaoEquipe> obterOsExecucaoEquipePorEquipe(
			Integer idEquipe, Date dataRoteiro) throws ErroRepositorioException;

	/**
	 * [UC0457] Encerrar Ordem de Servi�o
	 * 
	 * @author S�vio Luiz
	 * @date 18/09/2006
	 */
	public Integer pesquisarRAOrdemServico(Integer numeroOS)
			throws ErroRepositorioException;

	/**
	 * pesquisa se a OS tenha uma prgrama��o ativa com a data menor ou igual a
	 * data corrente e n�o tenha recebido a data de encerramento
	 * 
	 * [UC0457] Encerra Ordem de Servi�o
	 * 
	 * [FS0006] - Verificar Origem do Encerramento da Ordem de Servi�o
	 * 
	 * @author S�vio Luiz
	 * @date 18/09/2006
	 * 
	 * @throws ControladorException
	 */
	public Integer pesquisarOSProgramacaoAtiva(Integer numeroOS)
			throws ErroRepositorioException;

	/**
	 * pesquisa se a OS tenha uma prgrama��o ativa com a data menor ou igual a
	 * data corrente e n�o tenha recebido a data de encerramento
	 * 
	 * [UC0457] Encerra Ordem de Servi�o
	 * 
	 * [SB0001] - Encerrar sem execu��o
	 * 
	 * @author S�vio Luiz
	 * @date 18/09/2006
	 * 
	 * @throws ControladorException
	 */
	public void atualizarParmsOS(Integer numeroOS,
			Integer idMotivoEncerramento, Date dataEncerramento,
			String parecerEncerramento,String codigoRetornoVistoriaOs) throws ErroRepositorioException;

	/**
	 * [UC0457] Encerrar Ordem de Servi�o
	 * 
	 * [SB0001] - Encerrar sem execu��o
	 * 
	 * @author S�vio Luiz
	 * @date 18/09/2006
	 */
	public Integer pesquisarOSReferencia(Integer numeroOS)
			throws ErroRepositorioException;

	/**
	 * [UC0457] Encerra Ordem de Servi�o
	 * 
	 * [SB0001] - Encerrar sem execu��o
	 * 
	 * @author S�vio Luiz
	 * @date 18/09/2006
	 * 
	 * @throws ControladorException
	 */
	public void atualizarParmsOSReferencia(Integer idOSReferencia, Integer idMotivoEncerramento)
			throws ErroRepositorioException;

	/**
	 * 
	 * [UC0457] Encerra Ordem de Servi�o
	 * 
	 * [FS0004] - Verificar/Excluir/Atualizar Programa��o da Ordem de Servi�o
	 * 
	 * @author S�vio Luiz
	 * @date 18/09/2006
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarRoteiro(Integer numeroOS, Date dataEncerramento)
			throws ErroRepositorioException;

	/**
	 * 
	 * [UC0457] Encerra Ordem de Servi�o
	 * 
	 * [FS0004] - Verificar/Excluir/Atualizar Programa��o da Ordem de Servi�o
	 * 
	 * @author S�vio Luiz
	 * @date 18/09/2006
	 * 
	 * @throws ControladorException
	 */
	public Integer verificarExistenciaProgramacaoRoteiroParaOSProgramacao(
			Integer idOSProgramacao, Integer idRoteiro)
			throws ErroRepositorioException;

	/**
	 * [UC0457] Encerra Ordem de Servi�o
	 * 
	 * [FS0004] - Verificar/Excluir/Atualizar Programa��o da Ordem de Servi�o
	 * 
	 * @author S�vio Luiz
	 * @date 18/09/2006
	 * 
	 * @throws ControladorException
	 */
	public OrdemServicoProgramacao pesquisarOSProgramacaoAtivaComDataEncerramento(
			Integer numeroOS, Date dataEncerramento)
			throws ErroRepositorioException;

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Servi�o
	 * 
	 * @author Raphael Rossiter
	 * @date 15/09/2006
	 * 
	 * @param numeroOS
	 * @return Collection<Atividade>
	 * @throws ErroRepositorioException
	 */
	public Collection<Atividade> obterAtividadesOrdemServico(Integer numeroOS)
			throws ErroRepositorioException;

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Servi�o
	 * 
	 * @author Raphael Rossiter
	 * @date 15/09/2006
	 * 
	 * @param numeroOS
	 * @return Collection<Atividade>
	 * @throws ErroRepositorioException
	 */
	public Collection<Atividade> obterEquipesProgramadas(Integer numeroOS)
			throws ErroRepositorioException;

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Servi�o
	 * 
	 * @author Raphael Rossiter
	 * @date 19/09/2006
	 * 
	 * @param numeroOS
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer verificarOSAssociadaDocumentoCobranca(Integer numeroOS)
			throws ErroRepositorioException;

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Servi�o
	 * 
	 * @author Raphael Rossiter
	 * @date 19/09/2006
	 * 
	 * @param numeroOS
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer verificarOSAssociadaRA(Integer numeroOS)
			throws ErroRepositorioException;

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Servi�o
	 * 
	 * @author Raphael Rossiter
	 * @date 19/09/2006
	 * 
	 * @param numeroOS
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer obterUnidadeDestinoUltimoTramite(Integer numeroOS)
			throws ErroRepositorioException;

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Servi�o
	 * 
	 * @author Raphael Rossiter
	 * @date 19/09/2006
	 * 
	 * @param idUnidade
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection obterEquipesPorUnidade(Integer idUnidade)
			throws ErroRepositorioException;

	/**
	 * [UC0362] Efetuar Instala��o de Hidr�metro
	 * 
	 * @author Leonardo Regis
	 * @date 19/09/2006
	 * 
	 * @param dadosOS
	 * @throws ErroRepositorioException
	 */
	public void atualizarOSParaEfetivacaoInstalacaoHidrometro(
			DadosAtualizacaoOSParaInstalacaoHidrometroHelper dadosOS)
			throws ErroRepositorioException;

	/**
	 * [UC0470] Acompanhar Roteiro de Programa��o de Ordens de Servi�o
	 * 
	 * @author Rafael Pinto
	 * @date 11/09/2006
	 * 
	 * @param dataRoteiro,idUnidadeOrganizacional
	 * @return collection
	 * @throws ErroRepositorioException
	 */
	public Collection<OrdemServicoProgramacao> recuperaOSProgramacaoPorDataRoteiroUnidade(
			Date dataRoteiro, Integer idUnidadeOrganizacional)
			throws ErroRepositorioException;

	/**
	 * [UC0470] Acompanhar Roteiro de Programa��o de Ordens de Servi�o
	 * 
	 * [FS0008] - Verificar possibilidade da inclus�o da ordem de servi�o
	 * 
	 * @author Rafael Pinto
	 * @date 21/09/2006
	 * 
	 * @throws ErroRepositorioException
	 */
	public OrdemServicoProgramacao pesquisarOSProgramacaoAtivaPorOS(Integer idOS)
			throws ErroRepositorioException;

	/**
	 * [UC0470] Acompanhar Roteiro de Programa��o de Ordens de Servi�o
	 * 
	 * @author Rafael Pinto
	 * @date 21/09/2006
	 * 
	 * @throws ErroRepositorioException
	 */
	public OrdemServicoProgramacao pesquisarOSProgramacaoPorId(
			Integer idOrdemServicoProgramacao) throws ErroRepositorioException;

	/**
	 * [UC0470] Acompanhar Roteiro de Programacao de Ordens de Servico
	 * 
	 * @author Rafael Pinto
	 * @date 21/09/2006
	 * 
	 * @throws ErroRepositorioException
	 */
	public OrdemServicoProgramacao pesquisarOSProgramacaoAtivaComDataRoteiroIdEquipe(
			Integer numeroOS, Date dataRoteiro, Integer idEquipe)
			throws ErroRepositorioException;

	/**
	 * [UC0470] Acompanhar Roteiro de Programacao de Ordens de Servico
	 * 
	 * @author Rafael Pinto
	 * @date 21/09/2006
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OrdemServicoProgramacao> pesquisarOSProgramacaoComDataRoteiroIdEquipeDiferenteOS(
			Integer numeroOS, Date dataRoteiro, Integer idEquipe)
			throws ErroRepositorioException;

	/**
	 * [UC0470] Acompanhar Roteiro de Programacao de Ordens de Servico
	 * 
	 * @author Rafael Pinto
	 * @date 21/09/2006
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OrdemServicoProgramacao> pesquisarOSProgramacaoComSequencialMaior(
			Integer numeroOS, Date dataRoteiro, Integer idEquipe,
			short sequencialReferencia) throws ErroRepositorioException;

	/**
	 * [UC0470] Acompanhar Roteiro de Programacao de Ordens de Servico
	 * 
	 * @author Rafael Pinto
	 * @date 21/09/2006
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OrdemServicoProgramacao> pesquisarOSProgramacaoComDataRoteiroIdEquipe(
			Date dataRoteiro, Integer idEquipe) throws ErroRepositorioException;

	/**
	 * [UC0457] Encerrar Ordem de Servi�o
	 * 
	 * @author S�vio Luiz
	 * @date 25/09/2006
	 * 
	 * @param numeroOS
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Object[] recuperarParametrosServicoTipo(Integer numeroOS)
			throws ErroRepositorioException;

	/**
	 * [UC0470] Acompanhar Roteiro de Programa��o de Ordens de Servi�o
	 * 
	 * @author Rafael Pinto
	 * @date 25/09/2006
	 * 
	 * @param idOs,dataRoteiro
	 * @return Equipe
	 * @throws ErroRepositorioException
	 */
	public Collection<Equipe> recuperaEquipeDaOSProgramacaoPorDataRoteiro(
			Integer idOs, Date dataRoteiro) throws ErroRepositorioException;

	/**
	 * Atualiza��o Geral de OS
	 * 
	 * @author Leonardo Regis
	 * @date 25/09/2006
	 * 
	 * @param os
	 * @throws ErroRepositorioException
	 */
	public void atualizaOSGeral(OrdemServico os)
			throws ErroRepositorioException;

	/**
	 * [UC0457] Encerrar Ordem de Servi�o
	 * 
	 * @author S�vio Luiz
	 * @date 25/09/2006
	 * 
	 * @param numeroOS
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarSolicitacoesServicoTipoOS(Integer numeroOS)
			throws ErroRepositorioException;

	/**
	 * [UC0457] Encerrar Ordem de Servi�o
	 * 
	 * @author S�vio Luiz
	 * @date 25/09/2006
	 * 
	 * @param numeroOS
	 * @return Integer
	 * @throws ErroRRepositorioException
	 */
	public Collection pesquisarServicoTipo(Collection idsServicoTipo)
			throws ErroRepositorioException;

	/**
	 * [UC0470] Acompanhar Roteiro de Programa��o de Ordens de Servi�o
	 * 
	 * @author Rafael Pinto
	 * @date 25/09/2006
	 * 
	 * @param idOs,dataRoteiro
	 * @return Equipe
	 * @throws ErroRepositorioException
	 */
	public boolean verificaExitenciaProgramacaoAtivaParaDiasAnteriores(
			Integer idOs, Date dataRoteiro) throws ErroRepositorioException;

	/**
	 * [UC0470] Acompanhar Roteiro de Programa��o de Ordens de Servi�o
	 * 
	 * @author Rafael Pinto
	 * @date 26/09/2006
	 * 
	 * @param idOs,dataRoteiro
	 * @return Equipe
	 * @throws ErroRepositorioException
	 */
	public Collection<OrdemServicoProgramacao> recuperaOrdemServicoProgramacaoPorDataRoteiro(
			Integer idOs, Date dataRoteiro) throws ErroRepositorioException;

	/**
	 * [UC0470] Acompanhar Roteiro de Programa��o de Ordens de Servi�o
	 * 
	 * @author Rafael Pinto
	 * @date 04/09/2006
	 */
	public UnidadeOrganizacional pesquisarUnidadeOrganizacionalPorUnidade(
			Integer idOs, Integer unidadeLotacao)
			throws ErroRepositorioException;

	/**
	 * [UC0457] Encerrar Ordem de Servi�o
	 * 
	 * @author S�vio Luiz
	 * @date 26/09/2006
	 * 
	 * @param idOsReferidaRetornoTipo
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdMotivoEncerramentoOSReferida(
			Integer idOsReferidaRetornoTipo) throws ErroRepositorioException;

	/**
	 * [UC0457] Encerrar Ordem de Servi�o
	 * 
	 * @author S�vio Luiz
	 * @date 26/09/2006
	 * 
	 * @param idOsReferidaRetornoTipo
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Short pesquisarIndDiagnosticoServicoTipoReferencia(
			Integer idOrdemServicoReferencia) throws ErroRepositorioException;

	/**
	 * [UC0457] Encerrar Ordem de Servi�o
	 * 
	 * @author S�vio Luiz
	 * @date 29/09/2006
	 * 
	 * @param idOsReferidaRetornoTipo
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Object[] verificarExistenciaOSEncerrado(Integer idRA,
			Integer idServicoTipo) throws ErroRepositorioException;

	/**
	 * [UC0457] Encerrar Ordem de Servi�o
	 * 
	 * @author S�vio Luiz
	 * @date 29/09/2006
	 * 
	 * @param idOsReferidaRetornoTipo
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer verificarExistenciaServicoTipoReferencia(
			Integer idServicoTipo) throws ErroRepositorioException;

	/**
	 * [UC0457] Encerrar Ordem de Servi�o
	 * 
	 * @author S�vio Luiz
	 * @date 02/10/2006
	 * 
	 * @param idOsReferidaRetornoTipo
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer verificarExistenciaUnidadeTerceirizada(Integer idUsuario)
			throws ErroRepositorioException;

	/**
	 * [UC0457] Encerrar Ordem de Servi�o
	 * 
	 * @author S�vio Luiz
	 * @date 02/10/2006
	 * 
	 * @param idOsReferidaRetornoTipo
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Collection verificarOSparaRA(Integer idOrdemServico, Integer idRA)
			throws ErroRepositorioException;

	/**
	 * Pesquisa os campos da OS que ser�o impressos no relat�rio de Ordem de
	 * Servico
	 * 
	 * @author Rafael Corr�a
	 * @date 17/10/2006
	 * 
	 * @param idOrdemServico
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOSRelatorio(Collection idsOrdemServico)
			throws ErroRepositorioException;

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Servi�o
	 * 
	 * Obt�m as datas de encerramento e gera��o de uma ordem de servi�o
	 * 
	 * @author Raphael Rossiter
	 * @date 18/10/2006
	 * 
	 * @param numeroOS
	 * @return Collection
	 */
	public Collection obterDatasGeracaoEncerramentoOS(Integer numeroOS)
			throws ErroRepositorioException;

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Servi�o
	 * 
	 * @author Raphael Rossiter
	 * @date 15/09/2006
	 * 
	 * @param numeroOS
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection obterMateriaisProgramados(Integer numeroOS)
			throws ErroRepositorioException;

	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Servi�o
	 * 
	 * @author Raphael Rossiter
	 * @date 15/09/2006
	 * 
	 * @param numeroOS,
	 *            idMaterial
	 * @return BigDecimal
	 * @throws ErroRepositorioException
	 */
	public BigDecimal obterQuantidadePadraoMaterial(Integer numeroOS,
			Integer idMaterial) throws ErroRepositorioException;

	/**
	 * [UC0457] Encerrar Ordem de Servi�o
	 * 
	 * Recupera o id do im�vel do registro atendimento
	 * 
	 * @author S�vio Luiz
	 * @date 19/10/2006
	 * 
	 * @param numeroOS
	 * @return Collection
	 */
	public Integer recuperarIdImovelDoRA(Integer idRA)
			throws ErroRepositorioException;

	/**
	 * [UC0457] Encerrar Ordem de Servi�o
	 * 
	 * Recupera o id do im�vel do registro atendimento
	 * 
	 * @author S�vio Luiz
	 * @date 19/10/2006
	 * 
	 * @param numeroOS
	 * @return Collection
	 */
	public Short recuperarSituacaoOSReferida(Integer idOSReferida)
			throws ErroRepositorioException;

	/**
	 * [UC0482] - Obter Endere�o Abreviado da Ocorr�ncia do RA
	 * 
	 * Pesquisa os dados da OS usados para saber de onde ser� recebido o
	 * endere�o abreviado
	 * 
	 * @author Rafael Corr�a
	 * @date 19/10/2006
	 * 
	 * 
	 * @param idOrdemServico
	 * @throws ErroRepositorioException
	 */

	public Object[] obterDadosPesquisaEnderecoAbreviadoOS(Integer idOrdemServico)
			throws ErroRepositorioException;

	/**
	 * [UC0456] Elaborar Roteiro de Programa��o de Ordens de Servi�o
	 * 
	 * @author Rafael Pinto
	 * @date 23/10/2006
	 */
	public Collection<Integer> recuperaRegistroAtendimentoPorMaiorTramiteUnidadeDestino(
			Integer unidadeDestino) throws ErroRepositorioException;

	/**
	 * Imprimir OS
	 * 
	 * Atualiza a data de emiss�o e a de �ltima altera��o de OS quando gerar o
	 * relat�rio
	 * 
	 * @author Rafael Corr�a
	 * @date 26/10/2006
	 * 
	 * 
	 * @param colecaoIdsOrdemServico
	 * @throws ErroRepositorioException
	 */
	public void atualizarOrdemServicoRelatorio(Collection colecaoIdsOrdemServico)
			throws ErroRepositorioException;

	/**
	 * [UC0460] Obter Carga de Trabalho da Equipe
	 * 
	 * obter OsAtividadePeriodoExecucao por OS
	 * 
	 * @author Rafael Pinto
	 * @date 09/09/2006
	 * 
	 * @param idOS
	 * @return OsAtividadePeriodoExecucao
	 * @throws ErroRepositorioException
	 */
	public Collection<OsAtividadePeriodoExecucao> obterOsAtividadePeriodoExecucaoPorOS(
			Integer idOS, Date dataRoteiro) throws ErroRepositorioException;

	/**
	 * [UC0460] Obter Carga de Trabalho da Equipe
	 * 
	 * obter OsAtividadePeriodoExecucao por OS
	 * 
	 * @author Rafael Pinto
	 * @date 09/09/2006
	 * 
	 * @param idEquipe
	 * @param dataRoteiro
	 * @return OsAtividadePeriodoExecucao
	 * @throws ErroRepositorioException
	 */
	public Collection<OsAtividadePeriodoExecucao> obterOsAtividadePeriodoExecucaoPorEquipe(
			Integer idEquipe, Date dataRoteiro) throws ErroRepositorioException;

	/**
	 * [UC0461] - Manter Dados das Atividades da Ordem de Servi�o
	 * 
	 * Pesquisa os dados da OrdemServicoAtividade
	 * 
	 * @author Raphael Rossiter
	 * @date 01/11/2006
	 * 
	 * @param idOrdemServico,
	 *            idAtividade
	 * @throws ErroRepositorioException
	 */

	public Object[] pesquisarOrdemServicoAtividade(Integer numeroOS,
			Integer idAtividade) throws ErroRepositorioException;

	/**
	 * [UC0461] - Manter Dados das Atividades da Ordem de Servi�o
	 * 
	 * Pesquisa os dados da OsAtividadeMaterialExecucao associada �
	 * OrdemServicoAtividade para a data informada
	 * 
	 * @author Raphael Rossiter
	 * @date 01/11/2006
	 * 
	 * @param idOrdemServicoAtividade
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOsAtividadeMaterialExecucao(
			Integer idOrdemServicoAtividade) throws ErroRepositorioException;

	/**
	 * Cria a query de acordo com os par�metros de pesquisa informados pelo
	 * usu�rio
	 * 
	 * [UC0492] - Gerar Relat�rio Acompanhamento de Execu��o de Ordem de Servi�o
	 * 
	 * @author Rafael Corr�a
	 * @date 01/11/06
	 * 
	 * @param idImovel
	 * @param idCliente
	 * @param idTipoRelacao
	 * @param localidadeInicial
	 * @param localidadeFinal
	 * @param idAvisoBancario
	 * @param idArrecadador
	 * @param periodoArrecadacaoInicial
	 * @param periodoArrecadacaoFinal
	 * @param periodoPagamentoInicio
	 * @param periodoPagamentoFim
	 * @param dataPagamentoInicial
	 * @param dataPagamentoFinal
	 * @param idsPagamentosSituacoes
	 * @param idsDebitosTipos
	 * @param idsArrecadacaoForma
	 * @param idsDocumentosTipos
	 * @return Collection
	 */
	public Collection pesquisarOSGerarRelatorioAcompanhamentoExecucao(
			String origemServico, String situacaoOS, String[] idsServicosTipos,
			String idUnidadeAtendimento, String idUnidadeAtual,
			String idUnidadeEncerramento, Date periodoAtendimentoInicial,
			Date periodoAtendimentoFinal, Date periodoEncerramentoInicial,
			Date periodoEncerramentoFinal, String idEquipeProgramacao,
			String idEquipeExecucao, String tipoOrdenacao)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa a equipe principal de uma OS de programa��o atrav�s do id da OS
	 * 
	 * @author Rafael Corr�a
	 * @date 07/11/2006
	 * 
	 * @param idOS
	 * @throws ErroRepositorioException
	 */
	public Equipe pesquisarEquipePrincipalOSProgramacao(Integer idOS)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa a equipe principal de uma OS de execu��o da equipe atrav�s do id
	 * da OS
	 * 
	 * @author Rafael Corr�a
	 * @date 07/11/2006
	 * 
	 * @param idOS
	 * @throws ErroRepositorioException
	 */
	public Equipe pesquisarEquipePrincipalOSExecucaoEquipe(Integer idOS)
			throws ErroRepositorioException;
	
	/**
	 * Cria o count de acordo com os par�metros de pesquisa informados pelo
	 * usu�rio
	 * 
	 * [UC0492] - Gerar Relat�rio Acompanhamento de Execu��o de Ordem de Servi�o
	 * 
	 * @author Rafael Corr�a
	 * @date 01/11/06
	 * 
	 * @param idImovel
	 * @param idCliente
	 * @param idTipoRelacao
	 * @param localidadeInicial
	 * @param localidadeFinal
	 * @param idAvisoBancario
	 * @param idArrecadador
	 * @param periodoArrecadacaoInicial
	 * @param periodoArrecadacaoFinal
	 * @param periodoPagamentoInicio
	 * @param periodoPagamentoFim
	 * @param dataPagamentoInicial
	 * @param dataPagamentoFinal
	 * @param idsPagamentosSituacoes
	 * @param idsDebitosTipos
	 * @param idsArrecadacaoForma
	 * @param idsDocumentosTipos
	 * @return Collection
	 */
	public int pesquisarOSGerarRelatorioAcompanhamentoExecucaoCount(
			String origemServico, String situacaoOS, String[] idsServicosTipos,
			String idUnidadeAtendimento, String idUnidadeAtual,
			String idUnidadeEncerramento, Date periodoAtendimentoInicial,
			Date periodoAtendimentoFinal, Date periodoEncerramentoInicial,
			Date periodoEncerramentoFinal, String idEquipeProgramacao,
			String idEquipeExecucao)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa as equipes de acordo com os par�metros informado pelo usu�rio
	 * 
	 * [UC0370] - Filtrar Equipe
	 * 
	 * @author Rafael Corr�a
	 * @date 01/11/06
	 * 
	 * @param idEquipe
	 * @param nome
	 * @param placa
	 * @param cargaTrabalho
	 * @param idUnidade
	 * @param idFuncionario
	 * @param idPerfilServico
	 * @param indicadorUso
	 * @param numeroPagina
	 * @return Collection
	 */
	public Collection pesquisarEquipes(String idEquipe, String nome,
			String placa, String cargaTrabalho,String codigoDdd,
			String numeroTelefone, String numeroImei, String idUnidade,
			String idFuncionario, String idPerfilServico, String indicadorUso, 
			String tipoPesquisa, Integer numeroPagina,String equipamentosEspeciasId, String cdUsuarioRespExecServico,
			String indicadorProgramacaoAutomatica) throws ErroRepositorioException;
	
	/**
	 * Verifica a quantidade de registros retornados da pesquisa de equipe
	 * 
	 * [UC0370] - Filtrar Equipe
	 * 
	 * @author Rafael Corr�a
	 * @date 01/11/06
	 * 
	 * @param idEquipe
	 * @param nome
	 * @param placa
	 * @param cargaTrabalho
	 * @param idUnidade
	 * @param idFuncionario
	 * @param idPerfilServico
	 * @param indicadorUso
	 * @return int
	 */
	public int pesquisarEquipesCount(String idEquipe, String nome,
			String placa, String cargaTrabalho, String codigoDdd,
			String numeroTelefone, String numeroImei, String idUnidade,
			String idFuncionario, String idPerfilServico, String indicadorUso,
			String tipoPesquisa,String equipamentosEspeciasId, String cdUsuarioRespExecServico,
			String indicadorProgramcaoAutomatica) throws ErroRepositorioException;
	
	/**
	 * Verifica se o servi�o associado  � ordem de servi�o n�o corresponde  a um servi�o de fiscaliza��o de infra��o
	 * 
	 * [UC0488] - Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * @author S�vio Luiz
	 * @date 01/11/06
	 * 
	 * @return Integer
	 */
	public Object[] pesquisarServicoTipoComFiscalizacaoInfracao(Integer idOS)
			throws ErroRepositorioException;
	
	/**
	 * [UC0367] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * Recupera os  par�metros necess�rios da OS
	 * 
	 * @author S�vio Luiz
	 * @date 24/08/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Object[] pesquisarParmsOS(Integer idOS)
			throws ErroRepositorioException;
	
	/**
	 * [UC0367] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * Recupera os par�metros necess�rios da OS
	 * 
	 * @author S�vio Luiz
	 * @date 24/08/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Object[] pesquisarIdFiscalizacaoSituacaoAgua(
			Integer idLigacaoAguaSituacao, Integer idSituacaoEncontrada) throws ErroRepositorioException;
	
	/**
	 * [UC0367] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * Recupera os par�metros necess�rios da OS
	 * 
	 * @author S�vio Luiz
	 * @date 24/08/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Object[] pesquisarIdFiscalizacaoSituacaoEsgoto(
			Integer idLigacaoEsgotoSituacao, Integer idSituacaoEncontrada) throws ErroRepositorioException;
	
	/**
	 * [UC0367] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * [SB0001] - Atualizar Ordem de Servi�o
	 * 
	 * 
	 * @author S�vio Luiz
	 * @date 14/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public void atualizarParmsOrdemFiscalizacao(Integer numeroOS,
			String indicadorDocumentoEntregue,
			Short indicadorAtualizacaoSituacaoLigacaoAgua,
			Short indicadorAtualizacaoSituacaoLigacaoEsgoto, Integer idSituacaoEncontrada)
			throws ErroRepositorioException ;
	
	/**
	 * [UC0367] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * Recupera os par�metros necess�rios da OS
	 * 
	 * @author S�vio Luiz
	 * @date 14/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Object[] pesquisarIdFiscalizacaoSituacaoAguaOS(
			Integer idFiscalizacaoSituacao, Integer idLigacaoAguaSituacao) throws ErroRepositorioException;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * Recupera os par�metros necess�rios da OS
	 * 
	 * @author S�vio Luiz
	 * @date 14/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Object[] pesquisarIdFiscalizacaoSituacaoEsgotoOS(
			Integer idFiscalizacaoSituacao, Integer idLigacaoEsgotoSituacao) throws ErroRepositorioException;
	
	/**
	 * [UC0367] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * Recupera os par�metros necess�rios da OS
	 * 
	 * @author S�vio Luiz
	 * @date 14/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Integer pesquisarHidormetroCapacidadeFiscalizacaoAgua(
			Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * 
	 * @author S�vio Luiz
	 * @date 15/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Integer pesquisarHidormetroCapacidadeFiscalizacaoPoco(
			Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * 
	 * @author S�vio Luiz
	 * @date 15/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Collection pesquisarFiscalizacaoSituacaoServicoACobrar(
			Integer idFiscalizacaoSituacao) throws ErroRepositorioException;

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * 
	 * @author S�vio Luiz
	 * @date 20/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Object[] pesquisarParmsFiscalizacaoSituacaoServicoACobrar(
			Integer idFiscalizacaoSituacao, Integer idDebitoTipo) throws ErroRepositorioException;
	
	/**
	 * [UC0450] Pesquisar Ordem de Servico verifica o tamanho da consulta
	 * 
	 * [SB001] Selecionar Ordem de Servico por Situa��o [SB002] Selecionar Ordem
	 * de Servico por Situa��o da Programa��o [SB003] Selecionar Ordem de
	 * Servico por Matricula do Imovel [SB004] Selecionar Ordem de Servico por
	 * Codigo do Cliente [SB005] Selecionar Ordem de Servico por Unidade
	 * Superior [SB006] Selecionar Ordem de Servico por Munic�pio [SB007]
	 * Selecionar Ordem de Servico por Bairro [SB008] Selecionar Ordem de
	 * Servico por Bairro Logradouro
	 * 
	 * @author Rafael Pinto
	 * @date 18/08/2006
	 * 
	 * @param PesquisarOrdemServicoHelper
	 * 
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer pesquisarOrdemServicoTamanho(PesquisarOrdemServicoHelper filtro) 
		throws ErroRepositorioException ;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * 
	 * 
	 * @author S�vio Luiz
	 * @date 14/11/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Integer pesquisarIdServicoTipoDaOS(Integer idOS)
			throws ErroRepositorioException;
	
	/**
	 * Consulta a ordem de servico pelo id
	 * 
	 * @author S�vio Luiz
	 * @date 03/01/2007
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public OrdemServico pesquisarDadosServicoTipoPrioridade(Integer idServicoTipo)
		throws ErroRepositorioException;
	
	/**
	 * Atualiza os imoveis da OS que refere a RA passada como parametro
	 * 
	 * @author S�vio Luiz
	 * @date 03/01/2007
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public void atualizarOsDaRA(Integer idRA, Integer idImovel)
			throws ErroRepositorioException;
	
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscaliza��o 
	 * 
	 * @author Raphael Rossiter
	 * @date 25/01/2007
	 * 
	 * @param idOS
	 * @return fiscalizacaoSituacao
	 * @throws ControladorException
	 */
	public FiscalizacaoSituacao pesquisarFiscalizacaoSituacaoPorOS(Integer idOS)
			throws ErroRepositorioException ;
	
	
	/**
	 * [UC0455] Exibir Calend�rio para Elabora��o ou Acompanhamento de Roteiro 
	 * 
	 * @author Raphael Rossiter
	 * @date 14/02/2007
	 * 
	 * @param idProgramacaoRoteiro
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer verificarExistenciaOSProgramacao(Integer idProgramacaoRoteiro)
			throws ErroRepositorioException ;
	
	/**
	 * Pesquisa as Os do servi�o tipo especifico com RA  
	 * 
	 * @author S�vio Luiz
	 * @date 23/02/2007
	 * 
	 * @param idProgramacaoRoteiro
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOSComServicoTipo(Integer idServicoTipo)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa as Os do servi�o tipo especifico com RA  
	 * 
	 * @author S�vio Luiz
	 * @date 23/02/2007
	 * 
	 * @param idProgramacaoRoteiro
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public void atualizarColecaoOS(Collection colecaoIdsOS)
			throws ErroRepositorioException;

	/**
	 * 
	 * Pesquisar data e equipe da programa��o da ordem servi�o
	 * 
	 * @author Ana Maria
	 * @date 09/03/2007
	 */
	public OrdemServicoProgramacao pesquisarDataEquipeOSProgramacao(
			Integer idOs)throws ErroRepositorioException;	
	
	
	/**
	 * [UC0367] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * Obt�m o n�mero de meses da fatura a partir da tabela FISCALIZACAO_SITUACAO_AGUA
	 * 
	 * @author Raphael Rossiter
	 * @date 16/03/2007
	 * 
	 * @param idLigacaoAguaSituacao, idSituacaoEncontrada
	 * @return numeroMesesFatura
	 * @throws ErroRepositorioException
	 */
	public Short pesquisarNumeroMesesFaturaAgua(
			Integer idLigacaoAguaSituacao, Integer idSituacaoEncontrada)
			throws ErroRepositorioException ;
	
	
	/**
	 * [UC0367] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * Obt�m o n�mero de meses da fatura a partir da tabela FISCALIZACAO_SITUACAO_ESGOTO
	 * 
	 * @author Raphael Rossiter
	 * @date 16/03/2007
	 * 
	 * @param idLigacaoEsgotoSituacao, idSituacaoEncontrada
	 * @return numeroMesesFatura
	 * @throws ErroRepositorioException
	 */
	public Short pesquisarNumeroMesesFaturaEsgoto(
			Integer idLigacaoEsgotoSituacao, Integer idSituacaoEncontrada)
			throws ErroRepositorioException ;
	/**
	 * Inserir Debitos 
	 * 
	 * M�todo Criado a pedido de Rossana Carvalho
	 * para inser��o de debitos
	 */
	public Collection pesquisaParaCriarDebitosNaoGerados() throws ErroRepositorioException;
	
	/**
	 * Inserir Debitos Categoria
	 * 
	 * M�todo Criado a pedido de Rossana Carvalho
	 * para inser��o de debitos
	 */
	public Collection pesquisaParaCriarDebitosCategoriaNaoGerados() throws ErroRepositorioException;
	
	/**
	 * [UC0470] Acompanhar Roteiro de Programacao de Ordens de Servico
	 * 
	 * @author Rafael Pinto
	 * @date 21/09/2006
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OrdemServicoProgramacao> pesquisarOSProgramacaoComDataRoteiroIdEquipeOrdenada(
			Date dataRoteiro, Integer idEquipe) throws ErroRepositorioException;
	
	
	/**
	 * [UC0457] Encerra Ordem de Servi�o 
	 * 
	 * @author Raphael Rossiter
	 * @date 25/04/2007
	 * 
	 * @param idServicoTipo
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarServicoTipoOperacao(Integer idServicoTipo)
			throws ErroRepositorioException ;

	/**
	 * [UC0711] Filtro para Emissao de Ordens Seletivas 
	 * 
	 * @author Ivan S�rgio, Ivan S�rgio, Raphael Rossiter
	 * @date 08/11/2007, 27/03/2008, 17/04/2009
	 * @alteracao: Media do Imovel e Consumo por Economia com os valores >= ao informado
	 * 
	 * @param ImovelEmissaoOrdensSeletivasHelper, dataInstalacaoHidrometro, anoMesFaturamento
	 * @return List
	 * @throws ErroRepositorioException
	 */
	public List filtrarImovelEmissaoOrdensSeletivas(ImovelEmissaoOrdensSeletivasHelper helper,
			String dataInstalacaoInicialHidrometro, String anoMesFaturamento, 
			String dataInstalacaoFinalHidrometro,Short codigoEmpresaFebraban) throws ErroRepositorioException ;
	
	/**
	 * Verifica se j� existe Ordem de Servico de
	 * Instalacao/Substituicao de Hidrometro para o Imovel
	 * 
	 * @author Ivan S�rgio
	 * @date 19/11/2007
	 */
	public Integer verificarOrdemServicoInstalacaoSubstituicaoImovel(Integer idImovel, Date dataVencimento)
		throws ErroRepositorioException;
	
	/**
	 * [UC0732] Gerar Relatorio Acompanhamento de Oredem de Servico de Hidrometro
	 * 
	 * @author Ivan S�rgio
	 * @date 13/12/2007, 27/03/2008
	 * @alteracao: Adicionado Motivo Encerramento; Setor Comercial;
	 * 
	 * @param tipoOrdem
	 * @param situacaoOrdem
	 * @param idLocalidade
	 * @param dataEncerramentoInicial
	 * @param dataEncerramentoFinal
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOrdemServicoGerarRelatorioAcompanhamentoHidrometro(
			String idEmpresa,
			String tipoOrdem,
			Short situacaoOrdem,
			String idLocalidadeInicial,
			String idLocalidadeFinal,			
			String dataEncerramentoInicial,
			String dataEncerramentoFinal,
			String idMotivoEncerramento,
			String idSetorComercialInicial,
			String idSetorComercialFinal,
			String codigoQuadraInicial,
			String codigoQuadraFinal,
			String codigoRotaInicial,
			String codigoRotaFinal,
			String sequenciaRotaInicial,
			String sequenciaRotaFinal
			) throws ErroRepositorioException;
	

	
	/**
	 * Pesquisa os dados necess�rios para exportar a ordem de servi�o.
	 *
	 * [UC0720] Exportar Ordem Servi�o Prestadoras
	 *
	 * @author Pedro Alexandre
	 * @date 21/12/2007
	 *
	 * @param idRegistroAtendimento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosParaExportarOrdemServicoPrestadora(Integer idRegistroAtendimento) throws ErroRepositorioException; 


	
	
	/**
	 * [UC0640] Gerar TXT A��o de Cobran�a
	 * 
	 * @author Raphael Rossiter
	 * @date 04/01/2008
	 * 
	 * @param idCobrancaDocumento
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarOrdemServicoPorCobrancaDocumento(Integer idCobrancaDocumento)
			throws ErroRepositorioException ;



	
	/**
	 * [UC0734] Encerrar Ordem de Servico Vencida
	 * 
	 * @author Ivan S�rgio
	 * @date 14/01/2008
	 * 
	 * @param idServicoTipo
	 * @param dataVencimento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List pesquisarOrdemServicoVencida(Integer idServicoTipo, String dataVencimento)
			throws ErroRepositorioException;
	
	/**
	 * [UC0734] Encerrar Ordem de Servico Vencida
	 * 
	 * @author Ivan S�rgio
	 * @date 16/01/2008 
	 * 
	 * @param ordemServico
	 * @throws ErroRepositorioException
	 */
	public void alterarOrdemServicoVencida(OrdemServico ordemServico) throws ErroRepositorioException;

	/**
	 * Pesquisa o movimento da ordem de servi�o
	 *
	 * [UC0720] Exportar Ordem de Servi�o Movimento
	 *
	 * @author Pedro Alexandre
	 * @date 18/01/2008
	 *
	 * @param idOrdemServico
	 * @return
	 * @throws ErroRepositorioException
	 */
	public OrdemServicoMovimento pesquisarOrdemServicoMovimento(Integer idOrdemServico)	throws ErroRepositorioException ;
	
	/**
	 * [UC0753] Manter Ordem de Servico Concluida
	 * 
	 * @author Ivan S�rgio
	 * @date 26/03/2008
	 * 
	 * @param idOrdemServico
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOrdemServicoConcluida(Integer idOrdemServico) throws ErroRepositorioException;
	
	/**
	 * [UC0732] Gerar Relatorio Acompanhamento de Oredem de Servico de Hidrometro
	 * 			RELATORIO: Relacao das Ordens de Servico Concluidas
	 * 
	 * @author Ivan S�rgio
	 * @date 04/04/2008
	 * 
	 * @param tipoOrdem
	 * @param situacaoOrdem
	 * @param idLocalidade
	 * @param dataEncerramentoInicial
	 * @param dataEncerramentoFinal
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOrdemServicoConcluidaGerarRelatorioAcompanhamentoHidrometro(
			String idEmpresa,
			String tipoOrdem,
			Short situacaoOrdem,
			String idLocalidadeInicial,
			String idLocalidadeFinal,
			String dataEncerramentoInicial,
			String dataEncerramentoFinal,
			String idMotivoEncerramento,
			String idSetorComercialInicial,
			String idSetorComercialFinal,
			String codigoQuadraInicial,
			String codigoQuadraFinal,
			String codigoRotaInicial,
			String codigoRotaFinal,
			String sequenciaRotaInicial,
			String sequenciaRotaFinal) throws ErroRepositorioException;

    /**
     * [UC0720] Exportar Ordem de Servi�o Prestadoras
     * 
     * @author Vivianne Sousa
     * @date 02/04/2008
     * 
     * @throws ControladorException
     */
    public void atualizarIndicadorEncerramentoAutomaticoOS(
            Short indicadorEncerramentoAutomatico,Integer idOrdemServico)
            throws ErroRepositorioException ;
    
    /**
     * [UC0753] Manter Ordem Servico Concluida
     * 
     * @author Ivan S�rgio
     * @date 09/04/2008
     * 
     * @param idOrdemServico
     * @param situacaoFiscalizacao
     * @param dataFiscalizacao1
     * @param idFuncionario
     * @throws ErroRepositorioException
     */
    public void atualizarBoletimOSConcluida(
    		Integer idOrdemServico,
			Short situacaoFiscalizacao,
			Date dataFiscalizacao1,
			Integer idFuncionario) throws ErroRepositorioException;
    
    /**
     * [UC0753] Manter Ordem Servico Concluida
     * 
     * @author Ivan S�rgio
     * @date 09/04/2008
     *  
     * @param idOrdemServico
     * @throws ErroRepositorioException
     */
    public void excluirDatasFiscalizacaoBoletimOSConcluida(Integer idOrdemServico)
    	throws ErroRepositorioException;
    
    /***
     * [UC0765] Gerar Boletim Ordens de Servico Concluidas
     * 
     * @author Ivan S�rgio
     * @date 29/04/2008
     * 
     * @param idEmpresa
     * @param idLocalidade
     * @param anoMesReferenciaBoletim
     * @return
     * @throws ErroRepositorioException
     */
	public Collection pesquisarResumoOrdensServicoConcluidas(
			Integer idEmpresa,
			Integer idLocalidade,
			String anoMesReferenciaBoletim) throws ErroRepositorioException;
	
	/**
	 * [UC0765] Gerar Boletim Ordens de Servico Concluidas
     * 
     * @author Ivan S�rgio
     * @date 02/05/2008
	 * 
	 * @param idOrdemServico
	 * @throws ErroRepositorioException
	 */
	public void encerrarBoletimOrdemServicoConcluida(Integer idOrdemServico) throws ErroRepositorioException;


	
	
	/**
	 * Pesquisa Ordens em Processo de Repavimenta��o
	 *
	 * [UC0639] Filtrar Ordens em Processo de Repavimeta��o.
	 *
	 * @author Yara Taciane
	 * @date 02/06/2008
	 *
	 * @param idOrdemServico
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOrdemProcessoRepavimentacao(OSPavimentoHelper pavimentoHelper, Integer numeroPagina)
			throws ErroRepositorioException;


	
	
	/**
	 * Pesquisa Ordens em Processo de Repavimenta��o
	 *
	 * [UC0639] Filtrar Ordens em Processo de Repavimeta��o.
	 *
	 * @author Yara Taciane
	 * @date 12/06/2008
	 *
	 * @param idOrdemServico
	 * @return
	 * @throws ErroRepositorioException
	 */	
	
	public Collection pesquisarOrdemProcessoRepavimentacaoPorTipoPavimentoCalcada(OSPavimentoHelper pavimentoHelper)throws ErroRepositorioException;

	
	/**
	 * Pesquisa Ordens em Processo de Repavimenta��o
	 *
	 * [UC0639] Filtrar Ordens em Processo de Repavimeta��o.
	 *
	 * @author Yara Taciane
	 * @date 12/06/2008
	 *
	 * @param idOrdemServico
	 * @return
	 * @throws ErroRepositorioException
	 */	
	
	public Collection pesquisarOrdemProcessoRepavimentacaoPorTipoPavimentoRua(OSPavimentoHelper pavimentoHelper)throws ErroRepositorioException;

	
	/**
	 * Pesquisa Ordens em Processo de Repavimenta��o
	 *
	 * [UC0639] Filtrar Ordens em Processo de Repavimeta��o.
	 *
	 * @author Yara Taciane
	 * @date 12/06/2008
	 *
	 * @param idOrdemServico
	 * @return
	 * @throws ErroRepositorioException
	 */	
	public Collection pesquisarOrdemProcessoRepavimentacaoPorTipoPavimentoRuaRet(OSPavimentoHelper pavimentoHelper)throws ErroRepositorioException;
	/**
	 * [UC0457]Encerrar Ordem de Servico
	 * 
	 * Obt�m as datas de gera��o de uma ordem de servi�o
	 * 
	 * @author Yara Taciane 
	 * @date 18/06/2006
	 * 
	 * @param numeroOS
	 * @return Collection
	 */
	public Date obterDataGeracaOS(Integer integer)throws ErroRepositorioException;

	
	/**
	 * [UC0457]Encerrar Ordem de Servico
	 * 
	 * Verifica se � OS Seletiva.
	 * 
	 * @author Yara Taciane 
	 * @date 18/06/2006
	 * 
	 * @param numeroOS
	 * @return Collection
	 */
	public Integer verificarOrdemServicoSeletiva(Integer numOS)throws ErroRepositorioException;
			

	/**
	 * [UC0766] Gerar Relatorio Boletim de Ordens de Servico Concluidas
     * 
     * @author Ivan S�rgio
     * @date 07/05/2008
	 * 
	 * @param idEmpresa
	 * @param idLocalidade
	 * @param idSetorComercial
	 * @param referenciaEncerramento
	 * @param situacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarBoletimOrdensServicoConcluidasGerarRelatorio(
			Integer idEmpresa,
			Integer idLocalidade,
			Integer idSetorComercial,
			String referenciaEncerramento,
			Short situacao) throws ErroRepositorioException;

	/**
     * [UC0427] Tramitar Registro de Atendimento
     * 
     * @author Vivianne Sousa
     * @date 10/06/2008
     * 
     * @throws ControladorException
     */
    public void atualizarUnidadeOrganizacionalAtualOS(Integer idUnidadeOrganizacionalAtual,
            Integer idRA)throws ErroRepositorioException;
    
    /**
     * [UC0470] Acompanhar Roteiro de Programa��o de Ordens de Servi�o
     * [FS0008] - Verificar possibilidade da inclus�o da ordem de servi�o
     * 
     * @author Vivianne Sousa
     * @date 12/06/2008
     * 
     * @throws ControladorException
     */
    public UnidadeOrganizacional obterUnidadeAtualOS(Integer idOS)throws ErroRepositorioException;
    
    /**
	 * [UC0456] Elaborar Roteiro de Programa��o de Ordens de Servi�o
	 * 
	 * @author Vivianne Sousa
	 * @date 13/06/2008
	 */
	public Collection<Integer> recuperaRegistroAtendimentoPendenteUnidadeAtual(
			Integer unidadeAtual) throws ErroRepositorioException;
	
	/**
	 * [UC0427] - Tramitar Registro Atendimento
	 * [FS0011]Validar Tramite para terceira
	 * 
	 * @author Vivianne Sousa
	 * @date 29/10/2008
	 */
	public boolean existeMaisDeUmaOrdemServicoPendente(
			Integer idregistroAtendimento) throws ErroRepositorioException;
	
	/**
	 * [UC0488] - Consultar Dados do Registro de Atendimento 
	 * 
	 * Dados da Os Associadas
	 * 
	 * @author Arthur Carvalho
	 * @date 17/02/2009
	 */
	public Collection pesquisarOrdensServicosAssociados(Integer idregistroAtendimento) 
		throws ErroRepositorioException ;
	
	/**
	 * Retorna a quantidade de registros para geracao do relatorio
	 * 
	 * [UC0711] Filtro para Emissao de Ordens Seletivas 
	 * 
	 * @author Anderson Italo
	 * @date 21/08/2009
	 * 
	 * @param ImovelEmissaoOrdensSeletivasHelper, dataInstalacaoHidrometro, anoMesFaturamento
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer filtrarImovelEmissaoOrdensSeletivasCount(ImovelEmissaoOrdensSeletivasHelper helper,
			String dataInstalacaoHidrometroInicial, String anoMesFaturamento, String dataInstalacaoHidrometroFinal ) throws ErroRepositorioException;
	
	/**
	 * Pesquisar Servi�o Tipo Seletivo
	 * 
	 * Seleciona Servi�o Tipo Seletivo por codigo da constante
	 * 
	 * @author Hugo Amorim
	 * @date 09/10/2009
	 * 
	 */
	public Integer recuperaServicoTipoSeletivoPorConstante(
			Integer codigoConstante) throws ErroRepositorioException;
	
	
	/**
	 * [UC0620] Obter Indicador de Autoriza��o para Manuten��o da OS 
	 * 
	 *          Pesquisa a unidade Superior da unidade Organizacional
	 * 
	 * @author Arthur Carvalho
	 * 
	 * @date 11/11/2009
	 * 
	 * @param idUnidadeOrganizacional
	 * @return
	 * @throws ErroRepositorioException
	 */
	
	public UnidadeOrganizacional pesquisarUnidadeOrganizacionalSuperior(Integer idUnidadeOrganizacional) 
			 throws ErroRepositorioException;
	
	/**
	 * Pesquisar Servi�o Tipo Seletivo
	 * 
	 * Seleciona Servi�o Tipo Seletivo por codigo da constante
	 * 
	 * @author Hugo Amorim
	 * @date 12/02/2009
	 * 
	 */
	public Integer recuperaServicoTipoPorConstante(
			Integer codigoConstante) throws ErroRepositorioException;
	
	
	/**
	 * Pesquisa quantidade Ordens em Processo de Repavimenta��o
	 *
	 * [UC0639] Filtrar Ordens em Processo de Repavimeta��o.
	 *
	 * @author Arthur Carvalho
	 * @date 22/02/2010
	 *
	 * @param idOrdemServico
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarOrdemProcessoRepavimentacaoCount(OSPavimentoHelper pavimentoHelper)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa a quantidade de Ocorrencias Consecultivas da anormalidade
	 *
	 * Parte do [UC0711] Filtro Para Geracao de Ordens de Servco Hidrometro.
	 *
	 * @author Daniel Alves
	 * @date 22/03/2010
	 *
	 * @param String idAnormalidade, int qtdAnormalidades, 
	 * 		int referencia,ImovelEmissaoOrdensSeletivasHelper filtro
	 * @return Collection<Integer[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer[]> pesquisarNumeroDeOcorrenciasConsecultivasAnormalidades(			
			String idAnormalidades, String qtdAnormalidades,
			Integer referenciaFaturamento, 
			ImovelEmissaoOrdensSeletivasHelper  helper 		
			   )throws ErroRepositorioException;
	
	/**
	 *[SB0006] � Obter Unidade Repavimentadora do Munic�pio
	 * 
	 * 1.1.1.1.	Obter a unidade repavimentadora a partir do munic�pio do endere�o do CEP 
	 * 
	 * @author Arthur Carvalho
	 * @date 12/02/2009
	 * 
	 */
	public UnidadeOrganizacional obterUnidadeRepavimentadorAPartirMunicipio(OrdemServico os,
			String tipoPesquisa) 
		throws ErroRepositorioException ;
	
	/**
	 * Pesquisa Ordens de Repavimenta��o em Processo de Aceite.
	 * 
	 * [UC1019] Filtrar Ordens de Repavimeta��o em Processo de Aceite.
	 * 
	 * @author Hugo Leonardo
	 * @date 17/05/2010
	 *
	 * @param idOrdemServico
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOrdemRepavimentacaoProcessoAceite(OrdemRepavimentacaoProcessoAceiteHelper oSPavimentoHelper, Integer numeroPagina)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa quantidade Ordens de Repavimenta��o em Processo de Aceite.
	 *
	 * [UC1019] Filtrar Ordens de Repavimeta��o em Processo de Aceite.
	 *
	 * @author Hugo Leonardo.
	 * @date 17/05/2010
	 *
	 * @param idOrdemServico
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarOrdemProcessoRepavimentacaoAceiteCount(OrdemRepavimentacaoProcessoAceiteHelper oSPavimentoAceiteHelper)
			throws ErroRepositorioException ;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * Seleciona OSFS_DTFISCALIZACAOSITUACAO da tabela ORDEM_SERVICO_FISC_SIT 
	 * para ORSE_ID=ORSE_ID da tabela ORDEM_SERVICO
	 * 
	 * @author Vivianne Sousa
	 * @date 28/07/2010
	 * 
	 */
	public Date recuperaDataFiscalizacaoSituacao(
			Integer idOrdemServico,Integer idFiscalizacaoSituacao) throws ErroRepositorioException;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * @author Vivianne Sousa
	 * @date 29/07/2010
	 */
	public OrdemServicoFiscSit recuperaOrdemServicoFiscSit(
			Integer idOrdemServico, Integer idFiscalizacaoSituacao) throws ErroRepositorioException;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * @author Vivianne Sousa
	 * @date 30/07/2010
	 */
	public OrdemServico recuperaOrdemServico(
			Integer idOrdemServico) throws ErroRepositorioException;

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * @author Vivianne Sousa
	 * @date 02/08/2010
	 */
	public Collection recuperaFiscalizacaoSituacao(
			Integer idOrdemServico) throws ErroRepositorioException;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * @author Vivianne Sousa
	 * @date 03/08/2010
	 */
	public Collection recuperaFiscalizacaoSituacaoSelecionada(
			Integer idOrdemServico) throws ErroRepositorioException;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * @author Vivianne Sousa
	 * @date 09/08/2010
	 */
	public Collection recuperaOrdemServicoFiscSit(
			Integer idOrdemServico) throws ErroRepositorioException;
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscaliza��o
	 * [SB0003] � Calcular/Inserir Valor
	 * 
	 * @author Vivianne Sousa
	 * @date 18/08/2010
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarIndicadorDebitoOS(Integer indicadorDebito,
			Integer idFiscalizacaoSituacao, Integer idOS)throws ErroRepositorioException;
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscaliza��o
	 * [SB0001] � Atualizar Ordem de Servi�o.
	 * 
	 * @author Vivianne Sousa
	 * @date 18/08/2010
	 * 
	 * @throws ErroRepositorioException
	 */
    public void excluirSituacaoFiscalizacaoPorOS(Integer idOrdemServico)
    	throws ErroRepositorioException ;
    
    /**
	 * [UC0488] Informar Retorno Ordem de Fiscaliza��o
	 * [SB0001] � Atualizar Ordem de Servi�o.
	 * 
	 * @author Vivianne Sousa
	 * @date 18/08/2010
	 * 
	 * @throws ErroRepositorioException
	 */
    public void excluirCobrancaDocumentoFiscPorOS(Integer idOrdemServico)
    	throws ErroRepositorioException;
    
	
	/**
	 * [UC0488] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * @author Vivianne Sousa
	 * @date 19/08/2010
	 */
	public Short recuperaIndicadorDebitoDaOrdemServicoFiscSit(
			Integer idOrdemServico, Integer idFiscalizacaoSituacao) throws ErroRepositorioException;
	
	/**
	 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
	 * 
	 * @author Vivianne Sousa
	 * @date 30/08/2010
	 * 
	 */
	public OrdemServicoFiscSit recuperaOrdemServicoFiscSitComMenorDataFiscalizacao(
			Integer idOrdemServico) throws ErroRepositorioException;
	/**
	 * [UC0441] Consultar Dados da Ordem de Servi�o
	 * 
	 * @author Vivianne Sousa
	 * @date 01/09/2010
	 */
	public Collection pesquisaOrdemServicoFiscSit(
			Integer idOrdemServico) throws ErroRepositorioException;
	
	/**
	 * [UC1109] Filtrar Dados para Gera��o Boletim de Custo de Repavimenta��o
	 *
	 * @author Hugo Leonardo
	 * @date 03/01/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioBoletimCustoPavimento(FiltrarBoletimCustoPavimentoHelper relatorioHelper)
			throws ErroRepositorioException;
	
	/**
	 * [UC1109] Filtrar Dados para Gera��o Boletim de Custo de Repavimenta��o
 	 * 
 	 * @author Hugo Leonardo
 	 * @date 03/01/2011
 	 * 
 	 * @return boolean
	 */
	public boolean verificaDadosGeracaoBoletimCustoRepavimentacao(FiltrarBoletimCustoPavimentoHelper relatorioHelper)
		throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC1110] Gerar Boletim de Custo de Repavimenta��o por Tipo de Pavimento
	 *
	 * @author Hugo Leonardo
	 * @date 04/01/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarBoletimCustoPavimentoPorTipoPavimentoRua(FiltrarBoletimCustoPavimentoHelper relatorioHelper)	
		throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC1110] Gerar Boletim de Custo de Repavimenta��o por Tipo de Pavimento
	 *
	 * @author Hugo Leonardo
	 * @date 04/01/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarBoletimCustoPavimentoPorTipoPavimentoRuaRet(FiltrarBoletimCustoPavimentoHelper relatorioHelper)	
		throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC1110] Gerar Boletim de Custo de Repavimenta��o por Tipo de Pavimento
	 *
	 * @author Hugo Leonardo
	 * @date 10/01/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarTotaisPorTipoPavimentoRuaDemandadas(
			FiltrarBoletimCustoPavimentoHelper relatorioHelper)	throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC1110] Gerar Boletim de Custo de Repavimenta��o por Tipo de Pavimento
	 *
	 * @author Hugo Leonardo
	 * @date 10/01/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarTotaisPorTipoPavimentoRuaDemandadas3Meses(
			FiltrarBoletimCustoPavimentoHelper relatorioHelper)	throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC1110] Gerar Boletim de Custo de Repavimenta��o por Tipo de Pavimento
	 *
	 * @author Hugo Leonardo
	 * @date 10/01/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarTotaisPorTipoPavimentoRuaAceitas(
			FiltrarBoletimCustoPavimentoHelper relatorioHelper)	throws ErroRepositorioException;
	
	/**
	 * [UC0457] Encerrar Ordem de Servi�o
	 * 
	 * @author Vivianne Sousa
	 * @date 18/01/2011
	 */
	public ServicoTipo recuperaServicoTipoDaOrdemServico(
			Integer idOrdemServico) throws ErroRepositorioException;
	
	/**
	 * [UC0457] Encerrar Ordem de Servi�o
	 * 
	 * @author Vivianne Sousa
	 * @date 21/01/2011
	 */
	public ServicoTipoBoletim recuperaServicoTipoBoletimDoServicoTipo(
			Integer idServicoTipo) throws ErroRepositorioException;
	
	/**
	 * [UC1116] Atualizar Informa��es da OS para Boletim de Medi��o
	 * 
	 * @author Vivianne Sousa
	 * @date 02/02/2011
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public OrdemServico recuperaOSEDadosImovel(Integer idOS)
			throws ErroRepositorioException;
	
	/**
	 * [UC1116] Atualizar Informa��es da OS para Boletim de Medi��o
	 * 
	 * @author Vivianne Sousa
	 * @date 02/02/2011
	 */
	public OrdemServicoBoletim recuperaOrdemServicoBoletimDaOS(
			Integer idOrdemServico) throws ErroRepositorioException;
	
	/**
	 * [UC1163]  Gerar  Relat�rio de OS executadas por Prestadora de Servi�o
	 * 
	 * @author Vivianne Sousa
	 * @date 13/04/2011
	 */
	public Collection recuperaOSExecutadas(Date dataInicial,Date dataFinal, 
		Integer idGerencia, Integer idUnidade, Integer idLocalidade,
		String[] idsRegiao,String[] idsMicroregiao,
		String[] idsMunicipio) throws ErroRepositorioException;
	
	/**
	 * [UC1163]  Gerar  Relat�rio de OS executadas por Prestadora de Servi�o
	 * 
	 * @author Vivianne Sousa
	 * @date 18/04/2011
	 */
	public Collection recuperaTotalServicoOSExecutadas(Date dataInicial,Date dataFinal, 
		Integer idLocalidade) throws ErroRepositorioException;
	
	/**
	 * [UC1163]  Gerar  Relat�rio de OS executadas por Prestadora de Servi�o
	 * 
	 * @author Vivianne Sousa
	 * @date 18/04/2011
	 */
	public Collection recuperaTotalServicoOSExecutadasPorLocalidade(Date dataInicial,Date dataFinal, 
			Integer idGerencia, Integer idUnidade, Integer idLocalidade,
			String[] idsRegiao,String[] idsMicroregiao,	String[] idsMunicipio) throws ErroRepositorioException;
	
	/**
	 * [UC1163]  Gerar  Relat�rio de OS executadas por Prestadora de Servi�o
	 * 
	 * @author Vivianne Sousa
	 * @date 04/05/2011
	 */
	public Integer recuperaTotalOSExecutadasPorLocalidade(Date dataInicial,Date dataFinal, 
			Integer idGerencia, Integer idUnidade, Integer idLocalidade,
			String[] idsRegiao,String[] idsMicroregiao,
			String[] idsMunicipio) throws ErroRepositorioException ;
	
	/**
	 * 
	 * Recupera os par�metros necess�rios da OS
	 * 
	 * @author S�vio Luiz
	 * @date 04/04/2011
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Short pesquisarIndAtividadeServicoTipoOS(Integer idOS)
			throws ErroRepositorioException;

	/**
	 * Atualiza o documento de cobran�a da ordem de servi�o que foi 
	 * gerado pelo "[UC0444 Gerar e Emitir Extrato de D�bito]"
	 * 
	 * [UC1169] Movimentar Ordens de Servi�o de Cobran�a por Resultado
	 * 
	 * @author Mariana Victor
	 * @date 19/05/2011
	 */
	public void atualizarDocumentoCobrancaOS(Integer idOrdemServico,
			Integer idCobrancaDocumento)
			throws ErroRepositorioException;	
	
	/**
	 * [UC1176] Gerar Ordem de Fiscaliza��o para Ordem de Servi�o Encerrada 
	 * 
	 * [FS0001] � Validar Ordem de Servi�o.
	 * 
	 * @author Vivianne Sousa
	 * @date 24/05/2011
	 */
	public Integer pesquisarOSFiscalizacaoPendente(Integer numeroOS)
			throws ErroRepositorioException;
	
	/**
	 * [UC1176] Gerar Ordem de Fiscaliza��o para Ordem de Servi�o Encerrada 
	 * [SB0001] - Selecionar Ordens de Servi�o 
	 * 
	 * @author Vivianne Sousa
	 * @date 24/05/2011
	 */
	public Integer recuperaQtdeOSEncerradaConclusaoServico(Integer idRotaGrupoCobranca,Integer idGerencia, 
			Integer idUnidade, Integer idLocalidadeInicial,Integer idLocalidadeFinal, 
			Integer idTipoServico, Integer qtdeDiasEncerramentoOS, Integer idEmpresaContratoServico) throws ErroRepositorioException;
	
	/**
	 * [UC1176] Gerar Ordem de Fiscaliza��o para Ordem de Servi�o Encerrada 
	 * [SB0001] - Selecionar Ordens de Servi�o 
	 * 
	 * @author Vivianne Sousa
	 * @date 24/05/2011
	 */
	public Integer recuperaQtdeOSEncerradaNaoConclusaoServico(Integer idRotaGrupoCobranca,Integer idGerencia, 
			Integer idUnidade, Integer idLocalidadeInicial,Integer idLocalidadeFinal, 
			Integer idTipoServico, Integer qtdeDiasEncerramentoOS, Integer idEmpresaContratoServico) throws ErroRepositorioException;
	
	/**
	 * [UC1176] Gerar Ordem de Fiscaliza��o para Ordem de Servi�o Encerrada 
	 * 
	 * [FS0001] � Validar Ordem de Servi�o.
	 * 
	 * @author Vivianne Sousa
	 * @date 26/05/2011
	 */
	public Integer pesquisarEmpresaContratoEmpresaServico(Integer idGrupoCobranca)
			throws ErroRepositorioException;
	
	/**
	 * [UC1176] Gerar Ordem de Fiscaliza��o para Ordem de Servi�o Encerrada 
	 * 
	 * [FS0001] � Validar Ordem de Servi�o.
	 * 
	 * @author Vivianne Sousa
	 * @date 26/05/2011
	 */
	public Integer pesquisarRotaGrupoCobranca(Integer idGrupoCobranca)
			throws ErroRepositorioException;
	
	/**
	 * [UC1176] Gerar Ordem de Fiscaliza��o para Ordem de Servi�o Encerrada 
	 * [SB0002] � Verificar Ordem Servi�o  
	 * 
	 * @author Vivianne Sousa
	 * @date 26/05/2011
	 */
	public Integer pesquisarIdMotivoEncerramentoOS(Integer idOrdemServico) throws ErroRepositorioException;
	
	/**
	 * [UC1176] Gerar Ordem de Fiscaliza��o para Ordem de Servi�o Encerrada 
	 * [SB0003] - Gerar V�rias Ordens de Fiscaliza��o
	 * 
	 * @author Vivianne Sousa
	 * @date 26/05/2011
	 */
	public Collection recuperaIdOSEncerradaNaoConclusaoServico(Integer idRotaGrupoCobranca,Integer idGerencia, 
			Integer idUnidade, Integer idLocalidadeInicial,Integer idLocalidadeFinal, 
			Integer idTipoServico, Integer qtdeDiasEncerramentoOS, Integer idEmpresaContratoServico) throws ErroRepositorioException;

	/**
	 * [UC1176] Gerar Ordem de Fiscaliza��o para Ordem de Servi�o Encerrada 
	 * [SB0003] - Gerar V�rias Ordens de Fiscaliza��o
	 * 
	 * @author Vivianne Sousa
	 * @date 26/05/2011
	 */
	public Collection recuperaIdOSEncerradaConclusaoServico(Integer idRotaGrupoCobranca,Integer idGerencia, 
			Integer idUnidade, Integer idLocalidadeInicial,Integer idLocalidadeFinal, 
			Integer idTipoServico, Integer qtdeDiasEncerramentoOS, Integer idEmpresaContratoServico) throws ErroRepositorioException;
	
	
	/**
	 * [UC1176] Gerar Ordem de Fiscaliza��o para Ordem de Servi�o Encerrada 
	 * [SB0004] � Gerar Ordem de Servi�o.
	 * 
	 * @author Vivianne Sousa
	 * @date 27/05/2011
	 */
	public Object[] pesquisarImovelEUnidadeOrganizacional(Integer idOrdemServico) throws ErroRepositorioException;
	
	/**
	 * [UC1176] Gerar Ordem de Fiscaliza��o para Ordem de Servi�o Encerrada 
	 * [SB0004] � Gerar Ordem de Servi�o.
	 * 
	 * Consulta a ordem de servico pelo id
	 * 
	 * @author Vivianne sousa
	 * @date 30/05/2011
	 * 
	 * @param idOS
	 * @throws ControladorException
	 */
	public Object[] recuperaDadosOSPorId(Integer idOS)throws ErroRepositorioException;
	
	/**
	 * [UC1116] Atualizar Informa��es da OS para Boletim de Medi��o
	 * 
	 * @author Vivianne Sousa
	 * @date 01/06/2011
	 */
	public OrdemServicoBoletim recuperaOrdemServicoBoletim(
			Integer idOrdemServico) throws ErroRepositorioException;
	
	/**
	 * [UC1116] Atualizar Informa��es da OS para Boletim de Medi��o
	 * 
	 * @author Vivianne Sousa
	 * @date 01/06/2011
	 */
	public String pesquisarNumeroHidrometro(Integer idImovel) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0713] Emitir Ordem de Servi�o Seletiva
	 * [SB0002] Gerar TXT 
	 * 
	 * @author Vivianne Sousa
	 * @date 28/06/2011
	 */
	public Collection pesquisarDadosEmitirTxtOsInspecaoAnormalidade(
			Integer quantidadeInicio, Integer quantidadeMaxima,Integer idComandoOrdemSeletiva)
			throws ErroRepositorioException;
	
	/**
	 * [UC0713] Emitir Ordem de Servi�o Seletiva
	 * [SB0002] Gerar TXT 
	 * 
	 * @author Vivianne Sousa
	 * @date 29/06/2011
	 */
	public Collection pesquisarAtendimentoMotivoEncerramento()
			throws ErroRepositorioException;
	
	/**
	 * [UC1185] Informar Calibragem
	 * 
	 * @author Th�lio Ara�jo
	 * @param osProgramacaoCalibragem
	 * @throws ErroRepositorioException
	 */
	public void atualizarInformarCalibragem(OSProgramacaoCalibragem osProgramacaoCalibragem) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0471] Obter Dados dos equipamentos especiais
	 * 
	 * @author Nathalia Santos
	 * @date 29/06/2011
	 * 
	 * @return EquipamentosEspecial
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarEquipeEquipamentosEspeciais(Integer indicadorEquipamentosEspeciais)
			throws ErroRepositorioException;
	
	/**
	 * [UC0713] Emitir Ordem de Servi�o Seletiva
	 * [SB0002] Gerar TXT 
	 * 
	 * @author Vivianne Sousa
	 * @date 05/07/2011
	 */
	public String pesquisarAnormalidadeImovelPorNumeroDeOcorrenciasConsecultivas(			
			Collection idAnormalidades, Integer qtdAnormalidades,
			List<String> anoMesOcorrencias, Integer idImovel)throws ErroRepositorioException;
	
	/**
	 * [UC0713] Emitir Ordem de Servi�o Seletiva
	 * [SB0002] Gerar TXT 
	 * 
	 * @author Vivianne Sousa
	 * @date 05/07/2011
	 */
	public Collection pesquisarAnormalidadeComandoOSS(Integer idComando)
			throws ErroRepositorioException;
	
	/**
	 * [UC0412] Manter Tipo de Servi�o
	 * [SB0003] Atualizar Grau de Import�ncia
	 *
	 * @author Th�lio Ara�jo
	 * @date 30/06/2011
	 */
	public void atualizarGrauImportanciaServicoTipo(ServicoTipo servicoTipo)
	throws ErroRepositorioException;
	
	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspe��o de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @since 11/07/2011
	 */
	public Integer pesquisarDadosComandoOSSeletivaCount(
			Integer idEmpresa, Date comandoInicial, Date comandoFinal)
			throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspe��o de Anormalidade
	 * 
	 * @author Vivianne Sousa, Diogo Peixoto
	 * @since 11/07/2011, 14/09/2011
	 * 
	 * @param idEmpresa
	 * @param dataInicial
	 * @param dataFinal
	 * @param numeroIndice
	 * @param quantidadeRegistros
	 * @param comandoEncerrado - Caso a pesquisa retorne apenas os comandos encerrados.
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosComandoOSSeletivaResumido(
			Integer idEmpresa, Date dataInicial, Date dataFinal,
			 int numeroIndice,int quantidadeRegistros, boolean comandoEncerrado) throws ErroRepositorioException; 
	
	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspe��o de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @since 12/07/2011
	 */
	public ComandoOrdemSeletiva pesquisarDadosComandoOSSeletiva(
			Integer idComandoOrdemSeletiva) throws ErroRepositorioException;
	
	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspe��o de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @since 13/07/2011
	 */
	public Collection pesquisarDadosAnormalidadeComandoOSS(
			Integer idComandoOrdemSeletiva) throws ErroRepositorioException ;

	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspe��o de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @since 13/07/2011
	 */
	public Collection pesquisarDadosCapacidHidrComandoOSS(
			Integer idComandoOrdemSeletiva) throws ErroRepositorioException;	


	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspe��o de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @since 13/07/2011
	 */
	public Collection pesquisarDadosLigacaoSitComandoOSS(
			Integer idComandoOrdemSeletiva) throws ErroRepositorioException;
	
	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspe��o de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @since 15/07/2011
	 */
	public ComandoOrdemSeletiva pesquisarComandoOSSeletiva(
			Integer idComandoOrdemSeletiva) throws ErroRepositorioException;
	
	/**
	 * [UC1192] Movimentar OS Seletiva de Inspe��o de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @date 18/07/2011
	 */
	public Collection<Object[]> pesquisarDadosOSEmitir(Integer idComando,
			Integer numeroOSInicial, Integer numeroOSFinal) throws ErroRepositorioException;
	
	/**
	 * [UC1192] Movimentar OS Seletiva de Inspe��o de Anormalidade
	 * [FS0001] - Verificar se ordem de servi�o faz parte do comando
	 * 
	 * @author Vivianne Sousa
	 * @date 19/07/2011
	 */
	public Collection verificaSeOSFazParteComando(Integer idComandoOrdemSeletiva,
			List<Integer> numerosOSPesquisar) throws ErroRepositorioException;
	
	/**
	 * [UC1192] Movimentar OS Seletiva de Inspe��o de Anormalidade
	 * [FS0003] � Verificar se im�vel faz parte do comando
	 * 
	 * @author Vivianne Sousa
	 * @date 19/07/2011
	 */
	public Collection verificaSeImovelFazParteComando(Integer idComandoOrdemSeletiva,
			List<Integer> numerosImoveisPesquisar) throws ErroRepositorioException;
	
	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Servi�o do Roteiro Programado
	 * 
	 * Pesquisa os id's das equipes que ainda possuem OS, para a data
	 * informada, que ainda n�o foram encaminhadas para o campo.
	 * 
	 * @author Bruno Barros
	 * @date 06/07/2011
	 * 
	 * @param dataRoteiro - Data para a pesquisa das OS
	 * 
	 * @return Collection<Integer> - Cole��o com todos os ID's das equipes.
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> 
		pesquisarEquipesOSNaoEnviadas( Collection<Integer> colsEquipes, Date dataRoteiro ) 
			throws ErroRepositorioException;	
	
	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Servi�o do Roteiro Programado
	 * 
	 * Pesquisa se ja foi incluido um arquivo de acompanhamento
	 * de servi�o para a equipe / data informadas
	 * 
	 * @author Bruno Barros
	 * @date 06/07/2011
	 * 
	 * @param idEquipe - Identificador da equipe
	 * @param dataRoteiro - Data para a pesquisa do arquivo
	 * 
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> 
		pesquisarArquivoTextoAcompanhamentoServico( 
			Integer idUnidadeOrganizacional, 
			Date dataRoteiro ) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Servi�o do Roteiro Programado
	 * 
	 * Pesquisa o imei de uma equipe
	 * 
	 * @author Bruno Barros
	 * @date 06/07/2011
	 * 
	 * @param idEquipe - Identificador da equipe
	 * 
	 * @return BigDecimal - Numero do imei da equipe informada
	 * 
	 * @throws ErroRepositorioException
	 */
	public BigDecimal 
		pesquisarIMEIEquipe( 
			Integer idEquipe ) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Servi�o do Roteiro Programado
	 * 
	 * Pesquisa as OS que ainda n�o foram enviadas para uma equipe em uma
	 * determinada data
	 * 
	 * @author Bruno Barros
	 * @date 06/07/2011
	 * 
	 * @param idEquipe - id da equipe que ter� as os incluidas 
	 * 
	 * @return Collection<OSProgramacao> - Cole��o com todos as ordens de servico
	 * a serem incluidas na programa��o
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OrdemServicoProgramacao> 
	pesquisarOSNaoEnviadas( Integer idEquipe, Date dataProgramacao ) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Servi�o do Roteiro Programado
	 * 
	 * Pesquisa o id do Arquivo Texto do Acompanhamento de Servi�o
	 * 
	 * @author Bruno Barros
	 * @date 06/07/2011
	 * 
	 * @param idEquipe - Identificador da equipe
	 * @param dataRoteiro - Data do roteiro a ser pesquisado
	 * 
	 * @return Integer - Numero do imei da equipe informada
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer 
		pesquisarIdArquivoTextoAcompanhamentoServico( 
			Integer idEquipe,
			Date dataRoteiro)
			throws ErroRepositorioException;
	
	/**
	 * [UC1199] � Acompanhar Arquivos de Roteiro
	 * 
	 * Pesquisa o Arquivo Texto do Acompanhamento de Servi�o
	 * 
	 * @author Th�lio Ara�jo
	 * @date 19/07/2011
	 * 
	 * @param periodoProgramacaoInicial
	 * @param periodoProgramacaoFinal
	 * @param idEmpresa
	 * @param idSituacao
	 * 
	 * @returnCollection <ArquivoTextoAcompanhamentoServico>
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<?> pesquisarAcompanhamentoArquivosRoteiro( 
			String dataProgramacao,
			String idEmpresa,
			String idSituacao,
			Integer idUnidOrganizacacional) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1199] � Acompanhar Arquivos de Roteiro
	 * 
	 * Pesquisa OS Programacao Acompanhamento Servico
	 * 
	 * @author Th�lio Ara�jo
	 * @date 21/07/2011
	 * 
	 * @param idArqTextoAcompServico
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOSProgramacaoAcompServico(
			String idArqTextoAcompServico) throws ErroRepositorioException;
	
	/**
	 * [UC1197] Encerrar Comandos de OS Seletiva de Inspe��o de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @date 21/07/2011
	 */
	public Collection pesquisaOrdemServicoFazParteComando(int quantidadeInicio, 
			Integer idComandoOrdemSeletiva) throws ErroRepositorioException;
	
	/**
	 * [UC1197] Encerrar Comandos de OS Seletiva de Inspe��o de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @date 21/07/2011
	 */
	public void atualizarDataEncerramentoComando(Integer idComando) throws ErroRepositorioException;
	

	/**
	 * [UC0713] Emitir Ordem de Servi�o Seletiva
	 * [SB0002] Gerar TXT 
	 * 
	 * @author Vivianne Sousa
	 * @date 21/07/2011
	 */
	public void atualizarIndicadorGeracaoTxtComando(Integer idComando) throws ErroRepositorioException ;
			
	
	/**
	 * [UC1190] Programa��o Autom�tica do Roteiro para Acompanhamento das OS
	 *
	 * [SB0003] Programa��o Autom�tica das Ordens de Servi�o por Prioridade 
	 * @author S�vio Luiz
	 * @date 19/07/2011
	 */
	public Object[] pesquisarDadosOSCalibragem(Integer idPriorizacaoTipo,Integer idOSProgramaCalibragem,
			Integer faixaPriorizacao) throws ErroRepositorioException;
	
	/**
	 * [UC1190] Programa��o Autom�tica do Roteiro para Acompanhamento das OS
	 *
	 * [SB0004] Recuperar Equipe Pela Ordem de Servi�o
	 *  
	 * @author S�vio Luiz
	 * @date 19/07/2011
	 */
	public Collection<Equipe> pequisarEquipesPorUnidade(Integer idUnidade)
			throws ErroRepositorioException;
	

	/**
	 * [UC1190] Programa��o Autom�tica do Roteiro para Acompanhamento das OS
	 *
	 * [SB0004] Recuperar Equipe Pela Ordem de Servi�o
	 *  
	 * @author S�vio Luiz
	 * @date 19/07/2011
	 */
	public Object[] pesquisarTempoMedioOSProgramacaoComDataRoteiroUnidade(
			Integer idProgramacaoRoteiro,Integer idEquipe) throws ErroRepositorioException;
	
	/**
	 * [UC1190] Programa��o Autom�tica do Roteiro para Acompanhamento das OS
	 *
	 * @author S�vio Luiz
	 * @date 19/07/2011
	 */
	public Collection<OrdemServico> pesquisarOSFatorPrioridadeDecrescente(Integer idUnidadeOrganizacional)
	throws ErroRepositorioException;
	
	/**
	 * [UC1190] Programa��o Autom�tica do Roteiro para Acompanhamento das OS
	 *
	 * @author S�vio Luiz
	 * @date 19/07/2011
	 */
	public Date pesquisarDataAnteriorProgramacaoRoteiro(Integer idUnidadeOrganizacional)
	throws ErroRepositorioException;
	
	/**
	 * [UC1190] Programa��o Autom�tica do Roteiro para Acompanhamento das OS
	 *
	 * @author S�vio Luiz
	 * @date 19/07/2011
	 */
	public Collection pesquisarOSAcompServicoTransmitidasNaoAtualizadas(Integer idUnidadeOrganizacional,Date dataProgramacao)
	throws ErroRepositorioException;
	

	/**
	 * [UC1190] Programa��o Autom�tica do Roteiro para Acompanhamento das OS
	 *
	 * @author S�vio Luiz
	 * @date 19/07/2011
	 */
	public Collection pesquisarOSAcompServicoNaoENcerradas(Integer idUnidadeOrganizacional,Date dataProgramacao)
	throws ErroRepositorioException;
	
	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Servi�o do Roteiro Programado
	 * 
	 * Pesquisa a tabela atendimentopublico.os_prg_acomp_servico retornando
	 * apenas as os que ainda n�o foram enviadas.
	 * 
	 * @author Bruno Barros
	 * @date 26/07/2011
	 * 
	 * @param idEquipe - id da equipe que ter� as os incluidas 
	 * 
	 * @return Collection<OSProgramacaoAcompanhamentoServico> - Cole��o com todos as ordens de servico
	 * a serem incluidas na programa��o
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OSProgramacaoAcompanhamentoServico> 
		pesquisarOSAcompanhamentoServico( Integer idEquipe,Date dataRoteiro, boolean arquivoOnLine ) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Servi�o do Roteiro Programado
	 * 
	 * Pesquisa a tabela atendimentopublico.os_at_prg_acomp_servico
	 * apenas as os que ainda n�o foram enviadas.
	 * 
	 * @author Bruno Barros
	 * @date 26/07/2011
	 * 
	 * @param idOSProgramacaoAcompanhamentoServico - id da ativdade programacao 
	 * 
	 * @return Collection<OSAtividadeProgramacaoAcompanhamentoServico> - Cole��o com todas as atividades
	 * a serem incluidas na programa��o
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OSAtividadeProgramacaoAcompanhamentoServico> 
		pesquisarAtividadeOSAcompanhamentoServico( Integer idOSProgramacaoAcompanhamentoServico ) 
			throws ErroRepositorioException;	
	
	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspe��o de Anormalidade
	 * 
	 * @author Vivianne Sousa
	 * @date 26/07/2011
	 */
	public Integer pesquisaOrdemServicoNaoPendenteFazParteComando(Integer idComandoOrdemSeletiva) throws ErroRepositorioException ;
	
	/**
	 * [UC1190] Programa��o Autom�tica do Roteiro para Acompanhamento das OS
	 *
	 * @author S�vio Luiz
	 * @date 19/07/2011
	 */
	public Equipe pesquisarEquipeComEquipamentoEspecial(Integer idUnidadeOrganizacional,Integer idEquipamentoEspecial)
	throws ErroRepositorioException;
	
	/**
	 * [UC1190] Programa��o Autom�tica do Roteiro para Acompanhamento das OS
	 *
	 * [SB0002] Inserir Ordem de Servi�o na Programa��o 
	 * @author S�vio Luiz
	 * @date 19/07/2011
	 */
	public ProgramacaoRoteiro pesquisarProgramacaoRoteiro(Integer idUnidadeOrganizacional,Date dataProgramacao)
	throws ErroRepositorioException;
	
	/**
	 * [UC1190] Programa��o Autom�tica do Roteiro para Acompanhamento das OS
	 *
	 * [SB0002] Inserir Ordem de Servi�o na Programa��o 
	 * @author S�vio Luiz
	 * @date 19/07/2011
	 */
	public void atualizarIndicadorOSProgramada(Integer idOrdemServico)
	throws ErroRepositorioException;
	
	/**
	 * [UC1190] Programa��o Autom�tica do Roteiro para Acompanhamento das OS
	 *
	 * [SB0002] Inserir Ordem de Servi�o na Programa��o 
	 * @author S�vio Luiz
	 * @date 19/07/2011
	 */
    public void excluirOSProgramadas(Integer idUnidadeOrganizacional,Date dataProgramacao)
    	throws ErroRepositorioException;

    /**
	 * [UC0412] Manter Tipo de Servi�o
	 * 
	 * Metodo respons�vel por deletar motivos de
	 * encerramento a partir de um tipo de servi�o  
	 * 
	 * @author Raimundo Martins
	 * @date 26/07/2011
	 * 
	 * @param idServicoTipo
	 * 
	 */	
	public void removerServicoTipoMotivoEncerramento(Integer idServicoTipo) throws ErroRepositorioException;
	
	/**
	 * [UC1190] Programa��o Autom�tica do Roteiro para Acompanhamento das OS
	 *
	 * [SB0003] Programa��o Autom�tica das Ordens de Servi�o por Prioridade 
	 * @author S�vio Luiz
	 * @date 19/07/2011
	 */
	public void atualizarFatorPrioridadeOS(Integer idOrdemServico,Integer fatorPrioridade) throws ErroRepositorioException;
	
	/**
	 * [UC1190] Programa��o Autom�tica do Roteiro para Acompanhamento das OS
	 *
	 * [SB0003] Programa��o Autom�tica das Ordens de Servi�o por Prioridade 
	 * @author S�vio Luiz
	 * @date 19/07/2011
	 */
	public Integer pesquisarQuantidadeRAReativacao(Integer idRA) throws ErroRepositorioException;
	
	/**
	 * [UC1190] Programa��o Autom�tica do Roteiro para Acompanhamento das OS
	 *
	 * @author S�vio Luiz
	 * @date 30/07/2011
	 */
	public Collection pequisarUnidadesOrganizacionaisdasEquipes()
			throws ErroRepositorioException;
    
    /**
   	 * [UC1199] Acompanhamento de Arquivos de Roteiro
   	 * 
   	 * 
   	 * 
   	 * @author Th�lio Ara�jo
   	 * @date 28/07/2011
   	 * 
   	 * @param ids
   	 * @param situacaoNova
   	 */
     public void atualizarArquivoTextoAcompArqRoteiro(Integer id, Integer situacaoNova) throws ErroRepositorioException;
	public Collection<Object[]> pesquisarOSProgramacaoAutomatica(Integer idUnidadeOrganizacional)
	throws ErroRepositorioException;
     
     
     /**
 	 * [UC1192] Movimentar OS Seletiva de Inspe��o de Anormalidade
 	 * Verificar se ordem de servi�o que faz parte do comando ja esta encerrada
 	 * 
 	 * @author Vivianne Sousa
 	 * @date 02/08/2011
 	 */
 	public Collection verificaSeOSJaEncerrada(List<Integer> numerosOSPesquisar) throws ErroRepositorioException;
 	
    /**
     * [UC1184] - Gerar Arquivo para Acompanhar o Servi�o do Roteiro Programado
     * 
     * Exclue o arquivo de uma equipe para uma determinada data
     * 
     * 
     * @date 03/08/2011
     * @author Bruno Barros
     * 
     * @param idEquipe
     * @param dataProgramacao
     * @throws ErroRepositorioException
     */
	public void excluirDadosArquivoAcompanharServicoRoteiroProgramado(Integer idArquivoTexto)
		throws ErroRepositorioException; 	
	
    /**
     * [UC1184] - Gerar Arquivo para Acompanhar o Servi�o do Roteiro Programado
     * 
     * Exclue o as os programadas
     * 
     * 
     * @date 03/08/2011
     * @author Bruno Barros
     * 
     * @param idEquipe
     * @param dataProgramacao
     * @throws ErroRepositorioException
     */
	public void excluirOSProgramadasAcompanharServicoRoteiroProgramado( Integer id )
		throws ErroRepositorioException;
	
    /**
     * [UC1184] - Gerar Arquivo para Acompanhar o Servi�o do Roteiro Programado
     * 
     * Exclue o as os programadas
     * 
     * 
     * @date 03/08/2011
     * @author Bruno Barros
     * 
     * @param idEquipe
     * @param dataProgramacao
     * @throws ErroRepositorioException
     */
	public void excluirOrdemServicoAtividadeAcompanharServicoRoteiroProgramado( Integer id )
		throws ErroRepositorioException;
	
	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Servi�o do Roteiro Programado
	 * 
	 * Pesquisa as OS que foram enviadas para uma equipe em uma
	 * determinada data
	 * 
	 * @author Bruno Barros
	 * @date 06/07/2011
	 * 
	 * @param idEquipe - id da equipe que ter� as os incluidas 
	 * 
	 * @return Collection<OSProgramacao> - Cole��o com todos as ordens de servico
	 * a serem incluidas na programa��o
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OrdemServicoProgramacao> 
		pesquisarOSEnviadas( Integer idUnidadeOrganizacional, Date dataProgramacao ) 
			throws ErroRepositorioException;
	
	public Collection<Integer> pesquisarIdsEquipesPorUnidadeOrganizacional(Integer idUnidadeOrganizacional) throws ErroRepositorioException;
	
	/**
	 * [UC1199] � Acompanhar Arquivos de Roteiro
	 * 
	 * Pesquisa a(s) equipe(s) da OS Programacao Acompanhamento Servico 
	 * filtrando pelo identificador da Ordem de Servi�o
	 * 
	 * @author Raimundo Martins
	 * @date 09/08/2011
	 * 
	 * @param idOrdemServi�o
	 * @return Collection<Integer>
	 * @throws ErroRepositorioException
	 */
	
	public Collection<Integer> pesquisarEquipeOSProgramacaoAcompServicoPorIdOrdemServico(Integer idOrdemServico) throws ErroRepositorioException;
	
	/**
	 * [UC1190] Programa��o Autom�tica do Roteiro para Acompanhamento das OS
	 *
	 * [SB0002] Inserir Ordem de Servi�o na Programa��o 
	 * @author S�vio Luiz
	 * @date 19/07/2011
	 */
	public short pesquisarMaiorSequencialOSProgramacao(Integer idProgramacaoRoteiro,Integer idEquipe)
	throws ErroRepositorioException;
	
	/**
	 * [UC1213] Emitir Relatorio de Ordem de Servico de Fiscalizacao
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 06/08/2011
	 * 
	 * @throws ErroRepositorioException
	 */	
	public Collection pesquisarOrdensServicoFiscalizacao(int tipoRelatorio, String periodoInicial, String periodoFinal, String idGerenciaRegional, String idUnidadeNegocios,String idLocalidadeInicial, String idLocalidadeFinal, String situacaoOS, String idOSReferidaRetornoTipo, String aceitacaoDaOS) throws ErroRepositorioException;
	
	/**
	 * [UC1205] � Remanejar Ordem de Servico
	 * 
	 * Pesquisa OS Programacao Acompanhamento Servico por Equipe
	 * 
	 * @author Th�lio Ara�jo
	 * @date 22/08/2011
	 * 
	 * @param idArqTextoAcompServico
	 * @return Date - data da OS Programacao Acompanhamento Servico
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOSProgramacaoAcompServicoPorEquipeOS(
			Integer idOrdemServico, Date dataProgramacao, Integer idEquipe) throws ErroRepositorioException;
	
	/** --
	 * [UC1190] Programa��o Autom�tica do Roteiro para Acompanhamento das OS
	 *
	 * @author S�vio Luiz
	 * @date 19/07/2011
	 */
	public Integer pesquisarOSAcompServicoAtual(Integer idOrdemServico,Date dataProgramacao)
	throws ErroRepositorioException;
	
	/**
	 * [UC1213] Emitir Relatorio de Ordem de Servico de Fiscalizacao
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 06/08/2011
	 * 
	 * @throws ErroRepositorioException
	 */	
	public OrdemServico pesquisarOrdemServicoFiscalizada(Integer idOrdemServico) throws ErroRepositorioException;
	
	/** --
	 * [UC1190] Programa��o Autom�tica do Roteiro para Acompanhamento das OS
	 *
	 * @author S�vio Luiz
	 * @date 19/07/2011
	 */
	public Integer pesquisarOSProgramacaoAtual(Integer idOrdemServico,Date dataProgramacao)
	throws ErroRepositorioException;
	
	/**
	 * [UC1199] Acompanhamento de Arquivos de Roteiro
	 * 
	 * @author Th�lio Ara�jo
	 * @date 27/08/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OSProgramacaoAcompanhamentoServico> pesquisarOSProgramacaoAcompArquivoComDataRoteiroIdEquipe(
			Date dataRoteiro, Integer idArquivo) throws ErroRepositorioException;
	
	/**
	 * [UC1199] Acompanhamento de Arquivos de Roteiro
	 * 
	 * @author Th�lio Ara�jo
	 * @date 27/08/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OSProgramacaoAcompanhamentoServico> pesquisarOSProgramacaoAcompServicoComSequencialMaior(
			Integer numeroOS, Date dataRoteiro, Integer idArquivo,
			short sequencialReferencia) throws ErroRepositorioException;
	
	/**
	 * [UC1205] - Remanejar ordem de servico
	 * 
	 * Pesquisa as OS que ainda n�o foram enviadas para uma equipe em uma
	 * determinada data
	 * 
	 * @author Th�lio Ara�jo
	 * @date 06/07/2011
	 * 
	 * @param idEquipe - id da equipe que ter� as os incluidas 
	 * 
	 * @return Collection<OSProgramacao> - Cole��o com todos as ordens de servico
	 * a serem incluidas na programa��o
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OrdemServicoProgramacao> 
	pesquisarOSNaoEnviadasRemanejadas( Integer idEquipe, Date dataProgramacao, Integer idOrdemServico ) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1199] - Acompanhamento de Arquivos de Roteiro
	 * 
	 * Pesquisa os id's das equipes que ainda possuem OS, para a data
	 * informada, que ainda n�o foram encaminhadas para o campo.
	 * 
	 * @author Th�lio Ara�jo
	 * @date 06/07/2011
	 * 
	 * @param dataRoteiro - Data para a pesquisa das OS 
	 * 
	 * @return Collection<Integer> - Cole��o com todos os ID's das equipes.
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<Equipe> 
		pesquisarEquipesOSNaoEnviadasProgramadas(Integer idUnidadeLotacao, Date dataRoteiro, Integer idEquipe ) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1199] - Acompanhamento de Arquivos de Roteiro
	 * 
	 * Pesquisa o Arquivo Texto do Acompanhamento de Servi�o por Equipe e Data Roteiro
	 * 
	 * @author Th�lio Ara�jo
	 * @date 06/07/2011
	 * 
	 * @param idEquipe - Identificador da equipe
	 * @param dataRoteiro - Data do roteiro a ser pesquisado
	 * 
	 * @return Integer - Numero do imei da equipe informada
	 * 
	 * @throws ErroRepositorioException
	 */
	public ArquivoTextoAcompanhamentoServico 
		pesquisarArquivoTextoAcompanhamentoServicoEquipe( 
			Integer idEquipe,
			Date dataRoteiro) 
			throws ErroRepositorioException;


	public Collection<OsSeletivaVisitaCampo> pesquisarOsVisitaCampo(Integer idLeiturista) throws ErroRepositorioException;

	
	/**
	 * [UCXXX] - Retornar Mensagem Cadastrada para Equipe
	 * 
	 * @author Th�lio Ara�jo
	 * @date 08/09/2011
	 * 
	 * @param idArquivo - id do Arquivo a ser pesquisada a mensagem
	 * 
	 * @return MensagemAcompanhamentoServico - Objeto de Mensagem
	 * 
	 * @throws ErroRepositorioException
	 */
	public MensagemAcompanhamentoServico 
		retornaMensagemAcompanhamentoArquivosRoteiroImei( 
			Integer idArquivo)
			throws ErroRepositorioException;
	

	/**
	 * [UC1224] - Consultar Arquivo Ordens de Servi�o de Visita
	 * 
	 * @author Hugo Azevedo
	 * @date 15/09/2011
	 *
	 */
	public Collection obterColecaoSituacaoArquivoTexto() throws ErroRepositorioException;
	
	
	/**
	 * [UC1224] - Consultar Arquivo Ordens de Servi�o de Visita
	 * 
	 * @author Hugo Azevedo
	 * @date 15/09/2011
	 *
	 */
	public Collection obterColecaoAgenteComercial() throws ErroRepositorioException;

	
	/**
	 * [UC1224] - Consultar Arquivo Ordens de Servi�o de Visita
	 * 
	 * @author Hugo Azevedo
	 * @date 15/09/2011
	 *
	 */
	public Collection buscarColecaoArquivoTextoOSVisita(String idLocalidade,
			String idSetorComercialInicial, String idSetorComercialFinal,
			String idQuadraInicial, String idQuadraFinal, String idLeiturista,
			String idSituacaoArquivo)  throws ErroRepositorioException;

	
	/**
	 * [UC1224] - Consultar Arquivo Ordens de Servi�o de Visita
	 * 
	 * @author Hugo Azevedo
	 * @date 21/09/2011
	 *
	 */
	public void atualizarListaArquivoTextoOSVisita(
			Collection<ArquivoTxtOSVisitaHelper> colecaoArquivoTextoOSVisita,
			Integer idSituacaoLeituraNova, Leiturista leiturista,
			Date date)  throws ErroRepositorioException;
	
	/**
	 * [UC1224] - Consultar Arquivo Ordens de Servi�o de Visita
	 * 
	 * @author Hugo Azevedo
	 * @date 21/09/2011
	 *
	 */
	
	public boolean verificarArquivoTextoOSPendente(Integer idArquivoTextoVisitaCampo) throws ErroRepositorioException;
	
	/**
	 * [UCXXX] - Retornar Mensagem Cadastrada para Equipe
	 * 
	 * Pesquisar o id do Arquivo Texto do Acompanhamento de Servi�o
	 * 
	 * @author Th�lio Ara�jo
	 * @date 15/09/2011
	 * 
	 * @param imei
	 * @param dataRoteiro - Data do roteiro a ser pesquisado
	 * 
	 * @return Integer - idArquivo
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer 
		pesquisarIdArquivoTextoAcompanhamentoServicoImei( 
			long imei) 
			throws ErroRepositorioException;
	
	public void atualizarSituacaoArquivoTextoAcompanhamentoServico( long equipe, short situacao ) throws ErroRepositorioException;
	
	public void atualizarSituacaoProgramacaoOrdemServico( int numeroOS, short situacao ) throws ErroRepositorioException;
	
	/**
	 * [UC1193] - Consultar Comandos de OS Seletiva de Inspe��o de Anormalidade
	 * 
	 * @author Paulo Diniz
	 * @date 13/09/2011
	 * 
	 * @param idComandoOrdemSeletiva
	 * 
	 * @return ArquivoTextoVisitaCampo
	 * 
	 * @throws ErroRepositorioException
	 */
	public int pesquisarArqvTxtVisitaCampoNaoFinalizadoPorComandoOrdemSeletiva(Integer idComandoOrdemSeletiva) throws ErroRepositorioException;
	
	/**
	 * [UC1193] - Consultar Comandos de OS Seletiva de Inspe��o de Anormalidade
	 * 
	 * @author Paulo Diniz
	 * @date 13/09/2011
	 * 
	 * @param idComandoOrdemSeletiva
	 * 
	 * @return ArquivoTextoVisitaCampo
	 * 
	 * @throws ErroRepositorioException
	 */
	public int pesquisarArqvTxtVisitaCampoPorComandoOrdemSeletiva(Integer idComandoOrdemSeletiva) throws ErroRepositorioException;

	/**
	 * [UC1227] Atualizar Ordens Servi�o de Acompanhamento de Celular
	 * 
	 * @autor S�vio Luiz
	 * @date 22/09/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OSProgramacaoAcompanhamentoServico> pesquisarOSProgramacaoAcompServicoPelaDataProgramacao(
			Date dataRoteiro,Integer idEquipe, Integer idUnidadeOrganizacional) throws ErroRepositorioException;
	
	/**
	 * [UC1227] Atualizar Ordens Servi�o de Acompanhamento de Celular
	 * 
	 * @autor S�vio Luiz
	 * @date 22/09/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OSAtividadeProgramacaoAcompanhamentoServico> pesquisarOSAtividadeProgramacaoAcompServico(
			Integer idOSProgramacaoAcompServico) throws ErroRepositorioException;
	
	/**
	 * [UC1227] Atualizar Ordens Servi�o de Acompanhamento de Celular
	 * 
	 * @autor S�vio Luiz
	 * @date 22/09/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OSAtividadeMaterialProgramacaoAcompanhamentoServico> pesquisarOSAtividadeMaterialProgramacaoAcompanhamentoServico(
			Integer idOSAtividadeProgramacaoAcompanhamentoServico) throws ErroRepositorioException ;
	
	/**
	 * [UC1227] Atualizar Ordens Servi�o de Acompanhamento de Celular
	 * 
	 * @autor S�vio Luiz
	 * @date 22/09/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOsAtivMaterialExecucao(
			Integer idOrdemServicoAtividade) throws ErroRepositorioException;
	
	/**
	 * [UC1227] Atualizar Ordens Servi�o de Acompanhamento de Celular
	 * 
	 * @autor S�vio Luiz
	 * @date 22/09/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<OSAtividadeExecucaoAcompanhamentoServico> pesquisarOSAtividadeExecucaolProgramacaoAcompanhamentoServico(
			Integer idOSAtividadeProgramacaoAcompanhamentoServico) throws ErroRepositorioException;
	
	/**
	 * [UC1227] Atualizar Ordens Servi�o de Acompanhamento de Celular
	 * 
	 * @autor S�vio Luiz
	 * @date 22/09/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOsAtivPeriodoExecucao(
			Integer idOrdemServicoAtividade) throws ErroRepositorioException;
	
	/**
	 * [UC1227] Atualizar Ordens Servi�o de Acompanhamento de Celular
	 * 
	 * @autor S�vio Luiz
	 * @date 22/09/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarCodigoSituacaoOS(Integer numeroOS, short situacao) throws ErroRepositorioException;
	
	/**
	 * [UC1190] Programa��o Autom�tica do Roteiro para Acompanhamento das OS
	 *
	 * @author S�vio Luiz
	 * @date 19/07/2011
	 */
	public Collection<OSProgramacaoAcompanhamentoServico> pesquisarOSAcompServicoNaoENcerradasMotivo(Integer idUnidadeOrganizacional)
	throws ErroRepositorioException;
	
	/**
	 * [UC1225] Incluir Dados Acompanhamento de Servi�o
	 * 
	 * [FS0001] - Verificar exist�ncia do n�mero da ordem de servi�o
	 * 
	 * Caso o numero da ordem de servi�o n�o exista na tabela ORDEM_SERVICO,
	 * exibir a mensagem ?Ordem de Servi�o inexistente: <<n�mero da Ordem de Servi�o>>?
	 *
	 * e retornar para pr�ximo tipo 1 do arquivo de retorno.
	 * 
	 * @author Th�lio Ara�jo
	 * @date 23/09/2011
	 * 
	 * @param helperLaco
	 * @throws ControladorException
	 */
	public boolean verificarExistenciaOrdemServico(
			Integer idOrdemServico) throws ErroRepositorioException;
	
	/**
	 * [UC1225] Incluir Dados Acompanhamento de Servi�o
	 * 
	 * Pesquisa OS Programacao Acompanhamento Servico por idOs e dataProgramacao
	 * 
	 * @author Th�lio Ara�jo
	 * @date 23/09/2011
	 * 
	 * @param idOs, dataProgramacao
	 * @return boolean - data da OS Programacao Acompanhamento Servico
	 * @throws ControladorException
	 */
	public OSProgramacaoAcompanhamentoServico pesquisarOSProgramacaoAcompServicoPorIdOs(
			Integer idOrdemServico, Date dataProgramacao) throws ErroRepositorioException;
	
	/**
	 * [UC1225] Incluir Dados Acompanhamento de Servi�o
	 * 
	 * Pesquisa OS Atividade Programacao Acompanhamento Servico por idOs, dataProgramacao, idAtividade 
	 * 
	 * @author Th�lio Ara�jo
	 * @date 23/09/2011
	 * 
	 * @param idOrdemServico, dataProgramacao, idAtividade
	 * @return Collection<OSAtividadeProgramacaoAcompanhamentoServico>
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarOSAtividadeProgramacaoAcompServicoPorIdOs(
			Integer idOrdemServico, Date dataProgramacao, Integer idAtividade) throws ErroRepositorioException;
	
	/**
	 * [UC1225] Incluir Dados Acompanhamento de Servi�o
	 * 
	 * Excluir os dados da OSAtividadeExecucaoAcompanhamentoServico para cada id da tabela
	 * OsAtividadeProgramcaoAcompanhamentoServico
	 * 
	 * @author Th�lio Ara�jo
	 * @date 26/09/2011
	 * 
	 * @param Collection<Integer> isOsAtividadeProgramacaoAcompanhamentoServico
	 * @throws ErroRepositorioException
	 */
	public void excluirOsAtividadeExecucaoAcompahamentoServico(Collection<Integer> isOsAtividadeProgramacaoAcompanhamentoServico) throws ErroRepositorioException;
	
	/**
	 * [UC1225] Incluir Dados Acompanhamento de Servi�o
	 * 
	 * Excluir os dados da tabela OSAtividadeMaterialProgramacaoAcompanhamentoServico para cada id da tabela
	 * OsAtividadeProgramcaoAcompanhamentoServico
	 * 
	 * @author Th�lio Ara�jo
	 * @date 26/09/2011
	 * 
	 * @param Collection<Integer> isOsAtividadeProgramacaoAcompanhamentoServico
	 * @throws ErroRepositorioException
	 */
	public void excluirOsAtividadeMaterialProgramacaoAcompahamentoServico(Collection<Integer> isOsAtividadeProgramacaoAcompanhamentoServico) throws ErroRepositorioException;
	
	/**
	 * [UC1225] Incluir Dados Acompanhamento de Servi�o
	 * 
	 * Excluir os dados da tabela OSAtividadeProgramacaoAcompanhamentoServico para cada id da tabela
	 * OsAtividadeProgramcaoAcompanhamentoServico
	 * 
	 * @author Th�lio Ara�jo
	 * @date 26/09/2011
	 * 
	 * @param Collection<Integer> isOsAtividadeProgramacaoAcompanhamentoServico
	 * @throws ErroRepositorioException
	 */
	public void excluirOsAtividadeProgramacaoAcompahamentoServico(Collection<Integer> isOsAtividadeProgramacaoAcompanhamentoServico) throws ErroRepositorioException;
	
	/**
	 * [UC0461] Manter Dados das Atividades da Ordem de Servi�o
	 * 
	 * @author S�vio Luiz
	 * @date 19/09/2006
	 * 
	 * @param numeroOS
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> obterEquipesPorUnidadeProgramacaoAutomatica(Integer idUnidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC1225] Incluir Dados Acompanhamento de Servi�o
	 * 
	 * Pesquisa OS Programacao Acompanhamento Servico por idOs e idArquivoTexto
	 * 
	 * @author Th�lio Ara�jo
	 * @date 04/10/2011
	 * 
	 * @param idOs, idArquivoTexto
	 * @return OSProgramacaoAcompanhamentoServico
	 * @throws ControladorException
	 */
	public OSProgramacaoAcompanhamentoServico pesquisarOSProgramacaoAcompServicoPorIdArqTxt(
			Integer idOrdemServico, Integer idArqTexto) throws ErroRepositorioException;
			
	/**
	 * [UC1221] - Gerar Boletim de Medi��o Ordem de Servi�o Inspe��o Anormalidade
	 * 
	 * M�todo que vai retornar as ordens de servi�o geradas a partir de um comando
	 * de ordem de servi�o seletiva do tipo de inspe��o de anormalidade gerado.
	 * 
	 * @author Diogo Peixoto
	 * @since 14/09/2011
	 * 
	 * @param FiltrarOrdensServicosComandoOrdemSeletivaHelper
	 * @return List<Object[]>
	 * @throws ErroRepositorioException
	 */
	public List<Object[]> pesquisarRelatorioOrdensServicosComandoOrdemSeletiva(FiltrarOrdensServicosComandoOrdemSeletivaHelper helper) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1221] - Gerar Boletim de Medi��o Ordem de Servi�o Inspe��o Anormalidade
	 * 
	 * M�todo que vai retornar as ordens de servi�o geradas a partir de um comando
	 * de ordem de servi�o seletiva do tipo de inspe��o de anormalidade gerado.
	 * 
	 * @author Davi Menezes
	 * @since 28/10/2011
	 * 
	 * @param FiltrarOrdensServicosComandoOrdemSeletivaHelper
	 * @return List<Object[]>
	 * @throws ErroRepositorioException
	 */
	public List<Object[]> pesquisarRelatorioOrdensServicosAtualizacaoCliente(FiltrarOrdensServicosComandoOrdemSeletivaHelper helper) 
		throws ErroRepositorioException;

	/**
	 * M�todo que pesquisa a Leitura Anormalidade de uma
	 * Ordem de Servi�o
	 *  
	 * [UC 1220] Gerar Arquivo Texto para as Os de Visita
	 * 
	 * @author Raimundo Martins
	 * @date 21/09/2011
	 * 
	 * @return Objeto Leitura Anormalidade
	 */
	
	public LeituraAnormalidade pesquisarLeituraAnormalidade(Integer idOrdemServico) throws ErroRepositorioException;
	
	/**
	 * M�todo que retorna a quantidade de imoveis
	 * daquele comando, localidade, setor e quadra
	 *  
	 * [UC 1223] Gerar Relat�rio Quantitativo dos 
	 * Im�veis das Ordens Seletivas
	 * 
	 * @author Raimundo Martins
	 * @date 26/09/2011
	 * 
	 * @return Quantidade de im�veis encontrados
	 */
	
	public Integer pesquisarQuantidadeImoveisOrdensSeletivas(String comando, String localidade, String setor, String quadra) 
			throws ErroRepositorioException;
	
	/**
	 * M�todo que retorna os dados do relat�rio
	 *  
	 * [UC 1223] Gerar Relat�rio Quantitativo dos 
	 * Im�veis das Ordens Seletivas
	 * 
	 * @author Raimundo Martins
	 * @date 26/09/2011
	 * 
	 * @return Dados para preenchimento do relat�rio
	 */
	public  Collection<Object[]> gerarRelatorioQuantImovOSSeletivaBean (String comOrdemSeletiva, String local, 
		String setorInicial, String setorFinal, String quadraIni, String quadraFin) throws ErroRepositorioException;

	/**
	 * [UC1228] Consultar Ordens de Servi�o do Arquivo Texto
	 * 
	 * Verifica se a OS j� foi encerrada
	 * 
	 * @author Mariana Victor
	 * @date 04/10/2011
	 */
	public Boolean verificarOSEncerrada(Integer idOrdemServico) throws ErroRepositorioException;
	
	/**
	 * [UC1224] - Consultar Arquivo Ordens de Servi�o de Visita
	 * 
	 * @author Hugo Azevedo
	 * @date 15/09/2011
	 *
	 */
	public SetorComercial obterSetorComercialCodigo(String codigo) throws ErroRepositorioException;
	
	
	/**
	 * [UC1224] - Consultar Arquivo Ordens de Servi�o de Visita
	 * 
	 * @author Hugo Azevedo
	 * @date 15/09/2011
	 *
	 */
	public Quadra obterQuadraNumero(String numero) throws ErroRepositorioException;
	
	/**
	 * [UC1213] Emitir Relatorio de Ordem de Servico de Fiscalizacao
	 * 
	 * @author Mariana Victor
	 * @data 11/10/2011
	 */
	public Collection<Object[]> pesquisarDadosRelatorioSinteticoOSFiscalizacao(String periodoInicial, 
			String periodoFinal, String idGerenciaRegional, String idUnidadeNegocio,String idLocalidadeInicial, 
			String idLocalidadeFinal, String situacaoOS, String idOSReferidaRetornoTipo, String aceitacaoDaOS) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1213] Emitir Relatorio de Ordem de Servico de Fiscalizacao
	 * 
	 * @author Mariana Victor
	 * @data 11/10/2011
	 */
	public Collection<Object[]> pesquisarMotivosEncerramentoPorLocalidadeAnoMes(
			Date anoMes, Integer idLocalidade) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1213] Emitir Relatorio de Ordem de Servico de Fiscalizacao
	 * 
	 * @author Mariana Victor
	 * @data 11/10/2011
	 */
	public Collection<Object[]> pesquisarTiposRetornoPorLocalidadeAnoMes(
			Date anoMes, Integer idLocalidade) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1213] Emitir Relatorio de Ordem de Servico de Fiscalizacao
	 * 
	 * 
	 * @author Mariana Victor
	 * @date 11/10/2011
	 * 
	 * @throws ErroRepositorioException
	 */	
	public Integer pesquisarQuantidadeOrdensServicoFiscalizacao(String periodoInicial, String periodoFinal, 
			String idGerenciaRegional, String idUnidadeNegocio,String idLocalidadeInicial, 
			String idLocalidadeFinal, String situacaoOS, String idOSReferidaRetornoTipo, String aceitacaoDaOS) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1184] - Gerar Arquivo para Acompanhar o Servi�o do Roteiro Programado
	 * 
	 * 
	 * @author S�vio Luiz
	 * @date 06/07/2011
	 * 
	 * @param idEquipe - Identificador da equipe
	 * @param dataRoteiro - Data para a pesquisa do arquivo
	 * 
	 * 
	 * @throws ErroRepositorioException
	 */
	public ArquivoTextoAcompanhamentoServico 
	pesquisarArquivoTextoAcompanhamentoServicoPorImei( 
			BigDecimal imei, 
			Date dataRoteiro,
			Integer idSituacaoTransmissaoLeitura) 
			throws ErroRepositorioException;

	/**
	 * [UC1249] � Filtro OS Encerradas por Acompanhamento de Servi�o
	 * 
	 * Filtra OSs encerradas por Acompanhamento de Servi�o
	 * 
	 * @author Fernanda Almeida
	 * @date 31/10/2011
	 * 
	 * @param pesquisarFiscalizarOSAcompanhamentoHelper
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarFiscalizarOSAcompanhamento(PesquisarFiscalizarOSAcompanhamentoHelper filtro) throws ErroRepositorioException;; 
	
	/**
	 * [UC1205] � Remanejar Ordem de Servico
	 * 
	 * Pesquisa OS Programacao Acompanhamento Servico por Equipe
	 * 
	 * @author Th�lio Ara�jo
	 * @date 22/08/2011
	 * 
	 * @param idArqTextoAcompServico
	 * @return Date - data da OS Programacao Acompanhamento Servico
	 * @throws ErroRepositorioException
	 */
	public OSProgramacaoAcompanhamentoServico pesquisarOSProgramacaoAcompServicoPorArquivoOS(
			Integer idOrdemServico, Date dataProgramacao, Integer idArquivo) throws ErroRepositorioException;

	/**
	 *  [UC1249] Filtro OS Encerradas por Acompanhamento de Servi�o
	 * 
	 * Atualizar Indicardor de validacao da OS validada
	 * 
	 * @author Fernanda Almeida
	 * @date 09/11/2011
	 * 
	 * @param idOS
	 * @throws ErroRepositorioException
	 */
	public void atualizarIndicadorValidaOSProg(Integer idOS)  throws ErroRepositorioException;
	
	/**
	 *  [UC1249] Filtro OS Encerradas por Acompanhamento de Servi�o
	 * 
	 * Pesquisar OSs por ID do Registro de Atendimento
	 * 
	 * @author Fernanda Almeida
	 * @date 10/11/2011
	 * 
	 * @param idRA
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarOSProgramacaoASPorIdRA(
			Integer idRA) throws ErroRepositorioException ;
	
	/**
	 * [UC1190] Programa��o Autom�tica do Roteiro para Acompanhamento das OS
	 *
	 *  
	 * @author S�vio Luiz
	 * @date 19/07/2011
	 */
	public Integer pesquisarTempoMedioOSProgramacaoComDataRoteiroPorUnidade(Date dataProgramacao,Integer idUnidadeOrganizacional) throws ErroRepositorioException;
	
	/**
	 * [UC1190] Programa��o Autom�tica do Roteiro para Acompanhamento das OS
	 *
	 *  
	 * @author S�vio Luiz
	 * @date 19/07/2011
	 */
	public Integer obterCargaTrabalhoPorUnidadeProgramacaoAutomatica(Integer idUnidade)
			throws ErroRepositorioException; 
	
	/**
	 * [UC1190] Programa��o Autom�tica do Roteiro para Acompanhamento das OS
	 *
	 *  
	 * @author S�vio Luiz
	 * @date 19/07/2011
	 */
	public Object[] pesquisarDadosRAComDataRoteiroPorEquipe(Date dataProgramacao,Integer idEquipe) throws ErroRepositorioException; 
	
	/**
	 * [UC1190] Programa��oo Autom�tica do Roteiro para Acompanhamento das OS
	 *
	 * @author S�vio Luiz
	 * @date 19/07/2011
	 */
	public void atualizarDistanciaCoordenadasOS(Collection<OrdemServico> colecaoOS) throws ErroRepositorioException;
	
	/**
	 * [UC1190] Programa��oo Autom�tica do Roteiro para Acompanhamento das OS
	 *
	 * @author S�vio Luiz
	 * @date 30/11/2011
	 */
	public Collection<OrdemServico> pesquisarOSDistanciaCoordenadas(Collection<OrdemServico> idsOrdemServico)
	throws ErroRepositorioException;
	
	/** 
	 * [UC1190] Programa��o Autom�tica do Roteiro para Acompanhamento das OS
	 * @author S�vio Luiz
	 * @date 19/07/2011
	 */
	public OrdemServico pesquisarOSFatorPrioridadeDecrescente(Collection<OrdemServico> collOrdemServico)
	throws ErroRepositorioException;
	
	/**
	 * [UC1190] Programa��oo Autom�tica do Roteiro para Acompanhamento das OS
	 *
	 * @author S�vio Luiz
	 * @date 19/07/2011
	 */
	public void atualizarIndicadorProgramacaoOS(Integer idOrdemServico) throws ErroRepositorioException;
	
	
	/**
	 * [UC1190] Programa��o Autom�tica do Roteiro para Acompanhamento das OS
	 *
	 * @author S�vio Luiz
	 * @date 19/07/2011
	 */
	public Collection<Integer> pesquisarEquipesMesmoBairro(Date dataProgramacao,Integer idUnidadeOrganizacional,Integer idBairro)
	throws ErroRepositorioException;
	
	/**
	 * [UC1190] Programa��o Autom�tica do Roteiro para Acompanhamento das OS
	 *
	 * @author S�vio Luiz
	 * @date 19/07/2011
	 */
	public Object[] pesquisarEquipeMaiorCargaHoraria(Date dataProgramacao,Collection<Integer> idsEquipes)
	throws ErroRepositorioException;

	
	/**
	 * 
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspe��o de Anormalidade
	 * 
	 * @author Raimundo Martins
	 * @since 07/11/2011
	 *  
	 * @param comandoEncerrado - Caso a pesquisa retorne apenas os comandos encerrados.
	 **/
	public Collection<Object[]> pesquisarDadosComandoOSSeletivaResumido(
			Integer idEmpresa, Date dataInicial, Date dataFinal,
			int numeroIndice,int quantidadeRegistros, boolean comandoEncerrado, 
			Integer idLocalidadeInicial, Integer idLocalidadeFinal, 
			Integer idSetorComercialInicial, Integer idSetorComercialFinal,
			Integer matriculaImovel, Integer idOrdemServico) throws ErroRepositorioException;
	
	/**
	 * [UC1193] Consultar Comandos de OS Seletiva de Inspe��o de Anormalidade
	 * 
	 * @author Raimundo Martins
	 * @since 08/11/2011
	 */
	public Integer pesquisarDadosComandoOSSeletivaCount(
			Integer idEmpresa, Date comandoInicial, Date comandoFinal, Integer idLocalidadeInicial, Integer idLocalidadeFinal, 
			Integer idSetorInicial, Integer idSetorFinal, Integer matriculaImovel, Integer idOrdemServico)
			throws ErroRepositorioException;

	public Collection<RegistroTipo14Helper> obterRegistroTipo14(Integer idOS) throws ErroRepositorioException;
	
	/**
	 * RM1165 - Registrar em tabela os par�metros que foram utilizados para calcular o valor do 
	 *          d�bito a cobrar gerado decorrente da situa��o de fiscaliza��o informada
	 * UC0488 - Informar Retorno Ordem de Fiscaliza��o
	 * Analista: Cl�udio Lira
	 * 
	 * [SB0012] - Registrar  par�metros utilizados para calcular o valor do d�bito
	 * 
	 * Pesquisar o maior ano m�s faturamento a tabela CONSUMO_HISTORICO
	 * 
	 * @autor Th�lio Ara�jo
	 * @since 13/12/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer obterMaiorAnoMesFaturamento(Integer idImovel,
			Integer ligacaoTipo)
			throws ErroRepositorioException;


	public Collection<?> pesquisarAcompanhamentoArquivosRoteiro( 
			String dataProgramacao,
			String idEmpresa,
			String idSituacao ) 
			throws ErroRepositorioException;
	
	 /**
     * [UC1184] - Gerar Arquivo para Acompanhar o Servi�o do Roteiro Programado
     * 
     * Exclue o arquivo de uma equipe para uma determinada data
     * 
     * 
     * @date 03/08/2011
     * @author Bruno Barros
     * 
     * @param idEquipe
     * @param dataProgramacao
     * @throws ErroRepositorioException
     */
	public void excluirDadosMensagemServicoAcompanharRoteiroProgramado(Integer idArquivoTexto)
			throws ErroRepositorioException;
	
	/**
	 * [UC0938] Emitir Ordem Fiscaliza��o
	 * 
	 * [FS003] Verifica se � para ser emitido o relat�rio tipo 1
	 * 
	 * @author Fernanda Almeida
	 * @date 19/12/2012
	 * 
	 */
	public Integer retornaOsPrazoIrregularidade(
			Integer idImovel, Short idSituacao) throws ErroRepositorioException; 

	
	/**
	 * [ UC0468 ] - Consultar ordem de servico
	 * Buscar fiscalizacao foto
	 * 
	 * @author Carlos Chaves
	 * @date 18/12/2012
	 */
	public Collection <FiscalizacaoFoto> buscarFiscalizacaoFoto(Integer idOS) throws ErroRepositorioException;
	
	/**	 
	 * [UC 0441] Consultar Dados da Ordem de Servi�o
	 * Metodo que pesquisa as fotos de uma determinada 	 
	 * Ordem de Servi�o	 
	 * 
	 * @author Vivianne Sousa
	 * @date 21/06/2013 
	 * */		
	public Collection<OrdemServicoFoto> buscarFotosOs(Integer idOrdemServico) throws ErroRepositorioException;	
		
	/**
	 * Corre��o RM 10043 - Encerrar RA  
	 * 
	 * @author Diogo Luiz
	 * @date 21/02/2014
	 * 
	 * @param numeroOS
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer validarParecerEncerramento(String numeroOS) throws ErroRepositorioException;

	/**
	 * [UC1177] Gerar Relat�rio de Ordens de Servi�o por Situa��o
	 * 
	 * Pesquisa os dados necess�rios para a montagem do relat�rio de OS Situa��o Anal�tico
	 * 
	 * @author Rafael Corr�a
	 * @date 09/07/2014
	 * 
	 * @param FiltrarRelatorioOSSituacaoHelper
	 * @param boletimGerado
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDadosRelatorioOSSituacaoAnalitico(FiltrarRelatorioOSSituacaoHelper helper)
		throws ErroRepositorioException;
	
	/**
	 * [UC1177] Gerar Relat�rio de Ordens de Servi�o por Situa��o
	 * 
	 * Pesquisa os dados necess�rios para a montagem do relat�rio de OS Situa��o Anal�tico quando o usu�rio seleciona apenas OS de cobran�a
	 * 
	 * @author Rafael Corr�a
	 * @date 08/07/2014
	 * 
	 * @param FiltrarRelatorioOSSituacaoHelper
	 * @param boletimGerado
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDadosRelatorioOSSituacaoCobrancaAnalitico(FiltrarRelatorioOSSituacaoHelper helper, boolean boletimGerado)
		throws ErroRepositorioException;
	
	/**
	 * [UC1177] Gerar Relat�rio de Ordens de Servi�o por Situa��o
	 * 
	 * Pesquisa os dados necess�rios para a montagem do relat�rio de OS Situa��o Sint�tico
	 * 
	 * @author Rafael Corr�a
	 * @date 09/07/2014
	 * 
	 * @param FiltrarRelatorioOSSituacaoHelper
	 * @param boletimGerado
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDadosRelatorioOSSituacaoSintetico(FiltrarRelatorioOSSituacaoHelper helper)
		throws ErroRepositorioException;
	
	/**
	 * [UC1177] Gerar Relat�rio de Ordens de Servi�o por Situa��o
	 * 
	 * Pesquisa os dados necess�rios para a montagem do relat�rio de OS Situa��o Sint�tico quando o usu�rio seleciona apenas OS de cobran�a
	 * 
	 * @author Rafael Corr�a
	 * @date 08/07/2014
	 * 
	 * @param FiltrarRelatorioOSSituacaoHelper
	 * @param boletimGerado
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDadosRelatorioOSSituacaoCobrancaSintetico(FiltrarRelatorioOSSituacaoHelper helper, boolean boletimGerado)
		throws ErroRepositorioException;

	/**
	 * [UC1290] Inserir ou Atualizar Im�vel Atualiza��o Cadastral
	 * 
	 * @author Andr� Miranda
	 * @date 14/10/2014
	 */
	public FiscalizacaoSituacao pesquisaFiscalizacaoSituacao(Integer idFiscalizacaoSituacao) throws ErroRepositorioException;
	
	/**
     * [UC1297] Atualizar Dados Im�veis Inconsist�ntes
     * [SB0018] Atualizar Situa��o da Liga��o de �gua do Im�vel;
	 * 
	 * @author Vivianne Sousa
	 * @date 11/02/2015
	 */
	public void atualizarDataCorteFiscalizarParametroCalculoDebito(Integer idOS) throws ErroRepositorioException;
	
}
