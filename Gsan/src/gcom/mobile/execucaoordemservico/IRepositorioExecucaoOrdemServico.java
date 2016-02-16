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
package gcom.mobile.execucaoordemservico;

import gcom.cadastro.localidade.Localidade;
import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.DocumentoTipo;
import gcom.micromedicao.Leiturista;
import gcom.mobile.execucaoordemservico.bean.ArquivoTxtOSCobrancaSmartphoneHelper;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author Andr� Miranda
 * @date 16/11/2015
 */
public interface IRepositorioExecucaoOrdemServico {


	/**
	 * [UC1498] - Consultar Arquivo Texto de Ordens de Servi�o para Smartphone (Novo)
	 *
	 * @author Jean Varela
	 * @throws ErroRepositorioException 
	 * @date   16/11/2015	
	 */
	public Collection<Integer> pesquisarArquivosEmAbertoPorLeiturista(Integer idLeiturista) throws ErroRepositorioException;
	
	/**
	 * [UC1498] - Consultar Arquivo Texto de Ordens de Servi�o para Smartphone (Novo)
	 *
	 * @author Jean Varela
	 * @throws ErroRepositorioException 
	 * @date   16/11/2015	
	 */
	public void atualizarListaArquivoTextoOSCobrancaSmartphone(
			Collection<ArquivoTxtOSCobrancaSmartphoneHelper> colecaoArquivoTextoOSCobrancaSmartphone,
			Integer idSituacaoLeituraNova, Leiturista leiturista, Date date) throws ErroRepositorioException;

	/**
	 * [UC1497] - Gerar Arquivo Texto de Ordens de Servi�o para Smartphone
	 * [IT0017] - Exibir Lista de Comandos Eventuais
	 * 
	 * @author Andr� Miranda
	 * @date 13/11/2015
	 */
	public Collection<CobrancaAcaoAtividadeComando> pesquisarComandosEventuais(Integer idServicoTipo, Integer idEmpresa,
			Date dataInicial, Date dataFinal) throws ErroRepositorioException;

	/**
	 * [UC1497] - Gerar Arquivo Texto de Ordens de Servi�o para Smartphone
	 * [IT0005] - Exibir Lista de Localidades
	 * 
	 * @author Andr� Miranda
	 * @date 13/11/2015
	 */
	public Collection<Localidade> pesquisarLocalidadeGrupoCobranca(Integer idGrupoCobranca) throws ErroRepositorioException;

	/**
	 * [UC1497] - Gerar Arquivo Texto de Ordens de Servi�o para Smartphone
	 * [SB0001] - Exibir Totais Ordens de Servi�o
	 * [IT0018] - Selecionar Ordens de Servi�o Cronograma
	 * 
	 * @author Andr� Miranda
	 * @date 13/11/2015
	 */
	public Collection<Object[]> consultarQuantidadeOrdemServicoCronograma(Integer idCobrancaGrupo, Integer idEmpresa,
			Integer referencia, Integer idTipoServico, Integer[] idsLocalidade) throws ErroRepositorioException;

	/**
	 * [UC1497] - Gerar Arquivo Texto de Ordens de Servi�o para Smartphone
	 * [SB0001] - Exibir Totais Ordens de Servi�o
	 * [IT0019] - Selecionar Ordens de Servi�o Comando
	 * 
	 * @author Andr� Miranda
	 * @date 13/11/2015
	 */
	public Collection<Object[]> consultarQuantidadeOrdemServicoComando(Integer idComando,
			Integer idEmpresa, Integer idTipoServico) throws ErroRepositorioException;

	/**
	 * [UC1497] - Gerar Arquivo Texto de Ordens de Servi�o para Smartphone
	 * [SB0002] - Exibir Ordens de Servi�o
	 * [IT0021] - Selecionar Ordens de Servi�o Cronograma das rotas selecionadas
	 * 
	 * @author Andr� Miranda
	 * @date 13/11/2015
	 */
	public Collection<Object[]> consultarOrdemServicoCronograma(Integer[] idsRota, Integer idEmpresa,
			Integer referencia, Integer idTipoServico) throws ErroRepositorioException;

	/**
	 * [UC1497] - Gerar Arquivo Texto de Ordens de Servi�o para Smartphone
	 * [SB0002] - Exibir Ordens de Servi�o
	 * [IT0022] - Selecionar Ordens de Servi�o Comando das rotas selecionadas
	 * 
	 * @author Andr� Miranda
	 * @date 13/11/2015
	 */
	public Collection<Object[]> consultarOrdemServicoComando(Integer[] idsRota, Integer idEmpresa,
			Integer idTipoServico, Integer idComando) throws ErroRepositorioException;

	/**
	 *
	 * [UC1497] - Gerar Arquivo Texto de Ordens de Servico para Smartphone
	 * [IT0021] - Selecionar D�bitos da OS
	 * 
	 * @author Hugo Azevedo
	 * @date 24/10/2013
	 */
	public Collection<Object[]> obterItensDocCobranca(Integer idOrdemServico)throws ErroRepositorioException;
	
