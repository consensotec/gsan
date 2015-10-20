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


package gcom.gui.cadastro.entidadebeneficente;


import gcom.cadastro.imovel.FiltroEntidadeBeneficente;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**

 * Prepara o filtro dos dados da entidade beneficente e aciona o caso de uso [UC0916] para fazer a consulta.

 *  

 * @author Hugo Fernando

 * @date 18/01/2010

 * @since 4.1.6.4

 *

 */

public class FiltrarEntidadeBeneficenteAction extends GcomAction{

	
	@Override
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		 ActionForward retorno = actionMapping.findForward("filtrarEntidadeBeneficente");

		 

		 
	  // Mudar isso quando implementar a parte de seguran�a
      HttpSession sessao = httpServletRequest.getSession(false);

      FiltrarEntidadeBeneficenteActionForm form = (FiltrarEntidadeBeneficenteActionForm) actionForm;

	  
	  
      // Recupera os par�metros do form
	  Integer cliente            = form.getCliente().getId();
	  Integer tipoDebito         = form.getDebitoTipo().getId();
	  Integer empresa            = form.getEmpresa().getId();
	  String mesAnoAdesaoInic   = form.getInicioMesAnoAdesao();
	  String mesAnoAdesaoFim    = form.getFimMesAnoAdesao();
	  String indicadorUso       = ""+form.getIndicadorUso();

		
		
		FiltroEntidadeBeneficente filtroEntidadeBeneficente = new FiltroEntidadeBeneficente();
		
		
		
		boolean peloMenosUmParametroInformado = false;
		
		
        // Insere os par�metros informados no filtro
		if( cliente != null && cliente.intValue() > 0 ){
			peloMenosUmParametroInformado = true;
			filtroEntidadeBeneficente.adicionarParametro( 
			new ParametroSimples( FiltroEntidadeBeneficente.ID_CLIENTE , cliente ) );
		}
		if( tipoDebito != null && tipoDebito.intValue() > 0  ){
			peloMenosUmParametroInformado = true;
			filtroEntidadeBeneficente.adicionarParametro( 
			new ParametroSimples( FiltroEntidadeBeneficente.ID_DEBITO_TIPO , tipoDebito ) );
		}
		if( empresa != null && empresa.intValue() > 0 ){
			peloMenosUmParametroInformado = true;
			filtroEntidadeBeneficente.adicionarParametro( 
			new ParametroSimples( FiltroEntidadeBeneficente.ID_EMPRESA , empresa ) );
		}
		if( mesAnoAdesaoInic != null && !mesAnoAdesaoInic.trim().equals("") ){
			peloMenosUmParametroInformado = true;
			
			String anoAdesaoInicio =  mesAnoAdesaoInic.substring(3);
			String mesAdesaoInicio = mesAnoAdesaoInic.substring(0,2);
			mesAnoAdesaoInic = anoAdesaoInicio+""+mesAdesaoInicio;
			
			filtroEntidadeBeneficente.adicionarParametro( 
			new ParametroSimples( FiltroEntidadeBeneficente.CONTRATO_INICIAL , new Integer(mesAnoAdesaoInic) ) );
		}
		if( mesAnoAdesaoFim != null && !mesAnoAdesaoFim.trim().equals("") ){
			peloMenosUmParametroInformado = true;
			
			String anoAdesaoFim =  mesAnoAdesaoFim.substring(3);
			String mesAdesaoFim = mesAnoAdesaoFim.substring(0,2);
			mesAnoAdesaoInic = anoAdesaoFim+""+mesAdesaoFim;
			
			filtroEntidadeBeneficente.adicionarParametro( 
			new ParametroSimples( FiltroEntidadeBeneficente.CONTRATO_FINAL , new Integer(mesAnoAdesaoInic )) );
		}
		if( indicadorUso != null && !indicadorUso.trim().equals("") && indicadorUso.equals("1")){
			peloMenosUmParametroInformado = true;
			filtroEntidadeBeneficente.adicionarParametro( 
			new ParametroSimples( FiltroEntidadeBeneficente.INDICADOR_USO , ConstantesSistema.INDICADOR_USO_ATIVO ) );
		}
		else if( indicadorUso != null && !indicadorUso.trim().equals("") && indicadorUso.equals("2")){
			peloMenosUmParametroInformado = true;
			filtroEntidadeBeneficente.adicionarParametro( 
			new ParametroSimples( FiltroEntidadeBeneficente.INDICADOR_USO , ConstantesSistema.INDICADOR_USO_DESATIVO ) );
		}
		if(indicadorUso.equals("3") && peloMenosUmParametroInformado == false){
			peloMenosUmParametroInformado = true;
			filtroEntidadeBeneficente.setCampoOrderBy(FiltroEntidadeBeneficente.ID);
		}
		
		
		filtroEntidadeBeneficente.setCampoOrderBy(FiltroEntidadeBeneficente.ID);
		
		filtroEntidadeBeneficente.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtroEntidadeBeneficente.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
		filtroEntidadeBeneficente.adicionarCaminhoParaCarregamentoEntidade("empresa");
		
		
		// Erro caso o usu�rio mandou filtrar sem nenhum par�metro
		if ( peloMenosUmParametroInformado == false) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
		
		// Verifica se o checkbox Atualizar est� marcado e em caso afirmativo
		// manda pelo um request uma vari�vel para o
		// ExibirManterEntidadeBeneficenteAction e nele verificar se ir� para o
		// atualizar ou para o manter.		
		if (form.getAtualizar() != null	&& form.getAtualizar()
				.equalsIgnoreCase("1")) {
	    httpServletRequest.setAttribute("atualizar",form.getAtualizar());
	
         }
		
		
		// Manda o filtro pela sess�o para o ExibirManterEntidadeBeneficenteAction
		sessao.setAttribute("filtroEntidadeBeneficente", filtroEntidadeBeneficente);
		
		
		httpServletRequest.setAttribute("filtroEntidadeBeneficente", filtroEntidadeBeneficente);
		

	  	 
        // Devolve o mapeamento de retorno 
		return retorno;

	}	
}
