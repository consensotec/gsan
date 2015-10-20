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
 */
package gcom.gui.cadastro;

import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.CepTipo;
import gcom.cadastro.endereco.FiltroCep;
import gcom.cadastro.endereco.FiltroCepTipo;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

	
/**
 * @author Vinicius Medeiros
 * @date 12/02/2009
 */
public class ExibirAtualizarCepAction extends GcomAction {

	/**
	 * M�todo responsavel por responder a requisicao
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
	
	private String municipioId;
	
	private String bairroId;
	
	private Collection colecaoPesquisa;
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta o caminho de retorno
		ActionForward retorno = actionMapping
				.findForward("cepAtualizar");

		AtualizarCepActionForm atualizarCepActionForm =
			(AtualizarCepActionForm) actionForm;

		// Cria uma inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();
		
		String objetoConsulta = (String) httpServletRequest
			.getParameter("objetoConsulta");
		
		// Mudar quando houver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);
		
		String id = httpServletRequest.getParameter("idRegistroAtualizacao");
		
		if (id == null) {
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null) {
				id = (String) sessao.getAttribute("idRegistroAtualizacao");
			} else {
				id = (String) httpServletRequest.getAttribute(
						"idRegistroAtualizacao").toString();
			}
		} else {
			sessao.setAttribute("i", true);
		}
		
		if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("")) {

			// Recebe o valor do campo municipioId do formul�rio.
			municipioId = atualizarCepActionForm.getMunicipioId();
			
			// Recebe o valor do campo bairroId do formul�rio.
			bairroId = atualizarCepActionForm.getBairroId();

			switch (Integer.parseInt(objetoConsulta)) {

				// Municipio
				case 1:
	
					FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
	
					filtroMunicipio.adicionarParametro(
							new ParametroSimples(FiltroMunicipio.ID,
									municipioId));
	
					filtroMunicipio.adicionarParametro(
							new ParametroSimples(FiltroMunicipio.INDICADOR_USO,
									ConstantesSistema.INDICADOR_USO_ATIVO));
	
					// Retorna Municipio
					colecaoPesquisa = fachada.pesquisar(
							filtroMunicipio,Municipio.class.getName());
	
					if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
						// Municipio nao encontrado
						// Limpa o campo MunicipioId do formul�rio
						atualizarCepActionForm.setMunicipioId("");
						atualizarCepActionForm.setMunicipio("Municipio inexistente.");
						httpServletRequest.setAttribute("corMunicipio","exception");
	
						httpServletRequest.setAttribute("nomeCampo","municipioId");
						
					} else {
						
						Municipio objetoMunicipio = 
							(Municipio) Util.retonarObjetoDeColecao(colecaoPesquisa);
						
						atualizarCepActionForm.setMunicipioId(
								String.valueOf(objetoMunicipio.getId()));
						atualizarCepActionForm.setMunicipio(
								objetoMunicipio.getNome());
						
						httpServletRequest.setAttribute("corMunicipio","valor");
						httpServletRequest.setAttribute("nomeCampo","municipioId");
	
					}
	
					break;
					
					// Bairro
					case 2:
	
						FiltroBairro filtroBairro = new FiltroBairro();
		
	        			filtroBairro.adicionarParametro(new ParametroSimples(
	        					FiltroBairro.CODIGO, bairroId));
	        			filtroBairro.adicionarParametro(new ParametroSimples(
	        					FiltroBairro.MUNICIPIO_ID, municipioId));
		
						filtroBairro.adicionarParametro(
								new ParametroSimples(FiltroBairro.INDICADOR_USO,
										ConstantesSistema.INDICADOR_USO_ATIVO));
		
						// Retorna Bairro
						colecaoPesquisa = fachada.pesquisar(
								filtroBairro, Bairro.class.getName());
		
						if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
	
							// Bairro nao encontrado
							// Limpa o campo BairroId do formul�rio
							atualizarCepActionForm.setBairroId("");
							atualizarCepActionForm.setBairro("Bairro inexistente.");
							httpServletRequest.setAttribute("corBairro","exception");
		
							httpServletRequest.setAttribute("nomeCampo","bairroId");
							
						} else {
							
							Bairro objetoBairro = 
								(Bairro) Util.retonarObjetoDeColecao(colecaoPesquisa);
							
							atualizarCepActionForm.setBairroId(
									String.valueOf(objetoBairro.getCodigo()));
							atualizarCepActionForm.setBairro(
									objetoBairro.getNome());
							
							httpServletRequest.setAttribute("corBairro","valor");
							httpServletRequest.setAttribute("nomeCampo","bairroId");
		
						}
	
					break;
	
				}
			}
	
		Cep cep = new Cep();

		if (id != null && !id.trim().equals("") && Integer.parseInt(id) > 0 && objetoConsulta == null) {

			FiltroCep filtroCep = new FiltroCep();
			filtroCep.adicionarParametro(new ParametroSimples(
					FiltroCep.CEPID, id));
			
			filtroCep.adicionarCaminhoParaCarregamentoEntidade("cepTipo");
			
			Collection colecaoCep = fachada.pesquisar(
					filtroCep, Cep.class.getName());
			if (colecaoCep != null
					&& !colecaoCep.isEmpty()) {
				cep = (Cep) Util
						.retonarObjetoDeColecao(colecaoCep);
			}

			if(cep.getMunicipio() != null){
				
				FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
				
				filtroMunicipio.adicionarParametro(new ParametroSimples(
						FiltroMunicipio.NOME,  cep.getMunicipio()));
				
				// Pesquisa a cole��o de Siglas
				Collection colecaoMunicipio = fachada.pesquisar(
						filtroMunicipio, Municipio.class
								.getName());
				
				if(!colecaoMunicipio.isEmpty()){
					Municipio municipio = (Municipio) Util.retonarObjetoDeColecao(colecaoMunicipio);
				
					atualizarCepActionForm.setMunicipio(municipio.getNome());
					atualizarCepActionForm.setMunicipioId(municipio.getId().toString());
				}
				
				if(cep.getBairro() != null){
					
					FiltroBairro filtroBairro = new FiltroBairro();
					
					filtroBairro.adicionarParametro(new ParametroSimples(
							FiltroBairro.NOME,  cep.getBairro()));
					filtroBairro.adicionarParametro(new ParametroSimples(
							FiltroBairro.MUNICIPIO,  cep.getMunicipio()));
					
					// Pesquisa a cole��o de Siglas
					Collection colecaoBairro = fachada.pesquisar(
							filtroBairro, Bairro.class
									.getName());
					
					if(!colecaoBairro.isEmpty()){
						Bairro bairro = (Bairro) Util.retonarObjetoDeColecao(colecaoBairro);
					
						atualizarCepActionForm.setBairro(bairro.getNome());
					
						int bairroCodigo = bairro.getCodigo();
						atualizarCepActionForm.setBairroId(String.valueOf(bairroCodigo));
					}
					
					sessao.setAttribute("colecaoBairro",
							colecaoBairro);
				}
				
				
				
				
				sessao.setAttribute("colecaoMunicipio",
						colecaoMunicipio);
				
			}
			
			
	        // Filtro de Tipo CEP para trazer apenas os que tiverem Indicador Uso = 1
	        
			FiltroCepTipo filtroCepTipo = new FiltroCepTipo();

			filtroCepTipo.adicionarParametro(new ParametroSimples(
					FiltroCepTipo.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroCepTipo
					.setCampoOrderBy(FiltroCepTipo.ID);

			// Pesquisa a cole��o de Tipo de CEP
			Collection colecaoCepTipo = fachada.pesquisar(
					filtroCepTipo, CepTipo.class
							.getName());
	        
			sessao.setAttribute("colecaoCepTipo",
					colecaoCepTipo);
			
			//	Filtro de Tipo Logradouro
	        Collection colecaoLogradouroTipo = fachada.retornaListaLogradouroTipoCep();
	        
	    	sessao.setAttribute("colecaoLogradouroTipo",
	    			colecaoLogradouroTipo);
			
			if (id == null || id.trim().equals("")) {

				atualizarCepActionForm.setCepId(cep
						.getCepId().toString());

				atualizarCepActionForm
						.setCodigo(cep.getCodigo().toString());

				atualizarCepActionForm
						.setCepTipo(cep
								.getCepTipo().toString());
				
				atualizarCepActionForm
						.setMunicipio(cep.getMunicipio());
				
				atualizarCepActionForm
						.setBairro(cep.getBairro());
				
				atualizarCepActionForm
						.setLogradouroTipo(cep
								.getDescricaoTipoLogradouro());
				
				atualizarCepActionForm
						.setLogradouro(cep
								.getLogradouro());
				
				atualizarCepActionForm
						.setIndicadorUso(cep
								.getIndicadorUso().toString());
				

			}

			atualizarCepActionForm.setCepId(id);
			
			atualizarCepActionForm.setCodigo(Util.formatarCEP(cep.getCodigo().toString()));
			
			atualizarCepActionForm.setCepTipo(cep
					.getCepTipo().getId().toString());

			atualizarCepActionForm
					.setMunicipio(cep
							.getMunicipio());

			atualizarCepActionForm
					.setBairro(cep
							.getBairro());
			
			atualizarCepActionForm
					.setLogradouroTipo(cep
							.getDescricaoTipoLogradouro());
			
			atualizarCepActionForm
					.setLogradouro(cep
							.getLogradouro());
			
			atualizarCepActionForm.setIndicadorUso(""
					+ cep.getIndicadorUso());

			sessao.setAttribute("atualizarCep", cep);

			if (sessao.getAttribute("colecaoCep") != null) {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/filtrarCepAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/exibirFiltrarCepAction.do");
			}

		}

		return retorno;
	}
	
	}