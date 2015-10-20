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
package gcom.gui.cadastro.localidade;

import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.bean.InserirQuadraHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Classe respons�vel pela inser��o dos dados de uma quadra 
 *
 * @author Ivan S�rgio, Raphael Rossiter
 * @date 10/02/2009, 06/04/2009
 */
public class InserirQuadraAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		InserirQuadraActionForm inserirQuadraActionForm = (InserirQuadraActionForm) actionForm;
		
		
		//Verificar a existencia de Setor alternativo
		String setorComercialId = inserirQuadraActionForm.getSetorComercialID();
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
    	filtroSetorComercial.adicionarParametro( new ParametroSimples ( FiltroSetorComercial.ID,
    			setorComercialId ) );
    	
    	Collection setorComercial = this.getFachada()
    			.pesquisar( filtroSetorComercial, SetorComercial.class.getName() );

    	Iterator iteratorSetorComercial = setorComercial.iterator();
    	SetorComercial setor = null;
    
    	while ( iteratorSetorComercial.hasNext() ) {
    	
    		setor = (SetorComercial) iteratorSetorComercial.next();
            
    		if ( setor.getIndicadorSetorAlternativo().equals( ConstantesSistema.INDICADOR_USO_ATIVO ) ) {
    			
    			throw new ActionServletException("atencao.setor_comercial_alternativo");
    		}
    	}
		//CARREGANDO O OBJETO INSERIR_QUADRA_HELPER
		InserirQuadraHelper helper = this.carregarInserirQuadraHelper(inserirQuadraActionForm);
		
		//VALIDANDO OS DADOS DA QUADRA
		Quadra quadraInserir = fachada.validarQuadra(helper);
		
		//OBTENDO AS FACES DA QUADRA
		Collection colecaoQuadraFace = (Collection) sessao.getAttribute("colecaoQuadraFace");

		Integer idQuadra = null;
		
        Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		idQuadra = fachada.inserirQuadra(quadraInserir, colecaoQuadraFace, usuarioLogado);

		montarPaginaSucesso(httpServletRequest, "Quadra de n�mero "
				+ quadraInserir.getNumeroQuadra() + " do setor comercial "
				+ helper.getSetorComercialCD() + "-" + quadraInserir.getSetorComercial().getDescricao()
				+ " da localidade " + helper.getLocalidadeID() + "-"
				+ quadraInserir.getSetorComercial().getLocalidade().getDescricao() + " inserida com sucesso.",
				"Inserir outra Quadra", "exibirInserirQuadraAction.do",
				"exibirAtualizarQuadraAction.do?idRegistroInseridoAtualizar="
				+ idQuadra, "Atualizar Quadra Inserida");

		sessao.removeAttribute("InserirQuadraActionForm");
		sessao.removeAttribute("colecaoPerfilQuadra");
		sessao.removeAttribute("colecaoSistemaEsgoto");
		sessao.removeAttribute("colecaoZeis");
		sessao.removeAttribute("colecaoBacia");
		sessao.removeAttribute("colecaoQuadraFace");

		// devolve o mapeamento de retorno
		return retorno;
	}
	
	
	private InserirQuadraHelper carregarInserirQuadraHelper(InserirQuadraActionForm inserirQuadraActionForm){
		
		InserirQuadraHelper helper = new InserirQuadraHelper();
		
		helper.setLocalidadeID(inserirQuadraActionForm.getLocalidadeID());
		helper.setSetorComercialCD(inserirQuadraActionForm.getSetorComercialCD());
		helper.setQuadraNM(inserirQuadraActionForm.getQuadraNM());
		helper.setPerfilQuadraID(inserirQuadraActionForm.getPerfilQuadra());
		helper.setAreaTipoID(inserirQuadraActionForm.getAreaTipoID());
		helper.setIndicadorRedeAgua(inserirQuadraActionForm.getIndicadorRedeAguaAux());
		helper.setIndicadorRedeEsgoto(inserirQuadraActionForm.getIndicadorRedeEsgotoAux());
		helper.setSistemaEsgotoID(inserirQuadraActionForm.getSistemaEsgotoID());
		helper.setBaciaID(inserirQuadraActionForm.getBaciaID());
		helper.setDistritoOperacionalID(inserirQuadraActionForm.getDistritoOperacionalID());
		helper.setSetorCensitarioID(inserirQuadraActionForm.getSetorCensitarioID());
		helper.setZeisID(inserirQuadraActionForm.getZeisID());
		helper.setRotaCD(inserirQuadraActionForm.getCodigoRota());
		helper.setIndicadorIncrementoLote(inserirQuadraActionForm.getIndicadorIncrementoLote());
		helper.setBairroCD(inserirQuadraActionForm.getBairroID());
		helper.setMunicipioID(inserirQuadraActionForm.getMunicipioID());
		helper.setIndicadorAtualizacaoCadastral(inserirQuadraActionForm.getIndicadorAtualizacaoCadastral());
		
		return helper;
	}

}
