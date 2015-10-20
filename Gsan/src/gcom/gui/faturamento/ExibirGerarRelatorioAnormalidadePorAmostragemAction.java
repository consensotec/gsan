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
package gcom.gui.faturamento;


import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
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
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.consumo.ConsumoAnormalidade;
import gcom.micromedicao.consumo.FiltroConsumoAnormalidade;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.micromedicao.medicao.MedicaoTipo;
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
 * 
 * Este caso de uso gera relatorio de anormalidade Informadas - Amostragem
 * 
 * [UC1050]  Filtrar Anormalidade Informadas - Amostragem
 * 
 * @author Hugo Leonardo
 * @date 06/08/2010
 * 
 */

public class ExibirGerarRelatorioAnormalidadePorAmostragemAction extends GcomAction {

	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

			ActionForward retorno = actionMapping.findForward("gerarRelatorioAnormalidadePorAmostragem");
	
			Fachada fachada = Fachada.getInstancia();
			
			GerarRelatorioAnormalidadePorAmostragemActionForm form = 
								(GerarRelatorioAnormalidadePorAmostragemActionForm)actionForm;
	
			HttpSession sessao = httpServletRequest.getSession(false);
			
			
			 //--------- SETANDO FOCO INICIAL E CARREGANDO AS COLE��ES QUE SER�O MOSTRADAS NA P�GINA INICIAL
						              
