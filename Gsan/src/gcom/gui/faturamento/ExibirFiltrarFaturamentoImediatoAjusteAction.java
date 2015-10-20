/**
 * 
 */
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

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0991] Filtrar Faturamento Imediato Ajuste
 * 
 * @author Hugo Leonardo
 *
 * @date 26/02/2010
 */

public class ExibirFiltrarFaturamentoImediatoAjusteAction extends GcomAction {

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param actionMapping
	 *            Descri��o do par�metro
	 * @param actionForm
	 *            Descri��o do par�metro
	 * @param httpServletRequest
	 *            Descri��o do par�metro
	 * @param httpServletResponse
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("filtrarFaturamentoImediatoAjuste");

		// Cria a sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

		String tela = null;

		FiltrarFaturamentoImediatoAjusteActionForm form = (FiltrarFaturamentoImediatoAjusteActionForm) actionForm;

		if (httpServletRequest.getParameter("tela") != null) {
			tela = httpServletRequest.getParameter("tela");
			sessao.setAttribute("tela", tela);
		} else {
			if (sessao.getAttribute("tela") != null) {
				tela = (String) sessao.getAttribute("tela");
			}

		}
		// Obt�m a inst�ncia da fachada
		//Fachada fachada = Fachada.getInstancia();
		this.pesquisarGrupoFaturamento(httpServletRequest);
		
		//this.pesquisarImovel(httpServletRequest, form);

		// seta o parametro para o controle de acesso a fucionalidade ou
		// opera��o
		httpServletRequest.setAttribute("tela", tela);

		// Pega os codigos que o usuario digitou para a pesquisa direta de Imovel
		String objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");
		
		if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("")) {
			
			
			switch (Integer.parseInt(objetoConsulta)) {
            
            //Imovel
            case 1:
            	pesquisarImovel(httpServletRequest, form);
            	break;
            	
            case 2:
            	//ROTA INFORMADA
            	pesquisarRota(httpServletRequest, form);
            	break;
			}

		}
		
		httpServletRequest.setAttribute("referencia", form.getMesAnoReferencia());
		
		return retorno;
	}

	private void pesquisarGrupoFaturamento(HttpServletRequest httpServletRequest) {

		FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();

		filtroFaturamentoGrupo.setConsultaSemLimites(true);
//		filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
//				FiltroFaturamentoGrupo.DESCRICAO, ));
		filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
				FiltroFaturamentoGrupo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO ));
		filtroFaturamentoGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.DESCRICAO);

		Collection colecaoFaturamentoGrupo = this.getFachada().pesquisar(
				filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());

		if (colecaoFaturamentoGrupo == null
				|| colecaoFaturamentoGrupo.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Grupo Faturamento");
		} else {
			httpServletRequest.setAttribute("colecaoFaturamentoGrupo",
					colecaoFaturamentoGrupo);
		}
	}

	private void pesquisarImovel(HttpServletRequest httpServletRequest,
			FiltrarFaturamentoImediatoAjusteActionForm form) {

		Fachada fachada = Fachada.getInstancia();

		// Pesquisa a localidade na base
		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(new ParametroSimples(
				FiltroImovel.ID, form.getImovelId()));
		///filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.SETOR_COMERCIAL_CODIGO);

		Collection<Imovel> imovelPesquisado = fachada.pesquisar(
				filtroImovel, Imovel.class.getName());

		// Se nenhuma localidade for encontrada a mensagem � enviada para a
		// p�gina
		if (imovelPesquisado != null && !imovelPesquisado.isEmpty()) {
			Imovel imovel = (Imovel) Util
					.retonarObjetoDeColecao(imovelPesquisado);
			form.setImovelId("" + imovel.getId());
			form.setImovelInscricao(fachada.pesquisarInscricaoImovelExcluidoOuNao(new Integer(imovel.getId())));
			

		} else {
			form.setImovelId("");
			form.setImovelInscricao("IMOVEL INEXISTENTE");
			httpServletRequest.setAttribute("imovelNaoEncontrado", true);
		}
	}
	
	private void pesquisarRota(HttpServletRequest httpServletRequest,
			FiltrarFaturamentoImediatoAjusteActionForm form) {

		String idRota = form.getRotaId();
    	
    	if (idRota != null && !idRota.trim().equals("")) {
        	
    		FiltroRota filtroRota = new FiltroRota();
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, form.getRotaId()));
			
			Collection colecaoRotas = this.getFachada().pesquisar(filtroRota, Rota.class.getName());
			
			if (colecaoRotas != null && !colecaoRotas.isEmpty()) {
				Rota rota = (Rota) Util.retonarObjetoDeColecao(colecaoRotas);
				
				form.setCodigoRota(rota.getCodigo().toString());
				form.setRotaId(rota.getId().toString());
			}
    		
    	}else{
			form.setCodigoRota(null);
			form.setRotaId(null);
    	}
		
	}

}