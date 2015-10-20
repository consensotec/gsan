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

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroLocalidadeClasse;
import gcom.cadastro.localidade.FiltroLocalidadePorte;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.LocalidadeClasse;
import gcom.cadastro.localidade.LocalidadePorte;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroLocalArmazenagem;
import gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoCampos;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirInserirLocalidadeAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o retorno
        ActionForward retorno = actionMapping
                .findForward("exibirInserirLocalidade");

        //Obt�m a sess�o
        HttpSession sessao = httpServletRequest.getSession(false);

        InserirLocalidadeActionForm inserirLocalidadeActionForm = (InserirLocalidadeActionForm) actionForm;

        String objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");
        String limparForm = (String) httpServletRequest.getParameter("limparCampos");
        String removerEndereco = (String) httpServletRequest.getParameter("removerEndereco");

        Collection colecaoPesquisa = null;

        
        //Valida��o do codigo da localidade que ficar� como Elo.
        if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("")) {

            switch (Integer.parseInt(objetoConsulta)) {
            
            //Elo - Localidade
            case 1:

            	//Recebe o valor do campo id Unidade de Negocio do formul�rio.
            	String idUnidadeNegocio = inserirLocalidadeActionForm.getIdUnidadeNegocio();
                
                // O elo informado deve pertencer � mesma unidade
                // de negocio da localidade que est� sendo inserida

                FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

                filtroLocalidade.setCampoOrderBy(FiltroLocalidade.DESCRICAO);
                filtroLocalidade.adicionarParametro(new ParametroSimples(
                        FiltroLocalidade.ID_UNIDADE_NEGOCIO, idUnidadeNegocio));

                filtroLocalidade.adicionarParametro(new ParametroSimples(
                        FiltroLocalidade.INDICADORUSO,
                        ConstantesSistema.INDICADOR_USO_ATIVO));

                filtroLocalidade.adicionarParametro(new ComparacaoCampos(
                        FiltroLocalidade.ID, "localidade"));

                //Retorna localidade - Elo
                colecaoPesquisa = this.getFachada().pesquisar(filtroLocalidade,Localidade.class.getName());
                sessao.setAttribute("colecaoElo",colecaoPesquisa);

                break;

            //Gerente da Localidade
            case 2:
            	
            	this.pesquisarCliente(inserirLocalidadeActionForm);

            	break;
            case 3:	
            	
            	this.pesquisarMunicipioPrincipal(inserirLocalidadeActionForm);
            	break;
            default:

                break;
            }
        } else {

            if (sessao.getAttribute("colecaoUnidadeNegocio") == null
                    || sessao.getAttribute("colecaoClasse") == null
                    || sessao.getAttribute("colecaoPorte") == null) {

                // Carregamento inicial do formul�rio.
            	FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();	
                filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME_ABREVIADO,FiltroUnidadeNegocio.NOME);
                
            	filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
                        FiltroUnidadeNegocio.INDICADOR_USO,
                        ConstantesSistema.INDICADOR_USO_ATIVO));

                //Retorna Localidade_Classe
                Collection colecaoUnidadeNegocio = 
                	this.getFachada().pesquisar(filtroUnidadeNegocio,
                        UnidadeNegocio.class.getName());

                if (colecaoUnidadeNegocio == null || colecaoUnidadeNegocio.isEmpty()) {
                    //Nenhum registro na tabela gerencia_regional foi
                    // encontrada
                    throw new ActionServletException(
                            "atencao.pesquisa.nenhum_registro_tabela", null,
                            "Unidade de Neg�cio");
                } else {
                	
                	UnidadeNegocio unidadeNegocio = null;
        			Iterator iterator = colecaoUnidadeNegocio.iterator();
        		
        			while (iterator.hasNext()) {
        				unidadeNegocio = (UnidadeNegocio) iterator.next();
        				
        				String descUnidadeNegocio = unidadeNegocio.getNomeAbreviado() + "-" + unidadeNegocio.getNome();
        				unidadeNegocio.setNome(descUnidadeNegocio);
        				
        			}
                	
                    sessao.setAttribute("colecaoUnidadeNegocio",
                    		colecaoUnidadeNegocio);
                }

                FiltroLocalidadeClasse filtroLocalidadeClasse = new FiltroLocalidadeClasse();

                filtroLocalidadeClasse.setCampoOrderBy(FiltroLocalidadeClasse.DESCRICAO);
                filtroLocalidadeClasse.adicionarParametro(new ParametroSimples(
                        FiltroLocalidadeClasse.INDICADOR_USO,
                        ConstantesSistema.INDICADOR_USO_ATIVO));

                //Retorna Localidade_Classe
                colecaoPesquisa = this.getFachada().pesquisar(filtroLocalidadeClasse,
                        LocalidadeClasse.class.getName());

                if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                    //Nenhum registro na tabela localidade_classe foi
                    // encontrado
                    throw new ActionServletException(
                            "atencao.pesquisa.nenhum_registro_tabela", null,
                            "Localidade_Classe");
                } else {
                    sessao.setAttribute("colecaoClasse", colecaoPesquisa);
                }

                FiltroLocalidadePorte filtroLocalidadePorte = new FiltroLocalidadePorte();

                filtroLocalidadePorte.setCampoOrderBy(FiltroLocalidadePorte.DESCRICAO);
                
                filtroLocalidadePorte.adicionarParametro(new ParametroSimples(
                        FiltroLocalidadePorte.INDICADOR_USO,
                        ConstantesSistema.INDICADOR_USO_ATIVO));

                //Retorna Localidade_Porte
                colecaoPesquisa = this.getFachada().pesquisar(filtroLocalidadePorte,
                        LocalidadePorte.class.getName());

                if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                    //Nenhum registro na tabela localidade_porte foi encontrado
                    throw new ActionServletException(
                            "atencao.pesquisa.nenhum_registro_tabela", null,
                            "Localidade_Porte");
                } else {
                    sessao.setAttribute("colecaoPorte", colecaoPesquisa);
                }
                
                //hidrometro local armazenagem 
                FiltroHidrometroLocalArmazenagem filtroHidrometroLocalArmazenagem = new FiltroHidrometroLocalArmazenagem();
                
                filtroHidrometroLocalArmazenagem.setCampoOrderBy(FiltroHidrometroLocalArmazenagem.DESCRICAO);
                
                filtroHidrometroLocalArmazenagem.adicionarParametro(new ParametroSimples(
                		FiltroHidrometroLocalArmazenagem.INDICADOR_USO,
                		ConstantesSistema.INDICADOR_USO_ATIVO));
               
                //Retorna local de armazenagem do hidrometro
                colecaoPesquisa = this.getFachada().pesquisar(filtroHidrometroLocalArmazenagem,
                        HidrometroLocalArmazenagem.class.getName());
                
                if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                    //Nenhum registro na tabela hidrometro_local_armazenagem foi encontrado
                    throw new ActionServletException(
                            "atencao.pesquisa.nenhum_registro_tabela", null,
                            "Local de Armazenagem do Hidr�metro");
                } else {
                    sessao.setAttribute("colecaoHidrometroLocalArmazenagem", colecaoPesquisa);
                }
                
            }
        }
		
        //Codigo Cliente
		if(inserirLocalidadeActionForm.getGerenteLocalidade() != null && 
			!inserirLocalidadeActionForm.getGerenteLocalidade().equals("") && 
			inserirLocalidadeActionForm.getNomeGerente() != null && 
			!inserirLocalidadeActionForm.getNomeGerente().equals("")){
							
			httpServletRequest.setAttribute("gerenteLocalidadeEncontrado","true");
			limparForm = "1";
		}
		
		//Munic�pio
		if(inserirLocalidadeActionForm.getMunicipio() != null && 
				!inserirLocalidadeActionForm.getMunicipio().equals("") && 
				inserirLocalidadeActionForm.getDescricaoMunicipio() != null && 
				!inserirLocalidadeActionForm.getDescricaoMunicipio().equals("")){
								
				httpServletRequest.setAttribute("corMunicipio","true");
				limparForm = "1";
			}
		
        if ((limparForm == null || limparForm.trim().equalsIgnoreCase("")) ||
        		(httpServletRequest.getParameter("desfazer") != null
                        && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S"))) {
        	//-------------- bt DESFAZER ---------------
        	
        	//Estado Inicial do formulario
        	//Retorna o maior id de Localidade existente 
			int id = this.getFachada().pesquisarMaximoIdLocalidade();
			// Acrescenta 1 no valor do id para setar o primeiro id vazio para o usu�rio
			id = id + 1;
			inserirLocalidadeActionForm.setLocalidadeID("" + id);
			httpServletRequest.setAttribute("nomeCampo", "localidadeID");
			
        	
            //Limpando o formulario
        	inserirLocalidadeActionForm.setEmail("");
        	inserirLocalidadeActionForm.setFax("");
        	//inserirLocalidadeActionForm.setLocalidadeID("");
        	inserirLocalidadeActionForm.setLocalidadeNome("");
        	inserirLocalidadeActionForm.setMenorConsumo("");
        	inserirLocalidadeActionForm.setRamal("");
        	inserirLocalidadeActionForm.setTelefone("");

            // Campos do tipo lista no formul�rio
        	inserirLocalidadeActionForm.setEloID("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	inserirLocalidadeActionForm.setClasseID("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	inserirLocalidadeActionForm.setPorteID("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	inserirLocalidadeActionForm.setHidrometroLocalArmazenagem("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	inserirLocalidadeActionForm.setIdUnidadeNegocio("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	inserirLocalidadeActionForm.setGerenteLocalidade(null);
        	inserirLocalidadeActionForm.setNomeGerente(null);
        	inserirLocalidadeActionForm.setIcms("");
        	inserirLocalidadeActionForm.setCentroCusto("");
        	inserirLocalidadeActionForm.setDescricaoMunicipio(null);
        	inserirLocalidadeActionForm.setMunicipio(null);
        	inserirLocalidadeActionForm.setSede("2");
        	inserirLocalidadeActionForm.setInformatizada("2");
        	
            //Limpa o endere�o da sess�o
            if (sessao.getAttribute("colecaoEnderecos") != null) {
                sessao.removeAttribute("colecaoEnderecos");
            }
            sessao.removeAttribute("tipoPesquisaRetorno");
        }

        //Remove o endereco informado.
        if (removerEndereco != null
                && !removerEndereco.trim().equalsIgnoreCase("")) {

            if (sessao.getAttribute("colecaoEnderecos") != null) {

                Collection enderecos = (Collection) sessao
                        .getAttribute("colecaoEnderecos");
                if (!enderecos.isEmpty()) {
                    enderecos.remove(enderecos.iterator().next());
                }

            }

        }
        
        if ( httpServletRequest.getParameter("verificarLocalidadeSede")!= null &&
        		httpServletRequest.getParameter("verificarLocalidadeSede").equals("sim")){
        	
        	if ( inserirLocalidadeActionForm.getSede() != null && 
            		inserirLocalidadeActionForm.getSede().equals("1")){
            	
            	FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
            	            	
            	Collection colecaoLocalidade = this.getFachada().pesquisar(filtroLocalidade,Localidade.class.getName());
            	
            	if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()){
            		
            		Iterator colecaoLocalidadeIterator = colecaoLocalidade.iterator();
                	
                	Localidade localidade = null;
                	
                	while ( colecaoLocalidadeIterator.hasNext() ){
                		
                		localidade = (Localidade) colecaoLocalidadeIterator.next();
                		
                		if ( localidade.getIndicadorLocalidadeSede() == 1){
                			
                			String localidadeSede = ""+localidade.getId();
                			
                			throw new ActionServletException(
                                    "atencao.ja_existe_localidade_sede", null, localidadeSede);
                			
                		}
                		
                	}
            		
            	}
            	
            }
        	
        }

        //devolve o mapeamento de retorno
        return retorno;
    }
    
	/**
	 * Pesquisa Cliente 
	 *
	 * @author Rafael Pinto
	 * @date 15/08/2006
	 */
	private void pesquisarCliente(InserirLocalidadeActionForm form) {
		
		FiltroCliente filtroCliente = new FiltroCliente();

		filtroCliente.adicionarParametro(
			new ParametroSimples(FiltroCliente.ID, 
			new Integer(form.getGerenteLocalidade())));

		// Pesquisa de acordo com os par�metros informados no filtro
		Collection colecaoCliente = 
			this.getFachada().pesquisar(filtroCliente,Cliente.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoCliente != null && !colecaoCliente.isEmpty()) {

			// Obt�m o objeto da cole��o pesquisada
			Cliente cliente = 
				(Cliente) Util.retonarObjetoDeColecao(colecaoCliente);

			form.setGerenteLocalidade(cliente.getId().toString());
			form.setNomeGerente(cliente.getNome());
			

		} else {
			form.setGerenteLocalidade("");
			form.setNomeGerente("Cliente inexistente");
		}
	}

	/**
	 * Pesquisa Munic�pio Principal 
	 *
	 * @author Diogo Peixoto
	 * @date 29/03/2011
	 */
	private void pesquisarMunicipioPrincipal(InserirLocalidadeActionForm form) {
		
		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

		filtroMunicipio.adicionarParametro(
			new ParametroSimples(FiltroMunicipio.ID, 
			new Integer(form.getMunicipio())));

		// Pesquisa de acordo com os par�metros informados no filtro
		Collection colecaoMunicipio = 
			this.getFachada().pesquisar(filtroMunicipio,Municipio.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {

			// Obt�m o objeto da cole��o pesquisada
			Municipio municipio = 
				(Municipio) Util.retonarObjetoDeColecao(colecaoMunicipio);

			form.setMunicipio(municipio.getId().toString());
			form.setDescricaoMunicipio(municipio.getNome());
			

		} else {
			form.setMunicipio("");
			form.setDescricaoMunicipio("Munic�pio inexistente");
		}
	}
}
