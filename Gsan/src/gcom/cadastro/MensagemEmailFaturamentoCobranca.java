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
package gcom.cadastro;

import gcom.cadastro.cliente.Cliente;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.CobrancaDocumento;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.conta.Conta;

import java.io.Serializable;
import java.util.Date;


public class MensagemEmailFaturamentoCobranca implements Serializable {		private static final long serialVersionUID = 1L;

    private Integer id;
    private String descricaoMensagem;
    private String emailDestino;
    private Date dataPrevisaoEnvio;
    private Date dataLimiteEnvio;
    private Date dataEnvio;
    private Integer quantidadeTentativasEnvio;
    private Date ultimaAlteracao;
    private Integer anoMesReferenciaConta;
    
    private Cliente cliente;
    private ParametrosMSGSMSEmail parametroMensagemSMSEmail;
    private CobrancaAcao acaoCobranca;
    private Conta conta;
    private FaturamentoGrupo grupoFaturamento;
    private CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando;
    private CobrancaDocumento cobrancaDocumento;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricaoMensagem() {
		return descricaoMensagem;
	}
	public void setDescricaoMensagem(String descricaoMensagem) {
		this.descricaoMensagem = descricaoMensagem;
	}
	public String getEmailDestino() {
		return emailDestino;
	}
	public void setEmailDestino(String emailDestino) {
		this.emailDestino = emailDestino;
	}
	public Date getDataPrevisaoEnvio() {
		return dataPrevisaoEnvio;
	}
	public void setDataPrevisaoEnvio(Date dataPrevisaoEnvio) {
		this.dataPrevisaoEnvio = dataPrevisaoEnvio;
	}
	public Date getDataLimiteEnvio() {
		return dataLimiteEnvio;
	}
	public void setDataLimiteEnvio(Date dataLimiteEnvio) {
		this.dataLimiteEnvio = dataLimiteEnvio;
	}
	public Date getDataEnvio() {
		return dataEnvio;
	}
	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}
	public Integer getQuantidadeTentativasEnvio() {
		return quantidadeTentativasEnvio;
	}
	public void setQuantidadeTentativasEnvio(Integer quantidadeTentativasEnvio) {
		this.quantidadeTentativasEnvio = quantidadeTentativasEnvio;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	public Integer getAnoMesReferenciaConta() {
		return anoMesReferenciaConta;
	}
	public void setAnoMesReferenciaConta(Integer anoMesReferenciaConta) {
		this.anoMesReferenciaConta = anoMesReferenciaConta;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public CobrancaAcao getAcaoCobranca() {
		return acaoCobranca;
	}
	public void setAcaoCobranca(CobrancaAcao acaoCobranca) {
		this.acaoCobranca = acaoCobranca;
	}
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	public FaturamentoGrupo getGrupoFaturamento() {
		return grupoFaturamento;
	}
	public void setGrupoFaturamento(FaturamentoGrupo grupoFaturamento) {
		this.grupoFaturamento = grupoFaturamento;
	}
	public ParametrosMSGSMSEmail getParametroMensagemSMSEmail() {
		return parametroMensagemSMSEmail;
	}
	public void setParametroMensagemSMSEmail(
			ParametrosMSGSMSEmail parametroMensagemSMSEmail) {
		this.parametroMensagemSMSEmail = parametroMensagemSMSEmail;
	}
	public CobrancaAcaoAtividadeComando getCobrancaAcaoAtividadeComando() {
		return cobrancaAcaoAtividadeComando;
	}
	public void setCobrancaAcaoAtividadeComando(
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando) {
		this.cobrancaAcaoAtividadeComando = cobrancaAcaoAtividadeComando;
	}
	public CobrancaDocumento getCobrancaDocumento() {
		return cobrancaDocumento;
	}
	public void setCobrancaDocumento(CobrancaDocumento cobrancaDocumento) {
		this.cobrancaDocumento = cobrancaDocumento;
	}
}
