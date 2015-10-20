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
package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.bean.RegistroAtendimentoDevolucaoValoresHelper;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarRegistroAtendimentoDevolucaoValoresAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("manterRegistroAtendimentoDevolucaoValores");

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarRegistroAtendimentoDevolucaoValoresActionForm filtrarGuiaDevolucaoActionForm = (FiltrarRegistroAtendimentoDevolucaoValoresActionForm) actionForm;

		// Recupera os par�metros do form
		String[] idPerfilImovel = filtrarGuiaDevolucaoActionForm.getPerfilImovel();
		String dataAtendimentoInicio = filtrarGuiaDevolucaoActionForm.getDataAtendimentoInicio();
		String dataAtendimentoFim = filtrarGuiaDevolucaoActionForm.getDataAtendimentoFim();

		boolean peloMenosUmParametroInformado = false;
		Date dataAtendimentoInicioFormatada = null;
		Date dataAtendimentoFimFormatada = null;
		// Per�odo Data Emiss�o
		if ((dataAtendimentoInicio != null && !dataAtendimentoInicio.equals(""))
			|| (dataAtendimentoFim != null && !dataAtendimentoFim.equals(""))) {
			
			peloMenosUmParametroInformado = true;

			if (dataAtendimentoInicio != null && !dataAtendimentoInicio.trim().equals("")) {
				dataAtendimentoInicioFormatada = Util.converteStringParaDate(dataAtendimentoInicio);
			}

			if (dataAtendimentoFim != null && !dataAtendimentoFim.trim().equals("")) {
				dataAtendimentoFimFormatada = Util.converteStringParaDate(dataAtendimentoFim);
			}

			if(dataAtendimentoInicioFormatada != null && dataAtendimentoFimFormatada != null &&
					dataAtendimentoInicioFormatada.compareTo(dataAtendimentoFimFormatada) == 1){
				throw new ActionServletException("atencao.data.intervalo.invalido");
			}	

		}

		Integer numeroRA = null;
		if(filtrarGuiaDevolucaoActionForm.getNumeroRA() != null && !filtrarGuiaDevolucaoActionForm.getNumeroRA().equals("")){
			numeroRA = new Integer(filtrarGuiaDevolucaoActionForm.getNumeroRA());
			FiltroRegistroAtendimento filtroRegistroAtendimento = new FiltroRegistroAtendimento();
			filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.ID, numeroRA));
			filtroRegistroAtendimento.adicionarCaminhoParaCarregamentoEntidade(FiltroRegistroAtendimento.SOLICITACAO_TIPO_ESPECIFIC);
			
			Collection colecaoRegistroAtendimento = fachada
			.pesquisar(filtroRegistroAtendimento,RegistroAtendimento.class.getName());
			
			RegistroAtendimento registroAtendimento = (RegistroAtendimento) Util.retonarObjetoDeColecao(colecaoRegistroAtendimento);

			if(registroAtendimento == null){
				//[FS0003] - Verificar exist�ncia do Registro de atendimento
				//Caso o registro de atendimento informado n�o exista na tabela REGISTRO_ATENDIMENTO, 
				//exibir a mensagem �Registro de atendimento inexistente�
				throw new ActionServletException("atencao.pesquisa_inexistente", null,"Registro de Atendimento");
			}
		
			if(registroAtendimento.getCodigoSituacao() != RegistroAtendimento.SITUACAO_PENDENTE.shortValue()){
				//[FS0004] - Verificar situa��o do ra informado
				//Caso a situa��o do ra informado diferente de �PENDENTE� na tabela REGISTRO_ATENDIMENTO, 
				//exibir a mensagem �Situa��o do registro de atendimento deve ser pendente para devolu��o de pagamentos�
				throw new ActionServletException("atencao.situacao.ra.deve.ser.pendente");
			}
			
			if(registroAtendimento.getSolicitacaoTipoEspecificacao() != null && 
				registroAtendimento.getSolicitacaoTipoEspecificacao().getIndicadorInformarPagamentoDuplicidade().equals(ConstantesSistema.NAO)){
				//[FS0005] - Verificar tipo do ra informado
				//Caso o indicador  (IC_INFORMAPAGTODUPLICIDADE) = 2 da tabela SOLICITACAO_TIPO_ESPEC 
				//com STEP_ID = STEP_ID da tabela REGISTRO_ATENDIMENTO com o RGAT_ID do ra informado) 
				//exibir a mensagem �Registro de Atendimento n�o corresponde a uma solicita��o de devolu��o de pagamento�
				
				throw new ActionServletException("atencao.ra.nao.corresponde.devolucao.pagamento");
			}
			peloMenosUmParametroInformado = true;	
		}
		
		Integer idImovel = null;
		if (filtrarGuiaDevolucaoActionForm.getIdImovelHidden() != null && !filtrarGuiaDevolucaoActionForm.getIdImovelHidden().equals("")) {
			idImovel = new Integer(filtrarGuiaDevolucaoActionForm.getIdImovelHidden());
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel));

			Collection imoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());
			
			if(imoveis == null || imoveis.isEmpty()){
				throw new ActionServletException("atencao.pesquisa_inexistente", null,"Im�vel");
			}
			peloMenosUmParametroInformado = true;
		}	
		
		if (idPerfilImovel != null) {
			peloMenosUmParametroInformado = true;
		}
		
		// Erro caso o usu�rio mandou filtrar sem nenhum par�metro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}
		
		RegistroAtendimentoDevolucaoValoresHelper helper = new RegistroAtendimentoDevolucaoValoresHelper();
		helper.setIdImovel(idImovel);
		helper.setIdPerfilImovel(idPerfilImovel);
		helper.setDataAtendimentoInicioFormatada(dataAtendimentoInicioFormatada);
		helper.setDataAtendimentoFimFormatada(dataAtendimentoFimFormatada);
		helper.setNumeroRA(numeroRA);
		
		sessao.setAttribute("registroAtendimentoDevolucaoValoresHelper",helper);

		// Devolve o mapeamento de retorno
		return retorno;
	}
}
