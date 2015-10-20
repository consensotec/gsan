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
* Erivan Nogueira de Sousa
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
package gcom.gui.financeiro;

import java.io.File;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.relatorio.RelatorioProcessado;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Gerar Arquivos EFD-PIS/Confins.
 *
 * @author Erivan Sousa
 * @date 31/01/2012
 */
public class GerarArquivoEfdPisConfinsAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, 
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno para a tela de sucesso.
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		GerarArquivoEfdPisConfinsActionForm gerarArquivoEfdPisConfinsActionForm = (GerarArquivoEfdPisConfinsActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		//recupera os parâmetros informados pelo usuário.
		String[] tipoRegistroInformado = gerarArquivoEfdPisConfinsActionForm.getTipoRegistro();
		String mesAnoReferencia = gerarArquivoEfdPisConfinsActionForm.getMesAnoReferencia();
		Short indicadorTotalizarMunicipio = new Short(gerarArquivoEfdPisConfinsActionForm.getIndicadorTotalizarMunicipio());
		Short indicadorTipoGeracao = new Short(gerarArquivoEfdPisConfinsActionForm.getIndicadorTipoGeracao());
		int []tipoRegistro = null;
		
		if(tipoRegistroInformado != null && tipoRegistroInformado.length != 0){
			//verifica se foi selecionado a opção de gerar todos os registros
			for (int i = 0; i < tipoRegistroInformado.length; i++) {
				if(Integer.parseInt(tipoRegistroInformado[i]) == ConstantesSistema.NUMERO_NAO_INFORMADO){
					tipoRegistro = new int[]{ConstantesSistema.NUMERO_NAO_INFORMADO};
				}
			}
			//caso não tenha sido selecionado a opção de gerar todos os 
			//registros gera o vetor de inteiros com as opções selecionadas
			if(tipoRegistro == null){
				tipoRegistro = new int[tipoRegistroInformado.length];
				for (int i = 0; i < tipoRegistroInformado.length; i++) {
					tipoRegistro[i] = Integer.parseInt(tipoRegistroInformado[i]);
				}
			}
		}
		
		if(mesAnoReferencia != null && !mesAnoReferencia.equals("")){
			File arquivoTexto = fachada.gerarArquivoEFDPisConfins(tipoRegistro, 
				Util.formatarMesAnoComBarraParaAnoMes(mesAnoReferencia), indicadorTotalizarMunicipio == 1 ? true : false, indicadorTipoGeracao);
			
			HttpSession sessao = httpServletRequest.getSession(false);
			int tipoRelatorio = TarefaRelatorio.TIPO_HTML;
	 		
	 		try {
	 			RelatorioProcessado relatorioProcessado = null;
				relatorioProcessado = 
					new RelatorioProcessado(
						Util.converterFileParaArrayByte(arquivoTexto),
						tipoRelatorio
					);
				
				httpServletRequest.setAttribute("telaSucessoRelatorio",true);
				sessao.setAttribute("tipoRelatorio", tipoRelatorio+"");
				sessao.setAttribute("relatorioProcessado", relatorioProcessado);
			
			} catch (Exception e) {
				e.printStackTrace();
				throw new ActionServletException("erro.sistema");
			}
		}else{
			throw new ActionServletException("atencao.informe_referencia");
		}
	
		
		// montando página de sucesso
		montarPaginaSucesso(httpServletRequest, "Gerado o Arquivo EFD-PIS/Cofins.",
				"Gerar Outro Arquivo EFD-PIS/Cofins", "/exibirGerarArquivoEfdPisConfinsAction.do");

		return retorno;
	}
}
