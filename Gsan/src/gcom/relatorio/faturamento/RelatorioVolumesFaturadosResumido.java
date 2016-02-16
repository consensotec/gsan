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
package gcom.relatorio.faturamento;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.VolumesFaturadosRelatorioHelper;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe respons�vel por criar o relat�rio de volumes faturados
 * 
 * @author Rafael Corr�a
 * @created 12/09/2007
 */
public class RelatorioVolumesFaturadosResumido extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioVolumesFaturadosResumido(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_VOLUMES_FATURADOS_RESUMIDO);
	}

	@Deprecated
	public RelatorioVolumesFaturadosResumido() {
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

		Integer idLocalidade = (Integer) getParametro("idLocalidade");
		Integer anoMes = (Integer) getParametro("anoMes");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioVolumesFaturadosBean relatorioBean = null;

		Integer anoMes1 = Util.somaMesAnoMesReferencia(anoMes, 1);
		Integer anoMes2 = Util.somaMesAnoMesReferencia(anoMes, 2);
		Integer anoMes3 = Util.somaMesAnoMesReferencia(anoMes, 3);
		Integer anoMes4 = Util.somaMesAnoMesReferencia(anoMes, 4);
		Integer anoMes5 = Util.somaMesAnoMesReferencia(anoMes, 5);
		Integer anoMes6 = Util.somaMesAnoMesReferencia(anoMes, 6);

		Collection colecaoVolumesFaturadosRelatorioHelper = fachada
				.pesquisarDadosRelatorioVolumesFaturadosResumido(idLocalidade, anoMes,
						anoMes1, anoMes2, anoMes3, anoMes4, anoMes5, anoMes6);

		// se a cole��o de par�metros da analise n�o for vazia
		if (colecaoVolumesFaturadosRelatorioHelper != null
				&& !colecaoVolumesFaturadosRelatorioHelper.isEmpty()) {

			// coloca a cole��o de par�metros da analise no iterator
			Iterator colecaoVolumesFaturadosRelatorioHelperIterator = colecaoVolumesFaturadosRelatorioHelper
					.iterator();

			// Cria as vari�veis de totaliza��o
			Integer mediaTotalSetorComercial = new Integer("0");
			Integer mesAno1TotalSetorComercial = new Integer("0");
			Integer mesAno2TotalSetorComercial = new Integer("0");
			Integer mesAno3TotalSetorComercial = new Integer("0");
			Integer mesAno4TotalSetorComercial = new Integer("0");
			Integer mesAno5TotalSetorComercial = new Integer("0");
			Integer mesAno6TotalSetorComercial = new Integer("0");
			Integer mediaTotalLocalidade = new Integer("0");
			Integer mesAno1TotalLocalidade = new Integer("0");
			Integer mesAno2TotalLocalidade = new Integer("0");
			Integer mesAno3TotalLocalidade = new Integer("0");
			Integer mesAno4TotalLocalidade = new Integer("0");
			Integer mesAno5TotalLocalidade = new Integer("0");
			Integer mesAno6TotalLocalidade = new Integer("0");
			
			Integer idSetorComercialAnterior = null;
			Integer idLocalidadeAnterior = null;
			boolean zerarSetorComercial = false;
			boolean zerarLocalidade = false;

			String variacao1TotalSetorComercial = "";
			String variacao1TotalLocalidade = "";
			String variacao2TotalSetorComercial = "";
			String variacao2TotalLocalidade = "";
			String variacao3TotalSetorComercial = "";
			String variacao3TotalLocalidade = "";
			String variacao4TotalSetorComercial = "";
			String variacao4TotalLocalidade = "";
			String variacao5TotalSetorComercial = "";
			String variacao5TotalLocalidade = "";
			String variacao6TotalSetorComercial = "";
			String variacao6TotalLocalidade = "";
			
			// Cria as vari�veis para verificar se existe algum dado do m�s para cada totalizador
			boolean peloMenosUmDadoMesAno1SetorComercial = false;
			boolean peloMenosUmDadoMesAno2SetorComercial = false;
			boolean peloMenosUmDadoMesAno3SetorComercial = false;
			boolean peloMenosUmDadoMesAno4SetorComercial = false;
			boolean peloMenosUmDadoMesAno5SetorComercial = false;
			boolean peloMenosUmDadoMesAno6SetorComercial = false;
			
			boolean peloMenosUmDadoMesAno1Localidade = false;
			boolean peloMenosUmDadoMesAno2Localidade = false;
			boolean peloMenosUmDadoMesAno3Localidade = false;
			boolean peloMenosUmDadoMesAno4Localidade = false;
			boolean peloMenosUmDadoMesAno5Localidade = false;
			boolean peloMenosUmDadoMesAno6Localidade = false;

			// la�o para criar a cole��o de par�metros da analise
			while (colecaoVolumesFaturadosRelatorioHelperIterator.hasNext()) {

				VolumesFaturadosRelatorioHelper volumesFaturadosRelatorioHelper = (VolumesFaturadosRelatorioHelper) colecaoVolumesFaturadosRelatorioHelperIterator
						.next();

				// Seta os valores das vari�veis de controle de totaliza��o para
				// verificar quando deve ser zerado os totalizadores
				if (idSetorComercialAnterior == null) {
					idSetorComercialAnterior = volumesFaturadosRelatorioHelper
							.getIdSetorComercial();
				}

				if (idLocalidadeAnterior == null) {
					idLocalidadeAnterior = volumesFaturadosRelatorioHelper
							.getIdLocalidade();
				}

				// Faz as valida��es dos campos necess�riose e formata a String
				// para a forma como ir� aparecer no relat�rio

				// Localidade
				String localidade = "";

				if (volumesFaturadosRelatorioHelper.getIdLocalidade() != null) {
					localidade = volumesFaturadosRelatorioHelper
							.getIdLocalidade()
							+ " - "
							+ volumesFaturadosRelatorioHelper
									.getNomeLocalidade();

					// Caso tenha mudado a localidade do im�vel seta a vari�vel
					// para true, para posteriormente zerar todas as vari�veis
					// de totaliza��o da localidade
					if (!idLocalidadeAnterior
							.equals(volumesFaturadosRelatorioHelper
									.getIdLocalidade())) {
						zerarLocalidade = true;
					}
				}

				// Setor Comercial
				String idSetorComercial = "";
				String setorComercial = "";

				if (volumesFaturadosRelatorioHelper.getIdSetorComercial() != null) {
					idSetorComercial = volumesFaturadosRelatorioHelper
							.getIdSetorComercial().toString();
					setorComercial = volumesFaturadosRelatorioHelper
							.getCodigoSetorComercial()
							+ " - "
							+ volumesFaturadosRelatorioHelper
									.getNomeSetorComercial();

					// Caso tenha mudado o setor comercial do im�vel seta a
					// vari�vel para true, para posteriormente zerar todas as
					// vari�veis de totaliza��o do setor comercial
					if (!idSetorComercialAnterior
							.equals(volumesFaturadosRelatorioHelper
									.getIdSetorComercial())) {
						zerarSetorComercial = true;
					}
				}

				// Quadra
				String numeroQuadra = "";

				if (volumesFaturadosRelatorioHelper.getNumeroQuadra() != null) {
					numeroQuadra = volumesFaturadosRelatorioHelper
							.getNumeroQuadra().toString();
				}
				
				// Zera os totalizadores do setor comercial
				if (zerarSetorComercial) {
					mediaTotalSetorComercial = new Integer("0");
					mesAno1TotalSetorComercial = new Integer("0");
					mesAno2TotalSetorComercial = new Integer("0");
					mesAno3TotalSetorComercial = new Integer("0");
					mesAno4TotalSetorComercial = new Integer("0");
					mesAno5TotalSetorComercial = new Integer("0");
					mesAno6TotalSetorComercial = new Integer("0");
					
					variacao1TotalSetorComercial = "";
					variacao2TotalSetorComercial = "";
					variacao3TotalSetorComercial = "";
					variacao4TotalSetorComercial = "";
					variacao5TotalSetorComercial = "";
					variacao6TotalSetorComercial = "";

					zerarSetorComercial = false;
					
					peloMenosUmDadoMesAno1SetorComercial = false;
					peloMenosUmDadoMesAno2SetorComercial = false;
					peloMenosUmDadoMesAno3SetorComercial = false;
					peloMenosUmDadoMesAno4SetorComercial = false;
					peloMenosUmDadoMesAno5SetorComercial = false;
					peloMenosUmDadoMesAno6SetorComercial = false;
					
					idSetorComercialAnterior = volumesFaturadosRelatorioHelper
							.getIdSetorComercial();
				}

				// Zera os totalizadores da localidade
				if (zerarLocalidade) {
					mediaTotalLocalidade = new Integer("0");
					mesAno1TotalLocalidade = new Integer("0");
					mesAno2TotalLocalidade = new Integer("0");
					mesAno3TotalLocalidade = new Integer("0");
					mesAno4TotalLocalidade = new Integer("0");
					mesAno5TotalLocalidade = new Integer("0");
					mesAno6TotalLocalidade = new Integer("0");

					variacao1TotalLocalidade = "";
					variacao2TotalLocalidade = "";
					variacao3TotalLocalidade = "";
					variacao4TotalLocalidade = "";
					variacao5TotalLocalidade = "";
					variacao6TotalLocalidade = "";

					zerarLocalidade = false;
					
					peloMenosUmDadoMesAno1Localidade = false;
					peloMenosUmDadoMesAno2Localidade = false;
					peloMenosUmDadoMesAno3Localidade = false;
					peloMenosUmDadoMesAno4Localidade = false;
					peloMenosUmDadoMesAno5Localidade = false;
					peloMenosUmDadoMesAno6Localidade = false;
					
					idLocalidadeAnterior = volumesFaturadosRelatorioHelper
							.getIdLocalidade();
				}

				// M�dia de Consumo
				String media = "";
				Integer mediaConsumo = null;

				if (volumesFaturadosRelatorioHelper.getMediaConsumo() != null) {
					media = volumesFaturadosRelatorioHelper.getMediaConsumo()
							.toString();
					mediaConsumo = volumesFaturadosRelatorioHelper
							.getMediaConsumo();
					mediaTotalSetorComercial = mediaTotalSetorComercial
							+ mediaConsumo;
					mediaTotalLocalidade = mediaTotalLocalidade + mediaConsumo;
				}

				// Consumo e Varia��o M�s/Ano 1
				String consumoMesAno1 = "";
				String variacao1 = "";

				if (volumesFaturadosRelatorioHelper.getConsumoMesAno1() != null) {
					consumoMesAno1 = volumesFaturadosRelatorioHelper
							.getConsumoMesAno1().toString();
					
					peloMenosUmDadoMesAno1SetorComercial = true;
					peloMenosUmDadoMesAno1Localidade = true;

					// Soma os valores aos totalizadores de cada grupo
					mesAno1TotalSetorComercial = mesAno1TotalSetorComercial
							+ volumesFaturadosRelatorioHelper
									.getConsumoMesAno1();
					mesAno1TotalLocalidade = mesAno1TotalLocalidade
							+ volumesFaturadosRelatorioHelper
									.getConsumoMesAno1();

					// Calcula a varia��o
					if (mediaConsumo != null
							&& !mediaConsumo.equals(new Integer("0"))) {
						variacao1 = Util.formatarMoedaReal(new BigDecimal(
								volumesFaturadosRelatorioHelper
										.getConsumoMesAno1()).divide(
								new BigDecimal(mediaConsumo), 4,
								BigDecimal.ROUND_HALF_UP).subtract(
								new BigDecimal("1.00")).multiply(
								new BigDecimal("100.00")));
					}

				}

				// Calcula a varia��o dos totalizadores de cada grupo
				if (mesAno1TotalSetorComercial != null) {

					if (mediaTotalSetorComercial != null
							&& !mediaTotalSetorComercial
									.equals(new Integer("0"))) {
						variacao1TotalSetorComercial = Util
								.formatarMoedaReal(new BigDecimal(
										mesAno1TotalSetorComercial)
										.divide(
												new BigDecimal(
														mediaTotalSetorComercial),
												4, BigDecimal.ROUND_HALF_UP)
										.subtract(new BigDecimal("1.00"))
										.multiply(new BigDecimal("100.00")));
					}

				}

				if (mesAno1TotalLocalidade != null) {

					if (mediaTotalLocalidade != null
							&& !mediaTotalLocalidade.equals(new Integer("0"))) {
						variacao1TotalLocalidade = Util
								.formatarMoedaReal(new BigDecimal(
										mesAno1TotalLocalidade).divide(
										new BigDecimal(mediaTotalLocalidade),
										4, BigDecimal.ROUND_HALF_UP).subtract(
										new BigDecimal("1.00")).multiply(
										new BigDecimal("100.00")));
					}

				}

				// Consumo e Varia��o M�s/Ano 2
				String consumoMesAno2 = "";
				String variacao2 = "";

				if (volumesFaturadosRelatorioHelper.getConsumoMesAno2() != null) {
					consumoMesAno2 = volumesFaturadosRelatorioHelper
							.getConsumoMesAno2().toString();
					
					peloMenosUmDadoMesAno2SetorComercial = true;
					peloMenosUmDadoMesAno2Localidade = true;

					// Soma os valores aos totalizadores de cada grupo
					mesAno2TotalSetorComercial = mesAno2TotalSetorComercial
							+ volumesFaturadosRelatorioHelper
									.getConsumoMesAno2();
					mesAno2TotalLocalidade = mesAno2TotalLocalidade
							+ volumesFaturadosRelatorioHelper
									.getConsumoMesAno2();

					// Calcula a varia��o
					if (mediaConsumo != null
							&& !mediaConsumo.equals(new Integer("0"))) {
						variacao2 = Util.formatarMoedaReal(new BigDecimal(
								volumesFaturadosRelatorioHelper
										.getConsumoMesAno2()).divide(
								new BigDecimal(mediaConsumo), 4,
								BigDecimal.ROUND_HALF_UP).subtract(
								new BigDecimal("1.00")).multiply(
								new BigDecimal("100.00")));
					}

				}

				// Calcula a varia��o dos totalizadores de cada grupo
				if (mesAno2TotalSetorComercial != null) {

					if (mediaTotalSetorComercial != null
							&& !mediaTotalSetorComercial
									.equals(new Integer("0"))) {
						variacao2TotalSetorComercial = Util
								.formatarMoedaReal(new BigDecimal(
										mesAno2TotalSetorComercial)
										.divide(
												new BigDecimal(
														mediaTotalSetorComercial),
												4, BigDecimal.ROUND_HALF_UP)
										.subtract(new BigDecimal("1.00"))
										.multiply(new BigDecimal("100.00")));
					}

				}

				if (mesAno2TotalLocalidade != null) {

					if (mediaTotalLocalidade != null
							&& !mediaTotalLocalidade.equals(new Integer("0"))) {
						variacao2TotalLocalidade = Util
								.formatarMoedaReal(new BigDecimal(
										mesAno2TotalLocalidade).divide(
										new BigDecimal(mediaTotalLocalidade),
										4, BigDecimal.ROUND_HALF_UP).subtract(
										new BigDecimal("1.00")).multiply(
										new BigDecimal("100.00")));
					}

				}

				// Consumo e Varia��o M�s/Ano 3
				String consumoMesAno3 = "";
				String variacao3 = "";

				if (volumesFaturadosRelatorioHelper.getConsumoMesAno3() != null) {
					consumoMesAno3 = volumesFaturadosRelatorioHelper
							.getConsumoMesAno3().toString();
					
					peloMenosUmDadoMesAno3SetorComercial = true;
					peloMenosUmDadoMesAno3Localidade = true;

					// Soma os valores aos totalizadores de cada grupo
					mesAno3TotalSetorComercial = mesAno3TotalSetorComercial
							+ volumesFaturadosRelatorioHelper
									.getConsumoMesAno3();
					mesAno3TotalLocalidade = mesAno3TotalLocalidade
							+ volumesFaturadosRelatorioHelper
									.getConsumoMesAno3();

					// Calcula a varia��o
					if (mediaConsumo != null
							&& !mediaConsumo.equals(new Integer("0"))) {
						variacao3 = Util.formatarMoedaReal(new BigDecimal(
								volumesFaturadosRelatorioHelper
										.getConsumoMesAno3()).divide(
								new BigDecimal(mediaConsumo), 4,
								BigDecimal.ROUND_HALF_UP).subtract(
								new BigDecimal("1.00")).multiply(
								new BigDecimal("100.00")));
					}

				}

				// Calcula a varia��o dos totalizadores de cada grupo
				if (mesAno3TotalSetorComercial != null) {

					if (mediaTotalSetorComercial != null
							&& !mediaTotalSetorComercial
									.equals(new Integer("0"))) {
						variacao3TotalSetorComercial = Util
								.formatarMoedaReal(new BigDecimal(
										mesAno3TotalSetorComercial)
										.divide(
												new BigDecimal(
														mediaTotalSetorComercial),
												4, BigDecimal.ROUND_HALF_UP)
										.subtract(new BigDecimal("1.00"))
										.multiply(new BigDecimal("100.00")));
					}

				}

				if (mesAno3TotalLocalidade != null) {

					if (mediaTotalLocalidade != null
							&& !mediaTotalLocalidade.equals(new Integer("0"))) {
						variacao3TotalLocalidade = Util
								.formatarMoedaReal(new BigDecimal(
										mesAno3TotalLocalidade).divide(
										new BigDecimal(mediaTotalLocalidade),
										4, BigDecimal.ROUND_HALF_UP).subtract(
										new BigDecimal("1.00")).multiply(
										new BigDecimal("100.00")));
					}

				}

				// Consumo e Varia��o M�s/Ano 4
				String consumoMesAno4 = "";
				String variacao4 = "";

				if (volumesFaturadosRelatorioHelper.getConsumoMesAno4() != null) {
					consumoMesAno4 = volumesFaturadosRelatorioHelper
							.getConsumoMesAno4().toString();
					
					peloMenosUmDadoMesAno4SetorComercial = true;
					peloMenosUmDadoMesAno4Localidade = true;

					// Soma os valores aos totalizadores de cada grupo
					mesAno4TotalSetorComercial = mesAno4TotalSetorComercial
							+ volumesFaturadosRelatorioHelper
									.getConsumoMesAno4();
					mesAno4TotalLocalidade = mesAno4TotalLocalidade
							+ volumesFaturadosRelatorioHelper
									.getConsumoMesAno4();

					// Calcula a varia��o
					if (mediaConsumo != null
							&& !mediaConsumo.equals(new Integer("0"))) {
						variacao4 = Util.formatarMoedaReal(new BigDecimal(
								volumesFaturadosRelatorioHelper
										.getConsumoMesAno4()).divide(
								new BigDecimal(mediaConsumo), 4,
								BigDecimal.ROUND_HALF_UP).subtract(
								new BigDecimal("1.00")).multiply(
								new BigDecimal("100.00")));
					}

				}

				// Calcula a varia��o dos totalizadores de cada grupo
				if (mesAno4TotalSetorComercial != null) {

					if (mediaTotalSetorComercial != null
							&& !mediaTotalSetorComercial
									.equals(new Integer("0"))) {
						variacao4TotalSetorComercial = Util
								.formatarMoedaReal(new BigDecimal(
										mesAno4TotalSetorComercial)
										.divide(
												new BigDecimal(
														mediaTotalSetorComercial),
												4, BigDecimal.ROUND_HALF_UP)
										.subtract(new BigDecimal("1.00"))
										.multiply(new BigDecimal("100.00")));
					}

				}

				if (mesAno4TotalLocalidade != null) {

					if (mediaTotalLocalidade != null
							&& !mediaTotalLocalidade.equals(new Integer("0"))) {
						variacao4TotalLocalidade = Util
								.formatarMoedaReal(new BigDecimal(
										mesAno4TotalLocalidade).divide(
										new BigDecimal(mediaTotalLocalidade),
										4, BigDecimal.ROUND_HALF_UP).subtract(
										new BigDecimal("1.00")).multiply(
										new BigDecimal("100.00")));
					}

				}

				// Consumo e Varia��o M�s/Ano 5
				String consumoMesAno5 = "";
				String variacao5 = "";

				if (volumesFaturadosRelatorioHelper.getConsumoMesAno5() != null) {
					consumoMesAno5 = volumesFaturadosRelatorioHelper
							.getConsumoMesAno5().toString();
					
					peloMenosUmDadoMesAno5SetorComercial = true;
					peloMenosUmDadoMesAno5Localidade = true;

					// Soma os valores aos totalizadores de cada grupo
					mesAno5TotalSetorComercial = mesAno5TotalSetorComercial
							+ volumesFaturadosRelatorioHelper
									.getConsumoMesAno5();
					mesAno5TotalLocalidade = mesAno5TotalLocalidade
							+ volumesFaturadosRelatorioHelper
									.getConsumoMesAno5();

					// Calcula a varia��o
					if (mediaConsumo != null
							&& !mediaConsumo.equals(new Integer("0"))) {
						variacao5 = Util.formatarMoedaReal(new BigDecimal(
								volumesFaturadosRelatorioHelper
										.getConsumoMesAno5()).divide(
								new BigDecimal(mediaConsumo), 4,
								BigDecimal.ROUND_HALF_UP).subtract(
								new BigDecimal("1.00")).multiply(
								new BigDecimal("100.00")));
					}

				}

				// Calcula a varia��o dos totalizadores de cada grupo
				if (mesAno5TotalSetorComercial != null) {

					if (mediaTotalSetorComercial != null
							&& !mediaTotalSetorComercial
									.equals(new Integer("0"))) {
						variacao5TotalSetorComercial = Util
								.formatarMoedaReal(new BigDecimal(
										mesAno5TotalSetorComercial)
										.divide(
												new BigDecimal(
														mediaTotalSetorComercial),
												4, BigDecimal.ROUND_HALF_UP)
										.subtract(new BigDecimal("1.00"))
										.multiply(new BigDecimal("100.00")));
					}

				}

				if (mesAno5TotalLocalidade != null) {

					if (mediaTotalLocalidade != null
							&& !mediaTotalLocalidade.equals(new Integer("0"))) {
						variacao5TotalLocalidade = Util
								.formatarMoedaReal(new BigDecimal(
										mesAno5TotalLocalidade).divide(
										new BigDecimal(mediaTotalLocalidade),
										4, BigDecimal.ROUND_HALF_UP).subtract(
										new BigDecimal("1.00")).multiply(
										new BigDecimal("100.00")));
					}

				}

				// Consumo e Varia��o M�s/Ano 6
				String consumoMesAno6 = "";
				String variacao6 = "";

				if (volumesFaturadosRelatorioHelper.getConsumoMesAno6() != null) {
					consumoMesAno6 = volumesFaturadosRelatorioHelper
							.getConsumoMesAno6().toString();
					
					peloMenosUmDadoMesAno6SetorComercial = true;
					peloMenosUmDadoMesAno6Localidade = true;

					// Soma os valores aos totalizadores de cada grupo
					mesAno6TotalSetorComercial = mesAno6TotalSetorComercial
							+ volumesFaturadosRelatorioHelper
									.getConsumoMesAno6();
					mesAno6TotalLocalidade = mesAno6TotalLocalidade
							+ volumesFaturadosRelatorioHelper
									.getConsumoMesAno6();

					// Calcula a varia��o
					if (mediaConsumo != null
							&& !mediaConsumo.equals(new Integer("0"))) {
						variacao6 = Util.formatarMoedaReal(new BigDecimal(
								volumesFaturadosRelatorioHelper
										.getConsumoMesAno6()).divide(
								new BigDecimal(mediaConsumo), 4,
								BigDecimal.ROUND_HALF_UP).subtract(
								new BigDecimal("1.00")).multiply(
								new BigDecimal("100.00")));
					}

				}

				// Calcula a varia��o dos totalizadores de cada grupo
				if (mesAno6TotalSetorComercial != null) {

					if (mediaTotalSetorComercial != null
							&& !mediaTotalSetorComercial
									.equals(new Integer("0"))) {
						variacao6TotalSetorComercial = Util
								.formatarMoedaReal(new BigDecimal(
										mesAno6TotalSetorComercial)
										.divide(
												new BigDecimal(
														mediaTotalSetorComercial),
												4, BigDecimal.ROUND_HALF_UP)
										.subtract(new BigDecimal("1.00"))
										.multiply(new BigDecimal("100.00")));
					}

				}

				if (mesAno6TotalLocalidade != null) {

					if (mediaTotalLocalidade != null
							&& !mediaTotalLocalidade.equals(new Integer("0"))) {
						variacao6TotalLocalidade = Util
								.formatarMoedaReal(new BigDecimal(
										mesAno6TotalLocalidade).divide(
										new BigDecimal(mediaTotalLocalidade),
										4, BigDecimal.ROUND_HALF_UP).subtract(
										new BigDecimal("1.00")).multiply(
										new BigDecimal("100.00")));
					}

				}
				
				// Cria as vari�veis para caso n�o exista nenhum consumo para um totalizador setor vazio
				String mesAno1TotalSetorComercialFormatado = "";
				String mesAno2TotalSetorComercialFormatado = "";
				String mesAno3TotalSetorComercialFormatado = "";
				String mesAno4TotalSetorComercialFormatado = "";
				String mesAno5TotalSetorComercialFormatado = "";
				String mesAno6TotalSetorComercialFormatado = "";
				
				String mesAno1TotalLocalidadeFormatado = "";
				String mesAno2TotalLocalidadeFormatado = "";
				String mesAno3TotalLocalidadeFormatado = "";
				String mesAno4TotalLocalidadeFormatado = "";
				String mesAno5TotalLocalidadeFormatado = "";
				String mesAno6TotalLocalidadeFormatado = "";
				
				// Verifica se deve ser alterado o valor dos totalizadores de setor comercial de 0 para vazio
				if (peloMenosUmDadoMesAno1SetorComercial) {
					mesAno1TotalSetorComercialFormatado = mesAno1TotalSetorComercial.toString();
				} else {
					variacao1TotalSetorComercial = "";
				}
				
				if (peloMenosUmDadoMesAno2SetorComercial) {
					mesAno2TotalSetorComercialFormatado = mesAno2TotalSetorComercial.toString();
				} else {
					variacao2TotalSetorComercial = "";
				}
				
				if (peloMenosUmDadoMesAno3SetorComercial) {
					mesAno3TotalSetorComercialFormatado = mesAno3TotalSetorComercial.toString();
				} else {
					variacao3TotalSetorComercial = "";
				}
				
				if (peloMenosUmDadoMesAno4SetorComercial) {
					mesAno4TotalSetorComercialFormatado = mesAno4TotalSetorComercial.toString();
				} else {
					variacao4TotalSetorComercial = "";
				}
				
				if (peloMenosUmDadoMesAno5SetorComercial) {
					mesAno5TotalSetorComercialFormatado = mesAno5TotalSetorComercial.toString();
				} else {
					variacao5TotalSetorComercial = "";
				}
				
				if (peloMenosUmDadoMesAno6SetorComercial) {
					mesAno6TotalSetorComercialFormatado = mesAno6TotalSetorComercial.toString();
				} else {
					variacao6TotalSetorComercial = "";
				}
				
				// Verifica se deve ser alterado o valor dos totalizadores de localidade de 0 para vazio
				if (peloMenosUmDadoMesAno1Localidade) {
					mesAno1TotalLocalidadeFormatado = mesAno1TotalLocalidade.toString();
				} else {
					variacao1TotalLocalidade = "";
				}
				
				if (peloMenosUmDadoMesAno2Localidade) {
					mesAno2TotalLocalidadeFormatado = mesAno2TotalLocalidade.toString();
				} else {
					variacao2TotalLocalidade = "";
				}
				
				if (peloMenosUmDadoMesAno3Localidade) {
					mesAno3TotalLocalidadeFormatado = mesAno3TotalLocalidade.toString();
				} else {
					variacao3TotalLocalidade = "";
				}
				
				if (peloMenosUmDadoMesAno4Localidade) {
					mesAno4TotalLocalidadeFormatado = mesAno4TotalLocalidade.toString();
				} else {
					variacao4TotalLocalidade = "";
				}
				
				if (peloMenosUmDadoMesAno5Localidade) {
					mesAno5TotalLocalidadeFormatado = mesAno5TotalLocalidade.toString();
				} else {
					variacao5TotalLocalidade = "";
				}
				
				if (peloMenosUmDadoMesAno6Localidade) {
					mesAno6TotalLocalidadeFormatado = mesAno6TotalLocalidade.toString();
				} else {
					variacao6TotalLocalidade = "";
				}

				relatorioBean = new RelatorioVolumesFaturadosBean(

				// Localidade
						localidade,

						// Id do Setor Comercial
						idSetorComercial,
						
						// Setor Comercial
						setorComercial,
						
						// N�mero da Quadra
						numeroQuadra,
						
						// M�dia de Consumo
						media,
						
						// Consumo do M�s/Ano 1
						consumoMesAno1,
						
						// Varia��o do M�s/Ano 1
						variacao1,
						
						// Consumo do M�s/Ano 2
						consumoMesAno2,
						
						// Varia��o do M�s/Ano 2
						variacao2,
						
						// Consumo do M�s/Ano 3
						consumoMesAno3,
						
						// Varia��o do M�s/Ano 3
						variacao3,
						
						// Consumo do M�s/Ano 4
						consumoMesAno4,
						
						// Varia��o do M�s/Ano 4
						variacao4,
						
						// Consumo do M�s/Ano 5
						consumoMesAno5,
						
						// Varia��o do M�s/Ano 5
						variacao5,
						
						// Consumo do M�s/Ano 6
						consumoMesAno6,
						
						// Varia��o do M�s/Ano 6
						variacao6,
						
						// Totalizadores do Setor Comercial
						// M�dia de Consumo
						mediaTotalSetorComercial.toString(),
						
						// Consumo do M�s/Ano 1
						mesAno1TotalSetorComercialFormatado,
						
						// Varia��o do M�s/Ano 1
						variacao1TotalSetorComercial,
						
						// Consumo do M�s/Ano 2
						mesAno2TotalSetorComercialFormatado,
						
						// Varia��o do M�s/Ano 2
						variacao2TotalSetorComercial,
						
						// Consumo do M�s/Ano 3
						mesAno3TotalSetorComercialFormatado,
						
						// Varia��o do M�s/Ano 3
						variacao3TotalSetorComercial,
						
						// Consumo do M�s/Ano 4
						mesAno4TotalSetorComercialFormatado,
						
						// Varia��o do M�s/Ano 4
						variacao4TotalSetorComercial,
						
						// Consumo do M�s/Ano 5
						mesAno5TotalSetorComercialFormatado,
						
						// Varia��o do M�s/Ano 5
						variacao5TotalSetorComercial,
						
						// Consumo do M�s/Ano 6
						mesAno6TotalSetorComercialFormatado,
						
						// Varia��o do M�s/Ano 6
						variacao6TotalSetorComercial,
						
						// Totalizadores da Localidade
						// M�dia de Consumo
						mediaTotalLocalidade.toString(),
						
						// Consumo do M�s/Ano 1
						mesAno1TotalLocalidadeFormatado,
						
						// Varia��o do M�s/Ano 1
						variacao1TotalLocalidade,
						
						// Consumo do M�s/Ano 2
						mesAno2TotalLocalidadeFormatado,
						
						// Varia��o do M�s/Ano 2
						variacao2TotalLocalidade,
						
						// Consumo do M�s/Ano 3
						mesAno3TotalLocalidadeFormatado,
						
						// Varia��o do M�s/Ano 3
						variacao3TotalLocalidade,
						
						// Consumo do M�s/Ano 4
						mesAno4TotalLocalidadeFormatado,
						
						// Varia��o do M�s/Ano 4
						variacao4TotalLocalidade,
						
						// Consumo do M�s/Ano 5
						mesAno5TotalLocalidadeFormatado,
						
						// Varia��o do M�s/Ano 5
						variacao5TotalLocalidade,
						
						// Consumo do M�s/Ano 6
						mesAno6TotalLocalidadeFormatado,
						
						// Varia��o do M�s/Ano 6
						variacao6TotalLocalidade);

				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioBean);

			}
		}
		// __________________________________________________________________

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("mesAno", Util.formatarAnoMesParaMesAno(anoMes));
		parametros.put("mesAno1", Util.formatarAnoMesParaMesAno(anoMes1));
		parametros.put("mesAno2", Util.formatarAnoMesParaMesAno(anoMes2));
		parametros.put("mesAno3", Util.formatarAnoMesParaMesAno(anoMes3));
		parametros.put("mesAno4", Util.formatarAnoMesParaMesAno(anoMes4));
		parametros.put("mesAno5", Util.formatarAnoMesParaMesAno(anoMes5));
		parametros.put("mesAno6", Util.formatarAnoMesParaMesAno(anoMes6));

		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_VOLUMES_FATURADOS_RESUMIDO,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.VOLUMES_FATURADOS_RESUMIDO,
					idFuncionalidadeIniciada);
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
		int retorno = 0;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioVolumesFaturadosResumido",
				this);
	}
}