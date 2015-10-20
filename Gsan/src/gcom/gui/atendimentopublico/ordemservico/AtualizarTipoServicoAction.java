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
import gcom.atendimentopublico.ordemservico.ServicoPerfilTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoBoletim;
import gcom.atendimentopublico.ordemservico.ServicoTipoMotivoEncerramento;
import gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade;
import gcom.atendimentopublico.ordemservico.ServicoTipoReferencia;
import gcom.atendimentopublico.ordemservico.ServicoTipoSubgrupo;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoTipo;
import gcom.faturamento.debito.DebitoTipo;
import gcom.gui.GcomAction;

import java.math.BigDecimal;
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
 * [UC0412] MANTER TIPO DE SERVICO
 * [SB0001] Atualizar Tipo de Servi�o
 *
 * @author Thiago Ten�rio, Pedro Alexandre
 * @date 01/11/2006, 14/12/2007
 */


public class AtualizarTipoServicoAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		AtualizarTipoServicoActionForm atualizarTipoServicoActionForm = (AtualizarTipoServicoActionForm) actionForm;		
		Fachada fachada = Fachada.getInstancia();
		//HttpSession sessao = httpServletRequest.getSession(false);		
		
		//Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
//		  ------  O servico Tipo foi encontrado
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		String idTipoServico = atualizarTipoServicoActionForm.getIdTipoServico();
		String descricao = atualizarTipoServicoActionForm.getDescricao();
		String abreviada = atualizarTipoServicoActionForm.getAbreviada();
		String subgrupo = atualizarTipoServicoActionForm.getSubgrupo();
		String valor = atualizarTipoServicoActionForm.getValorServico();
		String pavimento = atualizarTipoServicoActionForm.getPavimento();
		
		String indicadorPavimentoRua = atualizarTipoServicoActionForm.getIndicadorPavimentoRua();
		String indicadorPavimentoCalcada = atualizarTipoServicoActionForm.getIndicadorPavimentoCalcada();
		String indicadorPermiteAlterarValor = atualizarTipoServicoActionForm.getIndicadorPermiteAlterarValor();
		
		String indicativoTipoSevicoEconomias = atualizarTipoServicoActionForm.getIndicativoTipoSevicoEconomias();
		
		String atualizacaoComercial = atualizarTipoServicoActionForm.getAtualizacaoComercial();
		String terceirizado = atualizarTipoServicoActionForm.getServicoTerceirizado();
		String fiscalizacao = atualizarTipoServicoActionForm.getIndicadorFiscalizacaoInfracao();
		String vistoria = atualizarTipoServicoActionForm.getIndicadorVistoria();
		String codigo = atualizarTipoServicoActionForm.getCodigoServico();
		String tempoMedio = atualizarTipoServicoActionForm.getTempoMedioExecucao();
		String debitoTipo = atualizarTipoServicoActionForm.getIdTipoDebito();
		String creditoTipo = atualizarTipoServicoActionForm.getIdTipoCredito();
		String perfilTipo = atualizarTipoServicoActionForm.getPerfilServico();
		String referenciaTipo = atualizarTipoServicoActionForm.getIdTipoServicoReferencia();
		String indicadorUso = atualizarTipoServicoActionForm.getIndicadorUso();
		String idPrioridadeServico = atualizarTipoServicoActionForm.getIdPrioridadeServico();
		String indicadorEmpresaCobranca = atualizarTipoServicoActionForm.getIndicadorEmpresaCobranca();
		String indicativoObrigatoriedadeAtividade = atualizarTipoServicoActionForm.getIndicativoObrigatoriedadeAtividadeValor();
		String indicadorServicoOrdemSeletiva = atualizarTipoServicoActionForm.getIndicadorServicoOrdemSeletiva();
		String indicadorEnvioPesquisaSatisfacao = atualizarTipoServicoActionForm.getIndicadorEnvioPesquisaSatisfacao();
		String indicadorProgramacaoAutomatica = atualizarTipoServicoActionForm.getIndicadorProgramacaoAutomatica();
		String indicadorInspecaoAnormalidade = atualizarTipoServicoActionForm.getIndicadorInspecaoAnormalidade();
		String indicadorEncAutomaticoRAQndEncOS = atualizarTipoServicoActionForm.getIndicadorEncAutomaticoRAQndEncOS();
		String indicadorCorrecaoAnormalidade = atualizarTipoServicoActionForm.getIndicadorCorrecaoAnormalidade();
		String indicadorIncluirDebito = atualizarTipoServicoActionForm.getIndicadorIncluirDebito();
		String indicadorCobrarJuros = atualizarTipoServicoActionForm.getIndicadorCobrarJuros();
		
		ServicoTipo servicoTipo = new ServicoTipo();
		
		servicoTipo.setId(new Integer(idTipoServico));
		servicoTipo.setDescricao(descricao);
		servicoTipo.setDescricaoAbreviada(abreviada);
		servicoTipo.setIndicadorServicoOrdemSeletiva(Short.parseShort(indicadorServicoOrdemSeletiva));
		servicoTipo.setIndicadorEnvioPesquisaSatisfacao(Short.parseShort(indicadorEnvioPesquisaSatisfacao));
		servicoTipo.setIndicadorInspecaoAnormalidade(Short.parseShort(indicadorInspecaoAnormalidade));
		servicoTipo.setIndicadorProgramacaoAutomatica(Short.parseShort(indicadorProgramacaoAutomatica));
		ServicoTipoSubgrupo servicoTipoSubgrupo = new ServicoTipoSubgrupo();
		servicoTipoSubgrupo.setId(new Integer(subgrupo));
		servicoTipo.setServicoTipoSubgrupo(servicoTipoSubgrupo);
		
		if(valor != null && !valor.trim().equals("")){
			servicoTipo.setValor(new BigDecimal(valor.replace(",", ".")));
		}
		
		servicoTipo.setIndicadorPavimentoRua(new Short(indicadorPavimentoRua));
		servicoTipo.setIndicadorPavimentoCalcada(new Short(indicadorPavimentoCalcada));
		
		servicoTipo.setIndicadorPermiteAlterarValor(new Short(indicadorPermiteAlterarValor));
		servicoTipo.setIndicativoTipoSevicoEconomias(new Short(indicativoTipoSevicoEconomias));
		
		servicoTipo.setIndicadorAtualizaComercial(new Short(atualizacaoComercial));
		servicoTipo.setIndicadorTerceirizado(new Short(terceirizado));
		servicoTipo.setIndicadorFiscalizacaoInfracao(new Short(fiscalizacao));
		servicoTipo.setIndicadorVistoria(new Short(vistoria));
		servicoTipo.setIndicadorFiscalizacaoInfracao(new Short(fiscalizacao));
		servicoTipo.setCodigoServicoTipo(codigo);
		servicoTipo.setTempoMedioExecucao(new Short(tempoMedio));
		servicoTipo.setIndicadorEmpresaCobranca(new Short(indicadorEmpresaCobranca));
		servicoTipo.setIndicadorAtividade(new Short (indicativoObrigatoriedadeAtividade));
		
		ServicoTipoPrioridade servicoTipoPrioridade = new ServicoTipoPrioridade();
		servicoTipoPrioridade.setId(new Integer(idPrioridadeServico));
		servicoTipo.setServicoTipoPrioridade(servicoTipoPrioridade);
		
		if(indicadorEncAutomaticoRAQndEncOS != null && !indicadorEncAutomaticoRAQndEncOS.equals("")){
			servicoTipo.setIndicadorEncAutomaticoRAQndEncOS(new Short(indicadorEncAutomaticoRAQndEncOS));
		}
		
		if(debitoTipo != null && !debitoTipo.trim().equals("")){
			DebitoTipo debitoTipoObj = new DebitoTipo();
			debitoTipoObj.setId(new Integer(debitoTipo));
			servicoTipo.setDebitoTipo(debitoTipoObj);
		}
		
		if(creditoTipo != null && !creditoTipo.trim().equals("-1")){
			CreditoTipo creditoTipoObj = new CreditoTipo();
			creditoTipoObj.setId(new Integer(creditoTipo));
			servicoTipo.setCreditoTipo(creditoTipoObj);
		}
		
		ServicoPerfilTipo servicoPerfilTipo = new ServicoPerfilTipo();
		servicoPerfilTipo.setId(new Integer(perfilTipo));
		servicoTipo.setServicoPerfilTipo(servicoPerfilTipo);
		
		if(referenciaTipo != null && !referenciaTipo.trim().equals("")){
			ServicoTipoReferencia servicoTipoReferencia = new ServicoTipoReferencia();
			servicoTipoReferencia.setId(new Integer(referenciaTipo));
			servicoTipo.setServicoTipoReferencia(servicoTipoReferencia);
		}
		
		if (sessao.getAttribute("colecaoServicoTipoAtividade") != null)	{
			servicoTipo.setServicoTipoAtividades((Collection) sessao.getAttribute("colecaoServicoTipoAtividade"));
			
		}
		
		if (sessao.getAttribute("colecaoServicoTipoMaterial") != null)	{
			servicoTipo.setServicoTipoMateriais((Collection) sessao.getAttribute("colecaoServicoTipoMaterial"));
			
		}
		
		//RM93 - adicionado por Vivianne Sousa - 07/01/2011 - analista:Rosana Carvalho
		ServicoTipoBoletim servicoTipoBoletim = null;
		if(atualizarTipoServicoActionForm.getIndicadorInformacoesBoletimMedicao() != null){
			servicoTipo.setIndicadorBoletim(new Short(atualizarTipoServicoActionForm.getIndicadorInformacoesBoletimMedicao()));
		
			if(atualizarTipoServicoActionForm.getIndicadorInformacoesBoletimMedicao().equals("1")){
				
				servicoTipoBoletim = new ServicoTipoBoletim();
				servicoTipoBoletim.setIndicadorPavimento(new Short(atualizarTipoServicoActionForm.getIndicativoPavimento()));
				servicoTipoBoletim.setIndicadorReposicaoAsfalto(new Short(atualizarTipoServicoActionForm.getIndicativoReposicaoAsfalto()));
				servicoTipoBoletim.setIndicadorReposicaoCalcada(new Short(atualizarTipoServicoActionForm.getIndicativoReposicaoCalcada()));
				servicoTipoBoletim.setIndicadorReposicaoParalelo(new Short(atualizarTipoServicoActionForm.getIndicativoReposicaoParalelo()));
			
			}
		}
		
		
		
		servicoTipo.setIndicadorUso(new Short(indicadorUso));
		servicoTipo.setUltimaAlteracao(new Date());
		
		if(indicadorCorrecaoAnormalidade == null || indicadorCorrecaoAnormalidade.equals("")){
			indicadorCorrecaoAnormalidade = "2";
		}
		servicoTipo.setIndicadorCorrecaoAnormalidade(Short.parseShort(indicadorCorrecaoAnormalidade));
		
		servicoTipo.setIndicadorIncluirDebito( Short.parseShort(indicadorIncluirDebito) );
		servicoTipo.setIndicadorCobrarJuros( Short.parseShort( indicadorCobrarJuros ) );
				
		//atualiza na base de dados Tipo Servi�o
		 fachada.atualizarServicoTipo(servicoTipo, servicoTipoBoletim);
		 
		 /*[RN2011071113][UC0412]
		 * @author Raimundo Martins
		 * @date 21/07/2011
		 * Associa��o de motivo de encerramento		  
		 */
		 
		if(sessao.getAttribute("colecaoAtendimentoMotivosEncerramentoInseridos") !=null){
			fachada.removerServicoTipoMotivoEncerramento(servicoTipo.getId());
			
			Collection colecaoAtendimentoMotivosEncerramento = (Collection)sessao.getAttribute("colecaoAtendimentoMotivosEncerramentoInseridos");
			ArrayList<AtendimentoMotivoEncerramento> motivos = new ArrayList<AtendimentoMotivoEncerramento>(colecaoAtendimentoMotivosEncerramento);
			for (AtendimentoMotivoEncerramento encerramento : motivos) {
				if(encerramento !=null){
					ServicoTipoMotivoEncerramento servicoTipoMotivoEncerramento = new ServicoTipoMotivoEncerramento();
				
					servicoTipoMotivoEncerramento.setMotivoEncerramento(encerramento);
					servicoTipoMotivoEncerramento.setServicoTipo(servicoTipo);
					servicoTipoMotivoEncerramento.setUltimaAlteracao(new Date());
					
					fachada.inserirServicoTipoMotivoEncerramento(servicoTipoMotivoEncerramento);
				}
			}
 
		}
			
		 
		
		//Monta a p�gina de sucesso
		montarPaginaSucesso(httpServletRequest, "Tipo de Servi�o "+servicoTipo.getDescricao()+" Alterado com Sucesso"
				//+" atualizado com sucesso."
				, "Realizar outra Manuten��o de Tipo de Servi�o",
				"exibirFiltrarTipoServicoAction.do?menu=sim");
		
		return retorno;
	}
	
}	      
    



