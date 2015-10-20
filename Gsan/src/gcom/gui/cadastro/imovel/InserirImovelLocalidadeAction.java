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

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.bean.ImovelAbaLocalidadeHelper;
import gcom.cadastro.imovel.bean.ImovelAbaLocalidadeRetornoHelper;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Classe respon�vel pela valida��o dos dados que formar�o a inscri��o do im�vel 
 *
 * @author Raphael Rossiter
 * @date 11/05/2009
 */
public class InserirImovelLocalidadeAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("gerenciadorProcesso");

		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);

		DynaValidatorForm inserirImovelLocalidadeActionForm = (DynaValidatorForm) sessao
		.getAttribute("InserirImovelActionForm");

		String idLocalidade = null;
		String idSetorComercial = null;
		String idQuadra = null;
		String idQuadraFace = null;
		String lote = null;
		String subLote = null;
		String sequencialRota = null;

		// RECEBENDO AS INFORMACOES INSERIDAS NO FORMULARIO
		idLocalidade = (String) inserirImovelLocalidadeActionForm.get("idLocalidade");
		idSetorComercial = (String) inserirImovelLocalidadeActionForm.get("idSetorComercial");
		idQuadra = (String) inserirImovelLocalidadeActionForm.get("idQuadra");
		idQuadraFace = (String) inserirImovelLocalidadeActionForm.get("idQuadraFace");
		lote = (String) inserirImovelLocalidadeActionForm.get("lote");
		subLote = (String) inserirImovelLocalidadeActionForm.get("subLote");
		sequencialRota = (String)inserirImovelLocalidadeActionForm.get("sequencialRota");

		sessao.removeAttribute("gis");
		
		//Verificar a existencia de Setor alternativo
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
    	filtroSetorComercial.adicionarParametro( new ParametroSimples ( FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
    			idSetorComercial ) );
    	
    	Collection setorComercial = this.getFachada()
    			.pesquisar( filtroSetorComercial, SetorComercial.class.getName() );

    	Iterator iteratorSetorComercial = setorComercial.iterator();
    	SetorComercial setor = null;
    
    	while ( iteratorSetorComercial.hasNext() ) {
    	
    		setor = (SetorComercial) iteratorSetorComercial.next();
            
    		if ( setor.getIndicadorSetorAlternativo().equals( ConstantesSistema.INDICADOR_USO_ATIVO ) &&
    				setor.getLocalidade().getId().equals( new Integer( idLocalidade ) ) &&
    						  ("" + setor.getCodigo()).equals(idSetorComercial) )  {
    			
    			throw new ActionServletException("atencao.setor_comercial_alternativo_nao_pode_ser_informado");
    		}
    	}
    	
		
		// ENCAPSULANDO AS INFORMA��ES DO FORMULARIO PARA VALIDA��O
		ImovelAbaLocalidadeHelper helper = new ImovelAbaLocalidadeHelper();
		
		helper.setIdLocalidade(idLocalidade);
		helper.setCodigoSetorComercial(idSetorComercial);
		helper.setNumeroQuadra(idQuadra);
		helper.setIdQuadraFace(idQuadraFace);
		helper.setLote(lote);
		helper.setSublote(subLote);
		helper.setUsuario((Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO));
		
		// VALIDANDO AS INFORMA��ES DO FORMUL�RIO
		ImovelAbaLocalidadeRetornoHelper resultado = fachada.validarImovelAbaLocalidade(helper);
		
		Collection setorComerciais = new ArrayList();
		setorComerciais.add(resultado.getSetorComercial());
		
		sessao.setAttribute("localidade", resultado.getLocalidade());
		sessao.setAttribute("setorComerciais", setorComerciais);
		sessao.setAttribute("setorComercial", resultado.getSetorComercial());
		sessao.setAttribute("quadra", resultado.getQuadra());
		sessao.setAttribute("quadraFace", resultado.getQuadraFace());
		sessao.setAttribute("quadraCaracteristicas", resultado.getQuadra());

		
		//validacao sequencial rota([FS0020])
		if(sequencialRota != null && !sequencialRota.trim().equals("")
				&& idLocalidade != null && !idLocalidade.trim().equalsIgnoreCase("")
				&& idSetorComercial != null
				&& !idSetorComercial.trim().equalsIgnoreCase("")
				&& idQuadra != null && !idQuadra.trim().equalsIgnoreCase("")){
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.LOCALIDADE_ID, new Integer(idLocalidade)));
			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.SETOR_COMERCIAL_CODIGO, new Integer(
							idSetorComercial)));
			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.QUADRA_NUMERO, new Integer(idQuadra)));
			
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.NUMERO_SEQUENCIAL_ROTA, sequencialRota));
			
			Collection imoveis = fachada.pesquisar(filtroImovel, Imovel.class
					.getName());
			if (!imoveis.isEmpty()) {
				Imovel imovelSequencial = (Imovel)Util.retonarObjetoDeColecao(imoveis);
				if(imovelSequencial.getNumeroSequencialRota().toString().equals(sequencialRota)){
					
					retorno = montarPaginaConfirmacaoWizard(
                            "atencao.imovel.ja.existe.na.sequencia.rota",
                            httpServletRequest, actionMapping);
									
				}
			}
		}

		String localiade = Util.adicionarZerosEsquedaNumero(3, new Integer(
				idLocalidade.trim()).toString());
		String codigoSetorComercial = Util.adicionarZerosEsquedaNumero(3,
				new Integer(idSetorComercial.trim()).toString());
		String quadra = Util.adicionarZerosEsquedaNumero(3, new Integer(
				idQuadra.trim()).toString());
		String numeroLote = Util.adicionarZerosEsquedaNumero(4, new Integer(
				lote.trim()).toString());
		String numerosublote = Util.adicionarZerosEsquedaNumero(3, new Integer(
				subLote.trim()).toString());

		// atribui os valores q vem pelo request as variaveis
		inserirImovelLocalidadeActionForm.set("idLocalidade", localiade);
		inserirImovelLocalidadeActionForm.set("idSetorComercial", codigoSetorComercial);
		inserirImovelLocalidadeActionForm.set("idQuadra", quadra);
		inserirImovelLocalidadeActionForm.set("lote", numeroLote);
		inserirImovelLocalidadeActionForm.set("subLote", numerosublote);
		
		

		httpServletRequest.setAttribute("destino", "inserirImovelEndereco");

		return retorno;
	}

}
