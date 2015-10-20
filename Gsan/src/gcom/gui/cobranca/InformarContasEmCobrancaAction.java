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
package gcom.gui.cobranca;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CmdEmpresaCobrancaContaLigacaoAguaSituacao;
import gcom.cobranca.CmdEmpresaCobrancaContaLigacaoAguaSituacaoPK;
import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.ComandoEmpresaCobrancaConta;
import gcom.cobranca.ComandoEmpresaCobrancaContaGerencia;
import gcom.cobranca.ComandoEmpresaCobrancaContaGerenciaPK;
import gcom.cobranca.ComandoEmpresaCobrancaContaImovelPerfil;
import gcom.cobranca.ComandoEmpresaCobrancaContaImovelPerfilPK;
import gcom.cobranca.ComandoEmpresaCobrancaContaSetorComercial;
import gcom.cobranca.ComandoEmpresaCobrancaContaSetorComercialPK;
import gcom.cobranca.ComandoEmpresaCobrancaContaUnidadeNegocio;
import gcom.cobranca.ComandoEmpresaCobrancaContaUnidadeNegocioPK;
import gcom.cobranca.FiltroCobrancaSituacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action utilizado para inserir uma resolu��o de diretoria no banco
 * 
 * [UC0217] Inserir Resolu��o de Diretoria Permite inserir uma
 * ResolucaoDiretoria
 * 
 * @author Rafael Corr�a
 * @since 30/03/2006
 */
