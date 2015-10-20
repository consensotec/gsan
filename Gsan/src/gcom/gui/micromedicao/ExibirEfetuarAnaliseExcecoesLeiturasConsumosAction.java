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

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.bean.ImovelMicromedicao;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.consumo.FiltroConsumoHistorico;
import gcom.micromedicao.medicao.FiltroMedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action respons�vel pela pre-exibi��o da pagina de inserir bairro
 * 
 * @author S�vio Luiz
 * @created 28 de Junho de 2004
 */
public class ExibirEfetuarAnaliseExcecoesLeiturasConsumosAction extends GcomAction {
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

         ActionForward retorno = actionMapping.findForward("efetuarAnaliseExcecoesLeiturasConsumosJsp");
         
         Fachada fachada = Fachada.getInstancia();
        
        
//       Mudar isso quando tiver esquema de seguran�a
 		HttpSession sessao = httpServletRequest.getSession(false); 
         
        //Collection<ImovelMicromedicao> imoveisMicromedicao = new ArrayList();
        
        //imoveisMicromedicao = (Collection<ImovelMicromedicao>) httpServletRequest.getAttribute("imoveisFiltrados");
        Collection imoveisFiltradosInicial = (Collection) sessao.getAttribute("imoveisFiltrados");
        
        Collection imovelPesquisa = null;
        Collection medicaoPesquisa = null;
        Collection consumoPesquisa = null;
        Collection imoveisFiltrados = new ArrayList();
        
        SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
        
        
        FiltroImovel filtroImovel = new FiltroImovel();
        FiltroMedicaoHistorico filtroMedicaoHistorico = new FiltroMedicaoHistorico();
        FiltroConsumoHistorico filtroConsumoHistorico = new FiltroConsumoHistorico();
        
        Iterator iterator = imoveisFiltradosInicial.iterator();
        
        Imovel imovel = null;
        MedicaoHistorico medicaoHistorico = null;
        ConsumoHistorico consumoHistorico = null;
        ImovelMicromedicao imovelMicromedicao = null;
        
        while(iterator.hasNext()){
        	Integer idImovel = (Integer)iterator.next();
        	
        	filtroImovel.limparListaParametros();
        	filtroMedicaoHistorico.limparListaParametros();
        	filtroConsumoHistorico.limparListaParametros();
        	
        	//---- Montar objeto ImovelMicromedicao para mostrar na tela efetuarExcecoesLeitura
        	
        	
        	//-----Imovel q fara parte do objeto
        	filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
        	filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
        	filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra.rota.faturamentoGrupo");
        	filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPerfil");
        	
        	filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel));
        	
        	imovelPesquisa = fachada.pesquisar(filtroImovel, Imovel.class.getName());
        	if(!imovelPesquisa.isEmpty()){
        		imovel = (Imovel)imovelPesquisa.iterator().next();
        	}
        	
        	//------Medicao que fara parte do objeto        	
        	filtroMedicaoHistorico.adicionarCaminhoParaCarregamentoEntidade("medicaoTipo");
        	filtroMedicaoHistorico.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeFaturamento");
        	
        	filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(FiltroMedicaoHistorico.IMOVEL_ID, 
        			idImovel, ParametroSimples.CONECTOR_OR));
        	filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(FiltroMedicaoHistorico.LIGACAO_AGUA_ID,
        			idImovel));
        	//Faz o teste do ano mes faturamento para pegar a medicao do ano de referencia
        	if(imovel != null && imovel.getQuadra() != null &&
        			imovel.getQuadra().getRota() != null &&
        			imovel.getQuadra().getRota().getFaturamentoGrupo() != null){
        		
        		filtroMedicaoHistorico.adicionarParametro(new ParametroSimples(FiltroMedicaoHistorico.ANO_MES_REFERENCIA_FATURAMENTO,
        				sistemaParametro.getAnoMesFaturamento()));
        		
        	}
        	
        	medicaoPesquisa = fachada.pesquisar(filtroMedicaoHistorico, MedicaoHistorico.class.getName());
        	
        	if(!medicaoPesquisa.isEmpty()){
        		medicaoHistorico = (MedicaoHistorico)medicaoPesquisa.iterator().next();
        	}
        	
        	//-------Consumo Historico
        	filtroConsumoHistorico.adicionarCaminhoParaCarregamentoEntidade("consumoAnormalidade");
        	
        	filtroConsumoHistorico.adicionarParametro(new ParametroSimples(FiltroConsumoHistorico.IMOVEL_ID,
        			idImovel));
        	
//        	Faz o teste do ano mes faturamento para pegar a medicao do ano de referencia
        	if(imovel != null && imovel.getQuadra() != null &&
        			imovel.getQuadra().getRota() != null &&
        			imovel.getQuadra().getRota().getFaturamentoGrupo() != null){
        	
        		filtroConsumoHistorico.adicionarParametro(new ParametroSimples(
        				FiltroConsumoHistorico.ANO_MES_FATURAMENTO,
        				sistemaParametro.getAnoMesFaturamento()));
        	}
        	
        	consumoPesquisa = fachada.pesquisar(filtroConsumoHistorico, ConsumoHistorico.class.getName());
        	
        	if(!consumoPesquisa.isEmpty()){
        		consumoHistorico = (ConsumoHistorico)consumoPesquisa.iterator().next();
        	}
        	
        	imovelMicromedicao = new ImovelMicromedicao();
        	imovelMicromedicao.setImovel(imovel);
        	imovelMicromedicao.setMedicaoHistorico(medicaoHistorico);
        	imovelMicromedicao.setConsumoHistorico(consumoHistorico);
        	
        	imoveisFiltrados.add(imovelMicromedicao);
        }
        
        sessao.setAttribute("imoveisFiltrados", imoveisFiltrados);
        
        return retorno;
    }
}
