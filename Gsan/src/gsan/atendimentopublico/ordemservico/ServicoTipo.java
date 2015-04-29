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
package gsan.atendimentopublico.ordemservico;

import gsan.faturamento.credito.CreditoTipo;
import gsan.faturamento.debito.DebitoTipo;
import gsan.interceptor.ControleAlteracao;
import gsan.interceptor.ObjetoTransacao;
import gsan.util.filtro.Filtro;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class ServicoTipo extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;
	public static final int ATUALIZAR_IMPORTANCIA_TIPO_SERVICO = 1819;

	public Filtro retornaFiltro() {
		FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();

		filtroServicoTipo.adicionarParametro(new ParametroSimples(	FiltroServicoTipo.ID,
																	this.getId()));
		filtroServicoTipo.adicionarCaminhoParaCarregamentoEntidade("programaCalibragem");
		return filtroServicoTipo;
	}

	public final static int TIPO_LIGACAO_AGUA = 619;

	public final static int TIPO_LIGACAO_ESGOTO = 726;

	public final static int TIPO_CONFIRMAR_DADOS_LIGACAO_ESGOTO = 254;

	public final static int TIPO_CONFIRMAR_DADOS_LIGACAO_AGUA = 255;

	public final static int TIPO_CONFIRMAR_DADOS_CORTE_LIGACAO_AGUA = 252;

	public final static int TIPO_CORTE_LIGACAO_AGUA = 242;

	public final static int TIPO_CONFIRMAR_DADOS_SUPRESSAO_LIGACAO_AGUA = 253;

	public final static int TIPO_SUPRESSAO_LIGACAO_AGUA = 245;

	public final static int TIPO_RESTABELECIMENTO_LIGACAO_AGUA = 244;

	public final static int TIPO_RELIGACAO_AGUA = 243;

	public final static int TIPO_CORTE_ADMINISTRATIVO_LIGACAO_AGUA = 241;

	public final static int TIPO_CALCULAR_CONSUMO_MINIMO_AGUA = 690;

	// public final static int TIPO_SUBSTITUICAO_HIDROMETRO_POCO = 13;

	// public final static int TIPO_SUBSTITUICAO_HIDROMETRO_LIGACAO_AGUA = 14;

	public final static int TIPO_RETIRADA_HIDROMETRO = 307;

	// public final static int TIPO_RETIRADA_HIDROMETRO_POCO = 16;

	public final static int TIPO_REMANEJAMENTO_HIDROMETRO_LIGACAO_AGUA = 308;

	public final static int TIPO_REMANEJAMENTO_HIDROMETRO_POCO = 18;

	// public final static int TIPO_INSTALACAO_HIDROMETRO_POCO = 19;

	// public final static int TIPO_INSTALACAO_HIDROMETRO_LIGACAO_AGUA = 20;

	public final static int TIPO_EFETUAR_INSTALACAO_HIDROMETRO = 304;

	public final static int TIPO_EFETUAR_INSTALACAO_HIDROMETRO_POCO = 305;

	public final static int TIPO_CONFIRMAR_DADOS_INSTALACAO_HIDROMETRO = 313;

	public final static int TIPO_EFETUAR_SUBSTITUICAO_HIDROMETRO = 310;

	public final static int TIPO_EFETUAR_SUBSTITUICAO_HIDROMETRO_POCO = 1006;

	public final static int TIPO_EFETUAR_REMOCAO_HIDROMETRO = 308;

	public final static int TIPO_EFETUAR_REMOCAO_HIDROMETRO_POCO = 1005;

	public final static int TIPO_CONFIRMAR_DADOS_SUBSTITUICAO_HIDROMETRO = 314;

	public final static int TIPO_CALCULAR_VOLUME_MINIMO_ESGOTO = 755;

	public final static short INDICADOR_PAVIMENTO_SIM = 1;

	public final static String INDICADOR_PAVIMENTO_NAO = "2";

	public final static short INDICADOR_ATUALIZA_COMERCIAL_SIM = 1;

	public final static short INDICADOR_ATUALIZA_COMERCIAL_NAO = 2;

	public final static int TIPO_TAMPONAMENTO_LIGACAO_ESGOTO = 743;

	public final static int TIPO_DESATIVACAO_LIGACAO_ESGOTO = 752;

	public final static int TIPO_RESTABELECIMENTO_LIGACAO_ESGOTO = 753;

	public final static int TIPO_REATIVACAO_LIGACAO_ESGOTO = 754;
	
	public final static int TIPO_REATIVACAO_LIGACAO_RAMAL_ESGOTO = 703;

	public final static int TIPO_LIGACAO_AGUA_COM_INSTALACAO_HIDROMETRO = 1001;

	public final static String INDICADOR_VISTORIA_SERVICO_TIPO_NAO = "2";

	public final static int TIPO_ALTERACAO_SITUACAO_LIGACAO = 1004;

	public final static int TIPO_RESTABELECIMENTO_LIGACAO_AGUA_COM_INSTALACAO_HIDROMETRO = 1005;

	public final static int TIPO_RELIGACAO_AGUA_COM_INSTALACAO_HIDROMETRO = 1006;

	public final static int TIPO_ORDEM_SERVICO_FISCALIZACAO = 33;

	//alterado para atender a RM 6466
	public final static int TIPO_ORDEM_SERVICO_FISCALIZACAO_ONLINE = 210;
	
	public final static int TIPO_FISCALIZACAO = 705;

	public final static int TIPO_INSPECAO_ANORMALIDADE = 350;

	public final static int TIPO_SUBSTITUICAO_COM_REMOCAO = 351;

	// --------------------------------------------------- [YTS - 30/05/2008]

	public final static short INDICADOR_PAVIMENTO_CALCADA_SIM = 1;

	public final static short INDICADOR_PAVIMENTO_CALCADA_NAO = 2;

	public final static short INDICADOR_PAVIMENTO_RUA_SIM = 1;

	public final static short INDICADOR_PAVIMENTO_RUA_NAO = 2;

	public final static int FISCALIZACAO_DA_ANALISE = 1007;

	// ---------------------------------------------------------------------

	// public final static short INDICADOR_EMPRESA_COBRANCA_SIM = 1;

	// public final static short INDICADOR_EMPRESA_COBRANCA_NAO = 2;

	/** identifier field */
	private Integer id;

	/** persistent field */
	private String descricao;

	/** persistent field */
	private String descricaoAbreviada;

	/** persistent field */
	private BigDecimal valor;

	/** persistent field */
	// private short prioridade;
	/** persistent field */
	private short indicadorPavimento;

	/** persistent field */
	private short indicadorAtualizaComercial;

	/** persistent field */
	private short indicadorTerceirizado;

	/** persistent field */
	private String codigoServicoTipo;

	/** persistent field */
	private short tempoMedioExecucao;

	/** persistent field */
	private short indicadorUso;

	/** persistent field */
	private short indicadorVistoria;

	/** persistent field */
	private short indicadorPermiteAlterarValor;

	/** persistent field */
	private short indicadorIncluirDebito;

	/** persistent field */
	private short indicadorCobrarJuros;

	/** persistent field */
	private short indicadorFiscalizacaoInfracao;

	/** persistent field */
	private Short indicadorPavimentoRua;

	/** persistent field */
	private Short indicadorPavimentoCalcada;

	/** persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private gsan.atendimentopublico.ordemservico.ServicoTipoReferencia servicoTipoReferencia;

	/** persistent field */
	private CreditoTipo creditoTipo;

	/** persistent field */
	private gsan.atendimentopublico.ordemservico.ServicoPerfilTipo servicoPerfilTipo;

	/** persistent field */
	private gsan.atendimentopublico.ordemservico.ServicoTipoSubgrupo servicoTipoSubgrupo;

	/** persistent field */
	private gsan.atendimentopublico.ordemservico.ServicoTipoPrioridade servicoTipoPrioridade;

	/** persistent field */
	private DebitoTipo debitoTipo;

	private Collection servicoTipoAtividades;

	private Collection servicoTipoMateriais;

	private Integer constanteFuncionalidadeTipoServico;

	/** persistent field */
	private Short indicativoTipoSevicoEconomias;

	private Short indicadorBoletim;

	private Short indicadorAtividade;

	public final static short INDICADOR_VISTORIA_SIM = 1;

	private short indicadorEmpresaCobranca;

	private short indicadorInspecaoAnormalidade;

	private short indicadorProgramacaoAutomatica;

	private Short indicadorEnvioPesquisaSatisfacao;

	private Short indicadorServicoOrdemSeletiva;

	private Short indicadorGerarOSInspecaoAnormalidade;

	private Short indicadorEncAutomaticoRAQndEncOS;

	private Short indicadorCorrecaoAnormalidade;

	private Collection servicoTipoMotivoEncerramentos;

	@ControleAlteracao(value = FiltroTipoServico.OS_PROGRAMA_CALIBRAGEM, funcionalidade = {
																							ATUALIZAR_IMPORTANCIA_TIPO_SERVICO,
																							OrdemServico.ATRIBUTOS_MANTER_ORDEM_SERVICO })
	private OSProgramacaoCalibragem programaCalibragem;

	/** full constructor */
	public ServicoTipo(
			String descricao, String descricaoAbreviada, BigDecimal valor, short prioridade, short indicadorPavimento,
			short indicadorAtualizaComercial, short indicadorTerceirizado, String codigoServicoTipo,
			short tempoMedioExecucao, short indicadorUso, Date ultimaAlteracao,
			gsan.atendimentopublico.ordemservico.ServicoTipoReferencia servicoTipoReferencia, CreditoTipo creditoTipo,
			gsan.atendimentopublico.ordemservico.ServicoPerfilTipo servicoPerfilTipo,
			gsan.atendimentopublico.ordemservico.ServicoTipoSubgrupo servicoTipoSubgrupo,
			gsan.atendimentopublico.ordemservico.ServicoTipoPrioridade servicoTipoPrioridade, DebitoTipo debitoTipo,
			Integer constanteFuncionalidadeTipoServico, short indicadorEmpresaCobranca, short indicadorInspecaoAnormalidade,
			short indicadorProgramacaoAutomatica) {
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.valor = valor;
		// this.prioridade = prioridade;
		this.indicadorPavimento = indicadorPavimento;
		this.indicadorAtualizaComercial = indicadorAtualizaComercial;
		this.indicadorTerceirizado = indicadorTerceirizado;
		this.codigoServicoTipo = codigoServicoTipo;
		this.tempoMedioExecucao = tempoMedioExecucao;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.servicoTipoReferencia = servicoTipoReferencia;
		this.creditoTipo = creditoTipo;
		this.servicoPerfilTipo = servicoPerfilTipo;
		this.servicoTipoSubgrupo = servicoTipoSubgrupo;
		this.servicoTipoPrioridade = servicoTipoPrioridade;
		this.debitoTipo = debitoTipo;
		this.constanteFuncionalidadeTipoServico = constanteFuncionalidadeTipoServico;
		this.indicadorEmpresaCobranca = indicadorEmpresaCobranca;
		this.indicadorInspecaoAnormalidade = indicadorInspecaoAnormalidade;
		this.indicadorProgramacaoAutomatica = indicadorProgramacaoAutomatica;
	}

	/** default constructor */
	public ServicoTipo() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OSProgramacaoCalibragem getProgramaCalibragem() {
		return programaCalibragem;
	}

	public void setProgramaCalibragem(OSProgramacaoCalibragem programaCalibragem) {
		this.programaCalibragem = programaCalibragem;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoAbreviada() {
		return this.descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	// public short getPrioridade() {
	// return this.prioridade;
	// }
	//
	// public void setPrioridade(short prioridade) {
	// this.prioridade = prioridade;
	// }

	public short getIndicadorPavimento() {
		return this.indicadorPavimento;
	}

	public void setIndicadorPavimento(short indicadorPavimento) {
		this.indicadorPavimento = indicadorPavimento;
	}

	public short getIndicadorAtualizaComercial() {
		return this.indicadorAtualizaComercial;
	}

	public void setIndicadorAtualizaComercial(short indicadorAtualizaComercial) {
		this.indicadorAtualizaComercial = indicadorAtualizaComercial;
	}

	public short getIndicadorTerceirizado() {
		return this.indicadorTerceirizado;
	}

	public void setIndicadorTerceirizado(short indicadorTerceirizado) {
		this.indicadorTerceirizado = indicadorTerceirizado;
	}

	public String getCodigoServicoTipo() {
		return this.codigoServicoTipo;
	}

	public void setCodigoServicoTipo(String codigoServicoTipo) {
		this.codigoServicoTipo = codigoServicoTipo;
	}

	public short getTempoMedioExecucao() {
		return this.tempoMedioExecucao;
	}

	public void setTempoMedioExecucao(short tempoMedioExecucao) {
		this.tempoMedioExecucao = tempoMedioExecucao;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public gsan.atendimentopublico.ordemservico.ServicoTipoReferencia getServicoTipoReferencia() {
		return this.servicoTipoReferencia;
	}

	public void setServicoTipoReferencia(gsan.atendimentopublico.ordemservico.ServicoTipoReferencia servicoTipoReferencia) {
		this.servicoTipoReferencia = servicoTipoReferencia;
	}

	public CreditoTipo getCreditoTipo() {
		return this.creditoTipo;
	}

	public void setCreditoTipo(CreditoTipo creditoTipo) {
		this.creditoTipo = creditoTipo;
	}

	public gsan.atendimentopublico.ordemservico.ServicoPerfilTipo getServicoPerfilTipo() {
		return this.servicoPerfilTipo;
	}

	public void setServicoPerfilTipo(gsan.atendimentopublico.ordemservico.ServicoPerfilTipo servicoPerfilTipo) {
		this.servicoPerfilTipo = servicoPerfilTipo;
	}

	public gsan.atendimentopublico.ordemservico.ServicoTipoSubgrupo getServicoTipoSubgrupo() {
		return this.servicoTipoSubgrupo;
	}

	public void setServicoTipoSubgrupo(gsan.atendimentopublico.ordemservico.ServicoTipoSubgrupo servicoTipoSubgrupo) {
		this.servicoTipoSubgrupo = servicoTipoSubgrupo;
	}

	public gsan.atendimentopublico.ordemservico.ServicoTipoPrioridade getServicoTipoPrioridade() {
		return this.servicoTipoPrioridade;
	}

	public void setServicoTipoPrioridade(gsan.atendimentopublico.ordemservico.ServicoTipoPrioridade servicoTipoPrioridade) {
		this.servicoTipoPrioridade = servicoTipoPrioridade;
	}

	public DebitoTipo getDebitoTipo() {
		return this.debitoTipo;
	}

	public void setDebitoTipo(DebitoTipo debitoTipo) {
		this.debitoTipo = debitoTipo;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Collection getServicoTipoAtividades() {
		return servicoTipoAtividades;
	}

	public void setServicoTipoAtividades(Collection servicoTipoAtividades) {
		this.servicoTipoAtividades = servicoTipoAtividades;
	}

	public Collection getServicoTipoMateriais() {
		return servicoTipoMateriais;
	}

	public void setServicoTipoMateriais(Collection servicoTipoMateriais) {
		this.servicoTipoMateriais = servicoTipoMateriais;
	}

	public short getIndicadorFiscalizacaoInfracao() {
		return indicadorFiscalizacaoInfracao;
	}

	public void setIndicadorFiscalizacaoInfracao(short indicadorFiscalizacaoInfracao) {
		this.indicadorFiscalizacaoInfracao = indicadorFiscalizacaoInfracao;
	}

	public short getIndicadorVistoria() {
		return indicadorVistoria;
	}

	public void setIndicadorVistoria(short indicadorVistoria) {
		this.indicadorVistoria = indicadorVistoria;
	}

	public short getIndicadorPermiteAlterarValor() {
		return indicadorPermiteAlterarValor;
	}

	public void setIndicadorPermiteAlterarValor(short indicadorPermiteAlterarValor) {
		this.indicadorPermiteAlterarValor = indicadorPermiteAlterarValor;
	}

	public short getIndicadorCobrarJuros() {
		return indicadorCobrarJuros;
	}

	public void setIndicadorCobrarJuros(short indicadorCobrarJuros) {
		this.indicadorCobrarJuros = indicadorCobrarJuros;
	}

	public short getIndicadorIncluirDebito() {
		return indicadorIncluirDebito;
	}

	public void setIndicadorIncluirDebito(short indicadorIncluirDebito) {
		this.indicadorIncluirDebito = indicadorIncluirDebito;
	}

	public Short getIndicadorPavimentoCalcada() {
		return indicadorPavimentoCalcada;
	}

	public void setIndicadorPavimentoCalcada(Short indicadorPavimentoCalcada) {
		this.indicadorPavimentoCalcada = indicadorPavimentoCalcada;
	}

	public Short getIndicadorPavimentoRua() {
		return indicadorPavimentoRua;
	}

	public void setIndicadorPavimentoRua(Short indicadorPavimentoRua) {
		this.indicadorPavimentoRua = indicadorPavimentoRua;
	}

	public Integer getConstanteFuncionalidadeTipoServico() {
		return constanteFuncionalidadeTipoServico;
	}

	public void setConstanteFuncionalidadeTipoServico(Integer constanteFuncionalidadeTipoServico) {
		this.constanteFuncionalidadeTipoServico = constanteFuncionalidadeTipoServico;
	}

	public Short getIndicativoTipoSevicoEconomias() {
		return indicativoTipoSevicoEconomias;
	}

	public void setIndicativoTipoSevicoEconomias(Short indicativoTipoSevicoEconomias) {
		this.indicativoTipoSevicoEconomias = indicativoTipoSevicoEconomias;
	}

	public Short getIndicadorBoletim() {
		return indicadorBoletim;
	}

	public void setIndicadorBoletim(Short indicadorBoletim) {
		this.indicadorBoletim = indicadorBoletim;
	}

	public short getIndicadorEmpresaCobranca() {
		return indicadorEmpresaCobranca;
	}

	public void setIndicadorEmpresaCobranca(short indicadorEmpresaCobranca) {
		this.indicadorEmpresaCobranca = indicadorEmpresaCobranca;
	}

	public short getIndicadorInspecaoAnormalidade() {
		return indicadorInspecaoAnormalidade;
	}

	public void setIndicadorInspecaoAnormalidade(short indicadorInspecaoAnormalidade) {
		this.indicadorInspecaoAnormalidade = indicadorInspecaoAnormalidade;
	}

	public short getIndicadorProgramacaoAutomatica() {
		return indicadorProgramacaoAutomatica;
	}

	public void setIndicadorProgramacaoAutomatica(short indicadorProgramacaoAutomatica) {
		this.indicadorProgramacaoAutomatica = indicadorProgramacaoAutomatica;
	}

	public Short getIndicadorAtividade() {
		return indicadorAtividade;
	}

	public void setIndicadorAtividade(Short indicadorAtividade) {
		this.indicadorAtividade = indicadorAtividade;
	}

	public Short getIndicadorServicoOrdemSeletiva() {
		return indicadorServicoOrdemSeletiva;
	}

	public void setIndicadorServicoOrdemSeletiva(Short indicadorServicoOrdemSeletiva) {
		this.indicadorServicoOrdemSeletiva = indicadorServicoOrdemSeletiva;
	}

	public Short getIndicadorEnvioPesquisaSatisfacao() {
		return indicadorEnvioPesquisaSatisfacao;
	}

	public void setIndicadorEnvioPesquisaSatisfacao(Short indicadorEnvioPesquisaSatisfacao) {
		this.indicadorEnvioPesquisaSatisfacao = indicadorEnvioPesquisaSatisfacao;
	}

	@Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		filtro.adicionarParametro(new ParametroSimples(	FiltroServicoTipo.ID,
														getId()));
		filtro.adicionarCaminhoParaCarregamentoEntidade("programaCalibragem");
		return filtro;
	}

	public Short getIndicadorGerarOSInspecaoAnormalidade() {
		return indicadorGerarOSInspecaoAnormalidade;
	}

	public void setIndicadorGerarOSInspecaoAnormalidade(Short indicadorGerarOSInspecaoAnormalidade) {
		this.indicadorGerarOSInspecaoAnormalidade = indicadorGerarOSInspecaoAnormalidade;
	}

	public Collection getServicoTipoMotivoEncerramentos() {
		return servicoTipoMotivoEncerramentos;
	}

	public void setServicoTipoMotivoEncerramentos(Collection servicoTipoMotivoEncerramentos) {
		this.servicoTipoMotivoEncerramentos = servicoTipoMotivoEncerramentos;
	}

	public Short getIndicadorEncAutomaticoRAQndEncOS() {
		return indicadorEncAutomaticoRAQndEncOS;
	}

	public void setIndicadorEncAutomaticoRAQndEncOS(Short indicadorEncAutomaticoRAQndEncOS) {
		this.indicadorEncAutomaticoRAQndEncOS = indicadorEncAutomaticoRAQndEncOS;
	}

	public Short getIndicadorCorrecaoAnormalidade() {
		return indicadorCorrecaoAnormalidade;
	}

	public void setIndicadorCorrecaoAnormalidade(Short indicadorCorrecaoAnormalidade) {
		this.indicadorCorrecaoAnormalidade = indicadorCorrecaoAnormalidade;
	}

}
