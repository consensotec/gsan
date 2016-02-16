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

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.localidade.Localidade;
import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.gui.micromedicao.DadosLeiturista;
import gcom.micromedicao.Leiturista;
import gcom.mobile.execucaoordemservico.bean.ArquivoTxtOSCobrancaSmartphoneHelper;
import gcom.mobile.execucaoordemservico.bean.DadosDebitoOSCobrancaSmartphoneHelper;
import gcom.mobile.execucaoordemservico.bean.GerarArquivoTxtSmartphoneHelper;
import gcom.mobile.execucaoordemservico.bean.OrdemServicoCobrancaSmartphoneHelper;
import gcom.relatorio.mobile.execucaoOrdemServico.RelatorioErrosEncerramentoOSCobrancaBean;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.FachadaException;

import java.io.BufferedReader;
import java.io.File;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.EJBLocalObject;

/**
 * Declara��o p�blica de servi�os do Session Bean do
 * ControladorExecucaoOrdemServico
 * 
 * @author Andr� Miranda
 * @date 13/11/2015
 */
public interface ControladorExecucaoOrdemServicoLocal extends EJBLocalObject {
	/** 
	 * [UC1497] - Gerar Arquivo Texto de Ordens de Servico para Smartphone
	 *  
	 * @author Andr� Miranda
	 * @date 13/11/2015
	 */
	public Collection<Empresa> validarEmpresaPrincipal(Usuario usuario) throws ControladorException;
	
	/**
	 * [UC1498] - Consultar Arquivo Texto de Ordens de Servi�o para Smartphone (Novo)
	 *
	 * @author Jean Varela
	 * @throws ErroRepositorioException 
	 * @date   16/11/2015	
	 */
	public Collection<ArquivoTxtOSCobrancaSmartphoneHelper> buscarColecaoArquivoTextoOSCobrancaSmartphone(Integer idEmpresa,
															Integer idTipoServico, Integer referenciaCronograma,
															Integer idCobrancaGrupo, Integer[] idsLocalidade, Integer idComandoEventual,Integer idAgenteComercial,Integer idSituacao)  throws ControladorException;

	/**
	 * [UC1498] - Consultar Arquivo Texto de Ordens de Servi�o para Smartphone (Novo)
	 *
	 * @author Jean Varela
	 * @throws ErroRepositorioException 
	 * @date   16/11/2015	
	 */
	public void atualizarListaArquivoTextoOSCobrancaSmartphone(Collection<ArquivoTxtOSCobrancaSmartphoneHelper> colecaoArquivoTextoOSCobrancaSmartphone,
															   Integer idSituacaoLeituraNova, Leiturista leiturista) throws ControladorException;

	/**
	 * [UC1497] - Gerar Arquivo Texto de Ordens de Servi�o para Smartphone
	 * [IT0017] - Exibir Lista de Comandos Eventuais
	 * 
	 * @author Andr� Miranda
	 * @date 13/11/2015
	 */
	public Collection<CobrancaAcaoAtividadeComando> pesquisarComandosEventuais(Integer idServicoTipo, Integer idEmpresa,
			Date dataInicial, Date dataFinal) throws ControladorException;

	/**
	 * [UC1497] - Gerar Arquivo Texto de Ordens de Servi�o para Smartphone
	 * [IT0005] - Exibir Lista de Localidades
	 * 
	 * @author Andr� Miranda
	 * @date 13/11/2015
	 */
	public Collection<Localidade> pesquisarLocalidadeGrupoCobranca(Integer idGrupoCobranca) throws ControladorException;

	/**
	 * [UC1497] - Gerar Arquivo Texto de Ordens de Servi�o para Smartphone
	 * [SB0001] - Exibir Totais Ordens de Servi�o
	 * [IT0018] - Selecionar Ordens de Servi�o Cronograma
	 * 
	 * @author Andr� Miranda
	 * @date 13/11/2015
	 */
	public List<GerarArquivoTxtSmartphoneHelper> consultarQuantidadeOrdemServicoCronograma(
			Integer idCobrancaGrupo, Integer idEmpresa, Integer referencia, Integer idTipoServico, Integer[] idsLocalidade)
			throws ControladorException;

