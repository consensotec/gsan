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
package gcom.gui.micromedicao.hidrometro;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.MovimentoHidrometroHelper;
import gcom.micromedicao.hidrometro.FiltroHidrometroLocalArmazenagem;
import gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 */
public class ConfirmarMovimentarHidrometroAction extends GcomAction {
    /**
     * < <Descri��o do m�todo>>
     * 
     * @param actionMapping
     *            Descri��o do par�metro
     * @param actionForm
     *            Descri��o do par�metro
     * @param httpServletRequest
     *            Descri��o do par�metro
     * @param httpServletResponse
     *            Descri��o do par�metro
     * @return Descri��o do retorno
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("telaSucesso");

        //Obt�m o action form
        ConfirmarMovimentarHidrometroActionForm confirmarMovimentarHidrometroActionForm = (ConfirmarMovimentarHidrometroActionForm) actionForm;

        //Obt�m a sess�o
        HttpSession sessao = httpServletRequest.getSession(false);
        
        Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

        Fachada fachada = Fachada.getInstancia();

        //Filtro para obter o local de armazenagem ativo de id informado
        FiltroHidrometroLocalArmazenagem filtroHidrometroLocalArmazenagem = new FiltroHidrometroLocalArmazenagem();

        filtroHidrometroLocalArmazenagem
                .adicionarParametro(new ParametroSimples(
                        FiltroHidrometroLocalArmazenagem.ID, new Integer(
                                confirmarMovimentarHidrometroActionForm
                                        .getIdLocalArmazenagemDestino()),
                        ParametroSimples.CONECTOR_AND));
        filtroHidrometroLocalArmazenagem
                .adicionarParametro(new ParametroSimples(
                        FiltroHidrometroLocalArmazenagem.INDICADOR_USO,
                        ConstantesSistema.INDICADOR_USO_ATIVO));

        //Pesquisa de acordo com os par�metros informados no filtro
        Collection colecaoHidrometroLocalArmazenagem = fachada.pesquisar(
                filtroHidrometroLocalArmazenagem,
                HidrometroLocalArmazenagem.class.getName());

        //Verifica se a pesquisa retornou algum objeto para a cole��o
        if (colecaoHidrometroLocalArmazenagem == null
                || colecaoHidrometroLocalArmazenagem.isEmpty()) {
            throw new ActionServletException(
                    "atencao.pesquisa.hidrometro_local_armazenagem.inexistente");
        }

        // Validando data de Movimenta��o
        Integer dia = new Integer(confirmarMovimentarHidrometroActionForm.getDataMovimentacao().substring(0, 2));
        Integer mes = new Integer(confirmarMovimentarHidrometroActionForm.getDataMovimentacao().substring(3, 5));
        Integer ano = new Integer(confirmarMovimentarHidrometroActionForm.getDataMovimentacao().substring(6, 10));
        
        Calendar dataMovimentacao = new GregorianCalendar();
        dataMovimentacao.set(Calendar.YEAR, ano);
        dataMovimentacao.set(Calendar.MONTH, mes);
        dataMovimentacao.set(Calendar.DATE, dia);

        Calendar dataLimite = new GregorianCalendar();
        dataLimite.add(Calendar.DATE, -720);
        
		// caso a data de movimenta��o seja menor que 720 dias antes da data atual
		if (dataMovimentacao.before(dataLimite)) {
			throw new ActionServletException("atencao.data.movimentacao.nao.inferior.data.limite");
		}

		String numeroHidrometrosSelecionados = (String) sessao
                .getAttribute("numeroHidrometrosSelecionados");

        Collection colecaoHidrometroSelecionado = (Collection) sessao
                .getAttribute("colecaoHidrometroSelecionado");

        //Verifica se a colecao vai para batch ou n�o.
        if ( !colecaoHidrometroSelecionado.equals("") && colecaoHidrometroSelecionado.size() < 500 ) {

        	fachada.inserirAtualizarMovimentacaoHidrometroIds(
	                colecaoHidrometroSelecionado,
	                confirmarMovimentarHidrometroActionForm.getDataMovimentacao(),
	                confirmarMovimentarHidrometroActionForm.getHoraMovimentacao(),
	                confirmarMovimentarHidrometroActionForm
	                        .getIdLocalArmazenagemDestino(),
	                confirmarMovimentarHidrometroActionForm
	                        .getIdMotivoMovimentacao(),
	                confirmarMovimentarHidrometroActionForm.getParecer(), usuario);
        } else {
        	
        	MovimentoHidrometroHelper helper = new MovimentoHidrometroHelper();
        	helper.setColecaoHidrometroSelecionado( colecaoHidrometroSelecionado );
            helper.setDataMovimentacao( confirmarMovimentarHidrometroActionForm.getDataMovimentacao() );
            helper.setHoraMovimentacao( confirmarMovimentarHidrometroActionForm.getHoraMovimentacao() );
            helper.setIdLocalArmazenagemDestino( confirmarMovimentarHidrometroActionForm
	                        .getIdLocalArmazenagemDestino() );
            helper.setIdMotivoMovimentacao(confirmarMovimentarHidrometroActionForm.getIdMotivoMovimentacao() );
            helper.setUsuario(usuario);
            
        	fachada.inserirAtualizarMovimentacaoHidrometroIdsBatch( helper );
        }
        
        if ( !colecaoHidrometroSelecionado.equals("") && colecaoHidrometroSelecionado.size() < 500 ) {
        	//M�todo utilizado para montar a p�gina de sucesso
            montarPaginaSucesso(httpServletRequest,
            		numeroHidrometrosSelecionados + " Hidr�metro(s) " 
                            + " movimentado(s) com sucesso.",
                    "Movimentar outro(s) Hidr�metro(s)",
                    "exibirFiltrarHidrometroAction.do?menu=sim&tela=movimentarHidrometro");
            
        } else {
        	//M�todo utilizado para montar a p�gina de sucesso batch
        	montarPaginaSucesso(httpServletRequest,
        		"Movimenta��o de Hidr�metros enviado para Processamento",
        		"Voltar",
                "exibirFiltrarHidrometroAction.do?menu=sim&tela=movimentarHidrometro");
        }
        
        //Remove objetos da sess�o
        sessao.removeAttribute("colecaoHidrometroSelecionado");
        sessao.removeAttribute("codigoDescricaoLocalArmazenagemAtual");
        sessao.removeAttribute("numeroHidrometrosSelecionados");
        sessao.removeAttribute("ManutencaoRegistroActionForm");
        sessao.removeAttribute("ConfirmarMovimentarHidrometroActionForm");

        return retorno;
    }
}
