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
package gcom.spcserasa.bean;

import gcom.seguranca.acesso.usuario.Usuario;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Classe respons�vel por ajudar o caso de uso [UC0671] Gerar Movimento de Inclus�o Negativa��o 
 *
 * @author Marcio Roberto
 * @date 21/11/2007
 */
public class DadosNegativacaoPorImovelHelper {

	private Integer idCliente;
	private Integer idImovel;
	private String cpfCliente;
	private String cnpjCliente;
	private List colecaoConta;
	private List colecaoGuias;
	private Integer qtdItensDebitoImovel;
	private BigDecimal totalDebitosImovel;
	private Integer idClienteRelacaoTipo;
	private Usuario usuarioInclusao;

	//----------------------------------------------------------
	//[UC0671] Gerar Movimento de Inclusao de Negativa��o
	//[SB0007] Gerar Registro de negativacao
	private Integer anoMesReferenciaInicioDebito;
	private Integer anoMesReferenciaFinalDebito;
		
	/**
	 * Construtor  
	 * @param idCliente
	 * @param cpfCliente
	 * @param cnpjCliente
	 */
	public DadosNegativacaoPorImovelHelper( 
			Integer idCliente,
			String cpfCliente,
			String cnpjCliente){
		this.idCliente = idCliente;
		this.cpfCliente = cpfCliente;
		this.cnpjCliente = cnpjCliente;
	}
	/**
	 * Construtor  
	 * @param idCliente
	 * @param cpfCliente
	 * @param cnpjCliente
	 */
	public DadosNegativacaoPorImovelHelper(){ 
	}
	
	
    /**
     *  Descri��o do m�todo>>
     * 
     * @param other
     *            Descri��o do par�metro
     * @return Descri��o do retorno
     */
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DadosNegativacaoPorImovelHelper)) {
            return false;
        }
        DadosNegativacaoPorImovelHelper castOther = (DadosNegativacaoPorImovelHelper) other;

        return new EqualsBuilder().append(this.getIdImovel(), castOther.getIdImovel())
                .isEquals();
    }

   
   /**
     * Description of the Method
     * 
     * @return Description of the Return Value
     */
    public int hashCode() {
        return new HashCodeBuilder().append(getIdImovel())
                .toHashCode();
    }

	public String getCnpjCliente() {
		return cnpjCliente;
	}
	public void setCnpjCliente(String cnpjCliente) {
		this.cnpjCliente = cnpjCliente;
	}
	public String getCpfCliente() {
		return cpfCliente;
	}
	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	public Integer getQtdItensDebitoImovel() {
		return qtdItensDebitoImovel;
	}
	public void setQtdItensDebitoImovel(Integer qtdItensDebitoImovel) {
		this.qtdItensDebitoImovel = qtdItensDebitoImovel;
	}
	public BigDecimal getTotalDebitosImovel() {
		return totalDebitosImovel;
	}
	public void setTotalDebitosImovel(BigDecimal totalDebitosImovel) {
		this.totalDebitosImovel = totalDebitosImovel;
	}
	public Integer getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}
	/**
	 * @return Retorna o campo colecaoConta.
	 */
	public List getColecaoConta() {
		return colecaoConta;
	}
	/**
	 * @param colecaoConta O colecaoConta a ser setado.
	 */
	public void setColecaoConta(List colecaoConta) {
		this.colecaoConta = colecaoConta;
	}
	/**
	 * @return Retorna o campo colecaoGuias.
	 */
	public List getColecaoGuias() {
		return colecaoGuias;
	}
	/**
	 * @param colecaoGuias O colecaoGuias a ser setado.
	 */
	public void setColecaoGuias(List colecaoGuias) {
		this.colecaoGuias = colecaoGuias;
	}
	public Integer getIdClienteRelacaoTipo() {
		return idClienteRelacaoTipo;
	}
	public void setIdClienteRelacaoTipo(Integer idClienteRelacaoTipo) {
		this.idClienteRelacaoTipo = idClienteRelacaoTipo;
	}
	public Integer getAnoMesReferenciaInicioDebito() {
		return anoMesReferenciaInicioDebito;
	}
	public void setAnoMesReferenciaInicioDebito(Integer anoMesReferenciaInicioDebito) {
		this.anoMesReferenciaInicioDebito = anoMesReferenciaInicioDebito;
	}
	public Integer getAnoMesReferenciaFinalDebito() {
		return anoMesReferenciaFinalDebito;
	}
	public void setAnoMesReferenciaFinalDebito(Integer anoMesReferenciaFinalDebito) {
		this.anoMesReferenciaFinalDebito = anoMesReferenciaFinalDebito;
	}
	public Usuario getUsuarioInclusao() {
		return usuarioInclusao;
	}
	public void setUsuarioInclusao(Usuario usuarioInclusao) {
		this.usuarioInclusao = usuarioInclusao;
	}
	
}