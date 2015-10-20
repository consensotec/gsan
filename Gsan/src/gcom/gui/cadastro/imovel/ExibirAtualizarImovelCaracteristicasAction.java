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
package gcom.gui.cadastro.imovel;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoEsgotamento;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoEsgotamento;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.imovel.AreaConstruidaFaixa;
import gcom.cadastro.imovel.Despejo;
import gcom.cadastro.imovel.FiltroAreaConstruidaFaixa;
import gcom.cadastro.imovel.FiltroDespejo;
import gcom.cadastro.imovel.FiltroFonteAbastecimento;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.FiltroImovelTipoCobertura;
import gcom.cadastro.imovel.FiltroImovelTipoConstrucao;
import gcom.cadastro.imovel.FiltroImovelTipoHabitacao;
import gcom.cadastro.imovel.FiltroImovelTipoPropriedade;
import gcom.cadastro.imovel.FiltroPavimentoCalcada;
import gcom.cadastro.imovel.FiltroPavimentoRua;
import gcom.cadastro.imovel.FiltroPiscinaVolumeFaixa;
import gcom.cadastro.imovel.FiltroPocoTipo;
import gcom.cadastro.imovel.FiltroReservatorioVolumeFaixa;
import gcom.cadastro.imovel.FonteAbastecimento;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.ImovelTipoCobertura;
import gcom.cadastro.imovel.ImovelTipoConstrucao;
import gcom.cadastro.imovel.ImovelTipoHabitacao;
import gcom.cadastro.imovel.ImovelTipoPropriedade;
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.cadastro.imovel.PiscinaVolumeFaixa;
import gcom.cadastro.imovel.PocoTipo;
import gcom.cadastro.imovel.ReservatorioVolumeFaixa;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroQuadraFace;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.QuadraFace;
import gcom.cadastro.localidade.bean.IntegracaoQuadraFaceHelper;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoSituacaoHistorico;
import gcom.faturamento.FiltroFaturamentoSituacaoHistorico;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ConectorOr;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.MenorQue;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Exibir os dados da Caracteristica do imovel
 * 
 * @author Fl�vio, Rafael Santos
 * @since 07/02/2006
 */
public class ExibirAtualizarImovelCaracteristicasAction extends GcomAction {

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

        ActionForward retorno = actionMapping
                .findForward("atualizarImovelCaracteristicas");

        //Obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        sessao.removeAttribute("gis");
        
        DynaValidatorForm atualizarImovelCaracteristicasActionForm = (DynaValidatorForm) actionForm;
        
        Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

        //Cria filtros
        FiltroAreaConstruidaFaixa filtroAreaConstruida = new FiltroAreaConstruidaFaixa();
        FiltroReservatorioVolumeFaixa filtroReservatorioVolumeFaixa = new FiltroReservatorioVolumeFaixa();
        FiltroPiscinaVolumeFaixa filtroPiscinaVolumeFaixa = new FiltroPiscinaVolumeFaixa();
        FiltroPavimentoCalcada filtroPavimentoCalcada = new FiltroPavimentoCalcada();
        FiltroPavimentoRua filtroPavimentoRua = new FiltroPavimentoRua();
        FiltroFonteAbastecimento filtroFonteAbastecimento = new FiltroFonteAbastecimento();
        FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
        FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
        FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
        FiltroPocoTipo filtroPocoTipo = new FiltroPocoTipo();
        FiltroDespejo filtroDespejo = new FiltroDespejo();
        FiltroImovelTipoHabitacao filtroImovelTipoHabitacao = new FiltroImovelTipoHabitacao();
        FiltroImovelTipoPropriedade filtroImovelTipoPropriedade = new FiltroImovelTipoPropriedade();
        FiltroImovelTipoConstrucao filtroImovelTipoConstrucao = new FiltroImovelTipoConstrucao();
        FiltroImovelTipoCobertura filtroImovelTipoCobertura = new FiltroImovelTipoCobertura();

        //Cria Cole�ao
        Collection areaContruidaFaixas = null;
        Collection reservatorioVolumeFaixas = null;
        Collection piscinaVolumeFaixas = null;
        Collection pavimetoCalcadas = null;
        Collection pavimentoRuas = null;
        Collection fonteAbastecimentos = null;
        Collection ligacaoEsgotoSituacaos = null;
        Collection ligacaoAguaSituacaos = null;
        Collection pocoTipos = null;
        List imovelPerfis = null;
        Collection despejos = null;
        Collection tipoHabitacao = new ArrayList();
        Collection tipoPropriedade = new ArrayList();
        Collection tipoConstrucao = new ArrayList();
        Collection tipoCobertura = new ArrayList();
        Collection colecaoLigacaoEsgotoEsgotamento = new ArrayList();
        
        //Obt�m a inst�ncia da Fachada
        Fachada fachada = Fachada.getInstancia();

        //Faz as pesuisas e testa se as colecoes estao vazias antes de jogalas
        // na sessao
        
        
        // Verifica permiss�o especial para
        // alterar area construida
        boolean temPermissaoAlterarAreaConstruida = 
        	fachada.verificarPermissaoEspecialAtiva(PermissaoEspecial.ALTERAR_AREA_CONSTRUIDA_IMOVEL, usuario);
        
