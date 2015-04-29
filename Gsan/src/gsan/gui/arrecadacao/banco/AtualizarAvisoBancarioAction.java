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
package gsan.gui.arrecadacao.banco;

import gsan.arrecadacao.ArrecadacaoForma;
import gsan.arrecadacao.aviso.AvisoAcerto;
import gsan.arrecadacao.aviso.AvisoBancario;
import gsan.arrecadacao.aviso.AvisoDeducoes;
import gsan.arrecadacao.banco.ContaBancaria;
import gsan.arrecadacao.banco.FiltroContaBancaria;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.seguranca.acesso.usuario.UsuarioAcao;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Action de exibir atulizar o aviso bancario
 *
 * @author thiago toscano
 * @date 10/03/2006
 */
public class AtualizarAvisoBancarioAction  extends GcomAction {

    /**
     * M�todo responsavel por responder a requisicao
     * 
     * @param actionMapping
     *            Descri��o do par�metro
     * @param actionForm
     *            Descri��o do par�metro
     * @param httpServletRequest
     *            Descri��o do par�metro
     * @param httpServletResponse
     *            Descri��o do par�metro
     * @return Descri��o do retorno
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("telaSucesso");

        AvisoBancarioActionForm form = (AvisoBancarioActionForm) actionForm;

        HttpSession sessao = request.getSession(false);

        Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
        
        // ------------ REGISTRAR TRANSA��O ----------------
        //TODO
//        RegistradorOperacao registradorOperacao = new RegistradorOperacao(
//				Operacao.OPERACAO_AVISO_BANCARIO_ATUALIZAR,
//				new UsuarioAcaoUsuarioHelper(usuarioLogado,
//						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
//        
//        Operacao operacao = new Operacao();
//        operacao.setId(Operacao.OPERACAO_SUBCATEGORIA_ATUALIZAR);
//
//        OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
//        operacaoEfetuada.setOperacao(operacao);
        //------------ REGISTRAR TRANSA��O ----------------
        

    	AvisoBancario avisoBancario = (AvisoBancario) sessao.getAttribute("avisoBancario");
    	//TODO avisoBancario.setOperacaoEfetuada(operacaoEfetuada);
    	avisoBancario.adicionarUsuario(usuarioLogado, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
    	//TODO registradorOperacao.registrarOperacao(avisoBancario);
    	Map<String, String[]> requestMap = request.getParameterMap();

    	//avisoBancario.setUltimaAlteracao(new Date(System.currentTimeMillis()));
    	FiltroContaBancaria filtroContaBancaria = new FiltroContaBancaria();
    	filtroContaBancaria.adicionarCaminhoParaCarregamentoEntidade("agencia");
    	filtroContaBancaria.adicionarCaminhoParaCarregamentoEntidade("agencia.banco");

    	Date dataLancamento = null;
    	if(form.getDataLancamento() != null && !form.getDataLancamento().equals("")){
    		dataLancamento = Util.converteStringParaDate(form.getDataLancamento());
    		avisoBancario.setDataLancamento(dataLancamento);
    	}else{
    		throw new ActionServletException("atencao.avisoBancario.data_lancamento_invalida");
    	}
    	
    	if (form.getIdContaBancaria()!= null && !"".equals(form.getIdContaBancaria().trim())) {
        	filtroContaBancaria.adicionarParametro(new ParametroSimples(FiltroContaBancaria.ID,form.getIdContaBancaria()));
        	Collection coll = Fachada.getInstancia().pesquisar(filtroContaBancaria, ContaBancaria.class.getSimpleName());
    		avisoBancario.setContaBancaria((ContaBancaria)coll.iterator().next());
    	}else{
    		
    		if (requestMap.get("idContaRequest") != null) 
    		{
    			String idConta = (requestMap.get("idContaRequest"))[0];
            	filtroContaBancaria.adicionarParametro(new ParametroSimples(FiltroContaBancaria.ID,idConta));
            	Collection coll = Fachada.getInstancia().pesquisar(filtroContaBancaria, ContaBancaria.class.getSimpleName());
        		avisoBancario.setContaBancaria((ContaBancaria)coll.iterator().next());
    		}
    	}
    	
		// ------------------------------
		// -- ArrecadacaoForma
		// ------------------------------
		if (form.getIdFormaArrecadacao() != null && !form.getIdFormaArrecadacao().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			ArrecadacaoForma arrecadacaoForma = new ArrecadacaoForma();
			arrecadacaoForma.setId(new Integer(form.getIdFormaArrecadacao()));
			avisoBancario.setArrecadacaoForma(arrecadacaoForma);
		}
    	
    	if (form.getNumeroDocumento()!= null && !"".equals(form.getNumeroDocumento().trim())) 
    		avisoBancario.setNumeroDocumento(Integer.parseInt(form.getNumeroDocumento()));
    	if (form.getDataRealizacao()!= null && !"".equals(form.getDataRealizacao().trim())) {
    		
			Date data = Util.converteStringParaDate(form.getDataRealizacao());
			
			if (data == null) {
				throw new ActionServletException("atencao.avisoBancario.data_realizacao_invalida");
			}
			if (data.getTime() > new Date(System.currentTimeMillis()).getTime()) {
				throw new ActionServletException("atencao.avisoBancario.data_realizacao_menor_que_hoje",null,Util.formatarData(new Date(System.currentTimeMillis())));
			}
			if (data.getTime() < dataLancamento.getTime()) {
				throw new ActionServletException("atencao.avisoBancario.data_realizacao_menor_que_data_lancamento", Util.formatarData(data), Util.formatarData(dataLancamento));
			}
    		avisoBancario.setDataRealizada(Util.converteStringParaDate(form.getDataRealizacao()));
    	}	
    	if (form.getValorArrecadacao()!= null && !"".equals(form.getValorArrecadacao().trim()))
    		avisoBancario.setValorArrecadacaoInformado(Util.formatarMoedaRealparaBigDecimal(form.getValorArrecadacao().trim()));
    	if (form.getValorDevolucao()!= null && !"".equals(form.getValorDevolucao().trim()))
    		avisoBancario.setValorDevolucaoInformado(Util.formatarMoedaRealparaBigDecimal(form.getValorDevolucao().trim()));
    	
    	// pegando os valores que foram alterados
    	Collection collAvisoDeducoes = (Collection) sessao.getAttribute("avisoDeducoes");
    	BigDecimal somatorioValorDeducao = new BigDecimal("0.00");
		
    	if (collAvisoDeducoes != null && !collAvisoDeducoes.isEmpty()) {
    		Iterator it = collAvisoDeducoes.iterator();
    		int i = 0;
    		while (it.hasNext()) {
    			AvisoDeducoes avisoDeducoes = (AvisoDeducoes) it.next();
    			if (request.getParameter("posicaoAvisoDeducao_" + i) != null) {
    				avisoDeducoes.setValorDeducao(Util.formatarMoedaRealparaBigDecimal(request.getParameter("posicaoAvisoDeducao_" + i).toString().trim()));
    				somatorioValorDeducao = somatorioValorDeducao.add((Util.formatarMoedaRealparaBigDecimal(request.getParameter("posicaoAvisoDeducao_" + i).toString().trim())));
    			}
    			i++;
    		}
    	}
    	
    	Collection collAvisoDeducoesRemover = (Collection) sessao.getAttribute("avisoDeducoesRemover");
    	/*if (collAvisoDeducoesRemover != null) {
    		Iterator it = collAvisoDeducoesRemover.iterator(); 
        	while(it.hasNext()) {
        		
        	}
    	}*/

//  	 pegando os valores que foram alterados
    	Collection collAvisoAcerto = (Collection) sessao.getAttribute("avisoAcerto");
    	if (collAvisoAcerto != null && !collAvisoAcerto.isEmpty()) {
    		Iterator it = collAvisoAcerto.iterator();
    		int i = 0;
    		while (it.hasNext()) {
    			AvisoAcerto avisoAcerto = (AvisoAcerto) it.next();
    			if (request.getParameter("posicaoAvisoAcerto_" + i) != null) {
    				avisoAcerto.setValorAcerto(Util.formatarMoedaRealparaBigDecimal(request.getParameter("posicaoAvisoAcerto_" + i).toString().trim()));
    			}
    			i++;
    		}
    	}
    	
