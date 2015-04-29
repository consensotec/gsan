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
package gsan.gui.micromedicao;

import gsan.cadastro.empresa.Empresa;
import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.FiltroSetorComercial;
import gsan.cadastro.localidade.Localidade;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.micromedicao.FiltroLeiturista;
import gsan.micromedicao.Leiturista;
import gsan.micromedicao.RoteiroEmpresa;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Realiza a inclus�o da rota de acordo com os par�metros informados
 * 
 * @author Vivianne Sousa
 * @created 21/03/2006
 */
public class InserirRoteiroEmpresaAction extends GcomAction {
	/**
	 * Este caso de uso permite a inclus�o de um novo roteiro empresa
	 * 
	 * [UC0583] Inserir Roteiro Empresa
	 * 
	 * 
	 * @author Francisco Nascimento
	 * @date 25/07/2007
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {


        ActionForward retorno = actionMapping.findForward("telaSucesso");
        InserirRoteiroEmpresaActionForm form = (InserirRoteiroEmpresaActionForm) actionForm;
        Fachada fachada = Fachada.getInstancia();
        HttpSession sessao = httpServletRequest.getSession(false);
        
        // obrigat�rios
        String idEmpresa = form.getEmpresa();
        String idLocalidade = form.getIdLocalidade();
		String idFaturamentoGrupo = form.getFaturamentoGrupo(); 				
		String idLeiturista = form.getIdLeiturista();
		
		String[] idQuadrasAdicionar = form.getIdQuadraAdicionar();

		// n obrigat�rios	
		
		validacaoRoteiroEmpresa (idEmpresa, idLocalidade, idFaturamentoGrupo, idLeiturista,  
				idQuadrasAdicionar,fachada,httpServletRequest);
		
        RoteiroEmpresa roteiro = new RoteiroEmpresa();
        
        if(idEmpresa != null && !idEmpresa.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	Empresa empresa = new Empresa();
        	empresa.setId(new Integer(idEmpresa));
        	roteiro.setEmpresa(empresa);
        }

        Leiturista leiturista = null;
        if (idLeiturista != null && !idLeiturista.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
       	
        	// Validar se a empresa escolhida for diferente da empresa a qual pertence o funcion�rio ou o cliente
			FiltroLeiturista filtro = new FiltroLeiturista();
			
			// Adiciona o id do leiturista
			filtro.adicionarParametro(new ParametroSimples(
				FiltroLeiturista.ID, idLeiturista));

			filtro.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.INDICADORUSO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna cole��o de leituristas
			Collection colecaoLeituristas = fachada.pesquisar(filtro, Leiturista.class.getName());
			leiturista = (Leiturista) Util.retonarObjetoDeColecao(colecaoLeituristas);

			if (leiturista.getEmpresa().getId().intValue() != Integer.parseInt(idEmpresa)){
				throw new ActionServletException("atencao.empresa_diferente");
			}
			
        	roteiro.setLeiturista(leiturista);
        	
        } 

        //Indicador de Uso
        roteiro.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);

        //Ultima altera��o
        roteiro.setUltimaAlteracao(new Date());
       
        Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
        
        Integer idRoteiro = fachada.inserirRoteiroEmpresa(roteiro, idQuadrasAdicionar, usuarioLogado);

        
		sessao.removeAttribute("reloadPageURL");
		sessao.removeAttribute("reloadPage");
		sessao.removeAttribute("colecaoEmpresa");
		sessao.removeAttribute("colecaoFaturamentoGrupo");
		sessao.removeAttribute("colecaoSetorComercial");
		sessao.removeAttribute("colecaoSetoresSelecionados");
		sessao.removeAttribute("colecaoQuadras");
		
        montarPaginaSucesso(httpServletRequest, "Roteiro Empresa (" +
        	idRoteiro +	") inserido com sucesso.", 
        			"Inserir outro Roteiro",
                    "exibirInserirRoteiroEmpresaAction.do?desfazer=S",
                    "exibirAtualizarRoteiroEmpresaAction.do?idRegistroInseridoAtualizar="
					+ idRoteiro,
					"Atualizar Roteiro Empresa Inserido");
        
       return retorno;
    }
    
//		validacaoRoteiroEmpresa (empresa, idLocalidade, idFaturamentoGrupo, idFuncionario, idCliente, 
//	quadras,fachada,httpServletRequest);

    private void validacaoRoteiroEmpresa (String empresa, String idLocalidade
    		,String idFaturamentoGrupo, String idLeiturista
    		,String[] quadrasAdicionar
    		,Fachada fachada
    		,HttpServletRequest httpServletRequest
    		){
    	
    	//Empresa � obrigat�rio.
		if ((empresa == null) || (empresa.equals(""
				+ ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			httpServletRequest.setAttribute("nomeCampo","empresa");
			throw new ActionServletException("atencao.empresa_nao_informada");			
		}else if (Util.validarValorNaoNumerico(empresa)){
			//Empresa n�o num�rico.
			httpServletRequest.setAttribute("nomeCampo","empresa");
			throw new ActionServletException("atencao.nao.numerico",
					null,"Empresa");		
		}
    	//Localidade � obrigat�rio.
		if ((idLocalidade == null) || (idLocalidade.equals(""))) {
			httpServletRequest.setAttribute("nomeCampo","idLocalidade");
			throw new ActionServletException("atencao.localidade_nao_informada");			
		}else if (Util.validarValorNaoNumerico(idLocalidade)){
			
			//Localidade n�o num�rico.
			httpServletRequest.setAttribute("nomeCampo","idLocalidade");
			throw new ActionServletException("atencao.nao.numerico",
					null,"Localidade");		
		} else {
			verificaExistenciaIdLocalidade(idLocalidade, fachada, httpServletRequest);
		}
		
		// O grupo de faturamento � obrigat�rio.
		if ((idFaturamentoGrupo == null)
				|| (idFaturamentoGrupo.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			httpServletRequest.setAttribute("nomeCampo","faturamentoGrupo");
			throw new ActionServletException(
					"atencao.faturamento_grupo_nao_informado");
		}
		
		// O leiturista � obrigat�rio: funcion�rio ou cliente
		if (idLeiturista == null || idLeiturista.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO)){
			httpServletRequest.setAttribute("nomeCampo","leiturista");
			throw new ActionServletException("atencao.leiturista_tipo_nao_informado");
		}

		
		//[FS0010] Verificar inexist�ncia de alguma quadra
		if (quadrasAdicionar == null || quadrasAdicionar.length == 0){
			throw new ActionServletException(
			"atencao.quadras.informar");			
		}
		
    }
    
    private void verificaExistenciaIdLocalidade(String idDigitadoEnterLocalidade,			
			Fachada fachada,HttpServletRequest httpServletRequest) {

		//Verifica se o c�digo da localidade foi digitado
		if ((idDigitadoEnterLocalidade != null && 
				!idDigitadoEnterLocalidade.toString().trim().equalsIgnoreCase(""))) {
		
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			if (idDigitadoEnterLocalidade != null
				&& !idDigitadoEnterLocalidade.toString().trim().equalsIgnoreCase("")) {
				
				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, new Integer(idDigitadoEnterLocalidade)));
			}
			
			Collection<Localidade> localidades = fachada.pesquisar(
					filtroLocalidade, Localidade.class.getName());
					
			if (localidades == null || localidades.isEmpty()) {
				//a localidade n�o foi encontrada
				//Setor Comercial n�o existe para esta localidade
				httpServletRequest.setAttribute("nomeCampo","codigoSetorComercial");
				throw new ActionServletException(
				"atencao.setor_comercial_nao_existe");						
			}
		
		}
	
	}

}
 