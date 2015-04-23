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
* Ivan Sérgio da Silva Júnior
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
package gsan.gui.gerencial;

import gsan.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gsan.cadastro.cliente.EsferaPoder;
import gsan.cadastro.cliente.FiltroEsferaPoder;
import gsan.cadastro.geografico.FiltroMunicipio;
import gsan.cadastro.geografico.Municipio;
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
import gsan.fachada.Fachada;
import gsan.faturamento.FaturamentoGrupo;
import gsan.faturamento.FiltroFaturamentoGrupo;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.micromedicao.FiltroRota;
import gsan.micromedicao.Rota;
import gsan.util.ConstantesInterfaceGSAN;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Esta classe tem por finalidade exibir para o usuário a tela que receberá os parâmetros para informar
 * os dados para geração de relatório/consulta. Alteração para poder ser consultada por um determinado 
 * período no ano.
 *
 * @author Raphael Rossiter, Davi Menezes
 * @date 23/02/2006, 30/09/2011
 */
public class ExibirInformarDadosGeracaoRelatorioConsultaPeriodoAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("exibirInformarDadosGeracaoRelatorioConsultaPeriodo");
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        Fachada fachada = Fachada.getInstancia();
        
        /*
         * Informa os códigos das opções de totalização que irão direcionar o caso de uso para um JSP
         */
        httpServletRequest.setAttribute("jsp", "1,6,7,10,13,16,18");
        
        
        /**
         * na chamada do menu é passado um parametro com o tipo do resumo
         * para o informar saber que caso de uso será chamado apartir dele.   
         */
        String tipoResumo = httpServletRequest.getParameter("tipoResumo");
        
        String grupo = httpServletRequest.getParameter("grupo");
        
        if (tipoResumo != null && !tipoResumo.equals("")){
        	sessao.setAttribute("tipoResumo", tipoResumo);
        }
        
        if (grupo != null && !grupo.equals("")){
        	sessao.setAttribute("cobranca", grupo);
        }
        
        
        InformarDadosGeracaoRelatorioConsultaPeriodoActionForm informarDadosGeracaoRelatorioConsultaPeriodoActionForm = 
        (InformarDadosGeracaoRelatorioConsultaPeriodoActionForm) actionForm;
        
        String analiseFaturamento = httpServletRequest.getParameter("analiseFaturamento");
        String limparForm = httpServletRequest.getParameter("limparForm");
        
        
        //Carregango as coleções que servirão como opção de escolha para o usuário
        
        //GRUPO DE FATURAMENTO
        if (sessao.getAttribute("colecaoFaturamentoGrupo") == null){
        	
        	FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo(FiltroFaturamentoGrupo.DESCRICAO_ABREVIADA);
        	filtroFaturamentoGrupo.setConsultaSemLimites(true);
        	
        	filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.INDICADOR_USO,
        	ConstantesSistema.INDICADOR_USO_ATIVO));
        	
        	Collection<FaturamentoGrupo> colecaoGrupoFaturamento = fachada.pesquisar(filtroFaturamentoGrupo, 
        	FaturamentoGrupo.class.getName());
        	
        	if (colecaoGrupoFaturamento == null || colecaoGrupoFaturamento.isEmpty()){
        		throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, "FATURAMENTO_GRUPO");
        	}
        	
        	sessao.setAttribute("colecaoFaturamentoGrupo", colecaoGrupoFaturamento);
        }
        
        //GRUPO DE COBRANCA
        if (sessao.getAttribute("colecaoGrupoCobranca") == null){
        	
        	FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo(FiltroCobrancaGrupo.DESCRICAO_ABREVIADA);
        	filtroCobrancaGrupo.setConsultaSemLimites(true);
        	
        	filtroCobrancaGrupo.adicionarParametro(new ParametroSimples(FiltroCobrancaGrupo.INDICADOR_USO,
        	ConstantesSistema.INDICADOR_USO_ATIVO));
        	
        	Collection<CobrancaGrupo> colecaoGrupoCobranca = fachada.pesquisar(filtroCobrancaGrupo, 
        	CobrancaGrupo.class.getName());
        	
        	if (colecaoGrupoCobranca == null || colecaoGrupoCobranca.isEmpty()){
        		throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, "COBRANCA_GRUPO");
        	}
        	
        	sessao.setAttribute("colecaoGrupoCobranca", colecaoGrupoCobranca);
        }        
        
        //GERÊNCIA REGIONAL
        if (sessao.getAttribute("colecaoGerenciaRegional") == null){
        	
        	FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional(FiltroGerenciaRegional.NOME);
        	filtroGerenciaRegional.setConsultaSemLimites(true);
        	
        	filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.INDICADOR_USO,
        	ConstantesSistema.INDICADOR_USO_ATIVO));
        	
        	Collection<GerenciaRegional> colecaoGerenciaRegional = fachada.pesquisar(filtroGerenciaRegional, 
        	GerenciaRegional.class.getName());
        	
        	if (colecaoGerenciaRegional == null || colecaoGerenciaRegional.isEmpty()){
        		throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, "GERENCIA_REGIONAL");
        	}
        	
        	sessao.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);
        }
        
        //UNIDADE NEGOCIO
        if (sessao.getAttribute("colecaoUnidadeNegocio") == null){
        	
        	FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
    		filtroUnidadeNegocio.setConsultaSemLimites(true);
    		filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);		
    		filtroUnidadeNegocio.adicionarParametro(
    				new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO, 
    				ConstantesSistema.INDICADOR_USO_ATIVO));		

    		Collection<UnidadeNegocio> colecaoUnidadeNegocio = 
    			this.getFachada().pesquisar(filtroUnidadeNegocio,UnidadeNegocio.class.getName());

    		if ( Util.isVazioOrNulo(colecaoUnidadeNegocio)) {
    			throw new ActionServletException(ConstantesInterfaceGSAN.ATENCAO_NAO_CADASTRADO, 
    					ConstantesInterfaceGSAN.LABEL_GSAN_UNIDADE_NEGOCIO);
    		}
    		sessao.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
        }
        
        //PERFIL IMÓVEL
        if (sessao.getAttribute("colecaoImovelPerfil") == null){
        	
        	FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil(FiltroImovelPerfil.DESCRICAO);
        	filtroImovelPerfil.setConsultaSemLimites(true);
        	
        	filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.INDICADOR_USO,
        	ConstantesSistema.INDICADOR_USO_ATIVO));
        	
        	Collection<ImovelPerfil> colecaoImovelPerfil = fachada.pesquisar(filtroImovelPerfil, 
        	ImovelPerfil.class.getName());
        	
        	if (colecaoImovelPerfil == null || colecaoImovelPerfil.isEmpty()){
        		throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, "IMOVEL_PERFIL");
        	}
        	
        	sessao.setAttribute("colecaoImovelPerfil", colecaoImovelPerfil);
        }
        
        //SITUAÇÃO LIGAÇÃO ÁGUA
        if (sessao.getAttribute("colecaoLigacaoAguaSituacao") == null){
        	
        	FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao(FiltroLigacaoAguaSituacao.DESCRICAO);
        	filtroLigacaoAguaSituacao.setConsultaSemLimites(true);
        	
        	filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoAguaSituacao.INDICADOR_USO,
        	ConstantesSistema.INDICADOR_USO_ATIVO));
        	
        	Collection<LigacaoAguaSituacao> colecaoLigacaoAguaSituacao = fachada.pesquisar(filtroLigacaoAguaSituacao, 
        	LigacaoAguaSituacao.class.getName());
        	
        	if (colecaoLigacaoAguaSituacao == null || colecaoLigacaoAguaSituacao.isEmpty()){
        		throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, "LIGACAO_AGUA_SITUACAO");
        	}
        	
        	sessao.setAttribute("colecaoLigacaoAguaSituacao", colecaoLigacaoAguaSituacao);
        }
        
        //SITUAÇÃO LIGAÇÃO ESGOTO
        if (sessao.getAttribute("colecaoLigacaoEsgotoSituacao") == null){
        	
        	FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao(FiltroLigacaoEsgotoSituacao.DESCRICAO);
        	filtroLigacaoEsgotoSituacao.setConsultaSemLimites(true);
        	
        	filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgotoSituacao.INDICADOR_USO,
        	ConstantesSistema.INDICADOR_USO_ATIVO));
        	
        	Collection<LigacaoEsgotoSituacao> colecaoLigacaoEsgotoSituacao = fachada.pesquisar(filtroLigacaoEsgotoSituacao, 
        	LigacaoEsgotoSituacao.class.getName());
        	
        	if (colecaoLigacaoEsgotoSituacao == null || colecaoLigacaoEsgotoSituacao.isEmpty()){
        		throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, "LIGACAO_ESGOTO_SITUACAO");
        	}
        	
        	sessao.setAttribute("colecaoLigacaoEsgotoSituacao", colecaoLigacaoEsgotoSituacao);
        }
        
        //CATEGORIA
        if (sessao.getAttribute("colecaoCategoria") == null){
        	
        	FiltroCategoria filtroCategoria = new FiltroCategoria(FiltroCategoria.DESCRICAO);
        	filtroCategoria.setConsultaSemLimites(true);
        	
        	filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.INDICADOR_USO,
        	ConstantesSistema.INDICADOR_USO_ATIVO));
        	
        	Collection<Categoria> colecaoCategoria = fachada.pesquisar(filtroCategoria, 
        	Categoria.class.getName());
        	
        	if (colecaoCategoria == null || colecaoCategoria.isEmpty()){
        		throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, "CATEGORIA");
        	}
        	
        	sessao.setAttribute("colecaoCategoria", colecaoCategoria);
        }
        
        //ESFERA PODER
        if (sessao.getAttribute("colecaoEsferaPoder") == null){
        	
        	FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder(FiltroEsferaPoder.DESCRICAO);
        	filtroEsferaPoder.setConsultaSemLimites(true);
        	
        	filtroEsferaPoder.adicionarParametro(new ParametroSimples(FiltroEsferaPoder.INDICADOR_USO,
        	ConstantesSistema.INDICADOR_USO_ATIVO));
        	
        	Collection<EsferaPoder> colecaoEsferaPoder = fachada.pesquisar(filtroEsferaPoder, 
        	EsferaPoder.class.getName());
        	
        	if (colecaoEsferaPoder == null || colecaoEsferaPoder.isEmpty()){
        		throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, "ESFERA_PODER");
        	}
        	
        	sessao.setAttribute("colecaoEsferaPoder", colecaoEsferaPoder);
        }
        
        String pesquisarEloPolo = httpServletRequest.getParameter("pesquisarEloPolo");
        String pesquisarLocalidade = httpServletRequest.getParameter("pesquisarLocalidade");
        String pesquisarSetorComercial = httpServletRequest.getParameter("pesquisarSetorComercial");
        String pesquisarQuadra = httpServletRequest.getParameter("pesquisarQuadra");
        String pesquisarMunicipio = httpServletRequest.getParameter("pesquisarMunicipio");
        
        Collection<Object> colecaoPesquisa = null;
        
        if (pesquisarEloPolo != null && !pesquisarEloPolo.equals("") && 
        	informarDadosGeracaoRelatorioConsultaPeriodoActionForm.getEloPolo() != null &&
        	!informarDadosGeracaoRelatorioConsultaPeriodoActionForm.getEloPolo().equals("")){
        	
        	FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
        	filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("localidade");
        	
            filtroLocalidade.adicionarParametro(new ParametroSimples(
            FiltroLocalidade.ID, informarDadosGeracaoRelatorioConsultaPeriodoActionForm.getEloPolo()));

            filtroLocalidade.adicionarParametro(new ParametroSimples(
            FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

            colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
            Localidade.class.getName());
            
            if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()){
            	
            	informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setEloPolo("");
            	informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setDescricaoEloPolo("Elo Pólo inexistente");
            	httpServletRequest.setAttribute("nomeCampo", "eloPolo");
            	httpServletRequest.setAttribute("corEloPolo", "exception");
            	
            }
            else{
            	Localidade eloPlo = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
            	
            	if (eloPlo.getLocalidade() == null){
            		
            		informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setEloPolo("");
                	informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setDescricaoEloPolo("Código informado não é um Elo Pólo");
                	httpServletRequest.setAttribute("nomeCampo", "eloPolo");
                	httpServletRequest.setAttribute("corEloPolo", "exception");
                	
            	}
            	else if (!eloPlo.getLocalidade().getId().equals(
            			 new Integer(informarDadosGeracaoRelatorioConsultaPeriodoActionForm.getEloPolo()))){
            		
            		informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setEloPolo("");
                	informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setDescricaoEloPolo("Código informado não é um Elo Pólo");
                	httpServletRequest.setAttribute("nomeCampo", "eloPolo");
                	httpServletRequest.setAttribute("corEloPolo", "exception");
                	
            	}
            	else{
            		
            		informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setEloPolo(String.valueOf(eloPlo.getId()));
                	informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setDescricaoEloPolo(eloPlo.getDescricao());
                	httpServletRequest.setAttribute("nomeCampo", "perfilImovel");
            	}
            }
        }
        
        
        if (pesquisarLocalidade != null && !pesquisarLocalidade.equals("") && 
            informarDadosGeracaoRelatorioConsultaPeriodoActionForm.getLocalidade() != null &&
            !informarDadosGeracaoRelatorioConsultaPeriodoActionForm.getLocalidade().equals("")){
            	
            	FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
            	
                filtroLocalidade.adicionarParametro(new ParametroSimples(
                FiltroLocalidade.ID, informarDadosGeracaoRelatorioConsultaPeriodoActionForm.getLocalidade()));

                filtroLocalidade.adicionarParametro(new ParametroSimples(
                FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

                colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
                Localidade.class.getName());
                
                if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()){
                	
                	informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setLocalidade("");
                	informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setDescricaoLocalidade("Localidade inexistente");
                	httpServletRequest.setAttribute("nomeCampo", "localidade");
                	httpServletRequest.setAttribute("corLocalidade", "exception");
                	
                }
                else{
                	Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
                	
                	informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setLocalidade(String.valueOf(localidade.getId()));
                    informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setDescricaoLocalidade(localidade.getDescricao());
                    httpServletRequest.setAttribute("nomeCampo", "setorComercial");
                }
        }
        
        
        if (pesquisarSetorComercial != null && !pesquisarSetorComercial.equals("") && 
            informarDadosGeracaoRelatorioConsultaPeriodoActionForm.getSetorComercial() != null &&
            !informarDadosGeracaoRelatorioConsultaPeriodoActionForm.getSetorComercial().equals("")){
                	
                	FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
                	
                	filtroSetorComercial.adicionarParametro(new ParametroSimples(
                	FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, 
                	informarDadosGeracaoRelatorioConsultaPeriodoActionForm.getSetorComercial()));

                	filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.ID_LOCALIDADE, 
                    informarDadosGeracaoRelatorioConsultaPeriodoActionForm.getLocalidade()));
                	
                	filtroSetorComercial.adicionarParametro(new ParametroSimples(
                	FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

                    colecaoPesquisa = fachada.pesquisar(filtroSetorComercial,
                    SetorComercial.class.getName());
                    
                    if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()){
                    	
                    	informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setSetorComercial("");
                    	informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setDescricaoSetorComercial("Setor Comercial inexistente");
                    	httpServletRequest.setAttribute("nomeCampo", "setorComercial");
                    	httpServletRequest.setAttribute("corSetorComercial", "exception");
                    	
                    }
                    else{
                    	SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoPesquisa);
                    	
                    	informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setIdSetorComercial(String.valueOf(setorComercial.getId()));
                    	informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setSetorComercial(String.valueOf(setorComercial.getCodigo()));
                        informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setDescricaoSetorComercial(setorComercial.getDescricao());
                        httpServletRequest.setAttribute("nomeCampo", "perfilImovel");
                    }
            }

        
	        if (pesquisarQuadra != null && !pesquisarQuadra.equals("") && 
	                informarDadosGeracaoRelatorioConsultaPeriodoActionForm.getQuadra() != null &&
	                !informarDadosGeracaoRelatorioConsultaPeriodoActionForm.getQuadra().equals("")){
	                    	
	        	FiltroQuadra filtroQuadra = new FiltroQuadra();
	            filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("bairro");
	                    	
	            filtroQuadra.adicionarParametro(new ParametroSimples(
	    	    FiltroQuadra.ID_SETORCOMERCIAL, 
	    	    informarDadosGeracaoRelatorioConsultaPeriodoActionForm.getIdSetorComercial()));
	            
	            filtroQuadra.adicionarParametro(new ParametroSimples(
	            FiltroQuadra.NUMERO_QUADRA, 
	            informarDadosGeracaoRelatorioConsultaPeriodoActionForm.getQuadra()));
	
	            filtroQuadra.adicionarParametro(new ParametroSimples(
	            FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
	
	            colecaoPesquisa = fachada.pesquisar(filtroQuadra,
	            Quadra.class.getName());
	                        
	            if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()){
	                        	
	            	informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setQuadra("");
	            	httpServletRequest.setAttribute("nomeCampo", "quadra");
	            	httpServletRequest.setAttribute("msgQuadra", "QUADRA INEXISTENTE");
	                        	
	            }
	            else{
	            	Quadra quadra = (Quadra) Util.retonarObjetoDeColecao(colecaoPesquisa);
	            		
	            	informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setQuadra(String.valueOf(quadra.getNumeroQuadra()));
	            	httpServletRequest.setAttribute("nomeCampo", "perfilImovel");
	            }
	        }
	        
	        //Pesquisar Município
	        if (pesquisarMunicipio != null && !pesquisarMunicipio.equals("") && 
	                informarDadosGeracaoRelatorioConsultaPeriodoActionForm.getMunicipio() != null &&
	                !informarDadosGeracaoRelatorioConsultaPeriodoActionForm.getMunicipio().equals("")){
	        	
	        	FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
            	
                filtroMunicipio.adicionarParametro(new ParametroSimples(
                FiltroMunicipio.ID, informarDadosGeracaoRelatorioConsultaPeriodoActionForm.getMunicipio()));

                colecaoPesquisa = fachada.pesquisar(filtroMunicipio,
                Municipio.class.getName());
                
                if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()){
                	
                	informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setMunicipio("");
                	informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setDescricaoMunicipio("Município inexistente");
                	httpServletRequest.setAttribute("nomeCampo", "municipio");
                	httpServletRequest.setAttribute("corMunicipio", "exception");
                	
                }
                else{
                	Municipio municipio = (Municipio) Util.retonarObjetoDeColecao(colecaoPesquisa);
                	
                	informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setMunicipio(String.valueOf(municipio.getId()));
                    informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setDescricaoMunicipio(municipio.getNome());
                    httpServletRequest.setAttribute("nomeCampo", "municipio");
                }
	        }
	        
	        if (analiseFaturamento != null && !analiseFaturamento.equals("")){
	        	sessao.setAttribute("analiseFaturamento", analiseFaturamento);
	        }
	        
	        //Pesquisar Rota
	        if (informarDadosGeracaoRelatorioConsultaPeriodoActionForm.getIdRota() != null && !informarDadosGeracaoRelatorioConsultaPeriodoActionForm.getIdRota().trim().equals("")) {
				FiltroRota filtroRota = new FiltroRota();
				filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, informarDadosGeracaoRelatorioConsultaPeriodoActionForm.getIdRota()));
				filtroRota.adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupo");
				
				Collection<Rota> colecaoRota = fachada.pesquisar(filtroRota, Rota.class.getName());
				
				if (colecaoRota != null && !colecaoRota.isEmpty()) {
					Rota rota = (Rota) Util.retonarObjetoDeColecao(colecaoRota);
					informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setCodigoRota(rota.getCodigo().toString());
					
					informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setGrupoFaturamento(rota.getFaturamentoGrupo().getId() + "");
					
					sessao.setAttribute("faturamentoGrupo", rota.getFaturamentoGrupo().getId());
				}
				
			}
	        
	        if (limparForm != null && !limparForm.equals("")){
	        	
	        	informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setMesAnoInicialFaturamento("");
	        	informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setMesAnoFinalFaturamento("");
	        	informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setOpcaoTotalizacao("");
	        	informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setGrupoFaturamento("");
	        	informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setGrupoCobranca("");
	        	informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setGerencialRegional("");
	        	informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setEloPolo("");
	        	informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setDescricaoEloPolo("");
	        	informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setLocalidade("");
	        	informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setDescricaoLocalidade("");
	        	informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setSetorComercial("");
	        	informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setDescricaoSetorComercial("");
	        	informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setQuadra("");
	        	informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setPerfilImovel(null);
	        	informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setSituacaoLigacaoAgua(null);
	        	informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setSituacaoLigacaoEsgoto(null);
	        	informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setCategoria(null);
	        	informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setEsferaPoder(null);
	        	informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setMunicipio("");
	        	informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setDescricaoMunicipio("");
	        	informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setIdRota("");
	        	informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setCodigoRota("");
	        	informarDadosGeracaoRelatorioConsultaPeriodoActionForm.setTipoAnaliseFaturamento("");
	        }
	        return retorno;
    }

}
