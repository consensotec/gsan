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
package gcom.gui.mobile.execucaoordemservico;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC1499] Consultar Dados Arquivo Texto OS Cobrança para Smartphone
 * 
 * @author Bruno Barros,Vivianne Sousa
 * @date 27/06/2013,24/11/2015
 */
public class ConsultarOrdemServicoCobrancaSmartphoneActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;

	private String empresa;	
	private String descricaoEmpresa;
	private String idTipoOS;
	private String descricaoTipoOS;
	private String idLocalidade;
	private String descricaoLocalidade;
    private String anoMesReferenciaCronograma;
    private String cobrancaGrupo;
    private String dataRealizacaoCobrancaAcaoAtividadeComando;
    public String getDataRealizacaoCobrancaAcaoAtividadeComando() {
		return dataRealizacaoCobrancaAcaoAtividadeComando;
	}

	public void setDataRealizacaoCobrancaAcaoAtividadeComando(String dataRealizacaoCobrancaAcaoAtividadeComando) {
		this.dataRealizacaoCobrancaAcaoAtividadeComando = dataRealizacaoCobrancaAcaoAtividadeComando;
	}

	private String tituloCobrancaAcaoAtividadeComando;
    private String descricaoServicoTipo;
	private String agenteComercial;
	
	private String[] idsRegistros;
	
	public ConsultarOrdemServicoCobrancaSmartphoneActionForm(){}

	public String[] getIdsRegistros() {
		return idsRegistros;
	}
	
	public void setIdsRegistros(String[] idsRegistros) {
		this.idsRegistros = idsRegistros;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getDescricaoEmpresa() {
		return descricaoEmpresa;
	}

	public void setDescricaoEmpresa(String descricaoEmpresa) {
		this.descricaoEmpresa = descricaoEmpresa;
	}

	public String getIdTipoOS() {
		return idTipoOS;
	}

	public void setIdTipoOS(String idTipoOS) {
		this.idTipoOS = idTipoOS;
	}

	public String getDescricaoTipoOS() {
		return descricaoTipoOS;
	}

	public void setDescricaoTipoOS(String descricaoTipoOS) {
		this.descricaoTipoOS = descricaoTipoOS;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}

	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}

	public String getAnoMesReferenciaCronograma() {
		return anoMesReferenciaCronograma;
	}

	public void setAnoMesReferenciaCronograma(String anoMesReferenciaCronograma) {
		this.anoMesReferenciaCronograma = anoMesReferenciaCronograma;
	}

	public String getCobrancaGrupo() {
		return cobrancaGrupo;
	}

	public void setCobrancaGrupo(String cobrancaGrupo) {
		this.cobrancaGrupo = cobrancaGrupo;
	}

	public String getTituloCobrancaAcaoAtividadeComando() {
		return tituloCobrancaAcaoAtividadeComando;
	}

	public void setTituloCobrancaAcaoAtividadeComando(String tituloCobrancaAcaoAtividadeComando) {
		this.tituloCobrancaAcaoAtividadeComando = tituloCobrancaAcaoAtividadeComando;
	}

	public String getDescricaoServicoTipo() {
		return descricaoServicoTipo;
	}

	public void setDescricaoServicoTipo(String descricaoServicoTipo) {
		this.descricaoServicoTipo = descricaoServicoTipo;
	}

	public String getAgenteComercial() {
		return agenteComercial;
	}

	public void setAgenteComercial(String agenteComercial) {
		this.agenteComercial = agenteComercial;
	}

}