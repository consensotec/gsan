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
package gsan.micromedicao;

import gsan.cadastro.empresa.Empresa;
import gsan.cadastro.localidade.SetorComercial;
import gsan.cobranca.CobrancaGrupo;
import gsan.faturamento.FaturamentoGrupo;
import gsan.interceptor.ControleAlteracao;
import gsan.interceptor.ObjetoTransacao;
import gsan.micromedicao.leitura.LeituraTipo;
import gsan.util.filtro.Filtro;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class Rota extends ObjetoTransacao {
	
	public static final int TRANSFERIR_ROTA_ENTRE_GRUPO_EMPRESA = 1559;
	public static final int OPERACAO_ATUALIZAR_ROTA = 809;
	public static final int OPERACAO_REMOVER_ROTA = 27;
	
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;
    
    @ControleAlteracao(funcionalidade={OPERACAO_ATUALIZAR_ROTA, OPERACAO_REMOVER_ROTA})
    private Short codigo;
    
    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={OPERACAO_ATUALIZAR_ROTA, OPERACAO_REMOVER_ROTA})
    private Short indicadorAjusteConsumo;
    
    @ControleAlteracao(funcionalidade={OPERACAO_ATUALIZAR_ROTA, OPERACAO_REMOVER_ROTA})
    private Short indicadorRotaAlternativa = new Short("2");
    
    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={OPERACAO_ATUALIZAR_ROTA, OPERACAO_REMOVER_ROTA})
    private Date dataAjusteLeitura;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={OPERACAO_ATUALIZAR_ROTA, OPERACAO_REMOVER_ROTA})
    private Short indicadorFiscalizarCortado;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={OPERACAO_ATUALIZAR_ROTA, OPERACAO_REMOVER_ROTA})
    private Short indicadorFiscalizarSuprimido;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={OPERACAO_ATUALIZAR_ROTA, OPERACAO_REMOVER_ROTA})
    private Short indicadorGerarFalsaFaixa;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={OPERACAO_ATUALIZAR_ROTA, OPERACAO_REMOVER_ROTA})
    private BigDecimal percentualGeracaoFaixaFalsa;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={OPERACAO_ATUALIZAR_ROTA, OPERACAO_REMOVER_ROTA})
    private Short indicadorGerarFiscalizacao;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={OPERACAO_ATUALIZAR_ROTA, OPERACAO_REMOVER_ROTA})
    private BigDecimal percentualGeracaoFiscalizacao;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={OPERACAO_ATUALIZAR_ROTA, OPERACAO_REMOVER_ROTA})
    private Short indicadorUso;
    
    @ControleAlteracao(funcionalidade={OPERACAO_ATUALIZAR_ROTA, OPERACAO_REMOVER_ROTA})
    private Short indicadorTransmissaoOffline;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={TRANSFERIR_ROTA_ENTRE_GRUPO_EMPRESA, OPERACAO_ATUALIZAR_ROTA, OPERACAO_REMOVER_ROTA})
    private Date ultimaAlteracao;

    /** persistent field */
    @ControleAlteracao(value=FiltroRota.EMPRESA, 
    		funcionalidade={TRANSFERIR_ROTA_ENTRE_GRUPO_EMPRESA, OPERACAO_ATUALIZAR_ROTA, OPERACAO_REMOVER_ROTA})
    private Empresa empresa;

    /** persistent field */
    @ControleAlteracao(value=FiltroRota.FATURAMENTO_GRUPO, 
    		funcionalidade={TRANSFERIR_ROTA_ENTRE_GRUPO_EMPRESA, OPERACAO_ATUALIZAR_ROTA, OPERACAO_REMOVER_ROTA})
    private FaturamentoGrupo faturamentoGrupo;

    /** persistent field */
    private LeituraTipo leituraTipo;

    /** persistent field */
    private SetorComercial setorComercial;

    /** persistent field */
    @ControleAlteracao(value=FiltroRota.COBRANCA_GRUPO, 
    		funcionalidade={TRANSFERIR_ROTA_ENTRE_GRUPO_EMPRESA, OPERACAO_ATUALIZAR_ROTA, OPERACAO_REMOVER_ROTA})
    private CobrancaGrupo cobrancaGrupo;
    
    /** persistent field */
    @ControleAlteracao(value=FiltroRota.EMPRESA, 
	funcionalidade={TRANSFERIR_ROTA_ENTRE_GRUPO_EMPRESA, OPERACAO_ATUALIZAR_ROTA, OPERACAO_REMOVER_ROTA})
    private Empresa empresaCobranca;
    
    /** persistent field */
    @ControleAlteracao(funcionalidade={OPERACAO_ATUALIZAR_ROTA, OPERACAO_REMOVER_ROTA})
    private Leiturista leiturista;
    
    /** persistent field */
    @ControleAlteracao(funcionalidade={OPERACAO_ATUALIZAR_ROTA, OPERACAO_REMOVER_ROTA})
    private Integer numeroSequenciaLeitura;
    
    /** persistent field */
    @ControleAlteracao(funcionalidade={OPERACAO_ATUALIZAR_ROTA, OPERACAO_REMOVER_ROTA})
    private Empresa empresaEntregaContas;
    
    
    /** persistent field */
    @ControleAlteracao(funcionalidade={OPERACAO_ATUALIZAR_ROTA, OPERACAO_REMOVER_ROTA})
    private Integer numeroLimiteImoveis;
    
    @ControleAlteracao(funcionalidade={OPERACAO_ATUALIZAR_ROTA, OPERACAO_REMOVER_ROTA})
    private Integer indicadorSequencialLeitura;
    
    @ControleAlteracao(funcionalidade={OPERACAO_ATUALIZAR_ROTA, OPERACAO_REMOVER_ROTA})
    private Integer numeroDiasConsumoAjuste;
    
    @ControleAlteracao(funcionalidade={OPERACAO_ATUALIZAR_ROTA, OPERACAO_REMOVER_ROTA})
    private Short indicadorCoordenadas;

    /** persistent field */
    //private CobrancaCriterio cobrancaCriterio;
    
     /**
      * Description of the Field
      */
     public final static int INDICADOR_AJUSTE_MENSAL = 1;
     
     /**
      * Description of the Field
      */
     public final static int INDICADOR_SUPRIMIDO_ATIVO = 1;
     
     /**
      * Description of the Field
      */
     public final static int INDICADOR_CORTADO_ATIVO = 1;
     
     /**
      * Description of the Field
      */
     public final static int LEITURA_TIPO_CONVENCIONAL = 1;
     
     /**
      * Description of the Field
      */
     public final static int LEITURA_TIPO_RELACAO = 2;
     
     public final static Short  INDICADOR_GERAR_FISCALIZACAO = new Short("1");
     
     public final static Short INDICADOR_NAO_GERAR_FAIXA_FALSA = new Short("2");


    /** full constructor */
    public Rota(Short indicadorAjusteConsumo, Date dataAjusteLeitura, Short indicadorFiscalizarCortado, Short indicadorFiscalizarSuprimido, Short indicadorGerarFalsaFaixa, BigDecimal percentualGeracaoFaixaFalsa, Short indicadorGerarFiscalizacao, BigDecimal percentualGeracaoFiscalizacao, Short indicadorUso, Date ultimaAlteracao, Empresa empresa, FaturamentoGrupo faturamentoGrupo, LeituraTipo leituraTipo, SetorComercial setorComercial, CobrancaGrupo cobrancaGrupo) {
        this.indicadorAjusteConsumo = indicadorAjusteConsumo;
        this.dataAjusteLeitura = dataAjusteLeitura;
        this.indicadorFiscalizarCortado = indicadorFiscalizarCortado;
        this.indicadorFiscalizarSuprimido = indicadorFiscalizarSuprimido;
        this.indicadorGerarFalsaFaixa = indicadorGerarFalsaFaixa;
        this.percentualGeracaoFaixaFalsa = percentualGeracaoFaixaFalsa;
        this.indicadorGerarFiscalizacao = indicadorGerarFiscalizacao;
        this.percentualGeracaoFiscalizacao = percentualGeracaoFiscalizacao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.empresa = empresa;
        this.faturamentoGrupo = faturamentoGrupo;
        this.leituraTipo = leituraTipo;
        this.setorComercial = setorComercial;
        this.cobrancaGrupo = cobrancaGrupo;
    }
    
    /** full constructor */
    public Rota(Short codigo, Short indicadorAjusteConsumo, Date dataAjusteLeitura, Short indicadorFiscalizarCortado, Short indicadorFiscalizarSuprimido, Short indicadorGerarFalsaFaixa, BigDecimal percentualGeracaoFaixaFalsa, Short indicadorGerarFiscalizacao, BigDecimal percentualGeracaoFiscalizacao, Short indicadorUso, Date ultimaAlteracao, Empresa empresa, FaturamentoGrupo faturamentoGrupo, LeituraTipo leituraTipo, SetorComercial setorComercial, CobrancaGrupo cobrancaGrupo) {
        this.codigo = codigo;
    	this.indicadorAjusteConsumo = indicadorAjusteConsumo;
        this.dataAjusteLeitura = dataAjusteLeitura;
        this.indicadorFiscalizarCortado = indicadorFiscalizarCortado;
        this.indicadorFiscalizarSuprimido = indicadorFiscalizarSuprimido;
        this.indicadorGerarFalsaFaixa = indicadorGerarFalsaFaixa;
        this.percentualGeracaoFaixaFalsa = percentualGeracaoFaixaFalsa;
        this.indicadorGerarFiscalizacao = indicadorGerarFiscalizacao;
        this.percentualGeracaoFiscalizacao = percentualGeracaoFiscalizacao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.empresa = empresa;
        this.faturamentoGrupo = faturamentoGrupo;
        this.leituraTipo = leituraTipo;
        this.setorComercial = setorComercial;
        this.cobrancaGrupo = cobrancaGrupo;
    }
    
    /** full constructor */
    public Rota(Short codigo, Short indicadorAjusteConsumo, Date dataAjusteLeitura, Short indicadorFiscalizarCortado, Short indicadorFiscalizarSuprimido, Short indicadorGerarFalsaFaixa, BigDecimal percentualGeracaoFaixaFalsa, Short indicadorGerarFiscalizacao, BigDecimal percentualGeracaoFiscalizacao, Short indicadorUso, Date ultimaAlteracao, Empresa empresa, FaturamentoGrupo faturamentoGrupo, LeituraTipo leituraTipo, SetorComercial setorComercial, CobrancaGrupo cobrancaGrupo, Short indicadorTransmissaoOffline) {
        this.codigo = codigo;
    	this.indicadorAjusteConsumo = indicadorAjusteConsumo;
        this.dataAjusteLeitura = dataAjusteLeitura;
        this.indicadorFiscalizarCortado = indicadorFiscalizarCortado;
        this.indicadorFiscalizarSuprimido = indicadorFiscalizarSuprimido;
        this.indicadorGerarFalsaFaixa = indicadorGerarFalsaFaixa;
        this.percentualGeracaoFaixaFalsa = percentualGeracaoFaixaFalsa;
        this.indicadorGerarFiscalizacao = indicadorGerarFiscalizacao;
        this.percentualGeracaoFiscalizacao = percentualGeracaoFiscalizacao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.empresa = empresa;
        this.faturamentoGrupo = faturamentoGrupo;
        this.leituraTipo = leituraTipo;
        this.setorComercial = setorComercial;
        this.cobrancaGrupo = cobrancaGrupo;
        this.indicadorTransmissaoOffline = indicadorTransmissaoOffline; 
    }

	/** default constructor */
    public Rota() {
    }

    /** minimal constructor */
    public Rota(Empresa empresa, FaturamentoGrupo faturamentoGrupo, LeituraTipo leituraTipo, SetorComercial setorComercial, CobrancaGrupo cobrancaGrupo) {
        this.empresa = empresa;
        this.faturamentoGrupo = faturamentoGrupo;
        this.leituraTipo = leituraTipo;
        this.setorComercial = setorComercial;
        this.cobrancaGrupo = cobrancaGrupo;
    }

    
    public Short getIndicadorRotaAlternativa() {
		return indicadorRotaAlternativa;
	}

	public void setIndicadorRotaAlternativa(Short indicadorRotaAlternativa) {
		this.indicadorRotaAlternativa = indicadorRotaAlternativa;
	}

	public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getIndicadorAjusteConsumo() {
        return this.indicadorAjusteConsumo;
    }

    public void setIndicadorAjusteConsumo(Short indicadorAjusteConsumo) {
        this.indicadorAjusteConsumo = indicadorAjusteConsumo;
    }

    public Date getDataAjusteLeitura() {
        return this.dataAjusteLeitura;
    }

    public void setDataAjusteLeitura(Date dataAjusteLeitura) {
        this.dataAjusteLeitura = dataAjusteLeitura;
    }

    public Short getIndicadorFiscalizarCortado() {
        return this.indicadorFiscalizarCortado;
    }

    public void setIndicadorFiscalizarCortado(Short indicadorFiscalizarCortado) {
        this.indicadorFiscalizarCortado = indicadorFiscalizarCortado;
    }

    public Short getIndicadorFiscalizarSuprimido() {
        return this.indicadorFiscalizarSuprimido;
    }

    public void setIndicadorFiscalizarSuprimido(Short indicadorFiscalizarSuprimido) {
        this.indicadorFiscalizarSuprimido = indicadorFiscalizarSuprimido;
    }

    public Short getIndicadorGerarFalsaFaixa() {
        return this.indicadorGerarFalsaFaixa;
    }

    public void setIndicadorGerarFalsaFaixa(Short indicadorGerarFalsaFaixa) {
        this.indicadorGerarFalsaFaixa = indicadorGerarFalsaFaixa;
    }

    public BigDecimal getPercentualGeracaoFaixaFalsa() {
        return this.percentualGeracaoFaixaFalsa;
    }

    public void setPercentualGeracaoFaixaFalsa(BigDecimal percentualGeracaoFaixaFalsa) {
        this.percentualGeracaoFaixaFalsa = percentualGeracaoFaixaFalsa;
    }

    public Short getIndicadorGerarFiscalizacao() {
        return this.indicadorGerarFiscalizacao;
    }

    public void setIndicadorGerarFiscalizacao(Short indicadorGerarFiscalizacao) {
        this.indicadorGerarFiscalizacao = indicadorGerarFiscalizacao;
    }

    public BigDecimal getPercentualGeracaoFiscalizacao() {
        return this.percentualGeracaoFiscalizacao;
    }

    public void setPercentualGeracaoFiscalizacao(BigDecimal percentualGeracaoFiscalizacao) {
        this.percentualGeracaoFiscalizacao = percentualGeracaoFiscalizacao;
    }

    public Short getIndicadorUso() {
        return this.indicadorUso;
    }

    public void setIndicadorUso(Short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Empresa getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public FaturamentoGrupo getFaturamentoGrupo() {
        return this.faturamentoGrupo;
    }

    public void setFaturamentoGrupo(FaturamentoGrupo faturamentoGrupo) {
        this.faturamentoGrupo = faturamentoGrupo;
    }

    public LeituraTipo getLeituraTipo() {
        return this.leituraTipo;
    }

    public void setLeituraTipo(LeituraTipo leituraTipo) {
        this.leituraTipo = leituraTipo;
    }

    public SetorComercial getSetorComercial() {
        return this.setorComercial;
    }

    public void setSetorComercial(SetorComercial setorComercial) {
        this.setorComercial = setorComercial;
    }

    public CobrancaGrupo getCobrancaGrupo() {
        return this.cobrancaGrupo;
    }

    public void setCobrancaGrupo(CobrancaGrupo cobrancaGrupo) {
        this.cobrancaGrupo = cobrancaGrupo;
    }
/*
    public CobrancaCriterio getCobrancaCriterio() {
        return this.cobrancaCriterio;
    }

    public void setCobrancaCriterio(CobrancaCriterio cobrancaCriterio) {
        this.cobrancaCriterio = cobrancaCriterio;
    }
*/
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Short getCodigo() {
		return codigo;
	}

	public void setCodigo(Short codigo) {
		this.codigo = codigo;
	}

	public Empresa getEmpresaCobranca() {
		return empresaCobranca;
	}

	public void setEmpresaCobranca(Empresa empresaCobranca) {
		this.empresaCobranca = empresaCobranca;
	}

	public Leiturista getLeiturista() {
		return leiturista;
	}

	public void setLeiturista(Leiturista leiturista) {
		this.leiturista = leiturista;
	}

	/**
	 * @return Returns the numeroSequenciaLeitura.
	 */
	public Integer getNumeroSequenciaLeitura() {
		return numeroSequenciaLeitura;
	}

	/**
	 * @param leituraSequencia The numeroSequenciaLeitura to set.
	 */
	public void setNumeroSequenciaLeitura(Integer leituraSequencia) {
		this.numeroSequenciaLeitura = leituraSequencia;
	}

	public Empresa getEmpresaEntregaContas() {
		return empresaEntregaContas;
	}

	public void setEmpresaEntregaContas(Empresa empresaEntregaContas) {
		this.empresaEntregaContas = empresaEntregaContas;
	}
		
	public Short getIndicadorTransmissaoOffline() {
		return indicadorTransmissaoOffline;
	}

	public void setIndicadorTransmissaoOffline(Short indicadorTransmissaoOffline) {
		this.indicadorTransmissaoOffline = indicadorTransmissaoOffline;
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = { "id" };
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroRota filtroRota = new FiltroRota();
		
		filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.LEITURISTA);
		filtroRota. adicionarCaminhoParaCarregamentoEntidade(FiltroRota.EMPRESA);
		filtroRota. adicionarCaminhoParaCarregamentoEntidade(FiltroRota.EMPRESA_COBRANCA);
		filtroRota. adicionarCaminhoParaCarregamentoEntidade(FiltroRota.FATURAMENTO_GRUPO);
		filtroRota. adicionarCaminhoParaCarregamentoEntidade(FiltroRota.COBRANCA_GRUPO);
		filtroRota. adicionarCaminhoParaCarregamentoEntidade(FiltroRota.SETOR_COMERCIAL);
		filtroRota. adicionarCaminhoParaCarregamentoEntidade(FiltroRota.LOCALIDADE);
		filtroRota. adicionarParametro(
				new ParametroSimples(FiltroRota.ID_ROTA, this.getId()));
		return filtroRota; 
	}
	
	@Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		
		filtro.adicionarParametro(new ParametroSimples(
			FiltroRota.ID_ROTA, this.getId()));
		
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.EMPRESA);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.EMPRESA_COBRANCA);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.FATURAMENTO_GRUPO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.SETOR_COMERCIAL);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.LOCALIDADE);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.COBRANCA_GRUPO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.LEITURISTA);
		
		return filtro;
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getId().toString();
	}
	
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []labels = {"id","setorComercial.localidade.descricao","setorComercial.descricao", "codigo"};
		return labels;		
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []labels = {"Id","Localidade","Setor Comercial", "C�digo da Rota"};
		return labels;		
	}


	public Integer getNumeroLimiteImoveis() {
		return numeroLimiteImoveis;
	}

	public void setNumeroLimiteImoveis(Integer numeroLimiteImoveis) {
		this.numeroLimiteImoveis = numeroLimiteImoveis;
	}

    public Integer getIndicadorSequencialLeitura() {
        return indicadorSequencialLeitura;
    }

    public void setIndicadorSequencialLeitura(Integer indicadorSequencialLeitura) {
        this.indicadorSequencialLeitura = indicadorSequencialLeitura;
    }

    public Integer getNumeroDiasConsumoAjuste() {
        return numeroDiasConsumoAjuste;
    }

    public void setNumeroDiasConsumoAjuste(Integer numeroDiasConsumoAjuste) {
        this.numeroDiasConsumoAjuste = numeroDiasConsumoAjuste;
    }

	public Short getIndicadorCoordenadas() {
		return indicadorCoordenadas;
	}

	public void setIndicadorCoordenadas(Short indicadorCoordenadas) {
		this.indicadorCoordenadas = indicadorCoordenadas;
	}

}
