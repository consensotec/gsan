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
package gcom.gui.atendimentopublico.registroatendimento;

import java.util.Collection;
import java.util.Date;

import gcom.atendimentopublico.bean.OcorrenciaOperacionalFiltroHelper;
import gcom.atendimentopublico.bean.OcorrenciaOperacionalHelper;
import gcom.atendimentopublico.registroatendimento.FiltroOcorrenciaOperacionalTipo;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.OcorrenciaOperacionalTipo;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.cliente.FiltroOrgaoExpedidorRg;
import gcom.cadastro.cliente.OrgaoExpedidorRg;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.FiltroUnidadeFederacao;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1530] Inserir Registro de Atendimento Simplificado
 * 
 * @author Mariana Victor
 * @date 10/07/2013
 */
public class ExibirInserirRegistroAtendimentoSimplificadoAction extends GcomAction {
	
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

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("inserirRegistroAtendimentoSimplificado");
		
		InserirRegistroAtendimentoSimplificadoActionForm form = (InserirRegistroAtendimentoSimplificadoActionForm) actionForm;

		if (httpServletRequest.getParameter("menu") != null
				&& httpServletRequest.getParameter("menu").trim().equals("sim")) {
			
			//[IT0001] Selecionar sequência do protocolo
			String protocoloAtendimento = this.getFachada().obterProtocoloAtendimento();
			sessao.setAttribute("protocoloAtendimento", protocoloAtendimento);
	
			form.setTipoAtendimento("1");
			form.setTipoAtendimentoSelecionado("1");
			
			Date dataCorrente = new Date();
			form.setDataAtendimento(Util
					.formatarData(dataCorrente));
			form.setHoraAtendimento(Util
					.formatarHoraSemSegundos(dataCorrente));

			this.pesquisarColecaoOrgaoExpedidor(sessao);
			this.pesquisarColecaoUnidadeFederacao(sessao);

			this.pesquisarColecaoTipoOcorrencia(sessao);
			this.pesquisarColecaoLocalidadeInfo(sessao);
			
			
		} else if (httpServletRequest.getParameter("pesquisarRA") != null
				&& httpServletRequest.getParameter("pesquisarRA").trim().equals("sim")) {

			this.pesquisarRA(form, sessao);
				
		} else if (httpServletRequest.getParameter("pesquisarMunicipio") != null
				&& httpServletRequest.getParameter("pesquisarMunicipio").trim().equals("sim")) {

			// [FE0003] Validar município
			this.pesquisarMunicipio(form, sessao);
		
		} else if (httpServletRequest.getParameter("limparMunicipio") != null
				&& httpServletRequest.getParameter("limparMunicipio").trim().equals("sim")) {

			// [SB0001] Limpar Município
			this.limparMunicipio(form, sessao);
			
		} else if (httpServletRequest.getParameter("consultarOcorrencias") != null
				&& httpServletRequest.getParameter("consultarOcorrencias").trim().equals("sim")) {
			
			// [IT0009] Selecionar Lista das Ocorrências Operacionais
			this.pesquisarOcorrenciasOperacionais(form, sessao);
				
		} else if (httpServletRequest.getParameter("limparOcorrencias") != null
				&& httpServletRequest.getParameter("limparOcorrencias").trim().equals("sim")) {
			
			sessao.removeAttribute("colecaoOcorrenciaOperacionalHelper");
				
		} 
		
