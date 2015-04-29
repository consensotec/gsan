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
package gsan.cobranca.bean;

import gsan.cobranca.CobrancaAcao;
import gsan.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;

/**
 * [UC0312] Inserir A��o de Cobran�a
 * 
 * @author S�vio Luiz
 * @date 04/12/2007
 */
public class CobrancaAcaoHelper implements Serializable {

	private static final long serialVersionUID = 1L;

	/** nullable persistent field */
	private String descricaoCobrancaAcao;

	/** nullable persistent field */
	private String indicadorObrigatoriedade;

	/** nullable persistent field */
	private String indicadorRepeticao;

	/** nullable persistent field */
	private String indicadorSuspensaoAbastecimento;

	/** nullable persistent field */
	private String numeroDiasValidade;

	/** nullable persistent field */
	private String numeroDiasMinimoAcaoPrecedente;

	/** nullable persistent field */
	private String indicadorUso;

	/** nullable persistent field */
	private String ultimaAlteracao;

	/** nullable persistent field */
	private String indicadorCobrancaDebACobrar;
	
	/** persistent field */
	private String indicadorCreditosARealizar;
	
	/** persistent field */
	private String indicadorNotasPromissoria; 

	/** nullable persistent field */
	private String indicadorGeracaoTaxa;

	/** nullable persistent field */
	private String ordemRealizacao;

	/** nullable persistent field */
	private String indicadorAcrescimoImpontualidade;

	/** persistent field */
	private String idCobrancaAcaoPredecessora;
	
	/** persistent field */
	private String idCobrancaAcaoPredecessoraAlternativa;

	/** persistent field */
	private String idLigacaoEsgotoSituacao;

	/** persistent field */
	private String idDocumentoTipo;

	/** persistent field */
	private String idLigacaoAguaSituacao;

	/** persistent field */
	private String idServicoTipo;

	/** persistent field */
	private String idCobrancaCriterio;

	/** persistent field */
	private String indicadorCronograma;

	/** persistent field */
	private String indicadorBoletim;

	/** persistent field */
	private String indicadorDebito;

	/** persistent field */
	private String numeroDiasVencimento;

	/** persistent field */
	private String descricaoCobrancaCriterio;

	/** persistent field */
	private String descricaoServicoTipo;
	
	/** persistent field */
	private String indicadorMetasCronograma;
	
	/** persistent field */
	private String indicadorOrdenamentoCronograma;
	
	/** persistent field */
	private String indicadorOrdenamentoEventual;
	
	/** persistent field */
	private String indicadorDebitoInterfereAcao;
	
	/** persistent field */
	private String numeroDiasRemuneracaoTerceiro;
	
	/** persistent field */
	private Usuario usuarioLogado;
	
	/** persistent field */
	private String indicadorOrdenarMaiorValor;
	
	/** persistent field */
	private String indicadorValidarItem;
	
	/** persistent field */
	private String textoPersonalizado;
	
	/** persistent field */
	private String indicadorEfetuarAcaoCpfCnpjValido;

	private String indicadorEnviarSMS;
    private String indicadorEnviarEmail;
    private String textoSMS;
    private String textoEmail;
    
    private Short indicadorExibeEventual;  
   
    
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
	
	

	private Integer numeroDiasMinimoCobranca;
    
    private Integer numeroDiasMaximoCobranca;
    
    private String numeroMaximoTentativoEnvio; 
	
