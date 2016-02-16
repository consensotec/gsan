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
package gcom.relatorio.cadastro;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * [UC1164] Gerar Resumo dos Im�veis Exclu�dos da Tarifa Social
 * 
 * @author Vivianne Sousa
 * @date 07/04/2011
 */

public class RelatorioResumoQtdeImoveisExcluidosTarifaSocial extends TarefaRelatorio {

	private static final long serialVersionUID = -7034984685957706140L;
		
	public RelatorioResumoQtdeImoveisExcluidosTarifaSocial(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_RESUMO_QTDE_IMOVEIS_EXCLUIDOS_TARIFA_SOCIAL);
	}
	
	@Deprecated
	public RelatorioResumoQtdeImoveisExcluidosTarifaSocial() {
		super(null, "");
	}

	
	private Collection<RelatorioResumoQtdeImoveisExcluidosTarifaSocialBean> inicializarBeanRelatorio(
			Collection colecaoQtdeImoveisExcluidostarifaSocialHelper) {
		
		Collection<RelatorioResumoQtdeImoveisExcluidosTarifaSocialBean> retorno = null;

		if(colecaoQtdeImoveisExcluidostarifaSocialHelper != null && !colecaoQtdeImoveisExcluidostarifaSocialHelper.isEmpty()){
			retorno = new ArrayList<RelatorioResumoQtdeImoveisExcluidosTarifaSocialBean>();
			Iterator iterHelper = colecaoQtdeImoveisExcluidostarifaSocialHelper.iterator();
			String descricaoLocalidadeAnterior = "";
			while (iterHelper.hasNext()) {
				RelatorioResumoQtdeImoveisExcluidosTarifaSocialHelper helper = 
					(RelatorioResumoQtdeImoveisExcluidosTarifaSocialHelper) iterHelper.next();
				
				String descricaoLocalidade = helper.getDescricaoLocalidade();
				
				if(descricaoLocalidade.equals(descricaoLocalidadeAnterior)){
					descricaoLocalidade = "";
				}
				
				RelatorioResumoQtdeImoveisExcluidosTarifaSocialBean bean = 
					new RelatorioResumoQtdeImoveisExcluidosTarifaSocialBean(
							helper.getDescricaoGerencia(),
							helper.getIdGerencia().toString(),
							descricaoLocalidade,
							helper.getIdLocalidade().toString(),
							helper.getDescricaoUnidadeNegocio(),
							helper.getIdUnidadeNegocio().toString(),
							helper.getMotivoExclusao().toString(),
							helper.getQtdeCartas(),
							helper.getQtdeExcluidos(),
							helper.getDescricaoMotivoExclusao());
				
				retorno.add(bean);
				
				descricaoLocalidadeAnterior = helper.getDescricaoLocalidade();
				
			}
		}
		return retorno;
	}

	/**
	 * M�todo que executa a tarefa
	 * 
	 * @return Object
	 * 
	 */
	public Object executar() throws TarefaException {
		
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		
		Fachada fachada = Fachada.getInstancia();
		
		Integer idGerenciaRegional = (Integer)getParametro("idGerenciaRegional");
        Integer idUnidadeNegocio = (Integer)getParametro("idUnidadeNegocio");
        Integer idLocalidade = (Integer)getParametro("idLocalidade");
        Integer anoMesPesquisaInicial = (Integer)getParametro("anoMesPesquisaInicial");
        Integer anoMesPesquisaFinal = (Integer)getParametro("anoMesPesquisaFinal");
        String motivoExclusao = (String)getParametro("motivoExclusao");
        Integer codigoTipoCarta = 1;
        
        
        String referencia = Util.formatarAnoMesParaMesAno(anoMesPesquisaInicial) + " a " + Util.formatarAnoMesParaMesAno(anoMesPesquisaFinal);
        
        String tipoResumo = "";
        if(motivoExclusao.equals("1")){
        	tipoResumo = "N�o Comparecimento para Atualizar Cadastro";
        	codigoTipoCarta = 1;
        }else if(motivoExclusao.equals("2")){
        	tipoResumo = "Contas Vencidas";
        	codigoTipoCarta = 2;
        }
//        else if(motivoExclusao.equals("3")){
//        	tipoResumo = "M�dia de Consumo Superior a 10m ";
//        }
        
		Collection colecaoQtdeImoveisExcluidostarifaSocialHelper = fachada.pesquisarQtdeImoveisExcluidostarifaSocial(
				codigoTipoCarta, idGerenciaRegional, idUnidadeNegocio, idLocalidade, 
				anoMesPesquisaInicial,  anoMesPesquisaFinal);
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		// valor de retorno
		byte[] retorno = null;

		// Par�metros do relat�rio
		Map<String, String> parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("imagemConta", sistemaParametro.getImagemConta());
//		parametros.put("nomeEmpresa",sistemaParametro.getNomeAbreviadoEmpresa());
//		parametros.put("cnpjEmpresa",sistemaParametro.getCnpjEmpresa().toString());

		parametros.put("referencia",referencia);
		parametros.put("tipoResumo",tipoResumo);
		parametros.put("codigoTipoCarta",codigoTipoCarta.toString());
		Collection dadosRelatorio = colecaoQtdeImoveisExcluidostarifaSocialHelper;

		Collection<RelatorioResumoQtdeImoveisExcluidosTarifaSocialBean> colecaoBean = this
				.inicializarBeanRelatorio(dadosRelatorio);

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// N�o existem dados para a exibi��o do relat�rio.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		RelatorioDataSource ds = new RelatorioDataSource((java.util.List) colecaoBean);

		retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_RESUMO_QTDE_IMOVEIS_EXCLUIDOS_TARIFA_SOCIAL, parametros, ds,tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.GERAR_RELATORIO_RESUMO_QTDE_IMOVEIS_EXCLUIDOS_TARIFA_SOCIAL,idFuncionalidadeIniciada);
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
//		Integer qtdeContas = (Integer) getParametro("qtdeContas");
//		
//		return qtdeContas;
		return 0;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioResumoQtdeImoveisExcluidosTarifaSocial", this);
	}
	
}
