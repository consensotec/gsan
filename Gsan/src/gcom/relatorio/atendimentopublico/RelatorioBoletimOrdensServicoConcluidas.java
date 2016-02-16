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

import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.bean.RelatorioBoletimOrdensServicoConcluidasHelper;
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
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * [UC0766] - Relatorio Boletim de Ordens de Servico Concluidas
 * 
 * @author Ivan S�rgio
 * @created 07/05/2008
 * 
 */
public class RelatorioBoletimOrdensServicoConcluidas extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	
	public RelatorioBoletimOrdensServicoConcluidas(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_BOLETIM_ORDENS_SERVICO_CONCLUIDAS);
	}

	@Deprecated
	public RelatorioBoletimOrdensServicoConcluidas() {
		super(null, "");
	}

	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		String idFirma = (String) getParametro("idFirma");

		String idLocalidade = (String) getParametro("idLocalidade");
		String idSetorComercial = (String) getParametro("idSetorComercial");
		String anoMesReferenciaEncerramento = (String) getParametro("anoMesReferenciaEncerramento");
		String situacao = (String) getParametro("situacao");		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		// valor de retorno
		byte[] retorno = null;
		
		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();
		
		Fachada fachada = Fachada.getInstancia();
		
		RelatorioBoletimOrdensServicoConcluidasBean relatorioBean = null;
		
		Integer empresa = new Integer(idFirma);
		Integer localidade = null;
		if (idLocalidade != null) {
			if (!idLocalidade.equals("")) {
				localidade = new Integer(idLocalidade);				
			}
		}
		Integer setorComercial = null;
		if (idSetorComercial != null) {
			if (!idSetorComercial.equals("")) {
				setorComercial = new Integer(idSetorComercial);
			}
		}
		String referenciaEncerramento = Util.formatarMesAnoParaAnoMesSemBarra(anoMesReferenciaEncerramento);
		Short situacaoBoletim = new Short(situacao);
		
		Collection colecaoDadosHelper = fachada
				.pesquisarBoletimOrdensServicoConcluidasGerarRelatorio(
						empresa, localidade, setorComercial, referenciaEncerramento, situacaoBoletim);
		
		if (colecaoDadosHelper != null && !colecaoDadosHelper.isEmpty()) {
			Iterator iColecaoDados = colecaoDadosHelper.iterator();

			while (iColecaoDados.hasNext()) {
				RelatorioBoletimOrdensServicoConcluidasHelper helper = 
					(RelatorioBoletimOrdensServicoConcluidasHelper) iColecaoDados.next(); 
				
				relatorioBean = new RelatorioBoletimOrdensServicoConcluidasBean();
				
				relatorioBean.setIdOrdemServico(helper.getIdOrdemServico().toString());
				
				Integer mes = new Integer(helper.getAnoMesReferenciaBoletim().toString().substring(4, 6));
				String ano = helper.getAnoMesReferenciaBoletim().toString().substring(0, 4);
				relatorioBean.setReferenciaEncerramentoMes(Util.retornaDescricaoMes(mes));
				relatorioBean.setReferenciaEncerramentoAno(ano);
				
				if (situacaoBoletim == 1) {
					relatorioBean.setSituacao("EM FISCALIZA��O/APROVADAS");
				}else {
					relatorioBean.setSituacao("REPROVADAS");
				}
				
				relatorioBean.setIdImovel(helper.getIdImovel().toString());
				relatorioBean.setIdLocalidade(helper.getIdLocalidade().toString());
				relatorioBean.setNomeLocalidade(helper.getDescricaoLocalidade());
				relatorioBean.setIdLocalInstalacao(helper.getIdLocalInstalacaoHidrometro().toString());
				relatorioBean.setDescricaoLocalInstalacao(helper.getDescricaoLocalInstalacaoHidrometro());
				relatorioBean.setNomeAbreviadoFirma(helper.getDescricaoAbreviadaFirma());
								
				// Inscricao do Imovel
				Imovel imovel = new Imovel();
				Localidade local = new Localidade();
				local.setId(helper.getIdLocalidade());
				
				SetorComercial setor = new SetorComercial();
				setor.setCodigo(helper.getCodigoSetorComercial());
				
				Quadra quadra = new Quadra();
				quadra.setNumeroQuadra(helper.getNumeroQuadra());
				
				imovel.setLocalidade(local);
				imovel.setSetorComercial(setor);
				imovel.setQuadra(quadra);
				imovel.setLote(helper.getLote());
				imovel.setSubLote(helper.getSubLote());
				
				relatorioBean.setInscricao(imovel.getInscricaoFormatada());
				//*****************************************************************************
				
				relatorioBean.setIdSetorComercial(helper.getIdSetorComercial().toString());
				relatorioBean.setCodigoSetorComercial(helper.getCodigoSetorComercial().toString());
				relatorioBean.setIdOrdemServico(helper.getIdOrdemServico().toString());
				
				if (helper.getIdTipoServico().equals(ServicoTipo.TIPO_EFETUAR_INSTALACAO_HIDROMETRO)) {
					relatorioBean.setTipoServico("INST");
				}else if(helper.getIdTipoServico().equals(ServicoTipo.TIPO_EFETUAR_SUBSTITUICAO_HIDROMETRO)){
					relatorioBean.setTipoServico("SUBST");
				}else if(helper.getIdTipoServico().equals(ServicoTipo.TIPO_EFETUAR_REMOCAO_HIDROMETRO)){
					relatorioBean.setTipoServico("REMOC");
				}
				
				if (helper.getIndicadorTorcaProtecaoHidrometro() == 1) {
					relatorioBean.setTrocaProtecao("SIM");
				}else {
					relatorioBean.setTrocaProtecao("NAO");
				}
				
				if (helper.getIndicadorTorcaRegistroHidrometro() == 1) {
					relatorioBean.setTrocaRegistro("SIM");
				}else {
					relatorioBean.setTrocaRegistro("NAO");
				}
				
				relatorioBean.setDataGeracaoOrdemServico(helper.getDataGeracaoOrdemServico());
				relatorioBean.setDataEncerramentoOrdemServico(helper.getDataEncerramentoOrdemServico());
				relatorioBean.setDataFiscalizacao1(helper.getDataFiscalizacao1());
				relatorioBean.setDataFiscalizacao2(helper.getDataFiscalizacao2());
				relatorioBean.setDataFiscalizacao3(helper.getDataFiscalizacao3());
				
				String paga = "";
				if (helper.getCodigoFiscalizaco() == 2 &&
						helper.getDataEncerramentoBoletim() != null &&
						!helper.getDataEncerramentoBoletim().equals("")) {
					paga = "SIM";
				}
				relatorioBean.setPaga(paga);
				
				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioBean);
			}
		
			// Par�metros do relat�rio
			Map parametros = new HashMap();
			
			SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

			parametros.put("imagem", sistemaParametro.getImagemRelatorio());

			// cria uma inst�ncia do dataSource do relat�rio
			RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

			retorno = gerarRelatorio(
					ConstantesRelatorios.RELATORIO_BOLETIM_ORDENS_SERVICO_CONCLUIDAS,
					parametros, ds, tipoFormatoRelatorio);

			// Grava o relat�rio no sistema
			try {
				persistirRelatorioConcluido(retorno,
						Relatorio.RELATORIO_BOLETIM_ORDENS_SERVICO_CONCLUIDAS,
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
		AgendadorTarefas.agendarTarefa("RelatorioBoletimOrdensServicoConcluidas",
				this);
	}
}