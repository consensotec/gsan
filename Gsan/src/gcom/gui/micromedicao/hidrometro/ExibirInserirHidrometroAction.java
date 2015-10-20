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
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroCapacidade;
import gcom.micromedicao.hidrometro.FiltroHidrometroClasseMetrologica;
import gcom.micromedicao.hidrometro.FiltroHidrometroClassePressao;
import gcom.micromedicao.hidrometro.FiltroHidrometroDiametro;
import gcom.micromedicao.hidrometro.FiltroHidrometroFatorCorrecao;
import gcom.micromedicao.hidrometro.FiltroHidrometroLocalArmazenagem;
import gcom.micromedicao.hidrometro.FiltroHidrometroMarca;
import gcom.micromedicao.hidrometro.FiltroHidrometroRelojoaria;
import gcom.micromedicao.hidrometro.FiltroHidrometroTipo;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroClasseMetrologica;
import gcom.micromedicao.hidrometro.HidrometroClassePressao;
import gcom.micromedicao.hidrometro.HidrometroDiametro;
import gcom.micromedicao.hidrometro.HidrometroFatorCorrecao;
import gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem;
import gcom.micromedicao.hidrometro.HidrometroMarca;
import gcom.micromedicao.hidrometro.HidrometroRelojoaria;
import gcom.micromedicao.hidrometro.HidrometroTipo;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 */
public class ExibirInserirHidrometroAction extends GcomAction {
	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param actionMapping
	 *            Descri��o do par�metro
	 * @param actionForm
	 *            Descri��o do par�metro
	 * @param httpServletRequest
	 *            Descri��o do par�metro
	 * @param httpServletResponse
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Obt�m o action form
		HidrometroActionForm hidrometroActionForm = (HidrometroActionForm) actionForm;

		Collection colecaoHidrometroCapacidade = null;

		// Seta a a��o de retorno
		ActionForward retorno = actionMapping.findForward("inserirHidrometro");

		// Obt�m a sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

		// Caso o form esteja na sess�o � removido
		// sessao.removeAttribute("HidrometroActionForm");

		// Obt�m a facahda
		Fachada fachada = Fachada.getInstancia();

		// Obt�m o objetoCosulta vindo na sess�o
		String objetoConsulta = (String) httpServletRequest
				.getParameter("objetoConsulta");

		// Chamou a funcionalidade pela 1� vez.
		// O usu�rio pode chamar o filtro de hidr�metro que carrega objetos na
		// sess�o
		// e depois chamar o inserir hidr�metro. Necess�rio remover os objetos
		// da sess�o deixados pelo filtro.
		String limpaSessao = (String) httpServletRequest
				.getParameter("limpaSessao");

		if (limpaSessao != null && !limpaSessao.trim().equalsIgnoreCase("")
				&& (Integer.parseInt(limpaSessao)) == 1) {

			// remove objetos da sess�o vindos do filtro
			sessao.removeAttribute("colecaoHidrometroClasseMetrologica");
			sessao.removeAttribute("colecaoHidrometroMarca");
			sessao.removeAttribute("colecaoHidrometroDiametro");
			sessao.removeAttribute("colecaoHidrometroCapacidade");
			sessao.removeAttribute("colecaoHidrometroTipo");
			sessao.removeAttribute("colecaoHidrometroRelojoaria");
			sessao.removeAttribute("colecaoHidrometroFatorCorrecao");
			sessao.removeAttribute("colecaoHidrometroClassePressao");
		}

