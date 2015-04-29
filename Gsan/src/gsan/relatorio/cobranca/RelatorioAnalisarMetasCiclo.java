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
 * Genival Soares Barbosa Filho
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
package gsan.relatorio.cobranca;

import gsan.batch.Relatorio;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cobranca.CicloMeta;
import gsan.cobranca.CobrancaAcao;
import gsan.cobranca.FiltroCicloMeta;
import gsan.cobranca.FiltroCobrancaAcao;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.RelatorioDataSource;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaException;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.ControladorException;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * classe respons�vel por criar o Relat�rio de D�bitos
 * 
 * @author Genival Barbosa
 * @date 21/07/2009
 * 
 */
public class RelatorioAnalisarMetasCiclo extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioAnalisarMetasCiclo(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_ANALISAR_METAS_CICLO);
	}

	@Deprecated
	public RelatorioAnalisarMetasCiclo() {
		super(null, "");
	}
	
	@Override
	public Object executar() throws TarefaException {
		
		Fachada fachada = Fachada.getInstancia();
		
		Object obj = null;
		Object[] dados = null;
		
		String idCicloMeta = (String) getParametro("idCicloMeta");
		String idAcaoCobranca = (String) getParametro("idAcaoCobranca");
		String anoMesCobranca = (String) getParametro("anoMesCobranca");
		
		FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
		filtroCobrancaAcao.adicionarParametro(new ParametroSimples(FiltroCobrancaAcao.ID, 
				idAcaoCobranca));

		Collection colecaoCobrancaAcao = fachada.pesquisar(
				filtroCobrancaAcao, CobrancaAcao.class.getName());
		
		CobrancaAcao cobrancaAcao = (CobrancaAcao) Util.retonarObjetoDeColecao(colecaoCobrancaAcao);
		String acaoCobranca = cobrancaAcao.getDescricaoCobrancaAcao();
		
	
		
		FiltroCicloMeta filtroCiclo = new FiltroCicloMeta();
		if ((idCicloMeta == null || idCicloMeta.equals("")) && 
				anoMesCobranca != null || anoMesCobranca.equals("")){
			filtroCiclo.adicionarParametro(new ParametroSimples(FiltroCicloMeta.COBRANCA_ACAO_ID, 
					idAcaoCobranca));
			filtroCiclo.adicionarParametro(new ParametroSimples(FiltroCicloMeta.ANO_MES_REFERENCIA, 
					Util.formatarMesAnoComBarraParaAnoMes(anoMesCobranca)));
			filtroCiclo.adicionarCaminhoParaCarregamentoEntidade(FiltroCicloMeta.COBRANCA_ACAO);

		}
		
		Collection colecaoCicloMetas = fachada.pesquisar(filtroCiclo, CicloMeta.class.getName());
		CicloMeta cicloMeta = new CicloMeta();
		
		
		cicloMeta = (CicloMeta) Util.retonarObjetoDeColecao(colecaoCicloMetas);
		//colocar auqi a parte referente ao ciclometa de exibirAnalisarMetasCicloAction
		byte[] retorno = null;
		if(cicloMeta != null) {
			
		
			List colecaoCicloMetaGrupo = fachada
					.consultarColecaoCicloMetaGrupoRelatorio(cicloMeta);
	
			
			Collection<RelatorioAnalisarMetasCicloBean> colecaoBean = new ArrayList<RelatorioAnalisarMetasCicloBean>();
			
	//		 pra cada objeto obter a categoria
			for (int i = 0; i < colecaoCicloMetaGrupo.size(); i++) {
				obj = colecaoCicloMetaGrupo.get(i);
	
				if (obj instanceof Object[]) {
					dados = (Object[]) obj;
					
					String idGerencia = dados[0].toString();
					String nomeGerencia = dados[1].toString();
					String idUnidade = dados[2].toString();
					String nomeUnidade = dados[3].toString();				
					String idLocalidade = dados[4].toString();
					String nomeLocalidade = dados[5].toString();
					String metaOriginal = dados[6].toString();
					String metaAtual = dados[7].toString();
					String qtdImoveisSituacao = "0";
					if(dados[8] != null){
						qtdImoveisSituacao = dados[8].toString();
					}
					String qtdTotalRealizada = "0";
					if(dados[9] != null){
						qtdTotalRealizada = dados[9].toString();
					}
					String valorTotalRealizada = "0";
					if(dados[10] != null){
						valorTotalRealizada = dados[10].toString();
					}
					String qtdTotalRestante = "0";
					if(dados[11] != null) {
						qtdTotalRestante = dados[11].toString();
					}				
					String valorTotalRestante = "0";
					if(dados[12] != null){
						valorTotalRestante = dados[12].toString();
					}
					
					RelatorioAnalisarMetasCicloBean relatorioAnalisarMetasCicloBean = 
						new RelatorioAnalisarMetasCicloBean(
							nomeGerencia, 
							idGerencia, 
							nomeUnidade, 
							idUnidade, 
							nomeLocalidade,
							idLocalidade , 
							metaOriginal,
							metaAtual, 
							qtdImoveisSituacao, 
							qtdTotalRealizada,
							valorTotalRealizada, 
							qtdTotalRestante, 
							valorTotalRestante,
							acaoCobranca, 
							anoMesCobranca);
	
					colecaoBean.add(relatorioAnalisarMetasCicloBean);
				}
				
			}
			
			int tipoFormatoRelatorio = (Integer) getParametro("tipoRelatorio");
			
			//Par�metros do relat�rio
			Map parametros = new HashMap();
			
			
			//Recupera o AnoMesFaturamento de Sistema Parametro
			SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
			
			parametros.put("imagem", sistemaParametro.getImagemRelatorio());
	
			RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);
	
			// exporta o relat�rio em pdf e retorna o array de bytes
			retorno = gerarRelatorio(
					ConstantesRelatorios.RELATORIO_ANALISAR_METAS_CICLO,
					parametros, ds, tipoFormatoRelatorio);
		}else {
			throw new ActionServletException(
            "atencao.pesquisa.nenhumresultado");
		}
		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_ANALISAR_METAS_CICLO, 0);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		}
		// ------------------------------------
		// retorna o relat�rio gerado
		
		return retorno;

	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void agendarTarefaBatch() {
		// TODO Auto-generated method stub

	}
}