	/** full constructor 
	 * @param string4 
	 * @param string3 
	 * @param string2 
	 * @param string */
	public CobrancaAcaoHelper(String descricaoCobrancaAcao,
			String indicadorObrigatoriedade, String indicadorRepeticao,
			String indicadorSuspensaoAbastecimento, String numeroDiasValidade,
			String numeroDiasMinimoAcaoPrecedente, String indicadorUso, String indicadorCobrancaDebACobrar,
			String indicadorGeracaoTaxa, String ordemRealizacao,
			String indicadorAcrescimoImpontualidade,
			String idCobrancaAcaoPredecessora, String idLigacaoEsgotoSituacao,
			String idDocumentoTipo, String idLigacaoAguaSituacao,
			String idServicoTipo, String idCobrancaCriterio,
			String indicadorCronograma, String indicadorBoletim,
			String indicadorDebito, String numeroDiasVencimento,
			String descricaoCobrancaCriterio, String descricaoServicoTipo,
			String indicadorMetasCronograma, String indicadorOrdenamentoCronograma, String indicadorOrdenamentoEventual, 
			String indicadorDebitoInterfereAcao, String numeroDiasRemuneracaoTerceiro, String indicadorEfetuarAcaoCpfCnpjValido,
			Usuario usuarioLogado) {
		this.descricaoCobrancaAcao = descricaoCobrancaAcao;
		this.indicadorObrigatoriedade = indicadorObrigatoriedade;
		this.indicadorRepeticao = indicadorRepeticao;
		this.indicadorSuspensaoAbastecimento = indicadorSuspensaoAbastecimento;
		this.numeroDiasValidade = numeroDiasValidade;
		this.numeroDiasMinimoAcaoPrecedente = numeroDiasMinimoAcaoPrecedente;
		this.indicadorUso = indicadorUso;
		this.indicadorCobrancaDebACobrar = indicadorCobrancaDebACobrar;
		this.indicadorGeracaoTaxa = indicadorGeracaoTaxa;
		this.ordemRealizacao = ordemRealizacao;
		this.indicadorAcrescimoImpontualidade = indicadorAcrescimoImpontualidade;
		this.idCobrancaAcaoPredecessora = idCobrancaAcaoPredecessora;
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
		this.idDocumentoTipo = idDocumentoTipo;
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
		this.idServicoTipo = idServicoTipo;
		this.idCobrancaCriterio = idCobrancaCriterio;
		this.indicadorCronograma = indicadorCronograma;
		this.indicadorBoletim = indicadorBoletim;
		this.indicadorDebito = indicadorDebito;
		this.numeroDiasVencimento = numeroDiasVencimento;
		this.descricaoCobrancaCriterio = descricaoCobrancaCriterio;
		this.descricaoServicoTipo = descricaoServicoTipo;
		this.indicadorMetasCronograma = indicadorMetasCronograma;
		this.indicadorOrdenamentoCronograma = indicadorOrdenamentoCronograma;
		this.indicadorOrdenamentoEventual = indicadorOrdenamentoEventual;
		this.indicadorDebitoInterfereAcao = indicadorDebitoInterfereAcao;
		this.numeroDiasRemuneracaoTerceiro = numeroDiasRemuneracaoTerceiro;
		this.setIndicadorEfetuarAcaoCpfCnpjValido(indicadorEfetuarAcaoCpfCnpjValido);
		this.usuarioLogado = usuarioLogado;

	}
	
	/** full constructor */
	public CobrancaAcaoHelper(String descricaoCobrancaAcao,
			String indicadorObrigatoriedade, String indicadorRepeticao,
			String indicadorSuspensaoAbastecimento, String numeroDiasValidade,
			String numeroDiasMinimoAcaoPrecedente, String indicadorUso, 
			String indicadorCobrancaDebACobrar,
			String indicadorCreditosARealizar,
			String indicadorNotasPromissoria,
			String indicadorGeracaoTaxa, String ordemRealizacao,
			String indicadorAcrescimoImpontualidade,
			String idCobrancaAcaoPredecessora, 
			String idLigacaoEsgotoSituacao,
			String idDocumentoTipo, String idLigacaoAguaSituacao,
			String idServicoTipo, String idCobrancaCriterio,
			String indicadorCronograma, String indicadorBoletim,
			String indicadorDebito, String numeroDiasVencimento,
			String descricaoCobrancaCriterio, String descricaoServicoTipo,
			String indicadorMetasCronograma, String indicadorOrdenamentoCronograma, String indicadorOrdenamentoEventual,
			String indicadorDebitoInterfereAcao, String numeroDiasRemuneracaoTerceiro, String indicadorEfetuarAcaoCpfCnpjValido,
			Usuario usuarioLogado) {
		this.descricaoCobrancaAcao = descricaoCobrancaAcao;
		this.indicadorObrigatoriedade = indicadorObrigatoriedade;
		this.indicadorRepeticao = indicadorRepeticao;
		this.indicadorSuspensaoAbastecimento = indicadorSuspensaoAbastecimento;
		this.numeroDiasValidade = numeroDiasValidade;
		this.numeroDiasMinimoAcaoPrecedente = numeroDiasMinimoAcaoPrecedente;
		this.indicadorUso = indicadorUso;
		this.indicadorCobrancaDebACobrar = indicadorCobrancaDebACobrar;
		this.indicadorCreditosARealizar = indicadorCreditosARealizar;
		this.indicadorNotasPromissoria = indicadorNotasPromissoria;
		this.indicadorGeracaoTaxa = indicadorGeracaoTaxa;
		this.ordemRealizacao = ordemRealizacao;
		this.indicadorAcrescimoImpontualidade = indicadorAcrescimoImpontualidade;
		this.idCobrancaAcaoPredecessora = idCobrancaAcaoPredecessora;		
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
		this.idDocumentoTipo = idDocumentoTipo;
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
		this.idServicoTipo = idServicoTipo;
		this.idCobrancaCriterio = idCobrancaCriterio;
		this.indicadorCronograma = indicadorCronograma;
		this.indicadorBoletim = indicadorBoletim;
		this.indicadorDebito = indicadorDebito;
		this.numeroDiasVencimento = numeroDiasVencimento;
		this.descricaoCobrancaCriterio = descricaoCobrancaCriterio;
		this.descricaoServicoTipo = descricaoServicoTipo;
		this.indicadorMetasCronograma = indicadorMetasCronograma;
		this.indicadorOrdenamentoCronograma = indicadorOrdenamentoCronograma;
		this.indicadorOrdenamentoEventual = indicadorOrdenamentoEventual;
		this.indicadorDebitoInterfereAcao = indicadorDebitoInterfereAcao;
		this.numeroDiasRemuneracaoTerceiro = numeroDiasRemuneracaoTerceiro;
		this.setIndicadorEfetuarAcaoCpfCnpjValido(indicadorEfetuarAcaoCpfCnpjValido);
		this.usuarioLogado = usuarioLogado;
	}
	
