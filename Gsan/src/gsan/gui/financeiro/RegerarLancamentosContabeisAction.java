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
package gsan.gui.financeiro;

import gsan.batch.Processo;
import gsan.batch.ProcessoIniciado;
import gsan.batch.ProcessoSituacao;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.financeiro.FiltroLancamentoOrigem;
import gsan.financeiro.lancamento.LancamentoOrigem;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *  [UC1383] Regerar Lan�amentos Cont�beis
 * 
 * @author Rodrigo Cabral
 * @created 16/10/2012
 */
public class RegerarLancamentosContabeisAction extends GcomAction {
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

		// Seta o mapeamento de retorno para a tela de sucesso.
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		RegerarLancamentosContabeisActionForm regerarLancamentosContabeisActionForm = (RegerarLancamentosContabeisActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		//recupera os par�metros informados pelo usu�rio.
		String idLancamentoOrigem = regerarLancamentosContabeisActionForm.getIdLacamentoOrigem();
		String mesAnoLancamento = regerarLancamentosContabeisActionForm.getAnoMesLancamento();
		
        
		//verifica se a origem do lan�amento foi informada.
		if(idLancamentoOrigem == null || idLancamentoOrigem.trim().equals("")){
			throw new ActionServletException("atencao.naoinformado",null, "Lan�amento Origem");
		}
		
		/*
		 * Caso a data n�o tenha sido informada levanta a exce��o para o usu�rio.
		 */
		if(mesAnoLancamento == null || mesAnoLancamento.trim().equals("")){
			throw new ActionServletException("atencao.naoinformado",null, "Data de Lan�amento");
		}
		
		//recupera o m�s e o ano informados
		String mes = mesAnoLancamento.substring(0, 2);
        String ano = mesAnoLancamento.substring(3, 7);
        String anoMesReferenciaContabilFormatado = ano + mes;
        
        SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
        
        FiltroLancamentoOrigem filtroLancamentoOrigem = new FiltroLancamentoOrigem();
        filtroLancamentoOrigem.adicionarParametro(new ParametroSimples(FiltroLancamentoOrigem.ID, idLancamentoOrigem));
        Collection<LancamentoOrigem> colecaoLancamento = fachada.pesquisar(filtroLancamentoOrigem, LancamentoOrigem.class.getName());
        LancamentoOrigem lancamentoOrigem = (LancamentoOrigem) Util.retonarObjetoDeColecao(colecaoLancamento);
        
        Boolean anoMesValido = true;
        String anoMesOrigem = null;
        
        if (lancamentoOrigem.getDescricao().equals("ARRECADACAO")){
        		anoMesValido = Util.compararAnoMesReferencia(anoMesReferenciaContabilFormatado, sistemaParametro.getAnoMesArrecadacao().toString(), "<");
        		anoMesOrigem = sistemaParametro.getAnoMesArrecadacao().toString();
        	
        } else if (lancamentoOrigem.getDescricao().equals("FATURAMENTO")){
        	
        	anoMesValido = Util.compararAnoMesReferencia(anoMesReferenciaContabilFormatado, sistemaParametro.getAnoMesFaturamento().toString(), "<");
        	anoMesOrigem = sistemaParametro.getAnoMesFaturamento().toString();
        }
        
        if (!anoMesValido){
        	String mesAnoOrigem = anoMesOrigem.substring(4,6) + "/" + anoMesOrigem.substring(0,4);
        	throw new ActionServletException("atencao.mes_ano_nao_corresponde",null, mesAnoOrigem);
        }
        
        String idTipoPerda = null;
        
        if(lancamentoOrigem != null && lancamentoOrigem.getPerdasTipo() != null){
        	idTipoPerda = lancamentoOrigem.getPerdasTipo().getId().toString();
        }
        
        //Cria o map que vai armazenar os dados para iniciar o processamento do batch
        Map<String, Object> dadosProcessamento = new HashMap();
        dadosProcessamento.put("anoMesReferenciaContabil",anoMesReferenciaContabilFormatado);
        dadosProcessamento.put("idTipoPerda", idTipoPerda);
		
        //Indica que o processo vai ser o de Gerar Resumo dos Devedores Duvidosos.
        int idProcesso = lancamentoOrigem.getProcesso().getId();

        //Monta as informa��es para iniciar o processo
		Date dataHoraAgendamento = new Date();
		ProcessoIniciado processoIniciado = new ProcessoIniciado();
		Processo processo = new Processo();
		processo.setId(idProcesso);
		processoIniciado.setDataHoraAgendamento(dataHoraAgendamento);
		
		//Adiciona a situa��o e o usu�rio ao objeto.
		ProcessoSituacao processoSituacao = new ProcessoSituacao();
		processoIniciado.setProcesso(processo);
		processoIniciado.setProcessoSituacao(processoSituacao);
		processoIniciado.setUsuario(this.getUsuarioLogado(httpServletRequest));
		
		fachada.inserirProcessoIniciado(processoIniciado, dadosProcessamento);
		
		// montando p�gina de sucesso
		montarPaginaSucesso(httpServletRequest, "Regera��o dos Lan�amentos Cont�beis enviados para processamento!",
				"Regerar Lan�amentos Cont�beis", "/exibirRegerarLancamentosContabeisAction.do");

		return retorno;
	}
}
