/*
* Copyright (C) 2007-2007 the GSAN -Sistema Integrado de Gest„o de ServiÁos de Saneamento
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
* Foundation, Inc., 59 Temple Place -Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN -Sistema Integrado de Gest„o de ServiÁos de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Ara˙jo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cl·udio de Andrade Lira
* Denys Guimar„es Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* FabÌola Gomes de Ara˙jo
* Fl·vio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento J˙nior
* Homero Sampaio Cavalcanti
* Ivan SÈrgio da Silva J˙nior
* JosÈ Edmar de Siqueira
* JosÈ Thiago TenÛrio Lopes
* K·ssia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* M·rcio Roberto Batista da Silva
* Maria de F·tima Sampaio Leite
* Micaela Maria Coelho de Ara˙jo
* Nelson MendonÁa de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael CorrÍa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Ara˙jo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* S·vio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa È software livre; vocÍ pode redistribuÌ-lo e/ou
* modific·-lo sob os termos de LicenÁa P˙blica Geral GNU, conforme
* publicada pela Free Software Foundation; vers„o 2 da
* LicenÁa.
* Este programa È distribuÌdo na expectativa de ser ˙til, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implÌcita de
* COMERCIALIZA«√O ou de ADEQUA«√O A QUALQUER PROP”SITO EM
* PARTICULAR. Consulte a LicenÁa P˙blica Geral GNU para obter mais
* detalhes.
* VocÍ deve ter recebido uma cÛpia da LicenÁa P˙blica Geral GNU
* junto com este programa; se n„o, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gsan.faturamento;

import gsan.arrecadacao.ControladorArrecadacaoLocal;
import gsan.arrecadacao.ControladorArrecadacaoLocalHome;
import gsan.atendimentopublico.ligacaoagua.LigacaoAgua;
import gsan.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gsan.batch.ControladorBatchLocal;
import gsan.batch.ControladorBatchLocalHome;
import gsan.cadastro.FiltroVersaoSistemasAndroid;
import gsan.cadastro.SistemaAndroid;
import gsan.cadastro.VersaoSistemasAndroid;
import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.cliente.ClienteConta;
import gsan.cadastro.cliente.ClienteImovel;
import gsan.cadastro.cliente.ClienteRelacaoTipo;
import gsan.cadastro.endereco.ControladorEnderecoLocal;
import gsan.cadastro.endereco.ControladorEnderecoLocalHome;
import gsan.cadastro.endereco.Logradouro;
import gsan.cadastro.endereco.LogradouroBairro;
import gsan.cadastro.geografico.Bairro;
import gsan.cadastro.imovel.Categoria;
import gsan.cadastro.imovel.ControladorImovelLocal;
import gsan.cadastro.imovel.ControladorImovelLocalHome;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.imovel.ImovelContaEnvio;
import gsan.cadastro.imovel.ImovelPerfil;
import gsan.cadastro.imovel.PocoTipo;
import gsan.cadastro.imovel.Subcategoria;
import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.GerenciaRegional;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.Quadra;
import gsan.cadastro.localidade.QuadraFace;
import gsan.cadastro.localidade.SetorComercial;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cobranca.CobrancaDocumento;
import gsan.cobranca.CobrancaDocumentoItem;
import gsan.cobranca.ControladorCobrancaLocal;
import gsan.cobranca.ControladorCobrancaLocalHome;
import gsan.cobranca.IRepositorioCobranca;
import gsan.cobranca.bean.CalcularValorDataVencimentoAnteriorHelper;
import gsan.fachada.Fachada;
import gsan.faturamento.bean.EmitirContaHelper;
import gsan.faturamento.consumotarifa.ConsumoTarifa;
import gsan.faturamento.conta.Conta;
import gsan.faturamento.conta.ContaComunicado;
import gsan.faturamento.credito.CreditoRealizado;
import gsan.faturamento.credito.CreditoTipo;
import gsan.faturamento.debito.DebitoCobrado;
import gsan.micromedicao.ArquivoTextoRoteiroEmpresa;
import gsan.micromedicao.ArquivoTextoRoteiroEmpresaDivisao;
import gsan.micromedicao.ControladorMicromedicaoLocal;
import gsan.micromedicao.ControladorMicromedicaoLocalHome;
import gsan.micromedicao.FiltroArquivoTextoRoteiroEmpresa;
import gsan.micromedicao.FiltroRota;
import gsan.micromedicao.Leiturista;
import gsan.micromedicao.Rota;
import gsan.micromedicao.ServicoTipoCelular;
import gsan.micromedicao.SituacaoTransmissaoLeitura;
import gsan.micromedicao.consumo.ConsumoAnormalidade;
import gsan.micromedicao.consumo.ConsumoAnormalidadeAcao;
import gsan.micromedicao.consumo.ConsumoHistorico;
import gsan.micromedicao.consumo.ConsumoTipo;
import gsan.micromedicao.consumo.FiltroConsumoAnormalidade;
import gsan.micromedicao.consumo.FiltroConsumoAnormalidadeAcao;
import gsan.micromedicao.consumo.FiltroConsumoTipo;
import gsan.micromedicao.consumo.LigacaoTipo;
import gsan.micromedicao.hidrometro.Hidrometro;
import gsan.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gsan.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gsan.micromedicao.hidrometro.HidrometroProtecao;
import gsan.micromedicao.leitura.FiltroLeituraAnormalidade;
import gsan.micromedicao.leitura.LeituraAnormalidade;
import gsan.micromedicao.leitura.LeituraSituacao;
import gsan.micromedicao.leitura.LeituraTipo;
import gsan.micromedicao.medicao.FiltroMedicaoHistorico;
import gsan.micromedicao.medicao.MedicaoHistorico;
import gsan.micromedicao.medicao.MedicaoTipo;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesJNDI;
import gsan.util.ConstantesSistema;
import gsan.util.ControladorException;
import gsan.util.ControladorUtilLocal;
import gsan.util.ControladorUtilLocalHome;
import gsan.util.Criptografia;
import gsan.util.ErroCriptografiaException;
import gsan.util.ErroRepositorioException;
import gsan.util.ServiceLocator;
import gsan.util.ServiceLocatorException;
import gsan.util.SistemaException;
import gsan.util.Util;
import gsan.util.ZipUtil;
import gsan.util.filtro.ParametroNulo;
import gsan.util.filtro.ParametroSimples;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.zip.GZIPOutputStream;

import javax.ejb.CreateException;
import javax.ejb.SessionContext;

/**
 * Esta classe tem como finalidade encapsular o caso de uso [UC0745] -
 * GerarArquivoTextoFaturamento, gerando maior facilidade na manutencao do
 * mesmo.
 * 
 * @author Raphael Rossiter
 * @date 30/04/2008
 */
public class UC0745GerarArquivoTextoFaturamentoANDROID {

    private static UC0745GerarArquivoTextoFaturamentoANDROID instancia;
    
    private final static int MESES_DISTINTOS = 4;
    
    private static ArrayList<ContaComunicado> listaContaComunicadoTipo18 = new ArrayList<ContaComunicado>();

    private IRepositorioFaturamento                   repositorioFaturamento;
    private IRepositorioCobranca                      repositorioCobranca;
    private SessionContext                            sessionContext;

    private UC0745GerarArquivoTextoFaturamentoANDROID(
            IRepositorioFaturamento repositorioFaturamento, SessionContext sessionContext,
            IRepositorioCobranca repositorioCobranca) {

        this.repositorioFaturamento = repositorioFaturamento;
        this.repositorioCobranca = repositorioCobranca;
        this.sessionContext = sessionContext;
    }

    public static UC0745GerarArquivoTextoFaturamentoANDROID getInstancia(IRepositorioFaturamento repositorioFaturamento,
            SessionContext sessionContext, IRepositorioCobranca repositorioCobranca) {

        if (instancia == null) {
            instancia = new UC0745GerarArquivoTextoFaturamentoANDROID(repositorioFaturamento,
                                                               sessionContext,
                                                               repositorioCobranca);
        }
        return instancia;
    }

