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
package gsan.gui.cadastro.endereco;

import gsan.cadastro.endereco.Cep;
import gsan.cadastro.endereco.FiltroCep;
import gsan.cadastro.endereco.FiltroLogradouroTipo;
import gsan.cadastro.endereco.FiltroLogradouroTitulo;
import gsan.cadastro.endereco.LogradouroTipo;
import gsan.cadastro.endereco.LogradouroTitulo;
import gsan.cadastro.geografico.Bairro;
import gsan.cadastro.geografico.FiltroBairro;
import gsan.cadastro.geografico.FiltroMunicipio;
import gsan.cadastro.geografico.Municipio;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ComparacaoTexto;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action respons�vel pela pre-exibi��o da pagina de inserir logradouro
 * 
 * @author S�vio Luiz
 * @created 15 de Julho de 2005
 */
public class ExibirInserirLogradouroAction extends GcomAction {
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

        ActionForward retorno = actionMapping.findForward("inserirLogradouro");

        Fachada fachada = Fachada.getInstancia();

        //Mudar isso quando tiver esquema de seguran�a
        HttpSession sessao = httpServletRequest.getSession(false);
        
        LogradouroActionForm logradouroActionForm = (LogradouroActionForm) actionForm;
        
        logradouroActionForm.setColecaoBairro("");
		logradouroActionForm.setColecaoCep("");
        
        if (sessao.getAttribute("colecaoBairrosSelecionadosUsuario") != null){
        	
        	Collection colecaoBairros = (List) sessao
            .getAttribute("colecaoBairrosSelecionadosUsuario");
        	
        	if (!colecaoBairros.isEmpty()){
        		logradouroActionForm.setColecaoBairro("CARREGADO");
        	}
        	else{
        		logradouroActionForm.setColecaoBairro("");
        	}
        	
        }
        
