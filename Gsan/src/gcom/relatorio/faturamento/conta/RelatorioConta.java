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
package gcom.relatorio.faturamento.conta;

import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

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
public class RelatorioConta extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioConta(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_CONTAS);
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

		Integer mesAno = (Integer) getParametro("mesAno");
		Integer idGrupo = (Integer) getParametro("idGrupo");
		Integer idLocalidadeInicial = (Integer) getParametro("idLocalidadeInicial");
		Integer idLocalidadeFinal = (Integer) getParametro("idLocalidadeFinal");
		Integer codigoSetorComercialInicial = (Integer) getParametro("codigoSetorComercialInicial");
		Integer codigoSetorComercialFinal = (Integer) getParametro("codigoSetorComercialFinal");
		Short codigoRotaInicial = (Short) getParametro("codigoRotaInicial");
		Short codigoRotaFinal = (Short) getParametro("codigoRotaFinal");
		Short sequencialRotaInicial = (Short) getParametro("sequencialRotaInicial");
		Short sequencialRotaFinal = (Short) getParametro("sequencialRotaFinal");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		String indicadorEmissao = (String) getParametro("indicadorEmissao");
		String indicadorOrdenacao = (String) getParametro("indicadorOrdenacao");
		
		// valor de retorno
		byte[] retorno = null;


		Fachada fachada = Fachada.getInstancia();

		// cole��o de beans do relat�rio
		List relatorioBeans = (List) fachada.pesquisarDadosContaRelatorio(
				mesAno, idGrupo, idLocalidadeInicial, idLocalidadeFinal,
				codigoSetorComercialInicial, codigoSetorComercialFinal,
				codigoRotaInicial, codigoRotaFinal,
				sequencialRotaInicial, sequencialRotaFinal, indicadorEmissao, indicadorOrdenacao);
		
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

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

		String nomeEmpresa = sistemaParametro.getNomeEmpresa();
		String enderecoEmpresaParte01 = sistemaParametro.getEnderecoFormatadoParte01();
		String enderecoEmpresaParte02 = sistemaParametro.getEnderecoFormatadoParte02();
		String enderecoEmpresaSemComplemento = sistemaParametro.getEnderecoFormatadoSemComplemento();
		String cepEmpresa = sistemaParametro.getCep().getCepFormatado();
		String cnpjEmpresa = Util.formatarCnpj(sistemaParametro.getCnpjEmpresa());
		
		
		String telefoneGeral = "";
		if (sistemaParametro.getLogradouroBairro().getBairro().getMunicipio().getDdd() != null) {
			telefoneGeral += "(" + sistemaParametro.getLogradouroBairro().getBairro().getMunicipio().getDdd() + ")";
		}
		telefoneGeral += Util.formatarTelefone(sistemaParametro.getNumeroTelefone());
		
		String fax = "";
		if (sistemaParametro.getLogradouroBairro().getBairro().getMunicipio().getDdd() != null) {
			fax += "(" + sistemaParametro.getLogradouroBairro().getBairro().getMunicipio().getDdd() + ")";
		}
		fax += Util.formatarTelefone(sistemaParametro.getNumeroFax());
		String cidadeEstado =  sistemaParametro.getNomeEstado();
		
		String telefoneAtendEsgoto = "";
		if (sistemaParametro.getLogradouroBairro().getBairro().getMunicipio().getDdd() != null && 
				sistemaParametro.getNumeroTelefoneAtendimentoEsgoto() != null) {
			telefoneAtendEsgoto += "(" + sistemaParametro.getLogradouroBairro().getBairro().getMunicipio().getDdd() + ")";
			telefoneAtendEsgoto += Util.formatarTelefone(sistemaParametro.getNumeroTelefoneAtendimentoEsgoto());
		}

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("nomeEmpresa", nomeEmpresa);
		parametros.put("enderecoEmpresaParte01", enderecoEmpresaParte01);
		parametros.put("enderecoEmpresaParte02", enderecoEmpresaParte02);
		parametros.put("enderecoEmpresaSemComplemento", enderecoEmpresaSemComplemento);
		parametros.put("cepEmpresa", cepEmpresa);
		parametros.put("cnpjEmpresa", cnpjEmpresa);
		parametros.put("telefoneGeral", telefoneGeral);
		parametros.put("fax", fax);
		parametros.put("cidadeEstado", cidadeEstado);
		parametros.put("telefoneAtendEsgoto", telefoneAtendEsgoto);
		
		/*
		 * Adicionado por: Hugo Azevedo
		 * Data: 13/09/2011
		 */
		if (sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase(
				SistemaParametro.EMPRESA_COSAMA)){
			retorno = gerarRelatorio(
					ConstantesRelatorios.RELATORIO_CONTAS_COSAMA,
					parametros, ds, tipoFormatoRelatorio);
		}else if(sistemaParametro.getCodigoEmpresaFebraban()
				.equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_JUAZEIRO)){
			retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_CONTAS_SAAE,
				parametros, ds, tipoFormatoRelatorio);
		}
		else{
			retorno = gerarRelatorio(
					ConstantesRelatorios.RELATORIO_CONTAS,
					parametros, ds, tipoFormatoRelatorio);
		}

		// ------------------------------------
		// Grava o relat�rio no sistema
		/*try {
			persistirRelatorioConcluido(retorno,
					Relatorio.RELATORIO_CONTAS,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		}*/	
			

		// retorna o relat�rio gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 1;
		
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();		
		
			Integer mesAno = (Integer) getParametro("mesAno");
			Integer idGrupo = (Integer) getParametro("idGrupo");
			Integer idLocalidadeInicial = (Integer) getParametro("idLocalidadeInicial");
			Integer idLocalidadeFinal = (Integer) getParametro("idLocalidadeFinal");
			Integer codigoSetorComercialInicial = (Integer) getParametro("codigoSetorComercialInicial");
			Integer codigoSetorComercialFinal = (Integer) getParametro("codigoSetorComercialFinal");
			Short codigoRotaInicial = (Short) getParametro("codigoRotaInicial");
			Short codigoRotaFinal = (Short) getParametro("codigoRotaFinal");
			Short sequencialRotaInicial = (Short) getParametro("sequencialRotaInicial");
			Short sequencialRotaFinal = (Short) getParametro("sequencialRotaFinal");
			int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
			String indicadorEmissao = (String) getParametro("indicadorEmissao");
			String indicadorOrdenacao = (String) getParametro("indicadorOrdenacao");
			
			retorno =  Fachada.getInstancia().pesquisarDadosContaRelatorioCount(
				mesAno, idGrupo, idLocalidadeInicial, idLocalidadeFinal,
				codigoSetorComercialInicial, codigoSetorComercialFinal,
				codigoRotaInicial, codigoRotaFinal,
				sequencialRotaInicial, sequencialRotaFinal, indicadorEmissao, indicadorOrdenacao);
		

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioContas",
				this);
	}
}