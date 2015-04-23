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
* Anderson Italo Felinto de Lima
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
package gsan.gui.cobranca;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Action form responsável pelas propiedades do caso de uso de 
 * gerar arquivo texto de faturas agrupadas.
 * 
 * @author Amélia Pessoa
 * @date 12/06/2012
 */
public class ExibirGerarArquivoFaturasAgrupadasActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	private String mesAnoInicial;
	private String mesAnoFinal;
	private String clienteResponsavelId;
	private String clienteResponsavelNome;
	private String clienteResponsavelInicialId;
	private String clienteResponsavelInicialNome;
	private String clienteResponsavelFinalId;
	private String clienteResponsavelFinalNome;
	
	public String getMesAnoInicial() {
		return mesAnoInicial;
	}
	public void setMesAnoInicial(String mesAnoInicial) {
		this.mesAnoInicial = mesAnoInicial;
	}
	public String getMesAnoFinal() {
		return mesAnoFinal;
	}
	public void setMesAnoFinal(String mesAnoFinal) {
		this.mesAnoFinal = mesAnoFinal;
	}
	public String getClienteResponsavelId() {
		return clienteResponsavelId;
	}
	public void setClienteResponsavelId(String clienteResponsavelId) {
		this.clienteResponsavelId = clienteResponsavelId;
	}
	public String getClienteResponsavelNome() {
		return clienteResponsavelNome;
	}
	public void setClienteResponsavelNome(String clienteResponsavelNome) {
		this.clienteResponsavelNome = clienteResponsavelNome;
	}
	public String getClienteResponsavelInicialId() {
		return clienteResponsavelInicialId;
	}
	public void setClienteResponsavelInicialId(String clienteResponsavelInicialId) {
		this.clienteResponsavelInicialId = clienteResponsavelInicialId;
	}
	public String getClienteResponsavelInicialNome() {
		return clienteResponsavelInicialNome;
	}
	public void setClienteResponsavelInicialNome(
			String clienteResponsavelInicialNome) {
		this.clienteResponsavelInicialNome = clienteResponsavelInicialNome;
	}
	public String getClienteResponsavelFinalId() {
		return clienteResponsavelFinalId;
	}
	public void setClienteResponsavelFinalId(String clienteResponsavelFinalId) {
		this.clienteResponsavelFinalId = clienteResponsavelFinalId;
	}
	public String getClienteResponsavelFinalNome() {
		return clienteResponsavelFinalNome;
	}
	public void setClienteResponsavelFinalNome(String clienteResponsavelFinalNome) {
		this.clienteResponsavelFinalNome = clienteResponsavelFinalNome;
	}
	
}