	/** full constructor */
	public CobrancaAcaoHelper(
			String descricaoCobrancaAcao,
			String indicadorObrigatoriedade, 
			String indicadorRepeticao,
			String indicadorSuspensaoAbastecimento, 
			String numeroDiasValidade,
			String numeroDiasMinimoAcaoPrecedente, 
			String indicadorUso, 
			String indicadorCobrancaDebACobrar,
			String indicadorCreditosARealizar,
			String indicadorNotasPromissoria,
			String indicadorGeracaoTaxa, 
			String ordemRealizacao,
			String indicadorAcrescimoImpontualidade,
			String idCobrancaAcaoPredecessora,	
			String idCobrancaAcaoPredecessoraAlternativa,
			String idLigacaoEsgotoSituacao,
			String idDocumentoTipo, 
			String idLigacaoAguaSituacao,
			String idServicoTipo, 
			String idCobrancaCriterio,
			String indicadorCronograma, 
			String indicadorBoletim,
			String indicadorDebito, 
			String numeroDiasVencimento,
			String descricaoCobrancaCriterio, 
			String descricaoServicoTipo,
			String indicadorMetasCronograma, 
			String indicadorOrdenamentoCronograma, 
			String indicadorOrdenamentoEventual,
			String indicadorDebitoInterfereAcao, 
			String numeroDiasRemuneracaoTerceiro,
			String indicadorOrdenarMaiorValor, 
			String indicadorValidarItem, 
			String indicadorEfetuarAcaoCpfCnpjValido,
			Usuario usuarioLogado,
			String indicadorEnviarSMS,
		    String indicadorEnviarEmail,
		    String textoSMS,
		    String textoEmail,
		    String numeroMaximoTentativoEnvio) {
		this.descricaoCobrancaAcao = descricaoCobrancaAcao;
		this.indicadorObrigatoriedade = indicadorObrigatoriedade;
		this.indicadorRepeticao = indicadorRepeticao;
		this.indicadorSuspensaoAbastecimento = indicadorSuspensaoAbastecimento;
		this.numeroDiasValidade = numeroDiasValidade;
		this.numeroDiasMinimoAcaoPrecedente = numeroDiasMinimoAcaoPrecedente;
		this.indicadorUso = indicadorUso;
		this.indicadorCobrancaDebACobrar = indicadorCobrancaDebACobrar;
		this.indicadorCreditosARealizar = indicadorCreditosARealizar;
		this.indicadorNotasPromissoria = indicadorNotasPromissoria;
		this.indicadorGeracaoTaxa = indicadorGeracaoTaxa;
		this.ordemRealizacao = ordemRealizacao;
		this.indicadorAcrescimoImpontualidade = indicadorAcrescimoImpontualidade;
		this.idCobrancaAcaoPredecessora = idCobrancaAcaoPredecessora;
		this.idCobrancaAcaoPredecessoraAlternativa = idCobrancaAcaoPredecessoraAlternativa;
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
		this.idDocumentoTipo = idDocumentoTipo;
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
		this.idServicoTipo = idServicoTipo;
		this.idCobrancaCriterio = idCobrancaCriterio;
		this.indicadorCronograma = indicadorCronograma;
		this.indicadorBoletim = indicadorBoletim;
		this.indicadorDebito = indicadorDebito;
		this.numeroDiasVencimento = numeroDiasVencimento;
		this.descricaoCobrancaCriterio = descricaoCobrancaCriterio;
		this.descricaoServicoTipo = descricaoServicoTipo;
		this.indicadorMetasCronograma = indicadorMetasCronograma;
		this.indicadorOrdenamentoCronograma = indicadorOrdenamentoCronograma;
		this.indicadorOrdenamentoEventual = indicadorOrdenamentoEventual;
		this.indicadorDebitoInterfereAcao = indicadorDebitoInterfereAcao;
		this.numeroDiasRemuneracaoTerceiro = numeroDiasRemuneracaoTerceiro;
		this.indicadorOrdenarMaiorValor = indicadorOrdenarMaiorValor;
		this.indicadorValidarItem = indicadorValidarItem; 
		this.setIndicadorEfetuarAcaoCpfCnpjValido(indicadorEfetuarAcaoCpfCnpjValido);
		this.usuarioLogado = usuarioLogado;
		this.indicadorEnviarSMS = indicadorEnviarSMS;
		this.indicadorEnviarEmail = indicadorEnviarEmail;
		this.textoSMS = textoSMS;
		this.textoEmail= textoEmail;
		this.numeroMaximoTentativoEnvio = numeroMaximoTentativoEnvio;
	}

	
	
	
	/**
	 * @return Retorna o campo descricaoCobrancaAcao.
	 */
	public String getDescricaoCobrancaAcao() {
		return descricaoCobrancaAcao;
	}

