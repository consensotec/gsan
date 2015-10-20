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
 * Rômulo Aurélio de Melo Souza Filho
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
package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.LigacaoOrigem;
import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoPerfil;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoCaixaInspecao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDestinoAguasPluviais;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDestinoDejetos;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDiametro;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoEsgotamento;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoMaterial;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

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
 * Description of the Class
 * 
 * @author Leandro Cavalcanti
 * @created 20 de Junho de 2006
 */
public class EfetuarLigacaoEsgotoAction extends GcomAction {

	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		HttpSession sessao = httpServletRequest.getSession(false);

		EfetuarLigacaoEsgotoActionForm ligacaoEsgotoActionForm = (EfetuarLigacaoEsgotoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		String funcionalidade = (String) sessao.getAttribute("funcionalidade");
		
		this.getSessao(httpServletRequest).setAttribute(
			"funcionalidade", funcionalidade);
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		String ordemServicoId = ligacaoEsgotoActionForm.getIdOrdemServico();

		String materialLigacao = ligacaoEsgotoActionForm.getMaterialLigacao();
		String perfilLigacao = ligacaoEsgotoActionForm.getPerfilLigacao();
		String percentual = ligacaoEsgotoActionForm.getPercentualColeta()
				.toString().replace(",", ".");
		String percentualEsgoto = ligacaoEsgotoActionForm.getPercentualEsgoto()
				.toString().replace(",", ".");
		String idServicoMotivoNaoCobranca = ligacaoEsgotoActionForm
				.getMotivoNaoCobranca();
		String valorPercentual = ligacaoEsgotoActionForm
				.getPercentualCobranca();
		String indicadorCaixaGordura = ligacaoEsgotoActionForm
				.getIndicadorCaixaGordura();

		String ligacaoEsgotoEsgotamento = ligacaoEsgotoActionForm
				.getCondicaoEsgotamento();
		String ligacaoEsgotoDestinoDejetos = ligacaoEsgotoActionForm
				.getDestinoDejetos();
		String ligacaoEsgotoCaixaInspecao = ligacaoEsgotoActionForm
				.getSituacaoCaixaInspecao();
		String ligacaoEsgotoDestinoAguasPluviais = ligacaoEsgotoActionForm
				.getDestinoAguasPluviais();
		String idLigacaoOrigem = ligacaoEsgotoActionForm.getIdLigacaoOrigem();
		String idImovel = ligacaoEsgotoActionForm.getIdImovel();
		String indicadorLigacaoEsgoto = ligacaoEsgotoActionForm.getIndicadorLigacao();
		
		String matriculaImovel = ligacaoEsgotoActionForm
				.getMatriculaImovel();
		
		String dataLigacao = ligacaoEsgotoActionForm
				.getDataLigacao();
		
		Imovel imovel = null;
		
		LigacaoEsgoto ligacaoEsgoto = new LigacaoEsgoto();
		
		FiltroLigacaoEsgoto filtroLigacaoEsgoto = new FiltroLigacaoEsgoto();
		
		if (funcionalidade != null && funcionalidade.equals("semRA")){
			
			if (matriculaImovel != null && !matriculaImovel.equals("")) {

				imovel = new Imovel();
				imovel.setId(new Integer(matriculaImovel));

				ligacaoEsgoto.setImovel(imovel);
				
				Imovel im = fachada.pesquisarDadosImovel(Integer.parseInt(matriculaImovel));
				
				// [FS0017] Verificar Nível para instalação de esgoto
				if(sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAER) && 
						im.getIndicadorNivelInstalacaoEsgoto() != null && im.getIndicadorNivelInstalacaoEsgoto().equals(ConstantesSistema.NAO)){
					throw new ActionServletException("atencao.imovel_sem_nivel_instalacao_esgoto", null, matriculaImovel);
				}

				/*---------------------  InícioDados da Ligação Esgoto  ------------------------*/
				// lesg_iccaixagordura
				ligacaoEsgoto.setIndicadorCaixaGordura(new Short(
						indicadorCaixaGordura));
				ligacaoEsgoto.setIndicadorLigacaoEsgoto(new Short(indicadorLigacaoEsgoto));
				// lagu_tultimaalteracao
				ligacaoEsgoto.setUltimaAlteracao(new Date());
				// lest_id
				ligacaoEsgoto.setId(imovel.getId());
				// LEST_ID
				LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
				ligacaoEsgotoSituacao.setId(LigacaoEsgotoSituacao.LIGADO);
				// ligacaoEsgoto.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);

				String diametroLigacao = ligacaoEsgotoActionForm
						.getDiametroLigacao();
				if (diametroLigacao != null
						&& !diametroLigacao.equals("")
						&& !diametroLigacao.trim().equalsIgnoreCase(
								"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					LigacaoEsgotoDiametro ligacaoEsgotoDiametro = new LigacaoEsgotoDiametro();
					ligacaoEsgotoDiametro.setId(new Integer(diametroLigacao));
					ligacaoEsgoto.setLigacaoEsgotoDiametro(ligacaoEsgotoDiametro);
				} else {
					throw new ActionServletException(
							"atencao.informe_campo_obrigatorio", null,
							"Diametro da Ligação");
				}

				if (materialLigacao != null
						&& !materialLigacao.equals("")
						&& !materialLigacao.trim().equalsIgnoreCase(
								"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					LigacaoEsgotoMaterial ligacaoEsgotoMaterialMaterial = new LigacaoEsgotoMaterial();
					ligacaoEsgotoMaterialMaterial
							.setId(new Integer(materialLigacao));
					ligacaoEsgoto
							.setLigacaoEsgotoMaterial(ligacaoEsgotoMaterialMaterial);
				} else {
					throw new ActionServletException(
							"atencao.informe_campo_obrigatorio", null,
							"Material da Ligação");
				}

				if (perfilLigacao != null
						&& !perfilLigacao.equals("")
						&& !perfilLigacao.trim().equalsIgnoreCase(
								"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					LigacaoEsgotoPerfil ligacaoEsgotoPerfil = new LigacaoEsgotoPerfil();
					ligacaoEsgotoPerfil.setId(new Integer(perfilLigacao));
					ligacaoEsgoto.setLigacaoEsgotoPerfil(ligacaoEsgotoPerfil);
				} else {
					throw new ActionServletException(
							"atencao.informe_campo_obrigatorio", null,
							"Perfil da Ligação");
				}
				// item 4.5 - [FS006] caso 1,3
				if (percentual != null && !percentual.equals("")) {

					BigDecimal percentualInformadoColeta = new BigDecimal(
							percentual);
					if (percentualInformadoColeta != null
							&& !percentualInformadoColeta.equals("")
							&& (percentualInformadoColeta.intValue() <= ConstantesSistema.NUMERO_MAXIMO_CONSUMO_MINIMO_FIXADO)) {
						ligacaoEsgoto
								.setPercentualAguaConsumidaColetada(percentualInformadoColeta);
					}
				} else {
					throw new ActionServletException(
							"atencao.informe_campo_obrigatorio", null,
							"Percentual de Coleta");
				}

				if (percentualEsgoto != null && !percentualEsgoto.equals("")) {

					BigDecimal percentualEsgotoColeta = new BigDecimal(
							percentualEsgoto);
					ligacaoEsgoto.setPercentual(percentualEsgotoColeta);
				}

				if (dataLigacao != null && !dataLigacao.equals("")) {
					ligacaoEsgoto.setDataLigacao(Util
							.converteStringParaDate(dataLigacao));
					if (ligacaoEsgoto.getDataLigacao().after(new Date())) {
						throw new ActionServletException(
								"atencao.data_menor_que_atual", null, "Ligação");
					}
				}
				
				// Condicação do Esgotamento
				if (ligacaoEsgotoEsgotamento != null
						&& Integer.parseInt(ligacaoEsgotoEsgotamento) != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					LigacaoEsgotoEsgotamento le = new LigacaoEsgotoEsgotamento();
					le.setId(Integer.parseInt(ligacaoEsgotoEsgotamento));
					ligacaoEsgoto.setLigacaoEsgotoEsgotamento(le);
				}

				// Destino dos dejetos
				if (ligacaoEsgotoDestinoDejetos != null
						&& Integer.parseInt(ligacaoEsgotoDestinoDejetos) != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					LigacaoEsgotoDestinoDejetos ledd = new LigacaoEsgotoDestinoDejetos();
					ledd.setId(Integer.parseInt(ligacaoEsgotoDestinoDejetos));
					ligacaoEsgoto.setLigacaoEsgotoDestinoDejetos(ledd);
				}

				// Situacao da caixa de inspecao
				if (ligacaoEsgotoCaixaInspecao != null
						&& Integer.parseInt(ligacaoEsgotoCaixaInspecao) != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					LigacaoEsgotoCaixaInspecao leci = new LigacaoEsgotoCaixaInspecao();
					leci.setId(Integer.parseInt(ligacaoEsgotoCaixaInspecao));
					ligacaoEsgoto.setLigacaoEsgotoCaixaInspecao(leci);
				}

				// Destino das Aguas Pluviais
				if (ligacaoEsgotoDestinoAguasPluviais != null
						&& Integer.parseInt(ligacaoEsgotoDestinoAguasPluviais) != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					LigacaoEsgotoDestinoAguasPluviais ledap = new LigacaoEsgotoDestinoAguasPluviais();
					ledap
							.setId(Integer
									.parseInt(ligacaoEsgotoDestinoAguasPluviais));
					ligacaoEsgoto.setLigacaoEsgotoDestinoAguasPluviais(ledap);
				}
				
				
				if(Util.verificarNaoVazio(ligacaoEsgotoActionForm.getLimiteConsumoAlternativo()) 
						|| Util.verificarNaoVazio(ligacaoEsgotoActionForm.getPercentualAlternativoEsgoto()))
				{
					if(!Util.verificarNaoVazio(ligacaoEsgotoActionForm.getLimiteConsumoAlternativo()))
					{
						throw new ActionServletException("atencao.required", "Limite de Consumo Alternativo");
					}
					
					if(!Util.validarNumeroMaiorQueZERO(ligacaoEsgotoActionForm.getLimiteConsumoAlternativo()))
					{
						throw new ActionServletException("atencao.invalid", "Limite de Consumo Alternativo");
					}
					
					if(!Util.verificarNaoVazio(ligacaoEsgotoActionForm.getPercentualAlternativoEsgoto()))
					{
						throw new ActionServletException("atencao.required", "Percentual Alternativo de Esgoto");
					}
					
					if(Util.validarValorNaoNumericoComoBigDecimal(ligacaoEsgotoActionForm.getPercentualAlternativoEsgoto().replace(",", ".")))
					{
						throw new ActionServletException("atencao.invalid", "Percentual Alternativo de Esgoto");
					}
					
					if(Util.formatarMoedaRealparaBigDecimal(ligacaoEsgotoActionForm.getPercentualAlternativoEsgoto()).compareTo(new BigDecimal("100.00"))==1)
					{
						throw new ActionServletException("atencao.percentual.alternativo.esgoto", "Percentual Alternativo de Esgoto");
					}
					
					ligacaoEsgoto.setNumeroConsumoPercentualAlternativo(Integer.valueOf(ligacaoEsgotoActionForm.getLimiteConsumoAlternativo()));
					ligacaoEsgoto.setPercentualAlternativo(Util.formatarMoedaRealparaBigDecimal(ligacaoEsgotoActionForm.getPercentualAlternativoEsgoto()));
				}
				

				/*
				 * [UC0107] Registrar Transação
				 * 
				 */

				RegistradorOperacao registradorOperacao = new RegistradorOperacao(
						Operacao.OPERACAO_LIGACAO_ESGOTO__SEM_RA_EFETUAR,
						imovel.getId(), imovel.getId(),
						new UsuarioAcaoUsuarioHelper(usuario,
								UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

				// [UC0107] -Fim- Registrar Transação
				
				registradorOperacao.registrarOperacao(ligacaoEsgoto);
				
				fachada.atualizarImovelExecucaoOrdemServicoLigacaoEsgoto(imovel,
						ligacaoEsgotoSituacao);

				filtroLigacaoEsgoto.adicionarParametro(new ParametroSimples(
						FiltroLigacaoEsgoto.ID, imovel.getId()));
				Collection colecaoLigacaoEsgotoBase = fachada.pesquisar(
						filtroLigacaoEsgoto, LigacaoEsgoto.class.getName());

				if (colecaoLigacaoEsgotoBase != null
						&& !colecaoLigacaoEsgotoBase.isEmpty()) {
					fachada.atualizar(ligacaoEsgoto);
				} else {
					fachada.inserir(ligacaoEsgoto);
				}

				montarPaginaSucesso(httpServletRequest,
						"Ligação de Esgoto sem RA efetuada com Sucesso",
						"Efetuar outra Ligação de Esgoto sem RA",
						"exibirEfetuarLigacaoEsgotoAction.do?menu=sim&funcionalidade=semRA");
				
				ligacaoEsgotoActionForm = null;

				return retorno;
			} else {
				throw new ActionServletException(
						"atencao.informe_campo_obrigatorio", null,
						"Matrícula Imóvel");
			}
		} else if (funcionalidade == null || funcionalidade.equals("comRA")){
		
		if (sessao.getAttribute("imovel") != null) {
			imovel = (Imovel) sessao.getAttribute("imovel");
			filtroLigacaoEsgoto.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgoto.ID, imovel.getId()));
		} else if (idImovel != null && !idImovel.trim().equals("")) {
			filtroLigacaoEsgoto.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgoto.ID, idImovel));
		}
		
		Collection colecaoLigacaoEsgoto = fachada.pesquisar(filtroLigacaoEsgoto, LigacaoEsgoto.class.getName());
		
		if (colecaoLigacaoEsgoto != null && !colecaoLigacaoEsgoto.isEmpty()) {
			ligacaoEsgoto = (LigacaoEsgoto) Util.retonarObjetoDeColecao(colecaoLigacaoEsgoto);
		}

		if (idImovel != null && !idImovel.equalsIgnoreCase("")) {

			imovel = new Imovel();
			imovel.setId(new Integer(idImovel));
			imovel.setUltimaAlteracao(new Date());

			/*---------------------  InícioDados da Ligação Esgoto  ------------------------*/
			// lesg_iccaixagordura
			ligacaoEsgoto.setIndicadorCaixaGordura(new Short(
					indicadorCaixaGordura));
			
			ligacaoEsgoto.setIndicadorLigacaoEsgoto(new Short(indicadorLigacaoEsgoto));
			// lagu_tultimaalteracao
			ligacaoEsgoto.setUltimaAlteracao(new Date());
			// lest_id
			ligacaoEsgoto.setId(imovel.getId());
			// LEST_ID
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
			ligacaoEsgotoSituacao.setId(LigacaoEsgotoSituacao.LIGADO);
			// ligacaoEsgoto.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);

			String diametroLigacao = ligacaoEsgotoActionForm
					.getDiametroLigacao();
			if (diametroLigacao != null
					&& !diametroLigacao.equals("")
					&& !diametroLigacao.trim().equalsIgnoreCase(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				LigacaoEsgotoDiametro ligacaoEsgotoDiametro = new LigacaoEsgotoDiametro();
				ligacaoEsgotoDiametro.setId(new Integer(diametroLigacao));
				ligacaoEsgoto.setLigacaoEsgotoDiametro(ligacaoEsgotoDiametro);
			} else {
				throw new ActionServletException(
						"atencao.informe_campo_obrigatorio", null,
						"Diametro da Ligação");
			}
			if (ligacaoEsgotoActionForm.getDataLigacao() != null
					&& !ligacaoEsgotoActionForm.getDataLigacao().equals("")) {

				Date data = Util.converteStringParaDate(ligacaoEsgotoActionForm
						.getDataLigacao());
				ligacaoEsgoto.setDataLigacao(data);
			} else {
				throw new ActionServletException("atencao.informe_campo", null,
						" Data da Ligação");
			}

			if (materialLigacao != null
					&& !materialLigacao.equals("")
					&& !materialLigacao.trim().equalsIgnoreCase(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				LigacaoEsgotoMaterial ligacaoEsgotoMaterialMaterial = new LigacaoEsgotoMaterial();
				ligacaoEsgotoMaterialMaterial
						.setId(new Integer(materialLigacao));
				ligacaoEsgoto
						.setLigacaoEsgotoMaterial(ligacaoEsgotoMaterialMaterial);
			} else {
				throw new ActionServletException(
						"atencao.informe_campo_obrigatorio", null,
						"Material da Ligação");
			}

			if (perfilLigacao != null && !perfilLigacao.equals("") && 
				!perfilLigacao.trim().equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				
				FiltroLigacaoEsgotoPerfil filtroLigacaoEsgotoPerfil = new FiltroLigacaoEsgotoPerfil();
				filtroLigacaoEsgotoPerfil.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgotoPerfil.ID, 
						new Integer(perfilLigacao)));
				
				Collection colecaoLigacaoEsgotoPerfil = fachada.pesquisar(filtroLigacaoEsgotoPerfil, LigacaoEsgotoPerfil.class.getName());
				LigacaoEsgotoPerfil ligacaoEsgotoPerfil = (LigacaoEsgotoPerfil) Util.retonarObjetoDeColecao(colecaoLigacaoEsgotoPerfil);
				ligacaoEsgoto.setLigacaoEsgotoPerfil(ligacaoEsgotoPerfil);
			} 
			else {
				
				throw new ActionServletException(
						"atencao.informe_campo_obrigatorio", null,
						"Perfil da Ligação");
			}
			// item 4.5 - [FS006] caso 1,3
			if (percentual != null && !percentual.equals("")) {

				BigDecimal percentualInformadoColeta = Util.formatarStringParaBigDecimal(percentual);
				
				if (percentualInformadoColeta != null &&
					percentualInformadoColeta.compareTo(ligacaoEsgoto.getLigacaoEsgotoPerfil().getPercentualMaximoColeta()) <= 0) {
					
					ligacaoEsgoto.setPercentualAguaConsumidaColetada(percentualInformadoColeta);
				}
				else{
					
					throw new ActionServletException("atencao.atualizar_percentual_informado_maior_maximo_permitido", null, 
							Util.formatarBigDecimalComPonto(ligacaoEsgoto.getLigacaoEsgotoPerfil().getPercentualMaximoColeta()) + "%");
				}
			} else {
				throw new ActionServletException(
						"atencao.informe_campo_obrigatorio", null,
						"Percentual de Coleta");
			}
			
			Imovel im = fachada.pesquisarDadosImovel(Integer.parseInt(idImovel));
			
			// [FS0017] Verificar Nível para instalação de esgoto
			if(sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAER) && 
					im.getIndicadorNivelInstalacaoEsgoto() != null && im.getIndicadorNivelInstalacaoEsgoto().equals(ConstantesSistema.NAO)){
				throw new ActionServletException("atencao.imovel_associado_os_sem_nivel_instalacao_esgoto", null, idImovel);
			}

			// Colocado por Rômulo Aurélio Analista: Sávio Luiz Data:03/09/2008
			// Ligacao Origem
			if (idLigacaoOrigem != null
					&& !idLigacaoOrigem.equals("")
					&& !idLigacaoOrigem.trim().equalsIgnoreCase(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

				LigacaoOrigem ligacaoOrigem = new LigacaoOrigem();
				ligacaoOrigem.setId(new Integer(idLigacaoOrigem));

				ligacaoEsgoto.setLigacaoOrigem(ligacaoOrigem);

			}

			if (percentualEsgoto != null && !percentualEsgoto.equals("")) {

				BigDecimal percentualEsgotoColeta = new BigDecimal(
						percentualEsgoto);
				ligacaoEsgoto.setPercentual(percentualEsgotoColeta);
			}

			// Condicação do Esgotamento
			if (ligacaoEsgotoEsgotamento != null
					&& Integer.parseInt(ligacaoEsgotoEsgotamento) != ConstantesSistema.NUMERO_NAO_INFORMADO) {
				LigacaoEsgotoEsgotamento le = new LigacaoEsgotoEsgotamento();
				le.setId(Integer.parseInt(ligacaoEsgotoEsgotamento));
				ligacaoEsgoto.setLigacaoEsgotoEsgotamento(le);
			}

			// Destino dos dejetos
			if (ligacaoEsgotoDestinoDejetos != null
					&& Integer.parseInt(ligacaoEsgotoDestinoDejetos) != ConstantesSistema.NUMERO_NAO_INFORMADO) {
				LigacaoEsgotoDestinoDejetos ledd = new LigacaoEsgotoDestinoDejetos();
				ledd.setId(Integer.parseInt(ligacaoEsgotoDestinoDejetos));
				ligacaoEsgoto.setLigacaoEsgotoDestinoDejetos(ledd);
			}

			// Situacao da caixa de inspecao
			if (ligacaoEsgotoCaixaInspecao != null
					&& Integer.parseInt(ligacaoEsgotoCaixaInspecao) != ConstantesSistema.NUMERO_NAO_INFORMADO) {
				LigacaoEsgotoCaixaInspecao leci = new LigacaoEsgotoCaixaInspecao();
				leci.setId(Integer.parseInt(ligacaoEsgotoCaixaInspecao));
				ligacaoEsgoto.setLigacaoEsgotoCaixaInspecao(leci);
			}

			// Destino das Aguas Pluviais
			if (ligacaoEsgotoDestinoAguasPluviais != null
					&& Integer.parseInt(ligacaoEsgotoDestinoAguasPluviais) != ConstantesSistema.NUMERO_NAO_INFORMADO) {
				LigacaoEsgotoDestinoAguasPluviais ledap = new LigacaoEsgotoDestinoAguasPluviais();
				ledap
						.setId(Integer
								.parseInt(ligacaoEsgotoDestinoAguasPluviais));
				ligacaoEsgoto.setLigacaoEsgotoDestinoAguasPluviais(ledap);
			}
			
			if(Util.verificarNaoVazio(ligacaoEsgotoActionForm.getLimiteConsumoAlternativo()) 
					|| Util.verificarNaoVazio(ligacaoEsgotoActionForm.getPercentualAlternativoEsgoto()))
			{
				
				if(!Util.verificarNaoVazio(ligacaoEsgotoActionForm.getLimiteConsumoAlternativo()))
				{
					throw new ActionServletException("atencao.required", "Limite de Consumo Alternativo");
				}
				
				if(!Util.validarNumeroMaiorQueZERO(ligacaoEsgotoActionForm.getLimiteConsumoAlternativo()))
				{
					throw new ActionServletException("atencao.invalid", "Limite de Consumo Alternativo");
				}
				
				if(!Util.verificarNaoVazio(ligacaoEsgotoActionForm.getPercentualAlternativoEsgoto()))
				{
					throw new ActionServletException("atencao.required", "Percentual Alternativo de Esgoto");
				}
				
				if(Util.validarValorNaoNumericoComoBigDecimal(ligacaoEsgotoActionForm.getPercentualAlternativoEsgoto().replace(",", ".")))
				{
					throw new ActionServletException("atencao.invalid", "Percentual Alternativo de Esgoto");
				}
				
				if(Util.formatarMoedaRealparaBigDecimal(ligacaoEsgotoActionForm.getPercentualAlternativoEsgoto()).compareTo(new BigDecimal("100.00"))==1)
				{
					throw new ActionServletException("atencao.percentual.alternativo.esgoto", "Percentual Alternativo de Esgoto");
				}
				
				ligacaoEsgoto.setNumeroConsumoPercentualAlternativo(Integer.valueOf(ligacaoEsgotoActionForm.getLimiteConsumoAlternativo()));
				ligacaoEsgoto.setPercentualAlternativo(Util.formatarMoedaRealparaBigDecimal(ligacaoEsgotoActionForm.getPercentualAlternativoEsgoto()));
			}

			ligacaoEsgoto.setImovel(imovel);

			String qtdParcelas = ligacaoEsgotoActionForm
					.getQuantidadeParcelas();
			IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();

			integracaoComercialHelper.setLigacaoEsgoto(ligacaoEsgoto);
			integracaoComercialHelper.setImovel(imovel);
			integracaoComercialHelper.setOrdemServico(null);
			integracaoComercialHelper.setQtdParcelas(qtdParcelas);
			fachada.inserirLigacaoEsgoto(integracaoComercialHelper);
			
			if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
				montarPaginaSucesso(httpServletRequest,
						"Ligação de Esgoto efetuada com Sucesso",
						"Efetuar outra Ligação de Esgoto",
						"exibirEfetuarLigacaoEsgotoAction.do?menu=sim",
						"exibirAtualizarLigacaoEsgotoAction.do?menu=sim&matriculaImovel="
								+ imovel.getId(),
						"Atualização Ligação de Esgoto efetuada");
				
				ligacaoEsgotoActionForm = null;

			}

		}

		OrdemServico ordemServico = null;
		if (ordemServicoId != null && !ordemServicoId.equals("")) {
			ordemServico = (OrdemServico) sessao.getAttribute("ordemServico");
			if (ordemServico == null) {
				throw new ActionServletException(
						"atencao.ordem_servico_inexistente", null,
						"ORDEM DE SERVIÇO INEXISTENTE");

			}
			
			ligacaoEsgoto.setImovel(imovel);

			if (ligacaoEsgotoActionForm.getDataLigacao() != null
					&& ligacaoEsgotoActionForm.getDataLigacao() != "") {
				Date data = Util.converteStringParaDate(ligacaoEsgotoActionForm
						.getDataLigacao());
				ligacaoEsgoto.setDataLigacao(data);
			} else {
				throw new ActionServletException("atencao.informe_campo", null,
						" Data da Ligação");
			}
			/*---------------------  InícioDados da Ligação Esgoto  ------------------------*/
			// lesg_iccaixagordura
			ligacaoEsgoto.setIndicadorCaixaGordura(new Short(
					indicadorCaixaGordura));
			ligacaoEsgoto.setIndicadorLigacaoEsgoto(new Short(indicadorLigacaoEsgoto));
			// lagu_tultimaalteracao
			ligacaoEsgoto.setUltimaAlteracao(new Date());
			// lest_id
			ligacaoEsgoto.setId(imovel.getId());
			// LEST_ID
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
			ligacaoEsgotoSituacao.setId(LigacaoEsgotoSituacao.LIGADO);
			// ligacaoEsgoto.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);

			String diametroLigacao = ligacaoEsgotoActionForm
					.getDiametroLigacao();
			if (diametroLigacao != null
					&& !diametroLigacao.equals("")
					&& !diametroLigacao.trim().equalsIgnoreCase(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				LigacaoEsgotoDiametro ligacaoEsgotoDiametro = new LigacaoEsgotoDiametro();
				ligacaoEsgotoDiametro.setId(new Integer(diametroLigacao));
				ligacaoEsgoto.setLigacaoEsgotoDiametro(ligacaoEsgotoDiametro);
			} else {
				throw new ActionServletException(
						"atencao.informe_campo_obrigatorio", null,
						"Diametro da Ligação");
			}

			if (materialLigacao != null
					&& !materialLigacao.equals("")
					&& !materialLigacao.trim().equalsIgnoreCase(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				LigacaoEsgotoMaterial ligacaoEsgotoMaterialMaterial = new LigacaoEsgotoMaterial();
				ligacaoEsgotoMaterialMaterial
						.setId(new Integer(materialLigacao));
				ligacaoEsgoto
						.setLigacaoEsgotoMaterial(ligacaoEsgotoMaterialMaterial);
			} else {
				throw new ActionServletException(
						"atencao.informe_campo_obrigatorio", null,
						"Material da Ligação");
			}
			
			if (perfilLigacao != null && !perfilLigacao.equals("") && 
				!perfilLigacao.trim().equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				
				FiltroLigacaoEsgotoPerfil filtroLigacaoEsgotoPerfil = new FiltroLigacaoEsgotoPerfil();
				filtroLigacaoEsgotoPerfil.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgotoPerfil.ID, 
						new Integer(perfilLigacao)));
				
				Collection colecaoLigacaoEsgotoPerfil = fachada.pesquisar(filtroLigacaoEsgotoPerfil, LigacaoEsgotoPerfil.class.getName());
				LigacaoEsgotoPerfil ligacaoEsgotoPerfil = (LigacaoEsgotoPerfil) Util.retonarObjetoDeColecao(colecaoLigacaoEsgotoPerfil);
				ligacaoEsgoto.setLigacaoEsgotoPerfil(ligacaoEsgotoPerfil);
			} 
			else {
				
				throw new ActionServletException(
						"atencao.informe_campo_obrigatorio", null,
						"Perfil da Ligação");
			}
			// item 4.5 - [FS006] caso 1,3
			if (percentual != null && !percentual.equals("")) {

				BigDecimal percentualInformadoColeta = Util.formatarStringParaBigDecimal(percentual);
				
				if (percentualInformadoColeta != null &&
					percentualInformadoColeta.compareTo(ligacaoEsgoto.getLigacaoEsgotoPerfil().getPercentualMaximoColeta()) <= 0) {
					
					ligacaoEsgoto.setPercentualAguaConsumidaColetada(percentualInformadoColeta);
				}
				else{
					
					throw new ActionServletException("atencao.atualizar_percentual_informado_maior_maximo_permitido", null, 
							Util.formatarBigDecimalComPonto(ligacaoEsgoto.getLigacaoEsgotoPerfil().getPercentualMaximoColeta()) + "%");
				}
			} else {
				throw new ActionServletException(
						"atencao.informe_campo_obrigatorio", null,
						"Percentual de Coleta");
			}
			
			Imovel im = fachada.pesquisarDadosImovel(imovel.getId());
			
			// [FS0017] Verificar Nível para instalação de esgoto
			if(sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAER) && 
					im.getIndicadorNivelInstalacaoEsgoto() != null && im.getIndicadorNivelInstalacaoEsgoto().equals(ConstantesSistema.NAO)){
				throw new ActionServletException("atencao.imovel_associado_os_sem_nivel_instalacao_esgoto", null, imovel.getId().toString());
			}

			// Colocado por Rômulo Aurélio Analista: Sávio Luiz Data:03/09/2008
			// Ligacao Origem
			if (idLigacaoOrigem != null
					&& !idLigacaoOrigem.equals("")
					&& !idLigacaoOrigem.trim().equalsIgnoreCase(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

				LigacaoOrigem ligacaoOrigem = new LigacaoOrigem();
				ligacaoOrigem.setId(new Integer(idLigacaoOrigem));

				ligacaoEsgoto.setLigacaoOrigem(ligacaoOrigem);

			}

			if (percentualEsgoto != null && !percentualEsgoto.equals("")) {

				BigDecimal percentualEsgotoColeta = new BigDecimal(
						percentualEsgoto);
				ligacaoEsgoto.setPercentual(percentualEsgotoColeta);
			}

			// Condicação do Esgotamento
			if (ligacaoEsgotoEsgotamento != null
					&& Integer.parseInt(ligacaoEsgotoEsgotamento) != ConstantesSistema.NUMERO_NAO_INFORMADO) {
				LigacaoEsgotoEsgotamento le = new LigacaoEsgotoEsgotamento();
				le.setId(Integer.parseInt(ligacaoEsgotoEsgotamento));
				ligacaoEsgoto.setLigacaoEsgotoEsgotamento(le);
			}

			// Destino dos dejetos
			if (ligacaoEsgotoDestinoDejetos != null
					&& Integer.parseInt(ligacaoEsgotoDestinoDejetos) != ConstantesSistema.NUMERO_NAO_INFORMADO) {
				LigacaoEsgotoDestinoDejetos ledd = new LigacaoEsgotoDestinoDejetos();
				ledd.setId(Integer.parseInt(ligacaoEsgotoDestinoDejetos));
				ligacaoEsgoto.setLigacaoEsgotoDestinoDejetos(ledd);
			}

			// Situacao da caixa de inspecao
			if (ligacaoEsgotoCaixaInspecao != null
					&& Integer.parseInt(ligacaoEsgotoCaixaInspecao) != ConstantesSistema.NUMERO_NAO_INFORMADO) {
				LigacaoEsgotoCaixaInspecao leci = new LigacaoEsgotoCaixaInspecao();
				leci.setId(Integer.parseInt(ligacaoEsgotoCaixaInspecao));
				ligacaoEsgoto.setLigacaoEsgotoCaixaInspecao(leci);
			}

			// Destino das Aguas Pluviais
			if (ligacaoEsgotoDestinoAguasPluviais != null
					&& Integer.parseInt(ligacaoEsgotoDestinoAguasPluviais) != ConstantesSistema.NUMERO_NAO_INFORMADO) {
				LigacaoEsgotoDestinoAguasPluviais ledap = new LigacaoEsgotoDestinoAguasPluviais();
				ledap
						.setId(Integer
								.parseInt(ligacaoEsgotoDestinoAguasPluviais));
				ligacaoEsgoto.setLigacaoEsgotoDestinoAguasPluviais(ledap);
			}

			if (ordemServico != null
					&& ligacaoEsgotoActionForm.getIdTipoDebito() != null) {

				ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo = null;

				ordemServico.setIndicadorComercialAtualizado(new Short("1"));

				BigDecimal valorAtual = new BigDecimal(0);
				if (ligacaoEsgotoActionForm.getValorDebito() != null) {
					String valorDebito = ligacaoEsgotoActionForm
							.getValorDebito().toString().replace(".", "");

					valorDebito = valorDebito.replace(",", ".");

					valorAtual = new BigDecimal(valorDebito);

					ordemServico.setValorAtual(valorAtual);
				}

				if (idServicoMotivoNaoCobranca != null
						&& !idServicoMotivoNaoCobranca
								.equals(ConstantesSistema.NUMERO_NAO_INFORMADO
										+ "")) {
					servicoNaoCobrancaMotivo = new ServicoNaoCobrancaMotivo();
					servicoNaoCobrancaMotivo.setId(new Integer(
							idServicoMotivoNaoCobranca));
				}
				ordemServico
						.setServicoNaoCobrancaMotivo(servicoNaoCobrancaMotivo);

				if (valorPercentual != null) {
					ordemServico.setPercentualCobranca(new BigDecimal(
							ligacaoEsgotoActionForm.getPercentualCobranca()));
				}

				ordemServico.setUltimaAlteracao(new Date());

			}
			
			if(Util.verificarNaoVazio(ligacaoEsgotoActionForm.getLimiteConsumoAlternativo()) 
					|| Util.verificarNaoVazio(ligacaoEsgotoActionForm.getPercentualAlternativoEsgoto()))
			{
				
				if(!Util.verificarNaoVazio(ligacaoEsgotoActionForm.getLimiteConsumoAlternativo()))
				{
					throw new ActionServletException("atencao.required", "Limite de Consumo Alternativo");
				}
				
				if(!Util.validarNumeroMaiorQueZERO(ligacaoEsgotoActionForm.getLimiteConsumoAlternativo()))
				{
					throw new ActionServletException("atencao.invalid", "Limite de Consumo Alternativo");
				}
				
				if(!Util.verificarNaoVazio(ligacaoEsgotoActionForm.getPercentualAlternativoEsgoto()))
				{
					throw new ActionServletException("atencao.required", "Percentual Alternativo de Esgoto");
				}
				
				if(Util.validarValorNaoNumericoComoBigDecimal(ligacaoEsgotoActionForm.getPercentualAlternativoEsgoto().replace(",", ".")))
				{
					throw new ActionServletException("atencao.invalid", "Percentual Alternativo de Esgoto");
				}
				
				if(Util.formatarMoedaRealparaBigDecimal(ligacaoEsgotoActionForm.getPercentualAlternativoEsgoto()).compareTo(new BigDecimal("100.00"))==1)
				{
					throw new ActionServletException("atencao.percentual.alternativo.esgoto", "Percentual Alternativo de Esgoto");
				}
				
				ligacaoEsgoto.setNumeroConsumoPercentualAlternativo(Integer.valueOf(ligacaoEsgotoActionForm.getLimiteConsumoAlternativo()));
				ligacaoEsgoto.setPercentualAlternativo(Util.formatarMoedaRealparaBigDecimal(ligacaoEsgotoActionForm.getPercentualAlternativoEsgoto()));
			}

			String qtdParcelas = ligacaoEsgotoActionForm
					.getQuantidadeParcelas();
			IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();

			integracaoComercialHelper.setLigacaoEsgoto(ligacaoEsgoto);
			integracaoComercialHelper.setImovel(imovel);
			integracaoComercialHelper.setOrdemServico(ordemServico);
			integracaoComercialHelper.setQtdParcelas(qtdParcelas);
			integracaoComercialHelper.setUsuarioLogado(usuario);

			if (ligacaoEsgotoActionForm.getVeioEncerrarOS().equalsIgnoreCase(
					"FALSE")) {
				integracaoComercialHelper.setVeioEncerrarOS(Boolean.FALSE);

				fachada.inserirLigacaoEsgoto(integracaoComercialHelper);
			} else {
				integracaoComercialHelper.setVeioEncerrarOS(Boolean.TRUE);

				sessao.setAttribute("integracaoComercialHelper",
						integracaoComercialHelper);

				if (sessao.getAttribute("semMenu") == null) {
					retorno = actionMapping
							.findForward("encerrarOrdemServicoAction");
				} else {
					retorno = actionMapping
							.findForward("encerrarOrdemServicoPopupAction");
				}
				sessao.removeAttribute("caminhoRetornoIntegracaoComercial");
			}

			if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
				montarPaginaSucesso(httpServletRequest,
						"Ligação de Esgoto efetuada com Sucesso",
						"Efetuar outra Ligação de Esgoto",
						"exibirEfetuarLigacaoEsgotoAction.do?menu=sim",
						"exibirAtualizarLigacaoEsgotoAction.do?menu=sim&funcionalidade=comRA&idOrdemServico="
								+ ordemServico.getId(),
						"Atualização Ligação de Esgoto efetuada");
				
				ligacaoEsgotoActionForm = null;

			}

		}
		
		}
		return retorno;
		

		/*
		 * else { throw new ActionServletException(
		 * "atencao.informe_campo_obrigatorio", null, "Ordem de Serviço"); }
		 */
	}

}
