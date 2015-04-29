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
import gsan.cadastro.imovel.FiltroImovelPerfil;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.imovel.ImovelContaEnvio;
import gsan.cadastro.imovel.ImovelPerfil;
import gsan.cadastro.sistemaparametro.SistemaParametro;
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
public class ExibirAtualizarImovelConclusaoAction extends GcomAction {
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
		
		ActionForward retorno = actionMapping
		.findForward("atualizarImovelConclusao");
		
		// obtendo uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);
		
		DynaValidatorForm inserirImovelConclusaoActionForm = (DynaValidatorForm) actionForm;
		
		/*
		 * GIS
		 * ==============================================================================================================	
		 */
		sessao.setAttribute("gis",true);		
		
		GisHelper gisHelper = (GisHelper) sessao.getAttribute("gisHelper");	
		
		if(gisHelper!= null){					
			carregarDadosGis(gisHelper,inserirImovelConclusaoActionForm,sessao,this.getFachada());
		}		
		
		//Verifica se existe cliente responsavel
		boolean eResponsavel = false;
		
		// Testa se j� existe um clinte propriet�rio
		if (sessao.getAttribute("imovelClientesNovos") == null) {
			httpServletRequest.setAttribute("envioContaListar", "naoListar");
		} else {
			Collection imovelClientes = (Collection) sessao.getAttribute("imovelClientesNovos");
			ClienteImovel clienteImovel = new ClienteImovel();
			Iterator iteratorClienteImovel = imovelClientes.iterator();
			
			while (iteratorClienteImovel.hasNext()) {
				clienteImovel = null;
				clienteImovel = (ClienteImovel) iteratorClienteImovel.next();
				
				if (clienteImovel.getClienteRelacaoTipo() != null &&
						clienteImovel.getClienteRelacaoTipo().getId() == 3 ){
					
					httpServletRequest.setAttribute("contaEnvioObrigatorio","obrigatorio");
					
					eResponsavel = true;
					
				}else if ( clienteImovel.getClienteRelacaoTipo() != null && !eResponsavel &&
						clienteImovel.getClienteRelacaoTipo().getId() != 3 ){
					
					httpServletRequest.setAttribute("contaEnvioObrigatorio","opcional");
					
				}
				
				if ( inserirImovelConclusaoActionForm.get("imovelContaEnvio") != null 
						&& !inserirImovelConclusaoActionForm.get("imovelContaEnvio").equals("") ){
					
					String envioConta = (String) inserirImovelConclusaoActionForm.get("imovelContaEnvio");
					
					if ( ( envioConta.equals("4") || envioConta.equals("5") )
							&& clienteImovel.getIndicadorNomeConta().compareTo(new Short("1")) == 0
							&& ( clienteImovel.getCliente().getEmail() == null
							|| clienteImovel.getCliente().getEmail().equals("") )){
	
							throw new ActionServletException("atencao.email.nao.cadastrado");
					}
					
				}
				
				// verifica se h� cliente respons�vel para o im�vel e verifica
				// e o campo "Extrato para responsavel" da aba de "Conclus�o" deve
				// ser marcado obrigatorimente para "Emitir" e sem a possibilidade de alteracao.
				if (clienteImovel.getClienteRelacaoTipo().getDescricao().equalsIgnoreCase(ConstantesSistema.IMOVEL_ENVIO_CONTA)) {
					httpServletRequest.setAttribute("envioContaListar","listar");
				}		
			}
			
		}
	
		String valorBloqueio = "naoSetarOpcaoEmitirComoObrigatorio";
		String perfilImovel = (String) inserirImovelConclusaoActionForm.get("perfilImovel");
		
		if (perfilImovel != null && !perfilImovel.equals("")) {
			ImovelPerfil imovelPerfilProgramaEspecial = this.getSistemaParametro().getPerfilProgramaEspecial();
			
			if(imovelPerfilProgramaEspecial != null){
				if(perfilImovel.equals(""+imovelPerfilProgramaEspecial.getId())){
					valorBloqueio = "setarOpcaoEmitirComoObrigatorio";
				}
			}
		}
		
