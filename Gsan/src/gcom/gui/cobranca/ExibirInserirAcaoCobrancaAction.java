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
package gcom.gui.cobranca;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaCriterio;
import gcom.cobranca.ColunasTextoSMSEmail;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.FiltroCobrancaAcao;
import gcom.cobranca.FiltroCobrancaCriterio;
import gcom.cobranca.FiltroColunasTextoSMSEmail;
import gcom.cobranca.FiltroDocumentoTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Pre- processamento para inserir o criterio da cobran�a
 * 
 * @author S�vio Luiz
 * @date 17/04/2006
 */
public class ExibirInserirAcaoCobrancaAction extends GcomAction {

	public ActionForward unspecified(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("inserirAcaoCobranca");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		AcaoCobrancaActionForm acaoCobrancaActionForm = (AcaoCobrancaActionForm) actionForm;
		acaoCobrancaActionForm.setIcCreditosARealizar("2");
		acaoCobrancaActionForm.setIcNotasPromissoria("2");
		
		acaoCobrancaActionForm.setIndicadorMensagemEmail("2");
		acaoCobrancaActionForm.setIndicadorMensagemSMS("2");
		
		// faz as pesquisas obrigat�rias
		pesquisasObrigatorias(fachada, sessao);

		return retorno;
	}
	
	
	@SuppressWarnings("unchecked")
	public ActionForward pesquisarCriterioCobranca(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping
				.findForward("inserirAcaoCobranca");
		
		AcaoCobrancaActionForm acaoCobrancaActionForm = (AcaoCobrancaActionForm) actionForm;
		FiltroCobrancaCriterio filtroCobrancaCriterio = new FiltroCobrancaCriterio();
		Fachada fachada = this.getFachada();
		
		try {
			filtroCobrancaCriterio
					.adicionarParametro(new ParametroSimples(
							FiltroCobrancaCriterio.ID, new Integer(
									acaoCobrancaActionForm
											.getIdCobrancaCriterio())));
		} catch (NumberFormatException ex) {
			throw new ActionServletException(
					"atencao.campo_texto.numero_obrigatorio", null,
					"Crit�rio de Cobran�a");
		}
		filtroCobrancaCriterio
				.setCampoOrderBy(FiltroCobrancaCriterio.DESCRICAO_COBRANCA_CRITERIO);
		Collection<CobrancaCriterio> colecaoCobrancaCriterio = fachada.pesquisar(
				filtroCobrancaCriterio, CobrancaCriterio.class.getName());

		if (colecaoCobrancaCriterio != null
				&& !colecaoCobrancaCriterio.isEmpty()) {
			CobrancaCriterio cobrancaCriterio = (CobrancaCriterio) Util
					.retonarObjetoDeColecao(colecaoCobrancaCriterio);
			acaoCobrancaActionForm
					.setDescricaoCobrancaCriterio(cobrancaCriterio
							.getDescricaoCobrancaCriterio());
		} else {
			acaoCobrancaActionForm.setIdCobrancaCriterio("");
			acaoCobrancaActionForm
					.setDescricaoCobrancaCriterio("COBRAN�A CRIT�RIO INEXISTENTE");
		}
		
		return retorno;
		
	}

