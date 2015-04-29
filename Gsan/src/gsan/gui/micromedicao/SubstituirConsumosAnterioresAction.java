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
package gsan.gui.micromedicao;

import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.imovel.bean.ImovelMicromedicao;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.micromedicao.consumo.ConsumoHistorico;
import gsan.micromedicao.medicao.MedicaoTipo;
import gsan.seguranca.acesso.usuario.Usuario;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 */
public class SubstituirConsumosAnterioresAction extends GcomAction {

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

        ActionForward retorno = actionMapping.findForward("exibirDadosAnalise");

        //LeituraConsumoActionForm leituraConsumoActionForm = (LeituraConsumoActionForm) actionForm;

        Fachada fachada = Fachada.getInstancia();

        // Mudar isso quando tiver esquema de seguran�a
        HttpSession sessao = httpServletRequest.getSession(false);
        
        //USU�RIO LOGADO
        Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
        
        //String codigoImovel = leituraConsumoActionForm.getIdImovelSubstituirConsumo();
        
        Collection colecaoImovelMicromedicao = (Collection) sessao
                .getAttribute("colecaoConsumoHistorico");
        
        Iterator iteratorImovelMicromedicao = colecaoImovelMicromedicao.iterator();
        String idImovel = "";
        
        while (iteratorImovelMicromedicao.hasNext()) {
        	
        	ImovelMicromedicao imovelMicromedicao = (ImovelMicromedicao) iteratorImovelMicromedicao.next();
        	if(sessao.getAttribute("idImovelSelecionado") == null){
        		idImovel = imovelMicromedicao.getImovel().getId().toString();
        	}else{
        		idImovel = ((Integer)sessao.getAttribute("idImovelSelecionado")).toString();
        	}
        	if(imovelMicromedicao.getConsumoHistorico() != null){
        		ConsumoHistorico consumoHistorico = imovelMicromedicao.getConsumoHistorico();
        		String consumoFaturadoMesAgua = (String) httpServletRequest.getParameter("agua" + imovelMicromedicao.getConsumoHistorico().getId().toString());
        		if(!consumoFaturadoMesAgua.trim().equalsIgnoreCase(consumoHistorico.getNumeroConsumoCalculoMedia().toString())){
        			consumoHistorico.setNumeroConsumoCalculoMedia(new Integer(consumoFaturadoMesAgua));
        			fachada.atualizarConsumosAnteriores(consumoHistorico, usuarioLogado);
        		}
        	}
        	if(imovelMicromedicao.getConsumoHistoricoEsgoto() != null){
        		ConsumoHistorico consumoHistoricoEsgoto = imovelMicromedicao.getConsumoHistoricoEsgoto();
        		
        		String consumoFaturadoMesEsgoto = (String) httpServletRequest.getParameter("esgoto" + imovelMicromedicao.getConsumoHistoricoEsgoto().getId().toString());
        		if(!consumoFaturadoMesEsgoto.trim().equalsIgnoreCase(consumoHistoricoEsgoto.getNumeroConsumoCalculoMedia().toString())){
        			consumoHistoricoEsgoto.setNumeroConsumoCalculoMedia(new Integer(consumoFaturadoMesEsgoto));
        			fachada.atualizarConsumosAnteriores(consumoHistoricoEsgoto, usuarioLogado);
        		}
        	}
        	
    	
        	Imovel imovel = new Imovel();
        	imovel.setId(new Integer(idImovel));
        	MedicaoTipo medicaoTipo = new MedicaoTipo();
        	medicaoTipo.setId(MedicaoTipo.LIGACAO_AGUA);
			int[] consumoMedioHidrometroAgua = fachada.obterVolumeMedioAguaEsgoto(imovel.getId(), imovelMicromedicao.getAnoMesGrupoFaturamento(), medicaoTipo.getId());
			medicaoTipo.setId(MedicaoTipo.POCO);
			int[] consumoMedioHidrometroEsgoto = fachada.obterVolumeMedioAguaEsgoto(imovel.getId(), imovelMicromedicao.getAnoMesGrupoFaturamento(), medicaoTipo.getId());
			
			fachada.atualizarConsumosMedio(new Integer(idImovel) ,imovelMicromedicao.getAnoMesGrupoFaturamento(), 
					 consumoMedioHidrometroAgua[0], consumoMedioHidrometroEsgoto[0]);
			
        }
        
        httpServletRequest.setAttribute("sucesso", "Consumos substitu�dos com sucesso.");
        
        if(sessao.getAttribute("peloMenu") != null){
        	retorno = actionMapping.findForward("telaSucesso");
        		
        	montarPaginaSucesso(httpServletRequest, "Consumos anteriores substitu�dos do im�vel " + idImovel + " com sucesso.",
                        "Realizar outra Substitui��o de consumos anteriores",
                        "exibirSubstituirConsumoAnteriorAction.do?menu=sim&peloMenu=true");
        }
        
        return retorno;
    }

}