	/**
	 * [UC01484] - Gerar Arquivo de Ida para Execucao de OS de Cobranca Android
	 * 
	 * @author Vivianne Sousa	
	 * @date 19/11/2015
	 */
	public Collection<Object[]> consultarArquivoTextoOSCobrancaItem(Integer idArquivoTextoOSCobranca) throws ErroRepositorioException ;
	
	/**
	 * [UC1498] - Consultar Arquivo Texto de Ordens de Servi�o para Smartphone
	 * 
	 * @author Vivianne Sousa	
	 * @date 23/11/2015
	 */
	public Collection<Object[]> consultarDadosArquivoTextoOSCobranca(Integer idEmpresa, Integer idTipoServico, 
			Integer referenciaCronograma, Integer idCobrancaGrupo, Integer[] idsLocalidade, Integer idComandoEventual,Integer idAgenteComercial,Integer idSituacao) throws ErroRepositorioException;
	
	/**
	 * [UC1498] - Consultar Arquivo Texto de Ordens de Servi�o para Smartphone
	 * 
	 * @author Vivianne Sousa	
	 * @date 23/11/2015
	 */
	public List<Integer> pesquisarSetorArquivoTextoOSCobrancaItem(Integer idArquivoTextoOSCobranca) throws ErroRepositorioException;
	
	/**
	 * [UC1498] - Consultar Arquivo Texto de Ordens de Servi�o para Smartphone
	 * 
	 * @author Vivianne Sousa	
	 * @date 23/11/2015
	 */
	public List<Integer> pesquisarRotaArquivoTextoOSCobrancaItem(Integer idArquivoTextoOSCobranca) throws ErroRepositorioException;
	
	/**
	 * [UC1498] Consultar Arquivo Texto de Ordens de Servi�o para Smartphone
	 * 
	 * @author Vivianne Sousa	
	 * @date 23/11/2015
	 */
	public List<Integer> pesquisarLocalidadeArquivoTextoOSCobrancaItem(Integer idArquivoTextoOSCobranca) throws ErroRepositorioException;
	
	/**
	 * [UC1499] Consultar Dados Arquivo Texto OS Cobran�a para Smartphone
	 * Retorna as informa��es das ordens de servi�o 
	 * 
	 * @author Bruno Barros,Vivianne Sousa
	 * @date 27/06/2013,24/11/2015
	 * 
	 * @param Integer
	 * 
	 * @return Collection<Object[]> 
	 * @throws ControladorException
	 */
	public Collection<Object[]> pesquisarDadosOrdensServicoCobrancaSmartphone(
			Integer idArquivoTexto)	throws ErroRepositorioException;

	/**
	 * [UC1498] Consultar Arquivo Texto de Ordens de Servi�o para Smartphone
	 * 
	 * @author Andr� Miranda
	 * @date 13/11/2015
	 */
	public List<Integer> pesquisarIdContaCobrancaDocumentoItem(Integer idCobrancaDocumento) throws ErroRepositorioException;
	
	/**
	 * [UC1498] - Consultar Arquivo Texto de Ordens de Servi�o para Smartphone (Novo)
	 *
	 * @author Jean Varela
	 * @throws ErroRepositorioException 
	 * @date   25/11/2015	
	 */
	public Collection obterColecaoAgenteComercial(Integer idEmpresa) throws ErroRepositorioException;
	
	/**
	 * [UC1499] Consultar Dados Arquivo Texto OS Cobranca para Smartphone
	 * 
	 * @author Rafael Correa
	 * @date 25/06/2013
	 */
	public Object[] pesquisarDadosEncerramentoOSCobrancaSmartphone(Integer idArquivo, Integer idOS) throws ErroRepositorioException;
	
