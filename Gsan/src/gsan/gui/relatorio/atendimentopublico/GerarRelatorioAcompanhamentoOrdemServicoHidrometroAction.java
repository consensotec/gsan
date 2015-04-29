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
* Ivan S�rgio Virginio da Silva J�nior
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
package gsan.gui.relatorio.atendimentopublico;

import gsan.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.FiltroSetorComercial;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.SetorComercial;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.atendimentopublico.ordemservico.GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gsan.relatorio.atendimentopublico.RelatorioRelacaoOrdensServicoConcluidas;
import gsan.relatorio.atendimentopublico.RelatorioRelacaoOrdensServicoEncerradasPendentes;
import gsan.relatorio.atendimentopublico.RelatorioResumoOrdensServicoEncerradasPendentes;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Ivan S�rgio
 * @created 11/12/2007, 27/03/2008, 04/04/2008
 * @alteracao: 27/03/2008 - Adicionado Motivo Encerramento; Setor Comercial;
 * 			   04/04/2008 - Adicionado um novo relatorio quebrando por Local de Instala��o; 
 */
public class GerarRelatorioAcompanhamentoOrdemServicoHidrometroAction extends
		ExibidorProcessamentoTarefaRelatorio {

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

		// cria a vari�vel de retorno
		ActionForward retorno = null;

		GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm form = 
			(GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm) actionForm;

		// Recupera os valores do form para serem passados como par�metros para
		// o RelatorioAcompanhamentoOrdemServicoHidrometro
		String tipoOrdem = form.getTipoOrdem();
		String situacaoOrdemServico = form.getSituacaoOrdemServico();
		String firma = form.getFirma();
		String nomeFirma = form.getNomeFirma();
		String idLocalidadeInicial = form.getLocalidadeInicial();
		String nomeLocalidadeInicial = form.getNomeLocalidadeInicial();
		String idLocalidadeFinal = form.getLocalidadeFinal();
		String nomeLocalidadeFinal = form.getNomeLocalidadeFinal();
		String dataEncerramentoInicial = form.getDataEncerramentoInicial();
		String dataEncerramentoFinal = form.getDataEncerramentoFinal();
		
		if (dataEncerramentoInicial != null && !dataEncerramentoInicial.trim().equals("")) {
			dataEncerramentoInicial += " 00:00:00";
		}
		
		if (dataEncerramentoFinal != null && !dataEncerramentoFinal.trim().equals("")) {
			dataEncerramentoFinal += " 23:59:59";
		}
		
		String tipoRelatorioAcompanhamento = form.gettipoRelatorioAcompanhamento();
		
		String idMotivoEncerramento = form.getIdMotivoEncerramento();
		String descricaoMotivoEncerramento = form.getDescricaoMotivoEncerramento();
		String idSetorComercialInicial = form.getSetorComercialInicial();
		String idSetorComercialFinal = form.getSetorComercialFinal();
		String codigoSetorComercialInicial = form.getCodigoSetorComercialInicial();		
		String codigoSetorComercialFinal = form.getCodigoSetorComercialFinal();
		String nomeSetorComercialInicial = form.getNomeSetorComercialInicial();
		String nomeSetorComercialFinal = form.getNomeSetorComercialFinal();
		String codigoQuadraInicial = form.getQuadraInicial();
		String codigoQuadraFinal = form.getQuadraFinal();
		String codigoRotaInicial = form.getRotaInicial();
		String codigoRotaFinal = form.getRotaFinal();
		String sequenciaRotaInicial = form.getRotaSequenciaInicial();
		String sequenciaRotaFinal = form.getRotaSequenciaFinal();
		
		// Valida a Localidade Informada
		if (idLocalidadeInicial != null && !idLocalidadeInicial.equals("") &&
				idLocalidadeFinal != null && !idLocalidadeFinal.equals("")) {
			Localidade localidadeInicial = validarLocalidade(idLocalidadeInicial);
			Localidade localidadeFinal = validarLocalidade(idLocalidadeFinal);
			
			if (nomeLocalidadeInicial == null || nomeLocalidadeInicial.equals("")) {
				nomeLocalidadeInicial = localidadeInicial.getDescricao();
			}
			
			if (nomeLocalidadeFinal == null || nomeLocalidadeFinal.equals("")) {
				nomeLocalidadeFinal = localidadeFinal.getDescricao();
			}
		}
		
		// Valida o Setor Informado
		if (codigoSetorComercialInicial != null && !codigoSetorComercialInicial.equals("")&&
				codigoSetorComercialFinal != null && !codigoSetorComercialFinal.equals("")) {
			SetorComercial setorComercialInicial = validarSetorComercial(idLocalidadeInicial, codigoSetorComercialInicial);
			SetorComercial setorComercialFinal = validarSetorComercial(idLocalidadeFinal, codigoSetorComercialFinal);
			//inicial
			if (idSetorComercialInicial == null || idSetorComercialInicial.equals("")) {
				idSetorComercialInicial = setorComercialInicial.getId().toString();
			}
			if (nomeSetorComercialInicial == null || nomeSetorComercialInicial.equals("")) {
				nomeSetorComercialInicial = setorComercialInicial.getDescricao();
			}
			
			//final
			if (idSetorComercialFinal == null || idSetorComercialFinal.equals("")) {
				idSetorComercialFinal = setorComercialFinal.getId().toString();
			}
			if (nomeSetorComercialFinal == null || nomeSetorComercialFinal.equals("")) {
				nomeSetorComercialFinal = setorComercialFinal.getDescricao();
			}			
		}
		
		String tipoGeracaoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		// Verifica o Tipo do Relatorio Escolhido pelo usuario
		// 1 - Analitico (RelatorioRelacaoOrdensServicoEncerradasPendentes)
		// 2 - Sintetico (RelatorioResumoOrdensServicoEncerradasPendentes)
		// 3 - Por Local de Instalacao (RelatorioRelacaoOrdensServicoConcluidas)
		// 4 - Por Motivo de Encerramento (RelatorioRelacaoOrdensServicoConcluidas)
		if (Util.converterStringParaInteger(tipoRelatorioAcompanhamento).equals(
				GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm.TIPO_RELATORIO_ANALITICO)) {
			
			RelatorioRelacaoOrdensServicoEncerradasPendentes relatorio = 
				new RelatorioRelacaoOrdensServicoEncerradasPendentes(
					(Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

			relatorio.addParametro("tipoOrdem", tipoOrdem);
			relatorio.addParametro("situacaoOrdemServico", situacaoOrdemServico);
			relatorio.addParametro("idMotivoEncerramento", idMotivoEncerramento);
			relatorio.addParametro("descricaoMotivoEncerramento", descricaoMotivoEncerramento);
			relatorio.addParametro("firma", firma);
			relatorio.addParametro("nomeFirma", nomeFirma);
			relatorio.addParametro("idLocalidadeInicial", idLocalidadeInicial);
			relatorio.addParametro("idLocalidadeFinal", idLocalidadeFinal);		
			relatorio.addParametro("idSetorComercialInicial", idSetorComercialInicial);
			relatorio.addParametro("idSetorComercialFinal", idSetorComercialFinal);
			relatorio.addParametro("codigoSetorComercialInicial", codigoSetorComercialInicial);
			relatorio.addParametro("codigoSetorComercialFinal", codigoSetorComercialFinal);			
			relatorio.addParametro("dataEncerramentoInicial", dataEncerramentoInicial);
			relatorio.addParametro("dataEncerramentoFinal", dataEncerramentoFinal);
			relatorio.addParametro("tipoRelatorioAcompanhamento", tipoRelatorioAcompanhamento);
			
			relatorio.addParametro("codigoQuadraInicial", codigoQuadraInicial);
			relatorio.addParametro("codigoQuadraFinal", codigoQuadraFinal);
			relatorio.addParametro("codigoRotaInicial", codigoRotaInicial);
			relatorio.addParametro("codigoRotaFinal", codigoRotaFinal);
			relatorio.addParametro("sequenciaRotaInicial", sequenciaRotaInicial);
			relatorio.addParametro("sequenciaRotaFinal", sequenciaRotaFinal);
			
			if (tipoGeracaoRelatorio == null) {
				tipoGeracaoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}

			relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoGeracaoRelatorio));
			
			retorno = processarExibicaoRelatorio(relatorio,
					tipoGeracaoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);

		}else if (Util.converterStringParaInteger(tipoRelatorioAcompanhamento).equals(
				GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm.TIPO_RELATORIO_SINTETICO)) {
			
			if(situacaoOrdemServico != null && situacaoOrdemServico.equals("PENDENTES")){
				throw new ActionServletException("atencao.tipo_relatorio_invalido");
			}
			
			RelatorioResumoOrdensServicoEncerradasPendentes relatorio = 
				new RelatorioResumoOrdensServicoEncerradasPendentes(
					(Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

			relatorio.addParametro("tipoOrdem", tipoOrdem);
			relatorio.addParametro("situacaoOrdemServico", situacaoOrdemServico);
			relatorio.addParametro("idMotivoEncerramento", idMotivoEncerramento);
			relatorio.addParametro("descricaoMotivoEncerramento", descricaoMotivoEncerramento);
			relatorio.addParametro("firma", firma);
			relatorio.addParametro("nomeFirma", nomeFirma);
			relatorio.addParametro("idLocalidadeInicial", idLocalidadeInicial);
			relatorio.addParametro("idLocalidadeFinal", idLocalidadeFinal);			
			relatorio.addParametro("idSetorComercialInicial", idSetorComercialInicial);
			relatorio.addParametro("idSetorComercialFinal", idSetorComercialFinal);
			relatorio.addParametro("codigoSetorComercialInicial", codigoSetorComercialInicial);
			relatorio.addParametro("codigoSetorComercialFinal", codigoSetorComercialFinal);			
			relatorio.addParametro("dataEncerramentoInicial", dataEncerramentoInicial);
			relatorio.addParametro("dataEncerramentoFinal", dataEncerramentoFinal);
			relatorio.addParametro("tipoRelatorioAcompanhamento", tipoRelatorioAcompanhamento);

			relatorio.addParametro("codigoQuadraInicial", codigoQuadraInicial);
			relatorio.addParametro("codigoQuadraFinal", codigoQuadraFinal);
			relatorio.addParametro("codigoRotaInicial", codigoRotaInicial);
			relatorio.addParametro("codigoRotaFinal", codigoRotaFinal);
			relatorio.addParametro("sequenciaRotaInicial", sequenciaRotaInicial);
			relatorio.addParametro("sequenciaRotaFinal", sequenciaRotaFinal);
			
			if (tipoGeracaoRelatorio == null) {
				tipoGeracaoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}

			relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoGeracaoRelatorio));
			
			retorno = processarExibicaoRelatorio(relatorio,
					tipoGeracaoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);
		}else if (Util.converterStringParaInteger(tipoRelatorioAcompanhamento).equals(
				GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm.TIPO_RELATORIO_POR_LOCAL_INSTALACAO)) {
			
			RelatorioRelacaoOrdensServicoConcluidas relatorio = 
				new RelatorioRelacaoOrdensServicoConcluidas((Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"),
						ConstantesRelatorios.RELATORIO_RELACAO_ORDENS_SERVICO_ENCERRADAS_PENDENTES);
			
			relatorio.addParametro("tipoOrdem", tipoOrdem);
			relatorio.addParametro("situacaoOrdemServico", "ENCERRADAS");
			relatorio.addParametro("idMotivoEncerramento", AtendimentoMotivoEncerramento.CONCLUSAO_SERVICO);
			relatorio.addParametro("descricaoMotivoEncerramento", descricaoMotivoEncerramento);
			relatorio.addParametro("firma", firma);
			relatorio.addParametro("nomeFirma", nomeFirma);
			relatorio.addParametro("idLocalidadeInicial", idLocalidadeInicial);
			relatorio.addParametro("idLocalidadeFinal", idLocalidadeFinal);			
			relatorio.addParametro("idSetorComercialInicial", idSetorComercialInicial);
			relatorio.addParametro("idSetorComercialFinal", idSetorComercialFinal);
			relatorio.addParametro("codigoSetorComercialInicial", codigoSetorComercialInicial);
			relatorio.addParametro("codigoSetorComercialFinal", codigoSetorComercialFinal);			
			relatorio.addParametro("dataEncerramentoInicial", dataEncerramentoInicial);
			relatorio.addParametro("dataEncerramentoFinal", dataEncerramentoFinal);
			relatorio.addParametro("tipoRelatorioAcompanhamento", tipoRelatorioAcompanhamento);
			
			relatorio.addParametro("codigoQuadraInicial", codigoQuadraInicial);
			relatorio.addParametro("codigoQuadraFinal", codigoQuadraFinal);
			relatorio.addParametro("codigoRotaInicial", codigoRotaInicial);
			relatorio.addParametro("codigoRotaFinal", codigoRotaFinal);
			relatorio.addParametro("sequenciaRotaInicial", sequenciaRotaInicial);
			relatorio.addParametro("sequenciaRotaFinal", sequenciaRotaFinal);
			
			if (tipoGeracaoRelatorio == null) {
				tipoGeracaoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}

			relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoGeracaoRelatorio));
			
			retorno = processarExibicaoRelatorio(relatorio,
					tipoGeracaoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);
						
		}else if (Util.converterStringParaInteger(tipoRelatorioAcompanhamento).equals(
				GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm.TIPO_RELATORIO_POR_MOTIVO_ENCERRAMENTO)) {
			
			RelatorioRelacaoOrdensServicoConcluidas relatorio = 
				new RelatorioRelacaoOrdensServicoConcluidas((Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"),
						ConstantesRelatorios.RELATORIO_RELACAO_ORDENS_SERVICO_ENCERRADAS_MOTIVO_ENCERRAMENTO);
			
			relatorio.addParametro("tipoOrdem", tipoOrdem);
			relatorio.addParametro("situacaoOrdemServico", "ENCERRADAS");
			relatorio.addParametro("idMotivoEncerramento", idMotivoEncerramento);
			relatorio.addParametro("descricaoMotivoEncerramento", descricaoMotivoEncerramento);
			relatorio.addParametro("firma", firma);
			relatorio.addParametro("nomeFirma", nomeFirma);
			relatorio.addParametro("idLocalidadeInicial", idLocalidadeInicial);
			relatorio.addParametro("idLocalidadeFinal", idLocalidadeFinal);			
			relatorio.addParametro("idSetorComercialInicial", idSetorComercialInicial);
			relatorio.addParametro("idSetorComercialFinal", idSetorComercialFinal);
			relatorio.addParametro("codigoSetorComercialInicial", codigoSetorComercialInicial);
			relatorio.addParametro("codigoSetorComercialFinal", codigoSetorComercialFinal);			
			relatorio.addParametro("dataEncerramentoInicial", dataEncerramentoInicial);
			relatorio.addParametro("dataEncerramentoFinal", dataEncerramentoFinal);
			relatorio.addParametro("tipoRelatorioAcompanhamento", tipoRelatorioAcompanhamento);
			
			relatorio.addParametro("codigoQuadraInicial", codigoQuadraInicial);
			relatorio.addParametro("codigoQuadraFinal", codigoQuadraFinal);
			relatorio.addParametro("codigoRotaInicial", codigoRotaInicial);
			relatorio.addParametro("codigoRotaFinal", codigoRotaFinal);
			relatorio.addParametro("sequenciaRotaInicial", sequenciaRotaInicial);
			relatorio.addParametro("sequenciaRotaFinal", sequenciaRotaFinal);
			
			if (tipoGeracaoRelatorio == null) {
				tipoGeracaoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}

			relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoGeracaoRelatorio));
			
			retorno = processarExibicaoRelatorio(relatorio,
					tipoGeracaoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);
						
		}
		return retorno;
	}

	private Localidade validarLocalidade(String localidade) {
		Localidade retorno = null;
		
		Fachada fachada = Fachada.getInstancia();

		FiltroLocalidade filtroLocalidade = null;
		Collection<Localidade> colecaoLocalidade = null;
		
		// Verifica se a Localidade existe
		if (localidade != null && !localidade.equals("")) {
			filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, localidade));
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			
			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				Iterator<Localidade> iColecaoLocalidade = colecaoLocalidade.iterator();
				retorno = iColecaoLocalidade.next();
			}else {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Localidade");
			}
		}
		
		return retorno;
	}
	
	private SetorComercial validarSetorComercial(String localidade, String codigoSetorComercial) {
		SetorComercial retorno = null;
		
		Fachada fachada = Fachada.getInstancia();

		FiltroSetorComercial filtroSetorComercial = null;
		Collection<SetorComercial> colecaoSetorComercial = null;
		
		// Verifica se o Setor existe
		if (codigoSetorComercial != null && !codigoSetorComercial.equals("")) {
			filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.ID_LOCALIDADE, localidade));
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercial));
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			colecaoSetorComercial = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
			
			if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {
				Iterator<SetorComercial> iColecaoSetorComercial = colecaoSetorComercial.iterator();
				retorno = iColecaoSetorComercial.next();
			}else {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Setor Comercial");
			}
		}
		return retorno;
	}
}