        if(temPermissaoAlterarAreaConstruida){
        	httpServletRequest.setAttribute("temPermissaoAlterarAreaConstruida", temPermissaoAlterarAreaConstruida);
        }
        
        
        //CARREGA OS DADOS DA AREA CONSTRUIDA
        filtroAreaConstruida.adicionarParametro(new ParametroSimples(
                FiltroAreaConstruidaFaixa.INDICADOR_USO,
                ConstantesSistema.INDICADOR_USO_ATIVO));
        areaContruidaFaixas = fachada.pesquisar(filtroAreaConstruida,
                AreaConstruidaFaixa.class.getName());
//        if (areaContruidaFaixas == null || areaContruidaFaixas.isEmpty()) {
//            throw new ActionServletException("atencao.naocadastrado", null,"Area Construida Faixa");
//        }
        //CARREGA OS DADOS DO RESERVATORIO 
        filtroReservatorioVolumeFaixa.adicionarParametro(new ParametroSimples(
                FiltroReservatorioVolumeFaixa.INDICADOR_USO,
                ConstantesSistema.INDICADOR_USO_ATIVO));
        reservatorioVolumeFaixas = fachada.pesquisar(
                filtroReservatorioVolumeFaixa, ReservatorioVolumeFaixa.class
                        .getName());
        if (reservatorioVolumeFaixas == null
                || reservatorioVolumeFaixas.isEmpty()) {
            throw new ActionServletException("atencao.naocadastrado", null,"Reservatorio Volume Faixa");
        }
        //CARREGA OS DADOS DE PISCINA
        filtroPiscinaVolumeFaixa.adicionarParametro(new ParametroSimples(
                FiltroPiscinaVolumeFaixa.INDICADOR_USO,
                ConstantesSistema.INDICADOR_USO_ATIVO));
        piscinaVolumeFaixas = fachada.pesquisar(filtroPiscinaVolumeFaixa,
                PiscinaVolumeFaixa.class.getName());
        if (piscinaVolumeFaixas == null || piscinaVolumeFaixas.isEmpty()) {
            throw new ActionServletException("atencao.naocadastrado", null,"Piscina Volume Faixa");
        }
        //CARREGA OS DADOS DE PAVIMENTO CAL�ADA
        filtroPavimentoCalcada.adicionarParametro(new ParametroSimples(
                FiltroPavimentoCalcada.INDICADOR_USO,
                ConstantesSistema.INDICADOR_USO_ATIVO));
        filtroPavimentoCalcada.setCampoOrderBy(FiltroPavimentoCalcada.DESCRICAO);
        //filtro pavimento calcadas
        pavimetoCalcadas = fachada.pesquisar(filtroPavimentoCalcada,
                PavimentoCalcada.class.getName());
        if (pavimetoCalcadas == null || pavimetoCalcadas.isEmpty()) {
            throw new ActionServletException("atencao.naocadastrado", null,"Pavimento Cal�ada");
        }
        //filtro pavimento rua
        filtroPavimentoRua.adicionarParametro(new ParametroSimples(
                FiltroPavimentoRua.INDICADOR_USO,
                ConstantesSistema.INDICADOR_USO_ATIVO));
        filtroPavimentoRua.setCampoOrderBy(FiltroPavimentoRua.DESCRICAO);
        pavimentoRuas = fachada.pesquisar(filtroPavimentoRua,
                PavimentoRua.class.getName());
        if (pavimentoRuas == null || pavimentoRuas.isEmpty()) {
            throw new ActionServletException("atencao.naocadastrado", null,"Pavimento Rua");
        }
        
        //CARREGAR OS DADOS DE FONTE DE ABASTECIMENTO
        filtroFonteAbastecimento.adicionarParametro(new ParametroSimples(
                FiltroFonteAbastecimento.INDICADOR_USO,
                ConstantesSistema.INDICADOR_USO_ATIVO));
        filtroFonteAbastecimento.setCampoOrderBy(FiltroFonteAbastecimento.DESCRICAO);
        
        fonteAbastecimentos = fachada.pesquisar(filtroFonteAbastecimento,
                FonteAbastecimento.class.getName());
        if (fonteAbastecimentos == null || fonteAbastecimentos.isEmpty()) {
            throw new ActionServletException("atencao.naocadastrado", null,"Fonte Abastecimento");
        }

        //CARREGAR OS DADOS DE PO�O TIPO
        filtroPocoTipo.setCampoOrderBy(FiltroPocoTipo.DESCRICAO);
        filtroPocoTipo.adicionarParametro(new ParametroSimples(
                FiltroPocoTipo.INDICADOR_USO,
                ConstantesSistema.INDICADOR_USO_ATIVO));
        pocoTipos = fachada.pesquisar(filtroPocoTipo, PocoTipo.class.getName());
        if (pocoTipos == null || pocoTipos.isEmpty()) {
            throw new ActionServletException("atencao.naocadastrado", null,"Po�o Tipo");
        }
        
        Imovel imovel  = null;
        if(sessao.getAttribute("imovelAtualizacao") != null){
        	imovel = (Imovel) sessao.getAttribute("imovelAtualizacao");	
        }
        
