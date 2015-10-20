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
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class PesquisarAnormalidadeLeituraAction extends GcomAction {
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
		ActionForward retorno = actionMapping.findForward("listaLeituraAnormalidades");

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		PesquisarLeituraAnormalidadeActionForm pesquisarLeituraAnormalidadeActionForm = (PesquisarLeituraAnormalidadeActionForm) actionForm;

		// Recupera os parametros

		String descricao = pesquisarLeituraAnormalidadeActionForm
				.getDescricao();
		String anormalidadeHidrometro = pesquisarLeituraAnormalidadeActionForm
				.getAnormalidadeRelativaHidrometro();
		String anormalidadeAceitaSemHidrometro = pesquisarLeituraAnormalidadeActionForm
				.getAnormalidadeSemHidrometro();
		String anormalidadeUsoSistema = pesquisarLeituraAnormalidadeActionForm
				.getAnormalidadeRestritoSistema();
		String anormalidadePerdaTarifaSocial = pesquisarLeituraAnormalidadeActionForm
				.getAnormalidadePerdaTarifaSocial();
		String anormalidadeOrdemServicoAutomatica = pesquisarLeituraAnormalidadeActionForm
				.getAnormalidadeOrdemServicoAutomatica();
		Integer tipoServico =  new Integer(pesquisarLeituraAnormalidadeActionForm
				.getTipoServico());
		Integer consumoLeituraInformada = new Integer(pesquisarLeituraAnormalidadeActionForm
				.getConsumoCobradoLeituraInformada());
		Integer consumoLeituraNaoInformada = new Integer(pesquisarLeituraAnormalidadeActionForm
				.getConsumoCobradoLeituraNaoInformada());
		Integer leituraFaturamentoLeituraInformada = new Integer(pesquisarLeituraAnormalidadeActionForm
				.getLeituraFaturamentoLeituraInformada());
		Integer leituraFaturamentoLeituraNaoInformada = new Integer(pesquisarLeituraAnormalidadeActionForm
				.getLeituraFaturamentoLeituraNaoInformada());

		FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade(
				FiltroLeituraAnormalidade.ID);

		boolean peloMenosUmParametroInformado = false;

		// Insere os par�metros informados no filtro

		if (descricao != null && !descricao.equals("")) {
			peloMenosUmParametroInformado = true;
			filtroLeituraAnormalidade.adicionarParametro(new ComparacaoTexto(
					FiltroLeituraAnormalidade.DESCRICAO, descricao));
		}

		if (anormalidadeHidrometro != null
				&& !anormalidadeHidrometro.equals("") && 
				!(new Short(anormalidadeHidrometro)).equals(ConstantesSistema.TODOS)) {
			peloMenosUmParametroInformado = true;
			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.INDICADOR_RELATIVO_HIDROMETRO,
					anormalidadeHidrometro));
		}

		if (anormalidadeAceitaSemHidrometro != null
				&& !anormalidadeAceitaSemHidrometro.equals("") && !(new Short(anormalidadeAceitaSemHidrometro)).equals(ConstantesSistema.TODOS)) {
			peloMenosUmParametroInformado = true;
			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.INDICADOR_IMOVEL_SEM_HIDROMETRO,
					anormalidadeAceitaSemHidrometro));
		}

		if (anormalidadeUsoSistema != null
				&& !anormalidadeUsoSistema.equals("") && !(new Short(anormalidadeUsoSistema)).equals(ConstantesSistema.TODOS)) {
			peloMenosUmParametroInformado = true;
			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.INDICADOR_USO_SISTEMA,
					anormalidadeUsoSistema));
		}

		if (anormalidadePerdaTarifaSocial != null
				&& !anormalidadePerdaTarifaSocial.equals("") && !(new Short(anormalidadePerdaTarifaSocial)).equals(ConstantesSistema.TODOS)) {
			peloMenosUmParametroInformado = true;
			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.INDICADOR_PERDA_TARIFA_SOCIAL,
					anormalidadePerdaTarifaSocial));
		}

		if (anormalidadeOrdemServicoAutomatica != null
				&& !anormalidadeOrdemServicoAutomatica.equals("") && !(new Short(anormalidadeOrdemServicoAutomatica)).equals(ConstantesSistema.TODOS)) {
			peloMenosUmParametroInformado = true;
			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.INDICADOR_EMISSAO_ORDEM_SERVICO,
					anormalidadeOrdemServicoAutomatica));
		}

		if (tipoServico != null
				&& tipoServico.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
			peloMenosUmParametroInformado = true;
			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.ID_TIPO_SERVICO, tipoServico));
		}

		if (consumoLeituraInformada != null
				&& consumoLeituraInformada.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
			peloMenosUmParametroInformado = true;
			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.ID_CONSUMO_A_COBRAR_COM_LEITURA,
					consumoLeituraInformada));
		}

		if (consumoLeituraNaoInformada != null
				&& consumoLeituraNaoInformada.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
			peloMenosUmParametroInformado = true;
			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.ID_CONSUMO_A_COBRAR_SEM_LEITURA,
					consumoLeituraNaoInformada));
		}

		if (leituraFaturamentoLeituraInformada != null
				&& leituraFaturamentoLeituraInformada.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
			peloMenosUmParametroInformado = true;
			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.ID_LEITURA_A_FATURAR_COM_LEITURA,
					leituraFaturamentoLeituraInformada));
		}

		if (leituraFaturamentoLeituraNaoInformada != null
				&& leituraFaturamentoLeituraNaoInformada.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
			peloMenosUmParametroInformado = true;
			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.ID_LEITURA_A_FATURAR_SEM_LEITURA,
					leituraFaturamentoLeituraNaoInformada));
		}


		// Erro caso o usu�rio mandou filtrar sem nenhum par�metro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		
		Collection leituraAnormalidades = null;

		// Obt�m a inst�ncia da Fachada
		Fachada fachada = Fachada.getInstancia();

		// adiciona as depend�ncias para serem mostradas na p�gina
		filtroLeituraAnormalidade
				.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeConsumoSemleitura");
		filtroLeituraAnormalidade
				.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeConsumoComleitura");
		filtroLeituraAnormalidade
				.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeLeituraSemleitura");
		filtroLeituraAnormalidade
				.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeLeituraComleitura");

		// Faz a busca das empresas
		leituraAnormalidades = fachada.pesquisar(filtroLeituraAnormalidade,
				LeituraAnormalidade.class.getName());

		if (leituraAnormalidades == null || leituraAnormalidades.isEmpty()) {

			// Nenhum municipio cadastrado
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null,
					"Anormalidade de Leitura");
		} else if (leituraAnormalidades.size() > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PESQUISA) {
			// Muitos registros encontrados
			throw new ActionServletException(
					"atencao.pesquisa.muitosregistros");
		} else {
			if (leituraAnormalidades.size() == ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PESQUISA) {
				httpServletRequest.setAttribute("limitePesquisa", "");
			}

			// Coloca a cole��o na sess�o
			sessao.setAttribute("leituraAnormalidades", leituraAnormalidades);

		}

		return retorno;
	}

}
