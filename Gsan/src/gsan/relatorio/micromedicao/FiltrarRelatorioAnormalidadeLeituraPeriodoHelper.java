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
package gsan.relatorio.micromedicao;

import java.io.Serializable;

/**
 *[UC0958] Gerar Relat�rio Juras, Multas e D�bitos Cancelados
 * 
 * @author Marlon Patrick
 * @since 22/10/2009
 */
public class FiltrarRelatorioAnormalidadeLeituraPeriodoHelper implements
		Serializable {

	private static final long serialVersionUID = 1L;

	private Integer anoMesReferenciaInicial;
	private Integer anoMesReferenciaFinal;
	private Integer quantidadeMeses;

	private Integer anormalidadeLeitura;

	private Integer grupoFaturamento;

	private Integer unidadeNegocio;

	private Integer localidadeInicial;
	private Integer localidadeFinal;

	private Integer setorComercialInicial;
	private Integer setorComercialFinal;

	private Integer rotaInicial;
	private Integer rotaFinal;

	private Integer sequencialRotaInicial;
	private Integer sequencialRotaFinal;
	
	private String[] anormalidadeLeituraFaturada;
	
	private Integer ordenacao;
	
	public Integer getAnoMesReferenciaInicial() {
		return anoMesReferenciaInicial;
	}

	public void setAnoMesReferenciaInicial(Integer mesAnoReferenciaInicial) {
		this.anoMesReferenciaInicial = mesAnoReferenciaInicial;
	}

	public Integer getAnoMesReferenciaFinal() {
		return anoMesReferenciaFinal;
	}

	public void setAnoMesReferenciaFinal(Integer mesAnoReferenciaFinal) {
		this.anoMesReferenciaFinal = mesAnoReferenciaFinal;
	}

	public Integer getAnormalidadeLeitura() {
		return anormalidadeLeitura;
	}

	public void setAnormalidadeLeitura(Integer anormalidadeLeitura) {
		this.anormalidadeLeitura = anormalidadeLeitura;
	}

	public Integer getGrupoFaturamento() {
		return grupoFaturamento;
	}

	public void setGrupoFaturamento(Integer grupoFaturamento) {
		this.grupoFaturamento = grupoFaturamento;
	}

	public Integer getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(Integer unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public Integer getLocalidadeInicial() {
		return localidadeInicial;
	}

	public void setLocalidadeInicial(Integer localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}

	public Integer getLocalidadeFinal() {
		return localidadeFinal;
	}

	public void setLocalidadeFinal(Integer localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}

	public Integer getSetorComercialInicial() {
		return setorComercialInicial;
	}

	public void setSetorComercialInicial(Integer setorComercialInicial) {
		this.setorComercialInicial = setorComercialInicial;
	}

	public Integer getSetorComercialFinal() {
		return setorComercialFinal;
	}

	public void setSetorComercialFinal(Integer setorComercialFinal) {
		this.setorComercialFinal = setorComercialFinal;
	}

	public Integer getRotaInicial() {
		return rotaInicial;
	}

	public void setRotaInicial(Integer rotaInicial) {
		this.rotaInicial = rotaInicial;
	}

	public Integer getRotaFinal() {
		return rotaFinal;
	}

	public void setRotaFinal(Integer rotaFinal) {
		this.rotaFinal = rotaFinal;
	}

	public Integer getSequencialRotaInicial() {
		return sequencialRotaInicial;
	}

	public void setSequencialRotaInicial(Integer sequencialRotaInicial) {
		this.sequencialRotaInicial = sequencialRotaInicial;
	}

	public Integer getSequencialRotaFinal() {
		return sequencialRotaFinal;
	}

	public void setSequencialRotaFinal(Integer sequencialRotaFinal) {
		this.sequencialRotaFinal = sequencialRotaFinal;
	}

	public Integer getQuantidadeMeses() {
		return quantidadeMeses;
	}

	public void setQuantidadeMeses(Integer quantidadeMeses) {
		this.quantidadeMeses = quantidadeMeses;
	}

	public String[] getAnormalidadeLeituraFaturada() {
		return anormalidadeLeituraFaturada;
	}

	public void setAnormalidadeLeituraFaturada(String[] anormalidadeLeituraFaturada) {
		this.anormalidadeLeituraFaturada = anormalidadeLeituraFaturada;
	}

	public Integer getOrdenacao() {
		return ordenacao;
	}

	public void setOrdenacao(Integer ordenacao) {
		this.ordenacao = ordenacao;
	}
}