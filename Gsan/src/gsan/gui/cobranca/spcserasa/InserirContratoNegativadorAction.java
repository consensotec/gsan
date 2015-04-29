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
package gsan.gui.cobranca.spcserasa;

import gsan.cobranca.Negativador;
import gsan.cobranca.NegativadorContrato;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.interceptor.RegistradorOperacao;
import gsan.seguranca.acesso.Operacao;
import gsan.seguranca.acesso.OperacaoEfetuada;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.seguranca.acesso.usuario.UsuarioAcao;
import gsan.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gsan.spcserasa.FiltroNegativadorContrato;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

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
 * Processamento para inserir o contrato do negativador
 * 
 * 
 * @author Yara Taciane de Souza
 * @date 18/12/2007
 */
public class InserirContratoNegativadorAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirContratoNegativadorActionForm inserirContratoNegativadorActionForm = (InserirContratoNegativadorActionForm) actionForm;

		Short indicadorObriControSequeRetorno = inserirContratoNegativadorActionForm.getIndicadorObriControSequeRetorno();
		
		HttpSession sessao = httpServletRequest.getSession(false);
			
		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// ------------ REGISTRAR TRANSA��O ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_INSERIR_CONTRATO_NEGATIVADOR,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_INSERIR_CONTRATO_NEGATIVADOR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		// ------------ REGISTRAR TRANSA��O ----------------
			
	
		Fachada fachada = Fachada.getInstancia();
		Integer idNegativadorContrato = null;
		Collection collNegativadorContrato = null;
		
		
		// cria o objeto negativador contrato para ser inserido
		NegativadorContrato negativadorContrato = new NegativadorContrato();
		
		
		if (inserirContratoNegativadorActionForm.getIdNegativador() != null	&& !inserirContratoNegativadorActionForm.getIdNegativador().equals("")) {			
			Negativador negativador = new Negativador();
			negativador.setId(new Integer(inserirContratoNegativadorActionForm.getIdNegativador()));			
			negativadorContrato.setNegativador(negativador);
			
		} else {
			throw new ActionServletException("atencao.required", null,"Negativador");
		}
			
		String idNegativador = (String)inserirContratoNegativadorActionForm.getIdNegativador();
		
		if (inserirContratoNegativadorActionForm.getDescricaoEmailEnvioArquivo() != null
				&& !inserirContratoNegativadorActionForm.getDescricaoEmailEnvioArquivo().equals("")) {			
			String descricaoEmailEnvioArquivo = inserirContratoNegativadorActionForm.getDescricaoEmailEnvioArquivo();			
			negativadorContrato.setDescricaoEmailEnvioArquivo(descricaoEmailEnvioArquivo);		
		
		} 
		
		if (inserirContratoNegativadorActionForm.getCodigoConvenio() != null
				&& !inserirContratoNegativadorActionForm.getCodigoConvenio().equals("")) {		
			
			String codigoConvenio = inserirContratoNegativadorActionForm.getCodigoConvenio();			
			negativadorContrato.setCodigoConvenio(codigoConvenio);		
		
		}
		
		if (inserirContratoNegativadorActionForm.getValorContrato() != null
				&& !inserirContratoNegativadorActionForm.getValorContrato()
						.equals("")) {
			
			BigDecimal valorContrato = new BigDecimal(
					inserirContratoNegativadorActionForm.getValorContrato().replace(".", "").replace(",", "."));
			negativadorContrato.setValorContrato(valorContrato);
		} else {
			throw new ActionServletException("atencao.required", null,
					"Valor do Contrato");
		}
		
		if (inserirContratoNegativadorActionForm.getNumeroEntidade() != null 
				&& !inserirContratoNegativadorActionForm.getNumeroEntidade().equals("")){
			
			negativadorContrato.setNumeroEntidade(new Integer(inserirContratoNegativadorActionForm.getNumeroEntidade()));
		} else {
			throw new ActionServletException("atencao.required", null, "N�mero da Entidade");
		}
		
		if (inserirContratoNegativadorActionForm.getNumeroAssociado() != null 
				&& !inserirContratoNegativadorActionForm.getNumeroAssociado().equals("")){
			
			negativadorContrato.setNumeroAssociado(new Integer(inserirContratoNegativadorActionForm.getNumeroAssociado()));
		} else {
			throw new ActionServletException("atencao.required", null, "N�mero do Associado");
		}
		
		if (inserirContratoNegativadorActionForm.getValorTarifaInclusao() != null
				&& !inserirContratoNegativadorActionForm.getValorTarifaInclusao()
						.equals("")) {
			
			BigDecimal valorTarifaInclusao = new BigDecimal(
					inserirContratoNegativadorActionForm.getValorTarifaInclusao().replace(".", "").replace(",", "."));
			negativadorContrato.setValorTarifaInclusao(valorTarifaInclusao);
		} else {
			throw new ActionServletException("atencao.required", null,
					"Valor do Tarifa para Inclus�o");
		}
		
		if (inserirContratoNegativadorActionForm.getNumeroPrazoInclusao() != null
				&& !inserirContratoNegativadorActionForm.getNumeroPrazoInclusao()
						.equals("")) {
			
			short numeroPrazoInclusao = Short.parseShort(inserirContratoNegativadorActionForm.getNumeroPrazoInclusao());
			
			negativadorContrato.setNumeroPrazoInclusao(numeroPrazoInclusao);
		} else {
			throw new ActionServletException("atencao.required", null,
					"Prazo Para Negativa��o");
		}
		
		
		
		// Data Fim anterior a Data In�cio

		Date dataContratoInicio = null;
		if (inserirContratoNegativadorActionForm.getDataContratoInicio() != null
				&& !inserirContratoNegativadorActionForm.getDataContratoInicio().equals("")) {
			
			String dataContrato = inserirContratoNegativadorActionForm.getDataContratoInicio();
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
			
			negativadorContrato.setDataContratoInicio(dataContratoInicio);
			
		} else {
			throw new ActionServletException("atencao.required", null,
					"Data de In�cio do Contrato");
		}
		
		
		Date dataContratoFim = null;
		if (inserirContratoNegativadorActionForm.getDataContratoFim() != null
				&& !inserirContratoNegativadorActionForm.getDataContratoFim().equals("")) {
			
			String dataContrato = inserirContratoNegativadorActionForm.getDataContratoFim();
			if (Util.validarDiaMesAno(dataContrato)) {
				throw new ActionServletException(
						"atencao.data.fim.Contrato.invalida");
			}

			dataContratoFim = Util.converteStringParaDate(dataContrato);
			
			//Se data inicio maior que data fim
			if(Util.compararData(dataContratoInicio, dataContratoFim) == 1){
				String dataInicio = Util.formatarData(dataContratoInicio);
				String dataFim = Util.formatarData(dataContratoFim);
				throw new ActionServletException(
						"atencao.data_inicial_maior_data_final",dataInicio,dataFim);
			}
			
			negativadorContrato.setDataContratoFim(dataContratoFim);	
			
		} else {
			throw new ActionServletException("atencao.required", null,
					"Data do Fim do Contrato");
		}
		
		
		//IndicadorObriControSequeRetorno		
		
		if(indicadorObriControSequeRetorno != null && 
			!indicadorObriControSequeRetorno.equals("")){
			
			negativadorContrato.setIndicadorObriControSequeRetorno(indicadorObriControSequeRetorno);
		} else {
			throw new ActionServletException("atencao.required", null,
			"Indicador de Obrigatoriedade do Controle de Sequencial de 	Retorno");
		}
		
	    FiltroNegativadorContrato filtroNegativadorContrato = new FiltroNegativadorContrato();
		
	    //Verificar exist�ncia do n�mero do contrato		
		if (inserirContratoNegativadorActionForm.getNumeroContrato() != null
				&& !inserirContratoNegativadorActionForm.getNumeroContrato().equals("")) {		
			
			String numeroContrato = inserirContratoNegativadorActionForm.getNumeroContrato();	
			
			filtroNegativadorContrato.limparListaParametros();			
			filtroNegativadorContrato.adicionarParametro(new ParametroSimples(FiltroNegativadorContrato.NEGATIVADOR_ID, idNegativador));
			filtroNegativadorContrato.adicionarParametro(new ParametroSimples(FiltroNegativadorContrato.NUMERO_CONTRATO, numeroContrato));				
		
			collNegativadorContrato = null;
			
			collNegativadorContrato = fachada.pesquisar(filtroNegativadorContrato,
					NegativadorContrato.class.getName());			
			
			if(collNegativadorContrato != null && !collNegativadorContrato.isEmpty()){
				
				throw new ActionServletException("atencao.negativador_associado_numero_contrato");				
				
			}
			negativadorContrato.setNumeroContrato(numeroContrato);		
		
		} else {
			throw new ActionServletException("atencao.required", null,
					"numero  do contrato");
		}
		
		
		
		 // Verificar exist�ncia de contrato vigente para o negativador		
		 filtroNegativadorContrato.limparListaParametros();
         filtroNegativadorContrato.adicionarParametro(new ParametroSimples(FiltroNegativadorContrato.NEGATIVADOR_ID,idNegativador));
         collNegativadorContrato = fachada.pesquisar(filtroNegativadorContrato, NegativadorContrato.class.getName());
	     NegativadorContrato negativadorContratoBanco = (NegativadorContrato) Util.retonarObjetoDeColecao(collNegativadorContrato);		
		
		if(negativadorContratoBanco != null){
			Date dataEncerramento = negativadorContratoBanco.getDataContratoEncerramento();
			Date dataFimContrato = negativadorContratoBanco.getDataContratoFim();			
			Date dataCorrente = Util.formatarDataSemHora(new Date());
			
			if((Util.compararData(dataFimContrato, dataCorrente) == 0 || Util.compararData(dataFimContrato, dataCorrente) == 1) && dataEncerramento == null ){
				//Existe um contrato em vigencia
				throw new ActionServletException("atencao.contrato_negativador_vigente");
			}
		}			
		
			
		//------------ REGISTRAR TRANSA��O ----------------
		negativadorContrato.setOperacaoEfetuada(operacaoEfetuada);
		negativadorContrato.adicionarUsuario(usuario,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(negativadorContrato);
		// ------------ REGISTRAR TRANSA��O ----------------
		
		if (negativadorContrato != null) {			
			
			negativadorContrato.setUltimaAlteracao(new Date());
		
			
			idNegativadorContrato = (Integer) fachada.inserir(negativadorContrato);
		} else {
			throw new ActionServletException(
					"atencao.informar.linha.criterio.cobranca");
		}
		

		montarPaginaSucesso(httpServletRequest, "Contrato do Negativador "
				+ idNegativadorContrato + " inserido com sucesso.",
				"Inserir outro Contrato do Negativador",
				"exibirInserirContratoNegativadorAction.do?menu=sim",
				"exibirAtualizarContratoNegativadorAction.do?idRegistroAtualizacao="
				+ idNegativadorContrato, "Atualizar Contrato do Negativador Inserido");
		
				
			
		return retorno;
	}

}
