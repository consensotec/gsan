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
 * Declaração pública de serviços do Session Bean do
 * ControladorExecucaoOrdemServico
 * 
 * @author André Miranda
 * @date 13/11/2015
 */
public interface ControladorExecucaoOrdemServicoLocal extends EJBLocalObject {
	/** 
	 * [UC1497] - Gerar Arquivo Texto de Ordens de Servico para Smartphone
	 *  
	 * @author André Miranda
	 * @date 13/11/2015
	 */
	public Collection<Empresa> validarEmpresaPrincipal(Usuario usuario) throws ControladorException;
	
	/**
	 * [UC1498] - Consultar Arquivo Texto de Ordens de Serviço para Smartphone (Novo)
	 *
	 * @author Jean Varela
	 * @throws ErroRepositorioException 
	 * @date   16/11/2015	
	 */
	public Collection<ArquivoTxtOSCobrancaSmartphoneHelper> buscarColecaoArquivoTextoOSCobrancaSmartphone(Integer idEmpresa,
															Integer idTipoServico, Integer referenciaCronograma,
															Integer idCobrancaGrupo, Integer[] idsLocalidade, Integer idComandoEventual,Integer idAgenteComercial,Integer idSituacao)  throws ControladorException;

	/**
	 * [UC1498] - Consultar Arquivo Texto de Ordens de Serviço para Smartphone (Novo)
	 *
	 * @author Jean Varela
	 * @throws ErroRepositorioException 
	 * @date   16/11/2015	
	 */
	public void atualizarListaArquivoTextoOSCobrancaSmartphone(Collection<ArquivoTxtOSCobrancaSmartphoneHelper> colecaoArquivoTextoOSCobrancaSmartphone,
															   Integer idSituacaoLeituraNova, Leiturista leiturista) throws ControladorException;

	/**
	 * [UC1497] - Gerar Arquivo Texto de Ordens de Serviço para Smartphone
	 * [IT0017] - Exibir Lista de Comandos Eventuais
	 * 
	 * @author André Miranda
	 * @date 13/11/2015
	 */
	public Collection<CobrancaAcaoAtividadeComando> pesquisarComandosEventuais(Integer idServicoTipo, Integer idEmpresa,
			Date dataInicial, Date dataFinal) throws ControladorException;

	/**
	 * [UC1497] - Gerar Arquivo Texto de Ordens de Serviço para Smartphone
	 * [IT0005] - Exibir Lista de Localidades
	 * 
	 * @author André Miranda
	 * @date 13/11/2015
	 */
	public Collection<Localidade> pesquisarLocalidadeGrupoCobranca(Integer idGrupoCobranca) throws ControladorException;

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
			throws ControladorException;

	/**
	 * [UC1497] - Gerar Arquivo Texto de Ordens de Serviço para Smartphone
	 * [SB0001] - Exibir Totais Ordens de Serviço
	 * [IT0019] - Selecionar Ordens de Serviço Comando
	 * 
	 * @author André Miranda
	 * @date 13/11/2015
	 */
	public List<GerarArquivoTxtSmartphoneHelper> consultarQuantidadeOrdemServicoComando(Integer idComando,
			Integer idEmpresa, Integer idTipoServico) throws ControladorException;

	/**
	 * [UC1497] - Gerar Arquivo Texto de Ordens de Serviço para Smartphone
	 * [SB0002] - Exibir Ordens de Serviço
	 * [IT0021] - Selecionar Ordens de Serviço Cronograma das rotas selecionadas
	 * 
	 * @author André Miranda
	 * @date 13/11/2015
	 */
	public List<GerarArquivoTxtSmartphoneHelper> consultarOrdemServicoCronograma(Integer[] idsRota, Integer idEmpresa,
			Integer referencia, Integer idTipoServico) throws ControladorException;

	/**
	 * [UC1497] - Gerar Arquivo Texto de Ordens de Serviço para Smartphone
	 * [SB0002] - Exibir Ordens de Serviço
	 * [IT0022] - Selecionar Ordens de Serviço Comando das rotas selecionadas
	 * 
	 * @author André Miranda
	 * @date 13/11/2015
	 */
	public List<GerarArquivoTxtSmartphoneHelper> consultarOrdemServicoComando(Integer[] idsRota, Integer idEmpresa,
			Integer idTipoServico, Integer idComando) throws ControladorException;

	/** 
	 * [UC1497] - Gerar Arquivo Texto de Ordens de Servico para Smartphone
	 * [IT0011] - Gerar Arquivo Texto de Ordens de Serviço
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
	 * [UC1498] - Consultar Arquivo Texto de Ordens de Serviço para Smartphone (Novo)
	 * [IT0008] - Exibir Lista de Agentes Comerciais.
	 *
	 * @author Jean Varela
	 * @date   19/11/2015	
	 */
	public Collection<DadosLeiturista>  pesquisarColecaoAgenteComercial(Integer idEmpresa) throws ControladorException;
	
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
			Integer idArquivoTexto) throws ControladorException ;
	
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
	public Collection<ExecucaoOSFoto> pesquisarFotosOSCobrancaSmartphone(Integer idArquivo, Integer idOS) throws ControladorException;
	
	/**
	 * [UC1500] Consultar Dados OS de Cobrança para Smartphone
	 * 
	 * @author Rafael Corrêa,Vivianne Sousa
	 * @date 18/06/2013, 25/11/2015
	 */
	public RelatorioErrosEncerramentoOSCobrancaBean efetuarExecucaoOSCobrancaSmartphone(Integer idArquivo, Integer idOS, DadosDebitoOSCobrancaSmartphoneHelper helperDebito, Usuario usuario) throws ControladorException;
	
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
			Integer idOS, Integer idSituacao) throws ControladorException;
	
	/**
	 * [UC-1498] Consultar Arquivo Texto de Ordens de Serviço para Smartphone
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
	 * [UC-1487] Processar Arquivo Texto Dispositivo Movel Execução de OS
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
	 * @author André Miranda
	 * @date 08/12/2015
	 * 
	 * @param numeroOS Número da Ordem de serviço a qual a OS pertence
	 * @param tipoFoto Tipo da foto enviada ( Antes da Execução, Durante a Execução, Após a Execução, Fachada do Imóvel
	 * @param foto Array de bytes contendo a foto
	 * @throws ControladorException
	 */
	public void inserirFotoOrdemServicoCobrancaSmartphone(int idArquivo, int numeroOS, int tipoFoto, byte[] foto, BigDecimal coordenadaX, BigDecimal coordenadaY)
			throws ControladorException;
	
	/**
	 * [UC1498] - Consultar Arquivo Texto de Ordens de Serviço para Smartphone (Novo)
	 *
	 * Esse método foi criado, pois o mesmo apenas é utilizado na tela de transmitir 
	 * arquivo texto.	
	 *
	 * @author Jean Varela
	 * @date   10/12/2015	
	 */
	public byte[] baixarArquivoTextoExecucaoOrdemServico(long imei,Integer idArquivo) throws ControladorException;
}
