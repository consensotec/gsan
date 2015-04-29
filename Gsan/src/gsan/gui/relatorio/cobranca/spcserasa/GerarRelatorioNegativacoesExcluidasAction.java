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
package gsan.gui.relatorio.cobranca.spcserasa;

import gsan.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gsan.cadastro.cliente.ClienteTipo;
import gsan.cadastro.cliente.EsferaPoder;
import gsan.cadastro.cliente.FiltroClienteTipo;
import gsan.cadastro.cliente.FiltroEsferaPoder;
import gsan.cadastro.imovel.Categoria;
import gsan.cadastro.imovel.FiltroCategoria;
import gsan.cadastro.imovel.FiltroImovelPerfil;
import gsan.cadastro.imovel.ImovelPerfil;
import gsan.cadastro.localidade.FiltroGerenciaRegional;
import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.FiltroQuadra;
import gsan.cadastro.localidade.FiltroSetorComercial;
import gsan.cadastro.localidade.FiltroUnidadeNegocio;
import gsan.cadastro.localidade.GerenciaRegional;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.Quadra;
import gsan.cadastro.localidade.SetorComercial;
import gsan.cadastro.localidade.UnidadeNegocio;
import gsan.cobranca.CobrancaGrupo;
import gsan.cobranca.FiltroCobrancaGrupo;
import gsan.cobranca.FiltroNegativadorExclusaoMotivo;
import gsan.cobranca.Negativador;
import gsan.cobranca.NegativadorExclusaoMotivo;
import gsan.cobranca.bean.DadosConsultaNegativacaoHelper;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.cobranca.spcserasa.InformarDadosConsultaNegativacaoActionForm;
import gsan.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gsan.relatorio.RelatorioVazioException;
import gsan.relatorio.cobranca.spcserasa.RelatorioNegativacoesExcluidas;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.spcserasa.FiltroNegativador;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.ConstantesSistema;
import gsan.util.SistemaException;
import gsan.util.Util;
import gsan.util.filtro.ConectorOr;
import gsan.util.filtro.ParametroSimples;
import gsan.util.filtro.ParametroSimplesIn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action respons�vel pela exibi��o do relat�rio de Acompanhamanto de Clientes Negativados
 * 
 * @author Yara Taciane
 * @created 18 de mar�o de 2008
 */
