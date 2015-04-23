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
package gsan.gui.cobranca.spcserasa;

import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.cliente.FiltroCliente;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cobranca.NegativacaoComando;
import gsan.cobranca.NegativacaoCriterio;
import gsan.cobranca.Negativador;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.spcserasa.bean.InserirComandoNegativacaoPorGuiaPagamentoHelper;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0651] Inserir Comando de Negativação<br>
 * [SB0010] Comandar Negativação Por Guia de Pagamento<br>
 * 
 * Esta classe tem por finalidade concluir as informações das duas abas do processo de inserção
 * de um comando de negativação por guia de pagamento.
 *
 * @author André Miranda
 * @date 16/03/2015
 */
public class ConcluirInserirComandoNegativacaoPorGuiaPagamentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) {
        ActionForward retorno = actionMapping.findForward("telaSucesso");
        InserirComandoNegativacaoActionForm form = (InserirComandoNegativacaoActionForm) actionForm;
        Fachada fachada = Fachada.getInstancia();
        InserirComandoNegativacaoPorGuiaPagamentoHelper helper = new InserirComandoNegativacaoPorGuiaPagamentoHelper();

		if (fachada.verificarExistenciaComandoNegativadorNaoRealizado()) {
			throw new ActionServletException("atencao.existe_comando_negativado_em_aberto",
					"",	new Exception());
		}

        // Validação Aba 2
        validarDadosCliente(form, fachada);

        //[SB0004] - Incluir Comando Negativação por Critério
        //Dados Negativação Comando
        NegativacaoComando comando = new NegativacaoComando();
        comando.setIndicadorSimulacao(new Short(form.getSimular()));
        comando.setIndicadorComandoCriterio(ConstantesSistema.TIPO_COMANDO_POR_GUIA_PAGAMENTO);
        comando.setDataPrevista(Util.converteStringParaDate(form.getDataPrevista()));
        comando.setDataHoraComando(new Date());
        comando.setUltimaAlteracao(new Date());

        Usuario usuario = new Usuario();
        usuario.setId(new Integer(form.getUsuario()));
        comando.setUsuario(usuario);

        Negativador negativador = new Negativador();
        negativador.setId(new Integer(form.getIdNegativador()));
        comando.setNegativador(negativador);
        
        if(Util.verificarNaoVazio(form.getIdComandoSimulado())){
          NegativacaoComando negComandoSimulacao = new NegativacaoComando();
          negComandoSimulacao.setId(new Integer(form.getIdComandoSimulado()));
          comando.setComandoSimulacao(negComandoSimulacao);
        }

        comando.setIndicadorCpfCnpjValidado(Short.valueOf(form.getIndicadorCpfCnpjValido()));
    	comando.setIndicadorContaNomeCliente(ConstantesSistema.NAO);
    	comando.setIndicadorOrgaoPublico(ConstantesSistema.NAO);
    	comando.setIndicadorBaixaRenda(ConstantesSistema.SIM);

        helper.setNegativacaoComando(comando);
        
        //Dados Negativacao Critério
        NegativacaoCriterio criterio = new NegativacaoCriterio();
        criterio.setDescricaoTitulo(form.getTitulo());
        criterio.setDescricaoSolicitacao(form.getSolicitacao());

		if (Util.verificarNaoVazio(form.getReferenciaInicial())) {
        	criterio.setAnoMesReferenciaContaInicial(Util.formatarMesAnoComBarraParaAnoMes(form.getReferenciaInicial()));
		} else {
			Date dtReferenciaInicial = Util.verificarNaoVazio(form.getReferenciaFinal()) ?
				Util.converteStringParaDate("01/" + form.getReferenciaFinal()) :
				new Date();

			dtReferenciaInicial = Util.subtrairNumeroAnosDeUmaData(dtReferenciaInicial, -5);
			criterio.setAnoMesReferenciaContaInicial(Util.formataAnoMes(dtReferenciaInicial));
		}

		if (Util.verificarNaoVazio(form.getReferenciaFinal())) {
			criterio.setAnoMesReferenciaContaFinal(Util.formatarMesAnoComBarraParaAnoMes(form.getReferenciaFinal()));
		} else {
			criterio.setAnoMesReferenciaContaFinal(Util.formataAnoMes(new Date()));
		}

		if (Util.verificarNaoVazio(form.getDataVencimentoInicial())) {
        	criterio.setDataVencimentoDebitoInicial(Util.converteStringParaDate(form.getDataVencimentoInicial()));
		} else {
        	Date dtVencimentoInicial = Util.verificarNaoVazio(form.getDataVencimentoFinal()) ?
        		Util.converteStringParaDate(form.getDataVencimentoFinal()) :
        		new Date();

			dtVencimentoInicial = Util.subtrairNumeroAnosDeUmaData(dtVencimentoInicial, -5);
			criterio.setDataVencimentoDebitoInicial(dtVencimentoInicial);
		}

