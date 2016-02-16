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
package gcom.relatorio.atendimentopublico;

import gcom.atendimentopublico.ordemservico.bean.RelacaoOrdensServicoConcluidasHelper;
import gcom.batch.Relatorio;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * [UC0432] - Gerar Relatorio Acompanhamento Ordem de Servico de Hidrometro
 * 
 * Tipo 3 - Por Local de Instalacao - Relatorio Relacao das Ordens de Servico Concluidas.
 * 
 * @author Ivan S�rgio
 * @created 04/04/2008
 * 
 */
public class RelatorioRelacaoOrdensServicoConcluidas extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	
	public RelatorioRelacaoOrdensServicoConcluidas(Usuario usuario, String nomeRelatorio) {
		super(usuario, nomeRelatorio);
	}

	@Deprecated
	public RelatorioRelacaoOrdensServicoConcluidas() {
		super(null, "");
	}

	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		String tipoOrdem = (String) getParametro("tipoOrdem");
		String situacaoOrdemServico = (String) getParametro("situacaoOrdemServico").toString();
		String firma = (String) getParametro("firma");
		String nomeFirma = (String) getParametro("nomeFirma");
		String idLocalidadeInicial = (String) getParametro("idLocalidadeInicial");
		String idLocalidadeFinal = (String) getParametro("idLocalidadeFinal");
		String dataEncerramentoInicial = (String) getParametro("dataEncerramentoInicial");
		String dataEncerramentoFinal = (String) getParametro("dataEncerramentoFinal");

		
		String idMotivoEncerramento = (String) getParametro("idMotivoEncerramento").toString();

		String idSetorComercialInicial = (String) getParametro("idSetorComercialFinal");
		String idSetorComercialFinal = (String) getParametro("idSetorComercialFinal");
		String codigoQuadraInicial = (String) getParametro("codigoQuadraInicial");
		String codigoQuadraFinal = (String) getParametro("codigoQuadraFinal");
		String codigoRotaInicial = (String) getParametro("codigoRotaInicial");
		String codigoRotaFinal = (String) getParametro("codigoRotaFinal");
		String sequenciaRotaInicial = (String) getParametro("sequenciaRotaInicial");
		String sequenciaRotaFinal = (String) getParametro("sequenciaRotaFinal");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		// valor de retorno
		byte[] retorno = null;
		
		// cole��o de beans do relat�rio
		List<RelatorioRelacaoOrdensServicoConcluidasBean> relatorioBeans = new ArrayList<RelatorioRelacaoOrdensServicoConcluidasBean>();
		
		Fachada fachada = Fachada.getInstancia();
		
		RelatorioRelacaoOrdensServicoConcluidasBean relatorioBean = null;
		
		Collection<RelacaoOrdensServicoConcluidasHelper> colecaoDadosHelper = fachada
				.pesquisarOrdemServicoConcluidaGerarRelatorioAcompanhamentoHidrometro(
						firma, tipoOrdem, situacaoOrdemServico, idLocalidadeInicial,
						idLocalidadeFinal, dataEncerramentoInicial, dataEncerramentoFinal,
						idMotivoEncerramento, idSetorComercialInicial, idSetorComercialFinal,
						codigoQuadraInicial, codigoQuadraFinal, codigoRotaInicial, codigoRotaFinal,
						sequenciaRotaInicial, sequenciaRotaFinal);
		
		if (colecaoDadosHelper != null && !colecaoDadosHelper.isEmpty()) {
			Iterator<RelacaoOrdensServicoConcluidasHelper> iColecaoDados = colecaoDadosHelper.iterator();

			while (iColecaoDados.hasNext()) {
				//dados = (Object[]) iColecaoDados.next();
				RelacaoOrdensServicoConcluidasHelper helper = iColecaoDados.next(); 
				
				relatorioBean = new RelatorioRelacaoOrdensServicoConcluidasBean();
				
				// Numero da Ordem
				relatorioBean.setNumeroOrdem(helper.getIdOrdemServico().toString());
				// Imovel Selecionado
				relatorioBean.setImovel(helper.getIdImovel().toString());
				// Localidade
				relatorioBean.setIdLocalidade(helper.getIdLocalidade().toString());
				// Inscricao do Imovel
				Imovel imovel = new Imovel();
				
				Localidade localidade = new Localidade();
				localidade.setId(helper.getIdLocalidade());
				
				SetorComercial setorComercial = new SetorComercial();
				setorComercial.setCodigo(helper.getCodigoSetorComercial());
				
				Quadra quadra = new Quadra();
				quadra.setNumeroQuadra(helper.getNumeroQuadra());
				
				imovel.setId(helper.getIdImovel());
				imovel.setLocalidade(localidade);
				imovel.setSetorComercial(setorComercial);
				imovel.setQuadra(quadra);
				imovel.setLote(helper.getLote());
				imovel.setSubLote(helper.getSubLote());
				
				relatorioBean.setInscricao(imovel.getInscricaoFormatada());
				
				// Data Geracao
				relatorioBean.setDataGeracao(helper.getDataGeracao());
				// Data Encerramento
				relatorioBean.setDataEncerramento(helper.getDataEncerramento());
				// Motivo Encerramento
				relatorioBean.setMotivoEncerramento(helper.getMotivoEncerramento());
				
				// Situacao selecionada
				relatorioBean.setSituacao(situacaoOrdemServico);
				// Tipo do Servico
				relatorioBean.setTipoServico(tipoOrdem);
				// Periodo encerramento
				if (dataEncerramentoInicial != null && !dataEncerramentoInicial.equals("") &&
						dataEncerramentoFinal != null && !dataEncerramentoFinal.equals("")) {
					relatorioBean.setPeriodoEncerramento(dataEncerramentoInicial + " � " + dataEncerramentoFinal);
				}else {
					relatorioBean.setPeriodoEncerramento("");
				}
				// Nome Localidade
				relatorioBean.setNomeLocalidade(helper.getNomeLocalidade());
				// Firma
				relatorioBean.setFirma(firma);
				// Nome Firma
				relatorioBean.setNomeFirma(nomeFirma);
				// Endereco abreviado
				relatorioBean.setEndereco(fachada.obterEnderecoAbreviadoImovel(imovel.getId()));
				
				// Id do Setor Comercial
				relatorioBean.setIdSetorComercial(helper.getIdSetorComercial().toString());
				// Codigo Setor Comercial
				relatorioBean.setCodigoSetorComercial(helper.getCodigoSetorComercial().toString());
				// Nome Setor Comercial
				relatorioBean.setNomeSetorComercial(helper.getNomeSetorComercial());
				
				// Id do Local de Instalacao do Hidrometro
				relatorioBean.setIdLocalInstalacao(helper.getIdLocalInstalacaoHidrometro().toString());
				// Descricao do Local de Instalacao do Hidrometro
				relatorioBean.setDescricaoLocalInstalacao(helper.getDescricaoLocalInstalacaoHidrometro());
				
				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioBean);
			}
		
			// Par�metros do relat�rio
			Map<String, String> parametros = new HashMap<String, String>();
			
			SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

			parametros.put("imagem", sistemaParametro.getImagemRelatorio());

			// cria uma inst�ncia do dataSource do relat�rio
			RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

			
			if(this.nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_RELACAO_ORDENS_SERVICO_ENCERRADAS_MOTIVO_ENCERRAMENTO)){
				retorno = gerarRelatorio(
						ConstantesRelatorios.RELATORIO_RELACAO_ORDENS_SERVICO_ENCERRADAS_MOTIVO_ENCERRAMENTO,
						parametros, ds, tipoFormatoRelatorio);	
			}else{
				retorno = gerarRelatorio(
						ConstantesRelatorios.RELATORIO_RELACAO_ORDENS_SERVICO_CONCLUIDAS,
						parametros, ds, tipoFormatoRelatorio);
			}

			// Grava o relat�rio no sistema
			try {
				persistirRelatorioConcluido(retorno,
						Relatorio.RELATORIO_RELACAO_OS_CONCLUIDAS,
						idFuncionalidadeIniciada);
			} catch (ControladorException e) {
				e.printStackTrace();
				throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
			}
			// ------------------------------------
		}else {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		// retorna o relat�rio gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioRelacaoOrdensServicoConcluidas",
				this);
	}
}