	/**
	 * [UC1497] - Gerar Arquivo Texto de Ordens de Servi�o para Smartphone
	 * [SB0001] - Exibir Totais Ordens de Servi�o
	 * [IT0019] - Selecionar Ordens de Servi�o Comando
	 * 
	 * @author Andr� Miranda
	 * @date 13/11/2015
	 */
	public List<GerarArquivoTxtSmartphoneHelper> consultarQuantidadeOrdemServicoComando(Integer idComando,
			Integer idEmpresa, Integer idTipoServico) throws ControladorException;

	/**
	 * [UC1497] - Gerar Arquivo Texto de Ordens de Servi�o para Smartphone
	 * [SB0002] - Exibir Ordens de Servi�o
	 * [IT0021] - Selecionar Ordens de Servi�o Cronograma das rotas selecionadas
	 * 
	 * @author Andr� Miranda
	 * @date 13/11/2015
	 */
	public List<GerarArquivoTxtSmartphoneHelper> consultarOrdemServicoCronograma(Integer[] idsRota, Integer idEmpresa,
			Integer referencia, Integer idTipoServico) throws ControladorException;

	/**
	 * [UC1497] - Gerar Arquivo Texto de Ordens de Servi�o para Smartphone
	 * [SB0002] - Exibir Ordens de Servi�o
	 * [IT0022] - Selecionar Ordens de Servi�o Comando das rotas selecionadas
	 * 
	 * @author Andr� Miranda
	 * @date 13/11/2015
	 */
	public List<GerarArquivoTxtSmartphoneHelper> consultarOrdemServicoComando(Integer[] idsRota, Integer idEmpresa,
			Integer idTipoServico, Integer idComando) throws ControladorException;

	/** 
	 * [UC1497] - Gerar Arquivo Texto de Ordens de Servico para Smartphone
	 * [IT0011] - Gerar Arquivo Texto de Ordens de Servi�o
	 *  
	 * @author Vivianne Sousa	
	 * @date 19/11/2015
	 */
	public void inserirParametrosArquivoTextoOSCobranca(ParametrosArquivoTextoOSCobranca parametros, 
			Integer idLeiturista, Integer[] idsRota, Collection<Integer[]> colecaoOS) throws ControladorException;

	/**
	 * [UC01484] - Gerar Arquivo de Ida para Execucao de OS de Cobranca Android
	 * 
	 * @author Vivianne Sousa	
	 * @date 19/11/2015
	 */
	public byte[] gerarArquivoTextoIdaExecucaoOSCobranca(Integer idArquivoTextoOSCobranca) throws ControladorException;

	/**
	 * [UC1498] - Consultar Arquivo Texto de Ordens de Servi�o para Smartphone (Novo)
	 * [IT0008] - Exibir Lista de Agentes Comerciais.
	 *
	 * @author Jean Varela
	 * @date   19/11/2015	
	 */
	public Collection<DadosLeiturista>  pesquisarColecaoAgenteComercial(Integer idEmpresa) throws ControladorException;
	
	/**
	 * [UC1499] Consultar Dados Arquivo Texto OS Cobran�a para Smartphone
	 * Retorna as informa��es das ordens de servi�o
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
			Integer idArquivoTexto) throws ControladorException ;
	
	/**
	 * [UC1500] - Consultar Dados OS de Cobran�a para Smartphone
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
	public Collection<ExecucaoOSFoto> pesquisarFotosOSCobrancaSmartphone(Integer idArquivo, Integer idOS) throws ControladorException;
	
	/**
	 * [UC1500] Consultar Dados OS de Cobran�a para Smartphone
	 * 
	 * @author Rafael Corr�a,Vivianne Sousa
	 * @date 18/06/2013, 25/11/2015
	 */
	public RelatorioErrosEncerramentoOSCobrancaBean efetuarExecucaoOSCobrancaSmartphone(Integer idArquivo, Integer idOS, DadosDebitoOSCobrancaSmartphoneHelper helperDebito, Usuario usuario) throws ControladorException;
	