		httpServletRequest.setAttribute("setarEmitirObrigatorio",valorBloqueio);

		
		// Cria Filtros
		FiltroImovelContaEnvio filtroImovelContaEnvio = new FiltroImovelContaEnvio();

		//Faz a pesquisa de Ocupacao Tipo
		filtroImovelContaEnvio.adicionarParametro(new ParametroSimples(
				FiltroNomeConta.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
		
		//Se existir cliente responsavel faz a pesquisa atraves do ICTE_ICCLIENTEREPONSAVEL = 1
//		if ( eResponsavel ){
//			
//			filtroImovelContaEnvio.adicionarParametro(new ParametroSimples(
//					FiltroImovelContaEnvio.INDICADOR_CLIENTE_RESPONSAVEL, "1" ));
//			
//		}//Se n�o existir cliente responsavel faz a pesquisa atraves do ICTE_ICCLIENTEREPONSAVEL = 2
//		else 
			
		if( !eResponsavel ){
			
			filtroImovelContaEnvio.adicionarParametro(new ParametroSimples(
					FiltroImovelContaEnvio.INDICADOR_CLIENTE_RESPONSAVEL, "2" ));
			
		}
		
		filtroImovelContaEnvio.setCampoOrderBy(FiltroImovelContaEnvio.DESCRICAO);

		//Cria cole�ao
		Imovel imovel = (Imovel) sessao.getAttribute("imovelAtualizacao");
		Collection colecaoImovelEnvioConta = null;

		colecaoImovelEnvioConta = 
			this.getFachada().pesquisar(filtroImovelContaEnvio, ImovelContaEnvio.class.getName());
		
		if (!colecaoImovelEnvioConta.isEmpty()) {
			
			//Coloca a cole�ao da pesquisa na sessao
			sessao.setAttribute("colecaoImovelEnnvioConta", colecaoImovelEnvioConta);
			
			/*
			 * Alterado por Raphael Rossiter em 10/09/2007 (Analista: Aryed Lins)
			 * OBJ: Marcar o indicador de emiss�o de extrato de faturamento para NAO EMITIR
			 */
			if (inserirImovelConclusaoActionForm.get("extratoResponsavel") != null &&
				!inserirImovelConclusaoActionForm.get("extratoResponsavel").equals("") ){
				
				inserirImovelConclusaoActionForm.set("extratoResponsavel", inserirImovelConclusaoActionForm.get("extratoResponsavel") );
			}else if (imovel.getIndicadorEmissaoExtratoFaturamento() != null){
				inserirImovelConclusaoActionForm.set("extratoResponsavel", imovel.getIndicadorEmissaoExtratoFaturamento());
			}
			
			httpServletRequest.setAttribute("colecaoImovelEnvioContaVazia", "false");
			
			
		} else {
			
			if ( !eResponsavel ){
				
				httpServletRequest.setAttribute("colecaoImovelEnvioContaVazia", "true");
			
			}else if ( eResponsavel ){
			
				throw new ActionServletException("atencao.naocadastrado",null,"Im�vel Conta Envio");
				
			}
				
		}
		
		if (imovel.getImovelPerfil() != null && 
				imovel.getImovelPerfil().getId().equals(ConstantesSistema.INDICADOR_TARIFA_SOCIAL)) {
			
			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
			filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.ID, ConstantesSistema.INDICADOR_TARIFA_SOCIAL));
			ImovelPerfil imovelPerfil = (ImovelPerfil) 
				Util.retonarObjetoDeColecao(this.getFachada().pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName()));
			
