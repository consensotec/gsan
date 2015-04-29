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
package gsan.gui.faturamento;

import gsan.batch.Processo;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.faturamento.bean.PrescreverDebitosImovelHelper;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.Util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class PrescreverImoveisPublicosAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        SistemaParametro sistemaParametro = 
			this.getFachada().pesquisarParametrosDoSistema();
        
        PrescreverImoveisPublicosActionForm form = (PrescreverImoveisPublicosActionForm) actionForm;

        Fachada fachada = Fachada.getInstancia();
        
    	Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
       
        PrescreverDebitosImovelHelper helper = new PrescreverDebitosImovelHelper();
        
        String dataInicial = Util.formatarMesAnoParaAnoMesSemBarra(form.getPeriodoInicial());
		String dataFinal = Util.formatarMesAnoParaAnoMesSemBarra(form.getPeriodoFinal());
		
		// Per�odo 
		if(Util.verificarNaoVazio(dataInicial)){
			
			if(!Util.verificarNaoVazio(dataFinal)){
				
				throw new ActionServletException("atencao.filtrar_data_final_obrigatorio_quando_inicial",
						null,"atendimento");
			}else{
				
				if(Util.compararAnoMesReferencia(dataInicial, dataFinal, ">")){
					
					throw new ActionServletException("atencao.gsan.data_final_menor_data_inicial",null, "Prescri��o");
				}
				
				helper.setDataInicio(dataInicial);
				helper.setDataFim(dataFinal);
			}
		}
        
        helper.setUsuarioLogado(usuarioLogado);
        
        //Seta o anoMes de Faturamento.
        helper.setAnoMesReferencia(sistemaParametro.getAnoMesFaturamento().toString());
        
        // Id Cliente
        if(Util.verificarNaoVazio(form.getIdCliente())){
        	helper.setIdCliente(form.getIdCliente());
        }
        
        //Is Imovel
        if(Util.verificarNaoVazio(form.getIdImovel())){
        	helper.setIdImovel(form.getIdImovel());
        }
        
		// Esfera de poder
        String idsEsferaPoder = "";
        if(!Util.verificarNaoVazio(form.getIdImovel()) && !Util.verificarNaoVazio(form.getIdCliente()) 
        		&& !Util.isVazioOrNulo( form.getIdsEsferaPoder())){
        	
        	for (String id : form.getIdsEsferaPoder()) {
        		if (!id.equals("-1")) {
        			idsEsferaPoder += id + ",";
        		}
        	}
        	idsEsferaPoder = Util.removerUltimosCaracteres(idsEsferaPoder, 1);

        }else if(!Util.verificarNaoVazio(form.getIdImovel()) && !Util.verificarNaoVazio(form.getIdCliente())){
        	throw new ActionServletException("atencao.campo_texto.obrigatorio",null, "Esfera do Poder");
        }
        
        helper.setEsferaPoder(idsEsferaPoder);
		
		// Forma Prescricao
		if(Util.verificarNaoVazio(form.getFormaPrescricao())){
			
			helper.setFormaPrescricao(form.getFormaPrescricao());
			
			if(form.getFormaPrescricao().equals("0")){
		        	
				helper.setIdProcesso(Processo.PRESCREVER_DEBITOS_IMOVEIS_PUBLICOS_MANUAL);
		    }else{
		    	helper.setIdProcesso(Processo.PRESCREVER_DEBITOS_IMOVEIS_PUBLICOS_AUTOMATICO);
		    }
		}
        
		Integer tipo = fachada.prescreverDebitosImoveisPublicos(helper);

		
		// Monta a p�gina de sucesso
		// Caso Batch Manual
		if(tipo != null){
			montarPaginaSucesso(httpServletRequest,
	    			"Processo Prescrever D�bitos de Im�veis P�blicos Manual iniciado com sucesso.", 
	    			"Inserir outra Prescri��o de D�bitos de Im�veis P�blicos Manual",
	    			"/exibirPrescreverImoveisPublicosAction.do");
			
		}else{
			// Caso Batch Autom�tico
			
			montarPaginaSucesso(httpServletRequest,
	    			"Configura��o da Prescri��o de D�bitos de Im�veis P�blicos Autom�tica inserida com sucesso.", 
	    			"Inserir outra Prescri��o de D�bitos de Im�veis P�blicos Autom�tica",
	    			"/exibirPrescreverImoveisPublicosAction.do");
		}
		
		sessao.removeAttribute("colecaoEsferaPoder");
        
        return retorno;
	}

}
