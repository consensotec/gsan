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
package gsan.gui.cobranca;

import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cobranca.CobrancaAcao;
import gsan.cobranca.ColunasTextoSMSEmail;
import gsan.cobranca.bean.CobrancaAcaoHelper;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesSistema;
import gsan.util.Util;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processamento para inserir o critério da cobrança e as linhas do criterio da
 * cobrança
 * 
 * @author Sávio Luiz
 * @date 18/09/2007
 */
public class InserirAcaoCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		AcaoCobrancaActionForm acaoCobrancaActionForm = (AcaoCobrancaActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);

		Fachada fachada = Fachada.getInstancia();
		Integer idCobrancaAcao = null;
	   
		AcaoCobrancaActionForm form = (AcaoCobrancaActionForm) actionForm;
		
		Collection<ColunasTextoSMSEmail> colecaoColunas = (Collection<ColunasTextoSMSEmail>)sessao.getAttribute("colecaoDadosTexto");
		String idSelecionado = form.getDadosTexto();
		String textoSMS = form.getTextoSMS();
		String textoEmail = form.getTextoEmail();
		SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();
		
		if(Util.verificarNaoVazio(textoSMS)){
			
			//[FS0012] - Validar quantidade de colunas digitadas/selecionadas	
			//Calculando o total das tags inseridas
			int tamanho = 0;
			Iterator<ColunasTextoSMSEmail> it = colecaoColunas.iterator();
			Integer tamanhoSelecionado = 0;
			while(it.hasNext()){
				ColunasTextoSMSEmail col = it.next();
				if(col.getId().toString().equals(idSelecionado))
					tamanhoSelecionado = col.getTamanhoColuna();
				while(textoSMS.contains(col.getNomeColuna())){
					tamanho += col.getTamanhoColuna();
					int index = textoSMS.indexOf(col.getNomeColuna());
					textoSMS = textoSMS.substring(0, index) + textoSMS.substring(index + col.getNomeColuna().length());
				}
			}
			
			tamanho += tamanhoSelecionado + textoSMS.length() + 57;
			
			//Caso a contagem de caracteres digitados mais o número de dígitos do campo adicionado
			//for maior que o número máximo de colunas da mensagem SMS
			if(tamanho > sistemaParametro.getTamanhoMaximoMensagemSms().intValue()){
				throw new ActionServletException("atencao.numero_colunas_maior_permitido_sms",
						null, sistemaParametro.getTamanhoMaximoMensagemSms().toString());
			}
				
			
			//[F0021] Verificar a integridade do texto de SMS
			this.validarTagsTextAreaSMSEmail(textoSMS, colecaoColunas, new Short("1"), httpServletRequest, form);
			
		}
		
		if(Util.verificarNaoVazio(textoEmail)){
			this.validarTagsTextAreaSMSEmail(textoEmail, colecaoColunas, new Short("2"), httpServletRequest, form);
		}

		
		CobrancaAcaoHelper cobrancaAcaoHelper = new CobrancaAcaoHelper(acaoCobrancaActionForm.getDescricaoAcao(),
				acaoCobrancaActionForm.getIcAcaoObrigatoria(),
				acaoCobrancaActionForm.getIcRepetidaCiclo(),
				acaoCobrancaActionForm.getIcSuspensaoAbastecimento(),
				acaoCobrancaActionForm.getNumeroDiasValidade(),
				acaoCobrancaActionForm.getNumeroDiasEntreAcoes(),
				""+ConstantesSistema.INDICADOR_USO_ATIVO,
				acaoCobrancaActionForm.getIcDebitosACobrar(),
				acaoCobrancaActionForm.getIcCreditosARealizar(),
				acaoCobrancaActionForm.getIcNotasPromissoria(),
				acaoCobrancaActionForm.getIcGeraTaxa(),
				acaoCobrancaActionForm.getOrdemCronograma(),
				acaoCobrancaActionForm.getIcAcrescimosImpontualidade(),
				acaoCobrancaActionForm.getIdAcaoPredecessora(),
				acaoCobrancaActionForm.getIdAcaoPredecessoraAlternativa(),
				acaoCobrancaActionForm.getIdSituacaoLigacaoEsgoto(),
				acaoCobrancaActionForm.getIdTipoDocumentoGerado(),
				acaoCobrancaActionForm.getIdSituacaoLigacaoAgua(),
				acaoCobrancaActionForm.getIdServicoTipo(),
				acaoCobrancaActionForm.getIdCobrancaCriterio(),
				acaoCobrancaActionForm.getIcCompoeCronograma(),
				acaoCobrancaActionForm.getIcEmitirBoletimCadastro(),
				acaoCobrancaActionForm.getIcImoveisSemDebitos(),
				acaoCobrancaActionForm.getNumeroDiasVencimento(),
				acaoCobrancaActionForm.getDescricaoCobrancaCriterio(),
				acaoCobrancaActionForm.getDescricaoServicoTipo(),
				acaoCobrancaActionForm.getIcMetasCronograma(),
				acaoCobrancaActionForm.getIcOrdenamentoCronograma(),
				acaoCobrancaActionForm.getIcOrdenamentoEventual(),
				acaoCobrancaActionForm.getIcDebitoInterfereAcao(),
				acaoCobrancaActionForm.getNumeroDiasRemuneracaoTerceiro(),
				acaoCobrancaActionForm.getIcOrdenarMaiorValor(),
				acaoCobrancaActionForm.getIcValidarItem(),
				acaoCobrancaActionForm.getIcEfetuarAcaoCpfCnpjValido(),
				usuarioLogado,
				acaoCobrancaActionForm.getIndicadorMensagemSMS(),
				acaoCobrancaActionForm.getIndicadorMensagemEmail(),
				acaoCobrancaActionForm.getTextoSMS(),
				acaoCobrancaActionForm.getTextoEmail(),
				acaoCobrancaActionForm.getNumeroMaximoTentativoEnvio());
		
		cobrancaAcaoHelper.setTextoPersonalizado(acaoCobrancaActionForm.getTextoPersonalizado());
		
		if (acaoCobrancaActionForm.getIcValidarItem() != null) {	
			
			if (acaoCobrancaActionForm.getNumeroDiasMinimoCobranca() != null 
					&& !acaoCobrancaActionForm.getNumeroDiasMinimoCobranca().trim().equalsIgnoreCase("")){
				cobrancaAcaoHelper.setNumeroDiasMinimoCobranca(new Integer (acaoCobrancaActionForm.getNumeroDiasMinimoCobranca()));
			}
			if (acaoCobrancaActionForm.getNumeroDiasMaximoCobranca() != null 
					&& !acaoCobrancaActionForm.getNumeroDiasMaximoCobranca().trim().equalsIgnoreCase("")){
				cobrancaAcaoHelper.setNumeroDiasMaximoCobranca(new Integer (acaoCobrancaActionForm.getNumeroDiasMaximoCobranca()));
			}
			
		}
		
		if (acaoCobrancaActionForm.getIndicadorExibeEventual() != null) {
			cobrancaAcaoHelper.setIndicadorExibeEventual(new Short(acaoCobrancaActionForm.getIndicadorExibeEventual()));
		} else {
			cobrancaAcaoHelper.setIndicadorExibeEventual(ConstantesSistema.NAO);
		}
		
		idCobrancaAcao = fachada.inserirAcaoCobranca(cobrancaAcaoHelper);
		
		
		

		montarPaginaSucesso(httpServletRequest, "Ação de Cobrança "
				+ acaoCobrancaActionForm.getDescricaoAcao() + " inserida com sucesso.",
				"Inserir outra Ação de Cobrança",
				"exibirInserirAcaoCobrancaAction.do?menu=sim",
				"exibirAtualizarAcaoCobrancaAction.do?idRegistroAtualizar="
						+ idCobrancaAcao + "&retornoFiltrar=1",
				"Atualizar Ação de Cobrança inserida");
		return retorno;
	}
	
	
	@SuppressWarnings("rawtypes")
	private void validarTagsTextAreaSMSEmail(String textoSMSEmail,
			Collection<ColunasTextoSMSEmail> colecaoColunasTextoSMSEmail,Short indicadorSMSEmail, 
			HttpServletRequest httpServletRequest,AcaoCobrancaActionForm form){
		
		if(validarQuantidadeTagsAbertasFechadas(textoSMSEmail)){
			boolean encontrouTag = false;
			String[] arrayChavesAberta = textoSMSEmail.split("[{]");
			String[] arrayChevesFechada = null;
			for(int posicao = 0;posicao<arrayChavesAberta.length;posicao++){
				encontrouTag = false;
				if(pesquisarChaves(arrayChavesAberta[posicao])){
					arrayChevesFechada = arrayChavesAberta[posicao].split("[}]");
					Iterator iterator = colecaoColunasTextoSMSEmail.iterator();
					ColunasTextoSMSEmail colunasTextoSMSEmail = null;
					while(iterator.hasNext()){
						colunasTextoSMSEmail = (ColunasTextoSMSEmail) iterator.next();
						if(arrayChevesFechada[0].compareTo(colunasTextoSMSEmail.getNomeColuna().replace("{", "").replace("}", ""))==0){
							encontrouTag = true;
						}
					}
					if(encontrouTag==false){
						if(indicadorSMSEmail.compareTo(ConstantesSistema.SIM)==0){
							throw new ActionServletException("atencao.tag.sms.invalida",null,"{"+arrayChevesFechada[0]+"}");
						}else{
							throw new ActionServletException("atencao.tag.email.invalida",null,"{"+arrayChevesFechada[0]+"}");
						}
					}
				}
			}
		}else{
			if(indicadorSMSEmail.compareTo(ConstantesSistema.SIM)==0){
				
				throw new ActionServletException("atencao.quantidade.tags.invalida",null,"SMS");
				
			}else if(indicadorSMSEmail.compareTo(ConstantesSistema.NAO)==0){
				throw new ActionServletException("atencao.quantidade.tags.invalida",null,"Email");		
			}
		}
	}
	
	private boolean validarQuantidadeTagsAbertasFechadas(String textoSMSEmail){
		boolean quantidadeValidada = false;
		int quantidadeAbrirChaves = 0;
		int quantidadeFecharChaves = 0;
		
		char[] arrayTextoSMSEmail = textoSMSEmail.toCharArray();
		
		for(int posicao = 0;posicao<textoSMSEmail.length();posicao++){
			if(arrayTextoSMSEmail[posicao]=='{'){
				quantidadeAbrirChaves++;
			}else if(arrayTextoSMSEmail[posicao]=='}'){
				quantidadeFecharChaves++;
			}
		}
		
		if(quantidadeAbrirChaves==quantidadeFecharChaves){
			quantidadeValidada = true;
		}
		
		return quantidadeValidada;
	}
	
	private boolean pesquisarChaves(String textoQuebrado){
		boolean temAspas = false;
		char[] arrayTextoQuebrado = textoQuebrado.toCharArray();
		for(int posicao = 0;posicao<arrayTextoQuebrado.length;posicao++){
			if(arrayTextoQuebrado[posicao]=='}'){
				temAspas=true;
				break;
			}
		}
		return temAspas;
	}
	
	

}
