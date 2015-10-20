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
package gcom.gui.atendimentopublico.registroatendimento;


import gcom.atendimentopublico.ligacaoagua.FiltroDiametroLigacao;
import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.FiltroMaterialLigacao;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.registroatendimento.EspecificacaoImovSitCriterio;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action respons�vel pela pr�-exibi��o da p�gina de Inserir Especifica�ao Situa��o
 * 
 * @author Rafael Pinto
 * @created 03/08/2006
 */
public class ExibirInserirEspecificacaoSituacaoImovelAction extends GcomAction {
	
	/**
	 * Exibi��o de inser��o de equipe.
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta Retorno (Forward = Exibi��o da Tela de Inser��o)
		ActionForward retorno = actionMapping.findForward("inserirEspecificacaoSituacaoImovel");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		// Form
		InserirEspecificacaoSituacaoImovelActionForm inserirEspecificacaoSituacaoImovelActionForm = 
			(InserirEspecificacaoSituacaoImovelActionForm) actionForm;
		
		// Testa se � pra remover especifica��o situacao criterio imovel
		if (inserirEspecificacaoSituacaoImovelActionForm.getDeleteSituacaoCriterioImovel() != null && 
			!inserirEspecificacaoSituacaoImovelActionForm.getDeleteSituacaoCriterioImovel().equals("")) {
			
			removeSituacaoCriterioImovel(inserirEspecificacaoSituacaoImovelActionForm);
			
		} else if(!inserirEspecificacaoSituacaoImovelActionForm.getMethod().equals("")){

			retorno = actionMapping.findForward("inserirEspecificacaoSituacaoCriterioImovel");			
			
			this.consultaSelectObrigatorio(sessao);
			
			if ((inserirEspecificacaoSituacaoImovelActionForm.getSituacaoLigacaoAgua() != null 
					&& !inserirEspecificacaoSituacaoImovelActionForm.getSituacaoLigacaoAgua().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) 
					|| (inserirEspecificacaoSituacaoImovelActionForm.getSituacaoLigacaoEsgoto() != null 
					&& !inserirEspecificacaoSituacaoImovelActionForm.getSituacaoLigacaoEsgoto().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO))
					|| (inserirEspecificacaoSituacaoImovelActionForm.getIndicadorHidrometroLigacaoAgua() != null 
					&& !inserirEspecificacaoSituacaoImovelActionForm.getIndicadorHidrometroLigacaoAgua().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO))					
					|| (inserirEspecificacaoSituacaoImovelActionForm.getIndicadorHidrometroPoco() != null 
					&& !inserirEspecificacaoSituacaoImovelActionForm.getIndicadorHidrometroPoco().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) ){


				// Cria objeto EspecificacaoImovSitCriterio
				EspecificacaoImovSitCriterio especificacaoImovSitCriterio = null;

				// Recupera especificao imovel sit criterio vindo do popup
				especificacaoImovSitCriterio = 
					getEspecificacaoImovSitCriterio(inserirEspecificacaoSituacaoImovelActionForm,sessao);
				
				// Reseta informa��es vindas do popup
				resetPopup(inserirEspecificacaoSituacaoImovelActionForm);
				
				// Faz as valida��es de inser��o de especificao situacao imovel
				fachada.validarExibirInsercaoEspecificacaoImovSitCriterio(
					inserirEspecificacaoSituacaoImovelActionForm.getEspecificacaoImovelSituacaoCriterio(), 
						especificacaoImovSitCriterio);
				
				// Seta componente na cole��o
				setColecaoEspecificacaoImovSitCriterio(inserirEspecificacaoSituacaoImovelActionForm, 
					especificacaoImovSitCriterio);
				
				// Seta retorno
				retorno = actionMapping.findForward("inserirEspecificacaoSituacaoImovel");
			}
			
			
			inserirEspecificacaoSituacaoImovelActionForm.setMethod("");
		}

		return retorno;
	}

	/**
	 * Seta nova EspecificacaoImovSitCriterio na Cole��o 
	 *
	 * @author Rafael Pinto
	 * @date 03/08/2006
	 *
	 * @param inserirEquipeActionForm
	 * @param especificacaoImovSitCriterio
	 */
	private void setColecaoEspecificacaoImovSitCriterio(InserirEspecificacaoSituacaoImovelActionForm inserirEspecificacaoSituacaoImovelActionForm, 
			EspecificacaoImovSitCriterio especificacaoImovSitCriterio) {
		
		
		inserirEspecificacaoSituacaoImovelActionForm.getEspecificacaoImovelSituacaoCriterio().add(especificacaoImovSitCriterio);
		inserirEspecificacaoSituacaoImovelActionForm.setTamanhoColecao(
			inserirEspecificacaoSituacaoImovelActionForm.getEspecificacaoImovelSituacaoCriterio().size()+"");
	}

