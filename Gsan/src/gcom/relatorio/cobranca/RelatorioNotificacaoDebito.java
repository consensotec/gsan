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
package gcom.relatorio.cobranca;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaAcao;
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
import java.util.List;
import java.util.Map;

/**
 * classe respons�vel por criar o relat�rio de contas
 * 
 * @author Rafael Corr�a
 * @created 27/07/2009
 */
public class RelatorioNotificacaoDebito extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioNotificacaoDebito(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_NOTIFICACAO_DEBITO);
	}

	@Deprecated
	public RelatorioNotificacaoDebito() {
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

		Integer idCobrancaAcaoCronograma = (Integer) getParametro("idCobrancaAcaoCronograma");
		Integer idCobrancaAcaoComando = (Integer) getParametro("idCobrancaAcaoComando");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		CobrancaAcao cobrancaAcao = (CobrancaAcao) getParametro("cobrancaAcao");

		//quantidades de relatorio a serem impressos em cada p�gina
		String quantidadeRelatorios = (String) getParametro("quantidadeRelatorios");

		//tamanho m�ximo de contas a aparecerem no relat�rio.
		int tamanhoMaximoDebito = 15;

		// valor de retorno
		byte[] retorno = null;


		Fachada fachada = Fachada.getInstancia();

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("bairro");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("cep");
		
		
		Collection colecaoSistemaParametro = fachada.pesquisar(filtroSistemaParametro,SistemaParametro.class.getName());
		
		SistemaParametro sistemaParametro = (SistemaParametro) colecaoSistemaParametro.iterator().next();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		//Caso a empresa seja COSANPA,  modificar a quantidade de d�bitos que ser�o mostrados no relatorio.
		if(sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_COSANPA)){
			tamanhoMaximoDebito = 39;
		}
		
		
		// cole��o de beans do relat�rio
		List relatorioBeans = (List) fachada.gerarRelatorioNotificacaoDebito(idCobrancaAcaoCronograma,
				idCobrancaAcaoComando, tamanhoMaximoDebito, quantidadeRelatorios);
		
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		
		
		String nomeEmpresa = sistemaParametro.getNomeEmpresa();
		String enderecoEmpresa = sistemaParametro.getEnderecoFormatado();
		String cepEmpresa = "CEP: " + sistemaParametro.getCep().getCepFormatado();
		String cnpjEmpresa = "CNPJ: " + Util.formatarCnpj(sistemaParametro.getCnpjEmpresa());
		String foneFaxEmpresa = "FONE: " + Util.formatarTelefone(sistemaParametro.getNumeroTelefone()) + "  "
								+ "FAX: " + Util.formatarTelefone(sistemaParametro.getNumeroFax());
		
		String textoPersonalizado = "";
		
		if(cobrancaAcao.getTextoPersonalizado() != null && !cobrancaAcao.getTextoPersonalizado().equals("")){
			cobrancaAcao.getTextoPersonalizado();
			textoPersonalizado = Fachada.getInstancia().buscarTextoPersonalizadoAcaoCobranca(cobrancaAcao.getId());
			System.out.println(textoPersonalizado);
		}
		
		parametros.put("nomeEmpresa", nomeEmpresa);
		parametros.put("enderecoEmpresa", enderecoEmpresa);
		parametros.put("cepEmpresa", cepEmpresa);
		parametros.put("cnpjEmpresa", cnpjEmpresa);
		parametros.put("foneFaxEmpresa", foneFaxEmpresa);
		parametros.put("textoPersonalizado", textoPersonalizado);
		
		//Verifica se a empresa � CAERN para alterar a partir de quanto tempo sera 
		//interrompido o fornecimento de agua.
//		if ( sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase(
//			SistemaParametro.EMPRESA_CAERN) ) {
//			
//			parametros.put("quantidadeDias", "30� (trig�simo)");
//			
//		} else {
//			parametros.put("quantidadeDias", "10� (d�cimo)");
//		}
		
		
		if(sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_COSANPA)){
			
			if(quantidadeRelatorios.equals("2")){
				//Array list adicionado para executar o relat�rio principal.
				ArrayList array = new ArrayList();
				array.add("relatorio");				
				RelatorioDataSource dsRelatorio = new RelatorioDataSource(array);
				
				//Data Source real, usado para preencher o subrelat�rio.
				parametros.put("dataSource", ds);
				
				retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_NOTIFICACAO_DEBITO_COSANPA_2_PAGINAS,				
						parametros, dsRelatorio, tipoFormatoRelatorio);
			}else{
				retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_NOTIFICACAO_DEBITO_COSANPA,
						parametros, ds, tipoFormatoRelatorio);
			}
			
		}
		else{
			
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_NOTIFICACAO_DEBITO,
			parametros, ds, tipoFormatoRelatorio);
		}

		
		
		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.ANORMALIDADE_CONSUMO,
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
		int retorno = 1;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioNotificacaoDebito",
				this);
	}
	
}