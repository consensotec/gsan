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
package gcom.util.filtro;

import java.io.Serializable;

/**
 * Representa um par�metro gen�rico do filtro
 * 
 * @author rodrigo
 */
public abstract class FiltroParametro implements Serializable {

	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Description of the Field
     */
    public final static String CONECTOR_AND = " AND ";

    /**
     * Description of the Field
     */
    public final static String CONECTOR_OR = "  OR ";

    /**
     * Description of the Field
     */
    protected String conector = CONECTOR_AND;

    /**
     * Description of the Field
     */
    protected String nomeAtributo;

    /**
     * Description of the Field
     */
    protected int numeroArgumentosIsoladosPeloConector = 0;

    /**
     * Construtor da classe FiltroParametro
     * 
     * @param nomeAtributo
     *            nome do atributo de que ser� feita a filtragem
     */
    public FiltroParametro(String nomeAtributo) {
        this.nomeAtributo = nomeAtributo;

    }

    /**
     * Construtor da classe FiltroParametro
     * 
     * @param nomeAtributo
     *            nome do atributo de que ser� feita a filtragem
     * @param conector
     *            conector l�gico para a query condicional
     */
    public FiltroParametro(String nomeAtributo, String conector) {
        this.nomeAtributo = nomeAtributo;
        this.conector = conector;

    }

    /**
     * Construtor da classe FiltroParametro
     * 
     * @param nomeAtributo
     *            nome do atributo de que ser� feita a filtragem
     * @param conector
     *            conector l�gico para a query condicional
     * @param numeroArgumentosIsoladosPeloConector
     *            N�mero de argumentos que ser�o isolados pelo conector por
     *            '(arg1 OR arg2 OR arg3)'
     */
    public FiltroParametro(String nomeAtributo, String conector,
            int numeroArgumentosIsoladosPeloConector) {
        this.nomeAtributo = nomeAtributo;
        this.conector = conector;
        this.numeroArgumentosIsoladosPeloConector = numeroArgumentosIsoladosPeloConector;
    }
    
    /**
     * Construtor da classe FiltroParametro
     * 
     * @param nomeAtributo
     *            nome do atributo de que ser� feita a filtragem
     * @param numeroArgumentosIsoladosPeloConector
     *            N�mero de argumentos que ser�o isolados pelo conector por
     *            '(arg1 OR arg2 OR arg3)'
     */
    public FiltroParametro(String nomeAtributo, int numeroArgumentosIsoladosPeloConector) {
        this.nomeAtributo = nomeAtributo;
        this.numeroArgumentosIsoladosPeloConector = numeroArgumentosIsoladosPeloConector;
    }

    /**
     * Retorna o valor de nomeAtributo
     * 
     * @return O valor de nomeAtributo
     */
    public String getNomeAtributo() {
        return nomeAtributo;
    }

    /**
     * Seta o valor de nomeAtributo
     * 
     * @param nomeAtributo
     *            O novo valor de nomeAtributo
     */
    public void setNomeAtributo(String nomeAtributo) {
        this.nomeAtributo = nomeAtributo;
    }

    /**
     * Retorna o valor de conector
     * 
     * @return O valor de conector
     */
    public String getConector() {
        return conector;
    }

    /**
     * Seta o valor de conector
     * 
     * @param conector
     *            O novo valor de conector
     */
    public void setConector(String conector) {
        this.conector = conector;
    }

    /**
     * Retorna o valor de numeroArgumentosIsoladosPeloConector
     * 
     * @return O valor de numeroArgumentosIsoladosPeloConector
     */
    public int getNumeroArgumentosIsoladosPeloConector() {
        return numeroArgumentosIsoladosPeloConector;
    }

}