public class GerarRelatorioNegativacoesExcluidasAction extends
		ExibidorProcessamentoTarefaRelatorio {

	/**
	 * < <Descri��o do m�todo>>
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
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a vari�vel de retorno
		ActionForward retorno = null;

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		

		InformarDadosConsultaNegativacaoActionForm form = (InformarDadosConsultaNegativacaoActionForm) actionForm;

		// Inicio da parte que vai mandar os parametros para o relat�rio
		DadosConsultaNegativacaoHelper parametros = new DadosConsultaNegativacaoHelper();

		//********************************************************
		// RM3755
		// Autor: Ivan Sergio
		// Data: 12/01/2011
		//********************************************************
		//String idNegativador = (String) form.getIdNegativador();        
        //if (idNegativador == null || idNegativador.equalsIgnoreCase("")){
        //    idNegativador = (String)sessao.getAttribute("idNegativador");
        //}
		//sessao.setAttribute("colecaoNegativador", colecaoNegativador);
		//--------------------------------------------------------------------------------------------------------
		// Negativador
		//--------------------------------------------------------------------------------------------------------
		String[] arrayNegativador = form.getArrayNegativador();		
		Negativador negativadorColecao = new Negativador();
		negativadorColecao.setId(-1);

		Collection colecaoNegativador = new ArrayList();
		int i = 0;		

		Collection colecaoIdNegativador = new ArrayList();
		if (arrayNegativador != null) {
			//negativadorColecao.setCliente("OP��ES SELECIONADAS");
			colecaoNegativador.add(negativadorColecao);
			FiltroNegativador filtroNegativador = new FiltroNegativador();
			
			filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("cliente");
			
			for (i = 0; i < arrayNegativador.length; i++) {
				if (!arrayNegativador[i].equals("")
						&& !arrayNegativador[i].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					colecaoIdNegativador.add(arrayNegativador[i]);
				}
			}

			filtroNegativador.adicionarParametro(new ParametroSimplesIn(
					FiltroNegativador.ID, colecaoIdNegativador));
			
			filtroNegativador.setCampoOrderBy(FiltroNegativador.CLIENTE);

			Collection colecaoNegativadorPesquisa = fachada.pesquisar(
					filtroNegativador, Negativador.class.getName());

			if (colecaoNegativadorPesquisa != null
					&& !colecaoNegativadorPesquisa.isEmpty()) {
				colecaoNegativador.addAll(colecaoNegativadorPesquisa);
			}
		} else {
			//negativadorColecao.setDescricao("TODOS");
			colecaoNegativador.add(negativadorColecao);
		}
		
		//********************************************************
        
        
		Date periodoEnvioNegativacaoInicio = null;
		if (form.getPeriodoEnvioNegativacaoInicio() != null && !form.getPeriodoEnvioNegativacaoInicio().equals("")){
			Date periodoInicio = Util.converteStringParaDate(form.getPeriodoEnvioNegativacaoInicio());			
			periodoEnvioNegativacaoInicio = Util.getSQLDate(periodoInicio);
		}
		if (periodoEnvioNegativacaoInicio == null){
			periodoEnvioNegativacaoInicio = (Date)sessao.getAttribute("periodoEnvioNegativacaoInicio");
	    }
	    sessao.setAttribute("periodoEnvioNegativacaoInicio",periodoEnvioNegativacaoInicio);
	  
				
		Date periodoEnvioNegativacaoFim = null;
		if (form.getPeriodoEnvioNegativacaoFim() != null && !form.getPeriodoEnvioNegativacaoFim().equals("")){
		 Date periodoFim = Util.converteStringParaDate(form.getPeriodoEnvioNegativacaoFim());
		 periodoEnvioNegativacaoFim = Util.getSQLDate(periodoFim);	
		}
		if (periodoEnvioNegativacaoFim == null){
			periodoEnvioNegativacaoFim = (Date)sessao.getAttribute("periodoEnvioNegativacaoFim");
	    }
	    sessao.setAttribute("periodoEnvioNegativacaoFim",periodoEnvioNegativacaoFim);
	    
	    
	    
	    
		Date periodoExclusaoNegativacaoInicio = null;
		if (form.getPeriodoExclusaoNegativacaoInicio() != null && !form.getPeriodoExclusaoNegativacaoInicio().equals("")){
			Date periodoInicio = Util.converteStringParaDate(form.getPeriodoExclusaoNegativacaoInicio());			
			periodoExclusaoNegativacaoInicio = Util.getSQLDate(periodoInicio);
		}
		if (periodoExclusaoNegativacaoInicio == null){
			periodoExclusaoNegativacaoInicio = (Date)sessao.getAttribute("periodoExclusaoNegativacaoInicio");
	    }
	    sessao.setAttribute("periodoExclusaoNegativacaoInicio",periodoExclusaoNegativacaoInicio);
	  
				
		Date periodoExclusaoNegativacaoFim = null;
		if (form.getPeriodoExclusaoNegativacaoFim() != null && !form.getPeriodoExclusaoNegativacaoFim().equals("")){
		 Date periodoFim = Util.converteStringParaDate(form.getPeriodoExclusaoNegativacaoFim());
		 periodoExclusaoNegativacaoFim = Util.getSQLDate(periodoFim);	
		}
		if (periodoExclusaoNegativacaoFim == null){
			periodoExclusaoNegativacaoFim = (Date)sessao.getAttribute("periodoExclusaoNegativacaoFim");
	    }
	    sessao.setAttribute("periodoExclusaoNegativacaoFim",periodoExclusaoNegativacaoFim);
		
		
		String idNegativadorExclusaoMotivo = (String) form.getIdNegativadorExclusaoMotivo();        
        if (idNegativadorExclusaoMotivo == null || idNegativadorExclusaoMotivo.equalsIgnoreCase("")){
        	idNegativadorExclusaoMotivo = (String)sessao.getAttribute("idNegativadorExclusaoMotivo");
        }
        sessao.setAttribute("idNegativadorExclusaoMotivo",idNegativadorExclusaoMotivo);
	    
	    
		String tituloComando = null;
		if (form.getTituloComando() != null && !form.getTituloComando().equals("")){
			tituloComando = form.getTituloComando();
		}
		if (tituloComando == null || tituloComando.equalsIgnoreCase("") ){
			tituloComando = (String)sessao.getAttribute("tituloComando");
	    }
	    sessao.setAttribute("tituloComando",tituloComando);
		
		
		
		Integer idEloPolo = null;
		if(form.getIdEloPolo()!= null && !form.getIdEloPolo().equals("") && !form.getIdEloPolo().equals("-1")){
			idEloPolo = new Integer(form.getIdEloPolo());
		}
		if (idEloPolo == null){
			idEloPolo = (Integer)sessao.getAttribute("idEloPolo");
	    }
	    sessao.setAttribute("idEloPolo",idEloPolo);
		
		
		
		Integer idLocalidade = null;
		if(form.getIdLocalidade()!= null && !form.getIdLocalidade().equals("")&& !form.getIdLocalidade().equals("-1")){
			idLocalidade = new Integer(form.getIdLocalidade());
		}
		if (idLocalidade == null){
			idLocalidade = (Integer)sessao.getAttribute("idLocalidade");
	    }
	    sessao.setAttribute("idLocalidade",idLocalidade);
		
		
		Integer idSetorComercial = null;
		if(form.getIdSetorComercial()!= null &&!form.getIdSetorComercial().equals("") && !form.getIdSetorComercial().equals("-1")){
			idSetorComercial = new Integer(form.getIdSetorComercial());
		}
		if (idSetorComercial == null){
			idSetorComercial = (Integer)sessao.getAttribute("idSetorComercial");
	    }
	    sessao.setAttribute("idSetorComercial",idSetorComercial);
		
		
		Integer idQuadra = null;
		if(form.getIdQuadra()!= null && !form.getIdQuadra().equals("") && !form.getIdQuadra().equals("-1")){
			idQuadra = new Integer(form.getIdQuadra());
		}
		if (idQuadra == null){
			idQuadra = (Integer)sessao.getAttribute("idQuadra");
	    }
	    sessao.setAttribute("idQuadra",idQuadra);
		
		//--------------------------------------------------------------------------------------------------------
		//Grupo Cobran�a
		//--------------------------------------------------------------------------------------------------------
		String[] arrayCobrancaGrupo = form.getArrayCobrancaGrupo();	
		
		if (arrayCobrancaGrupo == null){
			arrayCobrancaGrupo = (String[])sessao.getAttribute("arrayCobrancaGrupo");
	    }
	    sessao.setAttribute("arrayCobrancaGrupo",arrayCobrancaGrupo);
		
		
		CobrancaGrupo cobrancaGrupoColecao = new CobrancaGrupo();
		cobrancaGrupoColecao.setId(-1);

		Collection colecaoCobrancaGrupo = new ArrayList();
		//int i = 0;		
		
		if (arrayCobrancaGrupo != null && arrayCobrancaGrupo.length>0) {
			cobrancaGrupoColecao.setDescricao("OP��ES SELECIONADAS");
			colecaoCobrancaGrupo.add(cobrancaGrupoColecao);
			FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();

			for (i = 0; i < arrayCobrancaGrupo.length; i++) {
				if (!arrayCobrancaGrupo[i].equals("")
						&& !arrayCobrancaGrupo[i].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (i + 1 < arrayCobrancaGrupo.length) {
						filtroCobrancaGrupo.adicionarParametro(new ParametroSimples(
										FiltroCobrancaGrupo.ID, arrayCobrancaGrupo[i],
										ConectorOr.CONECTOR_OR,
										arrayCobrancaGrupo.length));

					} else {
						filtroCobrancaGrupo
								.adicionarParametro(new ParametroSimples(
										FiltroCobrancaGrupo.ID, arrayCobrancaGrupo[i]));
					}
				}
			}

			filtroCobrancaGrupo.setCampoOrderBy(FiltroCobrancaGrupo.DESCRICAO);

			Collection colecaoCobrancaGrupoPesquisa = fachada.pesquisar(
					filtroCobrancaGrupo, CobrancaGrupo.class.getName());

			if (colecaoCobrancaGrupoPesquisa != null
					&& !colecaoCobrancaGrupoPesquisa.isEmpty()) {
				colecaoCobrancaGrupo.addAll(colecaoCobrancaGrupoPesquisa);
			}
		} else {
			cobrancaGrupoColecao.setDescricao("TODOS");
			colecaoCobrancaGrupo.add(cobrancaGrupoColecao);
		}
		
		//--------------------------------------------------------------------------------------------------------
		//Ger�ncia Regional
		//--------------------------------------------------------------------------------------------------------
		String[] arrayGerenciaRegional = form.getArrayGerenciaRegional();		
		if (arrayGerenciaRegional == null){
			arrayGerenciaRegional = (String[])sessao.getAttribute("arrayGerenciaRegional");
	    }
	    sessao.setAttribute("arrayGerenciaRegional",arrayGerenciaRegional);
		
		
		GerenciaRegional gerenciaRegionalColecao = new GerenciaRegional();
		gerenciaRegionalColecao.setId(-1);

		Collection colecaoGerenciaRegional = new ArrayList();
		int j = 0;		
		
		if (arrayGerenciaRegional!= null && arrayGerenciaRegional.length>0) {
			gerenciaRegionalColecao.setNome("OP��ES SELECIONADAS");
			colecaoGerenciaRegional.add(gerenciaRegionalColecao);
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();

			for (j = 0; j < arrayGerenciaRegional.length; j++) {
				if (!arrayGerenciaRegional[j].equals("")
						&& !arrayGerenciaRegional[j].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (j + 1 < arrayGerenciaRegional.length) {
						filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
										FiltroGerenciaRegional.ID, arrayGerenciaRegional[j],
										ConectorOr.CONECTOR_OR,
										arrayGerenciaRegional.length));

					} else {
						filtroGerenciaRegional
								.adicionarParametro(new ParametroSimples(
										FiltroGerenciaRegional.ID, arrayGerenciaRegional[j]));
					}
				}
			}

			filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);

			Collection colecaoGerenciaRegionalPesquisa = fachada.pesquisar(
					filtroGerenciaRegional, GerenciaRegional.class.getName());

			if (colecaoGerenciaRegionalPesquisa != null
					&& !colecaoGerenciaRegionalPesquisa.isEmpty()) {
				
				colecaoGerenciaRegional.addAll(colecaoGerenciaRegionalPesquisa);				
			}
		} else {
			gerenciaRegionalColecao.setNome("TODOS");
			colecaoGerenciaRegional.add(gerenciaRegionalColecao);
		}
		//--------------------------------------------------------------------------------------------------------
		//Unidade de Neg�cio
		//--------------------------------------------------------------------------------------------------------
		String[] arrayUnidadeNegocio = form.getArrayUnidadeNegocio();	
		if (arrayUnidadeNegocio == null){
			arrayUnidadeNegocio = (String[])sessao.getAttribute("arrayUnidadeNegocio");
	    }
	    sessao.setAttribute("arrayUnidadeNegocio",arrayUnidadeNegocio);
		
		UnidadeNegocio unidadeNegocioColecao = new UnidadeNegocio();
		unidadeNegocioColecao.setId(-1);

		Collection colecaoUnidadeNegocio = new ArrayList();
		int l = 0;		
		
		if (arrayUnidadeNegocio != null && arrayUnidadeNegocio.length>0) {
			unidadeNegocioColecao.setNome("OP��ES SELECIONADAS");
			colecaoUnidadeNegocio.add(unidadeNegocioColecao);
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();

			for (l = 0; l < arrayUnidadeNegocio.length; l++) {
				if (!arrayUnidadeNegocio[l].equals("")
						&& !arrayUnidadeNegocio[l].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (l + 1 < arrayUnidadeNegocio.length) {
						filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
										FiltroUnidadeNegocio.ID, arrayUnidadeNegocio[l],
										ConectorOr.CONECTOR_OR,
										arrayUnidadeNegocio.length));

					} else {
						filtroUnidadeNegocio
								.adicionarParametro(new ParametroSimples(
										FiltroUnidadeNegocio.ID, arrayUnidadeNegocio[l]));
					}
				}
			}

			filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);

			Collection colecaoUnidadeNegocioPesquisa = fachada.pesquisar(
					filtroUnidadeNegocio, UnidadeNegocio.class.getName());

			if (colecaoUnidadeNegocioPesquisa != null
					&& !colecaoUnidadeNegocioPesquisa.isEmpty()) {
				colecaoUnidadeNegocio.addAll(colecaoUnidadeNegocioPesquisa);
			}
		} else {
			unidadeNegocioColecao.setNome("TODOS");
			colecaoUnidadeNegocio.add(unidadeNegocioColecao);
		}
		
		//--------------------------------------------------------------------------------------------------------
		//Perfil Im�vel
		//--------------------------------------------------------------------------------------------------------
		String[] arrayImovelPerfil = form.getArrayImovelPerfil();	
		if (arrayImovelPerfil == null){
			arrayImovelPerfil = (String[])sessao.getAttribute("arrayImovelPerfil");
	    }
	    sessao.setAttribute("arrayImovelPerfil",arrayImovelPerfil);
	    
		ImovelPerfil imovelPerfilColecao = new ImovelPerfil();
		imovelPerfilColecao.setId(-1);

		Collection colecaoImovelPerfil = new ArrayList();
		int m = 0;		
		
		if (arrayImovelPerfil != null && arrayImovelPerfil.length>0) {
			imovelPerfilColecao.setDescricao("OP��ES SELECIONADAS");
			colecaoImovelPerfil.add(imovelPerfilColecao);
			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();

			for (m = 0; m < arrayImovelPerfil.length; m++) {
				if (!arrayImovelPerfil[m].equals("")
						&& !arrayImovelPerfil[m].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (m + 1 < arrayImovelPerfil.length) {
						filtroImovelPerfil.adicionarParametro(new ParametroSimples(
										FiltroImovelPerfil.ID, arrayImovelPerfil[m],
										ConectorOr.CONECTOR_OR,
										arrayImovelPerfil.length));

					} else {
						filtroImovelPerfil
								.adicionarParametro(new ParametroSimples(
										FiltroImovelPerfil.ID, arrayImovelPerfil[m]));
					}
				}
			}

			filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);

			Collection colecaoImovelPerfilPesquisa = fachada.pesquisar(
					filtroImovelPerfil, ImovelPerfil.class.getName());

			if (colecaoImovelPerfilPesquisa != null
					&& !colecaoImovelPerfilPesquisa.isEmpty()) {
				colecaoImovelPerfil.addAll(colecaoImovelPerfilPesquisa);
			}
		} else {
			imovelPerfilColecao.setDescricao("TODOS");
			colecaoImovelPerfil.add(imovelPerfilColecao);
		}
		
		//--------------------------------------------------------------------------------------------------------
		//Categoria
		//--------------------------------------------------------------------------------------------------------
		String[] arrayCategoria = form.getArrayCategoria();	
		if (arrayCategoria == null){
			arrayCategoria = (String[])sessao.getAttribute("arrayCategoria");
	    }
	    sessao.setAttribute("arrayCategoria",arrayCategoria);
		
		Categoria categoriaColecao = new Categoria();
		categoriaColecao.setId(-1);

		Collection colecaoCategoria = new ArrayList();
		int n = 0;		
		
		if (arrayCategoria != null && arrayCategoria.length>0) {
			categoriaColecao.setDescricao("OP��ES SELECIONADAS");
			colecaoCategoria.add(categoriaColecao);
			FiltroCategoria filtroCategoria = new FiltroCategoria();

			for (n = 0; n < arrayCategoria.length; n++) {
				if (!arrayCategoria[n].equals("")
						&& !arrayCategoria[n].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (n + 1 < arrayCategoria.length) {
						filtroCategoria.adicionarParametro(new ParametroSimples(
										FiltroCategoria.CODIGO, arrayCategoria[n],
										ConectorOr.CONECTOR_OR,
										arrayCategoria.length));

					} else {
						filtroCategoria
								.adicionarParametro(new ParametroSimples(
										FiltroCategoria.CODIGO, arrayCategoria[n]));
					}
				}
			}

			filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);

			Collection colecaoCategoriaPesquisa = fachada.pesquisar(
					filtroCategoria, Categoria.class.getName());

			if (colecaoCategoriaPesquisa != null
					&& !colecaoCategoriaPesquisa.isEmpty()) {
				colecaoCategoria.addAll(colecaoCategoriaPesquisa);
			}
		} else {
			categoriaColecao.setDescricao("TODOS");
			colecaoCategoria.add(categoriaColecao);
		}
		
		
		//--------------------------------------------------------------------------------------------------------
		//TipoCliente
		//--------------------------------------------------------------------------------------------------------
		String[] arrayTipoCliente = form.getArrayTipoCliente();		
		if (arrayTipoCliente == null){
			arrayTipoCliente = (String[])sessao.getAttribute("arrayTipoCliente");
	    }
	    sessao.setAttribute("arrayTipoCliente",arrayTipoCliente);
		
		
		ClienteTipo tipoClienteColecao = new ClienteTipo();
		tipoClienteColecao.setId(-1);

		Collection colecaoTipoCliente = new ArrayList();
		int o = 0;		
		
		if (arrayTipoCliente != null && arrayTipoCliente.length>0) {
			tipoClienteColecao.setDescricao("OP��ES SELECIONADAS");
			colecaoTipoCliente.add(tipoClienteColecao);
			FiltroClienteTipo filtroClienteTipo = new FiltroClienteTipo();

			for (o = 0; o < arrayTipoCliente.length; o++) {
				if (!arrayTipoCliente[o].equals("")
						&& !arrayTipoCliente[o].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (o + 1 < arrayTipoCliente.length) {
						filtroClienteTipo.adicionarParametro(new ParametroSimples(
										FiltroClienteTipo.ID, arrayTipoCliente[o],
										ConectorOr.CONECTOR_OR,
										arrayTipoCliente.length));

					} else {
						filtroClienteTipo
								.adicionarParametro(new ParametroSimples(
										FiltroClienteTipo.ID, arrayTipoCliente[o]));
					}
				}
			}

			filtroClienteTipo.setCampoOrderBy(FiltroClienteTipo.DESCRICAO);

			Collection colecaoTipoClientePesquisa = fachada.pesquisar(
					filtroClienteTipo, ClienteTipo.class.getName());

			if (colecaoTipoClientePesquisa != null
					&& !colecaoTipoClientePesquisa.isEmpty()) {
				colecaoTipoCliente.addAll(colecaoTipoClientePesquisa);
			}
		} else {
			tipoClienteColecao.setDescricao("TODOS");
			colecaoTipoCliente.add(tipoClienteColecao);
		}
	
		//--------------------------------------------------------------------------------------------------------
		//Esfera Poder
		//--------------------------------------------------------------------------------------------------------
		String[] arrayEsferaPoder = form.getArrayEsferaPoder();		
		if (arrayEsferaPoder == null){
			arrayEsferaPoder = (String[])sessao.getAttribute("arrayEsferaPoder");
	    }
	    sessao.setAttribute("arrayEsferaPoder",arrayEsferaPoder);
		
		EsferaPoder esferaPoderColecao = new EsferaPoder();
		esferaPoderColecao.setId(-1);

		Collection colecaoEsferaPoder = new ArrayList();
		int p = 0;		
		
		if (arrayEsferaPoder != null && arrayEsferaPoder.length>0) {
			esferaPoderColecao.setDescricao("OP��ES SELECIONADAS");
			colecaoEsferaPoder.add(esferaPoderColecao);
			FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder();

			for (p = 0; p < arrayEsferaPoder.length; p++) {
				if (!arrayEsferaPoder[p].equals("")
						&& !arrayEsferaPoder[p].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (p + 1 < arrayEsferaPoder.length) {
						filtroEsferaPoder.adicionarParametro(new ParametroSimples(
										FiltroEsferaPoder.ID, arrayEsferaPoder[p],
										ConectorOr.CONECTOR_OR,
										arrayEsferaPoder.length));

					} else {
						filtroEsferaPoder
								.adicionarParametro(new ParametroSimples(
										FiltroEsferaPoder.ID, arrayEsferaPoder[p]));
					}
				}
			}

			filtroEsferaPoder.setCampoOrderBy(FiltroEsferaPoder.DESCRICAO);

			Collection colecaoEsferaPoderPesquisa = fachada.pesquisar(
					filtroEsferaPoder, EsferaPoder.class.getName());

			if (colecaoEsferaPoderPesquisa != null
					&& !colecaoEsferaPoderPesquisa.isEmpty()) {
				colecaoEsferaPoder.addAll(colecaoEsferaPoderPesquisa);
			}
		} else {
			esferaPoderColecao.setDescricao("TODOS");
			colecaoEsferaPoder.add(esferaPoderColecao);
		}
		
		//********************************************************
		// RM3755
		// Autor: Ivan Sergio
		// Data: 12/01/2011
		//********************************************************
		//--------------------------------------------------------------------------------------------------------
		// Ligacao Agua Situacao
		//--------------------------------------------------------------------------------------------------------
		String[] arrayLigacaoAguaSituacao = form.getArrayLigacaoAguaSituacao();		
		LigacaoAguaSituacao ligacaoAguaSituacaoColecao = new LigacaoAguaSituacao();
		ligacaoAguaSituacaoColecao.setId(-1);

		Collection colecaoLigacaoAguaSituacao = new ArrayList();
		int x = 0;		
		
		if (arrayLigacaoAguaSituacao != null) {
			ligacaoAguaSituacaoColecao.setDescricao("OP��ES SELECIONADAS");
			colecaoLigacaoAguaSituacao.add(ligacaoAguaSituacaoColecao);
			FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
			
			for (x = 0; x < arrayLigacaoAguaSituacao.length; x++) {
				if (!arrayLigacaoAguaSituacao[x].equals("")
						&& !arrayLigacaoAguaSituacao[x].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (x + 1 < arrayLigacaoAguaSituacao.length) {
						filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
										FiltroLigacaoAguaSituacao.ID, arrayLigacaoAguaSituacao[x],
										ConectorOr.CONECTOR_OR,
										arrayEsferaPoder.length));

					} else {
						filtroLigacaoAguaSituacao
								.adicionarParametro(new ParametroSimples(
										FiltroLigacaoAguaSituacao.ID, arrayLigacaoAguaSituacao[x]));
					}
				}
			}
			
			filtroLigacaoAguaSituacao.setCampoOrderBy(FiltroLigacaoAguaSituacao.DESCRICAO);
			
			Collection colecaoLigacaoAguaSituacaoPesquisa = fachada.pesquisar(
					filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());

			if (colecaoLigacaoAguaSituacaoPesquisa != null
					&& !colecaoLigacaoAguaSituacaoPesquisa.isEmpty()) {
				colecaoLigacaoAguaSituacao.addAll(colecaoLigacaoAguaSituacaoPesquisa);
			}
		} else {
			ligacaoAguaSituacaoColecao.setDescricao("TODOS");
			colecaoLigacaoAguaSituacao.add(ligacaoAguaSituacaoColecao);
		}
		
		
		//--------------------------------------------------------------------------------------------------------
		// Ligacao Esgoto Situacao
		//--------------------------------------------------------------------------------------------------------
		String[] arrayLigacaoEsgotoSituacao = form.getArrayLigacaoEsgotoSituacao();		
		LigacaoEsgotoSituacao ligacaoEsgotoSituacaoColecao = new LigacaoEsgotoSituacao();
		ligacaoEsgotoSituacaoColecao.setId(-1);

		Collection colecaoLigacaoEsgotoSituacao = new ArrayList();
		
		if (arrayLigacaoEsgotoSituacao != null) {
			ligacaoEsgotoSituacaoColecao.setDescricao("OP��ES SELECIONADAS");
			colecaoLigacaoEsgotoSituacao.add(ligacaoEsgotoSituacaoColecao);
			FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
			
			for (x = 0; x < arrayLigacaoEsgotoSituacao.length; x++) {
				if (!arrayLigacaoEsgotoSituacao[x].equals("")
						&& !arrayLigacaoEsgotoSituacao[x].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (x + 1 < arrayLigacaoEsgotoSituacao.length) {
						filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
										FiltroLigacaoEsgotoSituacao.ID, arrayLigacaoEsgotoSituacao[x],
										ConectorOr.CONECTOR_OR,
										arrayEsferaPoder.length));

					} else {
						filtroLigacaoEsgotoSituacao
								.adicionarParametro(new ParametroSimples(
										FiltroLigacaoEsgotoSituacao.ID, arrayLigacaoEsgotoSituacao[x]));
					}
				}
			}
			
			filtroLigacaoEsgotoSituacao.setCampoOrderBy(FiltroLigacaoEsgotoSituacao.DESCRICAO);
			
			Collection colecaoLigacaoEsgotoSituacaoPesquisa = fachada.pesquisar(
					filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName());

			if (colecaoLigacaoEsgotoSituacaoPesquisa != null
					&& !colecaoLigacaoEsgotoSituacaoPesquisa.isEmpty()) {
				colecaoLigacaoEsgotoSituacao.addAll(colecaoLigacaoEsgotoSituacaoPesquisa);
			}
		} else {
			ligacaoEsgotoSituacaoColecao.setDescricao("TODOS");
			colecaoLigacaoEsgotoSituacao.add(ligacaoEsgotoSituacaoColecao);
		}
		
		//************************************************************************************************
		
		
		// cria uma inst�ncia da classe do relat�rio
		RelatorioNegativacoesExcluidas relatorio = new RelatorioNegativacoesExcluidas(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
		
		// seta os parametros que ser�o mostrados no relat�rio
		parametros= validarGeracaoRelatorio(colecaoNegativador, periodoEnvioNegativacaoInicio, periodoEnvioNegativacaoFim,
				                                periodoExclusaoNegativacaoInicio, periodoExclusaoNegativacaoFim,
				                                idNegativadorExclusaoMotivo,
												tituloComando, idEloPolo, idLocalidade,
												idSetorComercial,
												idQuadra,
												colecaoCobrancaGrupo,
												colecaoGerenciaRegional,
												colecaoUnidadeNegocio, 
												colecaoImovelPerfil,
												colecaoCategoria,
												colecaoTipoCliente,
												colecaoEsferaPoder,
												colecaoLigacaoAguaSituacao,
												colecaoLigacaoEsgotoSituacao);
		// Fim da parte que vai mandar os parametros para o relat�rio
		

			relatorio.addParametro("parametros",parametros);
			
			// chama o met�do de gerar relat�rio passando o c�digo da analise
			// como par�metro
			String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
			if (tipoRelatorio == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}

			relatorio.addParametro("tipoFormatoRelatorio", Integer
					.parseInt(tipoRelatorio));
			try {
				retorno = processarExibicaoRelatorio(relatorio,
						tipoRelatorio, httpServletRequest, httpServletResponse,
						actionMapping);

		} catch (SistemaException ex) {
			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "erro.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de aten��o de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		// devolve o mapeamento contido na vari�vel retorno
		return retorno;
	}
	
	
	private DadosConsultaNegativacaoHelper validarGeracaoRelatorio(
			Collection collNegativador, Date periodoEnvioNegativacaoInicio,
			Date periodoEnvioNegativacaoFim,Date periodoExclusaoNegativacaoInicio,
			Date periodoExclusaoNegativacaoFim,String idNegativadorExclusaoMotivo,String tituloComando,
			Integer idEloPolo, Integer idLocalidade, Integer idSetorComercial,
			Integer idQuadra, Collection collCobrancaGrupo,
			Collection collGerenciaRegional, Collection collUnidadeNegocio,
			Collection collImovelPerfil, Collection collCategoria,
			Collection collTipoCliente, Collection collEsferaPoder,
			Collection collLigacaoAguaSituacao, Collection collLigacaoEsgotoSituacao) {

		Fachada fachada = Fachada.getInstancia();

		// Inicio da parte que vai mandar os parametros para o relat�rio
		DadosConsultaNegativacaoHelper helper = new DadosConsultaNegativacaoHelper();
		
		//********************************************************
		// RM3755
		// Autor: Ivan Sergio
		// Data: 12/01/2011
		//********************************************************
//		if (idNegativador != null && !idNegativador.equals("")) {
//			FiltroNegativador filtroNegativador = new FiltroNegativador();
//
//			filtroNegativador.adicionarParametro(new ParametroSimples(
//					FiltroNegativador.ID, idNegativador));
//
//			filtroNegativador
//					.adicionarCaminhoParaCarregamentoEntidade("cliente");
//
//			Collection collNegativador = fachada.pesquisar(filtroNegativador,
//					Negativador.class.getName());
//
//			if (Util.isVazioOrNulo(collNegativador)) {
//				throw new ActionServletException(
//						"atencao.pesquisa_inexistente", null, "Negativador");
//			}
//		}
				
		if (idNegativadorExclusaoMotivo != null && !idNegativadorExclusaoMotivo.equals("") 
				&& !idNegativadorExclusaoMotivo.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			FiltroNegativadorExclusaoMotivo filtroNegativadorExclusaoMotivo = new FiltroNegativadorExclusaoMotivo();

			filtroNegativadorExclusaoMotivo.adicionarParametro(new ParametroSimples(
					FiltroNegativadorExclusaoMotivo.ID, idNegativadorExclusaoMotivo));


			Collection collNegativadorExclusaoMotivo = fachada.pesquisar(filtroNegativadorExclusaoMotivo,
					NegativadorExclusaoMotivo.class.getName());

			if (Util.isVazioOrNulo(collNegativadorExclusaoMotivo)) {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Negativador Exclus�o Motivo");
			}
		}
		

		if (idEloPolo != null && !idEloPolo.equals("")) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID_ELO, idEloPolo));

			Collection collLocalidade = Fachada.getInstancia().pesquisar(
					filtroLocalidade, Localidade.class.getName());

			if (Util.isVazioOrNulo(collLocalidade)) {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "EloPolo");
			}
		}

		if (idLocalidade != null && !idLocalidade.equals("")) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidade));

			Collection collLocalidade = Fachada.getInstancia().pesquisar(
					filtroLocalidade, Localidade.class.getName());

			if (Util.isVazioOrNulo(collLocalidade)) {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Localidade");
			}
		}

		if (idSetorComercial != null && !idSetorComercial.equals("")) {
			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.ID_LOCALIDADE, idLocalidade));
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.ID, idSetorComercial));

			Collection collSetorComercial = Fachada.getInstancia().pesquisar(
					filtroSetorComercial, SetorComercial.class.getName());

			if (Util.isVazioOrNulo(collSetorComercial)) {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Setor Comercial");
			}
		}

		if (idQuadra != null && !idQuadra.equals("")) {

			FiltroQuadra filtroQuadra = new FiltroQuadra();
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.ID_LOCALIDADE, idLocalidade));
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.ID_SETORCOMERCIAL, idSetorComercial));
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.ID, idQuadra));

			Collection collQuadra = Fachada.getInstancia().pesquisar(
					filtroQuadra, Quadra.class.getName());

			if (Util.isVazioOrNulo(collQuadra)) {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Quadra");
			}
		}

		helper.setColecaoNegativador(collNegativador);
		helper.setPeriodoEnvioNegativacaoInicio(periodoEnvioNegativacaoInicio);
		helper.setPeriodoEnvioNegativacaoFim(periodoEnvioNegativacaoFim);
		helper.setPeriodoExclusaoNegativacaoInicio(periodoExclusaoNegativacaoInicio);
		helper.setPeriodoExclusaoNegativacaoFim(periodoExclusaoNegativacaoFim);
		helper.setIdNegativadorExclusaoMotivo(new Integer(idNegativadorExclusaoMotivo));
		helper.setTituloComando(tituloComando);
		helper.setIdEloPolo(idEloPolo);
		helper.setIdLocalidade(idLocalidade);
		helper.setIdSetorComercial(idSetorComercial);
		helper.setIdQuadra(idQuadra);
		helper.setColecaoCobrancaGrupo(collCobrancaGrupo);
		helper.setColecaoGerenciaRegional(collGerenciaRegional);
		helper.setColecaoUnidadeNegocio(collUnidadeNegocio);
		helper.setColecaoImovelPerfil(collImovelPerfil);
		helper.setColecaoCategoria(collCategoria);
		helper.setColecaoClienteTipo(collTipoCliente);
		helper.setColecaoEsferaPoder(collEsferaPoder);
		helper.setColecaoLigacaoAguaSituacao(collLigacaoAguaSituacao);
		helper.setColecaoLigacaoEsgotoSituacao(collLigacaoEsgotoSituacao);

		// Verifica se a pesquisa retorno algum resultado
		int qtdeResultados = fachada
				.pesquisarRelatorioNegativacoesExcluidasCount(helper);

		if (qtdeResultados == 0) {
			// Caso a pesquisa n�o retorne nenhum resultado comunica ao usu�rio;
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		} else {
			return helper;
		}

	}

	
	
}
