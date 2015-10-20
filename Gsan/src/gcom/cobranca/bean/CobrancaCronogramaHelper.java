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
package gcom.cobranca.bean;

import gcom.cobranca.CobrancaAcaoCronograma;
import gcom.cobranca.CobrancaGrupoCronogramaMes;

import java.io.Serializable;
import java.util.Collection;

/**
 * [UC0312] Inserir Cromograma de Cobran�a  
 *
 * @author Fl�vio Cordeiro
 * @date 25/04/2006
 */
public class CobrancaCronogramaHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private CobrancaGrupoCronogramaMes cobrancaGrupoCronogramaMes;
	
	private CobrancaAcaoCronograma cobrancaAcaoCronograma;
	
	private Collection cobrancasAtividadesParaInsercao;
	
	private String comandar;
	
	private boolean Inserir;
	
	private boolean critica1;
	
	private boolean critica2;
	
	private boolean critica3;

	public boolean getCritica1() {
		return critica1;
	}

	public void setCritica1(boolean critica1) {
		this.critica1 = critica1;
	}

	public boolean getCritica2() {
		return critica2;
	}

	public void setCritica2(boolean critica2) {
		this.critica2 = critica2;
	}

	public String getComandar() {
		return comandar;
	}

	public void setComandar(String comandar) {
		this.comandar = comandar;
	}

	/**
	 * Construtor de OpcoesParcelamentoHelper 
	 */
	public CobrancaCronogramaHelper() {
	}

	public CobrancaAcaoCronograma getCobrancaAcaoCronograma() {
		return cobrancaAcaoCronograma;
	}

	public void setCobrancaAcaoCronograma(
			CobrancaAcaoCronograma cobrancaAcaoCronograma) {
		this.cobrancaAcaoCronograma = cobrancaAcaoCronograma;
	}

	public CobrancaGrupoCronogramaMes getCobrancaGrupoCronogramaMes() {
		return cobrancaGrupoCronogramaMes;
	}

	public void setCobrancaGrupoCronogramaMes(
			CobrancaGrupoCronogramaMes cobrancaGrupoCronogramaMes) {
		this.cobrancaGrupoCronogramaMes = cobrancaGrupoCronogramaMes;
	}

	public Collection getCobrancasAtividadesParaInsercao() {
		return cobrancasAtividadesParaInsercao;
	}

	public void setCobrancasAtividadesParaInsercao(
			Collection cobrancasAtividadesParaInsercao) {
		this.cobrancasAtividadesParaInsercao = cobrancasAtividadesParaInsercao;
	}

	public boolean isInserir() {
		return Inserir;
	}

	public void setInserir(boolean inserir) {
		Inserir = inserir;
	}

	public boolean getCritica3() {
		return critica3;
	}

	public void setCritica3(boolean critica3) {
		this.critica3 = critica3;
	}
	
}