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
 * Anderson Italo felinto de Lima
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

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import java.io.Serializable;
import java.util.Date;

/** @author Hibernate CodeGenerator */
public class SolicitacaoAcesso implements Serializable {

	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	/** identifier field */
	private Funcionario funcionarioSolicitante;

	/** persistent field */
	private Funcionario funcionarioResponsavel;

	/** persistent field */
	private Short indicadorNotificarResponsavel;

	/** persistent field */
	private UsuarioTipo usuarioTipo;

	/** persistent field */
	private Empresa empresa;

	/** persistent field */
	private Funcionario funcionario;

	/** persistent field */
	private String nomeUsuario;
	
	/** persistent field */
	private String cpf;

	/** persistent field */
	private Date dataNascimento;
	
	/** persistent field */
	private UnidadeOrganizacional unidadeOrganizacional;
	
	/** persistent field */
	private String login;
	
	/** persistent field */
	private String email;
	
	/** persistent field */
	private Date periodoInicial;
	
	/** persistent field */
	private Date periodoFinal;
	
	private SolicitacaoAcessoSituacao solicitacaoAcessoSituacao;

	/** persistent field */
	private Date ultimaAlteracao;
	
	/** persistent field */
	private UsuarioAbrangencia usuarioAbrangencia;
	
	/** persistent field */
	private GerenciaRegional gerenciaRegional;
	
	/** persistent field */
	private UnidadeNegocio unidadeNegocio;
	
	/** persistent field */
	private Localidade localidade;
	
	/** persistent field */
	private Localidade localidadeElo;
		
	/** persistent field */
	private Date dataAutorizacao;
	
	/** persistent field */
	private Date dataCadastramento;
	
	/** persistent field */
	private Date dataSolicitacao;

	/** default constructor */
	public SolicitacaoAcesso() {

	}

	/** minimal constructor */
	public SolicitacaoAcesso(Funcionario funcionarioSolicitante, Funcionario funcionarioResponsavel, UsuarioTipo usuarioTipo,
			Empresa empresa, Funcionario funcionario, String nomeUsuario, Date dataNascimento, UnidadeOrganizacional unidadeOrganizacional, 
			String login, String email, SolicitacaoAcessoSituacao solicitacaoAcessoSituacao, Date ultimaAlteracao, 
			Date periodoInicial, Date periodoFinal, Date dataSolicitacao) {

		this.funcionarioSolicitante = funcionarioSolicitante;
		this.funcionarioResponsavel = funcionarioResponsavel;
		this.usuarioTipo = usuarioTipo;
		this.empresa = empresa;
		this.funcionario = funcionario;
		this.unidadeOrganizacional = unidadeOrganizacional;
		this.solicitacaoAcessoSituacao = solicitacaoAcessoSituacao;
		this.nomeUsuario = nomeUsuario;
		this.dataNascimento = dataNascimento;
		this.login = login;
		this.email = email;
		this.periodoInicial = periodoInicial;
		this.periodoFinal = periodoFinal;
		this.ultimaAlteracao = ultimaAlteracao;
		this.dataSolicitacao = dataSolicitacao;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Funcionario getFuncionarioResponsavel() {
		return funcionarioResponsavel;
	}

	public void setFuncionarioResponsavel(Funcionario funcionarioResponsavel) {
		this.funcionarioResponsavel = funcionarioResponsavel;
	}

	public Funcionario getFuncionarioSolicitante() {
		return funcionarioSolicitante;
	}

	public void setFuncionarioSolicitante(Funcionario funcionarioSolicitante) {
		this.funcionarioSolicitante = funcionarioSolicitante;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getIndicadorNotificarResponsavel() {
		return indicadorNotificarResponsavel;
	}

	public void setIndicadorNotificarResponsavel(Short indicadorNotificarResponsavel) {
		this.indicadorNotificarResponsavel = indicadorNotificarResponsavel;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public Date getPeriodoFinal() {
		return periodoFinal;
	}

	public void setPeriodoFinal(Date periodoFinal) {
		this.periodoFinal = periodoFinal;
	}

	public Date getPeriodoInicial() {
		return periodoInicial;
	}

	public void setPeriodoInicial(Date periodoInicial) {
		this.periodoInicial = periodoInicial;
	}

	public SolicitacaoAcessoSituacao getSolicitacaoAcessoSituacao() {
		return solicitacaoAcessoSituacao;
	}

	public void setSolicitacaoAcessoSituacao(
			SolicitacaoAcessoSituacao solicitacaoAcessoSituacao) {
		this.solicitacaoAcessoSituacao = solicitacaoAcessoSituacao;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public UnidadeOrganizacional getUnidadeOrganizacional() {
		return unidadeOrganizacional;
	}

	public void setUnidadeOrganizacional(UnidadeOrganizacional unidadeOrganizacional) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}

	public UsuarioTipo getUsuarioTipo() {
		return usuarioTipo;
	}

	public void setUsuarioTipo(UsuarioTipo usuarioTipo) {
		this.usuarioTipo = usuarioTipo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataAutorizacao() {
		return dataAutorizacao;
	}

	public void setDataAutorizacao(Date dataAutorizacao) {
		this.dataAutorizacao = dataAutorizacao;
	}

	public Date getDataCadastramento() {
		return dataCadastramento;
	}

	public void setDataCadastramento(Date dataCadastramento) {
		this.dataCadastramento = dataCadastramento;
	}

	public Date getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public UsuarioAbrangencia getUsuarioAbrangencia() {
		return usuarioAbrangencia;
	}

	public void setUsuarioAbrangencia(UsuarioAbrangencia usuarioAbrangencia) {
		this.usuarioAbrangencia = usuarioAbrangencia;
	}

	public GerenciaRegional getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public Localidade getLocalidadeElo() {
		return localidadeElo;
	}

	public void setLocalidadeElo(Localidade localidadeElo) {
		this.localidadeElo = localidadeElo;
	}

}
