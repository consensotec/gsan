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
package gcom.gui.faturamento;

import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoSituacaoComando;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.faturamento.FiltroFaturamentoSituacaoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarSituacaoEspecialFaturamentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirManterSituacaoEspecialFaturamento");
		
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FaturamentoSituacaoComando comandoFiltro = this.validarEGerarFiltro(actionForm);	
		
		Integer totalRegistros = fachada.pesquisarSituacaoEspecialFaturamentoCount(comandoFiltro);
		
		retorno = this.controlarPaginacao(httpServletRequest, retorno, totalRegistros);
		
		Collection<FaturamentoSituacaoComando> colecaoFaturamentoSituacaoComando = fachada.
		pesquisarSituacaoEspecialFaturamento(comandoFiltro,(Integer) 
				httpServletRequest.getAttribute("numeroPaginasPesquisa"));
		
			
		if ( Util.isVazioOrNulo(colecaoFaturamentoSituacaoComando)) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null,"Situa��o Especial de Faturamento");
		}

		sessao.setAttribute("colecaoFaturamentoSituacaoComando",colecaoFaturamentoSituacaoComando);
		sessao.setAttribute("idRegistroAtualizar", colecaoFaturamentoSituacaoComando.iterator().next().getId().toString());

		return retorno;

	}

	/**
	 * Valida todos os campos das inscri��es inicial e final.
	 * Verifica se o destino � >= a origem.
	 *
	 *@since 12/08/2009
	 *@author Marlon Patrick
	 */
	private void validacaoInscricaoOrigemDestino(FiltrarSituacaoEspecialFaturamentoActionForm form) {
	
		validarLocalidadeOrigemDestino(form);
		validarPeriodoOrigemDestino(form);
		/*
		validarSetorComercialOrigemDestino(form);
		validarQuadraOrigemDestino(form);
		validarLoteOrigemDestino(form);
		validarSubloteOrigemDestino(form);
		*/
	}



	/**
	 *Esse m�todo valida se a localidade destino � >= a localidade origem.
	 *
	 *@since 12/08/2009
	 *@author Marlon Patrick
	 */
	private void validarLocalidadeOrigemDestino(
			FiltrarSituacaoEspecialFaturamentoActionForm form) {
		if (Util.verificarNaoVazio(form.getLocalidadeOrigemID())
				&& Util.verificarNaoVazio(form.getLocalidadeDestinoID())) {
				
				int origem = Integer.parseInt(form.getLocalidadeOrigemID());
				int destino = Integer.parseInt(form.getLocalidadeDestinoID());
				
				if (origem > destino){
					throw new ActionServletException
						("atencao.localidade.final.maior.localidade.inicial",null, "");
				}				
		}
	}
	
	/**
	 *Esse m�todo valida se o Periodo destino � >= ao Periodo origem.
	 *
	 *@since 23/03/2010
	 *@author Hugo Fernando
	 */
	private void validarPeriodoOrigemDestino(
			FiltrarSituacaoEspecialFaturamentoActionForm form) {
		
		if (Util.verificarNaoVazio(form.getMesAnoReferenciaFaturamentoInicial())
				&& Util.verificarNaoVazio(form.getMesAnoReferenciaFaturamentoFinal())) {
				
				Integer origem = Util.formatarMesAnoComBarraParaAnoMes(form.getMesAnoReferenciaFaturamentoInicial());
				Integer destino = Util.formatarMesAnoComBarraParaAnoMes(form.getMesAnoReferenciaFaturamentoFinal());
				
				if (origem.intValue() > destino.intValue()){
					throw new ActionServletException
						("atencao.mes.ano.inicial.maior.mes.ano.final",null, "");
				}				
		}
	}

	private FaturamentoSituacaoComando validarEGerarFiltro(ActionForm actionForm){

		FiltrarSituacaoEspecialFaturamentoActionForm situacaoEspecialFaturamentoActionForm = 
			(FiltrarSituacaoEspecialFaturamentoActionForm) actionForm;

		FaturamentoSituacaoComando comandoFiltro = new FaturamentoSituacaoComando();
		
		Fachada fachada = Fachada.getInstancia();
		String idImovel = null;
		String localidadeOrigemID = null;
		String periodoInicial = null;
		String localidadeDestinoID = null;
		String periodoFinal = null;
		String idFaturamentoSituacaoTipo = null;
		
		idImovel = situacaoEspecialFaturamentoActionForm.getIdImovel();
		localidadeOrigemID = situacaoEspecialFaturamentoActionForm.getLocalidadeOrigemID();
		periodoInicial = situacaoEspecialFaturamentoActionForm.getMesAnoReferenciaFaturamentoInicial();
	
		localidadeDestinoID = situacaoEspecialFaturamentoActionForm.getLocalidadeDestinoID();
		periodoFinal = situacaoEspecialFaturamentoActionForm.getMesAnoReferenciaFaturamentoFinal();
		
		idFaturamentoSituacaoTipo = situacaoEspecialFaturamentoActionForm.getIdFaturamentoSituacaoTipo();
	

		boolean peloMenosUmParametroInformado = false;
		
		if (Util.verificarNaoVazio(idImovel)) {
			
			if(fachada.verificarExistenciaImovel(new Integer(idImovel)) <= 0){
				throw new ActionServletException("atencao.matricula.imovel.inexistente");
			}
			peloMenosUmParametroInformado = true;
			comandoFiltro.setImovel(new Imovel(Integer.parseInt(idImovel)));
		}

		if (Util.verificarNaoVazio(localidadeOrigemID)) {
			
			Integer LocalidadeInicial = fachada.verificarExistenciaLocalidade(new Integer(localidadeOrigemID));
			if( LocalidadeInicial == null || LocalidadeInicial <= 0){
				throw new ActionServletException("atencao.localidade.inexistente");
			}
			peloMenosUmParametroInformado = true;
			comandoFiltro.setLocalidadeInicial(new Localidade(Integer.parseInt(localidadeOrigemID)));
		}
       
		if (Util.verificarNaoVazio(periodoInicial)) {
			peloMenosUmParametroInformado = true;
			comandoFiltro.setAnoMesInicialSituacaoFaturamento(Util.formatarMesAnoComBarraParaAnoMes(periodoInicial));
		}


		// campos localidade destino
		if (Util.verificarNaoVazio(localidadeDestinoID)) {

			if( !localidadeDestinoID.equals(localidadeOrigemID)){
		Integer localidadeFinal = fachada.verificarExistenciaLocalidade(new Integer(localidadeDestinoID));
				if( localidadeFinal == null || localidadeFinal <= 0){
					throw new ActionServletException("atencao.localidade.inexistente");
				}
			}

			peloMenosUmParametroInformado = true;
			
			comandoFiltro.setLocalidadeFinal(new Localidade(Integer.parseInt(localidadeDestinoID)));
		}
		
		if (Util.verificarNaoVazio(periodoFinal)) {
			peloMenosUmParametroInformado = true;
			comandoFiltro.setAnoMesFinalSituacaoFaturamento(Util.formatarMesAnoComBarraParaAnoMes(periodoFinal));
		}

        
		if (Util.verificarNaoVazio(idFaturamentoSituacaoTipo) && !idFaturamentoSituacaoTipo.equals("-1")) {
			
			FiltroFaturamentoSituacaoTipo filtroFaturamentoSituacaoTipo = new FiltroFaturamentoSituacaoTipo();

			filtroFaturamentoSituacaoTipo.adicionarParametro(new ParametroSimples(FiltroFaturamentoSituacaoTipo.ID,
					idFaturamentoSituacaoTipo));
			
			Collection colecaoFatSituacaoTipo = this.getFachada().pesquisar(
					filtroFaturamentoSituacaoTipo,
					FaturamentoSituacaoTipo.class.getName());
			
			FaturamentoSituacaoTipo faturamentoSituacaoTipo = (FaturamentoSituacaoTipo) Util.retonarObjetoDeColecao(colecaoFatSituacaoTipo);
			
			if(faturamentoSituacaoTipo != null){
				peloMenosUmParametroInformado = true;
				comandoFiltro.setFaturamentoSituacaoTipo(faturamentoSituacaoTipo);	
			}
		}


		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");

		}
		
		this.validacaoInscricaoOrigemDestino(situacaoEspecialFaturamentoActionForm);
		
		return comandoFiltro;
	}
	
}
