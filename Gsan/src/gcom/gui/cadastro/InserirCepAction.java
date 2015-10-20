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
package gcom.gui.cadastro;

import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.CepTipo;
import gcom.cadastro.endereco.FiltroCep;
import gcom.cadastro.endereco.FiltroCepTipo;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0883] Inserir Cep
 * 
 * @author Vin�cius Medeiros
 * @date 10/02/2009
 */

public class InserirCepAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclus�o de um Cep
	 * 
	 * [UC0883] Inserir Cep
	 * 
	 * 
	 * @author Vin�cius Medeiros
	 * @date 10/02/2009
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

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirCepActionForm inserirCepActionForm = (InserirCepActionForm) actionForm;

		// Mudar isso quando houver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();
		
		String codigo = inserirCepActionForm.getCodigo();
		String cepTipo = inserirCepActionForm.getCepTipo();
		String municipioId = inserirCepActionForm.getMunicipioId();
		String bairro = inserirCepActionForm.getBairro();
		String bairroId = inserirCepActionForm.getBairroId();
		String logradouro = inserirCepActionForm.getLogradouro();
		
		Cep cep = new Cep();
		Collection colecaoPesquisa = null;

		// Verifica se o Tipo de CEP foi passado
		if (cepTipo != null
				&& !cepTipo.equalsIgnoreCase("")) {

			FiltroCepTipo filtroCepTipo = new FiltroCepTipo();

			filtroCepTipo.adicionarParametro(new ParametroSimples(
					FiltroCepTipo.ID, cepTipo));
			filtroCepTipo.adicionarParametro(new ParametroSimples(
					FiltroCepTipo.INDICADOR_USO, 
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna Tipo de CEP
			colecaoPesquisa = fachada.pesquisar(filtroCepTipo,
					CepTipo.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				throw new ActionServletException(
						"atencao.cep_tipo_inexistente");
			} else {
				CepTipo objetoCepTipo = (CepTipo) Util
						.retonarObjetoDeColecao(colecaoPesquisa);
				cep.setCepTipo(objetoCepTipo);
			}
		}
		
		// Verifica se o c�digo foi passado
		if (!"".equals(codigo)) {
			Integer codigoFormatado = new Integer(Util.retirarFormatacaoCEP(inserirCepActionForm.getCodigo()));
			cep.setCodigo(new Integer(codigoFormatado));
		} else {
			throw new ActionServletException("atencao.required", null,
					"C�digo");
		}
		
		// Verifica se o municipio foi passado
		if (municipioId != null
				&& !municipioId.equalsIgnoreCase("")) {

			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.ID, municipioId));
			
			filtroMunicipio.adicionarCaminhoParaCarregamentoEntidade("unidadeFederacao");

			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna Municipio
			colecaoPesquisa = fachada.pesquisar(filtroMunicipio,
					Municipio.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa.municipio_inexistente");
			} else {
				Municipio objetoMunicipio = (Municipio) Util
						.retonarObjetoDeColecao(colecaoPesquisa);
				cep.setMunicipio(objetoMunicipio.getNome());
				
				cep.setSigla(objetoMunicipio.getUnidadeFederacao().getSigla());
	        }
		}
		
		// Verifica se o bairro foi passado
		if (bairroId != null
				&& !bairroId.equalsIgnoreCase("")) {

			FiltroBairro filtroBairro = new FiltroBairro();

			filtroBairro.adicionarParametro(new ParametroSimples(
					FiltroBairro.CODIGO, bairroId));
			filtroBairro.adicionarParametro(new ParametroSimples(
					FiltroBairro.NOME,bairro));

			filtroBairro.adicionarParametro(new ParametroSimples(
					FiltroBairro.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna Bairro
			colecaoPesquisa = fachada.pesquisar(filtroBairro,
					Bairro.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa.bairro_inexistente");
			} else {
				Bairro objetoBairro = (Bairro) Util
						.retonarObjetoDeColecao(colecaoPesquisa);
				cep.setBairro(objetoBairro.getNome());
			}
		}
		
		if(!"".equals(inserirCepActionForm.getLogradouro())) {
			cep.setDescricaoTipoLogradouro(inserirCepActionForm.getLogradouroTipo());
		}
		
		cep.setUltimaAlteracao(new Date());

		cep.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
		
		if(!"".equals(inserirCepActionForm.getLogradouro())) {
			cep.setLogradouro(logradouro);
		}

		FiltroCep filtroCep = new FiltroCep();

		filtroCep.adicionarParametro(
			new ParametroSimples(
				FiltroCep.CODIGO, 
				cep.getCodigo()));
		
		colecaoPesquisa = 
			(Collection) this.getFachada().pesquisar(
				filtroCep, Cep.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			
			// Caso j� haja um CEP com o c�digo passado
			throw new ActionServletException("atencao.cep_ja_cadastrado", 
				null,cep.getCodigo().toString());
		} else {

			Integer cepId = (Integer) this.getFachada().inserir(cep);

			montarPaginaSucesso(httpServletRequest,
				"CEP " + codigo + " inserido com sucesso.",
				"Inserir outro CEP",
				"exibirInserirCepAction.do?menu=sim",
				"exibirAtualizarCepAction.do?idRegistroAtualizacao="+ cepId,
				"Atualizar CEP Inserido");

			sessao.removeAttribute("InserirCepActionForm");
			sessao.removeAttribute("colecaoCepTipo");
			sessao.removeAttribute("colecaoLogradouroTipo");
			sessao.removeAttribute("colecaoUnidadeFederacao");
			sessao.removeAttribute("colecaoCep");
			sessao.removeAttribute("colecaoCep2");
			
			
			return retorno;
		}

	}
}