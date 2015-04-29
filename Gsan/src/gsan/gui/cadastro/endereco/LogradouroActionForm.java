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
package gsan.gui.cadastro.endereco;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Class
 * 
 * @author S�vio Luiz
 * @created 19 de Julho de 2005
 */
public class LogradouroActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String nome;
    
	private String codigoLogradouro;
	
    private String nomePopular;

    private Integer idTitulo;

    private Integer idTipo;

    private String idMunicipio;

    private String nomeMunicipio;

    private String codigoBairro;
    
    private String nomeBairro;
    
    private String codigoCEP;
    
    private String descricaoCEP;

    private String indicadorUso;
    
    private String colecaoBairro;
    
    private String colecaoCep;
    
    

    public String getColecaoBairro() {
		return colecaoBairro;
	}

	public void setColecaoBairro(String colecaoBairro) {
		this.colecaoBairro = colecaoBairro;
	}

	public String getColecaoCep() {
		return colecaoCep;
	}

	public void setColecaoCep(String colecaoCep) {
		this.colecaoCep = colecaoCep;
	}

	/**
     * Gets the codigoBairro attribute of the LogradouroActionForm object
     * 
     * @return The codigoBairro value
     */
    public String getCodigoBairro() {
        return codigoBairro;
    }

    /**
     * Sets the codigoBairro attribute of the LogradouroActionForm object
     * 
     * @param codigoBairro
     *            The new codigoBairro value
     */
    public void setCodigoBairro(String codigoBairro) {
        this.codigoBairro = codigoBairro;
    }

    /**
     * Gets the idMunicipio attribute of the LogradouroActionForm object
     * 
     * @return The idMunicipio value
     */
    public String getIdMunicipio() {
        return idMunicipio;
    }

    /**
     * Sets the idMunicipio attribute of the LogradouroActionForm object
     * 
     * @param idMunicipio
     *            The new idMunicipio value
     */
    public void setIdMunicipio(String idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    /**
     * Gets the idTipo attribute of the LogradouroActionForm object
     * 
     * @return The idTipo value
     */
    public Integer getIdTipo() {
        return idTipo;
    }

    /**
     * Sets the idTipo attribute of the LogradouroActionForm object
     * 
     * @param idTipo
     *            The new idTipo value
     */
    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    /**
     * Gets the idTitulo attribute of the LogradouroActionForm object
     * 
     * @return The idTitulo value
     */
    public Integer getIdTitulo() {
        return idTitulo;
    }

    /**
     * Sets the idTitulo attribute of the LogradouroActionForm object
     * 
     * @param idTitulo
     *            The new idTitulo value
     */
    public void setIdTitulo(Integer idTitulo) {
        this.idTitulo = idTitulo;
    }

    /**
     * Gets the nome attribute of the LogradouroActionForm object
     * 
     * @return The nome value
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets the nome attribute of the LogradouroActionForm object
     * 
     * @param nome
     *            The new nome value
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Gets the nomeBairro attribute of the LogradouroActionForm object
     * 
     * @return The nomeBairro value
     */
    public String getNomeBairro() {
        return nomeBairro;
    }

    /**
     * Sets the nomeBairro attribute of the LogradouroActionForm object
     * 
     * @param nomeBairro
     *            The new nomeBairro value
     */
    public void setNomeBairro(String nomeBairro) {
        this.nomeBairro = nomeBairro;
    }

    /**
     * Gets the nomeMunicipio attribute of the LogradouroActionForm object
     * 
     * @return The nomeMunicipio value
     */
    public String getNomeMunicipio() {
        return nomeMunicipio;
    }

    /**
     * Sets the nomeMunicipio attribute of the LogradouroActionForm object
     * 
     * @param nomeMunicipio
     *            The new nomeMunicipio value
     */
    public void setNomeMunicipio(String nomeMunicipio) {
        this.nomeMunicipio = nomeMunicipio;
    }

    /**
     * Gets the indicadorUso attribute of the LogradouroActionForm object
     * 
     * @return The indicadorUso value
     */
    public String getIndicadorUso() {
        return indicadorUso;
    }

    /**
     * Sets the indicadorUso attribute of the LogradouroActionForm object
     * 
     * @param indicadorUso
     *            The new indicadorUso value
     */
    public void setIndicadorUso(String indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     */
    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
       /* nome = null;
        idTitulo = null;
        idTipo = null;
        idMunicipio = null;
        nomeMunicipio = null;
        codigoBairro = null;
        nomeBairro = null;
        indicadorUso = null; */
    }

	public String getNomePopular() {
		return nomePopular;
	}

	public void setNomePopular(String nomePopular) {
		this.nomePopular = nomePopular;
	}

	public String getCodigoCEP() {
		return codigoCEP;
	}

	public void setCodigoCEP(String codigoCEP) {
		this.codigoCEP = codigoCEP;
	}

	public String getDescricaoCEP() {
		return descricaoCEP;
	}

	public void setDescricaoCEP(String descricaoCEP) {
		this.descricaoCEP = descricaoCEP;
	}

	public String getCodigoLogradouro() {
		return codigoLogradouro;
	}

	public void setCodigoLogradouro(String codigoLogradouro) {
		this.codigoLogradouro = codigoLogradouro;
	}

}
