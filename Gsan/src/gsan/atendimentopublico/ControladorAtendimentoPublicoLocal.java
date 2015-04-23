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
package gsan.atendimentopublico;

import gsan.atendimentopublico.bean.AcoesParaCorrecaoAnormalidadesEncontradasHelper;
import gsan.atendimentopublico.bean.DadosLigacoesBoletimCadastroHelper;
import gsan.atendimentopublico.bean.EfetuarLigacaoAguaComInstalacaoHidrometroSemRAHelper;
import gsan.atendimentopublico.bean.IntegracaoComercialHelper;
import gsan.atendimentopublico.bean.OcorrenciaOperacionalFiltroHelper;
import gsan.atendimentopublico.bean.OcorrenciaOperacionalHelper;
import gsan.atendimentopublico.bean.OrdemServicoArquivoTextoHelper;
import gsan.atendimentopublico.bean.ProcessarAtualizacaoOSArquivoTextoHelper;
import gsan.atendimentopublico.bean.UnidadesFilhasHelper;
import gsan.atendimentopublico.ligacaoagua.CorteTipo;
import gsan.atendimentopublico.ligacaoagua.LigacaoAgua;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil;
import gsan.atendimentopublico.ordemservico.Atividade;
import gsan.atendimentopublico.ordemservico.Material;
import gsan.atendimentopublico.ordemservico.OrdemServico;
import gsan.atendimentopublico.ordemservico.OrdemServicoFoto;
import gsan.atendimentopublico.ordemservico.OsReferidaRetornoTipo;
import gsan.atendimentopublico.ordemservico.ServicoCobrancaValor;
import gsan.atendimentopublico.ordemservico.ServicoPerfilTipo;
import gsan.atendimentopublico.ordemservico.ServicoTipo;
import gsan.atendimentopublico.ordemservico.ServicoTipoBoletim;
import gsan.atendimentopublico.ordemservico.ServicoTipoPrioridade;
import gsan.atendimentopublico.ordemservico.ServicoTipoReferencia;
import gsan.atendimentopublico.ordemservico.ServicoTipoSubgrupo;
import gsan.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gsan.atendimentopublico.registroatendimento.EspecificacaoImovSitCriterio;
import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.localidade.SetorComercial;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cobranca.CobrancaAcaoAtividadeComandoFiscalizacaoSituacao;
import gsan.faturamento.ControladorFaturamentoLocal;
import gsan.faturamento.autoinfracao.AutosInfracao;
import gsan.gui.atendimentopublico.registroatendimento.FiltrarAcompanhamentoRegistroAtendimentoHelper;
import gsan.gui.relatorio.atendimentopublico.FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper;
import gsan.gui.relatorio.atendimentopublico.FiltrarRelatorioDebitosCobrancaImovelHelper;
import gsan.gui.relatorio.atendimentopublico.FiltrarRelatorioOSSituacaoHelper;
import gsan.gui.relatorio.atendimentopublico.FiltrarRelatorioTiposServicoHelper;
import gsan.micromedicao.hidrometro.HidrometroCapacidade;
import gsan.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gsan.micromedicao.medicao.MedicaoTipo;
import gsan.relatorio.atendimentopublico.RelatorioAcompanhamentoBoletimMedicaoHelper;
import gsan.relatorio.atendimentopublico.RelatorioCertidaoNegativaClienteBean;
import gsan.relatorio.atendimentopublico.RelatorioCertidaoNegativaHelper;
import gsan.relatorio.atendimentopublico.RelatorioContratoPrestacaoServicoJuridicoBean;
import gsan.relatorio.atendimentopublico.RelatorioDebitosCobradosImovelBean;
import gsan.relatorio.atendimentopublico.RelatorioOSSituacaoHelper;
import gsan.relatorio.atendimentopublico.RelatorioTiposServicoBean;
import gsan.relatorio.atendimentopublico.bean.AcessoLojaVirtualHelper;
import gsan.relatorio.atendimentopublico.ordemservico.FiltrarRelatorioReligacaoClientesInadiplentesHelper;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ControladorException;
import gsan.util.ErroRepositorioException;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 * Declaração pública de serviços do Session Bean de ControladorCliente
 * 
 * @author Sávio Luiz
 * @created 25 de Abril de 2005
 */
