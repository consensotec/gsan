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
package gcom.gui.cadastro.geografico;

import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
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
 * Description of the Class
 * 
 * @author compesa
 * @created 1 de Julho de 2004
 */
public class AtualizarBairroAction extends GcomAction {
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
        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping.findForward("telaSucesso");

        BairroActionForm bairroActionForm = (BairroActionForm) actionForm;

        Fachada fachada = Fachada.getInstancia();
        
		
        /*
		 * [UC0107] Registrar Transa��o
		 * 
		 */
        
//		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
//				Operacao.OPERACAO_BAIRRO_ATUALIZAR,
//				new UsuarioAcaoUsuarioHelper(Usuario.USUARIO_TESTE,
//						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
//
//		Operacao operacao = new Operacao();
//		operacao.setId(Operacao.OPERACAO_BAIRRO_ATUALIZAR);
//
//		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
//		operacaoEfetuada.setOperacao(operacao);

		// [UC0107] -Fim- Registrar Transa��o


        //Mudar isso quando tiver esquema de seguran�a
        HttpSession sessao = httpServletRequest.getSession(false);
        Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
        
        Bairro bairro = (Bairro) sessao.getAttribute("bairro");

        String idMunicipio = (String) bairroActionForm.getIdMunicipio();

        Municipio municipio = null;

        if (idMunicipio != null && !idMunicipio.equals("")) {
            FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

            filtroMunicipio.adicionarParametro(new ParametroSimples(
                    FiltroMunicipio.ID, idMunicipio));
            filtroMunicipio.adicionarParametro(new ParametroSimples(
                    FiltroMunicipio.INDICADOR_USO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

            Collection municipios = fachada.pesquisar(filtroMunicipio,
                    Municipio.class.getName());

            if (municipios != null && !municipios.isEmpty()) {
                //O municipio foi encontrado
                Iterator municipioIterator = municipios.iterator();

                municipio = (Municipio) municipioIterator.next();

            } else {
                throw new ActionServletException(
                        "atencao.pesquisa_inexistente", null, "Munic�pio");
            }

        }

        int codigoBairro = 0;

        String codigoBairroPesquisar = (String) bairroActionForm
                .getCodigoBairro();

       // FiltroBairro filtroBairro = new FiltroBairro();

        codigoBairro = Integer.parseInt(codigoBairroPesquisar);

        Integer codigoBairroPrefeitura = null;
        if (bairroActionForm.getCodigoBairroPrefeitura() != null
                && !bairroActionForm.getCodigoBairroPrefeitura()
                        .equalsIgnoreCase("")) {
            codigoBairroPrefeitura = new Integer(bairroActionForm
                    .getCodigoBairroPrefeitura());
        }

        Short indicadorDeUso = new Short(bairroActionForm.getIndicadorUso());

        
        //Verifica se o nome do bairro � diferente do atual, caso sim, verifica se ja existe o bairro na base  
        if (!bairro.getNome().equalsIgnoreCase(bairroActionForm.getNomeBairro())){
        	FiltroBairro filtroBairroExistente = new FiltroBairro();
    		filtroBairroExistente.adicionarParametro(new ParametroSimples(
    				FiltroBairro.MUNICIPIO_ID, bairroActionForm.getIdMunicipio()));
    		filtroBairroExistente.adicionarParametro(new ParametroSimples(
    				FiltroBairro.NOME, bairroActionForm.getNomeBairro()));
    		filtroBairroExistente
    				.adicionarCaminhoParaCarregamentoEntidade("municipio");
    		Collection collectionBairro = (Collection) fachada.pesquisar(
    				filtroBairroExistente, Bairro.class.getName());

    		if (collectionBairro != null && !collectionBairro.isEmpty()) {
    			throw new ActionServletException(
    					"atencao.bairro_existente_municipio");
    		}
        }
     
        Collection colecaoBairroArea = null;
		
		if (sessao.getAttribute("colecaoBairroArea") != null){
			colecaoBairroArea = (Collection)sessao.getAttribute("colecaoBairroArea");
		}
        if (colecaoBairroArea == null || colecaoBairroArea.isEmpty()){
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"�reas do Bairro");
		}
        
        //seta os campos para serem atualizados
        bairro.setMunicipio(municipio);
        bairro.setCodigo(codigoBairro);
        bairro.setNome(bairroActionForm.getNomeBairro());
        bairro.setCodigoBairroPrefeitura(codigoBairroPrefeitura);
        bairro.setIndicadorUso(indicadorDeUso);

        //regitrando operacao
//		bairro.setOperacaoEfetuada(operacaoEfetuada);
//		bairro.adicionarUsuario(Usuario.USUARIO_TESTE,	UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
//		registradorOperacao.registrarOperacao(bairro);
        
		fachada.atualizarBairro(bairro,colecaoBairroArea,
				(Collection)sessao.getAttribute("colecaoBairroAreaRemover"),usuarioLogado);

        montarPaginaSucesso(httpServletRequest, "Bairro de c�digo "
                + bairro.getCodigo() + " do munic�pio "+ bairro.getMunicipio().getNome() + " atualizado com sucesso.",
                "Realizar outra Manuten��o de Bairro",
                "exibirFiltrarBairroAction.do?menu=sim");

        return retorno;
    }

}
