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
package gcom.cadastro.endereco;

import gcom.interceptor.ObjetoGcom;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Hibernate CodeGenerator
 * @created 29 de Agosto de 2005
 */
public class Cep extends ObjetoGcom {
	
	private static final long serialVersionUID = 1L;

    /**
     * identifier field
     */
    private Integer cepId;

    /**
     * nullable persistent field
     */
    private Integer codigo;

    /**
     * nullable persistent field
     */
    private String sigla;
    
    /**
     * nullable persistent field
     */
    private String descricaoIntervaloNumeracao;

    /**
     * nullable persistent field
     */
    private String municipio;

    /**
     * nullable persistent field
     */
    private String bairro;

    /**
     * nullable persistent field
     */
    private String logradouro;
    
    /**
     * nullable persistent field
     */
    private String descricaoTipoLogradouro;

    /**
     * nullable persistent field
     */
    private Short indicadorUso;

    /**
     * nullable persistent field
     */
    private Date ultimaAlteracao;

    /**
     * persistent field
     */
    private gcom.cadastro.endereco.CepTipo cepTipo;
    
    //Usado no ExibirAtualizarLogradouroAtualizacaoCadastralPopUpAction 
    private String idCepAtualizacaoCadastral;
    
    /**
     * full constructor
     * 
     * @param codigo
     *            Descri��o do par�metro
     * @param sigla
     *            Descri��o do par�metro
     * @param municipio
     *            Descri��o do par�metro
     * @param bairro
     *            Descri��o do par�metro
     * @param logradouro
     *            Descri��o do par�metro
     * @param descricaoTipoLogradouro
     *            Descri��o do par�metro
     * @param indicadorUso
     *            Descri��o do par�metro
     * @param ultimaAlteracao
     *            Descri��o do par�metro
     * @param cepTipo
     *            Descri��o do par�metro
     */
    public Cep(Integer codigo, String sigla, String municipio, String bairro,
            String logradouro, String descricaoTipoLogradouro,
            Short indicadorUso, Date ultimaAlteracao,
            gcom.cadastro.endereco.CepTipo cepTipo) {
        this.codigo = codigo;
        this.sigla = sigla;
        this.municipio = municipio;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.descricaoTipoLogradouro = descricaoTipoLogradouro;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.cepTipo = cepTipo;
    }
    
    /**
     * full constructor
     * 
     * @param codigo
     *            Descri��o do par�metro
     * @param sigla
     *            Descri��o do par�metro
     * @param municipio
     *            Descri��o do par�metro
     * @param bairro
     *            Descri��o do par�metro
     * @param logradouro
     *            Descri��o do par�metro
     * @param descricaoTipoLogradouro
     *            Descri��o do par�metro
     * @param indicadorUso
     *            Descri��o do par�metro
     * @param ultimaAlteracao
     *            Descri��o do par�metro
     * @param cepTipo
     *            Descri��o do par�metro
     */
    public Cep(Integer codigo, String sigla, String municipio, String bairro,
            String logradouro, String descricaoTipoLogradouro,
            Short indicadorUso, Date ultimaAlteracao,
            gcom.cadastro.endereco.CepTipo cepTipo, String descricaoIntervaloNumeracao) {
        this.codigo = codigo;
        this.sigla = sigla;
        this.municipio = municipio;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.descricaoTipoLogradouro = descricaoTipoLogradouro;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.cepTipo = cepTipo;
        this.descricaoIntervaloNumeracao = descricaoIntervaloNumeracao;
    }
	/**
     * default constructor
     * 
     * @param cepId
     *            Description of the Parameter
     * @param codigo
     *            Description of the Parameter
     * @param sigla
     *            Description of the Parameter
     * @param municipio
     *            Description of the Parameter
     * @param bairro
     *            Description of the Parameter
     * @param logradouro
     *            Description of the Parameter
     * @param descricaoTipoLogradouro
     *            Description of the Parameter
     * @param cepTipo
     *            Description of the Parameter
     */
    public Cep(Integer cepId, Integer codigo, CepTipo cepTipo) {
        this.cepId = cepId;
        this.codigo = codigo;
        // this.sigla = sigla;
        // this.municipio = municipio;
        // this.bairro = bairro;
        //this.logradouro = logradouro;
        // this.descricaoTipoLogradouro = descricaoTipoLogradouro;
        this.cepTipo = cepTipo;

    }

