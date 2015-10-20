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

package gcom.atendimentopublico.ordemservico;

import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.cadastro.unidade.UnidadeOrganizacional;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class OrdemServicoMovimentoHistorico implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private Date dataGeracao;

    /** nullable persistent field */
    private String loginUsuario;

    /** persistent field */
    private Date dataTramite;

    /** nullable persistent field */
    private Date dataExecucao;

    /** nullable persistent field */
    private String inscricaoImovel;

    /** nullable persistent field */
    private String nomeSolicitante;

    /** nullable persistent field */
    private String telefoneSolicitante;

    /** nullable persistent field */
    private String endereco;

    /** nullable persistent field */
    private String pontoReferencia;

    /** nullable persistent field */
    private String bairro;

    /** nullable persistent field */
    private String nomeLocalidade;

    /** nullable persistent field */
    private Integer codigoElo;

    /** nullable persistent field */
    private Integer codigoSetor;

    /** nullable persistent field */
    private Integer numeroQuadra;

    /** nullable persistent field */
    private String observacao;

    /** persistent field */
    private String parecer;

    /** nullable persistent field */
    private BigDecimal areaPavimentoRua;

    /** nullable persistent field */
    private BigDecimal areaPavimentoCalcada;

    /** persistent field */
    private short indicadorMovimento;
    
    /** persistent field */
    private UnidadeOrganizacional unidadeOrganizacionalExecutora;
    
    /** persistent field */
    private UnidadeOrganizacional unidadeOrganizacionalCentralizadora;
    
    /** persistent field */
    private ServicoTipo servicoTipo;
    
    /** persistent field */
    private RegistroAtendimento registroAtendimento;
    
    /** persistent field */
    private Imovel imovel;
    
    /** persistent field */
    private ServicoTipoPrioridade servicoTipoPrioridadeAtual;
    
    /** persistent field */
    private PavimentoRua pavimentoRua;
    
    /** persistent field */
    private PavimentoCalcada pavimentoCalcada;
    
    private AtendimentoMotivoEncerramento atendimentoMotivoEncerramento;
    
    private Date dataHoraGeracaoHistorico;
    
 
