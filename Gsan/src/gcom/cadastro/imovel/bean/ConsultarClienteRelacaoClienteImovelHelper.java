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
package gcom.cadastro.imovel.bean;

import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.util.Util;

import java.util.Collection;

/**
 * Esta classe tem a finalidade para facilitar a visualiza��o dos dados na tela
 * [UC0472] Consultar Imovel
 * 
 * @author S�vio Luiz
 * @date 22/08/2007
 */
public class ConsultarClienteRelacaoClienteImovelHelper {

	private ClienteImovel clienteImovel;

	private String nomeClienteUsuario;

	private String enderecoImovel;

	public ClienteImovel getClienteImovel() {
		return clienteImovel;
	}

	public void setClienteImovel(ClienteImovel clienteImovel) {
		this.clienteImovel = clienteImovel;
	}

	public String getEnderecoImovel() {
		return enderecoImovel;
	}

	public void setEnderecoImovel(String enderecoImovel) {
		this.enderecoImovel = enderecoImovel;
	}

	public String getNomeClienteUsuario() {
		return nomeClienteUsuario;
	}

	public void setNomeClienteUsuario(String nomeClienteUsuario) {
		this.nomeClienteUsuario = nomeClienteUsuario;
	}

	public String getMatriculaFormatadaImovel() {
		if(this.clienteImovel!=null){
			return this.clienteImovel.getImovel().getMatriculaFormatada();
		}
		return "";
	}

	public String getInscricaoFormatadaImovel() {
		if(this.clienteImovel!=null){
			return this.clienteImovel.getImovel().getInscricaoFormatada();
		}
		return "";
	}

	public String getDescricaoTipoRelacao() {
		if(this.clienteImovel!=null
				&& this.clienteImovel.getClienteRelacaoTipo()!=null){
			
			return this.clienteImovel.getClienteRelacaoTipo().getDescricao();
		}
		return "";
	}

	public String getDataInicioRelacao() {
		if(this.clienteImovel!=null
				&& this.clienteImovel.getDataInicioRelacao()!=null){
			
			return Util.formatarData(this.clienteImovel.getDataInicioRelacao());
		}
		return "";
	}

	public String getDataFimRelacao(){
		if(this.clienteImovel!=null
				&& this.clienteImovel.getDataFimRelacao()!=null){
			
			return Util.formatarData(this.clienteImovel.getDataFimRelacao());
		}
		return "";
	}
	
	public String getDescricaoMotivoFimRelacao(){
		if(this.clienteImovel!=null 
				&& this.clienteImovel.getClienteImovelFimRelacaoMotivo()!=null){
			
			return this.clienteImovel.getClienteImovelFimRelacaoMotivo().getDescricao();
		}
		return "";
	}

	public Integer getIdCliente(){
		if(this.clienteImovel!=null 
				&& this.clienteImovel.getCliente()!=null){
			
			return this.clienteImovel.getCliente().getId();
		}
		
		return null;
	}

	public String getNomeCliente(){
		if(this.clienteImovel!=null 
				&& this.clienteImovel.getCliente()!=null){
			
			return this.clienteImovel.getCliente().getNome();
		}
		
		return "";
	}

	public String getTelefonePadraoCliente(){
		if(this.clienteImovel!=null 
				&& this.clienteImovel.getCliente()!=null){
			
			Collection<ClienteFone> colecaoFones = this.clienteImovel.getCliente().getClienteFones();
			if( !Util.isVazioOrNulo(colecaoFones) ){
				for(ClienteFone fone : colecaoFones){
					if(fone.getIndicadorTelefonePadrao()!=null && fone.getIndicadorTelefonePadrao() == 1){
						return fone.getDddTelefone();
					}
				}
			}
		}
		
		return "";
	}

	public String getCpfCnpjCliente(){
		if(this.clienteImovel!=null 
				&& this.clienteImovel.getCliente()!=null){
			
			if( Util.verificarNaoVazio(this.clienteImovel.getCliente().getCnpj())){
				return this.clienteImovel.getCliente().getCnpjFormatado();
			}
			
			return this.clienteImovel.getCliente().getCpfFormatado();
		}
		
		return "";
	}

}
