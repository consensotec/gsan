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
package gsan.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * Tem por objetivo o gerenciamento das funcionalidades qualificadas como
 * processos, bem como o controle de links, hints e quantidade de p�ginas
 * envolvidas num dado processo
 * 
 * @author Cesar
 */
public class GerenciadorPaginas {
    private LinkedList paginas;

    private String actionInicial;

    private String actionFinal;

    /**
     * Construtor da classe GuiGerenciadorPrecessos
     */
    public GerenciadorPaginas() {
        paginas = new LinkedList();
    }

    /**
     * Adds a feature to the Page attribute of the GuiGerenciadorPrecessos
     * object
     * 
     * @param descricao
     *            The feature to be added to the Page attribute
     * @param uriInicial
     *            Descri��o do par�metro
     * @param uriFinal
     *            Descri��o do par�metro
     * @param imagemAtiva
     *            The feature to be added to the Page attribute
     * @param imagemInativa
     *            The feature to be added to the Page attribute
     * @return Descri��o do retorno
     */
    public Pagina adicionaPagina(String descricao, String uriInicial,
            String uriFinal, String imagemAtiva, String imagemInativa) {
        paginas.add(new Pagina(descricao, uriInicial, uriFinal, imagemAtiva,
                imagemInativa));

        Pagina pagina = (Pagina) paginas.getLast();

        pagina.setIndex(paginas.size());
        return pagina;
    }

    /**
     * Retorna o valor de paginaCorrente
     * 
     * @return O valor de paginaCorrente
     */
    public Pagina getPaginaCorrente() {
        Pagina retorno = null;

        ListIterator listIterator = paginas.listIterator();

        while (listIterator.hasNext()) {
            Pagina pagina = (Pagina) listIterator.next();

            if (pagina.isPaginaCorrente()) {
                retorno = pagina;
            }
        }
        return retorno;
    }

    /**
     * Retorna o valor de paginaSeguinte
     * 
     * @return O valor de paginaSeguinte
     */
    public Pagina getPaginaSeguinte() {
        Pagina paginaCorrente = null;
        Pagina paginaSeguinte = null;

        paginaCorrente = getPaginaCorrente();

        if (paginaCorrente == null) {
            paginaSeguinte = setPaginaCorrentePeloIndice(0);
        } else {
            paginaSeguinte = getPaginaPeloIndice(paginaCorrente.getIndex() + 1);
        }

        return paginaSeguinte;
    }

    /**
     * Retorna o valor de paginaAnterior
     * 
     * @return O valor de paginaAnterior
     */
    public Pagina getPaginaAnterior() {
        //boolean existePaginaCorrente = false;
        Pagina paginaCorrente = null;
        Pagina paginaAnterior = null;

        paginaCorrente = getPaginaCorrente();

        if (paginaCorrente == null) {
            paginaAnterior = setPaginaCorrentePeloIndice(0);
        } else {
            paginaAnterior = getPaginaPeloIndice(paginaCorrente.getIndex() - 1);
        }

        return paginaAnterior;
    }

    /**
     * Construtor da classe setPaginaPeloIndice
     * 
     * @param indice
     *            Descri��o do par�metro
     * @return Descri��o do retorno
     */
    public Pagina setPaginaCorrentePeloIndice(int indice) {
        Pagina paginaCorrenteNova = null;
        Pagina paginaCorrenteAtual = null;

        paginaCorrenteAtual = getPaginaCorrente();

        if (paginaCorrenteAtual != null) {
            if (indice > paginaCorrenteAtual.getIndex()) {
                paginaCorrenteNova = getPaginaSeguinte();
                paginaCorrenteAtual.setPaginaCorrente(false);
                paginaCorrenteAtual.setPaginaPreenchida(true);
            } else if (indice < paginaCorrenteAtual.getIndex()) {
                for (int i = indice; i < paginas.size(); i++) {
                    paginaCorrenteAtual = ((Pagina) paginas.get(i));
                    paginaCorrenteAtual.setPaginaCorrente(false);
                    paginaCorrenteAtual.setPaginaPreenchida(false);
                }
                paginaCorrenteNova = ((Pagina) paginas.get(indice - 1));
            } else if (indice == paginaCorrenteAtual.getIndex()) {
                paginaCorrenteNova = paginaCorrenteAtual;
            }
        } else {
            paginaCorrenteNova = ((Pagina) paginas.get(indice - 1));
        }
        paginaCorrenteNova.setPaginaCorrente(true);
        paginaCorrenteNova.setPaginaPreenchida(false);

        return paginaCorrenteNova;
    }

