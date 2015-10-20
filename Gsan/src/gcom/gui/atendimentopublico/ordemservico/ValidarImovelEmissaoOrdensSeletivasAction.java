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
* Ivan S�rgio Virginio da Silva J�nior
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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

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
 * @author Ivan S�rgio
 */
public class ValidarImovelEmissaoOrdensSeletivasAction extends GcomAction {

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
	 *            Description of the Parameter*
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = null;
		
		ImovelEmissaoOrdensSeletivasActionForm form = 
			(ImovelEmissaoOrdensSeletivasActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		if(form.getTipoRelatorio()!=null){
			if(form.getTipoRelatorio().equals("1")){
				sessao.setAttribute("tipoRelatorio", "SINTETICO");
			}else if(form.getTipoRelatorio().equals("2")){
				sessao.setAttribute("tipoRelatorio", "ANALITICO");
			}else{
				sessao.removeAttribute("tipoRelatorio");
			}
		}

		// [FS0006] - Validar Ano/Mes de Instalacao
		if (form.getMesAnoInstalacaoInicial() != null &&
				!form.getMesAnoInstalacaoInicial().trim().equals("")
				&& form.getMesAnoInstalacaoInicial().contains("/")) {
			Integer anoMesAtual = Util.converterStringParaInteger(
					Util.getAnoMesComoString(new Date()).replace("/", ""));
			
			Integer anoMesInstalacaoInicial = Util.converterStringParaInteger(
					Util.formatarMesAnoParaAnoMes(
							form.getMesAnoInstalacaoInicial().replace("/", "")));
			
			if (anoMesInstalacaoInicial > anoMesAtual) {
				throw new ActionServletException("atencao.mes_ano_instalacao_invalido", null, "");
			}
		}
		
		if (form.getMesAnoInstalacaoFinal() != null &&
				!form.getMesAnoInstalacaoFinal().trim().equals("")
				&& form.getMesAnoInstalacaoFinal().contains("/")) {
			Integer anoMesAtual = Util.converterStringParaInteger(
					Util.getAnoMesComoString(new Date()).replace("/", ""));
			
			Integer anoMesInstalacaoFinal = Util.converterStringParaInteger(
					Util.formatarMesAnoParaAnoMes(
							form.getMesAnoInstalacaoFinal().replace("/", "")));
			
			if (form.getMesAnoInstalacaoInicial() != null &&
					!form.getMesAnoInstalacaoInicial().trim().equals("")
					&& form.getMesAnoInstalacaoInicial().contains("/")){
				
				Integer anoMesInstalacaoInicial = Util.converterStringParaInteger(
						Util.formatarMesAnoParaAnoMes(
								form.getMesAnoInstalacaoInicial().replace("/", "")));
				
				if (anoMesInstalacaoFinal < anoMesInstalacaoInicial){
					
					throw new ActionServletException("atencao.mes_ano_final_instalacao_menor_mes_ano_inicial_instalacao", null, "");
					
				}
				
			}
			
			if (anoMesInstalacaoFinal > anoMesAtual) {
				throw new ActionServletException("atencao.mes_ano_instalacao_invalido", null, "");
			}
		}
		
		if(form.getTipoOrdem() == null || form.getTipoOrdem().equalsIgnoreCase("")
				|| form.getTipoOrdem().equalsIgnoreCase("-1")){
			throw new ActionServletException("atencao.campo.informado", null, "Tipo da Ordem");
		}
		if(form.getSugestao() != null && form.getSugestao().equals("2")){
			if(form.getDescricaoComando() == null || form.getDescricaoComando().equals("")){
				throw new ActionServletException("atencao.campo.informado", null, "Descri��o Comando");
			}
		}
		
		/**
		 * Tipo da Ordem: Caso o usuario selecione a opcao INSTALACAO inibir todo os
		 * 				  os campos da Aba Hidrometro;
		 */
		if (form.getTipoOrdem() != null) {
			
			if(form.getTipoOrdem().equalsIgnoreCase(ImovelEmissaoOrdensSeletivasActionForm.TIPO_ORDEM_INSTALACAO)){
				
				form.limparCamposHidrometro();
				
			}else if(form.getTipoOrdem().equalsIgnoreCase(ImovelEmissaoOrdensSeletivasActionForm.TIPO_INSPECAO_ANORMALIDADE)){
			
				if(form.getIdImovel() == null ||form.getIdImovel().equals("")){
					String concluir = httpServletRequest.getParameter("concluir");
					
					
					if(form.getAnormalidadeHidrometro() == null){
						if(concluir != null){
//							1.3.3.Anormalidade de Leitura(obrigat�rio caso o tipo 
							//de ordem selecionado corresponda a �INSPE��O DE ANORMALIDADE�
							throw new ActionServletException("atencao.campo.informado", null, "Anormalidade de Leitura");
						}else if(sessao.getAttribute("collectionHidrometroAnormalidade") != null){
//							1.3.3.Anormalidade de Leitura(obrigat�rio caso o tipo 
							//de ordem selecionado corresponda a �INSPE��O DE ANORMALIDADE�
							throw new ActionServletException("atencao.campo.informado", null, "Anormalidade de Leitura");
						}
					}else if(form.getNumeroOcorrenciasConsecutivas() == null || form.getNumeroOcorrenciasConsecutivas().equals("")){
						throw new ActionServletException("atencao.campo.informado", null, "Num. Ocorr�ncias Consecutivas");
					}
					
				}
				
			}
		}
		
		return retorno;
	}
}