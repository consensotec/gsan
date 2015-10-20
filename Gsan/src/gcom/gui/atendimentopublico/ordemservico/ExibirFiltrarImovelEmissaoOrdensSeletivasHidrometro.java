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
* Ivan S�rgio Virginio da Silva J�nior
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

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroCapacidade;
import gcom.micromedicao.hidrometro.FiltroHidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.FiltroHidrometroMarca;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.HidrometroMarca;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirFiltrarImovelEmissaoOrdensSeletivasHidrometro extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("filtrarImovelEmissaoOrdensSeletivasHidrometro");
		
		//obt�m a inst�ncia da sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

		ImovelEmissaoOrdensSeletivasActionForm imovelEmissaoOrdensSeletivas = 
			(ImovelEmissaoOrdensSeletivasActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		 
		Collection<HidrometroCapacidade> collectionHidrometroCapacidade = null;
		Collection<HidrometroMarca> collectionHidrometroMarca = null;
		Collection<LeituraAnormalidade> collectionHidrometroAnormalidade = null;
		Collection<HidrometroLocalInstalacao> collectionHidrometroLocalInstalacao = null;
		
		if(imovelEmissaoOrdensSeletivas.getIdImovel() != null
				&& !imovelEmissaoOrdensSeletivas.getIdImovel().equals("")){
			
			httpServletRequest.setAttribute("desabilitarCampos", "ok");
		}else{
			if (httpServletRequest.getAttribute("desabilitarCampos") != null){
				httpServletRequest.removeAttribute("desabilitarCampos");
			}
		}
		
		if (imovelEmissaoOrdensSeletivas.getTipoOrdem().
				equalsIgnoreCase(ImovelEmissaoOrdensSeletivasActionForm.TIPO_ORDEM_INSTALACAO)) {
			
			FiltrarImovelEmissaoOrdensSeletivasWizardAction filtrar = new FiltrarImovelEmissaoOrdensSeletivasWizardAction();
			
			if (sessao.getAttribute("abaAtual") == "PARAMETROS") {
				retorno = actionMapping.findForward("filtrarImovelEmissaoOrdensSeletivasCaracteristicas");
				filtrar.exibirFiltrarImovelEmissaoOrdensSeletivasCaracteristicas(actionMapping, actionForm,
						httpServletRequest, httpServletResponse);
			}else if (sessao.getAttribute("abaAtual") == "CARACTERISTICAS") {
				retorno = actionMapping.findForward("filtrarImovelEmissaoOrdensSeletivasParametros");
				filtrar.exibirFiltrarImovelEmissaoOrdensSeletivasParametros(actionMapping, actionForm,
						httpServletRequest, httpServletResponse);
			}
			
		}else {
		
			// Lista Capacidade
			if (imovelEmissaoOrdensSeletivas.getCapacidade() == null) {
				FiltroHidrometroCapacidade filtroHidrometroCapacidade = new FiltroHidrometroCapacidade();
				filtroHidrometroCapacidade.adicionarParametro(new ParametroSimples(
						FiltroHidrometroCapacidade.INDICADOR_USO, 1));
				filtroHidrometroCapacidade.setCampoOrderBy(FiltroHidrometroCapacidade.DESCRICAO);
				collectionHidrometroCapacidade = fachada.pesquisar(filtroHidrometroCapacidade,
						HidrometroCapacidade.class.getName());
				
				if(collectionHidrometroCapacidade == null || collectionHidrometroCapacidade.isEmpty()) {
					throw new ActionServletException("atencao.naocadastrado", null, "Capacidade Hidr�metro");			
				}
				
				sessao.setAttribute("collectionHidrometroCapacidade", collectionHidrometroCapacidade);
			}
			
			// Lista Marca
			if (imovelEmissaoOrdensSeletivas.getMarca() == null) {
				FiltroHidrometroMarca filtroHidrometroMarca = new FiltroHidrometroMarca();
				filtroHidrometroMarca.adicionarParametro(new ParametroSimples(
						FiltroHidrometroMarca.INDICADOR_USO, 1));
				filtroHidrometroMarca.setCampoOrderBy(FiltroHidrometroMarca.DESCRICAO);
				
				collectionHidrometroMarca = fachada.pesquisar(filtroHidrometroMarca,
						HidrometroMarca.class.getName());
				
				if(collectionHidrometroMarca == null || collectionHidrometroMarca.isEmpty()){
					throw new ActionServletException("atencao.naocadastrado", null, "Marca do Hidr�metro");			
				}
				
				sessao.setAttribute("collectionHidrometroMarca", collectionHidrometroMarca);
			}
			
			//Lista Local de Instalacao
			if(imovelEmissaoOrdensSeletivas.getLocalInstalacao() == null){
				
				FiltroHidrometroLocalInstalacao filtroHidrometroLocalInstalacao = 
					new FiltroHidrometroLocalInstalacao();
				// Retirado por Romulo Aurelio, Analista: Ana Cristina
				// CRC 
				// Data: 30/08/2010  
				/*filtroHidrometroLocalInstalacao.adicionarParametro( new ParametroSimples(
						FiltroHidrometroLocalInstalacao.INDICADOR_USO, 1));*/
				filtroHidrometroLocalInstalacao.setCampoOrderBy(FiltroHidrometroLocalInstalacao.DESCRICAO);
				
				collectionHidrometroLocalInstalacao = fachada.pesquisar(filtroHidrometroLocalInstalacao,
						HidrometroLocalInstalacao.class.getName());
				
				if(collectionHidrometroLocalInstalacao == null || collectionHidrometroLocalInstalacao.isEmpty()){
					throw new ActionServletException("atencao.naocadastrado", null, "Local Instala��o Hidr�metro");
				}
				
				sessao.setAttribute("collectionHidrometroLocalInstalacao", collectionHidrometroLocalInstalacao);
			}
			
			// Lista Hidrometro Anormalidade
			if (imovelEmissaoOrdensSeletivas.getAnormalidadeHidrometro() == null) {
				FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();
				
				if(imovelEmissaoOrdensSeletivas.getTipoOrdem() != null && 
				(imovelEmissaoOrdensSeletivas.getTipoOrdem().equals(ImovelEmissaoOrdensSeletivasActionForm.TIPO_ORDEM_SUBSTITUICAO) ||						
				imovelEmissaoOrdensSeletivas.getTipoOrdem().equals(ImovelEmissaoOrdensSeletivasActionForm.TIPO_ORDEM_REMOCAO))){
					//Caso o tipo de ordem selecionado corresponda a �SUBSTITUI��O HIDROMETRO� 
					//ou �REMO��O HIDROMETRO�, selecionar apenas  anormalidades com LTAN_ICRELATIVOHIDROMETRO=1 
					filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
							FiltroLeituraAnormalidade.INDICADOR_RELATIVO_HIDROMETRO, 1));
				}
				filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
						FiltroLeituraAnormalidade.INDICADOR_USO, 1));
				
				filtroLeituraAnormalidade.setCampoOrderBy(FiltroLeituraAnormalidade.DESCRICAO);
				
				collectionHidrometroAnormalidade = fachada.pesquisar(filtroLeituraAnormalidade,
						LeituraAnormalidade.class.getName());
				
				if(collectionHidrometroAnormalidade == null || collectionHidrometroAnormalidade.isEmpty()){
					throw new ActionServletException("atencao.naocadastrado", null, "Anormalidade de Hidr�metro");			
				}
				
				sessao.setAttribute("collectionHidrometroAnormalidade", collectionHidrometroAnormalidade);
			}
		}
		
		// Usado para fazer o controle de navegacao por conta da Aba Local 
		//sessao.setAttribute("abaAtual", "HIDROMETRO");
		
		if (imovelEmissaoOrdensSeletivas.getTipoOrdem() != null) {
			httpServletRequest.setAttribute("tipoOrdem", imovelEmissaoOrdensSeletivas.getTipoOrdem());
		}
		
		return retorno;
	}
}