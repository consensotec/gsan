/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.gui.financeiro;

import gcom.batch.Processo;
import gcom.batch.ProcessoIniciado;
import gcom.batch.ProcessoSituacao;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.financeiro.ParametrosPerdasOrgaoPublico;
import gcom.financeiro.ParametrosPerdasSocietarias;
import gcom.financeiro.PerdasTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Gerar resumo dos devedores duvidosos
 *
 * @author Pedro Alexandre
 * @date 28/05/2007
 */
public class GerarResumoDevedoresDuvidososAction extends GcomAction {
	/**
	 * Description of the Method
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
			ActionForm actionForm, 
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		GerarResumoDevedoresDuvidososActionForm form = (GerarResumoDevedoresDuvidososActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		String indicadorReprocessamento = null;
		
		if (form.getIdTipoPerda() == null || form.getIdTipoPerda().equals("") ) {
			throw new ActionServletException("atencao.tipo_perda_obrigatorio");
		}
		
		
		if ( form.getIdTipoPerda().equals(String.valueOf(PerdasTipo.PROVISAO_PERDAS_SOCIETARIAS) )) {
			
			//[SB0003] - Validar Critérios Para Perdas Societárias
			if (validarCriteriosPerdasSocietarias(form) ) {
				indicadorReprocessamento = "1";
			}
						
		} else if ( form.getIdTipoPerda().equals(String.valueOf(PerdasTipo.PERDAS_ORGAO_PUBLICO) )) {
			
			//[SB0003] - Validar Critérios Para Perdas Societárias
			if ( validarCriteriosPerdasOrgaoPublico(form) ){
				indicadorReprocessamento = "1";
			}
			
		} else if (form.getIdTipoPerda().equals(String.valueOf(PerdasTipo.RECUPERACAO_DA_PROVISAO_DE_PERDAS_SOCIETARIAS) )) {
			
			validarCriteriosPerdasRecuperacaoSocietaria(form);
		}

		//Recpera o ano/mês contábil informado pelo usuário
		//e formata para o formato de AAAAMM
		String anoMesReferenciaContabil = form.getAnoMesReferenciaContabil();
        String mes = anoMesReferenciaContabil.substring(0, 2);
        String ano = anoMesReferenciaContabil.substring(3, 7);
        String anoMesReferenciaContabilFormatado = ano + mes;
        
        //Cria o map que vai armazenar os dados para iniciar o processamento do batch
        Map<String, Object> dadosProcessamento = new HashMap();
        dadosProcessamento.put("anoMesReferenciaContabil",anoMesReferenciaContabilFormatado);
        dadosProcessamento.put("idTipoPerda", form.getIdTipoPerda());
        dadosProcessamento.put("indicadorReprocessamento", indicadorReprocessamento);
		
        //Indica que o processo vai ser o de Gerar Resumo dos Devedores Duvidosos.
        int idProcesso = Processo.GERAR_RESUMO_DEVEDORES_DUVIDOSOS;

        //Monta as informações para iniciar o processo
		Date dataHoraAgendamento = new Date();
		ProcessoIniciado processoIniciado = new ProcessoIniciado();
		Processo processo = new Processo();
		processo.setId(idProcesso);
		processoIniciado.setDataHoraAgendamento(dataHoraAgendamento);
		
		//Adiciona a situação e o usuário ao objeto.
		ProcessoSituacao processoSituacao = new ProcessoSituacao();
		processoIniciado.setProcesso(processo);
		processoIniciado.setProcessoSituacao(processoSituacao);
		processoIniciado.setUsuario(this.getUsuarioLogado(httpServletRequest));
		
		//Inseri o processo retornando qual o id gerado para o processo 
		//que foi iniciado no banco de dados.
		Integer codigoProcessoIniciadoGerado = (Integer) fachada.gerarResumoDevedoresDuvidosos(processoIniciado, dadosProcessamento);
	
		String tipoPerdaDescricao = "";
		
		if ( form.getIdTipoPerda().equals(String.valueOf(PerdasTipo.PROVISAO_PERDAS_SOCIETARIAS) )) {
			
			tipoPerdaDescricao = " da Provisão de Perdas Societárias";
						
		} else if ( form.getIdTipoPerda().equals(String.valueOf(PerdasTipo.PERDAS_ORGAO_PUBLICO) )) {
			
			tipoPerdaDescricao = " de Perdas de Orgão Publico";
			
		} else if (form.getIdTipoPerda().equals(String.valueOf(PerdasTipo.RECUPERACAO_DA_PROVISAO_DE_PERDAS_SOCIETARIAS) )) {
			
			tipoPerdaDescricao = " da Recuperação de Provisão de Perdas Societárias";
		} else {
			tipoPerdaDescricao = " dos Devedores Duvidosos";
		}
		
		sessao.removeAttribute("colecaoPerdasTipo");
		sessao.removeAttribute("baixaPerdaSocietaria");
		sessao.removeAttribute("baixaPerdaOrgaoPublico");
		//Monta a página de sucesso.
		montarPaginaSucesso(httpServletRequest, "Gerando Resumo"+tipoPerdaDescricao+". Codigo do Processo: " + codigoProcessoIniciadoGerado,
				"Gerar Resumo dos Devedores Duvidosos", "exibirGerarResumoDevedoresDuvidososAction.do");

		return retorno;
	}
	
	
	/**
	 * @author Arthur Carvalho
	 * @param form
	 */
	private boolean validarCriteriosPerdasSocietarias(GerarResumoDevedoresDuvidososActionForm form) {

		boolean retorno = false;
//		Integer quantidadeContas = Fachada.getInstancia().pesquisaQuantidadeContasBaixaSocietaria(form.getPeriodoBaixaInicial(), form.getPeriodoBaixaFinal(), 
//			form.getNumeroMesesContasInferiores(), form.getIndicadorCategoriaResidencial() , form.getIndicadorCategoriaComercial() , 
//			form.getIndicadorCategoriaIndustrial(), form.getIndicadorCategoriaPublica() , form.getIndicadorEsferaParticular() , 
//			form.getIndicadorEsferaMunicipal() , form.getIndicadorEsferaEstadual() , form.getIndicadorEsferaFederal());
//
//		//[FS0008] - Validar Quantidade de Contas	
//		if ( quantidadeContas == null || quantidadeContas == 0 ) {
//             throw new ActionServletException("Não existem contas que atendam aos critérios informados para baixa societária."); 
//		}
		
		
		ParametrosPerdasSocietarias parametrosPerdasSocietarias = Fachada.getInstancia()
					.pesquisarParametrosPerdasSocietarias(
							Util.formatarMesAnoComBarraParaAnoMes(form.getAnoMesReferenciaContabil()));
		//Caso seja um reprocessamento
		if ( parametrosPerdasSocietarias != null && parametrosPerdasSocietarias.getId() != null ) {
			Fachada.getInstancia().deletarParametrosPerdasSocietarias(Util.formatarMesAnoComBarraParaAnoMes( form.getAnoMesReferenciaContabil() ));
			retorno = true;
		}
		
		inserirParametrosPerdasSocietarias(form);
		return retorno;
	}
	
