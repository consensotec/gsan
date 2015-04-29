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
package gsan.gui.micromedicao;

import gsan.cadastro.empresa.Empresa;
import gsan.cadastro.localidade.FiltroQuadra;
import gsan.cadastro.localidade.FiltroSetorComercial;
import gsan.cadastro.localidade.Quadra;
import gsan.cadastro.localidade.SetorComercial;
import gsan.cadastro.sistemaparametro.FiltroSistemaParametro;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cobranca.CobrancaAcao;
import gsan.cobranca.CobrancaCriterio;
import gsan.cobranca.CobrancaGrupo;
import gsan.cobranca.FiltroCobrancaAcao;
import gsan.cobranca.FiltroCobrancaCriterio;
import gsan.faturamento.FaturamentoGrupo;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.micromedicao.FiltroRota;
import gsan.micromedicao.Leiturista;
import gsan.micromedicao.Rota;
import gsan.micromedicao.leitura.FiltroLeituraTipo;
import gsan.micromedicao.leitura.FiltroLeiturista;
import gsan.micromedicao.leitura.LeituraTipo;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action form de manter rota
 * 
 * @author Vivianne Sousa
 * @created03/04/2006
 */
public class AtualizarRotaAction extends GcomAction {
	/**
	 * @author Vivianne Sousa
	 * @date 03/04/2006
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

    	InserirRotaActionForm inserirRotaActionForm = (InserirRotaActionForm) actionForm;
        ActionForward retorno = actionMapping.findForward("telaSucesso");
        
        HttpSession sessao = httpServletRequest.getSession(false);
    
        String idRota = (String)sessao.getAttribute("idRegistroAtualizacao");

        String codigoRota =  inserirRotaActionForm.getCodigoRota();
        String idLocalidade = inserirRotaActionForm.getIdLocalidade();
		String codigoSetorComercial = inserirRotaActionForm.getCodigoSetorComercial();
		String idCobrancaGrupo = inserirRotaActionForm.getCobrancaGrupo(); 			
		String idFaturamentoGrupo = inserirRotaActionForm.getFaturamentoGrupo(); 				
		String idLeituraTipo = inserirRotaActionForm.getLeituraTipo(); 
		String idEmpresaLeituristica = inserirRotaActionForm.getEmpresaLeituristica(); 
		String idEmpresaCobranca = inserirRotaActionForm.getEmpresaCobranca();
		String idCobrancaCriterio = inserirRotaActionForm.getCobrancaCriterio();
		String indicadorAjusteConsumo = inserirRotaActionForm.getIndicadorAjusteConsumo();
        String numeroDiasConsumoAjuste = inserirRotaActionForm.getNumeroDiasConsumoAjuste();
		String indicadorFiscalizarCortado = inserirRotaActionForm.getIndicadorFiscalizarCortado();			
		String indicadorFiscalizarSuprimido = inserirRotaActionForm.getIndicadorFiscalizarSuprimido();
		String indicadorTransmissaoOffline = inserirRotaActionForm.getIndicadorTransmissaoOffline();
		String indicadorGerarFalsaFaixa = inserirRotaActionForm.getIndicadorGerarFalsaFaixa();			
		String indicadorGerarFiscalizacao = inserirRotaActionForm.getIndicadorGerarFiscalizacao();			
		String indicadorUso = inserirRotaActionForm.getIndicadorUso();
        String idLeiturista = inserirRotaActionForm.getIdLeiturista();
        String idEmpresaEntregaContas = inserirRotaActionForm.getEmpresaEntregaContas();
        String indicadorRotaAlternativa = inserirRotaActionForm.getIndicadorRotaAlternativa();
        String indicadorSequencialLeitura = inserirRotaActionForm.getIndicadorSequencialLeitura();
        String indicadorArmazenarCoordenadas = inserirRotaActionForm.getIndicadorArmazenarCoordenadas();
		
		String percentualGeracaoFaixaFalsa = null ;
		if (inserirRotaActionForm.getPercentualGeracaoFaixaFalsa() != null){
			percentualGeracaoFaixaFalsa = inserirRotaActionForm.getPercentualGeracaoFaixaFalsa().toString().
			replace(",", ".");
		}
		
		String percentualGeracaoFiscalizacao = null ;
		if (inserirRotaActionForm.getPercentualGeracaoFiscalizacao() != null){
			percentualGeracaoFiscalizacao = inserirRotaActionForm.getPercentualGeracaoFiscalizacao().toString().
			replace(",", ".");
		}
		
		String numeroLimiteImoveis = null;
		if(inserirRotaActionForm.getNumeroLimiteImoveis() != null 
				&& !inserirRotaActionForm.getNumeroLimiteImoveis().equals("")){
			
			numeroLimiteImoveis = inserirRotaActionForm.getNumeroLimiteImoveis();
		}
		
		//Crit�rio de Cobran�a
		Collection collectionRotaAcaoCriterio = null;
	       
        if (sessao.getAttribute("collectionRotaAcaoCriterio") != null) {
        	collectionRotaAcaoCriterio = (Collection) sessao
                    .getAttribute("collectionRotaAcaoCriterio");
        }
		
        
        validacaoRota (idRota, idLocalidade, codigoSetorComercial, codigoRota, idCobrancaGrupo, idFaturamentoGrupo, idLeituraTipo
	    		, idEmpresaLeituristica, idEmpresaEntregaContas, indicadorFiscalizarCortado
	    		, indicadorFiscalizarSuprimido, indicadorTransmissaoOffline, indicadorGerarFalsaFaixa, indicadorGerarFiscalizacao
	    		, percentualGeracaoFaixaFalsa, percentualGeracaoFiscalizacao, idLeiturista
	    		, numeroLimiteImoveis, inserirRotaActionForm, indicadorUso
	    		, collectionRotaAcaoCriterio,httpServletRequest,indicadorSequencialLeitura, numeroDiasConsumoAjuste);
		
        if(inserirRotaActionForm.getNumeroLimiteImoveis() == null 
				|| inserirRotaActionForm.getNumeroLimiteImoveis().equals("")){
			
			numeroLimiteImoveis = null;
		}
        
        Rota rotaNova =  new Rota();

        rotaNova.setId(new Integer(idRota));
        rotaNova.setCodigo(new Short(codigoRota)); 
        rotaNova.setIndicadorAjusteConsumo(new Integer(indicadorAjusteConsumo).shortValue());
        
        if(numeroDiasConsumoAjuste != null && !numeroDiasConsumoAjuste.equals("")){
            
            rotaNova.setNumeroDiasConsumoAjuste( new Integer(numeroDiasConsumoAjuste) );
            
        }else{
            rotaNova.setNumeroDiasConsumoAjuste(null);
        }

        rotaNova.setIndicadorFiscalizarCortado(new Integer(indicadorFiscalizarCortado).shortValue());
        rotaNova.setIndicadorFiscalizarSuprimido(new Integer(indicadorFiscalizarSuprimido).shortValue());
        rotaNova.setIndicadorTransmissaoOffline(new Integer(indicadorTransmissaoOffline).shortValue());
        rotaNova.setIndicadorSequencialLeitura(new Integer(indicadorSequencialLeitura));
        rotaNova.setIndicadorGerarFalsaFaixa(new Integer(indicadorGerarFalsaFaixa).shortValue());
        
        if(percentualGeracaoFaixaFalsa != null && !percentualGeracaoFaixaFalsa.equals("")) {
            rotaNova.setPercentualGeracaoFaixaFalsa(new BigDecimal(percentualGeracaoFaixaFalsa));	
        }
        
        rotaNova.setIndicadorGerarFiscalizacao(new Integer(indicadorGerarFiscalizacao).shortValue());
                     
        if(percentualGeracaoFiscalizacao != null && !percentualGeracaoFiscalizacao.equals("")) {
        	 rotaNova.setPercentualGeracaoFiscalizacao(new BigDecimal(percentualGeracaoFiscalizacao));	
        }
        
        if(indicadorArmazenarCoordenadas != null && !indicadorArmazenarCoordenadas.equals("")) {
        	rotaNova.setIndicadorCoordenadas(Short.parseShort(indicadorArmazenarCoordenadas));
        }else{
        	rotaNova.setIndicadorCoordenadas(ConstantesSistema.NAO);
        }
		
        SetorComercial setorComercial = null;
        if(codigoSetorComercial != null && !codigoSetorComercial.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	
        	//Filtro para descobrir id do setor comercial
        	FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

        	filtroSetorComercial.adicionarParametro(
        		new ParametroSimples(
    				FiltroSetorComercial.ID_LOCALIDADE, 
    				new Integer(idLocalidade))); 

    		filtroSetorComercial.adicionarParametro(
    			new ParametroSimples(
    				FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, 
    				new Integer(codigoSetorComercial)));

    		Collection<SetorComercial> colectionSetorComerciais = 
    			this.getFachada().pesquisar(
    				filtroSetorComercial, 
    				SetorComercial.class.getName());
        	
    		Integer idSetorComercial =  
    			colectionSetorComerciais.iterator().next().getId();
    		
    		
        	setorComercial = new SetorComercial();
        	setorComercial.setId(new Integer (idSetorComercial));

        	rotaNova.setSetorComercial(setorComercial);

		}
		
        if(indicadorRotaAlternativa != null){
        	SetorComercial setorComercialTemp = rotaNova.getSetorComercial();
        	
        	if(setorComercialTemp != null){
        		if(!this.verificaExistenciaQuadraAtivaParaRota(idRota) && !indicadorRotaAlternativa.equals("2")){
        			rotaNova.setIndicadorRotaAlternativa(new Integer(indicadorRotaAlternativa).shortValue());		
            	}else if(indicadorRotaAlternativa.equals("2")){
            		rotaNova.setIndicadorRotaAlternativa(new Integer(indicadorRotaAlternativa).shortValue());
            	}else{
            		throw new ActionServletException("atencao.rota_nao_pode_virar_alternativa");
            	}
        	}	  	
        }
	
        CobrancaGrupo cobrancaGrupo = null;
        if(idCobrancaGrupo != null && !idCobrancaGrupo.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	cobrancaGrupo = new CobrancaGrupo();
        	cobrancaGrupo.setId(new Integer (idCobrancaGrupo));
        	rotaNova.setCobrancaGrupo(cobrancaGrupo);
		}
        

        FaturamentoGrupo faturamentoGrupo = null;
        if(idFaturamentoGrupo != null && !idFaturamentoGrupo.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){

        	faturamentoGrupo = new FaturamentoGrupo();
        	faturamentoGrupo.setId(new Integer (idFaturamentoGrupo));
        	
        	rotaNova.setFaturamentoGrupo(faturamentoGrupo);
		}

        LeituraTipo leituraTipo = null;
        if(idLeituraTipo != null && !idLeituraTipo.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	leituraTipo = new LeituraTipo();
        	leituraTipo.setId(new Integer (idLeituraTipo));
        	rotaNova.setLeituraTipo(leituraTipo);
		}

        Empresa empresaLeituristica = null;
        if(idEmpresaLeituristica != null && !idEmpresaLeituristica.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
          	empresaLeituristica = new Empresa();
        	empresaLeituristica.setId(new Integer (idEmpresaLeituristica));
        	rotaNova.setEmpresa(empresaLeituristica);
		}
        
        Empresa empresaEntregaContas = null;
        if(idEmpresaEntregaContas != null && !idEmpresaEntregaContas.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	empresaEntregaContas = new Empresa();
        	empresaEntregaContas.setId(new Integer (idEmpresaEntregaContas));
        	rotaNova.setEmpresaEntregaContas(empresaEntregaContas);
		}
        
        Empresa empresaCobranca = null;
        if (idEmpresaCobranca != null && !idEmpresaCobranca.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
        	empresaCobranca = new Empresa();
        	empresaCobranca.setId(new Integer (idEmpresaCobranca));
        	rotaNova.setEmpresaCobranca(empresaCobranca);
        }

        CobrancaCriterio cobrancaCriterio = null;
        if(idCobrancaCriterio != null && !idCobrancaCriterio.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	cobrancaCriterio = new CobrancaCriterio();
        	cobrancaCriterio.setId(new Integer (idCobrancaCriterio));
		}
                
        rotaNova.setIndicadorUso(new Integer(indicadorUso).shortValue());
        rotaNova.setUltimaAlteracao(Util.converteStringParaDateHora(inserirRotaActionForm.getUltimaAlteracao()));
        rotaNova.setIndicadorAjusteConsumo( Short.parseShort( inserirRotaActionForm.getIndicadorAjusteConsumo() ) );
        
        if ( idLeiturista != null && !idLeiturista.equals( "" )  ){
            Leiturista leiturista = new Leiturista();
            leiturista.setId( Integer.parseInt( idLeiturista ) );
            rotaNova.setLeiturista( leiturista );
        }    
        
        //Verificar a existencia de Rota associada
    	FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
    	filtroSetorComercial.adicionarParametro( new ParametroSimples ( FiltroSetorComercial.ID_LOCALIDADE,
    			new Integer(idLocalidade) ) );
    	filtroSetorComercial.adicionarParametro( new ParametroSimples ( FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
    			new Integer(codigoSetorComercial) ) );
    	
    	Collection setorComercialAssociadoRota = this.getFachada()
    			.pesquisar( filtroSetorComercial, SetorComercial.class.getName() );

    	Iterator iteratorComercialAssociadoRota = setorComercialAssociadoRota.iterator();
    	SetorComercial setor = null;
    
    	while ( iteratorComercialAssociadoRota.hasNext() ) {
    	
    		setor = (SetorComercial) iteratorComercialAssociadoRota.next();
            
    		if ( setor.getIndicadorSetorAlternativo().equals( ConstantesSistema.INDICADOR_USO_DESATIVO) &&
    				new Short(indicadorRotaAlternativa).equals( ConstantesSistema.INDICADOR_USO_ATIVO) ) {
    			
    			throw new ActionServletException("atencao.setor_comercial_rota_nao_alternativo");
    		}
    		
    		if ( setor.getIndicadorSetorAlternativo().equals( ConstantesSistema.INDICADOR_USO_ATIVO) &&
    				new Short(indicadorRotaAlternativa).equals( ConstantesSistema.INDICADOR_USO_DESATIVO) ) {
    			
    			throw new ActionServletException("atencao.setor_comercial_rota_alternativo");
    		}
    		
    		
    	}
    	
    	// N�mero Limite Imoveis por Rota.
		if(numeroLimiteImoveis != null 
				&& !numeroLimiteImoveis.equals("")){
			
			rotaNova.setNumeroLimiteImoveis( new Integer(numeroLimiteImoveis));
		}
     
        //------------ REGISTRAR TRANSA��O ----------------
        /*rotaNova.setOperacaoEfetuada(operacaoEfetuada);
        rotaNova.adicionarUsuario(Usuario.USUARIO_TESTE, 
        		UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
        registradorOperacao.registrarOperacao(rotaNova);*/
        //------------ REGISTRAR TRANSA��O ----------------  
        
        Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
        