public class InformarContasEmCobrancaAction extends GcomAction {

	
	private SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
	/**
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

		Fachada fachada = Fachada.getInstancia();

		InformarContasEmCobrancaActionForm informarContasEmCobrancaActionForm = (InformarContasEmCobrancaActionForm) actionForm;

		String idImovel = informarContasEmCobrancaActionForm.getIdImovel();
		String idCliente = informarContasEmCobrancaActionForm.getIdCliente();
		Integer idCobrancaSituacao = fachada.pesquisarCobrancaSituacao(
				CobrancaSituacao.COBRANCA_EMPRESA_TERCEIRIZADA);
		String idServicoTipo = informarContasEmCobrancaActionForm.getIdServicoTipo();
		String[] idsCategoria = informarContasEmCobrancaActionForm.getIdsCategoria();
		String idUnidadeNegocio = null;
		String[] idsUnidadeNegocio = null;
		if (informarContasEmCobrancaActionForm.getIdsUnidadeNegocio() != null
				&& informarContasEmCobrancaActionForm.getIdsUnidadeNegocio().length > 0) {
			if (informarContasEmCobrancaActionForm.getIdsUnidadeNegocio().length == 1) {
				idUnidadeNegocio = informarContasEmCobrancaActionForm.getIdsUnidadeNegocio()[0];
			} else {
				idsUnidadeNegocio = informarContasEmCobrancaActionForm.getIdsUnidadeNegocio();
			}
		}
		String idGerenciaRegional = null;
		String[] idsGerenciaRegional = null;
		if (informarContasEmCobrancaActionForm.getIdsGerenciaRegional() != null
				&& informarContasEmCobrancaActionForm.getIdsGerenciaRegional().length > 0) {
			if (informarContasEmCobrancaActionForm.getIdsGerenciaRegional().length == 1) {
				idGerenciaRegional = informarContasEmCobrancaActionForm.getIdsGerenciaRegional()[0];
			} else {
				idsGerenciaRegional = informarContasEmCobrancaActionForm.getIdsGerenciaRegional();
			}
		} 
		String idImovelPerfil = null;
		String[] idsImovelPerfil = null;
		if (informarContasEmCobrancaActionForm.getIdsImovelPerfil() != null
				&& informarContasEmCobrancaActionForm.getIdsImovelPerfil().length > 0) {
			if (informarContasEmCobrancaActionForm.getIdsImovelPerfil().length == 1) {
				idImovelPerfil = informarContasEmCobrancaActionForm.getIdsImovelPerfil()[0];
			} else {
				idsImovelPerfil = informarContasEmCobrancaActionForm.getIdsImovelPerfil();
			}
		} 
		String idLigacaoAguaSituacao = null;
		String[] idsLigacaoAguaSituacao = null;
		if (informarContasEmCobrancaActionForm.getIdsLigacaoAguaSituacao() != null
				&& informarContasEmCobrancaActionForm.getIdsLigacaoAguaSituacao().length > 0) {
			if (informarContasEmCobrancaActionForm.getIdsLigacaoAguaSituacao().length == 1) {
				idLigacaoAguaSituacao = informarContasEmCobrancaActionForm.getIdsLigacaoAguaSituacao()[0];
			} else {
				idsLigacaoAguaSituacao = informarContasEmCobrancaActionForm.getIdsLigacaoAguaSituacao();
			}
		}
		String idLocalidadeInicial = informarContasEmCobrancaActionForm.getIdLocalidadeOrigem();
		String idLocalidadeFinal = informarContasEmCobrancaActionForm.getIdLocalidadeDestino();
		String codigoSetorComercialInicial = informarContasEmCobrancaActionForm.getCodigoSetorComercialOrigem();
		String codigoSetorComercialFinal = informarContasEmCobrancaActionForm.getCodigoSetorComercialDestino();
		String referenciaInicial = informarContasEmCobrancaActionForm.getReferenciaInicial();
		String referenciaFinal = informarContasEmCobrancaActionForm.getReferenciaFinal();
		String dataVencimentoInicial = informarContasEmCobrancaActionForm.getDataVencimentoInicial();
		String dataVencimentoFinal = informarContasEmCobrancaActionForm.getDataVencimentoFinal();
		
		String idLocalidade = informarContasEmCobrancaActionForm.getIdLocalidade();
		String setoresSelecionadosComponent = informarContasEmCobrancaActionForm.getSetoresSelecionadosComponent();
		Collection<SetorComercial> setoresComponent = new ArrayList<SetorComercial>();
		
		String valorMaximo = null;
		String valorMinimo = null;
		String valorMaximoDebito = null;
		String valorMinimoDebito = null;
		if(sistemaParametro.getIndicadorTotalDebito() == 1){
			valorMaximoDebito = informarContasEmCobrancaActionForm.getValorFinalDebito();
			valorMinimoDebito = informarContasEmCobrancaActionForm.getValorInicialDebito();
		}
		else{
			valorMaximo = informarContasEmCobrancaActionForm.getValorMaximo();
			valorMinimo = informarContasEmCobrancaActionForm.getValorMinimo();
		}
		String quantidadeContasInicial = informarContasEmCobrancaActionForm.getQuantidadeContasInicial();
		String quantidadeContasFinal = informarContasEmCobrancaActionForm.getQuantidadeContasFinal();
		String idEmpresa = informarContasEmCobrancaActionForm.getIdEmpresa();

		String quantidadeDiasVencimento = informarContasEmCobrancaActionForm.getQuantidadeDiasVencimento();

		String codigoQuadraInicial = informarContasEmCobrancaActionForm.getCodigoQuadraInicial();
		String codigoQuadraFinal = informarContasEmCobrancaActionForm.getCodigoQuadraFinal();
		String dataInicioCiclo = informarContasEmCobrancaActionForm.getDataInicioCiclo();
		String quantidadeDiasCiclo = informarContasEmCobrancaActionForm.getQuantidadeDiasCiclo();
		
		// Cria um comando de empresa de cobran�a da conta setando os valores informados pelo
		// usu�rio na pagina para ser inserido no banco
		ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta = new ComandoEmpresaCobrancaConta();
		
		comandoEmpresaCobrancaConta.setIndicadorResidencial(ConstantesSistema.NAO.intValue());
		comandoEmpresaCobrancaConta.setIndicadorComercial(ConstantesSistema.NAO.intValue());
		comandoEmpresaCobrancaConta.setIndicadorIndustrial(ConstantesSistema.NAO.intValue());
		comandoEmpresaCobrancaConta.setIndicadorPublico(ConstantesSistema.NAO.intValue());
		
		// Im�vel
		if (idImovel != null && !idImovel.trim().equals("")) {
			
			Imovel imovel = fachada.pesquisarImovelDigitado(new Integer(idImovel));
			
			if (imovel != null) {
				comandoEmpresaCobrancaConta.setImovel(imovel);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null, "Im�vel");
			}
			
		}
		
		// Cliente
		if (idCliente != null && !idCliente.trim().equals("")) {
			
			Cliente cliente = fachada.pesquisarClienteDigitado(new Integer(idCliente));
			
			if (cliente != null) {
				comandoEmpresaCobrancaConta.setCliente(cliente);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null, "Cliente");
			}
			
		}
		
		if (idsCategoria != null) {
			
			for (int i = 0; i < idsCategoria.length; i++) {
				
				if (idsCategoria[i].equals(Categoria.COMERCIAL.toString())) {
					comandoEmpresaCobrancaConta.setIndicadorComercial(ConstantesSistema.SIM.intValue());
				} else if (idsCategoria[i].equals(Categoria.INDUSTRIAL.toString())) {
					comandoEmpresaCobrancaConta.setIndicadorIndustrial(ConstantesSistema.SIM.intValue());
				} else if (idsCategoria[i].equals(Categoria.RESIDENCIAL.toString())) {
					comandoEmpresaCobrancaConta.setIndicadorResidencial(ConstantesSistema.SIM.intValue());
				} else if (idsCategoria[i].equals(Categoria.PUBLICO.toString())) {
					comandoEmpresaCobrancaConta.setIndicadorPublico(ConstantesSistema.SIM.intValue());
				}
				
			}
		}
		
		// Unidade Neg�cio
		if (idUnidadeNegocio != null && !idUnidadeNegocio.trim().equals("") && !idUnidadeNegocio.trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			UnidadeNegocio unidadeNegocio = new UnidadeNegocio();
			unidadeNegocio.setId(new Integer(idUnidadeNegocio));
			
			comandoEmpresaCobrancaConta.setUnidadeNegocio(unidadeNegocio);
		}
		
		// Ger�ncia Regional
		if (idGerenciaRegional != null && !idGerenciaRegional.trim().equals("") && !idGerenciaRegional.trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			GerenciaRegional gerenciaRegional = new GerenciaRegional();
			gerenciaRegional.setId(new Integer(idGerenciaRegional));
			
			comandoEmpresaCobrancaConta.setGerenciaRegional(gerenciaRegional);
		}
		
		// Im�vel Perfil
		if (idImovelPerfil != null && !idImovelPerfil.trim().equals("") && !idImovelPerfil.trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			ImovelPerfil imovelPerfil = new ImovelPerfil();
			imovelPerfil.setId(new Integer(idImovelPerfil));
			
			comandoEmpresaCobrancaConta.setImovelPerfil(imovelPerfil);
		}
		
		// Situa��o de Liga��o de �gua
		if (idLigacaoAguaSituacao != null && !idLigacaoAguaSituacao.trim().equals("") && !idLigacaoAguaSituacao.trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
			ligacaoAguaSituacao.setId(new Integer(idLigacaoAguaSituacao));
			
			comandoEmpresaCobrancaConta.setLigacaoAguaSituacao(ligacaoAguaSituacao);
		}
		
		//Localidade do component
		if(idLocalidade !=null && !idLocalidade.trim().equals("")){
			if(setoresSelecionadosComponent !=null && !setoresSelecionadosComponent.trim().equals("")){
				String [] set = setoresSelecionadosComponent.split(",");
				for(int i = 0;  i < set.length; i++){
					FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
					filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, idLocalidade));
					filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, set[i]));
					filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade(FiltroSetorComercial.LOCALIDADE);
					Collection<SetorComercial> colecaoSetorComercial = Fachada.getInstancia().pesquisar(filtroSetorComercial,SetorComercial.class.getName());

					if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {
						setoresComponent.add((SetorComercial)Util.retonarObjetoDeColecao(colecaoSetorComercial));
					}
				}
			}
			else{
				throw new ActionServletException("atencao.setor_comercial_nao_informado");
			}
		}
		
		// Localidade Inicial
		if (idLocalidadeInicial != null && !idLocalidadeInicial.trim().equals("")) {
			
			Localidade localidadeInicial = null;
			Localidade localidadeFinal = null;
			
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidadeInicial));
			
			Collection colecaoLocalidadeInicial = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			
			if (colecaoLocalidadeInicial != null && !colecaoLocalidadeInicial.isEmpty()) {
				localidadeInicial = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidadeInicial);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null, "Localidade Inicial");
			}
			
			filtroLocalidade.limparListaParametros();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidadeFinal));
			
			Collection colecaoLocalidadeFinal = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			
			if (colecaoLocalidadeFinal != null && !colecaoLocalidadeFinal.isEmpty()) {
				localidadeFinal = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidadeFinal);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null, "Localidade Final");
			}
			
			if (localidadeInicial.getId().compareTo(localidadeFinal.getId()) > 0) {
				throw new ActionServletException("atencao.localidade.final.maior.localidade.inicial");
			}
			
			comandoEmpresaCobrancaConta.setLocalidadeInicial(localidadeInicial);
			comandoEmpresaCobrancaConta.setLocalidadeFinal(localidadeFinal);
		}
		
		// Setor Comercial
		if (codigoSetorComercialInicial != null && !codigoSetorComercialInicial.trim().equals("")) {
			
			SetorComercial setorComericialInicial = null;
			SetorComercial setorComericialFinal = null;
			
			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, idLocalidadeInicial));
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercialInicial));
			
			Collection colecaoSetorComercialInicial = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
			
			if (colecaoSetorComercialInicial != null && !colecaoSetorComercialInicial.isEmpty()) {
				setorComericialInicial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercialInicial);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null, "Setor Comercial Inicial");
			}
			
			filtroSetorComercial.limparListaParametros();
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, idLocalidadeFinal));
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercialFinal));
			
			Collection colecaoSetorComercialFinal = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
			
			if (colecaoSetorComercialFinal != null && !colecaoSetorComercialFinal.isEmpty()) {
				setorComericialFinal = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercialFinal);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null, "Setor Comercial Final");
			}
			
			if (setorComericialInicial.getCodigo() > setorComericialFinal.getCodigo()) {
				throw new ActionServletException("atencao.setor.comercial.final.maior.setor.comercial.inicial");
			}
			
			comandoEmpresaCobrancaConta.setCodigoSetorComercialInicial(setorComericialInicial.getCodigo());
			comandoEmpresaCobrancaConta.setCodigoSetorComercialFinal(setorComericialFinal.getCodigo());
		}
		
		// Quadra
		if (codigoQuadraInicial != null && !codigoQuadraInicial.trim().equals("")) {
			
			Quadra quadraInicial = null;
			Quadra quadraFinal = null;
			
			FiltroQuadra filtroQuadra = new FiltroQuadra();
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, codigoQuadraInicial));
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, codigoSetorComercialInicial));
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, idLocalidadeInicial));
			
			Collection colecaoQuadraInicial = fachada.pesquisar(filtroQuadra, Quadra.class.getName());
			
			if (colecaoQuadraInicial != null && !colecaoQuadraInicial.isEmpty()) {
				quadraInicial = (Quadra) Util.retonarObjetoDeColecao(colecaoQuadraInicial);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null, "Quadra Inicial");
			}
			
			filtroQuadra.limparListaParametros();
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, codigoQuadraFinal));
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, codigoSetorComercialFinal));
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, idLocalidadeFinal));
			
			Collection colecaoQuadraFinal = fachada.pesquisar(filtroQuadra, Quadra.class.getName());
			
			if (colecaoQuadraFinal != null && !colecaoQuadraFinal.isEmpty()) {
				quadraFinal = (Quadra) Util.retonarObjetoDeColecao(colecaoQuadraFinal);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null, "Localidade Final");
			}
			
			if (quadraInicial.getId().compareTo(quadraFinal.getId()) > 0) {
				throw new ActionServletException("atencao.quadraInicial.maior.que.quadraFinal");
			}
			
			comandoEmpresaCobrancaConta.setQuadraInicial(quadraInicial);
			comandoEmpresaCobrancaConta.setNumeroQuadraInicial(quadraInicial.getNumeroQuadra());
			
			comandoEmpresaCobrancaConta.setQuadraFinal(quadraFinal);
			comandoEmpresaCobrancaConta.setNumeroQuadraFinal(quadraFinal.getNumeroQuadra());
		}
		
		// Refer�ncia
		if (referenciaInicial != null && !referenciaInicial.trim().equals("")) {
			
			Integer referenciaInicialFormatada = Util.formatarMesAnoComBarraParaAnoMes(referenciaInicial);
			Integer referenciaFinalFormatada = null;
			if(referenciaFinal != null && !referenciaFinal.equals("")){
				referenciaFinalFormatada = Util.formatarMesAnoComBarraParaAnoMes(referenciaFinal);
			}else{
				//SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
				referenciaFinalFormatada = sistemaParametro.getAnoMesArrecadacao();
			}
			
			if (referenciaInicialFormatada.compareTo(referenciaFinalFormatada) > 0) {
				throw new ActionServletException("atencao.referencia.final.menor.referencia.inicial");
			}
			
			comandoEmpresaCobrancaConta.setReferenciaContaInicial(referenciaInicialFormatada);
			comandoEmpresaCobrancaConta.setReferenciaContaFinal(referenciaFinalFormatada);
		}else{
			Integer referenciaInicialFormatada = 198001;
			Integer referenciaFinalFormatada = null;
			if (referenciaFinal == null || referenciaFinal.equals("")){
				//SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
				referenciaFinalFormatada = sistemaParametro.getAnoMesArrecadacao();
			}else{
				referenciaFinalFormatada = Util.formatarMesAnoComBarraParaAnoMes(referenciaFinal);	
			}
			
			if (referenciaInicialFormatada.compareTo(referenciaFinalFormatada) > 0) {
				throw new ActionServletException("atencao.referencia.final.menor.referencia.inicial");
			}
			
			comandoEmpresaCobrancaConta.setReferenciaContaInicial(referenciaInicialFormatada);
			comandoEmpresaCobrancaConta.setReferenciaContaFinal(referenciaFinalFormatada);
		}
		
		if (dataVencimentoInicial != null && !dataVencimentoInicial.trim().equals("")) {
			
			Date dataVencimentoInicialFormatada = Util.converteStringParaDate(dataVencimentoInicial);
			Date dataVencimentoFinalFormatada = Util.converteStringParaDate(dataVencimentoFinal);
			
			if (dataVencimentoInicialFormatada.compareTo(dataVencimentoFinalFormatada) > 0) {
				throw new ActionServletException("atencao.data.intervalo.invalido");
			}
			
			comandoEmpresaCobrancaConta.setDataVencimentoContaInicial(dataVencimentoInicialFormatada);
			comandoEmpresaCobrancaConta.setDataVencimentoContaFinal(dataVencimentoFinalFormatada);
		}
		
		if (valorMinimo != null && !valorMinimo.trim().equals("")) {
			BigDecimal valorMinimoFormatado = Util.formatarMoedaRealparaBigDecimal(valorMinimo);
			BigDecimal valorMaximoFormatado = Util.formatarMoedaRealparaBigDecimal(valorMaximo);

			if (valorMinimo.length() > 14) {
				throw new ActionServletException("atencao.tamanho.valor.minimo.invalido");
			}

			if (valorMaximo.length() > 14) {
				throw new ActionServletException("atencao.tamanho.valor.maximo.invalido");
			}
			//[FS0018]Verificar valor final do d�bito menor que valor inicial
			if (valorMinimoFormatado.compareTo(valorMaximoFormatado) > 0) {
				throw new ActionServletException("atencao.valorConta_final_maior_inicial");
			}
			
			comandoEmpresaCobrancaConta.setValorMinimoConta(valorMinimoFormatado);
			comandoEmpresaCobrancaConta.setValorMaximoConta(valorMaximoFormatado);
		}
		
		if(valorMinimoDebito !=null && !valorMinimoDebito.trim().equals("") &&
		   valorMaximoDebito !=null && !valorMaximoDebito.trim().equals("")){
			
			BigDecimal valorMinimoDebitoFormatado = Util.formatarMoedaRealparaBigDecimal(valorMinimoDebito);
			BigDecimal valorMaximoDebitoFormatado = Util.formatarMoedaRealparaBigDecimal(valorMaximoDebito);
			
			if(valorMinimoDebito.length() > 14){
				throw new ActionServletException("atencao.tamanho_valor_minimo_debito.invalido");
			}
			if(valorMaximoDebito.length() > 14){
				throw new ActionServletException("atencao.tamanho_valor_maximo_debito.invalido");
			}
			//[FS0018]Verificar valor final do d�bito menor que valor inicial
			if (valorMinimoDebitoFormatado.compareTo(valorMaximoDebitoFormatado) > 0) {
				throw new ActionServletException("atencao.valorDebito_final_maior_inicial");
			}
			comandoEmpresaCobrancaConta.setValorMinimoDebito(valorMinimoDebitoFormatado);
			comandoEmpresaCobrancaConta.setValorMaximoDebito(valorMaximoDebitoFormatado);
		}
		
		if (quantidadeContasInicial != null && !quantidadeContasInicial.trim().equals("")) {
			Integer qtdContasInicial = new Integer(quantidadeContasInicial);
			Integer qtdContasFinal = new Integer(quantidadeContasFinal);

			// [FS0017] ? Verificar quantidade de contas final menor que quantidade inicial
			if (qtdContasInicial.compareTo(qtdContasFinal) > 0) {
				throw new ActionServletException("atencao.quantidade.contas_final.menor.quantidade_inicial");
			}
			
			Integer quantidadeMenorFaixa = Fachada.getInstancia()
					.pesquisarQuantidadeContasMenorFaixa(new Integer(informarContasEmCobrancaActionForm.getIdEmpresa()));
			
			if (quantidadeMenorFaixa != null 
					&& quantidadeMenorFaixa.compareTo(qtdContasInicial) > 0) {

				throw new ActionServletException(
						"atencao.quantidade_inicial.inferior.quantidade_menor_faixa");
			}
			
			comandoEmpresaCobrancaConta.setQtdContasInicial(qtdContasInicial);
			comandoEmpresaCobrancaConta.setQtdContasFinal(qtdContasFinal);
		}
		
		if (quantidadeDiasVencimento != null && !quantidadeDiasVencimento.trim().equals("")) {
			Integer qtdDiasVencimento = new Integer(quantidadeDiasVencimento);

			comandoEmpresaCobrancaConta.setQtdDiasVencimento(qtdDiasVencimento);
		}
		
		
		if (idEmpresa != null && !idEmpresa.trim().equals("")) {
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, idEmpresa));
			
			Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
			
			if (colecaoEmpresa != null && !colecaoEmpresa.isEmpty()) {
				Empresa empresa = (Empresa) Util
						.retonarObjetoDeColecao(colecaoEmpresa);

				//[FS0011]- Verificar se empresa � de Cobranca
				if (empresa.getIndicadorEmpresaContratadaCobranca() != null
						&& empresa.getIndicadorEmpresaContratadaCobranca()
								.intValue() == Empresa.INDICADOR_EMPRESA_COBRANCA
								.intValue()) {

					comandoEmpresaCobrancaConta.setEmpresa(empresa);
				} else {
					throw new ActionServletException(
							"atencao.empresa_nao_cobranca");
				}

			}  else {
				throw new ActionServletException("atencao.naocadastrado", null, "Empresa");
			}
		}
		
		if (dataInicioCiclo != null && !dataInicioCiclo.trim().equals("")) {
			
			Date dataInicioCicloFormatada = Util.converteStringParaDate(dataInicioCiclo);
			
			if (quantidadeDiasCiclo != null && !quantidadeDiasCiclo.trim().equals("")) {
				Integer quantidadeDias = new Integer(quantidadeDiasCiclo);
				Date dataFimCicloFormatada = Util.adicionarNumeroDiasDeUmaData(dataInicioCicloFormatada, quantidadeDias);
				comandoEmpresaCobrancaConta.setDataFimCiclo(dataFimCicloFormatada);
			}
			
			comandoEmpresaCobrancaConta.setDataInicioCiclo(dataInicioCicloFormatada);
		}
		
		if (idCobrancaSituacao != null) {
			
			FiltroCobrancaSituacao filtroCobrancaSituacao = new FiltroCobrancaSituacao();
			filtroCobrancaSituacao.adicionarParametro(new ParametroSimples(
					FiltroCobrancaSituacao.ID, idCobrancaSituacao));
			
			Collection colecaoCobrancaSituacao = fachada.pesquisar(filtroCobrancaSituacao, CobrancaSituacao.class.getName());
			
			if (colecaoCobrancaSituacao != null && !colecaoCobrancaSituacao.isEmpty()) {
				CobrancaSituacao cobrancaSituacao = (CobrancaSituacao) Util.retonarObjetoDeColecao(colecaoCobrancaSituacao);
				
				comandoEmpresaCobrancaConta.setCobrancaSituacao(cobrancaSituacao);
			}
		}
		
		
		if (idServicoTipo != null && !idServicoTipo.trim().equals("")) {
			
			FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
			filtroServicoTipo.adicionarParametro(new ParametroSimples(
					FiltroServicoTipo.ID, idServicoTipo));
			
			Collection colecaoServicoTipo = fachada.pesquisar(filtroServicoTipo, ServicoTipo.class.getName());
			
			if (colecaoServicoTipo != null && !colecaoServicoTipo.isEmpty()) {
				ServicoTipo servicoTipo = (ServicoTipo) Util.retonarObjetoDeColecao(colecaoServicoTipo);
				
				comandoEmpresaCobrancaConta.setServicoTipo(servicoTipo);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null, "Tipo de Servi�o");
			}
		}
		
		comandoEmpresaCobrancaConta.setIndicadorGeracaoTxt(new Integer(2));

		// Insere um Comando de Cobran�a da Conta na base
		Integer idComandoEmpresaCobrancaConta = fachada.inserirComandoEmpresaCobrancaConta(
				comandoEmpresaCobrancaConta, this.getUsuarioLogado(httpServletRequest));		
		
		//Insere os setores selecionados pelo component
		if(setoresComponent !=null && !setoresComponent.isEmpty()){
			for(SetorComercial setorComercial : setoresComponent){
				ComandoEmpresaCobrancaContaSetorComercial cmsc = new ComandoEmpresaCobrancaContaSetorComercial();
				ComandoEmpresaCobrancaContaSetorComercialPK pk = new ComandoEmpresaCobrancaContaSetorComercialPK();
				pk.setIdComando(idComandoEmpresaCobrancaConta);
				pk.setIdSetor(setorComercial.getId());
				
				cmsc.setPk(pk);
				cmsc.setDataUltimaAlteracao(new Date());
				
				fachada.inserir(cmsc);				
			}
		}

		// Insere um Comando de Cobran�a da Conta por Unidade de Neg�cio na base
		if (idsUnidadeNegocio != null && idsUnidadeNegocio.length > 0) {
			for (int i = 0; i < idsUnidadeNegocio.length; i++) {
				if (!idsUnidadeNegocio[i].equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					ComandoEmpresaCobrancaContaUnidadeNegocio comandoEmpresaCobrancaContaUnidadeNegocio = new ComandoEmpresaCobrancaContaUnidadeNegocio();
					ComandoEmpresaCobrancaContaUnidadeNegocioPK pk = new ComandoEmpresaCobrancaContaUnidadeNegocioPK();
					
					pk.setUnidadeNegocioId(new Integer (idsUnidadeNegocio[i]));
					pk.setComandoEmpresaCobrancaContaId(idComandoEmpresaCobrancaConta);
					
					comandoEmpresaCobrancaContaUnidadeNegocio.setComp_id(pk);
					comandoEmpresaCobrancaContaUnidadeNegocio.setUltimaAlteracao(new Date());
					
					fachada.inserir(comandoEmpresaCobrancaContaUnidadeNegocio);
				}
			}
		}
		
		// Insere um Comando de Cobran�a da Conta por Ger�ncia Regional na base
		if (idsGerenciaRegional != null && idsGerenciaRegional.length > 0) {
			for (int i = 0; i < idsGerenciaRegional.length; i++) {
				if (!idsGerenciaRegional[i].equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					ComandoEmpresaCobrancaContaGerencia comandoEmpresaCobrancaContaGerencia = new ComandoEmpresaCobrancaContaGerencia();
					ComandoEmpresaCobrancaContaGerenciaPK pk = new ComandoEmpresaCobrancaContaGerenciaPK();
					
					pk.setGerenciaRegionalId(new Integer (idsGerenciaRegional[i]));
					pk.setComandoEmpresaCobrancaContaId(idComandoEmpresaCobrancaConta);
					
					comandoEmpresaCobrancaContaGerencia.setComp_id(pk);
					comandoEmpresaCobrancaContaGerencia.setUltimaAlteracao(new Date());
					
					fachada.inserir(comandoEmpresaCobrancaContaGerencia);
				}
			}
		}
		
		// Insere um Comando de Cobran�a da Conta por Imovel Perfil na base
		if (idsImovelPerfil != null && idsImovelPerfil.length > 0) {
			for (int i = 0; i < idsImovelPerfil.length; i++) {
				if (!idsImovelPerfil[i].equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					ComandoEmpresaCobrancaContaImovelPerfil comandoEmpresaCobrancaContaImovelPerfil = new ComandoEmpresaCobrancaContaImovelPerfil();
					ComandoEmpresaCobrancaContaImovelPerfilPK pk = new ComandoEmpresaCobrancaContaImovelPerfilPK();
					
					pk.setImovelPerfilId(new Integer (idsImovelPerfil[i]));
					pk.setComandoEmpresaCobrancaContaId(idComandoEmpresaCobrancaConta);
					
					comandoEmpresaCobrancaContaImovelPerfil.setComp_id(pk);
					comandoEmpresaCobrancaContaImovelPerfil.setUltimaAlteracao(new Date());
					
					fachada.inserir(comandoEmpresaCobrancaContaImovelPerfil);
				}
			}
		}
		
		// Insere um Comando de Cobran�a da Conta por Situa��o de Liga��o de �gua na base
		if (idsLigacaoAguaSituacao != null && idsLigacaoAguaSituacao.length > 0) {
			for (int i = 0; i < idsLigacaoAguaSituacao.length; i++) {
				if (!idsLigacaoAguaSituacao[i].equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					CmdEmpresaCobrancaContaLigacaoAguaSituacao cmdEmpresaCobrancaContaLigacaoAguaSituacao = new CmdEmpresaCobrancaContaLigacaoAguaSituacao();
					CmdEmpresaCobrancaContaLigacaoAguaSituacaoPK pk = new CmdEmpresaCobrancaContaLigacaoAguaSituacaoPK();
					
					pk.setLigacaoAguaSituacaoId(new Integer (idsLigacaoAguaSituacao[i]));
					pk.setComandoEmpresaCobrancaContaId(idComandoEmpresaCobrancaConta);
					
					cmdEmpresaCobrancaContaLigacaoAguaSituacao.setCels_id(pk);
					cmdEmpresaCobrancaContaLigacaoAguaSituacao.setUltimaAlteracao(new Date());
					
					fachada.inserir(cmdEmpresaCobrancaContaLigacaoAguaSituacao);
				}
			}
		}
		
		// Monta a p�gina de sucesso de acordo com o padr�o do sistema.
		montarPaginaSucesso(httpServletRequest, "Comando de cobran�a de conta informado com sucesso. ",
				"Informar outro Comando de Cobran�a de Conta",
				"exibirInformarContasEmCobrancaAction.do?menu=sim");

		return retorno;

	}

}