	/**
	 * @param descricaoCobrancaAcao O descricaoCobrancaAcao a ser setado.
	 */
	public void setDescricaoCobrancaAcao(String descricaoCobrancaAcao) {
		this.descricaoCobrancaAcao = descricaoCobrancaAcao;
	}

	/**
	 * @return Retorna o campo descricaoCobrancaCriterio.
	 */
	public String getDescricaoCobrancaCriterio() {
		return descricaoCobrancaCriterio;
	}

	/**
	 * @param descricaoCobrancaCriterio O descricaoCobrancaCriterio a ser setado.
	 */
	public void setDescricaoCobrancaCriterio(String descricaoCobrancaCriterio) {
		this.descricaoCobrancaCriterio = descricaoCobrancaCriterio;
	}

	/**
	 * @return Retorna o campo descricaoServicoTipo.
	 */
	public String getDescricaoServicoTipo() {
		return descricaoServicoTipo;
	}

	/**
	 * @param descricaoServicoTipo O descricaoServicoTipo a ser setado.
	 */
	public void setDescricaoServicoTipo(String descricaoServicoTipo) {
		this.descricaoServicoTipo = descricaoServicoTipo;
	}

	/**
	 * @return Retorna o campo idCobrancaAcaoPredecessora.
	 */
	public String getIdCobrancaAcaoPredecessora() {
		return idCobrancaAcaoPredecessora;
	}

	/**
	 * @param idCobrancaAcaoPredecessora O idCobrancaAcaoPredecessora a ser setado.
	 */
	public void setIdCobrancaAcaoPredecessora(String idCobrancaAcaoPredecessora) {
		this.idCobrancaAcaoPredecessora = idCobrancaAcaoPredecessora;
	}

	/**
	 * @return Retorna o campo idCobrancaCriterio.
	 */
	public String getIdCobrancaCriterio() {
		return idCobrancaCriterio;
	}

	/**
	 * @param idCobrancaCriterio O idCobrancaCriterio a ser setado.
	 */
	public void setIdCobrancaCriterio(String idCobrancaCriterio) {
		this.idCobrancaCriterio = idCobrancaCriterio;
	}

	/**
	 * @return Retorna o campo idDocumentoTipo.
	 */
	public String getIdDocumentoTipo() {
		return idDocumentoTipo;
	}