	/**
	 * @author Arthur Carvalho
	 * @param form
	 */
	private boolean validarCriteriosPerdasOrgaoPublico(GerarResumoDevedoresDuvidososActionForm form){
		
		boolean retorno = false;
//		Integer quantidadeContas = Fachada.getInstancia().pesquisaQuantidadeContasBaixaPerdasOrgaoPublico(form.getNumeroMesesContasVencidas(), form.getIndicadorEsferaPoderMunicipal(), 
//			form.getIndicadorEsferaPoderFederal());
//
//		//[FS0008] - Validar Quantidade de Contas	
//		if ( quantidadeContas == null || quantidadeContas == 0 ) {
//             throw new ActionServletException("Não existem contas que atendam aos critérios informados para baixa de Órgão Público."); 
//		}
		
		
		ParametrosPerdasOrgaoPublico parametrosPerdasOrgaoPublico = Fachada.getInstancia().pesquisarParametrosPerdasOrgaoPublico(
									Util.formatarMesAnoComBarraParaAnoMes(form.getAnoMesReferenciaContabil()));
		//Caso seja um reprocessamento
		if ( parametrosPerdasOrgaoPublico != null && parametrosPerdasOrgaoPublico.getId() != null ) {
			//[SB0015] - Excluir Parâmetros Perdas Órgãos Públicos
			Fachada.getInstancia().deletarParametrosPerdasOrgaoPublico(Util.formatarMesAnoComBarraParaAnoMes( form.getAnoMesReferenciaContabil() ));
			retorno = true;
		}
		
		inserirParametrosPerdasOrgaoPublico(form);
		return retorno;
	}
	
