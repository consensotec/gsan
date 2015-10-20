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
 * [UC1530] Inserir Registro de Atendimento Simplificado
 * 
 * @author Mariana Victor
 * @date 10/07/2013
 */
public class InserirRegistroAtendimentoSimplificadoActionForm extends
		ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	private String dataAtendimento;

	private String horaAtendimento;
	
	private String nome;
	
	private String tipoAtendimento;
	
	private String tipoAtendimentoSelecionado;
    
    private String numeroCpf;
    
    private String numeroRg;
    
    private String idOrgaoExpedidor;
    
    private String idUnidadeFederacao;
    
    //Operacional
    private String idTipoOcorrencia;
    
    private String idMunicipioRA;
    
    private String nomeMunicipioRA;
    
    private String idLocalidadeRA;
    
    private String idBairro;
    
    private String idOcorrenciaOperacional;
    
    //Reiteração
    private String numeroProtocolo;
    
    private String numeroRA;
    
    private String descricaoRA;
    
    private String observacao;
    
    //Informação
    private String idLocalidadeInfo;
    
    private String descricaoInformacao;

    
	public String getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(String dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	public String getHoraAtendimento() {
		return horaAtendimento;
	}

	public void setHoraAtendimento(String horaAtendimento) {
		this.horaAtendimento = horaAtendimento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipoAtendimento() {
		return tipoAtendimento;
	}

	public void setTipoAtendimento(String tipoAtendimento) {
		this.tipoAtendimento = tipoAtendimento;
	}

	public String getTipoAtendimentoSelecionado() {
		return tipoAtendimentoSelecionado;
	}

	public void setTipoAtendimentoSelecionado(String tipoAtendimentoSelecionado) {
		this.tipoAtendimentoSelecionado = tipoAtendimentoSelecionado;
	}

	public String getNumeroCpf() {
		return numeroCpf;
	}

	public void setNumeroCpf(String numeroCpf) {
		this.numeroCpf = numeroCpf;
	}

	public String getNumeroRg() {
		return numeroRg;
	}

	public void setNumeroRg(String numeroRg) {
		this.numeroRg = numeroRg;
	}

	public String getIdOrgaoExpedidor() {
		return idOrgaoExpedidor;
	}

	public void setIdOrgaoExpedidor(String idOrgaoExpedidor) {
		this.idOrgaoExpedidor = idOrgaoExpedidor;
	}

	public String getIdUnidadeFederacao() {
		return idUnidadeFederacao;
	}

	public void setIdUnidadeFederacao(String idUnidadeFederacao) {
		this.idUnidadeFederacao = idUnidadeFederacao;
	}

	public String getIdTipoOcorrencia() {
		return idTipoOcorrencia;
	}

	public void setIdTipoOcorrencia(String idTipoOcorrencia) {
		this.idTipoOcorrencia = idTipoOcorrencia;
	}

	public String getIdMunicipioRA() {
		return idMunicipioRA;
	}

	public void setIdMunicipioRA(String idMunicipioRA) {
		this.idMunicipioRA = idMunicipioRA;
	}

	public String getNomeMunicipioRA() {
		return nomeMunicipioRA;
	}

	public void setNomeMunicipioRA(String nomeMunicipioRA) {
		this.nomeMunicipioRA = nomeMunicipioRA;
	}

	public String getIdLocalidadeRA() {
		return idLocalidadeRA;
	}

	public void setIdLocalidadeRA(String idLocalidadeRA) {
		this.idLocalidadeRA = idLocalidadeRA;
	}

	public String getIdBairro() {
		return idBairro;
	}

	public void setIdBairro(String idBairro) {
		this.idBairro = idBairro;
	}

	public String getIdOcorrenciaOperacional() {
		return idOcorrenciaOperacional;
	}

	public void setIdOcorrenciaOperacional(String idOcorrenciaOperacional) {
		this.idOcorrenciaOperacional = idOcorrenciaOperacional;
	}

	public String getIdLocalidadeInfo() {
		return idLocalidadeInfo;
	}

	public void setIdLocalidadeInfo(String idLocalidadeInfo) {
		this.idLocalidadeInfo = idLocalidadeInfo;
	}

	public String getDescricaoInformacao() {
		return descricaoInformacao;
	}

	public void setDescricaoInformacao(String descricaoInformacao) {
		this.descricaoInformacao = descricaoInformacao;
	}

	public String getNumeroProtocolo() {
		return numeroProtocolo;
	}

	public void setNumeroProtocolo(String numeroProtocolo) {
		this.numeroProtocolo = numeroProtocolo;
	}

	public String getNumeroRA() {
		return numeroRA;
	}

	public void setNumeroRA(String numeroRA) {
		this.numeroRA = numeroRA;
	}

	public String getDescricaoRA() {
		return descricaoRA;
	}

	public void setDescricaoRA(String descricaoRA) {
		this.descricaoRA = descricaoRA;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}
