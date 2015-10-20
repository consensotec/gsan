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
package gcom.gui.gerencial.cadastro;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.consumotarifa.FiltroConsumoTarifa;
import gcom.gui.GcomAction;
import gcom.micromedicao.consumo.ConsumoAnormalidade;
import gcom.micromedicao.consumo.FiltroConsumoAnormalidade;
import gcom.micromedicao.hidrometro.FiltroHidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidade;
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
 * Descri��o da classe
 * 
 * @author Pedro Alexandre
 * @date 21/05/2007
 */
public class ExibirGerarRelatorioClientesEspeciaisAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		HttpSession sessao = httpServletRequest.getSession(false);

		GerarRelatorioClientesEspeciaisActionForm gerarRelatorioClientesEspeciaisActionForm = (GerarRelatorioClientesEspeciaisActionForm) actionForm;

		if (httpServletRequest.getParameter("menu") != null) {

			carregarColecoes(sessao);

			gerarRelatorioClientesEspeciaisActionForm.setConsumoAnormalidade("");
			gerarRelatorioClientesEspeciaisActionForm.setLeituraAnormalidade("");

		}

		consultarLocalidadeInicial(gerarRelatorioClientesEspeciaisActionForm,httpServletRequest);

		consultarLocalidadeFinal(gerarRelatorioClientesEspeciaisActionForm,httpServletRequest);
		
		consultarSetorComercialInicial(gerarRelatorioClientesEspeciaisActionForm, httpServletRequest);

		consultarSetorComercialFinal(gerarRelatorioClientesEspeciaisActionForm, httpServletRequest);
		
		consultarClienteResponsavel(gerarRelatorioClientesEspeciaisActionForm, httpServletRequest);

		consultarLeituraAnormalidade(gerarRelatorioClientesEspeciaisActionForm,httpServletRequest);

		return actionMapping.findForward("exibirGerarRelatorioClientesEspeciais");
	}

	/**
	 * Esse m�todo consulta a leitura anormalidade, caso o usu�rio a tenha informado.
	 * Se por acaso ela n�o existir, � enviada a mensagem informando isso.
	 *
	 *@since 02/10/2009
	 *@author Marlon Patrick
	 */
	private void consultarLeituraAnormalidade(GerarRelatorioClientesEspeciaisActionForm gerarRelatorioClientesEspeciaisActionForm,
			HttpServletRequest httpServletRequest) {

		Fachada fachada = Fachada.getInstancia();

		String idLeituraAnormalidade = gerarRelatorioClientesEspeciaisActionForm.getIdLeituraAnormalidade();

		if ( Util.verificarNaoVazio(idLeituraAnormalidade)) {

			FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();
			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.ID, idLeituraAnormalidade));

			Collection<LeituraAnormalidade> colecaoLeiturasAnormalidades = fachada.pesquisar(
					filtroLeituraAnormalidade, LeituraAnormalidade.class.getName());

			if ( Util.isVazioOrNulo(colecaoLeiturasAnormalidades)) {
				gerarRelatorioClientesEspeciaisActionForm.setIdLeituraAnormalidade("");
				gerarRelatorioClientesEspeciaisActionForm.setDescricaoLeituraAnormalidade("ANORM. DE LEITURA INEXISTENTE");
				httpServletRequest.setAttribute("anormalidadeLeituraInexistente", true);

			} else {				
				LeituraAnormalidade leituraAnormalidade = colecaoLeiturasAnormalidades.iterator().next();

				gerarRelatorioClientesEspeciaisActionForm
					.setDescricaoLeituraAnormalidade(leituraAnormalidade.getDescricao());
			}

		} else {
			gerarRelatorioClientesEspeciaisActionForm.setDescricaoLeituraAnormalidade("");
		}
	}

	/**
	 * Esse m�todo consulta cliente responsavel, caso o usu�rio o tenha informado.
	 * Se por acaso ele n�o existir, � enviada a mensagem informando isso.
	 *
	 *@since 02/10/2009
	 *@author Marlon Patrick
	 */
	private void consultarClienteResponsavel(GerarRelatorioClientesEspeciaisActionForm gerarRelatorioClientesEspeciaisActionForm,
			HttpServletRequest httpServletRequest) {

		Fachada fachada = Fachada.getInstancia();

		String idClienteResponsavel = gerarRelatorioClientesEspeciaisActionForm.getIdClienteResponsavel();

		if ( Util.verificarNaoVazio(idClienteResponsavel)) {

			Cliente clienteResponsavel = fachada
					.pesquisarClienteDigitado(new Integer(idClienteResponsavel));

			if (clienteResponsavel == null) {
				gerarRelatorioClientesEspeciaisActionForm.setIdClienteResponsavel("");
				gerarRelatorioClientesEspeciaisActionForm.setNomeClienteResponsavel("CLIENTE INEXISTENTE");
				httpServletRequest.setAttribute("clienteResponsavelInexistente", true);

			} else {
				gerarRelatorioClientesEspeciaisActionForm.setNomeClienteResponsavel(clienteResponsavel.getNome());
			}

		} else {
			gerarRelatorioClientesEspeciaisActionForm.setNomeClienteResponsavel("");
		}
	}

	/**
	 * Esse m�todo consulta a localidade final, caso o usu�rio a tenha informado.
	 * Se por acaso ela n�o existir, � enviada a mensagem informando isso.
	 *
	 *@since 02/10/2009
	 *@author Marlon Patrick
	 */
	private void consultarLocalidadeFinal(GerarRelatorioClientesEspeciaisActionForm gerarRelatorioClientesEspeciaisActionForm,
			HttpServletRequest httpServletRequest) {

		Fachada fachada = Fachada.getInstancia();

		String idLocalidadeFinalForm = gerarRelatorioClientesEspeciaisActionForm.getIdLocalidadeFinal();

		if ( Util.verificarNaoVazio(idLocalidadeFinalForm)) {

			Integer idLocalidadeFinal = new Integer(idLocalidadeFinalForm);

			Localidade localidadeDestino = fachada
					.pesquisarLocalidadeDigitada(new Integer(idLocalidadeFinal));

			if (localidadeDestino == null) {
				gerarRelatorioClientesEspeciaisActionForm.setNomeLocalidadeFinal("LOCALIDADE INEXISTENTE");
				httpServletRequest.setAttribute("localidadeFinalInexistente",true);

			} else {
				gerarRelatorioClientesEspeciaisActionForm
					.setNomeLocalidadeFinal(localidadeDestino.getDescricao());
			}

		} else {
			gerarRelatorioClientesEspeciaisActionForm.setNomeLocalidadeFinal("");
		}
	}

	/**
	 * Esse m�todo consulta a localidade inicial, caso o usu�rio a tenha informado.
	 * Se por acaso ela n�o existir, � enviada a mensagem informando isso.
	 *
	 *@since 02/10/2009
	 *@author Marlon Patrick
	 */
	private void consultarLocalidadeInicial(GerarRelatorioClientesEspeciaisActionForm gerarRelatorioClientesEspeciaisActionForm,
			HttpServletRequest httpServletRequest) {

		Fachada fachada = Fachada.getInstancia();

		String idLocalidadeInicialForm = gerarRelatorioClientesEspeciaisActionForm.getIdLocalidadeInicial();

		if ( Util.verificarNaoVazio(idLocalidadeInicialForm)) {

			Integer idLocalidadeInicial = new Integer(idLocalidadeInicialForm);

			Localidade localidadeOrigem = 
				fachada.pesquisarLocalidadeDigitada(new Integer(idLocalidadeInicial));

			if (localidadeOrigem == null) {
				gerarRelatorioClientesEspeciaisActionForm.setNomeLocalidadeInicial("LOCALIDADE INEXISTENTE");
				httpServletRequest.setAttribute("localidadeInicialInexistente",true);
			} else {
				gerarRelatorioClientesEspeciaisActionForm
					.setNomeLocalidadeInicial(localidadeOrigem.getDescricao());
			}

		} else {
			gerarRelatorioClientesEspeciaisActionForm.setNomeLocalidadeInicial("");
		}
	}

	/**
	 * Esse m�todo consulta o setor inicial, caso o usu�rio o tenha informado.
	 * Se por acaso ele n�o existir, � enviada a mensagem informando isso.
	 *
	 *@since 02/10/2009
	 *@author Marlon Patrick
	 */
	private void consultarSetorComercialInicial(GerarRelatorioClientesEspeciaisActionForm gerarRelatorioClientesEspeciaisActionForm,
			HttpServletRequest httpServletRequest) {

		if ( Util.verificarNaoVazio(gerarRelatorioClientesEspeciaisActionForm.getCodigoSetorComercialInicial())) {

			Integer codigoSetorInicial = new Integer(gerarRelatorioClientesEspeciaisActionForm.getCodigoSetorComercialInicial());

			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(
				new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,codigoSetorInicial));
			filtroSetorComercial.adicionarParametro(
				new ParametroSimples(FiltroSetorComercial.LOCALIDADE_ID,new Integer(gerarRelatorioClientesEspeciaisActionForm.getIdLocalidadeInicial())));
			
			Collection<SetorComercial> colecaoSetorComercial = 
				this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());

			if ( Util.isVazioOrNulo(colecaoSetorComercial)) {
				gerarRelatorioClientesEspeciaisActionForm.setNomeSetorComercialInicial("SETOR COMERCIAL INEXISTENTE");
				httpServletRequest.setAttribute("setorComercialInicialInexistente",true);

			} else {
				SetorComercial setor = colecaoSetorComercial.iterator().next();
					gerarRelatorioClientesEspeciaisActionForm
						.setNomeSetorComercialInicial(setor.getDescricao());
			}

		} else {
			gerarRelatorioClientesEspeciaisActionForm.setNomeSetorComercialInicial("");
		}
	}

	/**
	 * Esse m�todo consulta o setor final, caso o usu�rio o tenha informado.
	 * Se por acaso ele n�o existir, � enviada a mensagem informando isso.
	 *
	 *@since 02/10/2009
	 *@author Marlon Patrick
	 */
	private void consultarSetorComercialFinal(GerarRelatorioClientesEspeciaisActionForm gerarRelatorioClientesEspeciaisActionForm,
			HttpServletRequest httpServletRequest) {

		if ( Util.verificarNaoVazio(gerarRelatorioClientesEspeciaisActionForm.getCodigoSetorComercialFinal())) {

			Integer codigoSetorFinal = new Integer(gerarRelatorioClientesEspeciaisActionForm.getCodigoSetorComercialFinal());

			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(
				new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,codigoSetorFinal));
			filtroSetorComercial.adicionarParametro(
				new ParametroSimples(FiltroSetorComercial.LOCALIDADE_ID,new Integer(gerarRelatorioClientesEspeciaisActionForm.getIdLocalidadeFinal())));
			
			Collection<SetorComercial> colecaoSetorComercial = 
				this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());

			if ( Util.isVazioOrNulo(colecaoSetorComercial)) {
				gerarRelatorioClientesEspeciaisActionForm.setNomeSetorComercialFinal("SETOR COMERCIAL INEXISTENTE");
				httpServletRequest.setAttribute("setorComercialFinalInexistente",true);

			} else {
				SetorComercial setor = colecaoSetorComercial.iterator().next();
					gerarRelatorioClientesEspeciaisActionForm
						.setNomeSetorComercialFinal(setor.getDescricao());
			}

		} else {
			gerarRelatorioClientesEspeciaisActionForm.setNomeSetorComercialFinal("");
		}
	}

	/**
	 * Esse m�todo carrega todas as cole��es a serem exibidas na tela
	 * na sess�o.
	 *
	 *@since 02/10/2009
	 *@author Marlon Patrick
	 */
	private void carregarColecoes(HttpSession sessao) {
		
		Fachada fachada = Fachada.getInstancia();

		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio(FiltroUnidadeNegocio.NOME);
		Collection<UnidadeNegocio> colecaoUnidadeNegocio = fachada.pesquisar(
				filtroUnidadeNegocio, UnidadeNegocio.class.getName());

		FiltroImovelPerfil filtroPerfilImovel = new FiltroImovelPerfil(FiltroImovelPerfil.DESCRICAO);
		Collection<ImovelPerfil> colecaoPerfilImovel = fachada.pesquisar(
				filtroPerfilImovel, ImovelPerfil.class.getName());

		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional(
				FiltroGerenciaRegional.NOME);
		Collection<GerenciaRegional> colecaoGerenciaRegional = fachada.pesquisar(
				filtroGerenciaRegional, GerenciaRegional.class.getName());

		FiltroCategoria filtroCategoria = new FiltroCategoria(
				FiltroCategoria.DESCRICAO);
		Collection<Categoria> colecaoCategoria = fachada.pesquisar(filtroCategoria,
				Categoria.class.getName());

		FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria(
				FiltroSubCategoria.DESCRICAO);
		Collection<Subcategoria> colecaoSubCategoria = fachada.pesquisar(
				filtroSubCategoria, Subcategoria.class.getName());

		FiltroLigacaoAguaSituacao filtroSituacaoLigacaoAgua = new FiltroLigacaoAguaSituacao(
				FiltroLigacaoAguaSituacao.DESCRICAO);
		Collection<LigacaoAguaSituacao> colecaoSituacaoLigacaoAgua = fachada.pesquisar(
				filtroSituacaoLigacaoAgua, LigacaoAguaSituacao.class
						.getName());

		FiltroLigacaoEsgotoSituacao filtroSituacaoLigacaoEsgoto = new FiltroLigacaoEsgotoSituacao(
				FiltroLigacaoEsgotoSituacao.DESCRICAO);
		Collection<LigacaoEsgotoSituacao> colecaoSituacaoLigacaoEsgoto = fachada.pesquisar(
				filtroSituacaoLigacaoEsgoto, LigacaoEsgotoSituacao.class
						.getName());

		FiltroHidrometroCapacidade filtroCapacidadeHidrometro = new FiltroHidrometroCapacidade(
				FiltroHidrometroCapacidade.NUMERO_ORDEM);
		Collection<HidrometroCapacidade> colecaoCapacidadeHidrometro = fachada.pesquisar(
				filtroCapacidadeHidrometro, HidrometroCapacidade.class
						.getName());

		FiltroConsumoTarifa filtroTarifaConsumo = new FiltroConsumoTarifa(
				FiltroConsumoTarifa.DESCRICAO);
		Collection<ConsumoTarifa> colecaoTarifaConsumo = fachada.pesquisar(
				filtroTarifaConsumo, ConsumoTarifa.class.getName());

		FiltroConsumoAnormalidade filtroConsumoAnormalidade = new FiltroConsumoAnormalidade(
				FiltroConsumoAnormalidade.DESCRICAO);
		Collection<ConsumoAnormalidade> colecaoConsumosAnormalidades = fachada.pesquisar(
				filtroConsumoAnormalidade, ConsumoAnormalidade.class
						.getName());

		sessao.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
		sessao.setAttribute("colecaoPerfilImovel", colecaoPerfilImovel);
		sessao.setAttribute("colecaoGerenciaRegional",colecaoGerenciaRegional);
		sessao.setAttribute("colecaoCategoria", colecaoCategoria);
		sessao.setAttribute("colecaoSubCategoria", colecaoSubCategoria);
		sessao.setAttribute("colecaoSituacaoLigacaoAgua",colecaoSituacaoLigacaoAgua);
		sessao.setAttribute("colecaoSituacaoLigacaoEsgoto",colecaoSituacaoLigacaoEsgoto);
		sessao.setAttribute("colecaoCapacidadeHidrometro",colecaoCapacidadeHidrometro);
		sessao.setAttribute("colecaoTarifaConsumo", colecaoTarifaConsumo);
		sessao.setAttribute("colecaoConsumosAnormalidades",colecaoConsumosAnormalidades);
	}
}