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
package gcom.gui.gerencial;

import gcom.gui.GcomAction;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 */
public class ExibirSelecionarRelatorioGerencialAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		Map<String, String> relatoriosCadastro = new HashMap<String, String>();
		relatoriosCadastro.put("Resumo Liga��es Economia",
				"ResumoLigacaoEconomia");
		relatoriosCadastro.put("Resumo Indicador Ligacao Economia",
		"ResumoIndicadorLigacaoEconomia");
		Map<String, String> relatoriosMicromedicao = new HashMap<String, String>();
		relatoriosMicromedicao.put("Resumo Anormalidades Consumo",
				"ResumoAnormalidadesConsumo");
		relatoriosMicromedicao.put("Resumo Consumo Agua",
				"ResumoConsumoAgua");

		relatoriosMicromedicao.put("Resumo Leitura Anormalidade",
		"ResumoLeituraAnormalidade");
		relatoriosMicromedicao.put("Resumo Hidrometro",
		"ResumoHidrometro");
		relatoriosMicromedicao.put("Resumo Instalacao Hidrometro",
		"ResumoInstalacaoHidrometro");
		relatoriosMicromedicao.put("Resumo Indicador Desempenho Micromedicao",
		"ResumoIndicadorDesempenhoMicromedicao");

		
		Map<String, String> relatoriosFaturamento = new HashMap<String, String>();
		relatoriosFaturamento.put("Resumo Situa��o Especial Faturamento",
				"ResumoSituacaoEspecialFaturamento");
		relatoriosFaturamento.put("Resumo Pend�ncia", "ResumoPendencia");
		Map<String, String> relatoriosCobranca = new HashMap<String, String>();
		relatoriosCobranca.put("Resumo Situa��o Especial Cobran�a",
				"ResumoSituacaoEspecialCobranca");
		Map<String, String> relatoriosArrecadacao = new HashMap<String, String>();
		Map<String, String> relatoriosAtendimentoPublico = new HashMap<String, String>();
		relatoriosCobranca.put("Resumo Parcelamento",
		"ResumoParcelamento");
		relatoriosFaturamento.put("Resumo Faturamento Outros",
		"ResumoFaturamentoOutros");
		relatoriosFaturamento.put("Resumo Faturamento Credito",
		"ResumoFaturamentoCredito");
		relatoriosFaturamento.put("Resumo Faturamento Agua Esgoto",
		"ResumoFaturamentoAguaEsgoto");
		relatoriosArrecadacao.put("Resumo Arrecadacao Outros",
		"ResumoArrecadacaoOutros");
		relatoriosArrecadacao.put("Resumo Arrecadacao Credito",
		"ResumoArrecadacaoCredito");
		relatoriosArrecadacao.put("Resumo Arrecadacao Agua Esgoto",
		"ResumoArrecadacaoAguaEsgoto");
		relatoriosAtendimentoPublico.put("Resumo Registro Atendimento",
		"ResumoRegistroAtendimento");
		
		httpServletRequest.setAttribute("relatoriosCadastro", relatoriosCadastro);
		httpServletRequest.setAttribute("relatoriosMicromedicao", relatoriosMicromedicao);
		httpServletRequest.setAttribute("relatoriosFaturamento", relatoriosFaturamento);
		httpServletRequest.setAttribute("relatoriosCobranca", relatoriosCobranca);
		httpServletRequest.setAttribute("relatoriosArrecadacao", relatoriosArrecadacao);
		httpServletRequest.setAttribute("relatoriosAtendimentoPublico", relatoriosAtendimentoPublico);

		
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("selecionarRelatorioGerencial");

		return retorno;

	}

}