		if (Util.verificarNaoVazio(form.getDataVencimentoFinal())) {
			criterio.setDataVencimentoDebitoFinal(Util.converteStringParaDate(form.getDataVencimentoFinal()));
		} else {
			criterio.setDataVencimentoDebitoFinal(new Date());
		}

		criterio.setIndicadorNegativacaoImovelParalisacao(ConstantesSistema.SIM);
		criterio.setIndicadorNegativacaoImovelSituacaoCobranca(ConstantesSistema.SIM);
		criterio.setIndicadorNegativacaoContaRevisao(ConstantesSistema.NAO);
		criterio.setIndicadorNegativacaoGuiaPagamento(ConstantesSistema.SIM);
		criterio.setIndicadorParcelamentoAtraso(ConstantesSistema.NAO);
		criterio.setIndicadorNegativacaoRecebimentoCartaParcelamento(ConstantesSistema.NAO);

		if (Util.verificarIdNaoVazio(form.getIdCliente())) {
			Cliente cliente = new Cliente();
			cliente.setId(new Integer(form.getIdCliente()));
			criterio.setCliente(cliente);
		}

		criterio.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
		criterio.setUltimaAlteracao(new Date());

		if (Util.verificarNaoVazio(form.getValorDebitoInicial())) {
			criterio.setValorMinimoDebito(Util.formatarMoedaRealparaBigDecimal(form.getValorDebitoInicial()));
		} else {
			criterio.setValorMinimoDebito(Util.formatarMoedaRealparaBigDecimal("0"));
		}

		if (Util.verificarNaoVazio(form.getValorDebitoFinal())) {
			criterio.setValorMaximoDebito(Util.formatarMoedaRealparaBigDecimal(form.getValorDebitoFinal()));
		} else {
			criterio.setValorMaximoDebito(Util.formatarMoedaRealparaBigDecimal("99999999999,99"));
		}

		if (form.getDiasAtrasoParcelamento() != null && !form.getDiasAtrasoParcelamento().equals("")) {
			criterio.setNumeroDiasParcelamentoAtraso(new Integer(form.getDiasAtrasoParcelamento()));
		}

		if (form.getDiasAtrasoRecebimentoCarta() != null && !form.getDiasAtrasoRecebimentoCarta().equals("")) {
			criterio.setNumeroDiasAtrasoRecebimentoCartaParcelamento(new Short(form.getDiasAtrasoRecebimentoCarta()));
		}

		if (Util.verificarNaoVazio(form.getQtdMaximaInclusao())) {
			criterio.setQuantidadeMaximaInclusoes(new Integer(form.getQtdMaximaInclusao()));
		}

		criterio.setQuantidadeMinimaContas(0);
		criterio.setQuantidadeMaximaContas(999999999);

		helper.setNegativacaoCriterio(criterio);

		// Tipos de Cliente
		if (!Util.isVazioOrNulo(form.getTipoCliente())) {
			helper.setIdsTipoCliente(form.getTipoCliente());
		}

		// [SB0004]- Incluir Comando de Negativação por Critério
		Integer idNegativadorComando = fachada.inserirComandoNegativacaoPorGuiaPagamento(helper);

		// Montar a página de sucesso
		montarPaginaSucesso(request, "Comando Negativação de código " + idNegativadorComando + " inserido com sucesso.",
				"Inserir outro Comando Negativação", "exibirInserirComandoNegativacaoTipoComandoAction.do?menu=sim");