    /**
     * Controlador Util
     * 
     * @author Raphael Rossiter
     * @date 30/04/2008
     * 
     * @return ControladorUtilLocal
     */
    private ControladorUtilLocal getControladorUtil() {

        ControladorUtilLocalHome localHome = null;
        ControladorUtilLocal local = null;

        ServiceLocator locator = null;

        try {
            locator = ServiceLocator.getInstancia();

            localHome = (ControladorUtilLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_UTIL_SEJB);
            // guarda a referencia de um objeto capaz de fazer chamadas a†
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
     * Controlador Endereco
     * 
     * @author Raphael Rossiter
     * @date 30/04/2008
     * 
     * @return ControladorEnderecoLocal
     */
    private ControladorEnderecoLocal getControladorEndereco() {
        ControladorEnderecoLocalHome localHome = null;
        ControladorEnderecoLocal local = null;

        ServiceLocator locator = null;

        try {
            locator = ServiceLocator.getInstancia();

            localHome = (ControladorEnderecoLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_ENDERECO_SEJB);
            // guarda a referencia de um objeto capaz de fazer chamadas a†
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
     * Controlador Imovel
     * 
     * @author Raphael Rossiter
     * @date 30/04/2008
     * 
     * @return ControladorImovelLocal
     */
    protected ControladorImovelLocal getControladorImovel() {
        ControladorImovelLocalHome localHome = null;
        ControladorImovelLocal local = null;

        ServiceLocator locator = null;

        try {
            locator = ServiceLocator.getInstancia();

            localHome = (ControladorImovelLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_IMOVEL_SEJB);
            // guarda a referencia de um objeto capaz de fazer chamadas a
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
     * Controlador Faturamento
     * 
     * @author Raphael Rossiter
     * @date 30/04/2008
     * 
     * @return ControladorFaturamentoLocal
     */
    private ControladorFaturamentoLocal getControladorFaturamento() {
        ControladorFaturamentoLocalHome localHome = null;
        ControladorFaturamentoLocal local = null;

        ServiceLocator locator = null;

        try {
            locator = ServiceLocator.getInstancia();

            localHome = (ControladorFaturamentoLocalHome) locator.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FATURAMENTO_SEJB);
            // guarda a referencia de um objeto capaz de fazer chamadas a†
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
     * Controlador Micromedicao
     * 
     * @author Raphael Rossiter
     * @date 30/04/2008
     * 
     * @return ControladorMicromedicaoLocal
     */
    protected ControladorMicromedicaoLocal getControladorMicromedicao() {
        ControladorMicromedicaoLocalHome localHome = null;
        ControladorMicromedicaoLocal local = null;

        ServiceLocator locator = null;

        try {
            locator = ServiceLocator.getInstancia();

            localHome = (ControladorMicromedicaoLocalHome) locator.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_MICROMEDICAO_SEJB);
            // guarda a referencia de um objeto capaz de fazer chamadas a†
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
     * Controlador Cobranca
     * 
     * @author Raphael Rossiter
     * @date 30/04/2008
     * 
     * @return ControladorCobrancaLocal
     */
    protected ControladorCobrancaLocal getControladorCobranca() {
        ControladorCobrancaLocalHome localHome = null;
        ControladorCobrancaLocal local = null;

        ServiceLocator locator = null;

        try {
            locator = ServiceLocator.getInstancia();

            localHome = (ControladorCobrancaLocalHome) locator.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_COBRANCA_SEJB);
            // guarda a referencia de um objeto capaz de fazer chamadas a†
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
     * Controlador Arrecadacao
     * 
     * @author Raphael Rossiter
     * @date 30/04/2008
     * 
     * @return ControladorArrecadacaoLocal
     */
    private ControladorArrecadacaoLocal getControladorArrecadacao() {
        ControladorArrecadacaoLocalHome localHome = null;
        ControladorArrecadacaoLocal local = null;

        ServiceLocator locator = null;

        try {
            locator = ServiceLocator.getInstancia();

            localHome = (ControladorArrecadacaoLocalHome) locator.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_ARRECADACAO_SEJB);

            local = localHome.create();

            return local;
        } catch (CreateException e) {
            throw new SistemaException(e);
        } catch (ServiceLocatorException e) {
            throw new SistemaException(e);
        }
    }

    /**
     * Controlador Batch
     * 
     * @author hugo Leonardo
     * @date 16/06/2010
     * 
     * @return ControladorBatchLocal
     */
    protected ControladorBatchLocal getControladorBatch() {
        ControladorBatchLocalHome localHome = null;
        ControladorBatchLocal local = null;

        // pega a instancia do ServiceLocator.

        ServiceLocator locator = null;

        try {
            locator = ServiceLocator.getInstancia();

            localHome = (ControladorBatchLocalHome) locator.getLocalHome(ConstantesJNDI.CONTROLADOR_BATCH_SEJB);
            // guarda a referencia de um objeto capaz de fazer chamadas a†
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
     * [UC0745] - Gerar Arquivo Texto para Faturamento
     * 
     * [FS0005] - Verificar existencia do arquivo texto por rota
     * 
     * @author Raphael Rossiter, Hugo Leonardo
     * @date 17/04/2008, 16/06/2010
     * 
     * @param idRota
     * @param anoMesReferencia
     * @return boolean
     * @throws ControladorException
     */
    public boolean verificarExistenciaArquivoTextoRota(Integer idRota, Integer anoMesReferencia) throws ControladorException {

        boolean retorno = true;
        Object[] dadosArquivoTextoRoteiroEmpresa = null;

        try {

            dadosArquivoTextoRoteiroEmpresa = repositorioFaturamento.pesquisarArquivoTextoRoteiroEmpresa(idRota, anoMesReferencia);

            /*
             * Caso ja exista um arquivo texto para rota e mes de referencia
             * informados e com situacao da transmissao de leitura igual a
             * disponivel: Remover o registro encontrado.
             */
            if (dadosArquivoTextoRoteiroEmpresa != null) {
                Integer idArquivoTexto = (Integer) dadosArquivoTextoRoteiroEmpresa[0];
                Integer idSituacaoTransmissaoLeitura = (Integer) dadosArquivoTextoRoteiroEmpresa[1];

                if (idSituacaoTransmissaoLeitura.equals(SituacaoTransmissaoLeitura.DISPONIVEL)) {
                    // REMOVENDO REGISTRO
                    this.repositorioFaturamento.deletaArquivoTextoRoteiroEmpresaDivisao(idArquivoTexto);
                    repositorioFaturamento.deletarArquivoTextoRoteiroEmpresa(idArquivoTexto);

                } else {
                    retorno = false;
                }

            }
        } catch (ErroRepositorioException e) {
            // sessionContext.setRollbackOnly();
            throw new ControladorException("erro.sistema",
                                           e);
        }

        return retorno;
    }

    /**
     * [UC0745] - Gerar Arquivo Texto para Faturamento
     * 
     * [FS0002] - Verificar Situacao Especial de Faturamento
     * 
     * @author Raphael Rossiter
     * @date 17/04/2008
     * 
     * @param idRota
     * @param numeroPaginas
     * @param quantidadeRegistros
     * @throws ControladorException
     */
    public Object[] pesquisarImovelGerarArquivoTextoFaturamento(Rota rota, int numeroIndice, int quantidadeRegistros,
            SistemaParametro sistemaParametro, FaturamentoGrupo faturamentoGrupo, Integer idImovelCondominio)
                                                                                                             throws ControladorException {

        Object[] retorno = new Object[2];
        Collection colecaoImoveis = null;
        int quantidadeImoveis = 0;
        Collection imoveis;
        Integer idRota = null;
        if (idImovelCondominio == null || idImovelCondominio.equals("")) {
            idRota = rota.getId();
        }

        /*
         * Caso a rota nao esteja com o indicador de rota alternativa ativo; a
         * pesquisa dos imoveis sera° feita a partir de sua quadra.
         */
        if (!rota.getIndicadorRotaAlternativa()
                 .equals(ConstantesSistema.SIM)) {

            try {
                imoveis = repositorioFaturamento.pesquisarImovelGerarArquivoTextoFaturamento(idRota, numeroIndice, quantidadeRegistros, sistemaParametro, idImovelCondominio);

            } catch (ErroRepositorioException ex) {
                sessionContext.setRollbackOnly();
                throw new ControladorException("erro.sistema",
                                               ex);
            }
        }
        /*
         * Caso contrario; a pesquisa dos imoveis sera feita a partir da rota
         * alternativa que estara associada ao mesmo.
         */
        else {

            try {

                imoveis = repositorioFaturamento.pesquisarImovelGerarArquivoTextoFaturamentoPorRotaAlternativa(idRota, numeroIndice, quantidadeRegistros, sistemaParametro, idImovelCondominio);

            } catch (ErroRepositorioException ex) {
                sessionContext.setRollbackOnly();
                throw new ControladorException("erro.sistema",
                                               ex);
            }
        }

        // Carregando os dados dos imoveis selecionados
        if (imoveis != null && !imoveis.isEmpty()) {

            Iterator iteratorImoveis = imoveis.iterator();

            colecaoImoveis = new ArrayList();

            quantidadeImoveis = imoveis.size();

            Imovel imovel = null;

            while (iteratorImoveis.hasNext()) {

                Object[] arrayImovel = (Object[]) iteratorImoveis.next();

                imovel = new Imovel();

                // ID
                imovel.setId((Integer) arrayImovel[23]);

                // GERENCIA REGIONAL E LOCALIDADE
                GerenciaRegional gerenciaRegional = new GerenciaRegional();
                gerenciaRegional.setNome((String) arrayImovel[0]);
                gerenciaRegional.setId((Integer) arrayImovel[28]);

                Localidade localidade = new Localidade();
                localidade.setId((Integer) arrayImovel[1]);
                localidade.setDescricao((String) arrayImovel[2]);
                localidade.setGerenciaRegional(gerenciaRegional);

                imovel.setLocalidade(localidade);

                // SETOR_COMERCIAL
                SetorComercial setorComercial = new SetorComercial();
                setorComercial.setCodigo((Integer) arrayImovel[5]);
                if (arrayImovel[26] != null) {
                    setorComercial.setId((Integer) arrayImovel[26]);
                }
                imovel.setSetorComercial(setorComercial);

                // LEITURISTA
                if (arrayImovel[39] != null) {
                    Leiturista leiturista = rota.getLeiturista();
                    if (arrayImovel[59] != null) {
                        Usuario usuario = new Usuario();
                        usuario.setLogin((String) arrayImovel[59]);
                        usuario.setSenha((String) arrayImovel[60]);
                        leiturista.setUsuario(usuario);
                    }
                    rota.setLeiturista(leiturista);
                }

                // GRUPO
                rota.setFaturamentoGrupo(faturamentoGrupo);

                Quadra quadra = new Quadra();
                quadra.setNumeroQuadra((Integer) arrayImovel[6]);
                quadra.setRota(rota);

                imovel.setQuadra(quadra);

                // QUADRA FACE
                if (arrayImovel[41] != null) {
                    QuadraFace quadraFace = new QuadraFace();
                    quadraFace.setId((Integer) arrayImovel[41]);
                    if (arrayImovel[42] != null) {
                        quadraFace.setNumeroQuadraFace((Integer) arrayImovel[42]);
                    }

                    imovel.setQuadraFace(quadraFace);
                }
                // SEQUENCIAL DA ROTA
                if (arrayImovel[27] != null) {
                    imovel.setNumeroSequencialRota((Integer) arrayImovel[27]);
                }

                // LOTE E SUBLOTE
                imovel.setLote((Short) arrayImovel[7]);
                imovel.setSubLote((Short) arrayImovel[8]);

                // IMOVEL_NOME E CLIENTES: USUARIO E RESPONSAVEL
                imovel.setNomeImovel((String) arrayImovel[3]);

                Set colecaoClienteImovel = new HashSet();
                if (arrayImovel[4] != null) {
                    ClienteRelacaoTipo clienteRelacaoTipo = new ClienteRelacaoTipo();
                    clienteRelacaoTipo.setId(ClienteRelacaoTipo.USUARIO.intValue());

                    Cliente clienteUsuario = new Cliente();
                    clienteUsuario.setNome((String) arrayImovel[4]);

                    if (arrayImovel[32] != null) {
                        clienteUsuario.setCpf((String) arrayImovel[32]);
                    }

                    if (arrayImovel[33] != null) {
                        clienteUsuario.setCnpj((String) arrayImovel[33]);
                    }

                    ClienteImovel clienteImovel = new ClienteImovel();
                    clienteImovel.setCliente(clienteUsuario);
                    clienteImovel.setClienteRelacaoTipo(clienteRelacaoTipo);

                    colecaoClienteImovel.add(clienteImovel);
                }

                if (arrayImovel[9] != null) {
                    ClienteRelacaoTipo clienteRelacaoTipo = new ClienteRelacaoTipo();
                    clienteRelacaoTipo.setId(ClienteRelacaoTipo.RESPONSAVEL.intValue());

                    Cliente clienteResponsavel = new Cliente();
                    clienteResponsavel.setId((Integer) arrayImovel[9]);
                    clienteResponsavel.setNome((String) arrayImovel[10]);

                    ClienteImovel clienteImovel = new ClienteImovel();
                    clienteImovel.setCliente(clienteResponsavel);
                    clienteImovel.setClienteRelacaoTipo(clienteRelacaoTipo);

                    colecaoClienteImovel.add(clienteImovel);
                }

                if (colecaoClienteImovel.size() > 0) {
                    imovel.setClienteImoveis(colecaoClienteImovel);
                }

                // LIGACAO_AGUA_SITUACAO
                if (arrayImovel[11] != null) {
                    LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
                    ligacaoAguaSituacao.setId((Integer) arrayImovel[11]);
                    ligacaoAguaSituacao.setIndicadorFaturamentoSituacao((Short) arrayImovel[29]);
                    ligacaoAguaSituacao.setIndicadorAbastecimento((Short) arrayImovel[36]);
                    ligacaoAguaSituacao.setDescricao((String) arrayImovel[38]);
                    ligacaoAguaSituacao.setIndicadorConsumoReal((Short) arrayImovel[66]);
                    ligacaoAguaSituacao.setNumeroDiasCorte((Integer) arrayImovel[67]);
                    ligacaoAguaSituacao.setIndicadorFaturarLeituraReal((Short) arrayImovel[75]);

                    imovel.setLigacaoAguaSituacao(ligacaoAguaSituacao);
                }

                // LIGACAO_ESGOTO_SITUACAO
                if (arrayImovel[12] != null) {
                    LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
                    ligacaoEsgotoSituacao.setId((Integer) arrayImovel[12]);
                    ligacaoEsgotoSituacao.setIndicadorFaturamentoSituacao((Short) arrayImovel[30]);
                    imovel.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);
                }

                // LIGACAO_ESGOTO - ConsumoMinimo
                LigacaoEsgoto ligacaoEsgoto = null;
                if (arrayImovel[14] != null) {
                    ligacaoEsgoto = new LigacaoEsgoto();
                    ligacaoEsgoto.setConsumoMinimo((Integer) arrayImovel[14]);

                }

                // LIGACAO_ESGOTO - Percentual
                if (arrayImovel[15] != null) {

                    if (ligacaoEsgoto != null) {
                        ligacaoEsgoto.setPercentualAguaConsumidaColetada((BigDecimal) arrayImovel[15]);
                    } else {
                        ligacaoEsgoto = new LigacaoEsgoto();
                        ligacaoEsgoto.setPercentualAguaConsumidaColetada((BigDecimal) arrayImovel[15]);
                    }
                }

                // percentual alternativo
                if (arrayImovel[63] != null) {
                    if (ligacaoEsgoto != null) {
                        ligacaoEsgoto.setPercentualAlternativo((BigDecimal) arrayImovel[63]);
                    } else {
                        ligacaoEsgoto = new LigacaoEsgoto();
                        ligacaoEsgoto.setPercentualAlternativo((BigDecimal) arrayImovel[63]);
                    }

                }
                // consumo percentual alternativo
                if (arrayImovel[64] != null) {
                    if (ligacaoEsgoto != null) {
                        ligacaoEsgoto.setNumeroConsumoPercentualAlternativo((Integer) arrayImovel[64]);
                    } else {
                        ligacaoEsgoto = new LigacaoEsgoto();
                        ligacaoEsgoto.setNumeroConsumoPercentualAlternativo((Integer) arrayImovel[64]);
                    }

                }
                imovel.setLigacaoEsgoto(ligacaoEsgoto);

                // FATURAMENTO_SITUACAO_TIPO
                if (arrayImovel[61] != null) {
                    FaturamentoSituacaoTipo faturamentoSituacaoTipo = new FaturamentoSituacaoTipo();
                    faturamentoSituacaoTipo.setId((Integer) arrayImovel[61]);
                    faturamentoSituacaoTipo.setIndicadorParalisacaoFaturamento((Short) arrayImovel[16]);
                    faturamentoSituacaoTipo.setIndicadorValidoAgua((Short) arrayImovel[17]);
                    faturamentoSituacaoTipo.setIndicadorValidoEsgoto((Short) arrayImovel[18]);
                    faturamentoSituacaoTipo.setIndicadorParalisacaoLeitura((Short) arrayImovel[65]);
                    faturamentoSituacaoTipo.setIndicadorParalisaFatAgua((Short)arrayImovel[70]);
                    faturamentoSituacaoTipo.setIndicadorParalisaFatEsgoto((Short) arrayImovel[71]);

                    /*
                    if ( arrayImovel[72] != null ) {
                    	
                    	faturamentoSituacaoTipo.setIndicadorCobrarConsumoMinimo((Short) arrayImovel[72]); */
                    	
                    	/**
                    	 * @author Arthur Carvalho
                    	 * 
                    	 * Verifica se o imovel possui categoria = residencial. Caso existe alguma mudanca na categoria do imovel com situacao especial de faturamento
                    	 * O indicador para cobrar consumo minimo do imovel so pode ser realizado com imoveis que possuam categoria = residencial.
                    	 */
                   /* 	if ( faturamentoSituacaoTipo.getIndicadorCobrarConsumoMinimo().equals(ConstantesSistema.SIM) && 
                    			!getControladorFaturamento().validaImovelCategoriaResidencialComIndicadorCobrarConsumoMinimo(imovel) ) {
	                        	
                    		faturamentoSituacaoTipo.setIndicadorCobrarConsumoMinimo(ConstantesSistema.NAO);
	                        
                    	}
                    }*/
                    
                    imovel.setFaturamentoSituacaoTipo(faturamentoSituacaoTipo);
                }

                // IMOVEL_CONDOMINIO
                if (arrayImovel[19] != null) {
                    Imovel imovelCondominio = new Imovel();
                    imovelCondominio.setId((Integer) arrayImovel[19]);

                    imovel.setImovelCondominio(imovelCondominio);
                }

                // INDICADOR_IMOVEL_CONDOMINIO
                imovel.setIndicadorImovelCondominio((Short) arrayImovel[20]);

                // IMOVEL_PERFIL
                ImovelPerfil imovelPerfil = new ImovelPerfil();
                imovelPerfil.setId((Integer) arrayImovel[21]);

                imovel.setImovelPerfil(imovelPerfil);

                /**
                 * CONSUMO TARIFA
                 * 
        		 * @author Arthur Carvalho
        		 * @date 14/06/2012
				 * 
        		 * Caso imovel possua tipo de situaÁ„o de faturamento especial e o indicador cobrar consumo minimo = ativo
        		 * consumo Tarifa = tarifa social. Caso contrario pega o consumo tarifa do imovel.
        		 */
                ConsumoTarifa consumoTarifa = new ConsumoTarifa();
               /*
                if ( imovel.getFaturamentoSituacaoTipo() != null && imovel.getFaturamentoSituacaoTipo().getIndicadorCobrarConsumoMinimo() != null &&
        				imovel.getFaturamentoSituacaoTipo().getIndicadorCobrarConsumoMinimo().equals(ConstantesSistema.SIM) ){

                    	consumoTarifa.setId(ConsumoTarifa.CONSUMO_SOCIAL);
	
	                	//Pesquisa o tarifatipoCalculo da Tarifa Social 
	                	FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
	                	filtroConsumoTarifa.adicionarParametro( new ParametroSimples(FiltroConsumoTarifa.ID, consumoTarifa.getId()));
	                	ConsumoTarifa consumoTarifaNaBase = (ConsumoTarifa) Util.retonarObjetoDeColecao(getControladorUtil().pesquisar(
	                			filtroConsumoTarifa, ConsumoTarifa.class.getName()));
	                	
	                    consumoTarifa.setTarifaTipoCalculo(consumoTarifaNaBase.getTarifaTipoCalculo());
	
	                    imovel.setConsumoTarifa(consumoTarifa);
	                    
        		} else { */
                
        			consumoTarifa.setId((Integer) arrayImovel[22]);

                    if (arrayImovel[25] != null) {
                        TarifaTipoCalculo tarifaTipoCalculo = new TarifaTipoCalculo();
                        tarifaTipoCalculo.setId((Integer) arrayImovel[25]);
                        consumoTarifa.setTarifaTipoCalculo(tarifaTipoCalculo);
                    }

                    imovel.setConsumoTarifa(consumoTarifa);
        		//}
               
                
                // POCO_TIPO
                if (arrayImovel[24] != null) {
                    PocoTipo pocoTipo = new PocoTipo();
                    pocoTipo.setId((Integer) arrayImovel[24]);

                    imovel.setPocoTipo(pocoTipo);
                }

                // IMOVEL_CONTA_ENVIO
                if (arrayImovel[31] != null) {
                    ImovelContaEnvio imovelContaEnvio = new ImovelContaEnvio();
                    imovelContaEnvio.setId((Integer) arrayImovel[31]);

                    imovel.setImovelContaEnvio(imovelContaEnvio);
                }

                boolean existeHidrometroAgua = false;
                if (arrayImovel[37] != null) {
                    LigacaoAgua ligacaoAgua = new LigacaoAgua();
                    ligacaoAgua.setId((Integer) arrayImovel[37]);
                    if (arrayImovel[13] != null) {
                        ligacaoAgua.setNumeroConsumoMinimoAgua((Integer) arrayImovel[13]);
                    }
                    
                    if (arrayImovel[72] != null) {
    					ligacaoAgua.setDataLigacao((Date) arrayImovel[72]);
    				}
    				
    				if (arrayImovel[73] != null) {
    					ligacaoAgua.setDataReligacao((Date) arrayImovel[73]);
    				}
    				
    				if (arrayImovel[74] != null) {
    					ligacaoAgua.setDataRestabelecimentoAgua((Date) arrayImovel[74]);
    				}
                    
                    if (arrayImovel[34] != null) {
                        HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = new HidrometroInstalacaoHistorico();
                        hidrometroInstalacaoHistorico.setId((Integer) arrayImovel[34]);
                        // MEDICAO TIPO AGUA
                        if (arrayImovel[44] != null) {
                            MedicaoTipo medicaoTipo = new MedicaoTipo();
                            medicaoTipo.setId((Integer) arrayImovel[44]);
                            hidrometroInstalacaoHistorico.setMedicaoTipo(medicaoTipo);
                        }
                        // LOCAL INSTALACAO
                        if (arrayImovel[53] != null) {
                            HidrometroLocalInstalacao hidrometroLocalInstalacao = new HidrometroLocalInstalacao();
                            hidrometroLocalInstalacao.setId((Integer) arrayImovel[53]);
                            hidrometroInstalacaoHistorico.setHidrometroLocalInstalacao(hidrometroLocalInstalacao);
                        }
                        // DATA INSTALACAO
                        if (arrayImovel[54] != null) {
                            hidrometroInstalacaoHistorico.setDataInstalacao((Date) arrayImovel[54]);
                        }
                        // HIDROMETRO PROTECAO
                        if (arrayImovel[55] != null) {
                            HidrometroProtecao hidrometroProtecao = new HidrometroProtecao();
                            hidrometroProtecao.setId((Integer) arrayImovel[55]);
                            hidrometroInstalacaoHistorico.setHidrometroProtecao(hidrometroProtecao);
                        }

                        existeHidrometroAgua = true;
                        ligacaoAgua.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);
                    }
                    // NUMERO DO LACRE
                    if (arrayImovel[46] != null) {
                        ligacaoAgua.setNumeroLacre((String) arrayImovel[46]);
                    }

                    ligacaoAgua.setDataCorte((Date) arrayImovel[68]);

                    imovel.setLigacaoAgua(ligacaoAgua);
                }

                boolean existeHidrometroPoco = false;
                if (arrayImovel[35] != null) {
                    HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = new HidrometroInstalacaoHistorico();
                    hidrometroInstalacaoHistorico.setId((Integer) arrayImovel[35]);
                    // MEDICAO TIPO AGUA
                    if (arrayImovel[45] != null) {
                        MedicaoTipo medicaoTipo = new MedicaoTipo();
                        medicaoTipo.setId((Integer) arrayImovel[45]);
                        hidrometroInstalacaoHistorico.setMedicaoTipo(medicaoTipo);
                    }
                    // LOCAL INSTALACAO
                    if (arrayImovel[56] != null) {
                        HidrometroLocalInstalacao hidrometroLocalInstalacao = new HidrometroLocalInstalacao();
                        hidrometroLocalInstalacao.setId((Integer) arrayImovel[56]);
                        hidrometroInstalacaoHistorico.setHidrometroLocalInstalacao(hidrometroLocalInstalacao);
                    }
                    // DATA INSTALACAO
                    if (arrayImovel[57] != null) {
                        hidrometroInstalacaoHistorico.setDataInstalacao((Date) arrayImovel[57]);
                    }
                    // HIDROMETRO PROTECAO
                    if (arrayImovel[58] != null) {
                        HidrometroProtecao hidrometroProtecao = new HidrometroProtecao();
                        hidrometroProtecao.setId((Integer) arrayImovel[58]);
                        hidrometroInstalacaoHistorico.setHidrometroProtecao(hidrometroProtecao);
                    }

                    imovel.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);
                    existeHidrometroAgua = true;
                }

                // NUMERO DE MORADORES
                if (arrayImovel[43] != null) {
                    imovel.setNumeroMorador((Short) arrayImovel[43]);
                }

                // LOGRADOURO BAIRRO
                if (arrayImovel[47] != null) {
                    LogradouroBairro logradouroBairro = new LogradouroBairro();
                    logradouroBairro.setId((Integer) arrayImovel[47]);
                    // LOGRADOURO
                    if (arrayImovel[48] != null) {
                        Logradouro logradouro = new Logradouro();
                        logradouro.setId((Integer) arrayImovel[48]);
                        logradouro.setNome((String) arrayImovel[49]);
                        logradouroBairro.setLogradouro(logradouro);
                    }
                    // BAIRRO
                    if (arrayImovel[50] != null) {
                        Bairro bairro = new Bairro();
                        bairro.setNome((String) arrayImovel[50]);
                        logradouroBairro.setBairro(bairro);
                    }

                    imovel.setLogradouroBairro(logradouroBairro);
                }

                // NUMERO DO IMOVEL
                if (arrayImovel[51] != null) {
                    imovel.setNumeroImovel((String) arrayImovel[51]);
                }

                // COMPLEMENTO ENDERECO
                if (arrayImovel[52] != null) {
                    imovel.setComplementoEndereco((String) arrayImovel[52]);
                }

                // CODIGO DEBITO AUTOMaÅTICO
                if (arrayImovel[62] != null) {
                    imovel.setCodigoDebitoAutomatico((Integer) arrayImovel[62]);
                }
                
                if (arrayImovel[69] != null) {
                    imovel.setIndicadorImovelAreaComum((Short) arrayImovel[69]);
                }
                
                boolean emitir = true;
                if (sistemaParametro.getCodigoEmpresaFebraban()
                                    .equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAERN)) {
                    if (imovel.getImovelContaEnvio() != null
                            && (imovel.getImovelContaEnvio()
                                      .getId().equals(ImovelContaEnvio.ENVIAR_CLIENTE_RESPONSAVEL_FINAL_GRUPO))) {
                        emitir = false;
                    }
                } else {
                    // SE NAO FOR CAERN, ENTAO A COMPESA OU CAER
                    if (imovel.getImovelContaEnvio() != null
                            && (imovel.getImovelContaEnvio()
                                      .getId()
                                      .equals(ImovelContaEnvio.ENVIAR_CLIENTE_RESPONSAVEL)
                                    || imovel.getImovelContaEnvio()
                                             .getId()
                                             .equals(ImovelContaEnvio.NAO_PAGAVEL_IMOVEL_PAGAVEL_RESPONSAVEL)
                                    || imovel.getImovelContaEnvio()
                                             .getId()
                                             .equals(ImovelContaEnvio.ENVIAR_CONTA_BRAILLE) || imovel.getImovelContaEnvio()
                                                                                                     .getId()
                                                                                                     .equals(ImovelContaEnvio.ENVIAR_CONTA_BRAILLE_RESPONSAVEL))) {
                        emitir = false;
                    }

                }

                if ((imovel.getImovelCondominio() != null && imovel.getImovelCondominio()
                                                                   .getId() != null && !imovel.getImovelCondominio()
                                                                                              .getId()
                                                                                              .equals(""))
                        || imovel.getIndicadorImovelCondominio()
                                 .equals(ConstantesSistema.SIM)) {
                    emitir = true;
                }
                
                // No sistema de leitura ANDROID, os imÛveis n„o medidos n„o ir„o para a rota de leitura
                // Com o emitir sendo falso, caso n„o exista hidrÙmetro de ·gua ou de poÁo, o imÛvel
                // n„o ir· para o arquivo de leitura
                if(rota.getLeituraTipo() != null && rota.getLeituraTipo().getId().equals(LeituraTipo.LEITURA_ANDROID)){
                	emitir = false;
                }

                // caso seja para emtir ou seja medido de agua ou poco
                if (emitir || existeHidrometroAgua || existeHidrometroPoco) {
                    colecaoImoveis.add(imovel);
                }
            }
        }

        retorno[0] = colecaoImoveis;
        retorno[1] = quantidadeImoveis;

        if (imoveis != null) {
            imoveis.clear();
            imoveis = null;
        }

        return retorno;
    }

    /**
     * [UC0745] - Gerar Arquivo Texto para Faturamento
     * 
     * @author Raphael Rossiter
     * @date 23/04/2008
     * 
     * @param imovel
     * @param anoMesReferencia
     * @return Conta
     * @throws ControladorException
     */
    public Conta pesquisarContaGerarArquivoTextoFaturamento(Imovel imovel, Integer anoMesReferencia,
            Integer idFaturamentoGrupo) throws ControladorException {

        Conta conta = null;
        Object[] arrayConta = null;

        try {

            arrayConta = repositorioFaturamento.pesquisarContaGerarArquivoTextoFaturamento(imovel.getId(), anoMesReferencia, idFaturamentoGrupo);

        } catch (ErroRepositorioException ex) {
            sessionContext.setRollbackOnly();
            throw new ControladorException("erro.sistema",
                                           ex);
        }

        if (arrayConta != null) {

            conta = new Conta();

            // ID
            conta.setId((Integer) arrayConta[18]);

            // GERENCIA REGIONAL E LOCALIDADE
            GerenciaRegional gerenciaRegional = new GerenciaRegional();
            gerenciaRegional.setNome((String) arrayConta[0]);
            gerenciaRegional.setId((Integer) arrayConta[20]);

            Localidade localidade = new Localidade();
            localidade.setId((Integer) arrayConta[1]);
            localidade.setDescricao((String) arrayConta[2]);
            localidade.setGerenciaRegional(gerenciaRegional);

            conta.setLocalidade(localidade);

            // SETOR_COMERCIAL
            conta.setCodigoSetorComercial((Integer) arrayConta[4]);

            // QUADRA
            conta.setQuadra((Integer) arrayConta[5]);

            // LOTE E SUBLOTE
            conta.setLote((Short) arrayConta[6]);
            conta.setSubLote((Short) arrayConta[7]);

            // CLIENTES: USUARIO E RESPONSAVEL
            Set colecaoClienteConta = new HashSet();
            if (arrayConta[3] != null) {
                ClienteRelacaoTipo clienteRelacaoTipo = new ClienteRelacaoTipo();
                clienteRelacaoTipo.setId(ClienteRelacaoTipo.USUARIO.intValue());

                Cliente clienteUsuario = new Cliente();
                clienteUsuario.setNome((String) arrayConta[3]);

                if (arrayConta[24] != null) {
                    clienteUsuario.setCpf((String) arrayConta[24]);
                }

                if (arrayConta[25] != null) {
                    clienteUsuario.setCnpj((String) arrayConta[25]);
                }

                ClienteConta clienteConta = new ClienteConta();
                clienteConta.setCliente(clienteUsuario);
                clienteConta.setClienteRelacaoTipo(clienteRelacaoTipo);

                colecaoClienteConta.add(clienteConta);
            }

            if (arrayConta[8] != null) {
                ClienteRelacaoTipo clienteRelacaoTipo = new ClienteRelacaoTipo();
                clienteRelacaoTipo.setId(ClienteRelacaoTipo.RESPONSAVEL.intValue());

                Cliente clienteResponsavel = new Cliente();
                clienteResponsavel.setId((Integer) arrayConta[8]);
                clienteResponsavel.setNome((String) arrayConta[9]);

                ClienteConta clienteConta = new ClienteConta();
                clienteConta.setCliente(clienteResponsavel);
                clienteConta.setClienteRelacaoTipo(clienteRelacaoTipo);

                colecaoClienteConta.add(clienteConta);
            }

            if (colecaoClienteConta.size() > 0) {
                conta.setClienteContas(colecaoClienteConta);
            }

            // LIGACAO_AGUA_SITUACAO
            if (arrayConta[10] != null) {
                LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
                ligacaoAguaSituacao.setId((Integer) arrayConta[10]);
                ligacaoAguaSituacao.setIndicadorFaturamentoSituacao((Short) arrayConta[21]);

                conta.setLigacaoAguaSituacao(ligacaoAguaSituacao);
            }

            // LIGACAO_ESGOTO_SITUACAO
            if (arrayConta[11] != null) {
                LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
                ligacaoEsgotoSituacao.setId((Integer) arrayConta[11]);
                ligacaoEsgotoSituacao.setIndicadorFaturamentoSituacao((Short) arrayConta[22]);

                conta.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);
            }

            // DATA_VENCIMENTO
            conta.setDataVencimentoConta((Date) arrayConta[12]);

            // DATA_VALIDADE
            conta.setDataValidadeConta((Date) arrayConta[13]);

            // DIGITO_VERIFICADOR
            conta.setDigitoVerificadorConta((Short) arrayConta[14]);

            // PERCENTUAL_ESGOTO
            conta.setPercentualEsgoto((BigDecimal) arrayConta[15]);

            // IMOVEL_PERFIL
            ImovelPerfil imovelPerfil = new ImovelPerfil();
            imovelPerfil.setId((Integer) arrayConta[16]);

            conta.setImovelPerfil(imovelPerfil);

            // CONSUMO_TARIFA
            ConsumoTarifa consumoTarifa = new ConsumoTarifa();
            consumoTarifa.setId((Integer) arrayConta[17]);

            conta.setConsumoTarifa(consumoTarifa);

            // REFERENCIA
            conta.setReferencia((Integer) arrayConta[19]);

            // IMOVEL
            conta.setImovel(imovel);
        }

        return conta;
    }

    /**
     * [UC0745] - Gerar Arquivo Texto para Faturamento
     * 
     * [SB0001] - Gerar Arquivo Texto
     * 
     * @author Raphael Rossiter
     * @date 24/04/2008
     * 
     * @param imovel
     * @param conta
     * @param anoMesReferencia
     * @return StringBuilder
     * @throws ControladorException
     */
    public StringBuilder gerarArquivoTextoPipe(Imovel imovel, Conta conta, Integer anoMesReferencia, Rota rota,
            FaturamentoGrupo faturamentoGrupo, SistemaParametro sistemaParametro, Date dataComando)
                                                                                                   throws ControladorException {
        StringBuilder arquivoTexto = new StringBuilder();

        Date dataEmissao = Util.subtrairNumeroDiasDeUmaData(dataComando, 10); 
        
        CobrancaDocumento cobrancaDocumento = null;
        try {

            cobrancaDocumento = repositorioCobranca.pesquisarCobrancaDocumentoImpressaoSimultanea(dataEmissao, imovel.getId());

        } catch (ErroRepositorioException ex) {
            sessionContext.setRollbackOnly();
            throw new ControladorException("erro.sistema",
                                           ex);
        }
        
        // REGISTRO_TIPO_01
        arquivoTexto.append(this.gerarArquivoTextoRegistroTipo01Pipe(imovel, conta, anoMesReferencia, rota, faturamentoGrupo, sistemaParametro, cobrancaDocumento));
        
        // REGISTRO_TIPO_02
        arquivoTexto.append(this.gerarArquivoTextoRegistroTipo02Pipe(imovel, conta, sistemaParametro));
        
        // REGISTRO_TIPO_03
        arquivoTexto.append(this.gerarArquivoTextoRegistroTipo03Pipe(imovel, anoMesReferencia));
                
        // REGISTRO_TIPO_04
        arquivoTexto.append(this.gerarArquivoTextoRegistroTipo04Pipe(conta));
        
        // REGISTRO_TIPO_05
        arquivoTexto.append(this.gerarArquivoTextoRegistroTipo05Pipe(conta));
        
        // REGISTRO_TIPO_06
        arquivoTexto.append(this.gerarArquivoTextoRegistroTipo06Pipe(conta));
        
        // REGISTRO_TIPO_07
        arquivoTexto.append(this.gerarArquivoTextoRegistroTipo07Pipe(imovel, sistemaParametro, cobrancaDocumento));
        
        // REGISTRO_TIPO_08
        arquivoTexto.append(this.gerarArquivoTextoRegistroTipo08Pipe(imovel, anoMesReferencia, sistemaParametro));
        
        return arquivoTexto;
    }
    
    /**
     * [UC0745] - Gerar Arquivo Texto para Faturamento
     * 
     * [SB0001] - Gerar Arquivo Texto - Registro Tipo 01
     * 
     * @author Raphael Rossiter, Mariana Victor, Magno Gouveia
     * @date 23/04/2008, 11/03/2011, 24/08/2011
     * 
     * @param imovel
     * @param conta
     * @param anoMesReferencia
     * @return StringBuilder
     * @throws ControladorException
     */
    public StringBuilder gerarArquivoTextoRegistroTipo01Pipe(Imovel imovel, Conta conta, Integer anoMesReferencia, Rota rota,
            FaturamentoGrupo faturamentoGrupo, SistemaParametro sistemaParametro, CobrancaDocumento cobrancaDocumento)
                                                                                                                      throws ControladorException {
        StringBuilder arquivoTextoRegistroTipo01 = new StringBuilder();

        Cliente clienteUsuario = null;
        Cliente clienteResponsavel = null;

        String nomeGerenciaRegional = null;
        Integer idGerenciaRegional = null;
        String descricaoLocalidade = null;
        Set colecaoClienteImovelOUConta = null;
        String inscricaoImovel = null;
        String idLigacaoAguaSituacao = null;
        String idLigacaoEsgotoSituacao = null;
        String idImovelPerfil = null;
        Integer idLocalidade = null;
        Integer idSetorComercial = null;
        Integer numeroSequencialRota = null;
        Short indicadorFaturamentoSituacaoAgua = null;
        Short indicadorFaturamentoSituacaoEsgoto = null;
        short indicadorParalisacaoFaturamentoAgua = 2;
        short indicadorParalisacaoFaturamentoEsgoto = 2;
        
		Fachada fachada = Fachada.getInstancia();

        if (conta != null) {

            nomeGerenciaRegional = conta.getLocalidade()
                                        .getGerenciaRegional()
                                        .getNome();
            idGerenciaRegional = conta.getLocalidade()
                                      .getGerenciaRegional()
                                      .getId();
            idLocalidade = conta.getLocalidade()
                                .getId();
            if (conta.getImovel() != null && conta.getImovel()
                                                  .getSetorComercial() != null) {
                idSetorComercial = conta.getImovel()
                                        .getSetorComercial()
                                        .getId();
            }
            if (conta.getImovel() != null && conta.getImovel()
                                                  .getNumeroSequencialRota() != null) {
                numeroSequencialRota = conta.getImovel()
                                            .getNumeroSequencialRota();
            }
            descricaoLocalidade = conta.getLocalidade()
                                       .getDescricao();
                    
            colecaoClienteImovelOUConta = conta.getClienteContas();
            
            
            // MONTANDO INSCRICAO DO IMOVEL A PARTIR DA CONTA
            // -----------------------------------------------------------------
            Imovel imovelInscricao = new Imovel();
            imovelInscricao.setLocalidade(conta.getLocalidade());

            SetorComercial setorComercial = new SetorComercial();
            setorComercial.setCodigo(conta.getCodigoSetorComercial());
            imovelInscricao.setSetorComercial(setorComercial);

            Quadra quadra = new Quadra();
            quadra.setNumeroQuadra(conta.getQuadra());
            imovelInscricao.setQuadra(quadra);

            imovelInscricao.setLote(conta.getLote());
            imovelInscricao.setSubLote(conta.getSubLote());

            inscricaoImovel = imovelInscricao.getInscricaoFormatadaSemPonto();
            // -----------------------------------------------------------------
            
            idLigacaoAguaSituacao = conta.getLigacaoAguaSituacao()
                                         .getId()
                                         .toString();
            indicadorFaturamentoSituacaoAgua = conta.getLigacaoAguaSituacao()
                                                    .getIndicadorFaturamentoSituacao();
            idLigacaoEsgotoSituacao = conta.getLigacaoEsgotoSituacao()
                                           .getId()
                                           .toString();
            indicadorFaturamentoSituacaoEsgoto = conta.getLigacaoEsgotoSituacao()
                                                      .getIndicadorFaturamentoSituacao();
            idImovelPerfil = conta.getImovelPerfil()
                                  .getId()
                                  .toString();
        } else {

            nomeGerenciaRegional = imovel.getLocalidade()
                                         .getGerenciaRegional()
                                         .getNome();
            idGerenciaRegional = imovel.getLocalidade()
                                       .getGerenciaRegional()
                                       .getId();
            idLocalidade = imovel.getLocalidade()
                                 .getId();
            if (imovel.getSetorComercial() != null) {
                idSetorComercial = imovel.getSetorComercial()
                                         .getId();
            }
            if (imovel.getNumeroSequencialRota() != null) {
                numeroSequencialRota = imovel.getNumeroSequencialRota();
            }
            descricaoLocalidade = imovel.getLocalidade()
                                        .getDescricao();
            colecaoClienteImovelOUConta = imovel.getClienteImoveis();
            inscricaoImovel = imovel.getInscricaoFormatadaSemPonto();
            idLigacaoAguaSituacao = imovel.getLigacaoAguaSituacao()
                                          .getId()
                                          .toString();
            indicadorFaturamentoSituacaoAgua = imovel.getLigacaoAguaSituacao()
                                                     .getIndicadorFaturamentoSituacao();
            idLigacaoEsgotoSituacao = imovel.getLigacaoEsgotoSituacao()
                                            .getId()
                                            .toString();
            indicadorFaturamentoSituacaoEsgoto = imovel.getLigacaoEsgotoSituacao()
                                                       .getIndicadorFaturamentoSituacao();
            idImovelPerfil = imovel.getImovelPerfil()
                                   .getId()
                                   .toString();
        }

        //1. TIPO DO REGISTRO
        arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao("01"));

        //2. MATRIçCULA DO IMOVEL
        arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(imovel.getId()
                                                                                    .toString()));

        //3. NOME DA GERENCIA REGIONAL
        arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(nomeGerenciaRegional));

        //4. DESCRICAO DA LOCALIDADE
        arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(descricaoLocalidade));

        Iterator iteratorClienteImovelOUConta = colecaoClienteImovelOUConta.iterator();

        while (iteratorClienteImovelOUConta.hasNext()) {

            Object clienteImovelOUConta = iteratorClienteImovelOUConta.next();

            ClienteRelacaoTipo clienteRelacaoTipo = null;
            Cliente cliente = null;

            if (clienteImovelOUConta instanceof ClienteImovel) {

                clienteRelacaoTipo = ((ClienteImovel) clienteImovelOUConta).getClienteRelacaoTipo();
                cliente = ((ClienteImovel) clienteImovelOUConta).getCliente();
            } else {

                clienteRelacaoTipo = ((ClienteConta) clienteImovelOUConta).getClienteRelacaoTipo();
                cliente = ((ClienteConta) clienteImovelOUConta).getCliente();
            }

            if (clienteRelacaoTipo.getId()
                                  .equals(ClienteRelacaoTipo.USUARIO.intValue())) {
                clienteUsuario = cliente;
            } else {
                clienteResponsavel = cliente;
            }
        }

        //5. NOME DO IMOVEL OU NOME DO CLIENTE USUAÅRIO
        if (imovel.getNomeImovel() != null) {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(imovel.getNomeImovel()));
        } else {
            /*
             * Alterado por: Mariana Victor Data: 11/03/2011
             */
            String nomeCliente = "";
            if (conta != null && conta.getId() != null) {
                nomeCliente = Fachada.getInstancia()
                                     .obterNomeClienteConta(conta.getId());
            } else if (imovel != null && imovel.getId() != null) {
                nomeCliente = Fachada.getInstancia()
                                     .obterNomeCliente(imovel.getId());
            }

            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(nomeCliente));
        }

        if (conta != null) {

            //6. DATA DE VENCIMENTO
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(
            		Util.formatarDataAAAAMMDD(conta.getDataVencimentoConta())));