	/**
	 * [UC1500] - Consultar Dados OS de Cobran�a para Smartphone
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
			Integer idOS, Integer idSituacao) throws ControladorException;
	
	/**
	 * [UC-1498] Consultar Arquivo Texto de Ordens de Servi�o para Smartphone
	 * [IT0016] Excluir Arquivo
	 * 
	 * Exclui os arquivos informados 
	 * 
	 * @author Bruno Barros
	 * @date 19/09/2013
	 * @param ids
	 */
	public void excluirArquivoTextoOrdensServicoCobranca( String[] ids ) throws ControladorException;
	
	/**
	 * [UC1487] Processar Arquivo Texto Dispositivo Movel Execu��o de OS
	 * 
	 * @author Bruno Barros, Mariana Victor, Rodrigo Cabral
	 * @date 18/06/2013, 12/11/2013, 01/12/2015
	 * 
	 * @author Andr� Miranda
	 * @date 24/12/2015
	 * 
	 * @param buffer Buffer do arquivo texto
	 * @param pasta Pasta onde o arquivo zip foi extra�do
	 * @throws ControladorException
	 */
	public void atualizarMovimentacaoExecucaoOS(BufferedReader buffer, File pasta) throws ControladorException;
	
	/**
	 * [UC1499] Consultar Dados Arquivo Texto OS Cobranca para Smartphone
	 * 
	 * @author Vivianne Sousa
	 * @date 02/12/2015
	 */
	public Collection<Localidade> pesquisarLocalidadesArquivo(Integer idArquivo ) 
			throws ControladorException;
	
	/**
	 * [UC-1487] Processar Arquivo Texto Dispositivo Movel Execu��o de OS
	 * 
	 * Metodo que ira pesquisar o arquivo que sera carregado no celular
	 * 
	 * @autor Bruno Barros
	 * @date 17/06/2013
	 * 
	 * @param imei  - Imei do aparalho que ira receber o arquivo
	 * 
	 * @return array de bytes com o arquivo
	 * 
	 * @throws ControladorException
	 */
	public byte[] baixarArquivoTextoExecucaoOS(long imei) throws ControladorException;
	
	/**
	 * [UC1499] Consultar Dados Arquivo Texto OS Cobranca para Smartphone
	 * 
	 * @author Rafael Correa
	 * @date 01/07/2013
	 */
	public Collection<RelatorioErrosEncerramentoOSCobrancaBean> efetuarExecucaoColecaoOSCobrancaSmartphone(
			Integer idArquivo, Collection<Integer> idsOS, Usuario usuario)throws ControladorException;
	
	
	public void pesquisarArquivoLiberadoEmCampoPorAgenteComercial(List<ArquivoTxtOSCobrancaSmartphoneHelper> arquivos) throws ControladorException;
	
	/**
	 * [UC1487] Processar Requisicao Dispositivo Movel Execucao OS
	 * 
	 * Recebe as fotos envidas pelo sistema gsaneos e as persiste na base de dados
	 * 
	 * @author Bruno Barros
	 * @date 03/07/2013
	 * 
	 * @author Andr� Miranda
	 * @date 08/12/2015
	 * 
	 * @param numeroOS N�mero da Ordem de servi�o a qual a OS pertence
	 * @param tipoFoto Tipo da foto enviada ( Antes da Execu��o, Durante a Execu��o, Ap�s a Execu��o, Fachada do Im�vel
	 * @param foto Array de bytes contendo a foto
	 * @throws ControladorException
	 */
	public void inserirFotoOrdemServicoCobrancaSmartphone(int idArquivo, int numeroOS, int tipoFoto, byte[] foto, BigDecimal coordenadaX, BigDecimal coordenadaY)
			throws ControladorException;
	
	/**
	 * [UC1498] - Consultar Arquivo Texto de Ordens de Servi�o para Smartphone (Novo)
	 *
	 * Esse m�todo foi criado, pois o mesmo apenas � utilizado na tela de transmitir 
	 * arquivo texto.	
	 *
	 * @author Jean Varela
	 * @date   10/12/2015	
	 */
	public byte[] baixarArquivoTextoExecucaoOrdemServico(long imei,Integer idArquivo) throws ControladorException;
}