	private void validarCriteriosPerdasRecuperacaoSocietaria(GerarResumoDevedoresDuvidososActionForm form){
		
		//[FS0013] - Pesquisar Referência do Faturamento/Arrecadação 
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
       
        if ( sistemaParametro.getAnoMesFaturamento().toString().equals(form.getAnoMesReferenciaContabil() ) ) {
    	   throw new ActionServletException("Referência do faturamento correspondente a referência contábil informada ainda não foi encerrado."); 
        } else if ( sistemaParametro.getAnoMesArrecadacao().toString().equals(form.getAnoMesReferenciaContabil() ) ) {
    	   throw new ActionServletException("Referência da arrecadação correspondente a referência contábil informada ainda não foi encerrado.");
        }
		
	}
	
	/**
	 * Metodo responsavel por inserir ParametrosPerdasSocietarias 
	 * 
	 * @author Arthur Carvalho
	 * @param form
	 */
	private void inserirParametrosPerdasSocietarias(GerarResumoDevedoresDuvidososActionForm form) {
	
		ParametrosPerdasSocietarias parametrosPerdasSocietarias = new ParametrosPerdasSocietarias();
		
		parametrosPerdasSocietarias.setAnoMesReferenciaContabil(Util.formatarMesAnoComBarraParaAnoMes(form.getAnoMesReferenciaContabil()));
		parametrosPerdasSocietarias.setAnoMesReferenciaBaixaInicial(Util.formatarMesAnoComBarraParaAnoMes(form.getPeriodoBaixaInicial()));
		parametrosPerdasSocietarias.setAnoMesReferenciaBaixaFinal(Util.formatarMesAnoComBarraParaAnoMes(form.getPeriodoBaixaFinal()));
		
		parametrosPerdasSocietarias.setNumeroMesesReferenciaInferior(Integer.valueOf(form.getNumeroMesesContasInferiores()));
		parametrosPerdasSocietarias.setIndicadorGeracaoReal(Short.valueOf(form.getIndicadorTipoGeracao()));
		
		if ( form.getIndicadorCategoriaComercial() != null && form.getIndicadorCategoriaComercial().equals("1") ) {
			parametrosPerdasSocietarias.setIndicadorCategoriaComercial(Short.valueOf(form.getIndicadorCategoriaComercial()));	
		} else {
			parametrosPerdasSocietarias.setIndicadorCategoriaComercial(ConstantesSistema.NAO);
		}
		
		if ( form.getIndicadorCategoriaIndustrial() != null && form.getIndicadorCategoriaIndustrial().equals("1") ) {
			parametrosPerdasSocietarias.setIndicadorCategoriaIndustrial(Short.valueOf(form.getIndicadorCategoriaIndustrial()));	
		} else {
			parametrosPerdasSocietarias.setIndicadorCategoriaIndustrial(ConstantesSistema.NAO);
		}
		
		if ( form.getIndicadorCategoriaPublica() != null && form.getIndicadorCategoriaPublica().equals("1") ) {
			parametrosPerdasSocietarias.setIndicadorCategoriaPublica(Short.valueOf(form.getIndicadorCategoriaPublica()));	
		} else {
			parametrosPerdasSocietarias.setIndicadorCategoriaPublica(ConstantesSistema.NAO);
		}
		
		if ( form.getIndicadorCategoriaResidencial() != null && form.getIndicadorCategoriaResidencial().equals("1") ) {
			parametrosPerdasSocietarias.setIndicadorCategoriaResidencial(Short.valueOf(form.getIndicadorCategoriaResidencial()));	
		} else {
			parametrosPerdasSocietarias.setIndicadorCategoriaResidencial(ConstantesSistema.NAO);
		}
		
		
		if ( form.getIndicadorEsferaEstadual() != null && form.getIndicadorEsferaEstadual().equals("1") ) {
			parametrosPerdasSocietarias.setIndicadorEsferaEstadual(Short.valueOf(form.getIndicadorEsferaEstadual()));	
		} else {
			parametrosPerdasSocietarias.setIndicadorEsferaEstadual(ConstantesSistema.NAO);
		}
		
		if ( form.getIndicadorEsferaMunicipal() != null && form.getIndicadorEsferaMunicipal().equals("1") ) {
			parametrosPerdasSocietarias.setIndicadorEsferaMunicipal(Short.valueOf(form.getIndicadorEsferaMunicipal()));	
		} else {
			parametrosPerdasSocietarias.setIndicadorEsferaMunicipal(ConstantesSistema.NAO);
		}
		
		if ( form.getIndicadorEsferaParticular() != null && form.getIndicadorEsferaParticular().equals("1") ) {
			parametrosPerdasSocietarias.setIndicadorEsferaParticular(Short.valueOf(form.getIndicadorEsferaParticular()));	
		} else {
			parametrosPerdasSocietarias.setIndicadorEsferaParticular(ConstantesSistema.NAO);
		}
		
		if ( form.getIndicadorEsferaFederal() != null && form.getIndicadorEsferaFederal().equals("1") ) {
			parametrosPerdasSocietarias.setIndicadorEsferaFederal(Short.valueOf(form.getIndicadorEsferaFederal()));	
		} else {
			parametrosPerdasSocietarias.setIndicadorEsferaFederal(ConstantesSistema.NAO);
		}
		
		parametrosPerdasSocietarias.setUltimaAlteracao(new Date());
		
		Fachada.getInstancia().inserir(parametrosPerdasSocietarias);
		
	}
	
