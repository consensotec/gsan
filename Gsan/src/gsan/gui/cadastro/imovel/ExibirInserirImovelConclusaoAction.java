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
package gsan.gui.cadastro.imovel;

import gsan.cadastro.cliente.ClienteImovel;
import gsan.cadastro.funcionario.FiltroFuncionario;
import gsan.cadastro.funcionario.Funcionario;
import gsan.cadastro.imovel.FiltroImovel;
import gsan.cadastro.imovel.FiltroImovelContaEnvio;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.imovel.ImovelContaEnvio;
import gsan.fachada.Fachada;
import gsan.faturamento.conta.FiltroNomeConta;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.gui.integracao.GisHelper;
import gsan.micromedicao.FiltroRota;
import gsan.micromedicao.Rota;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.FiltroParametro;
import gsan.util.filtro.ParametroNulo;
import gsan.util.filtro.ParametroSimples;
import gsan.util.filtro.ParametroSimplesDiferenteDe;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 */
public class ExibirInserirImovelConclusaoAction extends GcomAction {
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

        ActionForward retorno = actionMapping.findForward("inserirImovelConclusao");

        //obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);
        
        

        DynaValidatorForm inserirImovelConclusaoActionForm = 
        	(DynaValidatorForm) sessao.getAttribute("InserirImovelActionForm");

        //Cria Filtros
        FiltroImovelContaEnvio filtroImovelContaEnvio = new FiltroImovelContaEnvio();

        //Cria cole�ao
        Collection colecaoImovelEnnvioConta = null;


        //Obt�m a inst�ncia da Fachada
        Fachada fachada = Fachada.getInstancia();   
        
        /*
		 * GIS
		 * ==============================================================================================================	
		 */
		sessao.setAttribute("gis",true);
		
		GisHelper gisHelper = (GisHelper) sessao.getAttribute("gisHelper");	
		
		if(gisHelper!= null){					
		   carregarDadosGis(gisHelper,inserirImovelConclusaoActionForm,sessao,fachada);
		}		
		
		
        String informacoesComplementares = (String) inserirImovelConclusaoActionForm.get("informacoesComplementares");
        if(informacoesComplementares != null && informacoesComplementares.equals("")){
        	inserirImovelConclusaoActionForm.set("informacoesComplementares","");        	
        }else {
        	inserirImovelConclusaoActionForm.set("informacoesComplementares",informacoesComplementares);
        }
		/*
		 * Carregamento inicial da tela respons�vel pelo redebimento das
		 * informa��es referentes ao local da ocorr�ncia (ABA N� 02)
		 * ============================================================================================================
		 */
        
//        //Faz a pesquisa de Ocupacao Tipo
//        filtroImovelContaEnvio.adicionarParametro(new ParametroSimples(FiltroNomeConta.INDICADOR_USO,
//        		ConstantesSistema.INDICADOR_USO_ATIVO));
//        
//        filtroImovelContaEnvio.setCampoOrderBy(FiltroImovelContaEnvio.DESCRICAO);
//        
//        colecaoImovelEnnvioConta = 
//        	fachada.pesquisar(filtroImovelContaEnvio, ImovelContaEnvio.class.getName());
//        
//        if (!colecaoImovelEnnvioConta.isEmpty()) {
//            
//        	//Coloca a cole�ao da pesquisa na sessao
//            sessao.setAttribute("colecaoImovelEnnvioConta", colecaoImovelEnnvioConta);
//            
//            /*
//             * Alterado por Raphael Rossiter em 10/09/2007 (Analista: Aryed Lins)
//             * OBJ: Marcar o indicador de emiss�o de extrato de faturamento para NAO EMITIR
//             */
//            inserirImovelConclusaoActionForm.set("extratoResponsavel", ConstantesSistema.NAO.toString());
//            
//        } else {
//            throw new ActionServletException("atencao.naocadastrado",null, "Imovel Conta Envio");
//        }        
        
        //Verifica se existe cliente responsavel
		boolean eResponsavel = false;

        //Testa se j� existe um clinte propriet�rio
        if (sessao.getAttribute("imovelClientesNovos") == null) {
            httpServletRequest.setAttribute("envioContaListar", "naoListar");
        } else {
            Collection imovelClientes = (Collection) sessao.getAttribute("imovelClientesNovos");
            ClienteImovel clienteImovel = new ClienteImovel();
            Iterator iteratorClienteImovel = imovelClientes.iterator();

            while (iteratorClienteImovel.hasNext()) {
                
            	clienteImovel = null;
                clienteImovel = (ClienteImovel) iteratorClienteImovel.next();
                
                if (clienteImovel.getClienteRelacaoTipo().getDescricao().equalsIgnoreCase(ConstantesSistema.IMOVEL_ENVIO_CONTA)) {
                    httpServletRequest.setAttribute("envioContaListar","listar");
                }
                
				if (clienteImovel.getClienteRelacaoTipo() != null &&
						clienteImovel.getClienteRelacaoTipo().getId() == 3 ){
					
					httpServletRequest.setAttribute("contaEnvioObrigatorio","obrigatorio");
					
					eResponsavel = true;
					
				}else if ( clienteImovel.getClienteRelacaoTipo() != null && !eResponsavel &&
						clienteImovel.getClienteRelacaoTipo().getId() != 3 ){
					
					httpServletRequest.setAttribute("contaEnvioObrigatorio","opcional");
					
				}
				
				if ( sessao.getAttribute("imovelContaEnvio") != null && !sessao.getAttribute("imovelContaEnvio").equals("") ){
					
					String envioConta = (String) sessao.getAttribute("imovelContaEnvio");
					
					if ( ( envioConta.equals("4") || envioConta.equals("5") )
							&& clienteImovel.getIndicadorNomeConta().compareTo(new Short("1")) == 0
							&& ( clienteImovel.getCliente().getEmail() == null
							|| clienteImovel.getCliente().getEmail().equals("") )){
	
							throw new ActionServletException("atencao.email.nao.cadastrado");
						
						
					}
					
				}
                
            }
        }
        
