/*
 * Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
 * GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
 * Copyright (C) <2007> 
 * Adriano Britto Siqueira
 * Alexandre Santos Cabral
 * Ana Carolina Alves Breda
 * Ana Maria Andrade Cavalcante
 * Aryed Lins de Araújo
 * Bruno Leonardo Rodrigues Barros
 * Carlos Elmano Rodrigues Ferreira
 * Cláudio de Andrade Lira
 * Denys Guimarães Guenes Tavares
 * Eduardo Breckenfeld da Rosa Borges
 * Fabíola Gomes de Araújo
 * Flávio Leonardo Cavalcanti Cordeiro
 * Francisco do Nascimento Júnior
 * Homero Sampaio Cavalcanti
 * Ivan Sérgio da Silva Júnior
 * José Edmar de Siqueira
 * José Thiago Tenório Lopes
 * Kássia Regina Silvestre de Albuquerque
 * Leonardo Luiz Vieira da Silva
 * Márcio Roberto Batista da Silva
 * Maria de Fátima Sampaio Leite
 * Micaela Maria Coelho de Araújo
 * Nelson Mendonça de Carvalho
 * Newton Morais e Silva
 * Pedro Alexandre Santos da Silva Filho
 * Rafael Corrêa Lima e Silva
 * Rafael Francisco Pinto
 * Rafael Koury Monteiro
 * Rafael Palermo de Araújo
 * Raphael Veras Rossiter
 * Roberto Sobreira Barbalho
 * Rodrigo Avellar Silveira
 * Rômulo Aurélio de Melo Souza Filho
 * Rosana Carvalho Barbosa
 * Sávio Luiz de Andrade Cavalcante
 * Tai Mu Shih
 * Thiago Augusto Souza do Nascimento
 * Tiago Moreno Rodrigues
 * Vivianne Barbosa Sousa
 *
 * Este programa é software livre; você pode redistribuí-lo e/ou
 * modificá-lo sob os termos de Licença Pública Geral GNU, conforme
 * publicada pela Free Software Foundation; versão 2 da
 * Licença.
 * Este programa é distribuído na expectativa de ser útil, mas SEM
 * QUALQUER GARANTIA; sem mesmo a garantia implícita de
 * COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
 * PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
 * detalhes.
 * Você deve ter recebido uma cópia da Licença Pública Geral GNU
 * junto com este programa; se não, escreva para Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307, USA.
 */
package gsan.gui.atendimentopublico.registroatendimento;

import gsan.atendimentopublico.registroatendimento.FiltroOcorrenciaOperacionalMotivo;
import gsan.atendimentopublico.registroatendimento.FiltroOcorrenciaOperacionalTipo;
import gsan.atendimentopublico.registroatendimento.OcorrenciaOperacionalMotivo;
import gsan.atendimentopublico.registroatendimento.OcorrenciaOperacionalTipo;
import gsan.cadastro.geografico.Bairro;
import gsan.cadastro.geografico.FiltroBairro;
import gsan.cadastro.geografico.FiltroMunicipio;
import gsan.cadastro.geografico.Municipio;
import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.Localidade;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1528] - Filtrar Ocorrencia Operacional
 * 
 * @author Rômulo Aurélio
 * @date 12/07/2013
 */
public class ExibirFiltrarOcorrenciaOperacionalAction extends GcomAction {
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
	@SuppressWarnings({ "rawtypes", "unused" })
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("filtrarOcorrenciaOperacional");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		Collection colecaoLocalidade = new ArrayList();
		Collection colecaoBairro = new ArrayList();
		Collection colecaoOcorrenciaOperacionalMotivo = new ArrayList();

		FiltrarOcorrenciaOperacionalActionForm form = (FiltrarOcorrenciaOperacionalActionForm) actionForm;

		form.setAtualizar("1");

