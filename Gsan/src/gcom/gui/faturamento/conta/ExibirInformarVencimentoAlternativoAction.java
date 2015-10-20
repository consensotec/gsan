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
package gcom.gui.faturamento.conta;

import gcom.atendimentopublico.registroatendimento.EspecificacaoTipoValidacao;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FiltroVencimentoAlternativo;
import gcom.faturamento.VencimentoAlternativo;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirInformarVencimentoAlternativoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		HttpSession sessao = httpServletRequest.getSession(false);
		
		InformarVencimentoAlternativoActionForm informarVencimentoAlternativoActionForm = (InformarVencimentoAlternativoActionForm) actionForm;

		if (Util.verificarNaoVazio(informarVencimentoAlternativoActionForm.getIdImovel())) {

			Collection<ClienteImovel> colecaoClienteImovel = obterColecaoClienteImovel(informarVencimentoAlternativoActionForm);

			if (Util.isVazioOrNulo(colecaoClienteImovel)) {
				httpServletRequest.setAttribute("corInscricao", "exception");
        		httpServletRequest.setAttribute("nomeCampo", "idImovel");

        		limparFormESessao(informarVencimentoAlternativoActionForm,sessao);

        		informarVencimentoAlternativoActionForm.setInscricaoImovel("Matr�cula Inexistente");
			}else{
				
				setarDadosFormESessao(informarVencimentoAlternativoActionForm,colecaoClienteImovel, sessao);
			}
		}
		

		return actionMapping.findForward("informarVencimentoAlternativo");
	}

	/**
	 * Limpa todos os campos que seriam exibidos na tela, 
	 * sejam eles do form ou da sess�o.
	 *
	 *@since 23/10/2009
	 *@author Marlon Patrick
	 */
	private void limparFormESessao(
			InformarVencimentoAlternativoActionForm informarVencimentoAlternativoActionForm,
			HttpSession sessao) {

		informarVencimentoAlternativoActionForm.setIdImovel("");
		informarVencimentoAlternativoActionForm.setInscricaoImovel("");
		informarVencimentoAlternativoActionForm.setNomeClienteUsuario("");
		informarVencimentoAlternativoActionForm.setSituacaoAguaImovel("");
		informarVencimentoAlternativoActionForm.setSituacaoEsgotoImovel("");
		informarVencimentoAlternativoActionForm.setDiaVencimentoGrupo("");
		informarVencimentoAlternativoActionForm.setDiaVencimentoAtual("");
		informarVencimentoAlternativoActionForm.setDataAlteracaoVencimento("");
		informarVencimentoAlternativoActionForm.setIndicadorVencimentoMesSeguinte("");
		
		sessao.removeAttribute("vencimentoAlternativo");
		sessao.removeAttribute("colecaoNovoDiaVencimento");
		sessao.removeAttribute("imovel");
	}

	/**
	 * Este m�todo seta as informa��es a serem exibidas para o usu�rio
	 * no form e na sess�o, para que a JSP respons�vel processe os dados.
	 *
	 *@since 22/10/2009
	 *@author Marlon Patrick
	 */
	private void setarDadosFormESessao(
			InformarVencimentoAlternativoActionForm informarVencimentoAlternativoActionForm,
			Collection<ClienteImovel> colecaoClienteImovel, HttpSession sessao) {
		
		Fachada fachada = Fachada.getInstancia();

		ClienteImovel clienteImovel = colecaoClienteImovel.iterator().next();
		
		fachada.verificarExistenciaRegistroAtendimento(clienteImovel.getImovel().getId(), "atencao.vencimento_alternativo_existencia_registro_atendimento",EspecificacaoTipoValidacao.VENCIMENTO_ALTERNATIVO); 

		informarVencimentoAlternativoActionForm.setInscricaoImovel(clienteImovel.getImovel().getInscricaoFormatada());

		informarVencimentoAlternativoActionForm
				.setNomeClienteUsuario(clienteImovel.getCliente().getNome());
		
		informarVencimentoAlternativoActionForm
				.setSituacaoAguaImovel(clienteImovel.getImovel().getLigacaoAguaSituacao().getDescricao());

		informarVencimentoAlternativoActionForm
				.setSituacaoEsgotoImovel(clienteImovel.getImovel().getLigacaoEsgotoSituacao().getDescricao());

		informarVencimentoAlternativoActionForm.setDiaVencimentoGrupo("");

		if(clienteImovel.getImovel().getQuadra().getRota().getFaturamentoGrupo().getDiaVencimento()!=null
				&& !clienteImovel.getImovel().getQuadra().getRota().getFaturamentoGrupo().getDiaVencimento().equals(0)){

			informarVencimentoAlternativoActionForm
				.setDiaVencimentoGrupo(clienteImovel.getImovel().getQuadra().getRota().getFaturamentoGrupo().getDiaVencimento().toString());
		}
		
		informarVencimentoAlternativoActionForm.setDiaVencimentoAtual("");

		if(clienteImovel.getImovel().getDiaVencimento() != null && !clienteImovel.getImovel().getDiaVencimento().equals(0)){
			informarVencimentoAlternativoActionForm
				.setDiaVencimentoAtual(clienteImovel.getImovel().getDiaVencimento().toString());
		}
		

		FiltroVencimentoAlternativo filtroVencimentoAlternativo = new FiltroVencimentoAlternativo(FiltroVencimentoAlternativo.DATA_IMPLANTACAO);

		filtroVencimentoAlternativo
				.adicionarParametro(new ParametroSimples(
						FiltroVencimentoAlternativo.IMOVEL_ID, clienteImovel.getImovel().getId()));
		
		filtroVencimentoAlternativo.adicionarParametro(new ParametroNulo(FiltroVencimentoAlternativo.DATA_EXCLUSAO));

		Collection<VencimentoAlternativo> vencimentosAlternativos = fachada.pesquisar(
				filtroVencimentoAlternativo, VencimentoAlternativo.class.getName());

		if (!Util.isVazioOrNulo(vencimentosAlternativos)) {

			VencimentoAlternativo vencimentoAlternativo = vencimentosAlternativos.iterator().next();
				
			informarVencimentoAlternativoActionForm.setDataAlteracaoVencimento("");

			if (vencimentoAlternativo.getDataImplantacao() != null) {
				informarVencimentoAlternativoActionForm.setDataAlteracaoVencimento(
						new SimpleDateFormat("dd/MM/yyyy").format(vencimentoAlternativo.getDataImplantacao()));
			}	
			
			sessao.setAttribute("vencimentoAlternativo", vencimentoAlternativo);
		}
		
		sessao.setAttribute("colecaoNovoDiaVencimento",criarColecaoNovoDiaPagamento(clienteImovel, sessao));

		if (clienteImovel.getImovel().getIndicadorVencimentoMesSeguinte() != null){
			informarVencimentoAlternativoActionForm.setIndicadorVencimentoMesSeguinte(clienteImovel.getImovel().getIndicadorVencimentoMesSeguinte().toString());
		}else{
			informarVencimentoAlternativoActionForm.setIndicadorVencimentoMesSeguinte("2");
		}
		
		sessao.setAttribute("imovel", clienteImovel.getImovel());
	}

	/**
	 * Este m�todo cria a cole��o de dias de vencimento alternativo
	 * dispon�vel para esse im�vel.<br/>
	 * Caso o usu�rio tenha permiss�o especial de informar um vencimento alternativo,
	 * ent�o a cole��o ser� sempre os dias de 1 a 30.<br/>
	 * Caso exista dados no campo dias de vencimento alternatico na tabela sistema parametro,
	 * ent�o os dias poss�veis ser�os esses.
	 * Caso nenhuma das duas op��es anteriores seja atendida, ent�o os poss�veis dias para o vencimento
	 * ser�o do dia depois do dia de vencimento do grupo at� o dia 30.
	 *
	 *@since 22/10/2009
	 *@author Marlon Patrick
	 */
	private Collection<Integer> criarColecaoNovoDiaPagamento(
			ClienteImovel clienteImovel, HttpSession sessao) {
		
		boolean isUsuarioComPermissaoInformarVencimentoAlternativoNovaData = Fachada.getInstancia()
			.verificarPermissaoInformarVencimentoAlternativoNovaData((Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO));
				
		List<Integer> colecaoNovoDiaVencimento = new ArrayList<Integer>();
		
		if (isUsuarioComPermissaoInformarVencimentoAlternativoNovaData) {
			
			for (int i = 1; i <= 30; i++) {
				colecaoNovoDiaVencimento.add(i);
			}
			return colecaoNovoDiaVencimento;			
		} 
		
		SistemaParametro parametrosSistema = this.getFachada().pesquisarParametrosDoSistemaAtualizados();
		
		if(Util.verificarNaoVazio(parametrosSistema.getDiasVencimentoAlternativo())){
			String[] diasVencimento = parametrosSistema.getDiasVencimentoAlternativo().split(";");
			
			for (String diaAtual : diasVencimento) {
				if(Util.verificarNaoVazio(diaAtual)){
					colecaoNovoDiaVencimento.add(new Integer(diaAtual.trim()));						
				}
			}
			
			Collections.sort(colecaoNovoDiaVencimento);

			return colecaoNovoDiaVencimento;
		}
		
		String empresaCAERN = ""+ parametrosSistema.getCodigoEmpresaFebraban(); 
		if (empresaCAERN.equals("6") ) {

			colecaoNovoDiaVencimento.add(1);
			colecaoNovoDiaVencimento.add(5);
			colecaoNovoDiaVencimento.add(10);
			colecaoNovoDiaVencimento.add(25);
			colecaoNovoDiaVencimento.add(27);
			colecaoNovoDiaVencimento.add(30);
			
		} else {
		
			int i = 
				clienteImovel.getImovel().getQuadra().getRota().
					getFaturamentoGrupo().getDiaVencimento() + 1;
			
			
			int ultimoDiaVencimentoAlternativo = 30;
			
			if(parametrosSistema.getUltimoDiaVencimentoAlternativo() != null ){
				ultimoDiaVencimentoAlternativo = parametrosSistema.getUltimoDiaVencimentoAlternativo().intValue();
			}
	
			for (; i <= ultimoDiaVencimentoAlternativo; i++) {
				colecaoNovoDiaVencimento.add(i);
			}
			
		}
		return colecaoNovoDiaVencimento;
	}

	/**
	 * Obt�m o ClienteImovel relacionado ao im�vel informado,
	 * onde a rela��o entre o cliente e o im�vel seja de Usu�rio.
	 *
	 *@since 22/10/2009
	 *@author Marlon Patrick
	 */
	private Collection<ClienteImovel> obterColecaoClienteImovel(
			InformarVencimentoAlternativoActionForm informarVencimentoAlternativoActionForm) {
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel");
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.ligacaoAguaSituacao");
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.ligacaoEsgotoSituacao");
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra.rota.faturamentoGrupo");
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel");

		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.IMOVEL_ID,
				Integer.valueOf(informarVencimentoAlternativoActionForm.getIdImovel())));

		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.CLIENTE_RELACAO_TIPO,
				ClienteRelacaoTipo.USUARIO));
		
		filtroClienteImovel.adicionarParametro(new ParametroNulo(
				FiltroClienteImovel.DATA_FIM_RELACAO));
		
		return Fachada.getInstancia().pesquisar(
				filtroClienteImovel, ClienteImovel.class.getName());
	}
	
}
