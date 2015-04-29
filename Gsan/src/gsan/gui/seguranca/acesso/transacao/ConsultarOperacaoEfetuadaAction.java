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
package gsan.gui.seguranca.acesso.transacao;

import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.FiltroOperacao;
import gsan.seguranca.acesso.FiltroOperacaoEfetuada;
import gsan.seguranca.acesso.Operacao;
import gsan.seguranca.acesso.OperacaoEfetuada;
import gsan.seguranca.acesso.usuario.FiltroUsuarioAlteracao;
import gsan.seguranca.acesso.usuario.UsuarioAlteracao;
import gsan.seguranca.transacao.AlteracaoTipo;
import gsan.seguranca.transacao.FiltroTabelaLinhaColunaAlteracao;
import gsan.seguranca.transacao.TabelaLinhaColunaAlteracao;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ConsultarOperacaoEfetuadaAction   extends GcomAction {
    /**
     * < <Descri��o do m�todo>>
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
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("exibirOperacaoEfetuada");

        ConsultarOperacaoEfetuadaActionForm form = (ConsultarOperacaoEfetuadaActionForm) actionForm;  

        HttpSession sessao = httpServletRequest.getSession(false);
        
        HashMap resumoDados = null;
        
    	
		if(httpServletRequest.getParameter("tipoPesquisa") != null){
			if(httpServletRequest.getParameter("tipoPesquisa").equals("imovel")){
				Integer icImovel = 1;
				sessao.removeAttribute("icCliente");
				sessao.setAttribute("icImovel", icImovel);
			}
		}
			
		if(httpServletRequest.getParameter("tipoPesquisa") != null){
			if(httpServletRequest.getParameter("tipoPesquisa").equals("cliente")){
				Integer icCliente = 1;
				sessao.removeAttribute("icImovel");
				sessao.setAttribute("icCliente", icCliente);
			}
		}
        FiltroOperacaoEfetuada filtroOperacaoEfetuada = new FiltroOperacaoEfetuada();
        filtroOperacaoEfetuada.adicionarCaminhoParaCarregamentoEntidade(FiltroOperacaoEfetuada.OPERACAO);
        
        String idOperecaoEfetuada = null;
        if(httpServletRequest.getParameter("idOperacaoEfetuada") != null && !httpServletRequest.getParameter("idOperacaoEfetuada").equals("") ){
        	idOperecaoEfetuada = httpServletRequest.getParameter("idOperacaoEfetuada");
        	
        } else {
        	idOperecaoEfetuada = form.getIdOperacaoEfetuada(); 
        }
        filtroOperacaoEfetuada.adicionarParametro(new ParametroSimples(FiltroOperacaoEfetuada.ID, new Integer(form.getIdOperacaoEfetuada())));

        Collection coll = Fachada.getInstancia().pesquisar(filtroOperacaoEfetuada, OperacaoEfetuada.class.getSimpleName());
        	        	
        if (coll != null && !coll.isEmpty()) { 
        	
        	OperacaoEfetuada operacaoEfetuada = (OperacaoEfetuada) coll.iterator().next();
        	
        	FiltroOperacao filtroOperacao = new FiltroOperacao();
			filtroOperacao.adicionarCaminhoParaCarregamentoEntidade(
					FiltroOperacao.ARGUMENTO_PESQUISA);
			filtroOperacao.adicionarCaminhoParaCarregamentoEntidade(
					FiltroOperacao.ARGUMENTO_PESQUISA_TABELA);
			filtroOperacao.adicionarParametro(new ParametroSimples(FiltroOperacao.ID,operacaoEfetuada.
					getOperacao().getId()));
			
			Collection collOperacao = Fachada.getInstancia().pesquisar(filtroOperacao,
					Operacao.class.getSimpleName());
			
			Operacao operacao = (Operacao) collOperacao.iterator().next();
			operacaoEfetuada.setOperacao(operacao);
			
			if (operacao.getArgumentoPesquisa() != null) {
				sessao.setAttribute("descricaoArgumento", operacao.getArgumentoPesquisa().getDescricaoColuna());
			} else {
				sessao.setAttribute("descricaoArgumento", "");
			}
        	
        	sessao.setAttribute("operacaoEfetuada", operacaoEfetuada);
        	
            FiltroUsuarioAlteracao filtroUsuarioAlteracao = new FiltroUsuarioAlteracao();
            filtroUsuarioAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioAlteracao.OPERACAO_EFETUADA);
//            filtroUsuarioAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioAlteracao.OPERACAO);
            filtroUsuarioAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioAlteracao.USUARIO_ACAO);
//            filtroUsuarioAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioAlteracao.USUARIO);
            filtroUsuarioAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioAlteracao.USUARIO_TIPO);
            filtroUsuarioAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioAlteracao.USUARIO_FUNCIONARIO);
            filtroUsuarioAlteracao.adicionarParametro(new ParametroSimples(FiltroUsuarioAlteracao.OPERACAO_EFETUADA_ID, new Integer(form.getIdOperacaoEfetuada())));
            coll = Fachada.getInstancia().pesquisar(filtroUsuarioAlteracao, UsuarioAlteracao.class.getName());
                     
            sessao.setAttribute("usuarioAlteracao", coll);

            FiltroTabelaLinhaColunaAlteracao filtroTabelaLinhaColunaAlteracao = new FiltroTabelaLinhaColunaAlteracao();            
            filtroTabelaLinhaColunaAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroTabelaLinhaColunaAlteracao.TABELA_COLUNA);
            filtroTabelaLinhaColunaAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroTabelaLinhaColunaAlteracao.TABELA_LINHA_ALTERACAO);
            filtroTabelaLinhaColunaAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroTabelaLinhaColunaAlteracao.TABELA);
            filtroTabelaLinhaColunaAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroTabelaLinhaColunaAlteracao.OPERACAO_EFETUADA);
            filtroTabelaLinhaColunaAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroTabelaLinhaColunaAlteracao.ALTERACAO_TIPO);
            filtroTabelaLinhaColunaAlteracao.adicionarParametro(new ParametroSimples(FiltroTabelaLinhaColunaAlteracao.OPERACAO_EFETUADA_ID, new Integer(form.getIdOperacaoEfetuada())));
            filtroTabelaLinhaColunaAlteracao.adicionarParametro(new ParametroSimples(FiltroTabelaLinhaColunaAlteracao.ALTERACAO_TIPO_ID, AlteracaoTipo.ALTERACAO));
            coll = Fachada.getInstancia().pesquisar(filtroTabelaLinhaColunaAlteracao, TabelaLinhaColunaAlteracao.class.getName());
            //sessao.setAttribute("tabelaLinhaColunaAlteracao", coll);
            if (coll != null && !coll.isEmpty()) {
            	resumoDados = consultarResumoInformacoesItemAnalisado(operacaoEfetuada);
            	//sessao.setAttribute("tabelaLinhaColunaAlteracao", coll);
            } else {
            	sessao.removeAttribute("tabelaLinhaColunaAlteracao");
            }

            filtroTabelaLinhaColunaAlteracao = new FiltroTabelaLinhaColunaAlteracao();            
            filtroTabelaLinhaColunaAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroTabelaLinhaColunaAlteracao.TABELA_COLUNA);
            filtroTabelaLinhaColunaAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroTabelaLinhaColunaAlteracao.TABELA_LINHA_ALTERACAO);
            filtroTabelaLinhaColunaAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroTabelaLinhaColunaAlteracao.TABELA);
            filtroTabelaLinhaColunaAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroTabelaLinhaColunaAlteracao.OPERACAO_EFETUADA);
            filtroTabelaLinhaColunaAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroTabelaLinhaColunaAlteracao.ALTERACAO_TIPO);
            filtroTabelaLinhaColunaAlteracao.adicionarParametro(new ParametroSimples(FiltroTabelaLinhaColunaAlteracao.OPERACAO_EFETUADA_ID, new Integer(form.getIdOperacaoEfetuada())));
            filtroTabelaLinhaColunaAlteracao.adicionarParametro(new ParametroSimples(FiltroTabelaLinhaColunaAlteracao.ALTERACAO_TIPO_ID, AlteracaoTipo.EXCLUSAO));
            
            Collection exclusoes = Fachada.getInstancia().pesquisar(filtroTabelaLinhaColunaAlteracao, TabelaLinhaColunaAlteracao.class.getName()); 
            
            if (coll != null && !coll.isEmpty()) {
            	if (resumoDados == null){
            		resumoDados = consultarResumoInformacoesItemAnalisado(operacaoEfetuada);
            	}
            	//sessao.setAttribute("tabelaLinhaAlteracaoExcluida", coll);
            } else {
            	sessao.removeAttribute("tabelaLinhaAlteracaoExcluida");
            }

            filtroTabelaLinhaColunaAlteracao = new FiltroTabelaLinhaColunaAlteracao();
            filtroTabelaLinhaColunaAlteracao.setConsultaSemLimites(true);
            filtroTabelaLinhaColunaAlteracao.setCampoOrderBy("ultimaAlteracao");
            filtroTabelaLinhaColunaAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroTabelaLinhaColunaAlteracao.TABELA_COLUNA);
            filtroTabelaLinhaColunaAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroTabelaLinhaColunaAlteracao.TABELA_LINHA_ALTERACAO);
            filtroTabelaLinhaColunaAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroTabelaLinhaColunaAlteracao.TABELA);
            filtroTabelaLinhaColunaAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroTabelaLinhaColunaAlteracao.OPERACAO_EFETUADA);
            filtroTabelaLinhaColunaAlteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroTabelaLinhaColunaAlteracao.ALTERACAO_TIPO);
            filtroTabelaLinhaColunaAlteracao.adicionarParametro(new ParametroSimples(FiltroTabelaLinhaColunaAlteracao.OPERACAO_EFETUADA_ID, new Integer(form.getIdOperacaoEfetuada())));
            filtroTabelaLinhaColunaAlteracao.adicionarParametro(new ParametroSimples(FiltroTabelaLinhaColunaAlteracao.ALTERACAO_TIPO_ID, AlteracaoTipo.INCLUSAO));
            
            Collection inclusoes = Fachada.getInstancia().pesquisar(filtroTabelaLinhaColunaAlteracao, TabelaLinhaColunaAlteracao.class.getName()); 
            
            Collection juncaoInclRemov = juntarInclusaoExclusaoMesmoTipo(inclusoes, exclusoes);
//            coll = juntarInclusaoExclusaoMesmoTipo(coll, exclusoes);
            coll.addAll(juncaoInclRemov);
//            coll.addAll(inclusoes); 
//            coll.addAll(exclusoes);
            
            getFachada().ordenarTabelaLinhaColunaAlteracao(coll, operacao.getId());
            
//            Collections.sort((List)coll, new Comparador());
       
            if (coll != null && !coll.isEmpty()) {
            	if (resumoDados == null){
            		resumoDados = consultarResumoInformacoesItemAnalisado(operacaoEfetuada);
            	}
            	sessao.setAttribute("tabelaLinhaAlteracaoIncluidas", coll);
            } else {
            	sessao.removeAttribute("tabelaLinhaAlteracaoIncluidas");
            }
                        
            sessao.setAttribute("resumoDados", resumoDados);
        }
        return retorno;
    }
    
    private HashMap consultarResumoInformacoesItemAnalisado(OperacaoEfetuada operacaoEfetuada){
    	
		HashMap resumo = new HashMap();
		
		String dadosAdicionais = operacaoEfetuada.getDadosAdicionais();		
		if (dadosAdicionais != null){
			
			StringTokenizer stk = new StringTokenizer(dadosAdicionais,"$");
			while (stk.hasMoreElements()) {
				
				String element = (String) stk.nextElement();
				int ind = element.indexOf(":");
				
				if (ind != -1){
					String label = element.substring(0,ind);
					String valor = element.substring(ind + 1, element.length());
					resumo.put(label,valor);
				}
				
			}
			
		}
		
//    	FiltroTabelaLinhaAlteracao filtro = new FiltroTabelaLinhaAlteracao();
//		filtro.adicionarParametro(
//			new ParametroSimples(FiltroTabelaLinhaAlteracao.OPERACAO_EFETUADA_ID, operacaoEfetuada.getId()));
//		filtro.adicionarParametro(new ParametroSimples(FiltroTabelaLinhaAlteracao.INDICADOR_PRINCIPAL, 
//			TabelaLinhaAlteracao.INDICADOR_TABELA_LINHA_ALTERACAO_PRINCIPAL));
//		
//        Collection coll = fachada.pesquisar(filtro, TabelaLinhaAlteracao.class.getName());
//        if (coll != null && !coll.isEmpty()){
//        	TabelaLinhaAlteracao alteracao = (TabelaLinhaAlteracao) Util.retonarObjetoDeColecao(coll);
//    		resumo = fachada.consultarResumoInformacoesOperacaoEfetuada(operacaoEfetuada, 
//    				alteracao.getId1());        	
//        }
        return resumo;
    	
    }
    
    /**
     * No sistema, quando se tem altera��es em cole��es, o que est� sendo feito � exclus�o de todos e inclus�o dos novos, 
     * ent�o para ocultar tal a��o, esse m�todo faz um join das remo��es e inclusoes, afim de aparentar ter 
     * sido uma altera��o.
     * @param inclusoes
     * @param remocoes
     * @return
     */
    @SuppressWarnings("unchecked")
	private Collection juntarInclusaoExclusaoMesmoTipo(Collection inclusoes, Collection remocoes){
    	HashMap juncoes = new HashMap();
    	ArrayList linhasJuntadas = new ArrayList();
    	// percorrer as linhas de inclus�es e identificar as remo��es associadas a estas inclus�es
    	// a rela��o entre elas � o id da coluna alterada, e a igualdade entre os seus conte�dos
    	// al�m de identificar se esta coluna � primary key
    	// caso seja identificada a associa��o, guarda-se no Map 
    	//		idTabelaLinhaAlteracao de inclus�o -> idTabelaLinhaAlteracao da remo��o 
    	for (Iterator iter = inclusoes.iterator(); iter.hasNext();) {
			TabelaLinhaColunaAlteracao tlcaIncl = (TabelaLinhaColunaAlteracao) iter.next();
			for (Iterator iterator = remocoes.iterator(); iterator.hasNext();) {
				TabelaLinhaColunaAlteracao tlcaRem = (TabelaLinhaColunaAlteracao) iterator.next();
				if (tlcaIncl.getTabelaColuna().getId().equals(tlcaRem.getTabelaColuna().getId()) && 
					tlcaIncl.getTabelaColuna().getIndicadorPrimaryKey().intValue() == new Integer("1") && 
					tlcaIncl.getConteudoColunaAtual().equalsIgnoreCase(tlcaRem.getConteudoColunaAnterior())){
					juncoes.put(tlcaIncl.getTabelaLinhaAlteracao().getId(), tlcaRem.getTabelaLinhaAlteracao().getId());
				}
			}		
		}
    	
    	// para cada jun��o montada no la�o anterior, vamos setar o valor anterior na linha de inclus�o 
    	// com o valor anterior da linha de remo��o, fazendo assim parece q a remo��o e a inclus�o
    	// fosse uma altera��o
    	for (Iterator iter = juncoes.keySet().iterator(); iter.hasNext();) {
    		Integer idTLAIncl = (Integer) iter.next();
			Integer idTLARem = (Integer) juncoes.get(idTLAIncl);
			Collection linhasJuntadasAux = new ArrayList();
			boolean houveAlgumaAlteracao = false;
			for (Iterator iter2 = inclusoes.iterator(); iter2.hasNext();) {
				TabelaLinhaColunaAlteracao tlcaIncl = (TabelaLinhaColunaAlteracao) iter2.next();
				if (!tlcaIncl.getTabelaLinhaAlteracao().getId().equals(idTLAIncl)){
					continue;
				}

				for (Iterator iter3 = remocoes.iterator(); iter3.hasNext();) {
					TabelaLinhaColunaAlteracao tlcaRem = (TabelaLinhaColunaAlteracao) iter3.next();
					if (tlcaRem.getTabelaLinhaAlteracao().getId().equals(idTLARem) && 
						tlcaIncl.getTabelaColuna().getId().equals(tlcaRem.getTabelaColuna().getId())){
					
						// caso a linha altera��o seja da chake prim�ria, sempre acrescentar 
						if (tlcaIncl.getTabelaColuna().getIndicadorPrimaryKey().intValue() == new Integer("1")
							|| !tlcaIncl.getConteudoColunaAtual().equalsIgnoreCase(tlcaRem.getConteudoColunaAnterior())){
							tlcaIncl.setConteudoColunaAnterior(tlcaRem.getConteudoColunaAnterior());
							
							AlteracaoTipo alteracaoTipo = new AlteracaoTipo();
							alteracaoTipo.setId(AlteracaoTipo.ALTERACAO);
							alteracaoTipo.setDescricao("Altera��o");
											
							tlcaIncl.getTabelaLinhaAlteracao().setAlteracaoTipo(alteracaoTipo);
							linhasJuntadasAux.add(tlcaIncl);
						}
						
						// para cada idem juntado, removemos os respectivos da cole��o de inclus�o e da de remo��o						
						iter2.remove();
						iter3.remove();
						
						// como foi efetuada uma remo��o e uma inclus�o, n�o tinha como saber se os valores foram modificados
						// ou seja, pode acontecer de os conte�dos permanecerem o mesmo, neste caso n�o vamos exibir
						// esta flag 'houveAlgumaAlteracao' servir� para identificar se houve algum campo que foi modificado
						// e neste caso o conjunto de linhas (linha de identifica��o e linhas alteradas) ser�o 
						// adicionadas na cole��o final de altera��es.
						if (!tlcaIncl.getConteudoColunaAtual().equalsIgnoreCase(tlcaRem.getConteudoColunaAnterior())){
							houveAlgumaAlteracao = true;
						}						
					}
				}
				
			}
			if (houveAlgumaAlteracao){
				linhasJuntadas.addAll(linhasJuntadasAux);	
			}							
		}
    	// estas adi��es ser�o as linhas que efetivamente foram de inclus�o, ou seja, n�o havia exclus�o associada a eles
    	// da mesma forma, serao adicionadas as linhas de remo��o que n�o tinha inclus�es associadas.
    	linhasJuntadas.addAll(inclusoes);    	
    	linhasJuntadas.addAll(remocoes);
    	
    	//Ordenadando pelo Id da tabela para agrupar os itens atualizados 
//    	Collections.sort(linhasJuntadas, new Comparador());
    	return linhasJuntadas;
    }
    
    // Ordenador pelo Id da tabela para agrupar os itens atualizados
//    class Comparador implements Comparator {
//        public int compare(Object obj1, Object obj2){
//        	TabelaLinhaColunaAlteracao tlca1 = (TabelaLinhaColunaAlteracao) obj1;
//        	TabelaLinhaColunaAlteracao tlca2 = (TabelaLinhaColunaAlteracao) obj2;
//        	if (obj1 instanceof TabelaLinhaColunaAlteracao && obj2 instanceof TabelaLinhaColunaAlteracao){
//        		
//                int i2 = tlca1.getTabelaLinhaAlteracao().getId().intValue();
//                int i1 = tlca2.getTabelaLinhaAlteracao().getId().intValue();
//                int dif = Math.abs(i1) - Math.abs(i2);
//                if (dif == 0){
//                	
//                	i1 = tlca1.getTabelaColuna().getIndicadorPrimaryKey().intValue();
//                    i2 = tlca2.getTabelaColuna().getIndicadorPrimaryKey().intValue();
//                    dif = Math.abs(i1) - Math.abs(i2);
//                    if (dif == 0){
//                    	dif = tlca1.getTabelaColuna().getDescricaoColuna().compareTo(
//                    		tlca2.getTabelaColuna().getDescricaoColuna());                    	
//                    }
//                }
//                return dif;
//        	} else {
//        		return 0;
//        	}
//        }
//    }

}