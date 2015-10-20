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
package gcom.gui.cobranca;

import java.util.ArrayList;
import java.util.Collection;

import gcom.cadastro.localidade.SetorComercial;
import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.CobrancaAcaoAtividadeComandoFiscalizacaoSituacao;
import gcom.cobranca.CobrancaAtividade;
import gcom.cobranca.FiltroCobrancaAcaoAtividadeComandoFiscalizacaoSituacao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import gcom.cobranca.ComandoAtividadeImoveis;
import gcom.cobranca.FiltroComandoAtividadeImoveis;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Permite consultar comandos de a��o de cobran�a 
 * [UC0325] Consultar Comandos de A��o de Conbran�a
 * @author Rafael Santos
 * @since 11/05/2006
 */
public class ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoAction  extends GcomAction{
	
	
	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirComandosAcaoCobrancaEventualDadosComando");
        
        String idCobrancaAcaoAtividadeComando =  httpServletRequest.getParameter("idCobrancaAcaoAtividadeEventual");
        
        Fachada fachada = Fachada.getInstancia();
        
        CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando =  fachada.obterCobrancaAcaoAtividadeComando(idCobrancaAcaoAtividadeComando);
        
        HttpSession sessao = httpServletRequest.getSession(false);        
        
        ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm = (ExibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm)actionForm;
        
        exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setIdCobrancaAcaoAtividadeComando(idCobrancaAcaoAtividadeComando);
        
        //LIMPAR CAMPOS
        exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setAcaoCobranca("");
        exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setAtividadeCobranca("");
        exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setCriterioUtilizado("");
        exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setCriterio("");
        exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setGrupoCobranca("");
        exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setGerenciaRegional("");
        exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setUnidadeNegocio("");
        exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setLocalidadeInicial("");
        exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setDescricaoLocalidadeInicial("");
        exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setLocalidadeFinal("");
    	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setDescricaoLocalidadeFinal("");
    	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setSetorComercialFinal("");
    	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setDescricaoSetorComercialInicial("");
    	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setSetorComercialInicial("");
    	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setDescricaoSetorComercialFinal("");
    	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setRotaInicial("");
    	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setRotaFinal("");
    	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setCliente("");
    	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setTipoRelacaoCliente("");
    	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setDataComando("");
    	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setHoraComando("");
    	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setDataRealizacao("");
    	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setHoraRealizacao("");
    	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setPeriodoReferenciaContasInicial("");
    	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setPeriodoReferenciaContasFinal("");
    	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setPeriodoVencimentoContasInicial("");
    	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setPeriodoVencimentoContasFinal("");
    	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setValorDocumentos("");
    	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setQuantidadeDocumentos("");
    	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setQuantidadeItensDocumentos("");
    	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setSituacaoComando("");
    	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setConsumoMedioInicial("");
    	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setConsumoMedioFinal("");
    	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setTipoConsumo("");
    	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setPeriodoFinalFiscalizacao("");
    	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setPeriodoInicialFiscalizacao("");
    	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setSituacaoFiscalizacao(null);
    	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setIdImovel("");
    	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setQuantidadeSmsEnviados("");
    	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setQuantidadeSmsGerados("");
    	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setQuantidadeEmailEnviados("");
    	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setQuantidadeEmailGerados("");
    	
    	sessao.removeAttribute("colecaoComandoAtividadeImoveis");
    	
        //cobran�a acao
        if(cobrancaAcaoAtividadeComando.getCobrancaAcao() != null ){
        	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setAcaoCobranca(cobrancaAcaoAtividadeComando.getCobrancaAcao().getDescricaoCobrancaAcao());
        }
        //atividade cobranca
        if(cobrancaAcaoAtividadeComando.getCobrancaAtividade() != null ){
        	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setAtividadeCobranca(cobrancaAcaoAtividadeComando.getCobrancaAtividade().getDescricaoCobrancaAtividade());
        }
        //criterio utilizado
        if(cobrancaAcaoAtividadeComando.getIndicadorCriterio() != null){
        	if(cobrancaAcaoAtividadeComando.getIndicadorCriterio().shortValue() == (short)1){
        		exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setCriterioUtilizado("Rota");
        	}else{
        		exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setCriterioUtilizado("Comando");	
        	}
        	
        }
        //criterio
        if(cobrancaAcaoAtividadeComando.getIndicadorCriterio() != null){
        	if(cobrancaAcaoAtividadeComando.getIndicadorCriterio().shortValue() == (short)2){
        		exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setCriterio(cobrancaAcaoAtividadeComando.getCobrancaCriterio().getDescricaoCobrancaCriterio());        		
        	}
        }
        
