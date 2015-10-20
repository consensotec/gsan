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
package gcom.atendimentopublico.ordemservico;

import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class FiscalizacaoColetiva implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private Date dataGeracao;

    /** nullable persistent field */
    private String comentario;

    /** nullable persistent field */
    private Date dataEncerramento;

    /** persistent field */
    private Date ultimaAlteracao;
    
    /** persistent field */
    private String nomeFiscalizacaoColetiva;
    
    /** persistent field */
    private UnidadeOrganizacional unidadeOrganizacionalAbertura;
    
    /** persistent field */
    private UnidadeOrganizacional unidadeOrganizacionalEncerramento;
    
    /** persistent field */
    private Usuario UsuarioDaAbertura;
    
    /** persistent field */
    private Usuario UsuarioDeEncerramento;

    /** full constructor */
    public FiscalizacaoColetiva(Date dataGeracao, String comentario, Date dataEncerramento, Date ultimaAlteracao, String nomeFiscalizacaoColetiva, 
    		UnidadeOrganizacional unidadeOrganizacionalAbertura, UnidadeOrganizacional unidadeOrganizacionalEncerramento, Usuario UsuarioDaAbertura, 
    		Usuario UsuarioDeEncerramento) {
        this.dataGeracao = dataGeracao;
        this.comentario = comentario;
        this.dataEncerramento = dataEncerramento;
        this.ultimaAlteracao = ultimaAlteracao;
        this.nomeFiscalizacaoColetiva = nomeFiscalizacaoColetiva;
        this.unidadeOrganizacionalAbertura = unidadeOrganizacionalAbertura;
        this.unidadeOrganizacionalEncerramento = unidadeOrganizacionalEncerramento;
        this.UsuarioDaAbertura = UsuarioDaAbertura;
        this.UsuarioDeEncerramento = UsuarioDeEncerramento;
    }

    /** default constructor */
    public FiscalizacaoColetiva() {
    }

    /** minimal constructor */
    public FiscalizacaoColetiva(Date dataGeracao, Date ultimaAlteracao) {
        this.dataGeracao = dataGeracao;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataGeracao() {
        return this.dataGeracao;
    }

    public void setDataGeracao(Date dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public String getComentario() {
        return this.comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getDataEncerramento() {
        return this.dataEncerramento;
    }

    public void setDataEncerramento(Date dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	/**
	 * @return Retorna o campo nomeFiscalizacaoColetiva.
	 */
	public String getNomeFiscalizacaoColetiva() {
		return nomeFiscalizacaoColetiva;
	}

	/**
	 * @param nomeFiscalizacaoColetiva O nomeFiscalizacaoColetiva a ser setado.
	 */
	public void setNomeFiscalizacaoColetiva(String nomeFiscalizacaoColetiva) {
		this.nomeFiscalizacaoColetiva = nomeFiscalizacaoColetiva;
	}

	/**
	 * @return Retorna o campo unidadeOrganizacionalAbertura.
	 */
	public UnidadeOrganizacional getUnidadeOrganizacionalAbertura() {
		return unidadeOrganizacionalAbertura;
	}

	/**
	 * @param unidadeOrganizacionalAbertura O unidadeOrganizacionalAbertura a ser setado.
	 */
	public void setUnidadeOrganizacionalAbertura(
			UnidadeOrganizacional unidadeOrganizacionalAbertura) {
		this.unidadeOrganizacionalAbertura = unidadeOrganizacionalAbertura;
	}

	/**
	 * @return Retorna o campo unidadeOrganizacionalEncerramento.
	 */
	public UnidadeOrganizacional getUnidadeOrganizacionalEncerramento() {
		return unidadeOrganizacionalEncerramento;
	}

	/**
	 * @param unidadeOrganizacionalEncerramento O unidadeOrganizacionalEncerramento a ser setado.
	 */
	public void setUnidadeOrganizacionalEncerramento(
			UnidadeOrganizacional unidadeOrganizacionalEncerramento) {
		this.unidadeOrganizacionalEncerramento = unidadeOrganizacionalEncerramento;
	}

	/**
	 * @return Retorna o campo usuarioDaAbertura.
	 */
	public Usuario getUsuarioDaAbertura() {
		return UsuarioDaAbertura;
	}

	/**
	 * @param usuarioDaAbertura O usuarioDaAbertura a ser setado.
	 */
	public void setUsuarioDaAbertura(Usuario usuarioDaAbertura) {
		UsuarioDaAbertura = usuarioDaAbertura;
	}

	/**
	 * @return Retorna o campo usuarioDeEncerramento.
	 */
	public Usuario getUsuarioDeEncerramento() {
		return UsuarioDeEncerramento;
	}

	/**
	 * @param usuarioDeEncerramento O usuarioDeEncerramento a ser setado.
	 */
	public void setUsuarioDeEncerramento(Usuario usuarioDeEncerramento) {
		UsuarioDeEncerramento = usuarioDeEncerramento;
	}

}