		return retorno;
	}

	private void validarDadosCliente(InserirComandoNegativacaoActionForm form, Fachada fachada) {
		Date dataAtual = new Date();
		Integer referenciaDataAtual = Util.getAnoMesComoInteger(dataAtual);

		String referenciaInicial = form.getReferenciaInicial();
		String referenciaFinal = form.getReferenciaFinal();
		String dataVencimentoInicial = form.getDataVencimentoInicial();
		String dataVencimentoFinal = form.getDataVencimentoFinal();
		String valorDebitoInicial = form.getValorDebitoInicial();
		String valorDebitoFinal = form.getValorDebitoFinal();
		String idCliente = form.getIdCliente();

		// Período de referência do débito
		if (Util.verificarNaoVazio(referenciaInicial) && Util.verificarNaoVazio(referenciaFinal)) {
			Integer mesAnoReferenciaInicial = Util.formatarMesAnoComBarraParaAnoMes(referenciaInicial);
			Integer mesAnoReferenciaFinal = Util.formatarMesAnoComBarraParaAnoMes(referenciaFinal);

			if (!Util.validarMesAno(referenciaInicial) || !Util.validarMesAno(referenciaFinal)) {
				throw new ActionServletException("atencao.adicionar_debito_ano_mes_referencia_invalido", null, "do débito");
			}
			if (mesAnoReferenciaInicial > mesAnoReferenciaFinal) {
				throw new ActionServletException("atencao.referencia.final.menor.referencia.inicial");
			}
			if (referenciaDataAtual < mesAnoReferenciaInicial) {
				throw new ActionServletException("atencao.referencia.posterior");
			}
			if (referenciaDataAtual < mesAnoReferenciaFinal) {
				throw new ActionServletException("atencao.referencia.posterior");
			}
		}

   	    SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

	  	Integer referenciaMinima = Util.subtrairAnoAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao(), 5);	  			

		if (Util.verificarNaoVazio(referenciaInicial)) {
			Integer mesAnoReferenciaInicial = Util.formatarMesAnoComBarraParaAnoMes(referenciaInicial);
			if (mesAnoReferenciaInicial < referenciaMinima) {
				throw new ActionServletException("atencao.periodo_referencia_debito_minimo");
			}
		}

		if (Util.verificarNaoVazio(referenciaFinal)) {
			Integer mesAnoReferenciaFinal = Util.formatarMesAnoComBarraParaAnoMes(referenciaFinal);
			if (mesAnoReferenciaFinal < referenciaMinima) {
				throw new ActionServletException("atencao.periodo_referencia_debito_minimo");
			}
		}

		// Período de vencimento do débito	
		Integer numeroDiasVencimentoCobranca = new Integer(sistemaParametro.getNumeroDiasVencimentoCobranca());			
		Date dataMinima = Util.subtrairNumeroAnosDeUmaData(Util.subtrairNumeroDiasDeUmaData(new Date(), numeroDiasVencimentoCobranca), -5);

		if (Util.verificarNaoVazio(dataVencimentoInicial)) {
			if (Util.validarDiaMesAno(dataVencimentoInicial)) {
				throw new ActionServletException("atencao.datavencimentodebinicial.invalida");
			}

			Date dtVencimentoInicial = Util.converteStringParaDate(dataVencimentoInicial);
			if (Util.compararData(dtVencimentoInicial, dataAtual) == 1) {
				throw new ActionServletException("atencao.data_inicial.posterior.hoje", null,
						Util.formatarData(dataAtual));
			}
			if (Util.compararData(dtVencimentoInicial, dataMinima) == -1) {
				throw new ActionServletException("atencao.periodo_vencimento_debito_minimo");
			}
		}

		if (Util.verificarNaoVazio(dataVencimentoFinal)) {
			if (Util.validarDiaMesAno(dataVencimentoFinal)) {
				throw new ActionServletException("atencao.datavencimentodebfinal.invalida");
			}

			Date dtVencimentoFinal = Util.converteStringParaDate(dataVencimentoFinal);
			if (Util.compararData(dtVencimentoFinal, dataAtual) == 1) {
				throw new ActionServletException("atencao.data_final.posterior.hoje", null,
						Util.formatarData(new Date()));
			}
			if (Util.compararData(dtVencimentoFinal, dataMinima) == -1) {
				throw new ActionServletException("atencao.periodo_vencimento_debito_minimo");
			}
		}

		if (Util.verificarNaoVazio(dataVencimentoInicial) && Util.verificarNaoVazio(dataVencimentoFinal)) {
			Date vencimentoDebInicial = Util.converteStringParaDate(dataVencimentoInicial);
			Date vencimentoDebFinal = Util.converteStringParaDate(dataVencimentoFinal);

			// Data Final do Período é anterior à Data Inicial do Período
			if (Util.compararData(vencimentoDebInicial, vencimentoDebFinal) == 1) {
				throw new ActionServletException("atencao.data_final_periodo.anterior.data_inicial_periodo");
			}
		}

		// Valor do débito
		if (Util.verificarNaoVazio(valorDebitoInicial) && Util.verificarNaoVazio(valorDebitoFinal)) {
			BigDecimal valorDebInicial = Util.formatarMoedaRealparaBigDecimal(valorDebitoInicial);
			BigDecimal valorDebFinal = Util.formatarMoedaRealparaBigDecimal(valorDebitoFinal);
			if (valorDebInicial.compareTo(valorDebFinal) == 1) {
				throw new ActionServletException("atencao.debito_inicial_maior_debito_final");
			}
		}

		// Cliente
		if (Util.verificarIdNaoVazio(idCliente)) {
			FiltroCliente filtroCliente = new FiltroCliente();
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, idCliente));
			Collection colecaoCliente = fachada.pesquisar(filtroCliente, Cliente.class.getName());

			if (colecaoCliente == null || colecaoCliente.isEmpty()) {
				throw new ActionServletException("atencao.cliente.inexistente");
			}
		}
	}
}
