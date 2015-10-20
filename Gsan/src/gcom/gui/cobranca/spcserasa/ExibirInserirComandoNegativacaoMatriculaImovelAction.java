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
package gcom.gui.cobranca.spcserasa;

import gcom.batch.Processo;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.FiltroNegativacaoComando;
import gcom.cobranca.FiltroNegativacaoComandoImovel;
import gcom.cobranca.NegativacaoComando;
import gcom.cobranca.NegativacaoComandoImovel;
import gcom.cobranca.Negativador;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.spcserasa.FiltroNegativador;
import gcom.spcserasa.bean.DadosNegativacaoPorImovelHelper;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
/**
 * Esta classe tem por finalidade exibir para o usu�rio a tela que receber� os
 * par�metros para realiza��o da inser��o de um Comando de Negativa��o 
 * (Por Matr�cula de Im�veis)
 * 
 * @author Ana Maria
 * @date 06/11/2007
 */
public class ExibirInserirComandoNegativacaoMatriculaImovelAction extends GcomAction {
	@SuppressWarnings("unused")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirInserirComandoNegativacaoMatriculaImovelAction");

		Fachada fachada = Fachada.getInstancia();

		//Filtro para verificar se o comando est� em execu��o
		fachada.verificarExecucaoProcesso(Processo.EXECUTAR_COMANDO_NEGATIVACAO);
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		InserirComandoNegativacaoActionForm inserirComandoNegativacaoActionForm = (InserirComandoNegativacaoActionForm) actionForm;

	    boolean processoiniciado = fachada.verificarProcessoEmExecucao(Processo.GERAR_RESUMO_DIARIO_NEGATIVACAO);
		if (processoiniciado) {
			throw new ActionServletException("atencao.negativacao_por_imovel_nao_negativar");
		}

		if (fachada.verificarExistenciaComandoNegativadorNaoRealizado(ConstantesSistema.TIPO_COMANDO_POR_MATRICULA_IMOVEIS)) {
			throw new ActionServletException("atencao.existe_comando_negativado_em_aberto");
		}

	    Collection<DadosNegativacaoPorImovelHelper> colecaoDadosNegativacaoPorImovelHelper = new ArrayList<DadosNegativacaoPorImovelHelper>();
	    
	    Collection<DadosNegativacaoPorImovelHelper> colecaoDadosRemover = new ArrayList<DadosNegativacaoPorImovelHelper>();
	    
	    //RM10652 - Verificar 
	    FiltroNegativacaoComando filtroNegativacaoComando = new FiltroNegativacaoComando();
	    filtroNegativacaoComando.adicionarParametro(new ParametroSimples(FiltroNegativacaoComando.INDICADOR_COMANDO_CRITERIO, new Integer(2)));
	    filtroNegativacaoComando.adicionarParametro(new ParametroNulo(FiltroNegativacaoComando.DATA_HORA_REALIZACAO));
	    filtroNegativacaoComando.adicionarCaminhoParaCarregamentoEntidade("negativador");
	    filtroNegativacaoComando.adicionarCaminhoParaCarregamentoEntidade("negativador.cliente");
	    
	    Collection<NegativacaoComando> colNegativacaoComandoCollection = fachada.pesquisar(filtroNegativacaoComando, 
	    					NegativacaoComando.class.getName());
	    
