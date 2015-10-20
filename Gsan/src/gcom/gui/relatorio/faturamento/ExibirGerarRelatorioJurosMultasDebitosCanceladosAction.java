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


package gcom.gui.relatorio.faturamento;

import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesInterfaceGSAN;
import gcom.util.ConstantesSistema;
import gcom.util.Internacionalizador;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *[UC0958] - Gerar Relatorio de Juros, Multas e Debitos Cancelados.
 *
 * @author Marlon Patrick
 * @since 07/10/2009
 */
public class ExibirGerarRelatorioJurosMultasDebitosCanceladosAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		GerarRelatorioJurosMultasDebitosCanceladosActionForm form = 
			(GerarRelatorioJurosMultasDebitosCanceladosActionForm) actionForm;
		
		if(Util.verificarNaoVazio(form.getIdLocalidade())){
			pesquisarLocalidade(form);			
		}
		
		if(Util.verificarNaoVazio(form.getIdUsuarioCancelamento())){
			pesquisarUsuarioCancelamento(form);
		}
		
		carregarComboboxEListbox(form);
		
		return actionMapping.findForward("exibirGerarRelatorioJurosMultasDebitosCancelados");
	}

	/**
	 * Este m�todo faz a consulta para verificar a existencia da Localidade informada pelo usu�rio.
	 * 
	 * @author Marlon Patrick
	 * @since 07/10/2009
	 */
	private void pesquisarLocalidade(GerarRelatorioJurosMultasDebitosCanceladosActionForm form){

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID,form.getIdLocalidade()));
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO
				,ConstantesSistema.INDICADOR_USO_ATIVO));
		
		boolean isUnidadeNegocioInformado = false;
		
		if(Util.isCampoComboboxInformado(form.getIdUnidadeNegocio())){
			isUnidadeNegocioInformado = true;
			
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.UNIDADE_NEGOCIO_ID,form.getIdUnidadeNegocio()));
		}
		
		Collection<Localidade> colecaoLocalidade = 
			this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());
	
		if ( Util.isVazioOrNulo(colecaoLocalidade)) {
				form.setIdLocalidade("");
				form.setLocalidadeExistente(false);

				if(isUnidadeNegocioInformado){
					form.setNomeLocalidade(
							Internacionalizador.getMensagem(ConstantesInterfaceGSAN.ATENCAO_GSAN_CAMPO1_INEXISTENTE_NA_CAMPO2_INFORMADA,
								new String[]{ConstantesInterfaceGSAN.LABEL_GSAN_LOCALIDADE,ConstantesInterfaceGSAN.LABEL_GSAN_UNIDADE_NEGOCIO}));
				}else{
					form.setNomeLocalidade(Internacionalizador.getMensagem(
							ConstantesInterfaceGSAN.ATENCAO_PESQUISA_INEXISTENTE, ConstantesInterfaceGSAN.LABEL_GSAN_LOCALIDADE));
				}

				return;
		}
		
		Localidade localidade = colecaoLocalidade.iterator().next();
		
		form.setIdLocalidade(localidade.getId().toString());
		form.setNomeLocalidade(localidade.getDescricao());
		form.setLocalidadeExistente(true);

	}

	/**
	 * Este m�todo faz a consulta para verificar a existencia do Usu�rio informado pelo usu�rio.
	 * 
	 * @author Marlon Patrick
	 * @since 07/10/2009
	 */
	private void pesquisarUsuarioCancelamento(GerarRelatorioJurosMultasDebitosCanceladosActionForm form){

		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID,
				form.getIdUsuarioCancelamento()));
		
		Collection<Usuario> colecaoUsuario = 
			this.getFachada().pesquisar(filtroUsuario, Usuario.class.getName());
	
		if ( Util.isVazioOrNulo(colecaoUsuario)) {
				form.setIdUsuarioCancelamento("");
				form.setNomeUsuarioCancelamento(
						Internacionalizador.getMensagem(
								ConstantesInterfaceGSAN.ATENCAO_PESQUISA_INEXISTENTE, ConstantesInterfaceGSAN.LABEL_GSAN_USUARIO));
				
				form.setUsuarioExistente(false);
			
				return;
		}
		
		Usuario usuario = colecaoUsuario.iterator().next();
		
		form.setIdUsuarioCancelamento(usuario.getId().toString());
		form.setNomeUsuarioCancelamento(usuario.getNomeUsuario());
		form.setUsuarioExistente(true);
	}

	/**
	 * 
	 * Esse m�todo invoca os m�todos respons�veis por carregar as cole��es de
	 * todos os combo boxes e list boxes a serem exibidos na tela.
	 *
	 * @author Marlon Patrick
	 * @since 07/10/2009
	 */
	private void carregarComboboxEListbox(GerarRelatorioJurosMultasDebitosCanceladosActionForm form) {
		carregarColecaoUnidadesNegocios(form);
		carregarColecaoTiposDebito(form);
		carregarColecaoCategorias(form);
		carregarColecaoPerfisImovel(form);
		carregarColecaoEsferasPoder(form);
	}
	
	/**
	 * M�todo consulta os perfis de im�vel ativos
	 * e seta essa cole��o no FORM para que seja exibida na tela.
	 * 
	 * @author Marlon Patrick
	 * @since 07/10/2009
	 */
	private void carregarColecaoPerfisImovel(GerarRelatorioJurosMultasDebitosCanceladosActionForm form){
		
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		filtroImovelPerfil.setConsultaSemLimites(true);
		filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);		
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(
				FiltroImovelPerfil.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<ImovelPerfil> colecaoImoveisPerfis = this.getFachada().pesquisar(
				filtroImovelPerfil, ImovelPerfil.class.getName());

		if ( Util.isVazioOrNulo(colecaoImoveisPerfis)) {
			throw new ActionServletException(ConstantesInterfaceGSAN.ATENCAO_NAO_CADASTRADO, 
					ConstantesInterfaceGSAN.LABEL_GSAN_PERFIL_IMOVEL);
		}
		
		form.setColecaoPerfisImovel(colecaoImoveisPerfis);
	}

	/**
	 * M�todo consulta as Unidades de Neg�cio ativas
	 * e seta essa cole��o no FORM para que seja exibida na tela.
	 * 
	 * @author Marlon Patrick
	 * @since 07/10/2009
	 */
	private void carregarColecaoTiposDebito(GerarRelatorioJurosMultasDebitosCanceladosActionForm form){
		
		FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
		filtroDebitoTipo.setConsultaSemLimites(true);
		filtroDebitoTipo.setCampoOrderBy(FiltroDebitoTipo.DESCRICAO);		
		filtroDebitoTipo.adicionarParametro(
				new ParametroSimples(FiltroDebitoTipo.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));		

		Collection<DebitoTipo> colecaoDebitoTipo = 
			this.getFachada().pesquisar(filtroDebitoTipo,DebitoTipo.class.getName());

		if ( Util.isVazioOrNulo(colecaoDebitoTipo)) {
			throw new ActionServletException(ConstantesInterfaceGSAN.ATENCAO_NAO_CADASTRADO, 
					ConstantesInterfaceGSAN.LABEL_GSAN_TIPO_DEBITO);
		}
		
		form.setColecaoTiposDebito(colecaoDebitoTipo);
	}

	/**
	 * M�todo consulta as Unidades de Neg�cio ativas
	 * e seta essa cole��o no FORM para que seja exibida na tela.
	 * 
	 * @author Marlon Patrick
	 * @since 07/10/2009
	 */
	private void carregarColecaoUnidadesNegocios(GerarRelatorioJurosMultasDebitosCanceladosActionForm form){
		
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
		
		form.setColecaoUnidadesNegocios(colecaoUnidadeNegocio);
	}
	
	/**
	 * M�todo consulta as categorias ativas
	 * e seta essa cole��o no FORM para que seja exibida na tela.
	 * 
	 * @author Marlon Patrick
	 * @since 07/10/2009
	 */
	private void carregarColecaoCategorias(GerarRelatorioJurosMultasDebitosCanceladosActionForm form){
		
		FiltroCategoria filtroCategoria = new FiltroCategoria();
		
		filtroCategoria.setConsultaSemLimites(true);
		filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);
		filtroCategoria.adicionarParametro(
				new ParametroSimples(FiltroCategoria.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<Categoria> colecaoCategoria = 
			this.getFachada().pesquisar(filtroCategoria, Categoria.class.getName());


		if ( Util.isVazioOrNulo(colecaoCategoria)) {
			throw new ActionServletException(ConstantesInterfaceGSAN.ATENCAO_NAO_CADASTRADO, 
					ConstantesInterfaceGSAN.LABEL_GSAN_CATEGORIA);
		}
		
		form.setColecaoCategorias(colecaoCategoria);
	}
	
	/**
	 * M�todo consulta as esferas do poder ativas
	 * e seta essa cole��o no FORM para que seja exibida na tela.
	 * 
	 * @author Marlon Patrick
	 * @since 07/10/2009
	 */
	private void carregarColecaoEsferasPoder(GerarRelatorioJurosMultasDebitosCanceladosActionForm form){
		
		FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder();
		filtroEsferaPoder.setConsultaSemLimites(true);
		filtroEsferaPoder.setCampoOrderBy(FiltroEsferaPoder.DESCRICAO);
		filtroEsferaPoder.adicionarParametro(
				new ParametroSimples(FiltroEsferaPoder.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<EsferaPoder> colecaoEsferaPoder = 
			this.getFachada().pesquisar(filtroEsferaPoder,EsferaPoder.class.getName());

		if ( Util.isVazioOrNulo(colecaoEsferaPoder)) {
			throw new ActionServletException(ConstantesInterfaceGSAN.ATENCAO_NAO_CADASTRADO, 
					ConstantesInterfaceGSAN.LABEL_GSAN_ESFERA_PODER);
		}
		
		form.setColecaoEsferasPoder(colecaoEsferaPoder);
	}
}