	/**
	 * @param idDocumentoTipo O idDocumentoTipo a ser setado.
	 */
	public void setIdDocumentoTipo(String idDocumentoTipo) {
		this.idDocumentoTipo = idDocumentoTipo;
	}

	/**
	 * @return Retorna o campo idLigacaoAguaSituacao.
	 */
	public String getIdLigacaoAguaSituacao() {
		return idLigacaoAguaSituacao;
	}

	/**
	 * @param idLigacaoAguaSituacao O idLigacaoAguaSituacao a ser setado.
	 */
	public void setIdLigacaoAguaSituacao(String idLigacaoAguaSituacao) {
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
	}

	/**
	 * @return Retorna o campo idLigacaoEsgotoSituacao.
	 */
	public String getIdLigacaoEsgotoSituacao() {
		return idLigacaoEsgotoSituacao;
	}

	/**
	 * @param idLigacaoEsgotoSituacao O idLigacaoEsgotoSituacao a ser setado.
	 */
	public void setIdLigacaoEsgotoSituacao(String idLigacaoEsgotoSituacao) {
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
	}

	/**
	 * @return Retorna o campo idServicoTipo.
	 */
	public String getIdServicoTipo() {
		return idServicoTipo;
	}

	/**
	 * @param idServicoTipo O idServicoTipo a ser setado.
	 */
	public void setIdServicoTipo(String idServicoTipo) {
		this.idServicoTipo = idServicoTipo;
	}

	/**
	 * @return Retorna o campo indicadorAcrescimoImpontualidade.
	 */
	public String getIndicadorAcrescimoImpontualidade() {
		return indicadorAcrescimoImpontualidade;
	}

	/**
	 * @param indicadorAcrescimoImpontualidade O indicadorAcrescimoImpontualidade a ser setado.
	 */
	public void setIndicadorAcrescimoImpontualidade(
			String indicadorAcrescimoImpontualidade) {
		this.indicadorAcrescimoImpontualidade = indicadorAcrescimoImpontualidade;
	}

	/**
	 * @return Retorna o campo indicadorBoletim.
	 */
	public String getIndicadorBoletim() {
		return indicadorBoletim;
	}

	/**
	 * @param indicadorBoletim O indicadorBoletim a ser setado.
	 */
	public void setIndicadorBoletim(String indicadorBoletim) {
		this.indicadorBoletim = indicadorBoletim;
	}

	/**
	 * @return Retorna o campo indicadorCobrancaDebACobrar.
	 */
	public String getIndicadorCobrancaDebACobrar() {
		return indicadorCobrancaDebACobrar;
	}

	/**
	 * @param indicadorCobrancaDebACobrar O indicadorCobrancaDebACobrar a ser setado.
	 */
	public void setIndicadorCobrancaDebACobrar(String indicadorCobrancaDebACobrar) {
		this.indicadorCobrancaDebACobrar = indicadorCobrancaDebACobrar;
	}

	/**
	 * @return Retorna o campo indicadorCronograma.
	 */
	public String getIndicadorCronograma() {
		return indicadorCronograma;
	}

	/**
	 * @param indicadorCronograma O indicadorCronograma a ser setado.
	 */
	public void setIndicadorCronograma(String indicadorCronograma) {
		this.indicadorCronograma = indicadorCronograma;
	}

	/**
	 * @return Retorna o campo indicadorDebito.
	 */
	public String getIndicadorDebito() {
		return indicadorDebito;
	}

	/**
	 * @param indicadorDebito O indicadorDebito a ser setado.
	 */
	public void setIndicadorDebito(String indicadorDebito) {
		this.indicadorDebito = indicadorDebito;
	}

	/**
	 * @return Retorna o campo indicadorGeracaoTaxa.
	 */
	public String getIndicadorGeracaoTaxa() {
		return indicadorGeracaoTaxa;
	}

	/**
	 * @param indicadorGeracaoTaxa O indicadorGeracaoTaxa a ser setado.
	 */
	public void setIndicadorGeracaoTaxa(String indicadorGeracaoTaxa) {
		this.indicadorGeracaoTaxa = indicadorGeracaoTaxa;
	}

	/**
	 * @return Retorna o campo indicadorObrigatoriedade.
	 */
	public String getIndicadorObrigatoriedade() {
		return indicadorObrigatoriedade;
	}

	/**
	 * @param indicadorObrigatoriedade O indicadorObrigatoriedade a ser setado.
	 */
	public void setIndicadorObrigatoriedade(String indicadorObrigatoriedade) {
		this.indicadorObrigatoriedade = indicadorObrigatoriedade;
	}

