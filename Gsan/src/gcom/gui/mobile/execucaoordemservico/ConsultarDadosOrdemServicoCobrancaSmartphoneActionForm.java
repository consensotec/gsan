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
package gcom.gui.mobile.execucaoordemservico;

import org.apache.struts.validator.ValidatorActionForm;

public class ConsultarDadosOrdemServicoCobrancaSmartphoneActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String idArquivo;
	private String empresa;
	private String descricaoEmpresa;
	private String idTipoOS;
	private String descricaoTipoOS;
	private String origemOS;
	private String ordemServico;	
	private String matricula;
	private String idTipoOrdemServico;
	private String descricaoTipoOrdemServico;	
	
	private String descricaoServicoTipo;
	private String idServicoTipo;
	
	private String idMotivoEncerramento;
	private String descricaoMotivoEncerramento;
	
	private String dataExecucao;
	private String horaExecucao;
	private String parecer;
	private String icConferida;
	
	private String descricaoMotivoCorte;
	private String descricaoTipoCorte;
	private String leituraCorte;
	private String tipoExecucao;
	private String tipoPavimento;
	private String comCalcada;
	

	private String descricaoMotivoSupressao;
	private String descricaoTipoSupressao;
	private String leituraSupressao;	
	
	private String descricaoDocumentoEntregue;
	private String idsSituacaoesEncontradas;
	
	private String dataInstalacao;
	private String tipoMedicao;
	private String localInstalacao;
	private String protecao;
	private String leituraInstalacao;
	private String numeroSelo;
	private String numeroLacre;
	private String tipoPoco;
	
	// Dados da Geração do Débito
    private String idTipoDebito;
    private String descricaoTipoDebito;
    private String valorDebito;
    private String motivoNaoCobranca;
    private String percentualCobranca;
    private String quantidadeParcelas;
    private String valorParcelas;
    private String qtdeMaxParcelas;
    private String alteracaoValor;
    private String icExibirDebitos;
	
	// Dados da OS de Substituição
    private String numeroLeitura;
    private String situacaoHidrometro;
    private String localArmazenagem;
    private String tipoHidrometro;
    private String numeroHidrometro;
    private String numeroTombamento;
    private String numeroLeituraInstalacao;
	
	// Dados da OS de Remoção
    private String localInstalacaoHidrometro;
    private String protecaoHidrometro;
    private String cavalete;
	
	// Dados da OS de Instalação de Caixa de Proteção
    private String trocaProtecao;
    private String trocaRegistro;
	
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getDescricaoEmpresa() {
		return descricaoEmpresa;
	}
	public void setDescricaoEmpresa(String descricaoEmpresa) {
		this.descricaoEmpresa = descricaoEmpresa;
	}
	public String getIdTipoOS() {
		return idTipoOS;
	}
	public void setIdTipoOS(String idTipoOS) {
		this.idTipoOS = idTipoOS;
	}
	public String getDescricaoTipoOS() {
		return descricaoTipoOS;
	}
	public void setDescricaoTipoOS(String descricaoTipoOS) {
		this.descricaoTipoOS = descricaoTipoOS;
	}
	public String getOrigemOS() {
		return origemOS;
	}
	public void setOrigemOS(String origemOS) {
		this.origemOS = origemOS;
	}
	public String getOrdemServico() {
		return ordemServico;
	}
	public void setOrdemServico(String ordemServico) {
		this.ordemServico = ordemServico;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getIdServicoTipo() {
		return idServicoTipo;
	}
	public void setIdServicoTipo(String idServicoTipo) {
		this.idServicoTipo = idServicoTipo;
	}
	public String getDescricaoServicoTipo() {
		return descricaoServicoTipo;
	}
	public void setDescricaoServicoTipo(String descricaoServicoTipo) {
		this.descricaoServicoTipo = descricaoServicoTipo;
	}
	public String getIdTipoOrdemServico() {
		return idTipoOrdemServico;
	}
	public void setIdTipoOrdemServico(String idTipoOrdemServico) {
		this.idTipoOrdemServico = idTipoOrdemServico;
	}
	public String getDescricaoTipoOrdemServico() {
		return descricaoTipoOrdemServico;
	}
	public void setDescricaoTipoOrdemServico(String descricaoTipoOrdemServico) {
		this.descricaoTipoOrdemServico = descricaoTipoOrdemServico;
	}
	public String getIdMotivoEncerramento() {
		return idMotivoEncerramento;
	}
	public void setIdMotivoEncerramento(String idMotivoEncerramento) {
		this.idMotivoEncerramento = idMotivoEncerramento;
	}
	public String getDescricaoMotivoEncerramento() {
		return descricaoMotivoEncerramento;
	}
	public void setDescricaoMotivoEncerramento(String descricaoMotivoEncerramento) {
		this.descricaoMotivoEncerramento = descricaoMotivoEncerramento;
	}
	public String getDataExecucao() {
		return dataExecucao;
	}
	public void setDataExecucao(String dataExecucao) {
		this.dataExecucao = dataExecucao;
	}
	public String getHoraExecucao() {
		return horaExecucao;
	}
	public void setHoraExecucao(String horaExecucao) {
		this.horaExecucao = horaExecucao;
	}
	public String getParecer() {
		return parecer;
	}
	public void setParecer(String parecer) {
		this.parecer = parecer;
	}
	public String getIcConferida() {
		return icConferida;
	}
	public void setIcConferida(String icConferida) {
		this.icConferida = icConferida;
	}
	public String getDescricaoMotivoCorte() {
		return descricaoMotivoCorte;
	}
	public void setDescricaoMotivoCorte(String descricaoMotivoCorte) {
		this.descricaoMotivoCorte = descricaoMotivoCorte;
	}
	public String getDescricaoTipoCorte() {
		return descricaoTipoCorte;
	}
	public void setDescricaoTipoCorte(String descricaoTipoCorte) {
		this.descricaoTipoCorte = descricaoTipoCorte;
	}
	public String getLeituraCorte() {
		return leituraCorte;
	}
	public void setLeituraCorte(String leituraCorte) {
		this.leituraCorte = leituraCorte;
	}
	public String getPercentualCobranca() {
		return percentualCobranca;
	}
	public void setPercentualCobranca(String percentualCobranca) {
		this.percentualCobranca = percentualCobranca;
	}
	public String getDescricaoMotivoSupressao() {
		return descricaoMotivoSupressao;
	}
	public void setDescricaoMotivoSupressao(String descricaoMotivoSupressao) {
		this.descricaoMotivoSupressao = descricaoMotivoSupressao;
	}
	public String getDescricaoTipoSupressao() {
		return descricaoTipoSupressao;
	}
	public void setDescricaoTipoSupressao(String descricaoTipoSupressao) {
		this.descricaoTipoSupressao = descricaoTipoSupressao;
	}
	public String getLeituraSupressao() {
		return leituraSupressao;
	}
	public void setLeituraSupressao(String leituraSupressao) {
		this.leituraSupressao = leituraSupressao;
	}
	public String getDescricaoDocumentoEntregue() {
		return descricaoDocumentoEntregue;
	}
	public void setDescricaoDocumentoEntregue(String descricaoDocumentoEntregue) {
		this.descricaoDocumentoEntregue = descricaoDocumentoEntregue;
	}
	public String getIdsSituacaoesEncontradas() {
		return idsSituacaoesEncontradas;
	}
	public void setIdsSituacaoesEncontradas(String idsSituacaoesEncontradas) {
		this.idsSituacaoesEncontradas = idsSituacaoesEncontradas;
	}
	public String getDataInstalacao() {
		return dataInstalacao;
	}
	public void setDataInstalacao(String dataInstalacao) {
		this.dataInstalacao = dataInstalacao;
	}
	public String getTipoMedicao() {
		return tipoMedicao;
	}
	public void setTipoMedicao(String tipoMedicao) {
		this.tipoMedicao = tipoMedicao;
	}
	public String getLocalInstalacao() {
		return localInstalacao;
	}
	public void setLocalInstalacao(String localInstalacao) {
		this.localInstalacao = localInstalacao;
	}
	public String getProtecao() {
		return protecao;
	}
	public void setProtecao(String protecao) {
		this.protecao = protecao;
	}
	public String getLeituraInstalacao() {
		return leituraInstalacao;
	}
	public void setLeituraInstalacao(String leituraInstalacao) {
		this.leituraInstalacao = leituraInstalacao;
	}
	public String getNumeroSelo() {
		return numeroSelo;
	}
	public void setNumeroSelo(String numeroSelo) {
		this.numeroSelo = numeroSelo;
	}
	public String getNumeroLacre() {
		return numeroLacre;
	}
	public void setNumeroLacre(String numeroLacre) {
		this.numeroLacre = numeroLacre;
	}
	public String getTipoPoco() {
		return tipoPoco;
	}
	public void setTipoPoco(String tipoPoco) {
		this.tipoPoco = tipoPoco;
	}
	public String getIdArquivo() {
		return idArquivo;
	}
	public void setIdArquivo(String idArquivo) {
		this.idArquivo = idArquivo;
	}
	public String getIdTipoDebito() {
		return idTipoDebito;
	}
	public void setIdTipoDebito(String idTipoDebito) {
		this.idTipoDebito = idTipoDebito;
	}
	public String getDescricaoTipoDebito() {
		return descricaoTipoDebito;
	}
	public void setDescricaoTipoDebito(String descricaoTipoDebito) {
		this.descricaoTipoDebito = descricaoTipoDebito;
	}
	public String getValorDebito() {
		return valorDebito;
	}
	public void setValorDebito(String valorDebito) {
		this.valorDebito = valorDebito;
	}
	public String getMotivoNaoCobranca() {
		return motivoNaoCobranca;
	}
	public void setMotivoNaoCobranca(String motivoNaoCobranca) {
		this.motivoNaoCobranca = motivoNaoCobranca;
	}
	public String getQuantidadeParcelas() {
		return quantidadeParcelas;
	}
	public void setQuantidadeParcelas(String quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}
	public String getValorParcelas() {
		return valorParcelas;
	}
	public void setValorParcelas(String valorParcelas) {
		this.valorParcelas = valorParcelas;
	}
	public String getQtdeMaxParcelas() {
		return qtdeMaxParcelas;
	}
	public void setQtdeMaxParcelas(String qtdeMaxParcelas) {
		this.qtdeMaxParcelas = qtdeMaxParcelas;
	}
	public String getAlteracaoValor() {
		return alteracaoValor;
	}
	public void setAlteracaoValor(String alteracaoValor) {
		this.alteracaoValor = alteracaoValor;
	}
	public String getIcExibirDebitos() {
		return icExibirDebitos;
	}
	public void setIcExibirDebitos(String icExibirDebitos) {
		this.icExibirDebitos = icExibirDebitos;
	}
	public String getNumeroLeitura() {
		return numeroLeitura;
	}
	public void setNumeroLeitura(String numeroLeitura) {
		this.numeroLeitura = numeroLeitura;
	}
	public String getSituacaoHidrometro() {
		return situacaoHidrometro;
	}
	public void setSituacaoHidrometro(String situacaoHidrometro) {
		this.situacaoHidrometro = situacaoHidrometro;
	}
	public String getLocalArmazenagem() {
		return localArmazenagem;
	}
	public void setLocalArmazenagem(String localArmazenagem) {
		this.localArmazenagem = localArmazenagem;
	}
	public String getTipoHidrometro() {
		return tipoHidrometro;
	}
	public void setTipoHidrometro(String tipoHidrometro) {
		this.tipoHidrometro = tipoHidrometro;
	}
	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}
	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}
	public String getLocalInstalacaoHidrometro() {
		return localInstalacaoHidrometro;
	}
	public void setLocalInstalacaoHidrometro(String localInstalacaoHidrometro) {
		this.localInstalacaoHidrometro = localInstalacaoHidrometro;
	}
	public String getProtecaoHidrometro() {
		return protecaoHidrometro;
	}
	public void setProtecaoHidrometro(String protecaoHidrometro) {
		this.protecaoHidrometro = protecaoHidrometro;
	}
	public String getCavalete() {
		return cavalete;
	}
	public void setCavalete(String cavalete) {
		this.cavalete = cavalete;
	}
	public String getTrocaProtecao() {
		return trocaProtecao;
	}
	public void setTrocaProtecao(String trocaProtecao) {
		this.trocaProtecao = trocaProtecao;
	}
	public String getTrocaRegistro() {
		return trocaRegistro;
	}
	public void setTrocaRegistro(String trocaRegistro) {
		this.trocaRegistro = trocaRegistro;
	}
	public String getNumeroTombamento() {
		return numeroTombamento;
	}
	public void setNumeroTombamento(String numeroTombamento) {
		this.numeroTombamento = numeroTombamento;
	}
	public String getNumeroLeituraInstalacao() {
		return numeroLeituraInstalacao;
	}
	public void setNumeroLeituraInstalacao(String numeroLeituraInstalacao) {
		this.numeroLeituraInstalacao = numeroLeituraInstalacao;
	}
	public String getTipoExecucao() {
		return tipoExecucao;
	}
	public void setTipoExecucao(String tipoExecucao) {
		this.tipoExecucao = tipoExecucao;
	}
	public String getTipoPavimento() {
		return tipoPavimento;
	}
	public void setTipoPavimento(String tipoPavimento) {
		this.tipoPavimento = tipoPavimento;
	}
	public String getComCalcada() {
		return comCalcada;
	}
	public void setComCalcada(String comCalcada) {
		this.comCalcada = comCalcada;
	}
}