        //grupo cobranca
        if(cobrancaAcaoAtividadeComando.getCobrancaGrupo() != null){
        	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setGrupoCobranca(cobrancaAcaoAtividadeComando.getCobrancaGrupo().getDescricao());
        }
        
        //dados de localiza��o geografica
        //gerencia regional
        if(cobrancaAcaoAtividadeComando.getGerenciaRegional() != null){
        	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setGerenciaRegional(cobrancaAcaoAtividadeComando.getGerenciaRegional().getNome());
        }
        
        //dados de localiza��o geografica
        //unidade negocio
        if(cobrancaAcaoAtividadeComando.getUnidadeNegocio() != null){
        	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setUnidadeNegocio(cobrancaAcaoAtividadeComando.getUnidadeNegocio().getNome());
        }
        
        //localidade inicial
        if(cobrancaAcaoAtividadeComando.getLocalidadeInicial() != null){
        	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setLocalidadeInicial(cobrancaAcaoAtividadeComando.getLocalidadeInicial().getId().toString());
        	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setDescricaoLocalidadeInicial(cobrancaAcaoAtividadeComando.getLocalidadeInicial().getDescricao());
        }
        //localidade final
        if(cobrancaAcaoAtividadeComando.getLocalidadeFinal() != null){
        	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setLocalidadeFinal(cobrancaAcaoAtividadeComando.getLocalidadeFinal().getId().toString());
        	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setDescricaoLocalidadeFinal(cobrancaAcaoAtividadeComando.getLocalidadeFinal().getDescricao());
        }

        //setor comericial final
        if(cobrancaAcaoAtividadeComando.getCodigoSetorComercialFinal() != null){
        	SetorComercial setorComercial = fachada.obterSetorComercialLocalidade(cobrancaAcaoAtividadeComando.getLocalidadeInicial().getId().toString(),
        			cobrancaAcaoAtividadeComando.getCodigoSetorComercialInicial().toString());
        	
        	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setSetorComercialFinal(cobrancaAcaoAtividadeComando.getCodigoSetorComercialFinal().toString());
        	if(setorComercial != null){
        		exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setDescricaoSetorComercialInicial(setorComercial.getDescricao());
        		
        	}
        }
        //setor comercial inicial
        if(cobrancaAcaoAtividadeComando.getCodigoSetorComercialInicial() != null){
        	SetorComercial setorComercial = fachada.obterSetorComercialLocalidade(cobrancaAcaoAtividadeComando.getLocalidadeFinal().getId().toString(),
        			cobrancaAcaoAtividadeComando.getCodigoSetorComercialFinal().toString());

        	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setSetorComercialInicial(cobrancaAcaoAtividadeComando.getCodigoSetorComercialInicial().toString());
        	if(setorComercial != null){
        		exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setDescricaoSetorComercialFinal(setorComercial.getDescricao());
        		
        	}
        	
        	
        }

        //rota inicial
        if(cobrancaAcaoAtividadeComando.getRotaInicial() != null){
        	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setRotaInicial(cobrancaAcaoAtividadeComando.getRotaInicial().getCodigo().toString());
        }
        //rota final
        if(cobrancaAcaoAtividadeComando.getRotaFinal() != null){
        	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setRotaFinal(cobrancaAcaoAtividadeComando.getRotaFinal().getCodigo().toString());
        }
        //cliente
        if(cobrancaAcaoAtividadeComando.getCliente() != null){
        	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setCliente(cobrancaAcaoAtividadeComando.getCliente().getNome() );
        	
        }
        //cliente relacao tipo
        if(cobrancaAcaoAtividadeComando.getClienteRelacaoTipo() != null){
        	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setTipoRelacaoCliente(cobrancaAcaoAtividadeComando.getClienteRelacaoTipo().getDescricao());
        	
        }
              
        //data e hora do comando
        if(cobrancaAcaoAtividadeComando.getComando() != null){
        	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setDataComando(Util.formatarData(cobrancaAcaoAtividadeComando.getComando())  );
        	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setHoraComando(Util.formatarHoraSemData(cobrancaAcaoAtividadeComando.getComando()) );
        	
        }
		        
        //data e hora de realizacao
        if(cobrancaAcaoAtividadeComando.getRealizacao() != null){
        	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setDataRealizacao(Util.formatarData(cobrancaAcaoAtividadeComando.getRealizacao()));
        	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setHoraRealizacao(Util.formatarHoraSemData(cobrancaAcaoAtividadeComando.getRealizacao()));
        }

