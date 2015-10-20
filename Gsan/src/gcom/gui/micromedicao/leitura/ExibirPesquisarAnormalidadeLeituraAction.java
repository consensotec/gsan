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
package gcom.gui.micromedicao.leitura;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidadeConsumo;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidadeLeitura;
import gcom.micromedicao.leitura.LeituraAnormalidadeConsumo;
import gcom.micromedicao.leitura.LeituraAnormalidadeLeitura;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ExibirPesquisarAnormalidadeLeituraAction  extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirPesquisarAnormalidadeLeitura");

		PesquisarLeituraAnormalidadeActionForm pesquisarLeituraAnormalidadeActionForm = (PesquisarLeituraAnormalidadeActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();
		
		// limpa os parametros do form
		pesquisarLeituraAnormalidadeActionForm.setDescricao("");
		pesquisarLeituraAnormalidadeActionForm.setAnormalidadeRelativaHidrometro(ConstantesSistema.TODOS+"");
		pesquisarLeituraAnormalidadeActionForm.setAnormalidadeSemHidrometro(ConstantesSistema.TODOS+"");
		pesquisarLeituraAnormalidadeActionForm.setAnormalidadeRestritoSistema(ConstantesSistema.TODOS+"");
		pesquisarLeituraAnormalidadeActionForm.setAnormalidadePerdaTarifaSocial(ConstantesSistema.TODOS+"");
		pesquisarLeituraAnormalidadeActionForm.setAnormalidadeOrdemServicoAutomatica(ConstantesSistema.TODOS+"");

		// Parte que pega as cole��es da sess�o

		// Consumo a ser cobrado Leitura Anormalidade
		FiltroLeituraAnormalidadeConsumo filtroLeituraAnormalidadeConsumo = new FiltroLeituraAnormalidadeConsumo();
		filtroLeituraAnormalidadeConsumo.adicionarParametro(new ParametroSimples(
				FiltroLeituraAnormalidadeConsumo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection leiturasAnormalidadesConsumo = fachada.pesquisar(filtroLeituraAnormalidadeConsumo,
				LeituraAnormalidadeConsumo.class.getName());
		if (leiturasAnormalidadesConsumo.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Consumo a ser cobrado");
		} else {
			sessao.setAttribute("leiturasAnormalidadesConsumo", leiturasAnormalidadesConsumo);
		}

		// Leitura para Faturamento
		FiltroLeituraAnormalidadeLeitura filtroLeituraAnormalidadeLeitura = new FiltroLeituraAnormalidadeLeitura();
		filtroLeituraAnormalidadeLeitura.adicionarParametro(new ParametroSimples(
				FiltroLeituraAnormalidadeLeitura.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection leiturasAnormalidadesLeitura = fachada.pesquisar(filtroLeituraAnormalidadeLeitura,
				LeituraAnormalidadeLeitura.class.getName());
		if (leiturasAnormalidadesConsumo.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Leitura para Faturamento");
		} else {
			sessao.setAttribute("leiturasAnormalidadesLeitura", leiturasAnormalidadesLeitura);
		}
		
		
//		 envia uma flag que ser� verificado no cliente_resultado_pesquisa.jsp
		// para saber se ir� usar o enviar dados ou o enviar dados parametros
		if (httpServletRequest
				.getParameter("caminhoRetornoTelaPesquisaAnormalidadeLeitura") != null) {
			sessao
					.setAttribute(
							"caminhoRetornoTelaPesquisaAnormalidadeLeitura",
							httpServletRequest
									.getParameter("caminhoRetornoTelaPesquisaAnormalidadeLeitura"));
		}
/*
		// Ramo de Atividade
		FiltroRamoAtividade filtroRamoAtividade = new FiltroRamoAtividade();
		filtroRamoAtividade.adicionarParametro(new ParametroSimples(
				FiltroRamoAtividade.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection ramosAtividades = fachada.pesquisar(filtroRamoAtividade,
				RamoAtividade.class.getName());
		if (ramosAtividades.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"ramo de atividade");
		} else {
			sessao.setAttribute("ramosAtividades", ramosAtividades);
		}
*/
		return retorno;
	}
}