	@SuppressWarnings("unchecked")
	public ActionForward pesquisarTipoServico(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping
				.findForward("inserirAcaoCobranca");
		
		AcaoCobrancaActionForm acaoCobrancaActionForm = (AcaoCobrancaActionForm) actionForm;
		Fachada fachada = this.getFachada();
		
		FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
		try {
			filtroServicoTipo.adicionarParametro(new ParametroSimples(
					FiltroServicoTipo.ID, new Integer(
							acaoCobrancaActionForm.getIdServicoTipo())));
		} catch (NumberFormatException ex) {
			throw new ActionServletException(
					"atencao.campo_texto.numero_obrigatorio", null,
					"Servi�o Tipo");
		}
		filtroServicoTipo.setCampoOrderBy(FiltroServicoTipo.DESCRICAO);
		Collection<ServicoTipo> colecaoServicoTipo = fachada.pesquisar(
				filtroServicoTipo, ServicoTipo.class.getName());

		if (colecaoServicoTipo != null && !colecaoServicoTipo.isEmpty()) {
			ServicoTipo servicoTipo = (ServicoTipo) Util
					.retonarObjetoDeColecao(colecaoServicoTipo);
			acaoCobrancaActionForm.setDescricaoServicoTipo(servicoTipo
					.getDescricao());
		} else {
			acaoCobrancaActionForm.setIdServicoTipo("");
			acaoCobrancaActionForm
					.setDescricaoServicoTipo("TIPO DE SERVI�O INEXISTENTE");
		}
		
		return retorno;
		
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward pesquisarDocumentoTipo(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping
				.findForward("inserirAcaoCobranca");
		
		AcaoCobrancaActionForm acaoCobrancaActionForm = (AcaoCobrancaActionForm) actionForm;
		Fachada fachada = this.getFachada();
		
		if(Util.verificarIdNaoVazio(acaoCobrancaActionForm.getIdTipoDocumentoGerado())){
			FiltroDocumentoTipo filtroDocumentoTipo = new FiltroDocumentoTipo();
			filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
			FiltroDocumentoTipo.ID, Integer.valueOf(acaoCobrancaActionForm.getIdTipoDocumentoGerado())));
			
			Collection<DocumentoTipo> colecaoDocumentoTipo = fachada.pesquisar(filtroDocumentoTipo, DocumentoTipo.class.getName());
			
			if (colecaoDocumentoTipo == null || colecaoDocumentoTipo.isEmpty()) {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Documento Tipo");
			}
	
			DocumentoTipo documentoTipo = (DocumentoTipo) Util.retonarObjetoDeColecao(colecaoDocumentoTipo);
			
			if (documentoTipo.getIndicadorTextoPersonalizado() != null &&
				documentoTipo.getIndicadorTextoPersonalizado().equals(ConstantesSistema.SIM)){
				
				httpServletRequest.setAttribute("exibirTextoPersonalizado", "OK");
			}
		}
		
		
		return retorno;
	}
	

	@SuppressWarnings("unchecked")
	private void pesquisasObrigatorias(Fachada fachada, HttpSession sessao) {
		// pesquisa as a��es predecessoras
		FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
		filtroCobrancaAcao.setCampoOrderBy(FiltroCobrancaAcao.DESCRICAO);
		filtroCobrancaAcao.adicionarParametro(new ParametroSimples(
				FiltroCobrancaAcao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection<CobrancaAcao> colecaoAcaoPredecessora = fachada.pesquisar(
				filtroCobrancaAcao, CobrancaAcao.class.getName());
		if (colecaoAcaoPredecessora == null
				|| colecaoAcaoPredecessora.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente",
					null, "Cobran�a A��o");
		} else {
			sessao.setAttribute("colecaoAcaoPredecessora",
					colecaoAcaoPredecessora);
		}
		
		filtroCobrancaAcao = new FiltroCobrancaAcao();
		filtroCobrancaAcao.setCampoOrderBy(FiltroCobrancaAcao.DESCRICAO);
		filtroCobrancaAcao.adicionarParametro(new ParametroSimples(
				FiltroCobrancaAcao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<CobrancaAcao> colecaoCobrancaAcaoValidacaoItem = fachada.pesquisar(
				filtroCobrancaAcao, CobrancaAcao.class.getName());
		if (colecaoCobrancaAcaoValidacaoItem == null
				|| colecaoCobrancaAcaoValidacaoItem.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente",
					null, "Cobran�a A��o");
		} else {
			sessao.setAttribute("colecaoCobrancaAcaoValidacaoItem",
					colecaoCobrancaAcaoValidacaoItem);
		}

		// pesquisa os tipos de documentos
		FiltroDocumentoTipo filtroDocumentoTipo = new FiltroDocumentoTipo();
		filtroDocumentoTipo.setCampoOrderBy(FiltroDocumentoTipo.DESCRICAO);
		filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
				FiltroDocumentoTipo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection<DocumentoTipo> colecaoDocumentoTipo = fachada.pesquisar(
				filtroDocumentoTipo, DocumentoTipo.class.getName());
		if (colecaoDocumentoTipo == null || colecaoDocumentoTipo.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente",
					null, "Documento Tipo");
		} else {
			sessao.setAttribute("colecaoDocumentoTipo", colecaoDocumentoTipo);
		}

		// pesquisa as situa��es de liga��es de agua
		FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
		filtroDocumentoTipo
				.setCampoOrderBy(FiltroLigacaoAguaSituacao.DESCRICAO);
		filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
				FiltroLigacaoAguaSituacao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection<LigacaoAguaSituacao> colecaoLigacaoAguaSituacao = fachada.pesquisar(
				filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
		if (colecaoLigacaoAguaSituacao == null
				|| colecaoLigacaoAguaSituacao.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente",
					null, "Liga��o Agua Situa��o");
		} else {
			sessao.setAttribute("colecaoLigacaoAguaSituacao",
					colecaoLigacaoAguaSituacao);
		}

		// pesquisa as situa��es de liga��es de agua
		FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
		filtroDocumentoTipo
				.setCampoOrderBy(FiltroLigacaoEsgotoSituacao.DESCRICAO);
		filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
				FiltroLigacaoEsgotoSituacao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection<LigacaoEsgotoSituacao> colecaoLigacaoEsgotoSituacao = fachada.pesquisar(
				filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class
						.getName());
		if (colecaoLigacaoEsgotoSituacao == null
				|| colecaoLigacaoEsgotoSituacao.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa_inexistente",
					null, "Liga��o Esgoto Situa��o");
		} else {
			sessao.setAttribute("colecaoLigacaoEsgotoSituacao",
					colecaoLigacaoEsgotoSituacao);
		}
		
		
		FiltroColunasTextoSMSEmail filtroColunasTXTSMS = new FiltroColunasTextoSMSEmail();
		filtroColunasTXTSMS.adicionarParametro(new ParametroSimples(FiltroColunasTextoSMSEmail.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroColunasTXTSMS.adicionarParametro(new ParametroSimples(FiltroColunasTextoSMSEmail.INDICADOR_COBRANCA,ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroColunasTXTSMS.setCampoOrderBy(FiltroColunasTextoSMSEmail.DESC_COLUNA);
		Collection<ColunasTextoSMSEmail> colecaoDadosTexto = fachada.pesquisar(filtroColunasTXTSMS, ColunasTextoSMSEmail.class.getName());
		sessao.setAttribute("colecaoDadosTexto", colecaoDadosTexto);
	}
	
	
	@SuppressWarnings("unchecked")
	public ActionForward validarTextoSMS(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		AcaoCobrancaActionForm form = (AcaoCobrancaActionForm) actionForm;
		HttpSession sessao = this.getSessao(httpServletRequest);
		
		Collection<ColunasTextoSMSEmail> colecaoColunas = (Collection<ColunasTextoSMSEmail>)sessao.getAttribute("colecaoDadosTexto");
		String idSelecionado = form.getDadosTexto();
		String textoSMS = form.getTextoSMS();
		SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();
		
		//[FS0011] - Verificar sele��o de dados
		if(!Util.verificarIdNaoVazio(idSelecionado)){

			try {
				httpServletResponse.getWriter().write("Selecione antes o dado que ser� inclu�do no texto");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			httpServletResponse.setStatus(666);
			return null;
		}
		
		//[FS0012] - Validar quantidade de colunas digitadas/selecionadas	
		//Calculando o total das tags inseridas
		int tamanho = 0;
		Iterator<ColunasTextoSMSEmail> it = colecaoColunas.iterator();
		Integer tamanhoSelecionado = 0;
		String tagSelecionada = "";
		while(it.hasNext()){
			ColunasTextoSMSEmail col = it.next();
			if(col.getId().toString().equals(idSelecionado)){
				tamanhoSelecionado = col.getTamanhoColuna();
				tagSelecionada = col.getNomeColuna();
			}	
			while(textoSMS.contains(col.getNomeColuna())){
				tamanho += col.getTamanhoColuna();
				int index = textoSMS.indexOf(col.getNomeColuna());
				textoSMS = textoSMS.substring(0, index) + textoSMS.substring(index + col.getNomeColuna().length());
			}
		}
		
		tamanho += tamanhoSelecionado + textoSMS.length() + 57;
		//Caso a contagem de caracteres digitados mais o n�mero de d�gitos do campo adicionado
		//for maior que o n�mero m�ximo de colunas da mensagem SMS
		
		if(tamanho > sistemaParametro.getTamanhoMaximoMensagemSms().intValue()){
			try {
				httpServletResponse.getWriter().write("N�mero de colunas digitadas/selecionadas somada �s 57 posi��es do c�digo de barras � maior que o m�ximo permitido ("+sistemaParametro.getTamanhoMaximoMensagemSms()+")");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			httpServletResponse.setStatus(666);
			return null;
		}
		else{
			try {
				httpServletResponse.getWriter().write(tagSelecionada);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	public ActionForward validarTextoEmail(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		AcaoCobrancaActionForm form = (AcaoCobrancaActionForm) actionForm;
		HttpSession sessao = this.getSessao(httpServletRequest);
		
		Collection<ColunasTextoSMSEmail> colecaoColunas = (Collection<ColunasTextoSMSEmail>)sessao.getAttribute("colecaoDadosTexto");
		String idSelecionado = form.getDadosTexto();
		
		Iterator<ColunasTextoSMSEmail> it = colecaoColunas.iterator();
		while(it.hasNext()){
			ColunasTextoSMSEmail col = it.next();
			if(col.getId().toString().equals(idSelecionado)){
				try {
					httpServletResponse.getWriter().write(col.getNomeColuna());
					return null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}	
		}
		return null;
	}
	
}
