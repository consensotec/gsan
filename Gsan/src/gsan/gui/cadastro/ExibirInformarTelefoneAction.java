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
package gsan.gui.cadastro;

import gsan.cadastro.cliente.FiltroFoneTipo;
import gsan.cadastro.cliente.FoneTipo;
import gsan.cadastro.geografico.FiltroMunicipio;
import gsan.cadastro.geografico.Municipio;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * [UC1052] Informar Telefone
 *  
 * @author 	Daniel Alves 
 * @date  	05/08/2010
 */
public class ExibirInformarTelefoneAction extends GcomAction {
	
	
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


		ActionForward retorno = actionMapping
				.findForward("informarTelefone");
		
		HttpSession sessao = getSessao(httpServletRequest);
		
		InformarTelefoneActionForm informarTelefoneActionForm = 
			(InformarTelefoneActionForm) actionForm;
		
		String idCliente = (String) httpServletRequest.getParameter("idCliente");
		String idImovel = (String) httpServletRequest.getParameter("idImovel");
		String limparForm = (String) httpServletRequest.getParameter("limparForm");
		
		if(idCliente != null && !idCliente.equals("")){
			informarTelefoneActionForm.setIdCliente(idCliente);
		}
		
		if(idImovel != null && !idImovel.equals("")){
			informarTelefoneActionForm.setIdImovel(idImovel);
		}
		
		if(limparForm != null && limparForm.equals("S")){
			informarTelefoneActionForm.setTipoTelefone("");
			informarTelefoneActionForm.setTelefonePrincipal("");
			informarTelefoneActionForm.setMunicipio("");
			informarTelefoneActionForm.setMunicipioId("");
			informarTelefoneActionForm.setNomeContato("");
			informarTelefoneActionForm.setNumeroTelefone("");
			informarTelefoneActionForm.setRamal("");
			informarTelefoneActionForm.setDdd("");
		}
		
		
		// Colecao de Tipos Telefone
		Collection<FoneTipo> colecaoTiposTelefone = null;
		
		colecaoTiposTelefone = this.pesquisarColecaoTiposTelefone();
		
		if(colecaoTiposTelefone!= null && colecaoTiposTelefone.size() !=0){
			httpServletRequest.setAttribute("tiposTelefone", colecaoTiposTelefone);
		}
		
		
		//se pesquisar o municipio pelo popup, para selecionar o ddd.
		String tipoConsulta = (String)httpServletRequest.getParameter("tipoConsulta");
		if(tipoConsulta != null && !tipoConsulta.equals("") && tipoConsulta.equals("municipio")){
			
			informarTelefoneActionForm.setMunicipioId((String)httpServletRequest.getParameter("idCampoEnviarDados"));
			informarTelefoneActionForm.setMunicipio((String)httpServletRequest.getParameter("descricaoCampoEnviarDados"));
			
			if(informarTelefoneActionForm.getMunicipioId() != null && 
					!informarTelefoneActionForm.getMunicipioId().equals("")){
				
				Municipio municipio = pesquisarMunicipio(
						Integer.valueOf(informarTelefoneActionForm.getMunicipioId()));
				
				if(municipio != null){
					informarTelefoneActionForm.setDdd( municipio.getDdd().toString() );
				}
			}			
			
		}
		
		
		// Flag indicando que o usu�rio fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");

		// Pesquisar com enter
		if (objetoConsulta != null && !objetoConsulta.trim().equals("")){
			// Faz a consulta de Municipio
			if(objetoConsulta.trim().equals("1")){
				this.pesquisarMunicipio(informarTelefoneActionForm);
				
			}else if(objetoConsulta.trim().equals("2")){
				this.pesquisarDDD(informarTelefoneActionForm);
			}
			
		}
		
		
		//Seta os request�s encontrados
		this.setaRequest(httpServletRequest,informarTelefoneActionForm);
		