        //periodo de refernecia das contas inicial
        if(cobrancaAcaoAtividadeComando.getAnoMesReferenciaContaInicial() != null){
        	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setPeriodoReferenciaContasInicial(Util.formatarAnoMesParaMesAno(cobrancaAcaoAtividadeComando.getAnoMesReferenciaContaInicial().intValue()));
        }
        
        //periodo de refernecia das contas final
        if(cobrancaAcaoAtividadeComando.getAnoMesReferenciaContaFinal() != null){
        	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setPeriodoReferenciaContasFinal(Util.formatarAnoMesParaMesAno(cobrancaAcaoAtividadeComando.getAnoMesReferenciaContaFinal().intValue()));
        	
        }

        //periodo de vencimentos das contas inicial
        if(cobrancaAcaoAtividadeComando.getDataVencimentoContaInicial() != null){
        	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setPeriodoVencimentoContasInicial(Util.formatarData(cobrancaAcaoAtividadeComando.getDataVencimentoContaInicial()));
        }
        
        //periodo de vencimentos das contas final
        if(cobrancaAcaoAtividadeComando.getDataVencimentoContaFinal() != null){
        	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setPeriodoVencimentoContasFinal
        	(Util.formatarData(cobrancaAcaoAtividadeComando.getDataVencimentoContaFinal()));
        }
        
        //valor dos documentos
        if(cobrancaAcaoAtividadeComando.getValorDocumentos() != null){
        	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setValorDocumentos(
        			Util.formatarMoedaReal(cobrancaAcaoAtividadeComando.getValorDocumentos()));
        	
        }
        //quantidade de documentos
        if(cobrancaAcaoAtividadeComando.getQuantidadeDocumentos() != null){
        	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setQuantidadeDocumentos(cobrancaAcaoAtividadeComando.getQuantidadeDocumentos().toString());
        	
        }
        //quantidade de itens dos documentos
        if(cobrancaAcaoAtividadeComando.getQuantidadeItensCobrados() != null){
        	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setQuantidadeItensDocumentos(cobrancaAcaoAtividadeComando.getQuantidadeItensCobrados().toString());
        	
        }
        
		// [RM11569]
		// quantidade de sms gerados / enviados
        if (cobrancaAcaoAtividadeComando.getQuantidadeSmsGerados() != null) {
        	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setQuantidadeSmsGerados(cobrancaAcaoAtividadeComando.getQuantidadeSmsGerados().toString());
		}
		if (cobrancaAcaoAtividadeComando.getQuantidadeSmsEnviados() != null) {
			exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setQuantidadeSmsEnviados(cobrancaAcaoAtividadeComando.getQuantidadeSmsEnviados().toString());
		}

		// [RM11569]
		// quantidade de e-mail gerados / enviados
		if (cobrancaAcaoAtividadeComando.getQuantidadeEmailGerados() != null) {
			exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setQuantidadeEmailGerados(cobrancaAcaoAtividadeComando.getQuantidadeEmailGerados().toString());
		}
		if (cobrancaAcaoAtividadeComando.getQuantidadeEmailEnviados() != null) {
			exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setQuantidadeEmailEnviados(cobrancaAcaoAtividadeComando.getQuantidadeEmailEnviados().toString());
		}

		//situacao do comando
        if(cobrancaAcaoAtividadeComando.getRealizacao() != null){
        	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setSituacaoComando("Realizado");        	
        	if(cobrancaAcaoAtividadeComando.getCobrancaAtividade().getId().equals(CobrancaAtividade.EMITIR)){
            	 httpServletRequest.setAttribute("emitir", "sim");	
            }        	
        }else{
        	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setSituacaoComando("N�o Realizado");
        }
        
        if(cobrancaAcaoAtividadeComando.getConsumoMedioInicial()!=null){
        	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm
        		.setConsumoMedioInicial(cobrancaAcaoAtividadeComando.getConsumoMedioInicial().toString());
        }
        
        if(cobrancaAcaoAtividadeComando.getConsumoMedioFinal()!=null){
        	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm
        		.setConsumoMedioFinal(cobrancaAcaoAtividadeComando.getConsumoMedioFinal().toString());
        }
        
        if(cobrancaAcaoAtividadeComando.getTipoConsumo()!=null){
        	
        	switch (cobrancaAcaoAtividadeComando.getTipoConsumo().shortValue()) {
			case 1:
				exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm
        			.setTipoConsumo("MEDIDO");
				break;
			case 2:
				exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm
        			.setTipoConsumo("N�O MEDIDO");
				break;
			case 3:
				exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm
        			.setTipoConsumo("AMBOS");
				break;	
			default:
				break;
			} 	
        }
        