	/**
	 * Metodo responsavel por inserir ParametrosPerdasOrgaoPublico 
	 * 
	 * @author Arthur Carvalho
	 * @param form
	 */
	private void inserirParametrosPerdasOrgaoPublico(GerarResumoDevedoresDuvidososActionForm form) {
	
		ParametrosPerdasOrgaoPublico parametrosPerdasOrgaoPublico = new ParametrosPerdasOrgaoPublico();
		
		parametrosPerdasOrgaoPublico.setAnoMesReferenciaContabil(Util.formatarMesAnoComBarraParaAnoMes(form.getAnoMesReferenciaContabil()));
		
		if ( form.getIndicadorEsferaPoderFederal() != null && form.getIndicadorEsferaPoderFederal().equals("1") ) {
			parametrosPerdasOrgaoPublico.setIndicadorEsferaFederal(Short.valueOf(form.getIndicadorEsferaPoderFederal()));	
		} else {
			parametrosPerdasOrgaoPublico.setIndicadorEsferaFederal(ConstantesSistema.NAO);
		}
		
		if ( form.getIndicadorEsferaPoderMunicipal() != null && form.getIndicadorEsferaPoderMunicipal().equals("1") ) {
			parametrosPerdasOrgaoPublico.setIndicadorEsferaMunicipal(Short.valueOf(form.getIndicadorEsferaPoderMunicipal()));	
		} else {
			parametrosPerdasOrgaoPublico.setIndicadorEsferaMunicipal(ConstantesSistema.NAO);
		}
		
		parametrosPerdasOrgaoPublico.setNumeroMesesContasVencidas(Integer.valueOf(form.getNumeroMesesContasVencidas()));
		parametrosPerdasOrgaoPublico.setUltimaAlteracao(new Date());
		
		Fachada.getInstancia().inserir(parametrosPerdasOrgaoPublico);
		
	}
	
	
}
