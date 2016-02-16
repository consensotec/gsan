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

import gcom.atendimentopublico.ordemservico.FiltroServicoTipoAtividade;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipoBoletim;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipoMaterial;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipoMotivoEncerramento;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipoPrioridade;
import gcom.atendimentopublico.ordemservico.FiltroTipoServico;
import gcom.atendimentopublico.ordemservico.ServicoPerfilTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoAtividade;
import gcom.atendimentopublico.ordemservico.ServicoTipoBoletim;
import gcom.atendimentopublico.ordemservico.ServicoTipoMaterial;
import gcom.atendimentopublico.ordemservico.ServicoTipoMotivoEncerramento;
import gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade;
import gcom.atendimentopublico.ordemservico.ServicoTipoReferencia;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoTipo;
import gcom.faturamento.credito.FiltroCreditoTipo;
import gcom.faturamento.debito.DebitoTipo;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.FachadaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.util.Iterator;

/**
 * [SB0001]Atualizar Tipo Perfil de Servi�o
 *
 * @author Thiago Tenorio, Pedro Alexandre
 * @date 31/10/2006, 14/12/2007
 */

public class ExibirAtualizarTipoServicoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("atualizarTipoServico");

		AtualizarTipoServicoActionForm atualizarTipoServicoActionForm = (AtualizarTipoServicoActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		ServicoTipo servicoTipo = null;
		
		String idServico = null;
		
		if (httpServletRequest.getParameter("idRegistroAtualizacao") != null && !httpServletRequest.getParameter("idRegistroAtualizacao").equals("")) {
			//tela do manter
			idServico = (String) httpServletRequest.getParameter("idRegistroAtualizacao");
			sessao.setAttribute("idServico", idServico);
		} else if (sessao.getAttribute("servicoTipo") != null) {
			//tela do filtrar
			servicoTipo = (ServicoTipo) sessao.getAttribute("servicoTipo"); 
			idServico = servicoTipo.getId().toString();
		}else if(httpServletRequest.getParameter("idRegistroInseridoAtualizar") != null){
			//link na tela de sucesso do inserir Perfil Servi�o
			idServico = (String)httpServletRequest.getParameter("idRegistroInseridoAtualizar");
			sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarTipoPerfilServicoAction.do?menu=sim");
		}
		
		if (idServico == null){
			
			if (httpServletRequest.getParameter("idRegistroAtualizacao") == null){
				idServico = (String) sessao.getAttribute("idServico");
			}else{
				idServico = (String) httpServletRequest.getParameter("idRegistroAtualizacao").toString();
			}
		}
		
		boolean pesquisar = false;
		
		if(httpServletRequest.getParameter("pesquisa") != null &&
				httpServletRequest.getParameter("pesquisa").equals("S")){
			pesquisar = true;
		}else if(sessao.getAttribute("pesquisa") != null){
			pesquisar = true;
			sessao.removeAttribute("pesquisa");
		}
		
		//-------Parte que trata do c�digo quando o usu�rio tecla enter     
		if ((idServico != null && !idServico.equals(""))
				&& pesquisar) {

			FiltroTipoServico filtroTipoServico = new FiltroTipoServico();

			filtroTipoServico
					.adicionarParametro(new ParametroSimples(
							FiltroTipoServico.ID, idServico));
			
			filtroTipoServico.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
			filtroTipoServico.adicionarCaminhoParaCarregamentoEntidade("servicoTipoPrioridade");
			filtroTipoServico.adicionarCaminhoParaCarregamentoEntidade("servicoTipoSubgrupo");
			filtroTipoServico.adicionarCaminhoParaCarregamentoEntidade("servicoPerfilTipo");
			filtroTipoServico.adicionarCaminhoParaCarregamentoEntidade("servicoTipoReferencia");
			filtroTipoServico.adicionarCaminhoParaCarregamentoEntidade("creditoTipo");

			Collection colecaoServicoTipo = fachada.pesquisar(
					filtroTipoServico, ServicoTipo.class
							.getName());

			if (colecaoServicoTipo != null
					&& !colecaoServicoTipo.isEmpty()) {
				
				servicoTipo = (ServicoTipo) colecaoServicoTipo.iterator().next();
				atualizarTipoServicoActionForm.setDescricao(servicoTipo.getDescricao());
				sessao.setAttribute("servicoTipo", servicoTipo);
				
				atualizarTipoServicoActionForm.setIndicadorInformacoesBoletimMedicao(servicoTipo.getIndicadorBoletim().toString());
				
				if(servicoTipo.getIndicadorBoletim().equals(ConstantesSistema.SIM)){
					
					FiltroServicoTipoBoletim filtroServicoTipoBoletim = new FiltroServicoTipoBoletim();
					filtroServicoTipoBoletim.adicionarParametro(new ParametroSimples(
							FiltroServicoTipoBoletim.ID, idServico));
					Collection colecaoServicoTipoBoletim = fachada.pesquisar(
							filtroServicoTipoBoletim, ServicoTipoBoletim.class
									.getName());
					
					if (colecaoServicoTipoBoletim != null
							&& !colecaoServicoTipoBoletim.isEmpty()) {
						
						ServicoTipoBoletim	servicoTipoBoletim = (ServicoTipoBoletim) colecaoServicoTipoBoletim.iterator().next();
						
						atualizarTipoServicoActionForm.setIndicativoPavimento(servicoTipoBoletim.getIndicadorPavimento().toString());
						atualizarTipoServicoActionForm.setIndicativoReposicaoAsfalto(servicoTipoBoletim.getIndicadorReposicaoAsfalto().toString());
						atualizarTipoServicoActionForm.setIndicativoReposicaoParalelo(servicoTipoBoletim.getIndicadorReposicaoParalelo().toString());
						atualizarTipoServicoActionForm.setIndicativoReposicaoCalcada(servicoTipoBoletim.getIndicadorReposicaoCalcada().toString());
					}
				}else{
					atualizarTipoServicoActionForm.setIndicativoPavimento("");
					atualizarTipoServicoActionForm.setIndicativoReposicaoAsfalto("");
					atualizarTipoServicoActionForm.setIndicativoReposicaoParalelo("");
					atualizarTipoServicoActionForm.setIndicativoReposicaoCalcada("");
				}
				
				
			} 
			
			FiltroCreditoTipo filtroCreditoTipo = new FiltroCreditoTipo();
			Collection colecaoCreditoTipo = fachada.pesquisar(filtroCreditoTipo, CreditoTipo.class.getName());
			sessao.setAttribute("colecaoCreditoTipo", colecaoCreditoTipo);
			
			FiltroServicoTipoPrioridade filtroServicoTipoPrioridade = new FiltroServicoTipoPrioridade();
			Collection colecaoPrioridadeServico = fachada.pesquisar(filtroServicoTipoPrioridade, ServicoTipoPrioridade.class.getName());
			sessao.setAttribute("colecaoPrioridadeServico", colecaoPrioridadeServico);
			
//		  ------  O servico Tipo foi encontrado
			
			atualizarTipoServicoActionForm.setIdTipoServico(""+servicoTipo.getId());
			atualizarTipoServicoActionForm.setDescricao(""+servicoTipo.getDescricao());
			if (servicoTipo.getDescricaoAbreviada() != null) {
				atualizarTipoServicoActionForm.setAbreviada(""+servicoTipo.getDescricaoAbreviada());
			} else {
				atualizarTipoServicoActionForm.setAbreviada("");
			}
			atualizarTipoServicoActionForm.setSubgrupo(""+servicoTipo.getServicoTipoSubgrupo().getId());
			atualizarTipoServicoActionForm.setValorServico(Util.formatarMoedaReal(servicoTipo.getValor()));
			//atualizarTipoServicoActionForm.setPavimento(""+servicoTipo.getIndicadorPavimento());
			
			atualizarTipoServicoActionForm.setIndicadorPavimentoRua(""+servicoTipo.getIndicadorPavimentoRua());
			atualizarTipoServicoActionForm.setIndicadorPavimentoCalcada(""+servicoTipo.getIndicadorPavimentoCalcada());
			
			atualizarTipoServicoActionForm.setIndicativoTipoSevicoEconomias(""+servicoTipo.getIndicativoTipoSevicoEconomias());
			
			atualizarTipoServicoActionForm.setAtualizacaoComercial(""+servicoTipo.getIndicadorAtualizaComercial());
			atualizarTipoServicoActionForm.setServicoTerceirizado(""+servicoTipo.getIndicadorTerceirizado());
			atualizarTipoServicoActionForm.setIndicadorFiscalizacaoInfracao(""+servicoTipo.getIndicadorFiscalizacaoInfracao());
			atualizarTipoServicoActionForm.setIndicadorVistoria(""+servicoTipo.getIndicadorVistoria());
			atualizarTipoServicoActionForm.setCodigoServico(""+servicoTipo.getCodigoServicoTipo());
			atualizarTipoServicoActionForm.setTempoMedioExecucao(""+servicoTipo.getTempoMedioExecucao());
			atualizarTipoServicoActionForm.setIdPrioridadeServico(servicoTipo.getServicoTipoPrioridade().getId().toString());
			atualizarTipoServicoActionForm.setPerfilServico(servicoTipo.getServicoPerfilTipo().getId().toString());
			atualizarTipoServicoActionForm.setDescricaoPerfilServico(servicoTipo.getServicoPerfilTipo().getDescricao());
			atualizarTipoServicoActionForm.setIndicadorPermiteAlterarValor(""+servicoTipo.getIndicadorPermiteAlterarValor());
			atualizarTipoServicoActionForm.setIndicadorEmpresaCobranca(""+servicoTipo.getIndicadorEmpresaCobranca());
			atualizarTipoServicoActionForm.setIndicativoObrigatoriedadeAtividade(""+servicoTipo.getIndicadorAtividade());
			atualizarTipoServicoActionForm.setIndicativoObrigatoriedadeAtividadeValor(""+servicoTipo.getIndicadorAtividade());
			atualizarTipoServicoActionForm.setIndicadorServicoOrdemSeletiva(""+servicoTipo.getIndicadorServicoOrdemSeletiva());
			atualizarTipoServicoActionForm.setIndicadorEnvioPesquisaSatisfacao(""+servicoTipo.getIndicadorEnvioPesquisaSatisfacao());
			atualizarTipoServicoActionForm.setIndicadorInspecaoAnormalidade(""+servicoTipo.getIndicadorInspecaoAnormalidade());
			atualizarTipoServicoActionForm.setIndicadorProgramacaoAutomatica(""+servicoTipo.getIndicadorProgramacaoAutomatica());
			atualizarTipoServicoActionForm.setIndicadorProgramacaoAutomaticaValor(""+servicoTipo.getIndicadorProgramacaoAutomatica());
			atualizarTipoServicoActionForm.setIndicadorEncAutomaticoRAQndEncOS(""+servicoTipo.getIndicadorEncAutomaticoRAQndEncOS());
			atualizarTipoServicoActionForm.setIndicadorCorrecaoAnormalidade(""+servicoTipo.getIndicadorCorrecaoAnormalidade());
			atualizarTipoServicoActionForm.setIndicadorCobrarJuros("" + servicoTipo.getIndicadorCobrarJuros());
			atualizarTipoServicoActionForm.setIndicadorIncluirDebito("" + servicoTipo.getIndicadorIncluirDebito());
			atualizarTipoServicoActionForm.setIndicadorServicoCobranca("" + servicoTipo.getIndicadorServicoCobranca());
			
			FiltroServicoTipoAtividade filtroServicoTipoAtividade = new FiltroServicoTipoAtividade();
			filtroServicoTipoAtividade.adicionarParametro(new ParametroSimples(FiltroServicoTipoAtividade.SERVICO_TIPO_ID, servicoTipo.getId()));
			filtroServicoTipoAtividade.adicionarCaminhoParaCarregamentoEntidade("atividade");
			
			FiltroServicoTipoMaterial filtroServicoTipoMaterial = new FiltroServicoTipoMaterial();
			filtroServicoTipoMaterial.adicionarParametro(new ParametroSimples(FiltroServicoTipoMaterial.ID_SERVICO_TIPO, servicoTipo.getId()));
			filtroServicoTipoMaterial.adicionarCaminhoParaCarregamentoEntidade("material");
			
			FiltroServicoTipoMotivoEncerramento filtroServicoTipoMotivoEncerramento = new FiltroServicoTipoMotivoEncerramento();
			filtroServicoTipoMotivoEncerramento.adicionarParametro(new ParametroSimples(FiltroServicoTipoMotivoEncerramento.SERVICO_TIPO_ID, servicoTipo.getId()));
			filtroServicoTipoMotivoEncerramento.adicionarCaminhoParaCarregamentoEntidade(FiltroServicoTipoMotivoEncerramento.ATENDIMENTO_MOTIVO_ENCERRAMENTO);
			
			
			Collection colecaoServicoTipoMaterial = fachada.pesquisar(filtroServicoTipoMaterial, ServicoTipoMaterial.class.getName());
			
			Collection colecaoServicoTipoAtividade = fachada.pesquisar(filtroServicoTipoAtividade, ServicoTipoAtividade.class.getName());
			
			Collection colecaoAtendimentoMotivoEncerramento = fachada.pesquisar(filtroServicoTipoMotivoEncerramento, ServicoTipoMotivoEncerramento.class.getName());
			
			if (colecaoServicoTipoMaterial != null && !colecaoServicoTipoMaterial.isEmpty()){
				atualizarTipoServicoActionForm.setServicoTipoMateriais(colecaoServicoTipoMaterial);
			}
			
			if (colecaoServicoTipoAtividade == null || colecaoServicoTipoAtividade.isEmpty()) {
				atualizarTipoServicoActionForm.setIndicadorAtividadeUnica("1");
				sessao.removeAttribute("colecaoServicoTipoAtividade");
			} else {
				atualizarTipoServicoActionForm.setIndicadorAtividadeUnica("2");
				sessao.setAttribute("colecaoServicoTipoAtividade", colecaoServicoTipoAtividade);
				atualizarTipoServicoActionForm.setServicoTipoAtividades(colecaoServicoTipoAtividade);
			}
			

			sessao.setAttribute("colecaoServicoTipoMaterial", colecaoServicoTipoMaterial);
			
			if(colecaoAtendimentoMotivoEncerramento != null && !colecaoAtendimentoMotivoEncerramento.isEmpty()){
				Iterator iter = colecaoAtendimentoMotivoEncerramento.iterator();
				ArrayList<AtendimentoMotivoEncerramento> motivosEncerramento = new ArrayList<AtendimentoMotivoEncerramento>();
				while(iter.hasNext()){
					ServicoTipoMotivoEncerramento servicoTipoMotivoEncerramento = (ServicoTipoMotivoEncerramento) iter.next();
					if(servicoTipoMotivoEncerramento.getMotivoEncerramento() !=null){
						motivosEncerramento.add(servicoTipoMotivoEncerramento.getMotivoEncerramento());
					}
				}
				
				sessao.setAttribute("colecaoAtendimentoMotivosEncerramentoInseridos", motivosEncerramento);
			}
			
			
			if(servicoTipo.getDebitoTipo() != null){
				atualizarTipoServicoActionForm.setIdTipoDebito(""+servicoTipo.getDebitoTipo().getId());
				atualizarTipoServicoActionForm.setDescricaoTipoDebito(""+servicoTipo.getDebitoTipo().getDescricao());
			}
			if(servicoTipo.getCreditoTipo() != null){
				atualizarTipoServicoActionForm.setIdTipoCredito(""+servicoTipo.getCreditoTipo().getId());
				atualizarTipoServicoActionForm.setIdPrioridadeServico(""+servicoTipo.getServicoTipoPrioridade().getId());
			}
			atualizarTipoServicoActionForm.setPerfilServico(""+servicoTipo.getServicoPerfilTipo().getId());
			if(servicoTipo.getServicoTipoReferencia() !=  null){
				atualizarTipoServicoActionForm.setIdTipoServicoReferencia(""+servicoTipo.getServicoTipoReferencia().getId());
				atualizarTipoServicoActionForm.setDescricaoTipoServicoReferencia(""+servicoTipo.getServicoTipoReferencia().getDescricao());
			}
			atualizarTipoServicoActionForm.setIndicadorUso(""+servicoTipo.getIndicadorUso());
			
		}else{
		
			
//			Tipo de D�bito		
			Integer idDebitoTipo = Util.converterStringParaInteger(atualizarTipoServicoActionForm.getIdTipoDebito());
			
			if (Util.validarNumeroMaiorQueZERO(idDebitoTipo)) {
				try {
					DebitoTipo debitoTipo = fachada.pesquisarDebitoTipo(idDebitoTipo);
					servicoTipo.setDebitoTipo(debitoTipo);
					atualizarTipoServicoActionForm.setIdTipoDebito(debitoTipo.getId().toString());
					atualizarTipoServicoActionForm.setDescricaoTipoDebito(debitoTipo.getDescricao());
				} catch (FachadaException e) {
					servicoTipo.setDebitoTipo(null);
					atualizarTipoServicoActionForm.setDescricaoTipoDebito("Tipo de D�bito Inexistente");
				}
			} else {
				servicoTipo.setDebitoTipo(null);
			}
			
			
			
			if(atualizarTipoServicoActionForm.getIdTipoCredito() != null && Integer.parseInt(atualizarTipoServicoActionForm.getIdTipoCredito()) != ConstantesSistema.NUMERO_NAO_INFORMADO){
				httpServletRequest.setAttribute("desabilitaCredito", "desabilitaCredito");			
			}
			//Perfil do Servi�o
			
			Integer idServicoPerfilTipo = Util.converterStringParaInteger(atualizarTipoServicoActionForm.getPerfilServico());
			
			if (Util.validarNumeroMaiorQueZERO(idServicoPerfilTipo)) {
				try {
					ServicoPerfilTipo servicoPerfilTipo = fachada.pesquisarServicoPerfilTipo(idServicoPerfilTipo);
					servicoTipo.setServicoPerfilTipo(servicoPerfilTipo);
					atualizarTipoServicoActionForm.setPerfilServico(servicoPerfilTipo.getId().toString());
					atualizarTipoServicoActionForm.setDescricaoPerfilServico(servicoPerfilTipo.getDescricao());
				} catch (FachadaException e) {
					servicoTipo.setServicoPerfilTipo(null);
					atualizarTipoServicoActionForm.setDescricaoPerfilServico("Tipo do Perfil Inexistente");
				}
			}
			
			//Tipo do Servi�o Refer�ncia
			
			Integer idServicoTipoReferencia = Util.converterStringParaInteger(atualizarTipoServicoActionForm.getIdTipoServicoReferencia());
			
			if (Util.validarNumeroMaiorQueZERO(idServicoTipoReferencia)) {
				try {
					ServicoTipoReferencia servicoTipoReferencia = fachada.pesquisarServicoTipoReferencia(idServicoTipoReferencia);
					servicoTipo.setServicoTipoReferencia(servicoTipoReferencia);
					atualizarTipoServicoActionForm.setIdTipoServicoReferencia(servicoTipoReferencia.getId().toString());
					atualizarTipoServicoActionForm.setDescricaoTipoServicoReferencia(servicoTipoReferencia.getDescricao());
				} catch (FachadaException e) {
					servicoTipo.setServicoTipoReferencia(null);
					atualizarTipoServicoActionForm.setDescricaoTipoServicoReferencia("Tipo de Servi�o de Refer�ncia Inexistente");
				}
			}
			
			//outras pesquisar pelo enter
			
			if("removeRowTableServicoTipoReferencia".equals(atualizarTipoServicoActionForm.getMethod())){
				sessao.removeAttribute("servicoTipoReferencia");
				atualizarTipoServicoActionForm.setMethod("");
			}
			
			if ("addServicoTipoAtividade".equals(atualizarTipoServicoActionForm.getMethod())) {
				atualizarTipoServicoActionForm.addServicoTipoAtividade();
			} 
			
			if ("removeServicoTipoAtividade".equals(atualizarTipoServicoActionForm.getMethod())) {
				atualizarTipoServicoActionForm.removeServicoTipoAtividade();
			}
			
			if ("addServicoTipoMaterial".equals(atualizarTipoServicoActionForm.getMethod())) {
				atualizarTipoServicoActionForm.addServicoTipoMaterial();
			} 
			
			if ("removeServicoTipoMaterial".equals(atualizarTipoServicoActionForm.getMethod())) {
				atualizarTipoServicoActionForm.removeServicoTipoMaterial();
			}
			
			if ("removeAllServicoTipoAtividade".equals(atualizarTipoServicoActionForm.getMethod())) {
				atualizarTipoServicoActionForm.removeAllServicoTipoAtividade();
			}
			
			httpServletRequest.setAttribute("servicoTipo", servicoTipo);
			sessao.removeAttribute("colecaoServicoTipoAtividade");
			sessao.removeAttribute("colecaoServicoTipoAtividade");
			sessao.setAttribute("colecaoServicoTipoAtividade", atualizarTipoServicoActionForm.getServicoTipoAtividades());
			sessao.setAttribute("colecaoServicoTipoMaterial", atualizarTipoServicoActionForm.getServicoTipoMateriais());
		}
			

			
			
			
			return retorno;
	}
					
		
}

