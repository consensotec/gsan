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
* Anderson Italo Felinto de Lima
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
package gsan.cobranca;

import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.cliente.ClienteRelacaoTipo;
import gsan.cadastro.empresa.Empresa;
import gsan.cadastro.endereco.Logradouro;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.localidade.GerenciaRegional;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.UnidadeNegocio;
import gsan.faturamento.FaturamentoAtividadeCronograma;
import gsan.interceptor.ControleAlteracao;
import gsan.interceptor.ObjetoTransacao;
import gsan.micromedicao.Rota;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.filtro.Filtro;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class CobrancaAcaoAtividadeComando extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	
	public static final int ATRIBUTOS_CANCELAR_DOCUMENTOS_COBRANCA = 1472; 	

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Date comando;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade=ATRIBUTOS_CANCELAR_DOCUMENTOS_COBRANCA)
    private Date realizacao;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private Integer codigoSetorComercialInicial;

    /** nullable persistent field */
    private Integer codigoSetorComercialFinal;

    /** nullable persistent field */
    private Integer anoMesReferenciaContaInicial;

    /** nullable persistent field */
    private Integer anoMesReferenciaContaFinal;

    /** nullable persistent field */
    private Date dataVencimentoContaInicial;

    /** nullable persistent field */
    private Date dataVencimentoContaFinal;

    /** nullable persistent field */
    private Short indicadorCriterio;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade=ATRIBUTOS_CANCELAR_DOCUMENTOS_COBRANCA)
    private Integer quantidadeDocumentos;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade=ATRIBUTOS_CANCELAR_DOCUMENTOS_COBRANCA)
    private BigDecimal valorDocumentos;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade=ATRIBUTOS_CANCELAR_DOCUMENTOS_COBRANCA)
    private Integer quantidadeItensCobrados;

    /** persistent field */
    private Rota rotaFinal;

    /** persistent field */
    private Rota rotaInicial;

    /** persistent field */
    private gsan.cobranca.CobrancaCriterio cobrancaCriterio;

    /** persistent field */
    private Cliente cliente;

    /** persistent field */
    private gsan.cobranca.CobrancaAcao cobrancaAcao;

    /** persistent field */
    private Usuario usuario;

    /** persistent field */
    private Empresa empresa;

    /** persistent field */
    private ClienteRelacaoTipo clienteRelacaoTipo;

    /** persistent field */
    private gsan.cobranca.CobrancaAtividade cobrancaAtividade;

    /** persistent field */
    private GerenciaRegional gerenciaRegional;

    /** persistent field */
    private Localidade localidadeInicial;

    /** persistent field */
    private Localidade localidadeFinal;

    /** persistent field */
    private gsan.cobranca.CobrancaGrupo cobrancaGrupo;
    
	/** persistent field */
	private UnidadeNegocio unidadeNegocio;
	
	/** persistent field */
	private String descricaoTitulo;
	
	/** persistent field */
	private String descricaoSolicitacao;
	
	/** persistent field */
	private Date dataEncerramentoPrevista;
	
	/** persistent field */
	private Date dataEncerramentoRealizada;
	
	/** persistent field */
	private Short quantidadeDiasRealizacao;
	
	/** persistent field */
	private Short indicadorDebito;
	
	/** persistent field */
	private Short indicadorBoletim;
	
	/** persistent field */
	private Integer quantidadeMaximaDocumentos;
	
	/** persistent field */
	private Logradouro logradouro;
	
	/** persistent field */
	private Cliente superior;
	
	/** persistent field */
	private BigDecimal valorLimiteObrigatoria;

	/** persistent field */
	private Integer consumoMedioInicial;
	
	/** persistent field */
	private Integer consumoMedioFinal;
	
	/** persistent field */
	private Short tipoConsumo;
	
	/** persistent field */
	private Date periodoInicialFiscalizacao;
	
	/** persistent field */
	private Date periodoFinalFiscalizacao;
	
	private Integer numeroQuadraInicial;
	
	private Integer numeroQuadraFinal;
	
	private Integer quantidadeDiasVencimento;
	
	private FaturamentoAtividadeCronograma faturamentoAtividadeCronograma;
	
	private Imovel imovel;
	
	private String nomeArquivoRelacaoImoveis;
	
	private Integer quantidadeSmsGerados;
	private Integer quantidadeSmsEnviados;
	private Integer quantidadeEmailGerados;
	private Integer quantidadeEmailEnviados;
	
	public FaturamentoAtividadeCronograma getFaturamentoAtividadeCronograma() {
		return faturamentoAtividadeCronograma;
	}

	public void setFaturamentoAtividadeCronograma(FaturamentoAtividadeCronograma faturamentoAtividadeCronograma) {
		this.faturamentoAtividadeCronograma = faturamentoAtividadeCronograma;
	}

	public static final Short INDICADOR_BOLETIM_SIM = 1;
	
	public static final Short INDICADOR_DEBITO_NAO = 2;
	
	public static final Short INDICADOR_DEBITO_SIM = 1;

	

    /** full constructor */
    public CobrancaAcaoAtividadeComando(Date comando, 
    	Date realizacao, Date ultimaAlteracao, Integer codigoSetorComercialInicial, Integer codigoSetorComercialFinal, 
    	Integer anoMesReferenciaContaInicial, Integer anoMesReferenciaContaFinal, Date dataVencimentoContaInicial, 
    	Date dataVencimentoContaFinal, Short indicadorCriterio, Integer quantidadeDocumentos, BigDecimal valorDocumentos, 
    	Integer quantidadeItensCobrados, Rota rotaFinal, Rota rotaInicial, gsan.cobranca.CobrancaCriterio cobrancaCriterio, 
    	Cliente cliente, gsan.cobranca.CobrancaAcao cobrancaAcao, Usuario usuario, Empresa empresa, ClienteRelacaoTipo clienteRelacaoTipo, 
    	gsan.cobranca.CobrancaAtividade cobrancaAtividade, GerenciaRegional gerenciaRegional, Localidade localidadeInicial, Localidade localidadeFinal, 
    	gsan.cobranca.CobrancaGrupo cobrancaGrupo) {
        this.comando = comando;
        this.realizacao = realizacao;
        this.ultimaAlteracao = ultimaAlteracao;
        this.codigoSetorComercialInicial = codigoSetorComercialInicial;
        this.codigoSetorComercialFinal = codigoSetorComercialFinal;
        this.anoMesReferenciaContaInicial = anoMesReferenciaContaInicial;
        this.anoMesReferenciaContaFinal = anoMesReferenciaContaFinal;
        this.dataVencimentoContaInicial = dataVencimentoContaInicial;
        this.dataVencimentoContaFinal = dataVencimentoContaFinal;
        this.indicadorCriterio = indicadorCriterio;
        this.quantidadeDocumentos = quantidadeDocumentos;
        this.valorDocumentos = valorDocumentos;
        this.quantidadeItensCobrados = quantidadeItensCobrados;
        this.rotaFinal = rotaFinal;
        this.rotaInicial = rotaInicial;
        this.cobrancaCriterio = cobrancaCriterio;
        this.cliente = cliente;
        this.cobrancaAcao = cobrancaAcao;
        this.usuario = usuario;
        this.empresa = empresa;
        this.clienteRelacaoTipo = clienteRelacaoTipo;
        this.cobrancaAtividade = cobrancaAtividade;
        this.gerenciaRegional = gerenciaRegional;
        this.localidadeInicial = localidadeInicial;
        this.localidadeFinal = localidadeFinal;
        this.cobrancaGrupo = cobrancaGrupo;
    }

    /** default constructor */
    public CobrancaAcaoAtividadeComando() {
    }

    /** minimal constructor */
    public CobrancaAcaoAtividadeComando(Rota rotaFinal, Rota rotaInicial, gsan.cobranca.CobrancaCriterio cobrancaCriterio, Cliente cliente, gsan.cobranca.CobrancaAcao cobrancaAcao, Usuario usuario, Empresa empresa, ClienteRelacaoTipo clienteRelacaoTipo, gsan.cobranca.CobrancaAtividade cobrancaAtividade, GerenciaRegional gerenciaRegional, Localidade localidadeInicial, Localidade localidadeFinal, gsan.cobranca.CobrancaGrupo cobrancaGrupo) {
        this.rotaFinal = rotaFinal;
        this.rotaInicial = rotaInicial;
        this.cobrancaCriterio = cobrancaCriterio;
        this.cliente = cliente;
        this.cobrancaAcao = cobrancaAcao;
        this.usuario = usuario;
        this.empresa = empresa;
        this.clienteRelacaoTipo = clienteRelacaoTipo;
        this.cobrancaAtividade = cobrancaAtividade;
        this.gerenciaRegional = gerenciaRegional;
        this.localidadeInicial = localidadeInicial;
        this.localidadeFinal = localidadeFinal;
        this.cobrancaGrupo = cobrancaGrupo;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getComando() {
        return this.comando;
    }

    public void setComando(Date comando) {
        this.comando = comando;
    }

    public Date getRealizacao() {
        return this.realizacao;
    }

    public void setRealizacao(Date realizacao) {
        this.realizacao = realizacao;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Integer getCodigoSetorComercialInicial() {
        return this.codigoSetorComercialInicial;
    }

    public void setCodigoSetorComercialInicial(Integer codigoSetorComercialInicial) {
        this.codigoSetorComercialInicial = codigoSetorComercialInicial;
    }

    public Integer getCodigoSetorComercialFinal() {
        return this.codigoSetorComercialFinal;
    }

    public void setCodigoSetorComercialFinal(Integer codigoSetorComercialFinal) {
        this.codigoSetorComercialFinal = codigoSetorComercialFinal;
    }

    public Integer getAnoMesReferenciaContaInicial() {
        return this.anoMesReferenciaContaInicial;
    }

    public void setAnoMesReferenciaContaInicial(Integer anoMesReferenciaContaInicial) {
        this.anoMesReferenciaContaInicial = anoMesReferenciaContaInicial;
    }

    public Integer getAnoMesReferenciaContaFinal() {
        return this.anoMesReferenciaContaFinal;
    }

    public void setAnoMesReferenciaContaFinal(Integer anoMesReferenciaContaFinal) {
        this.anoMesReferenciaContaFinal = anoMesReferenciaContaFinal;
    }

    public Date getDataVencimentoContaInicial() {
        return this.dataVencimentoContaInicial;
    }

    public void setDataVencimentoContaInicial(Date dataVencimentoContaInicial) {
        this.dataVencimentoContaInicial = dataVencimentoContaInicial;
    }

    public Date getDataVencimentoContaFinal() {
        return this.dataVencimentoContaFinal;
    }

    public void setDataVencimentoContaFinal(Date dataVencimentoContaFinal) {
        this.dataVencimentoContaFinal = dataVencimentoContaFinal;
    }

    public Short getIndicadorCriterio() {
        return this.indicadorCriterio;
    }

    public void setIndicadorCriterio(Short indicadorCriterio) {
        this.indicadorCriterio = indicadorCriterio;
    }

    public Integer getQuantidadeDocumentos() {
        return this.quantidadeDocumentos;
    }

    public void setQuantidadeDocumentos(Integer quantidadeDocumentos) {
        this.quantidadeDocumentos = quantidadeDocumentos;
    }

    public BigDecimal getValorDocumentos() {
        return this.valorDocumentos;
    }

    public void setValorDocumentos(BigDecimal valorDocumentos) {
        this.valorDocumentos = valorDocumentos;
    }

    public Integer getQuantidadeItensCobrados() {
        return this.quantidadeItensCobrados;
    }

    public void setQuantidadeItensCobrados(Integer quantidadeItensCobrados) {
        this.quantidadeItensCobrados = quantidadeItensCobrados;
    }

    public Rota getRotaFinal() {
        return this.rotaFinal;
    }

    public void setRotaFinal(Rota rotaFinal) {
        this.rotaFinal = rotaFinal;
    }

    public Rota getRotaInicial() {
        return this.rotaInicial;
    }

    public void setRotaInicial(Rota rotaInicial) {
        this.rotaInicial = rotaInicial;
    }

    public gsan.cobranca.CobrancaCriterio getCobrancaCriterio() {
        return this.cobrancaCriterio;
    }

    public void setCobrancaCriterio(gsan.cobranca.CobrancaCriterio cobrancaCriterio) {
        this.cobrancaCriterio = cobrancaCriterio;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public gsan.cobranca.CobrancaAcao getCobrancaAcao() {
        return this.cobrancaAcao;
    }

    public void setCobrancaAcao(gsan.cobranca.CobrancaAcao cobrancaAcao) {
        this.cobrancaAcao = cobrancaAcao;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Empresa getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public ClienteRelacaoTipo getClienteRelacaoTipo() {
        return this.clienteRelacaoTipo;
    }

    public void setClienteRelacaoTipo(ClienteRelacaoTipo clienteRelacaoTipo) {
        this.clienteRelacaoTipo = clienteRelacaoTipo;
    }

    public gsan.cobranca.CobrancaAtividade getCobrancaAtividade() {
        return this.cobrancaAtividade;
    }

    public void setCobrancaAtividade(gsan.cobranca.CobrancaAtividade cobrancaAtividade) {
        this.cobrancaAtividade = cobrancaAtividade;
    }

    public GerenciaRegional getGerenciaRegional() {
        return this.gerenciaRegional;
    }

    public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
        this.gerenciaRegional = gerenciaRegional;
    }

    public Localidade getLocalidadeInicial() {
        return this.localidadeInicial;
    }

    public void setLocalidadeInicial(Localidade localidadeInicial) {
        this.localidadeInicial = localidadeInicial;
    }

    public Localidade getLocalidadeFinal() {
        return this.localidadeFinal;
    }

    public void setLocalidadeFinal(Localidade localidadeFinal) {
        this.localidadeFinal = localidadeFinal;
    }

    public gsan.cobranca.CobrancaGrupo getCobrancaGrupo() {
        return this.cobrancaGrupo;
    }

    public void setCobrancaGrupo(gsan.cobranca.CobrancaGrupo cobrancaGrupo) {
        this.cobrancaGrupo = cobrancaGrupo;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
	
	
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}    
    
	public Filtro retornaFiltro(){
		FiltroCobrancaAcaoAtividadeComando filtroCobrancaAcaoAtividadeComando = new FiltroCobrancaAcaoAtividadeComando();
		
		filtroCobrancaAcaoAtividadeComando.adicionarCaminhoParaCarregamentoEntidade("rotaFinal");
		filtroCobrancaAcaoAtividadeComando.adicionarCaminhoParaCarregamentoEntidade("cobrancaCriterio");
		filtroCobrancaAcaoAtividadeComando.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtroCobrancaAcaoAtividadeComando.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcao");
		filtroCobrancaAcaoAtividadeComando.adicionarCaminhoParaCarregamentoEntidade("usuario");
		filtroCobrancaAcaoAtividadeComando.adicionarCaminhoParaCarregamentoEntidade("empresa");
		filtroCobrancaAcaoAtividadeComando.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");
		filtroCobrancaAcaoAtividadeComando.adicionarCaminhoParaCarregamentoEntidade("cobrancaAtividade");
		filtroCobrancaAcaoAtividadeComando.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
		filtroCobrancaAcaoAtividadeComando.adicionarCaminhoParaCarregamentoEntidade("localidadeInicial");
		filtroCobrancaAcaoAtividadeComando.adicionarCaminhoParaCarregamentoEntidade("cobrancaGrupo");
		filtroCobrancaAcaoAtividadeComando.adicionarParametro(
						new ParametroSimples(FiltroCobrancaAcaoAtividadeComando.ID, this.getId()));
				
		return filtroCobrancaAcaoAtividadeComando; 
	}

	/**
	 * @return Retorna o campo unidadeNegocio.
	 */
	public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}

	/**
	 * @param unidadeNegocio O unidadeNegocio a ser setado.
	 */
	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public Date getDataEncerramentoPrevista() {
		return dataEncerramentoPrevista;
	}

	public void setDataEncerramentoPrevista(Date dataEncerramentoPrevista) {
		this.dataEncerramentoPrevista = dataEncerramentoPrevista;
	}

	public Date getDataEncerramentoRealizada() {
		return dataEncerramentoRealizada;
	}

	public void setDataEncerramentoRealizada(Date dataEncerramentoRealizada) {
		this.dataEncerramentoRealizada = dataEncerramentoRealizada;
	}

	public String getDescricaoSolicitacao() {
		return descricaoSolicitacao;
	}

	public void setDescricaoSolicitacao(String descricaoSolicitacao) {
		this.descricaoSolicitacao = descricaoSolicitacao;
	}

	public String getDescricaoTitulo() {
		return descricaoTitulo;
	}

	public void setDescricaoTitulo(String descricaoTitulo) {
		this.descricaoTitulo = descricaoTitulo;
	}

	public Short getIndicadorBoletim() {
		return indicadorBoletim;
	}

	public void setIndicadorBoletim(Short indicadorBoletim) {
		this.indicadorBoletim = indicadorBoletim;
	}

	public Short getIndicadorDebito() {
		return indicadorDebito;
	}

	public void setIndicadorDebito(Short indicadorDebito) {
		this.indicadorDebito = indicadorDebito;
	}

	public Short getQuantidadeDiasRealizacao() {
		return quantidadeDiasRealizacao;
	}

	public void setQuantidadeDiasRealizacao(Short quantidadeDiasRealizacao) {
		this.quantidadeDiasRealizacao = quantidadeDiasRealizacao;
	}

	public Integer getQuantidadeMaximaDocumentos() {
		return quantidadeMaximaDocumentos;
	}

	public void setQuantidadeMaximaDocumentos(Integer quantidadeMaximaDocumentos) {
		this.quantidadeMaximaDocumentos = quantidadeMaximaDocumentos;
	}

	public Cliente getSuperior() {
		return superior;
	}

	public void setSuperior(Cliente superior) {
		this.superior = superior;
	}

	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []labels = {"cobrancaAcao.descricaoCobrancaAcao", "descricaoTitulo"};
		return labels;		
	}
	
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []labels = {"Acao de cobranca", "Descricao"};
		return labels;		
	}

	/**
	 * @return Retorna o campo valorLimiteObrigatoria.
	 */
	public BigDecimal getValorLimiteObrigatoria() {
		return valorLimiteObrigatoria;
	}

	/**
	 * @param valorLimiteObrigatoria O valorLimiteObrigatoria a ser setado.
	 */
	public void setValorLimiteObrigatoria(
			BigDecimal valorLimiteObrigatoria) {
		this.valorLimiteObrigatoria = valorLimiteObrigatoria;
	}

	public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getConsumoMedioInicial() {
		return consumoMedioInicial;
	}

	public void setConsumoMedioInicial(Integer consumoMedioInicial) {
		this.consumoMedioInicial = consumoMedioInicial;
	}

	public Integer getConsumoMedioFinal() {
		return consumoMedioFinal;
	}

	public void setConsumoMedioFinal(Integer consumoMedioFinal) {
		this.consumoMedioFinal = consumoMedioFinal;
	}

	public Short getTipoConsumo() {
		return tipoConsumo;
	}

	public void setTipoConsumo(Short tipoConsumo) {
		this.tipoConsumo = tipoConsumo;
	}

	public Date getPeriodoInicialFiscalizacao() {
		return periodoInicialFiscalizacao;
	}

	public void setPeriodoInicialFiscalizacao(Date periodoInicialFiscalizacao) {
		this.periodoInicialFiscalizacao = periodoInicialFiscalizacao;
	}

	public Date getPeriodoFinalFiscalizacao() {
		return periodoFinalFiscalizacao;
	}

	public void setPeriodoFinalFiscalizacao(Date periodoFinalFiscalizacao) {
		this.periodoFinalFiscalizacao = periodoFinalFiscalizacao;
	}

	public Integer getNumeroQuadraFinal() {
		return numeroQuadraFinal;
	}

	public void setNumeroQuadraFinal(Integer numeroQuadraFinal) {
		this.numeroQuadraFinal = numeroQuadraFinal;
	}

	public Integer getNumeroQuadraInicial() {
		return numeroQuadraInicial;
	}

	public void setNumeroQuadraInicial(Integer numeroQuadraInicial) {
		this.numeroQuadraInicial = numeroQuadraInicial;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}
	
	public Integer getQuantidadeDiasVencimento() {
		return quantidadeDiasVencimento;
	}

	public void setQuantidadeDiasVencimento(Integer quantidadeDiasVencimento) {
		this.quantidadeDiasVencimento = quantidadeDiasVencimento;
	}
	
	public String getNomeArquivoRelacaoImoveis() {
		return nomeArquivoRelacaoImoveis;
	}

	public void setNomeArquivoRelacaoImoveis(String nomeArquivoRelacaoImoveis) {
		this.nomeArquivoRelacaoImoveis = nomeArquivoRelacaoImoveis;
	}

	public Integer getQuantidadeSmsGerados() {
		return quantidadeSmsGerados;
	}

	public void setQuantidadeSmsGerados(Integer quantidadeSmsGerados) {
		this.quantidadeSmsGerados = quantidadeSmsGerados;
	}

	public Integer getQuantidadeSmsEnviados() {
		return quantidadeSmsEnviados;
	}

	public void setQuantidadeSmsEnviados(Integer quantidadeSmsEnviados) {
		this.quantidadeSmsEnviados = quantidadeSmsEnviados;
	}

	public Integer getQuantidadeEmailGerados() {
		return quantidadeEmailGerados;
	}

	public void setQuantidadeEmailGerados(Integer quantidadeEmailGerados) {
		this.quantidadeEmailGerados = quantidadeEmailGerados;
	}

	public Integer getQuantidadeEmailEnviados() {
		return quantidadeEmailEnviados;
	}

	public void setQuantidadeEmailEnviados(Integer quantidadeEmailEnviados) {
		this.quantidadeEmailEnviados = quantidadeEmailEnviados;
	}
}
