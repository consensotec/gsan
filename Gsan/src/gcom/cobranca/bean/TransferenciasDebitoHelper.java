/**
 * 
 */
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
package gcom.cobranca.bean;

import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.cadastro.imovel.Imovel;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.Date;

public class TransferenciasDebitoHelper {
	
	private static final long serialVersionUID = 1L;
	
	private Conta conta;
	
	private DebitoACobrar debitoACobrar;
	
	private CreditoARealizar creditoARealizar;
	
	private GuiaPagamento guiaPagamento;
	
	private Imovel imovelOrigem;
	
	private Imovel imovelDestino;
	
	private Usuario usuario;
	
	private Date dataTransferencia;
	
	public TransferenciasDebitoHelper() {}
	
	/**
	 * @return Retorna o campo dataTransferencia.
	 */
	public Date getDataTransferencia() {
		return dataTransferencia;
	}

	/**
	 * @param dataTransferencia O dataTransferencia a ser setado.
	 */
	public void setDataTransferencia(Date dataTransferencia) {
		this.dataTransferencia = dataTransferencia;
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

	/**
	 * @return Retorna o campo imovelDestino.
	 */
	public Imovel getImovelDestino() {
		return imovelDestino;
	}

	/**
	 * @param imovelDestino O imovelDestino a ser setado.
	 */
	public void setImovelDestino(Imovel imovelDestino) {
		this.imovelDestino = imovelDestino;
	}

	/**
	 * @return Retorna o campo imovelOrigem.
	 */
	public Imovel getImovelOrigem() {
		return imovelOrigem;
	}

	/**
	 * @param imovelOrigem O imovelOrigem a ser setado.
	 */
	public void setImovelOrigem(Imovel imovelOrigem) {
		this.imovelOrigem = imovelOrigem;
	}

	/**
	 * @return Retorna o campo conta.
	 */
	public Conta getConta() {
		return conta;
	}

	/**
	 * @param conta O conta a ser setado.
	 */
	public void setConta(Conta conta) {
		this.conta = conta;
	}

	/**
	 * @return Retorna o campo creditoARealizar.
	 */
	public CreditoARealizar getCreditoARealizar() {
		return creditoARealizar;
	}

	/**
	 * @param creditoARealizar O creditoARealizar a ser setado.
	 */
	public void setCreditoARealizar(CreditoARealizar creditoARealizar) {
		this.creditoARealizar = creditoARealizar;
	}

	/**
	 * @return Retorna o campo debitoACobrar.
	 */
	public DebitoACobrar getDebitoACobrar() {
		return debitoACobrar;
	}

	/**
	 * @param debitoACobrar O debitoACobrar a ser setado.
	 */
	public void setDebitoACobrar(DebitoACobrar debitoACobrar) {
		this.debitoACobrar = debitoACobrar;
	}

	/**
	 * @return Retorna o campo guiaPagamento.
	 */
	public GuiaPagamento getGuiaPagamento() {
		return guiaPagamento;
	}

	/**
	 * @param guiaPagamento O guiaPagamento a ser setado.
	 */
	public void setGuiaPagamento(GuiaPagamento guiaPagamento) {
		this.guiaPagamento = guiaPagamento;
	}

}