    /**
     * Retorna o valor de paginaPeloIndice
     * 
     * @param indice
     *            Descri��o do par�metro
     * @return O valor de paginaPeloIndice
     */
    public Pagina getPaginaPeloIndice(int indice) {
        return (Pagina) this.paginas.get(indice - 1);
    }

    /**
     * Seta o valor de paginaInativa
     * 
     * @param indice
     *            O novo valor de paginaInativa
     */
    public void setPaginaInativa(int indice) {
        Pagina pagina = ((Pagina) paginas.get(indice - 1));

        pagina.setPaginaInativa(true);
    }

    /**
     * < <Descri��o do m�todo>>
     * 
     * @return Descri��o do retorno
     */
    public Collection getPaginas() {
        return paginas;
    }

    /**
     * < <Descri��o do m�todo>>
     * 
     * @param request
     *            Descri��o do par�metro
     * @param pagina
     *            Descri��o do par�metro
     * @return Descri��o do retorno
     */
    public String generateHint(HttpServletRequest request, Pagina pagina) {
        String hint = "";
        //int indexAtributoPagina = 0;
        AtributoPagina atributoPagina = null;
        String descricaoAtributoPagina = null;
        String descricaoAtributoRequest = null;
        Map mapRequest = request.getParameterMap();

        Iterator iterator = pagina.getAtributosPagina().iterator();

        while (iterator.hasNext()) {
            atributoPagina = (AtributoPagina) iterator.next();
            descricaoAtributoPagina = atributoPagina.getDescricao();
            if (mapRequest.get(atributoPagina.getId()) != null) {
                descricaoAtributoRequest = ((String[]) mapRequest
                        .get(atributoPagina.getId()))[0];
                atributoPagina.setTamanho(new Integer(descricaoAtributoRequest
                        .length()));
                hint += "<b>" + descricaoAtributoPagina + ":</b> "
                        + descricaoAtributoRequest + "<br>";
            }
        }
        hint = hint.substring(0, hint.length() - 4);
        return hint;
    }

    /**
     * < <Descri��o do m�todo>>
     * 
     * @param indicePagina
     *            Descri��o do par�metro
     */
    public void clearHint(int indicePagina) {
        this.getPaginaPeloIndice(indicePagina);
    }

    /**
     * Retorna o valor de size
     * 
     * @return O valor de size
     */
    public Integer getSize() {
        return new Integer(this.paginas.size());
    }

    /**
     * Retorna o valor de actionFinal
     * 
     * @return O valor de actionFinal
     */
    public String getActionFinal() {
        return actionFinal;
    }

    /**
     * Retorna o valor de actionInicial
     * 
     * @return O valor de actionInicial
     */
    public String getActionInicial() {
        return actionInicial;
    }

    /**
     * Seta o valor de actionFinal
     * 
     * @param actionFinal
     *            O novo valor de actionFinal
     */
    public void setActionFinal(String actionFinal) {
        this.actionFinal = actionFinal;
    }

    /**
     * Seta o valor de actionInicial
     * 
     * @param actionInicial
     *            O novo valor de actionInicial
     */
    public void setActionInicial(String actionInicial) {
        this.actionInicial = actionInicial;
    }

}