        this.getFachada().atualizarRota(rotaNova,idLocalidade,collectionRotaAcaoCriterio, usuarioLogado);
               
		sessao.removeAttribute("collectionRotaAcaoCriterio");
		sessao.removeAttribute("idRegistroAtualizacao");

		
        //Monta a p�gina de sucesso
        if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
            montarPaginaSucesso(httpServletRequest, "Rota de c�digo "
                    + inserirRotaActionForm.getCodigoRota() 
                    + " da localidade "
                    + idLocalidade
                    + " do setor "
                    + codigoSetorComercial
                    + " atualizada com sucesso.",
                    "Realizar outra Manuten��o de Rota", "exibirFiltrarRotaAction.do?desfazer=S");
        }
        
        
       return retorno;
    }
    
    
    
    private void validacaoRota (String idRota, String idLocalidade
    		,String codigoSetorComercial
    		,String codigoRota
    		,String idCobrancaGrupo
    		,String idFaturamentoGrupo
    		,String idLeituraTipo
    		,String idEmpresaLeituristica
    		,String idEmpresaEntregaContas
    		,String indicadorFiscalizarCortado
    		,String indicadorFiscalizarSuprimido
    		,String indicadorTransmissaoOffline
    		,String indicadorGerarFalsaFaixa
    		,String indicadorGerarFiscalizacao
    		,String percentualGeracaoFaixaFalsa		
    		,String percentualGeracaoFiscalizacao
            ,String idLeiturista
            ,String numeroLimiteImoveis
            ,InserirRotaActionForm form
    		,String indicadorUso
    		,Collection collectionRotaAcaoCriterioNovos
    		,HttpServletRequest httpServletRequest 
    		,String indicadorSequencialLeitura
            ,String numeroDiasConsumoAjuste){
    	
    	//Localidade � obrigat�rio.
		if ((idLocalidade == null) || (idLocalidade.equals(""))) {
			httpServletRequest.setAttribute("nomeCampo","idLocalidade");
			throw new ActionServletException("atencao.localidade_nao_informada");
		}else if (Util.validarValorNaoNumerico(idLocalidade)){
			//Localidade n�o num�rico.
			httpServletRequest.setAttribute("nomeCampo","idLocalidade");
			throw new ActionServletException("atencao.nao.numerico",
					null,"Localidade");		
		}
		
		// Setor Comercial � obrigat�rio.
		if ((codigoSetorComercial == null) || (codigoSetorComercial.equals(""))) {
			httpServletRequest.setAttribute("nomeCampo","codigoSetorComercial");
			throw new ActionServletException(
					"atencao.codigo_setor_comercial_nao_informado");
		}else if(Util.validarValorNaoNumerico(codigoSetorComercial)){
			//Setor Comercial n�o num�rico.
			httpServletRequest.setAttribute("nomeCampo","codigoSetorComercial");
			throw new ActionServletException("atencao.nao.numerico",
					null,"Setor Comercial");		
		}else{
			verificaExistenciaCodSetorComercial(idLocalidade,
					codigoSetorComercial,httpServletRequest);
		}
        
        if(Util.validarValorDiferenteZero(numeroDiasConsumoAjuste)){
            //Quantidade de Ajuste de Consumo n�o num�rico ou zero.
            httpServletRequest.setAttribute("nomeCampo","numeroDiasConsumoAjuste");
            throw new ActionServletException("atencao.nao.numerico",
                    null,"Quantidade de Dias de Consumo");    
        }   
        
        if(Util.validarValorDiferenteZero(numeroLimiteImoveis)){
            //Numero limite de Im�veis n�o num�rico ou zero.
            httpServletRequest.setAttribute("nomeCampo","numeroLimiteImoveis");
            throw new ActionServletException("atencao.nao.numerico",
                    null,"Limite de Im�veis por Rota");  
        }
	
		 //O c�digo da rota � obrigat�rio. 
		if (( codigoRota == null) || (codigoRota.equals(""))){ 
			httpServletRequest.setAttribute("nomeCampo","codigoRota");
			throw new ActionServletException(
				"atencao.rota_codigo_nao_informado"); 
		}

		// O grupo de faturamento � obrigat�rio.
		if ((idFaturamentoGrupo == null)
				|| (idFaturamentoGrupo.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			httpServletRequest.setAttribute("nomeCampo","faturamentoGrupo");
			throw new ActionServletException(
					"atencao.faturamento_grupo_nao_informado");
		}
		
		// O grupo de cobran�a � obrigat�rio.
		if ((idCobrancaGrupo == null)
				|| (idCobrancaGrupo.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			httpServletRequest.setAttribute("nomeCampo","cobrancaGrupo");
			throw new ActionServletException(
					"atencao.cobranca_grupo_nao_informado");
		}

		// O tipo de leitura � obrigat�rio.
		if ((idLeituraTipo == null)
				|| (idLeituraTipo.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			httpServletRequest.setAttribute("nomeCampo","leituraTipo");
			throw new ActionServletException("atencao.leitura_tipo_nao_informado");
		}

		// A empresa leituristica � obrigat�ria.
		if ((idEmpresaLeituristica == null)
				|| (idEmpresaLeituristica.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			httpServletRequest.setAttribute("nomeCampo","empresaLeituristica");
			throw new ActionServletException(
					"atencao.empresa_leituristica_nao_informado");
		}

		//A empresa de entrega das contas � obrigat�ria.
		if ((idEmpresaEntregaContas == null)
				|| (idEmpresaEntregaContas.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			httpServletRequest.setAttribute("nomeCampo","idEmpresaEntregaContas");
			throw new ActionServletException(
					"atencao.empresa_entrega_contas_nao_informado");
		}
		
		// O identificador de ajusta consumo � obrigat�rio.
		/*if ((indicadorAjusteConsumo == null)
				|| (indicadorAjusteConsumo.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			httpServletRequest.setAttribute("nomeCampo","indicadorAjusteConsumo");
			throw new ActionServletException(
					"atencao.ajusta_consumo_nao_informado");
		}
		*/
		
		// O identificador de fiscalizar cortado � obrigat�rio.
		if ((indicadorFiscalizarCortado == null)
				|| (indicadorFiscalizarCortado.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			httpServletRequest.setAttribute("nomeCampo","indicadorFiscalizarCortado");
			throw new ActionServletException(
					"atencao.fiscaliza_cortados_nao_informado");
		}

		// O identificador de fiscalizar suprido � obrigat�rio.
		if ((indicadorFiscalizarSuprimido == null)
				|| (indicadorFiscalizarSuprimido.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			httpServletRequest.setAttribute("nomeCampo","indicadorFiscalizarSuprimido");
			throw new ActionServletException(
					"atencao.fiscaliza_suprimidos_nao_informado");
		}
		
		// O identificador de Transmissao Offline � obrigat�rio.
		if ((indicadorTransmissaoOffline == null)
				|| (indicadorTransmissaoOffline.equals(""+ ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			httpServletRequest.setAttribute("nomeCampo","indicadorTransmissaoOffline");
			throw new ActionServletException(
					"atencao.indicador_transmissao_offline_nao_informado");
		}
		
		// O identificador de Transmissao Offline � obrigat�rio.
		if ((indicadorSequencialLeitura == null)
				|| (indicadorSequencialLeitura.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			httpServletRequest.setAttribute("nomeCampo",
					"indicadorSequencialLeitura");
			throw new ActionServletException(
					"atencao.campo_selecionado.obrigatorio",null,"Indicador Sequencial de Leitura");
		}
		
		// Sistema Parametro vai ser utilizado na valida��o de 
		//Percentual de Faixa Falsa e
		//Percentual de Fiscaliza��o de Leitura 
		FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
		
		Collection<SistemaParametro> collectionSistemaParametro = this.getFachada()
				.pesquisar(filtroSistemaParametro,
						SistemaParametro.class.getName());
		SistemaParametro sistemaParametro = collectionSistemaParametro.iterator().next();
		
		
		//O identificador de gerar faixa falsa � obrigat�rio.
		if ((indicadorGerarFalsaFaixa == null)
				|| (indicadorGerarFalsaFaixa.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			httpServletRequest.setAttribute("nomeCampo","indicadorGerarFalsaFaixa");
			throw new ActionServletException("atencao.gera_faixa_nao_informado");
		}else if((percentualGeracaoFaixaFalsa == null || percentualGeracaoFaixaFalsa.equalsIgnoreCase("")) && 
				indicadorGerarFalsaFaixa.equals("" + ConstantesSistema.SIM) 
				&& sistemaParametro.getIndicadorUsoFaixaFalsa().equals(SistemaParametro.INDICADOR_USO_FAIXA_FALSA_ROTA)) {
			// Percentual de Faixa Falsa � obrigat�rio
			//caso o indicador de gera��o de fiscaliza��o de leitura seja SIM e
			//o indicador de uso do percentual para gera��o de fiscaliza��o de leitura 
			//na tabela SISTEMA_PARAMETRO (PARM_ICUSOPERCENTUALFISCALIZACAOLEITURA) 
			//corresponda ao valor 2=USA PERCENTUAL DA ROTA
		
			httpServletRequest.setAttribute("nomeCampo","percentualGeracaoFaixaFalsa");
			throw new ActionServletException(
					"atencao.percentual_faixa_falsa_nao_informado");
		}

		
		// O identificador de gerar fiscaliza��o � obrigat�rio.
		if ((indicadorGerarFiscalizacao == null)
				|| (indicadorGerarFiscalizacao.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			httpServletRequest.setAttribute("nomeCampo","indicadorGerarFiscalizacao");
			throw new ActionServletException(
					"atencao.gera_fiscalizacao_leitura_nao_informado");
		}else if((percentualGeracaoFiscalizacao == null || percentualGeracaoFiscalizacao.equalsIgnoreCase("")) && 
				indicadorGerarFiscalizacao.equals("" + ConstantesSistema.SIM) 
				&& sistemaParametro.getIndicadorPercentualFiscalizacaoLeitura().equals(SistemaParametro.INDICADOR_PERCENTUAL_FISCALIZACAO_LEITURA_ROTA)) {
			// Percentual de Fiscaliza��o de Leitura � obrigat�rio
			// caso o indicador de gera��o de faixa falsa seja SIM e 
			//o indicador de uso do percentual para gera��o da faixa falsa 
			//na tabela SISTEMA_PARAMETRO (PARM_ICUSOPERCENTUALFAIXAFALSA) 
			//corresponda ao valor 2=USA PERCENTUAL DA ROTA
			httpServletRequest.setAttribute("nomeCampo","percentualGeracaoFiscalizacao");
			   throw new ActionServletException(
					"atencao.percentual_fiscalizacao_leitura_nao_informado");
		}
		

		//[FS0010] Verificar inexist�ncia de alguma a��o de cobran�a
		if (collectionRotaAcaoCriterioNovos == null){
			//� necess�rio informar o crit�rio de cobran�a da rota para todas as a��es de cobran�a
			throw new ActionServletException(
			"atencao.criterio_cobranca_rota.informar");
			
		}
		FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
		filtroCobrancaAcao.adicionarParametro(new ParametroSimples
				(FiltroCobrancaCriterio.INDICADOR_USO,ConstantesSistema.SIM));
		Collection<CobrancaGrupo> collectionCobrancaAcao = 
			this.getFachada().pesquisar(filtroCobrancaAcao, CobrancaAcao.class.getName());
		
		if (collectionRotaAcaoCriterioNovos.size() < collectionCobrancaAcao.size()){
			//� necess�rio informar o crit�rio de cobran�a da rota para todas as a��es de cobran�a
			throw new ActionServletException(
			"atencao.criterio_cobranca_rota.informar");	
		}
        
        // [FS0012] - Verificar leiturista na empresa informada
        if ( idLeituraTipo != null && !idLeituraTipo.equals( "" ) && idLeiturista != null && !idLeiturista.equals( "" )  ){
            FiltroLeiturista filtro = new FiltroLeiturista();
            filtro.adicionarParametro( new ParametroSimples( FiltroLeiturista.ID, idLeiturista ) );
            Collection<Leiturista> colLeiturista = this.getFachada().pesquisar( filtro, Leiturista.class.getName() );
            
            if ( colLeiturista.size() > 0 ){
                Leiturista leiturista = colLeiturista.iterator().next();
                
                // Se a empresa do leiturista for diferente da empresa informada
                if ( idEmpresaLeituristica != null && !idEmpresaLeituristica.equals( leiturista.getEmpresa().getId().toString() ) ){
                    throw new ActionServletException("atencao.leiturista.empresa_leitura");
                }
            } else {
                throw new ActionServletException("atencao.leiturista.informar");
            }
        }
        
        // [FS0013] - Verificar obrigat�ridade do leiturista para o tipo de leitura informado
        if ( (LeituraTipo.CELULAR_MOBILE.toString().equals( idLeituraTipo )||
				LeituraTipo.LEITURA_E_ENTRADA_SIMULTANEA.toString().equals( idLeituraTipo )||
				LeituraTipo.LEITURA_ANDROID.toString().equals( idLeituraTipo )||
				LeituraTipo.CELULAR_MOBILE_ANDROID.toString().equals( idLeituraTipo )) &&
             ( idLeiturista == null || idLeiturista.equals( "" ) ) ){
            FiltroLeituraTipo filtro = new FiltroLeituraTipo();
            filtro.adicionarParametro( new ParametroSimples( FiltroLeituraTipo.ID, idLeituraTipo ) );
            Collection<LeituraTipo> colLeituraTipo = this.getFachada().pesquisar( filtro, LeituraTipo.class.getName() );
            LeituraTipo leituraTipo = colLeituraTipo.iterator().next();       
            
            throw new ActionServletException("atencao.leiturista.tipo_leitura", null, leituraTipo.getDescricao() );
        }
        
		// [FS0013] ? Validar Limite de  Divis�o de Im�veis por Tipo de Leitura.
		if(numeroLimiteImoveis != null && !numeroLimiteImoveis.equals("") 
				&& !LeituraTipo.LEITURA_E_ENTRADA_SIMULTANEA.toString().equals( idLeituraTipo )
				&& !LeituraTipo.CELULAR_MOBILE_ANDROID.toString().equals( idLeituraTipo )
				&& !LeituraTipo.LEITURA_ANDROID.toString().equals( idLeituraTipo )){
			
			form.setNumeroLimiteImoveis("");
			throw new ActionServletException("atencao.tipo_leitura_divisao_rota", null, "");
		}
	
		// Caso o usu�rio tenha alterado a localidade/setor comercial e tenha alguma quadra associada a rota, n�o dever� permitir
		FiltroRota filtroRota = new FiltroRota();
		filtroRota.adicionarCaminhoParaCarregamentoEntidade("setorComercial.localidade");
		filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, idRota));
		
		
		Collection<Rota> colecaoRota = this.getFachada().pesquisar( filtroRota, Rota.class.getName() );
		Rota rota = colecaoRota.iterator().next();
		
		if (rota.getSetorComercial().getLocalidade().getId().intValue() != new Integer(idLocalidade).intValue() || 
			rota.getSetorComercial().getCodigo() != new Integer(codigoSetorComercial).intValue()) {
		
			Integer qtdQuadras = this.getFachada().pesqisarQuantidadeQuadrasDaRota(new Integer(idRota), null, null);
			
			if (qtdQuadras != null && qtdQuadras.intValue() > 0) {
				throw new ActionServletException("atencao.atualizacao.rota_quadra_associada", null, "");
			}
			
			
		}
		
    }

    
    
    private void verificaExistenciaCodSetorComercial(String idDigitadoEnterLocalidade,
			String codigoDigitadoEnterSetorComercial,HttpServletRequest httpServletRequest) {

	//Verifica se o c�digo do Setor Comercial foi digitado
	if ((codigoDigitadoEnterSetorComercial != null && !codigoDigitadoEnterSetorComercial.toString()
			.trim().equalsIgnoreCase(""))&& (idDigitadoEnterLocalidade != null && 
			!idDigitadoEnterLocalidade.toString().trim().equalsIgnoreCase(""))) {
	
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		if (idDigitadoEnterLocalidade != null
			&& !idDigitadoEnterLocalidade.toString().trim().equalsIgnoreCase("")) {
			
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
			FiltroSetorComercial.ID_LOCALIDADE, new Integer(
			idDigitadoEnterLocalidade)));
		}
		
		filtroSetorComercial.adicionarParametro(new ParametroSimples(
		FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, new Integer(
		codigoDigitadoEnterSetorComercial)));
		
		Collection<SetorComercial> setorComerciais = this.getFachada().pesquisar(
		filtroSetorComercial, SetorComercial.class.getName());
		
		
		if (setorComerciais == null || setorComerciais.isEmpty()) {
			//o setor comercial n�o foi encontrado
			//Setor Comercial n�o existe para esta localidade
			httpServletRequest.setAttribute("nomeCampo","codigoSetorComercial");
			throw new ActionServletException(
			"atencao.setor_comercial_nao_existe");				
		
		}
		
	
	}
	
	}

    
    private boolean verificaExistenciaQuadraAtivaParaRota(String idRota){
    	
    	boolean retorno = false;

    	FiltroQuadra filtroQuadra = new FiltroQuadra();
    	filtroQuadra.adicionarParametro(
    		new ParametroSimples(
    			FiltroQuadra.ROTA_ID,
    			idRota));
    	filtroQuadra.adicionarParametro(
       		new ParametroSimples(
       			FiltroQuadra.INDICADORUSO,
       			ConstantesSistema.INDICADOR_USO_ATIVO));
    	
    	Collection colecaoQuadra = this.getFachada().pesquisar(
    			filtroQuadra, Quadra.class.getName());
    	if(colecaoQuadra != null && !colecaoQuadra.isEmpty()){
    		retorno = true;
    	}
    	
    	return retorno;
    }


}
 