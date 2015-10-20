/*
 * Copyright (C) 2007-2007 the GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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
 * Foundation, Inc., 59 Temple Place � Suite 330, Boston, MA 02111-1307, USA
 */

/*
 * GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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
package gcom.relatorio.cadastro.micromedicao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * [UC0997] Gerar Resumo de Liga��es por Capacidade de Hidr�metro
 * 
 * @author Hugo Leonardo
 *
 * @date 29/03/2010
 */
public class RelatorioResumoLigacoesCapacidadeHidrometroHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idGerenciaRegional;
	private String nomeGerenciaRegional;
	private Integer idUnidadeNegocio;
	private String nomeUnidadeNegocio;
	private Integer idLocalidade;
	private String nomeLocalidade;
	private String capacidadeHidrometro;
	private String diametroHidrometro;
	private Integer qtdLigacoes;
	private Integer qtdEconomias;
	private Integer volumeAguaMedido;
	private BigDecimal valorFaturado;
	private String opcaoTotalizacao;
	private Date mesAnoReferencia;
	private String descricaoEstado;
	
	private Integer anoMesReferenciaAnterior;
	
	public String getCapacidadeHidrometro() {
		return capacidadeHidrometro;
	}
	
	public void setCapacidadeHidrometro(String capacidadeHidrometro) {
		this.capacidadeHidrometro = capacidadeHidrometro;
	}
	
	public String getDiametroHidrometro() {
		return diametroHidrometro;
	}
	
	public void setDiametroHidrometro(String diametroHidrometro) {
		this.diametroHidrometro = diametroHidrometro;
	}
	
	public Integer getIdGerenciaRegional() {
		return idGerenciaRegional;
	}
	
	public void setIdGerenciaRegional(Integer idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}
	
	public Integer getIdLocalidade() {
		return idLocalidade;
	}
	
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	
	public Integer getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}
	
	public void setIdUnidadeNegocio(Integer idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}
	
	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}
	
	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}
	
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}
	
	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}
	
	public String getNomeUnidadeNegocio() {
		return nomeUnidadeNegocio;
	}
	
	public void setNomeUnidadeNegocio(String nomeUnidadeNegocio) {
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
	}
	
	public Integer getQtdEconomias() {
		return qtdEconomias;
	}
	
	public void setQtdEconomias(Integer qtdEconomias) {
		this.qtdEconomias = qtdEconomias;
	}
	
	public Integer getQtdLigacoes() {
		return qtdLigacoes;
	}
	
	public void setQtdLigacoes(Integer qtdLigacoes) {
		this.qtdLigacoes = qtdLigacoes;
	}
	
	public BigDecimal getValorFaturado() {
		return valorFaturado;
	}
	
	public void setValorFaturado(BigDecimal valorFaturado) {
		this.valorFaturado = valorFaturado;
	}
	
	public Integer getVolumeAguaMedido() {
		return volumeAguaMedido;
	}
	
	public void setVolumeAguaMedido(Integer volumeAguaMedido) {
		this.volumeAguaMedido = volumeAguaMedido;
	}

	public String getOpcaoTotalizacao() {
		return opcaoTotalizacao;
	}

	public void setOpcaoTotalizacao(String opcaoTotalizacao) {
		this.opcaoTotalizacao = opcaoTotalizacao;
	}

	public Date getMesAnoReferencia() {
		return mesAnoReferencia;
	}

	public void setMesAnoReferencia(Date mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}

	public String getDescricaoEstado() {
		return descricaoEstado;
	}

	public void setDescricaoEstado(String descricaoEstado) {
		this.descricaoEstado = descricaoEstado;
	}

	public Integer getAnoMesReferenciaAnterior() {
		return anoMesReferenciaAnterior;
	}

	public void setAnoMesReferenciaAnterior(Integer anoMesReferenciaAnterior) {
		this.anoMesReferenciaAnterior = anoMesReferenciaAnterior;
	}

}