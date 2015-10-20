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
package gcom.gui.util.tabelaauxiliar.tipo;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.tabelaauxiliar.TabelaAuxiliar;
import gcom.util.tabelaauxiliar.tipo.FiltroTabelaAuxiliarTipo;

import java.util.Collection;

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
public class ExibirManterTabelaAuxiliarTipoAction extends GcomAction {
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

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("manterTabelaAuxiliarTipo");

        //Obt�m a instancia da fachada
        Fachada fachada = Fachada.getInstancia();

        //Cria a cole��o de tabelas auxiliares
        Collection tabelasAuxiliaresTipos = null;

        //Obt�m a inst�ncia da sess�o
        HttpSession sessao = httpServletRequest.getSession(false);

        //Obt�m o nome da tela passado no get do request
        String tela = httpServletRequest.getParameter("tela");

        //Declara��o de objetos e tipos primitivos
        String titulo = null;
        String tituloTipo = null;
        TabelaAuxiliar tabelaAuxiliarTipo = null;
        String pacoteNomeObjeto = null;
        String funcionalidadeTabelaAuxiliarTipoManter = null;
        Object objetoTipo = null;
        //Inicializa a variavel que ser� usada em uma fun��o para pegar a
        // substring
        String pacoteNomeObjetoTipo = null;

        //Verifica se o exibir manter foi chamado da tela de filtro
        if (httpServletRequest.getAttribute("filtroTabelaAuxiliarTipo") != null) {
            tela = (String) sessao.getAttribute("tela");
        }

        //********BLOCO DE C�DIGO PARA DEFINI��O DOS CADASTROS PERTENCENTES A
        // INSERIR TABELA AUXILIAR TIPO******//
        //        Para serem incluidos novos cadastros com c�digo, descri��o e tipo
        // basta apenas cria um novo
        //        if (condicional) semelhante ao exemplo abaixo, informando apenas os
        // dados relativos
        //        ao objeto desejado.

        //---BACIA
        //Identifica a string do objeto passado no get do request
        /*
         * if (tela.equals("bacia")) { //T�tulo a ser exido nas p�ginas titulo =
         * "Bacia"; tituloTipo = "Tipo Pavimento Rua"; //Cria o objeto principal
         * Bacia bacia = new Bacia(); //Cria o objeto tipo TipoPavimentoRua
         * tipoPavimentoRua = new TipoPavimentoRua(); //Associa o objeto tabela
         * auxiliar ao tipo criado tabelaAuxiliarTipo = bacia; //Associa o
         * objeto tipo ao tipo criado objetoTipo = tipoPavimentoRua; //Pega o
         * path do pacote mais o nome da classe pacoteNomeObjeto =
         * bacia.getClass().getName(); //Define o link a ser exibido na p�gina
         * de sucesso do inserir funcionalidadeTabelaAuxiliarTipoManter =
         * Funcionalidade.TABELA_AUXILIAR_TIPO_MANTER +
         * Funcionalidade.TELA_BACIA; }
         */
        //********FIM DO BLOCO DE C�DIGO*******//
        //Parte da verifica��o do filtro
        FiltroTabelaAuxiliarTipo filtroTabelaAuxiliarTipo = null;
        //Inicializa a vari�vel tipo
        //Integer tipo = null;

        //Verifica se o filtro foi informado pela p�gina de filtragem da tabela
        // auxiliar abreviada
        if (httpServletRequest.getAttribute("filtroTabelaAuxiliarTipo") != null) {
            filtroTabelaAuxiliarTipo = (FiltroTabelaAuxiliarTipo) httpServletRequest
                    .getAttribute("filtroTabelaAuxiliarTipo");
            //Pega pelo request a variavel tipo para saber se veio null ou n�o
        } else {
            //Caso o exibirManterTabelaAuxiliar n�o tenha passado por algum
            // esquema de filtro,
            //a quantidade de registros � verificada para avaliar a necessidade
            // de filtragem
            filtroTabelaAuxiliarTipo = new FiltroTabelaAuxiliarTipo();

            if (fachada.registroMaximo(TabelaAuxiliar.class) > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_MANUTENCAO) {
                //Se o limite de registros foi atingido, a p�gina de filtragem
                // � chamada
                retorno = actionMapping
                        .findForward("filtrarTabelaAuxiliarTipo");
                sessao.setAttribute("tela", tela);
                //A vari�vel recebe apenas o nome da classe do objeto
                pacoteNomeObjetoTipo = getNomeClasse(objetoTipo);
                //Envia o objeto na sess�o
                sessao.setAttribute("tipo", pacoteNomeObjetoTipo);
            }
        }

        //A pesquisa de tabelas auxiliares s� ser� feita se o forward estiver
        // direcionado
        //para a p�gina de manterTabelaAuxiliar
        if (retorno.getName().equalsIgnoreCase("manterTabelaAuxiliarTipo")) {
            //Seta a ordena��o desejada do filtro
            filtroTabelaAuxiliarTipo
                    .setCampoOrderBy(FiltroTabelaAuxiliarTipo.ID);

            //Pesquisa de tabelas auxiliares
            tabelasAuxiliaresTipos = fachada.pesquisarTabelaAuxiliar(
                    filtroTabelaAuxiliarTipo, pacoteNomeObjeto);

            if (tabelasAuxiliaresTipos == null
                    || tabelasAuxiliaresTipos.isEmpty()) {
                //Nenhum atividade cadastrado
                throw new ActionServletException("atencao.naocadastrado");
            }

            //Verifica o numero de objetos retornados
            if (tabelasAuxiliaresTipos.size() > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_MANUTENCAO) {
                throw new ActionServletException("atencao.pesquisa.muitosregistros");
            }

            //A cole��o fica na sess�o devido ao esquema de pagina��o
            sessao.setAttribute("tabelasAuxiliaresTipos",
                    tabelasAuxiliaresTipos);
            //Envia o path do pacote na sess�o
            sessao.setAttribute("pacoteNomeObjeto", pacoteNomeObjeto);

        }

        //Envia os objetos na sess�o
        sessao.setAttribute("titulo", titulo);
        sessao.setAttribute("tipoTitulo", tituloTipo);
        sessao.setAttribute("funcionalidadeTabelaAuxiliarTipoManter",
                funcionalidadeTabelaAuxiliarTipoManter);
        sessao.setAttribute("tabelaAuxiliarTipo", tabelaAuxiliarTipo);

        //Devolve o mapeamento de retorno
        return retorno;
    }
}
