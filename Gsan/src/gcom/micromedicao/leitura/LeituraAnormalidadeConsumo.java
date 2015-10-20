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
package gcom.micromedicao.leitura;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Hibernate CodeGenerator
 */
public class LeituraAnormalidadeConsumo implements Serializable {
	private static final long serialVersionUID = 1L;
    /**
     * identifier field
     */
    private Integer id;

    /**
     * nullable persistent field
     */
    private String descricaoConsumoACobrar;

    /**
     * nullable persistent field
     */
    private Short indicadorSemLeitura;

    /**
     * nullable persistent field
     */
    private Short indicadorComLeitura;

    /**
     * nullable persistent field
     */
    private Short indicadorUso;

    /**
     * nullable persistent field
     */
    private Date ultimaAlteracao;

    //--CONSTANTES
    /**
     * Description of the Field
     */
    public final static Integer NAO_OCORRE = new Integer(0);

    /**
     * Description of the Field
     */
    public final static Integer MINIMO = new Integer(1);

    /**
     * Description of the Field
     */
    public final static Integer MEDIA = new Integer(2);

    /**
     * Description of the Field
     */
    public final static Integer NORMAL = new Integer(3);

    /**
     * Description of the Field
     */
    public final static Integer MAIOR_ENTRE_CONSUMO_MEDIO_HIDROMETRO_CONSUMO_MEDIDO = new Integer(
            5);

    /**
     * Description of the Field
     */
    public final static Integer MENOR_ENTRE_CONSUMO_MEDIO_HIDROMETRO_CONSUMO_MEDIDO = new Integer(
            6);
    
    public final static Integer FIXO = new Integer(7);
    
    public final static Integer NAO_MEDIDO = new Integer(8);

    /**
     * full constructor
     * 
     * @param descricaoConsumoACobrar
     *            Descri��o do par�metro
     * @param indicadorSemLeitura
     *            Descri��o do par�metro
     * @param indicadorComLeitura
     *            Descri��o do par�metro
     * @param indicadorUso
     *            Descri��o do par�metro
     * @param ultimaAlteracao
     *            Descri��o do par�metro
     */
    public LeituraAnormalidadeConsumo(String descricaoConsumoACobrar,
            Short indicadorSemLeitura, Short indicadorComLeitura,
            Short indicadorUso, Date ultimaAlteracao) {
        this.descricaoConsumoACobrar = descricaoConsumoACobrar;
        this.indicadorSemLeitura = indicadorSemLeitura;
        this.indicadorComLeitura = indicadorComLeitura;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /**
     * default constructor
     */
    public LeituraAnormalidadeConsumo() {
    }

    /**
     * Retorna o valor de id
     * 
     * @return O valor de id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Seta o valor de id
     * 
     * @param id
     *            O novo valor de id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retorna o valor de descricaoConsumoACobrar
     * 
     * @return O valor de descricaoConsumoACobrar
     */
    public String getDescricaoConsumoACobrar() {
        return this.descricaoConsumoACobrar;
    }

    /**
     * Seta o valor de descricaoConsumoACobrar
     * 
     * @param descricaoConsumoACobrar
     *            O novo valor de descricaoConsumoACobrar
     */
    public void setDescricaoConsumoACobrar(String descricaoConsumoACobrar) {
        this.descricaoConsumoACobrar = descricaoConsumoACobrar;
    }

    /**
     * Retorna o valor de indicadorSemLeitura
     * 
     * @return O valor de indicadorSemLeitura
     */
    public Short getIndicadorSemLeitura() {
        return this.indicadorSemLeitura;
    }

    /**
     * Seta o valor de indicadorSemLeitura
     * 
     * @param indicadorSemLeitura
     *            O novo valor de indicadorSemLeitura
     */
    public void setIndicadorSemLeitura(Short indicadorSemLeitura) {
        this.indicadorSemLeitura = indicadorSemLeitura;
    }

    /**
     * Retorna o valor de indicadorComLeitura
     * 
     * @return O valor de indicadorComLeitura
     */
    public Short getIndicadorComLeitura() {
        return this.indicadorComLeitura;
    }

    /**
     * Seta o valor de indicadorComLeitura
     * 
     * @param indicadorComLeitura
     *            O novo valor de indicadorComLeitura
     */
    public void setIndicadorComLeitura(Short indicadorComLeitura) {
        this.indicadorComLeitura = indicadorComLeitura;
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
     * < <Descri��o do m�todo>>
     * 
     * @return Descri��o do retorno
     */
    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

}
