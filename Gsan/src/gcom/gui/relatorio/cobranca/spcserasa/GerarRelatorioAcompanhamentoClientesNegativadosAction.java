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
package gcom.gui.relatorio.cobranca.spcserasa;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroClienteTipo;
import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorRetornoMotivo;
import gcom.cobranca.bean.DadosConsultaNegativacaoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.cobranca.spcserasa.InformarDadosConsultaNegativacaoActionForm;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cobranca.spcserasa.RelatorioAcompanhamentoClientesNegativados;
import gcom.relatorio.cobranca.spcserasa.RelatorioAcompanhamentoClientesNegativadosSintetico;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.spcserasa.FiltroNegativador;
import gcom.spcserasa.FiltroNegativadorRetornoMotivo;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ConectorOr;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

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
public class GerarRelatorioAcompanhamentoClientesNegativadosAction extends
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
		ActionForward retorno = actionMapping.findForward("exibirInformarDadosConsultaNegativacao");
		
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);
		
		InformarDadosConsultaNegativacaoActionForm form =  (InformarDadosConsultaNegativacaoActionForm) actionForm;		
		
		// Inicio da parte que vai mandar os parametros para o relat�rio
		DadosConsultaNegativacaoHelper parametros = null;

		//********************************************************
		// RM3755
		// Autor: Ivan Sergio
		// Data: 12/01/2011
		//********************************************************		
		//String idNegativador = (String) form.getIdNegativador();        
        //if (idNegativador == null || idNegativador.equalsIgnoreCase("")){
        //    idNegativador = (String)sessao.getAttribute("idNegativador");
        //}
        //sessao.setAttribute("idNegativador",idNegativador);
		//--------------------------------------------------------------------------------------------------------
		// Negativador
		//--------------------------------------------------------------------------------------------------------
		String[] arrayNegativador = form.getArrayNegativador();	
		
		if (arrayNegativador == null){
			arrayNegativador = (String[])sessao.getAttribute("arrayNegativador");
	    }
	    sessao.setAttribute("arrayNegativador", arrayNegativador);
		
		
	    Negativador negativadorColecao = new Negativador();
	    negativadorColecao.setId(-1);

		Collection colecaoNegativador = new ArrayList();
		int i = 0;		
		
		Collection colecaoIdNegativador = new ArrayList();
		if (!Util.isVazioOrNulo(arrayNegativador)) {
			//negativadorColecao.setDescricao("OP��ES SELECIONADAS");
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
		
		if (!Util.isVazioOrNulo(arrayCobrancaGrupo)) {
			cobrancaGrupoColecao.setDescricao("");

			Collection<Integer> colecaoIdsCobrancaGrupoSelecionados = new ArrayList<Integer>();
			
			Collection colecaoCobrancaGrupoPesquisa = null;
			
			for (i = 0; i < arrayCobrancaGrupo.length; i++) {
				if (!arrayCobrancaGrupo[i].equals("")
						&& !arrayCobrancaGrupo[i].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					colecaoIdsCobrancaGrupoSelecionados.add(new Integer(arrayCobrancaGrupo[i]));
				}
			}
			
			if (colecaoIdsCobrancaGrupoSelecionados != null && !colecaoIdsCobrancaGrupoSelecionados.isEmpty()) {
				FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();
				filtroCobrancaGrupo.adicionarParametro(new ParametroSimplesIn(FiltroCobrancaGrupo.ID, colecaoIdsCobrancaGrupoSelecionados));
				filtroCobrancaGrupo.setCampoOrderBy(FiltroCobrancaGrupo.DESCRICAO);
				
				colecaoCobrancaGrupoPesquisa = fachada.pesquisar(
						filtroCobrancaGrupo, CobrancaGrupo.class.getName());
			}

			if (colecaoCobrancaGrupoPesquisa != null && !colecaoCobrancaGrupoPesquisa.isEmpty()) {
					colecaoCobrancaGrupo.addAll(colecaoCobrancaGrupoPesquisa);
			}
		} else {
			cobrancaGrupoColecao.setDescricao("");
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
		
		if (!Util.isVazioOrNulo(arrayGerenciaRegional)) {
			gerenciaRegionalColecao.setNome("");
			
			Collection<Integer> colecaoIdsGerenciaRegionalSelecionados = new ArrayList<Integer>();
			
			Collection colecaoGerenciaRegionalPesquisa = null;
			
			for (j = 0; j < arrayGerenciaRegional.length; j++) {
				if (!arrayGerenciaRegional[j].equals("")
						&& !arrayGerenciaRegional[j].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					colecaoIdsGerenciaRegionalSelecionados.add(new Integer(arrayGerenciaRegional[j]));
				}
			}
			
			if (colecaoIdsGerenciaRegionalSelecionados != null && !colecaoIdsGerenciaRegionalSelecionados.isEmpty()) {
				FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
				filtroGerenciaRegional.adicionarParametro(new ParametroSimplesIn(FiltroGerenciaRegional.ID, colecaoIdsGerenciaRegionalSelecionados));
				filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
				
				colecaoGerenciaRegionalPesquisa = fachada.pesquisar(
						filtroGerenciaRegional, GerenciaRegional.class.getName());
			}

			if (colecaoGerenciaRegionalPesquisa != null
					&& !colecaoGerenciaRegionalPesquisa.isEmpty()) {
				colecaoGerenciaRegional.addAll(colecaoGerenciaRegionalPesquisa);
			}
		} else {
			gerenciaRegionalColecao.setNome("");
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
		
		if (!Util.isVazioOrNulo(arrayUnidadeNegocio)) {
			unidadeNegocioColecao.setNome("");

			Collection<Integer> colecaoIdsUnidadeNegocioSelecionados = new ArrayList<Integer>();
			
			Collection colecaoUnidadeNegocioPesquisa = null;
			
			for (l = 0; l < arrayUnidadeNegocio.length; l++) {
				if (!arrayUnidadeNegocio[l].equals("")
						&& !arrayUnidadeNegocio[l].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					colecaoIdsUnidadeNegocioSelecionados.add(new Integer(arrayUnidadeNegocio[l]));
				}
			}
			
			if (colecaoIdsUnidadeNegocioSelecionados != null && !colecaoIdsUnidadeNegocioSelecionados.isEmpty()) {
				FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
				filtroUnidadeNegocio.adicionarParametro(new ParametroSimplesIn(FiltroUnidadeNegocio.ID, colecaoIdsUnidadeNegocioSelecionados));
				filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);
				
				colecaoUnidadeNegocioPesquisa = fachada.pesquisar(
						filtroUnidadeNegocio, UnidadeNegocio.class.getName());
			}
			
			if (colecaoUnidadeNegocioPesquisa != null
					&& !colecaoUnidadeNegocioPesquisa.isEmpty()) {
				colecaoUnidadeNegocio.addAll(colecaoUnidadeNegocioPesquisa);
			}
		} else {
			unidadeNegocioColecao.setNome("");
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
			imovelPerfilColecao.setDescricao("");
			
			Collection<Integer> colecaoIdsImovelPerfilSelecionados = new ArrayList<Integer>();
			
			Collection colecaoImovelPerfilPesquisa = null;
			
			for (m = 0; m < arrayImovelPerfil.length; m++) {
				if (!arrayImovelPerfil[m].equals("")
						&& !arrayImovelPerfil[m].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					colecaoIdsImovelPerfilSelecionados.add(new Integer(arrayImovelPerfil[m]));
				}
			}
			
			if (colecaoIdsImovelPerfilSelecionados != null && !colecaoIdsImovelPerfilSelecionados.isEmpty()) {
				FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
				filtroImovelPerfil.adicionarParametro(new ParametroSimplesIn(FiltroImovelPerfil.ID, colecaoIdsImovelPerfilSelecionados));
				filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);
				
				colecaoImovelPerfilPesquisa = fachada.pesquisar(
						filtroImovelPerfil, ImovelPerfil.class.getName());
			}

			if (colecaoImovelPerfilPesquisa != null
					&& !colecaoImovelPerfilPesquisa.isEmpty()) {
				colecaoImovelPerfil.addAll(colecaoImovelPerfilPesquisa);
			}
		} else {
			imovelPerfilColecao.setDescricao("");
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
			categoriaColecao.setDescricao("");
			
			Collection<Integer> colecaoIdsCategoriaSelecionados = new ArrayList<Integer>();
			
			Collection colecaoCategoriaPesquisa = null;
			
			for (n = 0; n < arrayCategoria.length; n++) {
				if (!arrayCategoria[n].equals("")
						&& !arrayCategoria[n].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					colecaoIdsCategoriaSelecionados.add(new Integer(arrayCategoria[n]));
				}
			}
			
			if (colecaoIdsCategoriaSelecionados != null && !colecaoIdsCategoriaSelecionados.isEmpty()) {
				FiltroCategoria filtroCategoria = new FiltroCategoria();
				filtroCategoria.adicionarParametro(new ParametroSimplesIn(FiltroCategoria.CODIGO, colecaoIdsCategoriaSelecionados));
				filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);
				
				colecaoCategoriaPesquisa = fachada.pesquisar(
						filtroCategoria, Categoria.class.getName());
			}

			if (colecaoCategoriaPesquisa != null
					&& !colecaoCategoriaPesquisa.isEmpty()) {
				colecaoCategoria.addAll(colecaoCategoriaPesquisa);
			}
		} else {
			categoriaColecao.setDescricao("");
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
			tipoClienteColecao.setDescricao("");
			
			Collection<Integer> colecaoIdsTipoClienteSelecionados = new ArrayList<Integer>();
			
			Collection colecaoTipoClientePesquisa = null;
			
			for (o = 0; o < arrayTipoCliente.length; o++) {
				if (!arrayTipoCliente[o].equals("")
						&& !arrayTipoCliente[o].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					colecaoIdsTipoClienteSelecionados.add(new Integer(arrayTipoCliente[o]));
				}
			}
			
			if (colecaoIdsTipoClienteSelecionados != null && !colecaoIdsTipoClienteSelecionados.isEmpty()) {
				FiltroClienteTipo filtroClienteTipo = new FiltroClienteTipo();
				filtroClienteTipo.adicionarParametro(new ParametroSimplesIn(FiltroClienteTipo.ID, colecaoIdsTipoClienteSelecionados));
				filtroClienteTipo.setCampoOrderBy(FiltroClienteTipo.DESCRICAO);
				
				colecaoTipoClientePesquisa = fachada.pesquisar(
						filtroClienteTipo, ClienteTipo.class.getName());
			}

			if (colecaoTipoClientePesquisa != null
					&& !colecaoTipoClientePesquisa.isEmpty()) {
				colecaoTipoCliente.addAll(colecaoTipoClientePesquisa);
			}
		} else {
			tipoClienteColecao.setDescricao("");
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
			esferaPoderColecao.setDescricao("");
			
			Collection<Integer> colecaoIdsEsferaPoderSelecionados = new ArrayList<Integer>();
			
			Collection colecaoEsferaPoderPesquisa = null;
			
			for (p = 0; p < arrayEsferaPoder.length; p++) {
				if (!arrayEsferaPoder[p].equals("")
						&& !arrayEsferaPoder[p].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					colecaoIdsEsferaPoderSelecionados.add(new Integer(arrayEsferaPoder[p]));
				}
			}
			
			if (colecaoIdsEsferaPoderSelecionados != null && !colecaoIdsEsferaPoderSelecionados.isEmpty()) {
				FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder();
				filtroEsferaPoder.adicionarParametro(new ParametroSimplesIn(FiltroEsferaPoder.ID, colecaoIdsEsferaPoderSelecionados));
				filtroEsferaPoder.setCampoOrderBy(FiltroEsferaPoder.DESCRICAO);
				
				colecaoEsferaPoderPesquisa = fachada.pesquisar(
						filtroEsferaPoder, EsferaPoder.class.getName());
			}

			if (colecaoEsferaPoderPesquisa != null
					&& !colecaoEsferaPoderPesquisa.isEmpty()) {
				colecaoEsferaPoder.addAll(colecaoEsferaPoderPesquisa);
			}
		} else {
			esferaPoderColecao.setDescricao("");
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
			ligacaoAguaSituacaoColecao.setDescricao("");
			
			Collection<Integer> colecaoIdsLigacaoAguaSituacaoSelecionados = new ArrayList<Integer>();
			
			Collection colecaoLigacaoAguaSituacaoPesquisa = null;
			
			for (x = 0; x < arrayLigacaoAguaSituacao.length; x++) {
				if (!arrayLigacaoAguaSituacao[x].equals("")
						&& !arrayLigacaoAguaSituacao[x].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					colecaoIdsLigacaoAguaSituacaoSelecionados.add(new Integer(arrayLigacaoAguaSituacao[x]));
				}
			}
			
			if (colecaoIdsLigacaoAguaSituacaoSelecionados != null && !colecaoIdsLigacaoAguaSituacaoSelecionados.isEmpty()) {
				FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
				filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimplesIn(FiltroLigacaoAguaSituacao.ID, colecaoIdsLigacaoAguaSituacaoSelecionados));
				filtroLigacaoAguaSituacao.setCampoOrderBy(FiltroLigacaoAguaSituacao.DESCRICAO);
				
				colecaoLigacaoAguaSituacaoPesquisa = fachada.pesquisar(
						filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
			}

			if (colecaoLigacaoAguaSituacaoPesquisa != null
					&& !colecaoLigacaoAguaSituacaoPesquisa.isEmpty()) {
				colecaoLigacaoAguaSituacao.addAll(colecaoLigacaoAguaSituacaoPesquisa);
			}
			
		} else {
			ligacaoAguaSituacaoColecao.setDescricao("");
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
			ligacaoEsgotoSituacaoColecao.setDescricao("");
			
			Collection<Integer> colecaoIdsLigacaoEsgotoSituacaoSelecionados = new ArrayList<Integer>();
			
			Collection colecaoLigacaoEsgotoSituacaoPesquisa = null;
			
			for (x = 0; x < arrayLigacaoEsgotoSituacao.length; x++) {
				if (!arrayLigacaoEsgotoSituacao[x].equals("")
						&& !arrayLigacaoEsgotoSituacao[x].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					colecaoIdsLigacaoEsgotoSituacaoSelecionados.add(new Integer(arrayLigacaoEsgotoSituacao[x]));
				}
			}
			
			if (colecaoIdsLigacaoEsgotoSituacaoSelecionados != null && !colecaoIdsLigacaoEsgotoSituacaoSelecionados.isEmpty()) {
				FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
				filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimplesIn(FiltroLigacaoEsgotoSituacao.ID, colecaoIdsLigacaoEsgotoSituacaoSelecionados));
				filtroLigacaoEsgotoSituacao.setCampoOrderBy(FiltroLigacaoEsgotoSituacao.DESCRICAO);
				
				colecaoLigacaoEsgotoSituacaoPesquisa = fachada.pesquisar(
						filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName());
			}

			if (colecaoLigacaoEsgotoSituacaoPesquisa != null
					&& !colecaoLigacaoEsgotoSituacaoPesquisa.isEmpty()) {
				colecaoLigacaoEsgotoSituacao.addAll(colecaoLigacaoEsgotoSituacaoPesquisa);
			}
		} else {
			ligacaoEsgotoSituacaoColecao.setDescricao("");
			colecaoLigacaoEsgotoSituacao.add(ligacaoEsgotoSituacaoColecao);
		}
				
		//************************************************************************************************
		
		
		Collection colecaoMotivoRejeicao = null;
		Short indicadorApenasNegativacoesRejeitadas = null;
		if(form.getIndicadorRelAcompanhamentoClientesNegativados()!= null && 
				form.getIndicadorRelAcompanhamentoClientesNegativados().equals("sim")){
			//--------------------------------------------------------------------------------------------------------
			//Motivo de Rejei��o
			//--------------------------------------------------------------------------------------------------------
			
			String[] arrayMotivoRejeicao = form.getArrayMotivoRejeicao();		
			NegativadorRetornoMotivo negativadorRetornoMotivoColecao = new NegativadorRetornoMotivo();
			negativadorRetornoMotivoColecao.setId(-1);
			
			if (arrayMotivoRejeicao == null){
				arrayMotivoRejeicao = (String[])sessao.getAttribute("arrayMotivoRejeicao");
		    }
		    sessao.setAttribute("arrayMotivoRejeicao",arrayMotivoRejeicao);
			
		    colecaoMotivoRejeicao = new ArrayList();
			int t = 0;				
			
			
			if (arrayMotivoRejeicao != null && arrayMotivoRejeicao.length>0) {
				negativadorRetornoMotivoColecao.setDescricaoRetornocodigo("");
				
				Collection<Integer> colecaoIdsNegativadorRetornoMotivoSelecionados = new ArrayList<Integer>();
				
				Collection colecaoNegativadorRetornoMotivoPesquisa = null;
				
				for (t = 0; t < arrayMotivoRejeicao.length; t++) {
					if (!arrayMotivoRejeicao[t].equals("")
							&& !arrayMotivoRejeicao[t].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
						colecaoIdsNegativadorRetornoMotivoSelecionados.add(new Integer(arrayMotivoRejeicao[t]));
					}
				}
				
				if (colecaoIdsNegativadorRetornoMotivoSelecionados != null && !colecaoIdsNegativadorRetornoMotivoSelecionados.isEmpty()) {
					FiltroNegativadorRetornoMotivo filtroNegativadorRetornoMotivo = new FiltroNegativadorRetornoMotivo();
					filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimples(FiltroNegativadorRetornoMotivo.INDICADOR_REGISTRO_ACEITO, new Short("2") ));
					filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimplesIn(FiltroNegativadorRetornoMotivo.NEGATIVADOR_RETORNO_MOTIVO_NEGATIVADOR, colecaoIdNegativador));
					filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimplesIn(FiltroNegativadorRetornoMotivo.ID, colecaoIdsNegativadorRetornoMotivoSelecionados));
					filtroNegativadorRetornoMotivo.setCampoOrderBy(FiltroNegativadorRetornoMotivo.DESCRICAO_RETORNO_CODIGO);
					
					colecaoNegativadorRetornoMotivoPesquisa = fachada.pesquisar(
							filtroNegativadorRetornoMotivo, NegativadorRetornoMotivo.class.getName());
				}
				
				if (colecaoNegativadorRetornoMotivoPesquisa != null
						&& !colecaoNegativadorRetornoMotivoPesquisa.isEmpty()) {
					colecaoMotivoRejeicao.addAll(colecaoNegativadorRetornoMotivoPesquisa);
				}
				
			} else {
				negativadorRetornoMotivoColecao.setDescricaoRetornocodigo("");
				colecaoMotivoRejeicao.add(negativadorRetornoMotivoColecao);
			}
			
			
			if(form.getIndicadorApenasNegativacoesRejeitadas() != null &&
					!form.getIndicadorApenasNegativacoesRejeitadas().equals("")){
				indicadorApenasNegativacoesRejeitadas = new Short(form.getIndicadorApenasNegativacoesRejeitadas());
			}
		
		}
		
		//*****************************************************
		// RM4036
		// Por: Ivan Sergio
		// Data: 04/02/2011
		//*****************************************************
		if (form.getTipoRelatorioNegativacao() != null &&
				form.getTipoRelatorioNegativacao().equals("" + ConstantesSistema.NAO)) {
			// SINTETICO
			
			String tipoRelatorio = null;
			String constanteRelatorio = null;
			
			//parametros = (DadosConsultaNegativacaoHelper) sessao.getAttribute("parametros");
			
			if(parametros == null){
				// seta os parametros que ser�o mostrados no relat�rio
				parametros= validarGeracaoRelatorio(colecaoNegativador, periodoEnvioNegativacaoInicio, periodoEnvioNegativacaoFim,
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
														colecaoMotivoRejeicao,
														indicadorApenasNegativacoesRejeitadas,
														colecaoLigacaoAguaSituacao,
														colecaoLigacaoEsgotoSituacao,
														form.getFormatoRelatorioNegativacao());
				
				tipoRelatorio = form.getFormatoRelatorioNegativacao();
			} else {
				tipoRelatorio = parametros.getFormatoRelatorioNegativacao();
			}
			// Fim da parte que vai mandar os parametros para o relat�rio
			
			if (tipoRelatorio != null && tipoRelatorio.equals(TarefaRelatorio.TIPO_XLS + "")) {
				constanteRelatorio = ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_CLIENTES_NEGATIVADOS_SINTETICO_EXCEL;
				tipoRelatorio = TarefaRelatorio.TIPO_XLS + "";
			}
			else{
				constanteRelatorio = ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_CLIENTES_NEGATIVADOS_SINTETICO_PDF;
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}

			TarefaRelatorio relatorio = new RelatorioAcompanhamentoClientesNegativadosSintetico(
					(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"), constanteRelatorio);
			
			relatorio.addParametro("parametros",parametros);
			relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
			
			sessao.setAttribute("parametros", parametros);			

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
		}else {
			// ANALITICO
			
			
			// chama o met�do de gerar relat�rio passando o c�digo da analise
			// como par�metro
			String tipoRelatorio = form.getFormatoRelatorioNegativacao();
			String constanteRelatorio = null;
			if (tipoRelatorio != null && tipoRelatorio.equals(TarefaRelatorio.TIPO_XLS + "")) {
				constanteRelatorio = ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_CLIENTES_NEGATIVADOS_EXCEL;
				tipoRelatorio = TarefaRelatorio.TIPO_XLS + "";
			}
			else{
				constanteRelatorio = ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_CLIENTES_NEGATIVADOS_PDF;
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}
			
			// cria uma inst�ncia da classe do relat�rio
			RelatorioAcompanhamentoClientesNegativados relatorio = new RelatorioAcompanhamentoClientesNegativados(
					(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"), constanteRelatorio);
			
			//parametros = (DadosConsultaNegativacaoHelper) sessao.getAttribute("parametros");
			
			//if(parametros == null){			
				// seta os parametros que ser�o mostrados no relat�rio
				parametros= validarGeracaoRelatorio(colecaoNegativador, periodoEnvioNegativacaoInicio, periodoEnvioNegativacaoFim,
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
														colecaoMotivoRejeicao,
														indicadorApenasNegativacoesRejeitadas,
														colecaoLigacaoAguaSituacao,
														colecaoLigacaoEsgotoSituacao,
														null);
			//}
			// Fim da parte que vai mandar os parametros para o relat�rio

			relatorio.addParametro("parametros",parametros);
			
			sessao.setAttribute("parametros", parametros);	
			
			

			relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
			
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
		}
		
		// devolve o mapeamento contido na vari�vel retorno
		return retorno;
	}
	
	
	private DadosConsultaNegativacaoHelper validarGeracaoRelatorio(
										 Collection collNegativador,
										 Date periodoEnvioNegativacaoInicio,
										 Date periodoEnvioNegativacaoFim,
										 String tituloComando,
										 Integer idEloPolo,
										 Integer idLocalidade,
										 Integer idSetorComercial,
										 Integer idQuadra,
										 Collection collCobrancaGrupo,
										 Collection collGerenciaRegional,
										 Collection collUnidadeNegocio, 
										 Collection collImovelPerfil,
										 Collection collCategoria,
										 Collection collTipoCliente,
										 Collection collEsferaPoder,
										 Collection collMotivoRejeicao,
										 Short indicadorApenasNegativacoesRejeitadas,
										 Collection collLigacaoAguaSituacao,
										 Collection collLigacaoEsgotoSituacao,
										 String formatoRelatorioNegativacao) {

				
		Fachada fachada = Fachada.getInstancia();

		// Inicio da parte que vai mandar os parametros para o relat�rio
		DadosConsultaNegativacaoHelper helper = new DadosConsultaNegativacaoHelper();
		
		//********************************************************
		// RM3755
		// Autor: Ivan Sergio
		// Data: 12/01/2011
		//********************************************************
//		if (idNegativador != null && !idNegativador.equals("")) {
//				FiltroNegativador filtroNegativador = new FiltroNegativador();
//
//				filtroNegativador.adicionarParametro(new ParametroSimples(
//						FiltroNegativador.ID, idNegativador));
//				
//				filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("cliente");			
//
//				Collection collNegativador = fachada.pesquisar(filtroNegativador,
//						Negativador.class.getName());
//
//				if (Util.isVazioOrNulo(collNegativador)) {					
//					throw new ActionServletException(
//							"atencao.pesquisa_inexistente", null, "Negativador");
//				}
//		}
		//********************************************************
			
		if (idEloPolo != null && !idEloPolo.equals("")) {			
				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID_ELO, idEloPolo));
				
				Collection collLocalidade = Fachada.getInstancia().pesquisar(filtroLocalidade, Localidade.class.getName());

				if (Util.isVazioOrNulo(collLocalidade)) {					
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null, "EloPolo");
				}
		}
		
		if (idLocalidade != null && !idLocalidade.equals("")) {		
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidade));
			
			Collection collLocalidade = Fachada.getInstancia().pesquisar(filtroLocalidade, Localidade.class.getName());

			if (Util.isVazioOrNulo(collLocalidade)) {			
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Localidade");
			}
	}
		

		if (idSetorComercial != null && !idSetorComercial.equals("")) {
			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE,idLocalidade));
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, idSetorComercial));
			
			Collection collSetorComercial = Fachada.getInstancia().pesquisar(filtroSetorComercial, SetorComercial.class.getName());

			if (Util.isVazioOrNulo(collSetorComercial)) {		
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Setor Comercial");
			}
	}
		
	
		
		if (idQuadra != null && !idQuadra.equals("")) {
			
			FiltroQuadra filtroQuadra = new FiltroQuadra();
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE,idLocalidade));
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL,idSetorComercial));
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID,idQuadra));
			
			Collection collQuadra = Fachada.getInstancia().pesquisar(filtroQuadra, Quadra.class.getName());

			if (Util.isVazioOrNulo(collQuadra)) {				
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Quadra");
			}
	  } 
		
    		
		//helper.setIdNegativador(new Integer(idNegativador));
		helper.setColecaoNegativador(collNegativador);
		helper.setPeriodoEnvioNegativacaoInicio(periodoEnvioNegativacaoInicio);
		helper.setPeriodoEnvioNegativacaoFim(periodoEnvioNegativacaoFim);
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
		helper.setColecaoMotivoRejeicao(collMotivoRejeicao);
		helper.setIndicadorApenasNegativacoesRejeitadas(indicadorApenasNegativacoesRejeitadas);
		helper.setColecaoLigacaoAguaSituacao(collLigacaoAguaSituacao);
		helper.setColecaoLigacaoEsgotoSituacao(collLigacaoEsgotoSituacao);
		helper.setFormatoRelatorioNegativacao(formatoRelatorioNegativacao);

		//Verifica se a pesquisa retorno algum resultado
		int qtdeResultados = fachada.pesquisarRelatorioAcompanhamentoClientesNegativadorCount(helper);
		
		if (qtdeResultados == 0) {
			// Caso a pesquisa n�o retorne nenhum resultado comunica ao usu�rio;
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}else{
			return helper;
		}

	}
	
	
}
