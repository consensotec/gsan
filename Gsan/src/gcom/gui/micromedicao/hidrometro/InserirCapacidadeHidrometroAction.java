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
package gcom.gui.micromedicao.hidrometro;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InserirCapacidadeHidrometroAction extends GcomAction {

	/**
	 * Este caso de uso permite inserir uma Capacidade de Hidrometro
	 * 
	 * [UC0515] Inserir Ag�ncia Capacidade Hidrometro
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @author Thiago Ten�rio
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		// Obt�m a sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		InserirCapacidadeHidrometroActionForm inserirCapacidadeHidrometroActionForm = (InserirCapacidadeHidrometroActionForm) actionForm;

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_CAPACIDADE_HIDROMETRO_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		// ------------ REGISTRAR TRANSA��O ----------------

//		String identificador = inserirCapacidadeHidrometroActionForm
//				.getIdentificador();
		String descricao = inserirCapacidadeHidrometroActionForm.getDescricao();
		String abreviatura = inserirCapacidadeHidrometroActionForm
				.getAbreviatura();
		String numMinimo = inserirCapacidadeHidrometroActionForm.getNumMinimo();
		String numMaximo = inserirCapacidadeHidrometroActionForm.getNumMaximo();
		String numOrdem = inserirCapacidadeHidrometroActionForm.getNumOrdem();
		String codigo = inserirCapacidadeHidrometroActionForm.getCodigo();

		HidrometroCapacidade hidrometroCapacidadeInserir = new HidrometroCapacidade();
		Collection colecaoPesquisa = null;

//		// O c�digo da Capacidade do Hidrometro � obrigat�rio.
//		if (identificador == null || identificador.equalsIgnoreCase("")) {
//			throw new ActionServletException("atencao.required", null,
//					"Identificador da capacidade de hidr�metro");
//		}

		// O c�digo da Capacidade do Hidrometro � obrigat�rio.
		if (codigo == null || codigo.equalsIgnoreCase("")) {
			throw new ActionServletException("atencao.required", null,
					"C�digo da capacidade do hidr�metro");
		}

		// A descri��o da Capacidade do Hidr�metro � obrigat�rio.
		if (descricao == null || descricao.equalsIgnoreCase("")) {
			throw new ActionServletException("atencao.required", null,
					"Descri��o da capacidade de hidr�metro");
		}

		// A descri��o Abreviada da Capacidade do Hidr�metro � obrigat�rio.
		if (abreviatura != null && !abreviatura.equalsIgnoreCase("")) {
			hidrometroCapacidadeInserir.setDescricaoAbreviada(abreviatura);
		}

		// O numero minimo de digitos de leitura do hidr�metro � obrigat�rio.
		if (numMinimo == null || numMinimo.equalsIgnoreCase("")) {
			throw new ActionServletException("atencao.required", null,
					"N�mero m�nimo de digitos de leitura do hidr�metro");
		}
		
		// O numero maximo de digitos de leitura do hidr�metro � obrigat�rio.
		if (numMaximo != null && !numMaximo.equalsIgnoreCase("")) {
			if (new Integer(numMaximo).intValue() < new Integer(numMinimo).intValue()) {
				throw new ActionServletException(
						"atencao.numero_minimo_nao_pode_ser_maior_que_numero_maximo",
						null, "Numero maximo de digitos de leitura do hidr�metro");
			} else {
				hidrometroCapacidadeInserir.setLeituraMaximo(new Short(numMaximo));
			}
		}

//		// O numero maximo de digitos de leitura do hidr�metro � obrigat�rio.
//		if (numMaximo == null || numMaximo.equalsIgnoreCase("")) {
//			throw new ActionServletException("atencao.required", null,
//					"N�mero maximo de digitos de leitura do hidr�metro");
//		}

//		hidrometroCapacidadeInserir.setId(new Integer(identificador));
		hidrometroCapacidadeInserir.setCodigoHidrometroCapacidade(codigo);
		hidrometroCapacidadeInserir.setDescricao(descricao);
		hidrometroCapacidadeInserir.setDescricaoAbreviada(abreviatura);		
		hidrometroCapacidadeInserir.setLeituraMinimo(new Short(numMinimo));
		hidrometroCapacidadeInserir.setLeituraMaximo(new Short(numMaximo));
		hidrometroCapacidadeInserir.setNumeroOrdem(new Short(numOrdem));
		
		// Indicador de uso
		Short iu = 1;
		hidrometroCapacidadeInserir.setIndicadorUso(iu);

		// Ultima altera��o
		hidrometroCapacidadeInserir.setUltimaAlteracao(new Date());

		FiltroHidrometroCapacidade filtroHidrometroCapacidade = new FiltroHidrometroCapacidade();
		
		
		filtroHidrometroCapacidade.adicionarParametro(new ParametroSimples(
				FiltroHidrometroCapacidade.NUMERO_ORDEM,
				hidrometroCapacidadeInserir.getNumeroOrdem()));

		// Verificar exist�ncia do N�mero de ordem da capacidade do hidr�metro
		colecaoPesquisa = fachada.pesquisar(filtroHidrometroCapacidade,
				HidrometroCapacidade.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			// N�mero de ordem da capacidade do hidr�metro j� existe
			throw new ActionServletException(
					"atencao.pesquisa_numero_de_ordem_da_capacidade_do_hidrometro_ja_cadastrada",
					null, numOrdem);
		}

		filtroHidrometroCapacidade.adicionarParametro(new ParametroSimples(
				FiltroHidrometroCapacidade.CODIGO_HIDROMETRO_CAPACIDADE,
				hidrometroCapacidadeInserir.getCodigoHidrometroCapacidade()));

		// Verificar exist�ncia da Capacidade do Hidrometro
		colecaoPesquisa = fachada.pesquisar(filtroHidrometroCapacidade,
				HidrometroCapacidade.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			// Capacidade de hidrometro j� existe
			throw new ActionServletException(
					"atencao.pesquisa_capacidade_do_hidrometro_ja_cadastrada",
					null, codigo);
		} else {
			Integer idHidrometroCapacidade = null;

			idHidrometroCapacidade = fachada.inserirCapacidadeHidrometro(
					hidrometroCapacidadeInserir, usuarioLogado);

			montarPaginaSucesso(
					httpServletRequest,
					"Capacidade do Hidr�metro de c�digo  "
							+ hidrometroCapacidadeInserir
									.getCodigoHidrometroCapacidade()
							+ " inserida com sucesso.",
					"Inserir outra Capacidade do Hidr�metro",
					"exibirInserirCapacidadeHidrometroAction.do?menu=sim",
					"exibirAtualizarCapacidadeHidrometroAction.do?inserir=sim&idRegistroAtualizacao="
							+ idHidrometroCapacidade,
					"Atualizar Capacidade do Hidr�metro Inserida");

		}

		// devolve o mapeamento de retorno
		return retorno;
	}

}