    /**
     * default constructor
     */
    public Cep() {
    }

    /**
     * minimal constructor
     * 
     * @param cepTipo
     *            Descri��o do par�metro
     */
    public Cep(gcom.cadastro.endereco.CepTipo cepTipo) {
        this.cepTipo = cepTipo;
    }

    /**
     * Retorna o valor de cepId
     * 
     * @return O valor de cepId
     */
    public Integer getCepId() {
        return this.cepId;
    }

    /**
	 * @return Retorna o campo descricaoIntervaloNumeracao.
	 */
	public String getDescricaoIntervaloNumeracao() {
		return descricaoIntervaloNumeracao;
	}

	/**
	 * @param descricaoIntervaloNumeracao O descricaoIntervaloNumeracao a ser setado.
	 */
	public void setDescricaoIntervaloNumeracao(String descricaoIntervaloNumeracao) {
		this.descricaoIntervaloNumeracao = descricaoIntervaloNumeracao;
	}

    /**
     * Seta o valor de cepId
     * 
     * @param cepId
     *            O novo valor de cepId
     */
    public void setCepId(Integer cepId) {
        this.cepId = cepId;
    }

    /**
     * Retorna o valor de codigo
     * 
     * @return O valor de codigo
     */
    public Integer getCodigo() {
        return this.codigo;
    }

    /**
     * Seta o valor de codigo
     * 
     * @param codigo
     *            O novo valor de codigo
     */
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    /**
     * Retorna o valor de sigla
     * 
     * @return O valor de sigla
     */
    public String getSigla() {
        return this.sigla;
    }

    /**
     * Seta o valor de sigla
     * 
     * @param sigla
     *            O novo valor de sigla
     */
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    /**
     * Retorna o valor de municipio
     * 
     * @return O valor de municipio
     */
    public String getMunicipio() {
        return this.municipio;
    }

    /**
     * Seta o valor de municipio
     * 
     * @param municipio
     *            O novo valor de municipio
     */
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    /**
     * Retorna o valor de bairro
     * 
     * @return O valor de bairro
     */
    public String getBairro() {
        return this.bairro;
    }

    /**
     * Seta o valor de bairro
     * 
     * @param bairro
     *            O novo valor de bairro
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * Retorna o valor de logradouro
     * 
     * @return O valor de logradouro
     */
    public String getLogradouro() {
        return this.logradouro;
    }

    /**
     * Seta o valor de logradouro
     * 
     * @param logradouro
     *            O novo valor de logradouro
     */
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    /**
     * Retorna o valor de descricaoTipoLogradouro
     * 
     * @return O valor de descricaoTipoLogradouro
     */
    public String getDescricaoTipoLogradouro() {
        return this.descricaoTipoLogradouro;
    }

    /**
     * Seta o valor de descricaoTipoLogradouro
     * 
     * @param descricaoTipoLogradouro
     *            O novo valor de descricaoTipoLogradouro
     */
    public void setDescricaoTipoLogradouro(String descricaoTipoLogradouro) {
        this.descricaoTipoLogradouro = descricaoTipoLogradouro;
    }

    /**
     * Retorna o valor de indicadorUso
     * 
     * @return O valor de indicadorUso
     */
    public Short getIndicadorUso() {
        return this.indicadorUso;
    }

