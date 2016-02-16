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
package gcom.gui.mobile.execucaoordemservico;

import gcom.atendimentopublico.ordemservico.ArquivoTextoVisitaCampo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.mobile.execucaoordemservico.ArquivoTextoOSCobranca;
import gcom.mobile.execucaoordemservico.FiltroArquivoTextoOSCobranca;
import gcom.mobile.execucaoordemservico.bean.OrdemServicoCobrancaSmartphoneHelper;
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
 * [UC1499] Consultar Dados Arquivo Texto OS Cobran�a para Smartphone
 * 
 * @author Bruno Barros
 * @date 27/06/2013
 */
public class ExibirConsultarOrdemServicoCobrancaSmartphoneAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, 
		HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirConsultarOrdemServicoCobrancaSmartphone");
		ConsultarOrdemServicoCobrancaSmartphoneActionForm form = (ConsultarOrdemServicoCobrancaSmartphoneActionForm) actionForm;
		HttpSession sessao = httpServletRequest.getSession(false);
		Integer idArquivoTexto = null;
		
		if (httpServletRequest.getParameter("arquivoTexto") != null 
			&& !httpServletRequest.getParameter("arquivoTexto").toString().trim().equals("")) {
			
			idArquivoTexto = new Integer(httpServletRequest.getParameter("arquivoTexto"));
			sessao.setAttribute("idArquivoTexto", idArquivoTexto);
			
		} else if (sessao.getAttribute("idArquivoTexto") != null 
				   && !sessao.getAttribute("idArquivoTexto").toString().trim().equals("")) {
			
			idArquivoTexto = new Integer(sessao.getAttribute("idArquivoTexto").toString());
			
		} else {
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio","Arquivo Texto");
		}
		
		if (httpServletRequest.getAttribute("telaSucessoRelatorio") != null) {
			httpServletRequest.setAttribute("telaSucessoRelatorio", true);
		}
		
		// 1. O sistema exibe as informa��es do arquivo texto;
		this.carregarCampos(form, idArquivoTexto, sessao);
		
		// Tipo da Ordem de Servico		
		String idTipoOrdemServico = httpServletRequest.getParameter("idTipoOrdemServico");
		if (idTipoOrdemServico != null && !idTipoOrdemServico.equals("")){
			if ((Integer.valueOf(idTipoOrdemServico)).equals(OrdemServico.ORDEM_SERVICO_COBRANCA)){
				form.setIdTipoOS(idTipoOrdemServico);
				form.setDescricaoTipoOS(OrdemServico.DESCRICAO_ORDEM_SERVICO_COBRANCA);
			}
		}
		
		return retorno;
	}
	
	public void carregarCampos(ConsultarOrdemServicoCobrancaSmartphoneActionForm form, 
			Integer idArquivoTexto, HttpSession sessao){
		
		Fachada fachada = Fachada.getInstancia();
		
		// 1. O sistema exibe as informa��es do arquivo texto
		FiltroArquivoTextoOSCobranca filtro = new FiltroArquivoTextoOSCobranca();
		filtro.adicionarParametro(new ParametroSimples(FiltroArquivoTextoOSCobranca.ID, idArquivoTexto));
		filtro.adicionarCaminhoParaCarregamentoEntidade( "parametrosArquivoTextoOSCobranca" );
		filtro.adicionarCaminhoParaCarregamentoEntidade( "parametrosArquivoTextoOSCobranca.cobrancaGrupo" );
		filtro.adicionarCaminhoParaCarregamentoEntidade( "parametrosArquivoTextoOSCobranca.cobrancaAcaoAtividadeComando" );
		filtro.adicionarCaminhoParaCarregamentoEntidade( "parametrosArquivoTextoOSCobranca.servicoTipo" );
		filtro.adicionarCaminhoParaCarregamentoEntidade( "parametrosArquivoTextoOSCobranca.empresa" );
		filtro.adicionarCaminhoParaCarregamentoEntidade( "leiturista" );
		filtro.adicionarCaminhoParaCarregamentoEntidade( "leiturista.cliente" );
		filtro.adicionarCaminhoParaCarregamentoEntidade( "leiturista.funcionario" );
		
		Collection<ArquivoTextoVisitaCampo> colArquivoTexto = this.getFachada().pesquisar(filtro, ArquivoTextoOSCobranca.class.getName());
		
		ArquivoTextoOSCobranca arquivoTextoVisitaCampo = (ArquivoTextoOSCobranca) Util.retonarObjetoDeColecao(colArquivoTexto);

		if (arquivoTextoVisitaCampo.getParametrosArquivoTextoOSCobranca().getAnoMesReferenciaCronograma() != null) {
			form.setAnoMesReferenciaCronograma(Util.formatarAnoMesParaMesAno(arquivoTextoVisitaCampo.getParametrosArquivoTextoOSCobranca().getAnoMesReferenciaCronograma()));
		}
		if (arquivoTextoVisitaCampo.getParametrosArquivoTextoOSCobranca().getCobrancaGrupo() != null) {
			form.setCobrancaGrupo(arquivoTextoVisitaCampo.getParametrosArquivoTextoOSCobranca().getCobrancaGrupo().getDescricao().toString());
		}
		if (arquivoTextoVisitaCampo.getParametrosArquivoTextoOSCobranca().getCobrancaAcaoAtividadeComando() != null) {
			form.setTituloCobrancaAcaoAtividadeComando(arquivoTextoVisitaCampo.getParametrosArquivoTextoOSCobranca().getCobrancaAcaoAtividadeComando().getDescricaoTitulo());
			form.setDataRealizacaoCobrancaAcaoAtividadeComando(Util.formatarDataComHora(arquivoTextoVisitaCampo.getParametrosArquivoTextoOSCobranca().getCobrancaAcaoAtividadeComando().getRealizacao()));
		}
		if (arquivoTextoVisitaCampo.getParametrosArquivoTextoOSCobranca().getServicoTipo() != null) {
			form.setDescricaoServicoTipo(arquivoTextoVisitaCampo.getParametrosArquivoTextoOSCobranca().getServicoTipo().getDescricao());
		}
		if ( arquivoTextoVisitaCampo.getLeiturista().getCliente() != null ){
			form.setAgenteComercial(arquivoTextoVisitaCampo.getLeiturista().getCliente().getNome());
		} else {
			form.setAgenteComercial(arquivoTextoVisitaCampo.getLeiturista().getFuncionario().getNome() );
		}
		if(arquivoTextoVisitaCampo.getParametrosArquivoTextoOSCobranca().getEmpresa() != null){
			form.setEmpresa( arquivoTextoVisitaCampo.getParametrosArquivoTextoOSCobranca().getEmpresa().getId()+"" );
			form.setDescricaoEmpresa( arquivoTextoVisitaCampo.getParametrosArquivoTextoOSCobranca().getEmpresa().getDescricao() );
		}
			
		// 2. O sistema dever� exibir a lista das ordens de servi�o associadas ao arquivo texto 
		Collection<OrdemServicoCobrancaSmartphoneHelper> colecaoOrdemServicoCobrancaSmartphoneHelper = null;
		colecaoOrdemServicoCobrancaSmartphoneHelper = fachada.pesquisarDadosOrdensServicoCobrancaSmartphone(idArquivoTexto);
		sessao.setAttribute("colecaoOrdemServicoCobrancaSmartphoneHelper", colecaoOrdemServicoCobrancaSmartphoneHelper);
		
		Collection<Localidade> colecaoLocalidades =  fachada.pesquisarLocalidadesArquivo(idArquivoTexto);
		sessao.setAttribute("colecaoLocalidades", colecaoLocalidades);
		
/*		// 3.1.	Caso todas as ordens de servi�o estejam "Pendentes", 
		//   o sistema dever� disponibilizar o bot�o de "Atualizar Ordem de Servi�o". 
		if (colecaoOrdemServicoCobrancaSmartphoneHelper != null 
				&& !colecaoOrdemServicoCobrancaSmartphoneHelper.isEmpty()) {
			Iterator<?> iterator = colecaoOrdemServicoCobrancaSmartphoneHelper.iterator();
			boolean ordemServicoPendente = false;
			
			while(iterator.hasNext()) {
				OrdemServicoArquivoTextoHelper helper = (OrdemServicoArquivoTextoHelper) iterator.next();
				
				if (helper.getCodigoSituacao().trim().equalsIgnoreCase("1")) {
					ordemServicoPendente = true;
					break;
				}
			}
			
			if (ordemServicoPendente) {
				sessao.setAttribute("ordemServicoPendente", true);
			} else {
				sessao.removeAttribute("ordemServicoPendente");
			}
		} else {
			sessao.removeAttribute("ordemServicoPendente");
		}
*/	}
}


