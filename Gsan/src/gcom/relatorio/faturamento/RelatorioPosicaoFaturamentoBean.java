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
package gcom.relatorio.faturamento;

import gcom.relatorio.RelatorioBean;

/**
 * Bean respons�vel de pegar os parametros que ser�o exibidos na parte de detail
 * do relat�rio.
 * 
 * @author Rafael Corr�a
 * @created 01/09/2008
 */
/**
 * @author Administrador
 *
 */
public class RelatorioPosicaoFaturamentoBean implements RelatorioBean {

	private String grupoFaturamento;

	private String mesAno;

	private String atividade;

	private String predecessora;
	
	private String obrigatoria;

	private String dataPrevisao;

	private String usuarioPrevisao;
	
	private String dataComando;

	private String usuarioComando;

	private String dataRealizacao;

	/**
	 * Construtor da classe RelatorioPosicaoFaturamentoBean
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
	public RelatorioPosicaoFaturamentoBean(String grupoFaturamento,
			String mesAno, String atividade, String predecessora,
			String obrigatoria, String dataPrevisao, String usuarioPrevisao,
			String dataComando, String usuarioComando, String dataRealizacao) {
		this.grupoFaturamento = grupoFaturamento;
		this.mesAno = mesAno;
		this.atividade = atividade;
		this.predecessora = predecessora;
		this.obrigatoria = obrigatoria;
		this.dataPrevisao = dataPrevisao;
		this.usuarioPrevisao = usuarioPrevisao;
		this.dataComando = dataComando;
		this.usuarioComando = usuarioComando;
		this.dataRealizacao = dataRealizacao;

	}

	/**
	 * @return Retorna o campo atividade.
	 */
	public String getAtividade() {
		return atividade;
	}

	/**
	 * @param atividade O atividade a ser setado.
	 */
	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}

	/**
	 * @return Retorna o campo dataComando.
	 */
	public String getDataComando() {
		return dataComando;
	}

	/**
	 * @param dataComando O dataComando a ser setado.
	 */
	public void setDataComando(String dataComando) {
		this.dataComando = dataComando;
	}

	/**
	 * @return Retorna o campo dataPrevisao.
	 */
	public String getDataPrevisao() {
		return dataPrevisao;
	}

	/**
	 * @param dataPrevisao O dataPrevisao a ser setado.
	 */
	public void setDataPrevisao(String dataPrevisao) {
		this.dataPrevisao = dataPrevisao;
	}

	/**
	 * @return Retorna o campo dataRealizacao.
	 */
	public String getDataRealizacao() {
		return dataRealizacao;
	}

	/**
	 * @param dataRealizacao O dataRealizacao a ser setado.
	 */
	public void setDataRealizacao(String dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	/**
	 * @return Retorna o campo grupoFaturamento.
	 */
	public String getGrupoFaturamento() {
		return grupoFaturamento;
	}

	/**
	 * @param grupoFaturamento O grupoFaturamento a ser setado.
	 */
	public void setGrupoFaturamento(String grupoFaturamento) {
		this.grupoFaturamento = grupoFaturamento;
	}

	/**
	 * @return Retorna o campo mesAno.
	 */
	public String getMesAno() {
		return mesAno;
	}

	/**
	 * @param mesAno O mesAno a ser setado.
	 */
	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	/**
	 * @return Retorna o campo obrigatoria.
	 */
	public String getObrigatoria() {
		return obrigatoria;
	}

	/**
	 * @param obrigatoria O obrigatoria a ser setado.
	 */
	public void setObrigatoria(String obrigatoria) {
		this.obrigatoria = obrigatoria;
	}

	/**
	 * @return Retorna o campo predecessora.
	 */
	public String getPredecessora() {
		return predecessora;
	}

	/**
	 * @param predecessora O predecessora a ser setado.
	 */
	public void setPredecessora(String predecessora) {
		this.predecessora = predecessora;
	}

	/**
	 * @return Retorna o campo usuarioComando.
	 */
	public String getUsuarioComando() {
		return usuarioComando;
	}

	/**
	 * @param usuarioComando O usuarioComando a ser setado.
	 */
	public void setUsuarioComando(String usuarioComando) {
		this.usuarioComando = usuarioComando;
	}

	/**
	 * @return Retorna o campo usuarioPrevisao.
	 */
	public String getUsuarioPrevisao() {
		return usuarioPrevisao;
	}

	/**
	 * @param usuarioPrevisao O usuarioPrevisao a ser setado.
	 */
	public void setUsuarioPrevisao(String usuarioPrevisao) {
		this.usuarioPrevisao = usuarioPrevisao;
	}

	
}
