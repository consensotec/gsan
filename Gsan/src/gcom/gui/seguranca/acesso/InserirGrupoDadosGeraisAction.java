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
package gcom.gui.seguranca.acesso;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroGrupo;
import gcom.seguranca.acesso.Grupo;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;


/**
 * Action respons�vel por validar os dados informados na p�gina de inserir grupo
 * e por criar um grupo com a descri��o e a descri��o abreviada informadas.
 *
 * @author Pedro Alexandre, Mariana Victor
 * @date 15/06/2006, 10/01/2011
 */
public class InserirGrupoDadosGeraisAction extends GcomAction {
	
   
    /**
     * <Breve descri��o sobre o caso de uso>
     *
     * [UC0278] Inserir Grupo
     *
     * @author Pedro Alexandre, Mariana Victor
     * @date 15/06/2006, 10/01/2011
     *
     * @param actionMapping
     * @param actionForm
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {

    	//Seta o mapeamento de retorno para a p�gina de definir acesso do grupo 
        ActionForward retorno = actionMapping.findForward("inserirGrupoAcessosGrupo");
        
        //Recupera o form 
        DynaValidatorForm grupoActionForm = (DynaValidatorForm) actionForm;

        //Cria uma inst�ncia da sess�o 
        HttpSession sessao = httpServletRequest.getSession(false);

        /* Cria o grupo e seta os dados informados 
         * para depois usar esse grupo gerado nas 
         * outras p�ginas.
         */ 
        Grupo grupo = new Grupo();
        grupo.setDescricao((String)grupoActionForm.get("descricao"));
        grupo.setDescricaoAbreviada((String)grupoActionForm.get("descricaoAbreviada"));
        grupo.setIndicadorUso(ConstantesSistema.SIM);
        grupo.setIndicadorSuperintendencia((Short)grupoActionForm.get("indicadorSuperintendencia"));
        
        if(grupoActionForm.get("diasExpiracaoSenha")!=null
        		&& !grupoActionForm.get("diasExpiracaoSenha").toString().equals("")){
        	grupo.setNumDiasExpiracaoSenha(new Integer(grupoActionForm.get("diasExpiracaoSenha").toString()));
        }
        if(grupoActionForm.get("mensagem")!=null
        		&& !grupoActionForm.get("mensagem").equals("")){
        	grupo.setMensagem(grupoActionForm.get("mensagem").toString());
        }
        if(grupoActionForm.get("competenciaRetificacao")!=null
        		&& !grupoActionForm.get("competenciaRetificacao").toString().equals("")
        		&& !grupoActionForm.get("competenciaRetificacao").toString().equals("0")){
        	grupo.setCompetenciaRetificacao(Util.formatarMoedaRealparaBigDecimal(grupoActionForm.get("competenciaRetificacao").toString()));
        }

        
        //[FS0002] - Verificar preenchimento dos campos
        if(grupo.getDescricao() == null){
        	throw new ActionServletException("atencao.naoinformado",null, "Descri��o do Grupo");
        }

        //[FS0002] - Verificar preenchimento dos campos
        if(grupo.getDescricaoAbreviada() == null){
        	throw new ActionServletException("atencao.naoinformado",null, "Descri��o Abreviada do Grupo");
        }
        
        //[FS002] - Verificar preenchimento dos campos
        if (grupo.getIndicadorSuperintendencia() == null){
        	throw new ActionServletException("atencao.naoinformado",null, "Indicador de Superintend�ncia");
        }

        
        //[FS0001] - Verificar exist�ncia da descri��o
        FiltroGrupo filtroGrupo = new FiltroGrupo();
        filtroGrupo.adicionarParametro(new ParametroSimples(FiltroGrupo.DESCRICAO, grupo.getDescricao()));
        Collection colecaoGrupo = Fachada.getInstancia().pesquisar(filtroGrupo, Grupo.class.getSimpleName());
        if (colecaoGrupo != null && !colecaoGrupo.isEmpty()) {
			throw new ActionServletException("atencao.grupo.descricao_ja_existe",null,grupo.getDescricao());
        }
        

        //Coloca na sess�oo grupo gerado com as informa��es informadas
        sessao.setAttribute("grupo",grupo);

        //Retorna o mapemaneto na vari�vel "retorno"
        return retorno;
    }
}