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
package gsan.gui.atualizacaocadastral;

import gsan.atualizacaocadastral.FiltroMensagemAtualizacaoCadastralDM;
import gsan.atualizacaocadastral.MensagemAtualizacaoCadastralDM;
import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.cliente.FiltroCliente;
import gsan.cadastro.empresa.Empresa;
import gsan.cadastro.empresa.FiltroEmpresa;
import gsan.cadastro.imovel.FiltroImovel;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.FiltroQuadra;
import gsan.cadastro.localidade.FiltroSetorComercial;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.Quadra;
import gsan.cadastro.localidade.SetorComercial;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesSistema;
import gsan.util.ErroRepositorioException;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;
import gsan.util.filtro.ParametroSimplesIn;
import gsan.util.filtro.ParametroSimplesNotIn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Jonathan Marcos
 * @since 21/10/2014
 */
public class ExibirFiltrarDadosCadastraisImoveisInconsistentesDMAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.
				findForward("exibirFiltrarDadosCadastraisImoveisInconsistentes");

		FiltrarDadosCadastraisImoveisInconsistentesDMActionForm filtrarDadosCadastraisImoveisInconsistentesDMActionForm = 
				(FiltrarDadosCadastraisImoveisInconsistentesDMActionForm) actionForm;

		filtrarDadosCadastraisImoveisInconsistentesDMActionForm.setIdRegistro(null);
		
		pesquisarEmpresa(filtrarDadosCadastraisImoveisInconsistentesDMActionForm,httpServletRequest);
			
		if(filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdEmpresa() != null 
				&& !filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdEmpresa().equals("-1")){
			pesquisarCadastrador(filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdEmpresa(), httpServletRequest);
			pesquisarMensagemAtualizacaoCadastralDM(httpServletRequest);
		}else{
			filtrarDadosCadastraisImoveisInconsistentesDMActionForm.limparMovimento();
			httpServletRequest.getSession().removeAttribute("colecaoQuadras");
			httpServletRequest.getSession().removeAttribute("colecaoQuadrasSelecionadas");
		}
		
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");
		if(objetoConsulta!=null){
			processarObjetoConsulta(filtrarDadosCadastraisImoveisInconsistentesDMActionForm,
					httpServletRequest,objetoConsulta);
		}
		return retorno;
	}
    
    /**
     * Método responsável por<br>
     * pesquisar as empresas
     * @author Jonathan Marcos
     * @since 21/10/2014
     * @param request
     */
	@SuppressWarnings("unchecked")
	private void pesquisarEmpresa(FiltrarDadosCadastraisImoveisInconsistentesDMActionForm 
			filtrarDadosCadastraisImoveisInconsistentesDMActionForm,
				HttpServletRequest request) {
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa(FiltroEmpresa.DESCRICAO);
		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.
				INDICADORUSO, ConstantesSistema.SIM));
		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.
				INDICADOR_ATUALIZA_CADASTRO, ConstantesSistema.SIM));
		
		Collection<Empresa> colecaoEmpresa = Fachada.getInstancia().
				pesquisar(filtroEmpresa, Empresa.class.getName());
	
		if(colecaoEmpresa != null && !colecaoEmpresa.isEmpty()){
			request.setAttribute("colecaoEmpresa", colecaoEmpresa);
			if((filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdEmpresa()!=null 
					&& filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdEmpresa().compareTo("-1")!=0)
					&& (filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIndicadorSituacaoMovimento()!=null && 
					filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIndicadorSituacaoMovimento().compareTo("")==0)){
				filtrarDadosCadastraisImoveisInconsistentesDMActionForm.setIndicadorSituacaoMovimento("3");
			}
		}else{
			throw new ActionServletException("atencao.pesquisa_inexistente",
					null,"Empresa");
		}
	}
	
    /**
	 * Método responsável por<br>
	 * pesquisar nomes dos usuários<br>
	 * que são leituristas associados<br>
	 * a empresa
	 * @author Jonathan Marcos
	 * @since 21/10/2014
	 * @param idEmpresa
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	private void pesquisarCadastrador(String idEmpresa, HttpServletRequest request) {
		Collection<Usuario> colecaoCadastrador = getFachada().
				pesquisarUsuarioAtuCadastral(new Integer (idEmpresa));
		if(colecaoCadastrador != null && !colecaoCadastrador.isEmpty()) {
			request.setAttribute("colecaoCadastrador", colecaoCadastrador);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void pesquisarLocalidade(FiltrarDadosCadastraisImoveisInconsistentesDMActionForm 
			filtrarDadosCadastraisImoveisInconsistentesDMActionForm , HttpServletRequest httpServletRequest) {

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, 
				Integer.valueOf(filtrarDadosCadastraisImoveisInconsistentesDMActionForm.
						getIdLocalidade())));

		Collection<Localidade> colecaoLocalidade = getFachada().
				pesquisar(filtroLocalidade, Localidade.class.getName());

		if(colecaoLocalidade != null && !colecaoLocalidade.isEmpty()){
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
			filtrarDadosCadastraisImoveisInconsistentesDMActionForm.setIdLocalidade(localidade.getId().toString());
			filtrarDadosCadastraisImoveisInconsistentesDMActionForm.setDescricaoLocalidade(localidade.getDescricao());
		} else {
			filtrarDadosCadastraisImoveisInconsistentesDMActionForm.setIdLocalidade(null);
			filtrarDadosCadastraisImoveisInconsistentesDMActionForm.setDescricaoLocalidade("Localidade Inexistente");
			httpServletRequest.setAttribute("localidadeEncontrada",true);
		}
	}
	
	/**
	 * Método responsável por<br>
	 * pesquisar setor comercial
	 * @author Jonathan Marcos
	 * @since 23/10/2014
	 * @param filtrarDadosCadastraisImoveisInconsistentesDMActionForm
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	private void pesquisarSetorComercial(FiltrarDadosCadastraisImoveisInconsistentesDMActionForm 
			filtrarDadosCadastraisImoveisInconsistentesDMActionForm,
				HttpServletRequest request) {

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, Integer.
				valueOf(filtrarDadosCadastraisImoveisInconsistentesDMActionForm.
						getIdSetorComercial())));

		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.LOCALIDADE, Integer.
				valueOf(filtrarDadosCadastraisImoveisInconsistentesDMActionForm.
						getIdLocalidade())));

		Collection<SetorComercial> colecaoSetorComercial = this.getFachada().pesquisar(
				filtroSetorComercial, SetorComercial.class.getName());

		if(colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()){
			SetorComercial setorComercial = (SetorComercial) Util.
					retonarObjetoDeColecao(colecaoSetorComercial);
			filtrarDadosCadastraisImoveisInconsistentesDMActionForm.setIdSetorComercial("" + setorComercial.getCodigo());
			filtrarDadosCadastraisImoveisInconsistentesDMActionForm.setDescricaoSetorComercial(setorComercial.getDescricao());
		}else{
			filtrarDadosCadastraisImoveisInconsistentesDMActionForm.setIdSetorComercial(null);
			filtrarDadosCadastraisImoveisInconsistentesDMActionForm.setDescricaoSetorComercial("Setor Comercial Inexistente");
			request.setAttribute("setorEncontrado", true);
		}
	}
	
	/**
	 * Método responsável por<br>
	 * pesquisar as quadras
	 * @author Jonathan Marcos
	 * @since 23/10/2014
	 * @param form
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	private void pesquisarQuadra(FiltrarDadosCadastraisImoveisInconsistentesDMActionForm 
			filtrarDadosCadastraisImoveisInconsistentesDMActionForm,
				HttpServletRequest httpServletRequest) {
		/*
		 * Verifica se a Localidade
		 * e o setor estão preenchidos
		 */
		if((filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdLocalidade() != null 
				&& !filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdLocalidade().equals("")) 
					&& (filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdSetorComercial() != null 
						&& !filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdSetorComercial().equals(""))) {
			
			/*
			 * Pesquisa as quadras não
			 * selecionadas
			 */
			FiltroQuadra filtroQuadraNaoSelecionadas = new FiltroQuadra();
			filtroQuadraNaoSelecionadas.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, new Integer(filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdLocalidade())));
			filtroQuadraNaoSelecionadas.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroQuadraNaoSelecionadas.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, new Integer(filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdSetorComercial())));
			filtroQuadraNaoSelecionadas.setCampoOrderBy(FiltroQuadra.NUMERO_QUADRA);
			if(filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdQuadra()!=null 
					&& filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdQuadra().length>0
						&& filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdQuadra()[0]!=-1){
				/*
				 * Verifica se vem a 
				 * quadras selecionadas
				 */
				if(filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdQuadra()!=null && filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdQuadra().length>0){
					Collection<Integer> colecaoQuadrasNaoSelecionadasIds = new ArrayList<Integer>();
					for (int i = 0; i < filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdQuadra().length; i++) {
						colecaoQuadrasNaoSelecionadasIds.add(filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdQuadra()[i]);
					}
					filtroQuadraNaoSelecionadas.adicionarParametro(new ParametroSimplesIn(FiltroQuadra.NUMERO_QUADRA, colecaoQuadrasNaoSelecionadasIds));
				}
			}
			
			if(filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdQuadra()==null
					|| filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdQuadra()[0]!=-1){
				Collection<Quadra> colecaoQuadrasNaoSelecionadas = Fachada.getInstancia().
						pesquisar(filtroQuadraNaoSelecionadas, Quadra.class.getName());
				if(colecaoQuadrasNaoSelecionadas != null && !colecaoQuadrasNaoSelecionadas.isEmpty()) {
					httpServletRequest.getSession().setAttribute("colecaoQuadras", colecaoQuadrasNaoSelecionadas);
				} 
			}else{
				httpServletRequest.getSession().removeAttribute("colecaoQuadras");
			}
			
			
			/*
			 * Pesquisa as quadras
			 * selecionadas
			 */
			if(filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdQuadraSelecionados() != null 
					&& filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdQuadraSelecionados().length > 0
						&& filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdQuadraSelecionados()[0]!=-1) {
				FiltroQuadra filtroQuadraSelecionadas = new FiltroQuadra();
				filtroQuadraSelecionadas.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, new Integer(filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdLocalidade())));
				filtroQuadraSelecionadas.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
				filtroQuadraSelecionadas.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, new Integer(filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdSetorComercial())));
				Collection<Integer> colecaoQuadrasSelecionadasIds = new ArrayList<Integer>();
				for (int i = 0; i < filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdQuadraSelecionados().length; i++) {
					colecaoQuadrasSelecionadasIds.add(filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdQuadraSelecionados()[i]);
				}
				filtroQuadraSelecionadas.adicionarParametro(new ParametroSimplesIn(FiltroQuadra.NUMERO_QUADRA, colecaoQuadrasSelecionadasIds));
				filtroQuadraSelecionadas.setCampoOrderBy(FiltroQuadra.NUMERO_QUADRA);
				Collection<Quadra> colecaoQuadrasSelecionadas = Fachada.getInstancia().
						pesquisar(filtroQuadraSelecionadas, Quadra.class.getName());	
				if (colecaoQuadrasSelecionadas != null && !colecaoQuadrasSelecionadas.isEmpty()) {
					httpServletRequest.getSession().setAttribute("colecaoQuadrasSelecionadas", colecaoQuadrasSelecionadas);
				} 
			}else{
				httpServletRequest.getSession().removeAttribute("colecaoQuadrasSelecionadas");
			}
		}	
	}
	
	/**
	 * Método responsável por<br>
	 * verificar o tipo de inconsistência
	 * @author Jonathan Marcos
	 * @since 24/11/2014
	 * @param filtrarDadosCadastraisImoveisInconsistentesDMActionForm
	 */
	private void verificarTipoInconsistencia(FiltrarDadosCadastraisImoveisInconsistentesDMActionForm 
			filtrarDadosCadastraisImoveisInconsistentesDMActionForm){
		if(filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIndicadorSituacaoMovimento()!=null 
				&& filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIndicadorSituacaoMovimento().compareTo("2")==0){
			filtrarDadosCadastraisImoveisInconsistentesDMActionForm.setIdTipoInconsistencia("-1");
		}
	}
	
	/**
	 * Método responsável por<br>
	 * pesquisar as mensagems<br>
	 * atualização cadastral
	 * @author Jonathan Marcos
	 * @since 05/11/2014
	 * @param httpServletRequest
	 */
	@SuppressWarnings("unchecked")
	private void pesquisarMensagemAtualizacaoCadastralDM(HttpServletRequest httpServletRequest) {
		Collection<Integer> colecaoIdsMensagens = new ArrayList<Integer>();
//		colecaoIdsMensagens.add(MensagemAtualizacaoCadastralDM.ATUALIZACAO_PENDENTE_POR_INSCRICAO);
		colecaoIdsMensagens.add(MensagemAtualizacaoCadastralDM.ATUALIZACAO_COM_SUCESSO);
//		colecaoIdsMensagens.add(MensagemAtualizacaoCadastralDM.ATUALIZACAO_PENDENTE_POR_LOGRADOURO);
		
		FiltroMensagemAtualizacaoCadastralDM filtroMensagemAtualizacaoCadastralDM = new FiltroMensagemAtualizacaoCadastralDM();
		filtroMensagemAtualizacaoCadastralDM.adicionarParametro( new ParametroSimplesNotIn(FiltroMensagemAtualizacaoCadastralDM.ID, colecaoIdsMensagens));
		filtroMensagemAtualizacaoCadastralDM.setCampoOrderBy(FiltroMensagemAtualizacaoCadastralDM.MENSAGEM);
		
		Collection<MensagemAtualizacaoCadastralDM> colecaoMensagemAtualizacaoCadastralDM = Fachada.getInstancia().
				pesquisar(filtroMensagemAtualizacaoCadastralDM, MensagemAtualizacaoCadastralDM.class.getName());
		if(colecaoMensagemAtualizacaoCadastralDM != null && !colecaoMensagemAtualizacaoCadastralDM.isEmpty()){
			httpServletRequest.setAttribute("colecaoMensagem", colecaoMensagemAtualizacaoCadastralDM);
		} 
	}
	
	/**
	 * Método responsável por<br>
	 * pesquisa o cliente
	 * @author Jonathan Marcos
	 * @since 23/10/2014
	 * @param form
	 * @param request
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void pesquisarCliente(FiltrarDadosCadastraisImoveisInconsistentesDMActionForm 
			filtrarDadosCadastraisImoveisInconsistentesDMActionForm,
				HttpServletRequest httpServletRequest) {
		FiltroCliente filtroCliente = new FiltroCliente();
		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, Long.valueOf(filtrarDadosCadastraisImoveisInconsistentesDMActionForm.
				getCodigoCliente())));
		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<Cliente> clienteEncontrado = Fachada.getInstancia().
				pesquisar(filtroCliente, Cliente.class.getName());

		if (clienteEncontrado != null && !clienteEncontrado.isEmpty()) {
			filtrarDadosCadastraisImoveisInconsistentesDMActionForm.setCodigoCliente(""+ ((Cliente) ((List) clienteEncontrado).get(0)).getId());
			filtrarDadosCadastraisImoveisInconsistentesDMActionForm.setNomeCliente(((Cliente) ((List) clienteEncontrado).get(0)).getNome());
			httpServletRequest.removeAttribute("clienteEncontrada");
		} else {
			filtrarDadosCadastraisImoveisInconsistentesDMActionForm.setCodigoCliente("");
			httpServletRequest.setAttribute("clienteEncontrada", "true");
			filtrarDadosCadastraisImoveisInconsistentesDMActionForm.setNomeCliente("Cliente Inexistente");
		}
	}
	
	/**
	 * Método responsável por<br>
	 * pesquisar imóvel
	 * @author Jonathan Marcos
	 * @since 23/10/2014
	 * @param form
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	private void pesquisarImovel(FiltrarDadosCadastraisImoveisInconsistentesDMActionForm 
			filtrarDadosCadastraisImoveisInconsistentesDMActionForm, 
				HttpServletRequest httpServletRequest) {
		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdImovel()));
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.INDICADOR_IMOVEL_EXCLUIDO, ConstantesSistema.NAO));
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
		Collection<Imovel> colecaoImovel = 
				getFachada().pesquisar(filtroImovel, Imovel.class.getName());
		
		if(colecaoImovel != null && !colecaoImovel.isEmpty()) {
			Imovel imovel = colecaoImovel.iterator().next();
			filtrarDadosCadastraisImoveisInconsistentesDMActionForm.setInscricaoImovel(imovel.getInscricaoFormatada());
			httpServletRequest.removeAttribute("imovelEncontrada");
		}else{
			filtrarDadosCadastraisImoveisInconsistentesDMActionForm.setIdImovel("");
			filtrarDadosCadastraisImoveisInconsistentesDMActionForm.setInscricaoImovel("Matrícula inexistente");
			httpServletRequest.setAttribute("imovelEncontrada", "true");
		}
	}
	
	/**
	 * Método responsável por<br>
	 * processar o objeto consulta
	 * @author Jonathan Marcos
	 * @since 23/10/2014
	 * @param filtrarDadosCadastraisImoveisInconsistentesDMActionForm
	 * @param httpServletRequest
	 * @param objetoConsulta
	 */
	private void processarObjetoConsulta(FiltrarDadosCadastraisImoveisInconsistentesDMActionForm 
			filtrarDadosCadastraisImoveisInconsistentesDMActionForm,HttpServletRequest httpServletRequest,
				String objetoConsulta){
		if(objetoConsulta.compareTo("2")==0){
			pesquisarLocalidade(filtrarDadosCadastraisImoveisInconsistentesDMActionForm, 
					httpServletRequest);
		}else if(objetoConsulta.compareTo("3")==0){
			pesquisarSetorComercial(filtrarDadosCadastraisImoveisInconsistentesDMActionForm,
					httpServletRequest);
			pesquisarQuadra(filtrarDadosCadastraisImoveisInconsistentesDMActionForm, 
					httpServletRequest);
		}else if(objetoConsulta.compareTo("4")==0){
			pesquisarQuadra(filtrarDadosCadastraisImoveisInconsistentesDMActionForm, 
					httpServletRequest);
			verificarTipoInconsistencia(filtrarDadosCadastraisImoveisInconsistentesDMActionForm);
		}else if(objetoConsulta.compareTo("6")==0){
			pesquisarImovel(filtrarDadosCadastraisImoveisInconsistentesDMActionForm, 
					httpServletRequest);
		}else if(objetoConsulta.compareTo("7")==0){
			pesquisarCliente(filtrarDadosCadastraisImoveisInconsistentesDMActionForm, 
					httpServletRequest);
		}else if(objetoConsulta.compareTo("8")==0){
			verificarTipoInconsistencia(filtrarDadosCadastraisImoveisInconsistentesDMActionForm);
		}else if(objetoConsulta.compareTo("limpar")==0){
			filtrarDadosCadastraisImoveisInconsistentesDMActionForm.limpar();
			httpServletRequest.getSession().removeAttribute("colecaoQuadras");
			httpServletRequest.getSession().removeAttribute("colecaoQuadrasSelecionadas");
		}
	}
}