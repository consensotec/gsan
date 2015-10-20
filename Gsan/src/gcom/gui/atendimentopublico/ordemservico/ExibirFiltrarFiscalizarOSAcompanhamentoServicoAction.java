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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.BairroArea;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroBairroArea;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0450] Pesquisar Ordem Servico - Exibir
 * 
 * @author Leonardo Regis
 *
 * @date 04/09/2006
 */
public class ExibirFiltrarFiscalizarOSAcompanhamentoServicoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirFiltrarFiscalizarOSAcompanhamentoServico");
		
		HttpSession sessao = httpServletRequest.getSession(false);		
		
		// Form
		FiltrarFiscalizarOSAcompanhamentoServicoActionForm filtrarFiscalizarOSAcompanhamentoServicoActionForm = 
			(FiltrarFiscalizarOSAcompanhamentoServicoActionForm) actionForm;
		
		//Colocado por Raphael Rossiter em 29/01/2007 
		String menu = httpServletRequest.getParameter("menu");
		  
		if (menu != null && !menu.equalsIgnoreCase("")){
			//Sugerindo um per�odo para realiza��o da filtragem de uma OS
			//SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
			
			Date dataAtual = new Date();
			Calendar calendario = new GregorianCalendar();
			calendario.setTime(dataAtual);

			// ******************************************************************
			// Solicitado por Leonardo Vieira sem U.C. Executor: Marcio Roberto *
			// Pega quantidade de dias do m�s atual,  antes tinha fixo 30 dias. * 
			Integer qtdDias = new Integer(Util.obterUltimoDiaMes(calendario.get(Calendar.MONTH), calendario.get(Calendar.YEAR)));
			
			Date dataSugestao = Util.subtrairNumeroDiasDeUmaData(dataAtual, qtdDias-1);
			
			filtrarFiscalizarOSAcompanhamentoServicoActionForm.setPeriodoGeracaoInicial(Util.formatarData(dataSugestao));
			filtrarFiscalizarOSAcompanhamentoServicoActionForm.setPeriodoGeracaoFinal(Util.formatarData(dataAtual));
			
		}

		// Flag indicando que o usu�rio fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");

		
		//[UC0013] - Pesquisar Imovel
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
				objetoConsulta.trim().equals("3")) {

			// Faz a consulta de Imovel
			this.pesquisarImovel(filtrarFiscalizarOSAcompanhamentoServicoActionForm);
		}

		//[UC0075] - Pesquisar Municipio
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
				objetoConsulta.trim().equals("8")) {

			// Faz a consulta de Municipio
			this.pesquisarMunicipio(filtrarFiscalizarOSAcompanhamentoServicoActionForm);
		}

		//[UC0141] - Pesquisar Bairro
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
				objetoConsulta.trim().equals("9")) {

			// Faz a consulta de Bairro
			this.pesquisarBairro(filtrarFiscalizarOSAcompanhamentoServicoActionForm,httpServletRequest);
		}

		//[UC0004] - Pesquisar Logradouro
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
				objetoConsulta.trim().equals("10")) {

			// Faz a consulta de logradouro
			this.pesquisarLogradouro(filtrarFiscalizarOSAcompanhamentoServicoActionForm);
		}
		
		if (httpServletRequest.getParameter("tipoConsulta") != null && 
			!httpServletRequest.getParameter("tipoConsulta").equals("")) {
			
			String id = httpServletRequest.getParameter("idCampoEnviarDados");
			String descricao = httpServletRequest.getParameter("descricaoCampoEnviarDados");
			
			if (httpServletRequest.getParameter("tipoConsulta").equals("imovel")) {

				filtrarFiscalizarOSAcompanhamentoServicoActionForm.setMatriculaImovel(id);
				filtrarFiscalizarOSAcompanhamentoServicoActionForm.setInscricaoImovel(descricao);
			
			}else if (httpServletRequest.getParameter("tipoConsulta").equals("municipio")) {

				filtrarFiscalizarOSAcompanhamentoServicoActionForm.setMunicipio(id);
				filtrarFiscalizarOSAcompanhamentoServicoActionForm.setDescricaoMunicipio(descricao);

			}else if (httpServletRequest.getParameter("tipoConsulta").equals("bairro")) {

				filtrarFiscalizarOSAcompanhamentoServicoActionForm.setCodigoBairro(id);
				filtrarFiscalizarOSAcompanhamentoServicoActionForm.setDescricaoBairro(descricao);

			}else if (httpServletRequest.getParameter("tipoConsulta").equals("logradouro")) {

				filtrarFiscalizarOSAcompanhamentoServicoActionForm.setLogradouro(id);
				filtrarFiscalizarOSAcompanhamentoServicoActionForm.setDescricaoLogradouro(descricao);

			}	
		}		
				
		// Perfil Imovel
		this.getPerfilImovelCollection(sessao, this.getFachada());
		
		//Seta os request�s encontrados
		this.setaRequest(httpServletRequest,filtrarFiscalizarOSAcompanhamentoServicoActionForm);
		
		if (httpServletRequest.getParameter("caminhoRetornoTelaPesquisaOrdemServico") != null) {
			
			sessao.setAttribute("caminhoRetornoTelaPesquisaOrdemServico",
				httpServletRequest.getParameter("caminhoRetornoTelaPesquisaOrdemServico"));
			
		}
				
		
		
		
		
		return retorno;
	}
	
	/**
	 * Pesquisa Im�vel 
	 *
	 * @author Rafael Pinto
	 * @date 15/08/2006
	 */
	private void pesquisarImovel(FiltrarFiscalizarOSAcompanhamentoServicoActionForm form) {

		// Filtra Imovel
		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(
				new ParametroSimples(FiltroImovel.ID, 
				form.getMatriculaImovel()));
		
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
		
		// Recupera Im�vel
		Collection<?> colecaoImovel = 
			Fachada.getInstancia().pesquisar(filtroImovel, Imovel.class.getName());
	
		if (colecaoImovel != null && !colecaoImovel.isEmpty()) {

			Imovel imovel = 
				(Imovel) Util.retonarObjetoDeColecao(colecaoImovel);
			
			form.setMatriculaImovel(imovel.getId().toString());
			form.setInscricaoImovel(imovel.getInscricaoFormatada());
			
		} else {
			form.setMatriculaImovel("");
			form.setInscricaoImovel("Matr�cula inexistente");
		}
	}

	
	
	
	/**
	 * Pesquisa Municipio 
	 *
	 * @author Rafael Pinto
	 * @date 16/08/2006
	 */
	private void pesquisarMunicipio(FiltrarFiscalizarOSAcompanhamentoServicoActionForm form) {
		
		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

		filtroMunicipio.adicionarParametro(
			new ParametroSimples(FiltroMunicipio.ID, 
			new Integer(form.getMunicipio())));

		// Pesquisa de acordo com os par�metros informados no filtro
		Collection<?> colecaoMunicipio = Fachada.getInstancia()
				.pesquisar(filtroMunicipio,Municipio.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {

			// Obt�m o objeto da cole��o pesquisada
			Municipio municipio = 
				(Municipio) Util.retonarObjetoDeColecao(colecaoMunicipio);

			form.setMunicipio(municipio.getId().toString());
			form.setDescricaoMunicipio(municipio.getNome());
			

		} else {
			form.setMunicipio("");
			form.setDescricaoMunicipio("Munic�pio inexistente");
		}
	}
	
	/**
	 * Pesquisa Bairro 
	 *
	 * @author Rafael Pinto
	 * @date 16/08/2006
	 */
	private void pesquisarBairro(FiltrarFiscalizarOSAcompanhamentoServicoActionForm form,HttpServletRequest httpServletRequest) {
		
		//[FS0013] - Verificar informa��o do mun�cipio
		String codigoMunicipio = form.getMunicipio();
		if(codigoMunicipio == null || codigoMunicipio.equals("")){
			throw new ActionServletException("atencao.filtrar_informar_municipio");	
		}
		
		FiltroBairro filtroBairro = new FiltroBairro();

		filtroBairro.adicionarParametro(
			new ParametroSimples(FiltroBairro.CODIGO, 
			new Integer(form.getCodigoBairro())));

		filtroBairro.adicionarParametro(
				new ParametroSimples(FiltroBairro.MUNICIPIO_ID,new Integer(codigoMunicipio)));

		// Pesquisa de acordo com os par�metros informados no filtro
		Collection<?> colecaoBairro = Fachada.getInstancia()
				.pesquisar(filtroBairro,Bairro.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoBairro != null && !colecaoBairro.isEmpty()) {

			// Obt�m o objeto da cole��o pesquisada
			Bairro bairro = 
				(Bairro) Util.retonarObjetoDeColecao(colecaoBairro);

			this.montarAreaBairroPorId(httpServletRequest,new Integer(bairro.getId()));			
			
			form.setCodigoBairro(""+bairro.getCodigo());
			form.setIdBairro(""+bairro.getId());
			form.setDescricaoBairro(bairro.getNome());

		} else {
			form.setCodigoBairro(null);
			form.setDescricaoBairro("Bairro inexistente");
		}
	}

	/**
	 * Pesquisa Logradouro 
	 *
	 * @author Rafael Pinto
	 * @date 16/08/2006
	 */
	private void pesquisarLogradouro(FiltrarFiscalizarOSAcompanhamentoServicoActionForm form) {
		
		FiltroLogradouro filtroLogradouro = new FiltroLogradouro();

		filtroLogradouro.adicionarParametro(
			new ParametroSimples(FiltroLogradouro.ID, 
			new Integer(form.getLogradouro())));

		// Pesquisa de acordo com os par�metros informados no filtro
		Collection<?> colecaoLogradouro = Fachada.getInstancia()
				.pesquisar(filtroLogradouro,Logradouro.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoLogradouro != null && !colecaoLogradouro.isEmpty()) {

			// Obt�m o objeto da cole��o pesquisada
			Logradouro logradouro = 
				(Logradouro) Util.retonarObjetoDeColecao(colecaoLogradouro);

			form.setLogradouro(logradouro.getId().toString());
			form.setDescricaoLogradouro(logradouro.getNome());

		} else {
			form.setLogradouro("");
			form.setDescricaoLogradouro("Logradouro inexistente");
		}
	}

	/**
	 * Pesquisa Area do Bairro pelo Id 
	 *
	 * @author Rafael Pinto
	 * @date 16/08/2006
	 */
	private void montarAreaBairroPorId(HttpServletRequest request,Integer id){

		
		// Parte que passa as cole��es necess�rias no jsp
		Collection<?> colecaoAreaBairro = new ArrayList<Object>();
			
		FiltroBairroArea filtroBairroArea = new FiltroBairroArea();
		filtroBairroArea.adicionarParametro(new ParametroSimples(FiltroBairroArea.ID_BAIRRO,id));

		colecaoAreaBairro = 
			Fachada.getInstancia().pesquisar(filtroBairroArea, BairroArea.class.getName());

		if (colecaoAreaBairro != null && !colecaoAreaBairro.isEmpty()) {
			request.setAttribute("colecaoAreaBairro", colecaoAreaBairro);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,"�rea do Bairro");
		}
		
	}

	
	/**
	 * Seta os request com os id encontrados 
	 *
	 * @author Fernanda Almeida
	 * @date 27/10/2011
	 */
	private void setaRequest(HttpServletRequest httpServletRequest,
			FiltrarFiscalizarOSAcompanhamentoServicoActionForm form){
		
		//Imovel
		if(form.getMatriculaImovel() != null && 
			!form.getMatriculaImovel().equals("") && 
			form.getInscricaoImovel() != null && 
			!form.getInscricaoImovel().equals("")){
			
			httpServletRequest.setAttribute("matriculaImovelEncontrada","true");			
		}		
		
		//Municipio
		if(form.getMunicipio() != null && 
			!form.getMunicipio().equals("") && 
			form.getDescricaoMunicipio() != null && 
			!form.getDescricaoMunicipio().equals("")){
								
			httpServletRequest.setAttribute("municipioEncontrada","true");
		}
		
		//Bairro
		if(form.getCodigoBairro() != null && 
			!form.getCodigoBairro().equals("") && 
			form.getDescricaoBairro() != null && 
			!form.getDescricaoBairro().equals("")){
								
			httpServletRequest.setAttribute("bairroEncontrada","true");
		}

		//Logradouro
		if(form.getLogradouro() != null && 
			!form.getLogradouro().equals("") && 
			form.getDescricaoLogradouro() != null && 
			!form.getDescricaoLogradouro().equals("")){
								
			httpServletRequest.setAttribute("logradouroEncontrado","true");
		}
		
		
	}
	
	
	/**
	 * Carrega cole��o de Perfil do Im�vel
	 *
	 * @author Daniel Alves
	 * @date 09/07/2010
	 *
	 * @param sessao
	 * @param fachada
	 */
	private void getPerfilImovelCollection(HttpSession sessao, Fachada fachada) {
		// Filtra Solicita��o Tipo
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);

		Collection<ImovelPerfil> colecaoPerfilImovel= fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());

		if (colecaoPerfilImovel != null && !colecaoPerfilImovel.isEmpty()) {
			sessao.setAttribute("colecaoPerfilImovel",	colecaoPerfilImovel);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null, "Perfil do Imovel");
		}
	}

}