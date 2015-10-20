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
package gcom.gui.operacional.abastecimento;

import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.BairroArea;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroBairroArea;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0414] - Informar Programa��o de Abastecimento e Manuten��o
 * 
 * [SB0008] - Copiar Programa��o de Abastecimento
 *
 * @author Rafael Pinto
 * 
 * @date 30/11/2006
 */

public class ExibirCopiarProgramacaoAbastecimentoManutencaoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("copiarProgramacaoAbastecimentoManutencao");

		InformarProgramacaoAbastecimentoManutencaoActionForm form = 
			(InformarProgramacaoAbastecimentoManutencaoActionForm) actionForm;
		
		String tipoOperacao = httpServletRequest.getParameter("tipoOperacao");
		
		if(tipoOperacao != null && !tipoOperacao.equals("") && tipoOperacao.equals("C")){
			
			String mesAnoReferencia = form.getMesAnoReferencia();
			String idMunicipio = form.getMunicipioCopiar();
			String idBairro = form.getBairroCopiar();
			String areaBairro = form.getAreaBairroCopiar();
			
			Collection colecaoProgramacaoAbastecimento = 
				this.getFachada().consultarProgramacaoAbastecimento(idMunicipio, 
					idBairro,areaBairro, mesAnoReferencia);
			
			if(colecaoProgramacaoAbastecimento == null || colecaoProgramacaoAbastecimento.isEmpty()){
				
				this.pesquisarAreaBairro(new Integer(areaBairro),form);
				
				String[] msg = new String[2];
				
				msg[0] = form.getNomeAreaBairroCopiar();
				msg[1] = form.getMesAnoReferencia();
				
				throw new ActionServletException("atencao.ja_existe_programacao_abastecimento_na_area", 
					null,msg);				
			}
			
			form.setAbastecimentoProgramacao(colecaoProgramacaoAbastecimento);
			httpServletRequest.setAttribute("fechaPopup", "true");
			
		}else{
			
			// Flag indicando que o usu�rio fez uma consulta a partir da tecla Enter
			String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");
			String tipoConsulta = httpServletRequest.getParameter("tipoConsulta");

			//[UC0075] - Pesquisar Municipio
			if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
				objetoConsulta.trim().equals("1")) {

				// Faz a consulta de Municipio
				this.pesquisarMunicipio(form);

			//[UC0141] - Pesquisar Bairro
			}else if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
					objetoConsulta.trim().equals("2")) {

				// Faz a consulta de Documento Cobran�a
				this.pesquisarBairro(form,httpServletRequest);
				
			}else if (tipoConsulta != null && !tipoConsulta.equals("")) {
					
				String idCampoEnviarDados = httpServletRequest.getParameter("idCampoEnviarDados");
				String descricaoCampoEnviarDados = httpServletRequest.getParameter("descricaoCampoEnviarDados");
				
				if (tipoConsulta.equals("municipio")) {
							
					form.setMunicipioCopiar(idCampoEnviarDados);
					form.setNomeMunicipioCopiar(descricaoCampoEnviarDados);

					form.setBairroCopiar(null);
					form.setNomeBairroCopiar(null);
					
					form.setAreaBairroCopiar(null);
					form.setNomeAreaBairroCopiar(null);
					

				}else if (tipoConsulta.equals("bairro")) {
							
					form.setBairroCopiar(idCampoEnviarDados);
					form.setNomeBairroCopiar(descricaoCampoEnviarDados);
					
					this.montarAreaBairroPorId(httpServletRequest,new Integer(idCampoEnviarDados));
				}		
			
			}else{
				this.resetPopup(form);
			}
			
			this.setaRequest(httpServletRequest,form);
		}
		
		return retorno;
	}
	
	/**
	 * Reseta informa��es vindas do popup 
	 *
	 * @author Rafael Pinto
	 * @date 14/11/2006
	 *
	 * @param InformarProgramacaoAbastecimentoManutencaoActionForm
	 */
	private void resetPopup(InformarProgramacaoAbastecimentoManutencaoActionForm form) {

		form.setMunicipioCopiar(null);
		form.setNomeMunicipioCopiar(null);
		
		form.setBairroCopiar(null);
		form.setNomeBairroCopiar(null);
		
		form.setAreaBairroCopiar(null);
		form.setNomeAreaBairroCopiar(null);
	}	
	
	/**
	 * [UC0075] - Pesquisar Municipio
	 * 
	 * [FS0001] - Verificar exist�ncia do munic�pio
	 *
	 * @author Rafael Pinto
	 * @date 13/11/2006
	 */
	private void pesquisarMunicipio(InformarProgramacaoAbastecimentoManutencaoActionForm form) {
		
		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

		filtroMunicipio.adicionarParametro(
			new ParametroSimples(FiltroMunicipio.ID,new Integer(form.getMunicipioCopiar())));

		filtroMunicipio.adicionarParametro(
			new ParametroSimples(FiltroMunicipio.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));

		// Pesquisa de acordo com os par�metros informados no filtro
		Collection colecaoMunicipio = 
			this.getFachada().pesquisar(filtroMunicipio,Municipio.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {

			// Obt�m o objeto da cole��o pesquisada
			Municipio municipio = 
				(Municipio) Util.retonarObjetoDeColecao(colecaoMunicipio);

			form.setMunicipioCopiar(municipio.getId().toString());
			form.setNomeMunicipioCopiar(municipio.getNome());
			

		} else {
			form.setMunicipioCopiar(null);
			form.setNomeMunicipioCopiar("Munic�pio inexistente");
		}
	}
	
	/**
	 * [UC0075] - Pesquisar Bairro
	 * 
	 * [FS0002] - Verificar informa��o do municipio
	 * [FS0003] - Verificar exist�ncia do bairro
	 *
	 * @author Rafael Pinto
	 * @date 13/11/2006
	 */
	private void pesquisarBairro(InformarProgramacaoAbastecimentoManutencaoActionForm form,
		HttpServletRequest httpServletRequest) {
		
		//[FS0002] - Verificar informa��o do municipio
		String codigoMunicipio = form.getMunicipioCopiar();
		if(codigoMunicipio == null || codigoMunicipio.equals("")){
			throw new ActionServletException("atencao.filtrar_informar_municipio");	
		}
		
		FiltroBairro filtroBairro = new FiltroBairro();

		filtroBairro.adicionarParametro(
			new ParametroSimples(FiltroBairro.CODIGO,new Integer(form.getBairroCopiar())));

		filtroBairro.adicionarParametro(
				new ParametroSimples(FiltroBairro.MUNICIPIO_ID,new Integer(codigoMunicipio)));

		filtroBairro.adicionarParametro(
			new ParametroSimples(FiltroBairro.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
		

		// Pesquisa de acordo com os par�metros informados no filtro
		Collection colecaoBairro = 
			this.getFachada().pesquisar(filtroBairro,Bairro.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoBairro != null && !colecaoBairro.isEmpty()) {

			// Obt�m o objeto da cole��o pesquisada
			Bairro bairro = 
				(Bairro) Util.retonarObjetoDeColecao(colecaoBairro);

			this.montarAreaBairroPorId(httpServletRequest,new Integer(bairro.getId()));			
			
			form.setBairroCopiar(""+bairro.getCodigo());
			form.setNomeBairroCopiar(bairro.getNome());

		} else {
			form.setBairroCopiar(null);
			form.setNomeBairroCopiar("Bairro inexistente");
		}
	}	
	
	/**
	 * Pesquisa Area do Bairro pelo Id 
	 *
	 * @author Rafael Pinto
	 * @date 13/11/2006
	 */
	private void montarAreaBairroPorId(HttpServletRequest request,Integer id){
		
		// Parte que passa as cole��es necess�rias no jsp
		Collection colecaoAreaBairro = new ArrayList();
			
		FiltroBairroArea filtroBairroArea = new FiltroBairroArea();
		filtroBairroArea.adicionarParametro(new ParametroSimples(FiltroBairroArea.ID_BAIRRO,id));

		colecaoAreaBairro = 
			this.getFachada().pesquisar(filtroBairroArea, BairroArea.class.getName());

		if (colecaoAreaBairro != null && !colecaoAreaBairro.isEmpty()) {
			request.setAttribute("colecaoAreaBairroCopiar", colecaoAreaBairro);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,"�rea do Bairro");
		}
		
	}
	
	/**
	 * Seta os request com os id encontrados 
	 *
	 * @author Rafael Pinto
	 * @date 01/12/2006
	 */
	private void setaRequest(HttpServletRequest httpServletRequest,
			InformarProgramacaoAbastecimentoManutencaoActionForm form){
		
		//Municipio
		if(form.getMunicipioCopiar() != null && 
			!form.getMunicipioCopiar().equals("") && 
			form.getNomeMunicipioCopiar() != null && 
			!form.getNomeMunicipioCopiar().equals("")){
			
			httpServletRequest.setAttribute("municipioEncontrado","true");			
		}

		//Bairro
		if(form.getBairroCopiar() != null && !form.getBairroCopiar().equals("") && 
			form.getNomeBairroCopiar() != null && !form.getNomeBairroCopiar().equals("")){
					
			httpServletRequest.setAttribute("bairroEncontrado","true");
		}	
	}	
	
	
	/**
	 * Pesquisa Area do Bairro pelo Id 
	 *
	 * @author Rafael Pinto
	 * @date 13/11/2006
	 */
	private void pesquisarAreaBairro(Integer idArea,InformarProgramacaoAbastecimentoManutencaoActionForm form){
		
		// Parte que passa as cole��es necess�rias no jsp
		Collection colecaoAreaBairro = new ArrayList();
			
		FiltroBairroArea filtroBairroArea = new FiltroBairroArea();
		filtroBairroArea.adicionarParametro(new ParametroSimples(FiltroBairroArea.ID,idArea));

		colecaoAreaBairro = 
			this.getFachada().pesquisar(filtroBairroArea, BairroArea.class.getName());

		if (colecaoAreaBairro != null && !colecaoAreaBairro.isEmpty()) {
			
			BairroArea bairroArea =
				(BairroArea) Util.retonarObjetoDeColecao(colecaoAreaBairro);
			
			form.setNomeAreaBairroCopiar(bairroArea.getNome());
			
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,"�rea do Bairro");
		}
		
	}
}