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

import gcom.cadastro.imovel.FiltroImovel;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class ServicoTipoReferencia extends ObjetoTransacao implements
		Serializable {
	
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private String descricao;

	/** nullable persistent field */
	private String descricaoAbreviada;

	/** nullable persistent field */
	private Integer situacaoOsReferenciaAntes;

	/** persistent field */
	private short indicadorUso;

	/** nullable persistent field */
	private Integer situacaoOsReferenciaApos;

	/** persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private short indicadorExistenciaOsReferencia;
	
	private short indicadorDiagnostico;
	
	private short indicadorFiscalizacao;
	
	/**
	 * Description of the Field
	 */
	public final static Short INDICADOR_FISCALIZACAO_SIM = new Short(
			"1");

	/**
	 * Description of the Field
	 */
	public final static Short INDICADOR_EXISTENCIA_OS_REFERENCIA_ATIVO = new Short(
			"1");
	
	/**
	 * Description of the Field
	 */
	public final static Short INDICADOR_DIAGNOSTICO_ATIVO = new Short(
			"1");

	/** persistent field */
	private gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipo;

	/** full constructor */
	public ServicoTipoReferencia(String descricao, String descricaoAbreviada,
			short indicadorUso, Integer situacaoOsReferenciaApos,
			Integer situacaoOsReferenciaAntes, Date ultimaAlteracao,
			short indicadorExistenciaOsReferencia,
			gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipo) {
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.situacaoOsReferenciaAntes = situacaoOsReferenciaAntes;
		this.indicadorUso = indicadorUso;
		this.situacaoOsReferenciaApos = situacaoOsReferenciaApos;
		this.ultimaAlteracao = ultimaAlteracao;
		this.indicadorExistenciaOsReferencia = indicadorExistenciaOsReferencia;
		this.servicoTipo = servicoTipo;
	}

	/** default constructor */
	public ServicoTipoReferencia() {
	}

	/** minimal constructor */
	public ServicoTipoReferencia(short indicadorUso, Date ultimaAlteracao,
			short indicadorExistenciaOsReferencia,
			gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipo) {
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.indicadorExistenciaOsReferencia = indicadorExistenciaOsReferencia;
		this.servicoTipo = servicoTipo;
	}

	/**
	 * @return Retorna o campo situacaoOsReferenciaAntes.
	 */
	public Integer getSituacaoOsReferenciaAntes() {
		return situacaoOsReferenciaAntes;
	}

	/**
	 * @param situacaoOsReferenciaAntes
	 *            O situacaoOsReferenciaAntes a ser setado.
	 */
	public void setSituacaoOsReferenciaAntes(Integer situacaoOsReferenciaAntes) {
		this.situacaoOsReferenciaAntes = situacaoOsReferenciaAntes;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public short getIndicadorUso() {
		return this.indicadorUso;
	}

	public void setIndicadorUso(short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Integer getSituacaoOsReferenciaApos() {
		return this.situacaoOsReferenciaApos;
	}

	public void setSituacaoOsReferenciaApos(Integer situacaoOsReferenciaApos) {
		this.situacaoOsReferenciaApos = situacaoOsReferenciaApos;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public short getIndicadorExistenciaOsReferencia() {
		return this.indicadorExistenciaOsReferencia;
	}

	public void setIndicadorExistenciaOsReferencia(
			short indicadorExistenciaOsReferencia) {
		this.indicadorExistenciaOsReferencia = indicadorExistenciaOsReferencia;
	}

	public gcom.atendimentopublico.ordemservico.ServicoTipo getServicoTipo() {
		return this.servicoTipo;
	}

	public void setServicoTipo(
			gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipo) {
		this.servicoTipo = servicoTipo;
	}
	
	

	public short getIndicadorDiagnostico() {
		return indicadorDiagnostico;
	}

	public void setIndicadorDiagnostico(short indicadorDiagnostico) {
		this.indicadorDiagnostico = indicadorDiagnostico;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public Filtro retornaFiltro() {
		FiltroServicoTipoReferencia filtroServicoTipoReferencia = new FiltroServicoTipoReferencia();
		filtroServicoTipoReferencia.adicionarParametro(new ParametroSimples(
				FiltroImovel.ID, this.getId()));
		// filtroImovel.adicionarCaminhoParaCarregamentoEntidade
		// ("ligacaoEsgoto");
		filtroServicoTipoReferencia
				.adicionarCaminhoParaCarregamentoEntidade("servicoTipo");
		return filtroServicoTipoReferencia;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public short getIndicadorFiscalizacao() {
		return indicadorFiscalizacao;
	}

	public void setIndicadorFiscalizacao(short indicadorFiscalizacao) {
		this.indicadorFiscalizacao = indicadorFiscalizacao;
	}
	
	

}