            //7. DATA DE VALIDADE
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(
            		Util.formatarDataAAAAMMDD(conta.getDataValidadeConta())));
        } else {

            //6 e 7. DATA DE VENCIMENTO E DATA DE VALIDADE
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(null));
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(null));
        }

        //8. INSCRICAO DO IMOVEL
        arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(inscricaoImovel));

        //9. ENDERECO DO IMOVEL
        // [UC0085] - Obter Endereco
        String enderecoImovel = getControladorEndereco().pesquisarEnderecoFormatado(imovel.getId());

        arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(enderecoImovel));

        if (conta != null) {

            //10 e 11. MES/ANO DE REFERENCIA DA CONTA E DIGITO VERIFICADOR
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(conta.getReferencia()));
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(conta.getDigitoVerificadorConta()));

        } else {

            //10 e 11. MES/ANO DE REFERENCIA DA CONTA E DIGITO VERIFICADOR
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(faturamentoGrupo.getAnoMesReferencia()));
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(null));
        }

        //12,13 e 14. CLIENTE RESPONSAÅVEL - CODIGO, NOME E ENDERECO
        if (clienteResponsavel != null) {

            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(clienteResponsavel.getId()
                                                                                                    .toString()));
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(clienteResponsavel.getNome()));

            // [UC0085] - Obter Endereco
            String enderecoCorrespondencia = getControladorEndereco().pesquisarEnderecoClienteAbreviado(clienteResponsavel.getId());

            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(enderecoCorrespondencia));
        } else {

            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(null));
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(null));
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(null));
        }

        //15. LIGACAO_SITUACAO_AGUA
        arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(idLigacaoAguaSituacao));

        //16. LIGACAO_SITUACAO_ESGOTO
        arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(idLigacaoEsgotoSituacao));

        // BANCO E AGENCIA
        Object[] parmsDebitoAutomatico = this.getControladorArrecadacao()
                                             .pesquisarParmsDebitoAutomatico(imovel.getId());

        if (parmsDebitoAutomatico != null) {

            //17. NOME DO BANCO
            if (parmsDebitoAutomatico[0] != null) {
                arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(parmsDebitoAutomatico[0]));
            } else {
                arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(null));
            }

            //18. CODIGO DA AGENCIA
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(parmsDebitoAutomatico[1]));

        } else {
        	arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(null));
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(null));
        }

        //19. MATRIçCULA DO IMOVEL CONDOMIçNIO
        if (imovel.getImovelCondominio() != null) {

            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(imovel.getImovelCondominio()
                                                                                        .getId()
                                                                                        .toString()));
        } else {

            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(null));
        }

        //20. INDICADOR IMOVEL CONDOMaçNIO
        arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(imovel.getIndicadorImovelCondominio()
                                                .toString()));

        //21. IMOVEL_PERFIL
        arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(idImovelPerfil));

        // CONSUMO MEDIO DO IMOVEL

        // [UC1113 - Obter Volume Medio aÅgua ou Esgoto]
        int[] consumoMedioLigacaoAgua = this.getControladorMicromedicao()
                                            .obterVolumeMedioAguaEsgoto(imovel.getId(), faturamentoGrupo.getAnoMesReferencia(), LigacaoTipo.LIGACAO_AGUA);
        //22. CONSUMO MEDIO AGUA NAO MEDIDO
        arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(
        		(String.valueOf(consumoMedioLigacaoAgua[0])!=null?String.valueOf(consumoMedioLigacaoAgua[0]):"0")));

        //23. INDICADOR_FATURAMENTO_ESGOTO
        arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(indicadorFaturamentoSituacaoAgua.toString()));

        //24. INDICADOR_FATURAMENTO_ESGOTO
        arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(indicadorFaturamentoSituacaoEsgoto.toString()));

        //25. INDICADOR_EMISSAO_CONTA
        Short indicadorEmissaoConta = new Short("1");

        boolean naoEmitir = false;
        if (sistemaParametro.getCodigoEmpresaFebraban()
                            .equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAERN)) {
            if (imovel.getImovelContaEnvio() != null
                    && (imovel.getImovelContaEnvio()
                              .getId().equals(ImovelContaEnvio.ENVIAR_CLIENTE_RESPONSAVEL_FINAL_GRUPO))) {
                naoEmitir = true;
            }
        } else {
            if (imovel.getImovelContaEnvio() != null
                    && (imovel.getImovelContaEnvio()
                              .getId()
                              .equals(ImovelContaEnvio.ENVIAR_CLIENTE_RESPONSAVEL)
                            || imovel.getImovelContaEnvio()
                                     .getId()
                                     .equals(ImovelContaEnvio.NAO_PAGAVEL_IMOVEL_PAGAVEL_RESPONSAVEL)
                            || imovel.getImovelContaEnvio()
                                     .getId()
                                     .equals(ImovelContaEnvio.ENVIAR_CONTA_BRAILLE) || imovel.getImovelContaEnvio()
                                                                                             .getId()
                                                                                             .equals(ImovelContaEnvio.ENVIAR_CONTA_BRAILLE_RESPONSAVEL))) {
                naoEmitir = true;
            }
        }

        if (clienteResponsavel != null && naoEmitir) {
            indicadorEmissaoConta = new Short("2");
        }

        arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(indicadorEmissaoConta.toString()));

        //26. CONSUMO_MINIMO_AGUA
        if (imovel.getLigacaoAgua() != null && imovel.getLigacaoAgua()
                                                     .getNumeroConsumoMinimoAgua() != null
                && !imovel.getLigacaoAgua()
                          .getNumeroConsumoMinimoAgua()
                          .equals("")) {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(imovel.getLigacaoAgua()
                                                                                        .getNumeroConsumoMinimoAgua()
                                                                                        .toString()));
        } else {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(null));
        }
        
        //int consumoMinimoLigacao = getControladorMicromedicao().obterConsumoMinimoLigacaoNaoMedido(sistemaParametro,imovel);

        //27. CONSUMO_MINIMO_ESGOTO
		if (imovel.getLigacaoEsgoto() != null && imovel.getLigacaoEsgoto().getConsumoMinimo() != null){
			arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(
			imovel.getLigacaoEsgoto().getConsumoMinimo().toString()));
		}
		else{
			arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(null));
		}

        //28. PERCENTUAL COLETA ESGOTO
        if (imovel.getLigacaoEsgoto() != null && imovel.getLigacaoEsgoto()
                                                       .getPercentualAguaConsumidaColetada() != null) {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(Util.formatarBigDecimalComPonto(imovel.getLigacaoEsgoto()
                                                                                                                        .getPercentualAguaConsumidaColetada())));
        } else {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao("0.00"));
        }

        //29. PERCENTUAL COBRANCA ESGOTO
        if (conta != null) {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(Util.formatarBigDecimalComPonto(conta.getPercentualEsgoto())));
        } else {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao("0.00"));
        }

        //30. TIPO_POCO
        if (imovel.getPocoTipo() != null) {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(imovel.getPocoTipo()
                                                    .getId().toString()));
        } else {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(null));
        }

        //31. CODIGO TARIFA
        arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(imovel.getConsumoTarifa()
                                                                                    .getId()
                                                                                    .toString()));

        // CATEGORIA PRINCIPAL
        Collection colecaoCategoria = null;

        // Obtem a quantidade de economias por categoria
        colecaoCategoria = getControladorImovel().obterQuantidadeEconomiasCategoria(imovel);

        int consumoTotalReferenciaAltoConsumo = 0;
        int consumoTotalReferenciaEstouroConsumo = 0;
        int consumoTotalReferenciaBaixoConsumo = 0;
        int consumoMaximoCobrancaEstouroConsumo = 0;
        int maiorQuantidadeEconomia = 0;
        BigDecimal vezesMediaAltoConsumo = new BigDecimal(0);
        BigDecimal vezesMediaEstouroConsumo = new BigDecimal(0);
        BigDecimal percentualDeterminacaoBaixoConsumo = new BigDecimal(0);

        Iterator colecaoCategoriaIterator = colecaoCategoria.iterator();

        while (colecaoCategoriaIterator.hasNext()) {

            Categoria categoria = (Categoria) colecaoCategoriaIterator.next();

            // Multiplica o consumo por economia de referencia (consumo estouro)
            // pr numero de economias do imovel
            consumoTotalReferenciaAltoConsumo = consumoTotalReferenciaAltoConsumo
                    + (categoria.getConsumoAlto()
                                .intValue() * categoria.getQuantidadeEconomiasCategoria()
                                                       .intValue());

            // Multiplica o consumo por economia de referencia (consumo estouro)
            // pr numero de economias do imovel
            consumoTotalReferenciaEstouroConsumo = consumoTotalReferenciaEstouroConsumo
                    + (categoria.getConsumoEstouro()
                                .intValue() * categoria.getQuantidadeEconomiasCategoria()
                                                       .intValue());

            // Multiplica o consumo por economia de referencia (consumo estouro)
            // pr numero de economias do imovel
            consumoMaximoCobrancaEstouroConsumo = consumoMaximoCobrancaEstouroConsumo
                    + (categoria.getNumeroConsumoMaximoEc()
                                .intValue() * categoria.getQuantidadeEconomiasCategoria()
                                                       .intValue());

            consumoTotalReferenciaBaixoConsumo = consumoTotalReferenciaBaixoConsumo
                    + (categoria.getMediaBaixoConsumo()
                                .intValue() * categoria.getQuantidadeEconomiasCategoria()
                                                       .intValue());

            // Obtem a maior quantidade de economias e a vezes media de estouro
            if (maiorQuantidadeEconomia < categoria.getQuantidadeEconomiasCategoria()
                                                   .intValue()) {

                maiorQuantidadeEconomia = categoria.getQuantidadeEconomiasCategoria()
                                                   .intValue();

                vezesMediaAltoConsumo = categoria.getVezesMediaAltoConsumo();
                vezesMediaEstouroConsumo = categoria.getVezesMediaEstouro();
                percentualDeterminacaoBaixoConsumo = categoria.getPorcentagemMediaBaixoConsumo();
            }
        }

        //32. CONSUMO_REFERENCIA_ESTOURO_CONSUMO
        if (consumoTotalReferenciaEstouroConsumo <= 999999) {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(consumoTotalReferenciaEstouroConsumo));
        } else {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao("999999"));
        }

        //33. CONSUMO_REFERENCIA_ALTO_CONSUMO
        if (consumoTotalReferenciaAltoConsumo <= 999999) {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(consumoTotalReferenciaAltoConsumo));
        } else {
        	arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao("999999"));
        }

        //34. CONSUMO_MEDIA_BAIXO_CONSUMO
        if (consumoTotalReferenciaBaixoConsumo <= 999999) {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(consumoTotalReferenciaBaixoConsumo + ""));
        } else {
        	arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao("999999"));
        }

        //35. FATOR_MULTIPLICACAO_MEDIA_ESTOURO_CONSUMO
        if (vezesMediaEstouroConsumo != null) { 
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(Util.formatarBigDecimalComPonto(vezesMediaEstouroConsumo)));
        } else {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(null));
        }

        //36. FATOR_MULTIPLICACAO_MEDIA_ALTO_CONSUMO
        if (vezesMediaAltoConsumo != null) { 
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(Util.formatarBigDecimalComPonto(vezesMediaAltoConsumo)));
        } else {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(null));
        }

        //37. PERCENTUAL_DETERMINACAO_BAIXO_CONSUMO
        if (percentualDeterminacaoBaixoConsumo != null) {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(Util.formatarBigDecimalComPonto(percentualDeterminacaoBaixoConsumo)));
        } else {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao("0.00"));
        }

        //38. CONSUMO_MAXIMO_COBRANCA_ESTOURO_CONSUMO
        if (consumoMaximoCobrancaEstouroConsumo <= 999999) {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(consumoMaximoCobrancaEstouroConsumo));
        } else {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao("999999"));
        }

        //39. FATURAMENTO_GRUPO
        arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(faturamentoGrupo.getId()
                                                                                              .toString()));

        //40. CODIGO_ROTA
        arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(rota.getCodigo()
                                                                                  .toString()));

        //41. NUMERO DA CONTA
        if (conta != null) {

            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(conta.getId()
                                                                                       .toString()));
        } else {

            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(null));
        }

         //42. Tipo da calculo da tarifa
        if (imovel.getConsumoTarifa() != null && imovel.getConsumoTarifa()
                                                       .getTarifaTipoCalculo() != null) {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(imovel.getConsumoTarifa()
                                                                                             .getTarifaTipoCalculo()
                                                                                             .getId()));
        }

        // Endereco Atendimento 1 Parte
        FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

        filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID,
                                                                 idLocalidade));

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

        Collection cLocalidade = (Collection) getControladorUtil().pesquisar(filtroLocalidade, Localidade.class.getName());

        Localidade localidade = (Localidade) cLocalidade.iterator()
                                                        .next();

        String descricaoAtendimento = localidade.getEnderecoFormatadoTituloAbreviado();

        //43. Endereco Atendimento
        arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(descricaoAtendimento));

        
        String dddMunicipio = "";
        if (localidade.getLogradouroBairro() != null && localidade.getLogradouroBairro()
                                                                  .getBairro() != null && localidade.getLogradouroBairro()
                                                                                                    .getBairro()
                                                                                                    .getMunicipio() != null
                && localidade.getLogradouroBairro()
                             .getBairro()
                             .getMunicipio()
                             .getDdd() != null) {
            dddMunicipio = "" + localidade.getLogradouroBairro()
                                          .getBairro()
                                          .getMunicipio()
                                          .getDdd();
        }

        String fome = "";
        if (localidade.getFone() != null) {
            fome = localidade.getFone();
        }
        //44. TELEFONE DA LOCALIDADE COM DDD 
        arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(dddMunicipio + fome));

        //45. Sequencial de rota
        if (numeroSequencialRota != null) {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(numeroSequencialRota));
        } else {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(null));
        }

        //46,47 E 48. Mensagem da conta em 3 partes
        EmitirContaHelper emitirContaHelper = new EmitirContaHelper();
        emitirContaHelper.setAmReferencia(faturamentoGrupo.getAnoMesReferencia());
        emitirContaHelper.setAnoMesFaturamentoGrupo(faturamentoGrupo.getAnoMesReferencia());
        emitirContaHelper.setIdFaturamentoGrupo(faturamentoGrupo.getId());
        emitirContaHelper.setIdGerenciaRegional(idGerenciaRegional);
        emitirContaHelper.setIdLocalidade(idLocalidade);
        emitirContaHelper.setIdSetorComercial(idSetorComercial);
        emitirContaHelper.setIdImovel(imovel.getId());
        // Caso a empresa seja a CAER
        if (sistemaParametro.getCodigoEmpresaFebraban()
                            .equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAER)) {
            String[] mensagemContaDividida = getControladorFaturamento().obterMensagemConta(emitirContaHelper, sistemaParametro, 4, null);
            if (mensagemContaDividida != null) {
                // Parte 1
                arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(mensagemContaDividida[0]));
                // Parte 2
                arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(mensagemContaDividida[1]));
                // Parte 3
                arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(mensagemContaDividida[2]));
                // Parte 4
                arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(mensagemContaDividida[3]));
                // Parte 5
                arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(mensagemContaDividida[4]));

            } else {
                arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(null));
                arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(null));
                arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(null));
                arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(null));
                arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(null));
            }
        } else {
            String[] mensagemContaDividida = getControladorFaturamento().obterMensagemConta3Partes(emitirContaHelper, sistemaParametro);
            if (mensagemContaDividida != null) {
                // Parte 1
                arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(mensagemContaDividida[0]));
                // Parte 2
                arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(mensagemContaDividida[1]));
                // Parte 3
                arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(mensagemContaDividida[2]));

            } else {
                arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(null));
                arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(null));
                arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(null));
            }
            
            // Alterado para que, independentemente da empresa, seja sempre reservado esses campos para ISC.
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(null));
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(null));
        }

        //49. consumo minimo do imovel
        Integer consumoMinimoImovel = null;

        if (sistemaParametro.getIndicadorNaoMedidoTarifa()
                            .equals(ConstantesSistema.SIM)) {
            // [UC0105] - Obter Consumo Minimo da Ligacao
            consumoMinimoImovel = getControladorMicromedicao().obterConsumoMinimoLigacao(imovel, null);
        } else {
            consumoMinimoImovel = getControladorMicromedicao().obterConsumoNaoMedido(imovel.getId(), sistemaParametro.getAnoMesFaturamento());
        }

        if (consumoMinimoImovel != null) {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(consumoMinimoImovel));
        } else {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(null));

        }

        if (cobrancaDocumento != null && !cobrancaDocumento.equals("")) {
            //50. Documento de Cobranca
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(cobrancaDocumento.getId()));

            String representacaoNumericaCodBarra = "";
            // Obtem a representacao numerica do
            // codigo de
            // barra
            representacaoNumericaCodBarra = this.getControladorArrecadacao()
                                                .obterRepresentacaoNumericaCodigoBarra(5, cobrancaDocumento.getValorDocumento(), cobrancaDocumento.getLocalidade()
                                                                                                                                                  .getId(), cobrancaDocumento.getImovel()
                                                                                                                                                                             .getId(), null, null, null, null, String.valueOf(cobrancaDocumento.getNumeroSequenciaDocumento()), cobrancaDocumento.getDocumentoTipo()
                                                                                                                                                                                                                                                                                                 .getId(), null, null, null);

            // 51. Codigo de Barras do Documento do Cobranca
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(representacaoNumericaCodBarra));

        } else {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(null));
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(null));
        }

        //52. CPF ou CNPJ do CLIENTE
        String cpfCnpj = "";
        if (clienteUsuario != null && !clienteUsuario.equals("")) {
            if (clienteUsuario.getCpf() != null && !clienteUsuario.getCpf()
                                                                  .equals("")) {
                cpfCnpj = clienteUsuario.getCpf();
            } else {
                if (clienteUsuario.getCnpj() != null && !clienteUsuario.getCnpj()
                                                                       .equals("")) {
                    cpfCnpj = clienteUsuario.getCnpj();
                }
            }
        }
        arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(cpfCnpj));

        //53 A 57. GERA AS COLUNAS DA SITUACAO ESPECIAL DE FATURAMENTO
        arquivoTextoRegistroTipo01.append(
        		gerarDadosSituacaoEspecialFaturamentoPipe(imovel, faturamentoGrupo));

        

        // DATA LEITURA ANTERIOR E DATA LEITURA ATUAL
        Integer anoMesFaturamentoAnterior = Util.subtrairMesDoAnoMes(anoMesReferencia, 1);

        Date dataLeituraAnteriorFaturamento = null;
        try {

            dataLeituraAnteriorFaturamento = (Date) repositorioFaturamento.pesquisarFaturamentoAtividadeCronogramaDataRealizacao(faturamentoGrupo.getId(), FaturamentoAtividade.EFETUAR_LEITURA, anoMesFaturamentoAnterior);

        } catch (ErroRepositorioException ex) {
            sessionContext.setRollbackOnly();
            throw new ControladorException("erro.sistema",
                                           ex);
        }

        if (dataLeituraAnteriorFaturamento == null || dataLeituraAnteriorFaturamento.equals("")) {
            // Obter data Prevista do cronograma do mes anterior
            try {

                dataLeituraAnteriorFaturamento = (Date) repositorioFaturamento.pesquisarFaturamentoAtividadeCronogramaDataPrevista(faturamentoGrupo.getId(), FaturamentoAtividade.EFETUAR_LEITURA, anoMesFaturamentoAnterior);

            } catch (ErroRepositorioException ex) {
                sessionContext.setRollbackOnly();
                throw new ControladorException("erro.sistema",
                                               ex);
            }
        }

        if (dataLeituraAnteriorFaturamento == null || dataLeituraAnteriorFaturamento.equals("")) {
            dataLeituraAnteriorFaturamento = Util.subtrairNumeroDiasDeUmaData(new Date(), 30);
        }

        //58. DATA DE LEITURA ANTERIOR NAO MEDIDO
        arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(
        		Util.formatarDataAAAAMMDD(dataLeituraAnteriorFaturamento)));

        // 59.INDICADOR ABASTECIMENTO
        if (imovel.getLigacaoAguaSituacao() != null && !imovel.getLigacaoAguaSituacao()
                                                              .equals("")) {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(imovel.getLigacaoAguaSituacao()
                                                                        .getIndicadorAbastecimento()));
        } else {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(null));
        }

        // verificar se o imovel e Sazonal
        boolean imovelSazonal = false;

        // [UC0108] - Obter Quantidade de Economias por Subcategoria
        Collection colecaoCategoriaOUSubcategoria = this.getControladorImovel()
                                                        .obterQuantidadeEconomiasSubCategoria(imovel.getId());

        Iterator itSubcategoria = colecaoCategoriaOUSubcategoria.iterator();

        while (itSubcategoria.hasNext()) {

            Subcategoria subcategoria = (Subcategoria) itSubcategoria.next();

            if (subcategoria.getIndicadorSazonalidade()
                            .equals(ConstantesSistema.SIM)) {

                imovelSazonal = true;
                break;
            }
        }

        // 60. IMOVEL SAZONAL
        if (imovelSazonal) {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao("1"));
        } else {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao("2"));
        }

        Short indicadorDesconsiderarEstouroAltoConsumo = new Short("2");
        
        // Verificar se da para faturar pela situacacao especial de faturamento
        if (imovel.getFaturamentoSituacaoTipo() != null && !imovel.getFaturamentoSituacaoTipo()
                                                                  .equals("")) {
            FiltroFaturamentoSituacaoHistorico filtroFaturamentoSituacaoHistorico = new FiltroFaturamentoSituacaoHistorico();
            filtroFaturamentoSituacaoHistorico.adicionarParametro(new ParametroSimples(FiltroFaturamentoSituacaoHistorico.ID_IMOVEL,
                                                                                       imovel.getId()));
            filtroFaturamentoSituacaoHistorico.adicionarParametro(new ParametroNulo(FiltroFaturamentoSituacaoHistorico.ANO_MES_FATURAMENTO_RETIRADA));
            Collection<FaturamentoSituacaoHistorico> colFiltroFaturamentoSituacaoHistorico = this.getControladorUtil()
                                                                                                 .pesquisar(filtroFaturamentoSituacaoHistorico, FaturamentoSituacaoHistorico.class.getName());

            FaturamentoSituacaoHistorico faturamentoSituacaoHistorico = (FaturamentoSituacaoHistorico) Util.retonarObjetoDeColecao(colFiltroFaturamentoSituacaoHistorico);

            if ((faturamentoSituacaoHistorico != null
                    && faturamentoGrupo.getAnoMesReferencia() >= faturamentoSituacaoHistorico.getAnoMesFaturamentoSituacaoInicio() && faturamentoGrupo.getAnoMesReferencia() <= faturamentoSituacaoHistorico.getAnoMesFaturamentoSituacaoFim())) {

            	indicadorDesconsiderarEstouroAltoConsumo = 2;            	
            	
            	//MA2013086420 - Tratar novos Tipos de SituaÁ„o Especial de Faturamento 
            	// [UC1360] - Gerar Arquivo Texto Faturamento Vers„o Android
            	//Autor: Diogo Luiz
            	//Data: 04/10/2013
            	
                // INDICADOR_PARALIZACAO_AGUA
                if (imovel.getFaturamentoSituacaoTipo() != null && 
                		!imovel.getFaturamentoSituacaoTipo().equals("")) {
                    indicadorParalisacaoFaturamentoAgua = imovel.getFaturamentoSituacaoTipo()
                    		.getIndicadorParalisaFatAgua();
                    
                }

                //MA2013086420 - Tratar novos Tipos de SituaÁ„o Especial de Faturamento 
            	//[UC1360] - Gerar Arquivo Texto Faturamento Vers„o Android
            	//Autor: Diogo Luiz
            	//Data: 04/10/2013
                
                // INDICADOR_PARALIZACAO_ESGOTO
                if (imovel.getFaturamentoSituacaoTipo() != null && 
                		!imovel.getFaturamentoSituacaoTipo().equals("")) {

                    indicadorParalisacaoFaturamentoEsgoto = imovel.getFaturamentoSituacaoTipo()
                    		.getIndicadorParalisaFatEsgoto();
                }
            }
        }
        //61.	Indicador Paralisacao agua (1) (1 SIM, 2- Nao);
        arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(indicadorParalisacaoFaturamentoAgua));

        //62.	Indicador Paralisacao esgoto (1) (1 -SIM, 2- Nao);
        arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(indicadorParalisacaoFaturamentoEsgoto));

        //63. Codigo Debito Automatico (9);
        if (imovel.getCodigoDebitoAutomatico() != null && !imovel.getCodigoDebitoAutomatico()
                                                                 .equals("")) {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(imovel.getCodigoDebitoAutomatico()));
        } else {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(null));
        }

        //64. PERCENTUAL_ALTERNATIVO_ESGOTO_LIGACAO
        if (imovel.getLigacaoEsgoto() != null && imovel.getLigacaoEsgoto()
                                                       .getPercentualAlternativo() != null) {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(
            		Util.formatarBigDecimalComPonto(imovel.getLigacaoEsgoto().getPercentualAlternativo())));
        } else {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(null));
        }

        //65. CONSUMO_PERCENTUAL_ALTERNATIVO_ESGOTO
        if (imovel.getLigacaoEsgoto() != null && imovel.getLigacaoEsgoto()
                                                       .getNumeroConsumoPercentualAlternativo() != null) {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(imovel.getLigacaoEsgoto()
                                                                                        .getNumeroConsumoPercentualAlternativo()
                                                                                        .toString()));
        } else {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(null));
        }

        //66. DATA DA EMISSAO DO DOCUMENTO DE COBRANCA
        if (cobrancaDocumento != null) {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(
            		Util.formatarDataAAAAMMDD(cobrancaDocumento.getEmissao())));
        } else {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(null));
        }

        // [UC1113 - Obter Volume Medio aÅgua ou Esgoto]
        int[] consumoMedioLigacaoEsgoto = this.getControladorMicromedicao()
                                              .obterVolumeMedioAguaEsgoto(imovel.getId(), faturamentoGrupo.getAnoMesReferencia(), LigacaoTipo.LIGACAO_ESGOTO);

        //67.	Consumo Medio Esgoto Nao Medido (6);
        arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(
        		(String.valueOf(consumoMedioLigacaoEsgoto[0])!=null?String.valueOf(consumoMedioLigacaoEsgoto[0]):"0")));
        
        /*
         * Magno Gouveia em 30/08/2011
         */
        //68.	Indicador de Consumo Real 
        if (imovel.getLigacaoAguaSituacao()
                  .getIndicadorConsumoReal() != null) {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(String.valueOf(imovel.getLigacaoAguaSituacao()
                                                                   .getIndicadorConsumoReal())));
        } else {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(null));
        }

        //69.	Numero de dias (2);
        if (imovel.getLigacaoAguaSituacao()
                  .getNumeroDiasCorte() != null) {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(String.valueOf(imovel.getLigacaoAguaSituacao()
                                                                                                       .getNumeroDiasCorte())));
        } else {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(null));
        }

        //70. DATA DE CORTE DA LIGACAO DE AGUA
        if (imovel.getLigacaoAgua() != null && !imovel.getLigacaoAgua()
                                                      .equals("") && imovel.getLigacaoAgua()
                                                                           .getDataCorte() != null) {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(Util.formatarDataAAAAMMDD(imovel.getLigacaoAgua()
                                                                              .getDataCorte())));
        } else {
            arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(null));
        }
        
		//71. - RM 6602 - Caso o imovel aceite rateio negativo, desconsiderar quantidade maxima parametrizada no sistema
		arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao("2"));
		
		//72. N˙mero da quadra do imÛvel
		arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(imovel.getQuadra().getNumeroQuadra()));
				
		//73	CÛdigo da localidade
		arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(imovel.getLocalidade().getId()));
		
		//74	ID do setor comercial
		arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(imovel.getSetorComercial().getId()));
		
		//75    Indicador ImÛvel ¡rea Comum
		arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(imovel.getIndicadorImovelAreaComum()));
		

//		if ( !sistemaParametro.getNomeAbreviadoEmpresa().equals( "CAERN" ) ){
		//76 Data da ligaÁ„o de ·gua
//		if ( !sistemaParametro.getNomeAbreviadoEmpresa().equals( "CAERN" ) ){
			if (imovel.getLigacaoAgua() != null && imovel.getLigacaoAgua().getDataReligacao() != null) {
				arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(
		    		Util.formatarDataAAAAMMDD(imovel.getLigacaoAgua().getDataReligacao())));
			} else if (imovel.getLigacaoAgua() != null && imovel.getLigacaoAgua().getDataLigacao() != null) {
				arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(
		    		Util.formatarDataAAAAMMDD(imovel.getLigacaoAgua().getDataLigacao())));
			} else {
				arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(null));
			}
	        
	        //77 Data do restabelecimento da ligaÁ„o de ·gua
			if (imovel.getLigacaoAgua() != null && imovel.getLigacaoAgua().getDataRestabelecimentoAgua() != null) {
				arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(
		    		Util.formatarDataAAAAMMDD(imovel.getLigacaoAgua().getDataRestabelecimentoAgua())));
			} else {
				arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(null));
			}