			if (httpServletRequest.getParameter("menu")!= null && 
											!httpServletRequest.getParameter("menu").equals("")){
				
				httpServletRequest.setAttribute("nomeCampo", "regional");
				form.setIndicadorOcorrenciasIguais("2");
				form.setTipoMedicao(MedicaoTipo.LIGACAO_AGUA.toString());
				
				//----- [FS0001 - VERIFICAR EXISTENCIA DE DADOS] --------  GRUPO
				
				FiltroFaturamentoGrupo filtroFaturamentoGrupo = 
					new FiltroFaturamentoGrupo(FiltroFaturamentoGrupo.ID);
				filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples( 
						FiltroFaturamentoGrupo.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
				
				Collection<FaturamentoGrupo> colecaoFaturamentoGrupo = fachada.pesquisar(
						filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
		
				if (colecaoFaturamentoGrupo == null || colecaoFaturamentoGrupo.isEmpty()) {
					
					throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Faturamento Grupo");
				}
		
				sessao.setAttribute("colecaoFaturamentoGrupo", colecaoFaturamentoGrupo);
				
				//-------[FS0001 - VERIFICAR EXISTENCIA DE DADOS] -------- UNIDADE DE NEGOCIO 

				FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
				filtroUnidadeNegocio.adicionarParametro(new ParametroSimples( 
						FiltroUnidadeNegocio.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
				Collection<UnidadeNegocio> colecaoUnidadeNegocio = fachada.pesquisar(
						filtroUnidadeNegocio, UnidadeNegocio.class.getName());
		
				if (colecaoUnidadeNegocio == null || colecaoUnidadeNegocio.isEmpty()) {
					
					throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Unidade Neg�cio");
				}
				
				sessao.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
				
				//-------[FS0001 - VERIFICAR EXISTENCIA DE DADOS]  ----  GERENCIA REGIONAL
				
				FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
				filtroGerenciaRegional.adicionarParametro(new ParametroSimples( 
						FiltroGerenciaRegional.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
				Collection<GerenciaRegional> colecaoGerenciaRegional = fachada.pesquisar(
						filtroGerenciaRegional, GerenciaRegional.class.getName());
		
				if (colecaoGerenciaRegional == null || colecaoGerenciaRegional.isEmpty()) {
					
					throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Ger�ncia Regional");
				}
				
				sessao.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);
				
				
				//-------[FS0001 - VERIFICAR EXISTENCIA DE DADOS] -------- PERFIL DO IMOVEL 

				FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
				filtroImovelPerfil.adicionarParametro(new ParametroSimples( 
						FiltroImovelPerfil.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
				Collection<ImovelPerfil> colecaoImovelPerfil = fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
		
				if (colecaoImovelPerfil == null || colecaoImovelPerfil.isEmpty()) {
					
					throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Im�vel Perfil");
				}
		
				sessao.setAttribute("colecaoImovelPerfil", colecaoImovelPerfil);
				
				//-------[FS0001 - VERIFICAR EXISTENCIA DE DADOS] -------- EMPRESA
				
				FiltroEmpresa filtroEmpresa = new FiltroEmpresa(FiltroEmpresa.DESCRICAO);
				filtroEmpresa.adicionarParametro(new ParametroSimples( FiltroEmpresa.INDICADOR_LEITURA,ConstantesSistema.SIM));
				
				Collection<Empresa> colecaoEmpresa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
		
				if (colecaoEmpresa == null || colecaoEmpresa.isEmpty()) {
					
					throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Empresa");
				}
		
				sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
							
				// -------[FS0001 - VERIFICAR EXISTENCIA DE DADOS] -------- CONSUMO ANORMALIDADE 

				FiltroConsumoAnormalidade filtroConsumoAnormalidade = new FiltroConsumoAnormalidade(FiltroConsumoAnormalidade.DESCRICAO);
				filtroConsumoAnormalidade.adicionarParametro(new ParametroSimples( 
						FiltroConsumoAnormalidade.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
				Collection<ConsumoAnormalidade> colecaoConsumoAnormalidade = fachada.pesquisar(filtroConsumoAnormalidade, ConsumoAnormalidade.class.getName());
		
				if (colecaoConsumoAnormalidade == null || colecaoConsumoAnormalidade.isEmpty()) {
					
					throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Consumo Anormalidade");
				}
		
				sessao.setAttribute("colecaoConsumoAnormalidade", colecaoConsumoAnormalidade);
				
				// -------[FS0001 - VERIFICAR EXISTENCIA DE DADOS] -------- CONSUMO ANORMALIDADE 

				FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade(FiltroLeituraAnormalidade.DESCRICAO);
				filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(FiltroLeituraAnormalidade.INDICADOR_USO, 
						ConstantesSistema.INDICADOR_USO_ATIVO));
				Collection<LeituraAnormalidade> colecaoLeituraAnormalidade = fachada.pesquisar(filtroLeituraAnormalidade, LeituraAnormalidade.class.getName());
		
				if (colecaoLeituraAnormalidade == null || colecaoLeituraAnormalidade.isEmpty()) {
					
					throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Leitura Anormalidade");
				}
		
				sessao.setAttribute("colecaoLeituraAnormalidade", colecaoLeituraAnormalidade);
				
				FiltroCategoria filtroCategoria = new FiltroCategoria();
				filtroCategoria.setConsultaSemLimites(true);
				filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.INDICADOR_USO, 
						ConstantesSistema.INDICADOR_USO_ATIVO));
				Collection colecaoCategoria = 
					this.getFachada().pesquisar(filtroCategoria, Categoria.class.getName());
				
				if(colecaoCategoria == null || colecaoCategoria.isEmpty()){
					throw new ActionServletException("atencao.naocadastrado", null, "Categorias");
				}else{
					sessao.setAttribute("colecaoCategoria", colecaoCategoria);
				}
				
			}else if (httpServletRequest.getParameter("reload")!= null && 
					!httpServletRequest.getParameter("reload").equals("")){
								
				if (form.getRegional() != null && !form.getRegional().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
					
					FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
					filtroUnidadeNegocio.adicionarParametro(new ParametroSimples( 
							FiltroUnidadeNegocio.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
					filtroUnidadeNegocio.adicionarParametro(new ParametroSimples( 
							FiltroUnidadeNegocio.GERENCIA, form.getRegional()));
					
					Collection<UnidadeNegocio> colecaoUnidadeNegocio = fachada.pesquisar(
							filtroUnidadeNegocio, UnidadeNegocio.class.getName());

					sessao.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
					
				}else{
					
					FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
					filtroUnidadeNegocio.adicionarParametro(new ParametroSimples( 
							FiltroUnidadeNegocio.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
					
					Collection<UnidadeNegocio> colecaoUnidadeNegocio = fachada.pesquisar(
							filtroUnidadeNegocio, UnidadeNegocio.class.getName());
			
					if (colecaoUnidadeNegocio == null || colecaoUnidadeNegocio.isEmpty()) {
						
						throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Unidade Neg�cio");
					}
					
					sessao.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
				}
			}

			//---------- [FS0003 - VERIFICAR EXISTENCIA DA LOCALIDADE]
			
			String idLocalidadeInicialForm = form.getIdLocalidadeInicial();

			if (idLocalidadeInicialForm != null && !idLocalidadeInicialForm.equals("")) {
				
				FiltroLocalidade filtroLocalidadeOrigem = new FiltroLocalidade();
				filtroLocalidadeOrigem.adicionarParametro(new ParametroSimples( FiltroLocalidade.INDICADORUSO,ConstantesSistema.INDICADOR_USO_ATIVO));
				filtroLocalidadeOrigem.adicionarParametro(new ParametroSimples( FiltroLocalidade.ID, new Integer(idLocalidadeInicialForm)));
				Collection colecaoLocalidadeOrigem = fachada.pesquisar(filtroLocalidadeOrigem, Localidade.class.getName());
				
				if (colecaoLocalidadeOrigem != null && !colecaoLocalidadeOrigem.isEmpty()) {
					
					Localidade localidadeOrigem = (Localidade)colecaoLocalidadeOrigem.iterator().next();
					form.setIdLocalidadeInicial(""+localidadeOrigem.getId());
					form.setNomeLocalidadeInicial(localidadeOrigem.getDescricao());
					httpServletRequest.setAttribute("nomeCampo", "idLocalidadeFinal");
					
				} else {
					form.setIdLocalidadeInicial("");
					form.setNomeLocalidadeInicial("LOCALIDADE INEXISTENTE");
					httpServletRequest.setAttribute("localidadeInicialInexistente",true);
					httpServletRequest.setAttribute("nomeCampo", "idLocalidadeInicial");
				}
			} else {
				form.setNomeLocalidadeInicial("");
			}

			String idLocalidadeFinalForm = form.getIdLocalidadeFinal();

			if (idLocalidadeFinalForm != null && !idLocalidadeFinalForm.equals("")) {

				FiltroLocalidade filtroLocalidadeDestino = new FiltroLocalidade();
				filtroLocalidadeDestino.adicionarParametro(new ParametroSimples( FiltroLocalidade.INDICADORUSO,ConstantesSistema.INDICADOR_USO_ATIVO));
				filtroLocalidadeDestino.adicionarParametro(new ParametroSimples( FiltroLocalidade.ID, new Integer(idLocalidadeFinalForm)));
				Collection colecaoLocalidadeDestino = fachada.pesquisar(filtroLocalidadeDestino, Localidade.class.getName());
				
				if (colecaoLocalidadeDestino != null && !colecaoLocalidadeDestino.isEmpty()) {
					
					Localidade localidadeDestino = (Localidade)colecaoLocalidadeDestino.iterator().next();
					form.setIdLocalidadeFinal(""+localidadeDestino.getId());
					form.setNomeLocalidadeFinal(localidadeDestino.getDescricao());
					httpServletRequest.setAttribute("nomeCampo", "referencia");
				} else {
					
					form.setIdLocalidadeFinal("");
					form.setNomeLocalidadeFinal("LOCALIDADE INEXISTENTE");
					httpServletRequest.setAttribute("localidadeFinalInexistente",true);
					httpServletRequest.setAttribute("nomeCampo", "idLocalidadeFinal");
				}
			} else {
				
				form.setNomeLocalidadeFinal("");
			}
			
			String codigoSetorComercialInicial = form.getCodigoSetorComercialInicial();
			if (codigoSetorComercialInicial != null && !codigoSetorComercialInicial.equals("") &&
				idLocalidadeInicialForm != null && !idLocalidadeInicialForm.equals("")) {
				
				FiltroSetorComercial filtro = new FiltroSetorComercial();
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercialInicial));
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, idLocalidadeInicialForm));
				Collection pesquisa = (Collection) fachada.pesquisar(filtro, SetorComercial.class.getName());
				
				if (pesquisa != null && !pesquisa.isEmpty()) {
					SetorComercial inicial = (SetorComercial) Util.retonarObjetoDeColecao(pesquisa);
					form.setCodigoSetorComercialInicial(inicial.getCodigo() + "");
					form.setNomeSetorComercialInicial(inicial.getDescricao());
					form.setIdSetorComercialInicial(inicial.getId().toString());
				} else {
					form.setCodigoSetorComercialInicial("");
					form.setIdSetorComercialInicial("");
					form.setNomeSetorComercialInicial("SETOR COMERCIAL INEXISTENTE");
					httpServletRequest.setAttribute("setorComercialInicialInexistente",true);
				}
			} else {
				form.setCodigoSetorComercialInicial("");
				form.setNomeSetorComercialInicial("");
				form.setIdSetorComercialInicial("");
			}
			
			String codigoSetorComercialFinal = form.getCodigoSetorComercialFinal();
			if (codigoSetorComercialFinal != null && !codigoSetorComercialFinal.equals("") &&
				idLocalidadeFinalForm != null && !idLocalidadeFinalForm.equals("")) {
				
				FiltroSetorComercial filtro = new FiltroSetorComercial();
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercialFinal));
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, idLocalidadeFinalForm));
				Collection pesquisa = (Collection) fachada.pesquisar(filtro, SetorComercial.class.getName());
				
				if (pesquisa != null && !pesquisa.isEmpty()) {
					SetorComercial Final = (SetorComercial) Util.retonarObjetoDeColecao(pesquisa);
					form.setCodigoSetorComercialFinal(Final.getCodigo() + "");
					form.setNomeSetorComercialFinal(Final.getDescricao());
					form.setIdSetorComercialFinal(Final.getId().toString());
				} else {
					form.setCodigoSetorComercialFinal("");
					form.setIdSetorComercialFinal("");
					form.setNomeSetorComercialFinal("SETOR COMERCIAL INEXISTENTE");
					httpServletRequest.setAttribute("setorComercialFinalInexistente",true);
				}
			} else {
				form.setCodigoSetorComercialFinal("");
				form.setNomeSetorComercialFinal("");
				form.setIdSetorComercialFinal("");
			}
			
			pesquisarQuadra(form , fachada, httpServletRequest);
			
			validacaoFinal(form);
			
			// --------------- [FS0004 - VALIDAR M�S/ANO REFER�NCIA]
			
			if (form.getReferencia()!= null && !form.getReferencia().equals("")){
				
				FiltroSistemaParametro filtroSistemaParametro= new FiltroSistemaParametro();
				Collection colecaoSistemaParametro = fachada.pesquisar(filtroSistemaParametro, SistemaParametro.class.getName());
				
				if (colecaoSistemaParametro != null && !colecaoSistemaParametro.isEmpty()) {
					
					SistemaParametro sistemaParametro = (SistemaParametro) colecaoSistemaParametro.iterator().next();
					String anoMesReferencia = Util.formatarMesAnoParaAnoMesSemBarra(form.getReferencia());
					String anoMesFaturamentoCorrente = ""+ sistemaParametro.getAnoMesFaturamento();
					
					Integer resultado = anoMesReferencia.compareTo(anoMesFaturamentoCorrente);
					
					if (resultado >= 0){
						
						httpServletRequest.setAttribute("nomeCampo", "referencia");	
						throw new ActionServletException( "atencao.ano_mes_referencia_anterior_que_ano_mes_faturamento_corrente",
								null, Util.formatarAnoMesParaMesAno(sistemaParametro.getAnoMesFaturamento()));
					}
				}
			}
			
			// ---------- QTD DO NUMERO DE OCORR�NCIAS ------
			
			if (form.getNumOcorrenciasConsecutivas()!= null 
								&& !form.getNumOcorrenciasConsecutivas().equals("")){
				
				if (new Integer(form.getNumOcorrenciasConsecutivas())> 12){
					
					throw new ActionServletException("atencao.quantidade_ocorrencia_maior", null,"");
				}
			}
			
			if(form.getAmostragem()==null){
				form.setAmostragem("10");
			}

			return retorno;
	}
	
	private void pesquisarQuadra( GerarRelatorioAnormalidadePorAmostragemActionForm form,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		Collection colecaoPesquisa = null;
		String setorComercialCD = null;
		String setorComercialID = null;
		String quadraNM = null;
		FiltroQuadra filtroQuadra = new FiltroQuadra();

		if(form.getQuadraInicialNM() != null &&
				!form.getQuadraInicialNM().equals("")){
			
			// Recebe os valores dos campos setorComercialOrigemCD e
			// setorComercialOrigemID do formul�rio.
			setorComercialCD = (String) form.getCodigoSetorComercialInicial();
			setorComercialID = (String) form.getIdSetorComercialInicial();

			if (setorComercialCD != null
					&& !setorComercialCD.trim().equalsIgnoreCase("")
					&& setorComercialID != null
					&& !setorComercialID.trim().equalsIgnoreCase("")) {

				quadraNM = (String) form.getQuadraInicialNM();

				// Adiciona o id do setor comercial que est� no formul�rio para compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_SETORCOMERCIAL, setorComercialID));

				// Adiciona o n�mero da quadra que esta no formul�rio para compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.NUMERO_QUADRA, quadraNM));

				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna quadra
				colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Quadra nao encontrada
					// Limpa os campos quadraOrigemNM e quadraOrigemID do formul�rio
					form.setQuadraInicialNM("");
					form.setQuadraInicialID("");
					// Mensagem de tela
					form.setQuadraMensagemInicial("QUADRA INEXISTENTE.");
					httpServletRequest.setAttribute("corQuadraOrigem",	"exception");
					httpServletRequest.setAttribute("nomeCampo", "quadraInicialNM");
				} else {
					Quadra objetoQuadra = (Quadra) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					form.setQuadraInicialNM(String.valueOf(objetoQuadra.getNumeroQuadra()));
					form.setQuadraInicialID(String.valueOf(objetoQuadra.getId()));
					httpServletRequest.setAttribute("corQuadraOrigem", "valor");
					
					if(form.getQuadraFinalNM() == null || form.getQuadraFinalNM().equals("")){
						form.setQuadraFinalNM(String.valueOf(objetoQuadra.getNumeroQuadra()));
						form.setQuadraFinalID(String.valueOf(objetoQuadra.getId()));
						httpServletRequest.setAttribute("corQuadraDestino", "valor");
					}
					
				}
			} else {
				// Limpa o campo quadraOrigemNM do formul�rio
				form.setQuadraInicialNM("");
				form.setQuadraMensagemInicial("Informe o setor comercial inicial.");
				httpServletRequest.setAttribute("corQuadraOrigem", "exception");
				httpServletRequest.setAttribute("nomeCampo", "codigoSetorComercialInicial");
			}
		}
		
		if(form.getQuadraFinalNM() != null &&
				!form.getQuadraFinalNM().equals("")){
			//Recebe os valores dos campos setorComercialOrigemCD e
			// setorComercialOrigemID do formul�rio.
			setorComercialCD = (String) form.getCodigoSetorComercialFinal();
			setorComercialID = (String) form.getIdSetorComercialFinal();

			// Os campos setorComercialOrigemCD e setorComercialID ser�o
			// obrigat�rios
			if (setorComercialCD != null
					&& !setorComercialCD.trim().equalsIgnoreCase("")
					&& setorComercialID != null
					&& !setorComercialID.trim().equalsIgnoreCase("")) {

				quadraNM = (String) form.getQuadraFinalNM();

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
					form.setQuadraFinalNM("");
					form.setQuadraFinalID("");
					// Mensagem de tela
					form.setQuadraMensagemFinal("QUADRA INEXISTENTE.");
					httpServletRequest.setAttribute("corQuadraDestino",	"exception");
					httpServletRequest.setAttribute("nomeCampo", "quadraFinalNM");
				} else {
					Quadra objetoQuadra = (Quadra) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					form.setQuadraFinalNM(String.valueOf(objetoQuadra.getNumeroQuadra()));
					form.setQuadraFinalID(String.valueOf(objetoQuadra.getId()));
					httpServletRequest.setAttribute("corQuadraDestino", "valor");
				}
			} else {
				// Limpa o campo setorComercialOrigemCD do formul�rio
				form.setQuadraFinalNM("");
				// Mensagem de tela
				form.setQuadraMensagemFinal("Informe o setor comercial final.");
				httpServletRequest.setAttribute("corQuadraDestino", "exception");
				httpServletRequest.setAttribute("nomeCampo","codigoSetorComercialFinal");
			}
		}
	}
	
	private void validacaoFinal(GerarRelatorioAnormalidadePorAmostragemActionForm form) {

		// validar localidade inicial sendo maior que localidade final
		if (form.getIdLocalidadeInicial() != null
				&& form.getIdLocalidadeFinal() != null) {
			if (!form.getIdLocalidadeInicial().equals("")
					&& !form.getIdLocalidadeFinal().equals("")) {
				
				int origem = Integer.parseInt(form.getIdLocalidadeInicial());
				int destino = Integer.parseInt(form.getIdLocalidadeFinal());
				
				if (origem > destino) {
					throw new ActionServletException(
					"atencao.localidade.final.maior.localidade.inicial",null, "");
				}
			}
		}

		// validar setor comercial sendo maior que localidade final
		if (form.getCodigoSetorComercialInicial() != null
				&& form.getCodigoSetorComercialFinal() != null) {
			if (!form.getCodigoSetorComercialInicial().equals("")
					&& !form.getCodigoSetorComercialFinal().equals("")) {
				
				int origem = Integer.parseInt(form.getCodigoSetorComercialInicial());
				int destino = Integer.parseInt(form.getCodigoSetorComercialFinal());
				
				if (origem > destino) {
					throw new ActionServletException(
							"atencao.setor.comercial.final.maior.setor.comercial.inicial",
							null, "");
				}
			}
		}

		// validar quadra sendo maior que localidade final
		if (form.getQuadraInicialNM() != null
				&& form.getQuadraFinalNM() != null) {
			if (!form.getQuadraInicialNM().equals("")
					&& !form.getQuadraFinalNM().equals("")) {
				
				int origem = Integer.parseInt(form.getQuadraInicialNM());
				int destino = Integer.parseInt(form.getQuadraFinalNM());
				
				if (origem > destino)
					throw new ActionServletException(
							"atencao.quadra.final.maior.quadra.inical", null,"");
			}
		}
		
	}
	
}