	/**
	 * @return Retorna o campo indicadorRepeticao.
	 */
	public String getIndicadorRepeticao() {
		return indicadorRepeticao;
	}

	/**
	 * @param indicadorRepeticao O indicadorRepeticao a ser setado.
	 */
	public void setIndicadorRepeticao(String indicadorRepeticao) {
		this.indicadorRepeticao = indicadorRepeticao;
	}

	/**
	 * @return Retorna o campo indicadorSuspensaoAbastecimento.
	 */
	public String getIndicadorSuspensaoAbastecimento() {
		return indicadorSuspensaoAbastecimento;
	}

	/**
	 * @param indicadorSuspensaoAbastecimento O indicadorSuspensaoAbastecimento a ser setado.
	 */
	public void setIndicadorSuspensaoAbastecimento(
			String indicadorSuspensaoAbastecimento) {
		this.indicadorSuspensaoAbastecimento = indicadorSuspensaoAbastecimento;
	}

	/**
	 * @return Retorna o campo indicadorUso.
	 */
	public String getIndicadorUso() {
		return indicadorUso;
	}

	/**
	 * @param indicadorUso O indicadorUso a ser setado.
	 */
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	/**
	 * @return Retorna o campo numeroDiasMinimoAcaoPrecedente.
	 */
	public String getNumeroDiasMinimoAcaoPrecedente() {
		return numeroDiasMinimoAcaoPrecedente;
	}

	/**
	 * @param numeroDiasMinimoAcaoPrecedente O numeroDiasMinimoAcaoPrecedente a ser setado.
	 */
	public void setNumeroDiasMinimoAcaoPrecedente(
			String numeroDiasMinimoAcaoPrecedente) {
		this.numeroDiasMinimoAcaoPrecedente = numeroDiasMinimoAcaoPrecedente;
	}

	/**
	 * @return Retorna o campo numeroDiasValidade.
	 */
	public String getNumeroDiasValidade() {
		return numeroDiasValidade;
	}

	/**
	 * @param numeroDiasValidade O numeroDiasValidade a ser setado.
	 */
	public void setNumeroDiasValidade(String numeroDiasValidade) {
		this.numeroDiasValidade = numeroDiasValidade;
	}

	/**
	 * @return Retorna o campo numeroDiasVencimento.
	 */
	public String getNumeroDiasVencimento() {
		return numeroDiasVencimento;
	}

	/**
	 * @param numeroDiasVencimento O numeroDiasVencimento a ser setado.
	 */
	public void setNumeroDiasVencimento(String numeroDiasVencimento) {
		this.numeroDiasVencimento = numeroDiasVencimento;
	}

	/**
	 * @return Retorna o campo ordemRealizacao.
	 */
	public String getOrdemRealizacao() {
		return ordemRealizacao;
	}

