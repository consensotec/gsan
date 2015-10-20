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
package gcom.relatorio.micromedicao;

import gcom.relatorio.RelatorioBean;

/**
 * Bean respons�vel de pegar os parametros que ser�o exibidos na parte de detail
 * do relat�rio.
 * 
 * @author S�vio Luiz
 * @created 8 de Julho de 2005
 */
public class RelatorioFaixasFalsasLeituraBean implements RelatorioBean {

	private String grupo;

	private String empresa;

	private String localidade;
	
	private String idSetorComercial;

	private String setorComercial;
	
	private String indicador;

	private String inscricao;

	private String matricula;

	private String leiturista;
	
	private String faixaCorreta;

	private String faixaFalsa;

	private String leituraInformada;
	
	private String dataLeitura;
	
	private String anormalidadeLeitura;
	
	private String situacao;
	
	private String totalFaixas;

	public RelatorioFaixasFalsasLeituraBean(String grupo, String empresa,
			String localidade, String idSetorComercial, String setorComercial,
			String indicador, String inscricao, String matricula,
			String leiturista, String faixaCorreta, String faixaFalsa,
			String leituraInformada, String dataLeitura,
			String anormalidadeLeitura, String situacao, String totalFaixas) {
		
		this.grupo = grupo;
		this.empresa = empresa;
		this.localidade = localidade;
		this.idSetorComercial = idSetorComercial;
		this.setorComercial = setorComercial;
		this.indicador = indicador;
		this.inscricao = inscricao;
		this.matricula = matricula;
		this.leiturista = leiturista;
		this.faixaCorreta = faixaCorreta;
		this.faixaFalsa = faixaFalsa;
		this.leituraInformada = leituraInformada;
		this.dataLeitura = dataLeitura;
		this.anormalidadeLeitura = anormalidadeLeitura;
		this.situacao = situacao;
		this.totalFaixas = totalFaixas;
		
	}

	/**
	 * @return Retorna o campo anormalidadeLeitura.
	 */
	public String getAnormalidadeLeitura() {
		return anormalidadeLeitura;
	}

	/**
	 * @param anormalidadeLeitura O anormalidadeLeitura a ser setado.
	 */
	public void setAnormalidadeLeitura(String anormalidadeLeitura) {
		this.anormalidadeLeitura = anormalidadeLeitura;
	}

	/**
	 * @return Retorna o campo dataLeitura.
	 */
	public String getDataLeitura() {
		return dataLeitura;
	}

	/**
	 * @param dataLeitura O dataLeitura a ser setado.
	 */
	public void setDataLeitura(String dataLeitura) {
		this.dataLeitura = dataLeitura;
	}

	/**
	 * @return Retorna o campo empresa.
	 */
	public String getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa O empresa a ser setado.
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/**
	 * @return Retorna o campo faixaCorreta.
	 */
	public String getFaixaCorreta() {
		return faixaCorreta;
	}

	/**
	 * @param faixaCorreta O faixaCorreta a ser setado.
	 */
	public void setFaixaCorreta(String faixaCorreta) {
		this.faixaCorreta = faixaCorreta;
	}

	/**
	 * @return Retorna o campo faixaFalsa.
	 */
	public String getFaixaFalsa() {
		return faixaFalsa;
	}

	/**
	 * @param faixaFalsa O faixaFalsa a ser setado.
	 */
	public void setFaixaFalsa(String faixaFalsa) {
		this.faixaFalsa = faixaFalsa;
	}

	/**
	 * @return Retorna o campo grupo.
	 */
	public String getGrupo() {
		return grupo;
	}

	/**
	 * @param grupo O grupo a ser setado.
	 */
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	/**
	 * @return Retorna o campo idSetorComercial.
	 */
	public String getIdSetorComercial() {
		return idSetorComercial;
	}

	/**
	 * @param idSetorComercial O idSetorComercial a ser setado.
	 */
	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	/**
	 * @return Retorna o campo indicador.
	 */
	public String getIndicador() {
		return indicador;
	}

	/**
	 * @param indicador O indicador a ser setado.
	 */
	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}

	/**
	 * @return Retorna o campo inscricao.
	 */
	public String getInscricao() {
		return inscricao;
	}

	/**
	 * @param inscricao O inscricao a ser setado.
	 */
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	/**
	 * @return Retorna o campo leituraInformada.
	 */
	public String getLeituraInformada() {
		return leituraInformada;
	}

	/**
	 * @param leituraInformada O leituraInformada a ser setado.
	 */
	public void setLeituraInformada(String leituraInformada) {
		this.leituraInformada = leituraInformada;
	}

	/**
	 * @return Retorna o campo leiturista.
	 */
	public String getLeiturista() {
		return leiturista;
	}

	/**
	 * @param leiturista O leiturista a ser setado.
	 */
	public void setLeiturista(String leiturista) {
		this.leiturista = leiturista;
	}

	/**
	 * @return Retorna o campo localidade.
	 */
	public String getLocalidade() {
		return localidade;
	}

	/**
	 * @param localidade O localidade a ser setado.
	 */
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	/**
	 * @return Retorna o campo matricula.
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * @param matricula O matricula a ser setado.
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	/**
	 * @return Retorna o campo setorComercial.
	 */
	public String getSetorComercial() {
		return setorComercial;
	}

	/**
	 * @param setorComercial O setorComercial a ser setado.
	 */
	public void setSetorComercial(String setorComercial) {
		this.setorComercial = setorComercial;
	}

	/**
	 * @return Retorna o campo situacao.
	 */
	public String getSituacao() {
		return situacao;
	}

	/**
	 * @param situacao O situacao a ser setado.
	 */
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	/**
	 * @return Retorna o campo totalFaixas.
	 */
	public String getTotalFaixas() {
		return totalFaixas;
	}

	/**
	 * @param totalFaixas O totalFaixas a ser setado.
	 */
	public void setTotalFaixas(String totalFaixas) {
		this.totalFaixas = totalFaixas;
	}
	
	


	
	
}
