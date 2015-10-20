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
package gcom.relatorio.micromedicao;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gest�o Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author S�vio Luiz
 * @version 1.0
 */

public class RelatorioRegistrarLeiturasAnormalidades extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioRegistrarLeiturasAnormalidades(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_REGISTRAR_LEITURAS_ANORMALIDADES);
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param localidades
	 *            Description of the Parameter
	 * @param localidadeParametros
	 *            Description of the Parameter
	 * @return Descri��o do retorno
	 * @exception RelatorioVazioException
	 *                Descri��o da exce��o
	 */

	public Object executar() throws TarefaException {

		// public byte[] gerarRelatorioRegistrarLeiturasAnormalidades(
		// Collection<MedicaoHistorico> colecaoMedicaoHistoricoRelatorio,
		// String idFaturamento, String anoMesReferencia)
		// throws RelatorioVazioException {

		Collection colecaoMedicaoHistoricoRelatorio = (Collection) getParametro("colecaoMedicaoHistoricoRelatorio");
		Integer idFaturamento = (Integer) getParametro("idFaturamento");
		String anoMesReferencia = (String) getParametro("anoMesLeitura");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		String nomeArquivo = (String) getParametro("nomeArquivo");

		Fachada fachada = Fachada.getInstancia();
		
		// valor de retorno
		byte[] retorno = null;

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		RelatorioRegistrarLeiturasAnormalidadesBean relatorioBean = null;

		// se a cole��o de par�metros da analise n�o for vazia
		if (colecaoMedicaoHistoricoRelatorio != null
				&& !colecaoMedicaoHistoricoRelatorio.isEmpty()) {
			// coloca a cole��o de par�metros da analise no iterator
			Iterator medicaoHistoricoRelatorioIterator = colecaoMedicaoHistoricoRelatorio
					.iterator();

			// la�o para criar a cole��o de par�metros da analise
			while (medicaoHistoricoRelatorioIterator.hasNext()) {

				MedicaoHistorico medicaoHistrico = (MedicaoHistorico) medicaoHistoricoRelatorioIterator
						.next();

				String matriculaFuncionario = ""
						+ medicaoHistrico.getFuncionario().getId();
				String matriculaImovel = ""
						+ medicaoHistrico.getImovel().getId();
				String inscricaoImovel = ""
						+ medicaoHistrico.getImovel().getInscricaoFormatada();
				String tipoMedicao = ""
						+ medicaoHistrico.getMedicaoTipo().getId();
				String dataLeitura = medicaoHistrico
						.getDataLeituraParaRegistrar();
				String dataLeituraFormatada = dataLeitura.substring(0, 2) + "/"
						+ dataLeitura.substring(2, 4) + "/"
						+ dataLeitura.substring(4, 8);
				String codigoAnormalidade = "";
				if (medicaoHistrico.getLeituraAnormalidadeInformada() != null
						&& !medicaoHistrico.getLeituraAnormalidadeInformada()
								.equals("")) {
					codigoAnormalidade = ""
							+ medicaoHistrico.getLeituraAnormalidadeInformada()
									.getId();
				}else{
					codigoAnormalidade = "0";
				}
				// indicador confirma��o leitura
				String indicadorConfirmacaoLeitura = medicaoHistrico
						.getIndicadorConfirmacaoLeitura();
				String errosTxt = medicaoHistrico.getGerarRelatorio();
				String leituraHidrometro = ""
						+ medicaoHistrico.getLeituraAtualInformada();

				relatorioBean = new RelatorioRegistrarLeiturasAnormalidadesBean(
						matriculaFuncionario, matriculaImovel, inscricaoImovel,
						tipoMedicao, dataLeituraFormatada, codigoAnormalidade,
						indicadorConfirmacaoLeitura, errosTxt,
						leituraHidrometro);

				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioBean);
			}
		}

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		// adiciona os par�metros do relat�rio
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("idFaturamentoGrupo", ""+idFaturamento);

		String formatarAnoMesReferencia = anoMesReferencia.substring(4, 6)
				+ "/" + anoMesReferencia.substring(0, 4);

		parametros.put("mesAnoReferencia", formatarAnoMesReferencia);
		
		parametros.put("nomeArquivo", nomeArquivo);

		//cria uma inst�ncia do dataSource do relat�rio
		if ( relatorioBeans.size() > 0) {
			RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

			retorno = gerarRelatorio(
					ConstantesRelatorios.RELATORIO_REGISTRAR_LEITURAS_ANORMALIDADES,
					parametros, ds, tipoFormatoRelatorio);
		} else {

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
	}

}