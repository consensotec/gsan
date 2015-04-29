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
package gsan.gui.cobranca.spcserasa;

import gsan.cadastro.CpfTipo;
import gsan.cadastro.FiltroCpfTipo;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cobranca.NegativacaoCriterio;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.usuario.FiltroUsuario;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Calendar;
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
 * [UC0652] Manter Comando de Negativa��o<br>
 * [SB0004] Exibir Atualizar Comando de Negativa��o por Guia de Pagamento<br>
 * 
 * Exibir para o usu�rio a tela que receber� os par�metros para realiza��o
 * da atualiza��o de um Comando de Negativa��o por Guia de Pagamento<br>
 * Aba n� 01 - Dados Gerais
 *
 * @author Andr� Miranda
 * @date 23/03/2015
 */
public class ExibirAtualizarComandoNegativacaoPorGuiaPagamentoDadosGeraisAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
        ActionForward retorno = actionMapping.findForward("atualizarComandoNegativacaoPorGuiaPagamentoDadosGerais");
    	Fachada fachada = Fachada.getInstancia();
    	HttpSession sessao = request.getSession(false);
    	AtualizarComandoNegativacaoPorCriterioActionForm form = (AtualizarComandoNegativacaoPorCriterioActionForm) actionForm;

    	if(form.getSimular().equals(ConstantesSistema.NAO_CONFIRMADA)){
        	request.setAttribute("habilitarExecutarSimulacao", "habilitarExecutarSimulacao");   		
    	}

    	if(request.getParameter("idComandoNegativacaoSimulado") != null){
 			Integer idComandoNegativacao = new Integer(request.getParameter("idComandoNegativacaoSimulado"));

 			NegativacaoCriterio negativacaoCriterio = 
 				fachada.pesquisarComandoNegativacaoSimulado(idComandoNegativacao);

 			//[FS0027] Verificar sele��o de comando simulado
 			if (((Short)negativacaoCriterio.getNegativacaoComando().getIndicadorSimulacao()).equals(ConstantesSistema.NAO)){
     			throw new ActionServletException("atencao.comando_nao_simulacao");
 			}

			if (negativacaoCriterio.getNegativacaoComando().getDataHoraRealizacao() == null) {
				throw new ActionServletException("atencao.simulacao_nao_realizada");
			}

			form.setIdComandoSimulado(idComandoNegativacao.toString());
			form.setDescricaoComandoSimulado(negativacaoCriterio.getDescricaoTitulo());
		}

    	//[SB0003]- Determinar Data Prevista para Execu��o do Comando
		if (request.getParameter("determinarData") != null) {
			if (form.getSimular().equals(ConstantesSistema.NAO_CONFIRMADA)) {
				Date dataRealizacaoComando = fachada.pesquisarUltimaDataRealizacaoComando(
						new Integer(form.getNegativadorId()), new Integer(form.getSimular()));
				if (dataRealizacaoComando == null) {
					dataRealizacaoComando = new Date();
				}

				Short periodoRealizacao = getSistemaParametro().getCodigoPeriodicidadeNegativacao();
				if (periodoRealizacao.equals(SistemaParametro.CODIGO_PERIODICIDADE_NEGATIVACAO_SEMANAL)) {
					dataRealizacaoComando = Util.adicionarNumeroDiasDeUmaData(dataRealizacaoComando, 7);
				} else if (periodoRealizacao.equals(SistemaParametro.CODIGO_PERIODICIDADE_NEGATIVACAO_QUINZENAL)) {
					dataRealizacaoComando = Util.adicionarNumeroDiasDeUmaData(dataRealizacaoComando, 15);
				} else {
					dataRealizacaoComando = Util.adicionarNumeroDiasDeUmaData(dataRealizacaoComando, 30);
				}

				Calendar calendar = Calendar.getInstance();
				calendar.setTime(dataRealizacaoComando);

				Integer diaSemana = calendar.get(Calendar.DAY_OF_WEEK);
				Integer qtdSomarData = 7 - diaSemana;
				if (qtdSomarData != 0) {
					dataRealizacaoComando = Util.adicionarNumeroDiasDeUmaData(dataRealizacaoComando, qtdSomarData);
				}

				form.setDataPrevista(Util.formatarData(dataRealizacaoComando));
			} else {
				form.setDataPrevista(Util.formatarData(new Date()));
			}
		}

		// Pesquisar Tipo CPF
		if (sessao.getAttribute("colecaoCPFTipo") == null) {
			Collection cpfTipos = fachada.pesquisar(new FiltroCpfTipo(), CpfTipo.class.getName());
			sessao.setAttribute("colecaoCPFTipo", cpfTipos);
		}

        //Pesquisa Usuario 
		if (request.getParameter("pesquisarUsuario") != null) {
			String usuario = form.getUsuario();

			if (Util.verificarNaoVazio(usuario)) {
				FiltroUsuario filtro = new FiltroUsuario();
				filtro.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, usuario));
				Collection<?> colecaoUsuario = fachada.pesquisar(filtro, Usuario.class.getName());

				if (Util.isVazioOrNulo(colecaoUsuario)) {
					request.setAttribute("corUsuario", "exception");
					form.setUsuario(null);
					form.setNomeUsuario(ConstantesSistema.USUARIO_INEXISTENTE);
				} else {
					request.setAttribute("corUsuario", "valor");
					form.setUsuario("" + ((Usuario) ((List) colecaoUsuario).get(0)).getId());
					form.setNomeUsuario("" + ((Usuario) ((List) colecaoUsuario).get(0)).getNomeUsuario());
				}
			}
		}

    	return retorno;
    }
}
