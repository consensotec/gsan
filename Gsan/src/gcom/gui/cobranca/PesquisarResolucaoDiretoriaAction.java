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
package gcom.gui.cobranca;

import gcom.cobranca.FiltroResolucaoDiretoria;
import gcom.cobranca.ResolucaoDiretoria;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Permite pesquisar resolu��es de diretoria 
 * [UC0223] Pesquisar Resolu��o de Diretoria
 * 
 * @author Vivianne Sousa
 * @since 19/04/2006
 */
public class PesquisarResolucaoDiretoriaAction extends GcomAction {

	/**
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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("pesquisarResolucaoDiretoriaAction");
		
		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		PesquisarResolucaoDiretoriaActionForm pesquisarResolucaoDiretoriaActionForm = (PesquisarResolucaoDiretoriaActionForm)actionForm;
		
		String numeroResolucaoDiretoria = pesquisarResolucaoDiretoriaActionForm.getNumeroResolucaoDiretoria();
		String dataInicioVigencia = pesquisarResolucaoDiretoriaActionForm.getDataInicioVigencia();
		String dataFimVigencia = pesquisarResolucaoDiretoriaActionForm.getDataFimVigencia();

		validacaoFinal(pesquisarResolucaoDiretoriaActionForm);
		
		FiltroResolucaoDiretoria filtroResolucaoDiretoria = new FiltroResolucaoDiretoria();

		filtroResolucaoDiretoria.setCampoOrderBy(FiltroResolucaoDiretoria.NUMERO);
		
		boolean peloMenosUmParametroInformado = false;
		
		if (numeroResolucaoDiretoria != null && (!numeroResolucaoDiretoria.equalsIgnoreCase(""))){
			peloMenosUmParametroInformado = true;		
			filtroResolucaoDiretoria.adicionarParametro(new ParametroSimples(
					FiltroResolucaoDiretoria.NUMERO, numeroResolucaoDiretoria));
		}
		
		if (dataInicioVigencia != null && (!dataInicioVigencia.equalsIgnoreCase(""))){
			peloMenosUmParametroInformado = true;
			Date dataInicioVigenciaDate = Util.converteStringParaDate(dataInicioVigencia);
			filtroResolucaoDiretoria.adicionarParametro(new ParametroSimples(
					FiltroResolucaoDiretoria.DATA_VIGENCIA_INICIO, dataInicioVigenciaDate));
		}
		
		if (dataFimVigencia != null && (!dataFimVigencia.equalsIgnoreCase(""))){
			peloMenosUmParametroInformado = true;
			Date dataFimVigenciaDate = Util.converteStringParaDate(dataFimVigencia);
			filtroResolucaoDiretoria.adicionarParametro(new ParametroSimples(
					FiltroResolucaoDiretoria.DATA_VIGENCIA_FIM, dataFimVigenciaDate));
		}
				
		// [FS0002] Verificar preenchimento dos campos
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
		// Filtrando os dados...
		filtroResolucaoDiretoria.isConsultaSemLimites();
		Collection<ResolucaoDiretoria> collectionResolucaoDiretoria =  fachada.pesquisar(filtroResolucaoDiretoria,ResolucaoDiretoria.class.getName()); 
			
  	  	if (collectionResolucaoDiretoria == null || collectionResolucaoDiretoria.isEmpty()) {
  	  		// [FS0004] Nenhum registro encontrado
  	  		throw new ActionServletException(
  	  				"atencao.pesquisa.nenhumresultado", null, "resolu��o de diretoria");
  	  	} /*else if (collectionResolucaoDiretoria.size() > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PESQUISA) {
  	  		// [FS0003] Muitos registros encontrados
  	  		throw new ActionServletException("atencao.pesquisa.muitosregistros");
  	  	}*/ else {
  	  		sessao.setAttribute("collectionResolucaoDiretoria", collectionResolucaoDiretoria);
  	  	} 
		

		return retorno;

	}
	
	
	private void validacaoFinal(PesquisarResolucaoDiretoriaActionForm form) {
		Date DataInicioVigencia = Util.converteStringParaDate(form.getDataInicioVigencia());
		Date DataFimVigencia = Util.converteStringParaDate(form.getDataFimVigencia());
		
		if (DataInicioVigencia != null && DataFimVigencia != null) {
			if (!DataInicioVigencia.equals("") && !DataFimVigencia.equals("")) {

				if (DataInicioVigencia.after(DataFimVigencia) || Util.datasIguais(DataInicioVigencia,DataFimVigencia) ) {
					//O T�rmino de Vig�ncia deve ser maior que o In�cio da Vig�ncia
					throw new ActionServletException(
							"atencao.termino_maior_inicio_vigencia");
				}

			}
		}
		
	}

}
