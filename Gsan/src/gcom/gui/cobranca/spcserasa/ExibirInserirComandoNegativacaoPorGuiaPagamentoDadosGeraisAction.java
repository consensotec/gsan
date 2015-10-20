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

import gcom.cadastro.CpfTipo;
import gcom.cadastro.FiltroCpfTipo;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.NegativacaoCriterio;
import gcom.cobranca.Negativador;
import gcom.cobranca.bean.ParametrosComandoNegativacaoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.spcserasa.FiltroNegativador;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0651] Inserir Comando de Negativa��o<br>
 * [SB0010] Comandar Negativa��o Por Guia de Pagamento<br>
 * 
 * Exibir para o usu�rio a tela que receber� os par�metros para realiza��o
 * da inser��o de um Comando de Negativa��o por Guia de Pagamento<br>
 * Aba n� 01 - Dados Gerais
 *
 * @author Andr� Miranda
 * @date 06/11/2007
 */
public class ExibirInserirComandoNegativacaoPorGuiaPagamentoDadosGeraisAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) {
        ActionForward retorno = actionMapping.findForward("inserirComandoNegativacaoPorGuiaPagamentoDadosGerais");

    	Fachada fachada = Fachada.getInstancia();
    	HttpSession sessao = request.getSession(false);
    	Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
    	SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
    	InserirComandoNegativacaoActionForm form = (InserirComandoNegativacaoActionForm ) actionForm;

 		Date dataRealizacaoComando = new Date();
 		Short periodoRealizacao = null;

		if (fachada.verificarExistenciaComandoNegativadorNaoRealizado()) {
			throw new ActionServletException("atencao.existe_comando_negativado_em_aberto");
		}

		if (request.getParameter("desfazer") != null) {
			String idNegativador = form.getIdNegativador();
			form.reset();
			form.setIdNegativador(idNegativador);
		}

		if (request.getParameter("idComandoNegativacao") != null) {
			Integer idComandoNegativacao = new Integer(request.getParameter("idComandoNegativacao"));

			ParametrosComandoNegativacaoHelper parametrosComandoNegativacaoHelper = fachada
					.pesquisarParametrosComandoNegativacao(idComandoNegativacao);

			form.reset();

	        // Dados Gerais
			setDadosGerais(sessao, form, parametrosComandoNegativacaoHelper, fachada);

			// Dados Cliente
			setDadosCliente(form, parametrosComandoNegativacaoHelper);
		} else if (request.getParameter("idComandoNegativacaoSimulado") != null) {
			Integer idComandoNegativacao = new Integer(request.getParameter("idComandoNegativacaoSimulado"));

			NegativacaoCriterio negativacaoCriterio = fachada.pesquisarComandoNegativacaoSimulado(idComandoNegativacao);

			// [FS0027] Verificar sele��o de comando simulado
			if (((Short) negativacaoCriterio.getNegativacaoComando().getIndicadorSimulacao())
					.equals(ConstantesSistema.NAO)) {
				throw new ActionServletException("atencao.comando_nao_simulacao");
			}
			if (negativacaoCriterio.getNegativacaoComando().getDataHoraRealizacao() == null) {
				throw new ActionServletException("atencao.simulacao_nao_realizada");
			}
			form.setIdComandoSimulado(idComandoNegativacao.toString());
			form.setDescricaoComandoSimulado(negativacaoCriterio.getDescricaoTitulo());
		}

        // Pesquisar o Negativador
		if (form.getIdNegativador() != null) {
			FiltroNegativador filtroNegativador = new FiltroNegativador();
			filtroNegativador.adicionarParametro(new ParametroSimples(FiltroNegativador.ID, form.getIdNegativador()));
			filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("cliente");

			Collection negativadores = fachada.pesquisar(filtroNegativador, Negativador.class.getName());

			if (negativadores != null && !negativadores.isEmpty()) {
				Negativador negativador = (Negativador) Util.retonarObjetoDeColecao(negativadores);
				form.setNomeNegativador(negativador.getCliente().getNome());
			}
		}

		if (request.getAttribute("entrou") != null) {
			// Simular Negativa��o - exibir com op��o "N�o" selecionada
			form.setSimular(ConstantesSistema.NAO_CONFIRMADA);
			request.setAttribute("habilitarExecutarSimulacao", "habilitarExecutarSimulacao");
			form.setExecutarSimulacao(ConstantesSistema.NAO_CONFIRMADA);
			form.setIndicadorCpfCnpjValido(ConstantesSistema.SIM.toString());
		}

		// [FS0037] Verificar permiss�o especial de altera��o de valor
    	boolean alterarSoCPFCNPJValidos = Fachada.getInstancia().verificarPermissaoEspecial(
				PermissaoEspecial.ALTERAR_SO_CPF_CNPJ_VALIDOS, usuarioLogado);

