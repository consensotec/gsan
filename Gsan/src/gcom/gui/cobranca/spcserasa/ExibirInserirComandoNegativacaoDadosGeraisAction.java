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
package gcom.gui.cobranca.spcserasa;

import gcom.cadastro.CpfTipo;
import gcom.cadastro.FiltroCpfTipo;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.NegativacaoCriterio;
import gcom.cobranca.NegativacaoCriterioCpfTipo;
import gcom.cobranca.Negativador;
import gcom.cobranca.bean.ParametrosComandoNegativacaoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.spcserasa.FiltroNegativador;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usu�rio a tela que receber� os par�metros para realiza��o
 * da inser��o de um Comando de Negativa��o (Aba n� 01 - Dados Gerais) 
 *
 * @author Ana Maria
 * @date 06/11/2007
 */
public class ExibirInserirComandoNegativacaoDadosGeraisAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("inserirComandoNegativacaoDadosGerais");
        
    	Fachada fachada = Fachada.getInstancia();
    	
    	HttpSession sessao = httpServletRequest.getSession(false);

    	Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
    	
    	SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
    	
		InserirComandoNegativacaoActionForm inserirComandoNegativacaoActionForm = (InserirComandoNegativacaoActionForm) actionForm;
		Integer idComandoNegativacao = null;
		ParametrosComandoNegativacaoHelper parametrosComandoNegativacaoHelper = null;

		if (fachada.verificarExistenciaComandoNegativadorNaoRealizado()) {
			throw new ActionServletException("atencao.existe_comando_negativado_em_aberto");
		}

		if (httpServletRequest.getParameter("desfazer") != null) {
			String idNegativador = inserirComandoNegativacaoActionForm.getIdNegativador();
			inserirComandoNegativacaoActionForm.reset();
			inserirComandoNegativacaoActionForm.setIdNegativador(idNegativador);
		}

		if(httpServletRequest.getParameter("idComandoNegativacao") != null){
			idComandoNegativacao = new Integer(httpServletRequest.getParameter("idComandoNegativacao"));
			
			parametrosComandoNegativacaoHelper = 
				fachada.pesquisarParametrosComandoNegativacao(idComandoNegativacao);
			
			inserirComandoNegativacaoActionForm.reset();
	        //Removendo todos os objetos da sess�o 
	        // Aba 01
	        sessao.removeAttribute("colecaoNegativacaoCriterioCpfTipo");
	        
	        //Aba 03
	        sessao.removeAttribute("colecaoClienteRelacaoTipo");
	        sessao.removeAttribute("colecaoLigAguaSituacao");
	        sessao.removeAttribute("colecaoLigEsgotoSituacao");
	        sessao.removeAttribute("colecaoSubcategoria");
	        sessao.removeAttribute("colecaoPerfilImovel");
	        sessao.removeAttribute("colecaoTipoCliente");
	        
	        //Aba 04
	        sessao.removeAttribute("colcaoCobrancaGrupo");
	        sessao.removeAttribute("colecaoGerenciaRegional");        
	        sessao.removeAttribute("colecaoUnidadeNegocio");
	        sessao.removeAttribute("colecaoEloPolo");
	        
	        //Dados Gerais
			setDadosGerais(sessao, inserirComandoNegativacaoActionForm, parametrosComandoNegativacaoHelper, fachada);
			
			//Dados D�bitos
			setDadosDebitos(inserirComandoNegativacaoActionForm, parametrosComandoNegativacaoHelper);		
			
			//Dados Im�vel
			setDadosImovel(inserirComandoNegativacaoActionForm, parametrosComandoNegativacaoHelper);	
			
			//Dados Localiza��o	
			setDadosLocalizacao(inserirComandoNegativacaoActionForm, parametrosComandoNegativacaoHelper);
			
		}else if(httpServletRequest.getParameter("idComandoNegativacaoSimulado") != null){
			idComandoNegativacao = new Integer(httpServletRequest.getParameter("idComandoNegativacaoSimulado"));
			
			NegativacaoCriterio negativacaoCriterio = 
				fachada.pesquisarComandoNegativacaoSimulado(idComandoNegativacao);
			
			//[FS0027] Verificar sele��o de comando simulado
			if (((Short)negativacaoCriterio.getNegativacaoComando().getIndicadorSimulacao()).equals(ConstantesSistema.NAO)){
    			throw new ActionServletException("atencao.comando_nao_simulacao");
			}
			if(negativacaoCriterio.getNegativacaoComando().getDataHoraRealizacao() == null){
				throw new ActionServletException("atencao.simulacao_nao_realizada");
			}
			inserirComandoNegativacaoActionForm.setIdComandoSimulado(idComandoNegativacao.toString());
			inserirComandoNegativacaoActionForm.setDescricaoComandoSimulado(negativacaoCriterio.getDescricaoTitulo());
		}
		
        //Pesquisar o Negativador
    	if (inserirComandoNegativacaoActionForm.getIdNegativador() != null) {
    		
    		FiltroNegativador filtroNegativador = new FiltroNegativador();    		
    		filtroNegativador.adicionarParametro(new ParametroSimples(
    				FiltroNegativador.ID, inserirComandoNegativacaoActionForm.getIdNegativador()));
    		filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("cliente");
    		
    		Collection negativadores = fachada.pesquisar(filtroNegativador,
    						Negativador.class.getName());
    		
    		if (negativadores != null && !negativadores.isEmpty()) {    		
    			Negativador negativador = (Negativador) Util.retonarObjetoDeColecao(negativadores);
    			inserirComandoNegativacaoActionForm.setNomeNegativador(negativador.getCliente().getNome());		
    		}
    	}else if(parametrosComandoNegativacaoHelper != null && parametrosComandoNegativacaoHelper.getIdNegativador() != null){
    		inserirComandoNegativacaoActionForm.setIdNegativador(String.valueOf(parametrosComandoNegativacaoHelper.getIdNegativador()));
    	}
    	
    	if(httpServletRequest.getAttribute("entrou") != null){    	
        	//Simular Negativa��o - exibir com op��o "N�o" selecionada    		
        	inserirComandoNegativacaoActionForm.setSimular(ConstantesSistema.NAO_CONFIRMADA);   
        	httpServletRequest.setAttribute("habilitarExecutarSimulacao", "habilitarExecutarSimulacao");
        	inserirComandoNegativacaoActionForm.setExecutarSimulacao(ConstantesSistema.NAO_CONFIRMADA);  
    	}
    	
    	/*
         * autor:Jonathan Marcos
         * data:25/10/2013
         * [UC0651] Inserir comando negativacao
         * [Observacao] colocar indicadorCpfCnpjValidado
         */
    	if(httpServletRequest.getAttribute("entrou") != null){    	
        	inserirComandoNegativacaoActionForm.setIndicadorCpfCnpjValido(ConstantesSistema.SIM.toString());   
        }
    	
    	
    	boolean alterarSoCPFCNPJValidos = false;
    	
    	if(sistemaParametro.getIndicadorValidaCpfCnpj().equals(new Integer(ConstantesSistema.NAO))){
    		
			 //[FS0037] Verificar permiss�o especial de altera��o de valor
	       	 alterarSoCPFCNPJValidos = Fachada
	       			.getInstancia()
	       			.verificarPermissaoEspecial(
	       					PermissaoEspecial.ALTERAR_SO_CPF_CNPJ_VALIDOS,
	       					usuarioLogado);
	       	 
	       	 if(!alterarSoCPFCNPJValidos){
	     		inserirComandoNegativacaoActionForm.setAlterarSoCPFCNPJValidos(alterarSoCPFCNPJValidos);           	
	 			inserirComandoNegativacaoActionForm.setIndicadorCpfCnpjValido(ConstantesSistema.NAO_CONFIRMADA);
	 			sessao.removeAttribute("CpfCnpjValidado");
	       	 }else{
   		 		sessao.setAttribute("CpfCnpjValidado", true);
	       	 }			
    	}		
    	
    	//[SB0003]- Determinar Data Prevista para Execu��o do Comando
    	if(httpServletRequest.getAttribute("entrou") != null || httpServletRequest.getParameter("determinarData") != null
    			|| httpServletRequest.getParameter("idComandoNegativacao") != null || httpServletRequest.getParameter("voltar") != null){
    		
        	if(httpServletRequest.getParameter("voltar") != null){    
    	       	if(inserirComandoNegativacaoActionForm.getSimular().equals(ConstantesSistema.CONFIRMADA)){
    	    		inserirComandoNegativacaoActionForm.setSimular(ConstantesSistema.CONFIRMADA);
    	    	}else{
    	    		inserirComandoNegativacaoActionForm.setSimular(ConstantesSistema.NAO_CONFIRMADA);
    	    	}
        	}
    		        	
    		if(httpServletRequest.getParameter("idComandoNegativacao") == null){
    			atualizaColecoesNaSessao(sessao, httpServletRequest);    			
    		}    		
	       
    		Date dataRealizacaoComando = new Date();
    	    
    		Short periodoRealizacao = null;
    		
    		//Pesquisar Sistema Parametro 
    		FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
    		
    		Collection<SistemaParametro> collectionSistemaParametro = fachada
    				.pesquisar(filtroSistemaParametro,
    					SistemaParametro.class.getName());
    		 sistemaParametro = (SistemaParametro) collectionSistemaParametro
    				.iterator().next();
    		periodoRealizacao = sistemaParametro.getCodigoPeriodicidadeNegativacao();
    		
    		if(!"".equals(inserirComandoNegativacaoActionForm.getExecutarSimulacao()) &&
    				!inserirComandoNegativacaoActionForm.getExecutarSimulacao().equals(ConstantesSistema.CONFIRMADA)){
                
    	    	dataRealizacaoComando = fachada.pesquisarUltimaDataRealizacaoComando(
    	    			new Integer(inserirComandoNegativacaoActionForm.getIdNegativador()), 
    					new Integer(inserirComandoNegativacaoActionForm.getSimular()));
    	    	
    	    	if(dataRealizacaoComando == null){
    	    		dataRealizacaoComando = new Date();
    	    	}
        	    
        	    /**
        	     * Autor Hugo Leonardo
        	     * Data: 19/04/2010
        	     * CRC: 3780 - Tratar a periodicidade di�ria para execu��o do comando
        	     * */
        	    
        	    if(periodoRealizacao.equals(SistemaParametro.CODIGO_PERIODICIDADE_NEGATIVACAO_DIARIA)){
        	    	
        	    	dataRealizacaoComando = new Date();
        	    	
        	    }else if(periodoRealizacao.equals(SistemaParametro.CODIGO_PERIODICIDADE_NEGATIVACAO_SEMANAL)){
        
        	    	dataRealizacaoComando = Util.adicionarNumeroDiasDeUmaData(dataRealizacaoComando, 7);  
        	    
        	    }else if(periodoRealizacao.equals(SistemaParametro.CODIGO_PERIODICIDADE_NEGATIVACAO_QUINZENAL)){
        	   
        	    	dataRealizacaoComando = Util.adicionarNumeroDiasDeUmaData(dataRealizacaoComando, 15); 		
        	    
        	    }else{
        	    
        	    	dataRealizacaoComando = Util.adicionarNumeroDiasDeUmaData(dataRealizacaoComando, 30); 
        	    
        	    }
        	  
    		    Calendar calendar = Calendar.getInstance();
    		    calendar.setTime(dataRealizacaoComando);
    		  
    		    Integer diaSemana = calendar.get(Calendar.DAY_OF_WEEK);
    		    Integer qtdSomarData = 7 - diaSemana;   	  
    		    if(qtdSomarData != 0){
    		      dataRealizacaoComando = Util.adicionarNumeroDiasDeUmaData(dataRealizacaoComando, qtdSomarData); 
    		    }	
    	    }
		  
    		// Hugo Leonardo  -  19/04/2010
    		// Se Periodicidade Diaria entao dataPrevista = a data corrente.
    		// CRC 3780
    		if(periodoRealizacao.equals(SistemaParametro.CODIGO_PERIODICIDADE_NEGATIVACAO_DIARIA)){
    			inserirComandoNegativacaoActionForm.setDataPrevista(Util.formatarData(new Date()));
    		}else{
    			inserirComandoNegativacaoActionForm.setDataPrevista(Util.formatarData(dataRealizacaoComando));
    		}
	    	  
	    	if(inserirComandoNegativacaoActionForm.getSimular().equals(ConstantesSistema.CONFIRMADA)){
	    		inserirComandoNegativacaoActionForm.setExecutarSimulacao(ConstantesSistema.NAO_CONFIRMADA);
	    		inserirComandoNegativacaoActionForm.setIdComandoSimulado(null);
				inserirComandoNegativacaoActionForm.setDescricaoComandoSimulado(null);
	    		inserirComandoNegativacaoActionForm.setDataPrevista(Util.formatarData(new Date()));
	    	}else{
	    	    httpServletRequest.setAttribute("habilitarExecutarSimulacao", "habilitarExecutarSimulacao");
	    	}
	        
	        //[FS0026] Verificar exist�ncia de comando para o negativador na data
	        boolean existeComando = fachada.verificarExistenciaComandoNegativador(inserirComandoNegativacaoActionForm.getIdNegativador(),Util.converteStringParaDate(inserirComandoNegativacaoActionForm.getDataPrevista()));
	        if(existeComando && 
	    		httpServletRequest.getAttribute("entrou") == null && 
	    		httpServletRequest.getParameter("entrou") == null){
	    		throw new ActionServletException("atencao.existe_comando_negativado_data", "inserirComandoNegativacaPorCriterioWizardAction.do?voltar=ok&entrou=ok&action=exibirInserirComandoNegativacaoDadosGeraisAction"
	    				,new Exception(), inserirComandoNegativacaoActionForm.getDataPrevista(),inserirComandoNegativacaoActionForm.getNomeNegativador());
	        }
    	}
    	
       	if(inserirComandoNegativacaoActionForm.getSimular().equals(ConstantesSistema.NAO_CONFIRMADA)){
	    	  httpServletRequest.setAttribute("habilitarExecutarSimulacao", "habilitarExecutarSimulacao");
    	}
       	
        //Pesquisar Tipo CPF
        if(sessao.getAttribute("colecaoCPFTipo") == null){
        	FiltroCpfTipo filtroCpfTipo = new FiltroCpfTipo();
    		
    		Collection cpfTipos = fachada.pesquisar(filtroCpfTipo,CpfTipo.class.getName());
    		
    		sessao.setAttribute("colecaoCPFTipo", cpfTipos);
        }
        
        //Pesquisa Usuario 
        if(httpServletRequest.getParameter("pesquisarUsuario") != null){
        	atualizaColecoesNaSessao(sessao, httpServletRequest);
        	
	        String usuario = inserirComandoNegativacaoActionForm.getUsuario();
	       
	        if(usuario != null && !usuario.equals("")){
	        	
	        	FiltroUsuario filtroUsuario = new FiltroUsuario();  
	            
	        	filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, usuario));
	            
	            Collection colecaoUsuario = fachada.pesquisar(
	                    filtroUsuario,Usuario.class.getName());
	            
	            if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
	            	httpServletRequest.setAttribute("corUsuario", "valor");
	            	
	            	inserirComandoNegativacaoActionForm.setUsuario(""
							+ ((Usuario) ((List) colecaoUsuario).get(0)).getId());
	            	inserirComandoNegativacaoActionForm.setNomeUsuario(""
							+ ((Usuario) ((List) colecaoUsuario).get(0)).getNomeUsuario());
				} else {
					httpServletRequest.setAttribute("corUsuario","exception");
					inserirComandoNegativacaoActionForm
	        		.setUsuario(null);
					inserirComandoNegativacaoActionForm
	            		.setNomeUsuario(ConstantesSistema.USUARIO_INEXISTENTE);
				}
	        }
        }
        
        //Adicionar Titularidade CPF/CNPJ da Negativa��o
        if(httpServletRequest.getParameter("adicionarTitularidade") != null){
        	atualizaColecoesNaSessao(sessao, httpServletRequest);
        	
        	if(inserirComandoNegativacaoActionForm.getTitularidadeNegativacao()== null || 
        			inserirComandoNegativacaoActionForm.getTitularidadeNegativacao().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
    			throw new ActionServletException("atencao.campo.informado", null, "Titularidade do CPF/CNPJ da Negativa��o");
        	}
        	Collection<NegativacaoCriterioCpfTipo> colecaoNegativacaoCriterioCpfTipo = null;
        	if(sessao.getAttribute("colecaoNegativacaoCriterioCpfTipo") == null){
        		colecaoNegativacaoCriterioCpfTipo = new ArrayList<NegativacaoCriterioCpfTipo>();
        	}else{
        		colecaoNegativacaoCriterioCpfTipo = (Collection)sessao.getAttribute("colecaoNegativacaoCriterioCpfTipo");
        	}
        	
        	NegativacaoCriterioCpfTipo negativacaoCriterioCpfTipo = new NegativacaoCriterioCpfTipo();
        	Integer idTitularidade = new Integer(inserirComandoNegativacaoActionForm.getTitularidadeNegativacao());
        	//negativacaoCriterioCpfTipo.setId(idTitularidade);
        	FiltroCpfTipo filtroCpfTipo = new FiltroCpfTipo();
        	filtroCpfTipo.adicionarParametro(new ParametroSimples(FiltroCpfTipo.ID, idTitularidade));
    		
    		Collection titularidades = fachada.pesquisar(filtroCpfTipo,
    						CpfTipo.class.getName());
    		
    		if (titularidades != null && !titularidades.isEmpty()) {    		
    			CpfTipo cpfTipo = (CpfTipo) Util.retonarObjetoDeColecao(titularidades);
    			negativacaoCriterioCpfTipo.setCpfTipo(cpfTipo);		
    			negativacaoCriterioCpfTipo.setIndicadorCoincidente(new Short("2"));
    		}
    		
    		//[FS0013] Verificar Titularidade do CPF/CNPJ da Negativa��o j� existe na lista
    		if(colecaoNegativacaoCriterioCpfTipo.contains(negativacaoCriterioCpfTipo)){
    			throw new ActionServletException("atencao.titularidade_negativacao_ja_existe_lista");
    		}
    		
    		inserirComandoNegativacaoActionForm.setTitularidadeNegativacao(""+ConstantesSistema.NUMERO_NAO_INFORMADO);
    		colecaoNegativacaoCriterioCpfTipo.add(negativacaoCriterioCpfTipo);
    		sessao.setAttribute("colecaoNegativacaoCriterioCpfTipo", colecaoNegativacaoCriterioCpfTipo);
        }
        
		//Remover Titularidade CPF/CNPJ da Negativa��o
		if(httpServletRequest.getParameter("idTitularidadeRemover") != null){
			atualizaColecoesNaSessao(sessao, httpServletRequest);
			
			Integer idTitularidadeRemover = new Integer(httpServletRequest.getParameter("idTitularidadeRemover"));
			Collection colecaoNegativacaoCriterioCpfTipo = (Collection)sessao.getAttribute("colecaoNegativacaoCriterioCpfTipo");			
			if(colecaoNegativacaoCriterioCpfTipo != null && !colecaoNegativacaoCriterioCpfTipo.isEmpty()){
				NegativacaoCriterioCpfTipo negativacaoCriterioCpfTipo = new NegativacaoCriterioCpfTipo();
	        	FiltroCpfTipo filtroCpfTipo = new FiltroCpfTipo();
	        	filtroCpfTipo.adicionarParametro(new ParametroSimples(FiltroCpfTipo.ID, idTitularidadeRemover));
	    		
	    		Collection titularidades = fachada.pesquisar(filtroCpfTipo,CpfTipo.class.getName());
    			CpfTipo cpfTipo = (CpfTipo) Util.retonarObjetoDeColecao(titularidades);
				negativacaoCriterioCpfTipo.setCpfTipo(cpfTipo);
				colecaoNegativacaoCriterioCpfTipo.remove(negativacaoCriterioCpfTipo);		
				sessao.setAttribute("colecaoNegativacaoCriterioCpfTipo", colecaoNegativacaoCriterioCpfTipo);
			}
		}
        
    	return retorno;
    }

	private void setDadosLocalizacao(InserirComandoNegativacaoActionForm form, ParametrosComandoNegativacaoHelper parametrosComandoNegativacaoHelper) {
		// Grupo de Cobran�a
		if (parametrosComandoNegativacaoHelper.getColecaoGrupoCobranca()!= null){
			String[] idsGrupoCobranca = new String[parametrosComandoNegativacaoHelper.getColecaoGrupoCobranca().size()];			
			Iterator colecaoGrupoCobranca = parametrosComandoNegativacaoHelper.getColecaoGrupoCobranca().iterator();
			int qtdGrupoCobranca = 0;
			while (colecaoGrupoCobranca.hasNext()) {
				CobrancaGrupo cobrancaGrupo = (CobrancaGrupo) colecaoGrupoCobranca.next();
				idsGrupoCobranca[qtdGrupoCobranca] = cobrancaGrupo.getId().toString();
				qtdGrupoCobranca ++;
			}
			form.setCobrancaGrupo(idsGrupoCobranca);
		}else{
			form.setCobrancaGrupo(null);
		}
		
		// Gerencia Regional
		
		if (parametrosComandoNegativacaoHelper.getColecaoGerenciaRegional()!= null){
			String[] idsGerenciaRegional = new String[parametrosComandoNegativacaoHelper.getColecaoGerenciaRegional().size()];			
			Iterator colecaoGerenciaRegional = parametrosComandoNegativacaoHelper.getColecaoGerenciaRegional().iterator();
			int qtdGerenciaRegional = 0;
			while (colecaoGerenciaRegional.hasNext()) {
				GerenciaRegional gerenciaRegional = (GerenciaRegional) colecaoGerenciaRegional.next();
				idsGerenciaRegional[qtdGerenciaRegional] = gerenciaRegional.getId().toString();
				qtdGerenciaRegional ++;
			}
			form.setGerenciaRegional(idsGerenciaRegional);
		}else{
			form.setGerenciaRegional(null);
		}
		
		// Unidade de Negocio
		
		if (parametrosComandoNegativacaoHelper.getColecaoUnidadeNegocio()!= null){
			String[] idsUnidadeNegocio = new String[parametrosComandoNegativacaoHelper.getColecaoUnidadeNegocio().size()];			
			Iterator colecaoUnidadeNegocio = parametrosComandoNegativacaoHelper.getColecaoUnidadeNegocio().iterator();
			int qtdUnidadeNegocio = 0;
			while (colecaoUnidadeNegocio.hasNext()) {
				UnidadeNegocio unidadeNegocio = (UnidadeNegocio) colecaoUnidadeNegocio.next();
				idsUnidadeNegocio[qtdUnidadeNegocio] = unidadeNegocio.getId().toString();
				qtdUnidadeNegocio ++;
			}
			form.setGerenciaRegional(idsUnidadeNegocio);
		}else{
			form.setGerenciaRegional(null);
		}
		
		// Elo P�lo
		
		if (parametrosComandoNegativacaoHelper.getColecaoEloPolo()!= null){
			String[] idsEloPolo = new String[parametrosComandoNegativacaoHelper.getColecaoUnidadeNegocio().size()];			
			Iterator colecaoEloPolo = parametrosComandoNegativacaoHelper.getColecaoEloPolo().iterator();
			int qtdEloPolo = 0;
			while (colecaoEloPolo.hasNext()) {
				Localidade eloPolo = (Localidade) colecaoEloPolo.next();
				idsEloPolo[qtdEloPolo] = eloPolo.getId().toString();
				qtdEloPolo ++;
			}
			form.setGerenciaRegional(idsEloPolo);
		}else{
			form.setGerenciaRegional(null);
		}
		
		// Localidade Inicial
		if (parametrosComandoNegativacaoHelper.getLocInicial()!= null){
			
			form.setLocalidadeDescricaoInicial(parametrosComandoNegativacaoHelper.getLocInicial());
		}
		
		// Localidade Final
		if (parametrosComandoNegativacaoHelper.getLocFinal()!= null){
			
			form.setLocalidadeDescricaoFinal(parametrosComandoNegativacaoHelper.getLocFinal());
		}
		
		// Setor Comercial Inicial
		if (parametrosComandoNegativacaoHelper.getSetComInicial()!= null){
			
			form.setSetorComercialDescricaoInicial(parametrosComandoNegativacaoHelper.getSetComInicial().toString());
		}
		
		// Setor Comercial Final
		if (parametrosComandoNegativacaoHelper.getSetComFinal()!= null){
			
			form.setSetorComercialDescricaoFinal(parametrosComandoNegativacaoHelper.getSetComFinal().toString());
		}
	}

	private void setDadosImovel(InserirComandoNegativacaoActionForm form, ParametrosComandoNegativacaoHelper parametrosComandoNegativacaoHelper) {
		//	Id Cliente
		if (parametrosComandoNegativacaoHelper.getIdCliente()!= null){
			
			form.setIdCliente(parametrosComandoNegativacaoHelper.getIdCliente().toString());
		}
		
		// Nome Cliente
		if (parametrosComandoNegativacaoHelper.getNomeCliente()!= null){
			
			form.setNomeCliente(parametrosComandoNegativacaoHelper.getNomeCliente());
		}
		
		// Tipo de Rela��o com o Cliente
		if (parametrosComandoNegativacaoHelper.getTipoRelClie()!= null){
			
			form.setTipoRelacaoCliente(parametrosComandoNegativacaoHelper.getTipoRelClie());
		}
		
		// Imovel com Sit. Especial de Cobranca
		if (parametrosComandoNegativacaoHelper.getIndicadorEspCobranca()!= null){
			
			if (parametrosComandoNegativacaoHelper.getIndicadorEspCobranca().equals(ConstantesSistema.SIM)){
				form.setImovSitEspecialCobranca(ConstantesSistema.SIM.toString());
			}else{
				form.setImovSitEspecialCobranca(ConstantesSistema.NAO.toString());
			}
		}
		
		// Imovel com Sit.de Cobranca
		if (parametrosComandoNegativacaoHelper.getIndicadorSitCobranca()!= null){
			
			if (parametrosComandoNegativacaoHelper.getIndicadorSitCobranca().equals(ConstantesSistema.SIM)){
				form.setImovSitCobranca(ConstantesSistema.SIM.toString());
			}else{
				form.setImovSitCobranca(ConstantesSistema.NAO.toString());
			}
		}
		
		//Im�vel com Baixa Renda
		if (parametrosComandoNegativacaoHelper.getIndicadorBaixaRenda()!= null){
			
			if (parametrosComandoNegativacaoHelper.getIndicadorBaixaRenda().equals(ConstantesSistema.SIM)){
				form.setIndicadorBaixaRenda(ConstantesSistema.SIM.toString());
			}else{
				form.setIndicadorBaixaRenda(ConstantesSistema.NAO.toString());
			}
		}
		
		//	Lista de Subcategoria
		if (parametrosComandoNegativacaoHelper.getColecaoSubcategoria()!= null){
			String[] idsSubcategoria = new String[parametrosComandoNegativacaoHelper.getColecaoSubcategoria().size()];
			Iterator colecaoSubcategoria = parametrosComandoNegativacaoHelper.getColecaoSubcategoria().iterator();
			int qtdSubcategoria = 0;
			while (colecaoSubcategoria.hasNext()) {
				Subcategoria subCategoria = (Subcategoria) colecaoSubcategoria.next();
				idsSubcategoria[qtdSubcategoria] = subCategoria.getId().toString();
				qtdSubcategoria ++;
			}
			form.setSubCategoria(idsSubcategoria);
		}else{
			form.setSubCategoria(null);   	
		}
		
		//	Lista de Perfil Imovel
		if (parametrosComandoNegativacaoHelper.getColecaoPerfilImovel()!= null){
			String[] idsPerfilImovel = new String[parametrosComandoNegativacaoHelper.getColecaoPerfilImovel().size()];
			Iterator colecaoPerfilImovel = parametrosComandoNegativacaoHelper.getColecaoPerfilImovel().iterator();
			int qtdPerfilImovel = 0;
			while (colecaoPerfilImovel.hasNext()) {
				ImovelPerfil imovelPerfil = (ImovelPerfil) colecaoPerfilImovel.next();
				idsPerfilImovel[qtdPerfilImovel] = imovelPerfil.getId().toString();
				qtdPerfilImovel ++;
			}
			form.setPerfilImovel(idsPerfilImovel);
		}else{
			form.setPerfilImovel(null);   	
		}
		
		//	Lista de Tipo de Cliente
		if (parametrosComandoNegativacaoHelper.getColecaoTipoCliente()!= null){
			String[] idsTipoCliente = new String[parametrosComandoNegativacaoHelper.getColecaoTipoCliente().size()];
			Iterator colecaoTipoCliente = parametrosComandoNegativacaoHelper.getColecaoTipoCliente().iterator();
			int qtdTipoCliente = 0;
			while (colecaoTipoCliente.hasNext()) {
				ClienteTipo clienteTipo = (ClienteTipo) colecaoTipoCliente.next();
				idsTipoCliente[qtdTipoCliente] = clienteTipo.getId().toString();
				qtdTipoCliente ++;
			}
			form.setTipoCliente(idsTipoCliente);						
		}else{
			form.setTipoCliente(null);   	
		}
	}

	private void setDadosDebitos(InserirComandoNegativacaoActionForm form, ParametrosComandoNegativacaoHelper parametrosComandoNegativacaoHelper) {
		//	Periodo de referencia Inicial
		if (parametrosComandoNegativacaoHelper.getReferenciaInicial() != null){
			
			form.setReferenciaInicial(Util.formatarAnoMesParaMesAno(parametrosComandoNegativacaoHelper.getReferenciaInicial()));
		}
		
		// Periodo de referencia Final
		if (parametrosComandoNegativacaoHelper.getReferenciaFinal()!= null){
			
			form.setReferenciaFinal(Util.formatarAnoMesParaMesAno(parametrosComandoNegativacaoHelper.getReferenciaFinal()));
		}
		
		// Periodo de Vencimento Debito inicial
		if (parametrosComandoNegativacaoHelper.getVencimentoInicial()!= null){
			
			form.setDataVencimentoInicial(Util.formatarData(parametrosComandoNegativacaoHelper.getVencimentoInicial()));
		}
		
		// Periodo de Vencimento Debito Final
		if (parametrosComandoNegativacaoHelper.getVencimentoFinal()!= null){
			
			form.setDataVencimentoFinal(Util.formatarData(parametrosComandoNegativacaoHelper.getVencimentoFinal()));
		}
		
		// Valor Minimo do Debito
		if (parametrosComandoNegativacaoHelper.getValoMinimoDebito() != null){
			
			form.setValorDebitoInicial(Util.formatarMoedaReal(parametrosComandoNegativacaoHelper.getValoMinimoDebito()));
		}
		
		// Valor Maximo do Debito
		if (parametrosComandoNegativacaoHelper.getValoMaximoDebito()!= null){
			
			form.setValorDebitoFinal(Util.formatarMoedaReal(parametrosComandoNegativacaoHelper.getValoMaximoDebito()));
		}
		
		// Quantidade Minina de Contas
		if (parametrosComandoNegativacaoHelper.getQtdMinimaContas()!= null){
			
			form.setNumeroContasInicial(parametrosComandoNegativacaoHelper.getQtdMinimaContas().toString());
		}
		
		// Quantidade Maxima de Contas
		if (parametrosComandoNegativacaoHelper.getQtdMaximaContas()!= null){
			
			form.setNumeroContasFinal(parametrosComandoNegativacaoHelper.getQtdMaximaContas().toString());
		}
		
		// Contas em revisao
		if (parametrosComandoNegativacaoHelper.getIndicadorContaRevisao()!= null){
			
			if (parametrosComandoNegativacaoHelper.getIndicadorContaRevisao().equals(ConstantesSistema.SIM)){
				form.setContasRevisao(ConstantesSistema.SIM.toString());
			}else{
				form.setContasRevisao(ConstantesSistema.NAO.toString());
			}
		}
		
		// Guia de pagamento
		if (parametrosComandoNegativacaoHelper.getIndicadorGuiaPagamento()!= null){
			
			if (parametrosComandoNegativacaoHelper.getIndicadorGuiaPagamento().equals(ConstantesSistema.SIM)){
				form.setGuiasPagamento(ConstantesSistema.SIM.toString());
			}else{
				form.setGuiasPagamento(ConstantesSistema.NAO.toString());
			}
		}
		
		// Parcelamento em Atraso
		if (parametrosComandoNegativacaoHelper.getIndicadorParcelamentoAtraso()!= null){
			
			if (parametrosComandoNegativacaoHelper.getIndicadorParcelamentoAtraso().equals(ConstantesSistema.SIM)){
				form.setParcelaAtraso(ConstantesSistema.SIM.toString());
			}else{
				form.setParcelaAtraso(ConstantesSistema.NAO.toString());
			}
		}
		
		// Dias em atraso de Parcelamento
		if (parametrosComandoNegativacaoHelper.getNumDiasAtrasoParcelamento()!= null){
			
			form.setDiasAtrasoParcelamento(parametrosComandoNegativacaoHelper.getNumDiasAtrasoParcelamento().toString());
		}
		
		// Recebeu Carta de Parcelamento em Atraso
		if (parametrosComandoNegativacaoHelper.getIndicadorCartaParcelamentoAtraso()!= null){
			
			if (parametrosComandoNegativacaoHelper.getIndicadorCartaParcelamentoAtraso().equals(ConstantesSistema.SIM)){
				form.setCartaParcelamentoAtraso(ConstantesSistema.SIM.toString());
			}else{
				form.setCartaParcelamentoAtraso(ConstantesSistema.NAO.toString());
			}
		}
		
		// Dias em Atraso apos Recebimento de Carta
		if (parametrosComandoNegativacaoHelper.getNumDiasAtrasoAposRecCarta()!= null){
			
			form.setDiasAtrasoRecebimentoCarta(parametrosComandoNegativacaoHelper.getNumDiasAtrasoAposRecCarta().toString());
		}
	}

	private void setDadosGerais(HttpSession sessao, InserirComandoNegativacaoActionForm form, 
			ParametrosComandoNegativacaoHelper parametrosComandoNegativacaoHelper, Fachada fachada) {
							
		// Titulo Comando
		if (parametrosComandoNegativacaoHelper.getTituloComando()!= null){
			
			form.setTitulo(parametrosComandoNegativacaoHelper.getTituloComando());
		}
		
		// Descricao da Solicitacao
		if (parametrosComandoNegativacaoHelper.getDescricaoSolicitacao() != null){
			
			form.setSolicitacao(parametrosComandoNegativacaoHelper.getDescricaoSolicitacao());
		}
		
		// Simular Negativacao
		if (parametrosComandoNegativacaoHelper.getSimularNegativacao()!= null){
			
			if (parametrosComandoNegativacaoHelper.getSimularNegativacao().equals(ConstantesSistema.SIM)){
				form.setSimular(ConstantesSistema.SIM.toString());
			}else{
				form.setSimular(ConstantesSistema.NAO.toString());
			}
		}
		
		if(parametrosComandoNegativacaoHelper.getIdComandoNegativacaoSimulado() != null){
			Integer idComandoNegativacao = parametrosComandoNegativacaoHelper.getIdComandoNegativacaoSimulado();
			
			NegativacaoCriterio negativacaoCriterio = 
				fachada.pesquisarComandoNegativacaoSimulado(idComandoNegativacao);
			
			form.setIdComandoSimulado(idComandoNegativacao.toString());
			form.setDescricaoComandoSimulado(negativacaoCriterio.getDescricaoTitulo());
			
		}
		
		//Data Prevista p Execucao
		if (parametrosComandoNegativacaoHelper.getDataExecucao()!= null){
			
			form.setDataPrevista(Util.formatarData(parametrosComandoNegativacaoHelper.getDataExecucao()));
		}
		
		//	Usuario Responsavel
		if (parametrosComandoNegativacaoHelper.getIdUsuario()!= null){
			
			form.setUsuario(parametrosComandoNegativacaoHelper.getIdUsuario().toString());			
			form.setNomeUsuario(parametrosComandoNegativacaoHelper.getUsuarioResponsavel());
		}
		
		// Quantidade Maxima Inclusoes
		if (parametrosComandoNegativacaoHelper.getQtdMaxInclusoes()!= null){
			
			form.setQtdMaximaInclusao(parametrosComandoNegativacaoHelper.getQtdMaxInclusoes().toString());
		}
		
		// Indicador CPF/CNPJ valido
		if (parametrosComandoNegativacaoHelper.getIndicadorCpfCnpjValido()!= null){
			
			if (parametrosComandoNegativacaoHelper.getIndicadorCpfCnpjValido().equals(ConstantesSistema.SIM)){
				form.setIndicadorCpfCnpjValido(ConstantesSistema.SIM.toString());
			}else{
				form.setIndicadorCpfCnpjValido(ConstantesSistema.NAO.toString());
			}
		}
		
		// Titularidade do CPF/CNPJ da Negativacao
		
		if (parametrosComandoNegativacaoHelper.getColecaoTitularNegativacao()!= null && !parametrosComandoNegativacaoHelper.getColecaoTitularNegativacao().isEmpty()){			
			sessao.setAttribute("colecaoNegativacaoCriterioCpfTipo",parametrosComandoNegativacaoHelper.getColecaoTitularNegativacao());
		}
	}

	private void atualizaColecoesNaSessao(HttpSession sessao,
			HttpServletRequest httpServletRequest){
    	     	
		//colecaoNegativacaoCriterioCpfTipo
		if (sessao.getAttribute("colecaoNegativacaoCriterioCpfTipo") != null
		&& !sessao.getAttribute("colecaoNegativacaoCriterioCpfTipo").equals("")) {
		
			Collection colecaoNegativacaoCriterioCpfTipo = (Collection) sessao
			.getAttribute("colecaoNegativacaoCriterioCpfTipo");
			
			// cria as vari�veis para recuperar os par�metros do request e jogar
			// no objeto  NegativacaoCriterioCpfTipo
			String ordem = null;
			String coincidente = null;
			
			//String verificarOrdem = null;
			//boolean verificarCoincidente = false;
		
			Iterator iteratorNegativacaoCriterioCpfTipo = colecaoNegativacaoCriterioCpfTipo.iterator();
			
			while (iteratorNegativacaoCriterioCpfTipo.hasNext()) {
				NegativacaoCriterioCpfTipo negativacaoCriterioCpfTipo = 
					(NegativacaoCriterioCpfTipo) iteratorNegativacaoCriterioCpfTipo.next();
				
				Integer idTitularidade = negativacaoCriterioCpfTipo.getCpfTipo().getId();

				ordem = (String) httpServletRequest.getParameter("ordem" + idTitularidade);
				coincidente = (String) httpServletRequest.getParameter("coincidente" + idTitularidade);
				
				if (ordem != null && !ordem.equals("")) {
					negativacaoCriterioCpfTipo.setNumeroOrdemSelecao(new Short(ordem));
				}

				if(coincidente != null && !coincidente.equals("")){
					negativacaoCriterioCpfTipo.setIndicadorCoincidente(new Short("1"));	
					//verificarCoincidente = true;
				}else{
					negativacaoCriterioCpfTipo.setIndicadorCoincidente(new Short("2"));	
					//verificarCoincidente = false;
				}
				//Verificar ordem e coincidente das Titularidade
/*				if(verificarCoincidente){
					if(verificarOrdem != null){
						if(!verificarOrdem.equals(ordem)){
							throw new ActionServletException("atencao.titularidade_coincidente_ordem_diferente");							
						}
					}else{
						verificarOrdem = ordem;
					}
				}*/
			}
		}	        	
    }
}
