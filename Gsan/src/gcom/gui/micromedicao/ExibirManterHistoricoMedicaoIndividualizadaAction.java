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
package gcom.gui.micromedicao;

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
 * 
 * @author Fábio Silva
 * @date 16/01/2015
 */
public class ExibirManterHistoricoMedicaoIndividualizadaAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws ParseException {

		ActionForward retorno = actionMapping.findForward("exibirManterHistoricoMedicaoIndividualizada");

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarHistoricoMedicaoIndividualizadaActionForm form = (FiltrarHistoricoMedicaoIndividualizadaActionForm)actionForm;

		if((form.getIdImovel()==null || form.getIdImovel().compareTo("")==0) 
				&& (form.getDescricaoImovel()!=null && !form.getDescricaoImovel().equals("")))
		{
			form.setIdImovel(null);
			form.setDescricaoImovel(null);
		}
		
		String mesAno = form.getMesAnoFaturamento();

		if (mesAno != null && !"".equalsIgnoreCase(mesAno)) {
			if (Util.validarAnoMes(mesAno)) {
				throw new ActionServletException("atencao.invalid", null, "Mês/Ano do Faturamento");
			}

			// [FS0001] Validar mês e ano referência
			int mes = Integer.parseInt(mesAno.substring(0, 2));
			if (mes > 12 || mes <= 0) {
				//throw new ActionServletException("atencao.ano_mes.invalido", null, idImovel);
				throw new ActionServletException("atencao.invalid", null, "Mês/Ano do Faturamento");
			}

			// [FS0002] Compara Ano Mês Referência com Ano Mes Atual
			SimpleDateFormat sdt = new SimpleDateFormat("MM/yyyy");
			Date mesAnoReferencia = sdt.parse(mesAno);
			Date dataAtual =  new Date();

//			if (mesAnoReferencia.getTime() > dataAtual.getTime()) {
//				throw new ActionServletException("atencao.ano_mes.maior.ano_mes_atual");
//			}

			// [FS0003] - Verifica ano e mês do faturamento
			if (form.getIdImovel() != null && !"".equalsIgnoreCase(form.getIdImovel())) {
				FiltroImovel filtroImovel = new FiltroImovel();

				filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, form.getIdImovel()));
				filtroImovel.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroImovel.INDICADOR_IMOVEL_EXCLUIDO, Imovel.IMOVEL_EXCLUIDO));
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra.rota.faturamentoGrupo");

				Collection<?> imovelPesquisado = this.getFachada().pesquisar(filtroImovel, Imovel.class.getName());

				if (imovelPesquisado != null && !imovelPesquisado.isEmpty()) {
					Imovel imovelCondominio = (Imovel) imovelPesquisado.iterator().next();
					String stringFaturamento = imovelCondominio.getQuadra().getRota().getFaturamentoGrupo().getAnoMesReferencia() + "";
		
					Calendar calendarFaturamento = Calendar.getInstance();
					calendarFaturamento.set(Calendar.YEAR, new Integer(stringFaturamento.substring(0, 4)).intValue());
					calendarFaturamento.set(Calendar.MONTH, new Integer(stringFaturamento.substring(4, 6)).intValue() - 1);
		
					Date mesAnoFaturamento =  calendarFaturamento.getTime();
		
					if (mesAnoReferencia.getTime() > mesAnoFaturamento.getTime()) {
						throw new ActionServletException("atencao.ano_mes.referencia.superior", null, form.getIdImovel());
					}
				}
			}
		}
		
		if(form.getTipoLigacao()==null || form.getTipoLigacao().compareTo("-1")==0){
			throw new ActionServletException("atencao.invalid", null, "Tipo de Ligação");
		}

		HistoricoMedicaoIndividualizadaHelper helper = new HistoricoMedicaoIndividualizadaHelper();

		if (form.getMesAnoFaturamento() != null && !"".equals(form.getMesAnoFaturamento())) {
			helper.setMesAnoFaturamento(Util.formatarMesAnoComBarraParaAnoMes(form.getMesAnoFaturamento()));
		}

		if (form.getIdImovel() != null && !"".equals(form.getIdImovel())) {
			helper.setIdImovel(Integer.parseInt(form.getIdImovel()));
		}

		if (form.getTipoLigacao() != null && !"".equals(form.getTipoLigacao())) {
			helper.setTipoLigacao(Integer.parseInt(form.getTipoLigacao()));
		}

		if (form.getGerenciaRegional() != null && !"".equals(form.getGerenciaRegional())) {
			helper.setGerenciaRegional(Integer.parseInt(form.getGerenciaRegional()));
		}

		if (form.getUnidadeNegocio() != null && !"".equals(form.getUnidadeNegocio())) {
			helper.setUnidadeNegocio(Integer.parseInt(form.getUnidadeNegocio()));
		}

		if (form.getLocalidadeInicial() != null && !"".equals(form.getLocalidadeInicial())) {
			helper.setLocalidadeInicial(Integer.parseInt(form.getLocalidadeInicial()));
		}

		if (form.getSetorComercialInicial() != null && !"".equals(form.getSetorComercialInicial())) {
			helper.setSetorComercialInicial(Integer.parseInt(form.getSetorComercialInicial()));
		}

		if (form.getRotaInicial() != null && !"".equals(form.getRotaInicial())) {
			helper.setRotaInicial(Integer.parseInt(form.getRotaInicial()));
		}

		if (form.getSequencialRotaInicial() != null && !"".equals(form.getSequencialRotaInicial())) {
			helper.setSequencialRotaInicial(Integer.parseInt(form.getSequencialRotaInicial()));
		}

		if (form.getLocalidadeFinal() != null && !"".equals(form.getLocalidadeFinal())) {
			helper.setLocalidadeFinal(Integer.parseInt(form.getLocalidadeFinal()));
		}

		if (form.getSetorComercialFinal() != null && !"".equals(form.getSetorComercialFinal())) {
			helper.setSetorComercialFinal(Integer.parseInt(form.getSetorComercialFinal()));
		}

		if (form.getRotaFinal() != null && !"".equals(form.getRotaFinal())) {
			helper.setRotaFinal(Integer.parseInt(form.getRotaFinal()));
		}

		if (form.getSequencialRotaFinal() != null && !"".equals(form.getSequencialRotaFinal())) {
			helper.setSequencialRotaFinal(Integer.parseInt(form.getSequencialRotaFinal()));
		}

		if (form.getRateio() != null && !"".equals(form.getRateio())) {
			helper.setRateio(Integer.parseInt(form.getRateio()));
		}

		if (form.getRateio() != null &&
				(form.getRateio().equals(String.valueOf(TipoRateio.SEM_RATEIO.getId())) || form.getRateio().equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO)))) {
			form.setFaixaInicial(null);
			form.setFaixaFinal(null);
		}

		if (form.getFaixaInicial() != null && !"".equals(form.getFaixaInicial())) {
			helper.setFaixaInicial(Integer.parseInt(form.getFaixaInicial()));
		}

		if (form.getFaixaFinal() != null && !"".equals(form.getFaixaFinal())) {
			helper.setFaixaFinal(Integer.parseInt(form.getFaixaFinal()));
		}
		
		if((helper.getFaixaInicial()!=null && helper.getFaixaFinal()!=null) 
				&& (helper.getFaixaInicial()>helper.getFaixaFinal()))
		{
			throw new ActionServletException("atencao.faixa.inicial.maior.faixa.final", null, "");
		}

		if (form.getImoveisVinculadosInicial() != null && !"".equals(form.getImoveisVinculadosInicial())) {
			helper.setImoveisVinculadosInicial(Integer.parseInt(form.getImoveisVinculadosInicial()));
		}

		if (form.getImoveisVinculadosFinal() != null && !"".equals(form.getImoveisVinculadosFinal())) {
			helper.setImoveisVinculadosFinal(Integer.parseInt(form.getImoveisVinculadosFinal()));
		}

		if (form.getAnormalidadeLeitura() != null && !"".equals(form.getAnormalidadeLeitura())) {
			helper.setAnormalidadeLeitura(Integer.parseInt(form.getAnormalidadeLeitura()));
		}

		if (form.getAnormalidadeConsumo() != null && !"".equals(form.getAnormalidadeConsumo())) {
			helper.setAnormalidadeConsumo(Integer.parseInt(form.getAnormalidadeConsumo()));
		}

		// Paginação
		Integer totalRegistros = this.getFachada().pesquisarHistoricoMedicaoIndividualizadaCount(helper);

		if (totalRegistros == null || totalRegistros.intValue() <= 0) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		retorno = this.controlarPaginacao(httpServletRequest, retorno, totalRegistros);

		Integer numeroPaginasPesquisa = 1;

		if (httpServletRequest.getAttribute("numeroPaginasPesquisa") != null) {
			numeroPaginasPesquisa = (Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa");
		}
		
		helper.setNumeroPaginas(numeroPaginasPesquisa);

		Collection<Object[]> colecaoImoveisCondominio = (Collection<Object[]>) this.getFachada().pesquisarHistoricoMedicaoIndividualizada(helper);
		List<ImovelFiltrado> imoveisFiltrados = null;

		if (colecaoImoveisCondominio != null && !colecaoImoveisCondominio.isEmpty()) {
			imoveisFiltrados = new ArrayList<ImovelFiltrado>();

			for (Object[] obj : colecaoImoveisCondominio) {
				ImovelFiltrado imovelFiltrado = new ImovelFiltrado();
				imovelFiltrado.setMatricula((Integer) obj[0]);
				imovelFiltrado.setCliente((String) obj[1]);
				imovelFiltrado.setIdTipoLigacao((Short)obj[2]);
				
				if (imovelFiltrado.getMatricula() != null) {
					imovelFiltrado.setEndereco(this.getFachada().pesquisarEndereco(imovelFiltrado.getMatricula()));
				}

				imoveisFiltrados.add(imovelFiltrado);
			}
		}

		sessao.setAttribute("imoveisFiltrados", imoveisFiltrados);
		httpServletRequest.setAttribute("mesAnoFaturamento", mesAno);

		return retorno;
	}
}
