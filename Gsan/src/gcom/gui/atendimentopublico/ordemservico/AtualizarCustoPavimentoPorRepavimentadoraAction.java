/**
 * 
 */
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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroUnidadeRepavimentadoraCustoPavimentoCalcada;
import gcom.atendimentopublico.ordemservico.FiltroUnidadeRepavimentadoraCustoPavimentoRua;
import gcom.cadastro.unidade.UnidadeRepavimentadoraCustoPavimentoCalcada;
import gcom.cadastro.unidade.UnidadeRepavimentadoraCustoPavimentoRua;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descri��o da classe
 * 
 * @author Hugo Leonardo
 * @date 28/12/2010
 */
public class AtualizarCustoPavimentoPorRepavimentadoraAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();
		
		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		
		AtualizarCustoPavimentoPorRepavimentadoraActionForm form = (AtualizarCustoPavimentoPorRepavimentadoraActionForm) actionForm;

		if (httpServletRequest.getParameter("confirmado") != null
				&& httpServletRequest.getParameter("confirmado").equalsIgnoreCase("ok")) {
			
			sessao.setAttribute("confirmou", "sim");
		}
		
		// Atualiza Custo Pavimento Rua
		if((httpServletRequest.getParameter("acao") != null && 
	        	httpServletRequest.getParameter("acao").equals("atualizarRua")) ||
	        	sessao.getAttribute("acao") != null && sessao.getAttribute("acao").equals("atualizarRua")){
			
			sessao.setAttribute("acao", "atualizarRua");
			
			Date timeStampDB = (Date) sessao.getAttribute("timeStamp");

			UnidadeRepavimentadoraCustoPavimentoRua unidadeRepavimentadoraCustoPavimentoRua = 
				(UnidadeRepavimentadoraCustoPavimentoRua) sessao.getAttribute("unidadeRepavimentadoraCustoPavimentoRua");

			String descricaoPavimento = unidadeRepavimentadoraCustoPavimentoRua.getPavimentoRua().getDescricao();
			
			String valorPavimentoRua = form.getValorPavimentoRua();
			
			unidadeRepavimentadoraCustoPavimentoRua.setValorPavimento(
					Util.formatarMoedaRealparaBigDecimal(valorPavimentoRua));

			// Verificar Atualiza��o realizada por outro usu�rio
			String idUnidadeRepavimentadoraCustoPavimentoRua = unidadeRepavimentadoraCustoPavimentoRua.getId().toString();
			
			FiltroUnidadeRepavimentadoraCustoPavimentoRua filtroUnidadeRepavimentadoraCustoPavimentoRua = 
				new FiltroUnidadeRepavimentadoraCustoPavimentoRua();
			
			filtroUnidadeRepavimentadoraCustoPavimentoRua.adicionarParametro(
					new ParametroSimples(FiltroUnidadeRepavimentadoraCustoPavimentoRua.ID, 
							idUnidadeRepavimentadoraCustoPavimentoRua));
			
			//  Recupera Debito Tipo Servi�o
			Collection colecaoUnidadeRepavimentadoraCustoPavimentoRua = 
				fachada.pesquisar(filtroUnidadeRepavimentadoraCustoPavimentoRua, 
						UnidadeRepavimentadoraCustoPavimentoRua.class.getName());
			
			UnidadeRepavimentadoraCustoPavimentoRua unidadeRepavimentadoraCustoPavimentoRuaBD = 
				(UnidadeRepavimentadoraCustoPavimentoRua) colecaoUnidadeRepavimentadoraCustoPavimentoRua.iterator().next();
			
			Date dtInicial = Util.converteStringParaDate(form.getDataVigenciaInicialPavimentoRua());
			
			Date dtFinal = Util.converteStringParaDate(form.getDataVigenciaFinalPavimentoRua());
			
			// [FS0006] Verificar exist�ncia de custo do pavimento de rua no per�odo informado
			fachada.verificaAtualizarCustoPavimentoPorRepavimentadora(unidadeRepavimentadoraCustoPavimentoRuaBD.getId(),
					unidadeRepavimentadoraCustoPavimentoRuaBD.getUnidadeRepavimentadora().getId(),
					unidadeRepavimentadoraCustoPavimentoRuaBD.getPavimentoRua().getId(), 
					dtInicial, dtFinal, new Integer("1"), new Integer("1"));
			
			fachada.verificaAtualizarCustoPavimentoPorRepavimentadora(unidadeRepavimentadoraCustoPavimentoRuaBD.getId(),
					unidadeRepavimentadoraCustoPavimentoRuaBD.getUnidadeRepavimentadora().getId(),
					unidadeRepavimentadoraCustoPavimentoRuaBD.getPavimentoRua().getId(), 
					dtInicial, dtFinal, new Integer("1"), new Integer("2"));
			
			Integer validar = fachada.verificarExistenciDiasSemValorCustoPavimentoPorRepavimentadora(
					unidadeRepavimentadoraCustoPavimentoRuaBD.getId(),
					unidadeRepavimentadoraCustoPavimentoRuaBD.getUnidadeRepavimentadora().getId(),
					unidadeRepavimentadoraCustoPavimentoRuaBD.getPavimentoRua().getId(), 
					dtInicial, dtFinal, new Integer("1"), new Integer("1"));
			
			Integer validar2 = fachada.verificarExistenciDiasSemValorCustoPavimentoPorRepavimentadora(
					unidadeRepavimentadoraCustoPavimentoRuaBD.getId(),
					unidadeRepavimentadoraCustoPavimentoRuaBD.getUnidadeRepavimentadora().getId(),
					unidadeRepavimentadoraCustoPavimentoRuaBD.getPavimentoRua().getId(), 
					dtInicial, dtFinal, new Integer("1"), new Integer("2"));
			
			if((validar != 0 || validar2 != 0) && sessao.getAttribute("confirmou") == null){
				
				httpServletRequest.setAttribute("caminhoActionConclusao", "atualizarCustoPavimentoPorRepavimentadoraAction.do");
				
				return montarPaginaConfirmacao("atencao.existe.periodo.sem_cadastro.custo_pavimento_por_repavimentadora",
						httpServletRequest, actionMapping, "acao=atualizarRua");
			}
			
			sessao.removeAttribute("acao");
			sessao.removeAttribute("confirmou");
				
			if (form.getDataVigenciaInicialPavimentoRua() != null && !form.getDataVigenciaInicialPavimentoRua().equals("")){
				
				if (!Util.validarDiaMesAno(form.getDataVigenciaInicialPavimentoRua())) {
					
					unidadeRepavimentadoraCustoPavimentoRua.setDataVigenciaInicial(
							Util.converteStringParaDate(form.getDataVigenciaInicialPavimentoRua()));
					
					if (form.getDataVigenciaFinalPavimentoRua() != null && !form.getDataVigenciaFinalPavimentoRua().equals("")){
						
						if (!Util.validarDiaMesAno(form.getDataVigenciaFinalPavimentoRua())) {
							
							unidadeRepavimentadoraCustoPavimentoRua.setDataVigenciaFinal(
									Util.converteStringParaDate(form.getDataVigenciaFinalPavimentoRua()));
							
							if(Util.compararData(unidadeRepavimentadoraCustoPavimentoRua.getDataVigenciaInicial(), 
									unidadeRepavimentadoraCustoPavimentoRua.getDataVigenciaFinal()) == 1){
								
								throw new ActionServletException("atencao.atencao.data_vigencia_final_menor");
							}
						}else{
							throw new ActionServletException("atencao.atencao.data_vigencia_final_invalida");
						}
					}else if(form.getDataVigenciaFinalPavimentoRua().equals("")){
						
						unidadeRepavimentadoraCustoPavimentoRua.setDataVigenciaFinal(null);
					}
				}else{
					throw new ActionServletException("atencao.data_vigencia_inicial_invalida");
				}
			}
			
			if(timeStampDB.compareTo(unidadeRepavimentadoraCustoPavimentoRua.getUltimaAlteracao()) != 0){
				throw new ActionServletException("atencao.atualizacao.timestamp");
			}
			
			unidadeRepavimentadoraCustoPavimentoRua.setUltimaAlteracao(new Date());
			
			// ------------ REGISTRAR TRANSA��O ----------------
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_ATUALIZAR_CUSTO_PAVIMENTO, unidadeRepavimentadoraCustoPavimentoRua.getId(),
					unidadeRepavimentadoraCustoPavimentoRua.getId(), new UsuarioAcaoUsuarioHelper(usuarioLogado,
					UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
			// ------------ REGISTRAR TRANSA��O ----------------
			registradorOperacao.registrarOperacao(unidadeRepavimentadoraCustoPavimentoRua);
			
			fachada.atualizar(unidadeRepavimentadoraCustoPavimentoRua);

			montarPaginaSucesso(httpServletRequest, "Custo do Pavimento de Rua "
					+ descricaoPavimento + " atualizado com sucesso.",
					"Realizar outra Manuten��o de Custo do Pavimento de Rua",
					"exibirFiltrarCustoPavimentoPorRepavimentadoraAction.do?menu=sim");
			
		}else{
			
			sessao.setAttribute("acao", "atualizarCalcada");
			
			Date timeStampDB = (Date) sessao.getAttribute("timeStamp");

			UnidadeRepavimentadoraCustoPavimentoCalcada unidadeRepavimentadoraCustoPavimentoCalcada = 
				(UnidadeRepavimentadoraCustoPavimentoCalcada) sessao.getAttribute("unidadeRepavimentadoraCustoPavimentoCalcada");

			String descricaoPavimento = unidadeRepavimentadoraCustoPavimentoCalcada.getPavimentoCalcada().getDescricao();
			
			String valorPavimentoCalcada = form.getValorPavimentoCalcada();
			
			unidadeRepavimentadoraCustoPavimentoCalcada.setValorPavimento(
					Util.formatarMoedaRealparaBigDecimal(valorPavimentoCalcada));

			// Verificar Atualiza��o realizada por outro usu�rio
			String idUnidadeRepavimentadoraCustoPavimentoCalcada = unidadeRepavimentadoraCustoPavimentoCalcada.getId().toString();
			
			FiltroUnidadeRepavimentadoraCustoPavimentoCalcada filtroUnidadeRepavimentadoraCustoPavimentoCalcada = 
				new FiltroUnidadeRepavimentadoraCustoPavimentoCalcada();
			
			filtroUnidadeRepavimentadoraCustoPavimentoCalcada.adicionarParametro(
					new ParametroSimples(FiltroUnidadeRepavimentadoraCustoPavimentoCalcada.ID, 
							idUnidadeRepavimentadoraCustoPavimentoCalcada));
			
			//  Recupera Debito Tipo Servi�o
			Collection colecaoUnidadeRepavimentadoraCustoPavimentoCalcada = 
				fachada.pesquisar(filtroUnidadeRepavimentadoraCustoPavimentoCalcada, 
						UnidadeRepavimentadoraCustoPavimentoCalcada.class.getName());
			
			UnidadeRepavimentadoraCustoPavimentoCalcada unidadeRepavimentadoraCustoPavimentoCalcadaBD = 
				(UnidadeRepavimentadoraCustoPavimentoCalcada) colecaoUnidadeRepavimentadoraCustoPavimentoCalcada.iterator().next();
			
			Date dtInicial = Util.converteStringParaDate(form.getDataVigenciaInicialPavimentoCalcada());
			
			Date dtFinal = Util.converteStringParaDate(form.getDataVigenciaFinalPavimentoCalcada());
			
			// [FS0007] Verificar exist�ncia de custo do pavimento de Cal�ada no per�odo informado
			fachada.verificaAtualizarCustoPavimentoPorRepavimentadora(
					unidadeRepavimentadoraCustoPavimentoCalcadaBD.getId(),
					unidadeRepavimentadoraCustoPavimentoCalcadaBD.getUnidadeRepavimentadora().getId(),
					unidadeRepavimentadoraCustoPavimentoCalcadaBD.getPavimentoCalcada().getId(), 
					dtInicial, dtFinal, new Integer("2"), new Integer("1"));
			
			fachada.verificaAtualizarCustoPavimentoPorRepavimentadora(
					unidadeRepavimentadoraCustoPavimentoCalcadaBD.getId(),
					unidadeRepavimentadoraCustoPavimentoCalcadaBD.getUnidadeRepavimentadora().getId(),
					unidadeRepavimentadoraCustoPavimentoCalcadaBD.getPavimentoCalcada().getId(), 
					dtInicial, dtFinal, new Integer("2"), new Integer("2"));
			
			Integer validar = fachada.verificarExistenciDiasSemValorCustoPavimentoPorRepavimentadora(
					unidadeRepavimentadoraCustoPavimentoCalcadaBD.getId(),
					unidadeRepavimentadoraCustoPavimentoCalcadaBD.getUnidadeRepavimentadora().getId(),
					unidadeRepavimentadoraCustoPavimentoCalcadaBD.getPavimentoCalcada().getId(), 
					dtInicial, dtFinal, new Integer("2"), new Integer("1"));
			
			Integer validar2 = fachada.verificarExistenciDiasSemValorCustoPavimentoPorRepavimentadora(
					unidadeRepavimentadoraCustoPavimentoCalcadaBD.getId(),
					unidadeRepavimentadoraCustoPavimentoCalcadaBD.getUnidadeRepavimentadora().getId(),
					unidadeRepavimentadoraCustoPavimentoCalcadaBD.getPavimentoCalcada().getId(), 
					dtInicial, dtFinal, new Integer("2"), new Integer("2"));
			
			if((validar != 0 || validar2 != 0) && sessao.getAttribute("confirmou") == null){
				
				httpServletRequest.setAttribute("caminhoActionConclusao", "atualizarCustoPavimentoPorRepavimentadoraAction.do");
				
				return montarPaginaConfirmacao("atencao.existe.periodo.sem_cadastro.custo_pavimento_por_repavimentadora",
						httpServletRequest, actionMapping, "acao=atualizarCalcada");
			}
			
			sessao.removeAttribute("acao");
			sessao.removeAttribute("confirmou");
				
			if (form.getDataVigenciaInicialPavimentoCalcada() != null 
					&& !form.getDataVigenciaInicialPavimentoCalcada().equals("")){
				
				if (!Util.validarDiaMesAno(form.getDataVigenciaInicialPavimentoCalcada())) {
					
					unidadeRepavimentadoraCustoPavimentoCalcada.setDataVigenciaInicial(
							Util.converteStringParaDate(form.getDataVigenciaInicialPavimentoCalcada()));
					
					if (form.getDataVigenciaFinalPavimentoCalcada() != null && 
							!form.getDataVigenciaFinalPavimentoCalcada().equals("")){
						
						if (!Util.validarDiaMesAno(form.getDataVigenciaFinalPavimentoCalcada())) {
							
							unidadeRepavimentadoraCustoPavimentoCalcada.setDataVigenciaFinal(
									Util.converteStringParaDate(form.getDataVigenciaFinalPavimentoCalcada()));
							
							if(Util.compararData(unidadeRepavimentadoraCustoPavimentoCalcada.getDataVigenciaInicial(), 
									unidadeRepavimentadoraCustoPavimentoCalcada.getDataVigenciaFinal()) == 1){
								
								throw new ActionServletException("atencao.atencao.data_vigencia_final_menor");
							}
						}else{
							throw new ActionServletException("atencao.atencao.data_vigencia_final_invalida");
						}
					}
				}else{
					throw new ActionServletException("atencao.data_vigencia_inicial_invalida");
				}
			}
			
			if(timeStampDB.compareTo(unidadeRepavimentadoraCustoPavimentoCalcada.getUltimaAlteracao()) != 0){
				throw new ActionServletException("atencao.atualizacao.timestamp");
			}
			
			unidadeRepavimentadoraCustoPavimentoCalcada.setUltimaAlteracao(new Date());
			
			fachada.atualizar(unidadeRepavimentadoraCustoPavimentoCalcada);

			montarPaginaSucesso(httpServletRequest, "Custo do Pavimento de Cal�ada "
					+ descricaoPavimento + " atualizado com sucesso.",
					"Realizar outra Manuten��o de Custo do Pavimento de Cal�ada",
					"exibirFiltrarCustoPavimentoPorRepavimentadoraAction.do?menu=sim");
			
		}

		return retorno;
	}
}
