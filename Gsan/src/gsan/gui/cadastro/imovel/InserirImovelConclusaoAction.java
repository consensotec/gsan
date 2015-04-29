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
package gsan.gui.cadastro.imovel;

import gsan.cadastro.cliente.ClienteImovel;
import gsan.cadastro.imovel.bean.ImovelAbaConclusaoHelper;
import gsan.cadastro.localidade.SetorComercial;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.micromedicao.FiltroRota;
import gsan.micromedicao.Rota;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

public class InserirImovelConclusaoAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping
                .findForward("gerenciadorProcesso");

        //Obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        //Instanciando o ActionForm de InserirImovelLocalidadeActionForm
        DynaValidatorForm inserirImovelConclusaoActionForm = (DynaValidatorForm) actionForm;

        //Cria variaveis
        String iptu = (String) inserirImovelConclusaoActionForm.get("numeroIptu");
        String contratoCelpe = (String) inserirImovelConclusaoActionForm.get("numeroContratoCelpe");
		String cordenadasX = (String) inserirImovelConclusaoActionForm.get("cordenadasUtmX");
		String cordenadasY = (String) inserirImovelConclusaoActionForm.get("cordenadasUtmY");
		String idQuadra = (String) inserirImovelConclusaoActionForm.get("idQuadra");
		String idImovelPrincipal = (String) inserirImovelConclusaoActionForm.get("idImovel");
		String sequencialRotaEntrega = (String) inserirImovelConclusaoActionForm.get("sequencialRotaEntrega");
		String numeroQuadraEntrega = (String) inserirImovelConclusaoActionForm.get("numeroQuadraEntrega");

        //Obt�m a inst�ncia da Fachada
        Fachada fachada = Fachada.getInstancia();
        
		String idRotaEntrega = (String) inserirImovelConclusaoActionForm.get("idRota");
		String idRotaAlternativa = (String) inserirImovelConclusaoActionForm.get("idRotaAlternativa");
		String numeroMedidorEnergia = (String) inserirImovelConclusaoActionForm.get("numeroMedidorEnergia");
		Collection clientes = (Collection) sessao.getAttribute("imovelClientesNovos");

		DecimalFormat dff = new DecimalFormat("########0.0000000000000000;-########0.0000000000000000");	
		
		if(cordenadasX != null && !cordenadasX.trim().equals("") ){
			cordenadasX = fachada.validaBigDecimal(cordenadasX, dff,"X");			
		}
				
		if(cordenadasY != null  && !cordenadasY.trim().equals("")){
			cordenadasY = fachada.validaBigDecimal(cordenadasY, dff,"Y");			
		}
		
        
        if(idRotaAlternativa != null && !"".equals(idRotaAlternativa)){
        	FiltroRota filtroRota = new FiltroRota();
        	
        	filtroRota.adicionarParametro(
        		new ParametroSimples(
        			FiltroRota.INDICADOR_ROTA_ALTERNATIVA,
        			ConstantesSistema.SIM));        	
        	
        	filtroRota.adicionarParametro(
        		new ParametroSimples(
        			FiltroRota.ID_ROTA,
        			idRotaAlternativa));
        	
        	Collection  rotasAlternativas = fachada.pesquisar(filtroRota,Rota.class.getName());
        	
        	if(rotasAlternativas != null && !rotasAlternativas.isEmpty()){

        		Rota rotaAlternativa = (Rota) Util.retonarObjetoDeColecao(rotasAlternativas);
            	
            	if(rotaAlternativa.getIndicadorUso().equals(ConstantesSistema.INDICADOR_USO_DESATIVO)){
            		//se passou a rota informada n�o est� ativa
            		throw new ActionServletException("atencao.rota_informada_nao_esta_ativa");
            	}
        	}else{
        		throw new ActionServletException("atencao.rota_informada_nao_e_alternativa");        			
        	}
        }
        
        //Verifica se o campo ENVIO DA CONTA � obrigatorio
        if ( clientes != null && !clientes.isEmpty() ){
        
        	Iterator clientesIterator = clientes.iterator();
            while (clientesIterator.hasNext() ){
            	
            	ClienteImovel clienteImovel = ( ClienteImovel ) clientesIterator.next();
            	
        		if ( clienteImovel.getClienteRelacaoTipo().getId() == 3 &&
        				(inserirImovelConclusaoActionForm.get("imovelContaEnvio") == null ||
            			inserirImovelConclusaoActionForm.get("imovelContaEnvio").equals("-1")) /*&&
            			clienteImovel.getClienteRelacaoTipo().getId() == 3 */ ){
            		
            		throw new ActionServletException("atencao.envio.conta.obrigatorio");
            		
            	}
            	
            }
        	
        }
        
		// Validacao dos dados
		ImovelAbaConclusaoHelper helperConclusao = new ImovelAbaConclusaoHelper();
		helperConclusao.setSetorComercial((SetorComercial) sessao.getAttribute("setorComercial"));
		helperConclusao.setIdQuadraImovel(idQuadra);
		helperConclusao.setIdImovelPrincipal(idImovelPrincipal);
		helperConclusao.setNumeroIptu(iptu);
		helperConclusao.setNumeroContratoCelpe(contratoCelpe);
		helperConclusao.setCoordenadasUtmX(cordenadasX);
		helperConclusao.setCoordenadasUtmY(cordenadasY);
		helperConclusao.setSequencialRotaEntrega(sequencialRotaEntrega);
		helperConclusao.setNumeroQuadraEntrega(numeroQuadraEntrega);
		
		helperConclusao.setIdRotaEntrega(idRotaEntrega);
		helperConclusao.setIdRotaAlternativa(idRotaAlternativa);
		helperConclusao.setImoveisClientes(clientes);
		helperConclusao.setNumeroMedidorEnergia(numeroMedidorEnergia);
		fachada.validarImovelAbaConclusao(helperConclusao);

        return retorno;
    }
}