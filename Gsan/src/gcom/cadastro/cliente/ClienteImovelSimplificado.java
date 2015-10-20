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
package gcom.cadastro.cliente;

import gcom.cadastro.endereco.EnderecoReferencia;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;

import java.io.Serializable;
import java.util.Date;

/**
 * bean simplificado respons�vel para trazer s� o necess�rio
 * 
 * @author S�vio Luiz
 * @created 17 de Maio de 2004
 */

public class ClienteImovelSimplificado implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * persistent field
     */
    private gcom.cadastro.imovel.Imovel imovel;

    /**
     * persistent field
     */
    private gcom.cadastro.cliente.Cliente cliente;

    /**
     * persistent field
     */   
    private Date dataFimRelacao;

    /**
     * Constructor for the ClienteImovelSimplificado object
     * 
     * @param idImovel
     *            Description of the Parameter
     * @param numeroImovel
     *            Description of the Parameter
     * @param cep
     *            Description of the Parameter
     * @param logradouro
     *            Description of the Parameter
     * @param nomeCliente
     *            Description of the Parameter
     */
	public ClienteImovelSimplificado(Integer idImovel, String numeroImovel, LogradouroCep logradouroCep, 
			                         LogradouroBairro logradouroBairro, Quadra quadra, EnderecoReferencia enderecoReferencia, 
			                         String complementoEndereco, String nomeCliente, Integer idCliente, 
			                         SetorComercial setorComercial,Localidade localidade, Date dataFimRelacao) {
		this.imovel = new Imovel(idImovel, numeroImovel, logradouroCep, 
				                 logradouroBairro, quadra, enderecoReferencia, 
				                 complementoEndereco, setorComercial, localidade);
		this.cliente = new Cliente(nomeCliente, idCliente);
		this.dataFimRelacao = dataFimRelacao; 
	}

    /**
     * Constructor for the ClienteImovelSimplificado object
     * 
     * @param idImovel
     *            Description of the Parameter
     * @param numeroImovel
     *            Description of the Parameter
     * @param cep
     *            Description of the Parameter
     * @param logradouro
     *            Description of the Parameter
     * @param nomeCliente
     *            Description of the Parameter
     */
	public ClienteImovelSimplificado(Integer idImovel, String numeroImovel,
			LogradouroCep logradouroCep, LogradouroBairro logradouroBairro, Quadra quadra,
			EnderecoReferencia enderecoReferencia, String complementoEndereco,
			String nomeCliente, Integer idCliente, SetorComercial setorComercial,Localidade localidade, Date dataFimRelacao, Date ultimaAlteracao) {
		this.imovel = new Imovel(idImovel, numeroImovel, logradouroCep, logradouroBairro, quadra,
				enderecoReferencia, complementoEndereco, setorComercial,localidade,ultimaAlteracao);
		this.cliente = new Cliente(nomeCliente, idCliente);
		this.dataFimRelacao = dataFimRelacao; 
	}	
	
	/**
     * Gets the cliente attribute of the ClienteImovelSimplificado object
     * 
     * @return The cliente value
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Sets the cliente attribute of the ClienteImovelSimplificado object
     * 
     * @param cliente
     *            The new cliente value
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Gets the imovel attribute of the ClienteImovelSimplificado object
     * 
     * @return The imovel value
     */
    public Imovel getImovel() {
        return imovel;
    }

    /**
     * Sets the imovel attribute of the ClienteImovelSimplificado object
     * 
     * @param imovel
     *            The new imovel value
     */
    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }
    
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ClienteImovelSimplificado)) {
            return false;
        }
        ClienteImovelSimplificado castOther = (ClienteImovelSimplificado) other;

        return this.getImovel().getId().equals(castOther.getImovel().getId());
    }

	public Date getDataFimRelacao() {
		return dataFimRelacao;
	}

	public void setDataFimRelacao(Date dataFimRelacao) {
		this.dataFimRelacao = dataFimRelacao;
	}


}
