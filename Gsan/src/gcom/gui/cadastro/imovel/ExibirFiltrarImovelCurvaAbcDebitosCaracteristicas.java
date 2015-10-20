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
package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.CategoriaTipo;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroCategoriaTipo;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.Subcategoria;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
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

public class ExibirFiltrarImovelCurvaAbcDebitosCaracteristicas extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("filtrarImovelCurvaAbcDebitosCaracteristicas");
		
		//obt�m a inst�ncia da sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

		ImovelCurvaAbcDebitosActionForm imovelCurvaAbcDebitosActionForm = (ImovelCurvaAbcDebitosActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		 
		Collection<ImovelPerfil> collectionImovelPerfil = null;
		Collection<CategoriaTipo> collectionCategoriaTipo = null;
		Collection<Categoria> collectionImovelCategoria= null;
		Collection<Subcategoria> collectionImovelSubcategoria = null;
		 
		Integer idTipoCategoria = 0;
		Integer idCategoria = 0;
		Integer categoria = null;
		 
		if (sessao.getAttribute("idTipoCategoria") != null) {
			idTipoCategoria = Util.converterStringParaInteger(sessao.getAttribute("idTipoCategoria").toString());
		}
		 
		if (sessao.getAttribute("idCategoria") != null) {
			idCategoria = Util.converterStringParaInteger(sessao.getAttribute("idCategoria").toString());
		}
		 
		if (imovelCurvaAbcDebitosActionForm.getCategoria() != null) {
			categoria = new Integer(imovelCurvaAbcDebitosActionForm.getCategoria()[0]);
		}
		 
		if (imovelCurvaAbcDebitosActionForm.getIdTipoCategoria() != null &&
            !imovelCurvaAbcDebitosActionForm.getIdTipoCategoria().trim().equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO) &&
            !imovelCurvaAbcDebitosActionForm.getIdTipoCategoria().trim().equals("") &&
            Util.converterStringParaInteger(imovelCurvaAbcDebitosActionForm.getIdTipoCategoria()) != idTipoCategoria) {
		 
			FiltroCategoria filtroCategoria = new FiltroCategoria();
			filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.TIPO_CATEGORIA,
				 imovelCurvaAbcDebitosActionForm.getIdTipoCategoria()));
			filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);
			
			collectionImovelCategoria = fachada.pesquisar(filtroCategoria, Categoria.class.getName());
			 
			if (idCategoria != 0) {
				FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria();
				filtroSubcategoria.setCampoOrderBy(FiltroSubCategoria.DESCRICAO);
				
				collectionImovelSubcategoria = fachada.pesquisar(filtroSubcategoria, Subcategoria.class.getName());
				
				sessao.setAttribute("collectionImovelSubcategoria", collectionImovelSubcategoria);
			}
			
			sessao.setAttribute("collectionImovelCategoria", collectionImovelCategoria);
			sessao.setAttribute("idTipoCategoria", imovelCurvaAbcDebitosActionForm.getIdTipoCategoria());
			
		}else if (categoria != null && categoria != idCategoria) {
			FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria();
			if (!categoria.toString().trim().equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				filtroSubcategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.CATEGORIA_ID, categoria));
			}else {
				filtroSubcategoria.setCampoOrderBy(FiltroSubCategoria.DESCRICAO);
			}
			filtroSubcategoria.setCampoOrderBy(FiltroSubCategoria.DESCRICAO);
			collectionImovelSubcategoria = fachada.pesquisar(filtroSubcategoria, Subcategoria.class.getName());
			
			sessao.setAttribute("collectionImovelSubcategoria", collectionImovelSubcategoria);
			sessao.setAttribute("idCategoria", categoria);
			
		}else {
		
			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();	
			filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);
			
			collectionImovelPerfil = fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName() );
			if(collectionImovelPerfil == null || collectionImovelPerfil.isEmpty()){
				throw new ActionServletException("atencao.naocadastrado", null, "Perfil do Im�vel");			
			}				 
			
			FiltroCategoriaTipo filtroCategoriaTipo = new FiltroCategoriaTipo();
			filtroCategoriaTipo.setCampoOrderBy(FiltroCategoriaTipo.DESCRICAO);
			
			collectionCategoriaTipo = fachada.pesquisar(filtroCategoriaTipo , CategoriaTipo.class.getName());
			if (collectionCategoriaTipo == null || collectionCategoriaTipo.isEmpty()) {
				throw new ActionServletException("atencao.naocadastrado", null, "Tipo da Categoria");
			}
			
			FiltroCategoria filtroCategoria = new FiltroCategoria();
			filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);
			
			collectionImovelCategoria = fachada.pesquisar(filtroCategoria, Categoria.class.getName() );
			if(collectionImovelCategoria == null || collectionImovelCategoria.isEmpty()){
				throw new ActionServletException("atencao.naocadastrado", null, "Categoria");			
			}				 
			
			FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria();
			filtroSubcategoria.setCampoOrderBy(FiltroSubCategoria.DESCRICAO);
			
			collectionImovelSubcategoria = fachada.pesquisar(filtroSubcategoria, Subcategoria.class.getName() );		 
			if(collectionImovelSubcategoria == null || collectionImovelSubcategoria.isEmpty()){
				throw new ActionServletException("atencao.naocadastrado", null, "Subcategoria");			
			}				 
			
			sessao.setAttribute("collectionImovelPerfil", collectionImovelPerfil);
			sessao.setAttribute("collectionCategoriaTipo", collectionCategoriaTipo);
			sessao.setAttribute("collectionImovelCategoria", collectionImovelCategoria);
			sessao.setAttribute("collectionImovelSubcategoria", collectionImovelSubcategoria);
			
			sessao.removeAttribute("idTipoCategoria");
			sessao.removeAttribute("idCategoria");
		}
		
		// Usado para fazer o controle de navegacao por conta da Aba Local 
		sessao.setAttribute("abaAtual", "CARACTERISTICAS");
		
		if (imovelCurvaAbcDebitosActionForm.getClassificacao() != null) {
			httpServletRequest.setAttribute("classificacao", imovelCurvaAbcDebitosActionForm.getClassificacao());
		}
		
		return retorno;
	}
}