	    if (colNegativacaoComandoCollection != null && !colNegativacaoComandoCollection.isEmpty()
	    							&& httpServletRequest.getParameter("menu") != null){
	    	
	    	NegativacaoComando negatComando = (NegativacaoComando) Util.retonarObjetoDeColecao(colNegativacaoComandoCollection);
	    	
	    	FiltroNegativacaoComandoImovel filtroNegativacaoComandoImovel = new FiltroNegativacaoComandoImovel();
	    	filtroNegativacaoComandoImovel.adicionarParametro(
	    		new ParametroSimples(FiltroNegativacaoComandoImovel.NEGATIVACAO_COMANDO_ID, negatComando.getId()));
	    	filtroNegativacaoComandoImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroNegativacaoComandoImovel.CLIENTE);
	    	filtroNegativacaoComandoImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroNegativacaoComandoImovel.USUARIO);
	    	
	    	Collection<NegativacaoComandoImovel> colNegativacaoComandoImovel = fachada.pesquisar(filtroNegativacaoComandoImovel,
	    					NegativacaoComandoImovel.class.getName());
	    	
	    	Iterator iColNegativacaoComandoImovel = colNegativacaoComandoImovel.iterator();
	    	
	    	while (iColNegativacaoComandoImovel.hasNext()){
	    		
	    		NegativacaoComandoImovel negativacaoComandoImovel = (NegativacaoComandoImovel) iColNegativacaoComandoImovel.next();
	    		
	    		DadosNegativacaoPorImovelHelper dadosNegativacaoPorImovelHelper = new DadosNegativacaoPorImovelHelper();
				dadosNegativacaoPorImovelHelper.setIdImovel(negativacaoComandoImovel.getImovel().getId());
				dadosNegativacaoPorImovelHelper.setIdCliente(negativacaoComandoImovel.getCliente().getId());
				
				if (negativacaoComandoImovel.getCliente().getCnpj() != null){
					
					dadosNegativacaoPorImovelHelper.setCnpjCliente(negativacaoComandoImovel.getCliente().getCnpjFormatado());
				} else {
					dadosNegativacaoPorImovelHelper.setCpfCliente(negativacaoComandoImovel.getCliente().getCpfFormatado());
				}
				dadosNegativacaoPorImovelHelper.setUsuarioInclusao(negativacaoComandoImovel.getUsuario());
				
				FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
				filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, negativacaoComandoImovel.getImovel().getId()));
				filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
				
				Collection<ClienteImovel> colecaoClienteImovel = fachada.pesquisar(filtroClienteImovel, ClienteImovel.class.getName());
				ClienteImovel clienteImovel = (ClienteImovel) Util.retonarObjetoDeColecao(colecaoClienteImovel);
				
				dadosNegativacaoPorImovelHelper.setIdClienteRelacaoTipo(clienteImovel.getClienteRelacaoTipo().getId());
				
				colecaoDadosNegativacaoPorImovelHelper.add(dadosNegativacaoPorImovelHelper);
				
	    	}
	    	
	    	sessao.setAttribute("colecaoDadosNegativacaoPorImovelHelper", colecaoDadosNegativacaoPorImovelHelper);
	    	
	    	
	    	inserirComandoNegativacaoActionForm.setNomeNegativador(negatComando.getNegativador().getCliente().getNome());
	    	
	    	//Pesquisa Usuario 
	        if(negatComando.getUsuario() != null){
	        	
	        	FiltroUsuario filtroUsuario = new FiltroUsuario();  
	            
	        	filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, negatComando.getUsuario().getId()));
	            
	            Collection colecaoUsuario = fachada.pesquisar(
	                    filtroUsuario,Usuario.class.getName());
	            
	            if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
	            	httpServletRequest.setAttribute("corUsuario", "valor");
	            	
	            	inserirComandoNegativacaoActionForm.setUsuario(""
							+ ((Usuario) ((List) colecaoUsuario).get(0)).getId());
	            	inserirComandoNegativacaoActionForm.setNomeUsuario(""
							+ ((Usuario) ((List) colecaoUsuario).get(0)).getNomeUsuario());
				}
	        }
	    	
	    	inserirComandoNegativacaoActionForm.setIndicadorBaixaRenda(negatComando.getIndicadorBaixaRenda().toString());   
	    	
	    	inserirComandoNegativacaoActionForm.setIndicadorContaNomeCliente(negatComando.getIndicadorContaNomeCliente().toString());   
	    	
	    	inserirComandoNegativacaoActionForm.setIndicadorImovelCategoriaPublico(negatComando.getIndicadorOrgaoPublico().toString());  
	    	
	    	inserirComandoNegativacaoActionForm.setIndicadorCpfCnpjValido(negatComando.getIndicadorCpfCnpjValidado().toString());
	    	
	    	inserirComandoNegativacaoActionForm.setIdentificacaoCI(negatComando.getDescricaoComunicacaoInterna());
	    	
	    	inserirComandoNegativacaoActionForm.setHabilitarDesabilitarCampos(true);
	    	
	    	sessao.setAttribute("desabilitarCampos", true);
	    	
	    } else {
	    	
        //Pesquisar o Negativador
    	if (inserirComandoNegativacaoActionForm.getIdNegativador() != null) {
    		
    		FiltroNegativador filtroNegativador = new FiltroNegativador();    		
    		filtroNegativador.adicionarParametro(new ParametroSimples(
    				FiltroNegativador.ID, inserirComandoNegativacaoActionForm.getIdNegativador()));
    		filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("cliente");
    		
    		Collection negativadores = fachada.pesquisar(filtroNegativador,
    						Negativador.class.getName());
    		
    		if (negativadores != null && !negativadores.isEmpty()) {    		
    			Negativador negativador = (Negativador) Util.retonarObjetoDeColecao(negativadores);
    			inserirComandoNegativacaoActionForm.setNomeNegativador(negativador.getCliente().getNome());		
    		}
    	}
    	
        String usuario = inserirComandoNegativacaoActionForm.getUsuario();
        
        //Pesquisa Usuario 
        if(usuario != null && !usuario.equals("")){
        	
        	FiltroUsuario filtroUsuario = new FiltroUsuario();  
            
        	filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, usuario));
            
            Collection colecaoUsuario = fachada.pesquisar(
                    filtroUsuario,Usuario.class.getName());
            
            if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
            	httpServletRequest.setAttribute("corUsuario", "valor");
            	
            	inserirComandoNegativacaoActionForm.setUsuario(""
						+ ((Usuario) ((List) colecaoUsuario).get(0)).getId());
            	inserirComandoNegativacaoActionForm.setNomeUsuario(""
						+ ((Usuario) ((List) colecaoUsuario).get(0)).getNomeUsuario());
			} else {
				httpServletRequest.setAttribute("corUsuario","exception");
				inserirComandoNegativacaoActionForm
        		.setUsuario(null);
				inserirComandoNegativacaoActionForm
            		.setNomeUsuario(ConstantesSistema.USUARIO_INEXISTENTE);
			}
        }


        if(inserirComandoNegativacaoActionForm.getIndicadorBaixaRenda() == null){
        	//CRC4496 - adicionado por Vivianne Sousa - analista:Adriana - 29/06/2010
        	//Im�vel com Baixa Renda - exibir com op��o "N�O" selecionada    		
        	inserirComandoNegativacaoActionForm.setIndicadorBaixaRenda(ConstantesSistema.CONFIRMADA);   
        }
        
        if(inserirComandoNegativacaoActionForm.getIndicadorContaNomeCliente() == null){
        	//Exigir ao Menos uma Conta em Nome do Cliente Negativado  - exibir com op��o "Sim" selecionada    		
        	inserirComandoNegativacaoActionForm.setIndicadorContaNomeCliente(ConstantesSistema.CONFIRMADA);   
        }
        
        if(inserirComandoNegativacaoActionForm.getIndicadorImovelCategoriaPublico() == null){
        	//RM3388 - adicionado por Ivan Sergio - analista:Adriana - 26/01/2011
        	//Im�vel de Categoria P�blico - exibir com op��o "N�O" selecionada    		
        	inserirComandoNegativacaoActionForm.setIndicadorImovelCategoriaPublico(ConstantesSistema.NAO_CONFIRMADA);   
        }
        
       
        	/*
        	 * RM7898 - Jonathan Marcos - adicionar campo considera CPF/CNPJ Validado
        	 * [Observacao] exibir com a opcao Nao selecionada e permitir que o usuario selecione entre Sim ou Nao)
        	 */
        
        	boolean alterarSoCPFCNPJValidos = false;
	    	
	    	if(sistemaParametro.getIndicadorValidaCpfCnpj().equals(new Integer(ConstantesSistema.NAO))){
	    		
	    		//[FS0037] Verificar permiss�o especial de altera��o de valor
	        	 alterarSoCPFCNPJValidos = Fachada
	        			.getInstancia()
	        			.verificarPermissaoEspecial(
	        					PermissaoEspecial.ALTERAR_SO_CPF_CNPJ_VALIDOS,
	        					usuarioLogado);
	    		
	    		inserirComandoNegativacaoActionForm.setAlterarSoCPFCNPJValidos(alterarSoCPFCNPJValidos);           	
				inserirComandoNegativacaoActionForm.setIndicadorCpfCnpjValido(ConstantesSistema.NAO_CONFIRMADA);
				
	    	}else{ 
        	
	        	//[FS0037] Verificar permiss�o especial de altera��o de valor
	        	 alterarSoCPFCNPJValidos = Fachada
	        			.getInstancia()
	        			.verificarPermissaoEspecial(
	        					PermissaoEspecial.ALTERAR_SO_CPF_CNPJ_VALIDOS,
	        					usuarioLogado);
	        			
	        	inserirComandoNegativacaoActionForm.setAlterarSoCPFCNPJValidos(alterarSoCPFCNPJValidos);
	        					
	        	
	        	if(inserirComandoNegativacaoActionForm.getIndicadorCpfCnpjValido() == null)
	    			inserirComandoNegativacaoActionForm.setIndicadorCpfCnpjValido(ConstantesSistema.CONFIRMADA);
        	
	    	}		
	    	
		}
			//Remover Im�vel
			
			if(httpServletRequest.getParameter("idImovelRemover") != null){
				Integer idImovelRemover = new Integer(httpServletRequest.getParameter("idImovelRemover"));
				colecaoDadosNegativacaoPorImovelHelper = (Collection)sessao.getAttribute("colecaoDadosNegativacaoPorImovelHelper");
				
				if (sessao.getAttribute("colecaoDadosRemover") != null){
					colecaoDadosRemover = (Collection)sessao.getAttribute("colecaoDadosRemover");	
				}
				
				if(colecaoDadosNegativacaoPorImovelHelper != null && !colecaoDadosNegativacaoPorImovelHelper.isEmpty()){
					DadosNegativacaoPorImovelHelper dadosNegativacaoPorImovelHelper = new DadosNegativacaoPorImovelHelper();
					dadosNegativacaoPorImovelHelper.setIdImovel(idImovelRemover);
					
					Iterator it = colecaoDadosNegativacaoPorImovelHelper.iterator();
					
					while(it.hasNext()){
						
						DadosNegativacaoPorImovelHelper helper = (DadosNegativacaoPorImovelHelper) it.next();
						
						if (helper.getIdImovel().equals(idImovelRemover)){
							
							if (helper.getUsuarioInclusao().equals(usuarioLogado)){
								
								colecaoDadosRemover.add(dadosNegativacaoPorImovelHelper);
								colecaoDadosNegativacaoPorImovelHelper.remove(dadosNegativacaoPorImovelHelper);	
								break;
							} else {
								
								throw new ActionServletException(
										"atencao.usuario_sem_permissao_remover_imovel_negativacao");
							}
							
						}
					}
						
					sessao.setAttribute("colecaoDadosNegativacaoPorImovelHelper", colecaoDadosNegativacaoPorImovelHelper);
					sessao.setAttribute("colecaoDadosRemover", colecaoDadosRemover);
				}
			}
        
		return retorno;

	}

}