			if (imovelPerfil.getIndicadorBloqueaDadosSocial().equals(ConstantesSistema.SIM)){
				httpServletRequest.setAttribute("tarifaSocial", "1");
			} else{
				httpServletRequest.setAttribute("tarifaSocial", "0");
			}
		} else {
			httpServletRequest.setAttribute("tarifaSocial", "0");
		}
		
		if(imovel.getInformacoesComplementares()!= null && !imovel.getInformacoesComplementares().equals("")){
			inserirImovelConclusaoActionForm.set("informacoesComplementares", imovel.getInformacoesComplementares());
		}else{
			inserirImovelConclusaoActionForm.set("informacoesComplementares", "");
		}
		
		imovel = null;
		
		String pesquisar = httpServletRequest.getParameter("pesquisar");
		
		// Cria variaveis
		String idImovel = (String) inserirImovelConclusaoActionForm.get("idImovel");
		
		if (pesquisar != null && !pesquisar.equalsIgnoreCase("")) {
			
			// Cria variaveis
			// Cria Filtros
			FiltroImovel filtroImovel = new FiltroImovel();
			
			// Objetos que ser�o retornados pelo Hibernate
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTipo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTitulo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTipo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTitulo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal");
			
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.quadra");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.logradouroBairro.bairro.municipio.unidadeFederacao");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.logradouroCep.cep");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.logradouroCep.logradouro.logradouroTipo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.logradouroCep.logradouro.logradouroTitulo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.enderecoReferencia");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.setorComercial");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.perimetroInicial.logradouroTipo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.perimetroInicial.logradouroTitulo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.perimetroFinal.logradouroTipo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.perimetroFinal.logradouroTitulo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.imovelPrincipal");
			
			filtroImovel.adicionarParametro(new ParametroSimplesDiferenteDe(
					FiltroImovel.INDICADOR_IMOVEL_EXCLUIDO,Imovel.IMOVEL_EXCLUIDO, FiltroParametro.CONECTOR_OR, 2));
			
			filtroImovel.adicionarParametro(new ParametroNulo(FiltroImovel.INDICADOR_IMOVEL_EXCLUIDO));
			
			Collection imoveis = null;
			
			if (idImovel != null && !idImovel.trim().equalsIgnoreCase("")) {
				
				filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, new Integer(idImovel)));
				
				Imovel imovelAtualizado = (Imovel) sessao.getAttribute("imovelAtualizacao");
				
				// testa se o imovel a ser principal � o mesmo que esta sendo
				// atualizado
				if (idImovel.equals(imovelAtualizado.getId().toString())) {
					
					// atualizarImovelPrincipalActionForm.set("idImovel","");
					throw new ActionServletException("atencao.imovel.principal.igual.atualizado");
				}
				
				imoveis = this.getFachada().pesquisar(filtroImovel, Imovel.class.getName());
				
				if (imoveis != null && !imoveis.isEmpty()) {
					
					filtroImovel = new FiltroImovel();
					filtroImovel.limparListaParametros();
					
					//Cria Variaveis
					String idLocalidade = (String) inserirImovelConclusaoActionForm.get("idLocalidade");
					String idSetorComercial = (String) inserirImovelConclusaoActionForm.get("idSetorComercial");
					String idQuadra = (String) inserirImovelConclusaoActionForm.get("idQuadra");
					String informacoesComplementares = (String) inserirImovelConclusaoActionForm.get("informacoesComplementares");
					
					filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,idImovel.trim()));
					filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.LOCALIDADE_ID,new Integer(idLocalidade)));
					filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.SETOR_COMERCIAL_CODIGO,new Integer(idSetorComercial)));
					filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.QUADRA_NUMERO,new Integer(idQuadra)));
					filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.INFORMACOES_COMPLEMENTARES,informacoesComplementares));
					
					Collection colecaoImovel = null;
					colecaoImovel = this.getFachada().pesquisar(filtroImovel,Imovel.class.getName());
					
					if(colecaoImovel == null || colecaoImovel.isEmpty()){
						throw new ActionServletException("atencao.imovel_principal.inexistente.quadra");        		
					}            	
					
					
					sessao.setAttribute("idImoveilPrincipal", ((Imovel) imoveis.iterator().next()).getId().toString());
					sessao.setAttribute("imoveisPrincipal", imoveis);
					
					inserirImovelConclusaoActionForm.set("idImovel", idImovel);
					inserirImovelConclusaoActionForm.set("informacoesComplementares",((Imovel) imoveis.iterator().next()).getInformacoesComplementares());
					
					httpServletRequest.setAttribute("valorMatriculaImovelPrincipal", 
							this.getFachada().pesquisarInscricaoImovel(new Integer(idImovel.trim())));
					httpServletRequest.setAttribute("idImovelPrincipalNaoEncontrado", null);                
					
				} else {
					httpServletRequest.setAttribute("idImovelPrincipalNaoEncontrado", "true");
					httpServletRequest.setAttribute("valorMatriculaImovelPrincipal", "IMOVEL INEXISTENTE");
				}
				
			}
		} else {
			if(idImovel != null && !idImovel.equals("")){
				inserirImovelConclusaoActionForm.set("idImovel", idImovel);
				httpServletRequest.setAttribute("valorMatriculaImovelPrincipal", 
						this.getFachada().pesquisarInscricaoImovel(new Integer(idImovel.trim())));
				
			}
		}
		
		
		String remover = httpServletRequest.getParameter("remover");
		String idFuncionario = (String) inserirImovelConclusaoActionForm.get("idFuncionario");
		
		if (remover != null && !remover.equalsIgnoreCase("")) {
			//Cria variaveis
			Collection imoveisPrincipaisNovos = (Collection) sessao.getAttribute("imoveisPrincipal");
			
			inserirImovelConclusaoActionForm.set("idImovel", "");
			
			if (imoveisPrincipaisNovos != null && !imoveisPrincipaisNovos.equals("")) {
				
				Iterator ImovelPrincipalIterator = imoveisPrincipaisNovos.iterator();
				
				while (ImovelPrincipalIterator.hasNext()) {
					ImovelPrincipalIterator.next();
					ImovelPrincipalIterator.remove();
					inserirImovelConclusaoActionForm.set("idImovel", "");
					sessao.removeAttribute("idImoveilPrincipal");
				}
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
		
		String idRota = (String) inserirImovelConclusaoActionForm.get("idRota");
		
		if (idRota != null && !idRota.trim().equals("")) {
			FiltroRota filtroRota = new FiltroRota();
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, idRota));
			
			Collection colecaoRotas = this.getFachada().pesquisar(filtroRota, Rota.class.getName());
			
			if (colecaoRotas != null && !colecaoRotas.isEmpty()) {
				Rota rota = (Rota) Util.retonarObjetoDeColecao(colecaoRotas);
				
				inserirImovelConclusaoActionForm.set("codigoRota", rota.getCodigo().toString());
			}
		}
		
		String idRotaAlternativa = (String) inserirImovelConclusaoActionForm.get("idRotaAlternativa");
		if (idRotaAlternativa != null && !idRotaAlternativa.trim().equals("")) {
        	FiltroRota filtroRota = new FiltroRota();
        	filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, idRotaAlternativa));
        	
        	Collection colecaoRotas = this.getFachada().pesquisar(filtroRota, Rota.class.getName());
        	
        	if (colecaoRotas != null && !colecaoRotas.isEmpty()) {
        		Rota rota = (Rota) Util.retonarObjetoDeColecao(colecaoRotas);
        		
        		inserirImovelConclusaoActionForm.set("codigoRotaAlternativa", rota.getCodigo().toString());
        	}
        }
		
		/**
		 * Verifica se o perfil do imovel � do programa especial e desabilita o extrato para responsavel
		 */
		SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();
		if ( sistemaParametro.getPerfilProgramaEspecial() != null && 
			 sistemaParametro.getPerfilProgramaEspecial().getId() != null && 
			(sistemaParametro.getPerfilProgramaEspecial().getId().intValue() == 
				 new Integer(perfilImovel).intValue() ) ) {

			sessao.setAttribute("bloquearExtratoParaResponsavel", true);
			//inserirImovelConclusaoActionForm.set("extratoResponsavel", "2");
		} else {
			
			sessao.setAttribute("bloquearExtratoParaResponsavel", "");
		}
		
		String idLocalidade = (String) inserirImovelConclusaoActionForm.get("idLocalidade");
        String idSetorComercial = (String) inserirImovelConclusaoActionForm.get("idSetorComercial");
        String idQuadra = (String) inserirImovelConclusaoActionForm.get("idQuadra");
        httpServletRequest.setAttribute("paramsMapa", idLocalidade+","+idSetorComercial+","+idQuadra);
        
		return retorno;
	}
//	=================================================================================================================
	
	
	
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