        //Faz a pesquisa de Ocupacao Tipo
		filtroImovelContaEnvio.adicionarParametro(new ParametroSimples(
				FiltroNomeConta.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
		
		//Se existir cliente responsavel faz a pesquisa atraves do ICTE_ICCLIENTEREPONSAVEL = 1
		if ( eResponsavel ){
			
			filtroImovelContaEnvio.adicionarParametro(new ParametroSimples(
					FiltroImovelContaEnvio.INDICADOR_CLIENTE_RESPONSAVEL, "1" ));
			
		}//Se n�o existir cliente responsavel faz a pesquisa atraves do ICTE_ICCLIENTEREPONSAVEL = 2
		else if( !eResponsavel ){
			
			filtroImovelContaEnvio.adicionarParametro(new ParametroSimples(
					FiltroImovelContaEnvio.INDICADOR_CLIENTE_RESPONSAVEL, "2" ));
			
		}
		
		filtroImovelContaEnvio.setCampoOrderBy(FiltroImovelContaEnvio.DESCRICAO);
		
		colecaoImovelEnnvioConta = 
			this.getFachada().pesquisar(filtroImovelContaEnvio, ImovelContaEnvio.class.getName());
		
		if (!colecaoImovelEnnvioConta.isEmpty()) {
			httpServletRequest.setAttribute("colecaoImovelEnvioContaVazia", "false");
			//Coloca a cole�ao da pesquisa na sessao
			sessao.setAttribute("colecaoImovelEnnvioConta", colecaoImovelEnnvioConta);
			
			/*
			 * Alterado por Raphael Rossiter em 10/09/2007 (Analista: Aryed Lins)
			 * OBJ: Marcar o indicador de emiss�o de extrato de faturamento para NAO EMITIR
			 */
			if (inserirImovelConclusaoActionForm.get("extratoResponsavel") != null &&
					!inserirImovelConclusaoActionForm.get("extratoResponsavel").equals("") &&
					inserirImovelConclusaoActionForm.get("extratoResponsavel").equals(ConstantesSistema.SIM.toString())){
				
				inserirImovelConclusaoActionForm.set("extratoResponsavel", ConstantesSistema.SIM.toString());
			}else{
				inserirImovelConclusaoActionForm.set("extratoResponsavel", ConstantesSistema.NAO.toString());
			}
			
			
		} else {
			
			/*throw new ActionServletException("atencao.naocadastrado",null,"Im�vel Conta Envio");*/
			if ( !eResponsavel ){
				
				httpServletRequest.setAttribute("colecaoImovelEnvioContaVazia", "true");
			
			}else if ( eResponsavel ){
			
				throw new ActionServletException("atencao.naocadastrado",null,"Im�vel Conta Envio");
				
			}
			
		}

        
        //Cria variaveis
        String idImovel = (String) inserirImovelConclusaoActionForm.get("idImovel");
        String idFuncionario = (String) inserirImovelConclusaoActionForm.get("idFuncionario");
        String idRota = (String) inserirImovelConclusaoActionForm.get("idRota");
        String idRotaAlternativa = (String) inserirImovelConclusaoActionForm.get("idRotaAlternativa");

        //Cria Filtros
        FiltroImovel filtroImovel = new FiltroImovel();
        
        //Objetos que ser�o retornados pelo Hibernate
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTipo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTitulo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTipo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTitulo");
        
        filtroImovel.adicionarParametro(new ParametroSimplesDiferenteDe(
        	FiltroImovel.INDICADOR_IMOVEL_EXCLUIDO,Imovel.IMOVEL_EXCLUIDO, FiltroParametro.CONECTOR_OR,2));

        filtroImovel.adicionarParametro(new ParametroNulo(FiltroImovel.INDICADOR_IMOVEL_EXCLUIDO));

        Collection imoveis = null;

        if (idImovel != null && !idImovel.trim().equalsIgnoreCase("")) {
            
        	filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, new Integer(idImovel.trim())));

            imoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());

