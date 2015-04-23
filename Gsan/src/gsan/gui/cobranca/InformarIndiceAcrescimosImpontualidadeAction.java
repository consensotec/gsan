package gsan.gui.cobranca;

import gsan.cobranca.FiltroIndicesAcrescimosImpontualidade;
import gsan.cobranca.IndicesAcrescimosImpontualidade;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.interceptor.RegistradorOperacao;
import gsan.seguranca.acesso.Operacao;
import gsan.seguranca.acesso.OperacaoEfetuada;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.seguranca.acesso.usuario.UsuarioAcao;
import gsan.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processamento para inserir o critério da cobrança e as linhas do criterio da
 * cobrança
 * 
 * @author Sávio Luiz
 * @date 18/09/2007
 */
public class InformarIndiceAcrescimosImpontualidadeAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		IndiceAcrescimosImpontualidadeForm indiceAcrescimosImpontualidadeForm = (IndiceAcrescimosImpontualidadeForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuarioLogado = (Usuario) sessao
				.getAttribute(Usuario.USUARIO_LOGADO);

		// Validar campos
		if (indiceAcrescimosImpontualidadeForm.getPercentualMulta() != null) {
			BigDecimal percentualMulta = Util
					.formatarMoedaRealparaBigDecimal(indiceAcrescimosImpontualidadeForm
							.getPercentualMulta());
			if (percentualMulta.compareTo(new BigDecimal("0.00")) <= 0
					|| percentualMulta.compareTo(new BigDecimal("100.00")) > 0) {
				throw new ActionServletException("atencao.percentual_invalido",
						null, "Multa");
			}
		}
		
		if (indiceAcrescimosImpontualidadeForm.getPercentualLimiteJuros() != null) {
			BigDecimal percentualLimiteJuros = Util
					.formatarMoedaRealparaBigDecimal(indiceAcrescimosImpontualidadeForm
							.getPercentualLimiteJuros());
			if (percentualLimiteJuros.compareTo(new BigDecimal("0.00")) <= 0
					|| percentualLimiteJuros.compareTo(new BigDecimal("100.00")) > 0) {
				throw new ActionServletException("atencao.percentual_invalido",
						null, "Limite dos Juros");
			}
		}

		if (indiceAcrescimosImpontualidadeForm.getPercentualLimiteMulta() != null) {
			BigDecimal percentualLimiteMulta = Util
					.formatarMoedaRealparaBigDecimal(indiceAcrescimosImpontualidadeForm
							.getPercentualLimiteMulta());
			if (percentualLimiteMulta.compareTo(new BigDecimal("0.00")) <= 0
					|| percentualLimiteMulta.compareTo(new BigDecimal("100.00")) > 0) {
				throw new ActionServletException("atencao.percentual_invalido",
						null, "Limite da Multa");
			}
		}
		
		Fachada fachada = Fachada.getInstancia();

		FiltroIndicesAcrescimosImpontualidade filtroIndicesAcrescimosImpontualidade = new FiltroIndicesAcrescimosImpontualidade();
		Integer anoMesReferencia = null;
		anoMesReferencia = Util
				.formatarMesAnoComBarraParaAnoMes(indiceAcrescimosImpontualidadeForm
						.getMesAnoReferencia());
		filtroIndicesAcrescimosImpontualidade
				.adicionarParametro(new ParametroSimples(
						FiltroIndicesAcrescimosImpontualidade.ANO_MES_REFERENCIA,
						anoMesReferencia));

		Collection colecaoIndicesAcrescimosImpontualidade = fachada.pesquisar(
				filtroIndicesAcrescimosImpontualidade,
				IndicesAcrescimosImpontualidade.class.getName());

		String mensagem = "";

		IndicesAcrescimosImpontualidade indicesAcrescimosImpontualidade = null;

		if (colecaoIndicesAcrescimosImpontualidade != null
				&& !colecaoIndicesAcrescimosImpontualidade.isEmpty()) {
			
			indicesAcrescimosImpontualidade = (IndicesAcrescimosImpontualidade) Util
					.retonarObjetoDeColecao(colecaoIndicesAcrescimosImpontualidade);
			
			indicesAcrescimosImpontualidade
					.setPercentualMulta(Util
							.formatarMoedaRealparaBigDecimal(indiceAcrescimosImpontualidadeForm
									.getPercentualMulta()));
			indicesAcrescimosImpontualidade
					.setPercentualJurosMora(Util
							.formatarMoedaRealparaBigDecimal(indiceAcrescimosImpontualidadeForm
									.getPercentualJurosMora()));

			String valorComPonto = indiceAcrescimosImpontualidadeForm
					.getFatorCorrecao().replace(",", ".");
			indicesAcrescimosImpontualidade
					.setFatorAtualizacaoMonetaria(new BigDecimal(valorComPonto));
			
			// Percentual Limite dos Juros
			indicesAcrescimosImpontualidade
					.setPercentualLimiteJuros(Util
							.formatarMoedaRealparaBigDecimal(indiceAcrescimosImpontualidadeForm
									.getPercentualLimiteJuros()));
			
			// Percentual Limite da Multa
			indicesAcrescimosImpontualidade
					.setPercentualLimiteMulta(Util
							.formatarMoedaRealparaBigDecimal(indiceAcrescimosImpontualidadeForm
									.getPercentualLimiteMulta()));

			// Indicador Juros Mensal
			indicesAcrescimosImpontualidade.setIndicadorJurosMensal(new Short(
					indiceAcrescimosImpontualidadeForm
							.getIndicadorJurosMensal()));

			// Indicador Multa Mensal
			indicesAcrescimosImpontualidade.setIndicadorMultaMensal(new Short(
					indiceAcrescimosImpontualidadeForm
							.getIndicadorMultaMensal()));
			
			indicesAcrescimosImpontualidade.setUltimaAlteracao(new Date());

			fachada.atualizar(indicesAcrescimosImpontualidade);
			mensagem = "atualizada";
			
		} else {
			indicesAcrescimosImpontualidade = new IndicesAcrescimosImpontualidade();
			indicesAcrescimosImpontualidade
					.setAnoMesReferencia(Util
							.formatarMesAnoComBarraParaAnoMes(indiceAcrescimosImpontualidadeForm
									.getMesAnoReferencia()));
			indicesAcrescimosImpontualidade
					.setPercentualMulta(Util
							.formatarMoedaRealparaBigDecimal(indiceAcrescimosImpontualidadeForm
									.getPercentualMulta()));
			indicesAcrescimosImpontualidade
					.setPercentualJurosMora(Util
							.formatarMoedaRealparaBigDecimal(indiceAcrescimosImpontualidadeForm
									.getPercentualJurosMora()));

			String valorComPonto = indiceAcrescimosImpontualidadeForm
					.getFatorCorrecao().replace(",", ".");
			indicesAcrescimosImpontualidade
					.setFatorAtualizacaoMonetaria(new BigDecimal(valorComPonto));
			
			// Percentual Limite dos Juros
			indicesAcrescimosImpontualidade
					.setPercentualLimiteJuros(Util
							.formatarMoedaRealparaBigDecimal(indiceAcrescimosImpontualidadeForm
									.getPercentualLimiteJuros()));
			
			// Percentual Limite da Multa
			indicesAcrescimosImpontualidade
					.setPercentualLimiteMulta(Util
							.formatarMoedaRealparaBigDecimal(indiceAcrescimosImpontualidadeForm
									.getPercentualLimiteMulta()));

			// Indicador Juros Mensal
			indicesAcrescimosImpontualidade.setIndicadorJurosMensal(new Short(
					indiceAcrescimosImpontualidadeForm
							.getIndicadorJurosMensal()));

			// Indicador Multa Mensal
			indicesAcrescimosImpontualidade.setIndicadorMultaMensal(new Short(
					indiceAcrescimosImpontualidadeForm
							.getIndicadorMultaMensal()));
			
			indicesAcrescimosImpontualidade.setUltimaAlteracao(new Date());

			fachada.inserir(indicesAcrescimosImpontualidade);

			mensagem = "inserida";
		}

		// ------------ REGISTRAR TRANSAÇÃO----------------------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_INFORMAR_INDICES_ACRESCIMOS_IMPONTUALIDADE,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_COBRANCA_ACAO_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		indicesAcrescimosImpontualidade.setOperacaoEfetuada(operacaoEfetuada);
		indicesAcrescimosImpontualidade.adicionarUsuario(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(indicesAcrescimosImpontualidade);
		// ------------ REGISTRAR TRANSAÇÃO----------------------------

		montarPaginaSucesso(httpServletRequest,
				"Índices dos acréscimos de impontualidade de "
						+ indiceAcrescimosImpontualidadeForm
								.getMesAnoReferencia() + " " + mensagem
						+ " com sucesso.", "", "");
		return retorno;
	}

}
