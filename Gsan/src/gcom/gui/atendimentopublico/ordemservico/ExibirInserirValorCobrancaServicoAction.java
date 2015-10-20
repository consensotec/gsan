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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.Subcategoria;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0391] Inserir Valor de Cobran�a de Servi�o
 * 
 * Este caso de uso permite a inclus�o de um novo valor de cobran�a de servi�o 
 *
 * @author Leonardo Regis
 * @date 28/09/2006
 */
public class ExibirInserirValorCobrancaServicoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		HttpSession sessao = httpServletRequest.getSession(false);
		ActionForward retorno = actionMapping.findForward("inserirValorCobrancaServico");

		Fachada fachada = Fachada.getInstancia();
		
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		
		InserirValorCobrancaServicoActionForm form = (InserirValorCobrancaServicoActionForm) actionForm;
		
		this.getPerfilImovelCollection(sessao);
		this.getHidrometroCapacidadeCollection(sessao, fachada);
		
		// Pesquisar Categoria
		this.pesquisarCategoria(httpServletRequest, form);
		
		// pesquisar SubCategoria
		this.pesquisarSubCategoria(httpServletRequest, form);
		
		if(form.getTipoServico() !=null &&	!form.getTipoServico().equals("")) {
			this.getServicoTipo(form, httpServletRequest);			
		}
		
		if(form.getIndicativoTipoSevicoEconomias() == null){
			form.setIndicativoTipoSevicoEconomias(ConstantesSistema.NAO.toString());
		}
		
		if(form.getIndicadorMedido() == null){
			form.setIndicadorMedido(ConstantesSistema.NAO.toString());
		}
		
		// Verificar permiss�o especial para replicar
		boolean usuarioPodeReplicar = fachada.verificarPermissaoEspecial(PermissaoEspecial.REPLICAR_VALOR_COBRANCA_SERVICO, 
				usuarioLogado);
		
		if (usuarioPodeReplicar){
			httpServletRequest.setAttribute("usuarioPodeReplicar", "true");
			//throw new ActionServletException("atencao.necessario_permissao_especial_para_retificar_apenas_volume_esgoto");
		}else{
			httpServletRequest.setAttribute("usuarioPodeReplicar", "false");
		}
		
		if(form.getIndicadorMedido()!=null && form.getIndicadorMedido().equals(ConstantesSistema.SIM.toString())){
			if((form.getIdCategoria()!=null 
					&& !form.getIdCategoria().equals(ConstantesSistema.NUMERO_NAO_INFORMADO+""))
						|| (form.getIdSubCategoria()!=null 
							&& !form.getIdSubCategoria().equals(ConstantesSistema.NUMERO_NAO_INFORMADO+""))){
				sessao.setAttribute("capacidadeObrigatoria","sim");
			}else{
				sessao.setAttribute("capacidadeObrigatoria","nao");
			}
		}else{
			sessao.setAttribute("capacidadeObrigatoria","nao");
		}
		
		if(httpServletRequest.getParameter("menu")!=null 
				&& httpServletRequest.getParameter("menu").toString().equalsIgnoreCase("sim")){
			form.setIndicadorGeracaoDebito("1");
		}
		
		return retorno;
	}

	/**
	 * Recupera Tipo de Servi�o
	 * 
	 * [FS0001] - Verificar Servi�o Geral D�bito
	 * 
	 * @author Leonardo Regis
	 * @date 29/09/2006
	 *
	 * @param filtrarRegistroAtendimentoActionForm
	 * @param fachada
	 */
	private void getServicoTipo(InserirValorCobrancaServicoActionForm form, HttpServletRequest httpServletRequest) {
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Filtra Tipo de Servi�o
		FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
		filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.ID, form.getTipoServico()));
		filtroServicoTipo.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
		
		// Recupera Tipo de Servi�o
		Collection colecaoServicoTipo = fachada.pesquisar(filtroServicoTipo, ServicoTipo.class.getName());
		
		if (colecaoServicoTipo != null && !colecaoServicoTipo.isEmpty()) {
			sessao.setAttribute("servicoTipoEncontrada", "true");
			ServicoTipo servicoTipo = (ServicoTipo) colecaoServicoTipo.iterator().next();
			form.setNomeTipoServico(servicoTipo.getDescricao());
//			if (servicoTipo.getDebitoTipo() == null) {
//				throw new ActionServletException("atencao.valor_cobranca_tipo_servico_sem_debito", null, servicoTipo.getDescricao());
//			}
		} else {
			sessao.removeAttribute("servicoTipoEncontrada");
			form.setTipoServico("");
			form.setNomeTipoServico("Tipo de Servi�o inexistente");
		}
	}	
	
	/**
	 * Carrega a cole��o de capacidade de hidr�metro 
	 *
	 * @author Leonardo Regis
	 * @date 28/09/2006
	 *
	 * @param sessao
	 */
	private void getHidrometroCapacidadeCollection(HttpSession sessao, Fachada fachada) {
		
		// Filtro para o campo Capacidade do Hidr�metro
		FiltroHidrometroCapacidade filtroHidrometroCapacidade = new FiltroHidrometroCapacidade();
		filtroHidrometroCapacidade.adicionarParametro(new ParametroSimples(FiltroHidrometroCapacidade.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroHidrometroCapacidade.setCampoOrderBy(FiltroHidrometroCapacidade.DESCRICAO);
		
		Collection colecaoHidrometroCapacidade = fachada.pesquisar(filtroHidrometroCapacidade, HidrometroCapacidade.class.getName());
		
		if (colecaoHidrometroCapacidade != null
				&& !colecaoHidrometroCapacidade.isEmpty()) {
				sessao.setAttribute("colecaoHidrometroCapacidade", colecaoHidrometroCapacidade);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null, "Capacidade Hidr�metro");
		}
	}

	/**
	 * Carrega a cole��o de perfil de im�vel 
	 *
	 * @author Leonardo Regis
	 * @date 28/09/2006
	 *
	 * @param sessao
	 */
	private void getPerfilImovelCollection(HttpSession sessao) {
		
		Fachada fachada = Fachada.getInstancia();
		// Filtro para o campo Perfil do Im�vel 
		FiltroImovelPerfil filtroPerfilImovel = new FiltroImovelPerfil();
    	filtroPerfilImovel.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
    	filtroPerfilImovel.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);

    	Collection colecaoImovelPerfil = fachada.pesquisar(filtroPerfilImovel, ImovelPerfil.class.getName());
		
    	if (colecaoImovelPerfil != null	&& !colecaoImovelPerfil.isEmpty()) {
			sessao.setAttribute("colecaoImovelPerfil", colecaoImovelPerfil);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null, "Perfil do Im�vel");
		}
	}
	
	// Hugo Leonardo - 19/04/2010 - CRC - 3507
	// Verificar exist�ncia de dados (CATEGORIA)
	private void pesquisarCategoria(HttpServletRequest httpServletRequest, 
			InserirValorCobrancaServicoActionForm form) {
	
		
		FiltroCategoria filtroCategoria = new FiltroCategoria();
		
		filtroCategoria.setConsultaSemLimites(true);
		
		filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection colecaoCategoria = 
			this.getFachada().pesquisar(filtroCategoria, Categoria.class.getName());
		
		if(colecaoCategoria == null || colecaoCategoria.isEmpty()){
			throw new ActionServletException("atencao.naocadastrado", null, "Categorias");
		}else{
			httpServletRequest.setAttribute("colecaoCategoria", colecaoCategoria);
		}
	}
	
	// Hugo Leonardo - 19/04/2010 - CRC - 3507
    // [FS0004] - Verificar exist�ncia de dados (SUBCATEGORIA)
	private void pesquisarSubCategoria(HttpServletRequest httpServletRequest, 
			InserirValorCobrancaServicoActionForm form) {
	
		String idCategoria = form.getIdCategoria();
		Collection colecaoSubCategoria  = null;
		
		if(idCategoria != null && !idCategoria.equalsIgnoreCase("-1")){
			FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria(FiltroSubCategoria.DESCRICAO);
			
			filtroSubCategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.CATEGORIA_ID, idCategoria));
			filtroSubCategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.INDICADOR_USO, 
					ConstantesSistema.INDICADOR_USO_ATIVO));
			
			colecaoSubCategoria = this.getFachada().pesquisar(filtroSubCategoria, Subcategoria.class.getName());
			
			if(colecaoSubCategoria == null || colecaoSubCategoria.isEmpty()){
				throw new ActionServletException("atencao.naocadastrado", null, "SubCategorias");
			}else{
				httpServletRequest.setAttribute("colecaoSubCategoria", colecaoSubCategoria);
			}
		}else{
			httpServletRequest.setAttribute("colecaoSubCategoria", colecaoSubCategoria);
		}
	}
}