        if (sessao.getAttribute("colecaoCepSelecionadosUsuario") != null){
        	
        	Collection colecaoCep = (List) sessao
            .getAttribute("colecaoCepSelecionadosUsuario");
        	
        	if (!colecaoCep.isEmpty()){
        		logradouroActionForm.setColecaoCep("CARREGADO");
        	}
        	else{
        		logradouroActionForm.setColecaoCep("");
        	}
        }

        
        //-------Parte que trata do c�digo quando o usu�rio tecla enter
        //caso seja o id do municipio
        String idDigitadoEnterMunicipio = (String) logradouroActionForm
                .getIdMunicipio();
        String codigoDigitadoEnterBairro = (String) logradouroActionForm
                .getCodigoBairro();
        
//      verifica se n�o foi a pesquisa do enter
        //se n�o for ent�o � a primeiravez que entra no action
        //prepara as cole��es de logradouro tipo e logradouro titulo para
        // exibir
        //na p�gina
        if ((idDigitadoEnterMunicipio == null || idDigitadoEnterMunicipio
                .trim().equals(""))
                && (codigoDigitadoEnterBairro == null || codigoDigitadoEnterBairro
                        .trim().equals(""))) {

            FiltroLogradouroTipo filtroLogradouroTipo = new FiltroLogradouroTipo(
                    FiltroLogradouroTipo.DESCRICAO);

            filtroLogradouroTipo.setConsultaSemLimites(true);

            filtroLogradouroTipo.adicionarParametro(new ParametroSimples(
                    FiltroLogradouroTipo.INDICADORUSO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

            Collection logradouroTipos = fachada.pesquisar(
                    filtroLogradouroTipo, LogradouroTipo.class.getName());

            if (logradouroTipos == null || logradouroTipos.isEmpty()) {
                throw new ActionServletException(
                        "atencao.pesquisa.nenhumresultado", null,
                        "logradouro tipo");
            } else {
                sessao.setAttribute("logradouroTipos", logradouroTipos);
            }

            FiltroLogradouroTitulo filtroLogradouroTitulo = new FiltroLogradouroTitulo(
                    FiltroLogradouroTitulo.DESCRICAO);

            filtroLogradouroTitulo.setConsultaSemLimites(true);

            filtroLogradouroTitulo.adicionarParametro(new ParametroSimples(
                    FiltroLogradouroTipo.INDICADORUSO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

            Collection logradouroTitulos = fachada.pesquisar(
                    filtroLogradouroTitulo, LogradouroTitulo.class.getName());

            if (logradouroTitulos == null || logradouroTitulos.isEmpty()) {
                throw new ActionServletException(
                        "atencao.pesquisa.nenhumresultado", null,
                        "logradouro t�tulo");
            } else {
                sessao.setAttribute("logradouroTitulos", logradouroTitulos);
            }

        }
        
        /*
         * Removendo toda a cole��o de bairro da sess�o
         */
        String removerColecaoBairro = httpServletRequest.getParameter("removerColecaoBairro");
        
        if (removerColecaoBairro != null && !removerColecaoBairro.equals("")){
        	
        	sessao.removeAttribute("colecaoBairrosSelecionadosUsuario");
        	logradouroActionForm.setColecaoBairro("");
        }
        
        /*
         * Removendo toda a cole��o de cep da sess�o
         */
        String removerColecaoCep = httpServletRequest.getParameter("removerColecaoCep");
        
        if (removerColecaoCep != null && !removerColecaoCep.equals("")){
        	
        	sessao.removeAttribute("colecaoCepSelecionadosUsuario");
        	logradouroActionForm.setColecaoCep("");
        }

        //Verifica se o c�digo foi digitado
        if (idDigitadoEnterMunicipio != null
                && !idDigitadoEnterMunicipio.trim().equals("")
                && !Util.validarValorNaoNumerico(idDigitadoEnterMunicipio)) {
            FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

            filtroMunicipio.adicionarParametro(new ParametroSimples(
                    FiltroMunicipio.ID, idDigitadoEnterMunicipio));
            filtroMunicipio.adicionarParametro(new ParametroSimples(
                    FiltroMunicipio.INDICADOR_USO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

            Collection municipioEncontrado = fachada.pesquisar(filtroMunicipio,
                    Municipio.class.getName());

            if (municipioEncontrado != null && !municipioEncontrado.isEmpty()) {
                //O municipio foi encontrado
                logradouroActionForm
                        .setIdMunicipio(((Municipio) ((List) municipioEncontrado)
                                .get(0)).getId().toString());
                logradouroActionForm
                        .setNomeMunicipio(((Municipio) ((List) municipioEncontrado)
                                .get(0)).getNome());
                
                httpServletRequest.setAttribute("nomeCampo",
                "codigoBairro");

                httpServletRequest.setAttribute("idMunicipioNaoEncontrado",
                        "true");
                
                Municipio municipio = ((Municipio) ((List) municipioEncontrado).get(0));
                
                if (!fachada.verificarMunicipioComCepPorLogradouro(municipio)){
                	
                	httpServletRequest.setAttribute("cepUnico", "OK");
                	
                	Cep cep = fachada.obterCepUnicoMunicipio(municipio);
                	
                	if (cep != null){
                		Collection colecaoCepSelecionadosUsuario = new ArrayList();
                    	colecaoCepSelecionadosUsuario.add(cep);
                    	
                    	sessao.setAttribute("colecaoCepSelecionadosUsuario", colecaoCepSelecionadosUsuario);
                	}
                }

            } else {
            	
                logradouroActionForm.setIdMunicipio("");
                httpServletRequest.setAttribute("nomeCampo",
                "idMunicipio");
                httpServletRequest.setAttribute("idMunicipioNaoEncontrado",
                        "exception");
                logradouroActionForm.setNomeMunicipio("Munic�pio inexistente");

            }

        }

        //Verifica se o c�digo foi digitado
        if (codigoDigitadoEnterBairro != null
                && !codigoDigitadoEnterBairro.trim().equals("")
                && !Util.validarValorNaoNumerico(codigoDigitadoEnterBairro)) {
            FiltroBairro filtroBairro = new FiltroBairro();

            filtroBairro.adicionarCaminhoParaCarregamentoEntidade("municipio");
            
            filtroBairro.adicionarParametro(new ParametroSimples(
                    FiltroBairro.CODIGO, codigoDigitadoEnterBairro));
            filtroBairro.adicionarParametro(new ParametroSimples(
                    FiltroBairro.INDICADOR_USO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));
            // verifica se o bairro pesquisado � de um municipio existente
            if (idDigitadoEnterMunicipio != null
                    && !idDigitadoEnterMunicipio.trim().equals("")
                    && Integer.parseInt(idDigitadoEnterMunicipio) > 0) {

                filtroBairro.adicionarParametro(new ParametroSimples(
                        FiltroBairro.MUNICIPIO_ID, idDigitadoEnterMunicipio));
            }

            Collection bairroEncontrado = fachada.pesquisar(filtroBairro,
                    Bairro.class.getName());

            if (bairroEncontrado != null && !bairroEncontrado.isEmpty()) {
                
            	//O bairro foi encontrado
                Bairro objetoBairroEncontrado = (Bairro) Util.retonarObjetoDeColecao(bairroEncontrado);
            	
            	logradouroActionForm.setCodigoBairro(String.valueOf(objetoBairroEncontrado.getCodigo()));
                logradouroActionForm.setNomeBairro(objetoBairroEncontrado.getNome());
                
                httpServletRequest.setAttribute("nomeCampo", "botaoAdicionarBairro");

                httpServletRequest.setAttribute("idBairroNaoEncontrado", "true");
                
                /*
                 * Adicionado o novo BAIRRO na cole��o
                 */
                String adicionarBairroColecao = httpServletRequest.getParameter("adicionarBairroColecao");
                
                if (adicionarBairroColecao != null && !adicionarBairroColecao.equals("")){
                	
                	logradouroActionForm.setCodigoBairro("");
                    logradouroActionForm.setNomeBairro("");
                    
                	List colecaoBairrosSelecionadosUsuario = new ArrayList();                	
                    if (sessao.getAttribute("colecaoBairrosSelecionadosUsuario") != null){
                    	
                    	colecaoBairrosSelecionadosUsuario = (List) sessao
                        .getAttribute("colecaoBairrosSelecionadosUsuario");
                    	
                    	if (!colecaoBairrosSelecionadosUsuario.contains((Bairro) ((List) bairroEncontrado).get(0))){
                    		colecaoBairrosSelecionadosUsuario.add((Bairro) ((List) bairroEncontrado).get(0));
                    		logradouroActionForm.setColecaoBairro("CARREGADO");
                    	}
                    	else{
                    		throw new ActionServletException(
                                    "atencao.objeto_ja_selecionado", null, "Bairro");
                    	}
                    }
                    else{
                    	colecaoBairrosSelecionadosUsuario.add((Bairro) ((List) bairroEncontrado).get(0));
                    	sessao.setAttribute("colecaoBairrosSelecionadosUsuario", colecaoBairrosSelecionadosUsuario);
                    	logradouroActionForm.setColecaoBairro("CARREGADO");
                    }
                }
                
            } else {
                logradouroActionForm.setCodigoBairro("");
                httpServletRequest.setAttribute("nomeCampo",
                "codigoBairro");
                httpServletRequest.setAttribute("idBairroNaoEncontrado",
                        "exception");
                logradouroActionForm.setNomeBairro("Bairro inexistente");

            }

        }
        //fim da parte da pesquisa do enter        
        
        /*
         * Removendo o bairro selecionado da sess�o
         */
        String idBairro = httpServletRequest.getParameter("idBairro");
        
        if (idBairro != null && !idBairro.equals("") &&
        	sessao.getAttribute("colecaoBairrosSelecionadosUsuario") != null){
        	
        	Collection colecaoBairrosSelecionadosUsuario = (Collection) sessao
            .getAttribute("colecaoBairrosSelecionadosUsuario");

            Iterator colecaoBairrosSelecionadosUsuarioIterator;

            Bairro bairroInserir;

            colecaoBairrosSelecionadosUsuarioIterator = colecaoBairrosSelecionadosUsuario
            .iterator();

            while (colecaoBairrosSelecionadosUsuarioIterator.hasNext()) {

            	bairroInserir = (Bairro) colecaoBairrosSelecionadosUsuarioIterator
                .next();

                if (bairroInserir.getId().equals(new Integer(idBairro))) {
                	colecaoBairrosSelecionadosUsuario.remove(bairroInserir);
                	break;
                }
            }
            
            if (colecaoBairrosSelecionadosUsuario.isEmpty()){
            	logradouroActionForm.setColecaoBairro("");
            }
        }
        
        
        /*
         * Removendo o CEP selecionado da sess�o
         */
        String idCep = httpServletRequest.getParameter("idCep");
        
        if (idCep != null && !idCep.equals("") &&
        	sessao.getAttribute("colecaoCepSelecionadosUsuario") != null){
        	
        	Collection colecaoCepSelecionadosUsuario = (Collection) sessao
            .getAttribute("colecaoCepSelecionadosUsuario");

            Iterator colecaoCepSelecionadosUsuarioIterator;

            Cep cepInserir;

            colecaoCepSelecionadosUsuarioIterator = colecaoCepSelecionadosUsuario
            .iterator();

            while (colecaoCepSelecionadosUsuarioIterator.hasNext()) {

            	cepInserir = (Cep) colecaoCepSelecionadosUsuarioIterator
                .next();

                if (cepInserir.getCepId().equals(new Integer(idCep))) {
                	colecaoCepSelecionadosUsuario.remove(cepInserir);
                	break;
                }
            }
            
            if (colecaoCepSelecionadosUsuario.isEmpty()){
            	logradouroActionForm.setColecaoCep("");
            }
        }
        
        
        /*
        * Adicionando um cep na cole��o a partir do nome do munic�pio e do c�digo do cep 
        */
        String codigoDigitadoEnterCep = (String) logradouroActionForm.getCodigoCEP();
        
        if (codigoDigitadoEnterCep != null && 
        	!codigoDigitadoEnterCep.trim().equals("") && 
        	!Util.validarValorNaoNumerico(codigoDigitadoEnterCep)) {
        	
            FiltroCep filtroCep = new FiltroCep();

            filtroCep.adicionarParametro(new ParametroSimples(
            FiltroCep.CODIGO, codigoDigitadoEnterCep));
            
            filtroCep.adicionarParametro(new ParametroSimples(
            FiltroCep.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
            
            if (logradouroActionForm.getNomeMunicipio() != null
                && !logradouroActionForm.getNomeMunicipio().trim().equals("")) {

            	filtroCep.adicionarParametro(new ComparacaoTexto(
                FiltroCep.MUNICIPIO, logradouroActionForm.getNomeMunicipio()));
            	
            }

            Collection cepEncontrado = fachada.pesquisar(filtroCep,
            Cep.class.getName());

            if (cepEncontrado != null && !cepEncontrado.isEmpty()) {
                
            	Cep objetoCepEncontrado = (Cep) Util.retonarObjetoDeColecao(cepEncontrado);
            	
            	logradouroActionForm.setCodigoCEP(String.valueOf(objetoCepEncontrado.getCodigo()));
                logradouroActionForm.setDescricaoCEP(objetoCepEncontrado.getDescricaoLogradouroFormatada());
                
                httpServletRequest.setAttribute("nomeCampo", "botaoAdicionarCep");

                httpServletRequest.setAttribute("idCEPNaoEncontrado", "true");
                
                /*
                 * Adicionado o novo CEP na cole��o
                 */
                String adicionarCepColecao = httpServletRequest.getParameter("adicionarCepColecao");
                
                if (adicionarCepColecao != null && !adicionarCepColecao.equals("")){
                	
                	logradouroActionForm.setCodigoCEP("");
                    logradouroActionForm.setDescricaoCEP("");
                    
                	List colecaoCepSelecionadosUsuario = new ArrayList();
	                if (sessao.getAttribute("colecaoCepSelecionadosUsuario") != null){
	                	
	                	colecaoCepSelecionadosUsuario = (List) sessao
	                    .getAttribute("colecaoCepSelecionadosUsuario");
	                	
	                	if (!colecaoCepSelecionadosUsuario.contains((Cep) ((List) cepEncontrado).get(0))){
	                		colecaoCepSelecionadosUsuario.add((Cep) ((List) cepEncontrado).get(0));
	                		logradouroActionForm.setColecaoCep("CARREGADO");
	                	}
	                	else{
                    		throw new ActionServletException(
                                    "atencao.objeto_ja_selecionado", null, "Cep");
                    	}
	                }
	                else{
	                	colecaoCepSelecionadosUsuario.add((Cep) ((List) cepEncontrado).get(0));
	                	sessao.setAttribute("colecaoCepSelecionadosUsuario", colecaoCepSelecionadosUsuario);
	                	logradouroActionForm.setColecaoCep("CARREGADO");
	                }
                }

            } else {
                logradouroActionForm.setCodigoCEP("");
                httpServletRequest.setAttribute("nomeCampo",
                "codigoCEP");
                httpServletRequest.setAttribute("idCEPNaoEncontrado",
                        "exception");
                logradouroActionForm.setDescricaoCEP("CEP inexistente");

            }

        }
        
        return retorno;
    }
}
