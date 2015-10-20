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
package gcom.gui.micromedicao;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.micromedicao.ArquivoTextoLigacoesHidrometroHelper;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0763] Gerar Arquivo Texto de Ligacoes com Hidrometro - CAERN 
 * @author Tiago Moreno
 * @date 10/04/2008
 * 
 * @param ArquivoTextoLigacoesHidrometroHelper
 * 
 * @return
 * @throws ControladorException
 */

public class GerarArquivoTextoLigacoesHidrometroAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		// Form
		GerarArquivoTextoLigacoesHidrometroActionForm form = 
			(GerarArquivoTextoLigacoesHidrometroActionForm) actionForm;
		
		ArquivoTextoLigacoesHidrometroHelper objetoArquivoTexto = 
			new ArquivoTextoLigacoesHidrometroHelper();
		
		// Verifica se pelo menos 1 campo foi preenchido
		boolean peloMenosUm = false;
		
		// Ger�ncia Regional
		if (form.getGerenciaRegional() != null && 
			!form.getGerenciaRegional().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
			
			objetoArquivoTexto.setGerenciaRegional(new Integer(form.getGerenciaRegional()));
			peloMenosUm = true;
		}
		
		// Unidade de Negocio
		if (form.getUnidadeNegocio() != null && 
			!form.getUnidadeNegocio().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
			
			objetoArquivoTexto.setUnidadeNegocio(new Integer(form.getUnidadeNegocio()));
			peloMenosUm = true;
		}
			
		// Localidade Inicial
		if (form.getLocalidadeInicial() != null && 
			!form.getLocalidadeInicial().equals("") ) {
				
			objetoArquivoTexto.setLocalidadeInicial(new Integer(form.getLocalidadeInicial()));
			peloMenosUm = true;
		}
		
		// Setor Comercial Inicial
		if (form.getSetorComercialInicial() != null && 
			!form.getSetorComercialInicial().equals("") ) {
				
			objetoArquivoTexto.setSetorComercialInicial(new Integer(form.getSetorComercialInicial()));
			peloMenosUm = true;
		}

		// Rota Inicial
		if (form.getRotaInicial() != null && 
			!form.getRotaInicial().equals("") ) {
				
			objetoArquivoTexto.setRotaInicial(new Integer(form.getRotaInicial()));
			peloMenosUm = true;
		}

		// Sequencial Rota Inicial
		if (form.getSequencialRotaInicial() != null && 
			!form.getSequencialRotaInicial().equals("") ) {
				
			objetoArquivoTexto.setSequencialRotalInicial(new Integer(form.getSequencialRotaInicial()));
			peloMenosUm = true;
		}

		// Localidade Final
		if (form.getLocalidadeFinal() != null && 
			!form.getLocalidadeFinal().equals("") ) {
				
			objetoArquivoTexto.setLocalidadeFinal(new Integer(form.getLocalidadeFinal()));
			peloMenosUm = true;
		}

		// Setor Comercial Final
		if (form.getSetorComercialFinal() != null && 
			!form.getSetorComercialFinal().equals("") ) {
				
			objetoArquivoTexto.setSetorComercialFinal(new Integer(form.getSetorComercialFinal()));
			peloMenosUm = true;
		}
		
		// Rota Final
		if (form.getRotaFinal() != null && 
			!form.getRotaFinal().equals("") ) {
				
			objetoArquivoTexto.setRotaFinal(new Integer(form.getRotaFinal()));
			peloMenosUm = true;
		}
		
		// Sequencial Rota Final
		if (form.getSequencialRotaFinal() != null && 
			!form.getSequencialRotaFinal().equals("") ) {
				
			objetoArquivoTexto.setSequencialRotalFinal(new Integer(form.getSequencialRotaFinal()));
			peloMenosUm = true;
		}			
		
		//verifica se pelo menos 1 campo foi preenchido no formul�rio
		if (!peloMenosUm){
			throw new ActionServletException(
				"atencao.filtro.nenhum_parametro_informado");
		}
		
		//chama o controlador para gerar o arquivo texto
		Fachada.getInstancia().gerarArquivoTextoLigacoesHidrometro(objetoArquivoTexto);
		
		montarPaginaSucesso(httpServletRequest, "Arquivo de Texto de Ligacoes com Hidrometro gerado com sucesso.",
				"Gerar outro Arquivo de Texto de Ligacoes com Hidrometro",
				"exibirGerarArquivoTextoLigacoesHidrometroAction.do?menu=sim" );
		

		return retorno;
	}
	
}