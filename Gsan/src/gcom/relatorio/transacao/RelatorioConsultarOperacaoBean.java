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
package gcom.relatorio.transacao;

import java.util.Date;

import gcom.relatorio.RelatorioBean;

/**
 * Bean respons�vel de pegar os parametros que ser�o exibidos na parte de detail
 * do relat�rio.
 * 
 * @author Rafael Corr�a
 * @created 06/04/2006
 */
public class RelatorioConsultarOperacaoBean implements RelatorioBean {

	private Date dataRealizacao;

	private String  nome;
	
	private String  dados;
	
	private String  outrosDados;

	private String  usuario;
	
	private Integer idOperacaoEfetuada;
	
	private String  campo;
	
	private String  valorAnterior;
	
	private String  valorAtual;
	
	private Date    dataAtualizacao;

	private String  ipUsuario;

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
	public RelatorioConsultarOperacaoBean(Date dataRealizacao, String nome,
			String dados, String outrosDados, String usuario, Integer idOperacaoEfetuada,
			String campo, String valorAnterior, String valorAtual, Date dataAtualizacao,String ipUsuario) {
		
		this.dataRealizacao     = dataRealizacao;
		this.nome               = nome;
		this.dados              = dados;
		this.outrosDados        = outrosDados;
		this.usuario            = usuario;
		this.idOperacaoEfetuada = idOperacaoEfetuada;
		this.campo              = campo;
		this.valorAnterior      = valorAnterior;
		this.valorAtual         = valorAtual;
		this.dataAtualizacao    = dataAtualizacao;
		this.ipUsuario          = ipUsuario;
	}

	public Date getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(Date dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getDados() {
		return dados;
	}

	public void setDados(String dados) {
		this.dados = dados;
	}

	public String getOutrosDados() {
		return outrosDados;
	}

	public void setOutrosDados(String outrosDados) {
		this.outrosDados = outrosDados;
	}

	/**
	 * @return Retorna o campo campo.
	 */
	public String getCampo() {
		return campo;
	}

	/**
	 * @param campo O campo a ser setado.
	 */
	public void setCampo(String campo) {
		this.campo = campo;
	}

	/**
	 * @return Retorna o campo dataAtualizacao.
	 */
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	/**
	 * @param dataAtualizacao O dataAtualizacao a ser setado.
	 */
	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	/**
	 * @return Retorna o campo idOperacaoEfetuada.
	 */
	public Integer getIdOperacaoEfetuada() {
		return idOperacaoEfetuada;
	}

	/**
	 * @param idOperacaoEfetuada O idOperacaoEfetuada a ser setado.
	 */
	public void setIdOperacaoEfetuada(Integer idOperacaoEfetuada) {
		this.idOperacaoEfetuada = idOperacaoEfetuada;
	}

	/**
	 * @return Retorna o campo valorAnterior.
	 */
	public String getValorAnterior() {
		return valorAnterior;
	}

	/**
	 * @param valorAnterior O valorAnterior a ser setado.
	 */
	public void setValorAnterior(String valorAnterior) {
		this.valorAnterior = valorAnterior;
	}

	/**
	 * @return Retorna o campo valorAtual.
	 */
	public String getValorAtual() {
		return valorAtual;
	}

	/**
	 * @param valorAtual O valorAtual a ser setado.
	 */
	public void setValorAtual(String valorAtual) {
		this.valorAtual = valorAtual;
	}

	public String getIpUsuario() {
		return ipUsuario;
	}

	public void setIpUsuario(String ipUsuario) {
		this.ipUsuario = ipUsuario;
	}
}

