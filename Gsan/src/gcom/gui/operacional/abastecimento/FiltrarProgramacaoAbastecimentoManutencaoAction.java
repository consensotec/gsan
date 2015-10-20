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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0414] - Informar Programa��o de Abastecimento e Manuten��o
 * 
 * @author Rafael Pinto
 * @date 14/11/2006
 */
public class FiltrarProgramacaoAbastecimentoManutencaoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = 
			actionMapping.findForward("exibirInformarProgramacaoAbastecimentoManutencaoAction");

		FiltrarProgramacaoAbastecimentoManutencaoActionForm form = 
			(FiltrarProgramacaoAbastecimentoManutencaoActionForm) actionForm;

		String mesAnoReferencia = form.getMesAnoReferencia();
		String idMunicipio = form.getMunicipio();
		this.pesquisarMunicipio(new Integer(idMunicipio),httpServletRequest);
		
		String idBairro = "";
		String areaBairro = "";
		if ( form.getBairro() != null && !form.getBairro().equals("") ) {
			
			idBairro = form.getBairro();
			this.pesquisarBairro(new Integer(idMunicipio),new Integer(idBairro),httpServletRequest);
			
			areaBairro = form.getAreaBairro();
			this.pesquisarAreaBairro(new Integer(areaBairro),httpServletRequest);
		}
		
		
		this.getSessao(httpServletRequest).setAttribute("mesAnoReferencia",mesAnoReferencia);

		Collection colecaoProgramacaoAbastecimento = 
			this.getFachada().consultarProgramacaoAbastecimento(idMunicipio, idBairro,areaBairro, mesAnoReferencia);

		Collection colecaoProgramacaoManutencao = 
			this.getFachada().consultarProgramacaoManutencao(idMunicipio, idBairro,areaBairro, mesAnoReferencia);
		
		this.getSessao(httpServletRequest).setAttribute("colecaoProgramacaoAbastecimento", colecaoProgramacaoAbastecimento);
		this.getSessao(httpServletRequest).setAttribute("colecaoProgramacaoManutencao", colecaoProgramacaoManutencao);
		this.getSessao(httpServletRequest).setAttribute("ultimaAlteracao", new Date());

		return retorno;
	}
	
	/**
	 * [UC0075] - Pesquisar Municipio
	 * 
	 * [FS0001] - Verificar exist�ncia do munic�pio
	 *
	 * @author Rafael Pinto
	 * @date 13/11/2006
	 */
	private void pesquisarMunicipio(Integer idMunicipio,HttpServletRequest httpServletRequest) {
		
		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

		filtroMunicipio.adicionarParametro(
			new ParametroSimples(FiltroMunicipio.ID,idMunicipio));

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

			this.getSessao(httpServletRequest).setAttribute("municipio",municipio);
			
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,"Munic�pio");
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
	private void pesquisarBairro(Integer idMunicipio,Integer idBairro,HttpServletRequest httpServletRequest) {
		
		//[FS0002] - Verificar informa��o do municipio
		if(idMunicipio == null || idMunicipio.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			throw new ActionServletException("atencao.filtrar_informar_municipio");	
		}
		
		FiltroBairro filtroBairro = new FiltroBairro();

		filtroBairro.adicionarParametro(
			new ParametroSimples(FiltroBairro.CODIGO,idBairro));

		filtroBairro.adicionarParametro(
				new ParametroSimples(FiltroBairro.MUNICIPIO_ID,idMunicipio));

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
			
			this.getSessao(httpServletRequest).setAttribute("bairro",bairro);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,"Bairro");
		}
	}	
	
	/**
	 * Pesquisa Area do Bairro pelo Id 
	 *
	 * @author Rafael Pinto
	 * @date 13/11/2006
	 */
	private void pesquisarAreaBairro(Integer idArea,HttpServletRequest httpServletRequest){
		
		// Parte que passa as cole��es necess�rias no jsp
		Collection colecaoAreaBairro = new ArrayList();
			
		FiltroBairroArea filtroBairroArea = new FiltroBairroArea();
		filtroBairroArea.adicionarParametro(new ParametroSimples(FiltroBairroArea.ID,idArea));

		filtroBairroArea.adicionarCaminhoParaCarregamentoEntidade("distritoOperacional");
		filtroBairroArea.adicionarCaminhoParaCarregamentoEntidade("distritoOperacional.zonaAbastecimento");
		filtroBairroArea.adicionarCaminhoParaCarregamentoEntidade("distritoOperacional.setorAbastecimento");
		filtroBairroArea.adicionarCaminhoParaCarregamentoEntidade("distritoOperacional.setorAbastecimento.sistemaAbastecimento");

		colecaoAreaBairro = 
			this.getFachada().pesquisar(filtroBairroArea, BairroArea.class.getName());

		if (colecaoAreaBairro != null && !colecaoAreaBairro.isEmpty()) {
			
			BairroArea bairroArea =
				(BairroArea) Util.retonarObjetoDeColecao(colecaoAreaBairro);
			
			this.getSessao(httpServletRequest).setAttribute("bairroArea",bairroArea);

		} else {
			throw new ActionServletException("atencao.naocadastrado", null,"�rea do Bairro");
		}
		
	}	
}
