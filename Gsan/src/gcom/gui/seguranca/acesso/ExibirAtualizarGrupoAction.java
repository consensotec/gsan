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
import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;
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
 * Action respons�vel por montar todo o esquema do 
 * processo de inserir grupo de usu�rios.
 *
 * @author Pedro Alexandre
 * @date 26/06/2006
 */
public class ExibirAtualizarGrupoAction extends GcomAction {

    /**
     * <Breve descri��o sobre o caso de uso>
     *
     * [UC0279] - Manter Grupo
     *
     * @author Pedro Alexandre
     * @date 26/06/2006
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

        //Localiza o action no objeto 
        ActionForward retorno = actionMapping.findForward("atualizarGrupoDadosGerais");

        //Obt�m a inst�ncia da sess�o
        HttpSession sessao = httpServletRequest.getSession(false);

        //Cria a vari�vel que vai armazenar o c�digo do grupo
        String idGrupo = null;
        
        //Caso o bot�o de desfazer n�o tenha sido pressionado
        if(httpServletRequest.getParameter("desfazer") == null){
        	//Recupera o c�digo do grupo
	       	if(httpServletRequest.getParameter("idRegistroAtualizacao") != null){
	        	idGrupo = (String) httpServletRequest.getParameter("idRegistroAtualizacao");
	        }else if(httpServletRequest.getAttribute("idRegistroAtualizacao") != null){
	        	idGrupo = (String) httpServletRequest.getAttribute("idRegistroAtualizacao");
	        }
	        
	       		       	
	       	//Monta o status wizard com os actions do processo
	       	StatusWizard statusWizard = new StatusWizard(
	                "atualizarGrupoWizardAction", "atualizarGrupoAction",
	                "cancelarAtualizarGrupoAction","exibirManterGrupoAction",
	                "exibirAtualizarGrupoAction.do", 
	                idGrupo);
	       	
	       	/*if(httpServletRequest.getParameter("sucesso") != null){
		        statusWizard = new StatusWizard(
		                "atualizarGrupoWizardAction", "atualizarGrupoAction",
		                "cancelarAtualizarGrupoAction","exibirFiltrarGrupoAction",
		                "exibirAtualizarGrupoAction.do", 
		                idGrupo);
	       	}else{
		        statusWizard = new StatusWizard(
		                "atualizarGrupoWizardAction", "atualizarGrupoAction",
		                "cancelarAtualizarGrupoAction","exibirManterGrupoAction",
		                "exibirAtualizarGrupoAction.do",
		                idGrupo);	       		
	       	}*/
	       		        	        
	        //Monta a primeira aba do processo de atualizar grupo
	        statusWizard
	                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
	                        1, "DadosGeraisPrimeiraAbaA.gif", "DadosGeraisPrimeiraAbaD.gif",
	                        "exibirAtualizarGrupoDadosGeraisAction",
	                        "atualizarGrupoDadosGeraisAction"));
	        
	        //Monta a segunda aba do processo de atualizar grupo
	        statusWizard
	                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
	                        2, "AcessosGrupoUltimaAbaA.gif", "AcessosGrupoUltimaAbaD.gif",
	                        "exibirAtualizarGrupoAcessosGrupoAction",
	                        "atualizarGrupoAcessosGrupoAction"));
	        

	        //Manda o statusWizard para a sessao
	        sessao.setAttribute("statusWizard", statusWizard);
	        
        }else{
        	//Recupera o status wizard da sess�o para recuperar o c�digo do grupo
        	StatusWizard statusWizardSessao = (StatusWizard)sessao.getAttribute("statusWizard");
        	idGrupo = statusWizardSessao.getId();
        }
        
        //Limpa a sess�o
        sessao.removeAttribute("GrupoActionForm");
        sessao.removeAttribute("grupo");
        sessao.removeAttribute("grupoFuncionalidades");
        
        //Parte da atualiza��o
        DynaValidatorForm atualizarGrupoActionForm = (DynaValidatorForm) actionForm;
        
        //Caso o usu�rio tenha informado o c�digo do grupo que vai ser atualizado
        if (idGrupo != null ) {
        	FiltroGrupo filtroGrupo = new FiltroGrupo();
        	filtroGrupo.adicionarParametro(new ParametroSimples(FiltroGrupo.ID,idGrupo));
        	Collection colecaoGrupo = Fachada.getInstancia().pesquisar(filtroGrupo, Grupo.class.getSimpleName());
        	Grupo grupo = (Grupo) colecaoGrupo.iterator().next();
        	sessao.setAttribute("grupo",grupo);
        }

        //Recupera o grupo da sess�o
        Grupo grupo = (Grupo) sessao.getAttribute("grupo");

        //Caso exista um grupo na sess�o seta todos os dados no grupo no form 
        if (grupo != null) {
        	atualizarGrupoActionForm.set("descricao",grupo.getDescricao());
        	atualizarGrupoActionForm.set("descricaoAbreviada",grupo.getDescricaoAbreviada());
	        if (grupo.getIndicadorUso() != null) {
	        	atualizarGrupoActionForm.set("indicadorUso",grupo.getIndicadorUso().toString());
	        } else {
	        	atualizarGrupoActionForm.set("indicadorUso",ConstantesSistema.SIM.toString());
	        }
        }

        //Atualiza o grupo da sess�o
        grupo.setDescricao(""+atualizarGrupoActionForm.get("descricao"));
        grupo.setDescricaoAbreviada(""+atualizarGrupoActionForm.get("descricaoAbreviada"));
        
        if(grupo.getNumDiasExpiracaoSenha()!=null
        		&& !grupo.getNumDiasExpiracaoSenha().toString().equals("")){
        	atualizarGrupoActionForm.set("diasExpiracaoSenha",grupo.getNumDiasExpiracaoSenha());
        }
        if(grupo.getMensagem()!=null
        		&& !grupo.getMensagem().equals("")){
        	atualizarGrupoActionForm.set("mensagem",grupo.getMensagem());
        }
        
        if(grupo.getIndicadorSuperintendencia() != null){
        	atualizarGrupoActionForm.set("indicadorSuperintendencia",grupo.getIndicadorSuperintendencia());
        }

        if(grupo.getCompetenciaRetificacao()!=null
        		&& !grupo.getCompetenciaRetificacao().toString().equals("")){
        	atualizarGrupoActionForm.set("competenciaRetificacao",Util.formatarBigDecimalParaStringComVirgula(grupo.getCompetenciaRetificacao()));
        }
        
        sessao.setAttribute("grupo",grupo);
        
       
        
        //Retorna o mapeamento contido na vari�vel retorno
        return retorno;
    }
}
