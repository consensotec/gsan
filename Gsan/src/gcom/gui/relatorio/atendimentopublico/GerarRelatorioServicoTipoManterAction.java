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
package gcom.gui.relatorio.atendimentopublico;

import gcom.atendimentopublico.ordemservico.FiltroPerfilServico;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipoPrioridade;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipoReferencia;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipoSubgrupo;
import gcom.atendimentopublico.ordemservico.FiltroTipoServico;
import gcom.atendimentopublico.ordemservico.ServicoPerfilTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade;
import gcom.atendimentopublico.ordemservico.ServicoTipoReferencia;
import gcom.atendimentopublico.ordemservico.ServicoTipoSubgrupo;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoTipo;
import gcom.faturamento.credito.FiltroCreditoTipo;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.atendimentopublico.ordemservico.FiltrarTipoServicoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.atendimentopublico.RelatorioManterServicoTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
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
 * @author Vinicius Medeiros
 * @version 1.0
 */

public class GerarRelatorioServicoTipoManterAction extends
		ExibidorProcessamentoTarefaRelatorio {

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

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		FiltrarTipoServicoActionForm filtrarTipoServicoActionForm = (FiltrarTipoServicoActionForm) actionForm;

		FiltroTipoServico filtroTipoServico = (FiltroTipoServico) sessao
				.getAttribute("filtroTipoServico");

		// Inicio da parte que vai mandar os parametros para o relat�rio
		ServicoTipo servicoTipoParametros = new ServicoTipo();

		// Id
		String idTipoServico = null;

		String idTipoServicoPesquisar = (String) filtrarTipoServicoActionForm
				.getIdTipoServico();

		if (idTipoServicoPesquisar != null && !idTipoServicoPesquisar.equals("")) {
			idTipoServico = idTipoServicoPesquisar;
		}
		
		// Descricao
		String descricao = null;
		
		if(filtrarTipoServicoActionForm.getDescricaoTipoServico() != null
				&& !filtrarTipoServicoActionForm.getDescricaoTipoServico().equals("")){

			descricao = filtrarTipoServicoActionForm.getDescricaoTipoServico();
		}
		
		//Descricao abreviada
		String abreviacao = null;
		
		if(filtrarTipoServicoActionForm.getAbreviada() != null
				&& !filtrarTipoServicoActionForm.getAbreviada().equals("")){
			
			abreviacao = filtrarTipoServicoActionForm.getAbreviada();
		}
		
		// Codigo Tipo de Servico 
		String codigoServicoTipo = null;
		
		if(filtrarTipoServicoActionForm.getCodigoServico() != null 
				&& !filtrarTipoServicoActionForm.getCodigoServico().equals("")){
			
			codigoServicoTipo = filtrarTipoServicoActionForm.getCodigoServico();

		}
		
		// Subgrupo
		if (filtrarTipoServicoActionForm.getSubgrupo() != null 
				&& !filtrarTipoServicoActionForm.getSubgrupo().equals("")) {
			
			FiltroServicoTipoSubgrupo filtroServicoTipoSubgrupo = new FiltroServicoTipoSubgrupo();
			filtroServicoTipoSubgrupo.adicionarParametro(new ParametroSimples(FiltroServicoTipoSubgrupo.ID, filtrarTipoServicoActionForm.getSubgrupo()));
			
			Collection colecaoServicoTipoSubgrupo = fachada.pesquisar(filtroServicoTipoSubgrupo, ServicoTipoSubgrupo.class.getName());
			
			if (colecaoServicoTipoSubgrupo != null && !colecaoServicoTipoSubgrupo.isEmpty()) {
				
				ServicoTipoSubgrupo servicoTipoSubgrupo = (ServicoTipoSubgrupo) Util.retonarObjetoDeColecao(colecaoServicoTipoSubgrupo);
				servicoTipoParametros.setServicoTipoSubgrupo(servicoTipoSubgrupo);
			
			}
			
		}
		
		// Valor do Servico
		String valorInicial = null;
		String valorFinal = null;
		
		if (filtrarTipoServicoActionForm.getValorInicial() != null 
			&& !filtrarTipoServicoActionForm.getValorInicial().equals("")
		    && filtrarTipoServicoActionForm.getValorFinal() != null 
			&& !filtrarTipoServicoActionForm.getValorFinal().equals("")){
				
				valorInicial = filtrarTipoServicoActionForm.getValorInicial();
				valorFinal = filtrarTipoServicoActionForm.getValorFinal();

		}
		
		// Indicador Pavimento Rua
		Short indicadorPavimentoRua = null;
		
		if(filtrarTipoServicoActionForm.getIndicadorPavimentoRua() != null
				&& !filtrarTipoServicoActionForm.getIndicadorPavimentoRua().equals("")){
			
			indicadorPavimentoRua = new Short("" 
					+ filtrarTipoServicoActionForm.getIndicadorPavimentoRua());
			
		}
		
		// Indicador Pavimento Calcada
		Short indicadorPavimentoCalcada = null;
		
		if(filtrarTipoServicoActionForm.getIndicadorPavimentoCalcada() != null
				&& !filtrarTipoServicoActionForm.getIndicadorPavimentoCalcada().equals("")){
			
			indicadorPavimentoCalcada = new Short("" 
					+ filtrarTipoServicoActionForm.getIndicadorPavimentoCalcada());
			
		}
		
		// Indicador Atualiza Comercial
		Short indicadorAtualizaComercial = null;

		if (filtrarTipoServicoActionForm.getAtualizacaoComercial() != null
				&& !filtrarTipoServicoActionForm.getAtualizacaoComercial().equals("")) {

			indicadorAtualizaComercial = new Short(""
					+ filtrarTipoServicoActionForm.getAtualizacaoComercial());
		}
		
		// Indicador Servico Terceirizado
		Short indicadorServicoTerceirizado = null;

		if (filtrarTipoServicoActionForm.getServicoTerceirizado() != null
				&& !filtrarTipoServicoActionForm.getServicoTerceirizado().equals("")) {

			indicadorServicoTerceirizado = new Short(""  
					+ filtrarTipoServicoActionForm.getServicoTerceirizado());
		}		
		
		// Indicador Fiscalizacao Infracao
		Short indicativoFiscalizacaoInfracao = null;

		if (filtrarTipoServicoActionForm.getIndicadorFiscalizacaoInfracao() != null
				&& !filtrarTipoServicoActionForm.getIndicadorFiscalizacaoInfracao().equals("")) {

			indicativoFiscalizacaoInfracao = new Short(""  
					+ filtrarTipoServicoActionForm.getIndicadorFiscalizacaoInfracao());
		}	

		// Indicativo Vistoria
		Short indicativoVistoria = null;

		if (filtrarTipoServicoActionForm.getIndicadorVistoria() != null
				&& !filtrarTipoServicoActionForm.getIndicadorVistoria().equals("")) {

			indicativoVistoria = new Short(""  
					+ filtrarTipoServicoActionForm.getIndicadorVistoria());
		}
		
		// Tempo Medio
		String tempoMedioInicial = null;
		String tempoMedioFinal = null;
		
		if (filtrarTipoServicoActionForm.getTempoMedioIncial() != null 
			&& !filtrarTipoServicoActionForm.getTempoMedioIncial().equals("")
		    && filtrarTipoServicoActionForm.getTempoMedioFinal() != null 
			&& !filtrarTipoServicoActionForm.getTempoMedioFinal().equals("")){
				
				tempoMedioInicial = filtrarTipoServicoActionForm.getTempoMedioIncial();
				tempoMedioFinal = filtrarTipoServicoActionForm.getTempoMedioFinal();

		}
		
		//Tipo Debito
		if (filtrarTipoServicoActionForm.getDescricaoTipoDebito() != null 
				&& !filtrarTipoServicoActionForm.getDescricaoTipoDebito().equals("")) {
			
			FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
			filtroDebitoTipo.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.ID, filtrarTipoServicoActionForm.getIdTipoDebito()));
			
			Collection colecaoDebitoTipo = fachada.pesquisar(filtroDebitoTipo, DebitoTipo.class.getName());
			
			if (colecaoDebitoTipo != null && !colecaoDebitoTipo.isEmpty()) {
				
				DebitoTipo debitoTipo = (DebitoTipo) Util.retonarObjetoDeColecao(colecaoDebitoTipo);
				servicoTipoParametros.setDebitoTipo(debitoTipo);
			
			}
		
		}
			
		//Tipo Credito
		if (filtrarTipoServicoActionForm.getIdTipoCredito() != null 
					&& !filtrarTipoServicoActionForm.getIdTipoCredito().equals("")) {
				
				FiltroCreditoTipo filtroCreditoTipo = new FiltroCreditoTipo();
				filtroCreditoTipo.adicionarParametro(new ParametroSimples(FiltroCreditoTipo.ID, filtrarTipoServicoActionForm.getIdTipoCredito()));
				
				Collection colecaoCreditoTipo = fachada.pesquisar(filtroCreditoTipo, CreditoTipo.class.getName());
				
				if (colecaoCreditoTipo != null && !colecaoCreditoTipo.isEmpty()) {
					
					CreditoTipo creditoTipo = (CreditoTipo) Util.retonarObjetoDeColecao(colecaoCreditoTipo);
					servicoTipoParametros.setCreditoTipo(creditoTipo);
				
				}		
			
		}
		
		//Prioridade Servico
		if (filtrarTipoServicoActionForm.getIdPrioridadeServico() != null 
					&& !filtrarTipoServicoActionForm.getIdPrioridadeServico().equals("")) {
				
				FiltroServicoTipoPrioridade filtroServicoTipoPrioridade = new FiltroServicoTipoPrioridade();
				filtroServicoTipoPrioridade.adicionarParametro(new ParametroSimples(FiltroServicoTipoPrioridade.ID, filtrarTipoServicoActionForm.getIdPrioridadeServico()));
				
				Collection colecaoPrioridadeServico = fachada.pesquisar(filtroServicoTipoPrioridade, ServicoTipoPrioridade.class.getName());
				
				if (colecaoPrioridadeServico != null && !colecaoPrioridadeServico.isEmpty()) {
					
					ServicoTipoPrioridade servicoTipoPrioridade = (ServicoTipoPrioridade) Util.retonarObjetoDeColecao(colecaoPrioridadeServico);
					servicoTipoParametros.setServicoTipoPrioridade(servicoTipoPrioridade);
				
				}		
			
		}
		
		//Perfil Tipo Servico
		if (filtrarTipoServicoActionForm.getPerfilServico() != null 
					&& !filtrarTipoServicoActionForm.getPerfilServico().equals("")) {
				
				FiltroPerfilServico filtroPerfilServico = new FiltroPerfilServico();
				filtroPerfilServico.adicionarParametro(new ParametroSimples(FiltroPerfilServico.ID, filtrarTipoServicoActionForm.getPerfilServico()));
				
				Collection colecaoPerfilServico = fachada.pesquisar(
						filtroPerfilServico, ServicoPerfilTipo.class.getName());
				
				if (colecaoPerfilServico != null && !colecaoPerfilServico.isEmpty()) {
					
					ServicoPerfilTipo servicoPerfilTipo = (ServicoPerfilTipo) Util.retonarObjetoDeColecao(colecaoPerfilServico);
					servicoTipoParametros.setServicoPerfilTipo(servicoPerfilTipo);
				
				}		
			
		}
		
		//Tipo Servico de Referencia
		if (filtrarTipoServicoActionForm.getDescricaoTipoServicoReferencia() != null 
					&& !filtrarTipoServicoActionForm.getDescricaoTipoServicoReferencia().equals("")) {
				
				FiltroServicoTipoReferencia filtroServicoTipoReferencia = new FiltroServicoTipoReferencia();
				filtroServicoTipoReferencia.adicionarParametro(new ParametroSimples(FiltroServicoTipoReferencia.ID, filtrarTipoServicoActionForm.getIdTipoServicoReferencia()));
				
				Collection colecaoTipoServicoReferencia = fachada.pesquisar(
						filtroServicoTipoReferencia, ServicoTipoReferencia.class.getName());
				
				if (colecaoTipoServicoReferencia != null && !colecaoTipoServicoReferencia.isEmpty()) {
					
					ServicoTipoReferencia servicoTipoReferencia = (ServicoTipoReferencia) Util.retonarObjetoDeColecao(colecaoTipoServicoReferencia);
					servicoTipoParametros.setServicoTipoReferencia(servicoTipoReferencia);
				
				}		
			
		}
		
		// Indicador Atividade Unica
		Short indicadorAtividadeUnica = null;

		if (filtrarTipoServicoActionForm.getIndicadorAtividadeUnica() != null
				&& !filtrarTipoServicoActionForm.getIndicadorAtividadeUnica().equals("")) {

			indicadorAtividadeUnica = new Short(""  
					+ filtrarTipoServicoActionForm.getIndicadorAtividadeUnica());
		}
		
		// Indicador Uso
		Short indicadorUso = null;

		if (filtrarTipoServicoActionForm.getIndicadorUso() != null
				&& !filtrarTipoServicoActionForm.getIndicadorUso().equals("")) {

			indicadorUso = new Short(""  
					+ filtrarTipoServicoActionForm.getIndicadorUso());
		}
		
		// seta os parametros que ser�o mostrados no relat�rio
		servicoTipoParametros.setId(idTipoServico == null ? null : new Integer(
				idTipoServico));
		servicoTipoParametros.setDescricao(descricao);
		servicoTipoParametros.setDescricaoAbreviada(abreviacao);
		servicoTipoParametros.setCodigoServicoTipo(codigoServicoTipo);
		servicoTipoParametros.setIndicadorPavimentoRua(indicadorPavimentoRua);
		servicoTipoParametros.setIndicadorPavimentoCalcada(indicadorPavimentoCalcada);
		servicoTipoParametros.setIndicadorAtualizaComercial(indicadorAtualizaComercial.shortValue());
		
		if(indicadorServicoTerceirizado != null){
			servicoTipoParametros.setIndicadorTerceirizado(indicadorServicoTerceirizado);
		}
		
		if(indicativoFiscalizacaoInfracao != null){
			servicoTipoParametros.setIndicadorFiscalizacaoInfracao(indicativoFiscalizacaoInfracao);
		}
		
		if(indicativoVistoria != null){
			servicoTipoParametros.setIndicadorVistoria(indicativoVistoria);
		}
		
		if(indicadorUso != null){
			servicoTipoParametros.setIndicadorUso(indicadorUso);
		}
	
		// Fim da parte que vai mandar os parametros para o relat�rio

		// cria uma inst�ncia da classe do relat�rio
		RelatorioManterServicoTipo relatorioManterServicoTipo = new RelatorioManterServicoTipo(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterServicoTipo.addParametro("filtroTipoServico",
				filtroTipoServico);

		relatorioManterServicoTipo.addParametro("valorInicial",
				valorInicial);

		relatorioManterServicoTipo.addParametro("valorFinal",
				valorFinal);
		
		relatorioManterServicoTipo.addParametro("tempoMedioInicial",
				tempoMedioInicial);

		relatorioManterServicoTipo.addParametro("tempoMedioFinal",
				tempoMedioFinal);
		
		relatorioManterServicoTipo.addParametro("indicadorAtividadeUnica",
				indicadorAtividadeUnica);

		
		relatorioManterServicoTipo.addParametro("servicoTipoParametros",
				servicoTipoParametros);

		// chama o met�do de gerar relat�rio passando o c�digo da analise
		// como par�metro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterServicoTipo.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioManterServicoTipo,
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