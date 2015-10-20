/*
 * Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
 * GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
 * Copyright (C) <2007> 
 * Adriano Britto Siqueira
 * Alexandre Santos Cabral
 * Ana Carolina Alves Breda
 * Ana Maria Andrade Cavalcante
 * Aryed Lins de Araújo
 * Bruno Leonardo Rodrigues Barros
 * Carlos Elmano Rodrigues Ferreira
 * Cláudio de Andrade Lira
 * Denys Guimarães Guenes Tavares
 * Eduardo Breckenfeld da Rosa Borges
 * Fabíola Gomes de Araújo
 * Flávio Leonardo Cavalcanti Cordeiro
 * Francisco do Nascimento Júnior
 * Homero Sampaio Cavalcanti
 * Ivan Sérgio da Silva Júnior
 * José Edmar de Siqueira
 * José Thiago Tenório Lopes
 * Kássia Regina Silvestre de Albuquerque
 * Leonardo Luiz Vieira da Silva
 * Márcio Roberto Batista da Silva
 * Maria de Fátima Sampaio Leite
 * Micaela Maria Coelho de Araújo
 * Nelson Mendonça de Carvalho
 * Newton Morais e Silva
 * Pedro Alexandre Santos da Silva Filho
 * Rafael Corrêa Lima e Silva
 * Rafael Francisco Pinto
 * Rafael Koury Monteiro
 * Rafael Palermo de Araújo
 * Raphael Veras Rossiter
 * Roberto Sobreira Barbalho
 * Rodrigo Avellar Silveira
 * Rosana Carvalho Barbosa
 * Sávio Luiz de Andrade Cavalcante
 * Tai Mu Shih
 * Thiago Augusto Souza do Nascimento
 * Tiago Moreno Rodrigues
 * Vivianne Barbosa Sousa
 *
 * Este programa é software livre; você pode redistribuí-lo e/ou
 * modificá-lo sob os termos de Licença Pública Geral GNU, conforme
 * publicada pela Free Software Foundation; versão 2 da
 * Licença.
 * Este programa é distribuído na expectativa de ser útil, mas SEM
 * QUALQUER GARANTIA; sem mesmo a garantia implícita de
 * COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
 * PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
 * detalhes.
 * Você deve ter recebido uma cópia da Licença Pública Geral GNU
 * junto com este programa; se não, escreva para Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307, USA.
 */
package gcom.gui.atendimentopublico.registroatendimento;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC1528] - Filtrar Ocorrencia Operacional 
 * 
 * @author Rômulo Aurélio
 * @date 12/07/2013
 * 
 */
public class FiltrarOcorrenciaOperacionalActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;

	
	private String atualizar;
	
	private String descricao;

	private String areasAfetadas;
	
	private String dataPrevisao;
	private String dataReprogramacao;
	private String dataConclusao;
	private String codigoPeriodoPrevisao;
	private String codigoPeriodoReprogramacao;
	private String codigoPeriodoConclusao;
	private String observacao;
	private String dataOcorrencia;
	private String horaOcorrencia;
	private String codigoMunicipio;
	private String descricaoMunicipio;
	private String localidade;
	private String bairro;
	private String ocorrenciaTipo;
	private String motivoOcorrencia;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getAreasAfetadas() {
		return areasAfetadas;
	}
	public void setAreasAfetadas(String areasAfetadas) {
		this.areasAfetadas = areasAfetadas;
	}
	public String getDataPrevisao() {
		return dataPrevisao;
	}
	public void setDataPrevisao(String dataPrevisao) {
		this.dataPrevisao = dataPrevisao;
	}
	public String getDataReprogramacao() {
		return dataReprogramacao;
	}
	public void setDataReprogramacao(String dataReprogramacao) {
		this.dataReprogramacao = dataReprogramacao;
	}
	public String getDataConclusao() {
		return dataConclusao;
	}
	public void setDataConclusao(String dataConclusao) {
		this.dataConclusao = dataConclusao;
	}
	public String getCodigoPeriodoPrevisao() {
		return codigoPeriodoPrevisao;
	}
	public void setCodigoPeriodoPrevisao(String codigoPeriodoPrevisao) {
		this.codigoPeriodoPrevisao = codigoPeriodoPrevisao;
	}
	public String getCodigoPeriodoReprogramacao() {
		return codigoPeriodoReprogramacao;
	}
	public void setCodigoPeriodoReprogramacao(String codigoPeriodoReprogramacao) {
		this.codigoPeriodoReprogramacao = codigoPeriodoReprogramacao;
	}
	public String getCodigoPeriodoConclusao() {
		return codigoPeriodoConclusao;
	}
	public void setCodigoPeriodoConclusao(String codigoPeriodoConclusao) {
		this.codigoPeriodoConclusao = codigoPeriodoConclusao;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getDataOcorrencia() {
		return dataOcorrencia;
	}
	public void setDataOcorrencia(String dataOcorrencia) {
		this.dataOcorrencia = dataOcorrencia;
	}
	public String getCodigoMunicipio() {
		return codigoMunicipio;
	}
	public void setCodigoMunicipio(String codigoMunicipio) {
		this.codigoMunicipio = codigoMunicipio;
	}
	public String getDescricaoMunicipio() {
		return descricaoMunicipio;
	}
	public void setDescricaoMunicipio(String descricaoMunicipio) {
		this.descricaoMunicipio = descricaoMunicipio;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getOcorrenciaTipo() {
		return ocorrenciaTipo;
	}
	public void setOcorrenciaTipo(String ocorrenciaTipo) {
		this.ocorrenciaTipo = ocorrenciaTipo;
	}
	public String getMotivoOcorrencia() {
		return motivoOcorrencia;
	}
	public void setMotivoOcorrencia(String motivoOcorrencia) {
		this.motivoOcorrencia = motivoOcorrencia;
	}
	public String getHoraOcorrencia() {
		return horaOcorrencia;
	}
	public void setHoraOcorrencia(String horaOcorrencia) {
		this.horaOcorrencia = horaOcorrencia;
	}
	public String getAtualizar() {
		return atualizar;
	}
	public void setAtualizar(String atualizar) {
		this.atualizar = atualizar;
	}
	
}
