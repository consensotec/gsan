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
package gcom.gui.cadastro.atualizacaocadastral;

import gcom.cadastro.ArquivoTextoAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.FiltroArquivoTextoAtualizacaoCadastral;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.micromedicao.DadosLeiturista;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.Leiturista;
import gcom.seguranca.transacao.FiltroTabelaColuna;
import gcom.seguranca.transacao.TabelaColuna;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirFiltrarAlteracaoAtualizacaoCadastralAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o retorno
        ActionForward retorno = actionMapping
                .findForward("exibirFiltrarAlteracaoAtualizacaoCadastral");

        //Obt�m a inst�ncia da fachada
        Fachada fachada = Fachada.getInstancia();

        //Obt�m a sess�o
        HttpSession sessao = httpServletRequest.getSession(false);
        
        FiltrarAlteracaoAtualizacaoCadastralActionForm form = (FiltrarAlteracaoAtualizacaoCadastralActionForm) actionForm;
        
		Collection colecaoLeiturista = new ArrayList();
        
		if(sessao.getAttribute("colecaoEmpresa") == null){
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(
					FiltroEmpresa.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
			
			Collection<Empresa> colecaoEmpresa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
			
			// [FS0001 - Verificar Existencia de dados]
			if ( (colecaoEmpresa == null) || (colecaoEmpresa.size() == 0) ) {
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null, Empresa.class.getName());
			}else {
				sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
			}
		}
		
		// Leiturista da Empresa
		if (form.getIdEmpresa() != null && !form.getIdEmpresa().equals("-1")
				&& !form.getIdEmpresa().equals("")) {

			FiltroLeiturista filtroLeiturista = new FiltroLeiturista(
					FiltroLeiturista.ID);
			filtroLeiturista.adicionarParametro(new ParametroSimples(
					FiltroLeiturista.EMPRESA_ID,
					form.getIdEmpresa()));
			filtroLeiturista
					.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
			filtroLeiturista
					.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.FUNCIONARIO);

			Collection colecao = fachada.pesquisar(filtroLeiturista,
					Leiturista.class.getName());

			if (colecao != null && !colecao.isEmpty()) {
				Iterator it = colecao.iterator();
				while (it.hasNext()) {
					Leiturista leitu = (Leiturista) it.next();
					DadosLeiturista dadosLeiu = null;
					if (leitu.getFuncionario() != null) {
						dadosLeiu = new DadosLeiturista(leitu.getId(), leitu
								.getFuncionario().getNome());
					} else {
						dadosLeiu = new DadosLeiturista(leitu.getId(), leitu
								.getCliente().getNome());
					}
					colecaoLeiturista.add(dadosLeiu);
				}
			}

		}
		
		sessao.setAttribute("colecaoLeiturista", colecaoLeiturista);
		
		
		String idDigitadoArquivo = form.getIdArquivo();
        
		// Verifica se o c�digo foi digitado
		if (idDigitadoArquivo != null && !idDigitadoArquivo.trim().equals("")) {
			FiltroArquivoTextoAtualizacaoCadastral filtroArquivoTextoAtualizacaoCadastral = new FiltroArquivoTextoAtualizacaoCadastral();

			filtroArquivoTextoAtualizacaoCadastral.adicionarParametro(new ParametroSimples(
					FiltroArquivoTextoAtualizacaoCadastral.ID, idDigitadoArquivo));

			Collection arquivosTextoAtualizacaoCadastral = fachada.pesquisar(filtroArquivoTextoAtualizacaoCadastral,
					ArquivoTextoAtualizacaoCadastral.class.getName());

			if (arquivosTextoAtualizacaoCadastral != null && !arquivosTextoAtualizacaoCadastral.isEmpty()) {
				form.setIdArquivo("" + ((ArquivoTextoAtualizacaoCadastral) ((List) arquivosTextoAtualizacaoCadastral).get(0))
					.getId());
				form.setDescricaoArquivo(((ArquivoTextoAtualizacaoCadastral) ((List) arquivosTextoAtualizacaoCadastral).get(0))
					.getDescricaoArquivo());
				httpServletRequest.setAttribute("idArquivoEncontrado","true");
				//httpServletRequest.setAttribute("nomeCampo","idCliente");

			} else {

				form.setIdArquivo("");
				//httpServletRequest.setAttribute("idClienteNaoEncontrado",
					//	"exception");
				form.setDescricaoArquivo("ARQUIVO INEXISTENTE");
				//httpServletRequest.setAttribute("nomeCampo","DDDTelefone");

			}

		}
		
		Collection colecaoColunaImoveisSelecionados = null;
		
		if(form.getColunaImoveisSelecionados() != null){
			
			String[] aux = form.getColunaImoveisSelecionados();
			
			List aux1 = Arrays.asList(aux);
			colecaoColunaImoveisSelecionados = aux1;
			
			FiltroTabelaColuna filtroTabelaColuna = new FiltroTabelaColuna();
			
			filtroTabelaColuna.adicionarParametro(
					new ParametroSimplesIn(
							FiltroTabelaColuna.ID, colecaoColunaImoveisSelecionados));
			
			filtroTabelaColuna.setCampoOrderBy(FiltroTabelaColuna.DESCRICAO_COLUNA);
			
			// Pesquisa de acordo com os par�metros informados no filtro
			colecaoColunaImoveisSelecionados = Fachada.getInstancia().pesquisar(
					filtroTabelaColuna, TabelaColuna.class.getName());
			
			
			// Verifica se a pesquisa retornou algum objeto para a cole��o
			if (colecaoColunaImoveisSelecionados != null && !colecaoColunaImoveisSelecionados.isEmpty()) {
				sessao.setAttribute("colecaoColunaImoveisSelecionados", colecaoColunaImoveisSelecionados);
				sessao.setAttribute("existeColecaoColunaImoveisSelecionados", colecaoColunaImoveisSelecionados);
			}
		}
		
