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
package gsan.cadastro.cliente;

import gsan.interceptor.ControleAlteracao;
import gsan.interceptor.ObjetoTransacao;
import gsan.util.filtro.Filtro;
import gsan.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
@ControleAlteracao
public class ClienteFone extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
//	@ControleAlteracao(funcionalidade={Cliente.ATRIBUTOS_CLIENTE_INSERIR, 
//			Cliente.ATRIBUTOS_CLIENTE_ATUALIZAR})
	private String ddd;

	/** nullable persistent field */
//	@ControleAlteracao(funcionalidade={Cliente.ATRIBUTOS_CLIENTE_INSERIR, 
//			Cliente.ATRIBUTOS_CLIENTE_ATUALIZAR})
	private String telefone;

	/** nullable persistent field */
//	@ControleAlteracao(funcionalidade={Cliente.ATRIBUTOS_CLIENTE_INSERIR, 
//			Cliente.ATRIBUTOS_CLIENTE_ATUALIZAR})
	private String ramal;
	
	/** nullable persistent field */
//	@ControleAlteracao(funcionalidade={Cliente.ATRIBUTOS_CLIENTE_INSERIR, 
//			Cliente.ATRIBUTOS_CLIENTE_ATUALIZAR})
	private String contato;

	/** nullable persistent field */
//	@ControleAlteracao(funcionalidade={Cliente.ATRIBUTOS_CLIENTE_INSERIR, 
//			Cliente.ATRIBUTOS_CLIENTE_ATUALIZAR})
	private Short indicadorTelefonePadrao;
	
	private Short indicadorTelefoneSMS;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
//	@ControleAlteracao(value=FiltroClienteFone.FONE_TIPO, 
//    		funcionalidade={Cliente.ATRIBUTOS_CLIENTE_INSERIR, 
//			Cliente.ATRIBUTOS_CLIENTE_ATUALIZAR})
	private gsan.cadastro.cliente.FoneTipo foneTipo;

	/** persistent field */
	private gsan.cadastro.cliente.Cliente cliente;
	
	public static final Short INDICADOR_FONE_PADRAO = new Short("1");
	
	// Criado um par�metro auxiliar para identificar se o tipo do cliente
	// � do mesmo cliente em atualiza��o cadastral
	// Autor: S�vio Luiz
	// Data: 17/05/2011
	private Integer idTabelaAtualizacaoCadastralAux;

	/** full constructor */
	public ClienteFone(String ddd, String telefone, String ramal, String contato,
			Short indicadorTelefonePadrao, Date ultimaAlteracao,
			gsan.cadastro.cliente.FoneTipo foneTipo,
			gsan.cadastro.cliente.Cliente cliente) {
		this.ddd = ddd;
		this.telefone = telefone;
		this.ramal = ramal;
		this.contato = contato;
		this.indicadorTelefonePadrao = indicadorTelefonePadrao;
		this.ultimaAlteracao = ultimaAlteracao;
		this.foneTipo = foneTipo;
		this.cliente = cliente;
	}

	/** default constructor */
	public ClienteFone() {
	}

	/** minimal constructor */
	public ClienteFone(gsan.cadastro.cliente.FoneTipo foneTipo,
			gsan.cadastro.cliente.Cliente cliente) {
		this.foneTipo = foneTipo;
		this.cliente = cliente;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDdd() {
		return this.ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getRamal() {
		return this.ramal;
	}

	public void setRamal(String ramal) {
		this.ramal = ramal;
	}
	
	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public Short getIndicadorTelefonePadrao() {
		return this.indicadorTelefonePadrao;
	}

	public void setIndicadorTelefonePadrao(Short indicadorTelefonePadrao) {
		this.indicadorTelefonePadrao = indicadorTelefonePadrao;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public gsan.cadastro.cliente.FoneTipo getFoneTipo() {
		return this.foneTipo;
	}

	public void setFoneTipo(gsan.cadastro.cliente.FoneTipo foneTipo) {
		this.foneTipo = foneTipo;
	}

	public gsan.cadastro.cliente.Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(gsan.cadastro.cliente.Cliente cliente) {
		this.cliente = cliente;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	/**
	 * Retorna o valor de dddTelefone
	 * 
	 * @return O valor de dddTelefone
	 */
	public String getDddTelefone() {
		
		if (this.ddd != null){
			return "(" + this.ddd + ")" + this.telefone;
		}
		return this.telefone;
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param other
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if (!(other instanceof ClienteFone)) {
			return false;
		}
		ClienteFone castOther = (ClienteFone) other;

		return new EqualsBuilder().append(this.getDdd(), castOther.getDdd())
				.append(this.getTelefone(), castOther.getTelefone()).append(
						this.getRamal(), castOther.getRamal()).isEquals();

	}

	@Override
	public Filtro retornaFiltro() {
		Filtro filtro = new FiltroClienteFone();
		
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteFone.FONE_TIPO);
		filtro.adicionarParametro(
				new ParametroSimples(FiltroClienteFone.ID, this.getId()));
		return filtro; 		
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}

	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDddTelefone();
	}

	public Integer getIdTabelaAtualizacaoCadastralAux() {
		return idTabelaAtualizacaoCadastralAux;
	}

	public void setIdTabelaAtualizacaoCadastralAux(
			Integer idTabelaAtualizacaoCadastralAux) {
		this.idTabelaAtualizacaoCadastralAux = idTabelaAtualizacaoCadastralAux;
	}

	public Short getIndicadorTelefoneSMS() {
		return indicadorTelefoneSMS;
	}

	public void setIndicadorTelefoneSMS(Short indicadorTelefoneSMS) {
		this.indicadorTelefoneSMS = indicadorTelefoneSMS;
	}
	
	

}