	/**
	 * @param ordemRealizacao O ordemRealizacao a ser setado.
	 */
	public void setOrdemRealizacao(String ordemRealizacao) {
		this.ordemRealizacao = ordemRealizacao;
	}

	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public String getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(String ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/**
	 * @return Retorna o campo usuarioLogado.
	 */
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	/**
	 * @param usuarioLogado O usuarioLogado a ser setado.
	 */
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	/**
	 * @return Retorna o campo indicadorDebitoInterfereAcao.
	 */
	public String getIndicadorDebitoInterfereAcao() {
		return indicadorDebitoInterfereAcao;
	}

	/**
	 * @param indicadorDebitoInterfereAcao O indicadorDebitoInterfereAcao a ser setado.
	 */
	public void setIndicadorDebitoInterfereAcao(String indicadorDebitoInterfereAcao) {
		this.indicadorDebitoInterfereAcao = indicadorDebitoInterfereAcao;
	}

	/**
	 * @return Retorna o campo indicadorMetasCronograma.
	 */
	public String getIndicadorMetasCronograma() {
		return indicadorMetasCronograma;
	}

	/**
	 * @param indicadorMetasCronograma O indicadorMetasCronograma a ser setado.
	 */
	public void setIndicadorMetasCronograma(String indicadorMetasCronograma) {
		this.indicadorMetasCronograma = indicadorMetasCronograma;
	}

	/**
	 * @return Retorna o campo indicadorOrdenamentoCronograma.
	 */
	public String getIndicadorOrdenamentoCronograma() {
		return indicadorOrdenamentoCronograma;
	}

	/**
	 * @param indicadorOrdenamentoCronograma O indicadorOrdenamentoCronograma a ser setado.
	 */
	public void setIndicadorOrdenamentoCronograma(
			String indicadorOrdenamentoCronograma) {
		this.indicadorOrdenamentoCronograma = indicadorOrdenamentoCronograma;
	}

	/**
	 * @return Retorna o campo indicadorOrdenamentoEventual.
	 */
	public String getIndicadorOrdenamentoEventual() {
		return indicadorOrdenamentoEventual;
	}

	/**
	 * @param indicadorOrdenamentoEventual O indicadorOrdenamentoEventual a ser setado.
	 */
	public void setIndicadorOrdenamentoEventual(String indicadorOrdenamentoEventual) {
		this.indicadorOrdenamentoEventual = indicadorOrdenamentoEventual;
	}

	/**
	 * @return Retorna o campo numeroDiasRemuneracaoTerceiro.
	 */
	public String getNumeroDiasRemuneracaoTerceiro() {
		return numeroDiasRemuneracaoTerceiro;
	}

	/**
	 * @param numeroDiasRemuneracaoTerceiro O numeroDiasRemuneracaoTerceiro a ser setado.
	 */
	public void setNumeroDiasRemuneracaoTerceiro(
			String numeroDiasRemuneracaoTerceiro) {
		this.numeroDiasRemuneracaoTerceiro = numeroDiasRemuneracaoTerceiro;
	}

	public String getIndicadorCreditosARealizar() {
		return indicadorCreditosARealizar;
	}

	public void setIndicadorCreditosARealizar(String indicadorCreditosARealizar) {
		this.indicadorCreditosARealizar = indicadorCreditosARealizar;
	}

	public String getIndicadorNotasPromissoria() {
		return indicadorNotasPromissoria;
	}

	public void setIndicadorNotasPromissoria(String indicadorNotasPromissoria) {
		this.indicadorNotasPromissoria = indicadorNotasPromissoria;
	}

	public String getIndicadorOrdenarMaiorValor() {
		return indicadorOrdenarMaiorValor;
	}

	public void setIndicadorOrdenarMaiorValor(String indicadorOrdenarMaiorValor) {
		this.indicadorOrdenarMaiorValor = indicadorOrdenarMaiorValor;
	}

	public String getIndicadorValidarItem() {
		return indicadorValidarItem;
	}

	public void setIndicadorValidarItem(String indicadorValidarItem) {
		this.indicadorValidarItem = indicadorValidarItem;
	}

	public String getIndicadorEfetuarAcaoCpfCnpjValido() {
		return indicadorEfetuarAcaoCpfCnpjValido;
	}

	public void setIndicadorEfetuarAcaoCpfCnpjValido(
			String indicadorEfetuarAcaoCpfCnpjValido) {
		this.indicadorEfetuarAcaoCpfCnpjValido = indicadorEfetuarAcaoCpfCnpjValido;
	}
	

	public String getTextoPersonalizado() {
		return textoPersonalizado;
	}

	public void setTextoPersonalizado(String textoPersonalizado) {
		this.textoPersonalizado = textoPersonalizado;
	}

	public String getIndicadorEnviarSMS() {
		return indicadorEnviarSMS;
	}

	public void setIndicadorEnviarSMS(String indicadorEnviarSMS) {
		this.indicadorEnviarSMS = indicadorEnviarSMS;
	}

	public String getIndicadorEnviarEmail() {
		return indicadorEnviarEmail;
	}

	public void setIndicadorEnviarEmail(String indicadorEnviarEmail) {
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

	public String getNumeroMaximoTentativoEnvio() {
		return numeroMaximoTentativoEnvio;
	}

	public void setNumeroMaximoTentativoEnvio(String numeroMaximoTentativoEnvio) {
		this.numeroMaximoTentativoEnvio = numeroMaximoTentativoEnvio;
	}

	public String getIdCobrancaAcaoPredecessoraAlternativa() {
		return idCobrancaAcaoPredecessoraAlternativa;
	}

	public void setIdCobrancaAcaoPredecessoraAlternativa(String idCobrancaAcaoPredecessoraAlternativa) {
		this.idCobrancaAcaoPredecessoraAlternativa = idCobrancaAcaoPredecessoraAlternativa;
	}
}