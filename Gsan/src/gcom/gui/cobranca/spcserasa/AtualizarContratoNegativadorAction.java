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
* Yara Taciane de Souza
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
package gcom.gui.cobranca.spcserasa;

import gcom.arrecadacao.ContratoMotivoCancelamento;
import gcom.cobranca.NegativadorContrato;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.spcserasa.FiltroNegativadorContrato;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action form de manter Contrato do Negativador
 * 
 * @author Yara Taciane 
 * @created 26/12/2007
 */
public class AtualizarContratoNegativadorAction extends GcomAction {
	/**
	 * @author Yara Taciane
	 * @date 26/12/2007
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		AtualizarContratoNegativadorActionForm atualizarContratoNegativadorActionForm = (AtualizarContratoNegativadorActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		// ------------ REGISTRAR TRANSA��O ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_ATUALIZAR_CONTRATO_NEGATIVADOR,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_ATUALIZAR_CONTRATO_NEGATIVADOR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		// ------------ REGISTRAR TRANSA��O ----------------

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a

		NegativadorContrato negativadorContrato = (NegativadorContrato) sessao.getAttribute("negativadorContrato");

		// Pegando os dados do Formul�rio
		
		//..............................................................................................................
		
		String numeroContrato = atualizarContratoNegativadorActionForm.getNumeroContrato();	
		
		
		FiltroNegativadorContrato filtroNegativadorContrato = new FiltroNegativadorContrato();
		
		filtroNegativadorContrato.adicionarParametro(new ParametroSimples(
				FiltroNegativadorContrato.NUMERO_CONTRATO, numeroContrato));		
	

		Collection<NegativadorContrato> collectionNegativadorContrato = fachada
				.pesquisar(filtroNegativadorContrato, NegativadorContrato.class.getName());

		// Caso a pesquisa tenha retornado o NegativadorContrato
		if (collectionNegativadorContrato != null && !collectionNegativadorContrato.isEmpty()) {		
			boolean flag = true;			
			Iterator it = collectionNegativadorContrato.iterator();
			while(it.hasNext()){
				NegativadorContrato negativadorContrat = (NegativadorContrato) it.next();				
				if(negativadorContrat.getId().equals(negativadorContrato.getId())){
					flag = false;
				}				
			}
			
			if(flag==true){
				throw new ActionServletException("atencao.negativador_associado_numero_contrato");
			}
					
		}
		
		//................................................................................................................
		
		String descricaoEmailEnvioArquivo = atualizarContratoNegativadorActionForm.getDescricaoEmailEnvioArquivo();
		String codigoConvenio = atualizarContratoNegativadorActionForm.getCodigoConvenio();
		String valorContrato = atualizarContratoNegativadorActionForm.getValorContrato();
		String valorTarifaInclusao = atualizarContratoNegativadorActionForm.getValorTarifaInclusao();
		String numeroPrazoInclusao = atualizarContratoNegativadorActionForm.getNumeroPrazoInclusao();
		Short indicadorObriControSequeRetorno = atualizarContratoNegativadorActionForm.getIndicadorObriControSequeRetorno();
		
		//.................................................................Validar Datas
		
		Date dataContratoInicio = null;
		if (atualizarContratoNegativadorActionForm.getDataContratoInicio() != null
				&& !atualizarContratoNegativadorActionForm.getDataContratoInicio().equals("")) {
			
			String dataContrato = atualizarContratoNegativadorActionForm.getDataContratoInicio();
			if (Util.validarDiaMesAno(dataContrato)) {
				throw new ActionServletException(
						"atencao.data.inicio.Contrato.invalida");
			}

			dataContratoInicio = Util.converteStringParaDate(dataContrato);
			
			Date dataAtualSemHora = Util.formatarDataSemHora(new Date());
			if(Util.compararData(dataContratoInicio, dataAtualSemHora) == - 1){
				String dataAtual = Util.formatarData(new Date());
				throw new ActionServletException(
						"atencao.data.inicio.nao.superior.data.corrente", null,
						dataAtual);
			}			

		} else {
			throw new ActionServletException("atencao.required", null,
					"Data de In�cio do Contrato");
		}
	
		//...................................................................................		
		
		
		Date dataContratoFim = null;
		if (atualizarContratoNegativadorActionForm.getDataContratoFim() != null
				&& !atualizarContratoNegativadorActionForm.getDataContratoFim().equals("")) {
			
			String dataContrato = atualizarContratoNegativadorActionForm.getDataContratoFim();
			if (Util.validarDiaMesAno(dataContrato)) {
				throw new ActionServletException(
						"atencao.data.fim.Contrato.invalida");
			}

			dataContratoFim = Util.converteStringParaDate(dataContrato);
			
			
			if (dataContratoInicio.after(dataContratoFim)) {
				String dataInicio = Util.formatarData(dataContratoInicio);
				throw new ActionServletException(
						"atencao.data.inicio.nao.superior.data.corrente", null,dataInicio);
			}
				
		} else {
			throw new ActionServletException("atencao.required", null,
					"Data do Fim do Contrato");
		}
		
		
		//....................................................................................... voltar ****		
		
		Date dataContratoEncerramento = null;
		if (atualizarContratoNegativadorActionForm.getDataContratoEncerramento() != null
				&& !atualizarContratoNegativadorActionForm.getDataContratoEncerramento().equals("")) {
			
			String dataContrato = atualizarContratoNegativadorActionForm.getDataContratoEncerramento();
			if (Util.validarDiaMesAno(dataContrato)) {
				throw new ActionServletException(
						"atencao.data.fim.Contrato.invalida");
			}

			dataContratoEncerramento = Util.converteStringParaDate(dataContrato);			
			
			if (dataContratoInicio.after(dataContratoEncerramento)) {
				String dataInicio = Util.formatarData(dataContratoInicio);
				throw new ActionServletException(
						"atencao.data.encerramento.intervalo.invalido", null,dataInicio);
			}
			
		}	

		//....................................................................................................
		
		String idContratoMotivoCancelamento = atualizarContratoNegativadorActionForm.getIdContratoMotivoCancelamento();
		
		if(dataContratoEncerramento != null && idContratoMotivoCancelamento.equals("-1")){			
			throw new ActionServletException("atencao.required", null,"Informe o Motivo do Cancelamento");			
			
		}
		
		if(dataContratoEncerramento == null && !idContratoMotivoCancelamento.equals("-1")){
			throw new ActionServletException("atencao.required", null,"Informe a Data de Encerramento");	
			
		}
		
		
		//...................................................................................................
		
		Long time = Long.parseLong(atualizarContratoNegativadorActionForm.getTime()); 
		
		
		
		// Seta os campos para serem atualizados		
		
		if(numeroContrato != null){
			negativadorContrato.setNumeroContrato(numeroContrato);
		}	
		
		if(atualizarContratoNegativadorActionForm.getNumeroEntidade() != null && !atualizarContratoNegativadorActionForm.getNumeroEntidade().equals("")){
			negativadorContrato.setNumeroEntidade(new Integer(atualizarContratoNegativadorActionForm.getNumeroEntidade()));
		}	
		
		if(atualizarContratoNegativadorActionForm.getNumeroAssociado() != null && !atualizarContratoNegativadorActionForm.getNumeroAssociado().equals("")){
			negativadorContrato.setNumeroAssociado(new Integer(atualizarContratoNegativadorActionForm.getNumeroAssociado()));
		}		
		
		if(descricaoEmailEnvioArquivo != null){
			negativadorContrato.setDescricaoEmailEnvioArquivo(descricaoEmailEnvioArquivo);	
		}
		if(codigoConvenio != null){
			negativadorContrato.setCodigoConvenio(codigoConvenio);
		}		
		if(valorContrato != null){
			negativadorContrato.setValorContrato(new BigDecimal(valorContrato));
		}		
		if(valorTarifaInclusao != null){
			negativadorContrato.setValorTarifaInclusao(new BigDecimal(valorTarifaInclusao));
		}		
		if(numeroPrazoInclusao != null){
			negativadorContrato.setNumeroPrazoInclusao(Short.parseShort(numeroPrazoInclusao));	
		}		
		if(dataContratoInicio != null){
			negativadorContrato.setDataContratoInicio(dataContratoInicio);
		}		
		if(dataContratoFim != null){
			negativadorContrato.setDataContratoFim(dataContratoFim);
		}		
		if(dataContratoEncerramento != null){
			negativadorContrato.setDataContratoEncerramento(dataContratoEncerramento);	
		}			
		if(idContratoMotivoCancelamento!= null && !idContratoMotivoCancelamento.equals("-1")){
		ContratoMotivoCancelamento contratoMotivoCancelamento = new ContratoMotivoCancelamento();
		contratoMotivoCancelamento.setId(new Integer(idContratoMotivoCancelamento));
		negativadorContrato.setContratoMotivoCancelamento(contratoMotivoCancelamento);

		}
		if(indicadorObriControSequeRetorno != null){
			negativadorContrato.setIndicadorObriControSequeRetorno(indicadorObriControSequeRetorno);
		}
		
		//Check para atualiza��o realizada por outro usu�rio 
		FiltroNegativadorContrato filtroNegatContrato = new FiltroNegativadorContrato(); 
		filtroNegatContrato.adicionarParametro(new ParametroSimples(FiltroNegativadorContrato.ID, negativadorContrato.getId()));
		
		Collection collNegativadorContrato = Fachada.getInstancia().pesquisar(filtroNegatContrato, NegativadorContrato.class.getName());
		
		NegativadorContrato negatContrato = (NegativadorContrato)collNegativadorContrato.iterator().next();

		if (negatContrato.getUltimaAlteracao().getTime() != time){
			throw new ActionServletException("atencao.registro_remocao_nao_existente");
		}
		// ------------ REGISTRAR TRANSA��O ----------------
		negativadorContrato.setOperacaoEfetuada(operacaoEfetuada);
		negativadorContrato.adicionarUsuario(usuario,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(negativadorContrato);
		// ------------ REGISTRAR TRANSA��O ----------------

			
		negativadorContrato.setUltimaAlteracao(new Date());
		
		// Atualiza o negativadorContrato 
		fachada.atualizar(negativadorContrato);
		
		montarPaginaSucesso(httpServletRequest, "Contrato do Negativador de c�digo "
				+ negativadorContrato.getId() + " atualizado com sucesso.",
				"Realizar outra Manuten��o de Contrato do Negativador",
				"exibirFiltrarContratoNegativadorAction.do?desfazer=S");
	
		return retorno;
	}
    
	
    
   
}
 