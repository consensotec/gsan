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
package gcom.gui.arrecadacao;

import gcom.arrecadacao.Devolucao;
import gcom.arrecadacao.FiltroAvisoBancario;
import gcom.arrecadacao.FiltroDevolucao;
import gcom.arrecadacao.GuiaDevolucao;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Fernanda Karla
 * @created 09 de Mar�o de 2006
 */
public class AtualizarDevolucaoAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		ManterDevolucaoActionForm manterDevolucaoActionForm = (ManterDevolucaoActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// ------------ REGISTRAR TRANSA��O ----------------
        RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_DEVOLUCOES_ATUALIZAR,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
        
        Operacao operacao = new Operacao();
        operacao.setId(Operacao.OPERACAO_DEVOLUCOES_ATUALIZAR);

        OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
        operacaoEfetuada.setOperacao(operacao);
        //------------ REGISTRAR TRANSA��O ----------------

		String codigoDevolucao = manterDevolucaoActionForm.getCodigoDevolucao();
		String valorDevolucaoAntes = manterDevolucaoActionForm.getValorDevolucao().toString().replace(".","");
		String valorDevolucao = valorDevolucaoAntes.replace(",",".");
		String dataDevolucao = manterDevolucaoActionForm.getDataDevolucao();
		String confirmado = httpServletRequest.getParameter("confirmado");

		Fachada fachada = Fachada.getInstancia();

		// Criando o objeto Devolucao
		Devolucao devolucao = new Devolucao();

		FiltroDevolucao filtroDevolucao = new FiltroDevolucao();

		filtroDevolucao.adicionarParametro(new ParametroSimples(
				FiltroDevolucao.ID, codigoDevolucao));

		Collection devolucoes = fachada.pesquisar(filtroDevolucao,
				Devolucao.class.getName());

		if (devolucoes != null && !devolucoes.isEmpty()) {

			Devolucao dadosDevolucao = (Devolucao) ((List) devolucoes).get(0);

			SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");

			devolucao.setId(new Integer(codigoDevolucao));
			
			if(dadosDevolucao.getValorDevolucao() != (new BigDecimal(valorDevolucao)))
			{
				FiltroAvisoBancario filtroAvisoBancario = new FiltroAvisoBancario();

				filtroAvisoBancario.adicionarParametro(new ParametroSimples(
						FiltroAvisoBancario.ID, dadosDevolucao.getAvisoBancario().getId()));

				Collection avisoBancario = fachada.pesquisar(filtroAvisoBancario,
						AvisoBancario.class.getName());
				
				BigDecimal valorFinal = null;

				if (avisoBancario != null && !avisoBancario.isEmpty()) {

					AvisoBancario dadosAvisoBancario = (AvisoBancario) ((List) avisoBancario).get(0);
					
					BigDecimal valorDevolucao2 = dadosAvisoBancario.getValorDevolucaoCalculado();
					
					BigDecimal valorSubtracao = valorDevolucao2.subtract(dadosDevolucao.getValorDevolucao());
					
					valorFinal = new BigDecimal(valorDevolucao).add(valorSubtracao);

				}
				fachada.atualizaValorArrecadacaoAvisoBancaraio(valorFinal, dadosDevolucao.getAvisoBancario().getId());
			}

			devolucao.setValorDevolucao(new BigDecimal(valorDevolucao));

			int mesAnoArrecadacao = dadosDevolucao
					.getAnoMesReferenciaArrecadacao();
			devolucao.setAnoMesReferenciaArrecadacao(new Integer(
					mesAnoArrecadacao));

			Date dataDevolucoesFormatada = null;

			try {
				dataDevolucoesFormatada = dataFormatada.parse(dataDevolucao);
			} catch (ParseException ex) {
				throw new ActionServletException("erro.sistema");
			}

			devolucao.setDataDevolucao(dataDevolucoesFormatada);

			// Criando o objeto Referencia Devolucao
			if (manterDevolucaoActionForm.getReferenciaDevolucaoClone() != null
					&& !manterDevolucaoActionForm.getReferenciaDevolucaoClone()
							.equals("")) {
				String referenciaDevolucao = manterDevolucaoActionForm
						.getReferenciaDevolucaoClone();
				String mes = referenciaDevolucao.substring(0, 2);
				String ano = referenciaDevolucao.substring(3, 7);
				String mesAno = ano + mes;

				devolucao.setAnoMesReferenciaDevolucao(new Integer(mesAno));
			} else {
				devolucao.setAnoMesReferenciaDevolucao(null);
			}

			// Criando o objeto Cliente
			if (manterDevolucaoActionForm.getCodigoClienteClone() != null
					&& !manterDevolucaoActionForm.getCodigoClienteClone().equals("")) {
				Integer idCliente = new Integer(manterDevolucaoActionForm
						.getCodigoClienteClone());
				Cliente cliente = new Cliente();
				cliente.setId(idCliente);
				devolucao.setCliente(cliente);
			}else {
				devolucao.setCliente(null);
			}

			devolucao.setDevolucaoSituacaoAnterior(dadosDevolucao
					.getDevolucaoSituacaoAnterior());
			devolucao.setDevolucaoSituacaoAtual(dadosDevolucao
					.getDevolucaoSituacaoAtual());

			FiltroImovel filtroImovel = new FiltroImovel();

			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("localidade");
			// Criando o objeto Imovel
			if (manterDevolucaoActionForm.getCodigoImovelClone() != null
					&& !manterDevolucaoActionForm.getCodigoImovelClone().equals("")) {
				
				Integer idImovel = new Integer(manterDevolucaoActionForm
						.getCodigoImovelClone());
				Imovel imovel = new Imovel();
				imovel.setId(idImovel);
				devolucao.setImovel(imovel);
				
				filtroImovel.adicionarParametro(new ParametroSimples(
						FiltroImovel.ID, manterDevolucaoActionForm.getCodigoImovelClone()));
				
				Collection imovelEncontrado = fachada.pesquisar(filtroImovel,
						Imovel.class.getName());
				if (imovelEncontrado != null && !imovelEncontrado.isEmpty()) 
				{
					String codigoDigitadoLocalidadeEnter = ((Imovel) ((List) imovelEncontrado).get(0)).getLocalidade().getId().toString();
					if(manterDevolucaoActionForm.getLocalidade() != null && !manterDevolucaoActionForm.getLocalidade().equals("") && !manterDevolucaoActionForm.getLocalidadeClone().equals(codigoDigitadoLocalidadeEnter))
					{
						throw new ActionServletException(
								"atencao.pesquisa.localidade.imovel.diferente", ""
										+ codigoDigitadoLocalidadeEnter, manterDevolucaoActionForm.getLocalidade());
					}
				}
			} else {
				devolucao.setImovel(null);
			}

			// Criando o objeto Localidade
			if (manterDevolucaoActionForm.getLocalidadeClone() != null
					&& !manterDevolucaoActionForm.getLocalidadeClone().equals("")) {
				Integer idLocalidade = new Integer(manterDevolucaoActionForm
						.getLocalidadeClone());
				Localidade localidade = new Localidade();
				localidade.setId(idLocalidade);
				devolucao.setLocalidade(localidade);
			}else {
				devolucao.setLocalidade(dadosDevolucao.getLocalidade());
			}
			// Criando o objeto Guia Devolucao
			if (manterDevolucaoActionForm.getGuiaDevolucaoClone() != null
					&& !manterDevolucaoActionForm.getGuiaDevolucaoClone().equals("")) {
				Integer idGuiaDevolucao = new Integer(manterDevolucaoActionForm
						.getGuiaDevolucaoClone());
				GuiaDevolucao guiaDevolucao = new GuiaDevolucao();
				guiaDevolucao.setId(idGuiaDevolucao);
				devolucao.setGuiaDevolucao(guiaDevolucao);
			} else {
				devolucao.setGuiaDevolucao(null);
			}

			devolucao.setAvisoBancario(dadosDevolucao.getAvisoBancario());

			// Criando o objeto TipoDebito
			if (manterDevolucaoActionForm.getTipoDebitoClone() != null
					&& !manterDevolucaoActionForm.getTipoDebitoClone().equals("")) {
				Integer idTipoDebito = new Integer(manterDevolucaoActionForm
						.getTipoDebitoClone());
				DebitoTipo tipoDebito = new DebitoTipo();
				tipoDebito.setId(idTipoDebito);
				devolucao.setDebitoTipo(tipoDebito);
			}else{
				devolucao.setDebitoTipo(null);
			}
			// �ltima Altera��o
			devolucao.setUltimaAlteracao(new Date());
		}
		
		if(devolucao.getImovel() != null)
		{
			FiltroImovel filtroImovel = new FiltroImovel();

			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("localidade");
			
			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.ID, devolucao.getImovel().getId()));
			
			Collection imovelEncontrado = fachada.pesquisar(filtroImovel,
					Imovel.class.getName());
			if (imovelEncontrado != null && !imovelEncontrado.isEmpty()) 
			{
				String codigoDigitadoLocalidadeEnter = ((Imovel) ((List) imovelEncontrado).get(0)).getLocalidade().getId().toString();
				if(manterDevolucaoActionForm.getLocalidade() != null && !manterDevolucaoActionForm.getLocalidade().equals("") && !manterDevolucaoActionForm.getLocalidade().equals(codigoDigitadoLocalidadeEnter))
				{
					throw new ActionServletException(
							"atencao.pesquisa.localidade.imovel.diferente", ""
									+ codigoDigitadoLocalidadeEnter, manterDevolucaoActionForm.getLocalidade());
				}
			}
		}
		Devolucao dadosDevolucao = (Devolucao)sessao.getAttribute("dadosDevolucao");
		// verifica se o valor da devolucao � maior que o valor da guia da devolucao
		if(manterDevolucaoActionForm.getValorGuiaDevolucao() != null && !manterDevolucaoActionForm.getValorGuiaDevolucao().equalsIgnoreCase("") 
				&& Util.formatarMoedaRealparaBigDecimal(manterDevolucaoActionForm.getValorDevolucao()).compareTo(Util.formatarMoedaRealparaBigDecimal(manterDevolucaoActionForm.getValorGuiaDevolucao())) == 1) 
		{
			if (confirmado == null || !confirmado.trim().equalsIgnoreCase("ok")){
				httpServletRequest.setAttribute("caminhoActionConclusao",
						"/gsan/atualizarDevolucaoAction.do");
				// Monta a p�gina de confirma��o para perguntar se o usu�rio
				// quer inserir
				// a devolu��o mesmo com o valor da devolu��o sendo superior ao da guia da devolu��o
				
				// coloca o objeto devolucao na sessao caso ele seja diferente de null
				if(dadosDevolucao != null){
					sessao.setAttribute("dadosDevolucao",dadosDevolucao);
				}
				return montarPaginaConfirmacao(
						"atencao.valor_devolucao_maior_valor_guia_devolucao.confirmacao",
						httpServletRequest, actionMapping, manterDevolucaoActionForm.getValorDevolucao(),manterDevolucaoActionForm.getValorGuiaDevolucao());
			}
		}

		
		// ------------ REGISTRAR TRANSA��O ----------------
        devolucao.setOperacaoEfetuada(operacaoEfetuada);
        devolucao.adicionarUsuario(usuarioLogado, 
        		UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
        registradorOperacao.registrarOperacao(devolucao);
        //------------ REGISTRAR TRANSA��O ----------------
        
        
        
        // Limpa os parametros do filtro para fazer uma nova pesquisar e verificar a ultima alteracao na base
		filtroDevolucao.limparListaParametros(); 

		// Parte de Valida��o com Timestamp

		// Seta no filtro o c�digo da devolucao que est� sendo atualizada
		filtroDevolucao.adicionarParametro(new ParametroSimples(
				FiltroDevolucao.ID, devolucao.getId()));

		// Procura a devolucao na base
		Devolucao devolucaoNaBase = (Devolucao) ((List) (fachada
				.pesquisar(filtroDevolucao, Devolucao.class.getName()))).get(0);

		// Atualiza��o realizada por outro usu�rio
		// Caso o usu�rio esteja tentando atualizar uma devolucao e o mesmo j�
		// tenha
		// sido atualizado durante a manuten��o corrente
		if (devolucaoNaBase.getUltimaAlteracao().after(
				dadosDevolucao.getUltimaAlteracao())) {
			throw new ActionServletException("atencao.atualizacao.timestamp");
		}

		// Atualiza a Devolucao
		fachada.atualizar(devolucao);

		montarPaginaSucesso(httpServletRequest, "Devolu��o de c�digo "
				+ devolucao.getId() + " atualizada com sucesso.",
				"Realizar outra Manuten��o de Devolucao",
				"exibirFiltrarDevolucaoAction.do?tela=manterDevolucao&menu=sim");
		return retorno;
	}
}