        //CARRGAR OS DADOS DE IMOVEL PERFIL --------------------------------
        filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);
        
        filtroImovelPerfil.adicionarParametro(new ParametroSimples(
                FiltroImovelPerfil.INDICADOR_USO,
                ConstantesSistema.INDICADOR_USO_ATIVO));
        
        filtroImovelPerfil.adicionarParametro(
                new ParametroSimples(FiltroImovelPerfil.INDICADOR_GERACAO_AUTOMATICA,
                    ImovelPerfil.NAO));
        
        //Ana Maria 10/09/2008
        //analista : Fabiola
        //Verifica se o usu�rio tem permiss�o para incluir o perfil do im�vel corporativo, grande telemedido e corporativo telemed.
        boolean temPermissaoAlterarPerfilCorporativoImovel = 
            fachada.verificarPermissaoAlterarPerfilCorporativoImovel(usuario);
        
        if(temPermissaoAlterarPerfilCorporativoImovel){
            //exibir imovelPerfil corporativo, grande telemedido e corporativo telemed. no combo
            if(imovel != null && (imovel.getImovelPerfil().getId().intValue() == ImovelPerfil.GRANDE_NO_MES.intValue())){
                filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.ID,ImovelPerfil.GRANDE_NO_MES,ConectorOr.CONECTOR_OR));
            }
            
            if(!(imovel.getImovelPerfil() != null && 
                    imovel.getImovelPerfil().getId().equals(ConstantesSistema.INDICADOR_TARIFA_SOCIAL))) {
                filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.INDICADOR_GERACAO_AUTOMATICA,ImovelPerfil.NAO));
            }
       
            imovelPerfis = (List) fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());            
            
           
        }else if(imovel != null &&
                (imovel.getImovelPerfil().getId().intValue() == ImovelPerfil.CORPORATIVO.intValue())){
            //exibir imovelPerfil corporativo no combo, mas n pode ser alterado
            
            imovelPerfis = (List) fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
            
        }else if(imovel != null &&
                (imovel.getImovelPerfil().getId().intValue() == ImovelPerfil.GRANDE_TELEMEDIDO.intValue())){
            //exibir imovelPerfil grande telemedido no combo, mas n pode ser alterado
            
            imovelPerfis = (List) fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
            
        }else if(imovel != null &&
                (imovel.getImovelPerfil().getId().intValue() == ImovelPerfil.CORPORATIVO_TELEMED.intValue())){
            //exibir imovelPerfil corporativo telemed. no combo, mas n pode ser alterado
            
            imovelPerfis = (List) fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
            
        }else{
            //se n�o tiver permiss�o especial e o imovelPerfil for diferente de corporativo, grande telemedido e corporativo telemed.
            //n�o exibir estes no combo 
        	if(!(imovel.getImovelPerfil() != null && 
                    imovel.getImovelPerfil().getId().equals(ConstantesSistema.INDICADOR_TARIFA_SOCIAL))) {
        		imovelPerfis = 
        			(List) fachada.pesquisarImovelPerfilDiferenteCorporativo();
        	}else{
        		imovelPerfis = 
        			(List) fachada.pesquisarImovelPerfilTarifaSocialDiferenteCorporativo();
        	}
        }        
        
        if (imovelPerfis == null || imovelPerfis.isEmpty()) {
            throw new ActionServletException("atencao.naocadastrado", null,"Imovel Perfil");
        }
        
        
        Collection<ImovelPerfil> colecaoPerfilExcluir = new ArrayList<ImovelPerfil>();
        
        Iterator itPerfis = imovelPerfis.iterator();
        
        while(itPerfis.hasNext()){
        	
        	ImovelPerfil imovelPerfil = (ImovelPerfil) itPerfis.next();
        	
        	if(imovelPerfil.getPermissaoEspecial() != null){        		
        		
        		if(!fachada.verificarPermissaoGrandeCorporativoCliente(imovelPerfil, usuario)){
        			colecaoPerfilExcluir.add(imovelPerfil);
        		}
        	}            	
        }
        
        this.excluirImovelPerfilSemPermissao(colecaoPerfilExcluir, imovelPerfis);
        
        FiltroImovelPerfil filtroIP = new FiltroImovelPerfil();
        filtroIP.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);
        filtroIP.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.ID, imovel.getImovelPerfil().getId()));
        Collection collectionImovelPerfis = fachada.pesquisar(filtroIP, ImovelPerfil.class.getName());
        
        ImovelPerfil imovelPerfilImovel = (ImovelPerfil) Util.retonarObjetoDeColecao(collectionImovelPerfis);
        
        //Acrescentando o perfil do im�vel na lista nos casos onde o perfil do im�vel tem o indicador de gera��o automatica ATIVO
        if (imovelPerfilImovel.getIndicadorGeracaoAutomatica().equals(ConstantesSistema.SIM)){
        	
        	imovelPerfis.add(imovelPerfilImovel);
        	
        	Collections.sort(imovelPerfis,  
			        new Comparator() {  
			           public int compare(Object left, Object right) {  
			           ImovelPerfil leftKey = (ImovelPerfil) left;  
			           ImovelPerfil rightKey = (ImovelPerfil) right;  
		               return leftKey.getDescricao().compareTo(rightKey.getDescricao());  
		             }  
	        });
        }
        
        boolean bloqueaDadosSocial = false;
        if(imovel.getImovelPerfil() != null && 
                (imovel.getImovelPerfil().getId().equals(ConstantesSistema.INDICADOR_TARIFA_SOCIAL))){
	        FiltroImovelPerfil filtroImovelPerfil2 = new FiltroImovelPerfil();
			filtroImovelPerfil2.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.ID, ConstantesSistema.INDICADOR_TARIFA_SOCIAL));
			ImovelPerfil imovelPerfil = (ImovelPerfil) 
				Util.retonarObjetoDeColecao(fachada.pesquisar(filtroImovelPerfil2, ImovelPerfil.class.getName()));
			
			if (imovelPerfil.getIndicadorBloqueaDadosSocial().equals(ConstantesSistema.SIM)){
				bloqueaDadosSocial = true;
			}
        }
        
        if(bloqueaDadosSocial || 
        		(!temPermissaoAlterarPerfilCorporativoImovel &&
        		imovelPerfilImovel.getIndicadorCorporativo().equals(ConstantesSistema.SIM))) {
        	
        	
        	// alterado por victor/chico pra pesquisar todos os imoveis perfis quando o 
        	// select estiver desabilitado
        	filtroImovelPerfil = new FiltroImovelPerfil();
            filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);
            filtroImovelPerfil.adicionarParametro(new ParametroSimples(
                    FiltroImovelPerfil.INDICADOR_USO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));
            imovelPerfis = (List) fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName() );
        	
            httpServletRequest.setAttribute("habilitaImovelPerfil", "1");
        } 
        else {
            
        	httpServletRequest.removeAttribute("habilitaImovelPerfil");
        	
        	if(imovelPerfilImovel.getIndicadorInserirManterPerfil().equals(ConstantesSistema.SIM)){
                
                httpServletRequest.setAttribute("habilitaImovelPerfil", "0");
            }
        	else{
        		
        		httpServletRequest.setAttribute("habilitaImovelPerfil", "1");
        	}
        }
        
        // CRC3184 
		// Desenvolvedor:Hugo Amorim Analista:Rosana
		// Data: 15/12/2009	       
        /*FiltroImovelPerfil filtroIP = new FiltroImovelPerfil();
        filtroIP.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);
        filtroIP.adicionarParametro(new ParametroSimples(
                FiltroImovelPerfil.ID,
                imovel.getImovelPerfil().getId()));
        Collection collectionImovelPerfis = fachada.pesquisar(filtroIP, ImovelPerfil.class.getName());
        
        ImovelPerfil imovelPerfil = (ImovelPerfil) Util.retonarObjetoDeColecao(collectionImovelPerfis);
        
        if(imovelPerfil.getIndicadorInserirManterPerfil().compareTo(new Short("1"))==0
        	&& imovelPerfil.getIndicadorGeracaoAutomatica().compareTo(new Short("2"))==0){
        	httpServletRequest.removeAttribute("habilitaImovelPerfil");
        	httpServletRequest.setAttribute("habilitaImovelPerfil", "0");
        }*/        
        // Fim da Altera��o CRC3184
        
        
        
        //-----------------------------------------------------------------------------------
        
        //CARREGAR OS DADOS DE DESPEJO
        filtroDespejo.setCampoOrderBy(FiltroDespejo.DESCRICAO);
        filtroDespejo.adicionarParametro(new ParametroSimples(
                FiltroDespejo.INDICADOR_USO,
                ConstantesSistema.INDICADOR_USO_ATIVO));
        despejos = fachada.pesquisar(filtroDespejo, Despejo.class.getName());
        if (despejos == null || despejos.isEmpty()) {
            throw new ActionServletException("atencao.naocadastrado", null,"Despejo");
        }
        
        
        //Imovel Tipo Habitacao
        filtroImovelTipoHabitacao.adicionarParametro(
           	new ParametroSimples(FiltroImovelTipoHabitacao.INDICADOR_USO,
           		ConstantesSistema.INDICADOR_USO_ATIVO));
            
        filtroImovelTipoHabitacao.setCampoOrderBy(FiltroImovelTipoHabitacao.DESCRICAO);
        
        tipoHabitacao = 
        	fachada.pesquisar(filtroImovelTipoHabitacao, ImovelTipoHabitacao.class.getName());

        //Imovel Tipo Propriedade
        filtroImovelTipoPropriedade.adicionarParametro(
               	new ParametroSimples(FiltroImovelTipoPropriedade.INDICADOR_USO,
               		ConstantesSistema.INDICADOR_USO_ATIVO));
                
        filtroImovelTipoPropriedade.setCampoOrderBy(FiltroImovelTipoPropriedade.DESCRICAO);
        
        tipoPropriedade = 
        	fachada.pesquisar(filtroImovelTipoPropriedade, ImovelTipoPropriedade.class.getName());
        
        //Imovel Tipo Construcao
        filtroImovelTipoConstrucao.adicionarParametro(
               	new ParametroSimples(FiltroImovelTipoConstrucao.INDICADOR_USO,
               		ConstantesSistema.INDICADOR_USO_ATIVO));
                
        filtroImovelTipoConstrucao.setCampoOrderBy(FiltroImovelTipoConstrucao.DESCRICAO);
        
        tipoConstrucao = 
        	fachada.pesquisar(filtroImovelTipoConstrucao, ImovelTipoConstrucao.class.getName());

        //Imovel Tipo Cobertura
        filtroImovelTipoCobertura.adicionarParametro(
               	new ParametroSimples(FiltroImovelTipoCobertura.INDICADOR_USO,
               		ConstantesSistema.INDICADOR_USO_ATIVO));
                
        filtroImovelTipoCobertura.setCampoOrderBy(FiltroImovelTipoCobertura.DESCRICAO);
        
        tipoCobertura = 
        	fachada.pesquisar(filtroImovelTipoCobertura, ImovelTipoCobertura.class.getName());
        
        
        //IMOVEL QUE ESTA SENDO ATUALIZADO
        //Imovel imovel = (Imovel) sessao.getAttribute("imovelAtualizacao");
        Quadra quadra = imovel.getQuadra();
		String idQuadraFace = (String) atualizarImovelCaracteristicasActionForm.get("idQuadraFace");
        
		/*
		 * Integra��o com o conceito de face da quadra
		 * 
		 * Caso a empresa utilize o conceito de face da quadra (PARM_ICQUADRAFACE = 1 da 
		 * tabela SISTEMA_PARAMETROS); os campos de INDICADOR_REDE_AGUA, INDICADOR_REDE_ESGOTO
		 * DISTRITO_OPERACIONAL e BACIA ser�o obtidos a partir da face.
		 */
		IntegracaoQuadraFaceHelper integracaoQuadraFace = null;
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
    	if (sistemaParametro.getIndicadorQuadraFace().equals(ConstantesSistema.SIM)){
    		
    		FiltroQuadraFace filtroQuadraFace = new FiltroQuadraFace();
    		filtroQuadraFace.adicionarParametro(new ParametroSimples(FiltroQuadraFace.ID, new Integer(idQuadraFace)));
    		
    		Collection colecaoQuadraFace = fachada.pesquisar(filtroQuadraFace, QuadraFace.class.getName());
    		
    		if (colecaoQuadraFace != null && !colecaoQuadraFace.isEmpty()) {
    			
    			QuadraFace quadraFaceNaBase = (QuadraFace) ((List) colecaoQuadraFace).get(0);
    			
    			integracaoQuadraFace = new IntegracaoQuadraFaceHelper();
    			
    			integracaoQuadraFace.setIndicadorRedeAgua(quadraFaceNaBase.getIndicadorRedeAgua());
    			integracaoQuadraFace.setIndicadorRedeEsgoto(quadraFaceNaBase.getIndicadorRedeEsgoto());
    		}
    	}
    	else{
    		
    		FiltroQuadra filtroQuadra = new FiltroQuadra();
//    		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, new Integer(idLocalidade)));
//    		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL, new Integer(idSetorComercial)));
    		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID, quadra.getId()));
    		
    		Collection colecaoQuadra = fachada.pesquisar(filtroQuadra, Quadra.class.getName());
    		
    		if (colecaoQuadra != null && !colecaoQuadra.isEmpty()) {
    			
    			Quadra quadraNaBase = (Quadra) ((List) colecaoQuadra).get(0);
    			
    			integracaoQuadraFace = new IntegracaoQuadraFaceHelper();
    			
    			integracaoQuadraFace.setIndicadorRedeAgua(quadraNaBase.getIndicadorRedeAgua());
    			integracaoQuadraFace.setIndicadorRedeEsgoto(quadraNaBase.getIndicadorRedeEsgoto());
    		}
    	}
        
        //[FS008] - VERIFICAR SITUA��O DA LIGA��O AGUA - FACTIVEL e POTENCIAL
        //verifica a situa��o primeiro do imovel para depois caso n�o satisfa�a a condi��o faz em rela��o a quadra
        if(imovel.getLigacaoAguaSituacao() != null &&
        		(imovel.getLigacaoAguaSituacao().getId().intValue() != LigacaoAguaSituacao.FACTIVEL.intValue()
        				&
               		imovel.getLigacaoAguaSituacao().getId().intValue() != LigacaoAguaSituacao.POTENCIAL.intValue()        				
        		)
        		){
        	//carrega apenas a situa��o de agua do imovel
        	Collection situacaoAguaImovel = new ArrayList();
        	situacaoAguaImovel.add(imovel.getLigacaoAguaSituacao());
        	ligacaoAguaSituacaos = situacaoAguaImovel;
        	
        	httpServletRequest.setAttribute("semSituacaoAgua", "true");
        }else{
        	httpServletRequest.setAttribute("semSituacaoAgua", "false");
        	
            //CARREGAR OS DADOS DE LIGA��O SITUA��O AGUA
            //TESTE PARA SABER SE A QUADRA TEM OU N�O REDE DE AGUA
            if (integracaoQuadraFace.getIndicadorRedeAgua() != null && 
            	integracaoQuadraFace.getIndicadorRedeAgua().equals(Quadra.SEM_REDE)) {

            	filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
    					FiltroLigacaoAguaSituacao.INDICADOR_EXISTENCIA_REDE,
    					LigacaoAguaSituacao.INDICADOR_EXISTENCIA_REDE_NAO));
            	
    			filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
    					FiltroLigacaoAguaSituacao.INDICADOR_EXISTENCIA_LIGACAO,
    					LigacaoAguaSituacao.INDICADOR_EXISTENCIA_LIGACAO_NAO));
            	
            }
            if (integracaoQuadraFace.getIndicadorRedeAgua() != null && 
            	integracaoQuadraFace.getIndicadorRedeAgua().equals(Quadra.COM_REDE)) {

            	filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
    					FiltroLigacaoAguaSituacao.INDICADOR_EXISTENCIA_REDE,
    					LigacaoAguaSituacao.INDICADOR_EXISTENCIA_REDE_SIM));
            	
    			filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
    					FiltroLigacaoAguaSituacao.INDICADOR_EXISTENCIA_LIGACAO,
    					LigacaoAguaSituacao.INDICADOR_EXISTENCIA_LIGACAO_NAO));
                
            }
            if (integracaoQuadraFace.getIndicadorRedeAgua() != null && 
                integracaoQuadraFace.getIndicadorRedeAgua().equals(Quadra.REDE_PARCIAL)) {
            	
            	filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
    					FiltroLigacaoAguaSituacao.INDICADOR_EXISTENCIA_LIGACAO,
    					LigacaoAguaSituacao.INDICADOR_EXISTENCIA_LIGACAO_NAO));
            	
            }
            filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
                    FiltroLigacaoAguaSituacao.INDICADOR_USO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));
            filtroLigacaoAguaSituacao.setCampoOrderBy(FiltroLigacaoAguaSituacao.DESCRICAO);
            ligacaoAguaSituacaos = fachada.pesquisar(filtroLigacaoAguaSituacao,
                    LigacaoAguaSituacao.class.getName());
            
            if (Util.isVazioOrNulo(ligacaoAguaSituacaos)) {
                throw new ActionServletException("atencao.naocadastrado",null, "Liga��o �gua Situa��o");
            }
        	
        }

        //[FS009] - VERIFICAR SITUA��O DA LIGA��O ESGOTO - INDICADOR FATURAMENTO
        //verifica a situa��o primeiro do imovel para depois caso n�o satisfa�a a condi��o faz em rela��o a quadra        
        if(imovel.getLigacaoEsgotoSituacao() != null &&
           imovel.getLigacaoEsgotoSituacao().getIndicadorFaturamentoSituacao().equals(ConstantesSistema.SIM)){
        	
        	//carrega apenas a situa��o do esgoto do imovel
        	Collection situacaoEsgotoImovel = new ArrayList();
        	situacaoEsgotoImovel.add(imovel.getLigacaoEsgotoSituacao());
        	ligacaoEsgotoSituacaos = situacaoEsgotoImovel;
        	
        	httpServletRequest.setAttribute("semSituacaoEsgoto", "true");
        }else{
        	httpServletRequest.setAttribute("semSituacaoEsgoto", "false");
        	
            //CARREGAR OS DADOS DE LIGA��O SITUA��O ESGOTO
            //TESTE PARA SABER SE A QUADRA TEM OU N�O REDE DE ESGOTO
            if (integracaoQuadraFace.getIndicadorRedeEsgoto() != null && 
            	integracaoQuadraFace.getIndicadorRedeEsgoto().equals(Quadra.SEM_REDE)) {

            	filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
    					FiltroLigacaoEsgotoSituacao.INDICADOREXISTENCIAREDE,
    					LigacaoAguaSituacao.INDICADOR_EXISTENCIA_REDE_NAO));
            	
            	filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
    					FiltroLigacaoEsgotoSituacao.INDICADOREXISTENCIALIGACAO,
    					LigacaoAguaSituacao.INDICADOR_EXISTENCIA_LIGACAO_NAO));
            	
            }
            if (integracaoQuadraFace.getIndicadorRedeEsgoto() != null && 
            	integracaoQuadraFace.getIndicadorRedeEsgoto().equals(Quadra.COM_REDE)) {

            	filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
            			FiltroLigacaoEsgotoSituacao.INDICADOREXISTENCIAREDE,
    					LigacaoAguaSituacao.INDICADOR_EXISTENCIA_REDE_SIM));
            	
            	filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
            			FiltroLigacaoEsgotoSituacao.INDICADOREXISTENCIALIGACAO,
    					LigacaoAguaSituacao.INDICADOR_EXISTENCIA_LIGACAO_NAO));
            	
            }
            if (integracaoQuadraFace.getIndicadorRedeEsgoto() != null && 
                integracaoQuadraFace.getIndicadorRedeEsgoto().equals(Quadra.REDE_PARCIAL)) {
            	
            	filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
            			FiltroLigacaoEsgotoSituacao.INDICADOREXISTENCIALIGACAO,
    					LigacaoAguaSituacao.INDICADOR_EXISTENCIA_LIGACAO_NAO));
            	
            }
            filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
                    FiltroLigacaoEsgotoSituacao.INDICADOR_USO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));
            filtroLigacaoEsgotoSituacao.setCampoOrderBy(FiltroLigacaoEsgotoSituacao.DESCRICAO);
            ligacaoEsgotoSituacaos = fachada.pesquisar(filtroLigacaoEsgotoSituacao,
                    LigacaoEsgotoSituacao.class.getName());
            
            if (Util.isVazioOrNulo(ligacaoEsgotoSituacaos)) {
                throw new ActionServletException("atencao.naocadastrado", null,"Liga��o Esgoto Situa��o");
            }
        	
        }
        
        //ESGOTAMENTO
        colecaoLigacaoEsgotoEsgotamento = this.verificarEsgotamento(fachada, imovel, usuario);
        
        
        //LOCAL QUE SETA OS DADOS NO REQUEST
        httpServletRequest.setAttribute("piscinaVolumeFaixas",piscinaVolumeFaixas);
        String piscina = (String) atualizarImovelCaracteristicasActionForm.get("piscina");
        if(piscina != null && !piscina.equalsIgnoreCase("")){
	//      Seta o valor da faixa de piscina
        	filtroPiscinaVolumeFaixa.adicionarParametro(new ParametroSimples(FiltroPiscinaVolumeFaixa.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
	        filtroPiscinaVolumeFaixa.adicionarParametro(new MaiorQue(FiltroPiscinaVolumeFaixa.VOLUME_MAIOR_FAIXA,new BigDecimal(piscina.replace(',','.'))));
	        filtroPiscinaVolumeFaixa.adicionarParametro(new MenorQue(FiltroPiscinaVolumeFaixa.VOLUME_MENOR_FAIXA,new BigDecimal(piscina.replace(',','.'))));
	
	        Collection piscinasFaixas = fachada.pesquisar(filtroPiscinaVolumeFaixa,PiscinaVolumeFaixa.class.getName());
	        PiscinaVolumeFaixa piscinaVolumeFaixa = null; 
	        if (piscinasFaixas != null && !piscinasFaixas.isEmpty()) {
	        	
	        	Iterator piscinasFaixasIterator = piscinasFaixas.iterator();
	
	        	piscinaVolumeFaixa = (PiscinaVolumeFaixa) piscinasFaixasIterator.next();
	        	atualizarImovelCaracteristicasActionForm.set("faixaPiscina",piscinaVolumeFaixa.getId().toString());
	        }
        }

        

        httpServletRequest.setAttribute("areaContruidaFaixas",areaContruidaFaixas);

        String areaConstruida = (String) atualizarImovelCaracteristicasActionForm.get("areaConstruida");
        if(areaConstruida != null && !areaConstruida.equalsIgnoreCase("")){
	        //seta o valor da faixa da area construida
	        filtroAreaConstruida.adicionarParametro(new ParametroSimples(FiltroAreaConstruidaFaixa.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
	        filtroAreaConstruida.adicionarParametro(new MaiorQue(FiltroAreaConstruidaFaixa.MAIOR_FAIXA,new BigDecimal(areaConstruida.replace(',','.'))));
	        filtroAreaConstruida.adicionarParametro(new MenorQue(FiltroAreaConstruidaFaixa.MENOR_FAIXA,new BigDecimal(areaConstruida.replace(',','.'))));
	
	        Collection areasConstruidasFaixas = fachada.pesquisar(filtroAreaConstruida,AreaConstruidaFaixa.class.getName());
	        AreaConstruidaFaixa areaConstruidaFaixa = null;
	        if (areasConstruidasFaixas != null && !areasConstruidasFaixas.isEmpty()) {
	        	
	        	Iterator areaContruidaFaixaIterator = areasConstruidasFaixas.iterator();
	
	        	areaConstruidaFaixa = (AreaConstruidaFaixa) areaContruidaFaixaIterator.next();
	        	atualizarImovelCaracteristicasActionForm.set("faixaAreaConstruida",areaConstruidaFaixa.getId().toString());
	        }        
        }
        
        
        httpServletRequest.setAttribute("reservatorioVolumeFaixas",reservatorioVolumeFaixas);
        String reservatorioInferior = (String) atualizarImovelCaracteristicasActionForm.get("reservatorioInferior");
        if(reservatorioInferior != null && !reservatorioInferior.equalsIgnoreCase("")){
	        //Seta o valor da faixa do  reservatorio inferior
	        filtroReservatorioVolumeFaixa.adicionarParametro(new ParametroSimples(FiltroReservatorioVolumeFaixa.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
	        filtroReservatorioVolumeFaixa.adicionarParametro(new MaiorQue(FiltroReservatorioVolumeFaixa.VOLUME_MAIOR_FAIXA,new BigDecimal(reservatorioInferior.replace(',','.'))));
	        filtroReservatorioVolumeFaixa.adicionarParametro(new MenorQue(FiltroReservatorioVolumeFaixa.VOLUME_MENOR_FAIXA,new BigDecimal(reservatorioInferior.replace(',','.'))));
	
	        Collection inferioresFaixas = fachada.pesquisar(filtroReservatorioVolumeFaixa,ReservatorioVolumeFaixa.class.getName());
	        ReservatorioVolumeFaixa reservatorioVolumeFaixa  = null;
	        if (inferioresFaixas != null && !inferioresFaixas.isEmpty()) {
	        	
	        	Iterator inferioresFaixasIterator = inferioresFaixas.iterator();
	
	        	reservatorioVolumeFaixa = (ReservatorioVolumeFaixa) inferioresFaixasIterator.next();
	        	atualizarImovelCaracteristicasActionForm.set("faixaReservatorioInferior",reservatorioVolumeFaixa.getId().toString());
	        }        
        }
        
        String reservatorioSuperior = (String) atualizarImovelCaracteristicasActionForm.get("reservatorioSuperior");
        if(reservatorioSuperior != null && !reservatorioSuperior.equalsIgnoreCase("")){
	        //Seta o valor da faixa do  reservatorio inferior
	        filtroReservatorioVolumeFaixa.adicionarParametro(new ParametroSimples(FiltroReservatorioVolumeFaixa.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
	        filtroReservatorioVolumeFaixa.adicionarParametro(new MaiorQue(FiltroReservatorioVolumeFaixa.VOLUME_MAIOR_FAIXA,new BigDecimal(reservatorioSuperior.replace(',','.'))));
	        filtroReservatorioVolumeFaixa.adicionarParametro(new MenorQue(FiltroReservatorioVolumeFaixa.VOLUME_MENOR_FAIXA,new BigDecimal(reservatorioSuperior.replace(',','.'))));
	        ReservatorioVolumeFaixa reservatorioVolumeFaixa  = null;
	        Collection superiosFaixas = fachada.pesquisar(filtroReservatorioVolumeFaixa,ReservatorioVolumeFaixa.class.getName());
	        if (superiosFaixas != null && !superiosFaixas.isEmpty()) {
	        	
	        	Iterator superiosFaixasIterator = superiosFaixas.iterator();
	        	reservatorioVolumeFaixa = (ReservatorioVolumeFaixa) superiosFaixasIterator.next();
	        	atualizarImovelCaracteristicasActionForm.set("faixaResevatorioSuperior",reservatorioVolumeFaixa.getId().toString());
	        }   
        }
                
        httpServletRequest.setAttribute("pavimetoCalcadas", pavimetoCalcadas);
        httpServletRequest.setAttribute("pavimentoRuas", pavimentoRuas);
        httpServletRequest.setAttribute("fonteAbastecimentos",fonteAbastecimentos);
        httpServletRequest.setAttribute("ligacaoAguaSituacaos",ligacaoAguaSituacaos);
        httpServletRequest.setAttribute("ligacaoEsgotoSituacaos",ligacaoEsgotoSituacaos);
        httpServletRequest.setAttribute("perfilImoveis", imovelPerfis);
        httpServletRequest.setAttribute("pocoTipos", pocoTipos);
        httpServletRequest.setAttribute("tipoDespejos", despejos);
        
        httpServletRequest.setAttribute("tipoHabitacao",tipoHabitacao);
        httpServletRequest.setAttribute("tipoPropriedade", tipoPropriedade);
        httpServletRequest.setAttribute("tipoConstrucao", tipoConstrucao);
        httpServletRequest.setAttribute("tipoCobertura", tipoCobertura);
        
        httpServletRequest.setAttribute("colecaoLigacaoEsgotoEsgotamento", colecaoLigacaoEsgotoEsgotamento);
        
    	// Nathalia Santos 12/07/2011
        // Analista: Romulo 
        // Verifica se a empresa � a CAER, caso seja seta o atributo, caso n�o remove o atributo.
      
        if (sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase(SistemaParametro.EMPRESA_CAER)){
			httpServletRequest.setAttribute("apresentarIndicadorNivelInstalacaoEsgoto", true);
			
			httpServletRequest.setAttribute("indicadorNivelInstalacaoEsgoto", imovel.getIndicadorNivelInstalacaoEsgoto());
		} else { 
			httpServletRequest.removeAttribute("apresentarIndicadorNivelInstalacaoEsgoto");	
			httpServletRequest.removeAttribute("indicadorNivelInstalacaoEsgoto");	
		}
        
        return retorno;
    }
    
  //Excluir perfil do im�vel sem permiss�o Especial
  	private void excluirImovelPerfilSemPermissao(Collection colecaoImovelExcluir, Collection imovelPerfis){
  		
  		Iterator it = colecaoImovelExcluir.iterator();
  		
  		while(it.hasNext()){
  			ImovelPerfil imovelPerfil = (ImovelPerfil) it.next();
  			
  			imovelPerfis.remove(imovelPerfil);
  		}		
  	}

    private Collection verificarEsgotamento(Fachada fachada, Imovel imovel, Usuario usuario){
    	
    	Collection retorno = new ArrayList();
    	
    	FiltroFaturamentoSituacaoHistorico filtroFaturamentoSituacaoHistorico = 
    	new FiltroFaturamentoSituacaoHistorico();
    	
    	filtroFaturamentoSituacaoHistorico.adicionarParametro(new ParametroSimples(
    	FiltroFaturamentoSituacaoHistorico.ID_IMOVEL, imovel.getId()));
    	
    	filtroFaturamentoSituacaoHistorico.adicionarParametro(new ParametroNulo(
    	FiltroFaturamentoSituacaoHistorico.ANO_MES_FATURAMENTO_RETIRADA));
    	
    	Collection colecaoSituacaoEspecial = fachada.pesquisar(filtroFaturamentoSituacaoHistorico,
    	FaturamentoSituacaoHistorico.class.getName());
    	
    	FiltroLigacaoEsgotoEsgotamento filtroLigacaoEsgotoEsgotamento = null;
        
    	if (colecaoSituacaoEspecial != null && !colecaoSituacaoEspecial.isEmpty()){
    		
    		if (imovel.getLigacaoEsgoto() != null && 
    			imovel.getLigacaoEsgoto().getLigacaoEsgotoEsgotamento() != null){
    			
    			retorno.add(imovel.getLigacaoEsgoto().getLigacaoEsgotoEsgotamento());
    		}
    		else{
    			
    			//Disponibilizar apenas as op��es de esgotamento que n�o geram situa��o especial
    			filtroLigacaoEsgotoEsgotamento = 
    	    	new FiltroLigacaoEsgotoEsgotamento(FiltroLigacaoEsgotoEsgotamento.DESCRICAO);
    			
    	        filtroLigacaoEsgotoEsgotamento.adicionarParametro(
    	        new ParametroNulo(FiltroLigacaoEsgotoEsgotamento.ID_FATURAMENTO_SITUACAO_MOTIVO));
    	        
    	        filtroLigacaoEsgotoEsgotamento.adicionarParametro(
    	        new ParametroSimples(FiltroLigacaoEsgotoEsgotamento.INDICADOR_USO,
    	        ConstantesSistema.INDICADOR_USO_ATIVO));
    	        
    	    	// RM12158 - S� dever� exibir as liga��es de esgotamento com lees_icpermissaoespecialuso = 1 caso o usu�rio tenha permiss�o especial. 
    	        boolean temPermissaoEspecial = Fachada.getInstancia().verificarPermissaoEspecial(PermissaoEspecial.ATRIBUIR_ESGOTAMENTO_SEM_VIABILIDADE_TECNICA,usuario);
        		
        		if (!temPermissaoEspecial){
        			filtroLigacaoEsgotoEsgotamento.adicionarParametro(
        				new ParametroSimplesDiferenteDe(FiltroLigacaoEsgotoEsgotamento.INDICADOR_PERMISSAO_ESPECIAL_USO, ConstantesSistema.SIM));
        		}
    	        
    	        filtroLigacaoEsgotoEsgotamento.setCampoOrderBy(FiltroLigacaoEsgotoEsgotamento.DESCRICAO);
    	        
    	        retorno = fachada.pesquisar(filtroLigacaoEsgotoEsgotamento,
    	        LigacaoEsgotoEsgotamento.class.getName());
    	        
    	        if (Util.isVazioOrNulo(retorno)) {
    	            throw new ActionServletException("atencao.naocadastrado", null,"Esgotamento");
    	        }
    		}
    	}
    	else{
    		
    		//Filtro Esgotamento
    		filtroLigacaoEsgotoEsgotamento = 
    		new FiltroLigacaoEsgotoEsgotamento(FiltroLigacaoEsgotoEsgotamento.DESCRICAO);
    		
    		filtroLigacaoEsgotoEsgotamento.adicionarParametro(
            new ParametroSimples(FiltroLigacaoEsgotoEsgotamento.INDICADOR_USO,
            ConstantesSistema.INDICADOR_USO_ATIVO));
    		
    		// RM12158 - S� dever� exibir as liga��es de esgotamento com lees_icpermissaoespecialuso = 1 caso o usu�rio tenha permiss�o especial. 
    		boolean temPermissaoEspecial = Fachada.getInstancia().verificarPermissaoEspecial(PermissaoEspecial.ATRIBUIR_ESGOTAMENTO_SEM_VIABILIDADE_TECNICA,usuario);
    		
    		if (!temPermissaoEspecial){
    			filtroLigacaoEsgotoEsgotamento.adicionarParametro(
    				new ParametroSimplesDiferenteDe(FiltroLigacaoEsgotoEsgotamento.INDICADOR_PERMISSAO_ESPECIAL_USO, ConstantesSistema.SIM));
    		}
            
            retorno = fachada.pesquisar(filtroLigacaoEsgotoEsgotamento,
            LigacaoEsgotoEsgotamento.class.getName());
            
            if (Util.isVazioOrNulo(retorno)) {
                throw new ActionServletException("atencao.naocadastrado", null,"Esgotamento");
            }
    	}
        
    	return retorno;
    }
}
