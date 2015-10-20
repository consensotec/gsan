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
package gcom.faturamento.bean;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.faturamento.conta.ContaMotivoRetificacao;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * Encapsula as informa��es necess�rias para retificar contas de um conjunto de im�veis
 *
 * @author Raphael Rossiter
 * 
 * @date 02/07/2009
 */
public class RetificarConjuntoContaConsumosHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Collection colecaoImovel;
	
	private Integer idGrupoFaturamento;
	
	private Integer codigoCliente;
	
	private Integer codigoClienteSuperior;
	
	private Integer anoMes; 
	
	private Integer anoMesFim;
	
	private ContaMotivoRetificacao contaMotivoRetificacao;
	
	private Usuario usuarioLogado;
	
	private Date dataVencimentoContaInicio; 
	
	private Date dataVencimentoContaFim;
	
	private String indicadorContaPaga;
	
	private LigacaoAguaSituacao ligacaoAguaSituacao;
	
	private LigacaoEsgotoSituacao ligacaoEsgotoSituacao;
	
	private Integer consumoAgua;
	
	private Integer volumeEsgoto;
	
	private Date dataVencimentoContaRetificacao;
	
	private Short indicadorCategoriaEconomiaConta;
	
	public RetificarConjuntoContaConsumosHelper(){}

	public Integer getAnoMes() {
		return anoMes;
	}

	public void setAnoMes(Integer anoMes) {
		this.anoMes = anoMes;
	}

	public Integer getAnoMesFim() {
		return anoMesFim;
	}

	public void setAnoMesFim(Integer anoMesFim) {
		this.anoMesFim = anoMesFim;
	}

	public Collection getColecaoImovel() {
		return colecaoImovel;
	}

	public void setColecaoImovel(Collection colecaoImovel) {
		this.colecaoImovel = colecaoImovel;
	}

	public ContaMotivoRetificacao getContaMotivoRetificacao() {
		return contaMotivoRetificacao;
	}

	public void setContaMotivoRetificacao(
			ContaMotivoRetificacao contaMotivoRetificacao) {
		this.contaMotivoRetificacao = contaMotivoRetificacao;
	}

	public Date getDataVencimentoContaFim() {
		return dataVencimentoContaFim;
	}

	public void setDataVencimentoContaFim(Date dataVencimentoContaFim) {
		this.dataVencimentoContaFim = dataVencimentoContaFim;
	}

	public Date getDataVencimentoContaInicio() {
		return dataVencimentoContaInicio;
	}

	public void setDataVencimentoContaInicio(Date dataVencimentoContaInicio) {
		this.dataVencimentoContaInicio = dataVencimentoContaInicio;
	}

	public String getIndicadorContaPaga() {
		return indicadorContaPaga;
	}

	public void setIndicadorContaPaga(String indicadorContaPaga) {
		this.indicadorContaPaga = indicadorContaPaga;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public Integer getConsumoAgua() {
		return consumoAgua;
	}

	public void setConsumoAgua(Integer consumoAgua) {
		this.consumoAgua = consumoAgua;
	}

	public Integer getVolumeEsgoto() {
		return volumeEsgoto;
	}

	public void setVolumeEsgoto(Integer volumeEsgoto) {
		this.volumeEsgoto = volumeEsgoto;
	}

	public LigacaoAguaSituacao getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}

	public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	public LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}

	public void setLigacaoEsgotoSituacao(LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

	public Date getDataVencimentoContaRetificacao() {
		return dataVencimentoContaRetificacao;
	}

	public void setDataVencimentoContaRetificacao(
			Date dataVencimentoContaRetificacao) {
		this.dataVencimentoContaRetificacao = dataVencimentoContaRetificacao;
	}

	public Integer getIdGrupoFaturamento() {
		return idGrupoFaturamento;
	}

	public void setIdGrupoFaturamento(Integer idGrupoFaturamento) {
		this.idGrupoFaturamento = idGrupoFaturamento;
	}

	public Integer getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public Integer getCodigoClienteSuperior() {
		return codigoClienteSuperior;
	}

	public void setCodigoClienteSuperior(Integer codigoClienteSuperior) {
		this.codigoClienteSuperior = codigoClienteSuperior;
	}

	public Short getIndicadorCategoriaEconomiaConta() {
		return indicadorCategoriaEconomiaConta;
	}

	public void setIndicadorCategoriaEconomiaConta(
			Short indicadorCategoriaEconomiaConta) {
		this.indicadorCategoriaEconomiaConta = indicadorCategoriaEconomiaConta;
	}
}