//		 Monta a colecao 
		this.pesquisarColunaImoveis(httpServletRequest, colecaoColunaImoveisSelecionados);
	
        return retorno;
    }
    
    private void pesquisarColunaImoveis(HttpServletRequest httpServletRequest, Collection colecaoColunaImoveisSelecionados) {

    	HttpSession sessao = httpServletRequest.getSession(false);
    	
    	FiltroTabelaColuna filtroTabelaColuna = new FiltroTabelaColuna();
		
		filtroTabelaColuna.adicionarParametro(
				new ParametroSimples(
						FiltroTabelaColuna.INDICADOR_ATUALIZACAO_CADASRAL, ConstantesSistema.SIM));
		
		filtroTabelaColuna.setConsultaSemLimites(true);
		filtroTabelaColuna.setCampoOrderBy(FiltroTabelaColuna.DESCRICAO_COLUNA);
        
		Collection colecaoColunaImoveis = Fachada.getInstancia().pesquisar(filtroTabelaColuna, TabelaColuna.class.getName());

			if(colecaoColunaImoveisSelecionados == null ){
				sessao.setAttribute("colecaoColunaImoveis",
						colecaoColunaImoveis);
			}else{
				for (Iterator iteratorColunaImoveis = colecaoColunaImoveis.iterator(); iteratorColunaImoveis.hasNext();){
			
					TabelaColuna colunaImoveis = (TabelaColuna) iteratorColunaImoveis.next();
					for (Iterator iteratorColunaImoveisSelecionados = colecaoColunaImoveisSelecionados.iterator(); 
					iteratorColunaImoveisSelecionados.hasNext();){
						
						TabelaColuna colunaImoveisSelecionado = (TabelaColuna) iteratorColunaImoveisSelecionados.next();
						
						if(colunaImoveis.getId().compareTo(colunaImoveisSelecionado.getId()) == 0){
							iteratorColunaImoveis.remove();
						}
					}
				}

				sessao.setAttribute("colecaoColunaImoveis", colecaoColunaImoveis);
			}
//		}
	}
    
}