            if (imoveis != null && !imoveis.isEmpty()) {

            	filtroImovel = new FiltroImovel();
            	filtroImovel.limparListaParametros();

            	//Cria Variaveis
                String idLocalidade = (String) inserirImovelConclusaoActionForm.get("idLocalidade");
                String idSetorComercial = (String) inserirImovelConclusaoActionForm.get("idSetorComercial");
                String idQuadra = (String) inserirImovelConclusaoActionForm.get("idQuadra");
                
            	filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,idImovel.trim()));
            	filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.LOCALIDADE_ID,new Integer(idLocalidade)));
            	filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.SETOR_COMERCIAL_CODIGO,new Integer(idSetorComercial)));
            	filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.QUADRA_NUMERO,new Integer(idQuadra)));
            	
            	Collection colecaoImovel = null;
            	colecaoImovel = fachada.pesquisar(filtroImovel,Imovel.class.getName());
            	
            	if(colecaoImovel == null || colecaoImovel.isEmpty()){
                    throw new ActionServletException("atencao.imovel_principal.inexistente.quadra");        		
            	}
            	
                sessao.setAttribute("imoveisPrincipal", imoveis);
                httpServletRequest.setAttribute("valorMatriculaImovelPrincipal", 
                	fachada.pesquisarInscricaoImovel(new Integer(idImovel.trim())));
                httpServletRequest.setAttribute("idImovelPrincipalNaoEncontrado", null);                
            }else {
                httpServletRequest.setAttribute("idImovelPrincipalNaoEncontrado", "true");
                httpServletRequest.setAttribute("valorMatriculaImovelPrincipal", "IMOVEL INEXISTENTE");
            }
        }
        
        
        if(idFuncionario != null && !idFuncionario.trim().equals("")){
        	
			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
			filtroFuncionario.adicionarParametro(new ParametroSimples(
				FiltroFuncionario.ID, idFuncionario));

			Collection colecaoFuncionario = 
				this.getFachada().pesquisar(filtroFuncionario, Funcionario.class.getSimpleName());
			
			if (colecaoFuncionario != null && !colecaoFuncionario.isEmpty()) {

				Funcionario funcionario = (Funcionario) Util.retonarObjetoDeColecao(colecaoFuncionario);

				httpServletRequest.setAttribute("idImovelPrincipalEncontrado","true");
				
				inserirImovelConclusaoActionForm.set("idFuncionario",funcionario.getId().toString());
				inserirImovelConclusaoActionForm.set("nomeFuncionario",funcionario.getNome());

			}else{
				
				inserirImovelConclusaoActionForm.set("idFuncionario","");
				inserirImovelConclusaoActionForm.set("nomeFuncionario","Funcion�rio Inexistente");
				
				
			}
        }
        
        if (idRota != null && !idRota.trim().equals("")) {
        	FiltroRota filtroRota = new FiltroRota();
        	filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, idRota));
        	
        	Collection colecaoRotas = fachada.pesquisar(filtroRota, Rota.class.getName());
        	
        	if (colecaoRotas != null && !colecaoRotas.isEmpty()) {
        		Rota rota = (Rota) Util.retonarObjetoDeColecao(colecaoRotas);
        		
        		inserirImovelConclusaoActionForm.set("codigoRota", rota.getCodigo().toString());
        	}
        }
        
        if (idRotaAlternativa != null && !idRotaAlternativa.trim().equals("")) {
        	FiltroRota filtroRota = new FiltroRota();
        	filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, idRotaAlternativa));
        	
        	Collection colecaoRotas = fachada.pesquisar(filtroRota, Rota.class.getName());
        	
        	if (colecaoRotas != null && !colecaoRotas.isEmpty()) {
        		Rota rota = (Rota) Util.retonarObjetoDeColecao(colecaoRotas);
        		
        		inserirImovelConclusaoActionForm.set("codigoRotaAlternativa", rota.getCodigo().toString());
        	}
        }
        
        String idLocalidade = (String) inserirImovelConclusaoActionForm.get("idLocalidade");
        String idSetorComercial = (String) inserirImovelConclusaoActionForm.get("idSetorComercial");
        String idQuadra = (String) inserirImovelConclusaoActionForm.get("idQuadra");
        httpServletRequest.setAttribute("paramsMapa", idLocalidade+","+idSetorComercial+","+idQuadra);
        
        return retorno;
    }
	//=================================================================================================================
	
	
	
	public void carregarDadosGis(			
			GisHelper gisHelper,
			DynaValidatorForm inserirImovelConclusaoActionForm,
			HttpSession sessao, Fachada fachada) {
	
		String nnCoordenadaNorte = gisHelper.getNnCoordenadaNorte(); 
		String nnCoordenadaLeste = gisHelper.getNnCoordenadaLeste(); 			
		
		inserirImovelConclusaoActionForm.set("cordenadasUtmX",nnCoordenadaLeste);
		inserirImovelConclusaoActionForm.set("cordenadasUtmY",nnCoordenadaNorte);
		
	     sessao.removeAttribute("gisHelper");	
	
	}
	
}
