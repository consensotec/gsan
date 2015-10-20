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
package gcom.seguranca.acesso;

import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;


/**
 * Descri��o da classe 
 *
 * @author Pedro Alexandre
 * @date 08/11/2006
 */
public class Abrangencia implements Serializable {
    
	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private Imovel imovel;
    private Localidade localidade;
    private Localidade eloPolo;
    private UnidadeNegocio unidadeNegocio;
    private GerenciaRegional gerenciaRegional;
    private Municipio municipio;
    private SetorComercial setorComercial;
    private Quadra quadra;
    
    public final static String ABRANGENCIA = "abrangencia";
    
    
    /**
     * Construtor de Abrangencia 
     * 
     * @param usuario
     * @param imovel
     */
    public Abrangencia(Usuario usuario, Imovel imovel) {
        this.usuario = usuario;
        this.imovel = imovel;
    }

    /**
     * Construtor de Abrangencia 
     * 
     * @param usuario
     * @param localidade
     */
    public Abrangencia(Usuario usuario, Localidade localidade) {
        this.usuario = usuario;
        this.localidade = localidade;
    }
    
    /**
     * Construtor de Abrangencia 
     * 
     * @param eloPolo
     * @param usuario
     */
    public Abrangencia(Localidade eloPolo, Usuario usuario) {
        this.usuario = usuario;
        this.eloPolo = eloPolo;
    }
    
    /**
     * Construtor de Abrangencia 
     * 
     * @param usuario
     * @param unidadeNegocio
     */
    public Abrangencia(Usuario usuario, UnidadeNegocio unidadeNegocio) {
        this.usuario = usuario;
        this.unidadeNegocio = unidadeNegocio;
    }
    
    /**
     * Construtor de Abrangencia 
     * 
     * @param usuario
     * @param gerenciaRegional
     */
    public Abrangencia(Usuario usuario, GerenciaRegional gerenciaRegional) {
        this.usuario = usuario;
        this.gerenciaRegional = gerenciaRegional;
    }

    /**
     * Construtor de Abrangencia 
     * 
     * @param usuario
     * @param municipio
     */
    public Abrangencia(Usuario usuario, Municipio municipio) {
        this.usuario = usuario;
        this.municipio = municipio;
    }

    /**
     * Construtor de Abrangencia 
     * 
     * @param usuario
     * @param setorComercial
     */
    public Abrangencia(Usuario usuario, SetorComercial setorComercial) {
        this.usuario = usuario;
        this.setorComercial = setorComercial;
    }
    
    /**
     * Construtor de Abrangencia 
     * 
     * @param usuario
     * @param quadra
     */
    public Abrangencia(Usuario usuario, Quadra quadra) {
        this.usuario = usuario;
        this.quadra = quadra;
    }

    /**
     * <Breve descri��o sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Administrador
     * @date 13/11/2006
     *
     * @return
     */
    public Localidade getEloPolo() {
        return eloPolo;
    }


    /**
     * <Breve descri��o sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Administrador
     * @date 13/11/2006
     *
     * @param eloPolo
     */
    protected void setEloPolo(Localidade eloPolo) {
        this.eloPolo = eloPolo;
    }


    /**
     * <Breve descri��o sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Administrador
     * @date 13/11/2006
     *
     * @return
     */
    public GerenciaRegional getGerenciaRegional() {
        return gerenciaRegional;
    }


    /**
     * <Breve descri��o sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Pedro Alexandre
     * @date 13/11/2006
     *
     * @param gerenciaRegional
     */
    protected void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
        this.gerenciaRegional = gerenciaRegional;
    }


    /**
     * <Breve descri��o sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Pedro Alexandre
     * @date 13/11/2006
     *
     * @return
     */
    public Imovel getImovel() {
        return imovel;
    }


    /**
     * <Breve descri��o sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Pedro Alexandre
     * @date 13/11/2006
     *
     * @param imovel
     */
    protected void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }


    /**
     * <Breve descri��o sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Pedro Alexandre
     * @date 13/11/2006
     *
     * @return
     */
    public Localidade getLocalidade() {
        return localidade;
    }


    /**
     * <Breve descri��o sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Pedro Alexandre
     * @date 13/11/2006
     *
     * @param localidade
     */
    protected void setLocalidade(Localidade localidade) {
        this.localidade = localidade;
    }


    /**
     * <Breve descri��o sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Pedro Alexandre
     * @date 13/11/2006
     *
     * @return
     */
    public UnidadeNegocio getUnidadeNegocio() {
        return unidadeNegocio;
    }


    /**
     * <Breve descri��o sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Pedro Alexandre
     * @date 13/11/2006
     *
     * @param unidadeNegocio
     */
    protected void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
        this.unidadeNegocio = unidadeNegocio;
    }


    /**
     * <Breve descri��o sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Pedro Alexandre
     * @date 13/11/2006
     *
     * @return
     */
    public Usuario getUsuario() {
        return usuario;
    }


    /**
     * <Breve descri��o sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Pedro Alexandre
     * @date 13/11/2006
     *
     * @param usuario
     */
    protected void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * <Breve descri��o sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Administrador
     * @date 14/11/2006
     *
     * @return
     */
    public Municipio getMunicipio() {
        return municipio;
    }

    /**
     * <Breve descri��o sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Administrador
     * @date 14/11/2006
     *
     * @param municipio
     */
    protected void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    /**
     * <Breve descri��o sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Administrador
     * @date 14/11/2006
     *
     * @return
     */
    public Quadra getQuadra() {
        return quadra;
    }

    /**
     * <Breve descri��o sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Administrador
     * @date 14/11/2006
     *
     * @param quadra
     */
    protected void setQuadra(Quadra quadra) {
        this.quadra = quadra;
    }

    /**
     * <Breve descri��o sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Administrador
     * @date 14/11/2006
     *
     * @return
     */
    public SetorComercial getSetorComercial() {
        return setorComercial;
    }

    /**
     * <Breve descri��o sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Administrador
     * @date 14/11/2006
     *
     * @param setorComercial
     */
    protected void setSetorComercial(SetorComercial setorComercial) {
        this.setorComercial = setorComercial;
    }
}    
  
