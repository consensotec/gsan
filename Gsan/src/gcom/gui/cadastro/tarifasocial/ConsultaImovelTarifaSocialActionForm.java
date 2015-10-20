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
package gcom.gui.cadastro.tarifasocial;

import java.util.Collection;
import java.util.Vector;

import gcom.gui.ControladorConsultaGcomActionForm;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

/**
 * Controlador ActionForm 
 * 
 * @author thiago
 * @created 20/12/2005
 */ 
public class ConsultaImovelTarifaSocialActionForm extends ControladorConsultaGcomActionForm {
	private static final long serialVersionUID = 1L;
	
    private String idLocalidade = "";
	private String localidadeDescricao = "";
    private String idSetorComercial = "";
    private String setorComercialDescricao = "";
    private String idQuadra = "";
    private String lote = "";
    private String subLote = "";
    private String codigoCliente = "";
    private String idMunicipio = "";
    private String cep = "";
    private String idBairro = "";
    private String logradouro = "";
    private String indicadorUso = "";
    private String matricula = "";    
    private String quadraDescricao = "";
    private String nomeCliente = "";
    private String bairro = "";
    private String municipio = "";
    
    private String idCliente = "";

    private String[] idRegistrosRemocao = new String[0];
	private String tarifaSocialExclusaoMotivo = "";
	private Collection collTarifaSocialExclusaoMotivo = new Vector();
	
    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     */
    public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
        this.idLocalidade = "";
        this.idSetorComercial = "";
        this.idQuadra = "";
        this.lote = "";
        this.subLote = "";
        this.codigoCliente = "";
        this.idMunicipio = "";
        this.cep = "";
        this.idBairro = "";
        this.logradouro = "";
        this.indicadorUso = "";
        this.matricula = "";
        this.localidadeDescricao = "";
        this.quadraDescricao = "";
        this.nomeCliente = "";
        this.bairro = "";
        this.municipio = "";
        
        this.idCliente="";
        
        this.idRegistrosRemocao = new String[0];
        
        this.tarifaSocialExclusaoMotivo = "";
    	this.collTarifaSocialExclusaoMotivo = new Vector();
        
    }

	public String[] getIdRegistrosRemocao() {
		return idRegistrosRemocao;
	}

	public void setIdRegistrosRemocao(String[] idRegistrosRemocao) {
		this.idRegistrosRemocao = idRegistrosRemocao;
	}

	public Collection getCollTarifaSocialExclusaoMotivo() {
		return collTarifaSocialExclusaoMotivo;
	}

	public void setCollTarifaSocialExclusaoMotivo(
			Collection collTarifaSocialExclusaoMotivo) {
		this.collTarifaSocialExclusaoMotivo = collTarifaSocialExclusaoMotivo;
	}

	public String getTarifaSocialExclusaoMotivo() {
		return tarifaSocialExclusaoMotivo;
	}

	public void setTarifaSocialExclusaoMotivo(String tarifaSocialExclusaoMotivo) {
		this.tarifaSocialExclusaoMotivo = tarifaSocialExclusaoMotivo;
	}
	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getQuadraDescricao() {
		return quadraDescricao;
	}

	public void setQuadraDescricao(String quadraDescricao) {
		this.quadraDescricao = quadraDescricao;
	}

	public String getSetorComercialDescricao() {
		return setorComercialDescricao;
	}

	public void setSetorComercialDescricao(String setorComercialDescricao) {
		this.setorComercialDescricao = setorComercialDescricao;
	}

	public String getLocalidadeDescricao() {
		return localidadeDescricao;
	}

	public void setLocalidadeDescricao(String localidadeDescricao) {
		this.localidadeDescricao = localidadeDescricao;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getIdBairro() {
		return idBairro;
	}

	public void setIdBairro(String idBairro) {
		this.idBairro = idBairro;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public String getIdQuadra() {
		return idQuadra;
	}

	public void setIdQuadra(String idQuadra) {
		this.idQuadra = idQuadra;
	}

	public String getIdSetorComercial() {
		return idSetorComercial;
	}

	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getSubLote() {
		return subLote;
	}

	public void setSubLote(String subLote) {
		this.subLote = subLote;
	}
    
    
}