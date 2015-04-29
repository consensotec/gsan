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
package gsan.micromedicao;

import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.cliente.FiltroClienteTipo;
import gsan.cadastro.empresa.Empresa;
import gsan.cadastro.funcionario.Funcionario;
import gsan.cadastro.unidade.UnidadeOrganizacional;
import gsan.interceptor.ObjetoTransacao;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.filtro.Filtro;
import gsan.util.filtro.ParametroSimples;

import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Leiturista extends ObjetoTransacao{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String codigoDDD;

    /** nullable persistent field */
    private String numeroFone;

    /** nullable persistent field */
    private Short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Cliente cliente;

    /** persistent field */
    private Funcionario funcionario;

    /** persistent field */
    private Set roteiroEmpresas;
    
    /** persistent field */
    private Empresa empresa;
    
    /** persistent field */
    private Long numeroImei;
    
    /** persistent field */
    private Usuario usuario;    

    private Short indicadorAgenteComercial;
    
    private UnidadeOrganizacional unidadeOrganizacional;
    
    private Short indicadorAtualizacaoCadastral;
    
    /** full constructor */
    public Leiturista(Integer id, String codigoDDD, String numeroFone, Short indicadorUso, Date ultimaAlteracao, Cliente cliente, Funcionario funcionario, Set roteiroEmpresas, Usuario usuario, Short indicadorAgenteComercial) {
        this.id = id;
        this.codigoDDD = codigoDDD;
        this.numeroFone = numeroFone;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.roteiroEmpresas = roteiroEmpresas;
        this.usuario = usuario;
        this.indicadorAgenteComercial = indicadorAgenteComercial;
    }

    /** default constructor */
    public Leiturista() {
    }

    /** minimal constructor */
    public Leiturista(Integer id, Date ultimaAlteracao, Cliente cliente, Funcionario funcionario, Set roteiroEmpresas, Usuario usuario, Short indicadorAgenteComercial) {
        this.id = id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.roteiroEmpresas = roteiroEmpresas;
        this.usuario = usuario;
        this.indicadorAgenteComercial = indicadorAgenteComercial;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getNumeroFone() {
        return this.numeroFone;
    }

    public void setNumeroFone(String numeroFone) {
        this.numeroFone = numeroFone;
    }

    public Short getIndicadorUso() {
        return this.indicadorUso;
    }

    public void setIndicadorUso(Short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return this.funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Set getRoteiroEmpresas() {
        return this.roteiroEmpresas;
    }

    public void setRoteiroEmpresas(Set roteiroEmpresas) {
        this.roteiroEmpresas = roteiroEmpresas;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getCodigoDDD() {
		return codigoDDD;
	}

	public void setCodigoDDD(String codigoDDD) {
		this.codigoDDD = codigoDDD;
	}
	
    /**
     * < <Descri��o do m�todo>>
     * 
     * @return Descri��o do retorno
     */
        
    public String[] retornaCamposChavePrimaria() {
    	String[] retorno = {"id"};
    	return retorno;
    }
        
    public Filtro retornaFiltro(){
       	FiltroClienteTipo filtroClienteTipo = new FiltroClienteTipo();
       	filtroClienteTipo.adicionarParametro(new ParametroSimples(FiltroClienteTipo.ID,this.getId()));
       	filtroClienteTipo.adicionarCaminhoParaCarregamentoEntidade("esferaPoder");
       	return filtroClienteTipo;
    }

	public Long getNumeroImei() {
		return numeroImei;
	}

	public void setNumeroImei(Long numeroImei) {
		this.numeroImei = numeroImei;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Short getIndicadorAgenteComercial() {
		return indicadorAgenteComercial;
	}

	public void setIndicadorAgenteComercial(Short indicadorAgenteComercial) {
		this.indicadorAgenteComercial = indicadorAgenteComercial;
	}

	public UnidadeOrganizacional getUnidadeOrganizacional() {
		return unidadeOrganizacional;
	}

	public void setUnidadeOrganizacional(UnidadeOrganizacional unidadeOrganizacional) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}

	public Short getIndicadorAtualizacaoCadastral() {
		return indicadorAtualizacaoCadastral;
	}

	public void setIndicadorAtualizacaoCadastral(Short indicadorAtualizacaoCadastral) {
		this.indicadorAtualizacaoCadastral = indicadorAtualizacaoCadastral;
	}
}