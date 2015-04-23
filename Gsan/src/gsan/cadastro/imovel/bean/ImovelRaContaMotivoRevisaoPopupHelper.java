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
package gsan.cadastro.imovel.bean;

import java.util.Collection;

public class ImovelRaContaMotivoRevisaoPopupHelper {

	//dados especificacao
	private String idEspecificacao;
	private String nomeEspecificacao;
	private String qtdTotalEspecificacao;
	
	private Collection<ImovelRaPopupHelper> imovelRaPopupHelper;
	private String countRATotal;
	//Dados conta motivo revisao
	private String amReferenciaConta;
	private String dataVencimento;
	private String descricaoMotivo;
	private String valorConta;
	private String totalConta;
	
	private String valorContaTotal;
	private String countContaTotal;
	
	
	

	public String getAmReferenciaConta() {
		return amReferenciaConta;
	}
	public void setAmReferenciaConta(String amReferenciaConta) {
		this.amReferenciaConta = amReferenciaConta;
	}
	public String getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public String getDescricaoMotivo() {
		return descricaoMotivo;
	}
	public void setDescricaoMotivo(String descricaoMotivo) {
		this.descricaoMotivo = descricaoMotivo;
	}
	public String getValorConta() {
		return valorConta;
	}
	public void setValorConta(String valorConta) {
		this.valorConta = valorConta;
	}
	public String getTotalConta() {
		return totalConta;
	}
	public void setTotalConta(String totalConta) {
		this.totalConta = totalConta;
	}
	public String getValorContaTotal() {
		return valorContaTotal;
	}
	public void setValorContaTotal(String valorContaTotal) {
		this.valorContaTotal = valorContaTotal;
	}
	public String getCountContaTotal() {
		return countContaTotal;
	}
	public void setCountContaTotal(String countContaTotal) {
		this.countContaTotal = countContaTotal;
	}
	public String getIdEspecificacao() {
		return idEspecificacao;
	}
	public void setIdEspecificacao(String idEspecificacao) {
		this.idEspecificacao = idEspecificacao;
	}
	public String getNomeEspecificacao() {
		return nomeEspecificacao;
	}
	public void setNomeEspecificacao(String nomeEspecificacao) {
		this.nomeEspecificacao = nomeEspecificacao;
	}
	public Collection<ImovelRaPopupHelper> getImovelRaPopupHelper() {
		return imovelRaPopupHelper;
	}
	public void setImovelRaPopupHelper(Collection<ImovelRaPopupHelper> imovelRaPopupHelper) {
		this.imovelRaPopupHelper = imovelRaPopupHelper;
	}
	public String getCountRATotal() {
		return countRATotal;
	}
	public void setCountRATotal(String countRATotal) {
		this.countRATotal = countRATotal;
	}
	public String getQtdTotalEspecificacao() {
		return qtdTotalEspecificacao;
	}
	public void setQtdTotalEspecificacao(String qtdTotalEspecificacao) {
		this.qtdTotalEspecificacao = qtdTotalEspecificacao;
	}
	
	
}
