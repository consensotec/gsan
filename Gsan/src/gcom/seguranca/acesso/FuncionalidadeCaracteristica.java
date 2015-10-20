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
package gcom.seguranca.acesso;

import java.io.Serializable;



/**
 * Representa as caracter�sticas de uma funcionalidade
 *
 * @author   rodrigo
 */
public class FuncionalidadeCaracteristica implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6996508709453691626L;

	/**
     * Description of the Field
     */
    ///public static int FUNCIONALIDADE = 1;

    /**
     * Description of the Field
     */
    ///public static int OPERACAO = 2;
    /**
     * Indica se o action � funcionalidade ou opera��o para o sistema
     */
    private int especificacao;
    /**
     * Caminho da funcionalidade
     */
    private String caminhoFuncionalidade;
    /**
     * T�tulo da funcionalidade para o menu
     */
    private String tituloFuncionalidade;
    /**
     * Id do m�dulo
     */
    private Integer moduloId;
    /**
     * Caminho que faz parte do menu
     */
    private String caminhoMenu;

    private Integer tipoFuncionalidadeId;

    private Integer operacaoId;
    /**
     * Campo que indica quais as funcionalidades que dependem desta
     */
    private String[] funcionalidadesDependentes;

    //Indica se a funcionalidade deve ser mostrada no menu
    private boolean pontoEntrada;

    //Representa o caminho da funcionalidade quando a Funcionalidade representa uma opera��o
    private String caminhoFuncionalidadePaiOperacao;

    private Class classe;


    /**
     * Construtor da classe FuncionalidadeCaracteristica
     */
    /*
     *  public FuncionalidadeCaracteristica(int especificacao,
     *  String caminhoFuncionalidade,
     *  String tituloFuncionalidade,
     *  Integer moduloId,
     *  String caminhoMenu,
     *  Integer tipoFuncionalidadeId,
     *  Integer operacaoId,
     *  String[] funcionalidadesDependentes,
     *  boolean pontoEntrada) {
     *  this.especificacao = especificacao;
     *  this.caminhoFuncionalidade = caminhoFuncionalidade;
     *  this.tituloFuncionalidade = tituloFuncionalidade;
     *  this.moduloId = moduloId;
     *  this.caminhoMenu = caminhoMenu;
     *  this.tipoFuncionalidadeId = tipoFuncionalidadeId;
     *  this.operacaoId = operacaoId;
     *  this.funcionalidadesDependentes = funcionalidadesDependentes;
     *  this.pontoEntrada = pontoEntrada;
     *  }
     */
    /**
     * Construtor da classe FuncionalidadeCaracteristica
     *
     * @param especificacao               Descri��o do par�metro
     * @param caminhoFuncionalidade       Descri��o do par�metro
     * @param tituloFuncionalidade        Descri��o do par�metro
     * @param moduloId                    Descri��o do par�metro
     * @param caminhoMenu                 Descri��o do par�metro
     * @param funcionalidadesDependentes  Descri��o do par�metro
     * @param pontoEntrada                Descri��o do par�metro
     */
    public FuncionalidadeCaracteristica(int especificacao,
            String caminhoFuncionalidade,
            String tituloFuncionalidade,
            Integer moduloId,
            String caminhoMenu,
            String[] funcionalidadesDependentes,
            boolean pontoEntrada) {
        this.especificacao = especificacao;
        this.caminhoFuncionalidade = caminhoFuncionalidade;
        this.tituloFuncionalidade = tituloFuncionalidade;
        this.moduloId = moduloId;
        this.caminhoMenu = caminhoMenu;
        this.funcionalidadesDependentes = funcionalidadesDependentes;
        this.pontoEntrada = pontoEntrada;

    }

    /**
     * Construtor da classe FuncionalidadeCaracteristica
     *
     * @param especificacao               Descri��o do par�metro
     * @param caminhoFuncionalidade       Descri��o do par�metro
     * @param tituloFuncionalidade        Descri��o do par�metro
     * @param moduloId                    Descri��o do par�metro
     * @param caminhoMenu                 Descri��o do par�metro
     * @param funcionalidadesDependentes  Descri��o do par�metro
     * @param classe                      Descri��o do par�metro
     * @param pontoEntrada                Descri��o do par�metro
     */
    public FuncionalidadeCaracteristica(int especificacao,
            String caminhoFuncionalidade,
            String tituloFuncionalidade,
            Integer moduloId,
            String caminhoMenu,
            String[] funcionalidadesDependentes,
            Class classe,
            boolean pontoEntrada) {
        this.especificacao = especificacao;
        this.caminhoFuncionalidade = caminhoFuncionalidade;
        this.tituloFuncionalidade = tituloFuncionalidade;
        this.moduloId = moduloId;
        this.caminhoMenu = caminhoMenu;
        this.funcionalidadesDependentes = funcionalidadesDependentes;
        this.pontoEntrada = pontoEntrada;
        this.classe = classe;

    }


    /**
     * Construtor da classe FuncionalidadeCaracteristica
     *
     * @param especificacao                     Descri��o do par�metro
     * @param caminhoFuncionalidade             Descri��o do par�metro
     * @param tituloFuncionalidade              Descri��o do par�metro
     * @param moduloId                          Descri��o do par�metro
     * @param caminhoMenu                       Descri��o do par�metro
     * @param funcionalidadesDependentes        Descri��o do par�metro
     * @param pontoEntrada                      Descri��o do par�metro
     * @param caminhoFuncionalidadePaiOperacao  Descri��o do par�metro
     */
    public FuncionalidadeCaracteristica(int especificacao,
            String caminhoFuncionalidade,
            String tituloFuncionalidade,
            Integer moduloId,
            String caminhoMenu,
            String[] funcionalidadesDependentes,
            boolean pontoEntrada,
            String caminhoFuncionalidadePaiOperacao) {
        this.especificacao = especificacao;
        this.caminhoFuncionalidade = caminhoFuncionalidade;
        this.tituloFuncionalidade = tituloFuncionalidade;
        this.moduloId = moduloId;
        this.caminhoMenu = caminhoMenu;
        this.funcionalidadesDependentes = funcionalidadesDependentes;
        this.pontoEntrada = pontoEntrada;
        this.caminhoFuncionalidadePaiOperacao = caminhoFuncionalidadePaiOperacao;

    }

    /**
     * Construtor da classe FuncionalidadeCaracteristica
     *
     * @param especificacao                     Descri��o do par�metro
     * @param caminhoFuncionalidade             Descri��o do par�metro
     * @param tituloFuncionalidade              Descri��o do par�metro
     * @param moduloId                          Descri��o do par�metro
     * @param caminhoMenu                       Descri��o do par�metro
     * @param funcionalidadesDependentes        Descri��o do par�metro
     * @param pontoEntrada                      Descri��o do par�metro
     * @param caminhoFuncionalidadePaiOperacao  Descri��o do par�metro
     * @param classe                            Descri��o do par�metro
     */
    public FuncionalidadeCaracteristica(int especificacao,
            String caminhoFuncionalidade,
            String tituloFuncionalidade,
            Integer moduloId,
            String caminhoMenu,
            String[] funcionalidadesDependentes,
            boolean pontoEntrada,
            String caminhoFuncionalidadePaiOperacao,
            Class classe) {
        this.especificacao = especificacao;
        this.caminhoFuncionalidade = caminhoFuncionalidade;
        this.tituloFuncionalidade = tituloFuncionalidade;
        this.moduloId = moduloId;
        this.caminhoMenu = caminhoMenu;
        this.funcionalidadesDependentes = funcionalidadesDependentes;
        this.pontoEntrada = pontoEntrada;
        this.caminhoFuncionalidadePaiOperacao = caminhoFuncionalidadePaiOperacao;
        this.classe = classe;

    }


    /**
     * Retorna o valor de caminhoFuncionalidade
     *
     * @return   O valor de caminhoFuncionalidade
     */
    public String getCaminhoFuncionalidade() {
        return caminhoFuncionalidade;
    }

    /**
     * Seta o valor de caminhoFuncionalidade
     *
     * @param caminhoFuncionalidade  O novo valor de caminhoFuncionalidade
     */
    public void setCaminhoFuncionalidade(String caminhoFuncionalidade) {
        this.caminhoFuncionalidade = caminhoFuncionalidade;
    }

    /**
     * Retorna o valor de caminhoMenu
     *
     * @return   O valor de caminhoMenu
     */
    public String getCaminhoMenu() {
        return caminhoMenu;
    }

    /**
     * Seta o valor de caminhoMenu
     *
     * @param caminhoMenu  O novo valor de caminhoMenu
     */
    public void setCaminhoMenu(String caminhoMenu) {
        this.caminhoMenu = caminhoMenu;
    }

    /**
     * Retorna o valor de especificacao
     *
     * @return   O valor de especificacao
     */
    public int getEspecificacao() {
        return especificacao;
    }

    /**
     * Seta o valor de especificacao
     *
     * @param especificacao  O novo valor de especificacao
     */
    public void setEspecificacao(int especificacao) {
        this.especificacao = especificacao;
    }

    /**
     * Retorna o valor de funcionalidadesDependentes
     *
     * @return   O valor de funcionalidadesDependentes
     */
    public String[] getFuncionalidadesDependentes() {
        return funcionalidadesDependentes;
    }

    /**
     * Seta o valor de funcionalidadesDependentes
     *
     * @param funcionalidadesDependentes  O novo valor de
     *      funcionalidadesDependentes
     */
    public void setFuncionalidadesDependentes(String[] funcionalidadesDependentes) {
        this.funcionalidadesDependentes = funcionalidadesDependentes;
    }

    /**
     * Retorna o valor de moduloId
     *
     * @return   O valor de moduloId
     */
    public Integer getModuloId() {
        return moduloId;
    }

    /**
     * Seta o valor de moduloId
     *
     * @param moduloId  O novo valor de moduloId
     */
    public void setModuloId(Integer moduloId) {
        this.moduloId = moduloId;
    }

    /**
     * Retorna o valor de tituloFuncionalidade
     *
     * @return   O valor de tituloFuncionalidade
     */
    public String getTituloFuncionalidade() {
        return tituloFuncionalidade;
    }

    /**
     * Seta o valor de tituloFuncionalidade
     *
     * @param tituloFuncionalidade  O novo valor de tituloFuncionalidade
     */
    public void setTituloFuncionalidade(String tituloFuncionalidade) {
        this.tituloFuncionalidade = tituloFuncionalidade;
    }

    /**
     * Retorna o valor de tipoFuncionalidade
     *
     * @return   O valor de tipoFuncionalidade
     */
    public Integer getTipoFuncionalidadeId() {
        return tipoFuncionalidadeId;
    }

    /**
     * Seta o valor de tipoFuncionalidade
     *
     * @param tipoFuncionalidadeId  O novo valor de tipoFuncionalidade
     */
    public void setTipoFuncionalidadeId(Integer tipoFuncionalidadeId) {
        this.tipoFuncionalidadeId = tipoFuncionalidadeId;
    }

    /**
     * Retorna o valor de operacaoId
     *
     * @return   O valor de operacaoId
     */
    public Integer getOperacaoId() {
        return operacaoId;
    }

    /**
     * Seta o valor de operacaoId
     *
     * @param operacaoId  O novo valor de operacaoId
     */
    public void setOperacaoId(Integer operacaoId) {
        this.operacaoId = operacaoId;
    }

    /**
     * Retorna o valor de pontoEntrada
     *
     * @return   O valor de pontoEntrada
     */
    public boolean isPontoEntrada() {
        return pontoEntrada;
    }

    /**
     * Seta o valor de pontoEntrada
     *
     * @param pontoEntrada  O novo valor de pontoEntrada
     */
    public void setPontoEntrada(boolean pontoEntrada) {
        this.pontoEntrada = pontoEntrada;
    }

    /**
     * Retorna o valor de caminhoFuncionalidadePaiOperacao
     *
     * @return   O valor de caminhoFuncionalidadePaiOperacao
     */
    public String getCaminhoFuncionalidadePaiOperacao() {
        return caminhoFuncionalidadePaiOperacao;
    }

    /**
     * Seta o valor de caminhoFuncionalidadePaiOperacao
     *
     * @param caminhoFuncionalidadePaiOperacao  O novo valor de
     *      caminhoFuncionalidadePaiOperacao
     */
    public void setCaminhoFuncionalidadePaiOperacao(String caminhoFuncionalidadePaiOperacao) {
        this.caminhoFuncionalidadePaiOperacao = caminhoFuncionalidadePaiOperacao;
    }

    /**
     * Retorna o valor de nomeClasse
     *
     * @return   O valor de nomeClasse
     */
    public Class getClasse() {
        return classe;
    }

    /**
     * Seta o valor de nomeClasse
     *
     * @param nomeClasse  O novo valor de nomeClasse
     */
    public void setClasse(Class nomeClasse) {
        this.classe = nomeClasse;
    }

}