		if (sistemaParametro.getIndicadorValidaCpfCnpj().equals(new Integer(ConstantesSistema.NAO))) {
			form.setAlterarSoCPFCNPJValidos(alterarSoCPFCNPJValidos);
			form.setIndicadorCpfCnpjValido(ConstantesSistema.NAO_CONFIRMADA);
		} else {
			sessao.setAttribute("alterarSoCPFCNPJValidos", alterarSoCPFCNPJValidos);
		}

		// [SB0003]- Determinar Data Prevista para Execu��o do Comando
		if (request.getAttribute("entrou") != null || request.getParameter("determinarData") != null
				|| request.getParameter("idComandoNegativacao") != null || request.getParameter("voltar") != null) {
			if (request.getParameter("voltar") != null) {
				if (form.getSimular().equals(ConstantesSistema.CONFIRMADA)) {
					form.setSimular(ConstantesSistema.CONFIRMADA);
				} else {
					form.setSimular(ConstantesSistema.NAO_CONFIRMADA);
				}
			}

			periodoRealizacao = sistemaParametro.getCodigoPeriodicidadeNegativacao();

			if (!ConstantesSistema.CONFIRMADA.equals(form.getExecutarSimulacao())) {
				dataRealizacaoComando = fachada.pesquisarUltimaDataRealizacaoComando(
						new Integer(form.getIdNegativador()), new Integer(form.getSimular()));

				if (dataRealizacaoComando == null) {
					dataRealizacaoComando = new Date();
				}

        	    /**
        	     * Autor Hugo Leonardo
        	     * Data: 19/04/2010
        	     * CRC: 3780 - Tratar a periodicidade di�ria para execu��o do comando
        	     * */
				if (periodoRealizacao.equals(SistemaParametro.CODIGO_PERIODICIDADE_NEGATIVACAO_DIARIA)) {
					dataRealizacaoComando = new Date();
				} else if (periodoRealizacao.equals(SistemaParametro.CODIGO_PERIODICIDADE_NEGATIVACAO_SEMANAL)) {
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
			}

    		// Hugo Leonardo  -  19/04/2010
    		// Se Periodicidade Diaria entao dataPrevista = a data corrente.
    		// CRC 3780
			if (periodoRealizacao.equals(SistemaParametro.CODIGO_PERIODICIDADE_NEGATIVACAO_DIARIA)) {
				form.setDataPrevista(Util.formatarData(new Date()));
			} else {
				form.setDataPrevista(Util.formatarData(dataRealizacaoComando));
			}

			if (form.getSimular().equals(ConstantesSistema.CONFIRMADA)) {
				form.setExecutarSimulacao(ConstantesSistema.NAO_CONFIRMADA);
				form.setIdComandoSimulado(null);
				form.setDescricaoComandoSimulado(null);
				form.setDataPrevista(Util.formatarData(new Date()));
			} else {
				request.setAttribute("habilitarExecutarSimulacao", "habilitarExecutarSimulacao");
			}

	        //[FS0026] Verificar exist�ncia de comando para o negativador na data
			boolean existeComando = fachada.verificarExistenciaComandoNegativador(form.getIdNegativador(),
					Util.converteStringParaDate(form.getDataPrevista()));
			if (existeComando && request.getAttribute("entrou") == null && request.getParameter("entrou") == null) {
				throw new ActionServletException(
						"atencao.existe_comando_negativado_data",
						"inserirComandoNegativacaoPorGuiaPagamentoWizardAction.do?voltar=ok&entrou=ok&action=exibirInserirComandoNegativacaoPorGuiaPagamentoDadosGeraisAction",
						new Exception(), form.getDataPrevista(), form.getNomeNegativador());
			}
		}

		if (form.getSimular().equals(ConstantesSistema.NAO_CONFIRMADA)) {
			request.setAttribute("habilitarExecutarSimulacao", "habilitarExecutarSimulacao");
		}

        // Pesquisar Tipo CPF
		if (sessao.getAttribute("colecaoCPFTipo") == null) {
			FiltroCpfTipo filtroCpfTipo = new FiltroCpfTipo();
			Collection cpfTipos = fachada.pesquisar(filtroCpfTipo, CpfTipo.class.getName());
			sessao.setAttribute("colecaoCPFTipo", cpfTipos);
		}

        // Pesquisa Usuario 
		if (request.getParameter("pesquisarUsuario") != null) {
			String idUsuario = form.getUsuario();

			if (Util.verificarNaoVazio(idUsuario)) {
	        	FiltroUsuario filtroUsuario = new FiltroUsuario();
	        	filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, idUsuario));

				Collection<?> colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());

