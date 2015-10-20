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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.BairroArea;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroBairroArea;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Filtrar Registro Atendimento - Exibir
 * 
 * @author Leonardo Regis - 02/08/2006
 */
public class ExibirFiltrarRegistroAtendimentoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("filtrarRegistroAtendimento");
		// Instacia a fachada
		Fachada fachada = Fachada.getInstancia();
		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Form
		FiltrarRegistroAtendimentoActionForm filtrarRegistroAtendimentoActionForm = (FiltrarRegistroAtendimentoActionForm) actionForm;
		
		if (filtrarRegistroAtendimentoActionForm.getSituacao() != null &&
				filtrarRegistroAtendimentoActionForm.getSituacao().equals("1")) {
			filtrarRegistroAtendimentoActionForm.setColecaoAtendimentoMotivoEncerramento(null); 
		}
		
		//Colocado por Raphael Rossiter em 29/09/2006... Solicitado por F�tima
		String menu = httpServletRequest.getParameter("menu");
		
		if (menu != null && !menu.equalsIgnoreCase("")){
			filtrarRegistroAtendimentoActionForm.setSituacao(ConstantesSistema.SET_ZERO.toString());
			filtrarRegistroAtendimentoActionForm.setIndicCoordSemLogr(ConstantesSistema.NAO.toString());
			
			//Sugerindo um per�odo para realiza��o da filtragem de um RA
			//SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
			Date dataAtual = new Date();
			
			Calendar calendario = new GregorianCalendar();
			calendario.setTime(dataAtual);
			// ******************************************************************
			// Solicitado por Leonardo Vieira sem U.C. Executor: Marcio Roberto *
			// Pega quantidade de dias do m�s atual,  antes tinha fixo 30 dias. * 
			Integer qtdDias = new Integer(Util.obterUltimoDiaMes(calendario.get(Calendar.MONTH), calendario.get(Calendar.YEAR)));
			
			Date dataSugestao = Util.subtrairNumeroDiasDeUmaData(dataAtual, qtdDias-1);
			
			filtrarRegistroAtendimentoActionForm.setPeriodoAtendimentoInicial(Util.formatarData(dataSugestao));
			filtrarRegistroAtendimentoActionForm.setPeriodoAtendimentoFinal(Util.formatarData(dataAtual));
			
		}
		
		
		// Matr�cula
		if (filtrarRegistroAtendimentoActionForm.getMatriculaImovel() != null &&
				!filtrarRegistroAtendimentoActionForm.getMatriculaImovel().equals("")) {
			getImovel(filtrarRegistroAtendimentoActionForm, httpServletRequest);
		}
		
		//Cliente
		if( filtrarRegistroAtendimentoActionForm.getCodigoCliente() != null && 
				!filtrarRegistroAtendimentoActionForm.getCodigoCliente().equals("")) {
			getCliente(filtrarRegistroAtendimentoActionForm, httpServletRequest);
		}
		
		//Usu�rio
		if (filtrarRegistroAtendimentoActionForm.getLoginUsuario() != null &&
				!filtrarRegistroAtendimentoActionForm.getLoginUsuario().equals("")) {
			
			getUsuario(filtrarRegistroAtendimentoActionForm, fachada,
					filtrarRegistroAtendimentoActionForm.getLoginUsuario(), sessao);
		}
		
		// Tipo Solicita��o
		getSolicitacaoTipoCollection(sessao, fachada);
		// Especifica��o
		if (filtrarRegistroAtendimentoActionForm.getTipoSolicitacao() != null &&
				filtrarRegistroAtendimentoActionForm.getTipoSolicitacao().length > 0) {
			getSolicitacaoTipoEspecificacaoCollection(sessao, fachada, filtrarRegistroAtendimentoActionForm);
		} else {
			filtrarRegistroAtendimentoActionForm.setSelectedTipoSolicitacaoSize("0");
		}
		// Perfil Imovel
		getPerfilImovelCollection(sessao, fachada);
		// Unidade de Atendimento
		if (filtrarRegistroAtendimentoActionForm.getUnidadeAtendimentoId() != null &&
				!filtrarRegistroAtendimentoActionForm.getUnidadeAtendimentoId().equals("")) {
			getUnidade(filtrarRegistroAtendimentoActionForm, httpServletRequest, 1);
		}
		// Unidade Atual
		if (filtrarRegistroAtendimentoActionForm.getUnidadeAtualId() != null &&
				!filtrarRegistroAtendimentoActionForm.getUnidadeAtualId().equals("")) {
			getUnidade(filtrarRegistroAtendimentoActionForm, httpServletRequest, 2);
		}
		//Unidade Anterior
		if (filtrarRegistroAtendimentoActionForm.getUnidadeAnteriorId() != null &&
				!filtrarRegistroAtendimentoActionForm.getUnidadeAnteriorId().equals("")) {
			getUnidade(filtrarRegistroAtendimentoActionForm, httpServletRequest, 4);
		}
		// Unidade Superior
		if (filtrarRegistroAtendimentoActionForm.getUnidadeSuperiorId() != null &&
				!filtrarRegistroAtendimentoActionForm.getUnidadeSuperiorId().equals("")) {
			getUnidade(filtrarRegistroAtendimentoActionForm, httpServletRequest, 3);
		}
		
		// Munic�pio
		if (filtrarRegistroAtendimentoActionForm.getMunicipioId() != null &&
				!filtrarRegistroAtendimentoActionForm.getMunicipioId().equals("")) {
			getMunicipio(filtrarRegistroAtendimentoActionForm, httpServletRequest);
		}
		// Bairro & �rea do Bairro
		if (filtrarRegistroAtendimentoActionForm.getBairroCodigo() != null &&
				!filtrarRegistroAtendimentoActionForm.getBairroCodigo().equals("")) {
			// [FS009] Verificar informa��o do munic�pio
			if (filtrarRegistroAtendimentoActionForm.getMunicipioId() != null &&
					!filtrarRegistroAtendimentoActionForm.getMunicipioId().equals("")) {
				getBairro(filtrarRegistroAtendimentoActionForm, httpServletRequest);
			} else {
				throw new ActionServletException("atencao.filtrar_informar_municipio");
			}
		}
		// Logradouro
		if (filtrarRegistroAtendimentoActionForm.getLogradouroId() != null &&
				!filtrarRegistroAtendimentoActionForm.getLogradouroId().equals("")) {
			getLogradouro(filtrarRegistroAtendimentoActionForm, httpServletRequest);
		}
		
		// Atendimento Motivo Encerramento
		FiltroAtendimentoMotivoEncerramento filtroAtendimentoMotivoEncerramento = new FiltroAtendimentoMotivoEncerramento();
		filtroAtendimentoMotivoEncerramento.setCampoOrderBy(FiltroAtendimentoMotivoEncerramento.DESCRICAO);
		Collection<AtendimentoMotivoEncerramento> colecaoAtendimentoMotivoEncerramento = fachada.pesquisar(
				filtroAtendimentoMotivoEncerramento, AtendimentoMotivoEncerramento.class.getName());
		sessao.setAttribute("colecaoAtendimentoMotivoEncerramento", colecaoAtendimentoMotivoEncerramento);
		
		return retorno;
	}
	
	private void getCliente(FiltrarRegistroAtendimentoActionForm filtrarRegistroAtendimentoActionForm, HttpServletRequest httpServletRequest) {
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		// [FS0015] Valida Cliente
		FiltroCliente filtroCliente = new FiltroCliente();
		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, filtrarRegistroAtendimentoActionForm.getCodigoCliente()));
		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		//Recupera Cliente
		Collection<Cliente> colecaoCliente = fachada.pesquisar(filtroCliente, Cliente.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoCliente)){
			Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(colecaoCliente);
			filtrarRegistroAtendimentoActionForm.setNomeCliente(cliente.getNome());
			sessao.setAttribute("clienteEncontrado", "true");
		}else{
			filtrarRegistroAtendimentoActionForm.setCodigoCliente("");
			filtrarRegistroAtendimentoActionForm.setNomeCliente("Cliente Inexistente");
			sessao.removeAttribute("clienteEncontrado");
		}
	}

	/**
	 * Recupera Im�vel 
	 *
	 * @author Leonardo Regis
	 * @date 02/08/2006
	 *
	 * @param filtrarRegistroAtendimentoActionForm
	 * @param fachada
	 */
	private void getImovel(FiltrarRegistroAtendimentoActionForm filtrarRegistroAtendimentoActionForm, HttpServletRequest httpServletRequest) {
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		// [F0001] Valida Imovel
		//if (isValidateObject(filtrarRegistroAtendimentoActionForm)) {
		// Filtra Imovel
		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, 
				filtrarRegistroAtendimentoActionForm.getMatriculaImovel()));
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
		// Recupera Im�vel
		
		Collection<Imovel> colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());
		
		if (colecaoImovel != null && !colecaoImovel.isEmpty()) {
			sessao.setAttribute("inscricaoImovelEncontrada", "true");
			Imovel imovel = colecaoImovel.iterator().next();
			filtrarRegistroAtendimentoActionForm.setInscricaoImovel(imovel.getInscricaoFormatada());
		} else {
			sessao.removeAttribute("inscricaoImovelEncontrada");
			filtrarRegistroAtendimentoActionForm.setMatriculaImovel("");
			filtrarRegistroAtendimentoActionForm.setInscricaoImovel("Matr�cula inexistente");
		}
		filtrarRegistroAtendimentoActionForm.setValidaImovel("false");
		//}
	}
	/**
	 * Carrega cole��o de solicita��o tipo
	 *
	 * @author Leonardo Regis
	 * @date 03/08/2006
	 *
	 * @param sessao
	 * @param fachada
	 */
	private void getSolicitacaoTipoCollection(HttpSession sessao, Fachada fachada) {
		// Filtra Solicita��o Tipo
		FiltroSolicitacaoTipo filtroSolicitacaoTipo = new FiltroSolicitacaoTipo();
		filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipo.INDICADOR_USO, 1));
		filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipo.INDICADOR_USO_SISTEMA, 2));		
		filtroSolicitacaoTipo.setCampoOrderBy(FiltroSolicitacaoTipo.DESCRICAO);
		
		Collection<SolicitacaoTipo> colecaoSolicitacaoTipo = fachada.pesquisar(filtroSolicitacaoTipo, SolicitacaoTipo.class.getName());
		
		if (colecaoSolicitacaoTipo != null && !colecaoSolicitacaoTipo.isEmpty()) {
			sessao.setAttribute("colecaoTipoSolicitacao",	colecaoSolicitacaoTipo);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null, "Especifica��o");
		}
	}	
	/**
	 * Carrega cole��o de solicita��o tipo especifica��o.
	 *
	 * @author Leonardo Regis
	 * @date 03/08/2006
	 *
	 * @param sessao
	 * @param fachada
	 */
	private void getSolicitacaoTipoEspecificacaoCollection(HttpSession sessao, Fachada fachada, FiltrarRegistroAtendimentoActionForm filtrarRegistroAtendimentoActionForm) {
		String[] solicitacaoTipo = filtrarRegistroAtendimentoActionForm.getTipoSolicitacao();
		filtrarRegistroAtendimentoActionForm.setSelectedTipoSolicitacaoSize(solicitacaoTipo.length+"");
		if (solicitacaoTipo.length == 1) {
			// Filtra Solicita��o Tipo Especifica��o
			FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
			filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO_ID, solicitacaoTipo[0]));
			filtroSolicitacaoTipoEspecificacao.setCampoOrderBy(FiltroSolicitacaoTipoEspecificacao.DESCRICAO);
			
			Collection<SolicitacaoTipoEspecificacao> colecaoSolicitacaoTipoEspecificacao = fachada.pesquisar( filtroSolicitacaoTipoEspecificacao, SolicitacaoTipoEspecificacao.class.getName());
			
			if (colecaoSolicitacaoTipoEspecificacao != null && !colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
				sessao.setAttribute("colecaoEspecificacao",	colecaoSolicitacaoTipoEspecificacao);
			} else {
				sessao.setAttribute("colecaoEspecificacao",	new ArrayList<SolicitacaoTipoEspecificacao>());
			}
			
		} else {
			sessao.setAttribute("colecaoEspecificacao",	new ArrayList<SolicitacaoTipoEspecificacao>());
		}
	}
	
	/**
	 * Carrega cole��o de Perfil do Im�vel
	 *
	 * @author Daniel Alves
	 * @date 09/07/2010
	 *
	 * @param sessao
	 * @param fachada
	 */
	private void getPerfilImovelCollection(HttpSession sessao, Fachada fachada) {
		// Filtra Solicita��o Tipo
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);
		
		Collection<ImovelPerfil> colecaoPerfilImovel= fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
		
		if (colecaoPerfilImovel != null && !colecaoPerfilImovel.isEmpty()) {
			sessao.setAttribute("colecaoPerfilImovel",	colecaoPerfilImovel);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null, "Perfil do Imovel");
		}
	}
	
	/**
	 * Recupera Unidade
	 *
	 * [FS007] Verificar exist�ncia de unidades subordinadas
	 * 
	 * @author Leonardo Regis
	 * @date 02/08/2006
	 *
	 * @param filtrarRegistroAtendimentoActionForm
	 * @param fachada
	 * @param tipo
	 */
	private void getUnidade(FiltrarRegistroAtendimentoActionForm filtrarRegistroAtendimentoActionForm, HttpServletRequest httpServletRequest, int tipo) {
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		// [F0006] Valida Unidade Atendimento
		//if (isValidateObject(filtrarRegistroAtendimentoActionForm)) {
		String unidadeId = "";
		switch (tipo) {
		case 1:
			unidadeId = filtrarRegistroAtendimentoActionForm.getUnidadeAtendimentoId();
			filtrarRegistroAtendimentoActionForm.setUnidadeAtendimentoDescricao(getUnidadeDescricao(filtrarRegistroAtendimentoActionForm, fachada, unidadeId));
			if(filtrarRegistroAtendimentoActionForm.getUnidadeAtendimentoDescricao().equalsIgnoreCase("Unidade Inexistente")) {
				sessao.removeAttribute("unidadeAtendimentoEncontrada");
				filtrarRegistroAtendimentoActionForm.setUnidadeAtendimentoId("");
			} else {
				sessao.setAttribute("unidadeAtendimentoEncontrada","true");
				filtrarRegistroAtendimentoActionForm.setValidaUnidadeAtendimento("false");
			}
			break;
		case 2:
			unidadeId = filtrarRegistroAtendimentoActionForm.getUnidadeAtualId();
			filtrarRegistroAtendimentoActionForm.setUnidadeAtualDescricao(getUnidadeDescricao(filtrarRegistroAtendimentoActionForm, fachada, unidadeId));				
			if (filtrarRegistroAtendimentoActionForm.getUnidadeAtualDescricao().equalsIgnoreCase("Unidade Inexistente")) {
				sessao.removeAttribute("unidadeAtualEncontrada");
				filtrarRegistroAtendimentoActionForm.setUnidadeAtualId("");
			} else {
				sessao.setAttribute("unidadeAtualEncontrada","true");
				filtrarRegistroAtendimentoActionForm.setValidaUnidadeAtual("false");
			}
			break;
		case 3:
			unidadeId = filtrarRegistroAtendimentoActionForm.getUnidadeSuperiorId();
			UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
			unidadeOrganizacional.setId(new Integer(unidadeId));
			filtrarRegistroAtendimentoActionForm.setUnidadeSuperiorDescricao(getUnidadeDescricao(filtrarRegistroAtendimentoActionForm, fachada, unidadeId));
			if (filtrarRegistroAtendimentoActionForm.getUnidadeSuperiorDescricao().equalsIgnoreCase("Unidade Inexistente")) {
				sessao.removeAttribute("unidadeSuperiorEncontrada");
				filtrarRegistroAtendimentoActionForm.setUnidadeSuperiorId("");
			} else {
				// [FS007] Verificar exist�ncia de unidades subordinadas
				fachada.verificarExistenciaUnidadesSubordinadas(unidadeOrganizacional);
				sessao.setAttribute("unidadeSuperiorEncontrada","true");
				filtrarRegistroAtendimentoActionForm.setValidaUnidadeSuperior("false");
			}
			break;
		case 4:
			unidadeId = filtrarRegistroAtendimentoActionForm.getUnidadeAnteriorId();
			filtrarRegistroAtendimentoActionForm.setUnidadeAnteriorDescricao(getUnidadeDescricao(filtrarRegistroAtendimentoActionForm, fachada, unidadeId));				
			if (filtrarRegistroAtendimentoActionForm.getUnidadeAnteriorDescricao().equalsIgnoreCase("Unidade Inexistente")) {
				sessao.removeAttribute("unidadeAnteriorEncontrada");
				filtrarRegistroAtendimentoActionForm.setUnidadeAnteriorId("");
			} else {
				sessao.setAttribute("unidadeAnteriorEncontrada","true");
				filtrarRegistroAtendimentoActionForm.setValidaUnidadeAnterior("false");
			}
			break;
		default:
			break;
		}
		//}
	}
	/**
	 * Recupera a Unidade Organizacional (Atendimento, Atual e Superior)
	 *
	 * [F0006] Valida Unidade
	 * 
	 * @author Leonardo Regis
	 * @date 04/08/2006
	 *
	 * @param filtrarRegistroAtendimentoActionForm
	 * @param fachada
	 * @param unidadeId
	 * @return Descri��o da Unidade Filtrada
	 */
	private String getUnidadeDescricao(FiltrarRegistroAtendimentoActionForm filtrarRegistroAtendimentoActionForm, Fachada fachada, String unidadeId) {
		// Filtra Unidade
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, unidadeId));
		//filtroUnidadeOrganizacional.adicionarCaminhoParaCarregamentoEntidade("descricao");
		// Recupera Unidade Organizacional
		
		Collection<UnidadeOrganizacional> colecaoUnidadeOrganizacional = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
		
		if (colecaoUnidadeOrganizacional != null && !colecaoUnidadeOrganizacional.isEmpty()) {
			return colecaoUnidadeOrganizacional.iterator().next().getDescricao();
		}
		return "Unidade Inexistente";
	}
	
	/**
	 * Recupera o Usu�rio
	 *
	 * @author Raphael Rossiter
	 * @date 11/12/2006
	 *
	 * @param filtrarRegistroAtendimentoActionForm
	 * @param fachada
	 * @param idUsuario
	 * @return Descri��o da Unidade Filtrada
	 */
	private void getUsuario(FiltrarRegistroAtendimentoActionForm filtrarRegistroAtendimentoActionForm, 
			Fachada fachada, String idUsuario, HttpSession sessao) {
		
		// Filtra Usuario
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, idUsuario));
		
		
		// Recupera Usu�rio
		Collection<Usuario> colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
		if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
			Usuario usuario = colecaoUsuario.iterator().next();
			
			sessao.setAttribute("usuarioEncontrado","true");
			filtrarRegistroAtendimentoActionForm.setNomeUsuario(usuario.getNomeUsuario());
			filtrarRegistroAtendimentoActionForm.setIdUsuario(usuario.getId().toString());
		} else {
			
			sessao.removeAttribute("usuarioEncontrado");
			filtrarRegistroAtendimentoActionForm.setLoginUsuario("");
			filtrarRegistroAtendimentoActionForm.setNomeUsuario("Usu�rio Inexistente");
			filtrarRegistroAtendimentoActionForm.setIdUsuario("");
		}
	}
	
	
	/**
	 * Recupera Munic�pio
	 *
	 * @author Leonardo Regis
	 * @date 02/08/2006
	 *
	 * @param filtrarRegistroAtendimentoActionForm
	 * @param fachada
	 */
	private void getMunicipio(FiltrarRegistroAtendimentoActionForm filtrarRegistroAtendimentoActionForm, 
			HttpServletRequest httpServletRequest) {
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
		filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, 
				filtrarRegistroAtendimentoActionForm.getMunicipioId()));
		Collection<Municipio> colecaoMunicipio = fachada.pesquisar(filtroMunicipio, Municipio.class.getName());
		
		if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {
			sessao.setAttribute("municipioEncontrada", "true");
			Municipio municipio = colecaoMunicipio.iterator().next();
			filtrarRegistroAtendimentoActionForm.setMunicipioDescricao(municipio.getNome());
		} else {
			sessao.removeAttribute("municipioEncontrada");
			filtrarRegistroAtendimentoActionForm.setMunicipioId("");
			filtrarRegistroAtendimentoActionForm.setMunicipioDescricao("Munic�pio inexistente");
		}
		filtrarRegistroAtendimentoActionForm.setValidaMunicipio("false");
	}
	
	/**
	 * Recupera Bairro
	 *
	 * @author Leonardo Regis
	 * @date 02/08/2006
	 *
	 * @param filtrarRegistroAtendimentoActionForm
	 * @param fachada
	 * @param sessao
	 */
	private void getBairro(FiltrarRegistroAtendimentoActionForm filtrarRegistroAtendimentoActionForm, HttpServletRequest httpServletRequest) {
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		// [F0010] Valida Bairro
		//if (isValidateObject(filtrarRegistroAtendimentoActionForm)) {
		// Filtra Bairro
		FiltroBairro filtroBairro = new FiltroBairro();
		filtroBairro.adicionarParametro(new ParametroSimples(FiltroBairro.CODIGO, 
				filtrarRegistroAtendimentoActionForm.getBairroCodigo()));
		filtroBairro.adicionarParametro(new ParametroSimples(FiltroBairro.MUNICIPIO_ID, 
				filtrarRegistroAtendimentoActionForm.getMunicipioId()));
		//filtroBairro.adicionarCaminhoParaCarregamentoEntidade("nome");
		// Recupera Munic�pio
		
		Collection<Bairro> colecaoBairro = fachada.pesquisar(filtroBairro, Bairro.class.getName());
		
		if (colecaoBairro != null && !colecaoBairro.isEmpty()) {
			sessao.setAttribute("bairroEncontrada", "true");
			Bairro bairro = colecaoBairro.iterator().next();
			filtrarRegistroAtendimentoActionForm.setBairroId(bairro.getId().toString());
			filtrarRegistroAtendimentoActionForm.setBairroDescricao(bairro.getNome());
			carregaBairroArea(bairro.getId(), fachada, sessao);
		} else {
			sessao.removeAttribute("bairroEncontrada");
			filtrarRegistroAtendimentoActionForm.setBairroId("");
			filtrarRegistroAtendimentoActionForm.setBairroCodigo("");
			filtrarRegistroAtendimentoActionForm.setBairroDescricao("Bairro inexistente");
		}
		filtrarRegistroAtendimentoActionForm.setValidaBairro("false");
		//}
	}
	
	/**
	 * Recupera Logradouro
	 *
	 * @author Leonardo Regis
	 * @date 02/08/2006
	 *
	 * @param filtrarRegistroAtendimentoActionForm
	 * @param fachada
	 */
	private void getLogradouro(FiltrarRegistroAtendimentoActionForm filtrarRegistroAtendimentoActionForm, HttpServletRequest httpServletRequest) {
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		// [F0011] Valida Logradouro
		//if (isValidateObject(filtrarRegistroAtendimentoActionForm)) {
		// Filtra Logradouro
		FiltroLogradouro filtroLogradouro = new FiltroLogradouro();
		filtroLogradouro.adicionarParametro(new ParametroSimples(FiltroLogradouro.ID, 
				filtrarRegistroAtendimentoActionForm.getLogradouroId()));
		
		//filtroLogradouro.adicionarCaminhoParaCarregamentoEntidade("nome");
		// Recupera Logradouro
		Collection<Logradouro> colecaoLogradouro = fachada.pesquisar(filtroLogradouro, Logradouro.class.getName());
		if (colecaoLogradouro != null && !colecaoLogradouro.isEmpty()) {
			sessao.setAttribute("logradouroEncontrada", "true");
			Logradouro logradouro = colecaoLogradouro.iterator().next();
			filtrarRegistroAtendimentoActionForm.setLogradouroDescricao(logradouro.getNome());
		} else {
			sessao.removeAttribute("logradouroEncontrada");
			filtrarRegistroAtendimentoActionForm.setLogradouroId("");
			filtrarRegistroAtendimentoActionForm.setLogradouroDescricao("Logradouro inexistente");
		}
		filtrarRegistroAtendimentoActionForm.setValidaLogradouro("false");
		//}
	}
	/**
	 * Carrega �rea do Bairro de acordo com o bairro informado 
	 *
	 * @author Leonardo Regis
	 * @date 04/08/2006
	 *
	 * @param bairroId
	 * @param fachada
	 * @param sessao
	 */
	private void carregaBairroArea(int bairroId, Fachada fachada, HttpSession sessao) {
		FiltroBairroArea filtroBairroArea = new FiltroBairroArea();
		filtroBairroArea.adicionarParametro(new ParametroSimples(FiltroBairroArea.ID_BAIRRO, bairroId));
		//filtroBairroArea.adicionarCaminhoParaCarregamentoEntidade("nome");
		// Recupera �rea do Bairro
		Collection<BairroArea> colecaoBairroArea = fachada.pesquisar(filtroBairroArea, BairroArea.class.getName());
		if (colecaoBairroArea != null && !colecaoBairroArea.isEmpty()) {
			sessao.setAttribute("colecaoBairroArea", colecaoBairroArea);
		} else {
			sessao.removeAttribute("colecaoBairroArea");
		}
	}
	/**
	 * Valida Objeto com <Enter> da tela
	 *
	 * @author Leonardo Regis
	 * @date 02/08/2006
	 *
	 * @return est� validando algum objeto atrav�s do enter?
	 */
	/*private boolean isValidateObject(FiltrarRegistroAtendimentoActionForm form) {
	 boolean toReturn = false;
	 if (form.getValidaImovel().equalsIgnoreCase("true") || 
	 form.getValidaUnidadeAtendimento().equalsIgnoreCase("true") || 
	 form.getValidaUnidadeAtual().equalsIgnoreCase("true") || 
	 form.getValidaUnidadeSuperior().equalsIgnoreCase("true") || 
	 form.getValidaMunicipio().equalsIgnoreCase("true") || 
	 form.getValidaBairro().equalsIgnoreCase("true") || 
	 form.getValidaLogradouro().equalsIgnoreCase("true")) {
	 toReturn = true;
	 }
	 return toReturn;		
	 }*/
}