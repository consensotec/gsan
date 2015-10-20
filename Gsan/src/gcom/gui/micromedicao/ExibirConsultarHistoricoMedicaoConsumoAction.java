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
package gcom.gui.micromedicao;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.cadastro.imovel.bean.ImovelMicromedicao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.consumo.FiltroConsumoHistorico;
import gcom.micromedicao.medicao.FiltroMedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action respons�vel pela pre-exibi��o da pagina de inserir bairro
 * 
 * @author S�vio Luiz
 * @created 28 de Junho de 2004
 */
public class ExibirConsultarHistoricoMedicaoConsumoAction extends GcomAction {
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
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("consultarHistoricoMedicaoConsumo");
        
        LeituraConsumoActionForm leituraConsumoActionForm = (LeituraConsumoActionForm) actionForm;
        
        Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a
		//HttpSession sessao = httpServletRequest.getSession(false);
        
		String codigoImovel = leituraConsumoActionForm.getImovel();
		
		Collection<MedicaoHistorico> medicoesHistorico = new ArrayList();
		Collection<ImovelMicromedicao> imoveisMicromedicao = new ArrayList();
		
		FiltroMedicaoHistorico filtroMedicaoHistorico = new FiltroMedicaoHistorico();
		FiltroConsumoHistorico filtroConsumoHistorico = new FiltroConsumoHistorico();

		//MedicaoHistorico
		filtroMedicaoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel");
		filtroMedicaoHistorico.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeFaturamento");
		filtroMedicaoHistorico.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeInformada");
		filtroMedicaoHistorico.adicionarCaminhoParaCarregamentoEntidade("leituraSituacaoAtual");
		filtroMedicaoHistorico.adicionarCaminhoParaCarregamentoEntidade("ligacaoAgua");
		
		
		//Consumo Historico
		filtroConsumoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel");
		filtroConsumoHistorico.adicionarCaminhoParaCarregamentoEntidade("consumoTipo");
		filtroConsumoHistorico.adicionarCaminhoParaCarregamentoEntidade("consumoAnormalidade");
		
		FiltroLigacaoAgua filtroLigacaoAgua = new FiltroLigacaoAgua();
		filtroLigacaoAgua.adicionarCaminhoParaCarregamentoEntidade("imovel");
		filtroLigacaoAgua.adicionarParametro(new ParametroSimples(FiltroLigacaoAgua.ID, codigoImovel));
		Collection<LigacaoAgua> ligacoesAgua = fachada.pesquisar(filtroLigacaoAgua, LigacaoAgua.class.getName());
		
		filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(FiltroMedicaoHistorico.IMOVEL_ID, codigoImovel, ParametroSimples.CONECTOR_OR));
		filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(
				FiltroMedicaoHistorico.LIGACAO_AGUA_ID, codigoImovel));
		filtroMedicaoHistorico.setCampoOrderBy(FiltroMedicaoHistorico.ANO_MES_REFERENCIA_FATURAMENTO);
		
		if(!ligacoesAgua.isEmpty()){
			filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(FiltroMedicaoHistorico.MEDICAO_TIPO_ID, MedicaoTipo.LIGACAO_AGUA));
		}else{
			filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(FiltroMedicaoHistorico.MEDICAO_TIPO_ID, MedicaoTipo.POCO));
		}
		medicoesHistorico = fachada.pesquisar(filtroMedicaoHistorico, MedicaoHistorico.class.getName());
		if(!medicoesHistorico.isEmpty()){
			Iterator iteratorMedicaoHistorico = medicoesHistorico.iterator();
			
			while(iteratorMedicaoHistorico.hasNext()){
				MedicaoHistorico medicaoHistoricoConsumo = (MedicaoHistorico)iteratorMedicaoHistorico.next();
				if(medicaoHistoricoConsumo.getAnoMesReferencia()!= 0){
					filtroConsumoHistorico.limparListaParametros();
					filtroConsumoHistorico.adicionarParametro(new ParametroSimples(FiltroConsumoHistorico.ANO_MES_FATURAMENTO, medicaoHistoricoConsumo.getAnoMesReferencia()));
					filtroConsumoHistorico.adicionarParametro(new ParametroSimples(FiltroConsumoHistorico.IMOVEL_ID, codigoImovel));
					filtroConsumoHistorico.setCampoOrderBy(FiltroConsumoHistorico.ANO_MES_FATURAMENTO);
					
					Collection<ConsumoHistorico> collectionConsumoHistorico = fachada.pesquisar(filtroConsumoHistorico, ConsumoHistorico.class.getName());
					if(!collectionConsumoHistorico.isEmpty()){
						ConsumoHistorico consumoHistoricoNovo = (ConsumoHistorico) collectionConsumoHistorico.iterator().next();
						ImovelMicromedicao imovelMicromedicao = new ImovelMicromedicao();
						imovelMicromedicao.setConsumoHistorico(consumoHistoricoNovo);
						imovelMicromedicao.setMedicaoHistorico(medicaoHistoricoConsumo);
						//if(!imoveisMicromedicao.contains(imovelMicromedicao)){
							imoveisMicromedicao.add(imovelMicromedicao);
						//}
						//obtem qtd de dias do consumo
						if(medicaoHistoricoConsumo.getDataLeituraAnteriorFaturamento() != null &&
								medicaoHistoricoConsumo.getDataLeituraAtualFaturamento() != null){
							int qtdDias = Util.obterQuantidadeDiasEntreDuasDatas(medicaoHistoricoConsumo.getDataLeituraAnteriorFaturamento(),
									medicaoHistoricoConsumo.getDataLeituraAtualFaturamento());
							imovelMicromedicao.setQtdDias("" + qtdDias);
						}
					}
				}
			}
			
			//coloca a colecao de medicao historico no request
			httpServletRequest.setAttribute("medicoesHistoricos", medicoesHistorico);
			//coloca colecao de consumo historico no request
			httpServletRequest.setAttribute("imoveisMicromedicao", imoveisMicromedicao);
		}
		
		//sessao.setAttribute("leituraConsumoActionForm", leituraConsumoActionForm);
		
        return retorno;
    }
}
