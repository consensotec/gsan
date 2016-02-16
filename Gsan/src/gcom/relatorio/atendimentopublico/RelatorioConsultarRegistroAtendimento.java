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
package gcom.relatorio.atendimentopublico;

import gcom.atendimentopublico.registroatendimento.AtendimentoRelacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoUnidade;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoUnidade;
import gcom.atendimentopublico.registroatendimento.bean.ObterDadosRegistroAtendimentoHelper;
import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * classe respons�vel por criar o relat�rio de regitro atendimento manter de
 * �gua
 * 
 * @author Rafael Corr�a
 * @created 11 de Julho de 2005
 */
public class RelatorioConsultarRegistroAtendimento extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioConsultarRegistroAtendimento(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_CONSULTAR_REGISTRO_ATENDIMENTO);
	}
	
	@Deprecated
	public RelatorioConsultarRegistroAtendimento() {
		super(null, "");
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param bairros
	 *            Description of the Parameter
	 * @param bairroParametros
	 *            Description of the Parameter
	 * @return Descri��o do retorno
	 * @exception RelatorioVazioException
	 *                Descri��o da exce��o
	 */
	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		Integer idRegistroAtendimento = (Integer) getParametro("idRegistroAtendimento");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioConsultarRegistroAtendimentoBean relatorioBean = null;
		RelatorioConsultarRegistroAtendimentoDetailBean relatorioDetailBean = null;

		ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper = fachada
				.obterDadosRegistroAtendimento(idRegistroAtendimento);

		// Seta um valor para um indicador que ser� comparado no relat�rio para
		// imprimir o t�tulo associado do n�mero e da situa��o da RA
		String indicadorAssociado = "";

		if (registroAtendimentoHelper.getTituloNumeroRAAssociado() != null
				&& registroAtendimentoHelper.getTituloNumeroRAAssociado()
						.equalsIgnoreCase("N�mero do RA de Refer�ncia:")) {
			indicadorAssociado = "1";
		} else if (registroAtendimentoHelper.getTituloNumeroRAAssociado() != null
				&& registroAtendimentoHelper.getTituloNumeroRAAssociado()
						.equalsIgnoreCase("N�mero do RA Atual:")) {
			indicadorAssociado = "2";
		} else if (registroAtendimentoHelper.getTituloNumeroRAAssociado() != null
				&& registroAtendimentoHelper.getTituloNumeroRAAssociado()
						.equalsIgnoreCase("N�mero do RA Anterior:")) {
			indicadorAssociado = "3";
		}
		
		String unidadeEncerramento = "";
		String usuarioEncerramento = "";
		
		UnidadeOrganizacional unidadeEncerramentoRA = 
			fachada.obterUnidadeEncerramentoRA(registroAtendimentoHelper.getRegistroAtendimento().getId());
		
		if(unidadeEncerramentoRA != null){
			
			unidadeEncerramento = unidadeEncerramentoRA.getId() + " - " + unidadeEncerramentoRA.getDescricao();
			
			RegistroAtendimentoUnidade registroAtendimentoUnidade = 
				this.consultarRegistroAtendimentoUnidade(registroAtendimentoHelper.getRegistroAtendimento().getId(),unidadeEncerramentoRA.getId());
			
			Usuario usuario = registroAtendimentoUnidade.getUsuario();
			if(usuario != null){
				usuarioEncerramento = usuario.getId() + " - " + usuario.getNomeUsuario();
			}
		}
		
		// Montando Bean Detail
		Collection<RelatorioConsultarRegistroAtendimentoDetailBean> colecaoDetail = new ArrayList<RelatorioConsultarRegistroAtendimentoDetailBean>();
		
		Collection colecaoDadosReiteracao = new ArrayList();
		
		colecaoDadosReiteracao = fachada.pesquisarDadosReiteracao(registroAtendimentoHelper.getRegistroAtendimento().getId());
		
		Iterator iColecaoDadosReiteracao = colecaoDadosReiteracao.iterator();
		
		while (iColecaoDadosReiteracao.hasNext()){
			Object[] dadosReiteracao = (Object[]) iColecaoDadosReiteracao.next();
			
			Date dt = (Date) dadosReiteracao[0];
			String dataHora = Util.formatarDataComHora(dt);
			
			String nomeSolicitante = "";
			
			if (dadosReiteracao[1] != null){
				nomeSolicitante = dadosReiteracao[1].toString();
			} else if (dadosReiteracao[2] != null){
				nomeSolicitante = dadosReiteracao[2].toString();
			} else if (dadosReiteracao[3] != null){
				nomeSolicitante = dadosReiteracao[3].toString();
			}
			
			String fone = "";

			if (dadosReiteracao[8] == null && dadosReiteracao[6] != null && dadosReiteracao[7] != null){
				fone = "(" + dadosReiteracao[6] + ")" + " - " + dadosReiteracao[7];
			} else if (dadosReiteracao[6] == null && dadosReiteracao[7] != null && dadosReiteracao[8] != null){
				fone = dadosReiteracao[7] + " - " + dadosReiteracao[8];
			} else if (dadosReiteracao[6] == null && dadosReiteracao[8] == null && dadosReiteracao[7] != null){
				fone = dadosReiteracao[7].toString();
			} else if (dadosReiteracao[8] != null && dadosReiteracao[6] != null && dadosReiteracao[7] != null){
				fone = "(" + dadosReiteracao[6] + ")" + " - " + dadosReiteracao[7] + " - " + dadosReiteracao[8];
			}
			
				relatorioDetailBean = new RelatorioConsultarRegistroAtendimentoDetailBean(
				
					// Data Hora
					dataHora,
					
					// Nome do Solicidante
					nomeSolicitante,
					
					// Cliente
					dadosReiteracao[4] == null ? ""
							: dadosReiteracao[4]
									.toString(),
									
					// Unidade
					dadosReiteracao[5] == null ? ""
							: dadosReiteracao[5]
									.toString(),
									
					// Telefone
					fone
				);
			
			colecaoDetail.add(relatorioDetailBean);
		}
		
		

		relatorioBean = new RelatorioConsultarRegistroAtendimentoBean(

				// Dados Gerais

				// N�mero RA
				registroAtendimentoHelper.getRegistroAtendimento().getId()
						.toString(),

				// Situa��o RA
				registroAtendimentoHelper.getDescricaoSituacaoRA(),

				// Indicador RA Associado
				indicadorAssociado,

				// N�mero RA Associado
				registroAtendimentoHelper.getRAAssociado() == null ? ""
						: registroAtendimentoHelper.getRAAssociado().getId()
								.toString(),

				// Situa��o RA Associado
				registroAtendimentoHelper.getDescricaoSituacaoRAAssociado(),

				// Tipo Solicita��o
				registroAtendimentoHelper.getRegistroAtendimento()
						.getSolicitacaoTipoEspecificacao() == null ? ""
						: registroAtendimentoHelper.getRegistroAtendimento()
								.getSolicitacaoTipoEspecificacao()
								.getSolicitacaoTipo().getDescricao(),

				// Especifica��o
				registroAtendimentoHelper.getRegistroAtendimento()
						.getSolicitacaoTipoEspecificacao() == null ? ""
						: registroAtendimentoHelper.getRegistroAtendimento()
								.getSolicitacaoTipoEspecificacao()
								.getDescricao(),

				// Data Atendimento
				Util.formatarDataComHora(registroAtendimentoHelper
						.getRegistroAtendimento().getRegistroAtendimento()),

				// Data Prevista
				Util.formatarData(registroAtendimentoHelper
						.getRegistroAtendimento().getDataPrevistaAtual()),

				// Meio Solicita��o
				registroAtendimentoHelper.getRegistroAtendimento()
						.getMeioSolicitacao() == null ? ""
						: registroAtendimentoHelper.getRegistroAtendimento()
								.getMeioSolicitacao().getDescricao(),

				// Unidade Atendimento
				registroAtendimentoHelper.getUnidadeAtendimento() == null ? ""
						: registroAtendimentoHelper.getUnidadeAtendimento()
								.getDescricao(),

				// Unidade Atual
				registroAtendimentoHelper.getUnidadeAtual() == null ? ""
						: registroAtendimentoHelper.getUnidadeAtual()
								.getDescricao(),

				// Observa��o
				registroAtendimentoHelper.getRegistroAtendimento()
						.getObservacao(),

				// Dados do Local da Ocorr�ncia

				// Matr�cula do Im�vel
				registroAtendimentoHelper.getRegistroAtendimento().getImovel() == null ? ""
						: registroAtendimentoHelper.getRegistroAtendimento()
								.getImovel().getId().toString(),

				// Inscri��o do Im�vel
				registroAtendimentoHelper.getRegistroAtendimento().getImovel() == null ? ""
						: registroAtendimentoHelper.getRegistroAtendimento()
								.getImovel().getInscricaoFormatada(),

				// Endere�o da Ocorr�ncia
				registroAtendimentoHelper.getEnderecoOcorrencia(),

				// Ponto de Refer�ncia
				registroAtendimentoHelper.getRegistroAtendimento()
						.getPontoReferencia(),

				// Munic�pio
				registroAtendimentoHelper.getRegistroAtendimento()
						.getBairroArea() == null ? ""
						: registroAtendimentoHelper.getRegistroAtendimento()
								.getBairroArea().getBairro().getMunicipio()
								.getNome(),

				// Bairro
				registroAtendimentoHelper.getRegistroAtendimento()
						.getBairroArea() == null ? ""
						: registroAtendimentoHelper.getRegistroAtendimento()
								.getBairroArea().getBairro().getNome(),

				// �rea do Bairro
				registroAtendimentoHelper.getRegistroAtendimento()
						.getBairroArea() == null ? ""
						: registroAtendimentoHelper.getRegistroAtendimento()
								.getBairroArea().getNome(),

				// Localidade/Setor/Quadra
				(registroAtendimentoHelper.getRegistroAtendimento()
						.getLocalidade() == null ? "---" : Util
						.adicionarZerosEsquedaNumero(3,
								registroAtendimentoHelper
										.getRegistroAtendimento()
										.getLocalidade().getId().toString()))
						+ "/"
						+ (registroAtendimentoHelper.getRegistroAtendimento()
								.getSetorComercial() == null ? "---" : Util
								.adicionarZerosEsquedaNumero(3, ""
										+ registroAtendimentoHelper
												.getRegistroAtendimento()
												.getSetorComercial()
												.getCodigo()))
						+ "/"
						+ (registroAtendimentoHelper.getRegistroAtendimento()
								.getQuadra() == null ? "---" : Util
								.adicionarZerosEsquedaNumero(3, ""
										+ registroAtendimentoHelper
												.getRegistroAtendimento()
												.getQuadra().getNumeroQuadra())),

				// Divis�o Esgoto
				registroAtendimentoHelper.getRegistroAtendimento()
						.getDivisaoEsgoto() == null ? ""
						: registroAtendimentoHelper.getRegistroAtendimento()
								.getDivisaoEsgoto().getDescricao(),

				// Local da Ocorr�ncia
				registroAtendimentoHelper.getRegistroAtendimento()
						.getLocalOcorrencia() == null ? ""
						: registroAtendimentoHelper.getRegistroAtendimento()
								.getLocalOcorrencia().getDescricao(),

				// Pavimento Rua
				registroAtendimentoHelper.getRegistroAtendimento()
						.getPavimentoRua() == null ? ""
						: registroAtendimentoHelper.getRegistroAtendimento()
								.getPavimentoRua().getDescricao(),

				// Pavimento Cal�ada
				registroAtendimentoHelper.getRegistroAtendimento()
						.getPavimentoCalcada() == null ? ""
						: registroAtendimentoHelper.getRegistroAtendimento()
								.getPavimentoCalcada().getDescricao(),

				// Descri��o do Local da Ocorr�ncia
				registroAtendimentoHelper.getRegistroAtendimento()
						.getDescricaoLocalOcorrencia(),

				// Dados do Solicitante

				// C�digo do Cliente
				registroAtendimentoHelper.getSolicitante() == null ? ""
						: registroAtendimentoHelper.getSolicitante()
								.getCliente() == null ? ""
								: registroAtendimentoHelper.getSolicitante()
										.getCliente().getId().toString(),

				// Nome do Cliente
				registroAtendimentoHelper.getSolicitante() == null ? ""
						: registroAtendimentoHelper.getSolicitante()
								.getCliente() == null ? ""
								: registroAtendimentoHelper.getSolicitante()
										.getCliente().getNome(),
				
				//Protocolo
				registroAtendimentoHelper.getSolicitante() == null ? ""
					: registroAtendimentoHelper.getSolicitante()
						.getNumeroProtocoloAtendimento() == null ? ""
								: registroAtendimentoHelper.getSolicitante()
									.getNumeroProtocoloAtendimento(),
						

				// Unidade Solicitante
				registroAtendimentoHelper.getSolicitante() == null ? ""
						: registroAtendimentoHelper.getSolicitante()
								.getUnidadeOrganizacional() == null ? ""
								: registroAtendimentoHelper.getSolicitante()
										.getUnidadeOrganizacional()
										.getDescricao(),

				// C�digo do Funcion�rio Respons�vel
				registroAtendimentoHelper.getSolicitante() == null ? ""
						: registroAtendimentoHelper.getSolicitante()
								.getFuncionario() == null ? ""
								: registroAtendimentoHelper.getSolicitante()
										.getFuncionario().getId().toString(),

				// Nome do Funcion�rio Respons�vel
				registroAtendimentoHelper.getSolicitante() == null ? ""
						: registroAtendimentoHelper.getSolicitante()
								.getFuncionario() == null ? ""
								: registroAtendimentoHelper.getSolicitante()
										.getFuncionario().getNome(),

				// Nome do Solicitante
				registroAtendimentoHelper.getSolicitante() == null ? ""
						: registroAtendimentoHelper.getSolicitante()
								.getSolicitante(),

				// Dados da Reitera��o

				colecaoDetail,

				// Dados do Encerramento

				// Motivo do Encerramento
				registroAtendimentoHelper.getRegistroAtendimento()
						.getAtendimentoMotivoEncerramento() == null ? ""
						: registroAtendimentoHelper.getRegistroAtendimento()
								.getAtendimentoMotivoEncerramento()
								.getDescricao(),

				// N�mero RA Refer�ncia
				registroAtendimentoHelper.getRAAssociado() == null ? ""
						: registroAtendimentoHelper.getRAAssociado().getId()
								.toString(),

				// Situa��o RA Refer�ncia
				registroAtendimentoHelper.getDescricaoSituacaoRAAssociado() == null ? ""
						: registroAtendimentoHelper
								.getDescricaoSituacaoRAAssociado(),

				// Data Encerramento
				registroAtendimentoHelper.getRegistroAtendimento()
						.getDataEncerramento() == null ? ""
						: Util
								.formatarDataComHora(registroAtendimentoHelper
										.getRegistroAtendimento()
										.getDataEncerramento()),
//				registroAtendimentoHelper.getRegistroAtendimento()
//						.getAtendimentoMotivoEncerramento() == null ? ""
//						: registroAtendimentoHelper.getRegistroAtendimento()
//								.getAtendimentoMotivoEncerramento()
//								.getUltimaAlteracao() == null ? "" : Util
//								.formatarDataComHora(registroAtendimentoHelper
//										.getRegistroAtendimento()
//										.getAtendimentoMotivoEncerramento()
//										.getUltimaAlteracao()),

				// Unidade Encerramento
				unidadeEncerramento,

				// Usu�rio Encerramento
				usuarioEncerramento,

				// Parecer Encerramento
				registroAtendimentoHelper.getRegistroAtendimento()
						.getParecerEncerramento() == null ? ""
						: registroAtendimentoHelper.getRegistroAtendimento()
								.getParecerEncerramento(),
				
				// Rota
				registroAtendimentoHelper.getCodigoRota() == null? "": registroAtendimentoHelper.getCodigoRota().toString(),
						
				// Sequencial Rota
				registroAtendimentoHelper.getSequencialRota() == null? "": registroAtendimentoHelper.getSequencialRota().toString() 
				
		);

		// adiciona o bean a cole��o
		relatorioBeans.add(relatorioBean);
		
		// __________________________________________________________________

		// Par�metros do relat�rio
		Map parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_CONSULTAR_REGISTRO_ATENDIMENTO,
				parametros, ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.CONSULTAR_REGISTRO_ATENDIMENTO,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		}
		// ------------------------------------

		// retorna o relat�rio gerado
		return retorno;
	}

	/**
	 * Consulta o Registro Atendimento Unidade pelo id da RA
	 * 
	 * @author Rafael Pinto
	 * @created 09/08/2006
	 */
	private RegistroAtendimentoUnidade consultarRegistroAtendimentoUnidade(Integer idRA,Integer idUnidade){

		RegistroAtendimentoUnidade retorno = null;
		
		Fachada fachada = Fachada.getInstancia();

		Collection colecaoRegistroAtendimentoUnidade = null; 

		FiltroRegistroAtendimentoUnidade filtroRegistroAtendimentoUnidade = new FiltroRegistroAtendimentoUnidade();

		filtroRegistroAtendimentoUnidade.adicionarParametro(
			new ParametroSimples(FiltroRegistroAtendimentoUnidade.REGISTRO_ATENDIMENTO_ID,idRA));

		filtroRegistroAtendimentoUnidade.adicionarParametro(
				new ParametroSimples(FiltroRegistroAtendimentoUnidade.UNIDADE_ORGANIZACIONAL_ID,idUnidade));
		
		filtroRegistroAtendimentoUnidade.adicionarParametro(
				new ParametroSimples(FiltroRegistroAtendimentoUnidade.ATENDIMENTO_RELACAO_TIPO, AtendimentoRelacaoTipo.ENCERRAR));

		filtroRegistroAtendimentoUnidade.adicionarCaminhoParaCarregamentoEntidade("usuario");
		
		colecaoRegistroAtendimentoUnidade = 
			fachada.pesquisar(filtroRegistroAtendimentoUnidade,RegistroAtendimentoUnidade.class.getName());

		if (colecaoRegistroAtendimentoUnidade != null && !colecaoRegistroAtendimentoUnidade.isEmpty()) {
			retorno = (RegistroAtendimentoUnidade) Util.retonarObjetoDeColecao(colecaoRegistroAtendimentoUnidade);
			
		} 
		
		return retorno;
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 1;
		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioConsultarRegistroAtendimento", this);
	}
}