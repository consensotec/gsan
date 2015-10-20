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
package gcom.gui.relatorio.micromedicao;

import org.apache.struts.action.ActionForm;

/** 
 * [UC1682] - Relat�rio Sint�tico de Hidr�metros por Faixa de Idad 
 * @author Cesar Medeiros
 * @date 04/06/2015
 */
public class GerarRelatorioHidrometroPorIdadeActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;	

	private String opcaoTotalizacao;
	private String gerenciaRegionalId;
	private String unidadeNegocioId;
	private String codigoLocalidade;
	private String descricaoLocalidade;
	private String idSetorComercial;
	private String descricaoSetorComercial;
	private String idRota;
	private String idQuadra;
	private String opcaoRelatorio;
	private String[] imovelPerfil;
	private String[] categoria;
	private String[] subCategoria;
	private String quantidadeEconomiaInicial;
	private String quantidadeEconomiaFinal;
	private String[] capacidade;
	private String[] marca;
	private String[] anormalidade;
	private String leituraMinima;
	private String faixaConsumoMesInical;
	private String faixaConsumoMesFinal;
	private String faixaConsumoMedioInicial;
	private String faixaConsumoMedioFinal;
	
	public String getOpcaoTotalizacao() {
		return opcaoTotalizacao;
	}
	public void setOpcaoTotalizacao(String opcaoTotalizacao) {
		this.opcaoTotalizacao = opcaoTotalizacao;
	}
	public String getGerenciaRegionalId() {
		return gerenciaRegionalId;
	}
	public void setGerenciaRegionalId(String gerenciaRegionalId) {
		this.gerenciaRegionalId = gerenciaRegionalId;
	}
	public String getUnidadeNegocioId() {
		return unidadeNegocioId;
	}
	public void setUnidadeNegocioId(String unidadeNegocioId) {
		this.unidadeNegocioId = unidadeNegocioId;
	}
	public String getCodigoLocalidade() {
		return codigoLocalidade;
	}
	public void setCodigoLocalidade(String codigoLocalidade) {
		this.codigoLocalidade = codigoLocalidade;
	}
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}	
	public String getIdSetorComercial() {
		return idSetorComercial;
	}
	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}
	public String getDescricaoSetorComercial() {
		return descricaoSetorComercial;
	}
	public void setDescricaoSetorComercial(String descricaoSetorComercial) {
		this.descricaoSetorComercial = descricaoSetorComercial;
	}
	public String getIdRota() {
		return idRota;
	}
	public void setIdRota(String idRota) {
		this.idRota = idRota;
	}
	public String getIdQuadra() {
		return idQuadra;
	}
	public void setIdQuadra(String idQuadra) {
		this.idQuadra = idQuadra;
	}
	public String getOpcaoRelatorio() {
		return opcaoRelatorio;
	}
	public void setOpcaoRelatorio(String opcaoRelatorio) {
		this.opcaoRelatorio = opcaoRelatorio;
	}
	public String[] getImovelPerfil() {
		return imovelPerfil;
	}
	public void setImovelPerfil(String[] imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}
	public String[] getCategoria() {
		return categoria;
	}
	public void setCategoria(String[] categoria) {
		this.categoria = categoria;
	}
	public String[] getSubCategoria() {
		return subCategoria;
	}
	public void setSubCategoria(String[] subCategoria) {
		this.subCategoria = subCategoria;
	}
	public String getQuantidadeEconomiaInicial() {
		return quantidadeEconomiaInicial;
	}
	public void setQuantidadeEconomiaInicial(String quantidadeEconomiaInicial) {
		this.quantidadeEconomiaInicial = quantidadeEconomiaInicial;
	}
	public String getQuantidadeEconomiaFinal() {
		return quantidadeEconomiaFinal;
	}
	public void setQuantidadeEconomiaFinal(String quantidadeEconomiaFinal) {
		this.quantidadeEconomiaFinal = quantidadeEconomiaFinal;
	}
	public String[] getCapacidade() {
		return capacidade;
	}
	public void setCapacidade(String[] capacidade) {
		this.capacidade = capacidade;
	}
	public String[] getMarca() {
		return marca;
	}
	public void setMarca(String[] marca) {
		this.marca = marca;
	}
	public String[] getAnormalidade() {
		return anormalidade;
	}
	public void setAnormalidade(String[] anormalidade) {
		this.anormalidade = anormalidade;
	}
	public String getLeituraMinima() {
		return leituraMinima;
	}
	public void setLeituraMinima(String leituraMinima) {
		this.leituraMinima = leituraMinima;
	}
	public String getFaixaConsumoMesInical() {
		return faixaConsumoMesInical;
	}
	public void setFaixaConsumoMesInical(String faixaConsumoMesInical) {
		this.faixaConsumoMesInical = faixaConsumoMesInical;
	}
	public String getFaixaConsumoMesFinal() {
		return faixaConsumoMesFinal;
	}
	public void setFaixaConsumoMesFinal(String faixaConsumoMesFinal) {
		this.faixaConsumoMesFinal = faixaConsumoMesFinal;
	}
	public String getFaixaConsumoMedioInicial() {
		return faixaConsumoMedioInicial;
	}
	public void setFaixaConsumoMedioInicial(String faixaConsumoMedioInicial) {
		this.faixaConsumoMedioInicial = faixaConsumoMedioInicial;
	}
	public String getFaixaConsumoMedioFinal() {
		return faixaConsumoMedioFinal;
	}
	public void setFaixaConsumoMedioFinal(String faixaConsumoMedioFinal) {
		this.faixaConsumoMedioFinal = faixaConsumoMedioFinal;
	}
	
}