				if (Util.isVazioOrNulo(colecaoUsuario)) {
					request.setAttribute("corUsuario", "exception");
					form.setUsuario(null);
					form.setNomeUsuario(ConstantesSistema.USUARIO_INEXISTENTE);
				} else {
					request.setAttribute("corUsuario", "valor");

					Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
					form.setUsuario(usuario.getId().toString());
					form.setNomeUsuario(usuario.getNomeUsuario());
				}
			}
		}

    	return retorno;
	}

	private void setDadosGerais(HttpSession sessao, InserirComandoNegativacaoActionForm form,
			ParametrosComandoNegativacaoHelper parametrosHelper, Fachada fachada) {
		// Titulo Comando
		if (parametrosHelper.getTituloComando() != null) {
			form.setTitulo(parametrosHelper.getTituloComando());
		}

		// Descricao da Solicitacao
		if (parametrosHelper.getDescricaoSolicitacao() != null) {
			form.setSolicitacao(parametrosHelper.getDescricaoSolicitacao());
		}

		// Simular Negativacao
		if (parametrosHelper.getSimularNegativacao() != null) {
			if (parametrosHelper.getSimularNegativacao().equals(ConstantesSistema.SIM)) {
				form.setSimular(ConstantesSistema.SIM.toString());
			} else {
				form.setSimular(ConstantesSistema.NAO.toString());
			}
		}

		if (parametrosHelper.getIdComandoNegativacaoSimulado() != null) {
			Integer idComandoNegativacao = parametrosHelper.getIdComandoNegativacaoSimulado();

			NegativacaoCriterio negativacaoCriterio = fachada.pesquisarComandoNegativacaoSimulado(idComandoNegativacao);

			form.setIdComandoSimulado(idComandoNegativacao.toString());
			form.setDescricaoComandoSimulado(negativacaoCriterio.getDescricaoTitulo());
		}

		// Data Prevista p Execucao
		if (parametrosHelper.getDataExecucao() != null) {
			form.setDataPrevista(Util.formatarData(parametrosHelper.getDataExecucao()));
		}

		// Usuario Responsavel
		if (parametrosHelper.getIdUsuario() != null) {
			form.setUsuario(parametrosHelper.getIdUsuario().toString());
			form.setNomeUsuario(parametrosHelper.getUsuarioResponsavel());
		}

		// Quantidade Maxima Inclusoes
		if (parametrosHelper.getQtdMaxInclusoes() != null) {
			form.setQtdMaximaInclusao(parametrosHelper.getQtdMaxInclusoes().toString());
		}

		// Indicador CPF/CNPJ valido
		if (parametrosHelper.getIndicadorCpfCnpjValido() != null) {
			if (parametrosHelper.getIndicadorCpfCnpjValido().equals(ConstantesSistema.SIM)) {
				form.setIndicadorCpfCnpjValido(ConstantesSistema.SIM.toString());
			} else {
				form.setIndicadorCpfCnpjValido(ConstantesSistema.NAO.toString());
			}
		}
	}

	private void setDadosCliente(InserirComandoNegativacaoActionForm form,
			ParametrosComandoNegativacaoHelper parametrosHelper) {
		// Periodo de referencia Inicial
		if (parametrosHelper.getReferenciaInicial() != null) {
			form.setReferenciaInicial(Util.formatarAnoMesParaMesAno(parametrosHelper.getReferenciaInicial()));
		}

		// Periodo de referencia Final
		if (parametrosHelper.getReferenciaFinal() != null) {
			form.setReferenciaFinal(Util.formatarAnoMesParaMesAno(parametrosHelper.getReferenciaFinal()));
		}

		// Periodo de Vencimento Debito inicial
		if (parametrosHelper.getVencimentoInicial() != null) {
			form.setDataVencimentoInicial(Util.formatarData(parametrosHelper.getVencimentoInicial()));
		}

		// Periodo de Vencimento Debito Final
		if (parametrosHelper.getVencimentoFinal() != null) {
			form.setDataVencimentoFinal(Util.formatarData(parametrosHelper.getVencimentoFinal()));
		}

		// Valor Minimo do Debito
		if (parametrosHelper.getValoMinimoDebito() != null) {
			form.setValorDebitoInicial(Util.formatarMoedaReal(parametrosHelper.getValoMinimoDebito()));
		}

		// Valor Maximo do Debito
		if (parametrosHelper.getValoMaximoDebito() != null) {
			form.setValorDebitoFinal(Util.formatarMoedaReal(parametrosHelper.getValoMaximoDebito()));
		}

		// Id Cliente
		if (parametrosHelper.getIdCliente() != null) {
			form.setIdCliente(parametrosHelper.getIdCliente().toString());
		}

		// Nome Cliente
		if (parametrosHelper.getNomeCliente() != null) {
			form.setNomeCliente(parametrosHelper.getNomeCliente());
		}

		// Lista de Tipo de Cliente
		if (parametrosHelper.getColecaoTipoCliente() == null) {
			form.setTipoCliente(null);
		} else {
			String[] idsTipoCliente = new String[parametrosHelper.getColecaoTipoCliente().size()];
			Iterator colecaoTipoCliente = parametrosHelper.getColecaoTipoCliente().iterator();
			int qtdTipoCliente = 0;
			while (colecaoTipoCliente.hasNext()) {
				ClienteTipo clienteTipo = (ClienteTipo) colecaoTipoCliente.next();
				idsTipoCliente[qtdTipoCliente] = clienteTipo.getId().toString();
				qtdTipoCliente++;
			}
			form.setTipoCliente(idsTipoCliente);
		}
	}
}
