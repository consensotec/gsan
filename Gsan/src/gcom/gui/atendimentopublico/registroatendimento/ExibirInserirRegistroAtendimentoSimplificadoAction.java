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

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("inserirRegistroAtendimentoSimplificado");
		
		InserirRegistroAtendimentoSimplificadoActionForm form = (InserirRegistroAtendimentoSimplificadoActionForm) actionForm;

		if (httpServletRequest.getParameter("menu") != null
				&& httpServletRequest.getParameter("menu").trim().equals("sim")) {
			
			//[IT0001] Selecionar sequ�ncia do protocolo
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

			// [FE0003] Validar munic�pio
			this.pesquisarMunicipio(form, sessao);
		
		} else if (httpServletRequest.getParameter("limparMunicipio") != null
				&& httpServletRequest.getParameter("limparMunicipio").trim().equals("sim")) {

			// [SB0001] Limpar Munic�pio
			this.limparMunicipio(form, sessao);
			
		} else if (httpServletRequest.getParameter("consultarOcorrencias") != null
				&& httpServletRequest.getParameter("consultarOcorrencias").trim().equals("sim")) {
			
			// [IT0009] Selecionar Lista das Ocorr�ncias Operacionais
			this.pesquisarOcorrenciasOperacionais(form, sessao);
				
		} else if (httpServletRequest.getParameter("limparOcorrencias") != null
				&& httpServletRequest.getParameter("limparOcorrencias").trim().equals("sim")) {
			
			sessao.removeAttribute("colecaoOcorrenciaOperacionalHelper");
				
		} 
		
		return retorno;
	}

	/**
	 * [IT0005] Pesquisar Localidades do Munic�pio
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
	 * [FE0003] Validar munic�pio
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
			form.setNomeMunicipioRA("MUNIC�PIO INEXISTENTE");
			sessao.setAttribute("municipioInexistente", true);
			
			sessao.removeAttribute("colecaoLocalidade");
			sessao.removeAttribute("colecaoBairro");
		}
	}

	/**
	 * [SB0001] Limpar Munic�pio
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
	 * [IT0009] Selecionar Lista das Ocorr�ncias Operacionais
	 * */
	private void pesquisarOcorrenciasOperacionais(InserirRegistroAtendimentoSimplificadoActionForm form, HttpSession sessao) {
		if (form.getIdMunicipioRA() == null
				|| form.getIdMunicipioRA().trim().equals("")) {
			
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", 
				null, "Munic�pio");
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
	 * [IT0005] Pesquisar Localidades do Munic�pio
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
	 * [IT0006] Pesquisar Bairros do Munic�pio
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
	 * [IT0002] Pesquisar �rg�o Expedidor
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
	 * [IT0007] Pesquisar Tipo de Ocorr�ncia
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