	/**
	 * Recupera objeto Especificacao Imovel Situacao Criterio com informa��es vindas da tela 
	 *
	 * @author Rafael Pinto
	 * @date 04/08/2006
	 *
	 * @param InserirEspecificacaoSituacaoImovelActionForm
	 */
	private EspecificacaoImovSitCriterio getEspecificacaoImovSitCriterio(
		InserirEspecificacaoSituacaoImovelActionForm inserirActionForm,HttpSession sessao) {
		
		EspecificacaoImovSitCriterio especificacaoImovSitCriterio = new EspecificacaoImovSitCriterio();
		
		if(inserirActionForm.getIndicadorHidrometroLigacaoAgua() != null &&
				!inserirActionForm.getIndicadorHidrometroLigacaoAgua().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			especificacaoImovSitCriterio.setIndicadorHidrometroLigacaoAgua(
				new Short(inserirActionForm.getIndicadorHidrometroLigacaoAgua()));	
		}
		
		if(inserirActionForm.getIndicadorHidrometroPoco() != null &&
				!inserirActionForm.getIndicadorHidrometroPoco().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
		
			especificacaoImovSitCriterio.setIndicadorHidrometroPoco(
				new Short(inserirActionForm.getIndicadorHidrometroPoco()));
		}		
		
		especificacaoImovSitCriterio.setUltimaAlteracao(new Date());

		if(inserirActionForm.getSituacaoLigacaoAgua() != null &&
				!inserirActionForm.getSituacaoLigacaoAgua().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			Integer idSituacaoLigacaoAgua = new Integer(inserirActionForm.getSituacaoLigacaoAgua());
			
			LigacaoAguaSituacao ligacaoAguaSituacao = this.retornaLigacaoAguaSituacao(sessao,idSituacaoLigacaoAgua);
			
			especificacaoImovSitCriterio.setLigacaoAguaSituacao(ligacaoAguaSituacao);
		}		
		
		if(inserirActionForm.getSituacaoLigacaoEsgoto() != null &&
				!inserirActionForm.getSituacaoLigacaoEsgoto().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){

			Integer idSituacaoLigacaoEsgoto = new Integer(inserirActionForm.getSituacaoLigacaoEsgoto());
			
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao = 
				this.retornaLigacaoEsgotoSituacao(sessao,idSituacaoLigacaoEsgoto);
			
			especificacaoImovSitCriterio.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);
			
		}		
		
