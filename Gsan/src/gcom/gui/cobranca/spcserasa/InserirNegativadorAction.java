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
* Thiago Vieira
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
package gcom.gui.cobranca.spcserasa;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.Negativador;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.spcserasa.FiltroNegativador;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Description of the Class
 * 
 * @author Thiago Vieira
 */
public class InserirNegativadorAction extends GcomAction {

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
  	
    	//localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		Fachada fachada = Fachada.getInstancia();

		// Pega o form do cliente
        InserirNegativadorActionForm form = (InserirNegativadorActionForm) actionForm; 

        short codigoAgente = Short.parseShort(form.getCodigoAgente());
        Integer clienteId = new Integer(form.getCodigoCliente());
        
        Integer imovelId = null;
        Imovel imovel = new Imovel();
        
        if(!"".equals(form.getCodigoImovel())){
        	imovelId = new Integer(form.getCodigoImovel());    		
    		imovel.setId(imovelId);
    		
        }
       
       	String inscricaoEstadual = form.getInscricaoEstadual();
       	
		Cliente cliente = new Cliente(); 
		cliente.setId(clienteId);

		
		
		//......................................................................
		FiltroNegativador filtroNegativador = new FiltroNegativador();

		filtroNegativador.adicionarParametro(new ParametroSimples(FiltroNegativador.NEGATIVADOR_CLIENTE, clienteId));		
		
		Collection collNegativador= fachada.pesquisar(filtroNegativador,Negativador.class.getName());		
		
		if(collNegativador != null && !collNegativador.isEmpty()){		
			throw new ActionServletException("atencao.negativador_associado_cliente");
			
		}		
		
		//......................................................................
		if(imovelId != null){
			
			filtroNegativador = new FiltroNegativador();

			filtroNegativador.adicionarParametro(new ParametroSimples(FiltroNegativador.NEGATIVADOR_IMOVEL, imovelId));
			
			Collection collNegativadorImovel= fachada.pesquisar(filtroNegativador,Negativador.class.getName());		
			
			if(collNegativadorImovel != null && !collNegativadorImovel.isEmpty()){		
				throw new ActionServletException("atencao.negativador_associado_imovel");
				
			}
			
			
		}
				
		//......................................................................
		
		filtroNegativador = new FiltroNegativador();

		filtroNegativador.adicionarParametro(new ParametroSimples(FiltroNegativador.CODIGO_AGENTE, codigoAgente));
		
		Collection collNegativadorAgente =  fachada.pesquisar(filtroNegativador,Negativador.class.getName());		
		
		if(collNegativadorAgente != null && !collNegativadorAgente.isEmpty()){		
			throw new ActionServletException("atencao.negativador_associado_agente");
			
		}		
		//......................................................................
				
		//Criando objeto Negativador a ser incluido
		Negativador negativador = new Negativador();
		negativador.setCliente(cliente);
		negativador.setCodigoAgente(codigoAgente);

		if(imovelId != null){
			negativador.setImovel(imovel);
		}
		
		
		negativador.setNumeroInscricaoEstadual(inscricaoEstadual);
		negativador.setUltimaAlteracao(new Date());
		negativador.setIndicadorUso(new Integer(1).shortValue());
		
	// Insere o Negativador
		
		Integer codigoNegativador = -1;
		if (negativador != null) {			
			
			codigoNegativador = (Integer) Fachada.getInstancia().inserir(negativador);
		} else {
			throw new ActionServletException(
					"atencao.informar.linha.criterio.cobranca");
		}
		
		
		Operacao operacao = new Operacao();


		operacao.setId(Operacao.OPERACAO_INSERIR_NEGATIVADOR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		// ------------ REGISTRAR TRANSA��O ----------------

        montarPaginaSucesso(httpServletRequest, "Negativador "
				+ codigoNegativador + " inserido com sucesso.",
				"Inserir outro Negativador",
				"exibirInserirNegativadorAction.do?menu=sim",
				"","");
        
		return retorno;
        
    }
}