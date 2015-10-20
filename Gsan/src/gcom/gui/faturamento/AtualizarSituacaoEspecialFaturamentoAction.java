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
package gcom.gui.faturamento;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoSituacaoComando;
import gcom.faturamento.FaturamentoSituacaoHistorico;
import gcom.faturamento.FaturamentoSituacaoMotivo;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.faturamento.FiltroFaturamentoSituacaoComando;
import gcom.faturamento.FiltroFaturamentoSituacaoHistorico;
import gcom.faturamento.bean.SituacaoEspecialFaturamentoHelper;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0928] - Manter Situa��o Especial de Faturamento
 * 
 * Para Manter uma Situa��o Especial de Faturamanto (SEF), na verdade se trabalha com duas entidades:
 * FaturamentoSitucaoComando (FSC) e FaturamentoSituacaoHistorico (FSH).
 * 
 * Na tela de consulta, e na tela em que se lista os resultados da consulta trabalha-se com o 
 * FaturamentoSitucaoComando. Por�m, para se atualizar uma SEF, n�o � simplesmente dar um update
 * nessa entidade e pronto.O que acontece � que devemos retirar a SEF escolhida pelo usu�rio e incluir uma nova com as altera��es.
 * 
 * Para retirar uma SEF insere-se um FSC igualzinho ao que se quer retirar, com alguns atributos diferentes 
 * que indicam que a mesma foi retirada. Al�m disso, � preciso inserir c�pias de todas os FSH do FSC em que 
 * se deseja retirar, onde essas c�pias s�o associadas ao novo FSC inserido.
 * 
 * Tendo isso em mente, o que se faz nesse action �:
 * 1 - Cria-se duas c�pias do FSC escolhido pelo usu�rio, uma que retira a j� existente e outro que inseri com 
 * as informa��es atualizadas.
 * 
 * 2 - Cria-se duas c�pias para cada FSH do FSC que se dejeja alterar. Uma ser� associada a c�pia do FSC que 
 * ser� configurada para retirar a SEF atual, e outra ser� associada ao FSC que ser� inserido para representar
 * a nova SEF com as informa��es atualizadas.
 * 
 * 3 - Todas essas c�pias s�o inseridas na base. Assim, a opera��o est� completa.
 * 
 * @author Marlon Patrick
 *
 */
public class AtualizarSituacaoEspecialFaturamentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		
		AtualizarSituacaoEspecialFaturamentoActionForm atualizarSituacaoEspecialFaturamentoActionForm = (AtualizarSituacaoEspecialFaturamentoActionForm) actionForm;
		FaturamentoSituacaoComando faturamentoSituacaoComandoOriginal= (FaturamentoSituacaoComando) sessao.getAttribute("atualizarFaturamentoSituacaoComando");
		
		Usuario usuarioLogado =(Usuario) sessao.getAttribute(Usuario.USUARIO_LOGADO);
		
		validarMesAnoReferencia(atualizarSituacaoEspecialFaturamentoActionForm, usuarioLogado);

		if( Util.verificarNaoVazio(atualizarSituacaoEspecialFaturamentoActionForm.getIdFaturamentoSituacaoComando())){
			faturamentoSituacaoComandoOriginal.setId(new Integer(atualizarSituacaoEspecialFaturamentoActionForm.getIdFaturamentoSituacaoComando().trim()));
		}else{
			faturamentoSituacaoComandoOriginal.setId(null);
		}		
        
        faturamentoSituacaoComandoOriginal = obterFaturamentoSituacaoComando(fachada,faturamentoSituacaoComandoOriginal);

        FaturamentoSituacaoComando faturamentoSituacaoComandoRetirar = criarCopiaComando(faturamentoSituacaoComandoOriginal);

        FaturamentoSituacaoComando faturamentoSituacaoComandoInserir = criarCopiaComando(faturamentoSituacaoComandoOriginal);

		SistemaParametro sistemaParametros = fachada.pesquisarParametrosDoSistema();			  

		modificarAtributosRetirarSituacaoEspecialFaturamento(
				faturamentoSituacaoComandoRetirar, usuarioLogado, sistemaParametros);		

		modificarAtributosInserirSituacaoEspecialFaturamento(
				faturamentoSituacaoComandoInserir, usuarioLogado,
				sistemaParametros,atualizarSituacaoEspecialFaturamentoActionForm);
		
		ArrayList<FaturamentoSituacaoHistorico> colecaoFaturamentoSituacaoHistoricoAtualizar = 
        	(ArrayList<FaturamentoSituacaoHistorico>) obterColecaoFaturamentoSituacaoHistorico(faturamentoSituacaoComandoOriginal);

        ArrayList<FaturamentoSituacaoHistorico> colecaoFaturamentoSituacaoHistoricoInserir = null;
        
        if( !Util.isVazioOrNulo(colecaoFaturamentoSituacaoHistoricoAtualizar)){
        	colecaoFaturamentoSituacaoHistoricoInserir = new ArrayList<FaturamentoSituacaoHistorico>();
        	
        	for(FaturamentoSituacaoHistorico historico: colecaoFaturamentoSituacaoHistoricoAtualizar){

        		FaturamentoSituacaoHistorico historicoInserir = criarCopiaHistorico(historico);
        		modificarAtributosInserirFaturamentoSituacaoHistorico(
						faturamentoSituacaoComandoInserir,historicoInserir);
        		colecaoFaturamentoSituacaoHistoricoInserir.add(historicoInserir);

        		
        		modificarAtributosRetirarFaturamentoSituacaoHistorico(
						faturamentoSituacaoComandoRetirar, historico);
        	}        	
        }
       
		fachada.atualizarSituacaoEspecialFaturamento(faturamentoSituacaoComandoOriginal,faturamentoSituacaoComandoInserir, colecaoFaturamentoSituacaoHistoricoInserir, faturamentoSituacaoComandoRetirar, colecaoFaturamentoSituacaoHistoricoAtualizar);		
		
		montarPaginaSucesso(httpServletRequest, "Situacao Especial de Faturamento"
				+ " atualizado com sucesso.",
				"Realizar outra Manuten��o de Situa��o Especial de Faturamento",
				"exibirFiltrarSituacaoEspecialFaturamentoAction.do?menu=sim");        
        
		return actionMapping.findForward("telaSucesso");
	}

	/**
	 *Esse m�todo seta os atributos do Faturamento Situa��o Hist�rico
	 *necess�rios para que ele seja inserido e associados ao Faturamento Situa��o Comando de retirada.
	 *
	 *@since 17/08/2009
	 *@author Marlon Patrick
	 */
	private void modificarAtributosRetirarFaturamentoSituacaoHistorico(
			FaturamentoSituacaoComando faturamentoSituacaoComandoRetirar,
			FaturamentoSituacaoHistorico historico) {

		
		historico.setAnoMesFaturamentoSituacaoFim(faturamentoSituacaoComandoRetirar.getAnoMesFinalSituacaoFaturamento());
		historico.setAnoMesFaturamentoRetirada(faturamentoSituacaoComandoRetirar.getAnoMesFinalSituacaoFaturamento());
		historico.setFaturamentoSituacaoComandoRetirada(faturamentoSituacaoComandoRetirar);
	}

	/**
	 *Esse m�todo seta os atributos do Faturamento Situa��o Hist�rico
	 *que ser�o inseridos e associados ao novo Faturamento Situa��o Comando 
	 *
	 *@since 17/08/2009
	 *@author Marlon Patrick
	 */
	private void modificarAtributosInserirFaturamentoSituacaoHistorico(
			FaturamentoSituacaoComando faturamentoSituacaoComandoInserir,
			FaturamentoSituacaoHistorico historico) {
		
		historico.setId(null);
		historico.setAnoMesFaturamentoRetirada(null);
		historico.setUltimaAlteracao(new Date());
		historico.setUsuario(null);
		historico.setObservacaoInforma(null);
		historico.setObservacaoRetira(null);
		historico.setUsuarioInforma(null);
		historico.setUsuarioRetira(null);
		historico.setIdAntigo(null);
		historico.setAnoMesFaturamentoSituacaoInicio(faturamentoSituacaoComandoInserir.getAnoMesInicialSituacaoFaturamento());
		historico.setAnoMesFaturamentoSituacaoFim(faturamentoSituacaoComandoInserir.getAnoMesFinalSituacaoFaturamento());
		historico.setFaturamentoSituacaoComandoInforma(faturamentoSituacaoComandoInserir);
	}

	/**
	 * S�op setados alguns atributos necess�rios a 
	 * inser��o de uma Situa��o Especial de Faturamento
	 *
	 *@since 17/08/2009
	 *@author Marlon Patrick
	 */
	private void modificarAtributosInserirSituacaoEspecialFaturamento(
			FaturamentoSituacaoComando faturamentoSituacaoComandoInserir,
			Usuario usuarioLogado,
			SistemaParametro sistemaParametros,
			AtualizarSituacaoEspecialFaturamentoActionForm atualizarSituacaoEspecialFaturamentoActionForm) {
		
		//atributo pass�vel de altera��o na tela
		faturamentoSituacaoComandoInserir.setId(null);		
		faturamentoSituacaoComandoInserir.setObservacao(atualizarSituacaoEspecialFaturamentoActionForm.
				getObservacao());		
		
		//atributo pass�vel de altera��o na tela
		faturamentoSituacaoComandoInserir.setAnoMesFinalSituacaoFaturamento( 
				Util.formatarMesAnoComBarraParaAnoMes(atualizarSituacaoEspecialFaturamentoActionForm.
						getMesAnoReferenciaFaturamentoFinal()));		
		
		faturamentoSituacaoComandoInserir.setIndicadorComando((short)1);		
		faturamentoSituacaoComandoInserir.setUsuario(usuarioLogado);		
		faturamentoSituacaoComandoInserir.setUltimaAlteracao(new Date());
		
		faturamentoSituacaoComandoInserir.setAnoMesInicialSituacaoFaturamento(
				sistemaParametros.getAnoMesFaturamento());

	}

	/**
	 *
	 * Os atributos setados nesse m�todo s�o necess�rios
	 * para que uma situa��o especial de faturamento seja considerada retirada.
	 *
	 *@since 17/08/2009
	 *@author Marlon Patrick
	 */
	private void modificarAtributosRetirarSituacaoEspecialFaturamento(
			FaturamentoSituacaoComando faturamentoSituacaoComando,
			Usuario usuarioLogado, SistemaParametro sistemaParametros) {

		faturamentoSituacaoComando.setId(null);
		faturamentoSituacaoComando.setIndicadorComando((short)2);		
		faturamentoSituacaoComando.setUsuario(usuarioLogado);
		faturamentoSituacaoComando.setAnoMesFinalSituacaoFaturamento(Util.subtrairMesDoAnoMes(sistemaParametros.getAnoMesFaturamento(), 1));
		faturamentoSituacaoComando.setUltimaAlteracao(new Date());
	}

	/**
	 *[UC ] - 
	 *[SB ] -
	 *[FS ] -
	 *
	 * comment
	 *
	 *@since 14/08/2009
	 *@author Marlon Patrick
	 */
	private Collection<FaturamentoSituacaoHistorico> obterColecaoFaturamentoSituacaoHistorico(
			FaturamentoSituacaoComando faturamentoSituacaoComando) {
		
		FiltroFaturamentoSituacaoHistorico filtroFaturamentoSituacaoHistorico = new FiltroFaturamentoSituacaoHistorico();
		filtroFaturamentoSituacaoHistorico.adicionarParametro(
				new ParametroSimples(FiltroFaturamentoSituacaoHistorico.FATURAMENTO_COMANDO_INFORMA_ID,faturamentoSituacaoComando.getId()));
		filtroFaturamentoSituacaoHistorico.adicionarParametro(
				new ParametroNulo(FiltroFaturamentoSituacaoHistorico.ANO_MES_FATURAMENTO_RETIRADA));
		
		filtroFaturamentoSituacaoHistorico.adicionarCaminhoParaCarregamentoEntidade(FiltroFaturamentoSituacaoHistorico.FATURAMENTO_COMANDO_INFORMA);
		filtroFaturamentoSituacaoHistorico.adicionarCaminhoParaCarregamentoEntidade(FiltroFaturamentoSituacaoHistorico.FATURAMENTO_RETIRA);
		filtroFaturamentoSituacaoHistorico.adicionarCaminhoParaCarregamentoEntidade(FiltroFaturamentoSituacaoHistorico.FATURAMENTO_MOTIVO);
		filtroFaturamentoSituacaoHistorico.adicionarCaminhoParaCarregamentoEntidade(FiltroFaturamentoSituacaoHistorico.FATURAMENTO_TIPO);
		filtroFaturamentoSituacaoHistorico.adicionarCaminhoParaCarregamentoEntidade(FiltroFaturamentoSituacaoHistorico.IMOVEL);
		filtroFaturamentoSituacaoHistorico.adicionarCaminhoParaCarregamentoEntidade(FiltroFaturamentoSituacaoHistorico.USUARIO);
		filtroFaturamentoSituacaoHistorico.adicionarCaminhoParaCarregamentoEntidade(FiltroFaturamentoSituacaoHistorico.USUARIO_INFORMA);
		filtroFaturamentoSituacaoHistorico.adicionarCaminhoParaCarregamentoEntidade(FiltroFaturamentoSituacaoHistorico.USUARIO_RETIRA);
		
		return Fachada.getInstancia().pesquisar(
				filtroFaturamentoSituacaoHistorico, FaturamentoSituacaoHistorico.class.getName());		
	}

	/**
	 * Esse m�todo criar o filtro necess�rio para consultar
	 * o FaturamentoSituacaoComando do banco adequadamente.
	 *
	 *@since 14/08/2009
	 *@author Marlon Patrick
	 */
	private FaturamentoSituacaoComando obterFaturamentoSituacaoComando(
			Fachada fachada,
			FaturamentoSituacaoComando faturamentoSituacaoComando) {

		FiltroFaturamentoSituacaoComando filtroFaturamentoSituacaoComando = new FiltroFaturamentoSituacaoComando();
		filtroFaturamentoSituacaoComando.adicionarParametro(
				new ParametroSimples(FiltroFaturamentoSituacaoComando.ID, faturamentoSituacaoComando.getId()));
		
		filtroFaturamentoSituacaoComando.adicionarCaminhoParaCarregamentoEntidade(FiltroFaturamentoSituacaoComando.CATEGORIA_1);
		filtroFaturamentoSituacaoComando.adicionarCaminhoParaCarregamentoEntidade(FiltroFaturamentoSituacaoComando.CATEGORIA_2);
		filtroFaturamentoSituacaoComando.adicionarCaminhoParaCarregamentoEntidade(FiltroFaturamentoSituacaoComando.CATEGORIA_3);
		filtroFaturamentoSituacaoComando.adicionarCaminhoParaCarregamentoEntidade(FiltroFaturamentoSituacaoComando.CATEGORIA_4);
		filtroFaturamentoSituacaoComando.adicionarCaminhoParaCarregamentoEntidade(FiltroFaturamentoSituacaoComando.IMOVEL);
		filtroFaturamentoSituacaoComando.adicionarCaminhoParaCarregamentoEntidade(FiltroFaturamentoSituacaoComando.LOCALIDADE_FINAL);
		filtroFaturamentoSituacaoComando.adicionarCaminhoParaCarregamentoEntidade(FiltroFaturamentoSituacaoComando.LOCALIDADE_INICIAL);
		filtroFaturamentoSituacaoComando.adicionarCaminhoParaCarregamentoEntidade(FiltroFaturamentoSituacaoComando.FATURAMENTO_SITUACAO_MOTIVO);
		filtroFaturamentoSituacaoComando.adicionarCaminhoParaCarregamentoEntidade(FiltroFaturamentoSituacaoComando.FATURAMENTO_SITUACAO_TIPO);
		filtroFaturamentoSituacaoComando.adicionarCaminhoParaCarregamentoEntidade(FiltroFaturamentoSituacaoComando.USUARIO);
	
		Collection<FaturamentoSituacaoComando> colecaoComando = fachada.
					pesquisar(filtroFaturamentoSituacaoComando, FaturamentoSituacaoComando.class.getName());
		
		return colecaoComando.iterator().next();		
	}
	
	private void validarMesAnoReferencia(AtualizarSituacaoEspecialFaturamentoActionForm 
			situacaoEspecialFaturamentoActionForm, Usuario usuarioLogado){

		Fachada fachada = Fachada.getInstancia();

		String mesAnoReferenciaFaturamentoInicial = situacaoEspecialFaturamentoActionForm
		.getMesAnoReferenciaFaturamentoInicial();

		String mesAnoReferenciaFaturamentoFinal = situacaoEspecialFaturamentoActionForm
		.getMesAnoReferenciaFaturamentoFinal();

		if (!Util.verificarNaoVazio(mesAnoReferenciaFaturamentoInicial)) {

			throw new ActionServletException("atencao.campo_texto.obrigatorio",
					null,"M�s e Ano de Refer�ncia do Faturamento Inicial");

		}

		if (!Util.verificarNaoVazio(mesAnoReferenciaFaturamentoFinal)) {

			throw new ActionServletException("atencao.campo_texto.obrigatorio",
					null,"M�s e Ano de Refer�ncia do Faturamento Final");

		}


		Integer anoMesReferenciaInicial = null;
		Integer anoMesReferenciaFinal = null;


		if ( !Util.validarMesAno(mesAnoReferenciaFaturamentoInicial)) {
			throw new ActionServletException(
					"atencao.adicionar_debito_ano_mes_referencia_invalido",null, "inicial");
		}
		if ( !Util.validarMesAno(mesAnoReferenciaFaturamentoFinal)) {
			throw new ActionServletException(
					"atencao.adicionar_debito_ano_mes_referencia_invalido",null, "final");

		}

		anoMesReferenciaInicial = Util
			.formatarMesAnoComBarraParaAnoMes(mesAnoReferenciaFaturamentoInicial);

		anoMesReferenciaFinal = Util
			.formatarMesAnoComBarraParaAnoMes(mesAnoReferenciaFaturamentoFinal);

		boolean dataInicialMenor = Util.compararAnoMesReferencia(
				new Integer(anoMesReferenciaInicial), new Integer(anoMesReferenciaFinal), "<");

		boolean dataInicialIgual = Util.compararAnoMesReferencia(
				new Integer(anoMesReferenciaInicial), new Integer(anoMesReferenciaFinal), "=");

		if (dataInicialMenor || dataInicialIgual) {

			Integer anoMesInicial = fachada
				.validarMesAnoReferencia(transferirActionFormParaHelper(situacaoEspecialFaturamentoActionForm,usuarioLogado));
			
			if (anoMesInicial > anoMesReferenciaFinal) {

				throw new ActionServletException(
						"atencao.mes.ano.anterior.mes.ano.corrente.imovel");

			}
		} else {
			throw new ActionServletException(
					"atencao.mes.ano.inicial.maior.mes.ano.final");

		}
	}
	
	private SituacaoEspecialFaturamentoHelper transferirActionFormParaHelper(
			AtualizarSituacaoEspecialFaturamentoActionForm situacaoEspecialFaturamentoActionForm,
			Usuario usuarioLogado) {

		SituacaoEspecialFaturamentoHelper situacaoEspecialFaturamentoHelper = new SituacaoEspecialFaturamentoHelper();

		situacaoEspecialFaturamentoHelper
				.setIdImovel(situacaoEspecialFaturamentoActionForm
						.getIdImovel() == null ? ""
						: situacaoEspecialFaturamentoActionForm.getIdImovel());

		situacaoEspecialFaturamentoHelper
				.setInscricaoTipo(situacaoEspecialFaturamentoActionForm
						.getInscricaoTipo() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getInscricaoTipo());

		situacaoEspecialFaturamentoHelper
				.setLoteDestino(situacaoEspecialFaturamentoActionForm
						.getLoteDestino() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getLoteDestino());

		situacaoEspecialFaturamentoHelper
				.setQuadraDestinoNM(situacaoEspecialFaturamentoActionForm
						.getQuadraDestinoNM() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getQuadraDestinoNM());

		situacaoEspecialFaturamentoHelper
				.setLoteOrigem(situacaoEspecialFaturamentoActionForm
						.getLoteOrigem() == null ? ""
						: situacaoEspecialFaturamentoActionForm.getLoteOrigem());

		situacaoEspecialFaturamentoHelper
				.setNomeLocalidadeOrigem(situacaoEspecialFaturamentoActionForm
						.getNomeLocalidadeOrigem() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getNomeLocalidadeOrigem());

		situacaoEspecialFaturamentoHelper
				.setNomeSetorComercialOrigem(situacaoEspecialFaturamentoActionForm
						.getNomeSetorComercialOrigem() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getNomeSetorComercialOrigem());

		situacaoEspecialFaturamentoHelper
				.setQuadraOrigemNM(situacaoEspecialFaturamentoActionForm
						.getQuadraOrigemNM() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getQuadraOrigemNM());

		situacaoEspecialFaturamentoHelper
				.setQuadraMensagemOrigem(situacaoEspecialFaturamentoActionForm
						.getQuadraMensagemOrigem() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getQuadraMensagemOrigem());

		situacaoEspecialFaturamentoHelper
				.setNomeLocalidadeDestino(situacaoEspecialFaturamentoActionForm
						.getNomeLocalidadeDestino() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getNomeLocalidadeDestino());

		situacaoEspecialFaturamentoHelper
				.setSetorComercialDestinoCD(situacaoEspecialFaturamentoActionForm
						.getSetorComercialDestinoCD() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getSetorComercialDestinoCD());

		situacaoEspecialFaturamentoHelper
				.setSetorComercialOrigemCD(situacaoEspecialFaturamentoActionForm
						.getSetorComercialOrigemCD() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getSetorComercialOrigemCD());

		situacaoEspecialFaturamentoHelper
				.setSetorComercialOrigemID(situacaoEspecialFaturamentoActionForm
						.getSetorComercialOrigemID() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getSetorComercialOrigemID());

		situacaoEspecialFaturamentoHelper
				.setQuadraOrigemID(situacaoEspecialFaturamentoActionForm
						.getQuadraOrigemID() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getQuadraOrigemID());

		situacaoEspecialFaturamentoHelper
				.setLocalidadeDestinoID(situacaoEspecialFaturamentoActionForm
						.getLocalidadeDestinoID() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getLocalidadeDestinoID());

		situacaoEspecialFaturamentoHelper
				.setLocalidadeOrigemID(situacaoEspecialFaturamentoActionForm
						.getLocalidadeOrigemID() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getLocalidadeOrigemID());

		situacaoEspecialFaturamentoHelper
				.setNomeSetorComercialDestino(situacaoEspecialFaturamentoActionForm
						.getNomeSetorComercialDestino() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getNomeSetorComercialDestino());

		situacaoEspecialFaturamentoHelper
				.setSetorComercialDestinoID(situacaoEspecialFaturamentoActionForm
						.getSetorComercialDestinoID() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getSetorComercialDestinoID());

		situacaoEspecialFaturamentoHelper
				.setQuadraMensagemDestino(situacaoEspecialFaturamentoActionForm
						.getQuadraMensagemDestino() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getQuadraMensagemDestino());

		situacaoEspecialFaturamentoHelper
				.setQuadraDestinoID(situacaoEspecialFaturamentoActionForm
						.getQuadraDestinoID() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getQuadraDestinoID());

		situacaoEspecialFaturamentoHelper
				.setTipoSituacaoEspecialFaturamento(situacaoEspecialFaturamentoActionForm
						.getTipoSituacaoEspecialFaturamento() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getTipoSituacaoEspecialFaturamento());

		situacaoEspecialFaturamentoHelper
				.setLoteOrigem(situacaoEspecialFaturamentoActionForm
						.getLoteOrigem() == null ? ""
						: situacaoEspecialFaturamentoActionForm.getLoteOrigem());

		situacaoEspecialFaturamentoHelper
				.setLoteDestino(situacaoEspecialFaturamentoActionForm
						.getLoteDestino() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getLoteDestino());

		situacaoEspecialFaturamentoHelper
				.setSubloteOrigem(situacaoEspecialFaturamentoActionForm
						.getSubloteOrigem() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getSubloteOrigem());

		situacaoEspecialFaturamentoHelper
				.setSubloteDestino(situacaoEspecialFaturamentoActionForm
						.getSubloteDestino() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getSubloteDestino());

		situacaoEspecialFaturamentoHelper
				.setIdFaturamentoSituacaoMotivo(situacaoEspecialFaturamentoActionForm
						.getIdFaturamentoSituacaoMotivo() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getIdFaturamentoSituacaoMotivo());

		situacaoEspecialFaturamentoHelper
				.setIdFaturamentoSituacaoTipo(situacaoEspecialFaturamentoActionForm
						.getIdFaturamentoSituacaoTipo() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getIdFaturamentoSituacaoTipo());

		situacaoEspecialFaturamentoHelper
				.setMesAnoReferenciaFaturamentoInicial(situacaoEspecialFaturamentoActionForm
						.getMesAnoReferenciaFaturamentoInicial() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getMesAnoReferenciaFaturamentoInicial());

		situacaoEspecialFaturamentoHelper
				.setMesAnoReferenciaFaturamentoFinal(situacaoEspecialFaturamentoActionForm
						.getMesAnoReferenciaFaturamentoFinal() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getMesAnoReferenciaFaturamentoFinal());

		situacaoEspecialFaturamentoHelper
				.setQuantidadeImoveisCOMSituacaoEspecialFaturamento(situacaoEspecialFaturamentoActionForm
						.getQuantidadeImoveisCOMSituacaoEspecialFaturamento() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getQuantidadeImoveisCOMSituacaoEspecialFaturamento());

		situacaoEspecialFaturamentoHelper
				.setQuantidadeImoveisSEMSituacaoEspecialFaturamento(situacaoEspecialFaturamentoActionForm
						.getQuantidadeImoveisSEMSituacaoEspecialFaturamento() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getQuantidadeImoveisSEMSituacaoEspecialFaturamento());

		situacaoEspecialFaturamentoHelper
				.setQuantidadeImoveisAtualizados(situacaoEspecialFaturamentoActionForm
						.getQuantidadeImoveisAtualizados() == null ? ""
						: situacaoEspecialFaturamentoActionForm
								.getQuantidadeImoveisAtualizados());
		
		situacaoEspecialFaturamentoHelper
		.setCodigoRotaInicial(situacaoEspecialFaturamentoActionForm
				.getCdRotaInicial() == null ? ""
				: situacaoEspecialFaturamentoActionForm.getCdRotaInicial());
		
		situacaoEspecialFaturamentoHelper
		.setCodigoRotaFinal(situacaoEspecialFaturamentoActionForm
				.getCdRotaFinal() == null ? ""
				: situacaoEspecialFaturamentoActionForm.getCdRotaFinal());
		
		situacaoEspecialFaturamentoHelper
		.setSequencialRotaInicial(situacaoEspecialFaturamentoActionForm
				.getSequencialRotaInicial() == null ? ""
				: situacaoEspecialFaturamentoActionForm.getSequencialRotaInicial());
		
		situacaoEspecialFaturamentoHelper
		.setSequencialRotaFinal(situacaoEspecialFaturamentoActionForm
				.getSequencialRotaFinal() == null ? ""
				: situacaoEspecialFaturamentoActionForm.getSequencialRotaFinal());
		
		//Colocado por Raphael Rossiter em 11/08/2008 - Analista:Rosana Carvalho
		if (situacaoEspecialFaturamentoActionForm.getConsumoFixoMedido() != null &&
			!situacaoEspecialFaturamentoActionForm.getConsumoFixoMedido().equals("")){
			
			situacaoEspecialFaturamentoHelper.setNumeroConsumoAguaMedido(
			new Integer(situacaoEspecialFaturamentoActionForm.getConsumoFixoMedido()));
		}
		
		if (situacaoEspecialFaturamentoActionForm.getConsumoFixoNaoMedido() != null &&
				!situacaoEspecialFaturamentoActionForm.getConsumoFixoNaoMedido().equals("")){
				
			situacaoEspecialFaturamentoHelper.setNumeroConsumoAguaNaoMedido(
			new Integer(situacaoEspecialFaturamentoActionForm.getConsumoFixoNaoMedido()));
		}
		
		if (situacaoEspecialFaturamentoActionForm.getVolumeFixoMedido() != null &&
			!situacaoEspecialFaturamentoActionForm.getVolumeFixoMedido().equals("")){
				
			situacaoEspecialFaturamentoHelper.setNumeroVolumeEsgotoMedido(
			new Integer(situacaoEspecialFaturamentoActionForm.getVolumeFixoMedido()));
		}
		
		if (situacaoEspecialFaturamentoActionForm.getVolumeFixoNaoMedido() != null &&
			!situacaoEspecialFaturamentoActionForm.getVolumeFixoNaoMedido().equals("")){
				
			situacaoEspecialFaturamentoHelper.setNumeroVolumeEsgotoNaoMedido(
			new Integer(situacaoEspecialFaturamentoActionForm.getVolumeFixoNaoMedido()));
		}
		
		if (situacaoEspecialFaturamentoActionForm.getObservacaoInforma() != null &&
			!situacaoEspecialFaturamentoActionForm.getObservacaoInforma().equals("")){
			
			situacaoEspecialFaturamentoHelper.setObservacaoInforma(
					situacaoEspecialFaturamentoActionForm.getObservacaoInforma());
					
			situacaoEspecialFaturamentoHelper.setObservacao(
			situacaoEspecialFaturamentoActionForm.getObservacaoInforma());
		}
		
		situacaoEspecialFaturamentoHelper.setUsuarioLogado(usuarioLogado);

		Integer quantidadeImoveisSem = 0;
		
		
		if (situacaoEspecialFaturamentoActionForm.getQuantidadeImoveisSEMSituacaoEspecialFaturamento()!=null 
				&& !situacaoEspecialFaturamentoActionForm.getQuantidadeImoveisSEMSituacaoEspecialFaturamento().equals("")){
			
			quantidadeImoveisSem =new Integer(situacaoEspecialFaturamentoActionForm.getQuantidadeImoveisSEMSituacaoEspecialFaturamento());
		}
		
		Integer quantidadeTotal =  quantidadeImoveisSem;
		
		situacaoEspecialFaturamentoHelper.setQuantidadeDeImoveis(quantidadeTotal.toString());
		
		situacaoEspecialFaturamentoHelper.setIdsCategoria(situacaoEspecialFaturamentoActionForm.getIdsCategoria());
		
		if (situacaoEspecialFaturamentoActionForm.getIdsCategoria() != null) {
			
			String [] idsCategoria = situacaoEspecialFaturamentoActionForm.getIdsCategoria();
			
			for (int i = 0; i < idsCategoria.length; i++) {
				
				if (idsCategoria[i].equals(Categoria.COMERCIAL.toString())) {
					situacaoEspecialFaturamentoHelper.setIndicadorComercial(ConstantesSistema.SIM.toString());
				} else if (idsCategoria[i].equals(Categoria.INDUSTRIAL.toString())) {
					situacaoEspecialFaturamentoHelper.setIndicadorIndustrial(ConstantesSistema.SIM.toString());
				} else if (idsCategoria[i].equals(Categoria.RESIDENCIAL.toString())) {
					situacaoEspecialFaturamentoHelper.setIndicadorResidencial(ConstantesSistema.SIM.toString());
				} else if (idsCategoria[i].equals(Categoria.PUBLICO.toString())) {
					situacaoEspecialFaturamentoHelper.setIndicadorPublico(ConstantesSistema.SIM.toString());
				}
				
			}
		}
		
		situacaoEspecialFaturamentoHelper.setIndicadorConsumoImovel(
				situacaoEspecialFaturamentoActionForm.getIndicadorConsumoImovel());
		
		
		return situacaoEspecialFaturamentoHelper;
	}	
	
	public FaturamentoSituacaoComando criarCopiaComando(FaturamentoSituacaoComando comando){
		FaturamentoSituacaoComando copiaComando = new FaturamentoSituacaoComando();
		
		copiaComando.setAnoMesFinalSituacaoFaturamento(comando.getAnoMesFinalSituacaoFaturamento());
		copiaComando.setAnoMesInicialSituacaoFaturamento(comando.getAnoMesInicialSituacaoFaturamento());
		copiaComando.setCodigoRotaFinal(comando.getCodigoRotaFinal());
		copiaComando.setCodigoRotaInicial(comando.getCodigoRotaInicial());
		copiaComando.setCodigoSetorComercialFinal(comando.getCodigoSetorComercialFinal());
		copiaComando.setCodigoSetorComercialInicial(comando.getCodigoSetorComercialInicial());		
		copiaComando.setNumeroLoteFinal(comando.getNumeroLoteFinal());
		copiaComando.setNumeroLoteInicial(comando.getNumeroLoteInicial());
		copiaComando.setNumeroQuadraFinal(comando.getNumeroQuadraFinal());
		copiaComando.setNumeroQuadraInicial(comando.getNumeroQuadraInicial());
		copiaComando.setNumeroSubLoteFinal(comando.getNumeroSubLoteFinal());
		copiaComando.setNumeroSubLoteInicial(comando.getNumeroSubLoteInicial());
		copiaComando.setObservacao(comando.getObservacao());
		copiaComando.setQuantidadeImoveis(comando.getQuantidadeImoveis());
		copiaComando.setSequencialRotaFinal(comando.getSequencialRotaFinal());
		copiaComando.setSequencialRotaInicial(comando.getSequencialRotaInicial());
		copiaComando.setUltimaAlteracao(comando.getUltimaAlteracao());

		
		if(comando.getCategoria1()!=null && comando.getCategoria1().getId()!=null){
			Categoria c = new Categoria();
			c.setId(comando.getCategoria1().getId());
			copiaComando.setCategoria1(c);
		}
		if(comando.getCategoria2()!=null && comando.getCategoria2().getId()!=null){
			Categoria c = new Categoria();
			c.setId(comando.getCategoria2().getId());
			copiaComando.setCategoria2(c);
		}
		if(comando.getCategoria3()!=null && comando.getCategoria3().getId()!=null){
			Categoria c = new Categoria();
			c.setId(comando.getCategoria3().getId());
			copiaComando.setCategoria3(c);
		}
		if(comando.getCategoria4()!=null && comando.getCategoria4().getId()!=null){
			Categoria c = new Categoria();
			c.setId(comando.getCategoria4().getId());
			copiaComando.setCategoria4(c);
		}
		
		if(comando.getFaturamentoSituacaoMotivo()!=null && comando.getFaturamentoSituacaoMotivo().getId()!=null){
			FaturamentoSituacaoMotivo fsm = new FaturamentoSituacaoMotivo();
			fsm.setId(comando.getFaturamentoSituacaoMotivo().getId());
			copiaComando.setFaturamentoSituacaoMotivo(fsm);
		}
		
		if(comando.getFaturamentoSituacaoTipo()!=null && comando.getFaturamentoSituacaoTipo().getId()!=null){
			FaturamentoSituacaoTipo fst = new FaturamentoSituacaoTipo();
			fst.setId(comando.getFaturamentoSituacaoTipo().getId());
			copiaComando.setFaturamentoSituacaoTipo(fst);			
		}
		
		copiaComando.setId(comando.getId());
		
		if(comando.getImovel()!=null && comando.getImovel().getId()!=null){
			Imovel imv = new Imovel();
			imv.setId(comando.getImovel().getId());
			copiaComando.setImovel(imv);
		}
		
		copiaComando.setIndicadorComando(comando.getIndicadorComando());
		copiaComando.setIndicadorConsumo(comando.getIndicadorConsumo());

		if(comando.getLocalidadeFinal()!=null && comando.getLocalidadeFinal().getId()!=null){
			Localidade loc = new Localidade();
			loc.setId(comando.getLocalidadeFinal().getId());
			copiaComando.setLocalidadeFinal(loc);
		}
		
		if(comando.getLocalidadeInicial()!=null && comando.getLocalidadeInicial().getId()!=null){
			Localidade loc = new Localidade();
			loc.setId(comando.getLocalidadeInicial().getId());
			copiaComando.setLocalidadeInicial(loc);
		}
		
		if(comando.getUsuario()!=null && comando.getUsuario().getId()!=null){
			Usuario user = new Usuario();
			user.setId(comando.getUsuario().getId());
			copiaComando.setUsuario(user);
		}
		
		return copiaComando;
	}

	public FaturamentoSituacaoHistorico criarCopiaHistorico(FaturamentoSituacaoHistorico historico){
		FaturamentoSituacaoHistorico copiaHistorico = new FaturamentoSituacaoHistorico();
		
		copiaHistorico.setAnoMesFaturamentoRetirada(historico.getAnoMesFaturamentoRetirada());
		copiaHistorico.setAnoMesFaturamentoSituacaoFim(historico.getAnoMesFaturamentoSituacaoFim());
		copiaHistorico.setAnoMesFaturamentoSituacaoInicio(historico.getAnoMesFaturamentoSituacaoInicio());
		copiaHistorico.setId(historico.getId());
		copiaHistorico.setIdAntigo(historico.getIdAntigo());
		copiaHistorico.setNumeroConsumoAguaMedido(historico.getNumeroConsumoAguaMedido());
		copiaHistorico.setNumeroConsumoAguaNaoMedido(historico.getNumeroConsumoAguaNaoMedido());
		copiaHistorico.setNumeroVolumeEsgotoMedido(historico.getNumeroVolumeEsgotoMedido());
		copiaHistorico.setNumeroVolumeEsgotoNaoMedido(historico.getNumeroVolumeEsgotoNaoMedido());
		copiaHistorico.setObservacaoInforma(historico.getObservacaoInforma());
		copiaHistorico.setObservacaoRetira(historico.getObservacaoRetira());
		copiaHistorico.setUltimaAlteracao(historico.getUltimaAlteracao());
		
		if(historico.getUsuarioRetira()!=null && historico.getUsuarioRetira().getId()!=null){
			Usuario user = new Usuario();
			user.setId(historico.getUsuarioRetira().getId());
			copiaHistorico.setUsuarioRetira(user);
		}
		
		if(historico.getUsuarioInforma()!=null && historico.getUsuarioInforma().getId()!=null){
			Usuario user = new Usuario();
			user.setId(historico.getUsuarioInforma().getId());
			copiaHistorico.setUsuarioInforma(user);
		}
		
		if(historico.getUsuario()!=null && historico.getUsuario().getId()!=null){
			Usuario user = new Usuario();
			user.setId(historico.getUsuario().getId());
			copiaHistorico.setUsuario(user);
		}
						
		if(historico.getImovel()!=null && historico.getImovel().getId()!=null){
			Imovel imv = new Imovel();
			imv.setId(historico.getImovel().getId());
			copiaHistorico.setImovel(imv);		
		}
		
		if(historico.getFaturamentoSituacaoTipo()!=null && historico.getFaturamentoSituacaoTipo().getId()!=null){
			FaturamentoSituacaoTipo fst = new FaturamentoSituacaoTipo();
			fst.setId(historico.getFaturamentoSituacaoTipo().getId());
			copiaHistorico.setFaturamentoSituacaoTipo(fst);
		}
		
		if(historico.getFaturamentoSituacaoMotivo()!=null && historico.getFaturamentoSituacaoMotivo().getId()!=null){
			FaturamentoSituacaoMotivo fsm = new FaturamentoSituacaoMotivo();
			fsm.setId(historico.getFaturamentoSituacaoMotivo().getId());
			copiaHistorico.setFaturamentoSituacaoMotivo(fsm);
		}

		if(historico.getFaturamentoSituacaoComandoRetirada()!=null && historico.getFaturamentoSituacaoComandoRetirada().getId()!=null){
			FaturamentoSituacaoComando fscr = new FaturamentoSituacaoComando();
			fscr.setId(historico.getFaturamentoSituacaoComandoRetirada().getId());			
			copiaHistorico.setFaturamentoSituacaoComandoRetirada(fscr);
		}
		
		if(historico.getFaturamentoSituacaoComandoInforma()!=null && historico.getFaturamentoSituacaoComandoInforma().getId()!=null){
			FaturamentoSituacaoComando fsci = new FaturamentoSituacaoComando();
			fsci.setId(historico.getFaturamentoSituacaoComandoInforma().getId());
			copiaHistorico.setFaturamentoSituacaoComandoInforma(fsci);
		}	
		
		
		return copiaHistorico;
	}

}
