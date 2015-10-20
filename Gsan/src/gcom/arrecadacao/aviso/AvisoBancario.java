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
package gcom.arrecadacao.aviso;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.ArrecadadorMovimento;
import gcom.arrecadacao.FiltroAvisoBancario;
import gcom.arrecadacao.banco.ContaBancaria;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class AvisoBancario extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	
	public static final int AVISO_BANCARIO_ATUALIZAR = 3; //Operacao.OPERACAO_AVISO_BANCARIO_ATUALIZAR
	public static final int AVISO_BANCARIO_INSERIR = 4; //Operacao.OPERACAO_AVISO_BANCARIO_INSERIR

	public static final int ATUALIZAR_AVISO_BANCARIO = 1714; //Operacao.OPERACAO_ATUALIZAR_AVISO_BANCARIO
	public static final int AVISO_BANCARIO_REMOVER = 501; //Operacao.OPERACAO_REMOVER_AVISO_BANCARIO
	
	/** identifier field */
	private Integer id;

	/** persistent field */
	@ControleAlteracao(funcionalidade={ATUALIZAR_AVISO_BANCARIO, AVISO_BANCARIO_INSERIR, AVISO_BANCARIO_REMOVER})
	private Date dataLancamento;

	/** persistent field */
	private BigDecimal valorContabilizado;

	/** persistent field */
	private int anoMesReferenciaArrecadacao;

	/** persistent field */
	private Short indicadorCreditoDebito;

	/** persistent field */
	@ControleAlteracao(funcionalidade={ATUALIZAR_AVISO_BANCARIO, AVISO_BANCARIO_INSERIR, AVISO_BANCARIO_REMOVER})
	private int numeroDocumento;

	/** nullable persistent field */
	//private BigDecimal valorDevolucao;

	/** nullable persistent field */
	//private BigDecimal valorArrecadacao;

	/** nullable persistent field */
	private Short numeroSequencial;

	/** nullable persistent field */
	private BigDecimal valorRealizado;

	/** nullable persistent field */
	private Date dataPrevista;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATUALIZAR_AVISO_BANCARIO, AVISO_BANCARIO_INSERIR, AVISO_BANCARIO_REMOVER})
	private Date dataRealizada;

	/** nullable persistent field */
	//private BigDecimal valorPrevisto;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private ContaBancaria contaBancaria;

	/** persistent field */
	@ControleAlteracao(value=FiltroAvisoBancario.ARRECADADOR, funcionalidade={AVISO_BANCARIO_INSERIR, AVISO_BANCARIO_REMOVER})
	private Arrecadador arrecadador;

	/** persistent field */
	private ArrecadadorMovimento arrecadadorMovimento;
	
	/** nullable persistent field */
	private BigDecimal valorArrecadacaoCalculado;

	/** nullable persistent field */
	private BigDecimal valorDevolucaoCalculado;
	
	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATUALIZAR_AVISO_BANCARIO, AVISO_BANCARIO_INSERIR, AVISO_BANCARIO_REMOVER})
	private BigDecimal valorArrecadacaoInformado;
	
	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATUALIZAR_AVISO_BANCARIO, AVISO_BANCARIO_INSERIR, AVISO_BANCARIO_REMOVER})
	private BigDecimal valorDevolucaoInformado;
	
	/** persistent field */
	@ControleAlteracao(value=FiltroAvisoBancario.ARRECADACAO_FORMA, funcionalidade={ATUALIZAR_AVISO_BANCARIO, AVISO_BANCARIO_INSERIR, AVISO_BANCARIO_REMOVER})
	private ArrecadacaoForma arrecadacaoForma;
	
	/**
	 * Description of the Field
	 */
	public final static Short INDICADOR_CREDITO = new Short("1");
	public final static String CREDITO = "CR�DITO";
	public final static String SIGLA_CREDITO = "C";

	/**
	 * Description of the Field
	 */
	public final static Short INDICADOR_DEBITO = new Short("2");
	public final static String DEBITO = "D�BITO";
	public final static String SIGLA_DEBITO = "D";
	

	/** full constructor */
	public AvisoBancario(Date dataLancamento, BigDecimal valorContabilizado,
			int anoMesReferenciaArrecadacao, Short indicadorCreditoDebito,
			int numeroDocumento, BigDecimal valorDevolucao,
			BigDecimal valorArrecadacao, Short numeroSequencial,
			BigDecimal valorRealizado, Date dataPrevista, Date dataRealizada,
			BigDecimal valorPrevisto, Date ultimaAlteracao,
			ContaBancaria contaBancaria, Arrecadador arrecadador,
			ArrecadadorMovimento arrecadadorMovimento) {
		this.dataLancamento = dataLancamento;
		this.valorContabilizado = valorContabilizado;
		this.anoMesReferenciaArrecadacao = anoMesReferenciaArrecadacao;
		this.indicadorCreditoDebito = indicadorCreditoDebito;
		this.numeroDocumento = numeroDocumento;
		/*this.valorDevolucao = valorDevolucao;
		this.valorArrecadacao = valorArrecadacao;*/
		this.numeroSequencial = numeroSequencial;
		this.valorRealizado = valorRealizado;
		this.dataPrevista = dataPrevista;
		this.dataRealizada = dataRealizada;
		//this.valorPrevisto = valorPrevisto;
		this.ultimaAlteracao = ultimaAlteracao;
		this.contaBancaria = contaBancaria;
		this.arrecadador = arrecadador;
		this.arrecadadorMovimento = arrecadadorMovimento;
	}

	/** default constructor */
	public AvisoBancario() {
	}

	/** minimal constructor */
	public AvisoBancario(Date dataLancamento, BigDecimal valorContabilizado,
			int anoMesReferenciaArrecadacao, Short indicadorCreditoDebito,
			int numeroDocumento, ContaBancaria contaBancaria,
			Arrecadador arrecadador, ArrecadadorMovimento arrecadadorMovimento) {
		this.dataLancamento = dataLancamento;
		this.valorContabilizado = valorContabilizado;
		this.anoMesReferenciaArrecadacao = anoMesReferenciaArrecadacao;
		this.indicadorCreditoDebito = indicadorCreditoDebito;
		this.numeroDocumento = numeroDocumento;
		this.contaBancaria = contaBancaria;
		this.arrecadador = arrecadador;
		this.arrecadadorMovimento = arrecadadorMovimento;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataLancamento() {
		return this.dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public BigDecimal getValorContabilizado() {
		return this.valorContabilizado;
	}

	public void setValorContabilizado(BigDecimal valorContabilizado) {
		this.valorContabilizado = valorContabilizado;
	}

	public int getAnoMesReferenciaArrecadacao() {
		return this.anoMesReferenciaArrecadacao;
	}

	public void setAnoMesReferenciaArrecadacao(int anoMesReferenciaArrecadacao) {
		this.anoMesReferenciaArrecadacao = anoMesReferenciaArrecadacao;
	}

	public Short getIndicadorCreditoDebito() {
		return this.indicadorCreditoDebito;
	}

	public void setIndicadorCreditoDebito(Short indicadorCreditoDebito) {
		this.indicadorCreditoDebito = indicadorCreditoDebito;
	}

	public int getNumeroDocumento() {
		return this.numeroDocumento;
	}

	public void setNumeroDocumento(int numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/*public BigDecimal getValorDevolucao() {
		return this.valorDevolucao;
	}

	public void setValorDevolucao(BigDecimal valorDevolucao) {
		this.valorDevolucao = valorDevolucao;
	}

	public BigDecimal getValorArrecadacao() {
		return this.valorArrecadacao;
	}

	public void setValorArrecadacao(BigDecimal valorArrecadacao) {
		this.valorArrecadacao = valorArrecadacao;
	}*/

	public Short getNumeroSequencial() {
		return this.numeroSequencial;
	}

	public void setNumeroSequencial(Short numeroSequencial) {
		this.numeroSequencial = numeroSequencial;
	}

	public BigDecimal getValorRealizado() {
		return this.valorRealizado;
	}

	public void setValorRealizado(BigDecimal valorRealizado) {
		this.valorRealizado = valorRealizado;
	}

	public Date getDataPrevista() {
		return this.dataPrevista;
	}

	public void setDataPrevista(Date dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	public Date getDataRealizada() {
		return this.dataRealizada;
	}

	public void setDataRealizada(Date dataRealizada) {
		this.dataRealizada = dataRealizada;
	}

	/*public BigDecimal getValorPrevisto() {
		return this.valorPrevisto;
	}

	public void setValorPrevisto(BigDecimal valorPrevisto) {
		this.valorPrevisto = valorPrevisto;
	}*/

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public ContaBancaria getContaBancaria() {
		return this.contaBancaria;
	}

	public void setContaBancaria(ContaBancaria contaBancaria) {
		this.contaBancaria = contaBancaria;
	}

	public Arrecadador getArrecadador() {
		return this.arrecadador;
	}

	public void setArrecadador(Arrecadador arrecadador) {
		this.arrecadador = arrecadador;
	}

	public ArrecadadorMovimento getArrecadadorMovimento() {
		return this.arrecadadorMovimento;
	}

	public void setArrecadadorMovimento(
			ArrecadadorMovimento arrecadadorMovimento) {
		this.arrecadadorMovimento = arrecadadorMovimento;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public Filtro retornaFiltro() {
		FiltroAvisoBancario filtroAvisoBancario  = new FiltroAvisoBancario();
        filtroAvisoBancario.adicionarParametro(new ParametroSimples(FiltroAvisoBancario.ID, this.getId()));
        filtroAvisoBancario.adicionarCaminhoParaCarregamentoEntidade(FiltroAvisoBancario.ARRECADADOR);
        filtroAvisoBancario.adicionarCaminhoParaCarregamentoEntidade(FiltroAvisoBancario.CONTA_BANCARIA);
		return filtroAvisoBancario ;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] ids = {"id"};
		return ids;
	}

	/**
	 * @return Retorna o campo valorArrecadacaoCalculado.
	 */
	public BigDecimal getValorArrecadacaoCalculado() {
		return valorArrecadacaoCalculado;
	}

	/**
	 * @param valorArrecadacaoCalculado O valorArrecadacaoCalculado a ser setado.
	 */
	public void setValorArrecadacaoCalculado(BigDecimal valorArrecadacaoCalculado) {
		this.valorArrecadacaoCalculado = valorArrecadacaoCalculado;
	}

	/**
	 * @return Retorna o campo valorArrecadacaoInformado.
	 */
	public BigDecimal getValorArrecadacaoInformado() {
		return valorArrecadacaoInformado;
	}

	/**
	 * @param valorArrecadacaoInformado O valorArrecadacaoInformado a ser setado.
	 */
	public void setValorArrecadacaoInformado(BigDecimal valorArrecadacaoInformado) {
		this.valorArrecadacaoInformado = valorArrecadacaoInformado;
	}

	/**
	 * @return Retorna o campo valorDevolucaoCalculado.
	 */
	public BigDecimal getValorDevolucaoCalculado() {
		return valorDevolucaoCalculado;
	}

	/**
	 * @param valorDevolucaoCalculado O valorDevolucaoCalculado a ser setado.
	 */
	public void setValorDevolucaoCalculado(BigDecimal valorDevolucaoCalculado) {
		this.valorDevolucaoCalculado = valorDevolucaoCalculado;
	}

	/**
	 * @return Retorna o campo valorDevolucaoInformado.
	 */
	public BigDecimal getValorDevolucaoInformado() {
		return valorDevolucaoInformado;
	}

	/**
	 * @param valorDevolucaoInformado O valorDevolucaoInformado a ser setado.
	 */
	public void setValorDevolucaoInformado(BigDecimal valorDevolucaoInformado) {
		this.valorDevolucaoInformado = valorDevolucaoInformado;
	}

	public ArrecadacaoForma getArrecadacaoForma() {
		return arrecadacaoForma;
	}

	public void setArrecadacaoForma(ArrecadacaoForma arrecadacaoForma) {
		this.arrecadacaoForma = arrecadacaoForma;
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getArrecadador().getCodigoAgente() + " - " + Util.formatarData(getDataLancamento());
	}
	
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []labels = { "numeroDocumento",
							"valorArrecadacaoInformado"};
		return labels;		
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []labels = {"Num. Documento",
						   "Valor da Arrecadacao"};
		return labels;		
	}
	
}
