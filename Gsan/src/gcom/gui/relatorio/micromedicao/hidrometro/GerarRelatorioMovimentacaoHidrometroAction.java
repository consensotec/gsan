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
package gcom.gui.relatorio.micromedicao.hidrometro;

import gcom.fachada.Fachada;
import gcom.gui.micromedicao.hidrometro.HidrometroActionForm;
import gcom.micromedicao.hidrometro.FiltroHidrometroLocalArmazenagem;
import gcom.micromedicao.hidrometro.FiltroHidrometroMotivoMovimentacao;
import gcom.micromedicao.hidrometro.FiltroHidrometroMovimentacao;
import gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem;
import gcom.micromedicao.hidrometro.HidrometroMotivoMovimentacao;
import gcom.micromedicao.hidrometro.HidrometroMovimentacao;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.micromedicao.hidrometro.RelatorioMovimentacaoHidrometro;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.SistemaException;
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
 * Title: GCOM
 * Description: Sistema de Gest�o Comercial
 * @author Arthur Carvalho
 * @version 1.0
 */

public class GerarRelatorioMovimentacaoHidrometroAction extends
		ExibidorProcessamentoTarefaRelatorio {

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

		// cria a vari�vel de retorno
		ActionForward retorno = null;

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		HidrometroActionForm hidrometroActionForm = (HidrometroActionForm) actionForm;

		FiltroHidrometroMovimentacao filtroHidrometroMovimentacao = (FiltroHidrometroMovimentacao) sessao
				.getAttribute("filtroMovimentacaoHidrometro");

		// Inicio da parte que vai mandar os parametros para o relat�rio
		Fachada fachada = Fachada.getInstancia();
		
		HidrometroMovimentacao hidrometroMovimentacaoParametros = new HidrometroMovimentacao();

		String id = null;

		//Local Armazenagem Origem
		if (hidrometroActionForm.getLocalArmazenagemOrigem() != null && 
				!hidrometroActionForm.getLocalArmazenagemOrigem()
					.equals("")) {
			
			FiltroHidrometroLocalArmazenagem filtroHidrometroLocalArmazenagem = new FiltroHidrometroLocalArmazenagem();
			filtroHidrometroLocalArmazenagem.adicionarParametro(new ParametroSimples
					(FiltroHidrometroLocalArmazenagem.ID, hidrometroActionForm.getLocalArmazenagemOrigem()));
			
			Collection colecaoLocalArmazenagemOrigem = fachada.pesquisar(filtroHidrometroLocalArmazenagem, 
					HidrometroLocalArmazenagem.class.getName());
			
			if (colecaoLocalArmazenagemOrigem != null && !colecaoLocalArmazenagemOrigem.isEmpty()) {
				HidrometroLocalArmazenagem hidrometroLocalArmazenagem = (HidrometroLocalArmazenagem) 
					Util.retonarObjetoDeColecao(colecaoLocalArmazenagemOrigem);
				
				hidrometroMovimentacaoParametros.setHidrometroLocalArmazenagemOrigem(hidrometroLocalArmazenagem);
			}
			
		}
		
		//Local Armazenagem Destino
		if (hidrometroActionForm.getLocalArmazenagemDestino() != null && 
				!hidrometroActionForm.getLocalArmazenagemDestino()
					.equals("")) {
			
			FiltroHidrometroLocalArmazenagem filtroHidrometroLocalArmazenagem = new FiltroHidrometroLocalArmazenagem();
			filtroHidrometroLocalArmazenagem.adicionarParametro(new ParametroSimples
					(FiltroHidrometroLocalArmazenagem.ID, hidrometroActionForm.getLocalArmazenagemDestino()));
			
			Collection colecaoLocalArmazenagemDestino = fachada.pesquisar(filtroHidrometroLocalArmazenagem, 
					HidrometroLocalArmazenagem.class.getName());
			
			if (colecaoLocalArmazenagemDestino != null && !colecaoLocalArmazenagemDestino.isEmpty()) {
				HidrometroLocalArmazenagem hidrometroLocalArmazenagemDestino = (HidrometroLocalArmazenagem) 
					Util.retonarObjetoDeColecao(colecaoLocalArmazenagemDestino);
				
				hidrometroMovimentacaoParametros.setHidrometroLocalArmazenagemDestino(hidrometroLocalArmazenagemDestino);
			}
			
		}
		
		//Usuario
		if (hidrometroActionForm.getUsuario() != null && 
				!hidrometroActionForm.getUsuario()
					.equals("")) {
			
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples
					(FiltroUsuario.ID, hidrometroActionForm.getUsuario()));
			
			Collection colecaoUsuario = fachada.pesquisar(filtroUsuario, 
					Usuario.class.getName());
			
			if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
				Usuario usuario = (Usuario) 
					Util.retonarObjetoDeColecao(colecaoUsuario);
				
				hidrometroMovimentacaoParametros.setUsuario(usuario);
			}
			
		}
		
		//Motivo Movimenta��o
		if (hidrometroActionForm.getMotivoMovimentacao() != null && 
				!hidrometroActionForm.getMotivoMovimentacao()
					.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			FiltroHidrometroMotivoMovimentacao filtroHidrometroMotivo = new FiltroHidrometroMotivoMovimentacao();
			filtroHidrometroMotivo.adicionarParametro(new ParametroSimples
					(FiltroHidrometroMotivoMovimentacao.ID, hidrometroActionForm.getMotivoMovimentacao()));
			
			Collection colecaoMotivoMovimentacao = fachada.pesquisar(filtroHidrometroMotivo, HidrometroMotivoMovimentacao.class.getName());
			
			if (colecaoMotivoMovimentacao != null && !colecaoMotivoMovimentacao.isEmpty()) {
				HidrometroMotivoMovimentacao hidrometroMotivoMovimentacao = (HidrometroMotivoMovimentacao) 
					Util.retonarObjetoDeColecao(colecaoMotivoMovimentacao);
				
				hidrometroMovimentacaoParametros.setHidrometroMotivoMovimentacao(hidrometroMotivoMovimentacao);
			}
			
		}
		
		//Hora Movimentacao Inicial
		String horaMovimentacaoInicial = "";
		
		if (hidrometroActionForm.getHoraMovimentacaoInicial() != null && 
				!hidrometroActionForm.getHoraMovimentacaoInicial()
					.equals("")) {
			
			horaMovimentacaoInicial = hidrometroActionForm.getHoraMovimentacaoInicial();
		}
		
		//Hora Movimentacao Final
		String horaMovimentacaoFinal = "";
		
		if (hidrometroActionForm.getHoraMovimentacaoFinal() != null && 
				!hidrometroActionForm.getHoraMovimentacaoFinal()
					.equals("")) {
			
			horaMovimentacaoFinal = hidrometroActionForm.getHoraMovimentacaoFinal();
		}
		
		//Data Movimentacao Final
		String dataMovimentacaoFinal = "";
		
		if (hidrometroActionForm.getDataMovimentacaoFinal() != null && 
				!hidrometroActionForm.getDataMovimentacaoFinal()
					.equals("")) {
			
			dataMovimentacaoFinal = hidrometroActionForm.getDataMovimentacaoFinal();
		}

		//Data Movimentacao Inicial
		String dataMovimentacaoInicial = "";
		
		if (hidrometroActionForm.getDataMovimentacaoInicial() != null && 
				!hidrometroActionForm.getDataMovimentacaoInicial()
					.equals("")) {
			
			dataMovimentacaoInicial = hidrometroActionForm.getDataMovimentacaoInicial();
		}
		
		//Fixo
		String fixo = "";
		if (hidrometroActionForm.getFixo() != null && 
				!hidrometroActionForm.getFixo()
					.equals("")) {
			fixo = hidrometroActionForm.getFixo();
		}
		
		//Faixa Inicial
		String faixaInicial = "";
		if (hidrometroActionForm.getFaixaInicial() != null && 
				!hidrometroActionForm.getFaixaInicial()
					.equals("")) {
			faixaInicial = hidrometroActionForm.getFaixaInicial();
		}
		
		//Faixa Final
		String faixaFinal = "";
		if (hidrometroActionForm.getFaixaFinal() != null && 
				!hidrometroActionForm.getFaixaFinal()
					.equals("")) {
			faixaFinal = hidrometroActionForm.getFaixaFinal();
		}

		// seta os parametros que ser�o mostrados no relat�rio
		hidrometroMovimentacaoParametros.setId(id == null ? null : new Integer(id));
		hidrometroMovimentacaoParametros.setHoraMovimentacaoInicial(horaMovimentacaoInicial);
		hidrometroMovimentacaoParametros.setHoraMovimentacaoFinal(horaMovimentacaoFinal);
		hidrometroMovimentacaoParametros.setDataMovimentacaoInicial(dataMovimentacaoInicial);
		hidrometroMovimentacaoParametros.setDataMovimentacaoFinal(dataMovimentacaoFinal);
		hidrometroMovimentacaoParametros.setFixo(fixo);
		hidrometroMovimentacaoParametros.setFaixaInicial(faixaInicial);
		hidrometroMovimentacaoParametros.setFaixaFinal(faixaFinal);
		// Fim da parte que vai mandar os parametros para o relat�rio

		// cria uma inst�ncia da classe do relat�rio
		RelatorioMovimentacaoHidrometro relatorioMovimentacaoHidrometro = new RelatorioMovimentacaoHidrometro(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioMovimentacaoHidrometro.addParametro("filtroHidrometroMovimentacao",
				filtroHidrometroMovimentacao);
		relatorioMovimentacaoHidrometro.addParametro("hidrometroMovimentacaoParametros",
				hidrometroMovimentacaoParametros);

		// chama o met�do de gerar relat�rio passando o c�digo da analise
		// como par�metro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioMovimentacaoHidrometro.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioMovimentacaoHidrometro,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);

		} catch (SistemaException ex) {
			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "erro.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de aten��o de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		// devolve o mapeamento contido na vari�vel retorno
		return retorno;
	}

}