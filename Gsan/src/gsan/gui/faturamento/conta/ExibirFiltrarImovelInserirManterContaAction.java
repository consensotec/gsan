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
package gsan.gui.faturamento.conta;


import gsan.arrecadacao.banco.Banco;
import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.cliente.ClienteRelacaoTipo;
import gsan.cadastro.cliente.FiltroCliente;
import gsan.cadastro.cliente.FiltroClienteRelacaoTipo;
import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.FiltroQuadra;
import gsan.cadastro.localidade.FiltroSetorComercial;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.Quadra;
import gsan.cadastro.localidade.SetorComercial;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.PermissaoEspecial;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0407] Filtrar Im�veis para Inserir ou Manter Conta
 * 
 * @author Ana Maria
 * @created 12/03/2007
 */
public class ExibirFiltrarImovelInserirManterContaAction extends GcomAction {
	

    private Collection colecaoPesquisa = null;

    private String localidadeIDOrigem = null;
    
    private String localidadeIDDestino = null;
    
    private String setorComercialCDOrigem = null;
    
    private String setorComercialCDDestino = null;

    private String setorComercialIDOrigem = null;
    
    private String setorComercialIDDestino = null;

    private String quadraNMOrigem = null;
    
    private String quadraNMDestino = null;
    
	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, 
		HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("filtrarImovelInserirManterConta");

		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//novo CRC1258 - Fl�vio Leonardo
		Collection<Banco> colecaoBancos = fachada.pesquisarBancoDebitoAutomatico();
		sessao.setAttribute("colecaoBancos", colecaoBancos);
		
		boolean habilitaBanco = fachada.verificarPermissaoEspecial(
				PermissaoEspecial.HABILITAR_BANCO_MANTER_CONTA, 
				this.getUsuarioLogado(httpServletRequest));
		
		sessao.setAttribute("habilitaBanco",habilitaBanco);
		
		FiltrarImovelContaActionForm filtrarImovelContaActionForm = (FiltrarImovelContaActionForm) actionForm;
		
		String codigoCliente = (String) filtrarImovelContaActionForm.getCodigoCliente();
		
        FiltroClienteRelacaoTipo filtroClienteRelacaoTipo = new FiltroClienteRelacaoTipo();
        
        filtroClienteRelacaoTipo.adicionarParametro(new ParametroSimples(FiltroClienteRelacaoTipo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
        
		Collection<ClienteRelacaoTipo> collectionClienteRelacaoTipo = fachada.pesquisar(filtroClienteRelacaoTipo, ClienteRelacaoTipo.class.getName() );
		
		if (collectionClienteRelacaoTipo != null && !collectionClienteRelacaoTipo.isEmpty()) 
		{
			httpServletRequest.setAttribute("collectionClienteRelacaoTipo", collectionClienteRelacaoTipo);
		}
		else
		{
	        throw new ActionServletException(
	        		"atencao.collectionClienteRelacaoTipo_inexistente", null, "id");
		}
		
		String idDigitadoEnterClienteSuperior = (String) filtrarImovelContaActionForm.getCodigoClienteSuperior();
        // verifica se o codigo do cliente superior foi digitado
        if (idDigitadoEnterClienteSuperior != null
                && !idDigitadoEnterClienteSuperior.trim().equals("")
                && Integer.parseInt(idDigitadoEnterClienteSuperior) > 0) {
            
        	FiltroCliente filtroCliente = new FiltroCliente();
			
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, idDigitadoEnterClienteSuperior));
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection clienteEncontrado = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());

