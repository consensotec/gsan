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

import gcom.cobranca.CobrancaGrupo;
import gcom.mobile.execucaoordemservico.bean.GerarArquivoTxtSmartphoneHelper;

import java.util.Collection;

import org.apache.struts.validator.ValidatorActionForm;

public class GerarArquivoTextoOrdensServicoSmartphoneActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	private Integer idEmpresa;

	private String descricaoEmpresa;

	private String idTipoOS;

	private String descricaoTipoOS;

	private Integer[] idsLocalidade;

	private Integer idServicoTipo;

	private String qtdMaxOS;

	private String mesAnoCronograma;

	private Integer idGrupoCobranca;

	private Integer[] idsRota;

	private String[] idsOS;

	private Collection<CobrancaGrupo> colecaoGrupoCobranca;

	private Collection<GerarArquivoTxtSmartphoneHelper> colecaoHelper;

	private Integer idComando;

	private String dataCobrancaEventualInicial;

	private String dataCobrancaEventualFinal;

	private Integer idAgenteComercial;

	private Integer tipoFiltro;

	public String getQtdMaxOS() {
		return qtdMaxOS;
	}

	public void setQtdMaxOS(String qtdMaxOS) {
		this.qtdMaxOS = qtdMaxOS;
	}

	public GerarArquivoTextoOrdensServicoSmartphoneActionForm() {
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getDescricaoEmpresa() {
		return descricaoEmpresa;
	}

	public void setDescricaoEmpresa(String descricaoEmpresa) {
		this.descricaoEmpresa = descricaoEmpresa;
	}

	public String getDescricaoTipoOS() {
		return descricaoTipoOS;
	}

	public void setDescricaoTipoOS(String descricaoTipoOS) {
		this.descricaoTipoOS = descricaoTipoOS;
	}

	public Integer[] getIdsLocalidade() {
		return idsLocalidade;
	}

	public void setIdsLocalidade(Integer[] idsLocalidade) {
		this.idsLocalidade = idsLocalidade;
	}

	public Integer getIdServicoTipo() {
		return idServicoTipo;
	}

	public void setIdServicoTipo(Integer idServicoTipo) {
		this.idServicoTipo = idServicoTipo;
	}

	public String getIdTipoOS() {
		return idTipoOS;
	}

	public void setIdTipoOS(String idTipoOS) {
		this.idTipoOS = idTipoOS;
	}

	public String getMesAnoCronograma() {
		return mesAnoCronograma;
	}

	public void setMesAnoCronograma(String mesAnoCronograma) {
		this.mesAnoCronograma = mesAnoCronograma;
	}

	public Collection<CobrancaGrupo> getColecaoGrupoCobranca() {
		return colecaoGrupoCobranca;
	}

	public void setColecaoGrupoCobranca(Collection<CobrancaGrupo> colecaoGrupoCobranca) {
		this.colecaoGrupoCobranca = colecaoGrupoCobranca;
	}

	public Integer getIdGrupoCobranca() {
		return idGrupoCobranca;
	}

	public void setIdGrupoCobranca(Integer idGrupoCobranca) {
		this.idGrupoCobranca = idGrupoCobranca;
	}

	public Integer[] getIdsRota() {
		return idsRota;
	}

	public void setIdsRota(Integer[] idsRota) {
		this.idsRota = idsRota;
	}

	public String[] getIdsOS() {
		return idsOS;
	}

	public void setIdsOS(String[] idsOS) {
		this.idsOS = idsOS;
	}

	public Collection<GerarArquivoTxtSmartphoneHelper> getColecaoHelper() {
		return colecaoHelper;
	}

	public void setColecaoHelper(Collection<GerarArquivoTxtSmartphoneHelper> colecaoHelper) {
		this.colecaoHelper = colecaoHelper;
	}

	public Integer getIdComando() {
		return idComando;
	}

	public void setIdComando(Integer idComando) {
		this.idComando = idComando;
	}

	public String getDataCobrancaEventualInicial() {
		return dataCobrancaEventualInicial;
	}

	public void setDataCobrancaEventualInicial(String dataCobrancaEventualInicial) {
		this.dataCobrancaEventualInicial = dataCobrancaEventualInicial;
	}

	public String getDataCobrancaEventualFinal() {
		return dataCobrancaEventualFinal;
	}

	public void setDataCobrancaEventualFinal(String dataCobrancaEventualFinal) {
		this.dataCobrancaEventualFinal = dataCobrancaEventualFinal;
	}

	public Integer getIdAgenteComercial() {
		return idAgenteComercial;
	}

	public void setIdAgenteComercial(Integer idAgenteComercial) {
		this.idAgenteComercial = idAgenteComercial;
	}

	public Integer getTipoFiltro() {
		return tipoFiltro;
	}

	public void setTipoFiltro(Integer tipoFiltro) {
		this.tipoFiltro = tipoFiltro;
	}
}