	/**
	 * [UC1499] Consultar Dados Arquivo Texto OS Cobranca para Smartphone
	 * 
	 * @author Rafael Correa
	 * @date 25/06/2013
	 */	
	public Collection<ExecucaoOSFoto> pesquisarFotosOSCobrancaSmartphone(Integer idArquivo, Integer idOS) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1499] Consultar Dados Arquivo Texto OS Cobranca para Smartphone
	 * 
	 * @author Rafael Correa
	 * @date 18/06/2013
	 */
	public Object[] pesquisarDadosCorteOSCobrancaSmartphone(Integer idArquivo, Integer idOS)
			throws ErroRepositorioException;
	
	/**
	 * [UC1499] Consultar Dados Arquivo Texto OS Cobranca para Smartphone
	 * 
	 * @author Rafael Correa
	 * @date 04/07/2013
	 */
	public void atualizarSituacaoOSSmartphone(Integer idArquivo, Integer idOS, Short situacao) throws ErroRepositorioException;
	
	/**
	 * [UC1499] Consultar Dados Arquivo Texto OS Cobranca para Smartphone
	 * 
	 * @author Rafael Correa
	 * @date 25/06/2013
	 */
	public Collection<Object[]> pesquisarDadosFiscalizacaoOSCobrancaSmartphone(Integer idArquivo, Integer idOS)
			throws ErroRepositorioException ;
	
	/**
	 * [UC01484] - Gerar Arquivo de Ida para Execucao de OS de Cobranca Android
	 * 
	 * @author Vivianne Sousa	
	 * @date 30/11/2015
	 */
	public Collection<DocumentoTipo> pesquisarDocumentoTipoDaSituacaoFiscalizacao() throws ErroRepositorioException;
	
	/**
	 * [UC-1498] Consultar Arquivo Texto de Ordens de Servi�o para Smartphone
	 * [IT0016] Excluir Arquivo
	 * 
	 * Verifica se entre lista de Ids passados como par�metro existe algum
	 * com situa��o diferente de DISPON�VEL
	 * 
	 * @author Bruno Barros
	 * @date 19/09/2013
	 * @param ids
	 * @throws ErroRepositorioException
	 */
	public Integer verificarSituacaoArquivosOSCobrancaParaExclusao(String[] ids) throws ErroRepositorioException;

	/**
	 * [UC-1498] Consultar Arquivo Texto de Ordens de Servi�o para Smartphone
	 * [IT0016] Excluir Arquivo
	 * 
	 * Exclui da base de dados os IDs passados como par�metros
	 * 
	 * @author Bruno Barros
	 * @date 19/09/2013
	 * @param ids
	 * @throws ErroRepositorioException
	 */	
	public void excluirArquivosOSCobranca(String[] ids) throws ErroRepositorioException;
	
	/**
	 * [UC1499] Consultar Dados Arquivo Texto OS Cobranca para Smartphone
	 * 
	 * @author Vivianne Sousa
	 * @date 02/12/2015
	 */
	public Collection<String> pesquisarLocalidadesArquivo(Integer idArquivo ) 
			throws ErroRepositorioException;
	
	/**
	 * [UC-1487] Processar Arquivo Texto Dispositivo Movel Execu��o de OS
	 * 
	 * Pesquisa o Arquivo Texto da Execu��o de OS para o imei informado
	 * 
	 * @author Bruno Barros
	 * @date 17/06/2013
	 * 
	 * @param long imei - Imei para sele��o do arquivo de ida
	 * 
	 * @return Id do leiturista que esta pesquisando o arquivo
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarArquivoTextoExecucaoOS(Integer leiturista) throws ErroRepositorioException;
	
	/**
	 * [UC1484] - Gerar Arquivo de Ida para Execucao de OS de Cobranca Android
	 * 
	 * @author Vivianne Sousa
	 * @date 04/12/2015
	 */
	public Integer obterNumDiasVencimentoCobrancaAcao(Integer idOrdemServico) throws ErroRepositorioException;

	/**
	 * [UC1498] Consultar Arquivo Texto de Ordens de Servi�o para Smartphone
	 * @author Vivianne Sousa
	 * @date 04/12/2015
	 */
	public ArquivoTextoOSCobranca pesquisarArquivoTextoOSCobrancaEmCampoPorCadastrador(Integer idAgenteComercial)  throws ErroRepositorioException;
	
	/**
	 * [UC1498] Consultar Arquivo Texto de Ordens de Servi�o para Smartphone
	 * @author Vivianne Sousa
	 * @date 04/12/2015
	 */
	public CobrancaDocumento pesquisarDocumentoCobrancaOS(Integer idOrdemServico)  throws ErroRepositorioException;
	
	/**
	 * [UC1498] Consultar Arquivo Texto de Ordens de Servi�o para Smartphone
	 * @author Vivianne Sousa
	 * @date 04/12/2015
	 */
	public String pesquisarArquivoEmCampoPorAgenteComercial(Integer idAgenteComercial) throws ErroRepositorioException;
	
	/**
	 * [UC1498] Consultar Arquivo Texto de Ordens de Servi�o para Smartphone
	 * @author Vivianne Sousa
	 * @date 15/12/2015
	 */
	public Integer pesquisarQtdeExecucaoOSOrdemServico(Integer idArquivoTextoOSCobranca)  throws ErroRepositorioException;
	
	/**
	 * [UC1484] - Gerar Arquivo de Ida para Execucao de OS de Cobranca Android
	 * 
	 * @author Vivianne Sousa
	 * @date 16/12/2015
	 */
	public Integer obterNumDiasValidadeCobrancaAcao(Integer idOrdemServico) throws ErroRepositorioException;
	
	/**
	 * [UC1498] - Consultar Arquivo Texto de Ordens de Servi�o para Smartphone
	 * 
	 * @author Jean Varela 	
	 * @date 05/01/2015
	 */
	public List<Integer> pesquisarQuadrasArquivoTextoOSCobrancaItem(Integer idArquivoTextoOSCobranca) throws ErroRepositorioException;
}
