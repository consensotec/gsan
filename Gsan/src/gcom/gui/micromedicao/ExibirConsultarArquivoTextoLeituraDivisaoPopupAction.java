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
package gcom.gui.micromedicao;

import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.ArquivoTextoRoteiroEmpresaDivisao;
import gcom.micromedicao.FiltroArquivoTextoRoteiroEmpresa;
import gcom.micromedicao.FiltroArquivoTextoRoteiroEmpresaDivisao;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
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
 * UC1027 - Exibir Consultar Arquivo Texto Leitura Divis�o.
 *  
 * @author Hugo Leonardo
 * @created 04/06/2010
 * 
 */
public class ExibirConsultarArquivoTextoLeituraDivisaoPopupAction extends GcomAction {

	/**
	 * 
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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirConsultarArquivoTextoLeituraDivisaoPopupAction");

		HttpSession sessao = httpServletRequest.getSession(false);

		ConsultarArquivoTextoLeituraDivisaoPopupActionForm form = 
			(ConsultarArquivoTextoLeituraDivisaoPopupActionForm) actionForm;

		FiltroArquivoTextoRoteiroEmpresaDivisao filtroArquivoTextoRoteiroEmpresaDivisao = null;		
			
		String idArqRoteiroEmpresa = null;
		if (httpServletRequest.getParameter("idArquivoTextoRoteiroEmpresa") != null
				|| sessao.getAttribute("idArquivoTextoRoteiroEmpresaDivisao") != null) {
			
			if(httpServletRequest.getParameter("idArquivoTextoRoteiroEmpresa") != null){
				
				idArqRoteiroEmpresa = (String) httpServletRequest.getParameter("idArquivoTextoRoteiroEmpresa");
				sessao.setAttribute("idArquivoTextoRoteiroEmpresaDivisao", idArqRoteiroEmpresa);
			}else{
				
				idArqRoteiroEmpresa = (String) sessao.getAttribute("idArquivoTextoRoteiroEmpresaDivisao");
			}					
			
			
			// pesquisa Arquivo Texto Roteiro Empresa Divis�o
			filtroArquivoTextoRoteiroEmpresaDivisao = new FiltroArquivoTextoRoteiroEmpresaDivisao();
			
			filtroArquivoTextoRoteiroEmpresaDivisao.adicionarParametro(new ParametroSimples(
					FiltroArquivoTextoRoteiroEmpresaDivisao.ARQUIVO_TEXTO_ROTEIRO_EMPRESA_ID,
					idArqRoteiroEmpresa));
			
			String[] orderby = new String[] {
					FiltroArquivoTextoRoteiroEmpresaDivisao.NUMERO_SEQUENCIA_LEITURA, 
					FiltroArquivoTextoRoteiroEmpresaDivisao.SITUACAO_TRANS_LEITURA};
			
			filtroArquivoTextoRoteiroEmpresaDivisao.adicionarCaminhoParaCarregamentoEntidade(
					FiltroArquivoTextoRoteiroEmpresaDivisao.ARQUIVO_TEXTO_ROTEIRO_EMPRESA);
			filtroArquivoTextoRoteiroEmpresaDivisao.adicionarCaminhoParaCarregamentoEntidade(
					FiltroArquivoTextoRoteiroEmpresa.SITUACAO_TRANS_LEITURA);
			filtroArquivoTextoRoteiroEmpresaDivisao.adicionarCaminhoParaCarregamentoEntidade(
					"arquivoTextoRoteiroEmpresa.localidade");
			filtroArquivoTextoRoteiroEmpresaDivisao.adicionarCaminhoParaCarregamentoEntidade(
					"arquivoTextoRoteiroEmpresa.rota");
			filtroArquivoTextoRoteiroEmpresaDivisao.adicionarCaminhoParaCarregamentoEntidade(
			"leiturista");
			filtroArquivoTextoRoteiroEmpresaDivisao.adicionarCaminhoParaCarregamentoEntidade(
			"leiturista.cliente");
			filtroArquivoTextoRoteiroEmpresaDivisao.adicionarCaminhoParaCarregamentoEntidade(
			"leiturista.funcionario");
			
			filtroArquivoTextoRoteiroEmpresaDivisao.setCampoOrderBy(orderby);
			
			Collection colecaoArquivoTextoRoteiroEmpresaDivisao = Fachada.getInstancia()
				.pesquisar(filtroArquivoTextoRoteiroEmpresaDivisao, ArquivoTextoRoteiroEmpresaDivisao.class.getName());
			
			if(colecaoArquivoTextoRoteiroEmpresaDivisao != null && !colecaoArquivoTextoRoteiroEmpresaDivisao.isEmpty()){
				
				ArquivoTextoRoteiroEmpresaDivisao arquivoTextoRoteiroEmpresaDivisao = 
					(ArquivoTextoRoteiroEmpresaDivisao) Util.retonarObjetoDeColecao(colecaoArquivoTextoRoteiroEmpresaDivisao);
				//Caso a situa��o de transmiss�o leitura do arquivo texto roteiro empresa n�o esteja com a situa��o de "Liberado"
				//ent�o exibe uma mensagem para liberar o arquivo.
				if(arquivoTextoRoteiroEmpresaDivisao.getArquivoTextoRoteiroEmpresa() != null && arquivoTextoRoteiroEmpresaDivisao.getArquivoTextoRoteiroEmpresa() != null &&
						!arquivoTextoRoteiroEmpresaDivisao.getArquivoTextoRoteiroEmpresa().getSituacaoTransmissaoLeitura().getId().equals(SituacaoTransmissaoLeitura.EM_CAMPO)){
					throw new ActionServletException("atencao.arquivo_texto_roteiro_empresa_nao_em_campo");
				}
				
				String idLocalidade = arquivoTextoRoteiroEmpresaDivisao.getArquivoTextoRoteiroEmpresa().getLocalidade().getId().toString();
				String nomeLocalidade = arquivoTextoRoteiroEmpresaDivisao.getArquivoTextoRoteiroEmpresa().getLocalidade().getDescricao();
				String idSetorComercial = arquivoTextoRoteiroEmpresaDivisao.getArquivoTextoRoteiroEmpresa().getCodigoSetorComercial1().toString();
				String codigoRota = arquivoTextoRoteiroEmpresaDivisao.getArquivoTextoRoteiroEmpresa().getRota().getCodigo().toString();
				
				// localidade
				form.setLocalidadeID(""+ idLocalidade);
				form.setLocalidadeNome(""+ nomeLocalidade);

				// setor comercial
				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, idSetorComercial));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.INDICADORUSO, ConstantesSistema.SIM));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.LOCALIDADE_ID, idLocalidade));

				Collection<SetorComercial> setorComercialPesquisada = Fachada.getInstancia().pesquisar(
						filtroSetorComercial, SetorComercial.class.getName());

				if (setorComercialPesquisada != null && !setorComercialPesquisada.isEmpty()) {
					SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(setorComercialPesquisada);
					
					form.setCodigoSetorComercial(""+ setorComercial.getCodigo());
				}
				
				// rota
				form.setCodigoRota(""+ codigoRota);
				
				sessao.setAttribute("colecaoArquivoTextoRoteiroEmpresaDivisao",
						colecaoArquivoTextoRoteiroEmpresaDivisao);
				
			}else{
				throw new ActionServletException("atencao.arquivo_texto_roteiro_empresa_divisao");
			}
		}

		sessao.setAttribute("achou","1");
		form.setIdsRegistros(new String[0]);

		return retorno;
	}

}
