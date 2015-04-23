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
package gsan.faturamento.bean;

import java.math.BigDecimal;

import gsan.faturamento.debito.DebitoACobrar;
import gsan.util.Util;



/**
 * [CRC:1710] - Botões de imprimir nas abas de Consultar Imovel.<br/><br/>
 * 
 * Classe que servirá para exibir os dados dos Debitos Automáticos
 * no RelatorioDadosComplementaresImovel.
 * OBS: Pode ser utilizada por qualquer outro relatorio tambem de modo
 * que não mude o que já existe.
 *
 * @author Marlon Patrick
 * @since 23/09/2009
 */
public class DebitoACobrarRelatoriosHelper {
	
	public DebitoACobrarRelatoriosHelper() {
	}
	
	public DebitoACobrarRelatoriosHelper(DebitoACobrar c) {
		this.debitoCobrar = c;
	}

	private DebitoACobrar debitoCobrar;

	public DebitoACobrar getDebitoCobrar() {
		return debitoCobrar;
	}

	public void setDebitoCobrar(DebitoACobrar c) {
		this.debitoCobrar = c;
	}	
	
	public String getDescricaoTipoDebito(){
		if(this.debitoCobrar!=null && this.debitoCobrar.getDebitoTipo()!=null){
			return this.debitoCobrar.getDebitoTipo().getDescricao();
		}
		
		return "";
	}

	public String getAnoMesReferenciaDebito(){
		if(this.debitoCobrar!=null && this.debitoCobrar.getAnoMesReferenciaDebito() != null){
			return this.debitoCobrar.getAnoMesReferenciaDebito()!=null 
			? Util.formatarAnoMesParaMesAno( this.debitoCobrar.getAnoMesReferenciaDebito()) : null;
		}
		
		return "";
	}

	public String getAnoMesCobrancaDebito(){
		if(this.debitoCobrar!=null && this.debitoCobrar.getAnoMesCobrancaDebito() != null){
			return this.debitoCobrar.getAnoMesCobrancaDebito() !=null ? 
					Util.formatarAnoMesParaMesAno(this.debitoCobrar.getAnoMesCobrancaDebito()) : null;
		}
		
		return "";
	}

	public Short getNumeroPrestacaoCobradas(){
		if(this.debitoCobrar!=null){
			return this.debitoCobrar.getNumeroPrestacaoCobradas();
		}
		
		return null;
	}

	public Short getNumeroPrestacaoDebito(){
		if(this.debitoCobrar!=null){
			return this.debitoCobrar.getNumeroPrestacaoDebito();
		}
		
		return null;
	}

	public Short getNumeroParcelaBonus(){
		if(this.debitoCobrar!=null){
			return this.debitoCobrar.getNumeroParcelaBonus();
		}
		
		return null;
	}

	public BigDecimal getValorDebito(){
		if(this.debitoCobrar!=null){
			return this.debitoCobrar.getValorDebito();
		}
		
		return null;
	}
	
	public String getDescricaoAbreviadaCreditoSituacaoAtual(){
		if(this.debitoCobrar!=null && this.debitoCobrar.getDebitoCreditoSituacaoAtual()!=null){
			return this.debitoCobrar.getDebitoCreditoSituacaoAtual().getDescricaoAbreviada();
		}
		
		return "";
	}
}

