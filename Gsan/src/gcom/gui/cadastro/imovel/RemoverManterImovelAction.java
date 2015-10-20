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
package gcom.gui.cadastro.imovel;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelInscricaoAlterada;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class RemoverManterImovelAction extends GcomAction {

    /**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;

        String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();

        ActionForward retorno = actionMapping.findForward("telaSucesso");

        // Obtendo uma instancia da sessao
     	HttpSession sessao = httpServletRequest.getSession(false);
        
        Fachada fachada = Fachada.getInstancia();

        SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
        
        Boolean sitAguaEsgotoCancel = false;
        
        //[SB0007] Verificar Situacao Agua/Esgoto Sistema Parametros.
        if(sistemaParametro.getSituacaoAguaExclusaoImovel() != null 
        		&& sistemaParametro.getSituacaoEsgotoExclusaoImovel() != null){
        	sitAguaEsgotoCancel = true;
        }
        
        Usuario usuario = this.getUsuarioLogado(httpServletRequest);
        
        Collection<Imovel> colecaoImovelRemover = new ArrayList<Imovel>();
        
        boolean removerConcluido = false;
        
        //mensagem de erro quando o usuario tenta excluir sem ter selecionado
        // nenhum
        //registro
        if (httpServletRequest.getParameter("confirmado") == null || httpServletRequest.getParameter("confirmado").equals("")) {
	       
        	if (ids == null || ids.length == 0) {
	            throw new ActionServletException(
	                    "atencao.registros.nao_selecionados");
	        }
	        String[] idsFormatado = new String[ids.length];
	        
	        for (int i = 0; i < ids.length; i++) {
				String dadosImovel = ids[i];
				String[] idUltimaAlteracao = dadosImovel.split("-");
				String idImovel = idUltimaAlteracao[0].trim();
				
				idsFormatado[i] = idImovel;
				Calendar dataInicio = new GregorianCalendar();
				dataInicio.set(Calendar.YEAR,1900);
				dataInicio.set(Calendar.MONTH,0);
				dataInicio.set(Calendar.DATE,01);
	
				Calendar dataFim = new GregorianCalendar();
				dataFim.set(Calendar.YEAR,9999);
				dataFim.set(Calendar.MONTH,11);
				dataFim.set(Calendar.DATE,31);			
				
				ObterDebitoImovelOuClienteHelper obterDebitoImovelOuClienteHelper =  
						fachada.obterDebitoImovelOuCliente(1,idImovel,null,null,"190001","999912",dataInicio.getTime(),dataFim.getTime(),1,1,1,1,1,1,1,null,2);
	        	
				boolean existeDebito = false;
	        	if(obterDebitoImovelOuClienteHelper != null){
	        		//contas
	        		if(obterDebitoImovelOuClienteHelper.getColecaoContasValores() != null && !obterDebitoImovelOuClienteHelper.getColecaoContasValores().isEmpty()){
	        			existeDebito = true;
	        		}else 
	        		//credito a realizar
	        		if(obterDebitoImovelOuClienteHelper.getColecaoCreditoARealizar() != null && !obterDebitoImovelOuClienteHelper.getColecaoCreditoARealizar().isEmpty()){
	        			existeDebito = true;	
	        		}else 
	        		//debito a cobrar
	        		if(obterDebitoImovelOuClienteHelper.getColecaoDebitoACobrar() != null && !obterDebitoImovelOuClienteHelper.getColecaoDebitoACobrar().isEmpty()){
	        			existeDebito = true;	
	        		}else 
	        		//guias pagamento
	        		if(obterDebitoImovelOuClienteHelper.getColecaoGuiasPagamentoValores() != null && !obterDebitoImovelOuClienteHelper.getColecaoGuiasPagamentoValores().isEmpty()){
	        			existeDebito = true;	
	        		}
	        		
	        	}
	        	
	        	//Se existir debito para o imovel
	        	if(existeDebito && !sitAguaEsgotoCancel){
	        		throw new ActionServletException(
					"atencao.imovel.possui.debito.nao.excluir");
	        	}
	        	
	        	FiltroImovel filtroImovel = new FiltroImovel();
	        	filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_AGUA_SITUACAO);
	        	filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_ESGOTO_SITUACAO);
	        	filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.IMOVEL_PERFIL);	        	
	        	filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,idImovel));
	        	
	        	Collection<Imovel> colecaoImovel = fachada.pesquisar(filtroImovel,Imovel.class.getName());
	        	
	        	//[FS0004] - Imóvel possui vínculos no sistema
	        	if(colecaoImovel != null && !colecaoImovel.isEmpty()){
	
	        		Imovel imovel = (Imovel)colecaoImovel.iterator().next();        		
	        		
	        		
	        		if(sitAguaEsgotoCancel){
        				imovel = excluirImovelSituacaoAguaEsgoto(imovel, fachada, sistemaParametro, manutencaoRegistroActionForm, usuario);
    				
	        		}else if((imovel.getLigacaoAguaSituacao().getId().intValue() == LigacaoAguaSituacao.LIGADO.intValue() 
			        				
			        			| imovel.getLigacaoAguaSituacao().getId().intValue() == LigacaoAguaSituacao.CORTADO.intValue())
			        				
			        			| (imovel.getLigacaoEsgotoSituacao().getId().intValue() == LigacaoEsgotoSituacao.LIGADO.intValue())){
			    				
			        			throw new ActionServletException("atencao.imovel.possui.ligacao_agua.ligacao_esgoto");
	        		}
			
				        		
	        		else if((imovel.getLigacaoAgua() != null && imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico() != null) | 
				        		 imovel.getHidrometroInstalacaoHistorico() != null){
				        		//Não é possível excluir o(s) imóvel(is) selecionado(s) devido a existência de hidrômetro na ligação de e/ou no poço
				        		throw new ActionServletException(
								"atencao.imovel.possui.hidrometro.ligacao.poco");
				        	}
				        	
				        	FiltroImovel filtroImovelVinculados = new FiltroImovel();
				        	filtroImovelVinculados.adicionarParametro(new ParametroSimples("imovelCondominio.id", imovel.getId()));
				        	
				        	Collection<Imovel> imovelPesquisadoVinculados = fachada.pesquisar(filtroImovelVinculados, Imovel.class.getName());
				        	
				        	if((imovel.getIndicadorImovelCondominio() != null &&  imovel.getIndicadorImovelCondominio().shortValue() == Imovel.IMOVEL_CONDOMINIO.shortValue())
				        			& (imovelPesquisadoVinculados != null && !imovelPesquisadoVinculados.isEmpty())
				        		){
				        		//Não é possível excluir o(s) imóvel(is) selecionado(s) devido a ser(em) imóvel(is) condomínio, com outros imóveis associados
				        		throw new ActionServletException(
								"atencao.imovel.ser.condominio.outros.imoveis");
				        	}		        	
	        			
	        				
	        			
		        	colecaoImovelRemover.add(imovel);	        		
	        	}	        	
	        	
			}
        }
        //[SB0006] - Verificar Exclusao do Imovel
    	if ( (httpServletRequest.getParameter("confirmado") == null || httpServletRequest.getParameter("confirmado").equals("")) && 
    		sistemaParametro.getIndicadorAlteracaoInscricaoImovel() != null && 
    			sistemaParametro.getIndicadorAlteracaoInscricaoImovel().toString().equals(ConstantesSistema.SIM.toString())) {
    		
//    		if ( fachada.verificarImovelEmProcessoDeFaturamento(imovel.getId()) ) {
    			httpServletRequest.setAttribute("nomeBotao1", "Sim");
				httpServletRequest.setAttribute("nomeBotao3", "N�o");
				httpServletRequest.setAttribute("confirmado", "ok");
				sessao.setAttribute("colecaoImovelRemover", colecaoImovelRemover);

				sessao.setAttribute("remove" , "nao");
				
				return montarPaginaConfirmacao("atencao.imovel_em_processo_de_faturamento_excluir",
				httpServletRequest, actionMapping);
//    		}
    		
    	} else if (httpServletRequest.getParameter("confirmado") == null || httpServletRequest.getParameter("confirmado").equals("")){
    		 /** alterado por pedro alexandre dia 17/11/2006 
             * Recupera o usuário logado para passar no metódo de atualizar imóvel 
             * para verificar se o usuário tem abrangência para atualizar o imóvel
             * informado.
             */
            Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);            
           
            if(!sitAguaEsgotoCancel){
            	fachada.removerImovel(ids, usuarioLogado, sitAguaEsgotoCancel);
            }
            removerConcluido = true;            
    	}
    	
    	String confirmado = httpServletRequest.getParameter("confirmado");
    	// Se tiver sido aprensentada a tela confirmaï¿½ï¿½o e o usuario confirmou.
		if(confirmado != null && confirmado.equalsIgnoreCase("ok") ) {
			retorno = actionMapping.findForward("telaSucesso");
			
			if(!sitAguaEsgotoCancel){
			
				colecaoImovelRemover = (Collection<Imovel>) sessao.getAttribute("colecaoImovelRemover");
				
				Iterator<Imovel> iteratorImovel = colecaoImovelRemover.iterator();
		        while (iteratorImovel.hasNext() ) {
	
	        		Imovel imovel = (Imovel)iteratorImovel.next();					
	        		
		        		//[SB0005] - Preparar Alteração Inscrição ou Exclusão do Imóvel no Encerramento Faturamento
						prepararExclusaoImovelEncerramentoFaturamento(imovel, usuario);
	        		
	        	}
		        removerConcluido = true;
		        sessao.removeAttribute("colecaoImovelRemover");
			}
		}
	    
        //Monta a pagina de sucesso
        if (retorno.getName().equalsIgnoreCase("telaSucesso") && removerConcluido) {
        	if ( ids != null ) {
        		
        		montarPaginaSucesso(httpServletRequest,
            		ids.length + " Im�vel(is) removido(s) com sucesso.",
                    "Realizar outra Manuten��o de Im�vel",
                    "exibirFiltrarImovelAction.do");
        	} else {
        		
        		montarPaginaSucesso(httpServletRequest,
            		colecaoImovelRemover.size() + " Im�vel(is) removido(s) com sucesso.",
                    "Realizar outra Manuten��o de Im�vel",
                    "exibirFiltrarImovelAction.do");
        	}
        } else {
        	montarPaginaSucesso(httpServletRequest,
         		"O Im�vel n�o teve seus dados alterados.",
                 "Realizar outra Manuten��o de Im�vel",
                 "exibirFiltrarImovelAction.do");        	
        }

        return retorno;
    }
    
    /**
	 * [SB0005] - Preparar Alteracao Inscricao ou Exclusao do Imovel no Encerramento Faturamento
	 * @author Arthur Carvalho
	 * @date 26/10/2011
	 */
	private void prepararExclusaoImovelEncerramentoFaturamento( Imovel imovel,
			  Usuario usuario ){
		
		//[FS0038 - Verificar Existência de Alteração de Inscrição Pendente para o Imóvel]
		Fachada.getInstancia().verificarExistenciaAlteracaoInscricaoPendenteImovel(imovel);

		ImovelInscricaoAlterada imovelInscricaoAlterada = new ImovelInscricaoAlterada();
		
		imovelInscricaoAlterada.setImovel(imovel);
		imovelInscricaoAlterada.setFaturamentoGrupo(null);
		//Dados da inscricao Anterior
		imovelInscricaoAlterada.setLocalidadeAnterior(imovel.getLocalidade());
		imovelInscricaoAlterada.setSetorComercialAnterior(imovel.getSetorComercial());
		imovelInscricaoAlterada.setQuadraAnterior(imovel.getQuadra());
		imovelInscricaoAlterada.setQuadraFaceAnterior(imovel.getQuadraFace()!=null?imovel.getQuadraFace():null);
		imovelInscricaoAlterada.setLoteAnterior(imovel.getLote());
		imovelInscricaoAlterada.setSubLoteAnterior(imovel.getSubLote());
		//Dados da inscricao Atual
		imovelInscricaoAlterada.setLocalidadeAtual(imovel.getLocalidade());
		imovelInscricaoAlterada.setSetorComercialAtual(imovel.getSetorComercial());
		imovelInscricaoAlterada.setQuadraAtual(imovel.getQuadra());
		imovelInscricaoAlterada.setQuadraFaceAtual(imovel.getQuadraFace()!=null?imovel.getQuadraFace():null);
		imovelInscricaoAlterada.setLoteAtual(imovel.getLote());
		imovelInscricaoAlterada.setSubLoteAtual(imovel.getSubLote());
		
		imovelInscricaoAlterada.setIndicadorAtualizado(ConstantesSistema.NAO);
		imovelInscricaoAlterada.setIndicadorAtualizacaoExcluida(ConstantesSistema.NAO);
		imovelInscricaoAlterada.setIndicadorImovelExcluido(ConstantesSistema.SIM);
		imovelInscricaoAlterada.setIndicadorErroAlteracao(null);
		
		if (this.getSistemaParametro().getIndicadorAlteracaoInscricaoImovel().toString().equals(""+ConstantesSistema.SIM)){
			
			imovelInscricaoAlterada.setIndicadorAutorizado(ConstantesSistema.NAO);
		} else {
			
			imovelInscricaoAlterada.setIndicadorAutorizado(ConstantesSistema.SIM);
		}
		
		imovelInscricaoAlterada.setUsuarioAlteracao(usuario);
		imovelInscricaoAlterada.setDataAlteracaoOnline(new Date());
		imovelInscricaoAlterada.setDataAlteracaoBatch(null);
		imovelInscricaoAlterada.setUltimaAlteracao(new Date());
		
		Fachada.getInstancia().inserir(imovelInscricaoAlterada);
		
	}
	
	/**
	 * RN2013118086 - Situacao de Agua e Esgoto
	 * [FS0043] - Excluir Imovel com situacao de Agua e Esgoto Informado.
	 * @author Diogo Luiz
	 * @date 10/12/2013
	 * 
	 * @param idImovel
	 * @param fachada
	 * @param sistemaParametro
	 */
	private Imovel excluirImovelSituacaoAguaEsgoto(Imovel imovel, Fachada fachada, SistemaParametro sistemaParametro,
			ManutencaoRegistroActionForm manutencaoRegistroActionForm, Usuario usuario){			
		
		//Alterar Situacao de �gua
		FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
		filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
			FiltroLigacaoAguaSituacao.ID, sistemaParametro.getSituacaoAguaExclusaoImovel()));
		
		Collection colecaoLigacaoAgua = fachada.pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
		
		LigacaoAguaSituacao ligacaoAguaSituacao = null;
		
		if(!Util.isVazioOrNulo(colecaoLigacaoAgua)){
			ligacaoAguaSituacao = (LigacaoAguaSituacao) Util.retonarObjetoDeColecao(colecaoLigacaoAgua);
		}
		
		LigacaoAguaSituacao ligacaoAguaSituacaoAnterior = new LigacaoAguaSituacao();
		
		ligacaoAguaSituacaoAnterior = imovel.getLigacaoAguaSituacao();
		
		imovel.setLigacaoAguaSituacao(ligacaoAguaSituacao);
		imovel.setLigacaoAguaSituacaoAnterior(ligacaoAguaSituacaoAnterior);
		
		//Alterar Situacao de Esgoto
		LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
		FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
		filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgotoSituacao.ID, 
			sistemaParametro.getSituacaoEsgotoExclusaoImovel()));
		
		Collection colecaoLigacaoEsgoto = fachada.pesquisar(filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoLigacaoEsgoto)){
			ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) Util.retonarObjetoDeColecao(colecaoLigacaoEsgoto);
		}
		
		LigacaoEsgotoSituacao ligacaoEsgotoSituacaoAnterior = new LigacaoEsgotoSituacao();
		ligacaoEsgotoSituacaoAnterior = imovel.getLigacaoEsgotoSituacao();
		
		imovel.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);
		imovel.setLigacaoEsgotoSituacaoAnterior(ligacaoEsgotoSituacaoAnterior);
		
		//Alterar Perfil do Im�vel
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.DESCRICAO, 
			ConstantesSistema.IMOVEL_PERFIL_CANCELADOEXCLUIDO));
		
		Collection colecaoImovelPerfil = fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
		
		ImovelPerfil imovelPerfil = null;
		if(!Util.isVazioOrNulo(colecaoImovelPerfil)){
			imovelPerfil = (ImovelPerfil) Util.retonarObjetoDeColecao(colecaoImovelPerfil);
		}
		
		ImovelPerfil imovelPerfilAnterior = new ImovelPerfil();
		imovelPerfilAnterior = imovel.getImovelPerfil();
		imovel.setImovelPerfil(imovelPerfil);
		imovel.setImovelPerfilAnterior(imovelPerfilAnterior);		
		
		imovel.setObservacaoCancelamento(manutencaoRegistroActionForm.getMensagemObservacaoExclusao());
		
		imovel.setIndicadorExclusao(ConstantesSistema.SIM);
		
		//-------------------- Registrar Transa��o ---------------------------------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
		Operacao.OPERACAO_IMOVEL_ATUALIZAR, imovel.getId(), imovel.getId(),
		new UsuarioAcaoUsuarioHelper(usuario, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));										
			
		registradorOperacao.registrarOperacao(imovel);
		//-------------------------- FIM -------------------------------------------------
		
		fachada.atualizar(imovel);
		
		return imovel;
	}
}
