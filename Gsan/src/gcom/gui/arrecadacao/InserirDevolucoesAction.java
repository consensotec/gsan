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
import gcom.arrecadacao.GuiaDevolucao;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
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
 * Descri��o da classe 
 *
 * @author Fernanda Paiva
 * @date 10/03/2006
 */
public class InserirDevolucoesAction extends GcomAction {
	
	/**
	 * Permite a Inclusao de Devolucoes 
	 *
	 * [UC0271] Inserir Devolucoes
	 *
	 * @author Fernanda Paiva
	 * @date 10/03/2006
	 *
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		// Obt�m a sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		 
		InserirDevolucoesActionForm inserirDevolucoesActionForm = (InserirDevolucoesActionForm) actionForm;
		
		// -----------8/- REGISTRAR TRANSA��O ----------------
        RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_DEVOLUCOES_INSERIR,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
        
        Operacao operacao = new Operacao();
        operacao.setId(Operacao.OPERACAO_DEVOLUCOES_INSERIR);

        OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
        operacaoEfetuada.setOperacao(operacao);
        //------------ REGISTRAR TRANSA��O ----------------

		
		// Campos do Formulario
		String valorDevolucaoAntes = inserirDevolucoesActionForm.getValorDevolucao().toString().replace(".","");
		String valorDevolucao = valorDevolucaoAntes.replace(",",".");
		String dataDevolucao = inserirDevolucoesActionForm.getDataDevolucao();
		Integer idAvisoBancario = new Integer(inserirDevolucoesActionForm.getAvisoBancario());
		String confirmado = httpServletRequest.getParameter("confirmado");

		SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");

		// Criando o objeto Devolucao
		Devolucao devolucao = new Devolucao();
		
		//	Criando o objeto Referencia Devolucao
		if (inserirDevolucoesActionForm.getReferenciaDevolucaoClone() != null
				&& !inserirDevolucoesActionForm.getReferenciaDevolucaoClone().equals("")) {
			String referenciaDevolucao = inserirDevolucoesActionForm
					.getReferenciaDevolucaoClone();
			String mes = referenciaDevolucao.substring(0, 2);
	        String ano = referenciaDevolucao.substring(3, 7);
	        String mesAno = ano + mes;
	        
			devolucao.setAnoMesReferenciaDevolucao(new Integer(mesAno));
		} else {
			devolucao.setAnoMesReferenciaDevolucao(null);
		}
		
		//	Criando o objeto Referencia Arrecadacao
		if (inserirDevolucoesActionForm.getDataDevolucao() != null
				&& !inserirDevolucoesActionForm.getDataDevolucao().equals("")) {
			// Concatena ano mes para insercao
	        String mesDataDevolucao = dataDevolucao.substring(3, 5);
	        String anoDataDevolucao = dataDevolucao.substring(6, 10);
	        String mesAnoDataDevolucao = anoDataDevolucao + mesDataDevolucao;
	        
	        SistemaParametro sistemaParametro = null;
	        sistemaParametro = fachada.pesquisarParametrosDoSistema();
	        
	        if (new Integer(mesAnoDataDevolucao) > sistemaParametro.getAnoMesArrecadacao())
	        {
	        	devolucao.setAnoMesReferenciaArrecadacao(new Integer(mesAnoDataDevolucao));	
	        }
	        else
	        {
	        	devolucao.setAnoMesReferenciaArrecadacao(sistemaParametro.getAnoMesArrecadacao());
	        }
		} else {
			devolucao.setAnoMesReferenciaArrecadacao(0);
		}
		
		FiltroAvisoBancario filtroAvisoBancario = new FiltroAvisoBancario();

		filtroAvisoBancario.adicionarParametro(new ParametroSimples(
				FiltroAvisoBancario.ID, idAvisoBancario));

		Collection avisoBancario = fachada.pesquisar(filtroAvisoBancario,
				AvisoBancario.class.getName());
		
		if (avisoBancario != null && !avisoBancario.isEmpty()) {

			AvisoBancario dadosAvisoBancario = (AvisoBancario) ((List) avisoBancario).get(0);
			
			BigDecimal valorDevolucaoInserir = dadosAvisoBancario.getValorDevolucaoCalculado().add(new BigDecimal(valorDevolucao));
			fachada.atualizaValorArrecadacaoAvisoBancaraio(valorDevolucaoInserir, idAvisoBancario);
		}

		devolucao.setValorDevolucao(new BigDecimal(valorDevolucao));
		
		//	Criando o objeto DataDevolucao
		Date dataDevolucoesFormatada = null;

		try {
			dataDevolucoesFormatada = dataFormatada.parse(dataDevolucao);
		} catch (ParseException ex) {
			throw new ActionServletException("erro.sistema");
		}
		
		devolucao.setDataDevolucao(dataDevolucoesFormatada);
		devolucao.setDevolucaoSituacaoAnterior(null);
		devolucao.setDevolucaoSituacaoAtual(null);
		
		AvisoBancario avisoBancarioSetarId = new AvisoBancario();
		avisoBancarioSetarId.setId(idAvisoBancario);
		devolucao.setAvisoBancario(avisoBancarioSetarId);
		
		Integer retornoLocalidade = null;
		if (inserirDevolucoesActionForm.getLocalidade() != null 
				&& !inserirDevolucoesActionForm.getLocalidade().equals("")) {
			retornoLocalidade = fachada.verificarExistenciaLocalidade(new Integer(inserirDevolucoesActionForm.getLocalidade()));
			if(retornoLocalidade == null || retornoLocalidade == 0)
			{
				throw new ActionServletException(
					"atencao.localidade.inexistente");
			}
		}
		Integer retornoGuia = null;
		if (inserirDevolucoesActionForm.getGuiaDevolucao() != null 
				&& !inserirDevolucoesActionForm.getGuiaDevolucao().equals("")) {
			retornoGuia = fachada.verificarExistenciaGuiaDevolucao(new Integer(inserirDevolucoesActionForm.getGuiaDevolucao()));
			if(retornoGuia == null || retornoGuia == 0)
			{
				throw new ActionServletException(
					"atencao.pesquisa.guia.inexistente");
			}
		}
		// Criando o objeto Guia Devolucao
		if (inserirDevolucoesActionForm.getGuiaDevolucao() != null 
				&& !inserirDevolucoesActionForm.getGuiaDevolucao().equals("")) {
			Integer idGuiaDevolucao = new Integer(inserirDevolucoesActionForm.getGuiaDevolucao());
			GuiaDevolucao guiaDevolucao = new GuiaDevolucao();
			guiaDevolucao.setId(idGuiaDevolucao);
			devolucao.setGuiaDevolucao(guiaDevolucao);
		}
		else {
			devolucao.setGuiaDevolucao(null);
		}
		
		//	Criando o objeto Localidade
		if (inserirDevolucoesActionForm.getLocalidadeClone() != null
				&& !inserirDevolucoesActionForm.getLocalidadeClone().equals("")) {
			Integer idLocalidade = new Integer(inserirDevolucoesActionForm.getLocalidadeClone());
			Localidade localidade = new Localidade();
			localidade.setId(idLocalidade);
			devolucao.setLocalidade(localidade);
		}
		else if (inserirDevolucoesActionForm.getLocalidade() != null
				&& !inserirDevolucoesActionForm.getLocalidade().equals("")) {
			Integer idLocalidade = new Integer(inserirDevolucoesActionForm.getLocalidade());
			Localidade localidade = new Localidade();
			localidade.setId(idLocalidade);
			devolucao.setLocalidade(localidade);
		}
		// Criando o objeto Imovel
		if (inserirDevolucoesActionForm.getCodigoImovelClone() != null 
				&& !inserirDevolucoesActionForm.getCodigoImovelClone().equals("")) {
			
			Integer idImovel = new Integer(inserirDevolucoesActionForm.getCodigoImovelClone());
			Imovel imovel = new Imovel();
			imovel.setId(idImovel);
			devolucao.setImovel(imovel);
		}
		else if ((inserirDevolucoesActionForm.getCodigoImovel() != null	
				&& !inserirDevolucoesActionForm.getCodigoImovel().equals("")) 
				&& (inserirDevolucoesActionForm.getCodigoCliente() == null)) {
			Integer idImovel = new Integer(inserirDevolucoesActionForm.getCodigoImovel());
			Imovel imovel = new Imovel();
			imovel.setId(idImovel);
			devolucao.setImovel(imovel);
		}
		else
		{
			devolucao.setImovel(null);
		}
		Integer retornoCliente = null;
		if (inserirDevolucoesActionForm.getCodigoCliente() != null 
				&& !inserirDevolucoesActionForm.getCodigoCliente().equals("")) {
			retornoCliente = fachada.verificarExistenciaCliente(new Integer(inserirDevolucoesActionForm.getCodigoCliente()));
			if(retornoCliente == null || retornoCliente == 0)
			{
				throw new ActionServletException(
					"atencao.cliente.inexistente");
			}
		}
		// Criando o objeto Cliente
		if (inserirDevolucoesActionForm.getCodigoClienteClone() != null 
				&& !inserirDevolucoesActionForm.getCodigoClienteClone().equals("")) {
			Integer idCliente = new Integer(inserirDevolucoesActionForm.getCodigoClienteClone());
			Cliente cliente = new Cliente();
			cliente.setId(idCliente);
			devolucao.setCliente(cliente);
		}
		else if ((inserirDevolucoesActionForm.getCodigoCliente() != null 
				&& !inserirDevolucoesActionForm.getCodigoCliente().equals("")) 
				&& (inserirDevolucoesActionForm.getCodigoImovel() == null)) {
			Integer idCliente = new Integer(inserirDevolucoesActionForm.getCodigoCliente());
			Cliente cliente = new Cliente();
			cliente.setId(idCliente);
			devolucao.setCliente(cliente);
		}
		else
		{
			devolucao.setCliente(null);
		}
		Integer retornoDebito = null;
		if (inserirDevolucoesActionForm.getTipoDebito() != null 
				&& !inserirDevolucoesActionForm.getTipoDebito().equals("")) {
			retornoDebito = fachada.verificarExistenciaDebitoTipo(new Integer(inserirDevolucoesActionForm.getTipoDebito()));
			if(retornoDebito == null || retornoDebito == 0)
			{
				throw new ActionServletException(
				"atencao.pesquisa.debitoTipo.inexistente");
			}
		}
		// Criando o objeto TipoDebito
		if (inserirDevolucoesActionForm.getTipoDebitoClone() != null 
				&& !inserirDevolucoesActionForm.getTipoDebitoClone().equals("")) {
			Integer idTipoDebito = new Integer(inserirDevolucoesActionForm.getTipoDebitoClone());
			DebitoTipo tipoDebito = new DebitoTipo();
			tipoDebito.setId(idTipoDebito);
			devolucao.setDebitoTipo(tipoDebito);
		}
		else if (inserirDevolucoesActionForm.getTipoDebito() != null 
				&& !inserirDevolucoesActionForm.getTipoDebito().equals("")) {
			Integer idTipoDebito = new Integer(inserirDevolucoesActionForm.getTipoDebito());
			DebitoTipo tipoDebito = new DebitoTipo();
			tipoDebito.setId(idTipoDebito);
			devolucao.setDebitoTipo(tipoDebito);
		}
		else
		{
			devolucao.setDebitoTipo(null);
		}
		
		// Verifica se a localidade pertence ao im�vel digitado
		if(inserirDevolucoesActionForm.getCodigoImovel() != null 
				&& !inserirDevolucoesActionForm.getCodigoImovel().equals("")) 
		{
			FiltroImovel filtroImovel = new FiltroImovel();

			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("localidade");
			
			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.ID, inserirDevolucoesActionForm.getCodigoImovel()));
			
			Collection imovelEncontrado = fachada.pesquisar(filtroImovel,
					Imovel.class.getName());
			if (imovelEncontrado != null && !imovelEncontrado.isEmpty()) 
			{
				String codigoDigitadoLocalidadeEnter = ((Imovel) ((List) imovelEncontrado).get(0)).getLocalidade().getId().toString();
				if(inserirDevolucoesActionForm.getLocalidade() != null && !inserirDevolucoesActionForm.getLocalidade().equals("") && !inserirDevolucoesActionForm.getLocalidade().equals(codigoDigitadoLocalidadeEnter))
				{
					throw new ActionServletException(
							"atencao.pesquisa.localidade.imovel.diferente",""
									+ codigoDigitadoLocalidadeEnter, inserirDevolucoesActionForm.getLocalidade());
				}
			}
		}
		
		String retornoImovel = null;
		if (inserirDevolucoesActionForm.getCodigoImovel() != null 
				&& !inserirDevolucoesActionForm.getCodigoImovel().equals("")) {
			retornoImovel = fachada.pesquisarInscricaoImovel(new Integer(inserirDevolucoesActionForm.getCodigoImovel()));
			if(retornoImovel == null || retornoImovel.equalsIgnoreCase(""))
			{
				throw new ActionServletException(
					"atencao.imovel.inexistente");
			}
		}
		// verifica se o valor da devolucao � maior que o valor da guia da devolucao
		if(inserirDevolucoesActionForm.getValorGuiaDevolucao() != null && !inserirDevolucoesActionForm.getValorGuiaDevolucao().equalsIgnoreCase("") && Util.formatarMoedaRealparaBigDecimal(inserirDevolucoesActionForm.getValorDevolucao()).compareTo(Util.formatarMoedaRealparaBigDecimal(inserirDevolucoesActionForm.getValorGuiaDevolucao())) == 1) 
		{
			if (confirmado == null || !confirmado.trim().equalsIgnoreCase("ok")){
				httpServletRequest.setAttribute("caminhoActionConclusao",
						"/gsan/inserirDevolucoesAction.do");
				// Monta a p�gina de confirma��o para perguntar se o usu�rio
				// quer inserir
				// a devolu��o mesmo com o valor da devolu��o sendo superior ao da guia da devolu��o
				return montarPaginaConfirmacao(
						"atencao.valor_devolucao_maior_valor_guia_devolucao.confirmacao",
						httpServletRequest, actionMapping, inserirDevolucoesActionForm.getValorDevolucao(),inserirDevolucoesActionForm.getValorGuiaDevolucao());
			}
		}
		
		// �ltima Altera��o
		devolucao.setUltimaAlteracao(new Date());
		
		//------------ REGISTRAR TRANSA��O ----------------
        devolucao.setOperacaoEfetuada(operacaoEfetuada);
        devolucao.adicionarUsuario(usuarioLogado, 
    				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
    		registradorOperacao.registrarOperacao(devolucao);
        //------------ REGISTRAR TRANSA��O ----------------
		
		fachada.inserir(devolucao);

		 montarPaginaSucesso(httpServletRequest, "Devolu��o de c�digo "
	                + devolucao.getId() + " inserida com sucesso.",
	                "Inserir outra Devolu��o",
	                "exibirInserirDevolucoesAction.do?menu=sim",
	                "exibirAtualizarDevolucaoAction.do?idRegistroAtualizacao="
					+ devolucao.getId(),
					"Atualizar Devolu��o Inserida");
		
		return retorno;
	}
}
