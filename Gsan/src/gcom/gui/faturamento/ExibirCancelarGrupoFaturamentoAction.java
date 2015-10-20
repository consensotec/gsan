
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
package gcom.gui.faturamento;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.faturamento.FiltroMotivoCancelamento;
import gcom.faturamento.conta.ContaMotivoCancelamento;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.Maior;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * [UC1241] - Filtrar Grupo Para Cancelar Faturamento
 * @author Arthur Carvalho
 */

public class ExibirCancelarGrupoFaturamentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("cancelarGrupoFaturamento");

		CancelarFaturamentoDeUmGrupoActionForm cancelarFaturamentoGrupoActionForm = (CancelarFaturamentoDeUmGrupoActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		//Pesquisa Sistema Parametro
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		//Pesquisa o grupo de faturamento
		FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
		filtroFaturamentoGrupo.adicionarParametro( new Maior(FiltroFaturamentoGrupo.ANO_MES_REFERENCIA, sistemaParametro.getAnoMesFaturamento()));
		Collection<FaturamentoGrupo> colecaoGrupoFaturamento = fachada.pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
		
		if (colecaoGrupoFaturamento == null || colecaoGrupoFaturamento.isEmpty()) {
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Faturamento Grupo");
		}
		
		httpServletRequest.setAttribute("colecaoGrupoFaturamento", colecaoGrupoFaturamento);
		
		//Pesquisa o motivo de cancelamento da conta
		FiltroMotivoCancelamento filtroMotivoCancelamento = new FiltroMotivoCancelamento();
		filtroMotivoCancelamento.adicionarParametro( new ParametroSimples(FiltroMotivoCancelamento.INDICADOR_USO, ConstantesSistema.SIM));
		
		Collection<ContaMotivoCancelamento>  colecaoMotivoCancelamento = fachada.pesquisar(filtroMotivoCancelamento, ContaMotivoCancelamento.class.getName() );
		if (colecaoMotivoCancelamento == null || colecaoMotivoCancelamento.isEmpty()) {
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Motivo de Cancelamento");
		}
		
		httpServletRequest.setAttribute("colecaoMotivoCancelamento", colecaoMotivoCancelamento);
		
		//[SB0001]-Exibir Dados Faturados do Grupo
		//Caso o grupo de faturamento seja informado carrega os valores do grupo.
		if ( cancelarFaturamentoGrupoActionForm.getGrupoFaturamento() != null && 
				!cancelarFaturamentoGrupoActionForm.getGrupoFaturamento().equals("-1") ) {
			
			Object[] dados = Fachada.getInstancia().pesquisarInformacoesGrupoFaturado(
				cancelarFaturamentoGrupoActionForm.getGrupoFaturamento(), sistemaParametro.getAnoMesFaturamento());
			
			setForm(cancelarFaturamentoGrupoActionForm, dados);
			
			
			//Pesquisa o grupo de faturamento
			FiltroFaturamentoGrupo filtroGrupo = new FiltroFaturamentoGrupo();
			filtroGrupo.adicionarParametro( new Maior(FiltroFaturamentoGrupo.ANO_MES_REFERENCIA, sistemaParametro.getAnoMesFaturamento()));
			filtroGrupo.adicionarParametro( new ParametroSimples(FiltroFaturamentoGrupo.ID, cancelarFaturamentoGrupoActionForm.getGrupoFaturamento()));
			Collection<FaturamentoGrupo> colecaoGrupo = fachada.pesquisar(filtroGrupo, FaturamentoGrupo.class.getName());
			FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) Util.retonarObjetoDeColecao(colecaoGrupo);
			
			
			cancelarFaturamentoGrupoActionForm.setReferenciaGrupoMenosUmMes(String.valueOf(Util.subtrairMesDoAnoMes(faturamentoGrupo.getAnoMesReferencia(), 1)));
			
			cancelarFaturamentoGrupoActionForm.setReferenciaFaturada(Util.formatarAnoMesParaMesAno(sistemaParametro.getAnoMesFaturamento().toString()));
		} else {
			limparForm(cancelarFaturamentoGrupoActionForm);
		}
		
		
       return retorno;

	}
	
	/**
	 * [SB0001]-Exibir Dados Faturados do Grupo
	 * Seta os valores no form
	 * @author Arthur Carvalho
	 * @param cancelarFaturamentoGrupoActionForm
	 * @param dados
	 */
	private void setForm(CancelarFaturamentoDeUmGrupoActionForm cancelarFaturamentoGrupoActionForm, Object[] dados) {
		//Valor agua
		if ( dados[0] != null ) {
			cancelarFaturamentoGrupoActionForm.setValorFaturadoAgua( Util.formatarMoedaReal( (BigDecimal) dados[0] ) );
		} else {
			cancelarFaturamentoGrupoActionForm.setValorFaturadoAgua( "0" );
		}
		
		//Valor esgoto
		if ( dados[1] != null ) {
			cancelarFaturamentoGrupoActionForm.setValorFaturadoEsgoto( Util.formatarMoedaReal( (BigDecimal) dados[1]) );
		} else {
			cancelarFaturamentoGrupoActionForm.setValorFaturadoEsgoto( "0" );
		}
		
		//valor debito
		if ( dados[2] != null ) {
			cancelarFaturamentoGrupoActionForm.setValorFaturadoDebito(Util.formatarMoedaReal( (BigDecimal) dados[2]) );
		} else {
			cancelarFaturamentoGrupoActionForm.setValorFaturadoDebito( "0" );
		}
		
		//valor credito
		if ( dados[3] != null ) {
			cancelarFaturamentoGrupoActionForm.setValorFaturadoCredito(Util.formatarMoedaReal((BigDecimal) dados[3]));
		} else {
			cancelarFaturamentoGrupoActionForm.setValorFaturadoCredito( "0" );
		}
		
		//valor imposto
		if ( dados[4] != null ) {
			cancelarFaturamentoGrupoActionForm.setValorFaturadoImpostos(Util.formatarMoedaReal( (BigDecimal) dados[4]) );
		} else {
			cancelarFaturamentoGrupoActionForm.setValorFaturadoImpostos( "0" );
		}

		//valor cobrado
		if ( dados[5] != null ) {
			cancelarFaturamentoGrupoActionForm.setValorFatudadoTotalCobrado(Util.formatarMoedaReal( (BigDecimal) dados[5]) );
		} else { 
			cancelarFaturamentoGrupoActionForm.setValorFatudadoTotalCobrado( "0" );
		}
		
		//qtd Normal
		if ( dados[6] != null ) {
			cancelarFaturamentoGrupoActionForm.setContasFaturadasNormal(Util.agruparNumeroEmMilhares( (Integer) dados[6] ) );
		} 
		
		//qtd Retificada
		if ( dados[7] != null ) {
			cancelarFaturamentoGrupoActionForm.setContasFaturadasRetificada( Util.agruparNumeroEmMilhares( (Integer) dados[7]));
		}
		
		//qtd incluida
		if ( dados[8] != null ) {
			cancelarFaturamentoGrupoActionForm.setContasFaturadasIncluida(Util.agruparNumeroEmMilhares((Integer) dados[8]));
		}
		//qtd cancelada
		if ( dados[9] != null ) {
			cancelarFaturamentoGrupoActionForm.setContasFaturadasCancelada(Util.agruparNumeroEmMilhares((Integer) dados[9]));
		}
		//qtd parcelada
		if ( dados[10] != null ) {
			cancelarFaturamentoGrupoActionForm.setContasFaturadasParcelada(Util.agruparNumeroEmMilhares( (Integer) dados[10]) );
		}
		//qtd paga
		if ( dados[11] != null ) {
			cancelarFaturamentoGrupoActionForm.setContasFaturadaPaga( Util.agruparNumeroEmMilhares( (Integer) dados[11]) );
		}
		
		//documento item
		if ( dados[12] != null ) {
			cancelarFaturamentoGrupoActionForm.setContasFaturadasVinculadasADcoumentoCobranca(Util.agruparNumeroEmMilhares( (Integer) dados[12]) );
		}
		
		//fatura item
		if ( dados[13] != null ) {
			cancelarFaturamentoGrupoActionForm.setContasFaturadasVinculadasAFaturamentoAgrupada(Util.agruparNumeroEmMilhares( (Integer) dados[13]) );
		}
		//excluidas
		if ( dados[14] != null ) {
			cancelarFaturamentoGrupoActionForm.setTotalDeContasParaExclusao(Util.agruparNumeroEmMilhares( (Integer) dados[14]) );
		}
	}
	
	/**
	 * Metodo responsavel por limpar o form
	 * @author Arthur Carvalho
	 * @param cancelarFaturamentoGrupoActionForm
	 */
	private void limparForm(CancelarFaturamentoDeUmGrupoActionForm cancelarFaturamentoGrupoActionForm) {
		cancelarFaturamentoGrupoActionForm.setContasFaturadaPaga("");
    	cancelarFaturamentoGrupoActionForm.setContasFaturadasCancelada("");
    	cancelarFaturamentoGrupoActionForm.setContasFaturadasIncluida("");
    	cancelarFaturamentoGrupoActionForm.setContasFaturadasNormal("");
    	cancelarFaturamentoGrupoActionForm.setContasFaturadasParcelada("");
    	cancelarFaturamentoGrupoActionForm.setContasFaturadasRetificada("");
    	cancelarFaturamentoGrupoActionForm.setContasFaturadasVinculadasADcoumentoCobranca("");
    	cancelarFaturamentoGrupoActionForm.setContasFaturadasVinculadasAFaturamentoAgrupada("");
    	cancelarFaturamentoGrupoActionForm.setReferenciaFaturada("");
    	cancelarFaturamentoGrupoActionForm.setTotalDeContasParaExclusao("");
    	cancelarFaturamentoGrupoActionForm.setValorFatudadoTotalCobrado("");
    	cancelarFaturamentoGrupoActionForm.setValorFaturadoAgua("");
    	cancelarFaturamentoGrupoActionForm.setValorFaturadoCredito("");
    	cancelarFaturamentoGrupoActionForm.setValorFaturadoDebito("");
    	cancelarFaturamentoGrupoActionForm.setValorFaturadoEsgoto("");
    	cancelarFaturamentoGrupoActionForm.setValorFaturadoImpostos("");
    	cancelarFaturamentoGrupoActionForm.setMotivoCancelamento("");
    	cancelarFaturamentoGrupoActionForm.setGrupoFaturamento("");
	}

}