public interface ControladorAtendimentoPublicoLocal extends
		javax.ejb.EJBLocalObject {

	/**
	 * [UC353]Efetuar Ligação de Esgoto no sistema. [SB002] Atualizar dados do
	 * imóvel.
	 * 
	 * @author Leandro Cavalcanti
	 * @date 14/06/2006
	 * 
	 * @param ligacaoEsgoto
	 *            a ser enserido
	 * @param imovel
	 *            a ser atualizado
	 * @throws ControladorException
	 */

	public void efetuarLigacaoAgua(IntegracaoComercialHelper integracaoComercialHelper)
			throws ControladorException;

	/**
	 * [UC0367]Atualizar Ligação de Agua no sistema.
	 * 
	 * [SB002] Atualiza ligação de agua.
	 * 
	 * @author Rafael Pinto
	 * @date 20/07/2006
	 * 
	 * @param ligacaoAgua
	 * @throws ControladorException
	 */
	public void atualizarLigacaoAgua(LigacaoAgua ligacaoAgua, Usuario usuario)
			throws ControladorException;

	/**
	 * [UC353]Efetuar Ligação Esgoto no sistema.
	 * 
	 * [SB002] Atualizar dados doimóvel.
	 * 
	 * @author Leandro Cavalcanti
	 * @date 20/06/2006
	 * 
	 * @param ligacaoEsgoto
	 *            a ser enserido
	 * @param imovel
	 *            a ser atualizado
	 * @throws ControladorException
	 */
	public void inserirLigacaoEsgoto(IntegracaoComercialHelper integracaoComercialHelper)
			throws ControladorException;

	/**
	 * Este método se destina a validar todas as situações e particularidades da
	 * inserção da especificacao situacao criterio imovel.
	 * 
	 * [FS0001] Validar especificação da situaçãoo já existente [FS0002] Validar
	 * existência de hidrômetro na ligação água [FS0003] Validar existência de
	 * hidrômetro no poço
	 * 
	 * @author Rafael Pinto
	 * @date 04/08/2006
	 * 
	 * @param equipeComponentes
	 */
	public void validarExibirInsercaoEspecificacaoImovSitCriterio(
			Collection colecaoEspecificacaoImovSitCriterio,
			EspecificacaoImovSitCriterio especImovSitCriterio)
			throws ControladorException;

	/**
	 * [UC0365] Efetuar Remanejamento de Hidrômetro [SB0001] Atualizar Hitórico
	 * de instalação do hidrômetro
	 * 
	 * @author Rômulo Aurélio
	 * @date 30/06/2006
	 * 
	 * @param hidrometroInstalacaoHistorico
	 * @throws ControladorException
	 */

	public void efetuarRemanejamentoHidrometro(IntegracaoComercialHelper integracaoComercialHelper)
			throws ControladorException;

	/**
	 * [UC0365] Efetuar Retirada de Hidrômetro [SB0001] Atualizar Hitórico de
	 * instalação do hidrômetro
	 * 
	 * @author Thiago Tenório
	 * @date 30/06/2006
	 * 
	 * @param hidrometroInstalacaoHistorico
	 * @throws ControladorException
	 */
	public void efetuarRetiradaHidrometro(IntegracaoComercialHelper integracaoComercialHelper)
			throws ControladorException;

	/**
	 * [UC0362] Efetuar Instalação de Hidrômetro
	 * 
	 * [SB0001] Gerar Hitórico de instalação do hidrômetro [SB0002] Atualizar
	 * Imóvel/Ligação de Água [SB0003] Atualizar situação de hidrômetro na
	 * tabela HIDROMETRO
	 * 
	 * @author Ana Maria
	 * @date 12/07/2006
	 * 
	 * @param hidrometroInstalacaoHistorico
	 * @param materialImovel
	 * 
	 * @throws ControladorException
	 * @throws ErroRepositorioException
	 */

	public void efetuarInstalacaoHidrometro(IntegracaoComercialHelper integracaoComercialHelper) 
		throws ControladorException;

	/**
	 * 
	 * Este método se destina a validar todas as situações e particularidades da
	 * ligação de agua do imóvel no momento da exibição.
	 * 
	 * [FS0001] Verificar existência da matrícula do imóvel. [FS0002] Verificar
	 * Situação do Imovel. [FS0003] Validar Situação de Esgoto do imóvel.
	 * 
	 * @author Rafael Pinto
	 * @date 20/07/2006
	 * 
	 * @param Imovel
	 */
	/**
	 * 
	 * Este método se destina a validar todas as situações e particularidades da
	 * ligação de agua do imóvel no momento da exibição.
	 * 
	 * [FS0001] Verificar existência da matrícula do imóvel. [FS0002] Verificar
	 * Situação do Imovel. [FS0003] Validar Situação de Esgoto do imóvel.
	 * 
	 * @author Rafael Pinto
	 * @date 20/07/2006
	 * 
	 * @param Imovel
	 */
	public void validarExibirLigacaoAguaImovel(OrdemServico ordemServico,
			boolean veioEncerrarOS) throws ControladorException;

	/**
	 * [UC0356] Efetuar Mudança de Situação de Faturamento da Ligação de Esgoto
	 * 
	 * Permite Efetuar Mudança de Situação de Faturamento da Ligação de Esgoto .
	 * 
	 * [FS0001]- Validar Ordem de Serviço [FS0002] Verificar Situação do Imovel
	 * [FS0002] Verificar Situação do Imovel [FS0003]- Validar Situação da
	 * Ligação de Esgoto do imóvel * [FS0001]- Validar Ordem de Serviço [FS0002]
	 * Verificar Situação do Imovel [FS0002] Verificar Situação do Imovel
	 * [FS0003]- Validar Situação da Ligação de Esgoto do imóvel
	 * 
	 * @author Leandro Cavalcanti
	 * @date 18/07/2006
	 * 
	 * @param ordemServicoId
	 * @param imovel
	 * @param dataMudanca
	 * @param volumeMinimoFixado
	 * @param novaSituacaoEsgoto
	 * @throws ControladorException
	 */
	public void efetuarMudancaSituacaoFaturamentoLiagacaoEsgoto(IntegracaoComercialHelper integracaoComercialHelper)
			throws ControladorException;

	/**
	 * [UC0356]- Efetuar mudança de Faturamento na Ligação de Água
	 * [FS0006]-Atualizar Ligação de Esgoto
	 * 
	 * Permite atualizar a Tabele de Ligação Esdoto . Update LIGACAO_ESGOTO
	 * LESG_NNCONSUMOMINIMOESGOTO (volume mínimo fixado) LESG_TMULTIMAALTERADAO
	 * (data e hora correntes) Where LESG_ID=IMOV_ID da tabela IMOVEL
	 * 
	 * @author Leandro Cavalcanti
	 * @date 18/07/2006
	 * 
	 * 
	 * @param imovel
	 * @param volumeMinimoFixado
	 * 
	 * @throws ControladorException
	 */
	public void atualizarLigacaoEsgoto(Imovel imovel, String volumeMinimoFixado)
			throws ControladorException;

	/**
	 * [UC0356]- Efetuar mudança de Faturamento na Ligação de Água
	 * 
	 * [FS0007]- Validar Situação da Ligação de Água do imóvel
	 * 
	 * @author Leandro Cavalcanti
	 * @date 18/07/2006
	 * @param imovel
	 * @param volumeMinimoFixado
	 * 
	 * @throws ControladorException
	 */
	public String validarSituacaoAguaImovel(Imovel imovel, Integer tipoServico)
			throws ControladorException;

	/**
	 * [UC0356] Efetuar Mudança de Situação de Faturamento da Ligação de Esgoto
	 * 
	 * Permite Efetuar Mudança de Situação de Faturamento da Ligação de Esgoto .
	 * 
	 * [FS0001]- Validar Ordem de Serviço [FS0002] Verificar Situação do Imovel
	 * [FS0002] Verificar Situação do Imovel [FS0003]- Validar Situação da
	 * Ligação de Esgoto do imóvel [FS0007]- Validar Situação da Ligação de Água
	 * do imóvel
	 * 
	 * @author Leandro Cavalcanti
	 * @date 18/07/2006
	 * 
	 * @param ordemServicoId
	 * @param imovel
	 * @param dataMudanca
	 * @param volumeMinimoFixado
	 * @param novaSituacaoEsgoto
	 * @throws ControladorException
	 */
	public String validarMudancaSituacaoFaturamentoLigacaoesgotoExibir(
			OrdemServico ordemServico, boolean veioEncerrarOS ) throws ControladorException;

	/**
	 * [UC0364] Efetuar Substituição de Hidrômetro
	 * 
	 * [SB0001] Atualiza o Histórico da instalação com os dados do hidrômetro
	 * substituido [SB0002] Gerar Hitórico de instalação do hidrômetro [SB0003]
	 * Atualizar Imóvel/Ligação de Água [SB0004] Atualizar situação de
	 * hidrômetro na tabela HIDROMETRO [SB0005] Atualizar situação do hidrômetro
	 * substituido na tabela HIDROMETRO
	 * 
	 * @author Ana Maria
	 * @date 24/07/2006
	 * 
	 * @param hidrometroInstalacaoHistorico
	 * @param materialImovel
	 * @param hidrometroSubstituicaoHistorico
	 * 
	 * @throws ControladorException
	 * @throws ErroRepositorioException
	 */
	public void efetuarSubstituicaoHidrometro(IntegracaoComercialHelper integracaoComercialHelper) throws ControladorException;

	/**
	 * [UC0360]- Efetuar Supressão da Ligação de Água
	 * 
	 * [SB0001]- Atualizar Ligação de água [SB0002]- Atualizar Imóvel [SB0004]-
	 * Atualizar Histótico de Instalação de Hidrômetro
	 * 
	 * @author Rômulo Aurélio
	 * @date 28/07/2006
	 * @param imovel
	 * 
	 * @throws ControladorException
	 */
	public void efetuarSupressaoLigacaoAgua(IntegracaoComercialHelper integracaoComercialHelper)
			throws ControladorException;

	/**
	 * [UC0368] Atualizar Instalação do Hidrômetro
	 * 
	 * [FS0001] - Verificar a existência da matrícula do imóvel [FS0002] -
	 * Verificar a situação do imóvel [FS0003] - Validar existência do
	 * hidrômetro [FS0004] - Validar leitura instalação hidrômetro [FS0005] -
	 * Validar leitura retirada hidrômetro [FS0006] - Validar leitura retirada
	 * corte [FS0007] - Validar Leitura Supressão [FS0009] - Verificar sucesso
	 * da transação
	 * 
	 * @author lms
	 * @created 21/07/2006
	 * @throws ControladorException
	 * 
	 */
	public void atualizarInstalacaoHidrometro(Imovel imovel, Integer medicaoTipo,Usuario usuario)
			throws ControladorException;

	/**
	 * [UC0359] Efetuar Restabelecimento Ligação de Água
	 * 
	 * 
	 * [SB0001] Atualizar Imóvel/Ligação de Água/Ligação de Esgoto
	 * 
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 12/07/2006
	 * 
	 * @param idImovel,idOrdemServico
	 * 
	 * @throws ControladorException
	 */

	public void efetuarRestabelecimentoLigacaoAgua(IntegracaoComercialHelper integracaoComercialHelper)
			throws ControladorException;

	/**
	 * [UC0396] Inserir Tipo de Retorno da OS Referida
	 * 
	 * [FS0003] - Validar atendimento do motivo de encerramento.
	 * 
	 * @author lms
	 * @created 21/07/2006
	 * @throws ControladorException
	 * 
	 */
	public void validarAtendimentoMotivoEncerramento(
			OsReferidaRetornoTipo osReferidaRetornoTipo)
			throws ControladorException;

	/**
	 * [UC0396] Inserir Tipo de Retorno da OS Referida
	 * 
	 * [FS0002] - Solicitar o indicador de troca de serviço, situação e motivo
	 * de encerramento [FS0003] - Validar atendimento do motivo de encerramento
	 * [FS0005] - Validar indicador de deferimento [FS0006] - Validar indicador
	 * de deferimento x indicador de troca de serviço [FS0007] - Verificar
	 * sucesso da transação
	 * 
	 * @author lms
	 * @created 21/07/2006
	 * @throws ControladorException
	 * 
	 */
	public Integer inserirOSReferidaRetornoTipo(
			OsReferidaRetornoTipo osReferidaRetornoTipo)
			throws ControladorException;

	/**
	 * [UC0354] Efetuar Ligação de Água.
	 * 
	 * Permite validar ligação de Água Exibir ou pelo menu ou pela
	 * funcionalidade encerrar a execução da ordem de serço.
	 * 
	 * [FS0008] Verificar Situação Rede de Água na Quadra. [FS0007] Verificar
	 * Situação do Imovel. [FS0002] Validar Situação de Água do Imóvel
	 * 
	 * @author Leandro Cavalcanti.
	 * @date 12/07/2006
	 * 
	 * 
	 * @param imovel
	 * @throws ControladorException
	 */
	public void validarLigacaoAguaExibir(OrdemServico ordem,
			boolean veioEncerrarOS) throws ControladorException;

	/**
	 * [UC0353] Efetuar Ligação de Esgoto.
	 * 
	 * Permite validar ligação de esgoto Exibir ou pelo menu ou pela
	 * funcionalidade encerrar a execução da ordem de serço.
	 * 
	 * [FS0008] Verificar Situação Rede de Esgoto na Quadra. [FS0007] Verificar
	 * Situação do Imovel. [FS0002] Validar Situação de Esgoto do Imóvel
	 * 
	 * @author Leandro Cavalcanti.
	 * @date 12/07/2006
	 * 
	 * 
	 * @param imovel
	 * @throws ControladorException
	 */
	public void validarLigacaoEsgotoExibir(OrdemServico ordem,
			boolean veioEncerrarOS) throws ControladorException;

	/**
	 * [UC0354] Efetuar Ligação de Água.
	 * 
	 * Permite validar ligação de Água Efetuar ou pelo menu ou pela
	 * funcionalidade encerrar a execução da ordem de serço.
	 * 
	 * 
	 * 
	 * @author Leandro Cavalcanti.
	 * @date 12/07/2006
	 * 
	 * 
	 * @param imovel
	 * @throws ControladorException
	 */
	public void validarLigacaoAguaEfetuar(Imovel imovel, LigacaoAgua ligacaoAgua)
			throws ControladorException;

	/**
	 * [UC0381] Inserir Material com Unidade
	 * 
	 * Permite a inclusao de um novo material
	 * 
	 * 
	 * [SB0001] Gerar Material com Unidade
	 * 
	 * 1.1Inclui o material na tabela Material
	 * 
	 * 
	 * 
	 * @author Rômulo Aurélio, Diogo Peixoto
	 * @date 31/07/2006, 18/08/2011
	 * 
	 * @param codigoMaterial
	 * @param descricao
	 * @param descricaoAbreviada
	 * @param unidadeMaterial
	 * 
	 * @throws ControladorException
	 */
	public Integer inserirMaterial(String codigoMaterial, String descricao, String descricaoAbreviada,
			String unidadeMaterial, Usuario usuarioLogado) throws ControladorException;

	/**
	 * [UC0385] Inserir Tipo Perfil Serviço
	 * 
	 * @author Ana Maria
	 * @date 01/08/2006
	 * 
	 * @param servicoPerfilTipo
	 * @throws ControladorException
	 */
	public Integer inserirServicoTipoPerfil(ServicoPerfilTipo servicoPerfilTipo)
			throws ControladorException;

	/**
	 * [UC0436] Inserir Tipo de Serviço de Referência.
	 * 
	 * Permite a inclusão de um tipo de serviço de referência.
	 * 
	 * [FS0003] Validar indicador de existencia x Situação da Os de referencia
	 * 
	 * @author Rômulo Aurélio.
	 * @date 05/08/2006
	 * 
	 * 
	 * @param servicoTipoReferencia
	 * @throws ControladorException
	 */

	public Integer inserirTipoServicoReferencia(
			ServicoTipoReferencia servicoTipoReferencia, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * 
	 * [UC0410] - Inserir Tipo de Serviço
	 * 
	 * [FS0004] - Validar Perfil do Serviço
	 * 
	 * @author lms
	 * @date 01/08/2006
	 */
	public ServicoPerfilTipo pesquisarServicoPerfilTipo(
			Integer idServicoPerfilTipo) throws ControladorException;

	/**
	 * 
	 * [UC0410] - Inserir Tipo de Serviço
	 * 
	 * [FS0005] - Validar Tipo de Serviço de Referência
	 * 
	 * @author lms
	 * @date 02/08/2006
	 */
	public ServicoTipoReferencia pesquisarServicoTipoReferencia(
			Integer idServicoTipoReferencia) throws ControladorException;

	/**
	 * 
	 * [UC0410] - Inserir Tipo de Serviço
	 * 
	 * [FS0009] - Validar Atividade
	 * 
	 * @author lms
	 * @date 05/08/2006
	 */
	public Atividade pesquisarAtividade(Integer idAtividade, String atividadeUnica)
			throws ControladorException;

	/**
	 * 
	 * [UC0410] - Inserir Tipo de Serviço
	 * 
	 * [FS0006] - Validar Ordem de Execução
	 * 
	 * @author lms
	 * @date 05/08/2006
	 */
	public void validarOrdemExecucao(Collection colecaoServicoTipoAtividade,
			Short ordemExecucao) throws ControladorException;

	/**
	 * 
	 * [UC0410] - Inserir Tipo de Serviço
	 * 
	 * @author lms
	 * @date 07/08/2006
	 */
	public Integer inserirServicoTipo(ServicoTipo servicoTipo, Usuario usuarioLogado,ServicoTipoBoletim servicoTipoBoletim)
			throws ControladorException;

	/**
	 * 
	 * [UC0410] - Inserir Tipo de Serviço
	 * 
	 * @author lms
	 * @date 07/08/2006
	 */
	public ServicoTipoSubgrupo pesquisarServicoTipoSubgrupo(
			Integer idServicoTipoSubgrupo) throws ControladorException;

	/**
	 * 
	 * [UC0410] - Inserir Tipo de Serviço
	 * 
	 * @author lms
	 * @date 07/08/2006
	 */
	public ServicoTipoPrioridade pesquisarServicoTipoPrioridade(
			Integer idServicoTipoPrioridade) throws ControladorException;

	/**
	 * 
	 * [UC0410] - Inserir Tipo de Serviço
	 * 
	 * [FS0010] - Validar Material
	 * 
	 * @author lms
	 * @date 08/08/2006
	 */
	public Material pesquisarMaterial(Integer idMaterial)
			throws ControladorException;

	/**
	 * 
	 * [UC0410] - Inserir Tipo de Serviço
	 * 
	 * @author lms
	 * @date 07/08/2006
	 */
	public void validarAdicionarAtividade(
			Collection colecaoServicoTipoAtividade, Integer idAtividade)
			throws ControladorException;

	/**
	 * 
	 * [UC0410] - Inserir Tipo de Serviço
	 * 
	 * @author lms
	 * @date 07/08/2006
	 */
	public void validarAdicionarMaterial(Collection colecaoServicoTipoMaterial,
			Integer idMaterial) throws ControladorException;

	/**
	 * [UC0449] Inserir Prioridade do Tipo de Serviço
	 * 
	 * Permite a inclusão de uma prioridade do tipo de serviço.
	 * 
	 * [FS0001] Verificar existencia da descrição [FS0003]- Verificar existência
	 * da descrição abreviada [FS0002] Validar quantidade de horas início e
	 * quantidade de horas fim
	 * 
	 * @author Rômulo Aurélio.
	 * @date 11/08/2006
	 * 
	 * 
	 * @param servicoTipoPrioridade
	 * @throws ControladorException
	 */
	public Integer inserirPrioridadeTipoServico(
			ServicoTipoPrioridade servicoTipoPrioridade, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * 
	 * Este método se destina a validar todas as situações e particularidades do
	 * supressao ligação de agua
	 * 
	 * @author Rafael Pinto
	 * @date 28/07/2006
	 * 
	 * @param OrdemServico
	 */
	public void validarExibirSupressaoLigacaoAgua(OrdemServico ordemServico,
			boolean veioEncerrarOS) throws ControladorException;

	/**
	 * 
	 * Este método se destina a validar todas as situações e particularidades do
	 * restabelecimento ligação de agua
	 * 
	 * @author Rafael Pinto
	 * @date 29/07/2006
	 * 
	 * @param ordemServico,veioEncerrarOS
	 */
	public void validarExibirRestabelecimentoLigacaoAgua(
			OrdemServico ordemServico, boolean veioEncerrarOS)
			throws ControladorException;

	/**
	 * 
	 * Este método se destina a validar todas as situações e particularidades de
	 * religação de água
	 * 
	 * @author Rafael Pinto
	 * @date 29/07/2006
	 * 
	 * @param ordemServico,veioEncerrarOS
	 */
	public void validarExibirReligacaoAgua(OrdemServico ordemServico,
			boolean veioEncerrarOS) throws ControladorException;

	/**
	 * 
	 * Este método se destina a validar todas as situações e particularidades de
	 * corte adimistrativo de ligação de água
	 * 
	 * @author Rafael Pinto
	 * @date 29/07/2006
	 * 
	 * @param ordemServico,veioEncerrarOS
	 */
	public void validarExibirCorteAdministrativoLigacaoAgua(
			OrdemServico ordemServico, boolean veioEncerrarOS)
			throws ControladorException;

	/**
	 * 
	 * Este método se destina a validar todas as situações e particularidades do
	 * substituição de hidrometro
	 * 
	 * @author Rafael Pinto
	 * @date 31/07/2006
	 * 
	 * @param OrdemServico
	 */
	public void validarExibirSubstituicaoHidrometro(OrdemServico ordemServico,
			boolean veioEncerrarOS) throws ControladorException;

	/**
	 * 
	 * Este método se destina a validar todas as situações e particularidades da
	 * retirada de hidrometro
	 * 
	 * @author Rafael Pinto
	 * @date 25/07/2006
	 * 
	 * @param OrdemServico
	 */
	public void validarExibirRetiradaHidrometroAgua(OrdemServico ordemServico,
			boolean veioEncerrarOS) throws ControladorException;

	/**
	 * 
	 * Este método se destina a validar todas as situações e particularidades da
	 * remanejamento de hidrometro
	 * 
	 * @author Rafael Pinto
	 * @date 25/07/2006
	 * 
	 * @param OrdemServico
	 */
	public void validarExibirRemanejmentoHidrometroAgua(
			OrdemServico ordemServico, boolean veioEncerrarOS)
			throws ControladorException;

	/**
	 * [UC0362] Efetuar Instalacao de Hidrômetro
	 * 
	 * Validar Instalacao de Hidrômetro
	 * 
	 * @author Ana Maria
	 * @date 13/07/2006
	 * 
	 * @param matriculaImovel,
	 * @param numeroHidrometro,
	 * @param tipoMedicao
	 * 
	 * return void
	 * @throws ControladorException
	 */
	public void validarExibirInstalacaoHidrometro(OrdemServico ordemServico,
			boolean veioEncerrarOS) throws ControladorException;

	/**
	 * 
	 * Este método se destina a validar todas as situações e particularidades da
	 * atualização da instalação de hidrômetro do imóvel no momento da exibição.
	 * 
	 * @author Rafael Pinto
	 * @date 20/07/2006
	 * 
	 * @param OrdemServico
	 */
	public void validarExibirAtualizarInstalacaoHidrometro(
			OrdemServico ordemServico, boolean menu)
			throws ControladorException;

	/**
	 * [UC0475] Obter Valor do Débito
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param servicoTipoId
	 * @param imovelId
	 * @param tipoMedicao
	 * @return valor do débito
	 * 
	 * @throws ControladorException
	 */
	public BigDecimal obterValorDebito(Integer servicoTipoId, Integer imovelId,
			Short tipoMedicao) throws ControladorException;

	/**
	 * Método que retorna o número do hidrômetro da ligação de água
	 * 
	 * @throws ErroRepositorioException
	 */
	public String pesquisarNumeroHidrometroLigacaoAgua(Integer idLigacaoAgua)
			throws ControladorException;

	/**
	 * Método que retorna o tipo da ligação de água e a data do corte da ligação
	 * de água
	 * 
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarDadosLigacaoAgua(Integer idLigacaoAgua)
			throws ControladorException;
	
	/**
	 * [UC0357] Efetuar Religação de Água
	 * 
	 * Permite efetuar religação da ligação de água ou pelo menu ou pela
	 * funcionalidade encerrar a execução da ordem de serviço.
	 * 
	 * [SB0001] Atualizar Imóvel/Ligação de Água/Ligação de Esgoto
	 * 
	 * @author Rômulo Aurélio
	 * @date 07/07/2006
	 * 
	 * @param ordemServico
	 * 
	 * @throws ControladorException
	 */
	public void efetuarReligacaoAgua(IntegracaoComercialHelper integracaoComercialHelper)
			throws ControladorException;
	
	/**
	 * Consulta os dados das ordens de serviço para a geração do relatório
	 * 
	 * @author Rafael Corrêa
	 * @created 07/10/2006
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Collection pesquisarOrdemServicoProgramacaoRelatorio(Integer idEquipe, Date dataRoteiro)
			throws ControladorException;
	
	/**
	 * [UC0364] Efetuar Substituição de Hidrômetro
	 * 
	 * Validar Substituição de Hidrômetro
	 * 
	 * @author Ana Maria
	 * @date 25/07/2006
	 * 
	 * @param matriculaImovel,
	 * @param numeroHidrometro,
	 * @param situacaoHidrometroSubstituido
	 * 
	 * return void
	 * @throws ControladorException
	 */
	public void validacaoSubstituicaoHidrometro(String matriculaImovel,
			String numeroHidrometro, String situacaoHidrometroSubstituido, String medicaoTipo)
			throws ControladorException; 
	
	/**
	 * [UC0362] Efetuar Instalacao de Hidrômetro
	 * 
	 * Validar Instalacao de Hidrômetro
	 * 
	 * @author Ana Maria
	 * @date 13/07/2006
	 * 
	 * @param matriculaImovel,
	 * @param numeroHidrometro,
	 * @param tipoMedicao
	 * 
	 * return void
	 * @throws ControladorException
	 */
	public void validacaoInstalacaoHidrometro(String numeroHidrometro)
			throws ControladorException;
	
	/**
	 * [UC0387] Manter Tipo Perfil Serviço
	 * 
	 * [SB0001] Atualizar Tipo Perfil Serviço
	 * 
	 * @author Kassia Albuquerque
	 * @date 01/11/2006
	 * 
	 * @pparam servicoPerfilTipo
	 * @throws ControladorException
	 */
	public void atualizarServicoTipoPerfil(ServicoPerfilTipo servicoPerfilTipo,Usuario usuarioLogado)
			throws ControladorException ;
	
	/**
	 * 
	 * Este método valida os dados que são necessarios para a 
	 * inserção do serviço tipo referencia.
	 *
	 *
	 * @author Flávio Leonardo
	 * @date 31/10/2006
	 *
	 * @param servicoTipoReferencia
	 * @return
	 * @throws ControladorException 
	 */
	public void validarTipoServicoReferenciaParaInsercao(ServicoTipoReferencia servicoTipoReferencia) throws ControladorException;
	
	
	/**
	 * [UC0387] Manter Tipo Perfil Serviço
	 * [SB0002] Remover Tipo Perfil Serviço
	 * 
	 * @author Kassia Albuquerque
	 * @date 08/11/2006
	 * 
	 * @pparam servicoPerfilTipo
	 * @throws ControladorException
	 */
	public void removerServicoTipoPerfil(String[] ids,Usuario usuarioLogado)
			throws ControladorException;
	
	/**
	 * [UC0404] Manter Especificação da Situação do Imovel
	 * 
	 * Este caso de uso remove a especificação e os critério
	 * 
	 * [SB0002] Remover Especificação da situacao
	 * 
	 * @author Rafael Pinto
	 * @created 08/11/2006
	 * 
	 * @throws ControladorException Controlador Exception
	 */
	public void removerEspecificacaoSituacaoImovel(String[] idsEspecificacaoSituacaoImovel,
		Usuario usuario,Date ultimaAlteracao) throws ControladorException;	
	

	/**
	 * [UC0475] Obter Valor do Débito
	 * 
	 * Verificar existência de hidrômetro na ligação de água.
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param imovelId
	 * @return existencia de hidrometro ou não
	 * @throws ErroRepositorioException
	 */
	public boolean verificarExistenciaHidrometroEmLigacaoAgua(Integer imovelId)
			throws ControladorException;
	
	/**
	 * [UC0475] Obter Valor do Débito
	 * 
	 * Verificar existência de hidrômetro na ligação de água.
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param imovelId
	 * @return existencia de hidrometro ou não
	 * @throws ErroRepositorioException
	 */
	public boolean verificarExistenciaHidrometroEmImovel(Integer imovelId)
			throws ControladorException;
	
	
	/**
	 * [UC0383] Manter Material
	 * [SB0003] Remover Material
	 * 
	 * @author Kassia Albuquerque
	 * @date 16/11/2006
	 * 
	 * @pparam material
	 * @throws ControladorException
	 */
	public void removerMaterial(String[] ids,Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * [UC0383] Manter Material [SB0001] Atualizar Material
	 * 
	 * @param Material  
	 * @param Usuario Logado
	 * 
	 * @author Diogo Peixoto
	 * @date 18/08/2011
	 */
	public void atualizarMaterial(Material material, Usuario usuarioLogado) throws ControladorException;
	
	/**
	 * [UC0498] Efetuar Ligação de Água com Instalação de Hidrômetro.
	 * 
	 * Permite validar o efetuar ligação de Água com Instalação de Hidrômetro Exibir ou pelo menu ou pela
	 * funcionalidade encerrar a execução da ordem de serço.
	 * 
	 * [FS0008] Verificar Situação Rede de Água na Quadra. [FS0007] Verificar
	 * Situação do Imovel. [FS0002] Validar Situação de Água do Imóvel
	 * 
	 * @author Rafael Corrêa
	 * @date 28/11/2006
	 * 
	 * 
	 * @param imovel
	 * @throws ControladorException
	 */
	public void validarLigacaoAguaComInstalacaoHidrometroExibir(OrdemServico ordem,
			boolean veioEncerrarOS) throws ControladorException;
	
	/**
	 * [UC0498] Efetuar Ligação de Água com Instalação de Hidrômetro.
	 * 
	 * Permite efetuar ligação de Água com Instalação de Hidrômetr ou pelo menu
	 * ou pela funcionalidade encerrar a execução da ordem de serço.
	 * 
	 * @author Rafael Corrêa
	 * @date 29/11/2006
	 * 
	 * @param integracaoComercialHelper
	 * @throws ControladorException
	 */
	public void efetuarLigacaoAguaComInstalacaoHidrometro(
			IntegracaoComercialHelper integracaoComercialHelper, Usuario usuario)
			throws ControladorException;
	
	/**
	 * [UC0294] Prioridade Tipo Servico [] Atualizar Prioridade Tipo Servico
	 * 
	 * 
	 * 
	 * @author Thiago Tenório
	 * @date 25/05/2006
	 * 
	 * @param Prioridade Tipo Servico
	 * @throws ControladorException
	 */

	public void atualizarPrioridadeTipoServico(ServicoTipoPrioridade servicoTipoPrioridade,
			Collection colecaoServicoTipoPrioridade)
			throws ControladorException;
	
	/**
	 * [UC0475] Obter Valor do Débito
	 * 
	 * @author Rafael Pinto
	 * @date 22/02/2007
	 * 
	 * @param servicoTipoId
	 * @param imovelId
	 * @param tipoMedicao
	 * @param idHidrometroCapacidade
	 *
	 * @return valor do Débito
	 * 
	 * @throws ControladorException
	 */
	public BigDecimal obterValorDebito(Integer servicoTipoId, Integer imovelId,
		HidrometroCapacidade hidrometroCapacidade) throws ControladorException ;
	
	/**
	 * [UC0555] Alterar Situacao da Ligacao
	 * 
	 * @author Romulo Aurelio
	 * @date 27/03/2007
	 * 
	 * @param imovel
	 *
	 * 
	 * @throws ControladorException
	 */
	public void validarOrdemServicoAlterarSituacaoLigacao(OrdemServico ordemServico,
			boolean veioEncerrarOS) throws ControladorException;
	
	public Integer alterarSituacaoLigacao(Imovel imovel, String indicadorTipoLigacao, String idSituacaoLigacaoAguaNova, 
			String idSituacaoLigacaoEsgotoNova, String idOrdemServico, 
			Usuario usuarioLogado) throws ControladorException;
	
	/**
	 * [UC0540] Efetuar Restabelecimento da Ligação de Água com Instalação de hidrômetro.
	 * 
	 * Permite validar o Efetuar Restabelecimento Ligação de Água com Instalação de hidrômetro
	 * Exibir ou pelo menu ou pela funcionalidade encerrar a Execução da ordem
	 * de serviço.
	 * 
	 * [FS0008] Verificar Situação Rede de Água na Quadra. [FS0007] Verificar
	 * Situação do Imovel. [FS0002] Validar Situação de Água do Imóvel
	 * 
	 * @author Rafael Corrêa
	 * @date 18/04/2007
	 * 
	 * 
	 * @param imovel
	 * @throws ControladorException
	 */
	public void validarRestabelecimentoLigacaoAguaComInstalacaoHidrometroExibir(
			OrdemServico ordem, boolean veioEncerrarOS)
			throws ControladorException;

	/**
	 * [UC0540] Efetuar Restabelecimento da Ligação de Água com Instalação de hidrômetro.
	 * 
	 * Permite efetuar o Restabelecimento Ligação de Água com Instalação de Hidrômetro ou pelo menu
	 * ou pela funcionalidade encerrar a Execução da ordem de serviço.
	 * 
	 * @author Rafael Corrêa
	 * @date 19/04/2007
	 * 
	 * @param integracaoComercialHelper
	 * @throws ControladorException
	 */
	public void efetuarRestabelecimentoLigacaoAguaComInstalacaoHidrometro(
			IntegracaoComercialHelper integracaoComercialHelper, Usuario usuario)
			throws ControladorException;

	/**
	 * Pesquisa todos os ids das situações de ligação de água.
	 *
	 * [UC0564 - Gerar Resumo das Instalações de Hidrômetros]
	 *
	 * @author Pedro Alexandre
	 * @date 25/04/2007
	 *
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarTodosIdsSituacaoLigacaoAgua() throws ControladorException ;
	
	/**
	 * Pesquisa todos os ids das situações de ligação de esgoto.
	 *
	 * [UC0564 - Gerar Resumo das Instalações de Hidrômetros]
	 *
	 * @author Pedro Alexandre
	 * @date 25/04/2007
	 *
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarTodosIdsSituacaoLigacaoEsgoto() throws ControladorException ;
	
	/**
	 * 
	 * Este cso de uso permite efetuar a ligação de água e eventualmente a
	 * instalação de hidrômetro, sem informação de RA sendo chamado direto pelo
	 * menu.
	 * 
	 * [UC0579] - Efetuar Ligação de Água com Intalação de Hidrômetro
	 * 
	 * @author Flávio Leonardo
	 * @date 25/04/2007
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public EfetuarLigacaoAguaComInstalacaoHidrometroSemRAHelper pesquisarEfetuarLigacaoAguaHidrometroSemRA(
			Integer idImovel) throws ControladorException;
	
	/**
	 * [UC0XXX] Gerar Contrato de Prestação de Serviço
	 * 
	 * @author Rafael Corrêa
	 * @date 03/05/2007
	 * 
	 * @throws ControladorException
	 */
	public Collection obterDadosContratoPrestacaoServico(
			Integer idImovel, Integer idCliente) throws ControladorException;
	
	public void atualizarImovelLigacaoAguaInstalacaoHidrometroSemRA(Integer idImovel, Integer idHidrometro);
	
	/**
	 * [UC0582] - Emitir Boletim de Cadastro
	 * 
	 * Obtém os dados necessário da ligação de água, de esgoto e do hidrômetro
	 * instalado na ligação de água
	 * 
	 * @author Rafael Corrêa
	 * @date 17/05/2007
	 * 
	 * @throws ControladorException
	 */
	public DadosLigacoesBoletimCadastroHelper obterDadosLigacaoAguaEsgoto(
			Integer idImovel) throws ControladorException;
	
	/**
	 * 
	 * [UC0587] Emitir Contrato de Prestacao de servico
	 * 
	 * @param idImovel
	 * @param idClienteEmpresa
	 * @return
	 * @throws ControladorException
	 */
	public RelatorioContratoPrestacaoServicoJuridicoBean gerarContratoJuridica(Integer idImovel, Integer idClienteEmpresa) throws ControladorException;

	
	/**
	 * [UC0608] Efetuar Ligação de Esgoto sem RA.
	 * 
	 * [FS0001] Verificar existência da matrícula do Imovel.
	 * 
	 * [FS0007] Verificar situação do imóvel.
	 * 
	 * [FS0008] Verificar Situação Rede de Esgoto da Quadra.
	 * 
	 * @author Sávio Luiz.
	 * @date 10/09/2007
	 * 
	 * 
	 * @param imovel
	 * @throws ControladorException
	 */
	public String validarMatriculaImovel(Integer idImovel)
			throws ControladorException;

	
	/**
	 * [UC0482]Emitir 2ª Via de Conta
	 *obter numero do hidrômetro na ligação de água.
	 * 
	 * @author Vivianne Sousa
	 * @date 11/09/2007
	 * 
	 * @param imovelId
	 * @return existencia de hidrometro ou não
	 * @throws ErroRepositorioException
	 */
	public String obterNumeroHidrometroEmLigacaoAgua(Integer imovelId)
			throws ControladorException;
	
	/**
	 * [UC0475] Obter Valor do Débito
	 * 
	 * Obter Capacidade de Hidrômetro pela Ligação de Água.
	 * 
	 * @author Leonardo Regis
	 * @date 09/09/2006
	 * 
	 * @param imovelId
	 * @return existencia de hidrometro ou não
	 * @throws ErroRepositorioException
	 */
	public HidrometroCapacidade obterHidrometroCapacidadeEmLigacaoAgua(
			Integer imovelId) throws ControladorException;
	
	/**
	 * [UC0726] Gerar Relatório de Imóveis com Faturas em Atraso
	 * 
	 * @author Bruno Barros
	 * @date 06/12/2007
	 * 
	 * @param FiltrarRelatorioImoveisFaturasAtrasoHelper
	 * 
	 * @return Collection<RelatorioImoveisSituacaoLigacaoAguaHelper>
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioCertidaoNegativaHelper> pesquisarRelatorioCertidaoNegativa(
		Imovel imo ) 
		throws ControladorException;
	
	/**
	 * [UC0747] Efetuar Religação de Água com Instalação de hidrômetro.
	 * 
	 * Permite validar o efetuar religação de Água com Instalação de hidrômetro
	 * Exibir ou pelo menu ou pela funcionalidade encerrar a Execução da ordem
	 * de serviço.
	 * 
	 * [FS0002] Verificar Situação do Imovel. [FS0003] Validar Situação de Água
	 * 
	 * @author Sávio Luiz
	 * @date 29/01/2008
	 * 
	 * 
	 * @param imovel
	 * @throws ControladorException
	 */
	public void validarReligacaoAguaComInstalacaoHidrometroExibir(
			OrdemServico ordem, boolean veioEncerrarOS)
			throws ControladorException;

	/**
	 * [UC0498] Efetuar Religação de Água com Instalação de hidrômetro.
	 * 
	 * Permite efetuar religação de Água com Instalação de Hidrômetro ou pelo
	 * menu ou pela funcionalidade encerrar a Execução da ordem de serviço.
	 * 
	 * @author Sávio Luiz
	 * @date 29/01/2008
	 * 
	 * @param integracaoComercialHelper
	 * @throws ControladorException
	 */
	public void efetuarReligacaoAguaComInstalacaoHidrometro(
			IntegracaoComercialHelper integracaoComercialHelper, Usuario usuario)
			throws ControladorException;
	
	public Integer atualizarServicoTipo(ServicoTipo servicoTipo,ServicoTipoBoletim servicoTipoBoletim)
	throws ControladorException;
	
	/**
	 * [UC0778] Gerar Relatório Gestão de Serviços UPA<br>
	 * [UC0497] Gerar Relatório Resumo de Solicitações de RA por Unidade
	 * <p>
	 * Retorna todas as unidades filhas (direta ou indiretamente) da Unidade Superior passada como parâmetro.
	 * 
	 * @see gsan.atendimentopublico.ControladorAtendimentoPublicoSEJB#pesquisarUnidadesFilhas(int)
	 * 
	 * @author Victor Cisneiros
	 * @date 09/07/2008
	 * 
	 * @param idUnidadeSuperior
	 * @throws ControladorException
	 */
	public UnidadesFilhasHelper pesquisarUnidadesFilhas(int idUnidadeSuperior) throws ControladorException;
	
	/**
	 * Pesquisa os dados necessários para a geração do relatório
	 * 
	 * [UC0864] Gerar Certidão Negativa por Cliente
	 * 
	 * @return
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioCertidaoNegativaClienteBean> pesquisarRelatorioCertidaoNegativaCliente(
			Collection<Integer> idsClientes, Cliente clienteInformado, Short icPossuiImovel,
			Boolean indicadorContaVencidas, Boolean imovelComParcelamento)
			throws ControladorException;
	
	/**
	 * @author Vivianne Sousa
	 * @date 12/08/2008
	 */
	public void efetuarLigacaoAguaComInstalacaoHidrometroSemRA(
			Integer idImovel, 
			LigacaoAgua ligacaoAgua,
			HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico) throws ControladorException;
	
	/**
	 * [UC0541] Emitir 2a Via Conta Internet
	 *
	 * [FS0003] - Verificar se documento é válido
	 *
	 * [FS0004] - Cliente não associado ao documento
	 *
	 * @author Raphael Rossiter
	 * @date 21/10/2008
	 *
	 * @param idImovel
	 * @param cpf
	 * @param cnpj
	 * @throws ControladorException
	 */
	public void verificarDocumentoValidoEmissaoInternet(Integer idImovel, String cpf, String cnpj) 
	throws ControladorException ;
	
	/**
	 * [UC0482] Emitir 2a Via Conta
	 *
	 * [FS0002] - Cliente sem documento
	 *
	 * @author Raphael Rossiter
	 * @date 24/10/2008
	 *
	 * @param idImovel
	 * @param usuario
	 * @throws ControladorException
	 */
	public void verificarClienteSemDocumento(Integer idImovel, Usuario usuario) throws ControladorException ;

	/**
	 * Permite inserir um Perfil da Ligacao de Esgoto
	 * 
	 * [UC0861] Inserir Perfil da Ligacao de Esgoto
	 * 
	 * @author Arthur Carvalho
	 * @date 16/10/2006
	 * 
	 */

	public Integer inserirPerfilLigacaoEsgoto(LigacaoEsgotoPerfil ligacaoEsgotoPerfil, Usuario usuarioLogado)
			throws ControladorException;
	
	/**
	 * Permite atualizacao um Perfil da Ligacao de Esgoto
	 * 
	 * [UC0861]Manter Perfil da Ligacao de Esgoto
	 * 
	 * @author Arthur Carvalho
	 * @date 20/10/2008
	 * 
	 */

	public void atualizarPerfilLigacaoEsgoto(LigacaoEsgotoPerfil ligacaoEsgotoPerfil, Usuario usuarioLogado)
			throws ControladorException;

	 /**
     * [UC0150] Retificar Conta
     * @author Vivianne Sousa
     * @date 26/11/2008
     */
    public BigDecimal obterPercentualAguaConsumidaColetadaImovel(Integer idImovel)
    	throws ControladorException ;
    
    /**
	 * [UC0898] Atualizar Autos de Infração com prazo de Recurso Vencido
	 * 
	 * [SB0002] - Gerar Débito a Cobrar
	 * 
	 * @author Sávio Luiz
	 * @date 08/05/2009
	 */
	public Short gerarDebitoACobrarAutoInfracao(AutosInfracao autosInfracao,SistemaParametro sistemaParametro)
			throws ControladorException;
    
    /**
	 * [UC0898] Atualizar Autos de Infração com prazo de Recurso Vencido
	 * 
	 * @author Sávio Luiz
	 * @date 08/05/2009
	 */
	public void atualizarAutosInfracaoComPrazoRecursoVencido(
			SistemaParametro sistemaParametro,Integer idFuncionalidadeIniciada)
			throws ControladorException; 
	
	/**
	 * [UC0996] Emitir Ordem de Fiscalização para imóveis suprimidos
	 * 
	 *  Step que gera os dados.
	 * 
	 * 
	 * @author Hugo Amorim
	 * @date 08/03/2010
	 * @param idFuncionalidadeIniciada
	 * @param usuarioLogado
	 * @param setorComercial
	 */
	public void gerarDadosOrdemDeFiscalizacao(int idFuncionalidadeIniciada,
			Usuario usuarioLogado,SetorComercial setorComercial,SistemaParametro sistemaParametro) throws ControladorException;
	
	/**
	 * [UC0996] Emitir Ordem de Fiscalização para imóveis suprimidos
	 * 
	 *  Step que gera os arquivos.
	 * 
	 * 
	 * @author Hugo Amorim
	 * @date 10/03/2010
	 * @param idFuncionalidadeIniciada
	 * @param usuarioLogado
	 * @param setorComercial
	 */
	public void gerarArquivoOrdemDeFiscalizacao(Integer integer, Usuario usuario)throws ControladorException;
	
	/**
	 * [SB0002] – Replicar os serviços existentes para uma nova vigência e
	 * valor. Pesquisa a última vigência de cada tipo serviço, e retorna o
	 * total.
	 * 
	 * @author Josenildo Neves
	 * @date 03/02/2010
	 */
	public Integer pesquisarServicoCobrancaValorUltimaVigenciaTotal() throws ControladorException;
	
	/**
	 * [SB0002] – Replicar os serviços existentes para uma nova vigência e
	 * valor. Pesquisa a última vigência de cada tipo serviço, e retorna uma
	 * coleção.
	 * 
	 * @author Josenildo Neves - Hugo Leonardo	
	 * @date 04/02/2010 - 26/04/2010
	 */
	public Collection<ServicoCobrancaValor> replicarServicoCobrancaValorUltimaVigencia(
			String[] selecionacos) throws ControladorException;
	
	/**
	 * [SB0002] – Replicar os serviços existentes para uma nova vigência e
	 * valor. Pesquisa a última vigência de cada tipo serviço, e retorna uma
	 * coleção.
	 * 
	 * @author Josenildo Neves
	 * @date 03/02/2010
	 */
	public Collection<ServicoCobrancaValor> pesquisarServicoCobrancaValorUltimaVigencia(
			Integer numeroPagina) throws ControladorException;
	
	/**
	 * [UC0391] Inserir valor cobrança Serviço.
	 * 
	 * Verificar se existe vigência já cadastrada para o Serviço Tipo.
	 * 
	 * @author Hugo Leonardo
	 * @param dataVigenciaInicial
	 * @param dataVigenciaFinal
	 * @param idServicoTipo
	 * @param opcao
	 * @throws ControladorException
	 * @data 03/05/2010
	 * 
	 * @see Caso a opcao = 1 - verifica as situações de inserir e atualizar Serviço Tipo.
	 * @see Caso a opcao = 2 - verifica a situação de retificar Serviço Tipo.
	 */
	public void verificarExistenciaVigenciaServicoTipo(String dataVigenciaInicial, String dataVigenciaFinal, Integer idServicoTipo, Integer opcao) 
			throws ControladorException;
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 *  [SB0034] – Verificar RA de urgência
	 * 
	 * Verifica se o Registro de Atendimento tem o nivel selecionado como Urgência
	 * 
	 * @author Daniel Alves
	 * @param  ID do Registro de Atendimento 
	 * @throws ControladorException
	 * @data   04/06/2010
	 * 
	 */
	public Integer verificarRegistroAtendimentoUrgencia(Integer IdRegistroAtendimento)
		throws ControladorException;
	
	/**
	 * [UC0503] Tramitar Conjunto Registro Atendimento
	 *  [SB0004] – Verificar RA de urgência
	 *  
	 * Atualizar os Usuários da Unidade relacionada a RA, na tabela "VisualizacaoRaUrgencia" 
	 * 
	 * @author Daniel Alves
	 * @param  ID do Registro de Atendimento 
	 * @throws ControladorException
	 * @data   03/06/2010
	 * 
	 */
	
	public void inserirUsuarioVisualizacaoRaUrgencia(Integer idRegistroAtendimento,Short indicadorReiteracao)
			throws ControladorException;
	
	/**
	 * [UC0503] Tramitar Conjunto Registro Atendimento
	 *  [SB0004] – Verificar RA de urgência
	 *  
	 * Atualizar os Usuários da Unidade relacionada a RA, na tabela "VisualizacaoRaUrgencia" 
	 * 
	 * @author Daniel Alves
	 * @param  ID do Registro de Atendimento 
	 * @throws ControladorException
	 * @data   03/06/2010
	 * 
	 */
	
	public void atualizarUsuarioVisualizacaoRaUrgencia(Integer idRegistroAtendimento, Integer idUnidade, Integer idUsuario, Integer indicadorTramite, Integer indicadorVisualizacao)
			throws ControladorException;
	
	/**
	 * [UC0503] Tramitar Conjunto Registro Atendimento	 * 
	 *  [SB0004] – Verificar RA de urgência
	 * 
	 * Verifica se o Registro de Atendimento já está relacionado a uma Unidade informada.
	 * 
	 * @author Daniel Alves
	 * @param  ID do Registro de Atendimento 
	 * @throws ControladorException
	 * @data   05/06/2010
	 * 
	 */
	public Integer verificarUsuariosRegistroAtendimentoUrgencia(Integer idRegistroAtendimento, Integer idUnidade)
		throws ControladorException;
	
	/**	 
	 * [UC1028] Exibir Registro Atendimento Urgência
	 *  
	 * Verifica se o Usuario possui algum Registro de Atendimento urgente.
	 * 
	 * @author Daniel Alves
	 * @param  ID do Registro de Atendimento 
	 * @throws ControladorException
	 * @data   07/06/2010
	 * 	  
	 */
	public Collection verificarUsuarioRegistroAtendimentoUrgencia(Integer idUsuario)
		throws ControladorException;
	
    /**
	 * [UC0251] Gerar Atividade de Ação de Cobrança
	 * 
	 * @author Hugo Amorim
	 * @date 15/07/2010
	 */
	public Collection<CobrancaAcaoAtividadeComandoFiscalizacaoSituacao> 
		pesquisarCobrancaAcaoAtividadeComandoFiscalizacaoSituacao(
			Integer idComando, Collection idsSituacos)throws ControladorException;
	
	/**
	 * [UC1056] Gerar Relatório de Acompanhamento dos Registros de Atendimento Analitico
	 * 
	 * @author Hugo Leonardo
	 * @date 28/09/2010
	 * 
	 * @param FiltrarAcompanhamentoRegistroAtendimentoHelper
	 * @return Collection<RelatorioAcompanhamentoRAHelper>
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarRelatorioAcompanhamentoRAAnalitico(FiltrarAcompanhamentoRegistroAtendimentoHelper helper)
		throws ControladorException;
	
	/**
	 * [UC1056] pesquisar Total de RA's do Relatório de Acompanhamento dos Registros de Atendimento
	 * 
	 * @author Hugo Leonardo
	 * @date 30/09/2010
	 * 
	 * @param FiltrarAcompanhamentoRegistroAtendimentoHelper
	 * @return Intenger
	 * 
	 * @throws ControladorException
	 */
	public Integer countPesquisarRelatorioAcompanhamentoRAAnalitico(FiltrarAcompanhamentoRegistroAtendimentoHelper helper)
		throws ControladorException;
	
	/**
	 * [UC1056] Gerar Relatório de Acompanhamento dos Registros de Atendimento Sintetico Encerrado
	 * 
	 * @author Hugo Leonardo
	 * @date 28/09/2010
	 * 
	 * @param FiltrarAcompanhamentoRegistroAtendimentoHelper
	 * @return Collection<RelatorioAcompanhamentoRAHelper>
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarRelatorioAcompanhamentoRASinteticoEncerrado(
			FiltrarAcompanhamentoRegistroAtendimentoHelper helper) 	throws ControladorException;
	
	/**
	 * [UC1056] Gerar Relatório de Acompanhamento dos Registros de Atendimento Sintetico Aberto
	 * 
	 * @author Hugo Leonardo
	 * @date 28/09/2010
	 * 
	 * @param FiltrarAcompanhamentoRegistroAtendimentoHelper
	 * @return Collection<RelatorioAcompanhamentoRAHelper>
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarRelatorioAcompanhamentoRASinteticoAberto(
			FiltrarAcompanhamentoRegistroAtendimentoHelper helper) 	throws ControladorException;
	
	/**
	 * Remover todas as LocalidadeComEspecificacaoUnidade
	 * [UC1091] Informar Associação de Localidade com Especificação e Unidade
	 * 
	 * @author Hugo Leonardo
	 * @date 30/11/2010
	 * 
	 * @param idLocalidade
	 * @return void
	 */
	public void removerLocalidadeComEspecificacaoUnidade( Integer idLocalidade) throws ControladorException;
	
	/**
	 * [UC1107] Manter Custo de Pavimento por Repavimentadora
 	 * 
 	 * @author Hugo Leonardo
 	 * @date 27/12/2010
 	 * 
 	 * @param idRepavimentadora, idPavimento, indicadorPavimento: 1-Rua, 2-Calçada
 	 * @return boolean
	 */
	public boolean verificaRemoverCustoPavimentoPorRepavimentadora(Integer idRepavimentadora,
			Integer idPavimento, Integer indicadorPavimento)throws ControladorException;
	
	/**
	 * [UC1107] Manter Custo de Pavimento por Repavimentadora
 	 * 
 	 * @author Hugo Leonardo
 	 * @date 28/12/2010
 	 * 
 	 * @param id, idRepavimentadora, idPavimento, dataInicio, dataFinal, indicadorPavimento: 1-Rua, 2-Calçada
 	 * @return void
	 */
	public void verificaAtualizarCustoPavimentoPorRepavimentadora(Integer idAtualizacao, 
			Integer idRepavimentadora, Integer idPavimento, Date dataInicio, Date dataFinal, 
			Integer indicadorPavimento, Integer tipo) throws ControladorException;
					
	/**
	 * [UC1102] - Inserir Tipo de Corte
	 * 
	 * @author Ivan Sergio
	 * @data: 03/12/2010
	 * 
	 * @param descricao
	 * @param indicadorUso
	 * @param indicadorCorteAdministrativo
	 * @param usuarioLogado
	 * @return
	 * @throws ControladorException
	 */
	public Integer inserirTipoCorte(String descricao, String indicadorUso, String indicadorCorteAdministrativo, Usuario usuarioLogado)
			throws ControladorException;
	
	/**
	 * [UC1103] Manter Tipo de Corte
	 *  
	 * @author Ivan Sergio
	 * @date 06/12/2010
	 * 
	 * @pparam cortetipo
	 * @throws ControladorException
	 */
	public void atualizarCorteTipo(CorteTipo corteTipo, Usuario usuarioLogado)
			throws ControladorException;
	
	/**
	 * [UC1103] - Manter Tipo de Corte
	 * 
	 * @author Ivan Sergio
	 * @data: 07/12/2010
	 * 
	 * @param ids
	 * @param usuarioLogado
	 * @throws ControladorException
	 */
	public void removerCorteTipo(String[] ids, Usuario usuarioLogado) throws ControladorException;
	
	/**
	 * [UC1107] Manter Custo de Pavimento por Repavimentadora
	 * 
	 * 		[FS0010] Verificar se existem dias sem valor
 	 * 
 	 * @author Hugo Leonardo
 	 * @date 11/01/2010
 	 * 
 	 * @param id, idRepavimentadora, idPavimento, dataInicio, dataFinal, indicadorPavimento: 1-Rua, 2-Calçada
 	 * @return Integer
	 */
	public Integer verificarExistenciDiasSemValorCustoPavimentoPorRepavimentadora(Integer idAtualizacao, 
			Integer idRepavimentadora, Integer idPavimento, Date dataInicio, Date dataFinal, 
			Integer indicadorPavimento, Integer tipo) throws ControladorException;
	
	/**
	 * [UC1120] Gerar Relatório de religação de clientes inadimplentes
	 *
	 * @author Hugo Leonardo
	 * @date 25/01/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioReligacaoClientesInadiplentes( 
			FiltrarRelatorioReligacaoClientesInadiplentesHelper relatorioHelper) throws ControladorException;
	
	/**
	 * [UC1120] Gerar Relatório de religação de clientes inadimplentes
	 *
	 * @author Hugo Leonardo
	 * @date 25/01/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Integer countRelatorioReligacaoClientesInadiplentes( 
			FiltrarRelatorioReligacaoClientesInadiplentesHelper relatorioHelper) throws ControladorException;

	
	/**
     * Obtém a coleção de perfis de tipo de serviço para OS.
     * 
     * @author Hugo Azevedo
     * @date 22/06/2011
     * 
     * @throws ControladorException
     */
	
	public Collection obterColecaoTipoOSgerada() throws ControladorException;

	
	
	/**
     * Obtém a coleção de OS a partir dos parâmetros passados pela funcionalidade de Acompanhamento de Cobrança por Resultado.
     * 
     * @author Hugo Azevedo
     * @date 27/06/2011
     * 
     * @throws ControladorException
     */
	
	public Collection obterColecaoImovelOSCobrancaResultado(String[] categoriaImovel, String[] perfilImovel, String[] gerenciaRegional, String[] unidadeNegocio, 
			String idLocalidadeInicial, String idLocalidadeFinal, String idSetorComercialInicial, String idSetorComercialFinal,
			String idQuadraInicial, String idQuadraFinal, String tipoServico,String comando) throws ControladorException;
	
	/**
	 * [UC1177] Gerar Relatório de Ordens de Serviço por Situação
	 * 
	 * O segundo parâmetro (boletimGerado) é um booleano que
	 * indica se para um dado grupo de cobrança e um mês referencia
	 * foi gerado um boletim de medição.
	 * 
	 * @author Diogo Peixoto
	 * @date 09/06/2011
	 * 
	 * @param FiltrarRelatorioOSSituacaoHelper

	 * @return FiltrarRelatorioOSSituacaoHelper
	 * @throws ControladorException
	 */
	public RelatorioOSSituacaoHelper filtrarRelatorioOSSituacao(FiltrarRelatorioOSSituacaoHelper helper)
		throws ControladorException;
	
	/**
	 * [UC1178] Gerar Relatório de Acompanhamento dos Boletins de Medição
	 * 
	 * @author Diogo Peixoto
	 * @date 17/06/2011
	 * 
	 * @param FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper
	 * @return RelatorioAcompanhamentoBoletimMedicaoHelper
	 */
	public RelatorioAcompanhamentoBoletimMedicaoHelper filtrarRelatorioAcompanhamentoBoletimMedicao(
			FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper filtro) throws ControladorException;

	/**
	 * [UC1186] Gerar Relatório Ordem de Serviço Cobrança p/Resultado
	 * 
	 * Pesquisar as Ordens de serviços a partir de seu imóvel e tipo de serviço
	 * 
	 * @author Hugo Azevedo
	 * @data 14/01/2011
	 */
	public Collection obterOSImovelTipoServico(Integer id, Integer tipoServico) throws ControladorException;
	
	/**
	 * 
	 * [UC1186] Gerar Relatório Ordem de Serviço Cobrança p/Resultado
	 * 
     * Obtém a quantida de OS a partir dos parâmetros passados pela funcionalidade de Acompanhamento de Cobrança por Resultado.
     * 
     * @author Hugo Azevedo
     * @date 27/06/2011
     * 
     * @throws ControladorException
     */
	
	public int obterTotalOSColecaoImovelTipoServico(Collection colecaoImovel,Integer tipoServico) throws ControladorException;
	
	 /**
	  * [UC1189] Inserir Registro de Atendimento Loja Virtual
	  * 
	  * @author Magno Gouveia
	  * @date 12/07/2011
	  * 
	  * @return
	  * @throws ErroRepositorioException
	  */
	public Collection<Object[]> pesquisarSolicitacaoTipoLojaVirtual() throws ControladorException;

	/**
	 * [UC1196] Exibir Lojas de Atendimento na Loja Virtual
	 * [SB0001] Selecionar Municípios da Região
	 * 
	 * @author Magno Gouveia
	 * @date 14/07/2011
	 * 
	 * @return colecaoDeMunicipios
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarMunicipiosLojaVirtualCompesa() throws ControladorException;

	/**
	 * [UC1196] Exibir Lojas de Atendimento na Loja Virtual
	 * [SB0005] Exibir Dados da Loja
	 * 
	 * @author Magno Gouveia
	 * @date 14/07/2011
	 * 
	 * @param id do bairro
	 * @return colecaoDeLojas
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarLojasDeAtendimentoLojaVirtualCompesa(Integer idMunicipio) throws ControladorException;
	
	public void ProcessarEncerramentoOSFiscalizacaoDecursoPrazo(Integer idFuncionalidadeIniciada) throws ControladorException;
	
	/**
	 * [UC1199] – Acompanhar Arquivos de Roteiro
	 * [SB0003] – Pesquisar Fotos da OS
	 * 
	 * Método que vai retornar as fotos de uma determinada
	 * ordem de serviço passada no parâmetro.
	 * 
	 * @author Diogo Peixoto
	 * @date 12/08/2011
	 * 
	 * @param Integer - ID (Ordem de Serviço ou da Foto Ordem de Serviço)
	 * @param Boolean - Indica se o id é da OS ou da Foto (true = OS, false = Foto) 
	 * 
	 * @return Collection<OrdemServicoFoto> - Coleção das Fotos da OS
	 * @throws ControladorException
	 */
	public Collection<OrdemServicoFoto> pesquisarFotosOrdemServico(Integer id, boolean idOS) throws ControladorException;
	
	/**
	 * Método que pesquisa as ordens seletivas para um
	 * determinado comando
	 * 
	 * [UC 1220] Gerar Arquivo Texto para as Os de Visita
	 * [SB 0001] Consultar Ordem de Serviço Seletiva
	 * @author Raimundo Martins
	 * @date 15/09/2011
	 * 
	 * @return coleção das ordens de serviços selecionadas
	 */
	
	public Collection<Object[]> consultarOrdemServicoSeletiva(String comOrdemSeletiva, String local, String setorInicial, 
		String setorFinal, String quadraIni, String quadraFin) throws ControladorException;
	
		
	/**
	 * Método que insere os dados nas tabelas:
	 * 
	 * atendimentopublico.Arquivo_Texto_Visita_Campo
	 * atendimentopublico.Os_Seletiva_Visita_Campo
	 * atendimentopublico.Clie_Os_Visita_Campo
	 * atendimentopublico.Clie_Fone_Visita_Campo
	 * 
	 * [UC 1220] Gerar Arquivo Texto para as Os de Visita
	 * [SB 0002] Geração do Arquivo Texto
	 * @author Raimundo Martins
	 * @date 19/09/2011
	 * 
	 * @return coleção das ordens de serviços selecionadas
	 */
	
	public Boolean inserirDadosGeracaoArquivoTextoOSVisitaCampo(String comOrdemSeletiva, String local, String setorInicial, 
			String setorFinal, String quadraIni, String quadraFin)throws ControladorException;

	/**
	 * Método que verifica a existência do arquivo
	 * texto para aquele comando, localidade,
	 * setor comercial inicial, setor comercial final
	 * quadra inicial e quadra final	 * 
	 * 
	 * [UC 1220] Gerar Arquivo Texto para as Os de Visita
	 * [FS 0005] Geração do Arquivo Texto
	 * @author Raimundo Martins
	 * @date 22/09/2011
	 * 
	 * @return true se tiver o arquivo, false senão
	 */
	
	public Boolean verificarExistenciaArquivoTextoVisitaCampo(String comOrdemSeletiva, String local, String setorInicial, 
			String setorFinal, String quadraIni, String quadraFin)throws ControladorException;
	
	/**
	 * [UC1228] Consultar Ordens de Serviço do Arquivo Texto
	 *  1. O sistema exibe as informações do arquivo texto;
	 * 
	 * @author Mariana Victor
	 * @date 22/09/2011
	 * 
	 * @param Integer
	 * 
	 * @return Object[]
	 * @throws ControladorException
	 */
	public Object[] pesquisarDadosArquivoTextoOSVisita(Integer idArquivoTexto)
		throws ControladorException;
	
	/**
	 * [UC1228] Consultar Ordens de Serviço do Arquivo Texto
	 * 
	 *  2.	O sistema deverá exibir a lista das ordens de serviço associadas ao arquivo texto 
	 * 
	 * @author Mariana Victor
	 * @date 22/09/2011
	 * 
	 * @param Integer
	 * 
	 * @return Collection<OrdemServicoArquivoTextoHelper>
	 * @throws ControladorException
	 */
	public Collection<OrdemServicoArquivoTextoHelper> pesquisarDadosOrdensServicoArquivoTextoVisitaCampo(Integer idArquivoTexto)
		throws ControladorException;
	
	/**
	 * [UC1228] Consultar Ordens de Serviço do Arquivo Texto
	 * 
	 *  Pesquisa o id do imóvel associado à ordem de serviço
	 * 
	 * @author Mariana Victor
	 * @date 23/09/2011
	 * 
	 * @param Integer
	 * 
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer pesquisarIdImovelAssociadoAOrdemVisitaCampo(Integer idOrdemServico) 
		throws ControladorException;
	
	/**
	 * [UC1228] Consultar Ordens de Serviço do Arquivo Texto
	 * 
	 * SB0001 - Consultar/Atualizar Dados da Ordem de Serviço
	 *  3. Anormalidade Registrada 
	 *  4. Anormalidade Encontrada 
	 *  5. Tipo de Pavimento de Calçada 
	 *  6. Tipo de Pavimento de Rua 
	 * 
	 * @author Mariana Victor
	 * @date 26/09/2011
	 * 
	 * @param Integer
	 * 
	 * @return Object[]
	 * @throws ControladorException
	 */
	public Object[] pesquisarDadosAnormalidadePavimentoOSVisitaCampo(Integer idOrdemServico) 
			throws ControladorException;
	
	/**
	 * [UC1228] Consultar Ordens de Serviço do Arquivo Texto
	 * 
	 * SB0001 - Consultar/Atualizar Dados da Ordem de Serviço
	 *  7.1.1. O sistema deverá exibir todas as ações para correção das anormalidades encontradas 
	 * 
	 * @author Mariana Victor
	 * @date 26/09/2011
	 * 
	 * @param Integer
	 * 
	 * @return Collection<Object[]>
	 * @throws ControladorException
	 */
	public Collection<AcoesParaCorrecaoAnormalidadesEncontradasHelper> pesquisarAcoesParaCorrecaoAnormalidadesEncontradas(
		Integer idArquivoTextoRetornoVisitaCampo)
			throws ControladorException;
	
	/**
	 * [UC1228] Consultar Ordens de Serviço do Arquivo Texto
	 * 
	 * SB0001 - Consultar/Atualizar Dados da Ordem de Serviço
	 * 
	 * Caso o usuário clique em "Atualizar OS"
	 * 
	 * @author Mariana Victor
	 * @date 26/09/2011
	 * 
	 * @param 
	 * @throws ControladorException
	 */
	public void atualizarArquivoTxtOSVisitaCampo(int idFuncionalidadeIniciada,
			Collection<ProcessarAtualizacaoOSArquivoTextoHelper> colecaoHelper,
			Usuario usuario)
			throws ControladorException;
	
	/**
	 * [UC1228] Consultar Ordens de Serviço do Arquivo Texto
	 * 
	 * @author Mariana Victor
	 * @date 04/10/2011
	 * 
	 * @param Integer
	 * 
	 * @return Object[]
	 * @throws ControladorException
	 */
	public Object[] pesquisarDadosOSVisitaCampo(Integer idOrdemServico) 
		throws ControladorException;
	
	/**
	 * [UC1228] Consultar Ordens de Serviço do Arquivo Texto
	 * 
	 * SB0001 - Consultar/Atualizar Dados da Ordem de Serviço
	 * 
	 * Caso o usuário clique em "Atualizar OS"
	 * 
	 * @author Mariana Victor
	 * @date 26/09/2011
	 * 
	 * @param 
	 * @throws ControladorException
	 */
	public void atualizarArquivoTxtOSVisitaCampo(
			Collection<ProcessarAtualizacaoOSArquivoTextoHelper> colecaoHelper,
			Usuario usuario)
			throws ControladorException;

	/**
	 * [UC1249] Filtro OS Encerradas por Acompanhamento de Serviço
	 * [SB0010] – Pesquisar Fotos do RA
	 * 
	 * Método que vai retornar as fotos de um determinado
	 * registro de atendimento passada no parâmetro.
	 * 
	 * @author Fernanda Almeida
	 * @date 17/11/2011
	 * 
	 * @param Integer - ID (Registro de atendimento ou da Foto Ordem de Serviço)
	 * @param Boolean - Indica se o id é do RA ou da Foto (true = RA, false = Foto) 
	 * 
	 * @return Collection<OrdemServicoFoto> - Coleção das Fotos da OS
	 * @throws ControladorException
	 */
	public Collection<OrdemServicoFoto> pesquisarFotosRA(Integer id, boolean idRA) throws ControladorException;

	
	
	/**
	 * [UC1246] Informar Motivo Encerramento Atendimento
	 * 
	 * @author Nathalia Santos
	 * @date 01/11/2011
	 * 
	 * @param atendimentoMotivoEncerramento
	 * @throws ControladorException
	 */

	public void atualizarAtendimentoMotivoEncerramento(AtendimentoMotivoEncerramento atendimentoMotivoEncerramento,
			Collection colecaoAtendimentoMotivoEncAcaoCobranca)
			throws ControladorException;

	/**
	 * [UC1254] Relatório Ordem de Serviço com valores de cobrança
	 * @author Amélia Pessoa
	 * @date 24/11/2011
	 */
	public Collection<RelatorioDebitosCobradosImovelBean> filtrarRelatorioDebitosCobradosImovel(
			FiltrarRelatorioDebitosCobrancaImovelHelper filtro) throws ControladorException;
	
	/**
	 * [UC1254] Relatório Ordem de Serviço com valores de cobrança
	 * @author Amélia Pessoa
	 * @date 24/11/2011
	 */
	public Collection<RelatorioTiposServicoBean> filtrarRelatorioTiposServico(
			FiltrarRelatorioTiposServicoHelper filtro) throws ControladorException;

	/**
	 * Verifica se o Registro de Atendimento pode cancelar débitos
	 * 
	 * @author Amélia Pessoa
	 * @date 06/12/2011
	 * @param idRegistroAtendimento
	 * @return boolean
	 */
	public boolean verificarPermissaoRACancelamentoDebito(  
			int idRegistroAtendimento) throws ControladorException;
	
	/**
	 * RM1165 - Registrar em tabela os parâmetros que foram utilizados para calcular o valor do 
	 *          débito a cobrar gerado decorrente da situação de fiscalização informada
	 * UC0210 - Consultar débito a cobrar
	 * Analista: Cláudio Lira
	 * 
	 * SB0001- Exibir Parâmetros de Cálculo
	 * 
	 * Pesquisar na tabela atendimentopublico.fiscaliz_param_calc_deb com dbac_id = dbac_id
     * recebido
	 * 
	 * @autor Thúlio Araújo
	 * @since 13/12/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public FiscalizarParametroCalculoDebito pesquisarParametroCalculoDebito(Integer idDebitoACobrar)
			throws ControladorException;

	/**
	 * [UC0738] Gerar Certidão Negativa por Imóvel
	 * 
	 * [FS0005] - Verificar existência de certidão negativa de débito
	 * 
	 * @author Mariana Victor
	 * @data 15/03/2012
	 */
	public boolean verificarExistenciaCertidaoNegativaDebito(Integer idImovel)
			throws ControladorException;
    /**
	 * [UC1530] Inserir Registro de Atendimento Simplificado
	 * 
	 * [IT0008] Pesquisar Ocorrência Operacional
	 * 
	 * @author Mariana Victor
	 * @date 15/07/2013
	 * @throws ControladorException
	 */
    public Collection<OcorrenciaOperacionalHelper> pesquisarOcorrenciaOperacional(
    	OcorrenciaOperacionalFiltroHelper helperFiltro) 
			throws ControladorException;
	
    /**
	 * [UC1530] Inserir Registro de Atendimento Simplificado
	 * 
	 * [IT0010] Inserir Registro Atendimento Simplificado Operacional
	 * 
	 * @author Mariana Victor
	 * @date 15/07/2013
	 * @throws ControladorException
	 */
    public Object[] pesquisarDadosOcorrenciaOperacional(Integer idOcorrenciaOperacional)
			throws ControladorException;
    
    /**
     * [UC1275] Gerar Relatório Quantidade de Acessos Loja Virtual
     * 
     * Metodo responsavel por pesquisar quantidade de acessos realizados no portal por um intervalo de tempo.
     * 
	 * @author Flávio Ferreira
	 * @date 30/09/2013
	 * @throws ControladorException
	 */ 
    public Integer pesquisarQuantidadeAcessoLojaVirtual(Date periodoInicial, Date periodoFinal, String tipoAtendimento) throws ControladorException;
    
    /**
     * [UC1275] Gerar Relatório Quantidade de Acessos Loja Virtual
     * 
     * Metodo responsavel por pesquisar quantidade de acessos realizados no portal por um intervalo de tempo.
     * 
	 * @author Flávio Ferreira
	 * @date 30/09/2013
	 * @throws ControladorException
	 */ 
    public Integer pesquisarQuantidadeAcessoLojaVirtualTipoAtendimento(
			Date periodoInicial, Date periodoFinal, String tipoAtendimento,String indicadorServicoExecutado)
			throws ControladorException;
    
    /**
	 * [UC1275] Gerar Relatório Quantidade de Acessos Loja Virtual
	 * 
	 * @author Flávio Ferreira
	 * @date 27/09/2013
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<AcessoLojaVirtualHelper> pesquisarAcessoLojaVirtual(Date periodoInicial, Date periodoFinal, String tipoAtendimento, String indicadorServicoExecutado)
			throws ControladorException;
	
	/**
	 * [UC1275] Gerar Relatório Quantidade de Acessos Loja Virtual
	 * 
	 * @author Flávio Ferreira
	 * @date 27/09/2013
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<AcessoLojaVirtualHelper> pesquisarAcessoLojaVirtualTipoAtendimento(
		Date periodoInicial, Date periodoFinal, String tipoAtendimento, 
		String indicadorServicoExecutdo, int numeroPageina) throws ControladorException;
	
	
	 /**
	 * [UC0738] Gerar Certidão Negativa por Imóvel
	 * 
	 * @author Hugo Azevedo
	 * @date 02/05/2014
	 * @throws ErroRepositorioException
	 */
    public  Object[] obterQtdSituacoesCobrancaBloqueadas(Integer idImovel)
			throws ControladorException;
    
    
    /**
   	 * [UC0738] Gerar Certidão Negativa por Imóvel
   	 * 
   	 * @author Hugo Azevedo
   	 * @date 02/05/2014
   	 * @throws ErroRepositorioException
   	 */
	public Object[] obterQtdSituacoesCobrancaBloqueadasCliente(Integer idCliente)
			throws ControladorException;
	
	/**
     * Método responsável por<br>
     * pesquisar contrato de adesão<br>
     * associado munícipio através do
     * do imovél 
     * @author Jonathan Marcos
     * @since 15/01/2014
     * @param idImovel
     * @return Integer
     * @throws ControladorException
     */
    public Integer pesquisarContratoAdesaoMunicipioAssociadoImovel(Integer idImovel)
    		throws ControladorException;
    
	/**
	 * [UC1669] Atualizar Dados nas Tabelas Resumos Grenciais Faturamento
	 * 
	 * @author Fábio Aguiar
	 * @since 03/02/2015
	 * 
	 * @param matriculaImovel
	 * @return ObterDebitoImovelOuClienteHelper
	 * @throws ControladorException
	 */
	public void gerarResumoAtendimentoPublicoAtualizaDados(Integer matriculaImovel) throws ControladorException;
}