		if(httpServletRequest.getParameter("nomeDigitado")!=null 
    			&& httpServletRequest.getParameter("cpfCnpj")!=null
    			&& httpServletRequest.getParameter("clienteImovel")!=null){
    		
			sessao.setAttribute("nomeDigitado", httpServletRequest.getParameter("nomeDigitado"));
			sessao.setAttribute("cpfCnpj", httpServletRequest.getParameter("cpfCnpj"));
			sessao.setAttribute("clienteImovel", httpServletRequest.getParameter("clienteImovel"));
    		
    	}
				
				
		return retorno;

	}



	/**
	 * Pesquisa Colecao Tipos de Telefone 
	 * @author Daniel Alves 
	 */

	private Collection pesquisarColecaoTiposTelefone() {

		FiltroFoneTipo filtroFoneTipo = new FiltroFoneTipo();
		
		Collection colecaoTiposTelefone = this.getFachada().pesquisar(filtroFoneTipo, FoneTipo.class.getName());

		if (colecaoTiposTelefone != null && !colecaoTiposTelefone.isEmpty()) {
			
			return (Collection) colecaoTiposTelefone;

		} else {
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Tipo de Telefone");
		}

	}
	
	
	/**
	 * Pesquisa Municipio
	 * 
	 * @author Daniel Alves
	 * @date 03/08/2010
	 */
	private Municipio pesquisarMunicipio(int municipioId) {

		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
		filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, municipioId));		

		Collection colecaoMunicipio = this.getFachada().pesquisar(filtroMunicipio, Municipio.class.getName());

		if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {
			
			return (Municipio) Util.retonarObjetoDeColecao( colecaoMunicipio);

		} else {
			throw new ActionServletException("atencao.naocadastrado", null,"Municipio");
		}

	}
	
	/**
	 * Pesquisa Municipio
	 *
	 * @author Daniel Alves
	 * @date 21/07/2010
	 */
	private void pesquisarMunicipio(InformarTelefoneActionForm form) {

		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
		filtroMunicipio.adicionarParametro(
				new ParametroSimples(FiltroMunicipio.ID, 
				form.getMunicipioId()));
		
		// Recupera Municipio
		Collection colecaoMunicipio = 
			this.getFachada().pesquisar(filtroMunicipio, Municipio.class.getName());
	
		if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {

			Municipio municipio = 
				(Municipio) Util.retonarObjetoDeColecao(colecaoMunicipio);
			
			form.setMunicipioId(municipio.getId().toString());
			form.setMunicipio(municipio.getNome());
			form.setDdd( municipio.getDdd().toString() );
			
		} else {
			form.setMunicipioId(null);
			form.setMunicipio("Municipio inexistente");
		}
	}	
	
	
	/**
	 * Pesquisa Municipio
	 *
	 * @author Daniel Alves
	 * @date 21/07/2010
	 */
	private void pesquisarDDD(InformarTelefoneActionForm form) {
		

		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
		filtroMunicipio.adicionarParametro(
				new ParametroSimples(FiltroMunicipio.DDD, 
				form.getDdd()));
		
		// Recupera Municipio
		Collection colecaoMunicipio = 
			this.getFachada().pesquisar(filtroMunicipio, Municipio.class.getName());
	
		if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {

			Municipio municipio = 
				(Municipio) Util.retonarObjetoDeColecao(colecaoMunicipio);
			
			form.setMunicipioId(municipio.getId().toString());
			form.setMunicipio(municipio.getNome());
			form.setDdd( municipio.getDdd().toString() );
			
		} else {
			form.setMunicipioId(null);
			form.setMunicipio("Municipio inexistente");
		}
	}
	
	/**
	 * Seta os request com os id encontrados 
	 *
	 * @author Rafael Pinto
	 * @date 23/11/2007
	 */
	private void setaRequest(HttpServletRequest httpServletRequest,
			InformarTelefoneActionForm form){
		
		//Municipio
		if(form.getMunicipioId() != null && 
			!form.getMunicipioId().equals("") && 
			form.getMunicipio() != null && 
			!form.getMunicipio().equals("")){
					
			httpServletRequest.setAttribute("municipioEncontrado","true");
		}else{
			httpServletRequest.setAttribute("municipioEncontrado","false");
		}
		
	}


}
