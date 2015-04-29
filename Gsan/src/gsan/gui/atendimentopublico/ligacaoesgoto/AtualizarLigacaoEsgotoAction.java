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
 * R�mulo Aur�lio de Melo Souza Filho
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
package gsan.gui.atendimentopublico.ligacaoesgoto;

import gsan.atendimentopublico.LigacaoOrigem;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDiametro;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoMaterial;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil;
import gsan.cadastro.imovel.FiltroImovel;
import gsan.cadastro.imovel.Imovel;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.interceptor.RegistradorOperacao;
import gsan.seguranca.acesso.Operacao;
import gsan.seguranca.acesso.OperacaoEfetuada;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.seguranca.acesso.usuario.UsuarioAcao;
import gsan.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gsan.util.ConstantesSistema;
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
 * Description of the Class
 * 
 * @author Leonardo Regis
 * @created 18 de Julho de 2006
 */
public class AtualizarLigacaoEsgotoAction extends GcomAction {

	/**
	 * Atualiza liga��o de esgoto
	 * 
	 * [FS0005] Registrar Transa��o
	 * 
	 * @author Leonardo Regis
	 * @date 21/07/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta Retorno (Forward = Sucesso)
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Form
		AtualizarLigacaoEsgotoActionForm ligacaoEsgotoActionForm = (AtualizarLigacaoEsgotoActionForm) actionForm;

		// Fachada
		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		// [UC0107] Registrar Transa��o
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_LIGACAO_ESGOTO_ATUALIZAR,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_LIGACAO_ESGOTO_ATUALIZAR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		// [UC0107] -Fim- Registrar Transa��o

		// Cria objeto LigacaoEsgoto
		LigacaoEsgoto ligacaoEsgoto = null;

		// Filtra Im�vel
		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,
				ligacaoEsgotoActionForm.getMatriculaImovel()));
		Collection<Imovel> colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());
		if (colecaoImovel != null && !colecaoImovel.isEmpty()) {
			Imovel imovel = (Imovel) colecaoImovel.iterator().next();

			// Seta no objeto informa��es do form
			ligacaoEsgoto = setLigacaoEsgoto(ligacaoEsgotoActionForm, imovel);

			// Seta novos valores da liga��o de esgoto para valida��o
			imovel.setLigacaoEsgoto(ligacaoEsgoto);

			// Faz as valida��es de atualiza��o de liga��o de esgoto
			fachada.validarLigacaoEsgotoImovelAtualizar(imovel);
		} else {
			ligacaoEsgotoActionForm.setInscricaoImovel("Matr�cula inexistente");
		}



		// Atualiza Liga��o de Esgoto
		fachada.atualizarLigacaoEsgoto(ligacaoEsgoto,usuario);

		// Setando Opera��o
		ligacaoEsgoto.setOperacaoEfetuada(operacaoEfetuada);
		ligacaoEsgoto.adicionarUsuario(usuario,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(ligacaoEsgoto);

		// [FS005] Monta a p�gina de sucesso
		montarPaginaSucesso(httpServletRequest,
				"Atualiza��o da Liga��o de Esgoto do im�vel "
						+ ligacaoEsgoto.getId() + " efetuada com sucesso!",
				"Atualizar Liga��o de Esgoto efetuada",
				"exibirAtualizarLigacaoEsgotoAction.do?matriculaImovel="
						+ ligacaoEsgoto.getId());
		return retorno;
	}

	/**
	 * Seta no objeto LigacaoEsgoto as atualiza��es.
	 * 
	 * @author Leonardo Regis, Diogo Peixoto 
	 * @date 21/07/2006, 10/08/2011
	 * 
	 * @param ligacaoEsgotoActionForm
	 * @param imovel
	 * @return LigacaoEsgoto
	 */
	private LigacaoEsgoto setLigacaoEsgoto(AtualizarLigacaoEsgotoActionForm ligacaoEsgotoActionForm, Imovel imovel) {
		LigacaoEsgoto ligacaoEsgoto;
		
		// Seta objeto LigacaoEsgoto
		ligacaoEsgoto = imovel.getLigacaoEsgoto();
		ligacaoEsgoto.setImovel(imovel);
		ligacaoEsgoto.setUltimaAlteracao(ligacaoEsgotoActionForm.getDataConcorrencia());

		// Data de Ligacao
		String dataLigacao = ligacaoEsgotoActionForm.getDataLigacao();
		if (Util.verificarNaoVazio(dataLigacao)){
			ligacaoEsgoto.setDataLigacao(Util.converteStringParaDate(dataLigacao));
		}

		// Di�metro
		LigacaoEsgotoDiametro ligacaoEsgotoDiametro = new LigacaoEsgotoDiametro();
		ligacaoEsgotoDiametro.setId(new Integer(ligacaoEsgotoActionForm.getDiametroLigacao()));
		ligacaoEsgoto.setLigacaoEsgotoDiametro(ligacaoEsgotoDiametro);
		
		// Material
		LigacaoEsgotoMaterial ligacaoEsgotoMaterialMaterial = new LigacaoEsgotoMaterial();
		ligacaoEsgotoMaterialMaterial.setId(new Integer(ligacaoEsgotoActionForm.getMaterialLigacao()));
		ligacaoEsgoto.setLigacaoEsgotoMaterial(ligacaoEsgotoMaterialMaterial);
		
		// Perfil
		LigacaoEsgotoPerfil ligacaoEsgotoPerfil = new LigacaoEsgotoPerfil();
		ligacaoEsgotoPerfil.setId(new Integer(ligacaoEsgotoActionForm.getPerfilLigacao()));
		ligacaoEsgoto.setLigacaoEsgotoPerfil(ligacaoEsgotoPerfil);

		// Percentual de Coleta
		String percentualColeta = ligacaoEsgotoActionForm.getPercentualColeta().replace(",", ".");
		ligacaoEsgoto.setPercentualAguaConsumidaColetada(new BigDecimal(percentualColeta));
		
		// Percentual de Esgoto
		if (Util.verificarNaoVazio(ligacaoEsgotoActionForm.getPercentualEsgoto())){
			String percentualEsgoto = ligacaoEsgotoActionForm.getPercentualEsgoto().replace(",", ".");
			ligacaoEsgoto.setPercentual(new BigDecimal(percentualEsgoto));
		} else {
			ligacaoEsgoto.setPercentual(null);
		}

		if (ligacaoEsgotoActionForm.getIdLigacaoOrigem() != null && 
				!ligacaoEsgotoActionForm.getIdLigacaoOrigem().equals(ConstantesSistema.NUMERO_NAO_INFORMADO) 
				&& !ligacaoEsgotoActionForm.getIdLigacaoOrigem().equals("")) {

			LigacaoOrigem ligacaoOrigem = new LigacaoOrigem();
			ligacaoOrigem.setId(new Integer(ligacaoEsgotoActionForm.getIdLigacaoOrigem()));
			ligacaoEsgoto.setLigacaoOrigem(ligacaoOrigem);

		}

		ligacaoEsgoto.setIndicadorCaixaGordura(new Short(ligacaoEsgotoActionForm.getIndicadorCaixaGordura()));
		ligacaoEsgoto.setIndicadorLigacaoEsgoto(new Short(ligacaoEsgotoActionForm.getIndicadorLigacao()));
		ligacaoEsgoto.setUltimaAlteracao(new Date());
		
		return ligacaoEsgoto;
	}
}
