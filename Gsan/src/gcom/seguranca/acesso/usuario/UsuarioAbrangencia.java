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
package gcom.seguranca.acesso.usuario;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;

import javax.jms.Session;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.CallbackException;
import org.hibernate.classic.Lifecycle;

/** @author Hibernate CodeGenerator */
public class UsuarioAbrangencia extends ObjetoTransacao implements Lifecycle {

	private static final long serialVersionUID = 1L;
	
	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private String descricao;

	/** nullable persistent field */
	private String descricaoAbreviada;

	/** nullable persistent field */
	private Short indicadorUso;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private gcom.seguranca.acesso.usuario.UsuarioAbrangencia usuarioAbrangenciaSuperior;

	// CONTANTES
	public final static Integer ESTADO = new Integer("1");

	public final static Integer GERENCIA_REGIONAL = new Integer("2");

	public final static Integer ELO_POLO = new Integer("3");

	public final static Integer LOCALIDADE = new Integer("4");
	
	public final static Integer UNIDADE_NEGOCIO = new Integer("5");
    
     //CONTANTES
    public final static int ESTADO_INT =1;

    public final static int GERENCIA_REGIONAL_INT = 2;
    
    public final static int UNIDADE_NEGOCIO_INT = 5;

    public final static int ELO_POLO_INT = 3;

    public final static int LOCALIDADE_INT = 4;

	/** full constructor */
	public UsuarioAbrangencia(
			String descricao,
			String descricaoAbreviada,
			Short indicadorUso,
			Date ultimaAlteracao,
			gcom.seguranca.acesso.usuario.UsuarioAbrangencia usuarioAbrangenciaSuperior) {
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.usuarioAbrangenciaSuperior = usuarioAbrangenciaSuperior;

	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}
	public String getDescricaoParaRegistroTransacao() {
		return this.getDescricao();
	}
	/** default constructor */
	public UsuarioAbrangencia() {
	}

	/** minimal constructor */
	public UsuarioAbrangencia(
			gcom.seguranca.acesso.usuario.UsuarioAbrangencia usuarioAbrangenciaSuperior) {
		this.usuarioAbrangenciaSuperior = usuarioAbrangenciaSuperior;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoAbreviada() {
		return this.descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public Short getIndicadorUso() {
		return this.indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public gcom.seguranca.acesso.usuario.UsuarioAbrangencia getUsuarioAbrangenciaSuperior() {
		return this.usuarioAbrangenciaSuperior;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param session
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	public boolean onUpdate(Session session) {
		return false;
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param session
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	public boolean onDelete(Session session) {
		return false;
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param session
	 *            Descri��o do par�metro
	 * @param serializable
	 *            Descri��o do par�metro
	 */
	public void onLoad(Session session, Serializable serializable) {
	}

	public void setUsuarioAbrangenciaSuperior(
			gcom.seguranca.acesso.usuario.UsuarioAbrangencia usuarioAbrangenciaSuperior) {
		this.usuarioAbrangenciaSuperior = usuarioAbrangenciaSuperior;
	}

	public boolean onSave(org.hibernate.Session arg0) throws CallbackException {
		if (this.usuarioAbrangenciaSuperior == null) {
			UsuarioAbrangencia temp = new UsuarioAbrangencia();

			temp.setId(this.id);
			this.usuarioAbrangenciaSuperior = temp;
		}
		return false;
	}

	public boolean onUpdate(org.hibernate.Session arg0)
			throws CallbackException {
		return false;
	}

	public boolean onDelete(org.hibernate.Session arg0)
			throws CallbackException {
		return false;
	}

	public void onLoad(org.hibernate.Session arg0, Serializable arg1) {
	}
	
	public Filtro retornaFiltroRegistroOperacao() {
		FiltroUsuarioAbrangencia filtro = new FiltroUsuarioAbrangencia();

		filtro.adicionarParametro(
			new ParametroSimples(FiltroUsuarioAbrangencia.ID,this.getId()));
		return filtro;
	}
	
	@Override
	public Filtro retornaFiltro() {
		return null;
	}

}
