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
package gcom.gui.cadastro.localidade;
//
//import gcom.cadastro.localidade.FiltroGerenciaRegional;
//import gcom.cadastro.localidade.GerenciaRegional;
//import gcom.fachada.Fachada;
//import gcom.faturamento.RegistradorOperacao;
//import gcom.gui.ActionServletException;
//import gcom.gui.GcomAction;
//import gcom.seguranca.acesso.Operacao;
//import gcom.seguranca.acesso.OperacaoEfetuada;
//import gcom.seguranca.acesso.usuario.Usuario;
//import gcom.seguranca.acesso.usuario.UsuarioAcao;
//import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
//import gcom.util.ConstantesSistema;
//import gcom.util.Util;
//import gcom.util.filtro.ParametroSimples;
//
//import java.util.Collection;
//import java.util.Date;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.apache.struts.action.ActionForm;
//import org.apache.struts.action.ActionForward;
//import org.apache.struts.action.ActionMapping;
//
//public class ManterGerenciaRegionalAction extends GcomAction {
//	
//	/**
//	 * Este caso de uso permite inserir Ger�ncia Regional 
//	 * 
//	 * [UC0396] Inserir Ger�ncia Regional
//	 * 
//	 * @param actionMapping
//	 * @param actionForm
//	 * @param httpServletRequest
//	 * @param httpServletResponse
//	 * @return
//	 */
//
//    public ActionForward execute(ActionMapping actionMapping,
//            ActionForm actionForm, HttpServletRequest httpServletRequest,
//            HttpServletResponse httpServletResponse) {
//
//        //Seta o retorno
//        ActionForward retorno = actionMapping.findForward("telaSucesso");
//
//        //Obt�m a inst�ncia da fachada
//        Fachada fachada = Fachada.getInstancia();
// 
//        //Obt�m a sess�o
//        HttpSession sessao = httpServletRequest.getSession(false);
//
//        InserirGerenciaRegionalActionForm inserirGerenciaRegionalActionForm = (InserirGerenciaRegionalActionForm) actionForm;
//
//
//        //------------ REGISTRAR TRANSA��O ----------------
//        RegistradorOperacao registradorOperacao = new RegistradorOperacao(
//				Operacao.OPERACAO_GERENCIA_REGIONAL_INSERIR,
//				new UsuarioAcaoUsuarioHelper(Usuario.USUARIO_TESTE,
//						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
//        
//        Operacao operacao = new Operacao();
//        operacao.setId(Operacao.OPERACAO_GERENCIA_REGIONAL_INSERIR);
//
//        OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
//        operacaoEfetuada.setOperacao(operacao);
//        //------------ REGISTRAR TRANSA��O ----------------
//        
//        
//        String gerenciaRegionalID = inserirGerenciaRegionalActionForm
//                .getGerenciaRegionalID();
//        String nome = inserirGerenciaRegionalActionForm
//                .getNome();
//        String nomeAbreviado = inserirGerenciaRegionalActionForm
//        .getNomeAbreviado();
//        Collection colecaoEnderecos = (Collection) sessao
//                .getAttribute("colecaoEnderecos");
//        String telefone = inserirGerenciaRegionalActionForm.getTelefone();
//        String ramal = inserirGerenciaRegionalActionForm.getRamal();
//        String fax = inserirGerenciaRegionalActionForm.getFax();
//        String email = inserirGerenciaRegionalActionForm.getEmail();
//
//
//        GerenciaRegional gerenciaRegionalInserir = new GerenciaRegional();
//        Collection colecaoPesquisa = null;
//
//        sessao.removeAttribute("tipoPesquisaRetorno");
//        //O c�digo da Gerencia Regional � obrigat�rio.
//        if (gerenciaRegionalID == null || gerenciaRegionalID.equalsIgnoreCase("")) {
//            throw new ActionServletException(
//            		"atencao.required",null,"C�digo");
//        }
//
//        //O nome da Ger�ncia Regional � obrigat�rio.
//        if (nome == null || nome.equalsIgnoreCase("")) {
//            throw new ActionServletException(
//            		"atencao.required",null,"Nome");
//        }
//        
//        //O nome Abreviado da Ger�ncia Regional � obrigat�rio.
//        if (nomeAbreviado == null || nome.equalsIgnoreCase("")) {
//            throw new ActionServletException(
//            		"atencao.required",null,"Nome Abreviado");
//        }
//
//        //O endere�o da localidade � obrigat�rio.
//        /*if (colecaoEnderecos == null || colecaoEnderecos.isEmpty()) {
//            throw new ActionServletException(
//                    "atencao.endereco_localidade_nao_informado");
//        } else {
//            localidadeInserir = (Localidade) Util
//                    .retonarObjetoDeColecao(colecaoEnderecos);
//            localidadeInserir.setId(new Integer(localidadeID));
//            localidadeInserir.setDescricao(localidadeNome);
//        }*/
//        
//        
////        if (colecaoEnderecos != null && !colecaoEnderecos.isEmpty()) {
////        	gerenciaRegionalInserir = (GerenciaRegional) Util
////            .retonarObjetoDeColecao(colecaoEnderecos);
////        } 
//
//        gerenciaRegionalInserir.setId(new Integer(gerenciaRegionalID));
//        gerenciaRegionalInserir.setNome(nome);
//        
//        
//        //O telefone � obrigat�rio caso o ramal tenha sido informado.
//        if (ramal != null && !ramal.equalsIgnoreCase("")) {
//        	gerenciaRegionalInserir.setRamalFone(ramal);
//            if (telefone == null || telefone.equalsIgnoreCase("")) {
//                throw new ActionServletException(
//                        "atencao.telefone_localidade_nao_informado");
//            }
//            else if (telefone.length() < 7){
//            	throw new ActionServletException(
//                "atencao.telefone_ou_fax_localidade_menor_sete_digitos", null, "Telefone");
//            }
//        }
//
//        //Telefone.
//        if (telefone != null && !telefone.equalsIgnoreCase("")) {
//        	if (telefone.length() < 7){
//        		throw new ActionServletException(
//                "atencao.telefone_ou_fax_localidade_menor_sete_digitos", null, "Telefone");
//        	}
//        	else{
//        		gerenciaRegionalInserir.setFone(telefone);
//        	}
//        }
//
//        //Fax.
//        if (fax != null && !fax.equalsIgnoreCase("")) {
//        	if (fax.length() < 7){
//        		throw new ActionServletException(
//                "atencao.telefone_ou_fax_localidade_menor_sete_digitos", null, "Fax");
//        	}
//        	else{
//        		gerenciaRegionalInserir.setFax(fax);
//        	}
//        }
//
//        //E-mail.
//        if (email != null && !email.equalsIgnoreCase("")) {
//        	gerenciaRegionalInserir.setEmail(email);
//        }
//    
// 
//
//
//
//        //Indicador de Uso
//        gerenciaRegionalInserir
//                .setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
//
//        //Ultima altera��o
//        gerenciaRegionalInserir.setUltimaAlteracao(new Date());
//
//        FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
//
//        filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
//        		FiltroGerenciaRegional.ID, gerenciaRegionalInserir.getId()));
//
//        //Verificar exist�ncia da Ger�ncia Regional
//        colecaoPesquisa = fachada.pesquisar(filtroGerenciaRegional, GerenciaRegional.class
//                .getName());
//
//        if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
//            //Gerencia Regional j� existe
//            throw new ActionServletException(
//                    "atencao.pesquisa_localidade_ja_cadastrada", null, gerenciaRegionalID);
//        } else {
//        	Integer idGerenciaRegional = null;
//            
//        	        	
//            //------------ REGISTRAR TRANSA��O ----------------
//        	gerenciaRegionalInserir.setOperacaoEfetuada(operacaoEfetuada);
//        	gerenciaRegionalInserir.adicionarUsuario(Usuario.USUARIO_TESTE, 
//            		UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
//            registradorOperacao.registrarOperacao(gerenciaRegionalInserir);
//            //------------ REGISTRAR TRANSA��O ----------------  
////            idGerenciaRegional = fachada.inserirGerenciaRegional(gerenciaRegionalInserir);
//
//            montarPaginaSucesso(httpServletRequest, "Ger�ncia Regional de c�digo  "
//                    + gerenciaRegionalInserir.getId().intValue()
//                    + " inserida com sucesso.", "Inserir outra Ger�ncia Regional",
//                    "exibirInserirGerenciaRegionalAction.do?menu=sim",
//                    "exibirAtualizarGerenciaRegionalAction.do?idRegistroInseridoAtualizar="
//					+ idGerenciaRegional,
//					"Atualizar Ger�ncia Regional Inserida");
//
//        }
//
//        sessao.removeAttribute("colecaoEnderecos");
//
//
//
//        //devolve o mapeamento de retorno
//        return retorno;
//    }
//
//}
