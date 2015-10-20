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
package gcom.gui.arrecadacao;

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.FiltroDocumentoTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

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
 * @date 15/05/2007
 */
public class ExibirFiltrarDadosDiariosArrecadacaoAction extends GcomAction {

    public ActionForward execute(
    		ActionMapping actionMapping,
            ActionForm actionForm, 
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o retorno
        ActionForward retorno = actionMapping.findForward("filtrarDadosDiariosArrecadacao");

        //Obt�m a inst�ncia da fachada
        Fachada fachada = Fachada.getInstancia();

        //Obt�m a sess�o
        HttpSession sessao = httpServletRequest.getSession(false);
        
        // Verificamos se o processo de dados di�rios da arrecada��o est� rodando
        fachada.verificarBatchDadosDiariosArracadacaoRodando();
        
        FiltrarDadosDiariosArrecadacaoActionForm filtrarDadosDiariosArrecadacaoActionForm = (FiltrarDadosDiariosArrecadacaoActionForm) actionForm;
        
        String idLocalidade = filtrarDadosDiariosArrecadacaoActionForm.getLocalidade();
        String idElo = filtrarDadosDiariosArrecadacaoActionForm.getIdElo();
        String idArrecadador = filtrarDadosDiariosArrecadacaoActionForm.getIdArrecadador();
        
        FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
        boolean entrar = false;
        // Pesquisando a localidade
        if (idLocalidade != null && !idLocalidade.trim().equals("")) {
        	
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidade));
			
			filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("localidade");

			Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade,Localidade.class.getName());

			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				Localidade localidade = (Localidade) colecaoLocalidade.iterator().next();
				
				filtrarDadosDiariosArrecadacaoActionForm.setLocalidade(idLocalidade);
				filtrarDadosDiariosArrecadacaoActionForm.setDescricaoLocalidade(localidade.getDescricao());
				
				if(idElo == null || idElo.equals("")) {
					filtrarDadosDiariosArrecadacaoActionForm.setIdElo(localidade.getLocalidade().getId().toString());
					filtrarDadosDiariosArrecadacaoActionForm.setNomeElo(localidade.getLocalidade().getDescricao());
				}
				entrar = true;
			} else {
				filtrarDadosDiariosArrecadacaoActionForm.setLocalidade("");
				filtrarDadosDiariosArrecadacaoActionForm.setDescricaoLocalidade("LOCALIDADE INEXISTENTE");
				httpServletRequest.setAttribute("localidadeInexistente", true);
			}
		} else {
			filtrarDadosDiariosArrecadacaoActionForm.setDescricaoLocalidade("");
		}
        
        if (idArrecadador != null && !idArrecadador.trim().equals("") && Integer.parseInt(idArrecadador) > 0) {
			FiltroArrecadador filtroArrecadador = new FiltroArrecadador();
			filtroArrecadador.adicionarParametro(new ParametroSimples(FiltroArrecadador.CODIGO_AGENTE, idArrecadador));
			filtroArrecadador.adicionarCaminhoParaCarregamentoEntidade("cliente");
			
			Collection<Arrecadador> arrecadadorEncontrado = fachada.pesquisar(filtroArrecadador,Arrecadador.class.getName());

			if (arrecadadorEncontrado != null && !arrecadadorEncontrado.isEmpty()) {
				Arrecadador arrecadador = (Arrecadador) arrecadadorEncontrado.iterator().next();

				filtrarDadosDiariosArrecadacaoActionForm.setIdArrecadador(idArrecadador);
				filtrarDadosDiariosArrecadacaoActionForm.setNomeArrecadador(arrecadador.getCliente().getNome());

			} else {
				filtrarDadosDiariosArrecadacaoActionForm.setIdArrecadador("");
				filtrarDadosDiariosArrecadacaoActionForm.setNomeArrecadador("ARRECADADOR INEXISTENTE");
				httpServletRequest.setAttribute("arrecadadorInexistente", true);
			}
		} else {
			filtrarDadosDiariosArrecadacaoActionForm.setNomeArrecadador("");
		}

        // Pesquisando a Elo Polo
        if (idElo != null && !idElo.trim().equals("")) {
        
        	filtroLocalidade.limparListaParametros();
        	
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID_ELO, idElo));
			
			filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("localidade");

			Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade,Localidade.class.getName());
			
			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				boolean flag = false;
				Localidade elo = (Localidade) colecaoLocalidade.iterator().next();

				filtrarDadosDiariosArrecadacaoActionForm.setIdElo(idElo);
				filtrarDadosDiariosArrecadacaoActionForm.setNomeElo(elo.getLocalidade().getDescricao());
				
				Iterator iteratorColecaoLocalidade = colecaoLocalidade.iterator();
				
				while (iteratorColecaoLocalidade.hasNext()) {
					
					// Obt�m os dados do cr�dito realizado
					Localidade elo2 = (Localidade) iteratorColecaoLocalidade.next();

					if(idLocalidade != null && !idLocalidade.equals("")) {
						if(elo2.getId().toString().equals(idLocalidade)) {
							flag = true;
							String eloDiferente = "NAO";
							filtrarDadosDiariosArrecadacaoActionForm.setEloDiferente(eloDiferente);
						}
					}
				}
				if(!flag && idLocalidade != null && !idLocalidade.equals("")){
					filtrarDadosDiariosArrecadacaoActionForm.setLocalidade("");
					filtrarDadosDiariosArrecadacaoActionForm.setDescricaoLocalidade("LOCALIDADE INEXISTENTE");
					httpServletRequest.setAttribute("localidadeInexistente", true);
				}
				filtrarDadosDiariosArrecadacaoActionForm.setLocalidadeDoElo(elo.getId().toString());
			} else {
				filtrarDadosDiariosArrecadacaoActionForm.setIdElo("");
				filtrarDadosDiariosArrecadacaoActionForm.setNomeElo("ELO P�LO INEXISTENTE");
				httpServletRequest.setAttribute("eloInexistente", true);
			}
		} else if (!entrar){
			filtrarDadosDiariosArrecadacaoActionForm.setNomeElo("");
		}
        
        // Pesquisando Ger�ncia Regional
        if (sessao.getAttribute("colecaoGerenciaRegional") == null) {
    		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional(FiltroGerenciaRegional.NOME_ABREVIADO);
    		filtroGerenciaRegional.setConsultaSemLimites(true);
    		filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME_ABREVIADO);
    		Collection<GerenciaRegional> colecaoGerenciasRegionais = fachada.pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
    		sessao.setAttribute("colecaoGerenciasRegionais",colecaoGerenciasRegionais);
        }

        
        // Pesquisando Perfil do Im�vel
        if (sessao.getAttribute("colecaoImovelPerfil") == null) {
        	FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
        	filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);
        	Collection<ImovelPerfil> colecaoImovelPerfil = fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
        	sessao.setAttribute("colecaoImovelPerfil", colecaoImovelPerfil);
        }

        
        // Pesquisando o Tipo de Documento
        if (sessao.getAttribute("colecaoDocumentoTipo") == null) {
			FiltroDocumentoTipo filtroDocumentoTipo = new FiltroDocumentoTipo();
			filtroDocumentoTipo.setCampoOrderBy(FiltroDocumentoTipo.DESCRICAO);
			Collection colecaoDocumentoTipo = fachada.pesquisar(filtroDocumentoTipo, DocumentoTipo.class.getName());
			sessao.setAttribute("colecaoDocumentoTipo",colecaoDocumentoTipo);
		}

        
        // Pesquisando Situa��o da Liga��o de �gua
        if (sessao.getAttribute("colecaoSituacaoLigacaoAgua") == null) {
        	FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
        	filtroLigacaoAguaSituacao.setCampoOrderBy(FiltroLigacaoAguaSituacao.DESCRICAO);
        	Collection<LigacaoAguaSituacao> colecaoSituacaoLigacaoAgua = fachada.pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
        	sessao.setAttribute("colecaoSituacaoLigacaoAgua", colecaoSituacaoLigacaoAgua);
        }

        
        // Pesquisando Situa��o da Liga��o de Esgoto
        if (sessao.getAttribute("colecaoSituacaoLigacaoEsgoto") == null) {
        	FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
        	filtroLigacaoEsgotoSituacao.setCampoOrderBy(FiltroLigacaoEsgotoSituacao.DESCRICAO);
        	Collection<LigacaoEsgotoSituacao> colecaoSituacaoLigacaoEsgoto = fachada.pesquisar(filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName());
        	sessao.setAttribute("colecaoSituacaoLigacaoEsgoto", colecaoSituacaoLigacaoEsgoto);
        }
        
        
        // Pesquisando Categoria
        if (sessao.getAttribute("colecaoCategoria") == null) {
        	FiltroCategoria filtroCategoria = new FiltroCategoria();
        	filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);
        	Collection<Categoria> colecaoCategoria = fachada.pesquisar(filtroCategoria, Categoria.class.getName());
        	sessao.setAttribute("colecaoCategoria", colecaoCategoria);
        }
        
        
        // Pesquisando Categoria
        if (sessao.getAttribute("colecaoEsferaPoder") == null) {
        	FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder();
        	filtroEsferaPoder.setCampoOrderBy(FiltroEsferaPoder.DESCRICAO);
        	Collection<EsferaPoder> colecaoEsferaPoder = fachada.pesquisar(filtroEsferaPoder, EsferaPoder.class.getName());
        	sessao.setAttribute("colecaoEsferaPoder", colecaoEsferaPoder);
        }
        
        // Pesquisando Sistema Par�metros
        if (sessao.getAttribute("sistemaParametro") == null) {
        	SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
        	sessao.setAttribute("sistemaParametro", sistemaParametro);
        }
        
        // devolve o mapeamento de retorno
        return retorno;
    }

}
