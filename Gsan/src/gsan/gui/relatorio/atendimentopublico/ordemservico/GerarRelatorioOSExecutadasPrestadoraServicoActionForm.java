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
package gsan.gui.relatorio.atendimentopublico.ordemservico;


import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC1163]  Gerar  Relat�rio de OS executadas por Prestadora de Servi�o
 * 
 * @author Vivianne Sousa
 *
 * @date 12/04/2011
 */
public class GerarRelatorioOSExecutadasPrestadoraServicoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	private String periodoEncerramentoInicial;
	private String periodoEncerramentoFinal;
	private String empresa;
	private String idGerencia;
	private String idUnidadeNegocio;
	private String codigoLocalidade;
	private String descricaoLocalidade;
	private String[] colecaoRegiao;
	private String[] colecaoMicrorregiao;
	private String[] colecaoMunicipio;
	private String opcaoRelatorio;
	
	public GerarRelatorioOSExecutadasPrestadoraServicoActionForm(String periodoEncerramentoInicial, String periodoEncerramentoFinal, String empresa, String idGerencia, String idUnidadeNegocio, String codigoLocalidade, String descricaoLocalidade, String[] colecaoRegiao, String[] colecaoMicrorregiao, String[] colecaoMunicipio, String opcaoRelatorio) {
		super();
		// TODO Auto-generated constructor stub
		this.periodoEncerramentoInicial = periodoEncerramentoInicial;
		this.periodoEncerramentoFinal = periodoEncerramentoFinal;
		this.empresa = empresa;
		this.idGerencia = idGerencia;
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.codigoLocalidade = codigoLocalidade;
		this.descricaoLocalidade = descricaoLocalidade;
		this.colecaoRegiao = colecaoRegiao;
		this.colecaoMicrorregiao = colecaoMicrorregiao;
		this.colecaoMunicipio = colecaoMunicipio;
		this.opcaoRelatorio = opcaoRelatorio;
	}
	public GerarRelatorioOSExecutadasPrestadoraServicoActionForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCodigoLocalidade() {
		return codigoLocalidade;
	}
	public void setCodigoLocalidade(String codigoLocalidade) {
		this.codigoLocalidade = codigoLocalidade;
	}
	public String[] getColecaoMicrorregiao() {
		return colecaoMicrorregiao;
	}
	public void setColecaoMicrorregiao(String[] colecaoMicrorregiao) {
		this.colecaoMicrorregiao = colecaoMicrorregiao;
	}
	public String[] getColecaoMunicipio() {
		return colecaoMunicipio;
	}
	public void setColecaoMunicipio(String[] colecaoMunicipio) {
		this.colecaoMunicipio = colecaoMunicipio;
	}
	public String[] getColecaoRegiao() {
		return colecaoRegiao;
	}
	public void setColecaoRegiao(String[] colecaoRegiao) {
		this.colecaoRegiao = colecaoRegiao;
	}
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getIdGerencia() {
		return idGerencia;
	}
	public void setIdGerencia(String idGerencia) {
		this.idGerencia = idGerencia;
	}
	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}
	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}
	public String getOpcaoRelatorio() {
		return opcaoRelatorio;
	}
	public void setOpcaoRelatorio(String opcaoRelatorio) {
		this.opcaoRelatorio = opcaoRelatorio;
	}
	public String getPeriodoEncerramentoFinal() {
		return periodoEncerramentoFinal;
	}
	public void setPeriodoEncerramentoFinal(String periodoEncerramentoFinal) {
		this.periodoEncerramentoFinal = periodoEncerramentoFinal;
	}
	public String getPeriodoEncerramentoInicial() {
		return periodoEncerramentoInicial;
	}
	public void setPeriodoEncerramentoInicial(String periodoEncerramentoInicial) {
		this.periodoEncerramentoInicial = periodoEncerramentoInicial;
	}
	
}