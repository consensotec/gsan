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
package gcom.relatorio.atendimentopublico.ordemservico;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.atendimentopublico.OSExecutadasRelatorioHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * [UC1163]  Gerar  Relat�rio de OS executadas por Prestadora de Servi�o
 * 
 * @author Vivianne Sousa
 * @date 18/04/2011
 */

public class RelatorioOSExecutadasPrestadoraServicoSintetico extends TarefaRelatorio {

	private static final long serialVersionUID = -7034984685957706140L;
		
	public RelatorioOSExecutadasPrestadoraServicoSintetico(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_OS_EXECUTADAS_PRESTADORA_SERVICO_SINTETICO);
	}
	
	@Deprecated
	public RelatorioOSExecutadasPrestadoraServicoSintetico() {
		super(null, "");
	}

	
	private Collection<RelatorioOSExecutadasPrestadoraServicoSinteticoBean> inicializarBeanRelatorio(
			Collection colecaoOSExecutadasRelatorioHelper,Date dataEncerramentoInicial,Date dataEncerramentoFinal,
			Integer idGerencia, Integer idUnidade, Integer idLocalidade,
			String[] idsRegiao,String[] idsMicroregiao,String[] idsMunicipio) {
		
		Collection<RelatorioOSExecutadasPrestadoraServicoSinteticoBean> retorno = null;

		if(colecaoOSExecutadasRelatorioHelper != null && !colecaoOSExecutadasRelatorioHelper.isEmpty()){
			retorno = new ArrayList<RelatorioOSExecutadasPrestadoraServicoSinteticoBean>();
			Iterator iterHelper = colecaoOSExecutadasRelatorioHelper.iterator();
			Integer idLocalidadeAnterior = null;
			Integer qtdeOsLocalidade = 0;
			while (iterHelper.hasNext()) {
				
				OSExecutadasRelatorioHelper helper = (OSExecutadasRelatorioHelper) iterHelper.next();
				
				idLocalidade = helper.getIdLocalidade();
				if(!idLocalidade.equals(idLocalidadeAnterior)){
					
					qtdeOsLocalidade = Fachada.getInstancia().recuperaTotalOSExecutadasPorLocalidade(
							dataEncerramentoInicial, dataEncerramentoFinal, idGerencia, 
							idUnidade, idLocalidade, idsRegiao, idsMicroregiao, idsMunicipio);
					
				}
				
				RelatorioOSExecutadasPrestadoraServicoSinteticoBean bean = 
					new RelatorioOSExecutadasPrestadoraServicoSinteticoBean(
							helper.getDescServicosOSExecutadas(), 
							helper.getCodigoServicosOSExecutadas(),
							helper.getQtdeServicosOSExecutadas(),
							helper.getIdLocalidade().toString(), 
							helper.getNomeLocalidade(),
							qtdeOsLocalidade.toString());
				
				retorno.add(bean);
				idLocalidadeAnterior = helper.getIdLocalidade();
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
        
		Integer idGerencia = (Integer)getParametro("idGerencia"); 
		Integer idUnidade = (Integer)getParametro("idUnidade"); 
		Integer idLocalidade = (Integer)getParametro("idLocalidade"); 
		String[] idsRegiao = (String[])getParametro("idsRegiao"); 
		String[] idsMicroregiao = (String[])getParametro("idsMicroregiao"); 
		String[] idsMunicipio = (String[])getParametro("idsMunicipio");  
		Date dataEncerramentoInicial = (Date)getParametro("dataEncerramentoInicial"); 
		Date dataEncerramentoFinal = (Date)getParametro("dataEncerramentoFinal"); 
        		
		String filtroGerencia = (String)getParametro("filtroGerencia");
		String filtroUnidade = (String)getParametro("filtroUnidade");
		String filtroLocalidade = (String)getParametro("filtroLocalidade");
		String filtroRegiao = (String)getParametro("filtroRegiao");
		String filtroMicroregiao = (String)getParametro("filtroMicroregiao");
		String filtroMunicipio = (String)getParametro("filtroMunicipio");
        
		Collection colecaoQtdeImoveisExcluidostarifaSocialHelper = 
			fachada.recuperaTotalServicoOSExecutadasPorLocalidade(
			dataEncerramentoInicial,dataEncerramentoFinal,idGerencia,idUnidade,
			idLocalidade,idsRegiao, idsMicroregiao, idsMunicipio) ;
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		// valor de retorno
		byte[] retorno = null;

		// Par�metros do relat�rio
		Map<String, String> parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		String referencia = Util.formatarData(dataEncerramentoInicial) + " a " + Util.formatarData(dataEncerramentoFinal);
		
		parametros.put("periodoencerramento", referencia);
		parametros.put("gerencia", filtroGerencia);
		parametros.put("unidade", filtroUnidade);
		parametros.put("localidade", filtroLocalidade);
		parametros.put("regiao", filtroRegiao);
		parametros.put("microregiao", filtroMicroregiao);
		parametros.put("municipio", filtroMunicipio);
		parametros.put("prestadoraServico", "PONTO FORTE");
		parametros.put("dataEmissao", Util.formatarData(new Date()));
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("imagemConta", sistemaParametro.getImagemConta());
//		parametros.put("nomeEmpresa",sistemaParametro.getNomeAbreviadoEmpresa());
//		parametros.put("cnpjEmpresa",sistemaParametro.getCnpjEmpresa().toString());

		Collection dadosRelatorio = colecaoQtdeImoveisExcluidostarifaSocialHelper;

		Collection<RelatorioOSExecutadasPrestadoraServicoSinteticoBean> colecaoBean = this
				.inicializarBeanRelatorio(dadosRelatorio,dataEncerramentoInicial,dataEncerramentoFinal,
						idGerencia,idUnidade,idLocalidade,idsRegiao, idsMicroregiao, idsMunicipio);

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// N�o existem dados para a exibi��o do relat�rio.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		RelatorioDataSource ds = new RelatorioDataSource((java.util.List) colecaoBean);

		retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_OS_EXECUTADAS_PRESTADORA_SERVICO_SINTETICO, parametros, ds,tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.GERAR_RELATORIO_OS_EXECUTADAS_PRESTADORA_SERVICO_SINTETICO,idFuncionalidadeIniciada);
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

		return 0;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioOSExecutadasPrestadoraServicoSintetico", this);
	}
	
}
