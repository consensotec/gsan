/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio Virginio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.gui.cadastro.atualizacaocadastral;

import gcom.batch.Processo;
import gcom.cadastro.imovel.bean.ImovelGeracaoTabelasTemporariasCadastroHelper;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarGerarTabelasTemporariasPorLocalidadeAction extends GcomAction {

	/**
	 * [UC1261] Gerar tebalas temporárias para atualização cadastral com dispositivo móvel  
	 * @author: Nathalia Santos
	 * @date: 14/12/2011 
	 */
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		GerarTabelasTemporariasPorLocalidadeActionForm form = (GerarTabelasTemporariasPorLocalidadeActionForm) actionForm;
		
		ImovelGeracaoTabelasTemporariasCadastroHelper filtroHelper = 
			this.montarFiltro(form,httpServletRequest);
		
		if(filtroHelper.getMatricula() != null && !filtroHelper.getMatricula().equals("")){
			
			this.getFachada().validarImovelGerarTabelasTemporarias(new Integer(filtroHelper.getMatricula()));
			
			Collection colecaoMatriculas = new ArrayList();
			colecaoMatriculas.add(new Integer(filtroHelper.getMatricula()));
			
			filtroHelper.setColecaoMatriculas(colecaoMatriculas);
			
			this.getFachada().obterImovelClienteProprietarioUsuario(filtroHelper);
			
			// montando página de sucesso
			montarPaginaSucesso(httpServletRequest, 
				"Gerado Tabelas Temporarias Com Sucesso !",
				"Gerar Tabelas Temporarias", "/exibirFiltrarGerarTabelasTemporariasPorLocalidadeAction.do?menu=sim");

			
		}else{
			
			Map parametros = new HashMap();
			parametros.put("imovelGeracaoTabelasTemporariasCadastroHelper",filtroHelper);

			this.getFachada().inserirProcessoIniciadoParametrosLivres(
				parametros, 
			    Processo.GERAR_TABELAS_TEMP_ATU_CADASTRAL ,
			    this.getUsuarioLogado(httpServletRequest));
			
			montarPaginaSucesso(httpServletRequest, "Geração de tabelas encaminhada para Batch.", "", "");
			
		}

			


		return retorno;
	}
	
	private void pesquisarLocalidade(Integer idLocalidade) {

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(
			new ParametroSimples(FiltroLocalidade.ID,idLocalidade));
		
		// Recupera Localidade
		Collection colecaoLocalidade = 
			this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());
	
		if (colecaoLocalidade == null || colecaoLocalidade.isEmpty()) {
			throw new ActionServletException("atencao.localidade.inexistente");
		}
	}
	
	private ImovelGeracaoTabelasTemporariasCadastroHelper montarFiltro(
		GerarTabelasTemporariasPorLocalidadeActionForm form,
		HttpServletRequest httpServletRequest){
		
		ImovelGeracaoTabelasTemporariasCadastroHelper filtroHelper =
				new ImovelGeracaoTabelasTemporariasCadastroHelper();
		
		// Empresa (Firma)
		if (form.getEmpresa() != null && 
			!form.getEmpresa().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
					
			filtroHelper.setFirma(form.getEmpresa());
		}
		
		if (form.getMatriculaImovel() != null && 
				!form.getMatriculaImovel().equals("") ) {
						
			filtroHelper.setMatricula(form.getMatriculaImovel());
		}

		
		filtroHelper.setUsuario(this.getUsuarioLogado(httpServletRequest));
			
		//Localidade Inicial
		if(form.getIdLocalidadeInicial() != null && !form.getIdLocalidadeInicial().equals("")){
			this.pesquisarLocalidade(new Integer(form.getIdLocalidadeInicial()));
			
			filtroHelper.setLocalidadeInicial(form.getIdLocalidadeInicial());
		}
				
		//Localidade Final
		if(form.getIdLocalidadeFinal() != null && !form.getIdLocalidadeFinal().equals("")){
			this.pesquisarLocalidade(new Integer(form.getIdLocalidadeFinal()));
			filtroHelper.setLocalidadeFinal(form.getIdLocalidadeFinal());
		}
		
		//Setor Comercial Inicial  
		if(form.getCodigoSetorComercialInicial() != null && !form.getCodigoSetorComercialInicial().equals("")){
			
			filtroHelper.setCodigoSetorComercialInicial(form.getCodigoSetorComercialInicial());
			
			SetorComercial setorComercial = 
				this.pesquisarSetor(filtroHelper.getLocalidadeInicial(),
					filtroHelper.getCodigoSetorComercialInicial());
				
			filtroHelper.setSetorComercialFinal(setorComercial.getId().toString());

		}
		
		//Setor Comercial Final  
		if(form.getCodigoSetorComercialFinal() != null && !form.getCodigoSetorComercialFinal().equals("")){
			
			filtroHelper.setCodigoSetorComercialFinal(form.getCodigoSetorComercialFinal());
			
			SetorComercial setorComercial = 
				this.pesquisarSetor(filtroHelper.getLocalidadeFinal(),
					filtroHelper.getCodigoSetorComercialFinal());
			
			filtroHelper.setSetorComercialFinal(setorComercial.getId().toString());
			
		}		
		
		if ( httpServletRequest.getParameter("quadra") != null && httpServletRequest.getParameter("quadra").equals("ok") ) {
		
			//Quadra Inicial   
			if(form.getNumeroQuadraInicial() != null && !form.getNumeroQuadraInicial().trim().equals("")){
				
				pesquisarQuadra(form.getIdLocalidadeInicial(), form.getCodigoSetorComercialInicial(), form.getNumeroQuadraInicial());
				filtroHelper.setQuadraInicial(form.getNumeroQuadraInicial());
				
				
				//Rota Inicial   
				if(form.getNumeroQuadraInicial().equals(form.getNumeroQuadraFinal()) && form.getRotaInicial() == null && form.getRotaInicial().equals("")){
	
					String local = form.getIdLocalidadeInicial();
					String setor = form.getCodigoSetorComercialInicial();
					String quadra = form.getNumeroQuadraInicial();
					
					FiltroQuadra filtroQuadra = new FiltroQuadra();
					filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, new Integer(local)));
					filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, new Integer(setor)));
					filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, new Integer(quadra)));
				    filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADORUSO,ConstantesSistema.INDICADOR_USO_ATIVO));
				    filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("rota");
						
					Collection<Quadra> quadras = Fachada.getInstancia().pesquisar(filtroQuadra, Quadra.class.getName());
					
					if (quadras != null && !quadras.isEmpty()) {
				
						Quadra quadraPesquisada = (Quadra) Util.retonarObjetoDeColecao(quadras);
						form.setRotaInicial(quadraPesquisada.getRota().getCodigo().toString());
					}
				}
				
			} else {
				//Rota Inicial   
				if(form.getRotaInicial() != null && !form.getRotaInicial().trim().equals("")){
					
					FiltroRota filtroRota = new FiltroRota();
					filtroRota.adicionarParametro( new ParametroSimples(FiltroRota.LOCALIDADE_ID, form.getIdLocalidadeInicial()));
					filtroRota.adicionarParametro( new ParametroSimples(FiltroRota.SETOR_COMERCIAL_CODIGO, form.getCodigoSetorComercialInicial()));
					filtroRota.adicionarParametro( new ParametroSimples(FiltroRota.CODIGO_ROTA, form.getRotaInicial()));
					Collection<Rota> colecaoRota = Fachada.getInstancia().pesquisar(filtroRota, Rota.class.getName());
					
					if ( colecaoRota == null || colecaoRota.isEmpty() ) {
						throw new ActionServletException("atencao.pesquisa.rota_inexistente");
					}
				}	
			}
		} else {
			//Rota Inicial   
			if(form.getRotaInicial() != null && !form.getRotaInicial().trim().equals("")){
				
				FiltroRota filtroRota = new FiltroRota();
				filtroRota.adicionarParametro( new ParametroSimples(FiltroRota.LOCALIDADE_ID, form.getIdLocalidadeInicial()));
				filtroRota.adicionarParametro( new ParametroSimples(FiltroRota.SETOR_COMERCIAL_CODIGO, form.getCodigoSetorComercialInicial()));
				filtroRota.adicionarParametro( new ParametroSimples(FiltroRota.CODIGO_ROTA, form.getRotaInicial()));
				Collection<Rota> colecaoRota = Fachada.getInstancia().pesquisar(filtroRota, Rota.class.getName());
				
				if ( colecaoRota == null || colecaoRota.isEmpty() ) {
					throw new ActionServletException("atencao.pesquisa.rota_inexistente");
				}
			}	
		}
		
		if ( httpServletRequest.getParameter("quadra") != null && httpServletRequest.getParameter("quadra").equals("ok") ) {
			//Quadra Final   
			if(form.getNumeroQuadraFinal() != null && !form.getNumeroQuadraFinal().trim().equals("")){
				pesquisarQuadra(form.getIdLocalidadeFinal(), form.getCodigoSetorComercialFinal(), form.getNumeroQuadraFinal());
				filtroHelper.setQuadraFinal(form.getNumeroQuadraFinal());
				
				
				//Rota final
				if(form.getNumeroQuadraInicial().equals(form.getNumeroQuadraFinal()) && form.getRotaFinal() == null && form.getRotaFinal().equals("")){
	
					String local = form.getIdLocalidadeFinal();
					String setor = form.getCodigoSetorComercialFinal();
					String quadra = form.getNumeroQuadraFinal();
					
					FiltroQuadra filtroQuadra = new FiltroQuadra();
					filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, new Integer(local)));
					filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, new Integer(setor)));
					filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, new Integer(quadra)));
				    filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADORUSO,ConstantesSistema.INDICADOR_USO_ATIVO));
				    filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("rota");
						
					Collection<Quadra> quadras = Fachada.getInstancia().pesquisar(filtroQuadra, Quadra.class.getName());
					
					if (quadras != null && !quadras.isEmpty()) {
				
						Quadra quadraPesquisada = (Quadra) Util.retonarObjetoDeColecao(quadras);
						form.setRotaFinal(quadraPesquisada.getRota().getCodigo().toString());
					}
				}
				
			} 
			else {
				//Rota Final   
				if(form.getRotaFinal() != null && !form.getRotaFinal().trim().equals("")){
					
					FiltroRota filtroRota = new FiltroRota();
					filtroRota.adicionarParametro( new ParametroSimples(FiltroRota.LOCALIDADE_ID, form.getIdLocalidadeFinal()));
					filtroRota.adicionarParametro( new ParametroSimples(FiltroRota.SETOR_COMERCIAL_CODIGO, form.getCodigoSetorComercialFinal()));
					filtroRota.adicionarParametro( new ParametroSimples(FiltroRota.CODIGO_ROTA, form.getRotaFinal()));
					
					Collection<Rota> colecaoRota = Fachada.getInstancia().pesquisar(filtroRota, Rota.class.getName());
					
					if ( colecaoRota == null || colecaoRota.isEmpty() ) {
						throw new ActionServletException("atencao.pesquisa.rota_inexistente");
					}
				}	
			}
		} else {
			//Rota Final
			if(form.getRotaFinal() != null && !form.getRotaFinal().trim().equals("")){
				
				FiltroRota filtroRota = new FiltroRota();
				filtroRota.adicionarParametro( new ParametroSimples(FiltroRota.LOCALIDADE_ID, form.getIdLocalidadeFinal()));
				filtroRota.adicionarParametro( new ParametroSimples(FiltroRota.SETOR_COMERCIAL_CODIGO, form.getCodigoSetorComercialFinal()));
				filtroRota.adicionarParametro( new ParametroSimples(FiltroRota.CODIGO_ROTA, form.getRotaFinal()));
				
				Collection<Rota> colecaoRota = Fachada.getInstancia().pesquisar(filtroRota, Rota.class.getName());
				
				if ( colecaoRota == null || colecaoRota.isEmpty() ) {
					throw new ActionServletException("atencao.pesquisa.rota_inexistente");
				}
			}	
		}
		
		//Rota Inicial   
		if(form.getRotaInicial() != null && !form.getRotaInicial().trim().equals("")){
			
			filtroHelper.setRotaInicial(new Short(form.getRotaInicial()));
		}		
		
		//Rota Final   
		if(form.getRotaFinal() != null && !form.getRotaFinal().trim().equals("")){
			filtroHelper.setRotaFinal(new Short(form.getRotaFinal()));
		}
		return filtroHelper;
	}
	
	private SetorComercial pesquisarSetor(String idLocalidade,String codigoSetor){
		
		SetorComercial setorComercial = null;
		
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(
			new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,codigoSetor));
		
		filtroSetorComercial.adicionarParametro(
			new ParametroSimples(FiltroSetorComercial.LOCALIDADE,idLocalidade));
		
		// Recupera Setor Comercial
		Collection colecaoSetorComercial = 
			this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());

		if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {

			setorComercial = 
				(SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);
		}else{
			throw new ActionServletException("atencao.setor_comercial.inexistente");
		}
		
		return setorComercial;
	}
	
	/**
	 * Metodo responsavel por validar quadra
	 * @param idLocalidade
	 * @param codigoSetor
	 * @param numeroQuadra
	 * @return
	 */
	private Quadra pesquisarQuadra(String idLocalidade , String codigoSetor, String numeroQuadra) {
		
		
		FiltroQuadra filtroQuadra = new FiltroQuadra();
		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, new Integer(idLocalidade)));
		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, new Integer(codigoSetor)));
		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, new Integer(numeroQuadra)));
        filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADORUSO,ConstantesSistema.INDICADOR_USO_ATIVO));
			
		Collection<Quadra> quadras = Fachada.getInstancia().pesquisar(filtroQuadra, Quadra.class.getName());
		Quadra quadraPesquisada = null;
		if (quadras != null && !quadras.isEmpty()) {

			 quadraPesquisada = (Quadra) Util.retonarObjetoDeColecao(quadras);
		} else {
			throw new ActionServletException("atencao.quadra.inexistente");
		}
		return quadraPesquisada;

	}

}