//		}
        
		
			
			
		/*
		 * Pesquisa a conta comunicado associado 
		 * ao imovel referente ao ano mes
		 * faturamento
		 */
		ContaComunicado contaComunicado = fachada.
				pesquisarContaComunicadoAssociadoAoImovel(imovel.getId(),
						sistemaParametro.getAnoMesFaturamento());
		if(contaComunicado!=null){
			arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(contaComunicado.getId()));
			if(!verificarContaComunicadoLista(contaComunicado))
					listaContaComunicadoTipo18.add(contaComunicado);
		}else{
			arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(null));
		}
		
		//Indicador de Faturar com Leitura Real
		if(imovel.getLigacaoAguaSituacao().getIndicadorFaturarLeituraReal() != null){				
			arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(imovel.getLigacaoAguaSituacao()
						.getIndicadorFaturarLeituraReal().toString())); 
		}else{				
			arquivoTextoRegistroTipo01.append(Util.formatarCampoParaConcatenacao(null));
		}
		
        arquivoTextoRegistroTipo01.append(System.getProperty("line.separator"));
        
        return arquivoTextoRegistroTipo01;
    }
  
    /**
     * [UC0745] - Gerar Arquivo Texto para Faturamento
     * 
     * [SB0001] - Gerar Arquivo Texto - Registro Tipo 02
     * 
     * @author Raphael Rossiter
     * @date 24/04/2008
     * 
     * @param imovel
     * @param conta
     * @return StringBuilder
     * @throws ControladorException
     */
    public StringBuilder gerarArquivoTextoRegistroTipo02Pipe(Imovel imovel, Conta conta, SistemaParametro sistemaParametro)
                                                                                                                  throws ControladorException {
        StringBuilder arquivoTextoRegistroTipo02 = new StringBuilder();

        short icTarifaCategoria = sistemaParametro.getIndicadorTarifaCategoria();

        if (icTarifaCategoria == SistemaParametro.INDICADOR_TARIFA_CATEGORIA) {

            Collection colecaoCategoria = null;

            if (conta != null) {

                // PESQUISANDO A PARTIR DA TABELA CONTA_CATEGORIA
                colecaoCategoria = this.getControladorImovel()
                                       .obterQuantidadeEconomiasContaCategoria(conta);
            } else {

                // PESQUISANDO A PARTIR DA TABELA IMOVEL_SUBCATEGORIA
                colecaoCategoria = this.getControladorImovel()
                                       .obterQuantidadeEconomiasCategoria(imovel);
            }

            if (colecaoCategoria != null && !colecaoCategoria.isEmpty()) {

                Iterator iterator = colecaoCategoria.iterator();
                int count = colecaoCategoria.size();

                while (iterator.hasNext()) {
                	
                	count--;

                    Categoria categoria = (Categoria) iterator.next();

                    // TIPO DO REGISTRO
                    arquivoTextoRegistroTipo02.append(Util.formatarCampoParaConcatenacao("02"));

                    // MATRaçCULA DO IMOVEL
                    arquivoTextoRegistroTipo02.append(Util.formatarCampoParaConcatenacao(imovel.getId()
                                                                                                .toString()));

                    // CODIGO_CATEGORIA
                    arquivoTextoRegistroTipo02.append(Util.formatarCampoParaConcatenacao(categoria.getId()
                                                               .toString()));

                    // DESCRICAO_CATEGORIA
                    arquivoTextoRegistroTipo02.append(Util.formatarCampoParaConcatenacao(categoria.getDescricao()));

                    // CODIGO_SUBCATEGORIA
                    arquivoTextoRegistroTipo02.append(Util.formatarCampoParaConcatenacao("0"));

                    // DESCRICAO_SUBCATEGORIA
                    arquivoTextoRegistroTipo02.append(Util.formatarCampoParaConcatenacao(null));

                    // QUANTIDADE_ECONOMIAS_SUBCATEGORIA
                    arquivoTextoRegistroTipo02.append(Util.formatarCampoParaConcatenacao(categoria.getQuantidadeEconomiasCategoria()));

                    // DESCRICAO_ABREVIADA_CATEGORIA
                    arquivoTextoRegistroTipo02.append(Util.formatarCampoParaConcatenacao(categoria.getDescricaoAbreviada()));

                    // DESCRICAO_ABREVIADA_SUBCATEGORIA
                    arquivoTextoRegistroTipo02.append(Util.formatarCampoParaConcatenacao(null));

                    // FATOR ECONOMIAS
                    String fatorEconomias = "";
                    if (categoria.getFatorEconomias() != null) {
                        fatorEconomias = categoria.getFatorEconomias()
                                                  .toString();
                    }
                    arquivoTextoRegistroTipo02.append(Util.formatarCampoParaConcatenacao(fatorEconomias));

                    if (count != 0) {
                      arquivoTextoRegistroTipo02.append(System.getProperty("line.separator"));
                    } 
                    
                }
            }
        } else {

            Collection colecaoSubcategoria = null;

            if (conta != null) {

                // PESQUISANDO A PARTIR DA TABELA CONTA_CATEGORIA
                colecaoSubcategoria = this.getControladorImovel()
                                          .obterQuantidadeEconomiasContaCategoriaPorSubcategoria(conta.getId());
            } else {

                // PESQUISANDO A PARTIR DA TABELA IMOVEL_SUBCATEGORIA
                colecaoSubcategoria = this.getControladorImovel()
                                          .obterQuantidadeEconomiasSubCategoria(imovel.getId());
            }

            if (colecaoSubcategoria != null && !colecaoSubcategoria.isEmpty()) {

                Iterator iterator = colecaoSubcategoria.iterator();
                int count = colecaoSubcategoria.size();

                while (iterator.hasNext()) {
                	
                	count--;

                    Subcategoria subcategoria = (Subcategoria) iterator.next();

                    // TIPO DO REGISTRO
                    arquivoTextoRegistroTipo02.append(Util.formatarCampoParaConcatenacao("02"));

                    // MATRaçCULA DO IMOVEL
                    arquivoTextoRegistroTipo02.append(Util.formatarCampoParaConcatenacao(imovel.getId()
                                                                                                .toString()));

                    // CODIGO_CATEGORIA
                    arquivoTextoRegistroTipo02.append(Util.formatarCampoParaConcatenacao(subcategoria.getCategoria()
                                                                  .getId()
                                                                  .toString()));

                    // DESCRICAO_CATEGORIA
                    arquivoTextoRegistroTipo02.append(Util.formatarCampoParaConcatenacao(subcategoria.getCategoria()
                                                                                      .getDescricao()));

                    // CODIGO_SUBCATEGORIA
                    arquivoTextoRegistroTipo02.append(Util.formatarCampoParaConcatenacao(subcategoria.getId()
                                                                                                      .toString()));

                    // DESCRICAO_SUBCATEGORIA
                    arquivoTextoRegistroTipo02.append(Util.formatarCampoParaConcatenacao(subcategoria.getDescricao()));

                    // QUANTIDADE_ECONOMIAS_SUBCATEGORIA
                    arquivoTextoRegistroTipo02.append(Util.formatarCampoParaConcatenacao(subcategoria.getQuantidadeEconomias()));

                    // DESCRICAO_ABREVIADA_CATEGORIA
                    arquivoTextoRegistroTipo02.append(Util.formatarCampoParaConcatenacao(subcategoria.getCategoria()
                                                                                      .getDescricaoAbreviada()));

                    // DESCRICAO_ABREVIADA_SUBCATEGORIA
                    arquivoTextoRegistroTipo02.append(Util.formatarCampoParaConcatenacao(subcategoria.getDescricaoAbreviada()));

                    // FATOR ECONOMIAS
                    String fatorEconomias = "";
                    if (subcategoria.getCategoria()
                                    .getFatorEconomias() != null) {
                        fatorEconomias = subcategoria.getCategoria()
                                                     .getFatorEconomias()
                                                     .toString();
                    }
                    arquivoTextoRegistroTipo02.append(Util.formatarCampoParaConcatenacao(fatorEconomias));

                    if (count != 0) {
                        arquivoTextoRegistroTipo02.append(System.getProperty("line.separator"));
                    }
                }
            }
        }

        return arquivoTextoRegistroTipo02;
    }

    /**
     * [UC0745] - Gerar Arquivo Texto para Faturamento
     * 
     * [SB0001] - Gerar Arquivo Texto - Registro Tipo 03
     * 
     * @author Raphael Rossiter
     * @date 24/04/2008
     * 
     * @param imovel
     * @param anoMesReferencia
     * @return StringBuilder
     * @throws ControladorException
     */
    public StringBuilder gerarArquivoTextoRegistroTipo03Pipe(Imovel imovel, Integer anoMesReferencia) throws ControladorException {

        StringBuilder arquivoTextoRegistroTipo03 = new StringBuilder();

        // Para cada ima≥vel obter os 6 a∫ltimos consumos e mediaßaµes anteriores.
        // Integer anoMesReferenciaFim =
        // Util.subtrairMesDoAnoMes(anoMesReferencia, 1);
        // Integer anoMesReferenciaInicio =
        // Util.subtrairMesDoAnoMes(anoMesReferencia, 6);

        Collection colecaoConsumoHistorico = this.getControladorMicromedicao()
                                                 .obterUltimosConsumosImovel(imovel);

        if (colecaoConsumoHistorico != null && !colecaoConsumoHistorico.isEmpty()) {

            Iterator iterator = colecaoConsumoHistorico.iterator();
            int count = colecaoConsumoHistorico.size();
            arquivoTextoRegistroTipo03.append(System.getProperty("line.separator"));
            
            while (iterator.hasNext()) {

            	count--;
            	
                ConsumoHistorico consumoHistorico = (ConsumoHistorico) iterator.next();

                // TIPO DO REGISTRO
                arquivoTextoRegistroTipo03.append(Util.formatarCampoParaConcatenacao("03"));

                // MATRaçCULA DO IMOVEL
                arquivoTextoRegistroTipo03.append(Util.formatarCampoParaConcatenacao(imovel.getId()
                                                                                            .toString()));

                // LICAGAO_TIPO
                arquivoTextoRegistroTipo03.append(Util.formatarCampoParaConcatenacao(String.valueOf(consumoHistorico.getLigacaoTipo()
                                                                                 .getId()
                                                                                 .toString())));

                // ANO_MES_FATURAMENTO
                arquivoTextoRegistroTipo03.append(Util.formatarCampoParaConcatenacao(String.valueOf(consumoHistorico.getReferenciaFaturamento())));

                // CONSUMO_FATURADO_MES
                if (consumoHistorico.getNumeroConsumoFaturadoMes() != null) {
                    arquivoTextoRegistroTipo03.append(Util.formatarCampoParaConcatenacao(consumoHistorico.getNumeroConsumoFaturadoMes()
                                                                                                          .toString()));
                } else {
                    arquivoTextoRegistroTipo03.append(Util.formatarCampoParaConcatenacao("0"));
                }

                // ANORMALIDADE_LEITURA
                Integer idLeituraAnormalidadeFaturamento = this.getControladorMicromedicao()
                                                               .obterLeituraAnormalidadeFaturamentoMedicaoHistorico(consumoHistorico);

                if (idLeituraAnormalidadeFaturamento != null) {
                    arquivoTextoRegistroTipo03.append(Util.formatarCampoParaConcatenacao(idLeituraAnormalidadeFaturamento.toString()));
                } else {
                    arquivoTextoRegistroTipo03.append(Util.formatarCampoParaConcatenacao("0"));
                }

                // ANORMALIDADE_CONSUMO
                if (consumoHistorico.getConsumoAnormalidade() != null) {
                    arquivoTextoRegistroTipo03.append(Util.formatarCampoParaConcatenacao(consumoHistorico.getConsumoAnormalidade()
                                                                                                          .getId()
                                                                                                          .toString()));
                } else {
                    arquivoTextoRegistroTipo03.append(Util.formatarCampoParaConcatenacao("0"));
                }

                
               if (count != 0) {
                	arquivoTextoRegistroTipo03.append(System.getProperty("line.separator"));
               } 
            }
        }

        return arquivoTextoRegistroTipo03;
    }

    /**
     * [UC0745] - Gerar Arquivo Texto para Faturamento
     * 
     * [SB0001] - Gerar Arquivo Texto - Registro Tipo 04 [SB0002] - Obter dados
     * dos serviaßos e parcelamento
     * 
     * @author Raphael Rossiter
     * @date 28/04/2008
     * 
     * @param conta
     * @return StringBuilder
     * @throws ControladorException
     */
    public StringBuilder gerarArquivoTextoRegistroTipo04Pipe(Conta conta) throws ControladorException {

        StringBuilder arquivoTextoRegistroTipo04 = new StringBuilder();

        if (conta != null) {

            // [SB0002] - Obter dados dos serviaßos e parcelamento - PARTE I
            // -----------------------------------------------------------------------------------------
            // Object[] debitoCobradoDeParcelamento = null;
            // try {
            // CRC4428 - Vivianne Sousa - 14/06/2010
            Collection<Object[]> colecaoDebitoCobradoDeParcelamento = this.getControladorFaturamento()
                                                       .pesquisarDebitoCobradoDeParcelamento(conta);

            // } catch (ErroRepositorioException e) {
            // sessionContext.setRollbackOnly();
            // throw new ControladorException("erro.sistema", e);
            // }

            if (!Util.isVazioOrNulo(colecaoDebitoCobradoDeParcelamento)) {
            
            	for (Object[] debitoCobradoDeParcelamento : colecaoDebitoCobradoDeParcelamento) {
	
	                // TIPO DO REGISTRO
	            	arquivoTextoRegistroTipo04.append(System.getProperty("line.separator"));
	                arquivoTextoRegistroTipo04.append(Util.formatarCampoParaConcatenacao("04"));
	
	                // MATRICULA DO IMOVEL
	                arquivoTextoRegistroTipo04.append(Util.formatarCampoParaConcatenacao(conta.getImovel()
	                                                                                           .getId()
	                                                                                           .toString()));
	                // DESCRICAO_SERVICO_PARCELAMENTO
	                arquivoTextoRegistroTipo04.append(Util.formatarCampoParaConcatenacao(("PARCELAMENTO DE DEBITOS PARCELA "
	                        + Util.adicionarZerosEsquedaNumero(2, String.valueOf(debitoCobradoDeParcelamento[0]))
	                        + "/" + Util.adicionarZerosEsquedaNumero(2, String.valueOf(debitoCobradoDeParcelamento[1])))));
	
	                // VALOR_SERVICO_PARCELAMENTO
	                arquivoTextoRegistroTipo04.append(Util.formatarCampoParaConcatenacao(Util.formatarBigDecimalComPonto((BigDecimal) debitoCobradoDeParcelamento[2])));
	
	                arquivoTextoRegistroTipo04.append(Util.formatarCampoParaConcatenacao(debitoCobradoDeParcelamento[3]));
	
	                //arquivoTextoRegistroTipo04.append(System.getProperty("line.separator"));
	            }
            
            }

            // FIM - [SB0002] - Obter dados dos servicos e parcelamento - PARTE
            // I
            // -----------------------------------------------------------------------------------------

            // [SB0002] - Obter dados dos servicos e parcelamento - PARTE II
            // -----------------------------------------------------------------------------------------
            // Collection colecaoDebitoCobradoNaoParcelamento = null;
            // try {
            // CRC4428 - Vivianne Sousa - 14/06/2010
            Collection colecaoDebitoCobradoNaoParcelamento = this.getControladorFaturamento()
                                                                 .pesquisarDebitoCobradoNaoParcelamento(conta);

            // } catch (ErroRepositorioException e) {
            // sessionContext.setRollbackOnly();
            // throw new ControladorException("erro.sistema", e);
            // }

            if (colecaoDebitoCobradoNaoParcelamento != null && !colecaoDebitoCobradoNaoParcelamento.isEmpty()) {

                Iterator iterator = colecaoDebitoCobradoNaoParcelamento.iterator();
                DebitoCobrado debitoCobradoAnterior = null;
                DebitoCobrado debitoCobradoAtual = null;
                BigDecimal valorPrestacaoAcumulado = BigDecimal.ZERO;
                String anoMesAcumulado = "";
                Integer qtdAnoMesDistintos = 0;

                while (iterator.hasNext()) {

                    debitoCobradoAtual = (DebitoCobrado) iterator.next();

                    if (debitoCobradoAnterior == null) {

                        debitoCobradoAnterior = debitoCobradoAtual;

                        if (debitoCobradoAnterior.getAnoMesReferenciaDebito() != null) {

                            // ACUMULA O VALOR DA PRESTACAO PARA OS DEBITOS DE
                            // MESMO TIPO
                            valorPrestacaoAcumulado = debitoCobradoAnterior.getValorPrestacao();

                            // ACUMULA A QUANTIDADE DE ANO/MES DISTINTOS PARA O
                            // MESMO TIPO DE DEBITO
                            qtdAnoMesDistintos++;

                            // CONCATENANDO OS ANO/MES DOS DEBITOS DE MESMO TIPO
                            anoMesAcumulado = Util.formatarAnoMesParaMesAno(debitoCobradoAnterior.getAnoMesReferenciaDebito());

                        } else {
                            // GERANDO - [SB0002] - Obter dados dos servicos e
                            // parcelamento - PARTE II
                            arquivoTextoRegistroTipo04.append(this.obterDadosServicosParcelamentosParteIIPipe(conta, debitoCobradoAnterior, 1, "", debitoCobradoAnterior.getValorPrestacao()));

                        }

                    } else if (debitoCobradoAnterior != null
                            && debitoCobradoAnterior.getDebitoTipo()
                                                    .getId()
                                                    .equals(debitoCobradoAtual.getDebitoTipo()
                                                                              .getId())) {

                        debitoCobradoAnterior = debitoCobradoAtual;

                        if (debitoCobradoAnterior.getAnoMesReferenciaDebito() != null) {

                            // ACUMULA O VALOR DA PRESTACAO PARA OS DEBITOS DE
                            // MESMO TIPO
                            valorPrestacaoAcumulado = valorPrestacaoAcumulado.add(debitoCobradoAtual.getValorPrestacao());

                            // ACUMULA A QUANTIDADE DE ANO/MES DISTINTOS PARA O
                            // MESMO TIPO DE DEBITO
                            qtdAnoMesDistintos++;

                            // CONCATENANDO OS ANO/MES DOS DEBITOS DE MESMO TIPO
                            if ( qtdAnoMesDistintos <= MESES_DISTINTOS ){
	                            anoMesAcumulado = anoMesAcumulado + " "
	                                    + Util.formatarAnoMesParaMesAno(debitoCobradoAtual.getAnoMesReferenciaDebito());
                            }

                        } else {
                            // GERANDO - [SB0002] - Obter dados dos servicos e
                            // parcelamento - PARTE II
                            arquivoTextoRegistroTipo04.append(this.obterDadosServicosParcelamentosParteIIPipe(conta, debitoCobradoAnterior, 1, "", debitoCobradoAnterior.getValorPrestacao()));

                        }

                    } else {
                        if (qtdAnoMesDistintos > 0) {
                            // GERANDO - [SB0002] - Obter dados dos servicos e
                            // parcelamento - PARTE II
                            arquivoTextoRegistroTipo04.append(
                            		this.obterDadosServicosParcelamentosParteIIPipe(conta, debitoCobradoAnterior, qtdAnoMesDistintos, anoMesAcumulado, valorPrestacaoAcumulado));
                        }

                        debitoCobradoAnterior = debitoCobradoAtual;
                        qtdAnoMesDistintos = 0;

                        if (debitoCobradoAnterior.getAnoMesReferenciaDebito() != null) {

                            // INICIALIZANDO O VALOR DA PRESTACAO PARA OS
                            // DEBITOS DE MESMO TIPO
                            valorPrestacaoAcumulado = debitoCobradoAnterior.getValorPrestacao();

                            // ACUMULA A QUANTIDADE DE ANO/MES DISTINTOS PARA O
                            // MESMO TIPO DE DEBITO
                            qtdAnoMesDistintos = 1;

                            // CONCATENANDO OS ANO/MES DOS DEBITOS DE MESMO TIPO
                            anoMesAcumulado = Util.formatarAnoMesParaMesAno(debitoCobradoAnterior.getAnoMesReferenciaDebito());
                        } else {
                            // GERANDO - [SB0002] - Obter dados dos servicos e
                            // parcelamento - PARTE II
                            arquivoTextoRegistroTipo04.append(
                            		this.obterDadosServicosParcelamentosParteIIPipe(conta, debitoCobradoAnterior, 1, "", debitoCobradoAnterior.getValorPrestacao()));
                        }
                    }
                }

                if (qtdAnoMesDistintos > 0) {
                    // GERANDO - [SB0002] - Obter dados dos servicos e
                    // parcelamento - PARTE II
                    arquivoTextoRegistroTipo04.append(
                    		this.obterDadosServicosParcelamentosParteIIPipe(conta, debitoCobradoAnterior, qtdAnoMesDistintos, anoMesAcumulado, valorPrestacaoAcumulado));
                }
            }

            // FIM - [SB0002] - Obter dados dos serviaßos e parcelamento - PARTE
            // II
            // -----------------------------------------------------------------------------------------
            
          
        }

        return arquivoTextoRegistroTipo04;
    }
    
    

    /**
     * [UC0745] - Gerar Arquivo Texto para Faturamento
     * 
     * [SB0001] - Gerar Arquivo Texto - Registro Tipo 04 [SB0002] - Obter dados
     * dos serviaßos e parcelamento - PARTE II
     * 
     * @author Raphael Rossiter
     * @date 28/04/2008
     * 
     * @param conta
     * @param debitoCobrado
     * @param qtdAnoMesDistintos
     * @param anoMesAcumulado
     * @param valorPrestacaoAcumulado
     * @return StringBuilder
     * @throws ControladorException
     */
    public StringBuilder obterDadosServicosParcelamentosParteIIPipe(Conta conta, DebitoCobrado debitoCobrado,
            Integer qtdAnoMesDistintos, String anoMesAcumulado, BigDecimal valorPrestacaoAcumulado)
                                                                                                   throws ControladorException {

        StringBuilder arquivoTextoRegistroTipo04 = new StringBuilder();
        arquivoTextoRegistroTipo04.append(System.getProperty("line.separator"));
        
        // TIPO DO REGISTRO
        arquivoTextoRegistroTipo04.append(Util.formatarCampoParaConcatenacao("04"));

        // MATRIçCULA DO IMOVEL
        arquivoTextoRegistroTipo04.append(Util.formatarCampoParaConcatenacao(conta.getImovel()
                                                                                   .getId()
                                                                                   .toString()));

        if (qtdAnoMesDistintos > 1) {

            /*
             * Caso a lista dos meses de refera™ncia distintos do grupo de tipo
             * de da©bitos esteja preenchida
             */

            if (qtdAnoMesDistintos >= MESES_DISTINTOS) {

                // DESCRICAO_SERVICO_PARCELAMENTO
                arquivoTextoRegistroTipo04.append(Util.formatarCampoParaConcatenacao(debitoCobrado.getDebitoTipo()
                        .getDescricao() + " " + anoMesAcumulado + " E OUTRAS"));
            } else {

                // DESCRICAO_SERVICO_PARCELAMENTO
                arquivoTextoRegistroTipo04.append(Util.formatarCampoParaConcatenacao(debitoCobrado.getDebitoTipo()
                                                                                   .getDescricao() + " " + anoMesAcumulado));
            }

            // VALOR_SERVICO_PARCELAMENTO
            arquivoTextoRegistroTipo04.append(Util.formatarCampoParaConcatenacao(Util.formatarBigDecimalComPonto(valorPrestacaoAcumulado)));

            if (debitoCobrado.getDebitoTipo() != null && debitoCobrado.getDebitoTipo()
                                                                      .getCodigoConstante() != null) {
                arquivoTextoRegistroTipo04.append(Util.formatarCampoParaConcatenacao(debitoCobrado.getDebitoTipo()
                                                                                   .getCodigoConstante()));
            } else {
                arquivoTextoRegistroTipo04.append(Util.formatarCampoParaConcatenacao(null));
            }

            //arquivoTextoRegistroTipo04.append(System.getProperty("line.separator"));
        } else {

            if (anoMesAcumulado == null || anoMesAcumulado.equals("")) {
                // Caso contra°rio formata o na∫mero da parcela do da©bito.
                arquivoTextoRegistroTipo04.append(Util.formatarCampoParaConcatenacao(debitoCobrado.getDebitoTipo()
                                                                                   .getDescricao() + " PARCELA "
                        + Util.adicionarZerosEsquedaNumero(2, String.valueOf(debitoCobrado.getNumeroPrestacaoDebito()))
                        + "/" + Util.adicionarZerosEsquedaNumero(2, String.valueOf(debitoCobrado.getNumeroPrestacao()))));

            } else {
                // DESCRICAO_SERVICO_PARCELAMENTO
                arquivoTextoRegistroTipo04.append(Util.formatarCampoParaConcatenacao(debitoCobrado.getDebitoTipo()
                                                                                   .getDescricao() + " " + anoMesAcumulado));
            }

            // VALOR_SERVICO_PARCELAMENTO
            arquivoTextoRegistroTipo04.append(Util.formatarCampoParaConcatenacao(Util.formatarBigDecimalComPonto(debitoCobrado.getValorPrestacao())));

            if (debitoCobrado.getDebitoTipo() != null && debitoCobrado.getDebitoTipo()
                                                                      .getCodigoConstante() != null) {
                arquivoTextoRegistroTipo04.append(Util.formatarCampoParaConcatenacao(debitoCobrado.getDebitoTipo()
                                                                                   .getCodigoConstante()));
            } else {
                arquivoTextoRegistroTipo04.append(Util.formatarCampoParaConcatenacao(null));
            }

            //arquivoTextoRegistroTipo04.append(System.getProperty("line.separator"));

        }
        //arquivoTextoRegistroTipo04.append(System.getProperty("line.separator"));
        return arquivoTextoRegistroTipo04;
    }
    
   

    /**
     * [UC0745] - Gerar Arquivo Texto para Faturamento
     * 
     * [SB0001] - Gerar Arquivo Texto - Registro Tipo 05 [SB0003] - Obter dados
     * dos cra©ditos realizados
     * 
     * @author Raphael Rossiter
     * @date 29/04/2008
     * 
     * @param conta
     * @return StringBuilder
     * @throws ControladorException
     */
    public StringBuilder gerarArquivoTextoRegistroTipo05Pipe(Conta conta) throws ControladorException {

        StringBuilder arquivoTextoRegistroTipo05 = new StringBuilder();

        if (conta != null) {

            // [SB0003] - Obter dados dos cra©ditos realizados
            Collection colecaoCreditoRealizado = null;
            try {

                colecaoCreditoRealizado = this.repositorioFaturamento.pesquisarCreditoRealizado(conta);

            } catch (ErroRepositorioException e) {
                sessionContext.setRollbackOnly();
                throw new ControladorException("erro.sistema",
                                               e);
            }

            if (colecaoCreditoRealizado != null && !colecaoCreditoRealizado.isEmpty()) {

                Iterator iterator = colecaoCreditoRealizado.iterator();
                CreditoRealizado creditoRealizadoAnterior = null;
                CreditoRealizado creditoRealizadoAtual = null;
                BigDecimal valorCreditoAcumulado = BigDecimal.ZERO;
                String anoMesAcumulado = "";
                Integer qtdAnoMesDistintos = 0;

                while (iterator.hasNext()) {

                    creditoRealizadoAtual = (CreditoRealizado) iterator.next();

                    if (creditoRealizadoAnterior == null) {

                        creditoRealizadoAnterior = creditoRealizadoAtual;

                        if (creditoRealizadoAnterior.getAnoMesReferenciaCredito() != null) {

                            // ACUMULA O VALOR DOS CREDITOS DE MESMO TIPO
                            valorCreditoAcumulado = creditoRealizadoAnterior.getValorCredito();

                            // ACUMULA A QUANTIDADE DE ANO/MES DISTINTOS PARA O
                            // MESMO TIPO DE CREDITO
                            qtdAnoMesDistintos++;

                            // CONCATENANDO OS ANO/MES DOS CREDITOS DE MESMO
                            // TIPO
                            anoMesAcumulado = Util.formatarAnoMesParaMesAno(creditoRealizadoAnterior.getAnoMesReferenciaCredito());
                        } else {
                            // GERANDO - [SB0003] - Obter dados dos creditos
                            // realizados
                            arquivoTextoRegistroTipo05.append(this.obterDadosCreditosRealizadosPipe(conta, creditoRealizadoAnterior, 1, "", creditoRealizadoAnterior.getValorCredito()));
                        }

                    } else if (creditoRealizadoAnterior != null
                            && creditoRealizadoAnterior.getCreditoTipo()
                                                       .getId()
                                                       .equals(creditoRealizadoAtual.getCreditoTipo()
                                                                                    .getId())) {

                        creditoRealizadoAnterior = creditoRealizadoAtual;

                        if (creditoRealizadoAnterior.getAnoMesReferenciaCredito() != null) {

                            // ACUMULA O VALOR DOS CREDITOS DE MESMO TIPO
                            valorCreditoAcumulado = valorCreditoAcumulado.add(creditoRealizadoAtual.getValorCredito());

                            // ACUMULA A QUANTIDADE DE ANO/MES DISTINTOS PARA O
                            // MESMO TIPO DE CREDITO
                            qtdAnoMesDistintos++;

                            // CONCATENANDO OS ANO/MES DOS CREDITOS DE MESMO
                            // TIPO
                            anoMesAcumulado = anoMesAcumulado + " "
                                    + Util.formatarAnoMesParaMesAno(creditoRealizadoAtual.getAnoMesReferenciaCredito());
                        } else {
                            // GERANDO - [SB0003] - Obter dados dos creditos
                            // realizados
                            arquivoTextoRegistroTipo05.append(this.obterDadosCreditosRealizadosPipe(conta, creditoRealizadoAnterior, 1, "", creditoRealizadoAnterior.getValorCredito()));
                        }

                    } else {
                        if (qtdAnoMesDistintos > 0) {
                            // GERANDO - [SB0003] - Obter dados dos creditos
                            // realizados
                            arquivoTextoRegistroTipo05.append(this.obterDadosCreditosRealizadosPipe(conta, creditoRealizadoAnterior, qtdAnoMesDistintos, anoMesAcumulado, valorCreditoAcumulado));
                        }

                        creditoRealizadoAnterior = creditoRealizadoAtual;
                        qtdAnoMesDistintos = 0;

                        if (creditoRealizadoAnterior.getAnoMesReferenciaCredito() != null) {

                            // INICIALIZANDO O VALOR DA PRESTACAO PARA OS
                            // DEBITOS DE MESMO TIPO
                            valorCreditoAcumulado = creditoRealizadoAnterior.getValorCredito();

                            // INICIALIZANDO A QUANTIDADE DE ANO/MES DISTINTOS
                            // PARA O MESMO TIPO DE CREDITO
                            qtdAnoMesDistintos = 1;

                            // CONCATENANDO OS ANO/MES DOS CREDITOS DE MESMO
                            // TIPO
                            anoMesAcumulado = Util.formatarAnoMesParaMesAno(creditoRealizadoAnterior.getAnoMesReferenciaCredito());
                        } else {
                            // GERANDO - [SB0003] - Obter dados dos creditos
                            // realizados
                            arquivoTextoRegistroTipo05.append(this.obterDadosCreditosRealizadosPipe(conta, creditoRealizadoAnterior, 1, "", creditoRealizadoAnterior.getValorCredito()));
                        }

                    }
                }

                if (qtdAnoMesDistintos > 0) {
                    // GERANDO - [SB0003] - Obter dados dos creditos realizados
                    arquivoTextoRegistroTipo05.append(this.obterDadosCreditosRealizadosPipe(conta, creditoRealizadoAnterior, qtdAnoMesDistintos, anoMesAcumulado, valorCreditoAcumulado));
                }
            }
        }

        return arquivoTextoRegistroTipo05;
    }
    

    /**
     * [UC0745] - Gerar Arquivo Texto para Faturamento
     * 
     * [SB0001] - Gerar Arquivo Texto - Registro Tipo 05 [SB0003] - Obter dados
     * dos cra©ditos realizados
     * 
     * @author Raphael Rossiter
     * @date 29/04/2008
     * 
     * @param conta
     * @param creditoRealizado
     * @param qtdAnoMesDistintos
     * @param anoMesAcumulado
     * @param valorCreditoAcumulado
     * @return StringBuilder
     * @throws ControladorException
     */
    public StringBuilder obterDadosCreditosRealizadosPipe(Conta conta, CreditoRealizado creditoRealizado,
            Integer qtdAnoMesDistintos, String anoMesAcumulado, BigDecimal valorCreditoAcumulado)
                                                                                                 throws ControladorException {

        StringBuilder arquivoTextoRegistroTipo05 = new StringBuilder();
        arquivoTextoRegistroTipo05.append(System.getProperty("line.separator"));
        
        // TIPO DO REGISTRO
        arquivoTextoRegistroTipo05.append(Util.formatarCampoParaConcatenacao("05"));

        // MATRIçCULA DO IMOVEL
        arquivoTextoRegistroTipo05.append(Util.formatarCampoParaConcatenacao(conta.getImovel()
                                                                                   .getId()
                                                                                   .toString()));

        if (qtdAnoMesDistintos > 1) {

            /*
             * Caso a lista dos meses de referencia distintos do grupo de tipo
             * de creditos esteja preenchida
             */

            if (qtdAnoMesDistintos > 5) {

                // DESCRICAO_CREDITO
                arquivoTextoRegistroTipo05.append(Util.formatarCampoParaConcatenacao(creditoRealizado.getCreditoTipo()
                                                                                      .getDescricao() + " "
                        + anoMesAcumulado + " E OUTRAS"));
            } else {
            	if ( creditoRealizado.getCreditoTipo().getCodigoConstante() != null && creditoRealizado.getCreditoTipo().getCodigoConstante().intValue() == CreditoTipo.CODIGO_CONSTANTE_CONTRATO_DEMANDA ){
            		
                	Collection colConsumoMinimoPercentualEsg =  Fachada.getInstancia().pesquisarContratoDemanda( conta.getImovel().getId()+"" );
                	
                	if ( colConsumoMinimoPercentualEsg != null && colConsumoMinimoPercentualEsg.size() > 0 ){
                		
                		Object[] valores = Util.retonarObjetoDeColecaoArray( colConsumoMinimoPercentualEsg );
                		
                		// DESCRICAO_CREDITO
                		arquivoTextoRegistroTipo05.append(Util.formatarCampoParaConcatenacao(creditoRealizado.getCreditoTipo()
                                                                                          .getDescricao() + " " + valores[1] + "% " + anoMesAcumulado));
                	}
                } else {
            		// DESCRICAO_CREDITO
            		arquivoTextoRegistroTipo05.append(Util.formatarCampoParaConcatenacao(creditoRealizado.getCreditoTipo()
                                                                                      .getDescricao() + " "
                        + anoMesAcumulado));
            	}
            }

            // VALOR_CREDITO
            arquivoTextoRegistroTipo05.append(Util.formatarCampoParaConcatenacao(Util.formatarBigDecimalComPonto(valorCreditoAcumulado)));

            if (creditoRealizado.getCreditoTipo() != null && creditoRealizado.getCreditoTipo()
                                                                             .getCodigoConstante() != null) {
                arquivoTextoRegistroTipo05.append(Util.formatarCampoParaConcatenacao(creditoRealizado.getCreditoTipo()
                                                                                      .getCodigoConstante()));
            } else {
                arquivoTextoRegistroTipo05.append(Util.formatarCampoParaConcatenacao(null));
            }

            //arquivoTextoRegistroTipo05.append(System.getProperty("line.separator"));
        } else {

            // Caso contra°rio formata o na∫mero da parcela do cra©dito.

            if (anoMesAcumulado == null || anoMesAcumulado.equals("")) {
                // DESCRICAO_CREDITO
                arquivoTextoRegistroTipo05.append(Util.formatarCampoParaConcatenacao(creditoRealizado.getCreditoTipo()
                                                                                      .getDescricao() + " PARCELA "
                        + Util.adicionarZerosEsquedaNumero(2, String.valueOf(creditoRealizado.getNumeroPrestacaoCredito()))
                        + "/" + Util.adicionarZerosEsquedaNumero(2, String.valueOf(creditoRealizado.getNumeroPrestacao()))));

            } else {
            	if ( creditoRealizado.getCreditoTipo().getCodigoConstante() != null && creditoRealizado.getCreditoTipo().getCodigoConstante().intValue() == CreditoTipo.CODIGO_CONSTANTE_CONTRATO_DEMANDA ){
            		
                	Collection colConsumoMinimoPercentualEsg =  Fachada.getInstancia().pesquisarContratoDemanda( conta.getImovel().getId()+"" );
                	
                	if ( colConsumoMinimoPercentualEsg != null && colConsumoMinimoPercentualEsg.size() > 0 ){
                		
                		Object[] valores = Util.retonarObjetoDeColecaoArray( colConsumoMinimoPercentualEsg );
                		
                		// DESCRICAO_CREDITO
                		arquivoTextoRegistroTipo05.append(Util.formatarCampoParaConcatenacao(creditoRealizado.getCreditoTipo()
                                                                                          .getDescricao() + " " + valores[1] + "% " + anoMesAcumulado));
                	}
                } else {
            		// DESCRICAO_CREDITO
            		arquivoTextoRegistroTipo05.append(Util.formatarCampoParaConcatenacao(creditoRealizado.getCreditoTipo()
                                                                                      .getDescricao() + " "
                        + anoMesAcumulado));
            	}
            }

            // VALOR_CREDITO
            arquivoTextoRegistroTipo05.append(Util.formatarCampoParaConcatenacao(Util.formatarBigDecimalComPonto(creditoRealizado.getValorCredito())));

            if (creditoRealizado.getCreditoTipo() != null && creditoRealizado.getCreditoTipo()
                                                                             .getCodigoConstante() != null) {
                arquivoTextoRegistroTipo05.append(Util.formatarCampoParaConcatenacao(creditoRealizado.getCreditoTipo()
                                                                                      .getCodigoConstante()));
            } else {
                arquivoTextoRegistroTipo05.append(Util.formatarCampoParaConcatenacao(null));
            }

            //arquivoTextoRegistroTipo05.append(System.getProperty("line.separator"));

        }

        return arquivoTextoRegistroTipo05;
    }
      

    /**
     * [UC0745] - Gerar Arquivo Texto para Faturamento
     * 
     * [SB0001] - Gerar Arquivo Texto - Registro Tipo 06 [SB0004] - Gerar linhas
     * dos impostos retidos
     * 
     * @author Raphael Rossiter
     * @date 29/04/2008
     * 
     * @param conta
     * @return StringBuilder
     * @throws ControladorException
     */
    public StringBuilder gerarArquivoTextoRegistroTipo06Pipe(Conta conta) throws ControladorException {

        StringBuilder arquivoTextoRegistroTipo06 = new StringBuilder();

        if (conta != null) {

            // [SB0004] - Gerar linhas dos impostos retidos
            Collection colecaoImpostosRetidos = null;
            try {

                colecaoImpostosRetidos = this.repositorioFaturamento.pesquisarParmsContaImpostosDeduzidos(conta.getId());

            } catch (ErroRepositorioException e) {
                sessionContext.setRollbackOnly();
                throw new ControladorException("erro.sistema",
                                               e);
            }

            if (colecaoImpostosRetidos != null && !colecaoImpostosRetidos.isEmpty()) {

                Iterator iterator = colecaoImpostosRetidos.iterator();

                while (iterator.hasNext()) {

                    Object[] arrayImpostos = (Object[]) iterator.next();
                    arquivoTextoRegistroTipo06.append(System.getProperty("line.separator"));
                    
                    // TIPO DO REGISTRO
                    arquivoTextoRegistroTipo06.append(Util.formatarCampoParaConcatenacao("06"));

                    // MATRaçCULA DO IMOVEL
                    arquivoTextoRegistroTipo06.append(Util.formatarCampoParaConcatenacao(conta.getImovel()
                                                                                               .getId()
                                                                                               .toString()));

                    // ID_IMPOSTO_TIPO
                    arquivoTextoRegistroTipo06.append(Util.formatarCampoParaConcatenacao(String.valueOf(arrayImpostos[3])));

                    // DESCRICAO_IMPOSTO
                    arquivoTextoRegistroTipo06.append(Util.formatarCampoParaConcatenacao(String.valueOf(arrayImpostos[0])));

                    // PERCENTUAL_ALIQUOTA
                    arquivoTextoRegistroTipo06.append(Util.formatarCampoParaConcatenacao(Util.formatarBigDecimalComPonto((BigDecimal) arrayImpostos[1])));

                   // arquivoTextoRegistroTipo06.append(System.getProperty("line.separator"));
                }
            }
        }

        return arquivoTextoRegistroTipo06;
    }

    /**
     * [UC0745] - Gerar Arquivo Texto para Faturamento
     * 
     * [SB0001] - Gerar Arquivo Texto - Registro Tipo 07 [SB0005] - Obter dados
     * da conta em aberto
     * 
     * @author Raphael Rossiter
     * @date 29/04/2008
     * 
     * @param conta
     * @return StringBuilder
     * @throws ControladorException
     */
    public StringBuilder gerarArquivoTextoRegistroTipo07Pipe(Imovel imovel, SistemaParametro sistemaParametro,
            CobrancaDocumento cobrancaDocumento) throws ControladorException {

        StringBuilder arquivoTextoRegistroTipo07 = new StringBuilder();

        if (cobrancaDocumento != null && !cobrancaDocumento.equals("")) {
        	return formatarCobrancaDocumentoItemParaImpressaoSimultaneaPipe(imovel.getId(), cobrancaDocumento, 17);
        } else {
        	return arquivoTextoRegistroTipo07;            
        }
    }

    /**
     * [UC0745] - Gerar Arquivo Texto para Faturamento
     * 
     * [SB0001] - Gerar Arquivo Texto - Registro Tipo 08 [SB0006] - Obter dados
     * dos tipos de mediaßa£o
     * 
     * @author Raphael Rossiter
     * @date 02/05/2008
     * 
     * @param imovel
     * @param anoMesReferencia
     * @param sistemaParametro
     * @return StringBuilder
     * @throws ControladorException
     */
    public StringBuilder gerarArquivoTextoRegistroTipo08Pipe(Imovel imovel, Integer anoMesReferencia,
            SistemaParametro sistemaParametro) throws ControladorException {

        StringBuilder arquivoTextoRegistroTipo08 = new StringBuilder();

        // [SB0006] - Obter dados dos tipos de mediaßa£o
        Integer anoMesReferenciaAnterior = Util.subtrairMesDoAnoMes(anoMesReferencia, 1);

        Collection colecaoDadosMedicaoHistorico = this.getControladorMicromedicao()
                                                      .obterDadosTiposMedicao(imovel, anoMesReferenciaAnterior);

        if (colecaoDadosMedicaoHistorico != null && !colecaoDadosMedicaoHistorico.isEmpty()) {

            Iterator iterator = colecaoDadosMedicaoHistorico.iterator();
            int count = colecaoDadosMedicaoHistorico.size();
            arquivoTextoRegistroTipo08.append(System.getProperty("line.separator"));
            
            while (iterator.hasNext()) {

            	count--;
            	
                Object[] arrayMedicaoHistorico = (Object[]) iterator.next();
                MedicaoHistorico medicaoHistorico = new MedicaoHistorico();
                Hidrometro hidrometro = null;
                
                // TIPO DO REGISTRO
                arquivoTextoRegistroTipo08.append(Util.formatarCampoParaConcatenacao("08"));

                // MATRICULA DO IMOVEL
                arquivoTextoRegistroTipo08.append(Util.formatarCampoParaConcatenacao(imovel.getId()
                                                                                            .toString()));

                // TIPO DE MEDICAO
                MedicaoTipo medicaoTipo = new MedicaoTipo();

                if (arrayMedicaoHistorico[7] != null && !((Integer) arrayMedicaoHistorico[7]).equals(0)) {

                    medicaoTipo.setId(MedicaoTipo.LIGACAO_AGUA);
                    arquivoTextoRegistroTipo08.append(Util.formatarCampoParaConcatenacao(MedicaoTipo.LIGACAO_AGUA.toString()));

                } else {

                    medicaoTipo.setId(MedicaoTipo.POCO);
                    arquivoTextoRegistroTipo08.append(Util.formatarCampoParaConcatenacao(MedicaoTipo.POCO.toString()));

                }

                // NUMERO DO HIDROMETRO
                if (arrayMedicaoHistorico[0] != null) {

                    hidrometro = new Hidrometro();
                    hidrometro.setNumero(String.valueOf(arrayMedicaoHistorico[0]));

                    arquivoTextoRegistroTipo08.append(Util.formatarCampoParaConcatenacao(hidrometro.getNumero().trim()));
                } else {
                    arquivoTextoRegistroTipo08.append(Util.formatarCampoParaConcatenacao(null));
                }

                // DATA INSTALACAO HIDROMETRO
                if (arrayMedicaoHistorico[2] != null) {
                    arquivoTextoRegistroTipo08.append(Util.formatarCampoParaConcatenacao(Util.formatarDataAAAAMMDD((Date) arrayMedicaoHistorico[2])));
                } else {
                    arquivoTextoRegistroTipo08.append(Util.formatarCampoParaConcatenacao(null));
                }

                // NUMERO DE DIçGITOS DE LEITURA
                if (arrayMedicaoHistorico[1] != null) {

                	if (hidrometro == null){
                    	
                    	hidrometro = new Hidrometro();
                    }
                	
                    hidrometro.setNumeroDigitosLeitura((Short) arrayMedicaoHistorico[1]);
                    arquivoTextoRegistroTipo08.append(Util.formatarCampoParaConcatenacao(hidrometro.getNumeroDigitosLeitura()
                                                                .toString()));
                } else {
                    arquivoTextoRegistroTipo08.append(Util.formatarCampoParaConcatenacao(null));
                }

                // LEITURA ANTERIOR FATURADA
                Integer leituraAnteriorFaturada = (Integer) arrayMedicaoHistorico[3];
                String filtroPoTipoMedicao = "";

                // LEITURA ANTERIOR INFORMADA
                Integer leituraAnteriorInformada = null;
                if (arrayMedicaoHistorico[10] != null) {
                    leituraAnteriorInformada = (Integer) arrayMedicaoHistorico[10];

                }

                if (medicaoTipo.getId()
                               .intValue() == MedicaoTipo.LIGACAO_AGUA.intValue()) {
                    filtroPoTipoMedicao = FiltroMedicaoHistorico.LIGACAO_AGUA_ID;
                } else {
                    filtroPoTipoMedicao = FiltroMedicaoHistorico.IMOVEL_ID;
                }

                Date dataLeituraAnteriorFaturamento = null;

                // Verifica se existe medicao historico para o mes ano atual.
                FiltroMedicaoHistorico filtroMedicaoHistorico = new FiltroMedicaoHistorico();
                filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(filtroPoTipoMedicao,
                                                                               imovel.getId()));
                filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(FiltroMedicaoHistorico.ANO_MES_REFERENCIA_FATURAMENTO,
                                                                               anoMesReferencia));
                Collection<MedicaoHistorico> colMedicaoHistoricoMenos1Mes = getControladorUtil().pesquisar(filtroMedicaoHistorico, MedicaoHistorico.class.getName());
                MedicaoHistorico medicaoHistoricoAtual = (MedicaoHistorico) Util.retonarObjetoDeColecao(colMedicaoHistoricoMenos1Mes);

                Integer idLeituraSituacaoAnterior = null;
                if (medicaoHistoricoAtual != null && !medicaoHistoricoAtual.equals("")) {
                    leituraAnteriorFaturada = medicaoHistoricoAtual.getLeituraAnteriorFaturamento();
                    leituraAnteriorInformada = medicaoHistoricoAtual.getLeituraAnteriorInformada();
                    dataLeituraAnteriorFaturamento = medicaoHistoricoAtual.getDataLeituraAnteriorFaturamento();
                    if(medicaoHistoricoAtual.getLeituraSituacaoAnterior() != null && !medicaoHistoricoAtual.getLeituraSituacaoAnterior().equals("")){
                    	idLeituraSituacaoAnterior = medicaoHistoricoAtual.getLeituraSituacaoAnterior().getId();
                    }
                }

                medicaoHistorico.setLeituraAnteriorFaturamento(leituraAnteriorFaturada);

                if (leituraAnteriorInformada != null) {
                    medicaoHistorico.setLeituraAnteriorInformada(leituraAnteriorInformada);
                }

                arquivoTextoRegistroTipo08.append(Util.formatarCampoParaConcatenacao(leituraAnteriorFaturada.toString()));

                // DATA DA LEITURA ANTERIOR FATURADA
                if (dataLeituraAnteriorFaturamento != null && !dataLeituraAnteriorFaturamento.equals("")) {
                    arquivoTextoRegistroTipo08.append(Util.formatarCampoParaConcatenacao(Util.formatarDataAAAAMMDD(dataLeituraAnteriorFaturamento)));
                } else {
                    if (arrayMedicaoHistorico[4] != null) {
                        arquivoTextoRegistroTipo08.append(Util.formatarCampoParaConcatenacao(Util.formatarDataAAAAMMDD((Date) arrayMedicaoHistorico[4])));
                    } else {
                        arquivoTextoRegistroTipo08.append(Util.formatarCampoParaConcatenacao(null));
                    }
                }

                // LEITURA SITUACAO ANTERIOR
                if(idLeituraSituacaoAnterior != null){
                	LeituraSituacao leituraSituacao = new LeituraSituacao();
                    leituraSituacao.setId(idLeituraSituacaoAnterior);

                    arquivoTextoRegistroTipo08.append(Util.formatarCampoParaConcatenacao(leituraSituacao.getId()
                                                                     .toString()));

                    medicaoHistorico.setLeituraSituacaoAtual(leituraSituacao);
                }else{
	                if (arrayMedicaoHistorico[5] != null) {
	
	                    LeituraSituacao leituraSituacao = new LeituraSituacao();
	                    leituraSituacao.setId((Integer) arrayMedicaoHistorico[5]);
	
	                    arquivoTextoRegistroTipo08.append(Util.formatarCampoParaConcatenacao(leituraSituacao.getId()
	                                                                     .toString()));
	
	                    medicaoHistorico.setLeituraSituacaoAtual(leituraSituacao);
	                } else {
	                    arquivoTextoRegistroTipo08.append(Util.formatarCampoParaConcatenacao(null));
	                }
                }

                // CONSUMO MEDIO HIDROMETRO

                // [UC0068] - Obter Consumo Medio do Hidrometro
                /*
                 * int[] consumoMedioHidrometro =
                 * this.getControladorMicromedicao()
                 * .obterConsumoMedioImovel(imovel, sistemaParametro);
                 */

                sistemaParametro.setAnoMesFaturamento(anoMesReferencia);

                int[] consumoMedioHidrometro = this.getControladorMicromedicao()
                                                   .obterVolumeMedioAguaEsgoto(imovel.getId(), anoMesReferencia, medicaoTipo.getId());

                // [SB0007] - Obter dados do limite de leitura esperada
                Integer[] faixaLeitura = this.obterDadosLimiteLeituraEsperada(imovel, hidrometro, consumoMedioHidrometro[0], medicaoHistorico, sistemaParametro);

                // LIMITE INFERIOR FAIXA LEITURA ESPERADA
                arquivoTextoRegistroTipo08.append(Util.formatarCampoParaConcatenacao(faixaLeitura[0].toString()));

                // LIMITE SUPERIOR FAIXA LEITURA ESPERADA
                arquivoTextoRegistroTipo08.append(Util.formatarCampoParaConcatenacao(faixaLeitura[1].toString()));

                // CONSUMO MEDIO HIDROMETRO
                arquivoTextoRegistroTipo08.append(Util.formatarCampoParaConcatenacao(String.valueOf(consumoMedioHidrometro[0])));

                // DESCRICAO LOCAL INSTALACAO DO HIDROMETRO
                if (arrayMedicaoHistorico[9] != null) {
                    arquivoTextoRegistroTipo08.append(Util.formatarCampoParaConcatenacao(arrayMedicaoHistorico[9]));
                } else {
                    arquivoTextoRegistroTipo08.append(Util.formatarCampoParaConcatenacao(null));
                }

                // LEITURA ANTERIOR INFORMADA
                if (leituraAnteriorInformada != null) {
                    arquivoTextoRegistroTipo08.append(Util.formatarCampoParaConcatenacao(leituraAnteriorInformada.toString()));
                } else {
                    arquivoTextoRegistroTipo08.append(Util.formatarCampoParaConcatenacao(null));
                }

                // TIPO DE RATEIO
                if (arrayMedicaoHistorico[11] != null) {
                    arquivoTextoRegistroTipo08.append(Util.formatarCampoParaConcatenacao(arrayMedicaoHistorico[11]).toString());
                } else {
                    arquivoTextoRegistroTipo08.append(Util.formatarCampoParaConcatenacao(null));
                }

                // LEITURA INFORMADA HIDROMETRO
                if (arrayMedicaoHistorico[12] != null) {
                    arquivoTextoRegistroTipo08.append(Util.formatarCampoParaConcatenacao(arrayMedicaoHistorico[12].toString()));
                } else {
                    arquivoTextoRegistroTipo08.append(Util.formatarCampoParaConcatenacao(null));
                }

                // INDICADOR PARALISAR LEITURA
                String indicadorParalisacaoLeituraHidrometroAgua = "2";
                if (imovel.getFaturamentoSituacaoTipo() != null) {

                    FiltroFaturamentoSituacaoHistorico filtroFaturamentoSituacaoHistorico = new FiltroFaturamentoSituacaoHistorico();
                    filtroFaturamentoSituacaoHistorico.adicionarParametro(new ParametroSimples(FiltroFaturamentoSituacaoHistorico.ID_IMOVEL,
                                                                                               imovel.getId()));
                    filtroFaturamentoSituacaoHistorico.adicionarParametro(new ParametroNulo(FiltroFaturamentoSituacaoHistorico.ANO_MES_FATURAMENTO_RETIRADA));
                    Collection<FaturamentoSituacaoHistorico> colFiltroFaturamentoSituacaoHistorico = this.getControladorUtil()
                                                                                                         .pesquisar(filtroFaturamentoSituacaoHistorico, FaturamentoSituacaoHistorico.class.getName());

                    FaturamentoSituacaoHistorico faturamentoSituacaoHistorico = (FaturamentoSituacaoHistorico) Util.retonarObjetoDeColecao(colFiltroFaturamentoSituacaoHistorico);

                    if ((faturamentoSituacaoHistorico != null
                            && anoMesReferencia >= faturamentoSituacaoHistorico.getAnoMesFaturamentoSituacaoInicio() && anoMesReferencia <= faturamentoSituacaoHistorico.getAnoMesFaturamentoSituacaoFim())) {

                        // FTST_ICLEITURAPARALISACAO=1
                        if (imovel.getFaturamentoSituacaoTipo()
                                  .getIndicadorParalisacaoLeitura()
                                  .equals(new Short("1"))) {

                            // (tipoLigacao=aÅGUA(lgti_id=1)_e_FTST_ICVALIDOAGUA=1)
                            // ou
                            // (tipoligacao=POCO(lgti_id=2)_e_FTST_ICVALIDOESGOTO=1)
                            if ((medicaoTipo.getId()
                                            .equals(MedicaoTipo.LIGACAO_AGUA) && imovel.getFaturamentoSituacaoTipo()
                                                                                       .getIndicadorValidoAgua()
                                                                                       .equals(ConstantesSistema.INDICADOR_USO_ATIVO))
                                    || (medicaoTipo.getId()
                                                   .equals(MedicaoTipo.POCO) && imovel.getFaturamentoSituacaoTipo()
                                                                                      .getIndicadorValidoEsgoto()
                                                                                      .equals(ConstantesSistema.INDICADOR_USO_ATIVO))) {
                                indicadorParalisacaoLeituraHidrometroAgua = "1";
                            }
                        }
                    }
                }
                arquivoTextoRegistroTipo08.append(Util.formatarCampoParaConcatenacao(indicadorParalisacaoLeituraHidrometroAgua));
                           
                
                // Consumo minimo de contrato de demanda
                if ( imovel.getLigacaoAguaSituacao() != null && 
                	 imovel.getLigacaoAguaSituacao().getId().intValue() == LigacaoAguaSituacao.LIGADO && 
                	 medicaoTipo.getId().intValue() == MedicaoTipo.LIGACAO_AGUA &&
                	 Fachada.getInstancia().obterQuantidadeEconomias(imovel) >= 20 ){
                	// Verificamos a existencia de contrato de demanda
                	Collection colConsumoMinimoPercentualEsg =  Fachada.getInstancia().pesquisarContratoDemanda( imovel.getId()+"" );
                	
                	if ( colConsumoMinimoPercentualEsg != null && colConsumoMinimoPercentualEsg.size() > 0 ){
                		
                		Object[] valores = Util.retonarObjetoDeColecaoArray( colConsumoMinimoPercentualEsg );
                		
                		arquivoTextoRegistroTipo08.append(Util.formatarCampoParaConcatenacao(valores[0]));
                		arquivoTextoRegistroTipo08.append(Util.formatarCampoParaConcatenacao(valores[1]));
                	} else {
                		arquivoTextoRegistroTipo08.append(Util.formatarCampoParaConcatenacao(null));
                		arquivoTextoRegistroTipo08.append(Util.formatarCampoParaConcatenacao(null));                		
                	}
                } else {
                	arquivoTextoRegistroTipo08.append(Util.formatarCampoParaConcatenacao(null));
                	arquivoTextoRegistroTipo08.append(Util.formatarCampoParaConcatenacao(null));
                }
                
                //RM6197 - Permitir exibir macromedidor do imovel
                //Tombamento do hidrometro
                arquivoTextoRegistroTipo08.append(Util.formatarCampoParaConcatenacao(null));
                
                if (count != 0) {
                	arquivoTextoRegistroTipo08.append(System.getProperty("line.separator"));
                }                
            }
        }

        return arquivoTextoRegistroTipo08;
    }
    
    /**
     * [UC0745] - Gerar Arquivo Texto para Faturamento
     * 
     * [SB0001] - Gerar Arquivo Texto - Registro Tipo 08 [SB0007] - Obter dados
     * do limite de leitura esperada
     * 
     * @author Raphael Rossiter
     * @date 30/04/2008
     * 
     * @param imovel
     * @param hidrometro
     * @param consumoMedioHidrometro
     * @param medicaoHistorico
     * @param sistemaParametro
     * @return Integer[]
     * @throws ControladorException
     */
    public Integer[] obterDadosLimiteLeituraEsperada(Imovel imovel, Hidrometro hidrometro, Integer consumoMedioHidrometro,
            MedicaoHistorico medicaoHistorico, SistemaParametro sistemaParametro) throws ControladorException {

        Integer[] retorno = new Integer[2];

        /*
         * Caso o ima≥vel na£o possua hidra¥metro instalado para o tipo de mediaßa£o:
         * Gravar a faixa de leitura esperada com zero.
         */
        if (hidrometro == null) {
            retorno[0] = 0;
            retorno[1] = 0;
        } else {

            // [UC0086] - Calcular Faixa de Leitura Esperada
            int[] faixaLeituraEsperada = this.getControladorMicromedicao()
                                                                    .calcularFaixaLeituraEsperada(consumoMedioHidrometro, null, hidrometro, medicaoHistorico.getLeituraAnteriorFaturamento());

            /*
             * Caso esteja indicado nos para¢metros do sistema que na£o seja
             * gerado faixa falsa (PARM_ICGERACAOFAIXAFALSA=2 na tabela
             * SISTEMA_PARAMETRO) ou que seja gerado de acordo com a rota
             * (PARM_ICGERACAOFAIXAFALSA=3 na tabela SISTEMA_PARAMETRO) e esteja
             * indicado na rota que na£o seja gerado faixa falsa
             * (ROTA_ICGERACAOFAIXAFALSA=2): Gravar a faixa de leitura esperada.
             */
            if ((sistemaParametro.getIndicadorFaixaFalsa() != null && sistemaParametro.getIndicadorFaixaFalsa()
                                                                                      .equals(ConstantesSistema.GERAR_FAIXA_FALSA_DESATIVO))
                    || (sistemaParametro.getIndicadorFaixaFalsa() != null
                            && sistemaParametro.getIndicadorFaixaFalsa()
                                               .equals(ConstantesSistema.GERAR_FAIXA_FALSA_ROTA)
                            && imovel.getQuadra()
                                     .getRota()
                                     .getIndicadorGerarFalsaFaixa() != null && imovel.getQuadra()
                                                                                     .getRota()
                                                                                     .getIndicadorGerarFalsaFaixa()
                                                                                     .equals(ConstantesSistema.GERAR_FAIXA_FALSA_DESATIVO))) {

                retorno[0] = faixaLeituraEsperada[0];
                retorno[1] = faixaLeituraEsperada[1];
            } else {

                // [UC0087] - Calcular Faixa de Leitura Falsa
                Object[] faixaLeituraFalsa = this.getControladorMicromedicao()
                                                 .calcularFaixaLeituraFalsa(imovel, consumoMedioHidrometro.intValue(), medicaoHistorico.getLeituraAnteriorFaturamento(), medicaoHistorico, true, hidrometro);

                /*
                 * Caso o hidra¥metro na£o tenha sido selecionado para faixa de
                 * leitura falsa: Gravar a faixa de leitura esperada. Caso
                 * contra°rio: gravar a faixa de leitura falsa
                 */
                boolean hidrometroSelecionado = Boolean.parseBoolean(faixaLeituraFalsa[2].toString());

                if (hidrometroSelecionado) {
                    retorno[0] = Integer.parseInt(faixaLeituraFalsa[0].toString());
                    retorno[1] = Integer.parseInt(faixaLeituraFalsa[1].toString());
                } else {
                    retorno[0] = faixaLeituraEsperada[0];
                    retorno[1] = faixaLeituraEsperada[1];
                }
            }
        }

        return retorno;
    }

    /**
     * [UC0745] - Gerar Arquivo Texto para Faturamento
     * 
     * @author Raphael Rossiter
     * @date 02/05/2008
     * 
     * @param anoMesFaturamento
     * @param faturamentoGrupo
     * @param rota
     * @param imovel
     * @param qtdImoveis
     * @param arquivoTexto
     * @throws ControladorException
     * @return idArquivoTextoRoteiroEmpresa
     */
    public Integer inserirArquivoTextoRoteiroEmpresa(Integer anoMesFaturamento, FaturamentoGrupo faturamentoGrupo,
            Rota rota, Imovel imovel, Integer qtdImoveis, StringBuilder arquivoTexto, Boolean apenasNaoEnviados)
                                                                                                                throws ControladorException {

        ArquivoTextoRoteiroEmpresa arquivoTextoRoteiroEmpresa = null;

        if (!apenasNaoEnviados) {
            arquivoTextoRoteiroEmpresa = new ArquivoTextoRoteiroEmpresa();
        } else {
            FiltroArquivoTextoRoteiroEmpresa filtro = new FiltroArquivoTextoRoteiroEmpresa();
            filtro.adicionarParametro(new ParametroSimples(FiltroArquivoTextoRoteiroEmpresa.ROTA_ID,
                                                           rota.getId()));
            filtro.adicionarParametro(new ParametroSimples(FiltroArquivoTextoRoteiroEmpresa.ANO_MES_REFERENCIA,
                                                           anoMesFaturamento));

            Collection<ArquivoTextoRoteiroEmpresa> colArquivoTextoRoteiroEmpresa = Fachada.getInstancia()
                                                                                          .pesquisar(filtro, ArquivoTextoRoteiroEmpresa.class.getName());

            arquivoTextoRoteiroEmpresa = (ArquivoTextoRoteiroEmpresa) Util.retonarObjetoDeColecao(colArquivoTextoRoteiroEmpresa);
        }

        Rota rotaAlternativa = null;

        if (rota.getIndicadorRotaAlternativa()
                .equals(ConstantesSistema.SIM)) {
            FiltroRota filtroRota = new FiltroRota();
            filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA,
                                                               rota.getId()));
            filtroRota.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
            filtroRota.adicionarCaminhoParaCarregamentoEntidade("setorComercial.localidade");
            filtroRota.adicionarCaminhoParaCarregamentoEntidade("leiturista");
            Collection<Rota> colRotas = Fachada.getInstancia()
                                               .pesquisar(filtroRota, Rota.class.getName());
            rotaAlternativa = (Rota) Util.retonarObjetoDeColecao(colRotas);
        }

        String nomeArquivoTexto = "";
           
        if (rotaAlternativa != null) {
            // NOME DO ARQUIVO
            // [FS0006] - Nome do arquivo texto
            nomeArquivoTexto = "G" + Util.adicionarZerosEsquedaNumero(3, faturamentoGrupo.getId() + "")
                    + Util.adicionarZerosEsquedaNumero(3, rotaAlternativa.getSetorComercial()
                                                                         .getLocalidade()
                                                                         .getId() + "")
                    + Util.adicionarZerosEsquedaNumero(3, rotaAlternativa.getSetorComercial()
                                                                         .getCodigo() + "")
                    + Util.adicionarZerosEsquedaNumero(4, rotaAlternativa.getCodigo() + "")
                    + Util.adicionarZerosEsquedaNumero(6, anoMesFaturamento + "");
        } else {
            // NOME DO ARQUIVO
            // [FS0006] - Nome do arquivo texto
            nomeArquivoTexto = "G" + Util.adicionarZerosEsquedaNumero(3, faturamentoGrupo.getId() + "")
                    + Util.adicionarZerosEsquedaNumero(3, imovel.getLocalidade()
                                                                .getId() + "")
                    + Util.adicionarZerosEsquedaNumero(3, imovel.getSetorComercial()
                                                                .getCodigo() + "")
                    + Util.adicionarZerosEsquedaNumero(4, rota.getCodigo() + "")
                    + Util.adicionarZerosEsquedaNumero(6, anoMesFaturamento + "");
        }
        

        /*
         * [UC1360] Gerar Arquivo Texto para Faturamento Vers„o Android
         * [FS0006] Nome do arquivo texto
         */
        if (rota.getLeituraTipo().getId().equals(LeituraTipo.CELULAR_MOBILE_ANDROID)||
        		rota.getLeituraTipo().getId().equals(LeituraTipo.LEITURA_ANDROID)){
        	nomeArquivoTexto += "A";
        }
        
        // ARQUIVO TEMPORAÅRIO GERADO PARA ROTA
        ByteArrayOutputStream baosArquivoZip = new ByteArrayOutputStream();

        GZIPOutputStream zos = null;
        BufferedWriter out = null;

        try {

            // arquivoTexto =
            // new StringBuilder( Util.reencodeString(
            // arquivoTexto.toString(), "UTF-8" ) );

            // Convertemos o StringBuilder em um vetor de array
            // arquivoTextoByte =
            // IoUtil.transformarObjetoParaBytes(arquivoTexto);

            File compactado = new File(nomeArquivoTexto + ".tar.gz");

            zos = new GZIPOutputStream(new FileOutputStream(compactado));
            File leitura = new File(nomeArquivoTexto + ".txt");

            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(leitura.getAbsolutePath())));
            out.write(arquivoTexto.toString());
            out.flush();
            ZipUtil.adicionarArquivo(zos, leitura);

            zos.close();

            FileInputStream inputStream = new FileInputStream(compactado);

            // Escrevemos aos poucos
            int INPUT_BUFFER_SIZE = 1024;
            byte[] temp = new byte[INPUT_BUFFER_SIZE];
            int numBytesRead = 0;

            while ((numBytesRead = inputStream.read(temp, 0, INPUT_BUFFER_SIZE)) != -1) {
                baosArquivoZip.write(temp, 0, numBytesRead);
            }

            if (!apenasNaoEnviados) {
                arquivoTextoRoteiroEmpresa.setArquivoTexto(baosArquivoZip.toByteArray());
            } else {
                arquivoTextoRoteiroEmpresa.setArquivoTextoNaoRecebido(baosArquivoZip.toByteArray());

                this.getControladorUtil()
                    .atualizar(arquivoTextoRoteiroEmpresa);
            }

            // Fechamos o inputStream
            inputStream.close();
            baosArquivoZip.close();

            inputStream = null;
            compactado.delete();
            leitura.delete();
        } catch (IOException e) {
            sessionContext.setRollbackOnly();
            throw new ControladorException("erro.sistema",
                                           e);
        }

        if (!apenasNaoEnviados) {

            Object[] intervalorNumeroQuadra = null;

            // ANO_MES_REFERENCIA
            arquivoTextoRoteiroEmpresa.setAnoMesReferencia(anoMesFaturamento);

            // FATURAMENTO_GRUPO
            arquivoTextoRoteiroEmpresa.setFaturamentoGrupo(faturamentoGrupo);

            if (rotaAlternativa != null) {
                // EMPRESA
                arquivoTextoRoteiroEmpresa.setEmpresa(rotaAlternativa.getEmpresa());

                // LOCALIDADE
                arquivoTextoRoteiroEmpresa.setLocalidade(rotaAlternativa.getSetorComercial()
                                                                        .getLocalidade());

                // CODIGO DO SETOR COMERCIAL
                arquivoTextoRoteiroEmpresa.setCodigoSetorComercial1(rotaAlternativa.getSetorComercial()
                                                                                   .getCodigo());

              //ROTA ALTERNATIVA N√O POSSUI QUADRA
                intervalorNumeroQuadra = new Object[2];
                
                intervalorNumeroQuadra[0] = 1;
                intervalorNumeroQuadra[1] = 1;

                // ROTA
                arquivoTextoRoteiroEmpresa.setRota(rotaAlternativa);
            } else {
                // EMPRESA
                arquivoTextoRoteiroEmpresa.setEmpresa(rota.getEmpresa());

                // LOCALIDADE
                arquivoTextoRoteiroEmpresa.setLocalidade(imovel.getLocalidade());

                // CODIGO DO SETOR COMERCIAL
                arquivoTextoRoteiroEmpresa.setCodigoSetorComercial1(imovel.getSetorComercial()
                                                                          .getCodigo());

                try {
                    intervalorNumeroQuadra = this.repositorioFaturamento.pesquisarIntervaloNumeroQuadraPorRota(rota.getId());
                } catch (ErroRepositorioException e) {
                    sessionContext.setRollbackOnly();
                    throw new ControladorException("erro.sistema",
                                                   e);
                }

                // ROTA
                arquivoTextoRoteiroEmpresa.setRota(rota);
            }

            // NUMERO SEQUENCIA DE LEITURA
            if (rota.getNumeroSequenciaLeitura() != null && !rota.getNumeroSequenciaLeitura()
                                                                 .equals("")) {
                if (rotaAlternativa != null) {
                    arquivoTextoRoteiroEmpresa.setNumeroSequenciaLeitura(rotaAlternativa.getNumeroSequenciaLeitura());
                } else {
                    arquivoTextoRoteiroEmpresa.setNumeroSequenciaLeitura(rota.getNumeroSequenciaLeitura());
                }
            }

            // MENOR E MAIOR NUMERO DA QUADRA PARA ROTA
            arquivoTextoRoteiroEmpresa.setNumeroQuadraInicial1((Integer) intervalorNumeroQuadra[0]);
            arquivoTextoRoteiroEmpresa.setNumeroQuadraFinal1((Integer) intervalorNumeroQuadra[1]);

            // QUANTIDADE DE IMOVEIS
            arquivoTextoRoteiroEmpresa.setQuantidadeImovel(qtdImoveis);

            arquivoTextoRoteiroEmpresa.setNomeArquivo(nomeArquivoTexto + ".gz");

            // INFORMACOES LEITURISTA
            if (rotaAlternativa != null && rotaAlternativa.getLeiturista() != null) {
                arquivoTextoRoteiroEmpresa.setLeiturista(rotaAlternativa.getLeiturista());
                arquivoTextoRoteiroEmpresa.setCodigoLeiturista(rotaAlternativa.getLeiturista()
                                                                              .getCodigoDDD());
                arquivoTextoRoteiroEmpresa.setNumeroFoneLeiturista(rotaAlternativa.getLeiturista()
                                                                                  .getNumeroFone());

                if (rota.getNumeroLimiteImoveis() == null) {
                    arquivoTextoRoteiroEmpresa.setNumeroImei(rotaAlternativa.getLeiturista()
                                                                            .getNumeroImei());
                } else if (rota.getNumeroLimiteImoveis() != null && qtdImoveis >= 250) {
                    arquivoTextoRoteiroEmpresa.setNumeroImei(null);
                }
            } else if (rota.getLeiturista() != null) {
                arquivoTextoRoteiroEmpresa.setLeiturista(rota.getLeiturista());
                arquivoTextoRoteiroEmpresa.setCodigoLeiturista(rota.getLeiturista()
                                                                   .getCodigoDDD());
                arquivoTextoRoteiroEmpresa.setNumeroFoneLeiturista(rota.getLeiturista()
                                                                       .getNumeroFone());

                if (rota.getNumeroLimiteImoveis() == null) {
                    arquivoTextoRoteiroEmpresa.setNumeroImei(rota.getLeiturista()
                                                                 .getNumeroImei());
                } else if (rota.getNumeroLimiteImoveis() != null && qtdImoveis >= 250) {
                    arquivoTextoRoteiroEmpresa.setNumeroImei(null);
                }
            }

            // SITUACAO_TRANSMISSAO_LEITURA
            SituacaoTransmissaoLeitura situacaoTransmissaoLeitura = new SituacaoTransmissaoLeitura();
            situacaoTransmissaoLeitura.setId(SituacaoTransmissaoLeitura.DISPONIVEL);

            arquivoTextoRoteiroEmpresa.setSituacaoTransmissaoLeitura(situacaoTransmissaoLeitura);

            // ULTIMA ALTERACAO
            arquivoTextoRoteiroEmpresa.setUltimaAlteracao(new Date());

            // ULTIMA ALTERACAO
            arquivoTextoRoteiroEmpresa.setUltimaAlteracao(new Date());

            // TIPO DO SERVICO CELULAR
            ServicoTipoCelular servicoTipoCelular = new ServicoTipoCelular();
            
            if (rota.getLeituraTipo().getId().equals(LeituraTipo.CELULAR_MOBILE_ANDROID)){
            	servicoTipoCelular.setId(ServicoTipoCelular.IMPRESSAO_SIMULTANEA);
            }else if (rota.getLeituraTipo().getId().equals(LeituraTipo.LEITURA_ANDROID)){
            	servicoTipoCelular.setId(ServicoTipoCelular.LEITURA);
            }
            
            
            arquivoTextoRoteiroEmpresa.setServicoTipoCelular(servicoTipoCelular);

            // INSERINDO NA BASE
            return (Integer) this.getControladorUtil()
                                 .inserir(arquivoTextoRoteiroEmpresa);
        }

        return -1;
    }

    
     
    /**
     * [UC0745] - Gerar Arquivo Texto para Faturamento
     * 
     * Formata a parte de padrao da qualidade da agua e a qualidade da a°gua
     * 
     * @author Savio Luiz
     * @date 13/07/2009
     * 
     * @param imovel
     * @param anoMesReferencia
     * @param sistemaParametro
     * @return StringBuilder
     * @throws ControladorException
     */
    public StringBuilder gerarArquivoTextoQualidadeAguaPipe(Integer anoMesReferencia) throws ControladorException {
    	
        StringBuilder arquivoTextoQualidadeAgua = new StringBuilder();

        //Pesquisa Dados Qualidade AÅgua
        FiltroQualidadeAgua filtroQualidadeAgua = new FiltroQualidadeAgua();
        filtroQualidadeAgua.adicionarParametro(new ParametroSimples(FiltroQualidadeAgua.ANO_MES_REFERENCIA, anoMesReferencia));
        filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade(FiltroQualidadeAgua.ID_FONTE_CAPTACAO);
        filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade(FiltroQualidadeAgua.LOCALIDADE);
        filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade(FiltroQualidadeAgua.SETOR_COMERCIAL);
        Collection<QualidadeAgua> colecaoQualidadeAgua = getControladorUtil().pesquisar(filtroQualidadeAgua, QualidadeAgua.class.getName());
        
        //Pesquisa Dados Qualidade aÅgua Padrao
        FiltroQualidadeAguaPadrao filtroQualidadeAguaPadrao = new FiltroQualidadeAguaPadrao();
        Collection<QualidadeAguaPadrao> colecaoQualidadeAguaPadrao = getControladorUtil().pesquisar(filtroQualidadeAguaPadrao, QualidadeAguaPadrao.class.getName());
        QualidadeAguaPadrao qualidadeAguaPadrao = (QualidadeAguaPadrao) Util.retonarObjetoDeColecao(colecaoQualidadeAguaPadrao);
        
        if(colecaoQualidadeAgua!=null && colecaoQualidadeAgua.size()>0){
        	
        	int count = colecaoQualidadeAgua.size();
        	
        	for (QualidadeAgua qualidade : colecaoQualidadeAgua){
        		
        		count--;
        		
				//        		1.	Tipo do Registro - 15
        		arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao("15"));
        		
				//        		2.	ID da qualidade da ·gua(6)
        		arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidade.getId()));
        		
				//        		3.	CÛdigo da localidade(3)
        		if (qualidade.getLocalidade() != null) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidade.getLocalidade().getId()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }
                	
				//        		4.	ID do setor comercial(6)
        		if (qualidade.getSetorComercial() != null) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidade.getSetorComercial().getId()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }
        		
				//        		5.	DescriÁ„o Turbidez 
        		arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidadeAguaPadrao.getDescricaoPadraoTurbidez()));
        		
				//        		6.	DescriÁ„o Ph 
        		if (qualidadeAguaPadrao.getDescricaoPadraoPh() != null) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidadeAguaPadrao.getDescricaoPadraoPh()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }
                // Cor
                if (qualidadeAguaPadrao.getDescricaoPadraoCor() != null) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidadeAguaPadrao.getDescricaoPadraoCor()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }
                // Cloro
                if (qualidadeAguaPadrao.getDescricaoPadraoCloro() != null) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidadeAguaPadrao.getDescricaoPadraoCloro()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }
                // Fluor
                if (qualidadeAguaPadrao.getDescricaoPadraoFluor() != null) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidadeAguaPadrao.getDescricaoPadraoFluor()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }
                // Ferro
                if (qualidadeAguaPadrao.getDescricaoPadraoFerro() != null) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidadeAguaPadrao.getDescricaoPadraoFerro()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }
                
                // Coliformes Totais
                if (qualidadeAguaPadrao.getDescricaoPadraoColiformesTotais() != null) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidadeAguaPadrao.getDescricaoPadraoColiformesTotais()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }
                // Coliformes Fecais
                if (qualidadeAguaPadrao.getDescricaoPadraoColiformesFecais() != null) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidadeAguaPadrao.getDescricaoPadraoColiformesFecais()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }
                // Nitrato
                if (qualidadeAguaPadrao.getDescricaoNitrato() != null) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidadeAguaPadrao.getDescricaoNitrato()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }
                // Coliformes Termo Tolerantes
                if (qualidadeAguaPadrao.getDescricaoPadraoColiformesTermotolerantes() != null) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidadeAguaPadrao.getDescricaoPadraoColiformesTermotolerantes()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }
                
                //mes e ano de referencia
                if (qualidade.getAnoMesReferencia() != null) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(Util.formatarAnoMesParaMesAnoSemBarra(qualidade.getAnoMesReferencia())));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }
                // cloro
                if (qualidade.getNumeroCloroResidual() != null && !qualidade.getNumeroCloroResidual()
                                                                                    .equals(0)) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidade.getNumeroCloroResidual()
                                                                                      .toString()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }
                // turbidez
                if (qualidade.getNumeroIndiceTurbidez() != null && !qualidade.getNumeroIndiceTurbidez()
                                                                                     .equals(0)) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidade.getNumeroIndiceTurbidez()
                                                                                      .toString()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }
                // ph
                if (qualidade.getNumeroIndicePh() != null && !qualidade.getNumeroIndicePh()
                                                                               .equals(0)) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidade.getNumeroIndicePh()
                                                                                      .toString()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }
                // Cor
                if (qualidade.getNumeroIndiceCor() != null && !qualidade.getNumeroIndiceCor()
                                                                                .equals(0)) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidade.getNumeroIndiceCor()
                                                                                      .toString()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }
                // fluor
                if (qualidade.getNumeroIndiceFluor() != null && !qualidade.getNumeroIndiceFluor()
                                                                                  .equals(0)) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidade.getNumeroIndiceFluor()
                                                                                      .toString()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }
                // ferro
                if (qualidade.getNumeroIndiceFerro() != null && !qualidade.getNumeroIndiceFerro()
                                                                                  .equals(0)) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidade.getNumeroIndiceFerro()
                                                                                      .toString()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }
                // Coliformes Totais
                if (qualidade.getNumeroIndiceColiformesTotais() != null && !qualidade.getNumeroIndiceColiformesTotais()
                                                                                             .equals(0)) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidade.getNumeroIndiceColiformesTotais()
                                                                                      .toString()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }
                // Coliformes Fecais
                if (qualidade.getNumeroIndiceColiformesFecais() != null && !qualidade.getNumeroIndiceColiformesFecais()
                                                                                             .equals(0)) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidade.getNumeroIndiceColiformesFecais()
                                                                                      .toString()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }
                // nitrato
                if (qualidade.getNumeroNitrato() != null && !qualidade.getNumeroNitrato()
                                                                              .equals(0)) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidade.getNumeroNitrato()
                                                                                      .toString()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }
                // Coliformes Termo Tolerantes
                if (qualidade.getNumeroIndiceColiformesTermotolerantes() != null
                        && !qualidade.getNumeroIndiceColiformesTermotolerantes()
                                         .equals(0)) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidade.getNumeroIndiceColiformesTermotolerantes()
                                                                                      .toString()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }
                // fonte
                if (qualidade.getFonteCaptacao() != null) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidade.getFonteCaptacao().getDescricao()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }

                // Exigidas
                if (qualidade.getQuantidadeTurbidezExigidas() != null) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidade.getQuantidadeTurbidezExigidas()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }
                if (qualidade.getQuantidadeCorExigidas() != null) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidade.getQuantidadeCorExigidas()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }
                if (qualidade.getQuantidadeCloroExigidas() != null) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidade.getQuantidadeCloroExigidas()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }
                if (qualidade.getQuantidadeFluorExigidas() != null) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidade.getQuantidadeFluorExigidas()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }
                if (qualidade.getQuantidadeColiformesTotaisExigidas() != null) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidade.getQuantidadeColiformesTotaisExigidas()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }
                if (qualidade.getQuantidadeColiformesFecaisExigidas() != null) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidade.getQuantidadeColiformesFecaisExigidas()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }
                if (qualidade.getQuantidadeColiformesTermotolerantesExigidas() != null) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidade.getQuantidadeColiformesTermotolerantesExigidas()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }
                // Analisadas
                if (qualidade.getQuantidadeTurbidezAnalisadas() != null) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidade.getQuantidadeTurbidezAnalisadas()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }
                if (qualidade.getQuantidadeCorAnalisadas() != null) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidade.getQuantidadeCorAnalisadas()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }
                if (qualidade.getQuantidadeCloroAnalisadas() != null) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidade.getQuantidadeCloroAnalisadas()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }
                if (qualidade.getQuantidadeFluorAnalisadas() != null) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidade.getQuantidadeFluorAnalisadas()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }
                if (qualidade.getQuantidadeColiformesTotaisAnalisadas() != null) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidade.getQuantidadeColiformesTotaisAnalisadas()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }
                if (qualidade.getQuantidadeColiformesFecaisAnalisadas() != null) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidade.getQuantidadeColiformesFecaisAnalisadas()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }
                if (qualidade.getQuantidadeColiformesTermotolerantesAnalisadas() != null) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidade.getQuantidadeColiformesTermotolerantesAnalisadas()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }

                // Em Conformidade
                if (qualidade.getQuantidadeTurbidezConforme() != null) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidade.getQuantidadeTurbidezConforme()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }
                if (qualidade.getQuantidadeCorConforme() != null) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidade.getQuantidadeCorConforme()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }
                if (qualidade.getQuantidadeCloroConforme() != null) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidade.getQuantidadeCloroConforme()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }
                if (qualidade.getQuantidadeFluorConforme() != null) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidade.getQuantidadeFluorConforme()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }
                if (qualidade.getQuantidadeColiformesTotaisConforme() != null) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidade.getQuantidadeColiformesTotaisConforme()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }
                if (qualidade.getQuantidadeColiformesFecaisConforme() != null) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidade.getQuantidadeColiformesFecaisConforme()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }
                if (qualidade.getQuantidadeColiformesTermotolerantesConforme() != null) {
                    arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidade.getQuantidadeColiformesTermotolerantesConforme()));
                } else {
                	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
                }
                
                if (count != 0) {
                	arquivoTextoQualidadeAgua.append(System.getProperty("line.separator"));
                }
                
        	}
        } else { //N„o existir qualidade de agua cadastrado
        	//    		1.	Tipo do Registro - 15
    		arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao("15"));
    		
			//        		2.	ID da qualidade da ·gua(6)
    		arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
    		
			//        		3.	CÛdigo da localidade(3)
    		arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
    		
			//        		4.	ID do setor comercial(6)
    		arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
    		
			//        		5.	DescriÁ„o Turbidez 
    		arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidadeAguaPadrao.getDescricaoPadraoTurbidez()));
    		
			//        		6.	DescriÁ„o Ph 
    		if (qualidadeAguaPadrao.getDescricaoPadraoPh() != null) {
                arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidadeAguaPadrao.getDescricaoPadraoPh()));
            } else {
            	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
            }
            // Cor
            if (qualidadeAguaPadrao.getDescricaoPadraoCor() != null) {
                arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidadeAguaPadrao.getDescricaoPadraoCor()));
            } else {
            	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
            }
            // Cloro
            if (qualidadeAguaPadrao.getDescricaoPadraoCloro() != null) {
                arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidadeAguaPadrao.getDescricaoPadraoCloro()));
            } else {
            	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
            }
            // Fluor
            if (qualidadeAguaPadrao.getDescricaoPadraoFluor() != null) {
                arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidadeAguaPadrao.getDescricaoPadraoFluor()));
            } else {
            	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
            }
            // Ferro
            if (qualidadeAguaPadrao.getDescricaoPadraoFerro() != null) {
                arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidadeAguaPadrao.getDescricaoPadraoFerro()));
            } else {
            	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
            }
            
            // Coliformes Totais
            if (qualidadeAguaPadrao.getDescricaoPadraoColiformesTotais() != null) {
                arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidadeAguaPadrao.getDescricaoPadraoColiformesTotais()));
            } else {
            	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
            }
            // Coliformes Fecais
            if (qualidadeAguaPadrao.getDescricaoPadraoColiformesFecais() != null) {
                arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidadeAguaPadrao.getDescricaoPadraoColiformesFecais()));
            } else {
            	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
            }
            // Nitrato
            if (qualidadeAguaPadrao.getDescricaoNitrato() != null) {
                arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidadeAguaPadrao.getDescricaoNitrato()));
            } else {
            	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
            }
            // Coliformes Termo Tolerantes
            if (qualidadeAguaPadrao.getDescricaoPadraoColiformesTermotolerantes() != null) {
                arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(qualidadeAguaPadrao.getDescricaoPadraoColiformesTermotolerantes()));
            } else {
            	arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
            }
            
            //mes e ano de referencia
            arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
            
            // cloro
            arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
            
            // turbidez
            arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
            
            // ph
            arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
            
            // Cor
            arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
            
            // fluor
            arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
            
            // ferro
            arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
            
            // Coliformes Totais
            arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
            
            // Coliformes Fecais
            arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
            
            // nitrato
            arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
            
            // Coliformes Termo Tolerantes
            arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
            
            // fonte
            arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
            
            // Exigidas
            arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
            arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
            arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
            arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
            arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
            arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
            arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
            
            // Analisadas
            arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
            arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
            arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
            arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
            arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
            arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
            arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
            
            // Em Conformidade
            arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
            arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
            arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
            arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
            arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
            arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));
            arquivoTextoQualidadeAgua.append(Util.formatarCampoParaConcatenacao(null));

        }
               
        return arquivoTextoQualidadeAgua;
    }
    
    
    private StringBuilder formatarCobrancaDocumentoItemParaImpressaoSimultaneaPipe(Integer idImovel,
            CobrancaDocumento cobrancaDocumento, int quantidadeContas) throws ControladorException {

        StringBuilder arquivoTextoRegistroTipo07 = new StringBuilder();
        
        if (cobrancaDocumento != null && !cobrancaDocumento.equals("")) {
            Collection colecaoCobrancaDocumentoItemConta = null;
            try {
                colecaoCobrancaDocumentoItemConta = repositorioCobranca.selecionarCobrancaDocumentoItemReferenteConta(cobrancaDocumento);

            } catch (ErroRepositorioException e) {
                sessionContext.setRollbackOnly();
                throw new ControladorException("erro.sistema",
                                               e);
            }

            if (colecaoCobrancaDocumentoItemConta != null && !colecaoCobrancaDocumentoItemConta.isEmpty()) {

                int countImpressao = colecaoCobrancaDocumentoItemConta.size() - (quantidadeContas - 1);

                if (colecaoCobrancaDocumentoItemConta.size() > quantidadeContas) {
                    // indicadorEstouro = 1;

                    CalcularValorDataVencimentoAnteriorHelper contaValoresHelper = getControladorCobranca().calcularValorDataVencimentoAnterior(colecaoCobrancaDocumentoItemConta, quantidadeContas);
                    arquivoTextoRegistroTipo07.append(System.getProperty("line.separator"));
                    
                    // TIPO DO REGISTRO
                    arquivoTextoRegistroTipo07.append(Util.formatarCampoParaConcatenacao("07"));

                    // MATRIçCULA DO IMOVEL
                    arquivoTextoRegistroTipo07.append(Util.formatarCampoParaConcatenacao(idImovel.toString()));

                    // ANO/MES DE REFERENCIA DA CONTA
                    arquivoTextoRegistroTipo07.append(Util.formatarCampoParaConcatenacao("DB.ATE"));

                    // VALOR DA CONTA
                    arquivoTextoRegistroTipo07.append(Util.formatarCampoParaConcatenacao(Util.formatarBigDecimalComPonto(contaValoresHelper.getValorAnterior())));

                    // DATA DE VENCIMENTO
                    arquivoTextoRegistroTipo07.append(Util.formatarCampoParaConcatenacao(Util.formatarDataAAAAMMDD(contaValoresHelper.getDataVencimentoAnterior())));

                    // VALOR ACRESCIMOS IMPONTUALIDADE
                    arquivoTextoRegistroTipo07.append(Util.formatarCampoParaConcatenacao(Util.formatarBigDecimalComPonto(contaValoresHelper.getValorAcrescimosAnterior())));

                   // arquivoTextoRegistroTipo07.append(System.getProperty("line.separator"));
                }

                if (countImpressao <= 1) {
                    Iterator iteratorColecaoCobrancaDocumentoItem = colecaoCobrancaDocumentoItemConta.iterator();

                    CobrancaDocumentoItem cobrancaDocumentoItem = null;

                    while (iteratorColecaoCobrancaDocumentoItem.hasNext()) {
                        cobrancaDocumentoItem = (CobrancaDocumentoItem) iteratorColecaoCobrancaDocumentoItem.next();
                        arquivoTextoRegistroTipo07.append(System.getProperty("line.separator"));
                        
                        // TIPO DO REGISTRO
                        arquivoTextoRegistroTipo07.append(Util.formatarCampoParaConcatenacao("07"));

                        // MATRIçCULA DO IMOVEL
                        arquivoTextoRegistroTipo07.append(Util.formatarCampoParaConcatenacao(idImovel.toString()));

                        // ANO/MES DE REFERENCIA DA CONTA
                        arquivoTextoRegistroTipo07.append(Util.formatarCampoParaConcatenacao(cobrancaDocumentoItem.getContaGeral()
                                                                               .getConta()
                                                                               .getReferencia()));

                        // VALOR DA CONTA
                        arquivoTextoRegistroTipo07.append(Util.formatarCampoParaConcatenacao(Util.formatarBigDecimalComPonto(cobrancaDocumentoItem.getValorItemCobrado())));

                        // DATA DE VENCIMENTO
                        arquivoTextoRegistroTipo07.append(Util.formatarCampoParaConcatenacao(Util.formatarDataAAAAMMDD(cobrancaDocumentoItem.getContaGeral()
                                                                                                         .getConta()
                                                                                                         .getDataVencimentoConta())));

                        // VALOR ACRESCIMOS IMPONTUALIDADE
                        arquivoTextoRegistroTipo07.append(Util.formatarCampoParaConcatenacao(Util.formatarBigDecimalComPonto(cobrancaDocumentoItem.getValorAcrescimos())));

                        //arquivoTextoRegistroTipo07.append(System.getProperty("line.separator"));

                    }

                } else {
                    CobrancaDocumentoItem cobrancaDocumentoItem = null;
                    while (countImpressao < colecaoCobrancaDocumentoItemConta.size()) {
                        cobrancaDocumentoItem = (CobrancaDocumentoItem) ((List) colecaoCobrancaDocumentoItemConta).get(countImpressao);
                        arquivoTextoRegistroTipo07.append(System.getProperty("line.separator"));
                        
                        // TIPO DO REGISTRO
                        arquivoTextoRegistroTipo07.append(Util.formatarCampoParaConcatenacao("07"));

                        // MATRIçCULA DO IMOVEL
                        arquivoTextoRegistroTipo07.append(Util.formatarCampoParaConcatenacao(idImovel.toString()));

                        // ANO/MES DE REFERENCIA DA CONTA
                        arquivoTextoRegistroTipo07.append(Util.formatarCampoParaConcatenacao(cobrancaDocumentoItem.getContaGeral()
                                                                               .getConta()
                                                                               .getReferencia()));

                        // VALOR DA CONTA
                        arquivoTextoRegistroTipo07.append(Util.formatarCampoParaConcatenacao(Util.formatarBigDecimalComPonto(cobrancaDocumentoItem.getValorItemCobrado())));

                        // DATA DE VENCIMENTO
                        arquivoTextoRegistroTipo07.append(Util.formatarCampoParaConcatenacao(Util.formatarDataAAAAMMDD(cobrancaDocumentoItem.getContaGeral()
                                                                                                         .getConta()
                                                                                                         .getDataVencimentoConta())));

                        // VALOR ACRESCIMOS IMPONTUALIDADE
                        arquivoTextoRegistroTipo07.append(Util.formatarCampoParaConcatenacao(Util.formatarBigDecimalComPonto(cobrancaDocumentoItem.getValorAcrescimos())));

                       // arquivoTextoRegistroTipo07.append(System.getProperty("line.separator"));

                        countImpressao++;

                    }
                }
            }

        }

        return arquivoTextoRegistroTipo07;
    }


    /**
     * [UC0745] - Gerar Arquivo Texto para Faturamento
     * 
     * [SB0001] - Gerar Arquivo Texto - Registro Tipo 11
     * 
     * @author Sa°vio Luiz
     * @date 25/11/2009
     * 
     * @param imovel
     * @param conta
     * @param anoMesReferencia
     * @return StringBuilder
     * @throws ControladorException
     */
    public StringBuilder gerarArquivoTextoRegistroTipo11Pipe(SistemaParametro sistemaParametro, Imovel imovel, Integer idLeituraTipo)
                                                                                                          throws ControladorException {

    	
        StringBuilder arquivoTextoRegistroTipo11 = new StringBuilder();

        // TIPO DO REGISTRO
        arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao("11"));

        // Codigo da empresa febraban
        arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(sistemaParametro.getCodigoEmpresaFebraban()));

        // DATA DE REFERENCIA DA ARRECADACAO
        Integer anoMesArrecadacao = sistemaParametro.getAnoMesArrecadacao();

        Date dataReferenciaArrecadacao = Util.criarData(new Integer(Util.obterUltimoDiaMes(Util.obterMes(anoMesArrecadacao), Util.obterAno(anoMesArrecadacao))), Util.obterMes(anoMesArrecadacao), Util.obterAno(anoMesArrecadacao));

        arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(Util.formatarDataAAAAMMDD(dataReferenciaArrecadacao)));

        // Telefone 0800
        String fome0800 = sistemaParametro.getNumero0800Empresa();
        arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(fome0800));

        // CNPJ da Empresa
        if (sistemaParametro.getCnpjEmpresa() != null) {
            arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(sistemaParametro.getCnpjEmpresa()));
        } else {
            arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(null));
        }

        // Inscricao Estadual da Empresa
        if (sistemaParametro.getInscricaoEstadual() != null) {
            arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(sistemaParametro.getInscricaoEstadual()));
        } else {
            arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(null));
        }

        // VALOR MIçNIMO EMISSAO CONTA
        arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(Util.formatarBigDecimalComPonto(sistemaParametro.getValorMinimoEmissaoConta())));

        // PERCENTUAL TOLERANCIA PARA RATEIO
        arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(Util.formatarBigDecimalComPonto(sistemaParametro.getPercentualToleranciaRateio())));

        // DECREMENTO MAÅXIMO DE CONSUMO POR ECONOMIA
        arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(sistemaParametro.getDecrementoMaximoConsumoRateio()));

        // ICCREMENTO MAXIMO DE CONSUMO POR ECONOMIA
        arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(sistemaParametro.getIncrementoMaximoConsumoRateio()));

        // INDICADOR TARIFA CATEGORIA
        arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(sistemaParametro.getIndicadorTarifaCategoria()));

        // ENTRA NO PROXIMA VERSAO
        // Verifica se o existe usuario para leiturista
        Rota rota = null;
        if (imovel != null && imovel.getQuadra() != null) {
            rota = imovel.getQuadra()
                         .getRota();
        }
        if (rota != null && rota.getLeiturista() != null && rota.getLeiturista()
                                                                .getUsuario() != null && !rota.getLeiturista()
                                                                                              .getUsuario()
                                                                                              .equals("")) {
            Usuario usuario = rota.getLeiturista()
                                  .getUsuario();
            if (usuario.getLogin() != null && !usuario.getLogin()
                                                      .equals("")) {
                // LOGIN LEITURISTA
                arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(usuario.getLogin()));

                // SENHA LEITURISTA
                String senhaCriptografada = usuario.getSenha();
                if (senhaCriptografada != null && !senhaCriptografada.equals("")) {
                    arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(senhaCriptografada));
                } else {
                    arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(null));
                }
            } else {
                // LOGIN LEITURISTA
                arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao("gsan"));

                // SENHA LEITURISTA
                String senhaGerada = "senha";
                String senhaCriptografada = null;
                try {
                    senhaCriptografada = Criptografia.encriptarSenha(senhaGerada);
                } catch (ErroCriptografiaException e1) {
                    throw new ControladorException("erro.criptografia.senha");
                }
                arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(senhaCriptografada));
            }
        } else {
            // LOGIN LEITURISTA
            arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao("gsan"));

            // SENHA LEITURISTA
            String senhaGerada = "senha";
            String senhaCriptografada = null;
            try {
                senhaCriptografada = Criptografia.encriptarSenha(senhaGerada);
            } catch (ErroCriptografiaException e1) {
                throw new ControladorException("erro.criptografia.senha");
            }
            arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(senhaCriptografada));
        }

        if (rota != null) {
            // DATA AJUSTE DE LEITURA
            if (rota.getDataAjusteLeitura() != null && !rota.getDataAjusteLeitura()
                                                            .equals("")) {
                arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(Util.formatarDataAAAAMMDD(rota.getDataAjusteLeitura())));
            } else {
                arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(null));
            }
            // INDICADOR AJUSTE DE CONSUMO
            if (rota.getIndicadorAjusteConsumo() != null && !rota.getIndicadorAjusteConsumo()
                                                                 .equals("")) {
                arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(rota.getIndicadorAjusteConsumo()));
            } else {
                arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(null));
            }
            // INDICADOR TRANSMISAO OFFLINE
            if (rota.getIndicadorTransmissaoOffline() != null && !rota.getIndicadorTransmissaoOffline()
                                                                      .equals("")) {
                arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(rota.getIndicadorTransmissaoOffline()));
            } else {
                arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(null));
            }
        } else {
            arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(null));
        }

        // VERSAO DO CELULAR