		if (form.getCodigoMunicipio() != null && !form.getCodigoMunicipio().equals("")) {

			// Dados Municipio

			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
			filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID,
																	form.getCodigoMunicipio()));

			Collection colecaoMunicipio = fachada.pesquisar(filtroMunicipio, Municipio.class.getName());

			if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {
				Municipio municipio = (Municipio) colecaoMunicipio.iterator().next();
				form.setDescricaoMunicipio(municipio.getNome());				
				
				httpServletRequest.setAttribute("nomeCampo", "localidade");

				// Dados Localidade
				colecaoLocalidade = fachada.obterLocalidadesdoMunicipio(new Integer(form.getCodigoMunicipio()));
				
				// Dados Bairro
				FiltroBairro filtroBairro = new FiltroBairro();
				filtroBairro.adicionarParametro(new ParametroSimples(FiltroBairro.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO.toString()));
				filtroBairro.adicionarParametro(new ParametroSimples(FiltroBairro.MUNICIPIO_ID, form.getCodigoMunicipio()));
				filtroBairro.setCampoOrderBy(FiltroBairro.NOME);
				
				colecaoBairro = fachada.pesquisar(filtroBairro, Bairro.class.getName());			
				

			} else {
				form.setCodigoMunicipio("");
				form.setDescricaoMunicipio("Município Inexistente");

				httpServletRequest.setAttribute("idMunicipioNaoEncontrado", "exception");
				httpServletRequest.setAttribute("nomeCampo", "codigoMunicipio");
			}

		}else{
			
			// Dados Localidade
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.setCampoOrderBy(FiltroLocalidade.DESCRICAO);
			filtroLocalidade.adicionarParametro(new ParametroSimples(	FiltroLocalidade.INDICADORUSO,
																		ConstantesSistema.INDICADOR_USO_ATIVO.toString()));
			colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());

			// Dados Bairro
			FiltroBairro filtroBairro = new FiltroBairro();
			filtroBairro.adicionarParametro(new ParametroSimples(	FiltroBairro.INDICADOR_USO,
																	ConstantesSistema.INDICADOR_USO_ATIVO.toString()));
			filtroBairro.setCampoOrderBy(FiltroBairro.NOME);

			colecaoBairro = fachada.pesquisar(filtroBairro, Bairro.class.getName());		
		}

		httpServletRequest.setAttribute("nomeCampo", "codigoMunicipio");	

		// Ocorrencia Operacional Tipo
		FiltroOcorrenciaOperacionalTipo filtroOcorrenciaOperacionalTipo = new FiltroOcorrenciaOperacionalTipo();
		filtroOcorrenciaOperacionalTipo.adicionarParametro(new ParametroSimples(FiltroOcorrenciaOperacionalTipo.INDICADOR_USO,
																				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection colecaoOcorrenciaOperacionalTipo = fachada.pesquisar(filtroOcorrenciaOperacionalTipo, OcorrenciaOperacionalTipo.class.getName());

		// Carregamento de Ocorrencia Operacional Motivo
		FiltroOcorrenciaOperacionalMotivo filtroOcorrenciaOperacionalMotivo = new FiltroOcorrenciaOperacionalMotivo();

		filtroOcorrenciaOperacionalMotivo.adicionarParametro(new ParametroSimples(	FiltroOcorrenciaOperacionalMotivo.INDICADOR_USO,
																					ConstantesSistema.INDICADOR_USO_ATIVO));

		colecaoOcorrenciaOperacionalMotivo = fachada.pesquisar(filtroOcorrenciaOperacionalMotivo, OcorrenciaOperacionalMotivo.class.getName());

		// Dados ComboBox
		httpServletRequest.setAttribute("colecaoOcorrenciaOperacionalTipo", colecaoOcorrenciaOperacionalTipo);
		httpServletRequest.setAttribute("colecaoLocalidade", colecaoLocalidade);
		httpServletRequest.setAttribute("colecaoBairro", colecaoBairro);
		httpServletRequest.setAttribute("colecaoOcorrenciaOperacionalMotivo", colecaoOcorrenciaOperacionalMotivo);

		return retorno;

	}
}