    /**
     * Seta o valor de indicadorUso
     * 
     * @param indicadorUso
     *            O novo valor de indicadorUso
     */
    public void setIndicadorUso(Short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    /**
     * Retorna o valor de ultimaAlteracao
     * 
     * @return O valor de ultimaAlteracao
     */
    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    /**
     * Seta o valor de ultimaAlteracao
     * 
     * @param ultimaAlteracao
     *            O novo valor de ultimaAlteracao
     */
    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /**
     * Retorna o valor de cepTipo
     * 
     * @return O valor de cepTipo
     */
    public gcom.cadastro.endereco.CepTipo getCepTipo() {
        return this.cepTipo;
    }

    /**
     * Seta o valor de cepTipo
     * 
     * @param cepTipo
     *            O novo valor de cepTipo
     */
    public void setCepTipo(gcom.cadastro.endereco.CepTipo cepTipo) {
        this.cepTipo = cepTipo;
    }

    /**
     * Retorna o valor de idCepAtualizacaoCadastral
     * 
     * @return O valor de idCepAtualizacaoCadastral
     */
    public String getIdCepAtualizacaoCadastral() {
		return idCepAtualizacaoCadastral;
	}

    /**
     * Seta o valor de idCepAtualizacaoCadastral
     * 
     * @param cepTipo
     *            O novo valor de cepTipo
     */
	public void setIdCepAtualizacaoCadastral(String idCepAtualizacaoCadastral) {
		this.idCepAtualizacaoCadastral = idCepAtualizacaoCadastral;
	}

	/**
     * < <Descri��o do m�todo>>
     * 
     * @return Descri��o do retorno
     */
    public String toString() {
        return new ToStringBuilder(this).append("cepId", getCepId()).toString();
    }

    /**
     * Retorna o valor de cepFormatado
     * 
     * @return O valor de cepFormatado
     */
    public String getCepFormatado() {
        String codigo = this.codigo.toString();
        
        if(codigo.length()==8){
        	codigo = codigo.substring(0, 5) + "-" + codigo.substring(5, 8);
        }
        
        return codigo;
    }
    
    public static String formatarCep( Integer cep ){
        String codigo = cep.toString();

        codigo = codigo.substring(0, 5) + "-" + codigo.substring(5, 8);
        return codigo;    	
    }
    
    public static String formatarCep( String cep ){
        String codigo = cep;

        codigo = codigo.substring(0, 5) + "-" + codigo.substring(5, 8);
        return codigo;    	
    }    
    
    
    /**
     * Author: Raphael Rossiter
     * Data: 04/02/2006
     * @param logradouro
     * @return Descri��o completa do logradouro (Tipo + Titulo + Nome)
     */
    public String getDescricaoLogradouroFormatada(){
    	
    	String retorno = "";
    	
    	if (this.getDescricaoTipoLogradouro() != null){
    		retorno = this.getDescricaoTipoLogradouro();
    	}
    	
    	if (this.getLogradouro() != null){
    		
    		if (retorno.length() > 0) {
    			retorno = retorno + " " + this.getLogradouro();
    		}
    		else {
    			retorno = this.getLogradouro();
    		}
    		
    	}
    	
    	return retorno;
    }

    /**
     * < <Descri��o do m�todo>>
     * 
     * @param other
     *            Descri��o do par�metro
     * @return Descri��o do retorno
     */
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Cep)) {
            return false;
        }
        Cep castOther = (Cep) other;

        return this.getCodigo().equals(castOther.getCodigo());
    }

    /**
     * < <Descri��o do m�todo>>
     * 
     * @param other
     *            Descri��o do par�metro
     * @return Descri��o do retorno
     */
    public boolean equalsCompleto(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Cep)) {
            return false;
        }
        Cep castOther = (Cep) other;

        return (this.getCodigo().equals(castOther.getCodigo()))
                && (this.getSigla().equals(castOther.getSigla()))
                && (this.getMunicipio().equals(castOther.getMunicipio()))
                && (this.getBairro().equals(castOther.getBairro()))
                && (this.getLogradouro().equals(castOther.getLogradouro()))
                && (this.getDescricaoTipoLogradouro().equals(castOther
                        .getDescricaoTipoLogradouro()));
    }
    
    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "cepId";
		return retorno;
	}

}