			if (clienteEncontrado != null && !clienteEncontrado.isEmpty()) {
				// O Cliente foi encontrado
				if (((Cliente) ((List) clienteEncontrado).get(0))
						.getIndicadorUso().equals(
								ConstantesSistema.INDICADOR_USO_DESATIVO)) {
					throw new ActionServletException("atencao.cliente.inativo",
							null, ""
									+ ((Cliente) ((List) clienteEncontrado)
											.get(0)).getId());
				}

				filtrarImovelContaActionForm
						.setCodigoClienteSuperior(((Cliente) ((List) clienteEncontrado)
								.get(0)).getId().toString());
				filtrarImovelContaActionForm
						.setNomeClienteSuperior(((Cliente) ((List) clienteEncontrado)
								.get(0)).getNome());

			} else {
				httpServletRequest.setAttribute("corClienteSuperior","exception");
				filtrarImovelContaActionForm
				.setCodigoClienteSuperior("");				
				filtrarImovelContaActionForm
               			.setNomeClienteSuperior(ConstantesSistema.CODIGO_CLIENTE_INEXISTENTE);

			}
        }
		
		// PESQUISAR CLIENTE
		if (codigoCliente != null
				&& !codigoCliente.toString().trim().equalsIgnoreCase("")) {
			this.pesquisarCliente(codigoCliente, filtrarImovelContaActionForm, fachada, httpServletRequest);
		}
		
        String objetoConsulta = (String) httpServletRequest
				.getParameter("objetoConsulta");
		String inscricaoTipo = (String) httpServletRequest
				.getParameter("inscricaoTipo");

		if (objetoConsulta != null
				&& !objetoConsulta.trim().equalsIgnoreCase("")
				&& inscricaoTipo != null
				&& !inscricaoTipo.trim().equalsIgnoreCase("")) {

			switch (Integer.parseInt(objetoConsulta)) {
			// Localidade
			case 1:

				pesquisarLocalidade(inscricaoTipo,
						filtrarImovelContaActionForm, fachada,
						httpServletRequest);

				break;
			// Setor Comercial
			case 2:

				pesquisarLocalidade(inscricaoTipo,
						filtrarImovelContaActionForm, fachada,
						httpServletRequest);

				pesquisarSetorComercial(inscricaoTipo,
						filtrarImovelContaActionForm, fachada,
						httpServletRequest);

				break;
			// Quadra
			case 3:

				pesquisarLocalidade(inscricaoTipo,
						filtrarImovelContaActionForm, fachada,
						httpServletRequest);

				pesquisarSetorComercial(inscricaoTipo,
						filtrarImovelContaActionForm, fachada,
						httpServletRequest);

				pesquisarQuadra(inscricaoTipo,
						filtrarImovelContaActionForm, fachada,
						httpServletRequest);

				break;
			default:
				break;
			}
		}
		
		return retorno;
	}
	
	/**
	 * Pesquisar Clientes
	 * 
	 * @param filtroCliente
	 * @param idCliente
	 * @param clientes
	 * @param form
	 * @param fachada
	 * @param httpServletRequest
	 */
	public void pesquisarCliente(String idCliente,
			FiltrarImovelContaActionForm form,
			Fachada fachada, HttpServletRequest httpServletRequest) {
		FiltroCliente filtroCliente = new FiltroCliente();

		filtroCliente.adicionarParametro(new ParametroSimples(
				FiltroCliente.ID, idCliente));
		filtroCliente.adicionarParametro(new ParametroSimples(
				FiltroCliente.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection clienteEncontrado = fachada.pesquisar(filtroCliente,
				Cliente.class.getName());

		if (clienteEncontrado != null && !clienteEncontrado.isEmpty()) {
			// O Cliente foi encontrado
			if (((Cliente) ((List) clienteEncontrado).get(0))
					.getIndicadorUso().equals(
							ConstantesSistema.INDICADOR_USO_DESATIVO)) {
				throw new ActionServletException("atencao.cliente.inativo",
						null, ""
								+ ((Cliente) ((List) clienteEncontrado).get(0)).getId());
			}

			form.setCodigoCliente(((Cliente) ((List) clienteEncontrado)
							.get(0)).getId().toString());
			form.setNomeCliente(((Cliente) ((List) clienteEncontrado)
							.get(0)).getNome());

		} else {
			httpServletRequest.setAttribute("corCliente","exception");
			form.setCodigoCliente("");
			form.setNomeCliente(ConstantesSistema.CODIGO_CLIENTE_INEXISTENTE);
		}
	}	
	
    private void pesquisarLocalidade(String inscricaoTipo,
    		FiltrarImovelContaActionForm form,
            Fachada fachada, HttpServletRequest httpServletRequest) {

        FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

        //Recebe o valor do campo localidadeOrigemID do formul�rio.
        localidadeIDOrigem = (String) form.getLocalidadeOrigemID();

        filtroLocalidade.adicionarParametro(new ParametroSimples(
                FiltroLocalidade.ID, localidadeIDOrigem));

        filtroLocalidade.adicionarParametro(new ParametroSimples(
                FiltroLocalidade.INDICADORUSO,
                ConstantesSistema.INDICADOR_USO_ATIVO));

        //Retorna localidade
        colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
                Localidade.class.getName());

        if (colecaoPesquisa == null || colecaoPesquisa.isEmpty() && !localidadeIDOrigem.equalsIgnoreCase("")) {
            //Localidade nao encontrada
            //Limpa os campos localidadeOrigemID e nomeLocalidadeOrigem do
            // formul�rio
        	if(form.getLocalidadeOrigemID().equals(form.getLocalidadeDestinoID()))
            {
            	form.setLocalidadeDestinoID("");
            }
        	form.setLocalidadeOrigemID("");
            form.setNomeLocalidadeOrigem("Localidade inexistente");
            httpServletRequest.setAttribute("corLocalidadeOrigem",
            		"exception");
        } else if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
            Localidade objetoLocalidade = (Localidade) Util
                    .retonarObjetoDeColecao(colecaoPesquisa);
            form.setLocalidadeOrigemID(String.valueOf(objetoLocalidade.getId()));
            form.setNomeLocalidadeOrigem(objetoLocalidade.getDescricao());
            if(form.getLocalidadeDestinoID() == null || form.getLocalidadeDestinoID().equals("") || 
            		form.getLocalidadeOrigemID().equals(form.getLocalidadeDestinoID()))
            {
            	form.setLocalidadeDestinoID(String.valueOf(objetoLocalidade.getId()));
            	form.setNomeLocalidadeDestino(objetoLocalidade.getDescricao());
            }
            httpServletRequest.setAttribute("corLocalidadeOrigem", "valor");
            httpServletRequest.setAttribute("nomeCampo", "setorComercialOrigemCD");
        }
        //Recebe o valor do campo localidadeDestinoID do formul�rio.
        localidadeIDDestino = (String) form.getLocalidadeDestinoID();

        
        /*
         * Alterado por Raphael Rossiter em 26/12/2007
         * OBJ: Corrigir bug aberto por Rosana Carvalho por email em 26/12/2007
         */
        if (localidadeIDDestino != null
            && !localidadeIDDestino.trim().equalsIgnoreCase("")) {
        	
        	//Limpa os parametros do filtro
            filtroLocalidade.limparListaParametros();
            
            filtroLocalidade.adicionarParametro(new ParametroSimples(
                    FiltroLocalidade.ID, localidadeIDDestino));

            filtroLocalidade.adicionarParametro(new ParametroSimples(
                    FiltroLocalidade.INDICADORUSO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

            //Retorna localidade
            colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
                    Localidade.class.getName());

            if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                //Localidade nao encontrada
                //Limpa os campos localidadeDestinoID e nomeLocalidadeDestino
                // do formul�rio
                form.setLocalidadeDestinoID("");
                form.setNomeLocalidadeDestino("Localidade inexistente");
                httpServletRequest.setAttribute("corLocalidadeDestino",
                        "exception");
                httpServletRequest.setAttribute("nomeCampo", "localidadeDestinoID");
            } else {
                Localidade objetoLocalidade = (Localidade) Util
                        .retonarObjetoDeColecao(colecaoPesquisa);
                form.setLocalidadeDestinoID(String.valueOf(objetoLocalidade.getId()));
                form.setNomeLocalidadeDestino(objetoLocalidade.getDescricao());
                httpServletRequest
                        .setAttribute("corLocalidadeDestino", "valor");
                if(!form.getSetorComercialOrigemCD().equals(""))
                {
                	httpServletRequest.setAttribute("nomeCampo", "setorComercialDestinoCD");
                }
            }
        }
        
    }
    
    private void pesquisarSetorComercial(String inscricaoTipo,
    		FiltrarImovelContaActionForm form,
            Fachada fachada, HttpServletRequest httpServletRequest) {

        FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

        //Recebe o valor do campo localidadeOrigemID do formul�rio.
        localidadeIDOrigem = (String) form.getLocalidadeOrigemID();

        // O campo localidadeOrigemID ser� obrigat�rio
        if (localidadeIDOrigem != null
                && !localidadeIDOrigem.trim().equalsIgnoreCase("")) {

            setorComercialCDOrigem = (String) form.getSetorComercialOrigemCD();

            //Adiciona o id da localidade que est� no formul�rio para
            // compor a pesquisa.
            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.ID_LOCALIDADE, localidadeIDOrigem));

            //Adiciona o c�digo do setor comercial que esta no formul�rio
            // para compor a pesquisa.
            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
                    setorComercialCDOrigem));

            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.INDICADORUSO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

            //Retorna setorComercial
            colecaoPesquisa = fachada.pesquisar(filtroSetorComercial,
                    SetorComercial.class.getName());

            if (colecaoPesquisa == null || colecaoPesquisa.isEmpty() && 
            		!form.getSetorComercialOrigemCD().equals("")) {
                //Setor Comercial nao encontrado
                //Limpa os campos setorComercialOrigemCD,
                // nomeSetorComercialOrigem e setorComercialOrigemID do
                // formul�rio
            	if(form.getSetorComercialOrigemCD().equals(form.getSetorComercialDestinoCD()))
                {
                	form.setSetorComercialDestinoCD("");
                }
            	form.setSetorComercialOrigemCD("");
                form.setSetorComercialOrigemID("");
                form.setNomeSetorComercialOrigem("Setor comercial inexistente");
                httpServletRequest.setAttribute("corSetorComercialOrigem",
                        "exception");
            } else if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()){
                SetorComercial objetoSetorComercial = (SetorComercial) Util
                        .retonarObjetoDeColecao(colecaoPesquisa);
                form.setSetorComercialOrigemCD(String
                                .valueOf(objetoSetorComercial.getCodigo()));
                form.setSetorComercialOrigemID(String
                                .valueOf(objetoSetorComercial.getId()));
                form.setNomeSetorComercialOrigem(objetoSetorComercial
                                .getDescricao());
                if(form.getSetorComercialDestinoCD() == null || form.getSetorComercialDestinoCD().equals("") ||
                		form.getSetorComercialDestinoCD().equals(form.getSetorComercialOrigemCD()))
                {
                    form.setSetorComercialDestinoCD(String
                                    .valueOf(objetoSetorComercial.getCodigo()));
                    form.setSetorComercialDestinoID(String
                                    .valueOf(objetoSetorComercial.getId()));
                    form.setNomeSetorComercialDestino(objetoSetorComercial
                                    .getDescricao());
                }
                httpServletRequest.setAttribute("corSetorComercialOrigem",
                        "valor");
               	httpServletRequest.setAttribute("nomeCampo", "quadraOrigemNM");
            }
        } else {
            //Limpa o campo setorComercialOrigemCD do formul�rio
        	if (!form.getSetorComercialOrigemCD().equals(""))
        	{
        		form.setSetorComercialOrigemCD("");
        		form.setNomeSetorComercialOrigem("Informe a localidade da inscri��o de origem.");
        		httpServletRequest.setAttribute("corSetorComercialOrigem",
                    "exception");
        	}
        }
        //Recebe o valor do campo localidadeDestinoID do formul�rio.
        localidadeIDDestino = (String) form.getLocalidadeDestinoID();

        // O campo localidadeOrigem ser� obrigat�rio
        if (localidadeIDDestino != null
                && !localidadeIDDestino.trim().equalsIgnoreCase("")) {

            setorComercialCDDestino = (String) form.getSetorComercialDestinoCD();

            //limpa o filtro
            filtroSetorComercial.limparListaParametros();
            
            //Adiciona o id da localidade que est� no formul�rio para
            // compor a pesquisa.
            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.ID_LOCALIDADE, localidadeIDDestino));

            //Adiciona o c�digo do setor comercial que esta no formul�rio
            // para compor a pesquisa.
            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
                    setorComercialCDDestino));

            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.INDICADORUSO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

            //Retorna setorComercial
            colecaoPesquisa = fachada.pesquisar(filtroSetorComercial,
                    SetorComercial.class.getName());

            if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                //Setor Comercial nao encontrado
                //Limpa os campos setorComercialDestinoCD,
                // nomeSetorComercialDestino e setorComercialDestinoID do
                // formul�rio
                form.setSetorComercialDestinoCD("");
                form.setSetorComercialDestinoID("");
                form.setNomeSetorComercialDestino("Setor comercial inexistente");
                httpServletRequest.setAttribute("corSetorComercialDestino",
                        "exception");
            } else {
                SetorComercial objetoSetorComercial = (SetorComercial) Util
                        .retonarObjetoDeColecao(colecaoPesquisa);
                form.setSetorComercialDestinoCD(String.valueOf(objetoSetorComercial.getCodigo()));
                form.setSetorComercialDestinoID(String.valueOf(objetoSetorComercial.getId()));
                form.setNomeSetorComercialDestino(objetoSetorComercial
                                .getDescricao());
                httpServletRequest.setAttribute("corSetorComercialDestino",
                        "valor");
                if(!form.getQuadraOrigemNM().equals(""))
                {
                	httpServletRequest.setAttribute("nomeCampo", "quadraDestinoNM");
                }
            }
        } else {
            //Limpa o campo setorComercialDestinoCD do formul�rio
            form.setSetorComercialDestinoCD("");
            form.setNomeSetorComercialDestino("Informe a localidade da inscri��o de destino.");
            httpServletRequest.setAttribute("corSetorComercialDestino",
                    "exception");
        }
    }

    private void pesquisarQuadra(String inscricaoTipo,
    		FiltrarImovelContaActionForm form,
            Fachada fachada, HttpServletRequest httpServletRequest) {

        FiltroQuadra filtroQuadra = new FiltroQuadra();
        
        //Objetos que ser�o retornados pelo hibernate.
        filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("bairro");

        //Recebe os valores dos campos setorComercialOrigemCD e
        // setorComercialOrigemID do formul�rio.
        setorComercialCDOrigem = (String) form.getSetorComercialOrigemCD();

        setorComercialIDOrigem = (String) form.getSetorComercialOrigemID();

        // Os campos setorComercialOrigemCD e setorComercialID ser�o
        // obrigat�rios
        if (setorComercialCDOrigem != null
                && !setorComercialCDOrigem.trim().equalsIgnoreCase("")
                && setorComercialIDOrigem != null
                && !setorComercialIDOrigem.trim().equalsIgnoreCase("")) {

            quadraNMOrigem = (String) form.getQuadraOrigemNM();

            //Adiciona o id do setor comercial que est� no formul�rio para
            // compor a pesquisa.
            filtroQuadra.adicionarParametro(new ParametroSimples(
                    FiltroQuadra.ID_SETORCOMERCIAL, setorComercialIDOrigem));

            //Adiciona o n�mero da quadra que esta no formul�rio para
            // compor a pesquisa.
            filtroQuadra.adicionarParametro(new ParametroSimples(
                    FiltroQuadra.NUMERO_QUADRA, quadraNMOrigem));

            filtroQuadra.adicionarParametro(new ParametroSimples(
                    FiltroQuadra.INDICADORUSO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

            //Retorna quadra
            colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class
                    .getName());

            if (colecaoPesquisa == null || colecaoPesquisa.isEmpty() && !form.getQuadraOrigemNM().equals("")) {
                //Quadra nao encontrada
                //Limpa os campos quadraOrigemNM e quadraOrigemID do
                // formul�rio
            	if(form.getQuadraOrigemNM().equals(form.getQuadraDestinoNM()))
                {
                	form.setQuadraDestinoNM("");
                }
            	form.setQuadraOrigemNM("");
                form.setQuadraOrigemID("");
                //Mensagem de tela
                form.setQuadraMensagemOrigem("Quadra inexistente");
                httpServletRequest.setAttribute("corQuadraOrigem", "exception");
            } else if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()){
                Quadra objetoQuadra = (Quadra) Util
                        .retonarObjetoDeColecao(colecaoPesquisa);
                form.setQuadraOrigemNM(String.valueOf(objetoQuadra.getNumeroQuadra()));
                form.setQuadraOrigemID(String.valueOf(objetoQuadra.getId()));
                if(form.getQuadraDestinoNM() == null || form.getQuadraDestinoNM().equals("") || 
                		form.getQuadraOrigemNM().equals(form.getQuadraDestinoNM()))
                {
	                form.setQuadraDestinoNM(String.valueOf(objetoQuadra.getNumeroQuadra()));
                    form.setQuadraDestinoID(String.valueOf(objetoQuadra.getId()));
                }
                httpServletRequest.setAttribute("corQuadraOrigem", "valor");
                httpServletRequest.setAttribute("nomeCampo", "loteOrigem");
            }
        } else {
        	if (!form.getQuadraOrigemNM().equals(""))
        	{
        		//Limpa o campo quadraOrigemNM do formul�rio
        		form.setQuadraOrigemNM("");
        		form.setQuadraMensagemOrigem("Informe o setor comercial da inscri��o de origem.");
        		httpServletRequest.setAttribute("corQuadraOrigem", "exception");
        	}
        }
        //Recebe os valores dos campos setorComercialOrigemCD e
        // setorComercialOrigemID do formul�rio.
        setorComercialCDDestino = (String) form.getSetorComercialDestinoCD();
        setorComercialIDDestino = (String) form.getSetorComercialDestinoID();

        // Os campos setorComercialOrigemCD e setorComercialID ser�o
        // obrigat�rios
        if (setorComercialCDDestino != null
                && !setorComercialCDDestino.trim().equalsIgnoreCase("")
                && setorComercialIDDestino != null
                && !setorComercialIDDestino.trim().equalsIgnoreCase("")) {

            quadraNMDestino = (String) form.getQuadraDestinoNM();

            //Limpa os parametros do filtro
            filtroQuadra.limparListaParametros();
            
            //Adiciona o id do setor comercial que est� no formul�rio para
            // compor a pesquisa.
            filtroQuadra.adicionarParametro(new ParametroSimples(
                    FiltroQuadra.ID_SETORCOMERCIAL, setorComercialIDDestino));

            //Adiciona o n�mero da quadra que esta no formul�rio para
            // compor a pesquisa.
            filtroQuadra.adicionarParametro(new ParametroSimples(
                    FiltroQuadra.NUMERO_QUADRA, quadraNMDestino));

            filtroQuadra.adicionarParametro(new ParametroSimples(
                    FiltroQuadra.INDICADORUSO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

            //Retorna quadra
            colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class
                    .getName());

            if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                //Quadra nao encontrada
                //Limpa os campos quadraOrigemNM e quadraOrigemID do
                // formul�rio
                form.setQuadraDestinoNM("");
                form.setQuadraDestinoID("");
                //Mensagem de tela
                form.setQuadraMensagemDestino("Quadra inexistente");
                httpServletRequest.setAttribute("corQuadraDestino",
                        "exception");
            } else {
                Quadra objetoQuadra = (Quadra) Util
                        .retonarObjetoDeColecao(colecaoPesquisa);
                form.setQuadraDestinoNM(String.valueOf(objetoQuadra.getNumeroQuadra()));
                form.setQuadraDestinoID(String.valueOf(objetoQuadra.getId()));
                httpServletRequest
                        .setAttribute("corQuadraDestino", "valor");
                if(!form.getLoteOrigem().equals(""))
                {
                	httpServletRequest.setAttribute("nomeCampo", "loteDestino");
                }
            }
        } else {
            //Limpa o campo setorComercialOrigemCD do formul�rio
            form.setQuadraDestinoNM("");
            //Mensagem de tela
            form.setQuadraMensagemDestino("Informe o setor comercial da inscri��o.");
            httpServletRequest
                    .setAttribute("corQuadraDestino", "exception");
        }
    }	
}