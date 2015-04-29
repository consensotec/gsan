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

import gsan.cadastro.unidade.UnidadeOrganizacional;
import gsan.interceptor.ObjetoTransacao;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.filtro.Filtro;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class Equipe extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	// CONSTANTE DE VALIDA��O DA CARGA HOR�RIA
	public final static int CARGA_HORARIA_MAXIMA = new Integer(24);

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private String nome;

	/** nullable persistent field */
	private String placaVeiculo;

	/** nullable persistent field */
	private Integer cargaTrabalho;

	/** nullable persistent field */
	private short indicadorUso;

	/** persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private UnidadeOrganizacional unidadeOrganizacional;

	/** nullable persistent field */
	private String codigoDdd;

	/** nullable persistent field */
	private String numeroTelefone;

	/** nullable persistent field */
	private BigDecimal numeroImei;

	/** persistent field */
	private gsan.atendimentopublico.ordemservico.ServicoPerfilTipo servicoPerfilTipo;
	
	private Usuario usuarioRespExecServico;
	
	private Short indicadorProgramacaoAutomatica;

	/** full constructor */
	public Equipe(
			String nome,
			String placaVeiculo,
			Integer cargaTrabalho,
			Date ultimaAlteracao,
			UnidadeOrganizacional unidadeOrganizacional,
			gsan.atendimentopublico.ordemservico.ServicoPerfilTipo servicoPerfilTipo,
			short indicadorUso, Integer codigoDdd, Integer numeroTelefone,
			Integer numeroImei) {
		this.nome = nome;
		this.placaVeiculo = placaVeiculo;
		this.cargaTrabalho = cargaTrabalho;
		this.ultimaAlteracao = ultimaAlteracao;
		this.unidadeOrganizacional = unidadeOrganizacional;
		this.servicoPerfilTipo = servicoPerfilTipo;
		this.indicadorUso = indicadorUso;

	}

	/** default constructor */
	public Equipe() {
	}

	/** minimal constructor */
	public Equipe(
			Date ultimaAlteracao,
			UnidadeOrganizacional unidadeOrganizacional,
			gsan.atendimentopublico.ordemservico.ServicoPerfilTipo servicoPerfilTipo,
			Integer codigoDdd, Integer numeroTelefone, Integer numeroImei) {
		this.ultimaAlteracao = ultimaAlteracao;
		this.unidadeOrganizacional = unidadeOrganizacional;
		this.servicoPerfilTipo = servicoPerfilTipo;

	}

	public short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPlacaVeiculo() {
		return this.placaVeiculo;
	}

	public void setPlacaVeiculo(String placaVeiculo) {
		this.placaVeiculo = placaVeiculo;
	}

	public Integer getCargaTrabalho() {
		return this.cargaTrabalho;
	}

	public void setCargaTrabalho(Integer cargaTrabalho) {
		this.cargaTrabalho = cargaTrabalho;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public UnidadeOrganizacional getUnidadeOrganizacional() {
		return this.unidadeOrganizacional;
	}

	public void setUnidadeOrganizacional(
			UnidadeOrganizacional unidadeOrganizacional) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}

	public gsan.atendimentopublico.ordemservico.ServicoPerfilTipo getServicoPerfilTipo() {
		return this.servicoPerfilTipo;
	}

	public void setServicoPerfilTipo(
			gsan.atendimentopublico.ordemservico.ServicoPerfilTipo servicoPerfilTipo) {
		this.servicoPerfilTipo = servicoPerfilTipo;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public Filtro retornaFiltro() {
		FiltroEquipe filtroEquipe = new FiltroEquipe();

		filtroEquipe
				.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");
		filtroEquipe
				.adicionarCaminhoParaCarregamentoEntidade("servicoPerfilTipo");
		filtroEquipe.adicionarCaminhoParaCarregamentoEntidade("codigoDdd");
		filtroEquipe.adicionarCaminhoParaCarregamentoEntidade("numeroTelefone");
		filtroEquipe.adicionarCaminhoParaCarregamentoEntidade("numeroImei");
		filtroEquipe.adicionarParametro(new ParametroSimples(FiltroEquipe.ID,
				this.getId()));
		return filtroEquipe;
	}

	public String getCodigoDdd() {
		return codigoDdd;
	}

	public void setCodigoDdd(String codigoDdd) {
		this.codigoDdd = codigoDdd;
	}

	public String getNumeroTelefone() {
		return numeroTelefone;
	}

	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}

	public BigDecimal getNumeroImei() {
		return numeroImei;
	}

	public void setNumeroImei(BigDecimal numeroImei) {
		this.numeroImei = numeroImei;
	}

	public Usuario getUsuarioRespExecServico() {
		return usuarioRespExecServico;
	}

	public void setUsuarioRespExecServico(Usuario usuarioRespExecServico) {
		this.usuarioRespExecServico = usuarioRespExecServico;
	}
	
	public Short getIndicadorProgramacaoAutomatica() {
		return indicadorProgramacaoAutomatica;
	}

	public void setIndicadorProgramacaoAutomatica(
			Short indicadorProgramacaoAutomatica) {
		this.indicadorProgramacaoAutomatica = indicadorProgramacaoAutomatica;
	}
	
	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param other
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if (!(other instanceof Equipe)) {
			return false;
		}
		Equipe castOther = (Equipe) other;

		return (this.getId().equals(castOther.getId()));
	}
}
