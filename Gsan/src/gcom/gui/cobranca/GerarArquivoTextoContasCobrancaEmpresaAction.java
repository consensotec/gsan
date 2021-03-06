/**
 * 
 */
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
package gcom.gui.cobranca;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0896] Gerar Arquivo Texto das Contas em Cobran�a por Empresa
 * 
 * @author R�mulo Aur�lio
 * @since 29/10/2008
 */
public class GerarArquivoTextoContasCobrancaEmpresaAction extends ExibidorProcessamentoTarefaRelatorio  {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException  {

		
		ActionForward retorno = actionMapping.findForward("telaSucesso");



		GerarArquivoTextoContasCobrancaEmpresaActionForm form = (GerarArquivoTextoContasCobrancaEmpresaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		Integer[] idsRegistros = form.getIdRegistros() ;
		
		Collection colecaoIdsRegistro = new ArrayList();
		//Converte a String[] de id em Collection
		for (int i = 0; i < idsRegistros.length; i++) {
			// [FS0006] - Verificar exist�ncia de dados para os comandos
			// Caso algum dos comandos n�o tenha sido executado 
			if (!fachada.verificarComandoExecutado(idsRegistros[i])) {
				throw new ActionServletException("atencao.comando.nao_executado.geracao_txt", 
						null, idsRegistros[i].toString());
			}
			// Caso algum dos comandos selecionados n�o possua pelo menos um im�vel em cobran�a 
			Integer quantidadeContas = fachada.pesquisarQuantidadeContasComando(idsRegistros[i]);
			if (quantidadeContas == null || quantidadeContas.compareTo(new Integer(0)) == 0) {
				throw new ActionServletException("atencao.comando.nao_possui_dados.geracao_txt", 
						null, idsRegistros[i].toString());
			}
			colecaoIdsRegistro.add(idsRegistros[i]);
		}
		
		String idEmpresa = form.getIdEmpresa();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		
		fachada.inserirProcessoIniciadoContasCobranca(colecaoIdsRegistro,new Integer(idEmpresa),usuarioLogado);
		
		//montando p�gina de sucesso
		montarPaginaSucesso(httpServletRequest,
			"Arquivo Texto Contas Cobran�a por Empresa Enviado para Processamento", 
			"Voltar",
			"exibirGerarArquivoTextoContasCobrancaEmpresaAction.do");

	
		return retorno;
	}


}
