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
package gsan.relatorio.faturamento;

import gsan.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gsan.cadastro.cliente.EsferaPoder;
import gsan.cadastro.imovel.Categoria;
import gsan.cadastro.imovel.ImovelPerfil;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.gerencial.bean.InformarDadosGeracaoRelatorioConsultaHelper;
import gsan.gerencial.bean.ResumoFaturamentoSimulacaoDetalheHelper;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.RelatorioDataSource;
import gsan.relatorio.RelatorioVazioException;
import gsan.relatorio.gerencial.faturamento.RelatorioAnaliseFaturamentoBean;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaException;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.ConstantesSistema;
import gsan.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * classe respons�vel por criar o relat�rio de bairro manter de �gua
 * 
 * @author Fernanda Paiva
 * @created 11 de Julho de 2005
 */
public class RelatorioAnaliseFaturamento extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;
	public RelatorioAnaliseFaturamento(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_ANALISE_FATURAMENTO);
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
		
		Fachada fachada = Fachada.getInstancia();
		
		//List retornoConsulta =  (List) getParametro("colecaoAnaliseFaturamento");
		Integer tipoAnalise =  (Integer) getParametro("tipoAnalise");
		String descricao =  (String) getParametro("descricao");
		String mesAnoFaturamento =  (String) getParametro("mesAnoFaturamento");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper 
			=  (InformarDadosGeracaoRelatorioConsultaHelper) getParametro("informarDadosGeracaoRelatorioConsultaHelper");
		

		//----------------------------------------------------------------------------------------------------------------
        // Alterado por : Yara T. Souza.
		// Data : 26/08/2008
		//----------------------------------------------------------------------------------------------------------------
	  	String localidade = (String) getParametro("localidade");
		String gerenciaRegional = (String) getParametro("gerenciaRegional");
		String unidadeNegocio = (String) getParametro("unidadeNegocio");
		String setorComercial = (String) getParametro("setorComercial");
		String quadra = (String) getParametro("quadra");
		String rota = (String) getParametro("rota");
		String grupoFaturamento = (String) getParametro("grupoFaturamento");
		
		String tipoQuebra = (String) getParametro("tipoQuebra");
		
		Collection colecaoPerfilImovel = (Collection) getParametro("colecaoPerfilImovel");
		Collection colecaoLigacaoAgua = (Collection) getParametro("colecaoLigacaoAgua");
		Collection colecaoLigacaoEsgoto = (Collection) getParametro("colecaoLigacaoEsgoto");
		Collection colecaoCategoria = (Collection) getParametro("colecaoCategoria");
		Collection colecaoEsferaPoder = (Collection) getParametro("colecaoEsferaPoder");
		
		//----------------------------------------------------------------------------------
		
		
		
		
		// valor de retorno
		byte[] retorno = null;

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();
		
		RelatorioAnaliseFaturamentoBean relatorioBean = null;

		BigDecimal valorTotalConta = BigDecimal.ZERO;
		
		Integer quantidadeConta = 0;
		
		Integer quantidadeEconomia = 0;
		
		Integer volumeConsumidoAgua = 0;
		
		Integer volumeColetadoEsgoto = 0;
		
		BigDecimal valorFaturadoAgua = BigDecimal.ZERO;
		
		BigDecimal valorFaturadoEsgoto = BigDecimal.ZERO;
		
		BigDecimal debitosCobrados = BigDecimal.ZERO;
		
		BigDecimal creditosRealizados = BigDecimal.ZERO;
		
		BigDecimal valorImpostos = BigDecimal.ZERO;
		
		BigDecimal totalCobrado = BigDecimal.ZERO;
		
		String idQuebra = null;
		
		List<Object[]> retornoConsulta = 
			fachada.consultarResumoAnaliseFaturamentoRelatorio(informarDadosGeracaoRelatorioConsultaHelper);
		
		if( retornoConsulta != null && !retornoConsulta.equals("")
				&& retornoConsulta.size() != 0 ){
			for (Object[] retornoObject : retornoConsulta) {
					
					valorTotalConta = BigDecimal.ZERO;
				
					if((Integer)retornoObject[0] != null)
					{
						quantidadeConta = ((Integer)retornoObject[0]);
					}
					if((Integer)retornoObject[1] != null)
					{
						quantidadeEconomia = ((Integer)retornoObject[1]);
					}
					if((Integer)retornoObject[2] != null)
					{
						volumeConsumidoAgua = ((Integer)retornoObject[2]);
					}
					if((BigDecimal)retornoObject[3] != null)
					{
						valorFaturadoAgua = (BigDecimal)retornoObject[3];
						valorTotalConta = valorTotalConta.add((BigDecimal)retornoObject[3]);
					}
					if((Integer)retornoObject[4] != null)
					{
						volumeColetadoEsgoto = ((Integer)retornoObject[4]);
					}
					if((BigDecimal)retornoObject[5] != null)
					{
						valorFaturadoEsgoto = (BigDecimal)retornoObject[5];
						valorTotalConta = valorTotalConta.add((BigDecimal)retornoObject[5]);
					}
					
					if((BigDecimal)retornoObject[7] != null)
					{
						debitosCobrados = (BigDecimal)retornoObject[7];
						valorTotalConta = valorTotalConta.add((BigDecimal)retornoObject[7]);
					}
					
					if((BigDecimal)retornoObject[6] != null)
					{
						creditosRealizados = (BigDecimal)retornoObject[6];
						valorTotalConta = valorTotalConta.subtract((BigDecimal)retornoObject[6]);
					}
					if((BigDecimal)retornoObject[8] != null)
					{	
						valorImpostos = (BigDecimal)retornoObject[8];
						valorTotalConta = valorTotalConta.subtract((BigDecimal)retornoObject[8]);
					}
					valorTotalConta = valorTotalConta.setScale(2, BigDecimal.ROUND_HALF_UP);
					if(valorTotalConta.compareTo(new BigDecimal ("0.00")) == 0)
					{
						totalCobrado = BigDecimal.ZERO;
					}
					else
					{
						totalCobrado = valorTotalConta;
					}
					if(retornoObject[9] != null)
					{	
						idQuebra = (String)retornoObject[9];
						
					}
					
					String agrupamento = this.obterDescricaoDoAgrupamento(informarDadosGeracaoRelatorioConsultaHelper,
							informarDadosGeracaoRelatorioConsultaHelper.getOpcaoTotalizacao().toString());
					
					relatorioBean = new RelatorioAnaliseFaturamentoBean(""
							+ descricao, quantidadeConta, quantidadeEconomia, volumeConsumidoAgua, 
							valorFaturadoAgua, volumeColetadoEsgoto, valorFaturadoEsgoto,
							debitosCobrados, creditosRealizados, totalCobrado, valorImpostos,
							idQuebra,agrupamento);

					//Colecao para com os dados do subrelatorio
					Collection<ResumoFaturamentoSimulacaoDetalheHelper> colecaoDetalhes 
			        	= new ArrayList<ResumoFaturamentoSimulacaoDetalheHelper>(); 
					
					//Credito
					informarDadosGeracaoRelatorioConsultaHelper.setTipoDetalhe("CREDITO");
					
					Collection<Object[]> retornoConsultaDetalheCredito = 
			        	fachada.consultarResumoAnaliseFaturamentoDetalhe(informarDadosGeracaoRelatorioConsultaHelper);
			        
			        for (Object[] dados : retornoConsultaDetalheCredito) {
			        	ResumoFaturamentoSimulacaoDetalheHelper  helper 
			        	 	= new ResumoFaturamentoSimulacaoDetalheHelper(
			        	 			(String)dados[0],
			        	 			Util.formatarMoedaReal((BigDecimal) dados[1]),
			        	 			"");
			        	
			        	colecaoDetalhes.add(helper);
					}
			        
			        //Debito 
			        informarDadosGeracaoRelatorioConsultaHelper.setTipoDetalhe("DEBITO");
			        
					Collection<Object[]> retornoConsultaDetalheDebito = 
			        	fachada.consultarResumoAnaliseFaturamentoDetalhe(informarDadosGeracaoRelatorioConsultaHelper);		
			        
			        for (Object[] dados : retornoConsultaDetalheDebito) {
			        	ResumoFaturamentoSimulacaoDetalheHelper  helper 
			        	 	= new ResumoFaturamentoSimulacaoDetalheHelper(
			        	 			 (String)dados[0],
			        	 			 "",
			        	 			 Util.formatarMoedaReal((BigDecimal) dados[1]));
			        	
			        	colecaoDetalhes.add(helper);
					}
			        
			        relatorioBean.setArrayDetalhesJRDetail(
			        		new JRBeanCollectionDataSource(colecaoDetalhes));
					
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
		
		parametros.put("mesAnoFaturamento", mesAnoFaturamento);
		String descricaoAnalise = "";
		if(tipoAnalise == 1)
		{
			descricaoAnalise = "REAL";
		}
		else
		{
			descricaoAnalise = "SIMULADO";
		}
		parametros.put("tipoAnalise", descricaoAnalise);
		

		//----------------------------------------------------------------------------------------------------------------
        // Alterado por : Yara T. Souza.
		// Data : 26/08/2008
		//----------------------------------------------------------------------------------------------------------------
	    

		parametros.put("localidade", localidade);
		parametros.put("gerenciaRegional", gerenciaRegional);
		parametros.put("unidadeNegocio", unidadeNegocio);
		parametros.put("setorComercial", setorComercial);
		parametros.put("quadra", quadra);
		parametros.put("rota", rota);
		parametros.put("grupoFaturamento", grupoFaturamento);
		
		
		if (colecaoPerfilImovel != null) {
			String imoveisPerfil = "";
			
			Iterator itt= colecaoPerfilImovel.iterator();
			boolean primeiro = true;
			while(itt.hasNext()){
				ImovelPerfil imovelPerfil = (ImovelPerfil)itt.next();
				if(imovelPerfil!=null){
					if (primeiro) {
						imoveisPerfil = imoveisPerfil + imovelPerfil.getDescricao();
						primeiro = false;
					} else {
						imoveisPerfil = imoveisPerfil + ", " + imovelPerfil.getDescricao();
					}
				}		
			}
			
			parametros.put("imovelPerfil", imoveisPerfil);

		} else {
			parametros.put("imovelPerfil", "TODOS");
		}
		
		
		if (colecaoLigacaoAgua != null) {
			String ligacoesAguaSituacao = "";
			
			Iterator itt= colecaoLigacaoAgua.iterator();
			boolean primeiro = true;
			while(itt.hasNext()){
				LigacaoAguaSituacao ligacaoAguaSituacao = (LigacaoAguaSituacao)itt.next();
				if(ligacaoAguaSituacao!=null){
					if (primeiro) {
						ligacoesAguaSituacao = ligacoesAguaSituacao + ligacaoAguaSituacao.getDescricao();
						primeiro = false;
					} else {
						ligacoesAguaSituacao = ligacoesAguaSituacao + ", " + ligacaoAguaSituacao.getDescricao();
					}
				}		
			}
			
			parametros.put("ligacaoAguaSituacao", ligacoesAguaSituacao);

		} else {
			parametros.put("ligacaoAguaSituacao", "TODOS");
		}
		
		

		if (colecaoLigacaoEsgoto != null) {
			String ligacoesEsgotoSituacao = "";
			
			Iterator itt= colecaoLigacaoEsgoto.iterator();
			boolean primeiro = true;
			while(itt.hasNext()){
				LigacaoEsgotoSituacao ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao)itt.next();
				if(ligacaoEsgotoSituacao!=null){
					if (primeiro) {
						ligacoesEsgotoSituacao = ligacoesEsgotoSituacao + ligacaoEsgotoSituacao.getDescricao();
						primeiro = false;
					} else {
						ligacoesEsgotoSituacao = ligacoesEsgotoSituacao + ", " + ligacaoEsgotoSituacao.getDescricao();
					}
				}		
			}
			
			parametros.put("ligacaoEsgotoSituacao", ligacoesEsgotoSituacao);

		} else {
			parametros.put("ligacaoEsgotoSituacao", "TODOS");
		}
		
		
		
		if (colecaoCategoria != null) {
			String categorias = "";
			
			Iterator itt= colecaoCategoria.iterator();
			boolean primeiro = true;
			while(itt.hasNext()){
				Categoria categoria = (Categoria)itt.next();
				if(categoria!=null){
					if (primeiro) {
						categorias = categorias + categoria.getDescricao();
						primeiro = false;
					} else {
						categorias = categorias + ", " + categoria.getDescricao();
					}
				}		
			}
			
			parametros.put("categoria", categorias);

		} else {
			parametros.put("categoria", "TODOS");
		}
		
		
		if (colecaoEsferaPoder != null) {
			String esferasPoder = "";
			
			Iterator itt= colecaoEsferaPoder.iterator();
			boolean primeiro = true;
			while(itt.hasNext()){
				EsferaPoder esferaPoder = (EsferaPoder)itt.next();
				if(esferaPoder!=null){
					if (primeiro) {
						esferasPoder = esferasPoder + esferaPoder.getDescricao();
						primeiro = false;
					} else {
						esferasPoder = esferasPoder + ", " + esferaPoder.getDescricao();
					}
				}		
			}
			
			parametros.put("esferaPoder", esferasPoder);

		} else {
			parametros.put("esferaPoder", "TODOS");
		}
		
		if(tipoQuebra!=null && !tipoQuebra.equals("")){
			parametros.put("tipoQuebra", tipoQuebra);
		}
		
		parametros.put("opcaoTotalizacao",
				informarDadosGeracaoRelatorioConsultaHelper.getOpcaoTotalizacao().toString());
		
	    //----------------------------------------------------------------------------------------
		
		
		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_ANALISE_FATURAMENTO, parametros,
				ds, tipoFormatoRelatorio);

		// retorna o relat�rio gerado
		return retorno;
	}

	private String obterDescricaoDoAgrupamento(InformarDadosGeracaoRelatorioConsultaHelper 
			informarDadosGeracaoRelatorioConsultaHelper, String opcaoTotalizacao) {
		
		String retorno = "";
		
		if(opcaoTotalizacao.equals(ConstantesSistema.CODIGO_ESTADO+"")
			||opcaoTotalizacao.equals(ConstantesSistema.CODIGO_ESTADO_GERENCIA_REGIONAL+"")
			||opcaoTotalizacao.equals(ConstantesSistema.CODIGO_ESTADO_UNIDADE_NEGOCIO+"")
			||opcaoTotalizacao.equals(ConstantesSistema.CODIGO_ESTADO_LOCALIDADE+"")
			||opcaoTotalizacao.equals(ConstantesSistema.CODIGO_ESTADO_GRUPO_FATURAMENTO+"")
			||opcaoTotalizacao.equals(ConstantesSistema.CODIGO_ESTADO_MUNICIPIO+"")){
			
			retorno = "ESTADO";
			
		}else if(opcaoTotalizacao.equals(ConstantesSistema.CODIGO_ESTADO_GRUPO_FATURAMENTO+"")
			||opcaoTotalizacao.equals(ConstantesSistema.CODIGO_GRUPO_FATURAMENTO+"")){
			
			retorno = informarDadosGeracaoRelatorioConsultaHelper
				.getFaturamentoGrupo().getDescricao();
			
		}else if(opcaoTotalizacao.equals(ConstantesSistema.CODIGO_GERENCIA_REGIONAL_UNIDADE_NEGOCIO+"")
			||opcaoTotalizacao.equals(ConstantesSistema.CODIGO_GERENCIA_REGIONAL_LOCALIDADE+"")){
			
			retorno = informarDadosGeracaoRelatorioConsultaHelper
				.getGerenciaRegional().getNome();
			
		}else if(opcaoTotalizacao.equals(ConstantesSistema.CODIGO_UNIDADE_NEGOCIO+"")	
			||opcaoTotalizacao.equals(ConstantesSistema.CODIGO_UNIDADE_NEGOCIO_LOCALIDADE+"")
			||opcaoTotalizacao.equals(ConstantesSistema.CODIGO_UNIDADE_NEGOCIO_SETOR_COMERCIAL+"")){
			
			retorno = informarDadosGeracaoRelatorioConsultaHelper
				.getUnidadeNegocio().getNome();
			
		}else if(opcaoTotalizacao.equals(ConstantesSistema.CODIGO_LOCALIDADE_SETOR_COMERCIAL+"")
			||opcaoTotalizacao.equals(ConstantesSistema.CODIGO_SETOR_COMERCIAL_QUADRA+"")){
			
			retorno = informarDadosGeracaoRelatorioConsultaHelper
				.getLocalidade().getDescricao();
			
		}else if(opcaoTotalizacao.equals(ConstantesSistema.CODIGO_SETOR_COMERCIAL_QUADRA+"")){
			
			retorno = informarDadosGeracaoRelatorioConsultaHelper
				.getSetorComercial().getDescricao();
			
		}
		
		return retorno;
		
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void agendarTarefaBatch() {
	}

}