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
package gsan.gui.cadastro.imovel;

import gsan.cadastro.cliente.FiltroClienteTipo;
import gsan.cadastro.funcionario.Funcionario;
import gsan.cadastro.imovel.Categoria;
import gsan.cadastro.imovel.CategoriaTipo;
import gsan.cadastro.imovel.FiltroCategoria;
import gsan.cadastro.imovel.FiltroCategoriaTipo;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirAtualizarCategoriaAction extends GcomAction {
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping.findForward("atualizarCategoria");

        CategoriaActionForm categoriaActionForm = (CategoriaActionForm) actionForm;

        Fachada fachada = Fachada.getInstancia();

        //Mudar isso quando tiver esquema de seguran�a
        HttpSession sessao = httpServletRequest.getSession(false);

        String codigoCategoria = httpServletRequest.getParameter("idRegistroAtualizacao");

        if (codigoCategoria == null){
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null){
				codigoCategoria = (String) sessao.getAttribute("codigoCategoria");
			}else{
				codigoCategoria = (String) httpServletRequest.getAttribute("idRegistroAtualizacao").toString();
			}
			
		} else {
			sessao.setAttribute("i", true);
		}
        
        
        if ( httpServletRequest.getParameter("desfazer") !=  null && httpServletRequest.getParameter("desfazer").equals("sim") ) {
        	Categoria categoria = (Categoria)  sessao.getAttribute("categoria");
        	codigoCategoria = categoria.getId().toString();
        }

        //Verifica se o c�digo foi digitado
        if (codigoCategoria != null && !codigoCategoria.trim().equals("")
                && Integer.parseInt(codigoCategoria) > 0) {
        	
        	
        	 // Pesquisa os Tipos de Categoria
            FiltroCategoriaTipo filtroCategoriaTipo = new FiltroCategoriaTipo();
            filtroCategoriaTipo.setCampoOrderBy(FiltroClienteTipo.DESCRICAO);

            httpServletRequest.setAttribute("colecaoTipoCategoria", Fachada
                    .getInstancia().pesquisar(filtroCategoriaTipo,
                            CategoriaTipo.class.getName()));
        	
        	
            FiltroCategoria filtroCategoria = new FiltroCategoria();
            filtroCategoria.adicionarCaminhoParaCarregamentoEntidade("categoriaTipo");
            filtroCategoria.adicionarParametro(new ParametroSimples(
                    FiltroCategoria.CODIGO, codigoCategoria));

            Collection categorias = fachada.pesquisar(filtroCategoria, Categoria.class
                    .getName());
            if (categorias != null && !categorias.isEmpty()) {
                //A categoria foi encontrada
            	categoriaActionForm
                        .setIdCategoria(formatarResultado(((Categoria) ((List) categorias)
                                .get(0)).getId().toString()));

            	categoriaActionForm
                        .setDescricao(formatarResultado(((Categoria) ((List) categorias)
                                .get(0)).getDescricao().toString()));

            	categoriaActionForm
                        .setDescricaoAbreviada(formatarResultado(((Categoria) ((List) categorias)
                                .get(0)).getDescricaoAbreviada().toString()));

            	categoriaActionForm
            			.setConsumoMinimo(formatarResultado(((Categoria) ((List) categorias).get(0))
                                .getConsumoMinimo().toString()));
            	
            	categoriaActionForm
    					.setConsumoEstouro(formatarResultado(((Categoria) ((List) categorias).get(0))
    							.getConsumoEstouro().toString()));

            	categoriaActionForm
    					.setVezesMediaEstouro(formatarResultado(((Categoria) ((List) categorias).get(0))
    							.getVezesMediaEstouro().toString().replace('.',',')));

            	categoriaActionForm
    					.setMediaBaixoConsumo(formatarResultado(((Categoria) ((List) categorias).get(0))
    							.getMediaBaixoConsumo().toString()));

            	categoriaActionForm
            			.setPorcentagemMediaBaixoConsumo(Util.formatarMoedaReal(((Categoria) ((List) categorias).get(0))
            					.getPorcentagemMediaBaixoConsumo()));

            	categoriaActionForm
    					.setConsumoAlto(formatarResultado(((Categoria) ((List) categorias).get(0))
    							.getConsumoAlto().toString()));
            	
            	categoriaActionForm
    					.setVezesMediaAltoConsumo(formatarResultado(((Categoria) ((List) categorias).get(0))
    							.getVezesMediaAltoConsumo().toString().replace('.',',')));

            	SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
            	
            	categoriaActionForm
                	.setVerificaIndicadorTarifaCategoria(sistemaParametro.getIndicadorTarifaCategoria().toString());

            	
            	 if (sistemaParametro.getIndicadorTarifaCategoria() != null
                 		&& sistemaParametro.getIndicadorTarifaCategoria().compareTo(ConstantesSistema.SIM) ==  0) {
            		 
	            	if(((Categoria) ((List) categorias).get(0)).getLimiteConsumo() != null){
	                	categoriaActionForm
	        			.setLimiteConsumo(formatarResultado(((Categoria) ((List) categorias).get(0))
							.getLimiteConsumo().toString().replace('.',',')));
	            	}else{
	                	categoriaActionForm
	        				.setLimiteConsumo(null);
	            	}
	            	
	            	if(((Categoria) ((List) categorias).get(0)).getFatorLimiteConsumo() != null){
	                	categoriaActionForm
	        			.setFatorLimiteConsumo(formatarResultado(((Categoria) ((List) categorias).get(0))
							.getFatorLimiteConsumo().toString().replace('.',',')));
	            	}else{
	                	categoriaActionForm
	        				.setFatorLimiteConsumo(null);
	            	}

            	 }
            	 sessao.setAttribute("indicadorTarifaCategoria", sistemaParametro.getIndicadorTarifaCategoria());
            	 sessao.setAttribute("verificaIndicadorTarifaCategoria", categoriaActionForm.getVerificaIndicadorTarifaCategoria());
            	
            	categoriaActionForm
                        .setIndicadorUso(formatarResultado(""
                                + ((Categoria) ((List) categorias).get(0))
                                        .getIndicadorUso()));
            	
            	categoriaActionForm
                .setTipoCategoria("" + ((Categoria) ((List) categorias).get(0)).getCategoriaTipo().getId());

            	Categoria categoria = ((Categoria) ((List) categorias).get(0));

                sessao.setAttribute("categoria", categoria);
            } else {
            	categoriaActionForm.setIdCategoria("");
                httpServletRequest.setAttribute("idCategoriaNaoEncontrado","true");
                throw new ActionServletException(
                        "atencao.categoria_inexistente", null, "categoria");
            }
        }
    
        return retorno;
    }

    /**
     * formatarResultado
     * 
     * @param parametro
     *            Descri��o do par�metro
     * @return Descri��o do retorno
     */
    private String formatarResultado(String parametro) {
        if (parametro != null && !parametro.trim().equals("")) {
            if (parametro.equals("null")) {
                return "";
            } else {
                return parametro.trim();
            }
        } else {
            return "";
        }
    }
}