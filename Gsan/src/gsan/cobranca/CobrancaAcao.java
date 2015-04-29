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

import gsan.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gsan.atendimentopublico.ordemservico.ServicoTipo;
import gsan.interceptor.ControleAlteracao;
import gsan.interceptor.ObjetoTransacao;
import gsan.util.ConstantesSistema;
import gsan.util.filtro.Filtro;
import gsan.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class CobrancaAcao extends ObjetoTransacao {
	
	public static final int ATRIBUTOS_ATUALIZAR_ACAO_COBRANCA = 1043; // Operacao.OPERACAO_ATUALIZAR_ACAO_COBRANCA
	public static final int ATRIBUTOS_INSERIR_ACAO_COBRANCA = 1010; // Operacao.OPERACAO_INSERIR_ACAO_COBRANCA
	
	public Filtro retornaFiltro(){
		FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();

		filtroCobrancaAcao.adicionarParametro(new ParametroSimples(FiltroCobrancaAcao.ID,
				this.getId()));			
		
		return filtroCobrancaAcao;
	}
	
	@Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		filtro.adicionarCaminhoParaCarregamentoEntidade("cobrancaCriterio");
		filtro.adicionarCaminhoParaCarregamentoEntidade("servicoTipo");
		filtro.adicionarCaminhoParaCarregamentoEntidade("documentoTipo");
		filtro.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");
		filtro.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");		
		
		filtro.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoPredecessora");
//		filtro.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoPredecessora.cobrancaCriterio");
//		filtro.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoPredecessora.servicoTipo");
//		filtro.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoPredecessora.documentoTipo");
//		filtro.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoPredecessora.ligacaoAguaSituacao");
//		filtro.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoPredecessora.ligacaoEsgotoSituacao");		
		
		filtro.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoPredecessoraAlternativa");
//		filtro.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoPredecessoraAlternativa.cobrancaCriterio");
//		filtro.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoPredecessoraAlternativa.documentoTipo");
//		filtro.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoPredecessoraAlternativa.servicoTipo");
//		filtro.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoPredecessoraAlternativa.ligacaoAguaSituacao");
//		filtro.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoPredecessoraAlternativa.ligacaoEsgotoSituacao");
		return filtro;
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return this.getDescricaoCobrancaAcao();
	}
	
	@Override
	public void initializeLazy() {
		this.getDescricaoCobrancaAcao();
	}

	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_ATUALIZAR_ACAO_COBRANCA, ATRIBUTOS_INSERIR_ACAO_COBRANCA})
    private String descricaoCobrancaAcao;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_ATUALIZAR_ACAO_COBRANCA, ATRIBUTOS_INSERIR_ACAO_COBRANCA})
    private Short indicadorObrigatoriedade;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_ATUALIZAR_ACAO_COBRANCA, ATRIBUTOS_INSERIR_ACAO_COBRANCA})
    private Short indicadorRepeticao;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_ATUALIZAR_ACAO_COBRANCA, ATRIBUTOS_INSERIR_ACAO_COBRANCA})
    private Short indicadorSuspensaoAbastecimento;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_ATUALIZAR_ACAO_COBRANCA, ATRIBUTOS_INSERIR_ACAO_COBRANCA})
    private Short numeroDiasValidade;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_ATUALIZAR_ACAO_COBRANCA, ATRIBUTOS_INSERIR_ACAO_COBRANCA})
    private Short numeroDiasMinimoAcaoPrecedente;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_ATUALIZAR_ACAO_COBRANCA, ATRIBUTOS_INSERIR_ACAO_COBRANCA})
    private Short indicadorCobrancaDebACobrar;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_ATUALIZAR_ACAO_COBRANCA, ATRIBUTOS_INSERIR_ACAO_COBRANCA})
    private Short indicadorGeracaoTaxa;
    
    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_ATUALIZAR_ACAO_COBRANCA, ATRIBUTOS_INSERIR_ACAO_COBRANCA})
    private Short ordemRealizacao;
    
    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_ATUALIZAR_ACAO_COBRANCA, ATRIBUTOS_INSERIR_ACAO_COBRANCA})
    private Short indicadorAcrescimoImpontualidade;

    /** persistent field */
    @ControleAlteracao(value=FiltroCobrancaAcao.COBRANCA_ACAO_PREDECESSORA, 
    		funcionalidade={ATRIBUTOS_ATUALIZAR_ACAO_COBRANCA, ATRIBUTOS_INSERIR_ACAO_COBRANCA})
    private gsan.cobranca.CobrancaAcao cobrancaAcaoPredecessora;

    /** persistent field */
    @ControleAlteracao(value=FiltroCobrancaAcao.LIGACAO_ESGOTO_SITUACAO, 
    		funcionalidade={ATRIBUTOS_ATUALIZAR_ACAO_COBRANCA, ATRIBUTOS_INSERIR_ACAO_COBRANCA})
    private LigacaoEsgotoSituacao ligacaoEsgotoSituacao;

    /** persistent field */
    @ControleAlteracao(value=FiltroCobrancaAcao.DOCUMENTO_TIPO, 
    		funcionalidade={ATRIBUTOS_ATUALIZAR_ACAO_COBRANCA, ATRIBUTOS_INSERIR_ACAO_COBRANCA})
    private gsan.cobranca.DocumentoTipo documentoTipo;

    /** persistent field */
    @ControleAlteracao(value=FiltroCobrancaAcao.LIGACAO_AGUA_SITUACAO, 
    		funcionalidade={ATRIBUTOS_ATUALIZAR_ACAO_COBRANCA, ATRIBUTOS_INSERIR_ACAO_COBRANCA})
    private LigacaoAguaSituacao ligacaoAguaSituacao;

    /** persistent field */
    @ControleAlteracao(value=FiltroCobrancaAcao.SERVICO_TIPO, 
    		funcionalidade={ATRIBUTOS_ATUALIZAR_ACAO_COBRANCA, ATRIBUTOS_INSERIR_ACAO_COBRANCA})
    private ServicoTipo servicoTipo;
    
    /** persistent field */
    @ControleAlteracao(value=FiltroCobrancaAcao.COBRANCAO_CRITERIO, 
    		funcionalidade={ATRIBUTOS_ATUALIZAR_ACAO_COBRANCA, ATRIBUTOS_INSERIR_ACAO_COBRANCA})
    private CobrancaCriterio cobrancaCriterio;
    
    /** persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_ATUALIZAR_ACAO_COBRANCA, ATRIBUTOS_INSERIR_ACAO_COBRANCA})
    private Short indicadorCronograma;
    
    /** persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_ATUALIZAR_ACAO_COBRANCA, ATRIBUTOS_INSERIR_ACAO_COBRANCA})
    private Short indicadorBoletim;
    
    /** persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_ATUALIZAR_ACAO_COBRANCA, ATRIBUTOS_INSERIR_ACAO_COBRANCA})
    private Short indicadorDebito;
    
    /** persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_ATUALIZAR_ACAO_COBRANCA, ATRIBUTOS_INSERIR_ACAO_COBRANCA})
    private Integer numeroDiasVencimento;
    
    /** persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_ATUALIZAR_ACAO_COBRANCA, ATRIBUTOS_INSERIR_ACAO_COBRANCA})
    private Integer indicadorMetasCronograma;
    
    /** persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_ATUALIZAR_ACAO_COBRANCA, ATRIBUTOS_INSERIR_ACAO_COBRANCA})
    private Integer indicadorOrdenamentoCronograma;
    
    /** persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_ATUALIZAR_ACAO_COBRANCA, ATRIBUTOS_INSERIR_ACAO_COBRANCA})
    private Integer indicadorOrdenamentoEventual;
    
    /** persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_ATUALIZAR_ACAO_COBRANCA, ATRIBUTOS_INSERIR_ACAO_COBRANCA})
    private Integer indicadorDebitoInterfereAcao;
    
    /** persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_ATUALIZAR_ACAO_COBRANCA, ATRIBUTOS_INSERIR_ACAO_COBRANCA})
    private Integer numeroDiasRemuneracaoTerceiro;
    
    private Short indicadorCreditosARealizar;

    private Short indicadorNotasPromissoria;
    
    private Short indicadorOrdenarMaiorValor;
    
    private Short indicadorValidarItem;    
    
    
    private Short indicadorEfetuarAcaoCpfCnpjValido;
    
    @ControleAlteracao(value=FiltroCobrancaAcao.COBRANCA_ACAO_PREDECESSORA_ALTERNATIVA,
    	funcionalidade={ATRIBUTOS_ATUALIZAR_ACAO_COBRANCA, ATRIBUTOS_INSERIR_ACAO_COBRANCA})
    private gsan.cobranca.CobrancaAcao cobrancaAcaoPredecessoraAlternativa;
    
    private Integer numerodiasEncerrarAtividade;

    private Integer numerodiasEncerrarOSAtividade;
    
    private Short indicadorAceitaRDCriterio;
    
    private String textoPersonalizado;
    
    private Short indicadorEnviarSMS;
    private Short indicadorEnviarEmail;
    private String textoSMS;
    private String textoEmail;
    
    private Integer numeroMaximoTentativoEnvio;
        
    private Short indicadorExibeEventual;    
    
    private Integer numeroDiasMinimoCobranca;
    
    private Integer numeroDiasMaximoCobranca;
    
    public Short getIndicadorExibeEventual() {
		return indicadorExibeEventual;
	}

	public void setIndicadorExibeEventual(Short indicadorExibeEventual) {
		this.indicadorExibeEventual = indicadorExibeEventual;
	}
	
	public Integer getNumeroDiasMinimoCobranca() {
		return numeroDiasMinimoCobranca;
	}

	public void setNumeroDiasMinimoCobranca(Integer numeroDiasMinimoCobranca) {
		this.numeroDiasMinimoCobranca = numeroDiasMinimoCobranca;
	}

	public Integer getNumeroDiasMaximoCobranca() {
		return numeroDiasMaximoCobranca;
	}

	public void setNumeroDiasMaximoCobranca(Integer numeroDiasMaximoCobranca) {
		this.numeroDiasMaximoCobranca = numeroDiasMaximoCobranca;
	}

	public Short getIndicadorAceitaRDCriterio() {
		return indicadorAceitaRDCriterio;
	}

	public void setIndicadorAceitaRDCriterio(Short indicadorAceitaRDCriterio) {
		this.indicadorAceitaRDCriterio = indicadorAceitaRDCriterio;
	}

	/**
     * Description of the Field
     */
    public final static int AVISO_CORTE = 1;
    
    public final static int	 AVISO_CORTE_A_REVELIA = 21;
    /**
     * Description of the Field
     */
    public final static Integer CORTE_ADMINISTRATIVO = 2;
    
    public final static Integer CORTE_ADMINISTRATIVO_LIGADO_A_REVELIA = 22;
    /**
     * Description of the Field
     */
    public final static Integer CORTE_FISICO = 3;
    
    public final static Integer CORTE_FISICO_LIGADO_A_REVELIA = 23;
    /**
     * Description of the Field
     */
    public final static Integer SUPRESSAO_PARCIAL = 4;
    /**
     * Description of the Field
     */
    public final static Integer SUPRESSAO_TOTAL = 5;
    /**
     * Description of the Field
     */
    public final static Integer AVISO_TAMPONAMENTO = 6;
    /**
     * Description of the Field
     */
    public final static Integer TAMPONAMENTO_ESGOTO = 7;
    /**
     * Description of the Field
     */
    public final static int FISCALIZACAO_SUPRIMIDO = 8;
    /**
     * Description of the Field
     */
    public final static int FISCALIZACAO_CORTADO = 9;
    
    /**
     * Description of the Field
     */
    public final static Integer CARTA_COBRANCA_SUPRIMIDO = 10;
    /**
     * Description of the Field
     */
    public final static Integer CARTA_COBRANCA_CORTADO = 11;
    
    /**
     * Description of the Field
     */
    public final static Integer CARTA_TARIFA_SOCIAL_LIGADO = 12;
    
    public final static Integer CARTA_TARIFA_SOCIAL_LIGADO_A_REVELIA = 24;
    /**
     * Description of the Field
     */
    public final static Integer CARTA_TARIFA_SOCIAL_CORTADO = 13;
    
    /**
     * Description of the Field
     */
    public final static Integer CARTA_COBRANCA_LIGADO = 14;
    
    public final static Integer CARTA_COBRANCA_LIGADO_A_REVELIA = 25;
    /**
     * Description of the Field
     */
    public final static Integer CARTA_COBRANCA_PARCELAMENTO = 15;
    
    /**
     * Description of the Field
     */
    public final static int FISCALIZACAO_FACTIVEL = 16;
    
    public final static int FISCALIZACAO_POTENCIAL = 17;
    
    public final static int FISCALIZACAO_LIGADO = 18;
    
    public final static int FISCALIZACAO_TOTAL = 26;
    
    public final static int FISCALIZACAO_LIGADO_A_REVELIA = 20;
    
    public final static int CORTE_FISICO_PUBLICO = 27;
    
    public final static int RECORTE = 29;
    
    public final static int INSPECAO_LIGACOES = 30;
    
    public final static int CARTA_FINAL_DE_ANO = 33;
    
    public final static Integer VISITA_COBRANCA = 34;
    
    public final static Integer FISCALIZACAO_SITUACAO_ESGOTO = 35;
    
    /**
     * Description of the Field
     */
    public final static Short INDICADOR_CRONOGRAMA_ATIVO = 1;
    
    /**
     * Description of the Field
     */
    public final static Short INDICADOR_BOLETIM_SIM = 1;
    
    /**
     * Description of the Field
     */
    public final static Short INDICADOR_BOLETIM_NAO = 2;
    
    /**
     * Description of the Field
     */
    public final static Short INDICADOR_DEBITO_NAO = 2;
    
    /**
     * Description of the Field
     */
    public final static Short INDICADOR_DEBITO_SIM = 1;
    
    /**
     * Description of the Field
     */
    public final static Short INDICADOR_SIM = 1;
    
    /**
     * Description of the Field
     */
    public final static Short INDICADOR_NAO = 2;
    
    /**
     * Description of the Field
     */
	public static final int INDICADOR_USA_METAS_CRONOGRAMA_SIM = 1; 
    
    
    
    

    /** full constructor */
    public CobrancaAcao(String descricaoCobrancaAcao, Short indicadorObrigatoriedade, Short indicadorRepeticao, Short indicadorSuspensaoAbastecimento, Short numeroDiasValidade, Short numeroDiasMinimoAcaoPrecedente, Short indicadorUso, Date ultimaAlteracao, Short indicadorCobrancaDebACobrar, Short indicadorGeracaoTaxa, gsan.cobranca.CobrancaAcao cobrancaAcaoPredecessora, LigacaoEsgotoSituacao ligacaoEsgotoSituacao, gsan.cobranca.DocumentoTipo documentoTipo, LigacaoAguaSituacao ligacaoAguaSituacao, ServicoTipo servicoTipo, Short indicadorEfetuarAcaoCpfCnpjValido) {
        this.descricaoCobrancaAcao = descricaoCobrancaAcao;
        this.indicadorObrigatoriedade = indicadorObrigatoriedade;
        this.indicadorRepeticao = indicadorRepeticao;
        this.indicadorSuspensaoAbastecimento = indicadorSuspensaoAbastecimento;
        this.numeroDiasValidade = numeroDiasValidade;
        this.numeroDiasMinimoAcaoPrecedente = numeroDiasMinimoAcaoPrecedente;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.indicadorCobrancaDebACobrar = indicadorCobrancaDebACobrar;
        this.indicadorGeracaoTaxa = indicadorGeracaoTaxa;
        this.cobrancaAcaoPredecessora = cobrancaAcaoPredecessora;
        this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
        this.documentoTipo = documentoTipo;
        this.ligacaoAguaSituacao = ligacaoAguaSituacao;
        this.servicoTipo = servicoTipo;
        this.indicadorAceitaRDCriterio = ConstantesSistema.NAO;
        this.indicadorEfetuarAcaoCpfCnpjValido = indicadorEfetuarAcaoCpfCnpjValido;
    }

    /** full constructor */
    public CobrancaAcao(String descricaoCobrancaAcao, Short indicadorObrigatoriedade, Short indicadorRepeticao, Short indicadorSuspensaoAbastecimento, Short numeroDiasValidade, Short numeroDiasMinimoAcaoPrecedente, Short indicadorUso, Date ultimaAlteracao, Short indicadorCobrancaDebACobrar, Short indicadorGeracaoTaxa, gsan.cobranca.CobrancaAcao cobrancaAcao, LigacaoEsgotoSituacao ligacaoEsgotoSituacao, gsan.cobranca.DocumentoTipo documentoTipo, LigacaoAguaSituacao ligacaoAguaSituacao, ServicoTipo servicoTipo, CobrancaCriterio cobrancaCriterio, Short indicadorEfetuarAcaoCpfCnpjValido) {
        this.descricaoCobrancaAcao = descricaoCobrancaAcao;
        this.indicadorObrigatoriedade = indicadorObrigatoriedade;
        this.indicadorRepeticao = indicadorRepeticao;
        this.indicadorSuspensaoAbastecimento = indicadorSuspensaoAbastecimento;
        this.numeroDiasValidade = numeroDiasValidade;
        this.numeroDiasMinimoAcaoPrecedente = numeroDiasMinimoAcaoPrecedente;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.indicadorCobrancaDebACobrar = indicadorCobrancaDebACobrar;
        this.indicadorGeracaoTaxa = indicadorGeracaoTaxa;
        this.cobrancaAcaoPredecessora = cobrancaAcao;
        this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
        this.documentoTipo = documentoTipo;
        this.ligacaoAguaSituacao = ligacaoAguaSituacao;
        this.servicoTipo = servicoTipo;
        this.cobrancaCriterio = cobrancaCriterio;
        this.indicadorAceitaRDCriterio = ConstantesSistema.NAO;

        this.indicadorEfetuarAcaoCpfCnpjValido=indicadorEfetuarAcaoCpfCnpjValido;
    }

    /**
	 * @return Retorna o campo cobrancaCriterio.
	 */
	public CobrancaCriterio getCobrancaCriterio() {
		return cobrancaCriterio;
	}

	/**
	 * @param cobrancaCriterio O cobrancaCriterio a ser setado.
	 */
	public void setCobrancaCriterio(CobrancaCriterio cobrancaCriterio) {
		this.cobrancaCriterio = cobrancaCriterio;
	}

	/** default constructor */
    public CobrancaAcao() {
    	this.indicadorAceitaRDCriterio = ConstantesSistema.NAO;
    }

    /** minimal constructor */
    public CobrancaAcao(gsan.cobranca.CobrancaAcao cobrancaAcaoPredecessora, LigacaoEsgotoSituacao ligacaoEsgotoSituacao, gsan.cobranca.DocumentoTipo documentoTipo, LigacaoAguaSituacao ligacaoAguaSituacao, ServicoTipo servicoTipo) {
    	this.indicadorAceitaRDCriterio = ConstantesSistema.NAO;
        this.cobrancaAcaoPredecessora = cobrancaAcaoPredecessora;
        this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
        this.documentoTipo = documentoTipo;
        this.ligacaoAguaSituacao = ligacaoAguaSituacao;
        this.servicoTipo = servicoTipo;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoCobrancaAcao() {
        return this.descricaoCobrancaAcao;
    }

    public void setDescricaoCobrancaAcao(String descricaoCobrancaAcao) {
        this.descricaoCobrancaAcao = descricaoCobrancaAcao;
    }

    public Short getIndicadorObrigatoriedade() {
        return this.indicadorObrigatoriedade;
    }

    public void setIndicadorObrigatoriedade(Short indicadorObrigatoriedade) {
        this.indicadorObrigatoriedade = indicadorObrigatoriedade;
    }

    public Short getIndicadorRepeticao() {
        return this.indicadorRepeticao;
    }

    public void setIndicadorRepeticao(Short indicadorRepeticao) {
        this.indicadorRepeticao = indicadorRepeticao;
    }

    public Short getIndicadorSuspensaoAbastecimento() {
        return this.indicadorSuspensaoAbastecimento;
    }

    public void setIndicadorSuspensaoAbastecimento(Short indicadorSuspensaoAbastecimento) {
        this.indicadorSuspensaoAbastecimento = indicadorSuspensaoAbastecimento;
    }

    public Short getNumeroDiasValidade() {
        return this.numeroDiasValidade;
    }

    public void setNumeroDiasValidade(Short numeroDiasValidade) {
        this.numeroDiasValidade = numeroDiasValidade;
    }

    public Short getNumeroDiasMinimoAcaoPrecedente() {
        return this.numeroDiasMinimoAcaoPrecedente;
    }

    public void setNumeroDiasMinimoAcaoPrecedente(Short numeroDiasMinimoAcaoPrecedente) {
        this.numeroDiasMinimoAcaoPrecedente = numeroDiasMinimoAcaoPrecedente;
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

    public Short getIndicadorCobrancaDebACobrar() {
        return this.indicadorCobrancaDebACobrar;
    }

    public void setIndicadorCobrancaDebACobrar(Short indicadorCobrancaDebACobrar) {
        this.indicadorCobrancaDebACobrar = indicadorCobrancaDebACobrar;
    }

    public Short getIndicadorGeracaoTaxa() {
        return this.indicadorGeracaoTaxa;
    }

    public void setIndicadorGeracaoTaxa(Short indicadorGeracaoTaxa) {
        this.indicadorGeracaoTaxa = indicadorGeracaoTaxa;
    }

    public gsan.cobranca.CobrancaAcao getCobrancaAcaoPredecessora() {
        return this.cobrancaAcaoPredecessora;
    }

    public void setCobrancaAcaoPredecessora(gsan.cobranca.CobrancaAcao cobrancaAcaoPredecessora) {
        this.cobrancaAcaoPredecessora = cobrancaAcaoPredecessora;
    }

    public LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
        return this.ligacaoEsgotoSituacao;
    }

    public void setLigacaoEsgotoSituacao(LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
        this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
    }

    public gsan.cobranca.DocumentoTipo getDocumentoTipo() {
        return this.documentoTipo;
    }

    public void setDocumentoTipo(gsan.cobranca.DocumentoTipo documentoTipo) {
        this.documentoTipo = documentoTipo;
    }

    public LigacaoAguaSituacao getLigacaoAguaSituacao() {
        return this.ligacaoAguaSituacao;
    }

    public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
        this.ligacaoAguaSituacao = ligacaoAguaSituacao;
    }

    public ServicoTipo getServicoTipo() {
        return this.servicoTipo;
    }

    public void setServicoTipo(ServicoTipo servicoTipo) {
        this.servicoTipo = servicoTipo;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Short getOrdemRealizacao() {
		return ordemRealizacao;
	}

	public void setOrdemRealizacao(Short ordemRealizacao) {
		this.ordemRealizacao = ordemRealizacao;
	}
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public Short getIndicadorAcrescimoImpontualidade() {
		return indicadorAcrescimoImpontualidade;
	}

	public void setIndicadorAcrescimoImpontualidade(
			Short indicadorAcrescimoImpontualidade) {
		this.indicadorAcrescimoImpontualidade = indicadorAcrescimoImpontualidade;
	}

	public Short getIndicadorCronograma() {
		return indicadorCronograma;
	}

	public void setIndicadorCronograma(Short indicadorCronograma) {
		this.indicadorCronograma = indicadorCronograma;
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

	public Integer getNumeroDiasVencimento() {
		return numeroDiasVencimento;
	}

	public void setNumeroDiasVencimento(Integer numeroDiasVencimento) {
		this.numeroDiasVencimento = numeroDiasVencimento;
	}
	
	/**
	 * @return Retorna o campo indicadorDebitoInterfereAcao.
	 */
	public Integer getIndicadorDebitoInterfereAcao() {
		return indicadorDebitoInterfereAcao;
	}

	/**
	 * @param indicadorDebitoInterfereAcao O indicadorDebitoInterfereAcao a ser setado.
	 */
	public void setIndicadorDebitoInterfereAcao(Integer indicadorDebitoInterfereAcao) {
		this.indicadorDebitoInterfereAcao = indicadorDebitoInterfereAcao;
	}

	/**
	 * @return Retorna o campo indicadorMetasCronograma.
	 */
	public Integer getIndicadorMetasCronograma() {
		return indicadorMetasCronograma;
	}

	/**
	 * @param indicadorMetasCronograma O indicadorMetasCronograma a ser setado.
	 */
	public void setIndicadorMetasCronograma(Integer indicadorMetasCronograma) {
		this.indicadorMetasCronograma = indicadorMetasCronograma;
	}

	/**
	 * @return Retorna o campo indicadorOrdenamentoCronograma.
	 */
	public Integer getIndicadorOrdenamentoCronograma() {
		return indicadorOrdenamentoCronograma;
	}

	/**
	 * @param indicadorOrdenamentoCronograma O indicadorOrdenamentoCronograma a ser setado.
	 */
	public void setIndicadorOrdenamentoCronograma(
			Integer indicadorOrdenamentoCronograma) {
		this.indicadorOrdenamentoCronograma = indicadorOrdenamentoCronograma;
	}

	/**
	 * @return Retorna o campo indicadorOrdenamentoEventual.
	 */
	public Integer getIndicadorOrdenamentoEventual() {
		return indicadorOrdenamentoEventual;
	}

	/**
	 * @param indicadorOrdenamentoEventual O indicadorOrdenamentoEventual a ser setado.
	 */
	public void setIndicadorOrdenamentoEventual(Integer indicadorOrdenamentoEventual) {
		this.indicadorOrdenamentoEventual = indicadorOrdenamentoEventual;
	}

	/**
	 * @return Retorna o campo numeroDiasRemuneracaoTerceiro.
	 */
	public Integer getNumeroDiasRemuneracaoTerceiro() {
		return numeroDiasRemuneracaoTerceiro;
	}

	/**
	 * @param numeroDiasRemuneracaoTerceiro O numeroDiasRemuneracaoTerceiro a ser setado.
	 */
	public void setNumeroDiasRemuneracaoTerceiro(
			Integer numeroDiasRemuneracaoTerceiro) {
		this.numeroDiasRemuneracaoTerceiro = numeroDiasRemuneracaoTerceiro;
	}

	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String[] labels = {"descricaoCobrancaAcao"};
		return labels;		
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String[] labels = {"Descricao"};
		return labels;		
	}

	public Short getIndicadorCreditosARealizar() {
		return indicadorCreditosARealizar;
	}

	public void setIndicadorCreditosARealizar(Short indicadorCreditosARealizar) {
		this.indicadorCreditosARealizar = indicadorCreditosARealizar;
	}

	public Short getIndicadorNotasPromissoria() {
		return indicadorNotasPromissoria;
	}

	public void setIndicadorNotasPromissoria(Short indicadorNotasPromissoria) {
		this.indicadorNotasPromissoria = indicadorNotasPromissoria;
	}

	public Short getIndicadorOrdenarMaiorValor() {
		return indicadorOrdenarMaiorValor;
	}

	public void setIndicadorOrdenarMaiorValor(Short indicadorOrdenarMaiorValor) {
		this.indicadorOrdenarMaiorValor = indicadorOrdenarMaiorValor;
	}

	public Short getIndicadorValidarItem() {
		return indicadorValidarItem;
	}

	public void setIndicadorValidarItem(Short indicadorValidarItem) {
		this.indicadorValidarItem = indicadorValidarItem;
	}

	public gsan.cobranca.CobrancaAcao getCobrancaAcaoPredecessoraAlternativa() {
		return cobrancaAcaoPredecessoraAlternativa;
	}

	public void setCobrancaAcaoPredecessoraAlternativa(
			gsan.cobranca.CobrancaAcao cobrancaAcaoPredecessoraAlternativa) {
		this.cobrancaAcaoPredecessoraAlternativa = cobrancaAcaoPredecessoraAlternativa;
	}

	public Integer getNumerodiasEncerrarAtividade() {
		return numerodiasEncerrarAtividade;
	}

	public void setNumerodiasEncerrarAtividade(Integer numerodiasEncerrarAtividade) {
		this.numerodiasEncerrarAtividade = numerodiasEncerrarAtividade;
	}

	public Integer getNumerodiasEncerrarOSAtividade() {
		return numerodiasEncerrarOSAtividade;
	}

	public void setNumerodiasEncerrarOSAtividade(
			Integer numerodiasEncerrarOSAtividade) {
		this.numerodiasEncerrarOSAtividade = numerodiasEncerrarOSAtividade;
	}

	public String getTextoPersonalizado() {
		return textoPersonalizado;
	}

	public void setTextoPersonalizado(String textoPersonalizado) {
		this.textoPersonalizado = textoPersonalizado;
	}

	public Short getIndicadorEfetuarAcaoCpfCnpjValido() {
		return indicadorEfetuarAcaoCpfCnpjValido;
	}

	public void setIndicadorEfetuarAcaoCpfCnpjValido(
			Short indicadorEfetuarAcaoCpfCnpjValido) {
		this.indicadorEfetuarAcaoCpfCnpjValido = indicadorEfetuarAcaoCpfCnpjValido;
	}

	public Short getIndicadorEnviarSMS() {
		return indicadorEnviarSMS;
	}

	public void setIndicadorEnviarSMS(Short indicadorEnviarSMS) {
		this.indicadorEnviarSMS = indicadorEnviarSMS;
	}

	public Short getIndicadorEnviarEmail() {
		return indicadorEnviarEmail;
	}

	public void setIndicadorEnviarEmail(Short indicadorEnviarEmail) {
		this.indicadorEnviarEmail = indicadorEnviarEmail;
	}

	public String getTextoSMS() {
		return textoSMS;
	}

	public void setTextoSMS(String textoSMS) {
		this.textoSMS = textoSMS;
	}

	public String getTextoEmail() {
		return textoEmail;
	}

	public void setTextoEmail(String textoEmail) {
		this.textoEmail = textoEmail;
	}

	public Integer getNumeroMaximoTentativoEnvio() {
		return numeroMaximoTentativoEnvio;
	}

	public void setNumeroMaximoTentativoEnvio(Integer numeroMaximoTentativoEnvio) {
		this.numeroMaximoTentativoEnvio = numeroMaximoTentativoEnvio;
	}

	
	
}
