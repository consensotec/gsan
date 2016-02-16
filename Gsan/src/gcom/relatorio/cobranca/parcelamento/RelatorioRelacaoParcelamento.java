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
package gcom.relatorio.cobranca.parcelamento;

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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe respons�vel por criar o relat�rio de 
 * [UC0580]Emitir Protocolo de Documento de Cobran�a do Cronogrma
 * 
 * @author Ana Maria
 * @date 05/10/06
 * 
 */
public class RelatorioRelacaoParcelamento extends TarefaRelatorio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RelatorioRelacaoParcelamento(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_EMITIR_PROTOCOLO_DOCUMENTO_COBRANCA);
	}
	
	@Deprecated
	public RelatorioRelacaoParcelamento() {
		super(null, "");
	}

	private Collection<RelatorioRelacaoParcelamentoBean> inicializarBeanRelatorio(
			Collection<RelacaoParcelamentoRelatorioHelper> dadosRelatorio) {

		Collection<RelatorioRelacaoParcelamentoBean> retorno = new ArrayList();
		
		Iterator iterator = dadosRelatorio.iterator();
		while (iterator.hasNext()) {
			
			RelacaoParcelamentoRelatorioHelper helper = (RelacaoParcelamentoRelatorioHelper)iterator.next();
			
			String situacao = "";
			if (helper.getSituacao() != null) {
				situacao = helper.getSituacao();
			}
			
			String localidade = "";
			if (helper.getLocalidade() != null) {
				localidade = helper.getIdGerencia() + "-" +helper.getGerencia()+"/"+ helper.getLocalidade();
			}
			
			String cliente = "";
			String telefone = "";
			if (helper.getCliente() != null) {
				cliente = helper.getCliente();
				if (helper.getTelefone() != null) {
					telefone = helper.getDdd()+" "+ helper.getTelefone();
				}
			}

			String matricula = "";
			if (helper.getMatricula() != null) {
				matricula = helper.getMatricula().toString();
			}
			
			String idParcelamento = "";
			if (helper.getParcelamento() != null) {
				idParcelamento = helper.getParcelamento().toString();
			}
			
			BigDecimal valorDebito = new BigDecimal("0.00");
			if (helper.getDebitoTotal() != null) {
				valorDebito = helper.getDebitoTotal();
			}
			
			BigDecimal valorEntrada = new BigDecimal("0.00");
			if (helper.getValorEntrada() != null) {
				valorEntrada = helper.getValorEntrada();
			}
			
			BigDecimal valorParcelas = new BigDecimal("0.00");
			if (helper.getValorParcelamento() != null) {
				valorParcelas = helper.getValorParcelamento();
			}
			
			String dataParcelamento = "";
			if (helper.getDataParcelamento() != null) {
				dataParcelamento = Util.formatarData(helper.getDataParcelamento());
			}
			
			String  vencimento = "";
			if (helper.getVencimento() != null) {
				vencimento = helper.getVencimento().toString();
				//vencimento = vencimento.substring(0, 2);
			}
			
			String numeroParcelas = "";
			if (helper.getNumeroParcelamento() != null) {
				numeroParcelas = helper.getNumeroParcelamento().toString();
			}
			
			String idLocalidade = "";
			if (helper.getIdLocalidade() != null) {
				idLocalidade = helper.getIdLocalidade().toString();
			}
			
			String idGerencia = "";
			if (helper.getIdGerencia() != null) {
				idGerencia = helper.getIdGerencia().toString();
			}			
			
			String gerencia = "";
			if (helper.getGerencia() != null) {
				gerencia = helper.getIdGerencia() + "-" +helper.getGerencia();
			}	
			
			String unidade = "";
			if (helper.getUnidade() != null) {
				unidade = helper.getUnidade();
			}
			
			String municipio = "";
			if(helper.getMunicipio() != null){
				municipio = helper.getMunicipio();
			}
			
			String idMunicipio = "";
			if(helper.getIdMunicipio() != null){
				idMunicipio = helper.getIdMunicipio().toString();
			}
			RelatorioRelacaoParcelamentoBean bean = new 
			RelatorioRelacaoParcelamentoBean(situacao, localidade, municipio,
					cliente, telefone, matricula, idParcelamento, valorDebito, valorEntrada,
					valorParcelas, dataParcelamento, vencimento,numeroParcelas, idLocalidade, 
					idMunicipio, idGerencia, gerencia, unidade);
			
			retorno.add(bean);
			
		}
		
		return retorno;
	}

	/**
	 * M�todo que executa a tarefa
	 * 
	 * @return Object
	 */
	public Object executar() throws TarefaException {
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		Collection dadosRelatorio = (Collection)getParametro("colecaoRelacaoParcelamento");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		String cabecalho = (String)getParametro("cabecalho");
		String faixaValores = (String)getParametro("faixaValores");
		String periodo = (String)getParametro("periodo");
		
		String gerencia = (String)getParametro("parametrosGerencia");
		String unidadeOrganizacional = (String)getParametro("parametroUnidadeOrganizacional");
		String unidadeNegocio = (String)getParametro("parametroUnidadeNegocio");
		String elo = (String)getParametro("parametroElo");
		String periodoParcelamento = (String)getParametro("parametroPeriodo");
		String usuarioParcelamento = (String)getParametro("parametroUsuario");
		String perfilImovel = (String)getParametro("parametroPerfilImovel");
		String valorParcelamento = (String)getParametro("parametroValor");
		String municipio = (String)getParametro("municipio");
		
		// valor de retorno
		byte[] retorno = null;

		Fachada fachada = Fachada.getInstancia();
		
		// Par�metros do relat�rio
		Map parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		parametros.put("cabecalho", cabecalho);
		
		parametros.put("faixaValores" , faixaValores);
		
		parametros.put("periodo", periodo);
		
		//String parametrosFiltro = (String)getParametro("parametros");
		
		//parametros.put("parametros", parametrosFiltro );
		
		parametros.put("parametrosGerencia", gerencia );
		parametros.put("parametroUnidadeOrganizacional", unidadeOrganizacional );
		parametros.put("parametroUnidadeNegocio", unidadeNegocio );
		parametros.put("parametroElo", elo );
		parametros.put("parametroPeriodo", periodoParcelamento );
		parametros.put("parametroUsuario", usuarioParcelamento );
		parametros.put("parametroPerfilImovel", perfilImovel );
		parametros.put("parametroValor", valorParcelamento );
		parametros.put("parametroMunicipio", municipio);
		
		parametros.put("numeroRelatorio", "R0594");
		
		Collection<RelatorioRelacaoParcelamentoBean> colecaoBean = this
				.inicializarBeanRelatorio(dadosRelatorio);

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// N�o existem dados para a exibi��o do relat�rio.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_RELACAO_PARCELAMENTO,
				parametros, ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELACAO_PARCELAMENTO,
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

		//retorno = ((Collection) getParametro("idsGuiaDevolucao")).size();

		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioRelacaoParcelamento", this);
	}
}