		// Caso a op��o selecionada seja Macromedidor:
		if (hidrometroActionForm.getIndicadorMacromedidor() != null 
				&& hidrometroActionForm.getIndicadorMacromedidor().trim().equals(Hidrometro.INDICADOR_MACROMEDIDOR)) {

			// Filtro de hidr�metro marca para obter todas as marcas de
			// hidr�metros de macromedidor ativas
			FiltroHidrometroMarca filtroHidrometroMarca = new FiltroHidrometroMarca();

			filtroHidrometroMarca.adicionarParametro(new ParametroSimples(
					FiltroHidrometroMarca.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroHidrometroMarca.adicionarParametro(new ParametroSimples(
				FiltroHidrometroMarca.INDICADOR_MACRO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroHidrometroMarca
					.setCampoOrderBy(FiltroHidrometroMarca.DESCRICAO);

			// Pesquisa a cole��o de hidr�metro marca
			Collection colecaoHidrometroMarca = fachada.pesquisar(
					filtroHidrometroMarca, HidrometroMarca.class.getName());
			
			sessao.setAttribute("colecaoHidrometroMarca", colecaoHidrometroMarca);
			
		} else if (hidrometroActionForm.getIndicadorMacromedidor() != null 
				&& (hidrometroActionForm.getIndicadorMacromedidor().trim().equals(Hidrometro.INDICADOR_MICROMEDIDOR)
						|| hidrometroActionForm.getIndicadorMacromedidor().trim().equals(Hidrometro.INDICADOR_REDE_ESGOTO))){
			// Caso contr�rio, ou seja, o usu�rio selecione Micromedidor

			
			// Filtro de hidr�metro marca para obter todas as marcas de
			// hidr�metros de micromedidor ativas
			FiltroHidrometroMarca filtroHidrometroMarca = new FiltroHidrometroMarca();

			filtroHidrometroMarca.adicionarParametro(new ParametroSimples(FiltroHidrometroMarca.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroHidrometroMarca.adicionarParametro(new ParametroSimples(FiltroHidrometroMarca.INDICADOR_MICRO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroHidrometroMarca.setCampoOrderBy(FiltroHidrometroMarca.DESCRICAO);

			// Pesquisa a cole��o de hidr�metro marca
			Collection colecaoHidrometroMarca = fachada.pesquisar(filtroHidrometroMarca, HidrometroMarca.class.getName());
			
			sessao.setAttribute("colecaoHidrometroMarca", colecaoHidrometroMarca);
		}
		
		// Verifica se o objeto � diferente de nulo
		if (objetoConsulta != null
				&& !objetoConsulta.trim().equalsIgnoreCase("")
				&& (Integer.parseInt(objetoConsulta)) == 1) {

			// Filtro para obter o local de armazenagem ativo de id informado
			FiltroHidrometroLocalArmazenagem filtroHidrometroLocalArmazenagem = new FiltroHidrometroLocalArmazenagem();

			filtroHidrometroLocalArmazenagem.adicionarParametro(new ParametroSimples(FiltroHidrometroLocalArmazenagem.ID, new Integer(hidrometroActionForm.getIdLocalArmazenagem()), ParametroSimples.CONECTOR_AND));
			filtroHidrometroLocalArmazenagem.adicionarParametro(new ParametroSimples(FiltroHidrometroLocalArmazenagem.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

			// Pesquisa de acordo com os par�metros informados no filtro
			Collection colecaoHidrometroLocalArmazenagem = fachada.pesquisar(filtroHidrometroLocalArmazenagem, HidrometroLocalArmazenagem.class.getName());

			// Verifica se a pesquisa retornou algum objeto para a cole��o
			if (colecaoHidrometroLocalArmazenagem != null
					&& !colecaoHidrometroLocalArmazenagem.isEmpty()) {

				// Obt�m o objeto da cole��o pesquisada
				HidrometroLocalArmazenagem hidrometroLocalArmazenagem = (HidrometroLocalArmazenagem) Util.retonarObjetoDeColecao(colecaoHidrometroLocalArmazenagem);

				// Exibe o c�digo e a descri��o pesquisa na p�gina
				httpServletRequest.setAttribute("corLocalArmazenagem", "valor");
				hidrometroActionForm.setIdLocalArmazenagem(hidrometroLocalArmazenagem.getId().toString());
				hidrometroActionForm.setLocalArmazenagemDescricao(hidrometroLocalArmazenagem.getDescricao());
				
			} else {

				// Exibe mensagem de c�digo inexiste e limpa o campo de c�digo
				httpServletRequest.setAttribute("corLocalArmazenagem", "exception");
				hidrometroActionForm.setIdLocalArmazenagem("");
				hidrometroActionForm.setLocalArmazenagemDescricao("Local de Armazenagem Inexistente");

			}

		} else if (sessao.getAttribute("colecaoHidrometroClasseMetrologica") == null
				&& sessao.getAttribute("colecaoHidrometroMarca") == null
				&& sessao.getAttribute("colecaoHidrometroDiametro") == null
				&& sessao.getAttribute("colecaoHidrometroCapacidade") == null
				&& sessao.getAttribute("colecaoHidrometroTipo") == null
				&& sessao.getAttribute("colecaoHidrometroRelojoaria") == null
				&& sessao.getAttribute("colecaoHidrometroFatorCorrecao") == null
				&& sessao.getAttribute("colecaoHidrometroClassePressao") == null) {
			
		
			// Remove objeto da sess�o
			sessao.removeAttribute("HidrometroActionForm");

			// Filtro de hidr�metro classe metrol�gica para obter todas as
			// classes metrol�gicas ativas
			FiltroHidrometroClasseMetrologica filtroHidrometroClasseMetrologica = new FiltroHidrometroClasseMetrologica();

			filtroHidrometroClasseMetrologica
					.adicionarParametro(new ParametroSimples(
							FiltroHidrometroClasseMetrologica.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroHidrometroClasseMetrologica
					.setCampoOrderBy(FiltroHidrometroClasseMetrologica.DESCRICAO);

			// Pesquisa a cole��o de classe metrol�gica
			Collection colecaoHidrometroClasseMetrologica = fachada.pesquisar(
					filtroHidrometroClasseMetrologica,
					HidrometroClasseMetrologica.class.getName());

			// Filtro de hidr�metro marca para obter todas as marcas de
			// hidr�metros ativas
			FiltroHidrometroMarca filtroHidrometroMarca = new FiltroHidrometroMarca();

			filtroHidrometroMarca.adicionarParametro(new ParametroSimples(
					FiltroHidrometroMarca.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroHidrometroMarca.adicionarParametro(new ParametroSimples(
					FiltroHidrometroMarca.INDICADOR_MICRO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroHidrometroMarca
					.setCampoOrderBy(FiltroHidrometroMarca.DESCRICAO);

			// Pesquisa a cole��o de hidr�metro marca
			Collection colecaoHidrometroMarca = fachada.pesquisar(
					filtroHidrometroMarca, HidrometroMarca.class.getName());

			// Filtro de hidr�metro di�metro para obter todos os di�metros de
			// hidr�metros ativos
			FiltroHidrometroDiametro filtroHidrometroDiametro = new FiltroHidrometroDiametro();

			filtroHidrometroDiametro.adicionarParametro(new ParametroSimples(
					FiltroHidrometroDiametro.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroHidrometroDiametro
					.setCampoOrderBy(FiltroHidrometroDiametro.NUMERO_ORDEM);

			// Pesquisa a cole��o de hidr�metro capacidade
			Collection colecaoHidrometroDiametro = fachada.pesquisar(
					filtroHidrometroDiametro, HidrometroDiametro.class
							.getName());

			// Filtro de hidr�metro capacidade para obter todos as capacidade de
			// hidr�metros ativas
			FiltroHidrometroCapacidade filtroHidrometroCapacidade = new FiltroHidrometroCapacidade();

			filtroHidrometroCapacidade.adicionarParametro(new ParametroSimples(
					FiltroHidrometroCapacidade.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroHidrometroCapacidade
					.setCampoOrderBy(FiltroHidrometroCapacidade.NUMERO_ORDEM);

			// Pesquisa a cole��o de hidr�metro capacidade
			colecaoHidrometroCapacidade = fachada.pesquisar(
					filtroHidrometroCapacidade, HidrometroCapacidade.class
							.getName());

			// Filtro de hidr�metro tipo para obter todos os tipos de
			// hidr�metros ativos
			FiltroHidrometroTipo filtroHidrometroTipo = new FiltroHidrometroTipo();

			filtroHidrometroTipo.adicionarParametro(new ParametroSimples(
					FiltroHidrometroTipo.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroHidrometroTipo
					.setCampoOrderBy(FiltroHidrometroTipo.DESCRICAO);

			// Pesquisa a cole��o de hidr�metro tipo
			Collection colecaoHidrometroTipo = fachada.pesquisar(
					filtroHidrometroTipo, HidrometroTipo.class.getName());
			
			

			// Filtro de hidr�metro relojoaria para obter todos os tipos de
			// hidr�metros relojoaria ativos
			FiltroHidrometroRelojoaria filtroHidrometroRelojoaria = new FiltroHidrometroRelojoaria();

			filtroHidrometroRelojoaria.adicionarParametro(new ParametroSimples(
					FiltroHidrometroRelojoaria.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroHidrometroRelojoaria
					.setCampoOrderBy(FiltroHidrometroRelojoaria.DESCRICAO);

			// Pesquisa a cole��o de hidr�metro tipo
			Collection colecaoHidrometroRelojoaria = fachada.pesquisar(
					filtroHidrometroRelojoaria, HidrometroRelojoaria.class.getName());
			
			

			// Filtro de hidr�metro relojoaria para obter todos os fatores de 
			// corre��o de hidr�metro ativos
			FiltroHidrometroFatorCorrecao filtroHidrometroFatorCorrecao = new FiltroHidrometroFatorCorrecao();

			filtroHidrometroFatorCorrecao.adicionarParametro(new ParametroSimples(
					FiltroHidrometroRelojoaria.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroHidrometroFatorCorrecao
					.setCampoOrderBy(FiltroHidrometroFatorCorrecao.DESCRICAO);

			// Pesquisa a cole��o de hidr�metro tipo
			Collection<HidrometroFatorCorrecao> colecaoHidrometroFatorCorrecao = fachada.pesquisar(
					filtroHidrometroFatorCorrecao, HidrometroFatorCorrecao.class.getName());
			
			

			// Filtro de hidr�metro relojoaria para obter todos os fatores de 
			// corre��o de hidr�metro ativos
			FiltroHidrometroClassePressao filtroHidrometroClassePressao = new FiltroHidrometroClassePressao();

			filtroHidrometroClassePressao.adicionarParametro(new ParametroSimples(
					FiltroHidrometroRelojoaria.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroHidrometroClassePressao
					.setCampoOrderBy(FiltroHidrometroClassePressao.DESCRICAO);

			// Pesquisa a cole��o de hidr�metro tipo
			Collection<HidrometroClassePressao> colecaoHidrometroClassePressao = fachada.pesquisar(filtroHidrometroClassePressao, HidrometroClassePressao.class.getName());


			hidrometroActionForm.setIndicadorOperacional("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
			hidrometroActionForm.setIndicadorMacromedidor(ConstantesSistema.NAO.toString());

			// Envia as cole��es na sess�o
			sessao.setAttribute("colecaoHidrometroClasseMetrologica",
					colecaoHidrometroClasseMetrologica);
			sessao.setAttribute("colecaoHidrometroMarca",
					colecaoHidrometroMarca);
			sessao.setAttribute("colecaoHidrometroDiametro",
					colecaoHidrometroDiametro);
			sessao.setAttribute("colecaoHidrometroCapacidade",
					colecaoHidrometroCapacidade);
			sessao.setAttribute("colecaoHidrometroTipo", 
					colecaoHidrometroTipo);
			sessao.setAttribute("colecaoHidrometroRelojoaria", 
					colecaoHidrometroRelojoaria);
			sessao.setAttribute("colecaoHidrometroFatorCorrecao", 
					colecaoHidrometroFatorCorrecao);
			sessao.setAttribute("colecaoHidrometroClassePressao", 
					colecaoHidrometroClassePressao);
		}

		// Filtro de hidr�metro capacidade para obter capacidade de hidr�metro
		// de acordo com o id
		FiltroHidrometroCapacidade filtroHidrometroCapacidadeNumeroDigitos = new FiltroHidrometroCapacidade();

		// Verifica se a cole��o de hidrometro capacidade � diferente de null
		if (colecaoHidrometroCapacidade != null
				&& !colecaoHidrometroCapacidade.isEmpty()) {

			// Obt�m o primeiro objeto da collection
			Iterator colecaoHidrometroCapacidadeIterator = colecaoHidrometroCapacidade
					.iterator();
			HidrometroCapacidade hidrometroCapacidade = (HidrometroCapacidade) colecaoHidrometroCapacidadeIterator
					.next();

			// Filtra pelo primeiro objeto da collection
			filtroHidrometroCapacidadeNumeroDigitos
					.adicionarParametro(new ParametroSimples(
							FiltroHidrometroCapacidade.ID, hidrometroCapacidade
									.getId()));
		} else {
			// Filtra pelo id selecionado no combobox
			filtroHidrometroCapacidadeNumeroDigitos
					.adicionarParametro(new ParametroSimples(
							FiltroHidrometroCapacidade.ID, new Integer(
									hidrometroActionForm
											.getIdHidrometroCapacidade())));

		}

		// Pesquisa o n�mero de d�gitos de acordo com o filtro
		Collection colecaoHidrometroCapacidadeNumeroDigitos = fachada
				.pesquisar(filtroHidrometroCapacidadeNumeroDigitos,
						HidrometroCapacidade.class.getName());
		
		if(colecaoHidrometroCapacidadeNumeroDigitos != null && !colecaoHidrometroCapacidadeNumeroDigitos.isEmpty())
		{
			// Retorna o objeto pesquisado
			HidrometroCapacidade hidrometroCapacidadeNumeroDigitos = (HidrometroCapacidade) Util
					.retonarObjetoDeColecao(colecaoHidrometroCapacidadeNumeroDigitos);
			Integer leituraMinimo = 0;
			Integer leituraMaximo = 0;
			// Obt�m as leituras
			if(!hidrometroCapacidadeNumeroDigitos.getLeituraMinimo().equals(""))
			{
				leituraMinimo = new Integer(hidrometroCapacidadeNumeroDigitos
						.getLeituraMinimo().toString());
			}
			if(!hidrometroCapacidadeNumeroDigitos.getLeituraMaximo().equals(""))
			{
				leituraMaximo = new Integer(hidrometroCapacidadeNumeroDigitos
					.getLeituraMaximo().toString());
			}
			// Obt�m a quantidade da diferen�a
			int quantidade = (leituraMaximo.intValue() - leituraMinimo.intValue()) + 1;
			Collection colecaoIntervalo = new ArrayList();

			// Adiciona a quantidade da diferen�a na cole��o
			for (int i = 0; i < quantidade; i++) {

				colecaoIntervalo.add(new Integer(leituraMinimo.intValue() + i));
			}
			sessao.setAttribute("colecaoIntervalo", colecaoIntervalo);
		}
		
		if (hidrometroActionForm.getIndicadorMacromedidor() != null && !"".equals(hidrometroActionForm.getIndicadorMacromedidor())) {
			if (hidrometroActionForm.getIndicadorMacromedidor().equals(Hidrometro.INDICADOR_REDE_ESGOTO)) {
				httpServletRequest.setAttribute("displayLigacao", "display: table-row;");
				httpServletRequest.setAttribute("displayFixoFaixa", "display: none;");
			} else {
				httpServletRequest.setAttribute("displayLigacao", "display: none;");
				httpServletRequest.setAttribute("displayFixoFaixa", "display: table-row;");
			}
		}
		
		return retorno;
	}
}
