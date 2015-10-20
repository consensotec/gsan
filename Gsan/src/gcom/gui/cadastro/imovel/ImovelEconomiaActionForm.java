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
package gcom.gui.cadastro.imovel;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 */
public class ImovelEconomiaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String[] idRegistrosRemocao;

    private String areaConstruida;

    private String codigoCliente;

    private String complementoEndereco;

    private String nomeCliente;

    private String numeroContratoEnergia;

    private String numeroIptu;

    private String numeroMorador;

    private String numeroPontoUtilizacao;

    private String quantidadeEconomia;

    private String idCategoria;

    private String idSubCategoria;

    private String idAreaConstruidaFaixa;

    private String idClienteTipo;

    private String botaoAdicionar;

    private String botaoClicado;

    private String botaoRemover;

    /**
     * Retorna o valor de areaConstruida
     * 
     * @return O valor de areaConstruida
     */
    public String getAreaConstruida() {
        return areaConstruida;
    }

    /**
     * Seta o valor de areaConstruida
     * 
     * @param areaConstruida
     *            O novo valor de areaConstruida
     */
    public void setAreaConstruida(String areaConstruida) {
        this.areaConstruida = areaConstruida;
    }

    /**
     * Retorna o valor de codigoCliente
     * 
     * @return O valor de codigoCliente
     */
    public String getCodigoCliente() {
        return codigoCliente;
    }

    /**
     * Seta o valor de codigoCliente
     * 
     * @param codigoCliente
     *            O novo valor de codigoCliente
     */
    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    /**
     * Retorna o valor de complementoEndereco
     * 
     * @return O valor de complementoEndereco
     */
    public String getComplementoEndereco() {
        return complementoEndereco;
    }

    /**
     * Seta o valor de complementoEndereco
     * 
     * @param complementoEndereco
     *            O novo valor de complementoEndereco
     */
    public void setComplementoEndereco(String complementoEndereco) {
        this.complementoEndereco = complementoEndereco;
    }

    /**
     * Retorna o valor de nomeCliente
     * 
     * @return O valor de nomeCliente
     */
    public String getNomeCliente() {
        return nomeCliente;
    }

    /**
     * Seta o valor de nomeCliente
     * 
     * @param nomeCliente
     *            O novo valor de nomeCliente
     */
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    /**
     * Retorna o valor de numeroContratoEnergia
     * 
     * @return O valor de numeroContratoEnergia
     */
    public String getNumeroContratoEnergia() {
        return numeroContratoEnergia;
    }

    /**
     * Seta o valor de numeroContratoEnergia
     * 
     * @param numeroContratoEnergia
     *            O novo valor de numeroContratoEnergia
     */
    public void setNumeroContratoEnergia(String numeroContratoEnergia) {
        this.numeroContratoEnergia = numeroContratoEnergia;
    }

    /**
     * Retorna o valor de numeroIptu
     * 
     * @return O valor de numeroIptu
     */
    public String getNumeroIptu() {
        return numeroIptu;
    }

    /**
     * Seta o valor de numeroIptu
     * 
     * @param numeroIptu
     *            O novo valor de numeroIptu
     */
    public void setNumeroIptu(String numeroIptu) {
        this.numeroIptu = numeroIptu;
    }

    /**
     * Retorna o valor de numeroMorador
     * 
     * @return O valor de numeroMorador
     */
    public String getNumeroMorador() {
        return numeroMorador;
    }

    /**
     * Seta o valor de numeroMorador
     * 
     * @param numeroMorador
     *            O novo valor de numeroMorador
     */
    public void setNumeroMorador(String numeroMorador) {
        this.numeroMorador = numeroMorador;
    }

    /**
     * Retorna o valor de numeroPontoUtilizacao
     * 
     * @return O valor de numeroPontoUtilizacao
     */
    public String getNumeroPontoUtilizacao() {
        return numeroPontoUtilizacao;
    }

    /**
     * Seta o valor de numeroPontoUtilizacao
     * 
     * @param numeroPontoUtilizacao
     *            O novo valor de numeroPontoUtilizacao
     */
    public void setNumeroPontoUtilizacao(String numeroPontoUtilizacao) {
        this.numeroPontoUtilizacao = numeroPontoUtilizacao;
    }

    /**
     * Retorna o valor de quantidadeEconomia
     * 
     * @return O valor de quantidadeEconomia
     */
    public String getQuantidadeEconomia() {
        return quantidadeEconomia;
    }

    /**
     * Seta o valor de quantidadeEconomia
     * 
     * @param quantidadeEconomia
     *            O novo valor de quantidadeEconomia
     */
    public void setQuantidadeEconomia(String quantidadeEconomia) {
        this.quantidadeEconomia = quantidadeEconomia;
    }

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
    }

    /**
     * Retorna o valor de idCategoria
     * 
     * @return O valor de idCategoria
     */
    public String getIdCategoria() {
        return idCategoria;
    }

    /**
     * Retorna o valor de idSubcategoria
     */

    /**
     * Seta o valor de idCategoria
     * 
     * @param idCategoria
     *            O novo valor de idCategoria
     */
    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    /**
     * Seta o valor de idSubcategoria
     * 
     * @return O valor de idAreaConstruidaFaixa
     */
    public String getIdAreaConstruidaFaixa() {
        return idAreaConstruidaFaixa;
    }

    /**
     * Seta o valor de idAreaConstruidaFaixa
     * 
     * @param idAreaConstruidaFaixa
     *            O novo valor de idAreaConstruidaFaixa
     */
    public void setIdAreaConstruidaFaixa(String idAreaConstruidaFaixa) {
        this.idAreaConstruidaFaixa = idAreaConstruidaFaixa;
    }

    /**
     * Seta o valor de idTipoCliente
     * 
     * @return O valor de idSubCategoria
     */

    public String getIdSubCategoria() {
        return idSubCategoria;
    }

    /**
     * Seta o valor de idSubCategoria
     * 
     * @param idSubCategoria
     *            O novo valor de idSubCategoria
     */
    public void setIdSubCategoria(String idSubCategoria) {
        this.idSubCategoria = idSubCategoria;
    }

    /**
     * Retorna o valor de idClienteTipo
     * 
     * @return O valor de idClienteTipo
     */
    public String getIdClienteTipo() {
        return idClienteTipo;
    }

    /**
     * Seta o valor de idClienteTipo
     * 
     * @param idClienteTipo
     *            O novo valor de idClienteTipo
     */
    public void setIdClienteTipo(String idClienteTipo) {
        this.idClienteTipo = idClienteTipo;
    }

    /**
     * Retorna o valor de botaoAdicionar
     * 
     * @return O valor de botaoAdicionar
     */
    public String getBotaoAdicionar() {
        return botaoAdicionar;
    }

    /**
     * Seta o valor de botaoAdicionar
     * 
     * @param botaoAdicionar
     *            O novo valor de botaoAdicionar
     */
    public void setBotaoAdicionar(String botaoAdicionar) {
        this.botaoAdicionar = botaoAdicionar;
    }

    /**
     * Retorna o valor de botaoClicado
     * 
     * @return O valor de botaoClicado
     */
    public String getBotaoClicado() {
        return botaoClicado;
    }

    /**
     * Seta o valor de botaoClicado
     * 
     * @param botaoClicado
     *            O novo valor de botaoClicado
     */
    public void setBotaoClicado(String botaoClicado) {
        this.botaoClicado = botaoClicado;
    }

    /**
     * Retorna o valor de idRegistrosRemocao
     * 
     * @return O valor de idRegistrosRemocao
     */
    public String[] getIdRegistrosRemocao() {
        return idRegistrosRemocao;
    }

    /**
     * Seta o valor de idRegistrosRemocao
     * 
     * @param idRegistrosRemocao
     *            O novo valor de idRegistrosRemocao
     */
    public void setIdRegistrosRemocao(String[] idRegistrosRemocao) {
        this.idRegistrosRemocao = idRegistrosRemocao;
    }

    public String getBotaoRemover() {
        return botaoRemover;
    }

    public void setBotaoRemover(String botaoRemover) {
        this.botaoRemover = botaoRemover;
    }
}
