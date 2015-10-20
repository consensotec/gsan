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

import gcom.cadastro.imovel.Categoria;
import gcom.cobranca.CobrancaSituacaoTipo;
import gcom.cobranca.FiltroCobrancaSituacaoTipo;
import gcom.cobranca.bean.SituacaoEspecialCobrancaHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
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

public class ValidarSituacaoEspecialCobrancaInserirAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		SituacaoEspecialCobrancaActionForm situacaoEspecialCobrancaActionForm = (SituacaoEspecialCobrancaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);

		// Comparar Ano Mes Referencia
		String mesAnoReferenciaCobrancaInicial = situacaoEspecialCobrancaActionForm
				.getMesAnoReferenciaCobrancaInicial();
		boolean mesAnoReferenciaInicialValido = Util
				.validarMesAno(mesAnoReferenciaCobrancaInicial);

		String mesAnoReferenciaCobrancaFinal = situacaoEspecialCobrancaActionForm
				.getMesAnoReferenciaCobrancaFinal();

		boolean mesAnoReferenciaFinalValido = Util
				.validarMesAno(mesAnoReferenciaCobrancaFinal);

		Integer anoMesReferenciaInicial = null;
		Integer anoMesReferenciaFinal = null;

		if ((mesAnoReferenciaCobrancaInicial != null && mesAnoReferenciaCobrancaFinal != null)
				&& (!mesAnoReferenciaCobrancaInicial.equals("") && !mesAnoReferenciaCobrancaFinal
						.equals(""))) {
			if (!mesAnoReferenciaInicialValido) {
				throw new ActionServletException(
						"atencao.adicionar_debito_ano_mes_referencia_invalido",
						null, "inicial");
			}
			if (!mesAnoReferenciaFinalValido) {
				throw new ActionServletException(
						"atencao.adicionar_debito_ano_mes_referencia_invalido",
						null, "final");

			}
			anoMesReferenciaInicial = Util
					.formatarMesAnoComBarraParaAnoMes(mesAnoReferenciaCobrancaInicial);

			anoMesReferenciaFinal = Util
					.formatarMesAnoComBarraParaAnoMes(mesAnoReferenciaCobrancaFinal);

			boolean dataInicialSuperiorMenor = Util.compararAnoMesReferencia(
					new Integer(anoMesReferenciaInicial), new Integer(
							anoMesReferenciaFinal), "<");

			boolean dataInicialSuperiorIgual = Util.compararAnoMesReferencia(
					new Integer(anoMesReferenciaInicial), new Integer(
							anoMesReferenciaFinal), "=");

			if (dataInicialSuperiorMenor || dataInicialSuperiorIgual) {

//				Integer anoMesInicial = fachada
//						.validarMesAnoReferenciaCobranca(transferirActionFormParaHelper(
//						situacaoEspecialCobrancaActionForm, usuarioLogado));
				
				Integer anoMesInicial = fachada.pesquisarParametrosDoSistema().getAnoMesFaturamento();
				
				if (anoMesInicial > (anoMesReferenciaInicial)) {

					throw new ActionServletException(
							"atencao.mes.ano.anterior.mes.ano.corrente.faturamento");

				}
			} else {
				throw new ActionServletException(
						"atencao.mes.ano.inicial.cobranca.maior.mes.ano.final.cobranca");

			}
		} else {
			throw new ActionServletException("atencao.campo_texto.obrigatorio",
					null, "M�s e Ano de Refer~encia do Cobran�aa Inicial e Final");
		}

		SituacaoEspecialCobrancaHelper situacaoEspecialCobrancaHelper = transferirActionFormParaHelper(
		situacaoEspecialCobrancaActionForm, usuarioLogado);

		/*Collection pesquisarImoveisParaSerInseridos = fachada
				.pesquisarImovelSituacaoEspecialCobranca("SEM",
						situacaoEspecialCobrancaHelper);*/
		
		Collection pesquisarImoveisParaSerInseridos = (Collection) sessao.getAttribute("SEMSituacaoEspecialCobranca");

		fachada.inserirSituacaoEspecialCobranca(
				pesquisarImoveisParaSerInseridos, 
				situacaoEspecialCobrancaHelper,
				usuarioLogado,
				new Integer(situacaoEspecialCobrancaActionForm.getIdCobrancaSituacaoTipo()),
				anoMesReferenciaInicial,
				anoMesReferenciaFinal);

		FiltroCobrancaSituacaoTipo filtroCobrancaSituacaoTipo = new FiltroCobrancaSituacaoTipo();

		filtroCobrancaSituacaoTipo
				.adicionarParametro(new ParametroSimples(
						FiltroCobrancaSituacaoTipo.ID,
						situacaoEspecialCobrancaActionForm
								.getIdCobrancaSituacaoTipo()));
		Collection collectionCobrancaSituacaoTipo = fachada.pesquisar(
				filtroCobrancaSituacaoTipo, CobrancaSituacaoTipo.class
						.getName());
		String descricaoCobrancaSituacaoTipo = ((CobrancaSituacaoTipo) Util
				.retonarObjetoDeColecao(collectionCobrancaSituacaoTipo))
				.getDescricao();

		
		montarPaginaSucesso(
				httpServletRequest,
				situacaoEspecialCobrancaActionForm
						.getQuantidadeImoveisSEMSituacaoEspecialCobranca()
						+ " im�vel(eis) inserido(s) na Situa��o Especial de Cobran�a "
						+ descricaoCobrancaSituacaoTipo + " com sucesso.",
				"Realizar outra Manuten��o de Situa��o Especial de Cobran�a",
				"exibirSituacaoEspecialCobrancaInformarAction.do?menu=sim");

		return retorno;
	}

	private SituacaoEspecialCobrancaHelper transferirActionFormParaHelper(
			SituacaoEspecialCobrancaActionForm situacaoEspecialCobrancaActionForm,
			Usuario usuarioLogado) {

		SituacaoEspecialCobrancaHelper situacaoEspecialCobrancaHelper = new SituacaoEspecialCobrancaHelper();

		situacaoEspecialCobrancaHelper
				.setIdImovel(situacaoEspecialCobrancaActionForm.getIdImovel() == null ? ""
						: situacaoEspecialCobrancaActionForm.getIdImovel());

		situacaoEspecialCobrancaHelper
				.setInscricaoTipo(situacaoEspecialCobrancaActionForm
						.getInscricaoTipo() == null ? ""
						: situacaoEspecialCobrancaActionForm.getInscricaoTipo());

		situacaoEspecialCobrancaHelper
				.setLoteDestino(situacaoEspecialCobrancaActionForm
						.getLoteDestino() == null ? ""
						: situacaoEspecialCobrancaActionForm.getLoteDestino());

		situacaoEspecialCobrancaHelper
				.setQuadraDestinoNM(situacaoEspecialCobrancaActionForm
						.getQuadraDestinoNM() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getQuadraDestinoNM());

		situacaoEspecialCobrancaHelper
				.setLoteOrigem(situacaoEspecialCobrancaActionForm
						.getLoteOrigem() == null ? ""
						: situacaoEspecialCobrancaActionForm.getLoteOrigem());

		situacaoEspecialCobrancaHelper
				.setNomeLocalidadeOrigem(situacaoEspecialCobrancaActionForm
						.getNomeLocalidadeOrigem() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getNomeLocalidadeOrigem());

		situacaoEspecialCobrancaHelper
				.setNomeSetorComercialOrigem(situacaoEspecialCobrancaActionForm
						.getNomeSetorComercialOrigem() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getNomeSetorComercialOrigem());

		situacaoEspecialCobrancaHelper
				.setQuadraOrigemNM(situacaoEspecialCobrancaActionForm
						.getQuadraOrigemNM() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getQuadraOrigemNM());

		situacaoEspecialCobrancaHelper
				.setQuadraMensagemOrigem(situacaoEspecialCobrancaActionForm
						.getQuadraMensagemOrigem() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getQuadraMensagemOrigem());

		situacaoEspecialCobrancaHelper
				.setNomeLocalidadeDestino(situacaoEspecialCobrancaActionForm
						.getNomeLocalidadeDestino() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getNomeLocalidadeDestino());

		situacaoEspecialCobrancaHelper
				.setSetorComercialDestinoCD(situacaoEspecialCobrancaActionForm
						.getSetorComercialDestinoCD() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getSetorComercialDestinoCD());

		situacaoEspecialCobrancaHelper
				.setSetorComercialOrigemCD(situacaoEspecialCobrancaActionForm
						.getSetorComercialOrigemCD() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getSetorComercialOrigemCD());

		situacaoEspecialCobrancaHelper
				.setSetorComercialOrigemID(situacaoEspecialCobrancaActionForm
						.getSetorComercialOrigemID() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getSetorComercialOrigemID());

		situacaoEspecialCobrancaHelper
				.setQuadraOrigemID(situacaoEspecialCobrancaActionForm
						.getQuadraOrigemID() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getQuadraOrigemID());

		situacaoEspecialCobrancaHelper
				.setLocalidadeDestinoID(situacaoEspecialCobrancaActionForm
						.getLocalidadeDestinoID() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getLocalidadeDestinoID());

		situacaoEspecialCobrancaHelper
				.setLocalidadeOrigemID(situacaoEspecialCobrancaActionForm
						.getLocalidadeOrigemID() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getLocalidadeOrigemID());

		situacaoEspecialCobrancaHelper
				.setNomeSetorComercialDestino(situacaoEspecialCobrancaActionForm
						.getNomeSetorComercialDestino() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getNomeSetorComercialDestino());

		situacaoEspecialCobrancaHelper
				.setSetorComercialDestinoID(situacaoEspecialCobrancaActionForm
						.getSetorComercialDestinoID() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getSetorComercialDestinoID());

		situacaoEspecialCobrancaHelper
				.setQuadraMensagemDestino(situacaoEspecialCobrancaActionForm
						.getQuadraMensagemDestino() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getQuadraMensagemDestino());

		situacaoEspecialCobrancaHelper
				.setQuadraDestinoID(situacaoEspecialCobrancaActionForm
						.getQuadraDestinoID() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getQuadraDestinoID());

		situacaoEspecialCobrancaHelper
				.setTipoSituacaoEspecialCobranca(situacaoEspecialCobrancaActionForm
						.getTipoSituacaoEspecialCobranca() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getTipoSituacaoEspecialCobranca());

		situacaoEspecialCobrancaHelper
				.setLoteOrigem(situacaoEspecialCobrancaActionForm
						.getLoteOrigem() == null ? ""
						: situacaoEspecialCobrancaActionForm.getLoteOrigem());

		situacaoEspecialCobrancaHelper
				.setLoteDestino(situacaoEspecialCobrancaActionForm
						.getLoteDestino() == null ? ""
						: situacaoEspecialCobrancaActionForm.getLoteDestino());

		situacaoEspecialCobrancaHelper
				.setSubloteOrigem(situacaoEspecialCobrancaActionForm
						.getSubloteOrigem() == null ? ""
						: situacaoEspecialCobrancaActionForm.getSubloteOrigem());

		situacaoEspecialCobrancaHelper
				.setSubloteDestino(situacaoEspecialCobrancaActionForm
						.getSubloteDestino() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getSubloteDestino());

		situacaoEspecialCobrancaHelper
				.setIdCobrancaSituacaoMotivo(situacaoEspecialCobrancaActionForm
						.getIdCobrancaSituacaoMotivo() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getIdCobrancaSituacaoMotivo());

		situacaoEspecialCobrancaHelper
				.setIdCobrancaSituacaoTipo(situacaoEspecialCobrancaActionForm
						.getIdCobrancaSituacaoTipo() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getIdCobrancaSituacaoTipo());

		situacaoEspecialCobrancaHelper
				.setMesAnoReferenciaCobrancaInicial(situacaoEspecialCobrancaActionForm
						.getMesAnoReferenciaCobrancaInicial() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getMesAnoReferenciaCobrancaInicial());

		situacaoEspecialCobrancaHelper
				.setMesAnoReferenciaCobrancaFinal(situacaoEspecialCobrancaActionForm
						.getMesAnoReferenciaCobrancaFinal() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getMesAnoReferenciaCobrancaFinal());

		situacaoEspecialCobrancaHelper
				.setQuantidadeImoveisCOMSituacaoEspecialCobranca(situacaoEspecialCobrancaActionForm
						.getQuantidadeImoveisCOMSituacaoEspecialCobranca() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getQuantidadeImoveisCOMSituacaoEspecialCobranca());

		situacaoEspecialCobrancaHelper
				.setQuantidadeImoveisSEMSituacaoEspecialCobranca(situacaoEspecialCobrancaActionForm
						.getQuantidadeImoveisSEMSituacaoEspecialCobranca() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getQuantidadeImoveisSEMSituacaoEspecialCobranca());

		situacaoEspecialCobrancaHelper
				.setQuantidadeImoveisAtualizados(situacaoEspecialCobrancaActionForm
						.getQuantidadeImoveisAtualizados() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getQuantidadeImoveisAtualizados());
		
		situacaoEspecialCobrancaHelper
		.setCodigoRotaInicial(situacaoEspecialCobrancaActionForm
				.getCdRotaInicial() == null ? ""
				: situacaoEspecialCobrancaActionForm.getCdRotaInicial());
		
		situacaoEspecialCobrancaHelper
		.setCodigoRotaFinal(situacaoEspecialCobrancaActionForm
				.getCdRotaFinal() == null ? ""
				: situacaoEspecialCobrancaActionForm.getCdRotaFinal());
		
		situacaoEspecialCobrancaHelper
		.setSequencialRotaInicial(situacaoEspecialCobrancaActionForm
				.getSequencialRotaInicial() == null ? ""
				: situacaoEspecialCobrancaActionForm.getSequencialRotaInicial());
		
		situacaoEspecialCobrancaHelper
		.setSequencialRotaFinal(situacaoEspecialCobrancaActionForm
				.getSequencialRotaFinal() == null ? ""
				: situacaoEspecialCobrancaActionForm.getSequencialRotaFinal());
		
		//Colocado por Raphael Rossiter em 11/08/2008 - Analista:Rosana Carvalho
		if (situacaoEspecialCobrancaActionForm.getObservacaoInforma() != null &&
			!situacaoEspecialCobrancaActionForm.getObservacaoInforma().equals("")){
						
			situacaoEspecialCobrancaHelper.setObservacaoInforma(
			situacaoEspecialCobrancaActionForm.getObservacaoInforma().trim());
		}
		
		situacaoEspecialCobrancaHelper.setIdUsuarioInforma(usuarioLogado.getId().toString());
		
		
		situacaoEspecialCobrancaHelper.setIdsCategoria(situacaoEspecialCobrancaActionForm.getIdsCategoria());
		
		if (situacaoEspecialCobrancaActionForm.getIdsCategoria() != null) {
			
			String [] idsCategoria = situacaoEspecialCobrancaActionForm.getIdsCategoria();
			
			for (int i = 0; i < idsCategoria.length; i++) {
				
				if (idsCategoria[i].equals(Categoria.COMERCIAL.toString())) {
					situacaoEspecialCobrancaHelper.setIndicadorComercial(ConstantesSistema.SIM.toString());
				} else if (idsCategoria[i].equals(Categoria.INDUSTRIAL.toString())) {
					situacaoEspecialCobrancaHelper.setIndicadorIndustrial(ConstantesSistema.SIM.toString());
				} else if (idsCategoria[i].equals(Categoria.RESIDENCIAL.toString())) {
					situacaoEspecialCobrancaHelper.setIndicadorResidencial(ConstantesSistema.SIM.toString());
				} else if (idsCategoria[i].equals(Categoria.PUBLICO.toString())) {
					situacaoEspecialCobrancaHelper.setIndicadorPublico(ConstantesSistema.SIM.toString());
				}
				
			}
		}
		
		// Colocado por R�mulo Aur�lio em 23/03/2009 - Analista:Rosana Carvalho
		if (situacaoEspecialCobrancaActionForm.getDataFimSituacao() != null &&
			!situacaoEspecialCobrancaActionForm.getDataFimSituacao().equals("")){
						
			situacaoEspecialCobrancaHelper.setDataFimSituacao(
					situacaoEspecialCobrancaActionForm.getDataFimSituacao());
		}
		
		Integer quantidadeImoveisSem = 0;
		
		
		if (situacaoEspecialCobrancaActionForm.getQuantidadeImoveisSEMSituacaoEspecialCobranca()!=null 
				&& !situacaoEspecialCobrancaActionForm.getQuantidadeImoveisSEMSituacaoEspecialCobranca().equals("")){
			
			quantidadeImoveisSem =new Integer(situacaoEspecialCobrancaActionForm.getQuantidadeImoveisSEMSituacaoEspecialCobranca());
			
			situacaoEspecialCobrancaHelper.setQuantidadeImoveisAtualizados(quantidadeImoveisSem.toString());
		}
		
		return situacaoEspecialCobrancaHelper;
	}
}
