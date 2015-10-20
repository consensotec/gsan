/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.atualizacaocadastral.bean;

import gcom.atualizacaocadastral.bean.AtualizacoesPorInconsistenciaHelper;

import java.io.Serializable;
import java.util.Collection;



public class DadosMovimentoAtualizacaoCadastralDMHelper implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Dados Movimento
	private String id;
	private String dataRecebimento;
	private String quantidadeTotal;
	private String quantidadePendente;
	private String quantidadePendenteInscricao;
	private String situacao;
	private String total;
	private String totalPendente;
	private String totalPendenteInscricao;
	
	
	//Dados Imovel
	private String codigoSetor;
	private String numeroQuadra;
	private String numeroLote;
	private String idCliente;
	private String idImovel;
	private String nomeCadastrador;
	private String imovelSituacao;
	private String idImovelAtlzCadastral;
	
	//Dados imovel atualizacao cadastral
	private String matricula;
	private String setor;
	private String quadra;
	private String lote;
	private String visitas;
	private Collection<AtualizacoesPorInconsistenciaHelper> colecaoAtualizacoesHelper;
	private Integer imacUltimoRetorno;
	
	private boolean retornarCampo;
	
	public DadosMovimentoAtualizacaoCadastralDMHelper() {
		
	}
	
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getDataRecebimento() {
		return dataRecebimento;
	}

	public void setDataRecebimento(String dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}

	public String getQuantidadeTotal() {
		return quantidadeTotal;
	}

	public void setQuantidadeTotal(String quantidadeTotal) {
		this.quantidadeTotal = quantidadeTotal;
	}

	public String getQuantidadePendente() {
		return quantidadePendente;
	}

	public void setQuantidadePendente(String quantidadePendente) {
		this.quantidadePendente = quantidadePendente;
	}

	public String getQuantidadePendenteInscricao() {
		return quantidadePendenteInscricao;
	}

	public void setQuantidadePendenteInscricao(String quantidadePendenteInscricao) {
		this.quantidadePendenteInscricao = quantidadePendenteInscricao;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}


	public String getCodigoSetor() {
		return codigoSetor;
	}


	public void setCodigoSetor(String codigoSetor) {
		this.codigoSetor = codigoSetor;
	}


	public String getNumeroQuadra() {
		return numeroQuadra;
	}


	public void setNumeroQuadra(String numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}


	public String getNumeroLote() {
		return numeroLote;
	}


	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}


	public String getIdCliente() {
		return idCliente;
	}


	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}


	public String getIdImovel() {
		return idImovel;
	}


	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}


	public String getNomeCadastrador() {
		return nomeCadastrador;
	}


	public void setNomeCadastrador(String nomeCadastrador) {
		this.nomeCadastrador = nomeCadastrador;
	}


	public String getImovelSituacao() {
		return imovelSituacao;
	}


	public void setImovelSituacao(String imovelSituacao) {
		this.imovelSituacao = imovelSituacao;
	}


	public String getIdImovelAtlzCadastral() {
		return idImovelAtlzCadastral;
	}


	public void setIdImovelAtlzCadastral(String idImovelAtlzCadastral) {
		this.idImovelAtlzCadastral = idImovelAtlzCadastral;
	}


	public String getMatricula() {
		return matricula;
	}


	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}


	public String getSetor() {
		return setor;
	}


	public void setSetor(String setor) {
		this.setor = setor;
	}


	public String getQuadra() {
		return quadra;
	}


	public void setQuadra(String quadra) {
		this.quadra = quadra;
	}


	public String getLote() {
		return lote;
	}


	public void setLote(String lote) {
		this.lote = lote;
	}


	public String getVisitas() {
		return visitas;
	}


	public void setVisitas(String visitas) {
		this.visitas = visitas;
	}


	public Collection<AtualizacoesPorInconsistenciaHelper> getColecaoAtualizacoesHelper() {
		return colecaoAtualizacoesHelper;
	}


	public void setColecaoAtualizacoesHelper(
			Collection<AtualizacoesPorInconsistenciaHelper> colecaoAtualizacoesHelper) {
		this.colecaoAtualizacoesHelper = colecaoAtualizacoesHelper;
	}


	public String getTotal() {
		return total;
	}


	public void setTotal(String total) {
		this.total = total;
	}


	public String getTotalPendente() {
		return totalPendente;
	}


	public void setTotalPendente(String totalPendente) {
		this.totalPendente = totalPendente;
	}


	public String getTotalPendenteInscricao() {
		return totalPendenteInscricao;
	}


	public void setTotalPendenteInscricao(String totalPendenteInscricao) {
		this.totalPendenteInscricao = totalPendenteInscricao;
	}


	public Integer getImacUltimoRetorno() {
		return imacUltimoRetorno;
	}


	public void setImacUltimoRetorno(Integer imacUltimoRetorno) {
		this.imacUltimoRetorno = imacUltimoRetorno;
	}


	public boolean isRetornarCampo() {
		return retornarCampo;
	}


	public void setRetornarCampo(boolean retornarCampo) {
		this.retornarCampo = retornarCampo;
	}
	
}
