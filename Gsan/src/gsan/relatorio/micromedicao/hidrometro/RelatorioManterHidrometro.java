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
package gsan.relatorio.micromedicao.hidrometro;

import gsan.batch.Relatorio;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.micromedicao.FiltrarHidrometroHelper;
import gsan.micromedicao.hidrometro.FiltroHidrometro;
import gsan.micromedicao.hidrometro.Hidrometro;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.RelatorioDataSource;
import gsan.relatorio.RelatorioVazioException;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaException;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.ConstantesSistema;
import gsan.util.ControladorException;
import gsan.util.Util;
import gsan.util.agendadortarefas.AgendadorTarefas;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 
 * Title: GCOM
 * </p>
 * <p>
 * 
 * Description: Sistema de Gest�o Comercial
 * </p>
 * <p>
 * 
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Rafael Corr�a
 * @created 23 de Setembro de 2005
 * @version 1.0
 */

public class RelatorioManterHidrometro extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the RelatorioManterHidrometro object
	 */
	public RelatorioManterHidrometro(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_HIDROMETRO_MANTER);
	}
	
	@Deprecated
	public RelatorioManterHidrometro() {
		super(null, "");
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param hidrometros
	 *            Description of the Parameter
	 * @param HidrometroParametros
	 *            Description of the Parameter
	 * @return Descri��o do retorno
	 * @exception RelatorioVazioException
	 *                Descri��o da exce��o
	 */

	public Object executar() throws TarefaException {
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltroHidrometro filtroHidrometro = (FiltroHidrometro) getParametro("filtroHidrometro");
		Hidrometro hidrometroParametros = (Hidrometro) getParametro("hidrometroParametros");
		String fixo = (String) getParametro("fixo");
		String tombamento = (String) getParametro("tombamento");
		String faixaInicial = (String) getParametro("faixaInicial");
		String faixaFinal = (String) getParametro("faixaFinal");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		FiltrarHidrometroHelper helper = (FiltrarHidrometroHelper) getParametro("helper");

		// valor de retorno
		byte[] retorno = null;

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioManterHidrometroBean relatorioBean = null;

		Collection hidrometrosNovos = null;

		if (filtroHidrometro != null) {

			filtroHidrometro
					.adicionarCaminhoParaCarregamentoEntidade("hidrometroClasseMetrologica");
			filtroHidrometro
					.adicionarCaminhoParaCarregamentoEntidade("hidrometroMarca");
			filtroHidrometro
					.adicionarCaminhoParaCarregamentoEntidade("hidrometroDiametro");
			filtroHidrometro
					.adicionarCaminhoParaCarregamentoEntidade("hidrometroCapacidade");
			filtroHidrometro
					.adicionarCaminhoParaCarregamentoEntidade("hidrometroTipo");
			filtroHidrometro
			.adicionarCaminhoParaCarregamentoEntidade("hidrometroSituacao");
			
			filtroHidrometro.setConsultaSemLimites(true);

			// consulta para trazer objeto completo
			hidrometrosNovos = fachada.pesquisar(filtroHidrometro,
					Hidrometro.class.getName());
			
			helper = null;
			
		} else if(helper != null){
			hidrometrosNovos = fachada.pesquisarNumeroHidrometroSituacaoInstaladoRelatorio(helper);
			
		}else if (tombamento != null && !tombamento.trim().equals("")){
			hidrometrosNovos = fachada.pesquisarNumeroHidrometroMacroMedidorRelatorio(tombamento);
		} else {
			hidrometrosNovos = fachada.pesquisarNumeroHidrometroFaixaRelatorio(fixo, faixaInicial, faixaFinal);
		}

		if (hidrometrosNovos != null && !hidrometrosNovos.isEmpty()) {
			// coloca a cole��o de par�metros da analise no iterator
			Iterator hidrometroNovoIterator = hidrometrosNovos.iterator();

			SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");

			// la�o para criar a cole��o de par�metros da analise
			while (hidrometroNovoIterator.hasNext()) {

				Hidrometro hidrometroNovo = (Hidrometro) hidrometroNovoIterator
						.next();
				
				String situacao = hidrometroNovo.getHidrometroSituacao() == null ? ""
						: hidrometroNovo.getHidrometroSituacao()
						.getDescricao();
				
				String matricula = "";
				
				String dataInstalacao = "";
				//alterado por R�mulo Aur�lio CRC 1671 Analista:Rosana Carvalho
				// Caso a situa�ao do hidrometro seja INSTALADO
				//colocar a matr�cula do im�vel junto com a Data de Intalacao do hidrometro
				if(hidrometroNovo.getHidrometroSituacao().getId().equals(Hidrometro.SITUACAO_INSTALADO)){
					Integer idImovel = fachada.pesquisarImovelPeloHidrometro(hidrometroNovo.getId());
					Date dataInstalacaoHidrometro = fachada.pesquisarDataInstalacaoHidrometroAgua(idImovel);
					matricula = idImovel.toString() ;
					
					dataInstalacao = Util.formatarData(dataInstalacaoHidrometro);
					
				}
				String hidrometroTipo = "";
				if (hidrometroNovo.getHidrometroTipo() != null
						&& hidrometroNovo.getHidrometroTipo().getDescricao() != null) {
					hidrometroTipo = hidrometroNovo.getHidrometroTipo().getDescricao();
				}
				
				String numero = "";
				
				if (hidrometroNovo.getNumero() != null 
						&& !hidrometroNovo.getNumero().trim().equals("")) {
					numero = hidrometroNovo.getNumero();
				} else {
					numero = hidrometroNovo.getTombamento();
				}
				
				relatorioBean = new RelatorioManterHidrometroBean(

						// N�mero
						numero,

						// Data de Aquisi��o
						dataFormatada.format(hidrometroNovo.getDataAquisicao()),

						// Ano de Fabrica��o
						hidrometroNovo.getAnoFabricacao().toString(),

						// Finalidade
						hidrometroNovo.getIndicadorOperacional() == 1 ? "COMERCIAL"
								: "OPERACIONAL",

						// Classe Metrol�gicao
						hidrometroNovo.getHidrometroClasseMetrologica()
								.getDescricao(),

						// Marca
						hidrometroNovo.getHidrometroMarca().getDescricao(),

						// Di�metro
						hidrometroNovo.getHidrometroDiametro().getDescricao(),

						// Capacidade
						hidrometroNovo.getHidrometroCapacidade().getDescricao(),

						// N�mero de Digitos
						hidrometroNovo.getNumeroDigitosLeitura().toString(),

						// Tipo
						hidrometroTipo,
						
						//Situa��o
						situacao,
						//matricula
						matricula,
						//DataInstalacao
						dataInstalacao);
							

				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioBean);
			}

		}

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String dataAquisicao = "";
		if (hidrometroParametros.getDataAquisicao() != null
				&& !hidrometroParametros.getDataAquisicao().equals("")) {
			dataAquisicao = format.format(hidrometroParametros
					.getDataAquisicao());
		}

		String numeroQuadra = "";
		
		String descricao = "";
		
		String codigo = "";
		
		if( helper!=null ){
			
			descricao = helper.getIdLocalidade() + "-" + helper.getNomeLocalidade() + "";
			
			
			codigo = helper.getCodigoSetorComercial();
			
			
			numeroQuadra = helper.getNumeroQuadra();
			
			
		}
		parametros.put("codigoSetorComercial", codigo);
		parametros.put("descricaoLocalidade",descricao);
		parametros.put("numeroQuadra", numeroQuadra);
		parametros.put("numero", hidrometroParametros.getNumero());

		parametros.put("dataAquisicao", dataAquisicao);

		parametros.put("anoFabricacao",
				hidrometroParametros.getAnoFabricacao() == null ? "" : ""
						+ hidrometroParametros.getAnoFabricacao());

		parametros.put("classeMetrologica", hidrometroParametros
				.getHidrometroClasseMetrologica() == null ? ""
				: hidrometroParametros.getHidrometroClasseMetrologica()
						.getDescricao());

		parametros.put("marca",
				hidrometroParametros.getHidrometroMarca() == null ? ""
						: hidrometroParametros.getHidrometroMarca()
								.getDescricao());

		parametros.put("diametro",
				hidrometroParametros.getHidrometroDiametro() == null ? ""
						: hidrometroParametros.getHidrometroDiametro()
								.getDescricao());

		parametros.put("capacidade", hidrometroParametros
				.getHidrometroCapacidade() == null ? "" : hidrometroParametros
				.getHidrometroCapacidade().getDescricao());

		parametros.put("tipo",
				hidrometroParametros.getHidrometroTipo() == null ? ""
						: hidrometroParametros.getHidrometroTipo()
								.getDescricao());

		parametros.put("idLocalArmazenagem", hidrometroParametros
				.getHidrometroLocalArmazenagem().getId() == null ? "" : ""
				+ hidrometroParametros.getHidrometroLocalArmazenagem().getId());

		parametros.put("nomeLocalArmazenagem", hidrometroParametros
				.getHidrometroLocalArmazenagem().getDescricao());

		parametros.put("fixo", fixo);

		parametros.put("faixaInicial", faixaInicial);

		parametros.put("faixaFinal", faixaFinal);

		String finalidade = "";

		if (hidrometroParametros.getIndicadorOperacional() != null
				&& !hidrometroParametros.getIndicadorOperacional().equals("")) {
			if (hidrometroParametros.getIndicadorOperacional().equals(
					new Short("1"))) {
				finalidade = "Comercial";
			} else {
				finalidade = "Operacional";
			}
		}

		parametros.put("finalidade", finalidade);

		parametros.put("tombamento", hidrometroParametros
				.getTombamento());
		if (hidrometroParametros.getHidrometroFatorCorrecao() != null) {
			parametros.put("erroMacromedidor", 
				hidrometroParametros.getHidrometroFatorCorrecao().getDescricao());
		}
		if (hidrometroParametros.getHidrometroClassePressao() != null) {
			parametros.put("classePressao", 
				hidrometroParametros.getHidrometroClassePressao().getDescricao());
		}
		if (hidrometroParametros.getIndicadorMacromedidor() != null
				&& hidrometroParametros.getIndicadorMacromedidor().compareTo(ConstantesSistema.SIM) == 0) {
			parametros.put("descricaoColunaNum","Tombamento");
		} else {
			parametros.put("descricaoColunaNum","N�mero");
		}
		
		
		// cria uma inst�ncia do dataSource do relat�rio

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_HIDROMETRO_MANTER,
				parametros, ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.MANTER_HIDROMETRO,
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

		if (getParametro("filtroHidrometro") != null) {

			retorno = Fachada.getInstancia().totalRegistrosPesquisa(
					(FiltroHidrometro) getParametro("filtroHidrometro"),
					Hidrometro.class.getName());

		}else if(getParametro("helper")!= null){
			retorno = Fachada.getInstancia().pesquisarNumeroHidrometroSituacaoInstaladoPaginacaoCount((FiltrarHidrometroHelper) getParametro("helper"));
			
		} 
		else  if (getParametro("tombamento") != null && !getParametro("tombamento").toString().trim().equals("")){
			retorno = Fachada.getInstancia().pesquisarNumeroHidrometroFaixaCount(getParametro("tombamento").toString());
		} else {

			String faixaInicial = (String) getParametro("faixaInicial");
			String faixaFinal = (String) getParametro("faixaFinal");
			String fixo = (String) getParametro("fixo");

			String numeroFormatadoInicial = "";
			String numeroFormatadoFinal = "";

			numeroFormatadoInicial = Util.adicionarZerosEsquedaNumero(6,
					faixaInicial);
			numeroFormatadoFinal = Util.adicionarZerosEsquedaNumero(6,
					faixaFinal);

			Integer totalRegistros = Fachada.getInstancia()
					.pesquisarNumeroHidrometroFaixaCount(fixo,
							fixo + numeroFormatadoInicial,
							fixo + numeroFormatadoFinal);

			retorno = totalRegistros.intValue();
		}

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterHidrometro", this);
	}

}