//        if (sistemaParametro.getVersaoCelular() != null && !sistemaParametro.getVersaoCelular()
//                                                                            .equals("")) {
//            arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(sistemaParametro.getVersaoCelular()));
//        } else {
//            arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(null));
//        }
        
        FiltroVersaoSistemasAndroid filtro  = new FiltroVersaoSistemasAndroid();
        filtro.adicionarParametro(new ParametroSimples(
        		FiltroVersaoSistemasAndroid.SISTEMA_ANDROID_ID, SistemaAndroid.IMPRESSAO_SIMULTANEA));
        filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroVersaoSistemasAndroid.SISTEMA_ANDROID);
        filtro.setCampoOrderBy(FiltroVersaoSistemasAndroid.ID);
        ArrayList<VersaoSistemasAndroid> colecaoVersoes = (ArrayList<VersaoSistemasAndroid>) Fachada.getInstancia()
        		.pesquisar(filtro, VersaoSistemasAndroid.class.getName());
        if (colecaoVersoes!=null && !colecaoVersoes.isEmpty()){      
        	VersaoSistemasAndroid versao = colecaoVersoes.get(colecaoVersoes.size()-1);
        	if (versao!=null) {
        		arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(versao.getNumeroVersao()));
        	} else {
                arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao("1.0.0.0"));
            }
        } else {
            arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao("1.0.0.0"));
        }

        // INDICADOR BLOQUEIO CONTA MOBILE
        if (sistemaParametro.getIndicadorBloqueioContaMobile() != null
                && !sistemaParametro.getIndicadorBloqueioContaMobile()
                                    .equals("")) {
            arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(sistemaParametro.getIndicadorBloqueioContaMobile()));
        } else {
            arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(null));
        }

        // INDICADOR DE ROTA DE MARCACAO
        if (rota.getIndicadorSequencialLeitura() == null) {
            arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(ConstantesSistema.NAO));
        } else {
            arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(rota.getIndicadorSequencialLeitura()));
        }

        // QUANTIDADE DE DIAS DE CONSUMO
        if (rota.getNumeroDiasConsumoAjuste() == null || rota.getNumeroDiasConsumoAjuste()
                                                             .equals("")) {
            arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(null));
        } else {
            arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(rota.getNumeroDiasConsumoAjuste()));
        }

        // MODULO DIGITO VERIFICADOR
        Short moduloVerificador = ConstantesSistema.MODULO_VERIFICADOR_10;
        if (sistemaParametro.getNumeroModuloDigitoVerificador() != null
                && sistemaParametro.getNumeroModuloDigitoVerificador()
                                   .compareTo(ConstantesSistema.MODULO_VERIFICADOR_11) == 0) {
            moduloVerificador = ConstantesSistema.MODULO_VERIFICADOR_11;
        }

        arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(moduloVerificador));

        // verifica se o campo na∫mero de dias para bloqueio a© diferente de nulo,
        // caso na£o seja a quantidade de dias sera° 30.
        int diasBloqueioCelular = 30;
        if (sistemaParametro.getNumeroDiasBloqueioCelular() != null) {
            diasBloqueioCelular = sistemaParametro.getNumeroDiasBloqueioCelular();
        }

        // DATA INICIO PARA BLOQUEIO
        Date dataInicioBloqueio = Util.subtrairNumeroDiasDeUmaData(new Date(), diasBloqueioCelular);
        arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(Util.formatarDataAAAAMMDD(dataInicioBloqueio)));

        // DATA FIM PARA BLOQUEIO
        Date dataFimBloqueio = Util.adicionarNumeroDiasDeUmaData(new Date(), diasBloqueioCelular);
        arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(Util.formatarDataAAAAMMDD(dataFimBloqueio)));
        
        // INDICADOR PARA CALCULO DE CONSUMO DE ESGOTO 
        arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(2));
        
        // INDICADOR PARA DESCONSIDERAR O RATEIO DE ESGOTO
        arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(2));
        
        //INDICADOR COORDENADAS
        if ( rota != null && rota.getIndicadorCoordenadas() != null ) {
        	arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(rota.getIndicadorCoordenadas()));
        }
        
        //26. Indicador de rateio de  consumo para imÛveis com situaÁ„o de n„o faturar
        arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(sistemaParametro.getIndicadorRateioAreaComumImovelNaoFat()));
        
        //27. Indicador se È leitura ou n„o 
        if(idLeituraTipo.equals(LeituraTipo.LEITURA_ANDROID)){
        	arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(ConstantesSistema.SIM));
        }else if (idLeituraTipo.equals(LeituraTipo.CELULAR_MOBILE_ANDROID)){
        	arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(ConstantesSistema.NAO));
        }
        
        //28 Quantidade de dias para identificar que È  uma nova ligaÁ„o de ·gua
