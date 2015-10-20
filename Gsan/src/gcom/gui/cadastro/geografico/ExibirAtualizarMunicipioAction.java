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
package gcom.gui.cadastro.geografico;

import gcom.atendimentopublico.contratoadesao.ContratoAdesao;
import gcom.atendimentopublico.contratoadesao.FiltroContratoAdesao;
import gcom.cadastro.geografico.FiltroMicrorregiao;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.FiltroRegiaoDesenvolvimento;
import gcom.cadastro.geografico.FiltroUnidadeFederacao;
import gcom.cadastro.geografico.Microrregiao;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.RegiaoDesenvolvimento;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC005] Manter Municipio [SB0001]Atualizar Municipio
 * 
 * @author K�ssia Albuquerque
 * @date 08/01/2007
 */

public class ExibirAtualizarMunicipioAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("atualizarMunicipio");

		AtualizarMunicipioActionForm atualizarMunicipioActionForm = (AtualizarMunicipioActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltroUnidadeFederacao filtroUnidadeFederacao = new FiltroUnidadeFederacao();

		Collection colecaoUnidadeFederacao = fachada.pesquisar(
				filtroUnidadeFederacao, UnidadeFederacao.class.getName());

		httpServletRequest.setAttribute("colecaoUnidadeFederacao",
				colecaoUnidadeFederacao);

		FiltroMicrorregiao filtroMicrorregiao = new FiltroMicrorregiao();

		filtroMicrorregiao.setCampoOrderBy(FiltroMicrorregiao.DESCRICAO);

		filtroMicrorregiao.adicionarParametro(new ParametroSimples(
				FiltroMicrorregiao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoMicrorregiao = fachada.pesquisar(filtroMicrorregiao,
				Microrregiao.class.getName());

		httpServletRequest.setAttribute("colecaoMicrorregiao",
				colecaoMicrorregiao);

		FiltroRegiaoDesenvolvimento filtroRegiaoDesenvolvimento = new FiltroRegiaoDesenvolvimento();

		filtroRegiaoDesenvolvimento
				.setCampoOrderBy(FiltroRegiaoDesenvolvimento.DESCRICAO);

		filtroRegiaoDesenvolvimento.adicionarParametro(new ParametroSimples(
				FiltroRegiaoDesenvolvimento.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoRegiaoDesenv = fachada.pesquisar(
				filtroRegiaoDesenvolvimento, RegiaoDesenvolvimento.class
						.getName());

		httpServletRequest.setAttribute("colecaoRegiaoDesenv",
				colecaoRegiaoDesenv);
		
		// CONTRATO DE ADES�O DE SERVI�O
		
		FiltroContratoAdesao filtroContratoAdesao = new FiltroContratoAdesao();
		
		filtroContratoAdesao.setCampoOrderBy(FiltroContratoAdesao.DESCRICAO);
		filtroContratoAdesao.adicionarParametro(new ParametroSimples(FiltroContratoAdesao.INDICADOR_USO, ConstantesSistema.SIM));
		
		Collection<FiltroContratoAdesao> colecaoContratoAdesao = fachada.pesquisar(
				filtroContratoAdesao, ContratoAdesao.class.getName());
		
	
		httpServletRequest.setAttribute("colecaoContratoAdesao",colecaoContratoAdesao);

		Municipio municipio = null;

		String idMunicipio = null;

		if (httpServletRequest.getParameter("idMunicipio") != null) {
			// tela do manter
			idMunicipio = (String) httpServletRequest
					.getParameter("idMunicipio");
			sessao.setAttribute("idMunicipio", idMunicipio);
		} else if (sessao.getAttribute("idMunicipio") != null) {
			// tela do filtrar
			idMunicipio = (String) sessao.getAttribute("idMunicipio");
		} else if (httpServletRequest
				.getParameter("idRegistroInseridoAtualizar") != null) {
			// link na tela de sucesso do inserir municipio
			idMunicipio = (String) httpServletRequest
					.getParameter("idRegistroInseridoAtualizar");
			
			sessao.setAttribute("caminhoRetornoVoltar",
					"/gsan/exibirFiltrarMunicipioAction.do?menu=sim");
		}

		if (idMunicipio == null) {

			if (sessao.getAttribute("idRegistroAtualizar") != null) {
				municipio = (Municipio) sessao
						.getAttribute("idRegistroAtualizar");
			} else {
				idMunicipio = (String) httpServletRequest.getParameter(
						"idMunicipio").toString();
			}
		}

		// ------Inicio da parte que verifica se vem da p�gina de
		// municipio_manter.jsp

		if (municipio == null) {

			if (idMunicipio != null && !idMunicipio.equals("")) {

				FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

				filtroMunicipio
						.adicionarCaminhoParaCarregamentoEntidade("unidadeFederacao");
				filtroMunicipio
						.adicionarCaminhoParaCarregamentoEntidade("microrregiao");
				filtroMunicipio
						.adicionarCaminhoParaCarregamentoEntidade("regiaoDesenvolvimento");
				
				filtroMunicipio.adicionarParametro(new ParametroSimples(
						FiltroMunicipio.ID, idMunicipio));

				Collection colecaoMunicipio = fachada.pesquisar(
						filtroMunicipio, Municipio.class.getName());

				if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {

					municipio = (Municipio) Util
							.retonarObjetoDeColecao(colecaoMunicipio);

				}
			}
		}

		// ------ O municipio foi encontrado

		atualizarMunicipioActionForm.setCodigoMunicipio(municipio.getId()
				.toString());

		atualizarMunicipioActionForm.setNomeMunicipio(municipio.getNome());

		if (municipio.getDdd() != null) {
			atualizarMunicipioActionForm.setCodigoDdd(municipio.getDdd()
					.toString());
		} else {
			atualizarMunicipioActionForm.setCodigoDdd("");
		}

		if (municipio.getCepInicio() != null) {
			atualizarMunicipioActionForm.setCepInicial(municipio.getCepInicio()
					.toString());
		} else {
			atualizarMunicipioActionForm.setCepInicial("");
		}

		if (municipio.getCepFim() != null) {
			atualizarMunicipioActionForm.setCepFinal(municipio.getCepFim()
					.toString());
		} else {
			atualizarMunicipioActionForm.setCepFinal("");
		}

		atualizarMunicipioActionForm.setUnidadeFederacao(municipio
				.getUnidadeFederacao().getId().toString());

		atualizarMunicipioActionForm.setMicroregiao(municipio.getMicrorregiao()
				.getId().toString());

		atualizarMunicipioActionForm.setRegiaoDesenv(municipio
				.getRegiaoDesenvolvimento().getId().toString());
		
		if(municipio.getContratoAdesao() == null){
			atualizarMunicipioActionForm.setContratoAdesao(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO));			
		}else{
			atualizarMunicipioActionForm.setContratoAdesao(municipio
					.getContratoAdesao().toString());			
		}
		
		
		if (municipio.getDataConcessaoInicio() != null) {
			atualizarMunicipioActionForm.setDataInicioConcessao(Util
					.formatarData(municipio.getDataConcessaoInicio()));
		} else {
			atualizarMunicipioActionForm.setDataInicioConcessao("");
		}

		if (municipio.getDataConcessaoFim() != null) {
			atualizarMunicipioActionForm.setDataFimConcessao(Util
					.formatarData(municipio.getDataConcessaoFim()));
		} else {
			atualizarMunicipioActionForm.setDataFimConcessao("");
		}

		atualizarMunicipioActionForm.setIndicadorUso(""
				+ municipio.getIndicadorUso());

		if (municipio.getCodigoIbge() != null) {

			atualizarMunicipioActionForm.setCodigoIbge(municipio
					.getCodigoIbge());
		}
		
		if(municipio.getIndicadorRelacaoQuadraBairro() != null && 
				municipio.getIndicadorRelacaoQuadraBairro().toString().equals("1")){
			
			atualizarMunicipioActionForm.setIndicadorRelacaoQuadraBairro("1");
		}else{
			atualizarMunicipioActionForm.setIndicadorRelacaoQuadraBairro("2");
		}
		
		sessao.setAttribute("municipio", municipio);

		// ------ Fim da parte que verifica se vem da p�gina de
		// municipio_manter.jsp
		

		return retorno;
	}

}
