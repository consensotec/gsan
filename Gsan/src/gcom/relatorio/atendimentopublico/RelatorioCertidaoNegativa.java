/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.relatorio.atendimentopublico;

import gcom.batch.Relatorio;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de certidao negativa
 * 
 * @author Bruno Barros
 * @created 29/01/2008
 */
public class RelatorioCertidaoNegativa extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioCertidaoNegativa(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_CERTIDAO_NEGATIVA);
	}

	@Deprecated
	public RelatorioCertidaoNegativa() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param bairros
	 *            Description of the Parameter
	 * @param bairroParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */
	public Object executar() throws TarefaException {

		// valor de retorno
		byte[] retorno = null;

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		Imovel imo = (Imovel) getParametro("imovel");
		
		Usuario usuarioLogado = (Usuario) getParametro("usuarioLogado");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		Boolean lojaVirtual = (Boolean) getParametro("lojaVirtual");		
		
		Boolean indicadorContaVencida = false;
		
		Boolean indicadorContaParcelada = false;
		
		Boolean indicadorContaRevisao = false;
		
		String mensagemRodape = "Certidão Negativa de Débitos";

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioCertidaoNegativaBean relatorioCertidaoNegativaBean = null;

		Collection<RelatorioCertidaoNegativaHelper> colecao =  
			fachada.pesquisarRelatorioCertidaoNegativa(imo);

		// se a coleção de parâmetros da analise não for vazia
		if (colecao != null && !colecao.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoIterator = colecao.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (colecaoIterator.hasNext()) {

				RelatorioCertidaoNegativaHelper helper = 
					(RelatorioCertidaoNegativaHelper) colecaoIterator.next();				
				
				if(helper.getIndicadorContaVencidas() != null){
					indicadorContaVencida = helper.getIndicadorContaVencidas();
				}				
				
				if(helper.getImovelComParcelamento() != null){
					indicadorContaParcelada = helper.getImovelComParcelamento(); 
				}
				
				if(helper.getIndicadorContaRevisao() != null){
					indicadorContaRevisao = helper.getIndicadorContaRevisao();
				}
				
				relatorioCertidaoNegativaBean = 
					new RelatorioCertidaoNegativaBean(helper);

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioCertidaoNegativaBean);				
			}
		}		

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		String nomeRelatorio = ConstantesRelatorios.RELATORIO_CERTIDAO_NEGATIVA;
		String validade = "IMPORTANTE: Qualquer rasura tornará nulo o efeito desta certidão.";
		
		if (sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAEMA)) {
			Boolean indicadorCertidaoRegistrada = false;
			
			parametros.put("atendente", "");
			if ( usuarioLogado != null) {
				parametros.put("atendente", usuarioLogado.getNomeUsuario());
			}
			
			nomeRelatorio = ConstantesRelatorios.RELATORIO_CERTIDAO_NEGATIVA_CAEMA;
			validade = "IMPORTANTE: Qualquer rasura tornará nulo o efeito desta certidão, que tem validade de 60 dias.";
			
			parametros.put("nomeRelatorio", "CERTIDÃO NEGATIVA DE DÉBITOS");
			
			if(indicadorContaParcelada || indicadorContaRevisao){//Certidão Positiva com Efeito de Negativa
				parametros.put("nomeRelatorio", "CERTIDÃO POSITIVA COM EFEITO DE NEGATIVA");
				mensagemRodape = "Certidão Positiva com efeito de Negativa";
				indicadorCertidaoRegistrada = true;
			}
			
			if(indicadorContaVencida && !indicadorCertidaoRegistrada){
				parametros.put("nomeRelatorio", "CERTIDÃO POSITIVA DE DÉBITOS");
				mensagemRodape = "Certidão Positiva de Débitos";
			}			
			
			parametros.put("nomeEmpresa", "COMPANHIA DE SANEAMENTO AMBIENTAL DO MARANHÃO");
			parametros.put("cnpjEmpresa", Util.formatarCnpj( sistemaParametro.getCnpjEmpresa()) );
			parametros.put("inscricaoEstadual", Util.formatarInscricaoEstadualCaema( sistemaParametro.getInscricaoEstadual()) );
			if(!indicadorContaParcelada && !indicadorContaVencida){
				parametros.put("textoCertidaoNegativa","Pelo presente instrumento certificamos, para fins de direito, " + 
					"que revendo os nossos controles, não encontramos débitos referente ao imóvel acima especificado(s) até a presente data: "
								+ Util.formatarData(new Date()) + ".");
			}else{
				parametros.put("textoCertidaoNegativa","");
			}
		}
		
		if (lojaVirtual) {			
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(
				new ParametroSimples(FiltroUsuario.INDICADOR_USUARIO_INTERNET, 
					ConstantesSistema.INDICADOR_USO_ATIVO));
			
			usuarioLogado = 
					(Usuario) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroUsuario, Usuario.class.getName()));			
		}

		String autenticacaoEletronica = null;
		
		if (sistemaParametro.getQuantidadeDiasValidadeCerticaoNegativa() != null
				&& sistemaParametro.getQuantidadeDiasValidadeCerticaoNegativa().compareTo(new Integer(0)) > 0) {

			autenticacaoEletronica = Util.obterStringAleatoria(1).toUpperCase()
											+ Util.obterNumeroInteiroAleatorio(0, 9)
											+ Util.obterStringAleatoria(1).toUpperCase()
											+ Util.obterNumeroInteiroAleatorio(0, 9)
											+ Util.formatarDataAAAAMMDD(new Date());
			
			parametros.put("autenticacaoEletronica", autenticacaoEletronica);
			
			String textoInformativo = 
				"Você poderá verificar a autenticidade da Certidão Negativa de Débitos emitida na nossa loja virtual"; 
			
			if (sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAEMA)) {
				textoInformativo = 
//					"Você poderá verificar a autenticidade da Certidão Negativa de Débitos emitida na nossa loja virtual no site www.caema.ma.gov.br, " +
//					"clicar no link da Loja Virtual, menu informações, opção Validar certidão negativa de débitos."; 
				// [RM12995 - Mudar texto do rodapé das certidões da CAEMA - 06/01/2015]
					"Você  poderá  verificar  a   autenticidade   desta   Certidão  acessando  nossa   loja   virtual  no  site  www.caema.ma.gov.br, " +
					"clicar no link da Loja Virtual, menu informações, opção Validar certidão negativa de débitos."; 						
			}else if (sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAER)) {
				textoInformativo = 
						"Você poderá verificar a autenticidade da" + mensagemRodape + "emitida na nossa loja virtual no site www.caer.com.br, " +
						"clicar no link da Loja Virtual, menu informações, opção Validar certidão negativa de débitos.";				
			}
			
			parametros.put("textoInformativo",textoInformativo);			
		}
		
		parametros.put("validade", validade);
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		retorno = gerarRelatorio(nomeRelatorio,
				parametros, ds, tipoFormatoRelatorio);
		
		if (sistemaParametro.getQuantidadeDiasValidadeCerticaoNegativa() != null
				&& sistemaParametro.getQuantidadeDiasValidadeCerticaoNegativa().compareTo(new Integer(0)) > 0) {

			//[SB0001] - Inserir certidão de negativa de débito
			fachada.inserirCertidaoNegativaDebito(imo, autenticacaoEletronica, retorno, usuarioLogado);			
		}
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.CERTIDAO_NEGATIVA,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------
		// retorna o relatório gerado
		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioCertidaoNegativa", this);
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}
}