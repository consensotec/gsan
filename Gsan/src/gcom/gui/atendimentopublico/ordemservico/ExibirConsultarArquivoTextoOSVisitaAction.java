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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * [UCXXXX] Consultar Arquivo Ordens de Servi�o de Visita
 * 
 * @author Hugo Azevedo
 *
 * @date 15/09/2011
 */
public class ExibirConsultarArquivoTextoOSVisitaAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, 
		HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirConsultarDadosOSVisita");
		
		// Form
		ConsultarArquivoTextoOSVisitaActionForm 
			form = (ConsultarArquivoTextoOSVisitaActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//==============================
		//Cole��es do formul�rio
		//==============================
		
		
		//Situa��o do Arquivo Texto
		Collection colecaoSituacaoArquivoTexto = fachada.obterColecaoSituacaoArquivoTexto();
		sessao.setAttribute("colecaoSituacaoArquivoTexto", colecaoSituacaoArquivoTexto);
		
		//Agente Comercial
		Collection colecaoAgenteComercial = fachada.obterColecaoAgenteComercial();
		sessao.setAttribute("colecaoLeiturista", colecaoAgenteComercial);
		
		//Tratamento das buscas atrav�s do enter
		//=================================================
		
		form.setDescricaoQuadraInicial("");
		form.setDescricaoQuadraFinal("");
				
		//Localidade
		String pesquisarLocalidade = httpServletRequest.getParameter("pesquisarLocalidade");
		if(pesquisarLocalidade != null && !"".equals(pesquisarLocalidade)){
			Integer idLocalidade = new Integer(form.getIdLocalidade());
			Localidade localidade = fachada.pesquisarLocalidadeDigitada(idLocalidade);
			
			//Apagando a cole��o, para uma nova busca
			sessao.removeAttribute("colecaoArquivoTextoOSVisita");
			
			if(localidade != null){
				form.setDescricaoLocalidade(localidade.getDescricao());
				sessao.setAttribute("localidadeArquivoTxt", localidade);
			}
			else{
				sessao.removeAttribute("localidadeArquivoTxt");
				form.setDescricaoLocalidade("LOCALIDADE INEXISTENTE");
				form.setIdLocalidade("");
				form.setIdSetorComercialInicial("");
				form.setDescricaoSetorComercialInicial("");
				form.setIdSetorComercialFinal("");
				form.setDescricaoSetorComercialFinal("");
				form.setIdQuadraInicial("");
				form.setDescricaoQuadraInicial("");
				form.setIdQuadraFinal("");
				form.setDescricaoQuadraFinal("");
				httpServletRequest.setAttribute("localidadeException","ok");
			}
			
		}
		
		//Setor Comercial Inicial
		String pesquisarSetorComercialInicial = httpServletRequest.getParameter("pesquisarSetorComercialInicial");
		if(pesquisarSetorComercialInicial != null && !"".equals(pesquisarSetorComercialInicial)){
			
			String idSetorComercial = form.getIdSetorComercialInicial();
			String idLocalidadeInicial = form.getIdLocalidade();
			Localidade localidade = (Localidade) sessao.getAttribute("localidadeArquivoTxt");
			
			SetorComercial setorComercialInicial = fachada.obterSetorComercialCodigo(idSetorComercial);
			
			if(setorComercialInicial != null){
				
				setorComercialInicial = null;
				setorComercialInicial = fachada.obterSetorComercialLocalidade(idLocalidadeInicial, idSetorComercial);
				
				if(setorComercialInicial != null){
					form.setDescricaoSetorComercialInicial(setorComercialInicial.getDescricao());
					form.setDescricaoSetorComercialFinal(setorComercialInicial.getDescricao());
					form.setIdSetorComercialFinal(idSetorComercial.toString());
					sessao.setAttribute("setorComercialInicial", setorComercialInicial);
					sessao.removeAttribute("setorComercialInicialException");
				}
				else{
					form.setIdSetorComercialInicial("");
					form.setIdQuadraInicial("");
					form.setDescricaoQuadraInicial("");
					form.setIdSetorComercialFinal("");
					form.setDescricaoSetorComercialFinal("");
					form.setIdQuadraFinal("");
					form.setDescricaoQuadraFinal("");
					sessao.setAttribute("setorComercialInicialException","ok");
					sessao.removeAttribute("setorComercialInicial");
					throw new ActionServletException("atencao.setor_nao_pertence_localidade",localidade.getDescricao());
				}
			}
			else{
				form.setDescricaoSetorComercialInicial("SETOR COMERCIAL INEXISTENTE");
				form.setIdSetorComercialInicial("");
				form.setIdQuadraInicial("");
				form.setDescricaoQuadraInicial("");
				form.setIdSetorComercialFinal("");
				form.setDescricaoSetorComercialFinal("");
				form.setIdQuadraFinal("");
				form.setDescricaoQuadraFinal("");
				sessao.setAttribute("setorComercialInicialException","ok");
				sessao.removeAttribute("setorComercialInicial");
			}
			
			//Apagando a cole��o, para uma nova busca
			sessao.removeAttribute("colecaoArquivoTextoOSVisita");
			
		}
		
		
		//Setor Comercial Final
		String pesquisarSetorComercialFinal = httpServletRequest.getParameter("pesquisarSetorComercialFinal");
		if(pesquisarSetorComercialFinal != null && !"".equals(pesquisarSetorComercialFinal)){
			
			String idSetorComercial = form.getIdSetorComercialFinal();
			Localidade localidade = (Localidade) sessao.getAttribute("localidadeArquivoTxt");
			String idLocalidadeFinal = form.getIdLocalidade();
			
			SetorComercial setorComercialFinal = fachada.obterSetorComercialCodigo(idSetorComercial);
			
			if(setorComercialFinal != null){
				
				setorComercialFinal = null;
				setorComercialFinal = fachada.obterSetorComercialLocalidade(idLocalidadeFinal, idSetorComercial);
				
				if(setorComercialFinal != null){
					form.setDescricaoSetorComercialFinal(setorComercialFinal.getDescricao());
					sessao.removeAttribute("setorComercialFinalException");
				}
				else{
					form.setIdSetorComercialFinal("");
					form.setIdQuadraFinal("");
					form.setDescricaoQuadraFinal("");
					sessao.setAttribute("setorComercialFinalException","ok");
					throw new ActionServletException("atencao.setor_nao_pertence_localidade",localidade.getDescricao());
				}
				
			}
			else{
				form.setDescricaoSetorComercialFinal("SETOR COMERCIAL INEXISTENTE");
				form.setIdSetorComercialFinal("");
				form.setIdQuadraFinal("");
				form.setDescricaoQuadraFinal("");
				sessao.setAttribute("setorComercialFinalException","ok");
			}
			
			//Apagando a cole��o, para uma nova busca
			sessao.removeAttribute("colecaoArquivoTextoOSVisita");
			
		}
		
		//Quadra Inicial
		String pesquisarQuadraInicial = httpServletRequest.getParameter("pesquisarQuadraInicial");
		if(pesquisarQuadraInicial != null && !"".equals(pesquisarQuadraInicial)){
			
			SetorComercial setorComercialInicial = (SetorComercial)sessao.getAttribute("setorComercialInicial");
			String idQuadraInicial = form.getIdQuadraInicial();
			Localidade localidade = (Localidade) sessao.getAttribute("localidadeArquivoTxt");
			
			Quadra quadraInicial = fachada.obterQuadraNumero(idQuadraInicial);
				
			if(quadraInicial != null){
				
				quadraInicial = null;
				
				if(setorComercialInicial != null){
					int idSetorComercialInicial = setorComercialInicial.getId();
					quadraInicial = fachada.obterQuadraSetorComercial(idSetorComercialInicial, Integer.parseInt(idQuadraInicial));
				}
				else if(form.getIdSetorComercialInicial() != null){
					setorComercialInicial = fachada.obterSetorComercialLocalidade(localidade.getId().toString(), form.getIdSetorComercialInicial());
					int idSetorComercialInicial = setorComercialInicial.getId();
					quadraInicial = fachada.obterQuadraSetorComercial(idSetorComercialInicial, Integer.parseInt(idQuadraInicial));
				}
				
				if(quadraInicial != null){
					httpServletRequest.setAttribute("quadraInicial",quadraInicial);
					form.setDescricaoQuadraInicial("");
					form.setIdQuadraFinal(form.getIdQuadraInicial());
				}
				else{
					form.setIdQuadraInicial("");
					form.setIdQuadraFinal("");
					form.setDescricaoQuadraFinal("");
					throw new ActionServletException("atencao.quadra_nao_pertence_setor_comercial",form.getIdSetorComercialInicial());
				}	
				
			}
			else{
				form.setDescricaoQuadraInicial("QUADRA INEXISTENTE");
				form.setIdQuadraInicial("");
				form.setIdQuadraFinal("");
				form.setDescricaoQuadraFinal("");
			}
			
			//Apagando a cole��o, para uma nova busca
			sessao.removeAttribute("colecaoArquivoTextoOSVisita");
			
		}
		
		//Quadra Final
		String pesquisarQuadraFinal = httpServletRequest.getParameter("pesquisarQuadraFinal");
		if(pesquisarQuadraFinal != null && !"".equals(pesquisarQuadraFinal)){
			
			SetorComercial setorComercialInicial = (SetorComercial)sessao.getAttribute("setorComercialInicial");
			String idQuadraFinal = form.getIdQuadraFinal();
			Localidade localidade = (Localidade) sessao.getAttribute("localidadeArquivoTxt");
			
			Quadra quadraFinal = fachada.obterQuadraNumero(idQuadraFinal);
			
			if(quadraFinal != null){
				
				quadraFinal = null;
				
				if(setorComercialInicial != null){
					int idSetorComercialFinal = setorComercialInicial.getId();
					quadraFinal = fachada.obterQuadraSetorComercial(idSetorComercialFinal, Integer.parseInt(idQuadraFinal));
				}
				else if(form.getIdSetorComercialFinal() != null){
						setorComercialInicial = fachada.obterSetorComercialLocalidade(localidade.getId().toString(), form.getIdSetorComercialFinal());
						int idSetorComercialInicial = setorComercialInicial.getId();
						quadraFinal = fachada.obterQuadraSetorComercial(idSetorComercialInicial, Integer.parseInt(idQuadraFinal));
					}
				
				if(quadraFinal != null){
					httpServletRequest.setAttribute("quadraFinal",quadraFinal);
				}
				else{
					form.setIdQuadraFinal("");
					throw new ActionServletException("atencao.quadra_nao_pertence_setor_comercial",form.getIdSetorComercialFinal());
				}
				
			}
			else{
				form.setDescricaoQuadraFinal("QUADRA INEXISTENTE");
				form.setIdQuadraFinal("");
			}
			
			//Apagando a cole��o, para uma nova busca
			sessao.removeAttribute("colecaoArquivoTextoOSVisita");
			
		}
		
		//Limpar filtro
		String limpar = httpServletRequest.getParameter("limpar");
		if(limpar != null && !"".equals(limpar)){
			//Apagando a cole��o, para uma nova busca
			sessao.removeAttribute("colecaoArquivoTextoOSVisita");
			//Indice para apontar qual grupo de apagar foi selecionado
			Integer limparIndice = Integer.parseInt((String)httpServletRequest.getParameter("indice"));
			switch(limparIndice){
			//Localidade
			case 1:
				form.setIdLocalidade("");
				form.setDescricaoLocalidade("");
				form.setIdSetorComercialInicial("");
				form.setDescricaoSetorComercialInicial("");
				form.setIdSetorComercialFinal("");
				form.setDescricaoSetorComercialFinal("");
				form.setIdQuadraInicial("");
				form.setDescricaoQuadraInicial("");
				form.setIdQuadraFinal("");
				form.setDescricaoQuadraFinal("");
				break;
			//Setor Comercial Inicial
			case 2:
				form.setIdSetorComercialInicial("");
				form.setDescricaoSetorComercialInicial("");
				form.setIdQuadraInicial("");
				form.setDescricaoQuadraInicial("");
				form.setIdSetorComercialFinal("");
				form.setDescricaoSetorComercialFinal("");
				form.setIdQuadraFinal("");
				form.setDescricaoQuadraFinal("");
				break;
			//Setor Comercial Final
			case 3:
				form.setIdQuadraInicial("");
				form.setDescricaoQuadraInicial("");
				form.setIdSetorComercialFinal("");
				form.setDescricaoSetorComercialFinal("");
				form.setIdQuadraFinal("");
				form.setDescricaoQuadraFinal("");
				break;
			}
		}
		
		
		return retorno;
	}
}