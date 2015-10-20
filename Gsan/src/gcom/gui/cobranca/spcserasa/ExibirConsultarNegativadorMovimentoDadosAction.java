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
 * Yara Taciane de Souza
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
package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.FiltroNegativadorRegistroTipo;
import gcom.cobranca.NegativadorMovimento;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.spcserasa.FiltroNegativadorMovimento;
import gcom.spcserasa.bean.NegativadorMovimentoHelper;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action de Exibir Atualizar Negativador Registro Tipo
 * 
 * @author Yara Taciane
 * @created 08/01/2008
 */

public class ExibirConsultarNegativadorMovimentoDadosAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Inicializando Variaveis
		ActionForward retorno = actionMapping.findForward("consultarNegativadorMovimentoDados");
		
		ConsultarNegativadorMovimentoActionForm consultarNegativadorMovimentoActionForm = (ConsultarNegativadorMovimentoActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// volta da msg de Negativador Exclusao Motivo j� utilizado, n�o pode ser
		// alterado nem exclu�do.
		String confirmado = httpServletRequest.getParameter("confirmado");

		String idNegativadorMovimento = null;
		if (httpServletRequest.getParameter("reload") == null
				|| httpServletRequest.getParameter("reload").equalsIgnoreCase(
						"") && (confirmado == null || confirmado.equals(""))) {
			// Recupera o id do Negativador  Motivo que vai ser atualizado

			if (httpServletRequest.getParameter("idRegistroInseridoAtualizar") != null) {
				idNegativadorMovimento = httpServletRequest.getParameter("idRegistroInseridoAtualizar");				
				httpServletRequest.setAttribute("voltar", "filtrar");
				sessao.setAttribute("idRegistroAtualizacao",idNegativadorMovimento);
				
			} else if (httpServletRequest.getParameter("idRegistroAtualizacao") == null) {
				idNegativadorMovimento = (String) sessao.getAttribute("idRegistroAtualizacao");			
				httpServletRequest.setAttribute("voltar", "filtrar");
				
			} else if (httpServletRequest.getParameter("idRegistroAtualizacao") != null) {
				idNegativadorMovimento = httpServletRequest.getParameter("idRegistroAtualizacao");					
				httpServletRequest.setAttribute("voltar", "manter");
				sessao.setAttribute("idRegistroAtualizacao",idNegativadorMovimento);
			}
		} else {
			idNegativadorMovimento = (String) sessao.getAttribute("idRegistroAtualizacao");
		}
		
		/////////////////////////////////////////////////////////
		if (httpServletRequest.getParameter("atualizar") != null
			&& !httpServletRequest.getParameter("atualizar").equalsIgnoreCase("")){
			
			String[] idRegistrosCorrecao = consultarNegativadorMovimentoActionForm.getIdRegistrosCorrecao();
			
			if (idRegistrosCorrecao == null || idRegistrosCorrecao.length < 1) {
	            throw new ActionServletException("atencao.registros.nao_selecionados_atualizacao");
	        } 
			
			if(consultarNegativadorMovimentoActionForm.getIndicadorCorrecao() == null 
				|| consultarNegativadorMovimentoActionForm.getIndicadorCorrecao()
				.equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
				 throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Corrigir");
			}
			
			Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
			Short indicadorCorrecao = new Short(consultarNegativadorMovimentoActionForm.getIndicadorCorrecao());
			Collection colecaoIdRegistrosCorrecao = new ArrayList();
			for (int i = 0; i < idRegistrosCorrecao.length; i++) {
				colecaoIdRegistrosCorrecao.add(idRegistrosCorrecao[i]);
			}
			
			fachada.atualizarIndicadorCorrecaoEUsuarioCorrecao(usuarioLogado.getId(), 
					indicadorCorrecao, colecaoIdRegistrosCorrecao);
		}
		
		////////////////////////////////////////////////////////

		// Verifica se o usu�rio est� selecionando o Negativador  da
		// p�gina de manter
		// Caso contr�rio o usu�rio est� teclando enter na p�gina de atualizar
		if ((idNegativadorMovimento != null && !idNegativadorMovimento.equals(""))
				&& (httpServletRequest.getParameter("desfazer") == null)
				&& (httpServletRequest.getParameter("reload") == null || httpServletRequest
						.getParameter("reload").equalsIgnoreCase(""))) {
			exibirNegativadorMovimento(idNegativadorMovimento,
					consultarNegativadorMovimentoActionForm, fachada, sessao,
					httpServletRequest, retorno);

		}	
		Integer idNegativadorMov = Integer.parseInt(idNegativadorMovimento);
		
		NegativadorMovimentoHelper negativadorMovimentoHelper = null;
		
//		 Verifica se o filtro foi informado pela p�gina de filtragem do Negativador
		if (sessao.getAttribute("negativadorMovimentoHelper") != null) {
			negativadorMovimentoHelper = ((NegativadorMovimentoHelper) sessao.getAttribute("negativadorMovimentoHelper")).clone();
	    	negativadorMovimentoHelper.setIdNegativadorMovimento(idNegativadorMov);   	
	    	    	
		}
		 

		NegativadorMovimento nm = (NegativadorMovimento) sessao.getAttribute("negativadorMovimento");
			
		if (consultarNegativadorMovimentoActionForm.getIdNegativador() == null || "".equals(consultarNegativadorMovimentoActionForm.getIdNegativador())){
			
			if(nm!= null && nm.getNegativador()!= null){
				
				Integer idNegativador = nm.getNegativador().getId();				
				negativadorMovimentoHelper.setIdNegativador(idNegativador);
			}
			
		}
		

		
		Collection collNegativadorMovimentoReg = fachada.pesquisarNegativadorMovimentoRegistroAceito(negativadorMovimentoHelper);
		
		if (!collNegativadorMovimentoReg.isEmpty()){
//			1� Passo - Pegar o total de registros atrav�s de um count da consulta que aparecer� na tela
			Integer totalRegistros = new Integer(collNegativadorMovimentoReg.size()); 

			// 2� Passo - Chamar a fun��o de Pagina��o passando o total de registros
			retorno = this.controlarPaginacao(httpServletRequest, retorno,	totalRegistros);

			// 3� Passo - Obter a cole��o da consulta que aparecer� na tela passando o numero de paginas
			// da pesquisa que est� no request
			
			collNegativadorMovimentoReg = Fachada.getInstancia().pesquisarNegativadorMovimentoRegistroAceito(negativadorMovimentoHelper,(Integer) httpServletRequest
					.getAttribute("numeroPaginasPesquisa"));
			
			sessao.setAttribute("collNegativadorMovimentoReg", collNegativadorMovimentoReg);
		}

		
		return retorno;

	}

	private void exibirNegativadorMovimento(
			String idNegativadorMovimento,
			ConsultarNegativadorMovimentoActionForm consultarNegativadorMovimentoActionForm,
			Fachada fachada, HttpSession sessao,
			HttpServletRequest httpServletRequest,
			ActionForward retorno) {

		Integer idNegativadorMov = Integer.parseInt(idNegativadorMovimento);
		
		// Cria a vari�vel que vai armazenar o negativador registro tipo para ser
		// atualizado
		NegativadorMovimento negativadorMovimento = null;
		

		// Cria o filtro de NegativadorMovimento e seta o id do
		// NegativadorMovimento para ser atualizado no filtro
		// e indica quais objetos devem ser retornados pela pesquisa
		
		FiltroNegativadorMovimento filtroNegativadorMovimento = new FiltroNegativadorMovimento();
		filtroNegativadorMovimento.adicionarParametro(new ParametroSimples(
				FiltroNegativadorRegistroTipo.ID, idNegativadorMovimento));

		filtroNegativadorMovimento.adicionarCaminhoParaCarregamentoEntidade("negativador");
		filtroNegativadorMovimento.adicionarCaminhoParaCarregamentoEntidade("negativador.cliente");

		Collection<NegativadorMovimento> collectionNegativadorMovimento = fachada
				.pesquisar(filtroNegativadorMovimento, NegativadorMovimento.class
						.getName());	
		

		

		// Caso a pesquisa tenha retornado o NegativadorMovimento
		if (collectionNegativadorMovimento != null
				&& !collectionNegativadorMovimento.isEmpty()) {

			// Recupera da cole��o o NegativadorMovimento que vai ser atualizado
			negativadorMovimento = (NegativadorMovimento) Util
					.retonarObjetoDeColecao(collectionNegativadorMovimento);
			
			
			// Seta no form os dados de NegativadorMovimento
			
			if(negativadorMovimento.getNegativador()!= null &&  negativadorMovimento.getNegativador().getCliente()!= null){
				consultarNegativadorMovimentoActionForm.setNegativador(negativadorMovimento.getNegativador().getCliente().getNome());
			}else{
				consultarNegativadorMovimentoActionForm.setNegativador("");
			}
		
			if (negativadorMovimento.getNumeroSequencialEnvio() != null
					&& !negativadorMovimento.getNumeroSequencialEnvio().equals("")) {

				consultarNegativadorMovimentoActionForm.setNumeroSequencialEnvio(""
						+ negativadorMovimento.getNumeroSequencialEnvio());			
			} else {
				consultarNegativadorMovimentoActionForm
						.setNumeroSequencialEnvio("");
			}
			
			
			if (negativadorMovimento.getCodigoMovimento() != -1) {

				consultarNegativadorMovimentoActionForm.setCodigoMovimento(""
						+ negativadorMovimento.getCodigoMovimento());			
			} else {
				consultarNegativadorMovimentoActionForm
						.setCodigoMovimento("");
			}
			
			if (negativadorMovimento.getCodigoMovimento() != -1) {

				consultarNegativadorMovimentoActionForm.setCodigoMovimento(""
						+ negativadorMovimento.getCodigoMovimento());			
			} else {
				consultarNegativadorMovimentoActionForm
						.setCodigoMovimento("");
			}
			
			if (negativadorMovimento.getNumeroRegistrosEnvio() != null && !negativadorMovimento.getNumeroRegistrosEnvio().equals("")) {

				consultarNegativadorMovimentoActionForm.setNumeroRegistrosEnvio(""
						+ negativadorMovimento.getNumeroRegistrosEnvio());			
			} else {
				consultarNegativadorMovimentoActionForm
						.setNumeroRegistrosEnvio("");
			}
			
			if (negativadorMovimento.getValorTotalEnvio() != null && !negativadorMovimento.getValorTotalEnvio().equals("")) {

				consultarNegativadorMovimentoActionForm.setValorTotalEnvio(""
						+ negativadorMovimento.getValorTotalEnvio().setScale(2));			
			} else {
				consultarNegativadorMovimentoActionForm
						.setValorTotalEnvio("");
			}
		
			
			if (negativadorMovimento.getDataProcessamentoEnvio() != null
					&& !negativadorMovimento.getDataProcessamentoEnvio()
							.equals("")) {

				Date dataEnvio = Util.formatarDataSemHora(negativadorMovimento.getDataProcessamentoEnvio());
				consultarNegativadorMovimentoActionForm.setDataEnvio(Util.formatarData(dataEnvio));

				String horaEnvio = Util.formatarHoraSemData(negativadorMovimento.getDataProcessamentoEnvio());
				consultarNegativadorMovimentoActionForm.setHoraEnvio(horaEnvio);
			} else {
				consultarNegativadorMovimentoActionForm.setDataEnvio("");
				consultarNegativadorMovimentoActionForm.setHoraEnvio("");
			}
					
			
			if(negativadorMovimento.getNumeroRegistrosRetorno() != null && !negativadorMovimento.getNumeroRegistrosRetorno().equals("")){
				consultarNegativadorMovimentoActionForm.setSituacaoMovimento("Com Retorno");
			}else{
				consultarNegativadorMovimentoActionForm.setSituacaoMovimento("Sem Retorno");
			}
			
			//total de registros aceitos			
			Integer totalRegistrosAceitos= fachada.verificarTotalRegistrosAceitos(idNegativadorMov);
			consultarNegativadorMovimentoActionForm.setTotalRegistrosAceitos(totalRegistrosAceitos.toString());

			
			if (negativadorMovimento.getDataProcessamentoRetorno() != null
					&& !negativadorMovimento.getDataProcessamentoRetorno()
							.equals("")) {

				Date dataRetorno = Util
						.formatarDataSemHora(negativadorMovimento
								.getDataProcessamentoRetorno());
				consultarNegativadorMovimentoActionForm.setDataRetorno(Util
						.formatarData(dataRetorno));

				String horaRetorno = Util
						.formatarHoraSemData(negativadorMovimento
								.getDataProcessamentoRetorno());
				consultarNegativadorMovimentoActionForm
						.setHoraRetorno(horaRetorno);
			} else {
				consultarNegativadorMovimentoActionForm.setDataRetorno("");
				consultarNegativadorMovimentoActionForm.setHoraRetorno("");
			}
			
			sessao.setAttribute("negativadorMovimento", negativadorMovimento);
		}
	}
}