    	Collection collAvisoAcertoRemover = (Collection) sessao.getAttribute("avisoAcertoRemover");
    	/*if (collAvisoAcertoRemover != null) {
    		Iterator it = collAvisoAcertoRemover.iterator(); 
        	while(it.hasNext()) {
        	}
    	}*/
    	
    	if (form.getValorArrecadacao() == null || form.getValorArrecadacao().equals("")){
    		form.setValorArrecadacao("0,00");
    	}
    	
    	if (form.getValorDevolucao() == null || form.getValorDevolucao().equals("")){
    		form.setValorDevolucao("0,00");
    	}
    	
    	if (form.getValorDeducao() == null || form.getValorDeducao().equals("")){
    		form.setValorDeducao("0,00");
    	}
    	
    	BigDecimal valorRealizado = Util.formatarMoedaRealparaBigDecimal(form.getValorArrecadacao()).subtract(Util.formatarMoedaRealparaBigDecimal(form.getValorDevolucao()));
    	valorRealizado =  valorRealizado.subtract(somatorioValorDeducao); 	
    	
    	avisoBancario.setValorRealizado(valorRealizado);

    	//TODO
    	Fachada.getInstancia().atualizarAvisoBancario(avisoBancario, collAvisoDeducoes, collAvisoDeducoesRemover, collAvisoAcerto, collAvisoAcertoRemover, usuarioLogado);

    	sessao.removeAttribute("avisoDeducoes");
    	sessao.removeAttribute("avisoDeducoesRemover");
        sessao.removeAttribute("avisoAcerto");
    	sessao.removeAttribute("avisoAcertoRemover");

        request.setAttribute("caminhoFuncionalidade","exibirFiltrarAvisoBancarioAction.do?menu=sim");
		request.setAttribute("labelPaginaSucesso"," Realizar outra Manuten��o de Aviso Banc�rio");
		request.setAttribute("mensagemPaginaSucesso","Aviso Banc�rio de c�digo " + avisoBancario.getId() 
				+ " com Data de Lan�amento " + Util.formatarData(avisoBancario.getDataLancamento())  
				+ " e seq�encial " + avisoBancario.getNumeroSequencial()
				+ " do arrecadador " + avisoBancario.getArrecadador().getCliente().getNome() 
				+ " atualizado com sucesso. ");
		
        return retorno;
    }
}