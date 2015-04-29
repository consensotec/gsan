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
package gsan.gui.arrecadacao;

import gsan.arrecadacao.Devolucao;
import gsan.arrecadacao.FiltroAvisoBancario;
import gsan.arrecadacao.FiltroDevolucao;
import gsan.arrecadacao.aviso.AvisoBancario;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.gui.ManutencaoRegistroActionForm;
import gsan.seguranca.acesso.Operacao;
import gsan.seguranca.acesso.OperacaoEfetuada;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.seguranca.acesso.usuario.UsuarioAcao;
import gsan.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Remove as devolu��es selecionadas na lista da funcionalidade Manter Devolu��o
 * 
 * @author Fernanda Paiva
 * @created 09 de Mar�o de 2006
 */
public class RemoverDevolucaoAction extends GcomAction {
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

        ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
        
        //------------ REGISTRAR TRANSA��O ----------------
        Operacao operacao = new Operacao();
        operacao.setId(Operacao.OPERACAO_DEVOLUCOES_REMOVER);

        OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
        operacaoEfetuada.setOperacao(operacao);
        //------------ REGISTRAR TRANSA��O ----------------

        Fachada fachada = Fachada.getInstancia();
        
        // Obt�m os ids de remo��o
        String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();

        if (ids != null) 
        {
			for (int i = 0; i < ids.length; i++) 
			{
				FiltroDevolucao filtroDevolucao =  new FiltroDevolucao();
				
				filtroDevolucao.adicionarCaminhoParaCarregamentoEntidade("devolucaoSituacaoAtual");
				filtroDevolucao.adicionarCaminhoParaCarregamentoEntidade("devolucaoSituacaoAnterior");
		
				filtroDevolucao.adicionarParametro(new ParametroSimples(
						FiltroDevolucao.ID, ids[i]));
				
				Collection<Devolucao> devolucaoPesquisado = fachada.pesquisar(
						filtroDevolucao, Devolucao.class.getName());
				
				if (devolucaoPesquisado != null && !devolucaoPesquisado.isEmpty()) {
					
					Devolucao dadosDevolucao = (Devolucao) ((List) devolucaoPesquisado).get(0);
					
					//O endere�o foi encontrado
					if ((dadosDevolucao.getDevolucaoSituacaoAtual() != null && !dadosDevolucao.getDevolucaoSituacaoAtual().equals("")) && (dadosDevolucao.getDevolucaoSituacaoAnterior() != null && !dadosDevolucao.getDevolucaoSituacaoAnterior().equals(""))) 
					{
						String situacaoAnterior = dadosDevolucao.getDevolucaoSituacaoAnterior().getDescricaoDevolucaoSituacao();
						String situacaoAtual = dadosDevolucao.getDevolucaoSituacaoAtual().getDescricaoDevolucaoSituacao();
						throw new ActionServletException(
	                    "atencao.devolucao.nao_excluir_situacao_alterada", ""
								+ situacaoAnterior, situacaoAtual);
					}
					
					FiltroAvisoBancario filtroAvisoBancario = new FiltroAvisoBancario();

					filtroAvisoBancario.adicionarParametro(new ParametroSimples(
							FiltroAvisoBancario.ID, dadosDevolucao.getAvisoBancario().getId()));

					Collection avisoBancario = fachada.pesquisar(filtroAvisoBancario,
							AvisoBancario.class.getName());
					
					BigDecimal valorFinal = null;

					if (avisoBancario != null && !avisoBancario.isEmpty()) {

						AvisoBancario dadosAvisoBancario = (AvisoBancario) ((List) avisoBancario).get(0);
						
						BigDecimal valorDevolucao2 = dadosAvisoBancario.getValorDevolucaoCalculado();
						
						valorFinal = (valorDevolucao2.subtract(dadosDevolucao.getValorDevolucao()));
						
					}
					fachada.atualizaValorArrecadacaoAvisoBancaraio(valorFinal, dadosDevolucao.getAvisoBancario().getId());

				}
			}
		}

		//Seta o mapeamento de retorno
        ActionForward retorno = actionMapping.findForward("telaSucesso");

        //Obt�m a sess�o
        //HttpSession sessao = httpServletRequest.getSession(false);
                
        //mensagem de erro quando o usu�rio tenta excluir sem ter selecionado nenhum registro
        if (ids == null || ids.length == 0) {
            throw new ActionServletException(
                    "atencao.registros.nao_selecionados");
        }

        //------------ REGISTRAR TRANSA��O ----------------
    	UsuarioAcaoUsuarioHelper usuarioAcaoUsuarioHelper = new UsuarioAcaoUsuarioHelper(usuarioLogado,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
    	Collection<UsuarioAcaoUsuarioHelper> colecaoUsuarios = new ArrayList();
    	colecaoUsuarios.add(usuarioAcaoUsuarioHelper);
        //------------ REGISTRAR TRANSA��O ----------------
    	
        fachada.remover(ids, Devolucao.class.getName(),operacaoEfetuada, colecaoUsuarios);
        
        //Monta a p�gina de sucesso
        if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
            montarPaginaSucesso(httpServletRequest,
            		ids.length + " Devolu�ao(�es) removida(s) com sucesso.",
                    "Realizar outra Manuten��o de Devolu��o",
                    "exibirFiltrarDevolucaoAction.do?tela=manterDevolucao&menu=sim");
        }

        return retorno;
    }
}