public OrdemServicoMovimentoHistorico(Integer id, Date dataGeracao, String loginUsuario, Date dataTramite, Date dataExecucao, String inscricaoImovel, String nomeSolicitante, String telefoneSolicitante, String endereco, String pontoReferencia, String bairro, String nomeLocalidade, Integer codigoElo, Integer codigoSetor, Integer numeroQuadra, String observacao, String parecer, BigDecimal areaPavimentoRua, BigDecimal areaPavimentoCalcada, short indicadorMovimento, UnidadeOrganizacional unidadeOrganizacionalExecutora, UnidadeOrganizacional unidadeOrganizacionalCentralizadora, ServicoTipo servicoTipo, RegistroAtendimento registroAtendimento, Imovel imovel, ServicoTipoPrioridade servicoTipoPrioridadeAtual, PavimentoRua pavimentoRua, PavimentoCalcada pavimentoCalcada) {
		this.id = id;
		this.dataGeracao = dataGeracao;
		this.loginUsuario = loginUsuario;
		this.dataTramite = dataTramite;
		this.dataExecucao = dataExecucao;
		this.inscricaoImovel = inscricaoImovel;
		this.nomeSolicitante = nomeSolicitante;
		this.telefoneSolicitante = telefoneSolicitante;
		this.endereco = endereco;
		this.pontoReferencia = pontoReferencia;
		this.bairro = bairro;
		this.nomeLocalidade = nomeLocalidade;
		this.codigoElo = codigoElo;
		this.codigoSetor = codigoSetor;
		this.numeroQuadra = numeroQuadra;
		this.observacao = observacao;
		this.parecer = parecer;
		this.areaPavimentoRua = areaPavimentoRua;
		this.areaPavimentoCalcada = areaPavimentoCalcada;
		this.indicadorMovimento = indicadorMovimento;
		this.unidadeOrganizacionalExecutora = unidadeOrganizacionalExecutora;
		this.unidadeOrganizacionalCentralizadora = unidadeOrganizacionalCentralizadora;
		this.servicoTipo = servicoTipo;
		this.registroAtendimento = registroAtendimento;
		this.imovel = imovel;
		this.servicoTipoPrioridadeAtual = servicoTipoPrioridadeAtual;
		this.pavimentoRua = pavimentoRua;
		this.pavimentoCalcada = pavimentoCalcada;
	}

	public OrdemServicoMovimentoHistorico(OrdemServicoMovimento ordemServicoMovimento) {
		
		this.id = ordemServicoMovimento.getId();
		this.dataGeracao = ordemServicoMovimento.getDataGeracao();
		this.loginUsuario = ordemServicoMovimento.getLoginUsuario();
		this.dataTramite = ordemServicoMovimento.getDataTramite();
		this.dataExecucao = ordemServicoMovimento.getDataExecucao();
		this.inscricaoImovel = ordemServicoMovimento.getInscricaoImovel();
		this.nomeSolicitante = ordemServicoMovimento.getNomeSolicitante();
		this.telefoneSolicitante = ordemServicoMovimento.getTelefoneSolicitante();
		this.endereco = ordemServicoMovimento.getEndereco();
		this.pontoReferencia = ordemServicoMovimento.getPontoReferencia();
		this.bairro = ordemServicoMovimento.getBairro();
		this.nomeLocalidade = ordemServicoMovimento.getNomeLocalidade();
		this.codigoElo = ordemServicoMovimento.getCodigoElo();
		this.codigoSetor = ordemServicoMovimento.getCodigoSetor();
		this.numeroQuadra = ordemServicoMovimento.getNumeroQuadra();
		this.observacao = ordemServicoMovimento.getObservacao();
		this.parecer = ordemServicoMovimento.getParecer();
		this.areaPavimentoRua = ordemServicoMovimento.getAreaPavimentoRua();
		this.areaPavimentoCalcada = ordemServicoMovimento.getAreaPavimentoCalcada();
		this.indicadorMovimento = ordemServicoMovimento.getIndicadorMovimento();
		this.unidadeOrganizacionalExecutora = ordemServicoMovimento.getUnidadeOrganizacionalExecutora();
		this.unidadeOrganizacionalCentralizadora = ordemServicoMovimento.getUnidadeOrganizacionalCentralizadora();
		this.servicoTipo = ordemServicoMovimento.getServicoTipo();
		this.registroAtendimento = ordemServicoMovimento.getRegistroAtendimento();
		this.imovel = ordemServicoMovimento.getImovel();
		this.servicoTipoPrioridadeAtual = ordemServicoMovimento.getServicoTipoPrioridadeAtual();
		this.pavimentoRua = ordemServicoMovimento.getPavimentoRua();
		this.pavimentoCalcada = ordemServicoMovimento.getPavimentoCalcada();
		this.dataHoraGeracaoHistorico = new Date();
	}


    /** default constructor */
    public OrdemServicoMovimentoHistorico() {
    }

    /** minimal constructor */
    public OrdemServicoMovimentoHistorico(Integer id, Date dataGeracao, Date dataTramite, String parecer, short indicadorMovimento, UnidadeOrganizacional unidadeOrganizacionalExecutora, UnidadeOrganizacional unidadeOrganizacionalCentralizadora, ServicoTipo servicoTipo, RegistroAtendimento registroAtendimento, ServicoTipoPrioridade servicoTipoPrioridadeAtual) {
        this.id = id;
        this.dataGeracao = dataGeracao;
        this.dataTramite = dataTramite;
        this.parecer = parecer;
        this.indicadorMovimento = indicadorMovimento;
        this.unidadeOrganizacionalExecutora = unidadeOrganizacionalExecutora;
        this.unidadeOrganizacionalCentralizadora = unidadeOrganizacionalCentralizadora;
        this.servicoTipo = servicoTipo;
        this.registroAtendimento = registroAtendimento;
        this.servicoTipoPrioridadeAtual = servicoTipoPrioridadeAtual;
    }

 

    public BigDecimal getAreaPavimentoCalcada() {
		return areaPavimentoCalcada;
	}

	public void setAreaPavimentoCalcada(BigDecimal areaPavimentoCalcada) {
		this.areaPavimentoCalcada = areaPavimentoCalcada;
	}

	public BigDecimal getAreaPavimentoRua() {
		return areaPavimentoRua;
	}

	public void setAreaPavimentoRua(BigDecimal areaPavimentoRua) {
		this.areaPavimentoRua = areaPavimentoRua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Integer getCodigoElo() {
		return codigoElo;
	}

	public void setCodigoElo(Integer codigoElo) {
		this.codigoElo = codigoElo;
	}

	public Integer getCodigoSetor() {
		return codigoSetor;
	}

	public void setCodigoSetor(Integer codigoSetor) {
		this.codigoSetor = codigoSetor;
	}

	public Date getDataExecucao() {
		return dataExecucao;
	}

	public void setDataExecucao(Date dataExecucao) {
		this.dataExecucao = dataExecucao;
	}

	public Date getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(Date dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public Date getDataTramite() {
		return dataTramite;
	}

	public void setDataTramite(Date dataTramite) {
		this.dataTramite = dataTramite;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public short getIndicadorMovimento() {
		return indicadorMovimento;
	}

	public void setIndicadorMovimento(short indicadorMovimento) {
		this.indicadorMovimento = indicadorMovimento;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getLoginUsuario() {
		return loginUsuario;
	}

	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getNomeSolicitante() {
		return nomeSolicitante;
	}

	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}

	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getParecer() {
		return parecer;
	}

	public void setParecer(String parecer) {
		this.parecer = parecer;
	}

	public PavimentoCalcada getPavimentoCalcada() {
		return pavimentoCalcada;
	}

	public void setPavimentoCalcada(PavimentoCalcada pavimentoCalcada) {
		this.pavimentoCalcada = pavimentoCalcada;
	}

	public PavimentoRua getPavimentoRua() {
		return pavimentoRua;
	}

	public void setPavimentoRua(PavimentoRua pavimentoRua) {
		this.pavimentoRua = pavimentoRua;
	}

	public String getPontoReferencia() {
		return pontoReferencia;
	}

	public void setPontoReferencia(String pontoReferencia) {
		this.pontoReferencia = pontoReferencia;
	}

	public RegistroAtendimento getRegistroAtendimento() {
		return registroAtendimento;
	}

	public void setRegistroAtendimento(RegistroAtendimento registroAtendimento) {
		this.registroAtendimento = registroAtendimento;
	}

	public ServicoTipo getServicoTipo() {
		return servicoTipo;
	}

	public void setServicoTipo(ServicoTipo servicoTipo) {
		this.servicoTipo = servicoTipo;
	}

	public ServicoTipoPrioridade getServicoTipoPrioridadeAtual() {
		return servicoTipoPrioridadeAtual;
	}

	public void setServicoTipoPrioridadeAtual(
			ServicoTipoPrioridade servicoTipoPrioridadeAtual) {
		this.servicoTipoPrioridadeAtual = servicoTipoPrioridadeAtual;
	}

	public String getTelefoneSolicitante() {
		return telefoneSolicitante;
	}

	public void setTelefoneSolicitante(String telefoneSolicitante) {
		this.telefoneSolicitante = telefoneSolicitante;
	}

	public UnidadeOrganizacional getUnidadeOrganizacionalCentralizadora() {
		return unidadeOrganizacionalCentralizadora;
	}

	public void setUnidadeOrganizacionalCentralizadora(
			UnidadeOrganizacional unidadeOrganizacionalCentralizadora) {
		this.unidadeOrganizacionalCentralizadora = unidadeOrganizacionalCentralizadora;
	}

	public UnidadeOrganizacional getUnidadeOrganizacionalExecutora() {
		return unidadeOrganizacionalExecutora;
	}

	public void setUnidadeOrganizacionalExecutora(
			UnidadeOrganizacional unidadeOrganizacionalExecutora) {
		this.unidadeOrganizacionalExecutora = unidadeOrganizacionalExecutora;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public AtendimentoMotivoEncerramento getAtendimentoMotivoEncerramento() {
		return atendimentoMotivoEncerramento;
	}

	public void setAtendimentoMotivoEncerramento(
			AtendimentoMotivoEncerramento atendimentoMotivoEncerramento) {
		this.atendimentoMotivoEncerramento = atendimentoMotivoEncerramento;
	}

	public Date getDataHoraGeracaoHistorico() {
		return dataHoraGeracaoHistorico;
	}

	public void setDataHoraGeracaoHistorico(Date dataHoraGeracaoHistorico) {
		this.dataHoraGeracaoHistorico = dataHoraGeracaoHistorico;
	}

	
}
