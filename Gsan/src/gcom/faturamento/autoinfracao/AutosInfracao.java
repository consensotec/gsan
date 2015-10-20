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
package gcom.faturamento.autoinfracao;

import gcom.atendimentopublico.ordemservico.FiscalizacaoSituacao;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.imovel.Imovel;
import gcom.faturamento.debito.DebitoTipo;
import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

public class AutosInfracao extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Date dataEmissao;
	
	private Date dataInicioRecurso;
	
	private Date dataTerminoRecurso;
	
	private Integer anoMesReferenciaGerada;
	
	private String observacao;
	
	private Integer numeroParcelasDebito;
	
	private Imovel imovel;
	
	private OrdemServico ordemServico;
	
	private Funcionario funcionario;
	
	private FiscalizacaoSituacao fiscalizacaoSituacao;
	
	private AutoInfracaoSituacao autoInfracaoSituacao;
	
	private Date ultimaAlteracao;
	
	private DebitoTipo debitoTipo;
	
	private Usuario usuario;
	
	public AutosInfracao(){}

	public Integer getAnoMesReferenciaGerada() {
		return anoMesReferenciaGerada;
	}

	public void setAnoMesReferenciaGerada(Integer anoMesReferenciaGerada) {
		this.anoMesReferenciaGerada = anoMesReferenciaGerada;
	}

	public AutoInfracaoSituacao getAutoInfracaoSituacao() {
		return autoInfracaoSituacao;
	}

	public void setAutoInfracaoSituacao(AutoInfracaoSituacao autoInfracaoSituacao) {
		this.autoInfracaoSituacao = autoInfracaoSituacao;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataInicioRecurso() {
		return dataInicioRecurso;
	}

	public void setDataInicioRecurso(Date dataInicioRecurso) {
		this.dataInicioRecurso = dataInicioRecurso;
	}

	public Date getDataTerminoRecurso() {
		return dataTerminoRecurso;
	}

	public void setDataTerminoRecurso(Date dataTerminoRecurso) {
		this.dataTerminoRecurso = dataTerminoRecurso;
	}

	public FiscalizacaoSituacao getFiscalizacaoSituacao() {
		return fiscalizacaoSituacao;
	}

	public void setFiscalizacaoSituacao(FiscalizacaoSituacao fiscalizacaoSituacao) {
		this.fiscalizacaoSituacao = fiscalizacaoSituacao;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public DebitoTipo getDebitoTipo() {
		return debitoTipo;
	}

	public void setDebitoTipo(DebitoTipo debitoTipo) {
		this.debitoTipo = debitoTipo;
	}

	
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroAutosInfracao filtroAutosInfracao = new FiltroAutosInfracao();
		
		filtroAutosInfracao. adicionarCaminhoParaCarregamentoEntidade("imovel");
		filtroAutosInfracao. adicionarCaminhoParaCarregamentoEntidade("ordemServico");
		filtroAutosInfracao. adicionarCaminhoParaCarregamentoEntidade("funcionario");
		filtroAutosInfracao. adicionarCaminhoParaCarregamentoEntidade("fiscalizacaoSituacao");
		filtroAutosInfracao. adicionarCaminhoParaCarregamentoEntidade("autoInfracaoSituacao");
		filtroAutosInfracao. adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
		
		filtroAutosInfracao. adicionarParametro(
				new ParametroSimples(FiltroAutosInfracao.ID, this.getId()));
		return filtroAutosInfracao; 
	}

	/**
	 * @return Retorna o campo numeroParcelasDebito.
	 */
	public Integer getNumeroParcelasDebito() {
		return numeroParcelasDebito;
	}

	/**
	 * @param numeroParcelasDebito O numeroParcelasDebito a ser setado.
	 */
	public void setNumeroParcelasDebito(Integer numeroParcelasDebito) {
		this.numeroParcelasDebito = numeroParcelasDebito;
	}

	/**
	 * @return Retorna o campo usuario.
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario O usuario a ser setado.
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
