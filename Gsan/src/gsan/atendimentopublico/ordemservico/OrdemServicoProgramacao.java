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
package gsan.atendimentopublico.ordemservico;

import gsan.interceptor.ObjetoTransacao;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.filtro.Filtro;
import gsan.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class OrdemServicoProgramacao extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	/** persistent field */
	private short nnSequencialProgramacao;

	/** persistent field */
	private short indicadorAtivo;

	/** persistent field */
	private short indicadorEquipePrincipal;

	/** persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private Equipe equipe;

	/** persistent field */
	private Usuario usuarioProgramacao;

	/** persistent field */
	private Usuario usuarioFechamento;

	/** persistent field */
	private ProgramacaoRoteiro programacaoRoteiro;

	/** persistent field */
	private OsProgramNaoEncerMotivo osProgramNaoEncerMotivo;
	
	private EquipamentosEspeciais equipamentoEspecialFaltante;

	/** persistent field */
	private OrdemServico ordemServico;

	private Short situacaoFechamento;
	
	private Short indicadorAcompanhamentoServico;

	public final static Short INDICADOR_ATIVO = new Short("1");

	public final static Short INDICADOR_ATIVO_NAO = new Short("2");
	
	// Caso a Ordem de Servi�o tenha sido realocada para outra Equipe, ent�o o indicador de 
	// acompanhamento de servi�o fica igual a 3.
	public final static Short INDICADOR_ACOMP_SERV_REALOCADA = new Short("3");

	public final static Short SITUACAO_FECHAMENTO = new Short("2");
	
	public final static Short SITUACAO_VAZIO = new Short("0");
	
	public final static Short EQUIPE_PRINCIPAL = new Short("1");

	public OrdemServicoProgramacao() {
		this.indicadorAcompanhamentoServico = 2;
	}

	/** full constructor */
	public OrdemServicoProgramacao(Integer id, short nnSequencialProgramacao,
			short indicadorAtivo, short indicadorEquipePrincipal,
			Date ultimaAlteracao, Equipe equipe, Usuario usuarioProgramacao,
			Usuario usuarioFechamento, ProgramacaoRoteiro programacaoRoteiro,
			OsProgramNaoEncerMotivo osProgramNaoEncerMotivo,
			OrdemServico ordemServico) {
		this.id = id;
		this.nnSequencialProgramacao = nnSequencialProgramacao;
		this.indicadorAtivo = indicadorAtivo;
		this.indicadorEquipePrincipal = indicadorEquipePrincipal;
		this.ultimaAlteracao = ultimaAlteracao;
		this.equipe = equipe;
		this.usuarioProgramacao = usuarioProgramacao;
		this.usuarioFechamento = usuarioFechamento;
		this.programacaoRoteiro = programacaoRoteiro;
		this.osProgramNaoEncerMotivo = osProgramNaoEncerMotivo;
		this.ordemServico = ordemServico;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public short getIndicadorAtivo() {
		return indicadorAtivo;
	}

	public void setIndicadorAtivo(short indicadorAtivo) {
		this.indicadorAtivo = indicadorAtivo;
	}

	public short getIndicadorEquipePrincipal() {
		return indicadorEquipePrincipal;
	}

	public void setIndicadorEquipePrincipal(short indicadorEquipePrincipal) {
		this.indicadorEquipePrincipal = indicadorEquipePrincipal;
	}

	public short getNnSequencialProgramacao() {
		return nnSequencialProgramacao;
	}

	public void setNnSequencialProgramacao(short nnSequencialProgramacao) {
		this.nnSequencialProgramacao = nnSequencialProgramacao;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public OsProgramNaoEncerMotivo getOsProgramNaoEncerMotivo() {
		return osProgramNaoEncerMotivo;
	}

	public void setOsProgramNaoEncerMotivo(
			OsProgramNaoEncerMotivo osProgramNaoEncerMotivo) {
		this.osProgramNaoEncerMotivo = osProgramNaoEncerMotivo;
	}

	public ProgramacaoRoteiro getProgramacaoRoteiro() {
		return programacaoRoteiro;
	}

	public void setProgramacaoRoteiro(ProgramacaoRoteiro programacaoRoteiro) {
		this.programacaoRoteiro = programacaoRoteiro;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Usuario getUsuarioFechamento() {
		return usuarioFechamento;
	}

	public void setUsuarioFechamento(Usuario usuarioFechamento) {
		this.usuarioFechamento = usuarioFechamento;
	}

	public Usuario getUsuarioProgramacao() {
		return usuarioProgramacao;
	}

	public void setUsuarioProgramacao(Usuario usuarioProgramacao) {
		this.usuarioProgramacao = usuarioProgramacao;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public Short getSituacaoFechamento() {
		return situacaoFechamento;
	}

	public void setSituacaoFechamento(Short situacaoFechamento) {
		this.situacaoFechamento = situacaoFechamento;
	}

	public EquipamentosEspeciais getEquipamentoEspecialFaltante() {
		return equipamentoEspecialFaltante;
	}

	public void setEquipamentoEspecialFaltante(
			EquipamentosEspeciais equipamentoEspecialFaltante) {
		this.equipamentoEspecialFaltante = equipamentoEspecialFaltante;
	}

	public Short getIndicadorAcompanhamentoServico() {
		return indicadorAcompanhamentoServico;
	}

	public void setIndicadorAcompanhamentoServico(
			Short indicadorAcompanhamentoServico) {
		this.indicadorAcompanhamentoServico = indicadorAcompanhamentoServico;
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroOrdemServicoProgramacao filtroOrdemServicoProgramacao = new FiltroOrdemServicoProgramacao();
		
		filtroOrdemServicoProgramacao.adicionarCaminhoParaCarregamentoEntidade("equipe");
		filtroOrdemServicoProgramacao.adicionarCaminhoParaCarregamentoEntidade("usuarioProgramacao");
		filtroOrdemServicoProgramacao.adicionarCaminhoParaCarregamentoEntidade("usuarioFechamento");
		filtroOrdemServicoProgramacao.adicionarCaminhoParaCarregamentoEntidade("programacaoRoteiro");
		filtroOrdemServicoProgramacao.adicionarCaminhoParaCarregamentoEntidade("osProgramNaoEncerMotivo");
		filtroOrdemServicoProgramacao.adicionarCaminhoParaCarregamentoEntidade("ordemServico");
		filtroOrdemServicoProgramacao.adicionarParametro(
				new ParametroSimples(FiltroOrdemServicoProgramacao.ID, this.getId()));
		
		return filtroOrdemServicoProgramacao; 
	}
	
}