        if(cobrancaAcaoAtividadeComando.getPeriodoInicialFiscalizacao()!=null){
        	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm
        		.setPeriodoInicialFiscalizacao(Util.formatarData(
        				cobrancaAcaoAtividadeComando.getPeriodoInicialFiscalizacao()));
        }
        
        if(cobrancaAcaoAtividadeComando.getPeriodoFinalFiscalizacao()!=null){
        	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm
        		.setPeriodoFinalFiscalizacao(Util.formatarData(
        				cobrancaAcaoAtividadeComando.getPeriodoFinalFiscalizacao()));
        }
        
        //Imovel
        if(cobrancaAcaoAtividadeComando.getImovel()!=null){
        	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm
        		.setIdImovel(""+cobrancaAcaoAtividadeComando.getImovel().getId());
        }
        
        
        if(cobrancaAcaoAtividadeComando.getQuantidadeDiasVencimento()!=null){
        	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm
        		.setQuantidadeDiasVencimento("" + cobrancaAcaoAtividadeComando.getQuantidadeDiasVencimento());
        }
        
        FiltroComandoAtividadeImoveis filtroComandoAtividadeImoveis = new FiltroComandoAtividadeImoveis();
        filtroComandoAtividadeImoveis.adicionarParametro(new ParametroSimples (
        	FiltroComandoAtividadeImoveis.COBRANCA_ACAO_ATIVIDADE_COMANDO_ID, cobrancaAcaoAtividadeComando.getId()));
        
        Collection colecaoComandoAtividadeImoveis = fachada.pesquisar(
        	filtroComandoAtividadeImoveis, ComandoAtividadeImoveis.class.getName());
        
        if(colecaoComandoAtividadeImoveis !=null && !colecaoComandoAtividadeImoveis.isEmpty()){
        	sessao.setAttribute("colecaoComandoAtividadeImoveis", colecaoComandoAtividadeImoveis);
        }
        
        
        if(cobrancaAcaoAtividadeComando.getNomeArquivoRelacaoImoveis() !=null 
        		&& !cobrancaAcaoAtividadeComando.getNomeArquivoRelacaoImoveis().equals("")){
        	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setNomeArquivoRelacaoImoveis(
        		cobrancaAcaoAtividadeComando.getNomeArquivoRelacaoImoveis());
        }else{
        	exibirResultadoConsultarComandosAcaoCobrancaEventualDadosComandoActionForm.setNomeArquivoRelacaoImoveis(
        		"");
        }
        
        
		FiltroCobrancaAcaoAtividadeComandoFiscalizacaoSituacao filtroCobrancaAcaoFisc
			= new FiltroCobrancaAcaoAtividadeComandoFiscalizacaoSituacao();
		
		filtroCobrancaAcaoFisc.adicionarParametro(
				new ParametroSimples(
					FiltroCobrancaAcaoAtividadeComandoFiscalizacaoSituacao.COBRANCA_ACAO_ATIVIDADE_COMANDO_ID,
					cobrancaAcaoAtividadeComando.getId()));
		
		filtroCobrancaAcaoFisc.adicionarCaminhoParaCarregamentoEntidade(
				FiltroCobrancaAcaoAtividadeComandoFiscalizacaoSituacao.FISCALIZACAO_SITUACAO);
		
		Collection<CobrancaAcaoAtividadeComandoFiscalizacaoSituacao> colecaoCobrancaAcaoFisc =
			fachada.pesquisar(filtroCobrancaAcaoFisc, 
					CobrancaAcaoAtividadeComandoFiscalizacaoSituacao.class.getName());
		
		Collection colecaoFiscalizacao = null;
		if(!Util.isVazioOrNulo(colecaoCobrancaAcaoFisc)){
			colecaoFiscalizacao = new ArrayList();
			for (CobrancaAcaoAtividadeComandoFiscalizacaoSituacao helper : colecaoCobrancaAcaoFisc) {
				colecaoFiscalizacao.add(helper.getFiscalizacaoSituacao());
			}
		}
        
		if(colecaoFiscalizacao!=null && !colecaoFiscalizacao.isEmpty()){
			sessao.setAttribute("colecaoFiscalizacao", colecaoFiscalizacao);
		}else{
			sessao.removeAttribute("colecaoFiscalizacao");
		}
        
        sessao.removeAttribute("cobrancaAcaoAtividadeCronograma");
        return retorno;
    }

}
