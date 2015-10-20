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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.ServicoCobrancaValor;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.Util;

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
 * Atualiza��o da vig�ncia do Valor de Cobran�a do Servi�o
 * 
 * @author Josenildo Neves - Hugo Leonardo
 * @date 04/02/2010 - 03/05/2010
 */
public class ReplicarValorCobrancaServicoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Forward
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Form
		ReplicarValorCobrancaServicoActionForm replicarCobrancaServicoActionForm = (ReplicarValorCobrancaServicoActionForm) actionForm;

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
				
		Collection<ServicoCobrancaValor> collServidorValorCobranca = this.setCollServicoCobrancaValor(replicarCobrancaServicoActionForm);
		
		for (ServicoCobrancaValor servicoCobrancaValor : collServidorValorCobranca) {
			
			// FS0008 - Verificar pr�-exist�ncia de vig�ncia para o d�bito tipo
			String dataVigenciaInicial = Util.formatarData(servicoCobrancaValor.getDataVigenciaInicial());
			String dataVigenciaFinal = Util.formatarData(servicoCobrancaValor.getDataVigenciaFinal());
			Integer idServicoTipo = servicoCobrancaValor.getServicoTipo().getId();
			
			Fachada.getInstancia().verificarExistenciaVigenciaServicoTipo(dataVigenciaInicial, 
					dataVigenciaFinal, idServicoTipo, new Integer("2"));
		}
		
		for (ServicoCobrancaValor servicoCobrancaValor : collServidorValorCobranca) {
			
			// ------------ REGISTRAR TRANSA��O ----------------
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_VALOR_COBRANCA_SERVICO_INSERIR, servicoCobrancaValor.getServicoTipo().getId(),
					servicoCobrancaValor.getServicoTipo().getId(), new UsuarioAcaoUsuarioHelper(usuarioLogado,
					UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
			// ------------ REGISTRAR TRANSA��O ----------------
			
			registradorOperacao.registrarOperacao(servicoCobrancaValor);
			
			Fachada.getInstancia().inserirValorCobrancaServico(servicoCobrancaValor);
			
		}		

		// [FS0003 - Verificar sucesso da transa��o].
		montarPaginaSucesso(httpServletRequest, "Todas as vig�ncias foram atualizadas com sucesso.",
				"Replicar Valor de Cobran�a do Servi�o",
				"exibirReplicarValorCobrancaServicoAction.do?menu=sim");
		return retorno;
	}

	/**
	 * Atualiza��o da vig�ncia e do valor na cole��o de Valor de Cobran�a do Servi�o
	 * 
	 * @author Josenildo Neves
	 * @date 04/02/2010
	 * 
	 * @param form
	 * @return servicoCobrancaValor
	 */
	
	private Collection<ServicoCobrancaValor> setCollServicoCobrancaValor(
			ReplicarValorCobrancaServicoActionForm form) {
		
		
		Collection<ServicoCobrancaValor> collServicoCobrancaValor = Fachada.getInstancia().replicarServicoCobrancaValorUltimaVigencia(form.getIdRegistrosSelecionados());
		
		for (ServicoCobrancaValor servicoCobrancaValor : collServicoCobrancaValor) {
			

			servicoCobrancaValor.setDataVigenciaInicial(Util.converteStringParaDate(form.getNovaDataVigenciaInicial()));
			
			servicoCobrancaValor.setDataVigenciaFinal(Util.converteStringParaDate(form.getNovaDataVigenciaFinal()));

			if(!form.getIndiceParaCorrecao().replace(",",".").equals("00.0000")){
				
				BigDecimal novoValor = null;
				BigDecimal indice = new BigDecimal(form.getIndiceParaCorrecao().replace(",","."));
				
				//divide por Cem pois o valor informado na tela � em percentagem "%".
				indice = indice.divide(new BigDecimal(100));

				novoValor = indice.multiply(servicoCobrancaValor.getValor());
				
				novoValor = novoValor.add(servicoCobrancaValor.getValor());
				
				servicoCobrancaValor.setValor(novoValor);
				
				servicoCobrancaValor.setUltimaAlteracao(new Date());

			}else{
				throw new ActionServletException("atencao.valor.�ndice.informado.igual.zero");
			}
		}
		
		return collServicoCobrancaValor;
	}

}