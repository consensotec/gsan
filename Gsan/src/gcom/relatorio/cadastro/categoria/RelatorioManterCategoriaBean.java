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
package gcom.relatorio.cadastro.categoria;

import gcom.relatorio.RelatorioBean;

public class RelatorioManterCategoriaBean implements RelatorioBean {

	private String codigo;

	private String descricao;

	private String descricaoAbreviada;

	private String consumoMinimo;

	private String consumoReferenciaAlto;

	private String consumoReferenciaBaixo;

	private String consumoReferenciaEstouro;

	private String fatorMultiplicacaoAlto;

	private String fatorMultiplicacaoEstouro;

	private String percentualBaixoConsumo;

	private String indicadorUso;

	/**
	 * Construtor da classe RelatorioAnaliseFisicoQuimicaAguaBean
	 * 
	 * @param codigo
	 *            Description of the Parameter
	 * @param nome
	 *            Description of the Parameter
	 * @param municipio
	 *            Description of the Parameter
	 * @param codPref
	 *            Description of the Parameter
	 * @param indicadorUso
	 *            Description of the Parameter
	 */
	public RelatorioManterCategoriaBean(String codigo, String descricao,
			String descricaoAbreviada, String consumoMinimo,
			String consumoReferenciaAlto, String consumoReferenciaBaixo,
			String consumoReferenciaEstouro, String fatorMultiplicacaoAlto,
			String fatorMultiplicacaoEstouro, String percentualBaixoConsumo,
			String indicadorUso) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.consumoMinimo = consumoMinimo;
		this.consumoReferenciaAlto = consumoReferenciaAlto;
		this.consumoReferenciaBaixo = consumoReferenciaBaixo;
		this.consumoReferenciaEstouro = consumoReferenciaEstouro;
		this.fatorMultiplicacaoEstouro = fatorMultiplicacaoEstouro;
		this.fatorMultiplicacaoAlto = fatorMultiplicacaoAlto;
		this.percentualBaixoConsumo = percentualBaixoConsumo;
		this.indicadorUso = indicadorUso;

	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getConsumoMinimo() {
		return consumoMinimo;
	}

	public void setConsumoMinimo(String consumoMinimo) {
		this.consumoMinimo = consumoMinimo;
	}

	public String getConsumoReferenciaAlto() {
		return consumoReferenciaAlto;
	}

	public void setConsumoReferenciaAlto(String consumoReferenciaAlto) {
		this.consumoReferenciaAlto = consumoReferenciaAlto;
	}

	public String getConsumoReferenciaBaixo() {
		return consumoReferenciaBaixo;
	}

	public void setConsumoReferenciaBaixo(String consumoReferenciaBaixo) {
		this.consumoReferenciaBaixo = consumoReferenciaBaixo;
	}

	public String getConsumoReferenciaEstouro() {
		return consumoReferenciaEstouro;
	}

	public void setConsumoReferenciaEstouro(String consumoReferenciaEstouro) {
		this.consumoReferenciaEstouro = consumoReferenciaEstouro;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public String getFatorMultiplicacaoAlto() {
		return fatorMultiplicacaoAlto;
	}

	public void setFatorMultiplicacaoAlto(String fatorMultiplicacaoAlto) {
		this.fatorMultiplicacaoAlto = fatorMultiplicacaoAlto;
	}

	public String getFatorMultiplicacaoEstouro() {
		return fatorMultiplicacaoEstouro;
	}

	public void setFatorMultiplicacaoEstouro(String fatorMultiplicacaoEstouro) {
		this.fatorMultiplicacaoEstouro = fatorMultiplicacaoEstouro;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getPercentualBaixoConsumo() {
		return percentualBaixoConsumo;
	}

	public void setPercentualBaixoConsumo(String percentualBaixoConsumo) {
		this.percentualBaixoConsumo = percentualBaixoConsumo;
	}

}
