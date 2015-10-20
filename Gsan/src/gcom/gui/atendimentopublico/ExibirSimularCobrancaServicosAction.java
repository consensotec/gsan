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
*/  package gcom.gui.atendimentopublico;import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;import gcom.atendimentopublico.ordemservico.ServicoTipo;import gcom.cadastro.imovel.FiltroImovel;import gcom.cadastro.imovel.Imovel;import gcom.fachada.Fachada;import gcom.gui.GcomAction;import gcom.util.Util;import gcom.util.filtro.ParametroSimples;import java.util.Collection;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;import javax.servlet.http.HttpSession;import org.apache.struts.action.ActionForm;import org.apache.struts.action.ActionForward;import org.apache.struts.action.ActionMapping;
/** * [UC1254] Relatório Ordem de Serviço com valores de cobrança * @author Amélia Pessoa * @date 24/11/2011 */
public class ExibirSimularCobrancaServicosAction extends GcomAction {	
	public ActionForward execute(ActionMapping actionMapping,			ActionForm actionForm, HttpServletRequest httpServletRequest,			HttpServletResponse httpServletResponse) {		// Seta o mapeamento de retorno		ActionForward retorno = actionMapping.findForward("exibirSimularCobrancaServicosAction");		SimularCobrancaServicoActionForm form = (SimularCobrancaServicoActionForm) actionForm;		HttpSession sessao = httpServletRequest.getSession(false);				// Verifica se entrou apartir do menu		if(httpServletRequest.getParameter("menu")!=null				&& httpServletRequest.getParameter("menu").toString().equalsIgnoreCase("sim")){			this.limpar(form, sessao);							} else if(httpServletRequest.getParameter("idImovel")!=null //Pesquisa Imovel				&& !httpServletRequest.getParameter("idImovel").toString().equals("")){			form.setIdImovel(httpServletRequest.getParameter("idImovel"));			getImovel(httpServletRequest,form);		}				//Tipo de serviço		if(form.getTipoServico() !=null &&	!form.getTipoServico().equals("")) {			this.getServicoTipo(form, httpServletRequest);					}				if(httpServletRequest.getParameter("calcular")!=null //[UC0475] – Obter Valor do Débito				&& httpServletRequest.getParameter("calcular").toString().equalsIgnoreCase("sim")){			Fachada fachada = Fachada.getInstancia();			String tipo = httpServletRequest.getParameter("tipomedicao");						form.setValorCalculado("R$ "+Util.formatarBigDecimalComPonto(fachada.obterValorDebito(Integer.parseInt(form.getTipoServico()), Integer.parseInt(form.getIdImovel()),					Short.parseShort(tipo))));			return retorno;		} 				return retorno;	}	private void getImovel(HttpServletRequest httpServletRequest,			SimularCobrancaServicoActionForm form) {		Fachada fachada = Fachada.getInstancia();				HttpSession sessao = httpServletRequest.getSession(false);				FiltroImovel filtroImovel = new FiltroImovel();		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, 				form.getIdImovel()));				filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOCALIDADE);		filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.SETOR_COMERCIAL);		filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.QUADRA);		filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_AGUA_SITUACAO);		filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_ESGOTO_SITUACAO);				Collection<?> colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());				if (colecaoImovel != null && !colecaoImovel.isEmpty()) {					Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);			sessao.setAttribute("inscricaoImovelEncontrada", "true");			form.setInscricaoImovel(imovel.getInscricaoFormatada());		} else {						this.limpar(form, sessao);			sessao.removeAttribute("inscricaoImovelEncontrada");			form.setIdImovel("");			form.setInscricaoImovel("Matrícula inexistente");					}	}
	private void limpar(SimularCobrancaServicoActionForm form,HttpSession sessao){		form.setInscricaoImovel(null);		form.setIdImovel(null);		sessao.removeAttribute("enderecoFormatado");		sessao.removeAttribute("inscricaoImovelEncontrada");	}	private void getServicoTipo(SimularCobrancaServicoActionForm form, HttpServletRequest httpServletRequest) {				Fachada fachada = Fachada.getInstancia();		HttpSession sessao = httpServletRequest.getSession(false);				// Filtra Tipo de Serviço		FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();		filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.ID, form.getTipoServico()));		filtroServicoTipo.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");				// Recupera Tipo de Serviço		Collection<?> colecaoServicoTipo = fachada.pesquisar(filtroServicoTipo, ServicoTipo.class.getName());				if (colecaoServicoTipo != null && !colecaoServicoTipo.isEmpty()) {			sessao.setAttribute("servicoTipoEncontrada", "true");			ServicoTipo servicoTipo = (ServicoTipo) colecaoServicoTipo.iterator().next();			form.setNomeTipoServico(servicoTipo.getDescricao());		} else {			sessao.removeAttribute("servicoTipoEncontrada");			form.setTipoServico("");			form.setNomeTipoServico("Tipo de Serviço inexistente");		}	}
}
