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
package gcom.gui.cadastro.imovel;

import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.FiltroCep;
import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
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

public class ExibirFiltrarImovelOutrosCriteriosLocalizarImoveis extends
		GcomAction {

	private Collection colecaoPesquisa = null;

	//private Iterator iterator = null;

	private String localidadeID = null;

	private String setorComercialCD = null;

	private String setorComercialID = null;

	private String quadraNM = null;
	
	private String municipioId = null;
	
	private String cepId = null;
	
	private String bairroId = null;
	
	private String logradouroId = null;

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("filtrarImovelOutrosCriterios");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();

		ImovelOutrosCriteriosActionForm imovelOutrosCriteriosActionForm = (ImovelOutrosCriteriosActionForm) actionForm;
		
		//Consulta de municipio para enter - Fl�vio Cordeiro
		municipioId = imovelOutrosCriteriosActionForm.getIdMunicipio();
		
		if(municipioId != null && !municipioId.trim().equalsIgnoreCase("")){
			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
			filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, municipioId));
			
			Collection municipios = fachada.pesquisar(filtroMunicipio, Municipio.class.getName());
			
			if(!municipios.isEmpty()){
				Municipio municipio = (Municipio)municipios.iterator().next();
				
				imovelOutrosCriteriosActionForm.setIdMunicipio(municipioId);
				imovelOutrosCriteriosActionForm.setNomeMunicipio(municipio.getNome());
				httpServletRequest.setAttribute("nomeCampo","idBairro");
			}else{
				imovelOutrosCriteriosActionForm.setIdMunicipio(null);
				imovelOutrosCriteriosActionForm.setNomeMunicipio("Munic�pio inexistente");
				httpServletRequest.setAttribute("nomeCampo","idMunicipio");
			}
		}	
		
		//
		//Consulta de municipio para enter - Fl�vio Cordeiro
		if(municipioId != null && !municipioId.trim().equalsIgnoreCase("")){
			bairroId = imovelOutrosCriteriosActionForm.getIdBairro();
			
			if(bairroId != null && !bairroId.trim().equalsIgnoreCase("")){
				FiltroBairro filtroBairro = new FiltroBairro();
				
				filtroBairro.adicionarCaminhoParaCarregamentoEntidade("municipio");
				
				filtroBairro.adicionarParametro(new ParametroSimples(FiltroBairro.CODIGO, bairroId));
				filtroBairro.adicionarParametro(new ParametroSimples(FiltroBairro.MUNICIPIO_ID, municipioId));
				
				Collection bairros = fachada.pesquisar(filtroBairro, Bairro.class.getName());
				
				if(!bairros.isEmpty()){
					Bairro bairro = (Bairro)bairros.iterator().next();
					
					imovelOutrosCriteriosActionForm.setIdBairro(bairroId);
					imovelOutrosCriteriosActionForm.setNomeBairro(bairro.getNome());
					httpServletRequest.setAttribute("nomeCampo","idMunicipio");
				}else{
					imovelOutrosCriteriosActionForm.setIdBairro(null);
					imovelOutrosCriteriosActionForm.setNomeBairro("Bairro inexistente");
					httpServletRequest.setAttribute("nomeCampo","CEP");
				}
			}	
			//Hugo -- CRC_3383
			FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
			Collection colecaoSistemaParametro = fachada.pesquisar(filtroSistemaParametro, SistemaParametro.class.getName());
			SistemaParametro sistemaParametro = (SistemaParametro) colecaoSistemaParametro.iterator().next();
			
			String nomeEmpresa = sistemaParametro.getNomeAbreviadoEmpresa();
			sessao.setAttribute("nomeEmpresa",nomeEmpresa);
			
			
			logradouroId = imovelOutrosCriteriosActionForm.getIdLogradouro();
			
			if(logradouroId != null && !logradouroId.trim().equalsIgnoreCase("")){
				FiltroLogradouro filtroLogradouro = new FiltroLogradouro();
				
				filtroLogradouro.adicionarCaminhoParaCarregamentoEntidade("municipio");
				filtroLogradouro.adicionarParametro(new ParametroSimples(FiltroLogradouro.ID, logradouroId));
				filtroLogradouro.adicionarParametro(new ParametroSimples(FiltroLogradouro.ID_MUNICIPIO, municipioId));
				
				Collection logradouros = fachada.pesquisar(filtroLogradouro, Logradouro.class.getName());
				
				if(!logradouros.isEmpty()){
					Logradouro logradouro = (Logradouro) logradouros.iterator().next();
					
					imovelOutrosCriteriosActionForm.setIdLogradouro(logradouroId);
					imovelOutrosCriteriosActionForm.setNomeLogradouro(logradouro.getNome());
				}else{
					imovelOutrosCriteriosActionForm.setIdLogradouro("");
					imovelOutrosCriteriosActionForm.setNomeLogradouro("Logradouro inexistente");
					httpServletRequest.setAttribute("nomeCampo","idLogradouro");
					httpServletRequest.setAttribute("Logradouro inexistente",true);
				}
			}
			
		}else{
			//erro depende de municipio
		}
		//

		
		//Consulta de cep para enter - Rafael Santos
		cepId = imovelOutrosCriteriosActionForm.getCEP();
		
		if(cepId != null && !cepId.trim().equalsIgnoreCase("")){
			FiltroCep filtroCep = new FiltroCep();

			filtroCep.adicionarParametro(new ParametroSimples(
					FiltroCep.CODIGO, cepId));
			filtroCep.adicionarParametro(new ParametroSimples(
					FiltroCep.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection cepEncontrado = null;

		       //pesquisa
		       	cepEncontrado = fachada.pesquisar(filtroCep, Cep.class
		                    .getName());
			
			if(!cepEncontrado.isEmpty()){
				Cep cep = (Cep)cepEncontrado.iterator().next();
				
				imovelOutrosCriteriosActionForm.setCEP(cep.getCodigo().toString());
				imovelOutrosCriteriosActionForm.setDescricaoCep(cep.getDescricaoLogradouroFormatada());
				httpServletRequest.setAttribute("nomeCampo","CEP");
			}else{
				imovelOutrosCriteriosActionForm.setCEP(null);
				imovelOutrosCriteriosActionForm.setDescricaoCep("CEP Inexistente");
				httpServletRequest.setAttribute("nomeCampo","idLogradouro");
			}
		}			
		
		
		String objetoConsulta = (String) httpServletRequest
				.getParameter("objetoConsulta");

		String inscricaoTipo = (String) httpServletRequest
				.getParameter("inscricaoTipo");

		if (objetoConsulta != null
				&& !objetoConsulta.trim().equalsIgnoreCase("")
				&& inscricaoTipo != null
				&& !inscricaoTipo.trim().equalsIgnoreCase("")) {

			switch (Integer.parseInt(objetoConsulta)) {
			// Localidade
			case 1:

				pesquisarLocalidade(inscricaoTipo,
						imovelOutrosCriteriosActionForm, fachada,
						httpServletRequest);

				break;
			// Setor Comercial
			case 2:

				pesquisarLocalidade(inscricaoTipo,
						imovelOutrosCriteriosActionForm, fachada,
						httpServletRequest);

				pesquisarSetorComercial(inscricaoTipo,
						imovelOutrosCriteriosActionForm, fachada,
						httpServletRequest);

				break;
			// Quadra
			case 3:

				pesquisarLocalidade(inscricaoTipo,
						imovelOutrosCriteriosActionForm, fachada,
						httpServletRequest);

				pesquisarSetorComercial(inscricaoTipo,
						imovelOutrosCriteriosActionForm, fachada,
						httpServletRequest);

				pesquisarQuadra(inscricaoTipo, imovelOutrosCriteriosActionForm,
						fachada, httpServletRequest);

				break;
			default:
				break;
			}
		} else {
			sessao.removeAttribute("imovelOutrosCriteriosActionForm");
		}
		//

		if(sessao.getAttribute("colecaoGerenciasRegionais") == null){
			// Pesquisar Ger�ncias Regionais
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
					FiltroGerenciaRegional.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			
			filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME_ABREVIADO);
	
			Collection<GerenciaRegional> colecaoGerenciasRegionais = fachada
					.pesquisar(filtroGerenciaRegional, GerenciaRegional.class
							.getName());
	
			sessao.setAttribute("colecaoGerenciasRegionais",
					colecaoGerenciasRegionais);
		}

		if(sessao.getAttribute("colecaoUnidadeNegocio") == null){
			sessao.setAttribute("colecaoUnidadeNegocio", fachada
					.obterColecaoUnidadeNegocio());
		}
		
		
		return retorno;
	}

	private void pesquisarLocalidade(String inscricaoTipo,
			ImovelOutrosCriteriosActionForm imovelOutrosCriteriosActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			imovelOutrosCriteriosActionForm.setInscricaoTipo("origem");
			// Recebe o valor do campo localidadeOrigemID do formul�rio.
			localidadeID = (String) imovelOutrosCriteriosActionForm
					.getLocalidadeOrigemID();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, localidadeID));

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna localidade
			colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Localidade nao encontrada
				// Limpa os campos localidadeOrigemID e nomeLocalidadeOrigem do
				// formul�rio
				imovelOutrosCriteriosActionForm.setLocalidadeOrigemID("");
				imovelOutrosCriteriosActionForm.setNomeLocalidadeOrigem("Localidade Inexistente");
				imovelOutrosCriteriosActionForm.setLocalidadeDestinoID("");
				imovelOutrosCriteriosActionForm.setNomeLocalidadeDestino("");
				httpServletRequest.setAttribute("corLocalidadeOrigem",
						"exception");
				httpServletRequest.setAttribute("nomeCampo","localidadeOrigemID");
			} else {
				Localidade objetoLocalidade = (Localidade) Util
						.retonarObjetoDeColecao(colecaoPesquisa);
				imovelOutrosCriteriosActionForm.setLocalidadeOrigemID(String
						.valueOf(objetoLocalidade.getId()));
				imovelOutrosCriteriosActionForm
						.setNomeLocalidadeOrigem(objetoLocalidade
								.getDescricao());
				httpServletRequest.setAttribute("corLocalidadeOrigem", "valor");
				httpServletRequest.setAttribute("nomeCampo","setorComercialOrigemCD");
				//destino
				imovelOutrosCriteriosActionForm.setLocalidadeDestinoID(String
						.valueOf(objetoLocalidade.getId()));
				imovelOutrosCriteriosActionForm
						.setNomeLocalidadeDestino(objetoLocalidade
								.getDescricao());
				httpServletRequest.setAttribute("corLocalidadeDestino", "valor");
			}
		} else {
			// Recebe o valor do campo localidadeDestinoID do formul�rio.
			localidadeID = (String) imovelOutrosCriteriosActionForm
					.getLocalidadeDestinoID();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, localidadeID));

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna localidade
			colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());
			
			imovelOutrosCriteriosActionForm.setInscricaoTipo("destino");
			
			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Localidade nao encontrada
				// Limpa os campos localidadeDestinoID e nomeLocalidadeDestino
				// do formul�rio
				imovelOutrosCriteriosActionForm.setLocalidadeDestinoID("");
				imovelOutrosCriteriosActionForm
						.setNomeLocalidadeDestino("Localidade inexistente.");
				httpServletRequest.setAttribute("corLocalidadeDestino",
						"exception");
				httpServletRequest.setAttribute("nomeCampo","localidadeDestinoID");
			} else {
				Localidade objetoLocalidade = (Localidade) Util
						.retonarObjetoDeColecao(colecaoPesquisa);
				imovelOutrosCriteriosActionForm.setLocalidadeDestinoID(String
						.valueOf(objetoLocalidade.getId()));
				imovelOutrosCriteriosActionForm
						.setNomeLocalidadeDestino(objetoLocalidade
								.getDescricao());
				httpServletRequest
						.setAttribute("corLocalidadeDestino", "valor");
				httpServletRequest.setAttribute("nomeCampo","setorComercialDestinoCD");
			}
		}

	}

	private void pesquisarSetorComercial(String inscricaoTipo,
			ImovelOutrosCriteriosActionForm imovelOutrosCriteriosActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			imovelOutrosCriteriosActionForm.setInscricaoTipo("origem");
			// Recebe o valor do campo localidadeOrigemID do formul�rio.
			localidadeID = (String) imovelOutrosCriteriosActionForm
					.getLocalidadeOrigemID();

			// O campo localidadeOrigemID ser� obrigat�rio
			if (localidadeID != null
					&& !localidadeID.trim().equalsIgnoreCase("")) {

				setorComercialCD = (String) imovelOutrosCriteriosActionForm
						.getSetorComercialOrigemCD();

				// Adiciona o id da localidade que est� no formul�rio para
				// compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, localidadeID));

				// Adiciona o c�digo do setor comercial que esta no formul�rio
				// para compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						setorComercialCD));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna setorComercial
				colecaoPesquisa = fachada.pesquisar(filtroSetorComercial,
						SetorComercial.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Setor Comercial nao encontrado
					// Limpa os campos setorComercialOrigemCD,
					// nomeSetorComercialOrigem e setorComercialOrigemID do
					// formul�rio
					imovelOutrosCriteriosActionForm
							.setSetorComercialOrigemCD("");
					imovelOutrosCriteriosActionForm
							.setSetorComercialOrigemID("");
					imovelOutrosCriteriosActionForm
							.setNomeSetorComercialOrigem("Setor comercial inexistente.");
					httpServletRequest.setAttribute("corSetorComercialOrigem",
							"exception");
					httpServletRequest.setAttribute("nomeCampo","setorComercialOrigemCD");
					//destino
					imovelOutrosCriteriosActionForm
							.setSetorComercialDestinoCD("");
					imovelOutrosCriteriosActionForm
							.setSetorComercialDestinoID("");
				} else {
					SetorComercial objetoSetorComercial = (SetorComercial) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					//setorComercialOrigem
					imovelOutrosCriteriosActionForm
							.setSetorComercialOrigemCD(String
									.valueOf(objetoSetorComercial.getCodigo()));
					imovelOutrosCriteriosActionForm
							.setSetorComercialOrigemID(String
									.valueOf(objetoSetorComercial.getId()));
					imovelOutrosCriteriosActionForm
							.setNomeSetorComercialOrigem(objetoSetorComercial
									.getDescricao());
					httpServletRequest.setAttribute("nomeCampo","quadraOrigemNM");
					//setorComercialOrigem
					
					//setorComercialDestino
					imovelOutrosCriteriosActionForm
							.setSetorComercialDestinoCD(String
									.valueOf(objetoSetorComercial.getCodigo()));
					imovelOutrosCriteriosActionForm
							.setSetorComercialDestinoID(String
									.valueOf(objetoSetorComercial.getId()));
					imovelOutrosCriteriosActionForm
							.setNomeSetorComercialDestino(objetoSetorComercial
									.getDescricao());
					//setorComercialDestino					
					httpServletRequest.setAttribute("corSetorComercialDestino",
							"valor");
				}
			} else {
				// Limpa o campo setorComercialOrigemCD do formul�rio
				imovelOutrosCriteriosActionForm.setSetorComercialOrigemCD("");
				imovelOutrosCriteriosActionForm
						.setNomeSetorComercialOrigem("Informe a localidade da inscri��o de origem.");
				httpServletRequest.setAttribute("corSetorComercialOrigem",
						"exception");
			}
		} else {
			
			imovelOutrosCriteriosActionForm.setInscricaoTipo("destino");
			
			// Recebe o valor do campo localidadeDestinoID do formul�rio.
			localidadeID = (String) imovelOutrosCriteriosActionForm
					.getLocalidadeDestinoID();

			// O campo localidadeOrigem ser� obrigat�rio
			if (localidadeID != null
					&& !localidadeID.trim().equalsIgnoreCase("")) {

				setorComercialCD = (String) imovelOutrosCriteriosActionForm
						.getSetorComercialDestinoCD();

				// Adiciona o id da localidade que est� no formul�rio para
				// compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, localidadeID));

				// Adiciona o c�digo do setor comercial que esta no formul�rio
				// para compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						setorComercialCD));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna setorComercial
				colecaoPesquisa = fachada.pesquisar(filtroSetorComercial,
						SetorComercial.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Setor Comercial nao encontrado
					// Limpa os campos setorComercialDestinoCD,
					// nomeSetorComercialDestino e setorComercialDestinoID do
					// formul�rio
					imovelOutrosCriteriosActionForm
							.setSetorComercialDestinoCD("");
					imovelOutrosCriteriosActionForm
							.setSetorComercialDestinoID("");
					imovelOutrosCriteriosActionForm
							.setNomeSetorComercialDestino("Setor comercial inexistente.");
					httpServletRequest.setAttribute("corSetorComercialDestino",
							"exception");
					httpServletRequest.setAttribute("nomeCampo","setorComercialDestinoCD");
				} else {
					SetorComercial objetoSetorComercial = (SetorComercial) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					imovelOutrosCriteriosActionForm
							.setSetorComercialDestinoCD(String
									.valueOf(objetoSetorComercial.getCodigo()));
					imovelOutrosCriteriosActionForm
							.setSetorComercialDestinoID(String
									.valueOf(objetoSetorComercial.getId()));
					imovelOutrosCriteriosActionForm
							.setNomeSetorComercialDestino(objetoSetorComercial
									.getDescricao());
					httpServletRequest.setAttribute("corSetorComercialDestino",
							"valor");
					httpServletRequest.setAttribute("nomeCampo","quadraDestinoNM");
				}
			} else {
				// Limpa o campo setorComercialDestinoCD do formul�rio
				imovelOutrosCriteriosActionForm.setSetorComercialDestinoCD("");
				imovelOutrosCriteriosActionForm
						.setNomeSetorComercialDestino("Informe a localidade da inscri��o de destino.");
				httpServletRequest.setAttribute("corSetorComercialDestino",
						"exception");
			}
		}

	}

	private void pesquisarQuadra(String inscricaoTipo,
			
			ImovelOutrosCriteriosActionForm imovelOutrosCriteriosActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		FiltroQuadra filtroQuadra = new FiltroQuadra();

		// Objetos que ser�o retornados pelo hibernate.
		//filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("bairro");

		//QUADRA
		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			imovelOutrosCriteriosActionForm.setInscricaoTipo("origem");
			// Recebe os valores dos campos setorComercialOrigemCD e
			// setorComercialOrigemID do formul�rio.
			setorComercialCD = (String) imovelOutrosCriteriosActionForm
					.getSetorComercialOrigemCD();
			
			setorComercialID = (String) imovelOutrosCriteriosActionForm
					.getSetorComercialOrigemID();

			String idLocalidadeInicial = (String) imovelOutrosCriteriosActionForm
			.getLocalidadeOrigemID();
			
			
			// Os campos setorComercialOrigemCD e setorComercialID ser�o
			// obrigat�rios
			if (setorComercialCD != null
					&& !setorComercialCD.trim().equalsIgnoreCase("")
					&& setorComercialID != null
					&& !setorComercialID.trim().equalsIgnoreCase("")) {

				quadraNM = (String) imovelOutrosCriteriosActionForm
						.getQuadraOrigemNM();

				// coloca parametro no filtro
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_LOCALIDADE, new Integer(
								idLocalidadeInicial)));
				
				// Adiciona o id do setor comercial que est� no formul�rio para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_SETORCOMERCIAL, setorComercialID));

				// Adiciona o n�mero da quadra que esta no formul�rio para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.NUMERO_QUADRA, quadraNM));

				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna quadra
				colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class
						.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Quadra nao encontrada
					// Limpa os campos quadraOrigemNM e quadraOrigemID do
					// formul�rio
					imovelOutrosCriteriosActionForm.setQuadraOrigemNM("");
					imovelOutrosCriteriosActionForm.setQuadraOrigemID("");
					// Mensagem de tela
					httpServletRequest.setAttribute
					("msgQuadraInicial", "QUADRA INEXISTENTE");
					//imovelOutrosCriteriosActionForm
						//	.setQuadraMensagemOrigem("Quadra inexistente.");
					httpServletRequest.setAttribute("corQuadraOrigem",
							"exception");
					httpServletRequest.setAttribute("nomeCampo","quadraOrigemNM");
					
					//destino
					
					imovelOutrosCriteriosActionForm.setQuadraDestinoNM("");
					imovelOutrosCriteriosActionForm.setQuadraDestinoID("");
				} else {
					Quadra objetoQuadra = (Quadra) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					imovelOutrosCriteriosActionForm.setQuadraOrigemNM(String
							.valueOf(objetoQuadra.getNumeroQuadra()));
					imovelOutrosCriteriosActionForm.setQuadraOrigemID(String
							.valueOf(objetoQuadra.getId()));
										
					imovelOutrosCriteriosActionForm.setQuadraDestinoNM(String
							.valueOf(objetoQuadra.getNumeroQuadra()));
					imovelOutrosCriteriosActionForm.setQuadraDestinoID(String
							.valueOf(objetoQuadra.getId()));
			

					httpServletRequest.setAttribute("corQuadraOrigem", null);
					httpServletRequest.setAttribute("nomeCampo","loteOrigem");
				}
			} else {
				// Limpa o campo quadraOrigemNM do formul�rio
				imovelOutrosCriteriosActionForm.setQuadraOrigemNM("");
				imovelOutrosCriteriosActionForm
						.setQuadraMensagemOrigem("Informe o setor comercial da inscri��o de origem.");
				httpServletRequest.setAttribute("corQuadraOrigem", "exception");
			}
		} else {//QUADRA FINAL
			
			imovelOutrosCriteriosActionForm.setInscricaoTipo("destino");
			
			// Recebe os valores dos campos setorComercialOrigemCD e
			// setorComercialOrigemID do formul�rio.
			setorComercialCD = (String) imovelOutrosCriteriosActionForm
					.getSetorComercialDestinoCD();
			setorComercialID = (String) imovelOutrosCriteriosActionForm
					.getSetorComercialDestinoID();

			String idLocalidadeFinal = (String) imovelOutrosCriteriosActionForm
			.getLocalidadeDestinoID();			
			
			// Os campos setorComercialOrigemCD e setorComercialID ser�o
			// obrigat�rios
			if (setorComercialCD != null
					&& !setorComercialCD.trim().equalsIgnoreCase("")
					&& setorComercialID != null
					&& !setorComercialID.trim().equalsIgnoreCase("")) {

				quadraNM = (String) imovelOutrosCriteriosActionForm
						.getQuadraDestinoNM();

				// coloca parametro no filtro
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_LOCALIDADE, new Integer(
								idLocalidadeFinal)));
				
				// Adiciona o id do setor comercial que est� no formul�rio para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_SETORCOMERCIAL, setorComercialID));

				// Adiciona o n�mero da quadra que esta no formul�rio para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.NUMERO_QUADRA, quadraNM));

				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna quadra
				colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class
						.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Quadra nao encontrada
					// Limpa os campos quadraOrigemNM e quadraOrigemID do
					// formul�rio
					imovelOutrosCriteriosActionForm.setQuadraDestinoNM("");
					imovelOutrosCriteriosActionForm.setQuadraDestinoID("");
					// Mensagem de tela
					//imovelOutrosCriteriosActionForm
						//	.setQuadraMensagemDestino("Quadra inexistente.");
					httpServletRequest.setAttribute
					("msgQuadraFinal", "QUADRA INEXISTENTE");					
					httpServletRequest.setAttribute("corQuadraDestino",
							"exception");
					httpServletRequest.setAttribute("nomeCampo","quadraDestinoNM");
				} else {
					Quadra objetoQuadra = (Quadra) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					imovelOutrosCriteriosActionForm.setQuadraDestinoNM(String
							.valueOf(objetoQuadra.getNumeroQuadra()));
					imovelOutrosCriteriosActionForm.setQuadraDestinoID(String
							.valueOf(objetoQuadra.getId()));
					httpServletRequest
							.setAttribute("corQuadraDestino", null);
					httpServletRequest.setAttribute("nomeCampo","loteDestino");
				}
			} else {
				// Limpa o campo setorComercialOrigemCD do formul�rio
				imovelOutrosCriteriosActionForm.setQuadraDestinoNM("");
				// Mensagem de tela
				imovelOutrosCriteriosActionForm
						.setQuadraMensagemDestino("Informe o setor comercial da inscri��o.");
				httpServletRequest
						.setAttribute("corQuadraDestino", "exception");
			}
		}

	}
//
}
