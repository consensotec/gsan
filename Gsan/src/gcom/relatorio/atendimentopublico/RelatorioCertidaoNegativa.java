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
 * classe respons�vel por criar o relat�rio de certidao negativa
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
		
		String mensagemRodape = "Certid�o Negativa de D�bitos";

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioCertidaoNegativaBean relatorioCertidaoNegativaBean = null;

		Collection<RelatorioCertidaoNegativaHelper> colecao =  
			fachada.pesquisarRelatorioCertidaoNegativa(imo);

		// se a cole��o de par�metros da analise n�o for vazia
		if (colecao != null && !colecao.isEmpty()) {

			// coloca a cole��o de par�metros da analise no iterator
			Iterator colecaoIterator = colecao.iterator();

			// la�o para criar a cole��o de par�metros da analise
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

				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioCertidaoNegativaBean);				
			}
		}		

		// Par�metros do relat�rio
		Map parametros = new HashMap();
		
		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		String nomeRelatorio = ConstantesRelatorios.RELATORIO_CERTIDAO_NEGATIVA;
		String validade = "IMPORTANTE: Qualquer rasura tornar� nulo o efeito desta certid�o.";
		
		if (sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAEMA)) {
			Boolean indicadorCertidaoRegistrada = false;
			
			parametros.put("atendente", "");
			if ( usuarioLogado != null) {
				parametros.put("atendente", usuarioLogado.getNomeUsuario());
			}
			
			nomeRelatorio = ConstantesRelatorios.RELATORIO_CERTIDAO_NEGATIVA_CAEMA;
			validade = "IMPORTANTE: Qualquer rasura tornar� nulo o efeito desta certid�o, que tem validade de 60 dias.";
			
			parametros.put("nomeRelatorio", "CERTID�O NEGATIVA DE D�BITOS");
			
			if(indicadorContaParcelada || indicadorContaRevisao){//Certid�o Positiva com Efeito de Negativa
				parametros.put("nomeRelatorio", "CERTID�O POSITIVA COM EFEITO DE NEGATIVA");
				mensagemRodape = "Certid�o Positiva com efeito de Negativa";
				indicadorCertidaoRegistrada = true;
			}
			
			if(indicadorContaVencida && !indicadorCertidaoRegistrada){
				parametros.put("nomeRelatorio", "CERTID�O POSITIVA DE D�BITOS");
				mensagemRodape = "Certid�o Positiva de D�bitos";
			}			
			
			parametros.put("nomeEmpresa", "COMPANHIA DE SANEAMENTO AMBIENTAL DO MARANH�O");
			parametros.put("cnpjEmpresa", Util.formatarCnpj( sistemaParametro.getCnpjEmpresa()) );
			parametros.put("inscricaoEstadual", Util.formatarInscricaoEstadualCaema( sistemaParametro.getInscricaoEstadual()) );
			if(!indicadorContaParcelada && !indicadorContaVencida){
				parametros.put("textoCertidaoNegativa","Pelo presente instrumento certificamos, para fins de direito, " + 
					"que revendo os nossos controles, n�o encontramos d�bitos referente ao im�vel acima especificado(s) at� a presente data: "
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
				"Voc� poder� verificar a autenticidade da Certid�o Negativa de D�bitos emitida na nossa loja virtual"; 
			
			if (sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAEMA)) {
				textoInformativo = 
//					"Voc� poder� verificar a autenticidade da Certid�o Negativa de D�bitos emitida na nossa loja virtual no site www.caema.ma.gov.br, " +
//					"clicar no link da Loja Virtual, menu informa��es, op��o Validar certid�o negativa de d�bitos."; 
				// [RM12995 - Mudar texto do rodap� das certid�es da CAEMA - 06/01/2015]
					"Voc�  poder�  verificar  a   autenticidade   desta   Certid�o  acessando  nossa   loja   virtual  no  site  www.caema.ma.gov.br, " +
					"clicar no link da Loja Virtual, menu informa��es, op��o Validar certid�o negativa de d�bitos."; 						
			}else if (sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAER)) {
				textoInformativo = 
						"Voc� poder� verificar a autenticidade da" + mensagemRodape + "emitida na nossa loja virtual no site www.caer.com.br, " +
						"clicar no link da Loja Virtual, menu informa��es, op��o Validar certid�o negativa de d�bitos.";				
			}
			
			parametros.put("textoInformativo",textoInformativo);			
		}
		
		parametros.put("validade", validade);
		
		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		retorno = gerarRelatorio(nomeRelatorio,
				parametros, ds, tipoFormatoRelatorio);
		
		if (sistemaParametro.getQuantidadeDiasValidadeCerticaoNegativa() != null
				&& sistemaParametro.getQuantidadeDiasValidadeCerticaoNegativa().compareTo(new Integer(0)) > 0) {

			//[SB0001] - Inserir certid�o de negativa de d�bito
			fachada.inserirCertidaoNegativaDebito(imo, autenticacaoEletronica, retorno, usuarioLogado);			
		}
		
		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.CERTIDAO_NEGATIVA,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		}
		// ------------------------------------
		// retorna o relat�rio gerado
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