		return retorno;
	}

	/**
	 * [IT0005] Pesquisar Localidades do Município
	 * */
	private void pesquisarRA(InserirRegistroAtendimentoSimplificadoActionForm form, HttpSession sessao) {
		
		FiltroRegistroAtendimento filtroRA = new FiltroRegistroAtendimento();
		filtroRA.adicionarParametro(new ParametroSimples(
			FiltroRegistroAtendimento.ID, new Integer(form.getNumeroRA())));
		filtroRA.adicionarCaminhoParaCarregamentoEntidade(
			FiltroRegistroAtendimento.SOLICITACAO_TIPO_ESPECIFICACAO);
		
		Collection<?> colecaoRA = Fachada.getInstancia()
				.pesquisar(filtroRA,RegistroAtendimento.class.getName());

		if (colecaoRA != null && !colecaoRA.isEmpty()) {

			RegistroAtendimento registroAtendimento = 
				(RegistroAtendimento) Util.retonarObjetoDeColecao(colecaoRA);

			form.setNumeroRA(registroAtendimento.getId().toString());
			form.setDescricaoRA(registroAtendimento.getSolicitacaoTipoEspecificacao().getDescricao());
			sessao.removeAttribute("numeroRAInexistente");

		} else {
			form.setNumeroRA("");
			form.setDescricaoRA("Registro Atendimento inexistente");
			sessao.setAttribute("numeroRAInexistente", true);
		}
	}

	/**
	 * [FE0003] Validar município
	 * */
	private void pesquisarMunicipio(InserirRegistroAtendimentoSimplificadoActionForm form, HttpSession sessao) {
		
		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
		filtroMunicipio.adicionarParametro(new ParametroSimples(
			FiltroMunicipio.ID, new Integer(form.getIdMunicipioRA())));
		filtroMunicipio.adicionarParametro(new ParametroSimples(
			FiltroMunicipio.INDICADOR_USO, ConstantesSistema.SIM));
		
		Collection<?> colecaoMunicipio = Fachada.getInstancia()
				.pesquisar(filtroMunicipio,Municipio.class.getName());

		if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {

			Municipio municipio = 
				(Municipio) Util.retonarObjetoDeColecao(colecaoMunicipio);

			form.setIdMunicipioRA(municipio.getId().toString());
			form.setNomeMunicipioRA(municipio.getNome());
			sessao.removeAttribute("municipioInexistente");

			this.pesquisarColecaoLocalidade(sessao, municipio.getId());
			this.pesquisarColecaoBairro(sessao, municipio.getId());
			
		} else {
			form.setIdMunicipioRA("");
			form.setNomeMunicipioRA("MUNICÍPIO INEXISTENTE");
			sessao.setAttribute("municipioInexistente", true);
			
			sessao.removeAttribute("colecaoLocalidade");
			sessao.removeAttribute("colecaoBairro");
		}
	}

	/**
	 * [SB0001] Limpar Município
	 * */
	private void limparMunicipio(InserirRegistroAtendimentoSimplificadoActionForm form, HttpSession sessao) {
		form.setIdMunicipioRA("");
		form.setNomeMunicipioRA("");
		sessao.removeAttribute("municipioInexistente");
		
		form.setIdTipoOcorrencia(ConstantesSistema.NUMERO_NAO_INFORMADO + "");
		sessao.removeAttribute("colecaoLocalidade");
		sessao.removeAttribute("colecaoBairro");
		sessao.removeAttribute("colecaoOcorrenciaOperacionalHelper");
	}

	/**
	 * [IT0009] Selecionar Lista das Ocorrências Operacionais
	 * */
	private void pesquisarOcorrenciasOperacionais(InserirRegistroAtendimentoSimplificadoActionForm form, HttpSession sessao) {
		if (form.getIdMunicipioRA() == null
				|| form.getIdMunicipioRA().trim().equals("")) {
			
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", 
				null, "Município");
		}

		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		
		OcorrenciaOperacionalFiltroHelper helper = new OcorrenciaOperacionalFiltroHelper();
		helper.setQuantidadeDias(sistemaParametro.getQtdDiasGuardarOcorrenciasParalisacao());
		helper.setIdMunicipio(Integer.valueOf(form.getIdMunicipioRA()));
		if (form.getIdLocalidadeRA() != null
				&& !form.getIdLocalidadeRA().trim().equals("")
				&& !form.getIdLocalidadeRA().trim().equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
			helper.setIdLocalidade(Integer.valueOf(form.getIdLocalidadeRA()));
		}
		if (form.getIdBairro() != null
				&& !form.getIdBairro().trim().equals("")
				&& !form.getIdBairro().trim().equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
			helper.setIdBairro(Integer.valueOf(form.getIdBairro()));
		}
		if (form.getIdTipoOcorrencia() != null
				&& !form.getIdTipoOcorrencia().trim().equals("")
				&& !form.getIdTipoOcorrencia().trim().equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
			helper.setIdTipoOcorrencia(Integer.valueOf(form.getIdTipoOcorrencia()));
		}
		
		Collection<OcorrenciaOperacionalHelper> colecaoOcorrenciaOperacionalHelper = 
				Fachada.getInstancia().pesquisarOcorrenciaOperacional(helper);

		if (colecaoOcorrenciaOperacionalHelper != null && !colecaoOcorrenciaOperacionalHelper.isEmpty()) {
			sessao.setAttribute("colecaoOcorrenciaOperacionalHelper", colecaoOcorrenciaOperacionalHelper);
		} else {
			sessao.removeAttribute("colecaoOcorrenciaOperacionalHelper");

			throw new ActionServletException("atencao.pesquisa.ocorrencia_operacional", 
				"exibirInserirRegistroAtendimentoSimplificadoAction.do", null);
		}
	}
	
	/**
	 * [IT0005] Pesquisar Localidades do Município
	 * */
	private void pesquisarColecaoLocalidade(HttpSession sessao, Integer idMunicipio) {
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(
			FiltroLocalidade.ID_MUNICIPIO, idMunicipio));
		filtroLocalidade.adicionarParametro(new ParametroSimples(
			FiltroLocalidade.INDICADORUSO, ConstantesSistema.SIM));
		filtroLocalidade.setCampoOrderBy(FiltroLocalidade.DESCRICAO);
		
		Collection<?> colecaoLocalidade = Fachada.getInstancia()
				.pesquisar(filtroLocalidade,Localidade.class.getName());
		
		if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
			sessao.setAttribute("colecaoLocalidade", colecaoLocalidade);
		} else {
			sessao.removeAttribute("colecaoLocalidade");
		}
	}

	/**
	 * [IT0006] Pesquisar Bairros do Município
	 * */
	private void pesquisarColecaoBairro(HttpSession sessao, Integer idMunicipio) {
		FiltroBairro filtroBairro = new FiltroBairro();
		filtroBairro.adicionarParametro(new ParametroSimples(
			FiltroBairro.MUNICIPIO_ID, idMunicipio));
		filtroBairro.adicionarParametro(new ParametroSimples(
			FiltroBairro.INDICADOR_USO, ConstantesSistema.SIM));
		filtroBairro.setCampoOrderBy(FiltroBairro.NOME);
		
		Collection<?> colecaoBairro = Fachada.getInstancia()
				.pesquisar(filtroBairro,Bairro.class.getName());
		
		if (colecaoBairro != null && !colecaoBairro.isEmpty()) {
			sessao.setAttribute("colecaoBairro", colecaoBairro);
		} else {
			sessao.removeAttribute("colecaoBairro");
		}
	}

	/**
	 * [IT0002] Pesquisar Órgão Expedidor
	 * */
	private void pesquisarColecaoOrgaoExpedidor(HttpSession sessao) {
		FiltroOrgaoExpedidorRg filtroOrgaoExpedidorRg = new FiltroOrgaoExpedidorRg();
		filtroOrgaoExpedidorRg.adicionarParametro(new ParametroSimples(
			FiltroOrgaoExpedidorRg.INDICADOR_USO, ConstantesSistema.SIM));
		filtroOrgaoExpedidorRg.setCampoOrderBy(FiltroOrgaoExpedidorRg.DESCRICAO);
		
		Collection<OrgaoExpedidorRg> colecaoOrgaoExpedidorRg = Fachada.getInstancia()
				.pesquisar(filtroOrgaoExpedidorRg,OrgaoExpedidorRg.class.getName());
		
		if (colecaoOrgaoExpedidorRg != null && !colecaoOrgaoExpedidorRg.isEmpty()) {
			sessao.setAttribute("colecaoOrgaoExpedidor", colecaoOrgaoExpedidorRg);
		} else {
			sessao.removeAttribute("colecaoOrgaoExpedidor");
		}
	}

	/**
	 * [IT0003] Pesquisar Estado
	 * */
	private void pesquisarColecaoUnidadeFederacao(HttpSession sessao) {
		FiltroUnidadeFederacao filtroUnidadeFederacao = new FiltroUnidadeFederacao();
		filtroUnidadeFederacao.setCampoOrderBy(FiltroUnidadeFederacao.DESCRICAO);
		
		Collection<UnidadeFederacao> colecaoUnidadeFederacao = Fachada.getInstancia()
				.pesquisar(filtroUnidadeFederacao,UnidadeFederacao.class.getName());
		
		if (colecaoUnidadeFederacao != null && !colecaoUnidadeFederacao.isEmpty()) {
			sessao.setAttribute("colecaoUnidadeFederacao", colecaoUnidadeFederacao);
		} else {
			sessao.removeAttribute("colecaoUnidadeFederacao");
		}
	}

	/**
	 * [IT0007] Pesquisar Tipo de Ocorrência
	 * */
	private void pesquisarColecaoTipoOcorrencia(HttpSession sessao) {
		FiltroOcorrenciaOperacionalTipo filtroOcorrenciaTipo = new FiltroOcorrenciaOperacionalTipo();
		filtroOcorrenciaTipo.adicionarParametro(new ParametroSimples(
			FiltroOcorrenciaOperacionalTipo.INDICADOR_USO, ConstantesSistema.SIM));
		filtroOcorrenciaTipo.setCampoOrderBy(FiltroOcorrenciaOperacionalTipo.DESCRICAO);
		
		Collection<OcorrenciaOperacionalTipo> colecaoOcorrenciaTipo = Fachada.getInstancia()
				.pesquisar(filtroOcorrenciaTipo,OcorrenciaOperacionalTipo.class.getName());
		
		if (colecaoOcorrenciaTipo != null && !colecaoOcorrenciaTipo.isEmpty()) {
			sessao.setAttribute("colecaoOcorrenciaTipo", colecaoOcorrenciaTipo);
		} else {
			sessao.removeAttribute("colecaoOcorrenciaTipo");
		}
	}

	/**
	 * [IT0016] Pesquisar Localidades
	 * */
	private void pesquisarColecaoLocalidadeInfo(HttpSession sessao) {
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(
			FiltroLocalidade.INDICADORUSO, ConstantesSistema.SIM));
		filtroLocalidade.setCampoOrderBy(FiltroLocalidade.DESCRICAO);
		
		Collection<Localidade> colecaoLocalidade = Fachada.getInstancia()
				.pesquisar(filtroLocalidade,Localidade.class.getName());
		
		if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
			sessao.setAttribute("colecaoLocalidadeInfo", colecaoLocalidade);
		} else {
			sessao.removeAttribute("colecaoLocalidadeInfo");
		}
	}
}