//        if ( !sistemaParametro.getNomeAbreviadoEmpresa().equals( "CAERN" ) ){
	        if (sistemaParametro.getNumeroMaximoDiasNovaLigacao() != null) {
	            arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(sistemaParametro.getNumeroMaximoDiasNovaLigacao()));
	         } else {
	            arquivoTextoRegistroTipo11.append(Util.formatarCampoParaConcatenacao(null));
	         }
//        }
        
        return arquivoTextoRegistroTipo11;
    }
        

    /**
     * [UC0745] - Gerar Arquivo Texto para Faturamento
     * 
     * [SB0001] - Gerar Arquivo Texto - Registro Tipo 12
     * 
     * @author Savio Luiz
     * @date 18/12/2009
     * 
     * @param imovel
     * @param conta
     * @param anoMesReferencia
     * @return StringBuilder
     * @throws ControladorException
     */
    public StringBuilder gerarArquivoTextoRegistroTipo12Pipe() throws ControladorException {

        StringBuilder arquivoTextoRegistroTipo12 = new StringBuilder();

        FiltroConsumoAnormalidadeAcao filtroConsumoAnormalidadeAcao = new FiltroConsumoAnormalidadeAcao();
        filtroConsumoAnormalidadeAcao.adicionarParametro(new ParametroSimples(FiltroConsumoAnormalidadeAcao.INDICADOR_USO,
                                                                              ConstantesSistema.INDICADOR_USO_ATIVO));
        Collection<ConsumoAnormalidadeAcao> colecaoConsumoAnormalidadeAcao = getControladorUtil().pesquisar(filtroConsumoAnormalidadeAcao, ConsumoAnormalidadeAcao.class.getName());

        if (colecaoConsumoAnormalidadeAcao != null && !colecaoConsumoAnormalidadeAcao.isEmpty()) {

            int count = colecaoConsumoAnormalidadeAcao.size();

            for (ConsumoAnormalidadeAcao consumoAnormalidadeAcao: colecaoConsumoAnormalidadeAcao){

                count--;

            	// TIPO DO REGISTRO
                arquivoTextoRegistroTipo12.append(Util.formatarCampoParaConcatenacao("12"));

                // Id do consumo Anormalidade
                if (consumoAnormalidadeAcao.getConsumoAnormalidade() != null
                        && consumoAnormalidadeAcao.getConsumoAnormalidade()
                                                  .getId() != null) {
                    arquivoTextoRegistroTipo12.append(Util.formatarCampoParaConcatenacao(
                            consumoAnormalidadeAcao.getConsumoAnormalidade().getId()));
                } else {
                    arquivoTextoRegistroTipo12.append(Util.formatarCampoParaConcatenacao(null));
                }

                // Id da categoria
                if (consumoAnormalidadeAcao.getCategoria() != null && consumoAnormalidadeAcao.getCategoria().getId() != null
                        && !consumoAnormalidadeAcao.getCategoria().getId().equals("")) {
                    arquivoTextoRegistroTipo12.append(Util.formatarCampoParaConcatenacao(consumoAnormalidadeAcao.getCategoria()
                                                     .getId()));
                } else {
                    arquivoTextoRegistroTipo12.append(Util.formatarCampoParaConcatenacao(null));
                }

                // Id do imovel perfil
                if (consumoAnormalidadeAcao.getImovelPerfil() != null && consumoAnormalidadeAcao.getImovelPerfil().getId() != null
                        && !consumoAnormalidadeAcao.getImovelPerfil().getId().equals("")) {
                    arquivoTextoRegistroTipo12.append(Util.formatarCampoParaConcatenacao(consumoAnormalidadeAcao.getImovelPerfil()
                                                     .getId()));
                } else {
                    arquivoTextoRegistroTipo12.append(Util.formatarCampoParaConcatenacao(null));
                }
            
            
                // Id da leitura anormalidade consumo do primeiro mes
                if (consumoAnormalidadeAcao.getLeituraAnormalidadeConsumoMes1() != null
                        && consumoAnormalidadeAcao.getLeituraAnormalidadeConsumoMes1().getId() != null) {
                    arquivoTextoRegistroTipo12.append(Util.formatarCampoParaConcatenacao(consumoAnormalidadeAcao.getLeituraAnormalidadeConsumoMes1()
                                                     .getId()));
                } else {
                    arquivoTextoRegistroTipo12.append(Util.formatarCampoParaConcatenacao(null));
                }

                // Id da leitura anormalidade consumo do segundo mes
                if (consumoAnormalidadeAcao.getLeituraAnormalidadeConsumoMes2() != null
                        && consumoAnormalidadeAcao.getLeituraAnormalidadeConsumoMes2().getId() != null) {
                    arquivoTextoRegistroTipo12.append(Util.formatarCampoParaConcatenacao(consumoAnormalidadeAcao.getLeituraAnormalidadeConsumoMes2()
                                                     .getId()));
                } else {
                    arquivoTextoRegistroTipo12.append(Util.formatarCampoParaConcatenacao(null));
                }	                

                // Id da leitura anormalidade consumo do terceiro mes
                if (consumoAnormalidadeAcao.getLeituraAnormalidadeConsumoMes3() != null
                        && consumoAnormalidadeAcao.getLeituraAnormalidadeConsumoMes3().getId() != null) {
                    arquivoTextoRegistroTipo12.append(Util.formatarCampoParaConcatenacao(consumoAnormalidadeAcao.getLeituraAnormalidadeConsumoMes3()
                                                     .getId()));
                } else {
                    arquivoTextoRegistroTipo12.append(Util.formatarCampoParaConcatenacao(null));
                }

                // Fator de consumo do primeiro mes
                if (consumoAnormalidadeAcao.getNumerofatorConsumoMes1() != null
                        && !consumoAnormalidadeAcao.getNumerofatorConsumoMes1().equals("")) {
                    arquivoTextoRegistroTipo12.append(Util.formatarCampoParaConcatenacao(consumoAnormalidadeAcao.getNumerofatorConsumoMes1()));
                } else {
                    arquivoTextoRegistroTipo12.append(Util.formatarCampoParaConcatenacao(null));
                }

                // Fator de consumo do segundo mes
                if (consumoAnormalidadeAcao.getNumerofatorConsumoMes2() != null
                        && !consumoAnormalidadeAcao.getNumerofatorConsumoMes2().equals("")) {
                    arquivoTextoRegistroTipo12.append(Util.formatarCampoParaConcatenacao(consumoAnormalidadeAcao.getNumerofatorConsumoMes2()));
                } else {
                    arquivoTextoRegistroTipo12.append(Util.formatarCampoParaConcatenacao(null));
                }

                // Fator de consumo do terceiro mes
                if (consumoAnormalidadeAcao.getNumerofatorConsumoMes3() != null
                        && !consumoAnormalidadeAcao.getNumerofatorConsumoMes3().equals("")) {
                    arquivoTextoRegistroTipo12.append(Util.formatarCampoParaConcatenacao(consumoAnormalidadeAcao.getNumerofatorConsumoMes3()));
                } else {
                    arquivoTextoRegistroTipo12.append(Util.formatarCampoParaConcatenacao(null));
                }

                // Mensagem da conta mes 1
                if (consumoAnormalidadeAcao.getDescricaoContaMensagemMes1() != null
                        && !consumoAnormalidadeAcao.getDescricaoContaMensagemMes1().equals("")) {
                    arquivoTextoRegistroTipo12.append(Util.formatarCampoParaConcatenacao(consumoAnormalidadeAcao.getDescricaoContaMensagemMes1()));
                } else {
                    arquivoTextoRegistroTipo12.append(Util.formatarCampoParaConcatenacao(null));
                }

                // Mensagem da conta mes 2
                if (consumoAnormalidadeAcao.getDescricaoContaMensagemMes2() != null
                        && !consumoAnormalidadeAcao.getDescricaoContaMensagemMes2().equals("")) {
                    arquivoTextoRegistroTipo12.append(Util.formatarCampoParaConcatenacao(consumoAnormalidadeAcao.getDescricaoContaMensagemMes2()));
                } else {
                    arquivoTextoRegistroTipo12.append(Util.formatarCampoParaConcatenacao(null));
                }
                	
                // Mensagem da conta mes 3
                if (consumoAnormalidadeAcao.getDescricaoContaMensagemMes3() != null
                        && !consumoAnormalidadeAcao.getDescricaoContaMensagemMes3().equals("")) {
                    arquivoTextoRegistroTipo12.append(Util.formatarCampoParaConcatenacao(consumoAnormalidadeAcao.getDescricaoContaMensagemMes3()));
                } else {
                    arquivoTextoRegistroTipo12.append(Util.formatarCampoParaConcatenacao(null));
                }

                //14 - CÛdigo da meses consecutivos
                arquivoTextoRegistroTipo12.append(Util.formatarCampoParaConcatenacao(null));

                if (count != 0) {
                    arquivoTextoRegistroTipo12.append(System.getProperty("line.separator"));
                }
                
                
            }

        }

        return arquivoTextoRegistroTipo12;
    }
    
    /**
     * [UC0745] - Gerar Arquivo Texto para Faturamento
     * 
     * [SB0001] - Gerar Arquivo Texto - Registro Tipo 13
     * 
     * @author Sa°vio Luiz
     * @date 18/12/2009
     * 
     * @param imovel
     * @param conta
     * @param anoMesReferencia
     * @return StringBuilder
     * @throws ControladorException
     */
    public StringBuilder gerarArquivoTextoRegistroTipo13Pipe() throws ControladorException {

        StringBuilder arquivoTextoRegistroTipo13 = new StringBuilder();

        FiltroConsumoAnormalidade filtroConsumoAnormalidade = new FiltroConsumoAnormalidade(FiltroConsumoAnormalidade.ID);
        filtroConsumoAnormalidade.adicionarParametro(new ParametroSimples(FiltroConsumoAnormalidade.INDICADOR_USO,
                                                                          ConstantesSistema.INDICADOR_USO_ATIVO));
        //filtroConsumoAnormalidade.adicionarParametro(new ParametroNaoNulo(FiltroConsumoAnormalidade.MENSAGEM_CONTA));
        Collection colecaoConsumoAnormalidade = getControladorUtil().pesquisar(filtroConsumoAnormalidade, ConsumoAnormalidade.class.getName());

        if (colecaoConsumoAnormalidade != null && !colecaoConsumoAnormalidade.isEmpty()) {

            int count = colecaoConsumoAnormalidade.size();

            Iterator itConsumoAnormalidade = colecaoConsumoAnormalidade.iterator();
            while (itConsumoAnormalidade.hasNext()) {

                count--;

                ConsumoAnormalidade consumoAnormalidade = (ConsumoAnormalidade) itConsumoAnormalidade.next();

                // TIPO DO REGISTRO
                arquivoTextoRegistroTipo13.append(Util.formatarCampoParaConcatenacao("13"));

                // Id do consumo Anormalidade
                if (consumoAnormalidade.getId() != null && !consumoAnormalidade.getId()
                                                                               .equals("")) {
                    arquivoTextoRegistroTipo13.append(Util.formatarCampoParaConcatenacao(consumoAnormalidade.getId()));
                } else {
                    arquivoTextoRegistroTipo13.append(Util.formatarCampoParaConcatenacao(null));
                }

                // mensagem consumo Anormalidade
                if (consumoAnormalidade.getMensagemConta() != null && !consumoAnormalidade.getMensagemConta()
                                                                                          .equals("")) {
                    arquivoTextoRegistroTipo13.append(Util.formatarCampoParaConcatenacao(consumoAnormalidade.getMensagemConta()));
                } else {
                    arquivoTextoRegistroTipo13.append(Util.formatarCampoParaConcatenacao(null));
                }
                
                // 4. descriÁ„o consumo Anormalidade
                if (consumoAnormalidade.getDescricao() != null && !consumoAnormalidade.getDescricao()
                                                                                          .equals("")) {
                    arquivoTextoRegistroTipo13.append(Util.formatarCampoParaConcatenacao(consumoAnormalidade.getDescricao()));
                } else {
                    arquivoTextoRegistroTipo13.append(Util.formatarCampoParaConcatenacao(null));
                }
                
                //5. Indicador de regra de imovel condominio
                if(consumoAnormalidade.getIndicadorRegraImovelCondominio() != null){
                	arquivoTextoRegistroTipo13.append(Util.formatarCampoParaConcatenacao(consumoAnormalidade.getIndicadorRegraImovelCondominio()));
                }else{
                	arquivoTextoRegistroTipo13.append(Util.formatarCampoParaConcatenacao(ConstantesSistema.NAO));
                }

                if (count != 0) {
                    arquivoTextoRegistroTipo13.append(System.getProperty("line.separator"));
                }

            }

        }

        return arquivoTextoRegistroTipo13;
    }

    /**
     * [UC0745] - Gerar Arquivo Texto para Faturamento
     * 
     * [SB0001] - Gerar Arquivo Texto - Registro Tipo 14 (Leitura Anormalidade)
     * 
     * @author Sa°vio Luiz
     * @date 18/12/2009
     * 
     * @param imovel
     * @param conta
     * @param anoMesReferencia
     * @return StringBuilder
     * @throws ControladorException
     */
    public StringBuilder gerarArquivoTextoRegistroTipo14Pipe() throws ControladorException {

        StringBuilder arquivoTextoRegistroTipo14 = new StringBuilder();

        FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade(FiltroLeituraAnormalidade.ID);
        //filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(FiltroLeituraAnormalidade.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
        Collection colecaoLeituraAnormalidade = getControladorUtil().pesquisar(filtroLeituraAnormalidade, LeituraAnormalidade.class.getName());

        if (colecaoLeituraAnormalidade != null && !colecaoLeituraAnormalidade.isEmpty()) {

            int count = colecaoLeituraAnormalidade.size();

            Iterator itLeituraAnormalidade = colecaoLeituraAnormalidade.iterator();
            while (itLeituraAnormalidade.hasNext()) {

                count--;

                LeituraAnormalidade leituraAnormalidade = (LeituraAnormalidade) itLeituraAnormalidade.next();

                //1. TIPO DO REGISTRO
                arquivoTextoRegistroTipo14.append(Util.formatarCampoParaConcatenacao("14"));

                //2. Id da leitura Anormalidade
                if (leituraAnormalidade.getId() != null && !leituraAnormalidade.getId()
                                                                               .equals("")) {
                    arquivoTextoRegistroTipo14.append(Util.formatarCampoParaConcatenacao(leituraAnormalidade.getId()));
                } else {
                    arquivoTextoRegistroTipo14.append(Util.formatarCampoParaConcatenacao(null));
                }

                //3. descricao da leitura Anormalidade
                if (leituraAnormalidade.getDescricao() != null && !leituraAnormalidade.getDescricao()
                                                                                      .equals("")) {
                    arquivoTextoRegistroTipo14.append(Util.formatarCampoParaConcatenacao(leituraAnormalidade.getDescricao()));
                } else {
                    arquivoTextoRegistroTipo14.append(Util.formatarCampoParaConcatenacao(null));
                }

                //4. INDICADOR ACEITA LEITURA
                if (leituraAnormalidade.getIndicadorLeitura() != null && !leituraAnormalidade.getIndicadorLeitura()
                                                                                             .equals("")) {
                    arquivoTextoRegistroTipo14.append(Util.formatarCampoParaConcatenacao(leituraAnormalidade.getIndicadorLeitura()));
                } else {
                    arquivoTextoRegistroTipo14.append(Util.formatarCampoParaConcatenacao(null));
                }

                // ID Consumo a cobrar com leitura
                if (leituraAnormalidade.getLeituraAnormalidadeConsumoComleitura() != null
                        && !leituraAnormalidade.getLeituraAnormalidadeConsumoComleitura()
                                               .equals("")) {
                    arquivoTextoRegistroTipo14.append(Util.formatarCampoParaConcatenacao(leituraAnormalidade.getLeituraAnormalidadeConsumoComleitura()
                                                 .getId()));
                } else {
                    arquivoTextoRegistroTipo14.append(Util.formatarCampoParaConcatenacao(null));
                }

                // ID Consumo a cobrar sem leitura
                if (leituraAnormalidade.getLeituraAnormalidadeConsumoSemleitura() != null
                        && !leituraAnormalidade.getLeituraAnormalidadeConsumoSemleitura()
                                               .equals("")) {
                    arquivoTextoRegistroTipo14.append(Util.formatarCampoParaConcatenacao(leituraAnormalidade.getLeituraAnormalidadeConsumoSemleitura()
                                                 .getId()));
                } else {
                    arquivoTextoRegistroTipo14.append(Util.formatarCampoParaConcatenacao(null));
                }

                // ID Leitura anormalidade leitura com leitura
                if (leituraAnormalidade.getLeituraAnormalidadeLeituraComleitura() != null
                        && !leituraAnormalidade.getLeituraAnormalidadeLeituraComleitura()
                                               .equals("")) {
                    arquivoTextoRegistroTipo14.append(Util.formatarCampoParaConcatenacao(leituraAnormalidade.getLeituraAnormalidadeLeituraComleitura()
                                                 .getId()));
                } else {
                    arquivoTextoRegistroTipo14.append(Util.formatarCampoParaConcatenacao(null));
                }

                // ID Leitura anormalidade leitura sem leitura
                if (leituraAnormalidade.getLeituraAnormalidadeLeituraSemleitura() != null
                        && !leituraAnormalidade.getLeituraAnormalidadeLeituraSemleitura()
                                               .equals("")) {
                    arquivoTextoRegistroTipo14.append(Util.formatarCampoParaConcatenacao(leituraAnormalidade.getLeituraAnormalidadeLeituraSemleitura()
                                                 .getId()));
                } else {
                    arquivoTextoRegistroTipo14.append(Util.formatarCampoParaConcatenacao(null));
                }

                //9. Indicador de uso
                if (leituraAnormalidade.getIndicadorUso() != null && !leituraAnormalidade.getIndicadorUso()
                                                                                         .equals("")) {
                    arquivoTextoRegistroTipo14.append(Util.formatarCampoParaConcatenacao(leituraAnormalidade.getIndicadorUso()));
                } else {
                    arquivoTextoRegistroTipo14.append(Util.formatarCampoParaConcatenacao(null));
                }

                //10. numero fator sem leitura
                if (leituraAnormalidade.getNumeroFatorSemLeitura() != null
                        && !leituraAnormalidade.getNumeroFatorSemLeitura()
                                               .equals("")) {
                    arquivoTextoRegistroTipo14.append(Util.formatarCampoParaConcatenacao(leituraAnormalidade.getNumeroFatorSemLeitura()));
                } else {
                    arquivoTextoRegistroTipo14.append(Util.formatarCampoParaConcatenacao(null));
                }

                //11. numero fator com leitura
                if (leituraAnormalidade.getNumeroFatorComLeitura() != null
                        && !leituraAnormalidade.getNumeroFatorComLeitura()
                                               .equals("")) {
                    arquivoTextoRegistroTipo14.append(Util.formatarCampoParaConcatenacao(leituraAnormalidade.getNumeroFatorComLeitura()));
                } else {
                    arquivoTextoRegistroTipo14.append(Util.formatarCampoParaConcatenacao(null));
                }

                
               //12. Indicador de exibir mensagem para os hidrometros cadastrados na calcada   
                if (leituraAnormalidade.getIndicadorExibirMensagemHidrometrosCalcada() != null
                        && !leituraAnormalidade.getIndicadorExibirMensagemHidrometrosCalcada()
                                               .equals("")) {
                    arquivoTextoRegistroTipo14.append(Util.formatarCampoParaConcatenacao(leituraAnormalidade.getIndicadorExibirMensagemHidrometrosCalcada()));
                } else {
                    arquivoTextoRegistroTipo14.append(Util.formatarCampoParaConcatenacao(null));
                }
                
                
                
                //13. Indicador de exibir mensagem para os hidrometros substituidos e 
                //que o leiturista informe uma anormalidade  
                if (leituraAnormalidade.getIndicadorExibirMensagemHidrometrosSubstituidos() != null
                        && !leituraAnormalidade.getIndicadorExibirMensagemHidrometrosSubstituidos()
                                               .equals("")) {
                    arquivoTextoRegistroTipo14.append(Util.formatarCampoParaConcatenacao(leituraAnormalidade.getIndicadorExibirMensagemHidrometrosSubstituidos()));
                } else {
                    arquivoTextoRegistroTipo14.append(Util.formatarCampoParaConcatenacao(null));
                }
                
                //14. Indicador de nao impressao da conta
                if (leituraAnormalidade.getIndicadorNaoImprimirConta() != null &&
                		!leituraAnormalidade.getIndicadorNaoImprimirConta()
                                               .equals("")){
                	 arquivoTextoRegistroTipo14.append(Util.formatarCampoParaConcatenacao(leituraAnormalidade.getIndicadorNaoImprimirConta()));
                } else {
                    arquivoTextoRegistroTipo14.append(Util.formatarCampoParaConcatenacao(null));
                }
                
                //15. Indicador de obrigatoriedade da foto
                if ( leituraAnormalidade.getIndicadorFotoObrigatoria() != null ) {
                	arquivoTextoRegistroTipo14.append(Util.formatarCampoParaConcatenacao(leituraAnormalidade.getIndicadorFotoObrigatoria()));
                } else {
                	arquivoTextoRegistroTipo14.append(Util.formatarCampoParaConcatenacao(2));
                }
                
               
                
                 if (count != 0) {
                    arquivoTextoRegistroTipo14.append(System.getProperty("line.separator"));
                } 
            }

        }

        return arquivoTextoRegistroTipo14;
    }
    
    /**
     * [UC0745] - Gerar Arquivo Texto Dividido para Faturamento
     * 
     * @author Hugo Leonardo
     * @date 01/06/2010
     * 
     * @param anoMesFaturamento
     * @param faturamentoGrupo
     * @param rota
     * @param imovel
     * @param qtdImoveis
     * @param arquivoTexto
     * @throws ControladorException
     */
    public ArquivoTextoRoteiroEmpresaDivisao inserirArquivoTextoRoteiroEmpresaDivisao(Integer anoMesFaturamento,
            FaturamentoGrupo faturamentoGrupo, Rota rota, Imovel imovel, Integer qtdImoveis, StringBuilder arquivoTexto)
                                                                                                                        throws ControladorException {

        ArquivoTextoRoteiroEmpresaDivisao arquivoTextoRoteiroEmpresaDivisao = new ArquivoTextoRoteiroEmpresaDivisao();

        // QUANTIDADE DE IMOVEIS
        arquivoTextoRoteiroEmpresaDivisao.setQuantidadeImovel(qtdImoveis);

        // NOME DO ARQUIVO

        // [FS0006] - Nome do arquivo texto
        String nomeArquivoTexto = "G" + Util.adicionarZerosEsquedaNumero(3, faturamentoGrupo.getId() + "")
                + Util.adicionarZerosEsquedaNumero(3, imovel.getLocalidade()
                                                            .getId() + "")
                + Util.adicionarZerosEsquedaNumero(3, imovel.getSetorComercial()
                                                            .getCodigo() + "")
                + Util.adicionarZerosEsquedaNumero(4, rota.getCodigo() + "")
                + Util.adicionarZerosEsquedaNumero(6, anoMesFaturamento + "");

        /*
         * [UC1360] Gerar Arquivo Texto para Faturamento Vers„o Android
         * [FS0006] Nome do arquivo texto
         */
        if (rota.getLeituraTipo().getId().equals(LeituraTipo.CELULAR_MOBILE_ANDROID) ||
        		rota.getLeituraTipo().getId().equals(LeituraTipo.LEITURA_ANDROID)){
        	nomeArquivoTexto += "A";
        }
        
        arquivoTextoRoteiroEmpresaDivisao.setNomeArquivo(nomeArquivoTexto + ".gz");

        // INFORMACOES LEITURISTA
        if (rota.getLeiturista() != null) {
            arquivoTextoRoteiroEmpresaDivisao.setLeiturista(rota.getLeiturista());
            arquivoTextoRoteiroEmpresaDivisao.setNumeroImei(rota.getLeiturista()
                                                                .getNumeroImei());

        }

        // ARQUIVO TEMPORaÅRIO GERADO PARA ROTA
        ByteArrayOutputStream baosArquivoZip = new ByteArrayOutputStream();

        GZIPOutputStream zos = null;
        BufferedWriter out = null;

        try {

            // arquivoTexto =
            // new StringBuilder( Util.reencodeString( arquivoTexto.toString(),
            // "UTF-8" ) );

            // Convertemos o StringBuilder em um vetor de array
            // arquivoTextoByte =
            // IoUtil.transformarObjetoParaBytes(arquivoTexto);

            File compactado = new File(nomeArquivoTexto + ".tar.gz");

            zos = new GZIPOutputStream(new FileOutputStream(compactado));
            File leitura = new File(nomeArquivoTexto + ".txt");

            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(leitura.getAbsolutePath())));
            out.write(arquivoTexto.toString());
            out.flush();
            ZipUtil.adicionarArquivo(zos, leitura);

            zos.close();

            FileInputStream inputStream = new FileInputStream(compactado);

            // Escrevemos aos poucos
            int INPUT_BUFFER_SIZE = 1024;
            byte[] temp = new byte[INPUT_BUFFER_SIZE];
            int numBytesRead = 0;

            while ((numBytesRead = inputStream.read(temp, 0, INPUT_BUFFER_SIZE)) != -1) {
                baosArquivoZip.write(temp, 0, numBytesRead);
            }

            arquivoTextoRoteiroEmpresaDivisao.setArquivoTexto(baosArquivoZip.toByteArray());

            // Fechamos o inputStream
            inputStream.close();
            baosArquivoZip.close();

            inputStream = null;
            compactado.delete();
            leitura.delete();
        } catch (IOException e) {
            sessionContext.setRollbackOnly();
            throw new ControladorException("erro.sistema",
                                           e);
        }

        // SITUACAO_TRANSMISSAO_LEITURA
        SituacaoTransmissaoLeitura situacaoTransmissaoLeitura = new SituacaoTransmissaoLeitura();
        situacaoTransmissaoLeitura.setId(SituacaoTransmissaoLeitura.DISPONIVEL);

        arquivoTextoRoteiroEmpresaDivisao.setSituacaoTransmissaoLeitura(situacaoTransmissaoLeitura);

        // ULTIMA ALTERACAO
        arquivoTextoRoteiroEmpresaDivisao.setUltimaAlteracao(new Date());

        // INSERINDO NA BASE
        // this.getControladorUtil().inserir(arquivoTextoRoteiroEmpresaDivisao);

        return arquivoTextoRoteiroEmpresaDivisao;
    }
    
    /**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * @author Raphael Rossiter
	 * @date 30/08/2011
	 * 
	 * @param idImovelMacro
	 * @param anoMesReferencia
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarMovimentoContaPrefaturadaArquivoTextoFaturamento(Integer idImovelMacro, Integer anoMesReferencia)
			throws ControladorException {
		
		Integer qtdMovimentoContaPrefaturada = null;
		
		try {
			
			qtdMovimentoContaPrefaturada = repositorioFaturamento.pesquisarMovimentoContaPrefaturadaArquivoTextoFaturamento(
											idImovelMacro, anoMesReferencia);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
				
		return qtdMovimentoContaPrefaturada;
	}
	
	
    
    
    /**
     * [UC1360] Gerar Arquivo Texto para Faturamento Vers„o Android
     * 
     * [SB0019] Qualidade da ¡gua
     * 
     * @author Amelia Pessoa
     * @date 19/07/2012
     *  
     */
    public StringBuilder gerarArquivoTextoRegistroTipo15Pipe(FaturamentoGrupo faturamentoGrupo,
    		SistemaParametro sistemaParametro) throws ControladorException {

        StringBuilder arquivoTextoRegistroTipo15 = new StringBuilder();

        //Qualidade aÅgua
        Integer anoMesReferenciaQualidadeAgua = null;
        if (sistemaParametro.getNomeEmpresa() != null && sistemaParametro.getNomeAbreviadoEmpresa()
                                                                         .equals(SistemaParametro.EMPRESA_COMPESA)) {
            anoMesReferenciaQualidadeAgua = Util.subtraiAteSeisMesesAnoMesReferencia(faturamentoGrupo.getAnoMesReferencia(), 1);
        } else {
            anoMesReferenciaQualidadeAgua = faturamentoGrupo.getAnoMesReferencia();
        }
        arquivoTextoRegistroTipo15 = arquivoTextoRegistroTipo15.append(
        		gerarArquivoTextoQualidadeAguaPipe(anoMesReferenciaQualidadeAgua));


        FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade(FiltroLeituraAnormalidade.ID);
        Collection colecaoLeituraAnormalidade = getControladorUtil().pesquisar(filtroLeituraAnormalidade, LeituraAnormalidade.class.getName());

        if (colecaoLeituraAnormalidade != null && !colecaoLeituraAnormalidade.isEmpty()) {

        }

        return arquivoTextoRegistroTipo15;
    }
    
    /**
     * [UC1360] Gerar Arquivo Texto para Faturamento Vers„o Android
     * 
     * [SB0020] Faturamento SituaÁ„o Tipo
     * 
     * @author Amelia Pessoa
     * @date 19/07/2012
     *  
     */
    public StringBuilder gerarArquivoTextoRegistroTipo16Pipe(FaturamentoGrupo faturamentoGrupo) throws ControladorException {

        StringBuilder arquivoTextoRegistroTipo16 = new StringBuilder();

        FiltroFaturamentoSituacaoTipo filtro = new FiltroFaturamentoSituacaoTipo();
    	filtro.adicionarParametro(new ParametroSimples(FiltroFaturamentoSituacaoTipo.INDICADOR_USO, ConstantesSistema.SIM));
    	Collection<FaturamentoSituacaoTipo> colecao = this.getControladorUtil()
    	.pesquisar(filtro, FaturamentoSituacaoTipo.class.getName());
    	
    	int count = colecao.size();
            
    	for (FaturamentoSituacaoTipo faturamentoSituacaoTipo : colecao){
    		
    		count--;
    		
    		//1. Tipo do Registro 
        	arquivoTextoRegistroTipo16.append(Util.formatarCampoParaConcatenacao("16"));
        	
    		// TIPO DA SITUACAO ESPECIAL DE FATURAMENTO
        	arquivoTextoRegistroTipo16.append(Util.formatarCampoParaConcatenacao(faturamentoSituacaoTipo.getId()));
        	
        	// ID DA LEITURA DE ANORMALIDADE DE CONSUMO SEM LEITURA
        	if (faturamentoSituacaoTipo.getLeituraAnormalidadeConsumoSemLeitura() != null
        			&& !faturamentoSituacaoTipo.getLeituraAnormalidadeConsumoSemLeitura()
        			.equals("")) {
        		arquivoTextoRegistroTipo16.append(Util.formatarCampoParaConcatenacao(faturamentoSituacaoTipo.getLeituraAnormalidadeConsumoSemLeitura()
        				.getId()));
        	} else {
        		arquivoTextoRegistroTipo16.append(Util.formatarCampoParaConcatenacao(null));        	
        	}
        	
        	// ID DA LEITURA DE ANORMALIDADE DE CONSUMO COM LEITURA
        	if (faturamentoSituacaoTipo.getLeituraAnormalidadeConsumoComLeitura() != null
	        	&& !faturamentoSituacaoTipo.getLeituraAnormalidadeConsumoComLeitura()
	        	.equals("")) {
	        	arquivoTextoRegistroTipo16.append(Util.formatarCampoParaConcatenacao(faturamentoSituacaoTipo.getLeituraAnormalidadeConsumoComLeitura()
	        	.getId()));
        	} else {
        		arquivoTextoRegistroTipo16.append(Util.formatarCampoParaConcatenacao(null));
        	
        	}
        	
        	// ID DA LEITURA DE ANORMALIDADE DE LEITURA SEM LEITURA
        	if (faturamentoSituacaoTipo.getLeituraAnormalidadeLeituraSemLeitura() != null
	        	&& !faturamentoSituacaoTipo.getLeituraAnormalidadeLeituraSemLeitura()
	        	.equals("")) {
	        	arquivoTextoRegistroTipo16.append(Util.formatarCampoParaConcatenacao(faturamentoSituacaoTipo.getLeituraAnormalidadeLeituraSemLeitura()
	        	.getId()));
        	} else {
        		arquivoTextoRegistroTipo16.append(Util.formatarCampoParaConcatenacao(null));
        	
        	}
        	
        	// ID DA LEITURA DE ANORMALIDADE DE LEITURA COM LEITURA
        	if (faturamentoSituacaoTipo.getLeituraAnormalidadeLeituraComLeitura() != null
	        	&& !faturamentoSituacaoTipo.getLeituraAnormalidadeLeituraComLeitura()
	        	.equals("")) {
	        	arquivoTextoRegistroTipo16.append(Util.formatarCampoParaConcatenacao(faturamentoSituacaoTipo.getLeituraAnormalidadeLeituraComLeitura()
	        	.getId()));
        	} else {
        		arquivoTextoRegistroTipo16.append(Util.formatarCampoParaConcatenacao(null));
        	
        	}
	    	
	    	// INDICADOR VALIDA AÅGUA
	    	arquivoTextoRegistroTipo16.append(Util.formatarCampoParaConcatenacao(faturamentoSituacaoTipo
	    	.getIndicadorValidoAgua()));
	    	
	    	// INDICADOR VALIDA ESGOTO
	    	arquivoTextoRegistroTipo16.append(Util.formatarCampoParaConcatenacao(faturamentoSituacaoTipo
	    	.getIndicadorValidoEsgoto()));
	    	
	    	//9. Indicador desconsiderar Estouro/Alto Consumo 
	    	arquivoTextoRegistroTipo16.append(Util.formatarCampoParaConcatenacao(2));
	    	
	    	if (count != 0) {
	    		arquivoTextoRegistroTipo16.append(System.getProperty("line.separator"));
            }	    	
    	}    	

        return arquivoTextoRegistroTipo16;
    }
    
    /**
     * [UC1360] Gerar Arquivo Texto para Faturamento Vers„o Android
     * 
     * [SB0021] Consumo Tipo
     * 
     * @author Amelia Pessoa
     * @date 19/07/2012
     *  
     */
    public StringBuilder gerarArquivoTextoRegistroTipo17Pipe() throws ControladorException {

        StringBuilder arquivoTextoRegistroTipo17 = new StringBuilder();

        FiltroConsumoTipo filtro = new FiltroConsumoTipo();
    	filtro.adicionarParametro(new ParametroSimples(FiltroConsumoTipo.INDICADOR_USO, ConstantesSistema.SIM));
    	Collection<ConsumoTipo> colecao = this.getControladorUtil()
    			.pesquisar(filtro, ConsumoTipo.class.getName());
    	
    	int count = colecao.size();
        	
    	for (ConsumoTipo consumoTipo : colecao){
    		
    		count--;
    		
    		//1. Tipo registro
    		arquivoTextoRegistroTipo17.append(Util.formatarCampoParaConcatenacao("17"));
    				
    		//2. Id do tipo de consumo
    		arquivoTextoRegistroTipo17.append(Util.formatarCampoParaConcatenacao(consumoTipo.getId()));
    						
    		//3. DescriÁ„o do tipo de consumo
    		arquivoTextoRegistroTipo17.append(Util.formatarCampoParaConcatenacao(consumoTipo.getDescricao()));
    			
    		if (count != 0) {
    			arquivoTextoRegistroTipo17.append(System.getProperty("line.separator"));
            }    		
    	}

        return arquivoTextoRegistroTipo17;
    }
    
    /**
     * MÈtodo respons·vel por<br>
     * gerar registro tipo 18<br>
     * referente a conta comunicado
     * @author Jonathan Marcos
     * @since 13/02/2015
     * @return  StringBuilder
     * @throws ControladorException
     */
    public StringBuilder gerarArquivoTextoRegistroTipo18Pipe() throws ControladorException {
        StringBuilder arquivoTextoRegistroTipo18 = new StringBuilder();
        for(int posicao = 0;posicao<listaContaComunicadoTipo18.size();posicao++){
        	// 1. Tipo registro
    		arquivoTextoRegistroTipo18.append(Util.formatarCampoParaConcatenacao("18"));
    		// 2. Id Conta Comunicado
    		arquivoTextoRegistroTipo18.append(Util.formatarCampoParaConcatenacao(
    				listaContaComunicadoTipo18.get(posicao).getId()));
    		// 3. Descricao Conta Comunicado
    		arquivoTextoRegistroTipo18.append(Util.formatarCampoParaConcatenacao(
    				listaContaComunicadoTipo18.get(posicao).getComunicado().replaceAll("\r\n", ContaComunicado.CONJUNTO_CARACTERE_ENTER)));
    		if(posicao+1!=listaContaComunicadoTipo18.size()){
    			// Pula Linha
        		arquivoTextoRegistroTipo18.append(System.getProperty("line.separator"));
    		}
        }
        return arquivoTextoRegistroTipo18;
    }
    
    /**
     * MÈtodo respons·vel por<br>
     * por verificar se a conta<br>
     * comuncado existe na lista
     * @author Jonathan Marcos
     * @since 21/02/2015
     * @param contaComunicado
     * @return boolean
     */
    private boolean verificarContaComunicadoLista(ContaComunicado contaComunicado){
    	for(ContaComunicado comuncado : listaContaComunicadoTipo18){
    		if(comuncado.getId().compareTo(contaComunicado.getId())==0){
    			return true;
    		}
    	}
    	return false;
    }
    
    private StringBuilder gerarDadosSituacaoEspecialFaturamentoPipe(Imovel imovel, FaturamentoGrupo faturamentoGrupo)
            throws ControladorException {

		StringBuilder arquivoTextoRegistro = new StringBuilder();
		
		if (imovel.getFaturamentoSituacaoTipo() != null && imovel.getFaturamentoSituacaoTipo()
				.getId() != null && !imovel.getFaturamentoSituacaoTipo()
				.getId().equals("")) {
		
			FiltroFaturamentoSituacaoHistorico filtroFaturamentoSituacaoHistorico = new FiltroFaturamentoSituacaoHistorico();
			filtroFaturamentoSituacaoHistorico.adicionarParametro(new ParametroSimples(FiltroFaturamentoSituacaoHistorico.ID_IMOVEL,
			imovel.getId()));
			filtroFaturamentoSituacaoHistorico.adicionarParametro(new ParametroNulo(FiltroFaturamentoSituacaoHistorico.ANO_MES_FATURAMENTO_RETIRADA));
			Collection<FaturamentoSituacaoHistorico> colFiltroFaturamentoSituacaoHistorico = this.getControladorUtil()
			.pesquisar(filtroFaturamentoSituacaoHistorico, FaturamentoSituacaoHistorico.class.getName());
			
			FaturamentoSituacaoHistorico faturamentoSituacaoHistorico = (FaturamentoSituacaoHistorico) Util.retonarObjetoDeColecao(colFiltroFaturamentoSituacaoHistorico);
			
			// Verificamos se anomesreferencia do grupo que esta sendo faturado
			// esta entre o os meses inicial e final do
			// FATURAMENTO_SITUACAO_HISTORICO
			if (faturamentoSituacaoHistorico != null
					&& faturamentoGrupo != null
					&& faturamentoGrupo.getAnoMesReferencia() >= faturamentoSituacaoHistorico.getAnoMesFaturamentoSituacaoInicio()
					&& faturamentoGrupo.getAnoMesReferencia() <= faturamentoSituacaoHistorico.getAnoMesFaturamentoSituacaoFim()) {
			
				Collection colecaoFaturamentoSituacaoTipo = null;
				
				// Pesquisa a anormalidade de leitura de faturamento
				colecaoFaturamentoSituacaoTipo = getControladorMicromedicao().pesquisarFaturamentoSituacaoTipo(imovel.getFaturamentoSituacaoTipo());
				
				//53. Tipo da situaÁ„o especial de faturamento				
				arquivoTextoRegistro.append(Util.formatarCampoParaConcatenacao(imovel.getFaturamentoSituacaoTipo().getId()));
				
				// Obtem a leitura anormalidade
				FaturamentoSituacaoTipo faturamentoSituacaoTipo = getControladorMicromedicao().obterFaturamentoSituacaoTipo(colecaoFaturamentoSituacaoTipo);
				
				
				//54. CONSUMO AÅGUA MEDIDO DO HISTORICO DE FATURAMENTO
				if (faturamentoSituacaoHistorico.getNumeroConsumoAguaMedido() != null
						&& !faturamentoSituacaoHistorico.getNumeroConsumoAguaMedido()
						.equals("")) {
					arquivoTextoRegistro.append(Util.formatarCampoParaConcatenacao(faturamentoSituacaoHistorico.getNumeroConsumoAguaMedido()));
				} else {
					arquivoTextoRegistro.append(Util.formatarCampoParaConcatenacao(null));				
				}
				
				//55. CONSUMO AGUA NAO MEDIDO DO HISTORICO DE FATURAMENTO
				if (faturamentoSituacaoHistorico.getNumeroConsumoAguaNaoMedido() != null
						&& !faturamentoSituacaoHistorico.getNumeroConsumoAguaNaoMedido()
						.equals("")) {
					arquivoTextoRegistro.append(Util.formatarCampoParaConcatenacao(faturamentoSituacaoHistorico.getNumeroConsumoAguaNaoMedido()));
				} else {
					arquivoTextoRegistro.append(Util.formatarCampoParaConcatenacao(null));				
				}
				
				//56. VOLUME ESGOTO MEDIDO DO HISTORICO DE FATURAMENTO
				if (faturamentoSituacaoHistorico.getNumeroVolumeEsgotoMedido() != null
						&& !faturamentoSituacaoHistorico.getNumeroVolumeEsgotoMedido()
						.equals("")) {
					arquivoTextoRegistro.append(Util.formatarCampoParaConcatenacao(faturamentoSituacaoHistorico.getNumeroVolumeEsgotoMedido()));
				} else {
					arquivoTextoRegistro.append(Util.formatarCampoParaConcatenacao(null));				
				}
				
				//57. VOLUME ESGOTO NAO MEDIDO DO HISTORICO DE FATURAMENTO
				if (faturamentoSituacaoHistorico.getNumeroVolumeEsgotoNaoMedido() != null
						&& !faturamentoSituacaoHistorico.getNumeroVolumeEsgotoNaoMedido()
						.equals("")) {
					arquivoTextoRegistro.append(Util.formatarCampoParaConcatenacao(faturamentoSituacaoHistorico.getNumeroVolumeEsgotoNaoMedido()));
				} else {
					arquivoTextoRegistro.append(Util.formatarCampoParaConcatenacao(null));			
				}		
			} else {
				//53 a 57
				arquivoTextoRegistro.append(Util.formatarCampoParaConcatenacao(null));	
				arquivoTextoRegistro.append(Util.formatarCampoParaConcatenacao(null));	
				arquivoTextoRegistro.append(Util.formatarCampoParaConcatenacao(null));	
				arquivoTextoRegistro.append(Util.formatarCampoParaConcatenacao(null));	
				arquivoTextoRegistro.append(Util.formatarCampoParaConcatenacao(null));
			}
		} else {
			//53 a 57
			arquivoTextoRegistro.append(Util.formatarCampoParaConcatenacao(null));	
			arquivoTextoRegistro.append(Util.formatarCampoParaConcatenacao(null));	
			arquivoTextoRegistro.append(Util.formatarCampoParaConcatenacao(null));	
			arquivoTextoRegistro.append(Util.formatarCampoParaConcatenacao(null));	
			arquivoTextoRegistro.append(Util.formatarCampoParaConcatenacao(null));	
		}
		
		return arquivoTextoRegistro;
	}
}