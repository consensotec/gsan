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
package gcom.mobile.execucaoordemservico;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.cliente.OrgaoExpedidorRg;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.Util;
import gcom.util.filtro.Filtro;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ExecucaoOSCliente extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
    private ArquivoTextoOSCobranca arquivoTextoOSCobranca;
    private OrdemServico ordemServico;
    private String nomeCliente;
    private String cpf;
    private String cnpj;
    private String rg;
    private String dddTelefone;
    private String telefone;
    private String ramalTelefone;
    private Date ultimaAlteracao;
    private OrgaoExpedidorRg orgaoExpedidorRg;
    private UnidadeFederacao unidadeFederacao;
    
    /** full constructor */
    public ExecucaoOSCliente(ArquivoTextoOSCobranca arquivoTextoOSCobranca, OrdemServico ordemServico, String nomeCliente, String cpf, String cnpj, String rg, String dddTelefone, String telefone, String ramalTelefone, Date ultimaAlteracao, OrgaoExpedidorRg orgaoExpedidorRg, UnidadeFederacao unidadeFederacao) {
        this.arquivoTextoOSCobranca = arquivoTextoOSCobranca;
        this.ordemServico = ordemServico;
    	this.nomeCliente = nomeCliente;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.rg = rg;
        this.dddTelefone = dddTelefone;
        this.telefone = telefone;
        this.ramalTelefone = ramalTelefone;
        this.ultimaAlteracao = ultimaAlteracao;
        this.orgaoExpedidorRg = orgaoExpedidorRg;
        this.unidadeFederacao = unidadeFederacao;
    }
    
    public ExecucaoOSCliente( Integer idArquivoTexto, String linha ) {

    	Object[] colunas = Util.split( linha ).toArray();
        
        Integer idOs = Integer.parseInt( colunas[1]+"" );
        
        ArquivoTextoOSCobranca atoc = new ArquivoTextoOSCobranca();
        atoc.setId( idArquivoTexto );
        this.arquivoTextoOSCobranca = atoc;
        
        OrdemServico os = new OrdemServico();
        os.setId( idOs );
        this.ordemServico = os;
        
        this.setNomeCliente( !colunas[2].equals("") ? colunas[2]+"" : null );
        this.setCpf( !colunas[3].equals("") ? colunas[3]+"" : null );
        this.setCnpj( !colunas[4].equals("") ? colunas[4]+"" : null );
        this.setRg( !colunas[5].equals("")? colunas[5]+"" : null );
        
        OrgaoExpedidorRg oerg = new OrgaoExpedidorRg();
        oerg.setId( !colunas[6].equals("") ? new Integer( colunas[6]+"" ): null );
        this.setOrgaoExpedidorRg( oerg );
        
        UnidadeFederacao uf = new UnidadeFederacao();
        uf.setId( !colunas[7].equals("") ? new Integer( colunas[7]+"" ): null );
        this.setUnidadeFederacao(uf);
        
        this.setDddTelefone( !colunas[8].equals("") ? colunas[8]+"" : null );
        this.setTelefone( !colunas[9].equals("") ? colunas[9]+"" : null );
        this.setRamalTelefone( !colunas[10].equals("") ? colunas[10]+"" : null );
        
        this.setUltimaAlteracao( new Date() );
    }
    
    /** default constructor */
    public ExecucaoOSCliente() {
    }

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getDddTelefone() {
		return dddTelefone;
	}

	public void setDddTelefone(String dddTelefone) {
		this.dddTelefone = dddTelefone;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getRamalTelefone() {
		return ramalTelefone;
	}

	public void setRamalTelefone(String ramalTelefone) {
		this.ramalTelefone = ramalTelefone;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public OrgaoExpedidorRg getOrgaoExpedidorRg() {
		return orgaoExpedidorRg;
	}

	public void setOrgaoExpedidorRg(OrgaoExpedidorRg orgaoExpedidorRg) {
		this.orgaoExpedidorRg = orgaoExpedidorRg;
	}

	public UnidadeFederacao getUnidadeFederacao() {
		return unidadeFederacao;
	}

	public void setUnidadeFederacao(UnidadeFederacao unidadeFederacao) {
		this.unidadeFederacao = unidadeFederacao;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}
    
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		return null;
	}
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ArquivoTextoOSCobranca getArquivoTextoOSCobranca() {
		return arquivoTextoOSCobranca;
	}

	public void setArquivoTextoOSCobranca(ArquivoTextoOSCobranca arquivoTextoOSCobranca) {
		this.arquivoTextoOSCobranca = arquivoTextoOSCobranca;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}
}
