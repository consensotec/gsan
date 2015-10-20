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
* R�mulo Aur�lio de Melo Souza Filho
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
package gcom.faturamento;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class QualidadeAguaPadrao implements Serializable {	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricaoPadraoTurbidez;

    /** nullable persistent field */
    private String descricaoPadraoPh;

    /** nullable persistent field */
    private String descricaoPadraoCor;

    /** nullable persistent field */
    private String descricaoPadraoCloro;

    /** nullable persistent field */
    private String descricaoPadraoFluor;

    /** nullable persistent field */
    private String descricaoPadraoFerro;

    /** nullable persistent field */
    private String descricaoPadraoColiformesTotais;

    /** nullable persistent field */
    private String descricaoPadraoColiformesFecais;

    /** persistent field */
    private Date ultimaAlteracao;
    
    /** persistent field */
    private String descricaoNitrato;
   
    /** persistent field */
    private String descricaoPadraoColiformesTermotolerantes;
    
    /** persistent field */
    private String descricaoPadraoAlcalinidade;
    
    private String descricaoDurezaTotal;

	/** full constructor */
    public QualidadeAguaPadrao(Integer id, String descricaoPadraoTurbidez, String descricaoPadraoPh, String descricaoPadraoCor, String descricaoPadraoCloro, String descricaoPadraoFluor, String descricaoPadraoFerro, String descricaoPadraoColiformesTotais, String descricaoPadraoColiformesFecais, Date ultimaAlteracao) {
        this.id = id;
        this.descricaoPadraoTurbidez = descricaoPadraoTurbidez;
        this.descricaoPadraoPh = descricaoPadraoPh;
        this.descricaoPadraoCor = descricaoPadraoCor;
        this.descricaoPadraoCloro = descricaoPadraoCloro;
        this.descricaoPadraoFluor = descricaoPadraoFluor;
        this.descricaoPadraoFerro = descricaoPadraoFerro;
        this.descricaoPadraoColiformesTotais = descricaoPadraoColiformesTotais;
        this.descricaoPadraoColiformesFecais = descricaoPadraoColiformesFecais;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public QualidadeAguaPadrao() {
    }

    /** minimal constructor */
    public QualidadeAguaPadrao(Integer id, Date ultimaAlteracao) {
        this.id = id;
        this.ultimaAlteracao = ultimaAlteracao;
    }

 

    public String getDescricaoPadraoCloro() {
		return descricaoPadraoCloro;
	}

	public void setDescricaoPadraoCloro(String descricaoPadraoCloro) {
		this.descricaoPadraoCloro = descricaoPadraoCloro;
	}

	public String getDescricaoPadraoColiformesFecais() {
		return descricaoPadraoColiformesFecais;
	}

	public void setDescricaoPadraoColiformesFecais(
			String descricaoPadraoColiformesFecais) {
		this.descricaoPadraoColiformesFecais = descricaoPadraoColiformesFecais;
	}

	public String getDescricaoPadraoColiformesTotais() {
		return descricaoPadraoColiformesTotais;
	}

	public void setDescricaoPadraoColiformesTotais(
			String descricaoPadraoColiformesTotais) {
		this.descricaoPadraoColiformesTotais = descricaoPadraoColiformesTotais;
	}

	public String getDescricaoPadraoCor() {
		return descricaoPadraoCor;
	}

	public void setDescricaoPadraoCor(String descricaoPadraoCor) {
		this.descricaoPadraoCor = descricaoPadraoCor;
	}

	public String getDescricaoPadraoFerro() {
		return descricaoPadraoFerro;
	}

	public void setDescricaoPadraoFerro(String descricaoPadraoFerro) {
		this.descricaoPadraoFerro = descricaoPadraoFerro;
	}

	public String getDescricaoPadraoFluor() {
		return descricaoPadraoFluor;
	}

	public void setDescricaoPadraoFluor(String descricaoPadraoFluor) {
		this.descricaoPadraoFluor = descricaoPadraoFluor;
	}

	public String getDescricaoPadraoPh() {
		return descricaoPadraoPh;
	}

	public void setDescricaoPadraoPh(String descricaoPadraoPh) {
		this.descricaoPadraoPh = descricaoPadraoPh;
	}

	public String getDescricaoPadraoTurbidez() {
		return descricaoPadraoTurbidez;
	}

	public void setDescricaoPadraoTurbidez(String descricaoPadraoTurbidez) {
		this.descricaoPadraoTurbidez = descricaoPadraoTurbidez;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public String getDescricaoNitrato() {
		return descricaoNitrato;
	}

	public void setDescricaoNitrato(String descricaoNitrato) {
		this.descricaoNitrato = descricaoNitrato;
	}

	public String getDescricaoPadraoColiformesTermotolerantes() {
		return descricaoPadraoColiformesTermotolerantes;
	}

	public void setDescricaoPadraoColiformesTermotolerantes(
			String descricaoPadraoColiformesTermotolerantes) {
		this.descricaoPadraoColiformesTermotolerantes = descricaoPadraoColiformesTermotolerantes;
	}
	public String getDescricaoPadraoAlcalinidade() {
		return descricaoPadraoAlcalinidade;
	}



	public void setDescricaoPadraoAlcalinidade(String descricaoPadraoAlcalinidade) {
		this.descricaoPadraoAlcalinidade = descricaoPadraoAlcalinidade;
	}



	public String getDescricaoDurezaTotal() {
		return descricaoDurezaTotal;
	}



	public void setDescricaoDurezaTotal(String descricaoDurezaTotal) {
		this.descricaoDurezaTotal = descricaoDurezaTotal;
	}
}
