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
package gcom.gui.relatorio.cadastro.endereco;

import gcom.cadastro.endereco.FiltroLogradouroTipo;
import gcom.cadastro.endereco.FiltroLogradouroTitulo;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.cadastro.endereco.LogradouroTitulo;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.cadastro.endereco.LogradorouRelatorioHelper;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.endereco.RelatorioManterLogradouro;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.SistemaException;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gest�o Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Rafael Corr�a
 * @version 1.0
 */

public class GerarRelatorioLogradouroManterAction extends ExibidorProcessamentoTarefaRelatorio {

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

		// cria a vari�vel de retorno
		ActionForward retorno = null;

		DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		LogradorouRelatorioHelper helperLogradouroRelatorio = (LogradorouRelatorioHelper) pesquisarActionForm.get("helperLogradouroRelatorio");
		
		Collection logradouros = fachada.pesquisarLogradouroCompletoRelatorio(
				helperLogradouroRelatorio.getIdMunicipio(),
				helperLogradouroRelatorio.getIdBairro(),
				helperLogradouroRelatorio.getNomeLogradouro(),
				helperLogradouroRelatorio.getNomePopularLogradouro(),
				helperLogradouroRelatorio.getIdTipoLogradouro(),
				helperLogradouroRelatorio.getIdTituloLogradouro(),
				helperLogradouroRelatorio.getCodigoCep(),
				helperLogradouroRelatorio.getIdLogradouro(),
				helperLogradouroRelatorio.getIndicadorUso(),
				helperLogradouroRelatorio.getTipoPesquisa(),
				helperLogradouroRelatorio.getTipoPesquisaPopular());
		
		// Organizar a cole��o

//		Collections.sort((List) logradouros, new Comparator() {
//			public int compare(Object a, Object b) {
//				String municipio1 = ((Logradouro) a).getMunicipio().getNome();
//				String municipio2 = ((Logradouro) b).getMunicipio().getNome();
//
//				return municipio1.compareTo(municipio2);
//			}
//		});

		// Inicio da parte que vai mandar os parametros para o relat�rio

		Logradouro logradouroParametros = new Logradouro();

		String idMunicipio = (String) pesquisarActionForm.get("idMunicipioFiltro");

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
				// O municipio foi encontrado
				Iterator municipioIterator = municipios.iterator();

				municipio = (Municipio) municipioIterator.next();

			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Munic�pio");
			}

		} else {
			municipio = new Municipio();
		}

		String idLogradouro = null;

		String idLogradouroPesquisar = (String) pesquisarActionForm
				.get("idLogradouro");

		if (idLogradouroPesquisar != null && !idLogradouroPesquisar.equals("")) {
			idLogradouro = idLogradouroPesquisar;
		}

		Integer idTitulo = (Integer) pesquisarActionForm.get("idTitulo");

		LogradouroTitulo logradouroTitulo = null;

		if (idTitulo != null && !idTitulo.equals("")) {
			FiltroLogradouroTitulo filtroLogradouroTitulo = new FiltroLogradouroTitulo();

			filtroLogradouroTitulo.adicionarParametro(new ParametroSimples(
					FiltroLogradouroTitulo.ID, idTitulo));
			filtroLogradouroTitulo.adicionarParametro(new ParametroSimples(
					FiltroLogradouroTitulo.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection logradourosTitulos = fachada.pesquisar(
					filtroLogradouroTitulo, LogradouroTitulo.class.getName());

			if (logradourosTitulos != null && !logradourosTitulos.isEmpty()) {
				// Titulo do Logradouro Foi Encontrado
				Iterator logradouroTituloIterator = logradourosTitulos
						.iterator();

				logradouroTitulo = (LogradouroTitulo) logradouroTituloIterator
						.next();

			} else {
				logradouroTitulo = new LogradouroTitulo();
			}
		}

		Integer idTipo = (Integer) pesquisarActionForm.get("idTipo");

		LogradouroTipo logradouroTipo = null;

		if (idTipo != null && !idTipo.equals("")) {
			FiltroLogradouroTipo filtroLogradouroTipo = new FiltroLogradouroTipo();

			filtroLogradouroTipo.adicionarParametro(new ParametroSimples(
					FiltroLogradouroTipo.ID, idTitulo));
			filtroLogradouroTipo.adicionarParametro(new ParametroSimples(
					FiltroLogradouroTipo.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection logradourosTipos = fachada.pesquisar(
					filtroLogradouroTipo, LogradouroTipo.class.getName());

			if (logradourosTipos != null && !logradourosTipos.isEmpty()) {
				// Titulo do Logradouro Foi Encontrado
				Iterator logradouroTipoIterator = logradourosTipos.iterator();

				logradouroTipo = (LogradouroTipo) logradouroTipoIterator.next();

			} else {
				logradouroTipo = new LogradouroTipo();
			}

		}

		Short indicadorDeUso = null;

		if (pesquisarActionForm.get("indicadorUso") != null
				&& !pesquisarActionForm.get("indicadorUso").equals("")) {

			indicadorDeUso = new Short(""
					+ pesquisarActionForm.get("indicadorUso"));
		}

		// seta os parametros que ser�o mostrados no relat�rio

		logradouroParametros.setMunicipio(municipio);
		logradouroParametros.setId(idLogradouro == null ? null : new Integer(
				idLogradouro));
		logradouroParametros.setNome(""
				+ pesquisarActionForm.get("nomeLogradouro"));
		logradouroParametros.setLogradouroTitulo(logradouroTitulo);
		logradouroParametros.setLogradouroTipo(logradouroTipo);
		logradouroParametros.setIndicadorUso(indicadorDeUso);

		// Fim da parte que vai mandar os parametros para o relat�rio

			// cria uma inst�ncia da classe do relat�rio
			RelatorioManterLogradouro relatorioManterLogradouro = new RelatorioManterLogradouro(
					(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

			relatorioManterLogradouro.addParametro("logradouros", logradouros);
			relatorioManterLogradouro.addParametro("logradouroParametros",
					logradouroParametros);

			// chama o met�do de gerar relat�rio passando o c�digo da analise
			// como par�metro
			String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
			if (tipoRelatorio == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}

			relatorioManterLogradouro.addParametro("tipoFormatoRelatorio", Integer
					.parseInt(tipoRelatorio));
			try {
				retorno = processarExibicaoRelatorio(relatorioManterLogradouro,
						tipoRelatorio, httpServletRequest, httpServletResponse,
						actionMapping);

		} catch (SistemaException ex) {
			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "erro.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de aten��o de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		// devolve o mapeamento contido na vari�vel retorno
		return retorno;
	}

}