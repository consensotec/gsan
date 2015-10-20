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
package gcom.faturamento.conta;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.util.Date;

public class ContaEmissao2Via implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private ContaGeral contaGeral;
	private Usuario usuario;
	private Date dataEmissao;
	private Date ultimaAlteracao;
	private Imovel imovel;
	private Cliente cliente;
	private Short indicadorClienteImovel;
	private Short indicadorEmissaoPresencial;
	
	public ContaEmissao2Via(ContaGeral contaGeral, Usuario usuario,
			Date dataEmissao, Imovel imovel, Cliente cliente,
			Short indicadorClienteImovel, Short indicadorEmissaoPresencial) {
		this.contaGeral = contaGeral;
		this.usuario = usuario;
		this.dataEmissao = dataEmissao;	
		this.imovel = imovel;
		this.cliente = cliente;
		this.indicadorClienteImovel = indicadorClienteImovel;
		this.indicadorEmissaoPresencial = indicadorEmissaoPresencial;
		
		this.ultimaAlteracao = new Date();
	}
	
	//Construtor completo
	public ContaEmissao2Via(Integer id, ContaGeral contaGeral, Usuario usuario,
			Date dataEmissao, Imovel imovel, Date ultimaAlteracao) {
		
		this.id = id;
		this.contaGeral = contaGeral;
		this.usuario = usuario;
		this.dataEmissao = dataEmissao;
		this.imovel = imovel;
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	//Construtor minimo
	public ContaEmissao2Via(ContaGeral contaGeral, Usuario usuario,
			Date dataEmissao, Imovel imovel) {
		this.contaGeral = contaGeral;
		this.usuario = usuario;
		this.dataEmissao = dataEmissao;	
		this.imovel = imovel;
		
		this.ultimaAlteracao = new Date();
	}
	
	//Construtor Vazio
	public ContaEmissao2Via() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ContaGeral getContaGeral() {
		return contaGeral;
	}

	public void setContaGeral(ContaGeral contaGeral) {
		this.contaGeral = contaGeral;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContaEmissao2Via other = (ContaEmissao2Via) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Short getIndicadorClienteImovel() {
		return indicadorClienteImovel;
	}

	public void setIndicadorClienteImovel(Short indicadorClienteImovel) {
		this.indicadorClienteImovel = indicadorClienteImovel;
	}

	public Short getIndicadorEmissaoPresencial() {
		return indicadorEmissaoPresencial;
	}

	public void setIndicadorEmissaoPresencial(Short indicadorEmissaoPresencial) {
		this.indicadorEmissaoPresencial = indicadorEmissaoPresencial;
	}
	
}