		return especificacaoImovSitCriterio;
	}

	//retorna LigacaoAguaSituacao pelo id na colecao de ligacaoAguaSituacao	
	private LigacaoAguaSituacao retornaLigacaoAguaSituacao(HttpSession sessao,Integer id){

		LigacaoAguaSituacao retorno = null;

		Collection colecao = (Collection) sessao.getAttribute("colecaoSituacaoLigacaoAgua");
		
		if(colecao != null && !colecao.isEmpty()){
			Iterator itera = colecao.iterator();
			
			while (itera.hasNext()) {
				LigacaoAguaSituacao ligacaoAguaSituacao = (LigacaoAguaSituacao) itera.next();
				
				if(ligacaoAguaSituacao.getId().intValue() == id.intValue()){
					retorno = ligacaoAguaSituacao;
					break;
				}
			}
		}

		return retorno;
	}

	//retorna LigacaoEsgotoSituacao pelo id na colecao de ligacaoEsgotoSituacao	
	private LigacaoEsgotoSituacao retornaLigacaoEsgotoSituacao(HttpSession sessao,Integer id){

		LigacaoEsgotoSituacao retorno = null;

		Collection colecao = (Collection) sessao.getAttribute("colecaoSituacaoLigacaoEsgoto");
		
		if(colecao != null && !colecao.isEmpty()){
			Iterator itera = colecao.iterator();
			
			while (itera.hasNext()) {
				LigacaoEsgotoSituacao ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) itera.next();
				
				if(ligacaoEsgotoSituacao.getId().intValue() == id.intValue()){
					retorno = ligacaoEsgotoSituacao;
					break;
				}
			}
		}

		return retorno;
	}

	/**
	 * Reseta informa��es vindas do popup 
	 *
	 * @author Rafael Pinto
	 * @date 03/08/2006
	 *
	 * @param inserirEspecificacaoSituacaoImovelActionForm
	 */
	private void resetPopup(InserirEspecificacaoSituacaoImovelActionForm inserirEspecificacaoSituacaoImovelActionForm) {

		inserirEspecificacaoSituacaoImovelActionForm.setIndicadorHidrometroLigacaoAgua(null);
		inserirEspecificacaoSituacaoImovelActionForm.setIndicadorHidrometroPoco(null);
		inserirEspecificacaoSituacaoImovelActionForm.setSituacaoLigacaoAgua(null);
		inserirEspecificacaoSituacaoImovelActionForm.setSituacaoLigacaoEsgoto(null);
		
	}

	/**
	 * Remove Situacao Criterio Imovel da Cole��o 
	 *
	 * @author Rafael Pinto
	 * @date 03/08/2006
	 *
	 * @param InserirEspecificacaoSituacaoImovelActionForm
	 */
	private void removeSituacaoCriterioImovel(InserirEspecificacaoSituacaoImovelActionForm inserirEspecificacaoSituacaoImovelActionForm) {
		
		Collection newSituacaoCriterioImovelCollection = new ArrayList();
		
		int index = 0;
		
		for (Iterator iter = inserirEspecificacaoSituacaoImovelActionForm.getEspecificacaoImovelSituacaoCriterio().iterator(); 
			iter.hasNext();) {
			
			index++;
			
			EspecificacaoImovSitCriterio element = (EspecificacaoImovSitCriterio) iter.next();
			
			if (index != new Integer(inserirEspecificacaoSituacaoImovelActionForm.getDeleteSituacaoCriterioImovel()).intValue()) {
				newSituacaoCriterioImovelCollection.add(element);
			}
		}
		inserirEspecificacaoSituacaoImovelActionForm.setEspecificacaoImovelSituacaoCriterio(newSituacaoCriterioImovelCollection);
		inserirEspecificacaoSituacaoImovelActionForm.setTamanhoColecao(inserirEspecificacaoSituacaoImovelActionForm.getEspecificacaoImovelSituacaoCriterio().size()+"");
		inserirEspecificacaoSituacaoImovelActionForm.setDeleteSituacaoCriterioImovel("");
		
		resetPopup(inserirEspecificacaoSituacaoImovelActionForm);
	}
	
	private void consultaSelectObrigatorio(HttpSession sessao){
		
		Fachada fachada = Fachada.getInstancia();
		
		// Filtro para o campo Situa��o Liga��o �gua
		Collection colecaoSituacaoLigacaoAgua = (Collection) 
			sessao.getAttribute("colecaoSituacaoLigacaoAgua");

		if(colecaoSituacaoLigacaoAgua == null){

			FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();

			filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
					FiltroLigacaoAguaSituacao.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroLigacaoAguaSituacao.setCampoOrderBy(FiltroDiametroLigacao.DESCRICAO);

			colecaoSituacaoLigacaoAgua = 
				fachada.pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());

			if (colecaoSituacaoLigacaoAgua != null && !colecaoSituacaoLigacaoAgua.isEmpty()) {
				sessao.setAttribute("colecaoSituacaoLigacaoAgua",colecaoSituacaoLigacaoAgua);
			} else {
				throw new ActionServletException("atencao.naocadastrado",null, "Situa��o Liga��o de Agua");
			}
		}	

		// Filtro para o campo Situacao da Liga��o Esgoto
		Collection colecaoSituacaoLigacaoEsgoto = (Collection) 
			sessao.getAttribute("colecaoSituacaoLigacaoEsgoto");

		if(colecaoSituacaoLigacaoEsgoto == null){

			FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
			
			filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
					FiltroLigacaoEsgotoSituacao.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroLigacaoEsgotoSituacao.setCampoOrderBy(FiltroMaterialLigacao.DESCRICAO);

			colecaoSituacaoLigacaoEsgoto = 
				fachada.pesquisar(filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName());

			if (colecaoSituacaoLigacaoEsgoto != null && !colecaoSituacaoLigacaoEsgoto.isEmpty()) {
				sessao.setAttribute("colecaoSituacaoLigacaoEsgoto",colecaoSituacaoLigacaoEsgoto);
			} else {
				throw new ActionServletException("atencao.naocadastrado",null, "Situa��o Liga��o de Esgoto");
			}
		}
	}
	
}