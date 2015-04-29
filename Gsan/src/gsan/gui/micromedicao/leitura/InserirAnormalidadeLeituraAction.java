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
 * Magno Jean Gouveia Silveira
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
package gsan.gui.micromedicao.leitura;

import gsan.atendimentopublico.ordemservico.FiltroTipoServico;
import gsan.atendimentopublico.ordemservico.ServicoTipo;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.micromedicao.leitura.FiltroLeituraAnormalidade;
import gsan.micromedicao.leitura.FiltroLeituraAnormalidadeConsumo;
import gsan.micromedicao.leitura.FiltroLeituraAnormalidadeLeitura;
import gsan.micromedicao.leitura.LeituraAnormalidade;
import gsan.micromedicao.leitura.LeituraAnormalidadeConsumo;
import gsan.micromedicao.leitura.LeituraAnormalidadeLeitura;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>
 * <b>[UC0190]</b> Inserir Anormalidade de Leitura
 * </p>
 * 
 * <p>
 * Esta funcionalidade permite inserir uma Anormalidade de Leitura
 * </p>
 * 
 * @author Thiago Ten�rio, Magno Gouveia
 * @since 07/02/2007, 23/08/2011
 */
public class InserirAnormalidadeLeituraAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute(Usuario.USUARIO_LOGADO);

		InserirAnormalidadeLeituraActionForm form = (InserirAnormalidadeLeituraActionForm) actionForm;

		String descricao = form.getDescricao();
		String abreviatura = form.getAbreviatura();
		String indicadorRelativoHidrometro = form.getIndicadorRelativoHidrometro();
		String indicadorImovelSemHidrometro = form.getIndicadorImovelSemHidrometro();
		String usoRestritoSistema = form.getUsoRestritoSistema();
		String perdaTarifaSocial = form.getPerdaTarifaSocial();
		String osAutomatico = form.getOsAutomatico();
		String tipoServico = form.getTipoServico();
		String consumoLeituraNaoInformado = form.getConsumoLeituraNaoInformado();
		String consumoLeituraInformado = form.getConsumoLeituraInformado();
		String leituraLeituraNaoturaInformado = form.getLeituraLeituraNaoturaInformado();
		String leituraLeituraInformado = form.getLeituraLeituraInformado();
		String fatorSemLeitura = form.getNumeroFatorSemLeitura();
		String fatorComLeitura = form.getNumeroFatorComLeitura();
		String indicadorLeitura = form.getIndicadorLeitura();
		String indicadorExibirAnormalidadeRelatorio = form.getIndicadorExibirAnormalidadeRelatorio();
		String indicadorExibirMensagemHidrometrosCalcada = form.getIndicadorExibirMensagemHidrometrosCalcada();
		String indicadorExibirMensagemHidrometrosSubstituidos = form.getIndicadorExibirMensagemHidrometrosSubstituidos();
		String indicadorNaoImprimirConta = form.getIndicadorNaoImprimirConta();
		String indicadorFotoObrigatoria = form.getIndicadorFotoObrigatoria();

		LeituraAnormalidade anormalidadeLeituraInserir = new LeituraAnormalidade();
		Collection colecaoPesquisa = null;

		sessao.removeAttribute("tipoPesquisaRetorno");

		if (Util.validarNumeroMaiorQueZERO(form.getTipoServico())) {
			FiltroTipoServico filtroTipoServico = new FiltroTipoServico();
			filtroTipoServico.adicionarParametro(new ParametroSimples(	FiltroLeituraAnormalidade.ID_TIPO_SERVICO,
																		form.getTipoServico()));
		}

		if (form.getConsumoLeituraNaoInformado() != null) {

			Integer idConsumoLeituraNaoInformado = new Integer(form.getConsumoLeituraNaoInformado());

			if (idConsumoLeituraNaoInformado.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {

				anormalidadeLeituraInserir.setLeituraAnormalidadeConsumoSemleitura(null);
			} else {
				FiltroLeituraAnormalidadeConsumo filtroLeituraAnormalidadeConsumo = new FiltroLeituraAnormalidadeConsumo();
				filtroLeituraAnormalidadeConsumo.adicionarParametro(new ParametroSimples(	FiltroLeituraAnormalidadeConsumo.ID,
																							form.getConsumoLeituraNaoInformado().toString()));
				Collection colecaoConsumoLeituraNaoInformado = (Collection) fachada.pesquisar(filtroLeituraAnormalidadeConsumo, LeituraAnormalidadeConsumo.class.getName());

				anormalidadeLeituraInserir.setLeituraAnormalidadeConsumoSemleitura((LeituraAnormalidadeConsumo) colecaoConsumoLeituraNaoInformado.iterator().next());
			}
		}

		if (form.getConsumoLeituraInformado() != null) {

			Integer idConsumoLeituraInformado = new Integer(form.getConsumoLeituraInformado());

			if (idConsumoLeituraInformado.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {

				anormalidadeLeituraInserir.setLeituraAnormalidadeConsumoComleitura(null);
			} else {
				FiltroLeituraAnormalidadeConsumo filtroLeituraAnormalidadeConsumo = new FiltroLeituraAnormalidadeConsumo();
				filtroLeituraAnormalidadeConsumo.adicionarParametro(new ParametroSimples(	FiltroLeituraAnormalidadeConsumo.ID,
																							form.getConsumoLeituraInformado().toString()));
				Collection colecaoConsumoLeituraInformado = (Collection) fachada.pesquisar(filtroLeituraAnormalidadeConsumo, LeituraAnormalidadeConsumo.class.getName());

				anormalidadeLeituraInserir.setLeituraAnormalidadeConsumoComleitura((LeituraAnormalidadeConsumo) colecaoConsumoLeituraInformado.iterator().next());
			}
		}

		if (form.getLeituraLeituraNaoturaInformado() != null) {

			Integer idLeituraLeituraNaoturaInformado = new Integer(form.getLeituraLeituraNaoturaInformado());

			if (idLeituraLeituraNaoturaInformado.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {

				anormalidadeLeituraInserir.setLeituraAnormalidadeLeituraSemleitura(null);
			} else {
				FiltroLeituraAnormalidadeLeitura filtroLeituraAnormalidadeLeitura = new FiltroLeituraAnormalidadeLeitura();
				filtroLeituraAnormalidadeLeitura.adicionarParametro(new ParametroSimples(	FiltroLeituraAnormalidadeLeitura.ID,
																							form.getLeituraLeituraNaoturaInformado().toString()));
				Collection colecaoLeituraLeituraNaoturaInformado = (Collection) fachada.pesquisar(filtroLeituraAnormalidadeLeitura, LeituraAnormalidadeLeitura.class.getName());

				// setando
				anormalidadeLeituraInserir.setLeituraAnormalidadeLeituraSemleitura((LeituraAnormalidadeLeitura) colecaoLeituraLeituraNaoturaInformado.iterator().next());
			}
		}

		if (form.getLeituraLeituraInformado() != null) {

			Integer idLeituraLeituraInformado = new Integer(form.getLeituraLeituraInformado());

			if (idLeituraLeituraInformado.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {

				anormalidadeLeituraInserir.setLeituraAnormalidadeLeituraComleitura(null);
			} else {
				FiltroLeituraAnormalidadeLeitura filtroLeituraAnormalidadeLeitura = new FiltroLeituraAnormalidadeLeitura();
				filtroLeituraAnormalidadeLeitura.adicionarParametro(new ParametroSimples(	FiltroLeituraAnormalidadeLeitura.ID,
																							form.getLeituraLeituraInformado().toString()));
				Collection colecaoLeituraLeituraInformado = (Collection) fachada.pesquisar(filtroLeituraAnormalidadeLeitura, LeituraAnormalidadeLeitura.class.getName());

				anormalidadeLeituraInserir.setLeituraAnormalidadeLeituraComleitura((LeituraAnormalidadeLeitura) colecaoLeituraLeituraInformado.iterator().next());
			}
		}

		// A Descri��o � obrigat�rio.
		if (descricao == null || descricao.equalsIgnoreCase("")) {
			throw new ActionServletException(	"atencao.required",
												null,
												"Descri��o");
		}

		// A Abreviatura � obrigat�rio.
		if (abreviatura == null || abreviatura.equalsIgnoreCase("")) {
			throw new ActionServletException(	"atencao.required",
												null,
												"Abreviatura");
		}

		// A Anormalidade Relativa a Hidr�metro � obrigat�rio.
		if (indicadorRelativoHidrometro == null || indicadorRelativoHidrometro.equalsIgnoreCase("")) {
			throw new ActionServletException(	"atencao.required",
												null,
												"Anormalidade Relativa a Hidr�metro");
		}

		// Anormalidade Aceita para Liga��o sem Hidr�metro � obrigat�rio.
		if (indicadorImovelSemHidrometro == null || indicadorImovelSemHidrometro.equalsIgnoreCase("")) {
			throw new ActionServletException(	"atencao.required",
												null,
												"Anormalidade Aceita para Liga��o sem Hidr�metro");
		}
		// Anormalidade de Uso Restrito do Sistema � obrigat�rio.
		if (usoRestritoSistema == null || usoRestritoSistema.equalsIgnoreCase("")) {
			throw new ActionServletException(	"atencao.required",
												null,
												"Anormalidade de Uso Restrito do Sistema");
		}

		// Anormalidade Acarreta Perda Tarifa Social � obrigat�rio.
		if (perdaTarifaSocial == null || perdaTarifaSocial.equalsIgnoreCase("")) {
			throw new ActionServletException(	"atencao.required",
												null,
												"Anormalidade Acarreta Perda Tarifa Social");
		}
		// Anormalidade Emite OS Autom�tica � obrigat�rio.
		if (osAutomatico == null || osAutomatico.equalsIgnoreCase("")) {
			throw new ActionServletException(	"atencao.required",
												null,
												"Anormalidade Emite OS Autom�tica");
		}
		//Exibir anormalidade no relatorio
		if(indicadorExibirAnormalidadeRelatorio == null || indicadorExibirAnormalidadeRelatorio.equals("")){
			throw new ActionServletException(	"atencao.required",
												null,
												"Exibir a anormalidade no relat�rio de leituras e anormalidades informadas");
		}

		anormalidadeLeituraInserir.setDescricao(descricao);
		anormalidadeLeituraInserir.setDescricaoAbreviada(abreviatura);
		anormalidadeLeituraInserir.setIndicadorRelativoHidrometro(new Short(indicadorRelativoHidrometro));
		anormalidadeLeituraInserir.setIndicadorImovelSemHidrometro(new Short(indicadorImovelSemHidrometro));
		anormalidadeLeituraInserir.setIndicadorEmissaoOrdemServico(new Short(usoRestritoSistema));
		anormalidadeLeituraInserir.setIndicadorPerdaTarifaSocial(new Short(perdaTarifaSocial));
		anormalidadeLeituraInserir.setIndicadorSistema(new Short(usoRestritoSistema));
		anormalidadeLeituraInserir.setIndicadorEmissaoOrdemServico(new Short(osAutomatico));
		anormalidadeLeituraInserir.setIndicadorExibirAnormalidadeRelatorio(new Short(indicadorExibirAnormalidadeRelatorio));
		anormalidadeLeituraInserir.setIndicadorExibirMensagemHidrometrosCalcada(new Short(indicadorExibirMensagemHidrometrosCalcada));
		anormalidadeLeituraInserir.setIndicadorExibirMensagemHidrometrosSubstituidos(new Short(indicadorExibirMensagemHidrometrosSubstituidos));
		anormalidadeLeituraInserir.setIndicadorNaoImprimirConta(new Short(indicadorNaoImprimirConta));
		anormalidadeLeituraInserir.setIndicadorFotoObrigatoria(new Short(indicadorFotoObrigatoria));
		
		if (tipoServico != null && !tipoServico.equals("")) {
			ServicoTipo servicoTipo = new ServicoTipo();
			servicoTipo.setId(new Integer(tipoServico));
			anormalidadeLeituraInserir.setServicoTipo(servicoTipo);
		} else {
			anormalidadeLeituraInserir.setServicoTipo(null);
		}

		LeituraAnormalidadeConsumo leituraAnormalidadeConsumoSemLeitua = new LeituraAnormalidadeConsumo();

		leituraAnormalidadeConsumoSemLeitua.setId(new Integer(consumoLeituraNaoInformado));
		anormalidadeLeituraInserir.setLeituraAnormalidadeConsumoSemleitura(leituraAnormalidadeConsumoSemLeitua);

		LeituraAnormalidadeConsumo leituraAnormalidadeConsumo = new LeituraAnormalidadeConsumo();

		leituraAnormalidadeConsumo.setId(new Integer(consumoLeituraInformado));
		anormalidadeLeituraInserir.setLeituraAnormalidadeConsumoComleitura(leituraAnormalidadeConsumo);

		LeituraAnormalidadeLeitura leituraAnormalidadeNaoLeitura = new LeituraAnormalidadeLeitura();

		leituraAnormalidadeNaoLeitura.setId(new Integer(leituraLeituraNaoturaInformado));
		anormalidadeLeituraInserir.setLeituraAnormalidadeLeituraSemleitura(leituraAnormalidadeNaoLeitura);

		LeituraAnormalidadeLeitura leituraAnormalidadeLeitura = new LeituraAnormalidadeLeitura();

		leituraAnormalidadeLeitura.setId(new Integer(leituraLeituraInformado));
		anormalidadeLeituraInserir.setLeituraAnormalidadeLeituraComleitura(leituraAnormalidadeLeitura);

		// Ultima altera��o
		anormalidadeLeituraInserir.setUltimaAlteracao(new Date());
		// Indicador de uso
		Short iu = 1;
		anormalidadeLeituraInserir.setIndicadorUso(iu);

		anormalidadeLeituraInserir.setNumeroFatorComLeitura(Util.formatarMoedaRealparaBigDecimal(fatorComLeitura));
		anormalidadeLeituraInserir.setNumeroFatorSemLeitura(Util.formatarMoedaRealparaBigDecimal(fatorSemLeitura));
		anormalidadeLeituraInserir.setIndicadorLeitura(new Short(indicadorLeitura));
		
		if (!Util.verificarNaoVazio(form.getNumeroVezesSuspendeLeitura())) {
			form.setNumeroVezesSuspendeLeitura("0");
		}
		anormalidadeLeituraInserir.setNumeroVezesSuspendeLeitura(new Integer(form.getNumeroVezesSuspendeLeitura()));

		if (!Util.verificarNaoVazio(form.getNumeroMesesLeituraSuspensa())) {
			form.setNumeroMesesLeituraSuspensa("0");
		}
		anormalidadeLeituraInserir.setNumeroMesesLeituraSuspensa(new Integer(form.getNumeroMesesLeituraSuspensa()));

		FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();

		filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(	FiltroLeituraAnormalidade.DESCRICAO,
																			anormalidadeLeituraInserir.getDescricao()));

		colecaoPesquisa = (Collection) fachada.pesquisar(filtroLeituraAnormalidade, LeituraAnormalidade.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			// 
			throw new ActionServletException(	"atencao.leitura_anormalidade_ja_cadastrada",
												null,
												anormalidadeLeituraInserir.getDescricao());
		} else {
			Integer idAnormalidadeLeitura = null;

			idAnormalidadeLeitura = fachada.inserirAnormalidadeLeitura(anormalidadeLeituraInserir, usuarioLogado);

			sessao.setAttribute("inserir", true);
			
			montarPaginaSucesso(httpServletRequest, "Anormalidade de Leitura de c�digo  "
					+ anormalidadeLeituraInserir.getId() + " inserida com sucesso.", "Inserir outra Anormalidade de Leitura", "exibirInserirAnormalidadeLeituraAction.do?menu=sim", "exibirAtualizarAnormalidadeLeituraAction.do?idRegistroAtualizacao="
					+ idAnormalidadeLeitura, "Atualizar Anormalidade de Leitura");

		}

		// devolve o mapeamento de retorno
		return retorno;
	}
}
