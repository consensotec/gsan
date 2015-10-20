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

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

/**
 * Description of the Class
 * 
 * @author compesa
 * @created 19 de Agosto de 2005
 */
public class TarifaSocialCartaoActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;
    private String numero;

    private String tipoCartao;

    private String dataValidade;

    private String numeroParcelas;

    private String numeroCelpe;

    private String valorRendaFamiliar;

    private String tipoRenda;
    
    private String idImovel;
    
    private String motivoRevisao;
    
    private String motivoExclusao;
    
	private String numeroIPTU;
	
	private String numeroContratoCelpe;
	
	private String areaConstruida;
	
	private String areaConstruidaFaixa;
	
	private String numeroMoradores;

    /**
     * < <Descri��o do m�todo>>
     * 
     * @param actionMapping
     *            Descri��o do par�metro
     * @param httpServletRequest
     *            Descri��o do par�metro
     */
    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
        numero = null;
        tipoCartao = null;
        dataValidade = null;
        numeroParcelas = null;
        numeroCelpe = null;
        valorRendaFamiliar = null;
        tipoRenda = null;
        idImovel = null;
        motivoRevisao = null;
        motivoExclusao = null;
        numeroIPTU = null;
    	numeroContratoCelpe = null;
    	areaConstruida = null;
    	areaConstruidaFaixa = null;
    	numeroMoradores = null;
    }

    /**
	 * @return Retorna o campo numeroMoradores.
	 */
	public String getNumeroMoradores() {
		return numeroMoradores;
	}

	/**
	 * @param numeroMoradores O numeroMoradores a ser setado.
	 */
	public void setNumeroMoradores(String numeroMoradores) {
		this.numeroMoradores = numeroMoradores;
	}

	/**
	 * @return Retorna o campo areaConstruida.
	 */
	public String getAreaConstruida() {
		return areaConstruida;
	}

	/**
	 * @param areaConstruida O areaConstruida a ser setado.
	 */
	public void setAreaConstruida(String areaConstruida) {
		this.areaConstruida = areaConstruida;
	}

	/**
	 * @return Retorna o campo areaConstruidaFaixa.
	 */
	public String getAreaConstruidaFaixa() {
		return areaConstruidaFaixa;
	}

	/**
	 * @param areaConstruidaFaixa O areaConstruidaFaixa a ser setado.
	 */
	public void setAreaConstruidaFaixa(String areaConstruidaFaixa) {
		this.areaConstruidaFaixa = areaConstruidaFaixa;
	}

	/**
	 * @return Retorna o campo numeroContratoCelpe.
	 */
	public String getNumeroContratoCelpe() {
		return numeroContratoCelpe;
	}

	/**
	 * @param numeroContratoCelpe O numeroContratoCelpe a ser setado.
	 */
	public void setNumeroContratoCelpe(String numeroContratoCelpe) {
		this.numeroContratoCelpe = numeroContratoCelpe;
	}

	/**
	 * @return Retorna o campo numeroIPTU.
	 */
	public String getNumeroIPTU() {
		return numeroIPTU;
	}

	/**
	 * @param numeroIPTU O numeroIPTU a ser setado.
	 */
	public void setNumeroIPTU(String numeroIPTU) {
		this.numeroIPTU = numeroIPTU;
	}

	/**
     * Retorna o valor de dataValidade
     * 
     * @return O valor de dataValidade
     */
    public String getDataValidade() {
        return dataValidade;
    }

    /**
     * Seta o valor de dataValidade
     * 
     * @param dataValidade
     *            O novo valor de dataValidade
     */
    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    /**
     * Retorna o valor de numero
     * 
     * @return O valor de numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Seta o valor de numero
     * 
     * @param numero
     *            O novo valor de numero
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Retorna o valor de numeroCelpe
     * 
     * @return O valor de numeroCelpe
     */
    public String getNumeroCelpe() {
        return numeroCelpe;
    }

    /**
     * Seta o valor de numeroCelpe
     * 
     * @param numeroCelpe
     *            O novo valor de numeroCelpe
     */
    public void setNumeroCelpe(String numeroCelpe) {
        this.numeroCelpe = numeroCelpe;
    }

    /**
     * Retorna o valor de numeroParcelas
     * 
     * @return O valor de numeroParcelas
     */
    public String getNumeroParcelas() {
        return numeroParcelas;
    }

    /**
     * Seta o valor de numeroParcelas
     * 
     * @param numeroParcelas
     *            O novo valor de numeroParcelas
     */
    public void setNumeroParcelas(String numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

    /**
     * Retorna o valor de tipoCartao
     * 
     * @return O valor de tipoCartao
     */
    public String getTipoCartao() {
        return tipoCartao;
    }

    /**
     * Seta o valor de tipoCartao
     * 
     * @param tipoCartao
     *            O novo valor de tipoCartao
     */
    public void setTipoCartao(String tipoCartao) {
        this.tipoCartao = tipoCartao;
    }

    /**
     * Retorna o valor de tipoRenda
     * 
     * @return O valor de tipoRenda
     */
    public String getTipoRenda() {
        return tipoRenda;
    }

    /**
     * Seta o valor de tipoRenda
     * 
     * @param tipoRenda
     *            O novo valor de tipoRenda
     */
    public void setTipoRenda(String tipoRenda) {
        this.tipoRenda = tipoRenda;
    }

    /**
     * Retorna o valor de valorRendaFamiliar
     * 
     * @return O valor de valorRendaFamiliar
     */
    public String getValorRendaFamiliar() {
        return valorRendaFamiliar;
    }

    /**
     * Seta o valor de valorRendaFamiliar
     * 
     * @param valorRendaFamiliar
     *            O novo valor de valorRendaFamiliar
     */
    public void setValorRendaFamiliar(String valorRendaFamiliar) {
        this.valorRendaFamiliar = valorRendaFamiliar;
    }

	/**
	 * @return Retorna o campo idImovel.
	 */
	public String getIdImovel() {
		return idImovel;
	}

	/**
	 * @param idImovel O idImovel a ser setado.
	 */
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	/**
	 * @return Retorna o campo motivoExclusao.
	 */
	public String getMotivoExclusao() {
		return motivoExclusao;
	}

	/**
	 * @param motivoExclusao O motivoExclusao a ser setado.
	 */
	public void setMotivoExclusao(String motivoExclusao) {
		this.motivoExclusao = motivoExclusao;
	}

	/**
	 * @return Retorna o campo motivoRevisao.
	 */
	public String getMotivoRevisao() {
		return motivoRevisao;
	}

	/**
	 * @param motivoRevisao O motivoRevisao a ser setado.
	 */
	public void setMotivoRevisao(String motivoRevisao) {
		this.motivoRevisao = motivoRevisao;
	}
}
