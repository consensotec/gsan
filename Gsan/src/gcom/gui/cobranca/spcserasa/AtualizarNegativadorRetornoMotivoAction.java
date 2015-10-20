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
* Thiago Vieira
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
package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorRetornoMotivo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.spcserasa.FiltroNegativadorRetornoMotivo;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Description of the Class
 * 
 * @author Thiago Vieira
 */
public class AtualizarNegativadorRetornoMotivoAction extends GcomAction {

    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param actionForm
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     * @param httpServletResponse
     *            Description of the Parameter
     * @return Description of the Return Value
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

//    	 localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		HttpSession sessao = httpServletRequest.getSession(false);

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		// Pega o form do cliente
        AtualizarNegativadorRetornoMotivoActionForm form = (AtualizarNegativadorRetornoMotivoActionForm) actionForm; 

        String descricaoRetornoMotivo = "";
        short indicadorRegistroAceito = 0;
        short indicadorUso = 0;
        Long time = Long.parseLong(form.getTime());
        Integer idNegativadorRetornoMotivo = new Integer(form.getIdNegativadorRetornoMotivo());
        Integer idNegativador = new Integer(form.getIdNegativador());
        
		NegativadorRetornoMotivo negativadorRetornoMotivo = new NegativadorRetornoMotivo();
      //------------ REGISTRAR TRANSA��O ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_ATUALIZAR_NEGATIVADOR_RETORNO_MOTIVO,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_CLIENTE_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
	  // ------------ REGISTRAR TRANSA��O ----------------
        
        if (form.getDescricaoRetornoMotivo() != null && !form.getDescricaoRetornoMotivo().equals("")){
        	descricaoRetornoMotivo = form.getDescricaoRetornoMotivo();
        	negativadorRetornoMotivo.setDescricaoRetornocodigo(descricaoRetornoMotivo);
        }
        if (form.getIndicadorRegistroAceito() != null && !form.getIndicadorRegistroAceito().equals("")){
        	indicadorRegistroAceito = Short.parseShort(form.getIndicadorRegistroAceito());
        	negativadorRetornoMotivo.setIndicadorRegistroAceito(indicadorRegistroAceito);
        }
        if (form.getIndicadorUso() != null && !form.getIndicadorUso().equals("")){
        	indicadorUso = Short.parseShort(form.getIndicadorUso());
        	negativadorRetornoMotivo.setIndicadorUso(indicadorUso);
        }
        
//      Check para atualiza��o realizada por outro usu�rio 
		FiltroNegativadorRetornoMotivo filtroNegativadorRetornoMotivo = new FiltroNegativadorRetornoMotivo(); 
		filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimples(FiltroNegativadorRetornoMotivo.ID, idNegativadorRetornoMotivo));
		filtroNegativadorRetornoMotivo.adicionarCaminhoParaCarregamentoEntidade("negativador");
		
		Collection collNegativadorRetornoMotivo = Fachada.getInstancia().pesquisar(filtroNegativadorRetornoMotivo, NegativadorRetornoMotivo.class.getName());
		NegativadorRetornoMotivo negativadorRetornoMotivoAtual = (NegativadorRetornoMotivo)collNegativadorRetornoMotivo.iterator().next();
		
		if (negativadorRetornoMotivoAtual.getUltimaAlteracao().getTime() != time){
			throw new ActionServletException("atencao.registro_remocao_nao_existente");
		}
		
		if (indicadorRegistroAceito == ConstantesSistema.INDICADOR_REGISTRO_ACEITO){
			filtroNegativadorRetornoMotivo.limparListaParametros();
			filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimples(
					FiltroNegativadorRetornoMotivo.INDICADOR_REGISTRO_ACEITO, ConstantesSistema.INDICADOR_REGISTRO_ACEITO));
			filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimples(
					FiltroNegativadorRetornoMotivo.NEGATIVADOR_RETORNO_MOTIVO_NEGATIVADOR, idNegativador));
			filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimplesDiferenteDe(
					FiltroNegativadorRetornoMotivo.CODIGO_RETORNO_MOTIVO, negativadorRetornoMotivoAtual.getCodigoRetornoMotivo()));
			
			collNegativadorRetornoMotivo = Fachada.getInstancia().pesquisar(filtroNegativadorRetornoMotivo, NegativadorRetornoMotivo.class.getName());
			
			if (collNegativadorRetornoMotivo != null && !collNegativadorRetornoMotivo.isEmpty()){
				throw new ActionServletException("atencao.negativador_retorno_motivo_ja_existe_cadastro");
			}
		}
		
	    negativadorRetornoMotivo.setId(idNegativadorRetornoMotivo);
        Negativador negativador = new Negativador();
        negativador.setId(idNegativador);
        negativadorRetornoMotivo.setNegativador(negativador);
        negativadorRetornoMotivo.setCodigoRetornoMotivo(negativadorRetornoMotivoAtual.getCodigoRetornoMotivo());
        
		negativadorRetornoMotivo.setUltimaAlteracao(new Date());
		
		
	     //------------ REGISTRAR TRANSA��O ----------------
		negativadorRetornoMotivo.setOperacaoEfetuada(operacaoEfetuada);
		negativadorRetornoMotivo.adicionarUsuario(usuario, 
        		UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
        registradorOperacao.registrarOperacao(negativadorRetornoMotivo);
        //------------ REGISTRAR TRANSA��O ----------------
		
		
		
		 // atualiza o NegativadorRetornoMotivo
		Fachada.getInstancia().atualizar(negativadorRetornoMotivo);

        montarPaginaSucesso(httpServletRequest, "Motivo de retorno do registro do negativador "
				+ descricaoRetornoMotivo + " atualizado com sucesso.",
				"Realizar outra manuten��o de motivo de retorno do registro do negativador",
				"exibirFiltrarNegativadorRetornoMotivoAction.do?menu=sim",
				"","");
        
		return retorno;
        
    }
}