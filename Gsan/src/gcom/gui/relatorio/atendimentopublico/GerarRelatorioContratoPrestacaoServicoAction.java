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
package gcom.gui.relatorio.atendimentopublico;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.atendimentopublico.GerarContratoPrestacaoServicoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.atendimentopublico.RelatorioContratoPrestacaoServico;
import gcom.relatorio.atendimentopublico.RelatorioContratoPrestacaoServicoJuridico;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action respons�vel pela exibi��o do relat�rio de bairro manter
 * 
 * @author S�vio Luiz
 * @created 11 de Julho de 2005
 */
public class GerarRelatorioContratoPrestacaoServicoAction extends
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
	@SuppressWarnings("rawtypes")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;

		Fachada fachada = Fachada.getInstancia();

		GerarContratoPrestacaoServicoActionForm gerarContratoPrestacaoServicoActionForm = (GerarContratoPrestacaoServicoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);
		// Inicio da parte que vai mandar os parametros para o relat�rio
		String idImovel = gerarContratoPrestacaoServicoActionForm.getIdImovel();;
		String idCliente = null;
		Integer idTipoRelacao = null;
		
		if (idImovel != null && !idImovel.trim().equals("")) {
			Imovel imovel = fachada.pesquisarImovel(new Integer(idImovel));
			if (imovel == null) {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Im�vel");
			}
			String[] arrayIdClienteIdRelacaoTipo = gerarContratoPrestacaoServicoActionForm.getIdCliente().split("[|]");
			idCliente = arrayIdClienteIdRelacaoTipo[0];
			idTipoRelacao = new Integer(arrayIdClienteIdRelacaoTipo[1]);
		}
		
		/*
		 * Pesquisa se existe contrato de
		 * ades�o vinculado ao munic�pio
		 * do im�vel
		 */
		Integer idControladorAdesao = fachada.pesquisarContratoAdesaoMunicipioAssociadoImovel(new Integer(idImovel));
		if(idControladorAdesao!=null){
			/*
			 * [UC1665] GerarContratoAdesao
			 * Emitir contrato de 
			 * ades�o
			 */
			EmitirContratoAdesaoAction emitirContratoAdesaoAction = new EmitirContratoAdesaoAction();
			emitirContratoAdesaoAction.emitirContratoAdesao(httpServletResponse, 
					new Integer(idImovel), new Integer(idCliente),idTipoRelacao,false);
		}else{
			FiltroCliente filtroCliente = new FiltroCliente();

			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,
					idCliente));
			
			filtroCliente.adicionarCaminhoParaCarregamentoEntidade(FiltroCliente.CLIENTE_TIPO);

			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoCliente = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());

			if (!colecaoCliente.isEmpty()) {
				
				Cliente cliente = (Cliente) colecaoCliente.iterator().next();
				
				if (cliente != null && cliente.getClienteTipo() != null && cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica().intValue() == ClienteTipo.INDICADOR_PESSOA_FISICA.intValue()) {

					RelatorioContratoPrestacaoServico relatorioContratoPrestacaoServico = new RelatorioContratoPrestacaoServico(
							(Usuario) (httpServletRequest.getSession(false))
									.getAttribute("usuarioLogado"));
					relatorioContratoPrestacaoServico.addParametro("idImovel",
							new Integer(idImovel));
					relatorioContratoPrestacaoServico.addParametro("idCliente",
							new Integer(idCliente));

					// Fim da parte que vai mandar os parametros para o relat�rio
					String tipoRelatorio = httpServletRequest
							.getParameter("tipoRelatorio");
					if (tipoRelatorio == null) {
						tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
					}
					relatorioContratoPrestacaoServico
							.addParametro("tipoFormatoRelatorio", Integer
									.parseInt(tipoRelatorio));

					sessao.setAttribute("relatorioContratoPrestacaoServico",
							relatorioContratoPrestacaoServico);
					retorno = actionMapping
							.findForward("gerarRelatorioContratoPrestacao");

				} else {
					
					RelatorioContratoPrestacaoServicoJuridico relatorioContratoPrestacaoServico = new RelatorioContratoPrestacaoServicoJuridico(
							(Usuario) (httpServletRequest.getSession(false))
									.getAttribute("usuarioLogado"));
					relatorioContratoPrestacaoServico.addParametro("idImovel",
							new Integer(idImovel));
					relatorioContratoPrestacaoServico.addParametro("idCliente",
							new Integer(idCliente));

					// Fim da parte que vai mandar os parametros para o relat�rio
					String tipoRelatorio = httpServletRequest
							.getParameter("tipoRelatorio");
					if (tipoRelatorio == null) {
						tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
					}
					relatorioContratoPrestacaoServico
							.addParametro("tipoFormatoRelatorio", Integer
									.parseInt(tipoRelatorio));
					retorno = processarExibicaoRelatorio(
							relatorioContratoPrestacaoServico, tipoRelatorio,
							httpServletRequest, httpServletResponse, actionMapping);
				}
			}

			// Fim da parte que vai mandar os parametros para o relat�rio
			String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

			RelatorioContratoPrestacaoServico relatorioContratoPrestacaoServico = new RelatorioContratoPrestacaoServico(
					(Usuario) (httpServletRequest.getSession(false))
							.getAttribute("usuarioLogado"));
			relatorioContratoPrestacaoServico.addParametro("idImovel", new Integer(
					idImovel));
			relatorioContratoPrestacaoServico.addParametro("idCliente",
					new Integer(idCliente));
			if (tipoRelatorio == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}

			relatorioContratoPrestacaoServico.addParametro("tipoFormatoRelatorio",
					Integer.parseInt(tipoRelatorio));
			retorno = processarExibicaoRelatorio(relatorioContratoPrestacaoServico,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);
		}

		// devolve o mapeamento contido na vari�vel retorno
		return retorno;
	}

}
