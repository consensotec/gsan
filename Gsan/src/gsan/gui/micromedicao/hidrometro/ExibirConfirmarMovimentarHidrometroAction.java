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
package gsan.gui.micromedicao.hidrometro;

import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.micromedicao.hidrometro.FiltroHidrometroLocalArmazenagem;
import gsan.micromedicao.hidrometro.FiltroHidrometroMotivoMovimentacao;
import gsan.micromedicao.hidrometro.Hidrometro;
import gsan.micromedicao.hidrometro.HidrometroLocalArmazenagem;
import gsan.micromedicao.hidrometro.HidrometroMotivoMovimentacao;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
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
public class ExibirConfirmarMovimentarHidrometroAction extends GcomAction {
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

        //Obt�m o action form
        ConfirmarMovimentarHidrometroActionForm confirmarMovimentarHidrometroActionForm = (ConfirmarMovimentarHidrometroActionForm) actionForm;

        //Define a��o de retorno
        ActionForward retorno = actionMapping
                .findForward("confirmarMovimentarHidrometro");

        //Obt�m a sess�o
        HttpSession sessao = httpServletRequest.getSession(false);

        //Obt�m a facahda
        Fachada fachada = Fachada.getInstancia();

        Collection colecaoHidrometroSelecionado = (Collection) sessao
                .getAttribute("colecaoHidrometroSelecionado");
        
        httpServletRequest.setAttribute("qtdeHidrometrosMovimentados",colecaoHidrometroSelecionado.size());

        //Obt�m a descri��o do local de armazenagem
        Hidrometro hidrometro = (Hidrometro) Util
                .retonarObjetoDeColecao(colecaoHidrometroSelecionado);
       
        String codigoDescricaoLocalArmazenagemAtual = null;
        if(hidrometro.getHidrometroLocalArmazenagem() != null){
    	   codigoDescricaoLocalArmazenagemAtual = hidrometro
    			   .getHidrometroLocalArmazenagem().getId().toString();
    	   codigoDescricaoLocalArmazenagemAtual = codigoDescricaoLocalArmazenagemAtual
    			   + " - "
    			   + hidrometro.getHidrometroLocalArmazenagem().getDescricao();
       }


        //Obt�m o objetoCosulta vindo na sess�o
        String objetoConsulta = (String) httpServletRequest
                .getParameter("objetoConsulta");
        
        httpServletRequest.setAttribute("nomeCampo", "idLocalArmazenagemDestino");
        
        //Verifica se o objeto � diferente de nulo
        if (objetoConsulta != null
                && !objetoConsulta.trim().equalsIgnoreCase("")
                && (Integer.parseInt(objetoConsulta)) == 1) {

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
            if (colecaoHidrometroLocalArmazenagem != null
                    && !colecaoHidrometroLocalArmazenagem.isEmpty()) {

                //Obt�m o objeto da cole��o pesquisada
                HidrometroLocalArmazenagem hidrometroLocalArmazenagem = (HidrometroLocalArmazenagem) Util
                        .retonarObjetoDeColecao(colecaoHidrometroLocalArmazenagem);

                //Exibe o c�digo e a descri��o pesquisa na p�gina
                httpServletRequest.setAttribute("corLocalArmazenagem", "valor");
                confirmarMovimentarHidrometroActionForm
                        .setIdLocalArmazenagemDestino(hidrometroLocalArmazenagem
                                .getId().toString());
                confirmarMovimentarHidrometroActionForm
                        .setLocalArmazenagemDescricaoDestino(hidrometroLocalArmazenagem
                                .getDescricao());
                httpServletRequest.setAttribute("nomeCampo", "dataMovimentacao");

            } else {

                //Exibe mensagem de c�digo inexiste e limpa o campo de c�digo
                httpServletRequest.setAttribute("corLocalArmazenagem",
                        "exception");
                confirmarMovimentarHidrometroActionForm
                        .setIdLocalArmazenagemDestino("");
                confirmarMovimentarHidrometroActionForm
                        .setLocalArmazenagemDescricaoDestino("LOCAL DE ARMAZENAGEM INEXISTENTE");
            }

        }
        //Cria��o e defini��o do filto de hidr�metro motivo da movimenta��o
        FiltroHidrometroMotivoMovimentacao filtroHidrometroMotivoMovimentacao = new FiltroHidrometroMotivoMovimentacao();
        
        filtroHidrometroMotivoMovimentacao
                .adicionarParametro(new ParametroSimples(
                        FiltroHidrometroMotivoMovimentacao.INDICADOR_USO,
                        ConstantesSistema.INDICADOR_USO_ATIVO));
        filtroHidrometroMotivoMovimentacao
                .setCampoOrderBy(FiltroHidrometroMotivoMovimentacao.DESCRICAO);

        //Obt�m os motivos da movimenta��o
        Collection colecaoHidrometroMotivoMovimentacao = fachada.pesquisar(
                filtroHidrometroMotivoMovimentacao,
                HidrometroMotivoMovimentacao.class.getName());

        //Envia objeto no request
        httpServletRequest.setAttribute("colecaoHidrometroMotivoMovimentacao",
                colecaoHidrometroMotivoMovimentacao);
        //Envia objeto pela sess�o
        sessao.setAttribute("codigoDescricaoLocalArmazenagemAtual",
                codigoDescricaoLocalArmazenagemAtual);
        
        //Data Corrente
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        Calendar dataCorrente = new GregorianCalendar();
        
        httpServletRequest.setAttribute("dataMovimentacao", formatoData
        		.format(dataCorrente.getTime()));
        httpServletRequest.setAttribute("dataAtual", formatoData
        		.format(dataCorrente.getTime()));

        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
        Calendar horaCorrente = new GregorianCalendar();
        
        httpServletRequest.setAttribute("horaMovimentacao", formatoHora
        		.format(horaCorrente.getTime()));
        return retorno;
    }
}